/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsConnectionConsumer;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import javax.jms.ConnectionConsumer;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.ServerSessionPool;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQConnectionConsumer
/*     */   extends MQRoot
/*     */   implements ConnectionConsumer, JmsConnectionConsumer
/*     */ {
/*     */   private static final long serialVersionUID = 5666658157993758875L;
/*     */   private JmsConnectionConsumer delegateConConsumer;
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.jms.MQConnectionConsumer", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQConnectionConsumer.java");
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
/*     */   MQConnectionConsumer() {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionConsumer", "<init>()");
/*     */     }
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionConsumer", "<init>()");
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
/*     */   public void close() throws JMSException {
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionConsumer", "close()");
/*     */     }
/*  78 */     this.delegateConConsumer.close();
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionConsumer", "close()");
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
/*     */   public ServerSessionPool getServerSessionPool() throws JMSException {
/*  93 */     ServerSessionPool traceRet1 = this.delegateConConsumer.getServerSessionPool();
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionConsumer", "getServerSessionPool()", "getter", traceRet1);
/*     */     }
/*     */     
/*  98 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   void setDelegate(JmsConnectionConsumer delegateConConsumer) {
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionConsumer", "setDelegate(JmsConnectionConsumer)", "setter", delegateConConsumer);
/*     */     }
/*     */     
/* 107 */     this.delegateConConsumer = delegateConConsumer;
/* 108 */     this.delegate = (JmsPropertyContext)delegateConConsumer;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQConnectionConsumer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */