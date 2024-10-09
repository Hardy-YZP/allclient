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
/*     */ public class MQInt64Field
/*     */   extends HeaderField
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQInt64Field.java";
/*     */   static final int SIZE = 8;
/*     */   final Long defaultValue;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.headers.internal.MQInt64Field", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQInt64Field.java");
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
/*     */   public MQInt64Field(int offset, String name) {
/*  56 */     this(offset, name, 0L);
/*  57 */     if (Trace.isOn)
/*  58 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQInt64Field", "<init>(int,String)", new Object[] {
/*  59 */             Integer.valueOf(offset), name
/*     */           }); 
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQInt64Field", "<init>(int,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQInt64Field(int offset, String name, long defaultValue) {
/*  73 */     super(offset, name);
/*  74 */     if (Trace.isOn)
/*  75 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQInt64Field", "<init>(int,String,long)", new Object[] {
/*  76 */             Integer.valueOf(offset), name, Long.valueOf(defaultValue)
/*     */           }); 
/*  78 */     this.defaultValue = Long.valueOf(defaultValue);
/*     */     
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQInt64Field", "<init>(int,String,long)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final long getLongValue(Store store, int offset) throws IOException {
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQInt64Field", "getLongValue(Store,int)", new Object[] { store, 
/*  89 */             Integer.valueOf(offset) });
/*     */     }
/*  91 */     long traceRet1 = store.getLong(this, offset);
/*     */     
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQInt64Field", "getLongValue(Store,int)", 
/*  95 */           Long.valueOf(traceRet1));
/*     */     }
/*  97 */     return traceRet1;
/*     */   }
/*     */   
/*     */   protected final void setLongValue(Store store, long value, int offset) throws IOException {
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQInt64Field", "setLongValue(Store,long,int)", new Object[] { store, 
/* 103 */             Long.valueOf(value), Integer.valueOf(offset) });
/*     */     }
/* 105 */     store.setLong(offset, value);
/*     */     
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQInt64Field", "setLongValue(Store,long,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(Header h) {
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQInt64Field", "getValue(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 123 */     Object traceRet1 = Long.valueOf(getLongValue(h));
/*     */     
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQInt64Field", "getValue(Header)", traceRet1);
/*     */     }
/* 128 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(Header h, Object value) {
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQInt64Field", "setValue(Header,Object)", new Object[] { h, value });
/*     */     }
/*     */     
/* 141 */     setLongValue(h, ((value == null) ? this.defaultValue : (Long)value).longValue());
/*     */     
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQInt64Field", "setValue(Header,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLongValue(Header h) {
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQInt64Field", "getLongValue(Header)", new Object[] { h });
/*     */     }
/*     */     
/*     */     try {
/* 159 */       long traceRet1 = getLongValue(getStore(h), getOffset(h));
/*     */       
/* 161 */       if (Trace.isOn) {
/* 162 */         Trace.exit(this, "com.ibm.mq.headers.internal.MQInt64Field", "getLongValue(Header)", 
/* 163 */             Long.valueOf(traceRet1));
/*     */       }
/* 165 */       return traceRet1;
/*     */     
/*     */     }
/* 168 */     catch (IOException e) {
/* 169 */       if (Trace.isOn) {
/* 170 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQInt64Field", "getLongValue(Header)", e);
/*     */       }
/*     */       
/* 173 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 174 */       if (Trace.isOn) {
/* 175 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQInt64Field", "getLongValue(Header)", traceRet2);
/*     */       }
/*     */       
/* 178 */       throw traceRet2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLongValue(Header h, long value) {
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQInt64Field", "setLongValue(Header,long)", new Object[] { h, 
/* 189 */             Long.valueOf(value) });
/*     */     }
/*     */     try {
/* 192 */       int offset = getOffset(h);
/* 193 */       Store store = getStore(h, offset, 8, 8);
/*     */       
/* 195 */       setLongValue(store, value, offset);
/*     */     
/*     */     }
/* 198 */     catch (IOException e) {
/* 199 */       if (Trace.isOn) {
/* 200 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQInt64Field", "setLongValue(Header,long)", e);
/*     */       }
/*     */ 
/*     */       
/* 204 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 205 */       if (Trace.isOn) {
/* 206 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQInt64Field", "setLongValue(Header,long)", traceRet1);
/*     */       }
/*     */       
/* 209 */       throw traceRet1;
/*     */     } 
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQInt64Field", "setLongValue(Header,long)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object defaultValue() {
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQInt64Field", "defaultValue()");
/*     */     }
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQInt64Field", "defaultValue()", this.defaultValue);
/*     */     }
/*     */     
/* 229 */     return this.defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int write(Header h, DataOutput output, int encoding, int characterSet) throws IOException {
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQInt64Field", "write(Header,DataOutput,int,int)", new Object[] { h, output, 
/* 240 */             Integer.valueOf(encoding), 
/* 241 */             Integer.valueOf(characterSet) });
/*     */     }
/* 243 */     Store store = getStore(h);
/*     */     
/* 245 */     if (store.hasData() && store.matchesEncoding(encoding)) {
/* 246 */       store.writeTo(output, getOffset(h), 8);
/*     */     } else {
/* 248 */       output.writeLong(Store.isReversed(encoding) ? 
/* 249 */           Store.reverse(getLongValue(h)) : 
/* 250 */           getLongValue(h));
/*     */     } 
/*     */     
/* 253 */     if (Trace.isOn) {
/* 254 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQInt64Field", "write(Header,DataOutput,int,int)", 
/* 255 */           Integer.valueOf(8));
/*     */     }
/* 257 */     return 8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size(Header h) {
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQInt64Field", "size(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQInt64Field", "size(Header)", 
/* 271 */           Integer.valueOf(8));
/*     */     }
/* 273 */     return 8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String type() {
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQInt64Field", "type()");
/*     */     }
/* 284 */     if (Trace.isOn) {
/* 285 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQInt64Field", "type()", "MQINT64");
/*     */     }
/* 287 */     return "MQINT64";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQInt64Field", "toString()");
/*     */     }
/* 299 */     String traceRet1 = super.toString() + " (default: " + this.defaultValue + ')';
/* 300 */     if (Trace.isOn) {
/* 301 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQInt64Field", "toString()", traceRet1);
/*     */     }
/* 303 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\MQInt64Field.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */