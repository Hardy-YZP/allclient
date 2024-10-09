/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsXATopicConnection;
/*     */ import com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl;
/*     */ import javax.jms.ConnectionConsumer;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.ServerSessionPool;
/*     */ import javax.jms.Topic;
/*     */ import javax.jms.TopicSession;
/*     */ import javax.jms.XATopicSession;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsXATopicConnectionImpl
/*     */   extends JmsXAConnectionImpl
/*     */   implements JmsXATopicConnection
/*     */ {
/*     */   private static final long serialVersionUID = -4979193976190976082L;
/*     */   static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsXATopicConnectionImpl.java";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.msg.client.jms.internal.JmsXATopicConnectionImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsXATopicConnectionImpl.java");
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
/*     */   public JmsXATopicConnectionImpl(JmsConnectionFactoryImpl connectionFactory) throws JMSException {
/*  69 */     super(connectionFactory);
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicConnectionImpl", "<init>(JmsConnectionFactoryImpl)", new Object[] { connectionFactory });
/*     */     }
/*     */     
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXATopicConnectionImpl", "<init>(JmsConnectionFactoryImpl)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TopicSession createTopicSession(boolean transacted, int ackMode) throws JMSException {
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicConnectionImpl", "createTopicSession(boolean,int)", new Object[] {
/*  87 */             Boolean.valueOf(transacted), 
/*  88 */             Integer.valueOf(ackMode)
/*     */           });
/*     */     }
/*     */     
/*  92 */     TopicSession traceRet1 = (TopicSession)createSession(transacted, ackMode);
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXATopicConnectionImpl", "createTopicSession(boolean,int)", traceRet1);
/*     */     }
/*     */     
/*  97 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XATopicSession createXATopicSession() throws JMSException {
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicConnectionImpl", "createXATopicSession()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 111 */     XATopicSession traceRet1 = (XATopicSession)createXASession();
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXATopicConnectionImpl", "createXATopicSession()", traceRet1);
/*     */     }
/*     */     
/* 116 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionConsumer createConnectionConsumer(Topic topic, String selector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicConnectionImpl", "createConnectionConsumer(Topic,String,ServerSessionPool,int)", new Object[] { topic, selector, sessionPool, 
/*     */             
/* 129 */             Integer.valueOf(maxMessages) });
/*     */     }
/*     */     
/* 132 */     ConnectionConsumer traceRet1 = createConnectionConsumer((Destination)topic, selector, sessionPool, maxMessages);
/*     */     
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXATopicConnectionImpl", "createConnectionConsumer(Topic,String,ServerSessionPool,int)", traceRet1);
/*     */     }
/*     */     
/* 138 */     return traceRet1;
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
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicConnectionImpl", "instantiateXASession()");
/*     */     }
/*     */ 
/*     */     
/* 155 */     JmsXASessionImpl xaSession = new JmsXATopicSessionImpl(this);
/*     */     
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXATopicConnectionImpl", "instantiateXASession()", xaSession);
/*     */     }
/*     */     
/* 161 */     return xaSession;
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
/*     */   protected JmsSessionImpl instantiateSession(boolean transacted, int acknowledgeMode) throws JMSException {
/* 175 */     if (Trace.isOn)
/* 176 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXATopicConnectionImpl", "instantiateSession(boolean,int)", new Object[] {
/* 177 */             Boolean.valueOf(transacted), 
/* 178 */             Integer.valueOf(acknowledgeMode)
/*     */           }); 
/* 180 */     JmsSessionImpl sess = new JmsTopicSessionImpl(transacted, acknowledgeMode, this);
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXATopicConnectionImpl", "instantiateSession(boolean,int)", sess);
/*     */     }
/*     */     
/* 185 */     return sess;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsXATopicConnectionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */