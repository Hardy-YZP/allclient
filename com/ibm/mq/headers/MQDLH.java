/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.headers.internal.Header;
/*     */ import com.ibm.mq.headers.internal.HeaderField;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
/*     */ import com.ibm.mq.headers.internal.MessageWrapper;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
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
/*     */ public class MQDLH
/*     */   extends Header
/*     */   implements MQChainable
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQDLH.java";
/*     */   public static final int SIZE = 172;
/*     */   
/*     */   static {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.data("com.ibm.mq.headers.MQDLH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQDLH.java");
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
/*  73 */   static final HeaderType TYPE = new HeaderType("MQDLH");
/*     */   
/*  75 */   static final HeaderField StrucId = TYPE.addMQChar("StrucId", "DLH ");
/*  76 */   static final HeaderField Version = TYPE.addMQLong("Version", 1);
/*  77 */   static final HeaderField Reason = TYPE.addMQLong("Reason");
/*  78 */   static final HeaderField DestQName = TYPE.addMQChar("DestQName", 48);
/*  79 */   static final HeaderField DestQMgrName = TYPE.addMQChar("DestQMgrName", 48);
/*  80 */   static final HeaderField Encoding = TYPE.addMQLong("Encoding");
/*  81 */   static final HeaderField CodedCharSetId = TYPE.addMQLong("CodedCharSetId");
/*  82 */   static final HeaderField Format = TYPE.addMQChar("Format", 8);
/*  83 */   static final HeaderField PutApplType = TYPE.addMQLong("PutApplType");
/*  84 */   static final HeaderField PutApplName = TYPE.addMQChar("PutApplName", 28);
/*  85 */   static final HeaderField PutDate = TYPE.addMQChar("PutDate", 8);
/*  86 */   static final HeaderField PutTime = TYPE.addMQChar("PutTime", 8);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQDLH() {
/*  92 */     super(TYPE);
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.entry(this, "com.ibm.mq.headers.MQDLH", "<init>()");
/*     */     }
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.exit(this, "com.ibm.mq.headers.MQDLH", "<init>()");
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
/*     */   public MQDLH(DataInput message) throws MQDataException, IOException {
/* 110 */     this();
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.entry(this, "com.ibm.mq.headers.MQDLH", "<init>(DataInput)", new Object[] { message });
/*     */     }
/* 114 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.exit(this, "com.ibm.mq.headers.MQDLH", "<init>(DataInput)");
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
/*     */   public MQDLH(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 133 */     this();
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.entry(this, "com.ibm.mq.headers.MQDLH", "<init>(DataInput,int,int)", new Object[] { message, 
/* 136 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 139 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 141 */     catch (Exception e) {
/* 142 */       if (Trace.isOn) {
/* 143 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQDLH", "<init>(DataInput,int,int)", e);
/*     */       }
/*     */       
/* 146 */       MQDataException traceRet1 = MQDataException.getMQDataException(e);
/* 147 */       if (Trace.isOn) {
/* 148 */         Trace.throwing(this, "com.ibm.mq.headers.MQDLH", "<init>(DataInput,int,int)", traceRet1);
/*     */       }
/* 150 */       throw traceRet1;
/*     */     } 
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.exit(this, "com.ibm.mq.headers.MQDLH", "<init>(DataInput,int,int)");
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
/* 164 */     String traceRet1 = getStringValue(StrucId);
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "getStrucId()", "getter", traceRet1);
/*     */     }
/* 168 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 177 */     int traceRet1 = getIntValue(Version);
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "getVersion()", "getter", 
/* 180 */           Integer.valueOf(traceRet1));
/*     */     }
/* 182 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReason() {
/* 191 */     int traceRet1 = getIntValue(Reason);
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "getReason()", "getter", 
/* 194 */           Integer.valueOf(traceRet1));
/*     */     }
/* 196 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReason(int value) {
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "setReason(int)", "setter", 
/* 207 */           Integer.valueOf(value));
/*     */     }
/* 209 */     setIntValue(Reason, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDestQName() {
/* 218 */     String traceRet1 = getStringValue(DestQName);
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "getDestQName()", "getter", traceRet1);
/*     */     }
/* 222 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDestQName(String value) {
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "setDestQName(String)", "setter", value);
/*     */     }
/* 234 */     setStringValue(DestQName, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDestQMgrName() {
/* 243 */     String traceRet1 = getStringValue(DestQMgrName);
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "getDestQMgrName()", "getter", traceRet1);
/*     */     }
/* 247 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDestQMgrName(String value) {
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "setDestQMgrName(String)", "setter", value);
/*     */     }
/* 259 */     setStringValue(DestQMgrName, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEncoding() {
/* 268 */     int traceRet1 = getIntValue(Encoding);
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "getEncoding()", "getter", 
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
/*     */   public void setEncoding(int value) {
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "setEncoding(int)", "setter", 
/* 284 */           Integer.valueOf(value));
/*     */     }
/* 286 */     setIntValue(Encoding, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 295 */     int traceRet1 = getIntValue(CodedCharSetId);
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "getCodedCharSetId()", "getter", 
/* 298 */           Integer.valueOf(traceRet1));
/*     */     }
/* 300 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int value) {
/* 309 */     if (Trace.isOn) {
/* 310 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "setCodedCharSetId(int)", "setter", 
/* 311 */           Integer.valueOf(value));
/*     */     }
/* 313 */     setIntValue(CodedCharSetId, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 322 */     String traceRet1 = getStringValue(Format);
/* 323 */     if (Trace.isOn) {
/* 324 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "getFormat()", "getter", traceRet1);
/*     */     }
/* 326 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(String value) {
/* 335 */     if (Trace.isOn) {
/* 336 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "setFormat(String)", "setter", value);
/*     */     }
/* 338 */     setStringValue(Format, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPutApplType() {
/* 347 */     int traceRet1 = getIntValue(PutApplType);
/* 348 */     if (Trace.isOn) {
/* 349 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "getPutApplType()", "getter", 
/* 350 */           Integer.valueOf(traceRet1));
/*     */     }
/* 352 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPutApplType(int value) {
/* 361 */     if (Trace.isOn) {
/* 362 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "setPutApplType(int)", "setter", 
/* 363 */           Integer.valueOf(value));
/*     */     }
/* 365 */     setIntValue(PutApplType, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPutApplName() {
/* 374 */     String traceRet1 = getStringValue(PutApplName);
/* 375 */     if (Trace.isOn) {
/* 376 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "getPutApplName()", "getter", traceRet1);
/*     */     }
/* 378 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPutApplName(String value) {
/* 387 */     if (Trace.isOn) {
/* 388 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "setPutApplName(String)", "setter", value);
/*     */     }
/* 390 */     setStringValue(PutApplName, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPutDate() {
/* 399 */     String traceRet1 = getStringValue(PutDate);
/* 400 */     if (Trace.isOn) {
/* 401 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "getPutDate()", "getter", traceRet1);
/*     */     }
/* 403 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPutDate(String value) {
/* 412 */     if (Trace.isOn) {
/* 413 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "setPutDate(String)", "setter", value);
/*     */     }
/* 415 */     setStringValue(PutDate, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPutTime() {
/* 424 */     String traceRet1 = getStringValue(PutTime);
/* 425 */     if (Trace.isOn) {
/* 426 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "getPutTime()", "getter", traceRet1);
/*     */     }
/* 428 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPutTime(String value) {
/* 437 */     if (Trace.isOn) {
/* 438 */       Trace.data(this, "com.ibm.mq.headers.MQDLH", "setPutTime(String)", "setter", value);
/*     */     }
/* 440 */     setStringValue(PutTime, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextEncoding() {
/* 448 */     if (Trace.isOn) {
/* 449 */       Trace.entry(this, "com.ibm.mq.headers.MQDLH", "nextEncoding()");
/*     */     }
/* 451 */     int traceRet1 = getEncoding();
/*     */     
/* 453 */     if (Trace.isOn) {
/* 454 */       Trace.exit(this, "com.ibm.mq.headers.MQDLH", "nextEncoding()", Integer.valueOf(traceRet1));
/*     */     }
/* 456 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextEncoding(int value) {
/* 464 */     if (Trace.isOn)
/* 465 */       Trace.entry(this, "com.ibm.mq.headers.MQDLH", "nextEncoding(int)", new Object[] {
/* 466 */             Integer.valueOf(value)
/*     */           }); 
/* 468 */     setEncoding(value);
/*     */     
/* 470 */     if (Trace.isOn) {
/* 471 */       Trace.exit(this, "com.ibm.mq.headers.MQDLH", "nextEncoding(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextCharacterSet() {
/* 481 */     if (Trace.isOn) {
/* 482 */       Trace.entry(this, "com.ibm.mq.headers.MQDLH", "nextCharacterSet()");
/*     */     }
/* 484 */     int traceRet1 = getCodedCharSetId();
/*     */     
/* 486 */     if (Trace.isOn) {
/* 487 */       Trace.exit(this, "com.ibm.mq.headers.MQDLH", "nextCharacterSet()", 
/* 488 */           Integer.valueOf(traceRet1));
/*     */     }
/* 490 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextCharacterSet(int value) {
/* 498 */     if (Trace.isOn)
/* 499 */       Trace.entry(this, "com.ibm.mq.headers.MQDLH", "nextCharacterSet(int)", new Object[] {
/* 500 */             Integer.valueOf(value)
/*     */           }); 
/* 502 */     setCodedCharSetId(value);
/*     */     
/* 504 */     if (Trace.isOn) {
/* 505 */       Trace.exit(this, "com.ibm.mq.headers.MQDLH", "nextCharacterSet(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String nextFormat() {
/* 515 */     if (Trace.isOn) {
/* 516 */       Trace.entry(this, "com.ibm.mq.headers.MQDLH", "nextFormat()");
/*     */     }
/* 518 */     String traceRet1 = getFormat();
/*     */     
/* 520 */     if (Trace.isOn) {
/* 521 */       Trace.exit(this, "com.ibm.mq.headers.MQDLH", "nextFormat()", traceRet1);
/*     */     }
/* 523 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextFormat(String value) {
/* 531 */     if (Trace.isOn) {
/* 532 */       Trace.entry(this, "com.ibm.mq.headers.MQDLH", "nextFormat(String)", new Object[] { value });
/*     */     }
/* 534 */     setFormat(value);
/*     */     
/* 536 */     if (Trace.isOn) {
/* 537 */       Trace.exit(this, "com.ibm.mq.headers.MQDLH", "nextFormat(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String format() {
/* 547 */     if (Trace.isOn) {
/* 548 */       Trace.entry(this, "com.ibm.mq.headers.MQDLH", "format()");
/*     */     }
/* 550 */     if (Trace.isOn) {
/* 551 */       Trace.exit(this, "com.ibm.mq.headers.MQDLH", "format()", "MQDEAD  ");
/*     */     }
/* 553 */     return "MQDEAD  ";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQDLH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */