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
/*     */ public class MQDLH
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQDLH.java";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_REASON = 4;
/*     */   private static final int SIZEOF_DESTQNAME = 48;
/*     */   private static final int SIZEOF_DESTQMGRNAME = 48;
/*     */   private static final int SIZEOF_ENCODING = 4;
/*     */   private static final int SIZEOF_CCSID = 4;
/*     */   private static final int SIZEOF_FORMAT = 8;
/*     */   private static final int SIZEOF_PUTAPPLTYPE = 4;
/*     */   private static final int SIZEOF_PUTAPPLNAME = 28;
/*     */   private static final int SIZEOF_PUTDATE = 8;
/*     */   private static final int SIZEOF_PUTTIME = 8;
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.jmqi.MQDLH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQDLH.java");
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
/*  78 */   private int version = 1;
/*  79 */   private int reason = 0;
/*  80 */   private String destQName = "";
/*  81 */   private String destQMgrName = "";
/*  82 */   private int encoding = 273;
/*  83 */   private int codedCharSetId = 0;
/*  84 */   private String format = "        ";
/*  85 */   private int putApplType = 0;
/*  86 */   private String putApplName = "";
/*  87 */   private String putDate = "";
/*  88 */   private String putTime = "";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int DEFAULT_CCSID = 1208;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 100 */     int size = 172;
/*     */ 
/*     */ 
/*     */     
/* 104 */     return size;
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
/* 116 */     int size = 0;
/* 117 */     switch (version) {
/*     */       case 1:
/* 119 */         size = getSizeV1(ptrSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 127 */         return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 2195, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 136 */     return getSize(this.env, this.version, ptrSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQDLH(JmqiEnvironment env) {
/* 143 */     super(env);
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.entry(this, "com.ibm.mq.jmqi.MQDLH", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.exit(this, "com.ibm.mq.jmqi.MQDLH", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "getVersion()", "getter", Integer.valueOf(this.version));
/*     */     }
/*     */     
/* 162 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "setVersion(int)", "setter", 
/* 172 */           Integer.valueOf(version));
/*     */     }
/* 174 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEncoding() {
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "getEncoding()", "getter", 
/* 183 */           Integer.valueOf(this.encoding));
/*     */     }
/* 185 */     return this.encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncoding(int encoding) {
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "setEncoding(int)", "setter", 
/* 194 */           Integer.valueOf(encoding));
/*     */     }
/* 196 */     this.encoding = encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReason() {
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "getReason()", "getter", Integer.valueOf(this.reason));
/*     */     }
/* 206 */     return this.reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReason(int reason) {
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "setReason(int)", "setter", 
/* 215 */           Integer.valueOf(reason));
/*     */     }
/* 217 */     this.reason = reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDestQName() {
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "getDestQName()", "getter", this.destQName);
/*     */     }
/* 227 */     return this.destQName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDestQName(String destQName) {
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "setDestQName(String)", "setter", destQName);
/*     */     }
/* 237 */     this.destQName = destQName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDestQMgrName() {
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "getDestQMgrName()", "getter", this.destQMgrName);
/*     */     }
/* 247 */     return this.destQMgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDestQMgrName(String destQMgrName) {
/* 254 */     if (Trace.isOn) {
/* 255 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "setDestQMgrName(String)", "setter", destQMgrName);
/*     */     }
/*     */     
/* 258 */     this.destQMgrName = destQMgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "getCodedCharSetId()", "getter", 
/* 267 */           Integer.valueOf(this.codedCharSetId));
/*     */     }
/* 269 */     return this.codedCharSetId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int codedCharSetId) {
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "setCodedCharSetId(int)", "setter", 
/* 278 */           Integer.valueOf(codedCharSetId));
/*     */     }
/* 280 */     this.codedCharSetId = codedCharSetId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 287 */     if (Trace.isOn) {
/* 288 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "getFormat()", "getter", this.format);
/*     */     }
/* 290 */     return this.format;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(String format) {
/* 297 */     if (Trace.isOn) {
/* 298 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "setFormat(String)", "setter", format);
/*     */     }
/* 300 */     this.format = format;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPutApplType() {
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "getPutApplType()", "getter", 
/* 310 */           Integer.valueOf(this.putApplType));
/*     */     }
/* 312 */     return this.putApplType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPutApplType(int putApplType) {
/* 319 */     if (Trace.isOn) {
/* 320 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "setPutApplType(int)", "setter", 
/* 321 */           Integer.valueOf(putApplType));
/*     */     }
/* 323 */     this.putApplType = putApplType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPutApplName() {
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "getPutApplName()", "getter", this.putApplName);
/*     */     }
/* 333 */     return this.putApplName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPutApplName(String putApplName) {
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "setPutApplName(String)", "setter", putApplName);
/*     */     }
/* 343 */     this.putApplName = putApplName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPutDate() {
/* 350 */     if (Trace.isOn) {
/* 351 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "getPutDate()", "getter", this.putDate);
/*     */     }
/* 353 */     return this.putDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPutDate(String putDate) {
/* 360 */     if (Trace.isOn) {
/* 361 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "setPutDate(String)", "setter", putDate);
/*     */     }
/* 363 */     this.putDate = putDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPutTime() {
/* 370 */     if (Trace.isOn) {
/* 371 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "getPutTime()", "getter", this.putTime);
/*     */     }
/* 373 */     return this.putTime;
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
/*     */   public void setPutDateAndTime(int year, int month, int day, int hour, int minute, int second, int millis) {
/* 389 */     if (Trace.isOn) {
/* 390 */       Trace.entry(this, "com.ibm.mq.jmqi.MQDLH", "setPutDateAndTime(int,int,int,int,int,int,int)", new Object[] {
/* 391 */             Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day), 
/* 392 */             Integer.valueOf(hour), Integer.valueOf(minute), Integer.valueOf(second), 
/* 393 */             Integer.valueOf(millis)
/*     */           });
/*     */     }
/* 396 */     StringBuffer buffer = new StringBuffer();
/* 397 */     buffer.append(JmqiTools.fix(year, 4));
/* 398 */     buffer.append(JmqiTools.fix(month, 2));
/* 399 */     buffer.append(JmqiTools.fix(day, 2));
/* 400 */     this.putDate = buffer.toString();
/*     */     
/* 402 */     buffer = new StringBuffer();
/* 403 */     buffer.append(JmqiTools.fix(hour, 2));
/* 404 */     buffer.append(JmqiTools.fix(minute, 2));
/* 405 */     buffer.append(JmqiTools.fix(second, 2));
/* 406 */     buffer.append(JmqiTools.fix(millis, 2));
/* 407 */     this.putTime = buffer.toString();
/*     */     
/* 409 */     if (Trace.isOn) {
/* 410 */       Trace.exit(this, "com.ibm.mq.jmqi.MQDLH", "setPutDateAndTime(int,int,int,int,int,int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPutTime(String putTime) {
/* 420 */     if (Trace.isOn) {
/* 421 */       Trace.data(this, "com.ibm.mq.jmqi.MQDLH", "setPutTime(String)", "setter", putTime);
/*     */     }
/* 423 */     this.putTime = putTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 432 */     if (Trace.isOn) {
/* 433 */       Trace.entry(this, "com.ibm.mq.jmqi.MQDLH", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 435 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 437 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 438 */     int pos = offset;
/*     */     
/* 440 */     dc.writeMQField("DLH ", buffer, pos, 4, cp, tls);
/* 441 */     pos += 4;
/*     */     
/* 443 */     dc.writeI32(this.version, buffer, pos, swap);
/* 444 */     pos += 4;
/*     */     
/* 446 */     dc.writeI32(this.reason, buffer, pos, swap);
/* 447 */     pos += 4;
/*     */     
/* 449 */     dc.writeMQField(this.destQName, buffer, pos, 48, cp, tls);
/* 450 */     pos += 48;
/*     */     
/* 452 */     dc.writeMQField(this.destQMgrName, buffer, pos, 48, cp, tls);
/* 453 */     pos += 48;
/*     */     
/* 455 */     dc.writeI32(this.encoding, buffer, pos, swap);
/* 456 */     pos += 4;
/*     */     
/* 458 */     dc.writeI32(this.codedCharSetId, buffer, pos, swap);
/* 459 */     pos += 4;
/*     */     
/* 461 */     dc.writeMQField(this.format, buffer, pos, 8, cp, tls);
/* 462 */     pos += 8;
/*     */     
/* 464 */     dc.writeI32(this.putApplType, buffer, pos, swap);
/* 465 */     pos += 4;
/*     */     
/* 467 */     dc.writeMQField(this.putApplName, buffer, pos, 28, cp, tls);
/* 468 */     pos += 28;
/*     */     
/* 470 */     dc.writeMQField(this.putDate, buffer, pos, 8, cp, tls);
/* 471 */     pos += 8;
/*     */     
/* 473 */     dc.writeMQField(this.putTime, buffer, pos, 8, cp, tls);
/* 474 */     pos += 8;
/*     */     
/* 476 */     if (Trace.isOn) {
/* 477 */       Trace.exit(this, "com.ibm.mq.jmqi.MQDLH", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
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
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 489 */     if (Trace.isOn) {
/* 490 */       Trace.entry(this, "com.ibm.mq.jmqi.MQDLH", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 492 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 494 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 495 */     int pos = offset;
/*     */     
/* 497 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 498 */     if (!strucId.equals("DLH ")) {
/*     */       
/* 500 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2141, null);
/*     */ 
/*     */       
/* 503 */       if (Trace.isOn) {
/* 504 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQDLH", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*     */       }
/*     */       
/* 507 */       throw traceRet1;
/*     */     } 
/* 509 */     pos += 4;
/*     */ 
/*     */     
/* 512 */     this.version = dc.readI32(buffer, pos, swap);
/* 513 */     pos += 4;
/*     */ 
/*     */     
/* 516 */     this.reason = dc.readI32(buffer, pos, swap);
/* 517 */     pos += 4;
/*     */ 
/*     */     
/* 520 */     this.destQName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 521 */     pos += 48;
/*     */ 
/*     */     
/* 524 */     this.destQMgrName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 525 */     pos += 48;
/*     */ 
/*     */     
/* 528 */     this.encoding = dc.readI32(buffer, pos, swap);
/* 529 */     pos += 4;
/*     */ 
/*     */     
/* 532 */     this.codedCharSetId = dc.readI32(buffer, pos, swap);
/* 533 */     pos += 4;
/*     */ 
/*     */     
/* 536 */     this.format = dc.readMQField(buffer, pos, 8, cp, tls);
/* 537 */     pos += 8;
/*     */ 
/*     */     
/* 540 */     this.putApplType = dc.readI32(buffer, pos, swap);
/* 541 */     pos += 4;
/*     */ 
/*     */     
/* 544 */     this.putApplName = dc.readMQField(buffer, pos, 28, cp, tls);
/* 545 */     pos += 28;
/*     */ 
/*     */     
/* 548 */     this.putDate = dc.readMQField(buffer, pos, 8, cp, tls);
/* 549 */     pos += 8;
/*     */ 
/*     */     
/* 552 */     this.putTime = dc.readMQField(buffer, pos, 8, cp, tls);
/* 553 */     pos += 8;
/*     */     
/* 555 */     if (Trace.isOn) {
/* 556 */       Trace.exit(this, "com.ibm.mq.jmqi.MQDLH", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 557 */           Integer.valueOf(pos));
/*     */     }
/* 559 */     return pos;
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
/* 571 */     fmt.add("version", this.version);
/* 572 */     fmt.add("reason", this.reason);
/* 573 */     fmt.add("destQName", this.destQName);
/* 574 */     fmt.add("destQMgrName", this.destQMgrName);
/* 575 */     fmt.add("encoding", this.encoding);
/* 576 */     fmt.add("codedCharSetId", this.codedCharSetId);
/* 577 */     fmt.add("format", this.format);
/* 578 */     fmt.add("putApplType", this.putApplType);
/* 579 */     fmt.add("putApplName", this.putApplName);
/* 580 */     fmt.add("putDate", this.putDate);
/* 581 */     fmt.add("putTime", this.putTime);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQDLH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */