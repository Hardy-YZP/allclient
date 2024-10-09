/*     */ package com.ibm.disthub2.impl.formats;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.util.ArrayUtil;
/*     */ import com.ibm.disthub2.impl.util.UTF;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.disthub2.spi.ExceptionConstants;
/*     */ import com.ibm.disthub2.spi.LogConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ByteSequence
/*     */   implements ExceptionConstants, LogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  53 */   private static final DebugObject debug = new DebugObject("ByteSequence");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int CHUNKSIZE = 256;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int length;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int framelen;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] frame;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int offset;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteSequence next;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean spare;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteSequence() {
/*  95 */     this(new byte[256], 0, 0);
/*  96 */     this.spare = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteSequence(byte[] frame) {
/* 107 */     this(frame, 0, frame.length);
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
/*     */   public ByteSequence(byte[] frame, int offset, int length) {
/* 122 */     this.frame = frame;
/* 123 */     this.offset = offset;
/* 124 */     this.length = this.framelen = length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(byte[] frame, int offset, int length) {
/* 131 */     this.frame = frame;
/* 132 */     this.offset = offset;
/* 133 */     this.length = this.framelen = length;
/* 134 */     this.spare = false; this.next = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 141 */     this.offset = this.length = 0;
/* 142 */     this.next = null;
/* 143 */     this.spare = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(ByteSequence app) {
/* 152 */     if (this.next == null) {
/* 153 */       this.next = app;
/*     */     } else {
/* 155 */       this.next.append(app);
/* 156 */     }  getLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void insert(ByteSequence ins) {
/* 165 */     if (this.next != null)
/* 166 */       ins.append(this.next); 
/* 167 */     this.next = ins;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void split(int at) {
/* 176 */     ByteSequence after = new ByteSequence(this.frame, this.offset + at, this.framelen - at);
/* 177 */     if (this.next != null)
/* 178 */       after.append(this.next); 
/* 179 */     this.framelen = at;
/* 180 */     this.next = after;
/* 181 */     getLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getLength() {
/* 190 */     if (this.next == null) {
/* 191 */       this.length = this.framelen;
/*     */     } else {
/* 193 */       this.length = this.framelen + this.next.getLength();
/*     */     } 
/* 195 */     return this.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insert(ByteSequence ins, int at) {
/* 203 */     if (at > this.framelen) {
/* 204 */       if (this.next == null) {
/* 205 */         throw new ArrayIndexOutOfBoundsException(ExceptionBuilder.buildReasonString(1634116819, null));
/*     */       }
/* 207 */       this.next.insert(ins, at - this.framelen);
/* 208 */     } else if (at == this.framelen) {
/* 209 */       insert(ins);
/*     */     } else {
/* 211 */       split(at);
/* 212 */       insert(ins);
/*     */     } 
/* 214 */     getLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteSequence remove(int startAt, int forBytes) {
/* 224 */     ByteSequence result = this;
/* 225 */     if (forBytes != 0) {
/* 226 */       if (startAt == 0) {
/* 227 */         if (forBytes > this.framelen) {
/*     */           
/* 229 */           if (this.next == null) {
/* 230 */             throw new ArrayIndexOutOfBoundsException(ExceptionBuilder.buildReasonString(1634116819, null));
/*     */           }
/* 232 */           result = this.next.remove(0, forBytes - this.framelen);
/* 233 */         } else if (forBytes == this.framelen) {
/*     */           
/* 235 */           result = this.next;
/*     */         } else {
/*     */           
/* 238 */           this.framelen -= forBytes;
/* 239 */           this.offset += forBytes;
/* 240 */           getLength();
/*     */         } 
/* 242 */       } else if (startAt >= this.framelen) {
/*     */         
/* 244 */         if (this.next == null) {
/* 245 */           throw new ArrayIndexOutOfBoundsException(ExceptionBuilder.buildReasonString(1634116819, null));
/*     */         }
/* 247 */         this.next = this.next.remove(startAt - this.framelen, forBytes);
/* 248 */         getLength();
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 254 */         int endRange = startAt + forBytes;
/* 255 */         if (endRange > this.framelen) {
/* 256 */           this.next = this.next.remove(0, endRange - this.framelen);
/* 257 */           endRange = this.framelen;
/*     */         
/*     */         }
/* 260 */         else if (endRange < this.framelen) {
/* 261 */           split(endRange);
/*     */         } 
/*     */         
/* 264 */         this.framelen = startAt;
/* 265 */         getLength();
/*     */       } 
/*     */     }
/*     */     
/* 269 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] linearize() {
/* 278 */     if (this.next != null || this.offset != 0 || this.framelen != this.frame.length) {
/* 279 */       linearize(new byte[this.length]);
/*     */     }
/* 281 */     return this.frame;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void linearize(byte[] newFrame) {
/* 289 */     int running = 0;
/* 290 */     ByteSequence finger = this;
/*     */     while (true) {
/* 292 */       System.arraycopy(finger.frame, finger.offset, newFrame, running, finger.framelen);
/* 293 */       running += finger.framelen;
/* 294 */       finger = finger.next;
/* 295 */       if (finger == null) {
/* 296 */         this.frame = newFrame;
/* 297 */         this.offset = 0;
/* 298 */         this.framelen = this.length = running;
/* 299 */         this.next = null;
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ensure(int len) {
/* 310 */     if (this.next == null && this.spare && this.offset + len <= this.frame.length) {
/*     */       
/* 312 */       this.framelen = this.length = len;
/*     */     
/*     */     }
/* 315 */     else if (len < this.length) {
/*     */ 
/*     */       
/* 318 */       linearize(new byte[this.length]);
/*     */     }
/*     */     else {
/*     */       
/* 322 */       linearize(new byte[len + 256]);
/* 323 */       this.spare = true;
/* 324 */       this.framelen = this.length = len;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte readByte(int at) {
/*     */     byte result;
/* 336 */     if (at < this.framelen)
/* 337 */     { result = this.frame[this.offset + at]; }
/* 338 */     else { if (this.next == null) {
/* 339 */         throw new ArrayIndexOutOfBoundsException(ExceptionBuilder.buildReasonString(1634116819, null));
/*     */       }
/* 341 */       result = this.next.readByte(at - this.framelen); }
/*     */     
/* 343 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short readShort(int at) {
/*     */     short result;
/* 353 */     if (at <= this.framelen - 2)
/* 354 */     { result = ArrayUtil.readShort(this.frame, this.offset + at); }
/* 355 */     else { if (this.next == null)
/* 356 */         throw new ArrayIndexOutOfBoundsException(ExceptionBuilder.buildReasonString(1634116819, null)); 
/* 357 */       if (at < this.framelen) {
/* 358 */         result = ArrayUtil.readShort(linearize(), at);
/*     */       } else {
/* 360 */         result = this.next.readShort(at - this.framelen);
/*     */       }  }
/* 362 */      return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readInt(int at) {
/*     */     int result;
/* 371 */     if (at <= this.framelen - 4)
/* 372 */     { result = ArrayUtil.readInt(this.frame, this.offset + at); }
/* 373 */     else { if (this.next == null)
/* 374 */         throw new ArrayIndexOutOfBoundsException(ExceptionBuilder.buildReasonString(1634116819, null)); 
/* 375 */       if (at < this.framelen) {
/* 376 */         result = ArrayUtil.readInt(linearize(), at);
/*     */       } else {
/* 378 */         result = this.next.readInt(at - this.framelen);
/*     */       }  }
/* 380 */      return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long readLong(int at) {
/*     */     long result;
/* 389 */     if (at <= this.framelen - 8)
/* 390 */     { result = ArrayUtil.readLong(this.frame, this.offset + at); }
/* 391 */     else { if (this.next == null)
/* 392 */         throw new ArrayIndexOutOfBoundsException(ExceptionBuilder.buildReasonString(1634116819, null)); 
/* 393 */       if (at < this.framelen) {
/* 394 */         result = ArrayUtil.readLong(linearize(), at);
/*     */       } else {
/* 396 */         result = this.next.readLong(at - this.framelen);
/*     */       }  }
/* 398 */      return result;
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
/*     */   public void read(int at, byte[] into, int offs, int len) {
/* 415 */     if (at <= this.framelen - len)
/* 416 */     { System.arraycopy(this.frame, this.offset + at, into, offs, len); }
/* 417 */     else { if (this.next == null)
/* 418 */         throw new ArrayIndexOutOfBoundsException(ExceptionBuilder.buildReasonString(1634116819, null)); 
/* 419 */       if (at < this.framelen) {
/* 420 */         System.arraycopy(linearize(), at, into, offs, len);
/*     */       } else {
/* 422 */         this.next.read(at - this.framelen, into, offs, len);
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   public byte[] getCopyAsBytes() {
/* 428 */     byte[] b = new byte[this.length];
/* 429 */     int running = 0;
/* 430 */     ByteSequence finger = this;
/*     */     while (true) {
/* 432 */       System.arraycopy(finger.frame, finger.offset, b, running, finger.framelen);
/* 433 */       running += finger.framelen;
/* 434 */       finger = finger.next;
/* 435 */       if (finger == null) {
/* 436 */         return b;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public long readL6(int at) {
/* 442 */     if (at <= this.framelen - 6)
/* 443 */       return ArrayUtil.readL6(this.frame, this.offset + at); 
/* 444 */     if (this.next == null)
/* 445 */       throw new ArrayIndexOutOfBoundsException(); 
/* 446 */     if (at < this.framelen) {
/* 447 */       return ArrayUtil.readL6(linearize(), at);
/*     */     }
/* 449 */     return this.next.readL6(at - this.framelen);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeByte(int at, byte val) {
/* 456 */     if (at < this.framelen) {
/* 457 */       this.frame[this.offset + at] = val;
/* 458 */     } else if (this.next == null) {
/* 459 */       ensure(at + 1);
/* 460 */       this.frame[this.offset + at] = val;
/*     */     } else {
/*     */       
/* 463 */       this.next.writeByte(at - this.framelen, val);
/* 464 */     }  getLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeShort(int at, short value) {
/* 473 */     if (at <= this.framelen - 2) {
/* 474 */       ArrayUtil.writeShort(this.frame, this.offset + at, value);
/* 475 */     } else if (at < this.framelen || this.next == null) {
/* 476 */       ensure(at + 2);
/* 477 */       ArrayUtil.writeShort(this.frame, this.offset + at, value);
/*     */     } else {
/*     */       
/* 480 */       this.next.writeShort(at - this.framelen, value);
/* 481 */     }  getLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeInt(int at, int value) {
/* 490 */     if (at <= this.framelen - 4) {
/* 491 */       ArrayUtil.writeInt(this.frame, this.offset + at, value);
/* 492 */     } else if (at < this.framelen || this.next == null) {
/* 493 */       ensure(at + 4);
/* 494 */       ArrayUtil.writeInt(this.frame, this.offset + at, value);
/*     */     } else {
/*     */       
/* 497 */       this.next.writeInt(at - this.framelen, value);
/* 498 */     }  getLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeLong(int at, long value) {
/* 507 */     if (at <= this.framelen - 8) {
/* 508 */       ArrayUtil.writeLong(this.frame, this.offset + at, value);
/* 509 */     } else if (at < this.framelen || this.next == null) {
/* 510 */       ensure(at + 8);
/* 511 */       ArrayUtil.writeLong(this.frame, this.offset + at, value);
/*     */     } else {
/*     */       
/* 514 */       this.next.writeLong(at - this.framelen, value);
/* 515 */     }  getLength();
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
/*     */   public void write(int at, byte[] from, int offs, int len) {
/* 532 */     if (at <= this.framelen - len) {
/* 533 */       System.arraycopy(from, offs, this.frame, this.offset + at, len);
/* 534 */     } else if (at < this.framelen || this.next == null) {
/* 535 */       ensure(at + len);
/* 536 */       System.arraycopy(from, offs, this.frame, this.offset + at, len);
/*     */     } else {
/*     */       
/* 539 */       this.next.write(at - this.framelen, from, offs, len);
/* 540 */     }  getLength();
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
/*     */   public void writeUnicode(int at, int utflen, char[] from, int offs, int len) {
/* 560 */     if (at <= this.framelen - utflen) {
/* 561 */       UTF.charsToUTF(from, offs, this.frame, this.offset + at, len);
/* 562 */     } else if (at < this.framelen || this.next == null) {
/* 563 */       ensure(at + utflen);
/* 564 */       UTF.charsToUTF(from, offs, this.frame, this.offset + at, len);
/*     */     } else {
/*     */       
/* 567 */       this.next.writeUnicode(at - this.framelen, utflen, from, offs, len);
/* 568 */     }  getLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeL6(int at, long value) {
/* 575 */     if (at <= this.framelen - 6) {
/* 576 */       ArrayUtil.writeL6(this.frame, this.offset + at, value);
/*     */       return;
/*     */     } 
/* 579 */     if (at < this.framelen || this.next == null) {
/* 580 */       ensure(at + 6);
/* 581 */       ArrayUtil.writeL6(this.frame, this.offset + at, value);
/*     */     } else {
/*     */       
/* 584 */       this.next.writeL6(at - this.framelen, value);
/* 585 */     }  getLength();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\ByteSequence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */