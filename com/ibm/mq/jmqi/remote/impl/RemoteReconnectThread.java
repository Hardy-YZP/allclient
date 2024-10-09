/*     */ package com.ibm.mq.jmqi.remote.impl;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.internal.Configuration;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.mq.jmqi.remote.api.RemoteFAP;
/*     */ import com.ibm.mq.jmqi.remote.api.RemoteParentHconn;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.msg.client.commonservices.Log.Log;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemoteReconnectThread
/*     */   extends JmqiObject
/*     */   implements Runnable
/*     */ {
/*     */   private static final String SCCSID = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteReconnectThread.java";
/*     */   private List<RemoteParentHconn> hconns;
/*     */   private RemoteFAP fap;
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteReconnectThread.java");
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
/*  69 */   private static Random rand = new Random();
/*     */ 
/*     */   
/*  72 */   private static volatile int reconnectCycle = 0;
/*     */ 
/*     */   
/*  75 */   private int[] rcnTimes = null;
/*     */   
/*  77 */   private int[] defaultDelayTimes = new int[] { 1000, 2000, 4000, 8000, 16000, 25000 };
/*     */ 
/*     */   
/*  80 */   private ReconnectMutex reconnectMutex = new ReconnectMutex();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteReconnectThread(JmqiEnvironment jmqiEnv, RemoteFAP fap) {
/*  89 */     super(jmqiEnv);
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread", "<init>(JmqiEnvironment,RemoteFAP)", new Object[] { jmqiEnv, fap });
/*     */     }
/*     */     
/*  94 */     this.hconns = new ArrayList<>();
/*  95 */     this.fap = fap;
/*  96 */     reconnectCycle++;
/*  97 */     buildReconnectionTimes();
/*     */     
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread", "<init>(JmqiEnvironment,RemoteFAP)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread", "run()");
/*     */     }
/*     */ 
/*     */     
/* 116 */     JmqiSystemEnvironment sysEnv = (JmqiSystemEnvironment)this.env;
/* 117 */     RemoteTls tls = (RemoteTls)sysEnv.getComponentTls(this.fap.getJmqiCompId());
/* 118 */     tls.isReconnectThread = true;
/*     */     
/*     */     while (true) {
/*     */       try {
/* 122 */         RemoteParentHconn hconn = bestHconn();
/*     */         
/* 124 */         HashMap<String, Object> logInserts = new HashMap<>();
/* 125 */         logInserts.put("XMSC_HCONN", hconn.toString());
/* 126 */         logInserts.put("XMSC_QUEUE_MANAGER", hconn.getReconnectQmName());
/* 127 */         Log.log(this, "run()", "JMSCS0044", logInserts);
/*     */ 
/*     */         
/* 130 */         if (hconn.reconnectSelf()) {
/* 131 */           Log.log(this, "run()", "JMSCS0047", logInserts);
/*     */           
/*     */           continue;
/*     */         } 
/* 135 */         if (hconn.hasFailed()) {
/* 136 */           Log.log(this, "run()", "JMSCS0045", logInserts);
/* 137 */           releaseHconn(hconn);
/*     */           continue;
/*     */         } 
/* 140 */         Log.log(this, "run()", "JMSCS0046", logInserts);
/*     */         
/* 142 */         hconn.setReconnectionAttempts(hconn.getReconnectionAttempts() + 1);
/*     */         
/* 144 */         int i = hconn.getReconnectionAttempts() - 1;
/* 145 */         if (i >= this.rcnTimes.length) {
/* 146 */           i = this.rcnTimes.length - 1;
/*     */         }
/* 148 */         hconn.setNextReconnect(System.currentTimeMillis() + this.rcnTimes[i]);
/*     */ 
/*     */       
/*     */       }
/* 152 */       catch (Throwable t) {
/* 153 */         String causeMessage = null;
/* 154 */         Throwable cause = t.getCause();
/* 155 */         if (cause != null) {
/* 156 */           causeMessage = cause.getMessage();
/*     */         }
/* 158 */         HashMap<String, Object> info = new HashMap<>();
/* 159 */         info.put("Exception Summary", JmqiTools.getExSumm(t));
/* 160 */         info.put("Exception cause message", causeMessage);
/* 161 */         info.put("exception", t);
/* 162 */         info.put("Description", "Unexpected Exception");
/* 163 */         Trace.ffst(this, "run()", "01", info, t.getClass());
/*     */       } 
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
/*     */   public boolean eligibleForReconnect(RemoteParentHconn remoteParentHconn, boolean register) {
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread", "eligibleForReconnect(RemoteParentHconn,boolean)", new Object[] { remoteParentHconn, 
/*     */             
/* 181 */             Boolean.valueOf(register) });
/*     */     }
/* 183 */     boolean eligible = true;
/*     */     
/* 185 */     synchronized (this.reconnectMutex) {
/* 186 */       if (register) {
/* 187 */         if (remoteParentHconn.isReconnectable() && !remoteParentHconn.hasFailed()) {
/* 188 */           if (remoteParentHconn.initializeForReconnect()) {
/* 189 */             if (!remoteParentHconn.queuedForReconnect()) {
/*     */               
/* 191 */               HashMap<String, Object> logInserts = new HashMap<>();
/* 192 */               logInserts.put("XMSC_HCONN", remoteParentHconn.toString());
/* 193 */               logInserts.put("XMSC_QUEUE_MANAGER", remoteParentHconn.getReconnectQmName());
/* 194 */               Log.log(this, "run()", "JMSCS0043", logInserts);
/*     */               
/* 196 */               this.hconns.add(remoteParentHconn);
/*     */             } 
/* 198 */             this.reconnectMutex.notifyAll();
/*     */           } 
/*     */         } else {
/*     */           
/* 202 */           eligible = false;
/*     */         }
/*     */       
/*     */       }
/* 206 */       else if (!this.hconns.contains(remoteParentHconn)) {
/* 207 */         remoteParentHconn.setReconnectable(false);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 212 */     if (Trace.isOn) {
/* 213 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread", "eligibleForReconnect(RemoteParentHconn,boolean)", 
/* 214 */           Boolean.valueOf(eligible));
/*     */     }
/* 216 */     return eligible;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void releaseHconn(RemoteParentHconn rcnHconn) {
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread", "releaseHconn(RemoteParentHconn)", new Object[] { rcnHconn });
/*     */     }
/*     */     
/* 229 */     synchronized (this.reconnectMutex) {
/* 230 */       rcnHconn.notQueuedForReconnect();
/* 231 */       rcnHconn.releaseReconnectLatch();
/* 232 */       this.hconns.remove(rcnHconn);
/*     */     } 
/*     */     
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread", "releaseHconn(RemoteParentHconn)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private RemoteParentHconn bestHconn() {
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread", "bestHconn()");
/*     */     }
/* 249 */     RemoteParentHconn best = null;
/* 250 */     while (best == null) {
/* 251 */       if (Trace.isOn) {
/* 252 */         Trace.data(this, "bestHconn()", "in main loop", null);
/*     */       }
/* 254 */       long bestTime = 0L;
/*     */ 
/*     */ 
/*     */       
/* 258 */       long delayTime = 2147483647L;
/* 259 */       synchronized (this.reconnectMutex) {
/*     */ 
/*     */ 
/*     */         
/* 263 */         if (this.hconns.isEmpty()) {
/* 264 */           if (Trace.isOn) {
/* 265 */             Trace.data(this, "bestHconn()", "No Hconns to check - waiting for work", null);
/*     */           }
/*     */           
/*     */           try {
/* 269 */             this.reconnectMutex.wait();
/* 270 */             reconnectCycle++;
/*     */           }
/* 272 */           catch (InterruptedException e) {
/* 273 */             if (Trace.isOn) {
/* 274 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread", "bestHconn()", e, 1);
/*     */             }
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 282 */         long now = System.currentTimeMillis();
/* 283 */         if (Trace.isOn) {
/* 284 */           Trace.data(this, "bestHconn()", "checking list of size ", Integer.valueOf(this.hconns.size()));
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 289 */         for (RemoteParentHconn hconn : new ArrayList(this.hconns)) {
/* 290 */           if (Trace.isOn) {
/* 291 */             Trace.data(this, "bestHconn()", "checking hconn - ", hconn);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 296 */           if (shouldDie(hconn, now)) {
/* 297 */             releaseHconn(hconn);
/*     */             
/*     */             continue;
/*     */           } 
/*     */           
/* 302 */           long overdueAmount = now - hconn.getNextReconnect();
/* 303 */           if (overdueAmount >= 0L) {
/*     */ 
/*     */ 
/*     */             
/* 307 */             if (moreEligible(overdueAmount, best, bestTime)) {
/* 308 */               bestTime = overdueAmount;
/* 309 */               best = hconn;
/*     */             } 
/*     */             continue;
/*     */           } 
/* 313 */           long newDelayTime = -overdueAmount;
/*     */ 
/*     */ 
/*     */           
/* 317 */           if (delayTime > newDelayTime) {
/* 318 */             if (Trace.isOn) {
/* 319 */               Trace.data(this, "bestHconn()", "  - adjusting delaytime to ", Long.valueOf(newDelayTime));
/*     */             }
/* 321 */             delayTime = newDelayTime;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 330 */       if (best == null && delayTime < 2147483647L) {
/*     */ 
/*     */         
/* 333 */         if (Trace.isOn) {
/* 334 */           Trace.data(this, "bestHconn()", "no suitable hconn - sleeping for ", Long.valueOf(delayTime));
/*     */         }
/*     */         try {
/* 337 */           Thread.sleep(delayTime);
/*     */         }
/* 339 */         catch (InterruptedException e) {
/* 340 */           if (Trace.isOn) {
/* 341 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread", "bestHconn()", e, 2);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 348 */     if (Trace.isOn) {
/* 349 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread", "bestHconn()", best);
/*     */     }
/* 351 */     return best;
/*     */   }
/*     */   
/*     */   private boolean moreEligible(long overdueAmount, RemoteParentHconn best, long bestTime) {
/* 355 */     boolean hconnMoreEligibleThanBest = false;
/*     */     
/* 357 */     if (best == null) {
/* 358 */       hconnMoreEligibleThanBest = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 365 */     else if (bestTime <= overdueAmount) {
/* 366 */       hconnMoreEligibleThanBest = true;
/*     */     } 
/* 368 */     return hconnMoreEligibleThanBest;
/*     */   }
/*     */   
/*     */   private boolean shouldDie(RemoteParentHconn hconn, long now) {
/* 372 */     if (Trace.isOn) {
/* 373 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread", "shouldDie(RemoteParentHconn,long)", new Object[] { hconn, 
/* 374 */             Long.valueOf(now) });
/*     */     }
/*     */     
/* 377 */     boolean shouldDie = false;
/*     */     
/* 379 */     if (!hconn.isReconnectable()) {
/* 380 */       HashMap<String, Object> logInserts = new HashMap<>();
/* 381 */       logInserts.put("XMSC_HCONN", hconn.toString());
/* 382 */       Log.log(this, "shouldDie(RemoteParentHconn,long)", "JMSCS0051", logInserts);
/* 383 */       if (Trace.isOn) {
/* 384 */         Trace.data(this, "shouldDie(RemoteParentHconn,long)", hconn + " - not eligible for reconnection", null);
/*     */       }
/* 386 */       hconn.setReconnectionFailure(2, 2009, null);
/* 387 */       shouldDie = true;
/*     */     }
/* 389 */     else if (hconn.getReconnectExpiry() < now) {
/* 390 */       HashMap<String, Object> logInserts = new HashMap<>();
/* 391 */       logInserts.put("XMSC_HCONN", hconn.toString());
/* 392 */       Log.log(this, "shouldDie(RemoteParentHconn,long)", "JMSCS0052", logInserts);
/* 393 */       if (Trace.isOn) {
/* 394 */         Trace.data(this, "shouldDie(RemoteParentHconn,long)", hconn + " - expired", null);
/*     */       }
/* 396 */       hconn.setReconnectionFailure(2, 2556, null);
/* 397 */       shouldDie = true;
/*     */     } 
/* 399 */     if (Trace.isOn)
/* 400 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread", "shouldDie(RemoteParentHconn,long)", new Object[] {
/* 401 */             Boolean.valueOf(shouldDie)
/*     */           }); 
/* 403 */     return shouldDie;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void buildReconnectionTimes() {
/* 412 */     if (Trace.isOn) {
/* 413 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread", "buildReconnectionTimes()");
/*     */     }
/*     */     
/* 416 */     String rcnTimeProperty = this.env.getConfiguration().getStringValue(Configuration.CHANNEL_RECONDELAY);
/*     */     
/* 418 */     if (rcnTimeProperty == null) {
/* 419 */       this.rcnTimes = new int[this.defaultDelayTimes.length];
/* 420 */       for (int i = 0; i < this.defaultDelayTimes.length; i++)
/*     */       {
/* 422 */         int randTime = rand.nextInt((i + 1) * 250);
/* 423 */         this.rcnTimes[i] = this.defaultDelayTimes[i] + randTime;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 428 */       String[] timePairs = rcnTimeProperty.split("[\\(\\)]+");
/* 429 */       ArrayList<Integer> tempList = new ArrayList<>(timePairs.length);
/* 430 */       for (String timePair : timePairs) {
/* 431 */         if (timePair.length() != 0) {
/* 432 */           String[] times = timePair.split(",");
/*     */           try {
/* 434 */             int delayTime = Integer.parseInt(times[0]);
/* 435 */             int randTime = (times.length > 1) ? Integer.parseInt(times[1]) : 0;
/* 436 */             tempList.add(Integer.valueOf(delayTime + randTime));
/*     */           }
/* 438 */           catch (NumberFormatException e) {
/* 439 */             if (Trace.isOn) {
/* 440 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread", "buildReconnectionTimes()", e);
/*     */             }
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 447 */         this.rcnTimes = new int[tempList.size()];
/* 448 */         int i = 0;
/* 449 */         for (Integer rcnTime : tempList) {
/* 450 */           this.rcnTimes[i++] = rcnTime.intValue();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 455 */     HashMap<String, Object> logInserts = new HashMap<>();
/* 456 */     logInserts.put("XMSC_RECONNECTION_TIMES", Arrays.toString(this.rcnTimes));
/* 457 */     Log.log(this, "shouldDie(RemoteParentHconn,long)", "JMSCS0053", logInserts);
/*     */     
/* 459 */     if (Trace.isOn) {
/* 460 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteReconnectThread", "buildReconnectionTimes()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getReconnectCycle() {
/* 470 */     return reconnectCycle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class ReconnectMutex
/*     */   {
/*     */     ReconnectMutex() {
/* 483 */       if (Trace.isOn) {
/* 484 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.ReconnectMutex", "<init>()");
/*     */       }
/*     */       
/* 487 */       if (Trace.isOn)
/* 488 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.ReconnectMutex", "<init>()"); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\impl\RemoteReconnectThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */