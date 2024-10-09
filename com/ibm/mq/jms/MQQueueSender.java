/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.jms.JMSMessage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsQueue;
/*     */ import com.ibm.msg.client.jms.JmsQueueSender;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Message;
/*     */ import javax.jms.Queue;
/*     */ import javax.jms.QueueSender;
/*     */ import javax.jms.TemporaryQueue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQQueueSender
/*     */   extends MQMessageProducer
/*     */   implements QueueSender, JmsQueueSender
/*     */ {
/*     */   private static final long serialVersionUID = -303776029855233556L;
/*     */   
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.mq.jms.MQQueueSender", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQQueueSender.java");
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
/*     */   MQQueueSender() {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueSender", "<init>()");
/*     */     }
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueSender", "<init>()");
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
/*     */   public void close() throws JMSException {
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueSender", "close()");
/*     */     }
/*  78 */     this.commonProducer.close();
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueSender", "close()");
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
/*     */   public Queue getQueue() throws JMSException {
/*  94 */     Queue traceRet1 = null;
/*  95 */     Queue dest = ((JmsQueueSender)this.commonProducer).getQueue();
/*     */ 
/*     */     
/*  98 */     if (dest instanceof TemporaryQueue) {
/*  99 */       traceRet1 = new MQTemporaryQueue((TemporaryQueue)dest);
/*     */     } else {
/* 101 */       traceRet1 = dest;
/*     */     } 
/*     */     
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.data(this, "com.ibm.mq.jms.MQQueueSender", "getQueue()", "getter", traceRet1);
/*     */     }
/* 107 */     return traceRet1;
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
/*     */   public void send(Queue queue, Message message) throws JMSException {
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueSender", "send(Queue,Message)", new Object[] { queue, message });
/*     */     }
/*     */     
/* 129 */     JmsQueue q = (queue == null) ? null : (JmsQueue)((MQQueue)queue).validateDestination();
/* 130 */     Message msg = (message instanceof JMSMessage) ? ((JMSMessage)message).getDelegate() : message;
/* 131 */     this.commonProducer.send((Destination)q, msg);
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueSender", "send(Queue,Message)");
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
/*     */   public void send(Queue queue, Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueSender", "send(Queue,Message,int,int,long)", new Object[] { queue, message, 
/* 160 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), 
/* 161 */             Long.valueOf(timeToLive) });
/*     */     }
/* 163 */     JmsQueue q = (queue == null) ? null : (JmsQueue)((MQQueue)queue).validateDestination();
/* 164 */     Message msg = (message instanceof JMSMessage) ? ((JMSMessage)message).getDelegate() : message;
/* 165 */     this.commonProducer.send((Destination)q, msg, deliveryMode, priority, timeToLive);
/* 166 */     if (Trace.isOn)
/* 167 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueSender", "send(Queue,Message,int,int,long)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQQueueSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */