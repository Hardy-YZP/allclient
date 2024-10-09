/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsQueueReceiver;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Queue;
/*     */ import javax.jms.QueueReceiver;
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
/*     */ public class MQQueueReceiver
/*     */   extends MQMessageConsumer
/*     */   implements QueueReceiver, JmsQueueReceiver
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.jms.MQQueueReceiver", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQQueueReceiver.java");
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
/*     */   MQQueueReceiver() {
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueReceiver", "<init>()");
/*     */     }
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueReceiver", "<init>()");
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
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueReceiver", "close()");
/*     */     }
/*  71 */     this.commonConsumer.close();
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueReceiver", "close()");
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
/*     */   public Queue getQueue() throws JMSException {
/*  88 */     Queue traceRet1 = null;
/*  89 */     Queue dest = ((JmsQueueReceiver)this.commonConsumer).getQueue();
/*     */ 
/*     */     
/*  92 */     if (dest instanceof TemporaryQueue) {
/*  93 */       traceRet1 = new MQTemporaryQueue((TemporaryQueue)dest);
/*     */     } else {
/*  95 */       traceRet1 = dest;
/*     */     } 
/*     */     
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.data(this, "com.ibm.mq.jms.MQQueueReceiver", "getQueue()", "getter", traceRet1);
/*     */     }
/* 101 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQQueueReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */