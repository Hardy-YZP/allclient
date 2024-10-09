/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Float390
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/Float390.java";
/*     */   private static final long DOUBLE_SIGN_MASK = -9223372036854775808L;
/*     */   private static final long DOUBLE_EXPONENT_MASK = 9218868437227405312L;
/*     */   private static final long DOUBLE_MANTISSA_MASK = 4503599627370495L;
/*     */   private static final long DOUBLE_MANTISSA_MSB_MASK = 4503599627370496L;
/*     */   private static final long DOUBLE_BIAS = 1022L;
/*     */   private static final int S390_DOUBLE_BIAS = 64;
/*     */   private static final long S390_DOUBLE_EXPONENT_MASK = 9151314442816847872L;
/*     */   private static final long S390_DOUBLE_MANTISSA_MASK = 72057594037927935L;
/*     */   private static final int FLOAT_SIGN_MASK = -2147483648;
/*     */   private static final int FLOAT_EXPONENT_MASK = 2139095040;
/*     */   private static final int FLOAT_MANTISSA_MASK = 8388607;
/*     */   private static final int FLOAT_MANTISSA_MSB_MASK = 8388608;
/*     */   private static final int FLOAT_BIAS = 126;
/*     */   private static final int S390_FLOAT_BIAS = 64;
/*     */   private static final int S390_FLOAT_EXPONENT_MASK = 2130706432;
/*     */   private static final int S390_FLOAT_MANTISSA_MASK = 16777215;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.msg.client.jms.internal.Float390", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/Float390.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final long doubleToS390LongBits(double ieeeDouble) throws IOException {
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry("com.ibm.msg.client.jms.internal.Float390", "doubleToS390LongBits(double)", new Object[] {
/* 122 */             Double.valueOf(ieeeDouble)
/*     */           });
/*     */     }
/*     */     
/* 126 */     long ieeeLongBits = Double.doubleToLongBits(ieeeDouble);
/*     */ 
/*     */     
/* 129 */     boolean positive = ((ieeeLongBits & Long.MIN_VALUE) == 0L);
/*     */ 
/*     */     
/* 132 */     if ((ieeeLongBits & Long.MAX_VALUE) == 0L) {
/*     */ 
/*     */       
/* 135 */       if (Trace.isOn) {
/* 136 */         Trace.exit("com.ibm.msg.client.jms.internal.Float390", "doubleToS390LongBits(double)", 
/* 137 */             Long.valueOf(ieeeLongBits), 1);
/*     */       }
/* 139 */       return ieeeLongBits;
/*     */     } 
/*     */ 
/*     */     
/* 143 */     long exponent = ieeeLongBits & 0x7FF0000000000000L;
/*     */     
/* 145 */     exponent >>>= 52L;
/*     */     
/* 147 */     exponent -= 1022L;
/*     */ 
/*     */     
/* 150 */     long mantissa = ieeeLongBits & 0xFFFFFFFFFFFFFL;
/*     */ 
/*     */ 
/*     */     
/* 154 */     long remainder = Math.abs(exponent) % 4L;
/* 155 */     long quotient = Math.abs(exponent) / 4L;
/*     */     
/* 157 */     long s390Exponent = quotient;
/* 158 */     if (exponent > 0L && remainder != 0L) {
/* 159 */       s390Exponent++;
/*     */     }
/*     */ 
/*     */     
/* 163 */     if (exponent < 0L) {
/* 164 */       s390Exponent = -s390Exponent;
/*     */     }
/*     */ 
/*     */     
/* 168 */     s390Exponent += 64L;
/*     */ 
/*     */     
/* 171 */     long s390Mantissa = mantissa;
/*     */     
/* 173 */     if (exponent > -1022L) {
/* 174 */       s390Mantissa |= 0x10000000000000L;
/*     */     }
/*     */     else {
/*     */       
/* 178 */       s390Mantissa <<= 1L;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 183 */     s390Mantissa <<= 3L;
/*     */     
/* 185 */     if (remainder > 0L) {
/* 186 */       if (exponent > 0L) {
/*     */         
/* 188 */         int shift_places = (int)(4L - remainder);
/* 189 */         s390Mantissa >>>= shift_places;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 194 */         if (exponent == -1022L && (s390Mantissa & 0xF0000000000000L) == 0L) {
/* 195 */           s390Mantissa <<= 4L;
/* 196 */           s390Exponent--;
/*     */         } 
/*     */         
/* 199 */         s390Mantissa >>>= (int)remainder;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 208 */     if (exponent == -1022L) {
/* 209 */       while (s390Mantissa != 0L && (s390Mantissa & 0xF0000000000000L) == 0L) {
/* 210 */         s390Mantissa <<= 4L;
/* 211 */         s390Exponent--;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 217 */     if (s390Exponent > 127L) {
/*     */       
/* 219 */       IOException traceRet1 = new IOException("Number outside of range for double precision S/390 Float");
/*     */       
/* 221 */       if (Trace.isOn) {
/* 222 */         Trace.throwing("com.ibm.msg.client.jms.internal.Float390", "doubleToS390LongBits(double)", traceRet1);
/*     */       }
/*     */       
/* 225 */       throw traceRet1;
/*     */     } 
/* 227 */     if (s390Exponent < 0L) {
/*     */ 
/*     */       
/* 230 */       if (Trace.isOn) {
/* 231 */         Trace.exit("com.ibm.msg.client.jms.internal.Float390", "doubleToS390LongBits(double)", 
/* 232 */             Long.valueOf(0L), 2);
/*     */       }
/* 234 */       return 0L;
/*     */     } 
/*     */ 
/*     */     
/* 238 */     long s390Double = 0L;
/* 239 */     long s390ExponentBits = s390Exponent & 0x7FL;
/*     */ 
/*     */     
/* 242 */     s390Double = s390ExponentBits << 56L;
/*     */     
/* 244 */     if (!positive) {
/* 245 */       s390Double |= Long.MIN_VALUE;
/*     */     }
/*     */     
/* 248 */     s390Double |= s390Mantissa;
/*     */     
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.exit("com.ibm.msg.client.jms.internal.Float390", "doubleToS390LongBits(double)", 
/* 252 */           Long.valueOf(s390Double), 3);
/*     */     }
/* 254 */     return s390Double;
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
/*     */   protected static final int floatToS390IntBits(float ieeeFloat) throws IOException {
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.entry("com.ibm.msg.client.jms.internal.Float390", "floatToS390IntBits(float)", new Object[] {
/* 270 */             Float.valueOf(ieeeFloat)
/*     */           });
/*     */     }
/*     */     
/* 274 */     int ieeeIntBits = Float.floatToIntBits(ieeeFloat);
/*     */ 
/*     */     
/* 277 */     boolean positive = ((ieeeIntBits & Integer.MIN_VALUE) == 0);
/*     */ 
/*     */     
/* 280 */     if ((ieeeIntBits & Integer.MAX_VALUE) == 0) {
/*     */ 
/*     */       
/* 283 */       if (Trace.isOn) {
/* 284 */         Trace.exit("com.ibm.msg.client.jms.internal.Float390", "floatToS390IntBits(float)", 
/* 285 */             Integer.valueOf(ieeeIntBits), 1);
/*     */       }
/* 287 */       return ieeeIntBits;
/*     */     } 
/*     */ 
/*     */     
/* 291 */     int exponent = ieeeIntBits & 0x7F800000;
/*     */     
/* 293 */     exponent >>>= 23;
/*     */     
/* 295 */     exponent -= 126;
/*     */ 
/*     */     
/* 298 */     int mantissa = ieeeIntBits & 0x7FFFFF;
/*     */     
/* 300 */     if (exponent > -126) {
/* 301 */       mantissa |= 0x800000;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 306 */     int remainder = Math.abs(exponent) % 4;
/* 307 */     int quotient = Math.abs(exponent) / 4;
/*     */     
/* 309 */     int s390Exponent = quotient;
/* 310 */     if (exponent > 0 && remainder != 0) {
/* 311 */       s390Exponent++;
/*     */     }
/*     */ 
/*     */     
/* 315 */     if (exponent < 0) {
/* 316 */       s390Exponent = -s390Exponent;
/*     */     }
/*     */ 
/*     */     
/* 320 */     s390Exponent += 64;
/*     */ 
/*     */     
/* 323 */     int s390Mantissa = mantissa;
/* 324 */     if (remainder > 0) {
/* 325 */       if (exponent > 0) {
/*     */         
/* 327 */         int shift_places = 4 - remainder;
/* 328 */         s390Mantissa >>>= shift_places;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 333 */         if (exponent == -126 && (s390Mantissa & 0xF00000) == 0) {
/* 334 */           s390Mantissa <<= 4;
/* 335 */           s390Exponent--;
/*     */         } 
/*     */         
/* 338 */         int shift_places = remainder;
/* 339 */         s390Mantissa >>>= shift_places;
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
/* 352 */     if (exponent == -126) {
/* 353 */       s390Mantissa <<= 1;
/* 354 */       while (s390Mantissa != 0 && (s390Mantissa & 0xF00000) == 0) {
/* 355 */         s390Mantissa <<= 4;
/* 356 */         s390Exponent--;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 361 */     int s390Float = 0;
/* 362 */     int s390ExponentBits = s390Exponent & 0x7F;
/*     */     
/* 364 */     s390Float = s390ExponentBits << 24;
/*     */     
/* 366 */     if (!positive) {
/* 367 */       s390Float |= Integer.MIN_VALUE;
/*     */     }
/*     */     
/* 370 */     s390Float |= s390Mantissa;
/*     */     
/* 372 */     if (Trace.isOn) {
/* 373 */       Trace.exit("com.ibm.msg.client.jms.internal.Float390", "floatToS390IntBits(float)", 
/* 374 */           Integer.valueOf(s390Float), 2);
/*     */     }
/* 376 */     return s390Float;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final float intS390BitsToFloat(int floatBits) throws IOException {
/* 386 */     if (Trace.isOn) {
/* 387 */       Trace.entry("com.ibm.msg.client.jms.internal.Float390", "intS390BitsToFloat(int)", new Object[] {
/* 388 */             Integer.valueOf(floatBits)
/*     */           });
/*     */     }
/*     */     
/* 392 */     boolean positive = ((floatBits & Integer.MIN_VALUE) == 0);
/*     */ 
/*     */     
/* 395 */     if ((floatBits & Integer.MAX_VALUE) == 0) {
/*     */ 
/*     */       
/* 398 */       if (positive) {
/* 399 */         if (Trace.isOn) {
/* 400 */           Trace.exit("com.ibm.msg.client.jms.internal.Float390", "intS390BitsToFloat(int)", 
/* 401 */               Float.valueOf(0.0F), 1);
/*     */         }
/* 403 */         return 0.0F;
/*     */       } 
/* 405 */       float traceRet1 = -0.0F;
/* 406 */       if (Trace.isOn) {
/* 407 */         Trace.exit("com.ibm.msg.client.jms.internal.Float390", "intS390BitsToFloat(int)", 
/* 408 */             Float.valueOf(traceRet1), 2);
/*     */       }
/* 410 */       return traceRet1;
/*     */     } 
/*     */     
/* 413 */     int mantissa = floatBits & 0xFFFFFF;
/* 414 */     int exponent = floatBits & 0x7F000000;
/*     */ 
/*     */     
/* 417 */     exponent >>= 24;
/*     */     
/* 419 */     exponent -= 64;
/*     */ 
/*     */     
/* 422 */     int ieeeExponent = exponent * 4;
/*     */ 
/*     */     
/* 425 */     int ieeeMantissa = mantissa;
/*     */     
/* 427 */     if (ieeeExponent <= -126) {
/*     */       
/* 429 */       ieeeMantissa >>= 1;
/*     */ 
/*     */       
/* 432 */       while (ieeeExponent < -126) {
/* 433 */         ieeeExponent++;
/* 434 */         ieeeMantissa >>= 1;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 439 */     while (ieeeMantissa != 0 && (ieeeMantissa & 0x800000) == 0 && ieeeExponent > -126) {
/*     */       
/* 441 */       ieeeMantissa <<= 1;
/* 442 */       ieeeExponent--;
/*     */     } 
/*     */ 
/*     */     
/* 446 */     if (ieeeExponent < -149) {
/*     */       
/* 448 */       if (Trace.isOn) {
/* 449 */         Trace.exit("com.ibm.msg.client.jms.internal.Float390", "intS390BitsToFloat(int)", 
/* 450 */             Float.valueOf(0.0F), 3);
/*     */       }
/* 452 */       return 0.0F;
/*     */     } 
/* 454 */     if (ieeeExponent > 128) {
/* 455 */       if (positive) {
/*     */         
/* 457 */         float traceRet2 = Float.POSITIVE_INFINITY;
/* 458 */         if (Trace.isOn) {
/* 459 */           Trace.exit("com.ibm.msg.client.jms.internal.Float390", "intS390BitsToFloat(int)", 
/* 460 */               Float.valueOf(traceRet2), 4);
/*     */         }
/* 462 */         return traceRet2;
/*     */       } 
/*     */       
/* 465 */       float traceRet3 = Float.NEGATIVE_INFINITY;
/* 466 */       if (Trace.isOn) {
/* 467 */         Trace.exit("com.ibm.msg.client.jms.internal.Float390", "intS390BitsToFloat(int)", 
/* 468 */             Float.valueOf(traceRet3), 5);
/*     */       }
/* 470 */       return traceRet3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 475 */     int ieeeBits = 0;
/* 476 */     if (!positive) {
/* 477 */       ieeeBits |= Integer.MIN_VALUE;
/*     */     }
/*     */ 
/*     */     
/* 481 */     ieeeExponent += 126;
/*     */     
/* 483 */     ieeeExponent <<= 23;
/*     */     
/* 485 */     ieeeBits |= ieeeExponent;
/*     */ 
/*     */     
/* 488 */     ieeeMantissa &= 0x7FFFFF;
/*     */     
/* 490 */     ieeeBits |= ieeeMantissa;
/*     */     
/* 492 */     float traceRet4 = Float.intBitsToFloat(ieeeBits);
/* 493 */     if (Trace.isOn) {
/* 494 */       Trace.exit("com.ibm.msg.client.jms.internal.Float390", "intS390BitsToFloat(int)", 
/* 495 */           Float.valueOf(traceRet4), 6);
/*     */     }
/* 497 */     return traceRet4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final double longS390BitsToDouble(long doubleBits) throws IOException {
/* 507 */     if (Trace.isOn) {
/* 508 */       Trace.entry("com.ibm.msg.client.jms.internal.Float390", "longS390BitsToDouble(long)", new Object[] {
/* 509 */             Long.valueOf(doubleBits)
/*     */           });
/*     */     }
/*     */     
/* 513 */     boolean positive = ((doubleBits & Long.MIN_VALUE) == 0L);
/*     */ 
/*     */     
/* 516 */     if ((doubleBits & Long.MAX_VALUE) == 0L) {
/*     */ 
/*     */       
/* 519 */       if (positive) {
/* 520 */         if (Trace.isOn) {
/* 521 */           Trace.exit("com.ibm.msg.client.jms.internal.Float390", "longS390BitsToDouble(long)", 
/* 522 */               Double.valueOf(0.0D), 1);
/*     */         }
/* 524 */         return 0.0D;
/*     */       } 
/* 526 */       double traceRet1 = -0.0D;
/* 527 */       if (Trace.isOn) {
/* 528 */         Trace.exit("com.ibm.msg.client.jms.internal.Float390", "longS390BitsToDouble(long)", 
/* 529 */             Double.valueOf(traceRet1), 2);
/*     */       }
/* 531 */       return traceRet1;
/*     */     } 
/*     */     
/* 534 */     long mantissa = doubleBits & 0xFFFFFFFFFFFFFFL;
/* 535 */     long exponent = doubleBits & 0x7F00000000000000L;
/*     */ 
/*     */     
/* 538 */     exponent >>= 56L;
/*     */     
/* 540 */     exponent -= 64L;
/*     */ 
/*     */     
/* 543 */     long ieeeExponent = exponent * 4L;
/*     */ 
/*     */     
/* 546 */     long ieeeMantissa = mantissa;
/*     */     
/* 548 */     ieeeMantissa >>= 3L;
/*     */ 
/*     */     
/* 551 */     if (ieeeExponent <= -1022L) {
/* 552 */       ieeeMantissa >>= 1L;
/*     */ 
/*     */       
/* 555 */       while (ieeeExponent < -1022L) {
/* 556 */         ieeeExponent++;
/* 557 */         ieeeMantissa >>= 1L;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 562 */     while (ieeeMantissa != 0L && (ieeeMantissa & 0x10000000000000L) == 0L && ieeeExponent > -1022L) {
/*     */       
/* 564 */       ieeeMantissa <<= 1L;
/* 565 */       ieeeExponent--;
/*     */     } 
/*     */ 
/*     */     
/* 569 */     if (ieeeExponent < -1045L) {
/* 570 */       if (Trace.isOn) {
/* 571 */         Trace.exit("com.ibm.msg.client.jms.internal.Float390", "longS390BitsToDouble(long)", 
/* 572 */             Double.valueOf(0.0D), 3);
/*     */       }
/* 574 */       return 0.0D;
/*     */     } 
/* 576 */     if (ieeeExponent > 1024L) {
/* 577 */       if (positive) {
/* 578 */         double traceRet2 = Double.POSITIVE_INFINITY;
/* 579 */         if (Trace.isOn) {
/* 580 */           Trace.exit("com.ibm.msg.client.jms.internal.Float390", "longS390BitsToDouble(long)", 
/* 581 */               Double.valueOf(traceRet2), 4);
/*     */         }
/* 583 */         return traceRet2;
/*     */       } 
/*     */       
/* 586 */       double traceRet3 = Double.NEGATIVE_INFINITY;
/* 587 */       if (Trace.isOn) {
/* 588 */         Trace.exit("com.ibm.msg.client.jms.internal.Float390", "longS390BitsToDouble(long)", 
/* 589 */             Double.valueOf(traceRet3), 5);
/*     */       }
/* 591 */       return traceRet3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 596 */     long ieeeBits = 0L;
/* 597 */     if (!positive) {
/* 598 */       ieeeBits |= Long.MIN_VALUE;
/*     */     }
/*     */ 
/*     */     
/* 602 */     ieeeExponent += 1022L;
/*     */     
/* 604 */     ieeeExponent <<= 52L;
/*     */     
/* 606 */     ieeeBits |= ieeeExponent;
/*     */ 
/*     */     
/* 609 */     ieeeMantissa &= 0xFFFFFFFFFFFFFL;
/*     */     
/* 611 */     ieeeBits |= ieeeMantissa;
/*     */     
/* 613 */     double traceRet4 = Double.longBitsToDouble(ieeeBits);
/* 614 */     if (Trace.isOn) {
/* 615 */       Trace.exit("com.ibm.msg.client.jms.internal.Float390", "longS390BitsToDouble(long)", 
/* 616 */           Double.valueOf(traceRet4), 6);
/*     */     }
/* 618 */     return traceRet4;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\Float390.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */