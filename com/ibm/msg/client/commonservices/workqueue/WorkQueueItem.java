/*     */ package com.ibm.msg.client.commonservices.workqueue;
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
/*     */ public abstract class WorkQueueItem
/*     */   implements Runnable
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/workqueue/WorkQueueItem.java";
/*     */   public static final int STATE_WAITING = 0;
/*     */   public static final int STATE_PAUSED = 1;
/*     */   public static final int STATE_RUNNING = 2;
/*     */   public static final int STATE_ENDING = 3;
/*     */   public static final int STATE_COMPLETE = 4;
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/workqueue/WorkQueueItem.java");
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
/*     */   public enum STATE
/*     */   {
/*  62 */     STATE_WAITING("waiting", 0),
/*     */ 
/*     */     
/*  65 */     STATE_PAUSED("paused", 1),
/*     */ 
/*     */     
/*  68 */     STATE_RUNNING("running", 2),
/*     */ 
/*     */     
/*  71 */     STATE_ENDING("ending", 3),
/*     */ 
/*     */     
/*  74 */     STATE_COMPLETE("Complete", 4);
/*     */     
/*     */     private String stateString;
/*     */     
/*     */     private int stateInt;
/*     */     
/*     */     STATE(String stateString, int stateInt) {
/*  81 */       if (Trace.isOn) {
/*  82 */         Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.STATE", "<init>(String,int)", new Object[] { stateString, 
/*  83 */               Integer.valueOf(stateInt) });
/*     */       }
/*  85 */       this.stateString = stateString;
/*  86 */       this.stateInt = stateInt;
/*  87 */       if (Trace.isOn) {
/*  88 */         Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.STATE", "<init>(String,int)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static STATE getStateByInt(int i) {
/*  99 */       if (Trace.isOn)
/* 100 */         Trace.entry("com.ibm.msg.client.commonservices.workqueue.STATE", "getStateByInt(int)", new Object[] {
/* 101 */               Integer.valueOf(i)
/*     */             }); 
/* 103 */       for (STATE candidate : values()) {
/* 104 */         if (candidate.stateInt == i) {
/* 105 */           if (Trace.isOn) {
/* 106 */             Trace.exit("com.ibm.msg.client.commonservices.workqueue.STATE", "getStateByInt(int)", candidate, 1);
/*     */           }
/*     */           
/* 109 */           return candidate;
/*     */         } 
/*     */       } 
/* 112 */       if (Trace.isOn) {
/* 113 */         Trace.exit("com.ibm.msg.client.commonservices.workqueue.STATE", "getStateByInt(int)", null, 2);
/*     */       }
/*     */       
/* 116 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getIntValue() {
/* 123 */       if (Trace.isOn) {
/* 124 */         Trace.data(this, "com.ibm.msg.client.commonservices.workqueue.STATE", "getIntValue()", "getter", 
/* 125 */             Integer.valueOf(this.stateInt));
/*     */       }
/* 127 */       return this.stateInt;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 135 */       if (Trace.isOn) {
/* 136 */         Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.STATE", "toString()");
/*     */       }
/* 138 */       if (Trace.isOn) {
/* 139 */         Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.STATE", "toString()", this.stateString);
/*     */       }
/*     */       
/* 142 */       return this.stateString;
/*     */     }
/*     */   }
/*     */   
/* 146 */   private int state = 0;
/*     */   
/*     */   private Runnable task;
/*     */   private boolean repeats = false;
/* 150 */   private Object completedNotifier = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 157 */   private Throwable lastThrown = null;
/*     */   
/*     */   protected WorkQueueItem(Runnable newTask) {
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "<init>(Runnable)", new Object[] { newTask });
/*     */     }
/*     */     
/* 164 */     this.state = 0;
/*     */     
/* 166 */     this.task = newTask;
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "<init>(Runnable)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized int getState() {
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.data(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "getState()", "getter", 
/* 181 */           Integer.valueOf(this.state));
/*     */     }
/* 183 */     return this.state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized String getStateString() {
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.data(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "getState()", "getter", 
/* 193 */           STATE.getStateByInt(this.state).toString());
/*     */     }
/*     */     
/* 196 */     String traceRet1 = STATE.getStateByInt(this.state).toString();
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.data(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "getStateString()", "getter", traceRet1);
/*     */     }
/*     */     
/* 201 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected synchronized void setState(int newState) {
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.data(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "setState(int)", "setter", 
/* 210 */           Integer.valueOf(newState));
/*     */     }
/* 212 */     this.state = newState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRepeats(boolean repeats) {
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.data(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "setRepeats(boolean)", "setter", 
/* 223 */           Boolean.valueOf(repeats));
/*     */     }
/* 225 */     this.repeats = repeats;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean repeats() {
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "repeats()");
/*     */     }
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "repeats()", 
/* 239 */           Boolean.valueOf(this.repeats));
/*     */     }
/* 241 */     return this.repeats;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setTask(Runnable newTask) {
/* 251 */     if (Trace.isOn) {
/* 252 */       Trace.data(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "setTask(Runnable)", "setter", newTask);
/*     */     }
/*     */     
/* 255 */     if (this.task == null) {
/* 256 */       this.task = newTask;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pause() {
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "pause()");
/*     */     }
/* 268 */     synchronized (this) {
/* 269 */       this.state = 1;
/* 270 */       notifyAll();
/*     */     } 
/* 272 */     if (Trace.isOn) {
/* 273 */       Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "pause()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resume() {
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "resume()");
/*     */     }
/* 286 */     synchronized (this) {
/* 287 */       this.state = 0;
/* 288 */       notifyAll();
/*     */     } 
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "resume()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void end() {
/* 301 */     if (Trace.isOn) {
/* 302 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "end()");
/*     */     }
/* 304 */     synchronized (this) {
/* 305 */       this.state = 3;
/* 306 */       notifyAll();
/*     */     } 
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "end()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void runTask() {
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "runTask()");
/*     */     }
/* 319 */     this.task.run();
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "runTask()");
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
/*     */   public void run() {
/* 337 */     if (Trace.isOn) {
/* 338 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "run()");
/* 339 */       Trace.data(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "Current thread name:" + 
/* 340 */           Thread.currentThread().getName());
/*     */     } 
/*     */     try {
/* 343 */       runItem();
/*     */     }
/* 345 */     catch (Throwable t) {
/* 346 */       if (Trace.isOn) {
/* 347 */         Trace.catchBlock(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "run()", t);
/*     */       }
/*     */       
/* 350 */       this.lastThrown = t;
/*     */ 
/*     */ 
/*     */       
/* 354 */       end();
/*     */     } 
/*     */ 
/*     */     
/* 358 */     synchronized (this.completedNotifier) {
/* 359 */       setState(4);
/* 360 */       this.completedNotifier.notifyAll();
/*     */     } 
/*     */     
/* 363 */     if (Trace.isOn) {
/* 364 */       Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "run()");
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
/*     */   public Throwable getLastThrown() {
/* 378 */     if (Trace.isOn) {
/* 379 */       Trace.data(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "getLastThrown()", "getter", this.lastThrown);
/*     */     }
/*     */     
/* 382 */     return this.lastThrown;
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
/*     */   protected void setLastThrown(Throwable t) {
/* 394 */     if (Trace.isOn) {
/* 395 */       Trace.data(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "setLastThrown(Throwable)", "setter", t);
/*     */     }
/*     */     
/* 398 */     this.lastThrown = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void waitForCompletion() {
/* 405 */     if (Trace.isOn) {
/* 406 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "waitForCompletion()");
/*     */     }
/*     */     
/* 409 */     synchronized (this.completedNotifier) {
/*     */       while (true) {
/* 411 */         if (getState() == 4) {
/*     */           
/* 413 */           if (Trace.isOn) {
/* 414 */             Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "waitForCompletion()");
/*     */           }
/*     */           
/*     */           return;
/*     */         } 
/*     */         try {
/* 420 */           this.completedNotifier.wait();
/*     */         }
/* 422 */         catch (InterruptedException e) {
/* 423 */           if (Trace.isOn) {
/* 424 */             Trace.catchBlock(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "waitForCompletion()", e);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void waitForCompletion(long timeout) {
/* 436 */     if (Trace.isOn)
/* 437 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "waitForCompletion(long)", new Object[] {
/* 438 */             Long.valueOf(timeout)
/*     */           }); 
/* 440 */     synchronized (this.completedNotifier) {
/* 441 */       if (getState() == 4) {
/* 442 */         if (Trace.isOn) {
/* 443 */           Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "waitForCompletion(long)", 1);
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */       try {
/* 449 */         this.completedNotifier.wait(timeout);
/*     */       }
/* 451 */       catch (InterruptedException e) {
/* 452 */         if (Trace.isOn) {
/* 453 */           Trace.catchBlock(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "waitForCompletion(long)", e);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 459 */     if (Trace.isOn)
/* 460 */       Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueItem", "waitForCompletion(long)", 2); 
/*     */   }
/*     */   
/*     */   public abstract void runItem();
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\workqueue\WorkQueueItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */