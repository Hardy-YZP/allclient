/*     */ package com.ibm.mq.headers.internal;
/*     */ 
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.internal.store.Store;
/*     */ import com.ibm.mq.headers.internal.validator.FieldValidator;
/*     */ import com.ibm.mq.headers.internal.validator.MQHeaderValidationException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ public class MQByteFieldVariableLength
/*     */   extends MQByteField
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQByteFieldVariableLength.java";
/*     */   static final int ALIGNMENT = 4;
/*     */   final MQLongField lengthRef;
/*     */   final MQLongField totalLengthRef;
/*     */   final int totalLengthOffset;
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.headers.internal.MQByteFieldVariableLength", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQByteFieldVariableLength.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQByteFieldVariableLength(int offset, String name, MQLongField lengthRef, MQLongField totalLengthRef) {
/*  66 */     super(offset, name, 0);
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "<init>(int,String,MQLongField,MQLongField)", new Object[] {
/*  69 */             Integer.valueOf(offset), name, lengthRef, totalLengthRef
/*     */           });
/*     */     }
/*  72 */     if (totalLengthRef == null) {
/*     */       
/*  74 */       IllegalArgumentException traceRet1 = new IllegalArgumentException(HeaderMessages.getMessage("MQHDR0015"));
/*     */       
/*  76 */       if (Trace.isOn) {
/*  77 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "<init>(int,String,MQLongField,MQLongField)", traceRet1);
/*     */       }
/*     */       
/*  80 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/*  84 */     this.lengthRef = lengthRef;
/*  85 */     this.totalLengthRef = totalLengthRef;
/*  86 */     this.totalLengthOffset = totalLengthRef.defaultValue.intValue();
/*  87 */     this.validator = new FieldValidator()
/*     */       {
/*     */         public void validate(Header h) throws MQDataException, IOException
/*     */         {
/*  91 */           if (Trace.isOn) {
/*  92 */             Trace.entry(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "validate(Header)", new Object[] { h });
/*     */           }
/*     */           
/*  95 */           if (MQByteFieldVariableLength.this.totalLength(h) < 0) {
/*     */             
/*  97 */             MQHeaderValidationException traceRet1 = new MQHeaderValidationException(HeaderMessages.getMessage("MQHDR0016", new Object[] { h.type(), Integer.valueOf(this.this$0.totalLength(h)), this.this$0.totalLengthRef.name }));
/*     */ 
/*     */             
/* 100 */             if (Trace.isOn) {
/* 101 */               Trace.throwing(this, "com.ibm.mq.headers.internal.null", "validate(Header)", (Throwable)traceRet1);
/*     */             }
/*     */             
/* 104 */             throw traceRet1;
/*     */           } 
/*     */           
/* 107 */           if (Trace.isOn) {
/* 108 */             Trace.exit(this, "com.ibm.mq.headers.internal.null", "validate(Header)");
/*     */           }
/*     */         }
/*     */       };
/*     */     
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "<init>(int,String,MQLongField,MQLongField)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int bytesLength(Header h) {
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "bytesLength(Header)", new Object[] { h });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 127 */     int traceRet1 = (this.lengthRef != null) ? Store.correctEncoding(this.lengthRef.getIntValue(h)) : totalLength(h);
/*     */     
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "bytesLength(Header)", 
/* 131 */           Integer.valueOf(traceRet1));
/*     */     }
/* 133 */     return traceRet1;
/*     */   }
/*     */   
/*     */   protected final int totalLength(Header h) {
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "totalLength(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 141 */     int traceRet1 = Store.correctEncoding(this.totalLengthRef.getIntValue(h)) - this.totalLengthOffset;
/*     */     
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "totalLength(Header)", 
/* 145 */           Integer.valueOf(traceRet1));
/*     */     }
/* 147 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(Header h) {
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "getValue(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 159 */     Object traceRet1 = getBytesValue(h, getOffset(h), bytesLength(h));
/*     */     
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "getValue(Header)", traceRet1);
/*     */     }
/*     */     
/* 165 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(Header h, Object value) {
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "setValue(Header,Object)", new Object[] { h, value });
/*     */     }
/*     */     
/*     */     try {
/* 179 */       byte[] bytes = (byte[])value;
/* 180 */       int length = (bytes == null) ? 0 : bytes.length;
/* 181 */       int newSize = getPaddedLength(length, 4);
/* 182 */       int offset = getOffset(h);
/* 183 */       Store store = getStore(h, offset, size(h), newSize);
/*     */       
/* 185 */       setBytesValue(store, bytes, offset, newSize);
/*     */       
/* 187 */       if (this.lengthRef != null) {
/* 188 */         this.lengthRef.setIntValue(h, length);
/*     */       }
/*     */       
/* 191 */       this.totalLengthRef.setIntValue(h, newSize + this.totalLengthOffset);
/*     */     
/*     */     }
/* 194 */     catch (ClassCastException e) {
/* 195 */       if (Trace.isOn) {
/* 196 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "setValue(Header,Object)", e, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 201 */       IllegalArgumentException traceRet1 = new IllegalArgumentException(HeaderMessages.getMessage("MQHDR0017"));
/* 202 */       if (Trace.isOn) {
/* 203 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "setValue(Header,Object)", traceRet1, 1);
/*     */       }
/*     */       
/* 206 */       throw traceRet1;
/*     */ 
/*     */     
/*     */     }
/* 210 */     catch (IOException e) {
/* 211 */       if (Trace.isOn) {
/* 212 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "setValue(Header,Object)", e, 2);
/*     */       }
/*     */       
/* 215 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 216 */       if (Trace.isOn) {
/* 217 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "setValue(Header,Object)", traceRet2, 2);
/*     */       }
/*     */       
/* 220 */       throw traceRet2;
/*     */     } 
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "setValue(Header,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size(Header h) {
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "size(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 238 */     int traceRet1 = Store.correctEncoding(this.totalLengthRef.getIntValue(h)) - this.totalLengthOffset;
/*     */     
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "size(Header)", 
/* 242 */           Integer.valueOf(traceRet1));
/*     */     }
/* 244 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String type() {
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "type()");
/*     */     }
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "type()", "MQBYTE[]");
/*     */     }
/*     */     
/* 259 */     return "MQBYTE[]";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "toString()");
/*     */     }
/*     */ 
/*     */     
/* 273 */     String traceRet1 = super.toString() + " (length reference: " + ((this.lengthRef != null) ? this.lengthRef.name() : "null") + ", offset " + this.totalLengthOffset + ")";
/*     */     
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQByteFieldVariableLength", "toString()", traceRet1);
/*     */     }
/*     */     
/* 279 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\MQByteFieldVariableLength.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */