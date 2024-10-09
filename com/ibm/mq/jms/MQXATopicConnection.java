/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsSession;
/*     */ import com.ibm.msg.client.jms.JmsXATopicConnection;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.XASession;
/*     */ import javax.jms.XATopicConnection;
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
/*     */ 
/*     */ 
/*     */ public class MQXATopicConnection
/*     */   extends MQTopicConnection
/*     */   implements XATopicConnection, JmsXATopicConnection
/*     */ {
/*     */   private static final long serialVersionUID = -5472632978553536660L;
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.mq.jms.MQXATopicConnection", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQXATopicConnection.java");
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
/*     */   MQXATopicConnection() {
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicConnection", "<init>()");
/*     */     }
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicConnection", "<init>()");
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
/*     */   public XASession createXASession() throws JMSException {
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicConnection", "createXASession()");
/*     */     }
/*  81 */     XATopicSession xATopicSession = createXATopicSession();
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicConnection", "createXASession()", xATopicSession);
/*     */     }
/*  85 */     return (XASession)xATopicSession;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XATopicSession createXATopicSession() throws JMSException {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, "com.ibm.mq.jms.MQXATopicConnection", "createXATopicSession()");
/*     */     }
/*  99 */     MQXATopicSession wrapper = new MQXATopicSession();
/* 100 */     wrapper.setDelegate((JmsSession)((JmsXATopicConnection)this.commonConn)
/* 101 */         .createXATopicSession());
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.exit(this, "com.ibm.mq.jms.MQXATopicConnection", "createXATopicSession()", wrapper);
/*     */     }
/*     */     
/* 106 */     return wrapper;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQXATopicConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */