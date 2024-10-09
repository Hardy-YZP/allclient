/*     */ package com.ibm.mq.headers.internal;
/*     */ 
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.internal.store.Store;
/*     */ import com.ibm.mq.headers.internal.validator.FieldValidator;
/*     */ import com.ibm.mq.headers.internal.validator.MQHeaderValidationException;
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
/*     */ public class MQLongField
/*     */   extends HeaderField
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQLongField.java";
/*     */   static final int SIZE = 4;
/*     */   final Integer defaultValue;
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.mq.headers.internal.MQLongField", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQLongField.java");
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
/*     */   public MQLongField(int offset, String name) {
/*  59 */     this(offset, name, 0, false);
/*  60 */     if (Trace.isOn)
/*  61 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongField", "<init>(int,String)", new Object[] {
/*  62 */             Integer.valueOf(offset), name
/*     */           }); 
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongField", "<init>(int,String)");
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
/*     */   public MQLongField(int offset, String name, int defaultValue, boolean fixed) {
/*  77 */     super(offset, name);
/*  78 */     if (Trace.isOn)
/*  79 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongField", "<init>(int,String,int,boolean)", new Object[] {
/*  80 */             Integer.valueOf(offset), name, 
/*  81 */             Integer.valueOf(defaultValue), Boolean.valueOf(fixed)
/*     */           }); 
/*  83 */     this.defaultValue = Integer.valueOf(defaultValue);
/*     */     
/*  85 */     if (fixed) {
/*  86 */       this.validator = new FieldValidator()
/*     */         {
/*     */           public void validate(Header h) throws MQDataException, IOException
/*     */           {
/*  90 */             if (Trace.isOn) {
/*  91 */               Trace.entry(this, "com.ibm.mq.headers.internal.MQLongField", "validate(Header)", new Object[] { h });
/*     */             }
/*     */             
/*  94 */             if (MQLongField.this.getIntValue(h) != MQLongField.this.defaultValue.intValue()) {
/*     */               
/*  96 */               MQHeaderValidationException traceRet1 = new MQHeaderValidationException(HeaderMessages.getMessage("MQHDR0011", new Object[] { h.type(), Integer.valueOf(this.this$0.getIntValue(h)), this.this$0.name }));
/*     */ 
/*     */               
/*  99 */               if (Trace.isOn) {
/* 100 */                 Trace.throwing(this, "com.ibm.mq.headers.internal.null", "validate(Header)", (Throwable)traceRet1);
/*     */               }
/*     */               
/* 103 */               throw traceRet1;
/*     */             } 
/*     */             
/* 106 */             if (Trace.isOn) {
/* 107 */               Trace.exit(this, "com.ibm.mq.headers.internal.null", "validate(Header)");
/*     */             }
/*     */           }
/*     */         };
/*     */     }
/*     */     
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongField", "<init>(int,String,int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int getIntValue(Store store, int offset) throws IOException {
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongField", "getIntValue(Store,int)", new Object[] { store, 
/* 123 */             Integer.valueOf(offset) });
/*     */     }
/* 125 */     int traceRet1 = store.getInt(this, offset);
/*     */     
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongField", "getIntValue(Store,int)", 
/* 129 */           Integer.valueOf(traceRet1));
/*     */     }
/* 131 */     return traceRet1;
/*     */   }
/*     */   
/*     */   protected final void setIntValue(Store store, int value, int offset) throws IOException {
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongField", "setIntValue(Store,int,int)", new Object[] { store, 
/* 137 */             Integer.valueOf(value), Integer.valueOf(offset) });
/*     */     }
/* 139 */     store.setInt(offset, value);
/*     */     
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongField", "setIntValue(Store,int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(Header h) {
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongField", "getValue(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 156 */     Object traceRet1 = Integer.valueOf(getIntValue(h));
/*     */     
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongField", "getValue(Header)", traceRet1);
/*     */     }
/* 161 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(Header h, Object value) {
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongField", "setValue(Header,Object)", new Object[] { h, value });
/*     */     }
/*     */     
/* 174 */     setIntValue(h, ((value == null) ? this.defaultValue : (Integer)value).intValue());
/*     */     
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongField", "setValue(Header,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIntValue(Header h) {
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongField", "getIntValue(Header)", new Object[] { h });
/*     */     }
/*     */     
/*     */     try {
/* 192 */       int traceRet1 = getIntValue(getStore(h), getOffset(h));
/*     */       
/* 194 */       if (Trace.isOn) {
/* 195 */         Trace.exit(this, "com.ibm.mq.headers.internal.MQLongField", "getIntValue(Header)", 
/* 196 */             Integer.valueOf(traceRet1));
/*     */       }
/* 198 */       return traceRet1;
/*     */     
/*     */     }
/* 201 */     catch (IOException e) {
/* 202 */       if (Trace.isOn) {
/* 203 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQLongField", "getIntValue(Header)", e);
/*     */       }
/*     */       
/* 206 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 207 */       if (Trace.isOn) {
/* 208 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQLongField", "getIntValue(Header)", traceRet2);
/*     */       }
/*     */       
/* 211 */       throw traceRet2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIntValue(Header h, int value) {
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongField", "setIntValue(Header,int)", new Object[] { h, 
/* 222 */             Integer.valueOf(value) });
/*     */     }
/*     */     try {
/* 225 */       int offset = getOffset(h);
/* 226 */       Store store = getStore(h, offset, 4, 4);
/*     */       
/* 228 */       setIntValue(store, value, offset);
/*     */     
/*     */     }
/* 231 */     catch (IOException e) {
/* 232 */       if (Trace.isOn) {
/* 233 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQLongField", "setIntValue(Header,int)", e);
/*     */       }
/*     */ 
/*     */       
/* 237 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 238 */       if (Trace.isOn) {
/* 239 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQLongField", "setIntValue(Header,int)", traceRet1);
/*     */       }
/*     */       
/* 242 */       throw traceRet1;
/*     */     } 
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongField", "setIntValue(Header,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object defaultValue() {
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongField", "defaultValue()");
/*     */     }
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongField", "defaultValue()", this.defaultValue);
/*     */     }
/* 261 */     return this.defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int write(Header h, DataOutput output, int encoding, int characterSet) throws IOException {
/* 270 */     if (Trace.isOn) {
/* 271 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongField", "write(Header,DataOutput,int,int)", new Object[] { h, output, 
/* 272 */             Integer.valueOf(encoding), 
/* 273 */             Integer.valueOf(characterSet) });
/*     */     }
/* 275 */     Store store = getStore(h);
/*     */     
/* 277 */     if (store.hasData() && store.matchesEncoding(encoding)) {
/* 278 */       store.writeTo(output, getOffset(h), 4);
/*     */     } else {
/* 280 */       output.writeInt(Store.isReversed(encoding) ? Store.reverse(getIntValue(h)) : getIntValue(h));
/*     */     } 
/*     */     
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongField", "write(Header,DataOutput,int,int)", 
/* 285 */           Integer.valueOf(4));
/*     */     }
/* 287 */     return 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size(Header h) {
/* 295 */     if (Trace.isOn) {
/* 296 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongField", "size(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 299 */     if (Trace.isOn) {
/* 300 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongField", "size(Header)", 
/* 301 */           Integer.valueOf(4));
/*     */     }
/* 303 */     return 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String type() {
/* 311 */     if (Trace.isOn) {
/* 312 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongField", "type()");
/*     */     }
/* 314 */     if (Trace.isOn) {
/* 315 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongField", "type()", "MQLONG");
/*     */     }
/* 317 */     return "MQLONG";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 326 */     if (Trace.isOn) {
/* 327 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongField", "toString()");
/*     */     }
/* 329 */     String traceRet1 = super.toString() + " (default: " + this.defaultValue + ')';
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongField", "toString()", traceRet1);
/*     */     }
/* 333 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\MQLongField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */