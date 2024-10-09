/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jms.BrokerCommandFailedException;
/*      */ import com.ibm.mq.jms.NoBrokerResponseException;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.wmq.common.WMQCommonUtils;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMessage;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQSESSION;
/*      */ import java.io.IOException;
/*      */ import javax.jms.InvalidDestinationException;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class MQBrokerSubscriptionEngine
/*      */   extends MQSubscriptionEngine
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQBrokerSubscriptionEngine.java";
/*      */   
/*      */   static {
/*   61 */     if (Trace.isOn) {
/*   62 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQBrokerSubscriptionEngine.java");
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
/*   73 */   private int WSClone = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static MQBrokerSubscriptionList localSubscriptions;
/*      */ 
/*      */ 
/*      */   
/*   82 */   private int cleanupLevel = 0;
/*      */ 
/*      */   
/*   85 */   private int SLEEPTIME = 3000;
/*      */ 
/*      */   
/*      */   private static final int CLEANUP_CANCEL_DEFERRED = 1;
/*      */ 
/*      */   
/*      */   private static final int CLEANUP_CANCEL_REGISTER = 2;
/*      */ 
/*      */   
/*      */   private static final int CLEANUP_CANCEL_SUBSCRIBE = 4;
/*      */ 
/*      */   
/*      */   private static final int CLEANUP_LEAVE_ONLY = 8;
/*      */ 
/*      */   
/*      */   private static final int CLEANUP_PURGE_QUEUE = 16;
/*      */   
/*      */   private MQMsg2 durableSubscriptionMessage;
/*      */ 
/*      */   
/*      */   public MQBrokerSubscriptionEngine(MQConnection connection, String qmName) throws JMSException {
/*  106 */     super(connection);
/*  107 */     if (Trace.isOn) {
/*  108 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "<init>(MQConnection,String)", new Object[] { connection, qmName });
/*      */     }
/*      */ 
/*      */     
/*  112 */     this.WSClone = connection.getIntProperty("XMSC_WMQ_CLONE_SUPPORT");
/*  113 */     if (localSubscriptions == null && this.WSClone == 1) {
/*  114 */       localSubscriptions = new MQBrokerSubscriptionList();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  119 */     Cleanup cleanup = new Cleanup();
/*  120 */     cleanup.setCleanupLevel(connection.getIntProperty("XMSC_WMQ_CLEANUP_LEVEL"));
/*  121 */     this.cleanupLevel = cleanup.getCleanupLevel();
/*      */     
/*  123 */     if (Trace.isOn) {
/*  124 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "<init>(MQConnection,String)");
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
/*      */   public MQSubscription openSubscription(MQSession mqs, WMQDestination topic, String selector, boolean noLocal, boolean sharedQueue, String queueName) throws JMSException {
/*  151 */     if (Trace.isOn) {
/*  152 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", new Object[] { mqs, topic, selector, 
/*      */             
/*  154 */             Boolean.valueOf(noLocal), Boolean.valueOf(sharedQueue), queueName });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  163 */     MQBrokerSubscription subscription = null;
/*      */ 
/*      */     
/*  166 */     MQQueue subscriberQueue = null;
/*      */ 
/*      */     
/*  169 */     byte[] correlationID = null;
/*      */ 
/*      */     
/*  172 */     byte[] deferredMessageId = null;
/*      */ 
/*      */     
/*  175 */     String escapedClientID = WMQCommonUtils.escapeString(getMQConnection().getClientID());
/*      */ 
/*      */     
/*  178 */     int regOpts = 65536;
/*      */ 
/*      */     
/*  181 */     MQMsg2 jmsMessage = new MQMsg2();
/*      */ 
/*      */     
/*  184 */     MQBrokerMessage brokerResponse = null;
/*      */     
/*  186 */     JmqiEnvironment jmqiEnv = MQSESSION.getJmqiEnv();
/*  187 */     Pint cleanupAction = jmqiEnv.newPint(0);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  192 */     int fiqBehaviour = 1;
/*  193 */     if (topic != null) {
/*      */       
/*  195 */       fiqBehaviour = topic.getIntProperty("failIfQuiesce");
/*      */     }
/*      */     else {
/*      */       
/*  199 */       fiqBehaviour = mqs.getFailIfQuiesce();
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/*  204 */       if (sharedQueue) {
/*  205 */         regOpts |= 0x1;
/*      */       }
/*      */       
/*  208 */       String qmName = mqs.getQMName();
/*      */ 
/*      */       
/*  211 */       subscription = new MQBrokerSubscription(this, mqs, false, sharedQueue, qmName, escapedClientID, null, topic, selector, noLocal, queueName, null, null);
/*      */       
/*  213 */       subscriberQueue = openSubscriberQueue(mqs, queueName, sharedQueue, false, fiqBehaviour);
/*      */       
/*  215 */       subscription.setSubscriberQueue(subscriberQueue);
/*  216 */       subscription.setQueueName(subscriberQueue.name);
/*      */ 
/*      */       
/*  219 */       jmsMessage.setMessageType(1);
/*  220 */       jmsMessage.setReport(0);
/*      */ 
/*      */       
/*  223 */       MQPutMessageOptions pmo = new MQPutMessageOptions(true);
/*      */       
/*  225 */       if (sharedQueue) {
/*  226 */         pmo.options += 128;
/*      */       }
/*      */       
/*  229 */       if (fiqBehaviour == 1) {
/*  230 */         pmo.options |= 0x2000;
/*      */       }
/*      */ 
/*      */       
/*  234 */       correlationID = setDeferredMessage(subscription, "DeregSub", regOpts, jmsMessage, pmo);
/*  235 */       deferredMessageId = jmsMessage.getMessageId();
/*      */       
/*  237 */       if (Trace.isOn) {
/*  238 */         Trace.traceData(this, "Put deferred message, correID:", correlationID);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  245 */         jmsMessage = new MQMsg2();
/*  246 */         subscription.setCorrelationId(correlationID);
/*  247 */         if (sharedQueue) {
/*  248 */           jmsMessage.setCorrelationId(correlationID);
/*  249 */           regOpts |= 0x1;
/*      */         } 
/*      */ 
/*      */         
/*  253 */         jmsMessage.setMessageType(1);
/*  254 */         jmsMessage.setReport(0);
/*      */ 
/*      */         
/*  257 */         sendBrokerMessage(subscription, "RegSub", regOpts, jmsMessage, (MQPutMessageOptions)null);
/*      */       
/*      */       }
/*  260 */       catch (JMSException je) {
/*  261 */         if (Trace.isOn) {
/*  262 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  274 */         cleanupAction.x &= 0xFFFFFFFB;
/*  275 */         cleanupAction.x |= 0x1;
/*      */         
/*  277 */         if (Trace.isOn) {
/*  278 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */         
/*  282 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*      */         String msg;
/*      */         
/*  289 */         if (getMQConnection().getBrkVersion() == 0 || getMQConnection().getBrkVersion() == -1) {
/*  290 */           if (Trace.isOn) {
/*  291 */             Trace.traceData(this, "Receiving RFH1 ProviderMessage from broker", null);
/*      */           }
/*  293 */           brokerResponse = new RFH1BrokerMessageImpl();
/*  294 */           brokerResponse = getBrokerResponse(mqs, jmsMessage, true);
/*      */         } else {
/*  296 */           if (Trace.isOn) {
/*  297 */             Trace.traceData(this, "Receiving RFH2 ProviderMessage from broker", null);
/*      */           }
/*  299 */           brokerResponse = getBrokerResponse(mqs, jmsMessage, true);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  306 */         int reason = 0;
/*  307 */         String reasonText = null;
/*  308 */         BrokerCommandFailedException bcfe = null;
/*      */ 
/*      */         
/*  311 */         switch (Integer.parseInt(brokerResponse.get("MQPSCompCode"))) {
/*      */ 
/*      */ 
/*      */           
/*      */           case 1:
/*  316 */             cleanupAction.x |= 0x4;
/*  317 */             cleanupAction.x |= 0x1;
/*      */             
/*  319 */             reason = Integer.parseInt(brokerResponse.get("MQPSReason"));
/*  320 */             reasonText = brokerResponse.get("MQPSReasonText");
/*      */             
/*  322 */             bcfe = new BrokerCommandFailedException("Broker command failed: " + reasonText + " Reason code " + reason, "MQRCCF" + Integer.toString(reason));
/*  323 */             bcfe.setReason(reason);
/*  324 */             if (Trace.isOn) {
/*  325 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)bcfe, 2);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  330 */             throw bcfe;
/*      */ 
/*      */ 
/*      */           
/*      */           case 2:
/*  335 */             cleanupAction.x &= 0xFFFFFFFB;
/*  336 */             cleanupAction.x |= 0x1;
/*      */             
/*  338 */             reason = Integer.parseInt(brokerResponse.get("MQPSReason"));
/*  339 */             reasonText = brokerResponse.get("MQPSReasonText");
/*      */             
/*  341 */             bcfe = new BrokerCommandFailedException("Broker command failed: " + reasonText + " Reason code " + reason, "MQRCCF" + Integer.toString(reason));
/*  342 */             bcfe.setReason(reason);
/*      */             
/*  344 */             if (reason == 2035) {
/*  345 */               bcfe.setUserId(brokerResponse.get("MQPSUserId"));
/*      */             }
/*      */             
/*  348 */             if (Trace.isOn) {
/*  349 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)bcfe, 3);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  354 */             throw bcfe;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 0:
/*  360 */             if (Trace.isOn) {
/*  361 */               Trace.traceData(this, "non-durable RegSub command processed ok", null);
/*      */             }
/*  363 */             cleanupAction.x &= 0xFFFFFFFB;
/*  364 */             cleanupAction.x &= 0xFFFFFFFE;
/*      */             break;
/*      */ 
/*      */           
/*      */           default:
/*  369 */             cleanupAction.x |= 0x4;
/*  370 */             cleanupAction.x |= 0x1;
/*      */             
/*  372 */             if (Trace.isOn) {
/*  373 */               Trace.traceData(this, "unrecognized completion code: " + brokerResponse.get("MQPSCompCode"), null);
/*      */             }
/*  375 */             msg = ConfigEnvironment.getErrorMessage("MQJMS1087");
/*  376 */             bcfe = new BrokerCommandFailedException(msg, "MQRCCF" + brokerResponse.get("MQPSCompCode"));
/*      */             
/*  378 */             if (Trace.isOn) {
/*  379 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)bcfe, 4);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  384 */             throw bcfe;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  391 */       } catch (NoBrokerResponseException nbre) {
/*  392 */         if (Trace.isOn) {
/*  393 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)nbre, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  398 */         cleanupAction.x |= 0x4;
/*  399 */         cleanupAction.x |= 0x1;
/*      */         
/*  401 */         if (Trace.isOn) {
/*  402 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)nbre, 5);
/*      */         }
/*      */ 
/*      */         
/*  406 */         throw nbre;
/*      */       } 
/*      */       
/*  409 */       subscription.setDeferredMsgId(deferredMessageId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  416 */     catch (JMSException je) {
/*  417 */       if (Trace.isOn) {
/*  418 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  423 */       if ((cleanupAction.x & 0x4) != 0) {
/*  424 */         regOpts = 1;
/*  425 */         jmsMessage.clearMessageData();
/*      */         
/*  427 */         sendBrokerMessage(subscription, "DeregSub", regOpts, jmsMessage, (MQPutMessageOptions)null);
/*      */       } 
/*      */ 
/*      */       
/*  431 */       if ((cleanupAction.x & 0x1) != 0)
/*      */       {
/*  433 */         cancelDeferredMessage(mqs, deferredMessageId);
/*      */       }
/*      */ 
/*      */       
/*  437 */       if (subscriberQueue != null && subscriberQueue.isOpen()) {
/*      */         try {
/*  439 */           if (Trace.isOn) {
/*  440 */             Trace.traceData(this, "deleting subscription queue", null);
/*      */           }
/*  442 */           if (!sharedQueue) {
/*  443 */             subscriberQueue.closeOptions = 2;
/*      */           }
/*  445 */           subscriberQueue.close();
/*      */         }
/*  447 */         catch (MQException e) {
/*  448 */           if (Trace.isOn) {
/*  449 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)e, 4);
/*      */           }
/*      */ 
/*      */           
/*  453 */           if (Trace.isOn) {
/*  454 */             Trace.traceData(this, "queue delete failed", null);
/*      */           }
/*  456 */           String msg = ConfigEnvironment.getErrorMessage("MQJMS2000");
/*  457 */           BrokerCommandFailedException bcfe = new BrokerCommandFailedException(msg, "MQJMS2000");
/*  458 */           bcfe.setLinkedException((Exception)e);
/*  459 */           if (Trace.isOn) {
/*  460 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)bcfe, 6);
/*      */           }
/*      */ 
/*      */           
/*  464 */           throw bcfe;
/*      */         } 
/*      */       }
/*      */       
/*  468 */       if (Trace.isOn) {
/*  469 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)je, 7);
/*      */       }
/*      */ 
/*      */       
/*  473 */       throw je;
/*      */     } 
/*      */     
/*  476 */     if (Trace.isOn) {
/*  477 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", subscription);
/*      */     }
/*      */     
/*  480 */     return subscription;
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
/*      */   public void closeSubscription(MQSubscription sub) throws JMSException {
/*  492 */     if (Trace.isOn) {
/*  493 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeSubscription(MQSubscription)", new Object[] { sub });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  499 */     int regOpts = 0;
/*      */ 
/*      */     
/*  502 */     MQMsg2 jmsMessage = new MQMsg2();
/*  503 */     MQQueue subscriberQueue = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  510 */       if (!(sub instanceof MQBrokerSubscription)) {
/*  511 */         JMSException je = ConfigEnvironment.newException("MQJMS3049");
/*  512 */         if (Trace.isOn) {
/*  513 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeSubscription(MQSubscription)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */         
/*  517 */         throw je;
/*      */       } 
/*  519 */       MQBrokerSubscription subscription = (MQBrokerSubscription)sub;
/*      */       
/*  521 */       if (subscription.isClosed() || subscription.getMQSession() == null) {
/*      */ 
/*      */ 
/*      */         
/*  525 */         if (Trace.isOn) {
/*  526 */           Trace.traceData(this, "null or closed session. Subscription should already be closed. Returning", null);
/*      */         }
/*  528 */         if (Trace.isOn) {
/*  529 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeSubscription(MQSubscription)", 1);
/*      */         }
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  536 */       subscriberQueue = subscription.getSubscriberQueue();
/*      */ 
/*      */       
/*  539 */       regOpts |= 0x10000;
/*      */ 
/*      */       
/*  542 */       jmsMessage.setMessageType(1);
/*  543 */       jmsMessage.setReport(0);
/*      */       
/*  545 */       if (subscription.isSharedQueue()) {
/*      */ 
/*      */         
/*  548 */         regOpts++;
/*  549 */         jmsMessage.setCorrelationId(subscription.getCorrelationId());
/*      */       } 
/*      */       
/*  552 */       sendBrokerMessage(subscription, "DeregSub", regOpts, jmsMessage, (MQPutMessageOptions)null);
/*      */       
/*  554 */       if (subscription.getMQSession() == null && Trace.isOn) {
/*  555 */         Trace.traceData(this, "session is null", null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  562 */       cleanupUnconsumedMessages(sub.getMQSession(), sub.isSharedQueue(), sub.getQueueName(), sub.getCorrelationId(), jmsMessage);
/*      */ 
/*      */ 
/*      */       
/*  566 */       if (sub.isSharedQueue() && subscriberQueue != null && subscriberQueue.isOpen()) {
/*      */         try {
/*  568 */           if (Trace.isOn) {
/*  569 */             Trace.traceData(this, "closing subscription queue", null);
/*      */           }
/*  571 */           subscriberQueue.close();
/*      */         }
/*  573 */         catch (MQException e) {
/*  574 */           if (Trace.isOn) {
/*  575 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeSubscription(MQSubscription)", (Throwable)e, 1);
/*      */           }
/*      */ 
/*      */           
/*  579 */           JMSException je = ConfigEnvironment.newException("MQJMS2000");
/*  580 */           je.setLinkedException((Exception)e);
/*  581 */           if (Trace.isOn) {
/*  582 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeSubscription(MQSubscription)", (Throwable)je, 2);
/*      */           }
/*      */ 
/*      */           
/*  586 */           throw je;
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/*  591 */       cancelDeferredMessage(subscription.getMQSession(), subscription.getDeferredMsgId());
/*      */     }
/*  593 */     catch (JMSException je) {
/*  594 */       if (Trace.isOn) {
/*  595 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeSubscription(MQSubscription)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */       
/*  599 */       if (Trace.isOn) {
/*  600 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeSubscription(MQSubscription)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */       
/*  604 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/*  608 */     if (Trace.isOn) {
/*  609 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeSubscription(MQSubscription)", 2);
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
/*      */   public MQSubscription openDurableSubscription(MQSession mqs, WMQDestination topic, String selector, boolean noLocal, boolean sharedQueue, String queueNameP, String subName) throws JMSException {
/*  640 */     if (Trace.isOn) {
/*  641 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", new Object[] { mqs, topic, selector, 
/*      */             
/*  643 */             Boolean.valueOf(noLocal), Boolean.valueOf(sharedQueue), queueNameP, subName });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  648 */     String queueName = queueNameP;
/*  649 */     String fullName = null;
/*  650 */     String escapedQMgrName = null;
/*  651 */     String escapedClientID = null;
/*  652 */     String escapedSubName = null;
/*  653 */     String escapedSelector = null;
/*  654 */     String userSubData = null;
/*      */ 
/*      */     
/*  657 */     MQBrokerSubscription testSubscription = null;
/*      */     
/*  659 */     MQBrokerSubscription newSubscription = null;
/*      */     
/*  661 */     MQBrokerMessage brokerMsg = null;
/*      */     
/*  663 */     MQBrokerMessage brokerResponse = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  668 */     byte[] correlationId1 = null;
/*      */     
/*  670 */     byte[] correlationId2 = null;
/*  671 */     byte[] deferredMsgId = null;
/*      */     
/*  673 */     JmqiEnvironment jmqiEnv = MQSESSION.getJmqiEnv();
/*      */     
/*  675 */     Pint cleanupAction = jmqiEnv.newPint(0);
/*      */ 
/*      */ 
/*      */     
/*  679 */     boolean locallySubscribed = false;
/*  680 */     String receivedSubName = null;
/*  681 */     String receivedQmgrName = null;
/*  682 */     String receivedQueueName = null;
/*  683 */     String receivedUserSubData = null;
/*  684 */     String receivedTopic = null;
/*      */     
/*  686 */     this.durableSubscriptionMessage = null;
/*      */ 
/*      */ 
/*      */     
/*  690 */     if (Trace.isOn && 
/*  691 */       this.WSClone == 1) {
/*  692 */       Trace.traceData(this, "WebSphere clones enabled", null);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  697 */     int fiqBehaviour = 1;
/*  698 */     if (topic != null) {
/*      */       
/*  700 */       fiqBehaviour = topic.getIntProperty("failIfQuiesce");
/*      */     }
/*  702 */     else if (mqs != null) {
/*      */       
/*  704 */       fiqBehaviour = mqs.getFailIfQuiesce();
/*      */     } 
/*      */     
/*      */     try {
/*      */       BrokerCommandFailedException brokerCommandFailedException1;
/*      */       JMSException je;
/*      */       BrokerCommandFailedException bcfe;
/*  711 */       String clientID = getMQConnection().getClientID();
/*      */ 
/*      */ 
/*      */       
/*  715 */       if (clientID == null) {
/*  716 */         JMSException jMSException = ConfigEnvironment.newException("MQJMS3024");
/*  717 */         if (Trace.isOn) {
/*  718 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)jMSException, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  723 */         throw jMSException;
/*      */       } 
/*      */ 
/*      */       
/*  727 */       testSubscription = new MQBrokerSubscription(this, mqs, true, sharedQueue, mqs.getQMName(), clientID, subName, topic, selector, noLocal, queueName, null, null);
/*      */ 
/*      */ 
/*      */       
/*  731 */       escapedQMgrName = WMQCommonUtils.escapeString(mqs.getQMName());
/*  732 */       escapedClientID = WMQCommonUtils.escapeString(getMQConnection().getClientID());
/*  733 */       escapedSubName = WMQCommonUtils.escapeString(subName);
/*  734 */       fullName = "JMS:" + escapedQMgrName + ":" + escapedClientID + ":" + escapedSubName;
/*  735 */       if (Trace.isOn) {
/*  736 */         Trace.traceData(this, "Fullname = '" + fullName + "'", null);
/*      */       }
/*      */ 
/*      */       
/*  740 */       if (selector != null && !selector.trim().equals("")) {
/*  741 */         escapedSelector = WMQCommonUtils.escapeString(selector);
/*  742 */         userSubData = "sel=\"" + escapedSelector + "\"";
/*      */       } else {
/*  744 */         userSubData = "";
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  763 */         if (Trace.isOn) {
/*  764 */           Trace.traceData(this, "Setting deferred message", null);
/*      */         }
/*  766 */         brokerMsg = createProviderMessage(mqs, topic, fullName, testSubscription.getFilter());
/*      */         
/*  768 */         if (Trace.isOn) {
/*  769 */           Trace.traceData(this, "creating put message options", null);
/*      */         }
/*  771 */         MQPutMessageOptions pmo = new MQPutMessageOptions(true);
/*      */         
/*  773 */         MQMsg2 msg = new MQMsg2();
/*      */         
/*  775 */         msg.setMessageType(8);
/*      */ 
/*      */         
/*  778 */         pmo.options += 128;
/*      */         
/*  780 */         if (Trace.isOn) {
/*  781 */           Trace.traceData(this, "setting deferred message", null);
/*      */         }
/*  783 */         correlationId1 = setDeferredMessage(mqs, brokerMsg, msg, pmo, true, fiqBehaviour);
/*  784 */         deferredMsgId = msg.getMessageId();
/*  785 */         if (Trace.isOn) {
/*  786 */           Trace.traceData(this, "got correlID", correlationId1);
/*  787 */           Trace.traceData(this, "got MessgeID", deferredMsgId);
/*      */         }
/*      */       
/*      */       }
/*  791 */       catch (JMSException jMSException) {
/*  792 */         if (Trace.isOn) {
/*  793 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)jMSException, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  798 */         cleanupAction.x &= 0xFFFFFFFE;
/*  799 */         cleanupAction.x &= 0xFFFFFFFD;
/*      */         
/*  801 */         if (Trace.isOn) {
/*  802 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)jMSException, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  807 */         throw jMSException;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  813 */       if (this.WSClone == 1) {
/*      */         
/*      */         try {
/*  816 */           localSubscriptions.getSubscription(fullName, mqs);
/*  817 */           locallySubscribed = true;
/*      */         }
/*  819 */         catch (JMSException jMSException) {
/*  820 */           if (Trace.isOn) {
/*  821 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)jMSException, 2);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  826 */           cleanupAction.x |= 0x9;
/*      */ 
/*      */           
/*  829 */           if (Trace.isOn) {
/*  830 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)jMSException, 3);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  835 */           throw jMSException;
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  844 */       if (sharedQueue) {
/*  845 */         brokerResponse = sharedQueueRegister(mqs, testSubscription, correlationId1, cleanupAction);
/*      */       } else {
/*  847 */         brokerResponse = nonSharedQueueRegister(mqs, testSubscription, correlationId1, cleanupAction, queueName, fiqBehaviour);
/*  848 */         queueName = testSubscription.getQueueName();
/*      */       } 
/*      */       
/*  851 */       String tmp = brokerResponse.get("MQPSCorrelId");
/*  852 */       if (tmp != null) {
/*  853 */         correlationId2 = Utils.hexToBytes(tmp);
/*      */       }
/*      */       
/*  856 */       int reason = Integer.parseInt(brokerResponse.get("MQPSReason"));
/*  857 */       String reasonText = brokerResponse.get("MQPSReasonText");
/*      */       
/*  859 */       if (Trace.isOn) {
/*  860 */         Trace.traceData(this, "got response from exclusive register", null);
/*      */       }
/*      */       
/*  863 */       switch (Integer.parseInt(brokerResponse.get("MQPSCompCode"))) {
/*      */ 
/*      */         
/*      */         case 1:
/*  867 */           if (Trace.isOn) {
/*  868 */             Trace.traceData(this, "broker returned with warning", null);
/*      */           }
/*  870 */           cleanupAction.x |= 0x9;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  878 */           if (reason == 3157) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  883 */             if (!sharedQueue) {
/*      */               break;
/*      */             }
/*      */           } else {
/*  887 */             cleanupAction.x |= 0x2;
/*      */           } 
/*      */ 
/*      */           
/*  891 */           brokerCommandFailedException1 = new BrokerCommandFailedException("Broker command failed: " + reasonText + " Reason code " + reason, "MQCCF" + Integer.toString(reason));
/*  892 */           brokerCommandFailedException1.setReason(reason);
/*      */           
/*  894 */           if (Trace.isOn) {
/*  895 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)brokerCommandFailedException1, 4);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  900 */           throw brokerCommandFailedException1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/*  909 */           if (Trace.isOn) {
/*  910 */             Trace.traceData(this, "broker command failed", null);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  915 */           if (this.WSClone == 1 && (reason == 3155 || reason == 3156)) {
/*      */             
/*  917 */             MQSubscription traceRet1 = webSphereCloneHandling(mqs, topic, sharedQueue, queueName, userSubData, testSubscription, brokerResponse, correlationId1, correlationId2, deferredMsgId, cleanupAction, fiqBehaviour, reason);
/*      */             
/*  919 */             if (Trace.isOn) {
/*  920 */               Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", traceRet1, 1);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  925 */             return traceRet1;
/*      */           } 
/*      */           
/*  928 */           cleanupAction.x |= 0x1;
/*      */           
/*  930 */           reason = Integer.parseInt(brokerResponse.get("MQPSReason"));
/*  931 */           reasonText = brokerResponse.get("MQPSReasonText");
/*      */ 
/*      */           
/*  934 */           bcfe = new BrokerCommandFailedException("Broker command failed: " + reasonText + " Reason code " + reason, "MQCCF" + Integer.toString(reason));
/*  935 */           bcfe.setReason(reason);
/*      */           
/*  937 */           if (Trace.isOn) {
/*  938 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)bcfe, 5);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  943 */           throw bcfe;
/*      */ 
/*      */ 
/*      */         
/*      */         case 0:
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/*  952 */           cleanupAction.x |= 0xB;
/*      */           
/*  954 */           if (Trace.isOn) {
/*  955 */             Trace.traceData(this, "unrecognized completion code: " + brokerResponse.get("MQPSCompCode"), null);
/*      */           }
/*  957 */           je = ConfigEnvironment.newException("MQJMS1087", "MQRCCF: " + brokerResponse.get("MQPSCompCode"));
/*      */           
/*  959 */           if (Trace.isOn) {
/*  960 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 6);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  965 */           throw je;
/*      */       } 
/*      */ 
/*      */       
/*  969 */       testSubscription.setCorrelationId(correlationId2);
/*      */       
/*  971 */       if (Trace.isOn) {
/*      */         try {
/*  973 */           receivedSubName = brokerResponse.get("MQPSSubName");
/*      */         }
/*  975 */         catch (Exception e) {
/*  976 */           if (Trace.isOn) {
/*  977 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", e, 3);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  982 */           Trace.traceData(this, "Couldn't get subName", null);
/*      */         } 
/*  984 */         if (receivedSubName == null) {
/*  985 */           Trace.traceData(this, "received null subName", null);
/*      */         } else {
/*  987 */           receivedSubName = receivedSubName.trim();
/*  988 */           Trace.traceData(this, "got subName         = '" + receivedSubName + "'", null);
/*      */         } 
/*  990 */         Trace.traceData(this, "wanted subName      = '" + fullName + "'", null);
/*      */       } 
/*      */       
/*  993 */       receivedQmgrName = brokerResponse.get("MQPSQMgrName").trim();
/*  994 */       if (Trace.isOn) {
/*  995 */         Trace.traceData(this, "got QueueManager    = '" + receivedQmgrName + "'", null);
/*  996 */         Trace.traceData(this, "wanted QueueManager = '" + escapedQMgrName + "'", null);
/*      */       } 
/*  998 */       receivedQueueName = brokerResponse.get("MQPSQName").trim();
/*  999 */       if (Trace.isOn) {
/* 1000 */         Trace.traceData(this, "got Queue           = '" + receivedQueueName + "'", null);
/* 1001 */         Trace.traceData(this, "wanted Queue        = '" + queueName + "'", null);
/*      */       } 
/* 1003 */       receivedUserSubData = brokerResponse.get("MQPSSubUserData");
/* 1004 */       if (receivedUserSubData == null) {
/* 1005 */         receivedUserSubData = "";
/*      */       }
/* 1007 */       if (Trace.isOn) {
/* 1008 */         Trace.traceData(this, "got UserSubData      = '" + receivedUserSubData + "'", null);
/* 1009 */         Trace.traceData(this, "wanted UserSubData   = '" + userSubData + "'", null);
/*      */       } 
/* 1011 */       receivedTopic = brokerResponse.get("MQPSTopic").trim();
/* 1012 */       if (Trace.isOn) {
/* 1013 */         Trace.traceData(this, "got Topic            = '" + receivedTopic + "'", null);
/* 1014 */         Trace.traceData(this, "wanted Topic         = '" + topic.getName() + "'", null);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1019 */       if (!mqs.getQMName().equals(receivedQmgrName) || !queueName.equals(receivedQueueName)) {
/*      */ 
/*      */         
/* 1022 */         cleanupAction.x |= 0xB;
/*      */         
/* 1024 */         String specified = mqs.getQMName() + ":" + queueName;
/* 1025 */         String original = receivedQmgrName + ":" + receivedQueueName;
/*      */         
/* 1027 */         JMSException jMSException = ConfigEnvironment.newException("MQJMS3022", specified, original);
/*      */         
/* 1029 */         if (Trace.isOn) {
/* 1030 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)jMSException, 7);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1035 */         throw jMSException;
/*      */       } 
/*      */ 
/*      */       
/* 1039 */       if (!userSubData.equals(receivedUserSubData) || !topicMatches(topic, receivedTopic)) {
/*      */         
/* 1041 */         if (Trace.isOn) {
/* 1042 */           Trace.traceData(this, "Subscription exists, but requires updating", null);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 1049 */           if (Trace.isOn) {
/* 1050 */             Trace.traceData(this, "subscription needs to drop and recreate a previous subscription", null);
/*      */           }
/*      */           
/* 1053 */           testSubscription.setDeferredMsgId(deferredMsgId);
/* 1054 */           closeDurableSubscription(testSubscription);
/* 1055 */           durableUnsubscribe(mqs, subName, true);
/*      */ 
/*      */           
/* 1058 */           newSubscription = (MQBrokerSubscription)openDurableSubscription(mqs, topic, selector, noLocal, sharedQueue, queueName, subName);
/*      */         
/*      */         }
/* 1061 */         catch (JMSException jMSException) {
/* 1062 */           if (Trace.isOn) {
/* 1063 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)jMSException, 4);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1068 */           cleanupAction.x |= 0xB;
/*      */           
/* 1070 */           if (Trace.isOn) {
/* 1071 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)jMSException, 8);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1076 */           throw jMSException;
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/* 1082 */       else if (brokerResponse.isOptionSet("MQPSRegOpts", 32)) {
/*      */         
/* 1084 */         if (Trace.isOn) {
/* 1085 */           Trace.traceData(this, "subscription needs to recover from previous update error", null);
/*      */         }
/*      */         
/*      */         try {
/* 1089 */           testSubscription.setDeferredMsgId(deferredMsgId);
/* 1090 */           closeDurableSubscription(testSubscription);
/* 1091 */           durableUnsubscribe(mqs, subName, true);
/*      */ 
/*      */           
/* 1094 */           newSubscription = (MQBrokerSubscription)openDurableSubscription(mqs, topic, escapedSelector, noLocal, sharedQueue, queueName, subName);
/*      */         }
/* 1096 */         catch (JMSException jMSException) {
/* 1097 */           if (Trace.isOn) {
/* 1098 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)jMSException, 5);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1104 */           cleanupAction.x |= 0xB;
/*      */           
/* 1106 */           if (Trace.isOn) {
/* 1107 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)jMSException, 9);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1112 */           throw jMSException;
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1118 */         if (Trace.isOn) {
/* 1119 */           Trace.traceData(this, "subscription exists as requested.", null);
/*      */         }
/*      */         
/* 1122 */         newSubscription = testSubscription;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1127 */         if (this.WSClone == 1) {
/*      */           
/*      */           try {
/* 1130 */             migrateToShared(mqs, sharedQueue, newSubscription);
/*      */           
/*      */           }
/* 1133 */           catch (JMSException jMSException) {
/* 1134 */             if (Trace.isOn) {
/* 1135 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)jMSException, 6);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 1140 */             if (Trace.isOn) {
/* 1141 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)jMSException, 10);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 1146 */             throw jMSException;
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1152 */         if (newSubscription.getSubscriberQueue() == null) {
/* 1153 */           newSubscription.setSubscriberQueue(openSubscriberQueue(mqs, queueName, sharedQueue, true, fiqBehaviour));
/*      */         }
/*      */         
/* 1156 */         newSubscription.setDeferredMsgId(deferredMsgId);
/*      */       } 
/*      */ 
/*      */       
/* 1160 */       if (Trace.isOn) {
/* 1161 */         if (sharedQueue) {
/* 1162 */           Trace.traceData(this, "got subscription with correlID", newSubscription.getCorrelationId());
/*      */         } else {
/* 1164 */           Trace.traceData(this, "got dynamic queue subscrpition", null);
/*      */         } 
/*      */       }
/*      */       
/* 1168 */       if (Trace.isOn) {
/* 1169 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", newSubscription, 2);
/*      */       }
/*      */ 
/*      */       
/* 1173 */       return newSubscription;
/*      */     
/*      */     }
/* 1176 */     catch (JMSException je) {
/* 1177 */       if (Trace.isOn) {
/* 1178 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 7);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1185 */       if (Trace.isOn) {
/* 1186 */         Trace.traceData(this, "cleaning up after exception", null);
/*      */         
/* 1188 */         if ((cleanupAction.x & 0x1) != 0) {
/* 1189 */           Trace.traceData(this, "must cancel deferred message", null);
/*      */         }
/* 1191 */         if ((cleanupAction.x & 0x2) != 0) {
/* 1192 */           Trace.traceData(this, "must cancel register broker command", null);
/*      */         }
/* 1194 */         if ((cleanupAction.x & 0x8) == 0) {
/* 1195 */           Trace.traceData(this, "closing subscriber queue", null);
/*      */         }
/* 1197 */         if ((cleanupAction.x & 0x10) == 0) {
/* 1198 */           Trace.traceData(this, "Purging subscriber queue", null);
/*      */         }
/*      */       } 
/*      */       
/* 1202 */       if ((cleanupAction.x & 0x2) != 0) {
/*      */         try {
/* 1204 */           int regOpts = 524288;
/* 1205 */           this.durableSubscriptionMessage.clearMessageData();
/* 1206 */           this.durableSubscriptionMessage.setMessageType(8);
/*      */           
/* 1208 */           sendBrokerMessage(testSubscription, "DeregSub", regOpts, this.durableSubscriptionMessage, (MQPutMessageOptions)null);
/*      */         
/*      */         }
/* 1211 */         catch (JMSException je2) {
/* 1212 */           if (Trace.isOn) {
/* 1213 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je2, 8);
/*      */           }
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1223 */       if ((cleanupAction.x & 0x1) != 0) {
/*      */         try {
/* 1225 */           cancelDeferredMessage(mqs, deferredMsgId);
/*      */         }
/* 1227 */         catch (JMSException je2) {
/* 1228 */           if (Trace.isOn) {
/* 1229 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je2, 9);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1236 */           if (Trace.isOn) {
/* 1237 */             Trace.traceData(this, "Exception cancelling deferred message", null);
/*      */           }
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 1243 */       if (this.WSClone == 1 && locallySubscribed) {
/*      */         try {
/* 1245 */           localSubscriptions.removeSubscription(fullName);
/*      */         }
/* 1247 */         catch (Exception e) {
/* 1248 */           if (Trace.isOn) {
/* 1249 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", e, 11);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1254 */           if (Trace.isOn) {
/* 1255 */             Trace.traceData(this, "error encountered while removing subscription entry from localSubscriptions table", null);
/*      */           }
/*      */         } 
/*      */       }
/*      */       
/* 1260 */       if (Trace.isOn) {
/* 1261 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 11);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1266 */       throw je;
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
/*      */   private boolean topicMatches(WMQDestination topic, String receivedTopic) {
/* 1281 */     if (Trace.isOn) {
/* 1282 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "topicMatches(WMQDestination,String)", new Object[] { topic, receivedTopic });
/*      */     }
/*      */ 
/*      */     
/* 1286 */     String topicName = topic.getName();
/* 1287 */     if (topicName.length() != receivedTopic.length()) {
/* 1288 */       if (Trace.isOn) {
/* 1289 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "topicMatches(WMQDestination,String)", 
/* 1290 */             Boolean.valueOf(false), 1);
/*      */       }
/* 1292 */       return false;
/*      */     } 
/*      */     
/* 1295 */     char[] topicNameCharArray = topicName.toCharArray();
/* 1296 */     char[] receivedTopicNameCharArray = receivedTopic.toCharArray();
/*      */     
/* 1298 */     for (int i = 0; i < topicNameCharArray.length; i++) {
/* 1299 */       if (topicNameCharArray[i] != receivedTopicNameCharArray[i])
/*      */       {
/* 1301 */         if (topicNameCharArray[i] != '*' || receivedTopicNameCharArray[i] != '#') {
/*      */ 
/*      */           
/* 1304 */           if (Trace.isOn) {
/* 1305 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "topicMatches(WMQDestination,String)", 
/*      */                 
/* 1307 */                 Boolean.valueOf(false), 2);
/*      */           }
/* 1309 */           return false;
/*      */         } 
/*      */       }
/*      */     } 
/* 1313 */     if (Trace.isOn) {
/* 1314 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "topicMatches(WMQDestination,String)", 
/* 1315 */           Boolean.valueOf(true), 3);
/*      */     }
/* 1317 */     return true;
/*      */   }
/*      */   
/*      */   private MQBrokerMessage sharedQueueRegister(MQSession mqs, MQBrokerSubscription testSubscription, byte[] correlationId1, Pint cleanupAction) throws JMSException {
/* 1321 */     if (Trace.isOn) {
/* 1322 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sharedQueueRegister(MQSession,MQBrokerSubscription,byte [ ],Pint)", new Object[] { mqs, testSubscription, correlationId1, cleanupAction });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1327 */     this.durableSubscriptionMessage = registerExclusive(testSubscription, correlationId1, cleanupAction);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1332 */     MQBrokerMessage brokerResponse = null;
/*      */ 
/*      */     
/*      */     try {
/* 1336 */       if (Trace.isOn) {
/* 1337 */         Trace.traceData(this, "getting broker response", null);
/*      */       }
/*      */       
/* 1340 */       brokerResponse = getBrokerResponse(mqs, this.durableSubscriptionMessage, true);
/*      */ 
/*      */     
/*      */     }
/* 1344 */     catch (JMSException je) {
/* 1345 */       if (Trace.isOn) {
/* 1346 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sharedQueueRegister(MQSession,MQBrokerSubscription,byte [ ],Pint)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 1350 */       cleanupAction.x |= 0x1;
/* 1351 */       if (je instanceof NoBrokerResponseException)
/*      */       {
/* 1353 */         cleanupAction.x |= 0xA;
/*      */       }
/* 1355 */       if (Trace.isOn) {
/* 1356 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sharedQueueRegister(MQSession,MQBrokerSubscription,byte [ ],Pint)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 1360 */       throw je;
/*      */     } 
/*      */     
/* 1363 */     if (Trace.isOn) {
/* 1364 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sharedQueueRegister(MQSession,MQBrokerSubscription,byte [ ],Pint)", brokerResponse);
/*      */     }
/*      */     
/* 1367 */     return brokerResponse;
/*      */   }
/*      */ 
/*      */   
/*      */   private MQBrokerMessage nonSharedQueueRegister(MQSession mqs, MQBrokerSubscription testSubscription, byte[] correlationId1, Pint cleanupAction, String queueName, int fiqBehaviour) throws JMSException, BrokerCommandFailedException {
/* 1372 */     if (Trace.isOn) {
/* 1373 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "nonSharedQueueRegister(MQSession,MQBrokerSubscription,byte [ ],Pint,String,int)", new Object[] { mqs, testSubscription, correlationId1, cleanupAction, queueName, 
/*      */ 
/*      */             
/* 1376 */             Integer.valueOf(fiqBehaviour) });
/*      */     }
/*      */     
/* 1379 */     MQQueue subscriberQueue = openSubscriberQueue(mqs, queueName, false, true, fiqBehaviour);
/* 1380 */     String subscriberQueueName = subscriberQueue.name.trim();
/*      */     
/* 1382 */     testSubscription.setSubscriberQueue(subscriberQueue);
/* 1383 */     testSubscription.setQueueName(subscriberQueueName);
/* 1384 */     if (Trace.isOn) {
/* 1385 */       Trace.traceData(this, "opened new dynamic queue: " + subscriberQueueName, null);
/*      */     }
/*      */     
/* 1388 */     cleanupAction.x |= 0x10;
/*      */     
/* 1390 */     this.durableSubscriptionMessage = registerExclusive(testSubscription, correlationId1, cleanupAction);
/*      */     
/* 1392 */     if (Trace.isOn) {
/* 1393 */       Trace.traceData(this, "using dynamic queues. Checking initial broker response", null);
/*      */     }
/*      */     
/* 1396 */     MQBrokerMessage brokerResponse = null;
/*      */ 
/*      */     
/*      */     try {
/* 1400 */       brokerResponse = getBrokerResponse(mqs, this.durableSubscriptionMessage, true);
/*      */     
/*      */     }
/* 1403 */     catch (JMSException je) {
/* 1404 */       if (Trace.isOn) {
/* 1405 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "nonSharedQueueRegister(MQSession,MQBrokerSubscription,byte [ ],Pint,String,int)", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1410 */       if (je instanceof NoBrokerResponseException) {
/*      */         
/* 1412 */         cleanupAction.x |= 0x1;
/* 1413 */         cleanupAction.x |= 0x2;
/* 1414 */         cleanupAction.x |= 0x8;
/*      */       } else {
/* 1416 */         cleanupAction.x |= 0x1;
/* 1417 */         cleanupAction.x &= 0xFFFFFFFD;
/* 1418 */         cleanupAction.x &= 0xFFFFFFF7;
/*      */       } 
/* 1420 */       if (Trace.isOn) {
/* 1421 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "nonSharedQueueRegister(MQSession,MQBrokerSubscription,byte [ ],Pint,String,int)", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1426 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/* 1430 */     int reason = Integer.parseInt(brokerResponse.get("MQPSReason"));
/* 1431 */     String reasonText = brokerResponse.get("MQPSReasonText");
/*      */     
/* 1433 */     if (reason != 0 && (this.WSClone != 1 || (reason != 3155 && reason != 3156))) {
/* 1434 */       if (Trace.isOn) {
/* 1435 */         Trace.traceData(this, "exclusive register failed", null);
/*      */       }
/*      */       
/* 1438 */       cleanupAction.x |= 0x1;
/* 1439 */       cleanupAction.x &= 0xFFFFFFFD;
/* 1440 */       cleanupAction.x &= 0xFFFFFFF7;
/*      */       
/* 1442 */       reason = Integer.parseInt(brokerResponse.get("MQPSReason"));
/* 1443 */       reasonText = brokerResponse.get("MQPSReasonText");
/*      */       
/* 1445 */       BrokerCommandFailedException bcfe = new BrokerCommandFailedException("Broker command failed: " + reasonText + " Reason code " + reason, "MQCCF" + Integer.toString(reason));
/* 1446 */       bcfe.setReason(reason);
/*      */       
/* 1448 */       if (Trace.isOn) {
/* 1449 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "nonSharedQueueRegister(MQSession,MQBrokerSubscription,byte [ ],Pint,String,int)", (Throwable)bcfe, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1454 */       throw bcfe;
/*      */     } 
/*      */ 
/*      */     
/* 1458 */     String receivedQueueName1 = brokerResponse.get("MQPSQName").trim();
/* 1459 */     if (Trace.isOn) {
/* 1460 */       Trace.traceData(this, "register succeeded to queue: " + receivedQueueName1, null);
/*      */     }
/*      */     
/* 1463 */     if (!receivedQueueName1.equals(subscriberQueueName)) {
/*      */       
/*      */       try {
/* 1466 */         if (Trace.isOn) {
/* 1467 */           Trace.traceData(this, "closing subscription queue", null);
/*      */         }
/*      */         
/* 1470 */         subscriberQueue.closeOptions = 2;
/* 1471 */         subscriberQueue.close();
/*      */ 
/*      */         
/* 1474 */         testSubscription.setQueueName(receivedQueueName1);
/* 1475 */         subscriberQueue = openSubscriberQueue(mqs, receivedQueueName1, false, true, fiqBehaviour);
/* 1476 */         testSubscription.setSubscriberQueue(subscriberQueue);
/*      */       }
/* 1478 */       catch (MQException mqe) {
/* 1479 */         if (Trace.isOn) {
/* 1480 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "nonSharedQueueRegister(MQSession,MQBrokerSubscription,byte [ ],Pint,String,int)", (Throwable)mqe, 2);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1489 */     if (Trace.isOn) {
/* 1490 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "nonSharedQueueRegister(MQSession,MQBrokerSubscription,byte [ ],Pint,String,int)", brokerResponse);
/*      */     }
/*      */ 
/*      */     
/* 1494 */     return brokerResponse;
/*      */   }
/*      */   
/*      */   private MQMsg2 registerExclusive(MQBrokerSubscription testSubscription, byte[] correlationId1, Pint cleanupAction) throws JMSException {
/* 1498 */     if (Trace.isOn) {
/* 1499 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "registerExclusive(MQBrokerSubscription,byte [ ],Pint)", new Object[] { testSubscription, correlationId1, cleanupAction });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1504 */     MQMsg2 jmsMessage = null;
/*      */ 
/*      */     
/*      */     try {
/* 1508 */       if (Trace.isOn) {
/* 1509 */         Trace.traceData(this, "attempting exclusive register", null);
/*      */       }
/*      */       
/* 1512 */       int regOpts = 360449;
/*      */ 
/*      */       
/* 1515 */       testSubscription.setCorrelationId(correlationId1);
/*      */       
/* 1517 */       jmsMessage = new MQMsg2();
/* 1518 */       jmsMessage.setCorrelationId(correlationId1);
/* 1519 */       jmsMessage.setMessageType(1);
/* 1520 */       jmsMessage.setReport(0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1527 */       sendBrokerMessage(testSubscription, "RegSub", regOpts, jmsMessage, (MQPutMessageOptions)null);
/*      */     
/*      */     }
/* 1530 */     catch (JMSException je) {
/* 1531 */       if (Trace.isOn) {
/* 1532 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "registerExclusive(MQBrokerSubscription,byte [ ],Pint)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 1536 */       cleanupAction.x |= 0x1;
/* 1537 */       cleanupAction.x &= 0xFFFFFFFD;
/* 1538 */       cleanupAction.x |= 0x8;
/* 1539 */       if (Trace.isOn) {
/* 1540 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "registerExclusive(MQBrokerSubscription,byte [ ],Pint)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 1544 */       throw je;
/*      */     } 
/* 1546 */     if (Trace.isOn) {
/* 1547 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "registerExclusive(MQBrokerSubscription,byte [ ],Pint)", jmsMessage);
/*      */     }
/*      */     
/* 1550 */     return jmsMessage;
/*      */   }
/*      */   
/*      */   private void migrateToShared(MQSession mqs, boolean sharedQueue, MQBrokerSubscription newSubscription) throws JMSException {
/* 1554 */     if (Trace.isOn) {
/* 1555 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "migrateToShared(MQSession,boolean,MQBrokerSubscription)", new Object[] { mqs, 
/*      */             
/* 1557 */             Boolean.valueOf(sharedQueue), newSubscription });
/*      */     }
/*      */     
/* 1560 */     int regOpts = 196608;
/*      */     
/* 1562 */     if (sharedQueue) {
/* 1563 */       regOpts++;
/*      */     }
/*      */     
/* 1566 */     MQMsg2 jmsMessage = new MQMsg2();
/* 1567 */     jmsMessage.setCorrelationId(newSubscription.getCorrelationId());
/* 1568 */     jmsMessage.setMessageType(1);
/* 1569 */     jmsMessage.setReport(0);
/*      */     
/*      */     try {
/* 1572 */       sendBrokerMessage(newSubscription, "RegSub", regOpts, jmsMessage, (MQPutMessageOptions)null);
/*      */     
/*      */     }
/* 1575 */     catch (JMSException je) {
/* 1576 */       if (Trace.isOn) {
/* 1577 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "migrateToShared(MQSession,boolean,MQBrokerSubscription)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 1581 */       if (Trace.isOn) {
/* 1582 */         Trace.traceData(this, "error sending migrate to shared subscription message to broker", null);
/*      */       }
/* 1584 */       if (Trace.isOn) {
/* 1585 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "migrateToShared(MQSession,boolean,MQBrokerSubscription)", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */       
/* 1589 */       throw je;
/*      */     } 
/*      */     
/* 1592 */     MQBrokerMessage brokerResponse = getBrokerResponse(mqs, jmsMessage, true);
/*      */     
/* 1594 */     int response = Integer.parseInt(brokerResponse.get("MQPSCompCode"));
/* 1595 */     int responseReason = Integer.parseInt(brokerResponse.get("MQPSReason"));
/* 1596 */     if (response != 0 && (response != 1 || responseReason != 3157)) {
/* 1597 */       JMSException je = ConfigEnvironment.newException(brokerResponse.get("MQPSReasonText"));
/*      */       
/* 1599 */       if (Trace.isOn) {
/* 1600 */         Trace.traceData(this, "migrate to shared broker command failed" + brokerResponse.get("MQPSReasonText"), null);
/*      */       }
/* 1602 */       if (Trace.isOn) {
/* 1603 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "migrateToShared(MQSession,boolean,MQBrokerSubscription)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */       
/* 1607 */       throw je;
/*      */     } 
/* 1609 */     if (Trace.isOn) {
/* 1610 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "migrateToShared(MQSession,boolean,MQBrokerSubscription)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQBrokerSubscription webSphereCloneHandling(MQSession mqs, WMQDestination topic, boolean sharedQueue, String queueName, String userSubData, MQBrokerSubscription testSubscription, MQBrokerMessage brokerResponseP, byte[] correlationId1, byte[] correlationId2, byte[] deferredMsgId, Pint cleanupAction, int fiqBehaviour, int reason) throws JMSException {
/* 1619 */     if (Trace.isOn) {
/* 1620 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "webSphereCloneHandling(MQSession,WMQDestination,boolean,String,String,MQBrokerSubscription,MQBrokerMessage,byte [ ],byte [ ],byte [ ],Pint,int,int)", new Object[] { mqs, topic, 
/*      */             
/* 1622 */             Boolean.valueOf(sharedQueue), queueName, userSubData, testSubscription, brokerResponseP, correlationId1, correlationId2, deferredMsgId, cleanupAction, 
/*      */             
/* 1624 */             Integer.valueOf(fiqBehaviour), Integer.valueOf(reason) });
/*      */     }
/*      */     
/* 1627 */     MQBrokerMessage brokerResponse = brokerResponseP;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1635 */     if (reason == 3155) {
/*      */       
/* 1637 */       if (Trace.isOn) {
/* 1638 */         Trace.traceData(this, "subscription already shared by another WebSphere clone", null);
/*      */       
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1644 */       if (Trace.isOn) {
/* 1645 */         Trace.traceData(this, "subscription exclusively locked. Hopefully, this will change soon", null);
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/* 1650 */         if (Trace.isOn) {
/* 1651 */           Trace.traceData(this, "Sleeping for " + this.SLEEPTIME + " milis", null);
/*      */         }
/*      */         
/* 1654 */         Thread.sleep(this.SLEEPTIME);
/*      */       
/*      */       }
/* 1657 */       catch (InterruptedException ie) {
/* 1658 */         if (Trace.isOn) {
/* 1659 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "webSphereCloneHandling(MQSession,WMQDestination,boolean,String,String,MQBrokerSubscription,MQBrokerMessage,byte [ ],byte [ ],byte [ ],Pint,int,int)", ie, 1);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1670 */     if (Trace.isOn) {
/* 1671 */       Trace.traceData(this, "Subscription in use by another clone.Re-trying with join shared", null);
/*      */     }
/*      */     
/* 1674 */     testSubscription.setCorrelationId(correlationId2);
/*      */     
/* 1676 */     int regOpts = 229376;
/*      */     
/* 1678 */     if (sharedQueue) {
/* 1679 */       regOpts++;
/*      */     }
/*      */     
/* 1682 */     MQMsg2 jmsMessage1 = new MQMsg2();
/* 1683 */     jmsMessage1.setCorrelationId(correlationId1);
/* 1684 */     jmsMessage1.setMessageType(1);
/* 1685 */     jmsMessage1.setReport(0);
/*      */ 
/*      */     
/*      */     try {
/* 1689 */       sendBrokerMessage(testSubscription, "RegSub", regOpts, jmsMessage1, (MQPutMessageOptions)null);
/*      */       
/* 1691 */       if (Trace.isOn) {
/* 1692 */         Trace.traceData(this, "Receiving RFH2 ProviderMessage from broker", null);
/*      */       }
/*      */       
/* 1695 */       brokerResponse = getBrokerResponse(mqs, jmsMessage1, true);
/*      */       
/* 1697 */       int response = Integer.parseInt(brokerResponse.get("MQPSCompCode"));
/*      */       
/* 1699 */       int responseReason = Integer.parseInt(brokerResponse.get("MQPSReason"));
/* 1700 */       if (response != 0 && (response != 1 || responseReason != 3157))
/*      */       {
/* 1702 */         JMSException je2 = ConfigEnvironment.newException("MQJMS3050");
/*      */         
/* 1704 */         if (Trace.isOn) {
/* 1705 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "webSphereCloneHandling(MQSession,WMQDestination,boolean,String,String,MQBrokerSubscription,MQBrokerMessage,byte [ ],byte [ ],byte [ ],Pint,int,int)", (Throwable)je2, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1710 */         throw je2;
/*      */       }
/*      */     
/*      */     }
/* 1714 */     catch (JMSException je2) {
/* 1715 */       if (Trace.isOn) {
/* 1716 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "webSphereCloneHandling(MQSession,WMQDestination,boolean,String,String,MQBrokerSubscription,MQBrokerMessage,byte [ ],byte [ ],byte [ ],Pint,int,int)", (Throwable)je2, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1721 */       if (Trace.isOn) {
/* 1722 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "webSphereCloneHandling(MQSession,WMQDestination,boolean,String,String,MQBrokerSubscription,MQBrokerMessage,byte [ ],byte [ ],byte [ ],Pint,int,int)", (Throwable)je2, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1727 */       throw je2;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1732 */     String receivedSubName1 = brokerResponse.get("MQPSSubName");
/* 1733 */     String receivedQmgrName1 = safeTrim(brokerResponse.get("MQPSQMgrName"));
/* 1734 */     String receivedQueueName1 = safeTrim(brokerResponse.get("MQPSQName"));
/* 1735 */     String receivedUserSubData1 = brokerResponse.get("MQPSSubUserData");
/* 1736 */     if (receivedUserSubData1 == null) {
/* 1737 */       receivedUserSubData1 = "";
/*      */     }
/* 1739 */     String receivedTopic1 = safeTrim(brokerResponse.get("MQPSTopic"));
/* 1740 */     byte[] correlationId21 = Utils.hexToBytes(brokerResponse.get("MQPSCorrelId"));
/* 1741 */     testSubscription.setCorrelationId(correlationId21);
/*      */     
/* 1743 */     if (Trace.isOn) {
/* 1744 */       Trace.traceData(this, "subscription we are trying to join is:", receivedSubName1);
/* 1745 */       Trace.traceData(this, "Topic:    " + receivedTopic1, null);
/* 1746 */       Trace.traceData(this, "SubData:  " + receivedUserSubData1, null);
/* 1747 */       Trace.traceData(this, "QMgr:     " + receivedQmgrName1, null);
/* 1748 */       Trace.traceData(this, "Queue:    " + receivedQueueName1, null);
/* 1749 */       Trace.traceData(this, "CorrelId: " + Utils.bytesToHex(correlationId21), null);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1755 */     if (!mqs.getQMName().equals(receivedQmgrName1) || !queueName.equals(receivedQueueName1) || !userSubData.equals(receivedUserSubData1) || !topic.getName().equals(receivedTopic1)) {
/*      */       
/* 1757 */       JMSException je2 = ConfigEnvironment.newException("MQJMS3050");
/*      */       
/* 1759 */       if (Trace.isOn) {
/* 1760 */         Trace.traceData(this, "subscription already in use by another clone and cannot beupdated", null);
/*      */       }
/*      */       
/* 1763 */       cleanupAction.x |= 0x1;
/* 1764 */       cleanupAction.x |= 0x2;
/* 1765 */       cleanupAction.x |= 0x8;
/*      */       
/* 1767 */       if (Trace.isOn) {
/* 1768 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "webSphereCloneHandling(MQSession,WMQDestination,boolean,String,String,MQBrokerSubscription,MQBrokerMessage,byte [ ],byte [ ],byte [ ],Pint,int,int)", (Throwable)je2, 3);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1773 */       throw je2;
/*      */     } 
/*      */ 
/*      */     
/* 1777 */     MQBrokerSubscription newSubscription = testSubscription;
/* 1778 */     if (newSubscription.getSubscriberQueue() == null) {
/* 1779 */       newSubscription.setSubscriberQueue(openSubscriberQueue(mqs, queueName, sharedQueue, true, fiqBehaviour));
/*      */     }
/*      */     
/* 1782 */     newSubscription.setDeferredMsgId(deferredMsgId);
/*      */     
/* 1784 */     if (Trace.isOn) {
/* 1785 */       Trace.traceData(this, "got subscription with correlID", newSubscription.getCorrelationId());
/*      */     }
/*      */     
/* 1788 */     if (Trace.isOn) {
/* 1789 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "webSphereCloneHandling(MQSession,WMQDestination,boolean,String,String,MQBrokerSubscription,MQBrokerMessage,byte [ ],byte [ ],byte [ ],Pint,int,int)", newSubscription);
/*      */     }
/*      */ 
/*      */     
/* 1793 */     return newSubscription;
/*      */   }
/*      */   
/*      */   private String safeTrim(String string) {
/* 1797 */     if (Trace.isOn) {
/* 1798 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "safeTrim(String)", new Object[] { string });
/*      */     }
/*      */     
/* 1801 */     if (string == null) {
/* 1802 */       if (Trace.isOn) {
/* 1803 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "safeTrim(String)", "", 1);
/*      */       }
/*      */       
/* 1806 */       return "";
/*      */     } 
/* 1808 */     String traceRet1 = string.trim();
/* 1809 */     if (Trace.isOn) {
/* 1810 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "safeTrim(String)", traceRet1, 2);
/*      */     }
/*      */     
/* 1813 */     return traceRet1;
/*      */   }
/*      */   
/*      */   private MQBrokerMessage createProviderMessage(MQSession mqs, WMQDestination topic, String fullName, String filter) throws JMSException {
/* 1817 */     if (Trace.isOn) {
/* 1818 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "createProviderMessage(MQSession,WMQDestination,String,String)", new Object[] { mqs, topic, fullName, filter });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1823 */     MQBrokerMessage brokerMsg = null;
/*      */     
/* 1825 */     if (getMQConnection().getBrkVersion() == 0 || getMQConnection().getBrkVersion() == -1) {
/* 1826 */       if (Trace.isOn) {
/* 1827 */         Trace.traceData(this, "Creating RFH1 ProviderMessage", null);
/*      */       }
/* 1829 */       brokerMsg = new RFH1BrokerMessageImpl();
/*      */     } else {
/* 1831 */       if (Trace.isOn) {
/* 1832 */         Trace.traceData(this, "Creating RFH2 ProviderMessage", null);
/*      */       }
/* 1834 */       brokerMsg = new RFH2BrokerMessageImpl();
/*      */     } 
/*      */     
/* 1837 */     brokerMsg.set("MQPSCommand", "DeregSub");
/* 1838 */     brokerMsg.set("MQPSTopic", topic.getName());
/* 1839 */     brokerMsg.setOption("MQPSRegOpts", "LeaveOnly");
/* 1840 */     brokerMsg.setOption("MQPSRegOpts", "VariableUserId");
/* 1841 */     brokerMsg.setOption("MQPSRegOpts", "FullResp");
/*      */ 
/*      */     
/* 1844 */     brokerMsg.setOption("MQPSRegOpts", "CorrelAsId");
/*      */     
/* 1846 */     brokerMsg.set("MQPSSubIdentity", Utils.bytesToHex(mqs.getSessionName()));
/* 1847 */     brokerMsg.set("MQPSSubName", fullName);
/*      */ 
/*      */     
/* 1850 */     if (filter != null) {
/* 1851 */       if (Trace.isOn) {
/* 1852 */         Trace.traceData(this, "setting filter to ", filter);
/*      */       }
/* 1854 */       brokerMsg.set("MQPSFilter", filter);
/*      */     } 
/* 1856 */     if (Trace.isOn) {
/* 1857 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "createProviderMessage(MQSession,WMQDestination,String,String)", brokerMsg);
/*      */     }
/*      */     
/* 1860 */     return brokerMsg;
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
/*      */   public void closeDurableSubscription(MQSubscription sub) throws JMSException {
/* 1872 */     if (Trace.isOn) {
/* 1873 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeDurableSubscription(MQSubscription)", new Object[] { sub });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1879 */     int regOpts = 0;
/*      */ 
/*      */     
/* 1882 */     MQMsg2 jmsMessage = new MQMsg2();
/*      */     
/* 1884 */     MQQueue subscriberQueue = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*      */       try {
/* 1891 */         if (!(sub instanceof MQBrokerSubscription)) {
/* 1892 */           JMSException je = new JMSException("MQJMS3049");
/* 1893 */           if (Trace.isOn) {
/* 1894 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)je, 1);
/*      */           }
/*      */ 
/*      */           
/* 1898 */           throw je;
/*      */         } 
/* 1900 */         MQBrokerSubscription subscription = (MQBrokerSubscription)sub;
/*      */ 
/*      */         
/* 1903 */         regOpts += 524288;
/*      */ 
/*      */         
/* 1906 */         jmsMessage.setMessageType(8);
/*      */         
/*      */         try {
/* 1909 */           sendBrokerMessage(subscription, "DeregSub", regOpts, jmsMessage, (MQPutMessageOptions)null);
/*      */         
/*      */         }
/* 1912 */         catch (JMSException je) {
/* 1913 */           if (Trace.isOn) {
/* 1914 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)je, 1);
/*      */           }
/*      */ 
/*      */           
/* 1918 */           if (Trace.isOn) {
/* 1919 */             Trace.traceData(this, "Error sending BrokerMessage: ", null);
/*      */           }
/* 1921 */           if (Trace.isOn) {
/* 1922 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)je, 2);
/*      */           }
/*      */ 
/*      */           
/* 1926 */           throw je;
/*      */         } 
/*      */ 
/*      */         
/*      */         try {
/* 1931 */           cancelDeferredMessage(subscription.getMQSession(), subscription.getDeferredMsgId());
/*      */         }
/* 1933 */         catch (JMSException je) {
/* 1934 */           if (Trace.isOn) {
/* 1935 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)je, 2);
/*      */           }
/*      */ 
/*      */           
/* 1939 */           if (Trace.isOn) {
/* 1940 */             Trace.traceData(this, "Error cancelling deferred MQPUT: ", null);
/*      */           }
/* 1942 */           if (Trace.isOn) {
/* 1943 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)je, 3);
/*      */           }
/*      */ 
/*      */           
/* 1947 */           throw je;
/*      */         } 
/*      */ 
/*      */         
/* 1951 */         subscriberQueue = subscription.getSubscriberQueue();
/*      */         
/* 1953 */         if (subscriberQueue != null && subscriberQueue.isOpen()) {
/*      */           try {
/* 1955 */             if (Trace.isOn) {
/* 1956 */               Trace.traceData(this, "closing subscription queue", null);
/*      */             }
/* 1958 */             subscriberQueue.close();
/*      */           }
/* 1960 */           catch (MQException e) {
/* 1961 */             if (Trace.isOn) {
/* 1962 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)e, 3);
/*      */             }
/*      */ 
/*      */             
/* 1966 */             if (Trace.isOn) {
/* 1967 */               Trace.traceData(this, "queue close failed", null);
/*      */             }
/* 1969 */             JMSException je = ConfigEnvironment.newException("MQJMS2000");
/* 1970 */             je.setLinkedException((Exception)e);
/* 1971 */             if (Trace.isOn) {
/* 1972 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)je, 4);
/*      */             }
/*      */ 
/*      */             
/* 1976 */             throw je;
/*      */           }
/*      */         
/*      */         }
/*      */       }
/* 1981 */       catch (JMSException je) {
/* 1982 */         if (Trace.isOn) {
/* 1983 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)je, 4);
/*      */         }
/*      */ 
/*      */         
/* 1987 */         if (Trace.isOn) {
/* 1988 */           Trace.traceData(this, "exception thrown while attempting to close durable subscription", null);
/*      */         }
/* 1990 */         if (Trace.isOn) {
/* 1991 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)je, 5);
/*      */         }
/*      */ 
/*      */         
/* 1995 */         throw je;
/*      */       } finally {
/*      */         
/* 1998 */         if (Trace.isOn) {
/* 1999 */           Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeDurableSubscription(MQSubscription)");
/*      */         }
/*      */ 
/*      */         
/* 2003 */         if (this.WSClone == 1) {
/* 2004 */           if (Trace.isOn) {
/* 2005 */             Trace.traceData(this, "attempting to remove localSubscription entry", null);
/*      */           }
/* 2007 */           localSubscriptions.removeSubscription(((MQBrokerSubscription)sub).getEscapedFullName());
/*      */         }
/*      */       
/*      */       }
/*      */     
/* 2012 */     } catch (JMSException je) {
/* 2013 */       if (Trace.isOn) {
/* 2014 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)je, 5);
/*      */       }
/*      */ 
/*      */       
/* 2018 */       if (Trace.isOn) {
/* 2019 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)je, 6);
/*      */       }
/*      */ 
/*      */       
/* 2023 */       throw je;
/*      */     } 
/*      */     
/* 2026 */     if (Trace.isOn) {
/* 2027 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "closeDurableSubscription(MQSubscription)");
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
/*      */   public void removeDurableSubscription(MQSubscription sub) throws JMSException {
/* 2046 */     if (Trace.isOn) {
/* 2047 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "removeDurableSubscription(MQSubscription)", new Object[] { sub });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 2052 */       if (this.WSClone == 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2058 */         String subscriptionName = ((MQBrokerSubscription)sub).getEscapedFullName();
/* 2059 */         if (Trace.isOn) {
/* 2060 */           Trace.traceData(this, "Clone Support is enabled. Attempting to remove entry for subscription " + subscriptionName + " in local subscription list " + localSubscriptions, null);
/*      */         }
/*      */         
/* 2063 */         localSubscriptions.removeSubscription(subscriptionName);
/*      */       
/*      */       }
/* 2066 */       else if (Trace.isOn) {
/* 2067 */         Trace.traceData(this, "Clone Support is not enabled. Nothing to do....", null);
/*      */       }
/*      */     
/*      */     }
/* 2071 */     catch (JMSException je) {
/* 2072 */       if (Trace.isOn) {
/* 2073 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "removeDurableSubscription(MQSubscription)", (Throwable)je);
/*      */         
/* 2075 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "removeDurableSubscription(MQSubscription)", (Throwable)je);
/*      */       } 
/*      */       
/* 2078 */       throw je;
/*      */     } 
/* 2080 */     if (Trace.isOn) {
/* 2081 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "removeDurableSubscription(MQSubscription)");
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
/*      */   public void durableUnsubscribe(MQSession mqs, String subName) throws JMSException {
/* 2098 */     if (Trace.isOn) {
/* 2099 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String)", new Object[] { mqs, subName });
/*      */     }
/*      */     
/* 2102 */     durableUnsubscribe(mqs, subName, false);
/* 2103 */     if (Trace.isOn) {
/* 2104 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void durableUnsubscribe(MQSession mqs, String subName, boolean swallowWarning) throws JMSException {
/* 2114 */     if (Trace.isOn) {
/* 2115 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", new Object[] { mqs, subName, 
/*      */             
/* 2117 */             Boolean.valueOf(swallowWarning) });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2122 */     int regOpts = 0;
/*      */ 
/*      */     
/* 2125 */     MQMsg2 jmsMessage = new MQMsg2();
/*      */ 
/*      */     
/* 2128 */     BrokerConnectionInfo brk = mqs.getBrk();
/*      */ 
/*      */     
/* 2131 */     byte[] defCorrelID = null;
/* 2132 */     byte[] defMessageID = null;
/*      */ 
/*      */     
/* 2135 */     String fullName = null;
/*      */ 
/*      */     
/* 2138 */     String topicName = null;
/*      */ 
/*      */     
/* 2141 */     String filter = null;
/*      */ 
/*      */     
/* 2144 */     String streamName = null;
/*      */ 
/*      */     
/* 2147 */     MQBrokerMessage brokerMsg = null;
/*      */ 
/*      */     
/* 2150 */     MQBrokerMessage responseMsg = null;
/*      */ 
/*      */     
/* 2153 */     boolean sharedQueue = false;
/* 2154 */     byte[] correlationId = null;
/* 2155 */     String queueName = null;
/*      */ 
/*      */     
/* 2158 */     boolean invalidSubName = false;
/* 2159 */     String unknownTopic = "JMS:SYS:Unknown";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*      */       try {
/*      */         try {
/* 2174 */           if (getMQConnection().getBrkVersion() == 0 || getMQConnection().getBrkVersion() == -1) {
/* 2175 */             if (Trace.isOn) {
/* 2176 */               Trace.traceData(this, "Creating new RFH1 ProviderMessage", null);
/*      */             }
/* 2178 */             brokerMsg = new RFH1BrokerMessageImpl();
/*      */           } else {
/* 2180 */             if (Trace.isOn) {
/* 2181 */               Trace.traceData(this, "Creating new RFH2 ProviderMessage", null);
/*      */             }
/* 2183 */             brokerMsg = new RFH2BrokerMessageImpl();
/*      */           } 
/*      */ 
/*      */           
/* 2187 */           fullName = "JMS:" + WMQCommonUtils.escapeString(mqs.getQMName()) + ":" + WMQCommonUtils.escapeString(getMQConnection().getClientID()) + ":" + WMQCommonUtils.escapeString(subName);
/*      */           
/* 2189 */           brokerMsg.set("MQPSCommand", "DeregSub");
/* 2190 */           brokerMsg.set("MQPSSubName", fullName);
/* 2191 */           brokerMsg.set("MQPSSubIdentity", Utils.bytesToHex(mqs.getSessionName()));
/*      */           
/* 2193 */           regOpts = 589824;
/*      */           
/* 2195 */           brokerMsg.setOption("MQPSRegOpts", regOpts);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2205 */           brokerMsg.set("MQPSStreamName", brk.streamQ);
/* 2206 */           if (mqs.getQMName() != null) {
/* 2207 */             brokerMsg.set("MQPSQMgrName", mqs.getQMName());
/*      */           }
/*      */           
/* 2210 */           jmsMessage.setMessageType(8);
/* 2211 */           jmsMessage.setFormat(brokerMsg.getHeaderFormat());
/*      */         
/*      */         }
/* 2214 */         catch (MQException e) {
/* 2215 */           if (Trace.isOn) {
/* 2216 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)e, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 2221 */           String msg = ConfigEnvironment.getErrorMessage("MQJMS3008");
/* 2222 */           JMSException je = new JMSException(msg, "MQJMS3008");
/* 2223 */           je.setLinkedException((Exception)e);
/* 2224 */           je.initCause((Throwable)e);
/* 2225 */           if (Trace.isOn) {
/* 2226 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)je, 1);
/*      */           }
/*      */ 
/*      */           
/* 2230 */           throw je;
/*      */         } 
/*      */ 
/*      */         
/*      */         try {
/* 2235 */           defCorrelID = setDeferredMessage(mqs, brokerMsg, jmsMessage, (MQPutMessageOptions)null, true, 0);
/* 2236 */           defMessageID = jmsMessage.getMessageId();
/* 2237 */           if (defCorrelID == null)
/*      */           {
/*      */ 
/*      */             
/* 2241 */             if (Trace.isOn) {
/* 2242 */               Trace.traceData(this, "Queue manager does not support deferred messages", null);
/*      */             }
/*      */           }
/*      */         }
/* 2246 */         catch (JMSException je) {
/* 2247 */           if (Trace.isOn) {
/* 2248 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)je, 2);
/*      */           }
/*      */ 
/*      */           
/* 2252 */           if (Trace.isOn) {
/* 2253 */             Trace.traceData(this, "Could not send brokerMessage with deferred MQPUT", null);
/*      */           }
/* 2255 */           if (Trace.isOn) {
/* 2256 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)je, 2);
/*      */           }
/*      */ 
/*      */           
/* 2260 */           throw je;
/*      */         }
/*      */       
/*      */       }
/* 2264 */       catch (JMSException je) {
/* 2265 */         if (Trace.isOn) {
/* 2266 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)je, 3);
/*      */         }
/*      */ 
/*      */         
/* 2270 */         if (Trace.isOn) {
/* 2271 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)je, 3);
/*      */         }
/*      */ 
/*      */         
/* 2275 */         throw je;
/*      */       }  try {
/*      */         BrokerCommandFailedException traceRet2, traceRet3;
/*      */         String msg;
/*      */         BrokerCommandFailedException traceRet4;
/* 2280 */         brokerMsg.clear();
/* 2281 */         brokerMsg.set("MQPSCommand", "RegSub");
/* 2282 */         brokerMsg.set("MQPSSubIdentity", Utils.bytesToHex(mqs.getSessionName()));
/* 2283 */         brokerMsg.set("MQPSSubName", fullName);
/* 2284 */         brokerMsg.set("MQPSTopic", "JMS:SYS:Unknown");
/* 2285 */         regOpts = 360448;
/* 2286 */         brokerMsg.setOption("MQPSRegOpts", regOpts);
/*      */         
/* 2288 */         brokerMsg.set("MQPSStreamName", brk.streamQ);
/* 2289 */         if (mqs.getQMName() != null) {
/* 2290 */           brokerMsg.set("MQPSQMgrName", mqs.getQMName());
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2295 */         jmsMessage = new MQMsg2();
/* 2296 */         jmsMessage.setMessageType(1);
/* 2297 */         jmsMessage.setReport(0);
/* 2298 */         jmsMessage.setFormat(brokerMsg.getHeaderFormat());
/*      */         
/* 2300 */         sendBrokerMessage(mqs, brokerMsg, jmsMessage, (MQPutMessageOptions)null, true);
/*      */ 
/*      */         
/*      */         try {
/* 2304 */           if (getMQConnection().getBrkVersion() == 0 || getMQConnection().getBrkVersion() == -1) {
/* 2305 */             if (Trace.isOn) {
/* 2306 */               Trace.traceData(this, "Receiving RFH1 ProviderMessage from broker", null);
/*      */             }
/* 2308 */             responseMsg = new RFH1BrokerMessageImpl();
/* 2309 */             responseMsg = getBrokerResponse(mqs, jmsMessage, false);
/*      */           } else {
/* 2311 */             if (Trace.isOn) {
/* 2312 */               Trace.traceData(this, "Receiving RFH2 ProviderMessage from broker", null);
/*      */             }
/* 2314 */             responseMsg = new RFH2BrokerMessageImpl();
/* 2315 */             responseMsg = getBrokerResponse(mqs, jmsMessage, false);
/*      */           }
/*      */         
/*      */         }
/* 2319 */         catch (JMSException je) {
/* 2320 */           if (Trace.isOn) {
/* 2321 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)je, 4);
/*      */           }
/*      */ 
/*      */           
/* 2325 */           if (je instanceof NoBrokerResponseException) {
/* 2326 */             responseMsg = null;
/*      */           }
/* 2328 */           if (Trace.isOn) {
/* 2329 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)je, 4);
/*      */           }
/*      */ 
/*      */           
/* 2333 */           throw je;
/*      */         } 
/*      */         
/* 2336 */         int brokerCompCode = Integer.parseInt(responseMsg.get("MQPSCompCode"));
/*      */         
/* 2338 */         if (swallowWarning && brokerCompCode == 1) {
/* 2339 */           brokerCompCode = 0;
/*      */         }
/*      */ 
/*      */         
/* 2343 */         String reasonText = "Broker did not respond";
/*      */         
/* 2345 */         switch (brokerCompCode) {
/*      */           
/*      */           case 1:
/* 2348 */             reasonText = responseMsg.get("MQPSReasonText");
/*      */ 
/*      */ 
/*      */             
/* 2352 */             traceRet2 = new BrokerCommandFailedException("Broker Command warning: " + reasonText, "MQCCF" + responseMsg.get("MQPSReason"));
/* 2353 */             if (Trace.isOn) {
/* 2354 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)traceRet2, 5);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2363 */             throw traceRet2;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 2:
/* 2369 */             reasonText = responseMsg.get("MQPSReasonText");
/*      */ 
/*      */             
/* 2372 */             traceRet3 = new BrokerCommandFailedException("Broker Command failed: " + reasonText, "MQCCF" + responseMsg.get("MQPSReason"));
/* 2373 */             if (Trace.isOn) {
/* 2374 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)traceRet3, 6);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2383 */             throw traceRet3;
/*      */           
/*      */           case 0:
/* 2386 */             topicName = responseMsg.get("MQPSTopic");
/* 2387 */             filter = responseMsg.get("MQPSFilter");
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2392 */             streamName = responseMsg.get("MQPSStreamName");
/*      */             
/* 2394 */             if (Trace.isOn && 
/* 2395 */               null != brk.streamQ && !brk.streamQ.equals(streamName)) {
/* 2396 */               Trace.traceData(this, "This subscription is on stream '" + streamName + "'", null);
/* 2397 */               Trace.traceData(this, "The deferred deregister message we sent uses stream '" + brk.streamQ + "'", null);
/* 2398 */               Trace.traceData(this, "We may not be able to deregister if something goes wrong while we have this subscription", null);
/*      */             } 
/*      */             
/* 2401 */             if (null == streamName) {
/* 2402 */               streamName = brk.streamQ;
/*      */             }
/*      */             break;
/*      */ 
/*      */           
/*      */           default:
/* 2408 */             reasonText = responseMsg.get("MQPSReasonText");
/* 2409 */             msg = ConfigEnvironment.getErrorMessage("MQJMS3040", reasonText);
/* 2410 */             traceRet4 = new BrokerCommandFailedException(msg, "MQJMS3040");
/*      */             
/* 2412 */             if (Trace.isOn) {
/* 2413 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)traceRet4, 7);
/*      */             }
/*      */ 
/*      */             
/* 2417 */             throw traceRet4;
/*      */         } 
/*      */       
/* 2420 */       } catch (MQException e) {
/* 2421 */         if (Trace.isOn) {
/* 2422 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)e, 5);
/*      */         }
/*      */ 
/*      */         
/* 2426 */         BrokerCommandFailedException je = new BrokerCommandFailedException(e.getMessage(), e.getErrorCode());
/* 2427 */         je.setLinkedException((Exception)e);
/* 2428 */         if (Trace.isOn) {
/* 2429 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)je, 8);
/*      */         }
/*      */ 
/*      */         
/* 2433 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2443 */         if (topicName.equals("JMS:SYS:Unknown"))
/*      */         {
/* 2445 */           invalidSubName = true;
/*      */         }
/*      */ 
/*      */         
/* 2449 */         sharedQueue = responseMsg.isOptionSet("MQPSRegOpts", 1);
/* 2450 */         queueName = responseMsg.get("MQPSQName");
/* 2451 */         String tmp = responseMsg.get("MQPSCorrelId");
/* 2452 */         if (tmp != null) {
/* 2453 */           correlationId = Utils.hexToBytes(tmp);
/*      */         }
/*      */         
/* 2456 */         brokerMsg.clear();
/* 2457 */         brokerMsg.set("MQPSCommand", "DeregSub");
/* 2458 */         brokerMsg.set("MQPSSubIdentity", Utils.bytesToHex(mqs.getSessionName()));
/* 2459 */         brokerMsg.set("MQPSSubName", fullName);
/* 2460 */         brokerMsg.set("MQPSTopic", topicName);
/* 2461 */         regOpts = 65536;
/* 2462 */         brokerMsg.setOption("MQPSRegOpts", regOpts);
/*      */         
/* 2464 */         brokerMsg.set("MQPSStreamName", streamName);
/* 2465 */         if (mqs.getQMName() != null) {
/* 2466 */           brokerMsg.set("MQPSQMgrName", mqs.getQMName());
/*      */         }
/*      */ 
/*      */         
/* 2470 */         if (filter != null) {
/* 2471 */           if (Trace.isOn) {
/* 2472 */             Trace.traceData(this, "setting filter to " + filter, null);
/*      */           }
/* 2474 */           brokerMsg.set("MQPSFilter", filter);
/*      */         } 
/*      */         
/* 2477 */         jmsMessage = new MQMsg2();
/* 2478 */         jmsMessage.setMessageType(1);
/* 2479 */         jmsMessage.setReport(0);
/* 2480 */         jmsMessage.setFormat(brokerMsg.getHeaderFormat());
/*      */         
/* 2482 */         sendBrokerMessage(mqs, brokerMsg, jmsMessage, (MQPutMessageOptions)null, true);
/*      */       
/*      */       }
/* 2485 */       catch (MQException e) {
/* 2486 */         if (Trace.isOn) {
/* 2487 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)e, 6);
/*      */         }
/*      */ 
/*      */         
/* 2491 */         NoBrokerResponseException je = new NoBrokerResponseException(e.getMessage(), e.getErrorCode());
/* 2492 */         je.setLinkedException((Exception)e);
/* 2493 */         if (Trace.isOn) {
/* 2494 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)je, 9);
/*      */         }
/*      */ 
/*      */         
/* 2498 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2503 */       if (invalidSubName) {
/* 2504 */         if (Trace.isOn) {
/* 2505 */           Trace.traceData(this, "No record found for '" + subName + "'", null);
/*      */         }
/*      */ 
/*      */         
/* 2509 */         String key = "MQJMS3018";
/* 2510 */         String msg = ConfigEnvironment.getErrorMessage(key, subName);
/* 2511 */         InvalidDestinationException ide = new InvalidDestinationException(msg, key);
/* 2512 */         if (Trace.isOn) {
/* 2513 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)ide, 10);
/*      */         }
/*      */ 
/*      */         
/* 2517 */         throw ide;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2524 */       if (Trace.isOn) {
/* 2525 */         Trace.traceData(this, "About to call cleanup to remove unconsumed messages", null);
/*      */       }
/*      */       try {
/* 2528 */         responseMsg = cleanupUnconsumedMessages(mqs, sharedQueue, queueName, correlationId, jmsMessage);
/*      */       }
/* 2530 */       catch (JMSException je) {
/* 2531 */         if (Trace.isOn) {
/* 2532 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)je, 7);
/*      */         }
/*      */ 
/*      */         
/* 2536 */         if (Trace.isOn) {
/* 2537 */           Trace.traceData(this, "exception thrown while attempting to cleanup subscriber queue", null);
/*      */         }
/* 2539 */         if (Trace.isOn) {
/* 2540 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)je, 11);
/*      */         }
/*      */ 
/*      */         
/* 2544 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 2549 */         cancelDeferredMessage(mqs, defMessageID);
/*      */       }
/* 2551 */       catch (JMSException je) {
/* 2552 */         if (Trace.isOn) {
/* 2553 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)je, 8);
/*      */         }
/*      */ 
/*      */         
/* 2557 */         if (Trace.isOn) {
/* 2558 */           Trace.traceData(this, "Exception thrown cancelling deferred deregister message. Ignoring", null);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 2565 */     catch (JMSException je) {
/* 2566 */       if (Trace.isOn) {
/* 2567 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)je, 9);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2572 */       if (Trace.isOn) {
/* 2573 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)", (Throwable)je, 12);
/*      */       }
/*      */ 
/*      */       
/* 2577 */       throw je;
/*      */     } 
/*      */     
/* 2580 */     if (Trace.isOn) {
/* 2581 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "durableUnsubscribe(MQSession,String,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() {
/* 2592 */     if (Trace.isOn) {
/* 2593 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "close()");
/*      */     }
/*      */     
/* 2596 */     if (Trace.isOn) {
/* 2597 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "close()");
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
/*      */   private byte[] sendBrokerMessageInternal(MQSession mqs, MQBrokerMessage brokerMsg, MQMsg2 jmsMessage, MQPutMessageOptions pmoP, boolean deferred, boolean durable, int fiqBehaviour) throws JMSException {
/* 2632 */     if (Trace.isOn) {
/* 2633 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean,boolean,int)", new Object[] { mqs, brokerMsg, jmsMessage, pmoP, 
/*      */             
/* 2635 */             Boolean.valueOf(deferred), 
/* 2636 */             Boolean.valueOf(durable), Integer.valueOf(fiqBehaviour) });
/*      */     }
/*      */     
/* 2639 */     MQPutMessageOptions pmo = pmoP;
/* 2640 */     MQQueue brokerQ = null;
/* 2641 */     byte[] sessionName = null;
/* 2642 */     byte[] correlId = null;
/*      */ 
/*      */     
/*      */     try {
/* 2646 */       BrokerConnectionInfo brk = mqs.getBrk();
/*      */ 
/*      */       
/*      */       try {
/* 2650 */         MQConnection mqc = getMQConnection();
/*      */ 
/*      */         
/* 2653 */         if ("RegSub".equals(brokerMsg.get("MQPSCommand")) && mqc.getBrkOptLevel() > 0)
/*      */         {
/* 2655 */           if (!mqs.persistenceFromMD && !durable) {
/* 2656 */             brokerMsg.setOption("MQPSRegOpts", "NonPers");
/*      */           }
/*      */ 
/*      */           
/* 2660 */           if (mqs.acknowledgeMode == 3) {
/* 2661 */             brokerMsg.setOption("MQPSRegOpts", "DupsOK");
/*      */           }
/*      */         }
/*      */       
/*      */       }
/* 2666 */       catch (JMSException je) {
/* 2667 */         if (Trace.isOn) {
/* 2668 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean,boolean,int)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2673 */         if (Trace.isOn) {
/* 2674 */           Trace.traceData(this, "Exception thrown attempting to set nonPersistent and DupsOk options", null);
/*      */         }
/* 2676 */         if (Trace.isOn) {
/* 2677 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean,boolean,int)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2682 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*      */         try {
/* 2688 */           if (durable) {
/* 2689 */             sessionName = mqs.getSessionName();
/* 2690 */             brokerMsg.set("MQPSSubIdentity", Utils.bytesToHex(sessionName));
/* 2691 */             brokerMsg.setOption("MQPSRegOpts", "VariableUserId");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2698 */             brokerMsg.set("MQPSStreamName", brk.streamQ);
/*      */           } 
/* 2700 */           brokerMsg.writeToMessage(jmsMessage);
/* 2701 */           jmsMessage.setReplyToQueueManagerName("");
/* 2702 */           jmsMessage.setReplyToQueueName("SYSTEM.JMS.REPORT.QUEUE");
/* 2703 */           jmsMessage.setFormat(brokerMsg.getHeaderFormat());
/*      */           
/* 2705 */           jmsMessage.setPersistence(1);
/*      */         }
/* 2707 */         catch (MQException e) {
/* 2708 */           if (Trace.isOn) {
/* 2709 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean,boolean,int)", (Throwable)e, 2);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 2714 */           String msg = ConfigEnvironment.getErrorMessage("MQJMS1000");
/* 2715 */           BrokerCommandFailedException je = new BrokerCommandFailedException(msg, e.getErrorCode());
/* 2716 */           je.setLinkedException((Exception)e);
/* 2717 */           if (Trace.isOn) {
/* 2718 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean,boolean,int)", (Throwable)je, 2);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 2723 */           throw je;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 2729 */           int openOptions = 16;
/* 2730 */           MQQueueManager qm = mqs.getQM();
/* 2731 */           if (pmo == null) {
/* 2732 */             pmo = new MQPutMessageOptions();
/*      */           }
/*      */ 
/*      */           
/* 2736 */           if (fiqBehaviour == 1) {
/* 2737 */             openOptions |= 0x2000;
/* 2738 */             pmo.options |= 0x2000;
/*      */           } 
/*      */ 
/*      */           
/* 2742 */           if (deferred) {
/* 2743 */             MQQueueManager spiQm = qm;
/* 2744 */             if (!spiQm.spiSupportsDeferred()) {
/*      */ 
/*      */               
/* 2747 */               JMSException je = ConfigEnvironment.newException("MQJMS3047");
/* 2748 */               if (Trace.isOn) {
/* 2749 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean,boolean,int)", (Throwable)je, 3);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/* 2754 */               throw je;
/*      */             } 
/* 2756 */             brokerQ = spiQm.accessQueue(brk.controlQ, openOptions, brk.qmName, null, null);
/* 2757 */             brokerQ.spiDeferredPut(jmsMessage, pmo);
/*      */           } else {
/*      */             
/* 2760 */             brokerQ = mqs.getQM().accessQueue(brk.controlQ, openOptions, brk.qmName, null, null);
/* 2761 */             brokerQ.putMsg2(jmsMessage, pmo);
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 2766 */         catch (MQException mqe) {
/* 2767 */           if (Trace.isOn) {
/* 2768 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean,boolean,int)", (Throwable)mqe, 3);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 2773 */           String msg = ConfigEnvironment.getErrorMessage("MQJMS2008", brk.controlQ);
/* 2774 */           BrokerCommandFailedException je = new BrokerCommandFailedException(msg, "MQJMS2008");
/* 2775 */           je.setLinkedException((Exception)mqe);
/*      */           
/* 2777 */           if (Trace.isOn) {
/* 2778 */             Trace.traceData(this, "Failed to MQPUT broker command due to: " + mqe, null);
/*      */           }
/* 2780 */           if (Trace.isOn) {
/* 2781 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean,boolean,int)", (Throwable)je, 4);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 2786 */           throw je;
/*      */         } 
/*      */         
/* 2789 */         correlId = jmsMessage.getCorrelationId();
/*      */ 
/*      */         
/*      */         try {
/* 2793 */           brokerQ.close();
/*      */         
/*      */         }
/* 2796 */         catch (MQException mqe) {
/* 2797 */           if (Trace.isOn) {
/* 2798 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean,boolean,int)", (Throwable)mqe, 4);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 2803 */           String msg = ConfigEnvironment.getErrorMessage("MQJMS2000");
/* 2804 */           BrokerCommandFailedException je = new BrokerCommandFailedException(msg, "MQJMS2000");
/* 2805 */           je.setLinkedException((Exception)mqe);
/*      */           
/* 2807 */           if (Trace.isOn) {
/* 2808 */             Trace.traceData(this, "Failed to close broker queue", null);
/*      */           }
/* 2810 */           if (Trace.isOn) {
/* 2811 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean,boolean,int)", (Throwable)je, 5);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 2816 */           throw je;
/*      */         }
/*      */       
/*      */       }
/* 2820 */       catch (JMSException je) {
/* 2821 */         if (Trace.isOn) {
/* 2822 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean,boolean,int)", (Throwable)je, 5);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2827 */         if (Trace.isOn) {
/* 2828 */           Trace.traceData(this, "Failed to put message to broker queue", null);
/*      */         }
/* 2830 */         if (Trace.isOn) {
/* 2831 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean,boolean,int)", (Throwable)je, 6);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2836 */         throw je;
/*      */       }
/*      */     
/*      */     }
/* 2840 */     catch (JMSException je) {
/* 2841 */       if (Trace.isOn) {
/* 2842 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean,boolean,int)", (Throwable)je, 6);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2847 */       if (Trace.isOn) {
/* 2848 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean,boolean,int)", (Throwable)je, 7);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2853 */       throw je;
/*      */     } 
/*      */     
/* 2856 */     if (Trace.isOn) {
/* 2857 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean,boolean,int)", correlId);
/*      */     }
/*      */ 
/*      */     
/* 2861 */     return correlId;
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
/*      */   private byte[] sendBrokerMessageInternal(MQSubscription subscription, String command, int regOpts, MQMsg2 jmsMessageP, MQPutMessageOptions pmo, boolean deferred) throws JMSException {
/* 2884 */     if (Trace.isOn) {
/* 2885 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSubscription,String,int,MQMsg2,MQPutMessageOptions,boolean)", new Object[] { subscription, command, 
/*      */             
/* 2887 */             Integer.valueOf(regOpts), jmsMessageP, pmo, 
/* 2888 */             Boolean.valueOf(deferred) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2896 */     MQMsg2 jmsMessage = jmsMessageP;
/* 2897 */     MQSession mqs = null;
/* 2898 */     String selector = null;
/* 2899 */     String subUserData = null;
/* 2900 */     byte[] correlationID = null;
/* 2901 */     String filter = null;
/*      */ 
/*      */     
/* 2904 */     MQBrokerMessage brokerMessage = null;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2909 */       mqs = subscription.getMQSession();
/* 2910 */       BrokerConnectionInfo brk = mqs.getBrk();
/*      */ 
/*      */ 
/*      */       
/* 2914 */       selector = subscription.getSelector();
/*      */       
/* 2916 */       if (selector != null && !selector.trim().equals("")) {
/* 2917 */         subUserData = "sel=\"" + WMQCommonUtils.escapeString(selector) + "\"";
/*      */       } else {
/* 2919 */         subUserData = "";
/*      */       } 
/*      */       
/*      */       try {
/* 2923 */         if (getMQConnection().getBrkVersion() == 0 || getMQConnection().getBrkVersion() == -1) {
/* 2924 */           if (Trace.isOn) {
/* 2925 */             Trace.traceData(this, "Creating RFH1 ProviderMessage", null);
/*      */           }
/* 2927 */           brokerMessage = new RFH1BrokerMessageImpl();
/*      */         } else {
/* 2929 */           if (Trace.isOn) {
/* 2930 */             Trace.traceData(this, "Creating RFH2 ProviderMessage", null);
/*      */           }
/* 2932 */           brokerMessage = new RFH2BrokerMessageImpl();
/*      */         } 
/*      */         
/* 2935 */         brokerMessage.set("MQPSCommand", command);
/* 2936 */         brokerMessage.setOption("MQPSRegOpts", regOpts);
/*      */ 
/*      */         
/* 2939 */         String topicStreamName = subscription.getMQTopic().getStringProperty("XMSC_WMQ_BROKER_PUBQ");
/* 2940 */         if (topicStreamName == null || topicStreamName.equals("")) {
/*      */           
/* 2942 */           brokerMessage.set("MQPSStreamName", brk.streamQ);
/*      */         }
/*      */         else {
/*      */           
/* 2946 */           brokerMessage.set("MQPSStreamName", topicStreamName);
/*      */         } 
/*      */         
/* 2949 */         brokerMessage.set("MQPSTopic", subscription.getTopic());
/* 2950 */         if (null != mqs.getQM() && mqs.getQMName() != null) {
/* 2951 */           brokerMessage.set("MQPSQMgrName", mqs.getQMName());
/*      */         }
/* 2953 */         brokerMessage.set("MQPSQName", subscription.getQueueName());
/*      */         
/* 2955 */         if (subscription.isDurable()) {
/* 2956 */           brokerMessage.set("MQPSSubName", ((MQBrokerSubscription)subscription).getEscapedFullName());
/*      */         }
/* 2958 */         if (!command.equals("DeregSub")) {
/* 2959 */           brokerMessage.set("MQPSSubUserData", subUserData);
/*      */         } else {
/* 2961 */           brokerMessage.unset("MQPSSubUserData");
/*      */         } 
/*      */ 
/*      */         
/* 2965 */         filter = subscription.getFilter();
/* 2966 */         if (filter != null) {
/* 2967 */           if (Trace.isOn) {
/* 2968 */             Trace.traceData(this, "setting filter to " + filter, null);
/*      */           }
/* 2970 */           brokerMessage.set("MQPSFilter", filter);
/*      */         }
/*      */       
/*      */       }
/* 2974 */       catch (JMSException je) {
/* 2975 */         if (Trace.isOn) {
/* 2976 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSubscription,String,int,MQMsg2,MQPutMessageOptions,boolean)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2982 */         if (Trace.isOn) {
/* 2983 */           Trace.traceData(this, "Error constructing MQBrokerMessage", null);
/*      */         }
/* 2985 */         if (Trace.isOn) {
/* 2986 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSubscription,String,int,MQMsg2,MQPutMessageOptions,boolean)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2991 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2997 */         if (jmsMessage == null) {
/* 2998 */           jmsMessage = new MQMsg2();
/*      */         }
/* 3000 */         jmsMessage.setReplyToQueueManagerName("");
/* 3001 */         jmsMessage.setReplyToQueueName("SYSTEM.JMS.REPORT.QUEUE");
/* 3002 */         jmsMessage.setFormat(brokerMessage.getHeaderFormat());
/*      */       
/*      */       }
/* 3005 */       catch (MQException e) {
/* 3006 */         if (Trace.isOn) {
/* 3007 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSubscription,String,int,MQMsg2,MQPutMessageOptions,boolean)", (Throwable)e, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 3012 */         if (Trace.isOn) {
/* 3013 */           Trace.traceData(this, "Error detailing jmsMessage", null);
/*      */         }
/* 3015 */         String msg = ConfigEnvironment.getErrorMessage("MQJMS1000");
/* 3016 */         BrokerCommandFailedException je = new BrokerCommandFailedException(msg, "MQJMS1000");
/* 3017 */         je.setLinkedException((Exception)e);
/*      */         
/* 3019 */         if (Trace.isOn) {
/* 3020 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSubscription,String,int,MQMsg2,MQPutMessageOptions,boolean)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 3025 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 3029 */       int fiqBehaviour = 1;
/*      */ 
/*      */       
/* 3032 */       if (subscription.getMQTopic() != null) {
/* 3033 */         fiqBehaviour = subscription.getMQTopic().getIntProperty("failIfQuiesce");
/*      */       }
/*      */       else {
/*      */         
/* 3037 */         fiqBehaviour = mqs.getFailIfQuiesce();
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 3042 */         correlationID = sendBrokerMessageInternal(mqs, brokerMessage, jmsMessage, pmo, deferred, subscription.isDurable(), fiqBehaviour);
/*      */       }
/* 3044 */       catch (JMSException je) {
/* 3045 */         if (Trace.isOn) {
/* 3046 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSubscription,String,int,MQMsg2,MQPutMessageOptions,boolean)", (Throwable)je, 3);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 3051 */         if (Trace.isOn) {
/* 3052 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSubscription,String,int,MQMsg2,MQPutMessageOptions,boolean)", (Throwable)je, 3);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 3057 */         throw je;
/*      */       } 
/*      */       
/* 3060 */       if (Trace.isOn) {
/* 3061 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSubscription,String,int,MQMsg2,MQPutMessageOptions,boolean)", correlationID);
/*      */       }
/*      */ 
/*      */       
/* 3065 */       return correlationID;
/*      */     
/*      */     }
/* 3068 */     catch (JMSException je) {
/* 3069 */       if (Trace.isOn) {
/* 3070 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSubscription,String,int,MQMsg2,MQPutMessageOptions,boolean)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3075 */       if (Trace.isOn) {
/* 3076 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessageInternal(MQSubscription,String,int,MQMsg2,MQPutMessageOptions,boolean)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3081 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] sendBrokerMessage(MQSubscription subscription, String command, int regOpts, MQMsg2 jmsMessage, MQPutMessageOptions pmo) throws JMSException {
/* 3092 */     if (Trace.isOn) {
/* 3093 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessage(MQSubscription,String,int,MQMsg2,MQPutMessageOptions)", new Object[] { subscription, command, 
/*      */             
/* 3095 */             Integer.valueOf(regOpts), jmsMessage, pmo });
/*      */     }
/*      */     
/* 3098 */     byte[] traceRet1 = sendBrokerMessageInternal(subscription, command, regOpts, jmsMessage, pmo, false);
/* 3099 */     if (Trace.isOn) {
/* 3100 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessage(MQSubscription,String,int,MQMsg2,MQPutMessageOptions)", traceRet1);
/*      */     }
/*      */     
/* 3103 */     return traceRet1;
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
/*      */   private byte[] sendBrokerMessage(MQSession mqs, MQBrokerMessage brokerMsg, MQMsg2 jmsMessage, MQPutMessageOptions pmo, boolean durable) throws JMSException {
/* 3115 */     if (Trace.isOn) {
/* 3116 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessage(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean)", new Object[] { mqs, brokerMsg, jmsMessage, pmo, 
/*      */             
/* 3118 */             Boolean.valueOf(durable) });
/*      */     }
/*      */     
/* 3121 */     byte[] traceRet1 = sendBrokerMessageInternal(mqs, brokerMsg, jmsMessage, pmo, false, durable, mqs.getFailIfQuiesce());
/* 3122 */     if (Trace.isOn) {
/* 3123 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "sendBrokerMessage(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean)", traceRet1);
/*      */     }
/*      */ 
/*      */     
/* 3127 */     return traceRet1;
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
/*      */   private byte[] setDeferredMessage(MQSubscription subscription, String command, int regOptions, MQMsg2 jmsMessage, MQPutMessageOptions pmo) throws JMSException {
/* 3152 */     if (Trace.isOn) {
/* 3153 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "setDeferredMessage(MQSubscription,String,int,MQMsg2,MQPutMessageOptions)", new Object[] { subscription, command, 
/*      */             
/* 3155 */             Integer.valueOf(regOptions), jmsMessage, pmo });
/*      */     }
/*      */     
/* 3158 */     byte[] deferredCorrelationID = null;
/* 3159 */     deferredCorrelationID = sendBrokerMessageInternal(subscription, command, regOptions, jmsMessage, pmo, true);
/* 3160 */     if (Trace.isOn) {
/* 3161 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "setDeferredMessage(MQSubscription,String,int,MQMsg2,MQPutMessageOptions)", deferredCorrelationID);
/*      */     }
/*      */ 
/*      */     
/* 3165 */     return deferredCorrelationID;
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
/*      */   private byte[] setDeferredMessage(MQSession mqs, MQBrokerMessage brokerMsg, MQMsg2 jmsMessage, MQPutMessageOptions pmo, boolean durable, int fiqBehaviour) throws JMSException {
/* 3186 */     if (Trace.isOn) {
/* 3187 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "setDeferredMessage(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean,int)", new Object[] { mqs, brokerMsg, jmsMessage, pmo, 
/*      */             
/* 3189 */             Boolean.valueOf(durable), 
/* 3190 */             Integer.valueOf(fiqBehaviour) });
/*      */     }
/*      */     
/* 3193 */     byte[] deferredCorrelationID = null;
/* 3194 */     deferredCorrelationID = sendBrokerMessageInternal(mqs, brokerMsg, jmsMessage, pmo, true, durable, fiqBehaviour);
/*      */     
/* 3196 */     if (Trace.isOn) {
/* 3197 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "setDeferredMessage(MQSession,MQBrokerMessage,MQMsg2,MQPutMessageOptions,boolean,int)", deferredCorrelationID);
/*      */     }
/*      */ 
/*      */     
/* 3201 */     return deferredCorrelationID;
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
/*      */   private void cancelDeferredMessage(MQSession mqs, byte[] messageID) throws JMSException {
/* 3216 */     if (Trace.isOn) {
/* 3217 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cancelDeferredMessage(MQSession,byte [ ])", new Object[] { mqs, messageID });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 3222 */       MQQueueManager qm = mqs.getQM();
/* 3223 */       MQQueueManager spiQm = qm;
/* 3224 */       if (!spiQm.spiSupportsDeferred()) {
/*      */ 
/*      */         
/* 3227 */         JMSException je = new JMSException("MQJMS3047");
/* 3228 */         if (Trace.isOn) {
/* 3229 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cancelDeferredMessage(MQSession,byte [ ])", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */         
/* 3233 */         throw je;
/*      */       } 
/* 3235 */       spiQm.spiCancelDeferred(messageID);
/*      */     
/*      */     }
/* 3238 */     catch (JMSException je) {
/* 3239 */       if (Trace.isOn) {
/* 3240 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cancelDeferredMessage(MQSession,byte [ ])", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */       
/* 3244 */       if (Trace.isOn) {
/* 3245 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cancelDeferredMessage(MQSession,byte [ ])", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */       
/* 3249 */       throw je;
/*      */     
/*      */     }
/* 3252 */     catch (MQException me) {
/* 3253 */       if (Trace.isOn) {
/* 3254 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cancelDeferredMessage(MQSession,byte [ ])", (Throwable)me, 2);
/*      */       }
/*      */ 
/*      */       
/* 3258 */       BrokerCommandFailedException je = new BrokerCommandFailedException(me.getMessage(), me.getErrorCode());
/* 3259 */       je.setLinkedException((Exception)me);
/* 3260 */       if (Trace.isOn) {
/* 3261 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cancelDeferredMessage(MQSession,byte [ ])", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */       
/* 3265 */       throw je;
/*      */     } 
/* 3267 */     if (Trace.isOn) {
/* 3268 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cancelDeferredMessage(MQSession,byte [ ])");
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
/*      */   private MQBrokerMessage getBrokerResponse(MQSession mqs, MQMsg2 sentMessage, boolean fullResponseRequested) throws JMSException {
/*      */     MQBrokerMessage responseMessage;
/* 3289 */     if (Trace.isOn) {
/* 3290 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2,boolean)", new Object[] { mqs, sentMessage, 
/*      */             
/* 3292 */             Boolean.valueOf(fullResponseRequested) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3300 */     MQMsg2 qResponse = null;
/*      */ 
/*      */     
/* 3303 */     MQQueue replyQ = null;
/*      */ 
/*      */     
/* 3306 */     MQGetMessageOptions brokerResponseGmo = null;
/*      */     
/* 3308 */     MQBrokerMessage originalBrokerMessage = null;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3313 */       qResponse = new MQMsg2();
/* 3314 */       qResponse.setCorrelationId(sentMessage.getMessageId());
/*      */ 
/*      */       
/*      */       try {
/* 3318 */         if (mqs.replyQ == null) {
/* 3319 */           replyQ = mqs.getResponseQueue();
/*      */         } else {
/* 3321 */           replyQ = mqs.replyQ;
/*      */         } 
/*      */         
/* 3324 */         brokerResponseGmo = new MQGetMessageOptions();
/* 3325 */         brokerResponseGmo.options = 1;
/* 3326 */         brokerResponseGmo.waitInterval = 120000;
/*      */ 
/*      */         
/* 3329 */         if (this.connection.getFailIfQuiesce() == 1) {
/* 3330 */           brokerResponseGmo.options |= 0x2000;
/*      */         }
/*      */         
/* 3333 */         if (Trace.isOn) {
/* 3334 */           Trace.traceData(this, "setting broker timeout to 120000", null);
/*      */         }
/*      */ 
/*      */         
/* 3338 */         replyQ.getMsg2(qResponse, brokerResponseGmo);
/*      */       }
/* 3340 */       catch (MQException e) {
/* 3341 */         if (Trace.isOn) {
/* 3342 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2,boolean)", (Throwable)e, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 3347 */         String key = "MQJMS5053";
/* 3348 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 3349 */         NoBrokerResponseException je = new NoBrokerResponseException(msg, key);
/* 3350 */         je.setLinkedException((Exception)e);
/* 3351 */         if (Trace.isOn) {
/* 3352 */           Trace.traceData(this, "error getting broker response", null);
/*      */         }
/* 3354 */         if (Trace.isOn) {
/* 3355 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2,boolean)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */         
/* 3359 */         throw je;
/*      */       } 
/*      */       
/* 3362 */       if (Trace.isOn) {
/* 3363 */         Trace.traceData(this, "got broker response", null);
/*      */       }
/* 3365 */       if (getMQConnection().getBrkVersion() == 0 || getMQConnection().getBrkVersion() == -1) {
/* 3366 */         if (Trace.isOn) {
/* 3367 */           Trace.traceData(this, "Creating RFH1 ProviderMessage", null);
/*      */         }
/* 3369 */         responseMessage = new RFH1BrokerMessageImpl();
/*      */       } else {
/* 3371 */         if (Trace.isOn) {
/* 3372 */           Trace.traceData(this, "Creating RFH2 ProviderMessage", null);
/*      */         }
/* 3374 */         responseMessage = new RFH2BrokerMessageImpl();
/*      */       } 
/* 3376 */       responseMessage.initializeFromMessage(qResponse);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3381 */       if (fullResponseRequested) {
/* 3382 */         if (Trace.isOn) {
/* 3383 */           Trace.traceData(this, "requested full Response. Did we get it?", null);
/*      */         }
/* 3385 */         if ((responseMessage.get("MQPSTopic") == null || responseMessage.get("MQPSTopic").equals("")) && 
/* 3386 */           Integer.parseInt(responseMessage.get("MQPSCompCode")) != 2)
/*      */         {
/*      */           
/* 3389 */           if (Trace.isOn) {
/* 3390 */             Trace.traceData(this, "No full response. Assume that this command failed", null);
/*      */           }
/*      */ 
/*      */           
/* 3394 */           if (Trace.isOn) {
/* 3395 */             Trace.traceData(this, "Get the original command", null);
/*      */           }
/* 3397 */           originalBrokerMessage = MQBrokerMessage.fromMessage(sentMessage);
/*      */           
/* 3399 */           if (originalBrokerMessage.get("MQPSCommand").equals("RegSub")) {
/* 3400 */             boolean sharedQueue = originalBrokerMessage.isOptionSet("MQPSRegOpts", "CorrelAsId");
/* 3401 */             cleanupUnwantedSubscription(mqs, sentMessage, sharedQueue);
/*      */           } 
/*      */ 
/*      */           
/* 3405 */           Trace.ffst(this, "getBrokerResponse", "XO00H001", null, NoBrokerResponseException.class);
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 3420 */     catch (JMSException je) {
/* 3421 */       if (Trace.isOn) {
/* 3422 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2,boolean)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */       
/* 3426 */       if (Trace.isOn) {
/* 3427 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2,boolean)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */       
/* 3431 */       throw je;
/*      */     } 
/* 3433 */     if (Trace.isOn) {
/* 3434 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2,boolean)", responseMessage);
/*      */     }
/*      */     
/* 3437 */     return responseMessage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   byte[] createSessionName(MQSession mqs) throws JMSException {
/* 3447 */     if (Trace.isOn) {
/* 3448 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "createSessionName(MQSession)", new Object[] { mqs });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3453 */     byte[] messageId = null;
/* 3454 */     MQQueueManager qm = mqs.getQM();
/* 3455 */     MQQueue queue = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3464 */       queue = qm.accessQueue("SYSTEM.JMS.REPORT.QUEUE", 8210);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3473 */       MQMessage tempMsg = new MQMessage();
/* 3474 */       MQPutMessageOptions pmo = new MQPutMessageOptions();
/*      */ 
/*      */       
/* 3477 */       if (this.connection.getFailIfQuiesce() == 1) {
/* 3478 */         pmo.options |= 0x2000;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3483 */       pmo.options |= 0x80;
/*      */       
/*      */       try {
/* 3486 */         tempMsg.writeString("sessGen");
/*      */       }
/* 3488 */       catch (IOException ie) {
/* 3489 */         if (Trace.isOn) {
/* 3490 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "createSessionName(MQSession)", ie, 1);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3497 */       tempMsg.expiry = 1;
/* 3498 */       tempMsg.persistence = 0;
/* 3499 */       queue.put(tempMsg, pmo);
/* 3500 */       messageId = tempMsg.messageId;
/*      */       try {
/* 3502 */         queue.get(tempMsg);
/*      */       }
/* 3504 */       catch (MQException me) {
/* 3505 */         if (Trace.isOn) {
/* 3506 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "createSessionName(MQSession)", (Throwable)me, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 3511 */         if (me.reasonCode != 2033) {
/* 3512 */           if (Trace.isOn) {
/* 3513 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "createSessionName(MQSession)", (Throwable)me, 1);
/*      */           }
/*      */ 
/*      */           
/* 3517 */           throw me;
/*      */         }
/*      */       
/*      */       } 
/* 3521 */     } catch (MQException ex) {
/* 3522 */       JMSException je; if (Trace.isOn) {
/* 3523 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "createSessionName(MQSession)", (Throwable)ex, 3);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3529 */       if (ex.reasonCode == 2085) {
/*      */         
/* 3531 */         je = ConfigEnvironment.newException("MQJMS3014", "SYSTEM.JMS.PS.STATUS.QUEUE");
/*      */       } else {
/* 3533 */         je = ConfigEnvironment.newException("MQJMS3005", ex);
/*      */       } 
/*      */       
/* 3536 */       je.setLinkedException((Exception)ex);
/*      */       
/* 3538 */       if (Trace.isOn) {
/* 3539 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "createSessionName(MQSession)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */       
/* 3543 */       throw je;
/*      */     }
/*      */     finally {
/*      */       
/* 3547 */       if (Trace.isOn) {
/* 3548 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "createSessionName(MQSession)");
/*      */       }
/*      */ 
/*      */       
/* 3552 */       if (queue != null) {
/*      */         try {
/* 3554 */           queue.close();
/*      */         }
/* 3556 */         catch (MQException mqe) {
/* 3557 */           if (Trace.isOn) {
/* 3558 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "createSessionName(MQSession)", (Throwable)mqe, 4);
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 3566 */     if (Trace.isOn) {
/* 3567 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "createSessionName(MQSession)", messageId);
/*      */     }
/*      */     
/* 3570 */     return messageId;
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
/*      */   private MQBrokerMessage cleanupUnconsumedMessages(MQSession mqs, boolean sharedQueue, String queueName, byte[] correlationId, MQMsg2 sentMessage) throws JMSException {
/* 3583 */     if (Trace.isOn) {
/* 3584 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", new Object[] { mqs, 
/*      */             
/* 3586 */             Boolean.valueOf(sharedQueue), queueName, correlationId, sentMessage });
/*      */     }
/*      */     
/* 3589 */     int cleanupLevel = this.cleanupLevel;
/* 3590 */     int openOptions = 0;
/* 3591 */     MQQueue reportQueue = null;
/*      */     
/* 3593 */     MQGetMessageOptions brokerResponseGmo = null;
/* 3594 */     MQMsg2 responseMessage = null;
/* 3595 */     MQBrokerMessage brokerResponse = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3601 */       if (cleanupLevel == 0) {
/* 3602 */         if (Trace.isOn) {
/* 3603 */           Trace.traceData(this, "Cleanup level is NONE; skipping cleanup", null);
/*      */         }
/* 3605 */         if (Trace.isOn) {
/* 3606 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", null, 1);
/*      */         }
/*      */ 
/*      */         
/* 3610 */         return null;
/*      */       } 
/*      */       
/* 3613 */       openOptions = 10;
/*      */ 
/*      */       
/* 3616 */       if (this.connection.getFailIfQuiesce() == 1) {
/* 3617 */         openOptions |= 0x2000;
/*      */       }
/*      */       
/*      */       try {
/* 3621 */         if (Trace.isOn) {
/* 3622 */           Trace.traceData(this, "opening report queue", null);
/*      */         }
/* 3624 */         reportQueue = mqs.getQM().accessQueue("SYSTEM.JMS.REPORT.QUEUE", openOptions);
/*      */       
/*      */       }
/* 3627 */       catch (MQException mqe) {
/* 3628 */         if (Trace.isOn) {
/* 3629 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", (Throwable)mqe, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3635 */         if (Trace.isOn) {
/* 3636 */           Trace.traceData(this, "exception thrown opening report queue", null);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 3641 */         if (mqe.reasonCode == 2042) {
/* 3642 */           if ((openOptions & 0x2) == 2) {
/*      */             
/* 3644 */             JMSException jMSException1 = ConfigEnvironment.newException("MQJMS3045", "SYSTEM.JMS.REPORT.QUEUE");
/* 3645 */             jMSException1.setLinkedException((Exception)mqe);
/* 3646 */             if (Trace.isOn) {
/* 3647 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", (Throwable)jMSException1, 1);
/*      */             }
/*      */ 
/*      */             
/* 3651 */             throw jMSException1;
/*      */           } 
/*      */           
/* 3654 */           JMSException jMSException = ConfigEnvironment.newException("MQJMS3046", "SYSTEM.JMS.REPORT.QUEUE");
/* 3655 */           jMSException.setLinkedException((Exception)mqe);
/* 3656 */           if (Trace.isOn) {
/* 3657 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", (Throwable)jMSException, 2);
/*      */           }
/*      */ 
/*      */           
/* 3661 */           throw jMSException;
/*      */         } 
/*      */         
/* 3664 */         JMSException je = ConfigEnvironment.newException("MQJMS2008", "SYSTEM.JMS.REPORT.QUEUE");
/* 3665 */         je.setLinkedException((Exception)mqe);
/* 3666 */         if (Trace.isOn) {
/* 3667 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", (Throwable)je, 3);
/*      */         }
/*      */ 
/*      */         
/* 3671 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 3676 */         brokerResponseGmo = new MQGetMessageOptions();
/* 3677 */         brokerResponseGmo.options = 545;
/* 3678 */         brokerResponseGmo.waitInterval = 120000;
/*      */ 
/*      */         
/* 3681 */         if (this.connection.getFailIfQuiesce() == 1) {
/* 3682 */           brokerResponseGmo.options |= 0x2000;
/*      */         }
/*      */         
/* 3685 */         if (Trace.isOn) {
/* 3686 */           Trace.traceData(this, "setting broker timeout to 120000", null);
/*      */         }
/*      */         
/* 3689 */         responseMessage = new MQMsg2();
/* 3690 */         responseMessage.setCorrelationId(sentMessage.getMessageId());
/* 3691 */         if (Trace.isOn) {
/* 3692 */           Trace.traceData(this, "Setting reponse correlationId = " + Utils.bytesToHex(sentMessage.getMessageId()), null);
/*      */         }
/*      */ 
/*      */         
/* 3696 */         reportQueue.getMsg2(responseMessage, brokerResponseGmo);
/*      */       }
/* 3698 */       catch (MQException e) {
/* 3699 */         if (Trace.isOn) {
/* 3700 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", (Throwable)e, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 3705 */         if (Trace.isOn) {
/* 3706 */           Trace.traceData(this, "exception thrown getting response message from report queue", null);
/*      */         }
/* 3708 */         String key = "MQJMS5053";
/* 3709 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 3710 */         NoBrokerResponseException je = new NoBrokerResponseException(msg, key);
/* 3711 */         je.setLinkedException((Exception)e);
/*      */         
/* 3713 */         if (Trace.isOn) {
/* 3714 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", (Throwable)je, 4);
/*      */         }
/*      */ 
/*      */         
/* 3718 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 3723 */         if (Trace.isOn) {
/* 3724 */           Trace.traceData(this, "got MQ response message, converting to MQBrokerMessage", null);
/*      */         }
/* 3726 */         brokerResponse = MQBrokerMessage.fromMessage(responseMessage);
/*      */       }
/* 3728 */       catch (JMSException je) {
/* 3729 */         if (Trace.isOn) {
/* 3730 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", (Throwable)je, 3);
/*      */         }
/*      */ 
/*      */         
/* 3734 */         if (Trace.isOn) {
/* 3735 */           Trace.traceData(this, "exception thrown while creating MQBrokerMessage", null);
/*      */         }
/* 3737 */         if (Trace.isOn) {
/* 3738 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", (Throwable)je, 5);
/*      */         }
/*      */ 
/*      */         
/* 3742 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 3746 */       int reasonCode = Integer.parseInt(brokerResponse.get("MQPSCompCode"));
/*      */       
/* 3748 */       if (reasonCode != 0) {
/* 3749 */         if (Trace.isOn) {
/* 3750 */           Trace.traceData(this, "The broker deregister command failed. Abort cleanup and throw exception", null);
/*      */         }
/*      */ 
/*      */         
/* 3754 */         String reasonText = brokerResponse.get("MQPSReasonText");
/* 3755 */         JMSException je = new JMSException(reasonText);
/*      */         
/* 3757 */         if (Trace.isOn) {
/* 3758 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", (Throwable)je, 6);
/*      */         }
/*      */ 
/*      */         
/* 3762 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3769 */         if (sharedQueue) {
/* 3770 */           if (Trace.isOn) {
/* 3771 */             Trace.traceData(this, "shared queue, calling removeMessages", null);
/*      */           }
/* 3773 */           Cleanup.removeMessages(mqs.getQM(), queueName, correlationId);
/*      */         } else {
/* 3775 */           if (Trace.isOn) {
/* 3776 */             Trace.traceData(this, "dynamic queue, calling removeDynamicQueue", null);
/*      */           }
/*      */ 
/*      */           
/* 3780 */           Cleanup.removeDynamicQueue(mqs.getQM(), queueName);
/*      */         }
/*      */       
/* 3783 */       } catch (JMSException je) {
/* 3784 */         if (Trace.isOn) {
/* 3785 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", (Throwable)je, 4);
/*      */         }
/*      */ 
/*      */         
/* 3789 */         if (Trace.isOn) {
/* 3790 */           Trace.traceData(this, "exception thrown while attempting to clean  subscriber queue", null);
/*      */         }
/*      */         
/* 3793 */         if (Trace.isOn) {
/* 3794 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", (Throwable)je, 7);
/*      */         }
/*      */ 
/*      */         
/* 3798 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3804 */         if (Trace.isOn) {
/* 3805 */           Trace.traceData(this, "Removing message", null);
/*      */         }
/*      */         
/* 3808 */         brokerResponseGmo.options = 320;
/*      */ 
/*      */         
/* 3811 */         if (this.connection.getFailIfQuiesce() == 1) {
/* 3812 */           brokerResponseGmo.options |= 0x2000;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 3817 */         reportQueue.getMsg2(responseMessage, brokerResponseGmo, 1);
/*      */       }
/* 3819 */       catch (MQException mqe) {
/* 3820 */         if (Trace.isOn) {
/* 3821 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", (Throwable)mqe, 5);
/*      */         }
/*      */ 
/*      */         
/* 3825 */         if (mqe.reasonCode != 2079) {
/*      */ 
/*      */ 
/*      */           
/* 3829 */           String detail = "Couldn't get locked message from queue (" + mqe + ")";
/* 3830 */           JMSException je = ConfigEnvironment.newException("MQJMS1016", detail);
/* 3831 */           je.setLinkedException((Exception)mqe);
/* 3832 */           if (Trace.isOn) {
/* 3833 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", (Throwable)je, 8);
/*      */           }
/*      */ 
/*      */           
/* 3837 */           throw je;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3844 */       if (Trace.isOn) {
/* 3845 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", brokerResponse, 2);
/*      */       }
/*      */ 
/*      */       
/* 3849 */       return brokerResponse;
/*      */ 
/*      */     
/*      */     }
/* 3853 */     catch (JMSException je) {
/* 3854 */       if (Trace.isOn) {
/* 3855 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", (Throwable)je, 6);
/*      */       }
/*      */ 
/*      */       
/* 3859 */       if (Trace.isOn) {
/* 3860 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", (Throwable)je, 9);
/*      */       }
/*      */ 
/*      */       
/* 3864 */       throw je;
/*      */     }
/*      */     finally {
/*      */       
/* 3868 */       if (Trace.isOn) {
/* 3869 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)");
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/* 3874 */         if (reportQueue != null && reportQueue.isOpen()) {
/* 3875 */           reportQueue.close();
/*      */         }
/*      */       }
/* 3878 */       catch (MQException mqe) {
/* 3879 */         if (Trace.isOn) {
/* 3880 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnconsumedMessages(MQSession,boolean,String,byte [ ],MQMsg2)", (Throwable)mqe, 7);
/*      */         }
/*      */ 
/*      */         
/* 3884 */         if (Trace.isOn) {
/* 3885 */           Trace.traceData(this, "exception thrown closing reportQueue. Not much we can do about this though.", null);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void cleanupUnwantedSubscription(MQSession mqs, MQMsg2 sentMessage, boolean sharedQueue) {
/* 3897 */     if (Trace.isOn) {
/* 3898 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnwantedSubscription(MQSession,MQMsg2,boolean)", new Object[] { mqs, sentMessage, 
/*      */             
/* 3900 */             Boolean.valueOf(sharedQueue) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3906 */     MQMsg2 jmsMessage = new MQMsg2();
/*      */     
/* 3908 */     MQBrokerMessage originalBrokerMessage = null;
/* 3909 */     MQBrokerMessage newBrokerMessage = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3916 */       if (Trace.isOn) {
/* 3917 */         Trace.traceData(this, "resetting read position", null);
/*      */       }
/*      */       try {
/* 3920 */         sentMessage.resetReadPosition();
/*      */       }
/* 3922 */       catch (MQException mie) {
/* 3923 */         if (Trace.isOn) {
/* 3924 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnwantedSubscription(MQSession,MQMsg2,boolean)", (Throwable)mie, 1);
/*      */         }
/*      */ 
/*      */         
/* 3928 */         if (Trace.isOn) {
/* 3929 */           Trace.traceData(this, "error resetting read position", null);
/*      */         }
/*      */       } 
/* 3932 */       originalBrokerMessage = MQBrokerMessage.fromMessage(sentMessage);
/*      */       
/* 3934 */       if (getMQConnection().getBrkVersion() == 0 || getMQConnection().getBrkVersion() == -1) {
/* 3935 */         if (Trace.isOn) {
/* 3936 */           Trace.traceData(this, "Composing new RFH1 message to deregister", null);
/*      */         }
/* 3938 */         newBrokerMessage = new RFH1BrokerMessageImpl();
/*      */       } else {
/* 3940 */         if (Trace.isOn) {
/* 3941 */           Trace.traceData(this, "Composing new RFH2 message to deregister", null);
/*      */         }
/* 3943 */         newBrokerMessage = new RFH2BrokerMessageImpl();
/*      */       } 
/*      */       
/* 3946 */       if (Trace.isOn) {
/* 3947 */         Trace.traceData(this, "Setting command to deregister", null);
/*      */       }
/* 3949 */       newBrokerMessage.set("MQPSCommand", "DeregSub");
/*      */       
/* 3951 */       String originalStreamName = originalBrokerMessage.get("MQPSStreamName");
/* 3952 */       String originalQMgrName = originalBrokerMessage.get("MQPSQMgrName");
/* 3953 */       String originalQName = originalBrokerMessage.get("MQPSQName");
/*      */       
/* 3955 */       if (originalStreamName != null) {
/* 3956 */         if (Trace.isOn) {
/* 3957 */           Trace.traceData(this, "Setting stream name to '" + originalStreamName + "'", null);
/*      */         }
/* 3959 */         newBrokerMessage.set("MQPSStreamName", originalStreamName);
/*      */       } 
/* 3961 */       if (originalQMgrName != null) {
/* 3962 */         if (Trace.isOn) {
/* 3963 */           Trace.traceData(this, "Setting qMgr name to '" + originalQMgrName + "'", null);
/*      */         }
/* 3965 */         newBrokerMessage.set("MQPSQMgrName", originalQMgrName);
/*      */       } 
/* 3967 */       if (originalQName != null) {
/* 3968 */         if (Trace.isOn) {
/* 3969 */           Trace.traceData(this, "Setting qName to '" + originalQName + "'", null);
/*      */         }
/* 3971 */         newBrokerMessage.set("MQPSQName", originalQName);
/*      */       } 
/* 3973 */       if (Trace.isOn && sharedQueue) {
/* 3974 */         Trace.traceData(this, "Shared queue, setting corelId as Id", null);
/*      */       }
/* 3976 */       if (sharedQueue) {
/* 3977 */         newBrokerMessage.setOption("MQPSRegOpts", 1);
/*      */       }
/*      */       
/* 3980 */       newBrokerMessage.setOption("MQPSRegOpts", 64);
/*      */       
/* 3982 */       newBrokerMessage.writeToMessage(jmsMessage);
/*      */ 
/*      */       
/* 3985 */       jmsMessage.setMessageType(1);
/* 3986 */       jmsMessage.setReport(0);
/*      */       
/* 3988 */       if (sharedQueue)
/*      */       {
/*      */         
/* 3991 */         jmsMessage.setCorrelationId(sentMessage.getCorrelationId());
/*      */       }
/*      */       
/*      */       try {
/* 3995 */         sendBrokerMessage(mqs, newBrokerMessage, jmsMessage, (MQPutMessageOptions)null, false);
/*      */       
/*      */       }
/* 3998 */       catch (JMSException je) {
/* 3999 */         if (Trace.isOn) {
/* 4000 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnwantedSubscription(MQSession,MQMsg2,boolean)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */         
/* 4004 */         if (Trace.isOn) {
/* 4005 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnwantedSubscription(MQSession,MQMsg2,boolean)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */         
/* 4009 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4018 */         cleanupUnconsumedMessages(mqs, sharedQueue, originalBrokerMessage.get("MQPSQName"), sentMessage.getCorrelationId(), jmsMessage);
/*      */       }
/* 4020 */       catch (JMSException je) {
/* 4021 */         if (Trace.isOn) {
/* 4022 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnwantedSubscription(MQSession,MQMsg2,boolean)", (Throwable)je, 3);
/*      */         }
/*      */ 
/*      */         
/* 4026 */         if (Trace.isOn) {
/* 4027 */           Trace.traceData(this, "exception thrown dealing with broker response and subscriber queue cleanup", null);
/*      */         }
/* 4029 */         if (Trace.isOn) {
/* 4030 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnwantedSubscription(MQSession,MQMsg2,boolean)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */         
/* 4034 */         throw je;
/*      */       }
/*      */     
/* 4037 */     } catch (JMSException je) {
/* 4038 */       if (Trace.isOn) {
/* 4039 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnwantedSubscription(MQSession,MQMsg2,boolean)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */       
/* 4043 */       if (Trace.isOn) {
/* 4044 */         Trace.traceData(this, "Problems encountered attempting to deal with an erroneously created subscription.", null);
/* 4045 */         Trace.traceData(this, "Subscription could still exist causing unwanted messages to be delivered", null);
/*      */       } 
/*      */     } 
/*      */     
/* 4049 */     if (Trace.isOn)
/* 4050 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscriptionEngine", "cleanupUnwantedSubscription(MQSession,MQMsg2,boolean)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQBrokerSubscriptionEngine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */