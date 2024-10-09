/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SpiGetOptions
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/SpiGetOptions.java";
/*     */   private static final String lpiGETOPT_STRUC_ID = "LGO ";
/*     */   public static final int lpiGETOPT_VERSION_2 = 2;
/*     */   public static final int lpiGETOPT_VERSION_3 = 3;
/*     */   public static final int lpiGETOPT_VERSION_4 = 4;
/*     */   public static final int lpiGETOPT_VERSION_5 = 5;
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_QUEUE_EMPTY = 4;
/*     */   private static final int SIZEOF_QTIME_DISTRIBUTED = 8;
/*     */   private static final int SIZEOF_QTIME_ZOS = 4;
/*     */   private static final int SIZEOF_PADDING1 = 4;
/*     */   private static final int SIZEOF_INHERITED = 4;
/*     */   private static final int SIZEOF_PUBLISH_FLAGS = 2;
/*     */   private static final int SIZEOF_PROP_FLAGS = 1;
/*     */   private static final int SIZEOF_ASRT_FLAGS = 1;
/*     */   private static final int SIZEOF_NEW_MSG_BUFFER_LEN = 4;
/*     */   private static final int SIZEOF_MAX_MSG_BUFFER_LEN = 4;
/*     */   private static final int SIZEOF_QM_NAME_HINT = 48;
/*     */   private static final int SIZEOF_QM_ID_HINT = 48;
/*     */   public static final int lpiGETOPT_INHERIT = 1;
/*     */   public static final int lpiGETOPT_COMMIT = 2;
/*     */   public static final int lpiGETOPT_COMMIT_IF_YOU_LIKE = 4;
/*     */   public static final int lpiGETOPT_COMMIT_ASYNC = 8;
/*     */   public static final int lpiGETOPT_SHORT_TXN = 16;
/*     */   public static final int lpiGETOPT_REPEATING_GET = 32;
/*     */   public static final int lpiGETOPT_ASYNC_CONSUME = 64;
/*     */   public static final int lpiGETOPT_FULL_MESSAGE = 128;
/*     */   public static final int lpiGETOPT_QTIME = 256;
/*     */   public static final int lpiGETOPT_RESET_QTIME = 512;
/*     */   public static final int lpiGETOPT_CANCEL_WAIT = 1024;
/*     */   public static final int lpiGETOPT_RESIZE_BUF = 2048;
/*     */   public static final int lpiGETOPT_ALLOW_BROWSE_INSTEAD = 262144;
/*     */   public static final int lpiPROPFLAGS_NONE = 0;
/*     */   public static final int lpiPROPFLAGS_INCS_NON_OPTIONAL = 16;
/*     */   public static final int lpiASRTFLAGS_NONE = 0;
/*     */   public static final int lpiASRTFLAGS_MSG_MOVED_TO_AMS_ERR_Q = 1;
/* 164 */   private int version = 2;
/* 165 */   private int options = 0;
/* 166 */   private int queueEmpty = 0;
/* 167 */   private long qTime_distributed = 0L;
/* 168 */   private int qTime_zos = 0;
/* 169 */   private int inherited = 0;
/* 170 */   private int publishFlags = 0;
/* 171 */   private int propFlags = 0;
/* 172 */   private int asrtFlags = 0;
/*     */ 
/*     */   
/*     */   private boolean isZOS = false;
/*     */ 
/*     */   
/*     */   private static Reference<Field[]> fieldsRef;
/*     */ 
/*     */   
/*     */   public SpiGetOptions(JmqiEnvironment env) {
/* 182 */     super(env);
/* 183 */     if (Trace.isOn) {
/* 184 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isZOS() {
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "isZOS()", "getter", 
/* 199 */           Boolean.valueOf(this.isZOS));
/*     */     }
/* 201 */     return this.isZOS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setZOS(boolean isZOS) {
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "setZOS(boolean)", "setter", 
/* 210 */           Boolean.valueOf(isZOS));
/*     */     }
/* 212 */     this.isZOS = isZOS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "getVersion()", "getter", 
/* 222 */           Integer.valueOf(this.version));
/*     */     }
/* 224 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "setVersion(int)", "setter", 
/* 236 */           Integer.valueOf(version));
/*     */     }
/* 238 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "getOptions()", "getter", 
/* 248 */           Integer.valueOf(this.options));
/*     */     }
/* 250 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "setOptions(int)", "setter", 
/* 262 */           Integer.valueOf(options));
/*     */     }
/* 264 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInherited() {
/* 272 */     if (Trace.isOn) {
/* 273 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "getInherited()", "getter", 
/* 274 */           Integer.valueOf(this.inherited));
/*     */     }
/* 276 */     return this.inherited;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInherited(int inherited) {
/* 284 */     if (Trace.isOn) {
/* 285 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "setInherited(int)", "setter", 
/* 286 */           Integer.valueOf(inherited));
/*     */     }
/* 288 */     this.inherited = inherited;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getQTime_distributed() {
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "getQTime_distributed()", "getter", 
/* 298 */           Long.valueOf(this.qTime_distributed));
/*     */     }
/* 300 */     return this.qTime_distributed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQTime_distributed(long time) {
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "setQTime_distributed(long)", "setter", 
/* 309 */           Long.valueOf(time));
/*     */     }
/* 311 */     this.qTime_distributed = time;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getQTime_zos() {
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "getQTime_zos()", "getter", 
/* 320 */           Long.valueOf(this.qTime_zos));
/*     */     }
/* 322 */     return this.qTime_zos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQTime_zos(int time) {
/* 329 */     if (Trace.isOn) {
/* 330 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "setQTime_zos(int)", "setter", 
/* 331 */           Integer.valueOf(time));
/*     */     }
/* 333 */     this.qTime_zos = time;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getQueueEmpty() {
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "getQueueEmpty()", "getter", 
/* 342 */           Integer.valueOf(this.queueEmpty));
/*     */     }
/* 344 */     return this.queueEmpty;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQueueEmpty(int queueEmpty) {
/* 352 */     if (Trace.isOn) {
/* 353 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "setQueueEmpty(int)", "setter", 
/* 354 */           Integer.valueOf(queueEmpty));
/*     */     }
/* 356 */     this.queueEmpty = queueEmpty;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPublishFlags() {
/* 364 */     if (Trace.isOn) {
/* 365 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "getPublishFlags()", "getter", 
/* 366 */           Integer.valueOf(this.publishFlags));
/*     */     }
/* 368 */     return this.publishFlags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPublishFlags(int publishFlags) {
/* 375 */     if (Trace.isOn) {
/* 376 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "setPublishFlags(int)", "setter", 
/* 377 */           Integer.valueOf(publishFlags));
/*     */     }
/* 379 */     this.publishFlags = publishFlags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropFlags() {
/* 386 */     if (Trace.isOn) {
/* 387 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "getPropFlags()", "getter", 
/* 388 */           Integer.valueOf(this.propFlags));
/*     */     }
/* 390 */     return this.propFlags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPropFlags(int propFlags) {
/* 397 */     if (Trace.isOn) {
/* 398 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "setPropFlags(int)", "setter", 
/* 399 */           Integer.valueOf(propFlags));
/*     */     }
/* 401 */     this.propFlags = propFlags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAsrtFlags() {
/* 408 */     if (Trace.isOn) {
/* 409 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "getAsrtFlags()", "getter", 
/* 410 */           Integer.valueOf(this.asrtFlags));
/*     */     }
/* 412 */     return this.asrtFlags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAsrtFlags(int asrtFlags) {
/* 419 */     if (Trace.isOn) {
/* 420 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "setAsrtFlags(int)", "setter", 
/* 421 */           Integer.valueOf(asrtFlags));
/*     */     }
/* 423 */     this.propFlags = asrtFlags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV2(int ptrSize, boolean isZOS) {
/* 432 */     int size = 16;
/* 433 */     if (isZOS) {
/* 434 */       size += 8;
/*     */     } else {
/*     */       
/* 437 */       size += 8;
/*     */     } 
/* 439 */     size += 4;
/*     */     
/* 441 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV3(int ptrSize, boolean isZOS) {
/* 450 */     int size = getFieldSizeV2(ptrSize, isZOS);
/* 451 */     size += 4;
/*     */     
/* 453 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV4(int ptrSize, boolean isZOS) {
/* 462 */     int size = getFieldSizeV3(ptrSize, isZOS);
/* 463 */     size += ptrSize + 4 + 4;
/*     */     
/* 465 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV5(int ptrSize, boolean isZOS) {
/* 474 */     int size = getFieldSizeV4(ptrSize, isZOS);
/* 475 */     size += 96;
/*     */     
/* 477 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV2(int ptrSize, boolean isZOS) {
/* 487 */     int size = getFieldSizeV2(ptrSize, isZOS);
/* 488 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*     */     
/* 490 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV3(int ptrSize, boolean isZOS) {
/* 500 */     int size = getFieldSizeV3(ptrSize, isZOS);
/*     */     
/* 502 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV4(int ptrSize, boolean isZOS) {
/* 512 */     int size = getFieldSizeV4(ptrSize, isZOS);
/*     */     
/* 514 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*     */     
/* 516 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV5(int ptrSize, boolean isZOS) {
/* 526 */     int size = getFieldSizeV5(ptrSize, isZOS);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 531 */     size += JmqiTools.alignToGrain(Math.max(ptrSize, 8), size);
/* 532 */     return size;
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
/*     */   public static int getSize(JmqiEnvironment env, int version, int ptrSize, boolean isZOS) throws JmqiException {
/* 546 */     int size = 0;
/* 547 */     switch (version) {
/*     */       case 2:
/* 549 */         size = getSizeV2(ptrSize, isZOS);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 564 */         return size;case 3: size = getSizeV3(ptrSize, isZOS); return size;case 4: size = getSizeV4(ptrSize, isZOS); return size;case 5: size = getSizeV5(ptrSize, isZOS); return size;
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
/* 575 */     if (Trace.isOn)
/* 576 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 577 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 579 */     int size = getSize(this.env, this.version, ptrSize, this.isZOS);
/*     */     
/* 581 */     if (Trace.isOn) {
/* 582 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 583 */           Integer.valueOf(size));
/*     */     }
/* 585 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 593 */     if (Trace.isOn) {
/* 594 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 596 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 598 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 599 */     int pos = offset;
/*     */     
/* 601 */     dc.writeMQField("LGO ", buffer, pos, 4, cp, tls);
/* 602 */     pos += 4;
/*     */     
/* 604 */     dc.writeI32(this.version, buffer, pos, swap);
/* 605 */     pos += 4;
/*     */     
/* 607 */     dc.writeI32(this.options, buffer, pos, swap);
/* 608 */     pos += 4;
/*     */     
/* 610 */     dc.writeI32(this.queueEmpty, buffer, pos, swap);
/* 611 */     pos += 4;
/* 612 */     if (this.isZOS) {
/*     */       
/* 614 */       dc.clear(buffer, pos, 4);
/* 615 */       pos += 4;
/*     */       
/* 617 */       dc.writeI32(this.qTime_zos, buffer, pos, swap);
/* 618 */       pos += 4;
/*     */     }
/*     */     else {
/*     */       
/* 622 */       dc.writeI64(this.qTime_distributed, buffer, pos, swap);
/* 623 */       pos += 8;
/*     */     } 
/*     */     
/* 626 */     dc.writeI32(this.inherited, buffer, pos, swap);
/* 627 */     pos += 4;
/* 628 */     if (this.version == 2) {
/*     */       
/* 630 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 631 */       dc.clear(buffer, pos, padding);
/* 632 */       pos += padding;
/*     */     } 
/* 634 */     if (this.version >= 3) {
/*     */       
/* 636 */       dc.writeU16(this.publishFlags, buffer, pos, swap);
/* 637 */       pos += 2;
/*     */       
/* 639 */       buffer[pos] = (byte)this.propFlags;
/* 640 */       pos++;
/*     */       
/* 642 */       buffer[pos] = (byte)this.asrtFlags;
/* 643 */       pos++;
/*     */     } 
/* 645 */     if (this.version >= 4) {
/*     */       
/* 647 */       dc.clear(buffer, pos, ptrSize);
/* 648 */       pos += ptrSize;
/*     */       
/* 650 */       dc.clear(buffer, pos, 4);
/* 651 */       pos += 4;
/*     */       
/* 653 */       dc.clear(buffer, pos, 4);
/* 654 */       pos += 4;
/*     */     } 
/*     */     
/* 657 */     if (this.version == 4) {
/* 658 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 659 */       dc.clear(buffer, pos, padding);
/* 660 */       pos += padding;
/*     */     } 
/*     */     
/* 663 */     if (this.version >= 5) {
/*     */       
/* 665 */       dc.clear(buffer, pos, 48);
/* 666 */       pos += 48;
/*     */       
/* 668 */       dc.clear(buffer, pos, 48);
/* 669 */       pos += 48;
/*     */     } 
/*     */     
/* 672 */     if (this.version == 5) {
/*     */       
/* 674 */       int padding = JmqiTools.alignToGrain(Math.max(ptrSize, 8), pos);
/* 675 */       dc.clear(buffer, pos, padding);
/* 676 */       pos += padding;
/*     */     } 
/*     */     
/* 679 */     if (Trace.isOn) {
/* 680 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 681 */           Integer.valueOf(pos));
/*     */     }
/* 683 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 691 */     if (Trace.isOn) {
/* 692 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 694 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 696 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 697 */     int pos = offset;
/*     */     
/* 699 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 700 */     if (!strucId.equals("LGO ")) {
/* 701 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 6107, null);
/* 702 */       if (Trace.isOn) {
/* 703 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 706 */       throw traceRet1;
/*     */     } 
/* 708 */     pos += 4;
/*     */ 
/*     */     
/* 711 */     this.version = dc.readI32(buffer, pos, swap);
/* 712 */     pos += 4;
/*     */ 
/*     */     
/* 715 */     this.options = dc.readI32(buffer, pos, swap);
/* 716 */     pos += 4;
/*     */ 
/*     */     
/* 719 */     this.queueEmpty = dc.readI32(buffer, pos, swap);
/* 720 */     pos += 4;
/*     */     
/* 722 */     if (this.isZOS) {
/*     */       
/* 724 */       pos += 4;
/*     */ 
/*     */       
/* 727 */       this.qTime_zos = dc.readI32(buffer, pos, swap);
/* 728 */       pos += 4;
/*     */     }
/*     */     else {
/*     */       
/* 732 */       this.qTime_distributed = dc.readI64(buffer, pos, swap);
/* 733 */       pos += 8;
/*     */     } 
/*     */ 
/*     */     
/* 737 */     this.inherited = dc.readI32(buffer, pos, swap);
/* 738 */     pos += 4;
/*     */     
/* 740 */     if (this.version == 2) {
/*     */       
/* 742 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 743 */       pos += padding;
/*     */     } 
/*     */     
/* 746 */     if (this.version >= 3) {
/*     */       
/* 748 */       this.publishFlags = dc.readU16(buffer, pos, swap);
/* 749 */       pos += 2;
/*     */ 
/*     */       
/* 752 */       this.propFlags = buffer[pos];
/* 753 */       pos++;
/*     */ 
/*     */       
/* 756 */       this.asrtFlags = buffer[pos];
/* 757 */       pos++;
/*     */     } 
/*     */     
/* 760 */     if (this.version >= 4) {
/*     */       
/* 762 */       pos += ptrSize;
/*     */ 
/*     */       
/* 765 */       pos += 4;
/*     */ 
/*     */       
/* 768 */       pos += 4;
/*     */     } 
/*     */     
/* 771 */     if (this.version == 4) {
/*     */       
/* 773 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 774 */       pos += padding;
/*     */     } 
/*     */     
/* 777 */     if (this.version >= 5) {
/*     */       
/* 779 */       pos += 48;
/*     */ 
/*     */       
/* 782 */       pos += 48;
/*     */     } 
/*     */     
/* 785 */     if (this.version == 5) {
/*     */       
/* 787 */       int padding = JmqiTools.alignToGrain(Math.max(ptrSize, 8), pos);
/* 788 */       pos += padding;
/*     */     } 
/*     */     
/* 791 */     if (Trace.isOn) {
/* 792 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiGetOptions", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 793 */           Integer.valueOf(pos));
/*     */     }
/* 795 */     return pos;
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
/* 807 */     fmt.add("version", this.version);
/* 808 */     fmt.add("options", this.options);
/* 809 */     String optionDescription = formatOptions(this.options, getFields(), "lpiGETOPT_");
/* 810 */     fmt.add("option flags", optionDescription);
/* 811 */     fmt.add("queueEmpty", this.queueEmpty);
/* 812 */     fmt.add("qTime.dist", this.qTime_distributed);
/* 813 */     fmt.add("qTime.zos", this.qTime_zos);
/* 814 */     fmt.add("inherited", this.inherited);
/* 815 */     fmt.add("publishFlags", this.publishFlags);
/* 816 */     fmt.add("propFlags", this.propFlags);
/* 817 */     fmt.add("asrtFlags", this.asrtFlags);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static synchronized Field[] getFields() {
/* 827 */     Reference<Field[]> ref = fieldsRef;
/*     */     
/*     */     Field[] fields;
/* 830 */     if (ref == null || (fields = ref.get()) == null) {
/* 831 */       fieldsRef = (Reference)new SoftReference<>(fields = SpiGetOptions.class.getFields());
/*     */     }
/*     */     
/* 834 */     if (Trace.isOn) {
/* 835 */       Trace.data("com.ibm.mq.jmqi.system.SpiGetOptions", "getFields()", "getter", fields);
/*     */     }
/* 837 */     return fields;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\SpiGetOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */