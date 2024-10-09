/*     */ package com.ibm.disthub2.impl.matching.selector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class NumericValue
/*     */   extends Number
/*     */   implements Comparable
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   private long longVal;
/*     */   private double doubleVal;
/*     */   private static final int BYTE = -2;
/*     */   private static final int SHORT = -1;
/*     */   private static final int INT = 0;
/*     */   private static final int LONG = 1;
/*     */   private static final int FLOAT = 2;
/*     */   private static final int DOUBLE = 3;
/*     */   private int realType;
/*     */   int type;
/*     */   
/*     */   public NumericValue(byte val) {
/*  77 */     this.realType = -2;
/*  78 */     this.type = 0;
/*  79 */     this.longVal = val;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericValue(short val) {
/*  85 */     this.realType = -1;
/*  86 */     this.type = 0;
/*  87 */     this.longVal = val;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericValue(int val) {
/*  93 */     this.realType = this.type = 0;
/*  94 */     this.longVal = val;
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
/*     */   public NumericValue(long val, boolean mustBeLong) {
/* 107 */     this.longVal = val;
/* 108 */     this.type = (mustBeLong || val < -2147483648L || val > 2147483647L) ? 1 : 0;
/* 109 */     this.realType = this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericValue(float val) {
/* 115 */     this.realType = this.type = 2;
/* 116 */     this.doubleVal = val;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericValue(double val) {
/* 122 */     this.realType = this.type = 3;
/* 123 */     this.doubleVal = val;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericValue(Number val) {
/* 129 */     if (val instanceof Integer) {
/* 130 */       this.realType = this.type = 0;
/* 131 */       this.longVal = val.longValue();
/*     */     }
/* 133 */     else if (val instanceof Long) {
/* 134 */       this.realType = this.type = 1;
/* 135 */       this.longVal = val.longValue();
/*     */     }
/* 137 */     else if (val instanceof Short) {
/* 138 */       this.realType = -1;
/* 139 */       this.type = 0;
/* 140 */       this.longVal = val.longValue();
/*     */     }
/* 142 */     else if (val instanceof Byte) {
/* 143 */       this.realType = -2;
/* 144 */       this.type = 0;
/* 145 */       this.longVal = val.longValue();
/*     */     }
/* 147 */     else if (val instanceof Double) {
/* 148 */       this.realType = this.type = 3;
/* 149 */       this.doubleVal = val.doubleValue();
/*     */     }
/* 151 */     else if (val instanceof Float) {
/* 152 */       this.realType = this.type = 2;
/* 153 */       this.doubleVal = val.doubleValue();
/*     */     } else {
/*     */       
/* 156 */       throw new IllegalArgumentException();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte byteValue() {
/* 163 */     if (this.type < 2) {
/* 164 */       return (byte)(int)this.longVal;
/*     */     }
/*     */     
/* 167 */     return (byte)(int)this.doubleVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short shortValue() {
/* 174 */     if (this.type < 2) {
/* 175 */       return (short)(int)this.longVal;
/*     */     }
/*     */     
/* 178 */     return (short)(int)this.doubleVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int intValue() {
/* 185 */     if (this.type < 2) {
/* 186 */       return (int)this.longVal;
/*     */     }
/*     */     
/* 189 */     return (int)this.doubleVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long longValue() {
/* 196 */     if (this.type < 2) {
/* 197 */       return this.longVal;
/*     */     }
/*     */     
/* 200 */     return (long)this.doubleVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float floatValue() {
/* 207 */     if (this.type < 2) {
/* 208 */       return (float)this.longVal;
/*     */     }
/*     */     
/* 211 */     return (float)this.doubleVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double doubleValue() {
/* 218 */     if (this.type < 2) {
/* 219 */       return this.longVal;
/*     */     }
/*     */     
/* 222 */     return this.doubleVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Number toStandardWrapper() {
/* 229 */     switch (this.realType) {
/*     */       case -2:
/* 231 */         return Byte.valueOf((byte)(int)this.longVal);
/*     */       case -1:
/* 233 */         return Short.valueOf((short)(int)this.longVal);
/*     */       case 0:
/* 235 */         return Integer.valueOf((int)this.longVal);
/*     */       case 1:
/* 237 */         return Long.valueOf(this.longVal);
/*     */       case 2:
/* 239 */         return Float.valueOf((float)this.doubleVal);
/*     */       case 3:
/* 241 */         return Double.valueOf(this.doubleVal);
/*     */     } 
/* 243 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int promote(NumericValue other) {
/* 250 */     return (this.type >= other.type) ? this.type : other.type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericValue neg() {
/* 256 */     switch (this.type) {
/*     */       case 0:
/* 258 */         return new NumericValue(-intValue());
/*     */       case 1:
/* 260 */         return new NumericValue(-longValue(), true);
/*     */       case 2:
/* 262 */         return new NumericValue(-floatValue());
/*     */       case 3:
/* 264 */         return new NumericValue(-doubleValue());
/*     */     } 
/* 266 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericValue plus(NumericValue other) {
/* 273 */     switch (promote(other)) {
/*     */       case 0:
/* 275 */         return new NumericValue(intValue() + other.intValue());
/*     */       case 1:
/* 277 */         return new NumericValue(longValue() + other.longValue(), true);
/*     */       case 2:
/* 279 */         return new NumericValue(floatValue() + other.floatValue());
/*     */       case 3:
/* 281 */         return new NumericValue(doubleValue() + other.doubleValue());
/*     */     } 
/* 283 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericValue times(NumericValue other) {
/* 290 */     switch (promote(other)) {
/*     */       case 0:
/* 292 */         return new NumericValue(intValue() * other.intValue());
/*     */       case 1:
/* 294 */         return new NumericValue(longValue() * other.longValue(), true);
/*     */       case 2:
/* 296 */         return new NumericValue(floatValue() * other.floatValue());
/*     */       case 3:
/* 298 */         return new NumericValue(doubleValue() * other.doubleValue());
/*     */     } 
/* 300 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericValue minus(NumericValue other) {
/* 307 */     switch (promote(other)) {
/*     */       case 0:
/* 309 */         return new NumericValue(intValue() - other.intValue());
/*     */       case 1:
/* 311 */         return new NumericValue(longValue() - other.longValue(), true);
/*     */       case 2:
/* 313 */         return new NumericValue(floatValue() - other.floatValue());
/*     */       case 3:
/* 315 */         return new NumericValue(doubleValue() - other.doubleValue());
/*     */     } 
/* 317 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericValue div(NumericValue other) {
/* 324 */     switch (promote(other)) {
/*     */       case 0:
/* 326 */         return new NumericValue(intValue() / other.intValue());
/*     */       case 1:
/* 328 */         return new NumericValue(longValue() / other.longValue(), true);
/*     */       case 2:
/* 330 */         return new NumericValue(floatValue() / other.floatValue());
/*     */       case 3:
/* 332 */         return new NumericValue(doubleValue() / other.doubleValue());
/*     */     } 
/* 334 */     throw new IllegalStateException();
/*     */   }
/*     */   public int compareTo(Object o) {
/*     */     int li, ri;
/*     */     long ll, rl;
/*     */     float lf, rf;
/*     */     double ld, rd;
/* 341 */     NumericValue other = (NumericValue)o;
/* 342 */     switch (promote(other)) {
/*     */       case 0:
/* 344 */         li = intValue();
/* 345 */         ri = other.intValue();
/* 346 */         return (li < ri) ? -1 : ((li == ri) ? 0 : 1);
/*     */       case 1:
/* 348 */         ll = longValue();
/* 349 */         rl = other.longValue();
/* 350 */         return (ll < rl) ? -1 : ((ll == rl) ? 0 : 1);
/*     */       case 2:
/* 352 */         lf = floatValue();
/* 353 */         rf = other.floatValue();
/* 354 */         return (lf < rf) ? -1 : ((lf == rf) ? 0 : 1);
/*     */       case 3:
/* 356 */         ld = doubleValue();
/* 357 */         rd = other.doubleValue();
/* 358 */         return (ld < rd) ? -1 : ((ld == rd) ? 0 : 1);
/*     */     } 
/* 360 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 367 */     if (o instanceof NumericValue) {
/* 368 */       return (compareTo(o) == 0);
/*     */     }
/*     */     
/* 371 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 376 */     long basis = this.longVal;
/* 377 */     if (this.type > 1) {
/* 378 */       basis = (long)this.doubleVal;
/* 379 */       if (this.doubleVal != basis) {
/* 380 */         basis = Double.doubleToLongBits(this.doubleVal);
/*     */       }
/*     */     } 
/* 383 */     return (int)(basis ^ basis >> 32L);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 387 */     switch (this.type) {
/*     */       case 0:
/* 389 */         return String.valueOf((int)this.longVal);
/*     */       case 1:
/* 391 */         return String.valueOf(this.longVal) + "L";
/*     */       case 2:
/* 393 */         return String.valueOf((float)this.doubleVal) + "f";
/*     */       case 3:
/* 395 */         return String.valueOf(this.doubleVal);
/*     */     } 
/* 397 */     throw new IllegalStateException();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\NumericValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */