/*     */ package com.ibm.mq.jmqi.local.internal.adapters;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.local.LocalMQ;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WindowsAdapter64
/*     */   extends Adapter
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/WindowsAdapter64.java";
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.jmqi.local.internal.adapters.WindowsAdapter64", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/WindowsAdapter64.java");
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
/*     */   public WindowsAdapter64(JmqiEnvironment env, LocalMQ mq) {
/*  59 */     super(env, mq);
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.WindowsAdapter64", "<init>(JmqiEnvironment,LocalMQ)", new Object[] { env, mq });
/*     */     }
/*     */     
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.WindowsAdapter64", "<init>(JmqiEnvironment,LocalMQ)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLibraryName() {
/*  76 */     String traceRet1 = this.mq.getWindowsLibraryName64();
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.WindowsAdapter64", "getLibraryName()", "getter", traceRet1);
/*     */     }
/*     */     
/*  81 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRXPBFlags() {
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.WindowsAdapter64", "getRXPBFlags()", "getter", 
/*  91 */           Integer.valueOf(0));
/*     */     }
/*  93 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSharedHandlesSupported() {
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.WindowsAdapter64", "isSharedHandlesSupported()", "getter", 
/* 103 */           Boolean.valueOf(true));
/*     */     }
/* 105 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWorkerThreadSupported() {
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.WindowsAdapter64", "isWorkerThreadSupported()", "getter", 
/* 115 */           Boolean.valueOf(true));
/*     */     }
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAlternateLibraryName() {
/* 127 */     String traceRet1 = this.mq.getWindowsLibraryName();
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.WindowsAdapter64", "getAlternateLibraryName()", "getter", traceRet1);
/*     */     }
/*     */     
/* 132 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\adapters\WindowsAdapter64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */