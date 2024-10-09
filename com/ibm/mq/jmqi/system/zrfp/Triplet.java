/*     */ package com.ibm.mq.jmqi.system.zrfp;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.ArrayList;
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
/*     */ public class Triplet
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/Triplet.java";
/*     */   private final String label;
/*     */   private final ArrayList<String> key;
/*     */   private final ArrayList<Object> value;
/*     */   private final ArrayList<Integer> type;
/*     */   
/*     */   static {
/*  32 */     if (Trace.isOn) {
/*  33 */       Trace.data("com.ibm.mq.jmqi.system.zrfp.Triplet", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/zrfp/Triplet.java");
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
/*     */   
/*     */   public Triplet(String labelIn, int initialCapacity) {
/*  59 */     assert labelIn != null : "Label is null!";
/*  60 */     assert initialCapacity > 0 : "initialCapacity is invalid!";
/*  61 */     this.label = labelIn;
/*  62 */     this.key = new ArrayList<>(initialCapacity);
/*  63 */     this.value = new ArrayList(initialCapacity);
/*  64 */     this.type = new ArrayList<>(initialCapacity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(String keyIn, String valueIn) {
/*  75 */     assert this.key != null : "Key is null!";
/*  76 */     assert this.value != null : "Value is null!";
/*  77 */     this.key.add(keyIn);
/*  78 */     this.value.add(valueIn);
/*  79 */     this.type.add(Constants.TYPE_STRING);
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
/*     */   public void add(String keyIn, Object valueIn, Integer typeIn) {
/*  94 */     assert this.key != null : "Key is null!";
/*     */     
/*  96 */     this.key.add(keyIn);
/*  97 */     this.value.add(valueIn);
/*  98 */     this.type.add(typeIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getKey(int index) {
/* 109 */     String result = this.key.get(index);
/* 110 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue(int index) {
/* 121 */     Object result = this.value.get(index);
/* 122 */     return result;
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
/*     */   public int getType(int index) {
/* 136 */     Integer result = this.type.get(index);
/* 137 */     return result.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLabel() {
/* 145 */     return this.label;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 153 */     return this.key.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 161 */     return this.key.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.Triplet", "toString()");
/*     */     }
/*     */     
/* 175 */     int size = size();
/*     */     try {
/* 177 */       StringBuffer sb = new StringBuffer(256);
/* 178 */       for (int i = 0; i < size; i++) {
/* 179 */         sb.append(this.label);
/* 180 */         sb.append("@");
/* 181 */         sb.append(i);
/* 182 */         sb.append("=");
/* 183 */         sb.append("[");
/* 184 */         sb.append(this.key.get(i));
/* 185 */         sb.append(",");
/* 186 */         sb.append(this.value.get(i));
/* 187 */         sb.append(",");
/* 188 */         sb.append(this.type.get(i));
/* 189 */         if (i != size - 1) {
/* 190 */           sb.append("], ");
/*     */         } else {
/*     */           
/* 193 */           sb.append("]. ");
/*     */         } 
/*     */       } 
/* 196 */       String traceRet1 = sb.toString();
/* 197 */       if (Trace.isOn) {
/* 198 */         Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.Triplet", "toString()", traceRet1, 1);
/*     */       }
/* 200 */       return traceRet1;
/*     */     }
/* 202 */     catch (Exception e) {
/* 203 */       if (Trace.isOn) {
/* 204 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.system.zrfp.Triplet", "toString()", e);
/*     */       }
/*     */       
/* 207 */       String traceRet2 = "Triplet sized " + size;
/* 208 */       if (Trace.isOn) {
/* 209 */         Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.Triplet", "toString()", traceRet2, 2);
/*     */       }
/* 211 */       return traceRet2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object other) {
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.Triplet", "equals(Object)", new Object[] { other });
/*     */     }
/*     */     
/* 224 */     boolean traceRet1 = (other instanceof Triplet) ? equals((Triplet)other) : false;
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.Triplet", "equals(Object)", 
/* 227 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 229 */     return traceRet1;
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
/*     */   public boolean equals(Triplet other) {
/* 244 */     int thisSize = size();
/* 245 */     int otherSize = other.size();
/*     */ 
/*     */     
/* 248 */     if (thisSize != otherSize) {
/* 249 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 253 */     for (int i = 0; i < thisSize; i++) {
/*     */       
/* 255 */       int thisType = getType(i);
/* 256 */       int otherType = other.getType(i);
/* 257 */       if (thisType != otherType) {
/* 258 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 262 */       String thisKey = getKey(i);
/* 263 */       String otherKey = other.getKey(i);
/* 264 */       if (!thisKey.equals(otherKey)) {
/* 265 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 269 */       Object thisValue = getValue(i);
/* 270 */       Object otherValue = other.getValue(i);
/* 271 */       if (!thisValue.equals(otherValue)) {
/* 272 */         return false;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 277 */     return true;
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
/*     */   public static boolean tripletArraysEqual(Triplet[] first, Triplet[] second) {
/* 290 */     int firstLength = first.length;
/* 291 */     int secondLength = second.length;
/*     */ 
/*     */     
/* 294 */     if (firstLength != secondLength) {
/* 295 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 302 */     for (int i = 0; i < firstLength; i++) {
/* 303 */       Triplet a = first[i];
/* 304 */       Triplet b = second[i];
/* 305 */       boolean abEquals = a.equals(b);
/* 306 */       if (!abEquals) {
/* 307 */         return false;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 312 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 322 */     if (Trace.isOn) {
/* 323 */       Trace.entry(this, "com.ibm.mq.jmqi.system.zrfp.Triplet", "hashCode()");
/*     */     }
/* 325 */     assert false : "hashCode not designed";
/* 326 */     int traceRet1 = -1;
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.exit(this, "com.ibm.mq.jmqi.system.zrfp.Triplet", "hashCode()", 
/* 329 */           Integer.valueOf(traceRet1));
/*     */     }
/* 331 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\zrfp\Triplet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */