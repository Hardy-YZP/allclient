/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
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
/*     */ public class SpiPutOptions
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/SpiPutOptions.java";
/*     */   private static final String lpiPUTOPTS_STRUC_ID = "LPOP";
/*     */   public static final int lpiPUTOPTS_VERSION1 = 1;
/*     */   public static final int lpiPUTOPTS_VERSION2 = 2;
/*     */   public static final int lpiPUTOPTS_VERSION3 = 3;
/*     */   public static final int lpiPUTOPTS_VERSION4 = 4;
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_MSG_ID_RESERVATION = 4;
/*     */   private static final int SIZEOF_OBJECT_TYPE = 4;
/*     */   private static final int SIZEOF_OBJECT_QMGR_NAME = 48;
/*     */   private static final int SIZEOF_OBJECT_NAME = 48;
/*     */   private static final int SIZEOF_ORIG_QMGR_NAME = 48;
/*     */   private static final int SIZEOF_PID_QMGR = 48;
/*     */   private static final int SIZEOF_PID_QNAME = 48;
/*     */   private static final int SIZEOF_PID_CORRELED = 24;
/*     */   private static final int SIZEOF_DELIVERY_DELAY_HIGH = 4;
/*     */   private static final int SIZEOF_DELIVERY_DELAY_LOW = 4;
/*     */   private static final int SIZEOF_RESERVED = 4;
/*     */   public static final int lpiPUTOPTS_NONE = 0;
/*     */   public static final int lpiPUTOPTS_BLANK_PADDED = 1;
/*     */   public static final int lpiPUTOPTS_SYNCPOINT_IF_YOU_LIKE = 2;
/*     */   public static final int lpiPUTOPTS_DEFERRED = 4;
/*     */   public static final int lpiPUTOPTS_PUT_AND_FORGET = 8;
/*     */   public static final int lpiPUTOPTS_ASYNC = 32;
/*     */   public static final int lpiPUTOPTS_REMOTE_PUB_CLUSTER = 64;
/*     */   public static final int lpiPUTOPTS_REMOTE_PUB_HIERARCHY = 128;
/*     */   public static final int lpiPUTOPTS_SET_RETAINED = 256;
/*     */   public static final int lpiPUTOPTS_TRUSTED_PUBLISH = 4096;
/* 114 */   private int version = 1;
/* 115 */   private int options = 0;
/* 116 */   private int msgIdReservation = 0;
/* 117 */   private int objectType = 0;
/* 118 */   private String objectQMgrName = null;
/* 119 */   private String objectName = null;
/* 120 */   private String origQMgrName = null;
/* 121 */   private String pidQMgr = null;
/* 122 */   private String pidQName = null;
/* 123 */   private byte[] pidCorrelId = new byte[24];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   private long deliveryDelay = 0L;
/*     */ 
/*     */   
/*     */   private static Reference<Field[]> fieldsRef;
/*     */ 
/*     */ 
/*     */   
/*     */   public SpiPutOptions(JmqiEnvironment env) {
/* 138 */     super(env);
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "getVersion()", "getter", 
/* 156 */           Integer.valueOf(this.version));
/*     */     }
/* 158 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "setVersion(int)", "setter", 
/* 170 */           Integer.valueOf(version));
/*     */     }
/* 172 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "getOptions()", "getter", 
/* 181 */           Integer.valueOf(this.options));
/*     */     }
/* 183 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "setOptions(int)", "setter", 
/* 194 */           Integer.valueOf(options));
/*     */     }
/* 196 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMsgIdReservation() {
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "getMsgIdReservation()", "getter", 
/* 205 */           Integer.valueOf(this.msgIdReservation));
/*     */     }
/* 207 */     return this.msgIdReservation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMsgIdReservation(int msgIdReservation) {
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "setMsgIdReservation(int)", "setter", 
/* 216 */           Integer.valueOf(msgIdReservation));
/*     */     }
/* 218 */     this.msgIdReservation = msgIdReservation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getObjectName() {
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "getObjectName()", "getter", this.objectName);
/*     */     }
/*     */     
/* 229 */     return this.objectName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectName(String objectName) {
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "setObjectName(String)", "setter", objectName);
/*     */     }
/*     */     
/* 240 */     this.objectName = objectName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getObjectQMgrName() {
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "getObjectQMgrName()", "getter", this.objectQMgrName);
/*     */     }
/*     */     
/* 251 */     return this.objectQMgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectQMgrName(String objectQMgrName) {
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "setObjectQMgrName(String)", "setter", objectQMgrName);
/*     */     }
/*     */     
/* 262 */     this.objectQMgrName = objectQMgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getObjectType() {
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "getObjectType()", "getter", 
/* 271 */           Integer.valueOf(this.objectType));
/*     */     }
/* 273 */     return this.objectType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectType(int objectType) {
/* 280 */     if (Trace.isOn) {
/* 281 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "setObjectType(int)", "setter", 
/* 282 */           Integer.valueOf(objectType));
/*     */     }
/* 284 */     this.objectType = objectType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOrigQMgrName() {
/* 291 */     if (Trace.isOn) {
/* 292 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "getOrigQMgrName()", "getter", this.origQMgrName);
/*     */     }
/*     */     
/* 295 */     return this.origQMgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOrigQMgrName(String origQMgrName) {
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "setOrigQMgrName(String)", "setter", origQMgrName);
/*     */     }
/*     */     
/* 306 */     this.origQMgrName = origQMgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPidQMgr() {
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "getPidQMgr()", "getter", this.pidQMgr);
/*     */     }
/* 316 */     return this.pidQMgr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPidQMgr(String pidQMgr) {
/* 323 */     if (Trace.isOn) {
/* 324 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "setPidQMgr(String)", "setter", pidQMgr);
/*     */     }
/*     */     
/* 327 */     this.pidQMgr = pidQMgr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPidQName() {
/* 334 */     if (Trace.isOn) {
/* 335 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "getPidQName()", "getter", this.pidQName);
/*     */     }
/*     */     
/* 338 */     return this.pidQName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPidQName(String pidQName) {
/* 345 */     if (Trace.isOn) {
/* 346 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "setPidQName(String)", "setter", pidQName);
/*     */     }
/*     */     
/* 349 */     this.pidQName = pidQName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getPidCorrelId() {
/* 356 */     if (Trace.isOn) {
/* 357 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "getPidCorrelId()", "getter", this.pidCorrelId);
/*     */     }
/*     */     
/* 360 */     return this.pidCorrelId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPidCorrelId(byte[] pidCorrelId) {
/* 367 */     if (Trace.isOn) {
/* 368 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "setPidCorrelId(byte [ ])", "setter", pidCorrelId);
/*     */     }
/*     */     
/* 371 */     this.pidCorrelId = pidCorrelId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getDeliveryDelay() {
/* 378 */     if (Trace.isOn) {
/* 379 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "getDeliveryDelay()", "getter", 
/* 380 */           Long.valueOf(this.deliveryDelay));
/*     */     }
/* 382 */     return this.deliveryDelay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDeliveryDelay(long deliveryDelay) {
/* 389 */     if (Trace.isOn) {
/* 390 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "setDeliveryDelay(long)", "setter", 
/* 391 */           Long.valueOf(deliveryDelay));
/*     */     }
/* 393 */     this.deliveryDelay = deliveryDelay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 401 */     if (Trace.isOn)
/* 402 */       Trace.entry("com.ibm.mq.jmqi.system.SpiPutOptions", "getSizeV1(int)", new Object[] {
/* 403 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 405 */     int size = 12;
/* 406 */     if (Trace.isOn) {
/* 407 */       Trace.exit("com.ibm.mq.jmqi.system.SpiPutOptions", "getSizeV1(int)", Integer.valueOf(size));
/*     */     }
/* 409 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV2(int ptrSize) {
/* 417 */     if (Trace.isOn)
/* 418 */       Trace.entry("com.ibm.mq.jmqi.system.SpiPutOptions", "getSizeV2(int)", new Object[] {
/* 419 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 421 */     int size = getSizeV1(ptrSize) + 4;
/* 422 */     if (Trace.isOn) {
/* 423 */       Trace.exit("com.ibm.mq.jmqi.system.SpiPutOptions", "getSizeV2(int)", Integer.valueOf(size));
/*     */     }
/* 425 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV3(int ptrSize) {
/* 433 */     if (Trace.isOn)
/* 434 */       Trace.entry("com.ibm.mq.jmqi.system.SpiPutOptions", "getSizeV3(int)", new Object[] {
/* 435 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 437 */     int size = getSizeV2(ptrSize) + 4 + 48 + 48 + 48 + 48 + 48 + 24;
/*     */ 
/*     */     
/* 440 */     if (Trace.isOn) {
/* 441 */       Trace.exit("com.ibm.mq.jmqi.system.SpiPutOptions", "getSizeV3(int)", 
/* 442 */           Integer.valueOf(size));
/*     */     }
/* 444 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV4(int ptrSize) {
/* 452 */     if (Trace.isOn)
/* 453 */       Trace.entry("com.ibm.mq.jmqi.system.SpiPutOptions", "getSizeV4(int)", new Object[] {
/* 454 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 456 */     int size = getSizeV3(ptrSize) + 4 + 4 + 4;
/* 457 */     if (Trace.isOn) {
/* 458 */       Trace.exit("com.ibm.mq.jmqi.system.SpiPutOptions", "getSizeV4(int)", Integer.valueOf(size));
/*     */     }
/* 460 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/*     */     int size;
/*     */     JmqiException e;
/* 471 */     if (Trace.isOn) {
/* 472 */       Trace.entry("com.ibm.mq.jmqi.system.SpiPutOptions", "getSize(JmqiEnvironment,int,int)", new Object[] { env, 
/* 473 */             Integer.valueOf(version), Integer.valueOf(ptrSize) });
/*     */     }
/*     */     
/* 476 */     switch (version) {
/*     */       case 1:
/* 478 */         size = getSizeV1(ptrSize);
/*     */         break;
/*     */       case 2:
/* 481 */         size = getSizeV2(ptrSize);
/*     */         break;
/*     */       case 3:
/* 484 */         size = getSizeV3(ptrSize);
/*     */         break;
/*     */       case 4:
/* 487 */         size = getSizeV4(ptrSize);
/*     */         break;
/*     */       
/*     */       default:
/* 491 */         e = new JmqiException(env, -1, null, 2, 2195, null);
/*     */ 
/*     */         
/* 494 */         if (Trace.isOn) {
/* 495 */           Trace.throwing("com.ibm.mq.jmqi.system.SpiPutOptions", "getSize(JmqiEnvironment,int,int)", (Throwable)e);
/*     */         }
/*     */         
/* 498 */         throw e;
/*     */     } 
/* 500 */     if (Trace.isOn) {
/* 501 */       Trace.exit("com.ibm.mq.jmqi.system.SpiPutOptions", "getSize(JmqiEnvironment,int,int)", 
/* 502 */           Integer.valueOf(size));
/*     */     }
/* 504 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFirstVersion() {
/* 512 */     if (Trace.isOn) {
/* 513 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "getFirstVersion()", "getter", 
/* 514 */           Integer.valueOf(1));
/*     */     }
/* 516 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrentVersion() {
/* 524 */     if (Trace.isOn) {
/* 525 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "getCurrentVersion()", "getter", 
/* 526 */           Integer.valueOf(4));
/*     */     }
/* 528 */     return 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 539 */     if (Trace.isOn)
/* 540 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 541 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 543 */     int size = getSize(this.env, this.version, ptrSize);
/*     */     
/* 545 */     if (Trace.isOn) {
/* 546 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 547 */           Integer.valueOf(size));
/*     */     }
/* 549 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 558 */     if (Trace.isOn) {
/* 559 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 561 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 563 */     int pos = offset;
/* 564 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 566 */     dc.writeMQField("LPOP", buffer, pos, 4, cp, tls);
/* 567 */     pos += 4;
/*     */     
/* 569 */     dc.writeI32(this.version, buffer, pos, swap);
/* 570 */     pos += 4;
/*     */     
/* 572 */     dc.writeI32(this.options, buffer, pos, swap);
/* 573 */     pos += 4;
/*     */     
/* 575 */     if (this.version >= 2) {
/*     */       
/* 577 */       dc.writeI32(this.msgIdReservation, buffer, pos, swap);
/* 578 */       pos += 4;
/*     */     } 
/*     */     
/* 581 */     if (this.version >= 3) {
/*     */       
/* 583 */       dc.writeI32(this.objectType, buffer, pos, swap);
/* 584 */       pos += 4;
/*     */       
/* 586 */       dc.writeMQField(this.objectQMgrName, buffer, pos, 48, cp, tls);
/* 587 */       pos += 48;
/*     */       
/* 589 */       dc.writeMQField(this.objectName, buffer, pos, 48, cp, tls);
/* 590 */       pos += 48;
/*     */       
/* 592 */       dc.writeMQField(this.origQMgrName, buffer, pos, 48, cp, tls);
/* 593 */       pos += 48;
/*     */       
/* 595 */       dc.writeMQField(this.pidQMgr, buffer, pos, 48, cp, tls);
/* 596 */       pos += 48;
/*     */       
/* 598 */       dc.writeMQField(this.pidQName, buffer, pos, 48, cp, tls);
/* 599 */       pos += 48;
/*     */       
/* 601 */       System.arraycopy(this.pidCorrelId, 0, buffer, pos, 24);
/* 602 */       pos += 24;
/*     */     } 
/*     */     
/* 605 */     if (this.version >= 4) {
/*     */ 
/*     */       
/* 608 */       dc.writeI32((int)(this.deliveryDelay >> 32L), buffer, pos, swap);
/* 609 */       pos += 4;
/* 610 */       dc.writeI32((int)(this.deliveryDelay & 0xFFFFFFFFFFFFFFFFL), buffer, pos, swap);
/* 611 */       pos += 4;
/*     */       
/* 613 */       dc.writeI32(0, buffer, pos, swap);
/* 614 */       pos += 4;
/*     */     } 
/*     */ 
/*     */     
/* 618 */     if (Trace.isOn) {
/* 619 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 620 */           Integer.valueOf(pos));
/*     */     }
/* 622 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 631 */     if (Trace.isOn) {
/* 632 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 634 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 636 */     int pos = offset;
/* 637 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 639 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 640 */     if (!strucId.equals("LPOP")) {
/* 641 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 6107, null);
/*     */       
/* 643 */       if (Trace.isOn) {
/* 644 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 647 */       throw traceRet1;
/*     */     } 
/* 649 */     pos += 4;
/*     */ 
/*     */     
/* 652 */     this.version = dc.readI32(buffer, pos, swap);
/* 653 */     pos += 4;
/*     */ 
/*     */     
/* 656 */     this.options = dc.readI32(buffer, pos, swap);
/* 657 */     pos += 4;
/*     */ 
/*     */     
/* 660 */     if (this.version >= 2) {
/*     */       
/* 662 */       this.msgIdReservation = dc.readI32(buffer, pos, swap);
/* 663 */       pos += 4;
/*     */     } 
/*     */ 
/*     */     
/* 667 */     if (this.version >= 3) {
/*     */       
/* 669 */       this.objectType = dc.readI32(buffer, pos, swap);
/* 670 */       pos += 4;
/*     */ 
/*     */       
/* 673 */       this.objectQMgrName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 674 */       pos += 48;
/*     */ 
/*     */       
/* 677 */       this.objectName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 678 */       pos += 48;
/*     */ 
/*     */       
/* 681 */       this.origQMgrName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 682 */       pos += 48;
/*     */ 
/*     */       
/* 685 */       this.pidQMgr = dc.readMQField(buffer, pos, 48, cp, tls);
/* 686 */       pos += 48;
/*     */ 
/*     */       
/* 689 */       this.pidQName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 690 */       pos += 48;
/*     */ 
/*     */       
/* 693 */       System.arraycopy(buffer, pos, this.pidCorrelId, 0, 24);
/* 694 */       pos += 24;
/*     */     } 
/*     */ 
/*     */     
/* 698 */     if (this.version >= 4) {
/*     */ 
/*     */ 
/*     */       
/* 702 */       this.deliveryDelay = dc.readI32(buffer, pos, swap);
/* 703 */       pos += 4;
/* 704 */       this.deliveryDelay <<= 32L;
/* 705 */       this.deliveryDelay |= dc.readI32(buffer, pos, swap);
/* 706 */       pos += 4;
/*     */       
/* 708 */       pos += 4;
/*     */     } 
/*     */     
/* 711 */     if (Trace.isOn) {
/* 712 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiPutOptions", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 713 */           Integer.valueOf(pos));
/*     */     }
/* 715 */     return pos;
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
/* 727 */     fmt.add("version", this.version);
/* 728 */     fmt.add("options", this.options);
/* 729 */     String optionDescription = formatOptions(this.options, getFields(), "lpiPUTOPTS_");
/* 730 */     fmt.add("option flags", optionDescription);
/* 731 */     fmt.add("msgIdReservation", this.msgIdReservation);
/* 732 */     fmt.add("objectType", this.objectType);
/* 733 */     fmt.add("objectQMgrName", this.objectQMgrName);
/* 734 */     fmt.add("objectName", this.objectName);
/* 735 */     fmt.add("pidQMgr", this.pidQMgr);
/* 736 */     fmt.add("pidQName", this.pidQName);
/* 737 */     fmt.add("pidCorrelId", this.pidCorrelId);
/* 738 */     fmt.add("deliveryDelay", this.deliveryDelay);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static synchronized Field[] getFields() {
/* 748 */     Reference<Field[]> ref = fieldsRef;
/*     */     
/*     */     Field[] fields;
/* 751 */     if (ref == null || (fields = ref.get()) == null) {
/* 752 */       fieldsRef = (Reference)new SoftReference<>(fields = SpiPutOptions.class.getFields());
/*     */     }
/*     */     
/* 755 */     if (Trace.isOn) {
/* 756 */       Trace.data("com.ibm.mq.jmqi.system.SpiPutOptions", "getFields()", "getter", fields);
/*     */     }
/* 758 */     return fields;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\SpiPutOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */