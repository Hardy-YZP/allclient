/*     */ package com.ibm.msg.client.wmq.common.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiMQ;
/*     */ import com.ibm.mq.jmqi.MQOD;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
/*     */ import com.ibm.mq.jmqi.handles.Phobj;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.common.WMQThreadLocalStorage;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.charset.CharacterCodingException;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WMQUtils
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQUtils.java";
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.msg.client.wmq.common.internal.WMQUtils", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQUtils.java");
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
/*  65 */   private static final ThreadLocal<Integer> cachedCcsid = new ThreadLocal<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   private static final ThreadLocal<JmqiCodepage> cachedCodepage = new ThreadLocal<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   private static final ThreadLocal<Integer> cachedEncoding = new ThreadLocal<>();
/*     */ 
/*     */   
/*     */   static final String CLASSNAME = "com.ibm.msg.client.wmq.internal.WMQUtils";
/*     */   
/*  80 */   private static final char[] BIN2HEX = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
/*     */   
/*  82 */   private static final char[] BIN2HEX_UPPERCASE = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] computeNewBytesFromBytes(byte[] input, int startIndex, int endIndex) {
/*  96 */     if (input == null) {
/*  97 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 101 */     int length = ((endIndex == -1) ? input.length : endIndex) - startIndex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     if (length == 0) {
/* 108 */       return null;
/*     */     }
/*     */     
/* 111 */     byte[] result = new byte[length];
/* 112 */     System.arraycopy(input, startIndex, result, 0, length);
/* 113 */     return result;
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
/*     */   @Deprecated
/*     */   public static byte[] computeBytesFromText(String input, String cpName) throws JMSException {
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQUtils", "computeBytesFromText(String,String)", new Object[] { input, cpName });
/*     */     }
/*     */     
/* 130 */     byte[] traceRet1 = computeBytesFromText(input, JmqiCodepage.getJmqiCodepage(null, cpName));
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUtils", "computeBytesFromText(String,String)", traceRet1);
/*     */     }
/*     */     
/* 135 */     return traceRet1;
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
/*     */   public static byte[] computeBytesFromText(String input, JmqiCodepage codepage) throws JMSException {
/* 149 */     if (input == null) {
/* 150 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 154 */     if (codepage.getCCSID() == 1208) {
/*     */       
/* 156 */       ByteBuffer buffer = convUtf16Utf8Optimistic(input, null);
/*     */ 
/*     */       
/* 159 */       if (buffer != null) {
/* 160 */         if (Trace.isOn) {
/* 161 */           Trace.data("com.ibm.msg.client.wmq.internal.WMQUtils", "computeBytesFromText(String,String)", "Fast-path successful. Resultant ByteBuffer: ", buffer);
/*     */         }
/* 163 */         return buffer.array();
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 169 */     return computeBytesFromTextSlowPath(input, codepage);
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
/*     */   @Deprecated
/*     */   public static ByteBuffer computeBytesFromTextUsingTls(String input, String cpName, WMQThreadLocalStorage tls) throws JMSException {
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQUtils", "computeBytesFromTextUsingTls(String,String,WMQThreadLocalStorage)", new Object[] { input, cpName, tls });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 190 */     ByteBuffer traceRet1 = computeBytesFromTextUsingTls(input, JmqiCodepage.getJmqiCodepage(null, cpName), tls);
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUtils", "computeBytesFromTextUsingTls(String,String,WMQThreadLocalStorage)", traceRet1);
/*     */     }
/*     */     
/* 195 */     return traceRet1;
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
/*     */   public static ByteBuffer computeBytesFromTextUsingTls(String input, JmqiCodepage codepage, WMQThreadLocalStorage tls) throws JMSException {
/* 211 */     if (input == null) {
/* 212 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 216 */     if (tls == null) {
/* 217 */       return ByteBuffer.wrap(computeBytesFromText(input, codepage));
/*     */     }
/*     */     
/* 220 */     ByteBuffer result = null;
/*     */ 
/*     */     
/* 223 */     if (codepage.getCCSID() == 1208) {
/*     */       
/* 225 */       result = convUtf16Utf8Optimistic(input, tls);
/*     */ 
/*     */       
/* 228 */       if (result != null) {
/* 229 */         if (Trace.isOn) {
/* 230 */           Trace.data("com.ibm.msg.client.wmq.internal.WMQUtils", "computeBytesFromTextUsingTls(String,String,WMQThreadLocalStorage)", "Fast-path successful. Resultant ByteBuffer: ", result);
/*     */         }
/*     */         
/* 233 */         return result;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 238 */     byte[] bytes = computeBytesFromTextSlowPath(input, codepage);
/* 239 */     result = ByteBuffer.wrap(bytes);
/*     */     
/* 241 */     return result;
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
/*     */   private static byte[] computeBytesFromTextSlowPath(String input, JmqiCodepage codepage) throws JMSException {
/*     */     byte[] result;
/*     */     try {
/* 257 */       result = codepage.stringToBytes(input);
/*     */     }
/* 259 */     catch (CharacterCodingException ex) {
/* 260 */       HashMap<String, String> inserts = new HashMap<>();
/* 261 */       inserts.put("CCSID", codepage.toString());
/* 262 */       inserts.put("XMSC_INSERT_VALUE", input);
/* 263 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ1049", inserts);
/* 264 */       je.setLinkedException(ex);
/* 265 */       throw je;
/*     */     } 
/*     */     
/* 268 */     return result;
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
/*     */   public static ByteBuffer convUtf16Utf8Optimistic(String src, WMQThreadLocalStorage tls) {
/* 286 */     int srcPos = 0;
/* 287 */     int targPos = 0;
/*     */ 
/*     */     
/* 290 */     int srcLen = src.length();
/* 291 */     byte[] targ = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 299 */     targ = new byte[srcLen];
/*     */     
/* 301 */     int lastByte = srcLen;
/*     */ 
/*     */     
/* 304 */     while (srcPos < srcLen) {
/*     */       
/* 306 */       char srcChar = src.charAt(srcPos++);
/* 307 */       int srcByte0 = srcChar >> 8 & 0xFF;
/* 308 */       int srcByte1 = srcChar & 0xFF;
/*     */       
/* 310 */       if ((srcByte1 & 0x80) == 0 && srcByte0 == 0) {
/*     */         
/* 312 */         if (targPos >= lastByte)
/*     */         {
/* 314 */           return null;
/*     */         }
/*     */         
/* 317 */         targ[targPos++] = (byte)srcByte1;
/*     */         
/*     */         continue;
/*     */       } 
/* 321 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 325 */     ByteBuffer result = ByteBuffer.wrap(targ);
/* 326 */     result.limit(srcLen);
/* 327 */     return result;
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
/*     */   @Deprecated
/*     */   public static String computeTextFromBytes(byte[] input, int startIndex, int endIndex, String cpName) throws JMSException {
/* 344 */     if (Trace.isOn) {
/* 345 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQUtils", "computeTextFromBytes(byte [ ],int,int,String)", new Object[] { input, 
/*     */             
/* 347 */             Integer.valueOf(startIndex), Integer.valueOf(endIndex), cpName });
/*     */     }
/*     */     
/* 350 */     String traceRet1 = computeTextFromBytes(input, startIndex, endIndex, JmqiCodepage.getJmqiCodepage(null, cpName));
/* 351 */     if (Trace.isOn) {
/* 352 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUtils", "computeTextFromBytes(byte [ ],int,int,String)", traceRet1);
/*     */     }
/*     */     
/* 355 */     return traceRet1;
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
/*     */   public static String computeTextFromBytes(byte[] input, int startIndex, int endIndex, JmqiCodepage codepage) throws JMSException {
/*     */     try {
/* 374 */       int dataLength = ((endIndex == -1) ? input.length : endIndex) - startIndex;
/* 375 */       String result = codepage.bytesToString(input, startIndex, dataLength);
/* 376 */       return result;
/*     */     }
/* 378 */     catch (CharacterCodingException ex) {
/* 379 */       HashMap<String, Object> inserts = new HashMap<>();
/* 380 */       inserts.put("CCSID", codepage);
/* 381 */       inserts.put("XMSC_INSERT_VALUE", input);
/* 382 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ1049", inserts);
/* 383 */       je.setLinkedException(ex);
/* 384 */       throw je;
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
/*     */   @Deprecated
/*     */   public static String computeTextFromByteBuffer(ByteBuffer input, String cpName) throws JMSException {
/* 398 */     if (Trace.isOn) {
/* 399 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQUtils", "computeTextFromByteBuffer(ByteBuffer,String)", new Object[] { input, cpName });
/*     */     }
/*     */ 
/*     */     
/* 403 */     String traceRet1 = computeTextFromByteBuffer(input, JmqiCodepage.getJmqiCodepage(null, cpName));
/* 404 */     if (Trace.isOn) {
/* 405 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUtils", "computeTextFromByteBuffer(ByteBuffer,String)", traceRet1);
/*     */     }
/*     */     
/* 408 */     return traceRet1;
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
/*     */   public static String computeTextFromByteBuffer(ByteBuffer input, JmqiCodepage codepage) throws JMSException {
/* 421 */     return computeTextFromBytes(input.array(), input.position(), input.limit(), codepage);
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
/*     */   public static int binToHex(byte[] bin, int start, int length, StringBuilder hex) {
/* 437 */     int sum = 0;
/*     */     
/* 439 */     for (int i = start; i < start + length; i++) {
/* 440 */       int binByte = bin[i];
/*     */       
/* 442 */       if (binByte < 0) {
/* 443 */         binByte += 256;
/*     */       }
/*     */       
/* 446 */       sum += binByte;
/* 447 */       hex.append(BIN2HEX[binByte / 16]);
/* 448 */       hex.append(BIN2HEX[binByte % 16]);
/*     */     } 
/* 450 */     return sum;
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
/*     */   public static int binToHexUpperCase(byte[] bin, int start, int length, StringBuffer hex) {
/* 465 */     int sum = 0;
/*     */     
/* 467 */     for (int i = start; i < start + length; i++) {
/* 468 */       int binByte = bin[i];
/*     */       
/* 470 */       if (binByte < 0) {
/* 471 */         binByte += 256;
/*     */       }
/*     */       
/* 474 */       sum += binByte;
/* 475 */       hex.append(BIN2HEX_UPPERCASE[binByte / 16]);
/* 476 */       hex.append(BIN2HEX_UPPERCASE[binByte % 16]);
/*     */     } 
/* 478 */     return sum;
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
/*     */   public static byte[] hexToBin(String hex, int start) throws JMSException {
/* 494 */     int length = hex.length() - start;
/*     */ 
/*     */     
/* 497 */     if (length == 0) {
/* 498 */       byte[] result = new byte[0];
/* 499 */       return result;
/*     */     } 
/*     */ 
/*     */     
/* 503 */     if (length < 0 || length % 2 != 0) {
/* 504 */       HashMap<String, String> inserts = new HashMap<>();
/* 505 */       inserts.put("XMSC_INSERT_HEX_STRING", hex.substring(start));
/* 506 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ1044", inserts);
/* 507 */       throw je;
/*     */     } 
/*     */ 
/*     */     
/* 511 */     length /= 2;
/*     */ 
/*     */     
/* 514 */     byte[] retval = new byte[length];
/*     */ 
/*     */     
/* 517 */     for (int i = 0; i < length; i++) {
/* 518 */       int digit1 = Character.digit(hex.charAt(2 * i + start), 16) << 4;
/* 519 */       int digit2 = Character.digit(hex.charAt(2 * i + start + 1), 16);
/*     */ 
/*     */       
/* 522 */       if (digit1 < 0 || digit2 < 0) {
/* 523 */         HashMap<String, String> inserts = new HashMap<>();
/* 524 */         inserts.put("XMSC_INSERT_HEX_STRING", hex.substring(start));
/* 525 */         JMSException je2 = (JMSException)NLSServices.createException("JMSCMQ1044", inserts);
/* 526 */         throw je2;
/*     */       } 
/*     */       
/* 529 */       retval[i] = (byte)(digit1 + digit2);
/*     */     } 
/* 531 */     return retval;
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
/*     */   public static String idToString(byte[] id) {
/* 545 */     if (id == null) {
/* 546 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 550 */     int len = id.length;
/* 551 */     char[] idchars = new char[3 + 2 * len];
/* 552 */     idchars[0] = 'I';
/* 553 */     idchars[1] = 'D';
/* 554 */     idchars[2] = ':';
/*     */ 
/*     */ 
/*     */     
/* 558 */     for (int i = 0; i < len; i++) {
/* 559 */       int binByte = id[i];
/*     */       
/* 561 */       if (binByte < 0) {
/* 562 */         binByte += 256;
/*     */       }
/*     */       
/* 565 */       idchars[2 * i + 3] = BIN2HEX[binByte / 16];
/* 566 */       idchars[2 * i + 4] = BIN2HEX[binByte % 16];
/*     */     } 
/*     */     
/* 569 */     return new String(idchars);
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
/*     */   public static byte[] stringToId(String idString) throws JMSException {
/* 588 */     byte[] result = hexToBin(idString, 3);
/* 589 */     return result;
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
/*     */   public static JmqiCodepage getCodepage(JmqiEnvironment env, int ccsidP, int encoding) throws JMSException {
/*     */     JmqiCodepage result;
/* 606 */     int ccsid = ccsidP;
/*     */ 
/*     */ 
/*     */     
/* 610 */     if (cachedCodepage.get() != null && ((Integer)cachedCcsid.get()).intValue() == ccsid && ((Integer)cachedEncoding.get()).intValue() == encoding) {
/*     */ 
/*     */       
/* 613 */       if (Trace.isOn) {
/* 614 */         Trace.data("com.ibm.msg.client.wmq.internal.WMQUtils", "getCharacterSetString(int,int)", "Mapped " + ccsid + " to " + cachedCodepage.get() + ", (cached) ", null);
/*     */       }
/* 616 */       return cachedCodepage.get();
/*     */     } 
/*     */     
/* 619 */     if (ccsid == 0) {
/* 620 */       ccsid = 819;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 625 */       result = JmqiCodepage.getJmqiCodepage(env, ccsid, encoding);
/*     */     }
/* 627 */     catch (UnsupportedEncodingException e) {
/*     */ 
/*     */       
/* 630 */       HashMap<String, String> inserts = new HashMap<>();
/* 631 */       inserts.put("CCSID", "ccsid:" + Integer.toString(ccsid));
/* 632 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ1046", inserts);
/* 633 */       throw je;
/*     */     } 
/*     */ 
/*     */     
/* 637 */     cachedCcsid.set(Integer.valueOf(ccsid));
/* 638 */     cachedEncoding.set(Integer.valueOf(encoding));
/* 639 */     cachedCodepage.set(result);
/*     */     
/* 641 */     return result;
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
/*     */   @Deprecated
/*     */   public static String getCharacterSetString(JmqiEnvironment env, int ccsidP, int encoding) throws JMSException {
/* 654 */     if (Trace.isOn) {
/* 655 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQUtils", "getCharacterSetString(JmqiEnvironment,int,int)", new Object[] { env, 
/*     */             
/* 657 */             Integer.valueOf(ccsidP), Integer.valueOf(encoding) });
/*     */     }
/* 659 */     String traceRet1 = getCodepage(env, ccsidP, encoding).getCharsetName();
/* 660 */     if (Trace.isOn) {
/* 661 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUtils", "getCharacterSetString(JmqiEnvironment,int,int)", traceRet1);
/*     */     }
/*     */     
/* 664 */     return traceRet1;
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
/*     */   public static boolean isNPMClassHigh(WMQConsumerOwner owner, WMQDestination destination) {
/* 683 */     if (Trace.isOn) {
/* 684 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.WMQUtils", "isNPMClassHigh(final WMQConsumerOwner,final WMQDestination)", new Object[] { owner, destination });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 690 */     if (owner == null || destination == null) {
/* 691 */       HashMap<String, Object> info = new HashMap<>();
/* 692 */       info.put("methodName", "isNPMClassHigh(WMQSession,WMQDestination)");
/* 693 */       info.put("owner", owner);
/* 694 */       info.put("destination", destination);
/* 695 */       Trace.ffst("com.ibm.msg.client.wmq.internal.WMQUtils", "isNPMClassHigh(WMQSession,WMQDestination)", "XN00A003", info, null);
/* 696 */       if (Trace.isOn) {
/* 697 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUtils", "isNPMClassHigh(final WMQConsumerOwner,final WMQDestination)", 
/* 698 */             Boolean.valueOf(false), 1);
/*     */       }
/*     */       
/* 701 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 706 */     if (destination.isTopic()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 724 */       if (Trace.isOn) {
/* 725 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUtils", "isNPMClassHigh(final WMQConsumerOwner,final WMQDestination)", 
/* 726 */             Boolean.valueOf(true), 2);
/*     */       }
/*     */       
/* 729 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 733 */     JmqiEnvironment env = owner.getJmqiEnvironment();
/* 734 */     Hconn hconn = owner.getHconn();
/* 735 */     JmqiMQ mq = owner.getJmqiMQ();
/*     */ 
/*     */     
/* 738 */     if (env == null || hconn == null || mq == null) {
/* 739 */       HashMap<String, Object> info = new HashMap<>();
/* 740 */       info.put("methodName", "isNPMClassHigh(WMQSession,WMQDestination)");
/* 741 */       info.put("JmqiEnvironment", env);
/* 742 */       info.put("Hconn", hconn);
/* 743 */       info.put("JmqiMQ", mq);
/* 744 */       Trace.ffst("com.ibm.msg.client.wmq.internal.WMQUtils", "isNPMClassHigh(WMQSession,WMQDestination)", "XN00A004", info, null);
/* 745 */       if (Trace.isOn) {
/* 746 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUtils", "isNPMClassHigh(final WMQConsumerOwner,final WMQDestination)", 
/* 747 */             Boolean.valueOf(false), 3);
/*     */       }
/*     */       
/* 750 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 755 */     Pint cc = env.newPint();
/* 756 */     Pint rc = env.newPint();
/*     */ 
/*     */     
/* 759 */     MQOD mqod = env.newMQOD();
/*     */     
/*     */     try {
/* 762 */       if (destination.propertyExists("XMSC_WMQ_QUEUE_MANAGER")) {
/* 763 */         String qmName = destination.getStringProperty("XMSC_WMQ_QUEUE_MANAGER");
/* 764 */         if (qmName != null && qmName.length() > 0) {
/* 765 */           mqod.setObjectQMgrName(qmName);
/*     */         }
/*     */       }
/*     */     
/* 769 */     } catch (JMSException je) {
/* 770 */       if (Trace.isOn) {
/* 771 */         Trace.catchBlock("com.ibm.msg.client.wmq.common.internal.WMQUtils", "isNPMClassHigh(final WMQConsumerOwner,final WMQDestination)", (Throwable)je);
/*     */       }
/*     */ 
/*     */       
/* 775 */       if (Trace.isOn) {
/* 776 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUtils", "isNPMClassHigh(final WMQConsumerOwner,final WMQDestination)", 
/* 777 */             Boolean.valueOf(false), 4);
/*     */       }
/*     */       
/* 780 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 784 */     mqod.setObjectType(1);
/* 785 */     mqod.setObjectName(destination.getName());
/*     */ 
/*     */     
/* 788 */     int openOptions = 32;
/*     */     
/* 790 */     Phobj phobj = env.newPhobj();
/*     */ 
/*     */     
/* 793 */     mq.MQOPEN(hconn, mqod, openOptions, phobj, cc, rc);
/*     */ 
/*     */     
/* 796 */     if (rc.x != 0 || cc.x != 0) {
/*     */ 
/*     */       
/* 799 */       if (Trace.isOn) {
/* 800 */         Trace.data("com.ibm.msg.client.wmq.internal.WMQUtils", "isNPMClassHigh(WMQSession,WMQDestination)", "MQOPEN did not succeed. cc=" + cc + " and rc=" + rc + ", returning false", null);
/*     */       }
/*     */       
/* 803 */       if (Trace.isOn) {
/* 804 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUtils", "isNPMClassHigh(final WMQConsumerOwner,final WMQDestination)", 
/* 805 */             Boolean.valueOf(false), 5);
/*     */       }
/*     */       
/* 808 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 812 */     Hobj hobj = phobj.getHobj();
/*     */ 
/*     */     
/*     */     try {
/* 816 */       int[] selectors = { 78 };
/* 817 */       int[] intAttrs = new int[1];
/*     */ 
/*     */       
/* 820 */       mq.MQINQ(hconn, hobj, selectors.length, selectors, intAttrs.length, intAttrs, 0, null, cc, rc);
/*     */ 
/*     */       
/* 823 */       if (rc.x != 0 || cc.x != 0) {
/*     */ 
/*     */         
/* 826 */         if (Trace.isOn) {
/* 827 */           Trace.data("com.ibm.msg.client.wmq.internal.WMQUtils", "isNPMClassHigh(WMQSession,WMQDestination)", "MQINQ did not succeed. cc=" + cc + " and rc=" + rc + ", returning false", null);
/*     */         }
/*     */         
/* 830 */         if (Trace.isOn) {
/* 831 */           Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUtils", "isNPMClassHigh(final WMQConsumerOwner,final WMQDestination)", 
/* 832 */               Boolean.valueOf(false), 6);
/*     */         }
/*     */         
/* 835 */         return false;
/*     */       } 
/*     */       
/* 838 */       int npmClass = intAttrs[0];
/*     */       
/* 840 */       if (Trace.isOn) {
/* 841 */         Trace.data("com.ibm.msg.client.wmq.internal.WMQUtils", "isNPMClassHigh(WMQSession,WMQDestination)", "MQIA_NPM_CLASS value = " + npmClass, null);
/*     */       }
/*     */       
/* 844 */       if (npmClass == 10) {
/* 845 */         if (Trace.isOn) {
/* 846 */           Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUtils", "isNPMClassHigh(final WMQConsumerOwner,final WMQDestination)", 
/* 847 */               Boolean.valueOf(true), 7);
/*     */         }
/*     */         
/* 850 */         return true;
/*     */       } 
/*     */       
/* 853 */       if (Trace.isOn) {
/* 854 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.WMQUtils", "isNPMClassHigh(final WMQConsumerOwner,final WMQDestination)", 
/* 855 */             Boolean.valueOf(false), 8);
/*     */       }
/*     */       
/* 858 */       return false;
/*     */     } finally {
/*     */       
/* 861 */       if (Trace.isOn) {
/* 862 */         Trace.finallyBlock("com.ibm.msg.client.wmq.common.internal.WMQUtils", "isNPMClassHigh(final WMQConsumerOwner,final WMQDestination)");
/*     */       }
/*     */       
/* 865 */       int closeOptions = 0;
/* 866 */       phobj.setHobj(hobj);
/*     */ 
/*     */       
/* 869 */       mq.MQCLOSE(hconn, phobj, closeOptions, cc, rc);
/*     */ 
/*     */       
/* 872 */       if (rc.x != 0 || cc.x != 0)
/*     */       {
/* 874 */         if (Trace.isOn) {
/* 875 */           Trace.data("com.ibm.msg.client.wmq.internal.WMQUtils", "isNPMClassHigh(WMQSession,WMQDestination)", "MQCLOSE did not succeed. cc=" + cc + " and rc=" + rc, null);
/*     */         }
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
/*     */   public static <K> Enumeration<K> enumerationFromIterator(Iterator<K> iterator) {
/* 891 */     final Iterator<K> it = iterator;
/*     */     
/* 893 */     return new Enumeration<K>()
/*     */       {
/*     */         public boolean hasMoreElements()
/*     */         {
/* 897 */           return it.hasNext();
/*     */         }
/*     */ 
/*     */         
/*     */         public K nextElement() {
/* 902 */           return it.next();
/*     */         }
/*     */       };
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
/*     */   public static <K> Enumeration<K> enumerationFromIterable(Iterable<K> iterable) {
/* 916 */     return enumerationFromIterator(iterable.iterator());
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\WMQUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */