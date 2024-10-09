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
/*     */ public class WindowsAdapter
/*     */   extends Adapter
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/WindowsAdapter.java";
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.jmqi.local.internal.adapters.WindowsAdapter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/WindowsAdapter.java");
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
/*     */   public WindowsAdapter(JmqiEnvironment env, LocalMQ mq) {
/*  52 */     super(env, mq);
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.WindowsAdapter", "<init>(JmqiEnvironment,LocalMQ)", new Object[] { env, mq });
/*     */     }
/*     */     
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.WindowsAdapter", "<init>(JmqiEnvironment,LocalMQ)");
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
/*  69 */     String traceRet1 = this.mq.getWindowsLibraryName();
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.WindowsAdapter", "getLibraryName()", "getter", traceRet1);
/*     */     }
/*     */     
/*  74 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRXPBFlags() {
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.WindowsAdapter", "getRXPBFlags()", "getter", 
/*  84 */           Integer.valueOf(0));
/*     */     }
/*  86 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSharedHandlesSupported() {
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.WindowsAdapter", "isSharedHandlesSupported()", "getter", 
/*  96 */           Boolean.valueOf(true));
/*     */     }
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWorkerThreadSupported() {
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.WindowsAdapter", "isWorkerThreadSupported()", "getter", 
/* 108 */           Boolean.valueOf(true));
/*     */     }
/* 110 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\adapters\WindowsAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */