/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsMessageConsumer;
/*     */ import com.ibm.msg.client.jms.JmsMessageProducer;
/*     */ import com.ibm.msg.client.jms.JmsQueue;
/*     */ import com.ibm.msg.client.jms.JmsQueueSession;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.MessageConsumer;
/*     */ import javax.jms.MessageProducer;
/*     */ import javax.jms.Queue;
/*     */ import javax.jms.QueueReceiver;
/*     */ import javax.jms.QueueSender;
/*     */ import javax.jms.QueueSession;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQQueueSession
/*     */   extends MQSession
/*     */   implements QueueSession, JmsQueueSession
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.mq.jms.MQQueueSession", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQQueueSession.java");
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
/*     */   MQQueueSession() {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueSession", "<init>()");
/*     */     }
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueSession", "<init>()");
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
/*     */   public QueueReceiver createReceiver(Queue queue) throws JMSException {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueSession", "createReceiver(Queue)", new Object[] { queue });
/*     */     }
/*     */     
/*  81 */     MQQueueReceiver wrapper = new MQQueueReceiver();
/*  82 */     JmsQueue theQueue = null;
/*     */     
/*  84 */     if (queue != null) {
/*  85 */       theQueue = (JmsQueue)((MQQueue)queue).validateDestination();
/*     */     }
/*  87 */     wrapper.setDelegate((JmsMessageConsumer)((JmsQueueSession)this.commonSess).createReceiver((Queue)theQueue));
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueSession", "createReceiver(Queue)", wrapper);
/*     */     }
/*  91 */     return wrapper;
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
/*     */   public QueueReceiver createReceiver(Queue queue, String selector) throws JMSException {
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueSession", "createReceiver(Queue,String)", new Object[] { queue, selector });
/*     */     }
/*     */     
/* 109 */     MQQueueReceiver wrapper = new MQQueueReceiver();
/* 110 */     JmsQueue theQueue = null;
/*     */     
/* 112 */     if (queue != null) {
/* 113 */       theQueue = (JmsQueue)((MQQueue)queue).validateDestination();
/*     */     }
/*     */     
/* 116 */     wrapper.setDelegate((JmsMessageConsumer)((JmsQueueSession)this.commonSess).createReceiver((Queue)theQueue, selector));
/*     */     
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueSession", "createReceiver(Queue,String)", wrapper);
/*     */     }
/*     */     
/* 122 */     return wrapper;
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
/*     */   public QueueSender createSender(Queue queue) throws JMSException {
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueSession", "createSender(Queue)", new Object[] { queue });
/*     */     }
/*     */     
/* 138 */     MQQueueSender wrapper = new MQQueueSender();
/* 139 */     JmsQueue jmsQueue = null;
/* 140 */     if (queue != null) {
/* 141 */       jmsQueue = (JmsQueue)((MQQueue)queue).validateDestination();
/*     */     }
/* 143 */     wrapper.setDelegate((JmsMessageProducer)((JmsQueueSession)this.commonSess).createSender((Queue)jmsQueue));
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueSession", "createSender(Queue)", wrapper);
/*     */     }
/* 147 */     return wrapper;
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
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueSession", "createConsumer(Destination)", new Object[] { destination });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 164 */     checkConsumerDestinationIsQueue(destination);
/*     */     
/* 166 */     QueueReceiver queueReceiver = createReceiver((MQQueue)destination);
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueSession", "createConsumer(Destination)", queueReceiver);
/*     */     }
/* 170 */     return (MessageConsumer)queueReceiver;
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
/*     */   public MessageConsumer createConsumer(Destination destination, String selector) throws JMSException {
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueSession", "createConsumer(Destination,String)", new Object[] { destination, selector });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 191 */     checkConsumerDestinationIsQueue(destination);
/*     */     
/* 193 */     QueueReceiver queueReceiver = createReceiver((MQQueue)destination, selector);
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueSession", "createConsumer(Destination,String)", queueReceiver);
/*     */     }
/*     */     
/* 198 */     return (MessageConsumer)queueReceiver;
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
/*     */   public MessageConsumer createConsumer(Destination destination, String selector, boolean noLocal) throws JMSException {
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueSession", "createConsumer(Destination,String,boolean)", new Object[] { destination, selector, 
/*     */             
/* 217 */             Boolean.valueOf(noLocal) });
/*     */     }
/*     */ 
/*     */     
/* 221 */     checkConsumerDestinationIsQueue(destination);
/*     */     
/* 223 */     QueueReceiver queueReceiver = createReceiver((MQQueue)destination, selector);
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueSession", "createConsumer(Destination,String,boolean)", queueReceiver);
/*     */     }
/*     */     
/* 228 */     return (MessageConsumer)queueReceiver;
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
/*     */   public MessageProducer createProducer(Destination destination) throws JMSException {
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueSession", "createProducer(Destination)", new Object[] { destination });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 246 */     checkProducerDestinationIsQueue(destination);
/*     */     
/* 248 */     QueueSender queueSender = createSender((MQQueue)destination);
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueSession", "createProducer(Destination)", queueSender);
/*     */     }
/* 252 */     return (MessageProducer)queueSender;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQQueueSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */