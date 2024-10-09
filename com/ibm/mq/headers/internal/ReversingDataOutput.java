/*     */ package com.ibm.mq.headers.internal;
/*     */ 
/*     */ import com.ibm.mq.headers.internal.store.Store;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ 
/*     */ 
/*     */ public class ReversingDataOutput
/*     */   implements DataOutput
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/ReversingDataOutput.java";
/*     */   final DataOutput out;
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.mq.headers.internal.ReversingDataOutput", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/ReversingDataOutput.java");
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
/*     */   public ReversingDataOutput(DataOutput out) {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "<init>(DataOutput)", new Object[] { out });
/*     */     }
/*     */     
/*  58 */     this.out = out;
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.exit(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "<init>(DataOutput)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeInt(int value) throws IOException {
/*  70 */     if (Trace.isOn)
/*  71 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeInt(int)", new Object[] {
/*  72 */             Integer.valueOf(value)
/*     */           }); 
/*  74 */     this.out.writeInt(Store.reverse(value));
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.exit(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeInt(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(int arg0) throws IOException {
/*  86 */     if (Trace.isOn)
/*  87 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "write(int)", new Object[] {
/*  88 */             Integer.valueOf(arg0)
/*     */           }); 
/*  90 */     this.out.write(arg0);
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.exit(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "write(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] arg0) throws IOException {
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "write(byte [ ])", new Object[] { arg0 });
/*     */     }
/*     */     
/* 106 */     this.out.write(arg0);
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.exit(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "write(byte [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] arg0, int arg1, int arg2) throws IOException {
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "write(byte [ ],int,int)", new Object[] { arg0, 
/* 120 */             Integer.valueOf(arg1), Integer.valueOf(arg2) });
/*     */     }
/*     */     
/* 123 */     this.out.write(arg0, arg1, arg2);
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.exit(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "write(byte [ ],int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeBoolean(boolean arg0) throws IOException {
/* 136 */     if (Trace.isOn)
/* 137 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeBoolean(boolean)", new Object[] {
/* 138 */             Boolean.valueOf(arg0)
/*     */           }); 
/* 140 */     this.out.writeBoolean(arg0);
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.exit(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeBoolean(boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeByte(int arg0) throws IOException {
/* 153 */     if (Trace.isOn)
/* 154 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeByte(int)", new Object[] {
/* 155 */             Integer.valueOf(arg0)
/*     */           }); 
/* 157 */     this.out.writeByte(arg0);
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.exit(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeByte(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeBytes(String arg0) throws IOException {
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeBytes(String)", new Object[] { arg0 });
/*     */     }
/*     */     
/* 173 */     this.out.writeBytes(arg0);
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.exit(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeBytes(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeChar(int arg0) throws IOException {
/* 185 */     if (Trace.isOn)
/* 186 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeChar(int)", new Object[] {
/* 187 */             Integer.valueOf(arg0)
/*     */           }); 
/* 189 */     this.out.writeChar(arg0);
/* 190 */     if (Trace.isOn) {
/* 191 */       Trace.exit(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeChar(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeChars(String arg0) throws IOException {
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeChars(String)", new Object[] { arg0 });
/*     */     }
/*     */     
/* 205 */     this.out.writeChars(arg0);
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.exit(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeChars(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeDouble(double arg0) throws IOException {
/* 217 */     if (Trace.isOn)
/* 218 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeDouble(double)", new Object[] {
/* 219 */             Double.valueOf(arg0)
/*     */           }); 
/* 221 */     this.out.writeDouble(arg0);
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.exit(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeDouble(double)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeFloat(float arg0) throws IOException {
/* 233 */     if (Trace.isOn)
/* 234 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeFloat(float)", new Object[] {
/* 235 */             Float.valueOf(arg0)
/*     */           }); 
/* 237 */     this.out.writeFloat(arg0);
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.exit(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeFloat(float)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeLong(long value) throws IOException {
/* 249 */     if (Trace.isOn)
/* 250 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeLong(long)", new Object[] {
/* 251 */             Long.valueOf(value)
/*     */           }); 
/* 253 */     this.out.writeLong(Store.reverse(value));
/* 254 */     if (Trace.isOn) {
/* 255 */       Trace.exit(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeLong(long)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeShort(int arg0) throws IOException {
/* 265 */     if (Trace.isOn)
/* 266 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeShort(int)", new Object[] {
/* 267 */             Integer.valueOf(arg0)
/*     */           }); 
/* 269 */     this.out.writeShort(arg0);
/* 270 */     if (Trace.isOn) {
/* 271 */       Trace.exit(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeShort(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeUTF(String arg0) throws IOException {
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeUTF(String)", new Object[] { arg0 });
/*     */     }
/*     */     
/* 285 */     this.out.writeUTF(arg0);
/* 286 */     if (Trace.isOn)
/* 287 */       Trace.exit(this, "com.ibm.mq.headers.internal.ReversingDataOutput", "writeUTF(String)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\ReversingDataOutput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */