/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.jms.NoBrokerResponseException;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQC;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*      */ import java.nio.charset.Charset;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.HashMap;
/*      */ import javax.jms.IllegalStateException;
/*      */ import javax.jms.JMSException;
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
/*      */ public class MQPubSubServices
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQPubSubServices.java";
/*      */   protected byte[] sessionName;
/*      */   private static final int DEFAULT_BROKER_TIMEOUT = 120000;
/*      */   private static final int MIN_CLEANUP_TIME = 59000;
/*      */   static final int REGISTER_PUBLISHER = 0;
/*      */   static final int DEREGISTER_PUBLISHER = 1;
/*      */   static final int REGISTER_SUBSCRIBER = 2;
/*      */   static final int DEREGISTER_SUBSCRIBER = 3;
/*      */   static final int REGISTER_SHAREDQ_SUBSCRIBER = 4;
/*      */   static final int DEREGISTER_SHAREDQ_SUBSCRIBER = 5;
/*      */   protected static final String REGPUB_CMD_PREFIX = "MQPSCommand RegPub MQPSTopic ";
/*      */   protected static final String DEREGPUB_CMD_PREFIX = "MQPSCommand DeregPub MQPSTopic ";
/*      */   protected static final String REGSUB_CMD_PREFIX = "MQPSCommand RegSub MQPSTopic ";
/*      */   protected static final String DEREGSUB_CMD_PREFIX = "MQPSCommand DeregSub MQPSTopic ";
/*      */   protected static final String REGSUB_CORREL_CMD_PREFIX = "MQPSCommand RegSub MQPSRegOpts CorrelAsId MQPSTopic ";
/*      */   protected static final String DEREGSUB_CORREL_CMD_PREFIX = "MQPSCommand DeregSub MQPSRegOpts CorrelAsId MQPSTopic ";
/*      */   protected static final String MQPS_STREAM_NAME_B = " MQPSStreamName ";
/*      */   protected static final String MQPS_Q_NAME_B = " MQPSQName ";
/*      */   protected static final String MQPS_REGISTRATION_OPTIONS_B = " MQPSRegOpts ";
/*      */   protected static final String MQPS_NON_PERSISTENT = "NonPers";
/*      */   protected static final String MQPS_DUPLICATES_OK = "DupsOK";
/*      */   
/*      */   static {
/*   67 */     if (Trace.isOn) {
/*   68 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQPubSubServices.java");
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
/*  112 */   protected BrokerConnectionInfo brk = new BrokerConnectionInfo(null, null, null);
/*      */ 
/*      */   
/*  115 */   protected MQQueue replyQ = null;
/*      */ 
/*      */   
/*      */   MQGetMessageOptions brokerReponseGmo;
/*      */ 
/*      */   
/*      */   protected int brkOptLevel;
/*      */   
/*  123 */   protected int brokerTimeout = 120000;
/*      */ 
/*      */   
/*  126 */   protected int brokerVersion = 0;
/*      */ 
/*      */   
/*      */   protected String nondur_subscribeQ;
/*      */ 
/*      */   
/*      */   protected String SUBSCRIBE_CMD_POSTFIX;
/*      */ 
/*      */ 
/*      */   
/*      */   MQPubSubServices() {
/*  137 */     if (Trace.isOn) {
/*  138 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "<init>()");
/*      */     }
/*      */ 
/*      */     
/*  142 */     if (Trace.isOn) {
/*  143 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "<init>()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   MQPubSubServices(MQConnection connection, boolean transacted, int acknowledgeMode, MQSession session) throws JMSException {
/*  149 */     if (Trace.isOn) {
/*  150 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "<init>(MQConnection,boolean,int,MQSession)", new Object[] { connection, 
/*      */             
/*  152 */             Boolean.valueOf(transacted), Integer.valueOf(acknowledgeMode), session });
/*      */     }
/*  154 */     initialisePubSub(connection, transacted, acknowledgeMode, session);
/*  155 */     if (Trace.isOn) {
/*  156 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "<init>(MQConnection,boolean,int,MQSession)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void initialisePubSub(MQConnection connection, boolean transacted, int acknowlesdgeMode, MQSession session) throws JMSException {
/*  166 */     if (Trace.isOn) {
/*  167 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "initialisePubSub(MQConnection,boolean,int,MQSession)", new Object[] { connection, 
/*      */             
/*  169 */             Boolean.valueOf(transacted), Integer.valueOf(acknowlesdgeMode), session });
/*      */     }
/*      */     
/*  172 */     this.sessionName = session.sessionName;
/*      */ 
/*      */     
/*  175 */     this.brk.controlQ = connection.getBrkControlQueue();
/*  176 */     this.brk.streamQ = connection.getBrkPubQueue();
/*  177 */     this.brk.qmName = connection.getBrkQueueManager();
/*      */     
/*  179 */     session.brk = this.brk;
/*      */     
/*  181 */     this.brokerVersion = connection.getBrkVersion();
/*      */     
/*  183 */     this.brkOptLevel = connection.getBrkOptLevel();
/*  184 */     session.brkOptLevel = this.brkOptLevel;
/*      */     
/*  186 */     this.nondur_subscribeQ = connection.getBrkSubQueue();
/*  187 */     session.nondur_subscribeQ = this.nondur_subscribeQ;
/*      */     
/*  189 */     this.SUBSCRIBE_CMD_POSTFIX = " MQPSStreamName " + this.brk.streamQ + " MQPSQName ";
/*      */     
/*  191 */     session.SUBSCRIBE_CMD_POSTFIX = this.SUBSCRIBE_CMD_POSTFIX;
/*      */     
/*  193 */     session.responseInterval = connection.getPubAckInterval();
/*  194 */     session.checkInterval = session.responseInterval / 2;
/*      */ 
/*      */     
/*  197 */     Integer tmp = AccessController.<Integer>doPrivileged(new PrivilegedAction<Integer>()
/*      */         {
/*      */           public Integer run()
/*      */           {
/*  201 */             if (Trace.isOn) {
/*  202 */               Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "run()");
/*      */             }
/*      */             
/*      */             try {
/*  206 */               Integer traceRet1 = Integer.getInteger("com.ibm.mq.jms.tuning.brokerResponseTimeout");
/*  207 */               if (Trace.isOn) {
/*  208 */                 Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "run()", traceRet1, 1);
/*      */               }
/*      */               
/*  211 */               return traceRet1;
/*      */             }
/*  213 */             catch (AccessControlException ace) {
/*  214 */               if (Trace.isOn) {
/*  215 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "run()", ace);
/*      */               }
/*      */               
/*  218 */               if (Trace.isOn) {
/*  219 */                 Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "run()", null, 2);
/*      */               }
/*  221 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*  225 */     if (null != tmp && tmp.intValue() > 0) {
/*  226 */       if (Trace.isOn) {
/*  227 */         Trace.traceData(this, "Setting brokerResponseTimeout from System property = " + tmp.intValue(), null);
/*      */       }
/*  229 */       this.brokerTimeout = tmp.intValue();
/*      */     } else {
/*  231 */       if (Trace.isOn) {
/*  232 */         Trace.traceData(this, "cannot read brokerResponseTimeout System property. Using default = 120000", null);
/*      */       }
/*  234 */       this.brokerTimeout = 120000;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  242 */       if (session.replyQ == null) {
/*      */         
/*  244 */         int openOptions = 1;
/*      */ 
/*      */         
/*  247 */         if (connection.getFailIfQuiesce() == 1) {
/*  248 */           openOptions |= 0x2000;
/*      */         }
/*      */         
/*  251 */         this.replyQ = session.getQM().accessQueue("SYSTEM.JMS.REPORT.QUEUE", openOptions);
/*      */         
/*  253 */         session.replyQ = this.replyQ;
/*      */       } else {
/*  255 */         this.replyQ = session.replyQ;
/*      */       } 
/*      */       
/*  258 */       this.brokerReponseGmo = new MQGetMessageOptions();
/*  259 */       this.brokerReponseGmo.options = 1;
/*  260 */       this.brokerReponseGmo.waitInterval = this.brokerTimeout;
/*      */ 
/*      */       
/*  263 */       if (connection.getFailIfQuiesce() == 1) {
/*  264 */         this.brokerReponseGmo.options |= 0x2000;
/*      */       
/*      */       }
/*      */     }
/*  268 */     catch (MQException e) {
/*  269 */       if (Trace.isOn) {
/*  270 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "initialisePubSub(MQConnection,boolean,int,MQSession)", (Throwable)e);
/*      */       }
/*      */       
/*  273 */       JMSException je = new JMSException(ConfigEnvironment.getErrorMessage("MQJMS1111"));
/*  274 */       je.setLinkedException((Exception)e);
/*  275 */       je.initCause((Throwable)e);
/*  276 */       if (Trace.isOn) {
/*  277 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "initialisePubSub(MQConnection,boolean,int,MQSession)", (Throwable)je);
/*      */       }
/*      */       
/*  280 */       throw je;
/*      */     } 
/*  282 */     if (Trace.isOn) {
/*  283 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "initialisePubSub(MQConnection,boolean,int,MQSession)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void closePubSub(MQSession session) throws JMSException {
/*  290 */     if (Trace.isOn) {
/*  291 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "closePubSub(MQSession)", new Object[] { session });
/*      */     }
/*      */ 
/*      */     
/*  295 */     if (session.isStarted() && session.usingAsyncMode()) {
/*      */       try {
/*  297 */         session.stop();
/*      */       }
/*  299 */       catch (JMSException e) {
/*  300 */         if (Trace.isOn) {
/*  301 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "closePubSub(MQSession)", (Throwable)e, 1);
/*      */         }
/*      */ 
/*      */         
/*  305 */         if (Trace.isOn) {
/*  306 */           Trace.traceData(this, "implicit stop failed", null);
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*  311 */     if (this.replyQ != null) {
/*      */ 
/*      */ 
/*      */       
/*  315 */       if (session.responseRequested == true) {
/*      */         try {
/*  317 */           MQMsg2 response = new MQMsg2();
/*  318 */           getBrokerResponse(session, response, false);
/*      */         
/*      */         }
/*  321 */         catch (JMSException e) {
/*  322 */           if (Trace.isOn) {
/*  323 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "closePubSub(MQSession)", (Throwable)e, 2);
/*      */           }
/*      */           
/*  326 */           if (Trace.isOn) {
/*  327 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "closePubSub(MQSession)", (Throwable)e, 1);
/*      */           }
/*      */           
/*  330 */           throw e;
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/*  336 */         this.replyQ.close();
/*  337 */         this.replyQ = null;
/*      */       }
/*  339 */       catch (MQException e) {
/*  340 */         if (Trace.isOn) {
/*  341 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "closePubSub(MQSession)", (Throwable)e, 3);
/*      */         }
/*      */         
/*  344 */         JMSException je = ConfigEnvironment.newException("MQJMS2000");
/*  345 */         je.setLinkedException((Exception)e);
/*  346 */         if (Trace.isOn) {
/*  347 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "closePubSub(MQSession)", (Throwable)je, 2);
/*      */         }
/*      */         
/*  350 */         throw je;
/*      */       } 
/*      */     } 
/*  353 */     if (Trace.isOn) {
/*  354 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "closePubSub(MQSession)");
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
/*      */   MQQueue getResponseQueue(MQSession session) throws JMSException {
/*  367 */     if (Trace.isOn) {
/*  368 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "getResponseQueue(MQSession)", new Object[] { session });
/*      */     }
/*      */     
/*  371 */     if (session.replyQ == null) {
/*      */       
/*      */       try {
/*      */         
/*  375 */         int openOptions = 1;
/*      */ 
/*      */         
/*  378 */         if (session.connection.getFailIfQuiesce() == 1) {
/*  379 */           openOptions |= 0x2000;
/*      */         }
/*      */         
/*  382 */         this.replyQ = session.getQM().accessQueue("SYSTEM.JMS.REPORT.QUEUE", openOptions);
/*      */         
/*  384 */         session.replyQ = this.replyQ;
/*      */       
/*      */       }
/*  387 */       catch (MQException e) {
/*  388 */         if (Trace.isOn) {
/*  389 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "getResponseQueue(MQSession)", (Throwable)e);
/*      */         }
/*      */         
/*  392 */         JMSException je = new JMSException(ConfigEnvironment.getErrorMessage("MQJMS1111"));
/*  393 */         je.setLinkedException((Exception)e);
/*  394 */         je.initCause((Throwable)e);
/*  395 */         if (Trace.isOn) {
/*  396 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "getResponseQueue(MQSession)", (Throwable)je);
/*      */         }
/*      */         
/*  399 */         throw je;
/*      */       } 
/*      */     }
/*      */     
/*  403 */     if (Trace.isOn) {
/*  404 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "getResponseQueue(MQSession)", session.replyQ);
/*      */     }
/*      */     
/*  407 */     return session.replyQ;
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
/*      */   void getBrokerResponse(MQSession session, MQMsg2 response, boolean immediateResponse) throws NoBrokerResponseException {
/*  428 */     if (Trace.isOn) {
/*  429 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "getBrokerResponse(MQSession,MQMsg2,boolean)", new Object[] { session, response, 
/*      */             
/*  431 */             Boolean.valueOf(immediateResponse) });
/*      */     }
/*  433 */     int elapsedTime = 0;
/*      */     
/*  435 */     if (immediateResponse) {
/*      */       
/*  437 */       response.setCorrelationId(response.getMessageId());
/*  438 */       this.brokerReponseGmo.waitInterval = this.brokerTimeout;
/*      */     } else {
/*      */       
/*  441 */       response.setCorrelationId(session.responseCorrelId);
/*      */       
/*  443 */       session.responseRequested = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  452 */       long currentTime = System.currentTimeMillis();
/*  453 */       elapsedTime = (int)(currentTime - session.responsePutTime);
/*      */       
/*  455 */       if (Trace.isOn) {
/*  456 */         Trace.traceData(this, "elapsed time is " + elapsedTime, null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  462 */       if (elapsedTime < this.brokerTimeout) {
/*  463 */         this.brokerReponseGmo.waitInterval = this.brokerTimeout - elapsedTime;
/*      */       } else {
/*  465 */         this.brokerReponseGmo.options &= 0xFFFFFFFE;
/*  466 */         this.brokerReponseGmo.waitInterval = 0;
/*      */       } 
/*      */     } 
/*      */     
/*  470 */     response.setMessageId(MQC.MQMI_NONE);
/*      */ 
/*      */     
/*      */     try {
/*  474 */       if (Trace.isOn) {
/*  475 */         Trace.traceData(this, "Getting Broker Response Lock for session " + session, null);
/*      */       }
/*  477 */       synchronized (session.getBrokerResponseLock()) {
/*  478 */         if (Trace.isOn) {
/*  479 */           Trace.traceData(this, "got Broker Response Lock", null);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  484 */         if (session.replyQ != null)
/*      */         {
/*  486 */           session.replyQ.getMsg2(response, this.brokerReponseGmo);
/*      */         
/*      */         }
/*      */         else
/*      */         {
/*      */           
/*  492 */           HashMap<String, Object> data = new HashMap<>();
/*  493 */           data.put("brokerVersion", Integer.valueOf(this.brokerVersion));
/*  494 */           data.put("session name", new String(this.sessionName, Charset.defaultCharset()));
/*  495 */           data.put("replyQ", (this.replyQ == null) ? "is null" : "is defined");
/*  496 */           data.put("session.replyQ", "is null");
/*  497 */           Trace.ffst("MQPubSubServices", "getBrokerResponse", "XO00I002", data, NoBrokerResponseException.class);
/*      */ 
/*      */           
/*  500 */           if (Trace.isOn) {
/*  501 */             Trace.traceData(this, "replyQ has been set to null. The session must have been closed, and the broker response processed  as part of the session's close() method", null);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  510 */           String key1 = "MQJMS1024";
/*  511 */           String msg1 = ConfigEnvironment.getErrorMessage(key1);
/*  512 */           IllegalStateException illegalStateException = new IllegalStateException(key1, msg1);
/*      */           
/*  514 */           String key = "MQJMS5053";
/*  515 */           String msg = ConfigEnvironment.getErrorMessage(key);
/*  516 */           NoBrokerResponseException je = new NoBrokerResponseException(msg, key);
/*  517 */           je.setLinkedException((Exception)illegalStateException);
/*  518 */           if (Trace.isOn) {
/*  519 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "getBrokerResponse(MQSession,MQMsg2,boolean)", (Throwable)je, 3);
/*      */           }
/*  521 */           if (Trace.isOn) {
/*  522 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "getBrokerResponse(MQSession,MQMsg2,boolean)", (Throwable)je, 1);
/*      */           }
/*      */           
/*  525 */           throw je;
/*      */         }
/*      */       
/*      */       } 
/*  529 */     } catch (MQException e) {
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
/*      */     }
/*      */     finally {
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
/*  571 */       if (Trace.isOn) {
/*  572 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "getBrokerResponse(MQSession,MQMsg2,boolean)");
/*      */       }
/*      */       
/*  575 */       this.brokerReponseGmo.options |= 0x1;
/*  576 */       this.brokerReponseGmo.waitInterval = this.brokerTimeout;
/*      */     } 
/*  578 */     if (Trace.isOn) {
/*  579 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "getBrokerResponse(MQSession,MQMsg2,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean getSparseSubscriptions(MQSession session) {
/*  589 */     if (Trace.isOn) {
/*  590 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "getSparseSubscriptions(MQSession)", new Object[] { session });
/*      */     }
/*      */     
/*  593 */     if (session.connection != null) {
/*  594 */       boolean traceRet1 = session.connection.getSparseSubscriptions();
/*  595 */       if (Trace.isOn) {
/*  596 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "getSparseSubscriptions(MQSession)", 
/*  597 */             Boolean.valueOf(traceRet1), 1);
/*      */       }
/*  599 */       return traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  604 */     if (Trace.isOn) {
/*  605 */       Trace.traceData(this, "getSparseSubscriptions, connection null.", null);
/*      */     }
/*  607 */     if (Trace.isOn) {
/*  608 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "getSparseSubscriptions(MQSession)", 
/*  609 */           Boolean.valueOf(false), 2);
/*      */     }
/*  611 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setBrokerTimeout(int x) {
/*  617 */     if (Trace.isOn) {
/*  618 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "setBrokerTimeout(int)", "setter", 
/*  619 */           Integer.valueOf(x));
/*      */     }
/*  621 */     this.brokerTimeout = x;
/*      */   }
/*      */   
/*      */   protected int getBrokerTimeout() {
/*  625 */     if (Trace.isOn) {
/*  626 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "getBrokerTimeout()", "getter", 
/*  627 */           Integer.valueOf(this.brokerTimeout));
/*      */     }
/*  629 */     return this.brokerTimeout;
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
/*      */   void sendCommand(String topicName, int command, String postfix, byte[] subscriberId, boolean wait, boolean nonDurable, MQSession session) throws JMSException {
/*      */     // Byte code:
/*      */     //   0: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   3: ifeq -> 61
/*      */     //   6: aload_0
/*      */     //   7: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices'
/*      */     //   9: ldc 'sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)'
/*      */     //   11: bipush #7
/*      */     //   13: anewarray java/lang/Object
/*      */     //   16: dup
/*      */     //   17: iconst_0
/*      */     //   18: aload_1
/*      */     //   19: aastore
/*      */     //   20: dup
/*      */     //   21: iconst_1
/*      */     //   22: iload_2
/*      */     //   23: invokestatic valueOf : (I)Ljava/lang/Integer;
/*      */     //   26: aastore
/*      */     //   27: dup
/*      */     //   28: iconst_2
/*      */     //   29: aload_3
/*      */     //   30: aastore
/*      */     //   31: dup
/*      */     //   32: iconst_3
/*      */     //   33: aload #4
/*      */     //   35: aastore
/*      */     //   36: dup
/*      */     //   37: iconst_4
/*      */     //   38: iload #5
/*      */     //   40: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*      */     //   43: aastore
/*      */     //   44: dup
/*      */     //   45: iconst_5
/*      */     //   46: iload #6
/*      */     //   48: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*      */     //   51: aastore
/*      */     //   52: dup
/*      */     //   53: bipush #6
/*      */     //   55: aload #7
/*      */     //   57: aastore
/*      */     //   58: invokestatic entry : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
/*      */     //   61: aconst_null
/*      */     //   62: astore #9
/*      */     //   64: aconst_null
/*      */     //   65: astore #10
/*      */     //   67: iconst_0
/*      */     //   68: istore #11
/*      */     //   70: iconst_1
/*      */     //   71: istore #12
/*      */     //   73: iconst_0
/*      */     //   74: istore #13
/*      */     //   76: iconst_0
/*      */     //   77: istore #14
/*      */     //   79: aconst_null
/*      */     //   80: astore #15
/*      */     //   82: iload_2
/*      */     //   83: iconst_2
/*      */     //   84: if_icmpeq -> 92
/*      */     //   87: iload_2
/*      */     //   88: iconst_4
/*      */     //   89: if_icmpne -> 130
/*      */     //   92: iload #6
/*      */     //   94: iconst_1
/*      */     //   95: if_icmpne -> 130
/*      */     //   98: aload_0
/*      */     //   99: getfield brk : Lcom/ibm/msg/client/wmq/compat/jms/internal/BrokerConnectionInfo;
/*      */     //   102: getfield qmName : Ljava/lang/String;
/*      */     //   105: aload #7
/*      */     //   107: getfield connection : Lcom/ibm/msg/client/wmq/compat/jms/internal/MQConnection;
/*      */     //   110: getfield qmgrName : Ljava/lang/String;
/*      */     //   113: invokevirtual equals : (Ljava/lang/Object;)Z
/*      */     //   116: ifeq -> 130
/*      */     //   119: aload #7
/*      */     //   121: getfield persistenceFromMD : Z
/*      */     //   124: ifne -> 130
/*      */     //   127: iconst_1
/*      */     //   128: istore #13
/*      */     //   130: aload #7
/*      */     //   132: getfield acknowledgeMode : I
/*      */     //   135: iconst_3
/*      */     //   136: if_icmpne -> 152
/*      */     //   139: iload_2
/*      */     //   140: iconst_2
/*      */     //   141: if_icmpeq -> 149
/*      */     //   144: iload_2
/*      */     //   145: iconst_4
/*      */     //   146: if_icmpne -> 152
/*      */     //   149: iconst_1
/*      */     //   150: istore #14
/*      */     //   152: iload #12
/*      */     //   154: iconst_1
/*      */     //   155: if_icmpne -> 979
/*      */     //   158: new com/ibm/msg/client/wmq/compat/jms/internal/MQJMSMessage
/*      */     //   161: dup
/*      */     //   162: invokespecial <init> : ()V
/*      */     //   165: astore #16
/*      */     //   167: new java/lang/StringBuffer
/*      */     //   170: dup
/*      */     //   171: invokespecial <init> : ()V
/*      */     //   174: astore #17
/*      */     //   176: iconst_0
/*      */     //   177: istore #12
/*      */     //   179: iload_2
/*      */     //   180: tableswitch default -> 292, 0 -> 220, 1 -> 256, 2 -> 231, 3 -> 267, 4 -> 242, 5 -> 278
/*      */     //   220: aload #17
/*      */     //   222: ldc 'MQPSCommand RegPub MQPSTopic '
/*      */     //   224: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   227: pop
/*      */     //   228: goto -> 338
/*      */     //   231: aload #17
/*      */     //   233: ldc 'MQPSCommand RegSub MQPSTopic '
/*      */     //   235: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   238: pop
/*      */     //   239: goto -> 338
/*      */     //   242: aload #17
/*      */     //   244: ldc 'MQPSCommand RegSub MQPSRegOpts CorrelAsId MQPSTopic '
/*      */     //   246: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   249: pop
/*      */     //   250: iconst_1
/*      */     //   251: istore #11
/*      */     //   253: goto -> 338
/*      */     //   256: aload #17
/*      */     //   258: ldc 'MQPSCommand DeregPub MQPSTopic '
/*      */     //   260: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   263: pop
/*      */     //   264: goto -> 338
/*      */     //   267: aload #17
/*      */     //   269: ldc 'MQPSCommand DeregSub MQPSTopic '
/*      */     //   271: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   274: pop
/*      */     //   275: goto -> 338
/*      */     //   278: aload #17
/*      */     //   280: ldc 'MQPSCommand DeregSub MQPSRegOpts CorrelAsId MQPSTopic '
/*      */     //   282: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   285: pop
/*      */     //   286: iconst_1
/*      */     //   287: istore #11
/*      */     //   289: goto -> 338
/*      */     //   292: ldc 'MQJMS1016'
/*      */     //   294: new java/lang/StringBuilder
/*      */     //   297: dup
/*      */     //   298: invokespecial <init> : ()V
/*      */     //   301: ldc 'unknown command value '
/*      */     //   303: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   306: iload_2
/*      */     //   307: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*      */     //   310: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   313: invokestatic newException : (Ljava/lang/String;Ljava/lang/Object;)Ljavax/jms/JMSException;
/*      */     //   316: astore #8
/*      */     //   318: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   321: ifeq -> 335
/*      */     //   324: aload_0
/*      */     //   325: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices'
/*      */     //   327: ldc 'sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)'
/*      */     //   329: aload #8
/*      */     //   331: iconst_1
/*      */     //   332: invokestatic throwing : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   335: aload #8
/*      */     //   337: athrow
/*      */     //   338: aload #17
/*      */     //   340: aload_1
/*      */     //   341: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   344: pop
/*      */     //   345: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   348: ifeq -> 378
/*      */     //   351: aload_0
/*      */     //   352: new java/lang/StringBuilder
/*      */     //   355: dup
/*      */     //   356: invokespecial <init> : ()V
/*      */     //   359: ldc 'Current brkOptLevel: '
/*      */     //   361: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   364: aload_0
/*      */     //   365: getfield brkOptLevel : I
/*      */     //   368: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*      */     //   371: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   374: aconst_null
/*      */     //   375: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   378: iload #13
/*      */     //   380: iconst_1
/*      */     //   381: if_icmpne -> 399
/*      */     //   384: aload_0
/*      */     //   385: getfield brkOptLevel : I
/*      */     //   388: ifle -> 399
/*      */     //   391: aload #17
/*      */     //   393: ldc ' MQPSRegOpts NonPers'
/*      */     //   395: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   398: pop
/*      */     //   399: iload #14
/*      */     //   401: iconst_1
/*      */     //   402: if_icmpne -> 420
/*      */     //   405: aload_0
/*      */     //   406: getfield brkOptLevel : I
/*      */     //   409: ifle -> 420
/*      */     //   412: aload #17
/*      */     //   414: ldc ' MQPSRegOpts DupsOK'
/*      */     //   416: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   419: pop
/*      */     //   420: aload #17
/*      */     //   422: aload_3
/*      */     //   423: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   426: pop
/*      */     //   427: aload #17
/*      */     //   429: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   432: astore #9
/*      */     //   434: aload #7
/*      */     //   436: invokevirtual createMessage : ()Lcom/ibm/msg/client/provider/ProviderMessage;
/*      */     //   439: checkcast com/ibm/msg/client/wmq/compat/jms/internal/JMSNullMessage
/*      */     //   442: astore #18
/*      */     //   444: aload #18
/*      */     //   446: iconst_2
/*      */     //   447: invokevirtual setJMSDeliveryMode : (I)V
/*      */     //   450: aload #16
/*      */     //   452: aload #9
/*      */     //   454: aload #18
/*      */     //   456: iconst_0
/*      */     //   457: iconst_0
/*      */     //   458: aconst_null
/*      */     //   459: invokevirtual writeRFH : (Ljava/lang/String;Lcom/ibm/msg/client/wmq/compat/jms/internal/JMSMessage;ZILcom/ibm/msg/client/provider/ProviderDestination;)V
/*      */     //   462: aload #16
/*      */     //   464: ldc 'MQHRF   '
/*      */     //   466: invokevirtual setFormat : (Ljava/lang/String;)V
/*      */     //   469: aload #16
/*      */     //   471: ldc 'SYSTEM.JMS.REPORT.QUEUE'
/*      */     //   473: invokevirtual setReplyToQueueName : (Ljava/lang/String;)V
/*      */     //   476: iload #11
/*      */     //   478: ifeq -> 488
/*      */     //   481: aload #16
/*      */     //   483: aload #4
/*      */     //   485: invokevirtual setCorrelationId : ([B)V
/*      */     //   488: iload #5
/*      */     //   490: ifeq -> 499
/*      */     //   493: aload #16
/*      */     //   495: iconst_3
/*      */     //   496: invokevirtual setReport : (I)V
/*      */     //   499: aload #15
/*      */     //   501: ifnull -> 511
/*      */     //   504: aload #16
/*      */     //   506: aload #15
/*      */     //   508: invokevirtual setUserId : (Ljava/lang/String;)V
/*      */     //   511: goto -> 567
/*      */     //   514: astore #18
/*      */     //   516: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   519: ifeq -> 533
/*      */     //   522: aload_0
/*      */     //   523: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices'
/*      */     //   525: ldc 'sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)'
/*      */     //   527: aload #18
/*      */     //   529: iconst_1
/*      */     //   530: invokestatic catchBlock : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   533: ldc 'MQJMS3008'
/*      */     //   535: invokestatic newException : (Ljava/lang/String;)Ljavax/jms/JMSException;
/*      */     //   538: astore #8
/*      */     //   540: aload #8
/*      */     //   542: aload #18
/*      */     //   544: invokevirtual setLinkedException : (Ljava/lang/Exception;)V
/*      */     //   547: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   550: ifeq -> 564
/*      */     //   553: aload_0
/*      */     //   554: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices'
/*      */     //   556: ldc 'sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)'
/*      */     //   558: aload #8
/*      */     //   560: iconst_2
/*      */     //   561: invokestatic throwing : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   564: aload #8
/*      */     //   566: athrow
/*      */     //   567: aload #7
/*      */     //   569: invokevirtual getQM : ()Lcom/ibm/msg/client/wmq/compat/base/internal/MQQueueManager;
/*      */     //   572: aload_0
/*      */     //   573: getfield brk : Lcom/ibm/msg/client/wmq/compat/jms/internal/BrokerConnectionInfo;
/*      */     //   576: getfield qmName : Ljava/lang/String;
/*      */     //   579: aload_0
/*      */     //   580: getfield brk : Lcom/ibm/msg/client/wmq/compat/jms/internal/BrokerConnectionInfo;
/*      */     //   583: getfield controlQ : Ljava/lang/String;
/*      */     //   586: aload #16
/*      */     //   588: invokestatic MQPUT1 : (Lcom/ibm/msg/client/wmq/compat/base/internal/MQQueueManager;Ljava/lang/String;Ljava/lang/String;Lcom/ibm/msg/client/wmq/compat/jms/internal/MQJMSMessage;)V
/*      */     //   591: goto -> 756
/*      */     //   594: astore #18
/*      */     //   596: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   599: ifeq -> 613
/*      */     //   602: aload_0
/*      */     //   603: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices'
/*      */     //   605: ldc 'sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)'
/*      */     //   607: aload #18
/*      */     //   609: iconst_2
/*      */     //   610: invokestatic catchBlock : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   613: iconst_0
/*      */     //   614: istore #19
/*      */     //   616: aload #18
/*      */     //   618: invokevirtual getLinkedException : ()Ljava/lang/Exception;
/*      */     //   621: astore #20
/*      */     //   623: aload #20
/*      */     //   625: instanceof com/ibm/mq/MQException
/*      */     //   628: ifeq -> 661
/*      */     //   631: aload #20
/*      */     //   633: checkcast com/ibm/mq/MQException
/*      */     //   636: astore #21
/*      */     //   638: aload #21
/*      */     //   640: getfield completionCode : I
/*      */     //   643: iconst_1
/*      */     //   644: if_icmpne -> 661
/*      */     //   647: aload #21
/*      */     //   649: getfield reasonCode : I
/*      */     //   652: sipush #2104
/*      */     //   655: if_icmpne -> 661
/*      */     //   658: iconst_1
/*      */     //   659: istore #19
/*      */     //   661: iload #19
/*      */     //   663: ifne -> 700
/*      */     //   666: ldc 'MQJMS3009'
/*      */     //   668: invokestatic newException : (Ljava/lang/String;)Ljavax/jms/JMSException;
/*      */     //   671: astore #8
/*      */     //   673: aload #8
/*      */     //   675: aload #18
/*      */     //   677: invokevirtual setLinkedException : (Ljava/lang/Exception;)V
/*      */     //   680: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   683: ifeq -> 697
/*      */     //   686: aload_0
/*      */     //   687: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices'
/*      */     //   689: ldc 'sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)'
/*      */     //   691: aload #8
/*      */     //   693: iconst_3
/*      */     //   694: invokestatic throwing : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   697: aload #8
/*      */     //   699: athrow
/*      */     //   700: goto -> 756
/*      */     //   703: astore #18
/*      */     //   705: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   708: ifeq -> 722
/*      */     //   711: aload_0
/*      */     //   712: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices'
/*      */     //   714: ldc 'sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)'
/*      */     //   716: aload #18
/*      */     //   718: iconst_3
/*      */     //   719: invokestatic catchBlock : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   722: ldc 'MQJMS3009'
/*      */     //   724: invokestatic newException : (Ljava/lang/String;)Ljavax/jms/JMSException;
/*      */     //   727: astore #8
/*      */     //   729: aload #8
/*      */     //   731: aload #18
/*      */     //   733: invokevirtual setLinkedException : (Ljava/lang/Exception;)V
/*      */     //   736: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   739: ifeq -> 753
/*      */     //   742: aload_0
/*      */     //   743: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices'
/*      */     //   745: ldc 'sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)'
/*      */     //   747: aload #8
/*      */     //   749: iconst_4
/*      */     //   750: invokestatic throwing : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   753: aload #8
/*      */     //   755: athrow
/*      */     //   756: iload #5
/*      */     //   758: ifeq -> 976
/*      */     //   761: new com/ibm/msg/client/wmq/compat/base/internal/MQMsg2
/*      */     //   764: dup
/*      */     //   765: invokespecial <init> : ()V
/*      */     //   768: astore #10
/*      */     //   770: aload #10
/*      */     //   772: aload #16
/*      */     //   774: invokevirtual getMessageId : ()[B
/*      */     //   777: invokevirtual setMessageId : ([B)V
/*      */     //   780: aload_0
/*      */     //   781: aload #7
/*      */     //   783: aload #10
/*      */     //   785: iconst_1
/*      */     //   786: invokevirtual getBrokerResponse : (Lcom/ibm/msg/client/wmq/compat/jms/internal/MQSession;Lcom/ibm/msg/client/wmq/compat/base/internal/MQMsg2;Z)V
/*      */     //   789: aload #10
/*      */     //   791: invokestatic checkResponse : (Lcom/ibm/msg/client/wmq/compat/base/internal/MQMsg2;)V
/*      */     //   794: goto -> 976
/*      */     //   797: astore #18
/*      */     //   799: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   802: ifeq -> 816
/*      */     //   805: aload_0
/*      */     //   806: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices'
/*      */     //   808: ldc 'sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)'
/*      */     //   810: aload #18
/*      */     //   812: iconst_4
/*      */     //   813: invokestatic catchBlock : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   816: aload #18
/*      */     //   818: invokevirtual getReason : ()I
/*      */     //   821: istore #19
/*      */     //   823: iload #19
/*      */     //   825: lookupswitch default -> 938, 3078 -> 920, 3083 -> 852
/*      */     //   852: aload_0
/*      */     //   853: getfield brkOptLevel : I
/*      */     //   856: ifle -> 938
/*      */     //   859: iload #14
/*      */     //   861: iconst_1
/*      */     //   862: if_icmpne -> 881
/*      */     //   865: aload_0
/*      */     //   866: dup
/*      */     //   867: getfield brkOptLevel : I
/*      */     //   870: iconst_1
/*      */     //   871: isub
/*      */     //   872: putfield brkOptLevel : I
/*      */     //   875: iconst_1
/*      */     //   876: istore #12
/*      */     //   878: goto -> 900
/*      */     //   881: iload #13
/*      */     //   883: iconst_1
/*      */     //   884: if_icmpne -> 900
/*      */     //   887: aload_0
/*      */     //   888: dup
/*      */     //   889: getfield brkOptLevel : I
/*      */     //   892: iconst_1
/*      */     //   893: isub
/*      */     //   894: putfield brkOptLevel : I
/*      */     //   897: iconst_1
/*      */     //   898: istore #12
/*      */     //   900: iload #12
/*      */     //   902: ifeq -> 938
/*      */     //   905: aload #7
/*      */     //   907: getfield connection : Lcom/ibm/msg/client/wmq/compat/jms/internal/MQConnection;
/*      */     //   910: aload_0
/*      */     //   911: getfield brkOptLevel : I
/*      */     //   914: invokevirtual setBrkOptLevel : (I)V
/*      */     //   917: goto -> 938
/*      */     //   920: aload #15
/*      */     //   922: ifnonnull -> 938
/*      */     //   925: aload #18
/*      */     //   927: invokevirtual getUserId : ()Ljava/lang/String;
/*      */     //   930: astore #15
/*      */     //   932: iconst_1
/*      */     //   933: istore #12
/*      */     //   935: goto -> 938
/*      */     //   938: iload #12
/*      */     //   940: ifne -> 976
/*      */     //   943: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   946: ifeq -> 956
/*      */     //   949: aload_0
/*      */     //   950: ldc 'Failed to reregister.'
/*      */     //   952: aconst_null
/*      */     //   953: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   956: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   959: ifeq -> 973
/*      */     //   962: aload_0
/*      */     //   963: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices'
/*      */     //   965: ldc 'sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)'
/*      */     //   967: aload #18
/*      */     //   969: iconst_5
/*      */     //   970: invokestatic throwing : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   973: aload #18
/*      */     //   975: athrow
/*      */     //   976: goto -> 152
/*      */     //   979: goto -> 1022
/*      */     //   982: astore #16
/*      */     //   984: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   987: ifeq -> 1001
/*      */     //   990: aload_0
/*      */     //   991: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices'
/*      */     //   993: ldc 'sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)'
/*      */     //   995: aload #16
/*      */     //   997: iconst_5
/*      */     //   998: invokestatic catchBlock : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   1001: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   1004: ifeq -> 1019
/*      */     //   1007: aload_0
/*      */     //   1008: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices'
/*      */     //   1010: ldc 'sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)'
/*      */     //   1012: aload #16
/*      */     //   1014: bipush #6
/*      */     //   1016: invokestatic throwing : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   1019: aload #16
/*      */     //   1021: athrow
/*      */     //   1022: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   1025: ifeq -> 1036
/*      */     //   1028: aload_0
/*      */     //   1029: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices'
/*      */     //   1031: ldc 'sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)'
/*      */     //   1033: invokestatic exit : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   1036: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #637	-> 0
/*      */     //   #638	-> 6
/*      */     //   #640	-> 23
/*      */     //   #641	-> 48
/*      */     //   #638	-> 58
/*      */     //   #644	-> 61
/*      */     //   #645	-> 64
/*      */     //   #646	-> 67
/*      */     //   #647	-> 70
/*      */     //   #648	-> 73
/*      */     //   #649	-> 76
/*      */     //   #650	-> 79
/*      */     //   #662	-> 82
/*      */     //   #664	-> 127
/*      */     //   #669	-> 130
/*      */     //   #670	-> 149
/*      */     //   #675	-> 152
/*      */     //   #676	-> 158
/*      */     //   #677	-> 167
/*      */     //   #679	-> 176
/*      */     //   #683	-> 179
/*      */     //   #685	-> 220
/*      */     //   #686	-> 228
/*      */     //   #688	-> 231
/*      */     //   #689	-> 239
/*      */     //   #691	-> 242
/*      */     //   #692	-> 250
/*      */     //   #693	-> 253
/*      */     //   #695	-> 256
/*      */     //   #696	-> 264
/*      */     //   #698	-> 267
/*      */     //   #699	-> 275
/*      */     //   #701	-> 278
/*      */     //   #702	-> 286
/*      */     //   #703	-> 289
/*      */     //   #706	-> 292
/*      */     //   #707	-> 318
/*      */     //   #708	-> 324
/*      */     //   #711	-> 335
/*      */     //   #718	-> 338
/*      */     //   #721	-> 345
/*      */     //   #722	-> 351
/*      */     //   #725	-> 378
/*      */     //   #727	-> 391
/*      */     //   #730	-> 399
/*      */     //   #732	-> 412
/*      */     //   #735	-> 420
/*      */     //   #737	-> 427
/*      */     //   #739	-> 434
/*      */     //   #742	-> 444
/*      */     //   #745	-> 450
/*      */     //   #748	-> 462
/*      */     //   #750	-> 469
/*      */     //   #753	-> 476
/*      */     //   #754	-> 481
/*      */     //   #757	-> 488
/*      */     //   #759	-> 493
/*      */     //   #764	-> 499
/*      */     //   #765	-> 504
/*      */     //   #781	-> 511
/*      */     //   #769	-> 514
/*      */     //   #770	-> 516
/*      */     //   #771	-> 522
/*      */     //   #774	-> 533
/*      */     //   #775	-> 540
/*      */     //   #776	-> 547
/*      */     //   #777	-> 553
/*      */     //   #780	-> 564
/*      */     //   #787	-> 567
/*      */     //   #836	-> 591
/*      */     //   #789	-> 594
/*      */     //   #790	-> 596
/*      */     //   #791	-> 602
/*      */     //   #795	-> 613
/*      */     //   #796	-> 616
/*      */     //   #797	-> 623
/*      */     //   #798	-> 631
/*      */     //   #807	-> 638
/*      */     //   #810	-> 658
/*      */     //   #813	-> 661
/*      */     //   #814	-> 666
/*      */     //   #815	-> 673
/*      */     //   #816	-> 680
/*      */     //   #817	-> 686
/*      */     //   #820	-> 697
/*      */     //   #836	-> 700
/*      */     //   #824	-> 703
/*      */     //   #825	-> 705
/*      */     //   #826	-> 711
/*      */     //   #829	-> 722
/*      */     //   #830	-> 729
/*      */     //   #831	-> 736
/*      */     //   #832	-> 742
/*      */     //   #835	-> 753
/*      */     //   #841	-> 756
/*      */     //   #843	-> 761
/*      */     //   #844	-> 770
/*      */     //   #846	-> 780
/*      */     //   #851	-> 789
/*      */     //   #907	-> 794
/*      */     //   #854	-> 797
/*      */     //   #855	-> 799
/*      */     //   #856	-> 805
/*      */     //   #861	-> 816
/*      */     //   #862	-> 823
/*      */     //   #867	-> 852
/*      */     //   #870	-> 859
/*      */     //   #871	-> 865
/*      */     //   #872	-> 875
/*      */     //   #873	-> 881
/*      */     //   #874	-> 887
/*      */     //   #875	-> 897
/*      */     //   #877	-> 900
/*      */     //   #878	-> 905
/*      */     //   #885	-> 920
/*      */     //   #886	-> 925
/*      */     //   #887	-> 932
/*      */     //   #896	-> 938
/*      */     //   #897	-> 943
/*      */     //   #898	-> 949
/*      */     //   #900	-> 956
/*      */     //   #901	-> 962
/*      */     //   #905	-> 973
/*      */     //   #911	-> 976
/*      */     //   #923	-> 979
/*      */     //   #913	-> 982
/*      */     //   #914	-> 984
/*      */     //   #915	-> 990
/*      */     //   #918	-> 1001
/*      */     //   #919	-> 1007
/*      */     //   #922	-> 1019
/*      */     //   #924	-> 1022
/*      */     //   #925	-> 1028
/*      */     //   #928	-> 1036
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   318	20	8	je	Ljavax/jms/JMSException;
/*      */     //   444	67	18	jmsNullMessage	Lcom/ibm/msg/client/wmq/compat/jms/internal/JMSNullMessage;
/*      */     //   516	51	18	e	Ljava/lang/Exception;
/*      */     //   540	27	8	je	Ljavax/jms/JMSException;
/*      */     //   638	23	21	mqe	Lcom/ibm/mq/MQException;
/*      */     //   673	27	8	je	Ljavax/jms/JMSException;
/*      */     //   616	84	19	subsume	Z
/*      */     //   623	77	20	le	Ljava/lang/Exception;
/*      */     //   596	104	18	e	Ljavax/jms/JMSException;
/*      */     //   705	51	18	e	Ljava/lang/Exception;
/*      */     //   729	27	8	je	Ljavax/jms/JMSException;
/*      */     //   823	153	19	rc	I
/*      */     //   799	177	18	e	Lcom/ibm/mq/jms/BrokerCommandFailedException;
/*      */     //   167	809	16	baseCommand	Lcom/ibm/msg/client/wmq/compat/jms/internal/MQJMSMessage;
/*      */     //   176	800	17	directiveBuffer	Ljava/lang/StringBuffer;
/*      */     //   984	38	16	jmse	Ljavax/jms/JMSException;
/*      */     //   0	1037	0	this	Lcom/ibm/msg/client/wmq/compat/jms/internal/MQPubSubServices;
/*      */     //   0	1037	1	topicName	Ljava/lang/String;
/*      */     //   0	1037	2	command	I
/*      */     //   0	1037	3	postfix	Ljava/lang/String;
/*      */     //   0	1037	4	subscriberId	[B
/*      */     //   0	1037	5	wait	Z
/*      */     //   0	1037	6	nonDurable	Z
/*      */     //   0	1037	7	session	Lcom/ibm/msg/client/wmq/compat/jms/internal/MQSession;
/*      */     //   64	973	9	directive	Ljava/lang/String;
/*      */     //   67	970	10	response	Lcom/ibm/msg/client/wmq/compat/base/internal/MQMsg2;
/*      */     //   70	967	11	useCorrelId	Z
/*      */     //   73	964	12	retry	Z
/*      */     //   76	961	13	nonPersOption	Z
/*      */     //   79	958	14	dupsOKOption	Z
/*      */     //   82	955	15	userId	Ljava/lang/String;
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   152	979	982	javax/jms/JMSException
/*      */     //   338	511	514	java/lang/Exception
/*      */     //   567	591	594	javax/jms/JMSException
/*      */     //   567	591	703	java/lang/Exception
/*      */     //   789	794	797	com/ibm/mq/jms/BrokerCommandFailedException
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
/*      */   boolean requestResponse(MQSession session) throws JMSException {
/*  937 */     if (Trace.isOn) {
/*  938 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "requestResponse(MQSession)", new Object[] { session });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  943 */     session.publishCount++;
/*  944 */     if (Trace.isOn) {
/*  945 */       Trace.traceData(this, "publishCount is " + session.publishCount, null);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  950 */     if (!session.responseRequested && !session.responseOnCommit && 
/*  951 */       session.publishCount >= session.responseInterval) {
/*  952 */       if (Trace.isOn) {
/*  953 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "requestResponse(MQSession)", 
/*  954 */             Boolean.valueOf(true), 1);
/*      */       }
/*  956 */       return true;
/*      */     } 
/*      */     
/*  959 */     if (Trace.isOn) {
/*  960 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "requestResponse(MQSession)", 
/*  961 */           Boolean.valueOf(false), 2);
/*      */     }
/*  963 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean checkForResponse(MQSession session) throws JMSException {
/*  972 */     if (Trace.isOn) {
/*  973 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "checkForResponse(MQSession)", new Object[] { session });
/*      */     }
/*      */     
/*  976 */     if (Trace.isOn) {
/*  977 */       Trace.traceData(this, "publishCount is " + session.publishCount, null);
/*  978 */       Trace.traceData(this, "checkInterval is " + session.checkInterval, null);
/*      */     } 
/*      */     
/*  981 */     if (session.responseRequested == true) {
/*  982 */       if (session.publishCount >= session.checkInterval) {
/*  983 */         if (Trace.isOn) {
/*  984 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "checkForResponse(MQSession)", 
/*  985 */               Boolean.valueOf(true), 1);
/*      */         }
/*  987 */         return true;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  994 */       if (System.currentTimeMillis() - session.responsePutTime > 59000L) {
/*  995 */         if (Trace.isOn) {
/*  996 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "checkForResponse(MQSession)", 
/*  997 */               Boolean.valueOf(true), 2);
/*      */         }
/*  999 */         return true;
/*      */       } 
/*      */     } 
/* 1002 */     if (Trace.isOn) {
/* 1003 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPubSubServices", "checkForResponse(MQSession)", 
/* 1004 */           Boolean.valueOf(false), 3);
/*      */     }
/* 1006 */     return false;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQPubSubServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */