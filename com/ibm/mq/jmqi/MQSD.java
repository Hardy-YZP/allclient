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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQSD
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQSD.java";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_OBJECTNAME = 48;
/*     */   private static final int SIZEOF_ALTERNATEUSERID = 12;
/*     */   private static final int SIZEOF_ALTERNATESECURITYID = 40;
/*     */   private static final int SIZEOF_SUBEXPIRY = 4;
/*     */   private static final int SIZEOF_SUBCORRELID = 24;
/*     */   private static final int SIZEOF_PUBPRIORITY = 4;
/*     */   private static final int SIZEOF_PUBACCOUNTINGTOKEN = 32;
/*     */   private static final int SIZEOF_PUBAPPLIDENTITYDATA = 32;
/*     */   private static final int SIZEOF_SUBLEVEL = 4;
/*     */   
/*     */   static {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.data("com.ibm.mq.jmqi.MQSD", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQSD.java");
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
/*     */   public static int getSizeV1(int ptrSize) {
/* 103 */     int size = 4;
/* 104 */     size += 4;
/* 105 */     size += 4;
/* 106 */     size += 48;
/* 107 */     size += 12;
/* 108 */     size += 40;
/* 109 */     size += 4;
/* 110 */     size += JmqiTools.alignToGrain(ptrSize, size);
/* 111 */     size += MQCHARV.getSize(ptrSize);
/* 112 */     size += MQCHARV.getSize(ptrSize);
/* 113 */     size += MQCHARV.getSize(ptrSize);
/* 114 */     size += 24;
/* 115 */     size += 4;
/* 116 */     size += 32;
/* 117 */     size += 32;
/* 118 */     size += JmqiTools.alignToGrain(ptrSize, size);
/* 119 */     size += MQCHARV.getSize(ptrSize);
/* 120 */     size += 4;
/* 121 */     size += JmqiTools.alignToGrain(ptrSize, size);
/* 122 */     size += MQCHARV.getSize(ptrSize);
/* 123 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*     */     
/* 125 */     return size;
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
/* 139 */     if (version == 1) {
/* 140 */       size = getSizeV1(ptrSize);
/*     */     }
/*     */     else {
/*     */       
/* 144 */       JmqiException e = new JmqiException(env, -1, null, 2, 2424, null);
/* 145 */       throw e;
/*     */     } 
/* 147 */     return size;
/*     */   }
/*     */   
/* 150 */   private int version = 1;
/*     */   private int options;
/*     */   private String objectName;
/*     */   private String alternateUserId;
/*     */   private String alternateSecurityId;
/* 155 */   private int subExpiry = -1;
/*     */   private MQCHARV objectString;
/*     */   private MQCHARV subName;
/*     */   private MQCHARV subUserData;
/* 159 */   private byte[] subCorrelId = new byte[24];
/* 160 */   private int pubPriority = -3;
/* 161 */   private byte[] pubAccountingToken = new byte[32];
/*     */   
/*     */   private String pubApplIdentityData;
/*     */   private MQCHARV selectionString;
/* 165 */   private int subLevel = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   private MQCHARV resObjectString;
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQSD(JmqiEnvironment env) {
/* 174 */     super(env);
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSD", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 178 */     this.objectString = env.newMQCHARV();
/* 179 */     this.subName = env.newMQCHARV();
/* 180 */     this.subUserData = env.newMQCHARV();
/* 181 */     this.selectionString = env.newMQCHARV();
/* 182 */     this.resObjectString = env.newMQCHARV();
/*     */     
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSD", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAlternateSecurityId() {
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "getAlternateSecurityId()", "getter", this.alternateSecurityId);
/*     */     }
/*     */     
/* 199 */     return this.alternateSecurityId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlternateSecurityId(String alternateSecurityId) {
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "setAlternateSecurityId(String)", "setter", alternateSecurityId);
/*     */     }
/*     */     
/* 211 */     this.alternateSecurityId = alternateSecurityId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAlternateUserId() {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "getAlternateUserId()", "getter", this.alternateUserId);
/*     */     }
/* 222 */     return this.alternateUserId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlternateUserId(String alternateUserId) {
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "setAlternateUserId(String)", "setter", alternateUserId);
/*     */     }
/*     */     
/* 234 */     this.alternateUserId = alternateUserId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getObjectName() {
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "getObjectName()", "getter", this.objectName);
/*     */     }
/* 245 */     return this.objectName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectName(String objectName) {
/* 253 */     if (Trace.isOn) {
/* 254 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "setObjectName(String)", "setter", objectName);
/*     */     }
/* 256 */     this.objectName = objectName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCHARV getObjectString() {
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "getObjectString()", "getter", this.objectString);
/*     */     }
/* 267 */     return this.objectString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "getOptions()", "getter", Integer.valueOf(this.options));
/*     */     }
/*     */     
/* 279 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 287 */     if (Trace.isOn) {
/* 288 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "setOptions(int)", "setter", 
/* 289 */           Integer.valueOf(options));
/*     */     }
/* 291 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getPubAccountingToken() {
/* 299 */     if (Trace.isOn) {
/* 300 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "getPubAccountingToken()", "getter", this.pubAccountingToken);
/*     */     }
/*     */     
/* 303 */     return this.pubAccountingToken;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPubAccountingToken(byte[] pubAccountingToken) {
/* 311 */     if (Trace.isOn) {
/* 312 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "setPubAccountingToken(byte [ ])", "setter", pubAccountingToken);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 317 */     int length = pubAccountingToken.length;
/* 318 */     if (length < 32) {
/* 319 */       this.pubAccountingToken = new byte[32];
/* 320 */       System.arraycopy(pubAccountingToken, 0, this.pubAccountingToken, 0, length);
/*     */     } else {
/*     */       
/* 323 */       this.pubAccountingToken = pubAccountingToken;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPubApplIdentityData() {
/* 332 */     if (Trace.isOn) {
/* 333 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "getPubApplIdentityData()", "getter", this.pubApplIdentityData);
/*     */     }
/*     */     
/* 336 */     return this.pubApplIdentityData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPubApplIdentityData(String pubApplIdentityData) {
/* 344 */     if (Trace.isOn) {
/* 345 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "setPubApplIdentityData(String)", "setter", pubApplIdentityData);
/*     */     }
/*     */     
/* 348 */     this.pubApplIdentityData = pubApplIdentityData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPubPriority() {
/* 356 */     if (Trace.isOn) {
/* 357 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "getPubPriority()", "getter", 
/* 358 */           Integer.valueOf(this.pubPriority));
/*     */     }
/* 360 */     return this.pubPriority;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPubPriority(int pubPriority) {
/* 368 */     if (Trace.isOn) {
/* 369 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "setPubPriority(int)", "setter", 
/* 370 */           Integer.valueOf(pubPriority));
/*     */     }
/* 372 */     this.pubPriority = pubPriority;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getSubCorrelId() {
/* 380 */     if (Trace.isOn) {
/* 381 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "getSubCorrelId()", "getter", this.subCorrelId);
/*     */     }
/* 383 */     return this.subCorrelId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubCorrelId(byte[] subCorrelId) {
/* 391 */     if (Trace.isOn) {
/* 392 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "setSubCorrelId(byte [ ])", "setter", subCorrelId);
/*     */     }
/*     */ 
/*     */     
/* 396 */     int length = subCorrelId.length;
/* 397 */     if (length < 24) {
/* 398 */       this.subCorrelId = new byte[24];
/* 399 */       System.arraycopy(subCorrelId, 0, this.subCorrelId, 0, length);
/*     */     } else {
/*     */       
/* 402 */       this.subCorrelId = subCorrelId;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSubExpiry() {
/* 411 */     if (Trace.isOn) {
/* 412 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "getSubExpiry()", "getter", 
/* 413 */           Integer.valueOf(this.subExpiry));
/*     */     }
/* 415 */     return this.subExpiry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubExpiry(int subExpiry) {
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "setSubExpiry(int)", "setter", 
/* 425 */           Integer.valueOf(subExpiry));
/*     */     }
/* 427 */     this.subExpiry = subExpiry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCHARV getSubName() {
/* 435 */     if (Trace.isOn) {
/* 436 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "getSubName()", "getter", this.subName);
/*     */     }
/* 438 */     return this.subName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCHARV getSubUserData() {
/* 446 */     if (Trace.isOn) {
/* 447 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "getSubUserData()", "getter", this.subUserData);
/*     */     }
/* 449 */     return this.subUserData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCHARV getSelectionString() {
/* 457 */     if (Trace.isOn) {
/* 458 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "getSelectionString()", "getter", this.selectionString);
/*     */     }
/* 460 */     return this.selectionString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSubLevel() {
/* 467 */     if (Trace.isOn) {
/* 468 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "getSubLevel()", "getter", 
/* 469 */           Integer.valueOf(this.subLevel));
/*     */     }
/* 471 */     return this.subLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubLevel(int subLevel) {
/* 478 */     if (Trace.isOn) {
/* 479 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "setSubLevel(int)", "setter", 
/* 480 */           Integer.valueOf(subLevel));
/*     */     }
/* 482 */     this.subLevel = subLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public MQCHARV getResolvedObjectString() {
/* 491 */     MQCHARV traceRet1 = getResObjectString();
/* 492 */     if (Trace.isOn) {
/* 493 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "getResolvedObjectString()", "getter", traceRet1);
/*     */     }
/* 495 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCHARV getResObjectString() {
/* 502 */     if (Trace.isOn) {
/* 503 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "getResObjectString()", "getter", this.resObjectString);
/*     */     }
/* 505 */     return this.resObjectString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 513 */     if (Trace.isOn) {
/* 514 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "getVersion()", "getter", Integer.valueOf(this.version));
/*     */     }
/*     */     
/* 517 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 526 */     if (Trace.isOn) {
/* 527 */       Trace.data(this, "com.ibm.mq.jmqi.MQSD", "setVersion(int)", "setter", 
/* 528 */           Integer.valueOf(version));
/*     */     }
/* 530 */     this.version = version;
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
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 542 */     int size = getSize(this.env, this.version, ptrSize);
/*     */ 
/*     */     
/* 545 */     size += this.objectString.getRequiredBufferSize(ptrSize, cp);
/* 546 */     size += this.subName.getRequiredBufferSize(ptrSize, cp);
/* 547 */     size += this.subUserData.getRequiredBufferSize(ptrSize, cp);
/* 548 */     size += this.selectionString.getRequiredBufferSize(ptrSize, cp);
/* 549 */     size += this.resObjectString.getRequiredBufferSize(ptrSize, cp);
/*     */     
/* 551 */     return size;
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
/*     */   public int getRequiredInputBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 565 */     int size = getSize(this.env, this.version, ptrSize);
/*     */ 
/*     */     
/* 568 */     size += this.objectString.getRequiredInputBufferSize(ptrSize, cp);
/* 569 */     size += this.subName.getRequiredInputBufferSize(ptrSize, cp);
/* 570 */     size += this.subUserData.getRequiredInputBufferSize(ptrSize, cp);
/* 571 */     size += this.selectionString.getRequiredInputBufferSize(ptrSize, cp);
/* 572 */     size += this.resObjectString.getRequiredInputBufferSize(ptrSize, cp);
/*     */     
/* 574 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaximumRequiredBufferSize(int ptrSize, JmqiCodepage cp) {
/* 585 */     if (Trace.isOn)
/* 586 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSD", "getMaximumRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 587 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 589 */     int size = getSizeV1(ptrSize);
/* 590 */     size += 10240;
/* 591 */     size += 10240;
/* 592 */     size += 10240;
/* 593 */     size += 10240;
/*     */     
/* 595 */     if (Trace.isOn) {
/* 596 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSD", "getMaximumRequiredBufferSize(int,JmqiCodepage)", 
/* 597 */           Integer.valueOf(size));
/*     */     }
/* 599 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 607 */     if (Trace.isOn) {
/* 608 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 610 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 612 */     int pos = offset;
/* 613 */     int startPos = pos;
/* 614 */     int objectStringPos = -1;
/* 615 */     int subNamePos = -1;
/* 616 */     int subUserDataPos = -1;
/* 617 */     int selectionStringPos = -1;
/* 618 */     int resObjectStringPos = -1;
/* 619 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 621 */     dc.writeMQField("SD  ", buffer, pos, 4, cp, tls);
/* 622 */     pos += 4;
/*     */     
/* 624 */     dc.writeI32(this.version, buffer, pos, swap);
/* 625 */     pos += 4;
/*     */     
/* 627 */     dc.writeI32(this.options, buffer, pos, swap);
/* 628 */     pos += 4;
/*     */     
/* 630 */     dc.writeMQField(this.objectName, buffer, pos, 48, cp, tls);
/* 631 */     pos += 48;
/*     */     
/* 633 */     dc.writeMQField(this.alternateUserId, buffer, pos, 12, cp, tls);
/* 634 */     pos += 12;
/*     */     
/* 636 */     dc.writeField(this.alternateSecurityId, buffer, pos, 40, cp, tls);
/* 637 */     pos += 40;
/*     */     
/* 639 */     dc.writeI32(this.subExpiry, buffer, pos, swap);
/* 640 */     pos += 4;
/*     */     
/* 642 */     int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 643 */     dc.clear(buffer, pos, padding);
/* 644 */     pos += padding;
/*     */     
/* 646 */     objectStringPos = pos;
/* 647 */     pos += MQCHARV.getSize(ptrSize);
/*     */     
/* 649 */     subNamePos = pos;
/* 650 */     pos += MQCHARV.getSize(ptrSize);
/*     */     
/* 652 */     subUserDataPos = pos;
/* 653 */     pos += MQCHARV.getSize(ptrSize);
/*     */     
/* 655 */     System.arraycopy(this.subCorrelId, 0, buffer, pos, 24);
/* 656 */     pos += 24;
/*     */     
/* 658 */     dc.writeI32(this.pubPriority, buffer, pos, swap);
/* 659 */     pos += 4;
/*     */     
/* 661 */     System.arraycopy(this.pubAccountingToken, 0, buffer, pos, 32);
/* 662 */     pos += 32;
/*     */     
/* 664 */     JmqiCodepage mycp = JmqiCodepage.getJmqiCodepage(this.env, 1208);
/* 665 */     if (mycp == null) {
/* 666 */       JmqiException traceRet1 = new JmqiException(this.env, 6047, new String[] { Integer.toString(1208), null, null, null, "???" }, 2, 2195, null);
/*     */       
/* 668 */       if (Trace.isOn) {
/* 669 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQSD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*     */       }
/*     */       
/* 672 */       throw traceRet1;
/*     */     } 
/* 674 */     dc.writeField(this.pubApplIdentityData, buffer, pos, 32, mycp, tls);
/* 675 */     pos += 32;
/*     */     
/* 677 */     padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 678 */     dc.clear(buffer, pos, padding);
/* 679 */     pos += padding;
/*     */     
/* 681 */     selectionStringPos = pos;
/* 682 */     pos += MQCHARV.getSize(ptrSize);
/*     */     
/* 684 */     dc.writeI32(this.subLevel, buffer, pos, swap);
/* 685 */     pos += 4;
/*     */     
/* 687 */     padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 688 */     dc.clear(buffer, pos, padding);
/* 689 */     pos += padding;
/*     */     
/* 691 */     resObjectStringPos = pos;
/* 692 */     pos += MQCHARV.getSize(ptrSize);
/*     */ 
/*     */     
/* 695 */     pos = this.objectString.writeToBuffer(buffer, startPos, objectStringPos, pos, ptrSize, swap, cp);
/* 696 */     pos = this.subName.writeToBuffer(buffer, startPos, subNamePos, pos, ptrSize, swap, cp);
/* 697 */     pos = this.subUserData.writeToBuffer(buffer, startPos, subUserDataPos, pos, ptrSize, swap, cp);
/* 698 */     pos = this.selectionString.writeToBuffer(buffer, startPos, selectionStringPos, pos, ptrSize, swap, cp);
/* 699 */     pos = this.resObjectString.writeToBuffer(buffer, startPos, resObjectStringPos, pos, ptrSize, swap, cp);
/*     */     
/* 701 */     if (Trace.isOn) {
/* 702 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 703 */           Integer.valueOf(pos));
/*     */     }
/* 705 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 713 */     if (Trace.isOn) {
/* 714 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 716 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 718 */     int pos = offset;
/* 719 */     int startPos = pos;
/* 720 */     int variableDataEnd = 0;
/*     */     
/* 722 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 724 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 725 */     if (!strucId.equals("SD  ")) {
/*     */       
/* 727 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2424, null);
/* 728 */       if (Trace.isOn) {
/* 729 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQSD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*     */       }
/*     */       
/* 732 */       throw traceRet1;
/*     */     } 
/* 734 */     pos += 4;
/*     */ 
/*     */ 
/*     */     
/* 738 */     pos += 112;
/*     */ 
/*     */     
/* 741 */     int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 742 */     pos += padding;
/*     */ 
/*     */     
/* 745 */     pos = this.objectString.readFromBuffer(buffer, startPos, pos, ptrSize, swap, tls);
/* 746 */     int cvEnd = this.objectString.getEndPosAligned(startPos);
/* 747 */     if (cvEnd > variableDataEnd) {
/* 748 */       variableDataEnd = cvEnd;
/*     */     }
/*     */ 
/*     */     
/* 752 */     pos = this.subName.readFromBuffer(buffer, startPos, pos, ptrSize, swap, tls);
/* 753 */     cvEnd = this.subName.getEndPosAligned(startPos);
/* 754 */     if (cvEnd > variableDataEnd) {
/* 755 */       variableDataEnd = cvEnd;
/*     */     }
/*     */ 
/*     */     
/* 759 */     pos = this.subUserData.readFromBuffer(buffer, startPos, pos, ptrSize, swap, tls);
/* 760 */     cvEnd = this.subUserData.getEndPosAligned(startPos);
/* 761 */     if (cvEnd > variableDataEnd) {
/* 762 */       variableDataEnd = cvEnd;
/*     */     }
/*     */ 
/*     */     
/* 766 */     System.arraycopy(buffer, pos, this.subCorrelId, 0, 24);
/* 767 */     pos += 24;
/*     */ 
/*     */     
/* 770 */     pos += 4;
/*     */ 
/*     */     
/* 773 */     System.arraycopy(buffer, pos, this.pubAccountingToken, 0, 32);
/* 774 */     pos += 32;
/*     */ 
/*     */ 
/*     */     
/* 778 */     this.pubApplIdentityData = dc.readMQField(buffer, pos, 32, cp, tls);
/* 779 */     pos += 32;
/*     */ 
/*     */     
/* 782 */     padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 783 */     pos += padding;
/*     */ 
/*     */     
/* 786 */     pos = this.selectionString.readFromBuffer(buffer, startPos, pos, ptrSize, swap, tls);
/* 787 */     cvEnd = this.selectionString.getEndPosAligned(startPos);
/* 788 */     if (cvEnd > variableDataEnd) {
/* 789 */       variableDataEnd = cvEnd;
/*     */     }
/*     */ 
/*     */     
/* 793 */     this.subLevel = dc.readI32(buffer, pos, swap);
/* 794 */     pos += 4;
/*     */ 
/*     */     
/* 797 */     padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 798 */     pos += padding;
/*     */ 
/*     */     
/* 801 */     pos = this.resObjectString.readFromBuffer(buffer, startPos, pos, ptrSize, swap, tls);
/* 802 */     cvEnd = this.resObjectString.getEndPosAligned(startPos);
/* 803 */     if (cvEnd > variableDataEnd) {
/* 804 */       variableDataEnd = cvEnd;
/*     */     }
/*     */ 
/*     */     
/* 808 */     if (variableDataEnd > pos) {
/* 809 */       pos = variableDataEnd;
/*     */     }
/*     */     
/* 812 */     if (Trace.isOn) {
/* 813 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 814 */           Integer.valueOf(pos));
/*     */     }
/* 816 */     return pos;
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
/* 828 */     fmt.add("version", this.version);
/* 829 */     fmt.add("options", this.options);
/* 830 */     fmt.add("objectName", this.objectName);
/* 831 */     fmt.add("alternateUserId", this.alternateUserId);
/* 832 */     fmt.add("alternateSecurityId", this.alternateSecurityId);
/* 833 */     fmt.add("subExpiry", this.subExpiry);
/* 834 */     fmt.add("objectString", this.objectString);
/* 835 */     fmt.add("subName", this.subName);
/* 836 */     fmt.add("subUserData", this.subUserData);
/* 837 */     fmt.add("subCorrelId", this.subCorrelId);
/* 838 */     fmt.add("pubPriority", this.pubPriority);
/* 839 */     fmt.add("pubAccountingToken", this.pubAccountingToken);
/* 840 */     fmt.add("pubApplIdentityData", this.pubApplIdentityData);
/* 841 */     fmt.add("selectionString", this.selectionString);
/* 842 */     fmt.add("subLevel", this.subLevel);
/* 843 */     fmt.add("resObjectString", this.resObjectString);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQSD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */