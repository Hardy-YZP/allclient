/*     */ package com.ibm.mq.ese.core;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IncorrectHeaderException
/*     */   extends AMBIException
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/IncorrectHeaderException.java";
/*     */   private static final long serialVersionUID = -8918431526409732947L;
/*     */   int reason;
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.ese.core.IncorrectHeaderException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/IncorrectHeaderException.java");
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
/*     */   public IncorrectHeaderException(String msg, String messageid, String explanation, String useraction, HashMap<String, ? extends Object> inserts) {
/*  52 */     super(msg, messageid, explanation, useraction, inserts);
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.entry(this, "com.ibm.mq.ese.core.IncorrectHeaderException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)", new Object[] { msg, messageid, explanation, useraction, inserts });
/*     */     }
/*     */ 
/*     */     
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.exit(this, "com.ibm.mq.ese.core.IncorrectHeaderException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IncorrectHeaderException(String messageid, HashMap<String, ? extends Object> inserts) {
/*  66 */     super(messageid, inserts);
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.entry(this, "com.ibm.mq.ese.core.IncorrectHeaderException", "<init>(String,HashMap<String , ? extends Object>)", new Object[] { messageid, inserts });
/*     */     }
/*     */     
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.exit(this, "com.ibm.mq.ese.core.IncorrectHeaderException", "<init>(String,HashMap<String , ? extends Object>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IncorrectHeaderException(String messageid) {
/*  79 */     super(messageid);
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.entry(this, "com.ibm.mq.ese.core.IncorrectHeaderException", "<init>(String)", new Object[] { messageid });
/*     */     }
/*     */     
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.exit(this, "com.ibm.mq.ese.core.IncorrectHeaderException", "<init>(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IncorrectHeaderException(String messageid, Throwable cause) {
/*  91 */     super(messageid, cause);
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.entry(this, "com.ibm.mq.ese.core.IncorrectHeaderException", "<init>(String,Throwable)", new Object[] { messageid, cause });
/*     */     }
/*     */     
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.exit(this, "com.ibm.mq.ese.core.IncorrectHeaderException", "<init>(String,Throwable)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReason() {
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.data(this, "com.ibm.mq.ese.core.IncorrectHeaderException", "getReason()", "getter", 
/* 107 */           Integer.valueOf(this.reason));
/*     */     }
/* 109 */     return this.reason;
/*     */   }
/*     */   
/*     */   public void setReason(int reason) {
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.data(this, "com.ibm.mq.ese.core.IncorrectHeaderException", "setReason(int)", "setter", 
/* 115 */           Integer.valueOf(reason));
/*     */     }
/* 117 */     this.reason = reason;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\core\IncorrectHeaderException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */