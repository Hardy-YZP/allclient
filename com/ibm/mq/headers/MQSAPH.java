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
/*     */ public class MQSAPH
/*     */   extends Header
/*     */   implements MQChainable
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQSAPH.java";
/*     */   public static final int SMQ_ID_LEN = 4;
/*     */   public static final int SMQ_SYSNUM_LEN = 2;
/*     */   public static final int SIZE = 108;
/*     */   
/*     */   static {
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.data("com.ibm.mq.headers.MQSAPH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQSAPH.java");
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
/*  85 */   static final HeaderType TYPE = new HeaderType("MQSAPH");
/*     */   
/*  87 */   static final HeaderField StrucId = TYPE.addMQChar("StrucId", "SAPH");
/*  88 */   static final HeaderField Version = TYPE.addMQLong("Version");
/*  89 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 108);
/*  90 */   static final HeaderField Encoding = TYPE.addMQLong("Encoding", 4);
/*  91 */   static final HeaderField CodedCharSetId = TYPE.addMQLong("CodedCharSetId", 4);
/*  92 */   static final HeaderField Format = TYPE.addMQChar("Format", 8);
/*  93 */   static final HeaderField Flags = TYPE.addMQLong("Flags");
/*  94 */   static final HeaderField Client = TYPE.addMQChar("Client", 4);
/*     */   
/*  96 */   static final HeaderField Hostname = TYPE.addMQChar("Hostname", 48);
/*  97 */   static final HeaderField UserId = TYPE.addMQChar("UserId", 12);
/*  98 */   static final HeaderField Password = TYPE.addMQChar("Password", 8);
/*  99 */   static final HeaderField SystemNumber = TYPE.addMQChar("SystemNumber", 2);
/* 100 */   static final HeaderField Reserved = TYPE.addMQByte("Reserved", 2);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQSAPH() {
/* 106 */     super(TYPE);
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.entry(this, "com.ibm.mq.headers.MQSAPH", "<init>()");
/*     */     }
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit(this, "com.ibm.mq.headers.MQSAPH", "<init>()");
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
/*     */   public MQSAPH(DataInput message) throws MQDataException, IOException {
/* 124 */     this();
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.entry(this, "com.ibm.mq.headers.MQSAPH", "<init>(DataInput)", new Object[] { message });
/*     */     }
/* 128 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.exit(this, "com.ibm.mq.headers.MQSAPH", "<init>(DataInput)");
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
/*     */   public MQSAPH(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 147 */     this();
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.entry(this, "com.ibm.mq.headers.MQSAPH", "<init>(DataInput,int,int)", new Object[] { message, 
/* 150 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 153 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 155 */     catch (MQDataException mde) {
/* 156 */       if (Trace.isOn) {
/* 157 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQSAPH", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/*     */       
/* 160 */       if (Trace.isOn) {
/* 161 */         Trace.throwing(this, "com.ibm.mq.headers.MQSAPH", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/* 163 */       throw mde;
/*     */     }
/* 165 */     catch (IOException ioe) {
/* 166 */       if (Trace.isOn) {
/* 167 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQSAPH", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 169 */       if (Trace.isOn) {
/* 170 */         Trace.throwing(this, "com.ibm.mq.headers.MQSAPH", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 172 */       throw ioe;
/*     */     }
/* 174 */     catch (Exception e) {
/* 175 */       if (Trace.isOn) {
/* 176 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQSAPH", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/* 178 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 179 */       if (Trace.isOn) {
/* 180 */         Trace.throwing(this, "com.ibm.mq.headers.MQSAPH", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 183 */       throw traceRet1;
/*     */     } 
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.exit(this, "com.ibm.mq.headers.MQSAPH", "<init>(DataInput,int,int)");
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
/* 197 */     String traceRet1 = getStringValue(StrucId);
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "getStrucId()", "getter", traceRet1);
/*     */     }
/* 201 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 210 */     int traceRet1 = getIntValue(Version);
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "getVersion()", "getter", 
/* 213 */           Integer.valueOf(traceRet1));
/*     */     }
/* 215 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 224 */     int traceRet1 = getIntValue(StrucLength);
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "getStrucLength()", "getter", 
/* 227 */           Integer.valueOf(traceRet1));
/*     */     }
/* 229 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEncoding() {
/* 238 */     int traceRet1 = getIntValue(Encoding);
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "getEncoding()", "getter", 
/* 241 */           Integer.valueOf(traceRet1));
/*     */     }
/* 243 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncoding(int value) {
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "setEncoding(int)", "setter", 
/* 254 */           Integer.valueOf(value));
/*     */     }
/* 256 */     setIntValue(Encoding, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 265 */     int traceRet1 = getIntValue(CodedCharSetId);
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "getCodedCharSetId()", "getter", 
/* 268 */           Integer.valueOf(traceRet1));
/*     */     }
/* 270 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int value) {
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "setCodedCharSetId(int)", "setter", 
/* 281 */           Integer.valueOf(value));
/*     */     }
/* 283 */     setIntValue(CodedCharSetId, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 292 */     String traceRet1 = getStringValue(Format);
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "getFormat()", "getter", traceRet1);
/*     */     }
/* 296 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(String value) {
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "setFormat(String)", "setter", value);
/*     */     }
/* 308 */     setStringValue(Format, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlags() {
/* 317 */     int traceRet1 = getIntValue(Flags);
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "getFlags()", "getter", 
/* 320 */           Integer.valueOf(traceRet1));
/*     */     }
/* 322 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int value) {
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "setFlags(int)", "setter", 
/* 333 */           Integer.valueOf(value));
/*     */     }
/* 335 */     setIntValue(Flags, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClient() {
/* 344 */     String traceRet1 = getStringValue(Client);
/* 345 */     if (Trace.isOn) {
/* 346 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "getClient()", "getter", traceRet1);
/*     */     }
/* 348 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClient(String value) {
/* 357 */     if (Trace.isOn) {
/* 358 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "setClient(String)", "setter", value);
/*     */     }
/* 360 */     setStringValue(Client, value);
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
/*     */   public String getLanguage() {
/* 372 */     String traceRet1 = getStringValue(Client);
/*     */     
/* 374 */     if (Trace.isOn) {
/* 375 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "getLanguage()", "getter", traceRet1);
/*     */     }
/* 377 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLanguage(String value) {
/* 386 */     if (Trace.isOn) {
/* 387 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "setLanguage(String)", "setter", value);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 392 */     setStringValue(Client, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHostname() {
/* 402 */     String traceRet1 = getStringValue(Hostname);
/* 403 */     if (Trace.isOn) {
/* 404 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "getHostname()", "getter", traceRet1);
/*     */     }
/* 406 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHostname(String value) {
/* 415 */     if (Trace.isOn) {
/* 416 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "setHostname(String)", "setter", value);
/*     */     }
/* 418 */     setStringValue(Hostname, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserId() {
/* 427 */     String traceRet1 = getStringValue(UserId);
/* 428 */     if (Trace.isOn) {
/* 429 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "getUserId()", "getter", traceRet1);
/*     */     }
/* 431 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserId(String value) {
/* 440 */     if (Trace.isOn) {
/* 441 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "setUserId(String)", "setter", value);
/*     */     }
/* 443 */     setStringValue(UserId, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPassword() {
/* 453 */     String password = getStringValue(Password);
/* 454 */     if (Trace.isOn) {
/* 455 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "getPassword()", "getter", password);
/*     */     }
/* 457 */     return password;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPassword(String value) {
/* 466 */     if (Trace.isOn) {
/* 467 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "setPassword(String)", "setter", value);
/*     */     }
/*     */     
/* 470 */     setStringValue(Password, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSystemNumber() {
/* 479 */     String traceRet1 = getStringValue(SystemNumber);
/* 480 */     if (Trace.isOn) {
/* 481 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "getSystemNumber()", "getter", traceRet1);
/*     */     }
/* 483 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSystemNumber(String value) {
/* 492 */     if (Trace.isOn) {
/* 493 */       Trace.data(this, "com.ibm.mq.headers.MQSAPH", "setSystemNumber(String)", "setter", value);
/*     */     }
/* 495 */     setStringValue(SystemNumber, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextEncoding() {
/* 503 */     if (Trace.isOn) {
/* 504 */       Trace.entry(this, "com.ibm.mq.headers.MQSAPH", "nextEncoding()");
/*     */     }
/* 506 */     int traceRet1 = getEncoding();
/*     */     
/* 508 */     if (Trace.isOn) {
/* 509 */       Trace.exit(this, "com.ibm.mq.headers.MQSAPH", "nextEncoding()", Integer.valueOf(traceRet1));
/*     */     }
/* 511 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextEncoding(int value) {
/* 519 */     if (Trace.isOn)
/* 520 */       Trace.entry(this, "com.ibm.mq.headers.MQSAPH", "nextEncoding(int)", new Object[] {
/* 521 */             Integer.valueOf(value)
/*     */           }); 
/* 523 */     setEncoding(value);
/*     */     
/* 525 */     if (Trace.isOn) {
/* 526 */       Trace.exit(this, "com.ibm.mq.headers.MQSAPH", "nextEncoding(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextCharacterSet() {
/* 536 */     if (Trace.isOn) {
/* 537 */       Trace.entry(this, "com.ibm.mq.headers.MQSAPH", "nextCharacterSet()");
/*     */     }
/* 539 */     int traceRet1 = getCodedCharSetId();
/*     */     
/* 541 */     if (Trace.isOn) {
/* 542 */       Trace.exit(this, "com.ibm.mq.headers.MQSAPH", "nextCharacterSet()", 
/* 543 */           Integer.valueOf(traceRet1));
/*     */     }
/* 545 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextCharacterSet(int value) {
/* 553 */     if (Trace.isOn)
/* 554 */       Trace.entry(this, "com.ibm.mq.headers.MQSAPH", "nextCharacterSet(int)", new Object[] {
/* 555 */             Integer.valueOf(value)
/*     */           }); 
/* 557 */     setCodedCharSetId(value);
/*     */     
/* 559 */     if (Trace.isOn) {
/* 560 */       Trace.exit(this, "com.ibm.mq.headers.MQSAPH", "nextCharacterSet(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String nextFormat() {
/* 570 */     if (Trace.isOn) {
/* 571 */       Trace.entry(this, "com.ibm.mq.headers.MQSAPH", "nextFormat()");
/*     */     }
/* 573 */     String traceRet1 = getFormat();
/*     */     
/* 575 */     if (Trace.isOn) {
/* 576 */       Trace.exit(this, "com.ibm.mq.headers.MQSAPH", "nextFormat()", traceRet1);
/*     */     }
/* 578 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextFormat(String value) {
/* 586 */     if (Trace.isOn) {
/* 587 */       Trace.entry(this, "com.ibm.mq.headers.MQSAPH", "nextFormat(String)", new Object[] { value });
/*     */     }
/* 589 */     setFormat(value);
/*     */     
/* 591 */     if (Trace.isOn) {
/* 592 */       Trace.exit(this, "com.ibm.mq.headers.MQSAPH", "nextFormat(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String format() {
/* 602 */     if (Trace.isOn) {
/* 603 */       Trace.entry(this, "com.ibm.mq.headers.MQSAPH", "format()");
/*     */     }
/* 605 */     if (Trace.isOn) {
/* 606 */       Trace.exit(this, "com.ibm.mq.headers.MQSAPH", "format()", "MQSAPH");
/*     */     }
/* 608 */     return "MQSAPH";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQSAPH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */