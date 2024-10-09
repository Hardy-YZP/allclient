/*     */ package com.ibm.mq.headers.internal;
/*     */ 
/*     */ import com.ibm.mq.headers.Charsets;
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
/*     */ public class MQCharArrayField
/*     */   extends MQCharFieldVariableLength
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQCharArrayField.java";
/*     */   final MQLongField sizeRef;
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.headers.internal.MQCharArrayField", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/MQCharArrayField.java");
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
/*     */   public MQCharArrayField(int offset, String name, MQLongField sizeRef, MQLongField lengthRef, MQLongField totalLengthRef) {
/*  61 */     this(offset, name, sizeRef, lengthRef, totalLengthRef, (MQLongField)null);
/*  62 */     if (Trace.isOn)
/*  63 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharArrayField", "<init>(int,String,MQLongField,MQLongField,MQLongField)", new Object[] {
/*     */             
/*  65 */             Integer.valueOf(offset), name, sizeRef, lengthRef, totalLengthRef
/*     */           }); 
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharArrayField", "<init>(int,String,MQLongField,MQLongField,MQLongField)");
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
/*     */   public MQCharArrayField(int offset, String name, MQLongField sizeRef, MQLongField lengthRef, MQLongField totalLengthRef, MQLongField ccsidRef) {
/*  89 */     super(offset, name, lengthRef, totalLengthRef, ccsidRef);
/*  90 */     if (Trace.isOn)
/*  91 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharArrayField", "<init>(int,String,MQLongField,MQLongField,MQLongField,MQLongField)", new Object[] {
/*     */             
/*  93 */             Integer.valueOf(offset), name, sizeRef, lengthRef, totalLengthRef, ccsidRef
/*     */           }); 
/*  95 */     this.sizeRef = sizeRef;
/*     */     
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharArrayField", "<init>(int,String,MQLongField,MQLongField,MQLongField,MQLongField)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int arrayLength(Header h) {
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharArrayField", "arrayLength(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 109 */     int traceRet1 = Store.correctEncoding(this.sizeRef.getIntValue(h));
/*     */     
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharArrayField", "arrayLength(Header)", 
/* 113 */           Integer.valueOf(traceRet1));
/*     */     }
/* 115 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(Header h) {
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharArrayField", "getValue(Header)", new Object[] { h });
/*     */     }
/*     */     
/*     */     try {
/* 128 */       int length = stringLength(h);
/* 129 */       int offset = getOffset(h);
/* 130 */       Store store = getStore(h);
/* 131 */       int ccsid = ccsid(h);
/*     */       
/* 133 */       Object traceRet1 = store.getStrings(this, offset, arrayLength(h), length, ccsid);
/*     */       
/* 135 */       if (Trace.isOn) {
/* 136 */         Trace.exit(this, "com.ibm.mq.headers.internal.MQCharArrayField", "getValue(Header)", traceRet1);
/*     */       }
/*     */       
/* 139 */       return traceRet1;
/*     */     
/*     */     }
/* 142 */     catch (IOException e) {
/* 143 */       if (Trace.isOn) {
/* 144 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQCharArrayField", "getValue(Header)", e);
/*     */       }
/*     */       
/* 147 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 148 */       if (Trace.isOn) {
/* 149 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQCharArrayField", "getValue(Header)", traceRet2);
/*     */       }
/*     */       
/* 152 */       throw traceRet2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(Header h, Object value) {
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharArrayField", "setValue(Header,Object)", new Object[] { h, value });
/*     */     }
/*     */     
/*     */     try {
/* 166 */       if (value == null) {
/*     */ 
/*     */         
/* 169 */         this.lengthRef.setValue(h, null);
/* 170 */         this.sizeRef.setValue(h, null);
/* 171 */         this.totalLengthRef.setValue(h, null);
/* 172 */       } else if (value instanceof String[]) {
/* 173 */         String[] array = (String[])value;
/* 174 */         int offset = getOffset(h);
/* 175 */         int size = size(h);
/* 176 */         int ccsid = ccsid(h);
/* 177 */         Store store = getStore(h, offset, size, size);
/* 178 */         int stringLength = store.setStrings(offset, array, 0, ccsid);
/*     */         
/* 180 */         this.lengthRef.setIntValue(h, stringLength);
/* 181 */         this.sizeRef.setIntValue(h, array.length);
/* 182 */         this.totalLengthRef.setIntValue(h, getPaddedLength(array.length * stringLength, 4) + this.totalLengthOffset);
/*     */       } else {
/*     */         
/* 185 */         IllegalArgumentException traceRet1 = new IllegalArgumentException("Value must be an instance of String []");
/*     */ 
/*     */         
/* 188 */         if (Trace.isOn) {
/* 189 */           Trace.throwing(this, "com.ibm.mq.headers.internal.MQCharArrayField", "setValue(Header,Object)", traceRet1, 1);
/*     */         }
/*     */         
/* 192 */         throw traceRet1;
/*     */       }
/*     */     
/*     */     }
/* 196 */     catch (IOException e) {
/* 197 */       if (Trace.isOn) {
/* 198 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.MQCharArrayField", "setValue(Header,Object)", e);
/*     */       }
/*     */       
/* 201 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 202 */       if (Trace.isOn) {
/* 203 */         Trace.throwing(this, "com.ibm.mq.headers.internal.MQCharArrayField", "setValue(Header,Object)", traceRet2, 2);
/*     */       }
/*     */       
/* 206 */       throw traceRet2;
/*     */     } 
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharArrayField", "setValue(Header,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int write(Header h, DataOutput output, int encoding, int characterSet) throws IOException {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharArrayField", "write(Header,DataOutput,int,int)", new Object[] { h, output, 
/* 221 */             Integer.valueOf(encoding), 
/* 222 */             Integer.valueOf(characterSet) });
/*     */     }
/* 224 */     Store store = getStore(h);
/* 225 */     int size = size(h);
/*     */     
/* 227 */     if (store.hasData() && store.characterSet() == characterSet) {
/*     */ 
/*     */       
/* 230 */       store.writeTo(output, getOffset(h), size);
/*     */     } else {
/* 232 */       String[] array = (String[])getValue(h);
/*     */       
/* 234 */       for (int i = 0; i < array.length; i++) {
/* 235 */         output.write(Charsets.convert(array[i], characterSet));
/*     */       }
/*     */       
/* 238 */       int padLength = size - arrayLength(h) * stringLength(h);
/*     */       
/* 240 */       while (padLength-- > 0) {
/* 241 */         output.writeByte(store.getPadByte(characterSet));
/*     */       }
/*     */     } 
/*     */     
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharArrayField", "write(Header,DataOutput,int,int)", 
/* 247 */           Integer.valueOf(size));
/*     */     }
/* 249 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size(Header h) {
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.entry(this, "com.ibm.mq.headers.internal.MQCharArrayField", "size(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 261 */     int traceRet1 = Math.max(getPaddedLength(arrayLength(h) * stringLength(h), 4), 
/* 262 */         totalLength(h));
/*     */     
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.exit(this, "com.ibm.mq.headers.internal.MQCharArrayField", "size(Header)", 
/* 266 */           Integer.valueOf(traceRet1));
/*     */     }
/* 268 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\MQCharArrayField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */