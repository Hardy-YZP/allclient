/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsQueueReceiver;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
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
/*     */ 
/*     */ public class JmsQueueReceiverImpl
/*     */   extends JmsMessageConsumerImpl
/*     */   implements JmsQueueReceiver
/*     */ {
/*     */   private static final long serialVersionUID = -8625328526909107766L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsQueueReceiverImpl.java";
/*     */   Queue queue;
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.msg.client.jms.internal.JmsQueueReceiverImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsQueueReceiverImpl.java");
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
/*     */   JmsQueueReceiverImpl(JmsSessionImpl session, Queue queue, String selector, boolean noLocal) throws JMSException {
/*  73 */     super(session, (Destination)queue, selector, noLocal, null, false, false);
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueReceiverImpl", "<init>(JmsSessionImpl,Queue,String,boolean)", new Object[] { session, queue, selector, 
/*     */             
/*  77 */             Boolean.valueOf(noLocal) });
/*     */     }
/*     */     
/*  80 */     this.queue = queue;
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueReceiverImpl", "<init>(JmsSessionImpl,Queue,String,boolean)");
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
/*     */   public Queue getQueue() throws JMSException {
/*  94 */     this.state.checkNotClosed("JMSCC0032");
/*  95 */     if (this.closed) {
/*  96 */       JMSException traceRet1 = (JMSException)JmsErrorUtils.createException("JMSCC0026", null);
/*     */       
/*  98 */       if (Trace.isOn) {
/*  99 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsQueueReceiverImpl", "getQueue()", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 102 */       throw traceRet1;
/*     */     } 
/*     */     
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsQueueReceiverImpl", "getQueue()", "getter", this.queue);
/*     */     }
/*     */     
/* 109 */     return this.queue;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsQueueReceiverImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */