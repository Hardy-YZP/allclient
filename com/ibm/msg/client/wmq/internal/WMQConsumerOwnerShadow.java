/*     */ package com.ibm.msg.client.wmq.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiMQ;
/*     */ import com.ibm.mq.jmqi.MQCTLO;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.msg.client.commonservices.locking.TraceableReentrantLock;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.common.internal.Reason;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQConsumerOwner;
/*     */ import java.util.HashMap;
/*     */ import java.util.Vector;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.concurrent.locks.Condition;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WMQConsumerOwnerShadow
/*     */ {
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQConsumerOwnerShadow.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private volatile boolean isSuspended = false;
/*     */ 
/*     */   
/*     */   static final String sccsid = "@(#) com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQConsumerOwnerShadow.java, jmscc.wmq, k000 1.33 09/05/12 09:45:46";
/*     */   
/*  66 */   private static Vector<String> subscriptionNames = new Vector<>();
/*     */   
/*     */   private boolean asyncRunning = false;
/*     */   
/*     */   private JmqiEnvironment environment;
/*     */   
/*     */   private static class HconnLock
/*     */     extends TraceableReentrantLock
/*     */   {
/*     */     private static final long serialVersionUID = -6791238136814310354L;
/*     */     
/*     */     private HconnLock() {}
/*     */   }
/*  79 */   private ReentrantLock hconnLock = (ReentrantLock)new HconnLock();
/*  80 */   private Condition hconnCondition = this.hconnLock.newCondition();
/*     */   private Hconn hconn;
/*     */   private JmqiMQ mq;
/*  83 */   private int numAsyncConsumers = 0;
/*  84 */   private AtomicInteger closeCounter = new AtomicInteger(0);
/*     */   
/*     */   private String queueManagerName;
/*     */   
/*     */   private boolean started = false;
/*     */ 
/*     */   
/*     */   private static final class AsyncStateChangeLock
/*     */     extends TraceableReentrantLock
/*     */   {
/*     */     private static final long serialVersionUID = 2906982352714286711L;
/*     */     
/*     */     private AsyncStateChangeLock() {}
/*     */   }
/*  98 */   private ReentrantLock asyncStateChangeLock = (ReentrantLock)new AsyncStateChangeLock();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WMQConsumerOwnerShadow(WMQConsumerOwner helper, String qmname) {
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "<init>(WMQConsumerOwner,String)", new Object[] { helper, qmname });
/*     */     }
/*     */     
/* 109 */     this.environment = helper.getJmqiEnvironment();
/* 110 */     this.hconn = helper.getHconn();
/* 111 */     this.mq = helper.getJmqiMQ();
/* 112 */     this.queueManagerName = qmname;
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "<init>(WMQConsumerOwner,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void addAsyncConsumer() throws JMSException {
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "addAsyncConsumer()");
/*     */     }
/*     */     
/* 125 */     this.numAsyncConsumers++;
/* 126 */     controlAsyncService();
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "addAsyncConsumer()");
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
/*     */   private void controlAsyncService() throws JMSException {
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "controlAsyncService()");
/*     */     }
/*     */ 
/*     */     
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.data(this, "controlAsyncService()", "asyncRunning=" + this.asyncRunning + " started=" + this.started + " numAsyncConsumers=" + this.numAsyncConsumers);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     boolean acquiredStateChangeLock = false;
/* 162 */     if (!this.mq.isAsyncConsumeThread(this.hconn)) {
/* 163 */       this.asyncStateChangeLock.lock();
/* 164 */       acquiredStateChangeLock = true;
/* 165 */       if (Trace.isOn) {
/* 166 */         Trace.data(this, "controlAsyncService()", "asyncStateChangeLock acquired.");
/*     */       }
/*     */     } 
/*     */     
/* 170 */     boolean hadHconnLock = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 177 */       if (this.asyncRunning)
/*     */       {
/* 179 */         if (!this.started || this.numAsyncConsumers <= 0)
/*     */         {
/* 181 */           if (haveHconnLock()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 187 */             unlockHconn();
/* 188 */             hadHconnLock = true;
/*     */           } 
/*     */ 
/*     */           
/* 192 */           Pint cc = this.environment.newPint();
/* 193 */           Pint rc = this.environment.newPint();
/*     */           
/* 195 */           MQCTLO mqctlo = this.environment.newMQCTLO();
/* 196 */           this.mq.MQCTL(this.hconn, 4, mqctlo, cc, rc);
/*     */ 
/*     */           
/* 199 */           if (0 != rc.x) {
/* 200 */             if (Reason.isImpossibleReason(rc.x, cc.x, null)) {
/* 201 */               HashMap<String, Object> info = new HashMap<>();
/* 202 */               info.put("reason", rc);
/* 203 */               info.put("compcode", cc);
/* 204 */               info.put("queuemanager", this.queueManagerName);
/* 205 */               info.put("hconn", this.hconn);
/* 206 */               Trace.ffst(this, "controlAsyncService()", "XN008009", info, JMSException.class);
/*     */             } 
/* 208 */             JMSException je = Reason.createException("MQCTL", rc.x, cc.x, this.environment);
/* 209 */             if (Trace.isOn) {
/* 210 */               Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "controlAsyncService()", (Throwable)je, 1);
/*     */             }
/*     */             
/* 213 */             throw je;
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 218 */           if (this.numAsyncConsumers < 0) {
/* 219 */             HashMap<String, Object> info = new HashMap<>();
/* 220 */             info.put("numAsyncConsumers", Integer.valueOf(this.numAsyncConsumers));
/* 221 */             info.put("queuemanager", this.queueManagerName);
/* 222 */             Trace.ffst(this, "controlAsyncService()", "XN00N002", info, JMSException.class);
/*     */           } 
/*     */           
/* 225 */           this.asyncRunning = false;
/*     */           
/* 227 */           this.isSuspended = false;
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 232 */       else if (this.started && this.numAsyncConsumers > 0)
/*     */       {
/* 234 */         if (haveHconnLock()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 240 */           unlockHconn();
/* 241 */           hadHconnLock = true;
/*     */         } 
/*     */ 
/*     */         
/* 245 */         Pint cc = this.environment.newPint();
/* 246 */         Pint rc = this.environment.newPint();
/*     */         
/* 248 */         MQCTLO mqctlo = this.environment.newMQCTLO();
/* 249 */         this.mq.MQCTL(this.hconn, 1, mqctlo, cc, rc);
/*     */ 
/*     */         
/* 252 */         if (0 != rc.x) {
/* 253 */           if (Reason.isImpossibleReason(rc.x, cc.x, null)) {
/* 254 */             HashMap<String, Object> info = new HashMap<>();
/* 255 */             info.put("reason", rc);
/* 256 */             info.put("compcode", cc);
/* 257 */             info.put("queuemanager", this.queueManagerName);
/* 258 */             info.put("hconn", this.hconn);
/* 259 */             Trace.ffst(this, "controlAsyncService()", "XN008008", info, JMSException.class);
/*     */           } 
/* 261 */           JMSException je = Reason.createException("MQCTL", rc.x, cc.x, this.environment);
/* 262 */           if (Trace.isOn) {
/* 263 */             Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "controlAsyncService()", (Throwable)je, 2);
/*     */           }
/*     */           
/* 266 */           throw je;
/*     */         } 
/*     */         
/* 269 */         this.asyncRunning = true;
/*     */         
/* 271 */         this.isSuspended = false;
/*     */       }
/*     */     
/*     */     }
/* 275 */     catch (Exception ex) {
/* 276 */       if (Trace.isOn) {
/* 277 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "controlAsyncService()", ex);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 282 */       throw ex;
/*     */     }
/*     */     finally {
/*     */       
/* 286 */       if (acquiredStateChangeLock) {
/* 287 */         this.asyncStateChangeLock.unlock();
/* 288 */         if (Trace.isOn) {
/* 289 */           Trace.data(this, "controlAsyncService()", "asyncStateChangeLock released.");
/*     */         }
/*     */       } 
/*     */       
/* 293 */       if (hadHconnLock) {
/* 294 */         lockHconn();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 299 */     if (Trace.isOn) {
/* 300 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "controlAsyncService()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isSuspended() {
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "isSuspended()", "getter", 
/* 309 */           Boolean.valueOf(this.isSuspended));
/*     */     }
/* 311 */     return this.isSuspended;
/*     */   }
/*     */   
/*     */   boolean isAsyncRunning() {
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "isAsyncRunning()", "getter", 
/* 317 */           Boolean.valueOf(this.asyncRunning));
/*     */     }
/* 319 */     return this.asyncRunning;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   synchronized boolean isSubscriptionInUse(String subscriptionName) {
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "isSubscriptionInUse(String)", new Object[] { subscriptionName });
/*     */     }
/*     */ 
/*     */     
/* 335 */     if (!subscriptionNames.contains(subscriptionName)) {
/*     */       
/* 337 */       subscriptionNames.add(subscriptionName);
/* 338 */       if (Trace.isOn) {
/* 339 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "isSubscriptionInUse(String)", 
/* 340 */             Boolean.valueOf(false), 1);
/*     */       }
/* 342 */       return false;
/*     */     } 
/*     */     
/* 345 */     if (Trace.isOn) {
/* 346 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "isSubscriptionInUse(String)", 
/* 347 */           Boolean.valueOf(true), 2);
/*     */     }
/* 349 */     return true;
/*     */   }
/*     */   
/*     */   void removeAsyncConsumer() throws JMSException {
/* 353 */     if (Trace.isOn) {
/* 354 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "removeAsyncConsumer()");
/*     */     }
/*     */     
/* 357 */     this.numAsyncConsumers--;
/* 358 */     controlAsyncService();
/* 359 */     if (Trace.isOn) {
/* 360 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "removeAsyncConsumer()");
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
/*     */   synchronized void removeSubscription(String subscriptionName) {
/* 373 */     if (Trace.isOn) {
/* 374 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "removeSubscription(String)", new Object[] { subscriptionName });
/*     */     }
/*     */ 
/*     */     
/* 378 */     subscriptionNames.remove(subscriptionName);
/* 379 */     if (Trace.isOn) {
/* 380 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "removeSubscription(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void resumeAsyncService() throws JMSException {
/* 390 */     if (Trace.isOn) {
/* 391 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "resumeAsyncService()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 406 */     boolean acquiredStateChangeLock = false;
/* 407 */     if (!this.mq.isAsyncConsumeThread(this.hconn)) {
/* 408 */       this.asyncStateChangeLock.lock();
/* 409 */       acquiredStateChangeLock = true;
/* 410 */       if (Trace.isOn) {
/* 411 */         Trace.data(this, "resumeAsyncService()", "asyncStateChangeLock acquired.");
/*     */       }
/*     */     } 
/*     */     
/* 415 */     boolean hadHconnLock = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 422 */       if (!this.mq.isAsyncConsumeThread(this.hconn)) {
/* 423 */         if (isAsyncRunning()) {
/* 424 */           if (this.isSuspended) {
/*     */             
/* 426 */             if (haveHconnLock()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 432 */               unlockHconn();
/* 433 */               hadHconnLock = true;
/*     */             } 
/*     */             
/* 436 */             Pint cc = this.environment.newPint();
/* 437 */             Pint rc = this.environment.newPint();
/* 438 */             MQCTLO mqctlo = this.environment.newMQCTLO();
/* 439 */             this.mq.MQCTL(this.hconn, 131072, mqctlo, cc, rc);
/*     */ 
/*     */             
/* 442 */             if (0 != rc.x) {
/* 443 */               if (Reason.isImpossibleReason(rc.x, cc.x, null)) {
/* 444 */                 HashMap<String, Object> info = new HashMap<>();
/* 445 */                 info.put("reason", rc);
/* 446 */                 info.put("compcode", cc);
/* 447 */                 info.put("queuemanager", this.queueManagerName);
/* 448 */                 info.put("hconn", this.hconn);
/* 449 */                 Trace.ffst(this, "resumeAsyncService()", "XN00N006", info, JMSException.class);
/*     */               } 
/* 451 */               JMSException je = Reason.createException("MQCTL", rc.x, cc.x, this.environment);
/* 452 */               if (Trace.isOn) {
/* 453 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "resumeAsyncService()", (Throwable)je);
/*     */               }
/*     */               
/* 456 */               throw je;
/*     */             } 
/* 458 */             this.isSuspended = false;
/*     */           
/*     */           }
/* 461 */           else if (Trace.isOn) {
/* 462 */             Trace.data(this, "WMQConsumerOwnerShadow", "Resume not called as NOT suspended");
/*     */           
/*     */           }
/*     */         
/*     */         }
/* 467 */         else if (Trace.isOn) {
/* 468 */           Trace.data(this, "WMQConsumerOwnerShadow", "Resume not called as async service not running");
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/* 474 */       else if (Trace.isOn) {
/* 475 */         Trace.data(this, "WMQConsumerOwnerShadow", "Resume not called as we are the async consume thread");
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 480 */     catch (Exception ex) {
/* 481 */       if (Trace.isOn) {
/* 482 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "resumeAsyncService()", ex);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 487 */       throw ex;
/*     */     } finally {
/*     */       
/* 490 */       if (acquiredStateChangeLock) {
/* 491 */         this.asyncStateChangeLock.unlock();
/* 492 */         if (Trace.isOn) {
/* 493 */           Trace.data(this, "resumeAsyncService()", "asyncStateChangeLock released.");
/*     */         }
/*     */       } 
/*     */       
/* 497 */       if (hadHconnLock) {
/* 498 */         lockHconn();
/*     */       }
/*     */     } 
/*     */     
/* 502 */     if (Trace.isOn) {
/* 503 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "resumeAsyncService()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void start() throws JMSException {
/* 513 */     if (Trace.isOn) {
/* 514 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "start()");
/*     */     }
/*     */     
/* 517 */     this.started = true;
/* 518 */     controlAsyncService();
/*     */     
/* 520 */     if (Trace.isOn) {
/* 521 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "start()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void stop() throws JMSException {
/* 530 */     if (Trace.isOn) {
/* 531 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "stop()");
/*     */     }
/*     */     
/* 534 */     this.started = false;
/* 535 */     controlAsyncService();
/* 536 */     if (Trace.isOn) {
/* 537 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "stop()");
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
/*     */   boolean suspendAsyncService() throws JMSException {
/* 549 */     if (Trace.isOn) {
/* 550 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "suspendAsyncService()");
/*     */     }
/*     */ 
/*     */     
/* 554 */     boolean didSuspend = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 567 */     boolean acquiredStateChangeLock = false;
/*     */     
/* 569 */     if (!this.mq.isAsyncConsumeThread(this.hconn)) {
/* 570 */       this.asyncStateChangeLock.lock();
/* 571 */       acquiredStateChangeLock = true;
/* 572 */       if (Trace.isOn) {
/* 573 */         Trace.data(this, "suspendAsyncService()", "asyncStateChangeLock acquired.");
/*     */       }
/*     */     } 
/*     */     
/* 577 */     boolean hadHconnLock = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 583 */       if (!this.mq.isAsyncConsumeThread(this.hconn)) {
/* 584 */         if (isAsyncRunning()) {
/* 585 */           if (!isSuspended()) {
/*     */ 
/*     */             
/* 588 */             this.isSuspended = true;
/*     */             
/* 590 */             if (haveHconnLock()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 596 */               unlockHconn();
/* 597 */               hadHconnLock = true;
/*     */             } 
/*     */             
/* 600 */             Pint cc = this.environment.newPint();
/* 601 */             Pint rc = this.environment.newPint();
/* 602 */             MQCTLO mqctlo = this.environment.newMQCTLO();
/* 603 */             this.mq.MQCTL(this.hconn, 65536, mqctlo, cc, rc);
/*     */ 
/*     */             
/* 606 */             if (0 != rc.x) {
/* 607 */               this.isSuspended = false;
/*     */               
/* 609 */               if (Reason.isImpossibleReason(rc.x, cc.x, null)) {
/* 610 */                 HashMap<String, Object> info = new HashMap<>();
/* 611 */                 info.put("reason", rc);
/* 612 */                 info.put("compcode", cc);
/* 613 */                 info.put("queuemanager", this.queueManagerName);
/* 614 */                 info.put("hconn", this.hconn);
/* 615 */                 Trace.ffst(this, "suspendAsyncService()", "XN00N007", info, JMSException.class);
/*     */               } 
/* 617 */               JMSException je = Reason.createException("MQCTL", rc.x, cc.x, this.environment);
/* 618 */               if (Trace.isOn) {
/* 619 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "suspendAsyncService()", (Throwable)je);
/*     */               }
/*     */               
/* 622 */               throw je;
/*     */             } 
/*     */             
/* 625 */             if (Trace.isOn) {
/* 626 */               Trace.data(this, "WMQConsumerOwnerShadow", "Setting suspend to true");
/*     */             }
/* 628 */             didSuspend = true;
/*     */           
/*     */           }
/* 631 */           else if (Trace.isOn) {
/* 632 */             Trace.data(this, "WMQConsumerOwnerShadow", "Suspend no called as async service already suspended");
/*     */           
/*     */           }
/*     */ 
/*     */         
/*     */         }
/* 638 */         else if (Trace.isOn) {
/* 639 */           Trace.data(this, "WMQConsumerOwnerShadow", "Suspend not called as async service not running");
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/* 645 */       else if (Trace.isOn) {
/* 646 */         Trace.data(this, "WMQConsumerOwnerShadow", "Suspend not called as we are the async consume thread");
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 651 */     catch (Exception ex) {
/* 652 */       if (Trace.isOn) {
/* 653 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "suspendAsyncService()", ex);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 658 */       throw ex;
/*     */     } finally {
/*     */       
/* 661 */       if (acquiredStateChangeLock) {
/* 662 */         this.asyncStateChangeLock.unlock();
/* 663 */         if (Trace.isOn) {
/* 664 */           Trace.data(this, "suspendAsyncService()", "asyncStateChangeLock released.");
/*     */         }
/*     */       } 
/*     */       
/* 668 */       if (hadHconnLock) {
/* 669 */         lockHconn();
/*     */       }
/*     */     } 
/* 672 */     if (Trace.isOn) {
/* 673 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "suspendAsyncService()", 
/* 674 */           Boolean.valueOf(didSuspend));
/*     */     }
/* 676 */     return didSuspend;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void incrementCloseCounter() {
/* 683 */     int closeCount = this.closeCounter.incrementAndGet();
/* 684 */     if (Trace.isOn) {
/* 685 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "incrementCloseCounter()", 
/* 686 */           Integer.valueOf(closeCount));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void decrementCloseCounter() {
/* 695 */     int closeCount = this.closeCounter.decrementAndGet();
/* 696 */     if (Trace.isOn) {
/* 697 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "decrementCloseCounter()", 
/* 698 */           Integer.valueOf(closeCount));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   int getCloseCounter() {
/* 704 */     int closeCount = this.closeCounter.get();
/* 705 */     if (Trace.isOn) {
/* 706 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "getCloseCounter()", "getter", 
/* 707 */           Integer.valueOf(closeCount));
/*     */     }
/* 709 */     return closeCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void lockHconn() {
/* 717 */     if (Trace.isOn) {
/* 718 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "lockHconn()", null);
/*     */     }
/* 720 */     this.hconnLock.lock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unlockHconn() {
/* 728 */     if (Trace.isOn) {
/* 729 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "unlockHconn()", null);
/*     */     }
/* 731 */     this.hconnLock.unlock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void awaitHconn() throws InterruptedException {
/* 740 */     if (Trace.isOn) {
/* 741 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "awaitHconn()", null);
/*     */     }
/* 743 */     this.hconnCondition.await();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void signalHconn() {
/* 751 */     if (Trace.isOn) {
/* 752 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "signalHconn()", null);
/*     */     }
/* 754 */     this.hconnCondition.signal();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean haveHconnLock() {
/* 762 */     boolean result = this.hconnLock.isHeldByCurrentThread();
/* 763 */     if (Trace.isOn) {
/* 764 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "haveHconnLock()", null);
/*     */     }
/* 766 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initiateConnectionStop() {
/* 773 */     if (Trace.isOn) {
/* 774 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "initiateConnectionStop()");
/*     */     }
/*     */     
/* 777 */     this.hconn.initiateConnectionStop();
/* 778 */     if (Trace.isOn) {
/* 779 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "initiateConnectionStop()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void finishConnectionStop() {
/* 788 */     if (Trace.isOn) {
/* 789 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "finishConnectionStop()");
/*     */     }
/*     */     
/* 792 */     this.hconn.finishConnectionStop();
/* 793 */     if (Trace.isOn)
/* 794 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerOwnerShadow", "finishConnectionStop()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\WMQConsumerOwnerShadow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */