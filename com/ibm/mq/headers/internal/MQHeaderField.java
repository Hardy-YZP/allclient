/*     */ package com.ibm.mq.headers.internal;
/*     */ 
/*     */ import com.ibm.mq.headers.internal.store.Store;
/*     */ import com.ibm.mq.headers.internal.store.StoreDataOutput;
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
/*     */ public class MQHeaderField
/*     */   extends HeaderField
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQHeaderField.java";
/*     */   final Class<?> headerClass;
/*     */   final String type;
/*     */   final HeaderType headerType;
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.mq.headers.internal.MQHeaderField", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQHeaderField.java");
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
/*     */   MQHeaderField(int offset, String name, Header header) {
/*  53 */     super(offset, name);
/*  54 */     if (Trace.isOn)
/*  55 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQHeaderField", "<init>(int,String,Header)", new Object[] {
/*  56 */             Integer.valueOf(offset), name, header
/*     */           }); 
/*  58 */     this.headerClass = header.getClass();
/*  59 */     this.type = header.type();
/*  60 */     this.headerType = header.headerType();
/*     */     
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQHeaderField", "<init>(int,String,Header)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   MQHeaderField(int offset, String name, HeaderType headerType, Class<?> headerClass) {
/*  69 */     super(offset, name);
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQHeaderField", "<init>(int,String,HeaderType,Class<?>)", new Object[] {
/*  72 */             Integer.valueOf(offset), name, headerType, headerClass
/*     */           });
/*     */     }
/*  75 */     this.headerClass = headerClass;
/*  76 */     this.type = headerType.name;
/*  77 */     this.headerType = headerType;
/*     */     
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQHeaderField", "<init>(int,String,HeaderType,Class<?>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Header getHeaderInstance(Header h) throws IOException {
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQHeaderField", "getHeaderInstance(Header)", new Object[] { h });
/*     */     }
/*     */     
/*     */     try {
/*  95 */       Header traceRet1 = h.newInstance(this.headerClass, this.headerType, new NestedStore(h, getOffset(h)));
/*     */       
/*  97 */       if (Trace.isOn) {
/*  98 */         Trace.exit(this, "com.ibm.mq.headers.internal.MQHeaderField", "getHeaderInstance(Header)", traceRet1);
/*     */       }
/*     */       
/* 101 */       return traceRet1;
/*     */     
/*     */     }
/* 104 */     catch (InstantiationException e) {
/* 105 */       if (Trace.isOn) {
/* 106 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQHeaderField", "getHeaderInstance(Header)", e, 1);
/*     */       }
/*     */       
/* 109 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 110 */       if (Trace.isOn) {
/* 111 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQHeaderField", "getHeaderInstance(Header)", traceRet2, 1);
/*     */       }
/*     */       
/* 114 */       throw traceRet2;
/*     */     
/*     */     }
/* 117 */     catch (IllegalAccessException e) {
/* 118 */       if (Trace.isOn) {
/* 119 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQHeaderField", "getHeaderInstance(Header)", e, 2);
/*     */       }
/*     */       
/* 122 */       RuntimeException traceRet3 = new RuntimeException(e);
/* 123 */       if (Trace.isOn) {
/* 124 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQHeaderField", "getHeaderInstance(Header)", traceRet3, 2);
/*     */       }
/*     */       
/* 127 */       throw traceRet3;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String type() {
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQHeaderField", "type()");
/*     */     }
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQHeaderField", "type()", this.type);
/*     */     }
/* 142 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object defaultValue() {
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQHeaderField", "defaultValue()");
/*     */     }
/*     */     try {
/* 154 */       Object traceRet1 = this.headerClass.newInstance();
/*     */       
/* 156 */       if (Trace.isOn) {
/* 157 */         Trace.exit(this, "com.ibm.mq.headers.internal.MQHeaderField", "defaultValue()", traceRet1);
/*     */       }
/*     */       
/* 160 */       return traceRet1;
/*     */     
/*     */     }
/* 163 */     catch (InstantiationException e) {
/* 164 */       if (Trace.isOn) {
/* 165 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQHeaderField", "defaultValue()", e, 1);
/*     */       }
/*     */       
/* 168 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 169 */       if (Trace.isOn) {
/* 170 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQHeaderField", "defaultValue()", traceRet2, 1);
/*     */       }
/*     */       
/* 173 */       throw traceRet2;
/*     */     
/*     */     }
/* 176 */     catch (IllegalAccessException e) {
/* 177 */       if (Trace.isOn) {
/* 178 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQHeaderField", "defaultValue()", e, 2);
/*     */       }
/*     */       
/* 181 */       RuntimeException traceRet3 = new RuntimeException(e);
/* 182 */       if (Trace.isOn) {
/* 183 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQHeaderField", "defaultValue()", traceRet3, 2);
/*     */       }
/*     */       
/* 186 */       throw traceRet3;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(Header h) {
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQHeaderField", "getValue(Header)", new Object[] { h });
/*     */     }
/*     */     
/*     */     try {
/* 200 */       Object traceRet1 = getHeaderInstance(h);
/*     */       
/* 202 */       if (Trace.isOn) {
/* 203 */         Trace.exit(this, "com.ibm.mq.headers.internal.MQHeaderField", "getValue(Header)", traceRet1);
/*     */       }
/*     */       
/* 206 */       return traceRet1;
/*     */     
/*     */     }
/* 209 */     catch (IOException e) {
/* 210 */       if (Trace.isOn) {
/* 211 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQHeaderField", "getValue(Header)", e);
/*     */       }
/*     */       
/* 214 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 215 */       if (Trace.isOn) {
/* 216 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQHeaderField", "getValue(Header)", traceRet2);
/*     */       }
/*     */       
/* 219 */       throw traceRet2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(Header h, Object value) {
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQHeaderField", "setValue(Header,Object)", new Object[] { h, value });
/*     */     }
/*     */     
/*     */     try {
/* 234 */       int size = size(h);
/*     */       
/* 236 */       if (value == null) {
/* 237 */         Store store = h.store();
/*     */         
/* 239 */         if (store != null) {
/* 240 */           store.clear(getOffset(h), size);
/*     */         }
/* 242 */       } else if (value instanceof Header) {
/* 243 */         Header header = (Header)value;
/* 244 */         int offset = getOffset(h);
/* 245 */         StoreDataOutput storeDataOutput = new StoreDataOutput(h.store(offset, size, header.size()), offset);
/*     */         
/* 247 */         header.write((DataOutput)storeDataOutput, h.encoding(), h.characterSet());
/*     */       } else {
/*     */         
/* 250 */         IllegalArgumentException traceRet1 = new IllegalArgumentException(HeaderMessages.getMessage("MQHDR0003", new Object[] { Header.class.getName() }));
/*     */         
/* 252 */         if (Trace.isOn) {
/* 253 */           Trace.throwing(this, "com.ibm.mq.headers.internal.MQHeaderField", "setValue(Header,Object)", traceRet1, 1);
/*     */         }
/*     */         
/* 256 */         throw traceRet1;
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 261 */     catch (IOException e) {
/* 262 */       if (Trace.isOn) {
/* 263 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQHeaderField", "setValue(Header,Object)", e);
/*     */       }
/*     */       
/* 266 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 267 */       if (Trace.isOn) {
/* 268 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQHeaderField", "setValue(Header,Object)", traceRet2, 2);
/*     */       }
/*     */       
/* 271 */       throw traceRet2;
/*     */     } 
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQHeaderField", "setValue(Header,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int write(Header h, DataOutput output, int encoding, int characterSet) throws IOException {
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQHeaderField", "write(Header,DataOutput,int,int)", new Object[] { h, output, 
/* 287 */             Integer.valueOf(encoding), 
/* 288 */             Integer.valueOf(characterSet) });
/*     */     }
/* 290 */     int traceRet1 = ((Header)getValue(h)).write(output, encoding, characterSet);
/*     */     
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQHeaderField", "write(Header,DataOutput,int,int)", 
/* 294 */           Integer.valueOf(traceRet1));
/*     */     }
/* 296 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size(Header h) {
/* 304 */     if (Trace.isOn) {
/* 305 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQHeaderField", "size(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 308 */     int traceRet1 = this.headerType.size(h);
/*     */     
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQHeaderField", "size(Header)", 
/* 312 */           Integer.valueOf(traceRet1));
/*     */     }
/* 314 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 322 */     if (Trace.isOn) {
/* 323 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQHeaderField", "toString()");
/*     */     }
/*     */     
/* 326 */     String traceRet1 = super.toString() + " (" + this.headerClass.getName() + ", headerType: " + this.headerType + ")";
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQHeaderField", "toString()", traceRet1);
/*     */     }
/* 330 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\MQHeaderField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */