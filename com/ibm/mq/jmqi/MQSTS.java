/*     */ package com.ibm.mq.jmqi;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQSTS
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQSTS.java";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_COMP_CODE = 4;
/*     */   private static final int SIZEOF_REASON = 4;
/*     */   private static final int SIZEOF_PUT_SUCCESS_COUNT = 4;
/*     */   private static final int SIZEOF_PUT_WARNING_COUNT = 4;
/*     */   private static final int SIZEOF_PUT_FAILURE_COUNT = 4;
/*     */   private static final int SIZEOF_OBJECT_TYPE = 4;
/*     */   private static final int SIZEOF_OBJECT_NAME = 48;
/*     */   private static final int SIZEOF_OBJECT_Q_MGR_NAME = 48;
/*     */   private static final int SIZEOF_RESOLVED_OBJECT_NAME = 48;
/*     */   private static final int SIZEOF_RESOLVE_Q_MGR_NAME = 48;
/*     */   private static final int SIZEOF_OPEN_OPTIONS = 4;
/*     */   private static final int SIZEOF_SUB_OPTIONS = 4;
/*     */   
/*     */   static {
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.data("com.ibm.mq.jmqi.MQSTS", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQSTS.java");
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
/*  91 */   private int version = 1;
/*  92 */   private int compCode = 0;
/*  93 */   private int reason = 0;
/*  94 */   private int putSuccessCount = 0;
/*  95 */   private int putWarningCount = 0;
/*  96 */   private int putFailureCount = 0;
/*  97 */   private int objectType = 0;
/*  98 */   private String objectName = null;
/*  99 */   private String objectQMgrName = null;
/* 100 */   private String resolvedObjectName = null;
/* 101 */   private String resolvedQMgrName = null;
/*     */   private MQCHARV objectString;
/*     */   private MQCHARV subName;
/* 104 */   private int openOptions = 0;
/* 105 */   private int subOptions = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 115 */     int size = 224;
/*     */ 
/*     */ 
/*     */     
/* 119 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV2(int ptrSize) {
/* 130 */     int size = getSizeV1(ptrSize) + 2 * MQCHARV.getSize(ptrSize) + 4 + 4;
/*     */ 
/*     */ 
/*     */     
/* 134 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*     */     
/* 136 */     return size;
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
/*     */   private static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/*     */     int size;
/* 150 */     if (version == 1) {
/* 151 */       size = getSizeV1(ptrSize);
/*     */     }
/* 153 */     else if (version == 2) {
/* 154 */       size = getSizeV2(ptrSize);
/*     */     }
/*     */     else {
/*     */       
/* 158 */       JmqiException e = new JmqiException(env, -1, null, 2, 2426, null);
/*     */       
/* 160 */       throw e;
/*     */     } 
/* 162 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQSTS(JmqiEnvironment env) {
/* 171 */     super(env);
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSTS", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 175 */     this.objectString = env.newMQCHARV();
/* 176 */     this.subName = env.newMQCHARV();
/*     */     
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSTS", "<init>(JmqiEnvironment)");
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
/*     */   public int getCompCode() {
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "getCompCode()", "getter", 
/* 193 */           Integer.valueOf(this.compCode));
/*     */     }
/* 195 */     return this.compCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCompCode(int compCode) {
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "setCompCode(int)", "setter", 
/* 207 */           Integer.valueOf(compCode));
/*     */     }
/* 209 */     this.compCode = compCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPutFailureCount() {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "getPutFailureCount()", "getter", 
/* 221 */           Integer.valueOf(this.putFailureCount));
/*     */     }
/* 223 */     return this.putFailureCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPutFailureCount(int putFailureCount) {
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "setPutFailureCount(int)", "setter", 
/* 235 */           Integer.valueOf(putFailureCount));
/*     */     }
/* 237 */     this.putFailureCount = putFailureCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getObjectName() {
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "getObjectName()", "getter", this.objectName);
/*     */     }
/* 250 */     return this.objectName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectName(String objectName) {
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "setObjectName(String)", "setter", objectName);
/*     */     }
/* 263 */     this.objectName = objectName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getObjectQMgrName() {
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "getObjectQMgrName()", "getter", this.objectQMgrName);
/*     */     }
/* 276 */     return this.objectQMgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectQMgrName(String objectQMgrName) {
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "setObjectQMgrName(String)", "setter", objectQMgrName);
/*     */     }
/*     */     
/* 290 */     this.objectQMgrName = objectQMgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getObjectType() {
/* 300 */     if (Trace.isOn) {
/* 301 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "getObjectType()", "getter", 
/* 302 */           Integer.valueOf(this.objectType));
/*     */     }
/* 304 */     return this.objectType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectType(int objectType) {
/* 314 */     if (Trace.isOn) {
/* 315 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "setObjectType(int)", "setter", 
/* 316 */           Integer.valueOf(objectType));
/*     */     }
/* 318 */     this.objectType = objectType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReason() {
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "getReason()", "getter", Integer.valueOf(this.reason));
/*     */     }
/* 330 */     return this.reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReason(int reason) {
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "setReason(int)", "setter", 
/* 342 */           Integer.valueOf(reason));
/*     */     }
/* 344 */     this.reason = reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getResolvedObjectName() {
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "getResolvedObjectName()", "getter", this.resolvedObjectName);
/*     */     }
/*     */     
/* 358 */     return this.resolvedObjectName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResolvedObjectName(String resolvedObjectName) {
/* 368 */     if (Trace.isOn) {
/* 369 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "setResolvedObjectName(String)", "setter", resolvedObjectName);
/*     */     }
/*     */     
/* 372 */     this.resolvedObjectName = resolvedObjectName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getResolvedQMgrName() {
/* 382 */     if (Trace.isOn) {
/* 383 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "getResolvedQMgrName()", "getter", this.resolvedQMgrName);
/*     */     }
/*     */     
/* 386 */     return this.resolvedQMgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResolvedQMgrName(String resolvedQMgrName) {
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "setResolvedQMgrName(String)", "setter", resolvedQMgrName);
/*     */     }
/*     */     
/* 400 */     this.resolvedQMgrName = resolvedQMgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPutSuccessCount() {
/* 410 */     if (Trace.isOn) {
/* 411 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "getPutSuccessCount()", "getter", 
/* 412 */           Integer.valueOf(this.putSuccessCount));
/*     */     }
/* 414 */     return this.putSuccessCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPutSuccessCount(int putSuccessCount) {
/* 424 */     if (Trace.isOn) {
/* 425 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "setPutSuccessCount(int)", "setter", 
/* 426 */           Integer.valueOf(putSuccessCount));
/*     */     }
/* 428 */     this.putSuccessCount = putSuccessCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 439 */     if (Trace.isOn) {
/* 440 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "getVersion()", "getter", Integer.valueOf(this.version));
/*     */     }
/*     */     
/* 443 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 454 */     if (Trace.isOn) {
/* 455 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "setVersion(int)", "setter", 
/* 456 */           Integer.valueOf(version));
/*     */     }
/* 458 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPutWarningCount() {
/* 468 */     if (Trace.isOn) {
/* 469 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "getPutWarningCount()", "getter", 
/* 470 */           Integer.valueOf(this.putWarningCount));
/*     */     }
/* 472 */     return this.putWarningCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPutWarningCount(int putWarningCount) {
/* 482 */     if (Trace.isOn) {
/* 483 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "setPutWarningCount(int)", "setter", 
/* 484 */           Integer.valueOf(putWarningCount));
/*     */     }
/* 486 */     this.putWarningCount = putWarningCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCHARV getObjectString() {
/* 495 */     if (Trace.isOn) {
/* 496 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "getObjectString()", "getter", this.objectString);
/*     */     }
/* 498 */     return this.objectString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCHARV getSubName() {
/* 508 */     if (Trace.isOn) {
/* 509 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "getSubName()", "getter", this.subName);
/*     */     }
/* 511 */     return this.subName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOpenOptions() {
/* 521 */     if (Trace.isOn) {
/* 522 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "getOpenOptions()", "getter", 
/* 523 */           Integer.valueOf(this.objectType));
/*     */     }
/* 525 */     return this.objectType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOpenOptions(int objectType) {
/* 535 */     if (Trace.isOn) {
/* 536 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "setOpenOptions(int)", "setter", 
/* 537 */           Integer.valueOf(objectType));
/*     */     }
/* 539 */     this.objectType = objectType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSubOptions() {
/* 548 */     if (Trace.isOn) {
/* 549 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "getSubOptions()", "getter", 
/* 550 */           Integer.valueOf(this.subOptions));
/*     */     }
/* 552 */     return this.subOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubOptions(int options) {
/* 562 */     if (Trace.isOn) {
/* 563 */       Trace.data(this, "com.ibm.mq.jmqi.MQSTS", "setSubOptions(int)", "setter", 
/* 564 */           Integer.valueOf(options));
/*     */     }
/* 566 */     this.subOptions = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 575 */     int size = getSize(this.env, this.version, ptrSize);
/* 576 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 585 */     if (Trace.isOn) {
/* 586 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSTS", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 588 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 590 */     int pos = offset;
/* 591 */     int startPos = pos;
/* 592 */     int objectStringPos = -1;
/* 593 */     int subNamePos = -1;
/* 594 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 596 */     dc.writeMQField("STAT", buffer, pos, 4, cp, tls);
/* 597 */     pos += 4;
/*     */     
/* 599 */     dc.writeI32(this.version, buffer, pos, swap);
/* 600 */     pos += 4;
/*     */ 
/*     */ 
/*     */     
/* 604 */     int clearBytes = 216;
/*     */ 
/*     */ 
/*     */     
/* 608 */     dc.clear(buffer, pos, clearBytes);
/* 609 */     pos += clearBytes;
/*     */     
/* 611 */     if (this.version >= 2) {
/*     */       
/* 613 */       objectStringPos = pos;
/* 614 */       pos += MQCHARV.getSize(ptrSize);
/*     */       
/* 616 */       subNamePos = pos;
/* 617 */       pos += MQCHARV.getSize(ptrSize);
/*     */       
/* 619 */       dc.writeI32(this.version, buffer, pos, swap);
/* 620 */       pos += 4;
/*     */       
/* 622 */       dc.writeI32(this.version, buffer, pos, swap);
/* 623 */       pos += 4;
/*     */     } 
/*     */     
/* 626 */     if (this.version == 2) {
/* 627 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 628 */       dc.clear(buffer, pos, padding);
/* 629 */       pos += padding;
/*     */     } 
/*     */ 
/*     */     
/* 633 */     if (objectStringPos >= 0) {
/* 634 */       pos = this.objectString.writeToBuffer(buffer, startPos, objectStringPos, pos, ptrSize, swap, cp);
/*     */     }
/* 636 */     if (subNamePos >= 0) {
/* 637 */       pos = this.subName.writeToBuffer(buffer, startPos, subNamePos, pos, ptrSize, swap, cp);
/*     */     }
/*     */     
/* 640 */     if (Trace.isOn) {
/* 641 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSTS", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
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
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 653 */     if (Trace.isOn) {
/* 654 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSTS", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 656 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 658 */     int pos = offset;
/* 659 */     int startPos = pos;
/* 660 */     int variableDataEnd = 0;
/*     */     
/* 662 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 664 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 665 */     if (!strucId.equals("STAT")) {
/*     */       
/* 667 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2426, null);
/*     */       
/* 669 */       if (Trace.isOn) {
/* 670 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQSTS", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*     */       }
/*     */       
/* 673 */       throw traceRet1;
/*     */     } 
/* 675 */     pos += 4;
/*     */ 
/*     */     
/* 678 */     pos += 4;
/*     */ 
/*     */     
/* 681 */     this.compCode = dc.readI32(buffer, pos, swap);
/* 682 */     pos += 4;
/*     */ 
/*     */     
/* 685 */     this.reason = dc.readI32(buffer, pos, swap);
/* 686 */     pos += 4;
/*     */ 
/*     */     
/* 689 */     this.putSuccessCount = dc.readI32(buffer, pos, swap);
/* 690 */     pos += 4;
/*     */ 
/*     */     
/* 693 */     this.putWarningCount = dc.readI32(buffer, pos, swap);
/* 694 */     pos += 4;
/*     */ 
/*     */     
/* 697 */     this.putFailureCount = dc.readI32(buffer, pos, swap);
/* 698 */     pos += 4;
/*     */ 
/*     */     
/* 701 */     this.objectType = dc.readI32(buffer, pos, swap);
/* 702 */     pos += 4;
/*     */ 
/*     */     
/* 705 */     this.objectName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 706 */     pos += 48;
/*     */ 
/*     */     
/* 709 */     this.objectQMgrName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 710 */     pos += 48;
/*     */ 
/*     */     
/* 713 */     if (this.version >= 2) {
/*     */ 
/*     */       
/* 716 */       pos = this.objectString.readFromBuffer(buffer, startPos, pos, ptrSize, swap, tls);
/* 717 */       int cvEnd = this.objectString.getEndPosAligned(startPos);
/* 718 */       if (cvEnd > variableDataEnd) {
/* 719 */         variableDataEnd = cvEnd;
/*     */       }
/*     */ 
/*     */       
/* 723 */       pos = this.subName.readFromBuffer(buffer, startPos, pos, ptrSize, swap, tls);
/* 724 */       cvEnd = this.subName.getEndPosAligned(startPos);
/* 725 */       if (cvEnd > variableDataEnd) {
/* 726 */         variableDataEnd = cvEnd;
/*     */       }
/*     */ 
/*     */       
/* 730 */       this.openOptions = dc.readI32(buffer, pos, swap);
/* 731 */       pos += 4;
/*     */ 
/*     */       
/* 734 */       this.subOptions = dc.readI32(buffer, pos, swap);
/* 735 */       pos += 4;
/*     */     } 
/*     */ 
/*     */     
/* 739 */     if (this.version == 2) {
/* 740 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 741 */       pos += padding;
/*     */     } 
/*     */ 
/*     */     
/* 745 */     if (variableDataEnd > pos) {
/* 746 */       pos = variableDataEnd;
/*     */     }
/*     */     
/* 749 */     if (Trace.isOn) {
/* 750 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSTS", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 751 */           Integer.valueOf(pos));
/*     */     }
/* 753 */     return pos;
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
/* 765 */     fmt.add("version", this.version);
/* 766 */     fmt.add("compCode", this.compCode);
/* 767 */     fmt.add("reason", this.reason);
/* 768 */     fmt.add("putSuccessCount", this.putSuccessCount);
/* 769 */     fmt.add("putWarningCount", this.putWarningCount);
/* 770 */     fmt.add("putFailureCount", this.putFailureCount);
/* 771 */     fmt.add("objectType", this.objectType);
/* 772 */     fmt.add("objectName", this.objectName);
/* 773 */     fmt.add("objectQMgrName", this.objectQMgrName);
/* 774 */     fmt.add("resolvedObjectName", this.resolvedObjectName);
/* 775 */     fmt.add("resolvedQMgrName", this.resolvedQMgrName);
/* 776 */     fmt.add("objectString", this.objectString);
/* 777 */     fmt.add("subName", this.subName);
/* 778 */     fmt.add("openOptions", this.openOptions);
/* 779 */     fmt.add("subOptions", this.subOptions);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQSTS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */