/*     */ package com.ibm.mq.headers.internal;
/*     */ 
/*     */ import com.ibm.mq.headers.Charsets;
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.internal.store.Store;
/*     */ import com.ibm.mq.headers.internal.validator.FieldValidator;
/*     */ import com.ibm.mq.headers.internal.validator.MQHeaderValidationException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQCharField
/*     */   extends HeaderField
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQCharField.java";
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.headers.internal.MQCharField", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQCharField.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   private static final Map<Integer, String> interns = new HashMap<>();
/*     */   
/*     */   protected final String defaultValue;
/*     */   
/*     */   protected static String intern(int length) {
/*  57 */     if (Trace.isOn)
/*  58 */       Trace.entry("com.ibm.mq.headers.internal.MQCharField", "intern(int)", new Object[] {
/*  59 */             Integer.valueOf(length)
/*     */           }); 
/*  61 */     Integer key = Integer.valueOf(length);
/*  62 */     String string = interns.get(key);
/*     */     
/*  64 */     if (string == null) {
/*  65 */       char[] chars = new char[length];
/*     */       
/*  67 */       Arrays.fill(chars, ' ');
/*  68 */       interns.put(key, string = new String(chars));
/*     */     } 
/*     */     
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.exit("com.ibm.mq.headers.internal.MQCharField", "intern(int)", string);
/*     */     }
/*  74 */     return string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCharField(int offset, String name) {
/*  82 */     this(offset, name, "");
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharField", "<init>(int,String)", new Object[] {
/*  85 */             Integer.valueOf(offset), name
/*     */           });
/*     */     }
/*     */     
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharField", "<init>(int,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCharField(int offset, String name, int length) {
/* 101 */     super(offset, name);
/* 102 */     if (Trace.isOn)
/* 103 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharField", "<init>(int,String,int)", new Object[] {
/* 104 */             Integer.valueOf(offset), name, Integer.valueOf(length)
/*     */           }); 
/* 106 */     if (length < 0) {
/*     */       
/* 108 */       IllegalArgumentException traceRet1 = new IllegalArgumentException(HeaderMessages.getMessage("MQHDR0012"));
/*     */       
/* 110 */       if (Trace.isOn) {
/* 111 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQCharField", "<init>(int,String,int)", traceRet1);
/*     */       }
/*     */       
/* 114 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 118 */     this.defaultValue = intern(length);
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharField", "<init>(int,String,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCharField(int offset, String name, String defaultValue) {
/* 131 */     this(offset, name, defaultValue, "StrucId".equals(name));
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharField", "<init>(int,String,String)", new Object[] {
/* 134 */             Integer.valueOf(offset), name, defaultValue
/*     */           });
/*     */     }
/*     */     
/* 138 */     if (defaultValue == null) {
/*     */       
/* 140 */       IllegalArgumentException traceRet1 = new IllegalArgumentException(HeaderMessages.getMessage("MQHDR0013"));
/*     */       
/* 142 */       if (Trace.isOn) {
/* 143 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQCharField", "<init>(int,String,String)", traceRet1);
/*     */       }
/*     */       
/* 146 */       throw traceRet1;
/*     */     } 
/*     */     
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharField", "<init>(int,String,String)");
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
/*     */   public MQCharField(int offset, String name, String defaultValue, boolean fixed) {
/* 162 */     super(offset, name);
/* 163 */     if (Trace.isOn)
/* 164 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharField", "<init>(int,String,String,boolean)", new Object[] {
/* 165 */             Integer.valueOf(offset), name, defaultValue, 
/* 166 */             Boolean.valueOf(fixed)
/*     */           }); 
/* 168 */     this.defaultValue = defaultValue;
/*     */     
/* 170 */     if (fixed) {
/* 171 */       this.validator = new FieldValidator()
/*     */         {
/*     */           public void validate(Header h) throws MQDataException, IOException
/*     */           {
/* 175 */             if (Trace.isOn) {
/* 176 */               Trace.entry(this, "com.ibm.mq.headers.internal.MQCharField", "validate(Header)", new Object[] { h });
/*     */             }
/*     */             
/* 179 */             if (!MQCharField.this.defaultValue.equals(MQCharField.this.getValue(h))) {
/*     */               
/* 181 */               MQHeaderValidationException traceRet1 = new MQHeaderValidationException(HeaderMessages.getMessage("MQHDR0011", new Object[] { h.type(), this.this$0.getValue(h), this.this$0.name }));
/*     */ 
/*     */               
/* 184 */               if (Trace.isOn) {
/* 185 */                 Trace.throwing(this, "com.ibm.mq.headers.internal.null", "validate(Header)", (Throwable)traceRet1);
/*     */               }
/*     */               
/* 188 */               throw traceRet1;
/*     */             } 
/*     */             
/* 191 */             if (Trace.isOn) {
/* 192 */               Trace.exit(this, "com.ibm.mq.headers.internal.null", "validate(Header)");
/*     */             }
/*     */           }
/*     */         };
/*     */     }
/*     */     
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharField", "<init>(int,String,String,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final String getStringValue(Store store, int offset, int length, int ccsid) throws IOException {
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharField", "getStringValue(Store,int,int,int)", new Object[] { store, 
/* 209 */             Integer.valueOf(offset), 
/* 210 */             Integer.valueOf(length), Integer.valueOf(ccsid) });
/*     */     }
/* 212 */     String traceRet1 = store.getString(this, offset, length, ccsid);
/*     */     
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharField", "getStringValue(Store,int,int,int)", traceRet1);
/*     */     }
/*     */     
/* 218 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected final int setStringValue(Store store, String value, int offset, int length, int ccsid) throws IOException {
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharField", "setStringValue(Store,String,int,int,int)", new Object[] { store, value, 
/*     */             
/* 226 */             Integer.valueOf(offset), Integer.valueOf(length), Integer.valueOf(ccsid) });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 231 */     int traceRet1 = store.setString(offset, value, length, ccsid);
/*     */     
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharField", "setStringValue(Store,String,int,int,int)", 
/* 235 */           Integer.valueOf(traceRet1));
/*     */     }
/* 237 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(Header h) {
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharField", "getValue(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 250 */     int characterSet = -1;
/*     */     try {
/* 252 */       characterSet = getStore(h).characterSet();
/* 253 */       Object traceRet1 = getStringValue(getStore(h), getOffset(h), size(h), characterSet);
/*     */       
/* 255 */       if (Trace.isOn) {
/* 256 */         Trace.exit(this, "com.ibm.mq.headers.internal.MQCharField", "getValue(Header)", traceRet1);
/*     */       }
/*     */       
/* 259 */       return traceRet1;
/*     */     
/*     */     }
/* 262 */     catch (IOException e) {
/* 263 */       if (Trace.isOn) {
/* 264 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQCharField", "getValue(Header)", e);
/*     */       }
/* 266 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 267 */       if (Trace.isOn) {
/* 268 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQCharField", "getValue(Header)", traceRet2);
/*     */       }
/*     */       
/* 271 */       throw traceRet2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(Header h, Object value) {
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharField", "setValue(Header,Object)", new Object[] { h, value });
/*     */     }
/*     */     
/*     */     try {
/* 286 */       int offset = getOffset(h);
/* 287 */       int size = size(h);
/* 288 */       Store store = getStore(h, offset, size, size);
/* 289 */       int ccsid = store.characterSet();
/*     */       
/* 291 */       if (value == null) {
/* 292 */         setStringValue(store, this.defaultValue, offset, size, ccsid);
/*     */       } else {
/* 294 */         setStringValue(store, (String)value, offset, size, ccsid);
/*     */       }
/*     */     
/*     */     }
/* 298 */     catch (ClassCastException e) {
/* 299 */       if (Trace.isOn) {
/* 300 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQCharField", "setValue(Header,Object)", e, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 305 */       IllegalArgumentException traceRet1 = new IllegalArgumentException(HeaderMessages.getMessage("MQHDR0014"));
/* 306 */       if (Trace.isOn) {
/* 307 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQCharField", "setValue(Header,Object)", traceRet1, 1);
/*     */       }
/*     */       
/* 310 */       throw traceRet1;
/*     */ 
/*     */     
/*     */     }
/* 314 */     catch (IOException e) {
/* 315 */       if (Trace.isOn) {
/* 316 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQCharField", "setValue(Header,Object)", e, 2);
/*     */       }
/*     */       
/* 319 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 320 */       if (Trace.isOn) {
/* 321 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQCharField", "setValue(Header,Object)", traceRet2, 2);
/*     */       }
/*     */       
/* 324 */       throw traceRet2;
/*     */     } 
/* 326 */     if (Trace.isOn) {
/* 327 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharField", "setValue(Header,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object defaultValue() {
/* 337 */     if (Trace.isOn) {
/* 338 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharField", "defaultValue()");
/*     */     }
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharField", "defaultValue()", this.defaultValue);
/*     */     }
/* 343 */     return this.defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int write(Header h, DataOutput output, int encoding, int characterSet) throws IOException {
/* 352 */     if (Trace.isOn) {
/* 353 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharField", "write(Header,DataOutput,int,int)", new Object[] { h, output, 
/* 354 */             Integer.valueOf(encoding), 
/* 355 */             Integer.valueOf(characterSet) });
/*     */     }
/* 357 */     Store store = getStore(h);
/* 358 */     int size = size(h);
/*     */     
/* 360 */     if (store.hasData() && store.characterSet() == characterSet) {
/*     */ 
/*     */       
/* 363 */       store.writeTo(output, getOffset(h), size);
/*     */     } else {
/* 365 */       output.write(Charsets.convert((String)getValue(h), characterSet));
/*     */     } 
/*     */     
/* 368 */     if (Trace.isOn) {
/* 369 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharField", "write(Header,DataOutput,int,int)", 
/* 370 */           Integer.valueOf(size));
/*     */     }
/* 372 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size(Header h) {
/* 380 */     if (Trace.isOn) {
/* 381 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharField", "size(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 384 */     int traceRet1 = this.defaultValue.length();
/*     */     
/* 386 */     if (Trace.isOn) {
/* 387 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharField", "size(Header)", 
/* 388 */           Integer.valueOf(traceRet1));
/*     */     }
/* 390 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String type() {
/* 398 */     if (Trace.isOn) {
/* 399 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharField", "type()");
/*     */     }
/* 401 */     String traceRet1 = "MQCHAR" + this.defaultValue.length();
/*     */     
/* 403 */     if (Trace.isOn) {
/* 404 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharField", "type()", traceRet1);
/*     */     }
/* 406 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 415 */     if (Trace.isOn) {
/* 416 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharField", "toString()");
/*     */     }
/* 418 */     String traceRet1 = super.toString() + " (default: \"" + this.defaultValue + "\")";
/* 419 */     if (Trace.isOn) {
/* 420 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharField", "toString()", traceRet1);
/*     */     }
/* 422 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\MQCharField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */