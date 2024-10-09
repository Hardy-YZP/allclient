/*     */ package com.ibm.mq.headers.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.charset.Charset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class HexString
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/HexString.java";
/*     */   protected static final char NON_PRINTABLE = '.';
/*     */   
/*     */   static {
/*  42 */     if (Trace.isOn) {
/*  43 */       Trace.data("com.ibm.mq.headers.internal.HexString", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/HexString.java");
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
/*     */   public static void main(String[] args) {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "main(String [ ])", new Object[] { args });
/*     */     }
/*     */     
/*  60 */     if (args.length == 1) {
/*  61 */       byte[] bytes = parseHex(args[0]);
/*     */       
/*  63 */       System.out.println("0x" + hexString(bytes, ' ', 4));
/*     */     } else {
/*  65 */       System.out.println("Usage: java " + HexString.class.getName() + " string");
/*     */     } 
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "main(String [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static final char getChar(int b) {
/*  74 */     if (Trace.isOn)
/*  75 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "getChar(int)", new Object[] {
/*  76 */             Integer.valueOf(b)
/*     */           }); 
/*  78 */     char traceRet1 = Character.forDigit(b, 16);
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "getChar(int)", 
/*  81 */           Character.valueOf(traceRet1));
/*     */     }
/*  83 */     return traceRet1;
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
/*     */   public static byte[] parseHex(String stringP) throws NumberFormatException {
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "parseHex(String)", new Object[] { stringP });
/*     */     }
/*     */     
/*  99 */     String string = stringP;
/* 100 */     byte[] bytes = new byte[(string = prepare(string)).length() >> 1];
/* 101 */     int c = 0;
/*     */     
/* 103 */     for (int i = 0; i < bytes.length; i++) {
/* 104 */       int msd = Character.digit(string.charAt(c++), 16);
/* 105 */       int lsd = Character.digit(string.charAt(c++), 16);
/*     */       
/* 107 */       if (msd < 0 || lsd < 0) {
/*     */         
/* 109 */         NumberFormatException traceRet1 = new NumberFormatException(HexString.class.getName() + ": Non-hexadecimal character in input");
/* 110 */         if (Trace.isOn) {
/* 111 */           Trace.throwing("com.ibm.mq.headers.internal.HexString", "parseHex(String)", traceRet1);
/*     */         }
/*     */         
/* 114 */         throw traceRet1;
/*     */       } 
/*     */ 
/*     */       
/* 118 */       bytes[i] = (byte)(msd << 4 | lsd);
/*     */     } 
/*     */     
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "parseHex(String)", bytes);
/*     */     }
/* 124 */     return bytes;
/*     */   }
/*     */   
/*     */   static String prepare(String string) {
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "prepare(String)", new Object[] { string });
/*     */     }
/*     */     
/* 132 */     char[] chars = new char[string.length()];
/* 133 */     int s = 0;
/* 134 */     int i = 0;
/*     */ 
/*     */     
/* 137 */     if (string.startsWith("0x") || string.startsWith("0X")) {
/* 138 */       s += 2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 143 */     while (s < chars.length) {
/* 144 */       char c; if (!Character.isWhitespace(c = string.charAt(s))) {
/* 145 */         chars[i++] = c;
/*     */       }
/*     */       
/* 148 */       s++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 153 */     if ((i & 0x1) == 1) {
/* 154 */       String traceRet1 = "0" + new String(chars, 0, i);
/* 155 */       if (Trace.isOn) {
/* 156 */         Trace.exit("com.ibm.mq.headers.internal.HexString", "prepare(String)", traceRet1, 1);
/*     */       }
/*     */       
/* 159 */       return traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 163 */     String traceRet2 = new String(chars, 0, i);
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "prepare(String)", traceRet2, 2);
/*     */     }
/*     */     
/* 168 */     return traceRet2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String hexString(char c) {
/* 176 */     if (Trace.isOn)
/* 177 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "hexString(char)", new Object[] {
/* 178 */             Character.valueOf(c)
/*     */           }); 
/* 180 */     StringBuilder sb = new StringBuilder(4);
/*     */     
/* 182 */     sb.append(getChar(c >> 12 & 0xF));
/* 183 */     sb.append(getChar(c >> 8 & 0xF));
/* 184 */     sb.append(getChar(c >> 4 & 0xF));
/* 185 */     sb.append(getChar(c & 0xF));
/*     */     
/* 187 */     String traceRet1 = new String(sb);
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "hexString(char)", traceRet1);
/*     */     }
/* 191 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String hexString(byte b) {
/* 199 */     if (Trace.isOn)
/* 200 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "hexString(byte)", new Object[] {
/* 201 */             Byte.valueOf(b)
/*     */           }); 
/* 203 */     StringBuilder sb = new StringBuilder(2);
/*     */     
/* 205 */     sb.append(getChar(b >> 4 & 0xF));
/* 206 */     sb.append(getChar(b & 0xF));
/*     */     
/* 208 */     String traceRet1 = new String(sb);
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "hexString(byte)", traceRet1);
/*     */     }
/* 212 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String hexString(short s) {
/* 220 */     if (Trace.isOn)
/* 221 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "hexString(short)", new Object[] {
/* 222 */             Short.valueOf(s)
/*     */           }); 
/* 224 */     StringBuilder sb = new StringBuilder(4);
/*     */     
/* 226 */     sb.append(getChar(s >> 12 & 0xF));
/* 227 */     sb.append(getChar(s >> 8 & 0xF));
/* 228 */     sb.append(getChar(s >> 4 & 0xF));
/* 229 */     sb.append(getChar(s & 0xF));
/*     */     
/* 231 */     String traceRet1 = new String(sb);
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "hexString(short)", traceRet1);
/*     */     }
/* 235 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String hexString(int i) {
/* 243 */     if (Trace.isOn)
/* 244 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "hexString(int)", new Object[] {
/* 245 */             Integer.valueOf(i)
/*     */           }); 
/* 247 */     StringBuilder sb = new StringBuilder(8);
/*     */     
/* 249 */     sb.append(getChar(i >> 28 & 0xF));
/* 250 */     sb.append(getChar(i >> 24 & 0xF));
/* 251 */     sb.append(getChar(i >> 20 & 0xF));
/* 252 */     sb.append(getChar(i >> 16 & 0xF));
/* 253 */     sb.append(getChar(i >> 12 & 0xF));
/* 254 */     sb.append(getChar(i >> 8 & 0xF));
/* 255 */     sb.append(getChar(i >> 4 & 0xF));
/* 256 */     sb.append(getChar(i & 0xF));
/*     */     
/* 258 */     String traceRet1 = new String(sb);
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "hexString(int)", traceRet1);
/*     */     }
/* 262 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String hexString(long l) {
/* 270 */     if (Trace.isOn)
/* 271 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "hexString(long)", new Object[] {
/* 272 */             Long.valueOf(l)
/*     */           }); 
/* 274 */     StringBuilder sb = new StringBuilder(16);
/*     */     
/* 276 */     sb.append(getChar((int)(l >> 60L & 0xFL)));
/* 277 */     sb.append(getChar((int)(l >> 56L & 0xFL)));
/* 278 */     sb.append(getChar((int)(l >> 52L & 0xFL)));
/* 279 */     sb.append(getChar((int)(l >> 48L & 0xFL)));
/* 280 */     sb.append(getChar((int)(l >> 44L & 0xFL)));
/* 281 */     sb.append(getChar((int)(l >> 40L & 0xFL)));
/* 282 */     sb.append(getChar((int)(l >> 36L & 0xFL)));
/* 283 */     sb.append(getChar((int)(l >> 32L & 0xFL)));
/* 284 */     sb.append(getChar((int)(l >> 28L & 0xFL)));
/* 285 */     sb.append(getChar((int)(l >> 24L & 0xFL)));
/* 286 */     sb.append(getChar((int)(l >> 20L & 0xFL)));
/* 287 */     sb.append(getChar((int)(l >> 16L & 0xFL)));
/* 288 */     sb.append(getChar((int)(l >> 12L & 0xFL)));
/* 289 */     sb.append(getChar((int)(l >> 8L & 0xFL)));
/* 290 */     sb.append(getChar((int)(l >> 4L & 0xFL)));
/* 291 */     sb.append(getChar((int)(l & 0xFL)));
/*     */     
/* 293 */     String traceRet1 = new String(sb);
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "hexString(long)", traceRet1);
/*     */     }
/* 297 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String hexString(byte[] bytes, char separator, int interval) {
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "hexString(byte [ ],char,int)", new Object[] { bytes, 
/* 309 */             Character.valueOf(separator), Integer.valueOf(interval) });
/*     */     }
/* 311 */     String traceRet1 = hexString(bytes, 0, bytes.length, separator, interval);
/* 312 */     if (Trace.isOn) {
/* 313 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "hexString(byte [ ],char,int)", traceRet1);
/*     */     }
/*     */     
/* 316 */     return traceRet1;
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
/*     */   public static String hexString(byte[] bytes, int offset, int lengthP, char separator, int interval) {
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "hexString(byte [ ],int,int,char,int)", new Object[] { bytes, 
/* 330 */             Integer.valueOf(offset), Integer.valueOf(lengthP), 
/* 331 */             Character.valueOf(separator), Integer.valueOf(interval) });
/*     */     }
/* 333 */     int length = lengthP;
/* 334 */     StringBuilder sb = new StringBuilder(length * 2 + length % interval);
/*     */     
/* 336 */     length += offset;
/*     */     
/* 338 */     for (int i = offset; i < length; i++) {
/* 339 */       if (i % interval == 0 && i > 0) {
/* 340 */         sb.append(separator);
/*     */       }
/*     */       
/* 343 */       sb.append(getChar(bytes[i] >>> 4 & 0xF));
/* 344 */       sb.append(getChar(bytes[i] & 0xF));
/*     */     } 
/*     */     
/* 347 */     String traceRet1 = new String(sb);
/* 348 */     if (Trace.isOn) {
/* 349 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "hexString(byte [ ],int,int,char,int)", traceRet1);
/*     */     }
/*     */     
/* 352 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String hexString(byte[] bytes) {
/* 360 */     if (Trace.isOn) {
/* 361 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "hexString(byte [ ])", new Object[] { bytes });
/*     */     }
/*     */     
/* 364 */     String traceRet1 = hexString(bytes, 0, bytes.length);
/* 365 */     if (Trace.isOn) {
/* 366 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "hexString(byte [ ])", traceRet1);
/*     */     }
/*     */     
/* 369 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String hexString(byte[] bytes, int offset, int lengthP) {
/* 379 */     if (Trace.isOn) {
/* 380 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "hexString(byte [ ],int,int)", new Object[] { bytes, 
/* 381 */             Integer.valueOf(offset), Integer.valueOf(lengthP) });
/*     */     }
/* 383 */     int length = lengthP;
/* 384 */     StringBuilder sb = new StringBuilder(length * 2);
/*     */     
/* 386 */     length += offset;
/*     */     
/* 388 */     for (int i = offset; i < length; i++) {
/* 389 */       sb.append(getChar(bytes[i] >> 4 & 0xF));
/* 390 */       sb.append(getChar(bytes[i] & 0xF));
/*     */     } 
/*     */     
/* 393 */     String traceRet1 = new String(sb);
/* 394 */     if (Trace.isOn) {
/* 395 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "hexString(byte [ ],int,int)", traceRet1);
/*     */     }
/*     */     
/* 398 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String hexString(String string) {
/* 406 */     if (Trace.isOn) {
/* 407 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "hexString(String)", new Object[] { string });
/*     */     }
/*     */     
/* 410 */     String traceRet1 = hexString(string.getBytes(Charset.defaultCharset()));
/* 411 */     if (Trace.isOn) {
/* 412 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "hexString(String)", traceRet1);
/*     */     }
/*     */     
/* 415 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String printPrintableChars(byte[] bytes) {
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "printPrintableChars(byte [ ])", new Object[] { bytes });
/*     */     }
/*     */     
/* 427 */     String traceRet1 = printPrintableChars(bytes, 0, bytes.length);
/* 428 */     if (Trace.isOn) {
/* 429 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "printPrintableChars(byte [ ])", traceRet1);
/*     */     }
/*     */     
/* 432 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String printPrintableChars(byte[] bytes, int offset, int length) {
/* 442 */     if (Trace.isOn) {
/* 443 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "printPrintableChars(byte [ ],int,int)", new Object[] { bytes, 
/* 444 */             Integer.valueOf(offset), Integer.valueOf(length) });
/*     */     }
/* 446 */     char[] chars = new char[length];
/*     */     
/* 448 */     for (int i = offset; i < length; i++) {
/*     */       
/* 450 */       if (bytes[i] < 32 || (bytes[i] & 0xFF) > 127) {
/* 451 */         chars[i] = '.';
/*     */       } else {
/* 453 */         chars[i] = (char)bytes[i];
/*     */       } 
/*     */     } 
/*     */     
/* 457 */     String traceRet1 = new String(chars);
/* 458 */     if (Trace.isOn) {
/* 459 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "printPrintableChars(byte [ ],int,int)", traceRet1);
/*     */     }
/*     */     
/* 462 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int displayHexStream(InputStream input, OutputStream output) throws IOException {
/* 472 */     if (Trace.isOn) {
/* 473 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "displayHexStream(InputStream,OutputStream)", new Object[] { input, output });
/*     */     }
/*     */     
/* 476 */     int traceRet1 = displayHexStream(input, output, false);
/* 477 */     if (Trace.isOn) {
/* 478 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "displayHexStream(InputStream,OutputStream)", 
/* 479 */           Integer.valueOf(traceRet1));
/*     */     }
/* 481 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int displayHexStream(InputStream input, OutputStream output, boolean indexed) throws IOException {
/* 492 */     if (Trace.isOn) {
/* 493 */       Trace.entry("com.ibm.mq.headers.internal.HexString", "displayHexStream(InputStream,OutputStream,boolean)", new Object[] { input, output, 
/*     */             
/* 495 */             Boolean.valueOf(indexed) });
/*     */     }
/* 497 */     int bufferSize = 20, bytes = 0, total = 0;
/* 498 */     byte[] buffer = new byte[bufferSize];
/* 499 */     byte[] newline = (File.separatorChar == '\\') ? "\r\n".getBytes(Charset.defaultCharset()) : "\n".getBytes(Charset.defaultCharset());
/*     */     
/* 501 */     while ((bytes = input.read(buffer)) >= 0) {
/* 502 */       if (indexed) {
/* 503 */         output.write(PaddedString.lead(total, '0', 10).getBytes(Charset.defaultCharset()));
/* 504 */         output.write(58);
/* 505 */         output.write(32);
/*     */       } 
/*     */       
/* 508 */       if (bytes == bufferSize) {
/* 509 */         output.write(hexString(buffer, ' ', 4).getBytes(Charset.defaultCharset()));
/*     */       } else {
/* 511 */         output.write(PaddedString.pad(hexString(buffer, 0, bytes, ' ', 4), 44).getBytes(Charset.defaultCharset()));
/*     */       } 
/*     */       
/* 514 */       output.write(32);
/* 515 */       output.write(39);
/* 516 */       output.write(printPrintableChars(buffer, 0, bytes).getBytes(Charset.defaultCharset()));
/* 517 */       output.write(39);
/* 518 */       output.write(newline);
/*     */       
/* 520 */       total += bytes;
/*     */     } 
/*     */     
/* 523 */     if (Trace.isOn) {
/* 524 */       Trace.exit("com.ibm.mq.headers.internal.HexString", "displayHexStream(InputStream,OutputStream,boolean)", 
/* 525 */           Integer.valueOf(total));
/*     */     }
/* 527 */     return total;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\HexString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */