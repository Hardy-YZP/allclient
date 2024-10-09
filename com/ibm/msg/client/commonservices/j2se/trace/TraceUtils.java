/*     */ package com.ibm.msg.client.commonservices.j2se.trace;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TraceUtils
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/trace/TraceUtils.java";
/*  40 */   private static TraceUtils utils = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   HashMap<Class<?>, ObjectFormatAdapter> objectAdapters;
/*     */ 
/*     */ 
/*     */   
/*     */   ObjectFormatAdapter genericAdapter;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  55 */     utils = new TraceUtils();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private TraceUtils() {
/*  63 */     this.objectAdapters = new HashMap<>();
/*  64 */     this.objectAdapters.put(byte[].class, new ByteArrayFormatAdapter());
/*  65 */     this.objectAdapters.put(Integer.class, new IntegerFormatAdapter());
/*  66 */     this.objectAdapters.put(Long.class, new IntegerFormatAdapter());
/*     */     
/*  68 */     this.genericAdapter = new GenericFormatAdapter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static StringBuilder formatObject(Object input) {
/*  78 */     return formatObject(input, -1);
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
/*     */   public static StringBuilder formatObject(Object input, int maxLength) {
/*  92 */     ObjectFormatAdapter adapter = utils.objectAdapters.get(input.getClass());
/*  93 */     if (adapter == null) {
/*  94 */       adapter = utils.genericAdapter;
/*     */     }
/*     */     
/*  97 */     return adapter.formatObject(input, maxLength);
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
/*     */   public static StringBuilder formatObjectDetailed(Object input) {
/* 109 */     return formatObjectDetailed(input, -1);
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
/*     */   public static StringBuilder formatObjectDetailed(Object input, int maxLength) {
/* 125 */     ObjectFormatAdapter adapter = utils.objectAdapters.get(input.getClass());
/* 126 */     if (adapter == null) {
/* 127 */       adapter = utils.genericAdapter;
/*     */     }
/*     */     
/* 130 */     return adapter.formatObjectDetailed(input, maxLength);
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
/*     */   static StringBuilder bytesToHex(byte[] bArray, int maxLength) {
/* 144 */     StringBuilder hex = new StringBuilder();
/*     */ 
/*     */     
/* 147 */     if (bArray == null) {
/* 148 */       hex.append("<null>");
/* 149 */       return hex;
/*     */     } 
/*     */ 
/*     */     
/* 153 */     int dumpLength = bArray.length;
/* 154 */     if (maxLength != -1 && maxLength < dumpLength) {
/* 155 */       dumpLength = maxLength;
/*     */     }
/*     */     
/* 158 */     for (int i = 0; i < dumpLength; i++) {
/* 159 */       int binByte = bArray[i];
/*     */       
/* 161 */       if (binByte < 0) {
/* 162 */         binByte += 256;
/*     */       }
/* 164 */       if (binByte < 16) {
/* 165 */         hex.append('0');
/*     */       }
/*     */       
/* 168 */       hex.append(Integer.toHexString(binByte).toUpperCase());
/*     */     } 
/*     */     
/* 171 */     return hex;
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
/*     */   static StringBuilder bytesToHex(byte[] bArray) {
/* 185 */     return bytesToHex(bArray, -1);
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
/*     */   public static byte[] hexToBytes(String hex) throws Exception {
/* 201 */     byte[] out = null;
/*     */     
/* 203 */     if (hex != null) {
/* 204 */       int len = hex.length();
/*     */       
/* 206 */       if (len % 2 != 0) {
/* 207 */         throw new NumberFormatException();
/*     */       }
/*     */       
/* 210 */       len /= 2;
/* 211 */       out = new byte[len];
/*     */       
/* 213 */       for (int i = 0; i < len; i++) {
/* 214 */         int digit1 = Character.digit(hex.charAt(2 * i), 16);
/* 215 */         int digit2 = Character.digit(hex.charAt(2 * i + 1), 16);
/* 216 */         if (digit1 == -1 || digit2 == -1) {
/* 217 */           throw new NumberFormatException();
/*     */         }
/* 219 */         out[i] = (byte)(digit1 * 16 + digit2);
/*     */       } 
/*     */     } 
/*     */     
/* 223 */     return out;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean byteArraysEqual(byte[] a, byte[] b) {
/*     */     boolean result;
/* 235 */     if (a == b) {
/* 236 */       result = true;
/*     */     
/*     */     }
/* 239 */     else if (a.length != b.length) {
/* 240 */       result = false;
/*     */     } else {
/*     */       
/* 243 */       result = true;
/* 244 */       int len = a.length;
/* 245 */       for (int i = 0; i < len; i++) {
/* 246 */         if (a[i] != b[i]) {
/* 247 */           result = false;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 253 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static StringBuilder getFormattedHexBytes(byte[] toDump, int maxLength) {
/* 261 */     StringBuilder buffer = new StringBuilder();
/* 262 */     if (maxLength == 0) {
/* 263 */       return buffer;
/*     */     }
/*     */     
/* 266 */     int dumpLength = toDump.length;
/* 267 */     if (maxLength != -1 && maxLength < dumpLength) {
/* 268 */       dumpLength = maxLength;
/*     */     }
/*     */     
/* 271 */     for (int lineStart = 0; lineStart < dumpLength; lineStart += 16) {
/* 272 */       int lineEnd = Math.min(lineStart + 16, dumpLength);
/* 273 */       StringBuilder hex = new StringBuilder();
/* 274 */       StringBuilder ascii = new StringBuilder();
/* 275 */       for (int i = lineStart; i < lineEnd; i++) {
/* 276 */         int b = toDump[i];
/* 277 */         b = (b + 256) % 256;
/*     */ 
/*     */         
/* 280 */         int c1 = b / 16;
/* 281 */         int c2 = b % 16;
/* 282 */         hex.append((char)((c1 < 10) ? (48 + c1) : (97 + c1 - 10)));
/* 283 */         hex.append((char)((c2 < 10) ? (48 + c2) : (97 + c2 - 10)));
/*     */         
/* 285 */         if (i % 2 != 0) {
/* 286 */           hex.append(' ');
/*     */         }
/*     */ 
/*     */         
/* 290 */         if (b >= 32 && b <= 126) {
/* 291 */           ascii.append((char)b);
/*     */         } else {
/*     */           
/* 294 */           ascii.append('.');
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 300 */       int pad = 16 - lineEnd - lineStart;
/*     */ 
/*     */       
/* 303 */       int spaces = (pad * 5 + pad % 2) / 2;
/*     */       
/* 305 */       spaces += 3;
/*     */       
/* 307 */       for (int j = 0; j < spaces; j++) {
/* 308 */         hex.append(' ');
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 313 */       String offset = "0000" + Integer.toHexString(lineStart);
/* 314 */       offset = offset.substring(offset.length() - 4);
/* 315 */       buffer.append(offset);
/* 316 */       buffer.append(":  ");
/*     */ 
/*     */       
/* 319 */       buffer.append(hex.toString());
/* 320 */       buffer.append(ascii.toString());
/* 321 */       buffer.append('\n');
/*     */     } 
/*     */     
/* 324 */     return buffer;
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
/*     */   public static String foldPackageName(String qualifiedClassName) {
/*     */     StringBuilder result;
/* 339 */     if (qualifiedClassName == null) {
/* 340 */       return "<Unknown>";
/*     */     }
/*     */     
/* 343 */     String[] elements = qualifiedClassName.split("\\.");
/* 344 */     if (elements.length > 3) {
/* 345 */       result = new StringBuilder(64);
/*     */       
/* 347 */       int idx = 0;
/* 348 */       for (idx = 0; idx < elements.length; idx++) {
/* 349 */         if (0 != idx) {
/* 350 */           result.append(".");
/*     */         }
/*     */         
/* 353 */         if (idx < elements.length - 3) {
/* 354 */           result.append(elements[idx].charAt(0));
/*     */         } else {
/*     */           
/* 357 */           result.append(elements[idx]);
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 362 */       result = new StringBuilder(qualifiedClassName);
/*     */     } 
/* 364 */     return result.toString();
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
/*     */   public static String objectToString(Object obj, String parentClassName) {
/*     */     StringBuilder result;
/* 384 */     if (obj == null) {
/* 385 */       return parentClassName;
/*     */     }
/*     */     
/* 388 */     String objectClassName = obj.getClass().getName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 394 */     String[] elements = objectClassName.split("\\.");
/* 395 */     if (elements.length > 3) {
/* 396 */       result = new StringBuilder(64);
/*     */       
/* 398 */       int idx = 0;
/* 399 */       for (idx = 0; idx < elements.length; idx++) {
/* 400 */         if (0 != idx) {
/* 401 */           result.append(".");
/*     */         }
/*     */         
/* 404 */         if (idx < elements.length - 3) {
/* 405 */           result.append(elements[idx].charAt(0));
/*     */         } else {
/*     */           
/* 408 */           result.append(elements[idx]);
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 413 */       result = new StringBuilder(objectClassName);
/*     */     } 
/*     */     
/* 416 */     if (null != parentClassName && !result.toString().equals(parentClassName) && objectClassName.indexOf('$') == -1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 423 */       int pIndex = parentClassName.lastIndexOf(".");
/* 424 */       result.append("(");
/* 425 */       result.append(parentClassName.substring(pIndex + 1));
/* 426 */       result.append(")");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 432 */     result.append("@");
/* 433 */     result.append(Integer.toHexString(System.identityHashCode(obj)));
/*     */     
/* 435 */     return result.toString();
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
/*     */   abstract class ObjectFormatAdapter
/*     */   {
/*     */     abstract StringBuilder formatObject(Object param1Object, int param1Int) throws ClassCastException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     StringBuilder formatObjectDetailed(Object input, int maxLength) throws ClassCastException {
/* 471 */       return formatObject(input, maxLength);
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
/*     */   class GenericFormatAdapter
/*     */     extends ObjectFormatAdapter
/*     */   {
/*     */     StringBuilder formatObject(Object input, int maxLength) throws ClassCastException {
/* 486 */       StringBuilder output = new StringBuilder();
/*     */       
/* 488 */       if (maxLength == 0) {
/* 489 */         return output;
/*     */       }
/*     */       
/* 492 */       output.append(String.valueOf(input));
/*     */       
/* 494 */       if (maxLength < 0 || output.length() <= maxLength) {
/* 495 */         return output;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 500 */       String fullOutput = output.toString();
/* 501 */       fullOutput = fullOutput.subSequence(0, maxLength - 1).toString();
/* 502 */       output = new StringBuilder(fullOutput);
/* 503 */       return output;
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
/*     */   class IntegerFormatAdapter
/*     */     extends ObjectFormatAdapter
/*     */   {
/*     */     StringBuilder formatObject(Object input, int maxLength) throws ClassCastException {
/* 520 */       long value = 0L;
/* 521 */       if (input instanceof Integer) {
/* 522 */         value = ((Integer)input).longValue();
/*     */       } else {
/*     */         
/* 525 */         value = ((Long)input).longValue();
/*     */       } 
/* 527 */       StringBuilder output = new StringBuilder();
/*     */       
/* 529 */       if (maxLength == 0) {
/* 530 */         return output;
/*     */       }
/*     */       
/* 533 */       output.append(Long.toString(value));
/* 534 */       String hexRepresentation = "(0x" + Long.toHexString(value) + ")";
/* 535 */       if (maxLength == -1 || output.length() + hexRepresentation.length() <= maxLength) {
/* 536 */         output.append(hexRepresentation);
/*     */       }
/*     */       
/* 539 */       return output;
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
/*     */   class ByteArrayFormatAdapter
/*     */     extends ObjectFormatAdapter
/*     */   {
/*     */     StringBuilder formatObject(Object input, int maxLength) throws ClassCastException {
/* 561 */       byte[] bytes = (byte[])input;
/*     */       
/* 563 */       StringBuilder output = TraceUtils.bytesToHex(bytes, maxLength);
/* 564 */       return output;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     StringBuilder formatObjectDetailed(Object input, int maxLength) throws ClassCastException {
/* 574 */       byte[] bytes = (byte[])input;
/*     */       
/* 576 */       StringBuilder output = TraceUtils.getFormattedHexBytes(bytes, maxLength);
/* 577 */       return output;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\trace\TraceUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */