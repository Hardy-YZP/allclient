/*      */ package com.ibm.mq.jmqi.internal;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.CharBuffer;
/*      */ import java.nio.charset.CharacterCodingException;
/*      */ import java.nio.charset.CharsetDecoder;
/*      */ import java.nio.charset.CharsetEncoder;
/*      */ import java.nio.charset.CoderResult;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
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
/*      */ public class JmqiDC
/*      */   extends JmqiObject
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/JmqiDC.java";
/*      */   private static final int maxBuffExpansionCoeff = 4;
/*      */   
/*      */   public JmqiDC(JmqiEnvironment env) {
/*   68 */     super(env);
/*   69 */     if (Trace.isOn) {
/*   70 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.JmqiDC", "<init>(JmqiEnvironment)", new Object[] { env });
/*      */     }
/*      */     
/*   73 */     if (Trace.isOn) {
/*   74 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.JmqiDC", "<init>(JmqiEnvironment)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*   80 */   static byte[] nullByteArray = new byte[0];
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
/*      */   public byte[] getStrBytes(String str, JmqiCodepage cp) throws JmqiException {
/*   94 */     if (Trace.isOn) {
/*   95 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.JmqiDC", "getStrBytes(String, JmqiCodepage)", new Object[] { "String not traced", cp });
/*      */     }
/*      */     try {
/*   98 */       if (str == null) {
/*   99 */         if (Trace.isOn) {
/*  100 */           Trace.exit(this, "com.ibm.mq.jmqi.internal.JmqiDC", "getStrBytes(String, JmqiCodepage)", nullByteArray, 1);
/*      */         }
/*  102 */         return nullByteArray;
/*      */       } 
/*  104 */       byte[] convertedString = cp.stringToBytes(str);
/*  105 */       if (Trace.isOn) {
/*  106 */         Trace.exit(this, "com.ibm.mq.jmqi.internal.JmqiDC", "getStrBytes(String, JmqiCodepage)", convertedString, 2);
/*      */       }
/*  108 */       return convertedString;
/*  109 */     } catch (CharacterCodingException e) {
/*      */       
/*  111 */       JmqiException je = new JmqiException(this.env, 6047, new String[] { "UCS2 (Unicode)", cp.toString() }, 2, 2330, e);
/*      */       
/*  113 */       if (Trace.isOn) {
/*  114 */         Trace.throwing(this, "com.ibm.mq.jmqi.internal.JmqiDC", "getStrBytes(String, JmqiCodepage)", (Throwable)je);
/*  115 */         Trace.exit(this, "com.ibm.mq.jmqi.internal.JmqiDC", "getStrBytes(String, JmqiCodepage)", 3);
/*      */       } 
/*  117 */       throw je;
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
/*      */   public void clear(byte[] buffer, int pos, int size) {
/*  130 */     int max = pos + size;
/*  131 */     for (int i = pos; i < max; i++) {
/*  132 */       buffer[i] = 0;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getTemporaryArray(byte[] array, String string) {
/*  143 */     byte[] result = array;
/*  144 */     int requiredLengh = string.length() * 3;
/*      */     
/*  146 */     if (array.length < requiredLengh) {
/*  147 */       result = new byte[requiredLengh];
/*      */     }
/*      */     
/*  150 */     return result;
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
/*      */   public int convUtf16Utf8(CharSequence src, int srcOffset, byte[] targ, int targOffset, int fsize) {
/*  167 */     int traceRet1 = convUtf16Utf8(src, srcOffset, targ, targOffset, fsize, false);
/*      */ 
/*      */     
/*  170 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int convUtf16Utf8(CharSequence src, int srcOffset, byte[] targ, int targOffset, int fsize, boolean srcIsPasswd) {
/*  178 */     if (Trace.isOn) {
/*  179 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.JmqiDC", "convUtf16Utf8(CharSequence,int,byte [ ],int,int,boolean)", new Object[] { srcIsPasswd ? "********" : src, 
/*      */ 
/*      */             
/*  182 */             Integer.valueOf(srcOffset), targ, 
/*      */             
/*  184 */             Integer.valueOf(targOffset), 
/*  185 */             Integer.valueOf(fsize) });
/*      */     }
/*  187 */     int srcPos = srcOffset;
/*  188 */     int targPos = targOffset;
/*      */ 
/*      */     
/*  191 */     int firstByte = targPos;
/*  192 */     int lastByte = targ.length;
/*  193 */     if (fsize >= 0) {
/*  194 */       lastByte = targPos + fsize;
/*      */     }
/*  196 */     int srcLen = src.length();
/*      */     
/*  198 */     while (srcPos < srcLen) {
/*      */       
/*  200 */       char srcChar = src.charAt(srcPos++);
/*  201 */       int srcByte0 = srcChar >> 8 & 0xFF;
/*  202 */       int srcByte1 = srcChar & 0xFF;
/*      */       
/*  204 */       if ((srcByte1 & 0x80) == 0 && srcByte0 == 0) {
/*      */         
/*  206 */         if (targPos >= lastByte) {
/*  207 */           int traceRet1 = 0 - lastByte - firstByte - 1;
/*      */           
/*  209 */           if (Trace.isOn) {
/*  210 */             Trace.exit(this, "com.ibm.mq.jmqi.internal.JmqiDC", "convUtf16Utf8(CharSequence,int,byte [ ],int,int,boolean)", 
/*      */ 
/*      */                 
/*  213 */                 Integer.valueOf(traceRet1), 1);
/*      */           }
/*  215 */           return traceRet1;
/*      */         } 
/*      */ 
/*      */         
/*  219 */         targ[targPos++] = (byte)srcByte1;
/*      */         continue;
/*      */       } 
/*  222 */       if ((srcByte0 & 0xF8) == 0) {
/*      */         
/*  224 */         if (targPos + 2 > lastByte) {
/*  225 */           int traceRet2 = 0 - lastByte - firstByte - 2;
/*  226 */           if (Trace.isOn) {
/*  227 */             Trace.exit(this, "com.ibm.mq.jmqi.internal.JmqiDC", "convUtf16Utf8(CharSequence,int,byte [ ],int,int,boolean)", 
/*      */ 
/*      */                 
/*  230 */                 Integer.valueOf(traceRet2), 2);
/*      */           }
/*  232 */           return traceRet2;
/*      */         } 
/*      */ 
/*      */         
/*  236 */         targ[targPos++] = (byte)(srcByte0 << 2 | srcByte1 >> 6 | 0xC0);
/*  237 */         targ[targPos++] = (byte)(srcByte1 & 0x3F | 0x80);
/*      */ 
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/*  243 */       if (targPos + 3 > lastByte) {
/*  244 */         int traceRet3 = 0 - lastByte - firstByte - 3;
/*  245 */         if (Trace.isOn) {
/*  246 */           Trace.exit(this, "com.ibm.mq.jmqi.internal.JmqiDC", "convUtf16Utf8(CharSequence,int,byte [ ],int,int,boolean)", 
/*      */ 
/*      */               
/*  249 */               Integer.valueOf(traceRet3), 3);
/*      */         }
/*  251 */         return traceRet3;
/*      */       } 
/*      */ 
/*      */       
/*  255 */       targ[targPos++] = (byte)(srcByte0 >> 4 | 0xE0);
/*  256 */       targ[targPos++] = (byte)(srcByte0 << 2 & 0x3C | srcByte1 >> 6 | 0x80);
/*      */       
/*  258 */       targ[targPos++] = (byte)(srcByte1 & 0x3F | 0x80);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  265 */     int byteCnt = targPos - firstByte;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  270 */     if (fsize >= 0) {
/*  271 */       while (targPos < lastByte) {
/*  272 */         targ[targPos++] = 32;
/*      */       }
/*  274 */       byteCnt = fsize;
/*      */     } 
/*  276 */     if (Trace.isOn) {
/*  277 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.JmqiDC", "convUtf16Utf8(CharSequence,int,byte [ ],int,int,boolean)", 
/*      */ 
/*      */           
/*  280 */           Integer.valueOf(byteCnt));
/*      */     }
/*  282 */     return byteCnt;
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
/*      */   public int convUtf8Utf16(byte[] src, int srcOffset, char[] targ, int targOffset, int fsize, JmqiTls tls) throws JmqiException {
/*  302 */     if (Trace.isOn) {
/*  303 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.JmqiDC", "convUtf16Utf8((byte[],int,char[],int,int,JmqiTls)", new Object[] { src, 
/*      */ 
/*      */             
/*  306 */             Integer.valueOf(srcOffset), targ, 
/*      */             
/*  308 */             Integer.valueOf(targOffset), 
/*  309 */             Integer.valueOf(fsize), tls });
/*      */     }
/*      */     
/*  312 */     int srcPos = srcOffset;
/*  313 */     int srcEnd = srcPos + fsize;
/*  314 */     tls.caPos = targOffset;
/*  315 */     int targMax = targ.length - 1;
/*      */ 
/*      */     
/*  318 */     while (srcPos < srcEnd) {
/*      */       char c;
/*  320 */       if (tls.caPos > targMax) {
/*      */         break;
/*      */       }
/*  323 */       int srcByte0 = src[srcPos++];
/*      */       
/*  325 */       if ((srcByte0 & 0x80) == 0) {
/*  326 */         c = (char)srcByte0;
/*      */       
/*      */       }
/*  329 */       else if ((srcByte0 & 0xE0) == 192) {
/*      */         
/*  331 */         if (srcPos >= srcEnd) {
/*  332 */           ucs2ConversionErrorFFST("convUtf8Utf16(byte[],int,char[],int,int,JmqiTls)", 1, src, srcOffset, srcPos, targOffset, fsize);
/*      */ 
/*      */           
/*  335 */           JmqiException traceRet1 = new JmqiException(this.env, 6051, new String[] { "1208 (UTF8)", "1200 (UCS2)" }, 2, 2341, null);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  340 */           if (Trace.isOn) {
/*  341 */             Trace.throwing(this, "com.ibm.mq.jmqi.internal.JmqiDC", "convUtf16Utf8((byte[],int,char[],int,int,JmqiTls)", (Throwable)traceRet1);
/*  342 */             Trace.exit(this, "com.ibm.mq.jmqi.internal.JmqiDC", "convUtf16Utf8((byte[],int,char[],int,int,JmqiTls)", 1);
/*      */           } 
/*  344 */           throw traceRet1;
/*      */         } 
/*  346 */         int srcByte1 = src[srcPos++];
/*      */         
/*  348 */         c = (char)((srcByte0 >> 2 & 0x7) << 8 | srcByte0 << 6 & 0xFF | srcByte1 & 0x3F);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  355 */       else if ((srcByte0 & 0xF0) == 224) {
/*      */         
/*  357 */         if (srcPos >= srcEnd + 1) {
/*  358 */           ucs2ConversionErrorFFST("convUtf8Utf16(byte[],int,char[],int,int,JmqiTls)", 2, src, srcOffset, srcPos, targOffset, fsize);
/*      */ 
/*      */           
/*  361 */           JmqiException traceRet2 = new JmqiException(this.env, 6051, new String[] { "1208 (UTF8)", "1200 (UCS2)" }, 2, 2341, null);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  366 */           if (Trace.isOn) {
/*  367 */             Trace.throwing(this, "com.ibm.mq.jmqi.internal.JmqiDC", "convUtf16Utf8((byte[],int,char[],int,int,JmqiTls)", (Throwable)traceRet2);
/*  368 */             Trace.exit(this, "com.ibm.mq.jmqi.internal.JmqiDC", "convUtf16Utf8((byte[],int,char[],int,int,JmqiTls)", 2);
/*      */           } 
/*  370 */           throw traceRet2;
/*      */         } 
/*  372 */         int srcByte1 = src[srcPos++];
/*  373 */         int srcByte2 = src[srcPos++];
/*      */         
/*  375 */         c = (char)((srcByte0 << 4 & 0xFF | srcByte1 >> 2 & 0xF) << 8 | srcByte1 << 6 & 0xFF | srcByte2 & 0x3F);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */         
/*  384 */         ucs2ConversionErrorFFST("convUtf8Utf16(byte [ ],int,char [ ],int,int,JmqiTls)", 3, src, srcOffset, srcPos, targOffset, fsize);
/*      */ 
/*      */         
/*  387 */         JmqiException traceRet3 = new JmqiException(this.env, 6051, new String[] { "1208 (UTF8)", "1200 (UCS2)" }, 2, 2341, null);
/*      */ 
/*      */ 
/*      */         
/*  391 */         if (Trace.isOn) {
/*  392 */           Trace.throwing(this, "com.ibm.mq.jmqi.internal.JmqiDC", "convUtf16Utf8((byte[],int,char[],int,int,JmqiTls)", (Throwable)traceRet3);
/*  393 */           Trace.exit(this, "com.ibm.mq.jmqi.internal.JmqiDC", "convUtf16Utf8((byte[],int,char[],int,int,JmqiTls)", 3);
/*      */         } 
/*  395 */         throw traceRet3;
/*      */       } 
/*  397 */       targ[tls.caPos++] = c;
/*      */     } 
/*  399 */     if (Trace.isOn) {
/*  400 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.JmqiDC", "convUtf16Utf8((byte[],int,char[],int,int,JmqiTls)", srcPos);
/*      */     }
/*  402 */     return srcPos;
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
/*      */   private void ucs2ConversionErrorFFST(String functionid, int probe, byte[] src, int srcOffset, int srcPos, int targOffset, int fsize) {
/*  419 */     if (Trace.isOn) {
/*  420 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.JmqiDC", "ucs2ConversionErrorFFST(String,int,byte [ ],int,int,int,int)", new Object[] { functionid, 
/*      */ 
/*      */ 
/*      */             
/*  424 */             Integer.valueOf(probe), src, 
/*  425 */             Integer.valueOf(srcOffset), 
/*  426 */             Integer.valueOf(srcPos), 
/*  427 */             Integer.valueOf(targOffset), Integer.valueOf(fsize) });
/*      */     }
/*  429 */     int a = Math.max(srcPos - 10, 0);
/*  430 */     int b = Math.min(srcPos + 10, srcOffset + fsize);
/*  431 */     int limit = Math.max(b - a, 0);
/*  432 */     byte[] array = new byte[limit];
/*  433 */     for (int i = 0; i < limit; i++) {
/*  434 */       array[i] = src[a + i];
/*      */     }
/*      */     
/*  437 */     HashMap<String, Object> ffstInfo = new HashMap<>();
/*  438 */     ffstInfo.put("rc", Integer.valueOf(2341));
/*  439 */     ffstInfo.put("srcOffset:", Integer.valueOf(srcOffset));
/*  440 */     ffstInfo.put("targOffset:", Integer.valueOf(targOffset));
/*  441 */     ffstInfo.put("fsize:", Integer.valueOf(fsize));
/*  442 */     ffstInfo.put("srcPos:", Integer.valueOf(srcPos));
/*  443 */     ffstInfo.put("array", JmqiTools.arrayToHexString(array));
/*  444 */     Trace.ffst(this, functionid, Integer.toString(probe), ffstInfo, null);
/*      */     
/*  446 */     if (Trace.isOn) {
/*  447 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.JmqiDC", "ucs2ConversionErrorFFST(String,int,byte [ ],int,int,int,int)");
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
/*      */   public void convertBytes(byte[] src, int srcPos, JmqiCodepage cp1, byte[] targ, int targPos, JmqiCodepage cp2, int bytes, JmqiTls tls) throws JmqiException {
/*  473 */     if (cp1.getCharsetName().equals(cp2.getCharsetName())) {
/*  474 */       System.arraycopy(src, srcPos, targ, targPos, bytes);
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  480 */     CharBuffer cb = CharBuffer.allocate(bytes);
/*      */     
/*  482 */     ByteBuffer sb = ByteBuffer.wrap(src, srcPos, bytes);
/*  483 */     ByteBuffer tb = ByteBuffer.wrap(targ, targPos, bytes);
/*      */     
/*  485 */     CharsetDecoder decoder = cp1.getDecoder();
/*  486 */     CharsetEncoder encoder = cp2.getEncoder();
/*      */     
/*  488 */     CoderResult res = decoder.decode(sb, cb, true);
/*      */     
/*  490 */     if (res.isError()) {
/*      */       try {
/*  492 */         res.throwException();
/*  493 */       } catch (Exception e) {
/*      */ 
/*      */ 
/*      */         
/*  497 */         JmqiException traceRet1 = new JmqiException(this.env, 6051, new String[] { cp1.toString(), cp2.toString() }, 2, 2330, e);
/*      */         
/*  499 */         throw traceRet1;
/*      */       } 
/*      */     }
/*      */     
/*  503 */     cb.limit(cb.position());
/*  504 */     cb.position(0);
/*  505 */     res = encoder.encode(cb, tb, true);
/*      */     
/*  507 */     if (res.isError()) {
/*      */       try {
/*  509 */         res.throwException();
/*  510 */       } catch (Exception e) {
/*      */ 
/*      */         
/*  513 */         JmqiException traceRet2 = new JmqiException(this.env, 6051, new String[] { cp1.toString(), cp2.toString() }, 2, 2330, e);
/*      */         
/*  515 */         throw traceRet2;
/*      */       } 
/*      */     }
/*      */     
/*  519 */     while (tb.position() != tb.limit()) {
/*  520 */       tb.put(cp2.spaceByte);
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
/*      */   public ByteBuffer convertBuffer(ByteBuffer srcBuff, JmqiCodepage cpSrc, JmqiCodepage cpDst) throws JmqiException {
/*  543 */     int dstLen = srcBuff.limit() * 4;
/*  544 */     CharBuffer charBuff = CharBuffer.allocate(dstLen);
/*      */     
/*  546 */     ByteBuffer dstBuff = ByteBuffer.allocate(dstLen);
/*      */     
/*  548 */     CharsetDecoder decoder = cpSrc.getDecoder();
/*  549 */     CharsetEncoder encoder = cpDst.getEncoder();
/*      */     
/*  551 */     CoderResult res = decoder.decode(srcBuff, charBuff, true);
/*      */     
/*  553 */     if (res.isError()) {
/*      */       try {
/*  555 */         res.throwException();
/*  556 */       } catch (Exception e) {
/*      */ 
/*      */ 
/*      */         
/*  560 */         JmqiException traceRet1 = new JmqiException(this.env, 6051, new String[] { cpSrc.toString(), cpDst.toString() }, 2, 2330, e);
/*      */         
/*  562 */         throw traceRet1;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  567 */     charBuff.limit(charBuff.position());
/*  568 */     charBuff.position(0);
/*      */     
/*  570 */     res = encoder.encode(charBuff, dstBuff, true);
/*      */ 
/*      */     
/*  573 */     if (res.isError()) {
/*      */       try {
/*  575 */         res.throwException();
/*  576 */       } catch (Exception e) {
/*      */ 
/*      */         
/*  579 */         JmqiException traceRet2 = new JmqiException(this.env, 6051, new String[] { cpSrc.toString(), cpDst.toString() }, 2, 2330, e);
/*      */         
/*  581 */         throw traceRet2;
/*      */       } 
/*      */     }
/*      */     
/*  585 */     dstBuff.limit(dstBuff.position());
/*  586 */     dstBuff.position(0);
/*      */     
/*  588 */     return dstBuff;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeFieldDC(String src, byte[] targ, int targPosP, int fsize, JmqiCodepage cp) throws JmqiException {
/*  598 */     byte[] srcBytes = null;
/*      */ 
/*      */     
/*      */     try {
/*  602 */       srcBytes = cp.stringToBytes(src);
/*  603 */     } catch (CharacterCodingException e) {
/*  604 */       throw new JmqiException(this.env, 6047, new String[] { "java.lang.String", cp
/*  605 */             .toString() }, 2, 2330, null);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  610 */     int availBytes = Math.min(fsize, srcBytes.length);
/*  611 */     System.arraycopy(srcBytes, 0, targ, targPosP, availBytes);
/*      */ 
/*      */     
/*  614 */     Arrays.fill(targ, targPosP + availBytes, targPosP + fsize, cp.spaceByte);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int writeStringDC(String src, byte[] targ, int targOffset, int fsize, JmqiCodepage cp) throws JmqiException {
/*  625 */     int bytes = 0;
/*  626 */     int targPos = targOffset;
/*      */     try {
/*  628 */       byte[] srcBytes = cp.stringToBytes(src);
/*      */       
/*  630 */       int targetLength = targ.length;
/*  631 */       if (fsize > 0) {
/*  632 */         targetLength = Math.min(targ.length, fsize);
/*      */       }
/*  634 */       int lastDataByte = Math.min(targetLength, srcBytes.length) + targPos;
/*      */ 
/*      */       
/*  637 */       for (int srcPos = 0; targPos < lastDataByte; targPos++) {
/*  638 */         targ[targPos] = srcBytes[srcPos++];
/*      */       }
/*      */ 
/*      */       
/*  642 */       if (fsize >= 0) {
/*  643 */         int lastPadByte = Math.min(targOffset + fsize, targ.length);
/*  644 */         while (targPos < lastPadByte) {
/*  645 */           targ[targPos++] = cp.spaceByte;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  650 */       if (lastDataByte < srcBytes.length) {
/*  651 */         bytes = targOffset - targPos;
/*      */       } else {
/*  653 */         bytes = targPos - targOffset;
/*      */       } 
/*  655 */     } catch (CharacterCodingException e) {
/*  656 */       throw new JmqiException(this.env, 6047, new String[] { "1200 (UCS2)", cp
/*  657 */             .toString() }, 2, 2330, null);
/*      */     } 
/*      */     
/*  660 */     return bytes;
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
/*      */   public void writeField(String src, byte[] targ, int targPos, int fsize, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  677 */     writeField(src, targ, targPos, fsize, cp, tls, false);
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
/*      */   public void writeField(String src, byte[] targ, int targPosP, int fsize, JmqiCodepage cp, JmqiTls tls, boolean srcIsPasswd) throws JmqiException {
/*  698 */     int targPos = targPosP;
/*  699 */     if (src == null) {
/*  700 */       int maxPos = targPos + fsize;
/*  701 */       for (; targPos < maxPos; targPos++) {
/*  702 */         targ[targPos] = cp.spaceByte;
/*      */       
/*      */       }
/*      */     }
/*  706 */     else if (cp.getCCSID() == 1208) {
/*  707 */       convUtf16Utf8(src, 0, targ, targPos, fsize, srcIsPasswd);
/*      */     }
/*      */     else {
/*      */       
/*  711 */       writeFieldDC(src, targ, targPos, fsize, cp);
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
/*      */   public void writeMQField(String src, byte[] targ, int targOffset, int fsize, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  730 */     writeMQField(src, false, targ, targOffset, fsize, cp, tls);
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
/*      */   public void writeMQField(String src, boolean obscure, byte[] targ, int targOffset, int fsize, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  752 */     int targPos = targOffset;
/*      */     
/*  754 */     if (src == null) {
/*  755 */       int maxPos = targPos + fsize;
/*  756 */       for (; targPos < maxPos; targPos++) {
/*  757 */         targ[targPos] = cp.spaceByte;
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*  764 */     else if (cp.isAscii) {
/*  765 */       int srcPos = 0;
/*  766 */       int targMax = fsize + targPos;
/*  767 */       int copyMax = Math.min(src.length(), fsize) + targPos;
/*  768 */       for (; targPos < copyMax; targPos++) {
/*  769 */         targ[targPos] = (byte)src.charAt(srcPos++);
/*      */       }
/*      */       
/*  772 */       for (; targPos < targMax; targPos++) {
/*  773 */         targ[targPos] = cp.spaceByte;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  778 */       writeFieldDC(src, targ, targPos, fsize, cp);
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int writeNullTerminatedField(String src, byte[] targ, int targPos, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  808 */     return writeNullTerminatedField(src, targ, targPos, -1, cp, tls);
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
/*      */   public int writeNullTerminatedField(String src, byte[] targ, int targPos, int fsize, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  827 */     int pos = writeString(src, targ, targPos, fsize - 1, cp, tls);
/*      */ 
/*      */     
/*  830 */     targ[targPos + pos] = 0;
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
/*  841 */     return pos;
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
/*      */   public int writeString(String src, byte[] targ, int targOffset, int fsize, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  860 */     int targPos = targOffset;
/*      */     
/*  862 */     if (src == null || src.length() == 0) {
/*  863 */       return 0;
/*      */     }
/*  865 */     int bytes = -1;
/*      */     
/*  867 */     if (cp.getCCSID() == 1208) {
/*  868 */       bytes = convUtf16Utf8(src, 0, targ, targPos, fsize);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  873 */     else if (cp.isAscii) {
/*  874 */       int targMax = Math.min(src.length() + targPos, targ.length);
/*  875 */       if (fsize >= 0) {
/*  876 */         targMax = Math.min(targMax, fsize + targPos);
/*      */       }
/*  878 */       int srcPos = 0;
/*  879 */       int targPosInitial = targPos;
/*  880 */       for (; targPos < targMax; targPos++) {
/*  881 */         char c = src.charAt(srcPos++);
/*  882 */         if ((c & 0xFF80) != 0) {
/*      */           break;
/*      */         }
/*      */ 
/*      */         
/*  887 */         targ[targPos] = (byte)c;
/*      */       } 
/*  889 */       bytes = targPos - targPosInitial;
/*      */       
/*  891 */       if (targPos < targMax) {
/*  892 */         targPos = targOffset;
/*  893 */         bytes += writeStringDC(src, targ, targPos, fsize, cp);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  898 */       bytes = writeStringDC(src, targ, targPos, fsize, cp);
/*      */     } 
/*      */ 
/*      */     
/*  902 */     if (bytes < 1) {
/*      */       
/*  904 */       JmqiException e = new JmqiException(this.env, 6052, new String[] { "1200 (UCS2)", cp.toString() }, 2, 2195, null);
/*      */       
/*  906 */       if (Trace.isOn) {
/*  907 */         Trace.throwing(this, "com.ibm.mq.jmqi.internal.JmqiDC", "writeString(String,byte[],int,int,JmqiCodepage,JmqiTls", (Throwable)e);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  913 */       throw e;
/*      */     } 
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
/*  925 */     return bytes;
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
/*      */   public int readString(byte[] src, int srcPos, int fsize, CharBuffer targ, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  947 */     if (fsize == 0) {
/*  948 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*  952 */     char[] targArr = targ.array();
/*  953 */     int pos = srcPos;
/*  954 */     int origTargPos = targ.position();
/*  955 */     int targPos = origTargPos;
/*      */     
/*  957 */     if (cp.getCCSID() == 1208) {
/*  958 */       pos = convUtf8Utf16(src, pos, targArr, targPos, fsize, tls);
/*      */ 
/*      */       
/*  961 */       targ.limit(tls.caPos);
/*  962 */       return pos - srcPos;
/*      */     } 
/*      */ 
/*      */     
/*  966 */     if (cp.isAscii) {
/*  967 */       boolean fullConv = false;
/*  968 */       int maxSrcPos = srcPos + fsize;
/*  969 */       int maxTargPos = targArr.length - 1;
/*  970 */       for (; pos < maxSrcPos && 
/*  971 */         targPos <= maxTargPos; pos++) {
/*      */ 
/*      */ 
/*      */         
/*  975 */         byte b = src[pos];
/*  976 */         if (b < 0) {
/*      */ 
/*      */ 
/*      */           
/*  980 */           fullConv = true;
/*      */           break;
/*      */         } 
/*  983 */         targArr[targPos++] = (char)b;
/*      */       } 
/*  985 */       if (!fullConv) {
/*      */         
/*  987 */         targ.limit(targPos);
/*  988 */         return pos - srcPos;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  993 */     if (tls.cachedDecoder == null || tls.cachedDecoder != cp
/*  994 */       .getDecoder()) {
/*  995 */       tls.cachedDecoder = cp.getDecoder();
/*      */     }
/*  997 */     CharsetDecoder dec = tls.cachedDecoder;
/*  998 */     targ.limit(targ.capacity());
/*  999 */     targ.position(targPos);
/* 1000 */     ByteBuffer wrapped = ByteBuffer.wrap(src, pos, fsize - pos - srcPos);
/* 1001 */     dec.decode(wrapped, targ, true);
/* 1002 */     targ.limit(targ.position());
/* 1003 */     targ.position(origTargPos);
/* 1004 */     return wrapped.position() - srcPos;
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
/*      */   public String readNullTerminatedField(byte[] src, int srcPos, int fsize, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 1029 */     int effectiveFieldSize = fsize;
/* 1030 */     for (int i = 0; i < fsize; i++) {
/* 1031 */       if (src[srcPos + i] == 0) {
/* 1032 */         effectiveFieldSize = i;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 1037 */     return readField(src, srcPos, effectiveFieldSize, cp, tls);
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
/*      */   public String readField(byte[] src, int srcPos, int fsize, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 1059 */     if (fsize == 0) {
/* 1060 */       return null;
/*      */     }
/* 1062 */     if (cp.getCCSID() == 1208) {
/* 1063 */       if (tls.ca.length < fsize) {
/* 1064 */         tls.ca = new char[fsize];
/*      */       }
/* 1066 */       convUtf8Utf16(src, srcPos, tls.ca, 0, fsize, tls);
/* 1067 */       return new String(tls.ca, 0, tls.caPos);
/*      */     } 
/*      */ 
/*      */     
/* 1071 */     if (cp.isAscii) {
/* 1072 */       int pos = srcPos;
/* 1073 */       int maxPos = srcPos + fsize;
/* 1074 */       int targPos = 0;
/* 1075 */       if (tls.ca.length < fsize) {
/* 1076 */         tls.ca = new char[fsize];
/*      */       }
/* 1078 */       for (; pos < maxPos; pos++) {
/*      */ 
/*      */         
/* 1081 */         byte b = src[pos];
/* 1082 */         if (b < 0) {
/*      */           
/*      */           try {
/*      */ 
/*      */             
/* 1087 */             ByteBuffer srcBB = ByteBuffer.wrap(src, srcPos, fsize);
/* 1088 */             CharBuffer cb = cp.getDecoder().decode(srcBB);
/* 1089 */             return cb.toString();
/* 1090 */           } catch (CharacterCodingException e) {
/*      */ 
/*      */             
/* 1093 */             JmqiException e1 = new JmqiException(this.env, 6047, new String[] { cp.toString(), "1200 (UCS2)" }, 2, 2330, e);
/*      */ 
/*      */             
/* 1096 */             if (Trace.isOn) {
/* 1097 */               Trace.throwing(this, "com.ibm.mq.jmqi.internal.JmqiDC", "readField(byte[],int,int,JmqiCodepage,JmqiTls)", (Throwable)e1, 1);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1103 */             throw e1;
/*      */           } 
/*      */         }
/* 1106 */         tls.ca[targPos++] = (char)b;
/*      */       } 
/*      */       
/* 1109 */       return new String(tls.ca, 0, targPos);
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 1114 */       ByteBuffer srcBB = ByteBuffer.wrap(src, srcPos, fsize);
/* 1115 */       CharBuffer cb = cp.getDecoder().decode(srcBB);
/* 1116 */       return cb.toString();
/* 1117 */     } catch (CharacterCodingException e) {
/*      */       
/* 1119 */       JmqiException e1 = new JmqiException(this.env, 6047, new String[] { cp.toString(), "1200 (UCS2)" }, 2, 2330, e);
/*      */ 
/*      */       
/* 1122 */       if (Trace.isOn) {
/* 1123 */         Trace.throwing(this, "com.ibm.mq.jmqi.internal.JmqiDC", "readField(byte[],int,int,JmqiCodepage,JmqiTls)", (Throwable)e1, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1128 */       throw e1;
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
/*      */   public String readMQField(byte[] src, int srcPos, int fsize, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 1149 */     if (fsize == 0) {
/* 1150 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1155 */     if (cp.isAscii) {
/* 1156 */       int pos = srcPos;
/* 1157 */       int maxPos = srcPos + fsize;
/* 1158 */       int targPos = 0;
/* 1159 */       if (tls.ca.length < fsize) {
/* 1160 */         tls.ca = new char[fsize];
/*      */       }
/* 1162 */       for (; pos < maxPos; pos++)
/*      */       {
/*      */         
/* 1165 */         tls.ca[targPos++] = (char)src[pos];
/*      */       }
/*      */       
/* 1168 */       String result = new String(tls.ca, 0, targPos);
/*      */       
/* 1170 */       return result;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 1175 */       ByteBuffer srcBB = ByteBuffer.wrap(src, srcPos, fsize);
/* 1176 */       CharBuffer cb = cp.getDecoder().decode(srcBB);
/* 1177 */       return cb.toString();
/*      */     }
/* 1179 */     catch (CharacterCodingException e) {
/*      */       
/* 1181 */       JmqiException e1 = new JmqiException(this.env, 6047, new String[] { cp.toString(), "1200 (UCS2)" }, 2, 2330, e);
/*      */       
/* 1183 */       if (Trace.isOn) {
/* 1184 */         Trace.throwing(this, "com.ibm.mq.jmqi.internal.JmqiDC", "readMQField(byte[],int,int,JmqiCodepage,JmqiTls)", (Throwable)e1, 1);
/*      */       }
/*      */ 
/*      */       
/* 1188 */       throw e1;
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
/*      */   public void writeI8(byte data, byte[] buffer, int pos, boolean swap) {
/* 1200 */     buffer[pos] = data;
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
/*      */   public void writeI16(short data, byte[] buffer, int pos, boolean swap) {
/* 1212 */     if (swap) {
/* 1213 */       buffer[pos] = (byte)(data & 0xFF);
/* 1214 */       buffer[pos + 1] = (byte)(data >> 8 & 0xFF);
/*      */     } else {
/* 1216 */       buffer[pos + 1] = (byte)(data & 0xFF);
/* 1217 */       buffer[pos] = (byte)(data >> 8 & 0xFF);
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
/*      */   public void writeI32(int data, byte[] buffer, int pos, boolean swap) {
/* 1231 */     if (swap) {
/* 1232 */       buffer[pos] = (byte)(data & 0xFF);
/* 1233 */       buffer[pos + 1] = (byte)(data >> 8 & 0xFF);
/* 1234 */       buffer[pos + 2] = (byte)(data >> 16 & 0xFF);
/* 1235 */       buffer[pos + 3] = (byte)(data >> 24 & 0xFF);
/*      */     } else {
/* 1237 */       buffer[pos + 3] = (byte)(data & 0xFF);
/* 1238 */       buffer[pos + 2] = (byte)(data >> 8 & 0xFF);
/* 1239 */       buffer[pos + 1] = (byte)(data >> 16 & 0xFF);
/* 1240 */       buffer[pos] = (byte)(data >> 24 & 0xFF);
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
/*      */   public void writeI32(int data, OutputStream os, boolean swap) throws IOException {
/* 1254 */     if (swap) {
/* 1255 */       os.write(data & 0xFF);
/* 1256 */       os.write(data >> 8 & 0xFF);
/* 1257 */       os.write(data >> 16 & 0xFF);
/* 1258 */       os.write(data >> 24 & 0xFF);
/*      */     } else {
/* 1260 */       os.write(data & 0xFF);
/* 1261 */       os.write(data >> 8 & 0xFF);
/* 1262 */       os.write(data >> 16 & 0xFF);
/* 1263 */       os.write(data >> 24 & 0xFF);
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
/*      */   public void writeI64(long data, byte[] buffer, int pos, boolean swap) {
/* 1277 */     if (swap) {
/* 1278 */       buffer[pos] = (byte)(int)(data & 0xFFL);
/* 1279 */       buffer[pos + 1] = (byte)(int)(data >> 8L & 0xFFL);
/* 1280 */       buffer[pos + 2] = (byte)(int)(data >> 16L & 0xFFL);
/* 1281 */       buffer[pos + 3] = (byte)(int)(data >> 24L & 0xFFL);
/* 1282 */       buffer[pos + 4] = (byte)(int)(data >> 32L & 0xFFL);
/* 1283 */       buffer[pos + 5] = (byte)(int)(data >> 40L & 0xFFL);
/* 1284 */       buffer[pos + 6] = (byte)(int)(data >> 48L & 0xFFL);
/* 1285 */       buffer[pos + 7] = (byte)(int)(data >> 56L & 0xFFL);
/*      */     } else {
/* 1287 */       buffer[pos + 7] = (byte)(int)(data & 0xFFL);
/* 1288 */       buffer[pos + 6] = (byte)(int)(data >> 8L & 0xFFL);
/* 1289 */       buffer[pos + 5] = (byte)(int)(data >> 16L & 0xFFL);
/* 1290 */       buffer[pos + 4] = (byte)(int)(data >> 24L & 0xFFL);
/* 1291 */       buffer[pos + 3] = (byte)(int)(data >> 32L & 0xFFL);
/* 1292 */       buffer[pos + 2] = (byte)(int)(data >> 40L & 0xFFL);
/* 1293 */       buffer[pos + 1] = (byte)(int)(data >> 48L & 0xFFL);
/* 1294 */       buffer[pos] = (byte)(int)(data >> 56L & 0xFFL);
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
/*      */   public void writeU16(int data, byte[] buffer, int pos, boolean swap) {
/* 1308 */     if (swap) {
/* 1309 */       buffer[pos] = (byte)(data & 0xFF);
/* 1310 */       buffer[pos + 1] = (byte)(data >> 8 & 0xFF);
/*      */     } else {
/* 1312 */       buffer[pos + 1] = (byte)(data & 0xFF);
/* 1313 */       buffer[pos] = (byte)(data >> 8 & 0xFF);
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
/*      */   public byte readI8(byte[] buffer, int pos, boolean swap) {
/* 1325 */     byte traceRet1 = buffer[pos];
/*      */     
/* 1327 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short readI16(byte[] buffer, int pos, boolean swap) {
/* 1338 */     int traceRet1 = swap ? ((buffer[pos + 1] & 0xFF) << 8 | buffer[pos] & 0xFF) : ((buffer[pos] & 0xFF) << 8 | buffer[pos + 1] & 0xFF);
/*      */ 
/*      */     
/* 1341 */     return (short)traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readI32(byte[] buffer, int pos, boolean swap) {
/* 1352 */     int traceRet1 = swap ? ((buffer[pos + 3] & 0xFF) << 24 | (buffer[pos + 2] & 0xFF) << 16 | (buffer[pos + 1] & 0xFF) << 8 | buffer[pos] & 0xFF) : ((buffer[pos] & 0xFF) << 24 | (buffer[pos + 1] & 0xFF) << 16 | (buffer[pos + 2] & 0xFF) << 8 | buffer[pos + 3] & 0xFF);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1359 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readI32(InputStream is, boolean swap) throws IOException {
/* 1370 */     int traceRet1 = 0;
/*      */     
/* 1372 */     if (swap) {
/* 1373 */       traceRet1 += is.read() & 0xFF;
/* 1374 */       traceRet1 += is.read() & 0xFF00;
/* 1375 */       traceRet1 += is.read() & 0xFF0000;
/* 1376 */       traceRet1 += is.read() & 0xFF000000;
/*      */     } else {
/* 1378 */       traceRet1 += is.read() & 0xFF000000;
/* 1379 */       traceRet1 += is.read() & 0xFF0000;
/* 1380 */       traceRet1 += is.read() & 0xFF00;
/* 1381 */       traceRet1 += is.read() & 0xFF;
/*      */     } 
/*      */     
/* 1384 */     return traceRet1;
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
/*      */   public long readI64(byte[] buffer, int pos, boolean swap) {
/* 1396 */     long traceRet1 = swap ? ((buffer[pos + 7] & 0xFF) << 56L | (buffer[pos + 6] & 0xFF) << 48L | (buffer[pos + 5] & 0xFF) << 40L | (buffer[pos + 4] & 0xFF) << 32L | (buffer[pos + 3] & 0xFF) << 24L | (buffer[pos + 2] & 0xFF) << 16L | (buffer[pos + 1] & 0xFF) << 8L | (buffer[pos] & 0xFF)) : ((buffer[pos] & 0xFF) << 56L | (buffer[pos + 1] & 0xFF) << 48L | (buffer[pos + 2] & 0xFF) << 40L | (buffer[pos + 3] & 0xFF) << 32L | (buffer[pos + 4] & 0xFF) << 24L | (buffer[pos + 5] & 0xFF) << 16L | (buffer[pos + 6] & 0xFF) << 8L | (buffer[pos + 7] & 0xFF));
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
/* 1411 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readU16(byte[] buffer, int pos, boolean swap) {
/* 1422 */     int traceRet1 = swap ? ((buffer[pos + 1] & 0xFF) << 8 | buffer[pos] & 0xFF) : ((buffer[pos] & 0xFF) << 8 | buffer[pos + 1] & 0xFF);
/*      */ 
/*      */     
/* 1425 */     return traceRet1;
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
/*      */   public int getAlignedStringSize(String string, int boundary) {
/* 1438 */     if (string == null || boundary <= 0) {
/* 1439 */       return 0;
/*      */     }
/*      */     
/* 1442 */     int boundaryMinusOne = boundary - 1;
/*      */     
/* 1444 */     int traceRet1 = string.length() + boundaryMinusOne & (boundaryMinusOne ^ 0xFFFFFFFF);
/*      */ 
/*      */     
/* 1447 */     return traceRet1;
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
/*      */   public int getPaddingLength(int byteArrayLength) {
/* 1480 */     return 4 - (byteArrayLength & 0x3) & 0x3;
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
/*      */   public int getAlignedLength(int byteArrayLength) {
/* 1508 */     return byteArrayLength + 3 & 0xFFFFFFFC;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\JmqiDC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */