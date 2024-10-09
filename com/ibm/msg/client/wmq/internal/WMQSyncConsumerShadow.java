/*     */ package com.ibm.msg.client.wmq.internal;
/*     */ 
/*     */ import com.ibm.mq.constants.CMQC;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.system.JmqiSP;
/*     */ import com.ibm.msg.client.commonservices.Log.Log;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.provider.ProviderMessageListener;
/*     */ import com.ibm.msg.client.wmq.common.internal.Reason;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQConsumerOwner;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*     */ import java.util.HashMap;
/*     */ import javax.jms.JMSException;
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
/*     */ class WMQSyncConsumerShadow
/*     */   extends WMQConsumerShadow
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQSyncConsumerShadow.java";
/*     */   private WMQSession session;
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQSyncConsumerShadow.java");
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
/*     */   WMQSyncConsumerShadow(JmsPropertyContext jmsProps, WMQSession session, WMQDestination destination, String selector, boolean nolocal, boolean shared, boolean durable, String subscriptionName) {
/*  78 */     this(jmsProps, session, destination, selector, nolocal, shared, durable, subscriptionName, (byte[])null);
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "<init>(JmsPropertyContext,WMQSession,WMQDestination,String,boolean,boolean,boolean,String)", new Object[] { jmsProps, session, destination, selector, 
/*     */             
/*  82 */             Boolean.valueOf(nolocal), 
/*  83 */             Boolean.valueOf(shared), Boolean.valueOf(durable), subscriptionName });
/*     */     }
/*     */     
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "<init>(JmsPropertyContext,WMQSession,WMQDestination,String,boolean,boolean,boolean,String)");
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
/*     */   WMQSyncConsumerShadow(JmsPropertyContext jmsProps, WMQSession session, WMQDestination destination, String selector, boolean nolocal, boolean shared, boolean durable, String subscriptionName, byte[] subID) {
/* 103 */     super(jmsProps, session, destination, selector, nolocal, shared, durable, subscriptionName, subID);
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "<init>(JmsPropertyContext,WMQSession,WMQDestination,String,boolean,boolean,boolean,String,byte [ ])", new Object[] { jmsProps, session, destination, selector, 
/*     */             
/* 107 */             Boolean.valueOf(nolocal), 
/* 108 */             Boolean.valueOf(shared), Boolean.valueOf(durable), subscriptionName, subID });
/*     */     }
/*     */     
/* 111 */     this.session = session;
/*     */     
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "<init>(JmsPropertyContext,WMQSession,WMQDestination,String,boolean,boolean,boolean,String,byte [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initialize() throws JMSException {
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "initialize()");
/*     */     }
/* 128 */     super.initialize();
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "initialize()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ProviderMessage receiveInternal(long timeOut) throws JMSException {
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "receiveInternal(long)", new Object[] {
/* 143 */             Long.valueOf(timeOut)
/*     */           });
/*     */     }
/* 146 */     Pint cc = this.env.newPint();
/* 147 */     Pint rc = this.env.newPint();
/*     */     
/* 149 */     ProviderMessage resultMsg = null;
/* 150 */     WMQGMO gmo = null;
/*     */ 
/*     */     
/* 153 */     if (timeOut != -1L && (timeOut > 2147483647L || timeOut < 0L)) {
/* 154 */       HashMap<String, Object> inserts = new HashMap<>();
/* 155 */       inserts.put("XMSC_INSERT_TIMEOUT", Long.valueOf(timeOut));
/* 156 */       JMSException je = (JMSException)NLSServices.createException("JMSWMQ1067", inserts);
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "receiveInternal(long)", (Throwable)je, 1);
/*     */       }
/*     */       
/* 161 */       throw je;
/*     */     } 
/*     */     
/* 164 */     boolean unlimitedWait = (timeOut == 0L);
/* 165 */     long thisWaitTime = 0L;
/*     */     
/* 167 */     long endTime = 0L;
/* 168 */     boolean firstPassDone = false;
/* 169 */     boolean lastTry = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 175 */       if (unlimitedWait) {
/* 176 */         thisWaitTime = -1L;
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 181 */       else if (firstPassDone) {
/*     */ 
/*     */ 
/*     */         
/* 185 */         thisWaitTime = Math.max(endTime - System.currentTimeMillis(), 0L);
/* 186 */         if (thisWaitTime == 0L) {
/* 187 */           lastTry = true;
/*     */         
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 193 */         thisWaitTime = Math.max(timeOut, 0L);
/* 194 */         endTime = System.currentTimeMillis() + timeOut;
/*     */       } 
/*     */       
/*     */       try {
/* 198 */         gmo = computeGMO((int)thisWaitTime);
/* 199 */         if (this.nolocal) {
/* 200 */           boolean messageValid = false;
/*     */           do {
/* 202 */             resultMsg = getMsg(gmo, -1, true, cc, rc);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 209 */             String parentConnId = ((WMQConnection)this.session.getConnection()).getHconn().getConnectionIdAsString();
/* 210 */             String messageConnId = null;
/* 211 */             if (resultMsg != null) {
/* 212 */               messageConnId = resultMsg.getStringProperty("JMS_IBM_ConnectionID");
/*     */             }
/* 214 */             if (!parentConnId.equals(messageConnId)) {
/*     */ 
/*     */ 
/*     */               
/* 218 */               messageValid = true;
/*     */ 
/*     */ 
/*     */             
/*     */             }
/* 223 */             else if (Trace.isOn) {
/* 224 */               Trace.data(this, "com.ibm.msg.client.wmq.internal.ApiSyncConsumerShadow", "noLocal, Message disgarded as ConnectionID matched our ConnectioID");
/*     */             
/*     */             }
/*     */           
/*     */           }
/* 229 */           while (!messageValid);
/*     */         }
/*     */         else {
/*     */           
/* 233 */           resultMsg = getMsg(gmo, -1, true, cc, rc);
/*     */         }
/*     */       
/*     */       }
/* 237 */       catch (JMSException e) {
/* 238 */         if (Trace.isOn) {
/* 239 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "receiveInternal(long)", (Throwable)e);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 245 */         if (rc.x == 2107 && cc.x != 0) {
/*     */           
/* 247 */           firstPassDone = true;
/*     */           
/* 249 */           if (lastTry) {
/* 250 */             if (Trace.isOn) {
/* 251 */               Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "receiveInternal(long)", null, 1);
/*     */             }
/*     */             
/* 254 */             return null;
/*     */           } 
/*     */         } else {
/* 257 */           if (rc.x == 2033) {
/*     */             
/* 259 */             if (Trace.isOn) {
/* 260 */               Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "receiveInternal(long)", null, 2);
/*     */             }
/*     */             
/* 263 */             return null;
/*     */           } 
/*     */           
/* 266 */           if (Trace.isOn) {
/* 267 */             Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "receiveInternal(long)", (Throwable)e, 2);
/*     */           }
/*     */           
/* 270 */           throw e;
/*     */         }
/*     */       
/*     */       }
/* 274 */       catch (JmqiException jmqiException) {
/* 275 */         WMQMessageConsumer.checkJmqiCallSuccess("JMSWMQ2002", this.destination.getName(), "XMSC_DESTINATION_NAME", this.env
/* 276 */             .newPint(jmqiException.getCompCode()), this.env
/* 277 */             .newPint(jmqiException.getReason()), this.env, "XN00P101", this.helper, this.helper.getHconn());
/*     */       }
/*     */     
/*     */     }
/* 281 */     while (resultMsg == null && isRunning() == true);
/*     */     
/* 283 */     if (resultMsg != null) {
/* 284 */       this.receiveCount++;
/* 285 */       this.lastReceiveTime = System.currentTimeMillis();
/*     */     } 
/*     */ 
/*     */     
/* 289 */     if (!isRunning() && resultMsg == null) {
/* 290 */       if (Trace.isOn) {
/* 291 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "receiveInternal(long)", null, 3);
/*     */       }
/*     */       
/* 294 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 299 */     if (resultMsg != null) {
/* 300 */       int getMessageOptions = gmo.getOptions();
/* 301 */       int mqmdPersistence = this.mqmd.getPersistence();
/* 302 */       if (messageConsumedUnderSyncpoint(getMessageOptions, mqmdPersistence))
/*     */       {
/*     */ 
/*     */         
/* 306 */         this.session.operationPerformed(WMQConsumerOwner.Operation.GET, true);
/*     */       }
/*     */     } 
/*     */     
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "receiveInternal(long)", resultMsg, 4);
/*     */     }
/*     */     
/* 314 */     return resultMsg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setMessageListener(ProviderMessageListener listener) throws JMSException {
/* 324 */     if (Trace.isOn) {
/* 325 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "setMessageListener(ProviderMessageListener)", "setter", listener);
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
/*     */   ProviderMessage receive(byte[] token, int msgLength) throws JMSException {
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "receive(byte [ ],int)", new Object[] { token, 
/* 343 */             Integer.valueOf(msgLength) });
/*     */     }
/* 345 */     ProviderMessage resultMsg = null;
/*     */ 
/*     */ 
/*     */     
/* 349 */     WMQGMO gmo = computeGMO(0);
/*     */ 
/*     */     
/* 352 */     gmo.setMatchOptions(32);
/* 353 */     gmo.setMsgToken(token);
/* 354 */     gmo.setVersion(3);
/*     */ 
/*     */ 
/*     */     
/* 358 */     int options = gmo.getOptions();
/* 359 */     options &= 0xFFFFFFFE;
/* 360 */     gmo.setOptions(options);
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
/* 372 */     resultMsg = getMsg(gmo, msgLength, false);
/*     */ 
/*     */ 
/*     */     
/* 376 */     if (resultMsg != null) {
/* 377 */       int getMessageOptions = gmo.getOptions();
/* 378 */       int mqmdPersistence = this.mqmd.getPersistence();
/* 379 */       if (messageConsumedUnderSyncpoint(getMessageOptions, mqmdPersistence))
/*     */       {
/*     */ 
/*     */         
/* 383 */         this.session.operationPerformed(WMQConsumerOwner.Operation.GET, true);
/*     */       }
/*     */     } 
/*     */     
/* 387 */     if (Trace.isOn) {
/* 388 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "receive(byte [ ],int)", resultMsg);
/*     */     }
/*     */     
/* 391 */     return resultMsg;
/*     */   }
/*     */ 
/*     */   
/*     */   void closeInternal(boolean closeHobj) throws JMSException {
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "closeInternal(boolean)", new Object[] {
/* 398 */             Boolean.valueOf(closeHobj)
/*     */           });
/*     */     }
/*     */     
/*     */     try {
/* 403 */       if (closeHobj) {
/* 404 */         Pint cc = this.env.newPint();
/* 405 */         Pint rc = this.env.newPint();
/*     */         
/* 407 */         if (this.destination.isTopic()) {
/*     */ 
/*     */ 
/*     */           
/* 411 */           this.mq.MQCLOSE(this.hconn, this.phsub, 0, cc, rc);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 419 */           if (this.shared && rc.x == 2429) {
/*     */             
/* 421 */             if (Trace.isOn) {
/* 422 */               Trace.traceData(this, "closeInternal(boolean)", "Subscription in use for shared consumer. Leaving subscription in place", rc);
/*     */             
/*     */             }
/*     */           }
/*     */           else {
/*     */             
/* 428 */             WMQMessageConsumer.checkJmqiCallSuccess("JMSWMQ0025", this.destination
/* 429 */                 .getName(), "XMSC_DESTINATION_NAME", cc, rc, this.env, "XN004009", this.helper, this.hconn);
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 434 */         this.mq.MQCLOSE(this.hconn, this.phobj, 0, cc, rc);
/*     */       } 
/*     */     } finally {
/*     */       
/* 438 */       if (Trace.isOn) {
/* 439 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "closeInternal(boolean)");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 444 */     if (Trace.isOn) {
/* 445 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "closeInternal(boolean)");
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
/*     */   void setRunning(boolean running) throws JMSException {
/* 460 */     if (Trace.isOn)
/* 461 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "setRunning(boolean)", new Object[] {
/* 462 */             Boolean.valueOf(running)
/*     */           }); 
/* 464 */     super.setRunning(running);
/*     */ 
/*     */     
/* 467 */     if (!running) {
/*     */ 
/*     */ 
/*     */       
/* 471 */       Hconn helperHconn = this.helper.getHconn();
/* 472 */       Hconn connectionsHconn = ((WMQConnection)this.helper.getConnection()).getHconn();
/*     */ 
/*     */ 
/*     */       
/* 476 */       if (helperHconn == CMQC.jmqi_MQHC_UNUSABLE_HCONN || connectionsHconn == CMQC.jmqi_MQHC_UNUSABLE_HCONN) {
/* 477 */         if (Trace.isOn) {
/* 478 */           Trace.data(this, "helper.hconn", helperHconn);
/* 479 */           Trace.data(this, "helper.connection.hconn", connectionsHconn);
/*     */         } 
/* 481 */         if (Trace.isOn) {
/* 482 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "setRunning(boolean)", 1);
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/* 487 */       Pint temprc = this.env.newPint();
/* 488 */       Pint tempcc = this.env.newPint();
/* 489 */       ((JmqiSP)this.mq).jmqiCancelWaitingGets(connectionsHconn, this.hconn, helperHconn, tempcc, temprc);
/*     */ 
/*     */ 
/*     */       
/* 493 */       if (temprc.x != 2107 && tempcc.x != 0) {
/* 494 */         if (Reason.isImpossibleReason(temprc.x, tempcc.x, (JmqiSP)this.mq)) {
/*     */           
/* 496 */           HashMap<String, Object> info = new HashMap<>();
/* 497 */           info.put("running", Boolean.valueOf(running));
/* 498 */           info.put("reason", Integer.valueOf(temprc.x));
/* 499 */           info.put("compcode", Integer.valueOf(tempcc.x));
/* 500 */           info.put("hconn", this.hconn);
/* 501 */           Trace.ffst("WMQSyncConsumerShadow", "setRunning(boolean)", "XN00T001", info, null);
/*     */         }
/*     */         else {
/*     */           
/* 505 */           HashMap<String, Object> info = new HashMap<>();
/* 506 */           info.put("XMSC_INSERT_METHOD", "spiNotify");
/* 507 */           info.put("XMSC_INSERT_COMP_CODE", Integer.valueOf(tempcc.x));
/* 508 */           info.put("XMSC_INSERT_REASON", Integer.valueOf(temprc.x));
/* 509 */           Log.log(this, "setRunning(boolean)", "JMSWMQ2019", info);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 514 */     if (Trace.isOn) {
/* 515 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "setRunning(boolean)", 2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int computeQueueOpenOptions() throws JMSException {
/* 522 */     if (Trace.isOn) {
/* 523 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "computeQueueOpenOptions()");
/*     */     }
/*     */     
/* 526 */     int options = super.computeQueueOpenOptions();
/* 527 */     options |= 0x8;
/*     */     
/* 529 */     if (Trace.isOn) {
/* 530 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "computeQueueOpenOptions()", 
/* 531 */           Integer.valueOf(options));
/*     */     }
/* 533 */     return options;
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
/*     */   protected void deleteLockedMessage(byte[] token, int msgLength) throws JMSException {
/* 545 */     if (Trace.isOn) {
/* 546 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "deleteLockedMessage(byte [ ],int)", new Object[] { token, 
/* 547 */             Integer.valueOf(msgLength) });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 552 */     WMQGMO gmo = new WMQGMO(this.env);
/*     */ 
/*     */     
/* 555 */     gmo.setMatchOptions(32);
/* 556 */     gmo.setMsgToken(token);
/* 557 */     gmo.setVersion(3);
/*     */ 
/*     */ 
/*     */     
/* 561 */     int options = gmo.getOptions();
/* 562 */     options &= 0xFFFFFFFE;
/* 563 */     options &= 0xFFFFFDFF;
/* 564 */     options &= 0xFFFFFFEF;
/* 565 */     gmo.setOptions(options);
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
/* 577 */     getMsg(gmo, msgLength, false);
/*     */     
/* 579 */     if (Trace.isOn) {
/* 580 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "deleteLockedMessage(byte [ ],int)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ProviderMessage receiveAndLock(int timeout) throws JMSException {
/*     */     int thisTimeout;
/* 587 */     if (Trace.isOn) {
/* 588 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "receiveAndLock(int)", new Object[] {
/* 589 */             Integer.valueOf(timeout)
/*     */           });
/*     */     }
/* 592 */     WMQGMO gmo = new WMQGMO(this.env);
/* 593 */     int options = gmo.getOptions();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 601 */     int gmoVersion = gmo.getVersion();
/* 602 */     if (gmoVersion < 3) {
/* 603 */       gmo.setVersion(3);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 612 */     switch (timeout) {
/*     */       case -1:
/* 614 */         thisTimeout = 0;
/*     */         break;
/*     */       case 0:
/* 617 */         thisTimeout = -1;
/*     */         break;
/*     */       default:
/* 620 */         thisTimeout = timeout;
/*     */         break;
/*     */     } 
/*     */     
/* 624 */     gmo.setWaitInterval(thisTimeout);
/* 625 */     options |= 0x1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 633 */     options |= 0x10;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 642 */     options |= 0x200;
/*     */ 
/*     */ 
/*     */     
/* 646 */     options &= 0xFFFFFFFD;
/* 647 */     options &= 0xFFFFEFFF;
/* 648 */     options |= 0x4;
/*     */     
/* 650 */     gmo.setOptions(options);
/*     */     
/* 652 */     ProviderMessage resultMsg = getMsg(gmo, -1, false);
/* 653 */     if (Trace.isOn) {
/* 654 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "receiveAndLock(int)", resultMsg);
/*     */     }
/* 656 */     return resultMsg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unlock() throws JMSException {
/* 665 */     if (Trace.isOn) {
/* 666 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "unlock()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 671 */     WMQGMO gmo = new WMQGMO(this.env);
/*     */     
/* 673 */     int options = gmo.getOptions();
/* 674 */     options |= 0x400;
/* 675 */     gmo.setOptions(options);
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
/* 687 */     getMsg(gmo, 0, false);
/* 688 */     if (Trace.isOn)
/* 689 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSyncConsumerShadow", "unlock()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\WMQSyncConsumerShadow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */