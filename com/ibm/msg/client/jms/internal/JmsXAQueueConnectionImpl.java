/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsConnectionBrowser;
/*     */ import com.ibm.msg.client.jms.JmsDestination;
/*     */ import com.ibm.msg.client.jms.JmsMessageReferenceHandler;
/*     */ import com.ibm.msg.client.jms.JmsQueue;
/*     */ import com.ibm.msg.client.jms.JmsXAQueueConnection;
/*     */ import com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl;
/*     */ import javax.jms.ConnectionConsumer;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Queue;
/*     */ import javax.jms.QueueSession;
/*     */ import javax.jms.ServerSessionPool;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsXAQueueConnectionImpl
/*     */   extends JmsXAConnectionImpl
/*     */   implements JmsXAQueueConnection
/*     */ {
/*     */   private static final long serialVersionUID = -5108413832439157180L;
/*     */   static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsXAQueueConnectionImpl.java";
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.msg.client.jms.internal.JmsXAQueueConnectionImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsXAQueueConnectionImpl.java");
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
/*     */   public JmsXAQueueConnectionImpl(JmsConnectionFactoryImpl connectionFactory) throws JMSException {
/*  71 */     super(connectionFactory);
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueConnectionImpl", "<init>(JmsConnectionFactoryImpl)", new Object[] { connectionFactory });
/*     */     }
/*     */     
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAQueueConnectionImpl", "<init>(JmsConnectionFactoryImpl)");
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
/*     */   public JmsConnectionBrowser createConnectionBrowser(JmsQueue queue, String selector, JmsMessageReferenceHandler handler, int quantityHint) throws JMSException {
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueConnectionImpl", "createConnectionBrowser(JmsQueue,String,JmsMessageReferenceHandler,int)", new Object[] { queue, selector, handler, 
/*     */             
/*  96 */             Integer.valueOf(quantityHint) });
/*     */     }
/*     */     
/*  99 */     JmsConnectionBrowser traceRet1 = createConnectionBrowser((JmsDestination)queue, selector, handler, quantityHint);
/*     */     
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAQueueConnectionImpl", "createConnectionBrowser(JmsQueue,String,JmsMessageReferenceHandler,int)", traceRet1);
/*     */     }
/*     */     
/* 105 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QueueSession createQueueSession(boolean transacted, int ackMode) throws JMSException {
/* 113 */     if (Trace.isOn)
/* 114 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueConnectionImpl", "createQueueSession(boolean,int)", new Object[] {
/* 115 */             Boolean.valueOf(transacted), 
/* 116 */             Integer.valueOf(ackMode)
/*     */           }); 
/* 118 */     QueueSession traceRet1 = (QueueSession)createSession(transacted, ackMode);
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAQueueConnectionImpl", "createQueueSession(boolean,int)", traceRet1);
/*     */     }
/*     */     
/* 123 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XAQueueSession createXAQueueSession() throws JMSException {
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueConnectionImpl", "createXAQueueSession()");
/*     */     }
/*     */ 
/*     */     
/* 136 */     XAQueueSession traceRet1 = (XAQueueSession)createXASession();
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAQueueConnectionImpl", "createXAQueueSession()", traceRet1);
/*     */     }
/*     */     
/* 141 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionConsumer createConnectionConsumer(Queue queue, String selector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueConnectionImpl", "createConnectionConsumer(Queue,String,ServerSessionPool,int)", new Object[] { queue, selector, sessionPool, 
/*     */             
/* 154 */             Integer.valueOf(maxMessages) });
/*     */     }
/*     */     
/* 157 */     ConnectionConsumer traceRet1 = createConnectionConsumer((Destination)queue, selector, sessionPool, maxMessages);
/*     */     
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAQueueConnectionImpl", "createConnectionConsumer(Queue,String,ServerSessionPool,int)", traceRet1);
/*     */     }
/*     */     
/* 163 */     return traceRet1;
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
/*     */   protected JmsXASessionImpl instantiateXASession() throws JMSException {
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueConnectionImpl", "instantiateXASession()");
/*     */     }
/*     */     
/* 179 */     JmsXASessionImpl xaSession = new JmsXAQueueSessionImpl(this);
/*     */     
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAQueueConnectionImpl", "instantiateXASession()", xaSession);
/*     */     }
/*     */     
/* 185 */     return xaSession;
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
/*     */   protected JmsSessionImpl instantiateSession(boolean transacted, int acknowledgeMode) throws JMSException {
/* 200 */     if (Trace.isOn)
/* 201 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAQueueConnectionImpl", "instantiateSession(boolean,int)", new Object[] {
/* 202 */             Boolean.valueOf(transacted), 
/* 203 */             Integer.valueOf(acknowledgeMode)
/*     */           }); 
/* 205 */     JmsSessionImpl sess = new JmsQueueSessionImpl(transacted, acknowledgeMode, this);
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAQueueConnectionImpl", "instantiateSession(boolean,int)", sess);
/*     */     }
/*     */     
/* 210 */     return sess;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsXAQueueConnectionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */