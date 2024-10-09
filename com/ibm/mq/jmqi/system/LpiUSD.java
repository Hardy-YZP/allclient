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
/*     */ public class LpiUSD
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/LpiUSD.java";
/*     */   private static final String lpiUSD_STRUCID = "LUSD";
/*     */   public static final int lpiUSD_VERSION_1 = 1;
/*     */   public static final int lpiUSD_CURRENT_VERSION = 1;
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_SUBID = 24;
/*     */   private static final int SIZEOF_ALTERNATE_USER_ID = 12;
/*     */   private static final int SIZEOF_ALTERNATE_SECURITY_ID = 40;
/*     */   public static final int lpiUSDO_NONE = 0;
/*     */   public static final int lpiUSDO_LEAVE_ONLY = 32;
/*     */   public static final int lpiUSDO_ALTERNATE_USER_AUTHORITY = 262144;
/*     */   public static final int lpiUSDO_SYNCPOINT = 67108864;
/*  86 */   private int version = 1;
/*  87 */   private int options = 0;
/*     */   private MQCHARV subIdentity;
/*     */   private MQCHARV subName;
/*  90 */   private byte[] subId = new byte[24];
/*     */   private String alternateUserId;
/*  92 */   private byte[] alternateSecurityId = new byte[40];
/*     */ 
/*     */   
/*     */   private static Reference<Field[]> fieldsRef;
/*     */ 
/*     */ 
/*     */   
/*     */   public LpiUSD(JmqiEnvironment env) {
/* 100 */     super(env);
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiUSD", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/* 105 */     this.subIdentity = env.newMQCHARV();
/* 106 */     this.subName = env.newMQCHARV();
/*     */     
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiUSD", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiUSD", "getVersion()", "getter", 
/* 121 */           Integer.valueOf(this.version));
/*     */     }
/* 123 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiUSD", "setVersion(int)", "setter", 
/* 135 */           Integer.valueOf(version));
/*     */     }
/* 137 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiUSD", "getOptions()", "getter", 
/* 146 */           Integer.valueOf(this.options));
/*     */     }
/* 148 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiUSD", "setOptions(int)", "setter", 
/* 159 */           Integer.valueOf(options));
/*     */     }
/* 161 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getSubId() {
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiUSD", "getSubId()", "getter", this.subId);
/*     */     }
/* 171 */     return this.subId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCHARV getSubIdentity() {
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiUSD", "getSubIdentity()", "getter", this.subIdentity);
/*     */     }
/*     */     
/* 182 */     return this.subIdentity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCHARV getSubName() {
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiUSD", "getSubName()", "getter", this.subName);
/*     */     }
/* 192 */     return this.subName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubId(byte[] value) {
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.data(this, "setSubId(byte[])", this.subId);
/*     */     }
/* 204 */     JmqiTools.byteArrayCopy(value, 0, this.subId, 0, 24);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAlternateUserId() {
/* 212 */     if (Trace.isOn) {
/* 213 */       Trace.data(this, "getAlternateUserId()", this.alternateUserId);
/*     */     }
/* 215 */     return this.alternateUserId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlternateUserId(String value) {
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.data(this, "setAlternateUserId()", value);
/*     */     }
/* 226 */     this.alternateUserId = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getAlternateSecurityId() {
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.data(this, "getAlternateSecurityId", this.alternateSecurityId);
/*     */     }
/* 237 */     return this.alternateSecurityId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlternateSecurityId(byte[] value) {
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.data(this, "setAlternateSecurityId", value);
/*     */     }
/* 248 */     JmqiTools.byteArrayCopy(value, 0, this.alternateSecurityId, 0, 40);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getCurrentSize(int ptrSize) {
/* 256 */     if (Trace.isOn)
/* 257 */       Trace.entry("com.ibm.mq.jmqi.system.LpiUSD", "getCurrentSize(int)", new Object[] {
/* 258 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 260 */     int traceRet1 = getSizeV1(ptrSize);
/*     */     
/* 262 */     if (Trace.isOn) {
/* 263 */       Trace.exit("com.ibm.mq.jmqi.system.LpiUSD", "getCurrentSize(int)", 
/* 264 */           Integer.valueOf(traceRet1));
/*     */     }
/* 266 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 274 */     if (Trace.isOn)
/* 275 */       Trace.entry("com.ibm.mq.jmqi.system.LpiUSD", "getSizeV1(int)", new Object[] {
/* 276 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 278 */     int size = 12;
/* 279 */     size += JmqiTools.alignToGrain(ptrSize, size);
/* 280 */     size += MQCHARV.getSize(ptrSize);
/* 281 */     size += MQCHARV.getSize(ptrSize);
/* 282 */     size += 76;
/* 283 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*     */     
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.exit("com.ibm.mq.jmqi.system.LpiUSD", "getSizeV1(int)", Integer.valueOf(size));
/*     */     }
/* 288 */     return size;
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
/* 300 */     int size = 0;
/* 301 */     switch (version) {
/*     */       case 1:
/* 303 */         size = getSizeV1(ptrSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 310 */         return size;
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
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiUSD", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 323 */             Integer.valueOf(ptrSize), cp
/*     */           });
/*     */     }
/* 326 */     int size = getSize(this.env, this.version, ptrSize);
/*     */     
/* 328 */     size += this.subIdentity.getRequiredBufferSize(ptrSize, cp);
/* 329 */     size += this.subName.getRequiredBufferSize(ptrSize, cp);
/*     */     
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiUSD", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 333 */           Integer.valueOf(size));
/*     */     }
/* 335 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 344 */     if (Trace.isOn) {
/* 345 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiUSD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 347 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 349 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 350 */     int pos = offset;
/* 351 */     int subIdentityPos = -1;
/* 352 */     int subNamePos = -1;
/*     */     
/* 354 */     dc.writeMQField("LUSD", buffer, pos, 4, cp, tls);
/* 355 */     pos += 4;
/*     */     
/* 357 */     dc.writeI32(this.version, buffer, pos, swap);
/* 358 */     pos += 4;
/*     */     
/* 360 */     dc.writeI32(this.options, buffer, pos, swap);
/* 361 */     pos += 4;
/*     */     
/* 363 */     int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 364 */     dc.clear(buffer, pos, padding);
/* 365 */     pos += padding;
/*     */     
/* 367 */     subIdentityPos = pos;
/* 368 */     pos += MQCHARV.getSize(ptrSize);
/*     */     
/* 370 */     subNamePos = pos;
/* 371 */     pos += MQCHARV.getSize(ptrSize);
/*     */     
/* 373 */     System.arraycopy(this.subId, 0, buffer, pos, 24);
/* 374 */     pos += 24;
/*     */     
/* 376 */     dc.writeMQField(this.alternateUserId, buffer, pos, 12, cp, tls);
/* 377 */     pos += 12;
/*     */     
/* 379 */     System.arraycopy(this.alternateSecurityId, 0, buffer, pos, 40);
/* 380 */     pos += 40;
/*     */     
/* 382 */     padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 383 */     dc.clear(buffer, pos, padding);
/* 384 */     pos += padding;
/*     */ 
/*     */     
/* 387 */     if (subIdentityPos > 0) {
/* 388 */       pos = this.subIdentity.writeToBuffer(buffer, offset, subIdentityPos, pos, ptrSize, swap, cp);
/*     */     }
/*     */     
/* 391 */     if (subNamePos > 0) {
/* 392 */       pos = this.subName.writeToBuffer(buffer, offset, subNamePos, pos, ptrSize, swap, cp);
/*     */     }
/*     */     
/* 395 */     if (Trace.isOn) {
/* 396 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiUSD", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 397 */           Integer.valueOf(pos));
/*     */     }
/* 399 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 408 */     if (Trace.isOn) {
/* 409 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiUSD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 411 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 413 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 414 */     int pos = offset;
/* 415 */     int variableDataEnd = -1;
/*     */     
/* 417 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 418 */     if (!strucId.equals("LUSD")) {
/* 419 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 6107, null);
/*     */ 
/*     */       
/* 422 */       if (Trace.isOn) {
/* 423 */         Trace.throwing(this, "com.ibm.mq.jmqi.system.LpiUSD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 426 */       throw traceRet1;
/*     */     } 
/* 428 */     pos += 4;
/*     */ 
/*     */     
/* 431 */     this.version = dc.readI32(buffer, pos, swap);
/* 432 */     pos += 4;
/*     */ 
/*     */     
/* 435 */     this.options = dc.readI32(buffer, pos, swap);
/* 436 */     pos += 4;
/*     */ 
/*     */     
/* 439 */     pos += JmqiTools.alignToGrain(ptrSize, pos);
/*     */ 
/*     */     
/* 442 */     pos = this.subIdentity.readFromBuffer(buffer, offset, pos, ptrSize, swap, tls);
/* 443 */     int siEnd = this.subIdentity.getEndPosAligned(offset);
/* 444 */     if (siEnd > variableDataEnd) {
/* 445 */       variableDataEnd = siEnd;
/*     */     }
/*     */ 
/*     */     
/* 449 */     pos = this.subName.readFromBuffer(buffer, offset, pos, ptrSize, swap, tls);
/* 450 */     int snEnd = this.subName.getEndPosAligned(offset);
/* 451 */     if (snEnd > variableDataEnd) {
/* 452 */       variableDataEnd = snEnd;
/*     */     }
/*     */ 
/*     */     
/* 456 */     System.arraycopy(buffer, pos, this.subId, 0, 24);
/* 457 */     pos += 24;
/*     */ 
/*     */     
/* 460 */     this.alternateUserId = dc.readMQField(buffer, pos, 12, cp, tls);
/* 461 */     pos += 12;
/*     */ 
/*     */     
/* 464 */     System.arraycopy(buffer, pos, this.alternateSecurityId, 0, 40);
/* 465 */     pos += 40;
/*     */ 
/*     */     
/* 468 */     pos += JmqiTools.alignToGrain(ptrSize, pos);
/*     */ 
/*     */ 
/*     */     
/* 472 */     if (variableDataEnd > pos) {
/* 473 */       pos = variableDataEnd;
/*     */     }
/*     */     
/* 476 */     if (Trace.isOn) {
/* 477 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiUSD", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 478 */           Integer.valueOf(pos));
/*     */     }
/* 480 */     return pos;
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
/* 492 */     fmt.add("version", this.version);
/* 493 */     fmt.add("options", this.options);
/* 494 */     String optionDescription = formatOptions(this.options, getFields(), "lpiUSDO_");
/* 495 */     fmt.add("option flags", optionDescription);
/* 496 */     fmt.add("subIdentity", this.subIdentity);
/* 497 */     fmt.add("subName", this.subName);
/* 498 */     fmt.add("subId", this.subId);
/* 499 */     fmt.add("alternateUserId", this.alternateUserId);
/* 500 */     fmt.add("alternateSecurityId", this.alternateSecurityId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static synchronized Field[] getFields() {
/* 510 */     Reference<Field[]> ref = fieldsRef;
/*     */     
/*     */     Field[] fields;
/* 513 */     if (ref == null || (fields = ref.get()) == null) {
/* 514 */       fieldsRef = (Reference)new SoftReference<>(fields = LpiUSD.class.getFields());
/*     */     }
/*     */     
/* 517 */     if (Trace.isOn) {
/* 518 */       Trace.data("com.ibm.mq.jmqi.system.LpiUSD", "getFields()", "getter", fields);
/*     */     }
/* 520 */     return fields;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\LpiUSD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */