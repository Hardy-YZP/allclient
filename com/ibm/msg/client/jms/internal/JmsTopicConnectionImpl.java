/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsTopicConnection;
/*     */ import com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl;
/*     */ import javax.jms.ConnectionConsumer;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.ServerSessionPool;
/*     */ import javax.jms.Topic;
/*     */ import javax.jms.TopicSession;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsTopicConnectionImpl
/*     */   extends JmsConnectionImpl
/*     */   implements JmsTopicConnection
/*     */ {
/*     */   private static final long serialVersionUID = 4070250702799112454L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsTopicConnectionImpl.java";
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.msg.client.jms.internal.JmsTopicConnectionImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsTopicConnectionImpl.java");
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
/*     */   public JmsTopicConnectionImpl(JmsConnectionFactoryImpl connectionFactory) throws JMSException {
/*  67 */     super(connectionFactory);
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTopicConnectionImpl", "<init>(JmsConnectionFactoryImpl)", new Object[] { connectionFactory });
/*     */     }
/*     */     
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTopicConnectionImpl", "<init>(JmsConnectionFactoryImpl)");
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
/*     */   public ConnectionConsumer createConnectionConsumer(Topic topic, String messageSelector, ServerSessionPool sessionPool, int maxBatchSize) throws JMSException {
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTopicConnectionImpl", "createConnectionConsumer(Topic,String,ServerSessionPool,int)", new Object[] { topic, messageSelector, sessionPool, 
/*     */             
/*  89 */             Integer.valueOf(maxBatchSize) });
/*     */     }
/*     */     
/*  92 */     ConnectionConsumer traceRet1 = createConnectionConsumer((Destination)topic, messageSelector, sessionPool, maxBatchSize);
/*     */     
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTopicConnectionImpl", "createConnectionConsumer(Topic,String,ServerSessionPool,int)", traceRet1);
/*     */     }
/*     */     
/*  98 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TopicSession createTopicSession(boolean transacted, int ackMode) throws JMSException {
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTopicConnectionImpl", "createTopicSession(boolean,int)", new Object[] {
/* 108 */             Boolean.valueOf(transacted), 
/* 109 */             Integer.valueOf(ackMode)
/*     */           });
/*     */     }
/* 112 */     TopicSession traceRet1 = (TopicSession)createSession(transacted, ackMode);
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTopicConnectionImpl", "createTopicSession(boolean,int)", traceRet1);
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
/* 128 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTopicConnectionImpl", "instantiateSession(boolean,int)", new Object[] {
/* 129 */             Boolean.valueOf(transacted), 
/* 130 */             Integer.valueOf(acknowledgeMode)
/*     */           }); 
/* 132 */     JmsTopicSessionImpl jmsSession = null;
/*     */     
/* 134 */     jmsSession = new JmsTopicSessionImpl(transacted, acknowledgeMode, this);
/*     */     
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTopicConnectionImpl", "instantiateSession(boolean,int)", jmsSession);
/*     */     }
/*     */     
/* 140 */     return jmsSession;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsTopicConnectionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */