/*     */ package com.ibm.mq.jmqi.remote.api;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.MQCNO;
/*     */ import com.ibm.mq.jmqi.remote.impl.RemoteConnection;
/*     */ import com.ibm.mq.jmqi.remote.impl.RemoteSession;
/*     */ import com.ibm.mq.jmqi.remote.impl.RemoteTls;
/*     */ import com.ibm.mq.jmqi.remote.util.HconnLock;
/*     */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*     */ import com.ibm.msg.client.commonservices.Log.Log;
/*     */ import com.ibm.msg.client.commonservices.locking.TraceableLatch;
/*     */ import com.ibm.msg.client.commonservices.locking.TraceableLock;
/*     */ import com.ibm.msg.client.commonservices.locking.TraceableReadWriteLock;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.concurrent.CountDownLatch;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemoteParentHconn
/*     */   extends RemoteHconn
/*     */ {
/*     */   private static final String SCCSID = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/api/RemoteParentHconn.java";
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.mq.jmqi.remote.api.RemotParentHconn", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/api/RemoteParentHconn.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class ReconnectExecutionLock
/*     */     extends TraceableReadWriteLock
/*     */   {
/*     */     private static final long serialVersionUID = 5773922846711719320L;
/*     */ 
/*     */     
/*     */     private ReconnectExecutionLock() {}
/*     */   }
/*     */ 
/*     */   
/*     */   private static final class ReconnectInitiationLock
/*     */     extends TraceableReadWriteLock
/*     */   {
/*     */     private static final long serialVersionUID = -4045990137744423993L;
/*     */ 
/*     */     
/*     */     private ReconnectInitiationLock() {}
/*     */   }
/*     */   
/*  79 */   private TraceableReadWriteLock _reconnectExecutionRWLock = new ReconnectExecutionLock();
/*  80 */   protected TraceableLock _reconnectExecutionChildLock = (TraceableLock)this._reconnectExecutionRWLock.getReadLock();
/*  81 */   protected TraceableLock _reconnectExecutionParentLock = (TraceableLock)this._reconnectExecutionRWLock.getWriteLock();
/*     */   
/*  83 */   private TraceableReadWriteLock _reconnectInitiationRWLock = new ReconnectInitiationLock();
/*  84 */   protected TraceableLock _reconnectInitiationChildLock = (TraceableLock)this._reconnectInitiationRWLock.getReadLock();
/*  85 */   protected TraceableLock _reconnectInitiationParentLock = (TraceableLock)this._reconnectInitiationRWLock.getWriteLock();
/*     */ 
/*     */ 
/*     */   
/*     */   private TraceableLatch waitOnParentLatch;
/*     */ 
/*     */   
/*     */   private ArrayList<RemoteChildHconn> childHconns;
/*     */ 
/*     */   
/*     */   private ArrayList<RemoteChildHconn> childHconnsSnapshot;
/*     */ 
/*     */   
/*  98 */   private MessageListenerControl messageListenerControl = new MessageListenerControl();
/*     */ 
/*     */ 
/*     */   
/*     */   private RemoteConnection workingConnection;
/*     */ 
/*     */ 
/*     */   
/* 106 */   private AtomicBoolean queuedForReconnection = new AtomicBoolean();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   private long reconnectExpiry = 0L;
/*     */ 
/*     */   
/*     */   RemoteParentHconn(JmqiEnvironment env, RemoteFAP fap, RemoteSession remoteSession, String qmName, MQCNO connectOptions, JmqiConnectOptions jmqiConnectOptions) throws JmqiException {
/* 115 */     super(env, fap, remoteSession, qmName, connectOptions, jmqiConnectOptions);
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "<init>(JmqiEnv, RemoteFAP, RemoteSession, String, MQCNO, JmqiConnectOptions)", new Object[] { env, fap, remoteSession, qmName, connectOptions, jmqiConnectOptions });
/*     */     }
/*     */ 
/*     */     
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "<init>(JmqiEnv, RemoteFAP, RemoteSession, String, MQCNO, JmqiConnectOptions)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initialiseSubclassFields() {
/* 132 */     this.childHconns = new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isaParentHconn() {
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "isaParentHconn()", "getter", Boolean.valueOf(true));
/*     */     }
/* 140 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteParentHconn getParent() {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "getParent(Hconn)", "getter", null);
/*     */     }
/* 151 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWorkingConnection(RemoteConnection workingConnection) {
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "setWorkingConnection(RemoteConnection)", workingConnection);
/*     */     }
/* 159 */     this.workingConnection = workingConnection;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReconnectionFailure(int compcode, int reason, JmqiException e) {
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "setReconnectionFailure(int,int,JmqiException)", new Object[] {
/* 167 */             Integer.valueOf(compcode), Integer.valueOf(reason), e
/*     */           });
/*     */     }
/* 170 */     super.setReconnectionFailure(compcode, reason, e);
/* 171 */     for (RemoteChildHconn child : this.childHconns) {
/* 172 */       child.setReconnectionFailureInner(compcode, reason, e);
/*     */     }
/*     */     
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "setReconnectionFailure(int,int,JmqiException)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteConnection getWorkingConnection() {
/* 185 */     RemoteConnection traceRet1 = this.workingConnection;
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "getWorkingConnection()", "getter", traceRet1);
/*     */     }
/*     */     
/* 190 */     return traceRet1;
/*     */   }
/*     */   
/*     */   protected void addChildHconn(RemoteChildHconn childHconn) {
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "addChildHconn(RemoteChildHconn)", new Object[] { childHconn });
/*     */     }
/* 197 */     synchronized (this.childHconns) {
/* 198 */       if (Trace.isOn) {
/* 199 */         Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "addChildHconn(RemoteChildHconn)", "childHconns @" + System.identityHashCode(this.childHconns));
/*     */       }
/* 201 */       if (!this.childHconns.contains(childHconn)) {
/* 202 */         this.childHconns.add(childHconn);
/* 203 */         if (Trace.isOn) {
/* 204 */           Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "addChildHconn(RemoteChildHconn)", "was added");
/*     */         
/*     */         }
/*     */       }
/* 208 */       else if (Trace.isOn) {
/* 209 */         Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "addChildHconn(RemoteChildHconn)", "was already present");
/*     */       } 
/*     */     } 
/*     */     
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "addChildHconn(RemoteChildHconn)");
/*     */     }
/*     */   }
/*     */   
/*     */   protected void removeChildHconn(RemoteChildHconn childHconn) {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "removeChildHconn(RemoteChildHconn)", childHconn);
/*     */     }
/* 222 */     synchronized (this.childHconns) {
/* 223 */       this.childHconns.remove(childHconn);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void allocateLatch() {
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "allocateLatch()");
/*     */     }
/* 232 */     this.waitOnParentLatch = new TraceableLatch(new CountDownLatch(1));
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "allocateLatch()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void waitForReconnectComplete() throws JmqiException {
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "waitForReconnectComplete()");
/*     */     }
/*     */     while (true) {
/*     */       try {
/* 248 */         this.waitOnParentLatch.await();
/*     */         
/* 250 */         if (getReconnectionFailureException() != null) {
/* 251 */           if (Trace.isOn) {
/* 252 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "waitForReconnectComplete()", (Throwable)getReconnectionFailureException());
/*     */           }
/* 254 */           throw getReconnectionFailureException();
/*     */         } 
/*     */         
/* 257 */         if (Trace.isOn) {
/* 258 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "waitForReconnectComplete()");
/*     */         }
/*     */         
/*     */         return;
/* 262 */       } catch (InterruptedException e) {
/* 263 */         if (Trace.isOn) {
/* 264 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "waitForReconnectComplete()", e);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean reconnectChildrenIfAny() throws JmqiException {
/* 272 */     if (Trace.isOn) {
/* 273 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "reconnectChildrenIfAny()");
/*     */     }
/*     */ 
/*     */     
/* 277 */     for (RemoteChildHconn child : this.childHconnsSnapshot) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 284 */       if (child.isReconnectable()) {
/* 285 */         if (Trace.isOn) {
/* 286 */           Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "reconnectChildrenIfAny()", "reconnecting child", child);
/*     */         }
/*     */ 
/*     */         
/* 290 */         child.notifyWaitingProxyGets();
/*     */         
/* 292 */         HashMap<String, Object> logInserts = new HashMap<>();
/* 293 */         logInserts.put("XMSC_HCONN", child.toString());
/*     */         
/* 295 */         Log.log(this, "run()", "JMSCS0048", logInserts);
/*     */         
/* 297 */         if (!child.reconnectSelf()) {
/* 298 */           if (Trace.isOn) {
/* 299 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "reconnectChildrenIfAny()", 
/* 300 */                 Boolean.valueOf(false), 0);
/*     */           }
/* 302 */           Log.log(this, "run()", "JMSCS0049", logInserts);
/* 303 */           return false;
/*     */         } 
/*     */         
/* 306 */         Log.log(this, "run()", "JMSCS0050", logInserts);
/*     */         
/* 308 */         if (Trace.isOn) {
/* 309 */           Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "reconnectChildrenIfAny()", "successfully reconnected child", child);
/*     */         }
/*     */         
/*     */         continue;
/*     */       } 
/* 314 */       if (Trace.isOn) {
/* 315 */         Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "reconnectChildrenIfAny()", "skipping child as it's still being set up", child);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "reconnectChildrenIfAny()", Boolean.valueOf(true), 1);
/*     */     }
/* 324 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void enterCall(HconnLock mutex, boolean isDispatchThread, boolean callAllowedWhenAsyncStarted, boolean doCheck, boolean useReconnectLocks) throws JmqiException {
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "enterCall(HconnLock,boolean,boolean,boolean,boolean)", new Object[] { mutex, 
/* 332 */             Boolean.valueOf(isDispatchThread), 
/* 333 */             Boolean.valueOf(callAllowedWhenAsyncStarted), Boolean.valueOf(doCheck), Boolean.valueOf(useReconnectLocks) });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 343 */     if (useReconnectLocks) {
/*     */ 
/*     */ 
/*     */       
/* 347 */       acquireReconnectInitiationChildLock();
/*     */       
/* 349 */       if (doCheck)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 354 */         checkUsable(this);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 360 */       acquireReconnectExecutionChildLock();
/*     */     } 
/*     */     
/* 363 */     RemoteTls remoteTls = this.fap.getTls();
/* 364 */     remoteTls.needToCheckForReconnect = false;
/*     */     
/* 366 */     if (useReconnectLocks)
/*     */     {
/*     */ 
/*     */       
/* 370 */       releaseReconnectInitiationChildLock();
/*     */     }
/*     */     
/* 373 */     if (this.shareOption == 64) {
/* 374 */       mutex.lock();
/*     */     }
/* 376 */     else if (!mutex.tryLock()) {
/* 377 */       int compcode = 2;
/* 378 */       int reason = 2018;
/* 379 */       if (this.shareOption == 128) {
/* 380 */         reason = 2219;
/*     */       }
/*     */       
/* 383 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, compcode, reason, null);
/*     */       
/* 385 */       if (Trace.isOn) {
/* 386 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "enterCall(HconnLock,boolean,boolean,boolean,boolean)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 389 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 396 */     if (!isDispatchThread && (
/* 397 */       this.acFlags & 0x40) != 0 && (this.acFlags & 0x80) == 0 && 
/* 398 */       !callAllowedWhenAsyncStarted) {
/* 399 */       mutex.unlock();
/* 400 */       JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2500, null);
/*     */       
/* 402 */       if (Trace.isOn) {
/* 403 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "enterCall(HconnLock,boolean,boolean,boolean,boolean)", (Throwable)traceRet3, 2);
/*     */       }
/*     */       
/* 406 */       throw traceRet3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 411 */     if (Trace.isOn) {
/* 412 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "enterCall(HconnLock,boolean,boolean,boolean,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void leaveCall(HconnLock mutex, int reason, boolean doCheck, boolean useReconnectLocks) throws JmqiException {
/* 419 */     if (Trace.isOn) {
/* 420 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "leaveCall(HconnLock,int,boolean,boolean)", new Object[] { mutex, 
/* 421 */             Integer.valueOf(reason), Boolean.valueOf(doCheck), Boolean.valueOf(useReconnectLocks) });
/*     */     }
/*     */     
/* 424 */     if (useReconnectLocks) {
/* 425 */       releaseReconnectExecutionChildLock();
/*     */     }
/*     */     
/* 428 */     if (doCheck) {
/* 429 */       checkUsable((RemoteParentHconn)null);
/*     */     }
/*     */     
/*     */     try {
/* 433 */       if (reason != 0 && reason != 2544)
/*     */       {
/* 435 */         raiseEventForReason(reason);
/*     */       }
/*     */     } finally {
/*     */       
/* 439 */       if (Trace.isOn) {
/* 440 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "leaveCall(HconnLock,int,boolean,boolean)");
/*     */       }
/* 442 */       mutex.unlock();
/*     */       
/* 444 */       RemoteTls remoteTls = this.fap.getTls();
/* 445 */       remoteTls.needToCheckForReconnect = true;
/*     */     } 
/*     */     
/* 448 */     if (Trace.isOn) {
/* 449 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "leaveCall(HconnLock,int,boolean,boolean)");
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
/*     */   public void deliverConnectionBroken(Throwable e, String qmName2, String qmId) throws JmqiException {
/* 463 */     if (Trace.isOn) {
/* 464 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "deliverConnectionBroken(Throwable,String,String)", new Object[] { e, qmName2, qmId });
/*     */     }
/*     */ 
/*     */     
/* 468 */     super.deliverConnectionBroken(e, qmName2, qmId);
/*     */     
/* 470 */     synchronized (this.childHconns) {
/* 471 */       for (RemoteHconn child : this.childHconns) {
/* 472 */         if (Trace.isOn) {
/* 473 */           Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "deliverConnectionBroken(Throwable,String,String)", "childHconns @" + 
/* 474 */               System.identityHashCode(this.childHconns));
/*     */         }
/* 476 */         child.deliverConnectionBroken(e, qmName2, qmId);
/*     */       } 
/*     */     } 
/*     */     
/* 480 */     if (Trace.isOn) {
/* 481 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "deliverConnectionBroken(Throwable,String,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void completeChildReconnections() throws JmqiException {
/* 490 */     for (RemoteHconn child : this.childHconnsSnapshot) {
/* 491 */       if (Trace.isOn) {
/* 492 */         Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "reconnectComplete()", "Completing reconnection of child", child);
/*     */       }
/*     */ 
/*     */       
/* 496 */       child.reconnectComplete();
/*     */       
/* 498 */       if (Trace.isOn) {
/* 499 */         Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "reconnectComplete()", "Completed reconnection of child", child);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void releaseReconnectLatch() {
/* 510 */     if (Trace.isOn) {
/* 511 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "releaseReconnectLatch()");
/*     */     }
/*     */     
/* 514 */     this.waitOnParentLatch.countDown();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void removeSelfFromParentIfAny() {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dequeFromReconnectThreadIfNecessary() throws JmqiException {
/* 524 */     this.fap.getReconnectThread().releaseHconn(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean queuedForReconnect() {
/* 531 */     if (Trace.isOn) {
/* 532 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "queuedForReconnect()", 
/* 533 */           Boolean.valueOf(this.queuedForReconnection.get()));
/*     */     }
/* 535 */     return this.queuedForReconnection.getAndSet(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reconnectSelf() throws JmqiException {
/* 546 */     if (Trace.isOn) {
/* 547 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "reconnectSelf()", new Object[0]);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 555 */     acquireReconnectInitiationParentLock();
/*     */     try {
/* 557 */       acquireReconnectExecutionParentLock();
/*     */     } finally {
/*     */       
/* 560 */       releaseReconnectInitiationParentLock();
/*     */     } 
/* 562 */     boolean result = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 573 */       synchronized (this.childHconns) {
/* 574 */         this.childHconnsSnapshot = new ArrayList<>(this.childHconns);
/*     */       } 
/*     */       
/* 577 */       result = super.reconnectSelf();
/*     */     } finally {
/*     */       
/* 580 */       releaseReconnectExecutionParentLock();
/*     */     } 
/*     */     
/* 583 */     if (Trace.isOn) {
/* 584 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "reconnectSelf()", 
/* 585 */           Boolean.valueOf(result));
/*     */     }
/* 587 */     return result;
/*     */   }
/*     */   
/*     */   private void acquireReconnectInitiationParentLock() {
/* 591 */     if (Trace.isOn) {
/* 592 */       Trace.data(this, "acquireReconnectInitiationParentLock", "lockAquire", null);
/*     */     }
/* 594 */     this._reconnectInitiationParentLock.lock();
/*     */   }
/*     */   
/*     */   private void releaseReconnectInitiationParentLock() {
/* 598 */     if (Trace.isOn) {
/* 599 */       Trace.data(this, "releaseReconnectInitiationParentLock", "lockRelease", null);
/*     */     }
/* 601 */     this._reconnectInitiationParentLock.unlock();
/*     */   }
/*     */   
/*     */   private void acquireReconnectExecutionParentLock() {
/* 605 */     if (Trace.isOn) {
/* 606 */       Trace.data(this, "acquireReconnectExecutionParentLock", "lockAquire", null);
/*     */     }
/* 608 */     this._reconnectExecutionParentLock.lock();
/*     */   }
/*     */   
/*     */   private void releaseReconnectExecutionParentLock() {
/* 612 */     if (Trace.isOn) {
/* 613 */       Trace.data(this, "releaseReconnectExecutionParentLock", "lockRelease", null);
/*     */     }
/* 615 */     this._reconnectExecutionParentLock.unlock();
/*     */   }
/*     */ 
/*     */   
/*     */   public void acquireReconnectInitiationChildLock() {
/* 620 */     if (Trace.isOn) {
/* 621 */       Trace.data(this, "acquireReconnectInitiationChildLock", "lockAquire", null);
/*     */     }
/* 623 */     this._reconnectInitiationChildLock.lock();
/*     */   }
/*     */ 
/*     */   
/*     */   public void releaseReconnectInitiationChildLock() {
/* 628 */     if (Trace.isOn) {
/* 629 */       Trace.data(this, "releaseReconnectInitiationChildLock", "lockRelease", null);
/*     */     }
/* 631 */     this._reconnectInitiationChildLock.unlock();
/*     */   }
/*     */ 
/*     */   
/*     */   public void acquireReconnectExecutionChildLock() {
/* 636 */     if (Trace.isOn) {
/* 637 */       Trace.data(this, "acquireReconnectExecutionChildLock", "lockAquire", null);
/*     */     }
/* 639 */     this._reconnectExecutionChildLock.lock();
/*     */   }
/*     */ 
/*     */   
/*     */   public void reacquireReconnectExecutionChildLock(int depth) {
/* 644 */     if (Trace.isOn) {
/* 645 */       Trace.data(this, "acquireReconnectExecutionChildLock", "lockReacquire", Integer.valueOf(depth));
/*     */     }
/* 647 */     for (int i = 0; i < depth; i++) {
/* 648 */       this._reconnectExecutionChildLock.lock();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void releaseReconnectExecutionChildLock() {
/* 654 */     if (Trace.isOn) {
/* 655 */       Trace.data(this, "releaseReconnectExecutionChildLock", "lockRelease", null);
/*     */     }
/* 657 */     this._reconnectExecutionChildLock.unlock();
/*     */   }
/*     */ 
/*     */   
/*     */   public int fullyReleaseReconnectExecutionChildLock() {
/* 662 */     int result = this._reconnectExecutionChildLock.fullyUnlock();
/* 663 */     if (Trace.isOn) {
/* 664 */       Trace.data(this, "fullyReleaseReconnectExecutionChildLock", "lockFullyRelease", Integer.valueOf(result));
/*     */     }
/* 666 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void notQueuedForReconnect() {
/* 672 */     if (Trace.isOn) {
/* 673 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "notQueuedForReconnect()", 
/* 674 */           Boolean.valueOf(this.queuedForReconnection.get()));
/*     */     }
/* 676 */     this.queuedForReconnection.set(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean initializeForReconnect() {
/* 683 */     if (Trace.isOn) {
/* 684 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "initializeForReconnect()");
/*     */     }
/* 686 */     boolean retval = false;
/*     */     
/* 688 */     acquireReconnectInitiationParentLock();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 694 */       if (!isReconnecting() && !this.session.isReconnectionBegun()) {
/* 695 */         this.reconnectExpiry = System.currentTimeMillis() + (getReconnectionTimeout() * 1000);
/*     */         
/* 697 */         allocateLatch();
/*     */         
/* 699 */         this.reconnectAttempts = 0;
/* 700 */         this.nextReconnect = System.currentTimeMillis();
/* 701 */         this.reconnectionState.startReconnection();
/* 702 */         this.session.setReconnectionBegun();
/*     */         
/* 704 */         notifyWaitingProxyGets();
/* 705 */         retval = true;
/*     */       } 
/*     */     } finally {
/*     */       
/* 709 */       releaseReconnectInitiationParentLock();
/*     */     } 
/*     */     
/* 712 */     if (Trace.isOn) {
/* 713 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn", "initializeForReconnect()", 
/* 714 */           Boolean.valueOf(retval));
/*     */     }
/* 716 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reacquireReconnectExecuteLock(int depth) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected int fullyReleaseReconnectExecuteLock() {
/* 727 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onMessagePermitted() {
/* 736 */     return this.messageListenerControl.enterOnMessage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void leaveOnMessage() {
/* 745 */     this.messageListenerControl.leaveOnMessage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initiateConnectionStop() {
/* 754 */     this.messageListenerControl.initiateConnectionStop();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void finishConnectionStop() {
/* 763 */     this.messageListenerControl.finishConnectionStop();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getReconnectExpiry() {
/* 771 */     if (Trace.isOn) {
/* 772 */       Trace.data(this, "getReconnectExpiry()", "getter", 
/* 773 */           Long.valueOf(this.reconnectExpiry));
/*     */     }
/* 775 */     return this.reconnectExpiry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class MessageListenerControl
/*     */   {
/*     */     private boolean inhibitOnMessage = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 791 */     private int listenersInFlight = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     synchronized boolean enterOnMessage() {
/* 799 */       if (Trace.isOn) {
/* 800 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn$MessageListenerControl", "enterOnMessage()");
/*     */       }
/*     */       
/* 803 */       if (this.inhibitOnMessage) {
/* 804 */         if (Trace.isOn) {
/* 805 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn$MessageListenerControl", "enterOnMessage()", Boolean.valueOf(false));
/*     */         }
/* 807 */         return false;
/*     */       } 
/*     */       
/* 810 */       this.listenersInFlight++;
/* 811 */       if (Trace.isOn) {
/* 812 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn$MessageListenerControl", "enterOnMessage()");
/*     */       }
/* 814 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     synchronized void leaveOnMessage() {
/* 823 */       if (Trace.isOn) {
/* 824 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn$MessageListenerControl", "leaveOnMessage()");
/*     */       }
/* 826 */       this.listenersInFlight--;
/* 827 */       if (this.inhibitOnMessage) {
/* 828 */         if (Trace.isOn) {
/* 829 */           Trace.data(this, "leaveOnMessage()", "notifying", null);
/*     */         }
/* 831 */         notify();
/*     */       } 
/* 833 */       if (Trace.isOn) {
/* 834 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn$MessageListenerControl", "leaveOnMessage()");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     synchronized void initiateConnectionStop() {
/* 843 */       if (Trace.isOn) {
/* 844 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn$MessageListenerControl", "initiateConnectionStop()");
/*     */       }
/* 846 */       this.inhibitOnMessage = true;
/* 847 */       while (this.listenersInFlight > 0) {
/*     */         try {
/* 849 */           wait();
/*     */         }
/* 851 */         catch (InterruptedException interruptedException) {}
/*     */       } 
/*     */ 
/*     */       
/* 855 */       if (Trace.isOn) {
/* 856 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn$MessageListenerControl", "initiateConnectionStop()");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     synchronized void finishConnectionStop() {
/* 866 */       if (Trace.isOn) {
/* 867 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn$MessageListenerControl", "finishConnectionStop()");
/*     */       }
/* 869 */       this.inhibitOnMessage = false;
/* 870 */       if (Trace.isOn)
/* 871 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteParentHconn$MessageListenerControl", "finishConnectionStop()"); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\api\RemoteParentHconn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */