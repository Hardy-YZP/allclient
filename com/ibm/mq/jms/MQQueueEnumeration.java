/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.jms.JMSMessage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Enumeration;
/*     */ import javax.jms.Message;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQQueueEnumeration
/*     */   implements Enumeration
/*     */ {
/*     */   Enumeration commonQEnum;
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.jms.MQQueueEnumeration", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQQueueEnumeration.java");
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
/*     */   MQQueueEnumeration() {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueEnumeration", "<init>()");
/*     */     }
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueEnumeration", "<init>()");
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
/*     */   public boolean hasMoreElements() {
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueEnumeration", "hasMoreElements()");
/*     */     }
/*  91 */     boolean traceRet1 = this.commonQEnum.hasMoreElements();
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueEnumeration", "hasMoreElements()", 
/*  94 */           Boolean.valueOf(traceRet1));
/*     */     }
/*  96 */     return traceRet1;
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
/*     */   public Message nextElement() {
/*     */     JMSMessage jMSMessage;
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.entry(this, "com.ibm.mq.jms.MQQueueEnumeration", "nextElement()");
/*     */     }
/* 112 */     Message traceRet1 = this.commonQEnum.nextElement();
/*     */     
/* 114 */     if (traceRet1 != null) {
/* 115 */       jMSMessage = MQSession.MessageProxy.wrapMessage(traceRet1);
/*     */     }
/*     */     
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.exit(this, "com.ibm.mq.jms.MQQueueEnumeration", "nextElement()", jMSMessage);
/*     */     }
/* 121 */     return (Message)jMSMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   void setDelegate(Enumeration commonQEnum) {
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.data(this, "com.ibm.mq.jms.MQQueueEnumeration", "setDelegate(Enumeration)", "setter", commonQEnum);
/*     */     }
/*     */     
/* 130 */     this.commonQEnum = commonQEnum;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQQueueEnumeration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */