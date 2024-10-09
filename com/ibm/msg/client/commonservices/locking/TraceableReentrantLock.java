/*     */ package com.ibm.msg.client.commonservices.locking;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.locks.Condition;
/*     */ import java.util.concurrent.locks.ReentrantLock;
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
/*     */ 
/*     */ 
/*     */ public class TraceableReentrantLock
/*     */   extends ReentrantLock
/*     */ {
/*     */   private static final long serialVersionUID = 7721873438427038124L;
/*     */   private String className;
/*     */   private TraceableLock delegate;
/*     */   private ReentrantLock theLock;
/*     */   
/*     */   public TraceableReentrantLock() {
/*  50 */     this(new ReentrantLock(true));
/*     */   }
/*     */   
/*     */   public TraceableReentrantLock(ReentrantLock theLock) {
/*  54 */     this.className = String.format("%s(%s)", new Object[] { getClass().getCanonicalName(), theLock.getClass().getSimpleName() });
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.entry(this.className, "<init>", new Object[] { theLock });
/*     */     }
/*  58 */     this.delegate = new TraceableLock(theLock);
/*  59 */     this.theLock = theLock;
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.exit(this.className, "<init>");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void lock() {
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.entry(this, this.className, "lock()");
/*  70 */       Trace.data(this, "lock()", "delegate", this.delegate.toString());
/*     */     } 
/*     */     
/*  73 */     this.delegate.lock();
/*     */     
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.exit(this, this.className, "lock()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void lockInterruptibly() throws InterruptedException {
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.entry(this, this.className, "lockInterruptibly()");
/*  84 */       Trace.data(this, "lockInterruptibly", "delegate", this.delegate.toString());
/*     */     } 
/*     */     
/*  87 */     this.delegate.lockInterruptibly();
/*     */     
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.exit(this, this.className, "lockInterruptibly()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tryLock() {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, this.className, "tryLock()");
/*  98 */       Trace.data(this, "tryLock()", "delegate", this.delegate.toString());
/*     */     } 
/* 100 */     boolean result = this.delegate.tryLock();
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.exit(this, this.className, "tryLock()", Boolean.valueOf(result));
/*     */     }
/* 104 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.entry(this, this.className, "tryLock(long, TimeUnit)", new Object[] { Long.valueOf(time), unit });
/* 111 */       Trace.data(this, "tryLock(long, TimeUnit)", "delegate", this.delegate.toString());
/*     */     } 
/* 113 */     boolean result = this.delegate.tryLock(time, unit);
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.exit(this, this.className, "tryLock()", Boolean.valueOf(result));
/*     */     }
/* 117 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void unlock() {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry(this, this.className, "unlock()");
/* 124 */       Trace.data(this, "unlock()", "delegate", this.delegate.toString());
/*     */     } 
/* 126 */     this.delegate.unlock();
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit(this, this.className, "unlock()");
/*     */     }
/*     */   }
/*     */   
/*     */   public int fullyUnlock() {
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.entry(this, this.className, "fullyUnlock()");
/* 135 */       Trace.data(this, "unlock()", "delegate", this.delegate.toString());
/*     */     } 
/*     */     
/* 138 */     int currentDepth = this.delegate.fullyUnlock();
/*     */     
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.exit(this, this.className, "fullyUnlock()", currentDepth);
/*     */     }
/* 143 */     return currentDepth;
/*     */   }
/*     */   
/*     */   public void relock(int desiredDepth) {
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.entry(this, this.className, "relock(int)", new Object[] { Integer.valueOf(desiredDepth) });
/* 149 */       Trace.data(this, "relock(int)", "delegate", this.delegate.toString());
/*     */     } 
/*     */     
/* 152 */     this.delegate.relock(desiredDepth);
/*     */     
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.exit(this, this.className, "relock(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Condition newCondition() {
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.entry(this, this.className, "newCondition()");
/* 163 */       Trace.data(this, "newCondition()", "delegate", this.delegate.toString());
/*     */     } 
/* 165 */     Condition result = this.delegate.newCondition();
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.exit(this, this.className, "newCondition()", result);
/*     */     }
/* 169 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHeldByCurrentThread() {
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.entry(this, this.className, "isHeldByCurrentThread()");
/* 176 */       Trace.data(this, "isHeldByCurrentThread()", "theLock", this.theLock.toString());
/*     */     } 
/* 178 */     boolean result = this.theLock.isHeldByCurrentThread();
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.exit(this, this.className, "isHeldByCurrentThread()", Boolean.valueOf(result));
/*     */     }
/* 182 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLocked() {
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.entry(this, this.className, "isLocked()");
/* 190 */       Trace.data(this, "isHeldByCurrentThread()", "theLock", this.theLock.toString());
/*     */     } 
/* 192 */     boolean result = this.theLock.isLocked();
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.exit(this, this.className, "isLocked()", Boolean.valueOf(result));
/*     */     }
/* 196 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 202 */     return this.delegate.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(String lockName, PrintWriter pw, int level) {
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.entry(this, this.className, "dump(String,PrintWriter,int)", new Object[] { lockName, pw, 
/* 213 */             Integer.valueOf(level) });
/*     */     }
/* 215 */     String prefix = Trace.buildPrefix(level);
/* 216 */     pw.format("%s%s - %s%n", new Object[] { prefix, lockName, toString() });
/* 217 */     if (this.theLock.hasQueuedThreads()) {
/* 218 */       pw.format("%s  has queued threads:%n", new Object[] { prefix });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 223 */     if (Trace.isOn)
/* 224 */       Trace.exit(this, this.className, "dump(String,PrintWriter,int)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\locking\TraceableReentrantLock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */