/*     */ package com.ibm.msg.client.commonservices;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.lang.management.ManagementFactory;
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
/*     */ public class Utils
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/Utils.java";
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.msg.client.commonservices.Utils", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/Utils.java");
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
/*  53 */   private static Utils utils = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HashMap<Class<?>, ObjectFormatAdapter> objectAdapters;
/*     */ 
/*     */ 
/*     */   
/*     */   private ObjectFormatAdapter genericAdapter;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.entry("com.ibm.msg.client.commonservices.Utils", "static()");
/*     */     }
/*  71 */     utils = new Utils();
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.exit("com.ibm.msg.client.commonservices.Utils", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Utils() {
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.entry(this, "com.ibm.msg.client.commonservices.Utils", "<init>()");
/*     */     }
/*  85 */     this.objectAdapters = new HashMap<>();
/*  86 */     this.objectAdapters.put(byte[].class, new ByteArrayFormatAdapter());
/*  87 */     this.genericAdapter = new GenericFormatAdapter();
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit(this, "com.ibm.msg.client.commonservices.Utils", "<init>()");
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
/*     */   public static StringBuffer formatObject(Object input) {
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.entry("com.ibm.msg.client.commonservices.Utils", "formatObject(Object)", new Object[] { input });
/*     */     }
/*     */     
/* 105 */     StringBuffer retVal = formatObject(input, -1);
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.exit("com.ibm.msg.client.commonservices.Utils", "formatObject(Object)", retVal);
/*     */     }
/* 109 */     return retVal;
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
/*     */   public static StringBuffer formatObject(Object input, int maxLength) {
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.entry("com.ibm.msg.client.commonservices.Utils", "formatObject(Object,int)", new Object[] { input, 
/* 125 */             Integer.valueOf(maxLength) });
/*     */     }
/* 127 */     ObjectFormatAdapter adapter = utils.objectAdapters.get(input.getClass());
/* 128 */     if (adapter == null) {
/* 129 */       adapter = utils.genericAdapter;
/*     */     }
/*     */     
/* 132 */     StringBuffer retVal = adapter.formatObject(input, maxLength);
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.exit("com.ibm.msg.client.commonservices.Utils", "formatObject(Object,int)", retVal);
/*     */     }
/* 136 */     return retVal;
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
/*     */   public static StringBuffer formatObjectDetailed(Object input) {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.entry("com.ibm.msg.client.commonservices.Utils", "formatObjectDetailed(Object)", new Object[] { input });
/*     */     }
/*     */     
/* 152 */     StringBuffer retVal = formatObjectDetailed(input, -1);
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.exit("com.ibm.msg.client.commonservices.Utils", "formatObjectDetailed(Object)", retVal);
/*     */     }
/* 156 */     return retVal;
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
/*     */   public static StringBuffer formatObjectDetailed(Object input, int maxLength) {
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.entry("com.ibm.msg.client.commonservices.Utils", "formatObjectDetailed(Object,int)", new Object[] { input, 
/* 174 */             Integer.valueOf(maxLength) });
/*     */     }
/* 176 */     ObjectFormatAdapter adapter = utils.objectAdapters.get(input.getClass());
/* 177 */     if (adapter == null) {
/* 178 */       adapter = utils.genericAdapter;
/*     */     }
/*     */     
/* 181 */     StringBuffer retVal = adapter.formatObjectDetailed(input, maxLength);
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.exit("com.ibm.msg.client.commonservices.Utils", "formatObjectDetailed(Object,int)", retVal);
/*     */     }
/*     */     
/* 186 */     return retVal;
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
/*     */   public static StringBuffer bytesToHex(byte[] bArray, int maxLength) {
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.entry("com.ibm.msg.client.commonservices.Utils", "bytesToHex(byte [ ],int)", new Object[] { bArray, 
/* 201 */             Integer.valueOf(maxLength) });
/*     */     }
/*     */ 
/*     */     
/* 205 */     StringBuffer hex = new StringBuffer();
/*     */ 
/*     */     
/* 208 */     if (bArray == null) {
/* 209 */       hex.append("<null>");
/* 210 */       if (Trace.isOn) {
/* 211 */         Trace.exit("com.ibm.msg.client.commonservices.Utils", "bytesToHex(byte [ ],int)", hex, 1);
/*     */       }
/* 213 */       return hex;
/*     */     } 
/*     */ 
/*     */     
/* 217 */     int dumpLength = bArray.length;
/* 218 */     if (maxLength != -1 && maxLength < dumpLength) {
/* 219 */       dumpLength = maxLength;
/*     */     }
/*     */     
/* 222 */     for (int i = 0; i < dumpLength; i++) {
/* 223 */       int binByte = bArray[i];
/*     */       
/* 225 */       if (binByte < 0) {
/* 226 */         binByte += 256;
/*     */       }
/* 228 */       if (binByte < 16) {
/* 229 */         hex.append('0');
/*     */       }
/*     */       
/* 232 */       hex.append(Integer.toHexString(binByte).toUpperCase());
/*     */     } 
/*     */     
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.exit("com.ibm.msg.client.commonservices.Utils", "bytesToHex(byte [ ],int)", hex, 2);
/*     */     }
/* 238 */     return hex;
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
/*     */   public static StringBuffer bytesToHex(byte[] bArray) {
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.entry("com.ibm.msg.client.commonservices.Utils", "bytesToHex(byte [ ])", new Object[] { bArray });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 256 */     StringBuffer retVal = bytesToHex(bArray, -1);
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.exit("com.ibm.msg.client.commonservices.Utils", "bytesToHex(byte [ ])", retVal);
/*     */     }
/* 260 */     return retVal;
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
/*     */   public static byte[] hexToBytes(String hex) throws CSIException {
/* 274 */     if (Trace.isOn) {
/* 275 */       Trace.entry("com.ibm.msg.client.commonservices.Utils", "hexToBytes(String)", new Object[] { hex });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 280 */     byte[] out = null;
/*     */     
/* 282 */     if (hex != null) {
/* 283 */       int len = hex.length();
/*     */       
/* 285 */       if (len % 2 != 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 290 */         CSIException e = new CSIException("Invalid Hex String");
/* 291 */         if (Trace.isOn) {
/* 292 */           Trace.throwing("com.ibm.msg.client.commonservices.Utils", "hexToBytes(String)", e, 1);
/*     */         }
/* 294 */         throw e;
/*     */       } 
/*     */       
/* 297 */       len /= 2;
/* 298 */       out = new byte[len];
/*     */       
/* 300 */       for (int i = 0; i < len; i++) {
/* 301 */         int digit1 = Character.digit(hex.charAt(2 * i), 16);
/* 302 */         int digit2 = Character.digit(hex.charAt(2 * i + 1), 16);
/* 303 */         if (digit1 == -1 || digit2 == -1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 309 */           CSIException e2 = new CSIException("Invalid Hex String");
/* 310 */           if (Trace.isOn) {
/* 311 */             Trace.throwing("com.ibm.msg.client.commonservices.Utils", "hexToBytes(String)", e2, 2);
/*     */           }
/* 313 */           throw e2;
/*     */         } 
/* 315 */         out[i] = (byte)(digit1 * 16 + digit2);
/*     */       } 
/*     */     } 
/*     */     
/* 319 */     if (Trace.isOn) {
/* 320 */       Trace.exit("com.ibm.msg.client.commonservices.Utils", "hexToBytes(String)", out);
/*     */     }
/* 322 */     return out;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getJVMProcessId() {
/* 331 */     int retval = 0;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 336 */       String jvmName = ManagementFactory.getRuntimeMXBean().getName();
/*     */ 
/*     */       
/* 339 */       int sep = jvmName.indexOf('@');
/* 340 */       if (sep > -1) {
/* 341 */         retval = Integer.parseInt(jvmName.substring(0, sep));
/*     */       }
/*     */     }
/* 344 */     catch (NumberFormatException|NullPointerException e) {
/*     */ 
/*     */       
/* 347 */       if (Trace.isOn) {
/* 348 */         Trace.catchBlock("com.ibm.msg.client.commonservices.Utils", "getJVMProcessId()", e);
/*     */       }
/*     */     } 
/*     */     
/* 352 */     if (Trace.isOn) {
/* 353 */       Trace.data("com.ibm.msg.client.commonservices.Utils", "getJVMProcessId()", "getter", 
/* 354 */           Integer.valueOf(retval));
/*     */     }
/*     */     
/* 357 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getThreadId() {
/* 367 */     int retval = 0;
/* 368 */     long longTid = Thread.currentThread().getId();
/* 369 */     if (longTid <= 2147483647L) {
/* 370 */       retval = (int)longTid;
/*     */     } else {
/*     */       
/* 373 */       retval = (int)(longTid % 2147483647L);
/* 374 */       if (Trace.isOn) {
/* 375 */         Trace.data("com.ibm.msg.client.commonservices.Utils", "getThreadId()", "Truncating returned value as following thread ID exceeds Integer MAX_VALUE:", 
/* 376 */             Long.valueOf(longTid));
/*     */       }
/*     */     } 
/*     */     
/* 380 */     if (Trace.isOn) {
/* 381 */       Trace.data("com.ibm.msg.client.commonservices.Utils", "getThreadId()", "getter", 
/* 382 */           Integer.valueOf(retval));
/*     */     }
/*     */     
/* 385 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean byteArraysEqual(byte[] a, byte[] b) {
/*     */     boolean result;
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.entry("com.ibm.msg.client.commonservices.Utils", "byteArraysEqual(byte [ ],byte [ ])", new Object[] { a, b });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 402 */     if (a == b) {
/* 403 */       result = true;
/*     */     
/*     */     }
/* 406 */     else if (a.length != b.length) {
/* 407 */       result = false;
/*     */     } else {
/*     */       
/* 410 */       result = true;
/* 411 */       int len = a.length;
/* 412 */       for (int i = 0; i < len; i++) {
/* 413 */         if (a[i] != b[i]) {
/* 414 */           result = false;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 420 */     if (Trace.isOn) {
/* 421 */       Trace.exit("com.ibm.msg.client.commonservices.Utils", "byteArraysEqual(byte [ ],byte [ ])", 
/* 422 */           Boolean.valueOf(result));
/*     */     }
/* 424 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static StringBuffer getFormattedHexBytes(byte[] toDump, int maxLength) {
/* 432 */     if (Trace.isOn) {
/* 433 */       Trace.entry("com.ibm.msg.client.commonservices.Utils", "getFormattedHexBytes(byte [ ],int)", new Object[] { toDump, 
/* 434 */             Integer.valueOf(maxLength) });
/*     */     }
/* 436 */     StringBuffer buffer = new StringBuffer();
/* 437 */     if (maxLength == 0) {
/* 438 */       if (Trace.isOn) {
/* 439 */         Trace.exit("com.ibm.msg.client.commonservices.Utils", "getFormattedHexBytes(byte [ ],int)", buffer, 1);
/*     */       }
/*     */       
/* 442 */       return buffer;
/*     */     } 
/*     */     
/* 445 */     int dumpLength = toDump.length;
/* 446 */     if (maxLength != -1 && maxLength < dumpLength) {
/* 447 */       dumpLength = maxLength;
/*     */     }
/*     */     
/* 450 */     for (int lineStart = 0; lineStart < dumpLength; lineStart += 16) {
/* 451 */       int lineEnd = Math.min(lineStart + 16, dumpLength);
/* 452 */       StringBuffer hex = new StringBuffer();
/* 453 */       StringBuffer ascii = new StringBuffer();
/* 454 */       for (int i = lineStart; i < lineEnd; i++) {
/* 455 */         int b = toDump[i];
/* 456 */         b = (b + 256) % 256;
/*     */ 
/*     */         
/* 459 */         int c1 = b / 16;
/* 460 */         int c2 = b % 16;
/* 461 */         hex.append((char)((c1 < 10) ? (48 + c1) : (97 + c1 - 10)));
/* 462 */         hex.append((char)((c2 < 10) ? (48 + c2) : (97 + c2 - 10)));
/*     */         
/* 464 */         if (i % 2 != 0) {
/* 465 */           hex.append(' ');
/*     */         }
/*     */ 
/*     */         
/* 469 */         if (b >= 32 && b <= 126) {
/* 470 */           ascii.append((char)b);
/*     */         } else {
/*     */           
/* 473 */           ascii.append('.');
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 479 */       int pad = 16 - lineEnd - lineStart;
/*     */ 
/*     */       
/* 482 */       int spaces = (pad * 5 + pad % 2) / 2;
/*     */       
/* 484 */       spaces += 3;
/*     */       
/* 486 */       for (int j = 0; j < spaces; j++) {
/* 487 */         hex.append(' ');
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 492 */       String offset = "0000" + Integer.toHexString(lineStart);
/* 493 */       offset = offset.substring(offset.length() - 4);
/* 494 */       buffer.append(offset);
/* 495 */       buffer.append(":  ");
/*     */ 
/*     */       
/* 498 */       buffer.append(hex.toString());
/* 499 */       buffer.append(ascii.toString());
/* 500 */       buffer.append('\n');
/*     */     } 
/*     */     
/* 503 */     if (Trace.isOn) {
/* 504 */       Trace.exit("com.ibm.msg.client.commonservices.Utils", "getFormattedHexBytes(byte [ ],int)", buffer, 2);
/*     */     }
/*     */     
/* 507 */     return buffer;
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
/*     */   private abstract class ObjectFormatAdapter
/*     */   {
/*     */     private ObjectFormatAdapter() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     StringBuffer formatObjectDetailed(Object input, int maxLength) throws ClassCastException {
/* 549 */       if (Trace.isOn) {
/* 550 */         Trace.entry(this, "com.ibm.msg.client.commonservices.ObjectFormatAdapter", "formatObjectDetailed(Object,int)", new Object[] { input, 
/* 551 */               Integer.valueOf(maxLength) });
/*     */       }
/* 553 */       StringBuffer traceRet1 = formatObject(input, maxLength);
/* 554 */       if (Trace.isOn) {
/* 555 */         Trace.exit(this, "com.ibm.msg.client.commonservices.ObjectFormatAdapter", "formatObjectDetailed(Object,int)", traceRet1);
/*     */       }
/*     */       
/* 558 */       return traceRet1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     abstract StringBuffer formatObject(Object param1Object, int param1Int) throws ClassCastException;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   class GenericFormatAdapter
/*     */     extends ObjectFormatAdapter
/*     */   {
/*     */     StringBuffer formatObject(Object input, int maxLength) throws ClassCastException {
/* 573 */       if (Trace.isOn) {
/* 574 */         Trace.entry(this, "com.ibm.msg.client.commonservices.GenericFormatAdapter", "formatObject(Object,int)", new Object[] { input, 
/* 575 */               Integer.valueOf(maxLength) });
/*     */       }
/* 577 */       StringBuffer output = new StringBuffer();
/*     */       
/* 579 */       if (maxLength == 0) {
/* 580 */         if (Trace.isOn) {
/* 581 */           Trace.exit(this, "com.ibm.msg.client.commonservices.GenericFormatAdapter", "formatObject(Object,int)", output, 1);
/*     */         }
/*     */         
/* 584 */         return output;
/*     */       } 
/*     */       
/* 587 */       output.append(input.toString());
/* 588 */       if (maxLength < 0 || output.length() <= maxLength) {
/* 589 */         if (Trace.isOn) {
/* 590 */           Trace.exit(this, "com.ibm.msg.client.commonservices.GenericFormatAdapter", "formatObject(Object,int)", output, 2);
/*     */         }
/*     */         
/* 593 */         return output;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 598 */       String fullOutput = output.toString();
/* 599 */       fullOutput = fullOutput.subSequence(0, maxLength - 1).toString();
/* 600 */       output = new StringBuffer(fullOutput);
/* 601 */       if (Trace.isOn) {
/* 602 */         Trace.exit(this, "com.ibm.msg.client.commonservices.GenericFormatAdapter", "formatObject(Object,int)", output, 3);
/*     */       }
/*     */       
/* 605 */       return output;
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
/*     */   class ByteArrayFormatAdapter
/*     */     extends ObjectFormatAdapter
/*     */   {
/*     */     StringBuffer formatObject(Object input, int maxLength) throws ClassCastException {
/* 625 */       if (Trace.isOn) {
/* 626 */         Trace.entry(this, "com.ibm.msg.client.commonservices.ByteArrayFormatAdapter", "formatObject(Object,int)", new Object[] { input, 
/* 627 */               Integer.valueOf(maxLength) });
/*     */       }
/* 629 */       byte[] bytes = (byte[])input;
/*     */       
/* 631 */       StringBuffer output = Utils.bytesToHex(bytes, maxLength);
/*     */       
/* 633 */       if (Trace.isOn) {
/* 634 */         Trace.exit(this, "com.ibm.msg.client.commonservices.ByteArrayFormatAdapter", "formatObject(Object,int)", output);
/*     */       }
/*     */       
/* 637 */       return output;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     StringBuffer formatObjectDetailed(Object input, int maxLength) throws ClassCastException {
/* 647 */       if (Trace.isOn) {
/* 648 */         Trace.entry(this, "com.ibm.msg.client.commonservices.ByteArrayFormatAdapter", "formatObjectDetailed(Object,int)", new Object[] { input, 
/* 649 */               Integer.valueOf(maxLength) });
/*     */       }
/* 651 */       byte[] bytes = (byte[])input;
/*     */       
/* 653 */       StringBuffer output = Utils.getFormattedHexBytes(bytes, maxLength);
/*     */       
/* 655 */       if (Trace.isOn) {
/* 656 */         Trace.exit(this, "com.ibm.msg.client.commonservices.ByteArrayFormatAdapter", "formatObjectDetailed(Object,int)", output);
/*     */       }
/*     */       
/* 659 */       return output;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */