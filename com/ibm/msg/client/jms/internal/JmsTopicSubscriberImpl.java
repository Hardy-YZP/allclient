/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsTopicSubscriber;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
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
/*     */ 
/*     */ 
/*     */ public class JmsTopicSubscriberImpl
/*     */   extends JmsMessageConsumerImpl
/*     */   implements JmsTopicSubscriber
/*     */ {
/*     */   private static final long serialVersionUID = 1389150200802673064L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsTopicSubscriberImpl.java";
/*     */   private Topic topic;
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.msg.client.jms.internal.JmsTopicSubscriberImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsTopicSubscriberImpl.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   JmsTopicSubscriberImpl(JmsSessionImpl session, Topic topic, String selector, boolean noLocal) throws JMSException {
/*  73 */     this(session, topic, selector, noLocal, (String)null, false, false);
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTopicSubscriberImpl", "<init>(JmsSessionImpl,Topic,String,boolean)", new Object[] { session, topic, selector, 
/*     */             
/*  77 */             Boolean.valueOf(noLocal) });
/*     */     }
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTopicSubscriberImpl", "<init>(JmsSessionImpl,Topic,String,boolean)");
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
/*     */   JmsTopicSubscriberImpl(JmsSessionImpl session, Topic topic, String selector, boolean noLocal, String subscriptionName, boolean shared, boolean durable) throws JMSException {
/* 105 */     super(session, (Destination)topic, selector, noLocal, subscriptionName, shared, durable);
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTopicSubscriberImpl", "<init>(JmsSessionImpl,Topic,String,boolean,String,boolean,boolean)", new Object[] { session, topic, selector, 
/*     */             
/* 109 */             Boolean.valueOf(noLocal), subscriptionName, Boolean.valueOf(shared), 
/* 110 */             Boolean.valueOf(durable) });
/*     */     }
/*     */     
/* 113 */     this.topic = topic;
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTopicSubscriberImpl", "<init>(JmsSessionImpl,Topic,String,boolean,String,boolean,boolean)");
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
/*     */   public boolean getNoLocal() throws JMSException {
/* 127 */     this.state.checkNotClosed("JMSCC0032");
/*     */     
/* 129 */     boolean traceRet1 = getBooleanProperty("XMSC_NOLOCAL");
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsTopicSubscriberImpl", "getNoLocal()", "getter", 
/* 132 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 134 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Topic getTopic() throws JMSException {
/* 142 */     this.state.checkNotClosed("JMSCC0032");
/* 143 */     if (this.closed) {
/* 144 */       JMSException traceRet1 = (JMSException)JmsErrorUtils.createException("JMSCC0026", null);
/*     */       
/* 146 */       if (Trace.isOn) {
/* 147 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsTopicSubscriberImpl", "getTopic()", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 150 */       throw traceRet1;
/*     */     } 
/*     */     
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsTopicSubscriberImpl", "getTopic()", "getter", this.topic);
/*     */     }
/*     */     
/* 157 */     return this.topic;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsTopicSubscriberImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */