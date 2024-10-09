/*     */ package com.ibm.mq.headers.internal;
/*     */ 
/*     */ import com.ibm.mq.headers.internal.store.Store;
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
/*     */ 
/*     */ final class NestedStore
/*     */   extends Store
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/NestedStore.java";
/*     */   final Header parent;
/*     */   final int origin;
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.mq.headers.internal.NestedStore", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/NestedStore.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NestedStore(Header parent, int origin) {
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "<init>(Header,int)", new Object[] { parent, 
/*  53 */             Integer.valueOf(origin) });
/*     */     }
/*  55 */     this.parent = parent;
/*  56 */     this.origin = origin;
/*     */     
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "<init>(Header,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(HeaderField field, int offset) throws IOException {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "getInt(HeaderField,int)", new Object[] { field, 
/*  68 */             Integer.valueOf(offset) });
/*     */     }
/*  70 */     int traceRet1 = this.parent.store().getInt(field, offset + this.origin);
/*     */     
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "getInt(HeaderField,int)", 
/*  74 */           Integer.valueOf(traceRet1));
/*     */     }
/*  76 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInt(int offset, int value) throws IOException {
/*  81 */     if (Trace.isOn)
/*  82 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "setInt(int,int)", new Object[] {
/*  83 */             Integer.valueOf(offset), Integer.valueOf(value)
/*     */           }); 
/*  85 */     this.parent.store().setInt(offset + this.origin, value);
/*     */     
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "setInt(int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLong(HeaderField field, int offset) throws IOException {
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "getLong(HeaderField,int)", new Object[] { field, 
/*  97 */             Integer.valueOf(offset) });
/*     */     }
/*  99 */     long traceRet1 = this.parent.store().getLong(field, offset + this.origin);
/*     */     
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "getLong(HeaderField,int)", 
/* 103 */           Long.valueOf(traceRet1));
/*     */     }
/* 105 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLong(int offset, long value) throws IOException {
/* 110 */     if (Trace.isOn)
/* 111 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "setLong(int,long)", new Object[] {
/* 112 */             Integer.valueOf(offset), Long.valueOf(value)
/*     */           }); 
/* 114 */     this.parent.store().setLong(offset + this.origin, value);
/*     */     
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "setLong(int,long)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBytes(HeaderField field, int offset, int length) throws IOException {
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "getBytes(HeaderField,int,int)", new Object[] { field, 
/* 126 */             Integer.valueOf(offset), Integer.valueOf(length) });
/*     */     }
/* 128 */     byte[] traceRet1 = this.parent.store().getBytes(field, offset + this.origin, length);
/*     */     
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "getBytes(HeaderField,int,int)", traceRet1);
/*     */     }
/*     */     
/* 134 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBytes(int offset, byte[] bytes, int length) throws IOException {
/* 139 */     if (Trace.isOn)
/* 140 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "setBytes(int,byte [ ],int)", new Object[] {
/* 141 */             Integer.valueOf(offset), bytes, Integer.valueOf(length)
/*     */           }); 
/* 143 */     this.parent.store().setBytes(offset + this.origin, bytes, length);
/*     */     
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "setBytes(int,byte [ ],int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(HeaderField field, int offset, int length, int ccsid) throws IOException {
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "getString(HeaderField,int,int,int)", new Object[] { field, 
/* 155 */             Integer.valueOf(offset), 
/* 156 */             Integer.valueOf(length), Integer.valueOf(ccsid) });
/*     */     }
/* 158 */     String traceRet1 = this.parent.store().getString(field, offset + this.origin, length, ccsid);
/*     */     
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "getString(HeaderField,int,int,int)", traceRet1);
/*     */     }
/*     */     
/* 164 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int setString(int offset, String value, int length, int ccsid) throws IOException {
/* 169 */     if (Trace.isOn)
/* 170 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "setString(int,String,int,int)", new Object[] {
/* 171 */             Integer.valueOf(offset), value, Integer.valueOf(length), 
/* 172 */             Integer.valueOf(ccsid)
/*     */           }); 
/* 174 */     int traceRet1 = this.parent.store().setString(offset + this.origin, value, length, ccsid);
/*     */     
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "setString(int,String,int,int)", 
/* 178 */           Integer.valueOf(traceRet1));
/*     */     }
/* 180 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getStrings(HeaderField field, int offset, int count, int length, int ccsid) throws IOException {
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "getStrings(HeaderField,int,int,int,int)", new Object[] { field, 
/* 188 */             Integer.valueOf(offset), 
/* 189 */             Integer.valueOf(count), Integer.valueOf(length), Integer.valueOf(ccsid) });
/*     */     }
/* 191 */     String[] traceRet1 = this.parent.store().getStrings(field, offset + this.origin, count, length, ccsid);
/*     */     
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "getStrings(HeaderField,int,int,int,int)", traceRet1);
/*     */     }
/*     */     
/* 197 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int setStrings(int offset, String[] array, int length, int ccsid) throws IOException {
/* 202 */     if (Trace.isOn)
/* 203 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "setStrings(int,String [ ],int,int)", new Object[] {
/* 204 */             Integer.valueOf(offset), array, 
/* 205 */             Integer.valueOf(length), Integer.valueOf(ccsid)
/*     */           }); 
/* 207 */     int traceRet1 = this.parent.store().setStrings(offset + this.origin, array, length, ccsid);
/*     */     
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "setStrings(int,String [ ],int,int)", 
/* 211 */           Integer.valueOf(traceRet1));
/*     */     }
/* 213 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFrom(DataInput input, int offset, int length) throws IOException {
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "readFrom(DataInput,int,int)", new Object[] { input, 
/* 220 */             Integer.valueOf(offset), Integer.valueOf(length) });
/*     */     }
/* 222 */     this.parent.store().readFrom(input, offset + this.origin, length);
/*     */     
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "readFrom(DataInput,int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeTo(DataOutput output, int offset, int length) throws IOException {
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "writeTo(DataOutput,int,int)", new Object[] { output, 
/* 234 */             Integer.valueOf(offset), Integer.valueOf(length) });
/*     */     }
/* 236 */     this.parent.store().writeTo(output, offset + this.origin, length);
/*     */     
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "writeTo(DataOutput,int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyTo(byte[] target, int offset, int length) throws IOException {
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "copyTo(byte [ ],int,int)", new Object[] { target, 
/* 248 */             Integer.valueOf(offset), Integer.valueOf(length) });
/*     */     }
/* 250 */     this.parent.store().copyTo(target, offset + this.origin, length);
/*     */     
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "copyTo(byte [ ],int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int encoding() {
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "encoding()");
/*     */     }
/* 263 */     int traceRet1 = this.parent.store().encoding();
/*     */     
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "encoding()", 
/* 267 */           Integer.valueOf(traceRet1));
/*     */     }
/* 269 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int characterSet() {
/* 274 */     if (Trace.isOn) {
/* 275 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "characterSet()");
/*     */     }
/* 277 */     int traceRet1 = this.parent.store().characterSet();
/*     */     
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "characterSet()", 
/* 281 */           Integer.valueOf(traceRet1));
/*     */     }
/* 283 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear(int offset, int length) throws IOException {
/* 288 */     if (Trace.isOn)
/* 289 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "clear(int,int)", new Object[] {
/* 290 */             Integer.valueOf(offset), Integer.valueOf(length)
/*     */           }); 
/* 292 */     this.parent.store().clear(offset + this.origin, length);
/*     */     
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "clear(int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fill(int offset, int length, byte value) throws IOException {
/* 302 */     if (Trace.isOn)
/* 303 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "fill(int,int,byte)", new Object[] {
/* 304 */             Integer.valueOf(offset), Integer.valueOf(length), Byte.valueOf(value)
/*     */           }); 
/* 306 */     this.parent.store().fill(offset + this.origin, length, value);
/*     */     
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "fill(int,int,byte)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasData() {
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "hasData()");
/*     */     }
/* 319 */     boolean traceRet1 = this.parent.store().hasData();
/*     */     
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "hasData()", 
/* 323 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 325 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public Store allocate(Header header, int size) throws IOException {
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "allocate(Header,int)", new Object[] { header, 
/* 332 */             Integer.valueOf(size) });
/*     */     }
/* 334 */     this.parent.store(this.origin + size);
/*     */     
/* 336 */     if (Trace.isOn) {
/* 337 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "allocate(Header,int)", this);
/*     */     }
/* 339 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Store allocate(Header header, int offset, int oldSize, int newSize) throws IOException {
/* 344 */     if (Trace.isOn) {
/* 345 */       Trace.entry(this, "com.ibm.mq.headers.internal.NestedStore", "allocate(Header,int,int,int)", new Object[] { header, 
/* 346 */             Integer.valueOf(offset), Integer.valueOf(oldSize), 
/* 347 */             Integer.valueOf(newSize) });
/*     */     }
/* 349 */     this.parent.store(offset + this.origin, oldSize, newSize);
/*     */     
/* 351 */     if (Trace.isOn) {
/* 352 */       Trace.exit(this, "com.ibm.mq.headers.internal.NestedStore", "allocate(Header,int,int,int)", this);
/*     */     }
/*     */     
/* 355 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\NestedStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */