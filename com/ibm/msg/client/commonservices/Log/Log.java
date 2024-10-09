/*     */ package com.ibm.msg.client.commonservices.Log;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.CSIException;
/*     */ import com.ibm.msg.client.commonservices.CSIListener;
/*     */ import com.ibm.msg.client.commonservices.CommonServices;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.provider.log.CSPLog;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Log
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/Log/Log.java";
/*     */   public static final String logStatusProperty = "com.ibm.msg.client.commonservices.log.status";
/*     */   public static final String logStatusProperty_ON = "ON";
/*     */   public static final String logStatusProperty_OFF = "OFF";
/*     */   public static final String logStatusProperty_default = "ON";
/*     */   public static final String startupLogProperty = "com.ibm.msg.client.commonservices.Log.startup";
/*     */   public static final String jeeRebalanceStatusProperty = "com.ibm.msg.client.commonservices.log.jee.status";
/*     */   public static final String jeeRebalanceStatusProperty_default = "OFF";
/*     */   public static final String outputFileNameProperty = "com.ibm.msg.client.commonservices.log.outputName";
/*     */   
/*     */   static {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.data("com.ibm.msg.client.commonservices.Log.Log", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/Log/Log.java");
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
/*     */   private static boolean isOn = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean jeeOn = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   private static CSPLog currentLogger = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   private static CSPLog functionalLogger = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   private static NullLog nullLogger = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   private static StartupLog startupLogger = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   private static Map<Thread, Boolean> currentlylogging = null;
/*     */ 
/*     */   
/*     */   static boolean startuplogging = false;
/*     */   
/*     */   private static boolean initialized = false;
/*     */   
/*     */   private static boolean listening = false;
/*     */   
/* 150 */   private static String lineSeparatorProperty = "line.separator";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   private static String lineSeparator = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 161 */   private static int titleSize = 30;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   private static LogHandler LogHandler = null;
/*     */   
/*     */   static {
/* 183 */     if (Trace.isOn)
/* 184 */       Trace.entry("com.ibm.msg.client.commonservices.Log.Log", "static()"); 
/*     */   }
/*     */   
/* 187 */   private static Object LogLock = new Object();
/*     */ 
/*     */   
/*     */   static {
/* 191 */     nullLogger = new NullLog();
/* 192 */     startupLogger = new StartupLog();
/* 193 */     functionalLogger = nullLogger;
/* 194 */     currentLogger = nullLogger;
/*     */ 
/*     */     
/* 197 */     currentlylogging = new ConcurrentHashMap<>();
/*     */ 
/*     */     
/* 200 */     PropertyStore.register(lineSeparatorProperty, "\n");
/*     */ 
/*     */     
/* 203 */     lineSeparator = PropertyStore.getStringProperty(lineSeparatorProperty);
/*     */     
/*     */     try {
/* 206 */       initialize();
/*     */     }
/* 208 */     catch (CSIException csie) {
/* 209 */       if (Trace.isOn) {
/* 210 */         Trace.catchBlock("com.ibm.msg.client.commonservices.Log.Log", "static()", (Throwable)csie);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Log() {
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.entry(this, "com.ibm.msg.client.commonservices.Log.Log", "<init>()");
/*     */     }
/*     */     
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.exit(this, "com.ibm.msg.client.commonservices.Log.Log", "<init>()");
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
/*     */   public static void setOn(boolean LogOn) {
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.data("com.ibm.msg.client.commonservices.Log.Log", "setOn(boolean)", "setter", 
/* 248 */           Boolean.valueOf(LogOn));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 253 */     synchronized (LogLock) {
/* 254 */       if (isOn != LogOn) {
/* 255 */         isOn = LogOn;
/*     */         
/* 257 */         if (LogOn) {
/* 258 */           currentLogger = functionalLogger;
/* 259 */           currentLogger.initialize();
/*     */         } else {
/*     */           
/* 262 */           currentLogger = nullLogger;
/*     */         } 
/*     */ 
/*     */         
/* 266 */         if (null != LogHandler) {
/* 267 */           LogHandler.setOn(isOn);
/*     */         }
/*     */       }
/* 270 */       else if (LogOn) {
/*     */ 
/*     */         
/* 273 */         if (currentLogger != functionalLogger) {
/* 274 */           isOn = false;
/* 275 */           currentLogger = functionalLogger;
/* 276 */           currentLogger.initialize();
/* 277 */           isOn = true;
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
/*     */   public static boolean getOn() {
/* 289 */     boolean retVal = isOn;
/*     */     
/* 291 */     if (Trace.isOn) {
/* 292 */       Trace.data("com.ibm.msg.client.commonservices.Log.Log", "getOn()", "getter", 
/* 293 */           Boolean.valueOf(retVal));
/*     */     }
/* 295 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerLogHandler(LogHandler handler) {
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.entry("com.ibm.msg.client.commonservices.Log.Log", "registerLogHandler(LogHandler)", new Object[] { handler });
/*     */     }
/*     */     
/* 309 */     LogHandler = handler;
/* 310 */     handler.setOn(isOn);
/* 311 */     if (Trace.isOn) {
/* 312 */       Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "registerLogHandler(LogHandler)");
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
/*     */   public static void setCSPLog(CSPLog newLogr) {
/* 324 */     if (Trace.isOn) {
/* 325 */       Trace.data("com.ibm.msg.client.commonservices.Log.Log", "setCSPLog(CSPLog)", "setter", newLogr);
/*     */     }
/*     */     
/* 328 */     if (null == newLogr) {
/* 329 */       functionalLogger = nullLogger;
/*     */     } else {
/*     */       
/* 332 */       functionalLogger = newLogr;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 337 */     setOn(isOn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CSPLog getCSPLog() {
/* 347 */     if (Trace.isOn) {
/* 348 */       Trace.entry("com.ibm.msg.client.commonservices.Log.Log", "getCSPLog()");
/*     */     }
/* 350 */     if (null == functionalLogger || functionalLogger instanceof NullLog) {
/* 351 */       if (Trace.isOn) {
/* 352 */         Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "getCSPLog()", null, 1);
/*     */       }
/* 354 */       return null;
/*     */     } 
/*     */     
/* 357 */     if (Trace.isOn) {
/* 358 */       Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "getCSPLog()", functionalLogger, 2);
/*     */     }
/* 360 */     return functionalLogger;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean logable() {
/* 371 */     if (Trace.isOn) {
/* 372 */       Trace.entry("com.ibm.msg.client.commonservices.Log.Log", "logable()");
/*     */     }
/* 374 */     Thread currentThread = Thread.currentThread();
/*     */     
/* 376 */     Boolean alreadylogging = currentlylogging.get(currentThread);
/* 377 */     if (null == alreadylogging || !alreadylogging.booleanValue()) {
/*     */ 
/*     */       
/* 380 */       currentlylogging.put(currentThread, Boolean.TRUE);
/*     */       
/* 382 */       if (Trace.isOn) {
/* 383 */         Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "logable()", Boolean.valueOf(true), 1);
/*     */       }
/* 385 */       return true;
/*     */     } 
/*     */     
/* 388 */     if (Trace.isOn) {
/* 389 */       Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "logable()", Boolean.valueOf(false), 2);
/*     */     }
/*     */     
/* 392 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void endLogging() {
/* 402 */     if (Trace.isOn) {
/* 403 */       Trace.entry("com.ibm.msg.client.commonservices.Log.Log", "endLogging()");
/*     */     }
/* 405 */     Thread currentThread = Thread.currentThread();
/*     */ 
/*     */ 
/*     */     
/* 409 */     Boolean alreadylogging = currentlylogging.get(currentThread);
/* 410 */     if (null == alreadylogging || !alreadylogging.booleanValue()) {
/*     */       
/* 412 */       if (Trace.isOn) {
/* 413 */         Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "endLogging()", 1);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 418 */     currentlylogging.remove(currentThread);
/*     */     
/* 420 */     if (Trace.isOn) {
/* 421 */       Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "endLogging()", 2);
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
/*     */   public static void log(Object parentClass, String parentClassName, String methodSignature, String key, HashMap<String, ? extends Object> inserts) {
/* 440 */     if (Trace.isOn) {
/* 441 */       Trace.entry("com.ibm.msg.client.commonservices.Log.Log", "log(Object,String,String,String,HashMap<String , ? extends Object>)", new Object[] { parentClass, parentClassName, methodSignature, key, inserts });
/*     */     }
/*     */ 
/*     */     
/* 445 */     logInternal(parentClass, parentClassName, methodSignature, key, inserts);
/*     */     
/* 447 */     if (Trace.isOn) {
/* 448 */       Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "log(Object,String,String,String,HashMap<String , ? extends Object>)");
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
/*     */   public static void log(Object parentClass, String methodSignature, String key, HashMap<String, ? extends Object> inserts) {
/* 465 */     if (Trace.isOn) {
/* 466 */       Trace.entry("com.ibm.msg.client.commonservices.Log.Log", "log(Object,String,String,HashMap<String , ? extends Object>)", new Object[] { parentClass, methodSignature, key, inserts });
/*     */     }
/*     */ 
/*     */     
/* 470 */     logInternal(parentClass, null, methodSignature, key, inserts);
/*     */     
/* 472 */     if (Trace.isOn) {
/* 473 */       Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "log(Object,String,String,HashMap<String , ? extends Object>)");
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
/*     */   public static void log(String parentClassName, String methodSignature, String key, HashMap<String, ? extends Object> inserts) {
/* 488 */     if (Trace.isOn) {
/* 489 */       Trace.entry("com.ibm.msg.client.commonservices.Log.Log", "log(String,String,String,HashMap<String , ? extends Object>)", new Object[] { parentClassName, methodSignature, key, inserts });
/*     */     }
/*     */ 
/*     */     
/* 493 */     logInternal(null, parentClassName, methodSignature, key, inserts);
/*     */     
/* 495 */     if (Trace.isOn) {
/* 496 */       Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "log(String,String,String,HashMap<String , ? extends Object>)");
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
/*     */   private static void logInternal(Object parentClass, String parentClassName, String methodSignature, String key, HashMap<String, ? extends Object> inserts) {
/* 516 */     if (Trace.isOn) {
/* 517 */       Trace.entry("com.ibm.msg.client.commonservices.Log.Log", "logInternal(Object,String,String,String,HashMap<String , ? extends Object>)", new Object[] { parentClass, parentClassName, methodSignature, key, inserts });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 522 */     if (isOn && logable()) {
/*     */       
/* 524 */       HashMap<String, Object> newInserts = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 530 */       if (inserts != null) {
/*     */         
/* 532 */         newInserts = new HashMap<>(inserts.size());
/*     */         
/* 534 */         Set<? extends Map.Entry<String, ? extends Object>> entries = inserts.entrySet();
/*     */         
/* 536 */         for (Map.Entry<String, ? extends Object> entry : entries) {
/* 537 */           String itemKey = entry.getKey();
/* 538 */           Object itemValue = entry.getValue();
/* 539 */           if (itemValue instanceof Exception) {
/* 540 */             itemValue = flattenExceptionToString((Exception)itemValue);
/*     */           }
/* 542 */           newInserts.put(itemKey, itemValue);
/*     */         } 
/*     */       } 
/*     */       
/*     */       try {
/* 547 */         currentLogger.log(parentClass, parentClassName, methodSignature, key, newInserts);
/*     */       }
/* 549 */       catch (Throwable t) {
/* 550 */         if (Trace.isOn) {
/* 551 */           Trace.catchBlock("com.ibm.msg.client.commonservices.Log.Log", "logInternal(Object,String,String,String,HashMap<String , ? extends Object>)", t, 1);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 558 */           System.err.println("Failed to write to log file. Attempting StartupLogger");
/* 559 */           startupLogger.log(parentClass, parentClassName, methodSignature, key, newInserts);
/*     */         }
/* 561 */         catch (Throwable t1) {
/* 562 */           if (Trace.isOn) {
/* 563 */             Trace.catchBlock("com.ibm.msg.client.commonservices.Log.Log", "logInternal(Object,String,String,String,HashMap<String , ? extends Object>)", t1, 2);
/*     */           }
/*     */ 
/*     */           
/* 567 */           System.err.println("Failed to use StartupLogger. Writing directly");
/* 568 */           System.err.println(t1.getMessage());
/* 569 */           t1.printStackTrace();
/* 570 */           System.err.println();
/* 571 */           System.err.println(t.getMessage());
/* 572 */           t.printStackTrace();
/* 573 */           System.err.println();
/* 574 */           System.err.println(parentClassName + ": " + methodSignature);
/* 575 */           System.err.println(key);
/* 576 */           if (newInserts != null) {
/* 577 */             System.err.println(newInserts.toString());
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 582 */         endLogging();
/*     */         
/* 584 */         HashMap<String, Throwable> data = new HashMap<>();
/* 585 */         data.put("exception", t);
/* 586 */         Trace.ffst("com.ibm.msg.client.commonservices.Log.Log", "LogDataInternal(Object, String, String, String, HashMap)", "XC006001", data, null);
/*     */       } finally {
/*     */         
/* 589 */         if (Trace.isOn) {
/* 590 */           Trace.finallyBlock("com.ibm.msg.client.commonservices.Log.Log", "logInternal(Object,String,String,String,HashMap<String , ? extends Object>)");
/*     */         }
/*     */ 
/*     */         
/* 594 */         endLogging();
/*     */       } 
/*     */     } 
/*     */     
/* 598 */     if (Trace.isOn) {
/* 599 */       Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "logInternal(Object,String,String,String,HashMap<String , ? extends Object>)");
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
/*     */   private static void logNLSInternal(Object parentClass, String parentClassName, String methodSignature, String NLSMessage) {
/* 617 */     if (Trace.isOn) {
/* 618 */       Trace.entry("com.ibm.msg.client.commonservices.Log.Log", "logNLSInternal(Object,String,String,String)", new Object[] { parentClass, parentClassName, methodSignature, NLSMessage });
/*     */     }
/*     */ 
/*     */     
/* 622 */     if (isOn && logable()) {
/*     */       try {
/* 624 */         currentLogger.logNLS(parentClass, parentClassName, methodSignature, NLSMessage);
/*     */       }
/* 626 */       catch (Throwable t) {
/* 627 */         if (Trace.isOn) {
/* 628 */           Trace.catchBlock("com.ibm.msg.client.commonservices.Log.Log", "logNLSInternal(Object,String,String,String)", t);
/*     */         }
/*     */ 
/*     */         
/* 632 */         endLogging();
/*     */         
/* 634 */         HashMap<String, Throwable> data = new HashMap<>();
/* 635 */         data.put("exception", t);
/* 636 */         Trace.ffst("com.ibm.msg.client.commonservices.Log.Log", "LogNLSInternal(Object, String, String, String)", "XC006002", data, null);
/*     */       } finally {
/*     */         
/* 639 */         if (Trace.isOn) {
/* 640 */           Trace.finallyBlock("com.ibm.msg.client.commonservices.Log.Log", "logNLSInternal(Object,String,String,String)");
/*     */         }
/*     */ 
/*     */         
/* 644 */         endLogging();
/*     */       } 
/*     */     }
/*     */     
/* 648 */     if (Trace.isOn) {
/* 649 */       Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "logNLSInternal(Object,String,String,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initialize() throws CSIException {
/* 659 */     if (Trace.isOn) {
/* 660 */       Trace.entry("com.ibm.msg.client.commonservices.Log.Log", "initialize()");
/*     */     }
/*     */ 
/*     */     
/* 664 */     if (!initialized) {
/*     */       
/*     */       try {
/* 667 */         functionalLogger = CommonServices.getLog();
/* 668 */         initialized = true;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 673 */         PropertyStore.register("com.ibm.msg.client.commonservices.log.status", "ON");
/* 674 */         String LogOn = PropertyStore.getStringProperty("com.ibm.msg.client.commonservices.log.status");
/* 675 */         if (null != LogOn && LogOn.equalsIgnoreCase("ON")) {
/* 676 */           setOn(true);
/*     */         } else {
/*     */           
/* 679 */           setOn(false);
/*     */         } 
/*     */         
/* 682 */         PropertyStore.register("com.ibm.msg.client.commonservices.log.jee.status", "OFF");
/* 683 */         String jeeStatus = PropertyStore.getStringProperty("com.ibm.msg.client.commonservices.log.jee.status");
/* 684 */         jeeOn = (null != jeeStatus && jeeStatus.equalsIgnoreCase("ON"));
/*     */       }
/* 686 */       catch (CSIException csie) {
/* 687 */         if (Trace.isOn) {
/* 688 */           Trace.catchBlock("com.ibm.msg.client.commonservices.Log.Log", "initialize()", (Throwable)csie);
/*     */         }
/*     */         
/* 691 */         if (!listening) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 696 */           if (startuplogging) {
/* 697 */             functionalLogger = startupLogger;
/*     */           } else {
/*     */             
/* 700 */             functionalLogger = new NullLog();
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 705 */           CSIListener listener = new CSIListener()
/*     */             {
/*     */               public void onCSIInitialize()
/*     */               {
/* 709 */                 if (Trace.isOn) {
/* 710 */                   Trace.entry(this, "com.ibm.msg.client.commonservices.Log.Log", "onCSIInitialize()");
/*     */                 }
/*     */                 try {
/* 713 */                   Log.initialize();
/* 714 */                   Log.listening = false;
/* 715 */                   CommonServices.removeCSIListener(this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 721 */                   PropertyStore.register("com.ibm.msg.client.commonservices.Log.status", "OFF");
/* 722 */                   String LogOn = PropertyStore.getStringProperty("com.ibm.msg.client.commonservices.Log.status");
/* 723 */                   if (null != LogOn && LogOn.equalsIgnoreCase("ON")) {
/* 724 */                     Log.setOn(true);
/*     */                   } else {
/*     */                     
/* 727 */                     Log.setOn(false);
/*     */                   }
/*     */                 
/*     */                 }
/* 731 */                 catch (CSIException csie2) {
/* 732 */                   if (Trace.isOn) {
/* 733 */                     Trace.catchBlock(this, "com.ibm.msg.client.commonservices.Log.null", "onCSIInitialize()", (Throwable)csie2);
/*     */                   }
/*     */ 
/*     */                   
/* 737 */                   HashMap<String, CSIException> hash = new HashMap<>();
/* 738 */                   hash.put("Exception", csie2);
/* 739 */                   Trace.ffst(this, "onCSIInitialize", "Failed to initialize CSI from Log listener", hash, null);
/*     */                 } 
/* 741 */                 if (Trace.isOn) {
/* 742 */                   Trace.exit(this, "com.ibm.msg.client.commonservices.Log.null", "onCSIInitialize()");
/*     */                 }
/*     */               }
/*     */             };
/*     */ 
/*     */           
/* 748 */           CommonServices.addCSIListener(listener);
/* 749 */           listening = true;
/*     */         } 
/* 751 */         if (Trace.isOn) {
/* 752 */           Trace.throwing("com.ibm.msg.client.commonservices.Log.Log", "initialize()", (Throwable)csie);
/*     */         }
/* 754 */         throw csie;
/*     */       } 
/*     */     }
/* 757 */     if (Trace.isOn) {
/* 758 */       Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "initialize()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface LogHandler
/*     */   {
/*     */     void setOn(boolean param1Boolean);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logNLS(Object parentClass, String methodSignature, String NLSMessage) {
/* 773 */     if (Trace.isOn) {
/* 774 */       Trace.entry("com.ibm.msg.client.commonservices.Log.Log", "logNLS(Object,String,String)", new Object[] { parentClass, methodSignature, NLSMessage });
/*     */     }
/*     */     
/* 777 */     logNLSInternal(parentClass, null, methodSignature, NLSMessage);
/*     */     
/* 779 */     if (Trace.isOn) {
/* 780 */       Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "logNLS(Object,String,String)");
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
/*     */   public static void logNLS(Object parentClass, String parentClassName, String methodSignature, String NLSMessage) {
/* 797 */     if (Trace.isOn) {
/* 798 */       Trace.entry("com.ibm.msg.client.commonservices.Log.Log", "logNLS(Object,String,String,String)", new Object[] { parentClass, parentClassName, methodSignature, NLSMessage });
/*     */     }
/*     */ 
/*     */     
/* 802 */     logNLSInternal(parentClass, parentClassName, methodSignature, NLSMessage);
/*     */     
/* 804 */     if (Trace.isOn) {
/* 805 */       Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "logNLS(Object,String,String,String)");
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
/*     */   public static void logNLS(String parentClassName, String methodSignature, String NLSMessage) {
/* 819 */     if (Trace.isOn) {
/* 820 */       Trace.entry("com.ibm.msg.client.commonservices.Log.Log", "logNLS(String,String,String)", new Object[] { parentClassName, methodSignature, NLSMessage });
/*     */     }
/*     */     
/* 823 */     logNLSInternal(null, parentClassName, methodSignature, NLSMessage);
/*     */     
/* 825 */     if (Trace.isOn) {
/* 826 */       Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "logNLS(String,String,String)");
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
/*     */   private static String flattenExceptionToString(Exception e) {
/* 839 */     if (Trace.isOn) {
/* 840 */       Trace.entry("com.ibm.msg.client.commonservices.Log.Log", "flattenExceptionToString(Exception)", new Object[] { e });
/*     */     }
/*     */ 
/*     */     
/* 844 */     int depth = 0;
/* 845 */     Throwable currentEx = e;
/* 846 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 848 */     while (currentEx != null) {
/*     */       
/* 850 */       if (depth == 0) {
/* 851 */         sb.append(lineSeparator);
/* 852 */         sb.append(padTitle(NLSServices.getMessage("JMSCS0011")));
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 857 */         sb.append(padTitle(NLSServices.getMessage("JMSCS0014", new Object[] { Integer.toString(depth) })));
/*     */       } 
/*     */       
/* 860 */       sb.append(currentEx.toString());
/* 861 */       sb.append(lineSeparator);
/*     */       
/* 863 */       sb.append(padTitle(NLSServices.getMessage("JMSCS0012")));
/* 864 */       sb.append(currentEx.getClass());
/* 865 */       sb.append(lineSeparator);
/*     */       
/* 867 */       dumpStackTrace(currentEx, sb);
/*     */       
/* 869 */       currentEx = currentEx.getCause();
/* 870 */       depth++;
/*     */     } 
/*     */     
/* 873 */     String traceRet1 = sb.toString();
/* 874 */     if (Trace.isOn) {
/* 875 */       Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "flattenExceptionToString(Exception)", traceRet1);
/*     */     }
/*     */     
/* 878 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String padTitle(String text) {
/* 889 */     if (Trace.isOn) {
/* 890 */       Trace.entry("com.ibm.msg.client.commonservices.Log.Log", "padTitle(String)", new Object[] { text });
/*     */     }
/*     */     
/* 893 */     StringBuffer buffer = new StringBuffer(titleSize);
/* 894 */     int textLength = text.trim().length();
/*     */     
/* 896 */     int padding = titleSize - textLength;
/* 897 */     for (int count = 0; count < padding; count++) {
/* 898 */       buffer.append(" ");
/*     */     }
/*     */     
/* 901 */     buffer.append(text.trim()).append(" : ");
/*     */     
/* 903 */     String traceRet1 = buffer.toString();
/* 904 */     if (Trace.isOn) {
/* 905 */       Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "padTitle(String)", traceRet1);
/*     */     }
/* 907 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void dumpStackTrace(Throwable e, StringBuffer sb) {
/* 917 */     if (Trace.isOn) {
/* 918 */       Trace.entry("com.ibm.msg.client.commonservices.Log.Log", "dumpStackTrace(final Throwable,StringBuffer)", new Object[] { e, sb });
/*     */     }
/*     */     
/* 921 */     StackTraceElement[] stack = e.getStackTrace();
/* 922 */     for (int count = 0; count < stack.length; count++) {
/*     */       String prefix;
/* 924 */       if (count == 0) {
/* 925 */         prefix = NLSServices.getMessage("JMSCS0013");
/*     */       } else {
/*     */         
/* 928 */         prefix = "";
/*     */       } 
/*     */       
/* 931 */       String className = stack[count].getClassName();
/* 932 */       String methodName = stack[count].getMethodName();
/* 933 */       String fileName = stack[count].getFileName();
/* 934 */       int line = stack[count].getLineNumber();
/*     */       
/* 936 */       sb.append(padTitle(prefix));
/* 937 */       sb.append(className);
/* 938 */       sb.append(".");
/* 939 */       sb.append(methodName);
/* 940 */       sb.append("(");
/* 941 */       sb.append(fileName);
/* 942 */       sb.append(":");
/* 943 */       sb.append(line);
/* 944 */       sb.append(")");
/* 945 */       sb.append(lineSeparator);
/*     */     } 
/* 947 */     if (Trace.isOn) {
/* 948 */       Trace.exit("com.ibm.msg.client.commonservices.Log.Log", "dumpStackTrace(final Throwable,StringBuffer)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isJeeOn() {
/* 955 */     return jeeOn;
/*     */   }
/*     */   
/*     */   public static void setJeeOn(boolean jeeOn) {
/* 959 */     Log.jeeOn = jeeOn;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\Log\Log.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */