/*      */ package com.ibm.msg.client.commonservices.trace;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.CSIException;
/*      */ import com.ibm.msg.client.commonservices.CSIListener;
/*      */ import com.ibm.msg.client.commonservices.CommonServices;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.Utils;
/*      */ import com.ibm.msg.client.commonservices.componentmanager.Component;
/*      */ import com.ibm.msg.client.commonservices.componentmanager.ComponentManager;
/*      */ import com.ibm.msg.client.commonservices.monitor.MonitorAgent;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.provider.trace.CSPTrace;
/*      */ import com.ibm.msg.client.commonservices.workqueue.WorkQueueManager;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.PrintStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.StringWriter;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.charset.Charset;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.Set;
/*      */ import java.util.TimeZone;
/*      */ import java.util.Vector;
/*      */ import java.util.WeakHashMap;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import java.util.zip.GZIPOutputStream;
/*      */ import javax.management.NotCompliantMBeanException;
/*      */ import javax.management.StandardMBean;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class Trace
/*      */ {
/*      */   private static final String HUMAN_DATE_FORMAT = "dd/MM/yyyy kk:mm:ss z";
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/trace/Trace.java";
/*      */   public static final String traceStatusProperty = "com.ibm.msg.client.commonservices.trace.status";
/*      */   public static final String traceStatusProperty_ON = "ON";
/*      */   public static final String traceStatusProperty_OFF = "OFF";
/*      */   public static final String traceStatusProperty_LISTEN = "LISTEN";
/*      */   public static final String traceStatusProperty_default = "OFF";
/*      */   public static final String startupTraceProperty = "com.ibm.msg.client.commonservices.trace.startup";
/*      */   public static final String errorStreamProperty = "com.ibm.msg.client.commonservices.trace.errorStream";
/*      */   public static final String traceLevelProperty = "com.ibm.msg.client.commonservices.trace.level";
/*      */   public static final long traceLevelProperty_default = 9L;
/*      */   public static final String outputFileNameProperty = "com.ibm.msg.client.commonservices.trace.outputName";
/*      */   public static final String includedPackagesProperty = "com.ibm.msg.client.commonservices.trace.include";
/*      */   public static final String includedPackagesProperty_default = "ALL";
/*      */   public static final String excludedPackagesProperty = "com.ibm.msg.client.commonservices.trace.exclude";
/*      */   public static final String excludedPackagesProperty_default = "NONE";
/*      */   public static final String excludedPackagesProperty_headers_etc = "com.ibm.mq.headers;com.ibm.mq.pcf";
/*      */   public static final String searchStringProperty = "com.ibm.msg.client.commonservices.trace.searchString";
/*      */   public static final String searchStringProperty_default = "";
/*      */   public static final String maxTraceBytesProperty = "com.ibm.msg.client.commonservices.trace.maxBytes";
/*      */   public static final int maxTraceBytesProperty_default = -1;
/*      */   public static final String traceFileLimitProperty = "com.ibm.msg.client.commonservices.trace.limit";
/*      */   public static final int traceFileLimitProperty_default = 0;
/*      */   public static final String traceFileCountProperty = "com.ibm.msg.client.commonservices.trace.count";
/*      */   public static final int traceFileCountProperty_default = 1;
/*      */   public static final String parameterTraceProperty = "com.ibm.msg.client.commonservices.trace.parameter";
/*      */   public static final boolean parameterTraceProperty_default = true;
/*      */   public static final String appendTraceProperty = "com.ibm.msg.client.commonservices.trace.append";
/*      */   public static final boolean appendTraceProperty_default = true;
/*      */   public static final String traceHandlerNameProperty = "com.ibm.msg.client.commonservices.trace.traceHandler";
/*      */   public static final String traceHandlerNameProperty_default = "";
/*      */   public static final String traceFormatterNameProperty = "com.ibm.msg.client.commonservices.trace.traceFormatter";
/*      */   public static final String traceFormatterNameProperty_default = "";
/*      */   public static final String compressedTraceProperty = "com.ibm.msg.client.commonservices.trace.compress";
/*      */   public static final boolean compressedTraceProperty_default = false;
/*      */   public static final String produceJavaCoreProperty = "com.ibm.msg.client.commonservices.j2se.produceJavaCore";
/*      */   public static final boolean produceJavaCoreProperty_default = false;
/*      */   private static Properties productProps;
/*      */   private static final int JCA_TRACE_EXCEPTION = 1;
/*      */   private static final int JCA_TRACE_WARNING = 3;
/*      */   private static final int JCA_TRACE_INFO = 6;
/*      */   private static final int JCA_TRACE_ENTRYEXIT = 8;
/*      */   private static final int JCA_TRACE_DATA = 9;
/*      */   private static final int JCA_TRACE_ALL = 2147483647;
/*      */   public static final int ENTRY_TRACE_LEVEL = 8;
/*      */   public static final int EXIT_TRACE_LEVEL = 8;
/*      */   public static final int THROW_TRACE_LEVEL = 1;
/*      */   public static final int CATCH_TRACE_LEVEL = 1;
/*      */   public static final int FINALLY_TRACE_LEVEL = 8;
/*      */   public static final int WARNING_TRACE_LEVEL = 3;
/*      */   public static final int INFO_TRACE_LEVEL = 6;
/*      */   public static final int DATA_TRACE_LEVEL = 9;
/*      */   public static final int ALL_TRACE_LEVEL = 2147483647;
/*      */   public static final int FINEST_TRACE_LEVEL = 10;
/*      */   private static final String UNSPECIFIED_METHOD = "";
/*      */   public static final String DUMMY_PROBEID = "????????";
/*      */   public static boolean isOn = false;
/*  254 */   private static int traceLevel = Integer.MAX_VALUE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  263 */   private static CSPTrace currentTracer = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  270 */   private static CSPTrace functionalTracer = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static PackageNode rootNode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean filterTrace = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  289 */   private static NullTracer nullTracer = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  295 */   private static ThreadLocal<Boolean> currentlyTracing = null;
/*      */ 
/*      */   
/*      */   static boolean startupTracing = false;
/*      */ 
/*      */   
/*      */   static boolean initialized = false;
/*      */   
/*      */   static boolean listening = false;
/*      */   
/*  305 */   private static TraceFFSTInfo providerFFSTInfo = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  310 */   private static ArrayList<DumpableComponent> allDumpableComponents = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  316 */   public static String lineSeparator = "\n";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  340 */   private static Map<TraceHandler, Object> traceHandlers = Collections.synchronizedMap(new WeakHashMap<>());
/*      */   
/*      */   private static final String PROBE_1 = "1";
/*      */   
/*      */   private static final String PROBE_2 = "2";
/*      */   
/*      */   private static final String PROBE_3 = "3";
/*      */   
/*      */   private static final String PROBE_4 = "4";
/*      */   private static final String PROBE_5 = "5";
/*      */   private static final String PROBE_6 = "6";
/*  351 */   public static PrintStream errorStream = System.err;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String suppressFFST = "com.ibm.msg.client.commonservices.ffst.suppress";
/*      */ 
/*      */   
/*      */   private static final int suppressFFST_default = 0;
/*      */ 
/*      */   
/*      */   public static final String suppressFFSTProbeIDs = "com.ibm.msg.client.commonservices.ffst.suppress.probeIDs";
/*      */ 
/*      */   
/*      */   public static final String suppressFFSTProbeIDs_default = "";
/*      */ 
/*      */   
/*      */   public static final String dumpOnFFST = "com.ibm.msg.client.commonservices.dumponffst";
/*      */ 
/*      */   
/*      */   private static final boolean dumpOnFFST_default = false;
/*      */ 
/*      */   
/*      */   private static boolean doDumpOnFFST = false;
/*      */ 
/*      */   
/*      */   public static final String dumpLoc = "com.ibm.msg.client.commonservices.dumplocation";
/*      */ 
/*      */   
/*  379 */   private static final String dumpLoc_default = null;
/*  380 */   private static String dumpLocation = dumpLoc_default;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String dumpCompressed = "com.ibm.msg.client.commonservices.dumpcompressed";
/*      */ 
/*      */   
/*      */   private static final boolean dumpCompressed_default = true;
/*      */ 
/*      */   
/*      */   private static boolean compressDump = true;
/*      */ 
/*      */   
/*  393 */   private static HashMap<String, Integer> receivedFfst = new HashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  398 */   private static int ffstSuppressionLevel = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  404 */   private static List<String> ffstSuppressionProbeIDs = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean ffstParametersUnchecked = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  416 */   private static ThreadLocal<ThreadLocalStorage> tls = new ThreadLocal<>();
/*      */   
/*  418 */   private static final Map<String, Class<?>> cachedClassObjects = new ConcurrentHashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class NullClass {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  429 */   private static HashSet<String> blacklistDisplayPropertyNameSet = new HashSet<>(Arrays.asList(new String[] { "javax.net.ssl.trustStorePassword", "javax.net.ssl.keyStorePassword" }));
/*      */   private static final String BLANKS = "                ";
/*      */   private static StandardMBean dynamicTraceControl;
/*      */   private static String dynamicTraceControlName;
/*      */   private static TraceControlImpl traceController;
/*      */   
/*      */   private static synchronized void checkFfstParameters() {
/*  436 */     PropertyStore.register("com.ibm.msg.client.commonservices.ffst.suppress", 0L, Long.valueOf(-1L), Long.valueOf(2147483647L));
/*  437 */     ffstSuppressionLevel = PropertyStore.getLongPropertyObject("com.ibm.msg.client.commonservices.ffst.suppress").intValue();
/*      */     
/*  439 */     PropertyStore.register("com.ibm.msg.client.commonservices.dumponffst", false);
/*  440 */     doDumpOnFFST = PropertyStore.getBooleanPropertyObject("com.ibm.msg.client.commonservices.dumponffst").booleanValue();
/*      */     
/*  442 */     PropertyStore.register("com.ibm.msg.client.commonservices.dumplocation", dumpLoc_default);
/*  443 */     dumpLocation = PropertyStore.getStringProperty("com.ibm.msg.client.commonservices.dumplocation");
/*      */     
/*  445 */     PropertyStore.register("com.ibm.msg.client.commonservices.dumpcompressed", true);
/*  446 */     compressDump = PropertyStore.getBooleanPropertyObject("com.ibm.msg.client.commonservices.dumpcompressed").booleanValue();
/*      */ 
/*      */     
/*  449 */     PropertyStore.register("com.ibm.msg.client.commonservices.ffst.suppress.probeIDs", "");
/*      */ 
/*      */     
/*  452 */     String[] probeIDs = PropertyStore.getStringProperty("com.ibm.msg.client.commonservices.ffst.suppress.probeIDs").split(";");
/*  453 */     for (String probeID : probeIDs) {
/*  454 */       ffstSuppressionProbeIDs.add(probeID);
/*      */     }
/*      */     
/*  457 */     ffstParametersUnchecked = false;
/*      */   } public static synchronized void setOn(boolean traceOn) { if (isOn != traceOn || (!currentTracer.equals(functionalTracer) && !currentTracer.equals(nullTracer))) { isOn = traceOn; if (traceOn) { functionalTracer.initialize(); currentTracer = functionalTracer; traceData(Trace.class, "setOn(boolean)", "Enabled trace", (Object)null); StringBuffer version = new StringBuffer(); version = getVersion(version); traceData(Trace.class, "setOn(boolean)", "Version Information", version); } else { traceData(Trace.class, "setOn(boolean)", "Disabling Trace", (Object)null); cachedClassObjects.clear(); currentTracer.close(); currentTracer = nullTracer; }  if (null != traceHandlers) synchronized (traceHandlers) { for (TraceHandler traceHandler : traceHandlers.keySet()) traceHandler.setOn(isOn);  }   }  } public static void setTraceLevel(int newTraceLevel) { traceLevel = newTraceLevel; functionalTracer.setTraceLevel(newTraceLevel); } public static int getTraceLevel() { return traceLevel; } public static PackageNode getRootNode() { return rootNode; } public static void registerTraceHandler(TraceHandler handler) { if (handler != null && !traceHandlers.containsKey(handler)) { traceHandlers.put(handler, null); handler.setOn(isOn); }  } public static void setCSPTrace(CSPTrace newTracer) { if (null == newTracer) { functionalTracer = nullTracer; } else { functionalTracer = newTracer; }  setOn(isOn); } public static CSPTrace getCSPTrace() { if (null == functionalTracer || functionalTracer instanceof NullTracer) return null;  return functionalTracer; } private static boolean traceable() { Boolean alreadyTracing = currentlyTracing.get(); currentlyTracing.set(Boolean.TRUE); return !alreadyTracing.booleanValue(); } private static void endTracing() { currentlyTracing.set(Boolean.FALSE); } public static void entry(Object parentClass, String parentClassName, String methodSignature, Object[] parameters) { methodEntryInternal(parentClass, parentClassName, methodSignature, parameters); } public static void entry(Object parentClass, String parentClassName, String methodSignature) { methodEntryInternal(parentClass, parentClassName, methodSignature, null); } public static void entry(String parentClassName, String methodSignature) { methodEntryInternal(null, parentClassName, methodSignature, null); } public static void entry(String parentClassName, String methodSignature, Object[] parameters) { methodEntryInternal(null, parentClassName, methodSignature, parameters); } private static void methodEntryInternal(Object parentClassP, String parentClassName, String methodSignature, Object[] parameters) { Object parentClass = parentClassP; if (isOn && traceable()) try { if (isClassTraced(parentClass, parentClassName)) { if (parentClass == null || parentClass instanceof String) parentClass = getCachedClassObject(parentClassName);  currentTracer.methodEntry(8, parentClass, parentClassName, methodSignature, parameters); }  } catch (Throwable t) { endTracing(); catchBlock("com.ibm.msg.client.commonservices.trace.Trace", "methodEntryInternal", t); HashMap<String, Object> data = new HashMap<>(); data.put("reason", "Entry trace threw exception"); data.put("exception", t); ffst("com.ibm.msg.client.commonservices.trace.Trace", "methodEntryInternal(Object, String, Object[])", "1", data, (Class)CSIException.class); } finally { endTracing(); }   } public static void exit(Object parentClass, String parentClassName, String methodSignature, Object returnValue, int exitIndex) { methodExitInternal(parentClass, parentClassName, methodSignature, returnValue, exitIndex); } public static void exit(Object parentClass, String parentClassName, String methodSignature) { methodExitInternal(parentClass, parentClassName, methodSignature, null, -1); } public static void exit(Object parentClass, String parentClassName, String methodSignature, Object returnValue) { methodExitInternal(parentClass, parentClassName, methodSignature, returnValue, -1); } public static void exit(Object parentClass, String parentClassName, String methodSignature, int exitIndex) { methodExitInternal(parentClass, parentClassName, methodSignature, null, exitIndex); } public static void exit(String parentClassName, String methodSignature) { methodExitInternal(null, parentClassName, methodSignature, null, -1); } public static void exit(String parentClassName, String methodSignature, Object returnValue) { methodExitInternal(null, parentClassName, methodSignature, returnValue, -1); } public static void exit(String parentClassName, String methodSignature, int exitIndex) { methodExitInternal(null, parentClassName, methodSignature, null, exitIndex); } public static void exit(String parentClassName, String methodSignature, Object returnValue, int exitIndex) { methodExitInternal(null, parentClassName, methodSignature, returnValue, exitIndex); } private static void methodExitInternal(Object parentClassP, String parentClassName, String methodSignature, Object returnValue, int exitIndex) { Object parentClass = parentClassP; if (isOn && traceable()) try { if (isClassTraced(parentClass, parentClassName)) { if (parentClass == null || parentClass instanceof String) parentClass = getCachedClassObject(parentClassName);  currentTracer.methodExit(8, parentClass, parentClassName, methodSignature, returnValue, exitIndex); }  } catch (Throwable t) { endTracing(); catchBlock("com.ibm.msg.client.commonservices.trace.Trace", "methodExitInternal", t); HashMap<String, Object> data = new HashMap<>(); data.put("Reason", "Exit trace threw exception"); data.put("exception", t); ffst("com.ibm.msg.client.commonservices.trace.Trace", "methodExitInternal(Object, String, Object, int)", "2", data, (Class)CSIException.class); } finally { endTracing(); }   } public static void traceData(Object parentClassP, String parentClassName, String methodSignature, String uniqueDescription, Object data) { traceDataInternal(9, parentClassP, parentClassName, methodSignature, uniqueDescription, data); } public static void traceData(int level, Object parentClassP, String parentClassName, String methodSignature, String uniqueDescription, Object data) { traceDataInternal(level, parentClassP, parentClassName, methodSignature, uniqueDescription, data); }
/*      */   public static void traceData(Object parentClassP, String methodSignature, String uniqueDescription, Object data) { traceDataInternal(9, parentClassP, null, methodSignature, uniqueDescription, data); }
/*      */   public static void traceData(int level, Object parentClassP, String methodSignature, String uniqueDescription, Object data) { traceDataInternal(level, parentClassP, null, methodSignature, uniqueDescription, data); }
/*      */   public static void data(Object parentClassP, String parentClassName, String methodSignature, String uniqueDescription, Object data) { traceDataInternal(9, parentClassP, parentClassName, methodSignature, uniqueDescription, data); }
/*      */   public static void data(int methodTraceLevel, Object parentClassP, String parentClassName, String methodSignature, String uniqueDescription, Object data) { traceDataInternal(methodTraceLevel, parentClassP, parentClassName, methodSignature, uniqueDescription, data); }
/*      */   public static void data(Object parentClassP, String methodSignature, String uniqueDescription, Object data) { traceDataInternal(9, parentClassP, null, methodSignature, uniqueDescription, data); }
/*      */   public static void traceData(String parentClassName, String methodSignature, String uniqueDescription, Object data) { traceDataInternal(9, null, parentClassName, methodSignature, uniqueDescription, data); }
/*  465 */   static { nullTracer = new NullTracer();
/*  466 */     functionalTracer = nullTracer;
/*      */     
/*  468 */     Object errorStreamObject = AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/*      */             try {
/*  473 */               return System.getProperty("com.ibm.msg.client.commonservices.trace.errorStream");
/*      */             }
/*  475 */             catch (AccessControlException ace) {
/*  476 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*      */     
/*  481 */     if (errorStreamObject != null && errorStreamObject instanceof String) {
/*  482 */       String stream = (String)errorStreamObject;
/*  483 */       if (stream.equalsIgnoreCase("System.err")) {
/*  484 */         errorStream = System.err;
/*      */       }
/*  486 */       else if (stream.equalsIgnoreCase("System.out")) {
/*  487 */         errorStream = System.out;
/*      */       } else {
/*      */         
/*      */         try {
/*  491 */           FileOutputStream fos = new FileOutputStream(stream, true);
/*  492 */           errorStream = new PrintStream(fos, true, Charset.defaultCharset().name());
/*      */         }
/*  494 */         catch (Throwable e) {
/*  495 */           errorStream = System.err;
/*  496 */           errorStream.println("Failed to open requested error stream " + e.getMessage());
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/*  502 */       errorStream = System.err;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  511 */     Object o = AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/*      */             try {
/*  516 */               return System.getProperty("com.ibm.msg.client.commonservices.trace.startup");
/*      */             }
/*  518 */             catch (AccessControlException ace) {
/*  519 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*  523 */     if (o != null && ((String)o).equalsIgnoreCase("TRUE")) {
/*      */       
/*  525 */       String s = "";
/*  526 */       s.toString();
/*  527 */       startupTracing = true;
/*  528 */       isOn = true;
/*  529 */       currentTracer = StartupTracer.getInstance();
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/*  538 */       currentTracer = nullTracer;
/*      */     } 
/*      */ 
/*      */     
/*  542 */     currentlyTracing = new ThreadLocal<Boolean>()
/*      */       {
/*      */         public Boolean initialValue()
/*      */         {
/*  546 */           return Boolean.FALSE;
/*      */         }
/*      */       };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  555 */     functionalTracer = currentTracer;
/*      */ 
/*      */ 
/*      */     
/*  559 */     lineSeparator = PropertyStore.line_separator;
/*      */     
/*      */     try {
/*  562 */       initialize();
/*      */     }
/*  564 */     catch (CSIException cSIException) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3341 */     dynamicTraceControl = null;
/* 3342 */     dynamicTraceControlName = null; } public static void data(String parentClassName, String methodSignature, String uniqueDescription, Object data) { traceDataInternal(9, null, parentClassName, methodSignature, uniqueDescription, data); } public static void traceData(Object parentClassP, String uniqueDescription, Object data) { traceDataInternal(9, parentClassP, null, "", uniqueDescription, data); } public static void data(Object parentClassP, String uniqueDescription, Object data) { traceDataInternal(9, parentClassP, null, "", uniqueDescription, data); } public static void traceData(String parentClassName, String uniqueDescription, Object data) { traceDataInternal(9, null, parentClassName, "", uniqueDescription, data); } public static void data(String parentClassName, String uniqueDescription, Object data) { traceDataInternal(9, null, parentClassName, "", uniqueDescription, data); } private static void traceDataInternal(int level, Object parentClassP, String parentClassName, String methodSignature, String uniqueDescription, Object data) { Object parentClass = parentClassP; if (isOn && traceable()) try { if (isClassTraced(parentClass, parentClassName)) { if (parentClass == null || parentClass instanceof String) parentClass = getCachedClassObject(parentClassName);  currentTracer.traceData(level, parentClass, parentClassName, methodSignature, uniqueDescription, data); }  } catch (Throwable t) { endTracing(); catchBlock("com.ibm.msg.client.commonservices.trace.Trace", "traceDataInternal", t); HashMap<String, Object> data1 = new HashMap<>(); data1.put("Reason", "Data trace threw exception"); data1.put("exception", t); ffst("com.ibm.msg.client.commonservices.trace.Trace", "traceDataInternal(Object, String, String, Object)", "3", data1, (Class)CSIException.class); } finally { endTracing(); }   } public static void catchBlock(Object parentClassP, String parentClassName, String methodSignature, Throwable thrown, int exitIndex) { catchBlockInternal(parentClassP, parentClassName, methodSignature, thrown, exitIndex); } public static void catchBlock(Object parentClassP, String parentClassName, String methodSignature, Throwable thrown) { catchBlockInternal(parentClassP, parentClassName, methodSignature, thrown, -1); } public static void catchBlock(String parentClassName, String methodSignature, Throwable thrown) { catchBlockInternal(null, parentClassName, methodSignature, thrown, -1); } public static void catchBlock(String parentClassName, String methodSignature, Throwable thrown, int exitIndex) { catchBlockInternal(null, parentClassName, methodSignature, thrown, exitIndex); } private static void catchBlockInternal(Object parentClassP, String parentClassName, String methodSignature, Throwable thrown, int exitIndex) { Object parentClass = parentClassP; if (isOn && traceable()) try { if (isClassTraced(parentClassP, parentClassName)) { if (parentClass == null || parentClass instanceof String) parentClass = getCachedClassObject(parentClassName);  currentTracer.catchBlock(1, parentClass, parentClassName, methodSignature, thrown, exitIndex); }  } catch (Throwable t) { HashMap<String, Object> data = new HashMap<>(); data.put("Reason", "Catch trace threw exception"); data.put("exception", t); ffst("com.ibm.msg.client.commonservices.trace.Trace", "catchBlockInternal(Object, String, Throwable, int)", "4", data, (Class)CSIException.class); } finally { endTracing(); }   } private static Object getCachedClassObject(String parentClassName) { Class<?> clazz = null; if (parentClassName != null) { clazz = cachedClassObjects.get(parentClassName); if (clazz == null) try { clazz = Class.forName(parentClassName); cachedClassObjects.put(parentClassName, clazz); } catch (ClassNotFoundException e) { cachedClassObjects.put(parentClassName, NullClass.class); } catch (NullPointerException nullPointerException) {}  }  return (clazz != NullClass.class) ? clazz : null; } public static void throwing(Object parentClassP, String parentClassName, String methodSignature, Throwable thrown, int exitIndex) { throwingInternal(parentClassP, parentClassName, methodSignature, thrown, exitIndex); } public static void throwing(Object parentClassP, String parentClassName, String methodSignature, Throwable thrown) { throwingInternal(parentClassP, parentClassName, methodSignature, thrown, -1); } public static void throwing(String parentClassName, String methodSignature, Throwable thrown) { throwingInternal(null, parentClassName, methodSignature, thrown, -1); } public static void throwing(String parentClassName, String methodSignature, Throwable thrown, int exitIndex) { throwingInternal(null, parentClassName, methodSignature, thrown, exitIndex); } private static void throwingInternal(Object parentClassP, String parentClassName, String methodSignature, Throwable thrown, int exitIndex) { Object parentClass = parentClassP; if (isOn && traceable()) try { if (isClassTraced(parentClassP, parentClassName)) { if (parentClass == null || parentClass instanceof String) parentClass = getCachedClassObject(parentClassName);  currentTracer.throwing(1, parentClass, parentClassName, methodSignature, thrown, exitIndex); }  } catch (Throwable t) { endTracing(); catchBlock("com.ibm.msg.client.commonservices.trace.Trace", "throwingInternal", t); HashMap<String, Object> data = new HashMap<>(); data.put("Reason", "Throw trace threw exception"); data.put("exception", t); ffst("com.ibm.msg.client.commonservices.trace.Trace", "throwingInternal(Object, String, Throwable, int)", "5", data, (Class)CSIException.class); } finally { endTracing(); }   } public static void finallyBlock(Object parentClassP, String parentClassName, String methodSignature, int exitIndex) { finallyBlockInternal(parentClassP, parentClassName, methodSignature, exitIndex); } public static void finallyBlock(Object parentClassP, String parentClassName, String methodSignature) { finallyBlockInternal(parentClassP, parentClassName, methodSignature, -1); } public static void finallyBlock(String parentClassName, String methodSignature) { finallyBlockInternal(null, parentClassName, methodSignature, -1); }
/*      */   public static void finallyBlock(String parentClassName, String methodSignature, int exitIndex) { finallyBlockInternal(null, parentClassName, methodSignature, exitIndex); }
/*      */   private static void finallyBlockInternal(Object parentClassP, String parentClassName, String methodSignature, int exitIndex) { Object parentClass = parentClassP; if (isOn && traceable()) try { if (isClassTraced(parentClassP, parentClassName)) { if (parentClass == null || parentClass instanceof String) parentClass = getCachedClassObject(parentClassName);  currentTracer.finallyBlock(8, parentClass, parentClassName, methodSignature, exitIndex); }  } catch (Throwable t) { endTracing(); catchBlock("com.ibm.msg.client.commonservices.trace.Trace", "finallyBlockInternal", t); HashMap<String, Object> data = new HashMap<>(); data.put("Reason", "Finally trace threw exception"); data.put("exception", t); ffst("com.ibm.msg.client.commonservices.trace.Trace", "finallyBlockInternal(Object, String, int)", "6", data, (Class)CSIException.class); } finally { endTracing(); }   }
/*      */   static class ThreadLocalStorage {
/*      */     public boolean insideFFST; }
/*      */   private static ThreadLocalStorage getTraceTls() { ThreadLocalStorage threadLocalStorage = tls.get(); if (threadLocalStorage == null) { threadLocalStorage = new ThreadLocalStorage(); tls.set(threadLocalStorage); }  return threadLocalStorage; }
/*      */   private static boolean ffstSafeToEnter() { boolean isSafeToEnter = false; if (tls != null) { ThreadLocalStorage threadLocalStorage = getTraceTls(); if (!threadLocalStorage.insideFFST) { threadLocalStorage.insideFFST = true; isSafeToEnter = true; }  }  return isSafeToEnter; }
/*      */   private static void leaveFFST() { ThreadLocalStorage threadLocalStorage = getTraceTls(); threadLocalStorage.insideFFST = false; }
/*      */   public static synchronized void ffst(Object sourceClass, String methodSignature, String probeID, HashMap<String, ? extends Object> dataP, Class<? extends Throwable> exceptionClassToThrow) { HashMap<String, ? extends Object> data = (dataP != null) ? dataP : new HashMap<>(); if (ffstSafeToEnter()) { try { ffstInternal(sourceClass, methodSignature, probeID, data, exceptionClassToThrow); } finally { leaveFFST(); }  } else { try { System.err.println("FFST called when already creating FFST."); if (sourceClass != null) System.err.println("sourceClass = " + sourceClass.getClass().getName());  if (methodSignature != null) System.err.println("method = " + methodSignature);  if (probeID != null) System.err.println("probeID = " + probeID);  (new Exception()).printStackTrace(System.err); } catch (Throwable throwable) {} }  }
/*      */   public static void ffst(String sourceClassName, String methodSignature, String probeID, HashMap<String, ? extends Object> data, Class<? extends Throwable> exceptionClassToThrow) { if (ffstSafeToEnter()) { try { ffstInternal(sourceClassName, methodSignature, probeID, data, exceptionClassToThrow); } finally { leaveFFST(); }  } else { try { System.err.println("FFST called when already creating FFST."); if (sourceClassName != null) System.err.println("sourceClass = " + sourceClassName);  if (methodSignature != null) System.err.println("method = " + methodSignature);  if (probeID != null) System.err.println("probeID = " + probeID);  (new Exception()).printStackTrace(); } catch (Throwable throwable) {} }  }
/*      */   public static synchronized String ffstAssertion(Object sourceClass, String methodSignature, String probeID, Object[] data2) { traceData(sourceClass, methodSignature, "Creating FFST Data"); HashMap<String, Object> data = new HashMap<>(data2.length); StringBuffer data2AsString = new StringBuffer(); for (int count1 = 0; count1 < data2.length; count1++) { if (data2[count1] instanceof String) { String nameValuePair = (String)data2[count1]; int splitIndex = nameValuePair.indexOf("=>"); if (splitIndex > 0) { String key = nameValuePair.substring(0, splitIndex); String value = nameValuePair.substring(splitIndex + 2); data.put(key, value); } else { data.put("Key" + count1, data2[count1]); }  } else { data.put("Key" + count1, data2[count1]); }  data2AsString.append(":").append(data2[count1]); }  String ffstName = null; try { ffst(sourceClass, methodSignature, probeID, data, (Class<? extends Throwable>)null); } catch (Throwable throwable) {} return sourceClass + " " + methodSignature + lineSeparator + "Assertion:" + probeID + " Data" + data2AsString + lineSeparator + "FDC File " + ffstName + " Written"; }
/*      */   private static void ffstInternal(Object sourceClass, String methodSignature, String probeID, HashMap<String, ? extends Object> data, Class<? extends Throwable> exceptionClassToThrow) { if (ffstParametersUnchecked) checkFfstParameters();  boolean suppress = false; if (ffstSuppressionProbeIDs.contains(probeID)) { suppress = true; if (isOn) traceData(sourceClass, methodSignature, "Suppressing FFST with Probe ID: " + probeID);  }  if (ffstSuppressionLevel != 0 && !suppress) { String key = sourceClass.getClass() + ":" + methodSignature + ":" + probeID; Integer callCount = receivedFfst.get(key); if (callCount != null) { if (ffstSuppressionLevel == -1) { suppress = true; } else if (ffstSuppressionLevel > 0) { if (callCount.intValue() % ffstSuppressionLevel != 0) suppress = true;  receivedFfst.put(key, Integer.valueOf(callCount.intValue() + 1)); }  } else { receivedFfst.put(key, Integer.valueOf(1)); }  }  String filename = "Suppressed"; if (!suppress) { if (isOn) traceData(sourceClass, methodSignature, "Creating FFST Data");  String ffstString = createFFSTString(sourceClass, methodSignature, probeID, data); if (isOn) traceData(sourceClass, methodSignature, "FFST string " + ffstString);  filename = functionalTracer.ffst(sourceClass, methodSignature, probeID, data, ffstString); if (doDumpOnFFST) dumpState(dumpLocation, compressDump);  }  if (exceptionClassToThrow != null) { Throwable ie = null; try { String message = "JMSCS0006: " + NLSServices.getMessage("JMSCS0006", new Object[] { filename }); Constructor<? extends Throwable> constructor = null; try { Constructor<? extends Throwable> constructorNew = exceptionClassToThrow.getConstructor(new Class[] { String.class }); constructor = constructorNew; if (isOn) traceData(sourceClass, methodSignature, "exceptionClassToThrow.getConstructor(new Class<?>[]{String.class})");  } catch (NoSuchMethodException nsme1) { try { Constructor<? extends Throwable> constructorNew = exceptionClassToThrow.getConstructor(new Class[] { Object.class }); constructor = constructorNew; if (isOn) traceData(sourceClass, methodSignature, "exceptionClassToThrow.getConstructor(new Class<?>[]{Object.class})");  } catch (NoSuchMethodException nsme2) { throw nsme1; }  }  ie = constructor.newInstance(new Object[] { message }); Object linkedException = data.get("exception"); if (linkedException == null) linkedException = data.get("EXCEPTION");  if (linkedException != null && linkedException instanceof Throwable) ie.initCause((Throwable)linkedException);  StackTraceElement[] stack = ie.getStackTrace(); if (stack.length > 0) { int stackShouldStartAt = 0; for (int count1 = 0; count1 < stack.length; count1++) { StackTraceElement elem = stack[count1]; if (elem.getClassName().equalsIgnoreCase("com.ibm.msg.client.commonservices.trace.Trace") && elem.getMethodName().equalsIgnoreCase("ffst")) { stackShouldStartAt = count1; break; }  }  int newStackSize = stack.length - stackShouldStartAt; if (newStackSize > 1) { StackTraceElement[] newStack = new StackTraceElement[newStackSize]; System.arraycopy(stack, stackShouldStartAt, newStack, 0, newStackSize); ie.setStackTrace(newStack); }  }  } catch (Exception e) { String message = "JMSCS0006: An internal problem occurred and diagnostic information for service was written to " + filename + ". It was not possible to throw the requested exception due to follow-on problems."; System.err.println(message); e.printStackTrace(System.err); System.err.flush(); }  if (ie != null) Thrower.sneakyThrow(filename, ie);  }  }
/* 3354 */   private static void initializeDynamicTrace() { traceController = new TraceControlImpl();
/*      */     try {
/* 3356 */       dynamicTraceControl = new StandardMBean((T)traceController, (Class)TraceControl.class);
/*      */     }
/* 3358 */     catch (NotCompliantMBeanException e) {
/* 3359 */       HashMap<String, Object> inserts = new HashMap<>();
/* 3360 */       inserts.put("TraceControlImpl", traceController);
/* 3361 */       inserts.put("TraceControl", TraceControl.class);
/* 3362 */       inserts.put("NotCompliantMBeanException", e);
/*      */       
/* 3364 */       StringBuffer message = new StringBuffer();
/* 3365 */       message.append("Failed to create StandardMBean for dynamic trace control\n");
/* 3366 */       for (Map.Entry<String, Object> insert : inserts.entrySet()) {
/* 3367 */         message.append(insert.getKey());
/* 3368 */         message.append(": ");
/* 3369 */         message.append(insert.getValue());
/* 3370 */         message.append("\n");
/*      */       } 
/*      */       
/* 3373 */       Log.logNLS("com.ibm.msg.client.commonservices.trace.Trace", "registerMBean(Object, String)", message.toString());
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 3378 */     dynamicTraceControlName = "TraceControl";
/* 3379 */     MonitorAgent.registerMBean(dynamicTraceControl, "CommonServices", dynamicTraceControlName); } private static String createFFSTString(Object sourceClass, String methodSignature, String probeID, HashMap<String, ? extends Object> data) { StringBuffer output = new StringBuffer(); String sourceClassName = ""; if (null != sourceClass) if (sourceClass instanceof String) { sourceClassName = (String)sourceClass; } else { sourceClassName = sourceClass.getClass().getName(); }   output.append(lineSeparator); output = getFFSTHeader(output, sourceClassName, methodSignature, probeID, data); output.append(lineSeparator); output = getVersion(output); output.append(lineSeparator); output = getCurrentPosition(output); output.append(lineSeparator); output = getFFSTPropertyStore(output); output.append(lineSeparator); output = getFFSTWorkQueueManager(output); output.append(lineSeparator); output = getRuntimeProperties(output); output.append(lineSeparator); output = getFFSTComponentManager(output); output.append(lineSeparator); output = getFFSTPropertyContext(output, sourceClass); output.append(lineSeparator); output = getFFSTProviderInformation(output, sourceClass); output.append(lineSeparator); return output.toString(); } private static StringBuffer getVersion(StringBuffer outputP) { StringBuffer output = outputP; ComponentManager compMgr = ComponentManager.getInstance(); Component[] components = compMgr.getComponents(null); if (components != null && components.length > 0) { newSubTitle(output, "Version information"); for (int c = 0; c < components.length; c++) { output.append(components[c].getTitle()).append(lineSeparator); output.append(components[c].getVersionString()).append(lineSeparator); Map<String, String> info = components[c].getImplementationInfo(true, true); if (info != null) { String cmvcLevel = info.get("CMVC"); if (cmvcLevel != null) output.append(cmvcLevel).append(lineSeparator);  }  output.append("Production").append(lineSeparator); HashMap<String, String> details = components[c].getDetails(); output = addFormattedHashMap(output, details); output.append(lineSeparator); }  } else { output.append("No Components Found").append(lineSeparator); }  return output; } private static StringBuffer addFormattedHashMap(StringBuffer output, HashMap<String, String> map) { int maxLength = 0; for (String key : map.keySet()) maxLength = Math.max(key.length(), maxLength);  for (Map.Entry<String, String> entry : map.entrySet()) { output.append(entry.getKey()); output.append(":"); int x = ((String)entry.getKey()).length(); while (x++ <= maxLength) output.append(' ');  output.append(entry.getValue()); output.append(lineSeparator); }  return output; } private static StringBuffer getCurrentPosition(StringBuffer output) { TableBuilder builder = new TableBuilder(); Exception e = new Exception(); StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw); e.printStackTrace(pw); pw.flush(); sw.flush(); builder.append("FFST Location", sw.toString()); newSubTitle(output, "Stack trace"); output.append("Stack trace to show the location of the FFST call").append(lineSeparator); output.append(builder.toStringBuilder()); return output; } private static StringBuffer newTitle(StringBuffer output, String title) { return newHeading(output, title, ' '); }
/*      */   private static StringBuffer newSubTitle(StringBuffer output, String title) { return newHeading(output, title, '-'); }
/*      */   private static StringBuffer newHeading(StringBuffer output, String title, char marker) { output.append(lineSeparator); int indent = 40 - Math.round(title.length() / 2.0F); int i; for (i = 0; i < indent; i++) output.append(' ');  output.append(title + lineSeparator); for (i = 0; i < indent; i++) output.append(' ');  for (i = 0; i < title.length(); i++) output.append(marker);  output.append(lineSeparator).append(lineSeparator); return output; }
/*      */   private static StringBuffer getFFSTHeader(StringBuffer output, String sourceClassString, String methodSignature, String probeID, HashMap<String, ? extends Object> data) { long time = System.currentTimeMillis(); newTitle(output, "FDCTitle"); output.append("Product          :- ProductName"); output.append(lineSeparator); output.append("Date/Time        :- " + getTimestamp(time)); output.append(lineSeparator); output.append("System time      :- " + Long.toString(time)); output.append(lineSeparator); output.append("Operating System :- " + PropertyStore.os_name); output.append(lineSeparator); output.append("UserID           :- " + PropertyStore.user_name); output.append(lineSeparator); output.append("Java Vendor      :- " + PropertyStore.java_vm_vendor); output.append(lineSeparator); output.append("Java Version     :- " + PropertyStore.java_vm_version); output.append(lineSeparator); output.append(lineSeparator); output.append("Source Class     :- " + sourceClassString); output.append(lineSeparator); output.append("Source Method    :- " + methodSignature); output.append(lineSeparator); output.append("ProbeID          :- " + probeID); output.append(lineSeparator); output.append("Thread           :- " + getCurrentThreadName()); output.append(lineSeparator); newSubTitle(output, "Data"); TableBuilder dataBuilder = new TableBuilder(); if (null == data) { output.append("Data        :- none"); } else { Set<? extends Map.Entry<String, ? extends Object>> entries = data.entrySet(); for (Map.Entry<String, ? extends Object> entry : entries) { String key = entry.getKey(); Object value = entry.getValue(); if (value instanceof Throwable) { Throwable e = (Throwable)value; TableBuilder exceptBuilder = new TableBuilder(); int exceptionDepth = 1; while (null != e) { exceptBuilder.append("Cause:" + exceptionDepth, e.toString()); String msg = e.getMessage(); if (null != msg) exceptBuilder.append("Message:" + exceptionDepth, msg);  StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw); e.printStackTrace(pw); pw.flush(); sw.flush(); exceptBuilder.append("StackTrace:" + exceptionDepth, sw.toString()); pw.close(); try { sw.close(); } catch (IOException iOException) {} e = e.getCause(); exceptionDepth++; }  dataBuilder.append(key, "ExceptionDepth is " + exceptionDepth); dataBuilder.append(key, exceptBuilder); continue; }  if (value instanceof byte[]) { StringBuffer buffer = new StringBuffer(); buffer.append("ByteArray len=").append(((byte[])value).length); buffer.append("  id=").append(value); buffer.append(lineSeparator); buffer.append(Utils.formatObjectDetailed(value)); dataBuilder.append(key, buffer); continue; }  if (value instanceof ByteBuffer) { ByteBuffer bb = (ByteBuffer)value; int position = bb.position(); bb.position(0); byte[] newarray = new byte[bb.capacity()]; bb.get(newarray, 0, bb.capacity()); bb.position(position); StringBuffer buffer = new StringBuffer(); buffer.append("ByteBuffer ").append(bb); buffer.append(lineSeparator); buffer.append(Utils.formatObjectDetailed(newarray)); dataBuilder.append(key, buffer); continue; }  dataBuilder.append(key, value); }  }  output.append(dataBuilder.toStringBuilder()); return output; }
/*      */   private static StringBuffer getFFSTPropertyStore(StringBuffer output) { newSubTitle(output, "Property Store Contents"); String displayJvmPropertiesName = "com.ibm.msg.client.commonservices.trace.displayJvmProperties"; PropertyStore.register(displayJvmPropertiesName, true); boolean doDisplayJvmProperties = PropertyStore.getBooleanPropertyObject(displayJvmPropertiesName).booleanValue(); if (!doDisplayJvmProperties) { output.append("Property store contents not displayed due to user configuration").append(lineSeparator); return output; }  output.append("All currently set properties").append(lineSeparator); HashMap<String, Object> properties = PropertyStore.getAll(); Set<Map.Entry<String, Object>> entries = properties.entrySet(); TableBuilder builder = new TableBuilder(); for (Map.Entry<String, Object> entry : entries) { if (propertyShouldBeMasked(entry.getKey())) { builder.append(entry.getKey(), "********"); continue; }  Object value = entry.getValue(); builder.append(entry.getKey(), (value == null) ? "<null>" : value.toString()); }  output.append(builder.toStringBuilder()); return output; }
/*      */   public static boolean propertyShouldBeMasked(String propertyName) { if (blacklistDisplayPropertyNameSet.contains(propertyName)) return true;  if (propertyName.toLowerCase().contains("password")) return true;  return false; }
/*      */   private static StringBuffer getFFSTPropertyContext(StringBuffer output, Object sourceClass) { if (sourceClass instanceof Map) { newSubTitle(output, "FFST Object Properties"); output.append("All currently set properties").append(lineSeparator); Map<String, Object> properties = (Map<String, Object>)sourceClass; TableBuilder builder = new TableBuilder(); Set<Map.Entry<String, Object>> entries = properties.entrySet(); for (Map.Entry<String, Object> entry : entries) { Object value = entry.getValue(); builder.append(entry.getKey(), (value == null) ? "<null>" : value); }  output.append(builder.toStringBuilder()); }  return output; }
/*      */   private static StringBuffer getFFSTComponentManager(StringBuffer output) { newSubTitle(output, "Component Manager Contents"); ComponentManager compMgr = ComponentManager.getInstance(); if (compMgr != null) { Component[] csiComponents = null; try { csiComponents = compMgr.getComponents("CSI", null); } catch (CSIException cSIException) {} Component[] mpiComponents = null; try { mpiComponents = compMgr.getComponents("MPI", null); } catch (CSIException cSIException) {} output.append("Common Services Components:").append(lineSeparator); int i; for (i = 0; csiComponents != null && i < csiComponents.length; i++) { HashMap<String, String> properties = csiComponents[i].getDetails(); TableBuilder builder = new TableBuilder(); Set<Map.Entry<String, String>> entries = properties.entrySet(); for (Map.Entry<String, String> entry : entries) { String value = entry.getValue(); builder.append(entry.getKey(), (value == null) ? "<null>" : value); }  output.append(builder.toStringBuilder()).append(lineSeparator).append(lineSeparator); }  output.append("Messaging Provider Components:").append(lineSeparator); for (i = 0; mpiComponents != null && i < mpiComponents.length; i++) { HashMap<String, String> properties = mpiComponents[i].getDetails(); TableBuilder builder = new TableBuilder(); Set<Map.Entry<String, String>> entries = properties.entrySet(); for (Map.Entry<String, String> entry : entries) { String value = entry.getValue(); builder.append(entry.getKey(), (value == null) ? "<null>" : value); }  output.append(builder.toStringBuilder()).append(lineSeparator).append(lineSeparator); }  }  return output; }
/* 3387 */   public static String getTraceFileName() { return (functionalTracer == null) ? "<none>" : functionalTracer.getOutputFileName(); }
/*      */   private static StringBuffer getFFSTProviderInformation(StringBuffer output, Object sourceClass) { if (providerFFSTInfo != null) { String providerOutput = providerFFSTInfo.providerInformation(sourceClass); if (providerOutput != null) { newSubTitle(output, "Provider Specific Information"); output.append(providerOutput); }  }  return output; }
/*      */   private static StringBuffer getFFSTWorkQueueManager(StringBuffer output) { newSubTitle(output, "WorkQueueMananger Contents"); TableBuilder builder = new TableBuilder(); try { builder.append("Current ThreadPool size", Integer.toString(WorkQueueManager.getCurrentThreadPoolSize())); } catch (CSIException e) { builder.append("unavailable - ", e.toString()); }  builder.append("Maximum ThreadPool size", Integer.toString(WorkQueueManager.getMaxThreadPoolSize())); builder.append("Maintain ThreadPool size", Boolean.toString(WorkQueueManager.getMaintainThreadPoolSize())); builder.append("ThreadPool inactive timeout", Long.toString(WorkQueueManager.getPoolInactiveTimeout())); output.append(builder.toStringBuilder()); return output; }
/*      */   public static void initialize() throws CSIException { if (!initialized) try { if (!CommonServices.isInitialized() || !CommonServices.isModuleInitialized(4)) { String msg = NLSServices.getMessage("JMSCS0002"); CSIException thrown = new CSIException(msg); throw thrown; }  registerConfigurationProperties(); initializeTraceFiltering(); initializeDynamicTrace(); allDumpableComponents.add(new PropertyDumper()); allDumpableComponents.add(new VersionDumper()); allDumpableComponents.add(new FFSTDumper()); allDumpableComponents.add(new ThreadDumper()); functionalTracer = CommonServices.getTrace(); initialized = true; ComponentManager.setUseStartupTrace(false); PropertyStore.register("com.ibm.msg.client.commonservices.trace.level", 9L, Long.valueOf(-2147483648L), Long.valueOf(2147483647L)); int userTraceLevel = PropertyStore.getLongPropertyObject("com.ibm.msg.client.commonservices.trace.level").intValue(); setTraceLevel(userTraceLevel); PropertyStore.register("com.ibm.msg.client.commonservices.trace.status", "OFF"); String traceOn = PropertyStore.getStringProperty("com.ibm.msg.client.commonservices.trace.status"); if (null != traceOn && traceOn.equalsIgnoreCase("ON")) { setOn(true); } else { setOn(false); }  } catch (CSIException csie) { if (!listening) { if (startupTracing) { functionalTracer = StartupTracer.getInstance(); } else { functionalTracer = new NullTracer(); }  CSIListener listener = new CSIListener() {
/*      */               public void onCSIInitialize() { try { Trace.initialize(); Trace.listening = false; CommonServices.removeCSIListener(this); ComponentManager.setUseStartupTrace(false); PropertyStore.register("com.ibm.msg.client.commonservices.trace.status", "OFF"); String traceOn = PropertyStore.getStringProperty("com.ibm.msg.client.commonservices.trace.status"); if (null != traceOn && traceOn.equalsIgnoreCase("ON")) { Trace.setOn(true); } else { Trace.setOn(false); }  } catch (CSIException csie2) { try { if (CommonServices.isInitialized() && CommonServices.isModuleInitialized(4)) { HashMap<String, Object> hash = new HashMap<>(); hash.put("Exception", csie2); Trace.ffst(this, "onCSIInitialize", "Failed to initialize CSI from Trace listener", hash, (Class)CSIException.class); }  } catch (CSIException csie3) { if (Trace.isOn) Trace.data("com.ibm.msg.client.commonservices.trace.Trace", "onCSIInitialize()", "Failed to initialize CSI from Trace listener and check for PropertyStore initialisation ", csie3.getMessage());  }  }  }
/*      */             }; CommonServices.addCSIListener(listener); listening = true; }  throw csie; }   }
/*      */   private static String getTimestamp(long time) { return (new Date(time)).toString(); }
/*      */   private static StringBuffer getRuntimeProperties(StringBuffer output) { Runtime runtime = Runtime.getRuntime(); if (runtime != null) { newSubTitle(output, "Runtime properties"); TableBuilder properties = new TableBuilder(); properties.append("Available processors", "" + runtime.availableProcessors()); properties.append("Total memory in bytes (now)", "" + runtime.totalMemory()); properties.append("Free memory in bytes (now)", "" + runtime.freeMemory()); properties.append("Max memory in bytes", "" + runtime.maxMemory()); output.append(properties.toStringBuilder()); return output; }  return output; }
/*      */   private static String getCurrentThreadName() { Map<String, Object> values = AccessController.<Map<String, Object>>doPrivileged(new PrivilegedAction<Map<String, Object>>() {
/*      */           public Map<String, Object> run() { HashMap<String, Object> map = new HashMap<>(); Thread thread = Thread.currentThread(); map.put("Thread", thread); map.put("ThreadName", thread.getName()); map.put("Priority", Integer.valueOf(thread.getPriority())); map.put("ThreadGroup", thread.getThreadGroup().getName()); map.put("ContextClassLoader", thread.getContextClassLoader()); return map; }
/*      */         }); String ccl = "<ContextClassLoader is null>"; ClassLoader cl = (ClassLoader)values.get("ContextClassLoader"); if (cl != null) ccl = cl.toString();  StringBuffer sb = new StringBuffer(32); sb.append("name="); sb.append(values.get("ThreadName")); sb.append(" priority="); sb.append(values.get("Priority")); sb.append(" group="); sb.append(values.get("ThreadGroup")); sb.append(" ccl="); sb.append(ccl); return sb.toString(); }
/*      */   public static void traceInfo(Object parentClassP, String methodSignature, String uniqueDescription, Object data) { traceInfoInternal(parentClassP, null, methodSignature, uniqueDescription, data); }
/*      */   public static void traceInfo(Object parentClassP, String parentClassName, String methodSignature, String uniqueDescription, Object data) { traceInfoInternal(parentClassP, parentClassName, methodSignature, uniqueDescription, data); }
/* 3400 */   public static void traceInfo(String parentClassName, String methodSignature, String uniqueDescription, Object data) { traceInfoInternal(null, parentClassName, methodSignature, uniqueDescription, data); } private static void traceInfoInternal(Object parentClassP, String parentClassName, String methodSignature, String uniqueDescription, Object data) { Object parentClass = parentClassP; if (isOn && traceable()) try { if (isClassTraced(parentClass, parentClassName)) { if (parentClass == null || parentClass instanceof String) parentClass = getCachedClassObject(parentClassName);  currentTracer.traceData(6, parentClass, parentClassName, methodSignature, uniqueDescription, data); }  } catch (Throwable t) { endTracing(); catchBlock("com.ibm.msg.client.commonservices.trace.Trace", "traceInfoInternal", t); HashMap<String, Object> data1 = new HashMap<>(); data1.put("Reason", "Data trace threw exception"); data1.put("exception", t); ffst("com.ibm.msg.client.commonservices.trace.Trace", "traceInfoInternal(Object, String, String, Object)", "3", data1, (Class)CSIException.class); } finally { endTracing(); }   } public static void traceWarning(Object parentClassP, String methodSignature, String uniqueDescription, Object data) { traceWarningInternal(parentClassP, null, methodSignature, uniqueDescription, data); } public static void traceWarning(Object parentClassP, String parentClassName, String methodSignature, String uniqueDescription, Object data) { traceWarningInternal(parentClassP, parentClassName, methodSignature, uniqueDescription, data); } public static String dumpState(String dumpLocation, boolean compress) { Date now = new Date();
/* 3401 */     SimpleDateFormat dateForFileName = new SimpleDateFormat("yyyyMMdd.kkmmss.SSS0");
/* 3402 */     SimpleDateFormat dateForHeader = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss z");
/* 3403 */     SimpleDateFormat UTCdateForHeader = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss z");
/* 3404 */     UTCdateForHeader.setTimeZone(TimeZone.getTimeZone("UTC"));
/* 3405 */     File outputFile = null;
/* 3406 */     boolean unique = false;
/* 3407 */     for (int counter = 1; !unique; counter++) {
/* 3408 */       String filename = String.format("mqClientDump.%s.%04d.%s", new Object[] { dateForFileName.format(now), Integer.valueOf(counter), compress ? "gz" : "txt" });
/* 3409 */       outputFile = new File(dumpLocation, filename);
/* 3410 */       unique = !outputFile.exists();
/*      */     } 
/*      */     
/* 3413 */     PrintWriter pw = null;
/*      */     try {
/* 3415 */       OutputStream outStream = new FileOutputStream(outputFile);
/* 3416 */       if (compress) {
/* 3417 */         outStream = new GZIPOutputStream(outStream);
/*      */       }
/* 3419 */       pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outStream, Charset.defaultCharset())));
/* 3420 */       pw.format("IBM MQ Java/JMS client dump @ %s (%s)%n", new Object[] { dateForHeader.format(now), UTCdateForHeader.format(now) });
/* 3421 */       pw.println();
/* 3422 */       pw.println("NOTE: This dump is a snapshot of a running process - no threads have been suspended, so data may be inconsistent");
/* 3423 */       pw.println();
/*      */       
/* 3425 */       ArrayList<DumpableComponent> tempList = (ArrayList<DumpableComponent>)allDumpableComponents.clone();
/* 3426 */       for (DumpableComponent dumpableComponent : tempList) {
/* 3427 */         writeComponentDump(pw, dumpableComponent);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3432 */       ArrayList<DumpableComponent> repeatTempList = (ArrayList<DumpableComponent>)allDumpableComponents.clone();
/* 3433 */       for (DumpableComponent dumpableComponent : repeatTempList) {
/*      */         
/* 3435 */         if (!tempList.contains(dumpableComponent)) {
/* 3436 */           writeComponentDump(pw, dumpableComponent);
/*      */         }
/*      */       } 
/*      */       
/* 3440 */       pw.println("[End of Dump]");
/*      */     }
/* 3442 */     catch (IOException e) {
/* 3443 */       if (isOn) {
/* 3444 */         catchBlock("com.ibm.mq.jms.MQJMSASIVT", "simulateJavaCore()", e, 1);
/*      */       }
/*      */     } finally {
/*      */       
/* 3448 */       if (pw != null) {
/* 3449 */         pw.close();
/*      */       }
/*      */     } 
/*      */     
/* 3453 */     return outputFile.getAbsolutePath(); }
/*      */   public static void traceWarning(String parentClassName, String methodSignature, String uniqueDescription, Object data) { traceWarningInternal(null, parentClassName, methodSignature, uniqueDescription, data); } private static void traceWarningInternal(Object parentClassP, String parentClassName, String methodSignature, String uniqueDescription, Object data) { Object parentClass = parentClassP; if (isOn && traceable()) try { if (parentClass == null || parentClass instanceof String) parentClass = getCachedClassObject(parentClassName);  currentTracer.traceData(3, parentClass, parentClassName, methodSignature, uniqueDescription, data); } catch (Throwable t) { endTracing(); catchBlock("com.ibm.msg.client.commonservices.trace.Trace", "traceDataInternal", t); HashMap<String, Object> data1 = new HashMap<>(); data1.put("Reason", "Data trace threw exception"); data1.put("exception", t); ffst("com.ibm.msg.client.commonservices.trace.Trace", "traceDataInternal(Object, String, String, Object)", "3", data1, (Class)CSIException.class); } finally { endTracing(); }   } public static void registerFFSTInfo(TraceFFSTInfo providerFFST) { providerFFSTInfo = providerFFST; } public static void registerDumpableComponent(DumpableComponent dumpable) { allDumpableComponents.add(dumpable); } private static void registerConfigurationProperties() { productProps = CommonServices.getProductization(); PropertyStore.register("com.ibm.msg.client.commonservices.trace.outputName", (String)productProps.get("TraceFilename")); PropertyStore.register("com.ibm.msg.client.commonservices.trace.include", "ALL"); PropertyStore.register("com.ibm.msg.client.commonservices.trace.exclude", "NONE"); PropertyStore.register("com.ibm.msg.client.commonservices.trace.searchString", ""); PropertyStore.register("com.ibm.msg.client.commonservices.trace.maxBytes", -1L, Long.valueOf(-1L), Long.valueOf(2147483647L)); PropertyStore.register("com.ibm.msg.client.commonservices.trace.limit", 0L, Long.valueOf(0L), Long.valueOf(2147483647L)); PropertyStore.register("com.ibm.msg.client.commonservices.trace.count", 1L, Long.valueOf(0L), Long.valueOf(2147483647L)); PropertyStore.register("com.ibm.msg.client.commonservices.trace.parameter", true); PropertyStore.register("com.ibm.msg.client.commonservices.trace.compress", false); PropertyStore.register("com.ibm.msg.client.commonservices.trace.traceFormatter", null); PropertyStore.register("com.ibm.msg.client.commonservices.trace.traceHandler", ""); PropertyStore.register("com.ibm.msg.client.commonservices.trace.append", true); } private static void initializeTraceFiltering() { rootNode = new PackageNode(null, null); rootNode.setIncluded(true); if (PropertyStore.wasOverridden("com.ibm.msg.client.commonservices.trace.include", null) || PropertyStore.wasOverridden("com.ibm.msg.client.commonservices.trace.exclude", null)) { filterTrace = true; String includedPackages = PropertyStore.getStringProperty("com.ibm.msg.client.commonservices.trace.include"); StringBuilder excludedPackages = new StringBuilder(PropertyStore.getStringProperty("com.ibm.msg.client.commonservices.trace.exclude")); String[] defaultExclusions = "com.ibm.mq.headers;com.ibm.mq.pcf".split(";"); for (String pkg : includedPackages.split(";")) { for (int i = 0; i < defaultExclusions.length; i++) { if (pkg.equals(defaultExclusions[i])) { defaultExclusions[i] = null; break; }  }  }  for (String defaultExclusion : defaultExclusions) { if (defaultExclusion != null) { excludedPackages.append(';'); excludedPackages.append(defaultExclusion); }  }  addPackageListToTree(excludedPackages.toString(), false); addPackageListToTree(includedPackages, true); } else { addPackageListToTree("com.ibm.mq.headers;com.ibm.mq.pcf", false); filterTrace = true; }  PropertyStore.PropertyListener traceFilterListener = new PropertyStore.PropertyListener() { public void onUpdate(String propertyName, Object newValue) { if (newValue instanceof String) if (propertyName.equals("com.ibm.msg.client.commonservices.trace.include")) { Trace.addPackageListToTree((String)newValue, true); } else if (propertyName.equals("com.ibm.msg.client.commonservices.trace.exclude")) { Trace.addPackageListToTree((String)newValue, false); }   } }
/*      */       ; List<String> propertyNames = new Vector<>(); propertyNames.add("com.ibm.msg.client.commonservices.trace.include"); propertyNames.add("com.ibm.msg.client.commonservices.trace.exclude"); PropertyStore.setPropertyListener(traceFilterListener, propertyNames); } private static void dumpTree(PackageNode node, int level) { System.err.format("%s%20.20s %s%n", new Object[] { "                ".substring(0, level * 2), node.getName(), node.isIncluded() ? "INCLUDED" : "EXCLUDED" }); for (PackageNode child : node.getChildren()) dumpTree(child, level + 1);  } public static void includePackage(String packageName) { addPackage(packageName, true); } public static void excludePackage(String packageName) { addPackage(packageName, false); } private static void addPackageListToTree(String packageList, boolean included) { if (packageList == null) return;  if (packageList.equals("ALL")) { rootNode.setIncluded(included); return; }  if (packageList.equals("NONE")) rootNode.setIncluded(!included);  String[] packages = packageList.split(";"); for (String packageName : packages) addPackage(packageName, included);  } private static void addPackage(String packageName, boolean included) { if (packageName == null) return;  if (packageName.equals("ALL")) { rootNode.setIncluded(included); return; }  if (packageName.equals("NONE")) { rootNode.setIncluded(!included); return; }  String[] levels = packageName.split("\\."); PackageNode currentNode = rootNode; if (currentNode == null) { rootNode = new PackageNode(null, null); currentNode = rootNode; }  PackageNode nextNode = null; boolean updateChildren = false; for (String level : levels) { if (level.equalsIgnoreCase("*")) { updateChildren = true; break; }  nextNode = currentNode.getChild(level); if (nextNode == null) { nextNode = new PackageNode(level, currentNode); currentNode.addChild(nextNode); nextNode.setIncluded(currentNode.isIncluded()); }  currentNode = nextNode; }  if (updateChildren) currentNode.destroyChildren();  currentNode.setIncluded(included); filterTrace = true; } public static boolean isClassTraced(Object classObject, String classString) { if (!filterTrace) return true;  try { String className = null; if (classObject != null) { className = classObject.getClass().getName(); } else if (classString != null) { className = classString; } else { return true; }  String[] names = className.split("\\."); PackageNode currentNode = rootNode; PackageNode nextNode = null; for (String name : names) { nextNode = currentNode.getChild(name); if (nextNode == null) return currentNode.isIncluded();  currentNode = nextNode; }  return currentNode.isIncluded(); } catch (Throwable t) { return true; }  } static class Thrower {
/*      */     private static Throwable t; private Thrower() throws Throwable { throw t; } public static synchronized void sneakyThrow(String filename, Throwable exceptionToThrow) { t = exceptionToThrow; try { Thrower.class.newInstance(); } catch (InstantiationException e) { String message = "JMSCS0006: An internal problem occurred. Please contact your service representative. Diagnostic information for service was written to " + filename + "."; System.err.println(message); e.printStackTrace(System.err); System.err.flush(); } catch (IllegalAccessException e) { String message = "JMSCS0006: An internal problem occurred. Please contact your service representative. Diagnostic information for service was written to " + filename + "."; System.err.println(message); e.printStackTrace(System.err); System.err.flush(); } finally { t = null; }  }
/* 3457 */   } private static void writeComponentDump(PrintWriter pw, DumpableComponent dumpableComponent) { String componentName = dumpableComponent.getComponentName();
/*      */     try {
/* 3459 */       pw.format("[%s Information]%n", new Object[] { componentName });
/* 3460 */       dumpableComponent.dump(pw, 1);
/*      */     }
/* 3462 */     catch (ConcurrentModificationException|NullPointerException e) {
/*      */       
/* 3464 */       pw.format("  %s encountered during processing - ignored%n", new Object[] { e.toString() });
/*      */     } finally {
/*      */       
/* 3467 */       pw.format("[End of %s Information]%n", new Object[] { componentName });
/* 3468 */       pw.println();
/*      */     }  }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void dumpStack(PrintWriter pw, int level, StackTraceElement[] stack) {
/* 3478 */     String prefix = buildPrefix(level);
/* 3479 */     if (stack == null) {
/* 3480 */       pw.format("%s[Stack not collected]%n", new Object[] { prefix });
/*      */     } else {
/*      */       
/* 3483 */       for (StackTraceElement element : stack) {
/* 3484 */         pw.format("%s   at %s.%s %s%n", new Object[] { prefix, element
/*      */               
/* 3486 */               .getClassName(), element
/* 3487 */               .getMethodName(), 
/* 3488 */               element.isNativeMethod() ? "(Native Method)" : String.format("(%s:%d)", new Object[] { element.getFileName(), Integer.valueOf(element.getLineNumber()) }) });
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String formatTimeStamp(long millis) {
/* 3498 */     Date timeStamp = new Date(millis);
/* 3499 */     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss z");
/* 3500 */     return formatter.format(timeStamp);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String buildPrefix(int level) {
/* 3508 */     char[] prefixArray = new char[level * 2];
/* 3509 */     Arrays.fill(prefixArray, ' ');
/* 3510 */     return new String(prefixArray);
/*      */   }
/*      */   
/*      */   private static class ThreadDumper implements DumpableComponent {
/*      */     private ThreadDumper() {}
/*      */     
/*      */     public void dump(final PrintWriter pw, final int level) {
/* 3517 */       final StringBuilder prefix = new StringBuilder();
/* 3518 */       for (int i = 0; i < level; i++) {
/* 3519 */         prefix.append("  ");
/*      */       }
/*      */       
/* 3522 */       AccessController.doPrivileged(new PrivilegedAction()
/*      */           {
/*      */             
/*      */             public Object run()
/*      */             {
/*      */               try {
/* 3528 */                 for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet())
/*      */                 {
/* 3530 */                   Thread thread = entry.getKey();
/* 3531 */                   pw.format("%s\"%s\" (id: %d,  state: %s) priority= %d, interrupted=%b, daemon=%b%n", new Object[] { this.val$prefix, thread
/*      */                         
/* 3533 */                         .getName(), 
/* 3534 */                         Long.valueOf(thread.getId()), thread
/* 3535 */                         .getState(), 
/* 3536 */                         Integer.valueOf(thread.getPriority()), 
/* 3537 */                         Boolean.valueOf(thread.isInterrupted()), 
/* 3538 */                         Boolean.valueOf(thread.isDaemon()) });
/*      */ 
/*      */                   
/* 3541 */                   StackTraceElement[] stack = entry.getValue();
/* 3542 */                   Trace.dumpStack(pw, level, stack);
/* 3543 */                   pw.println();
/*      */                 }
/*      */               
/* 3546 */               } catch (SecurityException se) {
/* 3547 */                 pw.format("Cannot dump Threads - %s%n", new Object[] { se.toString() });
/* 3548 */                 pw.println();
/*      */               } 
/* 3550 */               return null;
/*      */             }
/*      */           });
/*      */     }
/*      */ 
/*      */     
/*      */     public String getComponentName() {
/* 3557 */       return "Thread";
/*      */     }
/*      */   }
/*      */   
/*      */   private static class FFSTDumper implements DumpableComponent {
/*      */     private FFSTDumper() {}
/*      */     
/*      */     public void dump(PrintWriter pw, int level) {
/* 3565 */       if (Trace.providerFFSTInfo != null) {
/* 3566 */         Trace.providerFFSTInfo.dump(pw, level);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String getComponentName() {
/* 3573 */       return "FFST Provider";
/*      */     }
/*      */   }
/*      */   
/*      */   private static class VersionDumper implements DumpableComponent {
/*      */     private VersionDumper() {}
/*      */     
/*      */     public void dump(PrintWriter pw, int level) {
/* 3581 */       String prefix = Trace.buildPrefix(level);
/* 3582 */       ComponentManager compMgr = ComponentManager.getInstance();
/* 3583 */       Component[] components = compMgr.getComponents(null);
/* 3584 */       if (components != null) {
/* 3585 */         for (Component component : components) {
/* 3586 */           pw.format("%s%s Version %s%n", new Object[] { prefix, component.getTitle(), component.getVersionString() });
/* 3587 */           Map<String, String> info = component.getImplementationInfo(true);
/* 3588 */           if (info != null) {
/* 3589 */             String cmvcLevel = info.get("CMVC");
/* 3590 */             if (cmvcLevel != null) {
/* 3591 */               pw.format("%s  %s%n", new Object[] { prefix, cmvcLevel });
/*      */             }
/*      */           } 
/* 3594 */           HashMap<String, String> details = component.getDetails();
/* 3595 */           String jarLoc = details.get("Jar location");
/* 3596 */           pw.format("%s  %s%n%n", new Object[] { prefix, jarLoc });
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public String getComponentName() {
/* 3603 */       return "Version";
/*      */     }
/*      */   }
/*      */   
/*      */   private static class PropertyDumper
/*      */     implements DumpableComponent {
/*      */     private PropertyDumper() {}
/*      */     
/*      */     public void dump(PrintWriter pw, int level) {
/* 3612 */       HashMap<String, Object> properties = PropertyStore.getAll();
/* 3613 */       Set<Map.Entry<String, Object>> entries = properties.entrySet();
/* 3614 */       TableBuilder builder = new TableBuilder(level, false, false);
/* 3615 */       for (Map.Entry<String, Object> entry : entries) {
/* 3616 */         Object value = entry.getValue();
/* 3617 */         builder.append(entry.getKey(), (value == null) ? "<null>" : value.toString());
/*      */       } 
/*      */       
/* 3620 */       pw.println(builder.toString());
/*      */     }
/*      */ 
/*      */     
/*      */     public String getComponentName() {
/* 3625 */       return "Property";
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registerFFSTObject(Object object) {
/* 3634 */     if (providerFFSTInfo != null) {
/* 3635 */       providerFFSTInfo.registerObject(object);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void deRegisterFFSTObject(Object object) {
/* 3644 */     if (providerFFSTInfo != null) {
/* 3645 */       providerFFSTInfo.deRegisterObject(object);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void deRegisterDumpableObject(DumpableObject dumpable) {
/* 3654 */     if (providerFFSTInfo != null) {
/* 3655 */       providerFFSTInfo.deRegisterDumpable(dumpable);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registerDumpableObject(DumpableObject dumpable) {
/* 3663 */     if (providerFFSTInfo != null) {
/* 3664 */       providerFFSTInfo.registerDumpable(dumpable);
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
/*      */   public static Map<Object, Object> sanitizeMap(Map<? extends Object, ? extends Object> toSanitize, Object key, Object replacementValue) {
/*      */     Map<? extends Object, ? extends Object> map;
/* 3679 */     if (toSanitize == null) {
/* 3680 */       map = null;
/*      */     }
/* 3682 */     else if (toSanitize.containsKey(key)) {
/* 3683 */       map = new HashMap<>(toSanitize);
/* 3684 */       map.put(key, replacementValue);
/*      */     } else {
/*      */       
/* 3687 */       map = toSanitize;
/*      */     } 
/*      */     
/* 3690 */     return (Map)map;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<Object, Object> sanitizeMap(Map<? extends Object, ? extends Object> toSanitize, Map<? extends Object, ? extends Object> substitutions) {
/* 3700 */     Map<Object, Object> result = new HashMap<>(toSanitize);
/* 3701 */     if (toSanitize.size() <= substitutions.size()) {
/* 3702 */       for (Object key : toSanitize.keySet()) {
/* 3703 */         Object repl = substitutions.get(key);
/* 3704 */         if (repl != null) {
/* 3705 */           result.put(key, repl);
/*      */         }
/*      */       } 
/*      */     } else {
/*      */       
/* 3710 */       for (Map.Entry<? extends Object, ? extends Object> entr : substitutions.entrySet()) {
/* 3711 */         if (toSanitize.get(entr.getKey()) != null) {
/* 3712 */           result.put(entr.getKey(), entr.getValue());
/*      */         }
/*      */       } 
/*      */     } 
/* 3716 */     return result;
/*      */   }
/*      */   
/*      */   public static interface TraceHandler {
/*      */     void setOn(boolean param1Boolean);
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\trace\Trace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */