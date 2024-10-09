/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.jms.JMSMessage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsTopic;
/*     */ import com.ibm.msg.client.jms.JmsTopicPublisher;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Message;
/*     */ import javax.jms.TemporaryTopic;
/*     */ import javax.jms.Topic;
/*     */ import javax.jms.TopicPublisher;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQTopicPublisher
/*     */   extends MQMessageProducer
/*     */   implements TopicPublisher, JmsTopicPublisher
/*     */ {
/*     */   private static final long serialVersionUID = -8309134733986131222L;
/*     */   
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.mq.jms.MQTopicPublisher", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQTopicPublisher.java");
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
/*     */   MQTopicPublisher() {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicPublisher", "<init>()");
/*     */     }
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicPublisher", "<init>()");
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
/*     */   public Topic getTopic() throws JMSException {
/*  78 */     Topic traceRet1 = null;
/*  79 */     Topic dest = ((JmsTopicPublisher)this.commonProducer).getTopic();
/*     */ 
/*     */     
/*  82 */     if (dest instanceof TemporaryTopic) {
/*  83 */       traceRet1 = new MQTemporaryTopic((TemporaryTopic)dest);
/*     */     } else {
/*  85 */       traceRet1 = dest;
/*     */     } 
/*     */     
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.data(this, "com.ibm.mq.jms.MQTopicPublisher", "getTopic()", "getter", traceRet1);
/*     */     }
/*  91 */     return traceRet1;
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
/*     */   public void publish(Message message) throws JMSException {
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicPublisher", "publish(Message)", new Object[] { message });
/*     */     }
/*     */     
/* 107 */     Message msg = (message instanceof JMSMessage) ? ((JMSMessage)message).getDelegate() : message;
/* 108 */     ((JmsTopicPublisher)this.commonProducer).publish(msg);
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicPublisher", "publish(Message)");
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
/*     */   public void publish(Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicPublisher", "publish(Message,int,int,long)", new Object[] { message, 
/* 129 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), 
/* 130 */             Long.valueOf(timeToLive) });
/*     */     }
/* 132 */     Message msg = (message instanceof JMSMessage) ? ((JMSMessage)message).getDelegate() : message;
/* 133 */     ((JmsTopicPublisher)this.commonProducer).publish(msg, deliveryMode, priority, timeToLive);
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicPublisher", "publish(Message,int,int,long)");
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
/*     */   public void publish(Topic topic, Message message) throws JMSException {
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicPublisher", "publish(Topic,Message)", new Object[] { topic, message });
/*     */     }
/*     */     
/* 154 */     JmsTopic t = (topic == null) ? null : (JmsTopic)((MQTopic)topic).validateDestination();
/* 155 */     Message msg = (message instanceof JMSMessage) ? ((JMSMessage)message).getDelegate() : message;
/* 156 */     ((JmsTopicPublisher)this.commonProducer).publish((Topic)t, msg);
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicPublisher", "publish(Topic,Message)");
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
/*     */   public void publish(Topic topic, Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicPublisher", "publish(Topic,Message,int,int,long)", new Object[] { topic, message, 
/* 179 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), 
/* 180 */             Long.valueOf(timeToLive) });
/*     */     }
/* 182 */     JmsTopic t = (topic == null) ? null : (JmsTopic)((MQTopic)topic).validateDestination();
/* 183 */     Message msg = (message instanceof JMSMessage) ? ((JMSMessage)message).getDelegate() : message;
/* 184 */     ((JmsTopicPublisher)this.commonProducer).publish((Topic)t, msg, deliveryMode, priority, timeToLive);
/* 185 */     if (Trace.isOn)
/* 186 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicPublisher", "publish(Topic,Message,int,int,long)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQTopicPublisher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */