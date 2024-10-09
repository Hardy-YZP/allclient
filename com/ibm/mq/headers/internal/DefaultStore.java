/*     */ package com.ibm.mq.headers.internal;
/*     */ 
/*     */ import com.ibm.mq.headers.internal.store.ByteStore;
/*     */ import com.ibm.mq.headers.internal.store.Store;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.DataOutputStream;
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
/*     */ public class DefaultStore
/*     */   extends Store
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/DefaultStore.java";
/*     */   
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.mq.headers.internal.DefaultStore", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/DefaultStore.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   static final DefaultStore INSTANCE = new DefaultStore();
/*     */   
/*     */   DefaultStore() {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "<init>()");
/*     */     }
/*     */ 
/*     */     
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.exit(this, "com.ibm.mq.headers.internal.DefaultStore", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(HeaderField field, int offset) throws IOException {
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "getInt(HeaderField,int)", new Object[] { field, 
/*  74 */             Integer.valueOf(offset) });
/*     */     }
/*  76 */     int traceRet1 = ((Integer)field.defaultValue()).intValue();
/*     */     
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.exit(this, "com.ibm.mq.headers.internal.DefaultStore", "getInt(HeaderField,int)", 
/*  80 */           Integer.valueOf(traceRet1));
/*     */     }
/*  82 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInt(int offset, int value) throws IOException {
/*  90 */     if (Trace.isOn)
/*  91 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "setInt(int,int)", new Object[] {
/*  92 */             Integer.valueOf(offset), Integer.valueOf(value)
/*     */           }); 
/*  94 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException();
/*     */     
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DefaultStore", "setInt(int,int)", traceRet1);
/*     */     }
/*     */     
/* 100 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLong(HeaderField field, int offset) throws IOException {
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "getLong(HeaderField,int)", new Object[] { field, 
/* 110 */             Integer.valueOf(offset) });
/*     */     }
/* 112 */     long traceRet1 = ((Long)field.defaultValue()).longValue();
/*     */     
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.exit(this, "com.ibm.mq.headers.internal.DefaultStore", "getLong(HeaderField,int)", 
/* 116 */           Long.valueOf(traceRet1));
/*     */     }
/* 118 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLong(int offset, long value) throws IOException {
/* 126 */     if (Trace.isOn)
/* 127 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "setLong(int,long)", new Object[] {
/* 128 */             Integer.valueOf(offset), Long.valueOf(value)
/*     */           }); 
/* 130 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException();
/*     */     
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DefaultStore", "setLong(int,long)", traceRet1);
/*     */     }
/*     */     
/* 136 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBytes(HeaderField field, int offset, int length) throws IOException {
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "getBytes(HeaderField,int,int)", new Object[] { field, 
/* 146 */             Integer.valueOf(offset), 
/* 147 */             Integer.valueOf(length) });
/*     */     }
/* 149 */     byte[] traceRet1 = (byte[])field.defaultValue();
/*     */     
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.exit(this, "com.ibm.mq.headers.internal.DefaultStore", "getBytes(HeaderField,int,int)", traceRet1);
/*     */     }
/*     */     
/* 155 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBytes(int offset, byte[] bytes, int length) throws IOException {
/* 163 */     if (Trace.isOn)
/* 164 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "setBytes(int,byte [ ],int)", new Object[] {
/* 165 */             Integer.valueOf(offset), bytes, Integer.valueOf(length)
/*     */           }); 
/* 167 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException();
/*     */     
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DefaultStore", "setBytes(int,byte [ ],int)", traceRet1);
/*     */     }
/*     */     
/* 173 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(HeaderField field, int offset, int length, int ccsid) throws IOException {
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "getString(HeaderField,int,int,int)", new Object[] { field, 
/* 184 */             Integer.valueOf(offset), 
/* 185 */             Integer.valueOf(length), Integer.valueOf(ccsid) });
/*     */     }
/* 187 */     String traceRet1 = (String)field.defaultValue();
/*     */     
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.exit(this, "com.ibm.mq.headers.internal.DefaultStore", "getString(HeaderField,int,int,int)", traceRet1);
/*     */     }
/*     */     
/* 193 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int setString(int offset, String value, int length, int ccsid) throws IOException {
/* 202 */     if (Trace.isOn)
/* 203 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "setString(int,String,int,int)", new Object[] {
/* 204 */             Integer.valueOf(offset), value, 
/* 205 */             Integer.valueOf(length), Integer.valueOf(ccsid)
/*     */           }); 
/* 207 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException();
/*     */     
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DefaultStore", "setString(int,String,int,int)", traceRet1);
/*     */     }
/*     */     
/* 213 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getStrings(HeaderField field, int offset, int length, int count, int ccsid) throws IOException {
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "getStrings(HeaderField,int,int,int,int)", new Object[] { field, 
/* 224 */             Integer.valueOf(offset), 
/* 225 */             Integer.valueOf(length), Integer.valueOf(count), Integer.valueOf(ccsid) });
/*     */     }
/* 227 */     String[] traceRet1 = new String[0];
/*     */     
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.exit(this, "com.ibm.mq.headers.internal.DefaultStore", "getStrings(HeaderField,int,int,int,int)", traceRet1);
/*     */     }
/*     */     
/* 233 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int setStrings(int offset, String[] array, int length, int ccsid) throws IOException {
/* 242 */     if (Trace.isOn)
/* 243 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "setStrings(int,String [ ],int,int)", new Object[] {
/* 244 */             Integer.valueOf(offset), array, 
/* 245 */             Integer.valueOf(length), Integer.valueOf(ccsid)
/*     */           }); 
/* 247 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException();
/*     */     
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DefaultStore", "setStrings(int,String [ ],int,int)", traceRet1);
/*     */     }
/*     */     
/* 253 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeTo(DataOutput output, int offset, int length) throws IOException {
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "writeTo(DataOutput,int,int)", new Object[] { output, 
/* 263 */             Integer.valueOf(offset), Integer.valueOf(length) });
/*     */     }
/* 265 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException();
/*     */     
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DefaultStore", "writeTo(DataOutput,int,int)", traceRet1);
/*     */     }
/*     */     
/* 271 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFrom(DataInput input, int offset, int length) throws IOException {
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "readFrom(DataInput,int,int)", new Object[] { input, 
/* 281 */             Integer.valueOf(offset), Integer.valueOf(length) });
/*     */     }
/* 283 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException();
/*     */     
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DefaultStore", "readFrom(DataInput,int,int)", traceRet1);
/*     */     }
/*     */     
/* 289 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyTo(byte[] target, int offset, int length) throws IOException {
/* 297 */     if (Trace.isOn) {
/* 298 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "copyTo(byte [ ],int,int)", new Object[] { target, 
/* 299 */             Integer.valueOf(offset), Integer.valueOf(length) });
/*     */     }
/* 301 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException();
/*     */     
/* 303 */     if (Trace.isOn) {
/* 304 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DefaultStore", "copyTo(byte [ ],int,int)", traceRet1);
/*     */     }
/*     */     
/* 307 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int encoding() {
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "encoding()");
/*     */     }
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.exit(this, "com.ibm.mq.headers.internal.DefaultStore", "encoding()", 
/* 320 */           Integer.valueOf(273));
/*     */     }
/* 322 */     return 273;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int characterSet() {
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "characterSet()");
/*     */     }
/* 333 */     if (Trace.isOn) {
/* 334 */       Trace.exit(this, "com.ibm.mq.headers.internal.DefaultStore", "characterSet()", 
/* 335 */           Integer.valueOf(1208));
/*     */     }
/* 337 */     return 1208;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear(int offset, int length) throws IOException {
/* 345 */     if (Trace.isOn)
/* 346 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "clear(int,int)", new Object[] {
/* 347 */             Integer.valueOf(offset), Integer.valueOf(length)
/*     */           }); 
/* 349 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException();
/*     */     
/* 351 */     if (Trace.isOn) {
/* 352 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DefaultStore", "clear(int,int)", traceRet1);
/*     */     }
/*     */     
/* 355 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fill(int offset, int length, byte value) throws IOException {
/* 363 */     if (Trace.isOn)
/* 364 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "fill(int,int,byte)", new Object[] {
/* 365 */             Integer.valueOf(offset), Integer.valueOf(length), Byte.valueOf(value)
/*     */           }); 
/* 367 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException();
/*     */     
/* 369 */     if (Trace.isOn) {
/* 370 */       Trace.throwing(this, "com.ibm.mq.headers.internal.DefaultStore", "fill(int,int,byte)", traceRet1);
/*     */     }
/*     */     
/* 373 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasData() {
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "hasData()");
/*     */     }
/* 384 */     if (Trace.isOn) {
/* 385 */       Trace.exit(this, "com.ibm.mq.headers.internal.DefaultStore", "hasData()", 
/* 386 */           Boolean.valueOf(false));
/*     */     }
/* 388 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Store allocate(Header header, int size) throws IOException {
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "allocate(Header,int)", new Object[] { header, 
/* 398 */             Integer.valueOf(size) });
/*     */     }
/* 400 */     ByteArrayOutputStream out = new ByteArrayOutputStream(header.size());
/*     */     
/* 402 */     int written = header.write(new DataOutputStream(out), 273, 1208);
/*     */     
/* 404 */     while (written < size) {
/* 405 */       out.write(0);
/* 406 */       written++;
/*     */     } 
/*     */     
/* 409 */     Store traceRet1 = (new ByteStore(out.toByteArray(), 273, 1208)).allocate(header, size);
/*     */     
/* 411 */     if (Trace.isOn) {
/* 412 */       Trace.exit(this, "com.ibm.mq.headers.internal.DefaultStore", "allocate(Header,int)", traceRet1);
/*     */     }
/*     */     
/* 415 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Store allocate(Header header, int offset, int oldSize, int newSize) throws IOException {
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.entry(this, "com.ibm.mq.headers.internal.DefaultStore", "allocate(Header,int,int,int)", new Object[] { header, 
/* 425 */             Integer.valueOf(offset), Integer.valueOf(oldSize), 
/* 426 */             Integer.valueOf(newSize) });
/*     */     }
/* 428 */     ByteArrayOutputStream out = new ByteArrayOutputStream(header.size());
/*     */     
/* 430 */     header.write(new DataOutputStream(out), 273, 1208);
/*     */     
/* 432 */     int expansion = newSize - oldSize;
/*     */     
/* 434 */     while (expansion-- > 0) {
/* 435 */       out.write(0);
/*     */     }
/*     */     
/* 438 */     Store traceRet1 = (new ByteStore(out.toByteArray(), 273, 1208)).allocate(header, offset, oldSize, newSize);
/*     */     
/* 440 */     if (Trace.isOn) {
/* 441 */       Trace.exit(this, "com.ibm.mq.headers.internal.DefaultStore", "allocate(Header,int,int,int)", traceRet1);
/*     */     }
/*     */     
/* 444 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\DefaultStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */