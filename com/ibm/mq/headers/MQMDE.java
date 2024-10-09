/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.constants.CMQC;
/*     */ import com.ibm.mq.headers.internal.Header;
/*     */ import com.ibm.mq.headers.internal.HeaderField;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
/*     */ import com.ibm.mq.headers.internal.MessageWrapper;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQMDE
/*     */   extends Header
/*     */   implements MQChainable
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQMDE.java";
/*     */   
/*     */   static {
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.data("com.ibm.mq.headers.MQMDE", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQMDE.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   static final HeaderType TYPE = new HeaderType("MQMDE");
/*     */   
/*  72 */   static final HeaderField StrucId = TYPE.addMQChar("StrucId", "MDE ");
/*  73 */   static final HeaderField Version = TYPE.addMQLong("Version", 2);
/*  74 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 72);
/*  75 */   static final HeaderField Encoding = TYPE.addMQLong("Encoding");
/*  76 */   static final HeaderField CodedCharSetId = TYPE.addMQLong("CodedCharSetId");
/*  77 */   static final HeaderField Format = TYPE.addMQChar("Format", "        ");
/*  78 */   static final HeaderField Flags = TYPE.addMQLong("Flags");
/*  79 */   static final HeaderField GroupId = TYPE.addMQByte("GroupId", 24);
/*  80 */   static final HeaderField MsgSeqNumber = TYPE.addMQLong("MsgSeqNumber");
/*  81 */   static final HeaderField Offset = TYPE.addMQLong("Offset");
/*  82 */   static final HeaderField MsgFlags = TYPE.addMQLong("MsgFlags");
/*  83 */   static final HeaderField OriginalLength = TYPE.addMQLong("OriginalLength");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQMDE() {
/*  89 */     super(TYPE);
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.entry(this, "com.ibm.mq.headers.MQMDE", "<init>()");
/*     */     }
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.exit(this, "com.ibm.mq.headers.MQMDE", "<init>()");
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
/*     */   public MQMDE(DataInput message) throws MQDataException, IOException {
/* 107 */     this();
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.entry(this, "com.ibm.mq.headers.MQMDE", "<init>(DataInput)", new Object[] { message });
/*     */     }
/* 111 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.mq.headers.MQMDE", "<init>(DataInput)");
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
/*     */   public MQMDE(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 130 */     this();
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.entry(this, "com.ibm.mq.headers.MQMDE", "<init>(DataInput,int,int)", new Object[] { message, 
/* 133 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 136 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 138 */     catch (MQDataException mde) {
/* 139 */       if (Trace.isOn) {
/* 140 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQMDE", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/*     */       
/* 143 */       if (Trace.isOn) {
/* 144 */         Trace.throwing(this, "com.ibm.mq.headers.MQMDE", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/* 146 */       throw mde;
/*     */     }
/* 148 */     catch (IOException ioe) {
/* 149 */       if (Trace.isOn) {
/* 150 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQMDE", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 152 */       if (Trace.isOn) {
/* 153 */         Trace.throwing(this, "com.ibm.mq.headers.MQMDE", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 155 */       throw ioe;
/*     */     }
/* 157 */     catch (Exception e) {
/* 158 */       if (Trace.isOn) {
/* 159 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQMDE", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/* 161 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 162 */       if (Trace.isOn) {
/* 163 */         Trace.throwing(this, "com.ibm.mq.headers.MQMDE", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 166 */       throw traceRet1;
/*     */     } 
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.exit(this, "com.ibm.mq.headers.MQMDE", "<init>(DataInput,int,int)");
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
/* 180 */     String traceRet1 = getStringValue(StrucId);
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "getStrucId()", "getter", traceRet1);
/*     */     }
/* 184 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 193 */     int traceRet1 = getIntValue(Version);
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "getVersion()", "getter", 
/* 196 */           Integer.valueOf(traceRet1));
/*     */     }
/* 198 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 207 */     int traceRet1 = getIntValue(StrucLength);
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "getStrucLength()", "getter", 
/* 210 */           Integer.valueOf(traceRet1));
/*     */     }
/* 212 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEncoding() {
/* 221 */     int traceRet1 = getIntValue(Encoding);
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "getEncoding()", "getter", 
/* 224 */           Integer.valueOf(traceRet1));
/*     */     }
/* 226 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncoding(int value) {
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "setEncoding(int)", "setter", 
/* 237 */           Integer.valueOf(value));
/*     */     }
/* 239 */     setIntValue(Encoding, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 248 */     int traceRet1 = getIntValue(CodedCharSetId);
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "getCodedCharSetId()", "getter", 
/* 251 */           Integer.valueOf(traceRet1));
/*     */     }
/* 253 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int value) {
/* 262 */     if (Trace.isOn) {
/* 263 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "setCodedCharSetId(int)", "setter", 
/* 264 */           Integer.valueOf(value));
/*     */     }
/* 266 */     setIntValue(CodedCharSetId, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 275 */     String traceRet1 = getStringValue(Format);
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "getFormat()", "getter", traceRet1);
/*     */     }
/* 279 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(String value) {
/* 288 */     if (Trace.isOn) {
/* 289 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "setFormat(String)", "setter", value);
/*     */     }
/* 291 */     setStringValue(Format, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlags() {
/* 300 */     int traceRet1 = getIntValue(Flags);
/* 301 */     if (Trace.isOn) {
/* 302 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "getFlags()", "getter", 
/* 303 */           Integer.valueOf(traceRet1));
/*     */     }
/* 305 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int value) {
/* 314 */     if (Trace.isOn) {
/* 315 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "setFlags(int)", "setter", 
/* 316 */           Integer.valueOf(value));
/*     */     }
/* 318 */     setIntValue(Flags, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getGroupId() {
/* 327 */     byte[] traceRet1 = getBytesValue(GroupId);
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "getGroupId()", "getter", traceRet1);
/*     */     }
/* 331 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGroupId(byte[] value) {
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "setGroupId(byte [ ])", "setter", value);
/*     */     }
/* 343 */     setBytesValue(GroupId, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMsgSeqNumber() {
/* 352 */     int traceRet1 = getIntValue(MsgSeqNumber);
/* 353 */     if (Trace.isOn) {
/* 354 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "getMsgSeqNumber()", "getter", 
/* 355 */           Integer.valueOf(traceRet1));
/*     */     }
/* 357 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMsgSeqNumber(int value) {
/* 366 */     if (Trace.isOn) {
/* 367 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "setMsgSeqNumber(int)", "setter", 
/* 368 */           Integer.valueOf(value));
/*     */     }
/* 370 */     setIntValue(MsgSeqNumber, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOffset() {
/* 379 */     int traceRet1 = getIntValue(Offset);
/* 380 */     if (Trace.isOn) {
/* 381 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "getOffset()", "getter", 
/* 382 */           Integer.valueOf(traceRet1));
/*     */     }
/* 384 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOffset(int value) {
/* 393 */     if (Trace.isOn) {
/* 394 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "setOffset(int)", "setter", 
/* 395 */           Integer.valueOf(value));
/*     */     }
/* 397 */     setIntValue(Offset, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMsgFlags() {
/* 406 */     int traceRet1 = getIntValue(MsgFlags);
/* 407 */     if (Trace.isOn) {
/* 408 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "getMsgFlags()", "getter", 
/* 409 */           Integer.valueOf(traceRet1));
/*     */     }
/* 411 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMsgFlags(int value) {
/* 420 */     if (Trace.isOn) {
/* 421 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "setMsgFlags(int)", "setter", 
/* 422 */           Integer.valueOf(value));
/*     */     }
/* 424 */     setIntValue(MsgFlags, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOriginalLength() {
/* 433 */     int traceRet1 = getIntValue(OriginalLength);
/* 434 */     if (Trace.isOn) {
/* 435 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "getOriginalLength()", "getter", 
/* 436 */           Integer.valueOf(traceRet1));
/*     */     }
/* 438 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOriginalLength(int value) {
/* 447 */     if (Trace.isOn) {
/* 448 */       Trace.data(this, "com.ibm.mq.headers.MQMDE", "setOriginalLength(int)", "setter", 
/* 449 */           Integer.valueOf(value));
/*     */     }
/* 451 */     setIntValue(OriginalLength, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextEncoding() {
/* 459 */     if (Trace.isOn) {
/* 460 */       Trace.entry(this, "com.ibm.mq.headers.MQMDE", "nextEncoding()");
/*     */     }
/* 462 */     int traceRet1 = getEncoding();
/*     */     
/* 464 */     if (Trace.isOn) {
/* 465 */       Trace.exit(this, "com.ibm.mq.headers.MQMDE", "nextEncoding()", Integer.valueOf(traceRet1));
/*     */     }
/* 467 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextEncoding(int value) {
/* 475 */     if (Trace.isOn)
/* 476 */       Trace.entry(this, "com.ibm.mq.headers.MQMDE", "nextEncoding(int)", new Object[] {
/* 477 */             Integer.valueOf(value)
/*     */           }); 
/* 479 */     setEncoding(value);
/*     */     
/* 481 */     if (Trace.isOn) {
/* 482 */       Trace.exit(this, "com.ibm.mq.headers.MQMDE", "nextEncoding(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextCharacterSet() {
/* 492 */     if (Trace.isOn) {
/* 493 */       Trace.entry(this, "com.ibm.mq.headers.MQMDE", "nextCharacterSet()");
/*     */     }
/* 495 */     int traceRet1 = getCodedCharSetId();
/*     */     
/* 497 */     if (Trace.isOn) {
/* 498 */       Trace.exit(this, "com.ibm.mq.headers.MQMDE", "nextCharacterSet()", 
/* 499 */           Integer.valueOf(traceRet1));
/*     */     }
/* 501 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextCharacterSet(int value) {
/* 509 */     if (Trace.isOn)
/* 510 */       Trace.entry(this, "com.ibm.mq.headers.MQMDE", "nextCharacterSet(int)", new Object[] {
/* 511 */             Integer.valueOf(value)
/*     */           }); 
/* 513 */     setCodedCharSetId(value);
/*     */     
/* 515 */     if (Trace.isOn) {
/* 516 */       Trace.exit(this, "com.ibm.mq.headers.MQMDE", "nextCharacterSet(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String nextFormat() {
/* 526 */     if (Trace.isOn) {
/* 527 */       Trace.entry(this, "com.ibm.mq.headers.MQMDE", "nextFormat()");
/*     */     }
/* 529 */     String traceRet1 = getFormat();
/*     */     
/* 531 */     if (Trace.isOn) {
/* 532 */       Trace.exit(this, "com.ibm.mq.headers.MQMDE", "nextFormat()", traceRet1);
/*     */     }
/* 534 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextFormat(String value) {
/* 542 */     if (Trace.isOn) {
/* 543 */       Trace.entry(this, "com.ibm.mq.headers.MQMDE", "nextFormat(String)", new Object[] { value });
/*     */     }
/* 545 */     setFormat(value);
/*     */     
/* 547 */     if (Trace.isOn) {
/* 548 */       Trace.exit(this, "com.ibm.mq.headers.MQMDE", "nextFormat(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String format() {
/* 558 */     if (Trace.isOn) {
/* 559 */       Trace.entry(this, "com.ibm.mq.headers.MQMDE", "format()");
/*     */     }
/* 561 */     if (Trace.isOn) {
/* 562 */       Trace.exit(this, "com.ibm.mq.headers.MQMDE", "format()", "MQHMDE  ");
/*     */     }
/* 564 */     return "MQHMDE  ";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasExtensionContent() {
/* 573 */     if (Trace.isOn) {
/* 574 */       Trace.entry(this, "com.ibm.mq.headers.MQMDE", "hasExtensionContent()");
/*     */     }
/*     */ 
/*     */     
/* 578 */     boolean traceRet1 = (!Arrays.equals(CMQC.MQGI_NONE, getGroupId()) || getMsgSeqNumber() != 0 || getOffset() != 0 || getMsgFlags() != 0 || getOriginalLength() != 0);
/*     */     
/* 580 */     if (Trace.isOn) {
/* 581 */       Trace.exit(this, "com.ibm.mq.headers.MQMDE", "hasExtensionContent()", 
/* 582 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 584 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQMDE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */