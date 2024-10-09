/*     */ package com.ibm.disthub2.impl.util;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Hex
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  41 */   private static final DebugObject debug = new DebugObject("Hex");
/*     */ 
/*     */   
/*  44 */   private static final char[] digits = "0123456789ABCDEF".toCharArray();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toString(int[] frame) {
/*  53 */     return toString(frame, 0, frame.length);
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
/*     */   public static String toString(int[] frame, int offset, int length) {
/*  66 */     byte[] temp = new byte[length * 4];
/*     */     
/*  68 */     for (int j = 0, i = j; i < length; i++, j += 4) {
/*  69 */       temp[j] = (byte)(frame[i] >>> 24);
/*  70 */       temp[j + 1] = (byte)(frame[i] >>> 16);
/*  71 */       temp[j + 2] = (byte)(frame[i] >>> 8);
/*  72 */       temp[j + 3] = (byte)frame[i];
/*     */     } 
/*  74 */     return toString(temp, 0, length * 4);
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
/*     */   public static String toString(byte[] frame, int offset, int length) {
/*  87 */     if (frame == null) return null; 
/*  88 */     StringBuffer buf = new StringBuffer();
/*  89 */     int limit = offset + length;
/*  90 */     while (offset < limit) {
/*  91 */       buf.append(digits[frame[offset] >>> 4 & 0xF]);
/*  92 */       buf.append(digits[frame[offset++] & 0xF]);
/*     */     } 
/*  94 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toString(byte[] array) {
/* 101 */     return toString(array, 0, array.length);
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
/*     */   public static String dumpString(byte[] frame, int offset, int length) {
/* 113 */     return dumpString(frame, offset, length, false);
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
/*     */   public static String dumpString(byte[] frame, int offset, int len, boolean ascii) {
/* 128 */     if (frame == null || len == 0) return null;
/*     */ 
/*     */     
/* 131 */     StringBuffer buf = new StringBuffer();
/* 132 */     StringBuffer asciibuf = new StringBuffer();
/* 133 */     buf.append("Length=").append(len);
/* 134 */     for (int i = 0; i < len; i++) {
/* 135 */       if (i % 32 == 0) {
/* 136 */         if (ascii) {
/* 137 */           buf.append(asciibuf);
/* 138 */           asciibuf.setLength(0);
/* 139 */           asciibuf.append("\n").append(pad(0)).append("   ");
/*     */         } 
/* 141 */         buf.append("\n").append(pad(offset + i)).append(offset + i).append(": ");
/*     */       }
/* 143 */       else if (i % 16 == 0) {
/* 144 */         buf.append("  ");
/* 145 */         if (ascii) asciibuf.append("  ");
/*     */       
/* 147 */       } else if (i % 4 == 0) {
/* 148 */         buf.append(" ");
/* 149 */         if (ascii) asciibuf.append(" "); 
/*     */       } 
/* 151 */       buf.append(digits[frame[offset + i] >>> 4 & 0xF]);
/* 152 */       buf.append(digits[frame[offset + i] & 0xF]);
/* 153 */       if (ascii)
/* 154 */         if (frame[offset + i] >= 32 && frame[offset + i] < Byte.MAX_VALUE) {
/* 155 */           asciibuf.append(' ').append((char)frame[offset + i]);
/*     */         } else {
/* 157 */           asciibuf.append(" .");
/*     */         }  
/*     */     } 
/* 160 */     if (ascii) buf.append(asciibuf); 
/* 161 */     return buf.toString();
/*     */   }
/*     */   
/*     */   static String pad(int i) {
/* 165 */     if (i > 999)
/* 166 */       return ""; 
/* 167 */     if (i > 99)
/* 168 */       return " "; 
/* 169 */     if (i > 9) {
/* 170 */       return "  ";
/*     */     }
/* 172 */     return "   ";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\Hex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */