/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.mq.MQException;
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
/*     */ abstract class MQSubscriptionEngine
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQSubscriptionEngine.java";
/*     */   private static final String CLASSNAME = "MQSubscriptionEngine";
/*     */   static final int NON_DURABLE = 0;
/*     */   static final int DURABLE = 1;
/*     */   
/*     */   static {
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQSubscriptionEngine", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQSubscriptionEngine.java");
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
/*  97 */   protected MQConnection connection = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final int DEFAULT_BROKER_TIMEOUT = 120000;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQSubscriptionEngine(MQConnection connection) {
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscriptionEngine", "<init>(MQConnection)", new Object[] { connection });
/*     */     }
/*     */     
/* 117 */     this.connection = connection;
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscriptionEngine", "<init>(MQConnection)");
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
/*     */   
/*     */   public final MQConnection getMQConnection() {
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscriptionEngine", "getMQConnection()", "getter", this.connection);
/*     */     }
/*     */     
/* 235 */     return this.connection;
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
/*     */   static String validateQueueName(String queueName, int durability) throws JMSException {
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQSubscriptionEngine", "validateQueueName(String,int)", new Object[] { queueName, 
/* 262 */             Integer.valueOf(durability) });
/*     */     }
/* 264 */     String ret = null;
/*     */     
/* 266 */     if (queueName.trim().equals("")) {
/*     */ 
/*     */ 
/*     */       
/* 270 */       if (Trace.isOn) {
/* 271 */         Trace.traceData("MQSubscriptionEngine", "queueName is blank; reverting to MQJMSv1 approach", null);
/*     */       }
/*     */       
/* 274 */       if (durability == 0) {
/* 275 */         ret = "SYSTEM.JMS.ND.*";
/*     */       } else {
/* 277 */         ret = "SYSTEM.JMS.D.*";
/*     */       } 
/*     */     } else {
/*     */       
/* 281 */       if ((durability == 0 && !queueName.startsWith("SYSTEM.JMS.ND.")) || (durability == 1 && 
/* 282 */         !queueName.startsWith("SYSTEM.JMS.D."))) {
/*     */         
/* 284 */         JMSException je = ConfigEnvironment.newException("MQJMS3021", queueName);
/*     */         
/* 286 */         if (Trace.isOn) {
/* 287 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQSubscriptionEngine", "validateQueueName(String,int)", (Throwable)je);
/*     */         }
/*     */         
/* 290 */         throw je;
/*     */       } 
/*     */       
/* 293 */       ret = queueName;
/*     */     } 
/*     */     
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQSubscriptionEngine", "validateQueueName(String,int)", ret);
/*     */     }
/*     */     
/* 300 */     return ret;
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
/*     */   protected MQQueue openSubscriberQueue(MQSession mqs, String queueNameP, boolean sharedQueue, boolean durable, int fiqBehaviour) throws JMSException {
/*     */     String queuePrefix;
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscriptionEngine", "openSubscriberQueue(MQSession,String,boolean,boolean,int)", new Object[] { mqs, queueNameP, 
/*     */             
/* 331 */             Boolean.valueOf(sharedQueue), Boolean.valueOf(durable), Integer.valueOf(fiqBehaviour) });
/*     */     }
/*     */     
/* 334 */     MQQueue subscriberQueue = null;
/*     */     
/* 336 */     String queueName = queueNameP;
/*     */     
/* 338 */     int openOptions = 161;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 345 */     if (this.connection.getSparseSubscriptions()) {
/* 346 */       openOptions |= 0x8;
/*     */     }
/*     */ 
/*     */     
/* 350 */     if (fiqBehaviour == 1) {
/* 351 */       openOptions |= 0x2000;
/*     */     }
/*     */     
/* 354 */     if (durable) {
/* 355 */       queuePrefix = "SYSTEM.JMS.D.";
/*     */     } else {
/* 357 */       queuePrefix = "SYSTEM.JMS.ND.";
/*     */     } 
/*     */ 
/*     */     
/* 361 */     if (queueName.trim().equals("") && !sharedQueue) {
/*     */ 
/*     */ 
/*     */       
/* 365 */       if (durable) {
/* 366 */         queueName = "SYSTEM.JMS.D.*";
/*     */       } else {
/* 368 */         queueName = "SYSTEM.JMS.ND.*";
/*     */       }
/*     */     
/*     */     }
/* 372 */     else if (!queueName.startsWith(queuePrefix)) {
/* 373 */       JMSException je = ConfigEnvironment.newException("MQJMS3021", queueName);
/*     */       
/* 375 */       if (Trace.isOn) {
/* 376 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscriptionEngine", "openSubscriberQueue(MQSession,String,boolean,boolean,int)", (Throwable)je, 1);
/*     */       }
/*     */ 
/*     */       
/* 380 */       throw je;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 391 */       if (queueName.endsWith("*") && !sharedQueue) {
/*     */ 
/*     */         
/* 394 */         if (Trace.isOn) {
/* 395 */           Trace.traceData(this, "opening new non-shared subscriber queue", null);
/*     */         }
/*     */         
/* 398 */         subscriberQueue = mqs.getQM().accessQueue("SYSTEM.JMS.MODEL.QUEUE", openOptions, null, queueName, null);
/*     */         
/* 400 */         queueName = subscriberQueue.name;
/*     */ 
/*     */         
/* 403 */         if (Trace.isOn) {
/* 404 */           Trace.traceData(this, "Using non-shared queue approach.", null);
/* 405 */           Trace.traceData(this, "Model:  SYSTEM.JMS.MODEL.QUEUE", null);
/* 406 */           Trace.traceData(this, "Prefix: " + queuePrefix, null);
/* 407 */           Trace.traceData(this, "QName:  " + queueName, null);
/*     */         }
/*     */       
/* 410 */       } else if (sharedQueue) {
/*     */ 
/*     */         
/* 413 */         subscriberQueue = mqs.getQM().accessQueue(queueName, openOptions);
/*     */         
/* 415 */         queueName = subscriberQueue.name;
/*     */         
/* 417 */         if (Trace.isOn) {
/* 418 */           Trace.traceData(this, "Using shared queue approach.", null);
/* 419 */           Trace.traceData(this, "Prefix: " + queuePrefix, null);
/* 420 */           Trace.traceData(this, "QName:  " + queueName, null);
/*     */         } 
/*     */       } else {
/*     */         
/* 424 */         if (Trace.isOn) {
/* 425 */           Trace.traceData(this, "Must be re-opening a non-shared queue for an existing subscription", null);
/*     */         }
/*     */         
/* 428 */         subscriberQueue = mqs.getQM().accessQueue(queueName, openOptions);
/*     */         
/* 430 */         queueName = subscriberQueue.name;
/*     */         
/* 432 */         if (Trace.isOn) {
/* 433 */           Trace.traceData(this, "Using non-shared queue approach.", null);
/* 434 */           Trace.traceData(this, "Prefix: " + queuePrefix, null);
/* 435 */           Trace.traceData(this, "QName:  " + queueName, null);
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 440 */     } catch (MQException e) {
/* 441 */       if (Trace.isOn) {
/* 442 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscriptionEngine", "openSubscriberQueue(MQSession,String,boolean,boolean,int)", (Throwable)e);
/*     */       }
/*     */       
/* 445 */       JMSException je = ConfigEnvironment.newException("MQJMS2008", queueName);
/*     */       
/* 447 */       je.setLinkedException((Exception)e);
/* 448 */       if (Trace.isOn) {
/* 449 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscriptionEngine", "openSubscriberQueue(MQSession,String,boolean,boolean,int)", (Throwable)je, 2);
/*     */       }
/*     */       
/* 452 */       throw je;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 458 */     if (Trace.isOn) {
/* 459 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSubscriptionEngine", "openSubscriberQueue(MQSession,String,boolean,boolean,int)", subscriberQueue);
/*     */     }
/*     */     
/* 462 */     return subscriberQueue;
/*     */   }
/*     */   
/*     */   public abstract MQSubscription openSubscription(MQSession paramMQSession, WMQDestination paramWMQDestination, String paramString1, boolean paramBoolean1, boolean paramBoolean2, String paramString2) throws JMSException;
/*     */   
/*     */   public abstract void closeSubscription(MQSubscription paramMQSubscription) throws JMSException;
/*     */   
/*     */   public abstract MQSubscription openDurableSubscription(MQSession paramMQSession, WMQDestination paramWMQDestination, String paramString1, boolean paramBoolean1, boolean paramBoolean2, String paramString2, String paramString3) throws JMSException;
/*     */   
/*     */   public abstract void closeDurableSubscription(MQSubscription paramMQSubscription) throws JMSException;
/*     */   
/*     */   public abstract void durableUnsubscribe(MQSession paramMQSession, String paramString) throws JMSException;
/*     */   
/*     */   public abstract void close();
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQSubscriptionEngine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */