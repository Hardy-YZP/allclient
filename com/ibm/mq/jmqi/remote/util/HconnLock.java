/*     */ package com.ibm.mq.jmqi.remote.util;
/*     */ 
/*     */ import com.ibm.mq.jmqi.remote.api.RemoteFAP;
/*     */ import com.ibm.msg.client.commonservices.locking.TraceableReentrantLock;
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
/*     */ public class HconnLock
/*     */   extends TraceableReentrantLock
/*     */ {
/*     */   private static final long serialVersionUID = -8841362121256622651L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/util/HconnLock.java";
/*     */   RemoteFAP fap;
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.jmqi.remote.util.HconnLock", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/util/HconnLock.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HconnLock(RemoteFAP fap) {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.HconnLock", "<init>(RemoteFAP)", new Object[] { fap });
/*     */     }
/*     */ 
/*     */     
/*  61 */     this.fap = fap;
/*     */     
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.HconnLock", "<init>(RemoteFAP)");
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean isExempt() {
/*  69 */     boolean result = (this.fap.getTls()).isReconnectThread;
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.data(this, "com.ibm.mq.jmqi.remote.util.HconnLock", "isExempt", Boolean.valueOf(result));
/*     */     }
/*  73 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void lock() {
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.HconnLock", "lock()");
/*     */     }
/*     */     
/*  82 */     if (!isExempt()) {
/*  83 */       super.lock();
/*     */     }
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.HconnLock", "lock()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tryLock() {
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.HconnLock", "tryLock()");
/*     */     }
/*     */     
/*  97 */     boolean result = isExempt() ? true : super.tryLock();
/*     */     
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.HconnLock", "tryLock()", Boolean.valueOf(result));
/*     */     }
/* 102 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unlock() {
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.HconnLock", "unlock()");
/*     */     }
/*     */     
/* 114 */     if (!isExempt()) {
/* 115 */       super.unlock();
/*     */     }
/*     */     
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.HconnLock", "unlock()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int fullyUnlock() {
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.HconnLock", "fullyUnlock()");
/*     */     }
/*     */     
/* 134 */     int heldCount = isExempt() ? 0 : super.fullyUnlock();
/*     */     
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.HconnLock", "fullyUnlock()", 
/* 138 */           Integer.valueOf(heldCount));
/*     */     }
/* 140 */     return heldCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void relock(int desiredDepth) {
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.HconnLock", "relock(int)", new Object[] { Integer.valueOf(desiredDepth) });
/*     */     }
/*     */     
/* 149 */     if (!isExempt()) {
/* 150 */       super.relock(desiredDepth);
/*     */     }
/*     */     
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.HconnLock", "relock(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLocked() {
/* 165 */     boolean traceRet = isExempt() ? false : super.isLocked();
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.data(this, "com.ibm.mq.jmqi.remote.util.HconnLock", "isLocked()", "getter", 
/* 168 */           Boolean.valueOf(traceRet));
/*     */     }
/* 170 */     return traceRet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHeldByCurrentThread() {
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.HconnLock", "isHeldByCurrentThread()");
/*     */     }
/* 183 */     boolean result = (super.isHeldByCurrentThread() || isExempt());
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.HconnLock", "isHeldByCurrentThread()", Boolean.valueOf(result));
/*     */     }
/* 187 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remot\\util\HconnLock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */