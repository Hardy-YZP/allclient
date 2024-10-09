/*      */ package com.ibm.mq.jmqi;
/*      */ 
/*      */ import com.ibm.mq.exits.MQCD;
/*      */ import com.ibm.mq.exits.MQCSP;
/*      */ import com.ibm.mq.exits.MQCXP;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Hmsg;
/*      */ import com.ibm.mq.jmqi.handles.HmsgImpl;
/*      */ import com.ibm.mq.jmqi.handles.PbyteBuffer;
/*      */ import com.ibm.mq.jmqi.handles.Phconn;
/*      */ import com.ibm.mq.jmqi.handles.Phmsg;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.handles.PtripletArray;
/*      */ import com.ibm.mq.jmqi.internal.Configuration;
/*      */ import com.ibm.mq.jmqi.internal.JmqiResource;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.mq.jmqi.internal.QueueManagerInfo;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.zrfp.Triplet;
/*      */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.commonservices.util.Cruise;
/*      */ import java.io.File;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.charset.Charset;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import javax.transaction.xa.XAException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JmqiEnvironment
/*      */ {
/*      */   private static final String JMQI_PACKAGE = "com.ibm.mq.jmqi";
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiEnvironment.java";
/*      */   
/*      */   static {
/*   75 */     if (Trace.isOn) {
/*   76 */       Trace.data("com.ibm.mq.jmqi.JmqiEnvironment", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiEnvironment.java");
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
/*   87 */   private HashMap<String, JmqiMQ> mqiCache = new HashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int UNDEFINED = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int LOCAL_SERVER = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int LOCAL_CLIENT = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int REMOTE = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int USE_WORKER_THREAD_XA = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static final int USE_WORKER_THREAD = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int DONT_USE_SHARED_HCONN = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int FORCE_USE_WORKER_THREAD = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int FORCE_DONT_USE_WORKER_THREAD = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int FORCE_DONT_USE_SHARED_HCONN = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int RRS_ASYNC_CONSUME_REQD = 32;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int INHERIT_RRS_CONTEXT = 64;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Map<?, ?> versionProperties;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean checkEnvVars = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JmqiThreadPoolFactory threadPoolFactory;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  168 */   private JmqiCodepage defaultCharset = null;
/*      */ 
/*      */   
/*      */   private Configuration configuration;
/*      */   
/*      */   private String productIdentifier;
/*      */   
/*  175 */   protected JmqiPropertyHandler properties = null;
/*      */ 
/*      */   
/*      */   public static final char CALLER_WMQ_V7_LEG = 'M';
/*      */ 
/*      */   
/*      */   public static final char CALLER_WMQ_V6_LEG = 'N';
/*      */ 
/*      */   
/*      */   public static final char CALLER_BASE_JAVA = 'B';
/*      */   
/*      */   public static final char CALLER_UNKNOWN = 'U';
/*      */   
/*      */   public static final char CALLER_WAS = 'W';
/*      */   
/*  190 */   private char caller = 'U';
/*      */ 
/*      */   
/*  193 */   public static final int OS_UNKNOWN = CSSystem.Platform.OS_UNKNOWN.ordinal();
/*      */   
/*  195 */   public static final int OS_ZSERIES = CSSystem.Platform.OS_ZSERIES.ordinal();
/*      */   
/*  197 */   public static final int OS_ISERIES = CSSystem.Platform.OS_ISERIES.ordinal();
/*      */   
/*  199 */   public static final int OS_WINDOWS = CSSystem.Platform.OS_WINDOWS.ordinal();
/*      */   
/*  201 */   public static final int OS_AIX = CSSystem.Platform.OS_AIX.ordinal();
/*      */   
/*  203 */   public static final int OS_HP = CSSystem.Platform.OS_HP.ordinal();
/*      */   
/*  205 */   public static final int OS_LINUX = CSSystem.Platform.OS_LINUX.ordinal();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int os;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  215 */   private static int MQ_EXIT_NAME_LENGTH = 0;
/*      */   
/*      */   private String defaultCharsetName;
/*      */   
/*  219 */   private static final Object getZosEnvLock = new Object();
/*      */   
/*      */   private static boolean zosEnvInitialised = false;
/*      */   
/*      */   private static boolean runningUnderIMS = false;
/*      */   
/*      */   private static boolean runningUnderCICS = false;
/*      */   private static boolean maybeUnderCICS = false;
/*      */   private static final String ZOS_BOOTSTRAP_LIBRARYNAME = "mqjbnd";
/*      */   
/*      */   static {
/*  230 */     if (Trace.isOn) {
/*  231 */       Trace.entry("com.ibm.mq.jmqi.JmqiEnvironment", "static()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  237 */     versionProperties = JmqiTools.getVersionProperties();
/*  238 */     if (Trace.isOn) {
/*  239 */       Trace.exit("com.ibm.mq.jmqi.JmqiEnvironment", "static()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static final String ZOS_BOOTSTRAP_LIBRARYNAME_64 = "mqjbnd64";
/*      */   
/*      */   private static final String ZOS_CICS_LIBRARYNAME = "mqjcics";
/*      */   
/*      */   private static final long psaTcbAddress = 540L;
/*      */   
/*      */   private static final int tcbEtcbOffset = 208;
/*      */   
/*      */   private static final int etcbTargetOffset = 20;
/*      */ 
/*      */   
/*      */   protected JmqiEnvironment(JmqiTraceHandlerAdapter trace, JmqiThreadPoolFactory threadPoolFactory, JmqiPropertyHandler propertyHandler) throws JmqiException {
/*  256 */     this(threadPoolFactory, propertyHandler);
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
/*      */   protected JmqiEnvironment(JmqiThreadPoolFactory threadPoolFactory, JmqiPropertyHandler propertyHandler) throws JmqiException {
/*  268 */     this.threadPoolFactory = threadPoolFactory;
/*  269 */     this.properties = propertyHandler;
/*  270 */     lookupDefaultCharset();
/*      */     
/*  272 */     this.configuration = new Configuration(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Configuration getConfiguration() {
/*  279 */     if (Trace.isOn) {
/*  280 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getConfiguration()", "getter", this.configuration);
/*      */     }
/*      */     
/*  283 */     return this.configuration;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JmqiCodepage getJmqiCodepage(String rule, String charsetName) {
/*  294 */     if (Trace.isOn) {
/*  295 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getJmqiCodepage(String,String)", new Object[] { rule, charsetName });
/*      */     }
/*      */     
/*  298 */     JmqiCodepage result = null;
/*  299 */     if (charsetName != null && charsetName.length() > 0) {
/*  300 */       JmqiCodepage cp = null;
/*      */       try {
/*  302 */         cp = JmqiCodepage.getJmqiCodepage(this, charsetName);
/*      */         
/*  304 */         if (cp == null) {
/*  305 */           UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException(charsetName);
/*  306 */           if (Trace.isOn) {
/*  307 */             Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getJmqiCodepage(String,String)", traceRet1);
/*      */           }
/*      */           
/*  310 */           throw traceRet1;
/*      */         }
/*      */       
/*      */       }
/*  314 */       catch (UnsupportedEncodingException e) {
/*  315 */         if (Trace.isOn) {
/*  316 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getJmqiCodepage(String,String)", e);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  322 */       if (cp != null) {
/*      */         
/*  324 */         getOperatingSystem();
/*  325 */         if (os == OS_ISERIES || os == OS_ZSERIES) {
/*  326 */           if (cp.isAscii) {
/*  327 */             if (Trace.isOn) {
/*  328 */               Trace.data(this, "getJmqiCodepage(String,String)", "Ascii charset wrong type for platform", charsetName);
/*      */             }
/*  330 */             cp = null;
/*      */           }
/*      */         
/*  333 */         } else if (!cp.isAscii) {
/*  334 */           if (Trace.isOn) {
/*  335 */             Trace.data(this, "getJmqiCodepage(String,String)", "Ebcdic charset wrong type for platform", charsetName);
/*      */           }
/*  337 */           cp = null;
/*      */         } 
/*      */       } 
/*      */       
/*  341 */       if (cp != null) {
/*  342 */         if (Trace.isOn) {
/*  343 */           Trace.data(this, "getJmqiCodepage(String,String)", rule, charsetName);
/*      */         }
/*  345 */         result = cp;
/*      */       } 
/*      */     } 
/*      */     
/*  349 */     if (Trace.isOn) {
/*  350 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getJmqiCodepage(String,String)", result);
/*      */     }
/*  352 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void lookupDefaultCharset() {
/*  359 */     if (Trace.isOn) {
/*  360 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "lookupDefaultCharset()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  367 */     String rule = "Charset.defaultCharset";
/*  368 */     this.defaultCharsetName = Charset.defaultCharset().name();
/*  369 */     this.defaultCharset = getJmqiCodepage(rule, this.defaultCharsetName);
/*      */ 
/*      */     
/*  372 */     if (this.defaultCharset == null) {
/*      */       try {
/*  374 */         rule = "Property: 'ibm.system.encoding'";
/*  375 */         this.defaultCharsetName = JmqiTools.getSystemProperty("ibm.system.encoding");
/*  376 */         this.defaultCharset = getJmqiCodepage(rule, this.defaultCharsetName);
/*      */       }
/*  378 */       catch (Exception e) {
/*  379 */         if (Trace.isOn) {
/*  380 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "lookupDefaultCharset()", e, 1);
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*  385 */     if (this.defaultCharset == null) {
/*      */       try {
/*  387 */         rule = "Property: 'sun.jnu.encoding'";
/*  388 */         this.defaultCharsetName = JmqiTools.getSystemProperty("sun.jnu.encoding");
/*  389 */         this.defaultCharset = getJmqiCodepage(rule, this.defaultCharsetName);
/*      */       }
/*  391 */       catch (Exception e) {
/*  392 */         if (Trace.isOn) {
/*  393 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "lookupDefaultCharset()", e, 2);
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*  398 */     if (this.defaultCharset == null) {
/*      */       try {
/*  400 */         if (os == OS_ISERIES) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  408 */           rule = "Property: 'os400.job.file.encoding'";
/*  409 */           this.defaultCharsetName = JmqiTools.getSystemProperty("os400.job.file.encoding");
/*      */         } else {
/*      */           
/*  412 */           rule = "Property: 'file.encoding'";
/*  413 */           this.defaultCharsetName = JmqiTools.getSystemProperty("file.encoding");
/*      */         } 
/*  415 */         this.defaultCharset = getJmqiCodepage(rule, this.defaultCharsetName);
/*      */       }
/*  417 */       catch (Exception e) {
/*  418 */         if (Trace.isOn) {
/*  419 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "lookupDefaultCharset()", e, 3);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  425 */     if (this.defaultCharset == null) {
/*  426 */       rule = "OS Name";
/*  427 */       if (os == OS_ISERIES) {
/*  428 */         this.defaultCharsetName = "IBM-500";
/*      */       }
/*  430 */       else if (os == OS_ZSERIES) {
/*  431 */         this.defaultCharsetName = "IBM-500";
/*      */       } else {
/*      */         
/*  434 */         this.defaultCharsetName = "ISO-8859-1";
/*      */       } 
/*  436 */       if (Trace.isOn) {
/*  437 */         Trace.data(this, "lookupDefaultCharset()", rule, this.defaultCharsetName);
/*      */       }
/*      */       try {
/*  440 */         this.defaultCharset = JmqiCodepage.getJmqiCodepage(this, this.defaultCharsetName);
/*      */         
/*  442 */         if (this.defaultCharset == null) {
/*  443 */           UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(this.defaultCharsetName);
/*  444 */           if (Trace.isOn) {
/*  445 */             Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "lookupDefaultCharset()", traceRet2, 1);
/*      */           }
/*      */           
/*  448 */           throw traceRet2;
/*      */         }
/*      */       
/*      */       }
/*  452 */       catch (UnsupportedEncodingException e) {
/*  453 */         if (Trace.isOn) {
/*  454 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "lookupDefaultCharset()", e, 4);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  461 */     if (this.defaultCharset == null) {
/*  462 */       String msg = JmqiResource.getString("MQJE117");
/*  463 */       HashMap<String, Object> ffstInfo = new HashMap<>();
/*  464 */       ffstInfo.put("rule", rule);
/*  465 */       ffstInfo.put("defaultCharsetName", this.defaultCharsetName);
/*  466 */       ffstInfo.put("Description", msg);
/*  467 */       Trace.ffst(this, "lookupDefaultCharset()", "01", ffstInfo, null);
/*  468 */       RuntimeException traceRet1 = new RuntimeException(msg);
/*  469 */       if (Trace.isOn) {
/*  470 */         Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "lookupDefaultCharset()", traceRet1, 2);
/*      */       }
/*      */       
/*  473 */       throw traceRet1;
/*      */     } 
/*      */     
/*  476 */     if (Trace.isOn) {
/*  477 */       Trace.data(this, "lookupDefaultCharset()", "DefaultCharsetName", (this.defaultCharsetName == null) ? "<null>" : this.defaultCharsetName);
/*      */     }
/*      */     
/*  480 */     if (Trace.isOn) {
/*  481 */       Trace.data(this, "lookupDefaultCharset()", "defaultCharset=", this.defaultCharset);
/*  482 */       Trace.data(this, "lookupDefaultCharset()", "defaultCharsetName=", this.defaultCharsetName);
/*      */     } 
/*      */     
/*  485 */     if (Trace.isOn) {
/*  486 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "lookupDefaultCharset()");
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
/*      */   public JmqiCodepage getNativeCharSet() {
/*  498 */     if (Trace.isOn) {
/*  499 */       Trace.data(this, "getNativeCharSet()", this.defaultCharset);
/*      */     }
/*  501 */     return this.defaultCharset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean esafDetected() {
/*  508 */     if (Trace.isOn) {
/*  509 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "esafDetected()");
/*      */     }
/*  511 */     String esaf = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*      */         {
/*      */           public String run()
/*      */           {
/*      */             try {
/*  516 */               return System.getProperty("com.ibm.ims.esaf");
/*      */             }
/*  518 */             catch (AccessControlException ace) {
/*  519 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*  523 */     Boolean result = Boolean.valueOf(!(esaf == null));
/*  524 */     if (Trace.isOn) {
/*  525 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "esafDetected()", result);
/*      */     }
/*  527 */     return result.booleanValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean cicsJVMServerDetected() {
/*  535 */     if (Trace.isOn) {
/*  536 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "cicsJVMServerDetected()");
/*      */     }
/*  538 */     String cicsJVMServer = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*      */         {
/*      */           public String run()
/*      */           {
/*      */             try {
/*  543 */               return System.getProperty("com.ibm.cics.jvmserver");
/*      */             }
/*  545 */             catch (AccessControlException ace) {
/*  546 */               return null;
/*      */             } 
/*      */           }
/*      */         });
/*  550 */     Boolean result = Boolean.valueOf(!(cicsJVMServer == null));
/*  551 */     if (Trace.isOn) {
/*  552 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "cicsJVMServerDetected()", result);
/*      */     }
/*  554 */     return result.booleanValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNativeCharSetName() {
/*  564 */     if (Trace.isOn) {
/*  565 */       Trace.data(this, "getNativeCharSetName()", this.defaultCharsetName);
/*      */     }
/*  567 */     return this.defaultCharsetName;
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
/*      */   public int getNativeCcsid() {
/*  581 */     int ccsid = this.defaultCharset.getCCSID();
/*  582 */     if (Trace.isOn) {
/*  583 */       Trace.data(this, "getNativeCcsid()", Integer.valueOf(ccsid));
/*      */     }
/*  585 */     return ccsid;
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
/*      */   @Deprecated
/*      */   public JmqiMQ getMQI(int id) throws JmqiException {
/*  604 */     if (Trace.isOn)
/*  605 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getMQI(int)", new Object[] {
/*  606 */             Integer.valueOf(id)
/*      */           }); 
/*  608 */     JmqiMQ traceRet1 = getMQI(id, 0);
/*      */     
/*  610 */     if (Trace.isOn) {
/*  611 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getMQI(int)", traceRet1);
/*      */     }
/*  613 */     return traceRet1;
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
/*      */   public JmqiMQ getMQI(int id, int options) throws JmqiException {
/*      */     String text;
/*      */     HashMap<String, Object> ffstInfo;
/*      */     JmqiException e;
/*  633 */     if (Trace.isOn)
/*  634 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getMQI(int,int)", new Object[] {
/*  635 */             Integer.valueOf(id), Integer.valueOf(options)
/*      */           }); 
/*  637 */     JmqiMQ mq = null;
/*  638 */     String name = null;
/*      */ 
/*      */     
/*  641 */     switch (id) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 0:
/*  649 */         if (CSSystem.currentPlatform().equals(CSSystem.Platform.OS_NSS)) {
/*  650 */           JmqiException jmqiException = new JmqiException(this, -1, null, 2, 2568, null);
/*      */ 
/*      */ 
/*      */           
/*  654 */           if (Trace.isOn) {
/*  655 */             Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getMQI(int,int)", jmqiException, 1);
/*      */           }
/*      */           
/*  658 */           throw jmqiException;
/*      */         } 
/*      */         
/*  661 */         name = "com.ibm.mq.jmqi.local.LocalServer";
/*      */         break;
/*      */       case 1:
/*  664 */         name = "com.ibm.mq.jmqi.local.LocalClient";
/*      */         break;
/*      */       case 2:
/*  667 */         name = "com.ibm.mq.jmqi.remote.api.RemoteFAP";
/*      */         break;
/*      */       default:
/*  670 */         text = "The id '" + id + "' was not valid";
/*      */         
/*  672 */         ffstInfo = new HashMap<>();
/*  673 */         ffstInfo.put("id", Integer.valueOf(id));
/*  674 */         ffstInfo.put("Description", text);
/*  675 */         Trace.ffst(this, "getMQI(int,int)", "01", ffstInfo, null);
/*  676 */         e = new JmqiException(this, -1, null, 2, 2195, null);
/*      */         
/*  678 */         if (Trace.isOn) {
/*  679 */           Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getMQI(int,int)", e, 2);
/*      */         }
/*  681 */         throw e;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  686 */     StringBuffer buffer = new StringBuffer();
/*  687 */     buffer.append(name);
/*  688 */     buffer.append(':');
/*  689 */     buffer.append(options);
/*  690 */     String key = buffer.toString();
/*      */ 
/*      */     
/*  693 */     mq = this.mqiCache.get(key);
/*  694 */     if (mq == null) {
/*  695 */       synchronized (this.mqiCache) {
/*  696 */         mq = this.mqiCache.get(key);
/*  697 */         if (mq == null) {
/*      */           
/*  699 */           mq = getInstance(name, options);
/*  700 */           this.mqiCache.put(key, mq);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  706 */     JmqiMQ traceRet1 = mq;
/*  707 */     if (Trace.isOn) {
/*  708 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getMQI(int,int)", traceRet1);
/*      */     }
/*  710 */     return traceRet1;
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
/*      */   private JmqiMQ getInstance(String name, int options) throws JmqiException {
/*      */     Class<?> mqiClass;
/*  723 */     if (Trace.isOn) {
/*  724 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", new Object[] { name, 
/*  725 */             Integer.valueOf(options) });
/*      */     }
/*  727 */     JmqiMQ mq = null;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  732 */       mqiClass = JmqiTools.dynamicLoadClass(this, name, getClass());
/*      */     }
/*  734 */     catch (ClassNotFoundException failedToLoadClass) {
/*  735 */       if (Trace.isOn) {
/*  736 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", failedToLoadClass, 1);
/*      */       }
/*      */ 
/*      */       
/*  740 */       HashMap<String, Object> ffstInfo = new HashMap<>();
/*  741 */       ffstInfo.put("name", name);
/*  742 */       ffstInfo.put("Exception Summary", JmqiTools.getExSumm(failedToLoadClass));
/*  743 */       ffstInfo.put("Description", "Failed to load");
/*  744 */       Trace.ffst(this, "getInstance(final String,int)", "01", ffstInfo, null);
/*      */       
/*  746 */       JmqiException e = new JmqiException(this, 9546, new String[] { JmqiTools.getExSumm(failedToLoadClass), null, JmqiTools.getFailingCall(failedToLoadClass) }, 2, 2195, failedToLoadClass);
/*      */       
/*  748 */       if (Trace.isOn) {
/*  749 */         Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", e, 1);
/*      */       }
/*      */       
/*  752 */       throw e;
/*      */     } 
/*      */ 
/*      */     
/*  756 */     Class<JmqiEnvironment> envClass = JmqiEnvironment.class;
/*  757 */     Class<int> clazz = int.class;
/*  758 */     Class<?>[] paramTypes = new Class[] { envClass, clazz };
/*      */     
/*      */     try {
/*  761 */       Constructor<?> constructor = mqiClass.getConstructor(paramTypes);
/*      */       
/*  763 */       Object[] params = { this, Integer.valueOf(options) };
/*      */       try {
/*  765 */         mq = (JmqiMQ)constructor.newInstance(params);
/*      */       }
/*  767 */       catch (IllegalArgumentException e2) {
/*  768 */         if (Trace.isOn) {
/*  769 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", e2, 2);
/*      */         }
/*      */ 
/*      */         
/*  773 */         JmqiException traceRet1 = new JmqiException(this, 9546, new String[] { JmqiTools.getExSumm(e2), null, JmqiTools.getFailingCall(e2) }, 2, 2195, e2);
/*      */         
/*  775 */         if (Trace.isOn) {
/*  776 */           Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", traceRet1, 2);
/*      */         }
/*      */         
/*  779 */         throw traceRet1;
/*      */       }
/*  781 */       catch (InstantiationException e2) {
/*  782 */         if (Trace.isOn) {
/*  783 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", e2, 3);
/*      */         }
/*      */         
/*  786 */         JmqiException traceRet2 = new JmqiException(this, 9546, new String[] { JmqiTools.getExSumm(e2), null, JmqiTools.getFailingCall(e2) }, 2, 2195, e2);
/*      */         
/*  788 */         if (Trace.isOn) {
/*  789 */           Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", traceRet2, 3);
/*      */         }
/*      */         
/*  792 */         throw traceRet2;
/*      */       }
/*  794 */       catch (IllegalAccessException e2) {
/*  795 */         if (Trace.isOn) {
/*  796 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", e2, 4);
/*      */         }
/*      */         
/*  799 */         JmqiException traceRet3 = new JmqiException(this, 9546, new String[] { JmqiTools.getExSumm(e2), null, JmqiTools.getFailingCall(e2) }, 2, 2195, e2);
/*      */         
/*  801 */         if (Trace.isOn) {
/*  802 */           Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", traceRet3, 4);
/*      */         }
/*      */         
/*  805 */         throw traceRet3;
/*      */       }
/*  807 */       catch (InvocationTargetException e2) {
/*  808 */         if (Trace.isOn) {
/*  809 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", e2, 5);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  814 */         Throwable cause = e2.getCause();
/*  815 */         if (cause == null) {
/*  816 */           cause = e2;
/*      */         }
/*  818 */         else if (cause instanceof JmqiException) {
/*  819 */           JmqiException traceRet2 = (JmqiException)cause;
/*  820 */           if (Trace.isOn) {
/*  821 */             Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", traceRet2, 5);
/*      */           }
/*      */           
/*  824 */           throw traceRet2;
/*      */         } 
/*      */ 
/*      */         
/*  828 */         JmqiException wrappingException = new JmqiException(this, 9546, new String[] { JmqiTools.getExSumm(cause), null, JmqiTools.getFailingCall(cause) }, 2, 2195, cause);
/*      */         
/*  830 */         if (Trace.isOn) {
/*  831 */           Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", wrappingException, 6);
/*      */         }
/*      */         
/*  834 */         throw wrappingException;
/*      */       }
/*      */     
/*  837 */     } catch (SecurityException e1) {
/*  838 */       if (Trace.isOn) {
/*  839 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", e1, 6);
/*      */       }
/*      */       
/*  842 */       JmqiException traceRet5 = new JmqiException(this, 9546, new String[] { JmqiTools.getExSumm(e1), null, JmqiTools.getFailingCall(e1) }, 2, 2195, e1);
/*      */       
/*  844 */       if (Trace.isOn) {
/*  845 */         Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", traceRet5, 7);
/*      */       }
/*      */       
/*  848 */       throw traceRet5;
/*      */     }
/*  850 */     catch (NoSuchMethodException e1) {
/*  851 */       if (Trace.isOn) {
/*  852 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", e1, 7);
/*      */       }
/*      */       
/*  855 */       JmqiException traceRet6 = new JmqiException(this, 9546, new String[] { JmqiTools.getExSumm(e1), null, JmqiTools.getFailingCall(e1) }, 2, 2195, e1);
/*      */       
/*  857 */       if (Trace.isOn) {
/*  858 */         Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", traceRet6, 8);
/*      */       }
/*      */       
/*  861 */       throw traceRet6;
/*      */     }
/*  863 */     catch (JmqiException jmqie) {
/*  864 */       if (Trace.isOn) {
/*  865 */         Trace.data(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", "Caught expected exception at catch index 8", jmqie);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  871 */       if (Trace.isOn) {
/*  872 */         Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", jmqie, 9);
/*      */       }
/*      */       
/*  875 */       throw jmqie;
/*      */     }
/*  877 */     catch (Exception e1) {
/*  878 */       if (Trace.isOn) {
/*  879 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", e1, 9);
/*      */       }
/*      */       
/*  882 */       JmqiException traceRet6 = new JmqiException(this, 9546, new String[] { JmqiTools.getExSumm(e1), null, JmqiTools.getFailingCall(e1) }, 2, 2195, e1);
/*      */       
/*  884 */       if (Trace.isOn) {
/*  885 */         Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", traceRet6, 10);
/*      */       }
/*      */       
/*  888 */       throw traceRet6;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  893 */     if (!this.configuration.getBoolValue(Configuration.ENV_AMQ_DISABLE_CLIENT_AMS)) {
/*  894 */       mq = processESESecurity(options, mq);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  899 */     if (Trace.isOn) {
/*  900 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getInstance(final String,int)", mq);
/*      */     }
/*  902 */     return mq;
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
/*      */   @Cruise("Method 'private JmqiMQ processMonitoring(int, JmqiMQ)' removed by APAR IT17247.")
/*      */   private JmqiMQ processESESecurity(int options, JmqiMQ mq) {
/*  920 */     if (Trace.isOn)
/*  921 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "processESESecurity(int,JmqiMQ)", new Object[] {
/*  922 */             Integer.valueOf(options), mq
/*      */           }); 
/*  924 */     JmqiMQ wrappedMq = mq;
/*      */     
/*  926 */     if (CSSystem.currentPlatform().equals(CSSystem.Platform.OS_NSS)) {
/*      */       
/*  928 */       if (Trace.isOn) {
/*  929 */         Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "processESESecurity(int,JmqiMQ)", wrappedMq, 1);
/*      */       }
/*      */       
/*  932 */       return wrappedMq;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/*  937 */       Class<?> amsClass = JmqiTools.dynamicLoadClass(this, "com.ibm.mq.ese.jmqi.ESEJMQI", getClass());
/*      */ 
/*      */       
/*  940 */       Class<?>[] paramTypes = new Class[] { JmqiEnvironment.class, int.class, JmqiMQ.class };
/*      */       
/*  942 */       Constructor<?> constructor = amsClass.getConstructor(paramTypes);
/*      */       
/*  944 */       Object[] params = { this, Integer.valueOf(options), mq };
/*      */       
/*  946 */       wrappedMq = (JmqiMQ)constructor.newInstance(params);
/*      */     }
/*  948 */     catch (ClassNotFoundException|SecurityException|NoSuchMethodException|IllegalArgumentException|InstantiationException|IllegalAccessException|InvocationTargetException ex) {
/*      */ 
/*      */       
/*  951 */       if (Trace.isOn) {
/*  952 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "processESESecurity(int,JmqiMQ)", ex, 1);
/*      */ 
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  958 */     catch (RuntimeException e) {
/*  959 */       if (Trace.isOn) {
/*  960 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "processESESecurity(int,JmqiMQ)", e, 2);
/*      */       }
/*      */ 
/*      */       
/*  964 */       Throwable cause = e.getCause();
/*      */       
/*  966 */       if (!(cause instanceof ClassNotFoundException)) {
/*      */ 
/*      */         
/*  969 */         HashMap<String, Object> ffstInfo = new HashMap<>();
/*  970 */         ffstInfo.put("Description", "Unable to load com.ibm.mq.ese.jmqi.ESEJMQI");
/*  971 */         ffstInfo.put("Exception Summary", JmqiTools.getExSumm(e));
/*  972 */         ffstInfo.put("Exception message", (cause != null) ? cause.getMessage() : null);
/*  973 */         ffstInfo.put("Exception", e);
/*  974 */         Trace.ffst(this, "processESESecurity(int,JmqiMQ)", "01", ffstInfo, null);
/*      */         
/*  976 */         if (Trace.isOn) {
/*  977 */           Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "processESESecurity(int,JmqiMQ)", e, 1);
/*      */         }
/*      */         
/*  980 */         throw e;
/*      */       } 
/*      */     } 
/*  983 */     if (Trace.isOn) {
/*  984 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "processESESecurity(int,JmqiMQ)", wrappedMq, 2);
/*      */     }
/*      */     
/*  987 */     return wrappedMq;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getProperty(String property) {
/*  998 */     String value = null;
/*      */     try {
/* 1000 */       value = this.properties.getProperty(property);
/*      */     }
/* 1002 */     catch (Exception exception) {}
/*      */ 
/*      */     
/* 1005 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEnvironmentProperty(final String property) {
/* 1016 */     if (Trace.isOn) {
/* 1017 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getEnvironmentProperty(final String)", new Object[] { property });
/*      */     }
/*      */     
/* 1020 */     String value = null;
/*      */ 
/*      */ 
/*      */     
/* 1024 */     value = getProperty(property);
/*      */ 
/*      */ 
/*      */     
/* 1028 */     if ((value == null || value.length() == 0) && this.checkEnvVars) {
/*      */       try {
/* 1030 */         value = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*      */             {
/*      */               public String run()
/*      */               {
/* 1034 */                 if (Trace.isOn) {
/* 1035 */                   Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "run()");
/*      */                 }
/* 1037 */                 String value1 = null;
/*      */                 try {
/* 1039 */                   value1 = System.getenv(property);
/*      */                 }
/* 1041 */                 catch (Exception e) {
/* 1042 */                   if (Trace.isOn) {
/* 1043 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.null", "run()", e);
/*      */                   }
/*      */                   
/* 1046 */                   JmqiEnvironment.this.checkEnvVars = false;
/*      */                 } 
/* 1048 */                 if (Trace.isOn) {
/* 1049 */                   Trace.exit(this, "com.ibm.mq.jmqi.null", "run()", value1);
/*      */                 }
/* 1051 */                 return value1;
/*      */               }
/*      */             });
/*      */       
/*      */       }
/* 1056 */       catch (Throwable e) {
/* 1057 */         if (Trace.isOn) {
/* 1058 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getEnvironmentProperty(final String)", e);
/*      */         }
/*      */         
/* 1061 */         this.checkEnvVars = false;
/*      */       } 
/*      */     }
/*      */     
/* 1065 */     if (Trace.isOn) {
/* 1066 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getEnvironmentProperty(final String)", value);
/*      */     }
/*      */     
/* 1069 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JmqiThreadPoolFactory getThreadPoolFactory() {
/* 1076 */     if (Trace.isOn) {
/* 1077 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getThreadPoolFactory()", "getter", this.threadPoolFactory);
/*      */     }
/*      */     
/* 1080 */     return this.threadPoolFactory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQAIR newMQAIR() {
/* 1090 */     if (Trace.isOn) {
/* 1091 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQAIR()");
/*      */     }
/* 1093 */     MQAIR traceRet1 = new MQAIR(this);
/*      */     
/* 1095 */     if (Trace.isOn) {
/* 1096 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQAIR()", traceRet1);
/*      */     }
/* 1098 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQBO newMQBO() {
/* 1108 */     if (Trace.isOn) {
/* 1109 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQBO()");
/*      */     }
/* 1111 */     MQBO traceRet1 = new MQBO(this);
/*      */     
/* 1113 */     if (Trace.isOn) {
/* 1114 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQBO()", traceRet1);
/*      */     }
/* 1116 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCD newMQCD() {
/* 1126 */     if (Trace.isOn) {
/* 1127 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCD()");
/*      */     }
/* 1129 */     MQCD traceRet1 = new MQCD(this);
/*      */     
/* 1131 */     if (Trace.isOn) {
/* 1132 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCD()", traceRet1);
/*      */     }
/* 1134 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCNO newMQCNO() {
/* 1144 */     if (Trace.isOn) {
/* 1145 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCNO()");
/*      */     }
/* 1147 */     MQCNO traceRet1 = new MQCNO(this);
/*      */     
/* 1149 */     if (Trace.isOn) {
/* 1150 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCNO()", traceRet1);
/*      */     }
/* 1152 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQBNO newMQBNO() {
/* 1162 */     if (Trace.isOn) {
/* 1163 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQBNO()");
/*      */     }
/* 1165 */     MQBNO traceRet1 = new MQBNO(this);
/*      */     
/* 1167 */     if (Trace.isOn) {
/* 1168 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQBNO()", traceRet1);
/*      */     }
/* 1170 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCSP newMQCSP() {
/* 1180 */     if (Trace.isOn) {
/* 1181 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCSP()");
/*      */     }
/* 1183 */     MQCSP traceRet1 = new MQCSP(this);
/*      */     
/* 1185 */     if (Trace.isOn) {
/* 1186 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCSP()", traceRet1);
/*      */     }
/* 1188 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCXP newMQCXP() {
/* 1198 */     if (Trace.isOn) {
/* 1199 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCXP()");
/*      */     }
/* 1201 */     MQCXP traceRet1 = new MQCXP(this);
/*      */     
/* 1203 */     if (Trace.isOn) {
/* 1204 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCXP()", traceRet1);
/*      */     }
/* 1206 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQSTS newMQSTS() {
/* 1216 */     if (Trace.isOn) {
/* 1217 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQSTS()");
/*      */     }
/* 1219 */     MQSTS traceRet1 = new MQSTS(this);
/*      */     
/* 1221 */     if (Trace.isOn) {
/* 1222 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQSTS()", traceRet1);
/*      */     }
/* 1224 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQGMO newMQGMO() {
/* 1234 */     if (Trace.isOn) {
/* 1235 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQGMO()");
/*      */     }
/* 1237 */     MQGMO traceRet1 = new MQGMO(this);
/*      */     
/* 1239 */     if (Trace.isOn) {
/* 1240 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQGMO()", traceRet1);
/*      */     }
/* 1242 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQMD newMQMD() {
/* 1252 */     if (Trace.isOn) {
/* 1253 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQMD()");
/*      */     }
/* 1255 */     MQMD traceRet1 = new MQMD(this);
/*      */     
/* 1257 */     if (Trace.isOn) {
/* 1258 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQMD()", traceRet1);
/*      */     }
/* 1260 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQMDE newMQMDE() {
/* 1270 */     if (Trace.isOn) {
/* 1271 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQMDE()");
/*      */     }
/* 1273 */     MQMDE traceRet1 = new MQMDE(this);
/*      */     
/* 1275 */     if (Trace.isOn) {
/* 1276 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQMDE()", traceRet1);
/*      */     }
/* 1278 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQOD newMQOD() {
/* 1288 */     if (Trace.isOn) {
/* 1289 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQOD()");
/*      */     }
/* 1291 */     MQOD traceRet1 = new MQOD(this);
/*      */     
/* 1293 */     if (Trace.isOn) {
/* 1294 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQOD()", traceRet1);
/*      */     }
/* 1296 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQOR newMQOR() {
/* 1306 */     if (Trace.isOn) {
/* 1307 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQOR()");
/*      */     }
/* 1309 */     MQOR traceRet1 = new MQOR(this);
/*      */     
/* 1311 */     if (Trace.isOn) {
/* 1312 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQOR()", traceRet1);
/*      */     }
/* 1314 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQPMO newMQPMO() {
/* 1324 */     if (Trace.isOn) {
/* 1325 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQPMO()");
/*      */     }
/* 1327 */     MQPMO traceRet1 = new MQPMO(this);
/*      */     
/* 1329 */     if (Trace.isOn) {
/* 1330 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQPMO()", traceRet1);
/*      */     }
/* 1332 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQPMR newMQPMR() {
/* 1342 */     if (Trace.isOn) {
/* 1343 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQPMR()");
/*      */     }
/* 1345 */     MQPMR traceRet1 = new MQPMR(this);
/*      */     
/* 1347 */     if (Trace.isOn) {
/* 1348 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQPMR()", traceRet1);
/*      */     }
/* 1350 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQPD newMQPD() {
/* 1360 */     if (Trace.isOn) {
/* 1361 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQPD()");
/*      */     }
/* 1363 */     MQPD traceRet1 = new MQPD(this);
/*      */     
/* 1365 */     if (Trace.isOn) {
/* 1366 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQPD()", traceRet1);
/*      */     }
/* 1368 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQRR newMQRR() {
/* 1377 */     if (Trace.isOn) {
/* 1378 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQRR()");
/*      */     }
/* 1380 */     MQRR traceRet1 = new MQRR(this);
/*      */     
/* 1382 */     if (Trace.isOn) {
/* 1383 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQRR()", traceRet1);
/*      */     }
/* 1385 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQSCO newMQSCO() {
/* 1395 */     if (Trace.isOn) {
/* 1396 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQSCO()");
/*      */     }
/* 1398 */     MQSCO traceRet1 = new MQSCO(this);
/*      */     
/* 1400 */     if (Trace.isOn) {
/* 1401 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQSCO()", traceRet1);
/*      */     }
/* 1403 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Pint newPint() {
/* 1414 */     Pint traceRet1 = new Pint(this);
/*      */     
/* 1416 */     return traceRet1;
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
/*      */   public Pint newPint(int x) {
/* 1428 */     Pint traceRet1 = new Pint(this, x);
/*      */     
/* 1430 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PbyteBuffer newPbyteBuffer() {
/* 1440 */     if (Trace.isOn) {
/* 1441 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newPbyteBuffer()");
/*      */     }
/* 1443 */     PbyteBuffer result = new PbyteBuffer(this);
/*      */     
/* 1445 */     if (Trace.isOn) {
/* 1446 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newPbyteBuffer()", result);
/*      */     }
/* 1448 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PbyteBuffer newPbyteBuffer(ByteBuffer buffer) {
/* 1458 */     if (Trace.isOn) {
/* 1459 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newPbyteBuffer(ByteBuffer)", new Object[] { buffer });
/*      */     }
/*      */     
/* 1462 */     PbyteBuffer result = new PbyteBuffer(this, buffer);
/*      */     
/* 1464 */     if (Trace.isOn) {
/* 1465 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newPbyteBuffer(ByteBuffer)", result);
/*      */     }
/* 1467 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PtripletArray newPtripletArray(Triplet[] triplets) {
/* 1477 */     if (Trace.isOn) {
/* 1478 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newPtripletArray(Triplet [ ])", new Object[] { triplets });
/*      */     }
/*      */     
/* 1481 */     PtripletArray result = new PtripletArray(this, triplets);
/*      */     
/* 1483 */     if (Trace.isOn) {
/* 1484 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newPtripletArray(Triplet [ ])", result);
/*      */     }
/* 1486 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Phconn newPhconn() {
/* 1495 */     if (Trace.isOn) {
/* 1496 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newPhconn()");
/*      */     }
/* 1498 */     Phconn traceRet1 = new Phconn(this);
/*      */     
/* 1500 */     if (Trace.isOn) {
/* 1501 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newPhconn()", traceRet1);
/*      */     }
/* 1503 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Phobj newPhobj() {
/* 1513 */     if (Trace.isOn) {
/* 1514 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newPhobj()");
/*      */     }
/* 1516 */     Phobj traceRet1 = new Phobj(this);
/*      */     
/* 1518 */     if (Trace.isOn) {
/* 1519 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newPhobj()", traceRet1);
/*      */     }
/* 1521 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JmqiXid newJmqiXid() {
/* 1531 */     if (Trace.isOn) {
/* 1532 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newJmqiXid()");
/*      */     }
/* 1534 */     JmqiXid traceRet1 = new JmqiXid(this);
/*      */     
/* 1536 */     if (Trace.isOn) {
/* 1537 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newJmqiXid()", traceRet1);
/*      */     }
/* 1539 */     return traceRet1;
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
/*      */   public JmqiXAResource newJmqiXAResource(JmqiXA xa, Hconn hconn) throws XAException {
/* 1552 */     if (Trace.isOn) {
/* 1553 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newJmqiXAResource(JmqiXA,Hconn)", new Object[] { xa, hconn });
/*      */     }
/*      */     
/* 1556 */     JmqiXAResource result = JmqiXAResource.getInstance(this, xa, hconn);
/*      */     
/* 1558 */     if (Trace.isOn) {
/* 1559 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newJmqiXAResource(JmqiXA,Hconn)", result);
/*      */     }
/*      */     
/* 1562 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JmqiWorkerThread newJmqiWorkerThread() {
/* 1572 */     if (Trace.isOn) {
/* 1573 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newJmqiWorkerThread()");
/*      */     }
/* 1575 */     JmqiWorkerThread traceRet1 = new JmqiWorkerThread(this);
/*      */     
/* 1577 */     if (Trace.isOn) {
/* 1578 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newJmqiWorkerThread()", traceRet1);
/*      */     }
/* 1580 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCHARV newMQCHARV() {
/* 1590 */     if (Trace.isOn) {
/* 1591 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCHARV()");
/*      */     }
/* 1593 */     MQCHARV traceRet1 = new MQCHARV(this);
/*      */     
/* 1595 */     if (Trace.isOn) {
/* 1596 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCHARV()", traceRet1);
/*      */     }
/* 1598 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQSD newMQSD() {
/* 1608 */     if (Trace.isOn) {
/* 1609 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQSD()");
/*      */     }
/* 1611 */     MQSD traceRet1 = new MQSD(this);
/*      */     
/* 1613 */     if (Trace.isOn) {
/* 1614 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQSD()", traceRet1);
/*      */     }
/* 1616 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQSRO newMQSRO() {
/* 1626 */     if (Trace.isOn) {
/* 1627 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQSRO()");
/*      */     }
/* 1629 */     MQSRO traceRet1 = new MQSRO(this);
/*      */     
/* 1631 */     if (Trace.isOn) {
/* 1632 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQSRO()", traceRet1);
/*      */     }
/* 1634 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCBD newMQCBD() {
/* 1643 */     if (Trace.isOn) {
/* 1644 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCBD()");
/*      */     }
/* 1646 */     MQCBD result = new MQCBD(this);
/*      */     
/* 1648 */     if (Trace.isOn) {
/* 1649 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCBD()", result);
/*      */     }
/* 1651 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCBC newMQCBC() {
/* 1660 */     if (Trace.isOn) {
/* 1661 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCBC()");
/*      */     }
/* 1663 */     MQCBC result = new MQCBC(this);
/*      */     
/* 1665 */     if (Trace.isOn) {
/* 1666 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCBC()", result);
/*      */     }
/* 1668 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCTLO newMQCTLO() {
/* 1677 */     if (Trace.isOn) {
/* 1678 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCTLO()");
/*      */     }
/* 1680 */     MQCTLO result = new MQCTLO(this);
/*      */     
/* 1682 */     if (Trace.isOn) {
/* 1683 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCTLO()", result);
/*      */     }
/* 1685 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public QueueManagerInfo newQueueManagerInfo() {
/* 1694 */     if (Trace.isOn) {
/* 1695 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newQueueManagerInfo()");
/*      */     }
/* 1697 */     QueueManagerInfo result = new QueueManagerInfo(this);
/*      */     
/* 1699 */     if (Trace.isOn) {
/* 1700 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newQueueManagerInfo()", result);
/*      */     }
/* 1702 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQRFH newMQRFH(int capacity) {
/* 1712 */     if (Trace.isOn)
/* 1713 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQRFH(int)", new Object[] {
/* 1714 */             Integer.valueOf(capacity)
/*      */           }); 
/* 1716 */     MQRFH result = new MQRFH(this, capacity);
/*      */     
/* 1718 */     if (Trace.isOn) {
/* 1719 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQRFH(int)", result);
/*      */     }
/* 1721 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQDLH newMQDLH() {
/* 1730 */     if (Trace.isOn) {
/* 1731 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQDLH()");
/*      */     }
/* 1733 */     MQDLH result = new MQDLH(this);
/*      */     
/* 1735 */     if (Trace.isOn) {
/* 1736 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQDLH()", result);
/*      */     }
/* 1738 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCIH newMQCIH() {
/* 1747 */     if (Trace.isOn) {
/* 1748 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCIH()");
/*      */     }
/* 1750 */     MQCIH result = new MQCIH(this);
/*      */     
/* 1752 */     if (Trace.isOn) {
/* 1753 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCIH()", result);
/*      */     }
/* 1755 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQIIH newMQIIH() {
/* 1764 */     if (Trace.isOn) {
/* 1765 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQIIH()");
/*      */     }
/* 1767 */     MQIIH result = new MQIIH(this);
/*      */     
/* 1769 */     if (Trace.isOn) {
/* 1770 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQIIH()", result);
/*      */     }
/* 1772 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQWIH newMQWIH() {
/* 1781 */     if (Trace.isOn) {
/* 1782 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQWIH()");
/*      */     }
/* 1784 */     MQWIH result = new MQWIH(this);
/*      */     
/* 1786 */     if (Trace.isOn) {
/* 1787 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQWIH()", result);
/*      */     }
/* 1789 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQCMHO newMQCMHO() {
/* 1798 */     if (Trace.isOn) {
/* 1799 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCMHO()");
/*      */     }
/* 1801 */     MQCMHO result = new MQCMHO(this);
/*      */     
/* 1803 */     if (Trace.isOn) {
/* 1804 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQCMHO()", result);
/*      */     }
/* 1806 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQDMHO newMQDMHO() {
/* 1815 */     if (Trace.isOn) {
/* 1816 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQDMHO()");
/*      */     }
/* 1818 */     MQDMHO result = new MQDMHO(this);
/*      */     
/* 1820 */     if (Trace.isOn) {
/* 1821 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQDMHO()", result);
/*      */     }
/* 1823 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQSMPO newMQSMPO() {
/* 1832 */     if (Trace.isOn) {
/* 1833 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQSMPO()");
/*      */     }
/* 1835 */     MQSMPO result = new MQSMPO(this);
/*      */     
/* 1837 */     if (Trace.isOn) {
/* 1838 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQSMPO()", result);
/*      */     }
/* 1840 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQDMPO newMQDMPO() {
/* 1849 */     if (Trace.isOn) {
/* 1850 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQDMPO()");
/*      */     }
/* 1852 */     MQDMPO result = new MQDMPO(this);
/*      */     
/* 1854 */     if (Trace.isOn) {
/* 1855 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQDMPO()", result);
/*      */     }
/* 1857 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQIMPO newMQIMPO() {
/* 1866 */     if (Trace.isOn) {
/* 1867 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQIMPO()");
/*      */     }
/* 1869 */     MQIMPO result = new MQIMPO(this);
/*      */     
/* 1871 */     if (Trace.isOn) {
/* 1872 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQIMPO()", result);
/*      */     }
/* 1874 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQMHBO newMQMHBO() {
/* 1883 */     if (Trace.isOn) {
/* 1884 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQMHBO()");
/*      */     }
/* 1886 */     MQMHBO result = new MQMHBO(this);
/*      */     
/* 1888 */     if (Trace.isOn) {
/* 1889 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQMHBO()", result);
/*      */     }
/* 1891 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQBMHO newMQBMHO() {
/* 1900 */     if (Trace.isOn) {
/* 1901 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQBMHO()");
/*      */     }
/* 1903 */     MQBMHO result = new MQBMHO(this);
/*      */     
/* 1905 */     if (Trace.isOn) {
/* 1906 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQBMHO()", result);
/*      */     }
/* 1908 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQHeader newMQHeader() {
/* 1917 */     if (Trace.isOn) {
/* 1918 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQHeader()");
/*      */     }
/* 1920 */     MQHeader traceRet1 = new MQHeader(this);
/*      */     
/* 1922 */     if (Trace.isOn) {
/* 1923 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newMQHeader()", traceRet1);
/*      */     }
/* 1925 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Hmsg newHmsg(long value) {
/* 1936 */     if (Trace.isOn)
/* 1937 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newHmsg(long)", new Object[] {
/* 1938 */             Long.valueOf(value)
/*      */           }); 
/* 1940 */     HmsgImpl hmsgImpl = new HmsgImpl(this, value);
/*      */     
/* 1942 */     if (Trace.isOn) {
/* 1943 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newHmsg(long)", hmsgImpl);
/*      */     }
/* 1945 */     return (Hmsg)hmsgImpl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Phmsg newPhmsg(Hmsg hmsg) {
/* 1956 */     if (Trace.isOn) {
/* 1957 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newPhmsg(Hmsg)", new Object[] { hmsg });
/*      */     }
/* 1959 */     Phmsg traceRet1 = new Phmsg(this, hmsg);
/*      */     
/* 1961 */     if (Trace.isOn) {
/* 1962 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "newPhmsg(Hmsg)", traceRet1);
/*      */     }
/* 1964 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQMD copyMQMD(MQMD source) {
/* 1974 */     if (Trace.isOn) {
/* 1975 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "copyMQMD(MQMD)", new Object[] { source });
/*      */     }
/* 1977 */     MQMD result = new MQMD(this, source);
/*      */     
/* 1979 */     if (Trace.isOn) {
/* 1980 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "copyMQMD(MQMD)", result);
/*      */     }
/* 1982 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void conditionalCopyMQMD(MQMD source, MQMD target) {
/* 1992 */     if (Trace.isOn) {
/* 1993 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "conditionalCopyMQMD(MQMD,MQMD)", new Object[] { source, target });
/*      */     }
/*      */     
/* 1996 */     if (Trace.isOn) {
/* 1997 */       Trace.data(this, "conditionalCopyMQMD(MQMD,MQMD)", "Source MQMD:", source.toString());
/* 1998 */       Trace.data(this, "conditionalCopyMQMD(MQMD,MQMD)", "Target MQMD (before conditional copy):", target.toString());
/*      */     } 
/* 2000 */     MQMD.conditionalCopy(source, target);
/* 2001 */     if (Trace.isOn) {
/* 2002 */       Trace.data(this, "conditionalCopyMQMD(MQMD,MQMD)", "Target MQMD (after conditional copy):", target.toString());
/*      */     }
/*      */     
/* 2005 */     if (Trace.isOn) {
/* 2006 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "conditionalCopyMQMD(MQMD,MQMD)");
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
/*      */   public int getCcsid(String javaCharacterSet) throws JmqiException {
/*      */     JmqiCodepage cp;
/* 2019 */     if (Trace.isOn) {
/* 2020 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getCcsid(String)", new Object[] { javaCharacterSet });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 2025 */       cp = JmqiCodepage.getJmqiCodepage(this, javaCharacterSet);
/*      */       
/* 2027 */       if (cp == null) {
/* 2028 */         UnsupportedEncodingException traceRet3 = new UnsupportedEncodingException(javaCharacterSet);
/* 2029 */         if (Trace.isOn) {
/* 2030 */           Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getCcsid(String)", traceRet3, 1);
/*      */         }
/* 2032 */         throw traceRet3;
/*      */       }
/*      */     
/*      */     }
/* 2036 */     catch (UnsupportedEncodingException e) {
/* 2037 */       if (Trace.isOn) {
/* 2038 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getCcsid(String)", e);
/*      */       }
/* 2040 */       JmqiException traceRet2 = new JmqiException(this, 6047, new String[] { "UCS2 (Unicode)", javaCharacterSet }, 2, 2341, e);
/*      */ 
/*      */       
/* 2043 */       if (Trace.isOn) {
/* 2044 */         Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getCcsid(String)", traceRet2, 2);
/*      */       }
/*      */       
/* 2047 */       throw traceRet2;
/*      */     } 
/* 2049 */     int traceRet1 = cp.getCCSID();
/*      */     
/* 2051 */     if (Trace.isOn) {
/* 2052 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getCcsid(String)", 
/* 2053 */           Integer.valueOf(traceRet1));
/*      */     }
/* 2055 */     return traceRet1;
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
/*      */   @Deprecated
/*      */   public String getJavaCharacterSet(int ccsid) throws JmqiException {
/* 2069 */     if (Trace.isOn)
/* 2070 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getJavaCharacterSet(int)", new Object[] {
/* 2071 */             Integer.valueOf(ccsid)
/*      */           }); 
/* 2073 */     JmqiCodepage cp = JmqiCodepage.getJmqiCodepage(this, ccsid);
/* 2074 */     if (cp == null) {
/* 2075 */       JmqiException traceRet2 = new JmqiException(this, 6047, new String[] { "UCS2 (Unicode)", Integer.toString(ccsid) }, 2, 2341, null);
/*      */ 
/*      */       
/* 2078 */       if (Trace.isOn) {
/* 2079 */         Trace.throwing(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getJavaCharacterSet(int)", traceRet2);
/*      */       }
/*      */       
/* 2082 */       throw traceRet2;
/*      */     } 
/* 2084 */     String traceRet1 = cp.charsetId;
/*      */     
/* 2086 */     if (Trace.isOn) {
/* 2087 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getJavaCharacterSet(int)", traceRet1);
/*      */     }
/* 2089 */     return traceRet1;
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
/*      */   public JmqiException getLastException() {
/* 2103 */     return null;
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
/*      */   public void setLastException(JmqiException e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getOperatingSystem() {
/* 2124 */     os = CSSystem.currentPlatform().ordinal();
/* 2125 */     if (Trace.isOn) {
/* 2126 */       Trace.data("com.ibm.mq.jmqi.JmqiEnvironment", "getOperatingSystem()", "getter", 
/* 2127 */           Integer.valueOf(os));
/*      */     }
/* 2129 */     return os;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getBitmode() {
/* 2137 */     String bitmode = CSSystem.getBitmode();
/* 2138 */     if (Trace.isOn) {
/* 2139 */       Trace.data("com.ibm.mq.jmqi.JmqiEnvironment", "getBitmode()", "getter", bitmode);
/*      */     }
/* 2141 */     return bitmode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getMqExitNameLength() {
/* 2148 */     switch (CSSystem.currentPlatform()) {
/*      */       case OS_ISERIES:
/* 2150 */         MQ_EXIT_NAME_LENGTH = 20;
/*      */         break;
/*      */       case OS_ZSERIES:
/* 2153 */         MQ_EXIT_NAME_LENGTH = 8;
/*      */         break;
/*      */       default:
/* 2156 */         MQ_EXIT_NAME_LENGTH = 128;
/*      */         break;
/*      */     } 
/* 2159 */     if (Trace.isOn) {
/* 2160 */       Trace.data("com.ibm.mq.jmqi.JmqiEnvironment", "getMqExitNameLength()", "getter", 
/* 2161 */           Integer.valueOf(MQ_EXIT_NAME_LENGTH));
/*      */     }
/* 2163 */     return MQ_EXIT_NAME_LENGTH;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char getCaller() {
/* 2170 */     if (Trace.isOn) {
/* 2171 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getCaller()", "getter", 
/* 2172 */           Character.valueOf(this.caller));
/*      */     }
/* 2174 */     return this.caller;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCaller(char caller) {
/* 2181 */     if (Trace.isOn) {
/* 2182 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiEnvironment", "setCaller(char)", "setter", 
/* 2183 */           Character.valueOf(caller));
/*      */     }
/* 2185 */     this.caller = caller;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String getVersionProperty(String key) {
/* 2193 */     String traceRet1 = (String)versionProperties.get(key);
/*      */     
/* 2195 */     if (Trace.isOn) {
/* 2196 */       Trace.data("com.ibm.mq.jmqi.JmqiEnvironment", "getVersionProperty(" + key + ")", "getter", traceRet1);
/*      */     }
/* 2198 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getProductIdentifier() {
/* 2207 */     if (Trace.isOn) {
/* 2208 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getProductIdentifier()");
/*      */     }
/* 2210 */     if (this.productIdentifier == null) {
/* 2211 */       String versionString = getVersionProperty("com.ibm.mq.jmqi");
/* 2212 */       int version = 0;
/* 2213 */       int release = 0;
/* 2214 */       int maintenance = 0;
/* 2215 */       int fix = 0;
/*      */       
/* 2217 */       if (versionString != null) {
/*      */         try {
/* 2219 */           String[] words = versionString.split("\\.");
/* 2220 */           if (words.length > 0) {
/* 2221 */             version = Integer.parseInt(words[0]);
/*      */           }
/* 2223 */           if (words.length > 1) {
/* 2224 */             release = Integer.parseInt(words[1]);
/*      */           }
/* 2226 */           if (words.length > 2) {
/* 2227 */             maintenance = Integer.parseInt(words[2]);
/*      */           }
/* 2229 */           if (words.length > 3) {
/* 2230 */             fix = Integer.parseInt(words[3]);
/*      */           }
/*      */         }
/* 2233 */         catch (Exception e) {
/* 2234 */           if (Trace.isOn) {
/* 2235 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getProductIdentifier()", e);
/*      */           }
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2243 */       StringBuffer sb = new StringBuffer();
/* 2244 */       sb.append("MQJ");
/* 2245 */       sb.append(this.caller);
/* 2246 */       sb.append(JmqiTools.right(Integer.toString(version), 2, '0'));
/* 2247 */       sb.append(JmqiTools.right(Integer.toString(release), 2, '0'));
/* 2248 */       sb.append(JmqiTools.right(Integer.toString(maintenance), 2, '0'));
/* 2249 */       sb.append(JmqiTools.right(Integer.toString(fix), 2, '0'));
/*      */       
/* 2251 */       this.productIdentifier = sb.toString();
/*      */     } 
/*      */     
/* 2254 */     if (Trace.isOn) {
/* 2255 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getProductIdentifier()", this.productIdentifier);
/*      */     }
/*      */     
/* 2258 */     return this.productIdentifier;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getZosBootstrapLibraryName() {
/* 2265 */     if (Trace.isOn) {
/* 2266 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getZosBootstrapLibraryName()", "mqjbnd");
/*      */     }
/* 2268 */     return "mqjbnd";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getZosBootstrapLibraryName64() {
/* 2275 */     if (Trace.isOn) {
/* 2276 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getZosBootstrapLibraryName64()", "mqjbnd64");
/*      */     }
/* 2278 */     return "mqjbnd64";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getZosCicsLibraryName() {
/* 2285 */     if (Trace.isOn) {
/* 2286 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiEnvironment", "getZosCicsLibraryName()", "mqjcics");
/*      */     }
/* 2288 */     return "mqjcics";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean loadZosNativeBootstrap() {
/* 2296 */     final String bootstrap = getBitmode().equals("64") ? getZosBootstrapLibraryName64() : getZosBootstrapLibraryName();
/* 2297 */     final String libPath = getConfiguration().getStringValue(Configuration.JMQI_LIB_PATH);
/* 2298 */     if (Trace.isOn) {
/* 2299 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "loadZosNativeBootstrap()");
/* 2300 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiEnvironment", (libPath == null) ? bootstrap : (bootstrap + " from path " + libPath));
/*      */     } 
/* 2302 */     Boolean result = AccessController.<Boolean>doPrivileged(new PrivilegedAction<Boolean>()
/*      */         {
/*      */           public Boolean run()
/*      */           {
/*      */             try {
/* 2307 */               if (libPath != null) {
/* 2308 */                 String loadPath = libPath + File.separator + System.mapLibraryName(bootstrap);
/* 2309 */                 if (Trace.isOn) {
/* 2310 */                   boolean libraryFound = (new File(loadPath)).exists();
/* 2311 */                   Trace.data(this, "loadZosNativeBootstrap()", "candidate file " + loadPath + (libraryFound ? " exists" : " does not exist"));
/*      */                 } 
/* 2313 */                 System.load(loadPath);
/*      */               } else {
/*      */                 
/* 2316 */                 System.loadLibrary(bootstrap);
/*      */               } 
/* 2318 */               return Boolean.valueOf(true);
/*      */             }
/* 2320 */             catch (AccessControlException ace) {
/* 2321 */               if (Trace.isOn) {
/* 2322 */                 Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "loadZosNativeBootstrap", ace, 1);
/*      */               }
/* 2324 */               return Boolean.valueOf(false);
/*      */             }
/* 2326 */             catch (Error e) {
/*      */               
/* 2328 */               if (Trace.isOn) {
/* 2329 */                 Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "loadZosNativeBootstrap", e, 2);
/*      */               }
/* 2331 */               return Boolean.valueOf(false);
/*      */             } 
/*      */           }
/*      */         });
/* 2335 */     if (Trace.isOn) {
/* 2336 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "loadZosNativeBootstrap()", result);
/*      */     }
/* 2338 */     return result.booleanValue();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private enum OnCicsTcbState
/*      */   {
/* 2345 */     ON_CICS_TCB,
/* 2346 */     NOT_ON_CICS_TCB,
/* 2347 */     INDETERMINATE;
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
/*      */   private OnCicsTcbState onCicsTcb() {
/* 2361 */     if (Trace.isOn) {
/* 2362 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "onCicsTcb()");
/*      */     }
/* 2364 */     OnCicsTcbState result = OnCicsTcbState.INDETERMINATE;
/*      */     try {
/* 2366 */       Class<?> zUtilClass = Class.forName("com.ibm.jzos.ZUtil");
/* 2367 */       final Method zUtilPeekLong = zUtilClass.getMethod("peekOSMemory", new Class[] { long.class, int.class });
/* 2368 */       OnCicsTcbState res = AccessController.<OnCicsTcbState>doPrivileged(new PrivilegedAction<OnCicsTcbState>()
/*      */           {
/*      */             public JmqiEnvironment.OnCicsTcbState run()
/*      */             {
/*      */               try {
/* 2373 */                 long psaTcb = ((Long)zUtilPeekLong.invoke(null, new Object[] { Long.valueOf(540L), Integer.valueOf(4) })).longValue();
/* 2374 */                 if (psaTcb == 0L) {
/* 2375 */                   return JmqiEnvironment.OnCicsTcbState.INDETERMINATE;
/*      */                 }
/*      */                 
/* 2378 */                 long tcbEtcb = ((Long)zUtilPeekLong.invoke(null, new Object[] { Long.valueOf(psaTcb + 208L), Integer.valueOf(4) })).longValue();
/* 2379 */                 if (tcbEtcb == 0L) {
/* 2380 */                   return JmqiEnvironment.OnCicsTcbState.INDETERMINATE;
/*      */                 }
/*      */                 
/* 2383 */                 long etcbTarget = ((Long)zUtilPeekLong.invoke(null, new Object[] { Long.valueOf(tcbEtcb + 20L), Integer.valueOf(4) })).longValue();
/* 2384 */                 if (etcbTarget == 0L) {
/* 2385 */                   return JmqiEnvironment.OnCicsTcbState.NOT_ON_CICS_TCB;
/*      */                 }
/*      */                 
/* 2388 */                 return JmqiEnvironment.OnCicsTcbState.ON_CICS_TCB;
/*      */               }
/* 2390 */               catch (Exception e) {
/* 2391 */                 Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "onCicsTcb()", e, 1);
/* 2392 */                 return JmqiEnvironment.OnCicsTcbState.INDETERMINATE;
/*      */               } 
/*      */             }
/*      */           });
/* 2396 */       result = res;
/*      */     }
/* 2398 */     catch (Exception e) {
/* 2399 */       Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "onCicsTcb()", e, 2);
/*      */     }
/* 2401 */     catch (Error E) {
/*      */ 
/*      */ 
/*      */       
/* 2405 */       Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "onCicsTcb()", E, 3);
/*      */     } 
/* 2407 */     if (result == OnCicsTcbState.INDETERMINATE) {
/* 2408 */       String cicsdet = zosNativeAdapterDetect();
/* 2409 */       if (cicsdet == null || cicsdet != getZosCicsLibraryName()) {
/* 2410 */         result = OnCicsTcbState.NOT_ON_CICS_TCB;
/*      */       } else {
/*      */         
/* 2413 */         result = OnCicsTcbState.ON_CICS_TCB;
/*      */       } 
/*      */     } 
/* 2416 */     if (Trace.isOn) {
/* 2417 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "onCicsTcb()", result);
/*      */     }
/* 2419 */     return result;
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
/*      */   public String zosNativeAdapterDetect() {
/* 2433 */     String result = null;
/* 2434 */     if (Trace.isOn) {
/* 2435 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "zosNativeAdapterDetect()");
/*      */     }
/*      */     
/* 2438 */     if (getOperatingSystem() == OS_ZSERIES && loadZosNativeBootstrap()) {
/*      */       try {
/* 2440 */         result = adapter_detect();
/*      */       }
/* 2442 */       catch (UnsatisfiedLinkError ule) {
/* 2443 */         if (Trace.isOn) {
/* 2444 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "zosNativeAdapterDetect()", ule, 1);
/*      */         }
/*      */ 
/*      */         
/*      */         try {
/* 2449 */           Class<?> localNativeClass = Class.forName("com.ibm.mq.jmqi.local.internal.base.Native");
/* 2450 */           Method localNativeDetect = localNativeClass.getMethod("adapter_detect", new Class[0]);
/* 2451 */           String oldResult = (String)localNativeDetect.invoke(null, new Object[0]);
/* 2452 */           result = oldResult;
/*      */         }
/* 2454 */         catch (Error E) {
/* 2455 */           if (Trace.isOn) {
/* 2456 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "zosNativeAdapterDetect()", E, 2);
/*      */           }
/* 2458 */           result = null;
/*      */         }
/* 2460 */         catch (Exception e) {
/* 2461 */           if (Trace.isOn) {
/* 2462 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiEnvironment", "zosNativeAdapterDetect()", e, 3);
/*      */           }
/* 2464 */           result = null;
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/* 2469 */       result = null;
/*      */     } 
/* 2471 */     if (Trace.isOn) {
/* 2472 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "zosNativeAdapterDetect()", result);
/*      */     }
/* 2474 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initialiseZosEnv() {
/* 2483 */     if (Trace.isOn) {
/* 2484 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiEnvironment", "initialiseZos()");
/*      */     }
/* 2486 */     synchronized (getZosEnvLock) {
/* 2487 */       if (!zosEnvInitialised) {
/* 2488 */         if (getOperatingSystem() == OS_ZSERIES)
/*      */         {
/* 2490 */           if (esafDetected()) {
/* 2491 */             runningUnderIMS = true;
/*      */           
/*      */           }
/*      */           else {
/*      */ 
/*      */             
/* 2497 */             boolean runningInWAS = (getConfiguration().getStringValue(Configuration.webSphereServerTypeProperty) != null);
/* 2498 */             if (!runningInWAS) {
/* 2499 */               OnCicsTcbState onCics = onCicsTcb();
/* 2500 */               if (onCics == OnCicsTcbState.ON_CICS_TCB || cicsJVMServerDetected()) {
/* 2501 */                 runningUnderCICS = true;
/*      */               }
/* 2503 */               else if (onCics == OnCicsTcbState.INDETERMINATE) {
/* 2504 */                 maybeUnderCICS = true;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         }
/* 2509 */         zosEnvInitialised = true;
/*      */       } 
/*      */     } 
/*      */     
/* 2513 */     if (Trace.isOn) {
/* 2514 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiEnvironment", "initialiseZos()", runningUnderIMS ? "running under IMS" : (runningUnderCICS ? "running under CICS" : (maybeUnderCICS ? "could not load bootstrap" : "Not CICS or IMS")));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isRunningUnderIMS() {
/* 2524 */     if (!zosEnvInitialised) {
/* 2525 */       initialiseZosEnv();
/*      */     }
/* 2527 */     if (Trace.isOn) {
/* 2528 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiEnvironment", "isRunningUnderIMS()", "getter", Boolean.valueOf(runningUnderIMS));
/*      */     }
/* 2530 */     return runningUnderIMS;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isRunningUnderCICS() {
/* 2537 */     if (!zosEnvInitialised) {
/* 2538 */       initialiseZosEnv();
/*      */     }
/* 2540 */     if (Trace.isOn) {
/* 2541 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiEnvironment", "isRunningUnderCICS()", "getter", Boolean.valueOf(runningUnderCICS));
/*      */     }
/* 2543 */     return runningUnderCICS;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isMaybeUnderCICS() {
/* 2550 */     if (!zosEnvInitialised) {
/* 2551 */       initialiseZosEnv();
/*      */     }
/* 2553 */     if (Trace.isOn) {
/* 2554 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiEnvironment", "isMaybeUnderCICS()", "getter", Boolean.valueOf(maybeUnderCICS));
/*      */     }
/* 2556 */     return maybeUnderCICS;
/*      */   }
/*      */   
/*      */   private static native String adapter_detect();
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\JmqiEnvironment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */