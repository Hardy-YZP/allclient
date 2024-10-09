/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Vector;
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
/*     */ class PoolScavenger
/*     */   extends JmqiObject
/*     */   implements Runnable
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/PoolScavenger.java";
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.mq.PoolScavenger", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/PoolScavenger.java");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static int tid = 0;
/*     */   
/*     */   private long timeout;
/*     */   
/*     */   public PoolScavenger() {
/*  67 */     super(MQSESSION.getJmqiEnv());
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
/*  89 */     this.timeout = 300000L;
/*  90 */     this.highThreshold = 10;
/*     */     
/*  92 */     this.smcs = new Vector<>();
/*  93 */     this.times = new Vector<>();
/*     */     
/*  95 */     this.quit = false;
/*  96 */     this.nonempty = false;
/*  97 */     this.updateLock = new Object();
/*     */ 
/*     */     
/* 100 */     this.thread = null;
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.PoolScavenger", "<init>()"); 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.PoolScavenger", "<init>()"); 
/*     */   }
/*     */   
/*     */   private int highThreshold;
/*     */   
/*     */   public synchronized void run() {
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.entry(this, "com.ibm.mq.PoolScavenger", "run()");
/*     */     }
/* 113 */     String fid = "run()";
/*     */     
/* 115 */     long expire = 0L;
/* 116 */     StoredManagedConnection smc = null;
/* 117 */     long count = 0L;
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
/* 130 */     while (!this.quit) {
/* 131 */       long currtime = System.currentTimeMillis();
/* 132 */       boolean trigger = false;
/*     */       
/* 134 */       synchronized (this.updateLock) {
/* 135 */         if (!this.smcs.isEmpty()) {
/* 136 */           expire = ((Long)this.times.firstElement()).longValue() + this.timeout;
/* 137 */           smc = this.smcs.firstElement();
/* 138 */           count = this.smcs.size();
/* 139 */           this.nonempty = true;
/*     */         } else {
/* 141 */           this.nonempty = false;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 147 */         if (!this.quit) {
/* 148 */           if (this.nonempty) {
/* 149 */             if (expire <= currtime || count > this.highThreshold) {
/* 150 */               if (Trace.isOn) {
/* 151 */                 Trace.data(this, "run()", "SMC expired; about to call trigger()", "");
/*     */               }
/* 153 */               trigger = true;
/*     */             } else {
/*     */               try {
/* 156 */                 if (Trace.isOn) {
/* 157 */                   Trace.data(this, "run()", "Waiting for SMC to expire", "");
/*     */                 }
/* 159 */                 this.updateLock.wait(expire - currtime);
/*     */               }
/* 161 */               catch (InterruptedException e) {
/* 162 */                 if (Trace.isOn) {
/* 163 */                   Trace.catchBlock(this, "com.ibm.mq.PoolScavenger", "run()", e, 1);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } else {
/*     */             
/*     */             try {
/* 170 */               if (Trace.isOn) {
/* 171 */                 Trace.data(this, "run()", "No SMC in list; waiting", "");
/*     */               }
/* 173 */               this.updateLock.wait();
/*     */             }
/* 175 */             catch (InterruptedException e) {
/* 176 */               if (Trace.isOn) {
/* 177 */                 Trace.catchBlock(this, "com.ibm.mq.PoolScavenger", "run()", e, 2);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 185 */       if (trigger) {
/* 186 */         smc.trigger();
/*     */       }
/*     */     } 
/*     */     
/* 190 */     if (Trace.isOn)
/* 191 */       Trace.exit(this, "com.ibm.mq.PoolScavenger", "run()"); 
/*     */   }
/*     */   
/*     */   private Vector<StoredManagedConnection> smcs;
/*     */   private Vector<Long> times;
/*     */   private volatile boolean quit;
/*     */   
/*     */   void register(StoredManagedConnection smc) {
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.entry(this, "com.ibm.mq.PoolScavenger", "register(StoredManagedConnection)", new Object[] { smc });
/*     */     }
/*     */     
/* 203 */     String fid = "register(StoredManagedConnection)";
/* 204 */     synchronized (this.updateLock) {
/* 205 */       this.smcs.addElement(smc);
/* 206 */       this.times.addElement(Long.valueOf(System.currentTimeMillis()));
/*     */       
/* 208 */       if (Trace.isOn) {
/* 209 */         Trace.data(this, "register(StoredManagedConnection)", "Notifying scavenger thread", "");
/*     */       }
/*     */       
/* 212 */       this.updateLock.notify();
/*     */     } 
/*     */     
/* 215 */     if (Trace.isOn)
/* 216 */       Trace.exit(this, "com.ibm.mq.PoolScavenger", "register(StoredManagedConnection)"); 
/*     */   }
/*     */   
/*     */   private boolean nonempty;
/*     */   private Object updateLock;
/*     */   private Thread thread;
/*     */   
/*     */   void deregister(StoredManagedConnection smc) {
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.entry(this, "com.ibm.mq.PoolScavenger", "deregister(StoredManagedConnection)", new Object[] { smc });
/*     */     }
/*     */     
/* 228 */     String fid = "deregister(StoredManagedConnection)";
/*     */     
/* 230 */     synchronized (this.updateLock) {
/* 231 */       int p = this.smcs.indexOf(smc);
/* 232 */       if (p != -1) {
/* 233 */         this.smcs.removeElementAt(p);
/* 234 */         this.times.removeElementAt(p);
/* 235 */         if (Trace.isOn) {
/* 236 */           Trace.data(this, "deregister(StoredManagedConnection)", "Notifying scavenger thread", "");
/*     */         }
/* 238 */         this.updateLock.notify();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.exit(this, "com.ibm.mq.PoolScavenger", "deregister(StoredManagedConnection)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setTimeout(long timeout) {
/* 251 */     if (Trace.isOn) {
/* 252 */       Trace.data(this, "com.ibm.mq.PoolScavenger", "setTimeout(long)", "setter", 
/* 253 */           Long.valueOf(timeout));
/*     */     }
/* 255 */     String fid = "setTimeout(long)";
/* 256 */     this.timeout = timeout;
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.data(this, "setTimeout(long)", "Notifying scavenger thread", "");
/*     */     }
/* 260 */     synchronized (this.updateLock) {
/* 261 */       this.updateLock.notify();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   long getTimeout() {
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.data(this, "com.ibm.mq.PoolScavenger", "getTimeout()", "getter", Long.valueOf(this.timeout));
/*     */     }
/*     */     
/* 271 */     return this.timeout;
/*     */   }
/*     */ 
/*     */   
/*     */   void setMaxUnusedConnections(int threshold) {
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.data(this, "com.ibm.mq.PoolScavenger", "setMaxUnusedConnections(int)", "setter", 
/* 278 */           Integer.valueOf(threshold));
/*     */     }
/* 280 */     String fid = "setMaxUnusedConnections(int)";
/* 281 */     this.highThreshold = threshold;
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.data(this, "setMaxUnusedConnections(int)", "Notifying scavenger thread", "");
/*     */     }
/* 285 */     synchronized (this.updateLock) {
/* 286 */       this.updateLock.notify();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   int getMaxUnusedConnections() {
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.data(this, "com.ibm.mq.PoolScavenger", "getMaxUnusedConnections()", "getter", 
/* 294 */           Integer.valueOf(this.highThreshold));
/*     */     }
/* 296 */     return this.highThreshold;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void start() {
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.entry(this, "com.ibm.mq.PoolScavenger", "start()");
/*     */     }
/* 305 */     String fid = "start()";
/* 306 */     this.quit = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 312 */     if (this.thread == null) {
/* 313 */       if (Trace.isOn) {
/* 314 */         Trace.data(this, "start()", "Creating new Pool Scavenger thread", "");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 321 */       String tName = "PoolScavenger" + tid;
/* 322 */       tid++;
/* 323 */       this.thread = MQEnvironment.createThread(this, tName, true);
/* 324 */       this.thread.start();
/*     */     }
/* 326 */     else if (Trace.isOn) {
/* 327 */       Trace.data(this, "start()", "A thread has already been created for this Pool Scavenger", "");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 334 */     if (Trace.isOn) {
/* 335 */       Trace.exit(this, "com.ibm.mq.PoolScavenger", "start()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void quit() {
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.entry(this, "com.ibm.mq.PoolScavenger", "quit()");
/*     */     }
/* 346 */     String fid = "quit()";
/* 347 */     this.quit = true;
/* 348 */     if (Trace.isOn) {
/* 349 */       Trace.data(this, "quit()", "Notifying scavenger thread", "");
/*     */     }
/* 351 */     synchronized (this.updateLock) {
/* 352 */       this.updateLock.notify();
/*     */     } 
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.data(this, "quit()", "Waiting for scavenger thread to end", "");
/*     */     }
/*     */     try {
/* 358 */       if (this.thread != null) {
/* 359 */         this.thread.join();
/*     */ 
/*     */         
/* 362 */         if (Trace.isOn) {
/* 363 */           Trace.data(this, "quit()", "Setting thread to null", "");
/*     */         }
/* 365 */         this.thread = null;
/*     */       }
/*     */     
/* 368 */     } catch (InterruptedException e) {
/* 369 */       if (Trace.isOn) {
/* 370 */         Trace.catchBlock(this, "com.ibm.mq.PoolScavenger", "quit()", e);
/*     */       }
/*     */       
/* 373 */       if (Trace.isOn) {
/* 374 */         Trace.data(this, "quit()", "WARNING: Thread was interrupted", "");
/*     */       }
/*     */     } 
/*     */     
/* 378 */     if (Trace.isOn) {
/* 379 */       Trace.exit(this, "com.ibm.mq.PoolScavenger", "quit()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int inPool() {
/* 387 */     if (Trace.isOn) {
/* 388 */       Trace.entry(this, "com.ibm.mq.PoolScavenger", "inPool()");
/*     */     }
/* 390 */     int traceRet1 = this.smcs.size();
/*     */     
/* 392 */     if (Trace.isOn) {
/* 393 */       Trace.exit(this, "com.ibm.mq.PoolScavenger", "inPool()", Integer.valueOf(traceRet1));
/*     */     }
/* 395 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroyNext(int count) {
/* 403 */     if (Trace.isOn)
/* 404 */       Trace.entry(this, "com.ibm.mq.PoolScavenger", "destroyNext(int)", new Object[] {
/* 405 */             Integer.valueOf(count)
/*     */           }); 
/* 407 */     String fid = "destroyNext(int)";
/* 408 */     if (this.smcs.isEmpty()) {
/* 409 */       if (Trace.isOn) {
/* 410 */         Trace.exit(this, "com.ibm.mq.PoolScavenger", "destroyNext(int)", 1);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 415 */     if (Trace.isOn) {
/* 416 */       Trace.data(this, "destroyNext(int)", "destroying " + count + " inactive connections", "");
/*     */     }
/* 418 */     StoredManagedConnection smc = null;
/*     */ 
/*     */     
/* 421 */     for (int i = 0; i < count && 
/* 422 */       !this.smcs.isEmpty(); i++) {
/*     */ 
/*     */       
/* 425 */       smc = this.smcs.firstElement();
/* 426 */       smc.trigger();
/*     */     } 
/*     */     
/* 429 */     if (Trace.isOn)
/* 430 */       Trace.exit(this, "com.ibm.mq.PoolScavenger", "destroyNext(int)", 2); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\PoolScavenger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */