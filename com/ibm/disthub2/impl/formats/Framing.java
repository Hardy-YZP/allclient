/*     */ package com.ibm.disthub2.impl.formats;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.util.ArrayUtil;
/*     */ import com.ibm.disthub2.impl.util.Hex;
/*     */ import com.ibm.disthub2.spi.LogConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Framing
/*     */   implements LogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  90 */   public static final DebugObject debug = new DebugObject("Framing");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int MSGLEN_POS = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int SEGHDR_END = 6;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int QOP_POS = 6;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int QOP_HASH_SIZE = 7;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int QOP_HASH = 8;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SKSL_POS = 8;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int INTERP_ID_POS = 7;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int SCHEMA_ID_POS = 9;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int MSG_POS = 17;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final short PROPAGATION_INDICATOR = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void framePropagationMessage(byte[] msg, int length) {
/* 177 */     if (debug.debugIt(32)) {
/* 178 */       debug.debug(-165922073994779L, "framePropagationMessage", msg, Integer.valueOf(length));
/*     */     }
/*     */     
/* 181 */     ArrayUtil.writeShort(msg, 7, (short)-1);
/*     */     
/* 183 */     if (debug.debugIt(16)) {
/* 184 */       debug.debug(-153415734321212L, "framePropagationMessage", "Propagation frame contents: ", Hex.toString(msg, 0, length));
/*     */     }
/*     */     
/* 187 */     frame(msg, length, (byte)1);
/*     */     
/* 189 */     if (debug.debugIt(64)) {
/* 190 */       debug.debug(-142394261359015L, "framePropagationMessage");
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
/*     */   public static int overhead(byte qop, MessageEncrypter encrypter, boolean propagation) {
/* 208 */     int ans = 9;
/* 209 */     if (!propagation) {
/* 210 */       ans += 8;
/*     */     }
/* 212 */     if (qop > 1)
/*     */     {
/*     */       
/* 215 */       ans += 1 + encrypter.digestLength();
/*     */     }
/* 217 */     if (qop == 14)
/*     */     {
/* 219 */       ans += 4;
/*     */     }
/* 221 */     return ans;
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
/*     */   public static byte[] preFrame(MessageHandle handle, int overhead, MessageEncrypter encrypter) {
/* 243 */     int length = handle.getEncodedLength(encrypter);
/* 244 */     byte[] result = new byte[overhead + length];
/* 245 */     handle.toByteArray(result, overhead, length, encrypter);
/* 246 */     return result;
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
/*     */   public static void frameMessage(byte[] msg, short interpId, long schemaId, int length) {
/* 263 */     if (debug.debugIt(32)) {
/* 264 */       debug.debug(-165922073994779L, "frameMessage", msg, Short.valueOf(interpId), Long.valueOf(schemaId), 
/* 265 */           Integer.valueOf(length));
/*     */     }
/*     */ 
/*     */     
/* 269 */     ArrayUtil.writeShort(msg, 7, interpId);
/* 270 */     ArrayUtil.writeLong(msg, 9, schemaId);
/*     */ 
/*     */     
/* 273 */     frame(msg, length, (byte)1);
/*     */     
/* 275 */     if (debug.debugIt(64)) {
/* 276 */       debug.debug(-142394261359015L, "frameMessage");
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
/*     */   public static int fullLength(byte[] frame) {
/* 290 */     if (debug.debugIt(32)) {
/* 291 */       debug.debug(-165922073994779L, "fullLength", frame);
/*     */     }
/*     */     
/* 294 */     int result = ArrayUtil.readInt(frame, 2) + 6;
/*     */     
/* 296 */     if (debug.debugIt(64)) {
/* 297 */       debug.debug(-142394261359015L, "fullLength", Integer.valueOf(result));
/*     */     }
/*     */     
/* 300 */     return result;
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
/*     */   public static short interpId(byte[] frame) {
/* 314 */     if (debug.debugIt(32)) {
/* 315 */       debug.debug(-165922073994779L, "interpId", frame);
/*     */     }
/*     */ 
/*     */     
/* 319 */     int qopOffset = 0;
/*     */     
/* 321 */     switch (frame[6]) {
/*     */       
/*     */       case 14:
/* 324 */         qopOffset += 4;
/*     */ 
/*     */       
/*     */       case 2:
/*     */       case 6:
/* 329 */         qopOffset += frame[7] + 1;
/*     */         break;
/*     */     } 
/* 332 */     short result = ArrayUtil.readShort(frame, 7 + qopOffset);
/*     */     
/* 334 */     if (debug.debugIt(64)) {
/* 335 */       debug.debug(-142394261359015L, "interpId", Short.valueOf(result));
/*     */     }
/*     */     
/* 338 */     return result;
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
/*     */   public static long schemaId(byte[] frame) {
/* 352 */     if (debug.debugIt(32)) {
/* 353 */       debug.debug(-165922073994779L, "schemaId", frame);
/*     */     }
/*     */ 
/*     */     
/* 357 */     int qopOffset = 0;
/*     */     
/* 359 */     switch (frame[6]) {
/*     */       
/*     */       case 14:
/* 362 */         qopOffset += 4;
/*     */ 
/*     */       
/*     */       case 2:
/*     */       case 6:
/* 367 */         qopOffset += frame[7] + 1;
/*     */         break;
/*     */     } 
/* 370 */     long result = ArrayUtil.readLong(frame, 9 + qopOffset);
/*     */     
/* 372 */     if (debug.debugIt(64)) {
/* 373 */       debug.debug(-142394261359015L, "schemaId", Long.valueOf(result));
/*     */     }
/*     */     
/* 376 */     return result;
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
/*     */   public static int sksl(byte[] frame) {
/* 391 */     if (debug.debugIt(32)) {
/* 392 */       debug.debug(-165922073994779L, "sksl", frame);
/*     */     }
/*     */     
/* 395 */     int result = -1;
/* 396 */     if ((frame[6] & 0xE) == 14)
/*     */     {
/*     */       
/* 399 */       result = ArrayUtil.readInt(frame, 8 + frame[7]) - 10;
/*     */     }
/*     */     
/* 402 */     if (debug.debugIt(64)) {
/* 403 */       debug.debug(-142394261359015L, "sksl", Integer.valueOf(result));
/*     */     }
/*     */     
/* 406 */     return result;
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
/*     */   public static int propagationBodyOffset(byte[] frame) {
/* 421 */     if (debug.debugIt(32)) {
/* 422 */       debug.debug(-165922073994779L, "propagationBodyOffset", frame);
/*     */     }
/*     */ 
/*     */     
/* 426 */     int qopOffset = 0;
/* 427 */     if ((frame[6] & 0x2) == 2)
/*     */     {
/* 429 */       qopOffset = frame[7] + 1 + 4;
/*     */     }
/*     */     
/* 432 */     int result = 9 + qopOffset;
/*     */     
/* 434 */     if (debug.debugIt(64)) {
/* 435 */       debug.debug(-142394261359015L, "propagationBodyOffset", Integer.valueOf(result));
/*     */     }
/*     */     
/* 438 */     return result;
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
/*     */   public static int bodyOffset(byte[] frame) {
/* 454 */     if (debug.debugIt(32)) {
/* 455 */       debug.debug(-165922073994779L, "bodyOffset", frame);
/*     */     }
/*     */ 
/*     */     
/* 459 */     int qopOffset = 0;
/*     */     
/* 461 */     switch (frame[6]) {
/*     */       
/*     */       case 14:
/* 464 */         qopOffset += 4;
/*     */ 
/*     */       
/*     */       case 2:
/*     */       case 6:
/* 469 */         qopOffset += frame[7] + 1;
/*     */         break;
/*     */     } 
/* 472 */     int result = 17 + qopOffset;
/*     */     
/* 474 */     if (debug.debugIt(64)) {
/* 475 */       debug.debug(-142394261359015L, "bodyOffset", Integer.valueOf(result));
/*     */     }
/*     */     
/* 478 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte qop(byte[] frame) {
/* 489 */     if (debug.debugIt(32)) {
/* 490 */       debug.debug(-165922073994779L, "qop", frame);
/*     */     }
/*     */     
/* 493 */     byte result = frame[6];
/*     */     
/* 495 */     if (debug.debugIt(64)) {
/* 496 */       debug.debug(-142394261359015L, "qop", Byte.valueOf(result));
/*     */     }
/*     */     
/* 499 */     return result;
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
/*     */   public static void setLength(byte[] frame, int length) {
/* 512 */     if (debug.debugIt(32)) {
/* 513 */       debug.debug(-165922073994779L, "setLength", frame, Integer.valueOf(length));
/*     */     }
/*     */     
/* 516 */     ArrayUtil.writeInt(frame, 2, length - 6);
/*     */     
/* 518 */     if (debug.debugIt(64)) {
/* 519 */       debug.debug(-142394261359015L, "setLength");
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
/*     */   public static void frame(byte[] msg, int length, byte qop) {
/* 533 */     if (debug.debugIt(32)) {
/* 534 */       debug.debug(-165922073994779L, "frame", msg, Integer.valueOf(length), Byte.valueOf(qop));
/*     */     }
/*     */     
/* 537 */     ArrayUtil.writeShort(msg, 0, (short)-13647);
/* 538 */     ArrayUtil.writeInt(msg, 2, length - 6);
/* 539 */     msg[6] = qop;
/*     */     
/* 541 */     if (debug.debugIt(64)) {
/* 542 */       debug.debug(-142394261359015L, "frame");
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
/*     */   public static void attachDigest(byte[] msg, byte[] digest) {
/* 557 */     if (debug.debugIt(32)) {
/* 558 */       debug.debug(-165922073994779L, "attachDigest", msg, digest);
/*     */     }
/*     */     
/* 561 */     msg[7] = (byte)digest.length;
/* 562 */     System.arraycopy(digest, 0, msg, 8, digest.length);
/*     */     
/* 564 */     if (debug.debugIt(64)) {
/* 565 */       debug.debug(-142394261359015L, "attachDigest");
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
/*     */   public static int computeDigestOffset(byte[] frame) {
/* 578 */     if (debug.debugIt(32)) {
/* 579 */       debug.debug(-165922073994779L, "computeDigestOffset", frame);
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
/* 591 */     byte qop = frame[6];
/* 592 */     int msgLen = fullLength(frame);
/* 593 */     int hashLen = frame[7];
/* 594 */     int macALen = 8 + hashLen;
/* 595 */     int sksl = sksl(frame) + 10;
/* 596 */     int digestStart = (qop == 14) ? (macALen + sksl + 4) : (macALen + 10);
/*     */     
/* 598 */     if (debug.debugIt(64)) {
/* 599 */       debug.debug(-142394261359015L, "computeDigestOffset", Integer.valueOf(digestStart));
/*     */     }
/*     */     
/* 602 */     return digestStart;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\Framing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */