/*     */ package com.ibm.disthub2.impl.util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CryptoHash
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   
/*     */   public static long hash(byte[] data) {
/*  47 */     int[] code = calculate(data);
/*  48 */     return code[0] << 32L | code[1] & 0xFFFFFFFFL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void hash(byte[] data, byte[] output) {
/*  59 */     int len = output.length;
/*  60 */     if (len == 0)
/*     */       return; 
/*  62 */     int[] code = calculate(data);
/*  63 */     int outIndex = 0, codeIndex = 0, shift = 24;
/*  64 */     for (; outIndex < len && codeIndex < 5; 
/*  65 */       outIndex++, shift -= 8) {
/*  66 */       output[outIndex] = (byte)(code[codeIndex] >>> shift);
/*  67 */       if (shift == 0) {
/*  68 */         shift = 32;
/*  69 */         codeIndex++;
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
/*     */   private static int[] calculate(byte[] data) {
/*  83 */     int A = 1732584193;
/*  84 */     int B = -271733879;
/*  85 */     int C = -1732584194;
/*  86 */     int D = 271733878;
/*  87 */     int E = -1009589776;
/*     */     
/*  89 */     int[] W = new int[80];
/*  90 */     int len = data.length;
/*  91 */     int off = 0;
/*  92 */     int n = len / 4;
/*     */     
/*  94 */     boolean done = false;
/*  95 */     boolean padded = false;
/*     */     
/*  97 */     while (!done) {
/*     */       int i;
/*  99 */       for (i = 0; i < 16 && n > 0; n--, off += 4) {
/* 100 */         W[i++] = data[off + 3] & 0xFF | (data[off + 2] & 0xFF) << 8 | (data[off + 1] & 0xFF) << 16 | (data[off] & 0xFF) << 24;
/*     */       }
/*     */       
/* 103 */       if (i < 16) {
/* 104 */         if (!padded) {
/* 105 */           int X = len % 4;
/* 106 */           int pad1 = 0;
/* 107 */           for (int j = 0; j < X; j++) {
/* 108 */             pad1 |= (data[off + j] & 0xFF) << 24 - 8 * j;
/*     */           }
/* 110 */           W[i++] = pad1 | 1 << 31 - X * 8;
/*     */           
/* 112 */           if (i == 15) W[15] = 0; 
/* 113 */           padded = true;
/*     */         } 
/* 115 */         if (i <= 14) {
/* 116 */           for (; i < 14; W[i++] = 0);
/*     */           
/* 118 */           W[14] = len >>> 29;
/* 119 */           W[15] = len << 3;
/* 120 */           done = true;
/*     */         } 
/* 122 */         i = 16;
/*     */       } 
/*     */       do {
/*     */         int X;
/* 126 */         W[i] = (X = W[i - 3] ^ W[i - 8] ^ W[i - 14] ^ W[i - 16]) << 1 | X >>> 31;
/* 127 */       } while (++i < 80);
/*     */ 
/*     */       
/* 130 */       int A0 = A;
/* 131 */       int B0 = B;
/* 132 */       int C0 = C;
/* 133 */       int D0 = D;
/* 134 */       int E0 = E;
/* 135 */       i = 0; 
/* 136 */       do { int j = (A << 5 | A >>> 27) + (B & C | (B ^ 0xFFFFFFFF) & D) + E + W[i] + 1518500249;
/* 137 */         E = D; D = C; C = B << 30 | B >>> 2; B = A; A = j; } while (++i < 20); 
/* 138 */       do { int j = (A << 5 | A >>> 27) + (B ^ C ^ D) + E + W[i] + 1859775393;
/* 139 */         E = D; D = C; C = B << 30 | B >>> 2; B = A; A = j; } while (++i < 40); 
/* 140 */       do { int j = (A << 5 | A >>> 27) + (B & C | B & D | C & D) + E + W[i] + -1894007588;
/* 141 */         E = D; D = C; C = B << 30 | B >>> 2; B = A; A = j; } while (++i < 60); while (true) {
/* 142 */         int j = (A << 5 | A >>> 27) + (B ^ C ^ D) + E + W[i] + -899497514;
/* 143 */         E = D; D = C; C = B << 30 | B >>> 2; B = A; A = j; if (++i >= 80) {
/* 144 */           A += A0;
/* 145 */           B += B0;
/* 146 */           C += C0;
/* 147 */           D += D0;
/* 148 */           E += E0;
/*     */         } 
/*     */       } 
/* 151 */     }  int[] result = { A, B, C, D, E };
/* 152 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\CryptoHash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */