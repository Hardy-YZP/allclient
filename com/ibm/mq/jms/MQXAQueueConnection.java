/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsSession;
/*     */ import com.ibm.msg.client.jms.JmsXAQueueConnection;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.XAQueueConnection;
/*     */ import javax.jms.XAQueueSession;
/*     */ import javax.jms.XASession;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQXAQueueConnection
/*     */   extends MQQueueConnection
/*     */   implements XAQueueConnection, JmsXAQueueConnection
/*     */ {
/*     */   private static final long serialVersionUID = -527652479579361244L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQXAQueueConnection.java";
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.mq.jms.MQXAQueueConnection", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQXAQueueConnection.java");
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
/*     */   MQXAQueueConnection() {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.entry(this, "com.ibm.mq.jms.MQXAQueueConnection", "<init>()");
/*     */     }
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.exit(this, "com.ibm.mq.jms.MQXAQueueConnection", "<init>()");
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
/*     */   public XAQueueSession createXAQueueSession() throws JMSException {
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.entry(this, "com.ibm.mq.jms.MQXAQueueConnection", "createXAQueueSession()");
/*     */     }
/*  85 */     MQXAQueueSession wrapper = new MQXAQueueSession();
/*  86 */     wrapper.setDelegate((JmsSession)((JmsXAQueueConnection)this.commonConn)
/*  87 */         .createXAQueueSession());
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit(this, "com.ibm.mq.jms.MQXAQueueConnection", "createXAQueueSession()", wrapper);
/*     */     }
/*     */     
/*  92 */     return wrapper;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XASession createXASession() throws JMSException {
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.entry(this, "com.ibm.mq.jms.MQXAQueueConnection", "createXASession()");
/*     */     }
/* 105 */     XAQueueSession xAQueueSession = createXAQueueSession();
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.exit(this, "com.ibm.mq.jms.MQXAQueueConnection", "createXASession()", xAQueueSession);
/*     */     }
/* 109 */     return (XASession)xAQueueSession;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQXAQueueConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */