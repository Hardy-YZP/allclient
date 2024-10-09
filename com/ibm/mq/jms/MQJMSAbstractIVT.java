/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsExceptionDetail;
/*     */ import com.ibm.msg.client.services.Version;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.Charset;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
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
/*     */ public abstract class MQJMSAbstractIVT
/*     */ {
/*     */   static final String copyright_notice = "Licensed Materials - Property of IBM 5724-I07 (c) Copyright IBM Corp. 2004, 2016 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   protected static final String QUEUE = "SYSTEM.DEFAULT.LOCAL.QUEUE";
/*     */   protected static final String qcfLookup = "ivtQCF";
/*     */   protected static final String qLookup = "ivtQ";
/*     */   public static PrintWriter printWriter;
/*     */   protected static String CLASSNAME;
/*     */   protected static final Object watcherLock;
/*     */   protected static final int watcherTimeout = 600000;
/*     */   protected static boolean done;
/*     */   private static final SimpleDateFormat dateFormat;
/*     */   private static final String endOfLineCharacter;
/*     */   protected static final String DEFAULT_CHANNEL = "SYSTEM.DEF.SVRCONN";
/*     */   protected static final String TOPIC = "MQJMS/PSIVT/Information";
/*     */   static final String STREAM = "SYSTEM.BROKER.DEFAULT.STREAM";
/*     */   protected static final String tcfLookup = "ivtTCF";
/*     */   protected static final String tLookup = "ivtT";
/*     */   static int titleSize;
/*     */   
/*     */   static {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.data("com.ibm.mq.jms.MQJMSAbstractIVT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQJMSAbstractIVT.java");
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
/*     */ 
/*     */     
/*  77 */     if (Trace.isOn)
/*  78 */       Trace.entry("com.ibm.mq.jms.MQJMSAbstractIVT", "static()"); 
/*     */   }
/*  80 */   protected static String lineSeparator = PropertyStore.line_separator;
/*  81 */   protected static Object threadWaitLock; protected static final int waitTime = 5000; static boolean msgListenerFired; protected static void setupPrintWriter() throws UnsupportedEncodingException { if (Trace.isOn) Trace.entry("com.ibm.mq.jms.MQJMSAbstractIVT", "setupPrintWriter()");  String encProp = "console.encoding"; PropertyStore.register(encProp, ""); String encoding = PropertyStore.getStringProperty(encProp); if (NLSServices.isWindowsLatinCodepage() && !encoding.equals("") && encoding != null) { printWriter = new PrintWriter(new OutputStreamWriter(System.out, encoding)); } else { printWriter = new PrintWriter(new OutputStreamWriter(System.out, Charset.defaultCharset())); }  if (Trace.isOn) Trace.exit("com.ibm.mq.jms.MQJMSAbstractIVT", "setupPrintWriter()");  } static { if (Trace.isOn) {
/*  82 */       Trace.exit("com.ibm.mq.jms.MQJMSAbstractIVT", "static()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  90 */     printWriter = null;
/*     */ 
/*     */     
/*  93 */     watcherLock = new Object();
/*     */     
/*  95 */     done = false;
/*     */     
/*  97 */     dateFormat = new SimpleDateFormat("yyyyMMdd.kkmmss.SSS0.");
/*  98 */     endOfLineCharacter = System.getProperty("line.separator");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     titleSize = 30;
/*     */     
/* 148 */     threadWaitLock = new Object();
/*     */     
/* 150 */     msgListenerFired = false; }
/*     */   
/*     */   private static String padTitle(String text) {
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.entry("com.ibm.mq.jms.MQJMSAbstractIVT", "padTitle(String)", new Object[] { text });
/*     */     }
/* 156 */     StringBuffer buffer = new StringBuffer(titleSize);
/* 157 */     int textLength = text.trim().length();
/*     */     
/* 159 */     int padding = titleSize - textLength;
/* 160 */     for (int count = 0; count < padding; count++) {
/* 161 */       buffer.append(" ");
/*     */     }
/*     */     
/* 164 */     buffer.append(text.trim()).append(" : ");
/*     */     
/* 166 */     String traceRet1 = buffer.toString();
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit("com.ibm.mq.jms.MQJMSAbstractIVT", "padTitle(String)", traceRet1);
/*     */     }
/* 170 */     return traceRet1; } protected static void displayUsage() { if (Trace.isOn)
/*     */       Trace.entry("com.ibm.mq.jms.MQJMSAbstractIVT", "displayUsage()");  if (Trace.isOn)
/*     */       Trace.entry(CLASSNAME, "displayUsage");  printWriter.println(NLSServices.getMessage("JMSMQ5065"));
/*     */     printWriter.println(NLSServices.getMessage("JMSMQ5066"));
/*     */     printWriter.println(NLSServices.getMessage("JMSMQ5067"));
/*     */     printWriter.println(NLSServices.getMessage("JMSMQ5068"));
/*     */     printWriter.flush();
/*     */     if (Trace.isOn)
/*     */       Trace.exit("com.ibm.mq.jms.MQJMSAbstractIVT", "displayUsage()");  }
/* 179 */   public void handleException(Exception exception, PrintWriter stream) { if (Trace.isOn) {
/* 180 */       Trace.entry(this, "com.ibm.mq.jms.MQJMSAbstractIVT", "handleException(final Exception,PrintWriter)", new Object[] { exception, stream });
/*     */     }
/*     */ 
/*     */     
/* 184 */     int causeIndex = 0;
/* 185 */     Throwable loopException = exception;
/*     */     
/* 187 */     while (loopException != null) {
/* 188 */       if (causeIndex == 0) {
/* 189 */         stream.println(padTitle(NLSServices.getMessage("JMSMQ5100")) + loopException
/* 190 */             .getMessage());
/*     */       } else {
/*     */         
/* 193 */         stream.println(padTitle(NLSServices.getMessage("JMSMQ5103", new Object[] {
/* 194 */                   Integer.toString(causeIndex)
/* 195 */                 })) + loopException.getMessage());
/*     */       } 
/*     */       
/* 198 */       stream.println(padTitle(NLSServices.getMessage("JMSMQ5101")) + loopException
/* 199 */           .getClass());
/*     */       
/* 201 */       if (loopException instanceof MQException) {
/* 202 */         MQException mqException = (MQException)loopException;
/* 203 */         stream.println(padTitle(NLSServices.getMessage("JMSMQ5107")) + mqException.completionCode);
/*     */         
/* 205 */         stream.println(padTitle(NLSServices.getMessage("JMSMQ5108")) + mqException.reasonCode);
/*     */       
/*     */       }
/* 208 */       else if (loopException instanceof JmqiException) {
/* 209 */         JmqiException jmqiException = (JmqiException)loopException;
/* 210 */         stream.println(padTitle(NLSServices.getMessage("JMSMQ5107")) + jmqiException
/* 211 */             .getCompCode());
/* 212 */         stream.println(padTitle(NLSServices.getMessage("JMSMQ5108")) + jmqiException
/* 213 */             .getReason());
/*     */         
/* 215 */         String logMessage = jmqiException.getWmqLogMessage();
/* 216 */         logMessage = (logMessage == null) ? "" : logMessage;
/* 217 */         logMessage = logMessage.replaceAll(lineSeparator, lineSeparator + padTitle(""));
/* 218 */         stream.println(padTitle(NLSServices.getMessage("JMSMQ5109")) + logMessage);
/*     */         
/* 220 */         stream.println(padTitle(NLSServices.getMessage("JMSMQ5109")) + jmqiException
/* 221 */             .getWmqMsgExplanation());
/* 222 */         stream.println(padTitle(NLSServices.getMessage("JMSMQ5111")) + jmqiException
/* 223 */             .getWmqMsgSeverity());
/* 224 */         stream.println(padTitle(NLSServices.getMessage("JMSMQ5112")) + jmqiException
/* 225 */             .getWmqMsgSummary());
/*     */         
/* 227 */         String userResponse = jmqiException.getWmqMsgUserResponse();
/* 228 */         userResponse = (userResponse == null) ? "" : userResponse;
/* 229 */         userResponse = userResponse.replaceAll(lineSeparator, lineSeparator + padTitle(""));
/* 230 */         stream.println(padTitle(NLSServices.getMessage("JMSMQ5113")) + userResponse);
/*     */       
/*     */       }
/* 233 */       else if (loopException instanceof JmsExceptionDetail) {
/* 234 */         JmsExceptionDetail de = (JmsExceptionDetail)loopException;
/* 235 */         stream.println(padTitle(NLSServices.getMessage("JMSMQ5104")) + ((JMSException)loopException)
/* 236 */             .getErrorCode());
/* 237 */         stream.println(padTitle(NLSServices.getMessage("JMSMQ5105")) + de
/* 238 */             .getExplanation());
/* 239 */         stream.println(padTitle(NLSServices.getMessage("JMSMQ5106")) + de
/* 240 */             .getUserAction());
/*     */         
/* 242 */         Iterator<?> keys = de.getKeys();
/* 243 */         if (keys != null) {
/* 244 */           while (keys.hasNext()) {
/* 245 */             String key = (String)keys.next();
/* 246 */             stream.println(padTitle(key) + de.getValue(key));
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 251 */       dumpStackTrace(loopException, stream);
/*     */       
/* 253 */       loopException = loopException.getCause();
/* 254 */       causeIndex++;
/*     */     } 
/* 256 */     stream.flush();
/* 257 */     ivtFail();
/*     */     
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.exit(this, "com.ibm.mq.jms.MQJMSAbstractIVT", "handleException(final Exception,PrintWriter)");
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void dumpStackTrace(Throwable e, PrintWriter stream) {
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.entry("com.ibm.mq.jms.MQJMSAbstractIVT", "dumpStackTrace(final Throwable,PrintWriter)", new Object[] { e, stream });
/*     */     }
/*     */     
/* 271 */     StackTraceElement[] stack = e.getStackTrace();
/* 272 */     for (int count = 0; count < stack.length; count++) {
/*     */       String prefix;
/* 274 */       if (count == 0) {
/* 275 */         prefix = NLSServices.getMessage("JMSMQ5102");
/*     */       } else {
/*     */         
/* 278 */         prefix = "";
/*     */       } 
/*     */       
/* 281 */       String className = stack[count].getClassName();
/* 282 */       String methodName = stack[count].getMethodName();
/* 283 */       String fileName = stack[count].getFileName();
/* 284 */       int line = stack[count].getLineNumber();
/*     */       
/* 286 */       stream.println(padTitle(prefix) + className + "." + methodName + "(" + fileName + ":" + line + ")");
/* 287 */       stream.flush();
/*     */     } 
/* 289 */     if (Trace.isOn) {
/* 290 */       Trace.exit("com.ibm.mq.jms.MQJMSAbstractIVT", "dumpStackTrace(final Throwable,PrintWriter)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void runFrom(PrintWriter stream, Class<?> c) {
/* 301 */     if (Trace.isOn) {
/* 302 */       Trace.entry("com.ibm.mq.jms.MQJMSAbstractIVT", "runFrom(PrintWriter,Class<?>)", new Object[] { stream, c });
/*     */     }
/*     */     
/* 305 */     String resourceName = c.getName().replace('.', '/') + ".class";
/* 306 */     URL url = c.getClassLoader().getResource(resourceName);
/* 307 */     String protocol = url.getProtocol();
/*     */ 
/*     */     
/* 310 */     if (protocol.equalsIgnoreCase("jar")) {
/* 311 */       String path = url.getPath();
/* 312 */       outputStr = path.substring(0, path.indexOf("!"));
/*     */     } else {
/*     */       
/* 315 */       outputStr = url.toString();
/*     */     } 
/* 317 */     String outputStr = outputStr.replaceAll("%20", " ");
/* 318 */     stream.println(NLSServices.getMessage("JMSMQ5114", new Object[] { c.getName(), outputStr }));
/*     */     
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.exit("com.ibm.mq.jms.MQJMSAbstractIVT", "runFrom(PrintWriter,Class<?>)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void detailedClassChecking(boolean useJNDI, String icf) {
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.entry("com.ibm.mq.jms.MQJMSAbstractIVT", "detailedClassChecking(boolean,String)", new Object[] {
/* 329 */             Boolean.valueOf(useJNDI), icf
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 338 */       if (Trace.isOn) {
/* 339 */         Trace.traceData(CLASSNAME, "checking classpath", null);
/*     */       }
/*     */ 
/*     */       
/*     */       try {
/* 344 */         Class.forName("javax.jms.Message");
/*     */       }
/* 346 */       catch (ClassNotFoundException e) {
/* 347 */         if (Trace.isOn) {
/* 348 */           Trace.catchBlock("com.ibm.mq.jms.MQJMSAbstractIVT", "detailedClassChecking(boolean,String)", e, 1);
/*     */         }
/*     */         
/* 351 */         if (Trace.isOn) {
/* 352 */           Trace.traceData(CLASSNAME, "javax.jms.Message missing", null);
/*     */         }
/* 354 */         String msg = NLSServices.getMessage("JMSMQ5037", new Object[] { "javax.jms.jar" });
/* 355 */         System.err.println(msg);
/* 356 */         printWriter.flush();
/* 357 */         if (Trace.isOn) {
/* 358 */           Trace.throwing("com.ibm.mq.jms.MQJMSAbstractIVT", "detailedClassChecking(boolean,String)", e, 1);
/*     */         }
/*     */         
/* 361 */         throw e;
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 366 */         Class.forName("javax.naming.InitialContext");
/*     */       }
/* 368 */       catch (ClassNotFoundException e) {
/* 369 */         if (Trace.isOn) {
/* 370 */           Trace.catchBlock("com.ibm.mq.jms.MQJMSAbstractIVT", "detailedClassChecking(boolean,String)", e, 2);
/*     */         }
/*     */         
/* 373 */         if (Trace.isOn) {
/* 374 */           Trace.traceData(CLASSNAME, "javax.naming.InitialContext missing", null);
/*     */         }
/* 376 */         String msg = NLSServices.getMessage("JMSMQ5037", new Object[] { "jndi.jar" });
/* 377 */         System.err.println(msg);
/* 378 */         printWriter.flush();
/* 379 */         if (Trace.isOn) {
/* 380 */           Trace.throwing("com.ibm.mq.jms.MQJMSAbstractIVT", "detailedClassChecking(boolean,String)", e, 2);
/*     */         }
/*     */         
/* 383 */         throw e;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 388 */       if (useJNDI) {
/*     */         try {
/* 390 */           Class.forName(icf);
/*     */         }
/* 392 */         catch (ClassNotFoundException e) {
/* 393 */           if (Trace.isOn) {
/* 394 */             Trace.catchBlock("com.ibm.mq.jms.MQJMSAbstractIVT", "detailedClassChecking(boolean,String)", e, 3);
/*     */           }
/*     */           
/* 397 */           if (Trace.isOn) {
/* 398 */             Trace.traceData(CLASSNAME, "jndi provider for '" + icf + "' missing", null);
/*     */           }
/* 400 */           String msg = NLSServices.getMessage("JMSMQ5038", new Object[] { icf });
/* 401 */           System.err.println(msg);
/* 402 */           printWriter.flush();
/* 403 */           if (Trace.isOn) {
/* 404 */             Trace.throwing("com.ibm.mq.jms.MQJMSAbstractIVT", "detailedClassChecking(boolean,String)", e, 3);
/*     */           }
/*     */           
/* 407 */           throw e;
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 416 */     catch (ClassNotFoundException e) {
/* 417 */       if (Trace.isOn) {
/* 418 */         Trace.catchBlock("com.ibm.mq.jms.MQJMSAbstractIVT", "detailedClassChecking(boolean,String)", e, 4);
/*     */       }
/*     */       
/* 421 */       if (Trace.isOn) {
/* 422 */         Trace.traceData(CLASSNAME, "classpath check failed with " + e, null);
/* 423 */         Trace.exit(CLASSNAME, "main");
/*     */       } 
/* 425 */       System.err.println(e);
/* 426 */       printWriter.flush();
/* 427 */       System.exit(-1);
/*     */     } 
/* 429 */     if (Trace.isOn) {
/* 430 */       Trace.exit("com.ibm.mq.jms.MQJMSAbstractIVT", "detailedClassChecking(boolean,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void earlyClassChecking() {
/* 436 */     if (Trace.isOn) {
/* 437 */       Trace.entry("com.ibm.mq.jms.MQJMSAbstractIVT", "earlyClassChecking()");
/*     */     }
/*     */     
/*     */     try {
/* 441 */       Class.forName("javax.jms.Message");
/*     */     }
/* 443 */     catch (ClassNotFoundException e) {
/* 444 */       if (Trace.isOn) {
/* 445 */         Trace.catchBlock("com.ibm.mq.jms.MQJMSAbstractIVT", "earlyClassChecking()", e);
/*     */       }
/*     */       
/* 448 */       if (Trace.isOn) {
/* 449 */         Trace.traceData(CLASSNAME, "javax.jms.Message missing", null);
/* 450 */         Trace.traceData(CLASSNAME, "classpath check failed with " + e, null);
/* 451 */         Trace.exit(CLASSNAME, "main", null);
/*     */       } 
/*     */       
/* 454 */       System.err.println(NLSServices.getMessage("JMSMQ5037", new Object[] { "javax.jms.Message" }));
/*     */     } 
/*     */     
/* 457 */     if (Trace.isOn) {
/* 458 */       Trace.exit("com.ibm.mq.jms.MQJMSAbstractIVT", "earlyClassChecking()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void setupWatcherThread() {
/* 464 */     if (Trace.isOn) {
/* 465 */       Trace.entry("com.ibm.mq.jms.MQJMSAbstractIVT", "setupWatcherThread()");
/*     */     }
/* 467 */     Thread watcher = new Thread(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 471 */             if (Trace.isOn) {
/* 472 */               Trace.entry(this, "com.ibm.mq.jms.MQJMSAbstractIVT", "run()");
/*     */             }
/* 474 */             MQJMSAbstractIVT.printWriter.println("[watcher]started");
/* 475 */             MQJMSAbstractIVT.printWriter.flush();
/*     */             
/* 477 */             synchronized (MQJMSAbstractIVT.watcherLock) {
/*     */               try {
/* 479 */                 MQJMSAbstractIVT.watcherLock.wait(600000L);
/* 480 */                 MQJMSAbstractIVT.printWriter.println("[watcher]timeout/notififed");
/* 481 */                 MQJMSAbstractIVT.printWriter.flush();
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 486 */                 if (!MQJMSAbstractIVT.done) {
/* 487 */                   MQJMSAbstractIVT.printWriter.println("[watcher]write javacore");
/* 488 */                   MQJMSAbstractIVT.printWriter.flush();
/* 489 */                   MQJMSAbstractIVT.writeJavaCore();
/*     */                 }
/*     */               
/*     */               }
/* 493 */               catch (InterruptedException e) {
/* 494 */                 if (Trace.isOn) {
/* 495 */                   Trace.catchBlock(this, "com.ibm.mq.jms.null", "run()", e);
/*     */                 }
/* 497 */                 MQJMSAbstractIVT.printWriter.println("[watcher]interrupted");
/* 498 */                 MQJMSAbstractIVT.printWriter.flush();
/*     */               } 
/*     */             } 
/*     */             
/* 502 */             MQJMSAbstractIVT.printWriter.println("[watcher]ended");
/* 503 */             MQJMSAbstractIVT.printWriter.flush();
/*     */             
/* 505 */             if (Trace.isOn) {
/* 506 */               Trace.exit(this, "com.ibm.mq.jms.null", "run()");
/*     */             }
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 514 */     watcher.setDaemon(true);
/* 515 */     watcher.start();
/* 516 */     if (Trace.isOn) {
/* 517 */       Trace.exit("com.ibm.mq.jms.MQJMSAbstractIVT", "setupWatcherThread()");
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
/*     */   protected static void writeJavaCore() {
/* 529 */     if (Trace.isOn) {
/* 530 */       Trace.entry("com.ibm.mq.jms.MQJMSAbstractIVT", "writeJavaCore()");
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 535 */       Class<?> dumpClass = Class.forName("com.ibm.jvm.Dump");
/* 536 */       Method dumpMethod = dumpClass.getMethod("JavaDump", (Class[])null);
/* 537 */       dumpMethod.invoke(null, (Object[])null);
/*     */     }
/* 539 */     catch (Exception e1) {
/* 540 */       if (Trace.isOn) {
/* 541 */         Trace.catchBlock("com.ibm.mq.jms.MQJMSAbstractIVT", "writeJavaCore()", e1);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 548 */       simulateJavaCore();
/*     */     } 
/*     */     
/* 551 */     if (Trace.isOn) {
/* 552 */       Trace.exit("com.ibm.mq.jms.MQJMSAbstractIVT", "writeJavaCore()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void simulateJavaCore() {
/* 562 */     if (Trace.isOn) {
/* 563 */       Trace.entry("com.ibm.mq.jms.MQJMSAbstractIVT", "simulateJavaCore()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 568 */     String dateString = dateFormat.format(new Date());
/* 569 */     boolean unique = false;
/* 570 */     int counter = 1;
/* 571 */     String filename = "";
/* 572 */     while (!unique) {
/* 573 */       filename = "javacore." + dateString + String.format("%04d", new Object[] { Integer.valueOf(counter) }) + ".txt";
/* 574 */       unique = !(new File(filename)).exists();
/* 575 */       counter++;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 581 */     StringBuilder sb = new StringBuilder("This JVM does not support com.ibm.jvm.Dump.JavaDump() method");
/* 582 */     sb.append(endOfLineCharacter);
/* 583 */     sb.append("This file was generated by IBM MQ");
/* 584 */     sb.append(endOfLineCharacter);
/* 585 */     sb.append(endOfLineCharacter);
/*     */ 
/*     */     
/* 588 */     Map<Thread, StackTraceElement[]> threadToStackArrayMap = Thread.getAllStackTraces();
/* 589 */     for (Map.Entry<Thread, StackTraceElement[]> entry : threadToStackArrayMap.entrySet()) {
/*     */       
/* 591 */       Thread thread = entry.getKey();
/* 592 */       sb.append("\"" + thread.getName() + "\" ");
/* 593 */       sb.append("(id: " + thread.getId() + ", state: " + thread.getState() + ") ");
/* 594 */       sb.append("priority=" + thread.getPriority() + ", interrupted=" + thread.isInterrupted() + ", daemon=" + thread
/* 595 */           .isDaemon());
/* 596 */       sb.append(endOfLineCharacter);
/*     */ 
/*     */       
/* 599 */       for (StackTraceElement element : (StackTraceElement[])entry.getValue()) {
/* 600 */         sb.append("   at " + element.getClassName() + "." + element.getMethodName());
/* 601 */         if (element.isNativeMethod()) {
/* 602 */           sb.append("(Native Method)");
/*     */         } else {
/*     */           
/* 605 */           sb.append("(" + element.getFileName() + ":" + element.getLineNumber() + ")");
/*     */         } 
/* 607 */         sb.append(endOfLineCharacter);
/*     */       } 
/* 609 */       sb.append(endOfLineCharacter);
/*     */     } 
/* 611 */     sb.append("[EOF]");
/*     */ 
/*     */     
/* 614 */     File outputFile = new File(filename);
/* 615 */     FileOutputStream out = null;
/*     */     try {
/* 617 */       out = new FileOutputStream(outputFile);
/* 618 */       out.write(sb.toString().getBytes());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 623 */       System.err.println("JVMDUMP010I Java dump written to " + outputFile.getAbsolutePath());
/*     */     }
/* 625 */     catch (IOException e) {
/* 626 */       if (Trace.isOn) {
/* 627 */         Trace.catchBlock("com.ibm.mq.jms.MQJMSAbstractIVT", "simulateJavaCore()", e, 1);
/*     */       }
/* 629 */       e.printStackTrace(printWriter);
/* 630 */       printWriter.flush();
/*     */     } finally {
/*     */       
/* 633 */       if (Trace.isOn) {
/* 634 */         Trace.finallyBlock("com.ibm.mq.jms.MQJMSAbstractIVT", "simulateJavaCore()");
/*     */       }
/*     */       try {
/* 637 */         if (out != null) {
/* 638 */           out.close();
/*     */         }
/*     */       }
/* 641 */       catch (IOException e) {
/* 642 */         if (Trace.isOn) {
/* 643 */           Trace.catchBlock("com.ibm.mq.jms.MQJMSAbstractIVT", "simulateJavaCore()", e, 2);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 650 */     if (Trace.isOn) {
/* 651 */       Trace.exit("com.ibm.mq.jms.MQJMSAbstractIVT", "simulateJavaCore()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void displayPubSubUsage() {
/* 657 */     if (Trace.isOn) {
/* 658 */       Trace.entry("com.ibm.mq.jms.MQJMSAbstractIVT", "displayPubSubUsage()");
/*     */     }
/* 660 */     printWriter.println(NLSServices.getMessage("JMSMQ5069"));
/* 661 */     printWriter.println("    " + NLSServices.getMessage("JMSMQ5070"));
/* 662 */     printWriter.println("    " + NLSServices.getMessage("JMSMQ5071"));
/* 663 */     printWriter.println("    " + NLSServices.getMessage("JMSMQ5072"));
/* 664 */     printWriter.flush();
/* 665 */     if (Trace.isOn) {
/* 666 */       Trace.exit("com.ibm.mq.jms.MQJMSAbstractIVT", "displayPubSubUsage()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void displayVersion() {
/* 672 */     if (Trace.isOn) {
/* 673 */       Trace.entry("com.ibm.mq.jms.MQJMSAbstractIVT", "displayVersion()");
/*     */     }
/* 675 */     String pv = "7";
/* 676 */     Version.Component[] comp = Version.getComponents("MPI", "com.ibm.msg.client.wmq");
/*     */     
/* 678 */     if (comp.length > 0) {
/* 679 */       pv = comp[0].getVersionString() + " " + (String)comp[0].getImplementationInfo().get("CMVC");
/*     */     }
/* 681 */     printWriter.println();
/* 682 */     printWriter.println("Licensed Materials - Property of IBM 5724-I07 (c) Copyright IBM Corp. 2004, 2016 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.");
/* 683 */     printWriter.println(NLSServices.getMessage("JMSMQ1002", new Object[] { pv }));
/* 684 */     printWriter.flush();
/* 685 */     if (Trace.isOn) {
/* 686 */       Trace.exit("com.ibm.mq.jms.MQJMSAbstractIVT", "displayVersion()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void ivtFail() {
/* 692 */     if (Trace.isOn) {
/* 693 */       Trace.entry(this, "com.ibm.mq.jms.MQJMSAbstractIVT", "ivtFail()");
/*     */     }
/* 695 */     printWriter.println(NLSServices.getMessage("JMSMQ5095"));
/* 696 */     printWriter.flush();
/* 697 */     System.exit(-1);
/* 698 */     if (Trace.isOn)
/* 699 */       Trace.exit(this, "com.ibm.mq.jms.MQJMSAbstractIVT", "ivtFail()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQJMSAbstractIVT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */