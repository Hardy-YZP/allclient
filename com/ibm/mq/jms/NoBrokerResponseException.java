/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ @Deprecated
/*     */ public class NoBrokerResponseException
/*     */   extends JMSException
/*     */ {
/*     */   private static final long serialVersionUID = 8130060094775890665L;
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.jms.NoBrokerResponseException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/NoBrokerResponseException.java");
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
/*     */   protected NoBrokerResponseException(String str) {
/*  61 */     super(str);
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.entry(this, "com.ibm.mq.jms.NoBrokerResponseException", "<init>(String)", new Object[] { str });
/*     */     }
/*     */     
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.exit(this, "com.ibm.mq.jms.NoBrokerResponseException", "<init>(String)");
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
/*     */   public NoBrokerResponseException(String str, String errorCode) {
/*  80 */     super(str, errorCode);
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.entry(this, "com.ibm.mq.jms.NoBrokerResponseException", "<init>(String,String)", new Object[] { str, errorCode });
/*     */     }
/*     */     
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.exit(this, "com.ibm.mq.jms.NoBrokerResponseException", "<init>(String,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLinkedException(Exception exception) {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.data(this, "com.ibm.mq.jms.NoBrokerResponseException", "setLinkedException(Exception)", "setter", exception);
/*     */     }
/*     */     
/* 100 */     super.setLinkedException(exception);
/*     */     try {
/* 102 */       initCause(exception);
/*     */     }
/* 104 */     catch (IllegalStateException ise) {
/* 105 */       if (Trace.isOn)
/* 106 */         Trace.catchBlock(this, "com.ibm.mq.jms.NoBrokerResponseException", "setLinkedException(Exception)", ise); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\NoBrokerResponseException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */