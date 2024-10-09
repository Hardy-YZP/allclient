/*     */ package com.ibm.mq.headers.internal.store;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ public class StoreDataOutput
/*     */   implements DataOutput
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/store/StoreDataOutput.java";
/*     */   private final Store store;
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.mq.headers.internal.store.StoreDataOutput", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/store/StoreDataOutput.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   private final byte[] buffer1 = new byte[1];
/*     */ 
/*     */ 
/*     */   
/*     */   private int offset;
/*     */ 
/*     */ 
/*     */   
/*     */   public StoreDataOutput(Store store, int offset) {
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.StoreDataOutput", "<init>(Store,int)", new Object[] { store, 
/*  59 */             Integer.valueOf(offset) });
/*     */     }
/*  61 */     this.store = store;
/*  62 */     this.offset = offset;
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.StoreDataOutput", "<init>(Store,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(int arg0) throws IOException {
/*  74 */     if (Trace.isOn)
/*  75 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.StoreDataOutput", "write(int)", new Object[] {
/*  76 */             Integer.valueOf(arg0)
/*     */           }); 
/*  78 */     UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException();
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.throwing(this, "com.ibm.mq.headers.internal.store.StoreDataOutput", "write(int)", traceRet1);
/*     */     }
/*     */     
/*  83 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] value) throws IOException {
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.StoreDataOutput", "write(byte [ ])", new Object[] { value });
/*     */     }
/*     */     
/*  95 */     this.store.setBytes(this.offset, value, value.length);
/*  96 */     this.offset += value.length;
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.StoreDataOutput", "write(byte [ ])");
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
/*     */   public void write(byte[] value, int offset, int length) throws IOException {
/* 111 */     this.store.setBytes(this.offset, value, length);
/* 112 */     this.offset += length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeBoolean(boolean arg0) throws IOException {
/* 120 */     if (Trace.isOn)
/* 121 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.null", "writeBoolean(boolean)", new Object[] {
/* 122 */             Boolean.valueOf(arg0)
/*     */           }); 
/* 124 */     UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException();
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.throwing(this, "com.ibm.mq.headers.internal.store.null", "writeBoolean(boolean)", traceRet1);
/*     */     }
/*     */     
/* 129 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeByte(int value) throws IOException {
/* 137 */     if (Trace.isOn)
/* 138 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.null", "writeByte(int)", new Object[] {
/* 139 */             Integer.valueOf(value)
/*     */           }); 
/* 141 */     this.buffer1[0] = (byte)value;
/* 142 */     this.store.setBytes(this.offset, this.buffer1, 1);
/* 143 */     this.offset++;
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.null", "writeByte(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeShort(int arg0) throws IOException {
/* 155 */     if (Trace.isOn)
/* 156 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.null", "writeShort(int)", new Object[] {
/* 157 */             Integer.valueOf(arg0)
/*     */           }); 
/* 159 */     UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException();
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.throwing(this, "com.ibm.mq.headers.internal.store.null", "writeShort(int)", traceRet1);
/*     */     }
/*     */     
/* 164 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeChar(int arg0) throws IOException {
/* 172 */     if (Trace.isOn)
/* 173 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.null", "writeChar(int)", new Object[] {
/* 174 */             Integer.valueOf(arg0)
/*     */           }); 
/* 176 */     UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException();
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.throwing(this, "com.ibm.mq.headers.internal.store.null", "writeChar(int)", traceRet1);
/*     */     }
/* 180 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeInt(int value) throws IOException {
/* 188 */     if (Trace.isOn)
/* 189 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.null", "writeInt(int)", new Object[] {
/* 190 */             Integer.valueOf(value)
/*     */           }); 
/* 192 */     this.store.setInt(this.offset, value);
/* 193 */     this.offset += 4;
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.exit(this, "com.ibm.mq.headers.internal.store.null", "writeInt(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeLong(long arg0) throws IOException {
/* 205 */     if (Trace.isOn)
/* 206 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.null", "writeLong(long)", new Object[] {
/* 207 */             Long.valueOf(arg0)
/*     */           }); 
/* 209 */     UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException();
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.throwing(this, "com.ibm.mq.headers.internal.store.null", "writeLong(long)", traceRet1);
/*     */     }
/*     */     
/* 214 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeFloat(float arg0) throws IOException {
/* 222 */     if (Trace.isOn)
/* 223 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.null", "writeFloat(float)", new Object[] {
/* 224 */             Float.valueOf(arg0)
/*     */           }); 
/* 226 */     UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException();
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.throwing(this, "com.ibm.mq.headers.internal.store.null", "writeFloat(float)", traceRet1);
/*     */     }
/*     */     
/* 231 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeDouble(double arg0) throws IOException {
/* 239 */     if (Trace.isOn)
/* 240 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.null", "writeDouble(double)", new Object[] {
/* 241 */             Double.valueOf(arg0)
/*     */           }); 
/* 243 */     UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException();
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.throwing(this, "com.ibm.mq.headers.internal.store.null", "writeDouble(double)", traceRet1);
/*     */     }
/*     */     
/* 248 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeBytes(String arg0) throws IOException {
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.null", "writeBytes(String)", new Object[] { arg0 });
/*     */     }
/*     */     
/* 260 */     UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException();
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.throwing(this, "com.ibm.mq.headers.internal.store.null", "writeBytes(String)", traceRet1);
/*     */     }
/*     */     
/* 265 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeChars(String arg0) throws IOException {
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.null", "writeChars(String)", new Object[] { arg0 });
/*     */     }
/*     */     
/* 277 */     UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException();
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.throwing(this, "com.ibm.mq.headers.internal.store.null", "writeChars(String)", traceRet1);
/*     */     }
/*     */     
/* 282 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeUTF(String arg0) throws IOException {
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.entry(this, "com.ibm.mq.headers.internal.store.null", "writeUTF(String)", new Object[] { arg0 });
/*     */     }
/*     */     
/* 294 */     UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException();
/* 295 */     if (Trace.isOn) {
/* 296 */       Trace.throwing(this, "com.ibm.mq.headers.internal.store.null", "writeUTF(String)", traceRet1);
/*     */     }
/*     */     
/* 299 */     throw traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\store\StoreDataOutput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */