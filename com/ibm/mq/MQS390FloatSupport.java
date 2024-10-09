/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MQS390FloatSupport
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQS390FloatSupport.java";
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
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.MQS390FloatSupport", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQS390FloatSupport.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQS390FloatSupport() {
/*  61 */     super(MQSESSION.getJmqiEnv());
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.entry(this, "com.ibm.mq.MQS390FloatSupport", "<init>()");
/*     */     }
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.exit(this, "com.ibm.mq.MQS390FloatSupport", "<init>()");
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
/*     */   protected static final long doubleToS390LongBits(double ieeeDouble) throws IOException {
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.entry("com.ibm.mq.MQS390FloatSupport", "doubleToS390LongBits(double)", new Object[] {
/* 117 */             Double.valueOf(ieeeDouble)
/*     */           });
/*     */     }
/* 120 */     long ieeeLongBits = Double.doubleToLongBits(ieeeDouble);
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.data("com.ibm.mq.MQS390FloatSupport", "doubleToS390LongBits(double)", "IEEE double bit pattern= " + 
/*     */           
/* 124 */           Long.toString(ieeeLongBits, 16));
/*     */     }
/*     */     
/* 127 */     boolean positive = ((ieeeLongBits & Long.MIN_VALUE) == 0L);
/*     */     
/* 129 */     if ((ieeeLongBits & Long.MAX_VALUE) == 0L) {
/*     */ 
/*     */       
/* 132 */       if (Trace.isOn) {
/* 133 */         Trace.exit("com.ibm.mq.MQS390FloatSupport", "doubleToS390LongBits(double)", 
/* 134 */             Long.valueOf(ieeeLongBits), 1);
/*     */       }
/* 136 */       return ieeeLongBits;
/*     */     } 
/*     */ 
/*     */     
/* 140 */     long exponent = ieeeLongBits & 0x7FF0000000000000L;
/*     */     
/* 142 */     exponent >>>= 52L;
/*     */     
/* 144 */     exponent -= 1022L;
/*     */ 
/*     */     
/* 147 */     long mantissa = ieeeLongBits & 0xFFFFFFFFFFFFFL;
/*     */ 
/*     */ 
/*     */     
/* 151 */     long remainder = Math.abs(exponent) % 4L;
/* 152 */     long quotient = Math.abs(exponent) / 4L;
/*     */     
/* 154 */     long s390Exponent = quotient;
/* 155 */     if (exponent > 0L && remainder != 0L) {
/* 156 */       s390Exponent++;
/*     */     }
/*     */ 
/*     */     
/* 160 */     if (exponent < 0L) {
/* 161 */       s390Exponent = -s390Exponent;
/*     */     }
/*     */ 
/*     */     
/* 165 */     s390Exponent += 64L;
/*     */ 
/*     */     
/* 168 */     long s390Mantissa = mantissa;
/*     */     
/* 170 */     if (exponent > -1022L) {
/* 171 */       s390Mantissa |= 0x10000000000000L;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 176 */       s390Mantissa <<= 1L;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 181 */     s390Mantissa <<= 3L;
/*     */     
/* 183 */     if (remainder > 0L) {
/* 184 */       if (exponent > 0L) {
/*     */         
/* 186 */         int shift_places = (int)(4L - remainder);
/* 187 */         s390Mantissa >>>= shift_places;
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 193 */         if (exponent == -1022L && (s390Mantissa & 0xF0000000000000L) == 0L) {
/* 194 */           s390Mantissa <<= 4L;
/* 195 */           s390Exponent--;
/*     */         } 
/*     */         
/* 198 */         s390Mantissa >>>= (int)remainder;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 207 */     if (exponent == -1022L) {
/* 208 */       while (s390Mantissa != 0L && (s390Mantissa & 0xF0000000000000L) == 0L) {
/* 209 */         s390Mantissa <<= 4L;
/* 210 */         s390Exponent--;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 216 */     if (s390Exponent > 127L) {
/* 217 */       if (Trace.isOn) {
/* 218 */         Trace.data("com.ibm.mq.MQS390FloatSupport", "doubleToS390LongBits(double)", "Exponent = " + s390Exponent);
/*     */       }
/*     */       
/* 221 */       IOException traceRet1 = new IOException("Number outside of range for double precision S/390 Float");
/* 222 */       if (Trace.isOn) {
/* 223 */         Trace.throwing("com.ibm.mq.MQS390FloatSupport", "doubleToS390LongBits(double)", traceRet1);
/*     */       }
/*     */       
/* 226 */       throw traceRet1;
/*     */     } 
/* 228 */     if (s390Exponent < 0L) {
/*     */       
/* 230 */       if (Trace.isOn) {
/* 231 */         Trace.data("com.ibm.mq.MQS390FloatSupport", "doubleToS390LongBits(double)", "Number too small to represent, rounding to zero");
/*     */       }
/*     */ 
/*     */       
/* 235 */       if (Trace.isOn) {
/* 236 */         Trace.exit("com.ibm.mq.MQS390FloatSupport", "doubleToS390LongBits(double)", 
/* 237 */             Long.valueOf(0L), 2);
/*     */       }
/* 239 */       return 0L;
/*     */     } 
/*     */ 
/*     */     
/* 243 */     long s390Double = 0L;
/* 244 */     long s390ExponentBits = s390Exponent & 0x7FL;
/*     */ 
/*     */     
/* 247 */     s390Double = s390ExponentBits << 56L;
/*     */     
/* 249 */     if (!positive) {
/* 250 */       s390Double |= Long.MIN_VALUE;
/*     */     }
/*     */     
/* 253 */     s390Double |= s390Mantissa;
/*     */     
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.data("com.ibm.mq.MQS390FloatSupport", "doubleToS390LongBits(double)", "S390 bit pattern = " + 
/*     */           
/* 258 */           Long.toString(s390Double, 16));
/*     */     }
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.exit("com.ibm.mq.MQS390FloatSupport", "doubleToS390LongBits(double)", 
/* 262 */           Long.valueOf(s390Double), 3);
/*     */     }
/* 264 */     return s390Double;
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
/*     */   protected static final double longS390BitsToDouble(long doubleBits) throws IOException {
/* 281 */     if (Trace.isOn)
/* 282 */       Trace.entry("com.ibm.mq.MQS390FloatSupport", "longS390BitsToDouble(long)", new Object[] {
/* 283 */             Long.valueOf(doubleBits)
/*     */           }); 
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.data("com.ibm.mq.MQS390FloatSupport", "longS390BitsToDouble(long)", "S390 bit pattern = " + 
/* 287 */           Long.toString(doubleBits, 16));
/*     */     }
/*     */     
/* 290 */     boolean positive = ((doubleBits & Long.MIN_VALUE) == 0L);
/*     */     
/* 292 */     if ((doubleBits & Long.MAX_VALUE) == 0L) {
/*     */       
/* 294 */       if (positive) {
/* 295 */         if (Trace.isOn) {
/* 296 */           Trace.exit("com.ibm.mq.MQS390FloatSupport", "longS390BitsToDouble(long)", 
/* 297 */               Double.valueOf(0.0D), 1);
/*     */         }
/* 299 */         return 0.0D;
/*     */       } 
/*     */       
/* 302 */       double traceRet1 = -0.0D;
/* 303 */       if (Trace.isOn) {
/* 304 */         Trace.exit("com.ibm.mq.MQS390FloatSupport", "longS390BitsToDouble(long)", 
/* 305 */             Double.valueOf(traceRet1), 2);
/*     */       }
/* 307 */       return traceRet1;
/*     */     } 
/*     */     
/* 310 */     long mantissa = doubleBits & 0xFFFFFFFFFFFFFFL;
/* 311 */     long exponent = doubleBits & 0x7F00000000000000L;
/*     */ 
/*     */     
/* 314 */     exponent >>= 56L;
/*     */     
/* 316 */     exponent -= 64L;
/*     */ 
/*     */     
/* 319 */     long ieeeExponent = exponent * 4L;
/*     */ 
/*     */     
/* 322 */     long ieeeMantissa = mantissa;
/*     */     
/* 324 */     ieeeMantissa >>= 3L;
/*     */ 
/*     */     
/* 327 */     if (ieeeExponent <= -1022L) {
/* 328 */       ieeeMantissa >>= 1L;
/*     */ 
/*     */       
/* 331 */       while (ieeeExponent < -1022L) {
/* 332 */         ieeeExponent++;
/* 333 */         ieeeMantissa >>= 1L;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 338 */     while (ieeeMantissa != 0L && (ieeeMantissa & 0x10000000000000L) == 0L && ieeeExponent > -1022L) {
/*     */       
/* 340 */       ieeeMantissa <<= 1L;
/* 341 */       ieeeExponent--;
/*     */     } 
/*     */ 
/*     */     
/* 345 */     if (ieeeExponent < -1045L) {
/* 346 */       if (Trace.isOn) {
/* 347 */         Trace.exit("com.ibm.mq.MQS390FloatSupport", "longS390BitsToDouble(long)", 
/* 348 */             Double.valueOf(0.0D), 3);
/*     */       }
/* 350 */       return 0.0D;
/*     */     } 
/*     */     
/* 353 */     if (ieeeExponent > 1024L) {
/* 354 */       if (positive) {
/* 355 */         if (Trace.isOn) {
/* 356 */           Trace.data("com.ibm.mq.MQS390FloatSupport", "longS390BitsToDouble(long)", "overflow - returning +INFINITY");
/*     */         }
/*     */         
/* 359 */         double traceRet2 = Double.POSITIVE_INFINITY;
/* 360 */         if (Trace.isOn) {
/* 361 */           Trace.exit("com.ibm.mq.MQS390FloatSupport", "longS390BitsToDouble(long)", 
/* 362 */               Double.valueOf(traceRet2), 4);
/*     */         }
/* 364 */         return traceRet2;
/*     */       } 
/*     */ 
/*     */       
/* 368 */       if (Trace.isOn) {
/* 369 */         Trace.data("com.ibm.mq.MQS390FloatSupport", "longS390BitsToDouble(long)", "overflow - returning +INFINITY");
/*     */       }
/*     */       
/* 372 */       double traceRet3 = Double.NEGATIVE_INFINITY;
/* 373 */       if (Trace.isOn) {
/* 374 */         Trace.exit("com.ibm.mq.MQS390FloatSupport", "longS390BitsToDouble(long)", 
/* 375 */             Double.valueOf(traceRet3), 5);
/*     */       }
/* 377 */       return traceRet3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 382 */     long ieeeBits = 0L;
/* 383 */     if (!positive) {
/* 384 */       ieeeBits |= Long.MIN_VALUE;
/*     */     }
/*     */ 
/*     */     
/* 388 */     ieeeExponent += 1022L;
/*     */     
/* 390 */     ieeeExponent <<= 52L;
/*     */     
/* 392 */     ieeeBits |= ieeeExponent;
/*     */ 
/*     */     
/* 395 */     ieeeMantissa &= 0xFFFFFFFFFFFFFL;
/*     */     
/* 397 */     ieeeBits |= ieeeMantissa;
/*     */     
/* 399 */     if (Trace.isOn) {
/* 400 */       Trace.data("com.ibm.mq.MQS390FloatSupport", "longS390BitsToDouble(long)", "IEEE bit pattern = " + 
/*     */           
/* 402 */           Long.toString(ieeeBits, 16));
/*     */     }
/* 404 */     double traceRet4 = Double.longBitsToDouble(ieeeBits);
/* 405 */     if (Trace.isOn) {
/* 406 */       Trace.exit("com.ibm.mq.MQS390FloatSupport", "longS390BitsToDouble(long)", 
/* 407 */           Double.valueOf(traceRet4), 6);
/*     */     }
/* 409 */     return traceRet4;
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
/*     */   protected static final int floatToS390IntBits(float ieeeFloat) throws IOException {
/* 458 */     if (Trace.isOn) {
/* 459 */       Trace.entry("com.ibm.mq.MQS390FloatSupport", "floatToS390IntBits(float)", new Object[] {
/* 460 */             Float.valueOf(ieeeFloat)
/*     */           });
/*     */     }
/*     */     
/* 464 */     int ieeeIntBits = Float.floatToIntBits(ieeeFloat);
/* 465 */     if (Trace.isOn) {
/* 466 */       Trace.data("com.ibm.mq.MQS390FloatSupport", "floatToS390IntBits(float)", "IEEE bit pattern = " + 
/* 467 */           Integer.toString(ieeeIntBits, 16));
/*     */     }
/*     */     
/* 470 */     boolean positive = ((ieeeIntBits & Integer.MIN_VALUE) == 0);
/*     */     
/* 472 */     if ((ieeeIntBits & Integer.MAX_VALUE) == 0) {
/*     */ 
/*     */       
/* 475 */       if (Trace.isOn) {
/* 476 */         Trace.exit("com.ibm.mq.MQS390FloatSupport", "floatToS390IntBits(float)", 
/* 477 */             Integer.valueOf(ieeeIntBits), 1);
/*     */       }
/* 479 */       return ieeeIntBits;
/*     */     } 
/*     */ 
/*     */     
/* 483 */     int exponent = ieeeIntBits & 0x7F800000;
/*     */     
/* 485 */     exponent >>>= 23;
/*     */     
/* 487 */     exponent -= 126;
/*     */ 
/*     */     
/* 490 */     int mantissa = ieeeIntBits & 0x7FFFFF;
/*     */     
/* 492 */     if (exponent > -126) {
/* 493 */       mantissa |= 0x800000;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 498 */     int remainder = Math.abs(exponent) % 4;
/* 499 */     int quotient = Math.abs(exponent) / 4;
/*     */     
/* 501 */     int s390Exponent = quotient;
/* 502 */     if (exponent > 0 && remainder != 0) {
/* 503 */       s390Exponent++;
/*     */     }
/*     */ 
/*     */     
/* 507 */     if (exponent < 0) {
/* 508 */       s390Exponent = -s390Exponent;
/*     */     }
/*     */ 
/*     */     
/* 512 */     s390Exponent += 64;
/*     */ 
/*     */     
/* 515 */     int s390Mantissa = mantissa;
/* 516 */     if (remainder > 0) {
/* 517 */       if (exponent > 0) {
/*     */         
/* 519 */         int shift_places = 4 - remainder;
/* 520 */         s390Mantissa >>>= shift_places;
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 526 */         if (exponent == -126 && (s390Mantissa & 0xF00000) == 0) {
/* 527 */           s390Mantissa <<= 4;
/* 528 */           s390Exponent--;
/*     */         } 
/*     */         
/* 531 */         int shift_places = remainder;
/* 532 */         s390Mantissa >>>= shift_places;
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
/* 545 */     if (exponent == -126) {
/* 546 */       s390Mantissa <<= 1;
/* 547 */       while (s390Mantissa != 0 && (s390Mantissa & 0xF00000) == 0) {
/* 548 */         s390Mantissa <<= 4;
/* 549 */         s390Exponent--;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 554 */     int s390Float = 0;
/* 555 */     int s390ExponentBits = s390Exponent & 0x7F;
/*     */     
/* 557 */     s390Float = s390ExponentBits << 24;
/*     */     
/* 559 */     if (!positive) {
/* 560 */       s390Float |= Integer.MIN_VALUE;
/*     */     }
/*     */     
/* 563 */     s390Float |= s390Mantissa;
/*     */     
/* 565 */     if (Trace.isOn) {
/* 566 */       Trace.data("com.ibm.mq.MQS390FloatSupport", "floatToS390IntBits(float)", "S390 Bit pattern = " + 
/* 567 */           Integer.toString(s390Float, 16));
/*     */     }
/* 569 */     if (Trace.isOn) {
/* 570 */       Trace.exit("com.ibm.mq.MQS390FloatSupport", "floatToS390IntBits(float)", 
/* 571 */           Integer.valueOf(s390Float), 2);
/*     */     }
/* 573 */     return s390Float;
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
/*     */   protected static final float intS390BitsToFloat(int floatBits) throws IOException {
/* 590 */     if (Trace.isOn)
/* 591 */       Trace.entry("com.ibm.mq.MQS390FloatSupport", "intS390BitsToFloat(int)", new Object[] {
/* 592 */             Integer.valueOf(floatBits)
/*     */           }); 
/* 594 */     if (Trace.isOn) {
/* 595 */       Trace.data("com.ibm.mq.MQS390FloatSupport", "intS390BitsToFloat(int)", "sccsid = @(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQS390FloatSupport.java");
/*     */     }
/*     */     
/* 598 */     boolean positive = ((floatBits & Integer.MIN_VALUE) == 0);
/*     */     
/* 600 */     if ((floatBits & Integer.MAX_VALUE) == 0) {
/*     */       
/* 602 */       if (positive) {
/*     */         
/* 604 */         if (Trace.isOn) {
/* 605 */           Trace.exit("com.ibm.mq.MQS390FloatSupport", "intS390BitsToFloat(int)", 
/* 606 */               Float.valueOf(0.0F), 1);
/*     */         }
/* 608 */         return 0.0F;
/*     */       } 
/*     */       
/* 611 */       float traceRet1 = -0.0F;
/* 612 */       if (Trace.isOn) {
/* 613 */         Trace.exit("com.ibm.mq.MQS390FloatSupport", "intS390BitsToFloat(int)", 
/* 614 */             Float.valueOf(traceRet1), 2);
/*     */       }
/* 616 */       return traceRet1;
/*     */     } 
/*     */     
/* 619 */     int mantissa = floatBits & 0xFFFFFF;
/* 620 */     int exponent = floatBits & 0x7F000000;
/*     */ 
/*     */     
/* 623 */     exponent >>= 24;
/*     */     
/* 625 */     exponent -= 64;
/*     */ 
/*     */     
/* 628 */     int ieeeExponent = exponent * 4;
/*     */ 
/*     */     
/* 631 */     int ieeeMantissa = mantissa;
/*     */     
/* 633 */     if (ieeeExponent <= -126) {
/*     */       
/* 635 */       ieeeMantissa >>= 1;
/*     */ 
/*     */       
/* 638 */       while (ieeeExponent < -126) {
/* 639 */         ieeeExponent++;
/* 640 */         ieeeMantissa >>= 1;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 645 */     while (ieeeMantissa != 0 && (ieeeMantissa & 0x800000) == 0 && ieeeExponent > -126) {
/*     */       
/* 647 */       ieeeMantissa <<= 1;
/* 648 */       ieeeExponent--;
/*     */     } 
/*     */ 
/*     */     
/* 652 */     if (ieeeExponent < -149) {
/* 653 */       if (Trace.isOn) {
/* 654 */         Trace.exit("com.ibm.mq.MQS390FloatSupport", "intS390BitsToFloat(int)", Float.valueOf(0.0F), 3);
/*     */       }
/*     */       
/* 657 */       return 0.0F;
/*     */     } 
/*     */     
/* 660 */     if (ieeeExponent > 128) {
/* 661 */       if (positive) {
/* 662 */         float traceRet2 = Float.POSITIVE_INFINITY;
/* 663 */         if (Trace.isOn) {
/* 664 */           Trace.exit("com.ibm.mq.MQS390FloatSupport", "intS390BitsToFloat(int)", 
/* 665 */               Float.valueOf(traceRet2), 4);
/*     */         }
/* 667 */         return traceRet2;
/*     */       } 
/*     */ 
/*     */       
/* 671 */       float traceRet3 = Float.NEGATIVE_INFINITY;
/* 672 */       if (Trace.isOn) {
/* 673 */         Trace.exit("com.ibm.mq.MQS390FloatSupport", "intS390BitsToFloat(int)", 
/* 674 */             Float.valueOf(traceRet3), 5);
/*     */       }
/* 676 */       return traceRet3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 681 */     int ieeeBits = 0;
/* 682 */     if (!positive) {
/* 683 */       ieeeBits |= Integer.MIN_VALUE;
/*     */     }
/*     */ 
/*     */     
/* 687 */     ieeeExponent += 126;
/*     */     
/* 689 */     ieeeExponent <<= 23;
/*     */     
/* 691 */     ieeeBits |= ieeeExponent;
/*     */ 
/*     */     
/* 694 */     ieeeMantissa &= 0x7FFFFF;
/*     */     
/* 696 */     ieeeBits |= ieeeMantissa;
/*     */     
/* 698 */     if (Trace.isOn) {
/* 699 */       Trace.data("com.ibm.mq.MQS390FloatSupport", "intS390BitsToFloat(int)", "IEEE Bit pattern = " + 
/* 700 */           Integer.toString(ieeeBits, 16));
/*     */     }
/* 702 */     float traceRet4 = Float.intBitsToFloat(ieeeBits);
/* 703 */     if (Trace.isOn) {
/* 704 */       Trace.exit("com.ibm.mq.MQS390FloatSupport", "intS390BitsToFloat(int)", 
/* 705 */           Float.valueOf(traceRet4), 6);
/*     */     }
/* 707 */     return traceRet4;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQS390FloatSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */