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
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.charset.CharacterCodingException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQHeader
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQHeader.java";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_STRUCLENGTH = 4;
/*     */   private static final int SIZEOF_ENCODING = 4;
/*     */   private static final int SIZEOF_CODEDCHARSETID = 4;
/*     */   private static final int SIZEOF_FORMAT = 8;
/*     */   private static final int SIZEOF_FLAGS = 4;
/*     */   private static final long MQRFH2_ASCII = 5571313732236222496L;
/*     */   private static final long MQSTR_ASCII = 5571325835654209568L;
/*     */   private String strucId;
/*     */   private int version;
/*     */   private int strucLength;
/*     */   
/*     */   static {
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.data("com.ibm.mq.jmqi.MQHeader", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQHeader.java");
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
/*  85 */   private int encoding = 273;
/*  86 */   private int codedCharSetId = 0;
/*     */   
/*     */   private int flags;
/*     */   private boolean formatStringIsValid = true;
/*  90 */   private String formatString = "        ";
/*     */ 
/*     */   
/*     */   private boolean formatLongIsValid = false;
/*     */ 
/*     */   
/*     */   private long formatLong;
/*     */ 
/*     */   
/*     */   private JmqiCodepage formatLongCp;
/*     */ 
/*     */   
/*     */   public static long convertFormatToLong(JmqiEnvironment env, JmqiCodepage cp, String formatP) {
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.entry("com.ibm.mq.jmqi.MQHeader", "convertFormatToLong(JmqiEnvironment,JmqiCodepage,String)", new Object[] { env, cp, formatP });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 109 */     String format = formatP;
/*     */ 
/*     */     
/* 112 */     if (cp.getCCSID() == 1208 && format.equals("MQHRF2  ")) {
/*     */       
/* 114 */       if (Trace.isOn) {
/* 115 */         Trace.exit("com.ibm.mq.jmqi.MQHeader", "convertFormatToLong(JmqiEnvironment,JmqiCodepage,String)", 
/* 116 */             Long.valueOf(5571313732236222496L), 1);
/*     */       }
/*     */       
/* 119 */       return 5571313732236222496L;
/*     */     } 
/* 121 */     if (cp.getCCSID() == 1208 && format.equals("MQSTR   ")) {
/* 122 */       if (Trace.isOn) {
/* 123 */         Trace.exit("com.ibm.mq.jmqi.MQHeader", "convertFormatToLong(JmqiEnvironment,JmqiCodepage,String)", 
/* 124 */             Long.valueOf(5571325835654209568L), 2);
/*     */       }
/*     */       
/* 127 */       return 5571325835654209568L;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 132 */     long formatLong = 0L;
/* 133 */     byte[] formatBytes = null;
/*     */ 
/*     */     
/* 136 */     if (format.length() < 8) {
/* 137 */       format = JmqiTools.left(format, 8);
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 142 */       formatBytes = cp.stringToBytes(format);
/*     */     }
/* 144 */     catch (CharacterCodingException e) {
/* 145 */       if (Trace.isOn) {
/* 146 */         Trace.catchBlock("com.ibm.mq.jmqi.MQHeader", "convertFormatToLong(JmqiEnvironment,JmqiCodepage,String)", e);
/*     */       }
/*     */       
/*     */       try {
/* 150 */         formatBytes = format.getBytes(env.getNativeCharSetName());
/*     */       }
/* 152 */       catch (UnsupportedEncodingException unsupportedEncodingException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 157 */     for (int i = 0; i < 8; i++) {
/* 158 */       formatLong <<= 8L;
/*     */       
/* 160 */       long value = formatBytes[i];
/* 161 */       if (value < 0L) {
/* 162 */         value += 256L;
/*     */       }
/*     */       
/* 165 */       formatLong += value;
/*     */     } 
/*     */     
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.exit("com.ibm.mq.jmqi.MQHeader", "convertFormatToLong(JmqiEnvironment,JmqiCodepage,String)", 
/* 170 */           Long.valueOf(formatLong), 3);
/*     */     }
/* 172 */     return formatLong;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String convertFormatToString(JmqiEnvironment env, JmqiCodepage cp, long formatLong) {
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.entry("com.ibm.mq.jmqi.MQHeader", "convertFormatToString(JmqiEnvironment,JmqiCodepage,long)", new Object[] { env, cp, 
/*     */             
/* 183 */             Long.valueOf(formatLong) });
/*     */     }
/*     */     
/* 186 */     if (cp.getCCSID() == 1208 && formatLong == 5571325835654209568L) {
/*     */       
/* 188 */       if (Trace.isOn) {
/* 189 */         Trace.exit("com.ibm.mq.jmqi.MQHeader", "convertFormatToString(JmqiEnvironment,JmqiCodepage,long)", "MQSTR   ", 1);
/*     */       }
/*     */ 
/*     */       
/* 193 */       return "MQSTR   ";
/*     */     } 
/* 195 */     if (cp.getCCSID() == 1208 && formatLong == 5571313732236222496L) {
/* 196 */       if (Trace.isOn) {
/* 197 */         Trace.exit("com.ibm.mq.jmqi.MQHeader", "convertFormatToString(JmqiEnvironment,JmqiCodepage,long)", "MQHRF2  ", 2);
/*     */       }
/*     */ 
/*     */       
/* 201 */       return "MQHRF2  ";
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 206 */     byte[] formatBytes = new byte[8];
/*     */     
/* 208 */     formatBytes[0] = (byte)(int)(formatLong >> 56L & 0xFFL);
/* 209 */     formatBytes[1] = (byte)(int)(formatLong >> 48L & 0xFFL);
/* 210 */     formatBytes[2] = (byte)(int)(formatLong >> 40L & 0xFFL);
/* 211 */     formatBytes[3] = (byte)(int)(formatLong >> 32L & 0xFFL);
/* 212 */     formatBytes[4] = (byte)(int)(formatLong >> 24L & 0xFFL);
/* 213 */     formatBytes[5] = (byte)(int)(formatLong >> 16L & 0xFFL);
/* 214 */     formatBytes[6] = (byte)(int)(formatLong >> 8L & 0xFFL);
/* 215 */     formatBytes[7] = (byte)(int)(formatLong & 0xFFL);
/*     */     
/* 217 */     String result = null;
/*     */     try {
/* 219 */       result = cp.bytesToString(formatBytes);
/*     */     }
/* 221 */     catch (CharacterCodingException e) {
/* 222 */       if (Trace.isOn) {
/* 223 */         Trace.catchBlock("com.ibm.mq.jmqi.MQHeader", "convertFormatToString(JmqiEnvironment,JmqiCodepage,long)", e);
/*     */       }
/*     */       
/*     */       try {
/* 227 */         result = new String(formatBytes, env.getNativeCharSetName());
/*     */       }
/* 229 */       catch (UnsupportedEncodingException unsupportedEncodingException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit("com.ibm.mq.jmqi.MQHeader", "convertFormatToString(JmqiEnvironment,JmqiCodepage,long)", result, 3);
/*     */     }
/*     */     
/* 238 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQHeader(JmqiEnvironment env) {
/* 247 */     super(env);
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.entry(this, "com.ibm.mq.jmqi.MQHeader", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.exit(this, "com.ibm.mq.jmqi.MQHeader", "<init>(JmqiEnvironment)");
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
/*     */   public static int getSize(JmqiEnvironment env, int ptrsize) {
/* 265 */     int traceRet1 = 32;
/*     */     
/* 267 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) {
/* 275 */     if (Trace.isOn)
/* 276 */       Trace.entry(this, "com.ibm.mq.jmqi.MQHeader", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 277 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 279 */     int traceRet1 = getSize(this.env, ptrSize);
/*     */     
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.exit(this, "com.ibm.mq.jmqi.MQHeader", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 283 */           Integer.valueOf(traceRet1));
/*     */     }
/* 285 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStrucId() {
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.data(this, "com.ibm.mq.jmqi.MQHeader", "getStrucId()", "getter", this.strucId);
/*     */     }
/* 295 */     return this.strucId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrucId(String strucId) {
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.data(this, "com.ibm.mq.jmqi.MQHeader", "setStrucId(String)", "setter", strucId);
/*     */     }
/* 305 */     this.strucId = strucId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.data(this, "com.ibm.mq.jmqi.MQHeader", "getVersion()", "getter", 
/* 315 */           Integer.valueOf(this.version));
/*     */     }
/* 317 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 324 */     if (Trace.isOn) {
/* 325 */       Trace.data(this, "com.ibm.mq.jmqi.MQHeader", "getStrucLength()", "getter", 
/* 326 */           Integer.valueOf(this.strucLength));
/*     */     }
/* 328 */     return this.strucLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrucLength(int strucLength) {
/* 335 */     if (Trace.isOn) {
/* 336 */       Trace.data(this, "com.ibm.mq.jmqi.MQHeader", "setStrucLength(int)", "setter", 
/* 337 */           Integer.valueOf(strucLength));
/*     */     }
/* 339 */     this.strucLength = strucLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 347 */     if (Trace.isOn) {
/* 348 */       Trace.data(this, "com.ibm.mq.jmqi.MQHeader", "setVersion(int)", "setter", 
/* 349 */           Integer.valueOf(version));
/*     */     }
/* 351 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEncoding() {
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.data(this, "com.ibm.mq.jmqi.MQHeader", "getEncoding()", "getter", 
/* 360 */           Integer.valueOf(this.encoding));
/*     */     }
/* 362 */     return this.encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncoding(int encoding) {
/* 369 */     if (Trace.isOn) {
/* 370 */       Trace.data(this, "com.ibm.mq.jmqi.MQHeader", "setEncoding(int)", "setter", 
/* 371 */           Integer.valueOf(encoding));
/*     */     }
/* 373 */     this.encoding = encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 380 */     if (Trace.isOn) {
/* 381 */       Trace.data(this, "com.ibm.mq.jmqi.MQHeader", "getCodedCharSetId()", "getter", 
/* 382 */           Integer.valueOf(this.codedCharSetId));
/*     */     }
/* 384 */     return this.codedCharSetId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int codedCharSetId) {
/* 391 */     if (Trace.isOn) {
/* 392 */       Trace.data(this, "com.ibm.mq.jmqi.MQHeader", "setCodedCharSetId(int)", "setter", 
/* 393 */           Integer.valueOf(codedCharSetId));
/*     */     }
/* 395 */     this.codedCharSetId = codedCharSetId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 402 */     String result = this.formatString;
/* 403 */     if (!this.formatStringIsValid) {
/* 404 */       if (this.formatLongIsValid) {
/* 405 */         result = this.formatString = convertFormatToString(this.env, this.formatLongCp, this.formatLong);
/* 406 */         this.formatStringIsValid = true;
/*     */       } else {
/* 408 */         result = this.formatString = "        ";
/* 409 */         this.formatStringIsValid = true;
/*     */       } 
/*     */     }
/*     */     
/* 413 */     if (Trace.isOn) {
/* 414 */       Trace.data(this, "com.ibm.mq.jmqi.MQHeader", "getFormat()", "getter", result);
/*     */     }
/* 416 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(String format) {
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.data(this, "com.ibm.mq.jmqi.MQHeader", "setFormat(String)", "setter", format);
/*     */     }
/* 426 */     this.formatString = format;
/* 427 */     this.formatStringIsValid = true;
/* 428 */     this.formatLongIsValid = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getFormatLong(JmqiCodepage cp) {
/* 437 */     if (Trace.isOn) {
/* 438 */       Trace.entry(this, "com.ibm.mq.jmqi.MQHeader", "getFormatLong(JmqiCodepage)", new Object[] { cp });
/*     */     }
/*     */     
/* 441 */     long result = this.formatLong;
/* 442 */     if (!this.formatLongIsValid && 
/* 443 */       this.formatStringIsValid) {
/* 444 */       this.formatLongCp = cp;
/* 445 */       result = this.formatLong = convertFormatToLong(this.env, cp, this.formatString);
/* 446 */       this.formatLongIsValid = true;
/*     */     } 
/*     */ 
/*     */     
/* 450 */     if (Trace.isOn) {
/* 451 */       Trace.exit(this, "com.ibm.mq.jmqi.MQHeader", "getFormatLong(JmqiCodepage)", 
/* 452 */           Long.valueOf(result));
/*     */     }
/* 454 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormatLong(JmqiCodepage cp, long formatLong) {
/* 462 */     if (Trace.isOn) {
/* 463 */       Trace.entry(this, "com.ibm.mq.jmqi.MQHeader", "setFormatLong(JmqiCodepage,long)", new Object[] { cp, 
/* 464 */             Long.valueOf(formatLong) });
/*     */     }
/* 466 */     this.formatLong = formatLong;
/* 467 */     this.formatLongCp = cp;
/* 468 */     this.formatLongIsValid = true;
/* 469 */     this.formatStringIsValid = false;
/*     */     
/* 471 */     if (Trace.isOn) {
/* 472 */       Trace.exit(this, "com.ibm.mq.jmqi.MQHeader", "setFormatLong(JmqiCodepage,long)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlags() {
/* 481 */     if (Trace.isOn) {
/* 482 */       Trace.data(this, "com.ibm.mq.jmqi.MQHeader", "getFlags()", "getter", Integer.valueOf(this.flags));
/*     */     }
/*     */     
/* 485 */     return this.flags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int flags) {
/* 492 */     if (Trace.isOn) {
/* 493 */       Trace.data(this, "com.ibm.mq.jmqi.MQHeader", "setFlags(int)", "setter", 
/* 494 */           Integer.valueOf(flags));
/*     */     }
/* 496 */     this.flags = flags;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 505 */     if (Trace.isOn) {
/* 506 */       Trace.entry(this, "com.ibm.mq.jmqi.MQHeader", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 508 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 510 */     int pos = offset;
/* 511 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 513 */     dc.writeMQField(this.strucId, buffer, pos, 4, cp, tls);
/* 514 */     pos += 4;
/*     */     
/* 516 */     dc.writeI32(this.version, buffer, pos, swap);
/* 517 */     pos += 4;
/*     */     
/* 519 */     dc.writeI32(this.strucLength, buffer, pos, swap);
/* 520 */     pos += 4;
/*     */     
/* 522 */     dc.writeI32(this.encoding, buffer, pos, swap);
/* 523 */     pos += 4;
/*     */     
/* 525 */     dc.writeI32(this.codedCharSetId, buffer, pos, swap);
/* 526 */     pos += 4;
/*     */     
/* 528 */     long value = getFormatLong(cp);
/* 529 */     dc.writeI64(value, buffer, pos, false);
/* 530 */     pos += 8;
/*     */     
/* 532 */     dc.writeI32(this.flags, buffer, pos, swap);
/* 533 */     pos += 4;
/*     */     
/* 535 */     if (Trace.isOn) {
/* 536 */       Trace.exit(this, "com.ibm.mq.jmqi.MQHeader", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 537 */           Integer.valueOf(pos));
/*     */     }
/* 539 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 548 */     if (Trace.isOn) {
/* 549 */       Trace.entry(this, "com.ibm.mq.jmqi.MQHeader", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 551 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 553 */     int traceRet1 = readFromBuffer(buffer, offset, ptrSize, swap, cp, tls, true);
/*     */     
/* 555 */     if (Trace.isOn) {
/* 556 */       Trace.exit(this, "com.ibm.mq.jmqi.MQHeader", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 557 */           Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 560 */     return traceRet1;
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
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls, boolean readFully) throws JmqiException {
/* 577 */     if (Trace.isOn) {
/* 578 */       Trace.entry(this, "com.ibm.mq.jmqi.MQHeader", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls,boolean)", new Object[] { buffer, 
/*     */             
/* 580 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls, 
/* 581 */             Boolean.valueOf(readFully) });
/*     */     }
/* 583 */     int pos = offset;
/* 584 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 585 */     if (readFully) {
/*     */       
/* 587 */       this.strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 588 */       pos += 4;
/*     */       
/* 590 */       this.version = dc.readI32(buffer, pos, swap);
/* 591 */       pos += 4;
/*     */     } else {
/* 593 */       pos += 8;
/*     */     } 
/*     */     
/* 596 */     this.strucLength = dc.readI32(buffer, pos, swap);
/* 597 */     pos += 4;
/*     */     
/* 599 */     this.encoding = dc.readI32(buffer, pos, swap);
/* 600 */     pos += 4;
/*     */     
/* 602 */     this.codedCharSetId = dc.readI32(buffer, pos, swap);
/* 603 */     pos += 4;
/*     */     
/* 605 */     this.formatLong = dc.readI64(buffer, pos, false);
/* 606 */     this.formatStringIsValid = false;
/* 607 */     this.formatLongIsValid = true;
/* 608 */     this.formatLongCp = cp;
/* 609 */     pos += 8;
/* 610 */     if (readFully)
/*     */     {
/* 612 */       this.flags = dc.readI32(buffer, pos, swap);
/*     */     }
/* 614 */     pos += 4;
/*     */     
/* 616 */     if (Trace.isOn) {
/* 617 */       Trace.exit(this, "com.ibm.mq.jmqi.MQHeader", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls,boolean)", 
/*     */           
/* 619 */           Integer.valueOf(pos));
/*     */     }
/* 621 */     return pos;
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
/* 633 */     fmt.add("strucId", this.strucId);
/* 634 */     fmt.add("version", this.version);
/* 635 */     fmt.add("strucLength", this.strucLength);
/* 636 */     fmt.add("encoding", this.encoding);
/* 637 */     fmt.add("codedCharSetId", this.codedCharSetId);
/* 638 */     fmt.add("format", getFormat());
/* 639 */     fmt.add("flags", this.flags);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQHeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */