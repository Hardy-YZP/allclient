/*     */ package com.ibm.mq.jmqi.remote.api;
/*     */ 
/*     */ import com.ibm.mq.constants.MQConstants;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQCBD;
/*     */ import com.ibm.mq.jmqi.MQGMO;
/*     */ import com.ibm.mq.jmqi.MQMD;
/*     */ import com.ibm.mq.jmqi.MQOD;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
/*     */ import com.ibm.mq.jmqi.handles.Phobj;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue;
/*     */ import com.ibm.mq.jmqi.remote.impl.RemoteSession;
/*     */ import com.ibm.mq.jmqi.system.SpiOpenOptions;
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
/*     */ 
/*     */ 
/*     */ public class RemoteHobj
/*     */   extends JmqiObject
/*     */   implements Hobj
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/api/RemoteHobj.java";
/*     */   
/*     */   static {
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.data("com.ibm.mq.jmqi.remote.api.RemoteHobj", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/api/RemoteHobj.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   private int hobj = -1;
/*     */ 
/*     */   
/*     */   private int defaultPersistence;
/*     */ 
/*     */   
/*     */   private int defaultPutResponseType;
/*     */ 
/*     */   
/*     */   private int defaultReadAhead;
/*     */ 
/*     */   
/*     */   private int openOptions;
/*     */ 
/*     */   
/*     */   private SpiOpenOptions spiOpenOptions;
/*     */ 
/*     */   
/*     */   private String objectName;
/*     */ 
/*     */   
/*     */   private int objectType;
/*     */ 
/*     */   
/*     */   private RemoteProxyQueue proxyQueue;
/*     */ 
/*     */   
/*     */   Long identifier;
/*     */ 
/*     */   
/*     */   Long oldIdentifier;
/*     */ 
/*     */   
/*     */   private MQOD mqodForReconnect;
/*     */ 
/*     */   
/*     */   private boolean callbackRegistered = false;
/*     */ 
/*     */   
/*     */   private boolean callbackSuspended = false;
/*     */   
/* 103 */   private MQCBD callbackDesc = null;
/*     */   
/* 105 */   private MQMD callbackMsgDesc = null;
/*     */   
/* 107 */   private MQGMO callbackGetMsgOpts = null;
/*     */   
/* 109 */   private RemoteHsub parentHsub = null;
/*     */   
/*     */   private boolean logicallyClosed = false;
/*     */   
/* 113 */   private String originalObjectName = null;
/*     */ 
/*     */   
/*     */   private volatile boolean reopenable = true;
/*     */   
/* 118 */   private byte[] AMSPolicy = null;
/*     */   
/* 120 */   private String AMSErrorQueue = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteHobj(JmqiEnvironment env, int hobjInteger, RemoteProxyQueue proxyQueue, String objectName, int objectType, int openOptions, SpiOpenOptions spiOpenOptions, int defaultPersistence, int defaultPutReponseType, int defaultReadAhead, MQOD objectDescriptor, RemoteSession session) {
/* 150 */     super(env);
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "<init>(JmqiEnvironment,int,RemoteProxyQueue,String,int,int,SpiOpenOptions,int,int,int,MQOD,RemoteSession)", new Object[] { env, 
/*     */             
/* 154 */             Integer.valueOf(hobjInteger), proxyQueue, objectName, 
/* 155 */             Integer.valueOf(objectType), Integer.valueOf(openOptions), spiOpenOptions, 
/* 156 */             Integer.valueOf(defaultPersistence), Integer.valueOf(defaultPutReponseType), 
/* 157 */             Integer.valueOf(defaultReadAhead), objectDescriptor, session });
/*     */     }
/* 159 */     this.hobj = hobjInteger;
/* 160 */     this.proxyQueue = proxyQueue;
/* 161 */     this.objectName = objectName;
/* 162 */     this.objectType = objectType;
/* 163 */     this.openOptions = openOptions;
/* 164 */     this.spiOpenOptions = spiOpenOptions;
/* 165 */     this.defaultPersistence = defaultPersistence;
/* 166 */     this.defaultPutResponseType = defaultPutReponseType;
/* 167 */     this.defaultReadAhead = defaultReadAhead;
/* 168 */     if (objectDescriptor != null) {
/* 169 */       this.mqodForReconnect = objectDescriptor.cloneForReconnect();
/*     */     }
/* 171 */     this.identifier = session.generateIdentifier(this.hobj);
/*     */     
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "<init>(JmqiEnvironment,int,RemoteProxyQueue,String,int,int,int,int,int,int,MQOD,RemoteSession)");
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
/*     */   public RemoteHobj(JmqiEnvironment env, int hobjInteger, RemoteProxyQueue proxyQueue, String objectName, int objectType, int openOptions, int defaultPersistence, int defaultPutReponseType, int defaultReadAhead, MQOD objectDescriptor, RemoteSession session) {
/* 206 */     this(env, hobjInteger, proxyQueue, objectName, objectType, openOptions, null, defaultPersistence, defaultPutReponseType, defaultReadAhead, objectDescriptor, session);
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
/*     */   public int getDefaultPersistence() {
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "getDefaultPersistence()", "getter", 
/* 229 */           Integer.valueOf(this.defaultPersistence));
/*     */     }
/* 231 */     return this.defaultPersistence;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDefaultPutResponseType() {
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "getDefaultPutResponseType()", "getter", 
/* 241 */           Integer.valueOf(this.defaultPutResponseType));
/*     */     }
/* 243 */     return this.defaultPutResponseType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDefaultReadAhead() {
/* 251 */     if (Trace.isOn) {
/* 252 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "getDefaultReadAhead()", "getter", 
/* 253 */           Integer.valueOf(this.defaultReadAhead));
/*     */     }
/* 255 */     return this.defaultReadAhead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getObjectName() {
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "getObjectName()", "getter", this.objectName);
/*     */     }
/*     */     
/* 267 */     return this.objectName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOpenOptions() {
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "getOpenOptions()", "getter", 
/* 277 */           Integer.valueOf(this.openOptions));
/*     */     }
/* 279 */     return this.openOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getObjectType() {
/* 287 */     if (Trace.isOn) {
/* 288 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "getObjectType()", "getter", 
/* 289 */           Integer.valueOf(this.objectType));
/*     */     }
/* 291 */     return this.objectType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOpenOptions(int openOptions) {
/* 298 */     if (Trace.isOn) {
/* 299 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "setOpenOptions(int)", "setter", 
/* 300 */           Integer.valueOf(openOptions));
/*     */     }
/* 302 */     this.openOptions = openOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIntegerHandle() {
/* 309 */     if (Trace.isOn) {
/* 310 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "getIntegerHandle()", "getter", 
/* 311 */           Integer.valueOf(this.hobj));
/*     */     }
/* 313 */     return this.hobj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Long getIdentifier() {
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "getIdentifier()", "getter", this.identifier);
/*     */     }
/*     */ 
/*     */     
/* 325 */     return this.identifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Long getOldIdentifier() {
/* 332 */     if (Trace.isOn) {
/* 333 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "getOldIdentifier()", "getter", this.oldIdentifier);
/*     */     }
/*     */ 
/*     */     
/* 337 */     return this.oldIdentifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteProxyQueue getProxyQueue() {
/* 345 */     if (Trace.isOn) {
/* 346 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "getProxyQueue()", "getter", this.proxyQueue);
/*     */     }
/*     */     
/* 349 */     return this.proxyQueue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProxyQueue(RemoteProxyQueue pq) {
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "setProxyQueue(RemoteProxyQueue)", "setter", pq);
/*     */     }
/*     */     
/* 362 */     this.proxyQueue = pq;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 371 */     StringBuffer retString = new StringBuffer();
/*     */     
/* 373 */     retString.append(String.format("RemoteHobj@0x%8x - ", new Object[] { Integer.valueOf(System.identityHashCode(this)) }));
/* 374 */     retString.append("0x");
/* 375 */     retString.append(Integer.toHexString(this.hobj));
/* 376 */     retString.append(" - ");
/* 377 */     retString.append(getClass().getName());
/* 378 */     retString.append("[objectType=");
/* 379 */     retString.append(this.objectType);
/* 380 */     retString.append(",objectName=");
/* 381 */     retString.append(this.objectName.trim());
/* 382 */     retString.append(",proxyQueue=");
/* 383 */     retString.append((this.proxyQueue != null) ? this.proxyQueue.toString() : "<null>");
/* 384 */     retString.append(String.format(",Open Options 0x%x(%s)", new Object[] { Integer.valueOf(this.openOptions), MQConstants.decodeOptionsForTrace(this.openOptions, "MQOO_.*") }));
/* 385 */     retString.append(String.format(",SPI Options %s", new Object[] { String.valueOf(this.spiOpenOptions) }));
/* 386 */     retString.append("]");
/*     */     
/* 388 */     String traceRet1 = retString.toString();
/*     */     
/* 390 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateHandle(int hobjHandle, RemoteSession session) {
/* 398 */     if (Trace.isOn)
/* 399 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "updateHandle(int, RemoteSession)", new Object[] {
/* 400 */             Integer.valueOf(hobjHandle), session
/*     */           }); 
/* 402 */     this.hobj = hobjHandle;
/*     */     
/* 404 */     this.oldIdentifier = this.identifier;
/* 405 */     this.identifier = session.generateIdentifier(this.hobj);
/*     */     
/* 407 */     if (Trace.isOn) {
/* 408 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "updateHandle(int, RemoteSession)");
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
/*     */   public void setupCallback(MQCBD callbackDesc, int operation, MQMD callbackMsgDesc, MQGMO callbackGetMsgOpts) {
/* 422 */     if (Trace.isOn) {
/* 423 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "setupCallback(MQCBD,int,MQMD,MQGMO)", new Object[] { callbackDesc, 
/*     */             
/* 425 */             Integer.valueOf(operation), callbackMsgDesc, callbackGetMsgOpts });
/*     */     }
/* 427 */     this.callbackDesc = callbackDesc;
/* 428 */     this.callbackMsgDesc = callbackMsgDesc;
/* 429 */     this.callbackGetMsgOpts = callbackGetMsgOpts;
/* 430 */     this.callbackRegistered = ((operation & 0x100) != 0);
/* 431 */     this.callbackSuspended = ((operation & 0x10000) != 0);
/*     */     
/* 433 */     if (Trace.isOn) {
/* 434 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "setupCallback(MQCBD,int,MQMD,MQGMO)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyCallbackTo(RemoteHobj newHobj) {
/* 445 */     if (Trace.isOn) {
/* 446 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "copyCallbackTo(RemoteHobj)", new Object[] { newHobj });
/*     */     }
/*     */     
/* 449 */     newHobj.callbackDesc = this.callbackDesc;
/* 450 */     newHobj.callbackMsgDesc = this.callbackMsgDesc;
/* 451 */     newHobj.callbackGetMsgOpts = this.callbackGetMsgOpts;
/* 452 */     newHobj.callbackRegistered = this.callbackRegistered;
/* 453 */     newHobj.callbackSuspended = this.callbackSuspended;
/*     */     
/* 455 */     if (Trace.isOn) {
/* 456 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "copyCallbackTo(RemoteHobj)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCBD getCallbackDescriptor() {
/* 466 */     if (Trace.isOn) {
/* 467 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "getCallbackDescriptor()", "getter", this.callbackDesc);
/*     */     }
/*     */     
/* 470 */     return this.callbackDesc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQMD getCallbackMessageDescriptor() {
/* 478 */     if (Trace.isOn) {
/* 479 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "getCallbackMessageDescriptor()", "getter", this.callbackMsgDesc);
/*     */     }
/*     */     
/* 482 */     return this.callbackMsgDesc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQGMO getCallbackGetMessageOptions() {
/* 490 */     if (Trace.isOn) {
/* 491 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "getCallbackGetMessageOptions()", "getter", this.callbackGetMsgOpts);
/*     */     }
/*     */     
/* 494 */     return this.callbackGetMsgOpts;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCallbackRegistered() {
/* 502 */     if (Trace.isOn) {
/* 503 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "isCallbackRegistered()", "getter", 
/* 504 */           Boolean.valueOf(this.callbackRegistered));
/*     */     }
/* 506 */     return this.callbackRegistered;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCallbackSuspended() {
/* 514 */     if (Trace.isOn) {
/* 515 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "isCallbackSuspended()", "getter", 
/* 516 */           Boolean.valueOf(this.callbackSuspended));
/*     */     }
/* 518 */     return this.callbackSuspended;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteHsub getParentHsub() {
/* 525 */     if (Trace.isOn) {
/* 526 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "getParentHsub()", "getter", this.parentHsub);
/*     */     }
/*     */     
/* 529 */     return this.parentHsub;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setParentHsub(RemoteHsub parentHsub) {
/* 536 */     if (Trace.isOn) {
/* 537 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "setParentHsub(RemoteHsub)", "setter", parentHsub);
/*     */     }
/*     */     
/* 540 */     this.parentHsub = parentHsub;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isLogicallyClosed() {
/* 547 */     if (Trace.isOn) {
/* 548 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "isLogicallyClosed()", "getter", 
/* 549 */           Boolean.valueOf(this.logicallyClosed));
/*     */     }
/* 551 */     return this.logicallyClosed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setLogicallyClosed(boolean logicallyClosed) {
/* 558 */     if (Trace.isOn) {
/* 559 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "setLogicallyClosed(boolean)", "setter", 
/* 560 */           Boolean.valueOf(logicallyClosed));
/*     */     }
/* 562 */     this.logicallyClosed = logicallyClosed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOriginalObjectName() {
/* 569 */     if (Trace.isOn) {
/* 570 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "getOriginalObjectName()", "getter", this.originalObjectName);
/*     */     }
/*     */     
/* 573 */     return this.originalObjectName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setOriginalObjectName(String originalObjectName) {
/* 580 */     if (Trace.isOn) {
/* 581 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "setOriginalObjectName(String)", "setter", originalObjectName);
/*     */     }
/*     */     
/* 584 */     this.originalObjectName = originalObjectName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getAMSPolicy() {
/* 592 */     if (Trace.isOn) {
/* 593 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "getAMSPolicy()", "getter", this.AMSPolicy);
/*     */     }
/*     */     
/* 596 */     return this.AMSPolicy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAMSPolicy(byte[] policy) {
/* 604 */     if (Trace.isOn) {
/* 605 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "setAMSPolicy(byte [ ])", "setter", policy);
/*     */     }
/*     */     
/* 608 */     this.AMSPolicy = policy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAMSErrorQueue() {
/* 616 */     if (Trace.isOn) {
/* 617 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "getAMSErrorQueue()", "getter", this.AMSErrorQueue);
/*     */     }
/*     */     
/* 620 */     return this.AMSErrorQueue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAMSErrorQueue(String errorQueue) {
/* 628 */     if (Trace.isOn) {
/* 629 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "setAMSErrorQueue(String)", "setter", errorQueue);
/*     */     }
/*     */     
/* 632 */     this.AMSErrorQueue = errorQueue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reOpen(RemoteHconn remoteHconn, RemoteFAP fap) {
/* 642 */     if (Trace.isOn) {
/* 643 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "reOpen(RemoteHconn, RemoteFAP)", new Object[] { remoteHconn, fap });
/*     */     }
/*     */ 
/*     */     
/* 647 */     if (!this.reopenable) {
/* 648 */       if (Trace.isOn) {
/* 649 */         Trace.data(this, "reOpen(RemoteHconn, RemoteFAP)", "not eligible for reconnection", null);
/* 650 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "reOpen(RemoteHconn, RemoteFAP)", 
/* 651 */             Boolean.valueOf(true), 0);
/*     */       } 
/* 653 */       return true;
/*     */     } 
/*     */     
/* 656 */     Pint cc = this.env.newPint();
/* 657 */     Pint rc = this.env.newPint();
/*     */     
/* 659 */     Phobj phobj = this.env.newPhobj();
/*     */     
/* 661 */     if (this.spiOpenOptions == null) {
/* 662 */       fap.MQOPEN(remoteHconn, this.mqodForReconnect, getOpenOptions(), phobj, cc, rc, this);
/*     */     } else {
/*     */       
/* 665 */       fap.spiOpen(remoteHconn, this.mqodForReconnect, this.spiOpenOptions, phobj, cc, rc, this);
/*     */     } 
/*     */     
/* 668 */     if (Trace.isOn) {
/* 669 */       Trace.data(this, "reOpen()", "Reconnecting hobj returned: ", new Object[] { cc, rc });
/*     */     }
/* 671 */     if ((rc.x == 2085 || rc.x == 2087 || rc.x == 2086) && 
/* 672 */       this.mqodForReconnect != null) {
/* 673 */       if (Trace.isOn) {
/* 674 */         Trace.data(this, "reOpen(RemoteHconn, RemoteFAP)", "Was this dynamic? ", new Object[] { getOriginalObjectName(), this.mqodForReconnect.getObjectName() });
/*     */       }
/*     */ 
/*     */       
/* 678 */       if (!getOriginalObjectName().equals(this.mqodForReconnect.getObjectName())) {
/* 679 */         if (Trace.isOn) {
/* 680 */           Trace.data(this, "reOpen(RemoteHconn, RemoteFAP)", "Yes - it was dynamic ", null);
/*     */         }
/*     */         
/* 683 */         String saveObjectName = this.mqodForReconnect.getObjectName();
/* 684 */         String saveDynamicQName = this.mqodForReconnect.getDynamicQName();
/*     */         
/* 686 */         this.mqodForReconnect.setObjectName(getOriginalObjectName());
/* 687 */         this.mqodForReconnect.setDynamicQName(saveObjectName);
/* 688 */         this.mqodForReconnect.setObjectQMgrName(null);
/*     */         
/* 690 */         fap.MQOPEN(remoteHconn, this.mqodForReconnect, getOpenOptions(), phobj, cc, rc, this);
/* 691 */         this.mqodForReconnect.setObjectName(saveObjectName);
/* 692 */         this.mqodForReconnect.setDynamicQName(saveDynamicQName);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 697 */     if (rc.x != 0) {
/* 698 */       if (!RemoteHconn.isReconnectableReasonCode(rc.x) && rc.x != 2085)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 703 */         remoteHconn.setReconnectionFailure(2, 2548, new JmqiException(this.env, -1, null, cc.x, rc.x, null));
/*     */       }
/* 705 */       if (Trace.isOn) {
/* 706 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "reOpen(RemoteHconn, RemoteFAP)", 
/* 707 */             Boolean.valueOf(false), 4);
/*     */       }
/* 709 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 714 */     if (isCallbackRegistered()) {
/* 715 */       int operation = 256;
/* 716 */       if (isCallbackSuspended()) {
/* 717 */         operation |= 0x10000;
/*     */       }
/* 719 */       fap.MQCB(remoteHconn, operation, getCallbackDescriptor(), this, getCallbackMessageDescriptor(), getCallbackGetMessageOptions(), cc, rc);
/*     */       
/* 721 */       if (rc.x != 0) {
/* 722 */         if (!RemoteHconn.isReconnectableReasonCode(rc.x)) {
/* 723 */           remoteHconn.setReconnectionFailure(2, 2548, new JmqiException(this.env, -1, null, cc.x, rc.x, null));
/*     */         }
/* 725 */         if (Trace.isOn) {
/* 726 */           Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "reOpen(RemoteHconn, RemoteFAP)", 
/* 727 */               Boolean.valueOf(false), 5);
/*     */         }
/* 729 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/* 733 */     if (Trace.isOn) {
/* 734 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHobj", "reOpen(RemoteHconn, RemoteFAP)", 
/* 735 */           Boolean.valueOf(true), 6);
/*     */     }
/*     */     
/* 738 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReopenable(boolean reopenable) {
/* 747 */     if (Trace.isOn) {
/* 748 */       Trace.data(this, " setReopenable(boolean)", "setter ", Boolean.valueOf(reopenable));
/*     */     }
/* 750 */     this.reopenable = reopenable;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\api\RemoteHobj.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */