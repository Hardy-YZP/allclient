/*     */ package com.ibm.mq.jmqi.remote.impl;
/*     */ 
/*     */ import com.ibm.mq.exits.MQCD;
/*     */ import com.ibm.mq.exits.MQCSP;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQCNO;
/*     */ import com.ibm.mq.jmqi.MQSCO;
/*     */ import com.ibm.mq.jmqi.remote.api.RemoteHconn;
/*     */ import com.ibm.mq.jmqi.remote.api.RemoteParentHconn;
/*     */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.locks.Condition;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import javax.net.ssl.SSLSocketFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemoteConnectionSpecification
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteConnectionSpecification.java";
/*     */   private final RemoteConnectionSpecificationKey specKey;
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteConnectionSpecification.java");
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
/*  71 */   int maxFapLevel = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   private Lock shareableConnectionsLock = new ReentrantLock();
/*  79 */   private Set<RemoteConnection> generallyShareableConnections = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   private Lock connectorsLock = new ReentrantLock();
/*  86 */   private Condition connectorsCondition = this.connectorsLock.newCondition();
/*  87 */   private int connectorsCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteConnectionSpecification(JmqiEnvironment jmqiEnv, RemoteConnectionSpecificationKey specKey, int maxFapLevel) {
/*  96 */     super(jmqiEnv);
/*     */     
/*  98 */     String fid = "<init>(JmqiEnvironment,RemoteConnectionSpecificationKey,int)";
/*     */     
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", fid, new Object[] { jmqiEnv, specKey, 
/* 102 */             Integer.valueOf(maxFapLevel) });
/*     */     }
/*     */     
/* 105 */     this.specKey = specKey;
/* 106 */     this.maxFapLevel = maxFapLevel;
/*     */     
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", fid);
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
/*     */   RemoteSession getSession(RemoteTls tls, MQCSP mqcsp, MQCNO connectOptions, Object sendExits, String sendExitsUserData, Object receiveExits, String receiveExitsUserData, RemoteHconn parentHconn, JmqiConnectOptions.ChannelSharingMode jmsChannelSharingMode) throws JmqiException {
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getSession(MQCSP,String,MQCNO,int,Object,String,Object,String, RemoteHconn, boolean)", new Object[] { mqcsp, connectOptions, sendExits, sendExitsUserData, receiveExits, receiveExitsUserData, parentHconn, jmsChannelSharingMode });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     RemoteSession session = null;
/*     */     
/* 143 */     if (parentHconn != null) {
/*     */       
/* 145 */       RemoteConnection workingConnection = ((RemoteParentHconn)parentHconn).getWorkingConnection();
/* 146 */       if (workingConnection != null) {
/* 147 */         workingConnection.getSessionLock().lock();
/* 148 */         workingConnection.setSessionBeingCreated(true);
/*     */         
/*     */         try {
/* 151 */           if (workingConnection.isConnected()) {
/* 152 */             session = assignSessionFromConnection(tls, connectOptions, workingConnection, sendExits, sendExitsUserData, receiveExits, receiveExitsUserData, parentHconn);
/*     */           }
/*     */         } finally {
/*     */           
/* 156 */           workingConnection.setSessionBeingCreated(false);
/* 157 */           workingConnection.getSessionLock().unlock();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 163 */     if (session == null && eligibleForGeneralShareableConnection(connectOptions, jmsChannelSharingMode))
/*     */     {
/* 165 */       session = getSessionFromEligibleConnection(tls, connectOptions, sendExits, sendExitsUserData, receiveExits, receiveExitsUserData);
/*     */     }
/*     */ 
/*     */     
/* 169 */     if (session == null) {
/* 170 */       session = getSessionFromNewConnection(tls, mqcsp, connectOptions, sendExits, sendExitsUserData, receiveExits, receiveExitsUserData, jmsChannelSharingMode);
/*     */     }
/*     */     
/* 173 */     session.initOAMUserAuth(tls, mqcsp);
/*     */     
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getSession(RemoteTls,MQCSP,RemoteFAP,String,int,int,Object,String,Object,String)", session);
/*     */     }
/*     */ 
/*     */     
/* 180 */     return session;
/*     */   }
/*     */   
/*     */   private boolean eligibleForGeneralShareableConnection(MQCNO connectOptions, JmqiConnectOptions.ChannelSharingMode jmsChannelSharingMode) {
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "eligibleForGeneralShareableConnection(MQCNO, boolean)", new Object[] { connectOptions, jmsChannelSharingMode });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 192 */     if (jmsChannelSharingMode == JmqiConnectOptions.ChannelSharingMode.CONNECTION) {
/* 193 */       if (Trace.isOn) {
/* 194 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "eligibleForGeneralShareableConnection(MQCNO, boolean)", 
/* 195 */             Boolean.valueOf(false), 0);
/*     */       }
/* 197 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 203 */     if ((connectOptions.getOptions() & 0x10000) == 65536) {
/* 204 */       if (Trace.isOn) {
/* 205 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "eligibleForGeneralShareableConnection(MQCNO, boolean)", 
/* 206 */             Boolean.valueOf(false), 1);
/*     */       }
/* 208 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 215 */     if ((connectOptions.getOptions() & 0x2000000) == 33554432) {
/*     */       
/* 217 */       if (Trace.isOn) {
/* 218 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "eligibleForGeneralShareableConnection(MQCNO, boolean)", 
/* 219 */             Boolean.valueOf(true), 2);
/*     */       }
/* 221 */       return true;
/*     */     } 
/*     */     
/* 224 */     if ((connectOptions.getOptions() & 0x5000000) != 0) {
/*     */       
/* 226 */       if (Trace.isOn) {
/* 227 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "eligibleForGeneralShareableConnection(MQCNO, boolean)", 
/* 228 */             Boolean.valueOf(false), 3);
/*     */       }
/* 230 */       return false;
/*     */     } 
/*     */     
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "eligibleForGeneralShareableConnection(MQCNO, boolean)", 
/* 235 */           Boolean.valueOf(true), 3);
/*     */     }
/* 237 */     return true;
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
/*     */   private RemoteSession getSessionFromNewConnection(RemoteTls tls, MQCSP mqcsp, MQCNO connectOptions, Object sendExits, String sendExitsUserData, Object receiveExits, String receiveExitsUserData, JmqiConnectOptions.ChannelSharingMode jmsChannelSharingMode) throws JmqiException {
/*     */     RemoteSession session;
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getSessionFromNewConnection(MQCSP,MQCNO,Object,String,Object,String,ChannelSharingMode)", new Object[] { mqcsp, connectOptions, 
/*     */ 
/*     */             
/* 261 */             Integer.valueOf(this.maxFapLevel), sendExits, sendExitsUserData, receiveExits, receiveExitsUserData, jmsChannelSharingMode });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 268 */     RemoteExitChainPair exitChainPair = new RemoteExitChainPair(this.env, sendExits, sendExitsUserData, receiveExits, receiveExitsUserData, this.specKey.getExitClassPath());
/*     */ 
/*     */ 
/*     */     
/* 272 */     this.connectorsLock.lock();
/*     */     try {
/* 274 */       this.connectorsCount++;
/*     */     } finally {
/*     */       
/* 277 */       this.connectorsLock.unlock();
/*     */     } 
/*     */     
/*     */     try {
/*     */       RemoteConnection connection;
/*     */       try {
/* 283 */         connection = getNewConnection(tls, connectOptions, mqcsp, exitChainPair, jmsChannelSharingMode);
/*     */       }
/* 285 */       catch (JmqiException jmqie) {
/* 286 */         if (Trace.isOn) {
/* 287 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getSessionFromNewConnection(MQCSP,MQCNO,Object,String,Object,String,ChannelSharingMode)", (Throwable)jmqie);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 294 */         removeSelf();
/*     */         
/* 296 */         if (Trace.isOn) {
/* 297 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getSessionFromNewConnection(MQCSP,MQCNO,Object,String,Object,String,ChannelSharingMode)", (Throwable)jmqie);
/*     */         }
/*     */ 
/*     */         
/* 301 */         throw jmqie;
/*     */       } 
/*     */       
/* 304 */       session = connection.assignSession();
/* 305 */       session.setExits(exitChainPair);
/*     */       
/* 307 */       storeConnectionIfEligible(connection, connectOptions);
/*     */     }
/*     */     finally {
/*     */       
/* 311 */       this.connectorsLock.lock();
/*     */       try {
/* 313 */         this.connectorsCount--;
/* 314 */         this.connectorsCondition.signalAll();
/*     */       } finally {
/*     */         
/* 317 */         this.connectorsLock.unlock();
/*     */       } 
/*     */     } 
/*     */     
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getSessionFromNewConnection(MQCSP,MQCNO,Object,String,Object,String,ChannelSharingMode)", session);
/*     */     }
/*     */ 
/*     */     
/* 326 */     return session;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void storeConnectionIfEligible(RemoteConnection connection, MQCNO connectOptions) {
/* 335 */     if (Trace.isOn) {
/* 336 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "storeConnectionIfEligible(RemoteConnection, MQCNO)", new Object[] { connection, connectOptions });
/*     */     }
/*     */ 
/*     */     
/* 340 */     if (connection.isMultiplexed() && 
/* 341 */       !reconnectable(connection, connectOptions)) {
/*     */       
/* 343 */       if (Trace.isOn) {
/* 344 */         Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "storeConnectionIfEligible(RemoteConnection)", "connection was eligible, so was stored");
/*     */       }
/*     */       
/* 347 */       storeConnection(connection);
/*     */     
/*     */     }
/* 350 */     else if (Trace.isOn) {
/* 351 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "storeConnectionIfEligible(RemoteConnection)", "connection was NOT eligible, so was NOT stored");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 356 */     if (Trace.isOn) {
/* 357 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "storeConnectionIfEligible(RemoteConnection, MQCNO)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void storeConnection(RemoteConnection connection) {
/* 363 */     if (Trace.isOn) {
/* 364 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "storeConnection(RemoteConnection)", new Object[] { connection });
/*     */     }
/*     */ 
/*     */     
/* 368 */     this.shareableConnectionsLock.lock();
/*     */     try {
/* 370 */       this.generallyShareableConnections.add(connection);
/*     */     } finally {
/*     */       
/* 373 */       this.shareableConnectionsLock.unlock();
/*     */     } 
/*     */     
/* 376 */     if (Trace.isOn) {
/* 377 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "storeConnection(RemoteConnection)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean reconnectable(RemoteConnection connection, MQCNO connectOptions) {
/* 384 */     boolean traceRet = (connection.supportsReconnection() && (connectOptions.getOptions() & 0x5000000) != 0);
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.data(this, "reconnectable(RemoteConnection,MQCNO)", "getter", Boolean.valueOf(traceRet));
/*     */     }
/* 388 */     return traceRet;
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
/*     */   private RemoteSession getSessionFromEligibleConnection(RemoteTls tls, MQCNO connectOptions, Object sendExits, String sendExitsUserData, Object receiveExits, String receiveExitsUserData) throws JmqiException {
/* 404 */     if (Trace.isOn) {
/* 405 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getSessionFromEligibleConnection(RemoteTls, MQCNO, Object,String,Object,String)", new Object[] { tls, connectOptions, sendExits, sendExitsUserData, receiveExits, receiveExitsUserData });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 410 */     RemoteSession session = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 427 */     this.shareableConnectionsLock.lock();
/*     */ 
/*     */ 
/*     */     
/*     */     try { while (true)
/* 432 */       { for (RemoteConnection connection : this.generallyShareableConnections)
/* 433 */         { connection.getSessionLock().lock();
/* 434 */           connection.setSessionBeingCreated(true);
/*     */           
/*     */           try {
/* 437 */             if (connection.isConnected() && connection.getAsyncFailure() == null) {
/* 438 */               session = assignSessionFromConnection(tls, connectOptions, connection, sendExits, sendExitsUserData, receiveExits, receiveExitsUserData, null);
/*     */             }
/*     */           } finally {
/*     */             
/* 442 */             connection.setSessionBeingCreated(false);
/* 443 */             connection.getSessionLock().unlock();
/*     */           } 
/*     */           
/* 446 */           if (session != null)
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
/* 486 */             this.shareableConnectionsLock.unlock(); }  }  this.shareableConnectionsLock.unlock(); try { this.connectorsLock.lock(); try { if (this.connectorsCount <= 0) { this.connectorsLock.unlock(); this.shareableConnectionsLock.lock(); break; }  try { if (Trace.isOn) Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "assignSessionFromConnection(RemoteTls,MQCNO,RemoteConnection,Object,String,Object,String,RemoteHconn)", "Waiting for another thread to create a new connection.  Number of threads waiting:", Integer.valueOf(this.connectorsCount));  this.connectorsCondition.await(); } catch (InterruptedException interruptedException) {} } finally { this.connectorsLock.unlock(); }  } finally { this.shareableConnectionsLock.lock(); }  }  } finally { this.shareableConnectionsLock.unlock(); }
/*     */ 
/*     */     
/* 489 */     if (Trace.isOn) {
/* 490 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getSessionFromEligibleConnection(RemoteTls, MQCNO, Object,String,Object,String)", session);
/*     */     }
/*     */     
/* 493 */     return session;
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
/*     */   private RemoteSession assignSessionFromConnection(RemoteTls tls, MQCNO connectOptions, RemoteConnection connectionParameter, Object sendExits, String sendExitsUserData, Object receiveExits, String receiveExitsUserData, RemoteHconn parentHconn) throws JmqiException {
/* 512 */     if (Trace.isOn) {
/* 513 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "assignSessionFromConnection(RemoteTls, MQCNO, RemoteConnection, Object, String, Object, String, RemoteHconn)", new Object[] { tls, connectOptions, connectionParameter, sendExits, sendExitsUserData, receiveExits, receiveExitsUserData, parentHconn });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 519 */     RemoteConnection connection = connectionParameter;
/* 520 */     RemoteSession result = null;
/*     */     try {
/* 522 */       result = connection.assignSession();
/*     */       
/* 524 */       if (result != null)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 530 */         RemoteExitChainPair exitChainPair = new RemoteExitChainPair(this.env, sendExits, sendExitsUserData, receiveExits, receiveExitsUserData, this.specKey.getExitClassPath());
/* 531 */         exitChainPair.loadAndInitExits(connection, false);
/* 532 */         result.setExits(exitChainPair);
/* 533 */         result.startConversation(tls);
/* 534 */         if (connection.getFreeConversations() <= 0) {
/* 535 */           this.generallyShareableConnections.remove(connection);
/*     */         }
/*     */       }
/*     */     
/* 539 */     } catch (JmqiException e) {
/*     */       
/* 541 */       if (connectionAndOptionsAllowReconnect(connectOptions, connection) && 
/* 542 */         !this.specKey.hasRebalancingListener()) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 547 */         if (!tls.isReconnectThread && 
/* 548 */           RemoteHconn.isReconnectableReasonCode(e.getReason())) {
/* 549 */           if (result != null) {
/*     */             try {
/* 551 */               result.disconnect(tls);
/*     */             }
/* 553 */             catch (JmqiException jmqiException) {}
/*     */           }
/*     */ 
/*     */           
/* 557 */           if (!parentHconn.hasFailed())
/*     */           {
/* 559 */             int sessionLockCount = connection.getSessionLock().fullyUnlock();
/* 560 */             int reconnectExecutionChildLockCount = ((RemoteParentHconn)parentHconn).fullyReleaseReconnectExecutionChildLock();
/*     */             
/* 562 */             parentHconn.eligibleForReconnect(true);
/*     */             
/*     */             try {
/* 565 */               ((RemoteParentHconn)parentHconn).waitForReconnectComplete();
/*     */               
/* 567 */               if (parentHconn.getReconnectionFailed()) {
/* 568 */                 if (Trace.isOn) {
/* 569 */                   Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "assignSessionFromConnection(RemoteTls, MQCNO, RemoteConnection, Object, String, Object, String, RemoteHconn)", (Throwable)parentHconn
/*     */                       
/* 571 */                       .getReconnectionFailureException());
/*     */                 }
/* 573 */                 throw parentHconn.getReconnectionFailureException();
/*     */               } 
/*     */             } finally {
/*     */               
/* 577 */               ((RemoteParentHconn)parentHconn).reacquireReconnectExecutionChildLock(reconnectExecutionChildLockCount);
/* 578 */               connection.getSessionLock().relock(sessionLockCount);
/*     */             } 
/* 580 */             connection = parentHconn.getParentConnection();
/* 581 */             result = assignSessionFromConnection(tls, connectOptions, connection, sendExits, sendExitsUserData, receiveExits, receiveExitsUserData, parentHconn);
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 587 */         if (Trace.isOn) {
/* 588 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "assignSessionFromConnection(RemoteTls, MQCNO, RemoteConnection, Object, String, Object, String, RemoteHconn)", (Throwable)e);
/*     */         }
/*     */         
/* 591 */         throw e;
/*     */       } 
/*     */     } 
/*     */     
/* 595 */     if (Trace.isOn) {
/* 596 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "assignSessionFromConnection(RemoteTls, MQCNO, RemoteConnection, Object, String, Object, String, RemoteHconn)", result);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 601 */     return result;
/*     */   }
/*     */   
/*     */   private boolean connectionAndOptionsAllowReconnect(MQCNO connectOptions, RemoteConnection connection) {
/* 605 */     if (Trace.isOn) {
/* 606 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "connectionAndOptionsAllowReconnect(MQCNO, RemoteConnection)", new Object[] { connectOptions, connection });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 612 */     boolean result = (connection.supportsReconnection() && (connectOptions.getOptions() & 0x5000000) != 0);
/*     */     
/* 614 */     if (Trace.isOn) {
/* 615 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "connectionAndOptionsAllowReconnect(MQCNO, RemoteConnection)", 
/* 616 */           Boolean.valueOf(result));
/*     */     }
/*     */     
/* 619 */     return result;
/*     */   }
/*     */   private RemoteConnection getNewConnection(RemoteTls tls, MQCNO connectOptions, MQCSP mqcsp, RemoteExitChainPair exitChainPair, JmqiConnectOptions.ChannelSharingMode jmsChannelSharingMode) throws JmqiException {
/*     */     RemoteConnection connection;
/*     */     JmqiException traceRet;
/* 624 */     if (Trace.isOn) {
/* 625 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getNewConnection(RemoteTls, MQCNO, MQCSP, RemoteExitChainPair,ChannelSharingMode)", new Object[] { tls, connectOptions, mqcsp, exitChainPair, jmsChannelSharingMode });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 630 */     MQCD mqcd = this.specKey.getMqcd();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 635 */     switch (mqcd.getTransportType()) {
/*     */       case 2:
/* 637 */         connection = new RemoteTCPConnection(this.env, this, this.specKey.getFap(), this.specKey.getQmgrName(), connectOptions.getOptions(), this.maxFapLevel);
/* 638 */         if (reconnectable(connection, connectOptions)) {
/* 639 */           connection.setReconnectable(true);
/*     */         }
/* 641 */         connection.setGloballyShareable((jmsChannelSharingMode != JmqiConnectOptions.ChannelSharingMode.CONNECTION));
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/* 646 */         traceRet = new JmqiException(this.env, 9915, new String[] { null, null, Integer.toString(mqcd.getTransportType()) }, 2, 2538, null);
/*     */         
/* 648 */         if (Trace.isOn) {
/* 649 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getNewConnection(RemoteTls, MQCNO, MQCSP, RemoteExitChainPair,ChannelSharingMode)", (Throwable)traceRet, 2);
/*     */         }
/*     */ 
/*     */         
/* 653 */         throw traceRet;
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 658 */       MQCD cd = (MQCD)mqcd.clone();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 663 */       cd.setVersion(12);
/* 664 */       connection.setNegotiatedChannel(cd);
/*     */     }
/* 666 */     catch (CloneNotSupportedException e) {
/* 667 */       if (Trace.isOn) {
/* 668 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getNewConnection(RemoteTls, MQCNO, MQCSP, RemoteExitChainPair,ChannelSharingMode)", e, 3);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 673 */       JmqiException jmqiException = new JmqiException(this.env, -1, null, 2, 2277, null);
/*     */       
/* 675 */       if (Trace.isOn) {
/* 676 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getNewConnection(RemoteTls, MQCNO, MQCSP, RemoteExitChainPair,ChannelSharingMode)", (Throwable)jmqiException, 3);
/*     */       }
/*     */ 
/*     */       
/* 680 */       throw jmqiException;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 685 */     connection.initSecurityExits(connection.getNegotiatedChannel(), this.specKey.getSecurityExit(), this.specKey.getSecurityExitUserData(), this.specKey
/* 686 */         .getExitClassPath());
/*     */     
/* 688 */     exitChainPair.loadAndInitExits(connection, true);
/*     */     
/*     */     try {
/* 691 */       connection.connect(tls, mqcsp);
/*     */     }
/* 693 */     catch (JmqiException je) {
/* 694 */       if (Trace.isOn) {
/* 695 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getNewConnection(RemoteTls, MQCNO, MQCSP, RemoteExitChainPair,ChannelSharingMode)", (Throwable)je, 4);
/*     */       }
/*     */ 
/*     */       
/* 699 */       connection = null;
/* 700 */       if (Trace.isOn) {
/* 701 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getNewConnection(RemoteTls, MQCNO, MQCSP, RemoteExitChainPair,ChannelSharingMode)", (Throwable)je, 4);
/*     */       }
/*     */ 
/*     */       
/* 705 */       throw je;
/*     */     } 
/*     */     
/* 708 */     if (Trace.isOn) {
/* 709 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getNewConnection(RemoteTls, MQCNO, MQCSP, RemoteExitChainPair,ChannelSharingMode)", connection);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 714 */     return connection;
/*     */   }
/*     */   
/*     */   void removeSelf() {
/* 718 */     if (Trace.isOn) {
/* 719 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "removeSelf()");
/*     */     }
/* 721 */     this.shareableConnectionsLock.lock();
/*     */     try {
/* 723 */       this.generallyShareableConnections.clear();
/*     */     } finally {
/*     */       
/* 726 */       this.shareableConnectionsLock.unlock();
/*     */     } 
/* 728 */     this.specKey.removeSelf();
/* 729 */     if (Trace.isOn) {
/* 730 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "removeSelf()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void removeConnection(RemoteConnection connection) {
/* 736 */     if (Trace.isOn) {
/* 737 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "removeConnection(RemoteConnection)", new Object[] { connection });
/*     */     }
/*     */ 
/*     */     
/* 741 */     this.shareableConnectionsLock.lock();
/*     */     try {
/* 743 */       this.generallyShareableConnections.remove(connection);
/* 744 */       if (this.generallyShareableConnections.isEmpty()) {
/* 745 */         removeSelf();
/*     */       }
/*     */     } finally {
/*     */       
/* 749 */       this.shareableConnectionsLock.unlock();
/*     */     } 
/*     */     
/* 752 */     if (Trace.isOn) {
/* 753 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "removeConnection(RemoteConnection)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MQCD getMqcd() {
/* 762 */     if (Trace.isOn) {
/* 763 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getMqcd()", "getter", this.specKey.getMqcd());
/*     */     }
/* 765 */     return this.specKey.getMqcd();
/*     */   }
/*     */   
/*     */   MQSCO getMqsco() {
/* 769 */     if (Trace.isOn) {
/* 770 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getMqsco()", "getter", this.specKey
/* 771 */           .getMqsco());
/*     */     }
/* 773 */     return this.specKey.getMqsco();
/*     */   }
/*     */   
/*     */   int getJmqiFlags() {
/* 777 */     if (Trace.isOn) {
/* 778 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getJmqiFlags()", "getter", 
/* 779 */           Integer.valueOf(this.specKey.getJmqiFlags()));
/*     */     }
/* 781 */     return this.specKey.getJmqiFlags();
/*     */   }
/*     */   
/*     */   SSLSocketFactory getSslSocketFactory() {
/* 785 */     if (Trace.isOn) {
/* 786 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getSslSocketFactory()", "getter", this.specKey
/* 787 */           .getSslSocketFactory());
/*     */     }
/* 789 */     return this.specKey.getSslSocketFactory();
/*     */   }
/*     */   
/*     */   Collection<?> getSslCertStores() {
/* 793 */     if (Trace.isOn) {
/* 794 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getSslCertStores()", "getter", this.specKey
/* 795 */           .getSslCertStores());
/*     */     }
/* 797 */     return this.specKey.getSslCertStores();
/*     */   }
/*     */   
/*     */   String getUidFlowUserId() {
/* 801 */     if (Trace.isOn) {
/* 802 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getUidFlowUserId()", "getter", this.specKey
/* 803 */           .getUidFlowUserId());
/*     */     }
/* 805 */     return this.specKey.getUidFlowUserId();
/*     */   }
/*     */   
/*     */   String getUidFlowPassword() {
/* 809 */     if (Trace.isOn) {
/* 810 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getUidFlowPassword()", "getter", "****");
/*     */     }
/*     */     
/* 813 */     return this.specKey.getUidFlowPassword();
/*     */   }
/*     */   
/*     */   int getCcsid() {
/* 817 */     if (Trace.isOn) {
/* 818 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecification", "getCcsid()", "getter", 
/* 819 */           Integer.valueOf(this.specKey.getCcsid()));
/*     */     }
/* 821 */     return this.specKey.getCcsid();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\impl\RemoteConnectionSpecification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */