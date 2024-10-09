/*     */ package com.ibm.mq.jmqi.remote.impl;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.mq.jmqi.remote.api.RemoteHconn;
/*     */ import com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT;
/*     */ import com.ibm.mq.jmqi.remote.rfp.RfpTSH;
/*     */ import com.ibm.mq.jmqi.remote.util.RemoteCommsBuffer;
/*     */ import com.ibm.mq.jmqi.remote.util.RemoteCommsBufferPoolInterface;
/*     */ import com.ibm.mq.jmqi.remote.util.RemoteSharedReceiveBufferPool;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.PrintWriter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class RemoteRcvThread
/*     */   extends JmqiObject
/*     */   implements Runnable
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteRcvThread.java";
/*     */   private static final int RECONNECT_CLIENTS_YES = 1;
/*     */   private static final int RECONNECT_CLIENTS_NO = 0;
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteRcvThread.java");
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
/*  68 */   private RemoteConnection remoteConnection = null;
/*  69 */   private RemoteCommsBuffer commsBuffer = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RemoteCommsBufferPoolInterface commsBufferPool;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private volatile boolean disconnecting = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean reconnecting = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RemoteRcvThread(JmqiEnvironment jmqiEnv, RemoteConnection remoteConn) {
/*  95 */     super(jmqiEnv);
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "<init>(JmqiEnvironment,RemoteConnection, int)", new Object[] { jmqiEnv, remoteConn });
/*     */     }
/*     */     
/* 100 */     this.remoteConnection = remoteConn;
/* 101 */     this.commsBufferPool = (RemoteCommsBufferPoolInterface)new RemoteSharedReceiveBufferPool(this.env);
/*     */     
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "<init>(JmqiEnvironment,RemoteConnection, int)");
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
/*     */   public void run() {
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "run()");
/*     */     }
/*     */     
/* 121 */     String name = "RcvThread: " + this.remoteConnection.getThreadIdentifier();
/*     */     try {
/* 123 */       if (Trace.isOn) {
/* 124 */         Trace.data(this, "run()", "setting name to", name);
/*     */       }
/* 126 */       Thread.currentThread().setName(name);
/*     */     }
/* 128 */     catch (Exception e) {
/* 129 */       if (Trace.isOn) {
/* 130 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "run()", e, 1);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     JmqiSystemEnvironment sysenv = null;
/* 138 */     if (this.env instanceof JmqiSystemEnvironment)
/*     */     {
/* 140 */       sysenv = (JmqiSystemEnvironment)this.env;
/*     */     }
/* 142 */     RemoteTls tls = this.remoteConnection.getRemoteFap().getTls();
/* 143 */     tls.isReceiveThread = true;
/*     */     
/* 145 */     JmqiTls jTls = sysenv.getJmqiTls(tls);
/*     */     
/* 147 */     boolean parentConnSendLockTaken = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 155 */       RfpTSH rTSH = null;
/*     */ 
/*     */       
/* 158 */       while (!this.reconnecting) {
/*     */         int convId, requestId; RemoteSession remoteSession; String msg; StringBuffer dumpBuffer;
/*     */         HashMap<String, Object> hashMap1;
/* 161 */         rTSH = receiveOneTSH();
/*     */ 
/*     */         
/* 164 */         if (rTSH == null) {
/*     */           break;
/*     */         }
/*     */         
/* 168 */         if (Trace.isOn) {
/* 169 */           Trace.data(this, "run()", "Received", rTSH.getTshTypeString());
/*     */         }
/*     */         
/* 172 */         if (rTSH.getTshType() == 2) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 178 */           int i = rTSH.getConvId(false);
/*     */ 
/*     */ 
/*     */           
/* 182 */           RemoteSession remoteSession1 = this.remoteConnection.getSessionByConvId(i);
/* 183 */           if (remoteSession1 != null) {
/* 184 */             remoteSession1.processReceivedTsh(tls, rTSH);
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 189 */         if (this.remoteConnection.getRemoteEncoding() != rTSH.getEncoding()) {
/* 190 */           this.remoteConnection.setRemoteEncoding(rTSH.getEncoding());
/*     */           
/* 192 */           if (this.remoteConnection.getRemoteEncoding() == 1) {
/* 193 */             this.remoteConnection.setSwap(false);
/*     */           }
/* 195 */           else if (this.remoteConnection.getRemoteEncoding() == 2) {
/* 196 */             this.remoteConnection.setSwap(true);
/*     */           }
/*     */           else {
/*     */             
/* 200 */             HashMap<String, Object> hashMap = new HashMap<>();
/* 201 */             hashMap.put("encoding", Integer.valueOf(rTSH.getEncoding()));
/* 202 */             hashMap.put("Description", "UnknownEncoding");
/* 203 */             Trace.ffst(this, "run()", "01", hashMap, null);
/* 204 */             JmqiException traceRet9 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */             
/* 206 */             if (Trace.isOn) {
/* 207 */               Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "run()", (Throwable)traceRet9, 1);
/*     */             }
/* 209 */             throw traceRet9;
/*     */           } 
/*     */         } 
/*     */         
/* 213 */         boolean swap = this.remoteConnection.isSwap();
/*     */ 
/*     */         
/* 216 */         switch (rTSH.getTshType()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           case 2:
/* 223 */             convId = rTSH.getConvId(swap);
/* 224 */             requestId = rTSH.getRequestId(swap);
/*     */ 
/*     */             
/* 227 */             remoteSession = this.remoteConnection.getSessionByConvId(convId);
/*     */ 
/*     */             
/* 230 */             if (remoteSession != null && remoteSession.isEndRequested()) {
/* 231 */               if (Trace.isOn) {
/* 232 */                 Trace.data(this, "run()", "Ignored TSH for conversation " + convId + ". RequestID", 
/* 233 */                     Integer.valueOf(requestId));
/*     */               }
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               continue;
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/* 244 */             if (requestId == 0) {
/*     */               
/* 246 */               if (remoteSession != null) {
/* 247 */                 remoteSession.deliverTSH(rTSH);
/*     */                 
/*     */                 continue;
/*     */               } 
/*     */               
/* 252 */               if (convId == 1) {
/* 253 */                 this.remoteConnection.deliverTSH(rTSH);
/*     */                 
/*     */                 continue;
/*     */               } 
/*     */               
/* 258 */               if (Trace.isOn) {
/* 259 */                 Trace.data(this, "run()", "Ignored sync TSH for unknown Hconn. RequestID", 
/* 260 */                     Integer.valueOf(requestId));
/*     */               }
/*     */ 
/*     */               
/*     */               continue;
/*     */             } 
/*     */ 
/*     */             
/* 268 */             if (requestId == 1) {
/*     */               RemoteProxyQueueManager proxyQueueManager;
/*     */               
/* 271 */               if (remoteSession == null) {
/* 272 */                 if (Trace.isOn) {
/* 273 */                   Trace.data(this, "run()", "Ignored async TSH for unknown Hconn. RequestID", 
/* 274 */                       Integer.valueOf(requestId));
/*     */                 }
/*     */                 
/*     */                 continue;
/*     */               } 
/* 279 */               RemoteHconn remoteHconn = remoteSession.getHconn();
/* 280 */               if (remoteHconn == null) {
/* 281 */                 if (Trace.isOn) {
/* 282 */                   Trace.data(this, "run()", "Ignored async TSH for unknown Hconn. RequestID", 
/* 283 */                       Integer.valueOf(requestId));
/*     */                 }
/*     */ 
/*     */                 
/*     */                 continue;
/*     */               } 
/*     */               
/* 290 */               int segmentType = rTSH.getSegmentType();
/*     */               
/* 292 */               switch (segmentType) {
/*     */                 
/*     */                 case 13:
/*     */                 case 15:
/* 296 */                   proxyQueueManager = remoteHconn.getProxyQueueManager();
/*     */ 
/*     */                   
/* 299 */                   if (proxyQueueManager == null) {
/* 300 */                     HashMap<String, Object> hashMap2 = new HashMap<>();
/* 301 */                     hashMap2.put("Session", remoteSession);
/* 302 */                     hashMap2.put("Description", "Flow received for non-multiplexed connection");
/* 303 */                     Trace.ffst(this, "run()", "02", hashMap2, null);
/* 304 */                     JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */                     
/* 306 */                     if (Trace.isOn) {
/* 307 */                       Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "run()", (Throwable)traceRet1, 2);
/*     */                     }
/*     */                     
/* 310 */                     throw traceRet1;
/*     */                   } 
/*     */                   
/* 313 */                   if (segmentType == 13) {
/* 314 */                     proxyQueueManager.addMessage(tls, rTSH, remoteSession);
/*     */                   }
/* 316 */                   else if (segmentType == 15) {
/* 317 */                     proxyQueueManager.receiveNotification(tls, rTSH, remoteSession);
/*     */                   } 
/* 319 */                   remoteSession.releaseReceivedTSH(rTSH);
/*     */                   continue;
/*     */               } 
/*     */               
/* 323 */               HashMap<String, Object> hashMap = new HashMap<>();
/* 324 */               hashMap.put("SegmentType", Integer.valueOf(rTSH.getSegmentType()));
/* 325 */               hashMap.put("ControlFlags1", Integer.valueOf(rTSH.getControlFlags1()));
/* 326 */               hashMap.put("Description", "Unexpected flow received from server");
/* 327 */               Trace.ffst(this, "run()", "03", hashMap, null);
/* 328 */               JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */               
/* 330 */               if (Trace.isOn) {
/* 331 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "run()", (Throwable)traceRet2, 3);
/*     */               }
/*     */               
/* 334 */               throw traceRet2;
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 340 */             if (requestId % 2 != 0) {
/*     */ 
/*     */               
/* 343 */               if (remoteSession == null) {
/* 344 */                 if (Trace.isOn) {
/* 345 */                   Trace.data(this, "run()", "Ignored exchange TSH for unknown Hconn. RequestID", 
/* 346 */                       Integer.valueOf(requestId));
/*     */                 }
/*     */                 
/*     */                 continue;
/*     */               } 
/* 351 */               remoteSession.deliverExchangeReply(tls, requestId, rTSH);
/*     */               
/*     */               continue;
/*     */             } 
/*     */             
/* 356 */             msg = "Invalid request identifier in TSH";
/* 357 */             dumpBuffer = new StringBuffer();
/* 358 */             JmqiTools.hexDump(rTSH.getRfpBuffer(), null, rTSH.getRfpOffset(), rTSH.getTransLength(), dumpBuffer);
/*     */             
/* 360 */             hashMap1 = new HashMap<>();
/* 361 */             hashMap1.put("Requestid", Integer.valueOf(requestId));
/* 362 */             hashMap1.put("rfpBuffer", dumpBuffer);
/* 363 */             hashMap1.put("Description", msg);
/* 364 */             Trace.ffst(this, "sendTSH(RemoteTls,RfpTSH,RemoteSession)", "01", hashMap1, null);
/*     */             continue;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           case 1:
/* 371 */             if (Trace.isOn) {
/* 372 */               Trace.data(this, "run()", "Got TSH with segmentType", rTSH.getSegmentTypeString());
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 378 */             if (rTSH.getSegmentType() == 12) {
/*     */               boolean confirmRequest; int convIdToEnd; RemoteSession sessionToClose; RfpTSH satTSH; RfpSOCKACT sat; RfpTSH tsh; RfpSOCKACT sAct; int translength; String qmName, qmId;
/* 380 */               RfpSOCKACT sockAct = new RfpSOCKACT(this.env, rTSH.getRfpBuffer(), rTSH.getRfpOffset() + rTSH.hdrSize());
/*     */               
/* 382 */               if (Trace.isOn) {
/* 383 */                 Trace.data(this, "run()", "Socket Action", sockAct.getTypeString(swap));
/*     */               }
/*     */               
/* 386 */               switch (sockAct.getType(swap)) {
/*     */ 
/*     */ 
/*     */                 
/*     */                 case 2:
/* 391 */                   confirmRequest = false;
/* 392 */                   if ((rTSH.getControlFlags1() & 0x1) != 0) {
/* 393 */                     if (Trace.isOn) {
/* 394 */                       Trace.data(this, "run()", "rfpSAT_END_CONV request with rfpTCF_CONFIRM_REQUEST received", null);
/*     */                     }
/*     */                     
/* 397 */                     confirmRequest = true;
/*     */                   } 
/* 399 */                   convIdToEnd = sockAct.getConversationId(swap);
/* 400 */                   sessionToClose = this.remoteConnection.getSessionByConvId(convIdToEnd);
/* 401 */                   if (sessionToClose != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 407 */                     this.remoteConnection.removeSession(tls, sessionToClose, confirmRequest);
/* 408 */                     sessionToClose.setDisconnected();
/*     */ 
/*     */ 
/*     */                     
/* 412 */                     JmqiException sessionException = new JmqiException(this.env, -1, null, 2, 2009, null);
/*     */                     
/* 414 */                     sessionToClose.asyncFailureNotify((Throwable)sessionException); break;
/*     */                   } 
/* 416 */                   if (convIdToEnd == 1) {
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 421 */                     this.remoteConnection.asyncConnectionBroken(tls, null, null, null);
/*     */                     
/*     */                     break;
/*     */                   } 
/* 425 */                   if (Trace.isOn) {
/* 426 */                     Trace.data(this, "run()", "hConn not recognised by connection", 
/* 427 */                         Integer.valueOf(sockAct.getConversationId(swap)));
/*     */                   }
/*     */                   break;
/*     */ 
/*     */                 
/*     */                 case 1:
/*     */                   continue;
/*     */ 
/*     */                 
/*     */                 case 5:
/* 437 */                   this.remoteConnection.performSecureKeyReset();
/*     */ 
/*     */                   
/* 440 */                   satTSH = this.remoteConnection.allocateTSH(1, 12, null);
/*     */ 
/*     */ 
/*     */                   
/* 444 */                   satTSH.setTransLength(48);
/*     */ 
/*     */                   
/* 447 */                   sat = new RfpSOCKACT(this.env, satTSH.getRfpBuffer(), satTSH.getRfpOffset() + 28);
/* 448 */                   sat.setConversationId(0, swap);
/* 449 */                   sat.setRequestId(0, swap);
/* 450 */                   sat.setType(6, swap);
/*     */                   
/* 452 */                   this.remoteConnection.sendTSH(tls, satTSH, null);
/*     */                   
/* 454 */                   if (this.remoteConnection.getKeyReset() == 1) {
/* 455 */                     this.remoteConnection.notifyKeyReset();
/*     */                     break;
/*     */                   } 
/* 458 */                   this.remoteConnection.releaseSendLock();
/* 459 */                   parentConnSendLockTaken = false;
/*     */                   break;
/*     */                 
/*     */                 case 4:
/* 463 */                   if (this.remoteConnection.isMultiplexingEnabled() && this.remoteConnection.getKeyReset() == 1) {
/*     */                     continue;
/*     */                   }
/* 466 */                   this.remoteConnection.requestSendLock();
/* 467 */                   parentConnSendLockTaken = true;
/* 468 */                   this.remoteConnection.setKeyReset(2);
/*     */ 
/*     */                   
/* 471 */                   tsh = this.remoteConnection.allocateTSH(1, 12, null);
/*     */ 
/*     */                   
/* 474 */                   tsh.setTransLength(48);
/*     */ 
/*     */                   
/* 477 */                   sAct = new RfpSOCKACT(this.env, tsh.getRfpBuffer(), tsh.getRfpOffset() + 28);
/* 478 */                   sAct.setConversationId(0, swap);
/* 479 */                   sAct.setRequestId(0, swap);
/* 480 */                   sAct.setType(4, swap);
/*     */                   
/* 482 */                   this.remoteConnection.sendTSH(tls, tsh, null);
/*     */                   break;
/*     */                 
/*     */                 case 6:
/*     */                   continue;
/*     */                 case 3:
/* 488 */                   this.remoteConnection.chlQuiescing();
/*     */                   continue;
/*     */ 
/*     */ 
/*     */                 
/*     */                 case 7:
/* 494 */                   this.remoteConnection.qmQuiescing();
/*     */                   break;
/*     */                 
/*     */                 case 9:
/* 498 */                   if (!this.remoteConnection.isRebalancedByResourceAdapter()) {
/* 499 */                     this.reconnecting = true;
/*     */                   }
/* 501 */                   switch (sockAct.getParm1(swap)) {
/*     */                     case 0:
/* 503 */                       this.remoteConnection.notifyReconnect(tls, false, null, null);
/*     */                       break;
/*     */                     case 1:
/* 506 */                       translength = rTSH.getTransLength();
/* 507 */                       qmName = null;
/* 508 */                       qmId = null;
/* 509 */                       if (translength >= 96) {
/* 510 */                         qmName = sockAct.getQMgrName(this.remoteConnection.getCp(), jTls);
/*     */                       }
/* 512 */                       if (translength >= 144) {
/* 513 */                         qmId = sockAct.getQmId(this.remoteConnection.getCp(), jTls);
/*     */                       }
/* 515 */                       this.remoteConnection.notifyReconnect(tls, true, qmName, qmId);
/*     */                       break;
/*     */                   } 
/*     */ 
/*     */                   
/*     */                   break;
/*     */               } 
/*     */             
/* 523 */             } else if ((rTSH.getControlFlags1() & 0x8) != 0) {
/*     */ 
/*     */               
/* 526 */               this.remoteConnection.analyseErrorSegment(rTSH);
/*     */             }
/* 528 */             else if (rTSH.getSegmentType() == 9) {
/*     */ 
/*     */ 
/*     */               
/* 532 */               if ((this.remoteConnection.getFapLevel() >= 10) ? ((
/* 533 */                 rTSH.getControlFlags1() & 0x1) == 0) : ((
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 539 */                 rTSH.getControlFlags1() & 0x1) != 0)) {
/*     */                 continue;
/*     */               }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 549 */               if (this.remoteConnection.getFapLevel() < 8 && this.remoteConnection.getSecureKeyResetCount() > 0 && this.remoteConnection.isSecure()) {
/* 550 */                 this.remoteConnection.performSecureKeyReset();
/*     */               }
/*     */ 
/*     */               
/* 554 */               this.remoteConnection.sendHeartbeat(tls, 2);
/*     */             }
/* 556 */             else if (rTSH.getSegmentType() == 11) {
/*     */ 
/*     */ 
/*     */               
/* 560 */               this.remoteConnection.performSecureKeyReset();
/*     */               
/* 562 */               RfpTSH keyResetTSH = this.remoteConnection.allocateTSH(1, 5, null);
/*     */               
/* 564 */               keyResetTSH.setTransLength(keyResetTSH.hdrSize());
/* 565 */               keyResetTSH.setControlFlags1(64);
/* 566 */               this.remoteConnection.sendTSH(tls, keyResetTSH, null);
/*     */             } 
/*     */             
/* 569 */             this.remoteConnection.releaseReceivedTSH(rTSH);
/*     */             continue;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 577 */         HashMap<String, Object> info = new HashMap<>();
/* 578 */         info.put("Description", "Invalid TSH received on multiplexed connection");
/* 579 */         Trace.ffst(this, "run()", "03", info, null);
/*     */         
/* 581 */         JmqiException e = new JmqiException(this.env, -1, null, 2, 2009, null);
/*     */         
/* 583 */         if (Trace.isOn) {
/* 584 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "run()", (Throwable)e, 4);
/*     */         }
/* 586 */         throw e;
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 591 */     catch (Exception e) {
/* 592 */       if (Trace.isOn) {
/* 593 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "run()", e, 2);
/*     */       }
/*     */ 
/*     */       
/* 597 */       JmqiException je = null;
/* 598 */       if (e instanceof JmqiException) {
/* 599 */         je = (JmqiException)e;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 604 */         String exceptionLocation = null;
/* 605 */         StackTraceElement[] stackTrace = e.getStackTrace();
/* 606 */         if (stackTrace.length > 0) {
/* 607 */           StackTraceElement element = stackTrace[0];
/* 608 */           String className = element.getClassName();
/* 609 */           String methodName = element.getMethodName();
/* 610 */           int lineNumber = element.getLineNumber();
/*     */           
/* 612 */           StringBuffer sb = new StringBuffer();
/* 613 */           sb.append(className);
/* 614 */           sb.append(".");
/* 615 */           sb.append(methodName);
/* 616 */           sb.append(" ");
/* 617 */           sb.append("[");
/* 618 */           sb.append(lineNumber);
/* 619 */           sb.append("]");
/* 620 */           exceptionLocation = sb.toString();
/*     */         } 
/*     */         
/* 623 */         String exceptionCause = null;
/* 624 */         Throwable cause = e.getCause();
/* 625 */         if (cause != null) {
/* 626 */           exceptionCause = JmqiTools.getExSumm(cause);
/*     */         }
/*     */         
/* 629 */         HashMap<String, Object> info = new HashMap<>();
/* 630 */         info.put("Exception summary", JmqiTools.getExSumm(e));
/* 631 */         info.put("Exception Location", exceptionLocation);
/* 632 */         info.put("Exception cause", exceptionCause);
/* 633 */         info.put("Description", "Unexpected Exception");
/* 634 */         Trace.ffst(this, "run()", "04", info, null);
/*     */ 
/*     */         
/* 637 */         je = new JmqiException(this.env, -1, null, 2, 2195, e);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 644 */         this.remoteConnection.asyncConnectionBroken(tls, (Throwable)je, null, null);
/*     */       }
/* 646 */       catch (JmqiException e2) {
/* 647 */         if (Trace.isOn) {
/* 648 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "run()", (Throwable)e2, 3);
/*     */         
/*     */         }
/*     */       }
/* 652 */       catch (Throwable t) {
/* 653 */         if (Trace.isOn) {
/* 654 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "run()", t, 4);
/*     */         }
/* 656 */         String causeMessage = null;
/* 657 */         Throwable cause = t.getCause();
/* 658 */         if (cause != null) {
/* 659 */           causeMessage = cause.getMessage();
/*     */         }
/* 661 */         HashMap<String, Object> info = new HashMap<>();
/* 662 */         info.put("Exception summary", JmqiTools.getExSumm(t));
/* 663 */         info.put("Exception cause message", causeMessage);
/* 664 */         info.put("Description", "Unexpected Exception");
/* 665 */         info.put("exeception", t);
/* 666 */         Trace.ffst(this, "run()", "05", info, t.getClass());
/*     */       } 
/*     */     } finally {
/*     */       
/* 670 */       if (Trace.isOn) {
/* 671 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "run()");
/*     */       }
/* 673 */       if (parentConnSendLockTaken) {
/* 674 */         this.remoteConnection.releaseSendLock();
/* 675 */         parentConnSendLockTaken = false;
/*     */       } 
/*     */       
/* 678 */       tls.isReceiveThread = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 684 */     if (!this.disconnecting) {
/*     */       try {
/* 686 */         this.remoteConnection.disconnect(tls);
/*     */       }
/* 688 */       catch (JmqiException e) {
/* 689 */         if (Trace.isOn) {
/* 690 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "run()", (Throwable)e, 5);
/*     */         
/*     */         }
/*     */       }
/* 694 */       catch (Throwable t) {
/* 695 */         if (Trace.isOn) {
/* 696 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "run()", t, 6);
/*     */         }
/* 698 */         String causeMessage = null;
/* 699 */         Throwable cause = t.getCause();
/* 700 */         if (cause != null) {
/* 701 */           causeMessage = cause.getMessage();
/*     */         }
/* 703 */         HashMap<String, Object> info = new HashMap<>();
/* 704 */         info.put("Exception summary", JmqiTools.getExSumm(t));
/* 705 */         info.put("Exception cause message", causeMessage);
/* 706 */         info.put("Description", "Unexpected Throwable");
/* 707 */         info.put("exeception", t);
/* 708 */         Trace.ffst(this, "run()", "05", info, t.getClass());
/*     */       } 
/*     */     }
/*     */     
/* 712 */     if (Trace.isOn) {
/* 713 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "run()");
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
/*     */   private RfpTSH receiveOneTSH() throws JmqiException {
/* 730 */     if (Trace.isOn) {
/* 731 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "receiveOneTSH()");
/*     */     }
/* 733 */     int dataAvailable = 0;
/* 734 */     int size = 0;
/* 735 */     RfpTSH rTSH = new RfpTSH(this.env, null, 0);
/* 736 */     while (rTSH != null) {
/* 737 */       if (this.commsBuffer != null && (dataAvailable = this.commsBuffer.getDataAvailable()) >= 8) {
/*     */ 
/*     */         
/* 740 */         int dataUsed = this.commsBuffer.getDataUsed();
/* 741 */         rTSH.setRfpBuffer(this.commsBuffer.getBuffer());
/* 742 */         rTSH.setRfpOffset(dataUsed);
/* 743 */         rTSH.setParentBuffer(this.commsBuffer);
/*     */ 
/*     */         
/* 746 */         rTSH.checkEyecatcher();
/*     */         
/* 748 */         size = rTSH.getTransLength();
/*     */         
/* 750 */         if (size <= dataAvailable) {
/*     */ 
/*     */           
/* 753 */           this.commsBuffer.addUseCount(1);
/*     */           
/* 755 */           this.commsBuffer.setDataAvailable(dataAvailable - size);
/* 756 */           this.commsBuffer.setDataUsed(dataUsed + size);
/*     */ 
/*     */           
/* 759 */           if (Trace.isOn) {
/* 760 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "receiveOneTSH()", rTSH, 1);
/*     */           }
/* 762 */           return rTSH;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 771 */       RemoteCommsBuffer newBuffer = this.commsBufferPool.allocBuffer(this.remoteConnection.getMaxTransmissionSize() + 8);
/*     */       
/* 773 */       if (dataAvailable != 0) {
/*     */         
/* 775 */         System.arraycopy(this.commsBuffer.getBuffer(), this.commsBuffer.getDataUsed(), newBuffer.getBuffer(), 0, dataAvailable);
/*     */         
/* 777 */         newBuffer.setDataAvailable(dataAvailable);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 783 */       if (this.commsBuffer != null) {
/* 784 */         this.commsBuffer.free();
/*     */       }
/*     */       
/* 787 */       this.commsBuffer = newBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 798 */       int bytesRead = receiveBuffer();
/*     */ 
/*     */       
/* 801 */       if (bytesRead < 0) {
/* 802 */         rTSH = null;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 811 */     if (Trace.isOn) {
/* 812 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "receiveOneTSH()", null, 2);
/*     */     }
/* 814 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int receiveBuffer() throws JmqiException {
/*     */     int bytesRead;
/* 825 */     if (Trace.isOn) {
/* 826 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "receiveBuffer()");
/*     */     }
/* 828 */     int availableBeforeRecv = this.commsBuffer.getDataAvailable();
/* 829 */     int bytesInBuffer = availableBeforeRecv + this.commsBuffer.getDataUsed();
/*     */     
/* 831 */     byte[] commsArray = this.commsBuffer.getBuffer();
/*     */     try {
/* 833 */       bytesRead = this.remoteConnection.receive(commsArray, bytesInBuffer, commsArray.length - bytesInBuffer);
/*     */     }
/* 835 */     catch (JmqiException e) {
/* 836 */       if (Trace.isOn) {
/* 837 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "receiveBuffer()", (Throwable)e);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 843 */       if (this.disconnecting) {
/* 844 */         bytesRead = -1;
/*     */       } else {
/*     */         
/* 847 */         if (Trace.isOn) {
/* 848 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "receiveBuffer()", (Throwable)e, 1);
/*     */         }
/* 850 */         throw e;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 855 */     if (!this.disconnecting && bytesRead < 0) {
/*     */ 
/*     */       
/* 858 */       JmqiException e = new JmqiException(this.env, 9208, new String[] { Integer.toString(bytesRead), Integer.toHexString(bytesRead), this.remoteConnection.getRemoteHostDescr(), this.remoteConnection.getTrpType() }, 2, 2009, null);
/*     */       
/* 860 */       if (Trace.isOn) {
/* 861 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "receiveBuffer()", (Throwable)e, 2);
/*     */       }
/* 863 */       throw e;
/*     */     } 
/*     */ 
/*     */     
/* 867 */     if (bytesRead > 0) {
/*     */ 
/*     */       
/* 870 */       this.commsBuffer.setDataAvailable(availableBeforeRecv + bytesRead);
/*     */ 
/*     */       
/* 873 */       if (this.remoteConnection.getSecureKeyResetCount() > 0 && this.remoteConnection.isSecure()) {
/* 874 */         this.remoteConnection.setBytesSinceKeyReset(this.remoteConnection.getBytesSinceKeyReset() + bytesRead);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 881 */       if (this.remoteConnection.getFapLevel() >= 8 && this.remoteConnection.getBytesSinceKeyReset() > this.remoteConnection.getSecureKeyResetCount() && this.remoteConnection.isSecure()) {
/* 882 */         this.remoteConnection.setHeartbeatKeyResetRequired(true);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 887 */     if (Trace.isOn) {
/* 888 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "receiveBuffer()", 
/* 889 */           Integer.valueOf(bytesRead));
/*     */     }
/* 891 */     return bytesRead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setDisconnecting() {
/* 899 */     if (Trace.isOn) {
/* 900 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "setDisconnecting()");
/*     */     }
/* 902 */     this.disconnecting = true;
/*     */     
/* 904 */     if (Trace.isOn) {
/* 905 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "setDisconnecting()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void dump(PrintWriter pw, int level) {
/* 911 */     if (Trace.isOn) {
/* 912 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "dump(PrintWriter,int)", new Object[] { pw, 
/* 913 */             Integer.valueOf(level) });
/*     */     }
/* 915 */     String prefix = Trace.buildPrefix(level);
/* 916 */     pw.format("%s%s%n", new Object[] { prefix.toString(), toString() });
/* 917 */     if (Trace.isOn)
/* 918 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteRcvThread", "dump(PrintWriter,int)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\impl\RemoteRcvThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */