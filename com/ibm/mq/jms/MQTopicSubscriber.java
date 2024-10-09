/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsTopicSubscriber;
/*     */ import java.util.HashMap;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Queue;
/*     */ import javax.jms.TemporaryTopic;
/*     */ import javax.jms.Topic;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQTopicSubscriber
/*     */   extends MQMessageConsumer
/*     */   implements TopicSubscriber, JmsTopicSubscriber
/*     */ {
/*     */   private static final long serialVersionUID = -1044175374636601629L;
/*     */   
/*     */   static {
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.data("com.ibm.mq.jms.MQTopicSubscriber", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQTopicSubscriber.java");
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
/*     */   MQTopicSubscriber() {
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.entry(this, "com.ibm.mq.jms.MQTopicSubscriber", "<init>()");
/*     */     }
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.exit(this, "com.ibm.mq.jms.MQTopicSubscriber", "<init>()");
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
/*     */   public Topic getTopic() throws JMSException {
/*  84 */     Topic traceRet1 = null;
/*  85 */     Topic dest = ((JmsTopicSubscriber)this.commonConsumer).getTopic();
/*     */ 
/*     */     
/*  88 */     if (dest instanceof TemporaryTopic) {
/*  89 */       traceRet1 = new MQTemporaryTopic((TemporaryTopic)dest);
/*     */     } else {
/*     */       
/*  92 */       traceRet1 = dest;
/*     */     } 
/*     */     
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.data(this, "com.ibm.mq.jms.MQTopicSubscriber", "getTopic()", "getter", traceRet1);
/*     */     }
/*  98 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Queue getQueue() throws JMSException {
/* 109 */     HashMap<String, String> inserts = new HashMap<>();
/* 110 */     inserts.put("XMSC_INSERT_METHOD", "getQueue()");
/* 111 */     inserts.put("XMSC_INSERT_TYPE", "TopicSubscriber");
/* 112 */     JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/*     */ 
/*     */     
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.throwing(this, "com.ibm.mq.jms.MQTopicSubscriber", "getQueue()", (Throwable)je);
/*     */     }
/* 118 */     throw je;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQTopicSubscriber.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */