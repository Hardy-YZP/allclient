/*      */ package com.ibm.mq.jmqi.local;
/*      */ 
/*      */ import com.ibm.mq.constants.CMQC;
/*      */ import com.ibm.mq.constants.QmgrAdvancedCapability;
/*      */ import com.ibm.mq.constants.QmgrSplCapability;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.JmqiThreadPool;
/*      */ import com.ibm.mq.jmqi.JmqiWorkerThread;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Phconn;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.mq.jmqi.internal.QueueManagerInfo;
/*      */ import com.ibm.mq.jmqi.local.internal.LocalProxyConsumer;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*      */ import com.ibm.mq.jmqi.system.JmqiRunnable;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.RXPB;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.util.HashMap;
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
/*      */ public class LocalHconn
/*      */   extends JmqiObject
/*      */   implements Hconn
/*      */ {
/*      */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/LocalHconn.java";
/*      */   private static JmqiThreadPool threadPool;
/*      */   private int value;
/*      */   private boolean useWorkerThread;
/*      */   private JmqiWorkerThread workerThread;
/*      */   
/*      */   static {
/*   55 */     if (Trace.isOn) {
/*   56 */       Trace.data("com.ibm.mq.jmqi.local.LocalHconn", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/LocalHconn.java");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   84 */   private Object sharedHConnLock = new SharedHConnLock();
/*      */ 
/*      */ 
/*      */   
/*   88 */   private int callsInProgress = 0;
/*      */ 
/*      */ 
/*      */   
/*   92 */   private int shareOption = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JmqiMQ mq;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private LocalHconn parent;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private QueueManagerInfo info;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] qmNameBytes;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private RXPB rxpb;
/*      */ 
/*      */ 
/*      */   
/*      */   private JmqiConnectOptions jmqiConnectOptions;
/*      */ 
/*      */ 
/*      */   
/*  125 */   private byte[] connectionId = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   byte[] connTag;
/*      */ 
/*      */ 
/*      */   
/*  134 */   private String connectionIdString = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  140 */   private String originalQueueManagerName = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private LocalProxyConsumer proxyConsumer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Object connectionArea;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean XAPrepared = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  166 */   private IsInMQGetLock isInMQGETLock = new IsInMQGetLock();
/*      */   
/*      */   private static class IsInMQGetLock
/*      */   {
/*      */     private IsInMQGetLock() {}
/*      */   }
/*      */   
/*  173 */   private int MQGETinProgressCount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMQGETinProgressCount() {
/*  179 */     synchronized (this.isInMQGETLock) {
/*  180 */       if (Trace.isOn) {
/*  181 */         Trace.data(this, "MQGETinProgressCount is set to: ", 
/*  182 */             Integer.toString(this.MQGETinProgressCount));
/*      */       }
/*  184 */       if (Trace.isOn) {
/*  185 */         Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getMQGETinProgressCount()", "getter", 
/*  186 */             Integer.valueOf(this.MQGETinProgressCount));
/*      */       }
/*  188 */       return this.MQGETinProgressCount;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void incrementMQGETinProgressCounter() {
/*  196 */     if (Trace.isOn) {
/*  197 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalHconn", "incrementMQGETinProgressCounter()");
/*      */     }
/*  199 */     synchronized (this.isInMQGETLock) {
/*  200 */       this.MQGETinProgressCount++;
/*  201 */       if (Trace.isOn) {
/*  202 */         Trace.data(this, "Incrementing MQGETTinProgressCount to: ", 
/*  203 */             Integer.toString(this.MQGETinProgressCount));
/*      */       }
/*      */     } 
/*  206 */     if (Trace.isOn) {
/*  207 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalHconn", "incrementMQGETinProgressCounter()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void decrementMQGETinProgressCounter() {
/*  216 */     if (Trace.isOn) {
/*  217 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalHconn", "decrementMQGETinProgressCounter()");
/*      */     }
/*  219 */     synchronized (this.isInMQGETLock) {
/*  220 */       this.MQGETinProgressCount--;
/*  221 */       if (Trace.isOn) {
/*  222 */         Trace.data(this, "Decrementing MQGETinProgressCount to: ", 
/*  223 */             Integer.toString(this.MQGETinProgressCount));
/*      */       }
/*      */     } 
/*  226 */     if (Trace.isOn) {
/*  227 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalHconn", "decrementMQGETinProgressCounter()");
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
/*      */   private static JmqiThreadPool getThreadPool(JmqiEnvironment env) {
/*  239 */     if (Trace.isOn) {
/*  240 */       Trace.entry("com.ibm.mq.jmqi.local.LocalHconn", "getThreadPool(JmqiEnvironment)", new Object[] { env });
/*      */     }
/*      */     
/*  243 */     if (threadPool == null) {
/*  244 */       threadPool = env.getThreadPoolFactory().getThreadPool();
/*      */     }
/*      */     
/*  247 */     if (Trace.isOn) {
/*  248 */       Trace.exit("com.ibm.mq.jmqi.local.LocalHconn", "getThreadPool(JmqiEnvironment)", threadPool);
/*      */     }
/*  250 */     return threadPool;
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
/*      */   protected LocalHconn(JmqiEnvironment env, boolean useWorkerThread, int value) throws JmqiException {
/*  264 */     super(env);
/*  265 */     if (Trace.isOn) {
/*  266 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalHconn", "<init>(JmqiEnvironment,boolean,int)", new Object[] { env, 
/*  267 */             Boolean.valueOf(useWorkerThread), Integer.valueOf(value) });
/*      */     }
/*  269 */     this.value = value;
/*  270 */     this.useWorkerThread = useWorkerThread;
/*  271 */     initialise();
/*      */     
/*  273 */     if (Trace.isOn) {
/*  274 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalHconn", "<init>(JmqiEnvironment,boolean,int)");
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
/*      */   protected LocalHconn(JmqiEnvironment env, boolean useWorkerThread) throws JmqiException {
/*  289 */     super(env);
/*  290 */     if (Trace.isOn) {
/*  291 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalHconn", "<init>(JmqiEnvironment,boolean)", new Object[] { env, 
/*  292 */             Boolean.valueOf(useWorkerThread) });
/*      */     }
/*  294 */     this.value = -1;
/*  295 */     this.useWorkerThread = useWorkerThread;
/*  296 */     initialise();
/*      */     
/*  298 */     if (Trace.isOn) {
/*  299 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalHconn", "<init>(JmqiEnvironment,boolean)");
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
/*      */   private void initialise() throws JmqiException {
/*  311 */     if (Trace.isOn) {
/*  312 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalHconn", "initialise()");
/*      */     }
/*  314 */     if (this.useWorkerThread) {
/*  315 */       initialiseWorkerThread();
/*      */     }
/*      */ 
/*      */     
/*  319 */     JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)this.env;
/*  320 */     this.rxpb = sysenv.newRXPB();
/*  321 */     this.proxyConsumer = new LocalProxyConsumer(this.env, this, null);
/*      */     
/*  323 */     if (Trace.isOn) {
/*  324 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalHconn", "initialise()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void initialiseWorkerThread() throws JmqiException {
/*  330 */     if (Trace.isOn) {
/*  331 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalHconn", "initialiseWorkerThread()");
/*      */     }
/*  333 */     JmqiThreadPool threadPoolInstance = getThreadPool(this.env);
/*  334 */     this.workerThread = this.env.newJmqiWorkerThread();
/*  335 */     threadPoolInstance.enqueue((Runnable)this.workerThread);
/*      */     
/*  337 */     if (Trace.isOn) {
/*  338 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalHconn", "initialiseWorkerThread()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getValue() {
/*  347 */     if (Trace.isOn) {
/*  348 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getValue()", "getter", 
/*  349 */           Integer.valueOf(this.value));
/*      */     }
/*  351 */     return this.value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setValue(int value) {
/*  360 */     if (Trace.isOn) {
/*  361 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "setValue(int)", "setter", 
/*  362 */           Integer.valueOf(value));
/*      */     }
/*  364 */     this.value = value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getShareOption() {
/*  372 */     if (Trace.isOn) {
/*  373 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getShareOption()", "getter", 
/*  374 */           Integer.valueOf(this.shareOption));
/*      */     }
/*  376 */     return this.shareOption;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShareOption(int shareOption) {
/*  384 */     if (Trace.isOn) {
/*  385 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "setShareOption(int)", "setter", 
/*  386 */           Integer.valueOf(shareOption));
/*      */     }
/*  388 */     this.shareOption = shareOption;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setParent(Hconn parent) {
/*  396 */     if (Trace.isOn) {
/*  397 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "setParent(Hconn)", "setter", parent);
/*      */     }
/*  399 */     this.parent = (LocalHconn)parent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  408 */     String stringVal = "0x" + Integer.toHexString(this.value);
/*  409 */     return stringVal;
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
/*      */   public static LocalHconn getLocalHconn(JmqiEnvironment env, boolean useWorkerThread, Hconn hconn) throws JmqiException {
/*  421 */     if (Trace.isOn) {
/*  422 */       Trace.entry("com.ibm.mq.jmqi.local.LocalHconn", "getLocalHconn(JmqiEnvironment,boolean,Hconn)", new Object[] { env, 
/*      */             
/*  424 */             Boolean.valueOf(useWorkerThread), hconn });
/*      */     }
/*  426 */     LocalHconn localHconn = null;
/*  427 */     if (hconn instanceof LocalHconn) {
/*  428 */       localHconn = (LocalHconn)hconn;
/*      */     }
/*  430 */     else if (hconn == CMQC.jmqi_MQHC_DEF_HCONN) {
/*  431 */       localHconn = new LocalHconn(env, useWorkerThread, 0);
/*      */     }
/*      */     else {
/*      */       
/*  435 */       localHconn = new LocalHconn(env, useWorkerThread);
/*      */     } 
/*      */     
/*  438 */     if (Trace.isOn) {
/*  439 */       Trace.exit("com.ibm.mq.jmqi.local.LocalHconn", "getLocalHconn(JmqiEnvironment,boolean,Hconn)", localHconn);
/*      */     }
/*      */     
/*  442 */     return localHconn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateHconn(LocalMQ mqInstance, Phconn phconn) {
/*  451 */     if (Trace.isOn) {
/*  452 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalHconn", "updateHconn(LocalMQ,Phconn)", new Object[] { mqInstance, phconn });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  457 */     setMq(mqInstance);
/*      */     
/*  459 */     if (this.workerThread != null) {
/*  460 */       switch (this.value) {
/*      */         case -1:
/*      */         case 0:
/*      */           try {
/*  464 */             this.workerThread.close();
/*  465 */             this.workerThread = null;
/*      */           
/*      */           }
/*  468 */           catch (JmqiException e) {
/*  469 */             if (Trace.isOn) {
/*  470 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalHconn", "updateHconn(LocalMQ,Phconn)", (Throwable)e);
/*      */             }
/*      */           } 
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  484 */     Hconn hconn = null;
/*  485 */     switch (this.value) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 0:
/*  493 */         if (mqInstance.isCICS() && this.env.getCaller() != 'B') {
/*  494 */           hconn = this;
/*      */           break;
/*      */         } 
/*  497 */         hconn = CMQC.jmqi_MQHC_DEF_HCONN;
/*      */         break;
/*      */       
/*      */       case -1:
/*  501 */         hconn = CMQC.jmqi_MQHC_UNUSABLE_HCONN;
/*      */         break;
/*      */       default:
/*  504 */         hconn = this; break;
/*      */     } 
/*  506 */     phconn.setHconn(hconn);
/*  507 */     if (Trace.isOn) {
/*  508 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalHconn", "updateHconn(LocalMQ,Phconn)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void syncExec(JmqiRunnable job) throws JmqiException, Exception {
/*  519 */     if (Trace.isOn) {
/*  520 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalHconn", "syncExec(JmqiRunnable)", new Object[] { job });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  527 */     if (this.workerThread == null) {
/*  528 */       initialiseWorkerThread();
/*      */     }
/*      */     try {
/*  531 */       this.workerThread.syncExec(job);
/*  532 */       if (job.jmqiRunnableException != null) {
/*  533 */         if (job.jmqiRunnableException instanceof Exception) {
/*  534 */           Exception traceRet1 = (Exception)job.jmqiRunnableException;
/*      */           
/*  536 */           if (Trace.isOn) {
/*  537 */             Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalHconn", "syncExec(JmqiRunnable)", traceRet1, 1);
/*      */           }
/*      */           
/*  540 */           throw traceRet1;
/*      */         } 
/*  542 */         if (job.jmqiRunnableException instanceof Error) {
/*  543 */           Error traceRet2 = (Error)job.jmqiRunnableException;
/*  544 */           if (Trace.isOn) {
/*  545 */             Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalHconn", "syncExec(JmqiRunnable)", traceRet2, 2);
/*      */           }
/*      */           
/*  548 */           throw traceRet2;
/*      */         } 
/*      */         
/*  551 */         RuntimeException traceRet3 = new RuntimeException(job.jmqiRunnableException);
/*      */         
/*  553 */         if (Trace.isOn) {
/*  554 */           Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalHconn", "syncExec(JmqiRunnable)", traceRet3, 3);
/*      */         }
/*      */         
/*  557 */         throw traceRet3;
/*      */       }
/*      */     
/*      */     } finally {
/*      */       
/*  562 */       if (Trace.isOn) {
/*  563 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.local.LocalHconn", "syncExec(JmqiRunnable)");
/*      */       }
/*      */     } 
/*  566 */     if (Trace.isOn) {
/*  567 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalHconn", "syncExec(JmqiRunnable)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void enterCall() throws JmqiException {
/*  578 */     if (Trace.isOn) {
/*  579 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalHconn", "enterCall()");
/*      */     }
/*  581 */     synchronized (this.sharedHConnLock) {
/*  582 */       if (this.shareOption == 64) {
/*  583 */         while (this.callsInProgress != 0) {
/*      */           try {
/*  585 */             this.sharedHConnLock.wait();
/*      */           }
/*  587 */           catch (InterruptedException e) {
/*  588 */             if (Trace.isOn) {
/*  589 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.local.LocalHconn", "enterCall()", e);
/*      */             }
/*      */           }
/*      */         
/*      */         }
/*      */       
/*  595 */       } else if (this.callsInProgress != 0) {
/*  596 */         int compcode = 2;
/*  597 */         int reason = 2018;
/*  598 */         if (this.shareOption == 128) {
/*  599 */           reason = 2219;
/*      */         }
/*      */         
/*  602 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, compcode, reason, null);
/*      */ 
/*      */         
/*  605 */         if (Trace.isOn) {
/*  606 */           Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalHconn", "enterCall()", (Throwable)traceRet1, 1);
/*      */         }
/*  608 */         throw traceRet1;
/*      */       } 
/*      */       
/*  611 */       this.callsInProgress++;
/*  612 */       if (this.callsInProgress != 1) {
/*      */         
/*  614 */         HashMap<String, Object> ffstInfo = new HashMap<>();
/*  615 */         ffstInfo.put("callsInProgress", Integer.valueOf(this.callsInProgress));
/*  616 */         ffstInfo.put("Description", "Call lock failure. Calls already in progress");
/*      */         
/*  618 */         Trace.ffst(this, "enterCall()", "01", ffstInfo, null);
/*      */         
/*  620 */         JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2219, null);
/*      */ 
/*      */         
/*  623 */         if (Trace.isOn) {
/*  624 */           Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalHconn", "enterCall()", (Throwable)traceRet2, 2);
/*      */         }
/*  626 */         throw traceRet2;
/*      */       } 
/*      */     } 
/*  629 */     if (Trace.isOn) {
/*  630 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalHconn", "enterCall()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void leaveCall() throws JmqiException {
/*  641 */     if (Trace.isOn) {
/*  642 */       Trace.entry(this, "com.ibm.mq.jmqi.local.LocalHconn", "leaveCall()");
/*      */     }
/*  644 */     synchronized (this.sharedHConnLock) {
/*  645 */       if (this.callsInProgress != 1) {
/*  646 */         HashMap<String, Object> ffstInfo = new HashMap<>();
/*  647 */         ffstInfo.put("callsInProgress", Integer.valueOf(this.callsInProgress));
/*  648 */         ffstInfo.put("Description", "Call lock failure. Too many calls in progress");
/*      */         
/*  650 */         Trace.ffst(this, "leaveCall()", "01", ffstInfo, null);
/*  651 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2219, null);
/*      */ 
/*      */ 
/*      */         
/*  655 */         if (Trace.isOn) {
/*  656 */           Trace.throwing(this, "com.ibm.mq.jmqi.local.LocalHconn", "leaveCall()", (Throwable)traceRet1);
/*      */         }
/*  658 */         throw traceRet1;
/*      */       } 
/*  660 */       this.callsInProgress--;
/*      */       
/*  662 */       this.sharedHConnLock.notify();
/*      */     } 
/*  664 */     if (Trace.isOn) {
/*  665 */       Trace.exit(this, "com.ibm.mq.jmqi.local.LocalHconn", "leaveCall()");
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
/*      */   private QueueManagerInfo getCacheInfo() throws JmqiException {
/*  678 */     if (this.info != null) {
/*  679 */       return this.info;
/*      */     }
/*      */     
/*  682 */     if (this.parent != null) {
/*  683 */       this.info = this.parent.getInfo();
/*      */     } else {
/*      */       
/*  686 */       this.info = JmqiTools.getQueueManagerInfo(this.env, this.mq, this);
/*      */     } 
/*  688 */     return this.info;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCmdLevel() throws JmqiException {
/*  697 */     int commandLevel = getCacheInfo().getCommandLevel();
/*  698 */     if (Trace.isOn) {
/*  699 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getCmdLevel()", "getter", 
/*  700 */           Integer.valueOf(commandLevel));
/*      */     }
/*  702 */     return commandLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPlatform() throws JmqiException {
/*  712 */     int platform = getCacheInfo().getPlatform();
/*      */     
/*  714 */     if (Trace.isOn) {
/*  715 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getPlatform()", "getter", 
/*  716 */           Integer.valueOf(platform));
/*      */     }
/*  718 */     return platform;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCcsid() throws JmqiException {
/*  727 */     int ccsid = getCacheInfo().getCcsid();
/*      */     
/*  729 */     if (Trace.isOn) {
/*  730 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getCcsid()", "getter", 
/*  731 */           Integer.valueOf(ccsid));
/*      */     }
/*  733 */     return ccsid;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUid() throws JmqiException {
/*  742 */     String uid = getCacheInfo().getUid();
/*      */     
/*  744 */     if (Trace.isOn) {
/*  745 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getUid()", "getter", uid);
/*      */     }
/*  747 */     return uid;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getName() throws JmqiException {
/*  756 */     String name = getCacheInfo().getName();
/*      */     
/*  758 */     if (Trace.isOn) {
/*  759 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getName()", "getter", name);
/*      */     }
/*  761 */     return name;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getOriginalQueueManagerName() {
/*  769 */     if (Trace.isOn) {
/*  770 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getOriginalQueueManagerName()", "getter", this.originalQueueManagerName);
/*      */     }
/*      */     
/*  773 */     return this.originalQueueManagerName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOriginalQueueManagerName(String name) throws JmqiException {
/*  781 */     if (Trace.isOn) {
/*  782 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "setOriginalQueueManagerName(String)", "setter", name);
/*      */     }
/*      */     
/*  785 */     this.originalQueueManagerName = name;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JmqiMQ getMq() {
/*  792 */     if (Trace.isOn) {
/*  793 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getMq()", "getter", this.mq);
/*      */     }
/*  795 */     return this.mq;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMq(JmqiMQ mq) {
/*  803 */     if (Trace.isOn) {
/*  804 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "setMq(JmqiMQ)", "setter", mq);
/*      */     }
/*  806 */     this.mq = mq;
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
/*      */   public boolean isXASupported() {
/*  818 */     if (Trace.isOn) {
/*  819 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "isXASupported()", "getter", 
/*  820 */           Boolean.valueOf(this.useWorkerThread));
/*      */     }
/*  822 */     return this.useWorkerThread;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setQMNameBytes(byte[] qmgrNameBytes) {
/*  829 */     if (Trace.isOn) {
/*  830 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "setQMNameBytes(byte [ ])", "setter", qmgrNameBytes);
/*      */     }
/*      */     
/*  833 */     this.qmNameBytes = qmgrNameBytes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getQMNameBytes() {
/*  840 */     if (Trace.isOn) {
/*  841 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getQMNameBytes()", "getter", this.qmNameBytes);
/*      */     }
/*      */     
/*  844 */     return this.qmNameBytes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RXPB getRxpb() {
/*  852 */     if (Trace.isOn) {
/*  853 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getRxpb()", "getter", this.rxpb);
/*      */     }
/*  855 */     return this.rxpb;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getWASLocalWOWD() {
/*      */     long tranId;
/*  863 */     if (this.jmqiConnectOptions != null) {
/*  864 */       tranId = this.jmqiConnectOptions.getWASLocalUOWID();
/*      */     } else {
/*      */       
/*  867 */       tranId = -1L;
/*      */     } 
/*  869 */     if (Trace.isOn) {
/*  870 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getWASLocalWOWD()", "getter", 
/*  871 */           Long.valueOf(tranId));
/*      */     }
/*  873 */     return tranId;
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
/*      */   public RXPB getUpdatedRxpb() {
/*  885 */     if (this.jmqiConnectOptions != null) {
/*  886 */       long wasLocalUOWID = this.jmqiConnectOptions.getWASLocalUOWID();
/*  887 */       this.rxpb.setWasTranId(wasLocalUOWID);
/*      */     } 
/*      */     
/*  890 */     if (Trace.isOn) {
/*  891 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getUpdatedRxpb()", "getter", this.rxpb);
/*      */     }
/*  893 */     return this.rxpb;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRxpb(RXPB rxpb) {
/*  900 */     if (Trace.isOn) {
/*  901 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "setRxpb(RXPB)", "setter", rxpb);
/*      */     }
/*  903 */     this.rxpb = rxpb;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfSharingConversations() {
/*  911 */     if (Trace.isOn) {
/*  912 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getNumberOfSharingConversations()", "getter", 
/*  913 */           Integer.valueOf(-1));
/*      */     }
/*  915 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFapLevel() {
/*  923 */     if (Trace.isOn) {
/*  924 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getFapLevel()", "getter", 
/*  925 */           Integer.valueOf(-1));
/*      */     }
/*  927 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private QueueManagerInfo getInfo() {
/*  934 */     if (Trace.isOn) {
/*  935 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getInfo()", "getter", this.info);
/*      */     }
/*  937 */     return this.info;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConnectionId(byte[] connectionId) {
/*  947 */     if (Trace.isOn) {
/*  948 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "setConnectionId(byte [ ])", "setter", connectionId);
/*      */     }
/*      */     
/*  951 */     this.connectionId = connectionId;
/*  952 */     this.connectionIdString = JmqiTools.arrayToHexString(connectionId);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getConnectionId() {
/*  961 */     if (Trace.isOn) {
/*  962 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getConnectionId()", "getter", this.connectionId);
/*      */     }
/*      */     
/*  965 */     return this.connectionId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getConnectionIdAsString() {
/*  974 */     if (Trace.isOn) {
/*  975 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getConnectionIdAsString()", "getter", this.connectionIdString);
/*      */     }
/*      */     
/*  978 */     return this.connectionIdString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class SharedHConnLock
/*      */   {
/*      */     SharedHConnLock() {
/*  991 */       if (Trace.isOn) {
/*  992 */         Trace.entry(this, "com.ibm.mq.jmqi.local.SharedHConnLock", "<init>()");
/*      */       }
/*  994 */       if (Trace.isOn) {
/*  995 */         Trace.exit(this, "com.ibm.mq.jmqi.local.SharedHConnLock", "<init>()");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JmqiConnectOptions getJmqiConnectOpts() {
/* 1005 */     if (Trace.isOn) {
/* 1006 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getJmqiConnectOpts()", "getter", this.jmqiConnectOptions);
/*      */     }
/*      */     
/* 1009 */     return this.jmqiConnectOptions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJmqiConnectOpts(JmqiConnectOptions options) {
/* 1016 */     if (Trace.isOn) {
/* 1017 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "setJmqiConnectOpts(JmqiConnectOptions)", "setter", options);
/*      */     }
/*      */     
/* 1020 */     this.jmqiConnectOptions = options;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public LocalProxyConsumer getProxyConsumer() {
/* 1027 */     if (Trace.isOn) {
/* 1028 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getProxyConsumer()", "getter", this.proxyConsumer);
/*      */     }
/*      */     
/* 1031 */     return this.proxyConsumer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getConnectionArea() {
/* 1042 */     if (Trace.isOn) {
/* 1043 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getConnectionArea()", "getter", this.connectionArea);
/*      */     }
/*      */     
/* 1046 */     return this.connectionArea;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConnectionArea(Object object) {
/* 1057 */     if (Trace.isOn) {
/* 1058 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "setConnectionArea(Object)", "setter", object);
/*      */     }
/*      */     
/* 1061 */     this.connectionArea = object;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPointerSize() {
/* 1069 */     int traceRet1 = LocalMQ.getPtrSize();
/* 1070 */     if (Trace.isOn) {
/* 1071 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getPointerSize()", "getter", 
/* 1072 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1074 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isByteSwap() {
/* 1082 */     boolean traceRet1 = LocalMQ.getSwap();
/* 1083 */     if (Trace.isOn) {
/* 1084 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "isByteSwap()", "getter", 
/* 1085 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1087 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JmqiCodepage getCodePage() {
/* 1095 */     JmqiCodepage traceRet1 = LocalMQ.getCp();
/* 1096 */     if (Trace.isOn) {
/* 1097 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getCodePage()", "getter", traceRet1);
/*      */     }
/* 1099 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getQsgName() throws JmqiException {
/* 1109 */     String qsgName = getCacheInfo().getQsgName();
/*      */     
/* 1111 */     if (Trace.isOn) {
/* 1112 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getQsgName()", "getter", qsgName);
/*      */     }
/* 1114 */     return qsgName;
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
/*      */   public QmgrSplCapability getQmgrSplCapability() {
/* 1126 */     if (Trace.isOn) {
/* 1127 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getQmgrSplCapability()", "getter", QmgrSplCapability.UNKNOWN);
/*      */     }
/*      */     
/* 1130 */     return QmgrSplCapability.UNKNOWN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean equals(Object o) {
/* 1138 */     return super.equals(o);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int hashCode() {
/* 1146 */     return super.hashCode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void invalidate() {
/* 1154 */     this.value = -3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isConnToZos() {
/* 1163 */     boolean retval = false;
/*      */     
/* 1165 */     if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES) {
/* 1166 */       retval = true;
/*      */     }
/*      */     
/* 1169 */     if (Trace.isOn) {
/* 1170 */       Trace.data(this, "com.ibm.mq.jmqi.internal.LocalHconn", "isConnToZos()", "getter", 
/* 1171 */           Boolean.valueOf(retval));
/*      */     }
/* 1173 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRemoteProductId() {
/* 1181 */     if (Trace.isOn) {
/* 1182 */       Trace.data(this, "com.ibm.mq.jmqi.internal.LocalHconn", "getRemoteProductId()", "getter", null);
/*      */     }
/*      */     
/* 1185 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public QmgrAdvancedCapability getQmgrAdvancedCapability() throws JmqiException {
/*      */     QmgrAdvancedCapability traceRet1;
/* 1194 */     int advCap = getCacheInfo().getAdvCap();
/*      */     
/* 1196 */     if (advCap == 1) {
/* 1197 */       traceRet1 = QmgrAdvancedCapability.SUPPORTED;
/*      */     }
/* 1199 */     else if (advCap == -1) {
/* 1200 */       traceRet1 = QmgrAdvancedCapability.UNKNOWN;
/*      */     } else {
/*      */       
/* 1203 */       traceRet1 = QmgrAdvancedCapability.NOT_SUPPORTED;
/*      */     } 
/*      */     
/* 1206 */     if (Trace.isOn) {
/* 1207 */       Trace.data(this, "com.ibm.mq.jmqi.internal.LocalHconn", "getQmgrAdvancedCapability()", "getter", traceRet1);
/*      */     }
/*      */     
/* 1210 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isXAPrepared() {
/* 1218 */     return this.XAPrepared;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setXAPrepared(boolean xAPrepared) {
/* 1226 */     this.XAPrepared = xAPrepared;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getConnTag() {
/* 1234 */     if (Trace.isOn) {
/* 1235 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "getConnTag()", this.connTag);
/*      */     }
/*      */     
/* 1238 */     return this.connTag;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConnTag(byte[] connTag) {
/* 1246 */     if (Trace.isOn) {
/* 1247 */       Trace.data(this, "com.ibm.mq.jmqi.local.LocalHconn", "setConnTag()", connTag);
/*      */     }
/*      */     
/* 1250 */     this.connTag = connTag;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean onMessagePermitted() {
/* 1259 */     return true;
/*      */   }
/*      */   
/*      */   public void leaveOnMessage() {}
/*      */   
/*      */   public void initiateConnectionStop() {}
/*      */   
/*      */   public void finishConnectionStop() {}
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\LocalHconn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */