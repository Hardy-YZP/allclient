/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.jms.BrokerCommandFailedException;
/*      */ import com.ibm.mq.jms.NoBrokerResponseException;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQC;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMessage;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class MQQueueSubscriptionEngine
/*      */   extends MQSubscriptionEngine
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQQueueSubscriptionEngine.java";
/*      */   private static final int REGISTER_SUBSCRIBER = 1;
/*      */   private static final int DEREGISTER_SUBSCRIBER = 2;
/*      */   private static final int REGISTER_PUBLISHER = 3;
/*      */   private static final int DEREGISTER_PUBLISHER = 4;
/*      */   private MQPSStatusMgr pubsubStatusMgr;
/*      */   
/*      */   static {
/*   77 */     if (Trace.isOn) {
/*   78 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQQueueSubscriptionEngine.java");
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
/*   96 */   private StatusMgrCloseLock statusMgrCloseLock = new StatusMgrCloseLock();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] statusMgrId;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQQueueSubscriptionEngine(MQConnection connection, MQQueueManager qm) {
/*  108 */     super(connection);
/*  109 */     if (Trace.isOn) {
/*  110 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "<init>(MQConnection,MQQueueManager)", new Object[] { connection, qm });
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
/*      */     try {
/*  122 */       this.pubsubStatusMgr = new MQPSStatusMgr(qm, connection.getBrkControlQueue(), connection.getBrkPubQueue());
/*      */ 
/*      */ 
/*      */       
/*  126 */       this.statusMgrId = this.pubsubStatusMgr.addConnection(connection);
/*      */     }
/*  128 */     catch (JMSException je) {
/*  129 */       if (Trace.isOn) {
/*  130 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "<init>(MQConnection,MQQueueManager)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/*  134 */       if (Trace.isOn) {
/*  135 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "<init>(MQConnection,MQQueueManager)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  140 */     if (Trace.isOn) {
/*  141 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "<init>(MQConnection,MQQueueManager)", 2);
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
/*      */   public MQSubscription openSubscription(MQSession mqs, WMQDestination topic, String selector, boolean noLocal, boolean sharedQueue, String queueName) throws JMSException {
/*  166 */     if (Trace.isOn) {
/*  167 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", new Object[] { mqs, topic, selector, 
/*      */             
/*  169 */             Boolean.valueOf(noLocal), Boolean.valueOf(sharedQueue), queueName });
/*      */     }
/*  171 */     MQQueue subscriberQueue = null;
/*  172 */     MQQueueSubscription subscription = null;
/*  173 */     byte[] correlationId = null;
/*  174 */     boolean registered = false;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  179 */       int fiqBehaviour = 1;
/*  180 */       if (topic != null) {
/*      */         
/*  182 */         fiqBehaviour = topic.getIntProperty("failIfQuiesce");
/*      */       }
/*      */       else {
/*      */         
/*  186 */         fiqBehaviour = mqs.getFailIfQuiesce();
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  191 */         subscription = new MQQueueSubscription(this, mqs, false, sharedQueue, mqs.getQMName(), getMQConnection().getClientID(), null, topic, selector, noLocal, queueName, null, null);
/*      */         
/*  193 */         if (Trace.isOn) {
/*  194 */           Trace.traceData(this, "setting StatusMgrId = ", this.statusMgrId);
/*      */         }
/*  196 */         subscription.setStatusMgrId(this.statusMgrId);
/*  197 */         subscriberQueue = openSubscriberQueue(mqs, queueName, sharedQueue, false, fiqBehaviour);
/*  198 */         subscription.setSubscriberQueue(subscriberQueue);
/*  199 */         subscription.setQueueName(subscriberQueue.name);
/*      */       }
/*  201 */       catch (JMSException je) {
/*  202 */         if (Trace.isOn) {
/*  203 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  208 */         if (Trace.isOn) {
/*  209 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */         
/*  213 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  219 */         correlationId = addNDSubscriptionEntry(subscription);
/*  220 */         subscription.setCorrelationId(correlationId);
/*  221 */         if (Trace.isOn) {
/*  222 */           Trace.traceData(this, "Using correl ID = ", correlationId);
/*      */         
/*      */         }
/*      */       }
/*  226 */       catch (JMSException je) {
/*  227 */         if (Trace.isOn) {
/*  228 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  233 */         if (Trace.isOn) {
/*  234 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */         
/*  238 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  243 */         sendBrokerCommand(subscription, 1, true);
/*      */         
/*  245 */         registered = true;
/*      */       }
/*  247 */       catch (NoBrokerResponseException nbre) {
/*  248 */         if (Trace.isOn) {
/*  249 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)nbre, 3);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  257 */         registered = true;
/*  258 */         if (Trace.isOn) {
/*  259 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)nbre, 3);
/*      */         }
/*      */ 
/*      */         
/*  263 */         throw nbre;
/*      */       }
/*  265 */       catch (BrokerCommandFailedException bcfe) {
/*  266 */         if (Trace.isOn) {
/*  267 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)bcfe, 4);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  272 */         if (Trace.isOn) {
/*  273 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)bcfe, 4);
/*      */         }
/*      */ 
/*      */         
/*  277 */         throw bcfe;
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  282 */     catch (JMSException je) {
/*  283 */       if (Trace.isOn) {
/*  284 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)je, 5);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  289 */       if (Trace.isOn) {
/*  290 */         Trace.traceData(this, "cleaning up partial subscription", null);
/*      */       }
/*      */       
/*  293 */       boolean cleanupProblems = false;
/*      */       
/*  295 */       if (registered) {
/*      */         
/*      */         try {
/*      */           
/*  299 */           if (Trace.isOn) {
/*  300 */             Trace.traceData(this, "deregistering", null);
/*      */           }
/*      */           
/*  303 */           sendBrokerCommand(subscription, 2, true);
/*      */         
/*      */         }
/*  306 */         catch (JMSException sce) {
/*  307 */           if (Trace.isOn) {
/*  308 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)sce, 6);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  314 */           cleanupProblems = true;
/*  315 */           if (Trace.isOn) {
/*  316 */             Trace.traceData(this, "the cancel command also failed.", null);
/*      */           }
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  324 */       if (subscriberQueue != null) {
/*      */         try {
/*  326 */           if (Trace.isOn) {
/*  327 */             Trace.traceData(this, "deleting subscription queue", null);
/*      */           }
/*  329 */           if (!sharedQueue) {
/*  330 */             subscriberQueue.closeOptions = 2;
/*      */           }
/*  332 */           subscriberQueue.close();
/*      */         }
/*  334 */         catch (Exception e) {
/*  335 */           if (Trace.isOn) {
/*  336 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", e, 7);
/*      */           }
/*      */ 
/*      */           
/*  340 */           cleanupProblems = true;
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  346 */       if (!cleanupProblems) {
/*  347 */         removeNDSubscriptionEntry(subscription);
/*      */       }
/*      */       
/*  350 */       if (Trace.isOn) {
/*  351 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)je, 5);
/*      */       }
/*      */ 
/*      */       
/*  355 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/*  359 */     if (Trace.isOn) {
/*  360 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", subscription);
/*      */     }
/*      */     
/*  363 */     return subscription;
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
/*      */   public void closeSubscription(MQSubscription sub) throws JMSException {
/*  376 */     if (Trace.isOn) {
/*  377 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "closeSubscription(MQSubscription)", new Object[] { sub });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  382 */       MQQueueSubscription subscription = null;
/*  383 */       if (!(sub instanceof MQQueueSubscription)) {
/*  384 */         JMSException je = ConfigEnvironment.newException("MQJMS1016");
/*      */         
/*  386 */         if (Trace.isOn) {
/*  387 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "closeSubscription(MQSubscription)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */         
/*  391 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*  395 */       subscription = (MQQueueSubscription)sub;
/*      */       
/*  397 */       if (!subscription.isDurable()) {
/*      */         try {
/*  399 */           if (subscription.isClosed()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  406 */             if (subscription.getSubscriberQueue() != null) {
/*  407 */               SubscriptionHelper.deleteSubscriberMessages(subscription);
/*      */             
/*      */             }
/*      */           }
/*      */           else {
/*      */             
/*  413 */             deleteSubscriber(subscription);
/*      */           } 
/*      */           
/*  416 */           subscription.setSubscriberQueue(null);
/*      */         
/*      */         }
/*  419 */         catch (JMSException je) {
/*  420 */           if (Trace.isOn) {
/*  421 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "closeSubscription(MQSubscription)", (Throwable)je, 1);
/*      */           }
/*      */ 
/*      */           
/*  425 */           Exception e = je.getLinkedException();
/*  426 */           if (e instanceof MQException && ((MQException)e).reasonCode == 2055) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  432 */             if (Trace.isOn) {
/*  433 */               Trace.traceData(this, "close ignoring MQException 2055", null);
/*      */             }
/*      */           } else {
/*      */             
/*  437 */             if (Trace.isOn) {
/*  438 */               Trace.traceData(this, "closeSubscription() ignoring exception.", null);
/*      */             }
/*  440 */             subscription.setSubscriberQueue(null);
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  448 */         JMSException je = ConfigEnvironment.newException("MQJMS1016");
/*      */         
/*  450 */         if (Trace.isOn) {
/*  451 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "closeSubscription(MQSubscription)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */         
/*  455 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  460 */       if (!subscription.isClosed() && !subscription.isDurable())
/*      */       {
/*  462 */         removeNDSubscriptionEntry(subscription);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  467 */     catch (JMSException je) {
/*  468 */       if (Trace.isOn) {
/*  469 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "closeSubscription(MQSubscription)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */       
/*  473 */       if (Trace.isOn) {
/*  474 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "closeSubscription(MQSubscription)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */       
/*  478 */       throw je;
/*      */     } 
/*      */     
/*  481 */     if (Trace.isOn) {
/*  482 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "closeSubscription(MQSubscription)");
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
/*      */   public MQSubscription openDurableSubscription(MQSession mqs, WMQDestination topic, String selector, boolean noLocal, boolean sharedQueue, String queueNameP, String subName) throws JMSException {
/*  512 */     if (Trace.isOn) {
/*  513 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", new Object[] { mqs, topic, selector, 
/*      */             
/*  515 */             Boolean.valueOf(noLocal), Boolean.valueOf(sharedQueue), queueNameP, subName });
/*      */     }
/*      */     
/*  518 */     String queueName = queueNameP;
/*  519 */     MQQueue adminQueue = null;
/*  520 */     MQQueueSubscription subscription = null;
/*  521 */     MQQueueSubscription testSub = null;
/*  522 */     MQQueueSubscription returnSub = null;
/*      */ 
/*      */     
/*      */     try {
/*  526 */       if (Trace.isOn) {
/*  527 */         Trace.traceData(this, "Topic name = " + topic.getStringProperty("XMSC_DESTINATION_NAME"), null);
/*  528 */         Trace.traceData(this, "Sub Q name = " + queueName, null);
/*  529 */         Trace.traceData(this, "sub Name   = " + subName, null);
/*  530 */         Trace.traceData(this, "Selector   = " + selector, null);
/*  531 */         Trace.traceData(this, "noLocal    = " + noLocal, null);
/*      */       } 
/*      */ 
/*      */       
/*  535 */       int fiqBehaviour = 1;
/*  536 */       if (topic != null) {
/*      */         
/*  538 */         fiqBehaviour = topic.getIntProperty("failIfQuiesce");
/*      */       }
/*      */       else {
/*      */         
/*  542 */         fiqBehaviour = mqs.getFailIfQuiesce();
/*      */       } 
/*      */       
/*  545 */       adminQueue = getAdminQueueAccess(mqs, fiqBehaviour);
/*      */       
/*  547 */       String clientID = getMQConnection().getClientID();
/*      */ 
/*      */ 
/*      */       
/*  551 */       if (clientID == null) {
/*  552 */         JMSException je = ConfigEnvironment.newException("MQJMS3024");
/*  553 */         if (Trace.isOn) {
/*  554 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  559 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  565 */       testSub = new MQQueueSubscription(this, mqs, true, sharedQueue, mqs.getQMName(), clientID, subName, topic, selector, noLocal, queueName, null, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  574 */       subscription = getResolvedSubscription(adminQueue, testSub, (byte[])null, false);
/*      */ 
/*      */       
/*  577 */       if (subscription == null) {
/*      */         
/*  579 */         if (Trace.isOn) {
/*  580 */           Trace.traceData(this, "DurSubEntry not found; new durable subscriber", null);
/*  581 */           Trace.traceData(this, "setting StatusMgrId = ", this.statusMgrId);
/*      */         } 
/*  583 */         testSub.setStatusMgrId(this.statusMgrId);
/*  584 */         returnSub = createNewDurableSubscription(adminQueue, testSub);
/*      */       } else {
/*      */         
/*  587 */         subscription.setMQTopic(topic);
/*      */ 
/*      */ 
/*      */         
/*  591 */         String canonSel1 = testSub.getSelector();
/*  592 */         if (canonSel1 == null) {
/*  593 */           canonSel1 = "";
/*      */         }
/*  595 */         String canonSel2 = subscription.getSelector();
/*  596 */         if (canonSel2 == null) {
/*  597 */           canonSel2 = "";
/*      */         }
/*      */         
/*  600 */         if (testSub.getFullName().equals(subscription.getFullName()) && testSub.getTopic().equals(subscription.getTopic()) && canonSel1.equals(canonSel2))
/*      */         {
/*      */ 
/*      */           
/*  604 */           if (Trace.isOn) {
/*  605 */             Trace.traceData(this, "Found an entry for this client", null);
/*  606 */             Trace.traceData(this, "Name                  = " + subscription.getSubName(), null);
/*  607 */             Trace.traceData(this, "Topic name            = " + subscription.getTopic(), null);
/*  608 */             Trace.traceData(this, "Subscriber queue name = " + subscription.getQueueName(), null);
/*      */             
/*  610 */             Trace.traceData(this, "Comparing given subscriber queue name: " + queueName, null);
/*      */           } 
/*      */ 
/*      */           
/*  614 */           String originalQueueName = subscription.getQueueName().trim();
/*  615 */           queueName = queueName.trim();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  621 */           if (!originalQueueName.equals(queueName)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  642 */             if (queueName.endsWith("*")) {
/*      */ 
/*      */ 
/*      */               
/*  646 */               if (subscription.isSharedQueue() || !originalQueueName.regionMatches(0, queueName, 0, queueName.length() - 1))
/*      */               {
/*  648 */                 JMSException je = ConfigEnvironment.newException("MQJMS3022", queueName, originalQueueName);
/*  649 */                 if (Trace.isOn) {
/*  650 */                   Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 2);
/*      */                 }
/*      */ 
/*      */ 
/*      */                 
/*  655 */                 throw je;
/*      */               }
/*      */             
/*      */             }
/*      */             else {
/*      */               
/*  661 */               JMSException je = ConfigEnvironment.newException("MQJMS3022", queueName, originalQueueName);
/*  662 */               if (Trace.isOn) {
/*  663 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 3);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  668 */               throw je;
/*      */             } 
/*      */ 
/*      */             
/*  672 */             if (Trace.isOn) {
/*  673 */               Trace.traceData(this, "Using original subscriber queue: " + originalQueueName, null);
/*      */             }
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  682 */           if (Trace.isOn) {
/*  683 */             Trace.traceData(this, "Reset durable subscriber options", null);
/*      */           }
/*      */           
/*      */           try {
/*  687 */             sendBrokerCommand(subscription, 1, true);
/*      */           }
/*  689 */           catch (NoBrokerResponseException nbre) {
/*  690 */             if (Trace.isOn) {
/*  691 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)nbre, 1);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  698 */             if (Trace.isOn) {
/*  699 */               Trace.traceData(this, "re-subscribe command failed", null);
/*      */             }
/*      */ 
/*      */             
/*  703 */             if (Trace.isOn) {
/*  704 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)nbre, 4);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  709 */             throw nbre;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  714 */           returnSub = createExistingDurableSubscription(adminQueue, subscription);
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*  724 */           if (Trace.isOn) {
/*  725 */             Trace.traceData(this, "Updating a durable subscriber:", null);
/*  726 */             Trace.traceData(this, "Old subscriber: " + subscription.toString(), null);
/*  727 */             Trace.traceData(this, "New subscriber: " + testSub.toString(), null);
/*      */           } 
/*      */           
/*  730 */           returnSub = createUpdatedDurableSubscriber(adminQueue, testSub);
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*  739 */     catch (JMSException je) {
/*  740 */       if (Trace.isOn) {
/*  741 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  746 */       if (Trace.isOn) {
/*  747 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 5);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  752 */       throw je;
/*      */     } finally {
/*      */       
/*  755 */       if (Trace.isOn) {
/*  756 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  761 */       if (adminQueue != null) {
/*      */         try {
/*  763 */           adminQueue.close();
/*      */         }
/*  765 */         catch (MQException mqe) {
/*  766 */           if (Trace.isOn) {
/*  767 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)mqe, 3);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  772 */           JMSException je2 = new JMSException("MQJMS2000");
/*  773 */           je2.setLinkedException((Exception)mqe);
/*  774 */           je2.initCause((Throwable)mqe);
/*  775 */           if (Trace.isOn) {
/*  776 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je2, 6);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  781 */           throw je2;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  786 */     if (Trace.isOn) {
/*  787 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", returnSub);
/*      */     }
/*      */ 
/*      */     
/*  791 */     return returnSub;
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
/*      */   public void closeDurableSubscription(MQSubscription sub) throws JMSException {
/*  804 */     if (Trace.isOn) {
/*  805 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "closeDurableSubscription(MQSubscription)", new Object[] { sub });
/*      */     }
/*      */     
/*  808 */     MQQueue adminQueue = null;
/*      */     
/*      */     try {
/*  811 */       MQQueueSubscription subscription = null;
/*  812 */       if (!(sub instanceof MQQueueSubscription)) {
/*  813 */         JMSException je = ConfigEnvironment.newException("MQJMS3005");
/*      */         
/*  815 */         if (Trace.isOn) {
/*  816 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */         
/*  820 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*  824 */       subscription = (MQQueueSubscription)sub;
/*      */       
/*  826 */       if (subscription.isDurable())
/*      */       {
/*      */         
/*  829 */         if (subscription.getSubscriberQueue() != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  836 */           MQQueue subscriberQueue = null;
/*      */ 
/*      */           
/*      */           try {
/*  840 */             subscriberQueue = subscription.getSubscriberQueue();
/*  841 */             subscriberQueue.close();
/*  842 */             subscriberQueue = null;
/*      */ 
/*      */             
/*  845 */             int fiqBehaviour = 1;
/*  846 */             if (subscription.getMQTopic() != null) {
/*      */               
/*  848 */               fiqBehaviour = subscription.getMQTopic().getIntProperty("failIfQuiesce");
/*      */             }
/*  850 */             else if (subscription.getMQSession() != null) {
/*      */               
/*  852 */               fiqBehaviour = subscription.getMQSession().getFailIfQuiesce();
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  860 */             adminQueue = getAdminQueueAccess(subscription.getMQSession(), fiqBehaviour);
/*  861 */             subscription.setSubscriberState('i');
/*      */             
/*  863 */             if (Trace.isOn) {
/*  864 */               Trace.traceData(this, "Add new subscription entry", null);
/*      */             }
/*  866 */             addSubscriptionEntry(adminQueue, subscription, false);
/*      */ 
/*      */ 
/*      */             
/*  870 */             if (Trace.isOn) {
/*  871 */               Trace.traceData(this, "Remove old subscription entry", null);
/*      */             }
/*  873 */             removeSubscriptionEntry(adminQueue, subscription);
/*      */           
/*      */           }
/*  876 */           catch (MQException e) {
/*  877 */             if (Trace.isOn) {
/*  878 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)e, 1);
/*      */             }
/*      */ 
/*      */             
/*  882 */             JMSException je = ConfigEnvironment.newException("MQJMS3017", subscription.getQueueName());
/*  883 */             je.setLinkedException((Exception)e);
/*  884 */             if (Trace.isOn) {
/*  885 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)je, 2);
/*      */             }
/*      */ 
/*      */             
/*  889 */             throw je;
/*      */           }
/*      */           finally {
/*      */             
/*  893 */             if (Trace.isOn) {
/*  894 */               Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "closeDurableSubscription(MQSubscription)");
/*      */             }
/*      */ 
/*      */             
/*  898 */             if (adminQueue != null) {
/*      */               try {
/*  900 */                 adminQueue.close();
/*      */               }
/*  902 */               catch (MQException mqe) {
/*  903 */                 if (Trace.isOn) {
/*  904 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)mqe, 2);
/*      */                 }
/*      */ 
/*      */                 
/*  908 */                 JMSException je2 = new JMSException("MQJMS2000");
/*  909 */                 je2.setLinkedException((Exception)mqe);
/*  910 */                 je2.initCause((Throwable)mqe);
/*  911 */                 if (Trace.isOn) {
/*  912 */                   Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)je2, 3);
/*      */                 }
/*      */ 
/*      */                 
/*  916 */                 throw je2;
/*      */               }
/*      */             
/*      */             }
/*      */           } 
/*      */         } 
/*      */       }
/*  923 */     } catch (JMSException je) {
/*  924 */       if (Trace.isOn) {
/*  925 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */       
/*  929 */       if (Trace.isOn) {
/*  930 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */       
/*  934 */       throw je;
/*      */     } 
/*  936 */     if (Trace.isOn) {
/*  937 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "closeDurableSubscription(MQSubscription)");
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
/*  954 */     if (Trace.isOn) {
/*  955 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "durableUnsubscribe(MQSession,String)", new Object[] { mqs, subName });
/*      */     }
/*      */     
/*  958 */     MQQueue adminQueue = null;
/*      */ 
/*      */     
/*      */     try {
/*  962 */       int fiqBehaviour = mqs.getFailIfQuiesce();
/*      */       
/*  964 */       adminQueue = getAdminQueueAccess(mqs, fiqBehaviour);
/*      */ 
/*      */       
/*  967 */       unsubscribe(adminQueue, mqs, subName);
/*      */     
/*      */     }
/*  970 */     catch (JMSException je) {
/*  971 */       if (Trace.isOn) {
/*  972 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "durableUnsubscribe(MQSession,String)", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */       
/*  976 */       if (Trace.isOn) {
/*  977 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "durableUnsubscribe(MQSession,String)", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */       
/*  981 */       throw je;
/*      */     } finally {
/*      */       
/*  984 */       if (Trace.isOn) {
/*  985 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "durableUnsubscribe(MQSession,String)");
/*      */       }
/*      */ 
/*      */       
/*  989 */       if (adminQueue != null) {
/*      */         try {
/*  991 */           adminQueue.close();
/*      */         }
/*  993 */         catch (MQException mqe) {
/*  994 */           if (Trace.isOn) {
/*  995 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "durableUnsubscribe(MQSession,String)", (Throwable)mqe, 2);
/*      */           }
/*      */ 
/*      */           
/*  999 */           JMSException je2 = new JMSException("MQJMS2000");
/* 1000 */           je2.setLinkedException((Exception)mqe);
/* 1001 */           je2.initCause((Throwable)mqe);
/* 1002 */           if (Trace.isOn) {
/* 1003 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "durableUnsubscribe(MQSession,String)", (Throwable)je2, 2);
/*      */           }
/*      */ 
/*      */           
/* 1007 */           throw je2;
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1013 */     if (Trace.isOn) {
/* 1014 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "durableUnsubscribe(MQSession,String)");
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
/* 1025 */     if (Trace.isOn) {
/* 1026 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "close()");
/*      */     }
/*      */     
/*      */     try {
/* 1030 */       synchronized (this.statusMgrCloseLock) {
/*      */         
/* 1032 */         if (this.pubsubStatusMgr != null)
/*      */         {
/* 1034 */           this.pubsubStatusMgr.removeConnection(this.connection.qmgrName, this.connection.mqProperties, this.connection.getConnectionID());
/* 1035 */           this.pubsubStatusMgr.close();
/* 1036 */           this.pubsubStatusMgr = null;
/*      */         }
/*      */       
/*      */       } 
/* 1040 */     } catch (JMSException je) {
/* 1041 */       if (Trace.isOn) {
/* 1042 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "close()", (Throwable)je);
/*      */       }
/*      */       
/* 1045 */       if (Trace.isOn) {
/* 1046 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "close()", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1051 */     if (Trace.isOn) {
/* 1052 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "close()", 2);
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
/*      */   private static byte[] addNDSubscriptionEntry(MQQueueSubscription subscription) throws JMSException {
/* 1071 */     if (Trace.isOn) {
/* 1072 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "addNDSubscriptionEntry(MQQueueSubscription)", new Object[] { subscription });
/*      */     }
/*      */ 
/*      */     
/* 1076 */     MQQueue statusQueue = null;
/* 1077 */     MQQueueManager qMgr = subscription.getMQSession().getQM();
/* 1078 */     byte[] msgID = null;
/*      */ 
/*      */     
/* 1081 */     MQMessage subscriptionMessage = subscription.toMessage();
/*      */ 
/*      */     
/*      */     try {
/* 1085 */       statusQueue = qMgr.accessQueue("SYSTEM.JMS.PS.STATUS.QUEUE", 8208);
/*      */       
/* 1087 */       statusQueue.put(subscriptionMessage);
/* 1088 */       msgID = subscriptionMessage.messageId;
/*      */ 
/*      */       
/* 1091 */       statusQueue.close();
/*      */     
/*      */     }
/* 1094 */     catch (MQException e) {
/* 1095 */       if (Trace.isOn) {
/* 1096 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "addNDSubscriptionEntry(MQQueueSubscription)", (Throwable)e);
/*      */       }
/*      */ 
/*      */       
/* 1100 */       JMSException je = ConfigEnvironment.newException("MQJMS3013");
/* 1101 */       je.setLinkedException((Exception)e);
/*      */       
/* 1103 */       if (Trace.isOn) {
/* 1104 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "addNDSubscriptionEntry(MQQueueSubscription)", (Throwable)je);
/*      */       }
/*      */       
/* 1107 */       throw je;
/*      */     } 
/*      */     
/* 1110 */     if (Trace.isOn) {
/* 1111 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "addNDSubscriptionEntry(MQQueueSubscription)", msgID);
/*      */     }
/*      */     
/* 1114 */     return msgID;
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
/*      */   private static void removeNDSubscriptionEntry(MQQueueSubscription subscription) throws JMSException {
/* 1126 */     if (Trace.isOn) {
/* 1127 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "removeNDSubscriptionEntry(MQQueueSubscription)", new Object[] { subscription });
/*      */     }
/*      */     
/* 1130 */     MQQueue statusQueue = null;
/* 1131 */     MQMessage ndEntry = new MQMessage();
/*      */     
/* 1133 */     ndEntry.messageId = subscription.getCorrelationId();
/*      */ 
/*      */ 
/*      */     
/* 1137 */     byte[] subStatusMgrId = subscription.getStatusMgrId();
/* 1138 */     if (subStatusMgrId != null) {
/* 1139 */       ndEntry.correlationId = subStatusMgrId;
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1144 */       MQQueueManager qm = subscription.getMQSession().getQM();
/* 1145 */       statusQueue = qm.accessQueue("SYSTEM.JMS.PS.STATUS.QUEUE", 8194);
/*      */ 
/*      */       
/* 1148 */       statusQueue.get(ndEntry);
/*      */ 
/*      */       
/* 1151 */       statusQueue.close();
/*      */     
/*      */     }
/* 1154 */     catch (MQException e) {
/* 1155 */       if (Trace.isOn) {
/* 1156 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "removeNDSubscriptionEntry(MQQueueSubscription)", (Throwable)e);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1161 */       JMSException je = ConfigEnvironment.newException("MQJMS3013");
/* 1162 */       je.setLinkedException((Exception)e);
/*      */       
/* 1164 */       if (Trace.isOn) {
/* 1165 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "removeNDSubscriptionEntry(MQQueueSubscription)", (Throwable)je);
/*      */       }
/*      */       
/* 1168 */       throw je;
/*      */     } 
/* 1170 */     if (Trace.isOn) {
/* 1171 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "removeNDSubscriptionEntry(MQQueueSubscription)");
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
/*      */   private MQQueueSubscription getResolvedSubscription(MQQueue adminQueue, MQQueueSubscription subscription, byte[] JVMRefID, boolean getNext) throws JMSException {
/* 1197 */     if (Trace.isOn) {
/* 1198 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getResolvedSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription,byte [ ],boolean)", new Object[] { adminQueue, subscription, JVMRefID, 
/*      */             
/* 1200 */             Boolean.valueOf(getNext) });
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
/*      */     try {
/* 1215 */       String fullName = subscription.getFullName();
/*      */ 
/*      */       
/* 1218 */       MQQueueSubscription subscription1 = null;
/* 1219 */       MQQueueSubscription subscription2 = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1227 */       subscription1 = getSubscriptionEntry(adminQueue, subscription.getMQSession(), fullName, (byte[])null, (byte[])null, getNext);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1233 */       if (subscription1 != null) {
/*      */ 
/*      */         
/* 1236 */         subscription1.setDurable(true);
/*      */         
/* 1238 */         if (Trace.isOn) {
/* 1239 */           Trace.traceData(this, "Matching subscriber found. Checking for a second record.", null);
/*      */         }
/* 1241 */         subscription2 = getSubscriptionEntry(adminQueue, subscription.getMQSession(), (String)null, subscription1.getCorrelationId(), (byte[])null, true);
/*      */       } 
/*      */       
/* 1244 */       if (subscription2 != null) {
/*      */ 
/*      */         
/* 1247 */         subscription2.setDurable(true);
/*      */ 
/*      */ 
/*      */         
/* 1251 */         if (Trace.isOn) {
/* 1252 */           Trace.traceData(this, "Second record found: earlier problem must have occurred.", null);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1258 */         if (getSubscriptionEntry(adminQueue, subscription.getMQSession(), (String)null, subscription1.getCorrelationId(), (byte[])null, false) == null) {
/*      */           
/* 1260 */           if (Trace.isOn) {
/* 1261 */             Trace.traceData(this, "getResolved ERROR: couldn't reset browse cursor position", null);
/*      */           }
/*      */           
/* 1264 */           JMSException je = ConfigEnvironment.newException("MQJMS3013");
/* 1265 */           if (Trace.isOn) {
/* 1266 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getResolvedSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription,byte [ ],boolean)", (Throwable)je, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1271 */           throw je;
/*      */         } 
/*      */         
/* 1274 */         if (subscription1.getSubscriberState() == 't' || subscription2.getSubscriberState() == 't')
/*      */         {
/* 1276 */           if (Trace.isOn) {
/* 1277 */             Trace.traceData(this, "Trying to resolve problem detected with earlier create or unsubscribe.", null);
/*      */           }
/*      */           
/* 1280 */           deleteSubscriber(subscription1);
/*      */ 
/*      */           
/* 1283 */           removeSubscriptionEntry(adminQueue, subscription1);
/* 1284 */           removeSubscriptionEntry(adminQueue, subscription2);
/*      */           
/* 1286 */           if (JVMRefID != null) {
/*      */ 
/*      */             
/* 1289 */             subscription1.setSubscriberState('i');
/*      */           } else {
/* 1291 */             subscription1 = null;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*      */ 
/*      */           
/* 1307 */           if (Trace.isOn) {
/* 1308 */             Trace.traceData(this, "Trying to resolve problem detected with earlier re-create or close.", null);
/*      */           }
/*      */ 
/*      */           
/* 1312 */           MQQueueSubscription newSubscription = subscription1;
/*      */           
/* 1314 */           subscription1.setSubscriberState('i');
/*      */           
/* 1316 */           subscription1.setStatusMgrId(MQC.MQCI_NONE);
/*      */ 
/*      */           
/* 1319 */           if (Trace.isOn) {
/* 1320 */             Trace.traceData(this, "Remove the first old subscriptionEntry", null);
/*      */           }
/* 1322 */           removeSubscriptionEntry(adminQueue, subscription1);
/*      */ 
/*      */           
/* 1325 */           if (Trace.isOn) {
/* 1326 */             Trace.traceData(this, "Add the new subscriptionEntry", null);
/*      */           }
/* 1328 */           addSubscriptionEntry(adminQueue, newSubscription, false);
/*      */ 
/*      */ 
/*      */           
/* 1332 */           if (Trace.isOn) {
/* 1333 */             Trace.traceData(this, "Remove the second old subscriptionEntry", null);
/*      */           }
/* 1335 */           removeSubscriptionEntry(adminQueue, subscription2);
/*      */ 
/*      */           
/* 1338 */           subscription1 = newSubscription;
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 1343 */       else if (subscription1 != null && subscription1.getSubscriberState() == 't') {
/*      */         
/* 1345 */         if (Trace.isOn) {
/* 1346 */           Trace.traceData(this, "Trying to resolve problem detected with earlier create or unsubscribe.", null);
/*      */         }
/*      */ 
/*      */         
/*      */         try {
/* 1351 */           deleteSubscriber(subscription1);
/*      */         }
/* 1353 */         catch (JMSException je) {
/* 1354 */           if (Trace.isOn) {
/* 1355 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getResolvedSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription,byte [ ],boolean)", (Throwable)je, 1);
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1365 */         removeSubscriptionEntry(adminQueue, subscription1);
/*      */         
/* 1367 */         if (JVMRefID != null) {
/*      */ 
/*      */           
/* 1370 */           subscription1.setSubscriberState('i');
/*      */         } else {
/* 1372 */           subscription1 = null;
/*      */         } 
/*      */       } 
/*      */       
/* 1376 */       if (Trace.isOn) {
/* 1377 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getResolvedSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription,byte [ ],boolean)", subscription1);
/*      */       }
/*      */ 
/*      */       
/* 1381 */       return subscription1;
/*      */     }
/* 1383 */     catch (JMSException je) {
/* 1384 */       if (Trace.isOn) {
/* 1385 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getResolvedSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription,byte [ ],boolean)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1391 */       if (Trace.isOn) {
/* 1392 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getResolvedSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription,byte [ ],boolean)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1397 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQQueueSubscription createNewDurableSubscription(MQQueue adminQueue, MQQueueSubscription subscription) throws JMSException {
/* 1408 */     if (Trace.isOn) {
/* 1409 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createNewDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", new Object[] { adminQueue, subscription });
/*      */     }
/*      */ 
/*      */     
/* 1413 */     String subscriberQueueName = null;
/* 1414 */     MQQueue subscriberQueue = null;
/* 1415 */     int openOptionsInput = 33;
/* 1416 */     byte[] subscriberId = null;
/* 1417 */     boolean registered = false;
/* 1418 */     MQQueueManager qm = null;
/*      */ 
/*      */     
/* 1421 */     if (subscription.getMQTopic() != null && subscription.getMQTopic().getIntProperty("failIfQuiesce") == 1) {
/*      */       
/* 1423 */       openOptionsInput |= 0x2000;
/*      */     }
/* 1425 */     else if (this.connection.getFailIfQuiesce() == 1) {
/*      */       
/* 1427 */       openOptionsInput |= 0x2000;
/*      */     } 
/*      */     
/* 1430 */     MQSession mqs = subscription.getMQSession();
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*      */       try {
/* 1436 */         qm = mqs.getQM();
/* 1437 */         subscriberQueueName = subscription.getQueueName();
/*      */ 
/*      */         
/* 1440 */         if (!subscription.isSharedQueue()) {
/* 1441 */           subscriberQueue = qm.accessQueue("SYSTEM.JMS.MODEL.QUEUE", openOptionsInput, null, subscriberQueueName, null);
/*      */           
/* 1443 */           subscription.setQueueName(subscriberQueue.name);
/* 1444 */           subscriberQueueName = subscriberQueue.name;
/*      */           
/* 1446 */           if (Trace.isOn) {
/* 1447 */             Trace.traceData(this, "Using multiple-queue approach.", null);
/* 1448 */             Trace.traceData(this, "Model:  SYSTEM.JMS.MODEL.QUEUE", null);
/* 1449 */             Trace.traceData(this, "Prefix: SYSTEM.JMS.D.*", null);
/* 1450 */             Trace.traceData(this, "QName:  " + subscriberQueueName, null);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1455 */           subscriberQueue = qm.accessQueue(subscriberQueueName, openOptionsInput);
/* 1456 */           subscriberQueueName = subscriberQueue.name;
/*      */           
/* 1458 */           if (Trace.isOn) {
/* 1459 */             Trace.traceData(this, "Using shared-queue approach.", null);
/* 1460 */             Trace.traceData(this, "Prefix: SYSTEM.JMS.D.", null);
/* 1461 */             Trace.traceData(this, "QName:  " + subscriberQueueName, null);
/*      */           }
/*      */         
/*      */         } 
/* 1465 */       } catch (MQException e) {
/* 1466 */         if (Trace.isOn) {
/* 1467 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createNewDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)e, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1472 */         JMSException je = ConfigEnvironment.newException("MQJMS2008", subscriberQueueName);
/* 1473 */         je.setLinkedException((Exception)e);
/*      */         
/* 1475 */         if (Trace.isOn) {
/* 1476 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createNewDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1481 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1488 */         subscription.setSubscriberQueue(subscriberQueue);
/* 1489 */         subscription.setSubscriberState('t');
/* 1490 */         subscriberId = addSubscriptionEntry(adminQueue, subscription, true);
/*      */         
/* 1492 */         subscription.setCorrelationId(subscriberId);
/*      */       }
/* 1494 */       catch (JMSException je) {
/* 1495 */         if (Trace.isOn) {
/* 1496 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createNewDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 1505 */           subscriberQueue.close();
/*      */         }
/* 1507 */         catch (MQException mqe) {
/* 1508 */           if (Trace.isOn) {
/* 1509 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createNewDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)mqe, 3);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1514 */           if (Trace.isOn) {
/* 1515 */             Trace.traceData(this, "Error closing subscriber queue.", null);
/*      */           }
/*      */         } 
/*      */         
/* 1519 */         if (Trace.isOn) {
/* 1520 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createNewDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1525 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1533 */         sendBrokerCommand(subscription, 1, true);
/* 1534 */         registered = true;
/*      */       
/*      */       }
/* 1537 */       catch (NoBrokerResponseException nbre) {
/* 1538 */         if (Trace.isOn) {
/* 1539 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createNewDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)nbre, 4);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1546 */         if (Trace.isOn) {
/* 1547 */           Trace.traceData(this, "subscribe command failed, sending an unsubscribe to cancel it", null);
/*      */         }
/*      */         try {
/* 1550 */           sendBrokerCommand(subscription, 2, true);
/*      */         }
/* 1552 */         catch (JMSException je) {
/* 1553 */           if (Trace.isOn) {
/* 1554 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createNewDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)je, 5);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1561 */           if (Trace.isOn) {
/* 1562 */             Trace.traceData(this, "the cancel command also failed.", null);
/*      */           }
/*      */         } 
/* 1565 */         if (Trace.isOn) {
/* 1566 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createNewDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)nbre, 3);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1571 */         throw nbre;
/*      */       
/*      */       }
/* 1574 */       catch (BrokerCommandFailedException bcfe) {
/* 1575 */         if (Trace.isOn) {
/* 1576 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createNewDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)bcfe, 6);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1582 */         if (Trace.isOn) {
/* 1583 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createNewDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)bcfe, 4);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1588 */         throw bcfe;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1594 */       subscription.setSubscriberState('u');
/* 1595 */       addSubscriptionEntry(adminQueue, subscription, false);
/*      */ 
/*      */ 
/*      */       
/* 1599 */       removeSubscriptionEntry(adminQueue, subscription);
/*      */     
/*      */     }
/* 1602 */     catch (JMSException je) {
/* 1603 */       if (Trace.isOn) {
/* 1604 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createNewDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)je, 7);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1609 */       boolean cleanupProblems = false;
/*      */ 
/*      */ 
/*      */       
/* 1613 */       if (registered) {
/* 1614 */         if (Trace.isOn) {
/* 1615 */           Trace.traceData(this, "cleanup sending a cancel command", null);
/*      */         }
/*      */         try {
/* 1618 */           sendBrokerCommand(subscription, 2, true);
/*      */         
/*      */         }
/* 1621 */         catch (JMSException je2) {
/* 1622 */           if (Trace.isOn) {
/* 1623 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createNewDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)je2, 8);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1628 */           cleanupProblems = true;
/* 1629 */           if (Trace.isOn) {
/* 1630 */             Trace.traceData(this, "the cancel command also failed.", null);
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1636 */       if (subscriberQueue != null) {
/* 1637 */         if (Trace.isOn) {
/* 1638 */           Trace.traceData(this, "closing subscription queue", null);
/*      */         }
/* 1640 */         if (!subscription.isSharedQueue()) {
/* 1641 */           if (Trace.isOn) {
/* 1642 */             Trace.traceData(this, "not a shared queue so will be deleted", null);
/*      */           }
/* 1644 */           subscriberQueue.closeOptions = 2;
/*      */         } 
/*      */         try {
/* 1647 */           subscriberQueue.close();
/*      */         }
/* 1649 */         catch (MQException e) {
/* 1650 */           if (Trace.isOn) {
/* 1651 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createNewDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)e, 9);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1657 */           if (Trace.isOn) {
/* 1658 */             Trace.traceData(this, "Failed to delete subscription queue.", null);
/*      */           }
/* 1660 */           cleanupProblems = true;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1666 */       if (!cleanupProblems) {
/* 1667 */         removeSubscriptionEntry(adminQueue, subscription);
/*      */       }
/*      */       
/* 1670 */       if (Trace.isOn) {
/* 1671 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createNewDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)je, 5);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1676 */       throw je;
/*      */     } 
/*      */     
/* 1679 */     if (Trace.isOn) {
/* 1680 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createNewDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", subscription);
/*      */     }
/*      */ 
/*      */     
/* 1684 */     return subscription;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQQueueSubscription createExistingDurableSubscription(MQQueue adminQueue, MQQueueSubscription subscription) throws JMSException {
/* 1693 */     if (Trace.isOn) {
/* 1694 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createExistingDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", new Object[] { adminQueue, subscription });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1699 */     MQQueue subscriberQueue = null;
/* 1700 */     MQQueueSubscription newSubscription = null;
/*      */ 
/*      */     
/*      */     try {
/* 1704 */       subscriberQueue = checkInactive(subscription);
/* 1705 */       subscription.setSubscriberQueue(subscriberQueue);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1710 */       newSubscription = subscription;
/* 1711 */       newSubscription.setSubscriberState('u');
/* 1712 */       newSubscription.setStatusMgrId(this.statusMgrId);
/*      */       
/* 1714 */       addSubscriptionEntry(adminQueue, newSubscription, false);
/*      */       
/* 1716 */       removeSubscriptionEntry(adminQueue, subscription);
/*      */     
/*      */     }
/* 1719 */     catch (JMSException je) {
/* 1720 */       if (Trace.isOn) {
/* 1721 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createExistingDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)je);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1726 */       if (Trace.isOn) {
/* 1727 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createExistingDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)je);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1732 */       throw je;
/*      */     } 
/* 1734 */     if (Trace.isOn) {
/* 1735 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createExistingDurableSubscription(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", newSubscription);
/*      */     }
/*      */ 
/*      */     
/* 1739 */     return newSubscription;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQQueueSubscription createUpdatedDurableSubscriber(MQQueue adminQueue, MQQueueSubscription subscription) throws JMSException {
/* 1748 */     if (Trace.isOn) {
/* 1749 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createUpdatedDurableSubscriber(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", new Object[] { adminQueue, subscription });
/*      */     }
/*      */ 
/*      */     
/* 1753 */     MQQueueSubscription retSubscription = null;
/*      */ 
/*      */     
/*      */     try {
/* 1757 */       MQQueueSubscription newSubscription = null;
/*      */ 
/*      */ 
/*      */       
/* 1761 */       newSubscription = subscription;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1766 */       unsubscribe(adminQueue, subscription.getMQSession(), subscription.getSubName());
/*      */ 
/*      */       
/* 1769 */       newSubscription.setStatusMgrId(this.statusMgrId);
/* 1770 */       retSubscription = createNewDurableSubscription(adminQueue, newSubscription);
/*      */     
/*      */     }
/* 1773 */     catch (JMSException je) {
/* 1774 */       if (Trace.isOn) {
/* 1775 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createUpdatedDurableSubscriber(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)je);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1781 */       if (Trace.isOn) {
/* 1782 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createUpdatedDurableSubscriber(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)je);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1787 */       throw je;
/*      */     } 
/* 1789 */     if (Trace.isOn) {
/* 1790 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "createUpdatedDurableSubscriber(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", retSubscription);
/*      */     }
/*      */ 
/*      */     
/* 1794 */     return retSubscription;
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
/*      */   private MQQueue getAdminQueueAccess(MQSession mqs, int fiqBehaviour) throws JMSException {
/* 1811 */     if (Trace.isOn) {
/* 1812 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getAdminQueueAccess(MQSession,int)", new Object[] { mqs, 
/* 1813 */             Integer.valueOf(fiqBehaviour) });
/*      */     }
/* 1815 */     int MAXRETRIES = 20;
/* 1816 */     int MAXWAIT = 5000;
/*      */     
/* 1818 */     MQQueueManager qm = mqs.getQM();
/* 1819 */     MQQueue adminQueue = null;
/*      */     
/* 1821 */     int attempt = 0;
/*      */     
/*      */     while (true) {
/*      */       try {
/* 1825 */         if (Trace.isOn) {
/* 1826 */           Trace.traceData(this, "Try to open the adminQueue", null);
/*      */         }
/*      */         
/* 1829 */         int openOptions = 60;
/*      */         
/* 1831 */         if (fiqBehaviour == 1) {
/* 1832 */           openOptions |= 0x2000;
/*      */         }
/*      */         
/* 1835 */         adminQueue = qm.accessQueue("SYSTEM.JMS.ADMIN.QUEUE", openOptions);
/*      */ 
/*      */       
/*      */       }
/* 1839 */       catch (MQException e) {
/* 1840 */         if (Trace.isOn) {
/* 1841 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getAdminQueueAccess(MQSession,int)", (Throwable)e, 1);
/*      */         }
/*      */ 
/*      */         
/* 1845 */         if (Trace.isOn) {
/* 1846 */           Trace.traceData(this, "Cannot open adminQueue", null);
/*      */         }
/* 1848 */         switch (e.reasonCode) {
/*      */           
/*      */           case 2042:
/* 1851 */             attempt++;
/* 1852 */             if (attempt < 20) {
/*      */               
/* 1854 */               if (Trace.isOn) {
/* 1855 */                 Trace.traceData(this, "admin queue locked, tried " + attempt + "times", null);
/* 1856 */                 Trace.traceData(this, "suppressing exception and retrying after wait", null);
/*      */               } 
/*      */               
/*      */               try {
/* 1860 */                 long daisyTime = (long)(100.0D + 5000.0D * Math.random());
/* 1861 */                 if (Trace.isOn) {
/* 1862 */                   Trace.traceData(this, "waiting for " + daisyTime + " milis...", null);
/*      */                 }
/* 1864 */                 Thread.sleep(daisyTime);
/*      */               }
/* 1866 */               catch (InterruptedException ie) {
/* 1867 */                 if (Trace.isOn) {
/* 1868 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getAdminQueueAccess(MQSession,int)", ie, 2);
/*      */                 }
/*      */               } 
/*      */               
/*      */               continue;
/*      */             } 
/*      */             
/* 1875 */             if (Trace.isOn) {
/* 1876 */               Trace.traceData(this, "Tried 20 attempts. I'm bored now so I'll throw an exception", null);
/*      */             }
/*      */             
/* 1879 */             je = ConfigEnvironment.newException("MQJMS2008", "SYSTEM.JMS.ADMIN.QUEUE");
/* 1880 */             je.setLinkedException((Exception)e);
/* 1881 */             if (Trace.isOn) {
/* 1882 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getAdminQueueAccess(MQSession,int)", (Throwable)je, 1);
/*      */             }
/*      */ 
/*      */             
/* 1886 */             throw je;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1891 */         JMSException je = ConfigEnvironment.newException("MQJMS2008", "SYSTEM.JMS.ADMIN.QUEUE");
/* 1892 */         je.setLinkedException((Exception)e);
/* 1893 */         if (Trace.isOn) {
/* 1894 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getAdminQueueAccess(MQSession,int)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */         
/* 1898 */         throw je;
/*      */       } 
/*      */       
/*      */       break;
/*      */     } 
/*      */     
/* 1904 */     if (Trace.isOn) {
/* 1905 */       Trace.traceData(this, "Obtaining the admin queue lock required " + attempt + " retries", null);
/*      */     }
/*      */     
/* 1908 */     if (Trace.isOn) {
/* 1909 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getAdminQueueAccess(MQSession,int)", adminQueue);
/*      */     }
/*      */     
/* 1912 */     return adminQueue;
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
/*      */   private byte[] addSubscriptionEntry(MQQueue adminQueue, MQQueueSubscription subscription, boolean checkExists) throws JMSException {
/* 1925 */     if (Trace.isOn) {
/* 1926 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "addSubscriptionEntry(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription,boolean)", new Object[] { adminQueue, subscription, 
/*      */             
/* 1928 */             Boolean.valueOf(checkExists) });
/*      */     }
/*      */     
/* 1931 */     byte[] subscriberId = null;
/*      */ 
/*      */     
/* 1934 */     if (checkExists && contains(adminQueue, subscription.getMQSession(), subscription.getSubName())) {
/* 1935 */       if (Trace.isOn) {
/* 1936 */         Trace.traceData(this, "In addSubscriptionEntry() - entry already exists!", null);
/*      */       }
/*      */       
/* 1939 */       JMSException je = ConfigEnvironment.newException("MQJMS3013");
/* 1940 */       if (Trace.isOn) {
/* 1941 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "addSubscriptionEntry(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription,boolean)", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1946 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1952 */     MQMessage subEntryMsg = subscription.toMessage();
/* 1953 */     if (Trace.isOn) {
/* 1954 */       Trace.traceData(this, "MQPUT with messageId: ", subEntryMsg.messageId);
/*      */     }
/*      */     
/*      */     try {
/* 1958 */       adminQueue.put(subEntryMsg, new MQPutMessageOptions());
/* 1959 */       subscriberId = subEntryMsg.messageId;
/*      */     
/*      */     }
/* 1962 */     catch (MQException e) {
/* 1963 */       if (Trace.isOn) {
/* 1964 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "addSubscriptionEntry(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription,boolean)", (Throwable)e);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1970 */       if (Trace.isOn) {
/* 1971 */         Trace.traceData(this, "In addSubscriptionEntry() - unable to MQPUT new entry", null);
/*      */       }
/*      */       
/* 1974 */       JMSException je = ConfigEnvironment.newException("MQJMS3013");
/* 1975 */       je.setLinkedException((Exception)e);
/* 1976 */       if (Trace.isOn) {
/* 1977 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "addSubscriptionEntry(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription,boolean)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1982 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/* 1986 */     if (Trace.isOn) {
/* 1987 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "addSubscriptionEntry(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription,boolean)", subscriberId);
/*      */     }
/*      */ 
/*      */     
/* 1991 */     return subscriberId;
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
/*      */   private void removeSubscriptionEntry(MQQueue adminQueue, MQQueueSubscription subscription) throws JMSException {
/* 2004 */     if (Trace.isOn) {
/* 2005 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "removeSubscriptionEntry(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", new Object[] { adminQueue, subscription });
/*      */     }
/*      */ 
/*      */     
/* 2009 */     MQQueueSubscription currentEntry = null;
/*      */     
/* 2011 */     byte[] subscriberId = subscription.getCorrelationId();
/* 2012 */     String key = subscription.getSubName();
/* 2013 */     MQGetMessageOptions gmo = new MQGetMessageOptions();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2018 */     if (subscriberId == null) {
/* 2019 */       gmo.options = 16;
/*      */     }
/*      */     
/* 2022 */     boolean carryOn = true;
/*      */ 
/*      */     
/* 2025 */     if (this.connection.getFailIfQuiesce() == 1) {
/* 2026 */       gmo.options |= 0x2000;
/*      */     }
/*      */     
/* 2029 */     while (carryOn) {
/*      */       try {
/* 2031 */         MQMessage msg = new MQMessage();
/*      */ 
/*      */         
/* 2034 */         if (subscriberId != null) {
/* 2035 */           msg.messageId = subscriberId;
/* 2036 */           gmo.matchOptions |= 0x1;
/*      */         } 
/*      */         
/* 2039 */         adminQueue.get(msg, gmo);
/*      */         
/* 2041 */         currentEntry = new MQQueueSubscription(this, subscription.getMQSession(), msg);
/*      */ 
/*      */         
/* 2044 */         if (subscriberId != null) {
/*      */           
/* 2046 */           carryOn = false;
/*      */           continue;
/*      */         } 
/* 2049 */         if (currentEntry.isValid() && currentEntry.getSubName().equals(key)) {
/*      */ 
/*      */           
/* 2052 */           gmo.options = 256;
/* 2053 */           MQMessage delmsg = new MQMessage();
/* 2054 */           adminQueue.get(delmsg, gmo);
/* 2055 */           carryOn = false;
/*      */           
/*      */           continue;
/*      */         } 
/* 2059 */         gmo.options = 32;
/*      */ 
/*      */         
/* 2062 */         if (this.connection.getFailIfQuiesce() == 1) {
/* 2063 */           gmo.options |= 0x2000;
/*      */ 
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 2069 */       catch (MQException e) {
/* 2070 */         if (Trace.isOn) {
/* 2071 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "removeSubscriptionEntry(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)e);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2081 */         JMSException je = ConfigEnvironment.newException("MQJMS3013");
/* 2082 */         je.setLinkedException((Exception)e);
/*      */         
/* 2084 */         if (Trace.isOn) {
/* 2085 */           Trace.traceData(this, "In removeSubscriptionEntry() - cannot remove msg!", null);
/*      */         }
/* 2087 */         if (Trace.isOn) {
/* 2088 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "removeSubscriptionEntry(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)", (Throwable)je);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2093 */         throw je;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2098 */     if (Trace.isOn) {
/* 2099 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "removeSubscriptionEntry(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQQueueSubscription)");
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
/*      */   private MQQueue checkInactive(MQQueueSubscription subscription) throws JMSException {
/* 2117 */     if (Trace.isOn) {
/* 2118 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "checkInactive(MQQueueSubscription)", new Object[] { subscription });
/*      */     }
/*      */ 
/*      */     
/* 2122 */     MQQueueManager qm = null;
/* 2123 */     MQQueue subscriberQueue = null;
/*      */ 
/*      */     
/* 2126 */     boolean subInactive = false;
/* 2127 */     qm = subscription.getMQSession().getQM();
/*      */ 
/*      */     
/* 2130 */     if (subscription.getSubscriberState() == 'i') {
/* 2131 */       subInactive = true;
/*      */     }
/* 2133 */     else if (subscription.getSubscriberState() != 'u') {
/*      */ 
/*      */       
/* 2136 */       if (Trace.isOn) {
/* 2137 */         Trace.traceData(this, "checkInactive: state is neither unknown or inactive!", null);
/*      */       }
/* 2139 */       JMSException je = ConfigEnvironment.newException("MQJMS3005");
/* 2140 */       if (Trace.isOn) {
/* 2141 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "checkInactive(MQQueueSubscription)", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */       
/* 2145 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/* 2149 */     int fiqBehaviour = 1;
/* 2150 */     if (subscription.getMQTopic() != null) {
/*      */       
/* 2152 */       fiqBehaviour = subscription.getMQTopic().getIntProperty("failIfQuiesce");
/*      */     }
/* 2154 */     else if (subscription.getMQSession() != null) {
/*      */       
/* 2156 */       fiqBehaviour = subscription.getMQSession().getFailIfQuiesce();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2164 */       if (subscription.isSharedQueue() && !subInactive) {
/*      */ 
/*      */         
/* 2167 */         int openOptions = 10;
/*      */ 
/*      */         
/* 2170 */         if (fiqBehaviour == 1) {
/* 2171 */           openOptions |= 0x2000;
/*      */         }
/*      */ 
/*      */         
/* 2175 */         MQQueue statusQueue = qm.accessQueue("SYSTEM.JMS.PS.STATUS.QUEUE", openOptions);
/*      */ 
/*      */         
/* 2178 */         MQMessage jvmMessage = new MQMessage();
/* 2179 */         jvmMessage.messageId = subscription.getStatusMgrId();
/*      */ 
/*      */         
/* 2182 */         MQGetMessageOptions gmo = new MQGetMessageOptions();
/* 2183 */         gmo.options = 16;
/*      */         try {
/* 2185 */           if (Trace.isOn) {
/* 2186 */             Trace.traceData(this, "attempting MQGET for jvm reference msg.", null);
/*      */           }
/* 2188 */           statusQueue.get(jvmMessage, gmo);
/*      */         }
/* 2190 */         catch (MQException e) {
/* 2191 */           if (Trace.isOn) {
/* 2192 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "checkInactive(MQQueueSubscription)", (Throwable)e, 1);
/*      */           }
/*      */ 
/*      */           
/* 2196 */           if (e.reasonCode == 2033) {
/* 2197 */             if (Trace.isOn) {
/* 2198 */               Trace.traceData(this, "shared subscriber assumed active.", null);
/*      */             }
/* 2200 */             JMSException je = ConfigEnvironment.newException("MQJMS3023");
/* 2201 */             je.setLinkedException((Exception)e);
/* 2202 */             if (Trace.isOn) {
/* 2203 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "checkInactive(MQQueueSubscription)", (Throwable)je, 2);
/*      */             }
/*      */ 
/*      */             
/* 2207 */             throw je;
/*      */           } 
/*      */ 
/*      */           
/* 2211 */           if (Trace.isOn) {
/* 2212 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "checkInactive(MQQueueSubscription)", (Throwable)e, 3);
/*      */           }
/*      */ 
/*      */           
/* 2216 */           throw e;
/*      */         } 
/*      */ 
/*      */         
/* 2220 */         statusQueue.close();
/*      */       } 
/*      */ 
/*      */       
/* 2224 */       if (Trace.isOn) {
/* 2225 */         Trace.traceData(this, "opening subscription queue", null);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2230 */       subscriberQueue = openSubscriberQueue(subscription.getMQSession(), subscription.getQueueName(), subscription.isSharedQueue(), true, fiqBehaviour);
/*      */     
/*      */     }
/* 2233 */     catch (MQException e) {
/* 2234 */       JMSException je; if (Trace.isOn) {
/* 2235 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "checkInactive(MQQueueSubscription)", (Throwable)e, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2241 */       if (e.reasonCode == 2042) {
/*      */         
/* 2243 */         je = ConfigEnvironment.newException("MQJMS3023");
/*      */       } else {
/* 2245 */         je = ConfigEnvironment.newException("MQJMS3005");
/*      */       } 
/*      */       
/* 2248 */       je.setLinkedException((Exception)e);
/* 2249 */       if (Trace.isOn) {
/* 2250 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "checkInactive(MQQueueSubscription)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */       
/* 2254 */       throw je;
/*      */     } 
/*      */     
/* 2257 */     if (Trace.isOn) {
/* 2258 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "checkInactive(MQQueueSubscription)", subscriberQueue);
/*      */     }
/*      */     
/* 2261 */     return subscriberQueue;
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
/*      */   private MQQueueSubscription getSubscriptionEntry(MQQueue adminQueue, MQSession mqs, String key, byte[] subscriberId, byte[] jvmReferenceId, boolean getNext) throws JMSException {
/* 2281 */     if (Trace.isOn) {
/* 2282 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getSubscriptionEntry(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQSession,String,byte [ ],byte [ ],boolean)", new Object[] { adminQueue, mqs, key, subscriberId, jvmReferenceId, 
/*      */ 
/*      */             
/* 2285 */             Boolean.valueOf(getNext) });
/*      */     }
/*      */     
/* 2288 */     MQQueueSubscription ae = null;
/* 2289 */     MQQueueSubscription ret = null;
/*      */     
/* 2291 */     MQGetMessageOptions gmo = new MQGetMessageOptions();
/*      */ 
/*      */ 
/*      */     
/* 2295 */     if (getNext) {
/* 2296 */       gmo.options = 32;
/*      */     } else {
/* 2298 */       gmo.options = 16;
/*      */     } 
/*      */ 
/*      */     
/* 2302 */     if (this.connection.getFailIfQuiesce() == 1) {
/* 2303 */       gmo.options |= 0x2000;
/*      */     }
/*      */     
/* 2306 */     boolean carryOn = true;
/* 2307 */     while (carryOn) {
/*      */       
/*      */       try {
/* 2310 */         MQMessage msg = new MQMessage();
/*      */ 
/*      */         
/* 2313 */         if (subscriberId != null) {
/* 2314 */           msg.messageId = subscriberId;
/*      */         }
/*      */         
/* 2317 */         if (jvmReferenceId != null) {
/* 2318 */           msg.correlationId = jvmReferenceId;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2324 */         adminQueue.get(msg, gmo);
/*      */         
/* 2326 */         ae = new MQQueueSubscription(this, mqs, msg);
/*      */         
/* 2328 */         if (key == null && ae.isValid()) {
/*      */           
/* 2330 */           ret = ae;
/* 2331 */           carryOn = false; continue;
/* 2332 */         }  if (key != null && ae.isValid() && ae.getFullName().equals(key)) {
/*      */           
/* 2334 */           ret = ae;
/* 2335 */           carryOn = false; continue;
/*      */         } 
/* 2337 */         gmo.options = 32;
/*      */ 
/*      */       
/*      */       }
/* 2341 */       catch (MQException e) {
/* 2342 */         if (Trace.isOn) {
/* 2343 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getSubscriptionEntry(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQSession,String,byte [ ],byte [ ],boolean)", (Throwable)e);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2352 */         carryOn = false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2359 */     if (Trace.isOn) {
/* 2360 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getSubscriptionEntry(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQSession,String,byte [ ],byte [ ],boolean)", ret);
/*      */     }
/*      */ 
/*      */     
/* 2364 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void deleteSubscriber(MQQueueSubscription subscription) throws JMSException {
/*      */     String fullName;
/* 2374 */     if (Trace.isOn) {
/* 2375 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "deleteSubscriber(MQQueueSubscription)", new Object[] { subscription });
/*      */     }
/*      */     
/* 2378 */     JMSException deregException = null;
/*      */ 
/*      */     
/* 2381 */     if (subscription.isDurable()) {
/* 2382 */       fullName = subscription.getClientId() + ":" + subscription.getSubName();
/*      */     } else {
/* 2384 */       fullName = "";
/*      */     } 
/*      */ 
/*      */     
/* 2388 */     MQSubEntry subEntry = new MQSubEntry(fullName, subscription.getTopic(), subscription.getQueueName(), subscription.getSelector(), subscription.getNoLocal(), subscription.getStatusMgrId(), subscription.isSharedQueue());
/* 2389 */     subEntry.setSubscriberId(subscription.getCorrelationId());
/*      */     
/* 2391 */     MQQueue subQ = subscription.getSubscriberQueue();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*      */       try {
/* 2398 */         sendBrokerCommand(subscription, 2, true);
/*      */       
/*      */       }
/* 2401 */       catch (JMSException je) {
/* 2402 */         if (Trace.isOn) {
/* 2403 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "deleteSubscriber(MQQueueSubscription)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2408 */         deregException = je;
/* 2409 */         if (Trace.isOn) {
/* 2410 */           Trace.traceData(this, "Failed to deregister.", null);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 2415 */       SubscriptionHelper.deleteSubscriberMessages(subEntry, subQ);
/*      */ 
/*      */       
/* 2418 */       if (deregException != null) {
/* 2419 */         if (Trace.isOn) {
/* 2420 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "deleteSubscriber(MQQueueSubscription)", (Throwable)deregException, 1);
/*      */         }
/*      */ 
/*      */         
/* 2424 */         throw deregException;
/*      */       }
/*      */     
/*      */     }
/* 2428 */     catch (JMSException je) {
/* 2429 */       if (Trace.isOn) {
/* 2430 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "deleteSubscriber(MQQueueSubscription)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2435 */       if (Trace.isOn) {
/* 2436 */         Trace.traceData(this, "Failure in deleteSubscriber.", null);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2441 */       if (deregException != null && je != deregException) {
/* 2442 */         if (Trace.isOn) {
/* 2443 */           Trace.traceData(this, "NOTE! Throwing pending deregException instead!", null);
/*      */         }
/* 2445 */         if (Trace.isOn) {
/* 2446 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "deleteSubscriber(MQQueueSubscription)", (Throwable)deregException, 2);
/*      */         }
/*      */ 
/*      */         
/* 2450 */         throw deregException;
/*      */       } 
/*      */ 
/*      */       
/* 2454 */       if (Trace.isOn) {
/* 2455 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "deleteSubscriber(MQQueueSubscription)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */       
/* 2459 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/* 2463 */     if (Trace.isOn) {
/* 2464 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "deleteSubscriber(MQQueueSubscription)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void sendBrokerCommand(MQQueueSubscription subscription, int command, boolean wait) throws JMSException {
/*      */     // Byte code:
/*      */     //   0: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   3: ifeq -> 37
/*      */     //   6: aload_0
/*      */     //   7: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine'
/*      */     //   9: ldc_w 'sendBrokerCommand(MQQueueSubscription,int,boolean)'
/*      */     //   12: iconst_3
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
/*      */     //   29: iload_3
/*      */     //   30: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*      */     //   33: aastore
/*      */     //   34: invokestatic entry : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
/*      */     //   37: aconst_null
/*      */     //   38: astore #5
/*      */     //   40: iconst_0
/*      */     //   41: istore #6
/*      */     //   43: iconst_1
/*      */     //   44: istore #7
/*      */     //   46: iconst_0
/*      */     //   47: istore #8
/*      */     //   49: iconst_0
/*      */     //   50: istore #9
/*      */     //   52: aconst_null
/*      */     //   53: astore #10
/*      */     //   55: aconst_null
/*      */     //   56: astore #11
/*      */     //   58: aload_1
/*      */     //   59: invokevirtual getMQSession : ()Lcom/ibm/msg/client/wmq/compat/jms/internal/MQSession;
/*      */     //   62: astore #12
/*      */     //   64: aload #12
/*      */     //   66: invokevirtual getBrk : ()Lcom/ibm/msg/client/wmq/compat/jms/internal/BrokerConnectionInfo;
/*      */     //   69: astore #13
/*      */     //   71: aload_1
/*      */     //   72: invokevirtual getCorrelationId : ()[B
/*      */     //   75: astore #14
/*      */     //   77: aload_1
/*      */     //   78: invokevirtual isDurable : ()Z
/*      */     //   81: istore #15
/*      */     //   83: aload_0
/*      */     //   84: invokevirtual getMQConnection : ()Lcom/ibm/msg/client/wmq/compat/jms/internal/MQConnection;
/*      */     //   87: invokevirtual getBrkOptLevel : ()I
/*      */     //   90: istore #16
/*      */     //   92: iload_2
/*      */     //   93: iconst_1
/*      */     //   94: if_icmpne -> 131
/*      */     //   97: iload #15
/*      */     //   99: ifne -> 131
/*      */     //   102: aload #13
/*      */     //   104: getfield qmName : Ljava/lang/String;
/*      */     //   107: aload_0
/*      */     //   108: invokevirtual getMQConnection : ()Lcom/ibm/msg/client/wmq/compat/jms/internal/MQConnection;
/*      */     //   111: getfield qmgrName : Ljava/lang/String;
/*      */     //   114: invokevirtual equals : (Ljava/lang/Object;)Z
/*      */     //   117: ifeq -> 131
/*      */     //   120: aload #12
/*      */     //   122: getfield persistenceFromMD : Z
/*      */     //   125: ifne -> 131
/*      */     //   128: iconst_1
/*      */     //   129: istore #8
/*      */     //   131: aload #12
/*      */     //   133: getfield acknowledgeMode : I
/*      */     //   136: iconst_3
/*      */     //   137: if_icmpne -> 148
/*      */     //   140: iload_2
/*      */     //   141: iconst_1
/*      */     //   142: if_icmpne -> 148
/*      */     //   145: iconst_1
/*      */     //   146: istore #9
/*      */     //   148: iload #7
/*      */     //   150: iconst_1
/*      */     //   151: if_icmpne -> 1399
/*      */     //   154: aconst_null
/*      */     //   155: astore #17
/*      */     //   157: aload_0
/*      */     //   158: invokevirtual getMQConnection : ()Lcom/ibm/msg/client/wmq/compat/jms/internal/MQConnection;
/*      */     //   161: invokevirtual getBrkVersion : ()I
/*      */     //   164: ifeq -> 178
/*      */     //   167: aload_0
/*      */     //   168: invokevirtual getMQConnection : ()Lcom/ibm/msg/client/wmq/compat/jms/internal/MQConnection;
/*      */     //   171: invokevirtual getBrkVersion : ()I
/*      */     //   174: iconst_m1
/*      */     //   175: if_icmpne -> 204
/*      */     //   178: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   181: ifeq -> 192
/*      */     //   184: aload_0
/*      */     //   185: ldc_w 'Creating RFH1 ProviderMessage'
/*      */     //   188: aconst_null
/*      */     //   189: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   192: new com/ibm/msg/client/wmq/compat/jms/internal/RFH1BrokerMessageImpl
/*      */     //   195: dup
/*      */     //   196: invokespecial <init> : ()V
/*      */     //   199: astore #17
/*      */     //   201: goto -> 227
/*      */     //   204: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   207: ifeq -> 218
/*      */     //   210: aload_0
/*      */     //   211: ldc_w 'Creating RFH2 ProviderMessage'
/*      */     //   214: aconst_null
/*      */     //   215: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   218: new com/ibm/msg/client/wmq/compat/jms/internal/RFH2BrokerMessageImpl
/*      */     //   221: dup
/*      */     //   222: invokespecial <init> : ()V
/*      */     //   225: astore #17
/*      */     //   227: new com/ibm/msg/client/wmq/compat/jms/internal/MQJMSMessage
/*      */     //   230: dup
/*      */     //   231: invokespecial <init> : ()V
/*      */     //   234: astore #18
/*      */     //   236: iconst_0
/*      */     //   237: istore #7
/*      */     //   239: iload_2
/*      */     //   240: tableswitch default -> 342, 1 -> 272, 2 -> 307, 3 -> 342, 4 -> 342
/*      */     //   272: aload #17
/*      */     //   274: ldc_w 'MQPSCommand'
/*      */     //   277: ldc_w 'RegSub'
/*      */     //   280: invokevirtual set : (Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   283: aload_1
/*      */     //   284: invokevirtual isSharedQueue : ()Z
/*      */     //   287: ifeq -> 421
/*      */     //   290: aload #17
/*      */     //   292: ldc_w 'MQPSRegOpts'
/*      */     //   295: ldc_w 'CorrelAsId'
/*      */     //   298: invokevirtual setOption : (Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   301: iconst_1
/*      */     //   302: istore #6
/*      */     //   304: goto -> 421
/*      */     //   307: aload #17
/*      */     //   309: ldc_w 'MQPSCommand'
/*      */     //   312: ldc_w 'DeregSub'
/*      */     //   315: invokevirtual set : (Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   318: aload_1
/*      */     //   319: invokevirtual isSharedQueue : ()Z
/*      */     //   322: ifeq -> 421
/*      */     //   325: aload #17
/*      */     //   327: ldc_w 'MQPSRegOpts'
/*      */     //   330: ldc_w 'CorrelAsId'
/*      */     //   333: invokevirtual setOption : (Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   336: iconst_1
/*      */     //   337: istore #6
/*      */     //   339: goto -> 421
/*      */     //   342: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   345: ifeq -> 373
/*      */     //   348: aload_0
/*      */     //   349: new java/lang/StringBuilder
/*      */     //   352: dup
/*      */     //   353: invokespecial <init> : ()V
/*      */     //   356: ldc_w 'unknown value of command: '
/*      */     //   359: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   362: iload_2
/*      */     //   363: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*      */     //   366: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   369: aconst_null
/*      */     //   370: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   373: ldc 'MQJMS1016'
/*      */     //   375: new java/lang/StringBuilder
/*      */     //   378: dup
/*      */     //   379: invokespecial <init> : ()V
/*      */     //   382: ldc_w 'unknown command value '
/*      */     //   385: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   388: iload_2
/*      */     //   389: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*      */     //   392: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   395: invokestatic newException : (Ljava/lang/String;Ljava/lang/Object;)Ljavax/jms/JMSException;
/*      */     //   398: astore #4
/*      */     //   400: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   403: ifeq -> 418
/*      */     //   406: aload_0
/*      */     //   407: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine'
/*      */     //   409: ldc_w 'sendBrokerCommand(MQQueueSubscription,int,boolean)'
/*      */     //   412: aload #4
/*      */     //   414: iconst_1
/*      */     //   415: invokestatic throwing : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   418: aload #4
/*      */     //   420: athrow
/*      */     //   421: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   424: ifeq -> 435
/*      */     //   427: aload_0
/*      */     //   428: ldc_w 'getting topic'
/*      */     //   431: aconst_null
/*      */     //   432: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   435: aload #17
/*      */     //   437: ldc_w 'MQPSTopic'
/*      */     //   440: aload_1
/*      */     //   441: invokevirtual getTopic : ()Ljava/lang/String;
/*      */     //   444: invokevirtual set : (Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   447: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   450: ifeq -> 479
/*      */     //   453: aload_0
/*      */     //   454: new java/lang/StringBuilder
/*      */     //   457: dup
/*      */     //   458: invokespecial <init> : ()V
/*      */     //   461: ldc_w 'Current brkOptLevel: '
/*      */     //   464: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   467: iload #16
/*      */     //   469: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*      */     //   472: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   475: aconst_null
/*      */     //   476: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   479: iload #8
/*      */     //   481: iconst_1
/*      */     //   482: if_icmpne -> 515
/*      */     //   485: iload #16
/*      */     //   487: ifle -> 515
/*      */     //   490: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   493: ifeq -> 504
/*      */     //   496: aload_0
/*      */     //   497: ldc_w 'Setting non persistent'
/*      */     //   500: aconst_null
/*      */     //   501: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   504: aload #17
/*      */     //   506: ldc_w 'MQPSRegOpts'
/*      */     //   509: ldc_w 'NonPers'
/*      */     //   512: invokevirtual setOption : (Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   515: iload #9
/*      */     //   517: iconst_1
/*      */     //   518: if_icmpne -> 551
/*      */     //   521: iload #16
/*      */     //   523: ifle -> 551
/*      */     //   526: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   529: ifeq -> 540
/*      */     //   532: aload_0
/*      */     //   533: ldc_w 'Setting DupsOK'
/*      */     //   536: aconst_null
/*      */     //   537: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   540: aload #17
/*      */     //   542: ldc_w 'MQPSRegOpts'
/*      */     //   545: ldc_w 'DupsOK'
/*      */     //   548: invokevirtual setOption : (Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   551: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   554: ifeq -> 565
/*      */     //   557: aload_0
/*      */     //   558: ldc_w 'setting stream name'
/*      */     //   561: aconst_null
/*      */     //   562: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   565: aload_1
/*      */     //   566: invokevirtual getMQTopic : ()Lcom/ibm/msg/client/wmq/common/internal/WMQDestination;
/*      */     //   569: ifnull -> 645
/*      */     //   572: aload_1
/*      */     //   573: invokevirtual getMQTopic : ()Lcom/ibm/msg/client/wmq/common/internal/WMQDestination;
/*      */     //   576: ldc_w 'XMSC_WMQ_BROKER_PUBQ'
/*      */     //   579: invokevirtual getStringProperty : (Ljava/lang/String;)Ljava/lang/String;
/*      */     //   582: astore #19
/*      */     //   584: aconst_null
/*      */     //   585: aload #19
/*      */     //   587: if_acmpeq -> 614
/*      */     //   590: aload #19
/*      */     //   592: invokevirtual trim : ()Ljava/lang/String;
/*      */     //   595: invokevirtual length : ()I
/*      */     //   598: ifle -> 614
/*      */     //   601: aload #17
/*      */     //   603: ldc_w 'MQPSStreamName'
/*      */     //   606: aload #19
/*      */     //   608: invokevirtual set : (Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   611: goto -> 642
/*      */     //   614: aconst_null
/*      */     //   615: aload #12
/*      */     //   617: invokevirtual getBrk : ()Lcom/ibm/msg/client/wmq/compat/jms/internal/BrokerConnectionInfo;
/*      */     //   620: getfield streamQ : Ljava/lang/String;
/*      */     //   623: if_acmpeq -> 642
/*      */     //   626: aload #17
/*      */     //   628: ldc_w 'MQPSStreamName'
/*      */     //   631: aload #12
/*      */     //   633: invokevirtual getBrk : ()Lcom/ibm/msg/client/wmq/compat/jms/internal/BrokerConnectionInfo;
/*      */     //   636: getfield streamQ : Ljava/lang/String;
/*      */     //   639: invokevirtual set : (Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   642: goto -> 659
/*      */     //   645: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   648: ifeq -> 659
/*      */     //   651: aload_0
/*      */     //   652: ldc_w 'subscription.getMQTopic() returned null'
/*      */     //   655: aconst_null
/*      */     //   656: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   659: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   662: ifeq -> 673
/*      */     //   665: aload_0
/*      */     //   666: ldc_w 'setting queue Name'
/*      */     //   669: aconst_null
/*      */     //   670: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   673: aload #17
/*      */     //   675: ldc_w 'MQPSQName'
/*      */     //   678: aload_1
/*      */     //   679: invokevirtual getQueueName : ()Ljava/lang/String;
/*      */     //   682: invokevirtual set : (Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   685: aload_1
/*      */     //   686: invokevirtual getFilter : ()Ljava/lang/String;
/*      */     //   689: astore #11
/*      */     //   691: aload #11
/*      */     //   693: ifnull -> 738
/*      */     //   696: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   699: ifeq -> 728
/*      */     //   702: aload_0
/*      */     //   703: new java/lang/StringBuilder
/*      */     //   706: dup
/*      */     //   707: invokespecial <init> : ()V
/*      */     //   710: ldc_w 'setting filter to '
/*      */     //   713: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   716: aload #11
/*      */     //   718: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   721: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   724: aconst_null
/*      */     //   725: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   728: aload #17
/*      */     //   730: ldc_w 'MQPSFilter'
/*      */     //   733: aload #11
/*      */     //   735: invokevirtual set : (Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   738: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   741: ifeq -> 752
/*      */     //   744: aload_0
/*      */     //   745: ldc_w 'writing to message'
/*      */     //   748: aconst_null
/*      */     //   749: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   752: aload #17
/*      */     //   754: aload #18
/*      */     //   756: invokevirtual writeToMessage : (Lcom/ibm/msg/client/wmq/compat/base/internal/MQMsg2;)V
/*      */     //   759: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   762: ifeq -> 773
/*      */     //   765: aload_0
/*      */     //   766: ldc_w 'setting jms message as persistent'
/*      */     //   769: aconst_null
/*      */     //   770: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   773: aload #18
/*      */     //   775: iconst_1
/*      */     //   776: invokevirtual setPersistence : (I)V
/*      */     //   779: aload #18
/*      */     //   781: aload #17
/*      */     //   783: invokevirtual getHeaderFormat : ()Ljava/lang/String;
/*      */     //   786: invokevirtual setFormat : (Ljava/lang/String;)V
/*      */     //   789: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   792: ifeq -> 803
/*      */     //   795: aload_0
/*      */     //   796: ldc_w 'setting jmsMessage reply to queue'
/*      */     //   799: aconst_null
/*      */     //   800: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   803: aload #18
/*      */     //   805: ldc_w 'SYSTEM.JMS.REPORT.QUEUE'
/*      */     //   808: invokevirtual setReplyToQueueName : (Ljava/lang/String;)V
/*      */     //   811: iload #6
/*      */     //   813: ifeq -> 838
/*      */     //   816: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   819: ifeq -> 831
/*      */     //   822: aload_0
/*      */     //   823: ldc_w 'setting jmsMessage correlId: '
/*      */     //   826: aload #14
/*      */     //   828: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   831: aload #18
/*      */     //   833: aload #14
/*      */     //   835: invokevirtual setCorrelationId : ([B)V
/*      */     //   838: iload_3
/*      */     //   839: ifeq -> 862
/*      */     //   842: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   845: ifeq -> 856
/*      */     //   848: aload_0
/*      */     //   849: ldc_w 'setting jmsMessage report options to wait'
/*      */     //   852: aconst_null
/*      */     //   853: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   856: aload #18
/*      */     //   858: iconst_3
/*      */     //   859: invokevirtual setReport : (I)V
/*      */     //   862: aload #10
/*      */     //   864: ifnull -> 888
/*      */     //   867: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   870: ifeq -> 881
/*      */     //   873: aload_0
/*      */     //   874: ldc_w 'setting userId'
/*      */     //   877: aconst_null
/*      */     //   878: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   881: aload #18
/*      */     //   883: aload #10
/*      */     //   885: invokevirtual setUserId : (Ljava/lang/String;)V
/*      */     //   888: goto -> 988
/*      */     //   891: astore #19
/*      */     //   893: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   896: ifeq -> 911
/*      */     //   899: aload_0
/*      */     //   900: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine'
/*      */     //   902: ldc_w 'sendBrokerCommand(MQQueueSubscription,int,boolean)'
/*      */     //   905: aload #19
/*      */     //   907: iconst_1
/*      */     //   908: invokestatic catchBlock : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   911: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   914: ifeq -> 929
/*      */     //   917: aload_0
/*      */     //   918: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine'
/*      */     //   920: ldc_w 'sendBrokerCommand(MQQueueSubscription,int,boolean)'
/*      */     //   923: aload #19
/*      */     //   925: iconst_2
/*      */     //   926: invokestatic throwing : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   929: aload #19
/*      */     //   931: athrow
/*      */     //   932: astore #19
/*      */     //   934: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   937: ifeq -> 952
/*      */     //   940: aload_0
/*      */     //   941: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine'
/*      */     //   943: ldc_w 'sendBrokerCommand(MQQueueSubscription,int,boolean)'
/*      */     //   946: aload #19
/*      */     //   948: iconst_2
/*      */     //   949: invokestatic catchBlock : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   952: ldc_w 'MQJMS3008'
/*      */     //   955: invokestatic newException : (Ljava/lang/String;)Ljavax/jms/JMSException;
/*      */     //   958: astore #4
/*      */     //   960: aload #4
/*      */     //   962: aload #19
/*      */     //   964: invokevirtual setLinkedException : (Ljava/lang/Exception;)V
/*      */     //   967: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   970: ifeq -> 985
/*      */     //   973: aload_0
/*      */     //   974: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine'
/*      */     //   976: ldc_w 'sendBrokerCommand(MQQueueSubscription,int,boolean)'
/*      */     //   979: aload #4
/*      */     //   981: iconst_3
/*      */     //   982: invokestatic throwing : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   985: aload #4
/*      */     //   987: athrow
/*      */     //   988: aload #12
/*      */     //   990: invokevirtual getQM : ()Lcom/ibm/msg/client/wmq/compat/base/internal/MQQueueManager;
/*      */     //   993: aload #13
/*      */     //   995: getfield qmName : Ljava/lang/String;
/*      */     //   998: aload #13
/*      */     //   1000: getfield controlQ : Ljava/lang/String;
/*      */     //   1003: aload #18
/*      */     //   1005: invokestatic MQPUT1 : (Lcom/ibm/msg/client/wmq/compat/base/internal/MQQueueManager;Ljava/lang/String;Ljava/lang/String;Lcom/ibm/msg/client/wmq/compat/jms/internal/MQJMSMessage;)V
/*      */     //   1008: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   1011: ifeq -> 1026
/*      */     //   1014: aload_0
/*      */     //   1015: ldc_w 'created msg id: '
/*      */     //   1018: aload #18
/*      */     //   1020: invokevirtual getMessageId : ()[B
/*      */     //   1023: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   1026: goto -> 1197
/*      */     //   1029: astore #19
/*      */     //   1031: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   1034: ifeq -> 1049
/*      */     //   1037: aload_0
/*      */     //   1038: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine'
/*      */     //   1040: ldc_w 'sendBrokerCommand(MQQueueSubscription,int,boolean)'
/*      */     //   1043: aload #19
/*      */     //   1045: iconst_3
/*      */     //   1046: invokestatic catchBlock : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   1049: iconst_0
/*      */     //   1050: istore #20
/*      */     //   1052: aload #19
/*      */     //   1054: invokevirtual getLinkedException : ()Ljava/lang/Exception;
/*      */     //   1057: astore #21
/*      */     //   1059: aload #21
/*      */     //   1061: instanceof com/ibm/mq/MQException
/*      */     //   1064: ifeq -> 1097
/*      */     //   1067: aload #21
/*      */     //   1069: checkcast com/ibm/mq/MQException
/*      */     //   1072: astore #22
/*      */     //   1074: aload #22
/*      */     //   1076: getfield completionCode : I
/*      */     //   1079: iconst_1
/*      */     //   1080: if_icmpne -> 1097
/*      */     //   1083: aload #22
/*      */     //   1085: getfield reasonCode : I
/*      */     //   1088: sipush #2104
/*      */     //   1091: if_icmpne -> 1097
/*      */     //   1094: iconst_1
/*      */     //   1095: istore #20
/*      */     //   1097: iload #20
/*      */     //   1099: ifne -> 1138
/*      */     //   1102: ldc_w 'MQJMS3009'
/*      */     //   1105: invokestatic newException : (Ljava/lang/String;)Ljavax/jms/JMSException;
/*      */     //   1108: astore #4
/*      */     //   1110: aload #4
/*      */     //   1112: aload #19
/*      */     //   1114: invokevirtual setLinkedException : (Ljava/lang/Exception;)V
/*      */     //   1117: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   1120: ifeq -> 1135
/*      */     //   1123: aload_0
/*      */     //   1124: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine'
/*      */     //   1126: ldc_w 'sendBrokerCommand(MQQueueSubscription,int,boolean)'
/*      */     //   1129: aload #4
/*      */     //   1131: iconst_4
/*      */     //   1132: invokestatic throwing : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   1135: aload #4
/*      */     //   1137: athrow
/*      */     //   1138: goto -> 1197
/*      */     //   1141: astore #19
/*      */     //   1143: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   1146: ifeq -> 1161
/*      */     //   1149: aload_0
/*      */     //   1150: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine'
/*      */     //   1152: ldc_w 'sendBrokerCommand(MQQueueSubscription,int,boolean)'
/*      */     //   1155: aload #19
/*      */     //   1157: iconst_4
/*      */     //   1158: invokestatic catchBlock : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   1161: ldc_w 'MQJMS3009'
/*      */     //   1164: invokestatic newException : (Ljava/lang/String;)Ljavax/jms/JMSException;
/*      */     //   1167: astore #4
/*      */     //   1169: aload #4
/*      */     //   1171: aload #19
/*      */     //   1173: invokevirtual setLinkedException : (Ljava/lang/Exception;)V
/*      */     //   1176: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   1179: ifeq -> 1194
/*      */     //   1182: aload_0
/*      */     //   1183: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine'
/*      */     //   1185: ldc_w 'sendBrokerCommand(MQQueueSubscription,int,boolean)'
/*      */     //   1188: aload #4
/*      */     //   1190: iconst_5
/*      */     //   1191: invokestatic throwing : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   1194: aload #4
/*      */     //   1196: athrow
/*      */     //   1197: iload_3
/*      */     //   1198: ifeq -> 1396
/*      */     //   1201: new com/ibm/msg/client/wmq/compat/base/internal/MQMsg2
/*      */     //   1204: dup
/*      */     //   1205: invokespecial <init> : ()V
/*      */     //   1208: astore #5
/*      */     //   1210: aload #5
/*      */     //   1212: aload #18
/*      */     //   1214: invokevirtual getMessageId : ()[B
/*      */     //   1217: invokevirtual setCorrelationId : ([B)V
/*      */     //   1220: aload_0
/*      */     //   1221: aload #12
/*      */     //   1223: aload #5
/*      */     //   1225: invokevirtual getBrokerResponse : (Lcom/ibm/msg/client/wmq/compat/jms/internal/MQSession;Lcom/ibm/msg/client/wmq/compat/base/internal/MQMsg2;)V
/*      */     //   1228: aload #5
/*      */     //   1230: invokestatic checkResponse : (Lcom/ibm/msg/client/wmq/compat/base/internal/MQMsg2;)V
/*      */     //   1233: goto -> 1396
/*      */     //   1236: astore #19
/*      */     //   1238: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   1241: ifeq -> 1256
/*      */     //   1244: aload_0
/*      */     //   1245: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine'
/*      */     //   1247: ldc_w 'sendBrokerCommand(MQQueueSubscription,int,boolean)'
/*      */     //   1250: aload #19
/*      */     //   1252: iconst_5
/*      */     //   1253: invokestatic catchBlock : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   1256: aload #19
/*      */     //   1258: invokevirtual getReason : ()I
/*      */     //   1261: lookupswitch default -> 1355, 3078 -> 1337, 3083 -> 1288
/*      */     //   1288: iload #16
/*      */     //   1290: ifle -> 1355
/*      */     //   1293: iload #9
/*      */     //   1295: iconst_1
/*      */     //   1296: if_icmpne -> 1308
/*      */     //   1299: iinc #16, -1
/*      */     //   1302: iconst_1
/*      */     //   1303: istore #7
/*      */     //   1305: goto -> 1320
/*      */     //   1308: iload #8
/*      */     //   1310: iconst_1
/*      */     //   1311: if_icmpne -> 1320
/*      */     //   1314: iinc #16, -1
/*      */     //   1317: iconst_1
/*      */     //   1318: istore #7
/*      */     //   1320: iload #7
/*      */     //   1322: ifeq -> 1355
/*      */     //   1325: aload_0
/*      */     //   1326: invokevirtual getMQConnection : ()Lcom/ibm/msg/client/wmq/compat/jms/internal/MQConnection;
/*      */     //   1329: iload #16
/*      */     //   1331: invokevirtual setBrkOptLevel : (I)V
/*      */     //   1334: goto -> 1355
/*      */     //   1337: aload #10
/*      */     //   1339: ifnonnull -> 1355
/*      */     //   1342: aload #19
/*      */     //   1344: invokevirtual getUserId : ()Ljava/lang/String;
/*      */     //   1347: astore #10
/*      */     //   1349: iconst_1
/*      */     //   1350: istore #7
/*      */     //   1352: goto -> 1355
/*      */     //   1355: iload #7
/*      */     //   1357: ifne -> 1396
/*      */     //   1360: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   1363: ifeq -> 1374
/*      */     //   1366: aload_0
/*      */     //   1367: ldc_w 'Failed to reregister.'
/*      */     //   1370: aconst_null
/*      */     //   1371: invokestatic traceData : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V
/*      */     //   1374: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   1377: ifeq -> 1393
/*      */     //   1380: aload_0
/*      */     //   1381: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine'
/*      */     //   1383: ldc_w 'sendBrokerCommand(MQQueueSubscription,int,boolean)'
/*      */     //   1386: aload #19
/*      */     //   1388: bipush #6
/*      */     //   1390: invokestatic throwing : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   1393: aload #19
/*      */     //   1395: athrow
/*      */     //   1396: goto -> 148
/*      */     //   1399: goto -> 1445
/*      */     //   1402: astore #17
/*      */     //   1404: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   1407: ifeq -> 1423
/*      */     //   1410: aload_0
/*      */     //   1411: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine'
/*      */     //   1413: ldc_w 'sendBrokerCommand(MQQueueSubscription,int,boolean)'
/*      */     //   1416: aload #17
/*      */     //   1418: bipush #6
/*      */     //   1420: invokestatic catchBlock : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   1423: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   1426: ifeq -> 1442
/*      */     //   1429: aload_0
/*      */     //   1430: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine'
/*      */     //   1432: ldc_w 'sendBrokerCommand(MQQueueSubscription,int,boolean)'
/*      */     //   1435: aload #17
/*      */     //   1437: bipush #7
/*      */     //   1439: invokestatic throwing : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   1442: aload #17
/*      */     //   1444: athrow
/*      */     //   1445: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   1448: ifeq -> 1460
/*      */     //   1451: aload_0
/*      */     //   1452: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine'
/*      */     //   1454: ldc_w 'sendBrokerCommand(MQQueueSubscription,int,boolean)'
/*      */     //   1457: invokestatic exit : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   1460: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #2492	-> 0
/*      */     //   #2493	-> 6
/*      */     //   #2495	-> 23
/*      */     //   #2493	-> 34
/*      */     //   #2499	-> 37
/*      */     //   #2500	-> 40
/*      */     //   #2501	-> 43
/*      */     //   #2502	-> 46
/*      */     //   #2503	-> 49
/*      */     //   #2504	-> 52
/*      */     //   #2505	-> 55
/*      */     //   #2506	-> 58
/*      */     //   #2507	-> 64
/*      */     //   #2508	-> 71
/*      */     //   #2509	-> 77
/*      */     //   #2510	-> 83
/*      */     //   #2522	-> 92
/*      */     //   #2523	-> 128
/*      */     //   #2528	-> 131
/*      */     //   #2529	-> 145
/*      */     //   #2534	-> 148
/*      */     //   #2536	-> 154
/*      */     //   #2538	-> 157
/*      */     //   #2539	-> 178
/*      */     //   #2540	-> 184
/*      */     //   #2542	-> 192
/*      */     //   #2544	-> 204
/*      */     //   #2545	-> 210
/*      */     //   #2547	-> 218
/*      */     //   #2550	-> 227
/*      */     //   #2552	-> 236
/*      */     //   #2555	-> 239
/*      */     //   #2557	-> 272
/*      */     //   #2558	-> 283
/*      */     //   #2559	-> 290
/*      */     //   #2560	-> 301
/*      */     //   #2564	-> 307
/*      */     //   #2565	-> 318
/*      */     //   #2566	-> 325
/*      */     //   #2567	-> 336
/*      */     //   #2574	-> 342
/*      */     //   #2575	-> 348
/*      */     //   #2577	-> 373
/*      */     //   #2578	-> 400
/*      */     //   #2579	-> 406
/*      */     //   #2583	-> 418
/*      */     //   #2590	-> 421
/*      */     //   #2591	-> 427
/*      */     //   #2593	-> 435
/*      */     //   #2596	-> 447
/*      */     //   #2597	-> 453
/*      */     //   #2600	-> 479
/*      */     //   #2601	-> 490
/*      */     //   #2602	-> 496
/*      */     //   #2604	-> 504
/*      */     //   #2607	-> 515
/*      */     //   #2608	-> 526
/*      */     //   #2609	-> 532
/*      */     //   #2611	-> 540
/*      */     //   #2614	-> 551
/*      */     //   #2615	-> 557
/*      */     //   #2618	-> 565
/*      */     //   #2621	-> 572
/*      */     //   #2623	-> 584
/*      */     //   #2624	-> 601
/*      */     //   #2625	-> 614
/*      */     //   #2626	-> 626
/*      */     //   #2629	-> 642
/*      */     //   #2631	-> 645
/*      */     //   #2632	-> 651
/*      */     //   #2636	-> 659
/*      */     //   #2637	-> 665
/*      */     //   #2639	-> 673
/*      */     //   #2642	-> 685
/*      */     //   #2643	-> 691
/*      */     //   #2644	-> 696
/*      */     //   #2645	-> 702
/*      */     //   #2647	-> 728
/*      */     //   #2650	-> 738
/*      */     //   #2651	-> 744
/*      */     //   #2653	-> 752
/*      */     //   #2657	-> 759
/*      */     //   #2658	-> 765
/*      */     //   #2660	-> 773
/*      */     //   #2663	-> 779
/*      */     //   #2666	-> 789
/*      */     //   #2667	-> 795
/*      */     //   #2669	-> 803
/*      */     //   #2672	-> 811
/*      */     //   #2673	-> 816
/*      */     //   #2674	-> 822
/*      */     //   #2676	-> 831
/*      */     //   #2679	-> 838
/*      */     //   #2681	-> 842
/*      */     //   #2682	-> 848
/*      */     //   #2684	-> 856
/*      */     //   #2689	-> 862
/*      */     //   #2690	-> 867
/*      */     //   #2691	-> 873
/*      */     //   #2693	-> 881
/*      */     //   #2724	-> 888
/*      */     //   #2697	-> 891
/*      */     //   #2698	-> 893
/*      */     //   #2699	-> 899
/*      */     //   #2703	-> 911
/*      */     //   #2704	-> 917
/*      */     //   #2708	-> 929
/*      */     //   #2710	-> 932
/*      */     //   #2711	-> 934
/*      */     //   #2712	-> 940
/*      */     //   #2716	-> 952
/*      */     //   #2717	-> 960
/*      */     //   #2718	-> 967
/*      */     //   #2719	-> 973
/*      */     //   #2723	-> 985
/*      */     //   #2730	-> 988
/*      */     //   #2731	-> 1008
/*      */     //   #2732	-> 1014
/*      */     //   #2782	-> 1026
/*      */     //   #2735	-> 1029
/*      */     //   #2736	-> 1031
/*      */     //   #2737	-> 1037
/*      */     //   #2741	-> 1049
/*      */     //   #2742	-> 1052
/*      */     //   #2743	-> 1059
/*      */     //   #2744	-> 1067
/*      */     //   #2753	-> 1074
/*      */     //   #2754	-> 1094
/*      */     //   #2757	-> 1097
/*      */     //   #2758	-> 1102
/*      */     //   #2759	-> 1110
/*      */     //   #2760	-> 1117
/*      */     //   #2761	-> 1123
/*      */     //   #2765	-> 1135
/*      */     //   #2782	-> 1138
/*      */     //   #2768	-> 1141
/*      */     //   #2769	-> 1143
/*      */     //   #2770	-> 1149
/*      */     //   #2774	-> 1161
/*      */     //   #2775	-> 1169
/*      */     //   #2776	-> 1176
/*      */     //   #2777	-> 1182
/*      */     //   #2781	-> 1194
/*      */     //   #2787	-> 1197
/*      */     //   #2789	-> 1201
/*      */     //   #2791	-> 1210
/*      */     //   #2793	-> 1220
/*      */     //   #2798	-> 1228
/*      */     //   #2854	-> 1233
/*      */     //   #2801	-> 1236
/*      */     //   #2802	-> 1238
/*      */     //   #2803	-> 1244
/*      */     //   #2809	-> 1256
/*      */     //   #2814	-> 1288
/*      */     //   #2817	-> 1293
/*      */     //   #2818	-> 1299
/*      */     //   #2819	-> 1302
/*      */     //   #2820	-> 1308
/*      */     //   #2821	-> 1314
/*      */     //   #2822	-> 1317
/*      */     //   #2824	-> 1320
/*      */     //   #2825	-> 1325
/*      */     //   #2832	-> 1337
/*      */     //   #2833	-> 1342
/*      */     //   #2834	-> 1349
/*      */     //   #2843	-> 1355
/*      */     //   #2844	-> 1360
/*      */     //   #2845	-> 1366
/*      */     //   #2847	-> 1374
/*      */     //   #2848	-> 1380
/*      */     //   #2852	-> 1393
/*      */     //   #2856	-> 1396
/*      */     //   #2870	-> 1399
/*      */     //   #2858	-> 1402
/*      */     //   #2859	-> 1404
/*      */     //   #2860	-> 1410
/*      */     //   #2864	-> 1423
/*      */     //   #2865	-> 1429
/*      */     //   #2869	-> 1442
/*      */     //   #2872	-> 1445
/*      */     //   #2873	-> 1451
/*      */     //   #2876	-> 1460
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   400	21	4	je	Ljavax/jms/JMSException;
/*      */     //   584	58	19	subscriptionBrokerPubQueue	Ljava/lang/String;
/*      */     //   893	39	19	rte	Ljava/lang/RuntimeException;
/*      */     //   934	54	19	e	Ljava/lang/Exception;
/*      */     //   960	28	4	je	Ljavax/jms/JMSException;
/*      */     //   1074	23	22	mqe	Lcom/ibm/mq/MQException;
/*      */     //   1110	28	4	je	Ljavax/jms/JMSException;
/*      */     //   1052	86	20	subsume	Z
/*      */     //   1059	79	21	le	Ljava/lang/Exception;
/*      */     //   1031	107	19	e	Ljavax/jms/JMSException;
/*      */     //   1143	54	19	e	Ljava/lang/Exception;
/*      */     //   1169	28	4	je	Ljavax/jms/JMSException;
/*      */     //   1238	158	19	e	Lcom/ibm/mq/jms/BrokerCommandFailedException;
/*      */     //   157	1239	17	brkMsg	Lcom/ibm/msg/client/wmq/compat/jms/internal/MQBrokerMessage;
/*      */     //   236	1160	18	jmsMessage	Lcom/ibm/msg/client/wmq/compat/jms/internal/MQJMSMessage;
/*      */     //   1404	41	17	jmse	Ljavax/jms/JMSException;
/*      */     //   0	1461	0	this	Lcom/ibm/msg/client/wmq/compat/jms/internal/MQQueueSubscriptionEngine;
/*      */     //   0	1461	1	subscription	Lcom/ibm/msg/client/wmq/compat/jms/internal/MQQueueSubscription;
/*      */     //   0	1461	2	command	I
/*      */     //   0	1461	3	wait	Z
/*      */     //   40	1421	5	response	Lcom/ibm/msg/client/wmq/compat/base/internal/MQMsg2;
/*      */     //   43	1418	6	useCorrelId	Z
/*      */     //   46	1415	7	retry	Z
/*      */     //   49	1412	8	nonPersOption	Z
/*      */     //   52	1409	9	dupsOKOption	Z
/*      */     //   55	1406	10	userId	Ljava/lang/String;
/*      */     //   58	1403	11	filter	Ljava/lang/String;
/*      */     //   64	1397	12	mqs	Lcom/ibm/msg/client/wmq/compat/jms/internal/MQSession;
/*      */     //   71	1390	13	brk	Lcom/ibm/msg/client/wmq/compat/jms/internal/BrokerConnectionInfo;
/*      */     //   77	1384	14	subscriberId	[B
/*      */     //   83	1378	15	durable	Z
/*      */     //   92	1369	16	brkOptLevel	I
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   148	1399	1402	javax/jms/JMSException
/*      */     //   421	888	891	java/lang/RuntimeException
/*      */     //   421	888	932	java/lang/Exception
/*      */     //   988	1026	1029	javax/jms/JMSException
/*      */     //   988	1026	1141	java/lang/Exception
/*      */     //   1228	1233	1236	com/ibm/mq/jms/BrokerCommandFailedException
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void getBrokerResponse(MQSession mqs, MQMsg2 response) throws NoBrokerResponseException {
/* 2889 */     if (Trace.isOn) {
/* 2890 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2)", new Object[] { mqs, response });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2897 */       MQQueue replyQ = mqs.getResponseQueue();
/*      */       
/* 2899 */       MQGetMessageOptions brokerResponseGmo = new MQGetMessageOptions();
/* 2900 */       brokerResponseGmo.options = 1;
/* 2901 */       brokerResponseGmo.matchOptions = 2;
/* 2902 */       brokerResponseGmo.waitInterval = 120000;
/*      */ 
/*      */       
/* 2905 */       if (this.connection.getFailIfQuiesce() == 1) {
/* 2906 */         brokerResponseGmo.options |= 0x2000;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2911 */       replyQ.getMsg2(response, brokerResponseGmo);
/*      */     
/*      */     }
/* 2914 */     catch (MQException e) {
/* 2915 */       if (Trace.isOn) {
/* 2916 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2)", (Throwable)e, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2921 */       String key = "MQJMS5053";
/* 2922 */       String msg = ConfigEnvironment.getErrorMessage(key);
/* 2923 */       NoBrokerResponseException je = new NoBrokerResponseException(msg, key);
/* 2924 */       je.setLinkedException((Exception)e);
/* 2925 */       if (Trace.isOn) {
/* 2926 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2)", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */       
/* 2930 */       throw je;
/*      */     }
/* 2932 */     catch (JMSException je) {
/* 2933 */       if (Trace.isOn) {
/* 2934 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */       
/* 2938 */       RuntimeException traceRet1 = new RuntimeException("MQJMS1016");
/* 2939 */       if (Trace.isOn) {
/* 2940 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2)", traceRet1, 2);
/*      */       }
/*      */ 
/*      */       
/* 2944 */       throw traceRet1;
/*      */     } 
/*      */     
/* 2947 */     if (Trace.isOn) {
/* 2948 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2)");
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
/*      */   public void unsubscribe(MQQueue adminQueue, MQSession mqs, String subName) throws JMSException {
/* 2965 */     if (Trace.isOn) {
/* 2966 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "unsubscribe(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQSession,String)", new Object[] { adminQueue, mqs, subName });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2973 */       MQQueueSubscription sub = new MQQueueSubscription(this, mqs, true, false, mqs.getQMName(), getMQConnection().getClientID(), subName, null, null, false, null, null, null);
/*      */       
/* 2975 */       MQQueueSubscription subscription = getResolvedSubscription(adminQueue, sub, (byte[])null, false);
/*      */       
/* 2977 */       if (subscription == null) {
/*      */         
/* 2979 */         if (Trace.isOn) {
/* 2980 */           Trace.traceData(this, "No entry found for '" + subName + "'", null);
/*      */         }
/*      */ 
/*      */         
/* 2984 */         String key = "MQJMS3018";
/* 2985 */         String msg = ConfigEnvironment.getErrorMessage(key, subName);
/* 2986 */         InvalidDestinationException ide = new InvalidDestinationException(msg, key);
/* 2987 */         if (Trace.isOn) {
/* 2988 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "unsubscribe(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQSession,String)", (Throwable)ide, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2993 */         throw ide;
/*      */       } 
/*      */ 
/*      */       
/* 2997 */       if (Trace.isOn) {
/* 2998 */         Trace.traceData(this, "Entry found for '" + subName + "'", null);
/* 2999 */         Trace.traceData(this, subscription.toString(), null);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3006 */       MQQueue subQ = null;
/*      */       try {
/* 3008 */         subQ = checkInactive(subscription);
/*      */       
/*      */       }
/* 3011 */       catch (JMSException je) {
/* 3012 */         if (Trace.isOn) {
/* 3013 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "unsubscribe(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQSession,String)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3019 */         MQException e = (MQException)je.getLinkedException();
/* 3020 */         switch (e.reasonCode) {
/*      */ 
/*      */           
/*      */           case 2052:
/*      */           case 2085:
/* 3025 */             if (Trace.isOn) {
/* 3026 */               Trace.traceData(this, "Warning: durable sub queue '" + subscription.getQueueName() + " is missing\n", null);
/* 3027 */               Trace.traceData(this, "Carrying on with deregister", null);
/*      */             } 
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/* 3034 */             if (Trace.isOn) {
/* 3035 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "unsubscribe(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQSession,String)", (Throwable)je, 2);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 3040 */             throw je;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       } 
/* 3048 */       subscription.setSubscriberQueue(subQ);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3054 */       subscription.setSubscriberState('t');
/*      */ 
/*      */ 
/*      */       
/* 3058 */       addSubscriptionEntry(adminQueue, subscription, false);
/*      */ 
/*      */ 
/*      */       
/* 3062 */       deleteSubscriber(subscription);
/*      */ 
/*      */       
/*      */       try {
/* 3066 */         removeSubscriptionEntry(adminQueue, subscription);
/*      */       }
/* 3068 */       catch (JMSException je) {
/* 3069 */         if (Trace.isOn) {
/* 3070 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "unsubscribe(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQSession,String)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3078 */         if (Trace.isOn) {
/* 3079 */           Trace.traceData(this, "unsubscribe problem - failed to remove the admin entry.", null);
/*      */         }
/*      */ 
/*      */         
/* 3083 */         JMSException je2 = ConfigEnvironment.newException("MQJMS3013");
/* 3084 */         if (Trace.isOn) {
/* 3085 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "unsubscribe(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQSession,String)", (Throwable)je2, 3);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 3090 */         throw je2;
/*      */       } 
/*      */ 
/*      */       
/* 3094 */       removeSubscriptionEntry(adminQueue, subscription);
/*      */     
/*      */     }
/* 3097 */     catch (JMSException je) {
/* 3098 */       if (Trace.isOn) {
/* 3099 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "unsubscribe(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQSession,String)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3104 */       if (Trace.isOn) {
/* 3105 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "unsubscribe(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQSession,String)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3110 */       throw je;
/*      */     } 
/*      */     
/* 3113 */     if (Trace.isOn) {
/* 3114 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "unsubscribe(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQSession,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   MQPSStatusMgr getPubSubStatusMgr() {
/* 3121 */     if (Trace.isOn) {
/* 3122 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getPubSubStatusMgr()", "getter", this.pubsubStatusMgr);
/*      */     }
/*      */     
/* 3125 */     return this.pubsubStatusMgr;
/*      */   }
/*      */ 
/*      */   
/*      */   byte[] getStatusMgrId() {
/* 3130 */     if (Trace.isOn) {
/* 3131 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "getStatusMgrId()", "getter", this.statusMgrId);
/*      */     }
/*      */     
/* 3134 */     return this.statusMgrId;
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
/*      */   boolean contains(MQQueue adminQueue, MQSession mqs, String key) throws JMSException {
/* 3148 */     if (Trace.isOn) {
/* 3149 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "contains(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQSession,String)", new Object[] { adminQueue, mqs, key });
/*      */     }
/*      */ 
/*      */     
/* 3153 */     boolean traceRet1 = (getSubscriptionEntry(adminQueue, mqs, key, (byte[])null, (byte[])null, false) != null);
/* 3154 */     if (Trace.isOn) {
/* 3155 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscriptionEngine", "contains(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQSession,String)", 
/*      */           
/* 3157 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 3159 */     return traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   private static class StatusMgrCloseLock
/*      */   {
/*      */     StatusMgrCloseLock() {
/* 3166 */       if (Trace.isOn) {
/* 3167 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrCloseLock", "<init>()");
/*      */       }
/*      */ 
/*      */       
/* 3171 */       if (Trace.isOn)
/* 3172 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrCloseLock", "<init>()"); 
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQQueueSubscriptionEngine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */