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
/*     */ public class DefaultAdapter
/*     */   extends Adapter
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/DefaultAdapter.java";
/*     */   
/*     */   static {
/*  33 */     if (Trace.isOn) {
/*  34 */       Trace.data("com.ibm.mq.jmqi.local.internal.adapters.DefaultAdapter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/DefaultAdapter.java");
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
/*     */   public DefaultAdapter(JmqiEnvironment env, LocalMQ mq) {
/*  49 */     super(env, mq);
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.DefaultAdapter", "<init>(JmqiEnvironment,LocalMQ)", new Object[] { env, mq });
/*     */     }
/*     */     
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.DefaultAdapter", "<init>(JmqiEnvironment,LocalMQ)");
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
/*  66 */     String traceRet1 = this.mq.getLibraryName();
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.DefaultAdapter", "getLibraryName()", "getter", traceRet1);
/*     */     }
/*     */     
/*  71 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSharedHandlesSupported() {
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.DefaultAdapter", "isSharedHandlesSupported()", "getter", 
/*  81 */           Boolean.valueOf(true));
/*     */     }
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWorkerThreadSupported() {
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.DefaultAdapter", "isWorkerThreadSupported()", "getter", 
/*  93 */           Boolean.valueOf(true));
/*     */     }
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRXPBFlags() {
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.DefaultAdapter", "getRXPBFlags()", "getter", 
/* 105 */           Integer.valueOf(0));
/*     */     }
/* 107 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\adapters\DefaultAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */