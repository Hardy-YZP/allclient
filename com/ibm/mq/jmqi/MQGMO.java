/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.constants.MQConstants;
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQGMO
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQGMO.java";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_WAIT_INTERVAL = 4;
/*     */   private static final int SIZEOF_SIGNAL_1 = 4;
/*     */   private static final int SIZEOF_SIGNAL_2 = 4;
/*     */   private static final int SIZEOF_RESOLVED_Q_NAME = 48;
/*     */   private static final int SIZEOF_MATCH_OPTIONS = 4;
/*     */   private static final int SIZEOF_GROUP_STATUS = 1;
/*     */   private static final int SIZEOF_SEGMENT_STATUS = 1;
/*     */   private static final int SIZEOF_SEGMENTATION = 1;
/*     */   private static final int SIZEOF_RESERVED_1 = 1;
/*     */   private static final int SIZEOF_MSG_TOKEN = 16;
/*     */   private static final int SIZEOF_RETURNED_LENGTH = 4;
/*     */   private static final int SIZEOF_RESERVED_2 = 4;
/*     */   private static final int SIZEOF_MSGHANDLE = 8;
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.mq.jmqi.MQGMO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQGMO.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   private int version = 1;
/* 104 */   private int options = 0;
/* 105 */   private int waitInterval = 0;
/* 106 */   private String resolvedQName = null;
/* 107 */   private int matchOptions = 3;
/* 108 */   private int groupStatus = 32;
/* 109 */   private int segmentStatus = 32;
/* 110 */   private int segmentation = 32;
/* 111 */   private byte[] msgToken = new byte[16];
/* 112 */   private int returnedLength = -1;
/* 113 */   private long messageHandle = 0L;
/* 114 */   private int signal2 = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int sizeofNativePointer) {
/* 124 */     int sizeOfStructureV1 = 72;
/* 125 */     return sizeOfStructureV1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV2(int sizeofNativePointer) {
/* 136 */     int sizeOfStructureV2 = getSizeV1(sizeofNativePointer) + 4 + 1 + 1 + 1 + 1;
/* 137 */     return sizeOfStructureV2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV3(int sizeofNativePointer) {
/* 148 */     int sizeOfStructureV3 = getSizeV2(sizeofNativePointer) + 16 + 4;
/* 149 */     return sizeOfStructureV3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV4(int sizeofNativePointer) {
/* 160 */     int size = getSizeV3(sizeofNativePointer) + 4 + 8;
/* 161 */     return size;
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
/*     */   public static int getSize(JmqiEnvironment env, int version, int sizeofNativePointer) throws JmqiException {
/*     */     int size;
/* 176 */     if (version == 1) {
/* 177 */       size = getSizeV1(sizeofNativePointer);
/*     */     }
/* 179 */     else if (version == 2) {
/* 180 */       size = getSizeV2(sizeofNativePointer);
/*     */     }
/* 182 */     else if (version == 3) {
/* 183 */       size = getSizeV3(sizeofNativePointer);
/*     */     }
/* 185 */     else if (version == 4) {
/* 186 */       size = getSizeV4(sizeofNativePointer);
/*     */     }
/*     */     else {
/*     */       
/* 190 */       JmqiException e = new JmqiException(env, -1, null, 2, 2186, null);
/* 191 */       throw e;
/*     */     } 
/* 193 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQGMO(JmqiEnvironment env) {
/* 202 */     super(env);
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.entry(this, "com.ibm.mq.jmqi.MQGMO", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 206 */     Arrays.fill(this.msgToken, (byte)0);
/*     */     
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.exit(this, "com.ibm.mq.jmqi.MQGMO", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGroupStatus() {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "getGroupStatus()", "getter", 
/* 221 */           Integer.valueOf(this.groupStatus));
/*     */     }
/* 223 */     return this.groupStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGroupStatus(int groupStatus) {
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "setGroupStatus(int)", "setter", 
/* 233 */           Integer.valueOf(groupStatus));
/*     */     }
/* 235 */     this.groupStatus = groupStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMatchOptions() {
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "getMatchOptions()", "getter", 
/* 245 */           Integer.valueOf(this.matchOptions));
/*     */     }
/* 247 */     return this.matchOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMatchOptions(int matchOptions) {
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "setMatchOptions(int)", "setter", 
/* 257 */           Integer.valueOf(matchOptions));
/*     */     }
/* 259 */     this.matchOptions = matchOptions;
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
/*     */   public byte[] getMsgToken() {
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.data(this, "getMsgToken()", this.msgToken);
/*     */     }
/* 274 */     return this.msgToken;
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
/*     */   public void setMsgToken(byte[] msgToken) {
/* 287 */     if (Trace.isOn) {
/* 288 */       Trace.data(this, "setMsgToken(byte [ ])", msgToken);
/*     */     }
/* 290 */     JmqiTools.byteArrayCopy(msgToken, 0, this.msgToken, 0, 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 297 */     if (Trace.isOn) {
/* 298 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "getOptions()", "getter", Integer.valueOf(this.options));
/*     */     }
/* 300 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "setOptions(int)", "setter", 
/* 310 */           Integer.valueOf(options));
/*     */     }
/* 312 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getResolvedQName() {
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "getResolvedQName()", "getter", this.resolvedQName);
/*     */     }
/* 323 */     return this.resolvedQName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResolvedQName(String resolvedQName) {
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "setResolvedQName(String)", "setter", resolvedQName);
/*     */     }
/*     */     
/* 335 */     this.resolvedQName = resolvedQName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReturnedLength() {
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "getReturnedLength()", "getter", 
/* 345 */           Integer.valueOf(this.returnedLength));
/*     */     }
/* 347 */     return this.returnedLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReturnedLength(int returnedLength) {
/* 355 */     if (Trace.isOn)
/* 356 */       Trace.entry(this, "com.ibm.mq.jmqi.MQGMO", "setReturnedLength(int)", new Object[] {
/* 357 */             Integer.valueOf(returnedLength)
/*     */           }); 
/* 359 */     this.returnedLength = returnedLength;
/*     */     
/* 361 */     if (Trace.isOn) {
/* 362 */       Trace.exit(this, "com.ibm.mq.jmqi.MQGMO", "setReturnedLength(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSegmentation() {
/* 372 */     if (Trace.isOn) {
/* 373 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "getSegmentation()", "getter", 
/* 374 */           Integer.valueOf(this.segmentation));
/*     */     }
/* 376 */     return this.segmentation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSegmentation(int segmentation) {
/* 384 */     if (Trace.isOn) {
/* 385 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "setSegmentation(int)", "setter", 
/* 386 */           Integer.valueOf(segmentation));
/*     */     }
/* 388 */     this.segmentation = segmentation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSegmentStatus() {
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "getSegmentStatus()", "getter", 
/* 398 */           Integer.valueOf(this.segmentStatus));
/*     */     }
/* 400 */     return this.segmentStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSegmentStatus(int segmentStatus) {
/* 408 */     if (Trace.isOn) {
/* 409 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "setSegmentStatus(int)", "setter", 
/* 410 */           Integer.valueOf(segmentStatus));
/*     */     }
/* 412 */     this.segmentStatus = segmentStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 421 */     if (Trace.isOn) {
/* 422 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "getVersion()", "getter", Integer.valueOf(this.version));
/*     */     }
/* 424 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 433 */     if (Trace.isOn) {
/* 434 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "setVersion(int)", "setter", 
/* 435 */           Integer.valueOf(version));
/*     */     }
/* 437 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWaitInterval() {
/* 445 */     if (Trace.isOn) {
/* 446 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "getWaitInterval()", "getter", 
/* 447 */           Integer.valueOf(this.waitInterval));
/*     */     }
/* 449 */     return this.waitInterval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWaitInterval(int waitInterval) {
/* 457 */     if (Trace.isOn) {
/* 458 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "setWaitInterval(int)", "setter", 
/* 459 */           Integer.valueOf(waitInterval));
/*     */     }
/* 461 */     this.waitInterval = waitInterval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSignal2() {
/* 469 */     if (Trace.isOn) {
/* 470 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "getSignal2()", "getter", Integer.valueOf(this.signal2));
/*     */     }
/* 472 */     return this.signal2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSignal2(int signal2) {
/* 479 */     if (Trace.isOn) {
/* 480 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "setSignal2(int)", "setter", 
/* 481 */           Integer.valueOf(signal2));
/*     */     }
/* 483 */     this.signal2 = signal2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMessageHandle() {
/* 490 */     if (Trace.isOn) {
/* 491 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "getMessageHandle()", "getter", 
/* 492 */           Long.valueOf(this.messageHandle));
/*     */     }
/* 494 */     return this.messageHandle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMessageHandle(long messageHandle) {
/* 501 */     if (Trace.isOn) {
/* 502 */       Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "setMessageHandle(long)", "setter", 
/* 503 */           Long.valueOf(messageHandle));
/*     */     }
/* 505 */     this.messageHandle = messageHandle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 514 */     int size = getSize(this.env, this.version, ptrSize);
/* 515 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 523 */     if (Trace.isOn) {
/* 524 */       Trace.entry(this, "com.ibm.mq.jmqi.MQGMO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 526 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 528 */     int pos = offset;
/* 529 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 531 */     dc.writeMQField("GMO ", buffer, pos, 4, cp, tls);
/* 532 */     pos += 4;
/*     */     
/* 534 */     dc.writeI32(this.version, buffer, pos, swap);
/* 535 */     pos += 4;
/*     */     
/* 537 */     dc.writeI32(this.options, buffer, pos, swap);
/* 538 */     pos += 4;
/*     */     
/* 540 */     dc.writeI32(this.waitInterval, buffer, pos, swap);
/* 541 */     pos += 4;
/*     */     
/* 543 */     pos += 4;
/*     */     
/* 545 */     dc.writeI32(this.signal2, buffer, pos, swap);
/* 546 */     pos += 4;
/*     */     
/* 548 */     dc.clear(buffer, pos, 48);
/* 549 */     pos += 48;
/*     */     
/* 551 */     if (this.version >= 2) {
/*     */       
/* 553 */       dc.writeI32(this.matchOptions, buffer, pos, swap);
/* 554 */       pos += 4;
/*     */ 
/*     */       
/* 557 */       dc.clear(buffer, pos, 4);
/* 558 */       pos += 4;
/*     */     } 
/*     */     
/* 561 */     if (this.version >= 3) {
/*     */       
/* 563 */       System.arraycopy(this.msgToken, 0, buffer, pos, 16);
/* 564 */       pos += 16;
/*     */       
/* 566 */       dc.clear(buffer, pos, 4);
/* 567 */       pos += 4;
/*     */     } 
/*     */     
/* 570 */     if (this.version >= 4) {
/*     */       
/* 572 */       dc.clear(buffer, pos, 4);
/* 573 */       pos += 4;
/*     */       
/* 575 */       dc.writeI64(this.messageHandle, buffer, pos, swap);
/* 576 */       pos += 8;
/*     */     } 
/*     */     
/* 579 */     if (Trace.isOn) {
/* 580 */       Trace.exit(this, "com.ibm.mq.jmqi.MQGMO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 581 */           Integer.valueOf(pos));
/*     */     }
/* 583 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int pos, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 592 */     return readFromBuffer(buffer, pos, false, ptrSize, swap, cp, tls);
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
/*     */   public int readFromBuffer(byte[] buffer, int offset, boolean isGet, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 608 */     if (Trace.isOn) {
/* 609 */       Trace.entry(this, "com.ibm.mq.jmqi.MQGMO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 611 */             Integer.valueOf(offset), Boolean.valueOf(isGet), Integer.valueOf(ptrSize), 
/* 612 */             Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 614 */     int pos = offset;
/* 615 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 617 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 618 */     if (!strucId.equals("GMO ")) {
/*     */       
/* 620 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2186, null);
/* 621 */       if (Trace.isOn) {
/* 622 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQGMO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", traceRet1, 1);
/*     */       }
/*     */       
/* 625 */       throw traceRet1;
/*     */     } 
/* 627 */     pos += 4;
/*     */ 
/*     */     
/* 630 */     this.version = dc.readI32(buffer, pos, swap);
/* 631 */     pos += 4;
/*     */     
/* 633 */     if (isGet) {
/*     */       
/* 635 */       pos += 8;
/*     */ 
/*     */       
/* 638 */       pos += 4;
/*     */ 
/*     */       
/* 641 */       this.signal2 = dc.readI32(buffer, pos, swap);
/* 642 */       pos += 4;
/*     */     }
/*     */     else {
/*     */       
/* 646 */       this.options = dc.readI32(buffer, pos, swap);
/* 647 */       pos += 4;
/*     */ 
/*     */       
/* 650 */       this.waitInterval = dc.readI32(buffer, pos, swap);
/* 651 */       pos += 4;
/*     */ 
/*     */       
/* 654 */       pos += 4;
/*     */ 
/*     */       
/* 657 */       this.signal2 = dc.readI32(buffer, pos, swap);
/* 658 */       pos += 4;
/*     */     } 
/*     */ 
/*     */     
/* 662 */     this.resolvedQName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 663 */     pos += 48;
/*     */ 
/*     */     
/* 666 */     if (this.version >= 2) {
/*     */       JmqiCodepage cp2;
/*     */       
/* 669 */       if (!isGet) {
/* 670 */         this.matchOptions = dc.readI32(buffer, pos, swap);
/*     */       }
/* 672 */       pos += 4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 681 */         cp2 = JmqiCodepage.getJmqiCodepage(this.env, 1208);
/*     */         
/* 683 */         if (cp2 == null) {
/* 684 */           UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException("1208");
/* 685 */           if (Trace.isOn) {
/* 686 */             Trace.throwing(this, "com.ibm.mq.jmqi.MQGMO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", traceRet2, 2);
/*     */           }
/*     */           
/* 689 */           throw traceRet2;
/*     */         }
/*     */       
/*     */       }
/* 693 */       catch (UnsupportedEncodingException e) {
/* 694 */         if (Trace.isOn) {
/* 695 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.MQGMO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", e, 1);
/*     */         }
/*     */         
/* 698 */         JmqiException traceRet1 = new JmqiException(this.env, 6047, new String[] { Integer.toString(1208), null, null, null, "???" }, 2, 2195, e);
/*     */         
/* 700 */         if (Trace.isOn) {
/* 701 */           Trace.throwing(this, "com.ibm.mq.jmqi.MQGMO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", traceRet1, 3);
/*     */         }
/*     */ 
/*     */         
/* 705 */         throw traceRet1;
/*     */       } 
/*     */       try {
/* 708 */         byte[] convertedGroupStatus = new byte[1];
/* 709 */         dc.convertBytes(buffer, pos, cp, convertedGroupStatus, 0, cp2, 1, tls);
/* 710 */         this.groupStatus = convertedGroupStatus[0] & 0xFF;
/*     */       }
/* 712 */       catch (JmqiException e1) {
/* 713 */         if (Trace.isOn) {
/* 714 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.MQGMO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", e1, 2);
/*     */         }
/*     */         
/* 717 */         if (Trace.isOn) {
/* 718 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.MQGMO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", e1, 1);
/*     */ 
/*     */ 
/*     */           
/* 722 */           int unexpectedValue = buffer[pos] & 0xFF;
/* 723 */           Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", "GroupStatus character " + unexpectedValue + " cannot be converted into 1208. Using default value", null);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 729 */         this.groupStatus = 32;
/*     */       } 
/* 731 */       pos++;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 739 */         byte[] convertedSegmentStatus = new byte[1];
/* 740 */         dc.convertBytes(buffer, pos, cp, convertedSegmentStatus, 0, cp2, 1, tls);
/* 741 */         this.segmentStatus = convertedSegmentStatus[0] & 0xFF;
/*     */       }
/* 743 */       catch (JmqiException e2) {
/* 744 */         if (Trace.isOn) {
/* 745 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.MQGMO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", e2, 3);
/*     */         }
/*     */         
/* 748 */         if (Trace.isOn) {
/* 749 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.MQGMO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", e2, 2);
/*     */ 
/*     */ 
/*     */           
/* 753 */           int unexpectedValue = buffer[pos] & 0xFF;
/* 754 */           Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", "SegmentStatus character " + unexpectedValue + " cannot be converted into 1208. Using default value", null);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 760 */         this.groupStatus = 32;
/*     */       } 
/* 762 */       pos++;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 770 */         byte[] convertedSegmentation = new byte[1];
/* 771 */         dc.convertBytes(buffer, pos, cp, convertedSegmentation, 0, cp2, 1, tls);
/* 772 */         this.segmentation = convertedSegmentation[0] & 0xFF;
/*     */       }
/* 774 */       catch (JmqiException e3) {
/* 775 */         if (Trace.isOn) {
/* 776 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.MQGMO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", e3, 4);
/*     */         }
/*     */         
/* 779 */         if (Trace.isOn) {
/* 780 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.MQGMO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", e3, 3);
/*     */ 
/*     */ 
/*     */           
/* 784 */           int unexpectedValue = buffer[pos] & 0xFF;
/* 785 */           Trace.data(this, "com.ibm.mq.jmqi.MQGMO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", "Segmentation character " + unexpectedValue + " cannot be converted into 1208. Using default value", null);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 791 */         this.groupStatus = 32;
/*     */       } 
/* 793 */       pos++;
/*     */ 
/*     */       
/* 796 */       pos++;
/*     */     } 
/*     */ 
/*     */     
/* 800 */     if (this.version >= 3) {
/*     */ 
/*     */       
/* 803 */       System.arraycopy(buffer, pos, this.msgToken, 0, 16);
/* 804 */       pos += 16;
/*     */ 
/*     */       
/* 807 */       this.returnedLength = dc.readI32(buffer, pos, swap);
/* 808 */       pos += 4;
/*     */     } 
/*     */ 
/*     */     
/* 812 */     if (this.version >= 4) {
/*     */ 
/*     */       
/* 815 */       pos += 4;
/*     */ 
/*     */       
/* 818 */       this.messageHandle = dc.readI64(buffer, pos, swap);
/* 819 */       pos += 8;
/*     */     } 
/*     */     
/* 822 */     if (Trace.isOn) {
/* 823 */       Trace.exit(this, "com.ibm.mq.jmqi.MQGMO", "readFromBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", 
/*     */           
/* 825 */           Integer.valueOf(pos));
/*     */     }
/* 827 */     return pos;
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
/* 839 */     fmt.add("version", this.version);
/* 840 */     fmt.add("options", this.options);
/* 841 */     String optionDescription = MQConstants.decodeOptionsForTrace(this.options, "MQGMO_.*");
/* 842 */     fmt.add("option flags", optionDescription);
/* 843 */     fmt.add("waitInterval", this.waitInterval);
/* 844 */     fmt.add("signal2", this.signal2);
/* 845 */     fmt.add("resolvedQName", this.resolvedQName);
/* 846 */     fmt.add("matchOptions", this.matchOptions);
/* 847 */     fmt.add("groupStatus", this.groupStatus);
/* 848 */     fmt.add("segmentStatus", this.segmentStatus);
/* 849 */     fmt.add("msgToken", this.msgToken);
/* 850 */     fmt.add("returnedLength", this.returnedLength);
/* 851 */     fmt.add("messageHandle", this.messageHandle);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQGMO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */