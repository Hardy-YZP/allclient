/*     */ package com.ibm.mq.jmqi.remote.api;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQGMO;
/*     */ import com.ibm.mq.jmqi.MQSD;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
/*     */ import com.ibm.mq.jmqi.handles.Phobj;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.remote.impl.RemoteTls;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.mq.jmqi.system.LpiSD;
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
/*     */ public class RemoteHsub
/*     */   extends JmqiObject
/*     */   implements Hobj
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/api/RemoteHsub.java";
/*     */   
/*     */   static {
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.data("com.ibm.mq.jmqi.remote.api.RemoteHsub", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/api/RemoteHsub.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   private int hsub = -1;
/*     */ 
/*     */   
/*  65 */   private RemoteHobj remoteHobj = null;
/*     */ 
/*     */   
/*     */   private MQSD subDescriptor;
/*     */ 
/*     */   
/*     */   private LpiSD spiSD;
/*     */   
/*     */   private boolean spiCall;
/*     */   
/*  75 */   private long initialSubscriptionTime = System.currentTimeMillis();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean logicallyClosed = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteHsub(JmqiEnvironment env, int hsub, RemoteHobj hobj, MQSD subDescriptor, LpiSD spiSD, boolean spiCall, JmqiCodepage cp, RemoteTls tls) {
/*  90 */     super(env);
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "<init>(JmqiEnvironment,int,RemoteHobj,MQSD,LpiSD,boolean,JmqiCodepage,RemoteTls)", new Object[] { env, 
/*     */             
/*  94 */             Integer.valueOf(hsub), hobj, subDescriptor, spiSD, 
/*  95 */             Boolean.valueOf(spiCall), cp, tls });
/*     */     }
/*  97 */     this.hsub = hsub;
/*  98 */     this.remoteHobj = hobj;
/*  99 */     this.subDescriptor = env.newMQSD();
/* 100 */     this.subDescriptor.setVersion(subDescriptor.getVersion());
/* 101 */     if (subDescriptor.getAlternateUserId() != null) {
/* 102 */       this.subDescriptor.setAlternateUserId(subDescriptor.getAlternateUserId());
/*     */     }
/* 104 */     this.subDescriptor.setAlternateSecurityId(subDescriptor.getAlternateSecurityId());
/* 105 */     if (subDescriptor.getObjectName() != null) {
/* 106 */       this.subDescriptor.setObjectName(subDescriptor.getObjectName());
/*     */     }
/* 108 */     if (subDescriptor.getObjectString().getVsString() != null) {
/* 109 */       this.subDescriptor.getObjectString().setVsString(subDescriptor.getObjectString().getVsString());
/* 110 */       this.subDescriptor.getObjectString().setVsBufSize(10240);
/*     */     } 
/* 112 */     if (subDescriptor.getSubName().getVsString() != null) {
/* 113 */       this.subDescriptor.getSubName().setVsString(subDescriptor.getSubName().getVsString());
/* 114 */       this.subDescriptor.getSubName().setVsBufSize(10240);
/*     */     } 
/* 116 */     if (subDescriptor.getSelectionString().getVsString() != null) {
/* 117 */       this.subDescriptor.getSelectionString().setVsString(subDescriptor.getSelectionString().getVsString());
/* 118 */       this.subDescriptor.getSelectionString().setVsBufSize(subDescriptor.getSelectionString().getVsBufSize());
/*     */     } 
/* 120 */     this.subDescriptor.setOptions(subDescriptor.getOptions());
/* 121 */     this.subDescriptor.setSubExpiry(subDescriptor.getSubExpiry());
/* 122 */     this.subDescriptor.setSubLevel(subDescriptor.getSubLevel());
/* 123 */     if (spiSD != null) {
/*     */       
/* 125 */       JmqiSystemEnvironment sysEnv = (JmqiSystemEnvironment)env;
/* 126 */       this.spiSD = sysEnv.newSpiSD();
/* 127 */       JmqiTls jmqiTls = sysEnv.getJmqiTls((JmqiComponentTls)tls);
/*     */       try {
/* 129 */         byte[] buffer = new byte[spiSD.getRequiredBufferSize(4, cp)];
/* 130 */         spiSD.writeToBuffer(buffer, 0, 4, false, cp, jmqiTls);
/* 131 */         this.spiSD.readFromBuffer(buffer, 0, 4, false, cp, jmqiTls);
/*     */       }
/* 133 */       catch (JmqiException e) {
/* 134 */         if (Trace.isOn) {
/* 135 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "<init>(JmqiEnvironment,int,RemoteHobj,MQSD,LpiSD,boolean,JmqiCodepage,RemoteTls)", (Throwable)e);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 140 */     this.spiCall = spiCall;
/*     */     
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "<init>(JmqiEnvironment,int,RemoteHobj,MQSD,LpiSD,boolean,JmqiCodepage,RemoteTls)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteHobj getHobj() {
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "getHobj()", "getter", this.remoteHobj);
/*     */     }
/* 157 */     return this.remoteHobj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIntegerHandle() {
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "getIntegerHandle()", "getter", 
/* 168 */           Integer.valueOf(this.hsub));
/*     */     }
/* 170 */     return this.hsub;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHandle(int handle) {
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "setHandle(int)", "setter", 
/* 179 */           Integer.valueOf(handle));
/*     */     }
/* 181 */     this.hsub = handle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHobj(RemoteHobj hobj) {
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "setHobj(RemoteHobj)", "setter", hobj);
/*     */     }
/*     */     
/* 192 */     this.remoteHobj = hobj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyControl() {
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "getPropertyControl()", "getter", 
/* 201 */           Integer.valueOf(1));
/*     */     }
/* 203 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQSD getMqsd() {
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "getMqsd()", "getter", this.subDescriptor);
/*     */     }
/*     */     
/* 214 */     return this.subDescriptor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExpiryRemainder() {
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "getExpiryRemainder()");
/*     */     }
/*     */     
/* 225 */     long timeSubscribed = (System.currentTimeMillis() - this.initialSubscriptionTime) / 100L;
/* 226 */     if (this.subDescriptor.getSubExpiry() > timeSubscribed) {
/* 227 */       int traceRet1 = (int)(this.subDescriptor.getSubExpiry() - timeSubscribed);
/*     */       
/* 229 */       if (Trace.isOn) {
/* 230 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "getExpiryRemainder()", 
/* 231 */             Integer.valueOf(traceRet1), 1);
/*     */       }
/* 233 */       return traceRet1;
/*     */     } 
/*     */     
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "getExpiryRemainder()", 
/* 238 */           Integer.valueOf(1), 2);
/*     */     }
/* 240 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean spiCall() {
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "spiCall()");
/*     */     }
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "spiCall()", 
/* 252 */           Boolean.valueOf(this.spiCall));
/*     */     }
/* 254 */     return this.spiCall;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LpiSD getSpiSD() {
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "getSpiSD()", "getter", this.spiSD);
/*     */     }
/* 264 */     return this.spiSD;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isLogicallyClosed() {
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "isLogicallyClosed()", "getter", 
/* 273 */           Boolean.valueOf(this.logicallyClosed));
/*     */     }
/* 275 */     return this.logicallyClosed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setLogicallyClosed(boolean logicallyClosed) {
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "setLogicallyClosed(boolean)", "setter", 
/* 284 */           Boolean.valueOf(logicallyClosed));
/*     */     }
/* 286 */     this.logicallyClosed = logicallyClosed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getAMSPolicy() {
/* 295 */     if (Trace.isOn) {
/* 296 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "getAMSPolicy()", "getter", null);
/*     */     }
/* 298 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAMSErrorQueue() {
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.data(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "getAMSErrorQueue()", "getter", null);
/*     */     }
/*     */     
/* 311 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reOpen(RemoteHconn remoteHconn, RemoteFAP fap) {
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "reOpen(RemoteHconn, RemoteFAP)", new Object[] { remoteHconn, fap });
/*     */     }
/*     */     
/* 324 */     Pint cc = this.env.newPint();
/* 325 */     Pint rc = this.env.newPint();
/*     */ 
/*     */ 
/*     */     
/* 329 */     MQSD subDesc = getMqsd();
/* 330 */     boolean managedQueue = ((subDesc.getOptions() & 0x20) != 0);
/* 331 */     int suboptions = subDesc.getOptions();
/*     */ 
/*     */     
/* 334 */     if ((suboptions & 0x20) == 0) {
/* 335 */       suboptions |= 0x400000;
/*     */     }
/*     */ 
/*     */     
/* 339 */     if ((suboptions & 0x8) != 0) {
/* 340 */       if ((suboptions & 0x2) != 0) {
/* 341 */         suboptions |= 0x4;
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 346 */     else if ((suboptions & 0x1) != 0) {
/* 347 */       suboptions |= 0x2;
/*     */     } 
/*     */ 
/*     */     
/* 351 */     subDesc.setOptions(suboptions);
/*     */     
/* 353 */     Phobj phsub = this.env.newPhobj();
/* 354 */     Phobj phobj = this.env.newPhobj();
/*     */ 
/*     */     
/* 357 */     if (subDesc.getSubExpiry() != -1) {
/* 358 */       subDesc.setSubExpiry(getExpiryRemainder());
/*     */     }
/*     */     
/* 361 */     if (managedQueue) {
/* 362 */       this.spiSD.getSubProps().setDestinationQName(null);
/*     */     }
/*     */     
/* 365 */     fap.jmqiSubscribe(remoteHconn, subDesc, phobj, phsub, cc, rc, this.spiSD, spiCall(), this);
/* 366 */     if (rc.x != 0) {
/* 367 */       if (!RemoteHconn.isReconnectableReasonCode(rc.x) && rc.x != 2429) {
/* 368 */         remoteHconn.setReconnectionFailure(2, 2548, new JmqiException(this.env, -1, null, cc.x, rc.x, null));
/*     */       }
/* 370 */       if (Trace.isOn) {
/* 371 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHsub", "reOpen(RemoteHconn, RemoteFAP)", Boolean.valueOf(false), 6);
/*     */       }
/* 373 */       return false;
/*     */     } 
/*     */     
/* 376 */     RemoteHobj managedHobj = getHobj();
/* 377 */     if (managedHobj != null)
/*     */     {
/* 379 */       if (managedHobj.getProxyQueue() != null)
/*     */       {
/*     */         
/* 382 */         if (managedHobj.isCallbackRegistered()) {
/* 383 */           int operation = 256;
/* 384 */           if (managedHobj.isCallbackSuspended()) {
/* 385 */             operation |= 0x10000;
/*     */           }
/* 387 */           MQGMO callbackGetMessageOptions = managedHobj.getCallbackGetMessageOptions();
/* 388 */           callbackGetMessageOptions.setMsgToken(null);
/* 389 */           fap.MQCB(remoteHconn, operation, managedHobj.getCallbackDescriptor(), managedHobj, managedHobj.getCallbackMessageDescriptor(), callbackGetMessageOptions, cc, rc);
/* 390 */           if (rc.x != 0) {
/* 391 */             if (!RemoteHconn.isReconnectableReasonCode(rc.x)) {
/* 392 */               remoteHconn.setReconnectionFailure(2, 2548, new JmqiException(this.env, -1, null, cc.x, rc.x, null));
/*     */             }
/* 394 */             if (Trace.isOn) {
/* 395 */               Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectSelf()", 
/* 396 */                   Boolean.valueOf(false), 7);
/*     */             }
/* 398 */             return false;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     }
/* 403 */     if (Trace.isOn) {
/* 404 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.api.RemoteHconn", "reconnectSelf()", 
/* 405 */           Boolean.valueOf(true), 8);
/*     */     }
/* 407 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\api\RemoteHsub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */