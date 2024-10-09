/*      */ package com.ibm.mq.jms;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiUtils;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsCapabilityContext;
/*      */ import com.ibm.msg.client.jms.JmsConnection;
/*      */ import com.ibm.msg.client.jms.JmsFactoryFactory;
/*      */ import com.ibm.msg.client.jms.admin.JmsJndiConnectionFactoryImpl;
/*      */ import com.ibm.msg.client.jms.internal.JmsErrorUtils;
/*      */ import com.ibm.msg.client.services.Trace;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.ObjectStreamField;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.nio.charset.Charset;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import javax.jms.Connection;
/*      */ import javax.jms.JMSException;
/*      */ import javax.naming.NamingException;
/*      */ import javax.naming.OperationNotSupportedException;
/*      */ import javax.naming.RefAddr;
/*      */ import javax.naming.Reference;
/*      */ import javax.naming.StringRefAddr;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQConnectionFactory
/*      */   extends JmsJndiConnectionFactoryImpl
/*      */ {
/*      */   private static final long serialVersionUID = 1357803352856448349L;
/*      */   static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQConnectionFactory.java";
/*      */   
/*      */   static {
/*   95 */     if (Trace.isOn) {
/*   96 */       Trace.data("com.ibm.mq.jms.MQConnectionFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQConnectionFactory.java");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  107 */     if (Trace.isOn) {
/*  108 */       Trace.entry("com.ibm.mq.jms.MQConnectionFactory", "static()");
/*      */     }
/*      */ 
/*      */     
/*  112 */     checkTracing();
/*      */ 
/*      */     
/*  115 */     NLSServices.addCatalogue("com.ibm.mq.jms.resources.JMSMQ_MessageResourceBundle", "JMSMQ", MQConnectionFactory.class);
/*  116 */     if (Trace.isOn) {
/*  117 */       Trace.exit("com.ibm.mq.jms.MQConnectionFactory", "static()");
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
/*  131 */   private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("applicationName", String.class), new ObjectStreamField("asyncExceptions", int.class), new ObjectStreamField("brokerCCSubQueue", String.class), new ObjectStreamField("brokerControlQueue", String.class), new ObjectStreamField("brokerPubQueue", String.class), new ObjectStreamField("brokerQueueManager", String.class), new ObjectStreamField("brokerSubQueue", String.class), new ObjectStreamField("brokerVersion", int.class), new ObjectStreamField("brokerVersionUnspecified", boolean.class), new ObjectStreamField("bverSet", boolean.class), new ObjectStreamField("ccdtUrl", URL.class), new ObjectStreamField("CCSID", int.class), new ObjectStreamField("channel", String.class), new ObjectStreamField("cleanupInterval", long.class), new ObjectStreamField("cleanupLevel", int.class), new ObjectStreamField("clientId", String.class), new ObjectStreamField("clientReconnectOptions", int.class), new ObjectStreamField("clientReconnectTimeout", int.class), new ObjectStreamField("cloneSupport", int.class), new ObjectStreamField("connectionNameList", String.class), new ObjectStreamField("connOptions", int.class), new ObjectStreamField("connTag", byte[].class), new ObjectStreamField("description", String.class), new ObjectStreamField("directAuth", int.class), new ObjectStreamField("failIfQuiesce", int.class), new ObjectStreamField("hdrCompList", Collection.class), new ObjectStreamField("hostName", String.class), new ObjectStreamField("localAddress", String.class), new ObjectStreamField("mapNameStyle", boolean.class), new ObjectStreamField("maxBufferSize", int.class), new ObjectStreamField("msgBatchSize", int.class), new ObjectStreamField("msgCompList", Collection.class), new ObjectStreamField("messageRetention", int.class), new ObjectStreamField("messageSelection", int.class), new ObjectStreamField("mselSet", boolean.class), new ObjectStreamField("multicast", int.class), new ObjectStreamField("optimisticPublication", boolean.class), new ObjectStreamField("outcomeNotification", boolean.class), new ObjectStreamField("pollingInterval", int.class), new ObjectStreamField("port", int.class), new ObjectStreamField("portSet", boolean.class), new ObjectStreamField("processDuration", int.class), new ObjectStreamField("providerVersion", String.class), new ObjectStreamField("proxyHostName", String.class), new ObjectStreamField("proxyPort", int.class), new ObjectStreamField("pubAckInterval", int.class), new ObjectStreamField("queueManager", String.class), new ObjectStreamField("receiveExit", String.class), new ObjectStreamField("receiveExitInit", String.class), new ObjectStreamField("receiveIsolation", int.class), new ObjectStreamField("rescanInterval", int.class), new ObjectStreamField("securityExit", String.class), new ObjectStreamField("securityExitInit", String.class), new ObjectStreamField("sendExit", String.class), new ObjectStreamField("sendExitInit", String.class), new ObjectStreamField("sendCheckCount", int.class), new ObjectStreamField("shareConvAllowed", int.class), new ObjectStreamField("sparseSubscriptions", boolean.class), new ObjectStreamField("sslCertStores_coll", Collection.class), new ObjectStreamField("sslCertStores_string", String.class), new ObjectStreamField("sslCipherSuite", String.class), new ObjectStreamField("sslFipsRequired", boolean.class), new ObjectStreamField("sslPeerName", String.class), new ObjectStreamField("sslResetCount", int.class), new ObjectStreamField("sslSocketFactory", Object.class), new ObjectStreamField("statusRefreshInterval", int.class), new ObjectStreamField("subscriptionStore", int.class), new ObjectStreamField("syncpointAllGets", boolean.class), new ObjectStreamField("targetClientMatching", boolean.class), new ObjectStreamField("temporaryModel", String.class), new ObjectStreamField("tempQPrefix", String.class), new ObjectStreamField("tempTopicPrefix", String.class), new ObjectStreamField("transportType", int.class), new ObjectStreamField("useConnectionPooling", boolean.class), new ObjectStreamField("version", int.class), new ObjectStreamField("wildcardFormat", int.class) };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean mselSet;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean bverSet;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean portSet;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private transient boolean versionChangeAllowed = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  230 */   protected transient String connectionType = "com.ibm.msg.client.wmq";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String nullString = "IamANullString";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  240 */   protected JmsCapabilityContext capabilities = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQConnectionFactory() {
/*  246 */     if (Trace.isOn) {
/*  247 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "<init>()");
/*      */     }
/*      */     
/*  250 */     initialiseMQConnectionFactory();
/*      */ 
/*      */     
/*      */     try {
/*  254 */       setIntProperty("XMSC_WMQ_CONNECTION_MODE", 0);
/*  255 */       setIntProperty("XMSC_WMQ_CONNECT_OPTIONS", 0);
/*  256 */       if (this instanceof MQXAConnectionFactory || this instanceof MQXATopicConnectionFactory || this instanceof MQXAQueueConnectionFactory) {
/*  257 */         setProviderFactory(true);
/*      */       } else {
/*      */         
/*  260 */         setProviderFactory(false);
/*      */       }
/*      */     
/*  263 */     } catch (JMSException e) {
/*  264 */       if (Trace.isOn) {
/*  265 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "<init>()", (Throwable)e);
/*      */       }
/*  267 */       Trace.ffst(this, "readObject()", "XF001001", null, null);
/*      */     } 
/*      */     
/*  270 */     setDefaultProperties();
/*      */     
/*  272 */     if (Trace.isOn) {
/*  273 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "<init>()");
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
/*      */   private void setDefaultProperties() {
/*  285 */     if (Trace.isOn) {
/*  286 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "setDefaultProperties()");
/*      */     }
/*  288 */     this.portSet = false;
/*  289 */     this.bverSet = false;
/*  290 */     this.mselSet = false;
/*      */ 
/*      */     
/*      */     try {
/*  294 */       this.settingDefaults = true;
/*      */       
/*  296 */       setAsyncExceptions(1);
/*      */       
/*  298 */       setBrokerCCSubQueue("SYSTEM.JMS.ND.CC.SUBSCRIBER.QUEUE");
/*  299 */       setBrokerControlQueue("SYSTEM.BROKER.CONTROL.QUEUE");
/*  300 */       setBrokerPubQueue("SYSTEM.BROKER.DEFAULT.STREAM");
/*  301 */       setBrokerQueueManager("");
/*  302 */       setBrokerSubQueue("SYSTEM.JMS.ND.SUBSCRIBER.QUEUE");
/*  303 */       setBrokerVersion(-1);
/*  304 */       setCleanupInterval(3600000L);
/*      */       
/*  306 */       this.bverSet = false;
/*  307 */       setCCDTURL((URL)null);
/*  308 */       setCCSID(819);
/*  309 */       setChannel("SYSTEM.DEF.SVRCONN");
/*      */       
/*  311 */       setCleanupLevel(1);
/*  312 */       setClientID((String)null);
/*  313 */       setConnectionNameListInternal("localhost(1414)");
/*  314 */       setClientReconnectOptions(0);
/*  315 */       setClientReconnectTimeout(1800);
/*  316 */       setCloneSupport(0);
/*  317 */       setMQConnectionOptions(0);
/*  318 */       byte[] connTag = new byte[128];
/*  319 */       for (int i = 0; i < connTag.length; i++) {
/*  320 */         connTag[i] = 0;
/*      */       }
/*  322 */       setConnTag(connTag);
/*  323 */       setDescription((String)null);
/*  324 */       setDirectAuth(0);
/*  325 */       setFailIfQuiesce(1);
/*  326 */       setHdrCompList((Collection)null);
/*      */ 
/*      */       
/*  329 */       PropertyStore.register("com.ibm.mq.localAddress", "");
/*  330 */       setLocalAddress(PropertyStore.getStringProperty("com.ibm.mq.localAddress"));
/*      */       
/*  332 */       setMapNameStyle(true);
/*  333 */       setMaxBufferSize(1000);
/*  334 */       setMessageRetention(1);
/*  335 */       setMessageSelection(0);
/*  336 */       this.mselSet = false;
/*  337 */       setMsgBatchSize(10);
/*  338 */       setMsgCompList((Collection)null);
/*  339 */       setMulticast(0);
/*  340 */       setOptimisticPublication(false);
/*  341 */       setOutcomeNotification(true);
/*  342 */       setPollingInterval(5000);
/*      */       
/*  344 */       this.portSet = false;
/*  345 */       setProcessDuration(0);
/*  346 */       setProviderVersion("unspecified");
/*  347 */       setProxyHostName((String)null);
/*  348 */       setProxyPort(443);
/*  349 */       setPubAckInterval(25);
/*  350 */       setQueueManager("");
/*  351 */       setReceiveExit((String)null);
/*  352 */       setReceiveExitInit((String)null);
/*  353 */       setReceiveIsolation(0);
/*  354 */       setRescanInterval(5000);
/*  355 */       setSecurityExit((String)null);
/*  356 */       setSecurityExitInit((String)null);
/*  357 */       setSendExit((String)null);
/*  358 */       setSendExitInit((String)null);
/*  359 */       setSendCheckCount(0);
/*  360 */       setShareConvAllowed(1);
/*  361 */       setSparseSubscriptions(false);
/*  362 */       setSSLCertStores((Collection)null);
/*  363 */       setSSLCipherSuite((String)null);
/*  364 */       setSSLFipsRequired(false);
/*  365 */       setSSLPeerName((String)null);
/*  366 */       setSSLResetCount(0);
/*  367 */       setSSLSocketFactory((Object)null);
/*  368 */       setStatusRefreshInterval(60000);
/*  369 */       setSubscriptionStore(1);
/*  370 */       setSyncpointAllGets(false);
/*  371 */       setTargetClientMatching(true);
/*  372 */       setTemporaryModel("SYSTEM.DEFAULT.MODEL.QUEUE");
/*  373 */       setTempQPrefix("");
/*  374 */       setTempTopicPrefix("");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  379 */       if (CSSystem.currentPlatform().equals(CSSystem.Platform.OS_NSS)) {
/*  380 */         setTransportType(1);
/*      */       } else {
/*      */         
/*  383 */         setTransportType(0);
/*      */       } 
/*  385 */       setUseConnectionPooling(true);
/*  386 */       this.versionChangeAllowed = true;
/*  387 */       setVersion(7);
/*  388 */       this.versionChangeAllowed = false;
/*  389 */       setWildcardFormat(0);
/*  390 */       this.settingDefaults = false;
/*      */     }
/*  392 */     catch (JMSException e) {
/*  393 */       if (Trace.isOn) {
/*  394 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setDefaultProperties()", (Throwable)e);
/*      */       }
/*      */       
/*  397 */       e.printStackTrace();
/*      */     } 
/*  399 */     if (Trace.isOn) {
/*  400 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "setDefaultProperties()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String findCorrectField(String field) {
/*  411 */     String[][] fieldNames = { { "cCDTURL", "clientID", "hdrCompListAsString", "mQConnectionOptions", "msgCompListAsString", "sSLCertStores", "sSLCertStoresAsString", "sSLCipherSuite", "sSLFipsRequired", "sSLPeerName", "sSLSocketFactory", "sSLResetCount", "cCSID", "reference" }, { "ccdtUrl", "clientId", null, "connOptions", null, "sslCertStores_coll", "sslCertStores_string", "sslCipherSuite", "sslFipsRequired", "sslPeerName", "sslSocketFactory", "sslResetCount", "CCSID", null } };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  418 */     for (int i = 0; i < (fieldNames[0]).length; i++) {
/*  419 */       if (field.equals(fieldNames[0][i])) {
/*  420 */         return fieldNames[1][i];
/*      */       }
/*      */     } 
/*      */     
/*  424 */     return field;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String findCorrectMethod(String method) {
/*  433 */     String[][] methodNames = { { "setSslSocketFactory", "setSslResetCount", "setSslPeerName", "setSslFipsRequired", "setSslCipherSuite", "setSslCertStores_string", "setSslCertStores_coll", "setConnOptions", "setCcdtUrl" }, { "setSSLSocketFactory", "setSSLResetCount", "setSSLPeerName", "setSSLFipsRequired", "setSSLCipherSuite", "setSSLCertStores", "setSSLCertStores", "setMQConnectionOptions", "setCCDTURL" } };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  439 */     for (int i = 0; i < (methodNames[0]).length; i++) {
/*  440 */       if (method.equals(methodNames[0][i])) {
/*  441 */         return methodNames[1][i];
/*      */       }
/*      */     } 
/*  444 */     return method;
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
/*      */   public String getBrokerCCSubQueue() throws JMSException {
/*  457 */     if (this instanceof MQQueueConnectionFactory) {
/*  458 */       HashMap<String, Object> inserts = new HashMap<>();
/*  459 */       inserts.put("XMSC_INSERT_METHOD", "getBrokerCCSubQueue()");
/*  460 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/*  461 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/*  462 */       if (Trace.isOn) {
/*  463 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getBrokerCCSubQueue()", (Throwable)je);
/*      */       }
/*  465 */       throw je;
/*      */     } 
/*      */     
/*  468 */     String traceRet1 = getStringProperty("XMSC_WMQ_BROKER_CC_SUBQ");
/*      */     
/*  470 */     if (Trace.isOn) {
/*  471 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getBrokerCCSubQueue()", "getter", traceRet1);
/*      */     }
/*      */     
/*  474 */     return traceRet1;
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
/*      */   public String getBrokerControlQueue() throws JMSException {
/*  487 */     if (this instanceof MQQueueConnectionFactory) {
/*  488 */       HashMap<String, Object> inserts = new HashMap<>();
/*  489 */       inserts.put("XMSC_INSERT_METHOD", "getBrokerControlQueue()");
/*  490 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/*  491 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/*  492 */       if (Trace.isOn) {
/*  493 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getBrokerControlQueue()", (Throwable)je);
/*      */       }
/*  495 */       throw je;
/*      */     } 
/*      */     
/*  498 */     String traceRet1 = getStringProperty("XMSC_WMQ_BROKER_CONTROLQ");
/*      */     
/*  500 */     if (Trace.isOn) {
/*  501 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getBrokerControlQueue()", "getter", traceRet1);
/*      */     }
/*      */     
/*  504 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getBrokerPubQueue() throws JMSException {
/*  515 */     if (this instanceof MQQueueConnectionFactory) {
/*  516 */       HashMap<String, Object> inserts = new HashMap<>();
/*  517 */       inserts.put("XMSC_INSERT_METHOD", "getBrokerPubQueue()");
/*  518 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/*  519 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/*  520 */       if (Trace.isOn) {
/*  521 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getBrokerPubQueue()", (Throwable)je);
/*      */       }
/*  523 */       throw je;
/*      */     } 
/*      */     
/*  526 */     String traceRet1 = getStringProperty("XMSC_WMQ_BROKER_PUBQ");
/*      */     
/*  528 */     if (Trace.isOn) {
/*  529 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getBrokerPubQueue()", "getter", traceRet1);
/*      */     }
/*      */     
/*  532 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getBrokerQueueManager() throws JMSException {
/*  543 */     if (this instanceof MQQueueConnectionFactory) {
/*  544 */       HashMap<String, Object> inserts = new HashMap<>();
/*  545 */       inserts.put("XMSC_INSERT_METHOD", "getBrokerQueueManager()");
/*  546 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/*  547 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/*  548 */       if (Trace.isOn) {
/*  549 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getBrokerQueueManager()", (Throwable)je);
/*      */       }
/*  551 */       throw je;
/*      */     } 
/*      */     
/*  554 */     String traceRet1 = getStringProperty("XMSC_WMQ_BROKER_QMGR");
/*      */     
/*  556 */     if (Trace.isOn) {
/*  557 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getBrokerQueueManager()", "getter", traceRet1);
/*      */     }
/*      */     
/*  560 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getBrokerSubQueue() throws JMSException {
/*  571 */     if (this instanceof MQQueueConnectionFactory) {
/*  572 */       HashMap<String, Object> inserts = new HashMap<>();
/*  573 */       inserts.put("XMSC_INSERT_METHOD", "getBrokerSubQueue()");
/*  574 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/*  575 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/*  576 */       if (Trace.isOn) {
/*  577 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getBrokerSubQueue()", (Throwable)je);
/*      */       }
/*  579 */       throw je;
/*      */     } 
/*      */     
/*  582 */     String traceRet1 = getStringProperty("XMSC_WMQ_BROKER_SUBQ");
/*      */     
/*  584 */     if (Trace.isOn) {
/*  585 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getBrokerSubQueue()", "getter", traceRet1);
/*      */     }
/*      */     
/*  588 */     return traceRet1;
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
/*      */   public int getBrokerVersion() throws JMSException {
/*  601 */     if (this instanceof MQQueueConnectionFactory) {
/*  602 */       HashMap<String, Object> inserts = new HashMap<>();
/*  603 */       inserts.put("XMSC_INSERT_METHOD", "getBrokerVersion()");
/*  604 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/*  605 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/*  606 */       if (Trace.isOn) {
/*  607 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getBrokerVersion()", (Throwable)je);
/*      */       }
/*  609 */       throw je;
/*      */     } 
/*      */     
/*  612 */     int traceRet1 = getIntProperty("brokerVersion");
/*      */     
/*  614 */     if (Trace.isOn) {
/*  615 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getBrokerVersion()", "getter", 
/*  616 */           Integer.valueOf(traceRet1));
/*      */     }
/*  618 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public URL getCCDTURL() {
/*  628 */     if (Trace.isOn) {
/*  629 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getCCDTURL()");
/*      */     }
/*      */     
/*      */     try {
/*      */       URL traceRet1;
/*  634 */       String value = getStringProperty("XMSC_WMQ_CCDTURL");
/*  635 */       if (value != null) {
/*  636 */         traceRet1 = new URL(value);
/*      */       } else {
/*      */         
/*  639 */         traceRet1 = null;
/*      */       } 
/*  641 */       if (Trace.isOn) {
/*  642 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getCCDTURL()", traceRet1, 1);
/*      */       }
/*  644 */       return traceRet1;
/*      */     }
/*  646 */     catch (JMSException je) {
/*  647 */       if (Trace.isOn) {
/*  648 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getCCDTURL()", (Throwable)je, 1);
/*      */       }
/*  650 */       if (Trace.isOn) {
/*  651 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getCCDTURL()", null, 2);
/*      */       }
/*  653 */       return null;
/*      */     }
/*  655 */     catch (MalformedURLException e) {
/*  656 */       if (Trace.isOn) {
/*  657 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getCCDTURL()", e, 2);
/*      */       }
/*      */       
/*  660 */       if (Trace.isOn) {
/*  661 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getCCDTURL()", null, 3);
/*      */       }
/*  663 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCCSID() {
/*  673 */     if (Trace.isOn) {
/*  674 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getCCSID()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  679 */       int traceRet1 = getIntProperty("XMSC_WMQ_QMGR_CCSID");
/*  680 */       if (Trace.isOn) {
/*  681 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getCCSID()", 
/*  682 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/*  684 */       return traceRet1;
/*      */     }
/*  686 */     catch (JMSException je) {
/*  687 */       if (Trace.isOn) {
/*  688 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getCCSID()", (Throwable)je);
/*      */       }
/*  690 */       if (Trace.isOn) {
/*  691 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getCCSID()", Integer.valueOf(0), 2);
/*      */       }
/*  693 */       return 0;
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
/*      */   public String getChannel() {
/*  705 */     if (Trace.isOn) {
/*  706 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getChannel()");
/*      */     }
/*      */     try {
/*  709 */       String traceRet1 = getStringProperty("XMSC_WMQ_CHANNEL");
/*  710 */       if (Trace.isOn) {
/*  711 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getChannel()", traceRet1, 1);
/*      */       }
/*  713 */       return traceRet1;
/*      */     }
/*  715 */     catch (JMSException je) {
/*  716 */       if (Trace.isOn) {
/*  717 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getChannel()", (Throwable)je);
/*      */       }
/*  719 */       if (Trace.isOn) {
/*  720 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getChannel()", null, 2);
/*      */       }
/*  722 */       return null;
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
/*      */   public long getCleanupInterval() throws JMSException {
/*  734 */     if (this instanceof MQQueueConnectionFactory) {
/*  735 */       HashMap<String, Object> inserts = new HashMap<>();
/*  736 */       inserts.put("XMSC_INSERT_METHOD", "getCleanupInterval()");
/*  737 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/*  738 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/*  739 */       if (Trace.isOn) {
/*  740 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getCleanupInterval()", (Throwable)je);
/*      */       }
/*  742 */       throw je;
/*      */     } 
/*      */     
/*  745 */     long traceRet1 = getLongProperty("XMSC_WMQ_CLEANUP_INTERVAL");
/*      */     
/*  747 */     if (Trace.isOn) {
/*  748 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getCleanupInterval()", "getter", 
/*  749 */           Long.valueOf(traceRet1));
/*      */     }
/*  751 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCleanupLevel() throws JMSException {
/*  762 */     if (this instanceof MQQueueConnectionFactory) {
/*  763 */       HashMap<String, Object> inserts = new HashMap<>();
/*  764 */       inserts.put("XMSC_INSERT_METHOD", "getCleanupLevel()");
/*  765 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/*  766 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/*  767 */       if (Trace.isOn) {
/*  768 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getCleanupLevel()", (Throwable)je);
/*      */       }
/*  770 */       throw je;
/*      */     } 
/*      */     
/*  773 */     int traceRet1 = getIntProperty("XMSC_WMQ_CLEANUP_LEVEL");
/*      */     
/*  775 */     if (Trace.isOn) {
/*  776 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getCleanupLevel()", "getter", 
/*  777 */           Integer.valueOf(traceRet1));
/*      */     }
/*  779 */     return traceRet1;
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
/*      */   public String getClientID() {
/*  791 */     if (Trace.isOn) {
/*  792 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getClientID()");
/*      */     }
/*      */     
/*      */     try {
/*  796 */       String traceRet1 = getStringProperty("XMSC_CLIENT_ID");
/*  797 */       if (Trace.isOn) {
/*  798 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getClientID()", traceRet1, 1);
/*      */       }
/*  800 */       return traceRet1;
/*      */     }
/*  802 */     catch (JMSException je) {
/*  803 */       if (Trace.isOn) {
/*  804 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getClientID()", (Throwable)je);
/*      */       }
/*  806 */       if (Trace.isOn) {
/*  807 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getClientID()", null, 2);
/*      */       }
/*  809 */       return null;
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
/*      */   @Deprecated
/*      */   public String getClientId() {
/*  823 */     if (Trace.isOn) {
/*  824 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getClientId()");
/*      */     }
/*      */     
/*      */     try {
/*  828 */       String traceRet1 = getStringProperty("XMSC_CLIENT_ID");
/*  829 */       if (Trace.isOn) {
/*  830 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getClientId()", traceRet1, 1);
/*      */       }
/*  832 */       return traceRet1;
/*      */     }
/*  834 */     catch (JMSException je) {
/*  835 */       if (Trace.isOn) {
/*  836 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getClientId()", (Throwable)je);
/*      */       }
/*  838 */       if (Trace.isOn) {
/*  839 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getClientId()", null, 2);
/*      */       }
/*  841 */       return null;
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
/*      */   public int getCloneSupport() throws JMSException {
/*  857 */     if (this instanceof MQQueueConnectionFactory) {
/*  858 */       HashMap<String, Object> inserts = new HashMap<>();
/*  859 */       inserts.put("XMSC_INSERT_METHOD", "getCloneSupport()");
/*  860 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/*  861 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/*  862 */       if (Trace.isOn) {
/*  863 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getCloneSupport()", (Throwable)je);
/*      */       }
/*  865 */       throw je;
/*      */     } 
/*      */     
/*  868 */     int traceRet1 = getIntProperty("XMSC_WMQ_CLONE_SUPPORT");
/*      */     
/*  870 */     if (Trace.isOn) {
/*  871 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getCloneSupport()", "getter", 
/*  872 */           Integer.valueOf(traceRet1));
/*      */     }
/*  874 */     return traceRet1;
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
/*      */   public byte[] getConnTag() {
/*  887 */     if (Trace.isOn) {
/*  888 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getConnTag()");
/*      */     }
/*      */     
/*      */     try {
/*  892 */       byte[] traceRet1 = getBytesProperty("XMSC_WMQ_CONNECTION_TAG");
/*  893 */       if (Trace.isOn) {
/*  894 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getConnTag()", traceRet1, 1);
/*      */       }
/*  896 */       return traceRet1;
/*      */     }
/*  898 */     catch (JMSException je) {
/*  899 */       if (Trace.isOn) {
/*  900 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getConnTag()", (Throwable)je);
/*      */       }
/*  902 */       if (Trace.isOn) {
/*  903 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getConnTag()", null, 2);
/*      */       }
/*  905 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDescription() {
/*  916 */     if (Trace.isOn) {
/*  917 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getDescription()");
/*      */     }
/*      */     
/*      */     try {
/*  921 */       String traceRet1 = getStringProperty("XMSC_WMQ_CF_DESCRIPTION");
/*  922 */       if (Trace.isOn) {
/*  923 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getDescription()", traceRet1, 1);
/*      */       }
/*  925 */       return traceRet1;
/*      */     }
/*  927 */     catch (JMSException je) {
/*  928 */       if (Trace.isOn) {
/*  929 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getDescription()", (Throwable)je);
/*      */       }
/*  931 */       if (Trace.isOn) {
/*  932 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getDescription()", null, 2);
/*      */       }
/*  934 */       return null;
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
/*      */   @Deprecated
/*      */   public int getDirectAuth() throws JMSException {
/*  948 */     if (this instanceof MQQueueConnectionFactory) {
/*  949 */       HashMap<String, Object> inserts = new HashMap<>();
/*  950 */       inserts.put("XMSC_INSERT_METHOD", "getDirectAuth()");
/*  951 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/*  952 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/*  953 */       if (Trace.isOn) {
/*  954 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getDirectAuth()", (Throwable)je);
/*      */       }
/*  956 */       throw je;
/*      */     } 
/*      */     
/*  959 */     int traceRet1 = getIntProperty("XMSC_RTT_DIRECT_AUTH");
/*      */     
/*  961 */     if (Trace.isOn) {
/*  962 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getDirectAuth()", "getter", 
/*  963 */           Integer.valueOf(traceRet1));
/*      */     }
/*  965 */     return traceRet1;
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
/*      */   public int getFailIfQuiesce() {
/*  978 */     if (Trace.isOn) {
/*  979 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getFailIfQuiesce()");
/*      */     }
/*      */     
/*      */     try {
/*  983 */       int traceRet1 = getIntProperty("failIfQuiesce");
/*  984 */       if (Trace.isOn) {
/*  985 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getFailIfQuiesce()", 
/*  986 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/*  988 */       return traceRet1;
/*      */     }
/*  990 */     catch (JMSException je) {
/*  991 */       if (Trace.isOn) {
/*  992 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getFailIfQuiesce()", (Throwable)je);
/*      */       }
/*  994 */       if (Trace.isOn) {
/*  995 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getFailIfQuiesce()", 
/*  996 */             Integer.valueOf(0), 2);
/*      */       }
/*  998 */       return 0;
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
/*      */   public Collection<Object> getHdrCompList() {
/* 1010 */     if (Trace.isOn) {
/* 1011 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getHdrCompList()");
/*      */     }
/*      */     
/*      */     try {
/* 1015 */       Object<Object> value = (Object<Object>)getObjectProperty("XMSC_WMQ_HEADER_COMP");
/* 1016 */       if (value instanceof String) {
/*      */ 
/*      */ 
/*      */         
/* 1020 */         StringTokenizer st = new StringTokenizer((String)value);
/* 1021 */         Vector<Object> c = new Vector();
/* 1022 */         while (st.hasMoreTokens()) {
/* 1023 */           String tk = st.nextToken();
/* 1024 */           if (tk.equals("NONE")) {
/* 1025 */             c.add(Integer.valueOf(0)); continue;
/*      */           } 
/* 1027 */           if (tk.equals("SYSTEM")) {
/* 1028 */             c.add(Integer.valueOf(8));
/*      */             continue;
/*      */           } 
/* 1031 */           c.add(tk);
/*      */         } 
/*      */         
/* 1034 */         value = (Object<Object>)c;
/*      */       } 
/*      */       
/* 1037 */       Collection<Object> traceRet1 = (Collection<Object>)value;
/* 1038 */       if (Trace.isOn) {
/* 1039 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getHdrCompList()", traceRet1, 1);
/*      */       }
/* 1041 */       return traceRet1;
/*      */     }
/* 1043 */     catch (JMSException je) {
/* 1044 */       if (Trace.isOn) {
/* 1045 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getHdrCompList()", (Throwable)je);
/*      */       }
/* 1047 */       if (Trace.isOn) {
/* 1048 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getHdrCompList()", null, 2);
/*      */       }
/* 1050 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getHdrCompListAsString() {
/* 1060 */     StringBuffer compString = null;
/* 1061 */     Collection<?> hdrCompList = getHdrCompList();
/* 1062 */     if (hdrCompList != null) {
/* 1063 */       compString = new StringBuffer("");
/* 1064 */       Iterator<?> it = hdrCompList.iterator();
/* 1065 */       if (it != null) {
/* 1066 */         Integer compressor = Integer.valueOf(0);
/* 1067 */         while (it.hasNext()) {
/* 1068 */           compressor = (Integer)it.next();
/* 1069 */           switch (compressor.intValue()) {
/*      */             case 0:
/* 1071 */               compString.append("NONE ");
/*      */             
/*      */             case 8:
/* 1074 */               compString.append("SYSTEM ");
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         } 
/*      */       } 
/*      */     } 
/* 1083 */     String traceRet1 = (compString == null) ? null : compString.toString();
/* 1084 */     if (Trace.isOn) {
/* 1085 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getHdrCompListAsString()", "getter", traceRet1);
/*      */     }
/*      */     
/* 1088 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getHostName() {
/* 1098 */     if (Trace.isOn) {
/* 1099 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getHostName()");
/*      */     }
/*      */     
/*      */     try {
/* 1103 */       String traceRet1 = getStringProperty("XMSC_WMQ_HOST_NAME");
/* 1104 */       if (traceRet1.equals("IamANullString"))
/*      */       {
/*      */         
/* 1107 */         traceRet1 = null;
/*      */       }
/* 1109 */       if (Trace.isOn) {
/* 1110 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getHostName()", traceRet1, 1);
/*      */       }
/* 1112 */       return traceRet1;
/*      */     }
/* 1114 */     catch (JMSException je) {
/* 1115 */       if (Trace.isOn) {
/* 1116 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getHostName()", (Throwable)je);
/*      */       }
/* 1118 */       if (Trace.isOn) {
/* 1119 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getHostName()", null, 2);
/*      */       }
/* 1121 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLocalAddress() {
/* 1132 */     if (Trace.isOn) {
/* 1133 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getLocalAddress()");
/*      */     }
/*      */     
/*      */     try {
/* 1137 */       String traceRet1 = getStringProperty("XMSC_WMQ_LOCAL_ADDRESS");
/* 1138 */       if (Trace.isOn) {
/* 1139 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getLocalAddress()", traceRet1, 1);
/*      */       }
/* 1141 */       return traceRet1;
/*      */     }
/* 1143 */     catch (JMSException je) {
/* 1144 */       if (Trace.isOn) {
/* 1145 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getLocalAddress()", (Throwable)je);
/*      */       }
/* 1147 */       if (Trace.isOn) {
/* 1148 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getLocalAddress()", null, 2);
/*      */       }
/* 1150 */       return null;
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
/*      */   public boolean getMapNameStyle() {
/* 1166 */     if (Trace.isOn) {
/* 1167 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getMapNameStyle()");
/*      */     }
/*      */     
/*      */     try {
/* 1171 */       boolean traceRet1 = getBooleanProperty("XMSC_WMQ_MAP_NAME_STYLE");
/* 1172 */       if (Trace.isOn) {
/* 1173 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getMapNameStyle()", 
/* 1174 */             Boolean.valueOf(traceRet1), 1);
/*      */       }
/* 1176 */       return traceRet1;
/*      */     }
/* 1178 */     catch (JMSException je) {
/* 1179 */       if (Trace.isOn) {
/* 1180 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getMapNameStyle()", (Throwable)je);
/*      */       }
/* 1182 */       if (Trace.isOn) {
/* 1183 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getMapNameStyle()", 
/* 1184 */             Boolean.valueOf(false), 2);
/*      */       }
/* 1186 */       return false;
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
/*      */   public int getMaxBufferSize() throws JMSException {
/* 1200 */     if (this instanceof MQQueueConnectionFactory) {
/* 1201 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1202 */       inserts.put("XMSC_INSERT_METHOD", "getMaxBufferSize()");
/* 1203 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 1204 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 1205 */       if (Trace.isOn) {
/* 1206 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getMaxBufferSize()", (Throwable)je);
/*      */       }
/* 1208 */       throw je;
/*      */     } 
/*      */     
/* 1211 */     int traceRet1 = getIntProperty("XMSC_WMQ_MAX_BUFFER_SIZE");
/*      */     
/* 1213 */     if (Trace.isOn) {
/* 1214 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getMaxBufferSize()", "getter", 
/* 1215 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1217 */     return traceRet1;
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
/*      */   public int getMessageRetention() throws JMSException {
/* 1232 */     if (this instanceof MQTopicConnectionFactory) {
/* 1233 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1234 */       inserts.put("XMSC_INSERT_METHOD", "getMessageRetention()");
/* 1235 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 1236 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 1237 */       if (Trace.isOn) {
/* 1238 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getMessageRetention()", (Throwable)je);
/*      */       }
/* 1240 */       throw je;
/*      */     } 
/*      */     
/* 1243 */     int traceRet1 = getIntProperty("XMSC_WMQ_MESSAGE_RETENTION");
/*      */     
/* 1245 */     if (Trace.isOn) {
/* 1246 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getMessageRetention()", "getter", 
/* 1247 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1249 */     return traceRet1;
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
/*      */   public int getMessageSelection() throws JMSException {
/* 1264 */     if (this instanceof MQQueueConnectionFactory) {
/* 1265 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1266 */       inserts.put("XMSC_INSERT_METHOD", "getMessageSelection()");
/* 1267 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 1268 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 1269 */       if (Trace.isOn) {
/* 1270 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getMessageSelection()", (Throwable)je);
/*      */       }
/* 1272 */       throw je;
/*      */     } 
/*      */     
/* 1275 */     int traceRet1 = getIntProperty("XMSC_WMQ_MESSAGE_SELECTION");
/*      */     
/* 1277 */     if (Trace.isOn) {
/* 1278 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getMessageSelection()", "getter", 
/* 1279 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1281 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMQConnectionOptions() {
/* 1290 */     if (Trace.isOn) {
/* 1291 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getMQConnectionOptions()");
/*      */     }
/*      */     
/*      */     try {
/* 1295 */       int traceRet1 = getIntProperty("XMSC_WMQ_CONNECT_OPTIONS");
/* 1296 */       if (Trace.isOn) {
/* 1297 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getMQConnectionOptions()", 
/* 1298 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/* 1300 */       return traceRet1;
/*      */     }
/* 1302 */     catch (JMSException je) {
/* 1303 */       if (Trace.isOn) {
/* 1304 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getMQConnectionOptions()", (Throwable)je);
/*      */       }
/*      */       
/* 1307 */       if (Trace.isOn) {
/* 1308 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getMQConnectionOptions()", 
/* 1309 */             Integer.valueOf(0), 2);
/*      */       }
/* 1311 */       return 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMsgBatchSize() {
/* 1321 */     if (Trace.isOn) {
/* 1322 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getMsgBatchSize()");
/*      */     }
/*      */     
/*      */     try {
/* 1326 */       int traceRet1 = getIntProperty("XMSC_WMQ_MSG_BATCH_SIZE");
/* 1327 */       if (Trace.isOn) {
/* 1328 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getMsgBatchSize()", 
/* 1329 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/* 1331 */       return traceRet1;
/*      */     }
/* 1333 */     catch (JMSException je) {
/* 1334 */       if (Trace.isOn) {
/* 1335 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getMsgBatchSize()", (Throwable)je);
/*      */       }
/* 1337 */       if (Trace.isOn) {
/* 1338 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getMsgBatchSize()", 
/* 1339 */             Integer.valueOf(0), 2);
/*      */       }
/* 1341 */       return 0;
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
/*      */   public Collection<? extends Object> getMsgCompList() {
/* 1353 */     if (Trace.isOn) {
/* 1354 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getMsgCompList()");
/*      */     }
/*      */     
/*      */     try {
/* 1358 */       Object<Integer> value = (Object<Integer>)getObjectProperty("XMSC_WMQ_MSG_COMP");
/* 1359 */       if (value instanceof String) {
/*      */ 
/*      */ 
/*      */         
/* 1363 */         StringTokenizer st = new StringTokenizer((String)value);
/* 1364 */         Vector<Integer> c = new Vector<>();
/* 1365 */         while (st.hasMoreTokens()) {
/* 1366 */           String tk = st.nextToken();
/* 1367 */           if (tk.equals("NONE")) {
/* 1368 */             c.add(Integer.valueOf(0)); continue;
/*      */           } 
/* 1370 */           if (tk.equals("RLE")) {
/* 1371 */             c.add(Integer.valueOf(1)); continue;
/*      */           } 
/* 1373 */           if (tk.equals("ZLIBFAST")) {
/* 1374 */             c.add(Integer.valueOf(2)); continue;
/*      */           } 
/* 1376 */           if (tk.equals("ZLIBHIGH")) {
/* 1377 */             c.add(Integer.valueOf(4));
/*      */             
/*      */             continue;
/*      */           } 
/* 1381 */           if (Trace.isOn) {
/* 1382 */             Trace.traceData(this, "Compressor value is not supported", null);
/*      */           }
/*      */           
/* 1385 */           HashMap<String, String> info = new HashMap<>();
/* 1386 */           info.put("XMSC_INSERT_VALUE", tk);
/* 1387 */           info.put("XMSC_INSERT_PROPERTY", "Compressor value");
/* 1388 */           JMSException je = (JMSException)NLSServices.createException("JMSMQ1006", info);
/*      */           
/* 1390 */           if (Trace.isOn) {
/* 1391 */             Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getMsgCompList()", (Throwable)je);
/*      */           }
/* 1393 */           throw je;
/*      */         } 
/*      */         
/* 1396 */         value = (Object<Integer>)c;
/*      */       } 
/*      */       
/* 1399 */       Collection<? extends Object> traceRet1 = (Collection)value;
/* 1400 */       if (Trace.isOn) {
/* 1401 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getMsgCompList()", traceRet1, 1);
/*      */       }
/* 1403 */       return traceRet1;
/*      */     }
/* 1405 */     catch (JMSException je) {
/* 1406 */       if (Trace.isOn) {
/* 1407 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getMsgCompList()", (Throwable)je);
/*      */       }
/* 1409 */       if (Trace.isOn) {
/* 1410 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getMsgCompList()", null, 2);
/*      */       }
/* 1412 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMsgCompListAsString() {
/* 1423 */     StringBuffer compString = null;
/* 1424 */     Collection<? extends Object> msgCompList = getMsgCompList();
/* 1425 */     if (msgCompList != null) {
/* 1426 */       compString = new StringBuffer("");
/* 1427 */       Iterator<? extends Object> it = msgCompList.iterator();
/* 1428 */       Integer compressor = Integer.valueOf(0);
/* 1429 */       if (it != null) {
/* 1430 */         while (it.hasNext()) {
/* 1431 */           compressor = (Integer)it.next();
/* 1432 */           switch (compressor.intValue()) {
/*      */             case 0:
/* 1434 */               compString.append("NONE ");
/*      */             
/*      */             case 1:
/* 1437 */               compString.append("RLE ");
/*      */             
/*      */             case 2:
/* 1440 */               compString.append("ZLIBFAST ");
/*      */             
/*      */             case 4:
/* 1443 */               compString.append("ZLIBHIGH ");
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         } 
/*      */       }
/*      */     } 
/* 1452 */     String traceRet1 = (compString == null) ? null : compString.toString();
/* 1453 */     if (Trace.isOn) {
/* 1454 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getMsgCompListAsString()", "getter", traceRet1);
/*      */     }
/*      */     
/* 1457 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public int getMulticast() throws JMSException {
/* 1469 */     if (this instanceof MQQueueConnectionFactory) {
/* 1470 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1471 */       inserts.put("XMSC_INSERT_METHOD", "getMulticast()");
/* 1472 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 1473 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 1474 */       if (Trace.isOn) {
/* 1475 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getMulticast()", (Throwable)je);
/*      */       }
/* 1477 */       throw je;
/*      */     } 
/*      */     
/* 1480 */     int traceRet1 = getIntProperty("multicast");
/*      */     
/* 1482 */     if (Trace.isOn) {
/* 1483 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getMulticast()", "getter", 
/* 1484 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1486 */     return traceRet1;
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
/*      */   public boolean getOptimisticPublication() throws JMSException {
/* 1500 */     boolean traceRet1 = getBooleanProperty("XMSC_WMQ_OPT_PUB");
/* 1501 */     if (Trace.isOn) {
/* 1502 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getOptimisticPublication()", "getter", 
/* 1503 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1505 */     return traceRet1;
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
/*      */   @Deprecated
/*      */   public boolean getOutcomeNotification() throws JMSException {
/* 1518 */     boolean traceRet1 = getBooleanProperty("XMSC_WMQ_OUTCOME_NOTIFICATION");
/* 1519 */     if (Trace.isOn) {
/* 1520 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getOutcomeNotification()", "getter", 
/* 1521 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1523 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPollingInterval() {
/* 1533 */     if (Trace.isOn) {
/* 1534 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getPollingInterval()");
/*      */     }
/*      */     
/*      */     try {
/* 1538 */       int traceRet1 = getIntProperty("XMSC_WMQ_POLLING_INTERVAL");
/* 1539 */       if (Trace.isOn) {
/* 1540 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getPollingInterval()", 
/* 1541 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/* 1543 */       return traceRet1;
/*      */     }
/* 1545 */     catch (JMSException je) {
/* 1546 */       if (Trace.isOn) {
/* 1547 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getPollingInterval()", (Throwable)je);
/*      */       }
/* 1549 */       if (Trace.isOn) {
/* 1550 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getPollingInterval()", 
/* 1551 */             Integer.valueOf(0), 2);
/*      */       }
/* 1553 */       return 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPort() {
/* 1563 */     if (Trace.isOn) {
/* 1564 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getPort()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1569 */       int traceRet1 = getIntProperty("XMSC_WMQ_PORT");
/* 1570 */       if (Trace.isOn) {
/* 1571 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getPort()", 
/* 1572 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/* 1574 */       return traceRet1;
/*      */     }
/* 1576 */     catch (JMSException je) {
/* 1577 */       if (Trace.isOn) {
/* 1578 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getPort()", (Throwable)je);
/*      */       }
/* 1580 */       if (Trace.isOn) {
/* 1581 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getPort()", Integer.valueOf(0), 2);
/*      */       }
/* 1583 */       return 0;
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
/*      */   @Deprecated
/*      */   public int getProcessDuration() throws JMSException {
/* 1597 */     int traceRet1 = getIntProperty("XMSC_WMQ_PROCESS_DURATION");
/* 1598 */     if (Trace.isOn) {
/* 1599 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getProcessDuration()", "getter", 
/* 1600 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1602 */     return traceRet1;
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
/*      */   public String getProviderVersion() throws JMSException {
/* 1615 */     String traceRet1 = getStringProperty("XMSC_WMQ_PROVIDER_VERSION");
/* 1616 */     if (Trace.isOn) {
/* 1617 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getProviderVersion()", "getter", traceRet1);
/*      */     }
/*      */     
/* 1620 */     return traceRet1;
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
/*      */   public String getProxyHostName() throws JMSException {
/* 1634 */     if (this instanceof MQQueueConnectionFactory) {
/* 1635 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1636 */       inserts.put("XMSC_INSERT_METHOD", "getProxyHostName()");
/* 1637 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 1638 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 1639 */       if (Trace.isOn) {
/* 1640 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getProxyHostName()", (Throwable)je);
/*      */       }
/* 1642 */       throw je;
/*      */     } 
/*      */     
/* 1645 */     String traceRet1 = getStringProperty("XMSC_RTT_PROXY_HOSTNAME");
/*      */     
/* 1647 */     if (Trace.isOn) {
/* 1648 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getProxyHostName()", "getter", traceRet1);
/*      */     }
/*      */     
/* 1651 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getProxyPort() throws JMSException {
/* 1662 */     if (this instanceof MQQueueConnectionFactory) {
/* 1663 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1664 */       inserts.put("XMSC_INSERT_METHOD", "getProxyPort()");
/* 1665 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 1666 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 1667 */       if (Trace.isOn) {
/* 1668 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getProxyPort()", (Throwable)je);
/*      */       }
/* 1670 */       throw je;
/*      */     } 
/*      */     
/* 1673 */     int traceRet1 = getIntProperty("XMSC_RTT_PROXY_PORT");
/*      */     
/* 1675 */     if (Trace.isOn) {
/* 1676 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getProxyPort()", "getter", 
/* 1677 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1679 */     return traceRet1;
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
/*      */   public int getPubAckInterval() throws JMSException {
/* 1691 */     if (this instanceof MQQueueConnectionFactory) {
/* 1692 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1693 */       inserts.put("XMSC_INSERT_METHOD", "getPubAckInterval()");
/* 1694 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 1695 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 1696 */       if (Trace.isOn) {
/* 1697 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getPubAckInterval()", (Throwable)je);
/*      */       }
/* 1699 */       throw je;
/*      */     } 
/*      */     
/* 1702 */     int traceRet1 = getIntProperty("XMSC_WMQ_PUB_ACK_INTERVAL");
/*      */     
/* 1704 */     if (Trace.isOn) {
/* 1705 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getPubAckInterval()", "getter", 
/* 1706 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1708 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getQueueManager() {
/* 1718 */     if (Trace.isOn) {
/* 1719 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getQueueManager()");
/*      */     }
/*      */     
/*      */     try {
/* 1723 */       String traceRet1 = getStringProperty("XMSC_WMQ_QUEUE_MANAGER");
/* 1724 */       if (Trace.isOn) {
/* 1725 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getQueueManager()", traceRet1, 1);
/*      */       }
/* 1727 */       return traceRet1;
/*      */     }
/* 1729 */     catch (JMSException je) {
/* 1730 */       if (Trace.isOn) {
/* 1731 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getQueueManager()", (Throwable)je);
/*      */       }
/* 1733 */       if (Trace.isOn) {
/* 1734 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getQueueManager()", null, 2);
/*      */       }
/* 1736 */       return null;
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
/*      */   public String getReceiveExit() {
/* 1748 */     if (Trace.isOn) {
/* 1749 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getReceiveExit()");
/*      */     }
/*      */     
/*      */     try {
/* 1753 */       String traceRet1 = getStringProperty("XMSC_WMQ_RECEIVE_EXIT");
/* 1754 */       if (Trace.isOn) {
/* 1755 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getReceiveExit()", traceRet1, 1);
/*      */       }
/* 1757 */       return traceRet1;
/*      */     }
/* 1759 */     catch (JMSException je) {
/* 1760 */       if (Trace.isOn) {
/* 1761 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getReceiveExit()", (Throwable)je);
/*      */       }
/* 1763 */       if (Trace.isOn) {
/* 1764 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getReceiveExit()", null, 2);
/*      */       }
/* 1766 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReceiveExitInit() {
/* 1776 */     if (Trace.isOn) {
/* 1777 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getReceiveExitInit()");
/*      */     }
/*      */     
/*      */     try {
/* 1781 */       String traceRet1 = getStringProperty("XMSC_WMQ_RECEIVE_EXIT_INIT");
/* 1782 */       if (Trace.isOn) {
/* 1783 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getReceiveExitInit()", traceRet1, 1);
/*      */       }
/*      */       
/* 1786 */       return traceRet1;
/*      */     }
/* 1788 */     catch (JMSException je) {
/* 1789 */       if (Trace.isOn) {
/* 1790 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getReceiveExitInit()", (Throwable)je);
/*      */       }
/* 1792 */       if (Trace.isOn) {
/* 1793 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getReceiveExitInit()", null, 2);
/*      */       }
/* 1795 */       return null;
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
/*      */   @Deprecated
/*      */   public int getReceiveIsolation() throws JMSException {
/* 1813 */     int traceRet1 = getIntProperty("XMSC_WMQ_RECEIVE_ISOLATION");
/* 1814 */     if (Trace.isOn) {
/* 1815 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getReceiveIsolation()", "getter", 
/* 1816 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1818 */     return traceRet1;
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
/*      */   public Reference getReference() throws NamingException {
/* 1833 */     Reference ref = new Reference(getClass().getName(), MQConnectionFactoryFactory.class.getName(), null);
/*      */ 
/*      */     
/* 1836 */     populateReference(ref);
/* 1837 */     Reference superClassReference = super.getReference();
/* 1838 */     Enumeration<RefAddr> e = superClassReference.getAll();
/* 1839 */     while (e.hasMoreElements()) {
/* 1840 */       RefAddr currentRefAddr = e.nextElement();
/* 1841 */       ref.add(currentRefAddr);
/*      */     } 
/*      */     
/* 1844 */     if (Trace.isOn) {
/* 1845 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getReference()", "getter", ref);
/*      */     }
/* 1847 */     return ref;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRescanInterval() {
/* 1858 */     if (Trace.isOn) {
/* 1859 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getRescanInterval()");
/*      */     }
/*      */     
/*      */     try {
/* 1863 */       int traceRet1 = getIntProperty("XMSC_WMQ_RESCAN_INTERVAL");
/* 1864 */       if (Trace.isOn) {
/* 1865 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getRescanInterval()", 
/* 1866 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/* 1868 */       return traceRet1;
/*      */     }
/* 1870 */     catch (JMSException je) {
/* 1871 */       if (Trace.isOn) {
/* 1872 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getRescanInterval()", (Throwable)je);
/*      */       }
/* 1874 */       if (Trace.isOn) {
/* 1875 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getRescanInterval()", 
/* 1876 */             Integer.valueOf(0), 2);
/*      */       }
/* 1878 */       return 0;
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
/*      */   public String getSecurityExit() {
/* 1890 */     if (Trace.isOn) {
/* 1891 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getSecurityExit()");
/*      */     }
/*      */     
/*      */     try {
/* 1895 */       String traceRet1 = getStringProperty("XMSC_WMQ_SECURITY_EXIT");
/* 1896 */       if (Trace.isOn) {
/* 1897 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSecurityExit()", traceRet1, 1);
/*      */       }
/* 1899 */       return traceRet1;
/*      */     }
/* 1901 */     catch (JMSException je) {
/* 1902 */       if (Trace.isOn) {
/* 1903 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getSecurityExit()", (Throwable)je);
/*      */       }
/* 1905 */       if (Trace.isOn) {
/* 1906 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSecurityExit()", null, 2);
/*      */       }
/* 1908 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSecurityExitInit() {
/* 1918 */     if (Trace.isOn) {
/* 1919 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getSecurityExitInit()");
/*      */     }
/*      */     
/*      */     try {
/* 1923 */       String traceRet1 = getStringProperty("XMSC_WMQ_SECURITY_EXIT_INIT");
/* 1924 */       if (Trace.isOn) {
/* 1925 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSecurityExitInit()", traceRet1, 1);
/*      */       }
/*      */       
/* 1928 */       return traceRet1;
/*      */     }
/* 1930 */     catch (JMSException je) {
/* 1931 */       if (Trace.isOn) {
/* 1932 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getSecurityExitInit()", (Throwable)je);
/*      */       }
/* 1934 */       if (Trace.isOn) {
/* 1935 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSecurityExitInit()", null, 2);
/*      */       }
/* 1937 */       return null;
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
/*      */   public int getSendCheckCount() throws JMSException {
/* 1949 */     int traceRet1 = getIntProperty("XMSC_WMQ_SEND_CHECK_COUNT");
/* 1950 */     if (Trace.isOn) {
/* 1951 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getSendCheckCount()", "getter", 
/* 1952 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1954 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSendExit() {
/* 1965 */     if (Trace.isOn) {
/* 1966 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getSendExit()");
/*      */     }
/*      */     
/*      */     try {
/* 1970 */       String traceRet1 = getStringProperty("XMSC_WMQ_SEND_EXIT");
/* 1971 */       if (Trace.isOn) {
/* 1972 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSendExit()", traceRet1, 1);
/*      */       }
/* 1974 */       return traceRet1;
/*      */     }
/* 1976 */     catch (JMSException je) {
/* 1977 */       if (Trace.isOn) {
/* 1978 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getSendExit()", (Throwable)je);
/*      */       }
/* 1980 */       if (Trace.isOn) {
/* 1981 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSendExit()", null, 2);
/*      */       }
/* 1983 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSendExitInit() {
/* 1993 */     if (Trace.isOn) {
/* 1994 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getSendExitInit()");
/*      */     }
/*      */     
/*      */     try {
/* 1998 */       String traceRet1 = getStringProperty("XMSC_WMQ_SEND_EXIT_INIT");
/* 1999 */       if (Trace.isOn) {
/* 2000 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSendExitInit()", traceRet1, 1);
/*      */       }
/* 2002 */       return traceRet1;
/*      */     }
/* 2004 */     catch (JMSException je) {
/* 2005 */       if (Trace.isOn) {
/* 2006 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getSendExitInit()", (Throwable)je);
/*      */       }
/* 2008 */       if (Trace.isOn) {
/* 2009 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSendExitInit()", null, 2);
/*      */       }
/* 2011 */       return null;
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
/*      */   public int getShareConvAllowed() throws JMSException {
/* 2024 */     int traceRet1 = getIntProperty("XMSC_WMQ_SHARE_CONV_ALLOWED");
/* 2025 */     if (Trace.isOn) {
/* 2026 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getShareConvAllowed()", "getter", 
/* 2027 */           Integer.valueOf(traceRet1));
/*      */     }
/* 2029 */     return traceRet1;
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
/*      */   public boolean getSparseSubscriptions() throws JMSException {
/* 2042 */     if (this instanceof MQQueueConnectionFactory) {
/* 2043 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2044 */       inserts.put("XMSC_INSERT_METHOD", "getSparseSubscriptions()");
/* 2045 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 2046 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 2047 */       if (Trace.isOn) {
/* 2048 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getSparseSubscriptions()", (Throwable)je);
/*      */       }
/* 2050 */       throw je;
/*      */     } 
/*      */     
/* 2053 */     boolean traceRet1 = getBooleanProperty("XMSC_WMQ_SPARSE_SUBSCRIPTIONS");
/*      */     
/* 2055 */     if (Trace.isOn) {
/* 2056 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getSparseSubscriptions()", "getter", 
/* 2057 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 2059 */     return traceRet1;
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
/*      */   public Collection<? extends Object> getSSLCertStores() throws JMSException {
/* 2072 */     if (Trace.isOn) {
/* 2073 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLCertStores()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2083 */     Object sslCertStores_coll = getObjectProperty("XMSC_WMQ_SSL_CERT_STORES_COL");
/* 2084 */     if (sslCertStores_coll != null) {
/* 2085 */       Collection<? extends Object> traceRet1 = (Collection<? extends Object>)sslCertStores_coll;
/* 2086 */       if (Trace.isOn) {
/* 2087 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLCertStores()", traceRet1, 1);
/*      */       }
/* 2089 */       return traceRet1;
/*      */     } 
/* 2091 */     if (getObjectProperty("XMSC_WMQ_SSL_CERT_STORES_STR") == null) {
/* 2092 */       if (Trace.isOn) {
/* 2093 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLCertStores()", null, 2);
/*      */       }
/* 2095 */       return null;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2100 */     String sslCertStores_string = getStringProperty("XMSC_WMQ_SSL_CERT_STORES_STR");
/*      */     
/* 2102 */     if (sslCertStores_string != null && !sslCertStores_string.trim().equals("")) {
/*      */       try {
/* 2104 */         sslCertStores_coll = JmqiUtils.getCertStoreCollectionFromSpaceSeperatedString(sslCertStores_string);
/*      */       }
/* 2106 */       catch (Exception e) {
/* 2107 */         if (Trace.isOn) {
/* 2108 */           Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLCertStores()", e);
/*      */         }
/* 2110 */         HashMap<String, String> info = new HashMap<>();
/* 2111 */         info.put("XMSC_INSERT_VALUE", sslCertStores_string);
/* 2112 */         info.put("XMSC_INSERT_PROPERTY", "XMSC_WMQ_SSL_CERT_STORES_STR");
/* 2113 */         JMSException je = (JMSException)NLSServices.createException("JMSMQ1006", info);
/* 2114 */         je.setLinkedException(e);
/* 2115 */         if (Trace.isOn) {
/* 2116 */           Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLCertStores()", (Throwable)je);
/*      */         }
/* 2118 */         throw je;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2123 */     Collection<? extends Object> traceRet4 = (Collection<? extends Object>)sslCertStores_coll;
/* 2124 */     if (Trace.isOn) {
/* 2125 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLCertStores()", traceRet4, 3);
/*      */     }
/* 2127 */     return traceRet4;
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
/*      */   public String getSSLCertStoresAsString() throws JMSException {
/* 2140 */     String sslCertStores_string = getStringProperty("XMSC_WMQ_SSL_CERT_STORES_STR");
/* 2141 */     if (sslCertStores_string == null && getObjectProperty("XMSC_WMQ_SSL_CERT_STORES_COL") != null) {
/*      */       
/* 2143 */       HashMap<String, String> info = new HashMap<>();
/* 2144 */       info.put("XMSC_INSERT_NAME", "getSSLCertStoresAsString");
/* 2145 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ0000", info);
/*      */       
/* 2147 */       if (Trace.isOn) {
/* 2148 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLCertStoresAsString()", (Throwable)je);
/*      */       }
/*      */       
/* 2151 */       throw je;
/*      */     } 
/*      */     
/* 2154 */     if (Trace.isOn) {
/* 2155 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLCertStoresAsString()", "getter", sslCertStores_string);
/*      */     }
/*      */     
/* 2158 */     return sslCertStores_string;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSSLCipherSuite() {
/* 2168 */     if (Trace.isOn) {
/* 2169 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLCipherSuite()");
/*      */     }
/*      */     
/*      */     try {
/* 2173 */       String traceRet1 = getStringProperty("XMSC_WMQ_SSL_CIPHER_SUITE");
/* 2174 */       if (Trace.isOn) {
/* 2175 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLCipherSuite()", traceRet1, 1);
/*      */       }
/* 2177 */       return traceRet1;
/*      */     }
/* 2179 */     catch (JMSException je) {
/* 2180 */       if (Trace.isOn) {
/* 2181 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLCipherSuite()", (Throwable)je);
/*      */       }
/* 2183 */       if (Trace.isOn) {
/* 2184 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLCipherSuite()", null, 2);
/*      */       }
/* 2186 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getSSLFipsRequired() {
/* 2196 */     if (Trace.isOn) {
/* 2197 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLFipsRequired()");
/*      */     }
/*      */     
/*      */     try {
/* 2201 */       boolean traceRet1 = getBooleanProperty("XMSC_WMQ_SSL_FIPS_REQUIRED");
/* 2202 */       if (Trace.isOn) {
/* 2203 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLFipsRequired()", 
/* 2204 */             Boolean.valueOf(traceRet1), 1);
/*      */       }
/* 2206 */       return traceRet1;
/*      */     }
/* 2208 */     catch (JMSException je) {
/* 2209 */       if (Trace.isOn) {
/* 2210 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLFipsRequired()", (Throwable)je);
/*      */       }
/* 2212 */       if (Trace.isOn) {
/* 2213 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLFipsRequired()", 
/* 2214 */             Boolean.valueOf(false), 2);
/*      */       }
/* 2216 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSSLPeerName() {
/* 2226 */     if (Trace.isOn) {
/* 2227 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLPeerName()");
/*      */     }
/*      */     
/*      */     try {
/* 2231 */       String traceRet1 = getStringProperty("XMSC_WMQ_SSL_PEER_NAME");
/* 2232 */       if (Trace.isOn) {
/* 2233 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLPeerName()", traceRet1, 1);
/*      */       }
/* 2235 */       return traceRet1;
/*      */     }
/* 2237 */     catch (JMSException je) {
/* 2238 */       if (Trace.isOn) {
/* 2239 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLPeerName()", (Throwable)je);
/*      */       }
/* 2241 */       if (Trace.isOn) {
/* 2242 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLPeerName()", null, 2);
/*      */       }
/* 2244 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSSLResetCount() {
/* 2254 */     if (Trace.isOn) {
/* 2255 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLResetCount()");
/*      */     }
/*      */     
/*      */     try {
/* 2259 */       int traceRet1 = getIntProperty("XMSC_WMQ_SSL_KEY_RESETCOUNT");
/* 2260 */       if (Trace.isOn) {
/* 2261 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLResetCount()", 
/* 2262 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/* 2264 */       return traceRet1;
/*      */     }
/* 2266 */     catch (JMSException je) {
/* 2267 */       if (Trace.isOn) {
/* 2268 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLResetCount()", (Throwable)je);
/*      */       }
/* 2270 */       if (Trace.isOn) {
/* 2271 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLResetCount()", 
/* 2272 */             Integer.valueOf(0), 2);
/*      */       }
/* 2274 */       return 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getSSLSocketFactory() {
/* 2284 */     if (Trace.isOn) {
/* 2285 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLSocketFactory()");
/*      */     }
/*      */     
/*      */     try {
/* 2289 */       Object traceRet1 = getObjectProperty("XMSC_WMQ_SSL_SOCKET_FACTORY");
/* 2290 */       if (Trace.isOn) {
/* 2291 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLSocketFactory()", traceRet1, 1);
/*      */       }
/*      */       
/* 2294 */       return traceRet1;
/*      */     }
/* 2296 */     catch (JMSException je) {
/* 2297 */       if (Trace.isOn) {
/* 2298 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLSocketFactory()", (Throwable)je);
/*      */       }
/* 2300 */       if (Trace.isOn) {
/* 2301 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSSLSocketFactory()", null, 2);
/*      */       }
/* 2303 */       return null;
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
/*      */   public int getStatusRefreshInterval() throws JMSException {
/* 2323 */     if (this instanceof MQQueueConnectionFactory) {
/* 2324 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2325 */       inserts.put("XMSC_INSERT_METHOD", "getStatusRefreshInterval()");
/* 2326 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 2327 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 2328 */       if (Trace.isOn) {
/* 2329 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getStatusRefreshInterval()", (Throwable)je);
/*      */       }
/*      */       
/* 2332 */       throw je;
/*      */     } 
/*      */     
/* 2335 */     int traceRet1 = getIntProperty("XMSC_WMQ_STATUS_REFRESH_INTERVAL");
/*      */     
/* 2337 */     if (Trace.isOn) {
/* 2338 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getStatusRefreshInterval()", "getter", 
/* 2339 */           Integer.valueOf(traceRet1));
/*      */     }
/* 2341 */     return traceRet1;
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
/*      */   public int getSubscriptionStore() throws JMSException {
/* 2359 */     if (this instanceof MQQueueConnectionFactory) {
/* 2360 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2361 */       inserts.put("XMSC_INSERT_METHOD", "getSubscriptionStore()");
/* 2362 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 2363 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 2364 */       if (Trace.isOn) {
/* 2365 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getSubscriptionStore()", (Throwable)je);
/*      */       }
/* 2367 */       throw je;
/*      */     } 
/*      */     
/* 2370 */     int traceRet1 = getIntProperty("XMSC_WMQ_SUBSCRIPTION_STORE");
/*      */     
/* 2372 */     if (Trace.isOn) {
/* 2373 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getSubscriptionStore()", "getter", 
/* 2374 */           Integer.valueOf(traceRet1));
/*      */     }
/* 2376 */     return traceRet1;
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
/*      */   public boolean getSyncpointAllGets() {
/* 2388 */     if (Trace.isOn) {
/* 2389 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getSyncpointAllGets()");
/*      */     }
/*      */     
/*      */     try {
/* 2393 */       boolean sag = getBooleanProperty("XMSC_WMQ_SYNCPOINT_ALL_GETS");
/* 2394 */       if (Trace.isOn) {
/* 2395 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSyncpointAllGets()", 
/* 2396 */             Boolean.valueOf(sag), 1);
/*      */       }
/* 2398 */       return sag;
/*      */     }
/* 2400 */     catch (JMSException je) {
/* 2401 */       if (Trace.isOn) {
/* 2402 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getSyncpointAllGets()", (Throwable)je);
/*      */       }
/* 2404 */       if (Trace.isOn) {
/* 2405 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getSyncpointAllGets()", 
/* 2406 */             Boolean.valueOf(false), 2);
/*      */       }
/* 2408 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getTargetClientMatching() {
/* 2419 */     if (Trace.isOn) {
/* 2420 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getTargetClientMatching()");
/*      */     }
/*      */     
/*      */     try {
/* 2424 */       boolean traceRet1 = getBooleanProperty("XMSC_WMQ_TARGET_CLIENT_MATCHING");
/* 2425 */       if (Trace.isOn) {
/* 2426 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getTargetClientMatching()", 
/* 2427 */             Boolean.valueOf(traceRet1), 1);
/*      */       }
/* 2429 */       return traceRet1;
/*      */     }
/* 2431 */     catch (JMSException je) {
/* 2432 */       if (Trace.isOn) {
/* 2433 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getTargetClientMatching()", (Throwable)je);
/*      */       }
/*      */       
/* 2436 */       if (Trace.isOn) {
/* 2437 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getTargetClientMatching()", 
/* 2438 */             Boolean.valueOf(false), 2);
/*      */       }
/* 2440 */       return false;
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
/*      */   public String getTemporaryModel() throws JMSException {
/* 2459 */     if (this instanceof MQTopicConnectionFactory) {
/* 2460 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2461 */       inserts.put("XMSC_INSERT_METHOD", "getTemporaryModel()");
/* 2462 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 2463 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 2464 */       if (Trace.isOn) {
/* 2465 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getTemporaryModel()", (Throwable)je);
/*      */       }
/* 2467 */       throw je;
/*      */     } 
/*      */     
/* 2470 */     String traceRet1 = getStringProperty("XMSC_WMQ_TEMPORARY_MODEL");
/*      */     
/* 2472 */     if (Trace.isOn) {
/* 2473 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getTemporaryModel()", "getter", traceRet1);
/*      */     }
/*      */     
/* 2476 */     return traceRet1;
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
/*      */   public String getTempTopicPrefix() throws JMSException {
/* 2490 */     if (this instanceof MQQueueConnectionFactory) {
/* 2491 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2492 */       inserts.put("XMSC_INSERT_METHOD", "getTempTopicPrefix()");
/* 2493 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 2494 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 2495 */       if (Trace.isOn) {
/* 2496 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getTempTopicPrefix()", (Throwable)je);
/*      */       }
/* 2498 */       throw je;
/*      */     } 
/*      */     
/* 2501 */     String traceRet1 = getStringProperty("XMSC_WMQ_TEMP_TOPIC_PREFIX");
/* 2502 */     if (Trace.isOn) {
/* 2503 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getTempTopicPrefix()", "getter", traceRet1);
/*      */     }
/*      */     
/* 2506 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTempQPrefix() throws JMSException {
/* 2517 */     if (this instanceof MQTopicConnectionFactory) {
/* 2518 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2519 */       inserts.put("XMSC_INSERT_METHOD", "getTempQPrefix()");
/* 2520 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 2521 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 2522 */       if (Trace.isOn) {
/* 2523 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "getTempQPrefix()", (Throwable)je);
/*      */       }
/* 2525 */       throw je;
/*      */     } 
/*      */     
/* 2528 */     String traceRet1 = getStringProperty("XMSC_WMQ_TEMP_Q_PREFIX");
/*      */     
/* 2530 */     if (Trace.isOn) {
/* 2531 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getTempQPrefix()", "getter", traceRet1);
/*      */     }
/*      */     
/* 2534 */     return traceRet1;
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
/*      */   public int getTransportType() {
/* 2549 */     if (Trace.isOn) {
/* 2550 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getTransportType()");
/*      */     }
/*      */     
/*      */     try {
/* 2554 */       int traceRet1 = getIntProperty("XMSC_WMQ_CONNECTION_MODE");
/* 2555 */       if (Trace.isOn) {
/* 2556 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getTransportType()", 
/* 2557 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/* 2559 */       return traceRet1;
/*      */     }
/* 2561 */     catch (JMSException je) {
/* 2562 */       if (Trace.isOn) {
/* 2563 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getTransportType()", (Throwable)je);
/*      */       }
/* 2565 */       if (Trace.isOn) {
/* 2566 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getTransportType()", 
/* 2567 */             Integer.valueOf(0), 2);
/*      */       }
/* 2569 */       return 0;
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
/*      */   @Deprecated
/*      */   public boolean getUseConnectionPooling() {
/* 2583 */     if (Trace.isOn) {
/* 2584 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getUseConnectionPooling()");
/*      */     }
/*      */     
/*      */     try {
/* 2588 */       boolean traceRet1 = getBooleanProperty("XMSC_WMQ_USE_CONNECTION_POOLING");
/* 2589 */       if (Trace.isOn) {
/* 2590 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getUseConnectionPooling()", 
/* 2591 */             Boolean.valueOf(traceRet1), 1);
/*      */       }
/* 2593 */       return traceRet1;
/*      */     }
/* 2595 */     catch (JMSException je) {
/* 2596 */       if (Trace.isOn) {
/* 2597 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getUseConnectionPooling()", (Throwable)je);
/*      */       }
/*      */       
/* 2600 */       if (Trace.isOn) {
/* 2601 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getUseConnectionPooling()", 
/* 2602 */             Boolean.valueOf(false), 2);
/*      */       }
/* 2604 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/* 2615 */     if (Trace.isOn) {
/* 2616 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getVersion()");
/*      */     }
/*      */     
/*      */     try {
/* 2620 */       int traceRet1 = getIntProperty("version");
/* 2621 */       if (Trace.isOn) {
/* 2622 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getVersion()", 
/* 2623 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/* 2625 */       return traceRet1;
/*      */     }
/* 2627 */     catch (JMSException je) {
/* 2628 */       if (Trace.isOn) {
/* 2629 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getVersion()", (Throwable)je);
/*      */       }
/* 2631 */       if (Trace.isOn) {
/* 2632 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getVersion()", Integer.valueOf(0), 2);
/*      */       }
/*      */       
/* 2635 */       return 0;
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
/*      */   public int getWildcardFormat() throws JMSException {
/* 2650 */     int traceRet1 = getIntProperty("wildcardFormat");
/* 2651 */     if (Trace.isOn) {
/* 2652 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getWildcardFormat()", "getter", 
/* 2653 */           Integer.valueOf(traceRet1));
/*      */     }
/* 2655 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAppName() {
/* 2662 */     if (Trace.isOn) {
/* 2663 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getAppName()");
/*      */     }
/*      */     try {
/* 2666 */       String traceRet1 = getStringProperty("XMSC_WMQ_APPNAME");
/* 2667 */       if (Trace.isOn) {
/* 2668 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getAppName()", traceRet1, 1);
/*      */       }
/* 2670 */       return traceRet1;
/*      */     }
/* 2672 */     catch (JMSException je) {
/* 2673 */       if (Trace.isOn) {
/* 2674 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getAppName()", (Throwable)je);
/*      */       }
/* 2676 */       if (Trace.isOn) {
/* 2677 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getAppName()", null, 2);
/*      */       }
/* 2679 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAsyncExceptions() {
/* 2689 */     if (Trace.isOn) {
/* 2690 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "getAsyncExceptions()");
/*      */     }
/*      */     
/*      */     try {
/* 2694 */       int traceRet1 = getIntProperty("XMSC_ASYNC_EXCEPTIONS");
/* 2695 */       if (Trace.isOn) {
/* 2696 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getAsyncExceptions()", 
/* 2697 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/* 2699 */       return traceRet1;
/*      */     }
/* 2701 */     catch (JMSException je) {
/* 2702 */       if (Trace.isOn) {
/* 2703 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "getAsyncExceptions()", (Throwable)je);
/*      */       }
/* 2705 */       if (Trace.isOn) {
/* 2706 */         Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "getAsyncExceptions()", 
/* 2707 */             Integer.valueOf(0), 2);
/*      */       }
/* 2709 */       return 0;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void populateReference(Reference ref) throws OperationNotSupportedException {
/* 2714 */     if (Trace.isOn) {
/* 2715 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "populateReference(Reference)", new Object[] { ref });
/*      */     }
/*      */     
/* 2718 */     String s = null;
/*      */ 
/*      */     
/*      */     try {
/* 2722 */       updateReference(ref);
/*      */ 
/*      */       
/* 2725 */       if (!(this instanceof MQTopicConnectionFactory)) {
/* 2726 */         s = getTemporaryModel();
/* 2727 */         if (s != null) {
/* 2728 */           ref.add(new StringRefAddr("TM", s));
/*      */         }
/* 2730 */         s = getTempQPrefix();
/* 2731 */         if (s != null) {
/* 2732 */           ref.add(new StringRefAddr("TQPFX", s));
/*      */         }
/* 2734 */         ref.add(new StringRefAddr("MRET", String.valueOf(getMessageRetention())));
/*      */       } 
/*      */ 
/*      */       
/* 2738 */       if (!(this instanceof MQQueueConnectionFactory)) {
/* 2739 */         s = getBrokerControlQueue();
/* 2740 */         if (s != null) {
/* 2741 */           ref.add(new StringRefAddr("BCON", s));
/*      */         }
/* 2743 */         s = getBrokerPubQueue();
/* 2744 */         if (s != null) {
/* 2745 */           ref.add(new StringRefAddr("BPUB", s));
/*      */         }
/* 2747 */         s = getBrokerSubQueue();
/* 2748 */         if (s != null) {
/* 2749 */           ref.add(new StringRefAddr("BSUB", s));
/*      */         }
/* 2751 */         s = getBrokerCCSubQueue();
/* 2752 */         if (s != null) {
/* 2753 */           ref.add(new StringRefAddr("CCSUB", s));
/*      */         }
/* 2755 */         s = getBrokerQueueManager();
/* 2756 */         if (s != null) {
/* 2757 */           ref.add(new StringRefAddr("BQM", s));
/*      */         }
/* 2759 */         s = getProxyHostName();
/* 2760 */         if (s != null) {
/* 2761 */           ref.add(new StringRefAddr("PHOST", s));
/*      */         }
/* 2763 */         s = getTempTopicPrefix();
/* 2764 */         if (s != null) {
/* 2765 */           ref.add(new StringRefAddr("TTP", s));
/*      */         }
/* 2767 */         ref.add(new StringRefAddr("MSEL", String.valueOf(getMessageSelection())));
/* 2768 */         ref.add(new StringRefAddr("PAI", String.valueOf(getPubAckInterval())));
/* 2769 */         ref.add(new StringRefAddr("SRI", String.valueOf(getStatusRefreshInterval())));
/* 2770 */         ref.add(new StringRefAddr("SUBST", String.valueOf(getSubscriptionStore())));
/* 2771 */         ref.add(new StringRefAddr("CL", String.valueOf(getCleanupLevel())));
/* 2772 */         ref.add(new StringRefAddr("CLINT", String.valueOf(getCleanupInterval())));
/* 2773 */         ref.add(new StringRefAddr("CLS", String.valueOf(getCloneSupport())));
/* 2774 */         ref.add(new StringRefAddr("SSUBS", String.valueOf(getSparseSubscriptions())));
/* 2775 */         ref.add(new StringRefAddr("MCAST", String.valueOf(getMulticast())));
/* 2776 */         ref.add(new StringRefAddr("PPORT", String.valueOf(getProxyPort())));
/* 2777 */         ref.add(new StringRefAddr("DAUTH", String.valueOf(getDirectAuth())));
/* 2778 */         ref.add(new StringRefAddr("MBSZ", String.valueOf(getMaxBufferSize())));
/*      */         
/* 2780 */         if (!(this instanceof javax.jms.XATopicConnectionFactory)) {
/* 2781 */           ref.add(new StringRefAddr("RCVISOL", String.valueOf(getReceiveIsolation())));
/* 2782 */           ref.add(new StringRefAddr("PROCDUR", String.valueOf(getProcessDuration())));
/* 2783 */           ref.add(new StringRefAddr("NOTIFY", String.valueOf(getOutcomeNotification())));
/*      */         } 
/*      */         
/* 2786 */         ref.add(new StringRefAddr("OPTPUB", String.valueOf(getOptimisticPublication())));
/* 2787 */         ref.add(new StringRefAddr("CCDTURL", String.valueOf(getCCDTURL())));
/*      */ 
/*      */         
/* 2790 */         int brokerVersion = getBrokerVersion();
/* 2791 */         if (brokerVersion == -1)
/*      */         {
/* 2793 */           ref.add(new StringRefAddr("BVER", String.valueOf(0)));
/* 2794 */           ref.add(new StringRefAddr("BVERU", String.valueOf(true)));
/*      */         }
/*      */         else
/*      */         {
/* 2798 */           ref.add(new StringRefAddr("BVER", String.valueOf(brokerVersion)));
/* 2799 */           ref.add(new StringRefAddr("BVERU", String.valueOf(false)));
/*      */         }
/*      */       
/*      */       }
/*      */     
/* 2804 */     } catch (JMSException e) {
/* 2805 */       if (Trace.isOn) {
/* 2806 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "populateReference(Reference)", (Throwable)e);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2815 */     if (Trace.isOn) {
/* 2816 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "populateReference(Reference)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 2824 */     if (Trace.isOn) {
/* 2825 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2833 */     this.versionChangeAllowed = false;
/* 2834 */     this.connectionType = "com.ibm.msg.client.wmq";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2841 */     initialiseMQConnectionFactory();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2849 */       if (this instanceof MQXAConnectionFactory || this instanceof MQXATopicConnectionFactory || this instanceof MQXAQueueConnectionFactory) {
/* 2850 */         setProviderFactory(true);
/*      */       } else {
/*      */         
/* 2853 */         setProviderFactory(false);
/*      */       }
/*      */     
/* 2856 */     } catch (JMSException j) {
/* 2857 */       if (Trace.isOn) {
/* 2858 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "readObject(java.io.ObjectInputStream)", (Throwable)j, 1);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2870 */     setDefaultProperties();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2878 */     ObjectInputStream.GetField fields = in.readFields();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2902 */       if (!fields.defaulted("applicationName")) {
/* 2903 */         String appName = (String)fields.get("applicationName", (Object)null);
/* 2904 */         if (appName != null) {
/* 2905 */           setAppName(appName);
/*      */         }
/*      */       } 
/* 2908 */       if (!fields.defaulted("asyncExceptions")) {
/* 2909 */         setAsyncExceptions(fields.get("asyncExceptions", 0));
/*      */       }
/* 2911 */       if (!fields.defaulted("bverSet")) {
/* 2912 */         this.bverSet = fields.get("bverSet", false);
/*      */       }
/* 2914 */       if (!fields.defaulted("ccdtUrl")) {
/* 2915 */         setCCDTURL((URL)fields.get("ccdtUrl", (Object)null));
/*      */       }
/* 2917 */       if (!fields.defaulted("CCSID")) {
/* 2918 */         setCCSID(fields.get("CCSID", 0));
/*      */       }
/* 2920 */       if (!fields.defaulted("channel")) {
/* 2921 */         setChannel((String)fields.get("channel", (Object)null));
/*      */       }
/* 2923 */       if (!fields.defaulted("clientId")) {
/* 2924 */         setClientID((String)fields.get("clientId", (Object)null));
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2930 */       if (!fields.defaulted("connectionNameList")) {
/* 2931 */         setConnectionNameListInternal((String)fields.get("connectionNameList", (Object)null));
/*      */       }
/* 2933 */       if (!fields.defaulted("clientReconnectOptions")) {
/* 2934 */         setClientReconnectOptions(fields.get("clientReconnectOptions", 0));
/*      */       }
/* 2936 */       if (!fields.defaulted("clientReconnectTimeout")) {
/* 2937 */         setClientReconnectTimeout(fields.get("clientReconnectTimeout", 0));
/*      */       }
/* 2939 */       if (!fields.defaulted("connOptions")) {
/* 2940 */         setMQConnectionOptions(fields.get("connOptions", 0));
/*      */       }
/* 2942 */       if (!fields.defaulted("connTag")) {
/* 2943 */         setConnTag((byte[])fields.get("connTag", (Object)null));
/*      */       }
/* 2945 */       if (!fields.defaulted("description")) {
/* 2946 */         setDescription((String)fields.get("description", (Object)null));
/*      */       }
/* 2948 */       if (!fields.defaulted("failIfQuiesce")) {
/* 2949 */         setFailIfQuiesce(fields.get("failIfQuiesce", 0));
/*      */       }
/* 2951 */       if (!fields.defaulted("hdrCompList")) {
/* 2952 */         setHdrCompList((Collection)fields.get("hdrCompList", (Object)null));
/*      */       }
/* 2954 */       boolean hostIsDefault = true;
/* 2955 */       boolean portIsDefault = true;
/* 2956 */       if (!fields.defaulted("hostName")) {
/* 2957 */         String host = (String)fields.get("hostName", (Object)null);
/* 2958 */         if (host != null && !host.equalsIgnoreCase("localhost")) {
/* 2959 */           hostIsDefault = false;
/*      */         }
/*      */       } 
/* 2962 */       if (!fields.defaulted("localAddress")) {
/* 2963 */         setLocalAddress((String)fields.get("localAddress", (Object)null));
/*      */       }
/* 2965 */       if (!fields.defaulted("mapNameStyle")) {
/* 2966 */         setMapNameStyle(fields.get("mapNameStyle", false));
/*      */       }
/* 2968 */       if (!fields.defaulted("msgBatchSize")) {
/* 2969 */         setMsgBatchSize(fields.get("msgBatchSize", 0));
/*      */       }
/* 2971 */       if (!fields.defaulted("msgCompList")) {
/* 2972 */         setMsgCompList((Collection)fields.get("msgCompList", (Object)null));
/*      */       }
/* 2974 */       if (!fields.defaulted("mselSet")) {
/* 2975 */         this.mselSet = fields.get("mselSet", false);
/*      */       }
/* 2977 */       if (!fields.defaulted("optimisticPublication")) {
/* 2978 */         setOptimisticPublication(fields.get("optimisticPublication", false));
/*      */       }
/* 2980 */       if (!fields.defaulted("outcomeNotification")) {
/* 2981 */         setOutcomeNotification(fields.get("outcomeNotification", false));
/*      */       }
/* 2983 */       if (!fields.defaulted("pollingInterval")) {
/* 2984 */         setPollingInterval(fields.get("pollingInterval", 0));
/*      */       }
/* 2986 */       if (!fields.defaulted("port")) {
/* 2987 */         int port = fields.get("port", 0);
/* 2988 */         if (port != 1414 && port != 1506) {
/* 2989 */           portIsDefault = false;
/*      */         }
/*      */       } 
/*      */       
/* 2993 */       if (!hostIsDefault || !portIsDefault) {
/* 2994 */         setHostName((String)fields.get("hostName", (Object)null));
/* 2995 */         setPort(fields.get("port", 0));
/*      */       }
/* 2997 */       else if (!fields.defaulted("portSet")) {
/* 2998 */         this.portSet = fields.get("portSet", false);
/*      */       } 
/* 3000 */       if (!fields.defaulted("processDuration")) {
/* 3001 */         setProcessDuration(fields.get("processDuration", 0));
/*      */       }
/* 3003 */       if (!fields.defaulted("providerVersion")) {
/* 3004 */         setProviderVersion((String)fields.get("providerVersion", (Object)null));
/*      */       }
/* 3006 */       if (!fields.defaulted("queueManager")) {
/* 3007 */         setQueueManager((String)fields.get("queueManager", (Object)null));
/*      */       }
/* 3009 */       if (!fields.defaulted("receiveExit")) {
/* 3010 */         setReceiveExit((String)fields.get("receiveExit", (Object)null));
/*      */       }
/* 3012 */       if (!fields.defaulted("receiveExitInit")) {
/* 3013 */         setReceiveExitInit((String)fields.get("receiveExitInit", (Object)null));
/*      */       }
/* 3015 */       if (!fields.defaulted("receiveIsolation")) {
/* 3016 */         setReceiveIsolation(fields.get("receiveIsolation", 0));
/*      */       }
/* 3018 */       if (!fields.defaulted("rescanInterval")) {
/* 3019 */         setRescanInterval(fields.get("rescanInterval", 0));
/*      */       }
/* 3021 */       if (!fields.defaulted("securityExit")) {
/* 3022 */         setSecurityExit((String)fields.get("securityExit", (Object)null));
/*      */       }
/* 3024 */       if (!fields.defaulted("securityExitInit")) {
/* 3025 */         setSecurityExitInit((String)fields.get("securityExitInit", (Object)null));
/*      */       }
/* 3027 */       if (!fields.defaulted("sendExit")) {
/* 3028 */         setSendExit((String)fields.get("sendExit", (Object)null));
/*      */       }
/* 3030 */       if (!fields.defaulted("sendExitInit")) {
/* 3031 */         setSendExitInit((String)fields.get("sendExitInit", (Object)null));
/*      */       }
/* 3033 */       if (!fields.defaulted("sendCheckCount")) {
/* 3034 */         setSendCheckCount(fields.get("sendCheckCount", 0));
/*      */       }
/* 3036 */       if (!fields.defaulted("shareConvAllowed")) {
/* 3037 */         setShareConvAllowed(fields.get("shareConvAllowed", 0));
/*      */       }
/* 3039 */       if (!fields.defaulted("sslCertStores_coll")) {
/* 3040 */         setSSLCertStores((Collection)fields.get("sslCertStores_coll", (Object)null));
/*      */       }
/* 3042 */       if (!fields.defaulted("sslCertStores_string")) {
/* 3043 */         setSSLCertStores((String)fields.get("sslCertStores_string", (Object)null));
/*      */       }
/* 3045 */       if (!fields.defaulted("sslCipherSuite")) {
/* 3046 */         setSSLCipherSuite((String)fields.get("sslCipherSuite", (Object)null));
/*      */       }
/* 3048 */       if (!fields.defaulted("sslFipsRequired")) {
/* 3049 */         setSSLFipsRequired(fields.get("sslFipsRequired", false));
/*      */       }
/* 3051 */       if (!fields.defaulted("sslPeerName")) {
/* 3052 */         setSSLPeerName((String)fields.get("sslPeerName", (Object)null));
/*      */       }
/* 3054 */       if (!fields.defaulted("sslResetCount")) {
/* 3055 */         setSSLResetCount(fields.get("sslResetCount", 0));
/*      */       }
/* 3057 */       if (!fields.defaulted("sslSocketFactory")) {
/* 3058 */         setSSLSocketFactory(fields.get("sslSocketFactory", (Object)null));
/*      */       }
/* 3060 */       if (!fields.defaulted("syncpointAllGets")) {
/* 3061 */         setSyncpointAllGets(fields.get("syncpointAllGets", false));
/*      */       }
/* 3063 */       if (!fields.defaulted("targetClientMatching")) {
/* 3064 */         setTargetClientMatching(fields.get("targetClientMatching", false));
/*      */       }
/* 3066 */       if (!fields.defaulted("transportType")) {
/* 3067 */         setTransportType(fields.get("transportType", 0));
/*      */       }
/* 3069 */       if (!fields.defaulted("useConnectionPooling")) {
/* 3070 */         setUseConnectionPooling(fields.get("useConnectionPooling", false));
/*      */       }
/* 3072 */       if (!fields.defaulted("version")) {
/*      */         
/* 3074 */         this.versionChangeAllowed = true;
/* 3075 */         setVersion(fields.get("version", 0));
/* 3076 */         this.versionChangeAllowed = false;
/*      */       } 
/* 3078 */       if (!fields.defaulted("wildcardFormat")) {
/* 3079 */         setWildcardFormat(fields.get("wildcardFormat", 0));
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3085 */       if (!(this instanceof MQTopicConnectionFactory)) {
/* 3086 */         if (!fields.defaulted("messageRetention")) {
/* 3087 */           setMessageRetention(fields.get("messageRetention", 0));
/*      */         }
/* 3089 */         if (!fields.defaulted("temporaryModel")) {
/* 3090 */           setTemporaryModel((String)fields.get("temporaryModel", (Object)null));
/*      */         }
/* 3092 */         if (!fields.defaulted("tempTopicPrefix")) {
/* 3093 */           setTempTopicPrefix((String)fields.get("tempTopicPrefix", (Object)null));
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3100 */       if (!(this instanceof MQQueueConnectionFactory)) {
/* 3101 */         if (!fields.defaulted("brokerCCSubQueue")) {
/* 3102 */           setBrokerCCSubQueue((String)fields.get("brokerCCSubQueue", (Object)null));
/*      */         }
/* 3104 */         if (!fields.defaulted("brokerControlQueue")) {
/* 3105 */           setBrokerControlQueue((String)fields.get("brokerControlQueue", (Object)null));
/*      */         }
/* 3107 */         if (!fields.defaulted("brokerPubQueue")) {
/* 3108 */           setBrokerPubQueue((String)fields.get("brokerPubQueue", (Object)null));
/*      */         }
/* 3110 */         if (!fields.defaulted("brokerQueueManager")) {
/* 3111 */           setBrokerQueueManager((String)fields.get("brokerQueueManager", (Object)null));
/*      */         }
/* 3113 */         if (!fields.defaulted("brokerSubQueue")) {
/* 3114 */           setBrokerSubQueue((String)fields.get("brokerSubQueue", (Object)null));
/*      */         }
/* 3116 */         if (!fields.defaulted("brokerVersion")) {
/* 3117 */           setBrokerVersion(fields.get("brokerVersion", 0));
/*      */         }
/* 3119 */         if (!fields.defaulted("brokerVersionUnspecified")) {
/* 3120 */           boolean bverUnspecified = fields.get("brokerVersionUnspecified", false);
/*      */           
/* 3122 */           if (bverUnspecified == true) {
/* 3123 */             setBrokerVersion(-1);
/*      */           }
/*      */         } 
/* 3126 */         if (!fields.defaulted("cleanupInterval")) {
/* 3127 */           setCleanupInterval(fields.get("cleanupInterval", 0L));
/*      */         }
/* 3129 */         if (!fields.defaulted("cleanupLevel")) {
/* 3130 */           setCleanupLevel(fields.get("cleanupLevel", 0));
/*      */         }
/* 3132 */         if (!fields.defaulted("cloneSupport")) {
/* 3133 */           setCloneSupport(fields.get("cloneSupport", 0));
/*      */         }
/* 3135 */         if (!fields.defaulted("directAuth")) {
/* 3136 */           setDirectAuth(fields.get("directAuth", 0));
/*      */         }
/* 3138 */         if (!fields.defaulted("messageSelection")) {
/* 3139 */           setMessageSelection(fields.get("messageSelection", 0));
/*      */         }
/* 3141 */         if (!fields.defaulted("multicast")) {
/* 3142 */           setMulticast(fields.get("multicast", 0));
/*      */         }
/* 3144 */         if (!fields.defaulted("proxyHostName")) {
/* 3145 */           setProxyHostName((String)fields.get("proxyHostName", (Object)null));
/*      */         }
/* 3147 */         if (!fields.defaulted("proxyPort")) {
/* 3148 */           setProxyPort(fields.get("proxyPort", 0));
/*      */         }
/* 3150 */         if (!fields.defaulted("pubAckInterval")) {
/* 3151 */           setPubAckInterval(fields.get("pubAckInterval", 0));
/*      */         }
/* 3153 */         if (!fields.defaulted("sparseSubscriptions")) {
/* 3154 */           setSparseSubscriptions(fields.get("sparseSubscriptions", false));
/*      */         }
/* 3156 */         if (!fields.defaulted("statusRefreshInterval")) {
/* 3157 */           setStatusRefreshInterval(fields.get("statusRefreshInterval", 0));
/*      */         }
/* 3159 */         if (!fields.defaulted("subscriptionStore")) {
/* 3160 */           setSubscriptionStore(fields.get("subscriptionStore", 0));
/*      */         }
/* 3162 */         if (!fields.defaulted("tempQPrefix")) {
/* 3163 */           setTempQPrefix((String)fields.get("tempQPrefix", (Object)null));
/*      */         }
/*      */       }
/*      */     
/* 3167 */     } catch (JMSException je) {
/* 3168 */       if (Trace.isOn) {
/* 3169 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "readObject(java.io.ObjectInputStream)", (Throwable)je, 2);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3176 */     if (Trace.isOn) {
/* 3177 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "readObject(java.io.ObjectInputStream)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void initialiseMQConnectionFactory() {
/* 3185 */     if (Trace.isOn) {
/* 3186 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "initialiseMQConnectionFactory()");
/*      */     }
/*      */     
/*      */     try {
/* 3190 */       setStringProperty("XMSC_CONNECTION_TYPE_NAME", this.connectionType);
/* 3191 */       setIntProperty("XMSC_CONNECTION_TYPE", 1);
/*      */     }
/* 3193 */     catch (JMSException e) {
/* 3194 */       if (Trace.isOn) {
/* 3195 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "initialiseMQConnectionFactory()", (Throwable)e, 1);
/*      */       }
/*      */       
/* 3198 */       Trace.ffst(this, "readObject()", "XF001002", null, null);
/*      */     } 
/*      */     
/*      */     try {
/* 3202 */       JmsFactoryFactory factory = JmsFactoryFactory.getInstance(this.connectionType);
/* 3203 */       this.capabilities = factory.getCapabilities();
/*      */       
/* 3205 */       setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)20);
/*      */     }
/* 3207 */     catch (JMSException je) {
/* 3208 */       if (Trace.isOn) {
/* 3209 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "initialiseMQConnectionFactory()", (Throwable)je, 2);
/*      */       }
/*      */       
/* 3212 */       Trace.ffst(this, "readObject()", "XF001003", null, null);
/*      */     } 
/*      */     
/* 3215 */     if (Trace.isOn) {
/* 3216 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "initialiseMQConnectionFactory()");
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
/*      */   public void setBrokerCCSubQueue(String queueName) throws JMSException {
/* 3229 */     if (Trace.isOn) {
/* 3230 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setBrokerCCSubQueue(String)", "setter", queueName);
/*      */     }
/*      */     
/* 3233 */     setStringProperty("XMSC_WMQ_BROKER_CC_SUBQ", queueName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBrokerControlQueue(String queueName) throws JMSException {
/* 3244 */     if (Trace.isOn) {
/* 3245 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setBrokerControlQueue(String)", "setter", queueName);
/*      */     }
/*      */     
/* 3248 */     setStringProperty("XMSC_WMQ_BROKER_CONTROLQ", queueName);
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
/*      */   public void setBrokerPubQueue(String queueName) throws JMSException {
/* 3260 */     if (Trace.isOn) {
/* 3261 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setBrokerPubQueue(String)", "setter", queueName);
/*      */     }
/*      */     
/* 3264 */     setStringProperty("XMSC_WMQ_BROKER_PUBQ", queueName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBrokerQueueManager(String queueManagerName) throws JMSException {
/* 3275 */     if (Trace.isOn) {
/* 3276 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setBrokerQueueManager(String)", "setter", queueManagerName);
/*      */     }
/*      */     
/* 3279 */     setStringProperty("XMSC_WMQ_BROKER_QMGR", queueManagerName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBrokerSubQueue(String queueName) throws JMSException {
/* 3290 */     if (Trace.isOn) {
/* 3291 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setBrokerSubQueue(String)", "setter", queueName);
/*      */     }
/*      */     
/* 3294 */     setStringProperty("XMSC_WMQ_BROKER_SUBQ", queueName);
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
/*      */   public void setBrokerVersion(int version) throws JMSException {
/* 3310 */     if (Trace.isOn) {
/* 3311 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setBrokerVersion(int)", "setter", 
/* 3312 */           Integer.valueOf(version));
/*      */     }
/* 3314 */     setIntProperty("brokerVersion", version);
/*      */ 
/*      */ 
/*      */     
/* 3318 */     if (!this.mselSet) {
/* 3319 */       if (version == 0 || version == -1) {
/* 3320 */         setMessageSelection(0);
/*      */       }
/* 3322 */       else if (version == 1) {
/* 3323 */         setMessageSelection(1);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3328 */       this.mselSet = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCCDTURL(URL url) {
/* 3339 */     if (Trace.isOn) {
/* 3340 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setCCDTURL(URL)", "setter", url);
/*      */     }
/*      */     
/*      */     try {
/* 3344 */       setStringProperty("XMSC_WMQ_CCDTURL", (url == null) ? null : url.toString());
/*      */     }
/* 3346 */     catch (JMSException je) {
/* 3347 */       if (Trace.isOn) {
/* 3348 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setCCDTURL(URL)", (Throwable)je);
/*      */       }
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
/*      */   public void setCCSID(int ccsid) throws JMSException {
/* 3365 */     if (Trace.isOn) {
/* 3366 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setCCSID(int)", "setter", 
/* 3367 */           Integer.valueOf(ccsid));
/*      */     }
/*      */     
/* 3370 */     setIntProperty("XMSC_WMQ_QMGR_CCSID", ccsid);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setChannel(String channelName) throws JMSException {
/* 3381 */     if (Trace.isOn) {
/* 3382 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setChannel(String)", "setter", channelName);
/*      */     }
/*      */     
/* 3385 */     setStringProperty("XMSC_WMQ_CHANNEL", channelName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCleanupInterval(long interval) throws JMSException {
/* 3396 */     if (Trace.isOn) {
/* 3397 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setCleanupInterval(long)", "setter", 
/* 3398 */           Long.valueOf(interval));
/*      */     }
/* 3400 */     setLongProperty("XMSC_WMQ_CLEANUP_INTERVAL", interval);
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
/*      */   public void setCleanupLevel(int level) throws JMSException {
/* 3425 */     if (Trace.isOn) {
/* 3426 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setCleanupLevel(int)", "setter", 
/* 3427 */           Integer.valueOf(level));
/*      */     }
/* 3429 */     setIntProperty("XMSC_WMQ_CLEANUP_LEVEL", level);
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
/*      */   public void setClientID(String id) {
/* 3442 */     if (Trace.isOn) {
/* 3443 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setClientID(String)", "setter", id);
/*      */     }
/*      */     try {
/* 3446 */       setStringProperty("XMSC_CLIENT_ID", id);
/*      */     }
/* 3448 */     catch (JMSException je) {
/* 3449 */       if (Trace.isOn) {
/* 3450 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setClientID(String)", (Throwable)je);
/*      */       }
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
/*      */   @Deprecated
/*      */   public void setClientId(String id) {
/* 3464 */     if (Trace.isOn) {
/* 3465 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setClientId(String)", "setter", id);
/*      */     }
/*      */     try {
/* 3468 */       setStringProperty("XMSC_CLIENT_ID", id);
/*      */     }
/* 3470 */     catch (JMSException je) {
/* 3471 */       if (Trace.isOn) {
/* 3472 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setClientId(String)", (Throwable)je);
/*      */       }
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
/*      */   @Deprecated
/*      */   public void setCloneSupport(int type) throws JMSException {
/* 3493 */     if (Trace.isOn) {
/* 3494 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setCloneSupport(int)", "setter", 
/* 3495 */           Integer.valueOf(type));
/*      */     }
/* 3497 */     setIntProperty("XMSC_WMQ_CLONE_SUPPORT", type);
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
/*      */   void setCommonFromReference(Reference ref) throws JMSException {
/* 3509 */     if (Trace.isOn) {
/* 3510 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setCommonFromReference(Reference)", "setter", ref);
/*      */     }
/*      */ 
/*      */     
/* 3514 */     Object value = null;
/*      */ 
/*      */     
/*      */     try {
/* 3518 */       RefAddr addr = ref.get("VER");
/* 3519 */       if (addr != null) {
/* 3520 */         value = addr.getContent();
/* 3521 */         if (value != null) {
/* 3522 */           Integer.parseInt((String)value);
/*      */         }
/*      */       } 
/* 3525 */       addr = ref.get("APPNAME");
/* 3526 */       if (addr != null) {
/* 3527 */         value = addr.getContent();
/* 3528 */         if (value != null) {
/* 3529 */           setAppName((String)value);
/*      */         }
/*      */       } 
/* 3532 */       addr = ref.get("DESC");
/* 3533 */       if (addr != null) {
/* 3534 */         value = addr.getContent();
/* 3535 */         if (value != null) {
/* 3536 */           setDescription((String)value);
/*      */         }
/*      */       } 
/* 3539 */       addr = ref.get("TRAN");
/* 3540 */       if (addr != null) {
/* 3541 */         value = addr.getContent();
/* 3542 */         if (value != null) {
/* 3543 */           setTransportType(Integer.parseInt((String)value));
/*      */         }
/*      */       } 
/* 3546 */       addr = ref.get("CID");
/* 3547 */       if (addr != null) {
/* 3548 */         value = addr.getContent();
/* 3549 */         if (value != null) {
/* 3550 */           setClientId((String)value);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3560 */       addr = ref.get("QMGR");
/* 3561 */       if (addr != null) {
/* 3562 */         String qmgr = (String)addr.getContent();
/* 3563 */         if (qmgr == null) {
/* 3564 */           qmgr = "";
/*      */         }
/* 3566 */         setQueueManager(qmgr);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3579 */       boolean connectionNameListSet = false;
/* 3580 */       addr = ref.get("CRSHOSTS");
/* 3581 */       if (addr != null) {
/* 3582 */         value = addr.getContent();
/* 3583 */         if (value != null && !value.equals("")) {
/* 3584 */           setConnectionNameListInternal((String)value);
/* 3585 */           connectionNameListSet = true;
/*      */         } 
/*      */       } 
/* 3588 */       if (!connectionNameListSet) {
/* 3589 */         addr = ref.get("CNLIST");
/* 3590 */         if (addr != null) {
/* 3591 */           value = addr.getContent();
/* 3592 */           if (value != null && !value.equals("")) {
/* 3593 */             setConnectionNameListInternal((String)value);
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 3599 */       boolean hostIsDefault = true;
/* 3600 */       boolean portIsDefault = true;
/*      */       
/* 3602 */       addr = ref.get("HOST");
/* 3603 */       if (addr != null) {
/* 3604 */         value = addr.getContent();
/* 3605 */         if (value != null && !((String)value).equalsIgnoreCase("localhost")) {
/* 3606 */           hostIsDefault = false;
/*      */         }
/*      */       } 
/* 3609 */       addr = ref.get("PORT");
/* 3610 */       String port = null;
/* 3611 */       if (addr != null) {
/* 3612 */         port = (String)addr.getContent();
/* 3613 */         if (port != null && !port.equalsIgnoreCase("1414") && !port.equalsIgnoreCase("1506")) {
/* 3614 */           portIsDefault = false;
/*      */         }
/*      */       } 
/*      */       
/* 3618 */       if (!hostIsDefault || !portIsDefault) {
/* 3619 */         setHostName((String)value);
/* 3620 */         if (port != null) {
/* 3621 */           setPort(Integer.parseInt(port));
/*      */         }
/*      */       } 
/*      */       
/* 3625 */       addr = ref.get("CHAN");
/* 3626 */       if (addr != null) {
/* 3627 */         value = addr.getContent();
/* 3628 */         if (value != null) {
/* 3629 */           setChannel((String)value);
/*      */         }
/*      */       } 
/* 3632 */       addr = ref.get("CCS");
/* 3633 */       if (addr != null) {
/* 3634 */         value = addr.getContent();
/* 3635 */         if (value != null) {
/* 3636 */           setCCSID(Integer.parseInt((String)value));
/*      */         }
/*      */       } 
/* 3639 */       addr = ref.get("MNS");
/* 3640 */       if (addr != null) {
/* 3641 */         value = addr.getContent();
/* 3642 */         if (value != null) {
/* 3643 */           setMapNameStyle(Boolean.valueOf((String)value).booleanValue());
/*      */         }
/*      */       } 
/* 3646 */       addr = ref.get("PVER");
/* 3647 */       if (addr != null) {
/* 3648 */         value = addr.getContent();
/* 3649 */         if (value != null) {
/* 3650 */           setProviderVersion((String)value);
/*      */         }
/*      */       } 
/* 3653 */       addr = ref.get("RCX");
/* 3654 */       if (addr != null) {
/* 3655 */         value = addr.getContent();
/* 3656 */         if (value != null) {
/* 3657 */           setReceiveExit((String)value);
/*      */         }
/*      */       } 
/* 3660 */       addr = ref.get("RCXI");
/* 3661 */       if (addr != null) {
/* 3662 */         value = addr.getContent();
/* 3663 */         if (value != null) {
/* 3664 */           setReceiveExitInit((String)value);
/*      */         }
/*      */       } 
/* 3667 */       addr = ref.get("SCX");
/* 3668 */       if (addr != null) {
/* 3669 */         value = addr.getContent();
/* 3670 */         if (value != null) {
/* 3671 */           setSecurityExit((String)value);
/*      */         }
/*      */       } 
/* 3674 */       addr = ref.get("SCXI");
/* 3675 */       if (addr != null) {
/* 3676 */         value = addr.getContent();
/* 3677 */         if (value != null) {
/* 3678 */           setSecurityExitInit((String)value);
/*      */         }
/*      */       } 
/* 3681 */       addr = ref.get("SDX");
/* 3682 */       if (addr != null) {
/* 3683 */         value = addr.getContent();
/* 3684 */         if (value != null) {
/* 3685 */           setSendExit((String)value);
/*      */         }
/*      */       } 
/* 3688 */       addr = ref.get("SCC");
/* 3689 */       if (addr != null) {
/* 3690 */         value = addr.getContent();
/* 3691 */         if (value != null) {
/* 3692 */           setSendCheckCount(Integer.parseInt((String)value));
/*      */         }
/*      */       } 
/* 3695 */       addr = ref.get("SDXI");
/* 3696 */       if (addr != null) {
/* 3697 */         value = addr.getContent();
/* 3698 */         if (value != null) {
/* 3699 */           setSendExitInit((String)value);
/*      */         }
/*      */       } 
/* 3702 */       addr = ref.get("SCALD");
/* 3703 */       if (addr != null) {
/* 3704 */         value = addr.getContent();
/* 3705 */         if (value != null) {
/* 3706 */           setShareConvAllowed(Integer.parseInt((String)value));
/*      */         }
/*      */       } 
/* 3709 */       addr = ref.get("SCPHS");
/* 3710 */       if (addr != null) {
/* 3711 */         value = addr.getContent();
/* 3712 */         if (value != null) {
/* 3713 */           setSSLCipherSuite((String)value);
/*      */         }
/*      */       } 
/* 3716 */       addr = ref.get("SPEER");
/* 3717 */       if (addr != null) {
/* 3718 */         value = addr.getContent();
/* 3719 */         if (value != null) {
/* 3720 */           setSSLPeerName((String)value);
/*      */         }
/*      */       } 
/* 3723 */       addr = ref.get("SCRL");
/* 3724 */       if (addr != null) {
/* 3725 */         value = addr.getContent();
/* 3726 */         if (value != null) {
/* 3727 */           setSSLCertStores((String)value);
/*      */         }
/*      */       } 
/* 3730 */       addr = ref.get("SRC");
/* 3731 */       if (addr != null) {
/* 3732 */         value = addr.getContent();
/* 3733 */         if (value != null) {
/* 3734 */           setSSLResetCount(Integer.parseInt((String)value));
/*      */         }
/*      */       } 
/* 3737 */       addr = ref.get("SFIPS");
/* 3738 */       if (addr != null) {
/* 3739 */         value = addr.getContent();
/* 3740 */         if (value != null) {
/* 3741 */           setSSLFipsRequired(Boolean.valueOf((String)value).booleanValue());
/*      */         }
/*      */       } 
/* 3744 */       addr = ref.get("HC");
/* 3745 */       if (addr != null) {
/* 3746 */         value = addr.getContent();
/* 3747 */         if (value != null) {
/* 3748 */           setHdrCompList((String)value);
/*      */         }
/*      */       } 
/* 3751 */       addr = ref.get("MC");
/* 3752 */       if (addr != null) {
/* 3753 */         value = addr.getContent();
/* 3754 */         if (value != null) {
/* 3755 */           setMsgCompList((String)value);
/*      */         }
/*      */       } 
/* 3758 */       addr = ref.get("WCFMT");
/* 3759 */       if (addr != null) {
/* 3760 */         value = addr.getContent();
/* 3761 */         if (value != null) {
/* 3762 */           setWildcardFormat(Integer.parseInt((String)value));
/*      */         }
/*      */       } 
/*      */       
/* 3766 */       addr = ref.get("CT");
/* 3767 */       if (addr != null) {
/* 3768 */         value = addr.getContent();
/* 3769 */         if (value != null) {
/* 3770 */           setConnTag(((String)value).getBytes(Charset.defaultCharset()));
/*      */         }
/*      */       } 
/* 3773 */       addr = ref.get("CTO");
/* 3774 */       if (addr != null) {
/* 3775 */         value = addr.getContent();
/* 3776 */         if (value != null) {
/* 3777 */           int i = Integer.parseInt((String)value);
/* 3778 */           if (i != 0) {
/* 3779 */             setMQConnectionOptions(i);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 3784 */       addr = ref.get("TCM");
/* 3785 */       if (addr != null) {
/* 3786 */         value = addr.getContent();
/* 3787 */         if (value != null) {
/* 3788 */           setTargetClientMatching(Boolean.valueOf((String)value).booleanValue());
/*      */         }
/*      */       } 
/*      */       
/* 3792 */       addr = ref.get("SPAG");
/* 3793 */       if (addr != null) {
/* 3794 */         value = addr.getContent();
/* 3795 */         if (value != null) {
/* 3796 */           setSyncpointAllGets(Boolean.valueOf((String)value).booleanValue());
/*      */         }
/*      */       } 
/*      */       
/* 3800 */       addr = ref.get("UCP");
/* 3801 */       if (addr != null) {
/* 3802 */         value = addr.getContent();
/* 3803 */         if (value != null) {
/* 3804 */           setUseConnectionPooling(Boolean.valueOf((String)value).booleanValue());
/*      */         }
/*      */       } 
/*      */       
/* 3808 */       addr = ref.get("PINT");
/* 3809 */       if (addr != null) {
/* 3810 */         value = addr.getContent();
/* 3811 */         if (value != null) {
/* 3812 */           setPollingInterval(Integer.parseInt((String)value));
/*      */         }
/*      */       } 
/*      */       
/* 3816 */       addr = ref.get("AEX");
/* 3817 */       if (addr != null) {
/* 3818 */         value = addr.getContent();
/* 3819 */         if (value != null) {
/* 3820 */           setAsyncExceptions(Integer.parseInt((String)value));
/*      */         }
/*      */       } 
/*      */       
/* 3824 */       addr = ref.get("RINT");
/* 3825 */       if (addr != null) {
/* 3826 */         value = addr.getContent();
/* 3827 */         if (value != null) {
/* 3828 */           setRescanInterval(Integer.parseInt((String)value));
/*      */         }
/*      */       } 
/*      */       
/* 3832 */       addr = ref.get("MBS");
/* 3833 */       if (addr != null) {
/* 3834 */         value = addr.getContent();
/* 3835 */         if (value != null) {
/* 3836 */           setMsgBatchSize(Integer.parseInt((String)value));
/*      */         }
/*      */       } 
/*      */       
/* 3840 */       addr = ref.get("FIQ");
/* 3841 */       if (addr != null) {
/* 3842 */         value = addr.getContent();
/* 3843 */         if (value != null) {
/* 3844 */           setFailIfQuiesce(Integer.parseInt((String)value));
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3853 */       addr = ref.get("LA");
/* 3854 */       if (addr != null) {
/* 3855 */         String localadd = (String)addr.getContent();
/* 3856 */         if (localadd == null) {
/* 3857 */           localadd = "";
/*      */         }
/* 3859 */         setLocalAddress(localadd);
/*      */       } 
/*      */ 
/*      */       
/* 3863 */       addr = ref.get("RCVISOL");
/* 3864 */       if (addr != null) {
/* 3865 */         value = addr.getContent();
/* 3866 */         if (value != null) {
/* 3867 */           setReceiveIsolation(Integer.parseInt((String)value));
/*      */         }
/*      */       } 
/*      */       
/* 3871 */       addr = ref.get("NOTIFY");
/* 3872 */       if (addr != null) {
/* 3873 */         value = addr.getContent();
/* 3874 */         if (value != null) {
/* 3875 */           setOutcomeNotification(Boolean.valueOf((String)value).booleanValue());
/*      */         }
/*      */       } 
/*      */       
/* 3879 */       addr = ref.get("PROCDUR");
/* 3880 */       if (addr != null) {
/* 3881 */         value = addr.getContent();
/* 3882 */         if (value != null) {
/* 3883 */           setProcessDuration(Integer.parseInt((String)value));
/*      */         }
/*      */       } 
/*      */       
/* 3887 */       addr = ref.get("OPTPUB");
/* 3888 */       if (addr != null) {
/* 3889 */         value = addr.getContent();
/* 3890 */         if (value != null) {
/* 3891 */           setOptimisticPublication(Boolean.valueOf((String)value).booleanValue());
/*      */         }
/*      */       } 
/*      */       
/* 3895 */       addr = ref.get("CROPT");
/* 3896 */       if (addr != null) {
/* 3897 */         value = addr.getContent();
/* 3898 */         if (value != null) {
/* 3899 */           setClientReconnectOptions(Integer.parseInt((String)value));
/*      */         }
/*      */       } 
/*      */       
/* 3903 */       addr = ref.get("CRT");
/* 3904 */       if (addr != null) {
/* 3905 */         value = addr.getContent();
/* 3906 */         if (value != null) {
/* 3907 */           setClientReconnectTimeout(Integer.parseInt((String)value));
/*      */         }
/*      */       } 
/*      */       
/* 3911 */       addr = ref.get("CCDTURL");
/* 3912 */       if (addr != null) {
/*      */         try {
/* 3914 */           String content = addr.getContent().toString();
/* 3915 */           if (content == null || content.equals("null")) {
/*      */             
/* 3917 */             setCCDTURL((URL)null);
/*      */           } else {
/*      */             
/* 3920 */             setCCDTURL(new URL(content));
/*      */           }
/*      */         
/* 3923 */         } catch (MalformedURLException mue) {
/* 3924 */           if (Trace.isOn) {
/* 3925 */             Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setCommonFromReference(Reference)", mue, 1);
/*      */           }
/*      */           
/* 3928 */           HashMap<String, Object> inserts = new HashMap<>();
/* 3929 */           inserts.put("XMSC_INSERT_PROPERTY", "XMSC_WMQ_CCDTURL");
/* 3930 */           inserts.put("XMSC_INSERT_VALUE", addr.getContent());
/* 3931 */           JMSException je = (JMSException)NLSServices.createException("JMSMQ1006", inserts);
/* 3932 */           je.setLinkedException(mue);
/* 3933 */           if (Trace.isOn) {
/* 3934 */             Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "setCommonFromReference(Reference)", (Throwable)je, 1);
/*      */           }
/*      */           
/* 3937 */           throw je;
/*      */         }
/*      */       
/*      */       }
/* 3941 */     } catch (JMSException e) {
/* 3942 */       if (Trace.isOn) {
/* 3943 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setCommonFromReference(Reference)", (Throwable)e, 2);
/*      */       }
/*      */       
/* 3946 */       if (Trace.isOn) {
/* 3947 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "setCommonFromReference(Reference)", (Throwable)e, 2);
/*      */       }
/*      */       
/* 3950 */       throw e;
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
/*      */   public void setConnTag(byte[] cTag) {
/* 3965 */     if (Trace.isOn) {
/* 3966 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setConnTag(byte [ ])", "setter", cTag);
/*      */     }
/*      */     
/*      */     try {
/* 3970 */       setBytesProperty("XMSC_WMQ_CONNECTION_TAG", cTag);
/*      */     }
/* 3972 */     catch (JMSException je) {
/* 3973 */       if (Trace.isOn) {
/* 3974 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setConnTag(byte [ ])", (Throwable)je);
/*      */       }
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
/*      */   public void setDescription(String desc) {
/* 3987 */     if (Trace.isOn) {
/* 3988 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setDescription(String)", "setter", desc);
/*      */     }
/*      */     
/*      */     try {
/* 3992 */       setStringProperty("XMSC_WMQ_CF_DESCRIPTION", desc);
/*      */     }
/* 3994 */     catch (JMSException je) {
/* 3995 */       if (Trace.isOn) {
/* 3996 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setDescription(String)", (Throwable)je);
/*      */       }
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
/*      */   @Deprecated
/*      */   public void setDirectAuth(int authority) throws JMSException {
/* 4011 */     if (Trace.isOn) {
/* 4012 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setDirectAuth(int)", "setter", 
/* 4013 */           Integer.valueOf(authority));
/*      */     }
/* 4015 */     setIntProperty("XMSC_RTT_DIRECT_AUTH", authority);
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
/*      */   public void setFailIfQuiesce(int fiq) throws JMSException {
/* 4030 */     if (Trace.isOn) {
/* 4031 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setFailIfQuiesce(int)", "setter", 
/* 4032 */           Integer.valueOf(fiq));
/*      */     }
/* 4034 */     setIntProperty("failIfQuiesce", fiq);
/*      */   }
/*      */ 
/*      */   
/*      */   void setFromReference(Reference ref) throws JMSException {
/* 4039 */     if (Trace.isOn) {
/* 4040 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setFromReference(Reference)", "setter", ref);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4047 */     boolean originalDefaults = this.settingDefaults;
/*      */     
/*      */     try {
/* 4050 */       this.settingDefaults = true;
/*      */ 
/*      */       
/* 4053 */       setCommonFromReference(ref);
/*      */ 
/*      */       
/* 4056 */       if (!(this instanceof MQTopicConnectionFactory)) {
/* 4057 */         RefAddr addr = ref.get("TM");
/* 4058 */         if (addr != null) {
/* 4059 */           Object value = addr.getContent();
/* 4060 */           if (value != null) {
/* 4061 */             setTemporaryModel((String)value);
/*      */           }
/*      */         } 
/*      */         
/* 4065 */         addr = ref.get("TQPFX");
/* 4066 */         if (addr != null) {
/* 4067 */           Object value = addr.getContent();
/* 4068 */           if (value != null) {
/* 4069 */             setTempQPrefix((String)value);
/*      */           }
/*      */         } 
/*      */         
/* 4073 */         addr = ref.get("MRET");
/* 4074 */         if (addr != null) {
/* 4075 */           Object value = addr.getContent();
/* 4076 */           if (value != null) {
/* 4077 */             setMessageRetention(Integer.parseInt((String)value));
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 4083 */       if (!(this instanceof MQQueueConnectionFactory)) {
/* 4084 */         RefAddr addr = ref.get("BCON");
/* 4085 */         if (addr != null) {
/* 4086 */           Object value = addr.getContent();
/* 4087 */           if (value != null) {
/* 4088 */             setBrokerControlQueue((String)value);
/*      */           }
/*      */         } 
/*      */         
/* 4092 */         addr = ref.get("BPUB");
/* 4093 */         if (addr != null) {
/* 4094 */           Object value = addr.getContent();
/* 4095 */           if (value != null) {
/* 4096 */             setBrokerPubQueue((String)value);
/*      */           }
/*      */         } 
/*      */         
/* 4100 */         addr = ref.get("BSUB");
/* 4101 */         if (addr != null) {
/* 4102 */           Object value = addr.getContent();
/* 4103 */           if (value != null) {
/* 4104 */             setBrokerSubQueue((String)value);
/*      */           }
/*      */         } 
/*      */         
/* 4108 */         addr = ref.get("CCSUB");
/* 4109 */         if (addr != null) {
/* 4110 */           Object value = addr.getContent();
/* 4111 */           if (value != null) {
/* 4112 */             setBrokerCCSubQueue((String)value);
/*      */           }
/*      */         } 
/*      */         
/* 4116 */         addr = ref.get("BQM");
/* 4117 */         if (addr != null) {
/* 4118 */           String bqmgr = (String)addr.getContent();
/* 4119 */           if (bqmgr == null) {
/* 4120 */             bqmgr = "";
/*      */           }
/* 4122 */           setBrokerQueueManager(bqmgr);
/*      */         } 
/*      */         
/* 4125 */         addr = ref.get("PHOST");
/* 4126 */         if (addr != null) {
/* 4127 */           Object value = addr.getContent();
/* 4128 */           if (value != null) {
/* 4129 */             setProxyHostName((String)value);
/*      */           }
/*      */         } 
/*      */         
/* 4133 */         addr = ref.get("MSEL");
/* 4134 */         if (addr != null) {
/* 4135 */           Object value = addr.getContent();
/* 4136 */           if (value != null) {
/* 4137 */             setMessageSelection(Integer.parseInt((String)value));
/*      */           }
/*      */         } 
/*      */         
/* 4141 */         addr = ref.get("PAI");
/* 4142 */         if (addr != null) {
/* 4143 */           Object value = addr.getContent();
/* 4144 */           if (value != null) {
/* 4145 */             setPubAckInterval(Integer.parseInt((String)value));
/*      */           }
/*      */         } 
/*      */         
/* 4149 */         addr = ref.get("SRI");
/* 4150 */         if (addr != null) {
/* 4151 */           Object value = addr.getContent();
/* 4152 */           if (value != null) {
/* 4153 */             setStatusRefreshInterval(Integer.parseInt((String)value));
/*      */           }
/*      */         } 
/*      */         
/* 4157 */         addr = ref.get("SUBST");
/* 4158 */         if (addr != null) {
/* 4159 */           Object value = addr.getContent();
/* 4160 */           if (value != null) {
/* 4161 */             setSubscriptionStore(Integer.parseInt((String)value));
/*      */           }
/*      */         } 
/*      */         
/* 4165 */         addr = ref.get("TTP");
/* 4166 */         if (addr != null) {
/* 4167 */           Object value = addr.getContent();
/* 4168 */           if (value != null) {
/* 4169 */             setTempTopicPrefix((String)value);
/*      */           }
/*      */         } 
/*      */         
/* 4173 */         addr = ref.get("CL");
/* 4174 */         if (addr != null) {
/* 4175 */           Object value = addr.getContent();
/* 4176 */           if (value != null) {
/* 4177 */             setCleanupLevel(Integer.parseInt((String)value));
/*      */           }
/*      */         } 
/*      */         
/* 4181 */         addr = ref.get("CLINT");
/* 4182 */         if (addr != null) {
/* 4183 */           Object value = addr.getContent();
/* 4184 */           if (value != null) {
/* 4185 */             setCleanupInterval(Long.parseLong((String)value));
/*      */           }
/*      */         } 
/*      */         
/* 4189 */         addr = ref.get("CLS");
/* 4190 */         if (addr != null) {
/* 4191 */           Object value = addr.getContent();
/* 4192 */           if (value != null) {
/* 4193 */             setCloneSupport(Integer.parseInt((String)value));
/*      */           }
/*      */         } 
/*      */         
/* 4197 */         addr = ref.get("SSUBS");
/* 4198 */         if (addr != null) {
/* 4199 */           Object value = addr.getContent();
/* 4200 */           if (value != null) {
/* 4201 */             setSparseSubscriptions(Boolean.valueOf((String)value).booleanValue());
/*      */           }
/*      */         } 
/*      */         
/* 4205 */         addr = ref.get("MCAST");
/* 4206 */         if (addr != null) {
/* 4207 */           Object value = addr.getContent();
/* 4208 */           if (value != null) {
/* 4209 */             setMulticast(Integer.parseInt((String)value));
/*      */           }
/*      */         } 
/*      */         
/* 4213 */         addr = ref.get("PPORT");
/* 4214 */         if (addr != null) {
/* 4215 */           Object value = addr.getContent();
/* 4216 */           if (value != null) {
/* 4217 */             setProxyPort(Integer.parseInt((String)value));
/*      */           }
/*      */         } 
/*      */         
/* 4221 */         addr = ref.get("DAUTH");
/* 4222 */         if (addr != null) {
/* 4223 */           Object value = addr.getContent();
/* 4224 */           if (value != null) {
/* 4225 */             setDirectAuth(Integer.parseInt((String)value));
/*      */           }
/*      */         } 
/*      */         
/* 4229 */         addr = ref.get("MBSZ");
/* 4230 */         if (addr != null) {
/* 4231 */           Object value = addr.getContent();
/* 4232 */           if (value != null) {
/* 4233 */             setMaxBufferSize(Integer.parseInt((String)value));
/*      */           }
/*      */         } 
/*      */         
/* 4237 */         addr = ref.get("RCVISOL");
/* 4238 */         if (addr != null) {
/* 4239 */           Object value = addr.getContent();
/* 4240 */           if (value != null) {
/* 4241 */             setReceiveIsolation(Integer.parseInt((String)value));
/*      */           }
/*      */         } 
/*      */         
/* 4245 */         addr = ref.get("PROCDUR");
/* 4246 */         if (addr != null) {
/* 4247 */           Object value = addr.getContent();
/* 4248 */           if (value != null) {
/* 4249 */             setProcessDuration(Integer.parseInt((String)value));
/*      */           }
/*      */         } 
/*      */         
/* 4253 */         addr = ref.get("NOTIFY");
/* 4254 */         if (addr != null) {
/* 4255 */           Object value = addr.getContent();
/* 4256 */           if (value != null) {
/* 4257 */             setOutcomeNotification(Boolean.valueOf((String)value).booleanValue());
/*      */           }
/*      */         } 
/*      */         
/* 4261 */         addr = ref.get("OPTPUB");
/* 4262 */         if (addr != null) {
/* 4263 */           Object value = addr.getContent();
/* 4264 */           if (value != null) {
/* 4265 */             setOptimisticPublication(Boolean.valueOf((String)value).booleanValue());
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 4270 */         Object brokerVersion = null;
/* 4271 */         Object brokerVersionUnspecified = null;
/*      */ 
/*      */         
/* 4274 */         addr = ref.get("BVER");
/* 4275 */         if (addr != null) {
/* 4276 */           brokerVersion = addr.getContent();
/*      */         }
/* 4278 */         addr = ref.get("BVERU");
/* 4279 */         if (addr != null) {
/* 4280 */           brokerVersionUnspecified = addr.getContent();
/*      */         }
/*      */ 
/*      */         
/* 4284 */         if (brokerVersion != null) {
/* 4285 */           int bVerInt = Integer.parseInt((String)brokerVersion);
/*      */ 
/*      */           
/* 4288 */           if (brokerVersionUnspecified != null) {
/* 4289 */             boolean bVerUnsetBool = Boolean.valueOf((String)brokerVersionUnspecified).booleanValue();
/* 4290 */             if (bVerUnsetBool == true) {
/* 4291 */               setBrokerVersion(-1);
/*      */             } else {
/*      */               
/* 4294 */               setBrokerVersion(bVerInt);
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/* 4299 */             setBrokerVersion(bVerInt);
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } 
/* 4305 */     } catch (JMSException e) {
/* 4306 */       if (Trace.isOn) {
/* 4307 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setFromReference(Reference)", (Throwable)e);
/*      */       }
/*      */       
/* 4310 */       if (Trace.isOn) {
/* 4311 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "setFromReference(Reference)", (Throwable)e);
/*      */       }
/*      */       
/* 4314 */       throw e;
/*      */     } finally {
/*      */       
/* 4317 */       if (Trace.isOn) {
/* 4318 */         Trace.finallyBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setFromReference(Reference)");
/*      */       }
/*      */       
/* 4321 */       this.settingDefaults = originalDefaults;
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
/*      */   public void setHdrCompList(Collection<?> compList) throws JMSException {
/* 4334 */     if (Trace.isOn) {
/* 4335 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setHdrCompList(Collection<?>)", "setter", compList);
/*      */     }
/*      */     
/* 4338 */     if (compList == null || compList.size() == 0) {
/* 4339 */       Vector<Integer> defaultHdrCompList = new Vector<>();
/* 4340 */       defaultHdrCompList.add(Integer.valueOf(0));
/* 4341 */       setObjectProperty("XMSC_WMQ_HEADER_COMP", defaultHdrCompList);
/*      */     } else {
/*      */       
/* 4344 */       setObjectProperty("XMSC_WMQ_HEADER_COMP", compList);
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
/*      */   public void setHdrCompList(String compList) throws JMSException {
/* 4356 */     if (Trace.isOn) {
/* 4357 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setHdrCompList(String)", "setter", compList);
/*      */     }
/*      */ 
/*      */     
/* 4361 */     if (compList != null) {
/*      */       
/* 4363 */       StringTokenizer st = new StringTokenizer(compList);
/* 4364 */       Vector<Object> c = new Vector();
/* 4365 */       while (st.hasMoreTokens()) {
/* 4366 */         String tk = st.nextToken();
/* 4367 */         if (tk.equals("NONE")) {
/* 4368 */           c.add(Integer.valueOf(0)); continue;
/*      */         } 
/* 4370 */         if (tk.equals("SYSTEM")) {
/* 4371 */           c.add(Integer.valueOf(8));
/*      */           
/*      */           continue;
/*      */         } 
/* 4375 */         c.add(tk);
/*      */       } 
/*      */ 
/*      */       
/* 4379 */       setHdrCompList(c);
/*      */     }
/*      */     else {
/*      */       
/* 4383 */       setHdrCompList((Collection)null);
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
/*      */   public void setHostName(String hostname) {
/* 4395 */     if (Trace.isOn) {
/* 4396 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setHostName(String)", "setter", hostname);
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 4401 */       setStringProperty("XMSC_WMQ_HOST_NAME", (hostname != null) ? hostname : "IamANullString");
/*      */     }
/* 4403 */     catch (JMSException je) {
/* 4404 */       if (Trace.isOn) {
/* 4405 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setHostName(String)", (Throwable)je);
/*      */       }
/*      */       
/* 4408 */       HashMap<String, Object> inserts = new HashMap<>();
/* 4409 */       inserts.put("XMSC_INSERT_PROPERTY", "hostname");
/* 4410 */       inserts.put("XMSC_INSERT_VALUE", hostname);
/* 4411 */       Log.log("MQConnectionFactory", "setHostName", "JMSMQ1006", inserts);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLocalAddress(String address) throws JMSException {
/* 4454 */     if (Trace.isOn) {
/* 4455 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setLocalAddress(String)", "setter", address);
/*      */     }
/*      */     
/* 4458 */     setStringProperty("XMSC_WMQ_LOCAL_ADDRESS", address);
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
/*      */   public void setMapNameStyle(boolean style) {
/* 4474 */     if (Trace.isOn) {
/* 4475 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setMapNameStyle(boolean)", "setter", 
/* 4476 */           Boolean.valueOf(style));
/*      */     }
/*      */     try {
/* 4479 */       setBooleanProperty("XMSC_WMQ_MAP_NAME_STYLE", style);
/*      */     }
/* 4481 */     catch (JMSException je) {
/* 4482 */       if (Trace.isOn) {
/* 4483 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setMapNameStyle(boolean)", (Throwable)je);
/*      */       }
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
/*      */   public void setMaxBufferSize(int size) throws JMSException {
/* 4500 */     if (Trace.isOn) {
/* 4501 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setMaxBufferSize(int)", "setter", 
/* 4502 */           Integer.valueOf(size));
/*      */     }
/* 4504 */     setIntProperty("XMSC_WMQ_MAX_BUFFER_SIZE", size);
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
/*      */   public void setMessageRetention(int mRet) throws JMSException {
/* 4519 */     if (Trace.isOn) {
/* 4520 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setMessageRetention(int)", "setter", 
/* 4521 */           Integer.valueOf(mRet));
/*      */     }
/* 4523 */     setIntProperty("XMSC_WMQ_MESSAGE_RETENTION", mRet);
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
/*      */   public void setMessageSelection(int selection) throws JMSException {
/* 4538 */     if (Trace.isOn) {
/* 4539 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setMessageSelection(int)", "setter", 
/* 4540 */           Integer.valueOf(selection));
/*      */     }
/* 4542 */     setIntProperty("XMSC_WMQ_MESSAGE_SELECTION", selection);
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
/*      */   public void setMQConnectionOptions(int cTagOpt) throws JMSException {
/* 4556 */     if (Trace.isOn) {
/* 4557 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setMQConnectionOptions(int)", "setter", 
/* 4558 */           Integer.valueOf(cTagOpt));
/*      */     }
/* 4560 */     setIntProperty("XMSC_WMQ_CONNECT_OPTIONS", cTagOpt);
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
/*      */   public void setMsgBatchSize(int size) throws JMSException {
/* 4572 */     if (Trace.isOn) {
/* 4573 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setMsgBatchSize(int)", "setter", 
/* 4574 */           Integer.valueOf(size));
/*      */     }
/* 4576 */     setIntProperty("XMSC_WMQ_MSG_BATCH_SIZE", size);
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
/*      */   public void setMsgCompList(Collection<?> compList) throws JMSException {
/* 4588 */     if (Trace.isOn) {
/* 4589 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setMsgCompList(Collection<?>)", "setter", compList);
/*      */     }
/*      */ 
/*      */     
/* 4593 */     if (compList == null || compList.size() == 0) {
/* 4594 */       Vector<Integer> defaultMsgCompList = new Vector<>();
/* 4595 */       defaultMsgCompList.add(Integer.valueOf(0));
/* 4596 */       setObjectProperty("XMSC_WMQ_MSG_COMP", defaultMsgCompList);
/*      */     } else {
/*      */       
/* 4599 */       setObjectProperty("XMSC_WMQ_MSG_COMP", compList);
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
/*      */   public void setMsgCompList(String compList) throws JMSException {
/* 4611 */     if (Trace.isOn) {
/* 4612 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setMsgCompList(String)", "setter", compList);
/*      */     }
/*      */     
/* 4615 */     if (compList != null) {
/* 4616 */       StringTokenizer st = new StringTokenizer(compList);
/* 4617 */       Vector<Integer> c = new Vector<>();
/* 4618 */       while (st.hasMoreTokens()) {
/* 4619 */         String tk = st.nextToken();
/* 4620 */         if (tk.equals("NONE")) {
/* 4621 */           c.add(Integer.valueOf(0)); continue;
/*      */         } 
/* 4623 */         if (tk.equals("RLE")) {
/* 4624 */           c.add(Integer.valueOf(1)); continue;
/*      */         } 
/* 4626 */         if (tk.equals("ZLIBFAST")) {
/* 4627 */           c.add(Integer.valueOf(2)); continue;
/*      */         } 
/* 4629 */         if (tk.equals("ZLIBHIGH")) {
/* 4630 */           c.add(Integer.valueOf(4));
/*      */           continue;
/*      */         } 
/* 4633 */         if (Trace.isOn) {
/* 4634 */           Trace.traceData(this, "Compressor value is not supported", null);
/*      */         }
/*      */         
/* 4637 */         HashMap<String, String> info = new HashMap<>();
/* 4638 */         info.put("XMSC_INSERT_VALUE", tk);
/* 4639 */         info.put("XMSC_INSERT_PROPERTY", "Compressor value");
/* 4640 */         JMSException je = (JMSException)NLSServices.createException("JMSMQ1006", info);
/*      */         
/* 4642 */         if (Trace.isOn) {
/* 4643 */           Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "setMsgCompList(String)", (Throwable)je);
/*      */         }
/*      */         
/* 4646 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 4650 */       setObjectProperty("XMSC_WMQ_MSG_COMP", c);
/*      */     } else {
/*      */       
/* 4653 */       setObjectProperty("XMSC_WMQ_MSG_COMP", null);
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
/*      */   @Deprecated
/*      */   public void setMulticast(int multicast) throws JMSException {
/* 4666 */     if (Trace.isOn) {
/* 4667 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setMulticast(int)", "setter", 
/* 4668 */           Integer.valueOf(multicast));
/*      */     }
/* 4670 */     setIntProperty("multicast", multicast);
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
/*      */   public void setOptimisticPublication(boolean newVal) throws JMSException {
/* 4689 */     if (Trace.isOn) {
/* 4690 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setOptimisticPublication(boolean)", "setter", 
/* 4691 */           Boolean.valueOf(newVal));
/*      */     }
/* 4693 */     setBooleanProperty("XMSC_WMQ_OPT_PUB", newVal);
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
/*      */   @Deprecated
/*      */   public void setOutcomeNotification(boolean newVal) throws JMSException {
/* 4706 */     if (Trace.isOn) {
/* 4707 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setOutcomeNotification(boolean)", "setter", 
/* 4708 */           Boolean.valueOf(newVal));
/*      */     }
/* 4710 */     setBooleanProperty("XMSC_WMQ_OUTCOME_NOTIFICATION", newVal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPollingInterval(int interval) throws JMSException {
/* 4721 */     if (Trace.isOn) {
/* 4722 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setPollingInterval(int)", "setter", 
/* 4723 */           Integer.valueOf(interval));
/*      */     }
/* 4725 */     setIntProperty("XMSC_WMQ_POLLING_INTERVAL", interval);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPort(int port) throws JMSException {
/* 4736 */     if (Trace.isOn) {
/* 4737 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setPort(int)", "setter", 
/* 4738 */           Integer.valueOf(port));
/*      */     }
/*      */ 
/*      */     
/* 4742 */     setIntProperty("XMSC_WMQ_PORT", port);
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
/*      */   @Deprecated
/*      */   public void setProcessDuration(int newVal) throws JMSException {
/* 4755 */     if (Trace.isOn) {
/* 4756 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setProcessDuration(int)", "setter", 
/* 4757 */           Integer.valueOf(newVal));
/*      */     }
/* 4759 */     setIntProperty("XMSC_WMQ_PROCESS_DURATION", newVal);
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
/*      */   public void setProviderVersion(String version) throws JMSException {
/* 4774 */     if (Trace.isOn) {
/* 4775 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setProviderVersion(String)", "setter", version);
/*      */     }
/*      */     
/* 4778 */     setStringProperty("XMSC_WMQ_PROVIDER_VERSION", version);
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
/*      */   @Deprecated
/*      */   public void setProxyHostName(String hostName) throws JMSException {
/* 4791 */     if (Trace.isOn) {
/* 4792 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setProxyHostName(String)", "setter", hostName);
/*      */     }
/*      */ 
/*      */     
/* 4796 */     setStringProperty("XMSC_RTT_PROXY_HOSTNAME", hostName);
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
/*      */   @Deprecated
/*      */   public void setProxyPort(int proxyPort) throws JMSException {
/* 4809 */     if (Trace.isOn) {
/* 4810 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setProxyPort(int)", "setter", 
/* 4811 */           Integer.valueOf(proxyPort));
/*      */     }
/*      */     
/* 4814 */     setIntProperty("XMSC_RTT_PROXY_PORT", proxyPort);
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
/*      */   public void setPubAckInterval(int interval) throws JMSException {
/* 4827 */     if (Trace.isOn) {
/* 4828 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setPubAckInterval(int)", "setter", 
/* 4829 */           Integer.valueOf(interval));
/*      */     }
/* 4831 */     setIntProperty("XMSC_WMQ_PUB_ACK_INTERVAL", interval);
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
/*      */   public void setQueueManager(String queueManagerName) throws JMSException {
/* 4853 */     if (Trace.isOn) {
/* 4854 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setQueueManager(String)", "setter", queueManagerName);
/*      */     }
/*      */     
/* 4857 */     setStringProperty("XMSC_WMQ_QUEUE_MANAGER", queueManagerName);
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
/*      */   public void setReceiveExit(String receiveExit) {
/* 4871 */     if (Trace.isOn) {
/* 4872 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setReceiveExit(String)", "setter", receiveExit);
/*      */     }
/*      */     
/*      */     try {
/* 4876 */       setStringProperty("XMSC_WMQ_RECEIVE_EXIT", receiveExit);
/*      */     }
/* 4878 */     catch (JMSException je) {
/* 4879 */       if (Trace.isOn) {
/* 4880 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setReceiveExit(String)", (Throwable)je);
/*      */       }
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
/*      */   public void setReceiveExitInit(String data) {
/* 4893 */     if (Trace.isOn) {
/* 4894 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setReceiveExitInit(String)", "setter", data);
/*      */     }
/*      */     
/*      */     try {
/* 4898 */       setStringProperty("XMSC_WMQ_RECEIVE_EXIT_INIT", data);
/*      */     }
/* 4900 */     catch (JMSException e) {
/* 4901 */       if (Trace.isOn) {
/* 4902 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setReceiveExitInit(String)", (Throwable)e);
/*      */       }
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
/*      */   @Deprecated
/*      */   public void setReceiveIsolation(int newVal) throws JMSException {
/* 4921 */     if (Trace.isOn) {
/* 4922 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setReceiveIsolation(int)", "setter", 
/* 4923 */           Integer.valueOf(newVal));
/*      */     }
/* 4925 */     setIntProperty("XMSC_WMQ_RECEIVE_ISOLATION", newVal);
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
/*      */   public void setRescanInterval(int interval) throws JMSException {
/* 4937 */     if (Trace.isOn) {
/* 4938 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setRescanInterval(int)", "setter", 
/* 4939 */           Integer.valueOf(interval));
/*      */     }
/* 4941 */     setIntProperty("XMSC_WMQ_RESCAN_INTERVAL", interval);
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
/*      */   public void setSecurityExit(String securityExit) {
/* 4956 */     if (Trace.isOn) {
/* 4957 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setSecurityExit(String)", "setter", securityExit);
/*      */     }
/*      */     
/*      */     try {
/* 4961 */       setStringProperty("XMSC_WMQ_SECURITY_EXIT", securityExit);
/*      */     }
/* 4963 */     catch (JMSException je) {
/* 4964 */       if (Trace.isOn) {
/* 4965 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setSecurityExit(String)", (Throwable)je);
/*      */       }
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
/*      */   public void setSecurityExitInit(String data) {
/* 4978 */     if (Trace.isOn) {
/* 4979 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setSecurityExitInit(String)", "setter", data);
/*      */     }
/*      */     
/*      */     try {
/* 4983 */       setStringProperty("XMSC_WMQ_SECURITY_EXIT_INIT", data);
/*      */     }
/* 4985 */     catch (JMSException je) {
/* 4986 */       if (Trace.isOn) {
/* 4987 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setSecurityExitInit(String)", (Throwable)je);
/*      */       }
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
/*      */   public void setSendCheckCount(int interval) throws JMSException {
/* 5004 */     if (Trace.isOn) {
/* 5005 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setSendCheckCount(int)", "setter", 
/* 5006 */           Integer.valueOf(interval));
/*      */     }
/* 5008 */     setIntProperty("XMSC_WMQ_SEND_CHECK_COUNT", interval);
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
/*      */   public void setSendExit(String sendExit) {
/* 5021 */     if (Trace.isOn) {
/* 5022 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setSendExit(String)", "setter", sendExit);
/*      */     }
/*      */     
/*      */     try {
/* 5026 */       setStringProperty("XMSC_WMQ_SEND_EXIT", sendExit);
/*      */     }
/* 5028 */     catch (JMSException je) {
/* 5029 */       if (Trace.isOn) {
/* 5030 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setSendExit(String)", (Throwable)je);
/*      */       }
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
/*      */   public void setSendExitInit(String data) {
/* 5045 */     if (Trace.isOn) {
/* 5046 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setSendExitInit(String)", "setter", data);
/*      */     }
/*      */     
/*      */     try {
/* 5050 */       setStringProperty("XMSC_WMQ_SEND_EXIT_INIT", data);
/*      */     }
/* 5052 */     catch (JMSException je) {
/* 5053 */       if (Trace.isOn) {
/* 5054 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setSendExitInit(String)", (Throwable)je);
/*      */       }
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
/*      */   public void setShareConvAllowed(int shared) throws JMSException {
/* 5070 */     if (Trace.isOn) {
/* 5071 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setShareConvAllowed(int)", "setter", 
/* 5072 */           Integer.valueOf(shared));
/*      */     }
/* 5074 */     setIntProperty("XMSC_WMQ_SHARE_CONV_ALLOWED", shared);
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
/*      */   public void setSparseSubscriptions(boolean sparse) throws JMSException {
/* 5089 */     if (Trace.isOn) {
/* 5090 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setSparseSubscriptions(boolean)", "setter", 
/* 5091 */           Boolean.valueOf(sparse));
/*      */     }
/* 5093 */     setBooleanProperty("XMSC_WMQ_SPARSE_SUBSCRIPTIONS", sparse);
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
/*      */   public void setSSLCertStores(Collection<?> stores) {
/* 5117 */     if (Trace.isOn) {
/* 5118 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "setSSLCertStores(java.util.Collection<?>)", new Object[] { stores });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 5128 */       setObjectProperty("XMSC_WMQ_SSL_CERT_STORES_COL", stores);
/* 5129 */       setStringProperty("XMSC_WMQ_SSL_CERT_STORES_STR", null);
/*      */     }
/* 5131 */     catch (JMSException je) {
/* 5132 */       if (Trace.isOn) {
/* 5133 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setSSLCertStores(java.util.Collection<?>)", (Throwable)je);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 5139 */     if (Trace.isOn) {
/* 5140 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "setSSLCertStores(java.util.Collection<?>)");
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
/*      */   public void setSSLCertStores(String stores) throws JMSException {
/* 5162 */     if (Trace.isOn) {
/* 5163 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setSSLCertStores(String)", "setter", stores);
/*      */     }
/*      */     
/* 5166 */     setStringProperty("XMSC_WMQ_SSL_CERT_STORES_STR", stores);
/* 5167 */     setObjectProperty("XMSC_WMQ_SSL_CERT_STORES_COL", null);
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
/*      */   public void setSSLCipherSuite(String cipherSuite) {
/* 5183 */     if (Trace.isOn) {
/* 5184 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setSSLCipherSuite(String)", "setter", cipherSuite);
/*      */     }
/*      */     
/*      */     try {
/* 5188 */       setStringProperty("XMSC_WMQ_SSL_CIPHER_SUITE", cipherSuite);
/*      */     }
/* 5190 */     catch (JMSException je) {
/* 5191 */       if (Trace.isOn) {
/* 5192 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setSSLCipherSuite(String)", (Throwable)je);
/*      */       }
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
/*      */   public void setSSLFipsRequired(boolean required) {
/* 5206 */     if (Trace.isOn) {
/* 5207 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setSSLFipsRequired(boolean)", "setter", 
/* 5208 */           Boolean.valueOf(required));
/*      */     }
/*      */     try {
/* 5211 */       setBooleanProperty("XMSC_WMQ_SSL_FIPS_REQUIRED", required);
/*      */     }
/* 5213 */     catch (JMSException je) {
/* 5214 */       if (Trace.isOn) {
/* 5215 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setSSLFipsRequired(boolean)", (Throwable)je);
/*      */       }
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
/*      */   public void setSSLPeerName(String peerName) throws JMSException {
/* 5233 */     if (Trace.isOn) {
/* 5234 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setSSLPeerName(String)", "setter", peerName);
/*      */     }
/*      */ 
/*      */     
/* 5238 */     setObjectProperty("XMSC_WMQ_SSL_PEER_NAME", peerName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSSLResetCount(int bytes) throws JMSException {
/* 5249 */     if (Trace.isOn) {
/* 5250 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setSSLResetCount(int)", "setter", 
/* 5251 */           Integer.valueOf(bytes));
/*      */     }
/* 5253 */     setIntProperty("XMSC_WMQ_SSL_KEY_RESETCOUNT", bytes);
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
/*      */   public void setSSLSocketFactory(Object sf) {
/* 5269 */     if (Trace.isOn) {
/* 5270 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setSSLSocketFactory(Object)", "setter", sf);
/*      */     }
/*      */     
/*      */     try {
/* 5274 */       setObjectProperty("XMSC_WMQ_SSL_SOCKET_FACTORY", sf);
/*      */     }
/* 5276 */     catch (JMSException je) {
/* 5277 */       if (Trace.isOn) {
/* 5278 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setSSLSocketFactory(Object)", (Throwable)je);
/*      */       }
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
/*      */   public void setStatusRefreshInterval(int interval) throws JMSException {
/* 5294 */     if (Trace.isOn) {
/* 5295 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setStatusRefreshInterval(int)", "setter", 
/* 5296 */           Integer.valueOf(interval));
/*      */     }
/* 5298 */     setIntProperty("XMSC_WMQ_STATUS_REFRESH_INTERVAL", interval);
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
/*      */   public void setSubscriptionStore(int flag) throws JMSException {
/* 5314 */     if (Trace.isOn) {
/* 5315 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setSubscriptionStore(int)", "setter", 
/* 5316 */           Integer.valueOf(flag));
/*      */     }
/* 5318 */     setIntProperty("XMSC_WMQ_SUBSCRIPTION_STORE", flag);
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
/*      */   public void setSyncpointAllGets(boolean flag) {
/* 5337 */     if (Trace.isOn) {
/* 5338 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setSyncpointAllGets(boolean)", "setter", 
/* 5339 */           Boolean.valueOf(flag));
/*      */     }
/* 5341 */     if (flag) {
/*      */       try {
/* 5343 */         setBooleanProperty("XMSC_WMQ_SYNCPOINT_ALL_GETS", true);
/*      */       }
/* 5345 */       catch (JMSException je) {
/* 5346 */         if (Trace.isOn) {
/* 5347 */           Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setSyncpointAllGets(boolean)", (Throwable)je, 1);
/*      */         }
/*      */       } 
/*      */     } else {
/*      */ 
/*      */       
/*      */       try {
/*      */         
/* 5355 */         setBooleanProperty("XMSC_WMQ_SYNCPOINT_ALL_GETS", false);
/*      */       }
/* 5357 */       catch (JMSException je) {
/* 5358 */         if (Trace.isOn) {
/* 5359 */           Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setSyncpointAllGets(boolean)", (Throwable)je, 2);
/*      */         }
/*      */       } 
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
/*      */   public void setTargetClientMatching(boolean matchClient) {
/* 5383 */     if (Trace.isOn) {
/* 5384 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setTargetClientMatching(boolean)", "setter", 
/* 5385 */           Boolean.valueOf(matchClient));
/*      */     }
/*      */     try {
/* 5388 */       setBooleanProperty("XMSC_WMQ_TARGET_CLIENT_MATCHING", matchClient);
/*      */     }
/* 5390 */     catch (JMSException je) {
/* 5391 */       if (Trace.isOn) {
/* 5392 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setTargetClientMatching(boolean)", (Throwable)je);
/*      */       }
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
/*      */   public void setTemporaryModel(String queueName) throws JMSException {
/* 5415 */     if (Trace.isOn) {
/* 5416 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setTemporaryModel(String)", "setter", queueName);
/*      */     }
/*      */     
/* 5419 */     setStringProperty("XMSC_WMQ_TEMPORARY_MODEL", queueName);
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
/*      */   public void setTempTopicPrefix(String prefix) throws JMSException {
/* 5433 */     if (Trace.isOn) {
/* 5434 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setTempTopicPrefix(String)", "setter", prefix);
/*      */     }
/*      */     
/* 5437 */     setStringProperty("XMSC_WMQ_TEMP_TOPIC_PREFIX", prefix);
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
/*      */   public void setTempQPrefix(String newTempQPrefix) throws JMSException {
/* 5449 */     if (Trace.isOn) {
/* 5450 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setTempQPrefix(java.lang.String)", "setter", newTempQPrefix);
/*      */     }
/*      */     
/* 5453 */     setStringProperty("XMSC_WMQ_TEMP_Q_PREFIX", newTempQPrefix);
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
/*      */   public void setTransportType(int type) throws JMSException {
/* 5468 */     if (Trace.isOn) {
/* 5469 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setTransportType(int)", "setter", 
/* 5470 */           Integer.valueOf(type));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5495 */     setIntProperty("XMSC_WMQ_CONNECTION_MODE", type);
/*      */     
/* 5497 */     switch (type) {
/*      */       
/*      */       case 0:
/* 5500 */         if (CSSystem.currentPlatform().equals(CSSystem.Platform.OS_NSS)) {
/* 5501 */           HashMap<String, String> inserts = new HashMap<>();
/* 5502 */           inserts.put("XMSC_INSERT_VALUE", "WMQConstants.WMQ_CM_BINDINGS");
/* 5503 */           inserts.put("XMSC_INSERT_PROPERTY", "XMSC_WMQ_CONNECTION_MODE");
/* 5504 */           JMSException je = (JMSException)NLSServices.createException("JMSMQ1006", inserts);
/* 5505 */           if (Trace.isOn) {
/* 5506 */             Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "setTransportType(int)", (Throwable)je);
/*      */           }
/* 5508 */           throw je;
/*      */         } 
/*      */ 
/*      */ 
/*      */       
/*      */       case 1:
/* 5514 */         if (!this.portSet) {
/* 5515 */           setPort(1414);
/*      */ 
/*      */           
/* 5518 */           this.portSet = false;
/*      */         } 
/*      */ 
/*      */         
/* 5522 */         if (!(this instanceof MQQueueConnectionFactory))
/*      */         {
/*      */           
/* 5525 */           if (!this.bverSet) {
/* 5526 */             setBrokerVersion(-1);
/*      */ 
/*      */             
/* 5529 */             this.bverSet = false;
/*      */           } 
/*      */         }
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/*      */       case 4:
/* 5538 */         if (!this.portSet) {
/* 5539 */           setPort(1506);
/*      */ 
/*      */           
/* 5542 */           this.portSet = false;
/*      */         } 
/* 5544 */         if (!(this instanceof MQQueueConnectionFactory))
/*      */         {
/*      */           
/* 5547 */           if (!this.bverSet) {
/* 5548 */             setBrokerVersion(1);
/*      */ 
/*      */             
/* 5551 */             this.bverSet = false;
/*      */           } 
/*      */         }
/*      */         break;
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
/*      */   @Deprecated
/*      */   public void setUseConnectionPooling(boolean usePooling) {
/* 5575 */     if (Trace.isOn) {
/* 5576 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setUseConnectionPooling(boolean)", "setter", 
/* 5577 */           Boolean.valueOf(usePooling));
/*      */     }
/*      */     try {
/* 5580 */       setBooleanProperty("XMSC_WMQ_USE_CONNECTION_POOLING", usePooling);
/*      */     }
/* 5582 */     catch (JMSException je) {
/* 5583 */       if (Trace.isOn) {
/* 5584 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "setUseConnectionPooling(boolean)", (Throwable)je);
/*      */       }
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
/*      */   public void setVersion(int version) throws JMSException {
/* 5598 */     if (Trace.isOn) {
/* 5599 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setVersion(int)", "setter", 
/* 5600 */           Integer.valueOf(version));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5607 */     if (!this.versionChangeAllowed) {
/* 5608 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ0012", null);
/* 5609 */       if (Trace.isOn) {
/* 5610 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "setVersion(int)", (Throwable)je);
/*      */       }
/* 5612 */       throw je;
/*      */     } 
/* 5614 */     setIntProperty("version", version);
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
/*      */   public void setWildcardFormat(int format) throws JMSException {
/* 5629 */     if (Trace.isOn) {
/* 5630 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setWildcardFormat(int)", "setter", 
/* 5631 */           Integer.valueOf(format));
/*      */     }
/* 5633 */     setIntProperty("wildcardFormat", format);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAppName(String name) throws JMSException {
/* 5643 */     if (Trace.isOn) {
/* 5644 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setAppName(String)", "setter", name);
/*      */     }
/* 5646 */     setStringProperty("XMSC_WMQ_APPNAME", name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAsyncExceptions(int flags) throws JMSException {
/* 5656 */     if (Trace.isOn) {
/* 5657 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setAsyncExceptions(int)", "setter", 
/* 5658 */           Integer.valueOf(flags));
/*      */     }
/* 5660 */     setIntProperty("XMSC_ASYNC_EXCEPTIONS", flags);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateReference(Reference ref) throws OperationNotSupportedException {
/* 5670 */     if (Trace.isOn) {
/* 5671 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "updateReference(Reference)", new Object[] { ref });
/*      */     }
/*      */     
/*      */     try {
/* 5675 */       String s = null;
/*      */       
/* 5677 */       ref.add(new StringRefAddr("VER", String.valueOf(getVersion())));
/*      */       
/* 5679 */       s = getAppName();
/* 5680 */       if (s != null) {
/* 5681 */         ref.add(new StringRefAddr("APPNAME", s));
/*      */       }
/*      */       
/* 5684 */       s = getDescription();
/* 5685 */       if (s != null) {
/* 5686 */         ref.add(new StringRefAddr("DESC", s));
/*      */       }
/*      */       
/* 5689 */       ref.add(new StringRefAddr("TRAN", String.valueOf(getTransportType())));
/*      */       
/* 5691 */       s = getClientID();
/* 5692 */       if (s != null) {
/* 5693 */         ref.add(new StringRefAddr("CID", s));
/*      */       }
/*      */       
/* 5696 */       s = getQueueManager();
/* 5697 */       if (s != null) {
/* 5698 */         ref.add(new StringRefAddr("QMGR", s));
/*      */       }
/*      */       
/* 5701 */       s = getHostName();
/* 5702 */       if (s != null) {
/* 5703 */         ref.add(new StringRefAddr("HOST", s));
/*      */       }
/*      */       
/* 5706 */       ref.add(new StringRefAddr("PORT", String.valueOf(getPort())));
/*      */       
/* 5708 */       s = getChannel();
/* 5709 */       if (s != null) {
/* 5710 */         ref.add(new StringRefAddr("CHAN", s));
/*      */       }
/*      */       
/* 5713 */       ref.add(new StringRefAddr("CCS", String.valueOf(getCCSID())));
/*      */       
/* 5715 */       ref.add(new StringRefAddr("MNS", String.valueOf(getMapNameStyle())));
/*      */       
/* 5717 */       s = getReceiveExit();
/* 5718 */       if (s != null) {
/* 5719 */         ref.add(new StringRefAddr("RCX", s));
/*      */       }
/*      */       
/* 5722 */       s = getReceiveExitInit();
/* 5723 */       if (s != null) {
/* 5724 */         ref.add(new StringRefAddr("RCXI", s));
/*      */       }
/*      */       
/* 5727 */       s = getSecurityExit();
/* 5728 */       if (s != null) {
/* 5729 */         ref.add(new StringRefAddr("SCX", s));
/*      */       }
/*      */       
/* 5732 */       s = getSecurityExitInit();
/* 5733 */       if (s != null) {
/* 5734 */         ref.add(new StringRefAddr("SCXI", s));
/*      */       }
/*      */       
/* 5737 */       s = getSendExit();
/* 5738 */       if (s != null) {
/* 5739 */         ref.add(new StringRefAddr("SDX", s));
/*      */       }
/*      */       
/* 5742 */       s = getSendExitInit();
/* 5743 */       if (s != null) {
/* 5744 */         ref.add(new StringRefAddr("SDXI", s));
/*      */       }
/*      */       
/* 5747 */       s = getSSLCipherSuite();
/* 5748 */       if (s != null) {
/* 5749 */         ref.add(new StringRefAddr("SCPHS", s));
/*      */       }
/*      */       
/* 5752 */       s = getSSLPeerName();
/* 5753 */       if (s != null) {
/* 5754 */         ref.add(new StringRefAddr("SPEER", s));
/*      */       }
/*      */       
/* 5757 */       s = getSSLCertStoresAsString();
/* 5758 */       if (s != null) {
/* 5759 */         ref.add(new StringRefAddr("SCRL", s));
/*      */       }
/*      */       
/* 5762 */       s = getHdrCompListAsString();
/* 5763 */       if (s != null) {
/* 5764 */         ref.add(new StringRefAddr("HC", s));
/*      */       }
/*      */       
/* 5767 */       s = getMsgCompListAsString();
/* 5768 */       if (s != null) {
/* 5769 */         ref.add(new StringRefAddr("MC", s));
/*      */       }
/*      */       
/* 5772 */       s = new String(getConnTag(), Charset.defaultCharset());
/* 5773 */       if (s != null) {
/* 5774 */         ref.add(new StringRefAddr("CT", s));
/*      */       }
/* 5776 */       ref.add(new StringRefAddr("CTO", String.valueOf(getMQConnectionOptions())));
/*      */       
/* 5778 */       if (getSSLSocketFactory() != null) {
/*      */ 
/*      */         
/* 5781 */         OperationNotSupportedException traceRet2 = new OperationNotSupportedException("MQJMS_Messages.MQJMS_E_MQCF_NOT_SERIALIZABLE, SSLSocketFactory");
/*      */         
/* 5783 */         if (Trace.isOn) {
/* 5784 */           Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "updateReference(Reference)", traceRet2, 1);
/*      */         }
/*      */         
/* 5787 */         throw traceRet2;
/*      */       } 
/*      */       
/* 5790 */       ref.add(new StringRefAddr("SCC", String.valueOf(getSendCheckCount())));
/*      */       
/* 5792 */       ref.add(new StringRefAddr("SCALD", String.valueOf(getShareConvAllowed())));
/*      */       
/* 5794 */       ref.add(new StringRefAddr("SRC", String.valueOf(getSSLResetCount())));
/*      */       
/* 5796 */       ref.add(new StringRefAddr("SFIPS", String.valueOf(getSSLFipsRequired())));
/*      */       
/* 5798 */       ref.add(new StringRefAddr("SPAG", String.valueOf(getSyncpointAllGets())));
/*      */       
/* 5800 */       ref.add(new StringRefAddr("UCP", String.valueOf(getUseConnectionPooling())));
/*      */       
/* 5802 */       ref.add(new StringRefAddr("PINT", String.valueOf(getPollingInterval())));
/*      */       
/* 5804 */       ref.add(new StringRefAddr("PVER", getProviderVersion()));
/*      */       
/* 5806 */       ref.add(new StringRefAddr("WCFMT", String.valueOf(getWildcardFormat())));
/*      */       
/* 5808 */       ref.add(new StringRefAddr("MBS", String.valueOf(getMsgBatchSize())));
/*      */       
/* 5810 */       ref.add(new StringRefAddr("FIQ", String.valueOf(getFailIfQuiesce())));
/*      */       
/* 5812 */       ref.add(new StringRefAddr("LA", getLocalAddress()));
/*      */       
/* 5814 */       ref.add(new StringRefAddr("RINT", String.valueOf(getRescanInterval())));
/*      */       
/* 5816 */       ref.add(new StringRefAddr("TCM", String.valueOf(getTargetClientMatching())));
/*      */       
/* 5818 */       ref.add(new StringRefAddr("AEX", String.valueOf(getAsyncExceptions())));
/*      */       
/* 5820 */       if (getCCDTURL() != null) {
/* 5821 */         ref.add(new StringRefAddr("CCDTURL", String.valueOf(getCCDTURL())));
/*      */       }
/*      */       
/* 5824 */       ref.add(new StringRefAddr("CROPT", String.valueOf(getClientReconnectOptions())));
/*      */       
/* 5826 */       ref.add(new StringRefAddr("CRT", String.valueOf(getClientReconnectTimeout())));
/*      */       
/* 5828 */       ref.add(new StringRefAddr("CRSHOSTS", getConnectionNameList()));
/*      */     }
/* 5830 */     catch (JMSException je) {
/* 5831 */       if (Trace.isOn) {
/* 5832 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "updateReference(Reference)", (Throwable)je);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 5837 */       OperationNotSupportedException traceRet1 = new OperationNotSupportedException("MQJMS_E_MQCF_NOT_SERIALIZABLE, SSLCertStores");
/*      */       
/* 5839 */       if (Trace.isOn) {
/* 5840 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "updateReference(Reference)", traceRet1, 2);
/*      */       }
/*      */       
/* 5843 */       throw traceRet1;
/*      */     } 
/*      */     
/* 5846 */     if (Trace.isOn) {
/* 5847 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "updateReference(Reference)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 5853 */     if (Trace.isOn) {
/* 5854 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "writeObject(java.io.ObjectOutputStream)", new Object[] { out });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5862 */     ObjectOutputStream.PutField fields = out.putFields();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 5876 */       fields.put("applicationName", getAppName());
/* 5877 */       fields.put("asyncExceptions", getAsyncExceptions());
/* 5878 */       fields.put("bverSet", this.bverSet);
/* 5879 */       fields.put("ccdtUrl", getCCDTURL());
/* 5880 */       fields.put("CCSID", getCCSID());
/* 5881 */       fields.put("channel", getChannel());
/* 5882 */       fields.put("clientId", getClientID());
/* 5883 */       fields.put("clientReconnectOptions", getClientReconnectOptions());
/* 5884 */       fields.put("connectionNameList", getConnectionNameList());
/* 5885 */       fields.put("clientReconnectTimeout", getClientReconnectTimeout());
/* 5886 */       fields.put("connOptions", getMQConnectionOptions());
/* 5887 */       fields.put("connTag", getConnTag());
/* 5888 */       fields.put("description", getDescription());
/* 5889 */       fields.put("failIfQuiesce", getFailIfQuiesce());
/* 5890 */       fields.put("hdrCompList", getHdrCompList());
/* 5891 */       fields.put("hostName", getHostName());
/* 5892 */       fields.put("localAddress", getLocalAddress());
/* 5893 */       fields.put("mapNameStyle", getMapNameStyle());
/* 5894 */       fields.put("msgBatchSize", getMsgBatchSize());
/* 5895 */       fields.put("msgCompList", getMsgCompList());
/* 5896 */       fields.put("mselSet", this.mselSet);
/* 5897 */       fields.put("optimisticPublication", getOptimisticPublication());
/* 5898 */       fields.put("outcomeNotification", getOutcomeNotification());
/* 5899 */       fields.put("pollingInterval", getPollingInterval());
/* 5900 */       fields.put("port", getPort());
/* 5901 */       fields.put("portSet", this.portSet);
/* 5902 */       fields.put("processDuration", getProcessDuration());
/* 5903 */       fields.put("providerVersion", getProviderVersion());
/* 5904 */       fields.put("queueManager", getQueueManager());
/* 5905 */       fields.put("receiveExit", getReceiveExit());
/* 5906 */       fields.put("receiveExitInit", getReceiveExitInit());
/* 5907 */       fields.put("receiveIsolation", getReceiveIsolation());
/* 5908 */       fields.put("rescanInterval", getRescanInterval());
/* 5909 */       fields.put("securityExit", getSecurityExit());
/* 5910 */       fields.put("securityExitInit", getSecurityExitInit());
/* 5911 */       fields.put("sendExit", getSendExit());
/* 5912 */       fields.put("sendExitInit", getSendExitInit());
/* 5913 */       fields.put("sendCheckCount", getSendCheckCount());
/* 5914 */       fields.put("shareConvAllowed", getShareConvAllowed());
/* 5915 */       fields.put("sslCertStores_coll", getSSLCertStores());
/* 5916 */       fields.put("sslCertStores_string", getSSLCertStoresAsString());
/* 5917 */       fields.put("sslCipherSuite", getSSLCipherSuite());
/* 5918 */       fields.put("sslFipsRequired", getSSLFipsRequired());
/* 5919 */       fields.put("sslPeerName", getSSLPeerName());
/* 5920 */       fields.put("sslResetCount", getSSLResetCount());
/* 5921 */       fields.put("sslSocketFactory", getSSLSocketFactory());
/* 5922 */       fields.put("syncpointAllGets", getSyncpointAllGets());
/* 5923 */       fields.put("targetClientMatching", getTargetClientMatching());
/* 5924 */       fields.put("transportType", getTransportType());
/* 5925 */       fields.put("useConnectionPooling", getUseConnectionPooling());
/* 5926 */       fields.put("version", getVersion());
/* 5927 */       fields.put("wildcardFormat", getWildcardFormat());
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5932 */       if (!(this instanceof MQTopicConnectionFactory)) {
/* 5933 */         fields.put("messageRetention", getMessageRetention());
/* 5934 */         fields.put("temporaryModel", getTemporaryModel());
/* 5935 */         fields.put("tempQPrefix", getTempQPrefix());
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 5941 */       if (!(this instanceof MQQueueConnectionFactory)) {
/* 5942 */         fields.put("brokerCCSubQueue", getBrokerCCSubQueue());
/* 5943 */         fields.put("brokerControlQueue", getBrokerControlQueue());
/* 5944 */         fields.put("brokerPubQueue", getBrokerPubQueue());
/* 5945 */         fields.put("brokerQueueManager", getBrokerQueueManager());
/* 5946 */         fields.put("brokerSubQueue", getBrokerSubQueue());
/* 5947 */         fields.put("cleanupInterval", getCleanupInterval());
/* 5948 */         fields.put("cleanupLevel", getCleanupLevel());
/* 5949 */         fields.put("cloneSupport", getCloneSupport());
/* 5950 */         fields.put("directAuth", getDirectAuth());
/* 5951 */         fields.put("maxBufferSize", getMaxBufferSize());
/* 5952 */         fields.put("messageSelection", getMessageSelection());
/* 5953 */         fields.put("multicast", getMulticast());
/* 5954 */         fields.put("proxyHostName", getProxyHostName());
/* 5955 */         fields.put("proxyPort", getProxyPort());
/* 5956 */         fields.put("pubAckInterval", getPubAckInterval());
/* 5957 */         fields.put("sparseSubscriptions", getSparseSubscriptions());
/* 5958 */         fields.put("statusRefreshInterval", getStatusRefreshInterval());
/* 5959 */         fields.put("subscriptionStore", getSubscriptionStore());
/* 5960 */         fields.put("tempTopicPrefix", getTempTopicPrefix());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 5968 */         int brokerVersion = getBrokerVersion();
/* 5969 */         if (brokerVersion == -1)
/*      */         {
/* 5971 */           fields.put("brokerVersion", 0);
/* 5972 */           fields.put("brokerVersionUnspecified", true);
/*      */         }
/*      */         else
/*      */         {
/* 5976 */           fields.put("brokerVersion", brokerVersion);
/* 5977 */           fields.put("brokerVersionUnspecified", false);
/*      */         }
/*      */       
/*      */       } 
/* 5981 */     } catch (JMSException je) {
/* 5982 */       if (Trace.isOn) {
/* 5983 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQConnectionFactory", "writeObject(java.io.ObjectOutputStream)", (Throwable)je);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5992 */     out.writeFields();
/*      */     
/* 5994 */     if (Trace.isOn) {
/* 5995 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "writeObject(java.io.ObjectOutputStream)");
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
/*      */   protected JmsConnection createCommonConnection(String userID, String password) throws JMSException {
/* 6019 */     if (Trace.isOn) {
/* 6020 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "createCommonConnection(String,String)", new Object[] { userID, (password == null) ? password : 
/*      */ 
/*      */             
/* 6023 */             Integer.valueOf(password.length()) });
/*      */     }
/*      */     
/* 6026 */     JmsConnection traceRet1 = (JmsConnection)super.createConnection(userID, password);
/*      */     
/* 6028 */     if (Trace.isOn) {
/* 6029 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "createCommonConnection(String,String)", traceRet1);
/*      */     }
/* 6031 */     return traceRet1;
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
/*      */   public Connection createConnection() throws JMSException {
/* 6051 */     if (Trace.isOn) {
/* 6052 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "createConnection()");
/*      */     }
/* 6054 */     MQConnection wrapper = new MQConnection();
/* 6055 */     wrapper.setDelegate(createCommonConnection((String)null, (String)null));
/*      */     
/* 6057 */     if (Trace.isOn) {
/* 6058 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "createConnection()", wrapper);
/*      */     }
/* 6060 */     return wrapper;
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
/*      */   public Connection createConnection(String userID, String password) throws JMSException {
/* 6078 */     if (Trace.isOn) {
/* 6079 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "createConnection(String,String)", new Object[] { userID, (password == null) ? password : 
/*      */ 
/*      */             
/* 6082 */             Integer.valueOf(password.length()) });
/*      */     }
/*      */     
/* 6085 */     MQConnection wrapper = new MQConnection();
/* 6086 */     wrapper.setDelegate(createCommonConnection(userID, password));
/*      */     
/* 6088 */     if (Trace.isOn) {
/* 6089 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "createConnection(String,String)", wrapper);
/*      */     }
/* 6091 */     return wrapper;
/*      */   }
/*      */   
/*      */   static void checkTracing() {
/* 6095 */     if (Trace.isOn) {
/* 6096 */       Trace.entry("com.ibm.mq.jms.MQConnectionFactory", "checkTracing()");
/*      */     }
/*      */     
/* 6099 */     String traceLevelProperty = "MQJMS_TRACE_LEVEL";
/* 6100 */     PropertyStore.register(traceLevelProperty, "");
/* 6101 */     String traceLevel = PropertyStore.getStringProperty(traceLevelProperty);
/*      */ 
/*      */ 
/*      */     
/* 6105 */     String traceDirProperty = "MQJMS_TRACE_DIR";
/* 6106 */     PropertyStore.register(traceDirProperty, "");
/* 6107 */     String traceDir = PropertyStore.getStringProperty(traceDirProperty);
/*      */     
/* 6109 */     if (traceDir != "") {
/*      */       
/* 6111 */       if (!traceDir.endsWith("/") && !traceDir.endsWith("\\")) {
/* 6112 */         traceDir = traceDir + "/";
/*      */       }
/*      */       
/* 6115 */       String fqFilename = traceDir + "mqjms_%PID%.trc";
/*      */ 
/*      */ 
/*      */       
/* 6119 */       PropertyStore.set("com.ibm.msg.client.commonservices.trace.outputName", fqFilename);
/*      */     } 
/*      */     
/* 6122 */     if (traceLevel != null) {
/* 6123 */       traceLevel = traceLevel.toLowerCase();
/* 6124 */       if (traceLevel.equals("on") || traceLevel.equals("base")) {
/* 6125 */         Trace.setStatus(true);
/*      */       }
/* 6127 */       else if (traceLevel.equals("off")) {
/* 6128 */         Trace.setStatus(false);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 6134 */     if (Trace.isOn) {
/* 6135 */       Trace.exit("com.ibm.mq.jms.MQConnectionFactory", "checkTracing()");
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
/*      */   public int getClientReconnectOptions() throws JMSException {
/* 6147 */     int traceRet1 = getIntProperty("XMSC_WMQ_CLIENT_RECONNECT_OPTIONS");
/* 6148 */     if (Trace.isOn) {
/* 6149 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getClientReconnectOptions()", "getter", 
/* 6150 */           Integer.valueOf(traceRet1));
/*      */     }
/* 6152 */     return traceRet1;
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
/*      */   public void setClientReconnectOptions(int options) throws JMSException {
/* 6182 */     if (Trace.isOn) {
/* 6183 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setClientReconnectOptions(int)", "setter", 
/* 6184 */           Integer.valueOf(options));
/*      */     }
/* 6186 */     setIntProperty("XMSC_WMQ_CLIENT_RECONNECT_OPTIONS", options);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getConnectionNameList() throws JMSException {
/* 6196 */     String traceRet1 = getStringProperty("XMSC_WMQ_CONNECTION_NAME_LIST");
/* 6197 */     if (Trace.isOn) {
/* 6198 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getConnectionNameList()", "getter", traceRet1);
/*      */     }
/*      */     
/* 6201 */     return traceRet1;
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
/*      */   public void setConnectionNameList(String hosts) throws JMSException {
/* 6215 */     if (Trace.isOn) {
/* 6216 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setConnectionNameList(String)", "setter", hosts);
/*      */     }
/*      */     
/* 6219 */     setConnectionNameListInternal(hosts);
/*      */     
/* 6221 */     this.portSet = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setConnectionNameListInternal(String hosts) throws JMSException {
/* 6229 */     if (Trace.isOn) {
/* 6230 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setConnectionNameListInternal(String)", "setter", hosts);
/*      */     }
/*      */     
/* 6233 */     setStringProperty("XMSC_WMQ_CONNECTION_NAME_LIST", hosts);
/*      */ 
/*      */     
/* 6236 */     String[] listElements = getStringProperty("XMSC_WMQ_CONNECTION_NAME_LIST").split(",");
/* 6237 */     String[] firstElementParts = listElements[0].split("[()]");
/* 6238 */     if (getStringProperty("XMSC_WMQ_HOST_NAME") == null) {
/* 6239 */       setHostName(firstElementParts[0]);
/* 6240 */       setPort(Integer.parseInt(firstElementParts[1]));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getClientReconnectTimeout() throws JMSException {
/* 6251 */     int traceRet1 = getIntProperty("XMSC_WMQ_CLIENT_RECONNECT_TIMEOUT");
/* 6252 */     if (Trace.isOn) {
/* 6253 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "getClientReconnectTimeout()", "getter", 
/* 6254 */           Integer.valueOf(traceRet1));
/*      */     }
/* 6256 */     return traceRet1;
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
/*      */   public void setClientReconnectTimeout(int timeout) throws JMSException {
/* 6269 */     if (Trace.isOn) {
/* 6270 */       Trace.data(this, "com.ibm.mq.jms.MQConnectionFactory", "setClientReconnectTimeout(int)", "setter", 
/* 6271 */           Integer.valueOf(timeout));
/*      */     }
/* 6273 */     setIntProperty("XMSC_WMQ_CLIENT_RECONNECT_TIMEOUT", timeout);
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
/*      */   public enum ConnectionFactoryProperty
/*      */   {
/* 6286 */     ASYNC_EXCEPTIONS("XMSC_ASYNC_EXCEPTIONS", "ASYNCEXCEPTION", "AEX", (short)4),
/*      */     
/* 6288 */     WMQ_APPNAME("XMSC_WMQ_APPNAME", "APPLICATIONNAME", "APPNAME", (short)4),
/*      */     
/* 6290 */     WMQ_BROKER_CC_SUBQ("XMSC_WMQ_BROKER_CC_SUBQ", "BROKERCCSUBQ", "CCSUB", (short)2),
/*      */     
/* 6292 */     WMQ_BROKER_CONTROLQ("XMSC_WMQ_BROKER_CONTROLQ", "BROKERCONQ", "BCON", (short)2),
/*      */     
/* 6294 */     WMQ_BROKER_PUBQ("XMSC_WMQ_BROKER_PUBQ", "BROKERPUBQ", "BPUB", (short)2),
/*      */     
/* 6296 */     WMQ_BROKER_QMGR("XMSC_WMQ_BROKER_QMGR", "BROKERQMGR", "BQM", (short)2),
/*      */     
/* 6298 */     WMQ_BROKER_SUBQ("XMSC_WMQ_BROKER_SUBQ", "BROKERSUBQ", "BSUB", (short)2),
/*      */     
/* 6300 */     WMQ_BROKER_VERSION("brokerVersion", "BROKERVERPUBQ", "BVER", (short)2),
/*      */     
/* 6302 */     CLIENT_ID("XMSC_CLIENT_ID", "CLIENTID", "CID", (short)4),
/*      */     
/* 6304 */     WMQ_CCDTURL("XMSC_WMQ_CCDTURL", "CCDTURL", "CCDT", (short)4),
/*      */     
/* 6306 */     WMQ_CF_DESCRIPTION("XMSC_WMQ_CF_DESCRIPTION", "DESCRIPTION", "DESC", (short)4),
/*      */     
/* 6308 */     WMQ_CHANNEL("XMSC_WMQ_CHANNEL", "CHANNEL", "CHAN", (short)4),
/*      */     
/* 6310 */     WMQ_CLEANUP_INTERVAL("XMSC_WMQ_CLEANUP_INTERVAL", "CLEANUPINT", "CLINT", (short)2),
/*      */     
/* 6312 */     WMQ_CLEANUP_LEVEL("XMSC_WMQ_CLEANUP_LEVEL", "CLEANUP", "CL", (short)2),
/*      */     
/* 6314 */     WMQ_CLIENT_RECONNECT_OPTIONS("XMSC_WMQ_CLIENT_RECONNECT_OPTIONS", "CLIENTRECONNECTOPTIONS", "CROPT", (short)4),
/*      */ 
/*      */     
/* 6317 */     WMQ_CLIENT_RECONNECT_TIMEOUT("XMSC_WMQ_CLIENT_RECONNECT_TIMEOUT", "CLIENTRECONNECTTIMEOUT", "CRT", (short)4),
/*      */ 
/*      */     
/* 6320 */     WMQ_CLONE_SUPPORT("XMSC_WMQ_CLONE_SUPPORT", "CLONESUPP", "CLS", (short)2),
/*      */     
/* 6322 */     WMQ_CONNECTION_MODE("XMSC_WMQ_CONNECTION_MODE", "TRANSPORT", "TRAN", (short)4),
/*      */     
/* 6324 */     WMQ_CONNECTION_NAME_LIST("XMSC_WMQ_CONNECTION_NAME_LIST", "CONNECTIONNAMELIST", "CNLIST", (short)4),
/*      */     
/* 6326 */     WMQ_CONNECTION_TAG("XMSC_WMQ_CONNECTION_TAG", "CONNTAG", "CNTAG", (short)4),
/*      */     
/* 6328 */     WMQ_CONNECT_OPTIONS("XMSC_WMQ_CONNECT_OPTIONS", "CONNOPT", "CNOPT", (short)4),
/*      */     
/* 6330 */     WMQ_FAIL_IF_QUIESCE("failIfQuiesce", "FAILIFQUIESCE", "FIQ", (short)4),
/*      */     
/* 6332 */     WMQ_HEADER_COMP("XMSC_WMQ_HEADER_COMP", "COMPHDR", "HC", (short)2),
/*      */     
/* 6334 */     WMQ_HOST_NAME("XMSC_WMQ_HOST_NAME", "HOSTNAME", "HOST", (short)4),
/*      */     
/* 6336 */     WMQ_LOCAL_ADDRESS("XMSC_WMQ_LOCAL_ADDRESS", "LOCALADDRESS", "LA", (short)4),
/*      */     
/* 6338 */     WMQ_MAP_NAME_STYLE("XMSC_WMQ_MAP_NAME_STYLE", "MAPNAMESTYLE", "MNST", (short)4),
/*      */     
/* 6340 */     WMQ_MESSAGE_RETENTION("XMSC_WMQ_MESSAGE_RETENTION", "MSGRETENTION", "MRET", (short)1),
/*      */     
/* 6342 */     WMQ_MESSAGE_SELECTION("XMSC_WMQ_MESSAGE_SELECTION", "MSGSELECTION", "MSEL", (short)2),
/*      */     
/* 6344 */     WMQ_MSG_BATCH_SIZE("XMSC_WMQ_MSG_BATCH_SIZE", "MSGBATCHSZ", "MBS", (short)4),
/*      */     
/* 6346 */     WMQ_MSG_COMP("XMSC_WMQ_MSG_COMP", "COMPMSG", "MC", (short)4),
/*      */     
/* 6348 */     WMQ_OPT_PUB("XMSC_WMQ_OPT_PUB", "OPTIMISTICPUBLICATION", "OPTPUB", (short)2),
/*      */     
/* 6350 */     WMQ_OUTCOME_NOTIFICATION("XMSC_WMQ_OUTCOME_NOTIFICATION", "OUTCOMENOTIFICATION", "NOTIFY", (short)2),
/*      */     
/* 6352 */     WMQ_POLLING_INTERVAL("XMSC_WMQ_POLLING_INTERVAL", "POLLINGINT", "PINT", (short)4),
/*      */     
/* 6354 */     WMQ_PORT("XMSC_WMQ_PORT", "PORT", "PORT", (short)4),
/*      */     
/* 6356 */     WMQ_PROCESS_DURATION("XMSC_WMQ_PROCESS_DURATION", "PROCESSDURATION", "PROCDUR", (short)2),
/*      */     
/* 6358 */     WMQ_PROVIDER_VERSION("XMSC_WMQ_PROVIDER_VERSION", "PROVIDERVERSION", "PVER", (short)4),
/*      */     
/* 6360 */     WMQ_PUB_ACK_INTERVAL("XMSC_WMQ_PUB_ACK_INTERVAL", "PUBACKINT", "PAI", (short)2),
/*      */     
/* 6362 */     WMQ_QMGR_CCSID("XMSC_WMQ_QMGR_CCSID", "CCSID", "CCS", (short)4),
/*      */     
/* 6364 */     WMQ_QUEUE_MANAGER("XMSC_WMQ_QUEUE_MANAGER", "QMANAGER", "QMGR", (short)4),
/*      */     
/* 6366 */     WMQ_RECEIVE_EXIT("XMSC_WMQ_RECEIVE_EXIT", "RECEXIT", "RCX", (short)4),
/*      */     
/* 6368 */     WMQ_RECEIVE_EXIT_INIT("XMSC_WMQ_RECEIVE_EXIT_INIT", "RECEXITINIT", "RCXI", (short)4),
/*      */     
/* 6370 */     WMQ_RECEIVE_ISOLATION("XMSC_WMQ_RECEIVE_ISOLATION", "RECEIVEISOLATION", "RCVISOL", (short)2),
/*      */     
/* 6372 */     WMQ_RESCAN_INTERVAL("XMSC_WMQ_RESCAN_INTERVAL", "RESCANINT", "RINT", (short)1),
/*      */     
/* 6374 */     WMQ_SECURITY_EXIT("XMSC_WMQ_SECURITY_EXIT", "SECEXIT", "SCX", (short)4),
/*      */     
/* 6376 */     WMQ_SECURITY_EXIT_INIT("XMSC_WMQ_SECURITY_EXIT_INIT", "SECEXITINIT", "SCXI", (short)4),
/*      */     
/* 6378 */     WMQ_SEND_CHECK_COUNT("XMSC_WMQ_SEND_CHECK_COUNT", "SENDCHECKCOUNT", "SCC", (short)4),
/*      */     
/* 6380 */     WMQ_SEND_EXIT("XMSC_WMQ_SEND_EXIT", "SENDEXIT", "SDX", (short)4),
/*      */     
/* 6382 */     WMQ_SEND_EXIT_INIT("XMSC_WMQ_SEND_EXIT_INIT", "SENDEXITINIT", "SDXI", (short)4),
/*      */     
/* 6384 */     WMQ_SHARE_CONV_ALLOWED("XMSC_WMQ_SHARE_CONV_ALLOWED", "SHARECONVALLOWED", "SCALD", (short)4),
/*      */     
/* 6386 */     WMQ_SPARSE_SUBSCRIPTIONS("XMSC_WMQ_SPARSE_SUBSCRIPTIONS", "SPARSESUBS", "SSUBS", (short)2),
/*      */     
/* 6388 */     WMQ_SSL_CERT_STORES_STR("XMSC_WMQ_SSL_CERT_STORES_STR", "SSLCRL", "SCRL", (short)4),
/*      */     
/* 6390 */     WMQ_SSL_CIPHER_SUITE("XMSC_WMQ_SSL_CIPHER_SUITE", "SSLCIPHERSUITE", "SCPHS", (short)4),
/*      */     
/* 6392 */     WMQ_SSL_FIPS_REQUIRED("XMSC_WMQ_SSL_FIPS_REQUIRED", "SSLFIPSREQUIRED", "SFIPS", (short)4),
/*      */     
/* 6394 */     WMQ_SSL_KEY_RESETCOUNT("XMSC_WMQ_SSL_KEY_RESETCOUNT", "SSLRESETCOUNT", "SRC", (short)4),
/*      */     
/* 6396 */     WMQ_SSL_PEER_NAME("XMSC_WMQ_SSL_PEER_NAME", "SSLPEERNAME", "SPEER", (short)4),
/*      */     
/* 6398 */     WMQ_STATUS_REFRESH_INTERVAL("XMSC_WMQ_STATUS_REFRESH_INTERVAL", "STATREFRESHINT", "SRI", (short)2),
/*      */     
/* 6400 */     WMQ_SUBSCRIPTION_STORE("XMSC_WMQ_SUBSCRIPTION_STORE", "SUBSTORE", "SS", (short)2),
/*      */     
/* 6402 */     WMQ_SYNCPOINT_ALL_GETS("XMSC_WMQ_SYNCPOINT_ALL_GETS", "SYNCPOINTALLGETS", "SPAG", (short)4),
/*      */     
/* 6404 */     WMQ_TARGET_CLIENT_MATCHING("XMSC_WMQ_TARGET_CLIENT_MATCHING", "TARGCLIENTMATCHING", "TCM", (short)1),
/*      */     
/* 6406 */     WMQ_TEMPORARY_MODEL("XMSC_WMQ_TEMPORARY_MODEL", "TEMPMODEL", "TM", (short)1),
/*      */     
/* 6408 */     WMQ_TEMP_Q_PREFIX("XMSC_WMQ_TEMP_Q_PREFIX", "TEMPQPREFIX", "TQP", (short)1),
/*      */     
/* 6410 */     WMQ_TEMP_TOPIC_PREFIX("XMSC_WMQ_TEMP_TOPIC_PREFIX", "TEMPTOPICPREFIX", "TTP", (short)2),
/*      */     
/* 6412 */     WMQ_USE_CONNECTION_POOLING("XMSC_WMQ_USE_CONNECTION_POOLING", "USECONNPOOLING", "UCP", (short)4),
/*      */     
/* 6414 */     WMQ_WILDCARD_FORMAT("wildcardFormat", "WILDCARDFORMAT", "WCFMT", (short)2),
/*      */     
/* 6416 */     WMQ_MAX_BUFFER_SIZE("XMSC_WMQ_MAX_BUFFER_SIZE", "MAXBUFFSIZE", "MBSZ", (short)2),
/*      */     
/* 6418 */     RTT_DIRECT_AUTH("XMSC_RTT_DIRECT_AUTH", "DIRECTAUTH", "DAUTH", (short)2),
/*      */     
/* 6420 */     RTT_MULTICAST("multicast", "MULTICAST", "MCAST", (short)2),
/*      */     
/* 6422 */     RTT_PROXY_HOSTNAME("XMSC_RTT_PROXY_HOSTNAME", "PROXYHOSTNAME", "PHOST", (short)2),
/*      */     
/* 6424 */     RTT_PROXY_PORT("XMSC_RTT_PROXY_PORT", "PROXYPORT", "PPORT", (short)2); private final String canonicalKey;
/*      */     
/*      */     ConnectionFactoryProperty(String canonicalKey, String longAdminKey, String shortAdminKey, short domain) {
/* 6427 */       if (Trace.isOn) {
/* 6428 */         Trace.entry(this, "com.ibm.mq.jms.ConnectionFactoryProperty", "<init>(String,String,String,short)", new Object[] { canonicalKey, longAdminKey, shortAdminKey, 
/*      */               
/* 6430 */               Short.valueOf(domain) });
/*      */       }
/* 6432 */       this.canonicalKey = canonicalKey;
/* 6433 */       this.shortAdminKey = shortAdminKey;
/* 6434 */       this.longAdminKey = longAdminKey;
/* 6435 */       this.domain = domain;
/* 6436 */       if (Trace.isOn) {
/* 6437 */         Trace.exit(this, "com.ibm.mq.jms.ConnectionFactoryProperty", "<init>(String,String,String,short)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private final String shortAdminKey;
/*      */ 
/*      */     
/*      */     private final String longAdminKey;
/*      */     
/*      */     private final short domain;
/*      */ 
/*      */     
/*      */     public int getDomain() {
/* 6452 */       if (Trace.isOn) {
/* 6453 */         Trace.data(this, "com.ibm.mq.jms.ConnectionFactoryProperty", "getDomain()", "getter", 
/* 6454 */             Integer.valueOf(this.domain));
/*      */       }
/* 6456 */       return this.domain;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getCanonicalKey() {
/* 6463 */       if (Trace.isOn) {
/* 6464 */         Trace.data(this, "com.ibm.mq.jms.ConnectionFactoryProperty", "getCanonicalKey()", "getter", this.canonicalKey);
/*      */       }
/*      */       
/* 6467 */       return this.canonicalKey;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getShortAdminKey() {
/* 6474 */       if (Trace.isOn) {
/* 6475 */         Trace.data(this, "com.ibm.mq.jms.ConnectionFactoryProperty", "getShortAdminKey()", "getter", this.shortAdminKey);
/*      */       }
/*      */       
/* 6478 */       return this.shortAdminKey;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getLongAdminKey() {
/* 6485 */       if (Trace.isOn) {
/* 6486 */         Trace.data(this, "com.ibm.mq.jms.ConnectionFactoryProperty", "getLongAdminKey()", "getter", this.longAdminKey);
/*      */       }
/*      */       
/* 6489 */       return this.longAdminKey;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean checkXASupported() throws JMSException {
/* 6535 */     if (Trace.isOn) {
/* 6536 */       Trace.entry(this, "com.ibm.mq.jms.MQConnectionFactory", "checkXASupported()");
/*      */     }
/*      */     
/* 6539 */     if (!this.capabilities.getBooleanProperty("XMSC_CAPABILITY_TRANSACTIONS_XA")) {
/*      */       
/* 6541 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC4001", null);
/* 6542 */       if (Trace.isOn) {
/* 6543 */         Trace.throwing(this, "com.ibm.mq.jms.MQConnectionFactory", "checkXASupported()", (Throwable)je);
/*      */       }
/* 6545 */       throw je;
/*      */     } 
/* 6547 */     if (Trace.isOn) {
/* 6548 */       Trace.exit(this, "com.ibm.mq.jms.MQConnectionFactory", "checkXASupported()", 
/* 6549 */           Boolean.valueOf(true));
/*      */     }
/* 6551 */     return true;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQConnectionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */