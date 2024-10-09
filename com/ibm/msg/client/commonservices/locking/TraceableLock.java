/*     */ package com.ibm.msg.client.commonservices.locking;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.locks.Condition;
/*     */ import java.util.concurrent.locks.Lock;
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
/*     */ public class TraceableLock
/*     */   implements Lock
/*     */ {
/*     */   private String className;
/*     */   private Lock delegate;
/*     */   
/*  41 */   private ThreadLocal<Integer> acquisitionDepth = new ThreadLocal<Integer>()
/*     */     {
/*     */       protected Integer initialValue()
/*     */       {
/*  45 */         return Integer.valueOf(0);
/*     */       }
/*     */     };
/*     */   
/*     */   public TraceableLock(Lock delegate) {
/*  50 */     this.className = String.format("%s(%s)", new Object[] { getClass().getCanonicalName(), delegate.getClass().getSimpleName() });
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.entry(this.className, "<init>", new Object[] { delegate });
/*     */     }
/*  54 */     this.delegate = delegate;
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.exit(this.className, "<init>");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void lock() {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.entry(this, this.className, "lock()");
/*  65 */       Trace.data(this, "lock()", "delegate", this.delegate.toString());
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/*  70 */       if (this.delegate.tryLock()) {
/*  71 */         Trace.data(this, "lock()", "acquired lock in trylock", null);
/*     */       } else {
/*     */         
/*  74 */         Trace.data(this, "lock()", "did not acquire lock in trylock - waiting for lock", this.delegate);
/*  75 */         this.delegate.lock();
/*     */       }
/*     */     
/*  78 */     } catch (RuntimeException rte) {
/*  79 */       Trace.ffst(this, "lock()", "LCK001", new HashMap<String, Object>() { private static final long serialVersionUID = 1348586698097444390L; }, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  87 */       throw rte;
/*     */     } 
/*     */     
/*  90 */     incrementDepth();
/*     */     
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.exit(this, this.className, "lock()");
/*     */     }
/*     */   }
/*     */   
/*     */   protected void incrementDepth() {
/*  98 */     Integer depth = this.acquisitionDepth.get();
/*  99 */     this.acquisitionDepth.set(Integer.valueOf(depth.intValue() + 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public void lockInterruptibly() throws InterruptedException {
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.entry(this, this.className, "lockInterruptibly()");
/* 106 */       Trace.data(this, "lockInterruptibly", "delegate", this.delegate.toString());
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 111 */       if (this.delegate.tryLock()) {
/* 112 */         Trace.data(this, "lockInterruptibly", "acquired lock in trylock", null);
/*     */       } else {
/*     */         
/* 115 */         Trace.data(this, "lockInterruptibly", "did not acquire lock in trylock - waiting for lock", this.delegate);
/* 116 */         this.delegate.lockInterruptibly();
/*     */       }
/*     */     
/* 119 */     } catch (RuntimeException rte) {
/* 120 */       Trace.ffst(this, "lockInterruptibly()", "LCK002", new HashMap<String, Object>() { private static final long serialVersionUID = 1348586698097444390L; }, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 128 */       throw rte;
/*     */     } 
/*     */     
/* 131 */     incrementDepth();
/*     */     
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.exit(this, this.className, "lockInterruptibly()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tryLock() {
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.entry(this, this.className, "tryLock()");
/* 142 */       Trace.data(this, "tryLock()", "delegate", this.delegate.toString());
/*     */     } 
/*     */     
/* 145 */     boolean result = false;
/*     */     
/*     */     try {
/* 148 */       result = this.delegate.tryLock();
/* 149 */       if (result) {
/* 150 */         incrementDepth();
/*     */       }
/*     */     }
/* 153 */     catch (RuntimeException rte) {
/* 154 */       Trace.ffst(this, "tryLock()", "LCK003", new HashMap<String, Object>() { private static final long serialVersionUID = 1348586698097444390L; }, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 162 */       throw rte;
/*     */     } 
/*     */     
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.exit(this, this.className, "tryLock()", Boolean.valueOf(result));
/*     */     }
/* 168 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.entry(this, this.className, "tryLock(long, TimeUnit)", new Object[] { Long.valueOf(time), unit });
/* 175 */       Trace.data(this, "tryLock(long, TimeUnit)", "delegate", this.delegate.toString());
/*     */     } 
/*     */     
/* 178 */     boolean result = false;
/*     */     
/*     */     try {
/* 181 */       result = this.delegate.tryLock(time, unit);
/* 182 */       if (result) {
/* 183 */         incrementDepth();
/*     */       }
/*     */     }
/* 186 */     catch (RuntimeException rte) {
/* 187 */       Trace.ffst(this, "tryLock(long, TimeUnit)", "LCK004", new HashMap<String, Object>() { private static final long serialVersionUID = 1348586698097444390L; }, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 195 */       throw rte;
/*     */     } 
/*     */     
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.exit(this, this.className, "tryLock(long, TimeUnit)", Boolean.valueOf(result));
/*     */     }
/* 201 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void unlock() {
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.entry(this, this.className, "unlock()");
/* 208 */       Trace.data(this, "unlock()", "delegate", this.delegate.toString());
/*     */     } 
/*     */     
/*     */     try {
/* 212 */       this.delegate.unlock();
/* 213 */       decrementDepth();
/*     */     }
/* 215 */     catch (RuntimeException rte) {
/* 216 */       Trace.ffst(this, "unlock()", "LCK005", new HashMap<String, Object>() { private static final long serialVersionUID = 1348586698097444390L; }, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 224 */       throw rte;
/*     */     } 
/*     */     
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.exit(this, this.className, "unlock()");
/*     */     }
/*     */   }
/*     */   
/*     */   public int fullyUnlock() {
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.entry(this, this.className, "fullyUnlock()");
/* 235 */       Trace.data(this, "fullyUnlock()", "delegate", this.delegate.toString());
/*     */     } 
/*     */     
/* 238 */     Integer currentDepth = this.acquisitionDepth.get();
/*     */     
/*     */     try {
/* 241 */       for (int i = 0; i < currentDepth.intValue(); i++) {
/* 242 */         this.delegate.unlock();
/*     */       }
/*     */     }
/* 245 */     catch (RuntimeException rte) {
/* 246 */       Trace.ffst(this, "fullyUnlock()", "LCK006", new HashMap<String, Object>() { private static final long serialVersionUID = 1348586698097444390L; }, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 254 */       throw rte;
/*     */     } 
/*     */     
/* 257 */     this.acquisitionDepth.set(Integer.valueOf(0));
/*     */     
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.exit(this, this.className, "fullyUnlock()", currentDepth);
/*     */     }
/* 262 */     return currentDepth.intValue();
/*     */   }
/*     */   
/*     */   public void relock(int desiredDepth) {
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.entry(this, this.className, "relock(int)", new Object[] { Integer.valueOf(desiredDepth) });
/* 268 */       Trace.data(this, "relock(int)", "delegate", this.delegate.toString());
/*     */     } 
/*     */     
/*     */     try {
/* 272 */       for (int i = 0; i < desiredDepth; i++) {
/* 273 */         this.delegate.lock();
/*     */       }
/*     */     }
/* 276 */     catch (RuntimeException rte) {
/* 277 */       Trace.ffst(this, "relock(int)", "LCK007", new HashMap<String, Object>() { private static final long serialVersionUID = 1348586698097444390L; }, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 285 */       throw rte;
/*     */     } 
/*     */     
/* 288 */     this.acquisitionDepth.set(Integer.valueOf(desiredDepth));
/*     */     
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.exit(this, this.className, "relock(int)");
/*     */     }
/*     */   }
/*     */   
/*     */   protected void decrementDepth() {
/* 296 */     Integer depth = this.acquisitionDepth.get();
/* 297 */     this.acquisitionDepth.set(Integer.valueOf(depth.intValue() - 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public Condition newCondition() {
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.entry(this, this.className, "newCondition()");
/* 304 */       Trace.data(this, "newCondition()", "delegate", this.delegate.toString());
/*     */     } 
/*     */     
/* 307 */     Condition result = null;
/*     */     
/*     */     try {
/* 310 */       result = this.delegate.newCondition();
/*     */     }
/* 312 */     catch (RuntimeException rte) {
/* 313 */       Trace.ffst(this, "newCondition()", "LCK008", new HashMap<String, Object>() { private static final long serialVersionUID = 1348586698097444390L; }, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 321 */       throw rte;
/*     */     } 
/*     */     
/* 324 */     if (Trace.isOn) {
/* 325 */       Trace.exit(this, this.className, "newCondition()", result);
/*     */     }
/* 327 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 332 */     return this.delegate.toString();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\locking\TraceableLock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */