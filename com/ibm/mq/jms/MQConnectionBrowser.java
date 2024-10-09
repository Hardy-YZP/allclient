/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsConnectionBrowser;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQConnectionBrowser
/*     */   extends MQRoot
/*     */   implements ConnectionBrowser, JmsConnectionBrowser
/*     */ {
/*     */   private static final long serialVersionUID = 5666658157993758879L;
/*     */   JmsConnectionBrowser commonConnBrowser;
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.jms.MQConnectionBrowser", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQConnectionBrowser.java");
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
/*     */   public MQConnectionBrowser() {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionBrowser", "<init>()");
/*     */     }
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionBrowser", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws JMSException {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionBrowser", "close()");
/*     */     }
/*  80 */     this.commonConnBrowser.close();
/*     */     
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionBrowser", "close()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setDelegate(JmsConnectionBrowser commonConnBrowser) {
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionBrowser", "setDelegate(JmsConnectionBrowser)", "setter", commonConnBrowser);
/*     */     }
/*     */     
/*  94 */     this.commonConnBrowser = commonConnBrowser;
/*  95 */     this.delegate = (JmsPropertyContext)commonConnBrowser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionBrowser", "toString()");
/*     */     }
/* 107 */     String traceRet1 = this.commonConnBrowser.toString();
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionBrowser", "toString()", traceRet1);
/*     */     }
/* 111 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQConnectionBrowser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */