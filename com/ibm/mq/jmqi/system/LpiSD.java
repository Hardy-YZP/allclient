/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.MQCHARV;
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.lang.reflect.Field;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LpiSD
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/LpiSD.java";
/*     */   private static final String lpiSD_STRUCID = "LSD ";
/*     */   public static final int lpiSD_VERSION_1 = 1;
/*     */   public static final int lpiSD_VERSION_2 = 2;
/*     */   public static final int lpiSD_CURRENT_VERSION = 2;
/*     */   public static final int lpiSDO_NONE = 0;
/*     */   public static final int lpiSDO_TEMPLATE_SUBSCRIPTION = 2;
/*     */   public static final int lpiSDO_DEFAULT_SUBSCRIPTION = 4;
/*     */   public static final int lpiSDO_JOIN_EXCLUSIVE = 8;
/*     */   public static final int lpiSDO_JOIN_SHARED = 16;
/*     */   public static final int lpiSDO_MULTICAST_SUPPORTED = 32;
/*     */   public static final int lpiSDO_FIXED_JOIN = 64;
/*     */   public static final int lpiSDO_V6_SUB = 256;
/*     */   public static final int lpiSDO_INCLUDE_STREAM_NAME = 512;
/*     */   public static final int lpiSDO_INFORM_IF_RETAINED = 1024;
/*     */   public static final int lpiSDO_TRADITIONAL_IDENTITY = 2048;
/*     */   public static final int lpiSDO_NON_PERSISTENT = 4096;
/*     */   public static final int lpiSDO_PERSISTENT = 8192;
/*     */   public static final int lpiSDO_PERSISTENCE_AS_Q_DEF = 16384;
/*     */   public static final int lpiSDO_APPLICATION_VALIDATION = 32768;
/*     */   public static final int lpiSDO_IGNORE_HOBJS = 65536;
/*     */   public static final int lpiSDO_OPEN_DESTINATION = 131072;
/*     */   public static final int lpiSDO_NO_AUTH = 524288;
/*     */   public static final int lpiSDO_MIGRATED_SUB = 1048576;
/*     */   public static final int lpiSDO_MQDISC_SUB_EXPIRY = 2097152;
/*     */   public static final int lpiSDO_PROXYTYPE_CLUSTER = 16777216;
/*     */   public static final int lpiSDO_PROXYTYPE_HIERARCHY = 33554432;
/*     */   public static final int lpiSDO_SYNCPOINT = 67108864;
/*     */   public static final int lpiSDO_NONDURABLE_IF_TEMPDYN = 134217728;
/*     */   public static final int lpiSDO_UNSUB_IF_DEST_DELETED = 268435456;
/*     */   public static final int lpiSDO_NO_DEST_VALIDATION = 536870912;
/*     */   public static final int lpiSDO_SELECTION_STRING_SPECIFIED = 1073741824;
/*     */   public static final int lpiRC_SUB_JOIN_NOT_ALTERABLE = 29440;
/*     */   public static final int lpiSDO_MIGRATED_MQV6 = 1048576;
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_SUBID = 24;
/*     */   private static final int SIZEOF_DEST_READ_AHEAD = 4;
/*     */   private static final int SIZEOF_DESTOPENOPTIONS = 4;
/*     */   private static final int SIZEOF_MULTICAST = 4;
/* 144 */   private int version = 1;
/* 145 */   private int options = 0;
/*     */   private MQCHARV subIdentity;
/* 147 */   private byte[] subId = new byte[24];
/* 148 */   private int destReadAhead = 0;
/* 149 */   private int destOpenOptions = 0;
/*     */   private LpiSDSubProps subProps;
/* 151 */   private int multicast = 0;
/*     */ 
/*     */   
/*     */   private LpiCOMMINFO_ATTR_DATA commInfoAttrData;
/*     */   
/*     */   private static Reference<Field[]> fieldsRef;
/*     */ 
/*     */   
/*     */   public LpiSD(JmqiEnvironment env) {
/* 160 */     super(env);
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiSD", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/* 165 */     JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)env;
/* 166 */     this.subIdentity = env.newMQCHARV();
/* 167 */     this.subProps = sysenv.newLpiSDSubProps();
/* 168 */     this.commInfoAttrData = new LpiCOMMINFO_ATTR_DATA(env);
/*     */     
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiSD", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSD", "getVersion()", "getter", 
/* 183 */           Integer.valueOf(this.version));
/*     */     }
/* 185 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSD", "setVersion(int)", "setter", 
/* 197 */           Integer.valueOf(version));
/*     */     }
/* 199 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSD", "getOptions()", "getter", 
/* 208 */           Integer.valueOf(this.options));
/*     */     }
/* 210 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSD", "setOptions(int)", "setter", 
/* 221 */           Integer.valueOf(options));
/*     */     }
/* 223 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDestReadAhead() {
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSD", "getDestReadAhead()", "getter", 
/* 232 */           Integer.valueOf(this.destReadAhead));
/*     */     }
/* 234 */     return this.destReadAhead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDestReadAhead(int destReadAhead) {
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSD", "setDestReadAhead(int)", "setter", 
/* 245 */           Integer.valueOf(destReadAhead));
/*     */     }
/* 247 */     this.destReadAhead = destReadAhead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getSubId() {
/* 254 */     if (Trace.isOn) {
/* 255 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSD", "getSubId()", "getter", this.subId);
/*     */     }
/* 257 */     return this.subId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubId(byte[] subId) {
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.data(this, "setSubId(byte [ ])", subId);
/*     */     }
/* 271 */     System.arraycopy(subId, 0, this.subId, 0, 24);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCHARV getSubIdentity() {
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSD", "getSubIdentity()", "getter", this.subIdentity);
/*     */     }
/* 281 */     return this.subIdentity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDestOpenOptions() {
/* 288 */     if (Trace.isOn) {
/* 289 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSD", "getDestOpenOptions()", "getter", 
/* 290 */           Integer.valueOf(this.destOpenOptions));
/*     */     }
/* 292 */     return this.destOpenOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDestOpenOptions(int destOpenOptions) {
/* 299 */     if (Trace.isOn) {
/* 300 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSD", "setDestOpenOptions(int)", "setter", 
/* 301 */           Integer.valueOf(destOpenOptions));
/*     */     }
/* 303 */     this.destOpenOptions = destOpenOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LpiSDSubProps getSubProps() {
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiSD", "getSubProps()", "getter", this.subProps);
/*     */     }
/* 313 */     return this.subProps;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getCurrentSize(int ptrSize) {
/* 321 */     if (Trace.isOn)
/* 322 */       Trace.entry("com.ibm.mq.jmqi.system.LpiSD", "getCurrentSize(int)", new Object[] {
/* 323 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 325 */     int traceRet1 = getSizeV1(ptrSize);
/*     */     
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.exit("com.ibm.mq.jmqi.system.LpiSD", "getCurrentSize(int)", Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 331 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV1(int ptrSize) {
/* 338 */     if (Trace.isOn)
/* 339 */       Trace.entry("com.ibm.mq.jmqi.system.LpiSD", "getFieldSizeV1(int)", new Object[] {
/* 340 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 342 */     int size = 0;
/*     */     
/* 344 */     size += 4;
/* 345 */     size += 4;
/* 346 */     size += 4;
/* 347 */     size += JmqiTools.alignToGrain(ptrSize, size);
/* 348 */     size += MQCHARV.getSize(ptrSize);
/* 349 */     size += 24;
/* 350 */     size += 4;
/* 351 */     size += 4;
/* 352 */     size += LpiSDSubProps.getSizeV1(ptrSize);
/*     */     
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.exit("com.ibm.mq.jmqi.system.LpiSD", "getFieldSizeV1(int)", Integer.valueOf(size));
/*     */     }
/* 357 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 365 */     if (Trace.isOn)
/* 366 */       Trace.entry("com.ibm.mq.jmqi.system.LpiSD", "getSizeV1(int)", new Object[] {
/* 367 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 369 */     int size = 0;
/*     */     
/* 371 */     size += getFieldSizeV1(ptrSize);
/*     */ 
/*     */     
/* 374 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*     */     
/* 376 */     if (Trace.isOn) {
/* 377 */       Trace.exit("com.ibm.mq.jmqi.system.LpiSD", "getSizeV1(int)", Integer.valueOf(size));
/*     */     }
/* 379 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV2(int ptrSize) {
/* 386 */     if (Trace.isOn)
/* 387 */       Trace.entry("com.ibm.mq.jmqi.system.LpiSD", "getFieldSizeV2(int)", new Object[] {
/* 388 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 390 */     int size = 0;
/*     */     
/* 392 */     size += getFieldSizeV1(ptrSize);
/* 393 */     size += 4;
/* 394 */     size += LpiCOMMINFO_ATTR_DATA.getStructureSize(ptrSize);
/*     */     
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.exit("com.ibm.mq.jmqi.system.LpiSD", "getFieldSizeV2(int)", Integer.valueOf(size));
/*     */     }
/* 399 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV2(int ptrSize) {
/* 407 */     if (Trace.isOn)
/* 408 */       Trace.entry("com.ibm.mq.jmqi.system.LpiSD", "getSizeV2(int)", new Object[] {
/* 409 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 411 */     int size = 0;
/*     */     
/* 413 */     size += getFieldSizeV2(ptrSize);
/*     */ 
/*     */ 
/*     */     
/* 417 */     if (Trace.isOn) {
/* 418 */       Trace.exit("com.ibm.mq.jmqi.system.LpiSD", "getSizeV2(int)", Integer.valueOf(size));
/*     */     }
/* 420 */     return size;
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
/*     */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/* 433 */     int size = 0;
/* 434 */     switch (version) {
/*     */       case 1:
/* 436 */         size = getSizeV1(ptrSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 446 */         return size;case 2: size = getSizeV2(ptrSize); return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 2195, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 457 */     if (Trace.isOn) {
/* 458 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiSD", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 459 */             Integer.valueOf(ptrSize), cp
/*     */           });
/*     */     }
/* 462 */     int size = getSize(this.env, this.version, ptrSize);
/*     */     
/* 464 */     size += this.subIdentity.getRequiredBufferSize(ptrSize, cp);
/*     */     
/* 466 */     if (Trace.isOn) {
/* 467 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiSD", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 468 */           Integer.valueOf(size));
/*     */     }
/* 470 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 478 */     if (Trace.isOn) {
/* 479 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiSD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 481 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 483 */     int traceRet1 = writeToBuffer(buffer, offset, ptrSize, swap, cp, tls, false);
/* 484 */     if (Trace.isOn) {
/* 485 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiSD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 486 */           Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 489 */     return traceRet1;
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
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls, boolean bindingsMode) throws JmqiException {
/* 505 */     if (Trace.isOn) {
/* 506 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiSD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls,boolean)", new Object[] { buffer, 
/*     */             
/* 508 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls, 
/* 509 */             Boolean.valueOf(bindingsMode) });
/*     */     }
/* 511 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 512 */     int pos = offset;
/* 513 */     int subIdentityPos = -1;
/*     */     
/* 515 */     dc.writeMQField("LSD ", buffer, pos, 4, cp, tls);
/* 516 */     pos += 4;
/*     */     
/* 518 */     dc.writeI32(this.version, buffer, pos, swap);
/* 519 */     pos += 4;
/*     */     
/* 521 */     dc.writeI32(this.options, buffer, pos, swap);
/* 522 */     pos += 4;
/*     */     
/* 524 */     int padding = 0;
/* 525 */     padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 526 */     dc.clear(buffer, pos, padding);
/* 527 */     pos += padding;
/*     */     
/* 529 */     subIdentityPos = pos;
/* 530 */     pos += MQCHARV.getSize(ptrSize);
/*     */     
/* 532 */     System.arraycopy(this.subId, 0, buffer, pos, 24);
/* 533 */     pos += 24;
/*     */     
/* 535 */     dc.writeI32(this.destReadAhead, buffer, pos, swap);
/* 536 */     pos += 4;
/*     */     
/* 538 */     dc.writeI32(this.destOpenOptions, buffer, pos, swap);
/* 539 */     pos += 4;
/*     */     
/* 541 */     pos = this.subProps.writeToBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*     */     
/* 543 */     if (this.version == 1) {
/* 544 */       padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 545 */       dc.clear(buffer, pos, padding);
/* 546 */       pos += padding;
/*     */     } 
/* 548 */     if (this.version >= 2) {
/*     */       
/* 550 */       dc.writeI32(this.multicast, buffer, pos, swap);
/* 551 */       pos += 4;
/*     */       
/* 553 */       pos += this.commInfoAttrData.writeToBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 560 */     if (subIdentityPos > 0) {
/* 561 */       pos = this.subIdentity.writeToBuffer(buffer, offset, subIdentityPos, pos, ptrSize, swap, cp);
/*     */     }
/*     */     
/* 564 */     if (Trace.isOn) {
/* 565 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiSD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls,boolean)", 
/*     */           
/* 567 */           Integer.valueOf(pos));
/*     */     }
/* 569 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 577 */     if (Trace.isOn) {
/* 578 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiSD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 580 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 582 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 583 */     int pos = offset;
/* 584 */     int variableDataEnd = -1;
/*     */     
/* 586 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 587 */     if (!strucId.equals("LSD ")) {
/* 588 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 6107, null);
/* 589 */       if (Trace.isOn) {
/* 590 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.LpiSD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 593 */       throw traceRet1;
/*     */     } 
/* 595 */     pos += 4;
/*     */ 
/*     */     
/* 598 */     this.version = dc.readI32(buffer, pos, swap);
/* 599 */     pos += 4;
/*     */ 
/*     */     
/* 602 */     this.options = dc.readI32(buffer, pos, swap);
/* 603 */     pos += 4;
/*     */ 
/*     */     
/* 606 */     pos += JmqiTools.alignToGrain(ptrSize, pos);
/*     */ 
/*     */     
/* 609 */     pos = this.subIdentity.readFromBuffer(buffer, offset, pos, ptrSize, swap, tls);
/* 610 */     int siEnd = this.subIdentity.getEndPosAligned(offset);
/* 611 */     if (siEnd > variableDataEnd) {
/* 612 */       variableDataEnd = siEnd;
/*     */     }
/*     */ 
/*     */     
/* 616 */     System.arraycopy(buffer, pos, this.subId, 0, 24);
/* 617 */     pos += 24;
/*     */ 
/*     */     
/* 620 */     this.destReadAhead = dc.readI32(buffer, pos, swap);
/* 621 */     pos += 4;
/*     */ 
/*     */     
/* 624 */     this.destOpenOptions = dc.readI32(buffer, pos, swap);
/* 625 */     pos += 4;
/*     */ 
/*     */     
/* 628 */     pos = this.subProps.readFromBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*     */ 
/*     */     
/* 631 */     if (this.version == 1) {
/* 632 */       pos += JmqiTools.alignToGrain(ptrSize, pos);
/*     */     }
/*     */     
/* 635 */     if (this.version >= 2) {
/*     */       
/* 637 */       this.multicast = dc.readI32(buffer, pos, swap);
/* 638 */       pos += 4;
/*     */ 
/*     */       
/* 641 */       pos += this.commInfoAttrData.readFromBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 648 */     if (variableDataEnd > pos) {
/* 649 */       pos = variableDataEnd;
/*     */     }
/*     */     
/* 652 */     if (Trace.isOn) {
/* 653 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiSD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 654 */           Integer.valueOf(pos));
/*     */     }
/* 656 */     return pos;
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
/* 668 */     fmt.add("version", this.version);
/* 669 */     fmt.add("options", this.options);
/* 670 */     String optionDescription = formatOptions(this.options, getFields(), "lpiSDO_");
/* 671 */     fmt.add("option flags", optionDescription);
/* 672 */     fmt.add("subIdentity", this.subIdentity);
/* 673 */     fmt.add("subId", this.subId);
/* 674 */     fmt.add("destReadAhead", this.destReadAhead);
/* 675 */     fmt.add("destOpenOptions", this.destOpenOptions);
/* 676 */     fmt.add("subProps", this.subProps);
/* 677 */     fmt.add("multicast", this.multicast);
/* 678 */     fmt.add("commInfoAttrData", this.commInfoAttrData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static synchronized Field[] getFields() {
/* 688 */     Reference<Field[]> ref = fieldsRef;
/*     */     
/*     */     Field[] fields;
/* 691 */     if (ref == null || (fields = ref.get()) == null) {
/* 692 */       fieldsRef = (Reference)new SoftReference<>(fields = LpiSD.class.getFields());
/*     */     }
/*     */     
/* 695 */     if (Trace.isOn) {
/* 696 */       Trace.data("com.ibm.mq.jmqi.system.LpiSD", "getFields()", "getter", fields);
/*     */     }
/* 698 */     return fields;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\LpiSD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */