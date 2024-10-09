/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
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
/*      */ class MQMigrateSubscriptionEngine
/*      */   extends MQSubscriptionEngine
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQMigrateSubscriptionEngine.java";
/*      */   private int useBrokerSubStore;
/*      */   private static final int BSS_AVAILABLE = 1;
/*      */   private static final int BSS_UNAVAILABLE = 0;
/*      */   private static final int BSS_UNKNOWN = -1;
/*      */   private volatile MQQueueSubscriptionEngine queueSubStore;
/*      */   private MQBrokerSubscriptionEngine brokerSubStore;
/*      */   
/*      */   static {
/*   71 */     if (Trace.isOn) {
/*   72 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQMigrateSubscriptionEngine.java");
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
/*   93 */   private int WSClone = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQMigrateSubscriptionEngine(MQConnection connection, MQQueueManager qm, String resolvedQMName) throws JMSException {
/*  104 */     super(connection);
/*  105 */     if (Trace.isOn) {
/*  106 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "<init>(MQConnection,MQQueueManager,String)", new Object[] { connection, qm, resolvedQMName });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  113 */       this.WSClone = connection.getIntProperty("XMSC_WMQ_CLONE_SUPPORT");
/*  114 */       this.useBrokerSubStore = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  124 */       if (!qm.spiSupportsDeferred()) {
/*  125 */         if (Trace.isOn) {
/*  126 */           Trace.traceData(this, "Queue Manager doesn't support deferred messages - using Queue substore", null);
/*      */         }
/*  128 */         this.useBrokerSubStore = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  133 */       if (this.useBrokerSubStore == -1 || this.useBrokerSubStore == 0) {
/*  134 */         this.queueSubStore = new MQQueueSubscriptionEngine(connection, qm);
/*      */       }
/*  136 */       if (this.useBrokerSubStore == -1 || this.useBrokerSubStore == 1) {
/*  137 */         this.brokerSubStore = new MQBrokerSubscriptionEngine(connection, resolvedQMName);
/*      */       }
/*      */     }
/*  140 */     catch (MQException mqe) {
/*  141 */       if (Trace.isOn) {
/*  142 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "<init>(MQConnection,MQQueueManager,String)", (Throwable)mqe, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  147 */       JMSException je = ConfigEnvironment.newException("MQJMS1016");
/*  148 */       je.setLinkedException((Exception)mqe);
/*  149 */       if (Trace.isOn) {
/*  150 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "<init>(MQConnection,MQQueueManager,String)", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */       
/*  154 */       throw je;
/*      */     }
/*  156 */     catch (JMSException je) {
/*  157 */       if (Trace.isOn) {
/*  158 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "<init>(MQConnection,MQQueueManager,String)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  163 */       if (this.queueSubStore != null) {
/*  164 */         this.queueSubStore.close();
/*      */       }
/*  166 */       if (this.brokerSubStore != null) {
/*  167 */         this.brokerSubStore.close();
/*      */       }
/*  169 */       if (Trace.isOn) {
/*  170 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "<init>(MQConnection,MQQueueManager,String)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */       
/*  174 */       throw je;
/*      */     } 
/*      */     
/*  177 */     if (Trace.isOn) {
/*  178 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "<init>(MQConnection,MQQueueManager,String)");
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
/*      */   public MQSubscription openSubscription(MQSession mqts, WMQDestination topic, String selector, boolean noLocal, boolean sharedQueue, String queueName) throws JMSException {
/*  201 */     if (Trace.isOn) {
/*  202 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", new Object[] { mqts, topic, selector, 
/*      */             
/*  204 */             Boolean.valueOf(noLocal), Boolean.valueOf(sharedQueue), queueName });
/*      */     }
/*      */ 
/*      */     
/*  208 */     boolean retry = true;
/*  209 */     MQSubscription sub = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  219 */       while (retry) {
/*  220 */         switch (this.useBrokerSubStore) {
/*      */           case 1:
/*  222 */             retry = false;
/*  223 */             sub = this.brokerSubStore.openSubscription(mqts, topic, selector, noLocal, sharedQueue, queueName);
/*      */             continue;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case -1:
/*      */             try {
/*  231 */               if (!mqts.getQM().spiSupportsDeferred()) {
/*      */                 
/*  233 */                 if (this.useBrokerSubStore != 0) {
/*  234 */                   this.useBrokerSubStore = 0;
/*  235 */                   this.brokerSubStore.close();
/*  236 */                   this.brokerSubStore = null;
/*      */                 } 
/*  238 */                 retry = true;
/*      */                 
/*      */                 continue;
/*      */               } 
/*      */               
/*      */               try {
/*  244 */                 sub = this.brokerSubStore.openSubscription(mqts, topic, selector, noLocal, sharedQueue, queueName);
/*      */                 
/*  246 */                 this.useBrokerSubStore = 1;
/*  247 */                 retry = false;
/*      */ 
/*      */               
/*      */               }
/*  251 */               catch (JMSException je) {
/*  252 */                 if (Trace.isOn) {
/*  253 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)je, 1);
/*      */                 }
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  259 */                 if (je instanceof BrokerCommandFailedException) {
/*  260 */                   BrokerCommandFailedException bcfe = (BrokerCommandFailedException)je;
/*      */                   
/*  262 */                   if (bcfe.getReason() == 3083 || bcfe.getReason() == 2337) {
/*      */                     
/*  264 */                     if (this.useBrokerSubStore != 0) {
/*  265 */                       this.useBrokerSubStore = 0;
/*  266 */                       this.brokerSubStore.close();
/*  267 */                       this.brokerSubStore = null;
/*      */                     } 
/*  269 */                     retry = true;
/*      */                     
/*      */                     continue;
/*      */                   } 
/*      */                 } 
/*  274 */                 if (Trace.isOn) {
/*  275 */                   Trace.traceData(this, "test of brokerSubStore failed. Broker type still unknown", null);
/*      */                 }
/*      */                 
/*  278 */                 if (Trace.isOn) {
/*  279 */                   Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)je, 1);
/*      */                 }
/*      */ 
/*      */ 
/*      */                 
/*  284 */                 throw je;
/*      */               }
/*      */             
/*      */             }
/*  288 */             catch (Exception e) {
/*  289 */               JMSException je; if (Trace.isOn) {
/*  290 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", e, 2);
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  296 */               if (e instanceof JMSException) {
/*  297 */                 je = (JMSException)e;
/*      */               } else {
/*  299 */                 je = ConfigEnvironment.newException("MQJMS1016");
/*  300 */                 je.setLinkedException(e);
/*      */               } 
/*      */               
/*  303 */               if (Trace.isOn) {
/*  304 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)je, 2);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  309 */               throw je;
/*      */             } 
/*      */             continue;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  319 */         if (this.WSClone == 1) {
/*  320 */           if (Trace.isOn) {
/*  321 */             Trace.traceData(this, "Cannot have clone support enabled for a queueSubscriptionEngine.", null);
/*      */           }
/*  323 */           JMSException je = ConfigEnvironment.newException("MQJMS4125", "SUBSTORE(BROKER)", "CLONESUPP(ENABLED)");
/*      */           
/*  325 */           if (Trace.isOn) {
/*  326 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)je, 3);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  331 */           throw je;
/*      */         } 
/*      */         
/*  334 */         retry = false;
/*  335 */         sub = this.queueSubStore.openSubscription(mqts, topic, selector, noLocal, sharedQueue, queueName);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  341 */       if (this.queueSubStore != null && 
/*  342 */         this.useBrokerSubStore == 1) {
/*  343 */         if (Trace.isOn) {
/*  344 */           Trace.traceData(this, "BrokerSubEngine is available. Closing QueueSubscriptionEngine", null);
/*      */         }
/*  346 */         this.queueSubStore.close();
/*  347 */         this.queueSubStore = null;
/*      */       } 
/*      */       
/*  350 */       if (Trace.isOn) {
/*  351 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", sub);
/*      */       }
/*      */       
/*  354 */       return sub;
/*      */     
/*      */     }
/*  357 */     catch (JMSException je) {
/*  358 */       if (Trace.isOn) {
/*  359 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */       
/*  363 */       if (Trace.isOn) {
/*  364 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openSubscription(MQSession,WMQDestination,String,boolean,boolean,String)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */       
/*  368 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void closeSubscription(MQSubscription sub) throws JMSException {
/*  379 */     if (Trace.isOn) {
/*  380 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "closeSubscription(MQSubscription)", new Object[] { sub });
/*      */     }
/*      */     
/*  383 */     if (sub instanceof MQBrokerSubscription) {
/*  384 */       this.brokerSubStore.closeSubscription(sub);
/*  385 */     } else if (sub instanceof MQQueueSubscription) {
/*  386 */       this.queueSubStore.closeSubscription(sub);
/*      */     } else {
/*  388 */       JMSException je = ConfigEnvironment.newException("MQJMS3049");
/*  389 */       if (Trace.isOn) {
/*  390 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "closeSubscription(MQSubscription)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/*  394 */       throw je;
/*      */     } 
/*  396 */     if (Trace.isOn) {
/*  397 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "closeSubscription(MQSubscription)");
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
/*      */   public MQSubscription openDurableSubscription(MQSession mqs, WMQDestination topic, String selectorP, boolean noLocal, boolean sharedQueue, String queueName, String subName) throws JMSException {
/*  425 */     if (Trace.isOn) {
/*  426 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", new Object[] { mqs, topic, selectorP, 
/*      */             
/*  428 */             Boolean.valueOf(noLocal), Boolean.valueOf(sharedQueue), queueName, subName });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  433 */     String selector = selectorP;
/*  434 */     MQQueue adminQueue = null;
/*  435 */     MQQueueSubscription oldSubscription = null;
/*      */     
/*  437 */     MQBrokerSubscription newSubscription = null;
/*      */ 
/*      */     
/*  440 */     boolean queueSubscriptionOpen = false;
/*  441 */     boolean updateRequired = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  447 */       if (this.useBrokerSubStore == 0) {
/*      */ 
/*      */ 
/*      */         
/*  451 */         if (this.WSClone == 1) {
/*  452 */           if (Trace.isOn) {
/*  453 */             Trace.traceData(this, "Cannot have clone support enabled for a queueSubscriptionEngine.", null);
/*      */           }
/*  455 */           JMSException je = ConfigEnvironment.newException("MQJMS4125", "SUBSTORE(BROKER)", "CLONESUPP(ENABLED)");
/*      */           
/*  457 */           if (Trace.isOn) {
/*  458 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  463 */           throw je;
/*      */         } 
/*      */         
/*  466 */         if (Trace.isOn) {
/*  467 */           Trace.traceData(this, "BrokerSubStore unavailable. returning queueSubscription", null);
/*      */         }
/*  469 */         MQSubscription traceRet1 = this.queueSubStore.openDurableSubscription(mqs, topic, selector, noLocal, sharedQueue, queueName, subName);
/*  470 */         if (Trace.isOn) {
/*  471 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", traceRet1, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  476 */         return traceRet1;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  481 */       if (this.useBrokerSubStore == -1) {
/*  482 */         if (Trace.isOn) {
/*  483 */           Trace.traceData(this, "brokerSubStore state unknown. Checking QM level", null);
/*      */         }
/*      */ 
/*      */         
/*      */         try {
/*  488 */           if (!mqs.getQM().spiSupportsDeferred()) {
/*  489 */             if (Trace.isOn) {
/*  490 */               Trace.traceData(this, "deferred messages not supported. Returning queueSubscription", null);
/*      */             }
/*      */             
/*  493 */             if (this.useBrokerSubStore != 0) {
/*  494 */               this.useBrokerSubStore = 0;
/*  495 */               this.brokerSubStore.close();
/*  496 */               this.brokerSubStore = null;
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  502 */             if (this.WSClone == 1) {
/*  503 */               if (Trace.isOn) {
/*  504 */                 Trace.traceData(this, "Cannot have clone support enabled for a queueSubscriptionEngine.", null);
/*      */               }
/*  506 */               JMSException je = ConfigEnvironment.newException("MQJMS4125", "SUBSTORE(BROKER)", "CLONESUPP(ENABLED)");
/*  507 */               if (Trace.isOn) {
/*  508 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 2);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  513 */               throw je;
/*      */             } 
/*      */             
/*  516 */             MQSubscription traceRet2 = this.queueSubStore.openDurableSubscription(mqs, topic, selector, noLocal, sharedQueue, queueName, subName);
/*  517 */             if (Trace.isOn) {
/*  518 */               Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", traceRet2, 2);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  523 */             return traceRet2;
/*      */           }
/*      */         
/*  526 */         } catch (Exception e) {
/*  527 */           JMSException je; if (Trace.isOn) {
/*  528 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", e, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  534 */           if (e instanceof JMSException) {
/*  535 */             je = (JMSException)e;
/*      */           } else {
/*  537 */             je = ConfigEnvironment.newException(e.getMessage());
/*  538 */             je.setLinkedException(e);
/*      */           } 
/*      */           
/*  541 */           if (Trace.isOn) {
/*  542 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 3);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  547 */           throw je;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  558 */       int fiqBehaviour = 1;
/*  559 */       if (topic != null) {
/*      */         
/*  561 */         fiqBehaviour = topic.getIntProperty("failIfQuiesce");
/*      */       }
/*  563 */       else if (mqs != null) {
/*      */         
/*  565 */         fiqBehaviour = mqs.getFailIfQuiesce();
/*      */       } else {
/*  567 */         throw new NullPointerException("MQSession is null");
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  572 */         if (Trace.isOn) {
/*  573 */           Trace.traceData(this, "Checking for existing queueSubscription", null);
/*      */         }
/*      */         try {
/*  576 */           adminQueue = getAdminQueueAccess(mqs, fiqBehaviour);
/*      */         }
/*  578 */         catch (JMSException je) {
/*  579 */           if (Trace.isOn) {
/*  580 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 2);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  587 */           Exception e = je.getLinkedException();
/*  588 */           if (e instanceof MQException && ((MQException)e).reasonCode == 2085) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  595 */             if (Trace.isOn) {
/*  596 */               Trace.traceData(this, "adminQueue does not exist. Using brokerSubStore", null);
/*      */             }
/*      */             
/*  599 */             this.useBrokerSubStore = 1;
/*      */             
/*  601 */             newSubscription = (MQBrokerSubscription)this.brokerSubStore.openDurableSubscription(mqs, topic, selector, noLocal, sharedQueue, queueName, subName);
/*      */             
/*  603 */             if (this.queueSubStore != null) {
/*  604 */               if (Trace.isOn) {
/*  605 */                 Trace.traceData(this, "BrokerSubEngine available. Closing QueueSubscriptionEngine", null);
/*      */               }
/*  607 */               this.queueSubStore.close();
/*  608 */               this.queueSubStore = null;
/*      */             } 
/*  610 */             if (Trace.isOn) {
/*  611 */               Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", newSubscription, 3);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  616 */             return newSubscription;
/*      */           } 
/*      */ 
/*      */           
/*  620 */           if (Trace.isOn) {
/*  621 */             Trace.traceData(this, "problem opening admin queue to check for existing subscription", null);
/*      */           }
/*  623 */           if (Trace.isOn) {
/*  624 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 4);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  629 */           throw je;
/*      */         } 
/*      */ 
/*      */         
/*  633 */         if (Trace.isOn) {
/*  634 */           Trace.traceData(this, "opened adminQueue. Checking for existing subscription", null);
/*      */         }
/*      */         
/*  637 */         oldSubscription = checkQueueSubscription(mqs, subName, adminQueue, fiqBehaviour);
/*      */         
/*  639 */         if (oldSubscription == null)
/*      */         {
/*      */           
/*      */           try {
/*  643 */             adminQueue.close();
/*      */           }
/*  645 */           catch (MQException me) {
/*  646 */             if (Trace.isOn) {
/*  647 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)me, 3);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  652 */             if (Trace.isOn) {
/*  653 */               Trace.traceData(this, "error closing adminQueue", null);
/*      */             }
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*      */           try {
/*  660 */             if (Trace.isOn) {
/*  661 */               Trace.traceData(this, "no existing queueSubscription. attempting to return brokerSubscription", null);
/*      */             }
/*  663 */             newSubscription = (MQBrokerSubscription)this.brokerSubStore.openDurableSubscription(mqs, topic, selector, noLocal, sharedQueue, queueName, subName);
/*      */ 
/*      */             
/*  666 */             this.useBrokerSubStore = 1;
/*      */             
/*  668 */             if (this.queueSubStore != null) {
/*  669 */               if (Trace.isOn) {
/*  670 */                 Trace.traceData(this, "BrokerSubEngine available. Closing QueueSubscriptionEngine", null);
/*      */               }
/*  672 */               this.queueSubStore.close();
/*  673 */               this.queueSubStore = null;
/*      */             } 
/*  675 */             if (Trace.isOn) {
/*  676 */               Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", newSubscription, 4);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  681 */             return newSubscription;
/*      */           }
/*  683 */           catch (JMSException je) {
/*  684 */             if (Trace.isOn) {
/*  685 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 4);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  691 */             if (je instanceof BrokerCommandFailedException) {
/*  692 */               BrokerCommandFailedException bcfe = (BrokerCommandFailedException)je;
/*      */               
/*  694 */               if (bcfe.getReason() == 3083 || bcfe.getReason() == 2337) {
/*  695 */                 if (Trace.isOn) {
/*  696 */                   Trace.traceData(this, "new broker commands not recognized. Returning new queueSubscription", null);
/*      */                 }
/*      */                 
/*  699 */                 if (this.useBrokerSubStore != 0) {
/*  700 */                   this.useBrokerSubStore = 0;
/*  701 */                   this.brokerSubStore.close();
/*  702 */                   this.brokerSubStore = null;
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  708 */                 if (this.WSClone == 1) {
/*  709 */                   if (Trace.isOn) {
/*  710 */                     Trace.traceData(this, "Cannot have clone support enabled for a queueSubscriptionEngine.", null);
/*      */                   }
/*  712 */                   JMSException je2 = ConfigEnvironment.newException("MQJMS4125", "SUBSTORE(BROKER)", "CLONESUPP(ENABLED)");
/*      */                   
/*  714 */                   if (Trace.isOn) {
/*  715 */                     Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je2, 5);
/*      */                   }
/*      */ 
/*      */ 
/*      */                   
/*  720 */                   throw je2;
/*      */                 } 
/*      */                 
/*  723 */                 if (Trace.isOn) {
/*  724 */                   Trace.traceData(this, "about to call openDurableSubscription on queueSubStore object: " + this.queueSubStore, null);
/*      */                 }
/*      */ 
/*      */                 
/*  728 */                 if (this.queueSubStore != null) {
/*  729 */                   oldSubscription = (MQQueueSubscription)this.queueSubStore.openDurableSubscription(mqs, topic, selector, noLocal, sharedQueue, queueName, subName);
/*      */ 
/*      */                 
/*      */                 }
/*      */                 else {
/*      */ 
/*      */ 
/*      */                   
/*  737 */                   oldSubscription = null;
/*  738 */                   if (Trace.isOn) {
/*  739 */                     Trace.traceData(this, "queueSubStore object was null - returning null.", null);
/*      */                   }
/*      */                 } 
/*      */                 
/*  743 */                 if (oldSubscription != null) {
/*  744 */                   if (Trace.isOn) {
/*  745 */                     Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", oldSubscription, 5);
/*      */                   }
/*      */ 
/*      */ 
/*      */                   
/*  750 */                   return oldSubscription;
/*      */                 } 
/*      */                 
/*  753 */                 if (Trace.isOn) {
/*  754 */                   Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 6);
/*      */                 }
/*      */ 
/*      */ 
/*      */                 
/*  759 */                 throw je;
/*      */               } 
/*      */             } 
/*      */             
/*  763 */             if (Trace.isOn) {
/*  764 */               Trace.traceData(this, "test of brokerSubStore failed. Broker type still unknown", null);
/*      */             }
/*  766 */             if (Trace.isOn) {
/*  767 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 7);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  772 */             throw je;
/*      */           }
/*      */         
/*      */         }
/*      */       
/*  777 */       } catch (JMSException je) {
/*  778 */         if (Trace.isOn) {
/*  779 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 5);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  784 */         if (Trace.isOn) {
/*  785 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 8);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  790 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  798 */       oldSubscription.setMQTopic(topic);
/*      */       
/*  800 */       if (Trace.isOn) {
/*  801 */         Trace.traceData(this, "queueSubscription exists: " + oldSubscription.toString(), null);
/*      */       }
/*  803 */       if (selector == null) {
/*  804 */         selector = "";
/*      */       }
/*  806 */       if (oldSubscription.getSelector() == null) {
/*  807 */         oldSubscription.setSelector("");
/*      */       }
/*      */ 
/*      */       
/*  811 */       if (!topic.getStringProperty("XMSC_DESTINATION_NAME").equals(oldSubscription.getTopic()) || !selector.equals(oldSubscription.getSelector())) {
/*  812 */         updateRequired = true;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  819 */         if (Trace.isOn) {
/*  820 */           Trace.traceData(this, "opening existing queueSubscription", null);
/*      */         }
/*      */         try {
/*  823 */           adminQueue.close();
/*      */         }
/*  825 */         catch (MQException me) {
/*  826 */           if (Trace.isOn) {
/*  827 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)me, 6);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  832 */           if (Trace.isOn) {
/*  833 */             Trace.traceData(this, "error closing adminQueue", null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  839 */             JMSException je = new JMSException("error closing admin queue");
/*  840 */             je.setLinkedException((Exception)me);
/*  841 */             je.initCause((Throwable)me);
/*  842 */             if (Trace.isOn) {
/*  843 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 9);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  848 */             throw je;
/*      */           } 
/*      */         } 
/*      */         
/*  852 */         if (this.queueSubStore == null) {
/*  853 */           if (Trace.isOn) {
/*  854 */             Trace.traceData(this, "Reopening queueSubscriptionEngine for migration", null);
/*      */           }
/*  856 */           MQQueueManager qm = getMQConnection().createQMNonXA();
/*  857 */           this.queueSubStore = new MQQueueSubscriptionEngine(getMQConnection(), qm);
/*      */         } 
/*      */         
/*  860 */         oldSubscription = (MQQueueSubscription)this.queueSubStore.openDurableSubscription(mqs, topic, selector, noLocal, sharedQueue, queueName, subName);
/*      */ 
/*      */ 
/*      */         
/*  864 */         oldSubscription.setMQTopic(topic);
/*      */         
/*  866 */         queueSubscriptionOpen = true;
/*      */       
/*      */       }
/*  869 */       catch (JMSException je) {
/*  870 */         if (Trace.isOn) {
/*  871 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 7);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  876 */         if (Trace.isOn) {
/*  877 */           Trace.traceData(this, "error opening existing queueSubscription", null);
/*      */         }
/*  879 */         if (Trace.isOn) {
/*  880 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 10);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  885 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  895 */       if (updateRequired) {
/*      */ 
/*      */         
/*      */         try {
/*  899 */           newSubscription = (MQBrokerSubscription)this.brokerSubStore.openDurableSubscription(mqs, topic, selector, noLocal, sharedQueue, queueName, subName);
/*      */         }
/*  901 */         catch (JMSException je) {
/*  902 */           if (Trace.isOn) {
/*  903 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 8);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  909 */           if (je instanceof BrokerCommandFailedException) {
/*  910 */             BrokerCommandFailedException bcfe = (BrokerCommandFailedException)je;
/*      */             
/*  912 */             if (bcfe.getReason() == 3083 || bcfe.getReason() == 2337) {
/*  913 */               if (Trace.isOn) {
/*  914 */                 Trace.traceData(this, "new broker commands not recognized. Returning new queueSubscription", null);
/*      */               }
/*      */               
/*  917 */               if (this.useBrokerSubStore != 0) {
/*  918 */                 this.useBrokerSubStore = 0;
/*  919 */                 this.brokerSubStore.close();
/*  920 */                 this.brokerSubStore = null;
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/*  925 */               if (this.WSClone == 1) {
/*      */                 
/*  927 */                 if (Trace.isOn) {
/*  928 */                   Trace.traceData(this, "Cannot have clone support enabled for a queueSubscriptionEngine.", null);
/*      */                 }
/*  930 */                 oldSubscription.close();
/*  931 */                 JMSException je2 = ConfigEnvironment.newException("MQJMS4125", "SUBSTORE(BROKER)", "CLONESUPP(ENABLED)");
/*      */                 
/*  933 */                 if (Trace.isOn) {
/*  934 */                   Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je2, 11);
/*      */                 }
/*      */ 
/*      */ 
/*      */                 
/*  939 */                 throw je2;
/*      */               } 
/*      */               
/*  942 */               if (Trace.isOn) {
/*  943 */                 Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", oldSubscription, 6);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  948 */               return oldSubscription;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  955 */         oldSubscription.close();
/*      */         
/*  957 */         if (this.queueSubStore != null) {
/*  958 */           this.queueSubStore.durableUnsubscribe(mqs, subName);
/*  959 */           if (Trace.isOn) {
/*  960 */             Trace.traceData(this, "BrokerSubEngine available. Closing QueueSubscriptionEngine", null);
/*      */           }
/*  962 */           this.queueSubStore.close();
/*  963 */           this.queueSubStore = null;
/*      */         } 
/*      */         
/*  966 */         if (Trace.isOn) {
/*  967 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", newSubscription, 7);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  972 */         return newSubscription;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  979 */         newSubscription = getMigratedSubscription(oldSubscription, mqs, subName);
/*      */       }
/*  981 */       catch (JMSException je) {
/*  982 */         if (Trace.isOn) {
/*  983 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 9);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  989 */         if (this.useBrokerSubStore == 0) {
/*  990 */           if (Trace.isOn) {
/*  991 */             Trace.traceData(this, "broker appears not to support brokerSubscriptions. Returning queueSubscription", null);
/*      */           }
/*  993 */           if (Trace.isOn) {
/*  994 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", oldSubscription, 8);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  999 */           return oldSubscription;
/*      */         } 
/*      */         
/* 1002 */         if (Trace.isOn) {
/* 1003 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 12);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1008 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1015 */         if (Trace.isOn) {
/* 1016 */           Trace.traceData(this, "removing old adminQueue entry", null);
/*      */         }
/* 1018 */         removeQueueSubscription(mqs, oldSubscription);
/* 1019 */         if (this.queueSubStore != null) {
/* 1020 */           if (Trace.isOn) {
/* 1021 */             Trace.traceData(this, "BrokerSubEngine available. Closing QueueSubscriptionEngine", null);
/*      */           }
/* 1023 */           this.queueSubStore.close();
/* 1024 */           this.queueSubStore = null;
/*      */         }
/*      */       
/* 1027 */       } catch (JMSException je) {
/* 1028 */         if (Trace.isOn) {
/* 1029 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 10);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1034 */         if (Trace.isOn) {
/* 1035 */           Trace.traceData(this, "error removing queueSubscription", null);
/*      */         }
/* 1037 */         if (Trace.isOn) {
/* 1038 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 13);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1043 */         throw je;
/*      */       } 
/*      */       
/* 1046 */       if (Trace.isOn) {
/* 1047 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", newSubscription, 9);
/*      */       }
/*      */ 
/*      */       
/* 1051 */       return newSubscription;
/*      */     
/*      */     }
/* 1054 */     catch (JMSException je) {
/* 1055 */       if (Trace.isOn) {
/* 1056 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 11);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1061 */       if (queueSubscriptionOpen) {
/*      */         
/*      */         try {
/*      */           
/* 1065 */           if (Trace.isOn) {
/* 1066 */             Trace.traceData(this, "closing opened queueSubscription", null);
/*      */           }
/* 1068 */           this.queueSubStore.closeDurableSubscription(oldSubscription);
/*      */         }
/* 1070 */         catch (JMSException je2) {
/* 1071 */           if (Trace.isOn) {
/* 1072 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je2, 12);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1078 */           if (Trace.isOn) {
/* 1079 */             Trace.traceData(this, "error closing queueSubscription after exception", null);
/*      */           }
/*      */         } 
/*      */       }
/*      */       
/* 1084 */       if (Trace.isOn) {
/* 1085 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 14);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1090 */       throw je;
/*      */     
/*      */     }
/* 1093 */     catch (Exception e) {
/* 1094 */       if (Trace.isOn) {
/* 1095 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", e, 13);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1100 */       JMSException je = ConfigEnvironment.newException("MQJMS1031");
/* 1101 */       je.setLinkedException(e);
/* 1102 */       if (Trace.isOn) {
/* 1103 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)je, 15);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1108 */       throw je;
/*      */     } finally {
/*      */       
/* 1111 */       if (Trace.isOn) {
/* 1112 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1119 */         if (adminQueue != null) {
/* 1120 */           adminQueue.close();
/*      */         }
/*      */       }
/* 1123 */       catch (MQException mqe) {
/* 1124 */         if (Trace.isOn) {
/* 1125 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "openDurableSubscription(MQSession,WMQDestination,String,boolean,boolean,String,String)", (Throwable)mqe, 14);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1131 */         if (Trace.isOn) {
/* 1132 */           Trace.traceData(this, "Ignoring MQException closing admin Queue : " + mqe.getMessage(), null);
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
/*      */   
/*      */   public void closeDurableSubscription(MQSubscription sub) throws JMSException {
/* 1145 */     if (Trace.isOn) {
/* 1146 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "closeDurableSubscription(MQSubscription)", new Object[] { sub });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1153 */     if (sub instanceof MQBrokerSubscription) {
/* 1154 */       this.brokerSubStore.closeDurableSubscription(sub);
/* 1155 */     } else if (sub instanceof MQQueueSubscription) {
/* 1156 */       this.queueSubStore.closeDurableSubscription(sub);
/*      */     } else {
/* 1158 */       JMSException je = ConfigEnvironment.newException("MQJMS3049");
/* 1159 */       if (Trace.isOn) {
/* 1160 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "closeDurableSubscription(MQSubscription)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 1164 */       throw je;
/*      */     } 
/*      */     
/* 1167 */     if (Trace.isOn) {
/* 1168 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "closeDurableSubscription(MQSubscription)");
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
/*      */   public void durableUnsubscribe(MQSession mqts, String subName) throws JMSException {
/* 1180 */     if (Trace.isOn) {
/* 1181 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "durableUnsubscribe(MQSession,String)", new Object[] { mqts, subName });
/*      */     }
/*      */ 
/*      */     
/* 1185 */     MQQueue adminQueue = null;
/*      */ 
/*      */     
/*      */     try {
/* 1189 */       adminQueue = getAdminQueueAccess(mqts, mqts.getFailIfQuiesce());
/*      */       
/* 1191 */       if (this.brokerSubStore != null && checkQueueSubscription(mqts, subName, adminQueue, mqts.getFailIfQuiesce()) == null) {
/* 1192 */         this.brokerSubStore.durableUnsubscribe(mqts, subName);
/*      */       } else {
/*      */         try {
/* 1195 */           adminQueue.close();
/* 1196 */           adminQueue = null;
/*      */         }
/* 1198 */         catch (MQException me) {
/* 1199 */           if (Trace.isOn) {
/* 1200 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "durableUnsubscribe(MQSession,String)", (Throwable)me, 1);
/*      */           }
/*      */ 
/*      */           
/* 1204 */           if (Trace.isOn) {
/* 1205 */             Trace.traceData(this, "error closing adminQueue", null);
/* 1206 */             JMSException je = ConfigEnvironment.newException("MQJMS1032", Integer.valueOf(me.reasonCode));
/*      */             
/* 1208 */             if (Trace.isOn) {
/* 1209 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "durableUnsubscribe(MQSession,String)", (Throwable)je, 1);
/*      */             }
/*      */ 
/*      */             
/* 1213 */             throw je;
/*      */           } 
/*      */         } 
/* 1216 */         this.queueSubStore.durableUnsubscribe(mqts, subName);
/*      */       }
/*      */     
/*      */     } finally {
/*      */       
/* 1221 */       if (Trace.isOn) {
/* 1222 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "durableUnsubscribe(MQSession,String)");
/*      */       }
/*      */ 
/*      */       
/* 1226 */       if (adminQueue != null) {
/*      */         try {
/* 1228 */           adminQueue.close();
/*      */         }
/* 1230 */         catch (MQException mqe) {
/* 1231 */           if (Trace.isOn) {
/* 1232 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "durableUnsubscribe(MQSession,String)", (Throwable)mqe, 2);
/*      */           }
/*      */ 
/*      */           
/* 1236 */           JMSException je2 = new JMSException("MQJMS2000");
/* 1237 */           je2.setLinkedException((Exception)mqe);
/* 1238 */           je2.initCause((Throwable)mqe);
/* 1239 */           if (Trace.isOn) {
/* 1240 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "durableUnsubscribe(MQSession,String)", (Throwable)je2, 2);
/*      */           }
/*      */ 
/*      */           
/* 1244 */           throw je2;
/*      */         } 
/*      */       }
/*      */     } 
/* 1248 */     if (Trace.isOn) {
/* 1249 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "durableUnsubscribe(MQSession,String)");
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
/* 1260 */     if (Trace.isOn) {
/* 1261 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "close()");
/*      */     }
/*      */     
/* 1264 */     if (this.queueSubStore != null) {
/* 1265 */       this.queueSubStore.close();
/*      */     }
/* 1267 */     if (this.brokerSubStore != null) {
/* 1268 */       this.brokerSubStore.close();
/*      */     }
/* 1270 */     if (Trace.isOn) {
/* 1271 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "close()");
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
/*      */   private MQQueueSubscription checkQueueSubscription(MQSession mqs, String subName, MQQueue adminQueue, int fiqBehaviour) throws JMSException {
/* 1293 */     if (Trace.isOn) {
/* 1294 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "checkQueueSubscription(MQSession,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,int)", new Object[] { mqs, subName, adminQueue, 
/*      */             
/* 1296 */             Integer.valueOf(fiqBehaviour) });
/*      */     }
/*      */     
/* 1299 */     MQQueueSubscription testSub = null;
/* 1300 */     String clientID = null;
/* 1301 */     String fullName = null;
/* 1302 */     MQGetMessageOptions gmo = null;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1307 */       clientID = getMQConnection().getClientID();
/*      */ 
/*      */ 
/*      */       
/* 1311 */       if (clientID == null) {
/* 1312 */         JMSException je = ConfigEnvironment.newException("MQJMS3024");
/* 1313 */         if (Trace.isOn) {
/* 1314 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "checkQueueSubscription(MQSession,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,int)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1319 */         throw je;
/*      */       } 
/*      */       
/* 1322 */       fullName = clientID + ":" + subName;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1327 */       gmo = new MQGetMessageOptions();
/*      */       
/* 1329 */       gmo.options = 16;
/*      */ 
/*      */       
/* 1332 */       if (fiqBehaviour == 1) {
/* 1333 */         gmo.options |= 0x2000;
/*      */       }
/*      */       
/* 1336 */       boolean carryOn = true;
/* 1337 */       while (carryOn) {
/*      */         try {
/* 1339 */           MQMessage msg = new MQMessage();
/*      */ 
/*      */           
/* 1342 */           adminQueue.get(msg, gmo);
/*      */           
/* 1344 */           testSub = new MQQueueSubscription(this, mqs, msg);
/*      */           
/* 1346 */           if (testSub.isValid() && testSub.getFullName().equals(fullName)) {
/*      */             
/* 1348 */             carryOn = false; continue;
/*      */           } 
/* 1350 */           gmo.options = 32;
/*      */           
/* 1352 */           if (fiqBehaviour == 1) {
/* 1353 */             gmo.options |= 0x2000;
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 1358 */         catch (MQException e) {
/* 1359 */           if (Trace.isOn) {
/* 1360 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "checkQueueSubscription(MQSession,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,int)", (Throwable)e, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1369 */           testSub = null;
/* 1370 */           carryOn = false;
/*      */         } 
/*      */       } 
/*      */       
/* 1374 */       if (Trace.isOn) {
/* 1375 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "checkQueueSubscription(MQSession,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,int)", testSub);
/*      */       }
/*      */ 
/*      */       
/* 1379 */       return testSub;
/*      */     }
/* 1381 */     catch (JMSException je) {
/* 1382 */       if (Trace.isOn) {
/* 1383 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "checkQueueSubscription(MQSession,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,int)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1388 */       if (Trace.isOn) {
/* 1389 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "checkQueueSubscription(MQSession,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,int)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1394 */       throw je;
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
/*      */   private MQQueue getAdminQueueAccess(MQSession mqs, int fiqBehaviour) throws JMSException {
/* 1411 */     if (Trace.isOn) {
/* 1412 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getAdminQueueAccess(MQSession,int)", new Object[] { mqs, 
/* 1413 */             Integer.valueOf(fiqBehaviour) });
/*      */     }
/* 1415 */     int MAXRETRIES = 20;
/* 1416 */     int MAXWAIT = 5000;
/*      */     
/* 1418 */     MQQueueManager qm = mqs.getQM();
/* 1419 */     MQQueue adminQueue = null;
/*      */     
/* 1421 */     int attempt = 0;
/*      */     
/*      */     while (true) {
/*      */       try {
/* 1425 */         if (Trace.isOn) {
/* 1426 */           Trace.traceData(this, "Try to open the adminQueue", null);
/*      */         }
/*      */         
/* 1429 */         int openOptions = 60;
/*      */ 
/*      */         
/* 1432 */         if (fiqBehaviour == 1) {
/* 1433 */           openOptions |= 0x2000;
/*      */         }
/*      */         
/* 1436 */         adminQueue = qm.accessQueue("SYSTEM.JMS.ADMIN.QUEUE", openOptions);
/*      */ 
/*      */       
/*      */       }
/* 1440 */       catch (MQException e) {
/* 1441 */         if (Trace.isOn) {
/* 1442 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getAdminQueueAccess(MQSession,int)", (Throwable)e, 1);
/*      */         }
/*      */ 
/*      */         
/* 1446 */         if (Trace.isOn) {
/* 1447 */           Trace.traceData(this, "Cannot open adminQueue", null);
/*      */         }
/* 1449 */         switch (e.reasonCode) {
/*      */           
/*      */           case 2042:
/* 1452 */             attempt++;
/* 1453 */             if (attempt < 20) {
/*      */               
/* 1455 */               if (Trace.isOn) {
/* 1456 */                 Trace.traceData(this, "admin queue locked, tried " + attempt + "times", null);
/*      */                 
/* 1458 */                 Trace.traceData(this, "suppressing exception and retrying after wait", null);
/*      */               } 
/*      */               
/*      */               try {
/* 1462 */                 long daisyTime = (long)(100.0D + 5000.0D * Math.random());
/* 1463 */                 if (Trace.isOn) {
/* 1464 */                   Trace.traceData(this, "waiting for " + daisyTime + " milis...", null);
/*      */                 }
/* 1466 */                 Thread.sleep(daisyTime);
/*      */               }
/* 1468 */               catch (InterruptedException ie) {
/* 1469 */                 if (Trace.isOn) {
/* 1470 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getAdminQueueAccess(MQSession,int)", ie, 2);
/*      */                 }
/*      */               } 
/*      */               
/*      */               continue;
/*      */             } 
/*      */             
/* 1477 */             if (Trace.isOn) {
/* 1478 */               Trace.traceData(this, "Tried 20 attempts.", null);
/*      */             }
/*      */             
/* 1481 */             je = ConfigEnvironment.newException("MQJMS2008", "SYSTEM.JMS.ADMIN.QUEUE");
/* 1482 */             je.setLinkedException((Exception)e);
/* 1483 */             if (Trace.isOn) {
/* 1484 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getAdminQueueAccess(MQSession,int)", (Throwable)je, 1);
/*      */             }
/*      */ 
/*      */             
/* 1488 */             throw je;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1493 */         JMSException je = ConfigEnvironment.newException("MQJMS2008", "SYSTEM.JMS.ADMIN.QUEUE");
/* 1494 */         je.setLinkedException((Exception)e);
/* 1495 */         if (Trace.isOn) {
/* 1496 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getAdminQueueAccess(MQSession,int)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */         
/* 1500 */         throw je;
/*      */       } 
/*      */       
/*      */       break;
/*      */     } 
/*      */     
/* 1506 */     if (Trace.isOn) {
/* 1507 */       Trace.traceData(this, "Obtaining the admin queue lock required " + attempt + " retries", null);
/*      */     }
/* 1509 */     if (Trace.isOn) {
/* 1510 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getAdminQueueAccess(MQSession,int)", adminQueue);
/*      */     }
/*      */     
/* 1513 */     return adminQueue;
/*      */   }
/*      */ 
/*      */   
/*      */   byte[] createSessionName(MQSession mqts) throws JMSException {
/* 1518 */     if (Trace.isOn) {
/* 1519 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "createSessionName(MQSession)", new Object[] { mqts });
/*      */     }
/*      */     
/* 1522 */     if (this.brokerSubStore != null) {
/* 1523 */       byte[] traceRet1 = this.brokerSubStore.createSessionName(mqts);
/* 1524 */       if (Trace.isOn) {
/* 1525 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "createSessionName(MQSession)", traceRet1, 1);
/*      */       }
/*      */       
/* 1528 */       return traceRet1;
/*      */     } 
/* 1530 */     if (Trace.isOn) {
/* 1531 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "createSessionName(MQSession)", null, 2);
/*      */     }
/*      */     
/* 1534 */     return null;
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
/*      */   private MQMsg2 sendBrokerMessage(MQSession mqs, MQMsg2 jmsMessage, MQPutMessageOptions pmoP, boolean deferred, int fiqBehaviour) throws JMSException {
/* 1559 */     if (Trace.isOn) {
/* 1560 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "sendBrokerMessage(MQSession,MQMsg2,MQPutMessageOptions,boolean,int)", new Object[] { mqs, jmsMessage, pmoP, 
/*      */             
/* 1562 */             Boolean.valueOf(deferred), Integer.valueOf(fiqBehaviour) });
/*      */     }
/* 1564 */     MQPutMessageOptions pmo = pmoP;
/* 1565 */     MQQueue brokerQ = null;
/*      */     
/*      */     try {
/* 1568 */       BrokerConnectionInfo brk = mqs.getBrk();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1574 */       try { int openOptions = 16;
/*      */         
/* 1576 */         MQQueueManager qm = mqs.getQM();
/* 1577 */         if (pmo == null) {
/* 1578 */           pmo = new MQPutMessageOptions();
/*      */         }
/*      */ 
/*      */         
/* 1582 */         if (fiqBehaviour == 1) {
/* 1583 */           openOptions |= 0x2000;
/* 1584 */           pmo.options |= 0x2000;
/*      */         } 
/*      */         
/* 1587 */         if (deferred) {
/* 1588 */           MQQueueManager spiQm = qm;
/* 1589 */           if (!spiQm.spiSupportsDeferred()) {
/*      */ 
/*      */             
/* 1592 */             if (Trace.isOn) {
/* 1593 */               Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "sendBrokerMessage(MQSession,MQMsg2,MQPutMessageOptions,boolean,int)", null, 1);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 1598 */             return null;
/*      */           } 
/* 1600 */           brokerQ = spiQm.accessQueue(brk.controlQ, openOptions, brk.qmName, null, null);
/* 1601 */           brokerQ.spiDeferredPut(jmsMessage, pmo);
/*      */         } else {
/*      */           
/* 1604 */           brokerQ = mqs.getQM().accessQueue(brk.controlQ, openOptions, brk.qmName, null, null);
/*      */           
/* 1606 */           brokerQ.putMsg2(jmsMessage, pmo);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1632 */         try { brokerQ.close(); }
/*      */         
/* 1634 */         catch (MQException mqe)
/* 1635 */         { if (Trace.isOn) {
/* 1636 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "sendBrokerMessage(MQSession,MQMsg2,MQPutMessageOptions,boolean,int)", (Throwable)mqe, 2);
/*      */           }
/*      */ 
/*      */           
/* 1640 */           JMSException je = ConfigEnvironment.newException("MQJMS2000");
/* 1641 */           je.setLinkedException((Exception)mqe);
/* 1642 */           if (Trace.isOn) {
/* 1643 */             Trace.traceData(this, "Failed to close broker queue.", null);
/*      */           }
/* 1645 */           if (Trace.isOn) {
/* 1646 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "sendBrokerMessage(MQSession,MQMsg2,MQPutMessageOptions,boolean,int)", (Throwable)je, 2);
/*      */           }
/*      */ 
/*      */           
/* 1650 */           throw je; }  } catch (MQException mQException) { if (Trace.isOn)
/*      */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "sendBrokerMessage(MQSession,MQMsg2,MQPutMessageOptions,boolean,int)", (Throwable)mQException, 1);  JMSException je = ConfigEnvironment.newException("MQJMS2008", brk.controlQ); je.setLinkedException((Exception)mQException); if (Trace.isOn)
/*      */           Trace.traceData(this, "Failed to MQPUT broker command.", null);  if (Trace.isOn)
/*      */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "sendBrokerMessage(MQSession,MQMsg2,MQPutMessageOptions,boolean,int)", (Throwable)je, 1);  throw je; }
/* 1654 */       catch (JMSException je)
/* 1655 */       { if (Trace.isOn) {
/* 1656 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "sendBrokerMessage(MQSession,MQMsg2,MQPutMessageOptions,boolean,int)", (Throwable)je, 3);
/*      */         }
/*      */ 
/*      */         
/* 1660 */         if (Trace.isOn) {
/* 1661 */           Trace.traceData(this, "Failed to put message to broker queue.", null);
/*      */         }
/* 1663 */         if (Trace.isOn) {
/* 1664 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "sendBrokerMessage(MQSession,MQMsg2,MQPutMessageOptions,boolean,int)", (Throwable)je, 3);
/*      */         }
/*      */ 
/*      */         
/* 1668 */         throw je; }
/*      */ 
/*      */     
/*      */     }
/* 1672 */     catch (JMSException je) {
/* 1673 */       if (Trace.isOn) {
/* 1674 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "sendBrokerMessage(MQSession,MQMsg2,MQPutMessageOptions,boolean,int)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */       
/* 1678 */       if (Trace.isOn) {
/* 1679 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "sendBrokerMessage(MQSession,MQMsg2,MQPutMessageOptions,boolean,int)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */       
/* 1683 */       throw je;
/*      */     } 
/* 1685 */     if (Trace.isOn) {
/* 1686 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "sendBrokerMessage(MQSession,MQMsg2,MQPutMessageOptions,boolean,int)", jmsMessage, 2);
/*      */     }
/*      */     
/* 1689 */     return jmsMessage;
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
/*      */   private MQBrokerMessage getBrokerResponse(MQSession mqs, MQMsg2 sentMessage) throws JMSException {
/* 1707 */     if (Trace.isOn) {
/* 1708 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2)", new Object[] { mqs, sentMessage });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1717 */     MQBrokerMessage responseMessage = null;
/*      */ 
/*      */     
/* 1720 */     MQMsg2 qResponse = null;
/*      */ 
/*      */     
/* 1723 */     MQQueue replyQ = null;
/*      */ 
/*      */     
/* 1726 */     MQGetMessageOptions brokerResponseGmo = null;
/*      */     
/*      */     try {
/*      */       MQBrokerMessage sentBrokerMessage;
/*      */       
/* 1731 */       if (getMQConnection().getBrkVersion() == 0 || getMQConnection().getBrkVersion() == -1) {
/* 1732 */         responseMessage = new RFH1BrokerMessageImpl();
/* 1733 */         sentBrokerMessage = new RFH1BrokerMessageImpl();
/*      */       } else {
/* 1735 */         responseMessage = new RFH2BrokerMessageImpl();
/* 1736 */         sentBrokerMessage = new RFH2BrokerMessageImpl();
/*      */       } 
/*      */       
/* 1739 */       qResponse = new MQMsg2();
/* 1740 */       qResponse.setCorrelationId(sentMessage.getMessageId());
/*      */ 
/*      */       
/*      */       try {
/* 1744 */         replyQ = mqs.getResponseQueue();
/*      */         
/* 1746 */         brokerResponseGmo = new MQGetMessageOptions();
/* 1747 */         brokerResponseGmo.options = 1;
/* 1748 */         brokerResponseGmo.waitInterval = 120000;
/*      */ 
/*      */         
/* 1751 */         if (getMQConnection().getFailIfQuiesce() == 1) {
/* 1752 */           brokerResponseGmo.options |= 0x2000;
/*      */         }
/*      */         
/* 1755 */         if (Trace.isOn) {
/* 1756 */           Trace.traceData(this, "setting broker timeout to 120000", null);
/*      */         }
/*      */ 
/*      */         
/* 1760 */         replyQ.getMsg2(qResponse, brokerResponseGmo);
/*      */       }
/* 1762 */       catch (MQException e) {
/* 1763 */         if (Trace.isOn) {
/* 1764 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2)", (Throwable)e, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1769 */         String key = "MQJMS5053";
/* 1770 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 1771 */         NoBrokerResponseException je = new NoBrokerResponseException(msg, key);
/* 1772 */         je.setLinkedException((Exception)e);
/*      */         
/* 1774 */         if (Trace.isOn) {
/* 1775 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */         
/* 1779 */         throw je;
/*      */       } 
/*      */       
/* 1782 */       if (Trace.isOn) {
/* 1783 */         Trace.traceData(this, "got broker response", null);
/*      */       }
/* 1785 */       responseMessage.initializeFromMessage(qResponse);
/*      */       
/* 1787 */       sentBrokerMessage.initializeFromMessage(sentMessage);
/*      */ 
/*      */       
/* 1790 */       if (sentBrokerMessage.isOptionSet("MQPSRegOpts", 65536) && 
/* 1791 */         Integer.parseInt(responseMessage.get("MQPSCompCode")) != 2 && 
/* 1792 */         sentBrokerMessage.get("MQPSTopic") == null)
/*      */       {
/*      */ 
/*      */         
/* 1796 */         if (Trace.isOn) {
/* 1797 */           Trace.traceData(this, "Full response didn't work, using workaround and setting fail", null);
/*      */         }
/*      */         
/* 1800 */         responseMessage.set("MQPSCompCode", Integer.toString(2));
/* 1801 */         responseMessage.set("MQPSReason", Integer.toString(2337));
/* 1802 */         responseMessage.set("MQPSReasonText", "MQRC_RFH_PARM_ERROR");
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/* 1808 */     catch (JMSException je) {
/* 1809 */       if (Trace.isOn) {
/* 1810 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */       
/* 1814 */       if (Trace.isOn) {
/* 1815 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */       
/* 1819 */       throw je;
/*      */     } 
/*      */     
/* 1822 */     if (Trace.isOn) {
/* 1823 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getBrokerResponse(MQSession,MQMsg2)", responseMessage);
/*      */     }
/*      */     
/* 1826 */     return responseMessage;
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
/*      */   private void removeQueueSubscription(MQSession mqs, MQQueueSubscription oldSubscription) throws JMSException {
/* 1840 */     if (Trace.isOn) {
/* 1841 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "removeQueueSubscription(MQSession,MQQueueSubscription)", new Object[] { mqs, oldSubscription });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1847 */     MQQueue adminQueue = null;
/* 1848 */     MQQueue subscriberQueue = null;
/*      */     
/* 1850 */     MQQueueSubscription currentEntry = null;
/*      */     
/* 1852 */     byte[] subscriberId = null;
/* 1853 */     String key = null;
/* 1854 */     MQGetMessageOptions gmo = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1861 */       int fiqBehaviour = 1;
/* 1862 */       if (oldSubscription.getMQTopic() != null) {
/*      */         
/* 1864 */         fiqBehaviour = oldSubscription.getMQTopic().getIntProperty("failIfQuiesce");
/*      */       }
/*      */       else {
/*      */         
/* 1868 */         fiqBehaviour = mqs.getFailIfQuiesce();
/*      */       } 
/*      */       
/* 1871 */       adminQueue = getAdminQueueAccess(mqs, fiqBehaviour);
/*      */       
/* 1873 */       subscriberId = oldSubscription.getCorrelationId();
/* 1874 */       key = oldSubscription.getSubName();
/* 1875 */       gmo = new MQGetMessageOptions();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1880 */       if (subscriberId == null) {
/* 1881 */         gmo.options = 16;
/*      */       }
/*      */       
/* 1884 */       boolean carryOn = true;
/*      */ 
/*      */       
/* 1887 */       if (getMQConnection().getFailIfQuiesce() == 1) {
/* 1888 */         gmo.options |= 0x2000;
/*      */       }
/*      */       
/* 1891 */       while (carryOn) {
/*      */         try {
/* 1893 */           MQMessage msg = new MQMessage();
/*      */ 
/*      */           
/* 1896 */           if (subscriberId != null) {
/* 1897 */             msg.messageId = subscriberId;
/* 1898 */             gmo.matchOptions |= 0x1;
/*      */           } 
/*      */           
/* 1901 */           adminQueue.get(msg, gmo);
/*      */           
/* 1903 */           currentEntry = new MQQueueSubscription(this, oldSubscription.getMQSession(), msg);
/*      */ 
/*      */           
/* 1906 */           if (subscriberId != null) {
/*      */             
/* 1908 */             carryOn = false;
/*      */             continue;
/*      */           } 
/* 1911 */           if (currentEntry.isValid() && currentEntry.getSubName().equals(key)) {
/*      */ 
/*      */             
/* 1914 */             gmo.options = 256;
/* 1915 */             MQMessage delmsg = new MQMessage();
/* 1916 */             adminQueue.get(delmsg, gmo);
/* 1917 */             carryOn = false;
/*      */             
/*      */             continue;
/*      */           } 
/* 1921 */           gmo.options = 32;
/*      */ 
/*      */           
/* 1924 */           if (getMQConnection().getFailIfQuiesce() == 1) {
/* 1925 */             gmo.options |= 0x2000;
/*      */ 
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 1931 */         catch (MQException e) {
/* 1932 */           if (Trace.isOn) {
/* 1933 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "removeQueueSubscription(MQSession,MQQueueSubscription)", (Throwable)e, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1942 */           JMSException je = ConfigEnvironment.newException("MQJMS3013");
/* 1943 */           je.setLinkedException((Exception)e);
/*      */           
/* 1945 */           if (Trace.isOn) {
/* 1946 */             Trace.traceData(this, "In removeSubscriptionEntry() - cannot remove msg!", null);
/*      */           }
/* 1948 */           if (Trace.isOn) {
/* 1949 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "removeQueueSubscription(MQSession,MQQueueSubscription)", (Throwable)je, 1);
/*      */           }
/*      */ 
/*      */           
/* 1953 */           throw je;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 1959 */         if (oldSubscription.getSubscriberQueue() != null)
/*      */         {
/* 1961 */           subscriberQueue = oldSubscription.getSubscriberQueue();
/* 1962 */           subscriberQueue.close();
/* 1963 */           subscriberQueue = null;
/*      */         }
/*      */       
/*      */       }
/* 1967 */       catch (MQException me) {
/* 1968 */         if (Trace.isOn) {
/* 1969 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "removeQueueSubscription(MQSession,MQQueueSubscription)", (Throwable)me, 2);
/*      */         }
/*      */ 
/*      */         
/* 1973 */         JMSException je2 = ConfigEnvironment.newException("MQJMS1032", Integer.valueOf(me.reasonCode));
/* 1974 */         je2.setLinkedException((Exception)me);
/*      */         
/* 1976 */         if (Trace.isOn) {
/* 1977 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "removeQueueSubscription(MQSession,MQQueueSubscription)", (Throwable)je2, 2);
/*      */         }
/*      */ 
/*      */         
/* 1981 */         throw je2;
/*      */       }
/*      */     
/*      */     }
/* 1985 */     catch (JMSException je) {
/* 1986 */       if (Trace.isOn) {
/* 1987 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "removeQueueSubscription(MQSession,MQQueueSubscription)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */       
/* 1991 */       if (Trace.isOn) {
/* 1992 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "removeQueueSubscription(MQSession,MQQueueSubscription)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */       
/* 1996 */       throw je;
/*      */     } finally {
/*      */       
/* 1999 */       if (Trace.isOn) {
/* 2000 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "removeQueueSubscription(MQSession,MQQueueSubscription)");
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/* 2005 */         if (adminQueue != null) {
/* 2006 */           adminQueue.close();
/*      */         }
/*      */       }
/* 2009 */       catch (MQException me) {
/* 2010 */         if (Trace.isOn) {
/* 2011 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "removeQueueSubscription(MQSession,MQQueueSubscription)", (Throwable)me, 4);
/*      */         }
/*      */ 
/*      */         
/* 2015 */         if (Trace.isOn) {
/* 2016 */           Trace.traceData(this, "error closing adminQueue - tracing exception only", null);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2022 */     if (Trace.isOn) {
/* 2023 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "removeQueueSubscription(MQSession,MQQueueSubscription)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQBrokerSubscription getMigratedSubscription(MQQueueSubscription oldSubscription, MQSession mqs, String subName) throws JMSException {
/* 2031 */     if (Trace.isOn) {
/* 2032 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", new Object[] { oldSubscription, mqs, subName });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2038 */     String fullName = null;
/* 2039 */     String escapedQMgrName = null;
/* 2040 */     String escapedClientID = null;
/* 2041 */     String escapedSubName = null;
/* 2042 */     String escapedSelector = null;
/* 2043 */     String userSubData = null;
/* 2044 */     MQBrokerSubscription newSubscription = null;
/*      */     
/* 2046 */     MQBrokerMessage brokerMessage = null;
/* 2047 */     MQBrokerMessage brokerResponse = null;
/* 2048 */     int regOpts = 0;
/* 2049 */     MQMsg2 jmsMessage = null;
/* 2050 */     byte[] deferredMsgId = null;
/*      */     
/* 2052 */     boolean cancelDeferred = false;
/* 2053 */     String selector = null;
/* 2054 */     MQQueue subscriberQueue = null;
/*      */     
/* 2056 */     BrokerConnectionInfo brk = null;
/* 2057 */     int rc = 0;
/*      */     
/* 2059 */     String filter = null;
/*      */     
/*      */     try {
/*      */       int cc;
/*      */       
/*      */       JMSException traceRet1;
/* 2065 */       brk = mqs.getBrk();
/*      */ 
/*      */ 
/*      */       
/* 2069 */       escapedQMgrName = WMQCommonUtils.escapeString(oldSubscription.getQmgrName());
/* 2070 */       escapedClientID = WMQCommonUtils.escapeString(getMQConnection().getClientID());
/* 2071 */       escapedSubName = WMQCommonUtils.escapeString(subName);
/* 2072 */       fullName = "JMS:" + escapedQMgrName + ":" + escapedClientID + ":" + escapedSubName;
/* 2073 */       if (Trace.isOn) {
/* 2074 */         Trace.traceData(this, "Fullname = '" + fullName + "'", null);
/*      */       }
/*      */ 
/*      */       
/* 2078 */       selector = oldSubscription.getSelector();
/* 2079 */       if (selector != null && !selector.trim().equals("")) {
/* 2080 */         escapedSelector = WMQCommonUtils.escapeString(selector);
/* 2081 */         userSubData = "sel=\"" + escapedSelector + "\"";
/*      */       } else {
/* 2083 */         userSubData = "";
/*      */       } 
/*      */ 
/*      */       
/* 2087 */       int fiqBehaviour = 1;
/* 2088 */       if (oldSubscription.getMQTopic() != null) {
/*      */         
/* 2090 */         fiqBehaviour = oldSubscription.getMQTopic().getIntProperty("failIfQuiesce");
/*      */       }
/*      */       else {
/*      */         
/* 2094 */         fiqBehaviour = mqs.getFailIfQuiesce();
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*      */         try {
/* 2100 */           regOpts = 1572864;
/*      */           
/* 2102 */           if (oldSubscription.isSharedQueue()) {
/* 2103 */             regOpts++;
/*      */           }
/*      */           
/* 2106 */           if (getMQConnection().getBrkVersion() == 0 || getMQConnection().getBrkVersion() == -1) {
/* 2107 */             brokerMessage = new RFH1BrokerMessageImpl();
/*      */           } else {
/* 2109 */             brokerMessage = new RFH2BrokerMessageImpl();
/*      */           } 
/* 2111 */           brokerMessage.set("MQPSCommand", "DeregSub");
/* 2112 */           brokerMessage.set("MQPSRegOpts", regOpts);
/* 2113 */           brokerMessage.set("MQPSSubName", fullName);
/* 2114 */           brokerMessage.set("MQPSSubIdentity", Utils.bytesToHex(mqs.getSessionName()));
/* 2115 */           brokerMessage.set("MQPSQMgrName", oldSubscription.getQmgrName());
/* 2116 */           brokerMessage.set("MQPSQName", oldSubscription.getQueueName());
/* 2117 */           brokerMessage.set("MQPSStreamName", brk.streamQ);
/* 2118 */           brokerMessage.set("MQPSTopic", oldSubscription.getTopic());
/*      */ 
/*      */           
/* 2121 */           filter = oldSubscription.getFilter();
/* 2122 */           if (filter != null) {
/* 2123 */             if (Trace.isOn) {
/* 2124 */               Trace.traceData(this, "setting filter to " + filter, null);
/*      */             }
/* 2126 */             brokerMessage.set("MQPSFilter", filter);
/*      */           }
/*      */         
/*      */         }
/* 2130 */         catch (JMSException je) {
/* 2131 */           if (Trace.isOn) {
/* 2132 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 1);
/*      */           }
/*      */ 
/*      */           
/* 2136 */           if (Trace.isOn) {
/* 2137 */             Trace.traceData(this, "error composing update MQBrokerMessage", null);
/*      */           }
/*      */           
/* 2140 */           if (Trace.isOn) {
/* 2141 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 1);
/*      */           }
/*      */ 
/*      */           
/* 2145 */           throw je;
/*      */         } 
/*      */ 
/*      */         
/*      */         try {
/* 2150 */           jmsMessage = new MQMsg2();
/* 2151 */           brokerMessage.writeToMessage(jmsMessage);
/* 2152 */           jmsMessage.setCorrelationId(oldSubscription.getCorrelationId());
/* 2153 */           jmsMessage.setReplyToQueueManagerName("");
/* 2154 */           jmsMessage.setReplyToQueueName("SYSTEM.JMS.REPORT.QUEUE");
/* 2155 */           jmsMessage.setFormat(brokerMessage.getHeaderFormat());
/* 2156 */           jmsMessage.setMessageType(8);
/*      */         }
/* 2158 */         catch (Exception e) {
/* 2159 */           JMSException je; if (Trace.isOn) {
/* 2160 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", e, 2);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 2165 */           if (e instanceof JMSException) {
/* 2166 */             je = (JMSException)e;
/*      */           } else {
/* 2168 */             je = ConfigEnvironment.newException(e.getMessage());
/* 2169 */             je.setLinkedException(e);
/*      */           } 
/*      */           
/* 2172 */           if (Trace.isOn) {
/* 2173 */             Trace.traceData(this, "error building deferred deregister MQMsg2", null);
/*      */           }
/* 2175 */           if (Trace.isOn) {
/* 2176 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 2);
/*      */           }
/*      */ 
/*      */           
/* 2180 */           throw je;
/*      */         } 
/*      */         
/*      */         try {
/* 2184 */           jmsMessage = sendBrokerMessage(mqs, jmsMessage, (MQPutMessageOptions)null, true, fiqBehaviour);
/* 2185 */           cancelDeferred = true;
/*      */         }
/* 2187 */         catch (JMSException je) {
/* 2188 */           if (Trace.isOn) {
/* 2189 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 3);
/*      */           }
/*      */ 
/*      */           
/* 2193 */           if (Trace.isOn) {
/* 2194 */             Trace.traceData(this, "error sending deferred broker message", null);
/*      */           }
/* 2196 */           if (Trace.isOn) {
/* 2197 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 3);
/*      */           }
/*      */ 
/*      */           
/* 2201 */           throw je;
/*      */         }
/*      */       
/*      */       }
/* 2205 */       catch (JMSException je) {
/* 2206 */         if (Trace.isOn) {
/* 2207 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 4);
/*      */         }
/*      */ 
/*      */         
/* 2211 */         if (Trace.isOn) {
/* 2212 */           Trace.traceData(this, "error attempting to put deferred deregister", null);
/*      */         }
/* 2214 */         if (Trace.isOn) {
/* 2215 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 4);
/*      */         }
/*      */ 
/*      */         
/* 2219 */         throw je;
/*      */       } 
/*      */       
/* 2222 */       deferredMsgId = jmsMessage.getMessageId();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*      */         try {
/* 2229 */           regOpts = 1130496;
/* 2230 */           if (this.WSClone == 1) {
/* 2231 */             regOpts += 131072;
/*      */           } else {
/* 2233 */             regOpts += 262144;
/*      */           } 
/*      */ 
/*      */           
/* 2237 */           if (mqs.acknowledgeMode == 3 && getMQConnection().getBrkOptLevel() > 0) {
/* 2238 */             regOpts += 512;
/*      */           }
/*      */           
/* 2241 */           if (oldSubscription.isSharedQueue()) {
/* 2242 */             regOpts++;
/*      */           }
/*      */           
/* 2245 */           if (getMQConnection().getBrkVersion() == 0 || getMQConnection().getBrkVersion() == -1) {
/* 2246 */             brokerMessage = new RFH1BrokerMessageImpl();
/*      */           } else {
/* 2248 */             brokerMessage = new RFH2BrokerMessageImpl();
/*      */           } 
/* 2250 */           brokerMessage.set("MQPSCommand", "RegSub");
/* 2251 */           brokerMessage.set("MQPSRegOpts", regOpts);
/* 2252 */           brokerMessage.set("MQPSSubName", fullName);
/* 2253 */           brokerMessage.set("MQPSSubIdentity", Utils.bytesToHex(mqs.getSessionName()));
/* 2254 */           brokerMessage.set("MQPSQMgrName", oldSubscription.getQmgrName());
/* 2255 */           brokerMessage.set("MQPSQName", oldSubscription.getQueueName());
/* 2256 */           brokerMessage.set("MQPSStreamName", brk.streamQ);
/* 2257 */           brokerMessage.set("MQPSTopic", oldSubscription.getTopic());
/* 2258 */           brokerMessage.set("MQPSSubUserData", userSubData);
/*      */ 
/*      */           
/* 2261 */           filter = oldSubscription.getFilter();
/* 2262 */           if (filter != null) {
/* 2263 */             if (Trace.isOn) {
/* 2264 */               Trace.traceData(this, "setting filter to " + filter, null);
/*      */             }
/* 2266 */             brokerMessage.set("MQPSFilter", filter);
/*      */           }
/*      */         
/*      */         }
/* 2270 */         catch (JMSException je) {
/* 2271 */           if (Trace.isOn) {
/* 2272 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 5);
/*      */           }
/*      */ 
/*      */           
/* 2276 */           if (Trace.isOn) {
/* 2277 */             Trace.traceData(this, "error composing update MQBrokerMessage", null);
/*      */           }
/*      */           
/* 2280 */           if (Trace.isOn) {
/* 2281 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 5);
/*      */           }
/*      */ 
/*      */           
/* 2285 */           throw je;
/*      */         } 
/*      */ 
/*      */         
/*      */         try {
/* 2290 */           jmsMessage = new MQMsg2();
/* 2291 */           brokerMessage.writeToMessage(jmsMessage);
/* 2292 */           jmsMessage.setCorrelationId(oldSubscription.getCorrelationId());
/* 2293 */           jmsMessage.setReplyToQueueManagerName("");
/* 2294 */           jmsMessage.setReplyToQueueName("SYSTEM.JMS.REPORT.QUEUE");
/* 2295 */           jmsMessage.setFormat(brokerMessage.getHeaderFormat());
/* 2296 */           jmsMessage.setMessageType(1);
/*      */         }
/* 2298 */         catch (Exception e) {
/* 2299 */           JMSException je; if (Trace.isOn) {
/* 2300 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", e, 6);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 2305 */           if (e instanceof JMSException) {
/* 2306 */             je = (JMSException)e;
/*      */           } else {
/* 2308 */             je = ConfigEnvironment.newException(e.getMessage());
/* 2309 */             je.setLinkedException(e);
/*      */           } 
/*      */           
/* 2312 */           if (Trace.isOn) {
/* 2313 */             Trace.traceData(this, "error building broker update MQMsg2", null);
/*      */           }
/* 2315 */           if (Trace.isOn) {
/* 2316 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 6);
/*      */           }
/*      */ 
/*      */           
/* 2320 */           throw je;
/*      */         } 
/*      */         
/*      */         try {
/* 2324 */           jmsMessage = sendBrokerMessage(mqs, jmsMessage, (MQPutMessageOptions)null, false, fiqBehaviour);
/*      */         }
/* 2326 */         catch (JMSException je) {
/* 2327 */           if (Trace.isOn) {
/* 2328 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 7);
/*      */           }
/*      */ 
/*      */           
/* 2332 */           if (Trace.isOn) {
/* 2333 */             Trace.traceData(this, "error sending update subscription message to broker", null);
/*      */           }
/* 2335 */           if (Trace.isOn) {
/* 2336 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 7);
/*      */           }
/*      */ 
/*      */           
/* 2340 */           throw je;
/*      */         }
/*      */       
/*      */       }
/* 2344 */       catch (JMSException je) {
/* 2345 */         if (Trace.isOn) {
/* 2346 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 8);
/*      */         }
/*      */ 
/*      */         
/* 2350 */         if (Trace.isOn) {
/* 2351 */           Trace.traceData(this, "error sending broker update message", null);
/*      */         }
/* 2353 */         if (Trace.isOn) {
/* 2354 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 8);
/*      */         }
/*      */ 
/*      */         
/* 2358 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 2363 */         if (getMQConnection().getBrkVersion() == 0 || getMQConnection().getBrkVersion() == -1) {
/* 2364 */           brokerResponse = new RFH1BrokerMessageImpl();
/* 2365 */           brokerResponse = getBrokerResponse(mqs, jmsMessage);
/*      */         } else {
/* 2367 */           brokerResponse = new RFH2BrokerMessageImpl();
/* 2368 */           brokerResponse = getBrokerResponse(mqs, jmsMessage);
/*      */         } 
/* 2370 */         if (Trace.isOn) {
/* 2371 */           Trace.traceData(this, "got update response from broker", null);
/*      */         }
/*      */         
/* 2374 */         cc = Integer.parseInt(brokerResponse.get("MQPSCompCode"));
/* 2375 */         rc = Integer.parseInt(brokerResponse.get("MQPSReason"));
/*      */       }
/* 2377 */       catch (JMSException je) {
/* 2378 */         if (Trace.isOn) {
/* 2379 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 9);
/*      */         }
/*      */ 
/*      */         
/* 2383 */         if (Trace.isOn) {
/* 2384 */           Trace.traceData(this, "error getting response from broker update message", null);
/*      */         }
/* 2386 */         if (Trace.isOn) {
/* 2387 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 9);
/*      */         }
/*      */ 
/*      */         
/* 2391 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2397 */       if (Trace.isOn) {
/* 2398 */         Trace.traceData(this, "requested full Response. Did we get it?", null);
/*      */       }
/* 2400 */       if ((brokerResponse.get("MQPSTopic") == null || brokerResponse.get("MQPSTopic").equals("")) && cc != 2) {
/*      */ 
/*      */         
/* 2403 */         if (Trace.isOn) {
/* 2404 */           Trace.traceData(this, "No full Response. This should be a failure", null);
/*      */         }
/* 2406 */         cc = 2;
/* 2407 */         rc = 2337;
/*      */       } 
/*      */       
/* 2410 */       switch (cc) {
/*      */         case 0:
/*      */           break;
/*      */         case 1:
/*      */         case 2:
/* 2415 */           if (rc == 3083 || rc == 2337) {
/*      */ 
/*      */             
/* 2418 */             this.useBrokerSubStore = 0;
/*      */             try {
/* 2420 */               this.brokerSubStore.close();
/*      */             }
/* 2422 */             catch (Exception e2) {
/* 2423 */               if (Trace.isOn) {
/* 2424 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", e2, 10);
/*      */               }
/*      */ 
/*      */               
/* 2428 */               if (Trace.isOn) {
/* 2429 */                 Trace.traceData(this, "error attempting to close brokerSubStore", null);
/*      */               }
/*      */             } 
/* 2432 */             this.brokerSubStore = null;
/*      */           } 
/*      */         
/*      */         default:
/* 2436 */           if (Trace.isOn) {
/* 2437 */             Trace.traceData(this, "something went wrong updating the subscription", null);
/* 2438 */             Trace.traceData(this, brokerResponse.get("MQPSReasonText"), null);
/*      */           } 
/* 2440 */           traceRet1 = new JMSException(brokerResponse.get("MQPSReasonText"));
/* 2441 */           if (Trace.isOn) {
/* 2442 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)traceRet1, 10);
/*      */           }
/*      */ 
/*      */           
/* 2446 */           throw traceRet1;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 2451 */         String qMgrName = brokerResponse.get("MQPSQMgrName").trim();
/* 2452 */         String queueName = brokerResponse.get("MQPSQName").trim();
/* 2453 */         String correlId = brokerResponse.get("MQPSCorrelId").trim();
/*      */ 
/*      */         
/* 2456 */         newSubscription = new MQBrokerSubscription(this, mqs, true, oldSubscription.isSharedQueue(), qMgrName, getMQConnection().getClientID(), subName, oldSubscription.getMQTopic(), oldSubscription.getSelector(), oldSubscription.getNoLocal(), queueName, null, Utils.hexToBytes(correlId));
/* 2457 */         subscriberQueue = openSubscriberQueue(mqs, queueName, oldSubscription.isSharedQueue(), true, fiqBehaviour);
/* 2458 */         newSubscription.setSubscriberQueue(subscriberQueue);
/* 2459 */         newSubscription.setDeferredMsgId(deferredMsgId);
/*      */       }
/* 2461 */       catch (JMSException je) {
/* 2462 */         if (Trace.isOn) {
/* 2463 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 11);
/*      */         }
/*      */ 
/*      */         
/* 2467 */         if (Trace.isOn) {
/* 2468 */           Trace.traceData(this, "error creating new MQBrokerSubscription object from migrated subscription", null);
/*      */         }
/* 2470 */         if (Trace.isOn) {
/* 2471 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 11);
/*      */         }
/*      */ 
/*      */         
/* 2475 */         throw je;
/*      */       } 
/*      */       
/* 2478 */       if (Trace.isOn) {
/* 2479 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", newSubscription);
/*      */       }
/*      */       
/* 2482 */       return newSubscription;
/*      */     }
/* 2484 */     catch (JMSException je) {
/* 2485 */       if (Trace.isOn) {
/* 2486 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 12);
/*      */       }
/*      */ 
/*      */       
/* 2490 */       if (Trace.isOn) {
/* 2491 */         Trace.traceData(this, "error encountered while trying to migrate subscription.", null);
/*      */       }
/*      */       
/* 2494 */       if (cancelDeferred) {
/* 2495 */         if (Trace.isOn) {
/* 2496 */           Trace.traceData(this, "cancelling deferred message", null);
/*      */         }
/*      */         
/*      */         try {
/* 2500 */           MQQueueManager qm = mqs.getQM();
/* 2501 */           if (qm == null) {
/* 2502 */             JMSException je2 = new JMSException("MQJMS3047");
/* 2503 */             if (Trace.isOn) {
/* 2504 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je2, 12);
/*      */             }
/*      */ 
/*      */             
/* 2508 */             throw je2;
/*      */           } 
/* 2510 */           qm.spiCancelDeferred(deferredMsgId);
/*      */         }
/* 2512 */         catch (Exception e) {
/* 2513 */           if (Trace.isOn) {
/* 2514 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", e, 13);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 2519 */           if (e instanceof JMSException) {
/* 2520 */             JMSException je3 = (JMSException)e;
/*      */           } else {
/* 2522 */             JMSException je3 = new JMSException(e.getMessage());
/* 2523 */             je3.setLinkedException(e);
/* 2524 */             je3.initCause(e);
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 2529 */           if (Trace.isOn) {
/* 2530 */             Trace.traceData(this, "error cancelling deferred deregister message.", null);
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2536 */       if (Trace.isOn) {
/* 2537 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMigrateSubscriptionEngine", "getMigratedSubscription(MQQueueSubscription,MQSession,String)", (Throwable)je, 13);
/*      */       }
/*      */ 
/*      */       
/* 2541 */       throw je;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQMigrateSubscriptionEngine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */