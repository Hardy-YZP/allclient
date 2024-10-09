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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArrayUtil
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   private static final int ZEROES_LEN = 500;
/*  66 */   private static byte[] zeroes = new byte[500];
/*     */   
/*  68 */   private static final DebugObject debug = new DebugObject("ArrayUtil");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void clear(byte[] buf) {
/*  74 */     clear(buf, 0, buf.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void clear(byte[] buf, int offset, int length) {
/*  81 */     if (length <= 500) {
/*  82 */       System.arraycopy(zeroes, 0, buf, offset, length);
/*     */     } else {
/*  84 */       System.arraycopy(zeroes, 0, buf, offset, 500);
/*  85 */       int halflength = length / 2; int i;
/*  86 */       for (i = 500; i < length; i += i) {
/*  87 */         System.arraycopy(buf, offset, buf, offset + i, (i <= halflength) ? i : (length - i));
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
/*     */   
/*     */   public static long readLong(byte[] b, int offset) {
/* 105 */     long retValue = b[offset++] << 56L;
/* 106 */     retValue |= (b[offset++] & 0xFFL) << 48L;
/* 107 */     retValue |= (b[offset++] & 0xFFL) << 40L;
/* 108 */     retValue |= (b[offset++] & 0xFFL) << 32L;
/* 109 */     retValue |= (b[offset++] & 0xFFL) << 24L;
/* 110 */     retValue |= (b[offset++] & 0xFFL) << 16L;
/* 111 */     retValue |= (b[offset++] & 0xFFL) << 8L;
/* 112 */     retValue |= b[offset] & 0xFFL;
/*     */     
/* 114 */     return retValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeLong(byte[] b, int offset, long value) {
/* 122 */     b[offset++] = (byte)(int)(value >>> 56L);
/* 123 */     b[offset++] = (byte)(int)(value >>> 48L);
/* 124 */     b[offset++] = (byte)(int)(value >>> 40L);
/* 125 */     b[offset++] = (byte)(int)(value >>> 32L);
/* 126 */     b[offset++] = (byte)(int)(value >>> 24L);
/* 127 */     b[offset++] = (byte)(int)(value >>> 16L);
/* 128 */     b[offset++] = (byte)(int)(value >>> 8L);
/* 129 */     b[offset] = (byte)(int)value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long readL6(byte[] b, int offset) {
/* 138 */     long retValue = b[offset++] << 40L;
/* 139 */     retValue |= (b[offset++] & 0xFFL) << 32L;
/* 140 */     retValue |= (b[offset++] & 0xFFL) << 24L;
/* 141 */     retValue |= (b[offset++] & 0xFFL) << 16L;
/* 142 */     retValue |= (b[offset++] & 0xFFL) << 8L;
/* 143 */     retValue |= b[offset] & 0xFFL;
/*     */     
/* 145 */     return retValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeL6(byte[] b, int offset, long value) {
/* 153 */     b[offset++] = (byte)(int)(value >>> 40L);
/* 154 */     b[offset++] = (byte)(int)(value >>> 32L);
/* 155 */     b[offset++] = (byte)(int)(value >>> 24L);
/* 156 */     b[offset++] = (byte)(int)(value >>> 16L);
/* 157 */     b[offset++] = (byte)(int)(value >>> 8L);
/* 158 */     b[offset] = (byte)(int)value;
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
/*     */   public static int readInt(byte[] b, int offset) {
/* 172 */     int retValue = b[offset++] << 24;
/* 173 */     retValue |= (b[offset++] & 0xFF) << 16;
/* 174 */     retValue |= (b[offset++] & 0xFF) << 8;
/* 175 */     retValue |= b[offset] & 0xFF;
/*     */     
/* 177 */     return retValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeInt(byte[] b, int offset, int value) {
/* 186 */     b[offset++] = (byte)(value >>> 24);
/* 187 */     b[offset++] = (byte)(value >>> 16);
/* 188 */     b[offset++] = (byte)(value >>> 8);
/* 189 */     b[offset] = (byte)value;
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
/*     */   public static short readShort(byte[] b, int offset) {
/* 204 */     int retValue = b[offset++] << 8;
/* 205 */     retValue |= b[offset] & 0xFF;
/*     */     
/* 207 */     return (short)retValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeShort(byte[] b, int offset, short value) {
/* 216 */     b[offset++] = (byte)(value >>> 8);
/* 217 */     b[offset] = (byte)value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getString(byte[] b, int offset) {
/* 225 */     int len = (b[offset++] & 0xFF) << 8 | b[offset] & 0xFF;
/* 226 */     return new String(b, offset + 1, len);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int toInt(short s0, short s1) {
/* 235 */     return s0 & 0xFFFF | s1 << 16;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static short toShort(byte b0, byte b1) {
/* 243 */     return (short)(b0 & 0xFF | b1 << 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] toBytes(int n) {
/* 251 */     byte[] buf = new byte[4];
/* 252 */     writeInt(buf, 0, n);
/* 253 */     return buf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] toBytes(short[] array, int offset, int length) {
/* 263 */     byte[] buf = new byte[2 * length];
/* 264 */     int j = 0;
/*     */     
/* 266 */     for (int i = offset; i < offset + length; i++) {
/* 267 */       buf[j++] = (byte)(array[i] >>> 8 & 0xFF);
/* 268 */       buf[j++] = (byte)(array[i] & 0xFF);
/*     */     } 
/* 270 */     return buf;
/*     */   }
/*     */   public static byte[] toBytes(short[] array) {
/* 273 */     return toBytes(array, 0, array.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static short[] toShorts(byte[] array, int offset, int length) {
/* 282 */     short[] buf = new short[length / 2];
/* 283 */     int j = 0;
/*     */     
/* 285 */     for (int i = offset; i < offset + length - 1; i += 2) {
/* 286 */       buf[j++] = (short)((array[i] & 0xFF) << 8 | array[i + 1] & 0xFF);
/*     */     }
/* 288 */     return buf;
/*     */   }
/*     */   public static short[] toShorts(byte[] array) {
/* 291 */     return toShorts(array, 0, array.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean areEqual(byte[] a, byte[] b) {
/* 299 */     int aLength = a.length;
/* 300 */     if (aLength != b.length) return false;
/*     */     
/* 302 */     for (int i = 0; i < aLength; i++) {
/* 303 */       if (a[i] != b[i]) return false; 
/*     */     } 
/* 305 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean areEqual(int[] a, int[] b) {
/* 314 */     int aLength = a.length;
/* 315 */     if (aLength != b.length) return false;
/*     */     
/* 317 */     for (int i = 0; i < aLength; i++) {
/* 318 */       if (a[i] != b[i]) return false; 
/*     */     } 
/* 320 */     return true;
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
/*     */   public static int compared(byte[] a, byte[] b, boolean msbFirst) {
/* 336 */     int aLength = a.length;
/* 337 */     if (aLength < b.length) return -1; 
/* 338 */     if (aLength > b.length) return 1;
/*     */     
/* 340 */     if (msbFirst) {
/* 341 */       for (int i = aLength - 1; i >= 0; i--) {
/* 342 */         int b1 = a[i] & 0xFF;
/* 343 */         int b2 = b[i] & 0xFF;
/* 344 */         if (b1 < b2) return -1; 
/* 345 */         if (b1 > b2) return 1; 
/*     */       } 
/*     */     } else {
/* 348 */       for (int i = 0; i < aLength; i++) {
/* 349 */         int b1 = a[i] & 0xFF;
/* 350 */         int b2 = b[i] & 0xFF;
/* 351 */         if (b1 < b2) return -1; 
/* 352 */         if (b1 > b2) return 1; 
/*     */       } 
/* 354 */     }  return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isText(byte[] buffer) {
/* 359 */     int len = buffer.length;
/* 360 */     if (len == 0) return false; 
/* 361 */     for (int i = 0; i < len; i++) {
/* 362 */       int c = buffer[i] & 0xFF;
/* 363 */       if (c < 32 || c > 127)
/* 364 */         switch (c) {
/*     */           case 7:
/*     */           case 8:
/*     */           case 9:
/*     */           case 10:
/*     */           case 11:
/*     */           case 12:
/*     */           case 13:
/*     */           case 26:
/*     */           case 27:
/*     */           case 155:
/*     */             break;
/*     */           default:
/* 377 */             return false;
/*     */         }  
/*     */     } 
/* 380 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\imp\\util\ArrayUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */