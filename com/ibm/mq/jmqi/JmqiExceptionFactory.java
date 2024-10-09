/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmqiExceptionFactory
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8593377892654124834L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiExceptionFactory.java";
/*     */   int version;
/*     */   String exceptionData;
/*     */   
/*     */   static {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.data("com.ibm.mq.jmqi.JmqiExceptionFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiExceptionFactory.java");
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
/*  79 */   ArrayList<byte[]> linkedExceptions = (ArrayList)new ArrayList<>();
/*     */ 
/*     */ 
/*     */   
/*     */   static final String EXCLAMATION_POINT = "</exclam/>";
/*     */ 
/*     */ 
/*     */   
/*     */   static final String EQUALS = "</equals/>";
/*     */ 
/*     */ 
/*     */   
/*     */   static final String AMPERSAND = "</ampersand/>";
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiExceptionFactory(JmqiEnvironment env, Throwable e) {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry("com.ibm.mq.jmqi.JmqiExceptionFactory", "<init>(JmqiEnvironment,Throwable)", new Object[] { env, e });
/*     */     }
/*     */ 
/*     */     
/* 101 */     this.version = 7;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     this.exceptionData = collapseToString(e);
/*     */ 
/*     */     
/* 111 */     Throwable linkedException = e.getCause();
/* 112 */     if (linkedException != null) {
/*     */       
/*     */       try {
/* 115 */         ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 116 */         ObjectOutputStream out = new ObjectOutputStream(bos);
/* 117 */         out.writeObject(linkedException);
/* 118 */         out.close();
/*     */ 
/*     */         
/* 121 */         byte[] buf = bos.toByteArray();
/* 122 */         bos.close();
/*     */ 
/*     */         
/* 125 */         this.linkedExceptions.add(buf);
/*     */       }
/* 127 */       catch (IOException ioe) {
/*     */ 
/*     */         
/* 130 */         if (Trace.isOn) {
/* 131 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiExceptionFactory", "<init>(JmqiEnvironment,Throwable)", ioe);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.exit("com.ibm.mq.jmqi.JmqiExceptionFactory", "<init>(JmqiEnvironment,Throwable)");
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
/*     */   private Object readResolve() throws ObjectStreamException {
/* 149 */     Object traceRet1 = createException();
/* 150 */     return traceRet1;
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
/*     */   public Exception createException() {
/* 163 */     Throwable exceptionToLink = null;
/*     */     
/* 165 */     for (int i = 0; i < this.linkedExceptions.size(); i++) {
/*     */       
/* 167 */       byte[] buf = this.linkedExceptions.get(i);
/*     */ 
/*     */       
/*     */       try {
/* 171 */         ByteArrayInputStream bis = new ByteArrayInputStream(buf);
/* 172 */         ObjectInputStream ois = new ObjectInputStream(bis);
/* 173 */         Throwable t = (Throwable)ois.readObject();
/* 174 */         ois.close();
/* 175 */         bis.close();
/*     */         
/* 177 */         exceptionToLink = t;
/*     */       }
/* 179 */       catch (IOException ioe) {
/*     */ 
/*     */         
/* 182 */         ioe.printStackTrace();
/*     */       }
/* 184 */       catch (ClassNotFoundException cnfe) {
/*     */ 
/*     */         
/* 187 */         cnfe.printStackTrace();
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 194 */     Exception e = inflateFromString(this.exceptionData, exceptionToLink);
/*     */ 
/*     */     
/* 197 */     return e;
/*     */   }
/*     */ 
/*     */   
/*     */   private String collapseToString(Throwable exception) {
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiExceptionFactory", "collapseToString(Throwable)", new Object[] { exception });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 219 */     StringBuffer data = new StringBuffer();
/*     */ 
/*     */     
/* 222 */     data.append("!V" + this.version);
/* 223 */     data.append("!JMQI_EXCEPTION");
/* 224 */     data.append("!" + removeInvalidChars(exception.getClass().getName()));
/*     */ 
/*     */     
/* 227 */     data.append("!" + removeInvalidChars(((JmqiException)exception).getMessage()));
/* 228 */     data.append("!" + ((JmqiException)exception).getCompCode());
/* 229 */     data.append("!" + ((JmqiException)exception).getReason());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 234 */     StackTraceElement[] stackTrace = exception.getStackTrace();
/* 235 */     StringBuffer serializedStackTrace = new StringBuffer();
/*     */     
/*     */     try {
/* 238 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 239 */       ObjectOutputStream out = new ObjectOutputStream(bos);
/* 240 */       out.writeObject(stackTrace);
/* 241 */       out.close();
/*     */ 
/*     */       
/* 244 */       byte[] buf = bos.toByteArray();
/* 245 */       bos.close();
/*     */ 
/*     */       
/* 248 */       for (int j = 0; j < buf.length; j++) {
/* 249 */         serializedStackTrace.append(Byte.toString(buf[j]));
/* 250 */         serializedStackTrace.append(",");
/*     */       }
/*     */     
/* 253 */     } catch (IOException ioe) {
/*     */       
/* 255 */       if (Trace.isOn) {
/* 256 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiExceptionFactory", "collapseToString(Throwable)", ioe, 1);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 262 */     data.append("!" + serializedStackTrace);
/*     */ 
/*     */     
/* 265 */     data.append("!EXCEPTION_DETAILS!");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 272 */     if (exception instanceof JmqiException) {
/*     */       
/* 274 */       data.append("wmqLogMessage=").append(removeInvalidChars(((JmqiException)exception).getWmqLogMessage()));
/* 275 */       data.append("&wmqMessageExplanation=").append(removeInvalidChars(((JmqiException)exception).getWmqMsgExplanation()));
/* 276 */       data.append("&wmqMessageSeverity=").append(((JmqiException)exception).getWmqMsgSeverity());
/* 277 */       data.append("&wmqMessageSummary=").append(removeInvalidChars(((JmqiException)exception).getWmqMsgSummary()));
/* 278 */       data.append("&wmqMsgUserResponse=").append(removeInvalidChars(((JmqiException)exception).getWmqMsgUserResponse()));
/* 279 */       data.append("&amqXXXX=").append(((JmqiException)exception).getAMQXXXX());
/*     */ 
/*     */       
/* 282 */       String[] inserts = ((JmqiException)exception).getInserts();
/*     */ 
/*     */       
/* 285 */       StringBuffer serializedInserts = new StringBuffer();
/*     */       
/*     */       try {
/* 288 */         ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 289 */         ObjectOutputStream out = new ObjectOutputStream(bos);
/* 290 */         out.writeObject(inserts);
/* 291 */         out.close();
/*     */ 
/*     */         
/* 294 */         byte[] buf = bos.toByteArray();
/* 295 */         bos.close();
/*     */ 
/*     */         
/* 298 */         for (int j = 0; j < buf.length; j++) {
/* 299 */           serializedInserts.append(Byte.toString(buf[j]));
/* 300 */           serializedInserts.append(",");
/*     */         }
/*     */       
/* 303 */       } catch (IOException ioe) {
/* 304 */         if (Trace.isOn)
/*     */         {
/* 306 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiExceptionFactory", "collapseToString(Throwable)", ioe, 2);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 312 */       data.append("&inserts=").append(serializedInserts);
/*     */     } 
/*     */     
/* 315 */     String traceRet1 = data.toString();
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.exit(this, "collapseToString(Throwable)", traceRet1);
/*     */     }
/* 319 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Exception inflateFromString(String string, Throwable nestedException) {
/* 327 */     Exception e = null;
/* 328 */     String[] fields = string.split("!");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 337 */     String exceptionType = replaceInvalidChars(fields[3]);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 342 */     String exceptionCompCode = replaceInvalidChars(fields[5]);
/*     */ 
/*     */     
/* 345 */     String exceptionReason = replaceInvalidChars(fields[6]);
/*     */ 
/*     */     
/* 348 */     String stackTraceString = fields[7];
/* 349 */     StackTraceElement[] stackTrace = null;
/*     */     try {
/* 351 */       String[] stackTraceStringArray = stackTraceString.split(",");
/*     */       
/* 353 */       byte[] stackTraceBytes = new byte[stackTraceStringArray.length];
/* 354 */       for (int j = 0; j < stackTraceBytes.length; j++) {
/* 355 */         stackTraceBytes[j] = Byte.parseByte(stackTraceStringArray[j]);
/*     */       }
/*     */       
/* 358 */       ByteArrayInputStream bis = new ByteArrayInputStream(stackTraceBytes);
/* 359 */       ObjectInputStream ois = new ObjectInputStream(bis);
/* 360 */       stackTrace = (StackTraceElement[])ois.readObject();
/* 361 */       ois.close();
/* 362 */       bis.close();
/*     */     }
/* 364 */     catch (IOException ioe) {
/*     */ 
/*     */       
/* 367 */       ioe.printStackTrace();
/*     */     }
/* 369 */     catch (ClassNotFoundException cnfe) {
/*     */ 
/*     */       
/* 372 */       cnfe.printStackTrace();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 378 */     String[] eData = fields[9].split("&");
/*     */     
/* 380 */     String eWmqLogMessage = eData[0];
/* 381 */     eWmqLogMessage = eWmqLogMessage.substring(eWmqLogMessage.indexOf("=") + 1, eWmqLogMessage.length());
/* 382 */     eWmqLogMessage = replaceInvalidChars(eWmqLogMessage);
/*     */     
/* 384 */     String eWmqMessageExplanation = eData[1];
/* 385 */     eWmqMessageExplanation = eWmqMessageExplanation.substring(eWmqMessageExplanation.indexOf("=") + 1, eWmqMessageExplanation.length());
/* 386 */     eWmqMessageExplanation = replaceInvalidChars(eWmqMessageExplanation);
/*     */ 
/*     */ 
/*     */     
/* 390 */     String eAmqXXXX = eData[5];
/* 391 */     eAmqXXXX = eAmqXXXX.substring(eAmqXXXX.indexOf("=") + 1, eAmqXXXX.length());
/* 392 */     eAmqXXXX = replaceInvalidChars(eAmqXXXX);
/*     */     
/* 394 */     String eInsertsString = eData[6];
/* 395 */     eInsertsString = eInsertsString.substring(eInsertsString.indexOf("=") + 1, eInsertsString.length());
/* 396 */     String[] eInserts = null;
/*     */     try {
/* 398 */       String[] eInsertsStringArray = eInsertsString.split(",");
/*     */       
/* 400 */       byte[] eInsertsBytes = new byte[eInsertsStringArray.length];
/* 401 */       for (int j = 0; j < eInsertsBytes.length; j++) {
/* 402 */         eInsertsBytes[j] = Byte.parseByte(eInsertsStringArray[j]);
/*     */       }
/*     */       
/* 405 */       ByteArrayInputStream bis = new ByteArrayInputStream(eInsertsBytes);
/* 406 */       ObjectInputStream ois = new ObjectInputStream(bis);
/* 407 */       eInserts = (String[])ois.readObject();
/* 408 */       ois.close();
/* 409 */       bis.close();
/*     */     }
/* 411 */     catch (IOException ioe) {
/*     */ 
/*     */       
/* 414 */       ioe.printStackTrace();
/*     */     }
/* 416 */     catch (ClassNotFoundException cnfe) {
/*     */ 
/*     */       
/* 419 */       cnfe.printStackTrace();
/*     */     } 
/*     */ 
/*     */     
/* 423 */     if (exceptionType.equals(JmqiException.class.getName())) {
/* 424 */       e = new JmqiException(null, Integer.parseInt(eAmqXXXX), eInserts, Integer.parseInt(exceptionCompCode), Integer.parseInt(exceptionReason), nestedException);
/* 425 */       e.setStackTrace(stackTrace);
/*     */     } 
/*     */     
/* 428 */     return e;
/*     */   }
/*     */ 
/*     */   
/*     */   private String removeInvalidChars(String inputLine) {
/* 433 */     if (Trace.isOn) {
/* 434 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiExceptionFactory", "removeInvalidChars(String)", new Object[] { inputLine });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 445 */     if (inputLine == null) {
/*     */       
/* 447 */       if (Trace.isOn) {
/* 448 */         Trace.exit(this, "com.ibm.mq.jmqi.JmqiExceptionFactory", "removeInvalidChars(String)", null, 1);
/*     */       }
/* 450 */       return null;
/*     */     } 
/*     */     
/* 453 */     String result = inputLine;
/* 454 */     result = result.replaceAll("!", "</exclam/>");
/* 455 */     result = result.replaceAll("&", "</ampersand/>");
/* 456 */     result = result.replaceAll("=", "</equals/>");
/*     */     
/* 458 */     if (Trace.isOn) {
/* 459 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiExceptionFactory", "removeInvalidChars(String)", result, 2);
/*     */     }
/* 461 */     return result;
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
/*     */   private String replaceInvalidChars(String inputLine) {
/* 474 */     String result = inputLine;
/* 475 */     result = result.replaceAll("</exclam/>", "!");
/* 476 */     result = result.replaceAll("</ampersand/>", "&");
/* 477 */     result = result.replaceAll("</equals/>", "=");
/*     */     
/* 479 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\JmqiExceptionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */