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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class LikeOperator
/*     */   extends Operator
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public String pattern;
/*     */   public char escape;
/*     */   public boolean escaped = false;
/*     */   
/*     */   public LikeOperator(Selector operand, String pattern, String escape) {
/*  65 */     super(4, operand);
/*  66 */     this.pattern = pattern.substring(1, pattern.length() - 1);
/*  67 */     if (escape != null) {
/*  68 */       if (escape.length() != 3) {
/*  69 */         this.type = 2;
/*     */       } else {
/*  71 */         this.escape = escape.charAt(1);
/*  72 */         this.escaped = true;
/*     */       } 
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
/*     */   public LikeOperator(Selector operand, String pattern, boolean escaped, char escape) {
/*  89 */     super(4, operand);
/*  90 */     this.pattern = pattern;
/*  91 */     this.escaped = escaped;
/*  92 */     this.escape = escape;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   LikeOperator(byte[] buf, int offset) throws UTFDataFormatException {
/*  99 */     super(buf, offset);
/* 100 */     offset += 1 + this.operands[0].length();
/* 101 */     int len = ArrayUtil.readShort(buf, offset);
/* 102 */     char[] chars = new char[len];
/* 103 */     offset += 2 + len;
/* 104 */     len = UTF.UTFToChars(buf, offset - len, chars, 0, len);
/* 105 */     this.pattern = new String(chars, 0, len);
/* 106 */     this.escaped = (buf[offset] != 0);
/* 107 */     if (this.escaped) {
/* 108 */       this.escape = (char)ArrayUtil.readShort(buf, offset + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int length() {
/* 115 */     int len = super.length();
/* 116 */     char[] temp = this.pattern.toCharArray();
/* 117 */     len += UTF.lengthUTF(temp, 0, temp.length) + 3;
/* 118 */     if (this.escaped)
/* 119 */       len += 2; 
/* 120 */     return len;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int encode(byte[] buf, int offset) {
/* 127 */     super.encode(buf, offset);
/* 128 */     int start = offset;
/* 129 */     offset += super.length();
/* 130 */     char[] temp = this.pattern.toCharArray();
/* 131 */     int len = UTF.lengthUTF(temp, 0, temp.length);
/* 132 */     ArrayUtil.writeShort(buf, offset, (short)len);
/* 133 */     UTF.charsToUTF(temp, 0, buf, offset + 2, temp.length);
/* 134 */     offset += 2 + len;
/* 135 */     buf[offset++] = (byte)(this.escaped ? 1 : 0);
/* 136 */     if (this.escaped) {
/* 137 */       ArrayUtil.writeShort(buf, offset, (short)this.escape);
/* 138 */       offset += 2;
/*     */     } 
/* 140 */     return offset - start;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 147 */     if (o instanceof LikeOperator) {
/* 148 */       LikeOperator other = (LikeOperator)o;
/* 149 */       return (super.equals(o) && this.pattern.equals(other.pattern) && this.escaped == other.escaped && this.escape == other.escape);
/*     */     } 
/*     */ 
/*     */     
/* 153 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 157 */     return super.hashCode() + this.pattern.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 162 */     return this.operands[0] + " LIKE '" + this.pattern + "'" + (this.escaped ? (" ESCAPE '" + this.escape + "'") : "");
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\LikeOperator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */