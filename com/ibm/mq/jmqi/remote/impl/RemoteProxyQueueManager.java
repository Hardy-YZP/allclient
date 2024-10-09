/*     */ package com.ibm.mq.jmqi.remote.impl;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.remote.api.RemoteHconn;
/*     */ import com.ibm.mq.jmqi.remote.api.RemoteHobj;
/*     */ import com.ibm.mq.jmqi.remote.rfp.RfpASYNC_MESSAGE;
/*     */ import com.ibm.mq.jmqi.remote.rfp.RfpNOTIFICATION;
/*     */ import com.ibm.mq.jmqi.remote.rfp.RfpStructure;
/*     */ import com.ibm.mq.jmqi.remote.rfp.RfpTSH;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemoteProxyQueueManager
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteProxyQueueManager.java";
/*     */   
/*     */   static {
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteProxyQueueManager.java");
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
/*  64 */   private Map<Long, RemoteProxyQueue> proxyQueues = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private RemoteHconn hconn;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteProxyQueueManager(JmqiEnvironment env, RemoteHconn hconn) {
/*  78 */     super(env);
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "<init>(JmqiEnvironment,RemoteHconn)", new Object[] { env, hconn });
/*     */     }
/*     */     
/*  83 */     this.hconn = hconn;
/*     */     
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "<init>(JmqiEnvironment,RemoteHconn)");
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
/*     */   public RemoteProxyQueue createProxyQueue(RemoteTls tls) throws JmqiException {
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "createProxyQueue(RemoteTls)", new Object[] { tls });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 106 */     RemoteProxyQueue proxyQueue = new RemoteProxyQueue(this.env, this.hconn);
/*     */     
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "createProxyQueue(RemoteTls)", proxyQueue);
/*     */     }
/*     */     
/* 112 */     return proxyQueue;
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
/*     */   private synchronized RemoteProxyQueue findProxyQueue(RemoteTls tls, Long clientId) throws JmqiException {
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "findProxyQueue(RemoteTls,LOng)", new Object[] { tls, clientId });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 130 */     RemoteProxyQueue proxyQueue = this.proxyQueues.get(clientId);
/*     */     
/* 132 */     if (proxyQueue == null) {
/* 133 */       HashMap<String, Object> info = new HashMap<>();
/* 134 */       info.put("clientId", clientId);
/* 135 */       info.put("Description", "No proxy queue with this clientId");
/* 136 */       Trace.ffst(this, "findProxyQueue(RemoteTls,Long)", "01", info, null);
/* 137 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */       
/* 139 */       if (Trace.isOn) {
/* 140 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "findProxyQueue(RemoteTls,Long)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 143 */       throw traceRet1;
/*     */     } 
/*     */     
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "findProxyQueue(RemoteTls,Long)", proxyQueue);
/*     */     }
/*     */     
/* 150 */     return proxyQueue;
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
/*     */   public synchronized void setIdentifier(RemoteTls tls, RemoteProxyQueue proxyQueue, RemoteHobj hobj) throws JmqiException {
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "setIdentifier(RemoteTls,RemoteProxyQueue,RemoteHobj)", new Object[] { tls, proxyQueue, hobj });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 171 */     RemoteHobj originalHobj = proxyQueue.getHobj();
/* 172 */     if (originalHobj != null && originalHobj.getOldIdentifier() != null) {
/* 173 */       this.proxyQueues.remove(originalHobj.getOldIdentifier());
/*     */     }
/*     */     
/* 176 */     proxyQueue.setIdentifier(tls, hobj);
/* 177 */     this.proxyQueues.put(hobj.getIdentifier(), proxyQueue);
/*     */     
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "setIdentifier(RemoteTls,RemoteProxyQueue,RemoteHobj)");
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
/*     */   public synchronized void deleteProxyQueue(RemoteTls tls, RemoteProxyQueue proxyQueue) throws JmqiException {
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "deleteProxyQueue(RemoteTls,RemoteProxyQueue)", new Object[] { tls, proxyQueue });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 201 */     RemoteHobj hobj = proxyQueue.getHobj();
/* 202 */     if (hobj != null) {
/* 203 */       Long identifier = hobj.getIdentifier();
/*     */       
/* 205 */       RemoteProxyQueue hashTableQueue = this.proxyQueues.get(identifier);
/* 206 */       if (hashTableQueue != proxyQueue) {
/*     */         
/* 208 */         HashMap<String, Object> info = new HashMap<>();
/* 209 */         info.put("proxyQueue to be deleted", proxyQueue);
/* 210 */         info.put("hobj identifier", hobj.getIdentifier());
/* 211 */         info.put("hobj old identifier", hobj.getOldIdentifier());
/* 212 */         info.put("proxyQueue found in hashtable", hashTableQueue);
/* 213 */         StringBuffer sb = new StringBuffer();
/* 214 */         for (Map.Entry<Long, RemoteProxyQueue> e : this.proxyQueues.entrySet()) {
/* 215 */           sb.append(String.format("Indentifier %x rpq %s\n", new Object[] { e.getKey(), e.getValue() }));
/*     */         } 
/* 217 */         info.put("proxyQueues found", sb.toString());
/* 218 */         info.put("Description", "Desired proxyQueue not found");
/* 219 */         Trace.ffst(this, "deleteProxyQueue(RemoteTls,RemoteProxyQueue)", "01", info, null);
/* 220 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/* 221 */         if (Trace.isOn) {
/* 222 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "deleteProxyQueue(RemoteTls,RemoteProxyQueue)", (Throwable)traceRet1);
/*     */         }
/*     */         
/* 225 */         throw traceRet1;
/*     */       } 
/*     */ 
/*     */       
/* 229 */       this.proxyQueues.remove(identifier);
/*     */     } 
/*     */     
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "deleteProxyQueue(RemoteTls,RemoteProxyQueue)");
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
/*     */   void addMessage(RemoteTls tls, RfpTSH tsh, RemoteSession remoteSession) throws JmqiException {
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "addMessage(RemoteTls,RfpTSH, Remotesession)", new Object[] { tls, tsh, remoteSession });
/*     */     }
/*     */ 
/*     */     
/* 253 */     RfpASYNC_MESSAGE async = (RfpASYNC_MESSAGE)RfpStructure.newRfp(this.env, 18, tsh.getRfpBuffer(), tsh.getRfpOffset() + tsh.hdrSize());
/*     */ 
/*     */     
/* 256 */     RemoteProxyQueue proxyQueue = findProxyQueue(tls, remoteSession.generateIdentifier(async.getHobj(remoteSession.isSwap())));
/*     */ 
/*     */     
/* 259 */     proxyQueue.addMessage(tls, tsh, async, remoteSession);
/*     */     
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "addMessage(RemoteTls,RfpTSH, RemoteSession)");
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
/*     */   void receiveNotification(RemoteTls tls, RfpTSH tsh, RemoteSession remoteSession) throws JmqiException {
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "receiveNotification(RemoteTls,RfpTSH,RemoteSession)", new Object[] { tls, tsh, remoteSession });
/*     */     }
/*     */ 
/*     */     
/* 282 */     RfpNOTIFICATION notification = (RfpNOTIFICATION)RfpStructure.newRfp(this.env, 20, tsh.getRfpBuffer(), tsh.getRfpOffset() + tsh.hdrSize());
/*     */ 
/*     */     
/* 285 */     RemoteProxyQueue proxyQueue = findProxyQueue(tls, remoteSession.generateIdentifier(notification.getHobj(remoteSession.isSwap())));
/*     */ 
/*     */     
/* 288 */     proxyQueue.receiveNotification(tls, tsh, notification, remoteSession);
/*     */     
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "receiveNotification(RemoteTls,RfpTSH,RemoteSession)");
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
/*     */   public boolean checkClientEmpty() {
/* 303 */     if (Trace.isOn) {
/* 304 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "checkClientEmpty()");
/*     */     }
/*     */     
/* 307 */     boolean txnFound = false;
/* 308 */     for (RemoteProxyQueue rpq : this.proxyQueues.values()) {
/*     */ 
/*     */ 
/*     */       
/* 312 */       if (rpq != null && rpq.isCbAlreadyRegistered() && (rpq.getStatus() & 0x40000) == 0) {
/*     */ 
/*     */ 
/*     */         
/* 316 */         if (!rpq.isEmpty()) {
/*     */           
/* 318 */           if (Trace.isOn) {
/* 319 */             Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "checkClientEmpty()", 
/* 320 */                 Boolean.valueOf(false), 1);
/*     */           }
/* 322 */           return false;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 328 */         if ((rpq.getStatus() & 0x10000000) != 0) {
/* 329 */           txnFound = true;
/*     */         }
/*     */       } 
/*     */     } 
/* 333 */     if (Trace.isOn) {
/* 334 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "checkClientEmpty()", 
/* 335 */           Boolean.valueOf(txnFound), 2);
/*     */     }
/* 337 */     return txnFound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void notifyConnectionFailure(Throwable asyncFailure) {
/* 344 */     if (Trace.isOn) {
/* 345 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "notifyConnectionFailure(Throwable)", new Object[] { asyncFailure });
/*     */     }
/*     */     
/* 348 */     for (RemoteProxyQueue rpq : this.proxyQueues.values()) {
/* 349 */       rpq.notifyConnectionFailure(asyncFailure);
/*     */     }
/*     */     
/* 352 */     if (Trace.isOn) {
/* 353 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "notifyConnectionFailure(Throwable)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void wakeGetters() {
/* 363 */     if (Trace.isOn) {
/* 364 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "wakeGetters()");
/*     */     }
/*     */     
/* 367 */     for (RemoteProxyQueue rpq : this.proxyQueues.values()) {
/* 368 */       rpq.releaseWaitingGetters();
/*     */     }
/*     */     
/* 371 */     if (Trace.isOn) {
/* 372 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "wakeGetters()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkTxnMessage(RemoteTls tls) throws JmqiException {
/* 382 */     if (Trace.isOn) {
/* 383 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "checkTxnMessage(RemoteTls)", new Object[] { tls });
/*     */     }
/*     */ 
/*     */     
/* 387 */     for (RemoteProxyQueue proxyQueue : this.proxyQueues.values()) {
/* 388 */       proxyQueue.checkTxnMessage(tls);
/*     */     }
/*     */     
/* 391 */     if (Trace.isOn) {
/* 392 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "checkTxnMessage(RemoteTls)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearAllTxnMessages(RemoteTls tls) throws JmqiException {
/* 403 */     if (Trace.isOn) {
/* 404 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "clearAllTxnMessages(RemoteTls)", new Object[] { tls });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 409 */     for (RemoteProxyQueue proxyQueue : this.proxyQueues.values()) {
/* 410 */       proxyQueue.clearAllTxnMessages(tls);
/*     */     }
/*     */     
/* 413 */     if (Trace.isOn) {
/* 414 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "clearAllTxnMessages(RemoteTls)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void raiseEvent(RemoteConstants.Event ed) throws JmqiException {
/* 425 */     if (Trace.isOn) {
/* 426 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "raiseEvent(EventDescriptor)", new Object[] { ed });
/*     */     }
/*     */ 
/*     */     
/* 430 */     for (RemoteProxyQueue proxyQueue : this.proxyQueues.values()) {
/* 431 */       proxyQueue.raiseEvent(ed);
/*     */     }
/*     */     
/* 434 */     if (Trace.isOn) {
/* 435 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "raiseEvent(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(PrintWriter pw, int level) {
/* 445 */     if (Trace.isOn) {
/* 446 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "dump(PrintWriter,int)", new Object[] { pw, 
/* 447 */             Integer.valueOf(level) });
/*     */     }
/* 449 */     String prefix = Trace.buildPrefix(level);
/* 450 */     pw.format("%s%s\n", new Object[] { prefix, toString() });
/*     */     try {
/* 452 */       for (RemoteProxyQueue queue : this.proxyQueues.values()) {
/* 453 */         queue.dump(pw, level + 1);
/*     */       }
/*     */     }
/* 456 */     catch (ConcurrentModificationException cme) {
/* 457 */       if (Trace.isOn) {
/* 458 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "dump(PrintWriter,int)", cme);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 464 */     if (Trace.isOn)
/* 465 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyQueueManager", "dump(PrintWriter,int)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\impl\RemoteProxyQueueManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */