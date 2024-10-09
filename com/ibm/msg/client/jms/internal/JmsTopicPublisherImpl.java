/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsTopicPublisher;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Message;
/*     */ import javax.jms.Topic;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsTopicPublisherImpl
/*     */   extends JmsMessageProducerImpl
/*     */   implements JmsTopicPublisher
/*     */ {
/*     */   private static final long serialVersionUID = 3558935369273881843L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsTopicPublisherImpl.java";
/*     */   Topic topic;
/*     */   
/*     */   static {
/*  42 */     if (Trace.isOn) {
/*  43 */       Trace.data("com.ibm.msg.client.jms.internal.JmsTopicPublisherImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsTopicPublisherImpl.java");
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
/*     */   JmsTopicPublisherImpl(Topic topic, JmsSessionImpl session) throws JMSException {
/*  68 */     super((Destination)topic, session);
/*  69 */     if (Trace.isOn) {
/*  70 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTopicPublisherImpl", "<init>(Topic,JmsSessionImpl)", new Object[] { topic, session });
/*     */     }
/*     */ 
/*     */     
/*  74 */     this.topic = topic;
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTopicPublisherImpl", "<init>(Topic,JmsSessionImpl)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Topic getTopic() throws JMSException {
/*  87 */     this.state.checkNotClosed("JMSCC0026");
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsTopicPublisherImpl", "getTopic()", "getter", this.topic);
/*     */     }
/*     */     
/*  92 */     return this.topic;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void publish(Message message, int deliveryMode, int priority, long expiry) throws JMSException {
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTopicPublisherImpl", "publish(Message,int,int,long)", new Object[] { message, 
/* 103 */             Integer.valueOf(deliveryMode), 
/* 104 */             Integer.valueOf(priority), Long.valueOf(expiry) });
/*     */     }
/*     */     
/* 107 */     send(message, deliveryMode, priority, expiry);
/*     */     
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTopicPublisherImpl", "publish(Message,int,int,long)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void publish(Message message) throws JMSException {
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTopicPublisherImpl", "publish(Message)", new Object[] { message });
/*     */     }
/*     */ 
/*     */     
/* 126 */     send(message);
/*     */     
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTopicPublisherImpl", "publish(Message)");
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
/*     */   public void publish(Topic topic, Message message, int deliveryMode, int priority, long expiry) throws JMSException {
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTopicPublisherImpl", "publish(Topic,Message,int,int,long)", new Object[] { topic, message, 
/*     */             
/* 144 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), Long.valueOf(expiry) });
/*     */     }
/*     */     
/* 147 */     send((Destination)topic, message, deliveryMode, priority, expiry);
/*     */     
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTopicPublisherImpl", "publish(Topic,Message,int,int,long)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void publish(Topic topic, Message message) throws JMSException {
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTopicPublisherImpl", "publish(Topic,Message)", new Object[] { topic, message });
/*     */     }
/*     */ 
/*     */     
/* 166 */     send((Destination)topic, message);
/*     */     
/* 168 */     if (Trace.isOn)
/* 169 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTopicPublisherImpl", "publish(Topic,Message)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsTopicPublisherImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */