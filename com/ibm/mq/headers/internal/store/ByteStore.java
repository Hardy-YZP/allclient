/*     */ package com.ibm.mq.headers.internal.store;
/*     */ 
/*     */ import com.ibm.mq.headers.Charsets;
/*     */ import com.ibm.mq.headers.internal.Header;
/*     */ import com.ibm.mq.headers.internal.HeaderField;
/*     */ import com.ibm.mq.headers.internal.HexString;
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
/*     */ public final class ByteStore
/*     */   extends Store
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/store/ByteStore.java";
/*     */   private final int encoding;
/*     */   private final int characterSet;
/*     */   private byte[] buffer;
/*     */   private int size;
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.headers.internal.store.ByteStore", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/store/ByteStore.java");
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
/*     */   public ByteStore(byte[] buffer, int encoding, int characterSet) {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "<init>(byte [ ],int,int)", new Object[] { buffer, 
/*  65 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*  67 */     this.buffer = buffer;
/*  68 */     this.size = buffer.length;
/*  69 */     this.encoding = encoding;
/*  70 */     this.characterSet = characterSet;
/*     */     
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "<init>(byte [ ],int,int)");
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
/*     */   public ByteStore(DataInput input, int encoding, int characterSet, int size) throws IOException {
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "<init>(DataInput,int,int,int)", new Object[] { input, 
/*  88 */             Integer.valueOf(encoding), 
/*  89 */             Integer.valueOf(characterSet), Integer.valueOf(size) });
/*     */     }
/*  91 */     input.readFully(this.buffer = new byte[this.size = size]);
/*  92 */     this.encoding = encoding;
/*  93 */     this.characterSet = characterSet;
/*     */     
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "<init>(DataInput,int,int,int)");
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
/*     */   public ByteStore(int size, DataInput input, ByteStore preceding) throws IOException {
/* 109 */     if (Trace.isOn)
/* 110 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "<init>(int,DataInput,ByteStore)", new Object[] {
/* 111 */             Integer.valueOf(size), input, preceding
/*     */           }); 
/* 113 */     this.buffer = new byte[preceding.buffer.length + size];
/* 114 */     this.encoding = preceding.encoding;
/* 115 */     this.characterSet = preceding.characterSet;
/*     */     
/* 117 */     System.arraycopy(preceding.buffer, 0, this.buffer, 0, preceding.buffer.length);
/*     */     
/* 119 */     input.readFully(this.buffer);
/*     */     
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "<init>(int,DataInput,ByteStore)");
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
/*     */   public ByteStore(int size, Store preceding, int precedingSize) throws IOException {
/* 135 */     if (Trace.isOn)
/* 136 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "<init>(int,Store,int)", new Object[] {
/* 137 */             Integer.valueOf(size), preceding, Integer.valueOf(precedingSize)
/*     */           }); 
/* 139 */     preceding.copyTo(this.buffer = new byte[size], 0, precedingSize);
/* 140 */     this.encoding = preceding.encoding();
/* 141 */     this.characterSet = preceding.characterSet();
/* 142 */     this.padByte = preceding.padByte;
/*     */     
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "<init>(int,Store,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(HeaderField field, int offset) {
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "getInt(HeaderField,int)", new Object[] { field, 
/* 157 */             Integer.valueOf(offset) });
/*     */     }
/* 159 */     if (isReversed(this.encoding)) {
/* 160 */       int traceRet1 = this.buffer[offset] & 0xFF | (this.buffer[offset + 1] & 0xFF) << 8 | (this.buffer[offset + 2] & 0xFF) << 16 | (this.buffer[offset + 3] & 0xFF) << 24;
/*     */       
/* 162 */       if (Trace.isOn) {
/* 163 */         Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "getInt(HeaderField,int)", 
/* 164 */             Integer.valueOf(traceRet1), 1);
/*     */       }
/* 166 */       return traceRet1;
/*     */     } 
/* 168 */     int traceRet2 = (this.buffer[offset] & 0xFF) << 24 | (this.buffer[offset + 1] & 0xFF) << 16 | (this.buffer[offset + 2] & 0xFF) << 8 | this.buffer[offset + 3] & 0xFF;
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "getInt(HeaderField,int)", 
/* 171 */           Integer.valueOf(traceRet2), 2);
/*     */     }
/* 173 */     return traceRet2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInt(int offset, int value) {
/* 181 */     if (Trace.isOn)
/* 182 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "setInt(int,int)", new Object[] {
/* 183 */             Integer.valueOf(offset), Integer.valueOf(value)
/*     */           }); 
/* 185 */     if (isReversed(this.encoding)) {
/* 186 */       this.buffer[offset] = (byte)(value & 0xFF);
/* 187 */       this.buffer[offset + 1] = (byte)(value >> 8 & 0xFF);
/* 188 */       this.buffer[offset + 2] = (byte)(value >> 16 & 0xFF);
/* 189 */       this.buffer[offset + 3] = (byte)(value >> 24 & 0xFF);
/*     */     } else {
/*     */       
/* 192 */       this.buffer[offset] = (byte)(value >> 24 & 0xFF);
/* 193 */       this.buffer[offset + 1] = (byte)(value >> 16 & 0xFF);
/* 194 */       this.buffer[offset + 2] = (byte)(value >> 8 & 0xFF);
/* 195 */       this.buffer[offset + 3] = (byte)(value & 0xFF);
/*     */     } 
/*     */     
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "setInt(int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLong(HeaderField field, int offset) {
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "getLong(HeaderField,int)", new Object[] { field, 
/* 211 */             Integer.valueOf(offset) });
/*     */     }
/* 213 */     if (isReversed(this.encoding)) {
/* 214 */       long traceRet1 = this.buffer[offset] & 0xFFL | (this.buffer[offset + 1] & 0xFFL) << 8L | (this.buffer[offset + 2] & 0xFFL) << 16L | (this.buffer[offset + 3] & 0xFFL) << 24L | (this.buffer[offset + 4] & 0xFFL) << 32L | (this.buffer[offset + 5] & 0xFFL) << 40L | (this.buffer[offset + 6] & 0xFFL) << 48L | (this.buffer[offset + 7] & 0xFFL) << 56L;
/*     */ 
/*     */       
/* 217 */       if (Trace.isOn) {
/* 218 */         Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "getLong(HeaderField,int)", 
/* 219 */             Long.valueOf(traceRet1), 1);
/*     */       }
/* 221 */       return traceRet1;
/*     */     } 
/* 223 */     long traceRet2 = (this.buffer[offset] & 0xFFL) << 56L | (this.buffer[offset + 1] & 0xFFL) << 48L | (this.buffer[offset + 2] & 0xFFL) << 40L | (this.buffer[offset + 3] & 0xFFL) << 32L | (this.buffer[offset + 4] & 0xFFL) << 24L | (this.buffer[offset + 5] & 0xFFL) << 16L | (this.buffer[offset + 6] & 0xFFL) << 8L | this.buffer[offset + 7] & 0xFFL;
/*     */     
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "getLong(HeaderField,int)", 
/* 227 */           Long.valueOf(traceRet2), 2);
/*     */     }
/* 229 */     return traceRet2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLong(int offset, long value) {
/* 237 */     if (Trace.isOn)
/* 238 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "setLong(int,long)", new Object[] {
/* 239 */             Integer.valueOf(offset), Long.valueOf(value)
/*     */           }); 
/* 241 */     if (isReversed(this.encoding)) {
/* 242 */       this.buffer[offset] = (byte)(int)(value & 0xFFL);
/* 243 */       this.buffer[offset + 1] = (byte)(int)(value >> 8L & 0xFFL);
/* 244 */       this.buffer[offset + 2] = (byte)(int)(value >> 16L & 0xFFL);
/* 245 */       this.buffer[offset + 3] = (byte)(int)(value >> 24L & 0xFFL);
/* 246 */       this.buffer[offset + 4] = (byte)(int)(value >> 32L & 0xFFL);
/* 247 */       this.buffer[offset + 5] = (byte)(int)(value >> 40L & 0xFFL);
/* 248 */       this.buffer[offset + 6] = (byte)(int)(value >> 48L & 0xFFL);
/* 249 */       this.buffer[offset + 7] = (byte)(int)(value >> 56L & 0xFFL);
/*     */     } else {
/*     */       
/* 252 */       this.buffer[offset] = (byte)(int)(value >> 56L & 0xFFL);
/* 253 */       this.buffer[offset + 1] = (byte)(int)(value >> 48L & 0xFFL);
/* 254 */       this.buffer[offset + 2] = (byte)(int)(value >> 40L & 0xFFL);
/* 255 */       this.buffer[offset + 3] = (byte)(int)(value >> 32L & 0xFFL);
/* 256 */       this.buffer[offset + 4] = (byte)(int)(value >> 24L & 0xFFL);
/* 257 */       this.buffer[offset + 5] = (byte)(int)(value >> 16L & 0xFFL);
/* 258 */       this.buffer[offset + 6] = (byte)(int)(value >> 8L & 0xFFL);
/* 259 */       this.buffer[offset + 7] = (byte)(int)(value & 0xFFL);
/*     */     } 
/*     */     
/* 262 */     if (Trace.isOn) {
/* 263 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "setLong(int,long)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBytes(HeaderField field, int offset, int length) {
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "getBytes(HeaderField,int,int)", new Object[] { field, 
/* 275 */             Integer.valueOf(offset), 
/* 276 */             Integer.valueOf(length) });
/*     */     }
/* 278 */     byte[] bytes = new byte[length];
/*     */     
/* 280 */     System.arraycopy(this.buffer, offset, bytes, 0, length);
/*     */     
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "getBytes(HeaderField,int,int)", bytes);
/*     */     }
/*     */     
/* 286 */     return bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBytes(int offset, byte[] bytes, int length) throws IOException {
/* 294 */     if (Trace.isOn)
/* 295 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "setBytes(int,byte [ ],int)", new Object[] {
/* 296 */             Integer.valueOf(offset), bytes, 
/* 297 */             Integer.valueOf(length)
/*     */           }); 
/* 299 */     if (length > bytes.length) {
/* 300 */       System.arraycopy(bytes, 0, this.buffer, offset, bytes.length);
/*     */       
/* 302 */       clear(offset + bytes.length, length - bytes.length);
/*     */     } else {
/*     */       
/* 305 */       System.arraycopy(bytes, 0, this.buffer, offset, length);
/*     */     } 
/*     */     
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "setBytes(int,byte [ ],int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(HeaderField field, int offset, int length, int ccsid) throws IOException {
/* 319 */     if (Trace.isOn) {
/* 320 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "getString(HeaderField,int,int,int)", new Object[] { field, 
/* 321 */             Integer.valueOf(offset), 
/* 322 */             Integer.valueOf(length), Integer.valueOf(ccsid) });
/*     */     }
/*     */ 
/*     */     
/* 326 */     String traceRet1 = Charsets.convert(getBytes(field, offset, length), ccsid, this.encoding);
/*     */     
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "getString(HeaderField,int,int,int)", traceRet1);
/*     */     }
/*     */     
/* 332 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int setString(int offset, String value, int length, int ccsid) throws IOException {
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "setString(int,String,int,int)", new Object[] {
/* 342 */             Integer.valueOf(offset), value, 
/* 343 */             Integer.valueOf(length), Integer.valueOf(ccsid)
/*     */           });
/*     */     }
/*     */ 
/*     */     
/* 348 */     byte[] bytes = Charsets.convert(value, ccsid);
/*     */     
/* 350 */     if (length == 0) {
/*     */ 
/*     */       
/* 353 */       int totalLength = getPaddedLength(bytes.length, 4);
/*     */       
/* 355 */       allocate((Header)null, offset, value.length(), totalLength);
/* 356 */       setBytes(offset, bytes, bytes.length);
/*     */       
/* 358 */       if (totalLength > bytes.length) {
/* 359 */         fill(offset + bytes.length, totalLength - bytes.length, getPadByte(ccsid));
/*     */       }
/*     */       
/* 362 */       if (Trace.isOn) {
/* 363 */         Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "setString(int,String,int,int)", 
/* 364 */             Integer.valueOf(bytes.length), 1);
/*     */       }
/* 366 */       return bytes.length;
/*     */     } 
/* 368 */     if (length > bytes.length) {
/*     */ 
/*     */       
/* 371 */       setBytes(offset, bytes, bytes.length);
/* 372 */       fill(offset + bytes.length, length - bytes.length, getPadByte(ccsid));
/*     */       
/* 374 */       if (Trace.isOn) {
/* 375 */         Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "setString(int,String,int,int)", 
/* 376 */             Integer.valueOf(bytes.length), 2);
/*     */       }
/* 378 */       return bytes.length;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 383 */     setBytes(offset, bytes, length);
/*     */     
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "setString(int,String,int,int)", 
/* 387 */           Integer.valueOf(length), 3);
/*     */     }
/* 389 */     return length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getStrings(HeaderField field, int offsetP, int count, int length, int ccsid) throws IOException {
/* 399 */     if (Trace.isOn) {
/* 400 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "getStrings(HeaderField,int,int,int,int)", new Object[] { field, 
/* 401 */             Integer.valueOf(offsetP), 
/* 402 */             Integer.valueOf(count), Integer.valueOf(length), Integer.valueOf(ccsid) });
/*     */     }
/* 404 */     int offset = offsetP;
/* 405 */     String[] array = new String[count];
/*     */     
/* 407 */     for (int i = 0; i < array.length; i++) {
/* 408 */       array[i] = Charsets.convert(this.buffer, offset, length, ccsid);
/* 409 */       offset += length;
/*     */     } 
/*     */     
/* 412 */     if (Trace.isOn) {
/* 413 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "getStrings(HeaderField,int,int,int,int)", array);
/*     */     }
/*     */     
/* 416 */     return array;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int setStrings(int offsetP, String[] array, int length, int ccsid) throws IOException {
/* 424 */     if (Trace.isOn)
/* 425 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "setStrings(int,String [ ],int,int)", new Object[] {
/* 426 */             Integer.valueOf(offsetP), array, 
/* 427 */             Integer.valueOf(length), Integer.valueOf(ccsid)
/*     */           }); 
/* 429 */     int offset = offsetP;
/* 430 */     byte[][] bytes = new byte[array.length][];
/* 431 */     int stringLength = length;
/*     */     
/* 433 */     for (int i = 0; i < array.length; i++) {
/* 434 */       bytes[i] = Charsets.convert(array[i], ccsid);
/*     */       
/* 436 */       if (length == 0 && (bytes[i]).length > stringLength) {
/* 437 */         stringLength = (bytes[i]).length;
/*     */       }
/*     */     } 
/*     */     
/* 441 */     int totalLength = getPaddedLength(stringLength * array.length, 4);
/* 442 */     allocate((Header)null, offset + totalLength);
/*     */ 
/*     */ 
/*     */     
/* 446 */     for (int j = 0; j < array.length; j++) {
/* 447 */       setBytes(offset, bytes[j], Math.max(stringLength, (bytes[j]).length));
/*     */       
/* 449 */       if (stringLength > (bytes[j]).length) {
/* 450 */         fill(offset + (bytes[j]).length, stringLength - (bytes[j]).length, getPadByte(ccsid));
/*     */       }
/*     */       
/* 453 */       offset += stringLength;
/*     */     } 
/*     */     
/* 456 */     if (totalLength > stringLength * array.length) {
/* 457 */       fill(offset, totalLength - stringLength * array.length, getPadByte(ccsid));
/*     */     }
/*     */     
/* 460 */     if (Trace.isOn) {
/* 461 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "setStrings(int,String [ ],int,int)", 
/* 462 */           Integer.valueOf(stringLength));
/*     */     }
/* 464 */     return stringLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeTo(DataOutput output, int offset, int length) throws IOException {
/* 472 */     if (Trace.isOn) {
/* 473 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "writeTo(DataOutput,int,int)", new Object[] { output, 
/* 474 */             Integer.valueOf(offset), 
/* 475 */             Integer.valueOf(length) });
/*     */     }
/* 477 */     output.write(this.buffer, offset, length);
/*     */     
/* 479 */     if (Trace.isOn) {
/* 480 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "writeTo(DataOutput,int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFrom(DataInput input, int offset, int length) throws IOException {
/* 491 */     if (Trace.isOn) {
/* 492 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "readFrom(DataInput,int,int)", new Object[] { input, 
/* 493 */             Integer.valueOf(offset), 
/* 494 */             Integer.valueOf(length) });
/*     */     }
/* 496 */     if (offset + length > this.buffer.length) {
/* 497 */       System.arraycopy(this.buffer, 0, this.buffer = new byte[offset + length], 0, offset);
/*     */     }
/*     */     
/* 500 */     input.readFully(this.buffer, offset, length);
/* 501 */     this.size = offset + length;
/*     */     
/* 503 */     if (Trace.isOn) {
/* 504 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "readFrom(DataInput,int,int)");
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
/* 515 */     if (Trace.isOn) {
/* 516 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "copyTo(byte [ ],int,int)", new Object[] { target, 
/* 517 */             Integer.valueOf(offset), Integer.valueOf(length) });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 522 */     System.arraycopy(this.buffer, offset, target, 0, length);
/*     */     
/* 524 */     if (Trace.isOn) {
/* 525 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "copyTo(byte [ ],int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int encoding() {
/* 535 */     if (Trace.isOn) {
/* 536 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "encoding()");
/*     */     }
/* 538 */     if (Trace.isOn) {
/* 539 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "encoding()", 
/* 540 */           Integer.valueOf(this.encoding));
/*     */     }
/* 542 */     return this.encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int characterSet() {
/* 550 */     if (Trace.isOn) {
/* 551 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "characterSet()");
/*     */     }
/* 553 */     if (Trace.isOn) {
/* 554 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "characterSet()", 
/* 555 */           Integer.valueOf(this.characterSet));
/*     */     }
/* 557 */     return this.characterSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear(int offset, int length) throws IOException {
/* 565 */     if (Trace.isOn)
/* 566 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "clear(int,int)", new Object[] {
/* 567 */             Integer.valueOf(offset), Integer.valueOf(length)
/*     */           }); 
/* 569 */     fill(offset, length, (byte)0);
/*     */     
/* 571 */     if (Trace.isOn) {
/* 572 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "clear(int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fill(int offset, int length, byte value) throws IOException {
/* 582 */     if (Trace.isOn)
/* 583 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "fill(int,int,byte)", new Object[] {
/* 584 */             Integer.valueOf(offset), Integer.valueOf(length), Byte.valueOf(value)
/*     */           }); 
/* 586 */     int index = offset + length;
/*     */     
/* 588 */     while (index-- > offset) {
/* 589 */       this.buffer[index] = value;
/*     */     }
/*     */     
/* 592 */     if (Trace.isOn) {
/* 593 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "fill(int,int,byte)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasData() {
/* 603 */     if (Trace.isOn) {
/* 604 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "hasData()");
/*     */     }
/* 606 */     if (Trace.isOn) {
/* 607 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "hasData()", 
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
/* 619 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "allocate(final Header,final int)", new Object[] { header, 
/* 620 */             Integer.valueOf(size) });
/*     */     }
/* 622 */     if (size > this.buffer.length) {
/* 623 */       System.arraycopy(this.buffer, 0, this.buffer = new byte[size], 0, this.size);
/*     */     }
/*     */     
/* 626 */     this.size = size;
/*     */     
/* 628 */     if (Trace.isOn) {
/* 629 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "allocate(final Header,final int)", this);
/*     */     }
/*     */     
/* 632 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Store allocate(Header header, int offset, int oldSize, int newSize) throws IOException {
/* 641 */     if (Trace.isOn) {
/* 642 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "allocate(final Header,final int,final int,final int)", new Object[] { header, 
/*     */             
/* 644 */             Integer.valueOf(offset), Integer.valueOf(oldSize), Integer.valueOf(newSize) });
/*     */     }
/* 646 */     int expansion = newSize - oldSize;
/*     */     
/* 648 */     if (expansion > 0) {
/* 649 */       if (this.size + expansion > this.buffer.length)
/*     */       {
/* 651 */         System.arraycopy(this.buffer, 0, this.buffer = new byte[this.size += expansion], 0, this.size - expansion);
/*     */       }
/*     */       
/* 654 */       if (offset + newSize < this.size) {
/* 655 */         System.arraycopy(this.buffer, offset, this.buffer, offset + expansion, this.size - expansion - offset);
/*     */       }
/*     */     }
/* 658 */     else if (expansion < 0 && this.size > offset - expansion) {
/* 659 */       System.arraycopy(this.buffer, offset - expansion, this.buffer, offset, this.size - offset + expansion);
/*     */     } 
/*     */     
/* 662 */     if (Trace.isOn) {
/* 663 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "allocate(final Header,final int,final int,final int)", this);
/*     */     }
/*     */     
/* 666 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 674 */     if (Trace.isOn) {
/* 675 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.ByteStore", "toString()");
/*     */     }
/*     */     
/* 678 */     String traceRet1 = getClass().getName() + " [encoding: 0x" + HexString.hexString(encoding()) + ", ccsid: " + characterSet() + ", size: " + this.size + "] @" + System.identityHashCode(this);
/* 679 */     if (Trace.isOn) {
/* 680 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.ByteStore", "toString()", traceRet1);
/*     */     }
/*     */     
/* 683 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 691 */     return this.size;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\store\ByteStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */