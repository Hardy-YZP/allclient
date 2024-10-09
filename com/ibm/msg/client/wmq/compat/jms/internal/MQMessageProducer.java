/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessageProducer;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQPropertyContext;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQC;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager;
/*      */ import java.io.IOException;
/*      */ import java.io.NotSerializableException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.util.HashMap;
/*      */ import javax.jms.IllegalStateException;
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
/*      */ public class MQMessageProducer
/*      */   extends WMQPropertyContext
/*      */   implements ProviderMessageProducer
/*      */ {
/*      */   private static final long serialVersionUID = 3088542628007809451L;
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQMessageProducer.java";
/*      */   private static final int JMS101_DEFAULT_VAL = -1;
/*      */   
/*      */   static {
/*   80 */     if (Trace.isOn) {
/*   81 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQMessageProducer.java");
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
/*      */   private boolean closed = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQPutMessageOptions pmo;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  118 */   private MQSession session = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean setForPTP = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean setForPubSub = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  133 */   private WMQDestination queueSpec = null;
/*      */ 
/*      */   
/*  136 */   private MQQueue queue = null;
/*      */ 
/*      */   
/*  139 */   private MQJMSMessage mqMessage = new MQJMSMessage();
/*      */ 
/*      */   
/*  142 */   private WMQDestination TopicSpec = null;
/*      */ 
/*      */   
/*  145 */   private MQQueue mqPubQ = null;
/*      */ 
/*      */   
/*  148 */   private MQQueueManager qm = null;
/*      */ 
/*      */ 
/*      */   
/*  152 */   private MQJMSMessage baseMessage = new MQJMSMessage();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean firstMsg = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean firstMsgP2P = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean firstPublish = true;
/*      */ 
/*      */ 
/*      */   
/*  171 */   private byte[] cachedRFHData = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  178 */   private String cachedBaseTopic = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  186 */   private int cachedEncoding = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String brokerPubQMgr;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String PROBE_02 = "02";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQMessageProducer(WMQDestination dest, MQQueue queue, MQSession session) throws JMSException {
/*  211 */     super(null);
/*  212 */     if (Trace.isOn) {
/*  213 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "<init>(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQSession)", new Object[] { dest, queue, session });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  221 */     this.setForPTP = true;
/*      */     
/*  223 */     this.queueSpec = dest;
/*  224 */     this.queue = queue;
/*  225 */     this.session = session;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  235 */     this.pmo = new MQPutMessageOptions(true);
/*      */     
/*  237 */     if (Trace.isOn) {
/*  238 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "<init>(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,MQSession)");
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
/*      */   MQMessageProducer(WMQDestination dest, MQQueue mqqueue, MQQueueManager qm, MQConnection connection, MQSession session) throws JMSException {
/*  259 */     super(null);
/*  260 */     if (Trace.isOn) {
/*  261 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "<init>(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager,MQConnection,MQSession)", new Object[] { dest, mqqueue, qm, connection, session });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  266 */     if (Trace.isOn && 
/*  267 */       mqqueue != null) {
/*  268 */       Trace.traceData(this, mqqueue + "(" + mqqueue.name + ")", null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  275 */     this.setForPubSub = true;
/*      */     
/*  277 */     this.TopicSpec = dest;
/*  278 */     this.mqPubQ = mqqueue;
/*  279 */     this.qm = qm;
/*  280 */     this.session = session;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  286 */     this.brokerPubQMgr = qm.name;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  294 */     this.pmo = new MQPutMessageOptions(true);
/*      */     
/*  296 */     if (Trace.isOn) {
/*  297 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "<init>(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager,MQConnection,MQSession)");
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
/*      */   MQMessageProducer(MQQueue mqqueue, MQQueueManager qm, MQConnection connection, MQSession session) throws JMSException {
/*  318 */     super(null);
/*  319 */     if (Trace.isOn) {
/*  320 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager,MQConnection,MQSession)", new Object[] { mqqueue, qm, connection, session });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  326 */     this.TopicSpec = null;
/*  327 */     this.queue = null;
/*  328 */     this.mqPubQ = mqqueue;
/*  329 */     this.qm = qm;
/*  330 */     this.session = session;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  338 */     this.pmo = new MQPutMessageOptions(true);
/*      */ 
/*      */ 
/*      */     
/*  342 */     if (Trace.isOn) {
/*  343 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "<init>(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager,MQConnection,MQSession)");
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
/*      */   public void close(boolean closingFromSession) throws JMSException {
/*  365 */     if (Trace.isOn) {
/*  366 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "close(boolean)", new Object[] {
/*  367 */             Boolean.valueOf(closingFromSession)
/*      */           });
/*      */     }
/*      */     try {
/*  371 */       this.closed = true;
/*      */       
/*  373 */       if (this.setForPTP) {
/*  374 */         closeQ();
/*      */       }
/*  376 */       if (this.setForPubSub) {
/*  377 */         closeT();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  386 */         if (this.mqPubQ != null && this.mqPubQ.isOpen()) {
/*  387 */           this.mqPubQ.close();
/*      */         }
/*  389 */         this.mqPubQ = null;
/*      */       }
/*  391 */       catch (MQException mqe) {
/*  392 */         if (Trace.isOn) {
/*  393 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "close(boolean)", (Throwable)mqe, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  398 */         if (Trace.isOn) {
/*  399 */           Trace.traceData(this, "Exception closing mqPubQ: " + mqe.getMessage() + " Reason: " + mqe.reasonCode, null);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  404 */       if (this.session != null) {
/*  405 */         this.session.removeProducer(this);
/*      */       }
/*  407 */       else if (Trace.isOn) {
/*  408 */         Trace.traceData(this, "session null, can't call removeSender", null);
/*      */       }
/*      */     
/*      */     }
/*  412 */     catch (JMSException je) {
/*  413 */       if (Trace.isOn) {
/*  414 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "close(boolean)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  417 */       if (Trace.isOn) {
/*  418 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "close(boolean)", (Throwable)je);
/*      */       }
/*      */       
/*  421 */       throw je;
/*      */     } 
/*      */     
/*  424 */     if (Trace.isOn) {
/*  425 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "close(boolean)");
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
/*      */   private void closeQ() throws JMSException {
/*  441 */     if (Trace.isOn) {
/*  442 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "closeQ()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  447 */       this.closed = true;
/*      */       
/*  449 */       if (this.session != null) {
/*  450 */         this.session.removeSender(this);
/*      */       }
/*  452 */       else if (Trace.isOn) {
/*  453 */         Trace.traceData(this, "session null, can't call removeSender", null);
/*      */       } 
/*      */ 
/*      */       
/*  457 */       if (this.queue != null) {
/*      */         try {
/*  459 */           this.queue.close();
/*  460 */           this.queue = null;
/*      */         }
/*  462 */         catch (MQException e) {
/*  463 */           if (Trace.isOn) {
/*  464 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "closeQ()", (Throwable)e, 1);
/*      */           }
/*      */ 
/*      */           
/*  468 */           JMSException je = ConfigEnvironment.newException("MQJMS2000");
/*  469 */           je.setLinkedException((Exception)e);
/*  470 */           if (Trace.isOn) {
/*  471 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "closeQ()", (Throwable)je, 1);
/*      */           }
/*      */           
/*  474 */           throw je;
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  481 */         if (this.mqPubQ != null && this.mqPubQ.isOpen()) {
/*  482 */           this.mqPubQ.close();
/*      */         }
/*  484 */         this.mqPubQ = null;
/*      */       }
/*  486 */       catch (MQException mqe) {
/*  487 */         if (Trace.isOn) {
/*  488 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "closeQ()", (Throwable)mqe, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  493 */         if (Trace.isOn) {
/*  494 */           Trace.traceData(this, "Exception closing mqPubQ: " + mqe.getMessage() + " Reason: " + mqe.reasonCode, null);
/*      */         
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*  500 */     catch (JMSException je) {
/*  501 */       if (Trace.isOn) {
/*  502 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "closeQ()", (Throwable)je, 3);
/*      */       }
/*      */       
/*  505 */       if (Trace.isOn) {
/*  506 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "closeQ()", (Throwable)je, 2);
/*      */       }
/*      */       
/*  509 */       throw je;
/*      */     } 
/*      */     
/*  512 */     if (Trace.isOn) {
/*  513 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "closeQ()");
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
/*      */   private void closeT() throws JMSException {
/*  529 */     if (Trace.isOn) {
/*  530 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "closeT()");
/*      */     }
/*      */ 
/*      */     
/*  534 */     this.closed = true;
/*      */     
/*  536 */     if (this.TopicSpec != null) {
/*      */       
/*      */       try {
/*  539 */         this.TopicSpec = null;
/*      */         
/*  541 */         this.mqPubQ.close();
/*      */       }
/*  543 */       catch (Exception e) {
/*  544 */         if (Trace.isOn) {
/*  545 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "closeT()", e, 1);
/*      */         }
/*      */ 
/*      */         
/*  549 */         JMSException je = ConfigEnvironment.newException("MQJMS2000");
/*  550 */         je.setLinkedException(e);
/*      */         
/*  552 */         if (Trace.isOn) {
/*  553 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "closeT()", (Throwable)je);
/*      */         }
/*      */         
/*  556 */         throw je;
/*      */       }
/*      */       finally {
/*      */         
/*  560 */         if (Trace.isOn) {
/*  561 */           Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "closeT()");
/*      */         }
/*      */ 
/*      */         
/*  565 */         this.mqPubQ = null;
/*      */       } 
/*      */ 
/*      */       
/*  569 */       this.session.removePublisher(this);
/*      */     } else {
/*      */ 
/*      */       
/*      */       try {
/*      */ 
/*      */ 
/*      */         
/*  577 */         if (this.mqPubQ != null && this.mqPubQ.isOpen()) {
/*  578 */           this.mqPubQ.close();
/*      */         }
/*  580 */         this.mqPubQ = null;
/*      */       }
/*  582 */       catch (MQException mqe) {
/*  583 */         if (Trace.isOn) {
/*  584 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "closeT()", (Throwable)mqe, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  589 */         if (Trace.isOn) {
/*  590 */           Trace.traceData(this, "Exception closing mqPubQ: " + mqe.getMessage() + " Reason: " + mqe.reasonCode, null);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  597 */     if (Trace.isOn) {
/*  598 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "closeT()");
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
/*      */   private int getDeliveryMode() throws JMSException {
/*  616 */     int retVal = getIntProperty("deliveryMode");
/*      */     
/*  618 */     if (Trace.isOn) {
/*  619 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "getDeliveryMode()", "getter", 
/*  620 */           Integer.valueOf(retVal));
/*      */     }
/*  622 */     return retVal;
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
/*      */   private int getPriority() throws JMSException {
/*  635 */     int retVal = getIntProperty("priority");
/*      */     
/*  637 */     if (Trace.isOn) {
/*  638 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "getPriority()", "getter", 
/*  639 */           Integer.valueOf(retVal));
/*      */     }
/*  641 */     return retVal;
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
/*      */   private long getTimeToLive() throws JMSException {
/*  657 */     long retVal = getLongProperty("timeToLive");
/*      */     
/*  659 */     if (Trace.isOn) {
/*  660 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "getTimeToLive()", "getter", 
/*  661 */           Long.valueOf(retVal));
/*      */     }
/*  663 */     return retVal;
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
/*      */   private void send(ProviderMessage message, int deliveryMode, int priority, long timeToLive) throws JMSException {
/*  703 */     if (Trace.isOn) {
/*  704 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderMessage,int,int,long)", new Object[] { message, 
/*  705 */             Integer.valueOf(deliveryMode), 
/*  706 */             Integer.valueOf(priority), Long.valueOf(timeToLive) });
/*      */     }
/*      */     try {
/*  709 */       if (this.closed) {
/*  710 */         String msg = ConfigEnvironment.getErrorMessage("MQJMS3026");
/*  711 */         IllegalStateException je = new IllegalStateException(msg);
/*  712 */         if (Trace.isOn) {
/*  713 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderMessage,int,int,long)", (Throwable)je, 1);
/*      */         }
/*      */         
/*  716 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  722 */       if (this.setForPubSub) {
/*  723 */         publishInt(message, deliveryMode, priority, timeToLive);
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  729 */         if (this.queueSpec == null) {
/*  730 */           String msg = ConfigEnvironment.getErrorMessage("MQJMS1091");
/*  731 */           UnsupportedOperationException uoe = new UnsupportedOperationException(msg);
/*  732 */           if (Trace.isOn) {
/*  733 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderMessage,int,int,long)", uoe, 2);
/*      */           }
/*      */           
/*  736 */           throw uoe;
/*      */         } 
/*      */ 
/*      */         
/*  740 */         if (this.queue == null) {
/*  741 */           JMSException je = ConfigEnvironment.newException("MQJMS2001");
/*  742 */           if (Trace.isOn) {
/*  743 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderMessage,int,int,long)", (Throwable)je, 3);
/*      */           }
/*      */           
/*  746 */           throw je;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  751 */         if (this.session.usingAsyncMode() && !this.session.callingFromOnMessage() && this.session.isStarted()) {
/*  752 */           if (Trace.isOn) {
/*  753 */             Trace.traceData(this, "can't send because session is actively using async delivery", null);
/*      */           }
/*  755 */           JMSException je = ConfigEnvironment.newException("MQJMS1013");
/*  756 */           if (Trace.isOn) {
/*  757 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderMessage,int,int,long)", (Throwable)je, 4);
/*      */           }
/*      */           
/*  760 */           throw je;
/*      */         } 
/*      */         
/*  763 */         sendInternal(this.queueSpec, this.queue, message, deliveryMode, priority, timeToLive);
/*      */       }
/*      */     
/*      */     }
/*  767 */     catch (JMSException je) {
/*  768 */       if (Trace.isOn) {
/*  769 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderMessage,int,int,long)", (Throwable)je);
/*      */       }
/*      */       
/*  772 */       if (Trace.isOn) {
/*  773 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderMessage,int,int,long)", (Throwable)je, 5);
/*      */       }
/*      */       
/*  776 */       throw je;
/*      */     } 
/*      */     
/*  779 */     if (Trace.isOn) {
/*  780 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderMessage,int,int,long)");
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
/*      */   private void sendInternal(WMQDestination dest, MQQueue mqQueue, ProviderMessage message, int deliveryMode, int priority, long timeToLive) throws JMSException {
/*  802 */     if (Trace.isOn) {
/*  803 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "sendInternal(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)", new Object[] { dest, mqQueue, message, 
/*      */             
/*  805 */             Integer.valueOf(deliveryMode), 
/*  806 */             Integer.valueOf(priority), Long.valueOf(timeToLive) });
/*      */     }
/*      */     
/*      */     try {
/*  810 */       JMSMessage jmsMessage = null;
/*      */       
/*  812 */       if (this.firstMsgP2P) {
/*  813 */         this.session.addQueueServices();
/*  814 */         this.firstMsgP2P = false;
/*      */       } 
/*      */ 
/*      */       
/*  818 */       if (message == null) {
/*  819 */         if (Trace.isOn) {
/*  820 */           Trace.traceData(this, "sendInternal given a null message", null);
/*      */         }
/*  822 */         JMSException je = ConfigEnvironment.newException("MQJMS1006", "message", "null");
/*  823 */         if (Trace.isOn) {
/*  824 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "sendInternal(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */         
/*  828 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*  832 */       if (!(message instanceof JMSMessage)) {
/*  833 */         if (Trace.isOn) {
/*  834 */           Trace.traceData(this, "sendInternal publishing alien message of class " + message.getClass().getName(), null);
/*      */         }
/*      */         
/*  837 */         jmsMessage = JMSMessage._copyFromMessage(this.session, message);
/*      */       } else {
/*      */         
/*  840 */         jmsMessage = (JMSMessage)message;
/*      */       } 
/*      */ 
/*      */       
/*  844 */       if (dest != null) {
/*      */ 
/*      */         
/*  847 */         if (dest.getIntProperty("failIfQuiesce") == 1) {
/*  848 */           this.pmo.options |= 0x2000;
/*  849 */           if (Trace.isOn) {
/*  850 */             Trace.traceData(this, "FIQ behaviour: YES", null);
/*      */           }
/*      */         } else {
/*  853 */           this.pmo.options &= 0xFFFFDFFF;
/*  854 */           if (Trace.isOn) {
/*  855 */             Trace.traceData(this, "FIQ behaviour: NO", null);
/*      */           }
/*      */         } 
/*      */       } else {
/*  859 */         throw new NullPointerException("Destination is null");
/*      */       } 
/*      */ 
/*      */       
/*  863 */       boolean transacted = this.session.getTransacted();
/*  864 */       if (transacted) {
/*  865 */         this.pmo.options |= 0x2;
/*  866 */         this.pmo.options &= 0xFFFFFFFB;
/*      */       } else {
/*      */         
/*  869 */         this.pmo.options |= 0x4;
/*  870 */         this.pmo.options &= 0xFFFFFFFD;
/*      */       } 
/*      */ 
/*      */       
/*  874 */       this.pmo.options |= computeMessageContextOptions(dest);
/*      */ 
/*      */       
/*      */       try {
/*  878 */         if (this.session.getQM().getCommandLevel() > 700) {
/*  879 */           this.pmo.options |= 0x20000;
/*      */         }
/*      */       }
/*  882 */       catch (MQException me) {
/*  883 */         if (Trace.isOn) {
/*  884 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "sendInternal(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)", (Throwable)me, 1);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  893 */       long sendTime = System.currentTimeMillis();
/*      */       
/*      */       try {
/*      */         int ccsid;
/*      */         
/*  898 */         this.mqMessage.clearMessageData();
/*      */ 
/*      */         
/*  901 */         this.mqMessage.setFormat("MQHRF2  ");
/*      */ 
/*      */ 
/*      */         
/*  905 */         if (dest.propertyExists("encoding")) {
/*  906 */           this.mqMessage.setEncoding(dest.getIntProperty("encoding"));
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  911 */         if (mqQueue != null) {
/*  912 */           this.mqMessage.setCharacterSet(mqQueue._getConnectionCCSID());
/*      */         } else {
/*  914 */           this.mqMessage.setCharacterSet(this.session.getQM()._getConnectionCCSID());
/*      */         } 
/*      */ 
/*      */         
/*  918 */         jmsMessage.setJMSDestinationAsString(dest.toURI());
/*      */ 
/*      */         
/*  921 */         this.mqMessage.setPersistenceFromMD(this.session.getPersistenceFromMD());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  935 */         long destExp = dest.getLongProperty("timeToLive");
/*  936 */         if (destExp == -2L) {
/*  937 */           if (timeToLive == -1L) {
/*  938 */             jmsMessage._setTimeToLive(sendTime, getTimeToLive());
/*  939 */           } else if (timeToLive >= 0L) {
/*  940 */             jmsMessage._setTimeToLive(sendTime, timeToLive);
/*      */           } else {
/*      */             
/*  943 */             JMSException je = ConfigEnvironment.newException("MQJMS1006", "timeToLive", String.valueOf(timeToLive));
/*  944 */             if (Trace.isOn) {
/*  945 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "sendInternal(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)", (Throwable)je, 2);
/*      */             }
/*      */ 
/*      */             
/*  949 */             throw je;
/*      */           } 
/*      */         } else {
/*  952 */           jmsMessage._setTimeToLive(sendTime, destExp);
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
/*  963 */         int destPri = dest.getIntProperty("priority");
/*  964 */         if (destPri == -2) {
/*  965 */           if (priority == -1) {
/*  966 */             jmsMessage.setJMSPriority(getPriority());
/*  967 */           } else if (priority >= 0 && priority <= 9) {
/*  968 */             jmsMessage.setJMSPriority(priority);
/*      */           } else {
/*      */             
/*  971 */             JMSException je = ConfigEnvironment.newException("MQJMS1006", "priority", String.valueOf(priority));
/*  972 */             if (Trace.isOn) {
/*  973 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "sendInternal(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)", (Throwable)je, 3);
/*      */             }
/*      */ 
/*      */             
/*  977 */             throw je;
/*      */           }
/*      */         
/*  980 */         } else if (destPri == -1) {
/*  981 */           jmsMessage.setJMSPriority(4);
/*      */         } else {
/*  983 */           jmsMessage.setJMSPriority(destPri);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  989 */         if (dest.getIntProperty("deliveryMode") == 3 && !dest.isNPHighCheckDone()) {
/*  990 */           if (this.queue != null) {
/*  991 */             dest.setNPHighSupported((!this.session.getPersistenceFromMD() && dest.getIntProperty("targetClient") != 1 && dest
/*  992 */                 .getIntProperty("messageBody") != 1 && this.queue.isNPMClassHigh()));
/*      */           } else {
/*      */ 
/*      */             
/*      */             try {
/*  997 */               MQQueue theQueue = this.session.getQM().accessQueue(dest.getName(), 32, dest
/*  998 */                   .getStringProperty("XMSC_WMQ_QUEUE_MANAGER"), (this.session.connection != null) ? this.session.connection.temporaryModelQ : null, null);
/*  999 */               dest.setNPHighSupported((!this.session.getPersistenceFromMD() && dest.getIntProperty("targetClient") != 1 && dest
/* 1000 */                   .getIntProperty("messageBody") != 1 && theQueue.isNPMClassHigh()));
/*      */               
/* 1002 */               theQueue.close();
/*      */             
/*      */             }
/* 1005 */             catch (MQException me) {
/* 1006 */               if (Trace.isOn) {
/* 1007 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "sendInternal(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)", (Throwable)me, 2);
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1014 */               dest.setNPHighSupported(false);
/*      */             } 
/*      */           } 
/*      */         }
/*      */         
/* 1019 */         int destPer = dest.getIntProperty("deliveryMode");
/* 1020 */         if (destPer == -2 || destPer == 3) {
/* 1021 */           JMSException je; switch (deliveryMode) {
/*      */             case -1:
/* 1023 */               jmsMessage.setJMSDeliveryMode(getDeliveryMode());
/*      */               break;
/*      */             case 1:
/*      */             case 2:
/* 1027 */               jmsMessage.setJMSDeliveryMode(deliveryMode);
/*      */               break;
/*      */             
/*      */             default:
/* 1031 */               je = ConfigEnvironment.newException("MQJMS1006", "deliveryMode", String.valueOf(deliveryMode));
/* 1032 */               if (Trace.isOn) {
/* 1033 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "sendInternal(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)", (Throwable)je, 4);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/* 1038 */               throw je;
/*      */           } 
/* 1040 */         } else if (destPer == -1) {
/* 1041 */           jmsMessage.setJMSDeliveryMode(-2);
/*      */         } else {
/* 1043 */           jmsMessage.setJMSDeliveryMode(destPer);
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
/* 1055 */         Integer enc = (Integer)jmsMessage.getObjectProperty("JMS_IBM_Encoding");
/* 1056 */         if (enc != null) {
/* 1057 */           this.mqMessage.setEncoding(enc.intValue());
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1067 */         String charset = jmsMessage.getStringProperty("JMS_IBM_Character_Set");
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1072 */         if (charset == null) {
/* 1073 */           ccsid = dest.getIntProperty("CCSID");
/*      */         } else {
/* 1075 */           ccsid = MQJMSMessage.getCCSID(charset);
/*      */ 
/*      */           
/* 1078 */           if (ccsid == 0) {
/* 1079 */             JMSException je = ConfigEnvironment.newException("MQJMS1006", "JMS_IBM_Character_Set", charset);
/* 1080 */             if (Trace.isOn) {
/* 1081 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "sendInternal(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)", (Throwable)je, 5);
/*      */             }
/*      */ 
/*      */             
/* 1085 */             throw je;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 1090 */         boolean rfh2Required = isMQRFH2Required(dest);
/*      */ 
/*      */         
/* 1093 */         this.mqMessage.write(jmsMessage, rfh2Required, ccsid, (ProviderDestination)dest);
/*      */ 
/*      */         
/* 1096 */         if (destPri == -1) {
/* 1097 */           if (Trace.isOn) {
/* 1098 */             Trace.traceData(this, "sendInternal fixing MQMD for priority as qdef", null);
/*      */           }
/* 1100 */           this.mqMessage.setPriority(-1);
/*      */         } 
/*      */         
/* 1103 */         if (destPer == -1) {
/* 1104 */           if (Trace.isOn) {
/* 1105 */             Trace.traceData(this, "sendInternal fixing MQMD for persistence as qdef", null);
/*      */           }
/* 1107 */           this.mqMessage.setPersistence(2);
/* 1108 */         } else if (destPer == 3 && dest.getNPHighSupported()) {
/*      */           
/* 1110 */           this.mqMessage.setPersistence(0);
/*      */         }
/*      */       
/* 1113 */       } catch (JMSException e) {
/* 1114 */         if (Trace.isOn) {
/* 1115 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "sendInternal(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)", (Throwable)e, 3);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1122 */         if (Trace.isOn) {
/* 1123 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "sendInternal(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)", (Throwable)e, 6);
/*      */         }
/*      */ 
/*      */         
/* 1127 */         throw e;
/*      */       }
/* 1129 */       catch (Exception e) {
/* 1130 */         if (Trace.isOn) {
/* 1131 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "sendInternal(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)", e, 4);
/*      */         }
/*      */ 
/*      */         
/* 1135 */         e.printStackTrace(System.out);
/*      */ 
/*      */         
/* 1138 */         HashMap<String, Object> ffstData = new HashMap<>();
/* 1139 */         ffstData.put("Exception", e);
/* 1140 */         ffstData.put("Message", "MQJMS1016");
/* 1141 */         Trace.ffst(this, "sendInternal(MQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)", "02", ffstData, JMSException.class);
/*      */         
/* 1143 */         JMSException je = ConfigEnvironment.newException("MQJMS1016", e.toString());
/* 1144 */         je.setLinkedException(e);
/* 1145 */         if (Trace.isOn) {
/* 1146 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "sendInternal(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)", (Throwable)je, 7);
/*      */         }
/*      */ 
/*      */         
/* 1150 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1158 */         if (mqQueue == null) {
/* 1159 */           if (Trace.isOn) {
/* 1160 */             Trace.traceData(this, "put1 to " + dest, null);
/*      */           }
/* 1162 */           this.session.getQM().putMsg2(dest.getName(), dest.getStringProperty("XMSC_WMQ_QUEUE_MANAGER"), this.mqMessage, this.pmo);
/*      */         } else {
/* 1164 */           if (Trace.isOn) {
/* 1165 */             Trace.traceData(this, "send to " + mqQueue.name, null);
/*      */           }
/* 1167 */           mqQueue.putMsg2(this.mqMessage, this.pmo);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1175 */         if (transacted) {
/* 1176 */           this.session.setCommitRequired(true);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1182 */         this.mqMessage.setHeaderFromMQMD(message);
/*      */       }
/* 1184 */       catch (MQException e) {
/* 1185 */         JMSException je; if (Trace.isOn) {
/* 1186 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "sendInternal(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)", (Throwable)e, 5);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1191 */         if (e.reasonCode == 2052) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1196 */           String key = "MQJMS3001";
/* 1197 */           String msg = ConfigEnvironment.getErrorMessage(key);
/* 1198 */           InvalidDestinationException invalidDestinationException = new InvalidDestinationException(msg, key);
/*      */         } else {
/* 1200 */           je = ConfigEnvironment.newException("MQJMS2007");
/*      */         } 
/* 1202 */         je.setLinkedException((Exception)e);
/* 1203 */         if (Trace.isOn) {
/* 1204 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "sendInternal(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)", (Throwable)je, 8);
/*      */         }
/*      */ 
/*      */         
/* 1208 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1213 */       if (jmsMessage != message)
/*      */       {
/*      */         
/* 1216 */         message.setJMSDestinationAsString(dest.toURI());
/* 1217 */         message.setJMSDeliveryMode(deliveryMode);
/*      */         
/* 1219 */         message.setJMSTimestamp(sendTime);
/*      */ 
/*      */         
/* 1222 */         message.setJMSExpiration(jmsMessage.getJMSExpiration().longValue());
/*      */       }
/*      */     
/*      */     }
/* 1226 */     catch (JMSException je) {
/* 1227 */       if (Trace.isOn) {
/* 1228 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "sendInternal(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)", (Throwable)je, 6);
/*      */       }
/*      */ 
/*      */       
/* 1232 */       if (Trace.isOn) {
/* 1233 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "sendInternal(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)", (Throwable)je, 9);
/*      */       }
/*      */ 
/*      */       
/* 1237 */       throw je;
/*      */     } 
/*      */     
/* 1240 */     if (Trace.isOn) {
/* 1241 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "sendInternal(WMQDestination,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,ProviderMessage,int,int,long)");
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
/*      */   private void publishInt(ProviderMessage message, int deliveryMode, int priority, long timeToLive) throws JMSException {
/* 1274 */     if (Trace.isOn) {
/* 1275 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(ProviderMessage,int,int,long)", new Object[] { message, 
/*      */             
/* 1277 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), Long.valueOf(timeToLive) });
/*      */     }
/* 1279 */     if (Trace.isOn) {
/* 1280 */       Trace.traceData(this, "firstPublish is " + this.firstPublish, null);
/*      */     }
/* 1282 */     int savedMsgType = -1;
/* 1283 */     boolean requestAck = false;
/*      */ 
/*      */     
/*      */     try {
/* 1287 */       if (this.closed) {
/* 1288 */         String msg = ConfigEnvironment.getErrorMessage("MQJMS3028");
/* 1289 */         IllegalStateException traceRet1 = new IllegalStateException(msg);
/* 1290 */         if (Trace.isOn) {
/* 1291 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(ProviderMessage,int,int,long)", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/* 1294 */         throw traceRet1;
/*      */       } 
/*      */ 
/*      */       
/* 1298 */       if (this.TopicSpec == null) {
/* 1299 */         String msg = ConfigEnvironment.getErrorMessage("MQJMS1091");
/* 1300 */         UnsupportedOperationException uoe = new UnsupportedOperationException(msg);
/* 1301 */         if (Trace.isOn) {
/* 1302 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(ProviderMessage,int,int,long)", uoe, 2);
/*      */         }
/*      */         
/* 1305 */         throw uoe;
/*      */       } 
/*      */ 
/*      */       
/* 1309 */       validateParms(this.TopicSpec, message, deliveryMode, priority, timeToLive);
/*      */ 
/*      */       
/* 1312 */       if (this.mqPubQ == null) {
/*      */         
/* 1314 */         JMSException je = ConfigEnvironment.newException("MQJMS2001");
/* 1315 */         if (Trace.isOn) {
/* 1316 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(ProviderMessage,int,int,long)", (Throwable)je, 3);
/*      */         }
/*      */         
/* 1319 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 1323 */       if (this.session.usingAsyncMode() && !this.session.callingFromOnMessage() && this.session.isStarted()) {
/* 1324 */         if (Trace.isOn) {
/* 1325 */           Trace.traceData(this, "can't publish because session is actively using async delivery", null);
/*      */         }
/* 1327 */         JMSException je = ConfigEnvironment.newException("MQJMS1013");
/* 1328 */         if (Trace.isOn) {
/* 1329 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(ProviderMessage,int,int,long)", (Throwable)je, 4);
/*      */         }
/*      */         
/* 1332 */         throw je;
/*      */       } 
/*      */       
/* 1335 */       if (this.TopicSpec != null)
/*      */       {
/* 1337 */         if (this.TopicSpec.getIntProperty("failIfQuiesce") == 1) {
/* 1338 */           this.pmo.options |= 0x2000;
/* 1339 */           if (Trace.isOn) {
/* 1340 */             Trace.traceData(this, "FIQ behaviour YES", null);
/*      */           }
/*      */         } else {
/* 1343 */           this.pmo.options &= 0xFFFFDFFF;
/* 1344 */           if (Trace.isOn) {
/* 1345 */             Trace.traceData(this, "FIQ behaviour NO", null);
/*      */           }
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 1351 */       boolean transacted = this.session.getTransacted();
/*      */ 
/*      */       
/* 1354 */       if (transacted) {
/* 1355 */         if (Trace.isOn) {
/* 1356 */           Trace.traceData(this, "ProviderSession is transacted", null);
/*      */         }
/* 1358 */         this.pmo.options |= 0x2;
/* 1359 */         this.pmo.options &= 0xFFFFFFFB;
/*      */       } else {
/*      */         
/* 1362 */         if (Trace.isOn) {
/* 1363 */           Trace.traceData(this, "ProviderSession is NOT transacted", null);
/*      */         }
/* 1365 */         this.pmo.options |= 0x4;
/* 1366 */         this.pmo.options &= 0xFFFFFFFD;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 1371 */         if (this.session.getQM().getCommandLevel() > 700) {
/* 1372 */           this.pmo.options |= 0x20000;
/*      */         }
/*      */       }
/* 1375 */       catch (MQException me) {
/* 1376 */         if (Trace.isOn) {
/* 1377 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(ProviderMessage,int,int,long)", (Throwable)me, 1);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1384 */       buildBaseMessage(this.TopicSpec, message, deliveryMode, priority, timeToLive);
/*      */ 
/*      */       
/* 1387 */       if (this.firstMsg && !transacted) {
/* 1388 */         if (Trace.isOn) {
/* 1389 */           Trace.traceData(this, "First message on this publisher", null);
/*      */         }
/* 1391 */         requestAck = true;
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1396 */       else if (!this.firstMsg && this.session.responseInterval > 0 && this.session.getServicesMgr().requestResponse(this.session) == true) {
/* 1397 */         requestAck = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1402 */       if (this.baseMessage.getReport() != 0) {
/* 1403 */         requestAck = false;
/*      */       }
/*      */       
/* 1406 */       if (requestAck) {
/* 1407 */         if (Trace.isOn) {
/* 1408 */           Trace.traceData(this, "Requesting a Broker response", null);
/*      */         }
/*      */ 
/*      */         
/* 1412 */         savedMsgType = this.baseMessage.getMessageType();
/*      */ 
/*      */         
/* 1415 */         this.baseMessage.setReport(3);
/* 1416 */         this.baseMessage.setReplyToQueueName("SYSTEM.JMS.REPORT.QUEUE");
/* 1417 */         this.baseMessage.setReplyToQueueManagerName("");
/*      */       }
/* 1419 */       else if (this.baseMessage.getReport() == 0) {
/*      */ 
/*      */ 
/*      */         
/* 1423 */         this.baseMessage.setMessageType(8);
/*      */       } 
/*      */ 
/*      */       
/* 1427 */       if (this.session.getSessionName() != null && 
/* 1428 */         null == message.getJMSCorrelationID())
/*      */       {
/*      */ 
/*      */         
/* 1432 */         this.baseMessage.setCorrelationId(this.session.getSessionName());
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1438 */       if (this.firstPublish) {
/* 1439 */         this.session.addPubSubServices();
/* 1440 */         this.firstPublish = false;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1448 */         if (this.session.getAcknowledgeMode() == 0 && this.session.getOptimisticPublication() && this.session.getQM().spiSupportsInherited()) {
/*      */           
/* 1450 */           this.mqPubQ.spiPut(this.baseMessage, this.pmo, 32);
/*      */         } else {
/* 1452 */           this.mqPubQ.putMsg2(this.baseMessage, this.pmo);
/*      */         } 
/*      */         
/* 1455 */         if (transacted) {
/* 1456 */           this.session.setCommitRequired(true);
/*      */         
/*      */         }
/*      */       }
/* 1460 */       catch (MQException e) {
/* 1461 */         if (Trace.isOn) {
/* 1462 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(ProviderMessage,int,int,long)", (Throwable)e, 2);
/*      */         }
/*      */ 
/*      */         
/* 1466 */         JMSException je = ConfigEnvironment.newException("MQJMS3011");
/* 1467 */         je.setLinkedException((Exception)e);
/* 1468 */         if (Trace.isOn) {
/* 1469 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(ProviderMessage,int,int,long)", (Throwable)je, 5);
/*      */         }
/*      */         
/* 1472 */         throw je;
/*      */       } 
/*      */       
/* 1475 */       if (requestAck) {
/*      */ 
/*      */         
/* 1478 */         if (!this.firstMsg)
/*      */         {
/* 1480 */           this.session.responseRequested(System.currentTimeMillis(), this.baseMessage.getMessageId());
/*      */         }
/*      */         
/* 1483 */         this.baseMessage.setMessageType(savedMsgType);
/* 1484 */         this.baseMessage.setReport(0);
/* 1485 */         this.baseMessage.setReplyToQueueName("");
/* 1486 */         this.baseMessage.setReplyToQueueManagerName("");
/*      */       } 
/*      */ 
/*      */       
/* 1490 */       this.baseMessage.setHeaderFromMQMD(message);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1495 */       if ((this.firstMsg && requestAck) || this.session.getServicesMgr().checkForResponse(this.session)) {
/* 1496 */         if (Trace.isOn) {
/* 1497 */           Trace.traceData(this, "Checking for a Broker response", null);
/*      */         }
/*      */ 
/*      */         
/* 1501 */         checkBrokerResponse();
/* 1502 */         if (this.firstMsg)
/*      */         {
/* 1504 */           SubscriptionHelper.checkResponse(this.baseMessage);
/*      */         }
/*      */       } 
/*      */       
/* 1508 */       if (this.firstMsg) {
/* 1509 */         this.firstMsg = false;
/*      */       }
/*      */     }
/* 1512 */     catch (MQException e) {
/* 1513 */       if (Trace.isOn) {
/* 1514 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(ProviderMessage,int,int,long)", (Throwable)e, 3);
/*      */       }
/*      */ 
/*      */       
/* 1518 */       JMSException je = ConfigEnvironment.newException("MQJMS3011");
/* 1519 */       je.setLinkedException((Exception)e);
/* 1520 */       if (Trace.isOn) {
/* 1521 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(ProviderMessage,int,int,long)", (Throwable)je, 6);
/*      */       }
/*      */       
/* 1524 */       throw je;
/*      */     }
/* 1526 */     catch (JMSException je) {
/* 1527 */       if (Trace.isOn) {
/* 1528 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(ProviderMessage,int,int,long)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 1531 */       if (Trace.isOn) {
/* 1532 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(ProviderMessage,int,int,long)", (Throwable)je, 7);
/*      */       }
/*      */       
/* 1535 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/* 1539 */     if (Trace.isOn) {
/* 1540 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(ProviderMessage,int,int,long)");
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
/*      */   private void publishInt(WMQDestination topic, ProviderMessage message, int deliveryMode, int priority, long timeToLive) throws JMSException {
/* 1577 */     if (Trace.isOn) {
/* 1578 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(WMQDestination,ProviderMessage,int,int,long)", new Object[] { topic, message, 
/*      */             
/* 1580 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), Long.valueOf(timeToLive) });
/*      */     }
/*      */     
/* 1583 */     int savedMsgType = -1;
/* 1584 */     boolean requestAck = false;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1589 */       if (this.closed) {
/* 1590 */         String msg = ConfigEnvironment.getErrorMessage("MQJMS3028");
/* 1591 */         IllegalStateException traceRet1 = new IllegalStateException(msg);
/* 1592 */         if (Trace.isOn) {
/* 1593 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(WMQDestination,ProviderMessage,int,int,long)", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/* 1596 */         throw traceRet1;
/*      */       } 
/*      */ 
/*      */       
/* 1600 */       if (this.TopicSpec != null) {
/* 1601 */         String msg = ConfigEnvironment.getErrorMessage("MQJMS1014");
/* 1602 */         UnsupportedOperationException uoe = new UnsupportedOperationException(msg);
/* 1603 */         if (Trace.isOn) {
/* 1604 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(WMQDestination,ProviderMessage,int,int,long)", uoe, 2);
/*      */         }
/*      */         
/* 1607 */         throw uoe;
/*      */       } 
/*      */ 
/*      */       
/* 1611 */       if (this.firstPublish) {
/* 1612 */         this.session.addPubSubServices();
/* 1613 */         this.firstPublish = false;
/*      */       } 
/*      */ 
/*      */       
/* 1617 */       validateParms(topic, message, deliveryMode, priority, timeToLive);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1624 */       String brokerPubQueue = this.session.connection.getBrkPubQueue();
/* 1625 */       String topicBrokerPubQ = topic.getStringProperty("XMSC_WMQ_BROKER_PUBQ");
/* 1626 */       String topicBrokerQMgr = topic.getStringProperty("XMSC_WMQ_BROKER_QMGR");
/*      */       
/* 1628 */       boolean change = false;
/*      */       
/* 1630 */       if (topicBrokerPubQ != null && topicBrokerPubQ.length() != 0 && !brokerPubQueue.equals(topicBrokerPubQ)) {
/* 1631 */         brokerPubQueue = topicBrokerPubQ;
/* 1632 */         change = true;
/*      */       } 
/*      */       
/* 1635 */       if (topicBrokerQMgr != null && topicBrokerQMgr.length() != 0 && !this.brokerPubQMgr.equals(topicBrokerQMgr)) {
/* 1636 */         this.brokerPubQMgr = topicBrokerQMgr;
/* 1637 */         change = true;
/*      */       } 
/* 1639 */       if (change || this.mqPubQ == null) {
/*      */         try {
/* 1641 */           this.mqPubQ = this.qm.accessQueue(brokerPubQueue, 48, this.brokerPubQMgr, null, null);
/*      */         }
/* 1643 */         catch (MQException e) {
/* 1644 */           if (Trace.isOn) {
/* 1645 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(WMQDestination,ProviderMessage,int,int,long)", (Throwable)e, 1);
/*      */           }
/*      */           
/* 1648 */           if (Trace.isOn) {
/* 1649 */             Trace.traceData(this, "publish failed to access publish queue", null);
/*      */           }
/* 1651 */           JMSException je = ConfigEnvironment.newException("MQJMS2008", brokerPubQueue);
/* 1652 */           je.setLinkedException((Exception)e);
/* 1653 */           if (Trace.isOn) {
/* 1654 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(WMQDestination,ProviderMessage,int,int,long)", (Throwable)je, 3);
/*      */           }
/*      */           
/* 1657 */           throw je;
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 1662 */       if (!this.session.callingFromOnMessage() && this.session.usingAsyncMode() && this.session.isStarted()) {
/* 1663 */         if (Trace.isOn) {
/* 1664 */           Trace.traceData(this, "can't publish because session is actively using async delivery", null);
/*      */         }
/* 1666 */         JMSException je = ConfigEnvironment.newException("MQJMS1013");
/* 1667 */         if (Trace.isOn) {
/* 1668 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(WMQDestination,ProviderMessage,int,int,long)", (Throwable)je, 5);
/*      */         }
/*      */         
/* 1671 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 1675 */       if (topic.getIntProperty("failIfQuiesce") == 1) {
/* 1676 */         this.pmo.options |= 0x2000;
/* 1677 */         if (Trace.isOn) {
/* 1678 */           Trace.traceData(this, "FIQ behaviour YES", null);
/*      */         }
/*      */       } else {
/* 1681 */         this.pmo.options &= 0xFFFFDFFF;
/* 1682 */         if (Trace.isOn) {
/* 1683 */           Trace.traceData(this, "FIQ behaviour NO", null);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1688 */       boolean transacted = this.session.getTransacted();
/*      */ 
/*      */       
/* 1691 */       if (transacted) {
/* 1692 */         if (Trace.isOn) {
/* 1693 */           Trace.traceData(this, "ProviderSession is transacted", null);
/*      */         }
/* 1695 */         this.pmo.options |= 0x2;
/* 1696 */         this.pmo.options &= 0xFFFFFFFB;
/*      */       } else {
/*      */         
/* 1699 */         if (Trace.isOn) {
/* 1700 */           Trace.traceData(this, "ProviderSession is NOT transacted", null);
/*      */         }
/* 1702 */         this.pmo.options |= 0x4;
/* 1703 */         this.pmo.options &= 0xFFFFFFFD;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 1708 */         if (this.session.getQM().getCommandLevel() > 700) {
/* 1709 */           this.pmo.options |= 0x20000;
/*      */         }
/*      */       }
/* 1712 */       catch (MQException me) {
/* 1713 */         if (Trace.isOn) {
/* 1714 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(WMQDestination,ProviderMessage,int,int,long)", (Throwable)me, 2);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1721 */       buildBaseMessage(topic, message, deliveryMode, priority, timeToLive);
/*      */ 
/*      */       
/* 1724 */       if (this.firstMsg && !transacted) {
/* 1725 */         if (Trace.isOn) {
/* 1726 */           Trace.traceData(this, "First message on this publisher", null);
/*      */         }
/* 1728 */         requestAck = true;
/*      */       
/*      */       }
/* 1731 */       else if (!this.firstMsg && this.session.responseInterval > 0 && this.session.getServicesMgr().requestResponse(this.session) == true) {
/* 1732 */         requestAck = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1737 */       if (this.baseMessage.getReport() != 0) {
/* 1738 */         requestAck = false;
/*      */       }
/*      */       
/* 1741 */       if (requestAck) {
/* 1742 */         if (Trace.isOn) {
/* 1743 */           Trace.traceData(this, "Requesting a Broker response", null);
/*      */         }
/*      */ 
/*      */         
/* 1747 */         savedMsgType = this.baseMessage.getMessageType();
/*      */ 
/*      */         
/* 1750 */         this.baseMessage.setReport(3);
/* 1751 */         this.baseMessage.setReplyToQueueName("SYSTEM.JMS.REPORT.QUEUE");
/* 1752 */         this.baseMessage.setReplyToQueueManagerName("");
/* 1753 */       } else if (this.baseMessage.getReport() == 0) {
/*      */ 
/*      */ 
/*      */         
/* 1757 */         this.baseMessage.setMessageType(8);
/*      */       } 
/*      */ 
/*      */       
/* 1761 */       if (this.session.getSessionName() != null && 
/* 1762 */         null == message.getJMSCorrelationID())
/*      */       {
/*      */         
/* 1765 */         this.baseMessage.setCorrelationId(this.session.getSessionName());
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1775 */         if (this.session.getAcknowledgeMode() == 0 && this.session.getOptimisticPublication() && this.session.getQM().spiSupportsInherited()) {
/* 1776 */           this.mqPubQ.spiPut(this.baseMessage, this.pmo, 32);
/*      */         } else {
/* 1778 */           this.mqPubQ.putMsg2(this.baseMessage, this.pmo);
/*      */         } 
/*      */         
/* 1781 */         if (transacted) {
/* 1782 */           this.session.setCommitRequired(true);
/*      */         }
/*      */       }
/* 1785 */       catch (MQException e) {
/* 1786 */         if (Trace.isOn) {
/* 1787 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(WMQDestination,ProviderMessage,int,int,long)", (Throwable)e, 3);
/*      */         }
/*      */ 
/*      */         
/* 1791 */         JMSException je = ConfigEnvironment.newException("MQJMS3011");
/* 1792 */         je.setLinkedException((Exception)e);
/* 1793 */         if (Trace.isOn) {
/* 1794 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(WMQDestination,ProviderMessage,int,int,long)", (Throwable)je, 6);
/*      */         }
/*      */         
/* 1797 */         throw je;
/*      */       } 
/*      */       
/* 1800 */       if (requestAck) {
/*      */ 
/*      */         
/* 1803 */         if (!this.firstMsg)
/*      */         {
/* 1805 */           this.session.responseRequested(System.currentTimeMillis(), this.baseMessage.getMessageId());
/*      */         }
/*      */         
/* 1808 */         this.baseMessage.setMessageType(savedMsgType);
/* 1809 */         this.baseMessage.setReport(0);
/* 1810 */         this.baseMessage.setReplyToQueueName("");
/* 1811 */         this.baseMessage.setReplyToQueueManagerName("");
/*      */       } 
/*      */ 
/*      */       
/* 1815 */       this.baseMessage.setHeaderFromMQMD(message);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1820 */       if ((this.firstMsg && requestAck) || this.session.getServicesMgr().checkForResponse(this.session)) {
/*      */         
/* 1822 */         if (Trace.isOn) {
/* 1823 */           Trace.traceData(this, "Checking for a Broker response", null);
/*      */         }
/*      */ 
/*      */         
/* 1827 */         checkBrokerResponse();
/* 1828 */         if (this.firstMsg)
/*      */         {
/* 1830 */           SubscriptionHelper.checkResponse(this.baseMessage);
/*      */         }
/*      */       } 
/*      */       
/* 1834 */       if (this.firstMsg) {
/* 1835 */         this.firstMsg = false;
/*      */       
/*      */       }
/*      */     }
/* 1839 */     catch (MQException e) {
/* 1840 */       if (Trace.isOn) {
/* 1841 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(WMQDestination,ProviderMessage,int,int,long)", (Throwable)e, 4);
/*      */       }
/*      */ 
/*      */       
/* 1845 */       JMSException je = ConfigEnvironment.newException("MQJMS3011");
/* 1846 */       je.setLinkedException((Exception)e);
/*      */     }
/* 1848 */     catch (JMSException je) {
/* 1849 */       if (Trace.isOn) {
/* 1850 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(WMQDestination,ProviderMessage,int,int,long)", (Throwable)je, 5);
/*      */       }
/*      */       
/* 1853 */       if (Trace.isOn) {
/* 1854 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(WMQDestination,ProviderMessage,int,int,long)", (Throwable)je, 7);
/*      */       }
/*      */       
/* 1857 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/* 1861 */     if (Trace.isOn) {
/* 1862 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "publishInt(WMQDestination,ProviderMessage,int,int,long)");
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
/*      */   private void validateParms(WMQDestination topic, ProviderMessage message, int deliveryMode, int priority, long timeToLive) throws JMSException {
/* 1879 */     if (Trace.isOn) {
/* 1880 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "validateParms(WMQDestination,ProviderMessage,int,int,long)", new Object[] { topic, message, 
/*      */             
/* 1882 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), Long.valueOf(timeToLive) });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1888 */       if (deliveryMode != 2 && deliveryMode != 1 && deliveryMode != -1) {
/* 1889 */         JMSException je = ConfigEnvironment.newException("MQJMS1006", "Delivery Mode", String.valueOf(deliveryMode));
/* 1890 */         if (Trace.isOn) {
/* 1891 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "validateParms(WMQDestination,ProviderMessage,int,int,long)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 1894 */         throw je;
/*      */       } 
/*      */       
/* 1897 */       if (priority != -1 && (priority < 0 || priority > 9)) {
/* 1898 */         JMSException je = ConfigEnvironment.newException("MQJMS1006", "Priority", String.valueOf(priority));
/* 1899 */         if (Trace.isOn) {
/* 1900 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "validateParms(WMQDestination,ProviderMessage,int,int,long)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 1903 */         throw je;
/*      */       } 
/*      */       
/* 1906 */       if (timeToLive != -1L && timeToLive < 0L) {
/* 1907 */         JMSException je = ConfigEnvironment.newException("MQJMS1006", "Time to Live", String.valueOf(timeToLive));
/* 1908 */         if (Trace.isOn) {
/* 1909 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "validateParms(WMQDestination,ProviderMessage,int,int,long)", (Throwable)je, 3);
/*      */         }
/*      */         
/* 1912 */         throw je;
/*      */       }
/*      */     
/* 1915 */     } catch (JMSException je2) {
/* 1916 */       if (Trace.isOn) {
/* 1917 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "validateParms(WMQDestination,ProviderMessage,int,int,long)", (Throwable)je2);
/*      */       }
/*      */       
/* 1920 */       if (Trace.isOn) {
/* 1921 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "validateParms(WMQDestination,ProviderMessage,int,int,long)", (Throwable)je2, 4);
/*      */       }
/*      */       
/* 1924 */       throw je2;
/*      */     } 
/*      */     
/* 1927 */     if (Trace.isOn) {
/* 1928 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "validateParms(WMQDestination,ProviderMessage,int,int,long)");
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
/*      */   private void buildBaseMessage(WMQDestination topic, ProviderMessage message, int deliveryMode, int priority, long timeToLive) throws JMSException {
/* 1947 */     if (Trace.isOn) {
/* 1948 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "buildBaseMessage(WMQDestination,ProviderMessage,int,int,long)", new Object[] { topic, message, 
/*      */             
/* 1950 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), Long.valueOf(timeToLive) });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*      */       JMSMessage jmsMessage;
/*      */ 
/*      */       
/* 1958 */       if (message instanceof JMSMessage) {
/*      */         
/* 1960 */         jmsMessage = (JMSMessage)message;
/*      */       } else {
/*      */         
/* 1963 */         if (Trace.isOn) {
/* 1964 */           Trace.traceData(this, "buildBaseMessage publishing alien message", null);
/*      */         }
/* 1966 */         jmsMessage = JMSMessage._copyFromMessage(this.session, message);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1971 */       long sendTime = System.currentTimeMillis();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1976 */       this.baseMessage.clearMessageData();
/*      */ 
/*      */ 
/*      */       
/* 1980 */       this.baseMessage.setEncoding(topic.getIntProperty("encoding"));
/*      */ 
/*      */       
/* 1983 */       this.baseMessage.setCharacterSet(this.qm._getConnectionCCSID());
/*      */ 
/*      */       
/* 1986 */       jmsMessage.setJMSDestinationAsString(topic.toURI());
/*      */ 
/*      */       
/* 1989 */       this.baseMessage.setPersistenceFromMD(this.session.getPersistenceFromMD());
/*      */ 
/*      */       
/* 1992 */       long destExp = topic.getLongProperty("timeToLive");
/* 1993 */       if (destExp == -2L) {
/* 1994 */         if (timeToLive == -1L) {
/* 1995 */           jmsMessage._setTimeToLive(sendTime, getTimeToLive());
/*      */         } else {
/* 1997 */           jmsMessage._setTimeToLive(sendTime, timeToLive);
/*      */         } 
/*      */       } else {
/* 2000 */         jmsMessage._setTimeToLive(sendTime, destExp);
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
/* 2012 */       int destPri = topic.getIntProperty("priority");
/* 2013 */       if (destPri == -2) {
/* 2014 */         if (priority == -1) {
/* 2015 */           jmsMessage.setJMSPriority(getPriority());
/*      */         } else {
/* 2017 */           jmsMessage.setJMSPriority(priority);
/*      */         } 
/* 2019 */       } else if (destPri == -1) {
/* 2020 */         jmsMessage.setJMSPriority(4);
/*      */       } else {
/* 2022 */         jmsMessage.setJMSPriority(destPri);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2029 */       if (topic.getIntProperty("deliveryMode") == 3 && !topic.isNPHighCheckDone()) {
/* 2030 */         topic.setNPHighSupported((!this.session.getPersistenceFromMD() && topic.getIntProperty("targetClient") != 1 && topic
/* 2031 */             .getIntProperty("messageBody") != 1 && this.mqPubQ.isNPMClassHigh()));
/*      */       }
/*      */       
/* 2034 */       int destPer = topic.getIntProperty("deliveryMode");
/* 2035 */       if (destPer == -2 || destPer == 3) {
/* 2036 */         if (deliveryMode == -1) {
/* 2037 */           jmsMessage.setJMSDeliveryMode(getDeliveryMode());
/*      */         } else {
/* 2039 */           jmsMessage.setJMSDeliveryMode(deliveryMode);
/*      */         } 
/* 2041 */       } else if (destPer == -1) {
/* 2042 */         jmsMessage.setJMSDeliveryMode(-2);
/*      */       } else {
/* 2044 */         jmsMessage.setJMSDeliveryMode(destPer);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2054 */       String directive = "UNIQUE_CONNECTION_ID " + this.session.getConnectionID() + " " + "MQPSCommand Publish MQPSTopic " + quoteTopicName(topic) + " MQPSPubOpts NoReg";
/*      */       try {
/*      */         int ccsid;
/* 2057 */         jmsMessage.setJMSDestinationAsString(topic.toURI());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2068 */         Integer enc = (Integer)jmsMessage.getObjectProperty("JMS_IBM_Encoding");
/* 2069 */         if (enc != null) {
/* 2070 */           this.baseMessage.setEncoding(enc.intValue());
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2080 */         String charset = jmsMessage.getStringProperty("JMS_IBM_Character_Set");
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2085 */         if (charset == null) {
/* 2086 */           ccsid = topic.getIntProperty("CCSID");
/*      */         } else {
/* 2088 */           ccsid = MQJMSMessage.getCCSID(charset);
/*      */ 
/*      */           
/* 2091 */           if (ccsid == 0) {
/* 2092 */             JMSException bve = ConfigEnvironment.newException("MQJMS1006", "JMS_IBM_Character_Set", charset);
/* 2093 */             if (Trace.isOn) {
/* 2094 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "buildBaseMessage(WMQDestination,ProviderMessage,int,int,long)", (Throwable)bve, 1);
/*      */             }
/*      */             
/* 2097 */             throw bve;
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2104 */         if (this.session.getConnectionBrokerVersion() == 1) {
/* 2105 */           jmsMessage.setPSCTopic(topic.getStringProperty("XMSC_DESTINATION_NAME"));
/* 2106 */           jmsMessage.setPSCConnID(this.session.getConnectionID());
/* 2107 */           this.baseMessage.write(jmsMessage, true, ccsid, (ProviderDestination)topic);
/*      */         }
/*      */         else {
/*      */           
/* 2111 */           boolean rfh2Required = isMQRFH2Required(topic);
/*      */           
/* 2113 */           if (rfh2Required) {
/*      */ 
/*      */ 
/*      */             
/* 2117 */             buildBaseMessageWithCachedRFHAndRFH2(directive, jmsMessage, ccsid, (ProviderDestination)topic);
/*      */           } else {
/*      */             
/* 2120 */             this.baseMessage.writeRFH(directive, jmsMessage, false, ccsid, (ProviderDestination)topic);
/*      */           }
/*      */         
/*      */         } 
/* 2124 */       } catch (JMSException e) {
/* 2125 */         if (Trace.isOn) {
/* 2126 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "buildBaseMessage(WMQDestination,ProviderMessage,int,int,long)", (Throwable)e, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2132 */         if (Trace.isOn) {
/* 2133 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "buildBaseMessage(WMQDestination,ProviderMessage,int,int,long)", (Throwable)e, 2);
/*      */         }
/*      */         
/* 2136 */         throw e;
/*      */       
/*      */       }
/* 2139 */       catch (Exception e) {
/* 2140 */         if (Trace.isOn) {
/* 2141 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "buildBaseMessage(WMQDestination,ProviderMessage,int,int,long)", e, 2);
/*      */         }
/*      */         
/* 2144 */         JMSException je = ConfigEnvironment.newException("MQJMS3010");
/* 2145 */         je.setLinkedException(e);
/* 2146 */         if (Trace.isOn) {
/* 2147 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "buildBaseMessage(WMQDestination,ProviderMessage,int,int,long)", (Throwable)je, 3);
/*      */         }
/*      */         
/* 2150 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 2154 */       if (destPri == -1) {
/* 2155 */         if (Trace.isOn) {
/* 2156 */           Trace.traceData(this, "buildBaseMessage: MQMD priority set as QDEF", null);
/*      */         }
/* 2158 */         this.baseMessage.setPriority(-1);
/*      */       } 
/*      */       
/* 2161 */       if (destPer == -1) {
/* 2162 */         if (Trace.isOn) {
/* 2163 */           Trace.traceData(this, "buildBaseMessage: MQMD persistence set as QDEF", null);
/*      */         }
/* 2165 */         this.baseMessage.setPersistence(2);
/* 2166 */       } else if (destPer == 3 && topic.getNPHighSupported()) {
/*      */         
/* 2168 */         this.baseMessage.setPersistence(0);
/*      */       } 
/*      */ 
/*      */       
/* 2172 */       if (this.session.getConnectionBrokerVersion() == 1) {
/* 2173 */         this.baseMessage.setFormat("MQHRF2  ");
/*      */       } else {
/* 2175 */         this.baseMessage.setFormat("MQHRF   ");
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2181 */       this.baseMessage.setGroupId(MQC.MQGI_NONE);
/* 2182 */       this.baseMessage.setMessageSequenceNumber(1);
/* 2183 */       this.baseMessage.setMessageFlags(this.baseMessage.getMessageFlags() & 0xFFFFFFF7);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2194 */       if (jmsMessage != message)
/*      */       {
/*      */         
/* 2197 */         message.setJMSDestinationAsString(topic.toURI());
/* 2198 */         message.setJMSDeliveryMode(deliveryMode);
/*      */         
/* 2200 */         message.setJMSTimestamp(sendTime);
/*      */ 
/*      */         
/* 2203 */         message.setJMSExpiration(jmsMessage.getJMSExpiration().longValue());
/*      */       }
/*      */     
/* 2206 */     } catch (MQException e) {
/* 2207 */       if (Trace.isOn) {
/* 2208 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "buildBaseMessage(WMQDestination,ProviderMessage,int,int,long)", (Throwable)e, 3);
/*      */       }
/*      */ 
/*      */       
/* 2212 */       JMSException je2 = new JMSException("MQJMS1000");
/* 2213 */       je2.setLinkedException((Exception)e);
/* 2214 */       je2.initCause((Throwable)e);
/* 2215 */       if (Trace.isOn) {
/* 2216 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "buildBaseMessage(WMQDestination,ProviderMessage,int,int,long)", (Throwable)je2, 4);
/*      */       }
/*      */       
/* 2219 */       throw je2;
/*      */     }
/* 2221 */     catch (JMSException je2) {
/* 2222 */       if (Trace.isOn) {
/* 2223 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "buildBaseMessage(WMQDestination,ProviderMessage,int,int,long)", (Throwable)je2, 4);
/*      */       }
/*      */       
/* 2226 */       if (Trace.isOn) {
/* 2227 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "buildBaseMessage(WMQDestination,ProviderMessage,int,int,long)", (Throwable)je2, 5);
/*      */       }
/*      */       
/* 2230 */       throw je2;
/*      */     } 
/*      */     
/* 2233 */     if (Trace.isOn) {
/* 2234 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "buildBaseMessage(WMQDestination,ProviderMessage,int,int,long)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String quoteTopicName(WMQDestination topic) {
/* 2241 */     if (Trace.isOn) {
/* 2242 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "quoteTopicName(WMQDestination)", new Object[] { topic });
/*      */     }
/*      */     
/* 2245 */     StringBuffer topicBuffer = new StringBuffer();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2250 */     RFH1BrokerMessageImpl.formatNameValueValue(topicBuffer, topic.getName());
/*      */     
/* 2252 */     String traceRet1 = topicBuffer.toString();
/* 2253 */     if (Trace.isOn) {
/* 2254 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "quoteTopicName(WMQDestination)", traceRet1);
/*      */     }
/*      */     
/* 2257 */     return traceRet1;
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
/*      */   private void buildBaseMessageWithCachedRFHAndRFH2(String directive, JMSMessage jmsMsg, int ccsid, ProviderDestination destination) throws JMSException {
/* 2275 */     if (Trace.isOn) {
/* 2276 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "buildBaseMessageWithCachedRFHAndRFH2(String,JMSMessage,int,ProviderDestination)", new Object[] { directive, jmsMsg, 
/*      */             
/* 2278 */             Integer.valueOf(ccsid), destination });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2284 */       if (this.TopicSpec != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2291 */         if (this.cachedRFHData == null || (this.cachedBaseTopic != null && !this.cachedBaseTopic.equals(this.TopicSpec.getStringProperty("XMSC_DESTINATION_NAME"))) || this.cachedEncoding != this.baseMessage
/* 2292 */           .getEncoding()) {
/*      */           
/* 2294 */           this.cachedRFHData = this.baseMessage.buildCacheableRFH(directive, ccsid, this.baseMessage.getEncoding());
/* 2295 */           this.cachedBaseTopic = this.TopicSpec.getStringProperty("XMSC_DESTINATION_NAME");
/* 2296 */           this.cachedEncoding = this.baseMessage.getEncoding();
/*      */         } 
/*      */ 
/*      */         
/* 2300 */         this.baseMessage.appendByteArray(this.cachedRFHData);
/* 2301 */         this.baseMessage.write(jmsMsg, true, ccsid, (ProviderDestination)this.TopicSpec);
/*      */       }
/*      */       else
/*      */       {
/* 2305 */         this.baseMessage.writeRFH(directive, jmsMsg, true, ccsid, destination);
/*      */       }
/*      */     
/* 2308 */     } catch (IOException e) {
/* 2309 */       if (Trace.isOn) {
/* 2310 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "buildBaseMessageWithCachedRFHAndRFH2(String,JMSMessage,int,ProviderDestination)", e, 1);
/*      */       }
/*      */ 
/*      */       
/* 2314 */       JMSException je = new JMSException("MQJMS1000");
/* 2315 */       je.setLinkedException(e);
/* 2316 */       je.initCause(e);
/* 2317 */       if (Trace.isOn) {
/* 2318 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "buildBaseMessageWithCachedRFHAndRFH2(String,JMSMessage,int,ProviderDestination)", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */       
/* 2322 */       throw je;
/*      */     }
/* 2324 */     catch (MQException e) {
/* 2325 */       if (Trace.isOn) {
/* 2326 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "buildBaseMessageWithCachedRFHAndRFH2(String,JMSMessage,int,ProviderDestination)", (Throwable)e, 2);
/*      */       }
/*      */ 
/*      */       
/* 2330 */       JMSException je = new JMSException("MQJMS1000");
/* 2331 */       je.setLinkedException((Exception)e);
/* 2332 */       je.initCause((Throwable)e);
/* 2333 */       if (Trace.isOn) {
/* 2334 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "buildBaseMessageWithCachedRFHAndRFH2(String,JMSMessage,int,ProviderDestination)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */       
/* 2338 */       throw je;
/*      */     } 
/* 2340 */     if (Trace.isOn) {
/* 2341 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "buildBaseMessageWithCachedRFHAndRFH2(String,JMSMessage,int,ProviderDestination)");
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
/*      */   private void checkBrokerResponse() throws JMSException {
/* 2355 */     if (Trace.isOn) {
/* 2356 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "checkBrokerResponse()");
/*      */     }
/*      */     
/*      */     try {
/* 2360 */       this.session.getServicesMgr().getBrokerResponse(this.session, this.baseMessage, this.firstMsg);
/*      */     }
/* 2362 */     catch (JMSException e) {
/* 2363 */       if (Trace.isOn) {
/* 2364 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "checkBrokerResponse()", (Throwable)e);
/*      */       }
/*      */       
/* 2367 */       if (Trace.isOn) {
/* 2368 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "checkBrokerResponse()", (Throwable)e);
/*      */       }
/*      */       
/* 2371 */       throw e;
/*      */     } 
/* 2373 */     if (Trace.isOn) {
/* 2374 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "checkBrokerResponse()");
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
/*      */   private void send(ProviderDestination destination, ProviderMessage message, int deliveryMode, int priority, long timeToLive) throws JMSException {
/* 2425 */     if (Trace.isOn) {
/* 2426 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderDestination,ProviderMessage,int,int,long)", new Object[] { destination, message, 
/*      */             
/* 2428 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), Long.valueOf(timeToLive) });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 2433 */       if (destination == null) {
/* 2434 */         send(message, deliveryMode, priority, timeToLive);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 2439 */       else if (destination.isQueue()) {
/* 2440 */         sendInternal((WMQDestination)destination, (MQQueue)null, message, deliveryMode, priority, timeToLive);
/* 2441 */       } else if (destination.isTopic()) {
/* 2442 */         publishInt((WMQDestination)destination, message, deliveryMode, priority, timeToLive);
/*      */       } else {
/* 2444 */         String key = "MQJMS0003";
/* 2445 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 2446 */         InvalidDestinationException je = new InvalidDestinationException(msg, key);
/* 2447 */         if (Trace.isOn) {
/* 2448 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderDestination,ProviderMessage,int,int,long)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 2451 */         throw je;
/*      */       }
/*      */     
/*      */     }
/* 2455 */     catch (JMSException je) {
/* 2456 */       if (Trace.isOn) {
/* 2457 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderDestination,ProviderMessage,int,int,long)", (Throwable)je);
/*      */       }
/*      */       
/* 2460 */       if (Trace.isOn) {
/* 2461 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderDestination,ProviderMessage,int,int,long)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 2464 */       throw je;
/*      */     } 
/* 2466 */     if (Trace.isOn) {
/* 2467 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderDestination,ProviderMessage,int,int,long)");
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
/*      */   public void send(ProviderDestination destination, ProviderMessage message) throws JMSException {
/* 2513 */     if (Trace.isOn) {
/* 2514 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderDestination,ProviderMessage)", new Object[] { destination, message });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2524 */       int localDeliveryMode = message.getJMSDeliveryMode().intValue();
/* 2525 */       int localPriority = message.getJMSPriority().intValue();
/* 2526 */       long localExpiration = message.getJMSExpiration().longValue();
/* 2527 */       long localTimestamp = message.getJMSTimestamp().longValue();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2532 */       if (localTimestamp == 0L) {
/* 2533 */         localTimestamp = System.currentTimeMillis();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2539 */       long localTimeToLive = 0L;
/* 2540 */       if (localExpiration > 0L) {
/* 2541 */         localTimeToLive = localExpiration - localTimestamp;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2546 */       if (localTimeToLive < 0L) {
/* 2547 */         localTimeToLive = 1L;
/*      */       }
/*      */ 
/*      */       
/* 2551 */       if (destination == null) {
/* 2552 */         send(message, localDeliveryMode, localPriority, localTimeToLive);
/*      */       
/*      */       }
/* 2555 */       else if (destination.isQueue()) {
/* 2556 */         send(destination, message, localDeliveryMode, localPriority, localTimeToLive);
/* 2557 */       } else if (destination.isTopic()) {
/* 2558 */         publishInt((WMQDestination)destination, message, localDeliveryMode, localPriority, localTimeToLive);
/*      */       } else {
/* 2560 */         String key = "MQJMS0003";
/* 2561 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 2562 */         InvalidDestinationException je = new InvalidDestinationException(msg, key);
/* 2563 */         if (Trace.isOn) {
/* 2564 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderDestination,ProviderMessage)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 2567 */         throw je;
/*      */       }
/*      */     
/*      */     }
/* 2571 */     catch (JMSException je) {
/* 2572 */       if (Trace.isOn) {
/* 2573 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderDestination,ProviderMessage)", (Throwable)je);
/*      */       }
/*      */       
/* 2576 */       if (Trace.isOn) {
/* 2577 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderDestination,ProviderMessage)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 2580 */       throw je;
/*      */     } 
/* 2582 */     if (Trace.isOn) {
/* 2583 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "send(ProviderDestination,ProviderMessage)");
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
/*      */   public boolean providerPriorityValidate(int priority) {
/* 2595 */     if (Trace.isOn) {
/* 2596 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "providerPriorityValidate(int)", new Object[] {
/* 2597 */             Integer.valueOf(priority)
/*      */           });
/*      */     }
/* 2600 */     PropertyStore.register("com.ibm.mq.jms.SupportMQExtensions", false);
/* 2601 */     boolean supportMQExtensions = PropertyStore.getBooleanPropertyObject("com.ibm.mq.jms.SupportMQExtensions").booleanValue();
/*      */ 
/*      */     
/* 2604 */     if (supportMQExtensions && -1 == priority) {
/* 2605 */       if (Trace.isOn) {
/* 2606 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "providerPriorityValidate(int)", 
/* 2607 */             Boolean.valueOf(true), 1);
/*      */       }
/* 2609 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2614 */     if (Trace.isOn) {
/* 2615 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "providerPriorityValidate(int)", 
/* 2616 */           Boolean.valueOf(false), 2);
/*      */     }
/* 2618 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean providerTimeToLiveValidate(long timeToLive) {
/* 2626 */     if (Trace.isOn) {
/* 2627 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "providerTimeToLiveValidate(long)", new Object[] {
/* 2628 */             Long.valueOf(timeToLive)
/*      */           });
/*      */     }
/* 2631 */     PropertyStore.register("com.ibm.mq.jms.SupportMQExtensions", false);
/* 2632 */     boolean supportMQExtensions = PropertyStore.getBooleanPropertyObject("com.ibm.mq.jms.SupportMQExtensions").booleanValue();
/*      */     
/* 2634 */     if (supportMQExtensions && -2L == timeToLive) {
/* 2635 */       if (Trace.isOn) {
/* 2636 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "providerTimeToLiveValidate(long)", 
/* 2637 */             Boolean.valueOf(true), 1);
/*      */       }
/* 2639 */       return true;
/*      */     } 
/* 2641 */     if (Trace.isOn) {
/* 2642 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "providerTimeToLiveValidate(long)", 
/* 2643 */           Boolean.valueOf(false), 2);
/*      */     }
/* 2645 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isMQRFH2Required(WMQDestination destination) throws JMSException {
/*      */     int targetClient;
/*      */     HashMap<String, Object> info;
/* 2658 */     if (Trace.isOn) {
/* 2659 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "isMQRFH2Required(WMQDestination)", new Object[] { destination });
/*      */     }
/*      */ 
/*      */     
/* 2663 */     boolean rfh2Required = true;
/*      */     
/* 2665 */     int messageBodyStyle = destination.getIntProperty("messageBody");
/* 2666 */     switch (messageBodyStyle) {
/*      */ 
/*      */       
/*      */       case 2:
/* 2670 */         targetClient = destination.getIntProperty("targetClient");
/* 2671 */         if (targetClient == 0) {
/* 2672 */           rfh2Required = true; break;
/* 2673 */         }  if (targetClient == 1) {
/* 2674 */           rfh2Required = false;
/*      */           break;
/*      */         } 
/* 2677 */         info = new HashMap<>();
/* 2678 */         info.put("targetClient", Integer.valueOf(targetClient));
/* 2679 */         Trace.ffst(this, "isMQRFH2Required(WMQDestination)", "XO00K001", info, JMSException.class);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 0:
/* 2685 */         rfh2Required = true;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1:
/* 2690 */         rfh2Required = false;
/*      */         break;
/*      */       
/*      */       default:
/* 2694 */         info = new HashMap<>();
/* 2695 */         info.put("messageBody", Integer.valueOf(messageBodyStyle));
/* 2696 */         Trace.ffst(this, "isMQRFH2Required(WMQDestination)", "XO00K002", info, JMSException.class);
/*      */         break;
/*      */     } 
/* 2699 */     if (Trace.isOn) {
/* 2700 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "isMQRFH2Required(WMQDestination)", 
/* 2701 */           Boolean.valueOf(rfh2Required));
/*      */     }
/* 2703 */     return rfh2Required;
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
/*      */   static int computeMessageContextOptions(WMQDestination destination) throws JMSException {
/* 2717 */     if (Trace.isOn) {
/* 2718 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "computeMessageContextOptions(WMQDestination)", new Object[] { destination });
/*      */     }
/*      */     
/* 2721 */     int messageContextOptions = 0;
/* 2722 */     int value = destination.getIntProperty("mdMessageContext");
/* 2723 */     if (value == 1) {
/*      */       
/* 2725 */       messageContextOptions = 1024;
/* 2726 */     } else if (value == 2) {
/*      */       
/* 2728 */       messageContextOptions = 2048;
/*      */     } else {
/*      */       
/* 2731 */       messageContextOptions = 0;
/*      */     } 
/* 2733 */     if (Trace.isOn) {
/* 2734 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "computeMessageContextOptions(WMQDestination)", 
/* 2735 */           Integer.valueOf(messageContextOptions));
/*      */     }
/* 2737 */     return messageContextOptions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object o) {
/* 2745 */     if (Trace.isOn) {
/* 2746 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "equals(Object)", new Object[] { o });
/*      */     }
/*      */     
/* 2749 */     boolean traceRet1 = super.equals(o);
/* 2750 */     if (Trace.isOn) {
/* 2751 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "equals(Object)", 
/* 2752 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 2754 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 2763 */     if (Trace.isOn) {
/* 2764 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "hashCode()");
/*      */     }
/*      */     
/* 2767 */     int traceRet1 = super.hashCode();
/* 2768 */     if (Trace.isOn) {
/* 2769 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "hashCode()", 
/* 2770 */           Integer.valueOf(traceRet1));
/*      */     }
/* 2772 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 2781 */     if (Trace.isOn) {
/* 2782 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "readObject(ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */     
/* 2785 */     NotSerializableException traceRet1 = new NotSerializableException();
/* 2786 */     if (Trace.isOn) {
/* 2787 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "readObject(ObjectInputStream)", traceRet1);
/*      */     }
/*      */     
/* 2790 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 2799 */     if (Trace.isOn) {
/* 2800 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "writeObject(ObjectOutputStream)", new Object[] { out });
/*      */     }
/*      */     
/* 2803 */     NotSerializableException traceRet1 = new NotSerializableException();
/* 2804 */     if (Trace.isOn) {
/* 2805 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "writeObject(ObjectOutputStream)", traceRet1);
/*      */     }
/*      */     
/* 2808 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dump(PrintWriter pw, int level) {
/* 2817 */     if (Trace.isOn) {
/* 2818 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "dump(PrintWriter,int)", new Object[] { pw, 
/* 2819 */             Integer.valueOf(level) });
/*      */     }
/* 2821 */     StringBuilder prefix = new StringBuilder();
/* 2822 */     for (int i = 0; i < level; i++) {
/* 2823 */       prefix.append("  ");
/*      */     }
/* 2825 */     pw.format("%s%s%n", new Object[] { prefix, toString() });
/* 2826 */     if (this.session == null) {
/* 2827 */       pw.format("%s  Parent Session <null>%n", new Object[] { prefix });
/*      */     } else {
/*      */       
/* 2830 */       pw.format("%s  Parent Session %s%n", new Object[] { prefix, this.session.getClass().getName() + '@' + Integer.toHexString(this.session.hashCode()) });
/*      */     } 
/* 2832 */     if (Trace.isOn)
/* 2833 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageProducer", "dump(PrintWriter,int)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQMessageProducer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */