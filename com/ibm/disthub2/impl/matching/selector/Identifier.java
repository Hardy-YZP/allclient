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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Identifier
/*     */   extends Selector
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public String name;
/*     */   public ValueAccessor accessor;
/*     */   public int ordinalPosition;
/*     */   public long schemaId;
/*     */   public Identifier caseOf;
/*     */   
/*     */   public Identifier(String name) {
/*  89 */     this.name = name;
/*  90 */     this.type = 0;
/*  91 */     this.numIds = 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Identifier(byte[] buf, int offset) throws UTFDataFormatException {
/* 100 */     int len = ArrayUtil.readShort(buf, offset + 1);
/* 101 */     char[] chars = new char[len];
/* 102 */     len = UTF.UTFToChars(buf, offset + 3, chars, 0, len);
/* 103 */     this.name = new String(chars, 0, len);
/* 104 */     this.type = 0;
/* 105 */     this.numIds = 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int length() {
/* 115 */     char[] temp = this.name.toCharArray();
/* 116 */     return UTF.lengthUTF(temp, 0, temp.length) + 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int encode(byte[] buf, int offset) {
/* 125 */     buf[offset] = 0;
/* 126 */     char[] temp = this.name.toCharArray();
/* 127 */     int len = UTF.lengthUTF(temp, 0, temp.length);
/* 128 */     ArrayUtil.writeShort(buf, offset + 1, (short)len);
/* 129 */     UTF.charsToUTF(temp, 0, buf, offset + 3, temp.length);
/* 130 */     return len + 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 137 */     if (o instanceof Identifier && super.equals(o)) {
/* 138 */       return (((Identifier)o).name.equals(this.name) && ((Identifier)o).schemaId == this.schemaId);
/*     */     }
/* 140 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 144 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 148 */     return this.name;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\matching\selector\Identifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */