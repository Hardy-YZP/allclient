/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsSession;
/*     */ import com.ibm.msg.client.jms.JmsXASession;
/*     */ import com.ibm.msg.client.jms.internal.JmsSessionImpl;
/*     */ import com.ibm.msg.client.jms.internal.JmsXASessionImpl;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Session;
/*     */ import javax.jms.XASession;
/*     */ import javax.transaction.xa.XAResource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQXASession
/*     */   extends MQSession
/*     */   implements XASession
/*     */ {
/*     */   private static final long serialVersionUID = -1126380325678221809L;
/*     */   
/*     */   static {
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.data("com.ibm.mq.jms.MQXASession", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQXASession.java");
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
/*  75 */   protected MQSession backingSession = null;
/*     */   
/*     */   MQXASession() {
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.entry(this, "com.ibm.mq.jms.MQXASession", "<init>()");
/*     */     }
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.exit(this, "com.ibm.mq.jms.MQXASession", "<init>()");
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
/*     */   public Session getSession() throws JMSException {
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.entry(this, "com.ibm.mq.jms.MQXASession", "getSession()");
/*     */     }
/*     */     
/* 102 */     if (null == this.backingSession) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 108 */       JmsSession js = getDelegate();
/* 109 */       JmsXASessionImpl jxsi = (JmsXASessionImpl)js;
/* 110 */       JmsSession backingDelegate = (JmsSession)jxsi.getSession();
/* 111 */       this.backingSession = new MQSession();
/* 112 */       this.backingSession.setDelegate(backingDelegate);
/*     */     } 
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.exit(this, "com.ibm.mq.jms.MQXASession", "getSession()", this.backingSession);
/*     */     }
/* 117 */     return this.backingSession;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XAResource getXAResource() {
/* 127 */     XAResource traceRet1 = ((JmsXASession)this.commonSess).getXAResource();
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.data(this, "com.ibm.mq.jms.MQXASession", "getXAResource()", "getter", traceRet1);
/*     */     }
/* 131 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isClosed() {
/* 139 */     boolean traceRet1 = ((JmsSessionImpl)this.commonSess).isClosed();
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.data(this, "com.ibm.mq.jms.MQXASession", "isClosed()", "getter", 
/* 142 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 144 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQXASession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */