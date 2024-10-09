/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsQueueSession;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Queue;
/*     */ import javax.jms.QueueReceiver;
/*     */ import javax.jms.QueueSender;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsQueueSessionImpl
/*     */   extends JmsSessionImpl
/*     */   implements JmsQueueSession
/*     */ {
/*     */   private static final long serialVersionUID = 607299518337478121L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsQueueSessionImpl.java";
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.msg.client.jms.internal.JmsQueueSessionImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsQueueSessionImpl.java");
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
/*     */   protected JmsQueueSessionImpl(boolean transacted, int ackMode, JmsConnectionImpl connection) throws JMSException {
/*  63 */     super(transacted, ackMode, connection);
/*  64 */     if (Trace.isOn)
/*  65 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueSessionImpl", "<init>(boolean,int,JmsConnectionImpl)", new Object[] {
/*  66 */             Boolean.valueOf(transacted), 
/*  67 */             Integer.valueOf(ackMode), connection
/*     */           }); 
/*  69 */     if (Trace.isOn) {
/*  70 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueSessionImpl", "<init>(boolean,int,JmsConnectionImpl)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueueReceiver createReceiver(Queue queue, String selector) throws JMSException {
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueSessionImpl", "createReceiver(Queue,String)", new Object[] { queue, selector });
/*     */     }
/*     */ 
/*     */     
/*  86 */     QueueReceiver traceRet1 = (QueueReceiver)createConsumer((Destination)queue, selector);
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueSessionImpl", "createReceiver(Queue,String)", traceRet1);
/*     */     }
/*     */     
/*  91 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueueReceiver createReceiver(Queue queue) throws JMSException {
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueSessionImpl", "createReceiver(Queue)", new Object[] { queue });
/*     */     }
/*     */     
/* 103 */     QueueReceiver traceRet1 = (QueueReceiver)createConsumer((Destination)queue);
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueSessionImpl", "createReceiver(Queue)", traceRet1);
/*     */     }
/*     */     
/* 108 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueueSender createSender(Queue queue) throws JMSException {
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueSessionImpl", "createSender(Queue)", new Object[] { queue });
/*     */     }
/*     */ 
/*     */     
/* 121 */     QueueSender traceRet1 = (QueueSender)createProducer((Destination)queue);
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueSessionImpl", "createSender(Queue)", traceRet1);
/*     */     }
/*     */     
/* 126 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsQueueSessionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */