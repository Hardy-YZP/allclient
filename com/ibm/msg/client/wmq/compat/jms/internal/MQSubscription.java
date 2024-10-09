/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.disthub2.impl.matching.selector.MatchParser;
/*     */ import com.ibm.disthub2.impl.matching.selector.Resolver;
/*     */ import com.ibm.disthub2.impl.matching.selector.Selector;
/*     */ import com.ibm.disthub2.impl.matching.selector.Transformer;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
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
/*     */ abstract class MQSubscription
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQSubscription.java";
/*     */   private MQSubscriptionEngine subscriptionEngine;
/*     */   private boolean durable;
/*     */   private boolean sharedQueue;
/*     */   private String qmgrName;
/*     */   private String clientId;
/*     */   private String subName;
/*     */   private String topic;
/*     */   private WMQDestination mqTopic;
/*     */   private String selector;
/*     */   
/*     */   static {
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQSubscription.java");
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
/*  86 */   private String filter = null;
/*     */   
/*     */   private boolean noLocal;
/*     */   
/*     */   private String queueName;
/*     */   
/*     */   private byte[] correlationId;
/*     */   
/*     */   private MQSession session;
/*     */   
/*     */   private MQQueue subscriberQueue;
/*     */   private boolean closed = false;
/*     */   
/*     */   protected MQSubscription(MQSubscriptionEngine subscriptionEngine) {
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "<init>(MQSubscriptionEngine)", new Object[] { subscriptionEngine });
/*     */     }
/*     */     
/* 104 */     this.subscriptionEngine = subscriptionEngine;
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "<init>(MQSubscriptionEngine)");
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
/*     */   protected MQSubscription(MQSubscriptionEngine subscriptionEngine, MQSession session, boolean durable, boolean sharedQueue, String qmgrName, String clientId, String subName, WMQDestination topic, String selector, boolean noLocal, String queueName, MQQueue subscriberQueue, byte[] correlationId) {
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "<init>(MQSubscriptionEngine,MQSession,boolean,boolean,String,String,String,WMQDestination,String,boolean,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,byte [ ])", new Object[] { subscriptionEngine, session, 
/*     */             
/* 134 */             Boolean.valueOf(durable), 
/* 135 */             Boolean.valueOf(sharedQueue), qmgrName, clientId, subName, topic, selector, 
/* 136 */             Boolean.valueOf(noLocal), queueName, subscriberQueue, correlationId });
/*     */     }
/*     */     
/* 139 */     this.subscriptionEngine = subscriptionEngine;
/* 140 */     this.session = session;
/* 141 */     this.durable = durable;
/* 142 */     this.sharedQueue = sharedQueue;
/* 143 */     this.qmgrName = qmgrName;
/* 144 */     this.clientId = clientId;
/* 145 */     this.subName = subName;
/* 146 */     if (topic != null) {
/* 147 */       this.topic = topic.getName();
/*     */     } else {
/* 149 */       this.topic = null;
/*     */     } 
/* 151 */     this.mqTopic = topic;
/* 152 */     this.noLocal = noLocal;
/* 153 */     this.queueName = queueName;
/* 154 */     this.subscriberQueue = subscriberQueue;
/*     */     
/* 156 */     if (correlationId == null) {
/* 157 */       this.correlationId = null;
/*     */     } else {
/* 159 */       this.correlationId = new byte[correlationId.length];
/* 160 */       System.arraycopy(correlationId, 0, this.correlationId, 0, correlationId.length);
/*     */     } 
/*     */ 
/*     */     
/* 164 */     setSelector(selector);
/*     */     
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "<init>(MQSubscriptionEngine,MQSession,boolean,boolean,String,String,String,WMQDestination,String,boolean,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,byte [ ])");
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
/*     */   protected final MQSession getMQSession() {
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "getMQSession()", "getter", this.session);
/*     */     }
/*     */     
/* 185 */     return this.session;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setDurable(boolean durable) {
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "setDurable(boolean)", "setter", 
/* 195 */           Boolean.valueOf(durable));
/*     */     }
/* 197 */     this.durable = durable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isDurable() {
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "isDurable()", "getter", 
/* 207 */           Boolean.valueOf(this.durable));
/*     */     }
/* 209 */     return this.durable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setSharedQueue(boolean sharedQueue) {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "setSharedQueue(boolean)", "setter", 
/* 221 */           Boolean.valueOf(sharedQueue));
/*     */     }
/* 223 */     this.sharedQueue = sharedQueue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSharedQueue() {
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "isSharedQueue()", "getter", 
/* 235 */           Boolean.valueOf(this.sharedQueue));
/*     */     }
/* 237 */     return this.sharedQueue;
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
/*     */   protected void setQmgrName(String qmgrName) {
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "setQmgrName(String)", "setter", qmgrName);
/*     */     }
/*     */     
/* 253 */     this.qmgrName = qmgrName;
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
/*     */   protected String getQmgrName() {
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "getQmgrName()", "getter", this.qmgrName);
/*     */     }
/*     */     
/* 270 */     return this.qmgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final WMQDestination getMQTopic() {
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "getMQTopic()", "getter", this.mqTopic);
/*     */     }
/*     */     
/* 282 */     return this.mqTopic;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setMQTopic(WMQDestination newMQTopic) {
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "setMQTopic(WMQDestination)", "setter", newMQTopic);
/*     */     }
/*     */     
/* 294 */     this.mqTopic = newMQTopic;
/* 295 */     if (this.topic == null && Trace.isOn) {
/* 296 */       Trace.traceData(this, "New MQTopic set in subscription. TopicName still null", null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setClientId(String clientId) {
/* 306 */     if (Trace.isOn) {
/* 307 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "setClientId(String)", "setter", clientId);
/*     */     }
/*     */     
/* 310 */     this.clientId = clientId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getClientId() {
/* 319 */     if (Trace.isOn) {
/* 320 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "getClientId()", "getter", this.clientId);
/*     */     }
/*     */     
/* 323 */     return this.clientId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setSubName(String subName) {
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "setSubName(String)", "setter", subName);
/*     */     }
/*     */     
/* 335 */     this.subName = subName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getSubName() {
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "getSubName()", "getter", this.subName);
/*     */     }
/*     */     
/* 347 */     return this.subName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setTopic(String topic) {
/* 355 */     if (Trace.isOn) {
/* 356 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "setTopic(String)", "setter", topic);
/*     */     }
/*     */     
/* 359 */     this.topic = topic;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getTopic() {
/* 367 */     if (Trace.isOn) {
/* 368 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "getTopic()", "getter", this.topic);
/*     */     }
/*     */     
/* 371 */     return this.topic;
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
/*     */   protected void setSelector(String selector) {
/* 383 */     if (Trace.isOn) {
/* 384 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "setSelector(String)", "setter", selector);
/*     */     }
/*     */     
/* 387 */     this.selector = selector;
/*     */ 
/*     */     
/* 390 */     if (selector == null) {
/* 391 */       this.filter = null;
/*     */     } else {
/*     */       int connMsgSelection;
/*     */ 
/*     */       
/*     */       try {
/* 397 */         connMsgSelection = this.session.getConnectionMsgSelection();
/*     */       }
/* 399 */       catch (JMSException je) {
/* 400 */         if (Trace.isOn) {
/* 401 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "setSelector(String)", (Throwable)je, 1);
/*     */         }
/*     */ 
/*     */         
/* 405 */         RuntimeException traceRet1 = new RuntimeException(ConfigEnvironment.getErrorMessage("MQJMS1016"));
/* 406 */         if (Trace.isOn) {
/* 407 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "setSelector(String)", traceRet1);
/*     */         }
/*     */         
/* 410 */         throw traceRet1;
/*     */       } 
/* 412 */       if (connMsgSelection == 1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 418 */         String rawSelectorProperty = "com.ibm.mq.jms.pubsub.rawBrokerSelectors";
/* 419 */         PropertyStore.register(rawSelectorProperty, "");
/* 420 */         String rawSelector = PropertyStore.getStringProperty(rawSelectorProperty);
/*     */         
/* 422 */         boolean useRawSelector = false;
/* 423 */         if (rawSelector.equals("false")) {
/*     */ 
/*     */           
/* 426 */           if (Trace.isOn) {
/* 427 */             Trace.data(this, "Raw selectors have been disabled.", null);
/*     */           }
/* 429 */           useRawSelector = false;
/* 430 */         } else if (rawSelector.equals("")) {
/*     */ 
/*     */           
/*     */           try {
/*     */ 
/*     */             
/* 436 */             if (Trace.isOn) {
/* 437 */               Trace.data(this, "Checking the PSMODE property to determine whether to use raw selectors", null);
/*     */             }
/*     */             
/* 440 */             int psMode = this.subscriptionEngine.connection.getIntProperty("MQIA_PUBSUB_MODE");
/*     */             
/* 442 */             if (psMode == 2)
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 449 */               if (Trace.isOn) {
/* 450 */                 Trace.data(this, "PSMode=enabled, connection will be to MQ internal pub/sub engine so using raw selectors", null);
/*     */               }
/* 452 */               useRawSelector = true;
/*     */ 
/*     */             
/*     */             }
/*     */ 
/*     */           
/*     */           }
/* 459 */           catch (JMSException je) {
/* 460 */             if (Trace.isOn) {
/* 461 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "setSelector(String)", (Throwable)je, 2);
/*     */             }
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 468 */           if (Trace.isOn) {
/* 469 */             Trace.data(this, "Raw selectors have been enabled.", null);
/*     */           }
/* 471 */           useRawSelector = true;
/*     */         } 
/*     */         
/* 474 */         if (useRawSelector) {
/* 475 */           if (Trace.isOn) {
/* 476 */             Trace.traceData(this, "Passing unparsed raw selector to the broker - BYPASSING VALIDATION", null);
/*     */           }
/* 478 */           this.filter = selector;
/*     */         } else {
/*     */ 
/*     */           
/*     */           try {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 487 */             Resolver jmsResolver = new JMSResolver();
/*     */ 
/*     */             
/* 490 */             MatchParser matchParser = MatchParser.prime(null, selector, true);
/*     */ 
/*     */             
/* 493 */             Selector selectorTree = matchParser.getSelector();
/*     */ 
/*     */ 
/*     */             
/* 497 */             if (selectorTree.type == 2)
/*     */             {
/* 499 */               if (Trace.isOn) {
/* 500 */                 Trace.traceData(this, "Reverting to Client side filtering due to SyntaxException", null);
/*     */               }
/* 502 */               this.filter = null;
/*     */             }
/*     */             else
/*     */             {
/* 506 */               Selector filterTree = Transformer.resolve(selectorTree, jmsResolver, null);
/*     */               
/* 508 */               this.filter = filterTree.toString();
/*     */               
/* 510 */               if (Trace.isOn) {
/* 511 */                 Trace.traceData(this, "Filter is " + this.filter, null);
/*     */               }
/*     */               
/* 514 */               if (this.filter != null && this.filter.indexOf("Root.MQRFH2.jms.Dlv") >= 0) {
/*     */ 
/*     */ 
/*     */                 
/* 518 */                 int index = 0;
/*     */                 
/* 520 */                 if ((index = this.filter.indexOf("'NON_PERSISTENT'")) > 0) {
/*     */                   
/* 522 */                   this.filter = this.filter.substring(0, index) + '\001' + this.filter.substring(index + 16);
/*     */                 }
/* 524 */                 else if ((index = this.filter.indexOf("'PERSISTENT'")) > 0) {
/*     */                   
/* 526 */                   this.filter = this.filter.substring(0, index) + '\002' + this.filter.substring(index + 12);
/*     */                 } 
/*     */ 
/*     */                 
/* 530 */                 if (Trace.isOn) {
/* 531 */                   Trace.traceData(this, "Adjusted filter is " + this.filter, null);
/*     */                 }
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 541 */               if (this.filter != null && this.filter.indexOf("JMS_ERROR") >= 0) {
/* 542 */                 if (Trace.isOn) {
/* 543 */                   Trace.traceData(this, "Reverting to Client side filtering", null);
/*     */                 }
/* 545 */                 this.filter = null;
/*     */               }
/*     */             
/*     */             }
/*     */           
/* 550 */           } catch (Exception e) {
/* 551 */             if (Trace.isOn) {
/* 552 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "setSelector(String)", e, 3);
/*     */             }
/*     */ 
/*     */             
/* 556 */             if (Trace.isOn) {
/* 557 */               Trace.traceData(this, "Reverting to Client side filtering due to SyntaxException", null);
/*     */             }
/* 559 */             this.filter = null;
/*     */           } 
/*     */         } 
/*     */       } 
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
/*     */   protected String getSelector() {
/* 575 */     if (Trace.isOn) {
/* 576 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "getSelector()", "getter", this.selector);
/*     */     }
/*     */     
/* 579 */     return this.selector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getFilter() throws JMSException {
/* 589 */     if (Trace.isOn) {
/* 590 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "getFilter()", "getter", this.filter);
/*     */     }
/*     */     
/* 593 */     return this.filter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setNoLocal(boolean noLocal) {
/* 602 */     if (Trace.isOn) {
/* 603 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "setNoLocal(boolean)", "setter", 
/* 604 */           Boolean.valueOf(noLocal));
/*     */     }
/* 606 */     this.noLocal = noLocal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean getNoLocal() {
/* 615 */     if (Trace.isOn) {
/* 616 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "getNoLocal()", "getter", 
/* 617 */           Boolean.valueOf(this.noLocal));
/*     */     }
/* 619 */     return this.noLocal;
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
/*     */   protected void setQueueName(String queueName) {
/* 633 */     if (Trace.isOn) {
/* 634 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "setQueueName(String)", "setter", queueName);
/*     */     }
/*     */     
/* 637 */     this.queueName = queueName;
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
/*     */   protected String getQueueName() {
/* 651 */     if (Trace.isOn) {
/* 652 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "getQueueName()", "getter", this.queueName);
/*     */     }
/*     */     
/* 655 */     return this.queueName;
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
/*     */   protected void setSubscriberQueue(MQQueue subscriberQueue) {
/* 671 */     if (Trace.isOn) {
/* 672 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "setSubscriberQueue(com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", "setter", subscriberQueue);
/*     */     }
/*     */ 
/*     */     
/* 676 */     this.subscriberQueue = subscriberQueue;
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
/*     */   protected MQQueue getSubscriberQueue() {
/* 689 */     if (Trace.isOn) {
/* 690 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "getSubscriberQueue()");
/*     */     }
/*     */     
/* 693 */     if (this.subscriberQueue == null) {
/* 694 */       if (Trace.isOn) {
/* 695 */         Trace.traceData(this, "subscriber queue is null!", null);
/*     */       }
/* 697 */       if (Trace.isOn) {
/* 698 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "getSubscriberQueue()", null, 1);
/*     */       }
/*     */       
/* 701 */       return null;
/*     */     } 
/* 703 */     if (Trace.isOn) {
/* 704 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "getSubscriberQueue()", this.subscriberQueue, 2);
/*     */     }
/*     */     
/* 707 */     return this.subscriberQueue;
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
/*     */   protected void setCorrelationId(byte[] correlationId) {
/* 722 */     if (Trace.isOn) {
/* 723 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "setCorrelationId(byte [ ])", "setter", correlationId);
/*     */     }
/*     */     
/* 726 */     if (correlationId == null) {
/* 727 */       this.correlationId = null;
/*     */     } else {
/* 729 */       this.correlationId = new byte[correlationId.length];
/* 730 */       System.arraycopy(correlationId, 0, this.correlationId, 0, correlationId.length);
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
/*     */   protected byte[] getCorrelationId() {
/* 747 */     if (Trace.isOn) {
/* 748 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "getCorrelationId()", "getter", this.correlationId);
/*     */     }
/*     */     
/* 751 */     return this.correlationId;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isClosed() {
/* 756 */     if (Trace.isOn) {
/* 757 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "isClosed()", "getter", 
/* 758 */           Boolean.valueOf(this.closed));
/*     */     }
/* 760 */     return this.closed;
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
/*     */   protected void close() throws JMSException {
/* 772 */     if (Trace.isOn) {
/* 773 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "close()");
/*     */     }
/*     */     try {
/* 776 */       if (isDurable()) {
/* 777 */         this.subscriptionEngine.closeDurableSubscription(this);
/*     */       } else {
/* 779 */         this.subscriptionEngine.closeSubscription(this);
/*     */       }
/*     */     
/* 782 */     } catch (JMSException je) {
/* 783 */       if (Trace.isOn) {
/* 784 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "close()", (Throwable)je);
/*     */       }
/*     */ 
/*     */       
/* 788 */       if (Trace.isOn) {
/* 789 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "close()", (Throwable)je);
/*     */       }
/*     */       
/* 792 */       throw je;
/*     */     } 
/* 794 */     if (Trace.isOn) {
/* 795 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "close()");
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
/*     */   public void remove() throws JMSException {
/* 811 */     if (Trace.isOn) {
/* 812 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "remove()");
/*     */     }
/*     */     
/*     */     try {
/* 816 */       if (this.subscriptionEngine instanceof MQBrokerSubscriptionEngine) {
/* 817 */         if (Trace.isOn) {
/* 818 */           Trace.traceData(this, "Broker Subscription Engine in use. Removing durable subscription", null);
/*     */         }
/* 820 */         ((MQBrokerSubscriptionEngine)this.subscriptionEngine).removeDurableSubscription(this);
/*     */       }
/* 822 */       else if (Trace.isOn) {
/* 823 */         Trace.traceData(this, "Subscription Engine " + this.subscriptionEngine + " does not maintain a durable subscription list. Nothing to do....", null);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 828 */     catch (JMSException je) {
/*     */       
/* 830 */       if (Trace.isOn) {
/* 831 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "remove()", (Throwable)je);
/* 832 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "remove()", (Throwable)je);
/*     */       } 
/* 834 */       throw je;
/*     */     } 
/*     */ 
/*     */     
/* 838 */     if (Trace.isOn) {
/* 839 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "remove()");
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
/*     */   public String toString() {
/* 852 */     if (Trace.isOn) {
/* 853 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "toString()");
/*     */     }
/*     */ 
/*     */     
/* 857 */     String traceRet1 = "\nsubName       : " + this.subName + "\nTopic         : " + this.topic + "\nQueue Manager : " + this.qmgrName + "\nQueue Name    : " + this.queueName + "\nSelector      : " + this.selector + "\nnoLocal?      : " + (this.noLocal ? "Y" : "N") + "\nClient Id     : " + this.clientId + "\ncorrelationId : " + Utils.bytesToHex(this.correlationId) + "\nsharedQ?      : " + (this.sharedQueue ? "Y" : "N") + "\ndurable?      : " + (this.durable ? "Y" : "N");
/*     */     
/* 859 */     if (Trace.isOn) {
/* 860 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscription", "toString()", traceRet1);
/*     */     }
/*     */ 
/*     */     
/* 864 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQSubscription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */