/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsConnectionConsumer;
/*     */ import com.ibm.msg.client.jms.JmsQueue;
/*     */ import com.ibm.msg.client.jms.JmsQueueConnection;
/*     */ import com.ibm.msg.client.jms.JmsSession;
/*     */ import javax.jms.ConnectionConsumer;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Queue;
/*     */ import javax.jms.QueueConnection;
/*     */ import javax.jms.QueueSession;
/*     */ import javax.jms.ServerSessionPool;
/*     */ import javax.jms.Session;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQQueueConnection
/*     */   extends MQConnection
/*     */   implements QueueConnection, JmsQueueConnection
/*     */ {
/*     */   private static final long serialVersionUID = 6217246988072140000L;
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.jms.MQQueueConnection", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQQueueConnection.java");
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
/*     */   MQQueueConnection() {
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueConnection", "<init>()");
/*     */     }
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueConnection", "<init>()");
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
/*     */   public ConnectionConsumer createConnectionConsumer(Queue queue, String selector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueConnection", "createConnectionConsumer(Queue,String,ServerSessionPool,int)", new Object[] { queue, selector, sessionPool, 
/*     */             
/*  97 */             Integer.valueOf(maxMessages) });
/*     */     }
/*  99 */     MQConnectionConsumer wrapper = new MQConnectionConsumer();
/*     */     
/* 101 */     JmsQueue q = (queue == null) ? null : (JmsQueue)((MQQueue)queue).validateDestination();
/*     */     
/* 103 */     wrapper.setDelegate((JmsConnectionConsumer)((JmsQueueConnection)this.delegate)
/* 104 */         .createConnectionConsumer((Queue)q, selector, sessionPool, maxMessages));
/*     */     
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueConnection", "createConnectionConsumer(Queue,String,ServerSessionPool,int)", wrapper);
/*     */     }
/*     */     
/* 110 */     return wrapper;
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
/*     */   public QueueSession createQueueSession(boolean transacted, int acknowledgeMode) throws JMSException {
/* 126 */     if (Trace.isOn)
/* 127 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueConnection", "createQueueSession(boolean,int)", new Object[] {
/* 128 */             Boolean.valueOf(transacted), Integer.valueOf(acknowledgeMode)
/*     */           }); 
/* 130 */     MQQueueSession wrapper = new MQQueueSession();
/* 131 */     wrapper.setDelegate((JmsSession)((JmsQueueConnection)this.delegate).createQueueSession(transacted, acknowledgeMode));
/*     */     
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueConnection", "createQueueSession(boolean,int)", wrapper);
/*     */     }
/*     */     
/* 137 */     return wrapper;
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
/*     */   public Session createSession(boolean arg0, int arg1) throws JMSException {
/* 150 */     if (Trace.isOn)
/* 151 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueConnection", "createSession(boolean,int)", new Object[] {
/* 152 */             Boolean.valueOf(arg0), Integer.valueOf(arg1)
/*     */           }); 
/* 154 */     QueueSession queueSession = createQueueSession(arg0, arg1);
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueConnection", "createSession(boolean,int)", queueSession);
/*     */     }
/*     */     
/* 159 */     return (Session)queueSession;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQQueueConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */