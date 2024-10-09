/*     */ package com.ibm.mq.headers.internal.store;
/*     */ 
/*     */ import com.ibm.mq.headers.Charsets;
/*     */ import com.ibm.mq.headers.internal.Header;
/*     */ import com.ibm.mq.headers.internal.HeaderField;
/*     */ import com.ibm.mq.headers.internal.HexString;
/*     */ import com.ibm.mq.internal.MQCommonServices;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Store
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/store/Store.java";
/*     */   protected byte padByte;
/*     */   private int hconnCharacterSetID;
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.headers.internal.store.Store", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/store/Store.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Store() {
/*  58 */     super(MQCommonServices.jmqiEnv);
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.Store", "<init>()");
/*     */     }
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.Store", "<init>()");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean matchesEncoding(int encoding) {
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.Store", "matchesEncoding(int)", new Object[] {
/* 239 */             Integer.valueOf(encoding)
/*     */           });
/*     */     }
/* 242 */     boolean traceRet1 = (encoding == 0 || ((encoding() ^ encoding) & 0x2) == 0);
/*     */     
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.Store", "matchesEncoding(int)", 
/* 246 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 248 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final boolean isReversed(int encoding) {
/* 256 */     if (Trace.isOn)
/* 257 */       Trace.entry("com.ibm.mq.headers.internal.store.Store", "isReversed(int)", new Object[] {
/* 258 */             Integer.valueOf(encoding)
/*     */           }); 
/* 260 */     boolean traceRet1 = ((encoding & 0x2) == 2);
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.exit("com.ibm.mq.headers.internal.store.Store", "isReversed(int)", 
/* 263 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 265 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int reverse(int value) {
/* 273 */     if (Trace.isOn)
/* 274 */       Trace.entry("com.ibm.mq.headers.internal.store.Store", "reverse(int)", new Object[] {
/* 275 */             Integer.valueOf(value)
/*     */           }); 
/* 277 */     int traceRet1 = value >> 24 & 0xFF | value >> 8 & 0xFF00 | value << 8 & 0xFF0000 | value << 24 & 0xFF000000;
/*     */ 
/*     */     
/* 280 */     if (Trace.isOn) {
/* 281 */       Trace.exit("com.ibm.mq.headers.internal.store.Store", "reverse(int)", 
/* 282 */           Integer.valueOf(traceRet1));
/*     */     }
/* 284 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final long reverse(long value) {
/* 292 */     if (Trace.isOn)
/* 293 */       Trace.entry("com.ibm.mq.headers.internal.store.Store", "reverse(long)", new Object[] {
/* 294 */             Long.valueOf(value)
/*     */           }); 
/* 296 */     long traceRet1 = value >> 56L & 0xFFL | value >> 40L & 0xFF00L | value >> 24L & 0xFF0000L | value >> 8L & 0xFF000000L | value >> 8L & 0xFF00000000L | value >> 24L & 0xFF0000000000L | value >> 40L & 0xFF000000000000L | value >> 56L & 0xFF00000000000000L;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 301 */     if (Trace.isOn) {
/* 302 */       Trace.exit("com.ibm.mq.headers.internal.store.Store", "reverse(long)", 
/* 303 */           Long.valueOf(traceRet1));
/*     */     }
/* 305 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int correctEncoding(int value) {
/* 313 */     if (Trace.isOn)
/* 314 */       Trace.entry("com.ibm.mq.headers.internal.store.Store", "correctEncoding(int)", new Object[] {
/* 315 */             Integer.valueOf(value)
/*     */           }); 
/* 317 */     if ((value & 0xFFFF) == 0 && value >>> 16 > 0) {
/* 318 */       int traceRet1 = reverse(value);
/* 319 */       if (Trace.isOn) {
/* 320 */         Trace.exit("com.ibm.mq.headers.internal.store.Store", "correctEncoding(int)", 
/* 321 */             Integer.valueOf(traceRet1), 1);
/*     */       }
/* 323 */       return traceRet1;
/*     */     } 
/* 325 */     if (Trace.isOn) {
/* 326 */       Trace.exit("com.ibm.mq.headers.internal.store.Store", "correctEncoding(int)", 
/* 327 */           Integer.valueOf(value), 2);
/*     */     }
/* 329 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final byte getPadByte(int ccsid) throws UnsupportedEncodingException {
/* 338 */     if (Trace.isOn)
/* 339 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.Store", "getPadByte(int)", new Object[] {
/* 340 */             Integer.valueOf(ccsid)
/*     */           }); 
/* 342 */     if (ccsid == 0 || ccsid == characterSet()) {
/*     */ 
/*     */       
/* 345 */       if (this.padByte == 0) {
/* 346 */         this.padByte = Charsets.convert(" ", ccsid)[0];
/*     */       }
/*     */       
/* 349 */       if (Trace.isOn) {
/* 350 */         Trace.exit(this, "com.ibm.mq.headers.internal.store.Store", "getPadByte(int)", 
/* 351 */             Byte.valueOf(this.padByte), 1);
/*     */       }
/* 353 */       return this.padByte;
/*     */     } 
/*     */     
/* 356 */     byte traceRet1 = Charsets.convert(" ", ccsid)[0];
/* 357 */     if (Trace.isOn) {
/* 358 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.Store", "getPadByte(int)", 
/* 359 */           Byte.valueOf(traceRet1), 2);
/*     */     }
/* 361 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int getPaddedLength(int length, int alignment) {
/* 370 */     if (Trace.isOn)
/* 371 */       Trace.entry("com.ibm.mq.headers.internal.store.Store", "getPaddedLength(int,int)", new Object[] {
/* 372 */             Integer.valueOf(length), Integer.valueOf(alignment)
/*     */           }); 
/* 374 */     int pad = alignment - length % alignment;
/*     */     
/* 376 */     int traceRet1 = length + ((pad == alignment) ? 0 : pad);
/* 377 */     if (Trace.isOn) {
/* 378 */       Trace.exit("com.ibm.mq.headers.internal.store.Store", "getPaddedLength(int,int)", 
/* 379 */           Integer.valueOf(traceRet1));
/*     */     }
/* 381 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setHconnCharacterSetID(int ccsid) {
/* 392 */     if (Trace.isOn) {
/* 393 */       Trace.data(this, "com.ibm.mq.headers.internal.store.Store", "setHconnCharacterSetID(int)", "setter", 
/* 394 */           Integer.valueOf(ccsid));
/*     */     }
/*     */     
/* 397 */     if (Trace.isOn) {
/* 398 */       Trace.data("com.ibm.mq.headers.internal.store.Store", "setHconnCharacterSetID(int)", "Setting to: " + ccsid);
/*     */     }
/*     */ 
/*     */     
/* 402 */     this.hconnCharacterSetID = ccsid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getHconnCharacterSetID() {
/* 410 */     if (Trace.isOn) {
/* 411 */       Trace.data("com.ibm.mq.headers.internal.store.Store", "getHconnCharacterSetID()", "Returns: " + this.hconnCharacterSetID);
/*     */     }
/*     */ 
/*     */     
/* 415 */     if (Trace.isOn) {
/* 416 */       Trace.data(this, "com.ibm.mq.headers.internal.store.Store", "getHconnCharacterSetID()", "getter", 
/* 417 */           Integer.valueOf(this.hconnCharacterSetID));
/*     */     }
/* 419 */     return this.hconnCharacterSetID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 428 */     if (Trace.isOn) {
/* 429 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.Store", "toString()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 434 */     String traceRet1 = " [encoding: 0x" + HexString.hexString(encoding()) + ", ccsid: " + characterSet() + ", hconnCharacterSetID: " + this.hconnCharacterSetID + "]";
/*     */ 
/*     */ 
/*     */     
/* 438 */     if (Trace.isOn) {
/* 439 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.Store", "toString()", traceRet1);
/*     */     }
/*     */     
/* 442 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public abstract int getInt(HeaderField paramHeaderField, int paramInt) throws IOException;
/*     */   
/*     */   public abstract void setInt(int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public abstract long getLong(HeaderField paramHeaderField, int paramInt) throws IOException;
/*     */   
/*     */   public abstract void setLong(int paramInt, long paramLong) throws IOException;
/*     */   
/*     */   public abstract byte[] getBytes(HeaderField paramHeaderField, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public abstract void setBytes(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) throws IOException;
/*     */   
/*     */   public abstract String getString(HeaderField paramHeaderField, int paramInt1, int paramInt2, int paramInt3) throws IOException;
/*     */   
/*     */   public abstract int setString(int paramInt1, String paramString, int paramInt2, int paramInt3) throws IOException;
/*     */   
/*     */   public abstract String[] getStrings(HeaderField paramHeaderField, int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws IOException;
/*     */   
/*     */   public abstract int setStrings(int paramInt1, String[] paramArrayOfString, int paramInt2, int paramInt3) throws IOException;
/*     */   
/*     */   public abstract void writeTo(DataOutput paramDataOutput, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public abstract void readFrom(DataInput paramDataInput, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public abstract void copyTo(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public abstract int encoding();
/*     */   
/*     */   public abstract int characterSet();
/*     */   
/*     */   public abstract void clear(int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public abstract void fill(int paramInt1, int paramInt2, byte paramByte) throws IOException;
/*     */   
/*     */   public abstract boolean hasData();
/*     */   
/*     */   public abstract Store allocate(Header paramHeader, int paramInt) throws IOException;
/*     */   
/*     */   public abstract Store allocate(Header paramHeader, int paramInt1, int paramInt2, int paramInt3) throws IOException;
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\store\Store.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */