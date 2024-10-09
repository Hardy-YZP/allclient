/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.constants.CMQC;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQCBC
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQCBC.java";
/*     */   private static final int SIZEOF_STRUC_ID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_CALL_TYPE = 4;
/*     */   private static final int SIZEOF_HOBJ = 4;
/*     */   private static final int SIZEOF_COMP_CODE = 4;
/*     */   private static final int SIZEOF_REASON = 4;
/*     */   private static final int SIZEOF_STATE = 4;
/*     */   private static final int SIZEOF_DATA_LENGTH = 4;
/*     */   private static final int SIZEOF_BUFFER_LENGTH = 4;
/*     */   private static final int SIZEOF_FLAGS = 4;
/*     */   private static final int SIZEOF_RECONNECT_DELAY = 4;
/*     */   
/*     */   static {
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.data("com.ibm.mq.jmqi.MQCBC", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQCBC.java");
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
/*  84 */   private int version = 1;
/*     */   private int callType;
/*  86 */   private Hobj hobj = CMQC.jmqi_MQHO_UNUSABLE_HOBJ;
/*     */   private Object callbackArea;
/*     */   private Object connectionArea;
/*  89 */   private int compCode = 0;
/*  90 */   private int reason = 0;
/*  91 */   private int state = 0;
/*     */   private int dataLength;
/*     */   private int bufferLength;
/*  94 */   private int flags = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private int reconnectDelay;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV1(int ptrSize) {
/* 104 */     int size = 0;
/* 105 */     size += 4;
/* 106 */     size += 4;
/* 107 */     size += 4;
/* 108 */     size += 4;
/* 109 */     size += ptrSize;
/* 110 */     size += ptrSize;
/* 111 */     size += 4;
/* 112 */     size += 4;
/* 113 */     size += 4;
/* 114 */     size += 4;
/* 115 */     size += 4;
/* 116 */     size += 4;
/*     */     
/* 118 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 128 */     int size = getFieldSizeV1(ptrSize);
/*     */     
/* 130 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*     */     
/* 132 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV2(int ptrSize) {
/* 142 */     int size = getFieldSizeV1(ptrSize);
/* 143 */     size += 4;
/*     */     
/* 145 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV2(int ptrSize) {
/* 155 */     int size = getFieldSizeV2(ptrSize);
/*     */     
/* 157 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*     */     
/* 159 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQCBC(JmqiEnvironment env) {
/* 168 */     super(env);
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCBC", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCBC", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 183 */     if (Trace.isOn) {
/* 184 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "getVersion()", "getter", Integer.valueOf(this.version));
/*     */     }
/*     */     
/* 187 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "setVersion(int)", "setter", 
/* 197 */           Integer.valueOf(version));
/*     */     }
/* 199 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCallType() {
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "getCallType()", "getter", 
/* 208 */           Integer.valueOf(this.callType));
/*     */     }
/* 210 */     return this.callType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCallType(int callType) {
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "setCallType(int)", "setter", 
/* 219 */           Integer.valueOf(callType));
/*     */     }
/* 221 */     this.callType = callType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hobj getHobj() {
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "getHobj()", "getter", this.hobj);
/*     */     }
/* 231 */     return this.hobj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHobj(Hobj hobj) {
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "setHobj(Hobj)", "setter", hobj);
/*     */     }
/* 241 */     this.hobj = hobj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getCallbackArea() {
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "getCallbackArea()", "getter", this.callbackArea);
/*     */     }
/* 251 */     return this.callbackArea;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCallbackArea(Object callbackArea) {
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "setCallbackArea(Object)", "setter", callbackArea);
/*     */     }
/*     */     
/* 262 */     this.callbackArea = callbackArea;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getConnectionArea() {
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "getConnectionArea()", "getter", this.connectionArea);
/*     */     }
/* 272 */     return this.connectionArea;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnectionArea(Object connectionArea) {
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "setConnectionArea(Object)", "setter", connectionArea);
/*     */     }
/*     */     
/* 283 */     this.connectionArea = connectionArea;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCompCode() {
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "getCompCode()", "getter", 
/* 292 */           Integer.valueOf(this.compCode));
/*     */     }
/* 294 */     return this.compCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCompCode(int compCode) {
/* 301 */     if (Trace.isOn) {
/* 302 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "setCompCode(int)", "setter", 
/* 303 */           Integer.valueOf(compCode));
/*     */     }
/* 305 */     this.compCode = compCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReason() {
/* 312 */     if (Trace.isOn) {
/* 313 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "getReason()", "getter", Integer.valueOf(this.reason));
/*     */     }
/* 315 */     return this.reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReason(int reason) {
/* 322 */     if (Trace.isOn) {
/* 323 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "setReason(int)", "setter", 
/* 324 */           Integer.valueOf(reason));
/*     */     }
/* 326 */     this.reason = reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getState() {
/* 333 */     if (Trace.isOn) {
/* 334 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "getState()", "getter", Integer.valueOf(this.state));
/*     */     }
/* 336 */     return this.state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setState(int state) {
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "setState(int)", "setter", Integer.valueOf(state));
/*     */     }
/*     */     
/* 347 */     this.state = state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDataLength() {
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "getDataLength()", "getter", 
/* 356 */           Integer.valueOf(this.dataLength));
/*     */     }
/* 358 */     return this.dataLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDataLength(int dataLength) {
/* 365 */     if (Trace.isOn) {
/* 366 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "setDataLength(int)", "setter", 
/* 367 */           Integer.valueOf(dataLength));
/*     */     }
/* 369 */     this.dataLength = dataLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBufferLength() {
/* 376 */     if (Trace.isOn) {
/* 377 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "getBufferLength()", "getter", 
/* 378 */           Integer.valueOf(this.bufferLength));
/*     */     }
/* 380 */     return this.bufferLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBufferLength(int bufferLength) {
/* 387 */     if (Trace.isOn) {
/* 388 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "setBufferLength(int)", "setter", 
/* 389 */           Integer.valueOf(bufferLength));
/*     */     }
/* 391 */     this.bufferLength = bufferLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlags() {
/* 398 */     if (Trace.isOn) {
/* 399 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "getFlags()", "getter", Integer.valueOf(this.flags));
/*     */     }
/* 401 */     return this.flags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int flags) {
/* 408 */     if (Trace.isOn) {
/* 409 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "setFlags(int)", "setter", Integer.valueOf(flags));
/*     */     }
/*     */     
/* 412 */     this.flags = flags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReconnectDelay() {
/* 419 */     if (Trace.isOn) {
/* 420 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "getReconnectDelay()", "getter", 
/* 421 */           Integer.valueOf(this.reconnectDelay));
/*     */     }
/* 423 */     return this.reconnectDelay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReconnectDelay(int reconnectDelay) {
/* 430 */     if (Trace.isOn) {
/* 431 */       Trace.data(this, "com.ibm.mq.jmqi.MQCBC", "setReconnectDelay(int)", "setter", 
/* 432 */           Integer.valueOf(reconnectDelay));
/*     */     }
/* 434 */     this.reconnectDelay = reconnectDelay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 443 */     int size = getSize(this.env, this.version, ptrSize);
/* 444 */     return size;
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
/*     */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/* 456 */     int size = 0;
/* 457 */     switch (version) {
/*     */       case 1:
/* 459 */         size = getSizeV1(ptrSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 472 */         return size;case 2: size = getSizeV2(ptrSize); return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 6107, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 481 */     if (Trace.isOn) {
/* 482 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCBC", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 484 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 486 */     int pos = offset;
/*     */     
/* 488 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 490 */     dc.writeMQField("CBC ", buffer, pos, 4, cp, tls);
/* 491 */     pos += 4;
/*     */     
/* 493 */     dc.writeI32(this.version, buffer, pos, swap);
/* 494 */     pos += 4;
/*     */     
/* 496 */     dc.writeI32(this.callType, buffer, pos, swap);
/* 497 */     pos += 4;
/*     */     
/* 499 */     dc.writeI32(this.hobj.getIntegerHandle(), buffer, pos, swap);
/* 500 */     pos += 4;
/*     */     
/* 502 */     dc.clear(buffer, pos, ptrSize);
/* 503 */     pos += ptrSize;
/*     */     
/* 505 */     dc.clear(buffer, pos, ptrSize);
/* 506 */     pos += ptrSize;
/*     */     
/* 508 */     dc.writeI32(this.compCode, buffer, pos, swap);
/* 509 */     pos += 4;
/*     */     
/* 511 */     dc.writeI32(this.reason, buffer, pos, swap);
/* 512 */     pos += 4;
/*     */     
/* 514 */     dc.writeI32(this.state, buffer, pos, swap);
/* 515 */     pos += 4;
/*     */     
/* 517 */     dc.writeI32(this.dataLength, buffer, pos, swap);
/* 518 */     pos += 4;
/*     */     
/* 520 */     dc.writeI32(this.bufferLength, buffer, pos, swap);
/* 521 */     pos += 4;
/*     */     
/* 523 */     dc.writeI32(this.flags, buffer, pos, swap);
/* 524 */     pos += 4;
/* 525 */     if (this.version == 1) {
/*     */       
/* 527 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 528 */       dc.clear(buffer, pos, padding);
/* 529 */       pos += padding;
/*     */     } 
/* 531 */     if (this.version >= 2) {
/*     */       
/* 533 */       dc.writeI32(this.reconnectDelay, buffer, pos, swap);
/* 534 */       pos += 4;
/* 535 */       if (this.version == 2) {
/*     */         
/* 537 */         int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 538 */         dc.clear(buffer, pos, padding);
/* 539 */         pos += padding;
/*     */       } 
/*     */     } 
/*     */     
/* 543 */     if (Trace.isOn) {
/* 544 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCBC", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 545 */           Integer.valueOf(pos));
/*     */     }
/* 547 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 556 */     if (Trace.isOn) {
/* 557 */       Trace.entry(this, "com.ibm.mq.jmqi.MQCBC", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 559 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/*     */     
/* 562 */     int pos = offset;
/* 563 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 565 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 566 */     if (!strucId.equals("CBC ")) {
/*     */       
/* 568 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 6107, null);
/*     */       
/* 570 */       if (Trace.isOn) {
/* 571 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQCBC", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*     */       }
/*     */       
/* 574 */       throw traceRet1;
/*     */     } 
/* 576 */     pos += 4;
/*     */ 
/*     */     
/* 579 */     this.version = dc.readI32(buffer, pos, swap);
/* 580 */     pos += 4;
/*     */ 
/*     */     
/* 583 */     this.callType = dc.readI32(buffer, pos, swap);
/* 584 */     pos += 4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 590 */     pos += 4;
/*     */ 
/*     */     
/* 593 */     pos += ptrSize;
/*     */ 
/*     */     
/* 596 */     pos += ptrSize;
/*     */ 
/*     */     
/* 599 */     this.compCode = dc.readI32(buffer, pos, swap);
/* 600 */     pos += 4;
/*     */ 
/*     */     
/* 603 */     this.reason = dc.readI32(buffer, pos, swap);
/* 604 */     pos += 4;
/*     */ 
/*     */     
/* 607 */     this.state = dc.readI32(buffer, pos, swap);
/* 608 */     pos += 4;
/*     */ 
/*     */     
/* 611 */     this.dataLength = dc.readI32(buffer, pos, swap);
/* 612 */     pos += 4;
/*     */ 
/*     */     
/* 615 */     this.bufferLength = dc.readI32(buffer, pos, swap);
/* 616 */     pos += 4;
/*     */ 
/*     */     
/* 619 */     this.flags = dc.readI32(buffer, pos, swap);
/* 620 */     pos += 4;
/*     */     
/* 622 */     if (this.version == 1) {
/*     */       
/* 624 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 625 */       pos += padding;
/*     */     } 
/*     */     
/* 628 */     if (this.version >= 2) {
/*     */       
/* 630 */       this.reconnectDelay = dc.readI32(buffer, pos, swap);
/* 631 */       pos += 4;
/*     */       
/* 633 */       if (this.version == 2) {
/*     */         
/* 635 */         int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 636 */         pos += padding;
/*     */       } 
/*     */     } 
/*     */     
/* 640 */     if (Trace.isOn) {
/* 641 */       Trace.exit(this, "com.ibm.mq.jmqi.MQCBC", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 642 */           Integer.valueOf(pos));
/*     */     }
/* 644 */     return pos;
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
/*     */   public void addFieldsToFormatter(JmqiStructureFormatter fmt) {
/* 656 */     fmt.add("version", this.version);
/* 657 */     fmt.add("callType", this.callType);
/* 658 */     fmt.add("hobj", this.hobj);
/* 659 */     fmt.add("compCode", this.compCode);
/* 660 */     fmt.add("reason", this.reason);
/* 661 */     fmt.add("state", this.state);
/* 662 */     fmt.add("dataLength", this.dataLength);
/* 663 */     fmt.add("bufferLength", this.bufferLength);
/* 664 */     fmt.add("flags", this.flags);
/* 665 */     fmt.add("reconnectDelay", this.reconnectDelay);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQCBC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */