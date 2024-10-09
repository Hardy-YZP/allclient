/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.headers.internal.Header;
/*     */ import com.ibm.mq.headers.internal.HeaderField;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
/*     */ import com.ibm.mq.headers.internal.MessageWrapper;
/*     */ import com.ibm.mq.headers.internal.store.Store;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQDH
/*     */   extends Header
/*     */   implements MQChainable
/*     */ {
/*     */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQDH.java";
/*     */   @Deprecated
/*     */   public static final int MQDH_STRUC_LENGTH_FIXED = 48;
/*     */   
/*     */   static {
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.data("com.ibm.mq.headers.MQDH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQDH.java");
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
/*  83 */   static final HeaderType TYPE = new HeaderType("MQDH");
/*     */   
/*  85 */   static final HeaderField StrucId = TYPE.addMQChar("StrucId", "DH  ");
/*  86 */   static final HeaderField Version = TYPE.addMQLong("Version", 1);
/*  87 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 48);
/*  88 */   static final HeaderField Encoding = TYPE.addMQLong("Encoding");
/*  89 */   static final HeaderField CodedCharSetId = TYPE.addMQLong("CodedCharSetId");
/*  90 */   static final HeaderField Format = TYPE.addMQChar("Format", "        ");
/*  91 */   static final HeaderField Flags = TYPE.addMQLong("Flags", 0);
/*  92 */   static final HeaderField PutMsgRecFields = TYPE.addMQLong("PutMsgRecFields");
/*  93 */   static final HeaderField RecsPresent = TYPE.addMQLong("RecsPresent");
/*  94 */   static final HeaderField ObjectRecOffset = TYPE.addMQLong("ObjectRecOffset");
/*  95 */   static final HeaderField PutMsgRecOffset = TYPE.addMQLong("PutMsgRecOffset");
/*  96 */   static final HeaderField DistributionRecords = TYPE.addMQByte("DistributionRecords", null, StrucLength);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQDH() {
/* 103 */     super(TYPE);
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.entry(this, "com.ibm.mq.headers.MQDH", "<init>()");
/*     */     }
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.exit(this, "com.ibm.mq.headers.MQDH", "<init>()");
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
/*     */   public MQDH(DataInput message) throws MQDataException, IOException {
/* 121 */     this();
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry(this, "com.ibm.mq.headers.MQDH", "<init>(DataInput)", new Object[] { message });
/*     */     }
/* 125 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit(this, "com.ibm.mq.headers.MQDH", "<init>(DataInput)");
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
/*     */   public MQDH(DataInput message, int encoding, int characterSet) throws MQDataException {
/* 142 */     this();
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.entry(this, "com.ibm.mq.headers.MQDH", "<init>(DataInput,int,int)", new Object[] { message, 
/* 145 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 148 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 150 */     catch (Exception e) {
/* 151 */       if (Trace.isOn) {
/* 152 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQDH", "<init>(DataInput,int,int)", e);
/*     */       }
/*     */       
/* 155 */       MQDataException traceRet1 = MQDataException.getMQDataException(e);
/* 156 */       if (Trace.isOn) {
/* 157 */         Trace.throwing(this, "com.ibm.mq.headers.MQDH", "<init>(DataInput,int,int)", traceRet1);
/*     */       }
/* 159 */       throw traceRet1;
/*     */     } 
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.exit(this, "com.ibm.mq.headers.MQDH", "<init>(DataInput,int,int)");
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
/* 173 */     String traceRet1 = getStringValue(StrucId);
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "getStrucId()", "getter", traceRet1);
/*     */     }
/* 177 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 186 */     int traceRet1 = getIntValue(Version);
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "getVersion()", "getter", 
/* 189 */           Integer.valueOf(traceRet1));
/*     */     }
/* 191 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 200 */     int traceRet1 = getIntValue(StrucLength);
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "getStrucLength()", "getter", 
/* 203 */           Integer.valueOf(traceRet1));
/*     */     }
/* 205 */     return traceRet1;
/*     */   }
/*     */   
/*     */   private void setStrucLength(int strucLength) {
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "setStrucLength(int)", "setter", 
/* 211 */           Integer.valueOf(strucLength));
/*     */     }
/* 213 */     setIntValue(StrucLength, strucLength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEncoding() {
/* 222 */     int traceRet1 = getIntValue(Encoding);
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "getEncoding()", "getter", 
/* 225 */           Integer.valueOf(traceRet1));
/*     */     }
/* 227 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncoding(int value) {
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "setEncoding(int)", "setter", 
/* 238 */           Integer.valueOf(value));
/*     */     }
/* 240 */     setIntValue(Encoding, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 249 */     int traceRet1 = getIntValue(CodedCharSetId);
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "getCodedCharSetId()", "getter", 
/* 252 */           Integer.valueOf(traceRet1));
/*     */     }
/* 254 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int value) {
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "setCodedCharSetId(int)", "setter", 
/* 265 */           Integer.valueOf(value));
/*     */     }
/* 267 */     setIntValue(CodedCharSetId, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 276 */     String traceRet1 = getStringValue(Format);
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "getFormat()", "getter", traceRet1);
/*     */     }
/* 280 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(String value) {
/* 289 */     if (Trace.isOn) {
/* 290 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "setFormat(String)", "setter", value);
/*     */     }
/* 292 */     setStringValue(Format, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlags() {
/* 301 */     int traceRet1 = getIntValue(Flags);
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "getFlags()", "getter", 
/* 304 */           Integer.valueOf(traceRet1));
/*     */     }
/* 306 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int value) {
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "setFlags(int)", "setter", 
/* 317 */           Integer.valueOf(value));
/*     */     }
/* 319 */     setIntValue(Flags, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPutMsgRecFields() {
/* 328 */     int traceRet1 = getIntValue(PutMsgRecFields);
/* 329 */     if (Trace.isOn) {
/* 330 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "getPutMsgRecFields()", "getter", 
/* 331 */           Integer.valueOf(traceRet1));
/*     */     }
/* 333 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPutMsgRecFields(int value) {
/* 342 */     if (Trace.isOn) {
/* 343 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "setPutMsgRecFields(int)", "setter", 
/* 344 */           Integer.valueOf(value));
/*     */     }
/* 346 */     setIntValue(PutMsgRecFields, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecsPresent() {
/* 355 */     int traceRet1 = getIntValue(RecsPresent);
/* 356 */     if (Trace.isOn) {
/* 357 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "getRecsPresent()", "getter", 
/* 358 */           Integer.valueOf(traceRet1));
/*     */     }
/* 360 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRecsPresent(int value) {
/* 369 */     if (Trace.isOn) {
/* 370 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "setRecsPresent(int)", "setter", 
/* 371 */           Integer.valueOf(value));
/*     */     }
/* 373 */     setIntValue(RecsPresent, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getObjectRecOffset() {
/* 382 */     int traceRet1 = getIntValue(ObjectRecOffset);
/* 383 */     if (Trace.isOn) {
/* 384 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "getObjectRecOffset()", "getter", 
/* 385 */           Integer.valueOf(traceRet1));
/*     */     }
/* 387 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectRecOffset(int value) {
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "setObjectRecOffset(int)", "setter", 
/* 398 */           Integer.valueOf(value));
/*     */     }
/* 400 */     setIntValue(ObjectRecOffset, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPutMsgRecOffset() {
/* 409 */     int traceRet1 = getIntValue(PutMsgRecOffset);
/* 410 */     if (Trace.isOn) {
/* 411 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "getPutMsgRecOffset()", "getter", 
/* 412 */           Integer.valueOf(traceRet1));
/*     */     }
/* 414 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPutMsgRecOffset(int value) {
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "setPutMsgRecOffset(int)", "setter", 
/* 425 */           Integer.valueOf(value));
/*     */     }
/* 427 */     setIntValue(PutMsgRecOffset, value);
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
/*     */   public List<?> getDistributionRecords() throws MQDataException, IOException {
/* 444 */     if (Trace.isOn) {
/* 445 */       Trace.entry(this, "com.ibm.mq.headers.MQDH", "getDistributionRecords()");
/*     */     }
/* 447 */     Store store = store();
/*     */     
/* 449 */     if (store.hasData()) {
/* 450 */       List<MQDR> list = new ArrayList<>();
/*     */ 
/*     */ 
/*     */       
/* 454 */       int objectRecOffset = getObjectRecOffset();
/* 455 */       int putMsgRecOffset = getPutMsgRecOffset();
/* 456 */       int putMsgRecFields = getPutMsgRecFields();
/* 457 */       int count = getRecsPresent();
/* 458 */       int size = getStrucLength();
/*     */       
/* 460 */       if (objectRecOffset < 48 || objectRecOffset + count * 
/* 461 */         MQDR.getMQORSize() > size) {
/* 462 */         MQDataException traceRet1 = new MQDataException(2, 3238, "Invalid combination of objectRecOffset, recsPresent and strucLength field values");
/*     */ 
/*     */ 
/*     */         
/* 466 */         if (Trace.isOn) {
/* 467 */           Trace.throwing(this, "com.ibm.mq.headers.MQDH", "getDistributionRecords()", traceRet1, 1);
/*     */         }
/*     */         
/* 470 */         throw traceRet1;
/*     */       } 
/*     */ 
/*     */       
/* 474 */       if (putMsgRecOffset < 48 || putMsgRecOffset + count * 
/* 475 */         MQDR.getPmrSize(putMsgRecFields) > size) {
/* 476 */         MQDataException traceRet2 = new MQDataException(2, 3238, "Invalid combination of putMsgRecOffset, putMsgRecFields, recsPresent and strucLength field values");
/*     */ 
/*     */         
/* 479 */         if (Trace.isOn) {
/* 480 */           Trace.throwing(this, "com.ibm.mq.headers.MQDH", "getDistributionRecords()", traceRet2, 2);
/*     */         }
/*     */         
/* 483 */         throw traceRet2;
/*     */       } 
/*     */ 
/*     */       
/* 487 */       while (count-- > 0) {
/* 488 */         MQDR dr = new MQDR(store, objectRecOffset, putMsgRecOffset, putMsgRecFields);
/*     */         
/* 490 */         list.add(dr);
/* 491 */         objectRecOffset += MQDR.getMQORSize();
/* 492 */         putMsgRecOffset += MQDR.getPmrSize(putMsgRecFields);
/*     */       } 
/*     */       
/* 495 */       if (Trace.isOn) {
/* 496 */         Trace.exit(this, "com.ibm.mq.headers.MQDH", "getDistributionRecords()", list, 1);
/*     */       }
/* 498 */       return list;
/*     */     } 
/*     */     
/* 501 */     if (Trace.isOn) {
/* 502 */       Trace.exit(this, "com.ibm.mq.headers.MQDH", "getDistributionRecords()", Collections.EMPTY_LIST, 2);
/*     */     }
/*     */     
/* 505 */     return Collections.EMPTY_LIST;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDistributionRecords(List<?> distributionRecords) throws IOException {
/* 515 */     if (Trace.isOn) {
/* 516 */       Trace.data(this, "com.ibm.mq.headers.MQDH", "setDistributionRecords(List<?>)", "setter", distributionRecords);
/*     */     }
/*     */     
/* 519 */     List<?> records = distributionRecords;
/* 520 */     if (distributionRecords == null) {
/* 521 */       records = new ArrayList();
/*     */     }
/*     */     
/* 524 */     Iterator<?> it = records.iterator();
/* 525 */     int count = records.size();
/* 526 */     int putMsgRecFields = getPutMsgRecFields();
/* 527 */     int totalOrSize = count * MQDR.getMQORSize();
/* 528 */     int totalPmrSize = count * MQDR.getPmrSize(putMsgRecFields);
/* 529 */     int offset = 48;
/* 530 */     Store store = store(offset + totalOrSize + totalPmrSize);
/*     */     
/* 532 */     while (it.hasNext()) {
/* 533 */       offset += ((MQDR)it.next()).writeTo(store, offset, putMsgRecFields);
/*     */     }
/*     */     
/* 536 */     setStrucLength(offset);
/* 537 */     setRecsPresent(count);
/* 538 */     setObjectRecOffset(48);
/* 539 */     setPutMsgRecOffset(48 + totalOrSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextEncoding() {
/* 550 */     if (Trace.isOn) {
/* 551 */       Trace.entry(this, "com.ibm.mq.headers.MQDH", "nextEncoding()");
/*     */     }
/* 553 */     int traceRet1 = getEncoding();
/*     */     
/* 555 */     if (Trace.isOn) {
/* 556 */       Trace.exit(this, "com.ibm.mq.headers.MQDH", "nextEncoding()", Integer.valueOf(traceRet1));
/*     */     }
/* 558 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextEncoding(int value) {
/* 566 */     if (Trace.isOn)
/* 567 */       Trace.entry(this, "com.ibm.mq.headers.MQDH", "nextEncoding(int)", new Object[] {
/* 568 */             Integer.valueOf(value)
/*     */           }); 
/* 570 */     setEncoding(value);
/*     */     
/* 572 */     if (Trace.isOn) {
/* 573 */       Trace.exit(this, "com.ibm.mq.headers.MQDH", "nextEncoding(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextCharacterSet() {
/* 583 */     if (Trace.isOn) {
/* 584 */       Trace.entry(this, "com.ibm.mq.headers.MQDH", "nextCharacterSet()");
/*     */     }
/* 586 */     int traceRet1 = getCodedCharSetId();
/*     */     
/* 588 */     if (Trace.isOn) {
/* 589 */       Trace.exit(this, "com.ibm.mq.headers.MQDH", "nextCharacterSet()", Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 592 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextCharacterSet(int value) {
/* 600 */     if (Trace.isOn)
/* 601 */       Trace.entry(this, "com.ibm.mq.headers.MQDH", "nextCharacterSet(int)", new Object[] {
/* 602 */             Integer.valueOf(value)
/*     */           }); 
/* 604 */     setCodedCharSetId(value);
/*     */     
/* 606 */     if (Trace.isOn) {
/* 607 */       Trace.exit(this, "com.ibm.mq.headers.MQDH", "nextCharacterSet(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String nextFormat() {
/* 617 */     if (Trace.isOn) {
/* 618 */       Trace.entry(this, "com.ibm.mq.headers.MQDH", "nextFormat()");
/*     */     }
/* 620 */     String traceRet1 = getFormat();
/*     */     
/* 622 */     if (Trace.isOn) {
/* 623 */       Trace.exit(this, "com.ibm.mq.headers.MQDH", "nextFormat()", traceRet1);
/*     */     }
/* 625 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextFormat(String value) {
/* 633 */     if (Trace.isOn) {
/* 634 */       Trace.entry(this, "com.ibm.mq.headers.MQDH", "nextFormat(String)", new Object[] { value });
/*     */     }
/* 636 */     setFormat(value);
/*     */     
/* 638 */     if (Trace.isOn) {
/* 639 */       Trace.exit(this, "com.ibm.mq.headers.MQDH", "nextFormat(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String format() {
/* 649 */     if (Trace.isOn) {
/* 650 */       Trace.entry(this, "com.ibm.mq.headers.MQDH", "format()");
/*     */     }
/* 652 */     if (Trace.isOn) {
/* 653 */       Trace.exit(this, "com.ibm.mq.headers.MQDH", "format()", "MQHDIST ");
/*     */     }
/* 655 */     return "MQHDIST ";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DistributionRecord createDistributionRecord(String objectName, String objectQMgrName) {
/* 707 */     if (Trace.isOn) {
/* 708 */       Trace.entry("com.ibm.mq.headers.MQDH", "createDistributionRecord(String,String)", new Object[] { objectName, objectQMgrName });
/*     */     }
/*     */     
/* 711 */     DistributionRecord traceRet1 = new MQDR(objectName, objectQMgrName);
/* 712 */     if (Trace.isOn) {
/* 713 */       Trace.exit("com.ibm.mq.headers.MQDH", "createDistributionRecord(String,String)", traceRet1);
/*     */     }
/* 715 */     return traceRet1;
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
/*     */   public static DistributionRecord createDistributionRecord(String objectName, String objectQMgrName, byte[] msgId, byte[] correlId, byte[] groupId, int feedback, byte[] accountingToken) {
/* 732 */     if (Trace.isOn) {
/* 733 */       Trace.entry("com.ibm.mq.headers.MQDH", "createDistributionRecord(String,String,byte [ ],byte [ ],byte [ ],int,byte [ ])", new Object[] { objectName, objectQMgrName, msgId, correlId, groupId, 
/*     */ 
/*     */             
/* 736 */             Integer.valueOf(feedback), accountingToken });
/*     */     }
/* 738 */     DistributionRecord traceRet1 = new MQDR(objectName, objectQMgrName, msgId, correlId, groupId, feedback, accountingToken);
/*     */     
/* 740 */     if (Trace.isOn) {
/* 741 */       Trace.exit("com.ibm.mq.headers.MQDH", "createDistributionRecord(String,String,byte [ ],byte [ ],byte [ ],int,byte [ ])", traceRet1);
/*     */     }
/*     */ 
/*     */     
/* 745 */     return traceRet1;
/*     */   }
/*     */   
/*     */   static interface DistributionRecord {
/*     */     String getObjectName();
/*     */     
/*     */     String getObjectQMgrName();
/*     */     
/*     */     byte[] getMsgId();
/*     */     
/*     */     byte[] getCorrelId();
/*     */     
/*     */     byte[] getGroupId();
/*     */     
/*     */     int getFeedback();
/*     */     
/*     */     byte[] getAccountingToken();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQDH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */