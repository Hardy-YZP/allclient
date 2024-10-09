/*     */ package com.ibm.mq.ese.intercept;
/*     */ 
/*     */ import com.ibm.mq.ese.config.ConfigException;
/*     */ import com.ibm.mq.ese.core.AMBIException;
/*     */ import com.ibm.mq.ese.core.EseUser;
/*     */ import com.ibm.mq.ese.core.SecurityPolicy;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.mq.ese.pki.CertAccessException;
/*     */ import com.ibm.mq.ese.pki.MissingCertificateException;
/*     */ import com.ibm.mq.ese.service.EseMQException;
/*     */ import com.ibm.mq.ese.service.UserMapException;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.MQOD;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
/*     */ import com.ibm.mq.jmqi.handles.Phobj;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.system.SpiOpenOptions;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmqiOpenInterceptorImpl
/*     */   extends AbstractJmqiInterceptor
/*     */   implements JmqiOpenInterceptor
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiOpenInterceptorImpl.java";
/*     */   
/*     */   static {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.data("com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiOpenInterceptorImpl.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   private static final String CLASS = JmqiOpenInterceptorImpl.class.getName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SmqiObject beforeMQOPEN(Hconn hconn, MQOD objDesc, int Options, Phobj phobj, Pint cc, Pint rc) {
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "beforeMQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", new Object[] { hconn, objDesc, 
/*     */             
/*  86 */             Integer.valueOf(Options), phobj, cc, rc });
/*     */     }
/*     */     
/*  89 */     SmqiObject eseObject = beforeOpen(hconn, objDesc, Options, phobj, cc, rc);
/*     */     
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "beforeMQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", eseObject);
/*     */     }
/*     */     
/*  95 */     return eseObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SmqiObject beforeSpiOpen(Hconn hconn, MQOD od, SpiOpenOptions options, Phobj phobj, Pint cc, Pint rc) {
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "beforeSpiOpen(Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)", new Object[] { hconn, od, options, phobj, cc, rc });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 112 */     SmqiObject eseObject = beforeOpen(hconn, od, options.getOptions(), phobj, cc, rc);
/*     */     
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "beforeSpiOpen(Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)", eseObject);
/*     */     }
/*     */     
/* 118 */     return eseObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void afterMQOPEN(SmqiObject eseObject, Hconn hconn, MQOD od, int options, Phobj hobj, Pint compCode, Pint reason) {
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterMQOPEN(SmqiObject,Hconn,MQOD,int,Phobj,Pint,Pint)", new Object[] { eseObject, hconn, od, 
/*     */             
/* 134 */             Integer.valueOf(options), hobj, compCode, reason });
/*     */     }
/*     */     
/*     */     try {
/* 138 */       readPolicyIfAvailable(hobj, eseObject, hconn);
/*     */     }
/* 140 */     catch (EseMQException e) {
/* 141 */       if (Trace.isOn) {
/* 142 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterMQOPEN(SmqiObject,Hconn,MQOD,int,Phobj,Pint,Pint)", (Throwable)e);
/*     */       }
/*     */ 
/*     */       
/* 146 */       processExceptionAfterOpen(hconn, hobj, "afterMQOPEN(SmqiObject, Hconn, MQOD, int, Phobj, Pint, Pint)", (Exception)e, e
/*     */ 
/*     */ 
/*     */           
/* 150 */           .getReason(), compCode, reason);
/*     */ 
/*     */       
/* 153 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterMQOPEN(SmqiObject, Hconn, MQOD, int, Phobj, Pint, Pint)", new Object[] { eseObject, hconn, od, 
/*     */ 
/*     */             
/* 156 */             Integer.valueOf(options), hobj, compCode, reason });
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterMQOPEN(SmqiObject,Hconn,MQOD,int,Phobj,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 164 */     afterOpen(eseObject, hconn, od, options, hobj, compCode, reason);
/*     */     
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterMQOPEN(SmqiObject,Hconn,MQOD,int,Phobj,Pint,Pint)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void afterSpiOpen(SmqiObject eseObject, Hconn hconn, MQOD od, SpiOpenOptions options, Phobj hobj, Pint compCode, Pint reason) {
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterSpiOpen(SmqiObject,Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)", new Object[] { eseObject, hconn, od, options, hobj, compCode, reason });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 188 */       readPolicyIfAvailable(hobj, eseObject, hconn);
/*     */     }
/* 190 */     catch (EseMQException e) {
/* 191 */       if (Trace.isOn) {
/* 192 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterSpiOpen(SmqiObject,Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)", (Throwable)e);
/*     */       }
/*     */ 
/*     */       
/* 196 */       processExceptionAfterOpen(hconn, hobj, "afterSpiOpen(SmqiObject, Hconn, MQOD, SpiOpenOptions, Phobj, Pint, Pint)", (Exception)e, e
/*     */ 
/*     */ 
/*     */           
/* 200 */           .getReason(), compCode, reason);
/*     */ 
/*     */       
/* 203 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterMQOPEN(SmqiObject, Hconn, MQOD, SpiOpenOptions, Phobj, Pint, Pint)", new Object[] { eseObject, hconn, od, options, hobj, compCode, reason });
/*     */ 
/*     */ 
/*     */       
/* 207 */       if (Trace.isOn) {
/* 208 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterSpiOpen(SmqiObject,Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 214 */     afterOpen(eseObject, hconn, od, options.getOptions(), hobj, compCode, reason);
/*     */     
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterSpiOpen(SmqiObject,Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SmqiObject beforeOpen(Hconn hconn, MQOD objDesc, int options, Phobj phobj, Pint cc, Pint rc) {
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "beforeOpen(final Hconn,final MQOD,final int,Phobj,Pint,Pint)", new Object[] { hconn, objDesc, 
/*     */             
/* 240 */             Integer.valueOf(options), phobj, cc, rc });
/*     */     }
/*     */     
/* 243 */     String meth = "beforeOpen";
/* 244 */     if (cc == null || rc == null) {
/* 245 */       if (Trace.isOn) {
/* 246 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "beforeOpen(final Hconn, final MQOD, final int, Phobj, Pint, Pint)", "CompCode and Reason must not be null", "");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 252 */       IllegalArgumentException traceRet1 = new IllegalArgumentException();
/* 253 */       if (Trace.isOn) {
/* 254 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "beforeOpen(final Hconn,final MQOD,final int,Phobj,Pint,Pint)", traceRet1);
/*     */       }
/*     */       
/* 257 */       throw traceRet1;
/*     */     } 
/* 259 */     if (!validateInput(false, hconn, objDesc, options, phobj, cc, rc)) {
/* 260 */       if (Trace.isOn) {
/* 261 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "beforeOpen(final Hconn,final MQOD,final int,Phobj,Pint,Pint)", null, 1);
/*     */       }
/*     */       
/* 264 */       return null;
/*     */     } 
/*     */     
/* 267 */     ConnectionContext context = null;
/*     */     try {
/* 269 */       context = getContext(hconn);
/* 270 */       TemporaryQueueInfo tempQ = this.contextContainer.getTemporaryQueueInfo(objDesc.getObjectName(), context.getQmgrName());
/* 271 */       if (tempQ != null && tempQ.getModelQinfo() != null) {
/* 272 */         SmqiObject traceRet2 = tempQ.getModelQinfo();
/* 273 */         if (Trace.isOn) {
/* 274 */           Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "beforeOpen(final Hconn,final MQOD,final int,Phobj,Pint,Pint)", traceRet2, 2);
/*     */         }
/*     */         
/* 277 */         return traceRet2;
/*     */       }
/*     */     
/* 280 */     } catch (EseMQException e) {
/* 281 */       if (Trace.isOn) {
/* 282 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "beforeOpen(final Hconn,final MQOD,final int,Phobj,Pint,Pint)", (Throwable)e);
/*     */       }
/*     */       
/* 285 */       HashMap<String, Exception> data = new HashMap<>();
/* 286 */       data.put("exception", e);
/* 287 */       Trace.ffst(CLASS, meth, "MP005003", data, null);
/* 288 */       AmsErrorMessages.logException(CLASS, meth, (Throwable)e);
/* 289 */       if (Trace.isOn) {
/* 290 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "beforeOpen(final Hconn, final MQOD, final int, Phobj, Pint, Pint)", (Throwable)e);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 295 */       setErrorReasonCode(cc, rc, e.getReason());
/* 296 */       if (Trace.isOn) {
/* 297 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "beforeOpen(final Hconn,final MQOD,final int,Phobj,Pint,Pint)", null, 3);
/*     */       }
/*     */       
/* 300 */       return null;
/*     */     } 
/* 302 */     SmqiObject eseObject = new SmqiObject(phobj.getHobj(), context);
/* 303 */     eseObject.setObjectName((objDesc.getObjectName() == null) ? null : objDesc.getObjectName().trim());
/* 304 */     eseObject.setMqObjectType(objDesc.getObjectType());
/* 305 */     eseObject.setOpenOpts(options);
/*     */     
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "beforeOpen(final Hconn,final MQOD,final int,Phobj,Pint,Pint)", eseObject, 4);
/*     */     }
/*     */     
/* 311 */     return eseObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readPolicyIfAvailable(Phobj phobj, SmqiObject eseObject, Hconn hconn) throws EseMQException {
/* 324 */     if (Trace.isOn) {
/* 325 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "readPolicyIfAvailable(Phobj,SmqiObject, Hconn)", new Object[] { phobj, eseObject, hconn });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 330 */     String errorQueue = phobj.getHobj().getAMSErrorQueue();
/* 331 */     byte[] policyBytes = phobj.getHobj().getAMSPolicy();
/*     */     
/* 333 */     if (Trace.isOn) {
/* 334 */       if (policyBytes == null) {
/* 335 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "readPolicyIfAvailable(Phobj, SmqiObject, Hconn)", "Policy in hobj is null", "");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 341 */       if (errorQueue == null) {
/* 342 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "readPolicyIfAvailable(Phobj, SmqiObject, Hconn)", "errorQueue in hobj is null", "");
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 350 */     if (errorQueue != null && policyBytes != null) {
/* 351 */       storeSecurityInfoInSmqiObject(eseObject, policyBytes, errorQueue, hconn);
/*     */     }
/* 353 */     if (Trace.isOn) {
/* 354 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "readPolicyIfAvailable(Phobj,SmqiObject)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void storeSecurityInfoInSmqiObject(SmqiObject eseObject, byte[] policyBytes, String errorQueue, Hconn hconn) throws EseMQException {
/* 372 */     if (Trace.isOn) {
/* 373 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "storeSecurityInfoInSmqiObject(SmqiObject,byte [ ],String, Hconn)", new Object[] { eseObject, policyBytes, errorQueue, hconn });
/*     */     }
/*     */ 
/*     */     
/* 377 */     if (policyBytes == null) {
/* 378 */       if (Trace.isOn) {
/* 379 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "storeSecurityInfoInSmqiObject(SmqiObject,byte [ ],String, Hconn)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "storeSecurityInfoInSmqiObject(SmqiObject,byte [ ],String, Hconn)", "Received policy from queue manager, buffer limit: ", 
/*     */ 
/*     */ 
/*     */           
/* 390 */           String.valueOf(policyBytes.length));
/*     */     }
/* 392 */     ByteBuffer buff = ByteBuffer.wrap(policyBytes);
/*     */     
/* 394 */     Pint pCC = this.env.newPint();
/* 395 */     Pint pRC = this.env.newPint();
/* 396 */     SecurityPolicy amsPolicy = this.policyService.policyFromPcf(buff, pCC, pRC, hconn);
/*     */     
/* 398 */     amsPolicy.setErrorQName(errorQueue);
/* 399 */     eseObject.setSecPolicy(amsPolicy);
/* 400 */     if (Trace.isOn) {
/* 401 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "storeSecurityInfoInSmqiObject(SmqiObject,byte [ ],String, Hconn)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean validateInput(boolean afterOpen, Hconn hconn, MQOD objDesc, int options, Phobj phobj, Pint pCompCode, Pint pReason) {
/* 430 */     if (Trace.isOn) {
/* 431 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "validateInput(final boolean,final Hconn,final MQOD,final int,Phobj,Pint,Pint)", new Object[] {
/*     */             
/* 433 */             Boolean.valueOf(afterOpen), hconn, objDesc, Integer.valueOf(options), phobj, pCompCode, pReason
/*     */           });
/*     */     }
/* 436 */     if (afterOpen && pCompCode.x == 2) {
/* 437 */       if (Trace.isOn) {
/* 438 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "validateInput(final Hconn, final MQOD, final int, Phobj, Pint, Pint)", "skipping because CompCode is MQCC_FAILED", "");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 444 */       Trace.ffst(CLASS, "validateInput", "MP005002", null, null);
/* 445 */       if (Trace.isOn) {
/* 446 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "validateInput(final boolean,final Hconn,final MQOD,final int,Phobj,Pint,Pint)", 
/*     */             
/* 448 */             Boolean.valueOf(false), 1);
/*     */       }
/* 450 */       return false;
/*     */     } 
/* 452 */     if (hconn == null) {
/* 453 */       setErrorReasonCode(pCompCode, pReason, 2018);
/* 454 */       if (Trace.isOn) {
/* 455 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "validateInput(final Hconn, final MQOD, final int, Phobj, Pint, Pint)", "Hconn must not be null", "");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 461 */       Trace.ffst(CLASS, "validateInput", "MP005007", null, null);
/* 462 */       if (Trace.isOn) {
/* 463 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "validateInput(final boolean,final Hconn,final MQOD,final int,Phobj,Pint,Pint)", 
/*     */             
/* 465 */             Boolean.valueOf(false), 2);
/*     */       }
/* 467 */       return false;
/*     */     } 
/* 469 */     if (objDesc == null) {
/* 470 */       setErrorReasonCode(pCompCode, pReason, 2044);
/* 471 */       if (Trace.isOn) {
/* 472 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "validateInput(final Hconn, final MQOD, final int, Phobj, Pint, Pint)", "MQOD must not be null", "");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 478 */       Trace.ffst(CLASS, "validateInput", "MP005008", null, null);
/* 479 */       if (Trace.isOn) {
/* 480 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "validateInput(final boolean,final Hconn,final MQOD,final int,Phobj,Pint,Pint)", 
/*     */             
/* 482 */             Boolean.valueOf(false), 3);
/*     */       }
/* 484 */       return false;
/*     */     } 
/* 486 */     if (phobj == null) {
/* 487 */       setErrorReasonCode(pCompCode, pReason, 2019);
/* 488 */       if (Trace.isOn) {
/* 489 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "validateInput(final Hconn, final MQOD, final int, Phobj, Pint, Pint)", "Phobj must not be null", "");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 495 */       Trace.ffst(CLASS, "validateInput", "MP005009", null, null);
/* 496 */       if (Trace.isOn) {
/* 497 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "validateInput(final boolean,final Hconn,final MQOD,final int,Phobj,Pint,Pint)", 
/*     */             
/* 499 */             Boolean.valueOf(false), 4);
/*     */       }
/* 501 */       return false;
/*     */     } 
/* 503 */     if (Trace.isOn) {
/* 504 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "validateInput(final boolean,final Hconn,final MQOD,final int,Phobj,Pint,Pint)", 
/*     */           
/* 506 */           Boolean.valueOf(true), 5);
/*     */     }
/* 508 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void afterOpen(SmqiObject eseObject, Hconn hconn, MQOD od, int options, Phobj phobj, Pint cc, Pint rc) {
/* 523 */     if (Trace.isOn) {
/* 524 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterOpen(SmqiObject,Hconn,MQOD,int,Phobj,Pint,Pint)", new Object[] { eseObject, hconn, od, 
/*     */             
/* 526 */             Integer.valueOf(options), phobj, cc, rc });
/*     */     }
/*     */     
/* 529 */     String meth = "afterOpen";
/* 530 */     if (cc == null || rc == null) {
/* 531 */       if (Trace.isOn) {
/* 532 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterOpen(SmqiObject, Hconn, MQOD, int, Phobj, Pint, Pint)", "CompCode and Reason must not be null", "");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 538 */       IllegalArgumentException traceRet1 = new IllegalArgumentException();
/* 539 */       if (Trace.isOn) {
/* 540 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterOpen(SmqiObject,Hconn,MQOD,int,Phobj,Pint,Pint)", traceRet1, 1);
/*     */       }
/*     */       
/* 543 */       throw traceRet1;
/*     */     } 
/*     */     
/* 546 */     if (cc.x == 2 && 
/* 547 */       Trace.isOn) {
/* 548 */       Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterOpen(SmqiObject, Hconn, MQOD, int, Phobj, Pint, Pint)", "Error detected in MQOPEN: reason ", rc);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 555 */     if (!validateInput(true, hconn, od, options, phobj, cc, rc)) {
/* 556 */       Trace.ffst(CLASS, meth, "MP005004", null, null);
/* 557 */       if (Trace.isOn) {
/* 558 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterOpen(SmqiObject,Hconn,MQOD,int,Phobj,Pint,Pint)", 1);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 563 */     if (eseObject == null) {
/* 564 */       Trace.ffst(CLASS, meth, "MP005005", null, null);
/* 565 */       IllegalArgumentException traceRet2 = new IllegalArgumentException("qinfo == null");
/* 566 */       if (Trace.isOn) {
/* 567 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterOpen(SmqiObject,Hconn,MQOD,int,Phobj,Pint,Pint)", traceRet2, 2);
/*     */       }
/*     */       
/* 570 */       throw traceRet2;
/*     */     } 
/*     */     
/* 573 */     ConnectionContext context = null;
/*     */     try {
/* 575 */       context = getContext(hconn);
/*     */     }
/* 577 */     catch (EseMQException e) {
/* 578 */       if (Trace.isOn) {
/* 579 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterOpen(SmqiObject,Hconn,MQOD,int,Phobj,Pint,Pint)", (Throwable)e, 1);
/*     */       }
/*     */       
/* 582 */       HashMap<String, Exception> data = new HashMap<>();
/* 583 */       data.put("exception", e);
/* 584 */       Trace.ffst(CLASS, meth, "MP005006", data, null);
/* 585 */       AmsErrorMessages.logException(CLASS, meth, (Throwable)e);
/* 586 */       if (Trace.isOn) {
/* 587 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterOpen(SmqiObject, Hconn, MQOD, int, Phobj, Pint, Pint)", (Throwable)e);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 592 */       setErrorReasonCode(cc, rc, e.getReason());
/* 593 */       if (Trace.isOn) {
/* 594 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterOpen(SmqiObject,Hconn,MQOD,int,Phobj,Pint,Pint)", 2);
/*     */       }
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 601 */     Hobj hobj = phobj.getHobj();
/* 602 */     if (eseObject.getHobj().getIntegerHandle() == 0) {
/* 603 */       eseObject.setHobj(hobj);
/*     */     }
/* 605 */     context.addSmqiObject(eseObject);
/*     */     try {
/* 607 */       if (isInterceptedMqObjectType(od) && isInterceptedMqOperation(options)) {
/* 608 */         determinePolicy(hconn, od, options, phobj.getHobj(), eseObject, cc, rc);
/*     */       } else {
/*     */         
/* 611 */         eseObject.setSecPolicy(this.policyService.createNonePolicy(""));
/*     */       }
/*     */     
/* 614 */     } catch (EseMQException e) {
/* 615 */       if (Trace.isOn) {
/* 616 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterOpen(SmqiObject,Hconn,MQOD,int,Phobj,Pint,Pint)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 619 */       processExceptionAfterOpen(hconn, phobj, meth, (Exception)e, 2063, cc, rc);
/*     */     }
/* 621 */     catch (MissingCertificateException e) {
/* 622 */       if (Trace.isOn) {
/* 623 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterOpen(SmqiObject,Hconn,MQOD,int,Phobj,Pint,Pint)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 626 */       processExceptionAfterOpen(hconn, phobj, meth, (Exception)e, 2063, cc, rc);
/*     */     }
/* 628 */     catch (AMBIException e) {
/* 629 */       if (Trace.isOn) {
/* 630 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterOpen(SmqiObject,Hconn,MQOD,int,Phobj,Pint,Pint)", (Throwable)e, 4);
/*     */       }
/*     */       
/* 633 */       if (e.getCause() instanceof java.security.GeneralSecurityException) {
/* 634 */         processExceptionAfterOpen(hconn, phobj, meth, (Exception)e, 2063, cc, rc);
/*     */       } else {
/*     */         
/* 637 */         processExceptionAfterOpen(hconn, phobj, meth, (Exception)e, 2035, cc, rc);
/*     */       }
/*     */     
/* 640 */     } catch (JmqiException e) {
/* 641 */       if (Trace.isOn) {
/* 642 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterOpen(SmqiObject,Hconn,MQOD,int,Phobj,Pint,Pint)", (Throwable)e, 5);
/*     */       }
/*     */       
/* 645 */       processExceptionAfterOpen(hconn, phobj, meth, (Exception)e, e.getReason(), cc, rc);
/*     */     }
/* 647 */     catch (Exception e) {
/* 648 */       if (Trace.isOn) {
/* 649 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterOpen(SmqiObject,Hconn,MQOD,int,Phobj,Pint,Pint)", e, 5);
/*     */       }
/*     */       
/* 652 */       processExceptionAfterOpen(hconn, phobj, meth, e, 2195, cc, rc);
/*     */     } 
/*     */     
/* 655 */     if (Trace.isOn) {
/* 656 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "afterOpen(SmqiObject,Hconn,MQOD,int,Phobj,Pint,Pint)", 3);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void determinePolicy(Hconn hconn, MQOD od, int options, Hobj hobj, SmqiObject eseObject, Pint cc, Pint rc) throws EseMQException, ConfigException, UserMapException, AMBIException, JmqiException {
/* 682 */     if (Trace.isOn) {
/* 683 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "determinePolicy(Hconn,MQOD,int,Hobj,SmqiObject,Pint,Pint)", new Object[] { hconn, od, 
/*     */             
/* 685 */             Integer.valueOf(options), hobj, eseObject, cc, rc });
/*     */     }
/* 687 */     String qmgrName = getContext(hconn).getQmgrName();
/*     */     
/* 689 */     if (eseObject.getResolvedName().length() != 0) {
/* 690 */       this.contextContainer.addTempQinfoChild(eseObject.getResolvedName(), qmgrName, hobj);
/* 691 */       if (Trace.isOn) {
/* 692 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "determinePolicy(Hconn,MQOD,int,Hobj,SmqiObject,Pint,Pint)", 1);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 698 */     eseObject.setResolvedName(od.getObjectName().trim());
/* 699 */     if (eseObject.isModelQueue()) {
/* 700 */       this.contextContainer.newTempQinfo(hconn, qmgrName, eseObject);
/*     */     }
/*     */     
/* 703 */     if (eseObject.getSecPolicy() == null) {
/* 704 */       SecurityPolicy policy = this.policyService.getPolicy(qmgrName, eseObject.getResolvedName(), hconn, cc, rc);
/* 705 */       if (policy == null) {
/* 706 */         if (Trace.isOn) {
/* 707 */           Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "determinePolicy(Hconn,MQOD,int,Hobj,SmqiObject,Pint,Pint)", 2);
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/* 712 */       eseObject.setSecPolicy(policy);
/* 713 */       if (Trace.isOn) {
/* 714 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "determinePolicy(Hconn, MQOD, int, Hobj, SmqiObject)", "found policy: ", eseObject
/*     */ 
/*     */ 
/*     */             
/* 718 */             .getSecPolicy());
/*     */       }
/*     */     } 
/* 721 */     initCrypto(eseObject, eseObject.getSecPolicy(), options);
/* 722 */     if (Trace.isOn) {
/* 723 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "determinePolicy(Hconn,MQOD,int,Hobj,SmqiObject,Pint,Pint)", 3);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initCrypto(SmqiObject eseObject, SecurityPolicy policy, int options) throws MissingCertificateException, ConfigException, UserMapException, AMBIException, JmqiException {
/* 735 */     if (Trace.isOn) {
/* 736 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "initCrypto(SmqiObject,SecurityPolicy,int)", new Object[] { eseObject, policy, 
/*     */             
/* 738 */             Integer.valueOf(options) });
/*     */     }
/* 740 */     int qop = policy.getQop();
/* 741 */     if (qop == 0) {
/* 742 */       if (Trace.isOn) {
/* 743 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "initCrypto(SmqiObject,SecurityPolicy,int)");
/*     */       }
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 750 */     if (this.env.getCaller() == 'N') {
/* 751 */       JmqiException ex = new JmqiException(this.env, -1, null, 2, 2298, null);
/* 752 */       if (Trace.isOn) {
/* 753 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "initCrypto(SmqiObject,SecurityPolicy,int)", (Throwable)ex, 0);
/*     */       }
/*     */       
/* 756 */       throw ex;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 762 */     if (this.cryptoService.initialise()) {
/* 763 */       this.contextContainer.setCryptoCapable(this.cryptoService.isValid());
/*     */     }
/*     */ 
/*     */     
/* 767 */     this.certAccess.initialise();
/*     */ 
/*     */     
/* 770 */     eseObject.getContext().isProtectedConn().set(true);
/* 771 */     if (!this.contextContainer.isCryptoCapable()) {
/* 772 */       ConfigException ex = new ConfigException(new UnsupportedOperationException());
/* 773 */       if (Trace.isOn) {
/* 774 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "initCrypto(SmqiObject,SecurityPolicy,int)", (Throwable)ex, 2);
/*     */       }
/*     */       
/* 777 */       throw ex;
/*     */     } 
/*     */     try {
/* 780 */       EseUser user = this.identityService.getCredentials();
/* 781 */       eseObject.setUser(user);
/* 782 */       if ((qop == 2 || qop == 3) && 
/* 783 */         isOptionSet(options, 16)) {
/* 784 */         X509Certificate[] certs = this.certAccess.loadCertificates(user.getKeyStoreAccess(), user.getPkiSpec(), policy.getRecipientDNs());
/* 785 */         policy.setRecipientsCertificates(certs);
/*     */       }
/*     */     
/* 788 */     } catch (MissingCertificateException e) {
/* 789 */       if (Trace.isOn) {
/* 790 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "initCrypto(SmqiObject,SecurityPolicy,int)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 793 */       MissingCertificateException ex = new MissingCertificateException(AmsErrorMessages.mju_policy_error_get_receiver_certs, (Throwable)e);
/* 794 */       if (Trace.isOn) {
/* 795 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "initCrypto(SmqiObject,SecurityPolicy,int)", (Throwable)ex, 4);
/*     */       }
/*     */       
/* 798 */       throw ex;
/*     */     }
/* 800 */     catch (CertAccessException e) {
/* 801 */       if (Trace.isOn) {
/* 802 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "initCrypto(SmqiObject,SecurityPolicy,int)", (Throwable)e, 5);
/*     */       }
/*     */       
/* 805 */       MissingCertificateException ex = new MissingCertificateException(AmsErrorMessages.mju_policy_error_get_receiver_certs, (Throwable)e);
/* 806 */       if (Trace.isOn) {
/* 807 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "initCrypto(SmqiObject,SecurityPolicy,int)", (Throwable)ex, 6);
/*     */       }
/*     */       
/* 810 */       throw ex;
/*     */     } 
/* 812 */     if (Trace.isOn) {
/* 813 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "initCrypto(SmqiObject,SecurityPolicy,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isInterceptedMqObjectType(MQOD od) {
/* 824 */     if (Trace.isOn) {
/* 825 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "isInterceptedMqObjectType(MQOD)", new Object[] { od });
/*     */     }
/*     */ 
/*     */     
/* 829 */     boolean ret = (od.getObjectType() == 1 || od.getObjectType() == 1002 || od.getObjectType() == 1004 || od.getObjectType() == 1003 || od.getObjectType() == 1005);
/* 830 */     if (!ret && Trace.isOn) {
/* 831 */       Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptImpl", "isInterceptedMqObjectType(MQOD)", "MQOT object not relevant ", 
/*     */ 
/*     */ 
/*     */           
/* 835 */           String.valueOf(od.getObjectType()));
/*     */     }
/* 837 */     if (Trace.isOn) {
/* 838 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "isInterceptedMqObjectType(MQOD)", 
/* 839 */           Boolean.valueOf(ret));
/*     */     }
/* 841 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isInterceptedMqOperation(int options) {
/* 852 */     if (Trace.isOn) {
/* 853 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "isInterceptedMqOperation(int)", new Object[] {
/* 854 */             Integer.valueOf(options)
/*     */           });
/*     */     }
/*     */     
/* 858 */     boolean ret = (isOptionSet(options, 8) || isOptionSet(options, 1) || isOptionSet(options, 4) || isOptionSet(options, 2) || isOptionSet(options, 16));
/* 859 */     if (!ret && Trace.isOn) {
/* 860 */       Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptImpl", "isInterceptedMqOperation(int)", "MQOO options not relevant ", 
/*     */ 
/*     */ 
/*     */           
/* 864 */           String.valueOf(options));
/*     */     }
/* 866 */     if (Trace.isOn) {
/* 867 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "isInterceptedMqOperation(int)", 
/* 868 */           Boolean.valueOf(ret));
/*     */     }
/* 870 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processExceptionAfterOpen(Hconn hconn, Phobj phobj, String meth, Exception e, int reason, Pint cc, Pint rc) {
/* 885 */     if (Trace.isOn) {
/* 886 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "processExceptionAfterOpen(Hconn,Phobj,String,Exception,int,Pint,Pint)", new Object[] { hconn, phobj, meth, e, 
/*     */             
/* 888 */             Integer.valueOf(reason), cc, rc });
/*     */     }
/* 890 */     HashMap<String, Object> data = new HashMap<>();
/*     */     
/*     */     try {
/* 893 */       String queueManagerName = hconn.getName();
/* 894 */       if (queueManagerName == null) {
/* 895 */         data.put("Queue manager name", "<null>");
/*     */       } else {
/* 897 */         data.put("Queue manager name", queueManagerName);
/*     */       } 
/* 899 */     } catch (JmqiException jmqiEx) {
/*     */ 
/*     */       
/* 902 */       if (Trace.isOn) {
/* 903 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "processExceptionAfterOpen()Hconn, Phobj, String, Exception, int, Pint, Pint", (Throwable)jmqiEx, 1);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 910 */       String queueManagerUid = hconn.getUid();
/* 911 */       if (queueManagerUid == null) {
/* 912 */         data.put("Queue manager Uid", "<null>");
/*     */       } else {
/* 914 */         data.put("Queue manager Uid", queueManagerUid);
/*     */       } 
/* 916 */     } catch (JmqiException jmqiEx1) {
/*     */ 
/*     */       
/* 919 */       if (Trace.isOn) {
/* 920 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "processExceptionAfterOpen()Hconn, Phobj, String, Exception, int, Pint, Pint", (Throwable)jmqiEx1, 2);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 926 */     data.put("exception", e);
/* 927 */     Trace.ffst(CLASS, "processExceptionAfterOpen", "MP005001", data, null);
/* 928 */     AmsErrorMessages.logException(CLASS, meth, e);
/* 929 */     if (Trace.isOn) {
/* 930 */       Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "processExceptionAfterOpen()Hconn, Phobj, String, Exception, int, Pint, Pint", e);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 935 */     this.jmqi.MQCLOSE(hconn, phobj, 0, cc, rc);
/* 936 */     if (Trace.isOn) {
/* 937 */       Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "processExceptionAfterOpen", "MQCLOSE called, CC: " + cc + " RC: " + rc, "");
/*     */     }
/*     */     
/* 940 */     setErrorReasonCode(cc, rc, reason);
/* 941 */     (getTls()).lastException = new JmqiException(this.env, 0, null, cc.x, rc.x, e);
/* 942 */     if (Trace.isOn)
/* 943 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiOpenInterceptorImpl", "processExceptionAfterOpen(Hconn,Phobj,String,Exception,int,Pint,Pint)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\JmqiOpenInterceptorImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */