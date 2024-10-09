/*     */ package com.ibm.msg.client.commonservices.workqueue;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.CSIException;
/*     */ import com.ibm.msg.client.commonservices.CSIListener;
/*     */ import com.ibm.msg.client.commonservices.CommonServices;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.provider.workqueue.CSPWorkQueueManager;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ public final class WorkQueueManager
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/workqueue/WorkQueueManager.java";
/*     */   public static final String maxThreadPoolSizeProperty = "jmscc.workqueue.poolSize";
/*     */   public static final int maxThreadPoolSizeProperty_default = -1;
/*     */   public static final String maintainThreadPoolSizeProperty = "jmscc.workqueue.maintainPoolSize";
/*     */   public static final boolean maintainThreadPoolSizeProperty_default = false;
/*     */   public static final String threadPoolInactiveTimeoutProperty = "jmscc.workqueue.poolTimeout";
/*     */   public static final long threadPoolInactiveTimeoutProperty_default = 0L;
/*     */   public static final int PRIORITY_NORMAL = 0;
/*     */   public static final int PRIORITY_HIGH = 1;
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/workqueue/WorkQueueManager.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   static int maxThreadPoolSize = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean maintainThreadPoolSize = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   static long threadPoolInactiveTimeout = 0L;
/*     */ 
/*     */   
/*     */   static CSPWorkQueueManager manager;
/*     */ 
/*     */   
/*     */   static boolean initialized = false;
/*     */   
/*     */   static boolean listening = false;
/*     */ 
/*     */   
/*     */   static {
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.entry("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "static()");
/*     */     }
/*     */     
/*     */     try {
/* 110 */       initialize();
/*     */     }
/* 112 */     catch (CSIException csie) {
/* 113 */       if (Trace.isOn) {
/* 114 */         Trace.catchBlock("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "static()", (Throwable)csie);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.exit("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "static()");
/*     */     }
/*     */   }
/*     */   
/*     */   private WorkQueueManager() {
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "<init>()");
/*     */     }
/*     */ 
/*     */     
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "<init>()");
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
/*     */   public static void start() throws CSIException {
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.entry("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "start()");
/*     */     }
/* 149 */     manager.start();
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "start()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void pause() throws CSIException {
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.entry("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "pause()");
/*     */     }
/* 165 */     manager.pause();
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.exit("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "pause()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void close() throws CSIException {
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.entry("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "close()");
/*     */     }
/* 179 */     manager.close();
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.exit("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "close()");
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
/*     */   public static WorkQueueToken enqueue(Runnable newTask) throws CSIException {
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.entry("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "enqueue(Runnable)", new Object[] { newTask });
/*     */     }
/*     */     
/* 200 */     WorkQueueToken newToken = enqueue(newTask, 0, false);
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.exit("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "enqueue(Runnable)", newToken);
/*     */     }
/*     */     
/* 205 */     return newToken;
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
/*     */   public static WorkQueueToken enqueue(Runnable newTask, int priority, boolean repeating) throws CSIException {
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.entry("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "enqueue(Runnable,int,boolean)", new Object[] { newTask, 
/* 226 */             Integer.valueOf(priority), 
/* 227 */             Boolean.valueOf(repeating) });
/*     */     }
/* 229 */     WorkQueueItem newItem = new SimpleWorkQueueItem(newTask);
/* 230 */     newItem.setRepeats(repeating);
/* 231 */     WorkQueueToken newToken = new WorkQueueToken(newItem);
/* 232 */     manager.enqueueItem(newItem, priority);
/*     */     
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "enqueue(Runnable,int,boolean)", newToken);
/*     */     }
/*     */     
/* 238 */     return newToken;
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
/*     */   public static WorkQueueToken enqueue(WorkQueueItem newItem) throws CSIException {
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.entry("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "enqueue(WorkQueueItem)", new Object[] { newItem });
/*     */     }
/*     */     
/* 259 */     WorkQueueToken newToken = enqueue(newItem, 0, false);
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.exit("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "enqueue(WorkQueueItem)", newToken);
/*     */     }
/*     */     
/* 264 */     return newToken;
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
/*     */   public static WorkQueueToken enqueue(WorkQueueItem newItem, int priority, boolean repeating) throws CSIException {
/* 284 */     if (Trace.isOn) {
/* 285 */       Trace.entry("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "enqueue(WorkQueueItem,int,boolean)", new Object[] { newItem, 
/* 286 */             Integer.valueOf(priority), 
/* 287 */             Boolean.valueOf(repeating) });
/*     */     }
/* 289 */     WorkQueueToken newToken = new WorkQueueToken(newItem);
/* 290 */     newItem.setRepeats(repeating);
/* 291 */     manager.enqueueItem(newItem, priority);
/*     */     
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.exit("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "enqueue(WorkQueueItem,int,boolean)", newToken);
/*     */     }
/*     */     
/* 297 */     return newToken;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void runWorkQueueItem(WorkQueueItem item) {
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.entry("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "runWorkQueueItem(WorkQueueItem)", new Object[] { item });
/*     */     }
/*     */     
/* 312 */     item.run();
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.exit("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "runWorkQueueItem(WorkQueueItem)");
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
/*     */   public static void setMaxThreadPoolSize(int newMaxPoolSize) {
/* 334 */     if (Trace.isOn) {
/* 335 */       Trace.data("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "setMaxThreadPoolSize(int)", "setter", 
/* 336 */           Integer.valueOf(newMaxPoolSize));
/*     */     }
/* 338 */     maxThreadPoolSize = (newMaxPoolSize < -1) ? -1 : newMaxPoolSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getMaxThreadPoolSize() {
/* 345 */     if (Trace.isOn) {
/* 346 */       Trace.data("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "getMaxThreadPoolSize()", "getter", 
/* 347 */           Integer.valueOf(maxThreadPoolSize));
/*     */     }
/* 349 */     return maxThreadPoolSize;
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
/*     */   public static int getCurrentThreadPoolSize() throws CSIException {
/* 363 */     int traceRet1 = manager.getCurrentThreadPoolSize();
/* 364 */     if (Trace.isOn) {
/* 365 */       Trace.data("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "getCurrentThreadPoolSize()", "getter", 
/* 366 */           Integer.valueOf(traceRet1));
/*     */     }
/* 368 */     return traceRet1;
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
/*     */   public static void setPoolInactiveTimeout(long newTimeout) {
/* 383 */     if (Trace.isOn) {
/* 384 */       Trace.data("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "setPoolInactiveTimeout(long)", "setter", 
/* 385 */           Long.valueOf(newTimeout));
/*     */     }
/*     */     
/* 388 */     threadPoolInactiveTimeout = (newTimeout < 0L) ? 0L : newTimeout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getPoolInactiveTimeout() {
/* 397 */     if (Trace.isOn) {
/* 398 */       Trace.data("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "getPoolInactiveTimeout()", "getter", 
/* 399 */           Long.valueOf(threadPoolInactiveTimeout));
/*     */     }
/* 401 */     return threadPoolInactiveTimeout;
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
/*     */   public static void setMaintainThreadPoolSize(boolean maintain) {
/* 416 */     if (Trace.isOn) {
/* 417 */       Trace.data("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "setMaintainThreadPoolSize(boolean)", "setter", 
/* 418 */           Boolean.valueOf(maintain));
/*     */     }
/* 420 */     maintainThreadPoolSize = maintain;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean getMaintainThreadPoolSize() {
/* 427 */     if (Trace.isOn) {
/* 428 */       Trace.data("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "getMaintainThreadPoolSize()", "getter", 
/* 429 */           Boolean.valueOf(maintainThreadPoolSize));
/*     */     }
/* 431 */     return maintainThreadPoolSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerProperties() {
/* 438 */     if (Trace.isOn) {
/* 439 */       Trace.entry("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "registerProperties()");
/*     */     }
/*     */ 
/*     */     
/* 443 */     PropertyStore.register("jmscc.workqueue.poolSize", -1L, Long.valueOf(-1L), 
/* 444 */         Long.valueOf(1000000L));
/* 445 */     PropertyStore.register("jmscc.workqueue.maintainPoolSize", false);
/* 446 */     PropertyStore.register("jmscc.workqueue.poolTimeout", 0L, 
/* 447 */         Long.valueOf(0L), null);
/* 448 */     if (Trace.isOn) {
/* 449 */       Trace.exit("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "registerProperties()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void queryProperties() {
/* 456 */     if (Trace.isOn) {
/* 457 */       Trace.entry("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "queryProperties()");
/*     */     }
/*     */     
/* 460 */     maxThreadPoolSize = PropertyStore.getLongPropertyObject("jmscc.workqueue.poolSize").intValue();
/* 461 */     maintainThreadPoolSize = PropertyStore.getBooleanPropertyObject("jmscc.workqueue.maintainPoolSize").booleanValue();
/* 462 */     threadPoolInactiveTimeout = PropertyStore.getLongPropertyObject("jmscc.workqueue.poolTimeout").longValue();
/*     */     
/* 464 */     if (Trace.isOn) {
/* 465 */       Trace.exit("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "queryProperties()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initialize() throws CSIException {
/* 475 */     if (Trace.isOn) {
/* 476 */       Trace.entry("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "initialize()");
/*     */     }
/*     */ 
/*     */     
/* 480 */     if (!initialized) {
/*     */       
/*     */       try {
/* 483 */         manager = CommonServices.getWorkQueueManager();
/*     */ 
/*     */         
/* 486 */         registerProperties();
/* 487 */         queryProperties();
/* 488 */         initialized = true;
/*     */ 
/*     */         
/* 491 */         if (maintainThreadPoolSize) {
/* 492 */           int i = manager.fillThreadPool();
/* 493 */           if (Trace.isOn) {
/* 494 */             Trace.traceData("com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManager", "static initializer", "Created " + i + " new threads in thread pool", null);
/*     */           }
/*     */         }
/*     */       
/*     */       }
/* 499 */       catch (CSIException csie) {
/* 500 */         if (Trace.isOn) {
/* 501 */           Trace.catchBlock("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "initialize()", (Throwable)csie);
/*     */         }
/*     */ 
/*     */         
/* 505 */         if (!listening) {
/*     */ 
/*     */           
/* 508 */           manager = new PIWorkQueueManager();
/*     */ 
/*     */ 
/*     */           
/* 512 */           CSIListener listener = new CSIListener()
/*     */             {
/*     */               public void onCSIInitialize()
/*     */               {
/* 516 */                 if (Trace.isOn) {
/* 517 */                   Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "onCSIInitialize()");
/*     */                 }
/*     */                 
/*     */                 try {
/* 521 */                   WorkQueueManager.initialize();
/* 522 */                   WorkQueueManager.listening = false;
/* 523 */                   CommonServices.removeCSIListener(this);
/*     */                 }
/* 525 */                 catch (CSIException csie2) {
/* 526 */                   if (Trace.isOn) {
/* 527 */                     Trace.catchBlock(this, "com.ibm.msg.client.commonservices.workqueue.null", "onCSIInitialize()", (Throwable)csie2);
/*     */                   }
/*     */ 
/*     */                   
/* 531 */                   HashMap<String, CSIException> hash = new HashMap<>();
/* 532 */                   hash.put("Exception", csie2);
/* 533 */                   Trace.ffst(this, "onCSIInitialize", "Failed to initialize CSI from WorkQueueManager listener", hash, CSIException.class);
/*     */                 } 
/*     */ 
/*     */                 
/* 537 */                 if (Trace.isOn) {
/* 538 */                   Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.null", "onCSIInitialize()");
/*     */                 }
/*     */               }
/*     */             };
/*     */ 
/*     */ 
/*     */           
/* 545 */           CommonServices.addCSIListener(listener);
/* 546 */           listening = true;
/*     */         } 
/* 548 */         if (Trace.isOn) {
/* 549 */           Trace.throwing("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "initialize()", (Throwable)csie);
/*     */         }
/*     */         
/* 552 */         throw csie;
/*     */       } 
/*     */     }
/* 555 */     if (Trace.isOn)
/* 556 */       Trace.exit("com.ibm.msg.client.commonservices.workqueue.WorkQueueManager", "initialize()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\workqueue\WorkQueueManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */