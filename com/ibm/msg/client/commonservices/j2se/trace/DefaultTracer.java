/*      */ package com.ibm.msg.client.commonservices.j2se.trace;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.CommonServices;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.provider.trace.CSPTrace;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.commonservices.trace.TraceFormatter;
/*      */ import com.ibm.msg.client.commonservices.trace.TraceHandler;
/*      */ import java.io.File;
/*      */ import java.io.FileWriter;
/*      */ import java.io.IOException;
/*      */ import java.lang.management.ManagementFactory;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Method;
/*      */ import java.nio.file.InvalidPathException;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.Date;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.logging.ConsoleHandler;
/*      */ import java.util.logging.FileHandler;
/*      */ import java.util.logging.Filter;
/*      */ import java.util.logging.Formatter;
/*      */ import java.util.logging.Handler;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.LogManager;
/*      */ import java.util.logging.Logger;
/*      */ import java.util.regex.Pattern;
/*      */ import java.util.regex.PatternSyntaxException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class DefaultTracer
/*      */   implements CSPTrace
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/trace/DefaultTracer.java";
/*      */   public static final String METHOD_ENTRY = "METHOD_ENTRY";
/*      */   public static final String METHOD_EXIT = "METHOD_EXIT";
/*      */   public static final String THROWING = "THROWING";
/*      */   public static final String CATCH_BLOCK = "CATCH_BLOCK";
/*      */   public static final String FINALLY_BLOCK = "FINALLY_BLOCK";
/*      */   public static final String TRACE_DATA = "TRACE_DATA";
/*  190 */   static int maxTraceBytes = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private LogManager logManager;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Logger logger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  209 */   private String loggerName = "com.ibm.msg.client.commonservices.j2se.DefaultTracer";
/*      */   
/*  211 */   protected String tracePath = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean tracePropertiesRead = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  223 */   protected String outputFileName = "mqjms.trc";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  228 */   private int traceFileLimit = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  233 */   private int traceFileCount = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  238 */   private Handler handler = null;
/*      */ 
/*      */   
/*      */   private static boolean inMFT;
/*      */   
/*      */   private static boolean inMQXR;
/*      */   
/*      */   private static boolean inAMQP;
/*      */ 
/*      */   
/*      */   static {
/*  249 */     String javaClassPath = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*      */         {
/*      */           public String run()
/*      */           {
/*  253 */             return System.getProperty("java.class.path");
/*      */           }
/*      */         });
/*      */     
/*  257 */     inMFT = javaClassPath.contains("com.ibm.wmqfte.bootstrap");
/*  258 */     inMQXR = javaClassPath.contains("com.ibm.mq.mqxr.utils");
/*  259 */     inAMQP = javaClassPath.contains("com.ibm.mq.amqp.utils");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  265 */   private TraceHandler traceHandler = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  271 */   private Formatter formatter = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  276 */   private TraceFormatter traceFormatter = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean parameterTrace;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean compressTrace = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean appendTrace = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  303 */   private Filter filter = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  309 */   private int traceLevelint = Integer.MAX_VALUE;
/*  310 */   private Level traceLevelObject = Level.ALL;
/*      */   
/*      */   private Properties productProps;
/*      */ 
/*      */   
/*      */   private static final class HandlerWrapper
/*      */   {
/*      */     private String handlerFile;
/*      */     private Handler handler;
/*      */     
/*      */     HandlerWrapper(String handlerFile, Handler handler) {
/*  321 */       this.handlerFile = handlerFile;
/*  322 */       this.handler = handler;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     String getHandlerFile() {
/*  329 */       return this.handlerFile;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Handler getHandler() {
/*  336 */       return this.handler;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Properties getProductProperties() {
/*  371 */     return this.productProps;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Logger getLogger() {
/*  378 */     return this.logger;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void methodEntry(int level, Object parentClass, String parentClassName, String methodSignature, Object[] parameters) {
/*  389 */     this.logger.logp(getLevelObject(level), objectToString(parentClass, parentClassName), methodSignature, "METHOD_ENTRY", this.parameterTrace ? parameters : null);
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
/*      */   public void methodExit(int level, Object parentClass, String parentClassName, String methodSignature, Object returnValue, int exitIndex) {
/*  401 */     this.logger.logp(getLevelObject(level), objectToString(parentClass, parentClassName), (exitIndex == -1) ? methodSignature : (methodSignature + "<exitIndex " + exitIndex + ">"), "METHOD_EXIT", this.parameterTrace ? returnValue : null);
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
/*      */   public void traceData(int level, Object parentClass, String parentClassName, String methodSignature, String uniqueDescription, Object data) {
/*  414 */     this.logger.logp(getLevelObject(level), objectToString(parentClass, parentClassName), methodSignature, uniqueDescription, this.parameterTrace ? data : null);
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
/*      */   public void catchBlock(int level, Object parentClass, String parentClassName, String methodSignature, Throwable thrown, int catchIndex) {
/*  426 */     this.logger.logp(getLevelObject(level), objectToString(parentClass, parentClassName), (catchIndex == -1) ? methodSignature : (methodSignature + "<catchIndex " + catchIndex + ">"), "CATCH_BLOCK", this.parameterTrace ? thrown : null);
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
/*      */   public void throwing(int level, Object parentClass, String parentClassName, String methodSignature, Throwable thrown, int throwIndex) {
/*  439 */     this.logger.logp(getLevelObject(level), objectToString(parentClass, parentClassName), (throwIndex == -1) ? methodSignature : (methodSignature + "<throwIndex " + throwIndex + ">"), "THROWING", this.parameterTrace ? thrown : null);
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
/*      */   public void finallyBlock(int level, Object parentClass, String parentClassName, String methodSignature, int finallyIndex) {
/*  452 */     this.logger.logp(getLevelObject(level), objectToString(parentClass, parentClassName), (finallyIndex == -1) ? methodSignature : (methodSignature + "<finallyIndex " + finallyIndex + ">"), "FINALLY_BLOCK");
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
/*      */   protected void getTracerOptionsFromProperties() {
/*  472 */     if (this.tracePropertiesRead) {
/*      */       return;
/*      */     }
/*      */     
/*  476 */     this.outputFileName = PropertyStore.getStringProperty("com.ibm.msg.client.commonservices.trace.outputName");
/*  477 */     String searchString = PropertyStore.getStringProperty("com.ibm.msg.client.commonservices.trace.searchString");
/*  478 */     maxTraceBytes = PropertyStore.getLongPropertyObject("com.ibm.msg.client.commonservices.trace.maxBytes").intValue();
/*  479 */     this.traceFileLimit = PropertyStore.getLongPropertyObject("com.ibm.msg.client.commonservices.trace.limit").intValue();
/*  480 */     this.traceFileCount = PropertyStore.getLongPropertyObject("com.ibm.msg.client.commonservices.trace.count").intValue();
/*  481 */     this.parameterTrace = PropertyStore.getBooleanPropertyObject("com.ibm.msg.client.commonservices.trace.parameter").booleanValue();
/*  482 */     this.compressTrace = PropertyStore.getBooleanPropertyObject("com.ibm.msg.client.commonservices.trace.compress").booleanValue();
/*  483 */     this.appendTrace = PropertyStore.getBooleanPropertyObject("com.ibm.msg.client.commonservices.trace.append").booleanValue();
/*      */ 
/*      */     
/*  486 */     String traceHandlerName = PropertyStore.getStringProperty("com.ibm.msg.client.commonservices.trace.traceHandler");
/*  487 */     this.traceHandler = null;
/*  488 */     String traceFormatterName = PropertyStore.getStringProperty("com.ibm.msg.client.commonservices.trace.traceFormatter");
/*  489 */     this.traceFormatter = null;
/*      */     
/*  491 */     findMQRootDirectory();
/*      */     
/*  493 */     this.tDir = (this.mqRootDirectory != null) ? new File(this.mqRootDirectory, "trace") : null;
/*      */ 
/*      */ 
/*      */     
/*  497 */     this.cDir = new File(".");
/*      */ 
/*      */     
/*  500 */     if (PropertyStore.wasOverridden("com.ibm.msg.client.commonservices.trace.searchString", null)) {
/*      */ 
/*      */       
/*  503 */       if (this.filter == null) {
/*  504 */         this.filter = new TraceFilter();
/*      */       }
/*      */       
/*      */       try {
/*  508 */         ((TraceFilter)this.filter).setSearchString(searchString);
/*      */       }
/*  510 */       catch (PatternSyntaxException pse) {
/*      */ 
/*      */         
/*  513 */         HashMap<String, String> inserts = new HashMap<>();
/*  514 */         inserts.put("XMSC_SEARCH_STRING", searchString);
/*  515 */         System.err.println(NLSServices.getMessage("JMSCS0036", inserts));
/*  516 */         System.err.println(pse);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  521 */     if (traceHandlerName != null && !traceHandlerName.equals("")) {
/*      */       
/*      */       try {
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*      */           
/*  529 */           Class<?> cHandler = CSSystem.dynamicLoadClass(traceHandlerName, getClass());
/*  530 */           this.traceHandler = (TraceHandler)cHandler.newInstance();
/*      */         }
/*  532 */         catch (ClassNotFoundException e) {
/*  533 */           throw e;
/*      */         }
/*  535 */         catch (IllegalAccessException e) {
/*  536 */           throw e;
/*      */         }
/*  538 */         catch (InstantiationException e) {
/*  539 */           throw e;
/*      */         }
/*      */       
/*  542 */       } catch (Exception e) {
/*  543 */         HashMap<String, String> inserts = new HashMap<>();
/*  544 */         inserts.put("XMSC_HANDLER_NAME", traceHandlerName);
/*  545 */         System.err.println(NLSServices.getMessage("JMSCS0037", inserts));
/*  546 */         System.err.println(e);
/*      */       } 
/*      */     }
/*      */     
/*  550 */     if (traceFormatterName != null) {
/*      */       try {
/*      */         try {
/*  553 */           Class<?> cFormatter = CSSystem.dynamicLoadClass(traceFormatterName, getClass());
/*  554 */           this.traceFormatter = (TraceFormatter)cFormatter.newInstance();
/*      */         }
/*  556 */         catch (ClassNotFoundException e) {
/*  557 */           throw e;
/*      */         }
/*  559 */         catch (IllegalAccessException e) {
/*  560 */           throw e;
/*      */         }
/*  562 */         catch (InstantiationException e) {
/*  563 */           throw e;
/*      */         }
/*      */       
/*  566 */       } catch (Exception e) {
/*  567 */         HashMap<String, String> inserts = new HashMap<>();
/*  568 */         inserts.put("XMSC_FORMATTER_NAME", traceFormatterName);
/*  569 */         System.err.println(NLSServices.getMessage("JMSCS0037", inserts));
/*  570 */         System.err.println(e);
/*      */       } 
/*      */     }
/*      */     
/*  574 */     if (null != this.traceHandler) {
/*  575 */       setTraceHandler(this.traceHandler);
/*      */     }
/*      */     
/*  578 */     if (null != this.traceFormatter) {
/*  579 */       setTraceFormatter(this.traceFormatter);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  586 */     this.tracePropertiesRead = true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  591 */   protected static final String[] potentialRootDirectories = new String[] { "C:\\ProgramData\\IBM\\MQ", "C:\\Program Files (x86)\\IBM\\MQ", "C:\\Program Files\\IBM\\MQ", "C:\\Program Files (x86)\\IBM\\WebSphere MQ", "C:\\Program Files\\IBM\\WebSphere MQ", "/var/mqm", "/opt/mqm/" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void findMQRootDirectory() {
/*  607 */     this.mqRootDirectory = AccessController.<File>doPrivileged(new PrivilegedAction<File>()
/*      */         {
/*      */           public File run()
/*      */           {
/*  611 */             for (String potentialRootDirectory : DefaultTracer.potentialRootDirectories) {
/*      */               try {
/*  613 */                 File candidateDirectory = new File(potentialRootDirectory);
/*  614 */                 if (candidateDirectory.exists()) {
/*  615 */                   return candidateDirectory;
/*      */                 }
/*      */               }
/*  618 */               catch (AccessControlException accessControlException) {}
/*      */             } 
/*      */ 
/*      */             
/*  622 */             return null;
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTraceHandler(TraceHandler newHandler) {
/*      */     final Handler tempHandler;
/*  635 */     if (newHandler instanceof Handler) {
/*  636 */       tempHandler = (Handler)newHandler;
/*      */     } else {
/*      */       
/*  639 */       tempHandler = new TraceHandlerWrapper(newHandler);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  648 */     AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/*  652 */             DefaultTracer.this.logger.addHandler(tempHandler);
/*  653 */             if (DefaultTracer.this.handler != null) {
/*  654 */               DefaultTracer.this.logger.removeHandler(DefaultTracer.this.handler);
/*      */             }
/*  656 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  660 */     this.traceHandler = newHandler;
/*  661 */     this.handler = tempHandler;
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
/*      */   public void setTraceHandler(final Handler newHandler) {
/*  676 */     AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/*  680 */             if (DefaultTracer.this.handler != newHandler) {
/*  681 */               DefaultTracer.this.logger.addHandler(newHandler);
/*  682 */               DefaultTracer.this.logger.removeHandler(DefaultTracer.this.handler);
/*      */               
/*  684 */               DefaultTracer.this.handler = newHandler;
/*      */               
/*  686 */               DefaultTracer.this.traceHandler = null;
/*      */             } else {
/*      */               
/*  689 */               DefaultTracer.this.logger.addHandler(newHandler);
/*      */             } 
/*  691 */             return null;
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTraceFormatter(TraceFormatter newFormatter) {
/*  702 */     Formatter tempFormatter = null;
/*      */ 
/*      */ 
/*      */     
/*  706 */     if (newFormatter instanceof Formatter) {
/*  707 */       tempFormatter = (Formatter)newFormatter;
/*      */     } else {
/*      */       
/*  710 */       tempFormatter = new TraceFormatterWrapper(newFormatter);
/*      */     } 
/*      */     
/*  713 */     this.traceFormatter = newFormatter;
/*  714 */     this.formatter = tempFormatter;
/*  715 */     if (this.handler != null) {
/*  716 */       this.handler.setFormatter(this.formatter);
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
/*      */   public void setTraceFormatter(Formatter newFormatter) {
/*  732 */     this.formatter = newFormatter;
/*  733 */     this.traceFormatter = null;
/*      */     
/*  735 */     if (this.handler != null) {
/*  736 */       this.handler.setFormatter(this.formatter);
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
/*      */   public void close() {
/*      */     try {
/*  749 */       this.logger.removeHandler(this.handler);
/*  750 */       this.handler.close();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*  758 */     } catch (Throwable throwable) {
/*      */       return;
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
/*      */   public void initialize() {
/*  771 */     getTracerOptionsFromProperties();
/*      */     
/*  773 */     HandlerWrapper handlerWrapper = getAppropriateHandler();
/*  774 */     this.handler = handlerWrapper.getHandler();
/*  775 */     this.outputFileName = handlerWrapper.getHandlerFile();
/*      */ 
/*      */     
/*  778 */     this.traceLevelint = Trace.getTraceLevel();
/*  779 */     final Level level = getLevelObject(this.traceLevelint);
/*      */     
/*  781 */     AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/*      */             try {
/*  786 */               DefaultTracer.this.logger.setLevel(level);
/*      */             }
/*  788 */             catch (AccessControlException accessControlException) {}
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  793 */             DefaultTracer.this.logger.setUseParentHandlers(false);
/*  794 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  799 */     AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           
/*      */           public Object run()
/*      */           {
/*  804 */             DefaultTracer.this.handler.setLevel(Level.ALL);
/*  805 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  809 */     if (null == this.formatter) {
/*  810 */       this.formatter = new HumanFormatter();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  816 */     AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/*  820 */             DefaultTracer.this.handler.setFormatter(DefaultTracer.this.formatter);
/*  821 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  825 */     if (this.filter != null) {
/*  826 */       this.handler.setFilter(this.filter);
/*      */     }
/*      */     
/*  829 */     setTraceHandler(this.handler);
/*      */     
/*  831 */     logTraceProperties();
/*      */     
/*  833 */     if (this.handler instanceof ConsoleHandler) {
/*  834 */       displayAndLog("initialize", "JMSCS0041", new HashMap<>());
/*      */     }
/*      */     else {
/*      */       
/*  838 */       String resolvedTraceFilename = null;
/*      */       try {
/*  840 */         resolvedTraceFilename = (new File(this.outputFileName)).getCanonicalPath();
/*      */       }
/*  842 */       catch (AccessControlException|IOException ace) {
/*      */         
/*  844 */         resolvedTraceFilename = this.outputFileName;
/*      */       } 
/*  846 */       HashMap<String, String> inserts = new HashMap<>();
/*  847 */       inserts.put("XMSC_TRACE_FILE_NAME", resolvedTraceFilename);
/*  848 */       displayAndLog("initialize", "JMSCS0040", inserts);
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
/*      */   private void displayAndLog(String method, String key, HashMap<String, String> inserts) {
/*  862 */     if (!inMFT) {
/*  863 */       Log.log(this, method, key, inserts);
/*  864 */       System.out.println(NLSServices.getMessage(key, inserts));
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
/*      */   private void logTraceProperties() {
/*  883 */     AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/*  887 */             Field[] traceClassFields = Trace.class.getFields();
/*  888 */             HashMap<String, String> inserts = new HashMap<>();
/*  889 */             for (Field f : traceClassFields) {
/*  890 */               int modifiers = f.getModifiers();
/*  891 */               if ((modifiers & 0x8) != 0 && f
/*  892 */                 .getName().endsWith("Property")) {
/*      */                 try {
/*  894 */                   String propertyName = (String)f.get(null);
/*  895 */                   String propertyValue = System.getProperty(propertyName);
/*  896 */                   if (propertyValue != null) {
/*  897 */                     inserts.put("XMSC_PROPERTY_NAME", propertyName);
/*  898 */                     inserts.put("XMSC_PROPERTY_VALUE", propertyValue);
/*  899 */                     Log.log(DefaultTracer.class.getCanonicalName(), "LogTraceProperties", "JMSCS0042", inserts);
/*      */                   }
/*      */                 
/*  902 */                 } catch (IllegalArgumentException|IllegalAccessException|AccessControlException illegalArgumentException) {}
/*      */               }
/*      */             } 
/*      */ 
/*      */             
/*  907 */             return null;
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private HandlerWrapper getAppropriateHandler() {
/*  917 */     final String pid = generatePID();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  928 */     final String defaultTraceFileName = ((String)this.productProps.get("TraceFilename")).replaceAll("%PID%", pid);
/*  929 */     this.outputFileName = this.outputFileName.replace("%PID%", pid);
/*      */     
/*  931 */     return AccessController.<HandlerWrapper>doPrivileged(new PrivilegedAction<HandlerWrapper>()
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public DefaultTracer.HandlerWrapper run()
/*      */           {
/*  942 */             DefaultTracer.HandlerWrapper resultHandler = null;
/*      */             
/*  944 */             String workingOutputFileName = DefaultTracer.this.outputFileName;
/*      */             
/*  946 */             File outputFile = new File(workingOutputFileName);
/*      */             
/*  948 */             if (outputFile.isAbsolute() && outputFile.isDirectory())
/*      */             {
/*      */ 
/*      */ 
/*      */               
/*  953 */               if (outputFile.canWrite()) {
/*      */ 
/*      */ 
/*      */                 
/*  957 */                 outputFile = new File(outputFile, defaultTraceFileName);
/*      */               
/*      */               }
/*      */               else {
/*      */ 
/*      */                 
/*  963 */                 HashMap<String, String> inserts = new HashMap<>();
/*  964 */                 inserts.put("XMSC_TRACE_FILE_NAME", outputFile.getAbsolutePath());
/*  965 */                 DefaultTracer.reportFailure("JMSCS0034", inserts);
/*  966 */                 outputFile = new File(defaultTraceFileName);
/*      */               } 
/*      */             }
/*      */             
/*  970 */             if (outputFile.isAbsolute()) {
/*  971 */               resultHandler = createHandlerForNamedFile(outputFile);
/*  972 */               if (resultHandler == null) {
/*  973 */                 HashMap<String, String> inserts = new HashMap<>();
/*  974 */                 inserts.put("XMSC_TRACE_FILE_NAME", outputFile.getAbsolutePath());
/*  975 */                 DefaultTracer.reportFailure("JMSCS0034", inserts);
/*      */ 
/*      */                 
/*  978 */                 workingOutputFileName = defaultTraceFileName;
/*      */               } 
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  987 */             if ((DefaultTracer.inMQXR || DefaultTracer.inAMQP) && 
/*  988 */               resultHandler == null && DefaultTracer.this.tDir != null) {
/*  989 */               outputFile = new File(DefaultTracer.this.tDir, workingOutputFileName);
/*  990 */               resultHandler = createHandlerForNamedFile(outputFile);
/*  991 */               if (resultHandler == null) {
/*  992 */                 HashMap<String, String> inserts = new HashMap<>();
/*  993 */                 inserts.put("XMSC_TRACE_FILE_NAME", outputFile.getAbsolutePath());
/*  994 */                 DefaultTracer.reportFailure("JMSCS0033", inserts);
/*      */               } 
/*      */             } 
/*      */ 
/*      */             
/*  999 */             if (resultHandler == null) {
/* 1000 */               outputFile = new File(DefaultTracer.this.cDir, workingOutputFileName);
/* 1001 */               resultHandler = createHandlerForNamedFile(outputFile);
/* 1002 */               if (resultHandler == null) {
/* 1003 */                 HashMap<String, String> inserts = new HashMap<>();
/* 1004 */                 inserts.put("XMSC_TRACE_FILE_NAME", outputFile.getAbsolutePath());
/* 1005 */                 DefaultTracer.reportFailure("JMSCS0035", inserts);
/*      */               } 
/*      */             } 
/*      */             
/* 1009 */             if (resultHandler == null) {
/* 1010 */               resultHandler = new DefaultTracer.HandlerWrapper("console", new ConsoleHandler());
/*      */             }
/*      */             
/* 1013 */             return resultHandler;
/*      */           }
/*      */           
/*      */           private DefaultTracer.HandlerWrapper createHandlerForNamedFile(File outputFile) {
/*      */             try {
/* 1018 */               File resultantOutputFile = DefaultTracer.this.prepareOutputFile(outputFile);
/* 1019 */               if (resultantOutputFile != null) {
/* 1020 */                 return DefaultTracer.this.createHandler(resultantOutputFile.getAbsolutePath(), pid);
/*      */               }
/*      */             }
/* 1023 */             catch (IOException|AccessControlException iOException) {}
/*      */ 
/*      */             
/* 1026 */             return null;
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */   
/*      */   protected static void reportFailure(String messageKey, HashMap<String, String> inserts) {
/* 1033 */     String message = NLSServices.getMessage(messageKey, inserts);
/* 1034 */     System.err.println(message);
/* 1035 */     System.out.println(message);
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
/*      */   private HandlerWrapper createHandler(String outputFileName1, String pid) throws IOException, AccessControlException {
/*      */     Handler createdHandler;
/*      */     try {
/* 1051 */       if (this.compressTrace) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1056 */         JarFileHandler.setPID(pid);
/*      */         
/* 1058 */         outputFileName1 = outputFileName1.replaceAll(".trc", ".trz");
/* 1059 */         createdHandler = new JarFileHandler(outputFileName1, this.traceFileLimit, this.traceFileCount, this.appendTrace);
/*      */       } else {
/*      */         
/* 1062 */         createdHandler = new FileHandler(outputFileName1, this.traceFileLimit, this.traceFileCount, this.appendTrace);
/*      */       }
/*      */     
/* 1065 */     } catch (InvalidPathException ipe) {
/* 1066 */       throw new IOException(ipe);
/*      */     } 
/*      */     
/* 1069 */     return new HandlerWrapper(outputFileName1, createdHandler);
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
/*      */   private File prepareOutputFile(File traceFile) throws AccessControlException {
/* 1083 */     File traceDirectory = null;
/*      */     
/* 1085 */     String parent = traceFile.getParent();
/* 1086 */     if (parent == null) {
/* 1087 */       parent = ".";
/*      */     }
/* 1089 */     traceDirectory = new File(parent);
/*      */ 
/*      */     
/* 1092 */     if (!traceDirectory.exists()) {
/*      */       
/* 1094 */       boolean success = traceDirectory.mkdirs();
/* 1095 */       if (!success) {
/*      */ 
/*      */         
/* 1098 */         success = traceFile.mkdirs();
/* 1099 */         if (!success) {
/*      */ 
/*      */           
/* 1102 */           HashMap<String, String> inserts = new HashMap<>();
/* 1103 */           inserts.put("XMSC_TRACE_FILE_NAME", traceFile.getName());
/* 1104 */           inserts.put("XMSC_TRACE_DIRECTORY_NAME", traceFile.getParent());
/* 1105 */           reportFailure("JMSCS0030", inserts);
/* 1106 */           return null;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1115 */     if (!traceDirectory.canWrite()) {
/*      */       
/* 1117 */       HashMap<String, String> inserts = new HashMap<>();
/* 1118 */       inserts.put("XMSC_TRACE_FILE_NAME", traceFile.getName());
/* 1119 */       inserts.put("XMSC_TRACE_DIRECTORY_NAME", traceFile.getParent());
/* 1120 */       reportFailure("JMSCS0030", inserts);
/* 1121 */       return null;
/*      */     } 
/*      */     
/* 1124 */     File resolvedTraceFile = traceFile;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1131 */     int suffix = 0;
/* 1132 */     String baseName = resolvedTraceFile.getName();
/* 1133 */     while (resolvedTraceFile.exists() && !resolvedTraceFile.canWrite()) {
/* 1134 */       suffix++;
/* 1135 */       String newBaseName = String.format("%s_%d", new Object[] { baseName, Integer.valueOf(suffix) });
/* 1136 */       resolvedTraceFile = new File(traceDirectory, newBaseName);
/*      */     } 
/*      */     
/* 1139 */     return resolvedTraceFile;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1144 */   static String PID = "none";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static String generatePID() {
/* 1172 */     synchronized (PID) {
/* 1173 */       if (PID.equals("none")) {
/*      */ 
/*      */         
/* 1176 */         String pid = null;
/*      */         
/* 1178 */         String jvmName = ManagementFactory.getRuntimeMXBean().getName();
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1183 */         int sep = jvmName.indexOf('@');
/* 1184 */         if (sep > -1) {
/* 1185 */           int pidNumeric = Math.abs(Integer.parseInt(jvmName.substring(0, sep)));
/*      */ 
/*      */ 
/*      */           
/* 1189 */           pid = Integer.toString(pidNumeric);
/*      */         } 
/*      */         
/* 1192 */         if (pid == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1197 */           Random r = new Random(System.currentTimeMillis());
/* 1198 */           pid = "f" + r.nextInt(9999);
/*      */         } 
/*      */         
/* 1201 */         PID = pid;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1206 */     return PID;
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
/*      */   public static String objectToString(Object obj, String parentClassName) {
/* 1226 */     if (obj == null || obj instanceof Class) {
/* 1227 */       return parentClassName;
/*      */     }
/*      */ 
/*      */     
/* 1231 */     String objectClassName = obj.getClass().getName();
/*      */     
/* 1233 */     StringBuffer result = new StringBuffer(objectClassName);
/*      */     
/* 1235 */     if (null != parentClassName && !result.toString().equals(parentClassName) && objectClassName.indexOf('$') == -1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1242 */       int pIndex = parentClassName.lastIndexOf(".");
/* 1243 */       result.append("(");
/* 1244 */       result.append(parentClassName.substring(pIndex + 1));
/* 1245 */       result.append(")");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1251 */     result.append("@");
/* 1252 */     result.append(Integer.toHexString(System.identityHashCode(obj)));
/*      */     
/* 1254 */     return result.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getMaxTraceBytes() {
/* 1261 */     return maxTraceBytes;
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
/*      */   public String ffst(Object sourceClass, String methodSignature, String probeID, HashMap<String, ? extends Object> data, String header) {
/* 1279 */     return ffst(sourceClass, methodSignature, probeID, data, header, null);
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
/*      */   protected String ffst(Object sourceClass, String methodSignature, String probeID, HashMap<String, ? extends Object> data, final String header, final String fileName) {
/* 1291 */     getTracerOptionsFromProperties();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1297 */     return AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*      */         {
/*      */           public String run()
/*      */           {
/* 1301 */             String output = DefaultTracer.this.getFFSTName();
/*      */             
/*      */             try {
/* 1304 */               StringBuffer ffstInfo = new StringBuffer("----------------------------------START FFST------------------------------------");
/* 1305 */               ffstInfo.append(Trace.lineSeparator);
/*      */               
/* 1307 */               File ffdcDir = null;
/* 1308 */               if (fileName == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1314 */                 String pid = DefaultTracer.generatePID();
/* 1315 */                 String filename = DefaultTracer.this.outputFileName.replaceAll("%PID%", pid);
/* 1316 */                 File parentFile = (new File(filename)).getParentFile();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1323 */                 ffdcDir = new File(parentFile, "FFDC");
/*      */               
/*      */               }
/*      */               else {
/*      */                 
/* 1328 */                 ffdcDir = new File(fileName);
/*      */               } 
/* 1330 */               if (!ffdcDir.exists()) {
/* 1331 */                 boolean success = ffdcDir.mkdirs();
/*      */                 
/* 1333 */                 if (!success) {
/*      */ 
/*      */                   
/* 1336 */                   success = ffdcDir.mkdirs();
/* 1337 */                   if (!success) {
/*      */ 
/*      */                     
/* 1340 */                     ffdcDir = new File("FFDC");
/*      */                     
/* 1342 */                     boolean bool = ffdcDir.mkdirs();
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */ 
/*      */               
/* 1348 */               File outputFile = new File(ffdcDir, output);
/*      */               
/* 1350 */               while (!outputFile.createNewFile()) {
/* 1351 */                 String newName = DefaultTracer.this.getFFSTName();
/*      */ 
/*      */                 
/* 1354 */                 if (outputFile.getName().equals(newName)) {
/*      */                   break;
/*      */                 }
/* 1357 */                 outputFile = new File(ffdcDir, newName);
/*      */               } 
/*      */ 
/*      */               
/* 1361 */               ffstInfo.append(outputFile.getAbsoluteFile()).append(" PID:").append(DefaultTracer.PID);
/*      */ 
/*      */ 
/*      */               
/* 1365 */               ffstInfo.append(DefaultTracer.this.updateProductInfo(header));
/*      */ 
/*      */ 
/*      */               
/* 1369 */               try (FileWriter stream = new FileWriter(outputFile, true)) {
/*      */ 
/*      */ 
/*      */                 
/* 1373 */                 String threadInfo = DefaultTracer.this.generateThreadInfo();
/*      */                 
/* 1375 */                 ffstInfo.append(threadInfo);
/*      */                 
/* 1377 */                 ffstInfo.append(Trace.lineSeparator);
/* 1378 */                 ffstInfo.append("First Failure Symptom Report completed at ").append(DefaultTracer.getTimestamp());
/* 1379 */                 ffstInfo.append(Trace.lineSeparator);
/* 1380 */                 ffstInfo.append("------------------------------------END FFST------------------------------------");
/* 1381 */                 ffstInfo.append(Trace.lineSeparator);
/*      */                 
/* 1383 */                 stream.write(ffstInfo.toString());
/* 1384 */                 stream.flush();
/* 1385 */                 stream.close();
/*      */               } 
/*      */               
/* 1388 */               output = outputFile.getCanonicalPath();
/*      */ 
/*      */ 
/*      */               
/* 1392 */               DefaultTracer.this.writeJavaCore();
/*      */             
/*      */             }
/* 1395 */             catch (Throwable e) {
/*      */ 
/*      */ 
/*      */               
/* 1399 */               HashMap<String, String> inserts = new HashMap<>();
/* 1400 */               inserts.put("XMSC_FDC_NAME", output);
/* 1401 */               System.err.println(NLSServices.getMessage("JMSCS0039", inserts));
/*      */               
/* 1403 */               Throwable problem = e;
/*      */               do {
/* 1405 */                 problem.printStackTrace(System.err);
/* 1406 */                 problem = problem.getCause();
/*      */               }
/* 1408 */               while (problem != null);
/*      */ 
/*      */               
/* 1411 */               System.err.println(header);
/* 1412 */               output = "System.err";
/*      */             } 
/* 1414 */             return output;
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   private static String getTimestamp() {
/* 1420 */     long time = System.currentTimeMillis();
/* 1421 */     return (new Date(time)).toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String generateThreadInfo() {
/* 1431 */     StringBuffer threadInfo = (new StringBuffer("All Thread Information")).append(Trace.lineSeparator);
/*      */     
/* 1433 */     Map<Thread, StackTraceElement[]> traces = Thread.getAllStackTraces();
/*      */ 
/*      */     
/* 1436 */     Set<Map.Entry<Thread, StackTraceElement[]>> entries = (Set)traces.entrySet();
/* 1437 */     assert entries != null;
/*      */     
/* 1439 */     Iterator<Map.Entry<Thread, StackTraceElement[]>> it = entries.iterator();
/*      */     
/* 1441 */     while (it.hasNext()) {
/*      */       
/* 1443 */       Map.Entry<Thread, StackTraceElement[]> entry = it.next();
/* 1444 */       Thread t = entry.getKey();
/*      */       
/* 1446 */       threadInfo.append(Trace.lineSeparator);
/*      */       
/* 1448 */       threadInfo.append(padTitle("Name")).append(t.getName()).append(Trace.lineSeparator);
/* 1449 */       threadInfo.append(padTitle("Priority") + t.getPriority()).append(Trace.lineSeparator);
/* 1450 */       threadInfo.append(padTitle("ThreadGroup") + t.getThreadGroup()).append(Trace.lineSeparator);
/* 1451 */       threadInfo.append(padTitle("ID") + t.getId()).append(Trace.lineSeparator);
/* 1452 */       threadInfo.append(padTitle("State") + t.getState()).append(Trace.lineSeparator);
/*      */       
/* 1454 */       StackTraceElement[] stack = entry.getValue();
/* 1455 */       for (int c = 0; c < stack.length; c++) {
/* 1456 */         String prefix = (c == 0) ? "Stack" : "";
/*      */         
/* 1458 */         String className = stack[c].getClassName();
/* 1459 */         String methodName = stack[c].getMethodName();
/* 1460 */         String fileName = stack[c].getFileName();
/* 1461 */         int line = stack[c].getLineNumber();
/*      */         
/* 1463 */         threadInfo.append(padTitle(prefix)).append(className).append(".").append(methodName).append("(").append(fileName);
/* 1464 */         threadInfo.append(":").append(line).append(")").append(Trace.lineSeparator);
/*      */       } 
/*      */     } 
/*      */     
/* 1468 */     return threadInfo.toString();
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
/*      */   private String updateProductInfo(String header) {
/* 1480 */     String updatedHeader = header;
/*      */     
/* 1482 */     Enumeration<Object> keys = this.productProps.keys();
/* 1483 */     while (keys.hasMoreElements()) {
/* 1484 */       String key = (String)keys.nextElement();
/*      */       
/*      */       try {
/* 1487 */         updatedHeader = updatedHeader.replaceAll(Pattern.quote(key), (String)this.productProps.get(key));
/*      */       }
/* 1489 */       catch (IllegalArgumentException illegalArgumentException) {}
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1496 */     return updatedHeader;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeJavaCore() {
/* 1507 */     PropertyStore.register("com.ibm.msg.client.commonservices.j2se.produceJavaCore", false);
/* 1508 */     boolean produceCore = PropertyStore.getBooleanPropertyObject("com.ibm.msg.client.commonservices.j2se.produceJavaCore").booleanValue();
/* 1509 */     if (produceCore) {
/*      */       
/*      */       try {
/* 1512 */         Class<?> dumpClass = CSSystem.dynamicLoadClass("com.ibm.jvm.Dump", Trace.class);
/* 1513 */         Method dumpMethod = dumpClass.getMethod("JavaDump", (Class[])null);
/* 1514 */         dumpMethod.invoke(null, (Object[])null);
/*      */       }
/* 1516 */       catch (Exception exception) {}
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1526 */   private static int count = 0;
/*      */   public Level level950;
/*      */   public Level level867;
/*      */   public Level level833;
/*      */   
/*      */   private synchronized String getFFSTName() {
/* 1532 */     StringBuffer countString = new StringBuffer();
/* 1533 */     count++;
/* 1534 */     for (int j = 1000; j >= 10; j /= 10) {
/* 1535 */       if (count < j) {
/* 1536 */         countString.append('0');
/*      */       }
/*      */     } 
/* 1539 */     countString.append(count);
/*      */ 
/*      */     
/* 1542 */     String productName = (String)this.productProps.get("FDCFilename");
/* 1543 */     String ffstName = productName.replaceAll("%COUNT%", countString.toString());
/*      */     
/* 1545 */     return ffstName;
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
/*      */   private Level getLevelObject(int level) {
/* 1558 */     Level retLevel = Level.OFF;
/*      */     
/* 1560 */     switch (level)
/*      */     { case 0:
/* 1562 */         retLevel = Level.OFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1599 */         return retLevel;case 1: retLevel = Level.SEVERE; return retLevel;case 2: retLevel = this.level950; return retLevel;case 3: retLevel = Level.WARNING; return retLevel;case 4: retLevel = this.level867; return retLevel;case 5: retLevel = this.level833; return retLevel;case 6: retLevel = Level.INFO; return retLevel;case 7: retLevel = Level.CONFIG; return retLevel;case 8: retLevel = Level.FINE; return retLevel;case 9: retLevel = Level.FINER; return retLevel;case 10: retLevel = Level.FINEST; return retLevel; }  retLevel = Level.ALL; return retLevel;
/*      */   }
/*      */   
/*      */   public DefaultTracer() {
/* 1603 */     this.level950 = new UserLevel(950);
/*      */     
/* 1605 */     this.level867 = new UserLevel(867);
/*      */     
/* 1607 */     this.level833 = new UserLevel(833);
/*      */     this.logManager = LogManager.getLogManager();
/*      */     String classLoaderUniqueLoggerName = this.loggerName + "_" + DefaultTracer.class.getClassLoader().toString();
/*      */     this.logger = Logger.getLogger(classLoaderUniqueLoggerName);
/*      */     this.productProps = CommonServices.getProductization();
/*      */   }
/*      */   
/*      */   public static class UserLevel extends Level {
/*      */     private static final long serialVersionUID = -1908778006198989698L;
/*      */     
/*      */     public UserLevel(int level) {
/* 1618 */       super("Level: " + level, level);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTraceLevel(int traceLevel) {
/* 1627 */     this.traceLevelint = traceLevel;
/* 1628 */     this.traceLevelObject = getLevelObject(traceLevel);
/* 1629 */     final Level level = this.traceLevelObject;
/*      */ 
/*      */     
/* 1632 */     AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/* 1636 */             DefaultTracer.this.logger.setLevel(level);
/* 1637 */             return null;
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTraceLevel() {
/* 1649 */     return this.traceLevelint;
/*      */   }
/*      */ 
/*      */   
/* 1653 */   private static int titleSize = 30;
/*      */   
/*      */   protected File mqRootDirectory;
/*      */   
/*      */   private File tDir;
/*      */   private File cDir;
/*      */   
/*      */   private static String padTitle(String text) {
/* 1661 */     StringBuffer buffer = new StringBuffer(titleSize);
/* 1662 */     int textLength = text.trim().length();
/*      */     
/* 1664 */     int padding = titleSize - textLength;
/* 1665 */     for (int c = 0; c < padding; c++) {
/* 1666 */       buffer.append(" ");
/*      */     }
/*      */     
/* 1669 */     buffer.append(text.trim()).append(" : ");
/*      */     
/* 1671 */     String traceRet1 = buffer.toString();
/* 1672 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getOutputFileName() {
/* 1680 */     return this.outputFileName;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\trace\DefaultTracer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */