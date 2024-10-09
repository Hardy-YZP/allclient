/*     */ package com.ibm.disthub2.impl.util;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.disthub2.spi.ExceptionConstants;
/*     */ import com.ibm.disthub2.spi.LogConstants;
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
/*     */ public final class UTF
/*     */   implements LogConstants, ExceptionConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  74 */   private static final DebugObject debug = new DebugObject("UTF");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int UTFToChars(byte[] source, int fromBegin, char[] target, int toBegin, int numBytes) throws UTFDataFormatException {
/* 101 */     if (debug.debugIt(32)) {
/* 102 */       debug.debug(-165922073994779L, "UTFToChars", source, Integer.valueOf(fromBegin), target, Integer.valueOf(toBegin), Integer.valueOf(numBytes));
/*     */     }
/*     */ 
/*     */     
/* 106 */     int limit = fromBegin + numBytes;
/* 107 */     while (fromBegin < limit) {
/* 108 */       byte c = source[fromBegin++];
/* 109 */       if (c > 0) {
/* 110 */         target[toBegin++] = (char)c; continue;
/*     */       } 
/* 112 */       if ((c & 0xE0) == 192 && fromBegin < limit) {
/* 113 */         byte char2 = source[fromBegin++];
/* 114 */         if ((char2 & 0xC0) != 128) {
/* 115 */           throw new UTFDataFormatException(ExceptionBuilder.buildReasonString(357505012, null));
/*     */         }
/* 117 */         target[toBegin++] = (char)((c & 0x1F) << 6 | char2 & 0x3F); continue;
/*     */       } 
/* 119 */       if ((c & 0xF0) == 224 && fromBegin + 1 < limit) {
/* 120 */         byte char2 = source[fromBegin++];
/* 121 */         byte char3 = source[fromBegin++];
/* 122 */         if ((char2 & 0xC0) != 128 || (char3 & 0xC0) != 128) {
/* 123 */           throw new UTFDataFormatException(ExceptionBuilder.buildReasonString(357505012, null));
/*     */         }
/* 125 */         target[toBegin++] = (char)((c & 0xF) << 12 | (char2 & 0x3F) << 6 | (char3 & 0x3F) << 0);
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 130 */       throw new UTFDataFormatException(ExceptionBuilder.buildReasonString(357505012, null));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 135 */     if (debug.debugIt(64)) {
/* 136 */       debug.debug(-142394261359015L, "UTFToChars", Integer.valueOf(toBegin));
/*     */     }
/*     */ 
/*     */     
/* 140 */     return toBegin;
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
/*     */   public static void charsToUTF(char[] source, int fromBegin, byte[] target, int toBegin, int numChars) {
/* 165 */     int limit = fromBegin + numChars;
/* 166 */     while (fromBegin < limit) {
/* 167 */       int c = source[fromBegin++];
/* 168 */       if (c >= 1 && c <= 127) {
/* 169 */         target[toBegin++] = (byte)c; continue;
/*     */       } 
/* 171 */       if (c > 2047) {
/* 172 */         target[toBegin++] = (byte)(0xE0 | c >> 12 & 0xF);
/* 173 */         target[toBegin++] = (byte)(0x80 | c >> 6 & 0x3F);
/* 174 */         target[toBegin++] = (byte)(0x80 | c >> 0 & 0x3F);
/*     */         continue;
/*     */       } 
/* 177 */       target[toBegin++] = (byte)(0xC0 | c >> 6 & 0x1F);
/* 178 */       target[toBegin++] = (byte)(0x80 | c >> 0 & 0x3F);
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
/*     */   
/*     */   public static int lengthUTF(char[] source, int fromBegin, int numChars) {
/* 205 */     int limit = fromBegin + numChars;
/* 206 */     int ans = 0;
/* 207 */     while (fromBegin < limit) {
/* 208 */       int c = source[fromBegin++];
/* 209 */       if (c >= 1 && c <= 127) {
/* 210 */         ans++; continue;
/*     */       } 
/* 212 */       if (c > 2047) {
/* 213 */         ans += 3;
/*     */         continue;
/*     */       } 
/* 216 */       ans += 2;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 225 */     return ans;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\UTF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */