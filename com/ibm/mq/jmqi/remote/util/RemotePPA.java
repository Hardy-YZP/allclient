/*     */ package com.ibm.mq.jmqi.remote.util;
/*     */ 
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemotePPA
/*     */ {
/*     */   private static final int kl = 8;
/*     */   private static final int bs = 8;
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jmqi.remote.util.RemotePPA", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/util/RemotePPA.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   private static byte[] yfx = new byte[] { -33, 9, 21, -124, -119, 123, 126, -42, -73, 50, -63, 23, -75, -8, -85, -72, -43, 65, -29, 27, -37, 84, -86, 98 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int finishWritingAuthFlow(byte[] buffer, int pwlo, int pwo, byte[] r, int ppa, JmqiDC dc, boolean swap) {
/* 102 */     int pwl = dc.readI32(buffer, pwlo, swap);
/*     */     
/* 104 */     if (ppa == 0)
/*     */     {
/* 106 */       return pwl;
/*     */     }
/*     */     
/* 109 */     if (pwl == 0)
/*     */     {
/* 111 */       return pwl;
/*     */     }
/*     */     
/* 114 */     if (ppa != 1) {
/* 115 */       return -1;
/*     */     }
/*     */     
/* 118 */     byte[] workingPw = qrs(buffer, pwo, pwl);
/*     */     
/* 120 */     tuv(r, workingPw);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     System.arraycopy(workingPw, 0, buffer, pwo, workingPw.length);
/*     */     
/* 132 */     return workingPw.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void tuv(byte[] r, byte[] w) {
/* 139 */     byte[] Ka = new byte[8];
/* 140 */     byte[] Kb = new byte[8];
/* 141 */     byte[] Kc = new byte[8];
/*     */     
/* 143 */     byte[] Ra = new byte[8];
/* 144 */     byte[] Rb = new byte[8];
/* 145 */     byte[] Rc = new byte[8];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     System.arraycopy(r, 0, Ra, 0, 8);
/* 155 */     System.arraycopy(r, 8, Rb, 0, 8);
/* 156 */     System.arraycopy(r, 16, Rc, 0, 8);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int Index;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     for (Index = 0; Index < 8; Index++) {
/*     */       
/* 169 */       Ka[Index] = (byte)(yfx[Index] ^ 0x8);
/*     */       
/* 171 */       if (Index == 0) {
/*     */         
/* 173 */         Ka[Index] = (byte)(Ka[Index] & 0xF0 | (Ka[Index] ^ Ra[Index]) & 0xF);
/*     */       }
/*     */       else {
/*     */         
/* 177 */         Ka[Index] = (byte)(Ka[Index] ^ Ra[Index]);
/*     */       } 
/*     */     } 
/*     */     
/* 181 */     for (Index = 0; Index < 8; Index++) {
/*     */       
/* 183 */       Kb[Index] = (byte)(yfx[8 + Index] ^ 0x8);
/*     */       
/* 185 */       if (Index == 0) {
/*     */         
/* 187 */         Kb[Index] = (byte)(Kb[Index] & 0xF0 | (Kb[Index] ^ Rb[Index]) & 0xF);
/*     */       }
/*     */       else {
/*     */         
/* 191 */         Kb[Index] = (byte)(Kb[Index] ^ Rb[Index]);
/*     */       } 
/*     */     } 
/*     */     
/* 195 */     for (Index = 0; Index < 8; Index++) {
/*     */       
/* 197 */       Kc[Index] = (byte)(yfx[16 + Index] ^ 0x8);
/*     */       
/* 199 */       if (Index == 0) {
/*     */         
/* 201 */         Kc[Index] = (byte)(Kc[Index] & 0xF0 | (Kc[Index] ^ Rc[Index]) & 0xF);
/*     */       }
/*     */       else {
/*     */         
/* 205 */         Kc[Index] = (byte)(Kc[Index] ^ Rc[Index]);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 220 */     int blockCount = w.length / 8;
/* 221 */     byte[] in = new byte[8];
/* 222 */     byte[] out = new byte[8];
/* 223 */     for (byte[] key : new byte[][] { Ka, Kb, Kc }) {
/* 224 */       for (int block = 0; block < blockCount; block++) {
/*     */         
/* 226 */         System.arraycopy(w, block * 8, in, 0, 8);
/*     */         
/* 228 */         Arrays.fill(out, (byte)0);
/* 229 */         hij(key, in, out);
/* 230 */         System.arraycopy(out, 0, w, block * 8, 8);
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
/*     */   private static byte[] qrs(byte[] buffer, int pwo, int pwl) {
/* 242 */     int pwLength = pwl;
/* 243 */     if (pwLength % 8 != 0) {
/* 244 */       pwLength += 8 - pwLength % 8;
/*     */     }
/*     */     
/* 247 */     byte[] paddedPw = new byte[pwLength];
/* 248 */     System.arraycopy(buffer, pwo, paddedPw, 0, pwl);
/*     */     
/* 250 */     return paddedPw;
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
/*     */   private static void hij(byte[] key, byte[] input, byte[] output) {
/* 266 */     byte[] eOutput = new byte[64];
/* 267 */     byte[] bitData = new byte[64];
/* 268 */     byte[][] ks = new byte[16][48];
/*     */     
/* 270 */     wxy(key, bitData);
/* 271 */     nop(bitData, ks);
/*     */     
/* 273 */     wxy(input, bitData);
/* 274 */     abc(bitData, eOutput, ks);
/* 275 */     cba(eOutput, output);
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
/*     */   private static void klm(byte[] key, byte[] input, byte[] output) {
/* 294 */     byte[] eOutput = new byte[64];
/* 295 */     byte[] bitData = new byte[64];
/* 296 */     byte[][] ks = new byte[16][48];
/*     */     
/* 298 */     wxy(key, bitData);
/* 299 */     nop(bitData, ks);
/*     */     
/* 301 */     wxy(input, bitData);
/* 302 */     efg(bitData, eOutput, ks);
/* 303 */     cba(eOutput, output);
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
/*     */   private static void wxy(byte[] byteData, byte[] bitData) {
/* 317 */     Arrays.fill(bitData, (byte)0);
/* 318 */     for (int i = 0; i < 8; i++) {
/* 319 */       byte currByte = byteData[i];
/* 320 */       for (int j = 0; j < 8; j++) {
/* 321 */         bitData[i * 8 + j] = (byte)(currByte >>> 7 - j & 0x1);
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
/*     */   private static void cba(byte[] bitData, byte[] byteData) {
/* 335 */     for (int i = 0; i < 8; i++) {
/* 336 */       int j = i * 8;
/* 337 */       byteData[i] = (byte)(bitData[j] << 7 | bitData[j + 1] << 6 | bitData[j + 2] << 5 | bitData[j + 3] << 4 | bitData[j + 4] << 3 | bitData[j + 5] << 2 | bitData[j + 6] << 1 | bitData[j + 7]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 346 */   private static byte[] PC_1C = new byte[] { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 352 */   private static byte[] PC_1D = new byte[] { 62, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 359 */   private static byte[] PC_2C = new byte[] { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 365 */   private static byte[] PC_2D = new byte[] { 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 371 */   private static byte[] Shifts = new byte[] { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void nop(byte[] BitKey, byte[][] ks) {
/* 382 */     byte[] C = new byte[28];
/* 383 */     byte[] D = new byte[28];
/*     */     
/*     */     int i;
/* 386 */     for (i = 0; i < 28; i++) {
/* 387 */       C[i] = BitKey[PC_1C[i] - 1];
/* 388 */       D[i] = BitKey[PC_1D[i] - 1];
/*     */     } 
/*     */     
/* 391 */     for (i = 0; i < 16; i++) {
/* 392 */       int j; for (j = 0; j < Shifts[i]; j++) {
/* 393 */         byte chc = C[0];
/* 394 */         byte chd = D[0];
/* 395 */         for (int k = 0; k < 27; k++) {
/* 396 */           C[k] = C[k + 1];
/* 397 */           D[k] = D[k + 1];
/*     */         } 
/* 399 */         C[27] = chc;
/* 400 */         D[27] = chd;
/*     */       } 
/*     */       
/* 403 */       for (j = 0; j < 24; j++) {
/* 404 */         ks[i][j] = C[PC_2C[j] - 1];
/* 405 */         ks[i][j + 24] = D[PC_2D[j] - 28 - 1];
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 412 */   private static byte[] E = new byte[] { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 423 */   static byte[] P = new byte[] { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 434 */   private static byte[][][] S = new byte[][][] { { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 }, { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 }, { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 }, { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } }, { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 }, { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 }, { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 }, { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } }, { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 }, { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 }, { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 }, { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } }, { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 }, { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 }, { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 }, { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } }, { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 }, { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 }, { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 }, { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } }, { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 }, { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 }, { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 }, { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } }, { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 }, { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 }, { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 }, { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } }, { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 }, { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 }, { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 }, { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] xcsF(byte[] R, byte[] K, byte[] retF) {
/* 485 */     byte[] InputS = new byte[48];
/* 486 */     byte[] OutputS = new byte[32];
/*     */     
/*     */     int i;
/*     */     
/* 490 */     for (i = 0; i < 48; i++) {
/* 491 */       InputS[i] = (byte)(R[E[i] - 1] ^ K[i]);
/*     */     }
/*     */     
/* 494 */     for (i = 0; i < 8; i++) {
/* 495 */       int j = i * 6;
/*     */       
/* 497 */       int I = InputS[j] * 2 + InputS[j + 5];
/* 498 */       int J = InputS[j + 1] * 8 + InputS[j + 2] * 4 + InputS[j + 3] * 2 + InputS[j + 4];
/*     */       
/* 500 */       int k = i * 4;
/* 501 */       OutputS[k] = (byte)(S[i][I][J] >>> 3 & 0x1);
/* 502 */       OutputS[k + 1] = (byte)(S[i][I][J] >>> 2 & 0x1);
/* 503 */       OutputS[k + 2] = (byte)(S[i][I][J] >>> 1 & 0x1);
/* 504 */       OutputS[k + 3] = (byte)(S[i][I][J] & 0x1);
/*     */     } 
/*     */ 
/*     */     
/* 508 */     for (i = 0; i < 32; i++) {
/* 509 */       retF[i] = OutputS[P[i] - 1];
/*     */     }
/* 511 */     return retF;
/*     */   }
/*     */ 
/*     */   
/* 515 */   private static byte[] IP = new byte[] { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 526 */   private static byte[] IP_1 = new byte[] { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void abc(byte[] input, byte[] output, byte[][] ks) {
/* 546 */     byte[] tmpbuf = new byte[64];
/* 547 */     byte[] L = new byte[32];
/* 548 */     byte[] R = new byte[32];
/*     */ 
/*     */     
/* 551 */     byte[] RetF = new byte[32];
/*     */     
/*     */     int i;
/* 554 */     for (i = 0; i < 64; i++) {
/* 555 */       tmpbuf[i] = input[IP[i] - 1];
/*     */     }
/*     */ 
/*     */     
/* 559 */     for (i = 0; i < 16; i++) {
/* 560 */       int j; for (j = 0; j < 32; j++) {
/* 561 */         L[j] = tmpbuf[j];
/* 562 */         R[j] = tmpbuf[j + 32];
/*     */       } 
/* 564 */       byte[] fptr = xcsF(R, ks[i], RetF);
/* 565 */       int f = 0;
/* 566 */       if (i < 15) {
/* 567 */         for (j = 0; j < 32; j++) {
/* 568 */           tmpbuf[j] = R[j];
/* 569 */           tmpbuf[j + 32] = (byte)(L[j] ^ fptr[f++]);
/*     */         } 
/*     */       } else {
/* 572 */         for (j = 0; j < 32; j++) {
/* 573 */           tmpbuf[j] = (byte)(L[j] ^ fptr[f++]);
/* 574 */           tmpbuf[j + 32] = R[j];
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 580 */     for (i = 0; i < 64; i++) {
/* 581 */       output[i] = tmpbuf[IP_1[i] - 1];
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
/*     */   private static void efg(byte[] input, byte[] output, byte[][] ks) {
/* 595 */     byte[] tmpbuf = new byte[64];
/* 596 */     byte[] L = new byte[32];
/* 597 */     byte[] R = new byte[32];
/*     */ 
/*     */     
/* 600 */     byte[] RetF = new byte[32];
/*     */     
/*     */     int i;
/* 603 */     for (i = 0; i < 64; i++) {
/* 604 */       tmpbuf[i] = input[IP[i] - 1];
/*     */     }
/*     */     int j;
/* 607 */     for (j = 0; j < 32; j++) {
/* 608 */       L[j] = tmpbuf[j + 32];
/* 609 */       R[j] = tmpbuf[j];
/*     */     } 
/*     */     
/* 612 */     for (i = 15; i >= 0; i--) {
/* 613 */       byte[] fptr = xcsF(L, ks[i], RetF);
/* 614 */       int f = 0;
/* 615 */       for (j = 0; j < 32; j++) {
/* 616 */         tmpbuf[j] = (byte)(R[j] ^ fptr[f++]);
/* 617 */         tmpbuf[j + 32] = L[j];
/*     */       } 
/* 619 */       for (j = 0; j < 32; j++) {
/* 620 */         L[j] = tmpbuf[j];
/* 621 */         R[j] = tmpbuf[j + 32];
/*     */       } 
/* 623 */       for (j = 0; j < 32; j++) {
/* 624 */         tmpbuf[j] = L[j];
/* 625 */         tmpbuf[j + 32] = R[j];
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 630 */     for (i = 0; i < 64; i++)
/* 631 */       output[i] = tmpbuf[IP_1[i] - 1]; 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remot\\util\RemotePPA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */