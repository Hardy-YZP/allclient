/*     */ package com.ibm.mq.exits;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQCSP
/*     */   extends AbstractMqiStructure
/*     */   implements Cloneable
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/exits/MQCSP.java";
/*     */   private static final int SIZEOF_STRUC_ID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_AUTHENTICATION_TYPE = 4;
/*     */   private static final int SIZEOF_RESERVED1 = 4;
/*     */   private static final int SIZEOF_CSP_USER_ID_OFFSET = 4;
/*     */   private static final int SIZEOF_CSP_USER_ID_LENGTH = 4;
/*     */   private static final int SIZEOF_RESERVED2 = 8;
/*     */   private static final int SIZEOF_CSP_PASSWORD_OFFSET = 4;
/*     */   private static final int SIZEOF_CSP_PASSWORD_LENGTH = 4;
/*     */   private static final int SIZEOF_RESERVED3 = 8;
/*     */   private static final int SIZEOF_INITIAL_KEY_OFFSET = 4;
/*     */   private static final int SIZEOF_INITIAL_KEY_LENGTH = 4;
/*     */   
/*     */   static {
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.data("com.ibm.mq.exits.MQCSP", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/exits/MQCSP.java");
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
/* 105 */   private int version = 1;
/* 106 */   private int authenticationType = 0;
/* 107 */   private String cspUserId = null;
/* 108 */   private String cspPassword = null;
/* 109 */   private String initialKey = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] cspUserId_cachedBytes;
/*     */ 
/*     */   
/*     */   private byte[] cspPassword_cachedBytes;
/*     */ 
/*     */   
/*     */   private byte[] initialKey_cachedBytes;
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCSP(JmqiEnvironment env) {
/* 124 */     super(env);
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.entry(this, "com.ibm.mq.exits.MQCSP", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.exit(this, "com.ibm.mq.exits.MQCSP", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrentVersion() {
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.data(this, "com.ibm.mq.exits.MQCSP", "getCurrentVersion()", "getter", 
/* 141 */           Integer.valueOf(2));
/*     */     }
/* 143 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV1(int ptrSize) {
/* 153 */     int size = 0;
/* 154 */     size += 4;
/* 155 */     size += 4;
/* 156 */     size += 4;
/* 157 */     size += 4;
/* 158 */     size += ptrSize;
/* 159 */     size += 4;
/* 160 */     size += 4;
/* 161 */     size += 8;
/* 162 */     size += ptrSize;
/* 163 */     size += 4;
/* 164 */     size += 4;
/*     */     
/* 166 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 176 */     int size = getFieldSizeV1(ptrSize);
/* 177 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*     */     
/* 179 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV2(int ptrSize) {
/* 189 */     int size = getFieldSizeV1(ptrSize);
/* 190 */     size += 8;
/* 191 */     size += ptrSize;
/* 192 */     size += 4;
/* 193 */     size += 4;
/*     */     
/* 195 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV2(int ptrSize) {
/* 205 */     int size = getFieldSizeV2(ptrSize);
/* 206 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*     */     
/* 208 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize(int ptrSize) throws JmqiException {
/* 219 */     if (Trace.isOn)
/* 220 */       Trace.entry(this, "com.ibm.mq.exits.MQCSP", "getSize(int)", new Object[] {
/* 221 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 223 */     int traceRet1 = getSize(this.env, this.version, ptrSize);
/*     */     
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.exit(this, "com.ibm.mq.exits.MQCSP", "getSize(int)", Integer.valueOf(traceRet1));
/*     */     }
/* 228 */     return traceRet1;
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
/*     */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/*     */     int size;
/* 244 */     switch (version) {
/*     */       case 1:
/* 246 */         size = getSizeV1(ptrSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 257 */         return size;case 2: size = getSizeV2(ptrSize); return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 6128, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 270 */     int size = getSize(this.env, this.version, ptrSize);
/*     */ 
/*     */     
/* 273 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 275 */     this.cspUserId_cachedBytes = dc.getStrBytes(this.cspUserId, cp);
/* 276 */     size += this.cspUserId_cachedBytes.length;
/*     */     
/* 278 */     this.cspPassword_cachedBytes = dc.getStrBytes(this.cspPassword, cp);
/* 279 */     size += this.cspPassword_cachedBytes.length;
/*     */     
/* 281 */     this.initialKey_cachedBytes = dc.getStrBytes(this.initialKey, cp);
/* 282 */     size += this.initialKey_cachedBytes.length;
/*     */     
/* 284 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 295 */     if (Trace.isOn) {
/* 296 */       Trace.entry(this, "com.ibm.mq.exits.MQCSP", "clone()");
/*     */     }
/*     */     
/* 299 */     Object newObject = super.clone();
/* 300 */     MQCSP newMQCSP = (MQCSP)newObject;
/*     */     
/* 302 */     newMQCSP.setVersion(this.version);
/* 303 */     newMQCSP.setAuthenticationType(this.authenticationType);
/* 304 */     newMQCSP.setCspUserId(this.cspUserId);
/* 305 */     newMQCSP.setCspPassword(this.cspPassword);
/* 306 */     newMQCSP.setInitialKey(this.initialKey);
/*     */     
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.exit(this, "com.ibm.mq.exits.MQCSP", "clone()", newObject);
/*     */     }
/* 311 */     return newObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.entry(this, "com.ibm.mq.exits.MQCSP", "hashCode()");
/*     */     }
/* 324 */     int hashCode = 0;
/* 325 */     hashCode += 31 * this.version;
/* 326 */     hashCode += 37 * this.authenticationType;
/* 327 */     hashCode += 43 * ((this.cspUserId != null) ? this.cspUserId.hashCode() : 0);
/* 328 */     hashCode += 53 * ((this.cspPassword != null) ? this.cspPassword.hashCode() : 0);
/* 329 */     hashCode += 63 * ((this.initialKey != null) ? this.initialKey.hashCode() : 0);
/*     */     
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.exit(this, "com.ibm.mq.exits.MQCSP", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 334 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 345 */     if (Trace.isOn) {
/* 346 */       Trace.entry(this, "com.ibm.mq.exits.MQCSP", "equals(Object)", new Object[] { object });
/*     */     }
/* 348 */     boolean areEqual = true;
/* 349 */     if (object == null) {
/* 350 */       areEqual = false;
/*     */     }
/* 352 */     else if (object instanceof MQCSP) {
/* 353 */       MQCSP mqcsp = (MQCSP)object;
/* 354 */       areEqual = (areEqual && this.version == mqcsp.getVersion());
/* 355 */       areEqual = (areEqual && this.authenticationType == mqcsp.getAuthenticationType());
/*     */       
/* 357 */       if (this.cspUserId == null) {
/* 358 */         areEqual = (areEqual && mqcsp.getCspUserId() == null);
/*     */       } else {
/*     */         
/* 361 */         areEqual = (areEqual && this.cspUserId.equals(mqcsp.getCspUserId()));
/*     */       } 
/*     */       
/* 364 */       if (this.cspPassword == null) {
/* 365 */         areEqual = (areEqual && mqcsp.getCspPassword() == null);
/*     */       } else {
/*     */         
/* 368 */         areEqual = (areEqual && this.cspPassword.equals(mqcsp.getCspPassword()));
/*     */       } 
/*     */       
/* 371 */       if (this.initialKey == null) {
/* 372 */         areEqual = (areEqual && mqcsp.getInitialKey() == null);
/*     */       } else {
/*     */         
/* 375 */         areEqual = (areEqual && this.initialKey.equals(mqcsp.getInitialKey()));
/*     */       } 
/*     */     } else {
/*     */       
/* 379 */       areEqual = false;
/*     */     } 
/*     */     
/* 382 */     if (Trace.isOn) {
/* 383 */       Trace.exit(this, "com.ibm.mq.exits.MQCSP", "equals(Object)", Boolean.valueOf(areEqual));
/*     */     }
/* 385 */     return areEqual;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 393 */     if (Trace.isOn) {
/* 394 */       Trace.data(this, "com.ibm.mq.exits.MQCSP", "getVersion()", "getter", 
/* 395 */           Integer.valueOf(this.version));
/*     */     }
/* 397 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 405 */     if (Trace.isOn) {
/* 406 */       Trace.data(this, "com.ibm.mq.exits.MQCSP", "setVersion(int)", "setter", 
/* 407 */           Integer.valueOf(version));
/*     */     }
/* 409 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAuthenticationType() {
/* 416 */     if (Trace.isOn) {
/* 417 */       Trace.data(this, "com.ibm.mq.exits.MQCSP", "getAuthenticationType()", "getter", 
/* 418 */           Integer.valueOf(this.authenticationType));
/*     */     }
/* 420 */     return this.authenticationType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAuthenticationType(int authenticationType) {
/* 427 */     if (Trace.isOn) {
/* 428 */       Trace.data(this, "com.ibm.mq.exits.MQCSP", "setAuthenticationType(int)", "setter", 
/* 429 */           Integer.valueOf(authenticationType));
/*     */     }
/* 431 */     this.authenticationType = authenticationType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCspUserId() {
/* 438 */     if (Trace.isOn) {
/* 439 */       Trace.data(this, "com.ibm.mq.exits.MQCSP", "getCspUserId()", "getter", this.cspUserId);
/*     */     }
/* 441 */     return this.cspUserId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCspUserId(String cspUserId) {
/* 448 */     if (Trace.isOn) {
/* 449 */       Trace.data(this, "com.ibm.mq.exits.MQCSP", "setCspUserId(String)", "setter", cspUserId);
/*     */     }
/* 451 */     this.cspUserId = cspUserId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCspPassword() {
/* 459 */     if (Trace.isOn) {
/* 460 */       Trace.data(this, "com.ibm.mq.exits.MQCSP", "getCspPassword()", "getter", (this.cspPassword == null) ? null : "********");
/*     */     }
/*     */     
/* 463 */     return this.cspPassword;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCspPassword(String cspPassword) {
/* 471 */     if (Trace.isOn) {
/* 472 */       Trace.data(this, "com.ibm.mq.exits.MQCSP", "setCspPassword(String)", "setter", (cspPassword == null) ? null : "********");
/*     */     }
/*     */     
/* 475 */     this.cspPassword = cspPassword;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInitialKey() {
/* 483 */     if (Trace.isOn) {
/* 484 */       Trace.data(this, "com.ibm.mq.exits.MQCSP", "getInitialKey()", "getter", (this.initialKey == null) ? null : "********");
/*     */     }
/*     */     
/* 487 */     return this.initialKey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInitialKey(String initialKey) {
/* 495 */     if (Trace.isOn) {
/* 496 */       Trace.data(this, "com.ibm.mq.exits.MQCSP", "setInitialKey(String)", "setter", (initialKey == null) ? null : "********");
/*     */     }
/*     */     
/* 499 */     this.initialKey = initialKey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToTraceBuffer(byte[] buffer, int pos, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 507 */     if (Trace.isOn) {
/* 508 */       Trace.entry(this, "com.ibm.mq.exits.MQCSP", "writeToTraceBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 510 */             Integer.valueOf(pos), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 512 */     int traceRet1 = writeToBuffer(buffer, pos, true, ptrSize, swap, cp, tls);
/*     */     
/* 514 */     if (Trace.isOn) {
/* 515 */       Trace.exit(this, "com.ibm.mq.exits.MQCSP", "writeToTraceBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/*     */           
/* 517 */           Integer.valueOf(traceRet1));
/*     */     }
/* 519 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 527 */     if (Trace.isOn) {
/* 528 */       Trace.entry(this, "com.ibm.mq.exits.MQCSP", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 530 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 532 */     int traceRet1 = writeToBuffer(buffer, offset, false, ptrSize, swap, cp, tls);
/*     */     
/* 534 */     if (Trace.isOn) {
/* 535 */       Trace.exit(this, "com.ibm.mq.exits.MQCSP", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 536 */           Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 539 */     return traceRet1;
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
/*     */   public int writeToBuffer(byte[] buffer, int offset, boolean obscure, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 554 */     if (Trace.isOn) {
/* 555 */       Trace.entry(this, "com.ibm.mq.exits.MQCSP", "writeToBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 557 */             Integer.valueOf(offset), Boolean.valueOf(obscure), Integer.valueOf(ptrSize), 
/* 558 */             Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 560 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 561 */     int pos = offset;
/*     */     
/* 563 */     int cspPasswordLengthPosition = -1;
/* 564 */     int cspPasswordOffsetPosition = -1;
/* 565 */     int cspUserIdLengthPosition = -1;
/* 566 */     int cspUserIdOffsetPosition = -1;
/* 567 */     int initKeyLengthPosition = -1;
/* 568 */     int initKeyOffsetPosition = -1;
/*     */     
/* 570 */     dc.writeMQField("CSP ", buffer, pos, 4, cp, tls);
/* 571 */     pos += 4;
/*     */     
/* 573 */     dc.writeI32(this.version, buffer, pos, swap);
/* 574 */     pos += 4;
/*     */     
/* 576 */     dc.writeI32(this.authenticationType, buffer, pos, swap);
/* 577 */     pos += 4;
/*     */     
/* 579 */     dc.clear(buffer, pos, 4);
/* 580 */     pos += 4;
/*     */     
/* 582 */     dc.clear(buffer, pos, ptrSize);
/* 583 */     pos += ptrSize;
/*     */     
/* 585 */     cspUserIdOffsetPosition = pos;
/* 586 */     pos += 4;
/*     */     
/* 588 */     cspUserIdLengthPosition = pos;
/* 589 */     pos += 4;
/*     */     
/* 591 */     dc.clear(buffer, pos, 8);
/* 592 */     pos += 8;
/*     */     
/* 594 */     dc.clear(buffer, pos, ptrSize);
/* 595 */     pos += ptrSize;
/*     */     
/* 597 */     cspPasswordOffsetPosition = pos;
/* 598 */     pos += 4;
/*     */     
/* 600 */     cspPasswordLengthPosition = pos;
/* 601 */     pos += 4;
/*     */     
/* 603 */     if (this.version == 1) {
/* 604 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 605 */       dc.clear(buffer, pos, padding);
/* 606 */       pos += padding;
/*     */     } 
/*     */     
/* 609 */     if (this.version >= 2) {
/*     */       
/* 611 */       dc.clear(buffer, pos, 8);
/* 612 */       pos += 8;
/*     */       
/* 614 */       dc.clear(buffer, pos, ptrSize);
/* 615 */       pos += ptrSize;
/*     */       
/* 617 */       initKeyOffsetPosition = pos;
/* 618 */       pos += 4;
/*     */       
/* 620 */       initKeyLengthPosition = pos;
/* 621 */       pos += 4;
/*     */       
/* 623 */       if (this.version == 2) {
/* 624 */         int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 625 */         dc.clear(buffer, pos, padding);
/* 626 */         pos += padding;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 634 */     if (cspUserIdOffsetPosition > 0) {
/*     */       byte[] cspUserIdBytes;
/*     */       
/* 637 */       if (this.cspUserId_cachedBytes != null) {
/* 638 */         cspUserIdBytes = this.cspUserId_cachedBytes;
/* 639 */         this.cspUserId_cachedBytes = null;
/*     */       } else {
/*     */         
/* 642 */         cspUserIdBytes = dc.getStrBytes(this.cspUserId, cp);
/*     */       } 
/*     */       
/* 645 */       dc.writeI32(pos - offset, buffer, cspUserIdOffsetPosition, swap);
/* 646 */       dc.writeI32(cspUserIdBytes.length, buffer, cspUserIdLengthPosition, swap);
/*     */       
/* 648 */       System.arraycopy(cspUserIdBytes, 0, buffer, pos, cspUserIdBytes.length);
/* 649 */       pos += cspUserIdBytes.length;
/*     */     } 
/*     */     
/* 652 */     if (cspPasswordOffsetPosition > 0) {
/*     */       byte[] cspPasswordBytes;
/*     */       
/* 655 */       if (this.cspPassword_cachedBytes != null) {
/* 656 */         cspPasswordBytes = this.cspPassword_cachedBytes;
/* 657 */         this.cspPassword_cachedBytes = null;
/*     */       } else {
/*     */         
/* 660 */         cspPasswordBytes = dc.getStrBytes(this.cspPassword, cp);
/*     */       } 
/*     */       
/* 663 */       dc.writeI32(pos - offset, buffer, cspPasswordOffsetPosition, swap);
/* 664 */       dc.writeI32(cspPasswordBytes.length, buffer, cspPasswordLengthPosition, swap);
/*     */       
/* 666 */       if (obscure) {
/* 667 */         for (int i = 0; i < cspPasswordBytes.length; i++) {
/* 668 */           buffer[pos + i] = 120;
/*     */         }
/*     */       } else {
/*     */         
/* 672 */         System.arraycopy(cspPasswordBytes, 0, buffer, pos, cspPasswordBytes.length);
/*     */       } 
/* 674 */       pos += cspPasswordBytes.length;
/*     */     } 
/* 676 */     if (this.version >= 2)
/*     */     {
/* 678 */       if (initKeyOffsetPosition > 0) {
/*     */         byte[] initKeyBytes;
/*     */         
/* 681 */         if (this.initialKey_cachedBytes != null) {
/* 682 */           initKeyBytes = this.initialKey_cachedBytes;
/* 683 */           this.initialKey_cachedBytes = null;
/*     */         } else {
/*     */           
/* 686 */           initKeyBytes = dc.getStrBytes(this.initialKey, cp);
/*     */         } 
/*     */         
/* 689 */         dc.writeI32(pos - offset, buffer, initKeyOffsetPosition, swap);
/* 690 */         dc.writeI32(initKeyBytes.length, buffer, initKeyLengthPosition, swap);
/*     */         
/* 692 */         if (obscure) {
/* 693 */           for (int i = 0; i < initKeyBytes.length; i++) {
/* 694 */             buffer[pos + i] = 120;
/*     */           }
/*     */         } else {
/*     */           
/* 698 */           System.arraycopy(initKeyBytes, 0, buffer, pos, initKeyBytes.length);
/*     */         } 
/* 700 */         pos += initKeyBytes.length;
/*     */       } 
/*     */     }
/*     */     
/* 704 */     if (Trace.isOn) {
/* 705 */       Trace.exit(this, "com.ibm.mq.exits.MQCSP", "writeToBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", 
/*     */           
/* 707 */           Integer.valueOf(pos));
/*     */     }
/* 709 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 717 */     if (Trace.isOn) {
/* 718 */       Trace.entry(this, "com.ibm.mq.exits.MQCSP", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 720 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 722 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 723 */     int pos = offset;
/*     */ 
/*     */     
/* 726 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 727 */     if (!strucId.equals("CSP ")) {
/*     */       
/* 729 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 6107, null);
/* 730 */       if (Trace.isOn) {
/* 731 */         Trace.throwing(this, "com.ibm.mq.exits.MQCSP", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 734 */       throw traceRet1;
/*     */     } 
/* 736 */     pos += 4;
/*     */ 
/*     */     
/* 739 */     this.version = dc.readI32(buffer, pos, swap);
/* 740 */     pos += 4;
/*     */ 
/*     */     
/* 743 */     this.authenticationType = dc.readI32(buffer, pos, swap);
/* 744 */     pos += 4;
/*     */ 
/*     */     
/* 747 */     pos += 4;
/*     */ 
/*     */     
/* 750 */     pos += ptrSize;
/*     */ 
/*     */     
/* 753 */     int cspUserIdOffset = dc.readI32(buffer, pos, swap);
/* 754 */     pos += 4;
/*     */ 
/*     */     
/* 757 */     int cspUserIdLength = dc.readI32(buffer, pos, swap);
/* 758 */     pos += 4;
/*     */ 
/*     */     
/* 761 */     pos += 8;
/*     */ 
/*     */     
/* 764 */     pos += ptrSize;
/*     */ 
/*     */     
/* 767 */     int cspPasswordOffset = dc.readI32(buffer, pos, swap);
/* 768 */     pos += 4;
/*     */ 
/*     */     
/* 771 */     int cspPasswordLength = dc.readI32(buffer, pos, swap);
/* 772 */     pos += 4;
/*     */ 
/*     */     
/* 775 */     if (this.version == 1) {
/* 776 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 777 */       pos += padding;
/*     */     } 
/*     */     
/* 780 */     int initialKeyOffset = -1;
/* 781 */     int initialKeyLength = -1;
/*     */     
/* 783 */     if (this.version >= 2) {
/*     */       
/* 785 */       pos += 8;
/*     */ 
/*     */       
/* 788 */       pos += ptrSize;
/*     */ 
/*     */       
/* 791 */       initialKeyOffset = dc.readI32(buffer, pos, swap);
/* 792 */       pos += 4;
/*     */ 
/*     */       
/* 795 */       initialKeyLength = dc.readI32(buffer, pos, swap);
/* 796 */       pos += 4;
/*     */ 
/*     */       
/* 799 */       if (this.version == 2) {
/* 800 */         int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 801 */         pos += padding;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 810 */     if (cspUserIdOffset > 0) {
/* 811 */       this.cspUserId = dc.readField(buffer, cspUserIdOffset, cspUserIdLength, cp, tls);
/*     */     } else {
/*     */       
/* 814 */       this.cspUserId = null;
/*     */     } 
/*     */ 
/*     */     
/* 818 */     if (cspPasswordOffset > 0) {
/* 819 */       this.cspPassword = dc.readField(buffer, cspPasswordOffset, cspPasswordLength, cp, tls);
/*     */     } else {
/*     */       
/* 822 */       this.cspPassword = null;
/*     */     } 
/*     */     
/* 825 */     if (this.version >= 2)
/*     */     {
/* 827 */       if (initialKeyOffset > 0) {
/* 828 */         this.initialKey = dc.readField(buffer, initialKeyOffset, initialKeyLength, cp, tls);
/*     */       } else {
/*     */         
/* 831 */         this.initialKey = null;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 836 */     int cspUserIdEnd = cspUserIdOffset + cspUserIdLength;
/* 837 */     int cspPasswordEnd = cspPasswordOffset + cspPasswordLength;
/* 838 */     int initialKeyEnd = initialKeyOffset + initialKeyLength;
/*     */     
/* 840 */     int traceRet2 = Math.max(pos, Math.max(cspUserIdEnd, Math.max(cspPasswordEnd, initialKeyEnd)));
/* 841 */     if (Trace.isOn) {
/* 842 */       Trace.exit(this, "com.ibm.mq.exits.MQCSP", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 843 */           Integer.valueOf(traceRet2));
/*     */     }
/*     */     
/* 846 */     return traceRet2;
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
/* 858 */     fmt.add("version", this.version);
/* 859 */     fmt.add("authenticationType", this.authenticationType);
/* 860 */     fmt.add("cspUserId", this.cspUserId);
/* 861 */     fmt.add("cspPassword", JmqiTools.tracePassword(this.cspPassword));
/* 862 */     fmt.add("initialKey", JmqiTools.tracePassword(this.initialKey));
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\exits\MQCSP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */