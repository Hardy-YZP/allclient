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
/*     */ public class MQLongArrayField
/*     */   extends MQLongField
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQLongArrayField.java";
/*     */   final MQLongField sizeRef;
/*     */   final MQLongField totalLengthRef;
/*     */   final int totalLengthOffset;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.headers.internal.MQLongArrayField", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQLongArrayField.java");
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
/*     */   public MQLongArrayField(int offset, String name, MQLongField sizeRef, MQLongField totalLengthRef) {
/*  58 */     super(offset, name);
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongArrayField", "<init>(int,String,MQLongField,MQLongField)", new Object[] {
/*  61 */             Integer.valueOf(offset), name, sizeRef, totalLengthRef
/*     */           });
/*     */     }
/*  64 */     this.sizeRef = sizeRef;
/*  65 */     this.totalLengthRef = totalLengthRef;
/*  66 */     this.totalLengthOffset = totalLengthRef.defaultValue.intValue();
/*     */     
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongArrayField", "<init>(int,String,MQLongField,MQLongField)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int arrayLength(Header h) {
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongArrayField", "arrayLength(Header)", new Object[] { h });
/*     */     }
/*     */     
/*  80 */     int traceRet1 = Store.correctEncoding(this.sizeRef.getIntValue(h));
/*     */     
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongArrayField", "arrayLength(Header)", 
/*  84 */           Integer.valueOf(traceRet1));
/*     */     }
/*  86 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String type() {
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongArrayField", "type()");
/*     */     }
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongArrayField", "type()", "MQLONG[]");
/*     */     }
/* 100 */     return "MQLONG[]";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(Header h) {
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongArrayField", "getValue(Header)", new Object[] { h });
/*     */     }
/*     */     
/*     */     try {
/* 114 */       Store store = getStore(h);
/* 115 */       int[] array = new int[arrayLength(h)];
/* 116 */       int offset = getOffset(h);
/*     */       
/* 118 */       for (int i = 0; i < array.length; i++) {
/* 119 */         array[i] = getIntValue(store, offset);
/* 120 */         offset += 4;
/*     */       } 
/*     */       
/* 123 */       if (Trace.isOn) {
/* 124 */         Trace.exit(this, "com.ibm.mq.headers.internal.MQLongArrayField", "getValue(Header)", array);
/*     */       }
/*     */       
/* 127 */       return array;
/*     */     
/*     */     }
/* 130 */     catch (IOException e) {
/* 131 */       if (Trace.isOn) {
/* 132 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQLongArrayField", "getValue(Header)", e);
/*     */       }
/*     */       
/* 135 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 136 */       if (Trace.isOn) {
/* 137 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQLongArrayField", "getValue(Header)", traceRet1);
/*     */       }
/*     */       
/* 140 */       throw traceRet1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(Header h, Object value) {
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongArrayField", "setValue(Header,Object)", new Object[] { h, value });
/*     */     }
/*     */     
/*     */     try {
/* 155 */       if (value == null) {
/* 156 */         this.sizeRef.setIntValue(h, 0);
/* 157 */         this.totalLengthRef.setIntValue(h, this.totalLengthOffset);
/* 158 */       } else if (value instanceof int[]) {
/* 159 */         int[] array = (int[])value;
/* 160 */         int offset = getOffset(h);
/* 161 */         Store store = getStore(h, offset, size(h), array.length * 4);
/*     */         
/* 163 */         for (int i = 0; i < array.length; i++) {
/* 164 */           setIntValue(store, array[i], offset);
/* 165 */           offset += 4;
/*     */         } 
/*     */         
/* 168 */         this.sizeRef.setIntValue(h, array.length);
/* 169 */         this.totalLengthRef.setIntValue(h, array.length * 4 + this.totalLengthOffset);
/*     */       } else {
/*     */         
/* 172 */         IllegalArgumentException traceRet1 = new IllegalArgumentException(HeaderMessages.getMessage("MQHDR0018"));
/*     */         
/* 174 */         if (Trace.isOn) {
/* 175 */           Trace.throwing(this, "com.ibm.mq.headers.internal.MQLongArrayField", "setValue(Header,Object)", traceRet1, 1);
/*     */         }
/*     */         
/* 178 */         throw traceRet1;
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 183 */     catch (IOException e) {
/* 184 */       if (Trace.isOn) {
/* 185 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQLongArrayField", "setValue(Header,Object)", e);
/*     */       }
/*     */       
/* 188 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 189 */       if (Trace.isOn) {
/* 190 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQLongArrayField", "setValue(Header,Object)", traceRet2, 2);
/*     */       }
/*     */       
/* 193 */       throw traceRet2;
/*     */     } 
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongArrayField", "setValue(Header,Object)");
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
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongArrayField", "write(Header,DataOutput,int,int)", new Object[] { h, output, 
/* 209 */             Integer.valueOf(encoding), 
/* 210 */             Integer.valueOf(characterSet) });
/*     */     }
/* 212 */     Store store = getStore(h);
/* 213 */     int size = size(h);
/*     */     
/* 215 */     if (store.hasData() && store.matchesEncoding(encoding)) {
/* 216 */       store.writeTo(output, getOffset(h), size);
/*     */     } else {
/* 218 */       int[] array = (int[])getValue(h);
/*     */       
/* 220 */       for (int i = 0; i < array.length; i++) {
/* 221 */         output.writeInt(array[i]);
/*     */       }
/*     */     } 
/*     */     
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongArrayField", "write(Header,DataOutput,int,int)", 
/* 227 */           Integer.valueOf(size));
/*     */     }
/* 229 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size(Header h) {
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQLongArrayField", "size(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 241 */     int traceRet1 = arrayLength(h) * 4;
/*     */     
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQLongArrayField", "size(Header)", 
/* 245 */           Integer.valueOf(traceRet1));
/*     */     }
/* 247 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\MQLongArrayField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */