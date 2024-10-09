/*     */ package com.ibm.mq.jmqi.remote.api;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.MQCNO;
/*     */ import com.ibm.mq.jmqi.remote.impl.RemoteSession;
/*     */ import com.ibm.mq.jmqi.remote.impl.RemoteTls;
/*     */ import com.ibm.mq.jmqi.remote.util.HconnLock;
/*     */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
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
/*     */ public class RemoteChildHconn
/*     */   extends RemoteHconn
/*     */ {
/*     */   private static final String SCCSID = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/api/RemoteChildHconn.java";
/*     */   private RemoteParentHconn parent;
/*     */   
/*     */   static {
/*  42 */     if (Trace.isOn) {
/*  43 */       Trace.data("com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/api/RemoteChildHconn.java");
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
/*  57 */   protected long reconnectExpiry = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RemoteChildHconn(JmqiEnvironment env, RemoteFAP fap, RemoteSession remoteSession, String qmName, MQCNO connectOptions, JmqiConnectOptions jmqiConnectOptions, RemoteHconn parentHconn) throws JmqiException {
/*  67 */     super(env, fap, remoteSession, qmName, connectOptions, jmqiConnectOptions);
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "<init>(JmqiEnv, RemoteFAP, RemoteSession, String, MQCNO, JmqiConnectOptions, remoteHconn)", new Object[] { env, fap, remoteSession, qmName, connectOptions, jmqiConnectOptions, parentHconn });
/*     */     }
/*     */     
/*  72 */     this.parent = (RemoteParentHconn)parentHconn;
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "<init>(JmqiEnv, RemoteFAP, RemoteSession, String, MQCNO, JmqiConnectOptions, remoteHconn)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initialiseSubclassFields() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isaParentHconn() {
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "isaParentHconn()", "getter", Boolean.valueOf(false));
/*     */     }
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteParentHconn getParent() {
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "getParent(Hconn)", "getter", this.parent);
/*     */     }
/* 103 */     return this.parent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void enterCall(HconnLock mutex, boolean isDispatchThread, boolean callAllowedWhenAsyncStarted, boolean doCheck, boolean useReconnectLocks) throws JmqiException {
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "enterCall(HconnLock,boolean,boolean,boolean,boolean)", new Object[] { mutex, 
/* 111 */             Boolean.valueOf(isDispatchThread), 
/* 112 */             Boolean.valueOf(callAllowedWhenAsyncStarted), Boolean.valueOf(doCheck), Boolean.valueOf(useReconnectLocks) });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     if (useReconnectLocks) {
/*     */ 
/*     */ 
/*     */       
/* 126 */       this.parent.acquireReconnectInitiationChildLock();
/*     */       
/* 128 */       if (doCheck)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 133 */         checkUsable(this.parent);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 139 */       this.parent.acquireReconnectExecutionChildLock();
/*     */     } 
/*     */     
/* 142 */     RemoteTls remoteTls = this.fap.getTls();
/* 143 */     remoteTls.needToCheckForReconnect = false;
/*     */     
/* 145 */     if (useReconnectLocks)
/*     */     {
/*     */ 
/*     */       
/* 149 */       this.parent.releaseReconnectInitiationChildLock();
/*     */     }
/*     */     
/* 152 */     if (this.shareOption == 64) {
/* 153 */       mutex.lock();
/*     */     }
/* 155 */     else if (!mutex.tryLock()) {
/* 156 */       int compcode = 2;
/* 157 */       int reason = 2018;
/* 158 */       if (this.shareOption == 128) {
/* 159 */         reason = 2219;
/*     */       }
/*     */       
/* 162 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, compcode, reason, null);
/*     */ 
/*     */       
/* 165 */       if (Trace.isOn) {
/* 166 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "enterCall(HconnLock,boolean,boolean,boolean,boolean)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 169 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 176 */     if (!isDispatchThread && (
/* 177 */       this.acFlags & 0x40) != 0 && (this.acFlags & 0x80) == 0 && 
/* 178 */       !callAllowedWhenAsyncStarted) {
/* 179 */       mutex.unlock();
/* 180 */       JmqiException traceRet3 = new JmqiException(this.env, -1, null, 2, 2500, null);
/*     */       
/* 182 */       if (Trace.isOn) {
/* 183 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "enterCall(HconnLock,boolean,boolean,boolean,boolean)", (Throwable)traceRet3, 2);
/*     */       }
/*     */       
/* 186 */       throw traceRet3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "enterCall(HconnLock,boolean,boolean,boolean,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void leaveCall(HconnLock mutex, int reason, boolean doCheck, boolean useReconnectLocks) throws JmqiException {
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "leaveCall(HconnLock,int,boolean,boolean)", new Object[] { mutex, 
/* 201 */             Integer.valueOf(reason), Boolean.valueOf(doCheck), Boolean.valueOf(useReconnectLocks) });
/*     */     }
/*     */     
/*     */     try {
/* 205 */       if (reason != 0 && reason != 2544)
/*     */       {
/* 207 */         raiseEventForReason(reason);
/*     */       }
/*     */     } finally {
/*     */       
/* 211 */       if (Trace.isOn) {
/* 212 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "leaveCall(HconnLock,int,boolean,boolean)");
/*     */       }
/* 214 */       mutex.unlock();
/*     */       
/* 216 */       RemoteTls remoteTls = this.fap.getTls();
/* 217 */       remoteTls.needToCheckForReconnect = true;
/*     */       
/* 219 */       if (useReconnectLocks) {
/* 220 */         this.parent.releaseReconnectExecutionChildLock();
/*     */       }
/*     */     } 
/*     */     
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "leaveCall(HconnLock,int,boolean,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean reconnectChildrenIfAny() throws JmqiException {
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "reconnectChildrenIfAny()");
/*     */     }
/*     */     
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "reconnectChildrenIfAny()", Boolean.valueOf(true));
/*     */     }
/* 239 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void completeChildReconnections() throws JmqiException {
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "completeChildReconnections()");
/*     */     }
/*     */     
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "completeChildReconnections()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void removeSelfFromParentIfAny() {
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "removeSelfFromParentIfAny()");
/*     */     }
/*     */     
/* 259 */     this.parent.removeChildHconn(this);
/*     */     
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "removeSelfFromParentIfAny()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dequeFromReconnectThreadIfNecessary() throws JmqiException {
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "dequeFromReconnectThreadIfNecessary()");
/*     */     }
/*     */     
/* 272 */     if (Trace.isOn) {
/* 273 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "dequeFromReconnectThreadIfNecessary()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void reacquireReconnectExecuteLock(int depth) {
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "reacquireReconnectExecuteLock(int)", new Object[] { Integer.valueOf(depth) });
/*     */     }
/* 282 */     this.parent.reacquireReconnectExecutionChildLock(depth);
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "reacquireReconnectExecuteLock(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected int fullyReleaseReconnectExecuteLock() {
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "fullyReleaseReconnectExecuteLock()");
/*     */     }
/* 293 */     int depth = this.parent.fullyReleaseReconnectExecutionChildLock();
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "fullyReleaseReconnectExecuteLock()", depth);
/*     */     }
/* 297 */     return depth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onMessagePermitted() {
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "onMessagePermitted()");
/*     */     }
/*     */     
/* 309 */     boolean result = this.parent.onMessagePermitted();
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "onMessagePermitted()", Boolean.valueOf(result));
/*     */     }
/* 313 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void leaveOnMessage() {
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "leaveOnMessage()");
/*     */     }
/*     */     
/* 325 */     this.parent.leaveOnMessage();
/* 326 */     if (Trace.isOn) {
/* 327 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "leaveOnMessage()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initiateConnectionStop() {
/* 336 */     if (Trace.isOn) {
/* 337 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "initiateConnectionStop()");
/*     */     }
/*     */     
/* 340 */     this.parent.initiateConnectionStop();
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "initiateConnectionStop()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void finishConnectionStop() {
/* 351 */     if (Trace.isOn) {
/* 352 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "finishConnectionStop()");
/*     */     }
/*     */     
/* 355 */     this.parent.finishConnectionStop();
/* 356 */     if (Trace.isOn) {
/* 357 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "finishConnectionStop()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getReconnectExpiry() {
/* 366 */     if (Trace.isOn) {
/* 367 */       Trace.data(this, "getReconnectExpiry()", "getter", 
/* 368 */           Long.valueOf(this.reconnectExpiry));
/*     */     }
/* 370 */     return this.reconnectExpiry;
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
/*     */   public void setReconnectionFailure(int compcode, int reason, JmqiException e) {
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "setReconnectionFailure(int,int,JmqiException)", new Object[] {
/* 387 */             Integer.valueOf(compcode), Integer.valueOf(reason), e
/*     */           });
/*     */     }
/* 390 */     super.setReconnectionFailure(compcode, reason, e);
/* 391 */     this.parent.setReconnectionFailure(compcode, reason, e);
/*     */     
/* 393 */     if (Trace.isOn)
/* 394 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteChildHconn", "setReconnectionFailure(int,int,JmqiException)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\api\RemoteChildHconn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */