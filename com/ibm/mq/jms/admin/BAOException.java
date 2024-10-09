/*     */ package com.ibm.mq.jms.admin;
/*     */ 
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
/*     */ public class BAOException
/*     */   extends Exception
/*     */ {
/*     */   private static final long serialVersionUID = -8689370277673632394L;
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/BAOException.java";
/*     */   private int errorFlag;
/*     */   private String s1;
/*     */   private String s2;
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.mq.jms.admin.BAOException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/BAOException.java");
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
/*     */ 
/*     */   
/*     */   public BAOException(int flag, String a, Object value) {
/*  65 */     if (Trace.isOn)
/*  66 */       Trace.entry(this, "com.ibm.mq.jms.admin.BAOException", "<init>(int,String,Object)", new Object[] {
/*  67 */             Integer.valueOf(flag), a, value
/*     */           }); 
/*  69 */     this.errorFlag = flag;
/*  70 */     this.s1 = a;
/*  71 */     this.s2 = String.valueOf(value);
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.exit(this, "com.ibm.mq.jms.admin.BAOException", "<init>(int,String,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getErrorFlag() {
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.data(this, "com.ibm.mq.jms.admin.BAOException", "getErrorFlag()", "getter", 
/*  84 */           Integer.valueOf(this.errorFlag));
/*     */     }
/*  86 */     return this.errorFlag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString1() {
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.data(this, "com.ibm.mq.jms.admin.BAOException", "getString1()", "getter", this.s1);
/*     */     }
/*  96 */     return this.s1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString2() {
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.data(this, "com.ibm.mq.jms.admin.BAOException", "getString2()", "getter", this.s2);
/*     */     }
/* 106 */     return this.s2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.entry(this, "com.ibm.mq.jms.admin.BAOException", "toString()");
/*     */     }
/* 117 */     StringBuffer sb = new StringBuffer();
/* 118 */     sb.append(this.errorFlag);
/*     */     
/* 120 */     if (this.s1 != null) {
/* 121 */       sb.append(" s1=" + this.s1);
/*     */     }
/* 123 */     if (this.s2 != null) {
/* 124 */       sb.append(" s2=" + this.s2);
/*     */     }
/*     */     
/* 127 */     String traceRet1 = sb.toString();
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.exit(this, "com.ibm.mq.jms.admin.BAOException", "toString()", traceRet1);
/*     */     }
/* 131 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\BAOException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */