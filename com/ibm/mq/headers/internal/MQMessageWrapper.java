/*     */ package com.ibm.mq.headers.internal;
/*     */ 
/*     */ import com.ibm.mq.MQMessage;
/*     */ import com.ibm.mq.headers.internal.store.MQMessageStore;
/*     */ import com.ibm.mq.headers.internal.store.Store;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.EOFException;
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
/*     */ public class MQMessageWrapper
/*     */   extends MessageWrapper
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQMessageWrapper.java";
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.headers.internal.MQMessageWrapper", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQMessageWrapper.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   MQMessage delegate = null;
/*     */   
/*     */   static final int SHUFFLE_SIZE = 4096;
/*     */ 
/*     */   
/*     */   public MQMessageWrapper(DataInput in) {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "<init>(DataInput)", new Object[] { in });
/*     */     }
/*     */     
/*  64 */     if (in instanceof MQMessage) {
/*  65 */       this.delegate = (MQMessage)in;
/*     */     } else {
/*     */       
/*  68 */       UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/*  69 */       if (Trace.isOn) {
/*  70 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "<init>(DataInput)", traceRet1);
/*     */       }
/*     */       
/*  73 */       throw traceRet1;
/*     */     } 
/*     */     
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "<init>(DataInput)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQMessageWrapper(DataOutput out) {
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "<init>(DataOutput)", new Object[] { out });
/*     */     }
/*     */     
/*  90 */     if (out instanceof MQMessage) {
/*  91 */       this.delegate = (MQMessage)out;
/*     */     } else {
/*     */       
/*  94 */       UnsupportedOperationException traceRet1 = new UnsupportedOperationException(HeaderMessages.getMessage("MQHDR0001"));
/*  95 */       if (Trace.isOn) {
/*  96 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "<init>(DataOutput)", traceRet1);
/*     */       }
/*     */       
/*  99 */       throw traceRet1;
/*     */     } 
/*     */     
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "<init>(DataOutput)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getDelegate() {
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.data(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "getDelegate()", "getter", this.delegate);
/*     */     }
/*     */     
/* 117 */     return this.delegate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDataOffset() throws IOException {
/* 125 */     int traceRet1 = this.delegate.getDataOffset();
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.data(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "getDataOffset()", "getter", 
/* 128 */           Integer.valueOf(traceRet1));
/*     */     }
/* 130 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void seek(int offset) throws IOException {
/* 138 */     if (Trace.isOn)
/* 139 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "seek(int)", new Object[] {
/* 140 */             Integer.valueOf(offset)
/*     */           }); 
/* 142 */     this.delegate.seek(offset);
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "seek(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void writeBytes(String s) throws IOException {
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "writeBytes(String)", new Object[] { s });
/*     */     }
/*     */     
/* 159 */     this.delegate.writeBytes(s);
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "writeBytes(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readInt() throws IOException {
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readInt()");
/*     */     }
/* 174 */     int traceRet1 = this.delegate.readInt();
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readInt()", 
/* 177 */           Integer.valueOf(traceRet1));
/*     */     }
/* 179 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeString(String string) throws IOException {
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "writeString(String)", new Object[] { string });
/*     */     }
/*     */     
/* 191 */     this.delegate.writeString(string);
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "writeString(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeInt(int i) throws IOException {
/* 203 */     if (Trace.isOn)
/* 204 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "writeInt(int)", new Object[] {
/* 205 */             Integer.valueOf(i)
/*     */           }); 
/* 207 */     this.delegate.writeInt(i);
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "writeInt(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCharacterSet() {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.data(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "getCharacterSet()", "getter", 
/* 221 */           Integer.valueOf(this.delegate.characterSet));
/*     */     }
/* 223 */     return this.delegate.characterSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEncoding() {
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.data(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "getEncoding()", "getter", 
/* 233 */           Integer.valueOf(this.delegate.encoding));
/*     */     }
/* 235 */     return this.delegate.encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Store getStore(int encoding, int characterSet, int size) throws IOException {
/* 243 */     if (Trace.isOn)
/* 244 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "getStore(int,int,int)", new Object[] {
/* 245 */             Integer.valueOf(encoding), Integer.valueOf(characterSet), 
/* 246 */             Integer.valueOf(size)
/*     */           }); 
/* 248 */     MQMessageStore mQMessageStore = new MQMessageStore(this.delegate, encoding, characterSet, size);
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "getStore(int,int,int)", mQMessageStore);
/*     */     }
/*     */     
/* 253 */     return (Store)mQMessageStore;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataOutput getReversed() {
/* 261 */     DataOutput traceRet1 = new ReversingDataOutput((DataOutput)this.delegate);
/* 262 */     if (Trace.isOn) {
/* 263 */       Trace.data(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "getReversed()", "getter", traceRet1);
/*     */     }
/*     */     
/* 266 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFully(byte[] b, int off, int len) throws IOException {
/* 274 */     if (Trace.isOn) {
/* 275 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readFully(byte [ ],int,int)", new Object[] { b, 
/* 276 */             Integer.valueOf(off), Integer.valueOf(len) });
/*     */     }
/*     */     
/* 279 */     this.delegate.readFully(b, off, len);
/* 280 */     if (Trace.isOn) {
/* 281 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readFully(byte [ ],int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFully(byte[] b) throws IOException {
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readFully(byte [ ])", new Object[] { b });
/*     */     }
/*     */     
/* 296 */     this.delegate.readFully(b);
/* 297 */     if (Trace.isOn) {
/* 298 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readFully(byte [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "write(byte [ ],int,int)", new Object[] { b, 
/* 310 */             Integer.valueOf(off), Integer.valueOf(len) });
/*     */     }
/* 312 */     this.delegate.write(b, off, len);
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "write(byte [ ],int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/* 324 */     if (Trace.isOn) {
/* 325 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "write(byte [ ])", new Object[] { b });
/*     */     }
/*     */     
/* 328 */     this.delegate.write(b);
/* 329 */     if (Trace.isOn) {
/* 330 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "write(byte [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.data(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "getFormat()", "getter", this.delegate.format);
/*     */     }
/*     */     
/* 344 */     return this.delegate.format;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalMessageLength() {
/* 352 */     int traceRet1 = this.delegate.getTotalMessageLength();
/* 353 */     if (Trace.isOn) {
/* 354 */       Trace.data(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "getTotalMessageLength()", "getter", 
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
/*     */   
/*     */   public void shuffle(int from, int to, int length) throws IOException {
/* 367 */     if (Trace.isOn)
/* 368 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "shuffle(int,int,int)", new Object[] {
/* 369 */             Integer.valueOf(from), Integer.valueOf(to), Integer.valueOf(length)
/*     */           }); 
/* 371 */     int savedPos = getDataOffset();
/* 372 */     int offset = to - from;
/* 373 */     byte[] bytes = new byte[Math.min(length, 4096)];
/*     */     
/* 375 */     if (offset > 0) {
/*     */ 
/*     */       
/* 378 */       int remainder = length % bytes.length;
/* 379 */       int pos = from + length - remainder;
/*     */       
/* 381 */       if (remainder != 0) {
/* 382 */         seek(pos);
/* 383 */         readFully(bytes, 0, remainder);
/* 384 */         seek(pos + offset);
/* 385 */         write(bytes, 0, remainder);
/*     */       } 
/*     */       
/* 388 */       while (pos > from) {
/* 389 */         seek(pos -= bytes.length);
/* 390 */         readFully(bytes);
/* 391 */         seek(pos + offset);
/* 392 */         write(bytes);
/*     */       } 
/* 394 */     } else if (offset < 0) {
/*     */ 
/*     */       
/* 397 */       int remainder = length % bytes.length;
/* 398 */       int pos = from;
/*     */       
/* 400 */       if (remainder != 0) {
/* 401 */         seek(pos);
/* 402 */         readFully(bytes, 0, remainder);
/* 403 */         seek(pos + offset);
/* 404 */         write(bytes, 0, remainder);
/*     */       } 
/*     */       
/* 407 */       pos += remainder;
/*     */       
/* 409 */       while (pos <= from + length - bytes.length) {
/* 410 */         seek(pos);
/* 411 */         readFully(bytes);
/* 412 */         seek(pos + offset);
/* 413 */         write(bytes);
/* 414 */         pos += bytes.length;
/*     */       } 
/*     */     } 
/*     */     
/* 418 */     seek(savedPos);
/* 419 */     if (Trace.isOn) {
/* 420 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "shuffle(int,int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resizeBuffer(int size) throws IOException {
/* 430 */     if (Trace.isOn)
/* 431 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "resizeBuffer(int)", new Object[] {
/* 432 */             Integer.valueOf(size)
/*     */           }); 
/* 434 */     this.delegate.resizeBuffer(size);
/* 435 */     if (Trace.isOn) {
/* 436 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "resizeBuffer(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMQMessage() {
/* 446 */     if (Trace.isOn) {
/* 447 */       Trace.data(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "isMQMessage()", "getter", 
/* 448 */           Boolean.valueOf(true));
/*     */     }
/* 450 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMessageType() throws IOException {
/* 459 */     int type = this.delegate.readInt();
/* 460 */     if (this.delegate.skipBytes(-4) != -4) {
/* 461 */       EOFException traceRet1 = new EOFException();
/* 462 */       if (Trace.isOn) {
/* 463 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "getMessageType()", traceRet1);
/*     */       }
/*     */       
/* 466 */       throw traceRet1;
/*     */     } 
/* 468 */     if (Trace.isOn) {
/* 469 */       Trace.data(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "getMessageType()", "getter", 
/* 470 */           Integer.valueOf(type));
/*     */     }
/* 472 */     return type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean readBoolean() throws IOException {
/* 480 */     if (Trace.isOn) {
/* 481 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readBoolean()");
/*     */     }
/* 483 */     boolean traceRet1 = this.delegate.readBoolean();
/* 484 */     if (Trace.isOn) {
/* 485 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readBoolean()", 
/* 486 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 488 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte readByte() throws IOException {
/* 496 */     if (Trace.isOn) {
/* 497 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readByte()");
/*     */     }
/* 499 */     byte traceRet1 = this.delegate.readByte();
/* 500 */     if (Trace.isOn) {
/* 501 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readByte()", 
/* 502 */           Byte.valueOf(traceRet1));
/*     */     }
/* 504 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char readChar() throws IOException {
/* 512 */     if (Trace.isOn) {
/* 513 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readChar()");
/*     */     }
/* 515 */     char traceRet1 = this.delegate.readChar();
/* 516 */     if (Trace.isOn) {
/* 517 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readChar()", 
/* 518 */           Character.valueOf(traceRet1));
/*     */     }
/* 520 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double readDouble() throws IOException {
/* 528 */     if (Trace.isOn) {
/* 529 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readDouble()");
/*     */     }
/* 531 */     double traceRet1 = this.delegate.readDouble();
/* 532 */     if (Trace.isOn) {
/* 533 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readDouble()", 
/* 534 */           Double.valueOf(traceRet1));
/*     */     }
/* 536 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float readFloat() throws IOException {
/* 544 */     if (Trace.isOn) {
/* 545 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readFloat()");
/*     */     }
/* 547 */     float traceRet1 = this.delegate.readFloat();
/* 548 */     if (Trace.isOn) {
/* 549 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readFloat()", 
/* 550 */           Float.valueOf(traceRet1));
/*     */     }
/* 552 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String readLine() throws IOException {
/* 560 */     if (Trace.isOn) {
/* 561 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readLine()");
/*     */     }
/* 563 */     String traceRet1 = this.delegate.readLine();
/* 564 */     if (Trace.isOn) {
/* 565 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readLine()", traceRet1);
/*     */     }
/* 567 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long readLong() throws IOException {
/* 575 */     if (Trace.isOn) {
/* 576 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readLong()");
/*     */     }
/* 578 */     long traceRet1 = this.delegate.readLong();
/* 579 */     if (Trace.isOn) {
/* 580 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readLong()", 
/* 581 */           Long.valueOf(traceRet1));
/*     */     }
/* 583 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short readShort() throws IOException {
/* 591 */     if (Trace.isOn) {
/* 592 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readShort()");
/*     */     }
/* 594 */     short traceRet1 = this.delegate.readShort();
/* 595 */     if (Trace.isOn) {
/* 596 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readShort()", 
/* 597 */           Short.valueOf(traceRet1));
/*     */     }
/* 599 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String readUTF() throws IOException {
/* 607 */     if (Trace.isOn) {
/* 608 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readUTF()");
/*     */     }
/* 610 */     String traceRet1 = this.delegate.readUTF();
/* 611 */     if (Trace.isOn) {
/* 612 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readUTF()", traceRet1);
/*     */     }
/* 614 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readUnsignedByte() throws IOException {
/* 622 */     if (Trace.isOn) {
/* 623 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readUnsignedByte()");
/*     */     }
/* 625 */     int traceRet1 = this.delegate.readUnsignedByte();
/* 626 */     if (Trace.isOn) {
/* 627 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readUnsignedByte()", 
/* 628 */           Integer.valueOf(traceRet1));
/*     */     }
/* 630 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readUnsignedShort() throws IOException {
/* 638 */     if (Trace.isOn) {
/* 639 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readUnsignedShort()");
/*     */     }
/* 641 */     int traceRet1 = this.delegate.readUnsignedShort();
/* 642 */     if (Trace.isOn) {
/* 643 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "readUnsignedShort()", 
/* 644 */           Integer.valueOf(traceRet1));
/*     */     }
/* 646 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int skipBytes(int n) throws IOException {
/* 654 */     if (Trace.isOn)
/* 655 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "skipBytes(int)", new Object[] {
/* 656 */             Integer.valueOf(n)
/*     */           }); 
/* 658 */     int traceRet1 = this.delegate.skipBytes(n);
/* 659 */     if (Trace.isOn) {
/* 660 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQMessageWrapper", "skipBytes(int)", 
/* 661 */           Integer.valueOf(traceRet1));
/*     */     }
/* 663 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\MQMessageWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */