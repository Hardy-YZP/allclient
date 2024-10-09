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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQIIH
/*     */   extends Header
/*     */   implements MQChainable
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQIIH.java";
/*     */   
/*     */   static {
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.data("com.ibm.mq.headers.MQIIH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQIIH.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   static final HeaderType TYPE = new HeaderType("MQIIH");
/*     */   
/*  74 */   static final HeaderField StrucId = TYPE.addMQChar("StrucId", "IIH ");
/*  75 */   static final HeaderField Version = TYPE.addMQLong("Version", 1);
/*  76 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 84);
/*  77 */   static final HeaderField Encoding = TYPE.addMQLong("Encoding");
/*  78 */   static final HeaderField CodedCharSetId = TYPE.addMQLong("CodedCharSetId");
/*  79 */   static final HeaderField Format = TYPE.addMQChar("Format", "        ");
/*  80 */   static final HeaderField Flags = TYPE.addMQLong("Flags");
/*  81 */   static final HeaderField LTermOverride = TYPE.addMQChar("LTermOverride", 8);
/*  82 */   static final HeaderField MFSMapName = TYPE.addMQChar("MFSMapName", 8);
/*  83 */   static final HeaderField ReplyToFormat = TYPE.addMQChar("ReplyToFormat", 8);
/*  84 */   static final HeaderField Authenticator = TYPE.addMQChar("Authenticator", 8);
/*  85 */   static final HeaderField TranInstanceId = TYPE.addMQByte("TranInstanceId", 16);
/*  86 */   static final HeaderField TranState = TYPE.addMQChar("TranState", 1);
/*  87 */   static final HeaderField CommitMode = TYPE.addMQChar("CommitMode", 1);
/*  88 */   static final HeaderField SecurityScope = TYPE.addMQChar("SecurityScope", 1);
/*  89 */   static final HeaderField Reserved = TYPE.addMQChar("Reserved", 1);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQIIH() {
/*  95 */     super(TYPE);
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, "com.ibm.mq.headers.MQIIH", "<init>()");
/*     */     }
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.exit(this, "com.ibm.mq.headers.MQIIH", "<init>()");
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
/*     */   public MQIIH(DataInput message) throws MQDataException, IOException {
/* 113 */     this();
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.entry(this, "com.ibm.mq.headers.MQIIH", "<init>(DataInput)", new Object[] { message });
/*     */     }
/* 117 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.exit(this, "com.ibm.mq.headers.MQIIH", "<init>(DataInput)");
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
/*     */   public MQIIH(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 136 */     this();
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.entry(this, "com.ibm.mq.headers.MQIIH", "<init>(DataInput,int,int)", new Object[] { message, 
/* 139 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 142 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 144 */     catch (MQDataException mde) {
/* 145 */       if (Trace.isOn) {
/* 146 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQIIH", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/*     */       
/* 149 */       if (Trace.isOn) {
/* 150 */         Trace.throwing(this, "com.ibm.mq.headers.MQIIH", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/* 152 */       throw mde;
/*     */     }
/* 154 */     catch (IOException ioe) {
/* 155 */       if (Trace.isOn) {
/* 156 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQIIH", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 158 */       if (Trace.isOn) {
/* 159 */         Trace.throwing(this, "com.ibm.mq.headers.MQIIH", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 161 */       throw ioe;
/*     */     }
/* 163 */     catch (Exception e) {
/* 164 */       if (Trace.isOn) {
/* 165 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQIIH", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/* 167 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 168 */       if (Trace.isOn) {
/* 169 */         Trace.throwing(this, "com.ibm.mq.headers.MQIIH", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 172 */       throw traceRet1;
/*     */     } 
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.exit(this, "com.ibm.mq.headers.MQIIH", "<init>(DataInput,int,int)");
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
/* 186 */     String traceRet1 = getStringValue(StrucId);
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "getStrucId()", "getter", traceRet1);
/*     */     }
/* 190 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 199 */     int traceRet1 = getIntValue(Version);
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "getVersion()", "getter", 
/* 202 */           Integer.valueOf(traceRet1));
/*     */     }
/* 204 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 213 */     int traceRet1 = getIntValue(StrucLength);
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "getStrucLength()", "getter", 
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
/*     */   public int getEncoding() {
/* 227 */     int traceRet1 = getIntValue(Encoding);
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "getEncoding()", "getter", 
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
/*     */   public void setEncoding(int value) {
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "setEncoding(int)", "setter", 
/* 243 */           Integer.valueOf(value));
/*     */     }
/* 245 */     setIntValue(Encoding, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 254 */     int traceRet1 = getIntValue(CodedCharSetId);
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "getCodedCharSetId()", "getter", 
/* 257 */           Integer.valueOf(traceRet1));
/*     */     }
/* 259 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int value) {
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "setCodedCharSetId(int)", "setter", 
/* 270 */           Integer.valueOf(value));
/*     */     }
/* 272 */     setIntValue(CodedCharSetId, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 281 */     String traceRet1 = getStringValue(Format);
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "getFormat()", "getter", traceRet1);
/*     */     }
/* 285 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(String value) {
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "setFormat(String)", "setter", value);
/*     */     }
/* 297 */     setStringValue(Format, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlags() {
/* 306 */     int traceRet1 = getIntValue(Flags);
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "getFlags()", "getter", 
/* 309 */           Integer.valueOf(traceRet1));
/*     */     }
/* 311 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int value) {
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "setFlags(int)", "setter", 
/* 322 */           Integer.valueOf(value));
/*     */     }
/* 324 */     setIntValue(Flags, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLTermOverride() {
/* 333 */     String traceRet1 = getStringValue(LTermOverride);
/* 334 */     if (Trace.isOn) {
/* 335 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "getLTermOverride()", "getter", traceRet1);
/*     */     }
/* 337 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLTermOverride(String value) {
/* 346 */     if (Trace.isOn) {
/* 347 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "setLTermOverride(String)", "setter", value);
/*     */     }
/* 349 */     setStringValue(LTermOverride, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMFSMapName() {
/* 358 */     String traceRet1 = getStringValue(MFSMapName);
/* 359 */     if (Trace.isOn) {
/* 360 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "getMFSMapName()", "getter", traceRet1);
/*     */     }
/* 362 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMFSMapName(String value) {
/* 371 */     if (Trace.isOn) {
/* 372 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "setMFSMapName(String)", "setter", value);
/*     */     }
/* 374 */     setStringValue(MFSMapName, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReplyToFormat() {
/* 383 */     String traceRet1 = getStringValue(ReplyToFormat);
/* 384 */     if (Trace.isOn) {
/* 385 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "getReplyToFormat()", "getter", traceRet1);
/*     */     }
/* 387 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReplyToFormat(String value) {
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "setReplyToFormat(String)", "setter", value);
/*     */     }
/* 399 */     setStringValue(ReplyToFormat, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAuthenticator() {
/* 409 */     String authenticator = getStringValue(Authenticator);
/* 410 */     if (Trace.isOn) {
/* 411 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "getAuthenticator()", "getter", authenticator);
/*     */     }
/* 413 */     return authenticator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAuthenticator(String value) {
/* 422 */     if (Trace.isOn) {
/* 423 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "setAuthenticator(String)", "setter", value);
/*     */     }
/*     */     
/* 426 */     setStringValue(Authenticator, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getTranInstanceId() {
/* 435 */     byte[] traceRet1 = getBytesValue(TranInstanceId);
/* 436 */     if (Trace.isOn) {
/* 437 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "getTranInstanceId()", "getter", traceRet1);
/*     */     }
/* 439 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTranInstanceId(byte[] value) {
/* 448 */     if (Trace.isOn) {
/* 449 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "setTranInstanceId(byte [ ])", "setter", value);
/*     */     }
/*     */     
/* 452 */     setBytesValue(TranInstanceId, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getTranState() {
/* 461 */     char traceRet1 = getCharValue(TranState);
/* 462 */     if (Trace.isOn) {
/* 463 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "getTranState()", "getter", 
/* 464 */           Character.valueOf(traceRet1));
/*     */     }
/* 466 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTranState(char value) {
/* 475 */     if (Trace.isOn) {
/* 476 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "setTranState(char)", "setter", 
/* 477 */           Character.valueOf(value));
/*     */     }
/* 479 */     setCharValue(TranState, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getCommitMode() {
/* 488 */     char traceRet1 = getCharValue(CommitMode);
/* 489 */     if (Trace.isOn) {
/* 490 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "getCommitMode()", "getter", 
/* 491 */           Character.valueOf(traceRet1));
/*     */     }
/* 493 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommitMode(char value) {
/* 502 */     if (Trace.isOn) {
/* 503 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "setCommitMode(char)", "setter", 
/* 504 */           Character.valueOf(value));
/*     */     }
/* 506 */     setCharValue(CommitMode, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getSecurityScope() {
/* 515 */     char traceRet1 = getCharValue(SecurityScope);
/* 516 */     if (Trace.isOn) {
/* 517 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "getSecurityScope()", "getter", 
/* 518 */           Character.valueOf(traceRet1));
/*     */     }
/* 520 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSecurityScope(char value) {
/* 529 */     if (Trace.isOn) {
/* 530 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "setSecurityScope(char)", "setter", 
/* 531 */           Character.valueOf(value));
/*     */     }
/* 533 */     setCharValue(SecurityScope, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getReserved() {
/* 541 */     char traceRet1 = getCharValue(Reserved);
/* 542 */     if (Trace.isOn) {
/* 543 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "getReserved()", "getter", 
/* 544 */           Character.valueOf(traceRet1));
/*     */     }
/* 546 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReserved(char value) {
/* 554 */     if (Trace.isOn) {
/* 555 */       Trace.data(this, "com.ibm.mq.headers.MQIIH", "setReserved(char)", "setter", 
/* 556 */           Character.valueOf(value));
/*     */     }
/* 558 */     setCharValue(Reserved, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextEncoding() {
/* 566 */     if (Trace.isOn) {
/* 567 */       Trace.entry(this, "com.ibm.mq.headers.MQIIH", "nextEncoding()");
/*     */     }
/* 569 */     int traceRet1 = getEncoding();
/*     */     
/* 571 */     if (Trace.isOn) {
/* 572 */       Trace.exit(this, "com.ibm.mq.headers.MQIIH", "nextEncoding()", Integer.valueOf(traceRet1));
/*     */     }
/* 574 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextEncoding(int value) {
/* 582 */     if (Trace.isOn)
/* 583 */       Trace.entry(this, "com.ibm.mq.headers.MQIIH", "nextEncoding(int)", new Object[] {
/* 584 */             Integer.valueOf(value)
/*     */           }); 
/* 586 */     setEncoding(value);
/*     */     
/* 588 */     if (Trace.isOn) {
/* 589 */       Trace.exit(this, "com.ibm.mq.headers.MQIIH", "nextEncoding(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextCharacterSet() {
/* 599 */     if (Trace.isOn) {
/* 600 */       Trace.entry(this, "com.ibm.mq.headers.MQIIH", "nextCharacterSet()");
/*     */     }
/* 602 */     int traceRet1 = getCodedCharSetId();
/*     */     
/* 604 */     if (Trace.isOn) {
/* 605 */       Trace.exit(this, "com.ibm.mq.headers.MQIIH", "nextCharacterSet()", 
/* 606 */           Integer.valueOf(traceRet1));
/*     */     }
/* 608 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextCharacterSet(int value) {
/* 616 */     if (Trace.isOn)
/* 617 */       Trace.entry(this, "com.ibm.mq.headers.MQIIH", "nextCharacterSet(int)", new Object[] {
/* 618 */             Integer.valueOf(value)
/*     */           }); 
/* 620 */     setCodedCharSetId(value);
/*     */     
/* 622 */     if (Trace.isOn) {
/* 623 */       Trace.exit(this, "com.ibm.mq.headers.MQIIH", "nextCharacterSet(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String nextFormat() {
/* 633 */     if (Trace.isOn) {
/* 634 */       Trace.entry(this, "com.ibm.mq.headers.MQIIH", "nextFormat()");
/*     */     }
/* 636 */     String traceRet1 = getFormat();
/*     */     
/* 638 */     if (Trace.isOn) {
/* 639 */       Trace.exit(this, "com.ibm.mq.headers.MQIIH", "nextFormat()", traceRet1);
/*     */     }
/* 641 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextFormat(String value) {
/* 649 */     if (Trace.isOn) {
/* 650 */       Trace.entry(this, "com.ibm.mq.headers.MQIIH", "nextFormat(String)", new Object[] { value });
/*     */     }
/* 652 */     setFormat(value);
/*     */     
/* 654 */     if (Trace.isOn) {
/* 655 */       Trace.exit(this, "com.ibm.mq.headers.MQIIH", "nextFormat(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String format() {
/* 665 */     if (Trace.isOn) {
/* 666 */       Trace.entry(this, "com.ibm.mq.headers.MQIIH", "format()");
/*     */     }
/* 668 */     if (Trace.isOn) {
/* 669 */       Trace.exit(this, "com.ibm.mq.headers.MQIIH", "format()", "MQIMS   ");
/*     */     }
/* 671 */     return "MQIMS   ";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQIIH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */