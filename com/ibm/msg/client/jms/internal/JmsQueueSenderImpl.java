/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsQueueSender;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Message;
/*     */ import javax.jms.Queue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsQueueSenderImpl
/*     */   extends JmsMessageProducerImpl
/*     */   implements JmsQueueSender
/*     */ {
/*     */   private static final long serialVersionUID = 593891085163371217L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsQueueSenderImpl.java";
/*     */   Queue queue;
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.msg.client.jms.internal.JmsQueueSenderImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsQueueSenderImpl.java");
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
/*     */   JmsQueueSenderImpl(Queue queue, JmsSessionImpl session) throws JMSException {
/*  69 */     super((Destination)queue, session);
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueSenderImpl", "<init>(Queue,JmsSessionImpl)", new Object[] { queue, session });
/*     */     }
/*     */ 
/*     */     
/*  75 */     this.queue = queue;
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueSenderImpl", "<init>(Queue,JmsSessionImpl)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Queue getQueue() throws JMSException {
/*  88 */     this.state.checkNotClosed("JMSCC0026");
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsQueueSenderImpl", "getQueue()", "getter", this.queue);
/*     */     }
/*     */     
/*  93 */     return this.queue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void send(Queue queue, Message message, int deliveryMode, int priority, long expiry) throws JMSException {
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueSenderImpl", "send(Queue,Message,int,int,long)", new Object[] { queue, message, 
/*     */             
/* 104 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), Long.valueOf(expiry) });
/*     */     }
/*     */     
/* 107 */     send((Destination)queue, message, deliveryMode, priority, expiry);
/*     */     
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueSenderImpl", "send(Queue,Message,int,int,long)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void send(Queue queue, Message message) throws JMSException {
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueSenderImpl", "send(Queue,Message)", new Object[] { queue, message });
/*     */     }
/*     */ 
/*     */     
/* 126 */     send((Destination)queue, message);
/*     */     
/* 128 */     if (Trace.isOn)
/* 129 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueSenderImpl", "send(Queue,Message)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsQueueSenderImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */