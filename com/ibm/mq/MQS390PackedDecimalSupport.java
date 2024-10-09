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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MQS390PackedDecimalSupport
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQS390PackedDecimalSupport.java";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.MQS390PackedDecimalSupport", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQS390PackedDecimalSupport.java");
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
/*     */   public MQS390PackedDecimalSupport() {
/*  62 */     super(MQSESSION.getJmqiEnv());
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.entry(this, "com.ibm.mq.MQS390PackedDecimalSupport", "<init>()");
/*     */     }
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.exit(this, "com.ibm.mq.MQS390PackedDecimalSupport", "<init>()");
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
/*     */   protected static final long convertToBinary(byte[] packedDecimalBytes) throws IOException {
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry("com.ibm.mq.MQS390PackedDecimalSupport", "convertToBinary(byte [ ])", new Object[] { packedDecimalBytes });
/*     */     }
/*     */ 
/*     */     
/*  96 */     long retVal = 0L;
/*  97 */     long multiplier = 1L;
/*  98 */     boolean positive = true;
/*     */ 
/*     */     
/* 101 */     for (int b = packedDecimalBytes.length - 1; b >= 0; b--) {
/* 102 */       byte thisByte = packedDecimalBytes[b];
/* 103 */       int leastSignificantDigit = thisByte & 0xF;
/* 104 */       int mostSignificantDigit = thisByte >>> 4 & 0xF;
/*     */ 
/*     */ 
/*     */       
/* 108 */       if (b == packedDecimalBytes.length - 1) {
/* 109 */         IOException traceRet1; switch (leastSignificantDigit) {
/*     */           case 12:
/* 111 */             positive = true;
/*     */             break;
/*     */           case 13:
/* 114 */             positive = false;
/*     */             break;
/*     */           
/*     */           case 15:
/* 118 */             positive = true;
/*     */             break;
/*     */           
/*     */           default:
/* 122 */             traceRet1 = new IOException("Invalid sign nibble in packed decimal");
/* 123 */             if (Trace.isOn) {
/* 124 */               Trace.throwing("com.ibm.mq.MQS390PackedDecimalSupport", "convertToBinary(byte [ ])", traceRet1, 1);
/*     */             }
/*     */             
/* 127 */             throw traceRet1;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 133 */         leastSignificantDigit = mostSignificantDigit;
/* 134 */         mostSignificantDigit = 0;
/*     */       } 
/*     */ 
/*     */       
/* 138 */       if (leastSignificantDigit > 9 || mostSignificantDigit > 9) {
/* 139 */         IOException traceRet2 = new IOException("Packed Decimal digit outside of range 0-9");
/* 140 */         if (Trace.isOn) {
/* 141 */           Trace.throwing("com.ibm.mq.MQS390PackedDecimalSupport", "convertToBinary(byte [ ])", traceRet2, 2);
/*     */         }
/*     */         
/* 144 */         throw traceRet2;
/*     */       } 
/*     */       
/* 147 */       int binaryValue = leastSignificantDigit + 10 * mostSignificantDigit;
/* 148 */       retVal += multiplier * binaryValue;
/*     */       
/* 150 */       if (b == packedDecimalBytes.length - 1) {
/* 151 */         multiplier *= 10L;
/*     */       } else {
/* 153 */         multiplier *= 100L;
/*     */       } 
/*     */     } 
/*     */     
/* 157 */     if (!positive) {
/* 158 */       retVal = -retVal;
/*     */     }
/*     */     
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.exit("com.ibm.mq.MQS390PackedDecimalSupport", "convertToBinary(byte [ ])", 
/* 163 */           Long.valueOf(retVal));
/*     */     }
/* 165 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final byte[] convertToPackedDecimal(short binaryP) throws IOException {
/*     */     byte signByte;
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.entry("com.ibm.mq.MQS390PackedDecimalSupport", "convertToPackedDecimal(short)", new Object[] {
/* 179 */             Short.valueOf(binaryP)
/*     */           });
/*     */     }
/* 182 */     if (binaryP > 999 || binaryP < -999) {
/* 183 */       IOException traceRet1 = new IOException("Outside of range for short packed decimal (+/-999)");
/*     */       
/* 185 */       if (Trace.isOn) {
/* 186 */         Trace.throwing("com.ibm.mq.MQS390PackedDecimalSupport", "convertToPackedDecimal(short)", traceRet1);
/*     */       }
/*     */       
/* 189 */       throw traceRet1;
/*     */     } 
/*     */     
/* 192 */     short binary = binaryP;
/*     */     
/* 194 */     byte[] retVal = new byte[2];
/* 195 */     boolean positive = (binary >= 0);
/*     */ 
/*     */     
/* 198 */     int decimalDigit = Math.abs(binary % 10);
/* 199 */     binary = (short)(binary / 10);
/*     */     
/* 201 */     if (positive) {
/* 202 */       signByte = (byte)(decimalDigit << 4 | 0xC);
/*     */     } else {
/* 204 */       signByte = (byte)(decimalDigit << 4 | 0xD);
/*     */     } 
/* 206 */     retVal[1] = signByte;
/*     */     
/* 208 */     for (int byteIndex = 0; byteIndex >= 0; byteIndex--) {
/* 209 */       int leastSignificantDigit = Math.abs(binary % 10);
/* 210 */       binary = (short)(binary / 10);
/* 211 */       int mostSignificantDigit = Math.abs(binary % 10);
/* 212 */       binary = (short)(binary / 10);
/* 213 */       byte thisByte = (byte)(leastSignificantDigit | mostSignificantDigit << 4);
/* 214 */       retVal[byteIndex] = thisByte;
/*     */     } 
/*     */     
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.exit("com.ibm.mq.MQS390PackedDecimalSupport", "convertToPackedDecimal(short)", retVal);
/*     */     }
/*     */     
/* 221 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final byte[] convertToPackedDecimal(int binaryP) throws IOException {
/*     */     byte signByte;
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.entry("com.ibm.mq.MQS390PackedDecimalSupport", "convertToPackedDecimal(int)", new Object[] {
/* 234 */             Integer.valueOf(binaryP)
/*     */           });
/*     */     }
/* 237 */     if (binaryP > 9999999 || binaryP < -9999999) {
/* 238 */       IOException traceRet1 = new IOException("Outside of range for integer packed decimal (+/-9999999)");
/*     */       
/* 240 */       if (Trace.isOn) {
/* 241 */         Trace.throwing("com.ibm.mq.MQS390PackedDecimalSupport", "convertToPackedDecimal(int)", traceRet1);
/*     */       }
/*     */       
/* 244 */       throw traceRet1;
/*     */     } 
/*     */     
/* 247 */     int binary = binaryP;
/* 248 */     byte[] retVal = new byte[4];
/* 249 */     boolean positive = (binary >= 0);
/*     */ 
/*     */     
/* 252 */     int decimalDigit = Math.abs(binary % 10);
/* 253 */     binary /= 10;
/*     */     
/* 255 */     if (positive) {
/* 256 */       signByte = (byte)(decimalDigit << 4 | 0xC);
/*     */     } else {
/* 258 */       signByte = (byte)(decimalDigit << 4 | 0xD);
/*     */     } 
/* 260 */     retVal[3] = signByte;
/*     */     
/* 262 */     for (int byteIndex = 2; byteIndex >= 0; byteIndex--) {
/* 263 */       int leastSignificantDigit = Math.abs(binary % 10);
/* 264 */       binary /= 10;
/* 265 */       int mostSignificantDigit = Math.abs(binary % 10);
/* 266 */       binary /= 10;
/* 267 */       byte thisByte = (byte)(leastSignificantDigit | mostSignificantDigit << 4);
/* 268 */       retVal[byteIndex] = thisByte;
/*     */     } 
/*     */     
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.exit("com.ibm.mq.MQS390PackedDecimalSupport", "convertToPackedDecimal(int)", retVal);
/*     */     }
/* 274 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final byte[] convertToPackedDecimal(long binaryP) throws IOException {
/*     */     byte signByte;
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.entry("com.ibm.mq.MQS390PackedDecimalSupport", "convertToPackedDecimal(long)", new Object[] {
/* 287 */             Long.valueOf(binaryP)
/*     */           });
/*     */     }
/* 290 */     if (binaryP > 999999999999999L || binaryP < -999999999999999L) {
/* 291 */       IOException traceRet1 = new IOException("Outside of range for long packed decimal (0+/-999999999999999)");
/*     */       
/* 293 */       if (Trace.isOn) {
/* 294 */         Trace.throwing("com.ibm.mq.MQS390PackedDecimalSupport", "convertToPackedDecimal(long)", traceRet1);
/*     */       }
/*     */       
/* 297 */       throw traceRet1;
/*     */     } 
/*     */     
/* 300 */     long binary = binaryP;
/*     */     
/* 302 */     byte[] retVal = new byte[8];
/* 303 */     boolean positive = (binary >= 0L);
/*     */ 
/*     */     
/* 306 */     int decimalDigit = (int)Math.abs(binary % 10L);
/* 307 */     binary /= 10L;
/*     */     
/* 309 */     if (positive) {
/* 310 */       signByte = (byte)(decimalDigit << 4 | 0xC);
/*     */     } else {
/* 312 */       signByte = (byte)(decimalDigit << 4 | 0xD);
/*     */     } 
/* 314 */     retVal[7] = signByte;
/*     */     
/* 316 */     for (int byteIndex = 6; byteIndex >= 0; byteIndex--) {
/* 317 */       int leastSignificantDigit = (int)Math.abs(binary % 10L);
/* 318 */       binary /= 10L;
/* 319 */       int mostSignificantDigit = (int)Math.abs(binary % 10L);
/* 320 */       binary /= 10L;
/* 321 */       byte thisByte = (byte)(leastSignificantDigit | mostSignificantDigit << 4);
/* 322 */       retVal[byteIndex] = thisByte;
/*     */     } 
/*     */     
/* 325 */     if (Trace.isOn) {
/* 326 */       Trace.exit("com.ibm.mq.MQS390PackedDecimalSupport", "convertToPackedDecimal(long)", retVal);
/*     */     }
/* 328 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQS390PackedDecimalSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */