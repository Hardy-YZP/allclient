/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.internal.Configuration;
/*     */ import com.ibm.mq.jmqi.system.JmqiRunnable;
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
/*     */ public class JmqiWorkerThread
/*     */   extends JmqiObject
/*     */   implements Runnable
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiWorkerThread.java";
/*     */   private JmqiRunnable job;
/*     */   boolean isRunning;
/*     */   private boolean quit;
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.jmqi.JmqiWorkerThread", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiWorkerThread.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private Object enqueueLock = new Object();
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasAborted = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiWorkerThread(JmqiEnvironment env) {
/*  73 */     super(env);
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiWorkerThread", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiWorkerThread", "<init>(JmqiEnvironment)");
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
/*     */   public void syncExec(JmqiRunnable aJob) throws JmqiException {
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiWorkerThread", "syncExec(final JmqiRunnable)", new Object[] { aJob });
/*     */     }
/*     */     
/*     */     try {
/* 117 */       synchronized (this.enqueueLock) {
/* 118 */         synchronized (this) {
/* 119 */           if (this.quit) {
/*     */             
/* 121 */             JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2018, null);
/*     */ 
/*     */             
/* 124 */             if (Trace.isOn) {
/* 125 */               Trace.throwing(this, "com.ibm.mq.jmqi.JmqiWorkerThread", "syncExec(final JmqiRunnable)", traceRet1, 1);
/*     */             }
/*     */             
/* 128 */             throw traceRet1;
/*     */           } 
/*     */           
/* 131 */           long waitTime = this.env.getConfiguration().getIntValue(Configuration.threadWaitTimeoutProperty);
/*     */ 
/*     */           
/* 134 */           if (Trace.isOn) {
/* 135 */             Trace.data(this, "syncExec(final JmqiRunnable)", "Thread wait time out set at " + waitTime + " (isRunning: " + this.isRunning + ")");
/*     */           }
/*     */           
/* 138 */           long startTime = System.currentTimeMillis();
/*     */           
/* 140 */           while (!this.isRunning && (waitTime == 0L || 
/* 141 */             System.currentTimeMillis() - startTime < waitTime)) {
/*     */             try {
/* 143 */               if (Trace.isOn) {
/* 144 */                 Trace.data(this, "syncExec(final JmqiRunnable)", "Starting wait for thread start");
/*     */               }
/* 146 */               wait(waitTime);
/* 147 */               if (Trace.isOn) {
/* 148 */                 Trace.data(this, "syncExec(final JmqiRunnable)", "Finished wait for thread start (isRunning: " + this.isRunning + ")");
/*     */               
/*     */               }
/*     */             }
/* 152 */             catch (InterruptedException e) {
/* 153 */               if (Trace.isOn) {
/* 154 */                 Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiWorkerThread", "syncExec(final JmqiRunnable)", e, 1);
/*     */               }
/*     */             } 
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 161 */           if (!this.isRunning) {
/* 162 */             JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2102, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 168 */             this.hasAborted = true;
/* 169 */             if (Trace.isOn) {
/* 170 */               Trace.throwing(this, "com.ibm.mq.jmqi.JmqiWorkerThread", "syncExec(final JmqiRunnable)", traceRet1, 2);
/*     */             }
/*     */             
/* 173 */             throw traceRet1;
/*     */           } 
/*     */           
/* 176 */           this.job = aJob;
/* 177 */           notify();
/*     */           
/*     */           try {
/* 180 */             wait();
/*     */           }
/* 182 */           catch (InterruptedException e) {
/* 183 */             if (Trace.isOn) {
/* 184 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiWorkerThread", "syncExec(final JmqiRunnable)", e, 2);
/*     */             }
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/*     */     } finally {
/*     */       
/* 192 */       if (Trace.isOn) {
/* 193 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.JmqiWorkerThread", "syncExec(final JmqiRunnable)");
/*     */       }
/*     */     } 
/*     */     
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiWorkerThread", "syncExec(final JmqiRunnable)");
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
/*     */   public synchronized void run() {
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiWorkerThread", "run()");
/*     */     }
/* 219 */     Thread.currentThread().setName("JmqiWorkerThread");
/* 220 */     this.isRunning = true;
/* 221 */     notify();
/* 222 */     this.quit = false;
/* 223 */     while (!this.quit) {
/*     */       try {
/* 225 */         if (Trace.isOn) {
/* 226 */           Trace.data(this, "run()", "run() method starting wait for job notification (hasAborted: " + this.hasAborted + ")");
/*     */         }
/*     */         
/* 229 */         if (this.hasAborted) {
/* 230 */           if (Trace.isOn) {
/* 231 */             Trace.data(this, "run()", "Quitting run() method early as thread timeout exceeded previously");
/*     */           }
/*     */ 
/*     */           
/* 235 */           if (Trace.isOn) {
/* 236 */             Trace.exit(this, "com.ibm.mq.jmqi.JmqiWorkerThread", "run()", 1);
/*     */           }
/*     */           return;
/*     */         } 
/* 240 */         wait();
/*     */         try {
/* 242 */           this.job.jmqiRunnableException = null;
/* 243 */           if (Trace.isOn) {
/* 244 */             Trace.data(this, "run()", "run() method about to run job");
/*     */           }
/* 246 */           this.job.run();
/*     */         }
/* 248 */         catch (Throwable jobException) {
/* 249 */           if (Trace.isOn) {
/* 250 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiWorkerThread", "run()", jobException, 1);
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 256 */           this.job.jmqiRunnableException = jobException;
/*     */         } 
/* 258 */         notify();
/*     */       }
/* 260 */       catch (InterruptedException e) {
/* 261 */         if (Trace.isOn) {
/* 262 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiWorkerThread", "run()", e, 2);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiWorkerThread", "run()", 2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws JmqiException {
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiWorkerThread", "close()");
/*     */     }
/* 282 */     syncExec(new JmqiRunnable(this.env)
/*     */         {
/*     */           public void run()
/*     */           {
/* 286 */             if (Trace.isOn) {
/* 287 */               Trace.entry(this, "com.ibm.mq.jmqi.JmqiWorkerThread", "run()");
/*     */             }
/* 289 */             JmqiWorkerThread.this.quit = true;
/*     */             
/* 291 */             if (Trace.isOn) {
/* 292 */               Trace.exit(this, "com.ibm.mq.jmqi.null", "run()");
/*     */             }
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 298 */     if (Trace.isOn)
/* 299 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiWorkerThread", "close()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\JmqiWorkerThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */