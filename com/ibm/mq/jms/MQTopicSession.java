/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsMessageConsumer;
/*     */ import com.ibm.msg.client.jms.JmsMessageProducer;
/*     */ import com.ibm.msg.client.jms.JmsTopic;
/*     */ import com.ibm.msg.client.jms.JmsTopicSession;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.MessageConsumer;
/*     */ import javax.jms.MessageProducer;
/*     */ import javax.jms.Topic;
/*     */ import javax.jms.TopicPublisher;
/*     */ import javax.jms.TopicSession;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQTopicSession
/*     */   extends MQSession
/*     */   implements TopicSession, JmsTopicSession
/*     */ {
/*     */   private static final long serialVersionUID = -2575226792235452790L;
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.jms.MQTopicSession", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQTopicSession.java");
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
/*     */   MQTopicSession() {
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicSession", "<init>()");
/*     */     }
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicSession", "<init>()");
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
/*     */   public TopicPublisher createPublisher(Topic topic) throws JMSException {
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicSession", "createPublisher(Topic)", new Object[] { topic });
/*     */     }
/*     */     
/*  83 */     JmsTopic theTopic = null;
/*  84 */     if (topic != null) {
/*  85 */       theTopic = (JmsTopic)((MQTopic)topic).validateDestination();
/*     */     }
/*  87 */     MQTopicPublisher wrapper = new MQTopicPublisher();
/*     */     
/*  89 */     wrapper.setDelegate((JmsMessageProducer)((JmsTopicSession)this.commonSess)
/*  90 */         .createPublisher((Topic)theTopic));
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicSession", "createPublisher(Topic)", wrapper);
/*     */     }
/*  94 */     return wrapper;
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
/*     */   public TopicSubscriber createSubscriber(Topic topic) throws JMSException {
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicSession", "createSubscriber(Topic)", new Object[] { topic });
/*     */     }
/*     */     
/* 112 */     JmsTopic theTopic = null;
/*     */     
/* 114 */     if (topic != null) {
/* 115 */       theTopic = (JmsTopic)((MQTopic)topic).validateDestination();
/*     */     }
/* 117 */     MQTopicSubscriber wrapper = new MQTopicSubscriber();
/* 118 */     wrapper.setDelegate((JmsMessageConsumer)((JmsTopicSession)this.commonSess)
/* 119 */         .createSubscriber((Topic)theTopic));
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicSession", "createSubscriber(Topic)", wrapper);
/*     */     }
/* 123 */     return wrapper;
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
/*     */   public TopicSubscriber createSubscriber(Topic topic, String selector, boolean noLocal) throws JMSException {
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicSession", "createSubscriber(Topic,String,boolean)", new Object[] { topic, selector, 
/* 144 */             Boolean.valueOf(noLocal) });
/*     */     }
/* 146 */     JmsTopic theTopic = null;
/*     */     
/* 148 */     if (topic != null) {
/* 149 */       theTopic = (JmsTopic)((MQTopic)topic).validateDestination();
/*     */     }
/* 151 */     MQTopicSubscriber wrapper = new MQTopicSubscriber();
/* 152 */     wrapper.setDelegate((JmsMessageConsumer)((JmsTopicSession)this.commonSess).createSubscriber((Topic)theTopic, selector, noLocal));
/*     */     
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicSession", "createSubscriber(Topic,String,boolean)", wrapper);
/*     */     }
/*     */     
/* 158 */     return wrapper;
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
/*     */   public MessageConsumer createConsumer(Destination destination) throws JMSException {
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicSession", "createConsumer(Destination)", new Object[] { destination });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 178 */     checkConsumerDestinationIsTopic(destination);
/*     */     
/* 180 */     TopicSubscriber topicSubscriber = createSubscriber((MQTopic)destination);
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicSession", "createConsumer(Destination)", topicSubscriber);
/*     */     }
/* 184 */     return (MessageConsumer)topicSubscriber;
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
/*     */   public MessageConsumer createConsumer(Destination destination, String selector) throws JMSException {
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicSession", "createConsumer(Destination,String)", new Object[] { destination, selector });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 205 */     checkConsumerDestinationIsTopic(destination);
/*     */     
/* 207 */     TopicSubscriber topicSubscriber = createSubscriber((MQTopic)destination, selector, false);
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicSession", "createConsumer(Destination,String)", topicSubscriber);
/*     */     }
/*     */     
/* 212 */     return (MessageConsumer)topicSubscriber;
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
/*     */   public MessageConsumer createConsumer(Destination destination, String selector, boolean noLocal) throws JMSException {
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicSession", "createConsumer(Destination,String,boolean)", new Object[] { destination, selector, 
/*     */             
/* 231 */             Boolean.valueOf(noLocal) });
/*     */     }
/*     */ 
/*     */     
/* 235 */     checkConsumerDestinationIsTopic(destination);
/*     */     
/* 237 */     TopicSubscriber topicSubscriber = createSubscriber((MQTopic)destination, selector, noLocal);
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicSession", "createConsumer(Destination,String,boolean)", topicSubscriber);
/*     */     }
/*     */     
/* 242 */     return (MessageConsumer)topicSubscriber;
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
/*     */   public MessageProducer createProducer(Destination destination) throws JMSException {
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicSession", "createProducer(Destination)", new Object[] { destination });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 261 */     checkProducerDestinationIsTopic(destination);
/*     */     
/* 263 */     TopicPublisher topicPublisher = createPublisher((MQTopic)destination);
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicSession", "createProducer(Destination)", topicPublisher);
/*     */     }
/* 267 */     return (MessageProducer)topicPublisher;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQTopicSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */