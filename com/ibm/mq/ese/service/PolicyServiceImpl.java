/*      */ package com.ibm.mq.ese.service;
/*      */ 
/*      */ import com.ibm.mq.ese.core.SecurityPolicy;
/*      */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*      */ import com.ibm.mq.headers.MQDataException;
/*      */ import com.ibm.mq.headers.pcf.MQCFIN;
/*      */ import com.ibm.mq.headers.pcf.MQCFSL;
/*      */ import com.ibm.mq.headers.pcf.MQCFST;
/*      */ import com.ibm.mq.headers.pcf.PCFMessage;
/*      */ import com.ibm.mq.headers.pcf.PCFParameter;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.MQGMO;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Hobj;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.DataInput;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.IOException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PolicyServiceImpl
/*      */   implements PolicyService
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/PolicyServiceImpl.java";
/*      */   public static final int SMQOP_WAIT_INTERVAL = 5000;
/*      */   public static final String MQESE_POLICY_QUEUE_NAME = "SYSTEM.PROTECTION.POLICY.QUEUE";
/*      */   public static final String MQESE_ERROR_QUEUE_NAME = "SYSTEM.PROTECTION.ERROR.QUEUE";
/*      */   public static final int SMQOP_MAX_GET_ATTEMPTS = 5;
/*      */   
/*      */   static {
/*   69 */     if (Trace.isOn) {
/*   70 */       Trace.data("com.ibm.mq.ese.service.PolicyServiceImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/PolicyServiceImpl.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  104 */   public static final byte[] MQESE_POLINDEX_CORREL_ID_ARRAY = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
/*      */ 
/*      */   
/*      */   private static final int MQESE_POLINDEX_QPOL_MAP_PARM = 2;
/*      */ 
/*      */   
/*      */   private static final int MQESE_POLINDEX_VERSION_PARM = 3;
/*      */ 
/*      */   
/*      */   private static final int MQESE_ERROR_Q_NAME_PARM = 4;
/*      */ 
/*      */   
/*      */   private static final int MQESE_POLINDEX_MAPSTR_LENGTH = 96;
/*      */ 
/*      */   
/*      */   private static final int MQESE_POLICY_VERSION_PARM = 5;
/*      */ 
/*      */   
/*      */   private static final int MQESE_POLICY_NAME_PARM = 6;
/*      */ 
/*      */   
/*      */   private static final int MQESE_POLICY_SIGN_ALG_PARM = 7;
/*      */ 
/*      */   
/*      */   private static final int MQESE_POLICY_ENC_ALG_PARM = 8;
/*      */ 
/*      */   
/*      */   private static final int MQESE_POLICY_AUDIT_PARM = 9;
/*      */ 
/*      */   
/*      */   private static final int MQESE_POLICY_SIGNERDN_COUNT_PARM = 10;
/*      */ 
/*      */   
/*      */   private static final int MQESE_POLICY_SIGNERDN_PARM = 11;
/*      */   
/*      */   private static final int MQESE_POLICY_RECIPIENTDN_COUNT_PARM = 12;
/*      */   
/*      */   private static final int MQESE_POLICY_RECIPIENTDN_PARM = 13;
/*      */   
/*      */   private static final int MQESE_POLICY_TOLERATE_FLAG_PARM = 14;
/*      */   
/*      */   private static final int MQESE_POLICY_INDEX_VERSION_1 = 1;
/*      */   
/*      */   private static final int SMQOP_GETPOLICY_SLEEP_TIME = 5;
/*      */   
/*      */   private JmqiMQ jmqi;
/*      */   
/*      */   private JmqiSystemEnvironment env;
/*      */   
/*      */   private EseMQService mqService;
/*      */ 
/*      */   
/*      */   public PolicyServiceImpl(JmqiMQ jmqi, JmqiSystemEnvironment env, EseMQService mqService) {
/*  157 */     if (Trace.isOn) {
/*  158 */       Trace.entry(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "<init>(JmqiMQ,JmqiSystemEnvironment,EseMQService)", new Object[] { jmqi, env, mqService });
/*      */     }
/*      */ 
/*      */     
/*  162 */     this.jmqi = jmqi;
/*  163 */     this.env = env;
/*  164 */     this.mqService = mqService;
/*  165 */     if (Trace.isOn) {
/*  166 */       Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "<init>(JmqiMQ,JmqiSystemEnvironment,EseMQService)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SecurityPolicy getPolicy(String qmgrName, String name, Hconn hconn, Pint cc, Pint rc) throws EseMQException {
/*  181 */     if (Trace.isOn) {
/*  182 */       Trace.entry(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String,String,Hconn,Pint,Pint)", new Object[] { qmgrName, name, hconn, cc, rc });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  187 */     Phobj pHobj = this.env.newPhobj();
/*  188 */     SecurityPolicy policy = null;
/*  189 */     Pint pCC = this.env.newPint();
/*  190 */     Pint pRC = this.env.newPint();
/*      */     try {
/*  192 */       validateGetPolicyInput(qmgrName, name, hconn, cc, rc);
/*      */       
/*  194 */       if (PolicyBlackList.isPolicyBlackListed(name)) {
/*  195 */         SecurityPolicy traceRet1 = createNonePolicy(name);
/*  196 */         if (Trace.isOn) {
/*  197 */           Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String,String,Hconn,Pint,Pint)", traceRet1, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  202 */         return traceRet1;
/*      */       } 
/*      */ 
/*      */       
/*  206 */       openPolicyQueue(qmgrName, hconn, pHobj, pCC, pRC);
/*  207 */       if (pRC.x == 2085) {
/*  208 */         if (Trace.isOn) {
/*  209 */           Trace.traceInfo(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String, String, Hconn, cc, rc)", "MQRC_UNKNOWN_OBJECT_NAME policy queue not found", "");
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  215 */         SecurityPolicy traceRet2 = createNonePolicy(name);
/*  216 */         if (Trace.isOn) {
/*  217 */           Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String,String,Hconn,Pint,Pint)", traceRet2, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  222 */         return traceRet2;
/*      */       } 
/*  224 */       if (pCC.x != 0) {
/*  225 */         if (Trace.isOn) {
/*  226 */           Trace.traceInfo(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String, String, Hconn, cc, rc)", "Could not open policy queue. RC = ", pRC);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  231 */         cc.x = pCC.x;
/*  232 */         rc.x = pRC.x;
/*  233 */         if (Trace.isOn) {
/*  234 */           Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String,String,Hconn,Pint,Pint)", null, 3);
/*      */         }
/*      */ 
/*      */         
/*  238 */         return null;
/*      */       } 
/*      */ 
/*      */       
/*  242 */       for (int attempt = 1; attempt <= 5; attempt++) {
/*  243 */         if (Trace.isOn) {
/*  244 */           Trace.traceInfo(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String, String, Hconn, cc, rc)", "Checking for index message. attempt: " + attempt);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  251 */         IndexMessage indexMessage = loadIndexMessage(20, hconn, pHobj
/*      */             
/*  253 */             .getHobj(), pCC, pRC);
/*  254 */         if (pRC.x == 2033)
/*      */         
/*      */         { 
/*  257 */           browseFirstMessage(hconn, pHobj.getHobj(), pCC, pRC);
/*      */           
/*  259 */           if (pCC.x == 0) {
/*      */             
/*  261 */             if (Trace.isOn) {
/*  262 */               Trace.traceInfo(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String, String, Hconn, cc, rc)", "There is now a message on the policy queue");
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */           else {
/*      */             
/*  269 */             if (pRC.x == 2033) {
/*      */ 
/*      */               
/*  272 */               if (Trace.isOn) {
/*  273 */                 Trace.traceInfo(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String, String, Hconn, cc, rc)", "Policy queue is empty", "");
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  278 */               SecurityPolicy traceRet3 = createNonePolicy(name);
/*  279 */               if (Trace.isOn) {
/*  280 */                 Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String,String,Hconn,Pint,Pint)", traceRet3, 4);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  285 */               return traceRet3;
/*      */             } 
/*  287 */             if (pRC.x == 2080) {
/*      */ 
/*      */ 
/*      */               
/*  291 */               Thread.sleep(5L);
/*      */             
/*      */             }
/*      */             else {
/*      */               
/*  296 */               if (Trace.isOn) {
/*  297 */                 Trace.traceInfo(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String, String, Hconn, cc, rc)", "Could not get policy message. RC = ", pRC);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  302 */               cc.x = pCC.x;
/*  303 */               rc.x = pRC.x;
/*  304 */               if (Trace.isOn) {
/*  305 */                 Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String,String,Hconn,Pint,Pint)", null, 5);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  310 */               return null;
/*      */             } 
/*      */           }  }
/*  313 */         else { if (pCC.x != 0) {
/*  314 */             if (Trace.isOn) {
/*  315 */               Trace.traceInfo(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String, String, Hconn, cc, rc)", "error while getting the index message ", pRC);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  320 */             cc.x = pCC.x;
/*  321 */             rc.x = pRC.x;
/*  322 */             if (Trace.isOn) {
/*  323 */               Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String,String,Hconn,Pint,Pint)", null, 6);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  328 */             return null;
/*      */           } 
/*      */           
/*  331 */           if (Trace.isOn) {
/*  332 */             Trace.traceInfo(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String, String, Hconn, cc, rc)", "Index message read");
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  337 */           PolicyMapping mapping = findMapping(name, indexMessage);
/*  338 */           if (mapping == null) {
/*  339 */             SecurityPolicy traceRet4 = createNonePolicy(name);
/*  340 */             if (Trace.isOn) {
/*  341 */               Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String,String,Hconn,Pint,Pint)", traceRet4, 7);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  346 */             return traceRet4;
/*      */           } 
/*  348 */           byte[] correlId = hexToString(mapping.correlId);
/*  349 */           policy = loadPolicyMessage(20, correlId, hconn, pHobj
/*  350 */               .getHobj(), pCC, pRC);
/*  351 */           if (pCC.x == 0) {
/*      */             
/*  353 */             policy.setErrorQName(trimmedOrEmpty(indexMessage.errorQName));
/*  354 */             if (Trace.isOn) {
/*  355 */               Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String,String,Hconn,Pint,Pint)", policy, 8);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  360 */             return policy;
/*      */           } 
/*  362 */           if (pRC.x == 2033) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  367 */             Thread.sleep(5L);
/*      */           }
/*      */           else {
/*      */             
/*  371 */             if (Trace.isOn) {
/*  372 */               Trace.traceInfo(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String, String, Hconn, cc, rc)", "error while locating the policy message ", pRC);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  377 */             cc.x = pCC.x;
/*  378 */             rc.x = pRC.x;
/*  379 */             if (Trace.isOn) {
/*  380 */               Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String,String,Hconn,Pint,Pint)", null, 9);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  385 */             return null;
/*      */           }  }
/*      */       
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  392 */       if (Trace.isOn) {
/*  393 */         Trace.traceInfo(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String, String, Hconn, cc, rc)", "Could not read policy message ", "");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  398 */       cc.x = 2;
/*  399 */       rc.x = 2063;
/*  400 */       if (Trace.isOn) {
/*  401 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String,String,Hconn,Pint,Pint)", null, 10);
/*      */       }
/*      */       
/*  404 */       return null;
/*      */     }
/*  406 */     catch (EseMQException e) {
/*  407 */       if (Trace.isOn) {
/*  408 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String,String,Hconn,Pint,Pint)", (Throwable)e, 1);
/*      */       }
/*      */ 
/*      */       
/*  412 */       HashMap<String, Object> inserts = new HashMap<>();
/*  413 */       inserts.put("AMS_INSERT_MQ_REASON_CODE", 
/*  414 */           Integer.valueOf(e.getReason()).toString());
/*  415 */       EseMQException ex = new EseMQException(AmsErrorMessages.mjs_msg_error_failed_to_obtain_policy, inserts, (Throwable)e);
/*      */ 
/*      */       
/*  418 */       ex.setReason(e.getReason());
/*  419 */       cc.x = 2;
/*  420 */       rc.x = e.getReason();
/*  421 */       if (Trace.isOn) {
/*  422 */         Trace.throwing(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String,String,Hconn,Pint,Pint)", (Throwable)ex, 1);
/*      */       }
/*      */ 
/*      */       
/*  426 */       throw ex;
/*      */     }
/*  428 */     catch (Exception e) {
/*  429 */       if (Trace.isOn) {
/*  430 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String,String,Hconn,Pint,Pint)", e, 2);
/*      */       }
/*      */ 
/*      */       
/*  434 */       EseMQException traceRet5 = new EseMQException(e, 2195);
/*      */       
/*  436 */       if (Trace.isOn) {
/*  437 */         Trace.throwing(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String,String,Hconn,Pint,Pint)", (Throwable)traceRet5, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  442 */       throw traceRet5;
/*      */     } finally {
/*      */       
/*  445 */       if (Trace.isOn) {
/*  446 */         Trace.finallyBlock(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String,String,Hconn,Pint,Pint)");
/*      */       }
/*      */ 
/*      */       
/*  450 */       if (pHobj != null && pHobj.getHobj().getIntegerHandle() != -1 && pHobj.getHobj().getIntegerHandle() != 0) {
/*  451 */         this.jmqi.MQCLOSE(hconn, pHobj, 0, pCC, pRC);
/*      */       }
/*  453 */       if (Trace.isOn) {
/*  454 */         String policyStr = "<null>";
/*  455 */         if (policy != null) {
/*  456 */           policyStr = policy.toString();
/*  457 */           List<String> signerDNs = policy.getSignerDNs();
/*  458 */           policyStr = policyStr + " /signer DNs: ";
/*  459 */           if (signerDNs != null) {
/*  460 */             for (String dn : signerDNs) {
/*  461 */               policyStr = policyStr + " '" + dn + "'";
/*      */             }
/*      */           }
/*  464 */           policyStr = policyStr + " /recipient DNs: ";
/*  465 */           List<String> recipientDNs = policy.getRecipientDNs();
/*  466 */           if (recipientDNs != null) {
/*  467 */             for (String dn : recipientDNs) {
/*  468 */               policyStr = policyStr + " '" + dn + "'";
/*      */             }
/*      */           }
/*      */         } 
/*  472 */         Trace.traceInfo(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getPolicy(String, String, Hconn, cc, rc)", policyStr, "");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void browseFirstMessage(Hconn hconn, Hobj hobj, Pint pCC, Pint pRC) {
/*  481 */     if (Trace.isOn) {
/*  482 */       Trace.entry(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "browseFirstMessage(Hconn,Hobj,Pint,Pint)", new Object[] { hconn, hobj, pCC, pRC });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  487 */     MQMD md = this.env.newMQMD();
/*  488 */     MQGMO gmo = this.env.newMQGMO();
/*  489 */     ByteBuffer initialBuffer = null;
/*  490 */     Pint pDataLength = this.env.newPint();
/*      */     
/*  492 */     gmo.setVersion(2);
/*  493 */     int options = 16;
/*  494 */     options &= 0xFFFFFFBF;
/*  495 */     gmo.setOptions(options);
/*  496 */     md.setCodedCharSetId(1208);
/*  497 */     md.setEncoding(273);
/*      */     
/*  499 */     this.mqService.getMessage(hconn, hobj, md, gmo, -1, 2147483647, initialBuffer, pDataLength, pCC, pRC);
/*      */ 
/*      */     
/*  502 */     if (Trace.isOn) {
/*  503 */       Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "browseFirstMessage(Hconn,Hobj,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void openPolicyQueue(String qmgrName, Hconn hconn, Phobj pHobj, Pint pCC, Pint pRC) {
/*  520 */     if (Trace.isOn) {
/*  521 */       Trace.entry(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "openPolicyQueue(String,Hconn,Phobj,Pint,Pint)", new Object[] { qmgrName, hconn, pHobj, pCC, pRC });
/*      */     }
/*      */ 
/*      */     
/*  525 */     MQOD objDesc = this.env.newMQOD();
/*  526 */     objDesc.setObjectQMgrName(qmgrName);
/*  527 */     objDesc.setObjectName("SYSTEM.PROTECTION.POLICY.QUEUE");
/*  528 */     int openOpts = 8;
/*  529 */     this.jmqi.MQOPEN(hconn, objDesc, openOpts, pHobj, pCC, pRC);
/*  530 */     if (Trace.isOn) {
/*  531 */       Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "openPolicyQueue(String,Hconn,Phobj,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void validateGetPolicyInput(String qmgrName, String name, Hconn hconn, Pint cc, Pint rc) throws EseMQException {
/*  547 */     if (Trace.isOn) {
/*  548 */       Trace.entry(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "validateGetPolicyInput(String,String,Hconn,Pint,Pint)", new Object[] { qmgrName, name, hconn, cc, rc });
/*      */     }
/*      */ 
/*      */     
/*  552 */     if (name == null) {
/*  553 */       EseMQException traceRet1 = failedToObtainPolicyException(2152, null);
/*      */       
/*  555 */       if (Trace.isOn) {
/*  556 */         Trace.throwing(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "validateGetPolicyInput(String,String,Hconn,Pint,Pint)", (Throwable)traceRet1, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  562 */       throw traceRet1;
/*      */     } 
/*  564 */     if (hconn == null) {
/*  565 */       EseMQException traceRet2 = failedToObtainPolicyException(2018, null);
/*      */       
/*  567 */       if (Trace.isOn) {
/*  568 */         Trace.throwing(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "validateGetPolicyInput(String,String,Hconn,Pint,Pint)", (Throwable)traceRet2, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  574 */       throw traceRet2;
/*      */     } 
/*  576 */     if (qmgrName == null) {
/*  577 */       EseMQException traceRet3 = failedToObtainPolicyException(2153, null);
/*      */       
/*  579 */       if (Trace.isOn) {
/*  580 */         Trace.throwing(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "validateGetPolicyInput(String,String,Hconn,Pint,Pint)", (Throwable)traceRet3, 3);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  586 */       throw traceRet3;
/*      */     } 
/*  588 */     if (cc == null || rc == null) {
/*  589 */       EseMQException traceRet4 = failedToObtainPolicyException(2321, null);
/*      */       
/*  591 */       if (Trace.isOn) {
/*  592 */         Trace.throwing(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "validateGetPolicyInput(String,String,Hconn,Pint,Pint)", (Throwable)traceRet4, 4);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  598 */       throw traceRet4;
/*      */     } 
/*  600 */     if (Trace.isOn) {
/*  601 */       Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "validateGetPolicyInput(String,String,Hconn,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static EseMQException failedToObtainPolicyException(int rc, Throwable cause) throws EseMQException {
/*  616 */     if (Trace.isOn)
/*  617 */       Trace.entry("com.ibm.mq.ese.service.PolicyServiceImpl", "failedToObtainPolicyException(int,Throwable)", new Object[] {
/*      */             
/*  619 */             Integer.valueOf(rc), cause
/*      */           }); 
/*  621 */     HashMap<String, Object> inserts = new HashMap<>();
/*  622 */     inserts.put("AMS_INSERT_MQ_REASON_CODE", 
/*  623 */         Integer.valueOf(rc).toString());
/*  624 */     EseMQException ex = null;
/*  625 */     if (cause != null) {
/*  626 */       ex = new EseMQException(AmsErrorMessages.mjs_msg_error_failed_to_obtain_policy, inserts, cause);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  631 */       ex = new EseMQException(AmsErrorMessages.mjs_msg_error_failed_to_obtain_policy, inserts);
/*      */     } 
/*      */ 
/*      */     
/*  635 */     ex.setReason(rc);
/*  636 */     if (Trace.isOn) {
/*  637 */       Trace.exit("com.ibm.mq.ese.service.PolicyServiceImpl", "failedToObtainPolicyException(int,Throwable)", ex);
/*      */     }
/*      */     
/*  640 */     return ex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] hexToString(String correlId) throws EseMQException {
/*  652 */     if (Trace.isOn) {
/*  653 */       Trace.entry("com.ibm.mq.ese.service.PolicyServiceImpl", "hexToString(String)", new Object[] { correlId });
/*      */     }
/*      */ 
/*      */     
/*  657 */     int length = 24;
/*  658 */     byte[] string = new byte[24]; int i;
/*  659 */     for (i = 0; i < length; i++) {
/*  660 */       char c = correlId.charAt(i);
/*  661 */       if (!isHexDigit(c)) {
/*  662 */         EseMQException traceRet1 = failedToObtainPolicyException(2207, null);
/*      */         
/*  664 */         if (Trace.isOn) {
/*  665 */           Trace.throwing("com.ibm.mq.ese.service.PolicyServiceImpl", "hexToString(String)", (Throwable)traceRet1);
/*      */         }
/*      */         
/*  668 */         throw traceRet1;
/*      */       } 
/*      */     } 
/*  671 */     for (i = 0; i < length; i++) {
/*  672 */       char c1 = correlId.charAt(2 * i);
/*  673 */       char c2 = correlId.charAt(2 * i + 1);
/*  674 */       int val = toDigit(c1) * 16 + toDigit(c2);
/*  675 */       string[i] = (byte)val;
/*      */     } 
/*  677 */     if (Trace.isOn) {
/*  678 */       Trace.exit("com.ibm.mq.ese.service.PolicyServiceImpl", "hexToString(String)", string);
/*      */     }
/*      */     
/*  681 */     return string;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isHexDigit(char c) {
/*  689 */     if (Trace.isOn) {
/*  690 */       Trace.entry("com.ibm.mq.ese.service.PolicyServiceImpl", "isHexDigit(char)", new Object[] {
/*  691 */             Character.valueOf(c)
/*      */           });
/*      */     }
/*      */     
/*      */     try {
/*  696 */       Integer.valueOf(String.valueOf(c), 16);
/*  697 */       if (Trace.isOn) {
/*  698 */         Trace.exit("com.ibm.mq.ese.service.PolicyServiceImpl", "isHexDigit(char)", 
/*  699 */             Boolean.valueOf(true), 1);
/*      */       }
/*  701 */       return true;
/*      */     }
/*  703 */     catch (NumberFormatException e) {
/*  704 */       if (Trace.isOn) {
/*  705 */         Trace.catchBlock("com.ibm.mq.ese.service.PolicyServiceImpl", "isHexDigit(char)", e);
/*      */       }
/*      */       
/*  708 */       if (Trace.isOn) {
/*  709 */         Trace.exit("com.ibm.mq.ese.service.PolicyServiceImpl", "isHexDigit(char)", 
/*  710 */             Boolean.valueOf(false), 2);
/*      */       }
/*  712 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int toDigit(char c) {
/*  723 */     if (Trace.isOn) {
/*  724 */       Trace.entry("com.ibm.mq.ese.service.PolicyServiceImpl", "toDigit(char)", new Object[] {
/*  725 */             Character.valueOf(c)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  735 */     int traceRet1 = Integer.valueOf(String.valueOf(c), 16).intValue();
/*  736 */     if (Trace.isOn) {
/*  737 */       Trace.exit("com.ibm.mq.ese.service.PolicyServiceImpl", "toDigit(char)", 
/*  738 */           Integer.valueOf(traceRet1));
/*      */     }
/*  740 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private SecurityPolicy loadPolicyMessage(int pOptions, byte[] correlId, Hconn hconn, Hobj hobj, Pint pCC, Pint pRC) throws EseMQException {
/*  759 */     if (Trace.isOn) {
/*  760 */       Trace.entry(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "loadPolicyMessage(int,byte [ ],Hconn,Hobj,Pint,Pint)", new Object[] {
/*      */             
/*  762 */             Integer.valueOf(pOptions), correlId, hconn, hobj, pCC, pRC
/*      */           });
/*      */     }
/*      */     
/*  766 */     MQMD msgDesc = this.env.newMQMD();
/*  767 */     MQGMO getMsgOpts = this.env.newMQGMO();
/*  768 */     Pint dataLength = this.env.newPint();
/*      */     
/*  770 */     getMsgOpts.setVersion(2);
/*  771 */     int options = pOptions | 0x4000;
/*  772 */     getMsgOpts.setOptions(options);
/*  773 */     msgDesc.setCodedCharSetId(1208);
/*  774 */     msgDesc.setEncoding(273);
/*      */     
/*  776 */     getMsgOpts.setMatchOptions(2);
/*  777 */     msgDesc.setCorrelId(correlId);
/*      */     
/*  779 */     ByteBuffer buff = this.mqService.getMessage(hconn, hobj, msgDesc, getMsgOpts, -1, 2147483647, null, dataLength, pCC, pRC);
/*      */     
/*  781 */     if (pCC.x == 0) {
/*  782 */       SecurityPolicy policyFromPcf = policyFromPcf(buff, pCC, pRC, hconn);
/*  783 */       if (Trace.isOn) {
/*  784 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "loadPolicyMessage(int,byte [ ],Hconn,Hobj,Pint,Pint)", policyFromPcf, 1);
/*      */       }
/*      */ 
/*      */       
/*  788 */       return policyFromPcf;
/*      */     } 
/*      */     
/*  791 */     if (Trace.isOn) {
/*  792 */       Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "loadPolicyMessage(int,byte [ ],Hconn,Hobj,Pint,Pint)", null, 2);
/*      */     }
/*      */ 
/*      */     
/*  796 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SecurityPolicy policyFromPcf(ByteBuffer buff, Pint pCC, Pint pRC, Hconn hconn) throws EseMQException {
/*  815 */     if (Trace.isOn) {
/*  816 */       Trace.entry(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "policyFromPcf(ByteBuffer,Pint,Pint,Hconn)", new Object[] { buff, pCC, pRC, hconn });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  831 */     int hconnCcsid = 0;
/*      */     try {
/*  833 */       hconnCcsid = hconn.getCcsid();
/*      */     }
/*  835 */     catch (JmqiException jmqie) {
/*  836 */       if (Trace.isOn) {
/*  837 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "policyFromPcf(ByteBuffer, Pint, Pint, Hconn)", (Throwable)jmqie);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  846 */     byte[] data = buff.array();
/*  847 */     ByteArrayInputStream bais = new ByteArrayInputStream(data);
/*  848 */     DataInput dataInput = new DataInputStream(bais);
/*      */ 
/*      */     
/*      */     try {
/*  852 */       PCFMessage response = new PCFMessage(dataInput);
/*  853 */       SecurityPolicy policy = new SecurityPolicy();
/*      */       
/*  855 */       Enumeration<PCFParameter> parameters = response.getParameters();
/*  856 */       while (parameters.hasMoreElements()) {
/*  857 */         PCFParameter param = parameters.nextElement();
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  862 */         param.store().setHconnCharacterSetID(hconnCcsid);
/*      */         
/*  864 */         if (param.getType() == 3 && param.getParameter() == 5) {
/*  865 */           MQCFIN version = (MQCFIN)param;
/*  866 */           policy.setVersion(version.getIntValue()); continue;
/*      */         } 
/*  868 */         if (param.getType() == 4 && param.getParameter() == 6) {
/*  869 */           MQCFST policyName = (MQCFST)param;
/*  870 */           policy.setName(trimmedOrEmpty(policyName.getStringValue())); continue;
/*      */         } 
/*  872 */         if (param.getType() == 3 && param.getParameter() == 7) {
/*  873 */           MQCFIN signAlg = (MQCFIN)param;
/*  874 */           policy.setSignAlg(signAlgToString(signAlg.getIntValue())); continue;
/*      */         } 
/*  876 */         if (param.getType() == 3 && param.getParameter() == 8) {
/*  877 */           MQCFIN encAlg = (MQCFIN)param;
/*      */ 
/*      */           
/*  880 */           int intValue = encAlg.getIntValue();
/*  881 */           int encAlgInt = intValue & 0xFF;
/*  882 */           int reuse = intValue >> 8;
/*  883 */           policy.setEncAlg(encAlgToString(encAlgInt));
/*  884 */           policy.setReuse(reuse); continue;
/*      */         } 
/*  886 */         if (param.getType() == 3 && param.getParameter() == 9) {
/*  887 */           MQCFIN audit = (MQCFIN)param;
/*  888 */           policy.setAudit(audit.getIntValue()); continue;
/*      */         } 
/*  890 */         if (param.getType() == 3 && param.getParameter() == 10) {
/*      */           
/*  892 */           MQCFIN mQCFIN = (MQCFIN)param; continue;
/*      */         } 
/*  894 */         if (param.getType() == 4 && param.getParameter() == 11) {
/*  895 */           MQCFST dn = (MQCFST)param;
/*  896 */           policy.getSignerDNs().add(
/*  897 */               trimmedOrEmpty(dn.getStringValue())); continue;
/*      */         } 
/*  899 */         if (param.getType() == 3 && param.getParameter() == 12) {
/*      */           
/*  901 */           MQCFIN mQCFIN = (MQCFIN)param; continue;
/*      */         } 
/*  903 */         if (param.getType() == 4 && param.getParameter() == 13) {
/*  904 */           MQCFST dn = (MQCFST)param;
/*  905 */           policy.getRecipientDNs().add(
/*  906 */               trimmedOrEmpty(dn.getStringValue())); continue;
/*      */         } 
/*  908 */         if (param.getType() == 3 && param.getParameter() == 14) {
/*  909 */           MQCFIN tFlag = (MQCFIN)param;
/*  910 */           policy.setTolerateFlag(tFlag.getIntValue());
/*      */         } 
/*      */       } 
/*      */       
/*  914 */       policy.setRecipientsCertificates(new java.security.cert.X509Certificate[0]);
/*  915 */       validatePolicy(policy, pCC, pRC);
/*  916 */       if (Trace.isOn) {
/*  917 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "policyFromPcf(ByteBuffer,Pint,Pint,Hconn)", policy);
/*      */       }
/*      */       
/*  920 */       return policy;
/*      */     }
/*  922 */     catch (MQDataException e) {
/*  923 */       if (Trace.isOn) {
/*  924 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "policyFromPcf(ByteBuffer,Pint,Pint,Hconn)", (Throwable)e, 1);
/*      */       }
/*      */ 
/*      */       
/*  928 */       EseMQException traceRet1 = failedToObtainPolicyException(e
/*  929 */           .getReason(), (Throwable)e);
/*  930 */       if (Trace.isOn) {
/*  931 */         Trace.throwing(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "policyFromPcf(ByteBuffer,Pint,Pint,Hconn)", (Throwable)traceRet1, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  936 */       throw traceRet1;
/*      */     }
/*  938 */     catch (IOException e) {
/*  939 */       if (Trace.isOn) {
/*  940 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "policyFromPcf(ByteBuffer,Pint,Pint,Hconn)", e, 2);
/*      */       }
/*      */ 
/*      */       
/*  944 */       HashMap<String, Object> inserts = new HashMap<>();
/*  945 */       inserts.put("AMS_INSERT_MQ_REASON_CODE", 
/*  946 */           Integer.valueOf(2195).toString());
/*  947 */       EseMQException ex = new EseMQException(AmsErrorMessages.mjs_msg_error_failed_to_obtain_policy, inserts, e);
/*      */ 
/*      */       
/*  950 */       ex.setReason(2195);
/*  951 */       if (Trace.isOn) {
/*  952 */         Trace.throwing(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "policyFromPcf(ByteBuffer,Pint,Pint,Hconn)", (Throwable)ex, 2);
/*      */       }
/*      */ 
/*      */       
/*  956 */       throw ex;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String trimmedOrEmpty(String stringValue) {
/*  967 */     if (Trace.isOn) {
/*  968 */       Trace.entry(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "trimmedOrEmpty(String)", new Object[] { stringValue });
/*      */     }
/*      */     
/*  971 */     String traceRet1 = (stringValue == null) ? "" : stringValue.trim();
/*  972 */     if (Trace.isOn) {
/*  973 */       Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "trimmedOrEmpty(String)", traceRet1);
/*      */     }
/*      */     
/*  976 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void validatePolicy(SecurityPolicy policy, Pint pCC, Pint pRC) {
/*  987 */     if (Trace.isOn) {
/*  988 */       Trace.entry(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "validatePolicy(SecurityPolicy,Pint,Pint)", new Object[] { policy, pCC, pRC });
/*      */     }
/*      */ 
/*      */     
/*  992 */     if (policy.getVersion() != 1) {
/*  993 */       pCC.x = 2;
/*  994 */       pRC.x = 3014;
/*      */     } 
/*  996 */     if (Trace.isOn) {
/*  997 */       Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "validatePolicy(SecurityPolicy,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String encAlgToString(int intValue) {
/* 1011 */     if (Trace.isOn)
/* 1012 */       Trace.entry(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "encAlgToString(int)", new Object[] {
/*      */             
/* 1014 */             Integer.valueOf(intValue)
/*      */           }); 
/* 1016 */     if (intValue == 1) {
/* 1017 */       if (Trace.isOn) {
/* 1018 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "encAlgToString(int)", "RC2", 1);
/*      */       }
/*      */ 
/*      */       
/* 1022 */       return "RC2";
/*      */     } 
/* 1024 */     if (intValue == 2) {
/* 1025 */       if (Trace.isOn) {
/* 1026 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "encAlgToString(int)", "DES", 2);
/*      */       }
/*      */ 
/*      */       
/* 1030 */       return "DES";
/*      */     } 
/* 1032 */     if (intValue == 3) {
/* 1033 */       if (Trace.isOn) {
/* 1034 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "encAlgToString(int)", "DESede", 3);
/*      */       }
/*      */ 
/*      */       
/* 1038 */       return "DESede";
/*      */     } 
/* 1040 */     if (intValue == 4) {
/* 1041 */       if (Trace.isOn) {
/* 1042 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "encAlgToString(int)", "AES128", 4);
/*      */       }
/*      */ 
/*      */       
/* 1046 */       return "AES128";
/*      */     } 
/* 1048 */     if (intValue == 5) {
/* 1049 */       if (Trace.isOn) {
/* 1050 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "encAlgToString(int)", "AES256", 5);
/*      */       }
/*      */ 
/*      */       
/* 1054 */       return "AES256";
/*      */     } 
/* 1056 */     if (Trace.isOn) {
/* 1057 */       Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "encAlgToString(int)", "", 6);
/*      */     }
/*      */     
/* 1060 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String signAlgToString(int intValue) {
/* 1071 */     if (Trace.isOn) {
/* 1072 */       Trace.entry(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "signAlgToString(int)", new Object[] {
/*      */             
/* 1074 */             Integer.valueOf(intValue)
/*      */           });
/*      */     }
/* 1077 */     if (intValue == 1) {
/* 1078 */       if (Trace.isOn) {
/* 1079 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "signAlgToString(int)", "MD5withRSA", 1);
/*      */       }
/*      */ 
/*      */       
/* 1083 */       return "MD5withRSA";
/*      */     } 
/*      */     
/* 1086 */     if (intValue == 2) {
/* 1087 */       if (Trace.isOn) {
/* 1088 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "signAlgToString(int)", "SHA1withRSA", 2);
/*      */       }
/*      */ 
/*      */       
/* 1092 */       return "SHA1withRSA";
/*      */     } 
/*      */     
/* 1095 */     if (intValue == 3) {
/* 1096 */       if (Trace.isOn) {
/* 1097 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "signAlgToString(int)", "SHA224withRSA", 3);
/*      */       }
/*      */ 
/*      */       
/* 1101 */       return "SHA224withRSA";
/*      */     } 
/*      */     
/* 1104 */     if (intValue == 4) {
/* 1105 */       if (Trace.isOn) {
/* 1106 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "signAlgToString(int)", "SHA256withRSA", 4);
/*      */       }
/*      */ 
/*      */       
/* 1110 */       return "SHA256withRSA";
/*      */     } 
/*      */     
/* 1113 */     if (intValue == 5) {
/* 1114 */       if (Trace.isOn) {
/* 1115 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "signAlgToString(int)", "SHA384withRSA", 5);
/*      */       }
/*      */ 
/*      */       
/* 1119 */       return "SHA384withRSA";
/*      */     } 
/*      */     
/* 1122 */     if (intValue == 6) {
/* 1123 */       if (Trace.isOn) {
/* 1124 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "signAlgToString(int)", "SHA512withRSA", 6);
/*      */       }
/*      */ 
/*      */       
/* 1128 */       return "SHA512withRSA";
/*      */     } 
/* 1130 */     if (Trace.isOn) {
/* 1131 */       Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "signAlgToString(int)", "", 7);
/*      */     }
/*      */     
/* 1134 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private PolicyMapping findMapping(String name, IndexMessage indexMessage) {
/* 1146 */     if (Trace.isOn) {
/* 1147 */       Trace.entry(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "findMapping(String,IndexMessage)", new Object[] { name, indexMessage });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1152 */     String trimmedName = name.trim();
/* 1153 */     for (PolicyMapping mapping : indexMessage.mappings) {
/* 1154 */       String trimmedMappingName = mapping.policyName.trim();
/* 1155 */       if (trimmedName.equals(trimmedMappingName)) {
/* 1156 */         if (Trace.isOn) {
/* 1157 */           Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "findMapping(String,IndexMessage)", mapping, 1);
/*      */         }
/*      */ 
/*      */         
/* 1161 */         return mapping;
/*      */       } 
/*      */     } 
/*      */     
/* 1165 */     if (Trace.isOn) {
/* 1166 */       Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "findMapping(String,IndexMessage)", null, 2);
/*      */     }
/*      */     
/* 1169 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private IndexMessage loadIndexMessage(int pOptions, Hconn hconn, Hobj hobj, Pint pCC, Pint pRC) throws EseMQException {
/* 1186 */     if (Trace.isOn) {
/* 1187 */       Trace.entry(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "loadIndexMessage(int,Hconn,Hobj,Pint,Pint)", new Object[] {
/*      */             
/* 1189 */             Integer.valueOf(pOptions), hconn, hobj, pCC, pRC
/*      */           });
/*      */     }
/* 1192 */     MQMD md = this.env.newMQMD();
/* 1193 */     MQGMO gmo = this.env.newMQGMO();
/* 1194 */     ByteBuffer initialBuffer = null;
/* 1195 */     Pint pDataLength = this.env.newPint();
/*      */     
/* 1197 */     gmo.setVersion(2);
/* 1198 */     int options = pOptions | 0x4000;
/* 1199 */     gmo.setOptions(options);
/* 1200 */     gmo.setMatchOptions(2);
/*      */     
/* 1202 */     md.setCorrelId(MQESE_POLINDEX_CORREL_ID_ARRAY);
/* 1203 */     md.setCodedCharSetId(1208);
/* 1204 */     md.setEncoding(273);
/*      */     
/* 1206 */     ByteBuffer buff = this.mqService.getMessage(hconn, hobj, md, gmo, -1, 2147483647, initialBuffer, pDataLength, pCC, pRC);
/*      */     
/* 1208 */     if (pCC.x == 0) {
/* 1209 */       IndexMessage indexFromPcf = indexFromPcf(buff, pCC, pRC);
/* 1210 */       if (Trace.isOn) {
/* 1211 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "loadIndexMessage(int,Hconn,Hobj,Pint,Pint)", indexFromPcf, 1);
/*      */       }
/*      */ 
/*      */       
/* 1215 */       return indexFromPcf;
/*      */     } 
/*      */     
/* 1218 */     if (Trace.isOn) {
/* 1219 */       Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "loadIndexMessage(int,Hconn,Hobj,Pint,Pint)", null, 2);
/*      */     }
/*      */     
/* 1222 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private IndexMessage indexFromPcf(ByteBuffer buff, Pint pCC, Pint pRC) throws EseMQException {
/* 1236 */     if (Trace.isOn) {
/* 1237 */       Trace.entry(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "indexFromPcf(ByteBuffer,Pint,Pint)", new Object[] { buff, pCC, pRC });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1242 */     byte[] data = buff.array();
/* 1243 */     ByteArrayInputStream bais = new ByteArrayInputStream(data);
/* 1244 */     DataInput dataInput = new DataInputStream(bais);
/*      */ 
/*      */     
/*      */     try {
/* 1248 */       PCFMessage response = new PCFMessage(dataInput);
/* 1249 */       IndexMessage indexMessage = new IndexMessage();
/*      */       
/* 1251 */       Enumeration<PCFParameter> parameters = response.getParameters();
/* 1252 */       while (parameters.hasMoreElements()) {
/* 1253 */         PCFParameter param = parameters.nextElement();
/* 1254 */         if (param.getType() == 3) {
/* 1255 */           if (param.getParameter() == 3) {
/* 1256 */             MQCFIN mqcfin = (MQCFIN)param;
/* 1257 */             indexMessage.version = mqcfin.getIntValue();
/*      */           }  continue;
/*      */         } 
/* 1260 */         if (param.getType() == 4) {
/* 1261 */           if (param.getParameter() == 4) {
/* 1262 */             MQCFST mqcfst = (MQCFST)param;
/* 1263 */             indexMessage.errorQName = trimmedOrEmpty(mqcfst
/* 1264 */                 .getStringValue());
/* 1265 */             if (indexMessage.errorQName.length() == 0) {
/* 1266 */               indexMessage.errorQName = "SYSTEM.PROTECTION.ERROR.QUEUE";
/*      */             }
/*      */           } 
/*      */           continue;
/*      */         } 
/* 1271 */         if (param.getType() == 6 && 
/* 1272 */           param.getParameter() == 2) {
/* 1273 */           MQCFSL mqcfsl = (MQCFSL)param;
/* 1274 */           String[] strings = mqcfsl.getStrings();
/* 1275 */           for (int i = 0; i < strings.length; i++) {
/* 1276 */             String string = strings[i];
/* 1277 */             if (string.length() != 96) {
/* 1278 */               pCC.x = 2;
/* 1279 */               pRC.x = 3024;
/* 1280 */               if (Trace.isOn) {
/* 1281 */                 Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "indexFromPcf(ByteBuffer,Pint,Pint)", null, 1);
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1287 */               return null;
/*      */             } 
/* 1289 */             PolicyMapping mapping = new PolicyMapping();
/* 1290 */             mapping.policyName = string.substring(0, 48);
/*      */             
/* 1292 */             mapping
/* 1293 */               .correlId = string.substring(48);
/* 1294 */             indexMessage.mappings.add(mapping);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1299 */       if (!validateIndex(indexMessage, pCC, pRC) && 
/* 1300 */         Trace.isOn) {
/* 1301 */         Trace.traceInfo(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "indexFromPcf(ByteBuffer, Pint, Pint)", "Failed to validate policy index", "");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1307 */       if (Trace.isOn) {
/* 1308 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "indexFromPcf(ByteBuffer,Pint,Pint)", indexMessage, 2);
/*      */       }
/*      */       
/* 1311 */       return indexMessage;
/*      */     }
/* 1313 */     catch (MQDataException e) {
/* 1314 */       if (Trace.isOn) {
/* 1315 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "indexFromPcf(ByteBuffer,Pint,Pint)", (Throwable)e, 1);
/*      */       }
/*      */ 
/*      */       
/* 1319 */       EseMQException traceRet1 = failedToObtainPolicyException(e
/* 1320 */           .getReason(), (Throwable)e);
/* 1321 */       if (Trace.isOn) {
/* 1322 */         Trace.throwing(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "indexFromPcf(ByteBuffer,Pint,Pint)", (Throwable)traceRet1, 1);
/*      */       }
/*      */ 
/*      */       
/* 1326 */       throw traceRet1;
/*      */     }
/* 1328 */     catch (IOException e) {
/* 1329 */       if (Trace.isOn) {
/* 1330 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "indexFromPcf(ByteBuffer,Pint,Pint)", e, 2);
/*      */       }
/*      */ 
/*      */       
/* 1334 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1335 */       inserts.put("AMS_INSERT_MQ_REASON_CODE", 
/* 1336 */           Integer.valueOf(2195).toString());
/* 1337 */       EseMQException ex = new EseMQException(AmsErrorMessages.mjs_msg_error_failed_to_obtain_policy, inserts, e);
/*      */ 
/*      */       
/* 1340 */       ex.setReason(2195);
/* 1341 */       if (Trace.isOn) {
/* 1342 */         Trace.throwing(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "indexFromPcf(ByteBuffer,Pint,Pint)", (Throwable)ex, 2);
/*      */       }
/*      */ 
/*      */       
/* 1346 */       throw ex;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean validateIndex(IndexMessage index, Pint pCC, Pint pRC) {
/* 1359 */     if (Trace.isOn) {
/* 1360 */       Trace.entry(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "validateIndex(IndexMessage,Pint,Pint)", new Object[] { index, pCC, pRC });
/*      */     }
/*      */ 
/*      */     
/* 1364 */     if (index.version != 1) {
/* 1365 */       pCC.x = 2;
/* 1366 */       pRC.x = 3014;
/* 1367 */       if (Trace.isOn) {
/* 1368 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "validateIndex(IndexMessage,Pint,Pint)", 
/*      */             
/* 1370 */             Boolean.valueOf(false), 1);
/*      */       }
/* 1372 */       return false;
/*      */     } 
/* 1374 */     if (index.mappings == null) {
/* 1375 */       pCC.x = 2;
/* 1376 */       pRC.x = 3008;
/* 1377 */       if (Trace.isOn) {
/* 1378 */         Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "validateIndex(IndexMessage,Pint,Pint)", 
/*      */             
/* 1380 */             Boolean.valueOf(false), 2);
/*      */       }
/* 1382 */       return false;
/*      */     } 
/*      */     
/* 1385 */     for (PolicyMapping pm : index.mappings) {
/* 1386 */       if (pm == null || (pm != null && (pm.policyName == null || pm.correlId == null))) {
/* 1387 */         pCC.x = 2;
/* 1388 */         pRC.x = 3008;
/* 1389 */         if (Trace.isOn) {
/* 1390 */           Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "validateIndex(IndexMessage,Pint,Pint)", 
/*      */ 
/*      */               
/* 1393 */               Boolean.valueOf(false), 3);
/*      */         }
/* 1395 */         return false;
/*      */       } 
/*      */     } 
/* 1398 */     if (Trace.isOn) {
/* 1399 */       Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "validateIndex(IndexMessage,Pint,Pint)", 
/*      */           
/* 1401 */           Boolean.valueOf(true), 4);
/*      */     }
/* 1403 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SecurityPolicy createNonePolicy(String name) {
/* 1413 */     if (Trace.isOn) {
/* 1414 */       Trace.entry(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "createNonePolicy(String)", new Object[] { name });
/*      */     }
/*      */     
/* 1417 */     SecurityPolicy policy = new SecurityPolicy(name);
/* 1418 */     policy.setEncAlg("");
/* 1419 */     policy.setSignAlg("");
/* 1420 */     policy.setErrorQName("SYSTEM.PROTECTION.ERROR.QUEUE");
/* 1421 */     policy.setSignerDNs(Collections.emptyList());
/* 1422 */     policy.setRecipientsCertificates(new java.security.cert.X509Certificate[0]);
/* 1423 */     policy.setVersion(1);
/*      */     
/* 1425 */     if (Trace.isOn) {
/* 1426 */       Trace.traceInfo(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "createNonePolicy(String)", "createNonePolicy", policy);
/*      */     }
/*      */     
/* 1429 */     if (Trace.isOn) {
/* 1430 */       Trace.exit(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "createNonePolicy(String)", policy);
/*      */     }
/*      */     
/* 1433 */     return policy;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDefaultErrorQueueName() {
/* 1443 */     if (Trace.isOn) {
/* 1444 */       Trace.data(this, "com.ibm.mq.ese.service.PolicyServiceImpl", "getDefaultErrorQueueName()", "getter", "SYSTEM.PROTECTION.ERROR.QUEUE");
/*      */     }
/*      */ 
/*      */     
/* 1448 */     return "SYSTEM.PROTECTION.ERROR.QUEUE";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class IndexMessage
/*      */   {
/*      */     int version;
/*      */ 
/*      */ 
/*      */     
/*      */     private IndexMessage() {}
/*      */ 
/*      */ 
/*      */     
/* 1464 */     List<PolicyServiceImpl.PolicyMapping> mappings = new LinkedList<>();
/*      */     String errorQName;
/*      */   }
/*      */   
/*      */   private static class PolicyMapping {
/*      */     String policyName;
/*      */     String correlId;
/*      */     
/*      */     private PolicyMapping() {}
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\service\PolicyServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */