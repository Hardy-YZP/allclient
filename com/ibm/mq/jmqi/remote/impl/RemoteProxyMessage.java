/*     */ package com.ibm.mq.jmqi.remote.impl;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQMD;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
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
/*     */ public class RemoteProxyMessage
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteProxyMessage.java";
/*     */   private int type;
/*     */   private int compCode;
/*     */   private int reason;
/*     */   private int selectionIndex;
/*     */   private MQMD msgDesc;
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteProxyMessage.java");
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
/*  69 */   private final byte[] msgToken = new byte[16];
/*     */ 
/*     */ 
/*     */   
/*     */   private int msgDescByteSize;
/*     */ 
/*     */ 
/*     */   
/*     */   private short status;
/*     */ 
/*     */ 
/*     */   
/*     */   private String resolvedQName;
/*     */ 
/*     */ 
/*     */   
/*     */   private int msgLength;
/*     */ 
/*     */ 
/*     */   
/*     */   private int actualMsgLength;
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] msgData;
/*     */ 
/*     */ 
/*     */   
/*     */   private RemoteProxyMessage older;
/*     */ 
/*     */ 
/*     */   
/*     */   private RemoteProxyMessage newer;
/*     */ 
/*     */   
/*     */   private long addedTime;
/*     */ 
/*     */   
/*     */   private int messagePropertiesLength;
/*     */ 
/*     */ 
/*     */   
/*     */   RemoteProxyMessage(JmqiEnvironment env, int msgLength) {
/* 112 */     super(env);
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "<init>(JmqiEnvironment,int)", new Object[] { env, 
/* 115 */             Integer.valueOf(msgLength) });
/*     */     }
/*     */     
/* 118 */     this.msgLength = msgLength;
/* 119 */     this.msgData = new byte[msgLength];
/*     */     
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "<init>(JmqiEnvironment,int)");
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
/*     */   int getType() {
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "getType()", "getter", 
/* 137 */           Integer.valueOf(this.type));
/*     */     }
/* 139 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setType(int type) {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setType(int)", "setter", 
/* 150 */           Integer.valueOf(type));
/*     */     }
/*     */     
/* 153 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setTransactional() {
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setTransactional()");
/*     */     }
/* 163 */     this.type |= 0x2;
/*     */     
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setTransactional()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isTransactional() {
/* 175 */     boolean traceRet1 = ((this.type & 0x2) == 2);
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "isTransactional()", "getter", 
/* 178 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 180 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isPersistent() {
/* 187 */     boolean traceRet1 = (this.msgDesc != null && this.msgDesc.getPersistence() == 1);
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "isPersistent()", "getter", 
/* 190 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 192 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   byte[] getMsgData() {
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "getMsgData()", "getter", this.msgData);
/*     */     }
/*     */     
/* 206 */     return this.msgData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setMsgData(byte[] msgData) {
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setMsgData(byte [ ])", "setter", msgData);
/*     */     }
/*     */ 
/*     */     
/* 220 */     this.msgData = msgData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MQMD getMsgDesc() {
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "getMsgDesc()", "getter", this.msgDesc);
/*     */     }
/*     */     
/* 234 */     return this.msgDesc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setMsgDesc(MQMD msgDesc) {
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setMsgDesc(MQMD)", "setter", msgDesc);
/*     */     }
/*     */ 
/*     */     
/* 248 */     this.msgDesc = msgDesc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   byte[] getMsgToken() {
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "getMsgToken()", "getter", this.msgToken);
/*     */     }
/*     */     
/* 262 */     return this.msgToken;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setMsgToken(byte[] msgToken) {
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setMsgToken(byte [ ])", "setter", msgToken);
/*     */     }
/*     */ 
/*     */     
/* 276 */     System.arraycopy(msgToken, 0, this.msgToken, 0, 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getMsgLength() {
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "getMsgLength()", "getter", 
/* 288 */           Integer.valueOf(this.msgLength));
/*     */     }
/* 290 */     return this.msgLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setMsgLength(int msgLength) {
/* 299 */     if (Trace.isOn) {
/* 300 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setMsgLength(int)", "setter", 
/* 301 */           Integer.valueOf(msgLength));
/*     */     }
/*     */     
/* 304 */     this.msgLength = msgLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getActualMsgLength() {
/* 314 */     if (Trace.isOn) {
/* 315 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "getActualMsgLength()", "getter", 
/* 316 */           Integer.valueOf(this.actualMsgLength));
/*     */     }
/* 318 */     return this.actualMsgLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setActualMsgLength(int actualMsgLength) {
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setActualMsgLength(int)", "setter", 
/* 329 */           Integer.valueOf(actualMsgLength));
/*     */     }
/*     */     
/* 332 */     this.actualMsgLength = actualMsgLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getCompCode() {
/* 342 */     if (Trace.isOn) {
/* 343 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "getCompCode()", "getter", 
/* 344 */           Integer.valueOf(this.compCode));
/*     */     }
/* 346 */     return this.compCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setCompCode(int compCode) {
/* 355 */     if (Trace.isOn) {
/* 356 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setCompCode(int)", "setter", 
/* 357 */           Integer.valueOf(compCode));
/*     */     }
/*     */     
/* 360 */     this.compCode = compCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getReason() {
/* 370 */     if (Trace.isOn) {
/* 371 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "getReason()", "getter", 
/* 372 */           Integer.valueOf(this.reason));
/*     */     }
/* 374 */     return this.reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setReason(int reason) {
/* 383 */     if (Trace.isOn) {
/* 384 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setReason(int)", "setter", 
/* 385 */           Integer.valueOf(reason));
/*     */     }
/*     */     
/* 388 */     this.reason = reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getSelectionIndex() {
/* 398 */     if (Trace.isOn) {
/* 399 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "getSelectionIndex()", "getter", 
/* 400 */           Integer.valueOf(this.selectionIndex));
/*     */     }
/* 402 */     return this.selectionIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setSelectionIndex(int selectionIndex) {
/* 411 */     if (Trace.isOn) {
/* 412 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setSelectionIndex(int)", "setter", 
/* 413 */           Integer.valueOf(selectionIndex));
/*     */     }
/*     */     
/* 416 */     this.selectionIndex = selectionIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getMsgDescByteSize() {
/* 426 */     if (Trace.isOn) {
/* 427 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "getMsgDescByteSize()", "getter", 
/* 428 */           Integer.valueOf(this.msgDescByteSize));
/*     */     }
/* 430 */     return this.msgDescByteSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setMsgDescByteSize(int msgDescByteSize) {
/* 439 */     if (Trace.isOn) {
/* 440 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setMsgDescByteSize(int)", "setter", 
/* 441 */           Integer.valueOf(msgDescByteSize));
/*     */     }
/*     */     
/* 444 */     this.msgDescByteSize = msgDescByteSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RemoteProxyMessage getNewer() {
/* 454 */     if (Trace.isOn) {
/* 455 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "getNewer()", "getter", this.newer);
/*     */     }
/*     */     
/* 458 */     return this.newer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setNewer(RemoteProxyMessage newer) {
/* 467 */     if (Trace.isOn) {
/* 468 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setNewer(RemoteProxyMessage)", "setter", newer);
/*     */     }
/*     */ 
/*     */     
/* 472 */     this.newer = newer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RemoteProxyMessage getOlder() {
/* 482 */     if (Trace.isOn) {
/* 483 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "getOlder()", "getter", this.older);
/*     */     }
/*     */     
/* 486 */     return this.older;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setOlder(RemoteProxyMessage older) {
/* 495 */     if (Trace.isOn) {
/* 496 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setOlder(RemoteProxyMessage)", "setter", older);
/*     */     }
/*     */ 
/*     */     
/* 500 */     this.older = older;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long getAddedTime() {
/* 509 */     if (Trace.isOn) {
/* 510 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "getAddedTime()", "getter", 
/* 511 */           Long.valueOf(this.addedTime));
/*     */     }
/* 513 */     return this.addedTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setAddedTime(long addedTime) {
/* 522 */     if (Trace.isOn) {
/* 523 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setAddedTime(long)", "setter", 
/* 524 */           Long.valueOf(addedTime));
/*     */     }
/* 526 */     this.addedTime = addedTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   short getStatus() {
/* 535 */     if (Trace.isOn) {
/* 536 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "getStatus()", "getter", 
/* 537 */           Short.valueOf(this.status));
/*     */     }
/* 539 */     return this.status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setStatus(short status) {
/* 548 */     if (Trace.isOn) {
/* 549 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setStatus(short)", "setter", 
/* 550 */           Short.valueOf(status));
/*     */     }
/* 552 */     this.status = status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setResolvedQName(String resolvedQName) {
/* 561 */     if (Trace.isOn) {
/* 562 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setResolvedQName(String)", "setter", resolvedQName);
/*     */     }
/*     */     
/* 565 */     this.resolvedQName = resolvedQName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getResolvedQName() {
/* 574 */     if (Trace.isOn) {
/* 575 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "getResolvedQName()", "getter", this.resolvedQName);
/*     */     }
/*     */     
/* 578 */     return this.resolvedQName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getMessagePropertiesLength() {
/* 585 */     if (Trace.isOn) {
/* 586 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "getMessagePropertiesLength()", "getter", 
/* 587 */           Integer.valueOf(this.messagePropertiesLength));
/*     */     }
/* 589 */     return this.messagePropertiesLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setMessagePropertiesLength(int messagePropertiesLength) {
/* 596 */     if (Trace.isOn) {
/* 597 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteProxyMessage", "setMessagePropertiesLength(int)", "setter", 
/* 598 */           Integer.valueOf(messagePropertiesLength));
/*     */     }
/* 600 */     this.messagePropertiesLength = messagePropertiesLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 608 */     if (this.msgDesc == null) {
/* 609 */       return super.toString();
/*     */     }
/* 611 */     String result = String.format("%s - msgId %s", new Object[] { super.toString(), JmqiTools.arrayToHexString(this.msgDesc.getMsgId()) });
/* 612 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\impl\RemoteProxyMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */