/*      */ package com.ibm.msg.client.wmq.compat.base.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.jmqi.JmqiDefaultPropertyHandler;
/*      */ import com.ibm.mq.jmqi.JmqiDefaultThreadPoolFactory;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiFactory;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.JmqiPropertyHandler;
/*      */ import com.ibm.mq.jmqi.JmqiThreadPoolFactory;
/*      */ import com.ibm.mq.jmqi.JmqiXA;
/*      */ import com.ibm.mq.jmqi.MQCNO;
/*      */ import com.ibm.mq.jmqi.MQGMO;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.MQPMO;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Hobj;
/*      */ import com.ibm.mq.jmqi.handles.Phconn;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*      */ import com.ibm.mq.jmqi.system.JmqiMetaData;
/*      */ import com.ibm.mq.jmqi.system.JmqiSP;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.SpiActivate;
/*      */ import com.ibm.mq.jmqi.system.SpiGetOptions;
/*      */ import com.ibm.mq.jmqi.system.SpiPutOptions;
/*      */ import com.ibm.mq.jmqi.system.SpiSyncPointOptions;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.IOException;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.HashMap;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Map;
/*      */ import javax.transaction.xa.Xid;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class MQSESSION
/*      */   implements MQXAVerbs
/*      */ {
/*      */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQSESSION.java";
/*      */   protected JmqiMQ jMQI;
/*      */   private int jmqiBindType;
/*      */   private JmqiSP jSPI;
/*      */   protected JmqiXA jXA;
/*      */   
/*      */   static {
/*   74 */     if (Trace.isOn) {
/*   75 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQSESSION.java");
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
/*   92 */   protected MQManagedConnectionJ11 mqManCon = null;
/*      */   protected boolean supportsQAT2 = false;
/*      */   private boolean v2StructuresSupported = true;
/*   95 */   private static char[] charInitArray = null;
/*      */ 
/*      */   
/*      */   private static final int CHARINITARRAYSIZE = 100;
/*      */   
/*      */   protected static final boolean iSeries;
/*      */   
/*      */   protected static final boolean zSeries;
/*      */   
/*  104 */   private static JmqiEnvironment jmqiEnv = null;
/*  105 */   private static JmqiSystemEnvironment jmqiSysEnv = null;
/*      */ 
/*      */   
/*      */   public static int jmsv6CompId;
/*      */ 
/*      */   
/*      */   private static Exception staticInitialiserException;
/*      */   
/*  113 */   private static Boolean supportsDeferred = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  119 */   private static Boolean supportsInherited = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/*  126 */     if (Trace.isOn) {
/*  127 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "static()");
/*      */     }
/*      */ 
/*      */     
/*  131 */     String os = PropertyStore.os_name;
/*      */     
/*  133 */     if (os.equals("OS/390") || os.equals("z/OS")) {
/*  134 */       zSeries = true;
/*  135 */       iSeries = false;
/*  136 */     } else if (os.equals("OS/400") || os.equals("OS400")) {
/*  137 */       zSeries = false;
/*  138 */       iSeries = true;
/*      */     } else {
/*  140 */       zSeries = false;
/*  141 */       iSeries = false;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/*  146 */       JmqiDefaultThreadPoolFactory threadPool = new JmqiDefaultThreadPoolFactory();
/*  147 */       JmqiDefaultPropertyHandler jmqiDefaultPropertyHandler = new JmqiDefaultPropertyHandler();
/*      */       
/*  149 */       jmqiEnv = JmqiFactory.getInstance((JmqiThreadPoolFactory)threadPool, (JmqiPropertyHandler)jmqiDefaultPropertyHandler);
/*  150 */       jmqiSysEnv = (JmqiSystemEnvironment)jmqiEnv;
/*  151 */       new JmsV6TlsComponent(jmqiSysEnv);
/*  152 */       threadPool.setEnv(jmqiEnv);
/*      */     }
/*  154 */     catch (JmqiException e) {
/*  155 */       if (Trace.isOn) {
/*  156 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "static()", (Throwable)e);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  164 */       staticInitialiserException = (Exception)e;
/*      */     } 
/*  166 */     if (jmqiEnv != null) {
/*  167 */       jmqiEnv.setCaller('N');
/*      */     }
/*      */     
/*  170 */     charInitArray = new char[100];
/*  171 */     for (int i = 0; i < 100; i++) {
/*  172 */       charInitArray[i] = ' ';
/*      */     }
/*  174 */     if (Trace.isOn) {
/*  175 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "static()");
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
/*      */   public MQSESSION(int jmqiBindType, boolean requestedThreadAffinity, String threadingType) throws MQException {
/*  188 */     if (Trace.isOn) {
/*  189 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "<init>(int,boolean,String)", new Object[] {
/*  190 */             Integer.valueOf(jmqiBindType), 
/*  191 */             Boolean.valueOf(requestedThreadAffinity), threadingType
/*      */           });
/*      */     }
/*  194 */     if (staticInitialiserException != null) {
/*  195 */       MQException e = new MQException(2, 2195, this);
/*  196 */       e.initCause(staticInitialiserException);
/*  197 */       if (Trace.isOn) {
/*  198 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "<init>(int,boolean,String)", (Throwable)e, 1);
/*      */       }
/*      */       
/*  201 */       throw e;
/*      */     } 
/*      */ 
/*      */     
/*  205 */     this.jmqiBindType = jmqiBindType;
/*      */ 
/*      */     
/*  208 */     int options = 0;
/*  209 */     if (requestedThreadAffinity) {
/*  210 */       options = 1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  217 */       this.jMQI = jmqiEnv.getMQI(jmqiBindType, options);
/*  218 */       this.jSPI = (JmqiSP)this.jMQI;
/*  219 */       this.jXA = (JmqiXA)this.jMQI;
/*      */     }
/*  221 */     catch (JmqiException e) {
/*  222 */       if (Trace.isOn) {
/*  223 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "<init>(int,boolean,String)", (Throwable)e);
/*      */       }
/*      */       
/*  226 */       MQException traceRet1 = new MQException(2, 2298, this);
/*  227 */       traceRet1.initCause((Throwable)e);
/*  228 */       if (Trace.isOn) {
/*  229 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "<init>(int,boolean,String)", (Throwable)traceRet1, 2);
/*      */       }
/*      */       
/*  232 */       throw traceRet1;
/*      */     } 
/*  234 */     if (Trace.isOn) {
/*  235 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "<init>(int,boolean,String)");
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
/*      */   protected static final boolean backoutOnImplicitDisc() {
/*  250 */     if (Trace.isOn) {
/*  251 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "backoutOnImplicitDisc()");
/*      */     }
/*      */ 
/*      */     
/*  255 */     if (zSeries) {
/*  256 */       if (Trace.isOn) {
/*  257 */         Trace.traceData("backoutOnImplicitDisc", "false", null);
/*      */       }
/*  259 */       if (Trace.isOn) {
/*  260 */         Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "backoutOnImplicitDisc()", 
/*  261 */             Boolean.valueOf(false), 1);
/*      */       }
/*  263 */       return false;
/*      */     } 
/*  265 */     if (Trace.isOn) {
/*  266 */       Trace.traceData("backoutOnImplicitDisc", "true", null);
/*      */     }
/*  268 */     if (Trace.isOn) {
/*  269 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "backoutOnImplicitDisc()", 
/*  270 */           Boolean.valueOf(true), 2);
/*      */     }
/*  272 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final MQConnectionRequestInfo getConnectionRequestInfo(String transport, Map<String, Object> mqProperties, boolean fromUrl) throws MQException {
/*  282 */     if (Trace.isOn) {
/*  283 */       if (mqProperties.containsKey("password")) {
/*  284 */         Hashtable<String, Object> propsNotPasswd = new Hashtable<>(mqProperties);
/*  285 */         propsNotPasswd.put("password", "********");
/*  286 */         Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getConnectionRequestInfo(String,Hashtable<String,Object>,boolean)", new Object[] { transport, propsNotPasswd, 
/*      */               
/*  288 */               Boolean.valueOf(fromUrl) });
/*      */       } else {
/*  290 */         Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getConnectionRequestInfo(String,Hashtable<String,Object>,boolean)", new Object[] { transport, mqProperties, 
/*      */               
/*  292 */               Boolean.valueOf(fromUrl) });
/*      */       } 
/*      */     }
/*  295 */     if (transport.equals("MQSeries Bindings")) {
/*  296 */       MQConnectionRequestInfo traceRet1 = new BindingsConnectionRequestInfo(mqProperties);
/*  297 */       if (Trace.isOn) {
/*  298 */         Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getConnectionRequestInfo(String,Hashtable<String,Object>,boolean)", traceRet1, 1);
/*      */       }
/*  300 */       return traceRet1;
/*      */     } 
/*      */     
/*  303 */     if (transport.equals("MQSeries Client")) {
/*  304 */       MQConnectionRequestInfo traceRet2 = new ClientConnectionRequestInfo(mqProperties, fromUrl);
/*  305 */       if (Trace.isOn) {
/*  306 */         Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getConnectionRequestInfo(String,Hashtable<String,Object>,boolean)", traceRet2, 2);
/*      */       }
/*  308 */       return traceRet2;
/*      */     } 
/*      */ 
/*      */     
/*  312 */     MQException traceRet3 = new MQException(2, 2012, "static method in MQSESSION");
/*  313 */     if (Trace.isOn) {
/*  314 */       Trace.throwing("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getConnectionRequestInfo(String,Hashtable<String,Object>,boolean)", (Throwable)traceRet3);
/*      */     }
/*  316 */     throw traceRet3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final int getDefaultCCSID() {
/*  325 */     if (Trace.isOn) {
/*  326 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getDefaultCCSID()");
/*      */     }
/*  328 */     if (zSeries) {
/*  329 */       if (Trace.isOn) {
/*  330 */         Trace.traceData("getDefaultCCSID", "500", null);
/*      */       }
/*  332 */       if (Trace.isOn) {
/*  333 */         Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getDefaultCCSID()", 
/*  334 */             Integer.valueOf(500), 1);
/*      */       }
/*  336 */       return 500;
/*      */     } 
/*  338 */     if (Trace.isOn) {
/*  339 */       Trace.traceData("getDefaultCCSID", "819", null);
/*      */     }
/*  341 */     if (Trace.isOn) {
/*  342 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getDefaultCCSID()", 
/*  343 */           Integer.valueOf(819), 2);
/*      */     }
/*  345 */     return 819;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static JmqiEnvironment getJmqiEnv() {
/*  353 */     if (Trace.isOn) {
/*  354 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getJmqiEnv()", "getter", jmqiEnv);
/*      */     }
/*      */     
/*  357 */     return jmqiEnv;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static JmqiSystemEnvironment getJmqiSysEnv() {
/*  364 */     if (Trace.isOn) {
/*  365 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getJmqiSysEnv()", "getter", jmqiSysEnv);
/*      */     }
/*      */     
/*  368 */     return jmqiSysEnv;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final MQManagedConnectionFactory getMQManagedConnectionFactory(String transport, String qmgrName, Map<String, Object> mqProperties) throws MQException {
/*  378 */     if (Trace.isOn) {
/*  379 */       if (mqProperties.containsKey("password")) {
/*  380 */         Map<String, Object> propsNotPasswd = new HashMap<>(mqProperties);
/*  381 */         propsNotPasswd.put("password", "********");
/*  382 */         Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getMQManagedConnectionFactory(String,String,Map<String,Object>)", new Object[] { transport, qmgrName, propsNotPasswd });
/*      */       } else {
/*      */         
/*  385 */         Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getMQManagedConnectionFactory(String,String,Map<String,Object>)", new Object[] { transport, qmgrName, mqProperties });
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  390 */     if (transport.equals("MQSeries Bindings")) {
/*  391 */       MQManagedConnectionFactory traceRet1 = new MQBindingsManagedConnectionFactoryJ11(qmgrName, mqProperties);
/*  392 */       if (Trace.isOn) {
/*  393 */         Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getMQManagedConnectionFactory(String,String,Hashtable<String,Object>)", traceRet1, 1);
/*      */       }
/*  395 */       return traceRet1;
/*      */     } 
/*  397 */     if (transport.equals("MQSeries Client")) {
/*  398 */       MQManagedConnectionFactory traceRet3 = new MQClientManagedConnectionFactoryJ11(qmgrName, mqProperties);
/*  399 */       if (Trace.isOn) {
/*  400 */         Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getMQManagedConnectionFactory(String,String,Hashtable<String,Object>)", traceRet3, 2);
/*      */       }
/*  402 */       return traceRet3;
/*      */     } 
/*      */ 
/*      */     
/*  406 */     MQException traceRet5 = new MQException(2, 2012, "static method in MQSESSION");
/*  407 */     if (Trace.isOn) {
/*  408 */       Trace.throwing("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getMQManagedConnectionFactory(String,String,Hashtable<String,Object>)", (Throwable)traceRet5);
/*      */     }
/*  410 */     throw traceRet5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String getProductPrefix() {
/*  418 */     if (Trace.isOn) {
/*  419 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getProductPrefix()");
/*      */     }
/*  421 */     if (zSeries) {
/*  422 */       if (Trace.isOn) {
/*  423 */         Trace.traceData("getProductPrefix", "CSQ", null);
/*      */       }
/*  425 */       if (Trace.isOn) {
/*  426 */         Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getProductPrefix()", "CSQ", 1);
/*      */       }
/*      */       
/*  429 */       return "CSQ";
/*      */     } 
/*  431 */     if (Trace.isOn) {
/*  432 */       Trace.traceData("getProductPrefix", "AMQ", null);
/*      */     }
/*  434 */     if (Trace.isOn) {
/*  435 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getProductPrefix()", "AMQ", 2);
/*      */     }
/*      */     
/*  438 */     return "AMQ";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static MQSESSION getSession(MQManagedConnectionJ11 mancon) throws MQException {
/*  445 */     if (Trace.isOn) {
/*  446 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getSession(MQManagedConnectionJ11)", new Object[] { mancon });
/*      */     }
/*      */ 
/*      */     
/*  450 */     MQSESSION retVal = null;
/*  451 */     Boolean threadAffinityObject = (Boolean)mancon.getProperty("Thread affinity");
/*  452 */     boolean requestedThreadAffinity = false;
/*  453 */     if (threadAffinityObject != null && threadAffinityObject.booleanValue()) {
/*  454 */       requestedThreadAffinity = true;
/*      */     }
/*      */     
/*  457 */     String threadingType = mancon.getStringProperty("Thread access");
/*  458 */     String transport = mancon.getStringProperty("transport");
/*  459 */     String hostname = mancon.getStringProperty("hostname", "");
/*      */ 
/*      */     
/*  462 */     if (transport.equals("MQSeries Bindings") || (transport.equals("MQSeries") && hostname.equals(""))) {
/*      */ 
/*      */       
/*  465 */       retVal = new MQSESSION(0, requestedThreadAffinity, threadingType);
/*      */ 
/*      */       
/*  468 */       retVal.mqManCon = mancon;
/*      */     } 
/*      */ 
/*      */     
/*  472 */     if (retVal == null) {
/*      */ 
/*      */       
/*  475 */       retVal = new MQSESSION(2, requestedThreadAffinity, threadingType);
/*      */ 
/*      */       
/*  478 */       retVal.mqManCon = mancon;
/*      */     } 
/*      */     
/*  481 */     if (Trace.isOn) {
/*  482 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getSession(MQManagedConnectionJ11)", retVal);
/*      */     }
/*      */     
/*  485 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String setStringToLength(String s, int length) {
/*  494 */     if (Trace.isOn) {
/*  495 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "setStringToLength(String,int)", new Object[] { s, 
/*  496 */             Integer.valueOf(length) });
/*      */     }
/*      */     
/*  499 */     String result = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  506 */     if (s != null && 
/*  507 */       s.length() == length) {
/*  508 */       if (Trace.isOn) {
/*  509 */         Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "setStringToLength(String,int)", s, 1);
/*      */       }
/*      */       
/*  512 */       return s;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  517 */     if (s == null) {
/*      */ 
/*      */       
/*  520 */       if (length <= 100) {
/*  521 */         result = new String(charInitArray, 0, length);
/*      */       } else {
/*  523 */         StringBuffer tmp = new StringBuffer(length);
/*  524 */         for (int extraChars = length; extraChars > 0; extraChars -= 100) {
/*  525 */           if (extraChars > 100) {
/*  526 */             tmp.append(charInitArray);
/*      */           } else {
/*  528 */             tmp.append(charInitArray, 0, extraChars);
/*      */           } 
/*      */         } 
/*  531 */         result = tmp.toString();
/*      */       }
/*      */     
/*      */     }
/*  535 */     else if (s.length() > length) {
/*      */       try {
/*  537 */         result = s.substring(0, length);
/*      */       }
/*  539 */       catch (StringIndexOutOfBoundsException e) {
/*  540 */         if (Trace.isOn) {
/*  541 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "setStringToLength(String,int)", e);
/*      */         }
/*      */         
/*  544 */         if (Trace.isOn) {
/*  545 */           Trace.traceData("MQSESSION", "setStringToLength - index error", null);
/*      */         }
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  551 */       StringBuffer tmp = new StringBuffer(s);
/*  552 */       tmp.ensureCapacity(length);
/*  553 */       for (int extraChars = length - s.length(); extraChars > 0; extraChars -= 100) {
/*  554 */         if (extraChars > 100) {
/*  555 */           tmp.append(charInitArray);
/*      */         } else {
/*  557 */           tmp.append(charInitArray, 0, extraChars);
/*      */         } 
/*      */       } 
/*  560 */       result = tmp.toString();
/*      */     } 
/*      */ 
/*      */     
/*  564 */     if (Trace.isOn) {
/*  565 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "setStringToLength(String,int)", result, 2);
/*      */     }
/*      */     
/*  568 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean authenticate(Hconn hconn, String userId, String password, Pint pCompCode, Pint pReason) {
/*      */     boolean traceRet1;
/*  577 */     if (Trace.isOn) {
/*  578 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "authenticate(Hconn,String,String,Pint,Pint)", new Object[] { hconn, userId, "************", pCompCode, pReason });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  584 */     this.jMQI.authenticate(hconn, userId, password, pCompCode, pReason);
/*      */     
/*  586 */     if (pCompCode.x == 0) {
/*  587 */       traceRet1 = true;
/*      */     } else {
/*  589 */       traceRet1 = false;
/*      */     } 
/*      */     
/*  592 */     if (Trace.isOn) {
/*  593 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "authenticate(Hconn,String,String,Pint,Pint)", 
/*  594 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  596 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getConnectionCCSID() {
/*  603 */     Hconn hconn = this.mqManCon.getHconn();
/*  604 */     int ccsid = 0;
/*      */     try {
/*  606 */       ccsid = hconn.getCcsid();
/*      */     }
/*  608 */     catch (JmqiException e) {
/*  609 */       if (Trace.isOn) {
/*  610 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getConnectionCCSID()", (Throwable)e);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  617 */     if (Trace.isOn) {
/*  618 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getConnectionCCSID()", "getter", 
/*  619 */           Integer.valueOf(ccsid));
/*      */     }
/*  621 */     return ccsid;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getJmqiBindType() {
/*  628 */     if (Trace.isOn) {
/*  629 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getJmqiBindType()", "getter", 
/*  630 */           Integer.valueOf(this.jmqiBindType));
/*      */     }
/*  632 */     return this.jmqiBindType;
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
/*      */   protected int getMaxMessageSizeForBatch() {
/*  648 */     if (Trace.isOn) {
/*  649 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getMaxMessageSizeForBatch()", "getter", 
/*  650 */           Integer.valueOf(0));
/*      */     }
/*  652 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getSupportsQAT2() {
/*  661 */     if (Trace.isOn) {
/*  662 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getSupportsQAT2()", "getter", 
/*  663 */           Boolean.valueOf(this.supportsQAT2));
/*      */     }
/*  665 */     return this.supportsQAT2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQChannelDefinition getMQCD() {
/*  674 */     MQChannelDefinition traceRet1 = this.mqManCon.cno.getMQCD();
/*  675 */     if (Trace.isOn) {
/*  676 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "getMQCD()", "getter", traceRet1);
/*      */     }
/*      */     
/*  679 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void honourRRS(Hconn hconn, Pint pCompCode, Pint pReason) {
/*  686 */     if (Trace.isOn) {
/*  687 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "honourRRS(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  692 */     JmqiSP mqsp = (JmqiSP)this.jMQI;
/*  693 */     mqsp.honourRRS(hconn, pCompCode, pReason);
/*      */     
/*  695 */     if (Trace.isOn) {
/*  696 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "honourRRS(Hconn,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void MQBACK(Hconn hconn, Pint pCompCode, Pint pReason) {
/*  703 */     if (Trace.isOn) {
/*  704 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQBACK(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*      */     }
/*      */     
/*  707 */     this.jMQI.MQBACK(hconn, pCompCode, pReason);
/*  708 */     if (Trace.isOn) {
/*  709 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQBACK(Hconn,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void MQBEGIN(Hconn hconn, Pint pCompCode, Pint pReason) {
/*  716 */     if (Trace.isOn) {
/*  717 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQBEGIN(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*      */     }
/*      */     
/*  720 */     this.jMQI.MQBEGIN(hconn, null, pCompCode, pReason);
/*      */     
/*  722 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/*  724 */     if (Trace.isOn) {
/*  725 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQBEGIN(Hconn,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void MQCLOSE(Hconn hconn, Phobj pHobj, int Options, Pint pCompCode, Pint pReason) {
/*  732 */     if (Trace.isOn) {
/*  733 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", new Object[] { hconn, pHobj, 
/*  734 */             Integer.valueOf(Options), pCompCode, pReason });
/*      */     }
/*      */     
/*  737 */     this.jMQI.MQCLOSE(hconn, pHobj, Options, pCompCode, pReason);
/*      */     
/*  739 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/*  741 */     if (Trace.isOn) {
/*  742 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void MQCMIT(Hconn hconn, Pint pCompCode, Pint pReason) {
/*  749 */     if (Trace.isOn) {
/*  750 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQCMIT(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*      */     }
/*      */     
/*  753 */     this.jMQI.MQCMIT(hconn, pCompCode, pReason);
/*      */     
/*  755 */     if (pCompCode.x == 1 && pReason.x == 2408) {
/*      */ 
/*      */       
/*  758 */       pCompCode.x = 0;
/*  759 */       pReason.x = 0;
/*      */     } 
/*      */     
/*  762 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/*  764 */     if (Trace.isOn) {
/*  765 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQCMIT(Hconn,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void MQCONNX_j(String pName, JmqiConnectOptions jmqiCno, MQConnectionOptions mqcno, Phconn pHconn, Pint pCompCode, Pint pReason) {
/*  772 */     if (Trace.isOn) {
/*  773 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQCONNX_j(String,JmqiConnectOptions,MQConnectionOptions,Phconn,Pint,Pint)", new Object[] { pName, jmqiCno, mqcno, pHconn, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  778 */     if (mqcno == null) {
/*  779 */       pCompCode.x = 2;
/*  780 */       pReason.x = 2139;
/*  781 */       if (Trace.isOn) {
/*  782 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQCONNX_j(String,JmqiConnectOptions,MQConnectionOptions,Phconn,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  789 */     MQCNO cno = mqcno.getJMQIStructure();
/*  790 */     int originalOptions = cno.getOptions();
/*  791 */     int options = originalOptions | 0x40;
/*  792 */     cno.setOptions(options);
/*      */ 
/*      */     
/*  795 */     this.jSPI.jmqiConnect(pName, jmqiCno, cno, null, pHconn, pCompCode, pReason);
/*      */ 
/*      */     
/*  798 */     cno.setOptions(originalOptions);
/*      */     
/*  800 */     errorOccured(pHconn, pCompCode.x, pReason.x);
/*      */     
/*  802 */     if (Trace.isOn) {
/*  803 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQCONNX_j(String,JmqiConnectOptions,MQConnectionOptions,Phconn,Pint,Pint)", 2);
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
/*      */   protected void MQDISC(Phconn pHconn, Pint pCompCode, Pint pReason) {
/*  819 */     if (Trace.isOn) {
/*  820 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQDISC(Phconn,Pint,Pint)", new Object[] { pHconn, pCompCode, pReason });
/*      */     }
/*      */     
/*  823 */     this.jMQI.MQDISC(pHconn, pCompCode, pReason);
/*      */     
/*  825 */     errorOccured(pHconn, pCompCode.x, pReason.x);
/*      */     
/*  827 */     if (Trace.isOn) {
/*  828 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQDISC(Phconn,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void MQGET(Hconn hconn, Hobj hobj, MQMD pMsgDesc, MQGetMessageOptions pGetMsgOpts, int BufferLength, ByteBuffer pBuffer, Pint pDataLength, Pint pCompCode, Pint pReason) {
/*      */     MQGMO jmqiGMO;
/*  835 */     if (Trace.isOn) {
/*  836 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQGET(Hconn,Hobj,MQMD,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, pMsgDesc, pGetMsgOpts, 
/*      */             
/*  838 */             Integer.valueOf(BufferLength), pBuffer, pDataLength, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  843 */     if (pBuffer == null) {
/*  844 */       pCompCode.x = 2;
/*  845 */       pReason.x = 2004;
/*  846 */       if (Trace.isOn) {
/*  847 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQGET(Hconn,Hobj,MQMD,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/*  856 */     if (pGetMsgOpts == null) {
/*  857 */       pCompCode.x = 2;
/*  858 */       pReason.x = 2186;
/*  859 */       if (Trace.isOn) {
/*  860 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQGET(Hconn,Hobj,MQMD,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*      */     try {
/*  867 */       jmqiGMO = pGetMsgOpts.getJMQIStructure(hconn);
/*      */     }
/*  869 */     catch (MQException e) {
/*  870 */       if (Trace.isOn) {
/*  871 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQGET(Hconn,Hobj,MQMD,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)e);
/*      */       }
/*      */       
/*  874 */       pCompCode.x = e.getCompCode();
/*  875 */       pReason.x = e.getReason();
/*  876 */       if (Trace.isOn) {
/*  877 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQGET(Hconn,Hobj,MQMD,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", 3);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  884 */     int originalOptions = jmqiGMO.getOptions();
/*  885 */     if ((originalOptions & 0x1006) == 0) {
/*  886 */       jmqiGMO.setOptions(originalOptions | 0x4);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  891 */     if (pMsgDesc == null) {
/*  892 */       pCompCode.x = 2;
/*  893 */       pReason.x = 2026;
/*  894 */       if (Trace.isOn) {
/*  895 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQGET(Hconn,Hobj,MQMD,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", 4);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  900 */     MQMD jmqiMD = pMsgDesc.getJMQIStructure(0);
/*      */ 
/*      */     
/*  903 */     this.jMQI.MQGET(hconn, hobj, jmqiMD, jmqiGMO, BufferLength, pBuffer, pDataLength, pCompCode, pReason);
/*      */ 
/*      */ 
/*      */     
/*  907 */     pGetMsgOpts.updateFromJMQIStructure();
/*  908 */     pMsgDesc.updateFromJMQIStructure(0);
/*      */     
/*  910 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/*  912 */     if (Trace.isOn) {
/*  913 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQGET(Hconn,Hobj,MQMD,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", 5);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void MQGET(Hconn hconn, Hobj hobj, MQMsg2 pMsg2, MQGetMessageOptions pGetMsgOpts, int BufferLength, ByteBuffer pBuffer, Pint pDataLength, Pint pCompCode, Pint pReason) {
/*      */     MQGMO jmqiGMO;
/*  923 */     if (Trace.isOn) {
/*  924 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQGET(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, pMsg2, pGetMsgOpts, 
/*      */             
/*  926 */             Integer.valueOf(BufferLength), pBuffer, pDataLength, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  931 */     if (pBuffer == null) {
/*  932 */       pCompCode.x = 2;
/*  933 */       pReason.x = 2004;
/*  934 */       if (Trace.isOn) {
/*  935 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQGET(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  942 */     if (pGetMsgOpts == null) {
/*  943 */       pCompCode.x = 2;
/*  944 */       pReason.x = 2186;
/*  945 */       if (Trace.isOn) {
/*  946 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQGET(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*      */     try {
/*  953 */       jmqiGMO = pGetMsgOpts.getJMQIStructure(hconn);
/*      */     }
/*  955 */     catch (MQException e) {
/*  956 */       if (Trace.isOn) {
/*  957 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQGET(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)e);
/*      */       }
/*      */       
/*  960 */       pCompCode.x = e.getCompCode();
/*  961 */       pReason.x = e.getReason();
/*  962 */       if (Trace.isOn) {
/*  963 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQGET(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  969 */     int originalOptions = jmqiGMO.getOptions();
/*  970 */     if ((originalOptions & 0x1006) == 0) {
/*  971 */       jmqiGMO.setOptions(originalOptions | 0x4);
/*      */     }
/*      */ 
/*      */     
/*  975 */     this.jMQI.MQGET(hconn, hobj, pMsg2.getJMQIStructure(), jmqiGMO, BufferLength, pBuffer, pDataLength, pCompCode, pReason);
/*      */ 
/*      */     
/*  978 */     jmqiGMO.setOptions(originalOptions);
/*      */ 
/*      */ 
/*      */     
/*  982 */     pGetMsgOpts.updateFromJMQIStructure();
/*      */     
/*  984 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/*  986 */     if (Trace.isOn) {
/*  987 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQGET(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", 4);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void MQINQ(Hconn hconn, Hobj hobj, int SelectorCount, int[] pSelectors, int IntAttrCount, int[] pIntAttrs, int CharAttrLength, byte[] pCharAttrs, Pint pCompCode, Pint pReason) {
/*  995 */     if (Trace.isOn) {
/*  996 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", new Object[] { hconn, hobj, 
/*      */             
/*  998 */             Integer.valueOf(SelectorCount), pSelectors, Integer.valueOf(IntAttrCount), pIntAttrs, 
/*  999 */             Integer.valueOf(CharAttrLength), pCharAttrs, pCompCode, pReason });
/*      */     }
/*      */     
/* 1002 */     this.jMQI.MQINQ(hconn, hobj, SelectorCount, pSelectors, IntAttrCount, pIntAttrs, CharAttrLength, pCharAttrs, pCompCode, pReason);
/*      */     
/* 1004 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1006 */     if (Trace.isOn) {
/* 1007 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQINQ(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String MQINQ(Hconn hconn, Hobj hobj, int Selector, int AttrLength, Pint pCompCode, Pint pReason) {
/* 1018 */     if (Trace.isOn) {
/* 1019 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQINQ(Hconn,Hobj,int,int,Pint,Pint)", new Object[] { hconn, hobj, 
/* 1020 */             Integer.valueOf(Selector), 
/* 1021 */             Integer.valueOf(AttrLength), pCompCode, pReason });
/*      */     }
/*      */     
/* 1024 */     int SelectorCount = 1;
/* 1025 */     int[] pSelectors = { Selector };
/* 1026 */     byte[] pCharAttrs = new byte[AttrLength];
/*      */     
/* 1028 */     this.jMQI.MQINQ(hconn, hobj, SelectorCount, pSelectors, 0, null, AttrLength, pCharAttrs, pCompCode, pReason);
/*      */     
/* 1030 */     int queueManagerCcid = 0;
/* 1031 */     JmqiCodepage queueManagerCodepage = null;
/* 1032 */     String result = null;
/*      */     
/*      */     try {
/* 1035 */       queueManagerCcid = hconn.getCcsid();
/* 1036 */       queueManagerCodepage = JmqiCodepage.getJmqiCodepage(jmqiEnv, queueManagerCcid);
/*      */       
/* 1038 */       if (queueManagerCodepage == null) {
/*      */         
/* 1040 */         UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException(Integer.toString(queueManagerCcid));
/* 1041 */         if (Trace.isOn) {
/* 1042 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQINQ(Hconn,Hobj,int,int,Pint,Pint)", traceRet1);
/*      */         }
/*      */         
/* 1045 */         throw traceRet1;
/*      */       } 
/*      */ 
/*      */       
/* 1049 */       result = queueManagerCodepage.bytesToString(pCharAttrs);
/*      */     }
/* 1051 */     catch (JmqiException e) {
/* 1052 */       if (Trace.isOn) {
/* 1053 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQINQ(Hconn,Hobj,int,int,Pint,Pint)", (Throwable)e, 1);
/*      */       }
/*      */       
/* 1056 */       HashMap<String, Object> data = new HashMap<>();
/* 1057 */       data.put("reason", Integer.toString(e.getReason()));
/* 1058 */       data.put("ccsid", Integer.toString(queueManagerCcid));
/* 1059 */       data.put("codepage", queueManagerCodepage);
/* 1060 */       data.put("exception", e);
/* 1061 */       Trace.ffst(this, "MQINQ", "XO004005", data, null);
/*      */     }
/* 1063 */     catch (UnsupportedEncodingException|java.nio.charset.CharacterCodingException e) {
/* 1064 */       if (Trace.isOn) {
/* 1065 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQINQ(Hconn,Hobj,int,int,Pint,Pint)", e, 2);
/*      */       }
/*      */       
/* 1068 */       HashMap<String, Object> data = new HashMap<>();
/* 1069 */       data.put("ccsid", Integer.toString(queueManagerCcid));
/* 1070 */       data.put("codepage", queueManagerCodepage);
/* 1071 */       data.put("exception", e);
/* 1072 */       Trace.ffst(this, "MQINQ", "XO004006", data, null);
/*      */     } 
/*      */     
/* 1075 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1077 */     if (Trace.isOn) {
/* 1078 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQINQ(Hconn,Hobj,int,int,Pint,Pint)", result);
/*      */     }
/*      */     
/* 1081 */     return result;
/*      */   }
/*      */   
/*      */   protected void MQOPEN(Hconn hconn, MQOD pObjDesc, int Options, Phobj pHobj, Pint pCompCode, Pint pReason) {
/* 1085 */     if (Trace.isOn) {
/* 1086 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", new Object[] { hconn, pObjDesc, 
/*      */             
/* 1088 */             Integer.valueOf(Options), pHobj, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/* 1092 */     if (pObjDesc == null) {
/* 1093 */       pCompCode.x = 2;
/* 1094 */       pReason.x = 2044;
/* 1095 */       if (Trace.isOn) {
/* 1096 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1101 */     MQOD jmqiMQOD = pObjDesc.getJMQIStructure();
/*      */     
/* 1103 */     this.jMQI.MQOPEN(hconn, jmqiMQOD, Options, pHobj, pCompCode, pReason);
/*      */ 
/*      */     
/* 1106 */     pObjDesc.updateFromJMQIStructure();
/*      */     
/* 1108 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1110 */     if (Trace.isOn) {
/* 1111 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void MQPUT(Hconn hconn, Hobj hobj, MQMD pMsgDesc, MQPutMessageOptions pPutMsgOpts, int BufferLength, ByteBuffer pBuffer, Pint pCompCode, Pint pReason) {
/* 1118 */     if (Trace.isOn) {
/* 1119 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT(Hconn,Hobj,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, pMsgDesc, pPutMsgOpts, 
/*      */             
/* 1121 */             Integer.valueOf(BufferLength), pBuffer, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/* 1125 */     if (pBuffer == null) {
/* 1126 */       pCompCode.x = 2;
/* 1127 */       pReason.x = 2004;
/* 1128 */       if (Trace.isOn) {
/* 1129 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT(Hconn,Hobj,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1136 */     if (pPutMsgOpts == null) {
/* 1137 */       pCompCode.x = 2;
/* 1138 */       pReason.x = 2173;
/* 1139 */       if (Trace.isOn) {
/* 1140 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT(Hconn,Hobj,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1146 */     MQPMO jmqiPMO = pPutMsgOpts.getJMQIStructure();
/* 1147 */     int originalOptions = jmqiPMO.getOptions();
/*      */ 
/*      */     
/* 1150 */     if ((originalOptions & 0x6) == 0) {
/* 1151 */       jmqiPMO.setOptions(originalOptions | 0x4);
/*      */     }
/*      */ 
/*      */     
/* 1155 */     if (pMsgDesc == null) {
/* 1156 */       pCompCode.x = 2;
/* 1157 */       pReason.x = 2026;
/* 1158 */       if (Trace.isOn) {
/* 1159 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT(Hconn,Hobj,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1164 */     MQMD jmqiMD = pMsgDesc.getJMQIStructure(originalOptions);
/*      */ 
/*      */     
/* 1167 */     this.jMQI.MQPUT(hconn, hobj, jmqiMD, jmqiPMO, BufferLength, pBuffer, pCompCode, pReason);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1172 */     pMsgDesc.updateFromJMQIStructure(originalOptions);
/*      */ 
/*      */     
/* 1175 */     jmqiPMO.setOptions(originalOptions);
/*      */ 
/*      */     
/* 1178 */     pPutMsgOpts.updateFromJMQIStructure();
/*      */     
/* 1180 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1182 */     if (Trace.isOn) {
/* 1183 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT(Hconn,Hobj,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 4);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void MQPUT(Hconn hconn, Hobj hobj, MQMsg2 pMsg2, MQPutMessageOptions pPutMsgOpts, int BufferLength, ByteBuffer pBuffer, Pint pCompCode, Pint pReason) {
/* 1193 */     if (Trace.isOn) {
/* 1194 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, pMsg2, pPutMsgOpts, 
/*      */             
/* 1196 */             Integer.valueOf(BufferLength), pBuffer, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1201 */     if (pBuffer == null) {
/* 1202 */       pCompCode.x = 2;
/* 1203 */       pReason.x = 2004;
/* 1204 */       if (Trace.isOn) {
/* 1205 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1212 */     if (pPutMsgOpts == null) {
/* 1213 */       pCompCode.x = 2;
/* 1214 */       pReason.x = 2173;
/* 1215 */       if (Trace.isOn) {
/* 1216 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1221 */     MQPMO jmqiPMO = pPutMsgOpts.getJMQIStructure();
/* 1222 */     int originalOptions = jmqiPMO.getOptions();
/*      */ 
/*      */     
/* 1225 */     if ((originalOptions & 0x6) == 0) {
/* 1226 */       jmqiPMO.setOptions(originalOptions | 0x4);
/*      */     }
/*      */ 
/*      */     
/* 1230 */     this.jMQI.MQPUT(hconn, hobj, pMsg2.getJMQIStructure(), jmqiPMO, BufferLength, pBuffer, pCompCode, pReason);
/*      */ 
/*      */     
/* 1233 */     jmqiPMO.setOptions(originalOptions);
/*      */ 
/*      */     
/* 1236 */     pPutMsgOpts.updateFromJMQIStructure();
/*      */     
/* 1238 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1240 */     if (Trace.isOn) {
/* 1241 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 3);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void MQPUT1(Hconn hconn, MQOD pObjDesc, MQMD pMsgDesc, MQPutMessageOptions pPutMsgOpts, int BufferLength, ByteBuffer pBuffer, Pint pCompCode, Pint pReason) {
/* 1248 */     if (Trace.isOn) {
/* 1249 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT1(Hconn,MQOD,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, pObjDesc, pMsgDesc, pPutMsgOpts, 
/*      */             
/* 1251 */             Integer.valueOf(BufferLength), pBuffer, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1256 */     if (pBuffer == null) {
/* 1257 */       pCompCode.x = 2;
/* 1258 */       pReason.x = 2004;
/* 1259 */       if (Trace.isOn) {
/* 1260 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT1(Hconn,MQOD,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1267 */     if (pObjDesc == null) {
/* 1268 */       pCompCode.x = 2;
/* 1269 */       pReason.x = 2044;
/* 1270 */       if (Trace.isOn) {
/* 1271 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT1(Hconn,MQOD,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1276 */     MQOD jmqiOD = pObjDesc.getJMQIStructure();
/*      */ 
/*      */     
/* 1279 */     if (pPutMsgOpts == null) {
/* 1280 */       pCompCode.x = 2;
/* 1281 */       pReason.x = 2173;
/* 1282 */       if (Trace.isOn) {
/* 1283 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT1(Hconn,MQOD,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1288 */     MQPMO jmqiPMO = pPutMsgOpts.getJMQIStructure();
/* 1289 */     int originalOptions = jmqiPMO.getOptions();
/*      */ 
/*      */     
/* 1292 */     if ((originalOptions & 0x6) == 0) {
/* 1293 */       jmqiPMO.setOptions(originalOptions | 0x4);
/*      */     }
/*      */ 
/*      */     
/* 1297 */     if (pMsgDesc == null) {
/* 1298 */       pCompCode.x = 2;
/* 1299 */       pReason.x = 2026;
/* 1300 */       if (Trace.isOn) {
/* 1301 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT1(Hconn,MQOD,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 4);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1306 */     MQMD jmqiMD = pMsgDesc.getJMQIStructure(originalOptions);
/*      */ 
/*      */     
/* 1309 */     this.jMQI.MQPUT1(hconn, jmqiOD, jmqiMD, jmqiPMO, BufferLength, pBuffer, pCompCode, pReason);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1314 */     pMsgDesc.updateFromJMQIStructure(originalOptions);
/*      */ 
/*      */     
/* 1317 */     jmqiPMO.setOptions(originalOptions);
/*      */ 
/*      */     
/* 1320 */     pPutMsgOpts.updateFromJMQIStructure();
/*      */ 
/*      */     
/* 1323 */     pObjDesc.updateFromJMQIStructure();
/*      */     
/* 1325 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1327 */     if (Trace.isOn) {
/* 1328 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT1(Hconn,MQOD,MQMD,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 5);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void MQPUT1(Hconn hconn, MQOD pObjDesc, MQMsg2 pMsg2, MQPutMessageOptions pPutMsgOpts, int BufferLength, ByteBuffer pBuffer, Pint pCompCode, Pint pReason) {
/* 1339 */     if (Trace.isOn) {
/* 1340 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT1(Hconn,MQOD,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, pObjDesc, pMsg2, pPutMsgOpts, 
/*      */             
/* 1342 */             Integer.valueOf(BufferLength), pBuffer, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1347 */     if (pBuffer == null) {
/* 1348 */       pCompCode.x = 2;
/* 1349 */       pReason.x = 2004;
/* 1350 */       if (Trace.isOn) {
/* 1351 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT1(Hconn,MQOD,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1358 */     if (pObjDesc == null) {
/* 1359 */       pCompCode.x = 2;
/* 1360 */       pReason.x = 2044;
/* 1361 */       if (Trace.isOn) {
/* 1362 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT1(Hconn,MQOD,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1367 */     MQOD jmqiOD = pObjDesc.getJMQIStructure();
/*      */ 
/*      */     
/* 1370 */     if (pPutMsgOpts == null) {
/* 1371 */       pCompCode.x = 2;
/* 1372 */       pReason.x = 2173;
/* 1373 */       if (Trace.isOn) {
/* 1374 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT1(Hconn,MQOD,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1379 */     MQPMO jmqiPMO = pPutMsgOpts.getJMQIStructure();
/* 1380 */     int originalOptions = jmqiPMO.getOptions();
/*      */ 
/*      */     
/* 1383 */     if ((originalOptions & 0x6) == 0) {
/* 1384 */       jmqiPMO.setOptions(originalOptions | 0x4);
/*      */     }
/*      */ 
/*      */     
/* 1388 */     this.jMQI.MQPUT1(hconn, jmqiOD, pMsg2.getJMQIStructure(), jmqiPMO, BufferLength, pBuffer, pCompCode, pReason);
/*      */ 
/*      */     
/* 1391 */     jmqiPMO.setOptions(originalOptions);
/*      */ 
/*      */     
/* 1394 */     pPutMsgOpts.updateFromJMQIStructure();
/*      */ 
/*      */     
/* 1397 */     pObjDesc.updateFromJMQIStructure();
/*      */     
/* 1399 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1401 */     if (Trace.isOn) {
/* 1402 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQPUT1(Hconn,MQOD,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 4);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void MQSET(Hconn hconn, Hobj hobj, int SelectorCount, int[] pSelectors, int IntAttrCount, int[] pIntAttrs, int CharAttrLength, byte[] pCharAttrs, Pint pCompCode, Pint pReason) {
/* 1410 */     if (Trace.isOn) {
/* 1411 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)", new Object[] { hconn, hobj, 
/*      */             
/* 1413 */             Integer.valueOf(SelectorCount), pSelectors, Integer.valueOf(IntAttrCount), pIntAttrs, 
/* 1414 */             Integer.valueOf(CharAttrLength), pCharAttrs, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/* 1418 */     this.jMQI.MQSET(hconn, hobj, SelectorCount, pSelectors, IntAttrCount, pIntAttrs, CharAttrLength, pCharAttrs, pCompCode, pReason);
/*      */     
/* 1420 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1422 */     if (Trace.isOn) {
/* 1423 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQSET(Hconn,Hobj,int,int [ ],int,int [ ],int,byte [ ],Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void MQSET(Hconn hconn, Hobj hobj, int Selector, String Attr, int AttrLength, Pint pCompCode, Pint pReason) {
/* 1434 */     if (Trace.isOn) {
/* 1435 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQSET(Hconn,Hobj,int,String,int,Pint,Pint)", new Object[] { hconn, hobj, 
/*      */             
/* 1437 */             Integer.valueOf(Selector), Attr, Integer.valueOf(AttrLength), pCompCode, pReason });
/*      */     }
/*      */     
/* 1440 */     int SelectorCount = 1;
/* 1441 */     int[] pSelectors = { Selector };
/* 1442 */     byte[] pCharAttrs = null;
/*      */     
/* 1444 */     int queueManagerCcid = 0;
/* 1445 */     JmqiCodepage queueManagerCodepage = null;
/*      */     
/*      */     try {
/* 1448 */       queueManagerCcid = hconn.getCcsid();
/* 1449 */       queueManagerCodepage = JmqiCodepage.getJmqiCodepage(jmqiEnv, queueManagerCcid);
/*      */       
/* 1451 */       if (queueManagerCodepage == null) {
/*      */         
/* 1453 */         UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException(Integer.toString(queueManagerCcid));
/* 1454 */         if (Trace.isOn) {
/* 1455 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQSET(Hconn,Hobj,int,String,int,Pint,Pint)", traceRet1);
/*      */         }
/*      */         
/* 1458 */         throw traceRet1;
/*      */       } 
/*      */ 
/*      */       
/* 1462 */       pCharAttrs = queueManagerCodepage.stringToBytes(Attr);
/*      */     }
/* 1464 */     catch (JmqiException e) {
/* 1465 */       if (Trace.isOn) {
/* 1466 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQSET(Hconn,Hobj,int,String,int,Pint,Pint)", (Throwable)e, 1);
/*      */       }
/*      */       
/* 1469 */       HashMap<String, Object> data = new HashMap<>();
/* 1470 */       data.put("reason", Integer.toString(e.getReason()));
/* 1471 */       data.put("ccsid", Integer.toString(queueManagerCcid));
/* 1472 */       data.put("codepage", queueManagerCodepage);
/* 1473 */       data.put("exception", e);
/* 1474 */       Trace.ffst(this, "MQSET", "XO004007", data, null);
/*      */     }
/* 1476 */     catch (UnsupportedEncodingException|java.nio.charset.CharacterCodingException e) {
/* 1477 */       if (Trace.isOn) {
/* 1478 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQSET(Hconn,Hobj,int,String,int,Pint,Pint)", e, 2);
/*      */       }
/*      */       
/* 1481 */       HashMap<String, Object> data = new HashMap<>();
/* 1482 */       data.put("ccsid", Integer.toString(queueManagerCcid));
/* 1483 */       data.put("codepage", queueManagerCodepage);
/* 1484 */       data.put("exception", e);
/* 1485 */       Trace.ffst(this, "MQSET", "XO004008", data, null);
/*      */     } 
/*      */     
/* 1488 */     this.jMQI.MQSET(hconn, hobj, SelectorCount, pSelectors, 0, null, AttrLength, pCharAttrs, pCompCode, pReason);
/*      */     
/* 1490 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1492 */     if (Trace.isOn) {
/* 1493 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "MQSET(Hconn,Hobj,int,String,int,Pint,Pint)");
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
/*      */   protected boolean resolveV2Support(Hconn hconn, Hobj hobj, Pint pCompCode, Pint pReason) {
/* 1513 */     if (Trace.isOn) {
/* 1514 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "resolveV2Support(Hconn,Hobj,Pint,Pint)", new Object[] { hconn, hobj, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1519 */       int platform = hconn.getPlatform();
/* 1520 */       int cmdLevel = hconn.getCmdLevel();
/*      */       
/* 1522 */       switch (this.jmqiBindType) {
/*      */         case 0:
/* 1524 */           if (platform == 1) {
/* 1525 */             this.supportsQAT2 = true;
/*      */           }
/*      */           break;
/*      */         
/*      */         case 1:
/*      */         case 2:
/* 1531 */           if (platform == 1) {
/* 1532 */             if (cmdLevel >= 600) {
/* 1533 */               this.supportsQAT2 = true;
/*      */             }
/* 1535 */             if (cmdLevel >= 530)
/* 1536 */               this.v2StructuresSupported = true;  break;
/*      */           } 
/* 1538 */           if (cmdLevel >= 500) {
/* 1539 */             this.v2StructuresSupported = true;
/*      */           }
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1547 */     } catch (JmqiException e) {
/* 1548 */       if (Trace.isOn) {
/* 1549 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "resolveV2Support(Hconn,Hobj,Pint,Pint)", (Throwable)e);
/*      */       }
/*      */       
/* 1552 */       pCompCode.x = e.getCompCode();
/* 1553 */       pReason.x = e.getReason();
/*      */     } 
/* 1555 */     if (Trace.isOn) {
/* 1556 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "resolveV2Support(Hconn,Hobj,Pint,Pint)", 
/* 1557 */           Boolean.valueOf(this.v2StructuresSupported));
/*      */     }
/* 1559 */     return this.v2StructuresSupported;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void spiAsyncCmit(Hconn hconn, Pint pCompCode, Pint pReason) {
/* 1564 */     if (Trace.isOn) {
/* 1565 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiAsyncCmit(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/* 1569 */     SpiSyncPointOptions spiSyncPointOptions = jmqiSysEnv.newSpiSyncPointOptions();
/* 1570 */     spiSyncPointOptions.setActions(SpiSyncPointOptions.lpiSYNCPT_ASYNC_COMMIT);
/*      */ 
/*      */     
/* 1573 */     this.jSPI.spiSyncPoint(hconn, spiSyncPointOptions, pCompCode, pReason);
/*      */     
/* 1575 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1577 */     if (Trace.isOn) {
/* 1578 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiAsyncCmit(Hconn,Pint,Pint)");
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
/*      */   protected void spiBatchedGet(Hconn hconn, Hobj hobj, MQMsg2 pMsg2Desc, MQGetMessageOptions pGetMsgOpts, int BufferLength, ByteBuffer pBuffer, Pint pDataLength, Pint pCompCode, Pint pReason) throws MQException {
/* 1592 */     if (Trace.isOn) {
/* 1593 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiBatchedGet(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, pMsg2Desc, pGetMsgOpts, 
/*      */             
/* 1595 */             Integer.valueOf(BufferLength), pBuffer, pDataLength, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/* 1599 */     MQGET(hconn, hobj, pMsg2Desc, pGetMsgOpts, BufferLength, pBuffer, pDataLength, pCompCode, pReason);
/*      */     
/* 1601 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1603 */     if (Trace.isOn) {
/* 1604 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiBatchedGet(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,ByteBuffer,Pint,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void spiDefActivate(Hconn hconn, byte[] msgId, Pint pCompCode, Pint pReason) {
/* 1612 */     if (Trace.isOn) {
/* 1613 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiDefActivate(Hconn,byte [ ],Pint,Pint)", new Object[] { hconn, msgId, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1618 */     SpiActivate spiActivate = jmqiSysEnv.newSpiActivate();
/* 1619 */     spiActivate.setMsgId(msgId);
/* 1620 */     spiActivate.setOptions(1);
/*      */ 
/*      */     
/* 1623 */     this.jSPI.spiActivateMessage(hconn, spiActivate, pCompCode, pReason);
/*      */     
/* 1625 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1627 */     if (Trace.isOn) {
/* 1628 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiDefActivate(Hconn,byte [ ],Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void spiDefCancel(Hconn hconn, byte[] msgId, Pint pCompCode, Pint pReason) {
/* 1636 */     if (Trace.isOn) {
/* 1637 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiDefCancel(Hconn,byte [ ],Pint,Pint)", new Object[] { hconn, msgId, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/* 1641 */     SpiActivate spiActivate = jmqiSysEnv.newSpiActivate();
/* 1642 */     spiActivate.setMsgId(msgId);
/* 1643 */     spiActivate.setOptions(2);
/*      */ 
/*      */     
/* 1646 */     this.jSPI.spiActivateMessage(hconn, spiActivate, pCompCode, pReason);
/*      */     
/* 1648 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1650 */     if (Trace.isOn) {
/* 1651 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiDefCancel(Hconn,byte [ ],Pint,Pint)");
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
/*      */   protected void spiDefPut(Hconn hconn, Hobj hobj, MQMsg2 pMsg2, MQPutMessageOptions pPutMsgOpts, int BufferLength, ByteBuffer pBuffer, Pint pCompCode, Pint pReason) {
/* 1663 */     if (Trace.isOn) {
/* 1664 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiDefPut(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, pMsg2, pPutMsgOpts, 
/*      */             
/* 1666 */             Integer.valueOf(BufferLength), pBuffer, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1671 */     if (pBuffer == null) {
/* 1672 */       pCompCode.x = 2;
/* 1673 */       pReason.x = 2004;
/* 1674 */       if (Trace.isOn) {
/* 1675 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiDefPut(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1682 */     if (pPutMsgOpts == null) {
/* 1683 */       pCompCode.x = 2;
/* 1684 */       pReason.x = 2173;
/* 1685 */       if (Trace.isOn) {
/* 1686 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiDefPut(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1691 */     MQPMO jmqiPMO = pPutMsgOpts.getJMQIStructure();
/* 1692 */     int originalOptions = jmqiPMO.getOptions();
/*      */ 
/*      */     
/* 1695 */     if ((originalOptions & 0x6) == 0) {
/* 1696 */       jmqiPMO.setOptions(originalOptions | 0x4);
/*      */     }
/*      */     
/* 1699 */     SpiPutOptions spiPutOptions = jmqiSysEnv.newSpiPutOptions();
/* 1700 */     spiPutOptions.setOptions(4);
/*      */ 
/*      */     
/* 1703 */     this.jSPI.spiPut(hconn, hobj, pMsg2.getJMQIStructure(), jmqiPMO, spiPutOptions, BufferLength, pBuffer, pCompCode, pReason);
/*      */ 
/*      */     
/* 1706 */     jmqiPMO.setOptions(originalOptions);
/*      */ 
/*      */     
/* 1709 */     pPutMsgOpts.updateFromJMQIStructure();
/*      */     
/* 1711 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1713 */     if (Trace.isOn) {
/* 1714 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiDefPut(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,ByteBuffer,Pint,Pint)", 3);
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
/*      */   protected void spiGet(Hconn hconn, Hobj hobj, MQMsg2 pMsg2, MQGetMessageOptions pGetMsgOpts, int spiOpts, int BufferLength, ByteBuffer pBuffer, Pint pDataLength, Pint pCompCode, Pint pReason) throws MQException {
/* 1739 */     if (Trace.isOn) {
/* 1740 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiGet(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, pMsg2, pGetMsgOpts, 
/*      */             
/* 1742 */             Integer.valueOf(spiOpts), 
/* 1743 */             Integer.valueOf(BufferLength), pBuffer, pDataLength, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */     
/* 1747 */     if (pBuffer == null) {
/* 1748 */       pCompCode.x = 2;
/* 1749 */       pReason.x = 2004;
/* 1750 */       if (Trace.isOn) {
/* 1751 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiGet(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,int,ByteBuffer,Pint,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1758 */     if (pGetMsgOpts == null) {
/* 1759 */       pCompCode.x = 2;
/* 1760 */       pReason.x = 2186;
/* 1761 */       if (Trace.isOn) {
/* 1762 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiGet(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,int,ByteBuffer,Pint,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1768 */     MQGMO jmqiGMO = null;
/*      */     try {
/* 1770 */       jmqiGMO = pGetMsgOpts.getJMQIStructure(hconn);
/*      */     }
/* 1772 */     catch (MQException e) {
/* 1773 */       if (Trace.isOn) {
/* 1774 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiGet(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)e, 1);
/*      */       }
/*      */       
/* 1777 */       pCompCode.x = e.getCompCode();
/* 1778 */       pReason.x = e.getReason();
/* 1779 */       if (Trace.isOn) {
/* 1780 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiGet(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,int,ByteBuffer,Pint,Pint,Pint)", 5);
/*      */       }
/*      */       
/* 1783 */       if (Trace.isOn) {
/* 1784 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiGet(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,int,ByteBuffer,Pint,Pint,Pint)", 3);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1791 */     int originalOptions = jmqiGMO.getOptions();
/* 1792 */     if ((originalOptions & 0x1006) == 0) {
/* 1793 */       jmqiGMO.setOptions(originalOptions | 0x4);
/*      */     }
/*      */     
/* 1796 */     SpiGetOptions spiGetOptions = jmqiSysEnv.newSpiGetOptions();
/* 1797 */     spiGetOptions.setVersion(2);
/* 1798 */     spiGetOptions.setOptions(spiOpts);
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1803 */       boolean isZOS = (hconn.getPlatform() == 1);
/* 1804 */       spiGetOptions.setZOS(isZOS);
/*      */     }
/* 1806 */     catch (JmqiException e) {
/* 1807 */       if (Trace.isOn) {
/* 1808 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiGet(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,int,ByteBuffer,Pint,Pint,Pint)", (Throwable)e, 2);
/*      */       }
/*      */       
/* 1811 */       pCompCode.x = 2;
/* 1812 */       pReason.x = 2195;
/* 1813 */       if (Trace.isOn) {
/* 1814 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiGet(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,int,ByteBuffer,Pint,Pint,Pint)", 4);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1821 */     this.jSPI.spiGet(hconn, hobj, pMsg2.getJMQIStructure(), jmqiGMO, spiGetOptions, BufferLength, pBuffer, pDataLength, pCompCode, pReason);
/*      */ 
/*      */     
/* 1824 */     jmqiGMO.setOptions(originalOptions);
/*      */ 
/*      */ 
/*      */     
/* 1828 */     pGetMsgOpts.updateFromJMQIStructure();
/*      */     
/* 1830 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1832 */     if (Trace.isOn) {
/* 1833 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiGet(Hconn,Hobj,MQMsg2,MQGetMessageOptions,int,int,ByteBuffer,Pint,Pint,Pint)", 5);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void spiPut(Hconn hconn, Hobj hobj, MQMsg2 pMsg2, MQPutMessageOptions pPutMsgOpts, int spiOptions, int BufferLength, ByteBuffer pBuffer, Pint pCompCode, Pint pReason) {
/* 1840 */     if (Trace.isOn) {
/* 1841 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiPut(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, pMsg2, pPutMsgOpts, 
/*      */             
/* 1843 */             Integer.valueOf(spiOptions), Integer.valueOf(BufferLength), pBuffer, pCompCode, pReason });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1848 */     if (pBuffer == null) {
/* 1849 */       pCompCode.x = 2;
/* 1850 */       pReason.x = 2004;
/* 1851 */       if (Trace.isOn) {
/* 1852 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiPut(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,int,ByteBuffer,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1859 */     if (pPutMsgOpts == null) {
/* 1860 */       pCompCode.x = 2;
/* 1861 */       pReason.x = 2173;
/* 1862 */       if (Trace.isOn) {
/* 1863 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiPut(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,int,ByteBuffer,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1868 */     MQPMO jmqiPMO = pPutMsgOpts.getJMQIStructure();
/* 1869 */     int originalOptions = jmqiPMO.getOptions();
/*      */ 
/*      */     
/* 1872 */     if ((originalOptions & 0x6) == 0) {
/* 1873 */       jmqiPMO.setOptions(originalOptions | 0x4);
/*      */     }
/*      */     
/* 1876 */     SpiPutOptions spiPutOptions = jmqiSysEnv.newSpiPutOptions();
/* 1877 */     spiPutOptions.setOptions(spiOptions);
/*      */ 
/*      */     
/* 1880 */     this.jSPI.spiPut(hconn, hobj, pMsg2.getJMQIStructure(), jmqiPMO, spiPutOptions, BufferLength, pBuffer, pCompCode, pReason);
/*      */ 
/*      */     
/* 1883 */     jmqiPMO.setOptions(originalOptions);
/*      */ 
/*      */     
/* 1886 */     pPutMsgOpts.updateFromJMQIStructure();
/*      */     
/* 1888 */     errorOccured(hconn, pCompCode.x, pReason.x);
/*      */     
/* 1890 */     if (Trace.isOn) {
/* 1891 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiPut(Hconn,Hobj,MQMsg2,MQPutMessageOptions,int,int,ByteBuffer,Pint,Pint)", 3);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean spiSupportsDeferred() {
/* 1898 */     if (Trace.isOn) {
/* 1899 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiSupportsDeferred()");
/*      */     }
/*      */     
/* 1902 */     if (supportsDeferred == null) {
/* 1903 */       Pint pCompCode = jmqiEnv.newPint();
/* 1904 */       Pint pReason = jmqiEnv.newPint();
/* 1905 */       boolean isSupported = false;
/*      */ 
/*      */       
/* 1908 */       JmqiMetaData metadata = jmqiSysEnv.newJmqiMetaData();
/* 1909 */       metadata.setVersion(1);
/* 1910 */       this.jSPI.getMetaData(metadata, pCompCode, pReason);
/* 1911 */       if (pCompCode.x == 0 && pReason.x == 0) {
/* 1912 */         int flags = metadata.getFlags();
/*      */         
/* 1914 */         isSupported = true;
/* 1915 */         if ((flags & 0x2) == 0) {
/* 1916 */           isSupported = false;
/* 1917 */         } else if ((flags & 0x10) == 0) {
/* 1918 */           isSupported = false;
/*      */         } 
/*      */       } 
/*      */       
/* 1922 */       supportsDeferred = Boolean.valueOf(isSupported);
/*      */     } 
/* 1924 */     boolean traceRet1 = supportsDeferred.booleanValue();
/* 1925 */     if (Trace.isOn) {
/* 1926 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiSupportsDeferred()", 
/* 1927 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1929 */     return traceRet1;
/*      */   }
/*      */   
/*      */   protected boolean spiSupportsInherited() {
/* 1933 */     if (Trace.isOn) {
/* 1934 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiSupportsInherited()");
/*      */     }
/*      */     
/* 1937 */     if (supportsInherited == null) {
/* 1938 */       Pint pCompCode = jmqiEnv.newPint();
/* 1939 */       Pint pReason = jmqiEnv.newPint();
/* 1940 */       boolean isSupported = false;
/*      */ 
/*      */       
/* 1943 */       JmqiMetaData metadata = jmqiSysEnv.newJmqiMetaData();
/* 1944 */       metadata.setVersion(1);
/* 1945 */       this.jSPI.getMetaData(metadata, pCompCode, pReason);
/* 1946 */       if (pCompCode.x == 0 && pReason.x == 0) {
/* 1947 */         int flags = metadata.getFlags();
/*      */         
/* 1949 */         isSupported = true;
/* 1950 */         if ((flags & 0x8) == 0) {
/* 1951 */           isSupported = false;
/* 1952 */         } else if ((flags & 0x4) == 0) {
/* 1953 */           isSupported = false;
/*      */         } 
/*      */       } 
/*      */       
/* 1957 */       supportsInherited = Boolean.valueOf(isSupported);
/*      */     } 
/* 1959 */     boolean traceRet1 = supportsInherited.booleanValue();
/* 1960 */     if (Trace.isOn) {
/* 1961 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "spiSupportsInherited()", 
/* 1962 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1964 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int XACLOSE(String name, int rmid, int flags) throws Exception {
/* 1972 */     if (Trace.isOn) {
/* 1973 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XACLOSE(String,int,int)", new Object[] { name, 
/* 1974 */             Integer.valueOf(rmid), 
/* 1975 */             Integer.valueOf(flags) });
/*      */     }
/* 1977 */     Hconn hconn = this.mqManCon.getHconn();
/* 1978 */     int result = this.jXA.xa_close(hconn, name, rmid, flags);
/* 1979 */     if (Trace.isOn) {
/* 1980 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XACLOSE(String,int,int)", 
/* 1981 */           Integer.valueOf(result));
/*      */     }
/* 1983 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int XACOMMIT(Xid xid, int rmid, int flags) throws Exception {
/* 1992 */     if (Trace.isOn) {
/* 1993 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XACOMMIT(Xid,int,int)", new Object[] { xid, 
/* 1994 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*      */     }
/*      */     
/* 1997 */     Hconn hconn = this.mqManCon.getHconn();
/* 1998 */     int result = this.jXA.xa_commit(hconn, xid, rmid, flags);
/* 1999 */     if (Trace.isOn) {
/* 2000 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XACOMMIT(Xid,int,int)", 
/* 2001 */           Integer.valueOf(result));
/*      */     }
/* 2003 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int XAEND(Xid xid, int rmid, int flags) throws Exception {
/* 2012 */     if (Trace.isOn) {
/* 2013 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XAEND(Xid,int,int)", new Object[] { xid, 
/* 2014 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*      */     }
/* 2016 */     Hconn hconn = this.mqManCon.getHconn();
/* 2017 */     int result = this.jXA.xa_end(hconn, xid, rmid, flags);
/* 2018 */     if (Trace.isOn) {
/* 2019 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XAEND(Xid,int,int)", 
/* 2020 */           Integer.valueOf(result));
/*      */     }
/* 2022 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int XAFORGET(Xid xid, int rmid, int flags) throws Exception {
/* 2031 */     if (Trace.isOn) {
/* 2032 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XAFORGET(Xid,int,int)", new Object[] { xid, 
/* 2033 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*      */     }
/*      */     
/* 2036 */     Hconn hconn = this.mqManCon.getHconn();
/* 2037 */     int result = this.jXA.xa_forget(hconn, xid, rmid, flags);
/* 2038 */     if (Trace.isOn) {
/* 2039 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XAFORGET(Xid,int,int)", 
/* 2040 */           Integer.valueOf(result));
/*      */     }
/* 2042 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int XAOPEN(String name, int rmid, int flags) throws Exception {
/* 2050 */     if (Trace.isOn) {
/* 2051 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XAOPEN(String,int,int)", new Object[] { name, 
/* 2052 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*      */     }
/*      */     
/* 2055 */     Hconn hconn = this.mqManCon.getHconn();
/* 2056 */     int result = this.jXA.xa_open(hconn, "qmname=" + name, rmid, flags);
/* 2057 */     if (Trace.isOn) {
/* 2058 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XAOPEN(String,int,int)", 
/* 2059 */           Integer.valueOf(result));
/*      */     }
/* 2061 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int XAPREPARE(Xid xid, int rmid, int flags) throws Exception {
/* 2070 */     if (Trace.isOn) {
/* 2071 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XAPREPARE(Xid,int,int)", new Object[] { xid, 
/* 2072 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*      */     }
/*      */     
/* 2075 */     Hconn hconn = this.mqManCon.getHconn();
/* 2076 */     int result = this.jXA.xa_prepare(hconn, xid, rmid, flags);
/* 2077 */     if (Trace.isOn) {
/* 2078 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XAPREPARE(Xid,int,int)", 
/* 2079 */           Integer.valueOf(result));
/*      */     }
/* 2081 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int XARECOVER(Xid[] xids, int rmid, int flags) throws Exception {
/* 2090 */     if (Trace.isOn) {
/* 2091 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XARECOVER(Xid [ ],int,int)", new Object[] { xids, 
/* 2092 */             Integer.valueOf(rmid), 
/* 2093 */             Integer.valueOf(flags) });
/*      */     }
/* 2095 */     Hconn hconn = this.mqManCon.getHconn();
/* 2096 */     int result = this.jXA.xa_recover(hconn, xids, rmid, flags);
/* 2097 */     if (Trace.isOn) {
/* 2098 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XARECOVER(Xid [ ],int,int)", 
/* 2099 */           Integer.valueOf(result));
/*      */     }
/* 2101 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int XAROLLBACK(Xid xid, int rmid, int flags) throws Exception {
/* 2110 */     if (Trace.isOn) {
/* 2111 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XAROLLBACK(Xid,int,int)", new Object[] { xid, 
/* 2112 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*      */     }
/*      */     
/* 2115 */     Hconn hconn = this.mqManCon.getHconn();
/* 2116 */     int result = this.jXA.xa_rollback(hconn, xid, rmid, flags);
/* 2117 */     if (Trace.isOn) {
/* 2118 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XAROLLBACK(Xid,int,int)", 
/* 2119 */           Integer.valueOf(result));
/*      */     }
/* 2121 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int XASTART(Xid xid, int rmid, int flags) throws Exception {
/* 2130 */     if (Trace.isOn) {
/* 2131 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XASTART(Xid,int,int)", new Object[] { xid, 
/* 2132 */             Integer.valueOf(rmid), Integer.valueOf(flags) });
/*      */     }
/* 2134 */     Hconn hconn = this.mqManCon.getHconn();
/* 2135 */     int result = this.jXA.xa_start(hconn, xid, rmid, flags);
/* 2136 */     if (Trace.isOn) {
/* 2137 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "XASTART(Xid,int,int)", 
/* 2138 */           Integer.valueOf(result));
/*      */     }
/* 2140 */     return result;
/*      */   }
/*      */   
/*      */   private void errorOccured(Hconn hconn, int cc, int rc) {
/* 2144 */     if (Trace.isOn) {
/* 2145 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "errorOccured(Hconn,int,int)", new Object[] { hconn, 
/* 2146 */             Integer.valueOf(cc), 
/* 2147 */             Integer.valueOf(rc) });
/*      */     }
/* 2149 */     if (hconn != null) {
/* 2150 */       JmqiEnvironment env = getJmqiEnv();
/* 2151 */       Phconn pHconn = env.newPhconn();
/* 2152 */       pHconn.setHconn(hconn);
/* 2153 */       errorOccured(pHconn, cc, rc);
/*      */     } 
/* 2155 */     if (Trace.isOn) {
/* 2156 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "errorOccured(Hconn,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void errorOccured(Phconn pHconn, int cc, int rc) {
/* 2163 */     if (Trace.isOn) {
/* 2164 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "errorOccured(Phconn,int,int)", new Object[] { pHconn, 
/* 2165 */             Integer.valueOf(cc), 
/* 2166 */             Integer.valueOf(rc) });
/*      */     }
/*      */     
/* 2169 */     if (rc == 2162) {
/* 2170 */       JmqiEnvironment env = getJmqiEnv();
/* 2171 */       Pint pCompCode = env.newPint();
/* 2172 */       Pint pReason = env.newPint();
/* 2173 */       this.jMQI.MQDISC(pHconn, pCompCode, pReason);
/*      */     } 
/*      */     
/* 2176 */     if (Trace.isOn)
/* 2177 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSESSION", "errorOccured(Phconn,int,int)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQSESSION.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */