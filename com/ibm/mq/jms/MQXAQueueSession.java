/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsQueueSession;
/*     */ import com.ibm.msg.client.jms.JmsSession;
/*     */ import com.ibm.msg.client.jms.JmsXAQueueSession;
/*     */ import com.ibm.msg.client.jms.internal.JmsXAQueueSessionImpl;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.MessageConsumer;
/*     */ import javax.jms.MessageProducer;
/*     */ import javax.jms.QueueReceiver;
/*     */ import javax.jms.QueueSender;
/*     */ import javax.jms.QueueSession;
/*     */ import javax.jms.Session;
/*     */ import javax.jms.XAQueueSession;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQXAQueueSession
/*     */   extends MQXASession
/*     */   implements XAQueueSession, JmsXAQueueSession
/*     */ {
/*     */   private static final long serialVersionUID = 7598406145196537989L;
/*     */   
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.mq.jms.MQXAQueueSession", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQXAQueueSession.java");
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
/*     */   MQXAQueueSession() {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.entry(this, "com.ibm.mq.jms.MQXAQueueSession", "<init>()");
/*     */     }
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.exit(this, "com.ibm.mq.jms.MQXAQueueSession", "<init>()");
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
/*     */   public QueueSession getQueueSession() throws JMSException {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.mq.jms.MQXAQueueSession", "getQueueSession()");
/*     */     }
/*  80 */     if (null == this.backingSession) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  86 */       JmsSession js = getDelegate();
/*  87 */       JmsXAQueueSessionImpl jxqsi = (JmsXAQueueSessionImpl)js;
/*  88 */       JmsQueueSession backingDelegate = (JmsQueueSession)jxqsi.getQueueSession();
/*  89 */       this.backingSession = new MQQueueSession();
/*  90 */       this.backingSession.setDelegate((JmsSession)backingDelegate);
/*     */     } 
/*  92 */     QueueSession traceRet1 = (MQQueueSession)this.backingSession;
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.exit(this, "com.ibm.mq.jms.MQXAQueueSession", "getQueueSession()", traceRet1);
/*     */     }
/*  96 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Session getSession() throws JMSException {
/* 107 */     QueueSession queueSession = getQueueSession();
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.data(this, "com.ibm.mq.jms.MQXAQueueSession", "getSession()", "getter", queueSession);
/*     */     }
/* 111 */     return (Session)queueSession;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageConsumer createConsumer(Destination destination) throws JMSException {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry(this, "com.ibm.mq.jms.MQXAQueueSession", "createConsumer(Destination)", new Object[] { destination });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 128 */     checkConsumerDestinationIsQueue(destination);
/*     */     
/* 130 */     QueueReceiver queueReceiver = getQueueSession().createReceiver((MQQueue)destination);
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.exit(this, "com.ibm.mq.jms.MQXAQueueSession", "createConsumer(Destination)", queueReceiver);
/*     */     }
/*     */     
/* 135 */     return (MessageConsumer)queueReceiver;
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
/*     */   public MessageConsumer createConsumer(Destination destination, String selector) throws JMSException {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.entry(this, "com.ibm.mq.jms.MQXAQueueSession", "createConsumer(Destination,String)", new Object[] { destination, selector });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 154 */     checkConsumerDestinationIsQueue(destination);
/*     */     
/* 156 */     QueueReceiver queueReceiver = getQueueSession().createReceiver((MQQueue)destination, selector);
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.exit(this, "com.ibm.mq.jms.MQXAQueueSession", "createConsumer(Destination,String)", queueReceiver);
/*     */     }
/*     */     
/* 161 */     return (MessageConsumer)queueReceiver;
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
/*     */   public MessageConsumer createConsumer(Destination destination, String selector, boolean noLocal) throws JMSException {
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.entry(this, "com.ibm.mq.jms.MQXAQueueSession", "createConsumer(Destination,String,boolean)", new Object[] { destination, selector, 
/*     */             
/* 178 */             Boolean.valueOf(noLocal) });
/*     */     }
/*     */ 
/*     */     
/* 182 */     checkConsumerDestinationIsQueue(destination);
/*     */     
/* 184 */     QueueReceiver queueReceiver = getQueueSession().createReceiver((MQQueue)destination, selector);
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.exit(this, "com.ibm.mq.jms.MQXAQueueSession", "createConsumer(Destination,String,boolean)", queueReceiver);
/*     */     }
/*     */     
/* 189 */     return (MessageConsumer)queueReceiver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageProducer createProducer(Destination destination) throws JMSException {
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.entry(this, "com.ibm.mq.jms.MQXAQueueSession", "createProducer(Destination)", new Object[] { destination });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 206 */     checkProducerDestinationIsQueue(destination);
/*     */     
/* 208 */     QueueSender queueSender = getQueueSession().createSender((MQQueue)destination);
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.exit(this, "com.ibm.mq.jms.MQXAQueueSession", "createProducer(Destination)", queueSender);
/*     */     }
/*     */     
/* 213 */     return (MessageProducer)queueSender;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQXAQueueSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */