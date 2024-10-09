/*     */ package com.ibm.mq.headers.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
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
/*     */ public class ReflectingStore
/*     */   extends DefaultStore
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/ReflectingStore.java";
/*     */   private final Header header;
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.headers.internal.ReflectingStore", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/ReflectingStore.java");
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
/*     */   public ReflectingStore(Header header) {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReflectingStore", "<init>(Header)", new Object[] { header });
/*     */     }
/*     */     
/*  64 */     this.header = header;
/*     */     
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.exit(this, "com.ibm.mq.headers.internal.ReflectingStore", "<init>(Header)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(HeaderField field, int offset) throws IOException {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReflectingStore", "getInt(HeaderField,int)", new Object[] { field, 
/*  79 */             Integer.valueOf(offset) });
/*     */     }
/*  81 */     Object value = getValue(field);
/*  82 */     if (value instanceof Integer) {
/*  83 */       int traceRet1 = ((Integer)getValue(field)).intValue();
/*     */       
/*  85 */       if (Trace.isOn) {
/*  86 */         Trace.exit(this, "com.ibm.mq.headers.internal.ReflectingStore", "getInt(HeaderField,int)", 
/*  87 */             Integer.valueOf(traceRet1), 1);
/*     */       }
/*  89 */       return traceRet1;
/*  90 */     }  if (value instanceof int[]) {
/*  91 */       int[] array = (int[])value;
/*  92 */       int origin = field.getOffset(this.header);
/*  93 */       int index = (offset <= origin) ? 0 : (offset - origin >> 2);
/*     */       
/*  95 */       if (Trace.isOn) {
/*  96 */         Trace.exit(this, "com.ibm.mq.headers.internal.ReflectingStore", "getInt(HeaderField,int)", 
/*  97 */             Integer.valueOf(array[index]), 2);
/*     */       }
/*  99 */       return array[index];
/*     */     } 
/* 101 */     ClassCastException traceRet2 = new ClassCastException(HeaderMessages.getMessage("MQHDR0028", new Object[] { field
/* 102 */             .name(), value.getClass() }));
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.throwing(this, "com.ibm.mq.headers.internal.ReflectingStore", "getInt(HeaderField,int)", traceRet2);
/*     */     }
/*     */     
/* 107 */     throw traceRet2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLong(HeaderField field, int offset) throws IOException {
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReflectingStore", "getLong(HeaderField,int)", new Object[] { field, 
/* 119 */             Integer.valueOf(offset) });
/*     */     }
/* 121 */     Object value = getValue(field);
/*     */     
/* 123 */     if (value instanceof Long) {
/* 124 */       long traceRet1 = ((Long)getValue(field)).longValue();
/*     */       
/* 126 */       if (Trace.isOn) {
/* 127 */         Trace.exit(this, "com.ibm.mq.headers.internal.ReflectingStore", "getLong(HeaderField,int)", 
/* 128 */             Long.valueOf(traceRet1), 1);
/*     */       }
/* 130 */       return traceRet1;
/* 131 */     }  if (value instanceof long[]) {
/* 132 */       long[] array = (long[])value;
/* 133 */       int origin = field.getOffset(this.header);
/* 134 */       int index = (offset <= origin) ? 0 : (offset - origin >> 3);
/*     */       
/* 136 */       if (Trace.isOn) {
/* 137 */         Trace.exit(this, "com.ibm.mq.headers.internal.ReflectingStore", "getLong(HeaderField,int)", 
/* 138 */             Long.valueOf(array[index]), 2);
/*     */       }
/* 140 */       return array[index];
/*     */     } 
/* 142 */     ClassCastException traceRet2 = new ClassCastException(HeaderMessages.getMessage("MQHDR0026", new Object[] { field
/* 143 */             .name(), value.getClass() }));
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.throwing(this, "com.ibm.mq.headers.internal.ReflectingStore", "getLong(HeaderField,int)", traceRet2);
/*     */     }
/*     */     
/* 148 */     throw traceRet2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(HeaderField field, int offset, int length, int ccsid) throws IOException {
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReflectingStore", "getString(HeaderField,int,int,int)", new Object[] { field, 
/* 160 */             Integer.valueOf(offset), 
/* 161 */             Integer.valueOf(length), Integer.valueOf(ccsid) });
/*     */     }
/* 163 */     Object value = getValue(field);
/*     */     
/* 165 */     if (value == null) {
/*     */       
/* 167 */       if (Trace.isOn) {
/* 168 */         Trace.exit(this, "com.ibm.mq.headers.internal.ReflectingStore", "getString(HeaderField,int,int,int)", "", 1);
/*     */       }
/*     */       
/* 171 */       return "";
/* 172 */     }  if (value instanceof String) {
/* 173 */       String traceRet1 = (String)getValue(field);
/* 174 */       if (Trace.isOn) {
/* 175 */         Trace.exit(this, "com.ibm.mq.headers.internal.ReflectingStore", "getString(HeaderField,int,int,int)", traceRet1, 2);
/*     */       }
/*     */       
/* 178 */       return traceRet1;
/* 179 */     }  if (value instanceof String[]) {
/* 180 */       String[] array = (String[])value;
/* 181 */       int origin = field.getOffset(this.header);
/* 182 */       int index = (offset <= origin) ? 0 : ((offset - origin) / length);
/*     */       
/* 184 */       if (Trace.isOn) {
/* 185 */         Trace.exit(this, "com.ibm.mq.headers.internal.ReflectingStore", "getString(HeaderField,int,int,int)", array[index], 3);
/*     */       }
/*     */       
/* 188 */       return array[index];
/*     */     } 
/*     */     
/* 191 */     ClassCastException traceRet2 = new ClassCastException("Value of field " + field.name() + " is " + value.getClass() + " (expected String or String[])");
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.throwing(this, "com.ibm.mq.headers.internal.ReflectingStore", "getString(HeaderField,int,int,int)", traceRet2);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 198 */     throw traceRet2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getStrings(HeaderField field, int offset, int length, int count, int ccsid) throws IOException {
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReflectingStore", "getStrings(HeaderField,int,int,int,int)", new Object[] { field, 
/* 211 */             Integer.valueOf(offset), 
/* 212 */             Integer.valueOf(length), Integer.valueOf(count), Integer.valueOf(ccsid) });
/*     */     }
/* 214 */     Object value = getValue(field);
/*     */     
/* 216 */     if (value == null) {
/* 217 */       String[] traceRet1 = new String[0];
/*     */       
/* 219 */       if (Trace.isOn) {
/* 220 */         Trace.exit(this, "com.ibm.mq.headers.internal.ReflectingStore", "getStrings(HeaderField,int,int,int,int)", traceRet1, 1);
/*     */       }
/*     */       
/* 223 */       return traceRet1;
/* 224 */     }  if (value instanceof String[]) {
/* 225 */       String[] traceRet2 = (String[])value;
/* 226 */       if (Trace.isOn) {
/* 227 */         Trace.exit(this, "com.ibm.mq.headers.internal.ReflectingStore", "getStrings(HeaderField,int,int,int,int)", traceRet2, 2);
/*     */       }
/*     */       
/* 230 */       return traceRet2;
/*     */     } 
/*     */     
/* 233 */     ClassCastException traceRet3 = new ClassCastException("Value of field " + field.name() + " is " + value.getClass() + " (expected String[])");
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.throwing(this, "com.ibm.mq.headers.internal.ReflectingStore", "getStrings(HeaderField,int,int,int,int)", traceRet3);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 240 */     throw traceRet3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBytes(HeaderField field, int offset, int length) throws IOException {
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReflectingStore", "getBytes(HeaderField,int,int)", new Object[] { field, 
/* 251 */             Integer.valueOf(offset), 
/* 252 */             Integer.valueOf(length) });
/*     */     }
/* 254 */     Object value = getValue(field);
/*     */     
/* 256 */     if (value == null) {
/* 257 */       byte[] traceRet1 = new byte[0];
/*     */       
/* 259 */       if (Trace.isOn) {
/* 260 */         Trace.exit(this, "com.ibm.mq.headers.internal.ReflectingStore", "getBytes(HeaderField,int,int)", traceRet1, 1);
/*     */       }
/*     */       
/* 263 */       return traceRet1;
/* 264 */     }  if (value instanceof byte[]) {
/* 265 */       byte[] traceRet2 = (byte[])value;
/* 266 */       if (Trace.isOn) {
/* 267 */         Trace.exit(this, "com.ibm.mq.headers.internal.ReflectingStore", "getBytes(HeaderField,int,int)", traceRet2, 2);
/*     */       }
/*     */       
/* 270 */       return traceRet2;
/*     */     } 
/*     */     
/* 273 */     ClassCastException traceRet3 = new ClassCastException("Value of field " + field.name() + " is " + value.getClass() + " (expected byte[])");
/* 274 */     if (Trace.isOn) {
/* 275 */       Trace.throwing(this, "com.ibm.mq.headers.internal.ReflectingStore", "getBytes(HeaderField,int,int)", traceRet3);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 280 */     throw traceRet3;
/*     */   }
/*     */ 
/*     */   
/*     */   private Object getValue(HeaderField headerField) {
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.entry(this, "com.ibm.mq.headers.internal.ReflectingStore", "getValue(HeaderField)", new Object[] { headerField });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 291 */       String fieldName = Character.toLowerCase(headerField.name().charAt(0)) + headerField.name().substring(1);
/* 292 */       Field member = this.header.getClass().getField(fieldName);
/*     */       
/* 294 */       if (Modifier.isStatic(member.getModifiers())) {
/* 295 */         Object traceRet1 = member.get(this.header.getClass());
/*     */         
/* 297 */         if (Trace.isOn) {
/* 298 */           Trace.exit(this, "com.ibm.mq.headers.internal.ReflectingStore", "getValue(HeaderField)", traceRet1, 1);
/*     */         }
/*     */         
/* 301 */         return traceRet1;
/*     */       } 
/*     */       
/* 304 */       Object traceRet2 = member.get(this.header);
/* 305 */       if (Trace.isOn) {
/* 306 */         Trace.exit(this, "com.ibm.mq.headers.internal.ReflectingStore", "getValue(HeaderField)", traceRet2, 2);
/*     */       }
/*     */       
/* 309 */       return traceRet2;
/*     */     
/*     */     }
/* 312 */     catch (Exception e) {
/* 313 */       if (Trace.isOn) {
/* 314 */         Trace.catchBlock(this, "com.ibm.mq.headers.internal.ReflectingStore", "getValue(HeaderField)", e);
/*     */       }
/*     */       
/* 317 */       if (Trace.isOn) {
/* 318 */         Trace.exit(this, "com.ibm.mq.headers.internal.ReflectingStore", "getValue(HeaderField)", null, 3);
/*     */       }
/*     */       
/* 321 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\ReflectingStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */