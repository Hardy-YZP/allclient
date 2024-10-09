/*     */ package com.ibm.disthub2.impl.security;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.formats.Envelop;
/*     */ import com.ibm.disthub2.impl.formats.Framing;
/*     */ import com.ibm.disthub2.impl.util.ArrayUtil;
/*     */ import com.ibm.disthub2.impl.util.Assert;
/*     */ import com.ibm.disthub2.impl.util.Hex;
/*     */ import com.ibm.disthub2.spi.ClientExceptionConstants;
/*     */ import com.ibm.disthub2.spi.ClientLogConstants;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Qop
/*     */   implements ClientLogConstants, ClientExceptionConstants, Envelop.Constants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  90 */   private static final DebugObject debug = new DebugObject("Qop");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte getPayloadRequiredQop(int type) {
/* 117 */     if (debug.debugIt(32)) {
/* 118 */       debug.debug(-165922073994779L, "getPayloadRequiredQop", Integer.valueOf(type));
/*     */     }
/*     */     
/* 121 */     byte result = 1;
/*     */     
/* 123 */     switch (type) {
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/*     */       case 3:
/* 129 */         result = 2;
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 5:
/*     */       case 6:
/* 136 */         result = 6;
/*     */         break;
/*     */ 
/*     */       
/*     */       case 4:
/*     */       case 7:
/*     */       case 8:
/* 143 */         result = 14;
/*     */         break;
/*     */       
/*     */       case 1:
/*     */       case 10:
/* 148 */         Assert.failure("Qop.getPayloadRequiredQop(): invalid payload type");
/*     */ 
/*     */       
/*     */       default:
/* 152 */         result = 1;
/*     */         break;
/*     */     } 
/* 155 */     if (debug.debugIt(64)) {
/* 156 */       debug.debug(-142394261359015L, "getPayloadRequiredQop", Byte.valueOf(result));
/*     */     }
/*     */     
/* 159 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte getSingleHopControlRequiredQop(int type) {
/* 170 */     if (debug.debugIt(32)) {
/* 171 */       debug.debug(-165922073994779L, "getSingleHopControlRequiredQop", Integer.valueOf(type));
/*     */     }
/*     */     
/* 174 */     byte result = 1;
/*     */     
/* 176 */     switch (type) {
/*     */       
/*     */       case 1:
/*     */       case 2:
/*     */       case 11:
/*     */       case 14:
/*     */       case 15:
/*     */       case 34:
/*     */       case 35:
/* 185 */         result = 14;
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/* 198 */         result = 6;
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/* 203 */         Assert.failure("Qop.getSingleHopControlRequiredQop: SingleHopControl with unknown body type - are we in compatibility mode?");
/*     */         break;
/*     */     } 
/* 206 */     if (debug.debugIt(64)) {
/* 207 */       debug.debug(-142394261359015L, "getSingleHopControlRequiredQop", Byte.valueOf(result));
/*     */     }
/*     */     
/* 210 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPrivate(byte value) {
/* 221 */     if (debug.debugIt(32)) {
/* 222 */       debug.debug(-165922073994779L, "isPrivate", Byte.valueOf(value));
/*     */     }
/*     */     
/* 225 */     boolean result = ((value & 0xE) == 14);
/*     */     
/* 227 */     if (debug.debugIt(64)) {
/* 228 */       debug.debug(-142394261359015L, "isNoProtection", Boolean.valueOf(result));
/*     */     }
/*     */     
/* 231 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isMessageIntegrity(byte value) {
/* 242 */     if (debug.debugIt(32)) {
/* 243 */       debug.debug(-165922073994779L, "isMessageIntegrity", Byte.valueOf(value));
/*     */     }
/*     */     
/* 246 */     boolean result = ((value & 0x6) == 6);
/*     */     
/* 248 */     if (debug.debugIt(64)) {
/* 249 */       debug.debug(-142394261359015L, "isNoProtection", Boolean.valueOf(result));
/*     */     }
/*     */     
/* 252 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isChannelIntegrity(byte value) {
/* 263 */     if (debug.debugIt(32)) {
/* 264 */       debug.debug(-165922073994779L, "isChannelIntegrity", Byte.valueOf(value));
/*     */     }
/*     */     
/* 267 */     boolean result = ((value & 0x2) == 2);
/*     */     
/* 269 */     if (debug.debugIt(64)) {
/* 270 */       debug.debug(-142394261359015L, "isNoProtection", Boolean.valueOf(result));
/*     */     }
/*     */     
/* 273 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isNoProtection(byte value) {
/* 284 */     if (debug.debugIt(32)) {
/* 285 */       debug.debug(-165922073994779L, "isNoProtection", Byte.valueOf(value));
/*     */     }
/*     */     
/* 288 */     boolean result = (value == 1);
/*     */     
/* 290 */     if (debug.debugIt(64)) {
/* 291 */       debug.debug(-142394261359015L, "isNoProtection", Boolean.valueOf(result));
/*     */     }
/*     */     
/* 294 */     return result;
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
/*     */   public static void checkIntegrity(byte[] frame, SecurityContext C, boolean client, boolean qopEnabled) throws IntegrityCompromisedException, SecurityGeneralException {
/* 324 */     if (debug.debugIt(32)) {
/* 325 */       debug.debug(-165922073994779L, "checkIntegrity", frame, C, Boolean.valueOf(client));
/*     */     }
/*     */     
/* 328 */     byte qop = frame[6];
/*     */ 
/*     */     
/* 331 */     if (qop != 1 && qop != 2 && qop != 6 && qop != 14)
/*     */     {
/*     */ 
/*     */       
/* 335 */       throw new IntegrityCompromisedException(ExceptionBuilder.buildReasonString(-1470561016, new Object[] { Byte.valueOf(qop) }));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 340 */     if (qop != 1 && !qopEnabled) {
/* 341 */       throw new IntegrityCompromisedException(ExceptionBuilder.buildReasonString(-1544362115, new Object[] { Byte.valueOf(qop) }));
/*     */     }
/*     */ 
/*     */     
/* 345 */     if ((qop & 0x1) != 0) {
/* 346 */       if (debug.debugIt(16)) {
/* 347 */         debug.debug(-153415734321212L, "checkIntegrity", "No QOP on message, skipping integrity check");
/*     */       }
/*     */       
/* 350 */       if (debug.debugIt(64)) {
/* 351 */         debug.debug(-142394261359015L, "checkIntegrity");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 357 */     if (!C.useQOP) {
/* 358 */       if (debug.debugIt(16)) {
/* 359 */         debug.debug(-153415734321212L, "checkIntegrity", "QOP > SA_NONE but QOP disabled, throwing exception");
/*     */       }
/* 361 */       throw new SecurityGeneralException(ExceptionBuilder.buildReasonString(-1544362115, new Object[] { Byte.valueOf(qop) }));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 368 */     MessageProtection mp = C.getMP();
/* 369 */     int msgLen = Framing.fullLength(frame);
/* 370 */     int hashLen = frame[7];
/* 371 */     int channelRegion = Framing.computeDigestOffset(frame);
/* 372 */     byte[] hashFrame = new byte[channelRegion + 8];
/*     */ 
/*     */ 
/*     */     
/* 376 */     System.arraycopy(frame, 0, hashFrame, 8, channelRegion);
/*     */ 
/*     */     
/* 379 */     if ((qop & 0x6) == 6) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 384 */       if (debug.debugIt(16)) {
/* 385 */         debug.debug(-153415734321212L, "checkIntegrity", "hashLen=" + hashLen + ", msgLen=" + msgLen + ", channelRegion=" + channelRegion);
/* 386 */         debug.debug(-153415734321212L, "checkIntegrity", "Testing digest on frame: " + Hex.toString(frame, channelRegion, msgLen - channelRegion));
/*     */       } 
/* 388 */       byte[] theDigest = extractDigest(frame, mp);
/* 389 */       System.arraycopy(theDigest, 0, hashFrame, 16, theDigest.length);
/*     */       
/* 391 */       if (debug.debugIt(16)) {
/* 392 */         debug.debug(-153415734321212L, "checkIntegrity", "QOP = MINTEGRITY, computed hash: " + Hex.toString(hashFrame, 16, hashLen));
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 397 */       for (int i = 0; i < hashLen; i++) {
/* 398 */         hashFrame[i + 8 + 8] = 0;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 403 */     long nextSeq = C.getNextRcvCount();
/* 404 */     ArrayUtil.writeLong(hashFrame, 0, nextSeq);
/*     */ 
/*     */ 
/*     */     
/* 408 */     Object[] macKey = client ? C.getClientMAC() : C.getServerMAC();
/* 409 */     if (debug.debugIt(16)) {
/* 410 */       debug.debug(-153415734321212L, "checkIntegrity", "QOP = CINTEGRITY, pre-mac frame: " + Hex.toString(hashFrame));
/*     */     }
/* 412 */     mp.hmac(macKey, hashFrame, 0, hashFrame.length, hashFrame, 16);
/* 413 */     if (debug.debugIt(16)) {
/* 414 */       debug.debug(-153415734321212L, "checkIntegrity", "QOP = CINTEGRITY, seq: " + nextSeq + " computed mac: " + 
/* 415 */           Hex.toString(hashFrame, 16, mp.digestLength()) + " hash region length: " + hashFrame.length + " inner key: " + macKey[0]
/*     */           
/* 417 */           .toString() + " outer key: " + macKey[1]
/* 418 */           .toString());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 423 */     if (!mp.compareBuffers(frame, 8, hashFrame, 16, hashLen)) {
/* 424 */       if (debug.debugIt(16)) {
/* 425 */         debug.debug(-153415734321212L, "checkIntegrity", "Channel and/or message integrity compromised, throwing exception");
/*     */       }
/* 427 */       if (debug.debugIt(16)) {
/* 428 */         debug.debug(-153415734321212L, "checkIntegrity", "Msg QOP: " + qop + " msg type: " + ArrayUtil.readLong(frame, Framing.bodyOffset(frame)));
/*     */       }
/* 430 */       throw new IntegrityCompromisedException(ExceptionBuilder.buildReasonString(-1599226991, null));
/*     */     } 
/*     */     
/* 433 */     if (debug.debugIt(64)) {
/* 434 */       debug.debug(-142394261359015L, "checkIntegrity");
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
/*     */   public static void sessionEncrypt(byte[] msg, int offset, int sksl, MessageProtection mp, Object key, byte[] eiv) {
/* 458 */     if (debug.debugIt(32)) {
/* 459 */       debug.debug(-165922073994779L, "sessionEncrypt", msg, Integer.valueOf(offset), Integer.valueOf(sksl), mp, key, eiv);
/*     */     }
/*     */     
/* 462 */     if (debug.debugIt(16)) {
/* 463 */       debug.debug(-153415734321212L, "sessionEncrypt", "Session encrypting message with sksl: " + sksl);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 471 */     mp.encrypt(key, eiv, msg, offset, sksl, msg, offset);
/*     */ 
/*     */     
/* 474 */     ArrayUtil.writeInt(msg, offset - 4, sksl);
/*     */     
/* 476 */     if (debug.debugIt(64)) {
/* 477 */       debug.debug(-142394261359015L, "sessionEncrypt");
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
/*     */   public static void sessionDecrypt(byte[] frame, MessageProtection mp, Object key, byte[] div) {
/* 500 */     if (debug.debugIt(32)) {
/* 501 */       debug.debug(-165922073994779L, "sessionDecrypt", frame, mp, key, div);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 507 */     Assert.condition((Framing.qop(frame) == 14));
/*     */     
/* 509 */     int sksl = Framing.sksl(frame) + 10;
/*     */     
/* 511 */     if (debug.debugIt(16)) {
/* 512 */       debug.debug(-153415734321212L, "sessionDecrypt", "Session decrypting message with sksl: " + sksl);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 519 */     int startIndex = frame[7] + 8 + 4;
/*     */     
/* 521 */     if (debug.debugIt(16)) {
/* 522 */       debug.debug(-153415734321212L, "sessionDecrypt", "Pre decryption frame: " + Hex.toString(frame, startIndex, sksl));
/*     */     }
/*     */     
/* 525 */     mp.decrypt(key, div, frame, startIndex, sksl, frame, startIndex);
/*     */     
/* 527 */     if (debug.debugIt(16)) {
/* 528 */       debug.debug(-153415734321212L, "sessionDecrypt", "Post decryption frame: " + Hex.toString(frame, startIndex, sksl));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 533 */     if (debug.debugIt(64)) {
/* 534 */       debug.debug(-142394261359015L, "sessionDecrypt");
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
/*     */   public static void channelProtect(byte[] frame, MessageProtection mp, long counter, Object[] key) {
/* 555 */     if (debug.debugIt(32)) {
/* 556 */       debug.debug(-165922073994779L, "channelProtect", frame, mp, Long.valueOf(counter), key);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 563 */     byte qop = Framing.qop(frame);
/* 564 */     int size = mp.digestLength();
/* 565 */     int channelRegion = Framing.computeDigestOffset(frame);
/* 566 */     byte[] preFrame = new byte[channelRegion + 8];
/* 567 */     System.arraycopy(frame, 0, preFrame, 8, channelRegion);
/* 568 */     ArrayUtil.writeLong(preFrame, 0, counter);
/*     */     
/* 570 */     if (debug.debugIt(16)) {
/* 571 */       debug.debug(-153415734321212L, "channelProtect", "pre-mac frame: " + Hex.toString(preFrame));
/*     */     }
/* 573 */     if (debug.debugIt(16)) {
/* 574 */       debug.debug(-153415734321212L, "channelProtect", "Attaching channel protection -> count: " + counter + " to hash: " + 
/* 575 */           Hex.toString(preFrame, 0, preFrame.length) + " hash region length: " + preFrame.length + " inner key: " + key[0]
/*     */           
/* 577 */           .toString() + " outer key: " + key[1]
/* 578 */           .toString());
/*     */     }
/*     */     
/* 581 */     mp.hmac(key, preFrame, 0, preFrame.length, frame, 8);
/*     */     
/* 583 */     if (debug.debugIt(16)) {
/* 584 */       debug.debug(-153415734321212L, "channelProtect", "Attached channel protection -> count: " + counter + " hash: " + 
/* 585 */           Hex.toString(frame, 8, mp.digestLength()) + " hash region length: " + preFrame.length + " inner key: " + key[0]
/*     */           
/* 587 */           .toString() + " outer key: " + key[1]
/* 588 */           .toString());
/* 589 */       debug.debug(-153415734321212L, "channelProtect", "Final frame: " + Hex.toString(frame));
/*     */     } 
/*     */     
/* 592 */     if (debug.debugIt(64)) {
/* 593 */       debug.debug(-142394261359015L, "channelProtect");
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
/*     */   public static byte[] computeDigest(byte[] frame, int offset, int length, MessageProtection mp) {
/* 608 */     if (debug.debugIt(32)) {
/* 609 */       debug.debug(-165922073994779L, "computeDigest", frame, Integer.valueOf(offset), Integer.valueOf(length), mp);
/*     */     }
/*     */     
/* 612 */     byte[] digest = new byte[mp.digestLength()];
/* 613 */     mp.digest(null, frame, offset, length, digest, 0);
/*     */     
/* 615 */     if (debug.debugIt(64)) {
/* 616 */       debug.debug(-142394261359015L, "computeDigest", digest);
/*     */     }
/*     */     
/* 619 */     return digest;
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
/*     */   public static byte[] extractDigest(byte[] frame, MessageProtection mp) {
/* 634 */     if (debug.debugIt(32)) {
/* 635 */       debug.debug(-165922073994779L, "extractDigest", frame, mp);
/*     */     }
/*     */     
/* 638 */     byte[] result = new byte[mp.digestLength()];
/* 639 */     byte qop = frame[6];
/*     */     
/* 641 */     Assert.condition(((qop & 0x6) == 6));
/*     */ 
/*     */     
/* 644 */     int msgLen = Framing.fullLength(frame);
/* 645 */     int digestStart = Framing.computeDigestOffset(frame);
/*     */     
/* 647 */     mp.digest(null, frame, digestStart, msgLen - digestStart, result, 0);
/*     */     
/* 649 */     if (debug.debugIt(64)) {
/* 650 */       debug.debug(-142394261359015L, "extractDigest", result);
/*     */     }
/*     */     
/* 653 */     return result;
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
/*     */   public static void framePropagationMessage(byte[] msg, SecurityContext sc, boolean client, int length) {
/* 674 */     if (debug.debugIt(32)) {
/* 675 */       debug.debug(-165922073994779L, "framePropagationMessage", msg, sc, Boolean.valueOf(client), Integer.valueOf(length));
/*     */     }
/*     */     
/* 678 */     MessageProtection mp = sc.getMP();
/* 679 */     int overhead = Framing.overhead((byte)14, mp, true);
/* 680 */     ArrayUtil.writeShort(msg, overhead - 2, (short)-1);
/*     */     
/* 682 */     if (debug.debugIt(16)) {
/* 683 */       debug.debug(-153415734321212L, "framePropagationMessage", "Propagation frame contents: " + Hex.toString(msg, 0, length));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 688 */     Object key = client ? sc.getClientKey() : sc.getServerKey();
/*     */     
/* 690 */     sessionEncrypt(msg, overhead - 2, length - overhead - 2, mp, key, sc.getEncryptIV());
/*     */ 
/*     */     
/* 693 */     Framing.attachDigest(msg, computeDigest(msg, 0, 0, mp));
/*     */     
/* 695 */     Framing.frame(msg, length, (byte)14);
/*     */     
/* 697 */     channelProtect(msg, mp, sc.getNextSendCount(), client ? sc
/* 698 */         .getClientMAC() : sc.getServerMAC());
/*     */     
/* 700 */     if (debug.debugIt(64)) {
/* 701 */       debug.debug(-142394261359015L, "framePropagationMessage");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void frameMessage(byte[] msg, short interpId, long schemaId, byte qop, int sksl, SecurityContext C, byte[] digest, boolean client, int length) throws SecurityGeneralException {
/* 746 */     if (debug.debugIt(32)) {
/* 747 */       debug.debug(-165922073994779L, "frameMessage", new Object[] { msg, 
/* 748 */             Short.valueOf(interpId), Long.valueOf(schemaId), Byte.valueOf(qop), Integer.valueOf(sksl), C, digest, Boolean.valueOf(client), 
/* 749 */             Integer.valueOf(length) });
/*     */     }
/*     */ 
/*     */     
/* 753 */     int payloadStart = Framing.overhead(qop, C.getMP(), false);
/* 754 */     ArrayUtil.writeShort(msg, payloadStart - 10, interpId);
/* 755 */     ArrayUtil.writeLong(msg, payloadStart - 8, schemaId);
/* 756 */     sksl += 10;
/*     */ 
/*     */     
/* 759 */     MessageProtection mp = C.getMP();
/* 760 */     Object key = client ? C.getClientKey() : C.getServerKey();
/*     */     
/* 762 */     switch (qop) {
/*     */       case 14:
/* 764 */         sessionEncrypt(msg, payloadStart - 10, sksl, mp, key, C.getEncryptIV());
/*     */       
/*     */       case 6:
/* 767 */         Assert.condition((digest.length == mp.digestLength()));
/* 768 */         Framing.attachDigest(msg, digest);
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 776 */         Framing.attachDigest(msg, new byte[mp.digestLength()]);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 782 */     if (debug.debugIt(16)) {
/* 783 */       debug.debug(-153415734321212L, "frameMessage", "QOP: " + qop);
/*     */     }
/*     */ 
/*     */     
/* 787 */     Framing.frame(msg, length, qop);
/*     */     
/* 789 */     if (debug.debugIt(64)) {
/* 790 */       debug.debug(-142394261359015L, "frameMessage");
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
/*     */   public static String debugPrint(byte sa) {
/* 802 */     String result = "flags[ ";
/*     */     
/* 804 */     if (isPrivate(sa)) {
/* 805 */       result = result + "SA_PRIVACY ";
/*     */     }
/*     */     
/* 808 */     if (isMessageIntegrity(sa)) {
/* 809 */       result = result + "SA_MINTEGRITY ";
/*     */     }
/*     */     
/* 812 */     if (isChannelIntegrity(sa)) {
/* 813 */       result = result + "SA_CINTEGRITY ";
/*     */     }
/*     */     
/* 816 */     if (isNoProtection(sa)) {
/* 817 */       result = result + "SA_NONE ";
/*     */     }
/*     */     
/* 820 */     return result + "]";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\security\Qop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */