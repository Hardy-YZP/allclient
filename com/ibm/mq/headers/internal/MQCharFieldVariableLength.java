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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQCharFieldVariableLength
/*     */   extends MQCharField
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQCharFieldVariableLength.java";
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.mq.headers.internal.MQCharFieldVariableLength", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQCharFieldVariableLength.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class DefaultField
/*     */     extends MQLongField
/*     */   {
/*     */     DefaultField(int offset, String name) {
/*  55 */       super(offset, name);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getIntValue(Header h) {
/*     */       try {
/*  62 */         return getStore(h).characterSet();
/*     */       
/*     */       }
/*  65 */       catch (IOException e) {
/*  66 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setIntValue(Header h, int value) {
/*  73 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*  77 */   protected static final MQLongField ccsidDefaultField = new DefaultField(-1, "ccsidDefaultField");
/*     */ 
/*     */   
/*     */   static final int ALIGNMENT = 4;
/*     */ 
/*     */   
/*     */   final MQLongField lengthRef;
/*     */ 
/*     */   
/*     */   final MQLongField totalLengthRef;
/*     */ 
/*     */   
/*     */   final int totalLengthOffset;
/*     */ 
/*     */   
/*     */   final MQLongField ccsidRef;
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCharFieldVariableLength(int offset, String name, MQLongField lengthRef, MQLongField totalLengthRef) {
/*  97 */     this(offset, name, lengthRef, totalLengthRef, ccsidDefaultField);
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "<init>(int,String,MQLongField,MQLongField)", new Object[] {
/* 100 */             Integer.valueOf(offset), name, lengthRef, totalLengthRef
/*     */           });
/*     */     }
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "<init>(int,String,MQLongField,MQLongField)");
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
/*     */   public MQCharFieldVariableLength(int offset, String name, MQLongField lengthRef, MQLongField totalLengthRef, MQLongField ccsidRef) {
/* 123 */     super(offset, name, 0);
/* 124 */     if (Trace.isOn)
/* 125 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "<init>(int,String,MQLongField,MQLongField,MQLongField)", new Object[] {
/*     */             
/* 127 */             Integer.valueOf(offset), name, lengthRef, totalLengthRef, ccsidRef
/*     */           }); 
/* 129 */     this.lengthRef = lengthRef;
/* 130 */     this.totalLengthRef = totalLengthRef;
/* 131 */     this.totalLengthOffset = totalLengthRef.defaultValue.intValue();
/* 132 */     this.ccsidRef = (ccsidRef == null) ? ccsidDefaultField : ccsidRef;
/* 133 */     this.validator = new FieldValidator()
/*     */       {
/*     */         public void validate(Header h) throws MQDataException, IOException
/*     */         {
/* 137 */           if (Trace.isOn) {
/* 138 */             Trace.entry(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "validate(Header)", new Object[] { h });
/*     */           }
/*     */           
/* 141 */           if (MQCharFieldVariableLength.this.totalLength(h) < 0) {
/*     */             
/* 143 */             MQHeaderValidationException traceRet1 = new MQHeaderValidationException("Header " + h.type() + " has an invalid value " + MQCharFieldVariableLength.this.totalLength(h) + " for field " + MQCharFieldVariableLength.this.totalLengthRef.name);
/*     */ 
/*     */             
/* 146 */             if (Trace.isOn) {
/* 147 */               Trace.throwing(this, "com.ibm.mq.headers.internal.null", "validate(Header)", (Throwable)traceRet1);
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 153 */             throw traceRet1;
/*     */           } 
/* 155 */           if (Trace.isOn) {
/* 156 */             Trace.exit(this, "com.ibm.mq.headers.internal.null", "validate(Header)");
/*     */           }
/*     */         }
/*     */       };
/*     */     
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "<init>(int,String,MQLongField,MQLongField,MQLongField)");
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
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "getValue(Header)", new Object[] { h });
/*     */     }
/*     */     
/*     */     try {
/* 178 */       Object traceRet1 = getStringValue(getStore(h), getOffset(h), stringLength(h), ccsid(h));
/*     */       
/* 180 */       if (Trace.isOn) {
/* 181 */         Trace.exit(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "getValue(Header)", traceRet1);
/*     */       }
/*     */       
/* 184 */       return traceRet1;
/*     */     
/*     */     }
/* 187 */     catch (IOException e) {
/* 188 */       if (Trace.isOn) {
/* 189 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "getValue(Header)", e);
/*     */       }
/*     */       
/* 192 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 193 */       if (Trace.isOn) {
/* 194 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "getValue(Header)", traceRet2);
/*     */       }
/*     */       
/* 197 */       throw traceRet2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(Header h, Object value) {
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "setValue(Header,Object)", new Object[] { h, value });
/*     */     }
/*     */     
/*     */     try {
/* 211 */       int offset = getOffset(h);
/* 212 */       String string = (value == null) ? this.defaultValue : (String)value;
/* 213 */       Store store = getStore(h, offset, size(h), string.length());
/* 214 */       int ccsid = ccsid(h);
/* 215 */       int size = setStringValue(store, string, offset, 0, ccsid);
/* 216 */       int paddedLength = getPaddedLength(size, 4);
/*     */       
/* 218 */       if (this.lengthRef != null) {
/* 219 */         this.lengthRef.setIntValue(h, size);
/*     */       }
/*     */       
/* 222 */       this.totalLengthRef.setIntValue(h, paddedLength + this.totalLengthOffset);
/*     */     
/*     */     }
/* 225 */     catch (ClassCastException e) {
/* 226 */       if (Trace.isOn) {
/* 227 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "setValue(Header,Object)", e, 1);
/*     */       }
/*     */ 
/*     */       
/* 231 */       IllegalArgumentException traceRet1 = new IllegalArgumentException("Value must be an instance of java.lang.String");
/*     */       
/* 233 */       if (Trace.isOn) {
/* 234 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "setValue(Header,Object)", traceRet1, 1);
/*     */       }
/*     */       
/* 237 */       throw traceRet1;
/*     */     
/*     */     }
/* 240 */     catch (IOException e) {
/* 241 */       if (Trace.isOn) {
/* 242 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "setValue(Header,Object)", e, 2);
/*     */       }
/*     */       
/* 245 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 246 */       if (Trace.isOn) {
/* 247 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "setValue(Header,Object)", traceRet2, 2);
/*     */       }
/*     */       
/* 250 */       throw traceRet2;
/*     */     } 
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "setValue(Header,Object)");
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
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "write(Header,DataOutput,int,int)", new Object[] { h, output, 
/* 266 */             Integer.valueOf(encoding), 
/* 267 */             Integer.valueOf(characterSet) });
/*     */     }
/* 269 */     Store store = getStore(h);
/* 270 */     int size = size(h);
/*     */     
/* 272 */     if (store.hasData() && ccsid(h) == characterSet) {
/*     */ 
/*     */       
/* 275 */       store.writeTo(output, getOffset(h), size);
/*     */     } else {
/* 277 */       output.write(Charsets.convert((String)getValue(h), characterSet));
/*     */       
/* 279 */       int padLength = size - stringLength(h);
/*     */       
/* 281 */       while (padLength-- > 0) {
/* 282 */         output.writeByte(store.getPadByte(characterSet));
/*     */       }
/*     */     } 
/*     */     
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "write(Header,DataOutput,int,int)", 
/* 288 */           Integer.valueOf(size));
/*     */     }
/* 290 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size(Header h) {
/* 298 */     if (Trace.isOn) {
/* 299 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "size(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 302 */     int traceRet1 = Math.max(stringLength(h), totalLength(h));
/*     */     
/* 304 */     if (Trace.isOn) {
/* 305 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "size(Header)", 
/* 306 */           Integer.valueOf(traceRet1));
/*     */     }
/* 308 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String type() {
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "type()");
/*     */     }
/* 319 */     if (Trace.isOn) {
/* 320 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "type()", "MQCHAR[]");
/*     */     }
/*     */     
/* 323 */     return "MQCHAR[]";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "toString()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 337 */     String traceRet1 = super.toString() + " (length reference: " + ((this.lengthRef != null) ? this.lengthRef.name() : "null") + ", totalLengthRef: " + ((this.totalLengthRef != null) ? this.totalLengthRef.name() : "null") + ", offset " + this.totalLengthOffset + ")";
/* 338 */     if (Trace.isOn) {
/* 339 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "toString()", traceRet1);
/*     */     }
/*     */     
/* 342 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected final int ccsid(Header h) {
/* 347 */     if (Trace.isOn) {
/* 348 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "ccsid(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 351 */     int ccsid = this.ccsidRef.getIntValue(h);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 356 */     if (ccsid == 0) {
/*     */       
/* 358 */       ccsid = h.store().characterSet();
/*     */     } else {
/*     */       
/* 361 */       ccsid = Store.correctEncoding(ccsid);
/*     */     } 
/*     */     
/* 364 */     if (ccsid == 0) {
/*     */ 
/*     */       
/* 367 */       ccsid = h.store().getHconnCharacterSetID();
/* 368 */       if (Trace.isOn) {
/* 369 */         Trace.data("com.ibm.mq.headers.internal.MQCharFieldVariableLength", "ccsid(Header)", "Falling back to HConn CCSID, value: ", 
/*     */ 
/*     */             
/* 372 */             Integer.valueOf(ccsid));
/*     */       }
/*     */     } 
/*     */     
/* 376 */     if (Trace.isOn) {
/* 377 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "ccsid(Header)", 
/* 378 */           Integer.valueOf(ccsid));
/*     */     }
/* 380 */     return ccsid;
/*     */   }
/*     */ 
/*     */   
/*     */   protected final int stringLength(Header h) {
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "stringLength(Header)", new Object[] { h });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 391 */     int traceRet1 = (this.lengthRef != null) ? Store.correctEncoding(this.lengthRef.getIntValue(h)) : totalLength(h);
/*     */     
/* 393 */     if (Trace.isOn) {
/* 394 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "stringLength(Header)", 
/* 395 */           Integer.valueOf(traceRet1));
/*     */     }
/* 397 */     return traceRet1;
/*     */   }
/*     */   
/*     */   protected final int totalLength(Header h) {
/* 401 */     if (Trace.isOn) {
/* 402 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "totalLength(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 405 */     int traceRet1 = Store.correctEncoding(this.totalLengthRef.getIntValue(h)) - this.totalLengthOffset;
/*     */     
/* 407 */     if (Trace.isOn) {
/* 408 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharFieldVariableLength", "totalLength(Header)", 
/* 409 */           Integer.valueOf(traceRet1));
/*     */     }
/* 411 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\MQCharFieldVariableLength.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */