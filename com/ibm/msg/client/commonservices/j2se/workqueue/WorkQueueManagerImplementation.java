/*      */ package com.ibm.msg.client.commonservices.j2se.workqueue;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.provider.workqueue.CSPWorkQueueManager;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.commonservices.workqueue.WorkQueueItem;
/*      */ import com.ibm.msg.client.commonservices.workqueue.WorkQueueManager;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class WorkQueueManagerImplementation
/*      */   implements CSPWorkQueueManager
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/workqueue/WorkQueueManagerImplementation.java";
/*      */   private static final String threadGroupName = "JMSCCThreadPool";
/*      */   private static final String threadPoolMasterName = "JMSCCThreadPoolMaster";
/*      */   private static final String threadPoolWorkerNamePrefix = "JMSCCThreadPoolWorker-";
/*      */   private final ThreadGroup poolGroup;
/*      */   
/*      */   static {
/*   49 */     if (Trace.isOn) {
/*   50 */       Trace.data("com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/workqueue/WorkQueueManagerImplementation.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   73 */   private int threadCount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Vector<ThreadPoolWorker> threadPool;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Vector<ThreadPoolWorker> inactiveThreadPool;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private LinkedList<WorkQueueItem> workQueue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private LinkedList<WorkQueueItem> highPriorityWorkQueue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private WorkQueueManagerThread workManagerThread;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean useDeamonThreads = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public WorkQueueManagerImplementation() {
/*  120 */     if (Trace.isOn) {
/*  121 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "<init>()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  135 */     this.poolGroup = new ThreadGroup("JMSCCThreadPool");
/*  136 */     if (this.poolGroup.isDaemon()) {
/*  137 */       if (Trace.isOn) {
/*  138 */         Trace.traceData(this, "<init>()", "Unsetting daemon ThreadPool");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  144 */       AccessController.doPrivileged(new PrivilegedAction()
/*      */           {
/*      */             public Object run()
/*      */             {
/*  148 */               if (Trace.isOn) {
/*  149 */                 Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "run()");
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  154 */               WorkQueueManagerImplementation.this.poolGroup.setDaemon(false);
/*  155 */               if (Trace.isOn) {
/*  156 */                 Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.null", "run()", null);
/*      */               }
/*      */               
/*  159 */               return null;
/*      */             }
/*      */           });
/*      */     } 
/*      */ 
/*      */     
/*  165 */     this.threadPool = new Vector<>();
/*  166 */     this.inactiveThreadPool = new Vector<>();
/*  167 */     this.workQueue = new LinkedList<>();
/*  168 */     this.highPriorityWorkQueue = new LinkedList<>();
/*      */     
/*  170 */     this.workManagerThread = new WorkQueueManagerThread("JMSCCThreadPoolMaster");
/*  171 */     this.workManagerThread.setDaemon(this.useDeamonThreads);
/*  172 */     this.workManagerThread.start();
/*  173 */     if (Trace.isOn) {
/*  174 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "<init>()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void enqueueItem(WorkQueueItem newItem, int priority) {
/*  191 */     if (Trace.isOn) {
/*  192 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "enqueueItem(WorkQueueItem,int)", new Object[] { newItem, 
/*      */             
/*  194 */             Integer.valueOf(priority) });
/*      */     }
/*      */     
/*  197 */     if (newItem == null) {
/*  198 */       if (Trace.isOn)
/*      */       {
/*  200 */         Trace.traceData(this, "enqueueItem(WorkQueueItem,int)", "New item is null, returning", null);
/*      */       }
/*      */       
/*  203 */       if (Trace.isOn) {
/*  204 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "enqueueItem(WorkQueueItem,int)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  211 */     if (priority == 1) {
/*  212 */       synchronized (this.highPriorityWorkQueue) {
/*  213 */         if (Trace.isOn) {
/*  214 */           Trace.traceData(this, "enqueueItem(WorkQueueItem,int)", "Enqueueing high priority item", newItem);
/*      */         }
/*      */         
/*  217 */         this.highPriorityWorkQueue.addLast(newItem);
/*      */       } 
/*      */     } else {
/*      */       
/*  221 */       synchronized (this.workQueue) {
/*  222 */         if (Trace.isOn) {
/*  223 */           Trace.traceData(this, "enqueueItem(WorkQueueItem,int)", "Enqueueing normal priority item", newItem);
/*      */         }
/*      */         
/*  226 */         this.workQueue.addLast(newItem);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  232 */     if (Trace.isOn) {
/*  233 */       Trace.traceData(this, "enqueueItem(WorkQueueItem,int)", "Waking WorkManagerThread", this.workManagerThread);
/*      */     }
/*      */     
/*  236 */     this.workManagerThread.setWorkWaiting();
/*      */     
/*  238 */     if (Trace.isOn) {
/*  239 */       Trace.traceData(this, "enqueueItem(WorkQueueItem,int)", "Woken WorkManagerThread", null);
/*      */     }
/*  241 */     if (Trace.isOn) {
/*  242 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "enqueueItem(WorkQueueItem,int)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int fillThreadPool() {
/*  259 */     if (Trace.isOn) {
/*  260 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "fillThreadPool()");
/*      */     }
/*      */ 
/*      */     
/*  264 */     int threadsCreated = 0;
/*  265 */     int threadsToCreate = WorkQueueManager.getMaxThreadPoolSize() - this.threadPool.size();
/*      */     
/*  267 */     if (Trace.isOn) {
/*  268 */       Trace.traceData(this, "fillThreadPool()", "Creating " + threadsToCreate + " Threads", null);
/*      */     }
/*      */     
/*  271 */     if (threadsToCreate > 0) {
/*      */ 
/*      */ 
/*      */       
/*  275 */       for (int i = 0; i < threadsToCreate; i++) {
/*  276 */         this.inactiveThreadPool.add(createNewThread());
/*  277 */         threadsCreated++;
/*      */       } 
/*      */     } else {
/*      */       
/*  281 */       threadsCreated = threadsToCreate;
/*      */     } 
/*      */     
/*  284 */     if (Trace.isOn) {
/*  285 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "fillThreadPool()", 
/*      */           
/*  287 */           Integer.valueOf(threadsCreated));
/*      */     }
/*  289 */     return threadsCreated;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int emptyThreadPool() {
/*  309 */     if (Trace.isOn) {
/*  310 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "emptyThreadPool()");
/*      */     }
/*      */ 
/*      */     
/*  314 */     ThreadPoolWorker currentThread = null;
/*      */     
/*  316 */     Iterator<ThreadPoolWorker> iterator = this.threadPool.iterator();
/*  317 */     int count = 0;
/*  318 */     while (iterator.hasNext()) {
/*  319 */       currentThread = iterator.next();
/*  320 */       currentThread.closePolitely();
/*  321 */       count++;
/*      */     } 
/*  323 */     if (Trace.isOn) {
/*  324 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "emptyThreadPool()", 
/*      */           
/*  326 */           Integer.valueOf(count));
/*      */     }
/*  328 */     return count;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurrentThreadPoolSize() {
/*  337 */     int currentSize = this.threadPool.size();
/*  338 */     if (Trace.isOn) {
/*  339 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "getCurrentThreadPoolSize()", "getter", 
/*      */           
/*  341 */           Integer.valueOf(currentSize));
/*      */     }
/*  343 */     return currentSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void start() {
/*  352 */     if (Trace.isOn) {
/*  353 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "start()");
/*      */     }
/*      */ 
/*      */     
/*  357 */     this.workManagerThread.restart();
/*  358 */     if (Trace.isOn) {
/*  359 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "start()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void pause() {
/*  372 */     if (Trace.isOn) {
/*  373 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "pause()");
/*      */     }
/*      */ 
/*      */     
/*  377 */     this.workManagerThread.pause();
/*  378 */     if (Trace.isOn) {
/*  379 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "pause()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() {
/*  392 */     if (Trace.isOn) {
/*  393 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "close()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  398 */     this.workManagerThread.close();
/*      */ 
/*      */ 
/*      */     
/*  402 */     WorkQueueManager.setMaintainThreadPoolSize(false);
/*  403 */     emptyThreadPool();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  408 */     if (Trace.isOn) {
/*  409 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "close()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void end() {
/*  421 */     if (Trace.isOn) {
/*  422 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "end()");
/*      */     }
/*      */     
/*  425 */     close();
/*      */ 
/*      */     
/*  428 */     while (this.threadPool.size() > 0 || this.workManagerThread.isAlive()) {
/*      */       try {
/*  430 */         Thread.sleep(1000L);
/*      */       }
/*  432 */       catch (InterruptedException e) {
/*  433 */         if (Trace.isOn) {
/*  434 */           Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "end()", e);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  440 */     if (Trace.isOn) {
/*  441 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "end()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ThreadPoolWorker getWorkerThread() {
/*  457 */     ThreadPoolWorker thisThread = null;
/*      */     
/*  459 */     thisThread = getInactiveWorkerThread();
/*  460 */     if (Trace.isOn && thisThread == null) {
/*  461 */       Trace.traceData(this, "getWorkerThread()", "No inactive thread was available", null);
/*      */     }
/*  463 */     int maxPoolSize = WorkQueueManager.getMaxThreadPoolSize();
/*      */     
/*  465 */     if (thisThread == null && (maxPoolSize == -1 || this.threadPool.size() < maxPoolSize)) {
/*  466 */       thisThread = createNewThread();
/*      */     }
/*      */     
/*  469 */     if (Trace.isOn) {
/*  470 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "getWorkerThread()", "getter", thisThread);
/*      */     }
/*      */ 
/*      */     
/*  474 */     return thisThread;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ThreadPoolWorker getInactiveWorkerThread() {
/*  484 */     ThreadPoolWorker thisThread = null;
/*      */ 
/*      */     
/*  487 */     int inactiveThreadPoolSize = this.inactiveThreadPool.size();
/*  488 */     if (inactiveThreadPoolSize >= 1) {
/*  489 */       thisThread = this.inactiveThreadPool.firstElement();
/*      */     
/*      */     }
/*  492 */     else if (Trace.isOn) {
/*  493 */       Trace.traceData(this, "getInactiveWorkerThread()", "inactiveThreadPoolSize", inactiveThreadPoolSize + "");
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  498 */     if (Trace.isOn) {
/*  499 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "getInactiveWorkerThread()", "getter", thisThread);
/*      */     }
/*      */ 
/*      */     
/*  503 */     return thisThread;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ThreadPoolWorker createNewThread() {
/*  513 */     if (Trace.isOn) {
/*  514 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "createNewThread()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  519 */     this.threadCount++;
/*  520 */     String newThreadName = "JMSCCThreadPoolWorker-" + this.threadCount;
/*  521 */     if (Trace.isOn) {
/*  522 */       Trace.traceData(this, "createNewThread()", "Creating new ThreadPoolWorker", newThreadName);
/*      */     }
/*  524 */     ThreadPoolWorker thisThread = new ThreadPoolWorker(newThreadName, this.poolGroup, this.threadPool, this);
/*      */     
/*  526 */     thisThread.setDaemon(this.useDeamonThreads);
/*  527 */     if (Trace.isOn) {
/*  528 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "createNewThread()", thisThread);
/*      */     }
/*      */ 
/*      */     
/*  532 */     return thisThread;
/*      */   }
/*      */ 
/*      */   
/*      */   void wakeManagerThread() {
/*  537 */     if (Trace.isOn) {
/*  538 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "wakeManagerThread()");
/*      */     }
/*      */ 
/*      */     
/*  542 */     this.workManagerThread.wake();
/*  543 */     if (Trace.isOn) {
/*  544 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation", "wakeManagerThread()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class WorkQueueManagerThread
/*      */     extends Thread
/*      */   {
/*      */     static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/workqueue/WorkQueueManagerImplementation.java";
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int STATE_RUNNING = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int STATE_PAUSED = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int STATE_ENDING = 3;
/*      */ 
/*      */ 
/*      */     
/*  574 */     private int state = 1;
/*      */     
/*  576 */     private final Object threadNotifierLock = new Object();
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean workWaiting = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WorkQueueManagerThread(String name) {
/*  586 */       super(name);
/*  587 */       if (Trace.isOn) {
/*  588 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "<init>(String)", new Object[] { name });
/*      */       }
/*      */ 
/*      */       
/*  592 */       if (Trace.isOn) {
/*  593 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "<init>(String)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setWorkWaiting() {
/*  601 */       if (Trace.isOn) {
/*  602 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "setWorkWaiting()");
/*      */       }
/*      */ 
/*      */       
/*  606 */       synchronized (this.threadNotifierLock) {
/*  607 */         this.workWaiting = true;
/*  608 */         wake();
/*      */       } 
/*  610 */       if (Trace.isOn) {
/*  611 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "setWorkWaiting()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void wake() {
/*  619 */       if (Trace.isOn) {
/*  620 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "wake()");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  626 */       synchronized (this.threadNotifierLock) {
/*  627 */         if (Trace.isOn) {
/*  628 */           Trace.traceData(this, "wake()", "Notifying threadNotifierLock", null);
/*      */         }
/*  630 */         this.threadNotifierLock.notify();
/*  631 */         if (Trace.isOn) {
/*  632 */           Trace.traceData(this, "wake()", "threadNotifierLock notified", null);
/*      */         }
/*      */       } 
/*  635 */       if (Trace.isOn) {
/*  636 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "wake()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean waitForNotification() {
/*  643 */       if (Trace.isOn) {
/*  644 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "waitForNotification()");
/*      */       }
/*      */ 
/*      */       
/*  648 */       boolean interrupted = waitForNotification(-1L);
/*  649 */       if (Trace.isOn) {
/*  650 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "waitForNotification()", 
/*      */             
/*  652 */             Boolean.valueOf(interrupted));
/*      */       }
/*  654 */       return interrupted;
/*      */     }
/*      */ 
/*      */     
/*      */     private boolean waitForNotification(long waitTimeout) {
/*  659 */       if (Trace.isOn)
/*  660 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "waitForNotification(long)", new Object[] {
/*      */               
/*  662 */               Long.valueOf(waitTimeout)
/*      */             }); 
/*  664 */       boolean interrupted = false;
/*      */       try {
/*  666 */         if (Trace.isOn) {
/*  667 */           Trace.traceData(this, "waitForNotification(long)", "synchronizing on threadNotifierLock", null);
/*      */         }
/*      */         
/*  670 */         synchronized (this.threadNotifierLock) {
/*      */           
/*  672 */           if (!this.workWaiting) {
/*  673 */             if (Trace.isOn) {
/*  674 */               Trace.traceData(this, "waitForNotification(long)", "workWaiting=false. Waiting for " + waitTimeout, null);
/*      */             }
/*  676 */             if (waitTimeout == -1L) {
/*  677 */               while (!this.workWaiting) {
/*  678 */                 this.threadNotifierLock.wait();
/*      */               }
/*      */             } else {
/*      */               
/*  682 */               this.threadNotifierLock.wait(waitTimeout);
/*      */             } 
/*      */           } 
/*      */         } 
/*  686 */         synchronized (this.threadNotifierLock) {
/*  687 */           if (Trace.isOn) {
/*  688 */             Trace.traceData(this, "waitForNotification(long)", "setting workWaiting=false.", null);
/*      */           }
/*  690 */           this.workWaiting = false;
/*      */         }
/*      */       
/*  693 */       } catch (InterruptedException e) {
/*  694 */         if (Trace.isOn) {
/*  695 */           Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "waitForNotification(long)", e);
/*      */         }
/*      */ 
/*      */         
/*  699 */         interrupted = true;
/*      */       } 
/*  701 */       if (Trace.isOn) {
/*  702 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "waitForNotification(long)", 
/*      */             
/*  704 */             Boolean.valueOf(interrupted));
/*      */       }
/*  706 */       return interrupted;
/*      */     }
/*      */ 
/*      */     
/*      */     public void close() {
/*  711 */       if (Trace.isOn) {
/*  712 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "close()");
/*      */       }
/*      */       
/*  715 */       this.state = 3;
/*  716 */       wake();
/*  717 */       if (Trace.isOn) {
/*  718 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "close()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void pause() {
/*  726 */       if (Trace.isOn) {
/*  727 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "pause()");
/*      */       }
/*      */       
/*  730 */       this.state = 2;
/*  731 */       if (Trace.isOn) {
/*  732 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "pause()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void restart() {
/*  740 */       if (Trace.isOn) {
/*  741 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "restart()");
/*      */       }
/*      */       
/*  744 */       this.state = 1;
/*  745 */       wake();
/*  746 */       if (Trace.isOn) {
/*  747 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "restart()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/*  780 */       if (Trace.isOn) {
/*  781 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "run()");
/*      */       }
/*      */ 
/*      */       
/*  785 */       WorkQueueManagerImplementation.ThreadPoolWorker newThread = null;
/*      */ 
/*      */       
/*  788 */       WorkQueueItem currentItem = null;
/*      */       
/*  790 */       if (Trace.isOn) {
/*  791 */         Trace.traceData(this, "run()", "Current state", Integer.valueOf(this.state));
/*      */       }
/*      */       
/*  794 */       while (this.state != 3) {
/*      */ 
/*      */         
/*  797 */         if (Trace.isOn) {
/*  798 */           Trace.traceData(this, "run()", "Synchronizing on threadNotifierLock", this.threadNotifierLock);
/*      */         }
/*  800 */         synchronized (this.threadNotifierLock) {
/*  801 */           if (Trace.isOn) {
/*  802 */             Trace.traceData(this, "run()", "Got sync lock", this.threadNotifierLock);
/*      */           }
/*      */           
/*  805 */           if (this.state == 2) {
/*  806 */             if (Trace.isOn) {
/*  807 */               Trace.traceData(this, "in pause state. Pausing", null);
/*      */             }
/*      */ 
/*      */             
/*  811 */             waitForNotification();
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  816 */         if (!WorkQueueManagerImplementation.this.highPriorityWorkQueue.isEmpty()) {
/*      */           
/*  818 */           if (Trace.isOn) {
/*  819 */             Trace.traceData(this, "run()", "", null);
/*      */           }
/*  821 */           synchronized (WorkQueueManagerImplementation.this.highPriorityWorkQueue) {
/*  822 */             currentItem = WorkQueueManagerImplementation.this.highPriorityWorkQueue.removeFirst();
/*      */           } 
/*      */ 
/*      */           
/*  826 */           if (Trace.isOn) {
/*  827 */             Trace.traceData(this, "run()", "getting worker Thread from pool", null);
/*      */           }
/*  829 */           newThread = WorkQueueManagerImplementation.this.getWorkerThread();
/*      */ 
/*      */ 
/*      */           
/*  833 */           if (newThread == null) {
/*  834 */             if (Trace.isOn) {
/*  835 */               Trace.traceData(this, "run()", "Thread was null, creating new Worker Thread", null);
/*      */             }
/*  837 */             newThread = WorkQueueManagerImplementation.this.createNewThread();
/*      */           } 
/*      */ 
/*      */           
/*  841 */           if (Trace.isOn) {
/*  842 */             Trace.traceData(this, "run()", "Setting work item", null);
/*      */           }
/*  844 */           newThread.setWorkQueueItem(currentItem);
/*  845 */           if (Trace.isOn) {
/*  846 */             Trace.traceData(this, "run()", "Starting new Thread", newThread);
/*      */           }
/*  848 */           newThread.start();
/*      */ 
/*      */           
/*  851 */           if (Trace.isOn) {
/*  852 */             Trace.traceData(this, "run()", "Setting newThread reference to null", null);
/*      */           }
/*  854 */           newThread = null;
/*      */           continue;
/*      */         } 
/*  857 */         if (Trace.isOn) {
/*  858 */           Trace.traceData(this, "run()", "No high priority work to do", null);
/*      */         }
/*  860 */         boolean proceedable = true;
/*      */         
/*  862 */         if (!WorkQueueManagerImplementation.this.workQueue.isEmpty()) {
/*  863 */           if (Trace.isOn) {
/*  864 */             Trace.traceData(this, "run()", "Normal priority Work on work queue. Getting workerThread", null);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  869 */           newThread = WorkQueueManagerImplementation.this.getWorkerThread();
/*      */           
/*  871 */           if (newThread != null)
/*      */           {
/*  873 */             if (Trace.isOn) {
/*  874 */               Trace.traceData(this, "run()", "Got thread. Synchronizing", null);
/*      */             }
/*  876 */             synchronized (WorkQueueManagerImplementation.this.workQueue) {
/*  877 */               if (Trace.isOn) {
/*  878 */                 Trace.traceData(this, "run()", "Getting item from queue", null);
/*      */               }
/*  880 */               currentItem = WorkQueueManagerImplementation.this.workQueue.removeFirst();
/*  881 */               if (Trace.isOn) {
/*  882 */                 Trace.traceData(this, "run()", "Got workQueueItem", currentItem);
/*      */               }
/*      */             } 
/*      */ 
/*      */             
/*  887 */             if (Trace.isOn) {
/*  888 */               Trace.traceData(this, "run()", "Setting item in thread", null);
/*      */             }
/*  890 */             newThread.setWorkQueueItem(currentItem);
/*  891 */             if (Trace.isOn) {
/*  892 */               Trace.traceData(this, "run()", "Sterting workerThread", null);
/*      */             }
/*  894 */             newThread.start();
/*      */ 
/*      */             
/*  897 */             newThread = null;
/*  898 */             proceedable = true;
/*      */           }
/*      */           else
/*      */           {
/*  902 */             proceedable = false;
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  908 */           proceedable = false;
/*      */         } 
/*      */         
/*  911 */         if (!proceedable) {
/*      */ 
/*      */           
/*  914 */           if (Trace.isOn) {
/*  915 */             Trace.traceData(this, "run()", "No work to be done. Waiting for further notification", null);
/*      */           }
/*      */           
/*  918 */           waitForNotification();
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  924 */       if (Trace.isOn) {
/*  925 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerThread", "run()");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   class ThreadPoolWorker
/*      */     extends Thread
/*      */   {
/*      */     static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/workqueue/WorkQueueManagerImplementation.java";
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean inactive = true;
/*      */ 
/*      */     
/*      */     private WorkQueueItem workItem;
/*      */ 
/*      */     
/*  946 */     private Object notifier = null;
/*      */     
/*      */     private Vector<ThreadPoolWorker> parentThreadPool;
/*      */     
/*      */     private boolean working = false;
/*      */     
/*  952 */     private WorkQueueManagerImplementation manager = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ThreadPoolWorker(String threadName, ThreadGroup poolGroup, Vector<ThreadPoolWorker> threadPool, WorkQueueManagerImplementation parentManager) {
/*  967 */       super(poolGroup, threadName);
/*  968 */       if (Trace.isOn) {
/*  969 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "<init>(String,ThreadGroup,Vector<ThreadPoolWorker>,WorkQueueManagerImplementation)", new Object[] { threadName, poolGroup, threadPool, parentManager });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  974 */       this.manager = parentManager;
/*  975 */       this.parentThreadPool = threadPool;
/*  976 */       this.notifier = new Object();
/*  977 */       init();
/*  978 */       if (Trace.isOn) {
/*  979 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "<init>(String,ThreadGroup,Vector<ThreadPoolWorker>,WorkQueueManagerImplementation)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void init() {
/*  990 */       if (Trace.isOn) {
/*  991 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "init()");
/*      */       }
/*      */       
/*  994 */       this.parentThreadPool.add(this);
/*  995 */       if (Trace.isOn) {
/*  996 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "init()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean waitForNotification() {
/* 1004 */       if (Trace.isOn) {
/* 1005 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "waitForNotification()");
/*      */       }
/*      */       
/* 1008 */       boolean interrupted = waitForNotification(-1L);
/* 1009 */       if (Trace.isOn) {
/* 1010 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "waitForNotification()", 
/* 1011 */             Boolean.valueOf(interrupted));
/*      */       }
/* 1013 */       return interrupted;
/*      */     }
/*      */ 
/*      */     
/*      */     private boolean waitForNotification(long waitTimeout) {
/* 1018 */       if (Trace.isOn)
/* 1019 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "waitForNotification(long)", new Object[] {
/* 1020 */               Long.valueOf(waitTimeout)
/*      */             }); 
/* 1022 */       boolean interrupted = false;
/*      */       try {
/* 1024 */         synchronized (this.notifier) {
/*      */ 
/*      */           
/* 1027 */           if (!this.inactive) {
/* 1028 */             if (Trace.isOn) {
/* 1029 */               Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "waitForNotification(long)", 
/*      */                   
/* 1031 */                   Boolean.valueOf(true), 1);
/*      */             }
/* 1033 */             return true;
/*      */           } 
/*      */ 
/*      */           
/* 1037 */           if (waitTimeout == 0L) {
/* 1038 */             if (Trace.isOn) {
/* 1039 */               Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "waitForNotification(long)", 
/*      */                   
/* 1041 */                   Boolean.valueOf(false), 2);
/*      */             }
/* 1043 */             return false;
/*      */           } 
/*      */           
/* 1046 */           if (waitTimeout == -1L) {
/* 1047 */             while (this.inactive) {
/* 1048 */               this.notifier.wait();
/*      */             }
/*      */           } else {
/*      */             
/* 1052 */             this.notifier.wait(waitTimeout);
/*      */           }
/*      */         
/*      */         } 
/* 1056 */       } catch (InterruptedException e) {
/* 1057 */         if (Trace.isOn) {
/* 1058 */           Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "waitForNotification(long)", e);
/*      */         }
/*      */ 
/*      */         
/* 1062 */         interrupted = true;
/*      */       } 
/* 1064 */       if (Trace.isOn) {
/* 1065 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "waitForNotification(long)", 
/* 1066 */             Boolean.valueOf(interrupted), 3);
/*      */       }
/* 1068 */       return interrupted;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void wake() {
/* 1076 */       if (Trace.isOn) {
/* 1077 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "wake()");
/*      */       }
/*      */       
/* 1080 */       synchronized (this.notifier) {
/*      */         
/* 1082 */         this.inactive = false;
/* 1083 */         this.notifier.notifyAll();
/*      */       } 
/* 1085 */       if (Trace.isOn) {
/* 1086 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "wake()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void closePolitely() {
/* 1100 */       if (Trace.isOn) {
/* 1101 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "closePolitely()");
/*      */       }
/*      */       
/* 1104 */       if (this.inactive) {
/* 1105 */         WorkQueueManagerImplementation.this.inactiveThreadPool.remove(this);
/*      */       }
/*      */ 
/*      */       
/* 1109 */       this.working = false;
/*      */ 
/*      */       
/* 1112 */       wake();
/* 1113 */       if (Trace.isOn) {
/* 1114 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "closePolitely()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setWorkQueueItem(final WorkQueueItem newItem) {
/* 1127 */       if (Trace.isOn) {
/* 1128 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "setWorkQueueItem(final WorkQueueItem)", new Object[] { newItem });
/*      */       }
/*      */       
/* 1131 */       if (this.workItem == null) {
/* 1132 */         if (Trace.isOn) {
/* 1133 */           Trace.traceData(this, "setWorkQueueItem(WorkQueueItem)", "Synchronizing on notifier", null);
/*      */         }
/*      */         
/* 1136 */         synchronized (this.notifier) {
/* 1137 */           if (Trace.isOn) {
/* 1138 */             Trace.traceData(this, "setWorkQueueItem(WorkQueueItem)", "Synchronized", null);
/*      */           }
/*      */           
/* 1141 */           this.workItem = newItem;
/* 1142 */           this.inactive = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1148 */           AccessController.doPrivileged(new PrivilegedAction()
/*      */               {
/*      */                 public Object run()
/*      */                 {
/* 1152 */                   if (Trace.isOn) {
/* 1153 */                     Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "run()");
/*      */                   }
/*      */                   
/* 1156 */                   WorkQueueManagerImplementation.ThreadPoolWorker.this.setContextClassLoader(newItem.getClass().getClassLoader());
/* 1157 */                   if (Trace.isOn) {
/* 1158 */                     Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.null", "run()", null);
/*      */                   }
/*      */                   
/* 1161 */                   return null;
/*      */                 }
/*      */               });
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1173 */       if (Trace.isOn) {
/* 1174 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "setWorkQueueItem(final WorkQueueItem)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void start() {
/* 1189 */       if (Trace.isOn) {
/* 1190 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "start()");
/*      */       }
/*      */       
/* 1193 */       if (!this.working) {
/* 1194 */         if (Trace.isOn) {
/* 1195 */           Trace.traceData(this, "start()", "not in working state, starting", null);
/*      */         }
/* 1197 */         this.working = true;
/* 1198 */         super.start();
/*      */       } else {
/*      */         
/* 1201 */         if (Trace.isOn) {
/* 1202 */           Trace.traceData(this, "start()", "already in working state, waking", null);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1207 */         wake();
/*      */       } 
/*      */       
/* 1210 */       if (Trace.isOn) {
/* 1211 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "start()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/* 1226 */       if (Trace.isOn) {
/* 1227 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "run()");
/*      */       }
/*      */ 
/*      */       
/* 1231 */       long timeout = WorkQueueManager.getPoolInactiveTimeout();
/*      */       
/* 1233 */       while (this.working) {
/* 1234 */         this.inactive = false;
/*      */         
/* 1236 */         if (this.workItem != null) {
/* 1237 */           if (Trace.isOn) {
/* 1238 */             Trace.traceData(this, "run()", "running WorkQueueItem...", this.workItem);
/*      */           }
/* 1240 */           WorkQueueManager.runWorkQueueItem(this.workItem);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1245 */         if (!this.working) {
/* 1246 */           if (Trace.isOn) {
/* 1247 */             Trace.data(this, "run()", "Asked to close while working", null);
/*      */           }
/* 1249 */           this.workItem = null;
/* 1250 */           if (Trace.isOn) {
/* 1251 */             Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "run()", 1);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1262 */         if (WorkQueueManagerImplementation.this.threadPool.size() <= WorkQueueManager.getMaxThreadPoolSize() || !WorkQueueManagerImplementation.this.highPriorityWorkQueue.isEmpty()) {
/* 1263 */           if (Trace.isOn) {
/* 1264 */             Trace.data(this, "run()", "Returning thread to inactive pool", null);
/*      */           }
/*      */           
/* 1267 */           this.workItem = null;
/* 1268 */           this.inactive = true;
/*      */ 
/*      */ 
/*      */           
/* 1272 */           if (timeout != 0L) {
/* 1273 */             if (Trace.isOn) {
/* 1274 */               Trace.traceData(this, "run()", "returning thread to pool", null);
/*      */             }
/* 1276 */             WorkQueueManagerImplementation.this.inactiveThreadPool.add(this);
/* 1277 */             if (Trace.isOn) {
/* 1278 */               Trace.traceData(this, "run()", "Waking managerThread", null);
/*      */             }
/* 1280 */             this.manager.wakeManagerThread();
/*      */           } 
/*      */           
/* 1283 */           if (WorkQueueManager.getMaintainThreadPoolSize()) {
/* 1284 */             if (Trace.isOn) {
/* 1285 */               Trace.traceData(this, "run()", "Maintaining pool size. Waiting indefinitely for notification", null);
/*      */             }
/*      */ 
/*      */             
/* 1289 */             waitForNotification();
/*      */             
/*      */             continue;
/*      */           } 
/* 1293 */           if (Trace.isOn) {
/* 1294 */             Trace.traceData(this, "run()", "Waiting for interrupt or timeout", null);
/*      */           }
/* 1296 */           if (timeout == 0L || !waitForNotification(WorkQueueManager.getPoolInactiveTimeout())) {
/* 1297 */             if (Trace.isOn) {
/* 1298 */               Trace.traceData(this, "run()", "Timed out in pool, closing thread", null);
/*      */             }
/*      */ 
/*      */             
/* 1302 */             WorkQueueManagerImplementation.this.inactiveThreadPool.remove(this);
/* 1303 */             if (this.workItem == null) {
/* 1304 */               this.parentThreadPool.remove(this);
/* 1305 */               this.working = false;
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*      */           continue;
/*      */         } 
/*      */ 
/*      */         
/* 1315 */         this.workItem = null;
/* 1316 */         this.working = false;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1323 */       if (Trace.isOn) {
/* 1324 */         Trace.traceData(this, "run()", "Removing thread from pool", null);
/*      */       }
/* 1326 */       this.parentThreadPool.remove(this);
/*      */ 
/*      */       
/* 1329 */       if (Trace.isOn) {
/* 1330 */         Trace.traceData(this, "run()", "waking managerThread", null);
/*      */       }
/* 1332 */       this.manager.wakeManagerThread();
/*      */       
/* 1334 */       if (Trace.isOn) {
/* 1335 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "run()", 2);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isInactive() {
/* 1343 */       if (Trace.isOn) {
/* 1344 */         Trace.data(this, "com.ibm.msg.client.commonservices.j2se.workqueue.ThreadPoolWorker", "isInactive()", "getter", 
/* 1345 */             Boolean.valueOf(this.inactive));
/*      */       }
/* 1347 */       return this.inactive;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\workqueue\WorkQueueManagerImplementation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */