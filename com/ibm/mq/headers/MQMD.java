/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.constants.CMQC;
/*     */ import com.ibm.mq.headers.internal.HeaderField;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
/*     */ import com.ibm.mq.headers.internal.MessageWrapper;
/*     */ import com.ibm.mq.headers.internal.store.StoreDataOutput;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQMD
/*     */   extends MQMD1
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQMD.java";
/*     */   public static final int SIZE1 = 324;
/*     */   public static final int SIZE2 = 364;
/*     */   
/*     */   static {
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.data("com.ibm.mq.headers.MQMD", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQMD.java");
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
/* 103 */   static final HeaderType TYPE = new HeaderType("MQMD", MQMD1.TYPE, Version, 2);
/*     */   
/* 105 */   static final HeaderField GroupId = TYPE.addMQByte("GroupId", 24);
/* 106 */   static final HeaderField MsgSeqNumber = TYPE.addMQLong("MsgSeqNumber");
/* 107 */   static final HeaderField Offset = TYPE.addMQLong("Offset");
/* 108 */   static final HeaderField MsgFlags = TYPE.addMQLong("MsgFlags");
/* 109 */   static final HeaderField OriginalLength = TYPE.addMQLong("OriginalLength");
/*     */   
/*     */   static {
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry("com.ibm.mq.headers.MQMD", "static()");
/*     */     }
/* 115 */     TimeZone gmt = TimeZone.getTimeZone("GMT+0:00");
/*     */     
/* 117 */     timeFormat.setTimeZone(gmt);
/* 118 */     dateFormat.setTimeZone(gmt);
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.exit("com.ibm.mq.headers.MQMD", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQMD() {
/* 128 */     super(TYPE);
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.entry(this, "com.ibm.mq.headers.MQMD", "<init>()");
/*     */     }
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.exit(this, "com.ibm.mq.headers.MQMD", "<init>()");
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
/*     */   public MQMD(DataInput message) throws MQDataException, IOException {
/* 149 */     this();
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.entry(this, "com.ibm.mq.headers.MQMD", "<init>(DataInput)", new Object[] { message });
/*     */     }
/* 153 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.exit(this, "com.ibm.mq.headers.MQMD", "<init>(DataInput)");
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
/*     */   public MQMD(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 172 */     this();
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.entry(this, "com.ibm.mq.headers.MQMD", "<init>(DataInput,int,int)", new Object[] { message, 
/* 175 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 178 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 180 */     catch (MQDataException mde) {
/* 181 */       if (Trace.isOn) {
/* 182 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQMD", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/*     */       
/* 185 */       if (Trace.isOn) {
/* 186 */         Trace.throwing(this, "com.ibm.mq.headers.MQMD", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/* 188 */       throw mde;
/*     */     }
/* 190 */     catch (IOException ioe) {
/* 191 */       if (Trace.isOn) {
/* 192 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQMD", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 194 */       if (Trace.isOn) {
/* 195 */         Trace.throwing(this, "com.ibm.mq.headers.MQMD", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 197 */       throw ioe;
/*     */     }
/* 199 */     catch (Exception e) {
/* 200 */       if (Trace.isOn) {
/* 201 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQMD", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/* 203 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 204 */       if (Trace.isOn) {
/* 205 */         Trace.throwing(this, "com.ibm.mq.headers.MQMD", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 208 */       throw traceRet1;
/*     */     } 
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.exit(this, "com.ibm.mq.headers.MQMD", "<init>(DataInput,int,int)");
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
/*     */   public MQMD(MQMD1 md) throws IOException {
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.entry(this, "com.ibm.mq.headers.MQMD", "<init>(MQMD1)", new Object[] { md });
/*     */     }
/* 226 */     md.write((DataOutput)new StoreDataOutput(store(md.size()), 0), md.encoding(), md.characterSet());
/*     */     
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.exit(this, "com.ibm.mq.headers.MQMD", "<init>(MQMD1)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQMD(int version) {
/* 239 */     this();
/* 240 */     if (Trace.isOn)
/* 241 */       Trace.entry(this, "com.ibm.mq.headers.MQMD", "<init>(int)", new Object[] {
/* 242 */             Integer.valueOf(version)
/*     */           }); 
/* 244 */     if (version != 2) {
/* 245 */       setVersion(version);
/*     */     }
/*     */     
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.exit(this, "com.ibm.mq.headers.MQMD", "<init>(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int value) {
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.data(this, "com.ibm.mq.headers.MQMD", "setVersion(int)", "setter", 
/* 261 */           Integer.valueOf(value));
/*     */     }
/* 263 */     setIntValue(Version, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getGroupId() {
/* 274 */     byte[] traceRet1 = getBytesValue(GroupId);
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.data(this, "com.ibm.mq.headers.MQMD", "getGroupId()", "getter", traceRet1);
/*     */     }
/* 278 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGroupId(byte[] value) {
/* 287 */     if (Trace.isOn) {
/* 288 */       Trace.data(this, "com.ibm.mq.headers.MQMD", "setGroupId(byte [ ])", "setter", value);
/*     */     }
/* 290 */     setBytesValue(GroupId, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMsgSeqNumber() {
/* 299 */     int traceRet1 = getIntValue(MsgSeqNumber);
/* 300 */     if (Trace.isOn) {
/* 301 */       Trace.data(this, "com.ibm.mq.headers.MQMD", "getMsgSeqNumber()", "getter", 
/* 302 */           Integer.valueOf(traceRet1));
/*     */     }
/* 304 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMsgSeqNumber(int value) {
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.data(this, "com.ibm.mq.headers.MQMD", "setMsgSeqNumber(int)", "setter", 
/* 315 */           Integer.valueOf(value));
/*     */     }
/* 317 */     setIntValue(MsgSeqNumber, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOffset() {
/* 326 */     int traceRet1 = getIntValue(Offset);
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.data(this, "com.ibm.mq.headers.MQMD", "getOffset()", "getter", 
/* 329 */           Integer.valueOf(traceRet1));
/*     */     }
/* 331 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOffset(int value) {
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.data(this, "com.ibm.mq.headers.MQMD", "setOffset(int)", "setter", 
/* 342 */           Integer.valueOf(value));
/*     */     }
/* 344 */     setIntValue(Offset, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMsgFlags() {
/* 353 */     int traceRet1 = getIntValue(MsgFlags);
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.data(this, "com.ibm.mq.headers.MQMD", "getMsgFlags()", "getter", 
/* 356 */           Integer.valueOf(traceRet1));
/*     */     }
/* 358 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMsgFlags(int value) {
/* 367 */     if (Trace.isOn) {
/* 368 */       Trace.data(this, "com.ibm.mq.headers.MQMD", "setMsgFlags(int)", "setter", 
/* 369 */           Integer.valueOf(value));
/*     */     }
/* 371 */     setIntValue(MsgFlags, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOriginalLength() {
/* 380 */     int traceRet1 = getIntValue(OriginalLength);
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.data(this, "com.ibm.mq.headers.MQMD", "getOriginalLength()", "getter", 
/* 383 */           Integer.valueOf(traceRet1));
/*     */     }
/* 385 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOriginalLength(int value) {
/* 394 */     if (Trace.isOn) {
/* 395 */       Trace.data(this, "com.ibm.mq.headers.MQMD", "setOriginalLength(int)", "setter", 
/* 396 */           Integer.valueOf(value));
/*     */     }
/* 398 */     setIntValue(OriginalLength, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyFrom(com.ibm.mq.MQMD md) {
/* 406 */     if (Trace.isOn) {
/* 407 */       Trace.entry(this, "com.ibm.mq.headers.MQMD", "copyFrom(com.ibm.mq.MQMD)", new Object[] { md });
/*     */     }
/*     */     
/* 410 */     super.copyFrom(md);
/*     */     
/* 412 */     if (getVersion() >= 2) {
/* 413 */       setGroupId(md.groupId);
/* 414 */       setMsgSeqNumber(md.messageSequenceNumber);
/* 415 */       setOffset(md.offset);
/* 416 */       setMsgFlags(md.messageFlags);
/* 417 */       setOriginalLength(md.originalLength);
/*     */     } 
/*     */     
/* 420 */     if (Trace.isOn) {
/* 421 */       Trace.exit(this, "com.ibm.mq.headers.MQMD", "copyFrom(com.ibm.mq.MQMD)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyTo(com.ibm.mq.MQMD md) throws MQDataException {
/* 431 */     if (Trace.isOn) {
/* 432 */       Trace.entry(this, "com.ibm.mq.headers.MQMD", "copyTo(com.ibm.mq.MQMD)", new Object[] { md });
/*     */     }
/* 434 */     super.copyTo(md);
/*     */     
/* 436 */     if (getVersion() >= 2) {
/* 437 */       md.groupId = getGroupId();
/* 438 */       md.messageSequenceNumber = getMsgSeqNumber();
/* 439 */       md.offset = getOffset();
/* 440 */       md.messageFlags = getMsgFlags();
/* 441 */       md.originalLength = getOriginalLength();
/*     */     } 
/*     */     
/* 444 */     if (Trace.isOn) {
/* 445 */       Trace.exit(this, "com.ibm.mq.headers.MQMD", "copyTo(com.ibm.mq.MQMD)");
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
/*     */   public MQMD coalesce(MQMDE mde, boolean relink) {
/* 460 */     if (Trace.isOn) {
/* 461 */       Trace.entry(this, "com.ibm.mq.headers.MQMD", "coalesce(MQMDE,boolean)", new Object[] { mde, 
/* 462 */             Boolean.valueOf(relink) });
/*     */     }
/*     */ 
/*     */     
/* 466 */     if (hasExtensionContent() || mde.hasExtensionContent()) {
/* 467 */       setVersion(2);
/*     */       
/* 469 */       setGroupId(mde.getGroupId());
/* 470 */       setMsgSeqNumber(mde.getMsgSeqNumber());
/* 471 */       setOffset(mde.getOffset());
/* 472 */       setMsgFlags(mde.getMsgFlags());
/* 473 */       setOriginalLength(mde.getOriginalLength());
/*     */     } 
/*     */     
/* 476 */     if (relink) {
/* 477 */       setFormat(mde.nextFormat());
/* 478 */       setEncoding(mde.nextEncoding());
/* 479 */       setCodedCharSetId(mde.nextCharacterSet());
/*     */     } 
/*     */     
/* 482 */     if (Trace.isOn) {
/* 483 */       Trace.exit(this, "com.ibm.mq.headers.MQMD", "coalesce(MQMDE,boolean)", this);
/*     */     }
/* 485 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasExtensionContent() {
/* 493 */     if (Trace.isOn) {
/* 494 */       Trace.entry(this, "com.ibm.mq.headers.MQMD", "hasExtensionContent()");
/*     */     }
/*     */ 
/*     */     
/* 498 */     boolean traceRet1 = (getVersion() >= 2 && (!Arrays.equals(CMQC.MQGI_NONE, getGroupId()) || getMsgSeqNumber() != 0 || getOffset() != 0 || getMsgFlags() != 0 || getOriginalLength() != 0));
/*     */     
/* 500 */     if (Trace.isOn) {
/* 501 */       Trace.exit(this, "com.ibm.mq.headers.MQMD", "hasExtensionContent()", 
/* 502 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 504 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQMD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */