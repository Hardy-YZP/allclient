/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsSession;
/*     */ import com.ibm.msg.client.jms.JmsTopicSession;
/*     */ import com.ibm.msg.client.jms.JmsXATopicSession;
/*     */ import com.ibm.msg.client.jms.internal.JmsXATopicSessionImpl;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.MessageConsumer;
/*     */ import javax.jms.MessageProducer;
/*     */ import javax.jms.Session;
/*     */ import javax.jms.TopicPublisher;
/*     */ import javax.jms.TopicSession;
/*     */ import javax.jms.TopicSubscriber;
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
/*     */ public class MQXATopicSession
/*     */   extends MQXASession
/*     */   implements XATopicSession, JmsXATopicSession
/*     */ {
/*     */   private static final long serialVersionUID = 6705131420655754176L;
/*     */   
/*     */   static {
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.data("com.ibm.mq.jms.MQXATopicSession", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQXATopicSession.java");
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
/*     */   MQXATopicSession() {
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicSession", "<init>()");
/*     */     }
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicSession", "<init>()");
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
/*     */   public TopicSession getTopicSession() throws JMSException {
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicSession", "getTopicSession()");
/*     */     }
/*  85 */     if (null == this.backingSession) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  91 */       JmsSession js = getDelegate();
/*  92 */       JmsXATopicSessionImpl jxtsi = (JmsXATopicSessionImpl)js;
/*  93 */       JmsTopicSession backingDelegate = (JmsTopicSession)jxtsi.getTopicSession();
/*  94 */       this.backingSession = new MQTopicSession();
/*  95 */       this.backingSession.setDelegate((JmsSession)backingDelegate);
/*     */     } 
/*  97 */     TopicSession traceRet1 = (MQTopicSession)this.backingSession;
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicSession", "getTopicSession()", traceRet1);
/*     */     }
/* 101 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Session getSession() throws JMSException {
/* 112 */     TopicSession topicSession = getTopicSession();
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.data(this, "com.ibm.mq.jms.MQXATopicSession", "getSession()", "getter", topicSession);
/*     */     }
/* 116 */     return (Session)topicSession;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageConsumer createConsumer(Destination destination) throws JMSException {
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicSession", "createConsumer(Destination)", new Object[] { destination });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 133 */     checkConsumerDestinationIsTopic(destination);
/*     */     
/* 135 */     TopicSubscriber topicSubscriber = getTopicSession().createSubscriber((MQTopic)destination);
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicSession", "createConsumer(Destination)", topicSubscriber);
/*     */     }
/*     */     
/* 140 */     return (MessageConsumer)topicSubscriber;
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
/*     */   public MessageConsumer createConsumer(Destination destination, String selector) throws JMSException {
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicSession", "createConsumer(Destination,String)", new Object[] { destination, selector });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 159 */     checkConsumerDestinationIsTopic(destination);
/*     */     
/* 161 */     TopicSubscriber topicSubscriber = getTopicSession().createSubscriber((MQTopic)destination, selector, false);
/*     */     
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicSession", "createConsumer(Destination,String)", topicSubscriber);
/*     */     }
/*     */     
/* 167 */     return (MessageConsumer)topicSubscriber;
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
/*     */   public MessageConsumer createConsumer(Destination destination, String selector, boolean noLocal) throws JMSException {
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicSession", "createConsumer(Destination,String,boolean)", new Object[] { destination, selector, 
/*     */             
/* 184 */             Boolean.valueOf(noLocal) });
/*     */     }
/*     */ 
/*     */     
/* 188 */     checkConsumerDestinationIsTopic(destination);
/*     */     
/* 190 */     TopicSubscriber topicSubscriber = getTopicSession().createSubscriber((MQTopic)destination, selector, noLocal);
/*     */     
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicSession", "createConsumer(Destination,String,boolean)", topicSubscriber);
/*     */     }
/*     */     
/* 196 */     return (MessageConsumer)topicSubscriber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageProducer createProducer(Destination destination) throws JMSException {
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicSession", "createProducer(Destination)", new Object[] { destination });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 213 */     checkProducerDestinationIsTopic(destination);
/*     */     
/* 215 */     TopicPublisher topicPublisher = getTopicSession().createPublisher((MQTopic)destination);
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicSession", "createProducer(Destination)", topicPublisher);
/*     */     }
/*     */     
/* 220 */     return (MessageProducer)topicPublisher;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQXATopicSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */