/*     */ package com.ibm.mq.ese.service;
/*     */ 
/*     */ import com.ibm.mq.ese.core.AMBIException;
/*     */ import com.ibm.mq.jmqi.JmqiException;
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
/*     */ public class EseMQException
/*     */   extends AMBIException
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/EseMQException.java";
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.ese.service.EseMQException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/EseMQException.java");
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
/*  55 */   private int reason = 0;
/*     */   
/*     */   public EseMQException(String msg, String messageid, String explanation, String useraction, HashMap<String, ? extends Object> inserts) {
/*  58 */     super(msg, messageid, explanation, useraction, inserts);
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)", new Object[] { msg, messageid, explanation, useraction, inserts });
/*     */     }
/*     */ 
/*     */     
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EseMQException(String messageid, HashMap<String, ? extends Object> inserts) {
/*  72 */     super(messageid, inserts);
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQException", "<init>(String,HashMap<String , ? extends Object>)", new Object[] { messageid, inserts });
/*     */     }
/*     */     
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQException", "<init>(String,HashMap<String , ? extends Object>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EseMQException(String messageid, HashMap<String, ? extends Object> inserts, Throwable cause) {
/*  85 */     super(messageid, inserts, cause);
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQException", "<init>(String,HashMap<String , ? extends Object>,Throwable)", new Object[] { messageid, inserts, cause });
/*     */     }
/*     */ 
/*     */     
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQException", "<init>(String,HashMap<String , ? extends Object>,Throwable)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EseMQException(Exception e, int reason) {
/*  99 */     super(e);
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQException", "<init>(Exception,int)", new Object[] { e, 
/* 102 */             Integer.valueOf(reason) });
/*     */     }
/* 104 */     this.reason = reason;
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQException", "<init>(Exception,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EseMQException(JmqiException root) {
/* 117 */     super((Throwable)root);
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.entry(this, "com.ibm.mq.ese.service.EseMQException", "<init>(JmqiException)", new Object[] { root });
/*     */     }
/*     */     
/* 122 */     this.reason = root.getReason();
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.exit(this, "com.ibm.mq.ese.service.EseMQException", "<init>(JmqiException)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReason() {
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.data(this, "com.ibm.mq.ese.service.EseMQException", "getReason()", "getter", 
/* 136 */           Integer.valueOf(this.reason));
/*     */     }
/* 138 */     return this.reason;
/*     */   }
/*     */   
/*     */   public void setReason(int reason) {
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.data(this, "com.ibm.mq.ese.service.EseMQException", "setReason(int)", "setter", 
/* 144 */           Integer.valueOf(reason));
/*     */     }
/* 146 */     this.reason = reason;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\service\EseMQException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */