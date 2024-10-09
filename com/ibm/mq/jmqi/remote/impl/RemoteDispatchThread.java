/*     */ package com.ibm.mq.jmqi.remote.impl;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.mq.jmqi.remote.api.RemoteFAP;
/*     */ import com.ibm.mq.jmqi.remote.api.RemoteHconn;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemoteDispatchThread
/*     */   extends JmqiObject
/*     */   implements Runnable
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteDispatchThread.java";
/*     */   private RemoteFAP fap;
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteDispatchThread.java");
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
/*  59 */   private RemoteHconn hconn = null;
/*     */   private volatile boolean hconnActive;
/*     */   private boolean threadSleeping = false;
/*     */   private int dispatchSeq;
/*  63 */   private int savedDispatchSeq = 0;
/*     */   
/*  65 */   private final Object sleepingEventSync = new SleepingEventSync();
/*     */   
/*     */   private static final int dispatchThreadSleepTimeout = 2000;
/*     */   private boolean sleepingEventPosted = false;
/*     */   private String threadName;
/*     */   private int noMsgsWait;
/*  71 */   private long now = 0L;
/*     */   private boolean waitQ = false;
/*  73 */   private Pint processed = new Pint(this.env);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void postSleepingEvent() {
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "postSleepingEvent()");
/*     */     }
/*  82 */     synchronized (this.sleepingEventSync) {
/*  83 */       this.sleepingEventPosted = true;
/*  84 */       this.sleepingEventSync.notify();
/*     */     } 
/*     */     
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "postSleepingEvent()");
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
/*     */   private boolean waitOnSleepingEvent(int timeout) {
/* 102 */     if (Trace.isOn)
/* 103 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "waitOnSleepingEvent(int)", new Object[] {
/* 104 */             Integer.valueOf(timeout)
/*     */           }); 
/* 106 */     boolean wasEventPosted = false;
/* 107 */     synchronized (this.sleepingEventSync) {
/*     */       
/* 109 */       if (this.sleepingEventPosted) {
/* 110 */         wasEventPosted = true;
/* 111 */         this.sleepingEventPosted = false;
/*     */       } else {
/*     */         
/* 114 */         boolean waitComplete = false;
/* 115 */         while (!waitComplete) {
/*     */           
/*     */           try {
/* 118 */             if (timeout >= 0) {
/* 119 */               this.sleepingEventSync.wait(timeout);
/*     */             } else {
/*     */               
/* 122 */               this.sleepingEventSync.wait();
/*     */             } 
/*     */             
/* 125 */             waitComplete = true;
/*     */             
/* 127 */             if (this.sleepingEventPosted) {
/* 128 */               wasEventPosted = true;
/* 129 */               this.sleepingEventPosted = false;
/*     */             }
/*     */           
/* 132 */           } catch (InterruptedException e) {
/* 133 */             if (Trace.isOn) {
/* 134 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "waitOnSleepingEvent(int)", e);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "waitOnSleepingEvent(int)", 
/* 144 */           Boolean.valueOf(wasEventPosted));
/*     */     }
/* 146 */     return wasEventPosted;
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
/*     */   public RemoteDispatchThread(JmqiEnvironment jmqiEnv, RemoteFAP fap, RemoteHconn hconn) {
/* 163 */     super(jmqiEnv);
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "<init>(JmqiEnvironment,RemoteFAP,RemoteHconn)", new Object[] { jmqiEnv, fap, hconn });
/*     */     }
/*     */     
/* 168 */     this.fap = fap;
/* 169 */     this.hconn = hconn;
/* 170 */     this.hconnActive = true;
/*     */     
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "<init>(JmqiEnvironment,RemoteFAP,RemoteHconn)");
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
/*     */   public void run() {
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "run()");
/*     */     }
/* 188 */     this.threadName = "DispatchThread: [" + this.hconn + "]";
/*     */     try {
/* 190 */       Thread.currentThread().setName(this.threadName);
/*     */     }
/* 192 */     catch (Exception e) {
/* 193 */       if (Trace.isOn) {
/* 194 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "run()", e, 1);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 200 */     RemoteTls tls = this.fap.getTls();
/* 201 */     tls.dispatchHconn = this.hconn;
/* 202 */     tls.isDispatchThread = true;
/*     */     
/* 204 */     int totalProcessed = 0;
/*     */ 
/*     */     
/*     */     try {
/* 208 */       this.hconn.requestThreadLock();
/* 209 */       this.hconn.releaseThreadLock();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 215 */       while (this.hconnActive) {
/* 216 */         totalProcessed = 0;
/* 217 */         this.savedDispatchSeq = this.dispatchSeq;
/* 218 */         this.noMsgsWait = 0;
/*     */         
/* 220 */         this.hconn.requestDispatchLock();
/*     */ 
/*     */         
/* 223 */         try { processHconn(tls);
/*     */           
/* 225 */           if (!this.hconnActive)
/*     */           
/*     */           { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 340 */             if (Trace.isOn) {
/* 341 */               Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "run()", 1);
/*     */             }
/* 343 */             if (this.hconn.haveDispatchLock())
/* 344 */               this.hconn.releaseDispatchLock();  break; }  if (this.hconn.isStarted() && !this.hconn.isSuspended() && !this.hconn.isSuspending() && this.hconn.onMessagePermitted()) try { this.processed.x = 0; for (RemoteProxyQueue pq : this.hconn.getDispatchQueueList()) { if (Trace.isOn) Trace.data(this, "run()", "main loop processing proxy queue", pq);  if (pq.isLogicallyRemoved()) { if (Trace.isOn) Trace.data(this, "run()", "skipping logically removed proxy queue", pq);  continue; }  this.waitQ = (pq.getMqcbGmo().getWaitInterval() > 0 && (pq.getMqcbGmo().getOptions() & 0x1) != 0); if (this.waitQ) this.now = System.currentTimeMillis();  if (pq.getMqcbCBD().getCallbackFunction() != null && (pq.getStatus() & 0x40000) == 0) { int dispatchLockDepth = this.hconn.fullyReleaseDispatchLock(); try { if (!pq.getEventsRaised().equals(this.hconn.getEventsHad())) pq.driveEventsMC();  if (this.hconn.isQuiescing() && (pq.getMqcbCBD().getOptions() & 0x2000) != 0) pq.callConsumer(5, 2, 2202);  if (!pq.isEmpty()) { deliverMsgs(pq); } else if (shouldDriveNoMsgs(pq) || pq.callbackOnEmpty()) { pq.callConsumer(5, 2, 2033); pq.unsetCallbackOnEmpty(); }  if (this.hconn.consumersChanged()) this.hconn.driveOutstanding();  } finally { this.hconn.reacquireDispatchLock(dispatchLockDepth); }  }  totalProcessed += this.processed.x; }  } finally { this.hconn.leaveOnMessage(); }   } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "run()", 1);  if (this.hconn.haveDispatchLock()) this.hconn.releaseDispatchLock();
/*     */            }
/*     */ 
/*     */         
/* 348 */         Thread.yield();
/*     */ 
/*     */         
/* 351 */         if (totalProcessed != 0) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 357 */         if (this.savedDispatchSeq != this.dispatchSeq) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/* 362 */         this.hconn.requestThreadLock();
/*     */ 
/*     */         
/* 365 */         try { if (this.savedDispatchSeq != this.dispatchSeq)
/*     */           
/*     */           { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 373 */             if (Trace.isOn) {
/* 374 */               Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "run()", 2);
/*     */             }
/*     */             
/* 377 */             this.hconn.releaseThreadLock(); continue; }  this.threadSleeping = true; } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "run()", 2);  this.hconn.releaseThreadLock(); }
/*     */         
/* 379 */         sleepPhase();
/*     */       }
/*     */     
/* 382 */     } catch (Throwable e) {
/* 383 */       if (Trace.isOn) {
/* 384 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "run()", e, 2);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 390 */       if (e instanceof JmqiException) {
/* 391 */         JmqiException jmqiE = (JmqiException)e;
/* 392 */         if (jmqiE.getReason() == 2195) {
/* 393 */           HashMap<String, Object> info = new HashMap<>();
/*     */           
/* 395 */           info.put("Exception Summary", JmqiTools.getExSumm(e));
/* 396 */           info.put("Exception message", (e.getCause() != null) ? e
/* 397 */               .getCause().getMessage() : null);
/* 398 */           info.put("Exception", e);
/* 399 */           info.put("Description", "Unexpected error");
/* 400 */           Trace.ffst(this, "run()", "01", info, null);
/*     */         } 
/*     */       } else {
/*     */         
/* 404 */         if (e instanceof Error) {
/* 405 */           this.hconn.releaseThreadLock();
/*     */           try {
/* 407 */             this.hconn.getParentConnection().asyncConnectionBroken(tls, e, null, null);
/*     */           }
/* 409 */           catch (JmqiException e1) {
/* 410 */             if (Trace.isOn) {
/* 411 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "run()", (Throwable)e1, 3);
/*     */             }
/*     */           } 
/*     */           
/* 415 */           HashMap<String, Object> hashMap = new HashMap<>();
/*     */           
/* 417 */           hashMap.put("Exception Summary", JmqiTools.getExSumm(e));
/* 418 */           hashMap.put("Exception message", (e.getCause() != null) ? e
/* 419 */               .getCause().getMessage() : null);
/* 420 */           hashMap.put("Description", "Unexpected error");
/* 421 */           Trace.ffst(this, "run()", "00", hashMap, null);
/*     */         } 
/*     */         
/* 424 */         HashMap<String, Object> info = new HashMap<>();
/*     */         
/* 426 */         info.put("Exception Summary", JmqiTools.getExSumm(e));
/* 427 */         info.put("Exception message", (e.getCause() != null) ? e
/* 428 */             .getCause().getMessage() : null);
/* 429 */         info.put("Exception", e);
/* 430 */         info.put("Description", "Unexpected error");
/* 431 */         Trace.ffst(this, "run()", "01", info, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 437 */         driveEventsMCForAllProxyQueues();
/*     */         
/*     */         try {
/* 440 */           if (this.hconn.hasEventsOutstanding()) {
/* 441 */             this.hconn.driveEventsEH();
/*     */           }
/*     */         }
/* 444 */         catch (JmqiException e2) {
/* 445 */           if (Trace.isOn) {
/* 446 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "run()", (Throwable)e2, 4);
/*     */           }
/* 448 */           if (Trace.isOn) {
/* 449 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "run()", (Throwable)e2, 4);
/*     */           }
/*     */         }
/*     */       
/*     */       } 
/*     */     } finally {
/*     */       
/* 456 */       if (Trace.isOn) {
/* 457 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "run()", 3);
/*     */       }
/* 459 */       tls.isDispatchThread = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 464 */     if (Trace.isOn) {
/* 465 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "run()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean shouldDriveNoMsgs(RemoteProxyQueue pq) {
/* 471 */     if (Trace.isOn) {
/* 472 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "shouldDriveNoMsgs(RemoteProxyQueue)", new Object[] { pq });
/*     */     }
/*     */     
/* 475 */     boolean shouldDrive = false;
/*     */     
/* 477 */     if (this.waitQ) {
/*     */       
/* 479 */       long noMsgTime = pq.getNoMsgTime();
/* 480 */       int waitInterval = pq.getMqcbGmo().getWaitInterval();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 486 */       if (noMsgTime == 0L) {
/* 487 */         pq.setNoMsgTime(this.now + waitInterval);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 494 */       long diff = noMsgTime - this.now;
/*     */       
/* 496 */       if (diff <= 0L) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 501 */         shouldDrive = true;
/*     */         
/* 503 */         pq.setNoMsgTime(this.now + waitInterval);
/* 504 */         if (this.noMsgsWait > waitInterval || this.noMsgsWait == 0) {
/* 505 */           this.noMsgsWait = waitInterval;
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/* 513 */       else if (this.noMsgsWait > diff || this.noMsgsWait == 0) {
/* 514 */         this.noMsgsWait = (int)diff;
/*     */       } 
/*     */     } 
/*     */     
/* 518 */     if (Trace.isOn) {
/* 519 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "shouldDriveNoMsgs(RemoteProxyQueue)", 
/* 520 */           Boolean.valueOf(shouldDrive));
/*     */     }
/* 522 */     return shouldDrive;
/*     */   }
/*     */   
/*     */   private void deliverMsgs(RemoteProxyQueue pq) throws JmqiException {
/* 526 */     if (Trace.isOn) {
/* 527 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "deliverMsgs(RemoteProxyQueue)", new Object[] { pq });
/*     */     }
/*     */ 
/*     */     
/* 531 */     deliverMsgsReconnectable(pq);
/*     */ 
/*     */ 
/*     */     
/* 535 */     if (this.waitQ) {
/* 536 */       pq.setNoMsgTime(this.now + pq.getMqcbGmo().getWaitInterval());
/*     */       
/* 538 */       if (this.noMsgsWait > pq.getMqcbGmo().getWaitInterval() || this.noMsgsWait == 0) {
/* 539 */         this.noMsgsWait = pq.getMqcbGmo().getWaitInterval();
/*     */       }
/*     */     } 
/* 542 */     if (Trace.isOn) {
/* 543 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "deliverMsgs(RemoteProxyQueue)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void deliverMsgsReconnectable(RemoteProxyQueue pq) throws JmqiException {
/* 551 */     if (Trace.isOn) {
/* 552 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "deliverMsgsReconnectable(RemoteProxyQueue)", new Object[] { pq });
/*     */     }
/*     */     
/* 555 */     RemoteSession session = this.hconn.getSession();
/*     */     try {
/* 557 */       pq.deliverMsgs(this.processed);
/*     */     }
/* 559 */     catch (JmqiException jmqiException) {
/* 560 */       if (Trace.isOn) {
/* 561 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "deliverMsgsReconnectable(RemoteProxyQueue)", (Throwable)jmqiException, 1);
/*     */       }
/*     */       
/* 564 */       if (this.hconn.isReconnectable() && 
/* 565 */         RemoteHconn.isReconnectableReasonCode(jmqiException
/* 566 */           .getReason())) {
/* 567 */         if (this.hconn.hasFailed()) {
/*     */           
/* 569 */           JmqiException traceRet1 = this.hconn.getReconnectionFailureException();
/* 570 */           if (Trace.isOn) {
/* 571 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "deliverMsgsReconnectable(RemoteProxyQueue)", (Throwable)traceRet1, 1);
/*     */           }
/*     */           
/* 574 */           throw traceRet1;
/*     */         } 
/*     */         try {
/* 577 */           this.hconn.initiateReconnection(session);
/* 578 */           deliverMsgsReconnectable(pq);
/*     */         }
/* 580 */         catch (JmqiException je) {
/* 581 */           if (Trace.isOn) {
/* 582 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "deliverMsgsReconnectable(RemoteProxyQueue)", (Throwable)je, 2);
/*     */           }
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 589 */         if (Trace.isOn) {
/* 590 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "deliverMsgsReconnectable(RemoteProxyQueue)", (Throwable)jmqiException, 2);
/*     */         }
/*     */         
/* 593 */         throw jmqiException;
/*     */       } 
/*     */     } 
/* 596 */     if (Trace.isOn) {
/* 597 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "deliverMsgsReconnectable(RemoteProxyQueue)");
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
/*     */   private void sleepPhase() {
/* 613 */     if (Trace.isOn) {
/* 614 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "sleepPhase()");
/*     */     }
/*     */     
/* 617 */     while (this.hconnActive) {
/*     */       boolean posted;
/*     */ 
/*     */       
/* 621 */       if (this.noMsgsWait != 0) {
/* 622 */         posted = waitOnSleepingEvent(this.noMsgsWait);
/*     */       } else {
/*     */         
/* 625 */         posted = waitOnSleepingEvent(2000);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 630 */       if (posted) {
/* 631 */         if (Trace.isOn) {
/* 632 */           Trace.data(this, "sleepPhase (return (posted))", null);
/*     */         }
/*     */         break;
/*     */       } 
/* 636 */       if (this.noMsgsWait != 0) {
/* 637 */         if (Trace.isOn) {
/* 638 */           Trace.data(this, "sleepPhase (return (noMsgsWait))", null);
/*     */         }
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/* 645 */       this.hconn.requestThreadLock();
/*     */       try {
/* 647 */         if (this.savedDispatchSeq != this.dispatchSeq) {
/*     */           
/* 649 */           if (Trace.isOn) {
/* 650 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "sleepPhase()", 1);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } finally {
/* 658 */         if (Trace.isOn) {
/* 659 */           Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "sleepPhase()");
/*     */         }
/*     */         
/* 662 */         this.hconn.releaseThreadLock();
/*     */       } 
/*     */     } 
/* 665 */     if (Trace.isOn) {
/* 666 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "sleepPhase()", 2);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void processHconn(RemoteTls tls) throws JmqiException {
/* 672 */     if (Trace.isOn) {
/* 673 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "processHconn(RemoteTls)", new Object[] { tls });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 678 */     if (this.hconn.suspendPending()) {
/* 679 */       this.hconn.doSuspend();
/*     */     }
/*     */ 
/*     */     
/* 683 */     if (this.hconn.hasEventsOutstanding()) {
/* 684 */       this.hconn.driveEventsEH();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 690 */     if (this.hconn.stopPending()) {
/* 691 */       this.hconn.doStop();
/* 692 */       this.hconnActive = false;
/*     */     } 
/*     */     
/* 695 */     if (Trace.isOn) {
/* 696 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "processHconn(RemoteTls)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isThreadSleeping() {
/* 706 */     if (Trace.isOn) {
/* 707 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "isThreadSleeping()", "getter", 
/* 708 */           Boolean.valueOf(this.threadSleeping));
/*     */     }
/* 710 */     return this.threadSleeping;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThreadSleeping(boolean sleeping) {
/* 717 */     if (Trace.isOn) {
/* 718 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "setThreadSleeping(boolean)", "setter", 
/* 719 */           Boolean.valueOf(sleeping));
/*     */     }
/* 721 */     this.threadSleeping = sleeping;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHconnInactive() {
/* 728 */     if (Trace.isOn) {
/* 729 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "setHconnInactive()");
/*     */     }
/* 731 */     this.hconnActive = false;
/* 732 */     postSleepingEvent();
/* 733 */     if (Trace.isOn) {
/* 734 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "setHconnInactive()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void incrementDispatchSeq() {
/* 743 */     if (Trace.isOn) {
/* 744 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "incrementDispatchSeq()");
/*     */     }
/*     */     
/* 747 */     this.dispatchSeq++;
/*     */     
/* 749 */     if (Trace.isOn) {
/* 750 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "incrementDispatchSeq()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void driveEventsMCForAllProxyQueues() {
/* 761 */     if (Trace.isOn) {
/* 762 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "driveEventsMCForAllProxyQueues()");
/*     */     }
/*     */ 
/*     */     
/* 766 */     for (RemoteProxyQueue pq : this.hconn.getDispatchQueueList()) {
/* 767 */       if (!this.hconnActive) {
/* 768 */         if (Trace.isOn) {
/* 769 */           Trace.data(this, "driveEventsMCForAllProxyQueues()", "hconn no longer active", pq);
/*     */         }
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */ 
/*     */       
/* 777 */       if (pq.isLogicallyRemoved()) {
/* 778 */         if (Trace.isOn) {
/* 779 */           Trace.data(this, "driveEventsMCForAllProxyQueues()", "skipping logically removed proxy queue", pq);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 789 */         pq.driveEventsMC();
/*     */       }
/* 791 */       catch (JmqiException e) {
/* 792 */         if (Trace.isOn) {
/* 793 */           Trace.catchBlock("com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "driveEventsMCForAllProxyQueues()", (Throwable)e);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 800 */     if (Trace.isOn) {
/* 801 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "driveEventsMCForAllProxyQueues()");
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
/*     */   public void dump(PrintWriter pw, int level) {
/* 813 */     if (Trace.isOn) {
/* 814 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "dump(PrintWriter,int)", new Object[] { pw, 
/* 815 */             Integer.valueOf(level) });
/*     */     }
/* 817 */     String prefix = Trace.buildPrefix(level);
/*     */     
/* 819 */     pw.format("%s%s (%s) :%n", new Object[] { prefix, toString(), this.threadName });
/* 820 */     pw.format("%s%s%n", new Object[] { prefix, this.threadSleeping ? "sleeping" : "not sleeping" });
/* 821 */     pw.format("%s%s%n", new Object[] { prefix, this.hconnActive ? "Hconn active" : "Hconn inactive" });
/* 822 */     pw.format("%s%s%n", new Object[] { prefix, this.sleepingEventPosted ? "sleeping event posted" : "sleeping event not posted" });
/* 823 */     pw.format("%sWaitQ: %b%n", new Object[] { prefix, Boolean.valueOf(this.waitQ) });
/*     */     
/* 825 */     if (Trace.isOn) {
/* 826 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread", "dump(PrintWriter,int)");
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
/*     */   private static class SleepingEventSync
/*     */   {
/*     */     SleepingEventSync() {
/* 841 */       if (Trace.isOn) {
/* 842 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.SleepingEventSync", "<init>()");
/*     */       }
/* 844 */       if (Trace.isOn)
/* 845 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.SleepingEventSync", "<init>()"); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\impl\RemoteDispatchThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */