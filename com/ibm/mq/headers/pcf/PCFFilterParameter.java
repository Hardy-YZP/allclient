/*     */ package com.ibm.mq.headers.pcf;
/*     */ 
/*     */ import com.ibm.mq.headers.internal.HeaderType;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PCFFilterParameter
/*     */   extends PCFParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFFilterParameter.java";
/*     */   private static final int HEADER_VERSION = 3;
/*     */   
/*     */   static {
/*  34 */     if (Trace.isOn) {
/*  35 */       Trace.data("com.ibm.mq.headers.pcf.PCFFilterParameter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFFilterParameter.java");
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
/*     */   protected PCFFilterParameter(HeaderType type) {
/*  48 */     super(type);
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFFilterParameter", "<init>(HeaderType)", new Object[] { type });
/*     */     }
/*     */     
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFFilterParameter", "<init>(HeaderType)");
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
/*     */   public boolean equals(Object obj) {
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFFilterParameter", "equals(Object)", new Object[] { obj });
/*     */     }
/*     */     
/*  79 */     if (obj != null && obj instanceof PCFFilterParameter) {
/*  80 */       PCFFilterParameter other = (PCFFilterParameter)obj;
/*     */ 
/*     */       
/*  83 */       boolean traceRet1 = (other.getType() == getType() && other.getParameter() == getParameter() && other.getOperator() == getOperator() && other.getValue().equals(getValue()));
/*  84 */       if (Trace.isOn) {
/*  85 */         Trace.exit(this, "com.ibm.mq.headers.pcf.PCFFilterParameter", "equals(Object)", 
/*  86 */             Boolean.valueOf(traceRet1), 1);
/*     */       }
/*  88 */       return traceRet1;
/*     */     } 
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFFilterParameter", "equals(Object)", 
/*  92 */           Boolean.valueOf(false), 2);
/*     */     }
/*  94 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFFilterParameter", "hashCode()");
/*     */     }
/* 105 */     int hashCode = 0;
/* 106 */     hashCode += getParameter() * 31;
/* 107 */     hashCode += getOperator() * 37;
/* 108 */     hashCode += getValue().hashCode();
/*     */     
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFFilterParameter", "hashCode()", 
/* 112 */           Integer.valueOf(hashCode));
/*     */     }
/* 114 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeaderVersion() {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFFilterParameter", "getHeaderVersion()", "getter", 
/* 124 */           Integer.valueOf(3));
/*     */     }
/* 126 */     return 3;
/*     */   }
/*     */   
/*     */   public abstract int getOperator();
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\PCFFilterParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */