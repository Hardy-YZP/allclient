/*     */ package com.ibm.mq.headers.internal;
/*     */ 
/*     */ import com.ibm.mq.headers.internal.store.Store;
/*     */ import com.ibm.mq.headers.internal.validator.FieldValidator;
/*     */ import com.ibm.mq.internal.MQCommonServices;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
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
/*     */ 
/*     */ 
/*     */ public abstract class HeaderField
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/HeaderField.java";
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.headers.internal.HeaderField", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/HeaderField.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class DummyHeader
/*     */     extends Header
/*     */   {
/*     */     DummyHeader(HeaderType type) {
/*  57 */       super(type);
/*     */     }
/*     */   }
/*     */   
/*  61 */   static final Header dummyHeader = new DummyHeader(new HeaderType("DUMMY"));
/*     */ 
/*     */   
/*     */   final String name;
/*     */   
/*     */   private final int offset;
/*     */   
/*     */   private OptionRule optionRule;
/*     */   
/*     */   private int index;
/*     */   
/*     */   protected FieldValidator validator;
/*     */ 
/*     */   
/*     */   protected HeaderField(int offset, String name) {
/*  76 */     this(offset, name, OptionRule.DEFAULT);
/*  77 */     if (Trace.isOn)
/*  78 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderField", "<init>(int,String)", new Object[] {
/*  79 */             Integer.valueOf(offset), name
/*     */           }); 
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderField", "<init>(int,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected HeaderField(int offset, String name, OptionRule optionRule) {
/*  92 */     super(MQCommonServices.jmqiEnv);
/*  93 */     if (Trace.isOn)
/*  94 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderField", "<init>(int,String,OptionRule)", new Object[] {
/*  95 */             Integer.valueOf(offset), name, optionRule
/*     */           }); 
/*  97 */     this.offset = offset;
/*  98 */     this.name = name;
/*  99 */     this.optionRule = optionRule;
/*     */     
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderField", "<init>(int,String,OptionRule)");
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
/*     */   public final int getOffset(Header h) {
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderField", "getOffset(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 119 */     if (this.offset < 0) {
/* 120 */       HeaderType type = h.headerType();
/*     */       
/* 122 */       int offset = type.getFixedSize();
/* 123 */       int limit = type.getFieldCount();
/*     */       
/* 125 */       for (int i = type.getFixedSizeFieldCount(); i < limit; i++) {
/* 126 */         offset += type.getField(i).size(h);
/*     */       }
/*     */       
/* 129 */       if (Trace.isOn) {
/* 130 */         Trace.exit(this, "com.ibm.mq.headers.internal.HeaderField", "getOffset(Header)", 
/* 131 */             Integer.valueOf(offset), 1);
/*     */       }
/* 133 */       return offset;
/*     */     } 
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderField", "getOffset(Header)", 
/* 137 */           Integer.valueOf(this.offset), 2);
/*     */     }
/* 139 */     return this.offset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Store getStore(Header h) throws IOException {
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderField", "getStore(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 153 */     Store traceRet1 = h.store();
/*     */     
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderField", "getStore(Header)", traceRet1);
/*     */     }
/* 158 */     return traceRet1;
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
/*     */   protected final Store getStore(Header h, int offset, int oldSize, int newSize) throws IOException {
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderField", "getStore(Header,int,int,int)", new Object[] { h, 
/* 175 */             Integer.valueOf(offset), Integer.valueOf(oldSize), 
/* 176 */             Integer.valueOf(newSize) });
/*     */     }
/* 178 */     Store traceRet1 = h.store(offset, oldSize, newSize);
/*     */     
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderField", "getStore(Header,int,int,int)", traceRet1);
/*     */     }
/*     */     
/* 184 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setOptionRule(OptionRule optionRule) {
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.data(this, "com.ibm.mq.headers.internal.HeaderField", "setOptionRule(OptionRule)", "setter", optionRule);
/*     */     }
/*     */     
/* 196 */     this.optionRule = optionRule;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setIndex(int index) {
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.data(this, "com.ibm.mq.headers.internal.HeaderField", "setIndex(int)", "setter", 
/* 206 */           Integer.valueOf(index));
/*     */     }
/* 208 */     this.index = index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getIndex() {
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.data(this, "com.ibm.mq.headers.internal.HeaderField", "getIndex()", "getter", 
/* 218 */           Integer.valueOf(this.index));
/*     */     }
/* 220 */     return this.index;
/*     */   }
/*     */   
/*     */   FieldValidator getValidator() {
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.data(this, "com.ibm.mq.headers.internal.HeaderField", "getValidator()", "getter", this.validator);
/*     */     }
/*     */     
/* 228 */     return this.validator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String name() {
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderField", "name()");
/*     */     }
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderField", "name()", this.name);
/*     */     }
/* 241 */     return this.name;
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
/*     */   public final boolean isPresent(Header h) {
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderField", "isPresent(Header)", new Object[] { h });
/*     */     }
/*     */     
/* 282 */     boolean traceRet1 = this.optionRule.isPresent(h);
/*     */     
/* 284 */     if (Trace.isOn) {
/* 285 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderField", "isPresent(Header)", 
/* 286 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 288 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setPresent(Header h) {
/* 299 */     if (Trace.isOn) {
/* 300 */       Trace.data(this, "com.ibm.mq.headers.internal.HeaderField", "setPresent(Header)", "setter", h);
/*     */     }
/*     */     
/* 303 */     this.optionRule.setPresent(h);
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
/*     */   public int read(Header h, DataInput input, int encoding, int characterSet) throws IOException {
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderField", "read(Header,DataInput,int,int)", new Object[] { h, input, 
/* 320 */             Integer.valueOf(encoding), 
/* 321 */             Integer.valueOf(characterSet) });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 326 */     int offset = getOffset(h);
/* 327 */     int size = size(h);
/*     */     
/* 329 */     getStore(h, offset, size, size).readFrom(input, offset, size);
/*     */     
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderField", "read(Header,DataInput,int,int)", 
/* 333 */           Integer.valueOf(size));
/*     */     }
/* 335 */     return size;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int getPaddedLength(int length, int alignment) {
/* 364 */     if (Trace.isOn)
/* 365 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderField", "getPaddedLength(int,int)", new Object[] {
/* 366 */             Integer.valueOf(length), Integer.valueOf(alignment)
/*     */           }); 
/* 368 */     int pad = alignment - length % alignment;
/*     */     
/* 370 */     int traceRet1 = length + ((pad == alignment) ? 0 : pad);
/*     */     
/* 372 */     if (Trace.isOn) {
/* 373 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderField", "getPaddedLength(int,int)", 
/* 374 */           Integer.valueOf(traceRet1));
/*     */     }
/* 376 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 384 */     if (Trace.isOn) {
/* 385 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderField", "toString()");
/*     */     }
/* 387 */     String traceRet1 = getClass().getName() + " " + this.name + "@" + this.offset;
/* 388 */     if (Trace.isOn) {
/* 389 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderField", "toString()", traceRet1);
/*     */     }
/* 391 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public abstract String type();
/*     */   
/*     */   public abstract Object getValue(Header paramHeader);
/*     */   
/*     */   public abstract void setValue(Header paramHeader, Object paramObject);
/*     */   
/*     */   public abstract Object defaultValue();
/*     */   
/*     */   public abstract int write(Header paramHeader, DataOutput paramDataOutput, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public abstract int size(Header paramHeader);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\HeaderField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */