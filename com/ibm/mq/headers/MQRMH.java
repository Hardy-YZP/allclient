/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.headers.internal.Header;
/*     */ import com.ibm.mq.headers.internal.HeaderField;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQRMH
/*     */   extends Header
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQRMH.java";
/*     */   public static final int SIZE = 108;
/*     */   
/*     */   static {
/*  69 */     if (Trace.isOn) {
/*  70 */       Trace.data("com.ibm.mq.headers.MQRMH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQRMH.java");
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
/*  82 */   static final HeaderType TYPE = new HeaderType("MQRMH");
/*     */   
/*  84 */   static final HeaderField StrucId = TYPE.addMQChar("StrucId", "RMH ");
/*  85 */   static final HeaderField Version = TYPE.addMQLong("Version", 1);
/*  86 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength");
/*  87 */   static final HeaderField Encoding = TYPE.addMQLong("Encoding");
/*  88 */   static final HeaderField CodedCharSetId = TYPE.addMQLong("CodedCharSetId");
/*  89 */   static final HeaderField Format = TYPE.addMQChar("Format", "        ");
/*  90 */   static final HeaderField Flags = TYPE.addMQLong("Flags");
/*  91 */   static final HeaderField ObjectType = TYPE.addMQChar("ObjectType", 8);
/*  92 */   static final HeaderField ObjectInstanceId = TYPE.addMQByte("ObjectInstanceId", 24);
/*  93 */   static final HeaderField SrcEnvLength = TYPE.addMQLong("SrcEnvLength");
/*  94 */   static final HeaderField SrcEnvOffset = TYPE.addMQLong("SrcEnvOffset");
/*  95 */   static final HeaderField SrcNameLength = TYPE.addMQLong("SrcNameLength");
/*  96 */   static final HeaderField SrcNameOffset = TYPE.addMQLong("SrcNameOffset");
/*  97 */   static final HeaderField DestEnvLength = TYPE.addMQLong("DestEnvLength");
/*  98 */   static final HeaderField DestEnvOffset = TYPE.addMQLong("DestEnvOffset");
/*  99 */   static final HeaderField DestNameLength = TYPE.addMQLong("DestNameLength");
/* 100 */   static final HeaderField DestNameOffset = TYPE.addMQLong("DestNameOffset");
/* 101 */   static final HeaderField DataLogicalLength = TYPE.addMQLong("DataLogicalLength");
/* 102 */   static final HeaderField DataLogicalOffset = TYPE.addMQLong("DataLogicalOffset");
/* 103 */   static final HeaderField DataLogicalOffset2 = TYPE.addMQLong("DataLogicalOffset2");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQRMH() {
/* 109 */     super(TYPE);
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.entry(this, "com.ibm.mq.headers.MQRMH", "<init>()");
/*     */     }
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.mq.headers.MQRMH", "<init>()");
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
/*     */   public MQRMH(DataInput message) throws MQDataException, IOException {
/* 127 */     this();
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.entry(this, "com.ibm.mq.headers.MQRMH", "<init>(DataInput)", new Object[] { message });
/*     */     }
/* 131 */     read(message);
/*     */     
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.exit(this, "com.ibm.mq.headers.MQRMH", "<init>(DataInput)");
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
/*     */   public MQRMH(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 150 */     this();
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.entry(this, "com.ibm.mq.headers.MQRMH", "<init>(DataInput,int,int)", new Object[] { message, 
/* 153 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 156 */       read(message, encoding, characterSet);
/*     */     }
/* 158 */     catch (MQDataException mde) {
/* 159 */       if (Trace.isOn) {
/* 160 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQRMH", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/*     */       
/* 163 */       if (Trace.isOn) {
/* 164 */         Trace.throwing(this, "com.ibm.mq.headers.MQRMH", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/* 166 */       throw mde;
/*     */     }
/* 168 */     catch (IOException ioe) {
/* 169 */       if (Trace.isOn) {
/* 170 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQRMH", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 172 */       if (Trace.isOn) {
/* 173 */         Trace.throwing(this, "com.ibm.mq.headers.MQRMH", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 175 */       throw ioe;
/*     */     }
/* 177 */     catch (Exception e) {
/* 178 */       if (Trace.isOn) {
/* 179 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQRMH", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/* 181 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 182 */       if (Trace.isOn) {
/* 183 */         Trace.throwing(this, "com.ibm.mq.headers.MQRMH", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 186 */       throw traceRet1;
/*     */     } 
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.exit(this, "com.ibm.mq.headers.MQRMH", "<init>(DataInput,int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStrucId() {
/* 200 */     String traceRet1 = getStringValue(StrucId);
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getStrucId()", "getter", traceRet1);
/*     */     }
/* 204 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 213 */     int traceRet1 = getIntValue(Version);
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getVersion()", "getter", 
/* 216 */           Integer.valueOf(traceRet1));
/*     */     }
/* 218 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 227 */     int traceRet1 = getIntValue(StrucLength);
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getStrucLength()", "getter", 
/* 230 */           Integer.valueOf(traceRet1));
/*     */     }
/* 232 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEncoding() {
/* 241 */     int traceRet1 = getIntValue(Encoding);
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getEncoding()", "getter", 
/* 244 */           Integer.valueOf(traceRet1));
/*     */     }
/* 246 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncoding(int value) {
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "setEncoding(int)", "setter", 
/* 257 */           Integer.valueOf(value));
/*     */     }
/* 259 */     setIntValue(Encoding, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 268 */     int traceRet1 = getIntValue(CodedCharSetId);
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getCodedCharSetId()", "getter", 
/* 271 */           Integer.valueOf(traceRet1));
/*     */     }
/* 273 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int value) {
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "setCodedCharSetId(int)", "setter", 
/* 284 */           Integer.valueOf(value));
/*     */     }
/* 286 */     setIntValue(CodedCharSetId, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 295 */     String traceRet1 = getStringValue(Format);
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getFormat()", "getter", traceRet1);
/*     */     }
/* 299 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(String value) {
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "setFormat(String)", "setter", value);
/*     */     }
/* 311 */     setStringValue(Format, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlags() {
/* 320 */     int traceRet1 = getIntValue(Flags);
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getFlags()", "getter", 
/* 323 */           Integer.valueOf(traceRet1));
/*     */     }
/* 325 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int value) {
/* 334 */     if (Trace.isOn) {
/* 335 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "setFlags(int)", "setter", 
/* 336 */           Integer.valueOf(value));
/*     */     }
/* 338 */     setIntValue(Flags, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getObjectType() {
/* 347 */     String traceRet1 = getStringValue(ObjectType);
/* 348 */     if (Trace.isOn) {
/* 349 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getObjectType()", "getter", traceRet1);
/*     */     }
/* 351 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectType(String value) {
/* 360 */     if (Trace.isOn) {
/* 361 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "setObjectType(String)", "setter", value);
/*     */     }
/* 363 */     setStringValue(ObjectType, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getObjectInstanceId() {
/* 372 */     byte[] traceRet1 = getBytesValue(ObjectInstanceId);
/* 373 */     if (Trace.isOn) {
/* 374 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getObjectInstanceId()", "getter", traceRet1);
/*     */     }
/* 376 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectInstanceId(byte[] value) {
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "setObjectInstanceId(byte [ ])", "setter", value);
/*     */     }
/*     */     
/* 389 */     setBytesValue(ObjectInstanceId, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSrcEnvLength() {
/* 398 */     int traceRet1 = getIntValue(SrcEnvLength);
/* 399 */     if (Trace.isOn) {
/* 400 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getSrcEnvLength()", "getter", 
/* 401 */           Integer.valueOf(traceRet1));
/*     */     }
/* 403 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSrcEnvLength(int value) {
/* 412 */     if (Trace.isOn) {
/* 413 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "setSrcEnvLength(int)", "setter", 
/* 414 */           Integer.valueOf(value));
/*     */     }
/* 416 */     setIntValue(SrcEnvLength, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSrcEnvOffset() {
/* 425 */     int traceRet1 = getIntValue(SrcEnvOffset);
/* 426 */     if (Trace.isOn) {
/* 427 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getSrcEnvOffset()", "getter", 
/* 428 */           Integer.valueOf(traceRet1));
/*     */     }
/* 430 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSrcEnvOffset(int value) {
/* 439 */     if (Trace.isOn) {
/* 440 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "setSrcEnvOffset(int)", "setter", 
/* 441 */           Integer.valueOf(value));
/*     */     }
/* 443 */     setIntValue(SrcEnvOffset, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSrcNameLength() {
/* 452 */     int traceRet1 = getIntValue(SrcNameLength);
/* 453 */     if (Trace.isOn) {
/* 454 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getSrcNameLength()", "getter", 
/* 455 */           Integer.valueOf(traceRet1));
/*     */     }
/* 457 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSrcNameLength(int value) {
/* 466 */     if (Trace.isOn) {
/* 467 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "setSrcNameLength(int)", "setter", 
/* 468 */           Integer.valueOf(value));
/*     */     }
/* 470 */     setIntValue(SrcNameLength, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSrcNameOffset() {
/* 479 */     int traceRet1 = getIntValue(SrcNameOffset);
/* 480 */     if (Trace.isOn) {
/* 481 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getSrcNameOffset()", "getter", 
/* 482 */           Integer.valueOf(traceRet1));
/*     */     }
/* 484 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSrcNameOffset(int value) {
/* 493 */     if (Trace.isOn) {
/* 494 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "setSrcNameOffset(int)", "setter", 
/* 495 */           Integer.valueOf(value));
/*     */     }
/* 497 */     setIntValue(SrcNameOffset, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDestEnvLength() {
/* 506 */     int traceRet1 = getIntValue(DestEnvLength);
/* 507 */     if (Trace.isOn) {
/* 508 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getDestEnvLength()", "getter", 
/* 509 */           Integer.valueOf(traceRet1));
/*     */     }
/* 511 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDestEnvLength(int value) {
/* 520 */     if (Trace.isOn) {
/* 521 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "setDestEnvLength(int)", "setter", 
/* 522 */           Integer.valueOf(value));
/*     */     }
/* 524 */     setIntValue(DestEnvLength, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDestEnvOffset() {
/* 533 */     int traceRet1 = getIntValue(DestEnvOffset);
/* 534 */     if (Trace.isOn) {
/* 535 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getDestEnvOffset()", "getter", 
/* 536 */           Integer.valueOf(traceRet1));
/*     */     }
/* 538 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDestEnvOffset(int value) {
/* 547 */     if (Trace.isOn) {
/* 548 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "setDestEnvOffset(int)", "setter", 
/* 549 */           Integer.valueOf(value));
/*     */     }
/* 551 */     setIntValue(DestEnvOffset, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDestNameLength() {
/* 560 */     int traceRet1 = getIntValue(DestNameLength);
/* 561 */     if (Trace.isOn) {
/* 562 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getDestNameLength()", "getter", 
/* 563 */           Integer.valueOf(traceRet1));
/*     */     }
/* 565 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDestNameLength(int value) {
/* 574 */     if (Trace.isOn) {
/* 575 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "setDestNameLength(int)", "setter", 
/* 576 */           Integer.valueOf(value));
/*     */     }
/* 578 */     setIntValue(DestNameLength, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDestNameOffset() {
/* 587 */     int traceRet1 = getIntValue(DestNameOffset);
/* 588 */     if (Trace.isOn) {
/* 589 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getDestNameOffset()", "getter", 
/* 590 */           Integer.valueOf(traceRet1));
/*     */     }
/* 592 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDestNameOffset(int value) {
/* 601 */     if (Trace.isOn) {
/* 602 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "setDestNameOffset(int)", "setter", 
/* 603 */           Integer.valueOf(value));
/*     */     }
/* 605 */     setIntValue(DestNameOffset, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDataLogicalLength() {
/* 614 */     int traceRet1 = getIntValue(DataLogicalLength);
/* 615 */     if (Trace.isOn) {
/* 616 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getDataLogicalLength()", "getter", 
/* 617 */           Integer.valueOf(traceRet1));
/*     */     }
/* 619 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDataLogicalLength(int value) {
/* 628 */     if (Trace.isOn) {
/* 629 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "setDataLogicalLength(int)", "setter", 
/* 630 */           Integer.valueOf(value));
/*     */     }
/* 632 */     setIntValue(DataLogicalLength, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDataLogicalOffset() {
/* 641 */     int traceRet1 = getIntValue(DataLogicalOffset);
/* 642 */     if (Trace.isOn) {
/* 643 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getDataLogicalOffset()", "getter", 
/* 644 */           Integer.valueOf(traceRet1));
/*     */     }
/* 646 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDataLogicalOffset(int value) {
/* 655 */     if (Trace.isOn) {
/* 656 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "setDataLogicalOffset(int)", "setter", 
/* 657 */           Integer.valueOf(value));
/*     */     }
/* 659 */     setIntValue(DataLogicalOffset, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDataLogicalOffset2() {
/* 668 */     int traceRet1 = getIntValue(DataLogicalOffset2);
/* 669 */     if (Trace.isOn) {
/* 670 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "getDataLogicalOffset2()", "getter", 
/* 671 */           Integer.valueOf(traceRet1));
/*     */     }
/* 673 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDataLogicalOffset2(int value) {
/* 682 */     if (Trace.isOn) {
/* 683 */       Trace.data(this, "com.ibm.mq.headers.MQRMH", "setDataLogicalOffset2(int)", "setter", 
/* 684 */           Integer.valueOf(value));
/*     */     }
/* 686 */     setIntValue(DataLogicalOffset2, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int write(DataOutput message) throws IOException {
/* 694 */     if (Trace.isOn) {
/* 695 */       Trace.entry(this, "com.ibm.mq.headers.MQRMH", "write(DataOutput)", new Object[] { message });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 700 */     int strucLength = this.type.size(this);
/*     */     
/* 702 */     strucLength += getSrcEnvLength();
/* 703 */     strucLength += getSrcNameLength();
/* 704 */     strucLength += getDestEnvLength();
/* 705 */     strucLength += getDestNameLength();
/*     */     
/* 707 */     setIntValue(StrucLength, strucLength);
/*     */ 
/*     */     
/* 710 */     int traceRet1 = super.write(message);
/* 711 */     if (Trace.isOn) {
/* 712 */       Trace.exit(this, "com.ibm.mq.headers.MQRMH", "write(DataOutput)", 
/* 713 */           Integer.valueOf(traceRet1));
/*     */     }
/* 715 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQRMH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */