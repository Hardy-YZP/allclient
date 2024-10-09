/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.mq.jms.NoMsgListenerException;
/*     */ import com.ibm.mq.jms.SessionClosedException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Vector;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SessionAsyncHelper
/*     */   implements Runnable
/*     */ {
/*     */   private static final String PROBE_01 = "01";
/*     */   private static final String PROBE_02 = "02";
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/SessionAsyncHelper.java";
/*     */   
/*     */   static {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/SessionAsyncHelper.java");
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
/*  77 */   private static int tId = 0;
/*  78 */   private static final Object tidLock = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   private Thread asyncThread = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private MQConnection connection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean finished = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean going = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int msgBatchSize;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int pollingInterval;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class PausedStateLock {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   private PausedStateLock pausedStateLock = new PausedStateLock();
/*     */ 
/*     */   
/*     */   private boolean paused = false;
/*     */   
/* 133 */   private Vector receivers = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private MQSession session;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SessionAsyncHelper(MQConnection con, MQSession ses) {
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "<init>(MQConnection,MQSession)", new Object[] { con, ses });
/*     */     }
/*     */     
/* 150 */     this.connection = con;
/* 151 */     this.session = ses;
/*     */     
/* 153 */     this.msgBatchSize = this.connection.getMsgBatchSize();
/* 154 */     this.pollingInterval = this.connection.getPollingInterval();
/*     */ 
/*     */     
/* 157 */     synchronized (tidLock) {
/* 158 */       String tName = "asyncDelivery" + tId;
/* 159 */       tId++;
/*     */ 
/*     */       
/* 162 */       this.asyncThread = createThread(this, tName);
/*     */     } 
/* 164 */     this.asyncThread.start();
/*     */     
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "<init>(MQConnection,MQSession)");
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
/*     */   private Thread createThread(final Runnable r, final String name) {
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "createThread(final Runnable,final String)", new Object[] { r, name });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 188 */     Thread t = AccessController.<Thread>doPrivileged(new PrivilegedAction<Thread>()
/*     */         {
/*     */           public Object run() {
/* 191 */             if (Trace.isOn) {
/* 192 */               Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "run()");
/*     */             }
/*     */             
/* 195 */             Thread t2 = new Thread(r, name);
/* 196 */             t2.setDaemon(true);
/* 197 */             if (Trace.isOn) {
/* 198 */               Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "run()", t2);
/*     */             }
/* 200 */             return t2;
/*     */           }
/*     */         });
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "createThread(final Runnable,final String)", t);
/*     */     }
/*     */     
/* 207 */     return t;
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
/*     */   void addReceiver(MQMessageConsumer rcvr) {
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "addReceiver(MQMessageConsumer)", new Object[] { rcvr });
/*     */     }
/*     */     
/* 224 */     if (!this.receivers.contains(rcvr)) {
/* 225 */       this.receivers.addElement(rcvr);
/*     */     }
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "addReceiver(MQMessageConsumer)");
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
/*     */   boolean callingFromOnMessage() {
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "callingFromOnMessage()");
/*     */     }
/*     */     
/* 247 */     Thread caller = Thread.currentThread();
/* 248 */     boolean result = caller.equals(this.asyncThread);
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "callingFromOnMessage()", 
/* 251 */           Boolean.valueOf(result));
/*     */     }
/* 253 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean hasReceivers() {
/* 262 */     if (Trace.isOn) {
/* 263 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "hasReceivers()");
/*     */     }
/*     */     
/* 266 */     boolean traceRet1 = (this.receivers.size() > 0);
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "hasReceivers()", 
/* 269 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 271 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeReceiver(MQMessageConsumer rcvr) {
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "removeReceiver(MQMessageConsumer)", new Object[] { rcvr });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 288 */     boolean wasFound = this.receivers.removeElement(rcvr);
/* 289 */     if (!wasFound && Trace.isOn) {
/* 290 */       Trace.traceData(this, "removeReceiver() didn't find the receiver on the list!", null);
/*     */     }
/*     */     
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "removeReceiver(MQMessageConsumer)");
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
/*     */   public void run() {
/* 309 */     if (Trace.isOn) {
/* 310 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "run()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 316 */     MQMessageConsumer rcvr = null;
/*     */ 
/*     */ 
/*     */     
/* 320 */     while (this.going) {
/*     */ 
/*     */ 
/*     */       
/* 324 */       Vector recvClone = (Vector)this.receivers.clone();
/* 325 */       Enumeration<MQMessageConsumer> en = recvClone.elements();
/* 326 */       boolean nothingReceived = true;
/* 327 */       MQMessageConsumer blockOnConsumer = null;
/*     */ 
/*     */ 
/*     */       
/* 331 */       if (recvClone.size() > 1)
/*     */       {
/*     */ 
/*     */         
/* 335 */         while (this.going) {
/*     */           try {
/* 337 */             rcvr = en.nextElement();
/*     */ 
/*     */ 
/*     */             
/* 341 */             for (int i = 0; i < this.msgBatchSize; ) {
/*     */               
/* 343 */               if (!this.going) {
/*     */                 break;
/*     */               }
/*     */ 
/*     */               
/* 348 */               boolean msgDelivered = rcvr.receiveAsync(0L);
/* 349 */               if (msgDelivered) {
/*     */                 
/* 351 */                 nothingReceived = false;
/*     */ 
/*     */                 
/* 354 */                 blockOnConsumer = rcvr;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 i++;
/*     */               } 
/*     */             } 
/* 363 */           } catch (NoMsgListenerException e) {
/* 364 */             if (Trace.isOn) {
/* 365 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "run()", (Throwable)e, 1);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 370 */             this.receivers.removeElement(rcvr);
/*     */           
/*     */           }
/* 373 */           catch (JMSException e) {
/* 374 */             if (Trace.isOn) {
/* 375 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "run()", (Throwable)e, 2);
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 381 */             this.receivers.removeElement(rcvr);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 389 */             this.connection.deliverException(e);
/*     */ 
/*     */ 
/*     */             
/* 393 */             if (Trace.isOn) {
/* 394 */               Trace.traceData(this, "Leaving the Recvr polling loop, since connection is broken = ", e);
/*     */             }
/*     */ 
/*     */             
/*     */             break;
/* 399 */           } catch (NoSuchElementException e) {
/* 400 */             if (Trace.isOn) {
/* 401 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "run()", e, 3);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 406 */             if (Trace.isOn) {
/* 407 */               Trace.traceData(this, "leaving the recvr polling loop, nothingReceived = " + nothingReceived, null);
/*     */             }
/*     */ 
/*     */             
/*     */             break;
/* 412 */           } catch (Throwable e) {
/* 413 */             if (Trace.isOn) {
/* 414 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "run()", e, 4);
/*     */             }
/*     */ 
/*     */             
/* 418 */             HashMap<Object, Object> ffstData = new HashMap<>();
/* 419 */             ffstData.put("Exception", e);
/* 420 */             ffstData.put("Message", "MQJMS1016");
/* 421 */             Trace.ffst(this, "run()", "01", ffstData, null);
/*     */           } 
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 431 */       if (this.going && nothingReceived) {
/*     */ 
/*     */         
/* 434 */         if (blockOnConsumer == null) {
/*     */           try {
/* 436 */             blockOnConsumer = this.receivers.firstElement();
/*     */           }
/* 438 */           catch (NoSuchElementException e) {
/* 439 */             if (Trace.isOn) {
/* 440 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "run()", e, 5);
/*     */             
/*     */             }
/*     */           
/*     */           }
/* 445 */           catch (Exception e) {
/* 446 */             if (Trace.isOn) {
/* 447 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "run()", e, 6);
/*     */             }
/*     */             
/* 450 */             if (Trace.isOn) {
/* 451 */               Trace.traceData(this, "failed to find a consumer for blocking read.", null);
/*     */             }
/*     */           } 
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 464 */         if (!this.session.isStarted()) {
/*     */           
/* 466 */           synchronized (this.pausedStateLock) {
/* 467 */             this.paused = true;
/* 468 */             this.pausedStateLock.notifyAll();
/*     */           } 
/*     */           try {
/* 471 */             this.session.waitForStart(this.pollingInterval);
/*     */ 
/*     */             
/* 474 */             if (this.session.isStarted()) {
/* 475 */               synchronized (this.pausedStateLock) {
/* 476 */                 this.paused = false;
/*     */               }
/*     */             
/*     */             }
/* 480 */           } catch (SessionClosedException e) {
/* 481 */             if (Trace.isOn) {
/* 482 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "run()", (Throwable)e, 7);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 487 */             this.going = false;
/*     */           }
/* 489 */           catch (JMSException e) {
/* 490 */             if (Trace.isOn) {
/* 491 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "run()", (Throwable)e, 8);
/*     */             }
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/* 497 */         synchronized (this.pausedStateLock) {
/* 498 */           this.paused = false;
/*     */         } 
/*     */         
/* 501 */         if (blockOnConsumer == null || this.session.getPlayNice() == true) {
/*     */ 
/*     */           
/*     */           try {
/*     */             
/* 506 */             Thread.sleep(this.pollingInterval);
/*     */           }
/* 508 */           catch (InterruptedException e) {
/* 509 */             if (Trace.isOn) {
/* 510 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "run()", e, 9);
/*     */             }
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/*     */         try {
/* 518 */           boolean messageDelivered = blockOnConsumer.receiveAsync(this.pollingInterval);
/* 519 */           if (!messageDelivered) {
/* 520 */             Thread.yield();
/*     */           }
/*     */         }
/* 523 */         catch (NoMsgListenerException e) {
/* 524 */           if (Trace.isOn) {
/* 525 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "run()", (Throwable)e, 10);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 530 */           this.receivers.removeElement(blockOnConsumer);
/*     */         
/*     */         }
/* 533 */         catch (JMSException e) {
/* 534 */           if (Trace.isOn) {
/* 535 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "run()", (Throwable)e, 11);
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 541 */           this.receivers.removeElement(blockOnConsumer);
/*     */ 
/*     */           
/* 544 */           this.connection.deliverException(e);
/*     */ 
/*     */ 
/*     */           
/* 548 */           if (Trace.isOn) {
/* 549 */             Trace.traceData(this, "Leaving the Recvr polling loop, since connection is broken = ", e);
/*     */           }
/*     */ 
/*     */           
/*     */           break;
/* 554 */         } catch (Throwable e) {
/* 555 */           if (Trace.isOn) {
/* 556 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "run()", e, 12);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 561 */           HashMap<Object, Object> ffstData = new HashMap<>();
/* 562 */           ffstData.put("Exception", e);
/* 563 */           ffstData.put("Message", "MQJMS1016");
/* 564 */           Trace.ffst(this, "run()", "02", ffstData, null);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 576 */     setFinished();
/*     */     
/* 578 */     if (Trace.isOn) {
/* 579 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "run()");
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
/*     */   private synchronized void setFinished() {
/* 592 */     if (Trace.isOn) {
/* 593 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "setFinished()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 598 */     synchronized (this.pausedStateLock) {
/* 599 */       this.paused = true;
/* 600 */       this.pausedStateLock.notifyAll();
/*     */     } 
/*     */     
/* 603 */     this.finished = true;
/* 604 */     notifyAll();
/* 605 */     if (Trace.isOn) {
/* 606 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "setFinished()");
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
/*     */   synchronized void shutdown() {
/* 620 */     if (Trace.isOn) {
/* 621 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "shutdown()");
/*     */     }
/*     */     
/* 624 */     this.going = false;
/*     */ 
/*     */     
/* 627 */     while (!this.finished) {
/*     */       try {
/* 629 */         wait();
/*     */       }
/* 631 */       catch (InterruptedException e) {
/* 632 */         if (Trace.isOn) {
/* 633 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "shutdown()", e);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 640 */     if (Trace.isOn) {
/* 641 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "shutdown()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void waitForPaused() {
/* 652 */     if (Trace.isOn) {
/* 653 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "waitForPaused()");
/*     */     }
/*     */     
/* 656 */     synchronized (this.pausedStateLock) {
/*     */       while (true) {
/*     */         try {
/* 659 */           if (!this.paused) {
/* 660 */             this.pausedStateLock.wait(); continue;
/*     */           } 
/*     */           break;
/* 663 */         } catch (InterruptedException e) {
/* 664 */           if (Trace.isOn) {
/* 665 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "waitForPaused()", e);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 672 */     if (Trace.isOn)
/* 673 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SessionAsyncHelper", "waitForPaused()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\SessionAsyncHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */