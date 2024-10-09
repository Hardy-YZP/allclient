/*     */ package com.ibm.msg.client.commonservices.j2se.log;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.provider.log.CSPLog;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.security.AccessControlException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.logging.FileHandler;
/*     */ import java.util.logging.Formatter;
/*     */ import java.util.logging.Handler;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogManager;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.logging.StreamHandler;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultLogger
/*     */   implements CSPLog
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/log/DefaultLogger.java";
/*     */   private static final String outputFileNameProperty = "com.ibm.msg.client.commonservices.log.outputName";
/*     */   private static final String outputFileNameProperty_default = "mqjms.log";
/*     */   private static final String outputFileNameProperty_errorStreamFileNameRegex = "^(system\\.|std)err$";
/*     */   private static final String outputFileNameProperty_outputStreamFileNameRegex = "^(system\\.|std)out$";
/*     */   private static final String maxLogBytesProperty = "com.ibm.msg.client.commonservices.log.maxBytes";
/*     */   private static final int maxLogBytesProperty_default = -1;
/*     */   private static final String logFileLimitProperty = "com.ibm.msg.client.commonservices.log.limit";
/*     */   private static final int logFileLimitProperty_default = 262144;
/*     */   private static final String logFileCountProperty = "com.ibm.msg.client.commonservices.log.count";
/*     */   private static final int logFileCountProperty_default = 3;
/*     */   private static final String logHandlerNameProperty = "com.ibm.msg.client.commonservices.log.LogHandler";
/*     */   private static final String logHandlerNameProperty_default = "";
/*     */   private static final String logFormatterNameProperty = "com.ibm.msg.client.commonservices.log.LogFormatter";
/*     */   static final String LOG_MSG_NLS = "LOG_MSG_NLS";
/*     */   static final String LOG_MSG = "LOG_MSG";
/*     */   
/*     */   static {
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.data("com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/log/DefaultLogger.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   private static int maxLogBytes = -1;
/*     */   
/*     */   private LogManager logManager;
/*     */   private Logger logger;
/*     */   
/*     */   public static int getMaxLogBytes() {
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.data("com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "getMaxLogBytes()", "getter", 
/* 147 */           Integer.valueOf(maxLogBytes));
/*     */     }
/* 149 */     return maxLogBytes;
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
/* 168 */   private String loggerName = "com.ibm.msg.client.commonservices.j2se.DefaultLogger";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 173 */   private String outputFileName = "mqjms.log";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   private int logFileLimit = 0;
/*     */ 
/*     */ 
/*     */   
/* 182 */   private int logFileCount = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   private Formatter formatter = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultLogger() {
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "<init>()");
/*     */     }
/*     */ 
/*     */     
/* 201 */     this.logManager = LogManager.getLogManager();
/*     */ 
/*     */     
/* 204 */     this.logger = Logger.getLogger(this.loggerName);
/*     */ 
/*     */     
/* 207 */     AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run()
/*     */           {
/* 211 */             if (Trace.isOn) {
/* 212 */               Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "run()");
/*     */             }
/*     */             try {
/* 215 */               DefaultLogger.this.logger.setLevel(Level.ALL);
/*     */             }
/* 217 */             catch (AccessControlException ace) {
/* 218 */               if (Trace.isOn) {
/* 219 */                 Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.log.null", "run()", ace);
/*     */               }
/*     */               
/* 222 */               System.err.println("AccessControlException while enabling message client logging. No log output will be produced");
/*     */             } 
/* 224 */             if (Trace.isOn) {
/* 225 */               Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.null", "run()", null);
/*     */             }
/* 227 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 232 */     getTracerOptionsFromProperties();
/*     */ 
/*     */ 
/*     */     
/* 236 */     AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run()
/*     */           {
/* 240 */             if (Trace.isOn) {
/* 241 */               Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "run()");
/*     */             }
/*     */             try {
/* 244 */               DefaultLogger.this.logger.setUseParentHandlers(false);
/*     */             }
/* 246 */             catch (AccessControlException ace) {
/* 247 */               if (Trace.isOn) {
/* 248 */                 Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.log.null", "run()", ace);
/*     */               }
/*     */             } 
/*     */ 
/*     */             
/* 253 */             if (Trace.isOn) {
/* 254 */               Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.null", "run()", null);
/*     */             }
/* 256 */             return null;
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "close()");
/*     */     }
/*     */     try {
/* 281 */       for (Handler handler : this.logger.getHandlers()) {
/* 282 */         this.logger.removeHandler(handler);
/* 283 */         handler.close();
/*     */       } 
/* 285 */       this.logManager.reset();
/* 286 */       if (Trace.isOn) {
/* 287 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "close()", 1);
/*     */       }
/*     */ 
/*     */       
/*     */       return;
/* 292 */     } catch (Throwable t) {
/* 293 */       if (Trace.isOn) {
/* 294 */         Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "close()", t);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 299 */       if (Trace.isOn) {
/* 300 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "close()", 2);
/*     */       }
/*     */       return;
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
/*     */   protected void getTracerOptionsFromProperties() {
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "getTracerOptionsFromProperties()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 324 */     PropertyStore.register("com.ibm.msg.client.commonservices.log.outputName", "mqjms.log");
/*     */ 
/*     */     
/* 327 */     PropertyStore.register("com.ibm.msg.client.commonservices.log.maxBytes", -1L, Long.valueOf(-1L), Long.valueOf(2147483647L));
/* 328 */     PropertyStore.register("com.ibm.msg.client.commonservices.log.limit", 262144L, Long.valueOf(0L), Long.valueOf(2147483647L));
/* 329 */     PropertyStore.register("com.ibm.msg.client.commonservices.log.count", 3L, Long.valueOf(0L), Long.valueOf(2147483647L));
/* 330 */     PropertyStore.register("com.ibm.msg.client.commonservices.log.LogFormatter", null);
/* 331 */     PropertyStore.register("com.ibm.msg.client.commonservices.log.LogHandler", "");
/*     */     
/* 333 */     this.outputFileName = PropertyStore.getStringProperty("com.ibm.msg.client.commonservices.log.outputName");
/* 334 */     maxLogBytes = (int)PropertyStore.getLongProperty("com.ibm.msg.client.commonservices.log.maxBytes");
/* 335 */     this.logFileLimit = (int)PropertyStore.getLongProperty("com.ibm.msg.client.commonservices.log.limit");
/* 336 */     this.logFileCount = (int)PropertyStore.getLongProperty("com.ibm.msg.client.commonservices.log.count");
/*     */     
/* 338 */     if (Trace.isOn) {
/* 339 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "getTracerOptionsFromProperties()");
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
/*     */   public void initialize() {
/* 353 */     if (Trace.isOn) {
/* 354 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "initialize()");
/*     */     }
/*     */ 
/*     */     
/* 358 */     Set<Handler> handlerList = new HashSet<>();
/*     */     
/* 360 */     if (this.outputFileName == null) {
/* 361 */       this.outputFileName = "mqjms.log";
/*     */     }
/*     */     
/* 364 */     processOutputFileList(handlerList);
/*     */     
/* 366 */     if (null == this.formatter) {
/* 367 */       this.formatter = new DefaultFormatter();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 372 */     if (handlerList.size() > 0) {
/* 373 */       for (Handler handler : handlerList) {
/* 374 */         handler.setFormatter(this.formatter);
/*     */       }
/*     */     }
/*     */     
/* 378 */     setLogHandlers(handlerList);
/*     */     
/* 380 */     if (Trace.isOn) {
/* 381 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "initialize()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void processOutputFileList(Set<Handler> handlerList) {
/* 388 */     if (Trace.isOn) {
/* 389 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "processOutputFileList(Set<Handler>)", new Object[] { handlerList });
/*     */     }
/*     */     
/* 392 */     String[] ofNames = this.outputFileName.split(",");
/* 393 */     for (String ofName : ofNames) {
/* 394 */       if (ofName != null)
/*     */       {
/*     */         
/* 397 */         if (ofName.trim().length() != 0)
/*     */         {
/*     */           
/* 400 */           if (Pattern.matches("^(system\\.|std)err$", ofName.toLowerCase())) {
/* 401 */             if (Trace.isOn) {
/* 402 */               Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger.initialize", "Writing to System.err - Prop overridden: " + 
/* 403 */                   PropertyStore.wasOverridden("com.ibm.msg.client.commonservices.log.outputName", null) + " ofName: " + ofName);
/*     */             }
/*     */             
/* 406 */             handlerList.add(new LoggerStreamHandler(System.err));
/* 407 */           } else if (Pattern.matches("^(system\\.|std)out$", ofName.toLowerCase())) {
/*     */             
/* 409 */             if (Trace.isOn) {
/* 410 */               Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger.initialize", "Writing to System.out - Prop overridden: " + 
/* 411 */                   PropertyStore.wasOverridden("com.ibm.msg.client.commonservices.log.outputName", null) + " ofName: " + ofName);
/*     */             }
/*     */             
/* 414 */             handlerList.add(new LoggerStreamHandler(System.out));
/*     */           } else {
/* 416 */             if (Trace.isOn) {
/* 417 */               Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger.initialize", "NOT writing to System.err - Prop overridden: " + 
/* 418 */                   PropertyStore.wasOverridden("com.ibm.msg.client.commonservices.log.outputName", null) + " ofName: " + ofName);
/*     */             }
/*     */             
/*     */             try {
/* 422 */               final File traceFile = new File(ofName);
/* 423 */               Object traceFileName = AccessController.doPrivileged(new PrivilegedAction()
/*     */                   {
/*     */                     public Object run()
/*     */                     {
/* 427 */                       if (Trace.isOn) {
/* 428 */                         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "run()");
/*     */                       }
/*     */ 
/*     */                       
/* 432 */                       if (traceFile.isDirectory()) {
/*     */                         
/*     */                         try {
/* 435 */                           Object traceRet1 = traceFile.getCanonicalPath() + File.separator + "mqjms.log";
/* 436 */                           if (Trace.isOn) {
/* 437 */                             Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.null", "run()", traceRet1, 1);
/*     */                           }
/*     */                           
/* 440 */                           return traceRet1;
/*     */                         }
/* 442 */                         catch (IOException ioe) {
/* 443 */                           if (Trace.isOn) {
/* 444 */                             Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.log.null", "run()", ioe);
/*     */                           }
/*     */                           
/* 447 */                           if (Trace.isOn) {
/* 448 */                             Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.null", "run()", ioe, 2);
/*     */                           }
/*     */                           
/* 451 */                           return ioe;
/*     */                         } 
/*     */                       }
/* 454 */                       if (Trace.isOn) {
/* 455 */                         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.null", "run()", ofName, 3);
/*     */                       }
/*     */                       
/* 458 */                       return ofName;
/*     */                     }
/*     */                   });
/* 461 */               if (traceFileName instanceof IOException) {
/* 462 */                 IOException traceRet2 = (IOException)traceFileName;
/* 463 */                 if (Trace.isOn) {
/* 464 */                   Trace.throwing(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "processOutputFileList(Set<Handler>)", traceRet2);
/*     */                 }
/*     */                 
/* 467 */                 throw traceRet2;
/*     */               } 
/* 469 */               String traceFileName1 = (String)traceFileName;
/* 470 */               if (Trace.isOn) {
/* 471 */                 Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger.initialize()", "Creating a file handler for the log - " + traceFileName1);
/*     */               }
/* 473 */               handlerList.add(new FileHandler(traceFileName1, this.logFileLimit, this.logFileCount, true));
/*     */             }
/* 475 */             catch (IOException ioe) {
/* 476 */               if (Trace.isOn) {
/* 477 */                 Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "processOutputFileList(Set<Handler>)", ioe, 1);
/*     */               }
/*     */               
/* 480 */               System.err.println("Could not initialize log file, " + ioe);
/* 481 */               if (Trace.isOn) {
/* 482 */                 Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger.initialize()", "Creating a stderr stream handler for the log");
/*     */               }
/* 484 */               handlerList.add(new LoggerStreamHandler(System.err));
/*     */             }
/* 486 */             catch (AccessControlException ace) {
/* 487 */               if (Trace.isOn) {
/* 488 */                 Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "processOutputFileList(Set<Handler>)", ace, 2);
/*     */               }
/*     */               
/* 491 */               System.err.println("Insufficient permissions for log file, " + ace);
/* 492 */               if (Trace.isOn) {
/* 493 */                 Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger.initialize()", "Creating a stderr stream handler for the log");
/*     */               }
/* 495 */               handlerList.add(new LoggerStreamHandler(System.err));
/*     */             } 
/*     */           }  } 
/*     */       }
/*     */     } 
/* 500 */     if (Trace.isOn) {
/* 501 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "processOutputFileList(Set<Handler>)");
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
/*     */   public void log(Object parentClass, String parentClassName, String methodSignature, String key, HashMap inserts) {
/* 516 */     if (Trace.isOn) {
/* 517 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "log(Object,String,String,String,HashMap)", new Object[] { parentClass, parentClassName, methodSignature, key, inserts });
/*     */     }
/*     */ 
/*     */     
/* 521 */     this.logger.logp(Level.INFO, objectToString(parentClass, parentClassName), methodSignature, key, inserts);
/* 522 */     if (Trace.isOn) {
/* 523 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "log(Object,String,String,String,HashMap)");
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
/*     */   public void logNLS(Object parentClass, String parentClassName, String methodSignature, String NLSMessage) {
/* 537 */     if (Trace.isOn) {
/* 538 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "logNLS(Object,String,String,String)", new Object[] { parentClass, parentClassName, methodSignature, NLSMessage });
/*     */     }
/*     */ 
/*     */     
/* 542 */     this.logger.logp(Level.INFO, objectToString(parentClass, parentClassName), methodSignature, "LOG_MSG_NLS", NLSMessage);
/*     */     
/* 544 */     if (Trace.isOn) {
/* 545 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "logNLS(Object,String,String,String)");
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
/*     */   private String objectToString(Object obj, String parentClassName) {
/* 567 */     if (Trace.isOn) {
/* 568 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "objectToString(Object,String)", new Object[] { obj, parentClassName });
/*     */     }
/*     */ 
/*     */     
/* 572 */     if (obj == null) {
/* 573 */       if (Trace.isOn) {
/* 574 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "objectToString(Object,String)", parentClassName, 1);
/*     */       }
/*     */       
/* 577 */       return parentClassName;
/*     */     } 
/*     */     
/* 580 */     String objectClassName = obj.getClass().getName();
/*     */     
/* 582 */     StringBuffer result = new StringBuffer(objectClassName);
/*     */     
/* 584 */     if (null != parentClassName && !result.toString().equals(parentClassName) && objectClassName.indexOf('$') == -1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 591 */       int pIndex = parentClassName.lastIndexOf(".");
/* 592 */       result.append("(");
/* 593 */       result.append(parentClassName.substring(pIndex + 1));
/* 594 */       result.append(")");
/*     */     } 
/*     */     
/* 597 */     String traceRet1 = result.toString();
/* 598 */     if (Trace.isOn) {
/* 599 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "objectToString(Object,String)", traceRet1, 2);
/*     */     }
/*     */     
/* 602 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setLogHandlers(final Set<Handler> newHandlers) {
/* 612 */     if (Trace.isOn) {
/* 613 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "setLogHandlers(final Set<Handler>)", new Object[] { newHandlers });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 620 */     AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run()
/*     */           {
/* 624 */             if (Trace.isOn) {
/* 625 */               Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "run()");
/*     */             }
/*     */             
/* 628 */             List<Handler> handlersToRemove = new ArrayList<>();
/*     */             
/* 630 */             Handler[] oldHandlers = DefaultLogger.this.logger.getHandlers();
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 635 */             for (Handler oldHandler : oldHandlers) {
/* 636 */               if (newHandlers.contains(oldHandler)) {
/*     */                 
/* 638 */                 newHandlers.remove(oldHandler);
/*     */               }
/*     */               else {
/*     */                 
/* 642 */                 handlersToRemove.add(oldHandler);
/*     */               } 
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/* 648 */             for (Handler newHandler : newHandlers) {
/* 649 */               DefaultLogger.this.logger.addHandler(newHandler);
/*     */             }
/*     */             
/* 652 */             for (Handler handler : handlersToRemove) {
/* 653 */               DefaultLogger.this.logger.removeHandler(handler);
/*     */             }
/*     */             
/* 656 */             if (Trace.isOn) {
/* 657 */               Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.null", "run()", null);
/*     */             }
/* 659 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 663 */     if (Trace.isOn) {
/* 664 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.DefaultLogger", "setLogHandlers(final Set<Handler>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class LoggerStreamHandler
/*     */     extends StreamHandler
/*     */   {
/*     */     public LoggerStreamHandler(OutputStream out) {
/* 674 */       if (Trace.isOn) {
/* 675 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.LoggerStreamHandler", "<init>(OutputStream)", new Object[] { out });
/*     */       }
/*     */ 
/*     */       
/* 679 */       setOutputStream(out);
/*     */       
/* 681 */       if (Trace.isOn) {
/* 682 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.LoggerStreamHandler", "<init>(OutputStream)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void close() {
/* 692 */       if (Trace.isOn) {
/* 693 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.LoggerStreamHandler", "close()");
/*     */       }
/*     */ 
/*     */       
/* 697 */       flush();
/*     */       
/* 699 */       if (Trace.isOn) {
/* 700 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.LoggerStreamHandler", "close()");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void publish(LogRecord record) {
/* 709 */       if (Trace.isOn) {
/* 710 */         Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.LoggerStreamHandler", "publish(LogRecord)", new Object[] { record });
/*     */       }
/*     */ 
/*     */       
/* 714 */       super.publish(record);
/* 715 */       flush();
/*     */       
/* 717 */       if (Trace.isOn)
/* 718 */         Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.LoggerStreamHandler", "publish(LogRecord)"); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\log\DefaultLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */