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
/*     */ public abstract class RRSAdapter
/*     */   extends Adapter
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/RRSAdapter.java";
/*     */   
/*     */   static {
/*  33 */     if (Trace.isOn) {
/*  34 */       Trace.data("com.ibm.mq.jmqi.local.internal.adapters.RRSAdapter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/RRSAdapter.java");
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
/*     */   public RRSAdapter(JmqiEnvironment env, LocalMQ mq) {
/*  49 */     super(env, mq);
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.RRSAdapter", "<init>(JmqiEnvironment,LocalMQ)", new Object[] { env, mq });
/*     */     }
/*     */     
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.RRSAdapter", "<init>(JmqiEnvironment,LocalMQ)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSharedHandlesSupported() {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.RRSAdapter", "isSharedHandlesSupported()", "getter", 
/*  68 */           Boolean.valueOf(false));
/*     */     }
/*  70 */     return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWorkerThreadSupported() {
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.RRSAdapter", "isWorkerThreadSupported()", "getter", 
/* 100 */           Boolean.valueOf(true));
/*     */     }
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRRS() {
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.RRSAdapter", "isRRS()", "getter", 
/* 112 */           Boolean.valueOf(true));
/*     */     }
/* 114 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\adapters\RRSAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */