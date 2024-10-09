/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsTopicSession;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Topic;
/*     */ import javax.jms.TopicPublisher;
/*     */ import javax.jms.TopicSubscriber;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsTopicSessionImpl
/*     */   extends JmsSessionImpl
/*     */   implements JmsTopicSession
/*     */ {
/*     */   private static final long serialVersionUID = 1336402632860439163L;
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.msg.client.jms.internal.JmsTopicSessionImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsTopicSessionImpl.java");
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
/*     */   protected JmsTopicSessionImpl(boolean transacted, int ackMode, JmsConnectionImpl connection) throws JMSException {
/*  55 */     super(transacted, ackMode, connection);
/*  56 */     if (Trace.isOn)
/*  57 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTopicSessionImpl", "<init>(boolean,int,JmsConnectionImpl)", new Object[] {
/*  58 */             Boolean.valueOf(transacted), 
/*  59 */             Integer.valueOf(ackMode), connection
/*     */           }); 
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTopicSessionImpl", "<init>(boolean,int,JmsConnectionImpl)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TopicPublisher createPublisher(Topic topic) throws JMSException {
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTopicSessionImpl", "createPublisher(Topic)", new Object[] { topic });
/*     */     }
/*     */     
/*  77 */     TopicPublisher traceRet1 = (TopicPublisher)createProducer((Destination)topic);
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTopicSessionImpl", "createPublisher(Topic)", traceRet1);
/*     */     }
/*     */     
/*  82 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TopicSubscriber createSubscriber(Topic topic, String messageSelector, boolean noLocal) throws JMSException {
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTopicSessionImpl", "createSubscriber(Topic,String,boolean)", new Object[] { topic, messageSelector, 
/*     */             
/*  94 */             Boolean.valueOf(noLocal) });
/*     */     }
/*     */     
/*  97 */     TopicSubscriber traceRet1 = (TopicSubscriber)createConsumer((Destination)topic, messageSelector, noLocal);
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTopicSessionImpl", "createSubscriber(Topic,String,boolean)", traceRet1);
/*     */     }
/*     */     
/* 102 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TopicSubscriber createSubscriber(Topic topic) throws JMSException {
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTopicSessionImpl", "createSubscriber(Topic)", new Object[] { topic });
/*     */     }
/*     */     
/* 114 */     TopicSubscriber traceRet1 = (TopicSubscriber)createConsumer((Destination)topic);
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTopicSessionImpl", "createSubscriber(Topic)", traceRet1);
/*     */     }
/*     */     
/* 119 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsTopicSessionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */