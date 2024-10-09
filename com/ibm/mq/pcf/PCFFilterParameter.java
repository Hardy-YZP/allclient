/*     */ package com.ibm.mq.pcf;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public abstract class PCFFilterParameter
/*     */   extends PCFParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFFilterParameter.java";
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.mq.pcf.PCFFilterParameter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFFilterParameter.java");
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
/*     */   protected PCFFilterParameter(HeaderType type) {
/*  54 */     super(type);
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.entry(this, "com.ibm.mq.pcf.PCFFilterParameter", "<init>(HeaderType)", new Object[] { type });
/*     */     }
/*     */     
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.exit(this, "com.ibm.mq.pcf.PCFFilterParameter", "<init>(HeaderType)");
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
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.entry(this, "com.ibm.mq.pcf.PCFFilterParameter", "equals(Object)", new Object[] { obj });
/*     */     }
/*     */     
/*  85 */     if (obj != null && obj instanceof PCFFilterParameter) {
/*  86 */       PCFFilterParameter other = (PCFFilterParameter)obj;
/*     */ 
/*     */       
/*  89 */       boolean traceRet1 = (other.getType() == getType() && other.getParameter() == getParameter() && other.getOperator() == getOperator() && other.getValue().equals(getValue()));
/*  90 */       if (Trace.isOn) {
/*  91 */         Trace.exit(this, "com.ibm.mq.pcf.PCFFilterParameter", "equals(Object)", 
/*  92 */             Boolean.valueOf(traceRet1), 1);
/*     */       }
/*  94 */       return traceRet1;
/*     */     } 
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.exit(this, "com.ibm.mq.pcf.PCFFilterParameter", "equals(Object)", 
/*  98 */           Boolean.valueOf(false), 2);
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.entry(this, "com.ibm.mq.pcf.PCFFilterParameter", "hashCode()");
/*     */     }
/* 111 */     int hashCode = 0;
/* 112 */     hashCode += getParameter() * 31;
/* 113 */     hashCode += getOperator() * 37;
/* 114 */     hashCode += getValue().hashCode();
/*     */     
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.exit(this, "com.ibm.mq.pcf.PCFFilterParameter", "hashCode()", 
/* 118 */           Integer.valueOf(hashCode));
/*     */     }
/* 120 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeaderVersion() {
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.data(this, "com.ibm.mq.pcf.PCFFilterParameter", "getHeaderVersion()", "getter", 
/* 130 */           Integer.valueOf(3));
/*     */     }
/* 132 */     return 3;
/*     */   }
/*     */   
/*     */   public abstract int getOperator();
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\PCFFilterParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */