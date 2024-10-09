/*     */ package com.ibm.mq.headers.internal.store;
/*     */ 
/*     */ import com.ibm.mq.MQMessage;
/*     */ import com.ibm.mq.headers.Charsets;
/*     */ import com.ibm.mq.headers.internal.Header;
/*     */ import com.ibm.mq.headers.internal.HeaderField;
/*     */ import com.ibm.mq.headers.internal.MQMessageWrapper;
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
/*     */ public final class MQMessageStore
/*     */   extends Store
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/store/MQMessageStore.java";
/*     */   private final MQMessage message;
/*     */   private final int origin;
/*     */   private final int encoding;
/*     */   private final int characterSet;
/*     */   private int size;
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.headers.internal.store.MQMessageStore", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/store/MQMessageStore.java");
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
/*     */   public MQMessageStore(MQMessage message, int encoding, int characterSet, int size) throws IOException {
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "<init>(MQMessage,int,int,int)", new Object[] { message, 
/*  70 */             Integer.valueOf(encoding), 
/*  71 */             Integer.valueOf(characterSet), Integer.valueOf(size) });
/*     */     }
/*  73 */     this.message = message;
/*  74 */     this.origin = message.getDataOffset();
/*  75 */     this.encoding = encoding;
/*  76 */     this.characterSet = characterSet;
/*  77 */     this.size = size;
/*     */ 
/*     */     
/*  80 */     message.seek(this.origin + size);
/*     */     
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "<init>(MQMessage,int,int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(HeaderField field, int offset) throws IOException {
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "getInt(HeaderField,int)", new Object[] { field, 
/*  96 */             Integer.valueOf(offset) });
/*     */     }
/*  98 */     synchronized (this.message) {
/*  99 */       int pos = this.message.getDataOffset();
/*     */       
/* 101 */       this.message.seek(this.origin + offset);
/*     */       
/* 103 */       int result = matchesEncoding(this.message.encoding) ? this.message.readInt() : reverse(this.message
/* 104 */           .readInt());
/*     */       
/* 106 */       this.message.seek(pos);
/*     */       
/* 108 */       if (Trace.isOn) {
/* 109 */         Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "getInt(HeaderField,int)", 
/* 110 */             Integer.valueOf(result));
/*     */       }
/* 112 */       return result;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInt(int offset, int value) throws IOException {
/* 121 */     if (Trace.isOn)
/* 122 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "setInt(int,int)", new Object[] {
/* 123 */             Integer.valueOf(offset), Integer.valueOf(value)
/*     */           }); 
/* 125 */     synchronized (this.message) {
/* 126 */       int pos = this.message.getDataOffset();
/*     */       
/* 128 */       this.message.seek(this.origin + offset);
/* 129 */       this.message.writeInt(matchesEncoding(this.message.encoding) ? value : reverse(value));
/*     */       
/* 131 */       this.message.seek(pos);
/*     */     } 
/*     */     
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "setInt(int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLong(HeaderField field, int offset) throws IOException {
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "getLong(HeaderField,int)", new Object[] { field, 
/* 147 */             Integer.valueOf(offset) });
/*     */     }
/* 149 */     synchronized (this.message) {
/* 150 */       int pos = this.message.getDataOffset();
/*     */       
/* 152 */       this.message.seek(this.origin + offset);
/*     */       
/* 154 */       long result = matchesEncoding(this.message.encoding) ? this.message.readLong() : reverse(this.message
/* 155 */           .readLong());
/*     */       
/* 157 */       this.message.seek(pos);
/*     */       
/* 159 */       if (Trace.isOn) {
/* 160 */         Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "getLong(HeaderField,int)", 
/* 161 */             Long.valueOf(result));
/*     */       }
/* 163 */       return result;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLong(int offset, long value) throws IOException {
/* 172 */     if (Trace.isOn)
/* 173 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "setLong(int,long)", new Object[] {
/* 174 */             Integer.valueOf(offset), Long.valueOf(value)
/*     */           }); 
/* 176 */     synchronized (this.message) {
/* 177 */       int pos = this.message.getDataOffset();
/*     */       
/* 179 */       this.message.seek(this.origin + offset);
/* 180 */       this.message.writeLong(matchesEncoding(this.message.encoding) ? value : reverse(value));
/* 181 */       this.message.seek(pos);
/*     */     } 
/*     */     
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "setLong(int,long)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBytes(HeaderField field, int offset, int length) throws IOException {
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "getBytes(HeaderField,int,int)", new Object[] { field, 
/* 197 */             Integer.valueOf(offset), 
/* 198 */             Integer.valueOf(length) });
/*     */     }
/* 200 */     synchronized (this.message) {
/* 201 */       byte[] bytes = new byte[length];
/* 202 */       int pos = this.message.getDataOffset();
/*     */       
/* 204 */       this.message.seek(this.origin + offset);
/* 205 */       this.message.readFully(bytes);
/* 206 */       this.message.seek(pos);
/*     */       
/* 208 */       if (Trace.isOn) {
/* 209 */         Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "getBytes(HeaderField,int,int)", bytes);
/*     */       }
/*     */       
/* 212 */       return bytes;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBytes(int offset, byte[] bytes, int lengthP) throws IOException {
/* 221 */     if (Trace.isOn)
/* 222 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "setBytes(int,byte [ ],int)", new Object[] {
/* 223 */             Integer.valueOf(offset), bytes, 
/* 224 */             Integer.valueOf(lengthP)
/*     */           }); 
/* 226 */     int length = lengthP;
/* 227 */     synchronized (this.message) {
/* 228 */       int pos = this.message.getDataOffset();
/*     */       
/* 230 */       this.message.seek(this.origin + offset);
/*     */       
/* 232 */       if (bytes.length < length) {
/* 233 */         this.message.write(bytes, 0, bytes.length);
/*     */         
/* 235 */         while (length-- > bytes.length) {
/* 236 */           this.message.writeByte(0);
/*     */         }
/*     */       } else {
/*     */         
/* 240 */         this.message.write(bytes, 0, length);
/*     */       } 
/*     */       
/* 243 */       this.message.seek(pos);
/*     */     } 
/*     */     
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "setBytes(int,byte [ ],int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(HeaderField field, int offset, int length, int ccsid) throws IOException {
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "getString(HeaderField,int,int,int)", new Object[] { field, 
/* 260 */             Integer.valueOf(offset), 
/* 261 */             Integer.valueOf(length), Integer.valueOf(ccsid) });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 267 */     String traceRet1 = Charsets.convert(getBytes(field, offset, length), ccsid, this.encoding);
/*     */     
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "getString(HeaderField,int,int,int)", traceRet1);
/*     */     }
/*     */     
/* 273 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int setString(int offset, String value, int length, int ccsid) throws IOException {
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "setString(int,String,int,int)", new Object[] {
/* 283 */             Integer.valueOf(offset), value, 
/* 284 */             Integer.valueOf(length), Integer.valueOf(ccsid)
/*     */           });
/*     */     }
/*     */ 
/*     */     
/* 289 */     synchronized (this.message) {
/*     */ 
/*     */ 
/*     */       
/* 293 */       byte[] bytes = Charsets.convert(value, ccsid);
/*     */       
/* 295 */       if (length == 0) {
/*     */         
/* 297 */         int totalLength = getPaddedLength(bytes.length, 4);
/*     */         
/* 299 */         allocate((Header)null, offset, value.length(), totalLength);
/* 300 */         setBytes(offset, bytes, bytes.length);
/*     */         
/* 302 */         if (totalLength > bytes.length) {
/* 303 */           fill(offset + bytes.length, totalLength - bytes.length, getPadByte(ccsid));
/*     */         }
/*     */         
/* 306 */         if (Trace.isOn) {
/* 307 */           Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "setString(int,String,int,int)", 
/* 308 */               Integer.valueOf(bytes.length), 1);
/*     */         }
/* 310 */         return bytes.length;
/*     */       } 
/* 312 */       if (length > bytes.length) {
/*     */         
/* 314 */         setBytes(offset, bytes, bytes.length);
/* 315 */         fill(offset + bytes.length, length - bytes.length, getPadByte(ccsid));
/*     */         
/* 317 */         if (Trace.isOn) {
/* 318 */           Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "setString(int,String,int,int)", 
/* 319 */               Integer.valueOf(bytes.length), 2);
/*     */         }
/* 321 */         return bytes.length;
/*     */       } 
/*     */ 
/*     */       
/* 325 */       setBytes(offset, bytes, length);
/*     */       
/* 327 */       if (Trace.isOn) {
/* 328 */         Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "setString(int,String,int,int)", 
/* 329 */             Integer.valueOf(length), 3);
/*     */       }
/* 331 */       return length;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getStrings(HeaderField field, int offsetP, int count, int length, int ccsid) throws IOException {
/* 342 */     if (Trace.isOn) {
/* 343 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "getStrings(HeaderField,int,int,int,int)", new Object[] { field, 
/* 344 */             Integer.valueOf(offsetP), 
/* 345 */             Integer.valueOf(count), Integer.valueOf(length), Integer.valueOf(ccsid) });
/*     */     }
/* 347 */     int offset = offsetP;
/* 348 */     synchronized (this.message) {
/* 349 */       String[] array = new String[count];
/*     */       
/* 351 */       for (int i = 0; i < array.length; i++) {
/* 352 */         array[i] = Charsets.convert(getBytes(field, offset, length), ccsid);
/* 353 */         offset += length;
/*     */       } 
/*     */       
/* 356 */       if (Trace.isOn) {
/* 357 */         Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "getStrings(HeaderField,int,int,int,int)", array);
/*     */       }
/*     */       
/* 360 */       return array;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int setStrings(int offsetP, String[] array, int length, int ccsid) throws IOException {
/* 369 */     if (Trace.isOn)
/* 370 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "setStrings(int,String [ ],int,int)", new Object[] {
/* 371 */             Integer.valueOf(offsetP), array, 
/* 372 */             Integer.valueOf(length), Integer.valueOf(ccsid)
/*     */           }); 
/* 374 */     int offset = offsetP;
/* 375 */     byte[][] bytes = new byte[array.length][];
/* 376 */     int stringLength = length;
/*     */     
/* 378 */     for (int i = 0; i < array.length; i++) {
/* 379 */       bytes[i] = Charsets.convert(array[i], ccsid);
/*     */       
/* 381 */       if (length == 0 && (bytes[i]).length > stringLength) {
/* 382 */         stringLength = (bytes[i]).length;
/*     */       }
/*     */     } 
/*     */     
/* 386 */     int totalLength = getPaddedLength(stringLength * array.length, 4);
/* 387 */     allocate((Header)null, offset + totalLength);
/*     */ 
/*     */     
/* 390 */     for (int j = 0; j < array.length; j++) {
/* 391 */       setBytes(offset, bytes[j], stringLength);
/*     */       
/* 393 */       if (stringLength > (bytes[j]).length) {
/* 394 */         fill(offset + bytes.length, stringLength - bytes.length, getPadByte(ccsid));
/*     */       }
/*     */       
/* 397 */       offset += stringLength;
/*     */     } 
/*     */     
/* 400 */     if (totalLength > stringLength * array.length) {
/* 401 */       fill(offset, totalLength - stringLength * array.length, getPadByte(ccsid));
/*     */     }
/*     */     
/* 404 */     if (Trace.isOn) {
/* 405 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "setStrings(int,String [ ],int,int)", 
/* 406 */           Integer.valueOf(stringLength));
/*     */     }
/* 408 */     return stringLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFrom(DataInput input, int offset, int length) throws IOException {
/* 416 */     if (Trace.isOn) {
/* 417 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "readFrom(DataInput,int,int)", new Object[] { input, 
/* 418 */             Integer.valueOf(offset), 
/* 419 */             Integer.valueOf(length) });
/*     */     }
/* 421 */     if (input == this.message) {
/* 422 */       this.message.seek(this.origin + (this.size = offset + length));
/*     */     }
/* 424 */     else if (input instanceof MQMessageWrapper && ((MQMessageWrapper)input).getDelegate() == this.message) {
/* 425 */       this.message.seek(this.origin + (this.size = offset + length));
/*     */     } else {
/*     */       
/* 428 */       synchronized (this.message) {
/* 429 */         this.message.seek(this.origin + offset);
/*     */         
/* 431 */         for (int i = 0; i < length; i++) {
/* 432 */           this.message.writeByte(input.readByte());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 437 */     if (Trace.isOn) {
/* 438 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "readFrom(DataInput,int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeTo(DataOutput output, int offset, int length) throws IOException {
/* 449 */     if (Trace.isOn) {
/* 450 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "writeTo(DataOutput,int,int)", new Object[] { output, 
/* 451 */             Integer.valueOf(offset), 
/* 452 */             Integer.valueOf(length) });
/*     */     }
/* 454 */     synchronized (this.message) {
/* 455 */       if (output == this.message) {
/*     */ 
/*     */         
/* 458 */         if (offset + this.origin == this.message.getDataOffset())
/*     */         {
/*     */           
/* 461 */           this.message.seek(this.message.getDataOffset() + length);
/*     */         
/*     */         }
/*     */         else
/*     */         {
/* 466 */           byte[] bytes = new byte[length];
/* 467 */           int writePos = this.message.getDataOffset();
/*     */           
/* 469 */           this.message.seek(this.origin + offset);
/* 470 */           this.message.readFully(bytes);
/* 471 */           this.message.seek(writePos);
/* 472 */           this.message.write(bytes);
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 478 */         int pos = this.message.getDataOffset();
/*     */         
/* 480 */         this.message.seek(this.origin + offset);
/*     */         
/* 482 */         for (int i = 0; i < length; i++) {
/* 483 */           output.writeByte(this.message.readByte());
/*     */         }
/*     */         
/* 486 */         this.message.seek(pos);
/*     */       } 
/*     */     } 
/*     */     
/* 490 */     if (Trace.isOn) {
/* 491 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "writeTo(DataOutput,int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyTo(byte[] target, int offset, int length) throws IOException {
/* 502 */     if (Trace.isOn) {
/* 503 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "copyTo(byte [ ],int,int)", new Object[] { target, 
/* 504 */             Integer.valueOf(offset), 
/* 505 */             Integer.valueOf(length) });
/*     */     }
/* 507 */     synchronized (this.message) {
/* 508 */       int pos = this.message.getDataOffset();
/*     */       
/* 510 */       this.message.seek(this.origin + offset);
/* 511 */       this.message.readFully(target, 0, length);
/*     */       
/* 513 */       this.message.seek(pos);
/*     */     } 
/*     */     
/* 516 */     if (Trace.isOn) {
/* 517 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "copyTo(byte [ ],int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear(int offset, int length) throws IOException {
/* 528 */     if (Trace.isOn)
/* 529 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "clear(int,int)", new Object[] {
/* 530 */             Integer.valueOf(offset), Integer.valueOf(length)
/*     */           }); 
/* 532 */     fill(offset, length, (byte)0);
/*     */     
/* 534 */     if (Trace.isOn) {
/* 535 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "clear(int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fill(int offset, int lengthP, byte value) throws IOException {
/* 545 */     if (Trace.isOn)
/* 546 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "fill(int,int,byte)", new Object[] {
/* 547 */             Integer.valueOf(offset), Integer.valueOf(lengthP), Byte.valueOf(value)
/*     */           }); 
/* 549 */     int length = lengthP;
/* 550 */     synchronized (this.message) {
/* 551 */       int pos = this.message.getDataOffset();
/*     */       
/* 553 */       this.message.seek(this.origin + offset);
/*     */       
/* 555 */       while (length-- > 0) {
/* 556 */         this.message.writeByte(value);
/*     */       }
/*     */       
/* 559 */       this.message.seek(pos);
/*     */     } 
/*     */     
/* 562 */     if (Trace.isOn) {
/* 563 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "fill(int,int,byte)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int encoding() {
/* 573 */     if (Trace.isOn) {
/* 574 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "encoding()");
/*     */     }
/* 576 */     if (Trace.isOn) {
/* 577 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "encoding()", 
/* 578 */           Integer.valueOf(this.encoding));
/*     */     }
/* 580 */     return this.encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int characterSet() {
/* 588 */     if (Trace.isOn) {
/* 589 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "characterSet()");
/*     */     }
/* 591 */     if (Trace.isOn) {
/* 592 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "characterSet()", 
/* 593 */           Integer.valueOf(this.characterSet));
/*     */     }
/* 595 */     return this.characterSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasData() {
/* 603 */     if (Trace.isOn) {
/* 604 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "hasData()");
/*     */     }
/* 606 */     if (Trace.isOn) {
/* 607 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "hasData()", 
/* 608 */           Boolean.valueOf(true));
/*     */     }
/* 610 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Store allocate(Header header, int size) throws IOException {
/* 618 */     if (Trace.isOn) {
/* 619 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "allocate(Header,int)", new Object[] { header, Integer.valueOf(size) });
/*     */     }
/* 621 */     resizeBuffer(this.message.getMessageLength(), size);
/*     */     
/* 623 */     if (Trace.isOn) {
/* 624 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "allocate(Header,int)", this);
/*     */     }
/*     */     
/* 627 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Store allocate(Header header, int offset, int oldSize, int newSize) throws IOException {
/* 635 */     if (Trace.isOn) {
/* 636 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "allocate(Header,int,int,int)", new Object[] { header, 
/* 637 */             Integer.valueOf(offset), 
/* 638 */             Integer.valueOf(oldSize), Integer.valueOf(newSize) });
/*     */     }
/* 640 */     int expansion = newSize - oldSize;
/*     */     
/* 642 */     if (expansion != 0) {
/* 643 */       synchronized (this.message) {
/* 644 */         int totalSize = this.message.getMessageLength();
/*     */ 
/*     */         
/* 647 */         if (expansion > 0) {
/* 648 */           resizeBuffer(totalSize, totalSize + expansion); byte[] section;
/* 649 */           copyTo(section = new byte[totalSize - offset], offset, section.length);
/* 650 */           this.message.seek(offset + expansion);
/* 651 */           this.message.write(section);
/*     */         } else {
/*     */           byte[] section;
/* 654 */           copyTo(section = new byte[totalSize - offset + expansion], offset - expansion, section.length);
/*     */           
/* 656 */           this.message.seek(offset);
/* 657 */           this.message.write(section);
/* 658 */           resizeBuffer(totalSize, totalSize + expansion);
/*     */         } 
/*     */       } 
/*     */       
/* 662 */       this.size += expansion;
/*     */     } 
/*     */     
/* 665 */     if (Trace.isOn) {
/* 666 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "allocate(Header,int,int,int)", this);
/*     */     }
/*     */     
/* 669 */     return this;
/*     */   }
/*     */   
/*     */   private void resizeBuffer(int totalSizeP, int size) throws IOException {
/* 673 */     if (Trace.isOn) {
/* 674 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "resizeBuffer(int,final int)", new Object[] {
/* 675 */             Integer.valueOf(totalSizeP), 
/* 676 */             Integer.valueOf(size)
/*     */           });
/*     */     }
/* 679 */     int totalSize = totalSizeP;
/* 680 */     if (size + this.origin > totalSize) {
/* 681 */       this.message.resizeBuffer(size);
/*     */       
/* 683 */       if (size > totalSize) {
/* 684 */         this.message.seek(totalSize);
/*     */         
/* 686 */         while (totalSize++ < size) {
/* 687 */           this.message.writeByte(0);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 692 */     if (Trace.isOn)
/* 693 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.MQMessageStore", "resizeBuffer(int,final int)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\store\MQMessageStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */