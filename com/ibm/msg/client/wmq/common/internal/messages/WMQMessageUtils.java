/*     */ package com.ibm.msg.client.wmq.common.internal.messages;
/*     */ 
/*     */ import com.ibm.mq.jmqi.system.zrfp.Constants;
/*     */ import com.ibm.mq.jmqi.system.zrfp.Triplet;
/*     */ import com.ibm.msg.client.commonservices.CSIException;
/*     */ import com.ibm.msg.client.commonservices.Utils;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
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
/*     */ abstract class WMQMessageUtils
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQMessageUtils.java";
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageUtils", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQMessageUtils.java");
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
/*  54 */   static final HashMap<Class<?>, String> datatypeElements = new HashMap<>(10);
/*     */   
/*  56 */   static final HashMap<Class<?>, Integer> datatypeForTriplets = new HashMap<>(10);
/*     */   
/*  58 */   static final HashMap<String, Integer> datatypeNameToNumber = new HashMap<>(10);
/*     */ 
/*     */   
/*     */   static final String BOOLEAN_ID = "boolean";
/*     */ 
/*     */   
/*     */   static final String BYTEARRAY_ID = "bin.hex";
/*     */ 
/*     */   
/*     */   static final String CHAR_ID = "char";
/*     */ 
/*     */   
/*     */   static final String BYTE_ID = "i1";
/*     */   
/*     */   static final String SHORT_ID = "i2";
/*     */   
/*     */   static final String INT_ID = "i4";
/*     */   
/*     */   static final String INT_GENERIC_ID = "int";
/*     */   
/*     */   static final String LONG_ID = "i8";
/*     */   
/*     */   static final String FLOAT_ID = "r4";
/*     */   
/*     */   static final String DOUBLE_ID = "r8";
/*     */   
/*     */   static final String STRING_ID = "";
/*     */   
/*     */   static final int RFH2_DT_BOOLEAN = 1;
/*     */   
/*     */   static final int RFH2_DT_BYTEARRAY = 2;
/*     */   
/*     */   static final int RFH2_DT_CHAR = 3;
/*     */   
/*     */   static final int RFH2_DT_I1 = 4;
/*     */   
/*     */   static final int RFH2_DT_I2 = 5;
/*     */   
/*     */   static final int RFH2_DT_I4 = 6;
/*     */   
/*     */   static final int RFH2_DT_I8 = 7;
/*     */   
/*     */   static final int RFH2_DT_INT = 7;
/*     */   
/*     */   static final int RFH2_DT_R4 = 8;
/*     */   
/*     */   static final int RFH2_DT_R8 = 9;
/*     */   
/*     */   static final int RFH2_DT_STRING = 10;
/*     */   
/*     */   static final String MCD_CLASS_BYTES = "<mcd><Msd>jms_bytes</Msd></mcd>";
/*     */   
/*     */   static final String MCD_CLASS_MAP = "<mcd><Msd>jms_map</Msd></mcd>";
/*     */   
/*     */   static final String MCD_CLASS_NONE = "<mcd><Msd>jms_none</Msd></mcd>";
/*     */   
/*     */   static final String MCD_CLASS_OBJECT = "<mcd><Msd>jms_object</Msd></mcd>";
/*     */   
/*     */   static final String MCD_CLASS_STREAM = "<mcd><Msd>jms_stream</Msd></mcd>";
/*     */   
/*     */   static final String MCD_CLASS_TEXT = "<mcd><Msd>jms_text</Msd></mcd>";
/*     */   
/*     */   static final String MCD_CLASS_TEXT_NULL_MSG = "<mcd><Msd>jms_text</Msd><msgbody xsi:nil=\"true\"></msgbody></mcd>";
/*     */   
/*     */   static final String MCD_CLASS_TEXT_NULL_MSG_SINGLE_QUOTES = "<mcd><Msd>jms_text</Msd><msgbody xsi:nil='true'></msgbody></mcd>";
/*     */   
/*     */   static final String NULL_MSG_BODY = "<msgbody xsi:nil=\"true\"></msgbody>";
/*     */   
/* 126 */   static final Triplet TRIPLET_MCD_CLASS_BYTES = new Triplet("mcd", 1);
/*     */   
/* 128 */   static final Triplet TRIPLET_MCD_CLASS_MAP = new Triplet("mcd", 1);
/*     */   
/* 130 */   static final Triplet TRIPLET_MCD_CLASS_NONE = new Triplet("mcd", 1);
/*     */   
/* 132 */   static final Triplet TRIPLET_MCD_CLASS_OBJECT = new Triplet("mcd", 1);
/*     */   
/* 134 */   static final Triplet TRIPLET_MCD_CLASS_STREAM = new Triplet("mcd", 1);
/*     */   
/* 136 */   static final Triplet TRIPLET_MCD_CLASS_TEXT = new Triplet("mcd", 1);
/*     */   
/* 138 */   static final Triplet TRIPLET_MCD_CLASS_TEXT_NULL_MSG = new Triplet("mcd", 2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 145 */     datatypeElements.put(Boolean.class, "boolean");
/* 146 */     datatypeElements.put(Byte.class, "i1");
/* 147 */     datatypeElements.put(byte[].class, "bin.hex");
/* 148 */     datatypeElements.put(Character.class, "char");
/* 149 */     datatypeElements.put(Short.class, "i2");
/* 150 */     datatypeElements.put(Integer.class, "i4");
/* 151 */     datatypeElements.put(Long.class, "i8");
/* 152 */     datatypeElements.put(Float.class, "r4");
/* 153 */     datatypeElements.put(Double.class, "r8");
/* 154 */     datatypeElements.put(String.class, "");
/*     */     
/* 156 */     datatypeNameToNumber.put("boolean", Integer.valueOf(1));
/* 157 */     datatypeNameToNumber.put("i1", Integer.valueOf(4));
/* 158 */     datatypeNameToNumber.put("bin.hex", Integer.valueOf(2));
/* 159 */     datatypeNameToNumber.put("char", Integer.valueOf(3));
/* 160 */     datatypeNameToNumber.put("i2", Integer.valueOf(5));
/* 161 */     datatypeNameToNumber.put("i4", Integer.valueOf(6));
/* 162 */     datatypeNameToNumber.put("int", Integer.valueOf(7));
/* 163 */     datatypeNameToNumber.put("i8", Integer.valueOf(7));
/* 164 */     datatypeNameToNumber.put("r4", Integer.valueOf(8));
/* 165 */     datatypeNameToNumber.put("r8", Integer.valueOf(9));
/* 166 */     datatypeNameToNumber.put("string", Integer.valueOf(10));
/*     */     
/* 168 */     datatypeForTriplets.put(Boolean.class, Constants.TYPE_BOOLEAN);
/* 169 */     datatypeForTriplets.put(Byte.class, Constants.TYPE_INT8);
/* 170 */     datatypeForTriplets.put(byte[].class, Constants.TYPE_BYTE_STRING);
/* 171 */     datatypeForTriplets.put(Character.class, Constants.TYPE_INT16);
/* 172 */     datatypeForTriplets.put(Short.class, Constants.TYPE_INT16);
/* 173 */     datatypeForTriplets.put(Integer.class, Constants.TYPE_INT32);
/* 174 */     datatypeForTriplets.put(Long.class, Constants.TYPE_INT64);
/* 175 */     datatypeForTriplets.put(Float.class, Constants.TYPE_FLOAT32);
/* 176 */     datatypeForTriplets.put(Double.class, Constants.TYPE_FLOAT64);
/* 177 */     datatypeForTriplets.put(String.class, Constants.TYPE_STRING);
/*     */     
/* 179 */     TRIPLET_MCD_CLASS_BYTES.add("Msd", "jms_bytes");
/* 180 */     TRIPLET_MCD_CLASS_MAP.add("Msd", "jms_map");
/* 181 */     TRIPLET_MCD_CLASS_NONE.add("Msd", "jms_none");
/* 182 */     TRIPLET_MCD_CLASS_OBJECT.add("Msd", "jms_object");
/* 183 */     TRIPLET_MCD_CLASS_STREAM.add("Msd", "jms_stream");
/* 184 */     TRIPLET_MCD_CLASS_TEXT.add("Msd", "jms_text");
/* 185 */     TRIPLET_MCD_CLASS_TEXT_NULL_MSG.add("Msd", "jms_text");
/* 186 */     TRIPLET_MCD_CLASS_TEXT_NULL_MSG.add("msgbody", null, Constants.TYPE_NULL);
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
/*     */   static void backReference(StringBuilder sb, String string) throws JMSException {
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageUtils", "backReference(StringBuffer,String)", new Object[] { sb, string });
/*     */     }
/*     */     
/* 208 */     for (int i = 0; i < string.length(); i++) {
/* 209 */       char ch = string.charAt(i);
/* 210 */       if ('<' == ch) {
/* 211 */         sb.append("&lt;");
/* 212 */       } else if ('>' == ch) {
/* 213 */         sb.append("&gt;");
/* 214 */       } else if ('&' == ch) {
/* 215 */         sb.append("&amp;");
/* 216 */       } else if ('"' == ch) {
/* 217 */         sb.append("&quot;");
/* 218 */       } else if ('\'' == ch) {
/* 219 */         sb.append("&apos;");
/* 220 */       } else if ('?' <= ch && ch < '?') {
/* 221 */         if (i + 1 >= string.length()) {
/* 222 */           HashMap<String, Object> inserts = new HashMap<>();
/* 223 */           inserts.put("XMSC_INSERT_CHARACTER", Integer.toHexString(ch) + " ?");
/* 224 */           JMSException je = (JMSException)NLSServices.createException("JMSCMQ1053", inserts);
/* 225 */           if (Trace.isOn) {
/* 226 */             Trace.throwing("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageUtils", "backReference(StringBuffer,String)", (Throwable)je, 1);
/*     */           }
/*     */           
/* 229 */           throw je;
/*     */         } 
/*     */         
/* 232 */         int surrogatePairCodePoint = Character.codePointAt(string, i++);
/* 233 */         sb.append("&#x");
/* 234 */         sb.append(Integer.toHexString(surrogatePairCodePoint));
/* 235 */         sb.append(";");
/*     */       } else {
/* 237 */         sb.append(ch);
/*     */       } 
/*     */     } 
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageUtils", "backReference(StringBuffer,String)", "Escaped String:", sb
/*     */ 
/*     */           
/* 244 */           .toString());
/* 245 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageUtils", "backReference(StringBuffer,String)");
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
/*     */   static Object deformatTypedElement(int datatype, String value) throws JMSException {
/*     */     JMSException je;
/*     */     HashMap<String, Object> info;
/* 262 */     Object result = null;
/* 263 */     switch (datatype) {
/*     */       case 10:
/* 265 */         if (value == null) {
/* 266 */           result = null; break;
/*     */         } 
/* 268 */         result = expandRefs(value);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 6:
/*     */         try {
/* 274 */           result = Integer.valueOf(value);
/*     */         }
/* 276 */         catch (NumberFormatException e) {
/* 277 */           JMSException jMSException = (JMSException)NLSServices.createException("JMSCMQ0008", null);
/* 278 */           throw jMSException;
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 1:
/* 283 */         if (value.equals("1")) {
/* 284 */           result = Boolean.valueOf(true); break;
/* 285 */         }  if (value.equals("0")) {
/* 286 */           result = Boolean.valueOf(false); break;
/*     */         } 
/* 288 */         je = (JMSException)NLSServices.createException("JMSCMQ0008", null);
/* 289 */         throw je;
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 294 */         if (value.length() == 0) {
/* 295 */           result = new byte[0]; break;
/*     */         } 
/*     */         try {
/* 298 */           result = Utils.hexToBytes(value);
/*     */         }
/* 300 */         catch (CSIException e) {
/* 301 */           HashMap<String, Object> inserts = new HashMap<>();
/* 302 */           inserts.put("XMSC_INSERT_HEX_STRING", value);
/* 303 */           JMSException je2 = (JMSException)NLSServices.createException("JMSCMQ1044", inserts);
/* 304 */           throw je2;
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 5:
/* 310 */         result = Short.valueOf(value);
/*     */         break;
/*     */       
/*     */       case 7:
/* 314 */         result = Long.valueOf(value);
/*     */         break;
/*     */       
/*     */       case 8:
/* 318 */         result = Float.valueOf(value);
/*     */         break;
/*     */       
/*     */       case 9:
/* 322 */         result = Double.valueOf(value);
/*     */         break;
/*     */       
/*     */       case 3:
/* 326 */         result = Character.valueOf(expandRefs(value).charAt(0));
/*     */         break;
/*     */       
/*     */       case 4:
/* 330 */         result = Byte.valueOf(value);
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/* 335 */         info = new HashMap<>();
/* 336 */         info.put("info", "Unexpected RFH2 datatype numeric code.");
/* 337 */         info.put("datatype", Integer.valueOf(datatype));
/* 338 */         Trace.ffst("WMQMessageUtils", "deformatTypedElement(int,String)", "XN00Q001", info, JMSException.class);
/*     */         break;
/*     */     } 
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.data("WMQMessageUtils", "deformatTypedElement(int,String)", "De-formatted object", result);
/*     */     }
/*     */     
/* 345 */     return result;
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
/*     */   static Object deformatElement(String datatype, String value) throws JMSException {
/*     */     int numType;
/* 363 */     char openingQuote = datatype.charAt(0);
/*     */     
/* 365 */     if (openingQuote != '\'' && openingQuote != '"') {
/* 366 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0008", null);
/* 367 */       throw je;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 372 */     String type = datatype.substring(1, datatype.indexOf(openingQuote, 1));
/*     */ 
/*     */ 
/*     */     
/* 376 */     Object lookup = datatypeNameToNumber.get(type);
/* 377 */     if (lookup != null) {
/* 378 */       numType = ((Integer)lookup).intValue();
/*     */     } else {
/*     */       
/* 381 */       HashMap<String, Object> inserts = new HashMap<>();
/* 382 */       inserts.put("XMSC_INSERT_TYPE", type);
/* 383 */       JMSException je2 = (JMSException)NLSServices.createException("JMSCMQ1056", inserts);
/* 384 */       throw je2;
/*     */     } 
/*     */ 
/*     */     
/* 388 */     return deformatTypedElement(numType, value);
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
/*     */   private static String expandRefs(String input) throws JMSException {
/* 404 */     if (input.length() == 0) {
/* 405 */       String str = "";
/* 406 */       return str;
/*     */     } 
/*     */ 
/*     */     
/* 410 */     if (input.indexOf('&') == -1) {
/* 411 */       return input;
/*     */     }
/*     */     
/* 414 */     StringBuffer sb = new StringBuffer(input.length());
/*     */ 
/*     */     
/* 417 */     for (int i = 0; i < input.length(); i++) {
/* 418 */       char ch = input.charAt(i);
/* 419 */       if ('&' == ch) {
/* 420 */         String entity = input.substring(i + 1, i + 4);
/*     */         
/* 422 */         if (entity.equals("lt;")) {
/* 423 */           sb.append('<');
/* 424 */           i += 3;
/* 425 */         } else if (entity.equals("gt;")) {
/* 426 */           sb.append('>');
/* 427 */           i += 3;
/* 428 */         } else if (input.substring(i + 1, i + 5).equals("amp;")) {
/* 429 */           sb.append("&");
/* 430 */           i += 4;
/* 431 */         } else if (input.substring(i + 1, i + 6).equals("apos;")) {
/* 432 */           sb.append("'");
/* 433 */           i += 5;
/* 434 */         } else if (input.substring(i + 1, i + 6).equals("quot;")) {
/* 435 */           sb.append('"');
/* 436 */           i += 5;
/*     */         }
/* 438 */         else if (input.substring(i + 1, i + 3).equals("#x")) {
/* 439 */           char[] unscrambledChar = new char[2];
/* 440 */           int numEscapeChars = unscrambleUnicodeEscapedChar(input, i, unscrambledChar);
/* 441 */           sb.append(unscrambledChar);
/* 442 */           i += numEscapeChars - 1;
/*     */         } else {
/* 444 */           HashMap<String, Object> inserts = new HashMap<>();
/* 445 */           inserts.put("XMSC_INSERT_STRING", "&" + entity);
/* 446 */           JMSException je = (JMSException)NLSServices.createException("JMSCMQ1054", inserts);
/* 447 */           if (Trace.isOn) {
/* 448 */             Trace.throwing("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageUtils", "expandRefs(String)", (Throwable)je, 1);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 453 */           throw je;
/*     */         } 
/*     */       } else {
/* 456 */         sb.append(ch);
/*     */       } 
/*     */     } 
/* 459 */     String result = sb.toString();
/* 460 */     return result;
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
/*     */   private static int unscrambleUnicodeEscapedChar(String inputString, int startLocation, char[] returnedChar) throws JMSException {
/* 472 */     int endIndex = inputString.indexOf(';', startLocation);
/*     */     
/* 474 */     if (endIndex == -1) {
/* 475 */       String invalidEscapeSequence = inputString.substring(startLocation);
/* 476 */       HashMap<String, Object> inserts = new HashMap<>();
/* 477 */       inserts.put("XMSC_INSERT_STRING", invalidEscapeSequence);
/* 478 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ1054", inserts);
/* 479 */       if (Trace.isOn) {
/* 480 */         Trace.throwing("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageUtils", "unscrambleUnicodeEscapedChar(String,int,char[])", (Throwable)je, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 485 */       throw je;
/*     */     } 
/*     */     
/* 488 */     String unicodeSequence = inputString.substring(startLocation + 3, endIndex);
/*     */ 
/*     */     
/* 491 */     int codePointIntValue = (int)Long.parseLong(unicodeSequence, 16);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 496 */       char[] outputArray = Character.toChars(codePointIntValue);
/*     */ 
/*     */       
/* 499 */       if (returnedChar == null || outputArray.length > returnedChar.length) {
/* 500 */         HashMap<String, Object> inserts = new HashMap<>();
/* 501 */         inserts.put("XMSC_INSERT_STRING", "&#x" + unicodeSequence + ";\nUnable to fit into defined char[].  Space required:  " + outputArray.length);
/*     */         
/* 503 */         JMSException je = (JMSException)NLSServices.createException("JMSCMQ1054", inserts);
/* 504 */         if (Trace.isOn) {
/* 505 */           Trace.throwing("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageUtils", "unscrambleUnicodeEscapedChar(String,int,char[])", (Throwable)je, 3);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 510 */         throw je;
/*     */       } 
/*     */ 
/*     */       
/* 514 */       System.arraycopy(outputArray, 0, returnedChar, 0, outputArray.length);
/*     */     }
/* 516 */     catch (IllegalArgumentException iae) {
/*     */       
/* 518 */       if (Trace.isOn) {
/* 519 */         Trace.catchBlock("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageUtils", "unscrambleUnicodeEscapedChar(String,int,char[])", iae);
/*     */       }
/*     */ 
/*     */       
/* 523 */       HashMap<String, Object> inserts = new HashMap<>();
/* 524 */       inserts.put("XMSC_INSERT_STRING", "&#x" + unicodeSequence + ";");
/* 525 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ1054", inserts);
/* 526 */       if (Trace.isOn) {
/* 527 */         Trace.throwing("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageUtils", "unscrambleUnicodeEscapedChar(String,int,char[])", (Throwable)je, 2);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 532 */       throw je;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 537 */     return unicodeSequence.length() + 4;
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
/*     */   static void formatElement(String name, Object value, StringBuilder buffer) throws JMSException {
/* 550 */     if (Trace.isOn) {
/* 551 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageUtils", "formatElement(String,Object,StringBuffer)", new Object[] { name, value, buffer });
/*     */     }
/*     */     
/* 554 */     buffer.append("<");
/* 555 */     buffer.append(name);
/*     */ 
/*     */     
/* 558 */     if (value == null) {
/* 559 */       buffer.append(" xsi:nil='true'>");
/* 560 */       buffer.append("</");
/* 561 */       buffer.append(name);
/* 562 */       buffer.append(">");
/* 563 */       if (Trace.isOn) {
/* 564 */         Trace.exit("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageUtils", "formatElement(String,Object,StringBuffer)", 1);
/*     */       }
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 571 */     String type = datatypeElements.get(value.getClass());
/*     */     
/* 573 */     if (type.length() > 0) {
/* 574 */       buffer.append(" dt='" + type + "'");
/*     */     }
/*     */     
/* 577 */     buffer.append(">");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 584 */     if (value instanceof byte[]) {
/*     */       
/* 586 */       buffer.append(Utils.bytesToHex((byte[])value).toString());
/* 587 */     } else if (value instanceof Boolean) {
/* 588 */       if (((Boolean)value).equals(Boolean.TRUE)) {
/* 589 */         buffer.append("1");
/*     */       } else {
/* 591 */         buffer.append("0");
/*     */       } 
/* 593 */     } else if (value instanceof String) {
/* 594 */       backReference(buffer, (String)value);
/* 595 */     } else if (value instanceof Character) {
/* 596 */       backReference(buffer, ((Character)value).toString());
/*     */     } else {
/* 598 */       buffer.append(value);
/*     */     } 
/*     */ 
/*     */     
/* 602 */     buffer.append("</");
/* 603 */     buffer.append(name);
/* 604 */     buffer.append(">");
/* 605 */     if (Trace.isOn) {
/* 606 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageUtils", "formatElement(String,Object,StringBuffer)", 2);
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
/*     */   static void formatElement(String name, String type, String value, StringBuilder buffer) throws JMSException {
/* 629 */     buffer.append("<");
/* 630 */     buffer.append(name);
/*     */     
/* 632 */     if (!type.equals("")) {
/* 633 */       buffer.append(" dt=" + type);
/*     */     }
/*     */     
/* 636 */     buffer.append(">");
/*     */ 
/*     */ 
/*     */     
/* 640 */     buffer.append(value);
/*     */ 
/*     */     
/* 643 */     buffer.append("</");
/* 644 */     buffer.append(name);
/* 645 */     buffer.append(">");
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQMessageUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */