/*      */ package com.ibm.mq.jmqi.internal;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiImplementation;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.MQCNO;
/*      */ import com.ibm.mq.jmqi.MQGMO;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Hobj;
/*      */ import com.ibm.mq.jmqi.handles.PbyteBuffer;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*      */ import com.ibm.mq.jmqi.system.JmqiSP;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.mq.jmqi.system.LpiCALLOPT;
/*      */ import com.ibm.mq.jmqi.system.LpiNotifyDetails;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.StringWriter;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.net.URL;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.charset.CharacterCodingException;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JmqiTools
/*      */   extends JmqiObject
/*      */ {
/*      */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/JmqiTools.java";
/*      */   public static final String FFST_KEY_COMPCODE = "CompCode";
/*      */   public static final String FFST_KEY_REASON = "Reason";
/*      */   public static final String FFST_KEY_INSERT1 = "insert1";
/*      */   public static final String FFST_KEY_INSERT2 = "insert2";
/*      */   public static final String FFST_KEY_INSERT3 = "insert3";
/*      */   public static final String FFST_KEY_INSERT4 = "insert4";
/*      */   public static final String FFST_KEY_INSERT5 = "insert5";
/*      */   public static final String FFST_KEY_THROWABLE = "throwable";
/*   97 */   private static Map<String, ValueReference> environmentCache = new ConcurrentHashMap<>();
/*      */ 
/*      */   
/*  100 */   private static Map<String, ValueReference> registryCache = new ConcurrentHashMap<>();
/*      */ 
/*      */   
/*  103 */   private static final String NL = getNewline();
/*      */ 
/*      */   
/*      */   public static final int CMD_LEVEL_UNKNOWN = -1;
/*      */ 
/*      */   
/*      */   public static final int PRODUCTID_LENGTH = 12;
/*      */ 
/*      */   
/*      */   public static final String MQ_PRODUCT_ID_PFX = "MQ";
/*      */ 
/*      */   
/*      */   public static final String DEFAULT_APPNAME = "IBM MQ Client for Java";
/*      */ 
/*      */   
/*      */   private static final int QUIT = 0;
/*      */ 
/*      */   
/*      */   private static final int GET_MESSAGE = 1;
/*      */   
/*      */   private static final int CONVERT_MESSAGE = 2;
/*      */   
/*      */   private static int defaultMaxMsgSize;
/*      */   
/*      */   private static int threshold;
/*      */ 
/*      */   
/*      */   public JmqiTools(JmqiEnvironment env) {
/*  131 */     super(env);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void writeNativeNullPointerToBuffer(ByteBuffer buffer, int offset, int sizeofNativePointer) {
/*  151 */     for (int i = 0; i < sizeofNativePointer; i++) {
/*  152 */       buffer.put(offset + i, (byte)0);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void byteArrayCopy(byte[] source, int sourceOffset, byte[] target, int targetOffset, int dataLength) {
/*  178 */     for (int i = 0; i < dataLength; i++) {
/*  179 */       if (source != null && source.length > sourceOffset + i) {
/*  180 */         target[targetOffset + i] = source[sourceOffset + i];
/*      */       } else {
/*      */         
/*  183 */         target[targetOffset + i] = 0;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void cancelWaitingGets(JmqiEnvironment env, JmqiImplementation mq, Hconn notifyHconn, Hconn getterHconn, Hconn helperHconn, Pint pCompCode, Pint pReason) {
/*  199 */     if (Trace.isOn) {
/*  200 */       Trace.entry("com.ibm.mq.jmqi.internal.JmqiTools", "cancelWaitingGets(JmqiEnvironment,JmqiImplementation,Hconn,Hconn,Hconn,Pint,Pint)", new Object[] { env, mq, notifyHconn, getterHconn, helperHconn, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  205 */     LpiNotifyDetails notifyDetails = ((JmqiSystemEnvironment)env).newLpiNotifyDetails();
/*  206 */     byte[] connectionID = null;
/*      */     try {
/*  208 */       connectionID = helperHconn.getConnectionId();
/*      */     }
/*  210 */     catch (JmqiException jmqiException) {
/*  211 */       pCompCode.x = jmqiException.getCompCode();
/*  212 */       pReason.x = jmqiException.getReason();
/*      */     } 
/*  214 */     notifyDetails.setConnectionId(connectionID);
/*  215 */     notifyDetails.setVersion(1);
/*  216 */     notifyDetails.setReason(2107);
/*  217 */     mq.jmqiNotify(notifyHconn, getterHconn, 1, notifyDetails, pCompCode, pReason);
/*      */     
/*  219 */     if (Trace.isOn) {
/*  220 */       Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "cancelWaitingGets(JmqiEnvironment,JmqiImplementation,Hconn,Hconn,Hconn,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int countOf(String string, char candidate) {
/*  233 */     int count = 0;
/*  234 */     for (char c : string.toCharArray()) {
/*  235 */       if (c == candidate) {
/*  236 */         count++;
/*      */       }
/*      */     } 
/*  239 */     return count;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void hexDump(byte[] byteArray, ByteBuffer byteBuffer, int offset, int lengthP, StringBuffer dumpBuffer) {
/*  258 */     int length = lengthP;
/*  259 */     if (length > 65536) {
/*  260 */       length = 65536;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  265 */     char[][] rowChars = new char[2][63];
/*  266 */     for (int i = 0; i < 2; i++) {
/*  267 */       rowChars[i][0] = '0';
/*  268 */       rowChars[i][1] = 'x';
/*  269 */       rowChars[i][6] = ' ';
/*  270 */       rowChars[i][7] = ' ';
/*  271 */       rowChars[i][16] = ' ';
/*  272 */       rowChars[i][25] = ' ';
/*  273 */       rowChars[i][34] = ' ';
/*  274 */       rowChars[i][43] = ' ';
/*  275 */       rowChars[i][44] = ' ';
/*  276 */       rowChars[i][45] = '|';
/*  277 */       rowChars[i][62] = '|';
/*      */     } 
/*      */     
/*  280 */     boolean useArray = true;
/*  281 */     boolean validArray = false;
/*  282 */     if (byteArray != null) {
/*  283 */       validArray = true;
/*      */     }
/*  285 */     else if (byteBuffer != null) {
/*  286 */       useArray = false;
/*  287 */       validArray = true;
/*      */     } 
/*      */ 
/*      */     
/*  291 */     if ((validArray && offset >= 0 && length > 0 && useArray && byteArray.length >= offset + length) || (!useArray && byteBuffer.capacity() >= offset + length)) {
/*      */       
/*  293 */       int line = 0;
/*      */       
/*  295 */       int rowPosition = offset;
/*      */ 
/*      */       
/*  298 */       int skipBytes = rowPosition % 16;
/*  299 */       int bytesRemaining = length;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  304 */       int duplicateLines = 0;
/*      */ 
/*      */       
/*  307 */       char[] duplicateLine = null;
/*      */       
/*  309 */       while (bytesRemaining > 0) {
/*  310 */         int iBuf = line % 2;
/*  311 */         int bytesOnRow = (bytesRemaining >= 16) ? 16 : bytesRemaining;
/*      */         
/*  313 */         String rowPosStr = Integer.toHexString(rowPosition);
/*  314 */         int rowPosStrLen = rowPosStr.length(); int j;
/*  315 */         for (j = 0; j < 4; j++) {
/*  316 */           if (j < rowPosStrLen) {
/*  317 */             rowChars[iBuf][5 - j] = rowPosStr.charAt(rowPosStrLen - j - 1);
/*      */           } else {
/*      */             
/*  320 */             rowChars[iBuf][5 - j] = '0';
/*      */           } 
/*      */         } 
/*      */         
/*  324 */         for (j = 0; j < skipBytes; j++) {
/*  325 */           int hexPos = 8 + j / 4 + j * 2;
/*  326 */           int bytePos = 46 + j;
/*  327 */           rowChars[iBuf][hexPos] = ' ';
/*  328 */           rowChars[iBuf][hexPos + 1] = ' ';
/*  329 */           rowChars[iBuf][bytePos] = ' ';
/*      */         } 
/*      */         
/*  332 */         for (; j < 16; j++) {
/*      */           
/*  334 */           int hexPos = 8 + j / 4 + j * 2;
/*  335 */           int bytePos = 46 + j;
/*  336 */           if (j < bytesOnRow) {
/*      */             
/*  338 */             byte origByte = useArray ? byteArray[rowPosition + j] : byteBuffer.get(rowPosition + j);
/*  339 */             int tempVal = (origByte & 0xF0) >> 4;
/*  340 */             rowChars[iBuf][hexPos] = (char)((tempVal > 9) ? (97 + tempVal - 10) : (48 + tempVal));
/*  341 */             tempVal = origByte & 0xF;
/*  342 */             rowChars[iBuf][hexPos + 1] = (char)((tempVal > 9) ? (97 + tempVal - 10) : (48 + tempVal));
/*      */ 
/*      */             
/*  345 */             tempVal = origByte & 0xFF;
/*  346 */             if (tempVal >= 32 && tempVal < 127) {
/*  347 */               rowChars[iBuf][bytePos] = (char)tempVal;
/*      */             } else {
/*      */               
/*  350 */               rowChars[iBuf][bytePos] = '.';
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/*  355 */             rowChars[iBuf][hexPos] = ' ';
/*  356 */             rowChars[iBuf][hexPos + 1] = ' ';
/*  357 */             rowChars[iBuf][bytePos] = ' ';
/*      */           } 
/*      */         } 
/*  360 */         rowPosition += 16;
/*  361 */         bytesRemaining -= bytesOnRow;
/*      */ 
/*      */ 
/*      */         
/*  365 */         int nBuf = (line + 1) % 2;
/*  366 */         boolean isDup = false;
/*  367 */         if (line != 0) {
/*  368 */           isDup = true;
/*  369 */           for (j = 8; isDup && j <= 42; j++) {
/*  370 */             isDup = (isDup && rowChars[iBuf][j] == rowChars[nBuf][j]);
/*      */           }
/*      */         } 
/*      */         
/*  374 */         if (isDup) {
/*  375 */           duplicateLines++;
/*  376 */           duplicateLine = rowChars[iBuf];
/*      */         } 
/*      */ 
/*      */         
/*  380 */         if (!isDup || bytesRemaining == 0) {
/*      */ 
/*      */           
/*  383 */           if (duplicateLines == 1) {
/*  384 */             isDup = false;
/*  385 */             dumpBuffer.append(NL);
/*  386 */             dumpBuffer.append(duplicateLine);
/*      */           
/*      */           }
/*  389 */           else if (duplicateLines > 1) {
/*  390 */             dumpBuffer.append(NL);
/*  391 */             dumpBuffer.append(duplicateLines);
/*  392 */             dumpBuffer.append(" lines suppressed, same as above");
/*      */           } 
/*      */           
/*  395 */           duplicateLines = 0;
/*      */ 
/*      */           
/*  398 */           if (!isDup) {
/*      */             
/*  400 */             if (line != 0) {
/*  401 */               dumpBuffer.append(NL);
/*      */             }
/*  403 */             dumpBuffer.append(rowChars[iBuf]);
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  408 */         skipBytes = 0;
/*      */         
/*  410 */         line++;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String arrayToHexString(byte[] array) {
/*      */     String retVal;
/*  426 */     if (array != null) {
/*  427 */       StringBuffer hexString = new StringBuffer(array.length);
/*      */ 
/*      */       
/*  430 */       for (int i = 0; i < array.length; i++) {
/*  431 */         int hexVal = (array[i] & 0xF0) >> 4;
/*  432 */         char hexChar = (char)((hexVal > 9) ? (65 + hexVal - 10) : (48 + hexVal));
/*  433 */         hexString.append(hexChar);
/*  434 */         hexVal = array[i] & 0xF;
/*  435 */         hexChar = (char)((hexVal > 9) ? (65 + hexVal - 10) : (48 + hexVal));
/*  436 */         hexString.append(hexChar);
/*      */       } 
/*  438 */       retVal = hexString.toString();
/*      */     } else {
/*      */       
/*  441 */       retVal = "<null>";
/*      */     } 
/*  443 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getRemoteProductIdCmdLevel(String productId) {
/*      */     int retVal;
/*  459 */     if (productId == null || productId
/*  460 */       .length() != 12 || 
/*  461 */       !productId.startsWith("MQ") || productId
/*  462 */       .charAt(4) != '0' || productId
/*  463 */       .charAt(6) != '0' || productId
/*  464 */       .charAt(8) != '0') {
/*  465 */       retVal = -1;
/*      */     } else {
/*      */ 
/*      */       
/*      */       try {
/*  470 */         retVal = Integer.parseInt(productId.substring(4, 6)) * 100 + Integer.parseInt(productId.substring(6, 8)) * 10 + Integer.parseInt(productId.substring(8, 10));
/*      */       }
/*  472 */       catch (Exception e) {
/*  473 */         if (Trace.isOn) {
/*  474 */           Trace.catchBlock(productId, "com.ibm.mq.jmqi.internal.JmqiTools", "getRemoteProductIdCmdLevel(String)", e);
/*      */         }
/*  476 */         retVal = -1;
/*      */       } 
/*      */     } 
/*  479 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] getUtf8Bytes(JmqiEnvironment env, String value, String name) throws JmqiException {
/*  494 */     byte[] retVal = null;
/*  495 */     if (value == null) {
/*  496 */       retVal = new byte[0];
/*      */     } else {
/*      */       
/*      */       try {
/*  500 */         retVal = value.getBytes("UTF-8");
/*      */       }
/*  502 */       catch (UnsupportedEncodingException e) {
/*  503 */         JmqiException e2 = new JmqiException(env, 9546, new String[] { getExSumm(e), null, getFailingCall(e) }, 2, 2195, e);
/*  504 */         throw e2;
/*      */       } 
/*      */     } 
/*  507 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void traceApiBufferFromStaticMethod(String parentObject, String fid, ByteBuffer buffer, int buflen) {
/*  522 */     if (buffer == null) {
/*  523 */       Trace.data(parentObject, fid, "Buffer", null);
/*      */     
/*      */     }
/*  526 */     else if (buflen < 0) {
/*  527 */       Trace.data(parentObject, fid, "Buffer Length", Integer.valueOf(buflen));
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  532 */       int oldPosition = buffer.position();
/*  533 */       int oldLimit = buffer.limit();
/*      */       
/*  535 */       if (buflen > buffer.capacity()) {
/*  536 */         buffer.position(0);
/*  537 */         buffer.limit(0);
/*      */         
/*  539 */         Trace.data(parentObject, fid, "Buffer (Capacity < BufferLength)", buffer);
/*      */       
/*      */       }
/*  542 */       else if (buflen <= 4096) {
/*  543 */         buffer.position(0);
/*  544 */         buffer.limit(buflen);
/*      */         
/*  546 */         Trace.data(parentObject, fid, "Buffer", buffer);
/*      */       }
/*      */       else {
/*      */         
/*  550 */         buffer.position(0);
/*  551 */         buffer.limit(4096);
/*      */         
/*  553 */         Trace.data(parentObject, fid, "Buffer (0..4095B)", buffer);
/*      */       } 
/*      */ 
/*      */       
/*  557 */       buffer.limit(oldLimit);
/*  558 */       buffer.position(oldPosition);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void traceApiBuffer(Object parentObject, String fid, ByteBuffer buffer, int buflen) {
/*  573 */     if (buffer == null) {
/*  574 */       Trace.data(parentObject, fid, "Buffer", null);
/*      */     
/*      */     }
/*  577 */     else if (buflen < 0) {
/*  578 */       Trace.data(parentObject, fid, "Buffer Length", Integer.valueOf(buflen));
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  583 */       int oldPosition = buffer.position();
/*  584 */       int oldLimit = buffer.limit();
/*      */       
/*  586 */       if (buflen > buffer.capacity()) {
/*  587 */         buffer.position(0);
/*  588 */         buffer.limit(0);
/*      */         
/*  590 */         Trace.data(parentObject, fid, "Buffer (Capacity < BufferLength)", buffer);
/*      */       
/*      */       }
/*  593 */       else if (buflen <= 4096) {
/*  594 */         buffer.position(0);
/*  595 */         buffer.limit(buflen);
/*      */         
/*  597 */         Trace.data(parentObject, fid, "Buffer", buffer);
/*      */       }
/*      */       else {
/*      */         
/*  601 */         buffer.position(0);
/*  602 */         buffer.limit(4096);
/*      */         
/*  604 */         Trace.data(parentObject, fid, "Buffer (0..4095B)", buffer);
/*      */       } 
/*      */ 
/*      */       
/*  608 */       buffer.limit(oldLimit);
/*  609 */       buffer.position(oldPosition);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String safeString(Object object) {
/*  619 */     String traceRet1 = (object == null) ? "<null>" : object.toString();
/*  620 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String fix(int number, int limit) {
/*  630 */     String string = Integer.toString(number);
/*  631 */     String result = string;
/*  632 */     if (string.length() < limit) {
/*  633 */       StringBuffer buffer = new StringBuffer();
/*  634 */       for (int i = string.length(); i < limit; i++) {
/*  635 */         buffer.append('0');
/*      */       }
/*  637 */       buffer.append(string);
/*  638 */       result = buffer.toString();
/*      */     }
/*  640 */     else if (string.length() > limit) {
/*  641 */       result = string.substring(0, limit);
/*      */     } 
/*  643 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String convertBytes(JmqiEnvironment env, JmqiCodepage cp, byte[] input, int start, int length) throws JmqiException {
/*      */     String result;
/*      */     try {
/*  661 */       result = cp.bytesToString(input, start, length);
/*      */     }
/*  663 */     catch (CharacterCodingException cce) {
/*  664 */       if (Trace.isOn) {
/*  665 */         Trace.catchBlock("com.ibm.mq.jmqi.internal.JmqiTools", "convertBytes(JmqiEnvironment,JmqiCodepage,byte[],int,int)", cce, 1);
/*      */       }
/*      */ 
/*      */       
/*  669 */       HashMap<String, Object> ffstInfo = new HashMap<>();
/*  670 */       ffstInfo.put("Ccsid", Integer.valueOf(cp.getCCSID()));
/*      */       try {
/*  672 */         ffstInfo.put("String to convert", new String(input, start, length, env.getNativeCharSetName()));
/*      */       }
/*  674 */       catch (UnsupportedEncodingException unsupportedEncodingException) {}
/*      */ 
/*      */       
/*  677 */       ffstInfo.put("Exception", cce.toString());
/*  678 */       ffstInfo.put("Description", "CharacterCodingException");
/*  679 */       Trace.ffst("com.ibm.mq.jmqi.internal.JmqiTools", "convertBytes(JmqiEnvironment,JmqiCodepage,byte[],int,int,int)", "03", ffstInfo, null);
/*      */       
/*  681 */       JmqiException je = new JmqiException(env, 9530, new String[] { Integer.toString(cp.getCCSID()), null, null, null, "???" }, 0, 0, cce);
/*  682 */       if (Trace.isOn) {
/*  683 */         Trace.throwing("com.ibm.mq.jmqi.internal.JmqiTools", "convertBytes(JmqiEnvironment,JmqiCodepage,byte[],int,int,int)", cce, 1);
/*      */       }
/*  685 */       throw je;
/*      */     } 
/*  687 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean skipQsgCheck(JmqiEnvironment env, JmqiMQ mq, int platform) {
/*  696 */     return (platform != 1 || env.getConfiguration().getBoolValue(Configuration.skipQSGNameCheck) == true || ((JmqiSP)mq).isIMS() == true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static QueueManagerInfo getQueueManagerInfo(JmqiEnvironment env, JmqiMQ mq, Hconn hconn) throws JmqiException {
/*  711 */     if (Trace.isOn) {
/*  712 */       Trace.entry("com.ibm.mq.jmqi.internal.JmqiTools", "getQueueManagerInfo(JmqiEnvironment,JmqiMQ,Hconn)", new Object[] { env, mq, hconn });
/*      */     }
/*      */     
/*  715 */     QueueManagerInfo info = env.newQueueManagerInfo();
/*  716 */     Pint cc = env.newPint(0);
/*  717 */     Pint rc = env.newPint(0);
/*  718 */     MQOD mqod = env.newMQOD();
/*  719 */     mqod.setObjectType(5);
/*  720 */     int options = 8224;
/*  721 */     Phobj phobj = env.newPhobj();
/*      */     
/*  723 */     mq.MQOPEN(hconn, mqod, options, phobj, cc, rc);
/*      */     
/*  725 */     if (rc.x != 0) {
/*  726 */       JmqiException e = new JmqiException(env, 9509, new String[] { Integer.toString(rc.x), null, null, null, "???" }, cc.x, rc.x, (Throwable)env.getLastException());
/*  727 */       if (Trace.isOn) {
/*  728 */         Trace.throwing("com.ibm.mq.jmqi.internal.JmqiTools", "getQueueManagerInfo(JmqiEnvironment,JmqiMQ,Hconn)", (Throwable)e, 7);
/*      */       }
/*  730 */       throw e;
/*      */     } 
/*      */     try {
/*      */       JmqiCodepage cp;
/*  734 */       Hobj hobj = phobj.getHobj();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  742 */       int[] selectors = { 31, 32, 2, 2015, 2032, 2040, 273 };
/*      */       
/*  744 */       int[] intAttrs = new int[4];
/*  745 */       byte[] charAttrs = new byte[100];
/*  746 */       int selectorCount = 5;
/*  747 */       boolean gotQsg = false;
/*      */       
/*  749 */       intAttrs[3] = 0;
/*  750 */       if (hconn.isConnToZos()) {
/*  751 */         selectorCount++;
/*  752 */         if (mq.isLocal() || 
/*  753 */           getRemoteProductIdCmdLevel(hconn.getRemoteProductId()) >= 904) {
/*  754 */           selectorCount++;
/*      */         }
/*      */       } else {
/*      */         
/*  758 */         selectors[5] = 273;
/*  759 */         if (mq.isLocal() || 
/*  760 */           getRemoteProductIdCmdLevel(hconn.getRemoteProductId()) >= 905) {
/*  761 */           selectorCount++;
/*      */         }
/*      */       } 
/*      */       
/*  765 */       mq.MQINQ(hconn, hobj, selectorCount, selectors, intAttrs.length, intAttrs, charAttrs.length, charAttrs, cc, rc);
/*      */       
/*  767 */       if (rc.x == 2067) {
/*  768 */         if (hconn.isConnToZos() && mq.isLocal()) {
/*  769 */           selectorCount = 6;
/*      */         } else {
/*      */           
/*  772 */           selectorCount = 5;
/*      */         } 
/*  774 */         mq.MQINQ(hconn, hobj, selectorCount, selectors, intAttrs.length, intAttrs, charAttrs.length, charAttrs, cc, rc);
/*      */       } 
/*      */       
/*  777 */       if (rc.x != 0) {
/*  778 */         JmqiException e = new JmqiException(env, 9530, new String[] { Integer.toString(rc.x), null, null, null, "???" }, cc.x, rc.x, (Throwable)env.getLastException());
/*  779 */         if (Trace.isOn) {
/*  780 */           Trace.throwing("com.ibm.mq.jmqi.internal.JmqiTools", "getQueueManagerInfo(JmqiEnvironment,JmqiMQ,Hconn)", (Throwable)e, 6);
/*      */         }
/*  782 */         throw e;
/*      */       } 
/*      */       
/*  785 */       info.setCommandLevel(intAttrs[0]);
/*  786 */       info.setPlatform(intAttrs[1]);
/*  787 */       info.setCcsid(intAttrs[2]);
/*  788 */       info.setAdvCap(intAttrs[3]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  796 */       int ccsid = mq.isLocal() ? info.getCcsid() : hconn.getCcsid();
/*      */       
/*      */       try {
/*  799 */         cp = JmqiCodepage.getJmqiCodepage(env, ccsid, null, null);
/*      */       }
/*  801 */       catch (UnsupportedEncodingException e1) {
/*  802 */         if (Trace.isOn) {
/*  803 */           Trace.catchBlock("com.ibm.mq.jmqi.internal.JmqiTools", "getQueueManagerInfo(JmqiEnvironment,JmqiMQ,Hconn)", e1, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  808 */         HashMap<String, Object> ffstInfo = new HashMap<>();
/*  809 */         ffstInfo.put("Platform", Integer.valueOf(intAttrs[1]));
/*  810 */         ffstInfo.put("Ccsid", Integer.valueOf(intAttrs[2]));
/*      */         try {
/*  812 */           ffstInfo.put("QM Name", new String(charAttrs, 0, 48, env.getNativeCharSetName()));
/*      */         }
/*  814 */         catch (UnsupportedEncodingException unsupportedEncodingException) {}
/*      */ 
/*      */         
/*  817 */         ffstInfo.put("Description", "No codepage could be found");
/*  818 */         Trace.ffst("com.ibm.mq.jmqi.internal.JmqiTools", "getQueueManagerInfo(JmqiEnvironment,JmqiMQ,Hconn)", "02", ffstInfo, null);
/*      */         
/*  820 */         JmqiException traceRet1 = new JmqiException(env, 9530, new String[] { Integer.toString(ccsid), null, null, null, "???" }, 2, 2195, e1);
/*      */         
/*  822 */         if (Trace.isOn) {
/*  823 */           Trace.throwing("com.ibm.mq.jmqi.internal.JmqiTools", "getQueueManagerInfo(JmqiEnvironment,JmqiMQ,Hconn)", (Throwable)traceRet1, 3);
/*      */         }
/*  825 */         throw traceRet1;
/*      */       } 
/*  827 */       info.setName(convertBytes(env, cp, charAttrs, 0, 48));
/*  828 */       info.setUid(convertBytes(env, cp, charAttrs, 48, 48));
/*  829 */       if (selectorCount >= 6 && selectors[5] == 2040) {
/*  830 */         info.setQsgName(convertBytes(env, cp, charAttrs, 96, 4).trim());
/*  831 */         gotQsg = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  840 */       if ((!gotQsg && !skipQsgCheck(env, mq, info.getPlatform())) || (selectorCount < 7 && hconn
/*  841 */         .isConnToZos() && info.getCommandLevel() >= 904) || (selectorCount < 6 && info
/*  842 */         .getPlatform() != 1 && info.getCommandLevel() >= 905)) {
/*      */         
/*  844 */         int moreSelectors[], moreSelectorsCount = 1;
/*  845 */         int[] advCapAttr = { 0 };
/*  846 */         byte[] qsgNameAttr = new byte[4];
/*  847 */         int qsgAttrCount = 0;
/*  848 */         int advCapAttrCount = 0;
/*      */         
/*  850 */         if (info.getPlatform() == 1) {
/*  851 */           moreSelectors = new int[] { 2040, 273 };
/*  852 */           qsgAttrCount = 1;
/*  853 */           if (info.getCommandLevel() >= 904) {
/*  854 */             advCapAttrCount = 1;
/*  855 */             moreSelectorsCount = 2;
/*      */           } 
/*      */         } else {
/*      */           
/*  859 */           moreSelectors = new int[] { 273 };
/*  860 */           advCapAttrCount = 1;
/*      */         } 
/*      */         
/*  863 */         mq.MQINQ(hconn, hobj, moreSelectorsCount, moreSelectors, advCapAttrCount, advCapAttr, qsgNameAttr.length, qsgNameAttr, cc, rc);
/*      */ 
/*      */         
/*  866 */         if (rc.x == 0) {
/*      */           
/*  868 */           if (qsgAttrCount > 0) {
/*  869 */             String qsgName = convertBytes(env, cp, qsgNameAttr, 0, 4);
/*  870 */             info.setQsgName(qsgName.trim());
/*  871 */             gotQsg = true;
/*      */           } 
/*  873 */           if (advCapAttrCount > 0) {
/*  874 */             info.setAdvCap(advCapAttr[0]);
/*      */ 
/*      */           
/*      */           }
/*      */         
/*      */         }
/*  880 */         else if (Trace.isOn) {
/*  881 */           Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getQueueManagerInfo(JmqiEnvironment,JmqiMQ,Hconn", "Unable to get QSGName, due to reason: " + rc.x);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  886 */       else if (info.getQsgName() == null && info.getPlatform() == 1 && skipQsgCheck(env, mq, info.getPlatform())) {
/*  887 */         if (Trace.isOn) {
/*  888 */           Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getQueueManagerInfo(JmqiEnvironment,JmqiMQ,Hconn", "Skipping QSGName inquire due to property value. Setting QSGName to NOTCHECKED");
/*      */         }
/*      */ 
/*      */         
/*  892 */         info.setQsgName("NOTCHECKED");
/*      */       } 
/*      */     } finally {
/*      */       
/*  896 */       mq.MQCLOSE(hconn, phobj, 0, cc, rc);
/*      */     } 
/*  898 */     if (Trace.isOn) {
/*  899 */       Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "getQueueManagerInfo(JmqiEnvironment,JmqiMQ,Hconn)", info);
/*      */     }
/*  901 */     return info;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int alignToGrain(int granularity, int pos) {
/*  915 */     int padding = 0;
/*  916 */     int leftOver = pos % granularity;
/*  917 */     if (leftOver > 0) {
/*  918 */       padding = granularity - leftOver;
/*      */     }
/*  920 */     return padding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String tracePassword(String password) {
/*  929 */     StringBuffer buffer = new StringBuffer();
/*  930 */     if (password == null) {
/*  931 */       buffer.append("<null>");
/*      */     } else {
/*      */       
/*  934 */       buffer.append("length:");
/*  935 */       buffer.append(password.length());
/*      */     } 
/*  937 */     String traceRet1 = buffer.toString();
/*  938 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String left(String s, int width) {
/*  951 */     String traceRet1 = left(s, width, ' ');
/*  952 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String left(String s, int width, char fillChar) {
/*  966 */     if (s.length() >= width) {
/*  967 */       return s;
/*      */     }
/*  969 */     StringBuffer sb = new StringBuffer(width);
/*  970 */     sb.append(s);
/*  971 */     for (int i = width - s.length(); --i >= 0;) {
/*  972 */       sb.append(fillChar);
/*      */     }
/*  974 */     String traceRet1 = sb.toString();
/*  975 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String right(String s, int width) {
/*  988 */     String traceRet1 = right(s, width, ' ');
/*  989 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String right(String s, int width, char fillChar) {
/* 1003 */     if (s.length() >= width) {
/* 1004 */       return s;
/*      */     }
/* 1006 */     StringBuffer sb = new StringBuffer(width);
/* 1007 */     for (int i = width - s.length(); --i >= 0;) {
/* 1008 */       sb.append(fillChar);
/*      */     }
/* 1010 */     sb.append(s);
/* 1011 */     String traceRet1 = sb.toString();
/* 1012 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getExSumm(Throwable e) {
/* 1024 */     String exceptionSummary = null;
/* 1025 */     if (e != null) {
/* 1026 */       exceptionSummary = e.getClass().getName() + "[" + e.getLocalizedMessage() + "]";
/*      */     }
/* 1028 */     return exceptionSummary;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getFailingCall(Throwable e) {
/* 1040 */     String failingCall = null;
/* 1041 */     if (e != null) {
/* 1042 */       StackTraceElement[] stack = e.getStackTrace();
/* 1043 */       if (stack != null && stack.length > 0) {
/* 1044 */         failingCall = stack[0].getClassName();
/* 1045 */         int lastDot = failingCall.lastIndexOf('.');
/* 1046 */         if (lastDot > 0) {
/* 1047 */           failingCall = failingCall.substring(lastDot + 1);
/*      */         }
/* 1049 */         failingCall = failingCall + "." + stack[0].getMethodName();
/*      */       } 
/*      */     } 
/* 1052 */     return failingCall;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Class<?> dynamicLoadClass(JmqiEnvironment env, final String name, final Class<?> loadingClass) throws ClassNotFoundException {
/* 1088 */     Class<?> result = null;
/*      */     
/* 1090 */     Object[] results = AccessController.<Object[]>doPrivileged(new PrivilegedAction<Object[]>()
/*      */         {
/*      */           
/*      */           public Object[] run()
/*      */           {
/* 1095 */             Class<?> cls = null;
/*      */             
/* 1097 */             Exception exception = null;
/*      */             
/* 1099 */             ClassLoader threadClassloader = Thread.currentThread().getContextClassLoader();
/* 1100 */             ClassLoader classClassloader = loadingClass.getClassLoader();
/*      */             
/* 1102 */             if (threadClassloader != null) {
/*      */               try {
/* 1104 */                 if (Trace.isOn) {
/* 1105 */                   Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "dynamicLoadClass(final JmqiEnvironment , final String , final Class<?> )", "Classload Step 1: Threadcontext Classloader", threadClassloader);
/*      */                 }
/*      */                 
/* 1108 */                 cls = Class.forName(name, true, threadClassloader);
/* 1109 */                 if (Trace.isOn) {
/* 1110 */                   Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "dynamicLoadClass(final JmqiEnvironment , final String , final Class<?> )", "Step 1 After load:", cls);
/*      */                 }
/*      */               }
/* 1113 */               catch (ClassNotFoundException classNotFoundException) {}
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1121 */             if (cls == null && classClassloader != null) {
/*      */               try {
/* 1123 */                 if (Trace.isOn) {
/* 1124 */                   Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "dynamicLoadClass(final JmqiEnvironment , final String , final Class<?> )", "Classload Step 2: Loading Class Classlodaer ", classClassloader);
/*      */                 }
/*      */                 
/* 1127 */                 cls = Class.forName(name, true, classClassloader);
/* 1128 */                 if (Trace.isOn) {
/* 1129 */                   Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "dynamicLoadClass(final JmqiEnvironment , final String , final Class<?> )", "Step 2 After load:", cls);
/*      */                 }
/*      */               }
/* 1132 */               catch (ClassNotFoundException classNotFoundException) {}
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1139 */             if (cls == null) {
/*      */               try {
/* 1141 */                 if (Trace.isOn) {
/* 1142 */                   Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "dynamicLoadClass(final JmqiEnvironment , final String , final Class<?> )", "Classload Step 3: class.forName()", "not using specific classloader");
/*      */                 }
/*      */                 
/* 1145 */                 cls = Class.forName(name);
/* 1146 */                 if (Trace.isOn) {
/* 1147 */                   Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "dynamicLoadClass(final JmqiEnvironment , final String , final Class<?> )", "Step 3 After class:", cls);
/* 1148 */                   Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "dynamicLoadClass(final JmqiEnvironment , final String , final Class<?> )", "Step 3 Classloader", cls
/* 1149 */                       .getClassLoader().toString());
/*      */                 }
/*      */               
/* 1152 */               } catch (ClassNotFoundException e) {
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1157 */                 exception = e;
/*      */               } 
/*      */             }
/*      */ 
/*      */             
/* 1162 */             Object[] traceRet1 = { cls, exception };
/* 1163 */             return traceRet1;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1168 */     if (results[1] != null && results[1] instanceof ClassNotFoundException) {
/* 1169 */       ClassNotFoundException traceRet2 = (ClassNotFoundException)results[1];
/*      */       
/* 1171 */       if (Trace.isOn) {
/* 1172 */         StringBuffer sb = new StringBuffer();
/* 1173 */         sb.append("Throwing expected exception: ");
/* 1174 */         sb.append(traceRet2.getClass().getName());
/* 1175 */         sb.append(": ");
/* 1176 */         sb.append(traceRet2.getMessage());
/* 1177 */         Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "dynamicLoadClass(final JmqiEnvironment , final String , final Class<?> )", sb.toString(), null);
/*      */       } 
/* 1179 */       throw traceRet2;
/*      */     } 
/* 1181 */     if (results[0] instanceof Class) {
/* 1182 */       result = (Class)results[0];
/*      */     }
/*      */     
/* 1185 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1194 */   private static Configuration configuration = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String REGSTR_TOKEN = "REG_SZ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ByteBuffer getMessage(JmqiEnvironment env, JmqiImplementation mq, Hconn hconn, Hobj hobj, MQMD md, MQGMO gmo, int expectedMsgLength, int maxMsgLengthP, PbyteBuffer pByteBuffer, Pint pMsgTooSmallForBufferCount, Pint pDataLength, Pint pCompCode, Pint pReason) {
/* 1217 */     if (Trace.isOn) {
/* 1218 */       Trace.entry("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", new Object[] { env, mq, hconn, hobj, md, gmo, 
/*      */             
/* 1220 */             Integer.valueOf(expectedMsgLength), 
/* 1221 */             Integer.valueOf(maxMsgLengthP), pByteBuffer, pMsgTooSmallForBufferCount, pDataLength, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/* 1225 */     if (pByteBuffer == null) {
/* 1226 */       NullPointerException npe = new NullPointerException();
/* 1227 */       if (Trace.isOn) {
/* 1228 */         Trace.throwing("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", npe);
/*      */       }
/*      */       
/* 1231 */       if (Trace.isOn) {
/* 1232 */         Trace.throwing("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", npe);
/*      */       }
/*      */ 
/*      */       
/* 1236 */       throw npe;
/*      */     } 
/*      */ 
/*      */     
/* 1240 */     if (Trace.isOn) {
/* 1241 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "__________");
/*      */       
/* 1243 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "jmqiGet >>");
/*      */       
/* 1245 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Hconn", hconn);
/*      */       
/* 1247 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Hobj", hobj);
/*      */       
/* 1249 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "MQMD", md);
/*      */       
/* 1251 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "MQGMO", gmo);
/*      */       
/* 1253 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "expectedMsgLength", 
/* 1254 */           Integer.toString(expectedMsgLength));
/* 1255 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "maxMsgLength", 
/* 1256 */           Integer.toString(maxMsgLengthP));
/* 1257 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "pMsgTooSmallForBufferCount", pMsgTooSmallForBufferCount);
/*      */       
/* 1259 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Datalength", pDataLength);
/*      */       
/* 1261 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "CompCode", pCompCode);
/*      */       
/* 1263 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Reason", pReason);
/*      */       
/* 1265 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "__________");
/*      */     } 
/*      */     
/* 1268 */     int maxMsgLength = maxMsgLengthP;
/* 1269 */     if (configuration == null) {
/* 1270 */       configuration = env.getConfiguration();
/* 1271 */       defaultMaxMsgSize = configuration.getIntValue(Configuration.defaultMaxMsgSizeProperty);
/* 1272 */       threshold = configuration.getIntValue(Configuration.smallMsgsBufferReductionThresholdProperty);
/*      */     } 
/*      */     
/* 1275 */     int initialBufferSize = (expectedMsgLength < 0) ? defaultMaxMsgSize : expectedMsgLength;
/* 1276 */     initialBufferSize = Math.min(initialBufferSize, maxMsgLength);
/* 1277 */     initialBufferSize = Math.max(initialBufferSize, pByteBuffer.getHighWaterMark());
/* 1278 */     ByteBuffer buffer = pByteBuffer.getBuffer();
/* 1279 */     if (buffer == null || buffer.capacity() < initialBufferSize) {
/* 1280 */       buffer = setBufferSize(pByteBuffer, initialBufferSize);
/*      */     }
/*      */ 
/*      */     
/* 1284 */     int reqEncoding = md.getEncoding();
/* 1285 */     int reqCCSID = md.getCodedCharSetId();
/* 1286 */     byte[] reqMsgId = (byte[])md.getMsgId().clone();
/* 1287 */     byte[] reqCorrelId = (byte[])md.getCorrelId().clone();
/* 1288 */     int appOptions = gmo.getOptions();
/* 1289 */     boolean callExitOnLenErr = false;
/*      */     
/* 1291 */     Pint returnedLength = env.newPint();
/* 1292 */     int bufferLength = 0;
/*      */     
/* 1294 */     buffer.clear();
/*      */ 
/*      */     
/* 1297 */     boolean retryingMQGet = false;
/*      */ 
/*      */     
/* 1300 */     long startTime = System.currentTimeMillis();
/*      */     
/* 1302 */     Integer originalWaitInterval = Integer.valueOf(gmo.getWaitInterval());
/* 1303 */     boolean waitIntervalChanged = false;
/* 1304 */     int originalGmoOptions = gmo.getOptions();
/* 1305 */     boolean gmoOptionsChanged = false;
/* 1306 */     byte[] originalReqMsgId = new byte[24];
/* 1307 */     byteArrayCopy(md.getMsgId(), 0, originalReqMsgId, 0, 24);
/* 1308 */     if (Trace.isOn) {
/* 1309 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Message ID is ", 
/* 1310 */           arrayToHexString(originalReqMsgId));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1315 */     int originalMatchOptions = gmo.getMatchOptions();
/*      */ 
/*      */ 
/*      */     
/* 1319 */     int cmdLevel = 0;
/*      */     try {
/* 1321 */       cmdLevel = hconn.getCmdLevel();
/* 1322 */       if (cmdLevel >= 701)
/*      */       {
/*      */         
/* 1325 */         if (gmo.getVersion() < 3) {
/* 1326 */           gmo.setVersion(3);
/*      */         }
/*      */       }
/*      */     }
/* 1330 */     catch (JmqiException e) {
/* 1331 */       if (Trace.isOn) {
/* 1332 */         Trace.catchBlock("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", (Throwable)e);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1337 */       if (Trace.isOn) {
/* 1338 */         Trace.catchBlock("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", (Throwable)e);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1345 */     int state = 1;
/* 1346 */     while (state != 0) {
/* 1347 */       int availableLength; JmqiTls jTls; int oldBufferLength; boolean classesForJavaMQGET; boolean finished; switch (state) {
/*      */ 
/*      */ 
/*      */         
/*      */         case 1:
/* 1352 */           if (Trace.isOn) {
/* 1353 */             Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Calling jmqiGetMessage for ID ", 
/*      */                 
/* 1355 */                 arrayToHexString(md.getMsgId()));
/*      */           }
/* 1357 */           bufferLength = Math.min(buffer.capacity(), maxMsgLength);
/* 1358 */           mq.jmqiGetInternal(hconn, hobj, md, gmo, bufferLength, buffer, pDataLength, pCompCode, pReason);
/* 1359 */           switch (pReason.x) {
/*      */             case 0:
/* 1361 */               state = 0;
/*      */               continue;
/*      */             case 2161:
/*      */             case 2202:
/* 1365 */               if (Trace.isOn) {
/* 1366 */                 Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Quiescing state detected ", null);
/*      */               }
/*      */ 
/*      */               
/* 1370 */               if ((gmo.getOptions() & 0x2000) == 0) {
/* 1371 */                 if ((gmo.getOptions() & 0x1) != 0 && gmo.getWaitInterval() != 0) {
/*      */                   
/* 1373 */                   if (Trace.isOn) {
/* 1374 */                     Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Wait != 0, so Quiescing state tolerated - retrying ", null);
/*      */                   }
/*      */ 
/*      */ 
/*      */                   
/* 1379 */                   state = setupToRetryMQGET(md, gmo, startTime, originalWaitInterval, originalReqMsgId, reqCorrelId, reqEncoding, reqCCSID);
/* 1380 */                   pReason.x = 0;
/*      */                   continue;
/*      */                 } 
/* 1383 */                 if (Trace.isOn) {
/* 1384 */                   Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Wait = 0, so Quiescing state not tolerated - treat as nothing found ", null);
/*      */                 }
/*      */ 
/*      */ 
/*      */                 
/* 1389 */                 state = 0;
/* 1390 */                 pReason.x = 2033;
/*      */                 
/*      */                 continue;
/*      */               } 
/*      */               
/* 1395 */               if (Trace.isOn) {
/* 1396 */                 Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Quiescing state not tolerated", null);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/* 1401 */               state = 0;
/*      */               continue;
/*      */ 
/*      */             
/*      */             case 2010:
/* 1406 */               md.setEncoding(reqEncoding);
/* 1407 */               md.setCodedCharSetId(reqCCSID);
/* 1408 */               md.setMsgId(reqMsgId);
/* 1409 */               md.setCorrelId(reqCorrelId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1417 */               jTls = ((JmqiSystemEnvironment)env).getJmqiTls(null);
/*      */               
/* 1419 */               classesForJavaMQGET = jTls.getClassesForJavaMQGET();
/* 1420 */               if (classesForJavaMQGET) {
/*      */                 
/* 1422 */                 boolean maxMsgLengthSpecified = jTls.getClassesForJavaMQGETCalledWithMaxMsgLength();
/* 1423 */                 if (maxMsgLengthSpecified)
/*      */                 {
/* 1425 */                   if (Trace.isOn) {
/* 1426 */                     Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "The application trying to get a message is using the MQ classes for Java API and has explicitly provided a maximum message length of " + expectedMsgLength);
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/* 1431 */                     Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "The channel's MAXMSGL is " + pDataLength.x + ". Returing MQRC_DATA_LENGTH_ERROR");
/*      */                   } 
/*      */ 
/*      */                   
/* 1435 */                   state = 0;
/*      */                 
/*      */                 }
/*      */                 else
/*      */                 {
/*      */                   
/* 1441 */                   if (Trace.isOn) {
/* 1442 */                     Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "The application trying to get a message is using the MQ classes for Java API and did not specify a maximum message length.");
/*      */                   }
/*      */ 
/*      */ 
/*      */                   
/* 1447 */                   state = 1;
/*      */                 }
/*      */               
/* 1450 */               } else if (expectedMsgLength != -1) {
/* 1451 */                 if (Trace.isOn) {
/* 1452 */                   Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "The specified bufferLength is greater than the channel's MAXMSGL : " + expectedMsgLength + " > " + pDataLength.x);
/*      */                 }
/*      */ 
/*      */                 
/* 1456 */                 state = 0;
/*      */               
/*      */               }
/*      */               else {
/*      */                 
/* 1461 */                 if (Trace.isOn) {
/* 1462 */                   Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "The application did not specify a maximum message length");
/*      */                 }
/*      */ 
/*      */                 
/* 1466 */                 state = 1;
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/* 1471 */               if (state == 1) {
/* 1472 */                 maxMsgLength = pDataLength.x;
/* 1473 */                 if (Trace.isOn) {
/* 1474 */                   Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Resetting maxMsgLength to " + maxMsgLength + ". Going round the loop again");
/*      */                 }
/*      */               } 
/*      */               continue;
/*      */ 
/*      */             
/*      */             case 2080:
/* 1481 */               if (Trace.isOn) {
/* 1482 */                 Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "MQRC_TRUNCATED_MSG_FAILED received for message ID ", 
/*      */                     
/* 1484 */                     arrayToHexString(md.getMsgId()));
/*      */               }
/*      */ 
/*      */ 
/*      */               
/* 1489 */               pMsgTooSmallForBufferCount.x = 0;
/* 1490 */               if (pDataLength.x <= maxMsgLength) {
/* 1491 */                 int options = gmo.getOptions();
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1496 */                 if ((options & 0x4000) != 0) {
/* 1497 */                   bufferLength = Math.min(pDataLength.x * 2, maxMsgLength);
/*      */                 
/*      */                 }
/* 1500 */                 else if (bufferLength < pDataLength.x) {
/* 1501 */                   bufferLength = Math.min(pDataLength.x, maxMsgLength);
/*      */                 } else {
/*      */                   
/* 1504 */                   bufferLength = Math.min(bufferLength * 2, maxMsgLength);
/*      */                 } 
/*      */                 
/* 1507 */                 buffer = setBufferSize(pByteBuffer, bufferLength);
/*      */ 
/*      */                 
/* 1510 */                 md.setEncoding(reqEncoding);
/* 1511 */                 md.setCodedCharSetId(reqCCSID);
/* 1512 */                 md.setMsgId(reqMsgId);
/* 1513 */                 md.setCorrelId(reqCorrelId);
/*      */ 
/*      */ 
/*      */                 
/* 1517 */                 if (cmdLevel >= 701) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 1524 */                   if ((options & 0x8000) != 0 || (options & 0x20000) != 0)
/*      */                   {
/* 1526 */                     if (Trace.isOn) {
/* 1527 */                       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "MQGMO_LOGICAL_ORDER and/or MQGMO_ALL_MSGS_AVAILABLE is set", null);
/*      */                     }
/*      */                   }
/*      */                   else
/*      */                   {
/* 1532 */                     options &= 0xFFFFFFFE;
/* 1533 */                     options &= 0xFFFFFFF7;
/*      */                     
/* 1535 */                     gmo.setOptions(options);
/* 1536 */                     gmo.setMatchOptions(32);
/*      */                     
/* 1538 */                     if (Trace.isOn) {
/* 1539 */                       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "Retrying MQGET for message Token ", 
/*      */                           
/* 1541 */                           arrayToHexString(gmo.getMsgToken()));
/*      */                     
/*      */                     }
/*      */                   }
/*      */                 
/*      */                 }
/* 1547 */                 else if (Trace.isOn) {
/* 1548 */                   Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "Retrying MQGET for message ID ", 
/*      */                       
/* 1550 */                       arrayToHexString(md.getMsgId()));
/*      */                 } 
/*      */                 
/* 1553 */                 state = 1;
/* 1554 */                 if (Trace.isOn) {
/* 1555 */                   Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Resetting wait interval to zero");
/*      */                 }
/*      */ 
/*      */ 
/*      */                 
/* 1560 */                 gmo.setOptions(gmo.getOptions() & 0xFFFFFFFE);
/* 1561 */                 gmoOptionsChanged = true;
/* 1562 */                 retryingMQGet = true;
/* 1563 */                 if (Trace.isOn) {
/* 1564 */                   Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", 
/* 1565 */                       arrayToHexString(md.getMsgId()));
/*      */                 }
/*      */                 continue;
/*      */               } 
/* 1569 */               state = 0;
/*      */               continue;
/*      */ 
/*      */             
/*      */             case 2120:
/*      */             case 2190:
/* 1575 */               pMsgTooSmallForBufferCount.x = 0;
/* 1576 */               if (pDataLength.x < maxMsgLength) {
/* 1577 */                 bufferLength = Math.min(pDataLength.x * 2, maxMsgLength);
/* 1578 */                 state = 2;
/*      */                 continue;
/*      */               } 
/* 1581 */               state = 0;
/*      */               continue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             case 2206:
/* 1597 */               if (Trace.isOn) {
/* 1598 */                 Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Queue does not support get by message identifier");
/*      */               }
/*      */ 
/*      */               
/* 1602 */               if (retryingMQGet) {
/* 1603 */                 state = setupToRetryMQGET(md, gmo, startTime, originalWaitInterval, originalReqMsgId, reqCorrelId, reqEncoding, reqCCSID);
/*      */                 
/* 1605 */                 retryingMQGet = false;
/* 1606 */                 waitIntervalChanged = true;
/*      */ 
/*      */                 
/*      */                 continue;
/*      */               } 
/*      */               
/* 1612 */               state = 0;
/*      */               continue;
/*      */ 
/*      */ 
/*      */             
/*      */             case 2033:
/* 1618 */               if (retryingMQGet) {
/* 1619 */                 if (Trace.isOn) {
/* 1620 */                   Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Message has dissapeared from queue!");
/*      */ 
/*      */                   
/* 1623 */                   Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Resetting message ID in the MQMD to " + 
/*      */                       
/* 1625 */                       arrayToHexString(originalReqMsgId));
/*      */                 } 
/*      */                 
/* 1628 */                 gmo.setOptions(appOptions);
/* 1629 */                 gmo.setMatchOptions(originalMatchOptions);
/* 1630 */                 state = setupToRetryMQGET(md, gmo, startTime, originalWaitInterval, originalReqMsgId, reqCorrelId, reqEncoding, reqCCSID);
/*      */                 
/* 1632 */                 retryingMQGet = false;
/* 1633 */                 waitIntervalChanged = true;
/*      */                 
/*      */                 continue;
/*      */               } 
/*      */               
/* 1638 */               state = 0;
/*      */               continue;
/*      */ 
/*      */ 
/*      */             
/*      */             case 2331:
/* 1644 */               if (retryingMQGet) {
/* 1645 */                 if (Trace.isOn) {
/* 1646 */                   Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "MQRC_MSG_TOKEN_ERROR so retrying get");
/*      */                 }
/*      */ 
/*      */ 
/*      */                 
/* 1651 */                 gmo.setOptions(appOptions);
/* 1652 */                 gmo.setMatchOptions(originalMatchOptions);
/* 1653 */                 state = setupToRetryMQGET(md, gmo, startTime, originalWaitInterval, originalReqMsgId, reqCorrelId, reqEncoding, reqCCSID);
/*      */                 
/* 1655 */                 retryingMQGet = false;
/* 1656 */                 waitIntervalChanged = true;
/*      */                 
/*      */                 continue;
/*      */               } 
/* 1660 */               state = 0;
/*      */               continue;
/*      */           } 
/*      */           
/* 1664 */           state = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/* 1673 */           oldBufferLength = pDataLength.x;
/* 1674 */           buffer = growBuffer(pByteBuffer, oldBufferLength, bufferLength);
/* 1675 */           availableLength = pDataLength.x;
/*      */           
/* 1677 */           finished = mq.jmqiConvertMessage(hconn, hobj, reqEncoding, reqCCSID, appOptions, callExitOnLenErr, md, buffer, pDataLength, availableLength, bufferLength, pCompCode, pReason, returnedLength);
/*      */           
/* 1679 */           if (finished) {
/* 1680 */             state = 0;
/*      */             continue;
/*      */           } 
/* 1683 */           switch (pReason.x) {
/*      */             case 0:
/* 1685 */               state = 0;
/*      */               continue;
/*      */             
/*      */             case 2120:
/*      */             case 2190:
/* 1690 */               if (Trace.isOn) {
/* 1691 */                 Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Unable to convert message due to reason " + pReason.x);
/*      */ 
/*      */                 
/* 1694 */                 Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "bufferLength:" + bufferLength + ", pDataLength:" + pDataLength.x + ", maxMsgLength:" + maxMsgLength);
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/* 1699 */               state = 0;
/* 1700 */               if (pDataLength.x < maxMsgLength) {
/*      */ 
/*      */                 
/* 1703 */                 if (bufferLength != maxMsgLength) {
/*      */ 
/*      */                   
/* 1706 */                   bufferLength = Math.min(bufferLength * 2, maxMsgLength);
/* 1707 */                   if (Trace.isOn) {
/* 1708 */                     Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "bufferLength is now " + bufferLength + ". Trying to convert the message again");
/*      */                   }
/*      */ 
/*      */                   
/* 1712 */                   state = 2;
/*      */                 } 
/*      */                 
/* 1715 */                 if (state == 0)
/*      */                 {
/* 1717 */                   if (Trace.isOn) {
/* 1718 */                     Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "It is not possible to grow the buffer any more. Returing the unconverted message");
/*      */                   }
/*      */                 }
/*      */               } 
/*      */               continue;
/*      */           } 
/*      */ 
/*      */           
/* 1726 */           state = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 1732 */     if (waitIntervalChanged) {
/* 1733 */       gmo.setWaitInterval(originalWaitInterval.intValue());
/*      */     }
/* 1735 */     if (gmoOptionsChanged) {
/* 1736 */       gmo.setOptions(originalGmoOptions);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1741 */     if (pCompCode.x == 0 || pCompCode.x == 1) {
/*      */ 
/*      */       
/* 1744 */       if (bufferLength > pDataLength.x * 2 && bufferLength > defaultMaxMsgSize) {
/* 1745 */         pMsgTooSmallForBufferCount.x++;
/* 1746 */         if (bufferLength > pByteBuffer.getHighWaterMark()) {
/* 1747 */           pByteBuffer.setHighWaterMark(bufferLength);
/*      */         
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 1753 */         pMsgTooSmallForBufferCount.x = 0;
/* 1754 */         pByteBuffer.setHighWaterMark(0);
/*      */       }
/*      */     
/*      */     }
/* 1758 */     else if (pCompCode.x == 2 && pReason.x == 2033 && bufferLength > defaultMaxMsgSize) {
/* 1759 */       pMsgTooSmallForBufferCount.x++;
/*      */     } 
/*      */     
/* 1762 */     if (pMsgTooSmallForBufferCount.x > threshold) {
/* 1763 */       pByteBuffer.setBuffer(null);
/* 1764 */       pMsgTooSmallForBufferCount.x = 0;
/*      */     } 
/*      */     
/* 1767 */     int limit = Math.min(buffer.capacity(), Math.max(pDataLength.x, 0));
/* 1768 */     buffer.limit(limit);
/* 1769 */     buffer.position(0);
/*      */     
/* 1771 */     if (Trace.isOn) {
/* 1772 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "__________");
/*      */       
/* 1774 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "jmqiGet <<");
/*      */       
/* 1776 */       Trace.data(env, "com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Hconn", hconn);
/*      */       
/* 1778 */       Trace.data(env, "com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Hobj", hobj);
/*      */       
/* 1780 */       Trace.data(env, "com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "MQMD", md);
/*      */       
/* 1782 */       Trace.data(env, "com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "MQGMO", gmo);
/*      */       
/* 1784 */       Trace.data(env, "com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "expectedMsgLength", 
/* 1785 */           Integer.toString(expectedMsgLength));
/* 1786 */       Trace.data(env, "com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "maxMsgLength", 
/* 1787 */           Integer.toString(maxMsgLength));
/* 1788 */       Trace.data(env, "com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "pMsgTooSmallForBufferCount", pMsgTooSmallForBufferCount);
/*      */       
/* 1790 */       traceApiBufferFromStaticMethod("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", buffer, pDataLength.x);
/*      */ 
/*      */       
/* 1793 */       Trace.data(env, "com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Datalength", pDataLength);
/*      */       
/* 1795 */       Trace.data(env, "com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "CompCode", pCompCode);
/*      */       
/* 1797 */       Trace.data(env, "com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "Reason", pReason);
/*      */       
/* 1799 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", "__________");
/*      */     } 
/*      */ 
/*      */     
/* 1803 */     if (Trace.isOn) {
/* 1804 */       Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "getMessage(JmqiEnvironment,JmqiImplementation,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", buffer);
/*      */     }
/*      */ 
/*      */     
/* 1808 */     return buffer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int setupToRetryMQGET(MQMD md, MQGMO gmo, long startTime, Integer originalWaitInterval, byte[] originalReqMsgId, byte[] reqCorrelId, int reqEncoding, int reqCCSID) {
/*      */     int state;
/* 1821 */     if (Trace.isOn) {
/* 1822 */       Trace.entry("com.ibm.mq.jmqi.internal.JmqiTools", "setupToRetryMQGET(MQMD,MQGMO,long,Integer,byte [ ],byte [ ],int,int)", new Object[] { md, gmo, 
/*      */             
/* 1824 */             Long.valueOf(startTime), originalWaitInterval, originalReqMsgId, reqCorrelId, 
/* 1825 */             Integer.valueOf(reqEncoding), Integer.valueOf(reqCCSID) });
/*      */     }
/*      */ 
/*      */     
/* 1829 */     if (Trace.isOn) {
/* 1830 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "setupToRetryMQGET(MQMD,MQGMO,long,Integer,byte[],byte[],int,int)", "Resetting message ID in the MQMD to " + 
/*      */           
/* 1832 */           arrayToHexString(originalReqMsgId));
/*      */     }
/*      */ 
/*      */     
/* 1836 */     md.setMsgId(originalReqMsgId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1842 */     md.setCorrelId(reqCorrelId);
/* 1843 */     md.setEncoding(reqEncoding);
/* 1844 */     md.setCodedCharSetId(reqCCSID);
/*      */     
/* 1846 */     if (Trace.isOn) {
/* 1847 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "setupToRetryMQGET(MQMD,MQGMO,long,Integer,byte[],byte[],int,int)", "Message ID reset");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1853 */     if (originalWaitInterval.intValue() == -1) {
/*      */       
/* 1855 */       if (Trace.isOn) {
/* 1856 */         Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "setupToRetryMQGET(MQMD,MQGMO,long,Integer,byte[],byte[],int,int)", "Resetting wait interval to MQWI_UNLIMITED");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1861 */       gmo.setWaitInterval(-1);
/* 1862 */       state = 1;
/*      */     }
/*      */     else {
/*      */       
/* 1866 */       long currentTime = System.currentTimeMillis();
/* 1867 */       long elapsedTime = currentTime - startTime;
/* 1868 */       long remainingWaitTime = originalWaitInterval.longValue() - elapsedTime;
/*      */       
/* 1870 */       if (Trace.isOn) {
/* 1871 */         Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "setupToRetryMQGET(MQMD,MQGMO,long,Integer,byte[],byte[],int,int)", 
/*      */             
/* 1873 */             String.format("start %d current %d elapsed %d remaining %d", new Object[] { Long.valueOf(startTime), Long.valueOf(currentTime), Long.valueOf(elapsedTime), Long.valueOf(remainingWaitTime) }));
/*      */       }
/*      */       
/* 1876 */       if (remainingWaitTime > 0L) {
/*      */ 
/*      */         
/* 1879 */         Long remainingWaitTimeAsLong = Long.valueOf(remainingWaitTime);
/*      */         
/* 1881 */         if (Trace.isOn) {
/* 1882 */           Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "setupToRetryMQGET(MQMD,MQGMO,long,Integer,byte[],byte[],int,int)", "Resetting wait interval to " + remainingWaitTimeAsLong
/*      */               
/* 1884 */               .intValue());
/*      */         }
/*      */         
/* 1887 */         gmo.setWaitInterval(remainingWaitTimeAsLong.intValue());
/* 1888 */         state = 1;
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */         
/* 1897 */         if (Trace.isOn) {
/* 1898 */           Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "setupToRetryMQGET(MQMD,MQGMO,long,Integer,byte[],byte[],int,int)", "wait time expired");
/*      */ 
/*      */           
/* 1901 */           Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "setupToRetryMQGET(MQMD,MQGMO,long,Integer,byte[],byte[],int,int)", "trying one more MQGET with a zero wait time, in case the message is still there");
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1907 */         gmo.setWaitInterval(0);
/* 1908 */         state = 1;
/*      */       } 
/*      */     } 
/* 1911 */     if (Trace.isOn) {
/* 1912 */       Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "setupToRetryMQGET(MQMD,MQGMO,long,Integer,byte [ ],byte [ ],int,int)", 
/* 1913 */           Integer.valueOf(state));
/*      */     }
/* 1915 */     return state;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static ByteBuffer growBuffer(PbyteBuffer pByteBuffer, int oldBufferLength, int newBufferLength) {
/* 1927 */     byte[] oldArray = pByteBuffer.getBuffer().array();
/* 1928 */     byte[] newArray = new byte[newBufferLength];
/* 1929 */     System.arraycopy(oldArray, 0, newArray, 0, oldBufferLength);
/* 1930 */     pByteBuffer.setBuffer(ByteBuffer.wrap(newArray));
/* 1931 */     ByteBuffer traceRet1 = pByteBuffer.getBuffer();
/* 1932 */     return traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   private static ByteBuffer setBufferSize(PbyteBuffer pByteBuffer, int size) {
/* 1937 */     pByteBuffer.setBuffer(ByteBuffer.wrap(new byte[size]));
/* 1938 */     pByteBuffer.setHighWaterMark(0);
/* 1939 */     ByteBuffer traceRet1 = pByteBuffer.getBuffer();
/* 1940 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ByteBuffer mergeByteBuffers(ByteBuffer[] buffers) {
/* 1948 */     if (Trace.isOn) {
/* 1949 */       Trace.entry("com.ibm.mq.jmqi.internal.JmqiTools", "mergeByteBuffers(ByteBuffer [ ])", new Object[] { buffers });
/*      */     }
/*      */     
/* 1952 */     int totSize = 0;
/* 1953 */     for (ByteBuffer buffer : buffers) {
/* 1954 */       totSize += buffer.limit();
/*      */     }
/* 1956 */     ByteBuffer result = ByteBuffer.allocate(totSize);
/* 1957 */     for (ByteBuffer buffer : buffers) {
/* 1958 */       int oldPos = buffer.position();
/* 1959 */       result.put(buffer);
/* 1960 */       buffer.position(oldPos);
/*      */     } 
/* 1962 */     result.position(0);
/* 1963 */     result.limit(totSize);
/* 1964 */     if (Trace.isOn) {
/* 1965 */       Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "mergeByteBuffers(ByteBuffer [ ])", result);
/*      */     }
/* 1967 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getUsername() {
/* 1978 */     return getSystemProperty("user.name");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getUserHome() {
/*      */     try {
/* 1990 */       return getSystemProperty("user.home");
/*      */     }
/* 1992 */     catch (AccessControlException ace) {
/*      */       
/* 1994 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getNewline() {
/* 2008 */     if (NL != null) {
/* 2009 */       return NL;
/*      */     }
/* 2011 */     String nlString = "\n";
/*      */     try {
/* 2013 */       nlString = getSystemProperty("line.separator");
/*      */     }
/* 2015 */     catch (AccessControlException accessControlException) {}
/*      */ 
/*      */     
/* 2018 */     return nlString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class ValueReference
/*      */   {
/*      */     public String value;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ValueReference(String value) {
/* 2035 */       this.value = value;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getSystemProperty(final String name) {
/* 2052 */     ValueReference found = environmentCache.get(name);
/*      */ 
/*      */     
/* 2055 */     if (found == null) {
/* 2056 */       Object o = AccessController.doPrivileged(new PrivilegedAction()
/*      */           {
/*      */             public Object run()
/*      */             {
/* 2060 */               Object object = null;
/*      */               try {
/* 2062 */                 object = System.getProperty(name);
/*      */               }
/* 2064 */               catch (AccessControlException ace) {
/*      */ 
/*      */ 
/*      */                 
/* 2068 */                 object = ace;
/*      */               } 
/* 2070 */               return object;
/*      */             }
/*      */           });
/* 2073 */       if (o instanceof AccessControlException) {
/* 2074 */         throw (AccessControlException)o;
/*      */       }
/*      */       
/* 2077 */       found = new ValueReference((String)o);
/* 2078 */       environmentCache.put(name, found);
/*      */     } 
/* 2080 */     return found.value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getEnvironmentProperty(final String name) {
/* 2096 */     ValueReference found = environmentCache.get(name);
/*      */ 
/*      */     
/* 2099 */     if (found == null) {
/* 2100 */       Object o = AccessController.doPrivileged(new PrivilegedAction()
/*      */           {
/*      */             public Object run()
/*      */             {
/* 2104 */               Object object = null;
/*      */               try {
/* 2106 */                 object = System.getenv(name);
/*      */               }
/* 2108 */               catch (Throwable throwable) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2114 */               return object;
/*      */             }
/*      */           });
/*      */       
/* 2118 */       found = new ValueReference((String)o);
/* 2119 */       environmentCache.put(name, found);
/*      */     } 
/* 2121 */     return found.value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getRegistryProperty(final String name) {
/* 2137 */     ValueReference found = registryCache.get(name);
/*      */ 
/*      */     
/* 2140 */     if (found == null) {
/* 2141 */       String s = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*      */           {
/*      */             public String run()
/*      */             {
/* 2145 */               return JmqiTools.getRegistryPropertyInternal(name);
/*      */             }
/*      */           });
/*      */       
/* 2149 */       found = new ValueReference(s);
/* 2150 */       registryCache.put(name, found);
/*      */     } 
/* 2152 */     return found.value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getRegistryPropertyInternal(String name) {
/* 2162 */     if (Trace.isOn) {
/* 2163 */       Trace.entry("com.ibm.mq.jmqi.internal.JmqiTools", "getRegistryPropertyInternal(final String)", new Object[] { name });
/*      */     }
/*      */     
/*      */     try {
/* 2167 */       String subTree = null;
/* 2168 */       String key = name;
/*      */       
/* 2170 */       int index = name.lastIndexOf("\\");
/* 2171 */       if (index >= 0) {
/* 2172 */         subTree = name.substring(0, index);
/* 2173 */         key = name.substring(index + 1, name.length());
/*      */       } 
/*      */       
/* 2176 */       StringBuffer cmd = new StringBuffer();
/* 2177 */       cmd.append("reg query \"");
/* 2178 */       cmd.append("HKLM\\SOFTWARE\\IBM\\WebSphere MQ");
/* 2179 */       if (subTree != null) {
/* 2180 */         cmd.append("\\");
/* 2181 */         cmd.append(subTree);
/*      */       } 
/* 2183 */       cmd.append("\" /v ");
/* 2184 */       cmd.append(key);
/*      */       
/* 2186 */       Process process = Runtime.getRuntime().exec(cmd.toString());
/* 2187 */       StreamReader reader = new StreamReader(process.getInputStream());
/*      */       
/* 2189 */       reader.start();
/* 2190 */       process.waitFor();
/* 2191 */       reader.join();
/*      */       
/* 2193 */       String result = reader.getResult();
/* 2194 */       int p = result.indexOf("REG_SZ");
/*      */       
/* 2196 */       if (p == -1) {
/* 2197 */         if (Trace.isOn) {
/* 2198 */           Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "getRegistryPropertyInternal(final String)", null, 1);
/*      */         }
/*      */         
/* 2201 */         return null;
/*      */       } 
/*      */       
/* 2204 */       String traceRet1 = result.substring(p + "REG_SZ".length()).trim();
/* 2205 */       if (Trace.isOn) {
/* 2206 */         Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "getRegistryPropertyInternal(final String)", traceRet1, 2);
/*      */       }
/*      */       
/* 2209 */       return traceRet1;
/*      */     }
/* 2211 */     catch (RuntimeException rte) {
/* 2212 */       if (Trace.isOn) {
/* 2213 */         Trace.catchBlock("com.ibm.mq.jmqi.internal.JmqiTools", "getRegistryPropertyInternal(final String)", rte, 1);
/*      */       }
/*      */       
/* 2216 */       if (Trace.isOn) {
/* 2217 */         Trace.throwing("com.ibm.mq.jmqi.internal.JmqiTools", "getRegistryPropertyInternal(final String)", rte);
/*      */       }
/*      */       
/* 2220 */       throw rte;
/*      */     }
/* 2222 */     catch (Exception e) {
/* 2223 */       if (Trace.isOn) {
/* 2224 */         Trace.catchBlock("com.ibm.mq.jmqi.internal.JmqiTools", "getRegistryPropertyInternal(final String)", e, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2231 */       if (Trace.isOn) {
/* 2232 */         Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "getRegistryPropertyInternal(final String)", null, 3);
/*      */       }
/*      */       
/* 2235 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   static class StreamReader
/*      */     extends Thread
/*      */   {
/*      */     private InputStream is;
/*      */     private StringWriter sw;
/*      */     
/*      */     StreamReader(InputStream is) {
/* 2246 */       this.is = is;
/* 2247 */       this.sw = new StringWriter();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/*      */       try {
/*      */         int c;
/* 2258 */         while ((c = this.is.read()) != -1) {
/* 2259 */           this.sw.write(c);
/*      */         }
/*      */       }
/* 2262 */       catch (IOException e) {
/*      */ 
/*      */         
/* 2265 */         e.printStackTrace();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     String getResult() {
/* 2271 */       return this.sw.toString();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static FileInputStream newFileInputStream(final String name) {
/* 2282 */     FileInputStream fileInputStream = AccessController.<FileInputStream>doPrivileged(new PrivilegedAction<FileInputStream>()
/*      */         {
/*      */           
/*      */           public FileInputStream run()
/*      */           {
/* 2287 */             FileInputStream stream = null;
/*      */             try {
/* 2289 */               stream = new FileInputStream(name);
/*      */             }
/* 2291 */             catch (FileNotFoundException fileNotFoundException) {}
/*      */ 
/*      */ 
/*      */             
/* 2295 */             return stream;
/*      */           }
/*      */         });
/* 2298 */     return fileInputStream;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getXAErrorCode(boolean isPrepared, Throwable t) {
/* 2313 */     int retXACode = -3;
/*      */     
/* 2315 */     if (t instanceof JmqiException) {
/* 2316 */       if (isPrepared) {
/* 2317 */         JmqiException je = (JmqiException)t;
/* 2318 */         int iReasonCode = je.getReason();
/* 2319 */         switch (iReasonCode) {
/*      */           case 2009:
/*      */           case 2018:
/*      */           case 2019:
/*      */           case 2059:
/*      */           case 2161:
/*      */           case 2162:
/*      */           case 2202:
/*      */           case 2203:
/*      */           case 2223:
/*      */           case 2273:
/*      */           case 2278:
/*      */           case 2279:
/*      */           case 2537:
/*      */           case 2538:
/* 2334 */             retXACode = -7;
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       } 
/*      */     } else {
/* 2343 */       retXACode = -7;
/*      */     } 
/* 2345 */     return retXACode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<?, ?> getVersionProperties() {
/* 2354 */     Map<?, ?> map = null;
/*      */ 
/*      */     
/* 2357 */     Object o = AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/* 2361 */             Object object = null;
/*      */             try {
/* 2363 */               String filename = "META-INF/jmqiversion.properties";
/*      */ 
/*      */               
/* 2366 */               URL resource = ClassLoader.getSystemResource(filename);
/*      */ 
/*      */               
/* 2369 */               if (resource == null) {
/* 2370 */                 ClassLoader thisClassesLoader = getClass().getClassLoader();
/* 2371 */                 resource = thisClassesLoader.getResource(filename);
/*      */               } 
/*      */ 
/*      */               
/* 2375 */               if (resource == null) {
/* 2376 */                 ClassLoader threadContextClassloader = Thread.currentThread().getContextClassLoader();
/* 2377 */                 resource = threadContextClassloader.getResource(filename);
/*      */               } 
/*      */ 
/*      */               
/* 2381 */               if (resource != null) {
/* 2382 */                 InputStream versionPropertiesStream = resource.openStream();
/* 2383 */                 Properties properties = new Properties();
/* 2384 */                 properties.load(versionPropertiesStream);
/* 2385 */                 object = properties;
/*      */                 
/*      */                 try {
/* 2388 */                   versionPropertiesStream.close();
/*      */                 }
/* 2390 */                 catch (IOException ioe) {
/* 2391 */                   if (Trace.isOn) {
/* 2392 */                     Trace.catchBlock("com.ibm.mq.jmqi.internal.JmqiTools", "getVersionProperties()", ioe, 1);
/*      */                   }
/*      */                 }
/*      */               
/*      */               } 
/* 2397 */             } catch (AccessControlException e) {
/*      */ 
/*      */ 
/*      */               
/* 2401 */               object = e;
/*      */             }
/* 2403 */             catch (IOException e) {
/* 2404 */               object = e;
/*      */             } 
/* 2406 */             return object;
/*      */           }
/*      */         });
/*      */     
/* 2410 */     if (o instanceof AccessControlException) {
/* 2411 */       throw (AccessControlException)o;
/*      */     }
/* 2413 */     if (o instanceof IOException) {
/* 2414 */       IOException ioe = (IOException)o;
/* 2415 */       throw new RuntimeException(ioe);
/*      */     } 
/* 2417 */     if (o instanceof Map) {
/* 2418 */       map = (Map<?, ?>)o;
/*      */     }
/*      */     
/* 2421 */     return map;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toString(byte[] byteArray) {
/* 2430 */     StringBuffer sb = new StringBuffer();
/* 2431 */     sb.append(NL);
/* 2432 */     hexDump(byteArray, null, 0, byteArray.length, sb);
/* 2433 */     String traceRet1 = sb.toString();
/* 2434 */     return traceRet1;
/*      */   }
/*      */   
/*      */   protected static String safeGet(String propertyName) {
/* 2438 */     if (Trace.isOn) {
/* 2439 */       Trace.entry("com.ibm.mq.jmqi.internal.JmqiTools", "safeGet(String)", new Object[] { propertyName });
/*      */     }
/*      */     
/*      */     try {
/* 2443 */       String traceRet1 = getSystemProperty("propertyName");
/* 2444 */       if (Trace.isOn) {
/* 2445 */         Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "safeGet(String)", traceRet1, 1);
/*      */       }
/* 2447 */       return traceRet1;
/*      */     }
/* 2449 */     catch (AccessControlException ace) {
/* 2450 */       if (Trace.isOn) {
/* 2451 */         Trace.catchBlock("com.ibm.mq.jmqi.internal.JmqiTools", "safeGet(String)", ace);
/*      */       }
/* 2453 */       String traceRet2 = "Insufficient permissions to read property '" + propertyName + "'";
/* 2454 */       if (Trace.isOn) {
/* 2455 */         Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "safeGet(String)", traceRet2, 2);
/*      */       }
/* 2457 */       return traceRet2;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ClassLoader getThreadContextClassLoader() {
/* 2472 */     Object o = AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/* 2476 */             Object object = null;
/*      */             try {
/* 2478 */               object = Thread.currentThread().getContextClassLoader();
/*      */             }
/* 2480 */             catch (SecurityException e) {
/*      */ 
/*      */ 
/*      */               
/* 2484 */               object = e;
/*      */             } 
/* 2486 */             return object;
/*      */           }
/*      */         });
/* 2489 */     if (o instanceof SecurityException) {
/* 2490 */       throw (SecurityException)o;
/*      */     }
/*      */     
/* 2493 */     return (ClassLoader)o;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setThreadContextClassLoader(final ClassLoader classloader) {
/* 2506 */     Object o = AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/* 2510 */             Object object = null;
/*      */             try {
/* 2512 */               Thread.currentThread().setContextClassLoader(classloader);
/*      */             }
/* 2514 */             catch (SecurityException e) {
/*      */ 
/*      */ 
/*      */               
/* 2518 */               object = e;
/*      */             } 
/* 2520 */             return object;
/*      */           }
/*      */         });
/* 2523 */     if (o instanceof SecurityException) {
/* 2524 */       throw (SecurityException)o;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<byte[]> jmqiInquireNamedSubscribers(JmqiEnvironment env, JmqiMQ mq, JmqiTls jTls, Hconn hconn, LpiCALLOPT callOptions, String subName, Pint pCompCode, Pint pReason) {
/* 2541 */     if (Trace.isOn) {
/* 2542 */       Trace.entry("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", new Object[] { env, mq, jTls, hconn, callOptions, subName, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2547 */     if (Trace.isOn) {
/* 2548 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", "__________");
/*      */       
/* 2550 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", "jmqiInquireNamedSubscribers >>");
/*      */       
/* 2552 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", "Hconn", hconn);
/*      */       
/* 2554 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", "subName", subName);
/*      */       
/* 2556 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", "CompCode", pCompCode);
/*      */       
/* 2558 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", "Reason", pReason);
/*      */       
/* 2560 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", "__________");
/*      */     } 
/*      */     
/* 2563 */     JmqiSP mqsp = (JmqiSP)mq;
/* 2564 */     int ptrSize = hconn.getPointerSize();
/* 2565 */     List<byte[]> subIds = null; try {
/*      */       byte[] callOptionsBuffer; int callOptionsBufferLength; byte[] subNameBuffer; int subNameBufferLength;
/* 2567 */       boolean swap = hconn.isByteSwap();
/* 2568 */       JmqiCodepage cp = hconn.getCodePage();
/*      */ 
/*      */ 
/*      */       
/* 2572 */       if (callOptions == null) {
/* 2573 */         callOptionsBuffer = null;
/* 2574 */         callOptionsBufferLength = 0;
/*      */       } else {
/*      */         
/* 2577 */         callOptionsBufferLength = callOptions.getRequiredBufferSize(ptrSize, cp);
/* 2578 */         callOptionsBuffer = new byte[callOptionsBufferLength];
/* 2579 */         callOptions.writeToBuffer(callOptionsBuffer, 0, ptrSize, swap, cp, jTls);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2584 */       if (subName == null) {
/* 2585 */         subNameBuffer = null;
/* 2586 */         subNameBufferLength = 0;
/*      */       } else {
/*      */         
/* 2589 */         int length = Math.min(subName.length() * 4, 10240);
/* 2590 */         subNameBuffer = new byte[length + 1];
/* 2591 */         byte[] subName_noterm = cp.stringToBytes(subName);
/* 2592 */         subNameBufferLength = Math.min(10240, subName_noterm.length);
/* 2593 */         System.arraycopy(subName_noterm, 0, subNameBuffer, 0, subNameBufferLength);
/* 2594 */         subNameBuffer[subNameBufferLength] = 0;
/*      */       } 
/* 2596 */       Pint subscribersReturned = env.newPint();
/*      */ 
/*      */       
/* 2599 */       Pint subIdBufferLength = env.newPint(24000);
/* 2600 */       byte[] subIdBuffer = null; int i;
/* 2601 */       for (i = 0; i < 10; ) {
/* 2602 */         subIdBuffer = new byte[subIdBufferLength.x];
/* 2603 */         mqsp.lpiSPIInquireNamedSubscribers(hconn, callOptionsBuffer, callOptionsBufferLength, subNameBuffer, subNameBufferLength, subIdBuffer, subIdBufferLength, subscribersReturned, pCompCode, pReason);
/*      */         
/* 2605 */         if (pReason.x == 6113) {
/*      */ 
/*      */           
/* 2608 */           subIdBufferLength.x *= 2;
/*      */ 
/*      */           
/*      */           i++;
/*      */         } 
/*      */       } 
/*      */       
/* 2615 */       if (pReason.x == 0) {
/*      */         
/* 2617 */         if (callOptions != null) {
/* 2618 */           callOptions.readFromBuffer(callOptionsBuffer, 0, ptrSize, swap, cp, jTls);
/*      */         }
/*      */         
/* 2621 */         subIds = (List)new ArrayList<>();
/* 2622 */         for (i = 0; i < subscribersReturned.x; i++) {
/* 2623 */           byte[] subId = new byte[24];
/* 2624 */           System.arraycopy(subIdBuffer, i * 24, subId, 0, 24);
/* 2625 */           subIds.add(subId);
/*      */         }
/*      */       
/*      */       } 
/* 2629 */     } catch (JmqiException e) {
/* 2630 */       if (Trace.isOn) {
/* 2631 */         Trace.catchBlock("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", (Throwable)e, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2636 */       pCompCode.x = e.getCompCode();
/* 2637 */       pReason.x = e.getReason();
/*      */     }
/* 2639 */     catch (Throwable e) {
/* 2640 */       if (Trace.isOn) {
/* 2641 */         Trace.catchBlock("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", e, 2);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2648 */     if (Trace.isOn) {
/* 2649 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", "__________");
/*      */       
/* 2651 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", "jmqiInquireNamedSubscribers <<");
/*      */       
/* 2653 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", "Hconn", hconn);
/*      */       
/* 2655 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", "subName", subName);
/*      */       
/* 2657 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", "CompCode", pCompCode);
/*      */       
/* 2659 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", "Reason", pReason);
/*      */       
/* 2661 */       Trace.data("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", "__________");
/*      */     } 
/*      */     
/* 2664 */     if (Trace.isOn) {
/* 2665 */       Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "jmqiInquireNamedSubscribers(JmqiEnvironment,JmqiMQ,JmqiTls,Hconn,LpiCALLOPT,String,Pint,Pint)", subIds);
/*      */     }
/*      */ 
/*      */     
/* 2669 */     return subIds;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String getDefaultAppName() {
/* 2680 */     if (Trace.isOn) {
/* 2681 */       Trace.entry("com.ibm.mq.jmqi.internal.JmqiTools", "getDefaultAppName()");
/*      */     }
/* 2683 */     String commandString = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*      */         {
/*      */           public String run()
/*      */           {
/* 2687 */             if (Trace.isOn) {
/* 2688 */               Trace.entry("com.ibm.mq.jmqi.internal.JmqiTools", "run()");
/*      */             }
/* 2690 */             String traceRet1 = System.getProperty("sun.java.command");
/* 2691 */             if (Trace.isOn) {
/* 2692 */               Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "run()", traceRet1);
/*      */             }
/* 2694 */             return traceRet1;
/*      */           }
/*      */         });
/*      */     
/* 2698 */     Trace.data("getDefaultAppName()", "command string", commandString);
/*      */ 
/*      */ 
/*      */     
/* 2702 */     if (commandString != null && commandString.length() != 0) {
/* 2703 */       String applicationName = commandString.split(" ")[0];
/* 2704 */       String trimmedName = trimToSize(applicationName, 28);
/* 2705 */       if (Trace.isOn) {
/* 2706 */         Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "getDefaultAppName()", trimmedName, 1);
/*      */       }
/*      */       
/* 2709 */       return trimmedName;
/*      */     } 
/* 2711 */     if (Trace.isOn) {
/* 2712 */       Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "getDefaultAppName()", "IBM MQ Client for Java", 2);
/*      */     }
/*      */     
/* 2715 */     return "IBM MQ Client for Java";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getAppName(MQCNO pMQCNO, JmqiConnectOptions pJmqiConnectOpts) {
/* 2730 */     if (Trace.isOn) {
/* 2731 */       Trace.entry("com.ibm.mq.jmqi.internal.JmqiTools", "getAppName(MQCNO,JmqiConnectOptions)", new Object[] { pJmqiConnectOpts });
/*      */     }
/*      */ 
/*      */     
/* 2735 */     if (pJmqiConnectOpts != null) {
/* 2736 */       String specifiedApplicationName = pJmqiConnectOpts.getApplicationName();
/* 2737 */       if (specifiedApplicationName != null) {
/* 2738 */         String str = specifiedApplicationName.trim();
/*      */         
/* 2740 */         if (str.length() != 0) {
/* 2741 */           if (Trace.isOn) {
/* 2742 */             Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "getAppName(MQCNO,JmqiConnectOptions)", str, 1);
/*      */           }
/*      */           
/* 2745 */           return str;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2750 */     String applicationName = getAppName(pMQCNO);
/* 2751 */     if (Trace.isOn) {
/* 2752 */       Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "getAppName(MQCNO,JmqiConnectOptions)", applicationName, 2);
/*      */     }
/*      */     
/* 2755 */     return applicationName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getAppName(MQCNO pMQCNO) {
/* 2767 */     if (Trace.isOn) {
/* 2768 */       Trace.entry("com.ibm.mq.jmqi.internal.JmqiTools", "getAppName(MQCNO)", new Object[] { pMQCNO });
/*      */     }
/*      */ 
/*      */     
/* 2772 */     if (pMQCNO != null) {
/* 2773 */       String specifiedApplicationName = pMQCNO.getApplName();
/* 2774 */       if (specifiedApplicationName != null) {
/* 2775 */         String str = specifiedApplicationName.trim();
/*      */         
/* 2777 */         if (str.length() != 0) {
/* 2778 */           if (Trace.isOn) {
/* 2779 */             Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "getAppName(MQCNO)", str, 1);
/*      */           }
/*      */           
/* 2782 */           return str;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2787 */     String applicationName = getDefaultAppName();
/* 2788 */     if (Trace.isOn) {
/* 2789 */       Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "getAppName(MQCNO)", applicationName, 2);
/*      */     }
/*      */     
/* 2792 */     return applicationName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String trimToSize(String applicationName, int maxLength) {
/* 2805 */     if (Trace.isOn) {
/* 2806 */       Trace.entry("com.ibm.mq.jmqi.internal.JmqiTools", "trimToSize(String,int)", new Object[] { applicationName, 
/* 2807 */             Integer.valueOf(maxLength) });
/*      */     }
/* 2809 */     if (applicationName.length() <= maxLength) {
/* 2810 */       if (Trace.isOn) {
/* 2811 */         Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "trimToSize(String,int)", applicationName, 1);
/*      */       }
/*      */       
/* 2814 */       return applicationName;
/*      */     } 
/*      */ 
/*      */     
/* 2818 */     if (applicationName.toLowerCase().endsWith(".jar")) {
/* 2819 */       String traceRet1 = applicationName.substring(applicationName.length() - maxLength);
/* 2820 */       Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "trimToSize(String,int)", traceRet1, 4);
/* 2821 */       return traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2827 */     String[] classElements = applicationName.split("\\.");
/* 2828 */     int classIndex = classElements.length - 1;
/* 2829 */     int currentLength = classElements[classIndex].length();
/* 2830 */     if (currentLength > maxLength) {
/*      */       
/* 2832 */       String traceRet1 = classElements[classIndex].substring(0, maxLength);
/* 2833 */       if (Trace.isOn) {
/* 2834 */         Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "trimToSize(String,int)", traceRet1, 2);
/*      */       }
/*      */       
/* 2837 */       return traceRet1;
/*      */     } 
/*      */     
/* 2840 */     int currentElement = classIndex - 1;
/*      */     while (true) {
/* 2842 */       currentLength += classElements[currentElement].length() + 1;
/*      */ 
/*      */ 
/*      */       
/* 2846 */       if (currentLength >= maxLength) {
/*      */         break;
/*      */       }
/* 2849 */       if (currentElement == 0) {
/*      */         break;
/*      */       }
/* 2852 */       currentElement--;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2857 */     StringBuilder resultBuilder = new StringBuilder();
/* 2858 */     for (; ++currentElement < classElements.length; currentElement++) {
/* 2859 */       if (resultBuilder.length() > 0) {
/* 2860 */         resultBuilder.append('.');
/*      */       }
/* 2862 */       resultBuilder.append(classElements[currentElement]);
/*      */     } 
/*      */     
/* 2865 */     String traceRet2 = resultBuilder.toString();
/* 2866 */     if (Trace.isOn) {
/* 2867 */       Trace.exit("com.ibm.mq.jmqi.internal.JmqiTools", "trimToSize(String,int)", traceRet2, 3);
/*      */     }
/*      */     
/* 2870 */     return traceRet2;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\JmqiTools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */