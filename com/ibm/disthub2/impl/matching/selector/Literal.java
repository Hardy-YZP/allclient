/*     */ package com.ibm.disthub2.impl.matching.selector;
/*     */ 
/*     */ import com.ibm.disthub2.impl.util.ArrayUtil;
/*     */ import com.ibm.disthub2.impl.util.UTF;
/*     */ import java.io.UTFDataFormatException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Literal
/*     */   extends Selector
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public Object value;
/*     */   
/*     */   static int objectType(Object value) {
/*  53 */     if (value instanceof String)
/*  54 */       return -5; 
/*  55 */     if (value instanceof BooleanValue)
/*  56 */       return -6; 
/*  57 */     if (value instanceof NumericValue)
/*     */     {
/*     */       
/*  60 */       return ((NumericValue)value).type + -4;
/*     */     }
/*  62 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Literal(Object value) {
/*  72 */     this.value = value;
/*  73 */     this.type = objectType(value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   Literal(byte[] buf, int offset) throws UTFDataFormatException {
/*     */     int len;
/*     */     char[] chars;
/*  81 */     this.type = buf[offset];
/*  82 */     switch (this.type) {
/*     */       case -2:
/*  84 */         this.value = new NumericValue(Float.intBitsToFloat(ArrayUtil.readInt(buf, offset + 1)));
/*     */         return;
/*     */       case -1:
/*  87 */         this.value = new NumericValue(Double.longBitsToDouble(ArrayUtil.readLong(buf, offset + 1)));
/*     */         return;
/*     */       case -4:
/*  90 */         this.value = new NumericValue(ArrayUtil.readInt(buf, offset + 1));
/*     */         return;
/*     */       case -3:
/*  93 */         this.value = new NumericValue(ArrayUtil.readLong(buf, offset + 1), true);
/*     */         return;
/*     */       case -5:
/*  96 */         len = ArrayUtil.readShort(buf, offset + 1);
/*  97 */         chars = new char[len];
/*  98 */         len = UTF.UTFToChars(buf, offset + 3, chars, 0, len);
/*  99 */         this.value = new String(chars, 0, len);
/*     */         return;
/*     */       case -6:
/* 102 */         this.value = BooleanValue.valueOf((buf[offset + 1] != 0));
/*     */         return;
/*     */     } 
/* 105 */     this.type = 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int length() {
/*     */     char[] temp;
/* 116 */     switch (this.type) {
/*     */       case -5:
/* 118 */         temp = ((String)this.value).toCharArray();
/* 119 */         return UTF.lengthUTF(temp, 0, temp.length) + 3;
/*     */       case -3:
/*     */       case -1:
/* 122 */         return 9;
/*     */       case -6:
/* 124 */         return 2;
/*     */     } 
/*     */     
/* 127 */     return 5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int encode(byte[] buf, int offset) {
/*     */     char[] temp;
/*     */     int len;
/* 135 */     buf[offset] = (byte)this.type;
/* 136 */     switch (this.type) {
/*     */       case -5:
/* 138 */         temp = ((String)this.value).toCharArray();
/* 139 */         len = UTF.lengthUTF(temp, 0, temp.length);
/* 140 */         ArrayUtil.writeShort(buf, offset + 1, (short)len);
/* 141 */         UTF.charsToUTF(temp, 0, buf, offset + 3, temp.length);
/* 142 */         return len + 3;
/*     */       case -3:
/* 144 */         ArrayUtil.writeLong(buf, offset + 1, ((NumericValue)this.value).longValue());
/* 145 */         return 9;
/*     */       case -1:
/* 147 */         ArrayUtil.writeLong(buf, offset + 1, 
/* 148 */             Double.doubleToLongBits(((NumericValue)this.value).doubleValue()));
/* 149 */         return 9;
/*     */       case -4:
/* 151 */         ArrayUtil.writeInt(buf, offset + 1, ((NumericValue)this.value).intValue());
/* 152 */         return 5;
/*     */       case -2:
/* 154 */         ArrayUtil.writeInt(buf, offset + 1, 
/* 155 */             Float.floatToIntBits(((NumericValue)this.value).floatValue()));
/* 156 */         return 5;
/*     */       case -6:
/* 158 */         buf[offset + 1] = (byte)(((BooleanValue)this.value).booleanValue() ? 1 : 0);
/* 159 */         return 2;
/*     */     } 
/* 161 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 169 */     if (o instanceof Literal && super.equals(o)) {
/* 170 */       return ((Literal)o).value.equals(this.value);
/*     */     }
/* 172 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 176 */     return this.value.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 180 */     if (this.type == -5) {
/*     */       
/* 182 */       String result = (String)this.value;
/* 183 */       if (result.indexOf("'") > 0) {
/*     */ 
/*     */         
/* 186 */         for (int i = 0; i < result.length(); i++) {
/*     */           
/* 188 */           if (result.charAt(i) == '\'') {
/*     */             
/* 190 */             result = result.substring(0, i + 1) + '\'' + result.substring(i + 1);
/* 191 */             i++;
/*     */           } 
/*     */         } 
/* 194 */         return "'" + result + "'";
/*     */       } 
/*     */ 
/*     */       
/* 198 */       return "'" + this.value + "'";
/*     */     } 
/*     */ 
/*     */     
/* 202 */     return this.value.toString();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\Literal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */