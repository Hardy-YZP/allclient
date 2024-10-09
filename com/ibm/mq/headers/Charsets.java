/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.internal.MQCommonServices;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.charset.CharacterCodingException;
/*     */ import java.nio.charset.CodingErrorAction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Charsets
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/Charsets.java";
/*     */   
/*     */   static {
/*  42 */     if (Trace.isOn) {
/*  43 */       Trace.data("com.ibm.mq.headers.Charsets", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/Charsets.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Charsets() {
/*  53 */     super(MQCommonServices.jmqiEnv);
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.entry(this, "com.ibm.mq.headers.Charsets", "<init>()");
/*     */     }
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.exit(this, "com.ibm.mq.headers.Charsets", "<init>()");
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
/*     */   public static String convert(byte[] bytes, int ccsid) throws UnsupportedEncodingException {
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry("com.ibm.mq.headers.Charsets", "convert(byte [ ],int)", new Object[] { bytes, 
/*  76 */             Integer.valueOf(ccsid) });
/*     */     }
/*  78 */     String traceRet1 = convert(bytes, 0, bytes.length, ccsid);
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit("com.ibm.mq.headers.Charsets", "convert(byte [ ],int)", traceRet1);
/*     */     }
/*  82 */     return traceRet1;
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
/*     */   public static String convert(byte[] bytes, int ccsid, int encoding) throws UnsupportedEncodingException {
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.entry("com.ibm.mq.headers.Charsets", "convert(byte [ ],int,int)", new Object[] { bytes, 
/*  99 */             Integer.valueOf(ccsid) });
/*     */     }
/* 101 */     String traceRet1 = convert(bytes, 0, bytes.length, ccsid, encoding);
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.exit("com.ibm.mq.headers.Charsets", "convert(byte [ ],int,int)", traceRet1);
/*     */     }
/* 105 */     return traceRet1;
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
/*     */   public static String convert(byte[] bytes, int offset, int length, int ccsid) throws UnsupportedEncodingException {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry("com.ibm.mq.headers.Charsets", "convert(byte [ ],int,int,int)", new Object[] { bytes, 
/* 124 */             Integer.valueOf(offset), Integer.valueOf(length), Integer.valueOf(ccsid) });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 129 */     String traceRet1 = convert(bytes, offset, length, ccsid, 0);
/*     */     
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.exit("com.ibm.mq.headers.Charsets", "convert(byte [ ],int,int,int)", traceRet1);
/*     */     }
/* 134 */     return traceRet1;
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
/*     */   public static String convert(byte[] bytes, int offset, int length, int ccsid, int encoding) throws UnsupportedEncodingException {
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.entry("com.ibm.mq.headers.Charsets", "convert(byte [ ],int,int,int,int)", new Object[] { bytes, 
/* 153 */             Integer.valueOf(offset), Integer.valueOf(length), Integer.valueOf(ccsid), Integer.valueOf(encoding) });
/*     */     }
/*     */     
/*     */     try {
/*     */       String traceRet1;
/* 158 */       if (encoding == 0) {
/* 159 */         traceRet1 = JmqiCodepage.getJmqiCodepage((JmqiEnvironment)null, ccsid, CodingErrorAction.REPLACE).bytesToString(bytes, offset, length);
/*     */       }
/*     */       else {
/*     */         
/* 163 */         traceRet1 = JmqiCodepage.getJmqiCodepage((JmqiEnvironment)null, ccsid, CodingErrorAction.REPLACE, null, encoding).bytesToString(bytes, offset, length);
/*     */       } 
/*     */       
/* 166 */       if (Trace.isOn) {
/* 167 */         Trace.exit("com.ibm.mq.headers.Charsets", "convert(byte [ ],int,int,int,int)", traceRet1);
/*     */       }
/* 169 */       return traceRet1;
/*     */     
/*     */     }
/* 172 */     catch (CharacterCodingException e) {
/* 173 */       if (Trace.isOn) {
/* 174 */         Trace.catchBlock("com.ibm.mq.headers.Charsets", "convert(byte [ ],int,int,int,int)", e);
/*     */       }
/* 176 */       UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(e.getMessage());
/* 177 */       if (Trace.isOn) {
/* 178 */         Trace.throwing("com.ibm.mq.headers.Charsets", "convert(byte [ ],int,int,int,int)", traceRet2);
/*     */       }
/* 180 */       throw traceRet2;
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
/*     */   public static String convert(ByteBuffer bytes, int offset, int length, int ccsid) throws UnsupportedEncodingException {
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.entry("com.ibm.mq.headers.Charsets", "convert(ByteBuffer,int,int,int)", new Object[] { bytes, 
/* 200 */             Integer.valueOf(offset), Integer.valueOf(length), Integer.valueOf(ccsid) });
/*     */     }
/*     */     
/* 203 */     byte[] theBytes = new byte[length];
/* 204 */     bytes.get(theBytes, offset, length);
/* 205 */     String traceRet1 = convert(theBytes, ccsid);
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.exit("com.ibm.mq.headers.Charsets", "convert(ByteBuffer,int,int,int)", traceRet1);
/*     */     }
/* 209 */     return traceRet1;
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
/*     */   public static byte[] convert(String string, int ccsid) throws UnsupportedEncodingException {
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.entry("com.ibm.mq.headers.Charsets", "convert(String,int)", new Object[] { string, 
/* 225 */             Integer.valueOf(ccsid) });
/*     */     }
/*     */     try {
/* 228 */       byte[] bytes = JmqiCodepage.getJmqiCodepage((JmqiEnvironment)null, ccsid, CodingErrorAction.REPLACE).stringToBytes(string);
/*     */       
/* 230 */       if (Trace.isOn) {
/* 231 */         Trace.exit("com.ibm.mq.headers.Charsets", "convert(String,int)", bytes);
/*     */       }
/* 233 */       return bytes;
/*     */     
/*     */     }
/* 236 */     catch (CharacterCodingException e) {
/* 237 */       if (Trace.isOn) {
/* 238 */         Trace.catchBlock("com.ibm.mq.headers.Charsets", "convert(String,int)", e);
/*     */       }
/* 240 */       e.printStackTrace();
/* 241 */       UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException(e.getMessage());
/* 242 */       if (Trace.isOn) {
/* 243 */         Trace.throwing("com.ibm.mq.headers.Charsets", "convert(String,int)", traceRet1);
/*     */       }
/* 245 */       throw traceRet1;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\Charsets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */