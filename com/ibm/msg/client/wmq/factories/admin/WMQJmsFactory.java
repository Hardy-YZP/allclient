/*     */ package com.ibm.msg.client.wmq.factories.admin;
/*     */ 
/*     */ import com.ibm.mq.jms.MQConnectionFactory;
/*     */ import com.ibm.mq.jms.MQDestination;
/*     */ import com.ibm.mq.jms.MQQueue;
/*     */ import com.ibm.mq.jms.MQQueueConnectionFactory;
/*     */ import com.ibm.mq.jms.MQRRSConnectionFactory;
/*     */ import com.ibm.mq.jms.MQRRSQueueConnectionFactory;
/*     */ import com.ibm.mq.jms.MQRRSTopicConnectionFactory;
/*     */ import com.ibm.mq.jms.MQTopic;
/*     */ import com.ibm.mq.jms.MQTopicConnectionFactory;
/*     */ import com.ibm.mq.jms.MQXAConnectionFactory;
/*     */ import com.ibm.mq.jms.MQXAQueueConnectionFactory;
/*     */ import com.ibm.mq.jms.MQXATopicConnectionFactory;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsConnectionFactory;
/*     */ import com.ibm.msg.client.jms.JmsDestination;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.jms.JmsQueue;
/*     */ import com.ibm.msg.client.jms.JmsQueueConnectionFactory;
/*     */ import com.ibm.msg.client.jms.JmsSession;
/*     */ import com.ibm.msg.client.jms.JmsTopic;
/*     */ import com.ibm.msg.client.jms.JmsTopicConnectionFactory;
/*     */ import com.ibm.msg.client.jms.JmsXAConnectionFactory;
/*     */ import com.ibm.msg.client.jms.JmsXAQueueConnectionFactory;
/*     */ import com.ibm.msg.client.jms.JmsXATopicConnectionFactory;
/*     */ import com.ibm.msg.client.provider.ProviderJmsFactory;
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
/*     */ public class WMQJmsFactory
/*     */   implements ProviderJmsFactory
/*     */ {
/*     */   static {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.data("com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/factories/admin/WMQJmsFactory.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WMQJmsFactory() {
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "<init>()");
/*     */     }
/*     */     
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsConnectionFactory createConnectionFactory() throws JMSException {
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createConnectionFactory()");
/*     */     }
/*     */ 
/*     */     
/*  87 */     MQConnectionFactory mQConnectionFactory = new MQConnectionFactory();
/*     */     
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createConnectionFactory()", mQConnectionFactory);
/*     */     }
/*     */     
/*  93 */     return (JmsConnectionFactory)mQConnectionFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsConnectionFactory createConnectionFactory(String name) throws JMSException {
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createConnectionFactory(String)", new Object[] { name });
/*     */     }
/*     */ 
/*     */     
/* 106 */     MQConnectionFactory mQConnectionFactory = new MQConnectionFactory();
/*     */     
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createConnectionFactory(String)", mQConnectionFactory);
/*     */     }
/*     */     
/* 112 */     return (JmsConnectionFactory)mQConnectionFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsDestination createDestination(String name) throws JMSException {
/*     */     MQDestination mQDestination;
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createDestination(String)", new Object[] { name });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     assert name != null : Trace.ffstAssertion(this, "createDestination()", "XN00C001", null);
/*     */ 
/*     */     
/* 130 */     if (name.startsWith("queue://", 0)) {
/* 131 */       JmsQueue jmsQueue = createQueue(name);
/*     */     }
/* 133 */     else if (name.startsWith("topic://", 0)) {
/* 134 */       JmsTopic jmsTopic = createTopic(name);
/*     */     }
/*     */     else {
/*     */       
/* 138 */       mQDestination = new MQDestination(name);
/*     */     } 
/*     */     
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createDestination(String)", mQDestination);
/*     */     }
/*     */     
/* 145 */     return (JmsDestination)mQDestination;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsQueue createQueue(String name) throws JMSException {
/*     */     MQQueue mQQueue;
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createQueue(String)", new Object[] { name });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 159 */     if (name == null) {
/* 160 */       mQQueue = new MQQueue();
/*     */     } else {
/*     */       
/* 163 */       mQQueue = new MQQueue(name);
/*     */     } 
/*     */     
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createQueue(String)", mQQueue);
/*     */     }
/*     */     
/* 170 */     return (JmsQueue)mQQueue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsQueueConnectionFactory createQueueConnectionFactory() throws JMSException {
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createQueueConnectionFactory()");
/*     */     }
/*     */ 
/*     */     
/* 183 */     MQQueueConnectionFactory mQQueueConnectionFactory = new MQQueueConnectionFactory();
/*     */     
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createQueueConnectionFactory()", mQQueueConnectionFactory);
/*     */     }
/*     */     
/* 189 */     return (JmsQueueConnectionFactory)mQQueueConnectionFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsQueueConnectionFactory createQueueConnectionFactory(String uri) throws JMSException {
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createQueueConnectionFactory(String)", new Object[] { uri });
/*     */     }
/*     */ 
/*     */     
/* 202 */     MQQueueConnectionFactory mQQueueConnectionFactory = new MQQueueConnectionFactory();
/*     */     
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createQueueConnectionFactory(String)", mQQueueConnectionFactory);
/*     */     }
/*     */     
/* 208 */     return (JmsQueueConnectionFactory)mQQueueConnectionFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsTopic createTopic(String name) throws JMSException {
/*     */     MQTopic mQTopic;
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createTopic(String)", new Object[] { name });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 222 */     if (name == null) {
/* 223 */       mQTopic = new MQTopic();
/*     */     } else {
/*     */       
/* 226 */       mQTopic = new MQTopic(name);
/*     */     } 
/*     */     
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createTopic(String)", mQTopic);
/*     */     }
/*     */     
/* 233 */     return (JmsTopic)mQTopic;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsTopicConnectionFactory createTopicConnectionFactory() throws JMSException {
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createTopicConnectionFactory()");
/*     */     }
/*     */ 
/*     */     
/* 246 */     MQTopicConnectionFactory mQTopicConnectionFactory = new MQTopicConnectionFactory();
/*     */     
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createTopicConnectionFactory()", mQTopicConnectionFactory);
/*     */     }
/*     */     
/* 252 */     return (JmsTopicConnectionFactory)mQTopicConnectionFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsTopicConnectionFactory createTopicConnectionFactory(String uri) throws JMSException {
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createTopicConnectionFactory(String)", new Object[] { uri });
/*     */     }
/*     */ 
/*     */     
/* 265 */     MQTopicConnectionFactory mQTopicConnectionFactory = new MQTopicConnectionFactory();
/*     */     
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createTopicConnectionFactory(String)", mQTopicConnectionFactory);
/*     */     }
/*     */     
/* 271 */     return (JmsTopicConnectionFactory)mQTopicConnectionFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsXAConnectionFactory createXAConnectionFactory() throws JMSException {
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createXAConnectionFactory()");
/*     */     }
/*     */ 
/*     */     
/* 286 */     MQXAConnectionFactory mQXAConnectionFactory = new MQXAConnectionFactory();
/*     */     
/* 288 */     if (Trace.isOn) {
/* 289 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createXAConnectionFactory()", mQXAConnectionFactory);
/*     */     }
/*     */     
/* 292 */     return (JmsXAConnectionFactory)mQXAConnectionFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsXAConnectionFactory createXAConnectionFactory(String uri) throws JMSException {
/* 301 */     if (Trace.isOn) {
/* 302 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createXAConnectionFactory(String)", new Object[] { uri });
/*     */     }
/*     */ 
/*     */     
/* 306 */     MQXAConnectionFactory mQXAConnectionFactory = new MQXAConnectionFactory();
/*     */     
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createXAConnectionFactory(String)", mQXAConnectionFactory);
/*     */     }
/*     */     
/* 312 */     return (JmsXAConnectionFactory)mQXAConnectionFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsXAQueueConnectionFactory createXAQueueConnectionFactory() throws JMSException {
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createXAQueueConnectionFactory()");
/*     */     }
/*     */ 
/*     */     
/* 326 */     MQXAQueueConnectionFactory mQXAQueueConnectionFactory = new MQXAQueueConnectionFactory();
/*     */     
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createXAQueueConnectionFactory()", mQXAQueueConnectionFactory);
/*     */     }
/*     */     
/* 332 */     return (JmsXAQueueConnectionFactory)mQXAQueueConnectionFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsXAQueueConnectionFactory createXAQueueConnectionFactory(String uri) throws JMSException {
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createXAQueueConnectionFactory(String)", new Object[] { uri });
/*     */     }
/*     */ 
/*     */     
/* 346 */     MQXAQueueConnectionFactory mQXAQueueConnectionFactory = new MQXAQueueConnectionFactory();
/*     */     
/* 348 */     if (Trace.isOn) {
/* 349 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createXAQueueConnectionFactory(String)", mQXAQueueConnectionFactory);
/*     */     }
/*     */     
/* 352 */     return (JmsXAQueueConnectionFactory)mQXAQueueConnectionFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsXATopicConnectionFactory createXATopicConnectionFactory() throws JMSException {
/* 361 */     if (Trace.isOn) {
/* 362 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createXATopicConnectionFactory()");
/*     */     }
/*     */ 
/*     */     
/* 366 */     MQXATopicConnectionFactory mQXATopicConnectionFactory = new MQXATopicConnectionFactory();
/*     */     
/* 368 */     if (Trace.isOn) {
/* 369 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createXATopicConnectionFactory()", mQXATopicConnectionFactory);
/*     */     }
/*     */     
/* 372 */     return (JmsXATopicConnectionFactory)mQXATopicConnectionFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsXATopicConnectionFactory createXATopicConnectionFactory(String uri) throws JMSException {
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createXATopicConnectionFactory(String)", new Object[] { uri });
/*     */     }
/*     */ 
/*     */     
/* 386 */     MQXATopicConnectionFactory mQXATopicConnectionFactory = new MQXATopicConnectionFactory();
/*     */     
/* 388 */     if (Trace.isOn) {
/* 389 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createXATopicConnectionFactory(String)", mQXATopicConnectionFactory);
/*     */     }
/*     */     
/* 392 */     return (JmsXATopicConnectionFactory)mQXATopicConnectionFactory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsPropertyContext createJmsObject(short flag, Object parameter) throws JMSException {
/*     */     MQTopic mQTopic;
/* 399 */     if (Trace.isOn) {
/* 400 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createJmsObject(short,Object)", new Object[] {
/* 401 */             Short.valueOf(flag), parameter
/*     */           });
/*     */     }
/* 404 */     JmsPropertyContext object = null;
/*     */     
/* 406 */     if ((flag & 0x10) == 16) {
/* 407 */       if ((flag & 0x1) == 1) {
/* 408 */         if ((flag & 0x40) == 64) {
/* 409 */           MQXAQueueConnectionFactory mQXAQueueConnectionFactory = new MQXAQueueConnectionFactory();
/*     */         }
/* 411 */         else if ((flag & 0x100) == 256) {
/* 412 */           MQRRSQueueConnectionFactory mQRRSQueueConnectionFactory = new MQRRSQueueConnectionFactory();
/*     */         } else {
/*     */           
/* 415 */           MQQueueConnectionFactory mQQueueConnectionFactory = new MQQueueConnectionFactory();
/*     */         }
/*     */       
/* 418 */       } else if ((flag & 0x2) == 2) {
/* 419 */         if ((flag & 0x40) == 64) {
/* 420 */           MQXATopicConnectionFactory mQXATopicConnectionFactory = new MQXATopicConnectionFactory();
/*     */         }
/* 422 */         else if ((flag & 0x100) == 256) {
/* 423 */           MQRRSTopicConnectionFactory mQRRSTopicConnectionFactory = new MQRRSTopicConnectionFactory();
/*     */         } else {
/*     */           
/* 426 */           MQTopicConnectionFactory mQTopicConnectionFactory = new MQTopicConnectionFactory();
/*     */         }
/*     */       
/* 429 */       } else if ((flag & 0x4) == 4) {
/* 430 */         if ((flag & 0x40) == 64) {
/* 431 */           MQXAConnectionFactory mQXAConnectionFactory = new MQXAConnectionFactory();
/*     */         }
/* 433 */         else if ((flag & 0x100) == 256) {
/* 434 */           MQRRSConnectionFactory mQRRSConnectionFactory = new MQRRSConnectionFactory();
/*     */         } else {
/*     */           
/* 437 */           MQConnectionFactory mQConnectionFactory = new MQConnectionFactory();
/*     */         }
/*     */       
/*     */       } 
/* 441 */     } else if ((flag & 0x20) == 32) {
/* 442 */       if ((flag & 0x1) == 1) {
/* 443 */         MQQueue mQQueue = new MQQueue((String)parameter);
/*     */       }
/* 445 */       else if ((flag & 0x2) == 2) {
/* 446 */         mQTopic = new MQTopic((String)parameter);
/*     */       } 
/*     */     } 
/*     */     
/* 450 */     if (Trace.isOn) {
/* 451 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createJmsObject(short,Object)", mQTopic);
/*     */     }
/*     */     
/* 454 */     return (JmsPropertyContext)mQTopic;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsQueue createQueue(String name, JmsSession session) throws JMSException {
/* 461 */     if (Trace.isOn) {
/* 462 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createQueue(String,JmsSession)", new Object[] { name, session });
/*     */     }
/*     */     
/* 465 */     JmsQueue queue = createQueue(name);
/*     */ 
/*     */ 
/*     */     
/* 469 */     String ccsid = session.getStringProperty("CCSID");
/* 470 */     if (ccsid != null) {
/* 471 */       queue.setStringProperty("CCSID", ccsid);
/*     */     }
/*     */ 
/*     */     
/* 475 */     queue.setIntProperty("failIfQuiesce", session
/* 476 */         .getIntProperty("failIfQuiesce"));
/*     */     
/* 478 */     if (Trace.isOn) {
/* 479 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createQueue(String,JmsSession)", queue);
/*     */     }
/*     */     
/* 482 */     return queue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsTopic createTopic(String name, JmsSession session) throws JMSException {
/* 489 */     if (Trace.isOn) {
/* 490 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createTopic(String,JmsSession)", new Object[] { name, session });
/*     */     }
/*     */     
/* 493 */     JmsTopic topic = createTopic(name);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 498 */     int brokerVer = session.getIntProperty("brokerVersion");
/* 499 */     if (brokerVer != -1) {
/* 500 */       topic.setIntProperty("brokerVersion", brokerVer);
/*     */     }
/*     */ 
/*     */     
/* 504 */     String ccsid = session.getStringProperty("CCSID");
/* 505 */     if (ccsid != null) {
/* 506 */       topic.setStringProperty("CCSID", ccsid);
/*     */     }
/*     */ 
/*     */     
/* 510 */     topic.setIntProperty("failIfQuiesce", session
/* 511 */         .getIntProperty("failIfQuiesce"));
/*     */ 
/*     */     
/* 514 */     topic.setIntProperty("wildcardFormat", session
/* 515 */         .getIntProperty("wildcardFormat"));
/*     */     
/* 517 */     if (Trace.isOn) {
/* 518 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createTopic(String,JmsSession)", topic);
/*     */     }
/*     */     
/* 521 */     return topic;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsDestination createDestination(String name, JmsSession session) throws JMSException {
/* 528 */     if (Trace.isOn) {
/* 529 */       Trace.entry(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createDestination(String,JmsSession)", new Object[] { name, session });
/*     */     }
/*     */     
/* 532 */     JmsDestination destination = createDestination(name);
/*     */ 
/*     */ 
/*     */     
/* 536 */     destination.setStringProperty("XMSC_WMQ_BROKER_PUBQ", session
/* 537 */         .getStringProperty("XMSC_WMQ_BROKER_PUBQ"));
/*     */ 
/*     */     
/* 540 */     int brokerVer = session.getIntProperty("brokerVersion");
/* 541 */     if (brokerVer != -1) {
/* 542 */       destination.setIntProperty("brokerVersion", brokerVer);
/*     */     }
/*     */ 
/*     */     
/* 546 */     String ccsid = session.getStringProperty("CCSID");
/* 547 */     if (ccsid != null) {
/* 548 */       destination.setStringProperty("CCSID", ccsid);
/*     */     }
/*     */ 
/*     */     
/* 552 */     destination.setIntProperty("failIfQuiesce", session
/* 553 */         .getIntProperty("failIfQuiesce"));
/*     */ 
/*     */     
/* 556 */     destination.setIntProperty("wildcardFormat", session
/* 557 */         .getIntProperty("wildcardFormat"));
/*     */     
/* 559 */     if (Trace.isOn) {
/* 560 */       Trace.exit(this, "com.ibm.msg.client.wmq.factories.admin.WMQJmsFactory", "createDestination(String,JmsSession)", destination);
/*     */     }
/*     */     
/* 563 */     return destination;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\factories\admin\WMQJmsFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */