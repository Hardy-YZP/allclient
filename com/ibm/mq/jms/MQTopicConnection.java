/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsConnectionConsumer;
/*     */ import com.ibm.msg.client.jms.JmsSession;
/*     */ import com.ibm.msg.client.jms.JmsTopic;
/*     */ import com.ibm.msg.client.jms.JmsTopicConnection;
/*     */ import javax.jms.ConnectionConsumer;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.ServerSessionPool;
/*     */ import javax.jms.Session;
/*     */ import javax.jms.Topic;
/*     */ import javax.jms.TopicConnection;
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
/*     */ public class MQTopicConnection
/*     */   extends MQConnection
/*     */   implements TopicConnection, JmsTopicConnection
/*     */ {
/*     */   private static final long serialVersionUID = -9092277000304699464L;
/*     */   
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.mq.jms.MQTopicConnection", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQTopicConnection.java");
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
/*     */   MQTopicConnection() {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicConnection", "<init>()");
/*     */     }
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicConnection", "<init>()");
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
/*     */   public ConnectionConsumer createConnectionConsumer(Topic topic, String selector, ServerSessionPool ssp, int maxMessages) throws JMSException {
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicConnection", "createConnectionConsumer(Topic,String,ServerSessionPool,int)", new Object[] { topic, selector, ssp, 
/*     */             
/*  83 */             Integer.valueOf(maxMessages) });
/*     */     }
/*     */     
/*  86 */     MQConnectionConsumer wrapper = new MQConnectionConsumer();
/*     */     
/*  88 */     JmsTopic t = (topic == null) ? null : (JmsTopic)((MQTopic)topic).validateDestination();
/*     */     
/*  90 */     wrapper.setDelegate((JmsConnectionConsumer)((JmsTopicConnection)this.delegate)
/*  91 */         .createConnectionConsumer((Topic)t, selector, ssp, maxMessages));
/*     */     
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicConnection", "createConnectionConsumer(Topic,String,ServerSessionPool,int)", wrapper);
/*     */     }
/*     */     
/*  97 */     return wrapper;
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
/*     */   public TopicSession createTopicSession(boolean transacted, int acknowledgeMode) throws JMSException {
/* 113 */     if (Trace.isOn)
/* 114 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicConnection", "createTopicSession(boolean,int)", new Object[] {
/* 115 */             Boolean.valueOf(transacted), Integer.valueOf(acknowledgeMode)
/*     */           }); 
/* 117 */     MQTopicSession wrapper = new MQTopicSession();
/* 118 */     wrapper.setDelegate((JmsSession)((JmsTopicConnection)this.delegate).createTopicSession(transacted, acknowledgeMode));
/*     */     
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicConnection", "createTopicSession(boolean,int)", wrapper);
/*     */     }
/*     */     
/* 124 */     return wrapper;
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
/*     */   public Session createSession(boolean arg0, int arg1) throws JMSException {
/* 138 */     if (Trace.isOn)
/* 139 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicConnection", "createSession(boolean,int)", new Object[] {
/* 140 */             Boolean.valueOf(arg0), Integer.valueOf(arg1)
/*     */           }); 
/* 142 */     TopicSession topicSession = createTopicSession(arg0, arg1);
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicConnection", "createSession(boolean,int)", topicSession);
/*     */     }
/*     */     
/* 147 */     return (Session)topicSession;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQTopicConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */