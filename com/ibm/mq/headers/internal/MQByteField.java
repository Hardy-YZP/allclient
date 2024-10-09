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
/*     */ public class MQByteField
/*     */   extends HeaderField
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQByteField.java";
/*     */   final int defaultLength;
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.mq.headers.internal.MQByteField", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQByteField.java");
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
/*     */   public MQByteField(int offset, String name) {
/*  55 */     this(offset, name, 1);
/*  56 */     if (Trace.isOn)
/*  57 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteField", "<init>(int,String)", new Object[] {
/*  58 */             Integer.valueOf(offset), name
/*     */           }); 
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQByteField", "<init>(int,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQByteField(int offset, String name, int length) {
/*  72 */     super(offset, name);
/*  73 */     if (Trace.isOn)
/*  74 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteField", "<init>(int,String,int)", new Object[] {
/*  75 */             Integer.valueOf(offset), name, Integer.valueOf(length)
/*     */           }); 
/*  77 */     this.defaultLength = length;
/*     */     
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQByteField", "<init>(int,String,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final byte[] getBytesValue(Header h, int offset, int length) {
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteField", "getBytesValue(Header,int,int)", new Object[] { h, 
/*  88 */             Integer.valueOf(offset), Integer.valueOf(length) });
/*     */     }
/*     */     try {
/*  91 */       byte[] traceRet1 = getStore(h).getBytes(this, offset, length);
/*     */       
/*  93 */       if (Trace.isOn) {
/*  94 */         Trace.exit(this, "com.ibm.mq.headers.internal.MQByteField", "getBytesValue(Header,int,int)", traceRet1);
/*     */       }
/*     */       
/*  97 */       return traceRet1;
/*     */     
/*     */     }
/* 100 */     catch (IOException e) {
/* 101 */       if (Trace.isOn) {
/* 102 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQByteField", "getBytesValue(Header,int,int)", e);
/*     */       }
/*     */       
/* 105 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 106 */       if (Trace.isOn) {
/* 107 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQByteField", "getBytesValue(Header,int,int)", traceRet2);
/*     */       }
/*     */       
/* 110 */       throw traceRet2;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected final int setBytesValue(Store store, byte[] value, int offset, int length) throws IOException {
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteField", "setBytesValue(Store,byte [ ],int,int)", new Object[] { store, value, 
/*     */             
/* 119 */             Integer.valueOf(offset), Integer.valueOf(length) });
/*     */     }
/* 121 */     if (value == null) {
/* 122 */       store.clear(offset, length);
/* 123 */     } else if (value.length >= length) {
/* 124 */       store.setBytes(offset, value, length);
/*     */     } else {
/* 126 */       store.setBytes(offset, value, value.length);
/* 127 */       store.clear(offset + value.length, length - value.length);
/*     */     } 
/*     */     
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQByteField", "setBytesValue(Store,byte [ ],int,int)", 
/* 132 */           Integer.valueOf(length));
/*     */     }
/* 134 */     return length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(Header h) {
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteField", "getValue(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 146 */     Object traceRet1 = getBytesValue(h, getOffset(h), size(h));
/*     */     
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQByteField", "getValue(Header)", traceRet1);
/*     */     }
/* 151 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(Header h, Object value) {
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteField", "setValue(Header,Object)", new Object[] { h, value });
/*     */     }
/*     */     
/*     */     try {
/* 164 */       int offset = getOffset(h);
/* 165 */       int size = size(h);
/* 166 */       Store store = getStore(h, offset, size, size);
/*     */       
/* 168 */       setBytesValue(store, (byte[])value, offset, size);
/*     */     
/*     */     }
/* 171 */     catch (ClassCastException e) {
/* 172 */       if (Trace.isOn) {
/* 173 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQByteField", "setValue(Header,Object)", e, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 178 */       IllegalArgumentException traceRet1 = new IllegalArgumentException(HeaderMessages.getMessage("MQHDR0017"));
/* 179 */       if (Trace.isOn) {
/* 180 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQByteField", "setValue(Header,Object)", traceRet1, 1);
/*     */       }
/*     */       
/* 183 */       throw traceRet1;
/*     */ 
/*     */     
/*     */     }
/* 187 */     catch (IOException e) {
/* 188 */       if (Trace.isOn) {
/* 189 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQByteField", "setValue(Header,Object)", e, 2);
/*     */       }
/*     */       
/* 192 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 193 */       if (Trace.isOn) {
/* 194 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQByteField", "setValue(Header,Object)", traceRet2, 2);
/*     */       }
/*     */       
/* 197 */       throw traceRet2;
/*     */     } 
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQByteField", "setValue(Header,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object defaultValue() {
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteField", "defaultValue()");
/*     */     }
/* 213 */     Object traceRet1 = new byte[this.defaultLength];
/*     */     
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQByteField", "defaultValue()", traceRet1);
/*     */     }
/* 218 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int write(Header h, DataOutput output, int encoding, int characterSet) throws IOException {
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteField", "write(Header,DataOutput,int,int)", new Object[] { h, output, 
/* 229 */             Integer.valueOf(encoding), 
/* 230 */             Integer.valueOf(characterSet) });
/*     */     }
/* 232 */     Store store = getStore(h);
/* 233 */     int size = size(h);
/*     */     
/* 235 */     if (store.hasData()) {
/* 236 */       store.writeTo(output, getOffset(h), size);
/*     */     } else {
/* 238 */       for (int i = 0; i < size; i++) {
/* 239 */         output.writeByte(0);
/*     */       }
/*     */     } 
/*     */     
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQByteField", "write(Header,DataOutput,int,int)", 
/* 245 */           Integer.valueOf(size));
/*     */     }
/* 247 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size(Header h) {
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteField", "size(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQByteField", "size(Header)", 
/* 261 */           Integer.valueOf(this.defaultLength));
/*     */     }
/* 263 */     return this.defaultLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String type() {
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteField", "type()");
/*     */     }
/* 274 */     String traceRet1 = "MQBYTE" + this.defaultLength;
/*     */     
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQByteField", "type()", traceRet1);
/*     */     }
/* 279 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\MQByteField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */