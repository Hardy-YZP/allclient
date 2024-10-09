/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.internal.MQCommonServices;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.charset.CharacterCodingException;
/*     */ import java.nio.charset.CharsetDecoder;
/*     */ import java.nio.charset.CharsetEncoder;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.Enumeration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CCSID
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/CCSID.java";
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.mq.headers.CCSID", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/CCSID.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CCSID() {
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.entry(this, "com.ibm.mq.headers.CCSID", "<init>()");
/*     */     }
/*  71 */     UnsupportedOperationException traceRet1 = new UnsupportedOperationException();
/*     */     
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.throwing(this, "com.ibm.mq.headers.CCSID", "<init>()", traceRet1);
/*     */     }
/*  76 */     throw traceRet1;
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
/*     */   public static JmqiCodepage getJmqiCodepage(int ccsid) throws UnsupportedEncodingException {
/*  92 */     if (Trace.isOn)
/*  93 */       Trace.entry("com.ibm.mq.headers.CCSID", "getJmqiCodepage(int)", new Object[] {
/*  94 */             Integer.valueOf(ccsid)
/*     */           }); 
/*  96 */     JmqiCodepage result = null;
/*  97 */     if (ccsid == 0) {
/*  98 */       result = JmqiCodepage.getJmqiCodepage(MQCommonServices.jmqiEnv, getDefaultEncoding());
/*     */     } else {
/* 100 */       result = JmqiCodepage.getJmqiCodepage(MQCommonServices.jmqiEnv, ccsid);
/*     */     } 
/*     */     
/* 103 */     if (result == null) {
/*     */       
/* 105 */       UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException(Integer.toString(ccsid));
/* 106 */       if (Trace.isOn) {
/* 107 */         Trace.throwing("com.ibm.mq.headers.CCSID", "getJmqiCodepage(int)", traceRet1);
/*     */       }
/* 109 */       throw traceRet1;
/*     */     } 
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.exit("com.ibm.mq.headers.CCSID", "getJmqiCodepage(int)", result);
/*     */     }
/* 114 */     return result;
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
/*     */   public static String getCodepage(int ccsid) throws UnsupportedEncodingException {
/* 129 */     if (Trace.isOn)
/* 130 */       Trace.entry("com.ibm.mq.headers.CCSID", "getCodepage(int)", new Object[] {
/* 131 */             Integer.valueOf(ccsid)
/*     */           }); 
/* 133 */     String traceRet1 = getJmqiCodepage(ccsid).getCharsetName();
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.exit("com.ibm.mq.headers.CCSID", "getCodepage(int)", traceRet1);
/*     */     }
/* 137 */     return traceRet1;
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
/*     */   public static int getCCSID(String codepage) throws UnsupportedEncodingException {
/*     */     JmqiCodepage result;
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.entry("com.ibm.mq.headers.CCSID", "getCCSID(String)", new Object[] { codepage });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 156 */     if (codepage == null || codepage.length() == 0) {
/* 157 */       result = JmqiCodepage.getJmqiCodepage(MQCommonServices.jmqiEnv, getDefaultEncoding());
/*     */     } else {
/* 159 */       result = JmqiCodepage.getJmqiCodepage(MQCommonServices.jmqiEnv, codepage);
/*     */     } 
/*     */     
/* 162 */     if (result == null) {
/* 163 */       UnsupportedEncodingException e = new UnsupportedEncodingException(codepage);
/* 164 */       if (Trace.isOn) {
/* 165 */         Trace.throwing("com.ibm.mq.headers.CCSID", "getCCSID(String)", e);
/*     */       }
/* 167 */       throw e;
/*     */     } 
/*     */     
/* 170 */     int ccsid = result.getCCSID();
/*     */     
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.exit("com.ibm.mq.headers.CCSID", "getCCSID(String)", Integer.valueOf(ccsid));
/*     */     }
/* 175 */     return ccsid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String getDefaultEncoding() throws UnsupportedEncodingException {
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.entry("com.ibm.mq.headers.CCSID", "getDefaultEncoding()");
/*     */     }
/*     */     
/* 188 */     String defaultEncoding = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*     */         {
/*     */           public String run()
/*     */           {
/* 192 */             if (Trace.isOn) {
/* 193 */               Trace.entry(this, "com.ibm.mq.headers.CCSID", "run()");
/*     */             }
/* 195 */             String traceRet1 = System.getProperty("file.encoding");
/* 196 */             if (Trace.isOn) {
/* 197 */               Trace.exit(this, "com.ibm.mq.headers.null", "run()", traceRet1);
/*     */             }
/* 199 */             return traceRet1;
/*     */           }
/*     */         });
/*     */     
/* 203 */     if (defaultEncoding == null) {
/*     */       
/* 205 */       UnsupportedEncodingException e = new UnsupportedEncodingException();
/*     */       
/* 207 */       if (Trace.isOn) {
/* 208 */         Trace.throwing("com.ibm.mq.headers.CCSID", "getDefaultEncoding()", e);
/*     */       }
/* 210 */       throw e;
/*     */     } 
/*     */     
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.exit("com.ibm.mq.headers.CCSID", "getDefaultEncoding()", defaultEncoding);
/*     */     }
/* 216 */     return defaultEncoding;
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
/*     */   public static CharsetEncoder getEncoder(int ccsid) throws UnsupportedEncodingException {
/* 231 */     if (Trace.isOn)
/* 232 */       Trace.entry("com.ibm.mq.headers.CCSID", "getEncoder(int)", new Object[] {
/* 233 */             Integer.valueOf(ccsid)
/*     */           }); 
/* 235 */     CharsetEncoder traceRet1 = getJmqiCodepage(ccsid).getEncoder();
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.exit("com.ibm.mq.headers.CCSID", "getEncoder(int)", traceRet1);
/*     */     }
/* 239 */     return traceRet1;
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
/*     */   public static CharsetDecoder getDecoder(int ccsid) throws UnsupportedEncodingException {
/* 254 */     if (Trace.isOn)
/* 255 */       Trace.entry("com.ibm.mq.headers.CCSID", "getDecoder(int)", new Object[] {
/* 256 */             Integer.valueOf(ccsid)
/*     */           }); 
/* 258 */     CharsetDecoder traceRet1 = getJmqiCodepage(ccsid).getDecoder();
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.exit("com.ibm.mq.headers.CCSID", "getDecoder(int)", traceRet1);
/*     */     }
/* 262 */     return traceRet1;
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
/*     */   @Deprecated
/*     */   public static String convert(byte[] bytes, int ccsid) throws UnsupportedEncodingException {
/*     */     String traceRet1;
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.entry("com.ibm.mq.headers.CCSID", "convert(byte [ ],int)", new Object[] { bytes, 
/* 285 */             Integer.valueOf(ccsid) });
/*     */     }
/*     */     
/*     */     try {
/* 289 */       traceRet1 = getJmqiCodepage(ccsid).bytesToString(bytes);
/*     */     }
/* 291 */     catch (CharacterCodingException e) {
/* 292 */       if (Trace.isOn) {
/* 293 */         Trace.catchBlock("com.ibm.mq.headers.CCSID", "convert(byte [ ],int)", e);
/*     */       }
/* 295 */       UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(e.toString());
/*     */       
/* 297 */       traceRet2.initCause(e);
/* 298 */       if (Trace.isOn) {
/* 299 */         Trace.throwing("com.ibm.mq.headers.CCSID", "convert(byte [ ],int)", traceRet2);
/*     */       }
/* 301 */       throw traceRet2;
/*     */     } 
/* 303 */     if (Trace.isOn) {
/* 304 */       Trace.exit("com.ibm.mq.headers.CCSID", "convert(byte [ ],int)", traceRet1);
/*     */     }
/* 306 */     return traceRet1;
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
/*     */   @Deprecated
/*     */   public static String convert(byte[] bytes, int offset, int length, int ccsid) throws UnsupportedEncodingException {
/*     */     String traceRet1;
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.entry("com.ibm.mq.headers.CCSID", "convert(byte [ ],int,int,int)", new Object[] { bytes, 
/* 333 */             Integer.valueOf(offset), Integer.valueOf(length), Integer.valueOf(ccsid) });
/*     */     }
/*     */     
/*     */     try {
/* 337 */       traceRet1 = getJmqiCodepage(ccsid).bytesToString(bytes, offset, length);
/*     */     }
/* 339 */     catch (CharacterCodingException e) {
/* 340 */       if (Trace.isOn) {
/* 341 */         Trace.catchBlock("com.ibm.mq.headers.CCSID", "convert(byte [ ],int,int,int)", e);
/*     */       }
/* 343 */       UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(e.toString());
/*     */       
/* 345 */       traceRet2.initCause(e);
/* 346 */       if (Trace.isOn) {
/* 347 */         Trace.throwing("com.ibm.mq.headers.CCSID", "convert(byte [ ],int,int,int)", traceRet2);
/*     */       }
/* 349 */       throw traceRet2;
/*     */     } 
/* 351 */     if (Trace.isOn) {
/* 352 */       Trace.exit("com.ibm.mq.headers.CCSID", "convert(byte [ ],int,int,int)", traceRet1);
/*     */     }
/* 354 */     return traceRet1;
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
/*     */   @Deprecated
/*     */   public static byte[] convert(String string, int ccsid) throws UnsupportedEncodingException {
/* 376 */     if (Trace.isOn) {
/* 377 */       Trace.entry("com.ibm.mq.headers.CCSID", "convert(String,int)", new Object[] { string, 
/* 378 */             Integer.valueOf(ccsid) });
/*     */     }
/* 380 */     byte[] traceRet1 = null;
/*     */     try {
/* 382 */       traceRet1 = getJmqiCodepage(ccsid).stringToBytes(string);
/*     */     }
/* 384 */     catch (CharacterCodingException e) {
/* 385 */       if (Trace.isOn) {
/* 386 */         Trace.catchBlock("com.ibm.mq.headers.CCSID", "convert(String,int)", e);
/*     */       }
/* 388 */       UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(e.toString());
/*     */       
/* 390 */       traceRet2.initCause(e);
/* 391 */       if (Trace.isOn) {
/* 392 */         Trace.throwing("com.ibm.mq.headers.CCSID", "convert(String,int)", traceRet2);
/*     */       }
/* 394 */       throw traceRet2;
/*     */     } 
/*     */     
/* 397 */     if (Trace.isOn) {
/* 398 */       Trace.exit("com.ibm.mq.headers.CCSID", "convert(String,int)", traceRet1);
/*     */     }
/* 400 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Enumeration getCCSIDs() {
/* 408 */     Enumeration traceRet1 = JmqiCodepage.getCCSIDs();
/* 409 */     if (Trace.isOn) {
/* 410 */       Trace.data("com.ibm.mq.headers.CCSID", "getCCSIDs()", "getter", traceRet1);
/*     */     }
/* 412 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\CCSID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */