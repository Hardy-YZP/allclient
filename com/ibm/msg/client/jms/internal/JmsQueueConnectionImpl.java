/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsQueueConnection;
/*     */ import com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl;
/*     */ import javax.jms.ConnectionConsumer;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Queue;
/*     */ import javax.jms.QueueSession;
/*     */ import javax.jms.ServerSessionPool;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsQueueConnectionImpl
/*     */   extends JmsConnectionImpl
/*     */   implements JmsQueueConnection
/*     */ {
/*     */   private static final long serialVersionUID = -8574437746218273184L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsQueueConnectionImpl.java";
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.msg.client.jms.internal.JmsQueueConnectionImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsQueueConnectionImpl.java");
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
/*     */   public JmsQueueConnectionImpl(JmsConnectionFactoryImpl connectionFactory) throws JMSException {
/*  67 */     super(connectionFactory);
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueConnectionImpl", "<init>(JmsConnectionFactoryImpl)", new Object[] { connectionFactory });
/*     */     }
/*     */     
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueConnectionImpl", "<init>(JmsConnectionFactoryImpl)");
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
/*     */   public ConnectionConsumer createConnectionConsumer(Queue queue, String messageSelector, ServerSessionPool sessionPool, int maxBatchSize) throws JMSException {
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueConnectionImpl", "createConnectionConsumer(Queue,String,ServerSessionPool,int)", new Object[] { queue, messageSelector, sessionPool, 
/*     */             
/*  89 */             Integer.valueOf(maxBatchSize) });
/*     */     }
/*     */     
/*  92 */     ConnectionConsumer traceRet1 = createConnectionConsumer((Destination)queue, messageSelector, sessionPool, maxBatchSize);
/*     */     
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueConnectionImpl", "createConnectionConsumer(Queue,String,ServerSessionPool,int)", traceRet1);
/*     */     }
/*     */     
/*  98 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueueSession createQueueSession(boolean transacted, int ackMode) throws JMSException {
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueConnectionImpl", "createQueueSession(boolean,int)", new Object[] {
/* 108 */             Boolean.valueOf(transacted), 
/* 109 */             Integer.valueOf(ackMode)
/*     */           });
/*     */     }
/* 112 */     QueueSession traceRet1 = (QueueSession)createSession(transacted, ackMode);
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueConnectionImpl", "createQueueSession(boolean,int)", traceRet1);
/*     */     }
/*     */     
/* 117 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JmsSessionImpl instantiateSession(boolean transacted, int acknowledgeMode) throws JMSException {
/* 127 */     if (Trace.isOn)
/* 128 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueConnectionImpl", "instantiateSession(boolean,int)", new Object[] {
/* 129 */             Boolean.valueOf(transacted), 
/* 130 */             Integer.valueOf(acknowledgeMode)
/*     */           }); 
/* 132 */     JmsQueueSessionImpl jmsSession = null;
/*     */     
/* 134 */     jmsSession = new JmsQueueSessionImpl(transacted, acknowledgeMode, this);
/*     */     
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueConnectionImpl", "instantiateSession(boolean,int)", jmsSession);
/*     */     }
/*     */     
/* 140 */     return jmsSession;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsQueueConnectionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */