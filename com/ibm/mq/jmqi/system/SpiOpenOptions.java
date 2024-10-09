/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import com.ibm.mq.constants.MQConstants;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.MQCHARV;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SpiOpenOptions
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/SpiOpenOptions.java";
/*     */   private static final String lpiOPENOPT_STRUC_ID = "LPOO";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_LPIOPTIONS = 4;
/*     */   private static final int SIZEOF_DEF_PERSISTENCE = 4;
/*     */   private static final int SIZEOF_DEF_PUT_RESPONSE_TYPE = 4;
/*     */   private static final int SIZEOF_DEF_READ_AHEAD = 4;
/*     */   private static final int SIZEOF_PROPERTY_CONTROL = 4;
/*     */   private static final int SIZEOF_MULTICAST = 4;
/*     */   private static final int SIZEOF_AMS_FLAGS = 4;
/*     */   private static final int SIZEOF_LPICOMMINFO_ATTR_DATA_ZOS = 4;
/*     */   public static final int lpiOPENOPT_VERSION_1 = 1;
/*     */   public static final int lpiOPENOPT_VERSION_2 = 2;
/*     */   public static final int lpiOPENOPT_VERSION_3 = 3;
/*     */   public static final int lpiOPENOPT_VERSION_4 = 4;
/*     */   public static final int lpiOPENOPTS_CURRENT_VERSION = 4;
/*     */   public static final int lpiOPENOPTS_NONE = 0;
/*     */   public static final int lpiOPENOPTS_SAVE_IDENTITY_CTXT = 1;
/*     */   public static final int lpiOPENOPTS_SAVE_ORIGIN_CTXT = 2;
/*     */   public static final int lpiOPENOPTS_SAVE_USER_CTXT = 4;
/*     */   public static final int lpiOPENOPTS_MULTICAST_SUPPORTED = 8;
/*     */   public static final int lpiOPENOPTS_REQUEST_AMS_POLICY = 32;
/* 139 */   private int version = 1;
/* 140 */   private int options = 0;
/* 141 */   private int lpiOptions = 0;
/* 142 */   private int defPersistence = -1;
/* 143 */   private int defPutResponseType = -1;
/* 144 */   private int defReadAhead = -1;
/* 145 */   private int propertyControl = -1;
/* 146 */   private int multicast = -1;
/*     */   
/*     */   private LpiCOMMINFO_ATTR_DATA commInfoAttrData;
/*     */   
/*     */   private int amsFlags;
/*     */   
/*     */   private MQCHARV amsPolicy;
/*     */   
/*     */   private String amsErrorQueue;
/*     */   
/*     */   private String clusterName;
/*     */   private boolean isZOS = false;
/*     */   private static Reference<Field[]> fieldsRef;
/*     */   
/*     */   public SpiOpenOptions(JmqiEnvironment env) {
/* 161 */     super(env);
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */ 
/*     */     
/* 167 */     this.commInfoAttrData = new LpiCOMMINFO_ATTR_DATA(env);
/* 168 */     this.amsFlags = 0;
/* 169 */     this.amsPolicy = env.newMQCHARV();
/* 170 */     this.amsErrorQueue = "";
/* 171 */     this.clusterName = "";
/*     */     
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isZOS() {
/* 183 */     if (Trace.isOn) {
/* 184 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "isZOS()", "getter", 
/* 185 */           Boolean.valueOf(this.isZOS));
/*     */     }
/* 187 */     return this.isZOS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setZOS(boolean isZOS) {
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "setZOS(boolean)", "setter", 
/* 196 */           Boolean.valueOf(isZOS));
/*     */     }
/* 198 */     this.isZOS = isZOS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV1() {
/* 205 */     int size = 0;
/* 206 */     size += 4;
/* 207 */     size += 4;
/* 208 */     size += 4;
/* 209 */     size += 4;
/* 210 */     size += 4;
/* 211 */     size += 4;
/* 212 */     size += 4;
/* 213 */     size += 4;
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.data("com.ibm.mq.jmqi.system.SpiOpenOptions", "getFieldSizeV1()", "getter", 
/* 216 */           Integer.valueOf(size));
/*     */     }
/* 218 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 226 */     if (Trace.isOn)
/* 227 */       Trace.entry("com.ibm.mq.jmqi.system.SpiOpenOptions", "getSizeV1(int)", new Object[] {
/* 228 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 230 */     int size = getFieldSizeV1();
/*     */     
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.exit("com.ibm.mq.jmqi.system.SpiOpenOptions", "getSizeV1(int)", Integer.valueOf(size));
/*     */     }
/* 235 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV2(int ptrSize, boolean isZOS) {
/* 244 */     if (Trace.isOn)
/* 245 */       Trace.entry("com.ibm.mq.jmqi.system.SpiOpenOptions", "getFieldSizeV2(int,boolean)", new Object[] {
/* 246 */             Integer.valueOf(ptrSize), Boolean.valueOf(isZOS)
/*     */           }); 
/* 248 */     int size = 0;
/* 249 */     size += getFieldSizeV1();
/* 250 */     size += 4;
/* 251 */     if (isZOS) {
/* 252 */       size += 4;
/*     */     } else {
/*     */       
/* 255 */       size += LpiCOMMINFO_ATTR_DATA.getStructureSize(ptrSize);
/*     */     } 
/*     */     
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.exit("com.ibm.mq.jmqi.system.SpiOpenOptions", "getFieldSizeV2(int,boolean)", 
/* 260 */           Integer.valueOf(size));
/*     */     }
/* 262 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV2(int ptrSize, boolean isZOS) {
/* 271 */     if (Trace.isOn)
/* 272 */       Trace.entry("com.ibm.mq.jmqi.system.SpiOpenOptions", "getSizeV2(int,boolean)", new Object[] {
/* 273 */             Integer.valueOf(ptrSize), Boolean.valueOf(isZOS)
/*     */           }); 
/* 275 */     int size = 0;
/* 276 */     size += getFieldSizeV2(ptrSize, isZOS);
/*     */ 
/*     */     
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.exit("com.ibm.mq.jmqi.system.SpiOpenOptions", "getSizeV2(int,boolean)", 
/* 281 */           Integer.valueOf(size));
/*     */     }
/* 283 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV3(int ptrSize, boolean isZOS) {
/* 292 */     if (Trace.isOn)
/* 293 */       Trace.entry("com.ibm.mq.jmqi.system.SpiOpenOptions", "getFieldSizeV3(int,boolean)", new Object[] {
/* 294 */             Integer.valueOf(ptrSize), Boolean.valueOf(isZOS)
/*     */           }); 
/* 296 */     int size = 0;
/* 297 */     size += getFieldSizeV2(ptrSize, isZOS);
/* 298 */     size += 4;
/* 299 */     size += MQCHARV.getSize(ptrSize);
/* 300 */     size += 48;
/*     */     
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.exit("com.ibm.mq.jmqi.system.SpiOpenOptions", "getFieldSizeV3(int,boolean)", 
/* 304 */           Integer.valueOf(size));
/*     */     }
/* 306 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV3(int ptrSize, boolean isZOS) {
/* 315 */     if (Trace.isOn)
/* 316 */       Trace.entry("com.ibm.mq.jmqi.system.SpiOpenOptions", "getSizeV3(int,boolean)", new Object[] {
/* 317 */             Integer.valueOf(ptrSize), Boolean.valueOf(isZOS)
/*     */           }); 
/* 319 */     int size = 0;
/* 320 */     size += getFieldSizeV3(ptrSize, isZOS);
/*     */     
/* 322 */     if (Trace.isOn) {
/* 323 */       Trace.exit("com.ibm.mq.jmqi.system.SpiOpenOptions", "getSizeV3(int,boolean)", 
/* 324 */           Integer.valueOf(size));
/*     */     }
/* 326 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV4(int ptrSize, boolean isZOS) {
/* 335 */     if (Trace.isOn)
/* 336 */       Trace.entry("com.ibm.mq.jmqi.system.SpiOpenOptions", "getFieldSizeV4(int,boolean)", new Object[] {
/* 337 */             Integer.valueOf(ptrSize), Boolean.valueOf(isZOS)
/*     */           }); 
/* 339 */     int size = 0;
/* 340 */     size += getFieldSizeV3(ptrSize, isZOS);
/* 341 */     size += 48;
/*     */     
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.exit("com.ibm.mq.jmqi.system.SpiOpenOptions", "getFieldSizeV4(int,boolean)", 
/* 345 */           Integer.valueOf(size));
/*     */     }
/* 347 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV4(int ptrSize, boolean isZOS) {
/* 356 */     if (Trace.isOn)
/* 357 */       Trace.entry("com.ibm.mq.jmqi.system.SpiOpenOptions", "getSizeV4(int,boolean)", new Object[] {
/* 358 */             Integer.valueOf(ptrSize), Boolean.valueOf(isZOS)
/*     */           }); 
/* 360 */     int size = 0;
/* 361 */     size += getFieldSizeV4(ptrSize, isZOS);
/*     */     
/* 363 */     if (Trace.isOn) {
/* 364 */       Trace.exit("com.ibm.mq.jmqi.system.SpiOpenOptions", "getSizeV4(int,boolean)", 
/* 365 */           Integer.valueOf(size));
/*     */     }
/* 367 */     return size;
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
/* 381 */     int size = 0;
/* 382 */     switch (version) {
/*     */       case 1:
/* 384 */         size = getSizeV1(ptrSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 400 */         return size;case 2: size = getSizeV2(ptrSize, isZOS); return size;case 3: size = getSizeV3(ptrSize, isZOS); return size;case 4: size = getSizeV4(ptrSize, isZOS); return size;
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
/* 411 */     if (Trace.isOn) {
/* 412 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 413 */             Integer.valueOf(ptrSize), cp
/*     */           });
/*     */     }
/* 416 */     int size = getSize(this.env, this.version, ptrSize, this.isZOS);
/*     */ 
/*     */     
/* 419 */     if (this.version >= 3) {
/* 420 */       size += this.amsPolicy.getRequiredBufferSize(ptrSize, cp);
/*     */     }
/*     */     
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 425 */           Integer.valueOf(size));
/*     */     }
/* 427 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 435 */     if (Trace.isOn) {
/* 436 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "getVersion()", "getter", 
/* 437 */           Integer.valueOf(this.version));
/*     */     }
/* 439 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 447 */     if (Trace.isOn) {
/* 448 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "setVersion(int)", "setter", 
/* 449 */           Integer.valueOf(version));
/*     */     }
/* 451 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 458 */     if (Trace.isOn) {
/* 459 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "getOptions()", "getter", 
/* 460 */           Integer.valueOf(this.options));
/*     */     }
/* 462 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 469 */     if (Trace.isOn) {
/* 470 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "setOptions(int)", "setter", 
/* 471 */           Integer.valueOf(options));
/*     */     }
/* 473 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLpiOptions() {
/* 480 */     if (Trace.isOn) {
/* 481 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "getLpiOptions()", "getter", 
/* 482 */           Integer.valueOf(this.lpiOptions));
/*     */     }
/* 484 */     return this.lpiOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLpiOptions(int lpiOptions) {
/* 491 */     if (Trace.isOn) {
/* 492 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "setLpiOptions(int)", "setter", 
/* 493 */           Integer.valueOf(lpiOptions));
/*     */     }
/* 495 */     this.lpiOptions = lpiOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDefPersistence() {
/* 502 */     if (Trace.isOn) {
/* 503 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "getDefPersistence()", "getter", 
/* 504 */           Integer.valueOf(this.defPersistence));
/*     */     }
/* 506 */     return this.defPersistence;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefPersistence(int defPersistence) {
/* 513 */     if (Trace.isOn) {
/* 514 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "setDefPersistence(int)", "setter", 
/* 515 */           Integer.valueOf(defPersistence));
/*     */     }
/* 517 */     this.defPersistence = defPersistence;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDefPutResponseType() {
/* 524 */     if (Trace.isOn) {
/* 525 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "getDefPutResponseType()", "getter", 
/* 526 */           Integer.valueOf(this.defPutResponseType));
/*     */     }
/* 528 */     return this.defPutResponseType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefPutResponseType(int defPutResponseType) {
/* 535 */     if (Trace.isOn) {
/* 536 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "setDefPutResponseType(int)", "setter", 
/* 537 */           Integer.valueOf(defPutResponseType));
/*     */     }
/* 539 */     this.defPutResponseType = defPutResponseType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDefReadAhead() {
/* 546 */     if (Trace.isOn) {
/* 547 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "getDefReadAhead()", "getter", 
/* 548 */           Integer.valueOf(this.defReadAhead));
/*     */     }
/* 550 */     return this.defReadAhead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefReadAhead(int defReadAhead) {
/* 557 */     if (Trace.isOn) {
/* 558 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "setDefReadAhead(int)", "setter", 
/* 559 */           Integer.valueOf(defReadAhead));
/*     */     }
/* 561 */     this.defReadAhead = defReadAhead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPropertyControl() {
/* 568 */     if (Trace.isOn) {
/* 569 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "getPropertyControl()", "getter", 
/* 570 */           Integer.valueOf(this.propertyControl));
/*     */     }
/* 572 */     return this.propertyControl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPropertyControl(int propertyControl) {
/* 579 */     if (Trace.isOn) {
/* 580 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "setPropertyControl(int)", "setter", 
/* 581 */           Integer.valueOf(propertyControl));
/*     */     }
/* 583 */     this.propertyControl = propertyControl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMulticast() {
/* 590 */     if (Trace.isOn) {
/* 591 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "getMulticast()", "getter", 
/* 592 */           Integer.valueOf(this.multicast));
/*     */     }
/* 594 */     return this.multicast;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMulticast(int value) {
/* 601 */     if (Trace.isOn) {
/* 602 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "setMulticast(int)", "setter", 
/* 603 */           Integer.valueOf(value));
/*     */     }
/* 605 */     this.multicast = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LpiCOMMINFO_ATTR_DATA getCommInfoAttrData() {
/* 612 */     if (Trace.isOn) {
/* 613 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "getCommInfoAttrData()", "getter", this.commInfoAttrData);
/*     */     }
/*     */     
/* 616 */     return this.commInfoAttrData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClusterName(String clusterName) {
/* 623 */     if (Trace.isOn) {
/* 624 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "setClusterName(String)", "setter", clusterName);
/*     */     }
/*     */     
/* 627 */     this.clusterName = clusterName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClusterName() {
/* 634 */     if (Trace.isOn) {
/* 635 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "getClusterName()", "getter", this.clusterName);
/*     */     }
/*     */     
/* 638 */     return this.clusterName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 647 */     if (Trace.isOn) {
/* 648 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 650 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 652 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 653 */     int pos = offset;
/* 654 */     int startPos = offset;
/* 655 */     int amsPolicyInfoPos = -1;
/*     */ 
/*     */     
/* 658 */     dc.writeMQField("LPOO", buffer, pos, 4, cp, tls);
/* 659 */     pos += 4;
/*     */     
/* 661 */     dc.writeI32(this.version, buffer, pos, swap);
/* 662 */     pos += 4;
/*     */     
/* 664 */     dc.writeI32(this.options, buffer, pos, swap);
/* 665 */     pos += 4;
/*     */     
/* 667 */     dc.writeI32(this.lpiOptions, buffer, pos, swap);
/* 668 */     pos += 4;
/*     */     
/* 670 */     dc.writeI32(this.defPersistence, buffer, pos, swap);
/* 671 */     pos += 4;
/*     */     
/* 673 */     dc.writeI32(this.defPutResponseType, buffer, pos, swap);
/* 674 */     pos += 4;
/*     */     
/* 676 */     dc.writeI32(this.defReadAhead, buffer, pos, swap);
/* 677 */     pos += 4;
/*     */     
/* 679 */     dc.writeI32(this.propertyControl, buffer, pos, swap);
/* 680 */     pos += 4;
/* 681 */     if (this.version >= 2) {
/*     */       
/* 683 */       dc.writeI32(this.multicast, buffer, pos, swap);
/* 684 */       pos += 4;
/* 685 */       if (this.isZOS) {
/*     */         
/* 687 */         dc.clear(buffer, pos, 4);
/* 688 */         pos += 4;
/*     */       }
/*     */       else {
/*     */         
/* 692 */         pos += this.commInfoAttrData.writeToBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 697 */     if (this.version >= 3) {
/* 698 */       dc.writeI32(this.amsFlags, buffer, pos, swap);
/* 699 */       pos += 4;
/*     */       
/* 701 */       amsPolicyInfoPos = pos;
/* 702 */       pos += MQCHARV.getSize(ptrSize);
/*     */       
/* 704 */       dc.clear(buffer, pos, 48);
/* 705 */       pos += 48;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 710 */     if (this.version >= 4) {
/*     */       
/* 712 */       dc.clear(buffer, pos, 48);
/* 713 */       pos += 48;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 718 */     if (amsPolicyInfoPos > 0) {
/* 719 */       pos = this.amsPolicy.writeToBuffer(buffer, startPos, amsPolicyInfoPos, pos, ptrSize, swap, cp);
/*     */     }
/*     */     
/* 722 */     if (Trace.isOn) {
/* 723 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 724 */           Integer.valueOf(pos));
/*     */     }
/* 726 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 735 */     if (Trace.isOn) {
/* 736 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 738 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 740 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 741 */     int pos = offset;
/* 742 */     int startPos = offset;
/* 743 */     int variableDataEnd = -1;
/*     */ 
/*     */     
/* 746 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 747 */     if (!strucId.equals("LPOO")) {
/* 748 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 6107, null);
/*     */       
/* 750 */       if (Trace.isOn) {
/* 751 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 754 */       throw traceRet1;
/*     */     } 
/* 756 */     pos += 4;
/*     */ 
/*     */     
/* 759 */     this.version = dc.readI32(buffer, pos, swap);
/* 760 */     pos += 4;
/*     */ 
/*     */     
/* 763 */     this.options = dc.readI32(buffer, pos, swap);
/* 764 */     pos += 4;
/*     */ 
/*     */     
/* 767 */     this.lpiOptions = dc.readI32(buffer, pos, swap);
/* 768 */     pos += 4;
/*     */ 
/*     */     
/* 771 */     this.defPersistence = dc.readI32(buffer, pos, swap);
/* 772 */     pos += 4;
/*     */ 
/*     */     
/* 775 */     this.defPutResponseType = dc.readI32(buffer, pos, swap);
/* 776 */     pos += 4;
/*     */ 
/*     */     
/* 779 */     this.defReadAhead = dc.readI32(buffer, pos, swap);
/* 780 */     pos += 4;
/*     */ 
/*     */     
/* 783 */     this.propertyControl = dc.readI32(buffer, pos, swap);
/* 784 */     pos += 4;
/*     */     
/* 786 */     if (this.version >= 2) {
/*     */       
/* 788 */       this.multicast = dc.readI32(buffer, pos, swap);
/* 789 */       pos += 4;
/*     */       
/* 791 */       if (this.isZOS) {
/*     */         
/* 793 */         pos += 4;
/*     */       }
/*     */       else {
/*     */         
/* 797 */         pos += this.commInfoAttrData.readFromBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 803 */     if (this.version >= 3) {
/* 804 */       this.amsFlags = dc.readI32(buffer, pos, swap);
/* 805 */       pos += 4;
/*     */       
/* 807 */       pos = this.amsPolicy.readFromBuffer(buffer, startPos, pos, ptrSize, swap, tls);
/* 808 */       int apiEnd = this.amsPolicy.getEndPosAligned(startPos);
/* 809 */       if (apiEnd > variableDataEnd) {
/* 810 */         variableDataEnd = apiEnd;
/*     */       }
/*     */       
/* 813 */       this.amsErrorQueue = dc.readMQField(buffer, pos, 48, cp, tls);
/* 814 */       pos += 48;
/* 815 */       if (this.amsErrorQueue != null) {
/* 816 */         this.amsErrorQueue = this.amsErrorQueue.trim();
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 822 */     if (this.version >= 4) {
/*     */       
/* 824 */       this.clusterName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 825 */       pos += 48;
/* 826 */       if (this.clusterName != null) {
/* 827 */         this.clusterName = this.clusterName.trim();
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 833 */     if (variableDataEnd > pos) {
/* 834 */       pos = variableDataEnd;
/*     */     }
/*     */     
/* 837 */     if (Trace.isOn) {
/* 838 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 839 */           Integer.valueOf(pos));
/*     */     }
/* 841 */     return pos;
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
/* 853 */     fmt.add("version", this.version);
/* 854 */     fmt.add("options", this.options);
/* 855 */     String optionDescription = MQConstants.decodeOptionsForTrace(this.options, "MQOO_.*");
/* 856 */     fmt.add("option flags", optionDescription);
/* 857 */     fmt.add("lpiOptions", this.lpiOptions);
/* 858 */     String lpiOptionDescription = formatOptions(this.lpiOptions, getFields(), "lpiOPENOPTS_");
/* 859 */     fmt.add("option flags", lpiOptionDescription);
/* 860 */     fmt.add("defPersistence", this.defPersistence);
/* 861 */     fmt.add("defPutResponseType", this.defPutResponseType);
/* 862 */     fmt.add("defReadAhead", this.defReadAhead);
/* 863 */     fmt.add("propertyControl", this.propertyControl);
/* 864 */     fmt.add("multicast", this.multicast);
/* 865 */     fmt.add("commInfoAttrData", this.commInfoAttrData);
/* 866 */     fmt.add("amsPolicyInfo", this.amsPolicy);
/* 867 */     fmt.add("amsErrorQueue", this.amsErrorQueue);
/* 868 */     fmt.add("clusterName", this.clusterName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrentVersion() {
/* 876 */     if (Trace.isOn) {
/* 877 */       Trace.data(this, "com.ibm.mq.jmqi.system.SpiOpenOptions", "getCurrentVersion()", "getter", 
/* 878 */           Integer.valueOf(4));
/*     */     }
/* 880 */     return 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static synchronized Field[] getFields() {
/* 890 */     Reference<Field[]> ref = fieldsRef;
/*     */     
/*     */     Field[] fields;
/* 893 */     if (ref == null || (fields = ref.get()) == null) {
/* 894 */       fieldsRef = (Reference)new SoftReference<>(fields = SpiOpenOptions.class.getFields());
/*     */     }
/*     */     
/* 897 */     return fields;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\SpiOpenOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */