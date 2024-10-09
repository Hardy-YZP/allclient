/*      */ package com.ibm.mq.jmqi.remote.api;
/*      */ 
/*      */ import com.ibm.mq.constants.QmgrAdvancedCapability;
/*      */ import com.ibm.mq.constants.QmgrSplCapability;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.JmqiReconnectionListener;
/*      */ import com.ibm.mq.jmqi.JmqiUtils;
/*      */ import com.ibm.mq.jmqi.MQBNO;
/*      */ import com.ibm.mq.jmqi.MQCBC;
/*      */ import com.ibm.mq.jmqi.MQCBD;
/*      */ import com.ibm.mq.jmqi.MQCNO;
/*      */ import com.ibm.mq.jmqi.MQCTLO;
/*      */ import com.ibm.mq.jmqi.MQConsumer;
/*      */ import com.ibm.mq.jmqi.RebalancingListener;
/*      */ import com.ibm.mq.jmqi.RebalancingRequest;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Hobj;
/*      */ import com.ibm.mq.jmqi.handles.Phconn;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.mq.jmqi.remote.impl.RemoteConnection;
/*      */ import com.ibm.mq.jmqi.remote.impl.RemoteConstants;
/*      */ import com.ibm.mq.jmqi.remote.impl.RemoteDispatchThread;
/*      */ import com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue;
/*      */ import com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager;
/*      */ import com.ibm.mq.jmqi.remote.impl.RemoteSession;
/*      */ import com.ibm.mq.jmqi.remote.impl.RemoteTagPool;
/*      */ import com.ibm.mq.jmqi.remote.impl.RemoteTls;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpStructure;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpTSH;
/*      */ import com.ibm.mq.jmqi.remote.util.HconnLock;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*      */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.locking.TraceableReentrantLock;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.PrintWriter;
/*      */ import java.util.ArrayList;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.EnumSet;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.locks.ReentrantLock;
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
/*      */ public abstract class RemoteHconn
/*      */   extends JmqiObject
/*      */   implements Hconn
/*      */ {
/*      */   private static final String SCCSID = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/api/RemoteHconn.java";
/*      */   protected volatile RemoteSession session;
/*      */   private String qmName;
/*      */   private MQCNO connectOptions;
/*      */   
/*      */   static {
/*  115 */     if (Trace.isOn) {
/*  116 */       Trace.data("com.ibm.mq.jmqi.remote.api.RemoteHconn", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/api/RemoteHconn.java");
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
/*  132 */   protected JmqiConnectOptions jmqiConnectOptions = null;
/*      */ 
/*      */   
/*      */   protected Set<RemoteHobj> hobjs;
/*      */ 
/*      */   
/*      */   protected Set<RemoteHsub> hsubs;
/*      */   
/*      */   protected String reconnectQmName;
/*      */   
/*      */   private String reconnectQmId;
/*      */   
/*  144 */   protected int reconnectAttempts = 0;
/*      */   
/*  146 */   protected long nextReconnect = 0L;
/*      */   
/*  148 */   protected ReconnectionState reconnectionState = new ReconnectionState();
/*      */   
/*  150 */   private JmqiReconnectionListener reconnectionListener = null;
/*      */ 
/*      */   
/*  153 */   private RebalancingListener rebalancingListener = null;
/*      */ 
/*      */   
/*      */   private volatile boolean callbackStarted = false;
/*      */   
/*      */   private volatile boolean callbackSuspended = false;
/*      */   
/*  160 */   private int capabilityFlag = 0;
/*      */ 
/*      */   
/*      */   private boolean suspendInProgress = false;
/*      */   
/*  165 */   private MQCBD eventDesc = null;
/*      */ 
/*      */   
/*      */   private boolean eventRegistered = false;
/*      */ 
/*      */   
/*      */   private boolean XAPrepared = false;
/*      */ 
/*      */   
/*      */   private boolean eventSuspended = false;
/*      */   
/*      */   private CallLock callLock;
/*      */   
/*      */   private NotifyLock notifyLock;
/*      */   
/*  180 */   private String originalQueueManagerName = null;
/*      */ 
/*      */   
/*  183 */   private final Object dispatchEventSync = new DispatchEventSync();
/*      */   
/*      */   private boolean dispatchEventPosted = false;
/*      */   
/*      */   private RemoteDispatchThread dispatchThread;
/*      */   
/*      */   private DispatchLock dispatchLock;
/*      */   
/*      */   private static final class ThreadLock
/*      */     extends TraceableReentrantLock
/*      */   {
/*      */     private static final long serialVersionUID = 4631617060083318091L;
/*      */     
/*      */     private ThreadLock() {}
/*      */   }
/*  198 */   private final ReentrantLock threadLock = (ReentrantLock)new ThreadLock();
/*      */ 
/*      */   
/*  201 */   private Set<RemoteProxyQueue> dispatchQueueList = new HashSet<>();
/*      */ 
/*      */   
/*  204 */   private final Object dispatchQMutex = new DispatchQMutex();
/*      */ 
/*      */   
/*  207 */   protected RemoteProxyQueueManager proxyQueueManager = null;
/*      */ 
/*      */   
/*      */   private static final int DEFAULT_RECONNECT_TIMEOUT = 1800;
/*      */ 
/*      */   
/*  213 */   protected int shareOption = 0;
/*      */   
/*      */   private Object ehObject;
/*      */   
/*      */   private int ehOptions;
/*  218 */   private EnumSet<RemoteConstants.Event> eventsHad = EnumSet.noneOf(RemoteConstants.Event.class);
/*  219 */   private EnumSet<RemoteConstants.Event> eventsRaised = EnumSet.noneOf(RemoteConstants.Event.class);
/*      */ 
/*      */ 
/*      */   
/*      */   protected volatile int acFlags;
/*      */ 
/*      */ 
/*      */   
/*      */   private String applicationName;
/*      */ 
/*      */   
/*      */   private Object connectionArea;
/*      */ 
/*      */   
/*      */   byte[] connTag;
/*      */ 
/*      */   
/*      */   private QmgrSplCapability qmgrSplCapability;
/*      */ 
/*      */   
/*      */   protected RemoteFAP fap;
/*      */ 
/*      */   
/*      */   private JmqiSystemEnvironment sysenv;
/*      */ 
/*      */   
/*      */   private boolean waitedOnReconnect;
/*      */ 
/*      */   
/*      */   private volatile int applicableReconnectCycle;
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isReconnectableReasonCode(int reason) {
/*  253 */     if (Trace.isOn)
/*  254 */       Trace.entry("com.ibm.mq.jmqi.remote.api.RemoteHconn", "isReconnectableReasonCode(int)", new Object[] {
/*  255 */             Integer.valueOf(reason)
/*      */           }); 
/*  257 */     switch (reason) {
/*      */       case 2009:
/*      */       case 2059:
/*      */       case 2161:
/*      */       case 2162:
/*      */       case 2195:
/*      */       case 2202:
/*      */       case 2537:
/*      */       case 2538:
/*      */       case 2539:
/*  267 */         if (Trace.isOn) {
/*  268 */           Trace.exit("com.ibm.mq.jmqi.remote.api.RemoteHconn", "isReconnectableReasonCode(int)", 
/*  269 */               Boolean.valueOf(true), 1);
/*      */         }
/*  271 */         return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  276 */     if (Trace.isOn) {
/*  277 */       Trace.exit("com.ibm.mq.jmqi.remote.api.RemoteHconn", "isReconnectableReasonCode(int)", 
/*  278 */           Boolean.valueOf(false), 2);
/*      */     }
/*  280 */     return false;
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
/*      */   RemoteHconn(JmqiEnvironment env, RemoteFAP fap, RemoteSession remoteSession, String qmName, MQCNO connectOptions, JmqiConnectOptions jmqiConnectOptions) throws JmqiException {
/*  301 */     super(env);
/*  302 */     this.sysenv = (JmqiSystemEnvironment)env;
/*  303 */     if (Trace.isOn) {
/*  304 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "<init>(JmqiEnvironment,RemoteFAP,RemoteSession,String,MQCNO, JmqiConnectOptions)", new Object[] { env, fap, remoteSession, qmName, connectOptions, jmqiConnectOptions });
/*      */     }
/*      */ 
/*      */     
/*  308 */     this.qmName = qmName;
/*      */     
/*  310 */     this.connectOptions = env.newMQCNO();
/*  311 */     this.connectOptions.setVersion(connectOptions.getVersion());
/*  312 */     this.connectOptions.setClientConn(connectOptions.getClientConn());
/*  313 */     this.connectOptions.setOptions(connectOptions.getOptions());
/*  314 */     this.connectOptions.setSecurityParms(connectOptions.getSecurityParms());
/*  315 */     this.connectOptions.setSslConfig(connectOptions.getSslConfig());
/*  316 */     MQBNO mqbno = connectOptions.getMqbno();
/*  317 */     if (mqbno != null) {
/*  318 */       this.connectOptions.setMqbno(mqbno);
/*      */     }
/*  320 */     this.jmqiConnectOptions = jmqiConnectOptions;
/*  321 */     initialiseSubclassFields();
/*      */     
/*  323 */     if (jmqiConnectOptions != null) {
/*  324 */       this.reconnectionListener = jmqiConnectOptions.getReconnectionListener();
/*  325 */       this.rebalancingListener = jmqiConnectOptions.getRebalancingListener();
/*      */     } 
/*      */     
/*  328 */     if (remoteSession.isMultiplexingEnabled()) {
/*  329 */       this.proxyQueueManager = new RemoteProxyQueueManager(env, this);
/*      */     }
/*  331 */     this.hobjs = new HashSet<>();
/*  332 */     this.hsubs = new HashSet<>();
/*  333 */     setSession(remoteSession);
/*      */     
/*  335 */     this.fap = fap;
/*  336 */     this.applicableReconnectCycle = remoteSession.getReconnectCycle();
/*      */     
/*  338 */     this.callLock = new CallLock(fap);
/*  339 */     this.dispatchLock = new DispatchLock(fap);
/*  340 */     this.notifyLock = new NotifyLock(fap);
/*      */     
/*  342 */     if (Trace.isOn) {
/*  343 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "<init>(JmqiEnvironment,RemoteFAP,RemoteSession,String,MQCNO)");
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
/*      */   public static RemoteHconn newInstance(JmqiEnvironment env, RemoteFAP fap, RemoteHconn parentHconn, RemoteSession remoteSession, String name, MQCNO connectionOptions, JmqiConnectOptions jmqiConnectOptions) throws JmqiException {
/*  366 */     if (Trace.isOn) {
/*  367 */       Trace.entry("com.ibm.mq.jmqi.remote.api.RemoteHconn", "newInstance(JmqiEnvironment,RemoteFAP,RemoteHconn,RemoteSession,String, MQCNO,JmqiConnectOptions) ", new Object[] { env, fap, parentHconn, remoteSession, name, connectionOptions, jmqiConnectOptions });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  372 */     RemoteHconn theInstance = null;
/*  373 */     if (parentHconn == null) {
/*  374 */       theInstance = new RemoteParentHconn(env, fap, remoteSession, name, connectionOptions, jmqiConnectOptions);
/*      */     } else {
/*      */       
/*  377 */       theInstance = new RemoteChildHconn(env, fap, remoteSession, name, connectionOptions, jmqiConnectOptions, parentHconn);
/*  378 */       ((RemoteParentHconn)parentHconn).addChildHconn((RemoteChildHconn)theInstance);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  383 */       if (parentHconn.hasFailed()) {
/*  384 */         return null;
/*      */       }
/*      */     } 
/*      */     
/*  388 */     if (Trace.isOn) {
/*  389 */       Trace.exit("com.ibm.mq.jmqi.remote.api.RemoteHconn", "newInstance(JmqiEnvironment,RemoteFAP,RemoteHconn,RemoteSession,String, MQCNO,JmqiConnectOptions) ", theInstance);
/*      */     }
/*      */ 
/*      */     
/*  393 */     return theInstance;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void checkTxnMessage() throws JmqiException {
/*  400 */     if (Trace.isOn) {
/*  401 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkTxnMessage()");
/*      */     }
/*  403 */     this.proxyQueueManager.checkTxnMessage(this.fap.getTls());
/*      */     
/*  405 */     if (Trace.isOn) {
/*  406 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkTxnMessage()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void clearAllTxnMessages() throws JmqiException {
/*  415 */     if (Trace.isOn) {
/*  416 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "clearAllTxnMessages()");
/*      */     }
/*      */ 
/*      */     
/*  420 */     if (this.proxyQueueManager != null) {
/*  421 */       this.proxyQueueManager.clearAllTxnMessages(this.fap.getTls());
/*      */     }
/*      */     
/*  424 */     if (Trace.isOn) {
/*  425 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "clearAllTxnMessages()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkClientEmpty() {
/*  434 */     if (Trace.isOn) {
/*  435 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkClientEmpty()");
/*      */     }
/*  437 */     boolean traceRet1 = this.proxyQueueManager.checkClientEmpty();
/*      */     
/*  439 */     if (Trace.isOn) {
/*  440 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkClientEmpty()", 
/*  441 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  443 */     return traceRet1;
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
/*      */   public void checkDispatchable(RemoteProxyQueue pq) throws JmqiException {
/*  460 */     if (Trace.isOn) {
/*  461 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkDispatchable(RemoteProxyQueue)", new Object[] { pq });
/*      */     }
/*      */     
/*  464 */     RemoteDispatchThread pThread = null;
/*  465 */     boolean dispatchable = false;
/*      */     
/*  467 */     if (!this.eventsHad.equals(this.eventsRaised) && 
/*  468 */       this.ehObject != null && (this.acFlags & 0x400) == 0) {
/*  469 */       dispatchable = true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  476 */     if (!dispatchable) {
/*      */       
/*  478 */       if ((this.acFlags & 0x40) == 0 || (this.acFlags & 0x80) != 0) {
/*      */         
/*  480 */         if (Trace.isOn) {
/*  481 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkDispatchable(RemoteProxyQueue)", 1);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  492 */       if (pq != null) {
/*  493 */         dispatchable = pq.isDispatchable();
/*      */       } else {
/*      */         
/*  496 */         synchronized (this.dispatchQMutex) {
/*  497 */           if (Trace.isOn) {
/*  498 */             Trace.data(this, "checkDispatchable(RemoteProxyQueue)", "checking " + this.dispatchQueueList
/*  499 */                 .size() + " queues", null);
/*      */           }
/*  501 */           for (RemoteProxyQueue pq1 : this.dispatchQueueList) {
/*  502 */             if (pq1.isDispatchable()) {
/*  503 */               dispatchable = true;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  510 */       if (!dispatchable) {
/*  511 */         if (Trace.isOn) {
/*  512 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkDispatchable(RemoteProxyQueue)", 2);
/*      */         }
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*      */     
/*  520 */     requestThreadLock();
/*      */     
/*      */     try {
/*  523 */       pThread = getDispatchThread();
/*      */ 
/*      */       
/*  526 */       pThread.incrementDispatchSeq();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  532 */       if (pThread.isThreadSleeping()) {
/*  533 */         pThread.postSleepingEvent();
/*  534 */         pThread.setThreadSleeping(false);
/*      */       } 
/*      */     } finally {
/*      */       
/*  538 */       if (Trace.isOn) {
/*  539 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkDispatchable(RemoteProxyQueue)");
/*      */       }
/*      */       
/*  542 */       releaseThreadLock();
/*      */     } 
/*      */     
/*  545 */     if (Trace.isOn) {
/*  546 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkDispatchable(RemoteProxyQueue)", 3);
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
/*      */   public void checkTxnAllowed() throws JmqiException {
/*  559 */     if (Trace.isOn) {
/*  560 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkTxnAllowed()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  566 */     int globalMessageIndex1 = this.session.getGlobalMessageIndex();
/*  567 */     if (checkClientEmpty()) {
/*  568 */       sendNotification(-1, 8, globalMessageIndex1, false);
/*      */     }
/*      */     
/*  571 */     if (Trace.isOn) {
/*  572 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkTxnAllowed()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void postDispatchThreadEvent() {
/*  581 */     if (Trace.isOn) {
/*  582 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "postDispatchThreadEvent()");
/*      */     }
/*  584 */     synchronized (this.dispatchEventSync) {
/*  585 */       this.dispatchEventPosted = true;
/*  586 */       this.dispatchEventSync.notify();
/*      */     } 
/*      */     
/*  589 */     if (Trace.isOn) {
/*  590 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "postDispatchThreadEvent()");
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
/*      */   private boolean waitOnDispatchEvent(int timeout) {
/*  604 */     if (Trace.isOn)
/*  605 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "waitOnDispatchEvent(int)", new Object[] {
/*  606 */             Integer.valueOf(timeout)
/*      */           }); 
/*  608 */     boolean wasEventPosted = false;
/*  609 */     synchronized (this.dispatchEventSync) {
/*      */       
/*  611 */       if (this.dispatchEventPosted) {
/*  612 */         wasEventPosted = true;
/*  613 */         this.dispatchEventPosted = false;
/*      */       } else {
/*      */         
/*  616 */         boolean waitComplete = false;
/*  617 */         while (!waitComplete) {
/*      */           
/*      */           try {
/*  620 */             if (timeout >= 0) {
/*  621 */               this.dispatchEventSync.wait(timeout);
/*      */             } else {
/*      */               
/*  624 */               this.dispatchEventSync.wait();
/*      */             } 
/*      */             
/*  627 */             waitComplete = true;
/*      */             
/*  629 */             if (this.dispatchEventPosted) {
/*  630 */               wasEventPosted = true;
/*  631 */               this.dispatchEventPosted = false;
/*      */             }
/*      */           
/*  634 */           } catch (InterruptedException e) {
/*  635 */             if (Trace.isOn) {
/*  636 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "waitOnDispatchEvent(int)", e);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  644 */     if (Trace.isOn) {
/*  645 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "waitOnDispatchEvent(int)", 
/*  646 */           Boolean.valueOf(wasEventPosted));
/*      */     }
/*  648 */     return wasEventPosted;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void dispatchThreadExchange() {
/*  657 */     if (Trace.isOn) {
/*  658 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "dispatchThreadExchange()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  664 */     this.dispatchThread.postSleepingEvent();
/*      */     
/*  666 */     int lockHeldCount = fullyReleaseDispatchLock();
/*      */     
/*  668 */     waitOnDispatchEvent(-1);
/*  669 */     int lockReacquireCount = lockHeldCount - 1;
/*      */     
/*  671 */     for (int i = 0; i < lockReacquireCount; i++) {
/*  672 */       requestDispatchLock();
/*      */     }
/*      */     
/*  675 */     if (Trace.isOn) {
/*  676 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "dispatchThreadExchange()");
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
/*      */   public void wakeDispatchThread() {
/*  688 */     if (Trace.isOn) {
/*  689 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "wakeDispatchThread()");
/*      */     }
/*  691 */     if (this.dispatchThread != null) {
/*      */       
/*  693 */       Trace.data(this, "wakeDispatchThread()", "Waking the dispatch thread", this.dispatchThread);
/*      */       
/*  695 */       this.dispatchThread.postSleepingEvent();
/*      */     } else {
/*      */       
/*  698 */       Trace.data(this, "wakeDispatchThread()", "No dispatch thread has been created for this RemoteHconn yet, so there is nothing to do");
/*      */     } 
/*      */ 
/*      */     
/*  702 */     if (Trace.isOn) {
/*  703 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "wakeDispatchThread()");
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
/*      */   public void driveEventsEH() throws JmqiException {
/*  715 */     if (Trace.isOn) {
/*  716 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "driveEventsEH()");
/*      */     }
/*      */ 
/*      */     
/*  720 */     for (RemoteConstants.Event ed : this.eventsHad) {
/*  721 */       if (!this.eventsRaised.contains(ed)) {
/*  722 */         this.eventsRaised.add(ed);
/*  723 */         callEventHandler(5, 2, ed.getReason());
/*      */       } 
/*      */     } 
/*      */     
/*  727 */     if (Trace.isOn) {
/*  728 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "driveEventsEH()");
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
/*      */   public void driveOutstanding() throws JmqiException {
/*  740 */     if (Trace.isOn) {
/*  741 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "driveOutstanding()");
/*      */     }
/*      */     
/*  744 */     List<RemoteProxyQueue> proxyQueuesToDeregister = new ArrayList<>();
/*      */ 
/*      */ 
/*      */     
/*  748 */     this.acFlags |= 0x200;
/*      */ 
/*      */     
/*  751 */     while ((this.acFlags & 0x200) != 0) {
/*      */       
/*  753 */       this.acFlags &= 0xFFFFFDFF;
/*      */       
/*  755 */       synchronized (this.dispatchQMutex) {
/*      */         
/*  757 */         for (RemoteProxyQueue pq : this.dispatchQueueList) {
/*      */ 
/*      */ 
/*      */           
/*  761 */           if ((this.acFlags & 0x40) != 0) {
/*      */ 
/*      */ 
/*      */             
/*  765 */             if ((pq.getMqcbCBD().getOptions() & 0x1) != 0 && (pq.getStatus() & 0x400000) == 0) {
/*  766 */               pq.callConsumer(1, 0, 0);
/*      */ 
/*      */             
/*      */             }
/*      */           
/*      */           }
/*  772 */           else if ((pq.getMqcbCBD().getOptions() & 0x4) != 0 && (pq.getStatus() & 0x400000) != 0 && (pq
/*  773 */             .getStatus() & 0x800000) == 0) {
/*  774 */             pq.callConsumer(2, 0, 0);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  780 */           if ((pq.getStatus() & 0x200000) != 0) {
/*  781 */             proxyQueuesToDeregister.add(pq);
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  788 */         for (RemoteProxyQueue pq : proxyQueuesToDeregister) {
/*  789 */           pq.mqcbDeRegisterMC(true);
/*      */         }
/*  791 */         proxyQueuesToDeregister.clear();
/*      */       } 
/*      */     } 
/*      */     
/*  795 */     if (Trace.isOn) {
/*  796 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "driveOutstanding()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasEventsOutstanding() {
/*  805 */     if (Trace.isOn) {
/*  806 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "hasEventsOutstanding()");
/*      */     }
/*  808 */     boolean traceRet1 = !this.eventsRaised.equals(this.eventsHad);
/*      */     
/*  810 */     if (Trace.isOn) {
/*  811 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "hasEventsOutstanding()", 
/*  812 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  814 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RemoteFAP getFap() {
/*  821 */     if (Trace.isOn) {
/*  822 */       Trace.data(this, "getFap()", "getter", this.fap);
/*      */     }
/*      */     
/*  825 */     return this.fap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFapLevel() throws JmqiException {
/*  833 */     int traceRet1 = getSession().getFapLevel();
/*  834 */     if (Trace.isOn) {
/*  835 */       Trace.data(this, "getFapLevel()", "getter", 
/*  836 */           Integer.valueOf(traceRet1));
/*      */     }
/*  838 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getName() throws JmqiException {
/*  847 */     String traceRet1 = null;
/*      */     try {
/*  849 */       traceRet1 = getSession().getName();
/*      */     }
/*  851 */     catch (JmqiException e) {
/*  852 */       if (shouldReconnect(e)) {
/*      */         try {
/*  854 */           initiateReconnection(this.session);
/*  855 */           traceRet1 = getName();
/*      */         }
/*  857 */         catch (JmqiException je) {
/*  858 */           if (Trace.isOn) {
/*  859 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "getName()", (Throwable)je);
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  866 */     if (Trace.isOn) {
/*  867 */       Trace.data(this, "getName()", "getter", traceRet1);
/*      */     }
/*  869 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean shouldReconnect(JmqiException e) {
/*  877 */     if (Trace.isOn) {
/*  878 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "shouldReconnect(JmqiException)", new Object[] { e });
/*      */     }
/*      */ 
/*      */     
/*  882 */     RemoteTls tls = this.fap.getTls();
/*  883 */     boolean result = false;
/*  884 */     if (isReconnectable() && 
/*  885 */       !tls.isReconnectThread && 
/*  886 */       !tls.inMQCTLorMQCB && 
/*  887 */       isReconnectableReasonCode(e.getReason()) && 
/*  888 */       !hasFailed()) {
/*  889 */       result = true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  896 */     if (Trace.isOn) {
/*  897 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "shouldReconnect(JmqiException)", 
/*  898 */           Boolean.valueOf(result));
/*      */     }
/*  900 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfSharingConversations() throws JmqiException {
/*  909 */     int traceRet1 = getSession().getNumberOfSharingConversations();
/*  910 */     if (Trace.isOn) {
/*  911 */       Trace.data(this, "getNumberOfSharingConversations()", "getter", 
/*  912 */           Integer.valueOf(traceRet1));
/*      */     }
/*  914 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RemoteConnection getParentConnection() throws JmqiException {
/*  922 */     RemoteConnection traceRet1 = getParentConnection(null);
/*  923 */     if (Trace.isOn) {
/*  924 */       Trace.data(this, "getParentConnection()", "getter", traceRet1);
/*      */     }
/*  926 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RemoteConnection getParentConnection(RemoteProxyQueue proxyQueue) throws JmqiException {
/*  935 */     if (Trace.isOn) {
/*  936 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "getParentConnection(RemoteProxyQueue)", new Object[] { proxyQueue });
/*      */     }
/*      */     
/*  939 */     RemoteConnection traceRet1 = getSession(proxyQueue).getParentConnection();
/*  940 */     if (Trace.isOn) {
/*  941 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "getParentConnection(RemoteProxyQueue)", traceRet1);
/*      */     }
/*      */     
/*  944 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPlatform() throws JmqiException {
/*  952 */     int traceRet1 = 0;
/*      */     try {
/*  954 */       traceRet1 = getSession().getPlatform();
/*      */     }
/*  956 */     catch (JmqiException e) {
/*  957 */       if (shouldReconnect(e)) {
/*      */         try {
/*  959 */           initiateReconnection(this.session);
/*  960 */           traceRet1 = getPlatform();
/*      */         }
/*  962 */         catch (JmqiException je) {
/*  963 */           if (Trace.isOn) {
/*  964 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "getPlatform()", (Throwable)je);
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  972 */     if (Trace.isOn) {
/*  973 */       Trace.data(this, "getPlatform()", "getter", 
/*  974 */           Integer.valueOf(traceRet1));
/*      */     }
/*  976 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RemoteProxyQueueManager getProxyQueueManager() {
/*  983 */     RemoteProxyQueueManager traceRet1 = this.proxyQueueManager;
/*  984 */     if (Trace.isOn) {
/*  985 */       Trace.data(this, "getProxyQueueManager()", "getter", traceRet1);
/*      */     }
/*  987 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RemoteFAP getRemoteFap() throws JmqiException {
/*  995 */     RemoteFAP traceRet1 = getSession().getRemoteFap();
/*  996 */     if (Trace.isOn) {
/*  997 */       Trace.data(this, "getRemoteFap()", "getter", traceRet1);
/*      */     }
/*  999 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUid() throws JmqiException {
/* 1007 */     String traceRet1 = null;
/*      */     try {
/* 1009 */       traceRet1 = getSession().getUid();
/*      */     }
/* 1011 */     catch (JmqiException e) {
/* 1012 */       if (shouldReconnect(e)) {
/*      */         try {
/* 1014 */           initiateReconnection(this.session);
/* 1015 */           traceRet1 = getUid();
/*      */         }
/* 1017 */         catch (JmqiException je) {
/* 1018 */           if (Trace.isOn) {
/* 1019 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "getUId()", (Throwable)je);
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1026 */     if (Trace.isOn) {
/* 1027 */       Trace.data(this, "getUid()", "getter", traceRet1);
/*      */     }
/* 1029 */     return traceRet1;
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
/*      */   public String getOldUid() throws JmqiException {
/* 1043 */     String traceRet1 = getSession().getOldUid();
/* 1044 */     if (Trace.isOn) {
/* 1045 */       Trace.data(this, "getOldUid()", "getter", traceRet1);
/*      */     }
/* 1047 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDisconnected() throws JmqiException {
/* 1058 */     boolean traceRet1 = getSession().isDisconnected();
/* 1059 */     if (Trace.isOn) {
/* 1060 */       Trace.data(this, "isDisconnected()", "getter", 
/* 1061 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1063 */     return traceRet1;
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
/*      */   public void leaveCall(int reason) throws JmqiException {
/* 1075 */     if (Trace.isOn)
/* 1076 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "leaveCall(int)", new Object[] {
/* 1077 */             Integer.valueOf(reason)
/*      */           }); 
/* 1079 */     leaveCall(reason, true);
/*      */     
/* 1081 */     if (Trace.isOn) {
/* 1082 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "leaveCall(int)");
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
/*      */   public void leaveCall(int reason, boolean doCheck) throws JmqiException {
/* 1098 */     if (Trace.isOn) {
/* 1099 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "leaveCall(int,boolean)", new Object[] {
/* 1100 */             Integer.valueOf(reason), Boolean.valueOf(doCheck)
/*      */           });
/*      */     }
/* 1103 */     leaveCall(this.callLock, reason, doCheck, true);
/*      */     
/* 1105 */     if (Trace.isOn) {
/* 1106 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "leaveCall(int,boolean)");
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
/*      */   public void leaveNotifyCall(int reason, boolean useReconnectLocks) throws JmqiException {
/* 1127 */     if (Trace.isOn) {
/* 1128 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "leaveNotifyCall(int, boolean)", new Object[] {
/* 1129 */             Integer.valueOf(reason), Boolean.valueOf(useReconnectLocks)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1135 */     leaveCall(this.notifyLock, reason, true, useReconnectLocks);
/*      */     
/* 1137 */     if (Trace.isOn) {
/* 1138 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "leaveNotifyCall(int, boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean haveDispatchLock() {
/* 1149 */     if (Trace.isOn) {
/* 1150 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "haveDispatchLock()");
/*      */     }
/*      */     
/* 1153 */     boolean result = this.dispatchLock.isHeldByCurrentThread();
/*      */     
/* 1155 */     if (Trace.isOn) {
/* 1156 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "haveDispatchLock()", Boolean.valueOf(result));
/*      */     }
/* 1158 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void requestDispatchLock() {
/* 1165 */     if (Trace.isOn) {
/* 1166 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "requestDispatchLock()");
/*      */     }
/*      */     
/* 1169 */     this.dispatchLock.lock();
/*      */     
/* 1171 */     if (Trace.isOn) {
/* 1172 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "requestDispatchLock()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reacquireDispatchLock(int depth) {
/* 1183 */     if (Trace.isOn) {
/* 1184 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reacquireDispatchLock(int)", new Object[] {
/* 1185 */             Integer.valueOf(depth)
/*      */           });
/*      */     }
/* 1188 */     this.dispatchLock.relock(depth);
/*      */     
/* 1190 */     if (Trace.isOn) {
/* 1191 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reacquireDispatchLock(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void releaseDispatchLock() {
/* 1201 */     if (Trace.isOn) {
/* 1202 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "releaseDispatchLock()");
/*      */     }
/*      */     
/* 1205 */     if ((this.fap.getTls()).isReconnectThread) {
/* 1206 */       Trace.data(this, "releaseDispatchLock()", "reconnect thread can skip this");
/* 1207 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "releaseDispatchLock()", null);
/*      */       
/*      */       return;
/*      */     } 
/* 1211 */     this.dispatchLock.unlock();
/*      */     
/* 1213 */     if (Trace.isOn) {
/* 1214 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "releaseDispatchLock()");
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
/*      */   public int fullyReleaseDispatchLock() {
/* 1226 */     if (Trace.isOn) {
/* 1227 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "fullyReleaseDispatchLock()");
/*      */     }
/*      */     
/* 1230 */     if ((this.fap.getTls()).isReconnectThread) {
/* 1231 */       if (Trace.isOn) {
/* 1232 */         Trace.data(this, "fullyReleaseDispatchLock()", "reconnect thread can skip this");
/* 1233 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "fullyReleaseDispatchLock()", 0);
/*      */       } 
/* 1235 */       return 0;
/*      */     } 
/*      */     
/* 1238 */     int retval = this.dispatchLock.fullyUnlock();
/*      */     
/* 1240 */     if (Trace.isOn) {
/* 1241 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "fullyReleaseDispatchLock()", 
/* 1242 */           Integer.valueOf(retval));
/*      */     }
/* 1244 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void requestThreadLock() {
/* 1251 */     if (Trace.isOn) {
/* 1252 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "requestThreadLock()");
/*      */     }
/* 1254 */     this.threadLock.lock();
/*      */     
/* 1256 */     if (Trace.isOn) {
/* 1257 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "requestThreadLock()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void releaseThreadLock() {
/* 1266 */     if (Trace.isOn) {
/* 1267 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "releaseThreadLock()");
/*      */     }
/*      */     
/* 1270 */     if (this.threadLock.isHeldByCurrentThread()) {
/* 1271 */       this.threadLock.unlock();
/*      */     }
/*      */     
/* 1274 */     if (Trace.isOn) {
/* 1275 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "releaseThreadLock()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resetReconnectionEvents() {
/* 1285 */     if (Trace.isOn) {
/* 1286 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "resetReconnectionEvents()");
/*      */     }
/*      */     
/* 1289 */     this.eventsHad.clear();
/* 1290 */     this.eventsRaised.clear();
/*      */     
/* 1292 */     if (Trace.isOn) {
/* 1293 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "resetReconnectionEvents()");
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
/*      */   public void sendConnState(boolean reply) throws JmqiException {
/*      */     int clientAstate;
/* 1308 */     if (Trace.isOn) {
/* 1309 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "sendConnState(boolean)", new Object[] {
/* 1310 */             Boolean.valueOf(reply)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1316 */     if ((this.acFlags & 0x40) != 0) {
/* 1317 */       clientAstate = 2;
/* 1318 */       if ((this.acFlags & 0x80) != 0) {
/* 1319 */         clientAstate |= 0x1;
/*      */       }
/*      */     } else {
/*      */       
/* 1323 */       clientAstate = 0;
/*      */     } 
/* 1325 */     sendNotification(-1, 3, clientAstate, reply);
/*      */     
/* 1327 */     if (Trace.isOn) {
/* 1328 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "sendConnState(boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void notifyReconnectionComplete() throws JmqiException {
/* 1334 */     if (Trace.isOn) {
/* 1335 */       Trace.entry("com.ibm.mq.jmqi.remote.api.RemoteHconn", "notifyReconnectionComplete()", new Object[0]);
/*      */     }
/*      */     
/* 1338 */     boolean swap = this.session.isSwap();
/* 1339 */     RfpTSH sTsh = this.session.allocateTSH(15, 1, null);
/*      */     
/* 1341 */     RfpNOTIFICATION notification = (RfpNOTIFICATION)RfpStructure.newRfp(this.env, 20, sTsh
/* 1342 */         .getRfpBuffer(), sTsh.getRfpOffset() + sTsh.hdrSize());
/*      */     
/* 1344 */     sTsh.setControlFlags1(48);
/* 1345 */     notification.setVersion(1, swap);
/* 1346 */     notification.setHobj(-1, swap);
/* 1347 */     notification.setNotificationCode(17, swap);
/* 1348 */     notification.setValue(0, swap);
/* 1349 */     sTsh.setTransLength(sTsh.hdrSize() + 16);
/*      */     
/* 1351 */     this.session.sendTSH(this.fap.getTls(), sTsh);
/*      */     
/* 1353 */     if (Trace.isOn) {
/* 1354 */       Trace.exit("com.ibm.mq.jmqi.remote.api.RemoteHconn", "notifyReconnectionComplete()");
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
/*      */   public void sendNotification(int hobj, int code, int value, boolean reply) throws JmqiException {
/* 1377 */     if (Trace.isOn) {
/* 1378 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "sendNotification(int,int,int,boolean)", new Object[] {
/* 1379 */             Integer.valueOf(hobj), Integer.valueOf(code), Integer.valueOf(value), 
/* 1380 */             Boolean.valueOf(reply)
/*      */           });
/*      */     }
/*      */     
/* 1384 */     RemoteSession reconnSession = this.session;
/*      */     
/*      */     try {
/* 1387 */       sendNotificationReconnectable(hobj, code, value, reply, reconnSession);
/*      */     }
/* 1389 */     catch (JmqiException je) {
/* 1390 */       if (Trace.isOn) {
/* 1391 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "sendNotification(int,int,int,boolean)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 1395 */       if (shouldReconnect(je)) {
/*      */         
/* 1397 */         initiateReconnection(this.session);
/*      */         
/* 1399 */         sendNotification(hobj, code, value, reply);
/*      */       } else {
/*      */         
/* 1402 */         if (Trace.isOn) {
/* 1403 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "sendNotification(int,int,int,boolean)", (Throwable)je);
/*      */         }
/*      */         
/* 1406 */         throw je;
/*      */       } 
/*      */     } 
/*      */     
/* 1410 */     if (Trace.isOn) {
/* 1411 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "sendNotification(int,int,int,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void sendNotificationReconnectable(int hobj, int code, int value, boolean reply, RemoteSession reconnSession) throws JmqiException {
/* 1422 */     if (Trace.isOn)
/* 1423 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "sendNotificationReconnectable(int,int,int,boolean,RemoteSession)", new Object[] {
/*      */             
/* 1425 */             Integer.valueOf(hobj), Integer.valueOf(code), Integer.valueOf(value), 
/* 1426 */             Boolean.valueOf(reply), reconnSession
/*      */           }); 
/* 1428 */     boolean swap = reconnSession.isSwap();
/* 1429 */     RfpTSH sTsh = reconnSession.allocateTSH(15, 1, null);
/*      */     
/* 1431 */     RfpNOTIFICATION notification = (RfpNOTIFICATION)RfpStructure.newRfp(this.env, 20, sTsh
/* 1432 */         .getRfpBuffer(), sTsh.getRfpOffset() + sTsh.hdrSize());
/*      */     
/* 1434 */     sTsh.setControlFlags1(48);
/* 1435 */     notification.setVersion(1, swap);
/* 1436 */     notification.setHobj(hobj, swap);
/* 1437 */     notification.setNotificationCode(code, swap);
/* 1438 */     notification.setValue(value, swap);
/* 1439 */     sTsh.setTransLength(sTsh.hdrSize() + 16);
/*      */ 
/*      */ 
/*      */     
/* 1443 */     if (reply) {
/*      */ 
/*      */ 
/*      */       
/* 1447 */       RfpTSH rTsh = reconnSession.exchangeTSH(this.fap.getTls(), sTsh);
/*      */       
/* 1449 */       if (rTsh.getSegmentType() != 15) {
/* 1450 */         StringBuffer dumpBuffer = new StringBuffer();
/* 1451 */         JmqiTools.hexDump(rTsh.getRfpBuffer(), null, rTsh.getRfpOffset(), rTsh.getTransLength(), dumpBuffer);
/* 1452 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */ 
/*      */         
/* 1455 */         if (Trace.isOn) {
/* 1456 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "sendNotificationReconnectable(int,int,int,boolean,RemoteSession)", (Throwable)traceRet1);
/*      */         }
/*      */         
/* 1459 */         throw traceRet1;
/*      */       } 
/* 1461 */       reconnSession.releaseReceivedTSH(rTsh);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1466 */       reconnSession.sendTSH(this.fap.getTls(), sTsh);
/*      */     } 
/*      */     
/* 1469 */     if (Trace.isOn) {
/* 1470 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "sendNotificationReconnectable(int,int,int,boolean,RemoteSession)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDisconnected(Throwable asyncFailure) {
/* 1480 */     if (Trace.isOn) {
/* 1481 */       Trace.data(this, "setDisconnected(Throwable)", "setter", asyncFailure);
/*      */     }
/*      */ 
/*      */     
/* 1485 */     if (this.proxyQueueManager != null) {
/* 1486 */       this.proxyQueueManager.notifyConnectionFailure(asyncFailure);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void setDispatchThread(RemoteDispatchThread dispatchThread) {
/* 1495 */     if (Trace.isOn) {
/* 1496 */       Trace.data(this, "setDispatchThread(RemoteDispatchThread)", "setter", dispatchThread);
/*      */     }
/*      */     
/* 1499 */     this.dispatchThread = dispatchThread;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setShareOption(int shareOption) {
/* 1509 */     if (Trace.isOn) {
/* 1510 */       Trace.data(this, "setShareOption(int)", "setter", 
/* 1511 */           Integer.valueOf(shareOption));
/*      */     }
/*      */     
/* 1514 */     this.shareOption = shareOption;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getShareOption() {
/* 1523 */     if (Trace.isOn) {
/* 1524 */       Trace.data(this, "getShareOption()", "getter", 
/* 1525 */           Integer.valueOf(this.shareOption));
/*      */     }
/* 1527 */     return this.shareOption;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1535 */     if (Trace.isOn) {
/* 1536 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "toString()");
/*      */     }
/* 1538 */     String traceRet1 = (this.session != null) ? this.session.toString() : "RemoteHconn: No Session";
/* 1539 */     if (Trace.isOn) {
/* 1540 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "toString()", traceRet1);
/*      */     }
/* 1542 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getConnectionId() throws JmqiException {
/* 1553 */     byte[] traceRet1 = getSession().getConnectionId();
/* 1554 */     if (Trace.isOn) {
/* 1555 */       Trace.data(this, "getConnectionId()", "getter", traceRet1);
/*      */     }
/* 1557 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getConnectionIdAsString() throws JmqiException {
/* 1568 */     String traceRet1 = getSession().getConnectionIdAsString();
/* 1569 */     if (Trace.isOn) {
/* 1570 */       Trace.data(this, "getConnectionIdAsString()", "getter", traceRet1);
/*      */     }
/*      */     
/* 1573 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RemoteSession getSession() throws JmqiException {
/* 1583 */     RemoteSession traceRet1 = getSession(null);
/* 1584 */     if (Trace.isOn) {
/* 1585 */       Trace.data(this, "getSession()", "getter", traceRet1);
/*      */     }
/* 1587 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RemoteSession getSession(RemoteProxyQueue proxyQueue) throws JmqiException {
/* 1597 */     if (Trace.isOn) {
/* 1598 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "getSession(RemoteProxyQueue)", new Object[] { proxyQueue });
/*      */     }
/*      */ 
/*      */     
/* 1602 */     RemoteTls remoteTls = this.fap.getTls();
/*      */     
/* 1604 */     checkForReconnectIfNecessary(proxyQueue, null, remoteTls);
/*      */     
/* 1606 */     if (Trace.isOn) {
/* 1607 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "getSession(RemoteProxyQueue)", this.session);
/*      */     }
/* 1609 */     return this.session;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void checkForReconnectIfNecessary(RemoteProxyQueue proxyQueue, RemoteParentHconn parent, RemoteTls remoteTls) throws JmqiException {
/* 1619 */     if (Trace.isOn) {
/* 1620 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkForReconnectIfNecessary(RemoteProxyQueue, RemoteParentHconn, RemoteTls)", new Object[] { proxyQueue, parent, remoteTls });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1625 */     if (remoteTls.isReconnectThread) {
/* 1626 */       if (Trace.isOn) {
/* 1627 */         Trace.data(this, "checkForReconnectIfNecessary(RemoteProxyQueue, RemoteParentHconn, RemoteTls)", "according to the tls this is the reconnect thread so would not wait", remoteTls);
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1632 */     else if (remoteTls.isReceiveThread) {
/* 1633 */       if (Trace.isOn) {
/* 1634 */         Trace.data(this, "checkForReconnectIfNecessary(RemoteProxyQueue, RemoteParentHconn, RemoteTls)", "according to the tls this is the receive thread so would not wait", remoteTls);
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1639 */       if (Trace.isOn) {
/* 1640 */         Trace.data(this, "checkForReconnectIfNecessary(RemoteProxyQueue, RemoteParentHconn, RemoteTls)", "according to the tls this is NEITHER the reconnect NOR the current receive thread so will wait if necessary", remoteTls);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1645 */       checkForReconnect(proxyQueue, parent);
/*      */     } 
/*      */ 
/*      */     
/* 1649 */     if (Trace.isOn) {
/* 1650 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkForReconnectIfNecessary(RemoteProxyQueue, RemoteParentHconn, RemoteTls)");
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
/*      */   protected void checkForReconnect(RemoteProxyQueue proxyQueue, RemoteParentHconn parent) throws JmqiException {
/* 1662 */     if (Trace.isOn) {
/* 1663 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkForReconnect(RemoteProxyQueue, RemoteParentHconn)", new Object[] { proxyQueue, parent });
/*      */     }
/*      */     
/* 1666 */     RemoteTls tls = this.fap.getTls();
/*      */     
/* 1668 */     if (!tls.needToCheckForReconnect) {
/* 1669 */       if (Trace.isOn) {
/* 1670 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkForReconnect(RemoteProxyQueue, RemoteParentHconn)", 0);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1675 */     this.waitedOnReconnect = false;
/*      */     
/* 1677 */     RemoteParentHconn reconnectionHconn = isaParentHconn() ? (RemoteParentHconn)this : getParent();
/*      */     
/* 1679 */     boolean isReconnectionInProgress = reconnectionHconn.isReconnecting();
/* 1680 */     if (isReconnectionInProgress) {
/*      */       
/* 1682 */       if (Trace.isOn) {
/* 1683 */         Trace.data(this, "checkForReconnect(RemoteProxyQueue, RemoteParentHconn)", "reconnection in progress, so waiting", null);
/*      */       }
/*      */       
/* 1686 */       boolean needToRegainProxyQueueMutex = false;
/*      */       
/* 1688 */       if (proxyQueue != null && proxyQueue.haveMutex()) {
/* 1689 */         proxyQueue.releaseMutex();
/* 1690 */         needToRegainProxyQueueMutex = true;
/*      */       } 
/*      */       
/* 1693 */       if (parent != null) {
/* 1694 */         parent.releaseReconnectInitiationChildLock();
/*      */       }
/*      */       
/* 1697 */       reconnectionHconn.waitForReconnectComplete();
/*      */       
/* 1699 */       if (parent != null) {
/* 1700 */         parent.acquireReconnectInitiationChildLock();
/*      */       }
/*      */       
/* 1703 */       if (needToRegainProxyQueueMutex) {
/* 1704 */         proxyQueue.requestMutex(tls);
/*      */       }
/*      */       
/* 1707 */       this.waitedOnReconnect = true;
/*      */     } 
/* 1709 */     if (Trace.isOn) {
/* 1710 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkForReconnect(RemoteProxyQueue, RemoteParentHconn)", 1);
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
/*      */   public RemoteSession getSessionNoWait() {
/* 1722 */     if (Trace.isOn) {
/* 1723 */       Trace.data(this, "getSessionNoWait()", "getter", this.session);
/*      */     }
/* 1725 */     return this.session;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSession(RemoteSession session) {
/* 1735 */     if (Trace.isOn) {
/* 1736 */       Trace.data(this, "setSession(RemoteSession)", "setter", session);
/*      */     }
/* 1738 */     this.session = session;
/* 1739 */     this.session.setHconn(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getQmName() {
/* 1748 */     if (Trace.isOn) {
/* 1749 */       Trace.data(this, "getQmName()", "getter", this.qmName);
/*      */     }
/* 1751 */     return this.qmName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setQmName(String qmName) {
/* 1758 */     if (Trace.isOn) {
/* 1759 */       Trace.data(this, "setQmName()", "setter", qmName);
/*      */     }
/* 1761 */     this.qmName = qmName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCNO getConnectionOptions() {
/* 1770 */     if (Trace.isOn) {
/* 1771 */       Trace.data(this, "getConnectionOptions()", "getter", this.connectOptions);
/*      */     }
/*      */     
/* 1774 */     return this.connectOptions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JmqiConnectOptions getJmqiConnectionOptions() {
/* 1783 */     if (Trace.isOn) {
/* 1784 */       Trace.data(this, "getJmqiConnectionOptions()", "getter", this.jmqiConnectOptions);
/*      */     }
/*      */     
/* 1787 */     return this.jmqiConnectOptions;
/*      */   }
/*      */   
/*      */   void setInactive() {
/* 1791 */     if (Trace.isOn) {
/* 1792 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setInactive()");
/*      */     }
/* 1794 */     if (this.dispatchThread != null) {
/* 1795 */       this.dispatchThread.setHconnInactive();
/* 1796 */       setDispatchThread(null);
/*      */     } 
/* 1798 */     if (Trace.isOn) {
/* 1799 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setInactive()");
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
/*      */   boolean addHobj(RemoteHobj hobj) {
/* 1813 */     if (Trace.isOn) {
/* 1814 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "addHobj(RemoteHobj)", new Object[] { hobj });
/*      */     }
/* 1816 */     boolean retval = this.hobjs.add(hobj);
/*      */     
/* 1818 */     if (Trace.isOn) {
/* 1819 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "addHobj(RemoteHobj)", Boolean.valueOf(retval));
/*      */     }
/* 1821 */     return retval;
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
/*      */   boolean addHsub(RemoteHsub hsub) {
/* 1833 */     if (Trace.isOn) {
/* 1834 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "addHsub(RemoteHsub)", new Object[] { hsub });
/*      */     }
/* 1836 */     boolean retval = this.hsubs.add(hsub);
/*      */     
/* 1838 */     if (Trace.isOn) {
/* 1839 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "addHsub(RemoteHsub)", Boolean.valueOf(retval));
/*      */     }
/* 1841 */     return retval;
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
/*      */   public void raiseEventForReason(int reason) throws JmqiException {
/* 1858 */     if (Trace.isOn) {
/* 1859 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "raiseEventForReason(int)", new Object[] {
/* 1860 */             Integer.valueOf(reason)
/*      */           });
/*      */     }
/* 1863 */     RemoteConstants.Event ed = RemoteConstants.Event.lookupByReason(reason);
/*      */ 
/*      */     
/* 1866 */     if (ed == null) {
/*      */       
/* 1868 */       if (Trace.isOn) {
/* 1869 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "raiseEventForReason(int)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1875 */     if (this.eventsHad.contains(ed)) {
/* 1876 */       if (Trace.isOn) {
/* 1877 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "raiseEventForReason(int)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1883 */     if (isReconnectable() && !ed.getRaiseReconnecting()) {
/* 1884 */       if (Trace.isOn) {
/* 1885 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "raiseEventForReason(int)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1890 */     if (this.proxyQueueManager != null) {
/* 1891 */       this.proxyQueueManager.raiseEvent(ed);
/*      */     }
/*      */ 
/*      */     
/* 1895 */     this.eventsHad.add(ed);
/*      */ 
/*      */     
/* 1898 */     if (this.ehObject != null) {
/* 1899 */       checkDispatchable(null);
/*      */     }
/*      */     
/* 1902 */     if (Trace.isOn) {
/* 1903 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "raiseEventForReason(int)", 4);
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
/*      */   boolean removeHobj(Hobj hobj) {
/* 1917 */     if (Trace.isOn) {
/* 1918 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "removeHobj(Hobj)", new Object[] { hobj });
/*      */     }
/* 1920 */     boolean retval = false;
/* 1921 */     if (hobj instanceof RemoteHobj) {
/* 1922 */       RemoteHobj remoteHobj = (RemoteHobj)hobj;
/* 1923 */       remoteHobj.setLogicallyClosed(true);
/* 1924 */       RemoteHsub parentHsub = remoteHobj.getParentHsub();
/* 1925 */       if (parentHsub == null) {
/* 1926 */         retval = this.hobjs.remove(hobj);
/*      */       
/*      */       }
/* 1929 */       else if (parentHsub.isLogicallyClosed()) {
/* 1930 */         this.hsubs.remove(parentHsub);
/* 1931 */         retval = this.hobjs.remove(hobj);
/*      */       } else {
/*      */         
/* 1934 */         retval = true;
/*      */       }
/*      */     
/*      */     }
/* 1938 */     else if (hobj instanceof RemoteHsub) {
/* 1939 */       RemoteHsub remoteHsub = (RemoteHsub)hobj;
/* 1940 */       remoteHsub.setLogicallyClosed(true);
/* 1941 */       RemoteHobj childHobj = remoteHsub.getHobj();
/* 1942 */       if (childHobj == null) {
/* 1943 */         retval = this.hsubs.remove(hobj);
/*      */       
/*      */       }
/* 1946 */       else if (childHobj.isLogicallyClosed()) {
/* 1947 */         this.hobjs.remove(childHobj);
/* 1948 */         retval = this.hsubs.remove(hobj);
/*      */       } else {
/*      */         
/* 1951 */         retval = true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1956 */     if (Trace.isOn) {
/* 1957 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "removeHobj(Hobj)", Boolean.valueOf(retval));
/*      */     }
/* 1959 */     return retval;
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
/*      */   public void initiateReconnection(RemoteSession failedSession) throws JmqiException {
/* 1972 */     if (Trace.isOn) {
/* 1973 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "initiateReconnection(RemoteSession)", new Object[] { failedSession });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1983 */     if (this.waitedOnReconnect) {
/* 1984 */       if (Trace.isOn) {
/* 1985 */         Trace.data(this, "initiateReconnection(RemoteSession)", "We've already been reconnected while waiting in the leaveCall() method", null);
/*      */       }
/* 1987 */       this.waitedOnReconnect = false;
/*      */ 
/*      */     
/*      */     }
/* 1991 */     else if (!(this.fap.getTls()).isReconnectThread) {
/* 1992 */       RemoteParentHconn reconnectionController = null;
/*      */       
/* 1994 */       if (isaParentHconn()) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1999 */         reconnectionController = (RemoteParentHconn)this;
/* 2000 */         if (!eligibleForReconnect(true)) {
/* 2001 */           JmqiException traceRet = new JmqiException(this.env, -1, null, 2, 2548, null);
/*      */ 
/*      */           
/* 2004 */           if (Trace.isOn) {
/* 2005 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "initiateReconnection(RemoteSession)", (Throwable)traceRet, 1);
/*      */           }
/*      */           
/* 2008 */           throw traceRet;
/*      */         }
/*      */       
/*      */       }
/* 2012 */       else if (this.reconnectionState.isReconnectable()) {
/* 2013 */         reconnectionController = getParent();
/* 2014 */         reconnectionController.eligibleForReconnect(true);
/*      */       } else {
/*      */         
/* 2017 */         JmqiException traceRet = new JmqiException(this.env, -1, null, 2, 2548, null);
/*      */ 
/*      */         
/* 2020 */         if (Trace.isOn) {
/* 2021 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "initiateReconnection(RemoteSession)", (Throwable)traceRet, 2);
/*      */         }
/*      */         
/* 2024 */         throw traceRet;
/*      */       } 
/*      */ 
/*      */       
/* 2028 */       reconnectionController.waitForReconnectComplete();
/*      */       
/* 2030 */       if (reconnectionController.getReconnectionFailed()) {
/* 2031 */         if (Trace.isOn) {
/* 2032 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "initiateReconnection(RemoteSession)", (Throwable)reconnectionController
/* 2033 */               .getReconnectionFailureException(), 2);
/*      */         }
/* 2035 */         throw reconnectionController.getReconnectionFailureException();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2040 */     if (Trace.isOn) {
/* 2041 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "initiateReconnection(RemoteSession)");
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
/*      */   public void deliverConnectionBroken(Throwable e, String qmName2, String qmId) throws JmqiException {
/* 2054 */     if (Trace.isOn) {
/* 2055 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "deliverConnectionBroken(Throwable,String,String)", new Object[] { e, qmName2, qmId });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2060 */     if (qmName2 != null && qmId != null && 
/* 2061 */       !qmName2.isEmpty() && !qmId.isEmpty() && this.rebalancingListener != null) {
/* 2062 */       if (this instanceof RemoteParentHconn) {
/*      */         
/* 2064 */         RebalancingRequest rebalancingRequest = new RebalancingRequest(qmName2, qmId);
/* 2065 */         if (Log.isJeeOn()) {
/* 2066 */           Log.logNLS(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "deliverConnectionBroken(Throwable,String,String)", 
/* 2067 */               String.format("Reconnecting with request %s", new Object[] { String.valueOf(rebalancingRequest) }));
/*      */         }
/* 2069 */         this.rebalancingListener.onRebalancingRequest(rebalancingRequest);
/*      */       } 
/*      */     } else {
/*      */       
/* 2073 */       this.reconnectQmName = qmName2;
/* 2074 */       this.reconnectQmId = qmId;
/* 2075 */       boolean reconnectable1 = establishReconnectability(e);
/*      */ 
/*      */ 
/*      */       
/* 2079 */       if (!reconnectable1) {
/* 2080 */         this.acFlags |= 0x2;
/* 2081 */         raiseEventForReason(2009);
/* 2082 */         this.session.asyncFailureNotify(e);
/* 2083 */         wakeGetters();
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 2089 */         this.session.setAsyncFailure(e);
/* 2090 */         this.proxyQueueManager.notifyConnectionFailure(e);
/*      */       } 
/*      */     } 
/*      */     
/* 2094 */     if (Trace.isOn) {
/* 2095 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "deliverConnectionBroken(Throwable,String,String)");
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
/*      */   public boolean eligibleForReconnect(boolean register) throws JmqiException {
/* 2109 */     if (Trace.isOn) {
/* 2110 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "eligibleForReconnect(boolean)", new Object[] {
/* 2111 */             Boolean.valueOf(register)
/*      */           });
/*      */     }
/* 2114 */     if (this.reconnectionState.alreadyQueued()) {
/* 2115 */       if (Trace.isOn) {
/* 2116 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "eligibleForReconnect(boolean)", Boolean.TRUE, 0);
/*      */       }
/*      */ 
/*      */       
/* 2120 */       return true;
/*      */     } 
/*      */     
/* 2123 */     if (!this.reconnectionState.isReconnectable()) {
/* 2124 */       if (Trace.isOn) {
/* 2125 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "eligibleForReconnect(boolean)", Boolean.FALSE, 1);
/*      */       }
/*      */       
/* 2128 */       return false;
/*      */     } 
/*      */     
/* 2131 */     if (register && 
/* 2132 */       this.reconnectQmName == null)
/*      */     {
/*      */ 
/*      */       
/* 2136 */       getSessionNoWait().getParentConnection().invalidate(2009);
/*      */     }
/*      */ 
/*      */     
/* 2140 */     if (isaParentHconn()) {
/*      */       
/* 2142 */       boolean queued = getRemoteFap().getReconnectThread().eligibleForReconnect((RemoteParentHconn)this, register);
/* 2143 */       if (queued) {
/* 2144 */         this.reconnectionState.setQueued();
/*      */       }
/* 2146 */       if (Trace.isOn) {
/* 2147 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "eligibleForReconnect(boolean)", 
/* 2148 */             Boolean.valueOf(queued), 2);
/*      */       }
/* 2150 */       return queued;
/*      */     } 
/*      */ 
/*      */     
/* 2154 */     if (!register) {
/* 2155 */       setReconnectable(false);
/* 2156 */       if (Trace.isOn) {
/* 2157 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "eligibleForReconnect(boolean)", Boolean.FALSE, 3);
/*      */       }
/*      */       
/* 2160 */       return false;
/*      */     } 
/*      */     
/* 2163 */     if (Trace.isOn) {
/* 2164 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "eligibleForReconnect(boolean)", Boolean.TRUE, 4);
/*      */     }
/*      */ 
/*      */     
/* 2168 */     return true;
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
/*      */   public void enterCall(boolean isDispatchThread, boolean callAllowedWhenAsyncStarted) throws JmqiException {
/* 2182 */     if (Trace.isOn)
/* 2183 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "enterCall(boolean,boolean)", new Object[] {
/* 2184 */             Boolean.valueOf(isDispatchThread), Boolean.valueOf(callAllowedWhenAsyncStarted)
/*      */           }); 
/* 2186 */     enterCall(isDispatchThread, callAllowedWhenAsyncStarted, true);
/*      */     
/* 2188 */     if (Trace.isOn) {
/* 2189 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "enterCall(boolean,boolean)");
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
/*      */   public void enterCall(boolean isDispatchThread, boolean callAllowedWhenAsyncStarted, boolean doCheck) throws JmqiException {
/* 2208 */     if (Trace.isOn) {
/* 2209 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "enterCall(boolean,boolean,boolean)", new Object[] {
/* 2210 */             Boolean.valueOf(isDispatchThread), Boolean.valueOf(callAllowedWhenAsyncStarted), 
/* 2211 */             Boolean.valueOf(doCheck)
/*      */           });
/*      */     }
/* 2214 */     enterCall(this.callLock, isDispatchThread, callAllowedWhenAsyncStarted, doCheck, true);
/*      */     
/* 2216 */     if (Trace.isOn) {
/* 2217 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "enterCall(boolean,boolean,boolean)");
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
/*      */   public void enterNotifyCall(boolean isDispatchThread, boolean useReconnectLocks) throws JmqiException {
/* 2232 */     if (Trace.isOn)
/* 2233 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "enterNotifyCall(boolean, boolean)", new Object[] {
/* 2234 */             Boolean.valueOf(isDispatchThread), Boolean.valueOf(useReconnectLocks)
/*      */           }); 
/* 2236 */     enterCall(this.notifyLock, isDispatchThread, true, true, useReconnectLocks);
/*      */     
/* 2238 */     if (Trace.isOn) {
/* 2239 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "enterNotifyCall(boolean, boolean)");
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
/*      */   public int getReconnectionAttempts() {
/* 2257 */     if (Trace.isOn) {
/* 2258 */       Trace.data(this, "getReconnectionAttempts()", "getter", 
/* 2259 */           Integer.valueOf(this.reconnectAttempts));
/*      */     }
/* 2261 */     return this.reconnectAttempts;
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
/*      */   public void setReconnectionAttempts(int attempts) {
/* 2273 */     if (Trace.isOn) {
/* 2274 */       Trace.data(this, "setReconnectionAttempts(int)", "setter", 
/* 2275 */           Integer.valueOf(attempts));
/*      */     }
/* 2277 */     this.reconnectAttempts = attempts;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getNextReconnect() {
/* 2288 */     if (Trace.isOn) {
/* 2289 */       Trace.data(this, "getNextReconnect()", "getter", 
/* 2290 */           Long.valueOf(this.nextReconnect));
/*      */     }
/* 2292 */     return this.nextReconnect;
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
/*      */   public void setNextReconnect(long time) {
/* 2304 */     if (Trace.isOn) {
/* 2305 */       Trace.data(this, "setNextReconnect(long)", "setter", 
/* 2306 */           Long.valueOf(time));
/*      */     }
/* 2308 */     this.nextReconnect = time;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reconnected() {
/* 2315 */     if (Trace.isOn) {
/* 2316 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnected()");
/*      */     }
/*      */     
/* 2319 */     this.reconnectionState.reconnected();
/* 2320 */     this.applicableReconnectCycle = this.session.getReconnectCycle();
/*      */     
/* 2322 */     if (this.reconnectionListener != null) {
/* 2323 */       this.reconnectionListener.reconnected();
/*      */     }
/*      */     
/* 2326 */     if (Trace.isOn) {
/* 2327 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnected()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasFailed() {
/* 2338 */     if (Trace.isOn) {
/* 2339 */       Trace.data(this, "hasFailed()", "getter", Boolean.valueOf(this.reconnectionState.failed()));
/*      */     }
/* 2341 */     return this.reconnectionState.failed();
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
/*      */   void setupCallback(int operation) {
/* 2353 */     if (Trace.isOn) {
/* 2354 */       Trace.data(this, "setupCallback(int)", "setter", 
/* 2355 */           Integer.valueOf(operation));
/*      */     }
/*      */     
/* 2358 */     if (!this.reconnectionState.isInProgress()) {
/* 2359 */       switch (operation) {
/*      */         case 1:
/*      */         case 131072:
/* 2362 */           this.callbackStarted = true;
/* 2363 */           this.callbackSuspended = false;
/*      */           break;
/*      */         case 4:
/* 2366 */           this.callbackStarted = false;
/* 2367 */           this.callbackSuspended = false;
/*      */           break;
/*      */         case 65536:
/* 2370 */           this.callbackSuspended = true;
/*      */           break;
/*      */       } 
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
/*      */   void setupEventHandler(MQCBD eventDesc, int operation) {
/* 2390 */     if (Trace.isOn) {
/* 2391 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setupEventHandler(MQCBD,int)", new Object[] { eventDesc, 
/* 2392 */             Integer.valueOf(operation) });
/*      */     }
/* 2394 */     this.eventDesc = eventDesc;
/* 2395 */     this.eventRegistered = ((operation & 0x100) != 0);
/* 2396 */     this.eventSuspended = ((operation & 0x10000) != 0);
/*      */     
/* 2398 */     if (Trace.isOn) {
/* 2399 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setupEventHandler(MQCBD,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCallbackStarted() {
/* 2409 */     if (Trace.isOn) {
/* 2410 */       Trace.data(this, "isCallbackStarted()", "getter", 
/* 2411 */           Boolean.valueOf(this.callbackStarted));
/*      */     }
/* 2413 */     return this.callbackStarted;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCallbackSuspended() {
/* 2421 */     if (Trace.isOn) {
/* 2422 */       Trace.data(this, "isCallbackSuspended()", "getter", 
/* 2423 */           Boolean.valueOf(this.callbackSuspended));
/*      */     }
/* 2425 */     return this.callbackSuspended;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkUsable(RemoteParentHconn parent) throws JmqiException {
/* 2436 */     if (Trace.isOn) {
/* 2437 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkUsable()");
/*      */     }
/*      */     
/* 2440 */     if (!isReconnectable()) {
/*      */       
/* 2442 */       if (Trace.isOn) {
/* 2443 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkUsable()", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 2448 */     RemoteTls remoteTls = this.fap.getTls();
/*      */     
/* 2450 */     checkForReconnectIfNecessary(null, parent, remoteTls);
/*      */     
/* 2452 */     if (Trace.isOn) {
/* 2453 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "checkUsable()", 3);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getOriginalQueueManagerName() {
/* 2463 */     if (Trace.isOn) {
/* 2464 */       Trace.data(this, "getOriginalQueueManagerName()", "getter", this.originalQueueManagerName);
/*      */     }
/*      */     
/* 2467 */     return this.originalQueueManagerName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOriginalQueueManagerNameOnceOnly(String name) throws JmqiException {
/* 2475 */     if (this.originalQueueManagerName == null) {
/* 2476 */       this.originalQueueManagerName = name;
/* 2477 */       if (Trace.isOn) {
/* 2478 */         Trace.data(this, "setOriginalQueueManagerNameOnceOnly(String)", "setter", name);
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 2483 */     else if (Trace.isOn) {
/* 2484 */       Trace.data(this, "setOriginalQueueManagerNameOnceOnly(String)", "already set, not updated");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isReconnectable() {
/* 2494 */     if (Trace.isOn) {
/* 2495 */       Trace.data(this, "isReconnectable()", "getter", 
/* 2496 */           Boolean.valueOf(this.reconnectionState.isReconnectable()));
/*      */     }
/* 2498 */     return this.reconnectionState.isReconnectable();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReconnectable(boolean reconnectable) {
/* 2508 */     if (Trace.isOn) {
/* 2509 */       Trace.data(this, "setReconnectable(boolean)", "setter", 
/* 2510 */           Boolean.valueOf(reconnectable));
/*      */     }
/* 2512 */     this.reconnectionState.setReconnectable(reconnectable);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isReconnecting() {
/* 2519 */     if (Trace.isOn) {
/* 2520 */       Trace.data(this, "isReconnecting()", "getter", 
/* 2521 */           Boolean.valueOf(this.reconnectionState.isInProgress()));
/*      */     }
/* 2523 */     return this.reconnectionState.isInProgress();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEventRegistered() {
/* 2530 */     if (Trace.isOn) {
/* 2531 */       Trace.data(this, "isEventRegistered()", "getter", 
/* 2532 */           Boolean.valueOf(this.eventRegistered));
/*      */     }
/* 2534 */     return this.eventRegistered;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEventSuspended() {
/* 2541 */     if (Trace.isOn) {
/* 2542 */       Trace.data(this, "isEventSuspended()", "getter", 
/* 2543 */           Boolean.valueOf(this.eventSuspended));
/*      */     }
/* 2545 */     return this.eventSuspended;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCBD getEventDescriptor() {
/* 2552 */     if (Trace.isOn) {
/* 2553 */       Trace.data(this, "getEventDescriptor()", "getter", this.eventDesc);
/*      */     }
/* 2555 */     return this.eventDesc;
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
/*      */   public void setReconnectionFailure(int compcode, int reason, JmqiException e) {
/* 2569 */     if (Trace.isOn) {
/* 2570 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setReconnectionFailure(int,int,JmqiException)", new Object[] {
/* 2571 */             Integer.valueOf(compcode), Integer.valueOf(reason), e
/*      */           });
/*      */     }
/* 2574 */     setReconnectionFailureInner(compcode, reason, e);
/*      */ 
/*      */     
/*      */     try {
/* 2578 */       callEventHandler(5, 2, reason);
/*      */     }
/* 2580 */     catch (JmqiException e1) {
/* 2581 */       if (Trace.isOn) {
/* 2582 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setReconnectionFailure(int,int,JmqiException)", (Throwable)e1);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2588 */     if (Trace.isOn) {
/* 2589 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setReconnectionFailure(int,int,JmqiException)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setReconnectionFailureInner(int compcode, int reason, JmqiException e) {
/* 2595 */     if (Trace.isOn)
/* 2596 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setReconnectionFailureInner(int,int,JmqiException)", new Object[] {
/* 2597 */             Integer.valueOf(compcode), Integer.valueOf(reason), e
/*      */           }); 
/* 2599 */     if (this.session != null)
/*      */     {
/* 2601 */       this.session.setHconn(null);
/*      */     }
/*      */     
/* 2604 */     this.reconnectionState.recordFailure(compcode, reason, e);
/*      */     
/* 2606 */     if (Trace.isOn) {
/* 2607 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setReconnectionFailureInner(int,int,JmqiException)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getReconnectionFailed() {
/* 2615 */     if (Trace.isOn) {
/* 2616 */       Trace.data(this, "getReconnectionFailed()", "getter", 
/* 2617 */           Boolean.valueOf(this.reconnectionState.failed()));
/*      */     }
/* 2619 */     return this.reconnectionState.failed();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReconnectionFailureCompCode() {
/* 2626 */     if (Trace.isOn) {
/* 2627 */       Trace.data(this, "getReconnectionFailureCompCode()", "getter", 
/* 2628 */           Integer.valueOf(this.reconnectionState.failureCompCode()));
/*      */     }
/* 2630 */     return this.reconnectionState.failureCompCode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReconnectionFailureReason() {
/* 2637 */     if (Trace.isOn) {
/* 2638 */       Trace.data(this, "getReconnectionFailureReason()", "getter", 
/* 2639 */           Integer.valueOf(this.reconnectionState.failureReason()));
/*      */     }
/* 2641 */     return this.reconnectionState.failureReason();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JmqiException getReconnectionFailureException() {
/* 2648 */     if (Trace.isOn) {
/* 2649 */       Trace.data(this, "getReconnectionFailureException()", "getter", this.reconnectionState
/* 2650 */           .failureException());
/*      */     }
/* 2652 */     return this.reconnectionState.failureException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getReconnectionTimeout() {
/* 2659 */     if (Trace.isOn) {
/* 2660 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "getReconnectionTimeout()");
/*      */     }
/* 2662 */     if (this.jmqiConnectOptions != null) {
/* 2663 */       int traceRet1 = this.jmqiConnectOptions.getReconnectionTimeout();
/*      */       
/* 2665 */       if (Trace.isOn) {
/* 2666 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "getReconnectionTimeout()", 
/* 2667 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/* 2669 */       return traceRet1;
/*      */     } 
/* 2671 */     if (Trace.isOn) {
/* 2672 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "getReconnectionTimeout()", 
/* 2673 */           Integer.valueOf(1800), 2);
/*      */     }
/* 2675 */     return 1800;
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
/* 2686 */     if (Trace.isOn) {
/* 2687 */       Trace.data(this, "getConnectionArea()", "getter", this.connectionArea);
/*      */     }
/* 2689 */     return this.connectionArea;
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
/* 2700 */     if (Trace.isOn) {
/* 2701 */       Trace.data(this, "setConnectionArea(Object)", "setter", object);
/*      */     }
/* 2703 */     this.connectionArea = object;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class NotifyLock
/*      */     extends HconnLock
/*      */   {
/*      */     private static final long serialVersionUID = -801761646115456862L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     NotifyLock(RemoteFAP fap) {
/* 2718 */       super(fap);
/* 2719 */       if (Trace.isOn) {
/* 2720 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.NotifyLock", "<init>(RemoteTls)", new Object[] { fap });
/*      */       }
/* 2722 */       if (Trace.isOn) {
/* 2723 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.NotifyLock", "<init>(RemoteTls)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class CallLock
/*      */     extends HconnLock
/*      */   {
/*      */     private static final long serialVersionUID = 765753709679835473L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     CallLock(RemoteFAP fap) {
/* 2741 */       super(fap);
/* 2742 */       if (Trace.isOn) {
/* 2743 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.CallLock", "<init>(RemoteTls)", new Object[] { fap });
/*      */       }
/* 2745 */       if (Trace.isOn) {
/* 2746 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.CallLock", "<init>(RemoteTls)");
/*      */       }
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
/*      */   private static class DispatchEventSync
/*      */   {
/*      */     DispatchEventSync() {
/* 2762 */       if (Trace.isOn) {
/* 2763 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.DispatchEventSync", "<init>()");
/*      */       }
/* 2765 */       if (Trace.isOn) {
/* 2766 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.DispatchEventSync", "<init>()");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class DispatchLock
/*      */     extends HconnLock
/*      */   {
/*      */     private static final long serialVersionUID = -1502018434007077311L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     DispatchLock(RemoteFAP fap) {
/* 2784 */       super(fap);
/* 2785 */       if (Trace.isOn) {
/* 2786 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.DispatchLock", "<init>(RemoteTls)", new Object[] { fap });
/*      */       }
/* 2788 */       if (Trace.isOn) {
/* 2789 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.DispatchLock", "<init>(RemoteTls)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCcsid() throws JmqiException {
/* 2800 */     int traceRet1 = 0;
/*      */     try {
/* 2802 */       traceRet1 = getSession().getCcsid();
/*      */     }
/* 2804 */     catch (JmqiException e) {
/* 2805 */       if (shouldReconnect(e)) {
/*      */         try {
/* 2807 */           initiateReconnection(this.session);
/* 2808 */           traceRet1 = getCcsid();
/*      */         }
/* 2810 */         catch (JmqiException je) {
/* 2811 */           if (Trace.isOn) {
/* 2812 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "getCcsid()", (Throwable)je);
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2819 */     if (Trace.isOn) {
/* 2820 */       Trace.data(this, "getCcsid()", "getter", 
/* 2821 */           Integer.valueOf(traceRet1));
/*      */     }
/* 2823 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCmdLevel() throws JmqiException {
/* 2831 */     int traceRet1 = 0;
/*      */     try {
/* 2833 */       traceRet1 = getSession().getCmdLevel();
/*      */     }
/* 2835 */     catch (JmqiException e) {
/* 2836 */       if (shouldReconnect(e)) {
/*      */         try {
/* 2838 */           initiateReconnection(this.session);
/* 2839 */           traceRet1 = getCmdLevel();
/*      */         }
/* 2841 */         catch (JmqiException je) {
/* 2842 */           if (Trace.isOn) {
/* 2843 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "getCmdLevel()", (Throwable)je);
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2850 */     if (Trace.isOn) {
/* 2851 */       Trace.data(this, "getCmdLevel()", "getter", 
/* 2852 */           Integer.valueOf(traceRet1));
/*      */     }
/* 2854 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isXASupported() {
/* 2865 */     if (Trace.isOn) {
/* 2866 */       Trace.data(this, "isXASupported()", "getter", 
/* 2867 */           Boolean.valueOf(true));
/*      */     }
/* 2869 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isConnToZos() throws JmqiException {
/* 2878 */     boolean retval = false;
/*      */     
/* 2880 */     String remProd = getParentConnection().getRemoteProductId();
/* 2881 */     if (remProd != null && remProd.startsWith("MQMV")) {
/* 2882 */       retval = true;
/*      */     }
/*      */     
/* 2885 */     if (Trace.isOn) {
/* 2886 */       Trace.data(this, "isConnToZos()", "getter", 
/* 2887 */           Boolean.valueOf(retval));
/*      */     }
/* 2889 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRemoteProductId() throws JmqiException {
/* 2898 */     String retval = getParentConnection().getRemoteProductId();
/* 2899 */     if (Trace.isOn) {
/* 2900 */       Trace.data(this, "getRemoteProductId()", "getter", retval);
/*      */     }
/* 2902 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   RemoteTagPool getIdTagPool() throws JmqiException {
/* 2911 */     RemoteTagPool traceRet1 = getSession().getIdTagPool();
/* 2912 */     if (Trace.isOn) {
/* 2913 */       Trace.data(this, "getIdTagPool()", "getter", traceRet1);
/*      */     }
/* 2915 */     return traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void wakeGetters() {
/* 2920 */     if (Trace.isOn) {
/* 2921 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "wakeGetters()");
/*      */     }
/* 2923 */     if (this.proxyQueueManager != null) {
/* 2924 */       this.proxyQueueManager.wakeGetters();
/*      */     }
/*      */     
/* 2927 */     if (Trace.isOn) {
/* 2928 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "wakeGetters()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void qmQuiescing() throws JmqiException {
/* 2938 */     if (Trace.isOn) {
/* 2939 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "qmQuiescing()");
/*      */     }
/* 2941 */     this.acFlags |= 0x100;
/* 2942 */     if (!isReconnectable()) {
/* 2943 */       raiseEventForReason(2161);
/* 2944 */       wakeGetters();
/*      */     } 
/*      */     
/* 2947 */     if (Trace.isOn) {
/* 2948 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "qmQuiescing()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void chlQuiescing() throws JmqiException {
/* 2958 */     if (Trace.isOn) {
/* 2959 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "chlQuiescing()");
/*      */     }
/* 2961 */     this.acFlags |= 0x100;
/* 2962 */     if (!isReconnectable()) {
/* 2963 */       raiseEventForReason(2202);
/* 2964 */       wakeGetters();
/*      */     } 
/*      */     
/* 2967 */     if (Trace.isOn) {
/* 2968 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "chlQuiescing()");
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
/*      */   private void callEventHandler(int callType, int compCode, int reason) throws JmqiException {
/* 2987 */     if (Trace.isOn)
/* 2988 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "callEventHandler(int,int,int)", new Object[] {
/* 2989 */             Integer.valueOf(callType), Integer.valueOf(compCode), Integer.valueOf(reason)
/*      */           }); 
/* 2991 */     if (isReconnectable() && isReconnectableReasonCode(reason)) {
/*      */ 
/*      */       
/* 2994 */       if (Trace.isOn) {
/* 2995 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "callEventHandler(int,int,int)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 3000 */     if (this.ehObject == null) {
/*      */ 
/*      */       
/* 3003 */       if (Trace.isOn) {
/* 3004 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "callEventHandler(int,int,int)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 3009 */     MQCBC cbc = this.env.newMQCBC();
/*      */ 
/*      */     
/* 3012 */     switch (callType) {
/*      */       case 3:
/*      */       case 4:
/*      */         break;
/*      */       
/*      */       case 5:
/* 3018 */         if ((this.acFlags & 0x400) != 0) {
/* 3019 */           if (Trace.isOn) {
/* 3020 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "callEventHandler(int,int,int)", 3);
/*      */           }
/*      */           return;
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       default:
/* 3029 */         if (Trace.isOn) {
/* 3030 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "callEventHandler(int,int,int)", 4);
/*      */         }
/*      */         return;
/*      */     } 
/*      */ 
/*      */     
/* 3036 */     cbc.setCallType(callType);
/* 3037 */     cbc.setCompCode(compCode);
/* 3038 */     cbc.setReason(reason);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3043 */     MQConsumer eh = (MQConsumer)this.ehObject;
/*      */     
/*      */     try {
/* 3046 */       eh.consumer(this, null, null, null, cbc);
/*      */     }
/* 3048 */     catch (Throwable throwable) {
/* 3049 */       if (Trace.isOn) {
/* 3050 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "callEventHandler(int,int,int)", throwable);
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
/* 3067 */     if (Trace.isOn) {
/* 3068 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "callEventHandler(int,int,int)", 5);
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
/*      */   void mqcbRegisterEH(MQCBD callbackDesc) throws JmqiException {
/* 3083 */     if (Trace.isOn) {
/* 3084 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "mqcbRegisterEH(MQCBD)", new Object[] { callbackDesc });
/*      */     }
/*      */     
/* 3087 */     if (callbackDesc.getCallbackFunction() == null) {
/* 3088 */       callbackDesc.setCallbackFunction((MQConsumer)JmqiUtils.loadAndInstantiateClass(this.env, callbackDesc.getCallbackName()));
/*      */     }
/* 3090 */     this.ehObject = callbackDesc.getCallbackFunction();
/* 3091 */     this.ehOptions = callbackDesc.getOptions();
/*      */ 
/*      */ 
/*      */     
/* 3095 */     if ((this.ehOptions & 0x100) != 0) {
/* 3096 */       callEventHandler(3, 0, 0);
/*      */     }
/*      */     
/* 3099 */     if (Trace.isOn) {
/* 3100 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "mqcbRegisterEH(MQCBD)");
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
/*      */   void mqcbDeregisterEH() throws JmqiException {
/* 3112 */     if (Trace.isOn) {
/* 3113 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "mqcbDeregisterEH()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3118 */     if (this.ehObject == null) {
/* 3119 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 1, 2448, null);
/*      */ 
/*      */       
/* 3122 */       if (Trace.isOn) {
/* 3123 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "mqcbDeregisterEH()", (Throwable)traceRet1);
/*      */       }
/* 3125 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3131 */     if ((this.ehOptions & 0x200) != 0) {
/* 3132 */       callEventHandler(4, 0, 0);
/*      */     }
/*      */     
/* 3135 */     this.ehObject = null;
/*      */     
/* 3137 */     if (Trace.isOn) {
/* 3138 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "mqcbDeregisterEH()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void mqcbSuspendEH() {
/* 3147 */     if (Trace.isOn) {
/* 3148 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "mqcbSuspendEH()");
/*      */     }
/* 3150 */     this.acFlags |= 0x400;
/*      */     
/* 3152 */     if (Trace.isOn) {
/* 3153 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "mqcbSuspendEH()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void mqcbResumeEH() {
/* 3162 */     if (Trace.isOn) {
/* 3163 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "mqcbResumeEH()");
/*      */     }
/* 3165 */     this.acFlags &= 0xFFFFFBFF;
/*      */     
/* 3167 */     if (Trace.isOn) {
/* 3168 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "mqcbResumeEH()");
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
/*      */   private static class DispatchQMutex
/*      */   {
/*      */     DispatchQMutex() {
/* 3183 */       if (Trace.isOn) {
/* 3184 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.DispatchQMutex", "<init>()");
/*      */       }
/* 3186 */       if (Trace.isOn) {
/* 3187 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.DispatchQMutex", "<init>()");
/*      */       }
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
/*      */   public void addToDispatchList(RemoteProxyQueue pq) throws JmqiException {
/* 3202 */     if (Trace.isOn) {
/* 3203 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "addToDispatchList(RemoteProxyQueue)", new Object[] { pq });
/*      */     }
/*      */     
/* 3206 */     synchronized (this.dispatchQMutex) {
/* 3207 */       this.dispatchQueueList.add(pq);
/*      */     } 
/* 3209 */     if (Trace.isOn) {
/* 3210 */       Trace.data(this, "addToDispatchList(RemoteProxyQueue)", "added a queue - list is now " + this.dispatchQueueList
/* 3211 */           .size() + " queues", null);
/*      */     }
/*      */     
/* 3214 */     if (Trace.isOn) {
/* 3215 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "addToDispatchList(RemoteProxyQueue)");
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
/*      */   public void removeFromDispatchList(RemoteProxyQueue pq) throws JmqiException {
/* 3229 */     if (Trace.isOn) {
/* 3230 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "removeFromDispatchList(RemoteProxyQueue)", new Object[] { pq });
/*      */     }
/*      */     
/* 3233 */     synchronized (this.dispatchQMutex) {
/* 3234 */       pq.setLogicallyRemoved(true);
/* 3235 */       if (!this.dispatchQueueList.remove(pq)) {
/* 3236 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */ 
/*      */         
/* 3239 */         HashMap<String, Object> info = new HashMap<>();
/* 3240 */         info.put("Description", "Proxy queue is not associated with this conversation");
/* 3241 */         Trace.ffst(this, "removeFromDispatchList(RemoteProxyQueue)", "01", info, null);
/*      */         
/* 3243 */         if (Trace.isOn) {
/* 3244 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "removeFromDispatchList(RemoteProxyQueue)", (Throwable)traceRet1);
/*      */         }
/*      */         
/* 3247 */         throw traceRet1;
/*      */       } 
/*      */       
/* 3250 */       if (Trace.isOn) {
/* 3251 */         Trace.data(this, "removeFromDispatchList(RemoteProxyQueue)", "removed a queue - list is now " + this.dispatchQueueList
/* 3252 */             .size() + " queues", null);
/*      */       }
/*      */     } 
/*      */     
/* 3256 */     if (Trace.isOn) {
/* 3257 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "removeFromDispatchList(RemoteProxyQueue)");
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
/*      */   void startInit(boolean resume) throws JmqiException {
/* 3272 */     if (Trace.isOn)
/* 3273 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "startInit(boolean)", new Object[] {
/* 3274 */             Boolean.valueOf(resume)
/*      */           }); 
/* 3276 */     synchronized (this.dispatchQMutex) {
/*      */ 
/*      */       
/*      */       try {
/*      */         
/* 3281 */         for (RemoteProxyQueue pq : this.dispatchQueueList) {
/* 3282 */           pq.setNoMsgTime(0L);
/*      */         }
/*      */       }
/* 3285 */       catch (Exception e) {
/* 3286 */         if (Trace.isOn) {
/* 3287 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "startInit(boolean)", e);
/*      */         }
/*      */         
/* 3290 */         JmqiException je = new JmqiException(this.env, -1, null, 0, 2195, null);
/*      */         
/* 3292 */         HashMap<String, Object> info = new HashMap<>();
/* 3293 */         info.put("Description", "Error iterating through dispatch queue list");
/* 3294 */         Trace.ffst(this, "removeFromDispatchList(RemoteProxyQueue)", "01", info, null);
/* 3295 */         if (Trace.isOn) {
/* 3296 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "startInit(boolean)", (Throwable)je);
/*      */         }
/* 3298 */         throw je;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 3304 */     driveOutstanding();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3309 */     if ((this.acFlags & 0x40) != 0) {
/*      */ 
/*      */ 
/*      */       
/* 3313 */       sendConnState(false);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3318 */       checkDispatchable(null);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3323 */       checkTxnAllowed();
/*      */     } 
/* 3325 */     if (Trace.isOn) {
/* 3326 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "startInit(boolean)");
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
/*      */   void driveStops() throws JmqiException {
/* 3338 */     if (Trace.isOn) {
/* 3339 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "driveStops()");
/*      */     }
/*      */     
/* 3342 */     driveOutstanding();
/* 3343 */     synchronized (this.dispatchQMutex) {
/* 3344 */       for (RemoteProxyQueue pq : this.dispatchQueueList)
/*      */       {
/* 3346 */         pq.driveStop();
/*      */       }
/*      */     } 
/*      */     
/* 3350 */     if (Trace.isOn) {
/* 3351 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "driveStops()");
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
/*      */   public synchronized RemoteDispatchThread getDispatchThread() throws JmqiException {
/* 3365 */     if (this.dispatchThread == null) {
/*      */       
/* 3367 */       this.dispatchThread = new RemoteDispatchThread(this.env, getParentConnection().getRemoteFap(), this);
/* 3368 */       this.env.getThreadPoolFactory().getThreadPool().enqueue((Runnable)this.dispatchThread);
/*      */     } 
/*      */     
/* 3371 */     if (Trace.isOn) {
/* 3372 */       Trace.data(this, "getDispatchThread()", "getter", this.dispatchThread);
/*      */     }
/* 3374 */     return this.dispatchThread;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Iterable<RemoteProxyQueue> getDispatchQueueList() {
/* 3381 */     if (Trace.isOn) {
/* 3382 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "getDispatchQueueList()");
/*      */     }
/*      */     
/* 3385 */     Iterable<RemoteProxyQueue> result = null;
/* 3386 */     synchronized (this.dispatchQMutex) {
/* 3387 */       result = new ArrayList<>(this.dispatchQueueList);
/*      */     } 
/* 3389 */     if (Trace.isOn) {
/* 3390 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "getDispatchQueueList()", result);
/*      */     }
/* 3392 */     return result;
/*      */   }
/*      */   
/*      */   private boolean establishReconnectability(Throwable e) throws JmqiException {
/* 3396 */     return (isReconnectable() && e instanceof JmqiException && 
/*      */       
/* 3398 */       isReconnectableReasonCode(((JmqiException)e).getReason()) && 
/* 3399 */       eligibleForReconnect(true));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void doSuspend() throws JmqiException {
/* 3406 */     if (Trace.isOn) {
/* 3407 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doSuspend()");
/*      */     }
/*      */     
/* 3410 */     checkTxnMessage();
/*      */     
/* 3412 */     this.acFlags &= 0xFFEFFFFF;
/*      */     
/* 3414 */     postDispatchThreadEvent();
/*      */     
/* 3416 */     if (Trace.isOn) {
/* 3417 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doSuspend()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void doStop() throws JmqiException {
/* 3426 */     if (Trace.isOn) {
/* 3427 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doStop()");
/*      */     }
/*      */     
/* 3430 */     checkTxnMessage();
/*      */     
/* 3432 */     driveStops();
/*      */     
/* 3434 */     this.acFlags &= 0xFFFDFFFF;
/*      */     
/* 3436 */     setDispatchThread(null);
/*      */     
/* 3438 */     releaseDispatchLock();
/* 3439 */     postDispatchThreadEvent();
/*      */     
/* 3441 */     if (Trace.isOn) {
/* 3442 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doStop()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isQuiescing() {
/* 3451 */     boolean traceRet1 = ((this.acFlags & 0x100) != 0);
/* 3452 */     if (Trace.isOn) {
/* 3453 */       Trace.data(this, "isQuiescing()", "getter", 
/* 3454 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 3456 */     return traceRet1;
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
/*      */   public Iterable<RemoteHobj> getHobjs() {
/* 3468 */     if (Trace.isOn) {
/* 3469 */       Trace.data(this, "getHobjs()", "getter", this.hobjs);
/*      */     }
/* 3471 */     return this.hobjs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Iterable<RemoteHsub> getHsubs() {
/* 3478 */     if (Trace.isOn) {
/* 3479 */       Trace.data(this, "getHsubs()", "getter", this.hsubs);
/*      */     }
/* 3481 */     return this.hsubs;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setQuiescing() {
/* 3486 */     if (Trace.isOn) {
/* 3487 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setQuiescing()");
/*      */     }
/* 3489 */     this.acFlags |= 0x100;
/*      */     
/* 3491 */     if (Trace.isOn) {
/* 3492 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setQuiescing()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void unSetQuiescing() {
/* 3499 */     if (Trace.isOn) {
/* 3500 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "unSetQuiescing()");
/*      */     }
/* 3502 */     this.acFlags &= 0xFFFFFEFF;
/*      */     
/* 3504 */     if (Trace.isOn) {
/* 3505 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "unSetQuiescing()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isTransactionDoomed() {
/* 3514 */     boolean retval = ((this.acFlags & 0x8000000) != 0);
/* 3515 */     if (Trace.isOn) {
/* 3516 */       Trace.data(this, "isTransactionDoomed()", "getter", 
/* 3517 */           Boolean.valueOf(retval));
/*      */     }
/* 3519 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSuspended() {
/* 3526 */     boolean retval = ((this.acFlags & 0x80) != 0);
/* 3527 */     if (Trace.isOn) {
/* 3528 */       Trace.data(this, "isSuspended()", "getter", 
/* 3529 */           Boolean.valueOf(retval));
/*      */     }
/* 3531 */     return retval;
/*      */   }
/*      */ 
/*      */   
/*      */   void setSuspended() {
/* 3536 */     if (Trace.isOn) {
/* 3537 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setSuspended()");
/*      */     }
/* 3539 */     this.acFlags |= 0x80;
/*      */     
/* 3541 */     if (Trace.isOn) {
/* 3542 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setSuspended()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void unsetSuspended() {
/* 3549 */     if (Trace.isOn) {
/* 3550 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "unsetSuspended()");
/*      */     }
/* 3552 */     this.acFlags &= 0xFFFFFF7F;
/*      */     
/* 3554 */     if (Trace.isOn) {
/* 3555 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "unsetSuspended()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSuspending() {
/* 3564 */     boolean retval = this.suspendInProgress;
/* 3565 */     if (Trace.isOn) {
/* 3566 */       Trace.data(this, "isSuspending()", "getter", 
/* 3567 */           Boolean.valueOf(retval));
/*      */     }
/* 3569 */     return retval;
/*      */   }
/*      */ 
/*      */   
/*      */   void setSuspending() {
/* 3574 */     if (Trace.isOn) {
/* 3575 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setSuspending()");
/*      */     }
/* 3577 */     this.suspendInProgress = true;
/*      */     
/* 3579 */     if (Trace.isOn) {
/* 3580 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setSuspending()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void unsetSuspending() {
/* 3587 */     if (Trace.isOn) {
/* 3588 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "unsetSuspending()");
/*      */     }
/* 3590 */     this.suspendInProgress = false;
/*      */     
/* 3592 */     if (Trace.isOn) {
/* 3593 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "unsetSuspending()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isStarted() {
/* 3602 */     boolean retval = ((this.acFlags & 0x40) != 0);
/* 3603 */     if (Trace.isOn) {
/* 3604 */       Trace.data(this, "isStarted()", "getter", 
/* 3605 */           Boolean.valueOf(retval));
/*      */     }
/* 3607 */     return retval;
/*      */   }
/*      */ 
/*      */   
/*      */   void setStarted() {
/* 3612 */     if (Trace.isOn) {
/* 3613 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setStarted()");
/*      */     }
/* 3615 */     this.acFlags |= 0x40;
/*      */     
/* 3617 */     if (Trace.isOn) {
/* 3618 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setStarted()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsetStarted() {
/* 3625 */     if (Trace.isOn) {
/* 3626 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "unsetStarted()");
/*      */     }
/* 3628 */     this.acFlags &= 0xFFFFFFBF;
/*      */     
/* 3630 */     if (Trace.isOn) {
/* 3631 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "unsetStarted()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void setStopPending() {
/* 3638 */     if (Trace.isOn) {
/* 3639 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setStopPending()");
/*      */     }
/* 3641 */     this.acFlags |= 0x20000;
/*      */     
/* 3643 */     if (Trace.isOn) {
/* 3644 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setStopPending()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isXaConnected() {
/* 3653 */     boolean retval = ((this.acFlags & 0x8) != 0);
/* 3654 */     if (Trace.isOn) {
/* 3655 */       Trace.data(this, "isXaConnected()", "getter", 
/* 3656 */           Boolean.valueOf(retval));
/*      */     }
/* 3658 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean consumersChanged() {
/* 3665 */     if (Trace.isOn) {
/* 3666 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "consumersChanged()");
/*      */     }
/* 3668 */     boolean retval = ((this.acFlags & 0x200) != 0);
/*      */     
/* 3670 */     if (Trace.isOn) {
/* 3671 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "consumersChanged()", Boolean.valueOf(retval));
/*      */     }
/* 3673 */     return retval;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setConsumersChanged() {
/* 3678 */     if (Trace.isOn) {
/* 3679 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setConsumersChanged()");
/*      */     }
/* 3681 */     this.acFlags |= 0x200;
/*      */     
/* 3683 */     if (Trace.isOn) {
/* 3684 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setConsumersChanged()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean suspendPending() {
/* 3693 */     if (Trace.isOn) {
/* 3694 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "suspendPending()");
/*      */     }
/* 3696 */     boolean retval = ((this.acFlags & 0x100000) != 0);
/*      */     
/* 3698 */     if (Trace.isOn) {
/* 3699 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "suspendPending()", Boolean.valueOf(retval));
/*      */     }
/* 3701 */     return retval;
/*      */   }
/*      */ 
/*      */   
/*      */   void setSuspendPending() {
/* 3706 */     if (Trace.isOn) {
/* 3707 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setSuspendPending()");
/*      */     }
/* 3709 */     this.acFlags |= 0x100000;
/*      */     
/* 3711 */     if (Trace.isOn) {
/* 3712 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setSuspendPending()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void unsetSuspendPending() {
/* 3719 */     if (Trace.isOn) {
/* 3720 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "unsetSuspendPending()");
/*      */     }
/* 3722 */     this.acFlags &= 0xFFEFFFFF;
/*      */     
/* 3724 */     if (Trace.isOn) {
/* 3725 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "unsetSuspendPending()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean stopPending() {
/* 3734 */     if (Trace.isOn) {
/* 3735 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "stopPending()");
/*      */     }
/* 3737 */     boolean retval = ((this.acFlags & 0x20000) != 0);
/*      */     
/* 3739 */     if (Trace.isOn) {
/* 3740 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "stopPending()", Boolean.valueOf(retval));
/*      */     }
/* 3742 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean inTransaction() {
/* 3749 */     if (Trace.isOn) {
/* 3750 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "inTransaction()");
/*      */     }
/* 3752 */     boolean retval = ((this.acFlags & 0x4000000) != 0);
/*      */     
/* 3754 */     if (Trace.isOn) {
/* 3755 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "inTransaction()", Boolean.valueOf(retval));
/*      */     }
/* 3757 */     return retval;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setInTransaction() {
/* 3762 */     if (Trace.isOn) {
/* 3763 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setInTransaction()");
/*      */     }
/* 3765 */     this.acFlags |= 0x4000000;
/*      */     
/* 3767 */     if (Trace.isOn) {
/* 3768 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setInTransaction()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void unsetInTransaction() {
/* 3775 */     if (Trace.isOn) {
/* 3776 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "unsetInTransaction()");
/*      */     }
/* 3778 */     this.acFlags &= 0xFBFFFFFF;
/*      */     
/* 3780 */     if (Trace.isOn) {
/* 3781 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "unsetInTransaction()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTransactionDoomed() {
/* 3788 */     if (Trace.isOn) {
/* 3789 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setTransactionDoomed()");
/*      */     }
/* 3791 */     this.acFlags |= 0x8000000;
/*      */     
/* 3793 */     if (Trace.isOn) {
/* 3794 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setTransactionDoomed()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void unsetTransactionDoomed() {
/* 3801 */     if (Trace.isOn) {
/* 3802 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "unsetTransactionDoomed()");
/*      */     }
/* 3804 */     this.acFlags &= 0xF7FFFFFF;
/*      */     
/* 3806 */     if (Trace.isOn) {
/* 3807 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "unsetTransactionDoomed()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumSet<RemoteConstants.Event> getEventsHad() {
/* 3816 */     if (Trace.isOn) {
/* 3817 */       Trace.data(this, "getEventsHad()", "getter", this.eventsHad);
/*      */     }
/* 3819 */     return this.eventsHad;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPointerSize() {
/* 3827 */     if (Trace.isOn) {
/* 3828 */       Trace.data(this, "getPointerSize()", "getter", 
/* 3829 */           Integer.valueOf(4));
/*      */     }
/* 3831 */     return 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isByteSwap() throws JmqiException {
/* 3840 */     boolean traceRet1 = getSession().isSwap();
/* 3841 */     if (Trace.isOn) {
/* 3842 */       Trace.data(this, "isByteSwap()", "getter", 
/* 3843 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 3845 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JmqiCodepage getCodePage() throws JmqiException {
/* 3854 */     JmqiCodepage traceRet1 = getSession().getCp();
/* 3855 */     if (Trace.isOn) {
/* 3856 */       Trace.data(this, "getCodePage()", "getter", traceRet1);
/*      */     }
/* 3858 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getMaximumMessageLength() throws JmqiException {
/* 3866 */     long traceRet1 = getSession().getMaximumMessageLength();
/* 3867 */     if (Trace.isOn) {
/* 3868 */       Trace.data(this, "getMaximumMessageLength()", "getter", 
/* 3869 */           Long.valueOf(traceRet1));
/*      */     }
/* 3871 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSPISupported() throws JmqiException {
/* 3882 */     boolean traceRet1 = getSession().isSPISupported();
/*      */     
/* 3884 */     if (Trace.isOn) {
/* 3885 */       Trace.data(this, "isSPISupported()", "getter", 
/* 3886 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 3888 */     return traceRet1;
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
/*      */   public String getQsgName() throws JmqiException {
/* 3900 */     String traceRet = null;
/*      */     try {
/* 3902 */       traceRet = getSession().getQsgName();
/*      */     }
/* 3904 */     catch (JmqiException e) {
/* 3905 */       if (shouldReconnect(e)) {
/*      */         try {
/* 3907 */           initiateReconnection(this.session);
/* 3908 */           traceRet = getQsgName();
/*      */         }
/* 3910 */         catch (JmqiException je) {
/* 3911 */           if (Trace.isOn) {
/* 3912 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "getQsgName()", (Throwable)je);
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 3919 */     if (Trace.isOn) {
/* 3920 */       Trace.data(this, "getQsgName()", "getter", traceRet);
/*      */     }
/* 3922 */     return traceRet;
/*      */   }
/*      */   
/*      */   void setApplicationName(String applicationName) {
/* 3926 */     if (Trace.isOn) {
/* 3927 */       Trace.data(this, "setApplicationName(String)", "setter", applicationName);
/*      */     }
/*      */     
/* 3930 */     this.applicationName = applicationName;
/*      */   }
/*      */   
/*      */   String getApplicationName() {
/* 3934 */     if (Trace.isOn) {
/* 3935 */       Trace.data(this, "getApplicationName()", "getter", this.applicationName);
/*      */     }
/*      */     
/* 3938 */     return this.applicationName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public QmgrSplCapability getQmgrSplCapability() {
/* 3948 */     if (Trace.isOn) {
/* 3949 */       Trace.data(this, "getQmgrSplCapability()", "getter", this.qmgrSplCapability);
/*      */     }
/*      */     
/* 3952 */     return this.qmgrSplCapability;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setQmgrSplCapability(QmgrSplCapability qmgrSplCapability) {
/* 3961 */     if (Trace.isOn) {
/* 3962 */       Trace.data(this, "setQmgrSplCapability(QmgrSplCapability)", "setter", qmgrSplCapability);
/*      */     }
/*      */     
/* 3965 */     this.qmgrSplCapability = qmgrSplCapability;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public QmgrAdvancedCapability getQmgrAdvancedCapability() throws JmqiException {
/* 3973 */     QmgrAdvancedCapability traceRet1 = null;
/*      */     try {
/* 3975 */       traceRet1 = getSession().getQmgrAdvancedCapability();
/*      */     }
/* 3977 */     catch (JmqiException e) {
/* 3978 */       if (shouldReconnect(e)) {
/*      */         try {
/* 3980 */           initiateReconnection(this.session);
/* 3981 */           traceRet1 = getSession().getQmgrAdvancedCapability();
/*      */         }
/* 3983 */         catch (JmqiException je) {
/* 3984 */           if (Trace.isOn) {
/* 3985 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "getQmgrAdvancedCapability()", (Throwable)je);
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 3992 */     if (Trace.isOn) {
/* 3993 */       Trace.data(this, "getQmgrAdvancedCapability()", "getter", traceRet1);
/*      */     }
/*      */     
/* 3996 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInUse() {
/* 4005 */     boolean traceRet1 = this.callLock.isLocked();
/* 4006 */     if (Trace.isOn) {
/* 4007 */       Trace.data(this, "isInUse()", Boolean.valueOf(traceRet1));
/*      */     }
/* 4009 */     if (Trace.isOn) {
/* 4010 */       Trace.data(this, "isInUse()", "getter", 
/* 4011 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 4013 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dump(PrintWriter pw, int level) {
/* 4021 */     if (Trace.isOn) {
/* 4022 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "dump(PrintWriter,int)", new Object[] { pw, 
/* 4023 */             Integer.valueOf(level) });
/*      */     }
/* 4025 */     String prefix = Trace.buildPrefix(level);
/*      */     try {
/* 4027 */       for (RemoteHobj hobj : this.hobjs) {
/* 4028 */         pw.format("%s%s%n", new Object[] { prefix, hobj.toString() });
/*      */       }
/*      */     
/* 4031 */     } catch (ConcurrentModificationException cme) {
/* 4032 */       if (Trace.isOn) {
/* 4033 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "dump(PrintWriter,int)", cme, 1);
/*      */       }
/*      */     } 
/*      */     
/*      */     try {
/* 4038 */       for (RemoteHsub hsub : this.hsubs) {
/* 4039 */         pw.format("%s%s%n", new Object[] { prefix, hsub.toString() });
/*      */       }
/*      */     
/* 4042 */     } catch (ConcurrentModificationException cme) {
/* 4043 */       if (Trace.isOn) {
/* 4044 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "dump(PrintWriter,int)", cme, 2);
/*      */       }
/*      */     } 
/*      */     
/* 4048 */     if (this.dispatchThread != null) {
/* 4049 */       this.dispatchThread.dump(pw, level);
/*      */     }
/* 4051 */     if (Trace.isOn) {
/* 4052 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "dump(PrintWriter,int)");
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
/*      */   public void invalidate() {
/* 4064 */     if (Trace.isOn) {
/* 4065 */       Trace.data(this, "invalidate()", null);
/*      */     }
/* 4067 */     throw new IllegalStateException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isXAPrepared() {
/* 4075 */     if (Trace.isOn) {
/* 4076 */       Trace.data(this, "isXAPrepared()", Boolean.valueOf(this.XAPrepared));
/*      */     }
/* 4078 */     return this.XAPrepared;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setXAPrepared(boolean xAPrepared) {
/* 4086 */     if (Trace.isOn) {
/* 4087 */       Trace.data(this, "setXAPrepared()", Boolean.valueOf(xAPrepared));
/*      */     }
/* 4089 */     this.XAPrepared = xAPrepared;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RebalancingListener getRebalancingListener() {
/* 4096 */     if (Trace.isOn) {
/* 4097 */       Trace.data(this, "getRebalancingListener()", this.rebalancingListener);
/*      */     }
/* 4099 */     return this.rebalancingListener;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean reconnectSelf() throws JmqiException {
/* 4109 */     if (Trace.isOn) {
/* 4110 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectSelf()", new Object[0]);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 4121 */       requestDispatchLock();
/* 4122 */       enterCall(false, true, false);
/* 4123 */       enterNotifyCall(false, false);
/*      */     }
/* 4125 */     catch (JmqiException je) {
/* 4126 */       if (Trace.isOn) {
/* 4127 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectSelf()", (Throwable)je, 1);
/*      */       }
/*      */       
/* 4130 */       HashMap<String, Object> info = new HashMap<>();
/* 4131 */       info.put("exception", je);
/* 4132 */       info.put("Description", "Cannot get Hconn locks");
/* 4133 */       Trace.ffst(this, "reconnectSelf()", "01", info, je.getClass());
/*      */     } 
/*      */     
/*      */     try {
/* 4137 */       RemoteTls tls = this.fap.getTls();
/*      */       
/* 4139 */       this.session.disconnect(tls);
/*      */       
/* 4141 */       unsetStarted();
/*      */       
/* 4143 */       if (!doReconnect()) {
/* 4144 */         if (Trace.isOn) {
/* 4145 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectSelf()", 
/* 4146 */               Boolean.valueOf(false), 0);
/*      */         }
/* 4148 */         setCapabilityFlag(0);
/* 4149 */         return false;
/*      */       } 
/*      */ 
/*      */       
/* 4153 */       if (inTransaction()) {
/* 4154 */         setTransactionDoomed();
/*      */       }
/*      */ 
/*      */       
/* 4158 */       unSetQuiescing();
/*      */ 
/*      */       
/* 4161 */       for (RemoteHobj remoteHobj : getHobjs()) {
/*      */         
/* 4163 */         if (remoteHobj.getParentHsub() != null) {
/* 4164 */           if (Trace.isOn) {
/* 4165 */             Trace.data(this, "reconnectSelf()", "not reconnecting hobj (part of a subscription): ", new Object[] { remoteHobj, Integer.valueOf(remoteHobj.getOpenOptions()) });
/*      */           }
/*      */           
/*      */           continue;
/*      */         } 
/* 4170 */         if (!remoteHobj.reOpen(this, this.fap)) {
/* 4171 */           if (Trace.isOn) {
/* 4172 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectSelf()", 
/* 4173 */                 Boolean.valueOf(false), 4);
/*      */           }
/* 4175 */           return false;
/*      */         } 
/*      */       } 
/*      */       
/* 4179 */       for (RemoteHsub remoteHsub : getHsubs()) {
/* 4180 */         if (!remoteHsub.reOpen(this, this.fap)) {
/* 4181 */           if (Trace.isOn) {
/* 4182 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectSelf()", 
/* 4183 */                 Boolean.valueOf(false), 5);
/*      */           }
/* 4185 */           return false;
/*      */         } 
/*      */       } 
/*      */       
/* 4189 */       if (!restartEventHandler()) {
/* 4190 */         if (Trace.isOn) {
/* 4191 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectSelf()", 
/* 4192 */               Boolean.valueOf(false), 12);
/*      */         }
/* 4194 */         return false;
/*      */       } 
/*      */       
/* 4197 */       if (!restartCallBacks()) {
/* 4198 */         if (Trace.isOn) {
/* 4199 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectSelf()", 
/* 4200 */               Boolean.valueOf(false), 13);
/*      */         }
/* 4202 */         return false;
/*      */       } 
/*      */       
/* 4205 */       if (!reconnectChildrenIfAny()) {
/* 4206 */         if (Trace.isOn) {
/* 4207 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectSelf()", 
/* 4208 */               Boolean.valueOf(false), 6);
/*      */         }
/* 4210 */         return false;
/*      */       } 
/*      */       
/* 4213 */       if (!reconnectComplete()) {
/* 4214 */         if (Trace.isOn) {
/* 4215 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectSelf()", 
/* 4216 */               Boolean.valueOf(false), 7);
/*      */         }
/* 4218 */         return false;
/*      */       } 
/*      */     } finally {
/*      */       
/* 4222 */       if (Trace.isOn) {
/* 4223 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectSelf()");
/*      */       }
/*      */       
/*      */       try {
/* 4227 */         leaveNotifyCall(0, false);
/* 4228 */         leaveCall(0);
/* 4229 */         releaseDispatchLock();
/*      */       }
/* 4231 */       catch (JmqiException je) {
/* 4232 */         if (Trace.isOn) {
/* 4233 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectSelf()", (Throwable)je, 4);
/*      */         }
/*      */         
/* 4236 */         HashMap<String, Object> info = new HashMap<>();
/* 4237 */         info.put("exception", je);
/* 4238 */         info.put("Description", "Cannot release Hconn locks");
/* 4239 */         Trace.ffst(this, "reconnectSelf()", "02", info, je.getClass());
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 4245 */     if (Trace.isOn) {
/* 4246 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectSelf()", 
/* 4247 */           Boolean.valueOf(true), 11);
/*      */     }
/* 4249 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean restartCallBacks() {
/* 4255 */     if (Trace.isOn) {
/* 4256 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "restartCallBacks()", new Object[0]);
/*      */     }
/*      */ 
/*      */     
/* 4260 */     Pint cc = this.env.newPint();
/* 4261 */     Pint rc = this.env.newPint();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4268 */     this.acFlags &= 0xFFFFFF3F;
/*      */ 
/*      */     
/* 4271 */     if (isCallbackStarted()) {
/* 4272 */       MQCTLO mqctlo = this.env.newMQCTLO();
/* 4273 */       this.fap.MQCTL(this, 1, mqctlo, cc, rc);
/* 4274 */       if (rc.x != 0) {
/* 4275 */         if (!isReconnectableReasonCode(rc.x)) {
/* 4276 */           setReconnectionFailure(2, 2548, new JmqiException(this.env, -1, null, cc.x, rc.x, null));
/*      */         }
/* 4278 */         if (Trace.isOn) {
/* 4279 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "restartCallBacks()", 
/* 4280 */               Boolean.valueOf(false), 1);
/*      */         }
/* 4282 */         return false;
/*      */       } 
/* 4284 */       if (Trace.isOn) {
/* 4285 */         Trace.data(this, "restartCallBacks()", "callBacks restarted", Boolean.valueOf(true));
/*      */       }
/*      */       
/* 4288 */       if (isCallbackSuspended()) {
/* 4289 */         this.fap.MQCTL(this, 65536, mqctlo, cc, rc);
/* 4290 */         if (rc.x != 0) {
/* 4291 */           if (!isReconnectableReasonCode(rc.x)) {
/* 4292 */             setReconnectionFailure(2, 2548, new JmqiException(this.env, -1, null, cc.x, rc.x, null));
/*      */           }
/* 4294 */           if (Trace.isOn) {
/* 4295 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "restartCallBacks()", 
/* 4296 */                 Boolean.valueOf(false), 2);
/*      */           }
/* 4298 */           return false;
/*      */         } 
/* 4300 */         if (Trace.isOn) {
/* 4301 */           Trace.data(this, "restartCallBacks()", "callBacks suspended", Boolean.valueOf(true));
/*      */         }
/*      */       } 
/*      */       
/* 4305 */       wakeDispatchThread();
/*      */     } 
/*      */     
/* 4308 */     if (Trace.isOn) {
/* 4309 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "restartCallBacks()", 
/* 4310 */           Boolean.valueOf(true), 3);
/*      */     }
/*      */     
/* 4313 */     return true;
/*      */   }
/*      */   
/*      */   private boolean restartEventHandler() {
/* 4317 */     if (Trace.isOn) {
/* 4318 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "restartEventHandler() ", new Object[0]);
/*      */     }
/*      */ 
/*      */     
/* 4322 */     Pint cc = this.env.newPint();
/* 4323 */     Pint rc = this.env.newPint();
/*      */ 
/*      */     
/* 4326 */     if (isEventRegistered()) {
/* 4327 */       int operation = 256;
/* 4328 */       if (isEventSuspended()) {
/* 4329 */         operation |= 0x10000;
/*      */       }
/* 4331 */       this.fap.MQCB(this, operation, getEventDescriptor(), this.env.newPhobj().getHobj(), null, null, cc, rc);
/* 4332 */       if (rc.x != 0) {
/* 4333 */         if (!isReconnectableReasonCode(rc.x)) {
/* 4334 */           setReconnectionFailure(2, 2548, new JmqiException(this.env, -1, null, cc.x, rc.x, null));
/*      */         }
/* 4336 */         if (Trace.isOn) {
/* 4337 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "restartEventHandler()", 
/* 4338 */               Boolean.valueOf(false));
/*      */         }
/* 4340 */         return false;
/*      */       } 
/*      */     } 
/* 4343 */     if (Trace.isOn) {
/* 4344 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "restartEventHandler()", 
/* 4345 */           Boolean.valueOf(true));
/*      */     }
/*      */     
/* 4348 */     return true;
/*      */   }
/*      */   
/*      */   private boolean doReconnect() throws JmqiException {
/* 4352 */     if (Trace.isOn) {
/* 4353 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doReconnect()", new Object[0]);
/*      */     }
/*      */ 
/*      */     
/* 4357 */     Pint cc = this.env.newPint();
/* 4358 */     Pint rc = this.env.newPint();
/* 4359 */     Phconn phconn = this.env.newPhconn();
/*      */ 
/*      */     
/* 4362 */     JmqiConnectOptions jmqiConnectionOptions = getJmqiConnectionOptions();
/* 4363 */     jmqiConnectionOptions.setReconnectionId(getConnectionId());
/* 4364 */     jmqiConnectionOptions.setReconnectionQmId((this.reconnectQmId != null) ? this.reconnectQmId : getOldUid());
/* 4365 */     String reconnectionQueueManagerName = (this.reconnectQmName != null) ? this.reconnectQmName : getOriginalQueueManagerName();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4372 */     this.reconnectQmId = null;
/* 4373 */     this.reconnectQmName = null;
/*      */ 
/*      */ 
/*      */     
/* 4377 */     this.fap.jmqiConnect(reconnectionQueueManagerName, jmqiConnectionOptions, getConnectionOptions(), getParent(), phconn, cc, rc, this);
/* 4378 */     if (rc.x != 0) {
/* 4379 */       if (rc.x == 2546) {
/* 4380 */         setReconnectionFailure(2, 2546, null);
/* 4381 */         if (Trace.isOn) {
/* 4382 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doReconnect()", 
/* 4383 */               Boolean.valueOf(false), 2);
/*      */         }
/* 4385 */         return false;
/*      */       } 
/* 4387 */       if (Trace.isOn) {
/* 4388 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doReconnect()", 
/* 4389 */             Boolean.valueOf(false), 3);
/*      */       }
/* 4391 */       return false;
/*      */     } 
/*      */     
/* 4394 */     if (Trace.isOn) {
/* 4395 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doReconnect()", 
/* 4396 */           Boolean.valueOf(true), 4);
/*      */     }
/*      */     
/* 4399 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean reconnectComplete() {
/* 4408 */     if (Trace.isOn) {
/* 4409 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectComplete()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 4415 */       notifyReconnectionComplete();
/*      */ 
/*      */       
/* 4418 */       resetReconnectionEvents();
/*      */ 
/*      */       
/* 4421 */       checkDispatchable(null);
/*      */       
/* 4423 */       completeChildReconnections();
/*      */       
/* 4425 */       dequeFromReconnectThreadIfNecessary();
/*      */ 
/*      */       
/* 4428 */       reconnected();
/*      */       
/* 4430 */       if (Trace.isOn) {
/* 4431 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectComplete()", Boolean.TRUE);
/*      */       }
/*      */       
/* 4434 */       return true;
/*      */     }
/* 4436 */     catch (JmqiException e) {
/*      */       
/* 4438 */       if (Trace.isOn) {
/* 4439 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectComplete()", (Throwable)e);
/*      */         
/* 4441 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectComplete()", Boolean.FALSE);
/*      */       } 
/*      */       
/* 4444 */       return false;
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
/*      */   public byte[] getConnTag() {
/* 4458 */     if (Trace.isOn) {
/* 4459 */       Trace.data(this, "getConnTag()", this.connTag);
/*      */     }
/* 4461 */     return this.connTag;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConnTag(byte[] connTag) {
/* 4469 */     if (Trace.isOn) {
/* 4470 */       Trace.data(this, "setConnTag()", connTag);
/*      */     }
/*      */     
/* 4473 */     this.connTag = connTag;
/*      */   }
/*      */   
/*      */   void doMQCTL(int jmqiCompId, int operation, MQCTLO pControlOpts, Pint pCompCode, Pint pReason) {
/* 4477 */     if (Trace.isOn) {
/* 4478 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doMQCTL(int, int, MQCTLO, Pint, Pint)", new Object[] {
/* 4479 */             Integer.valueOf(jmqiCompId), Integer.valueOf(operation), pControlOpts, pCompCode, pReason
/*      */           });
/*      */     }
/* 4482 */     boolean callEntered = false;
/* 4483 */     boolean connLocked = false;
/* 4484 */     RemoteTls tls = (RemoteTls)this.sysenv.getComponentTls(jmqiCompId);
/* 4485 */     JmqiTls jmqiTls = this.sysenv.getJmqiTls((JmqiComponentTls)tls);
/* 4486 */     jmqiTls.lastException = null;
/*      */     
/*      */     try {
/* 4489 */       RemoteSession remoteSession = getSession();
/*      */       
/* 4491 */       if (getParentConnection().getFapLevel() < 9) {
/* 4492 */         JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2298, null);
/*      */ 
/*      */         
/* 4495 */         if (Trace.isOn) {
/* 4496 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doMQCTL(int,int,MQCTLO,Pint,Pint)", (Throwable)traceRet2, 1);
/*      */         }
/*      */         
/* 4499 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */       
/* 4503 */       if (!getParentConnection().isMultiplexingEnabled()) {
/* 4504 */         JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2012, null);
/*      */         
/* 4506 */         if (Trace.isOn) {
/* 4507 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doMQCTL(int,int,MQCTLO,Pint,Pint)", (Throwable)traceRet3, 2);
/*      */         }
/*      */         
/* 4510 */         throw traceRet3;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4519 */       requestDispatchLock();
/* 4520 */       connLocked = true;
/*      */ 
/*      */       
/* 4523 */       enterCall(tls.isDispatchThread, true);
/* 4524 */       callEntered = true;
/*      */       
/* 4526 */       remoteSession.checkIfDisconnected();
/*      */       
/* 4528 */       pCompCode.x = 0;
/* 4529 */       pReason.x = 0;
/*      */ 
/*      */       
/* 4532 */       validateMQCTLO(pControlOpts);
/*      */ 
/*      */       
/* 4535 */       if ((pControlOpts.getOptions() & 0x2000) != 0 && isQuiescing()) {
/* 4536 */         pCompCode.x = 2;
/* 4537 */         pReason.x = 2202;
/*      */       } else {
/*      */         boolean disassociatedThread;
/*      */ 
/*      */         
/* 4542 */         if (isReconnectable()) {
/* 4543 */           setupCallback(operation);
/*      */         }
/*      */         
/* 4546 */         switch (operation) {
/*      */           
/*      */           case 2:
/* 4549 */             pCompCode.x = 2;
/* 4550 */             pReason.x = 2534;
/*      */             break;
/*      */ 
/*      */           
/*      */           case 1:
/* 4555 */             doMQCTLStart(tls, pCompCode, pReason);
/*      */             break;
/*      */           
/*      */           case 4:
/* 4559 */             disassociatedThread = doMQCTLStop(tls);
/* 4560 */             if (disassociatedThread) {
/* 4561 */               callEntered = false;
/* 4562 */               connLocked = false;
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case 65536:
/* 4568 */             disassociatedThread = doMQCTLSuspend(tls, pCompCode, pReason);
/* 4569 */             if (disassociatedThread) {
/* 4570 */               callEntered = false;
/* 4571 */               connLocked = false;
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case 131072:
/* 4577 */             doMQCTLResume(tls, pCompCode, pReason);
/*      */             break;
/*      */           
/*      */           default:
/* 4581 */             pCompCode.x = 2;
/* 4582 */             pReason.x = 2488;
/*      */             break;
/*      */         } 
/*      */       
/*      */       } 
/* 4587 */     } catch (JmqiException e) {
/* 4588 */       if (Trace.isOn) {
/* 4589 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doMQCTL(int,int,MQCTLO,Pint,Pint)", (Throwable)e, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 4594 */       jmqiTls.lastException = e;
/* 4595 */       pCompCode.x = e.getCompCode();
/* 4596 */       pReason.x = e.getReason();
/*      */     } finally {
/*      */       
/* 4599 */       if (Trace.isOn) {
/* 4600 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doMQCTL(int,int,MQCTLO,Pint,Pint)");
/*      */       }
/*      */       
/* 4603 */       if (connLocked) {
/* 4604 */         releaseDispatchLock();
/*      */       }
/*      */ 
/*      */       
/* 4608 */       if (callEntered) {
/*      */         try {
/* 4610 */           leaveCall(pReason.x);
/*      */         }
/* 4612 */         catch (JmqiException e) {
/* 4613 */           if (Trace.isOn) {
/* 4614 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doMQCTL(int,int,MQCTLO,Pint,Pint)", (Throwable)e, 2);
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 4620 */     if (Trace.isOn) {
/* 4621 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doMQCTL(int, int, MQCTLO, Pint, Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void doMQCTLResume(RemoteTls tls, Pint pCompCode, Pint pReason) throws JmqiException {
/* 4627 */     if (Trace.isOn) {
/* 4628 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doMQCTLResume(RemoteTls, Pint, Pint)", new Object[] { tls, pCompCode, pReason });
/*      */     }
/*      */     
/* 4631 */     if (!isStarted()) {
/*      */       
/* 4633 */       pCompCode.x = 2;
/* 4634 */       pReason.x = 2534;
/*      */     }
/* 4636 */     else if (isSuspended()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4645 */       unsetSuspended();
/*      */ 
/*      */       
/* 4648 */       sendConnState(false);
/*      */ 
/*      */       
/* 4651 */       checkTxnAllowed();
/*      */ 
/*      */ 
/*      */       
/* 4655 */       if (consumersChanged() && !tls.isDispatchThread) {
/* 4656 */         driveOutstanding();
/*      */       }
/*      */ 
/*      */       
/* 4660 */       checkDispatchable(null);
/*      */     } 
/* 4662 */     if (Trace.isOn) {
/* 4663 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doMQCTLResume(RemoteTls, Pint, Pint)");
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean doMQCTLSuspend(RemoteTls tls, Pint pCompCode, Pint pReason) throws JmqiException {
/* 4668 */     if (Trace.isOn) {
/* 4669 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doMQCTLSuspend(RemoteTls, Pint, Pint)", new Object[] { tls, pCompCode, pReason });
/*      */     }
/*      */     
/* 4672 */     boolean disassociatedThread = false;
/*      */     
/* 4674 */     if (!isStarted()) {
/*      */       
/* 4676 */       pCompCode.x = 2;
/* 4677 */       pReason.x = 2534;
/*      */     }
/* 4679 */     else if (!isSuspended()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4685 */       setSuspending();
/*      */ 
/*      */ 
/*      */       
/* 4689 */       setSuspended();
/* 4690 */       unsetSuspending();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4695 */       sendConnState(true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4704 */       requestThreadLock();
/*      */       
/* 4706 */       this.dispatchThread = getDispatchThread();
/*      */       
/* 4708 */       setSuspendPending();
/* 4709 */       this.dispatchThread.incrementDispatchSeq();
/*      */       
/* 4711 */       releaseThreadLock();
/*      */ 
/*      */ 
/*      */       
/* 4715 */       if (!tls.isDispatchThread) {
/*      */         
/* 4717 */         leaveCall(0);
/*      */ 
/*      */ 
/*      */         
/* 4721 */         dispatchThreadExchange();
/*      */ 
/*      */         
/* 4724 */         disassociatedThread = true;
/*      */       } 
/*      */     } 
/* 4727 */     if (Trace.isOn) {
/* 4728 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doMQCTLSuspend(RemoteTls, Pint, Pint)", Boolean.valueOf(disassociatedThread));
/*      */     }
/* 4730 */     return disassociatedThread;
/*      */   }
/*      */   
/*      */   private boolean doMQCTLStop(RemoteTls tls) throws JmqiException {
/* 4734 */     if (Trace.isOn) {
/* 4735 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doMQCTLStop(RemoteTls)", new Object[] { tls });
/*      */     }
/*      */     
/* 4738 */     boolean disassociatedThread = false;
/*      */     
/* 4740 */     if (isStarted()) {
/*      */ 
/*      */       
/* 4743 */       unsetStarted();
/* 4744 */       unsetSuspended();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4751 */       sendConnState(true);
/*      */       
/* 4753 */       setConsumersChanged();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4762 */       requestThreadLock();
/*      */ 
/*      */       
/* 4765 */       RemoteDispatchThread dispatchThread = getDispatchThread();
/*      */       
/* 4767 */       setStopPending();
/* 4768 */       dispatchThread.incrementDispatchSeq();
/*      */       
/* 4770 */       releaseThreadLock();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4776 */       if (!tls.isDispatchThread) {
/*      */         
/* 4778 */         leaveCall(0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4784 */         dispatchThreadExchange();
/* 4785 */         disassociatedThread = true;
/*      */       } 
/*      */     } 
/* 4788 */     if (Trace.isOn) {
/* 4789 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doMQCTLStop(RemoteTls)", Boolean.valueOf(disassociatedThread));
/*      */     }
/* 4791 */     return disassociatedThread;
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
/*      */   private void validateMQCTLO(MQCTLO ctlo) throws JmqiException {
/* 4803 */     if (Trace.isOn) {
/* 4804 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "validateMQCTLO(MQCTLO)", new Object[] { ctlo });
/*      */     }
/*      */     
/* 4807 */     if (ctlo == null) {
/* 4808 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2445, null);
/*      */ 
/*      */       
/* 4811 */       if (Trace.isOn) {
/* 4812 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "validateMQCTLO(MQCTLO)", (Throwable)traceRet1, 1);
/*      */       }
/* 4814 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 4818 */     if (ctlo.getVersion() < 1 || ctlo.getVersion() > 1) {
/* 4819 */       JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 6128, null);
/*      */       
/* 4821 */       if (Trace.isOn) {
/* 4822 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "validateMQCTLO(MQCTLO)", (Throwable)traceRet2, 2);
/*      */       }
/* 4824 */       throw traceRet2;
/*      */     } 
/*      */ 
/*      */     
/* 4828 */     int ValidOptions = 8192;
/* 4829 */     if ((ctlo.getOptions() & (ValidOptions ^ 0xFFFFFFFF)) != 0) {
/* 4830 */       JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2046, null);
/*      */       
/* 4832 */       if (Trace.isOn) {
/* 4833 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "validateMQCTLO(MQCTLO)", (Throwable)traceRet3, 3);
/*      */       }
/* 4835 */       throw traceRet3;
/*      */     } 
/* 4837 */     if (Trace.isOn) {
/* 4838 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "validateMQCTLO(MQCTLO)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void doMQCTLStart(RemoteTls tls, Pint pCompCode, Pint pReason) throws JmqiException {
/* 4844 */     if (Trace.isOn) {
/* 4845 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doMQCTLStart(RemoteTls, Pint, Pint)", new Object[] { tls, pCompCode, pReason });
/*      */     }
/*      */     
/* 4848 */     if (isSuspended()) {
/*      */       
/* 4850 */       pCompCode.x = 2;
/* 4851 */       pReason.x = 2534;
/*      */     }
/* 4853 */     else if (isStarted()) {
/*      */       
/* 4855 */       pCompCode.x = 2;
/* 4856 */       pReason.x = 2500;
/*      */     }
/* 4858 */     else if (isXaConnected()) {
/*      */       
/* 4860 */       pCompCode.x = 2;
/* 4861 */       pReason.x = 2530;
/*      */     }
/*      */     else {
/*      */       
/* 4865 */       setStarted();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4871 */       if (tls.isDispatchThread) {
/*      */ 
/*      */ 
/*      */         
/* 4875 */         sendConnState(false);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 4880 */         startInit(false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4886 */         if (!isStarted() && pCompCode.x == 0) {
/* 4887 */           pCompCode.x = 2;
/* 4888 */           pReason.x = 2528;
/*      */         } 
/*      */       } 
/*      */     } 
/* 4892 */     if (Trace.isOn) {
/* 4893 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "doMQCTLStart(RemoteTls, Pint, Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCapabilityFlag() {
/* 4903 */     if (Trace.isOn) {
/* 4904 */       Trace.data(this, "getCapabilityFlag()", "getter", Integer.valueOf(this.capabilityFlag));
/*      */     }
/* 4906 */     return this.capabilityFlag;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCapabilityFlag(int capabilityFlag) {
/* 4913 */     if (Trace.isOn) {
/* 4914 */       Trace.data(this, "setCapabilityFlag()", "setter", Integer.valueOf(capabilityFlag));
/*      */     }
/* 4916 */     this.capabilityFlag = capabilityFlag;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReconnectQmName() {
/* 4923 */     if (Trace.isOn) {
/* 4924 */       Trace.data(this, "getReconnectQmName()", "getter", this.reconnectQmName);
/*      */     }
/* 4926 */     return this.reconnectQmName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getApplicableReconnectCycle() {
/* 4933 */     if (Trace.isOn) {
/* 4934 */       Trace.data(this, "getApplicableReconnectCycle()", "getter", Integer.valueOf(this.applicableReconnectCycle));
/*      */     }
/* 4936 */     return this.applicableReconnectCycle;
/*      */   } protected abstract void initialiseSubclassFields(); protected abstract boolean isaParentHconn(); protected abstract void leaveCall(HconnLock paramHconnLock, int paramInt, boolean paramBoolean1, boolean paramBoolean2) throws JmqiException; protected abstract void reacquireReconnectExecuteLock(int paramInt); protected abstract int fullyReleaseReconnectExecuteLock();
/*      */   protected abstract void enterCall(HconnLock paramHconnLock, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) throws JmqiException;
/*      */   protected void notifyWaitingProxyGets() {
/* 4940 */     if (Trace.isOn) {
/* 4941 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "notifyWaitingProxyGets()", (Object[])null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4947 */     Exception dummyException = new Exception("Dummy Exception for Reconnect");
/*      */     
/* 4949 */     for (RemoteHobj hobj : this.hobjs) {
/* 4950 */       RemoteProxyQueue proxyQueue = hobj.getProxyQueue();
/* 4951 */       if (proxyQueue != null)
/*      */       {
/* 4953 */         proxyQueue.notifyConnectionFailure(dummyException);
/*      */       }
/*      */     } 
/*      */     
/* 4957 */     for (RemoteHsub hsub : this.hsubs) {
/* 4958 */       RemoteHobj hobj = hsub.getHobj();
/* 4959 */       if (hobj != null) {
/* 4960 */         RemoteProxyQueue proxyQueue = hobj.getProxyQueue();
/* 4961 */         if (proxyQueue != null)
/*      */         {
/* 4963 */           proxyQueue.notifyConnectionFailure(dummyException);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 4968 */     if (Trace.isOn)
/* 4969 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "notifyWaitingProxyGets()", null); 
/*      */   } public abstract RemoteParentHconn getParent(); protected abstract boolean reconnectChildrenIfAny() throws JmqiException;
/*      */   protected abstract void completeChildReconnections() throws JmqiException;
/*      */   protected abstract void dequeFromReconnectThreadIfNecessary() throws JmqiException;
/*      */   abstract void removeSelfFromParentIfAny() throws JmqiException;
/* 4974 */   private enum State { NOT_RECONNECTING, QUEUED, IN_PROGRESS, FAILED; }
/*      */ 
/*      */   
/*      */   class ReconnectionState
/*      */   {
/* 4979 */     private RemoteHconn.State state = RemoteHconn.State.NOT_RECONNECTING;
/*      */     
/* 4981 */     private int failureCompCode = 0;
/* 4982 */     private int failureReason = 0;
/* 4983 */     private JmqiException failureException = null;
/*      */     
/*      */     private boolean reconnectable = false;
/*      */     
/* 4987 */     private String className = getClass().getCanonicalName();
/*      */     
/*      */     protected ReconnectionState() {
/* 4990 */       if (Trace.isOn) {
/* 4991 */         Trace.entry(this.className, "<init>");
/*      */       }
/* 4993 */       if (Trace.isOn) {
/* 4994 */         Trace.exit(this.className, "<init>");
/*      */       }
/*      */     }
/*      */     
/*      */     protected synchronized void recordFailure(int compCode, int reason, JmqiException e) {
/* 4999 */       if (Trace.isOn) {
/* 5000 */         Trace.entry(this, this.className, "recordFailure(int,int,JmqiException)", new Object[] {
/* 5001 */               Integer.valueOf(compCode), Integer.valueOf(reason), e
/*      */             });
/*      */       }
/* 5004 */       this.state = RemoteHconn.State.FAILED;
/* 5005 */       this.reconnectable = false;
/* 5006 */       this.failureCompCode = compCode;
/* 5007 */       this.failureReason = reason;
/* 5008 */       if (e != null) {
/* 5009 */         this.failureException = e;
/*      */       } else {
/*      */         
/* 5012 */         this.failureException = new JmqiException(RemoteHconn.this.env, -1, null, compCode, reason, null);
/*      */       } 
/* 5014 */       if (Trace.isOn) {
/* 5015 */         Trace.exit(this, this.className, "recordFailure(int,int,JmqiException)");
/*      */       }
/*      */     }
/*      */     
/*      */     protected synchronized boolean failed() {
/* 5020 */       boolean failed = (this.state == RemoteHconn.State.FAILED);
/* 5021 */       if (Trace.isOn) {
/* 5022 */         Trace.data(this, this.className, "failed()", "getter", Boolean.valueOf(failed));
/*      */       }
/* 5024 */       return failed;
/*      */     }
/*      */     
/*      */     protected synchronized int failureCompCode() {
/* 5028 */       if (Trace.isOn) {
/* 5029 */         Trace.data(this, this.className, "failureCompCode()", "getter", Integer.valueOf(this.failureCompCode));
/*      */       }
/* 5031 */       return this.failureCompCode;
/*      */     }
/*      */     
/*      */     protected synchronized int failureReason() {
/* 5035 */       if (Trace.isOn) {
/* 5036 */         Trace.data(this, this.className, "failureReason()", "getter", Integer.valueOf(this.failureReason));
/*      */       }
/* 5038 */       return this.failureReason;
/*      */     }
/*      */     
/*      */     protected synchronized JmqiException failureException() {
/* 5042 */       if (Trace.isOn) {
/* 5043 */         Trace.data(this, this.className, "failureException()", "getter", this.failureException);
/*      */       }
/* 5045 */       return this.failureException;
/*      */     }
/*      */     
/*      */     protected synchronized boolean alreadyQueued() {
/* 5049 */       boolean alreadyQueued = (this.state == RemoteHconn.State.QUEUED || this.state == RemoteHconn.State.IN_PROGRESS);
/* 5050 */       if (Trace.isOn) {
/* 5051 */         Trace.data(this, this.className, "alreadyQueued()", "getter", Boolean.valueOf(alreadyQueued));
/*      */       }
/* 5053 */       return alreadyQueued;
/*      */     }
/*      */     
/*      */     protected synchronized boolean isInProgress() {
/* 5057 */       boolean inProgress = (this.state == RemoteHconn.State.IN_PROGRESS);
/* 5058 */       if (Trace.isOn) {
/* 5059 */         Trace.data(this, this.className, "isInProgress()", "getter", Boolean.valueOf(inProgress));
/*      */       }
/* 5061 */       return inProgress;
/*      */     }
/*      */     
/*      */     protected synchronized void setQueued() {
/* 5065 */       if (Trace.isOn) {
/* 5066 */         Trace.data(this, this.className, "setQueued(boolean)", "setter");
/*      */       }
/* 5068 */       if (!isInProgress()) {
/* 5069 */         this.state = RemoteHconn.State.QUEUED;
/* 5070 */         if (Trace.isOn) {
/* 5071 */           Trace.data(this, this.className, "setQueued(boolean)", "setter", "QUEUED");
/*      */         
/*      */         }
/*      */       }
/* 5075 */       else if (Trace.isOn) {
/* 5076 */         Trace.data(this, this.className, "setQueued(boolean)", "setter", "already IN_PROGRESS");
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     protected synchronized void reconnected() {
/* 5082 */       if (Trace.isOn) {
/* 5083 */         Trace.data(this, this.className, "reconnected()", "setter");
/*      */       }
/* 5085 */       this.state = RemoteHconn.State.NOT_RECONNECTING;
/*      */     }
/*      */     
/*      */     protected synchronized void startReconnection() {
/* 5089 */       if (Trace.isOn) {
/* 5090 */         Trace.data(this, this.className, "startReconnection()", "setter");
/*      */       }
/* 5092 */       this.state = RemoteHconn.State.IN_PROGRESS;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public synchronized boolean isReconnectable() {
/* 5099 */       if (Trace.isOn) {
/* 5100 */         Trace.data(this, this.className, "isReconnectable()", "getter", Boolean.valueOf(this.reconnectable));
/*      */       }
/* 5102 */       return this.reconnectable;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public synchronized void setReconnectable(boolean reconnectable) {
/* 5109 */       if (Trace.isOn) {
/* 5110 */         Trace.data(this, this.className, "setReconnectable(boolean)", "setter", Boolean.valueOf(reconnectable));
/*      */       }
/* 5112 */       this.reconnectable = reconnectable;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\api\RemoteHconn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */