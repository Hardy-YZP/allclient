/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.constants.CMQC;
/*      */ import com.ibm.mq.constants.QmgrAdvancedCapability;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiUtils;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.system.JmqiSP;
/*      */ import com.ibm.mq.jms.SessionClosedException;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*      */ import com.ibm.msg.client.commonservices.cssystem.WASSupport;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import com.ibm.msg.client.provider.ProviderConnection;
/*      */ import com.ibm.msg.client.provider.ProviderConnectionBrowser;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import com.ibm.msg.client.provider.ProviderExceptionListener;
/*      */ import com.ibm.msg.client.provider.ProviderMessageReferenceHandler;
/*      */ import com.ibm.msg.client.provider.ProviderMetaData;
/*      */ import com.ibm.msg.client.provider.ProviderSession;
/*      */ import com.ibm.msg.client.wmq.common.WMQConnectionName;
/*      */ import com.ibm.msg.client.wmq.common.WMQConnectionNameList;
/*      */ import com.ibm.msg.client.wmq.common.internal.Reason;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQCommonConnection;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQPropertyContext;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQTemporaryQueue;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQTemporaryTopic;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQEnvironment;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMessage;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueueManagerEventListener;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQRRSQueueManager;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQSESSION;
/*      */ import java.io.IOException;
/*      */ import java.io.NotSerializableException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Hashtable;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.UUID;
/*      */ import java.util.Vector;
/*      */ import javax.jms.IllegalStateException;
/*      */ import javax.jms.InvalidDestinationException;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.JMSSecurityException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQConnection
/*      */   extends WMQPropertyContext
/*      */   implements ProviderConnection, WMQCommonConnection
/*      */ {
/*      */   private static final long serialVersionUID = -1720556615135839483L;
/*      */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQConnection.java";
/*      */   
/*      */   static {
/*  120 */     if (Trace.isOn) {
/*  121 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQConnection.java");
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
/*  137 */   protected static final Object NON_XAGROUP = new Object();
/*      */ 
/*      */   
/*      */   private static final int STATE_CLOSED = 2;
/*      */   
/*      */   private static final int STATE_STARTED = 1;
/*      */   
/*      */   private static final int STATE_STOPPED = 0;
/*      */   
/*  146 */   protected static final Object XAGROUP = new Object(); private static final String CLIENT_CONNECTION_MODE_NOT_SUPPORTED = "JMSFMQ0005"; private int brkOptLevel; private int brkVersion; private Hashtable<WMQDestination, MQQueue> temporaryDestinations; private Vector<MQConnectionBrowser> browsers; private MQSession cbBrokerSession; private Object cbBrokerSessionLock;
/*      */   private String connectionID;
/*      */   private long eoqTimeout;
/*      */   private long sweep_time;
/*      */   private static final int SWEEPTIME_DEFAULT = 30000;
/*      */   private int headerDataSize;
/*      */   private static final int HEADERSIZE_DEFAULT = -1;
/*      */   
/*      */   static {
/*  155 */     if (Trace.isOn) {
/*  156 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "static()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  164 */       CSSystem.dynamicLoadClass("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", MQConnection.class);
/*      */     }
/*  166 */     catch (ClassNotFoundException e) {
/*  167 */       if (Trace.isOn) {
/*  168 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "static()", e);
/*      */       }
/*      */       
/*  171 */       System.err.println("ERROR: couldn't load ConfigEnvironment class");
/*      */     } 
/*      */     
/*  174 */     if (Trace.isOn) {
/*  175 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "static()");
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
/*  210 */   private static String HEADERSIZE_PROPERTY = "com.ibm.mq.jms.ConnectionBrowserHeaderSize";
/*  211 */   private static String SWEEPTIME_PROPERTY = "com.ibm.mq.jms.tuning.sweep_time";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ProviderExceptionListener exceptionListener;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean qmConnectionBrokenEventRecieved;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int failIfQuiesce;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQQueueManager initialQm;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean mapNameStyle;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int messageRetention;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int messageSelection;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Map<String, Object> mqProperties;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int msgBatchSize;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean optimisticPublication;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean outcomeNotification;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean persistenceFromMD;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int pollingInterval;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int processDuration;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQPubSubServices psServices;
/*      */ 
/*      */ 
/*      */   
/*      */   private int pubAckInterval;
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQQueueManager qm;
/*      */ 
/*      */ 
/*      */   
/*      */   protected String qmgrName;
/*      */ 
/*      */ 
/*      */   
/*      */   protected String qmName;
/*      */ 
/*      */ 
/*      */   
/*      */   private MQQueueServices qServices;
/*      */ 
/*      */ 
/*      */   
/*      */   private int receiveIsolation;
/*      */ 
/*      */ 
/*      */   
/*      */   private String resolvedQMName;
/*      */ 
/*      */ 
/*      */   
/*      */   private JMSServicesMgr servicesMgr;
/*      */ 
/*      */ 
/*      */   
/*      */   private Vector<MQSession> sessions;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean sparseSubscriptions;
/*      */ 
/*      */ 
/*      */   
/*      */   private volatile int state;
/*      */ 
/*      */ 
/*      */   
/*      */   private MQSubscriptionEngine subscriptionEngine;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean supportsQAT2;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean syncpointAllGets;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean targetClientMatching;
/*      */ 
/*      */ 
/*      */   
/*      */   protected String temporaryModelQ;
/*      */ 
/*      */ 
/*      */   
/*      */   private String tempQPrefix;
/*      */ 
/*      */ 
/*      */   
/*      */   private MQQueueManager tempQqm;
/*      */ 
/*      */ 
/*      */   
/*      */   private int tmpQOpenOptions;
/*      */ 
/*      */ 
/*      */   
/*      */   private MQConnectionMetaData metaData;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQConnection(String username, String password, JmsPropertyContext propContext) throws JMSException {
/*  372 */     super(propContext); JmqiException jmqiException; this.brkOptLevel = 1; this.temporaryDestinations = new Hashtable<>(); this.browsers = new Vector<>(); this.cbBrokerSession = null; this.cbBrokerSessionLock = new Object(); this.connectionID = null; this.eoqTimeout = -1L; this.sweep_time = 30000L; this.headerDataSize = -1; this.qmConnectionBrokenEventRecieved = false; this.failIfQuiesce = 1; this.initialQm = null; this.mapNameStyle = true; this.mqProperties = new HashMap<>(); this.optimisticPublication = false; this.outcomeNotification = true; this.processDuration = 0; this.psServices = null; this.qmName = null; this.qServices = null; this.receiveIsolation = 0; this.resolvedQMName = ""; this.servicesMgr = null; this.sessions = new Vector<>(); this.state = 0; this.supportsQAT2 = false; this.targetClientMatching = true; this.tempQqm = null;
/*      */     this.tmpQOpenOptions = 32;
/*  374 */     if (Trace.isOn) {
/*  375 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>(String,String,JmsPropertyContext)", new Object[] { username, (password == null) ? password : 
/*      */ 
/*      */             
/*  378 */             Integer.valueOf(password.length()), propContext });
/*      */     }
/*  380 */     boolean zosClientAllowed = false;
/*  381 */     Exception nestedException = null; try {
/*      */       String sslCipherSuite; byte[] connTag; int connOptions; WMQConnectionNameList connName; String sslCSuite; Object peerName; Collection<?> sslCertStore; Object sslSocketFactory; String localAddress; Object hdrCompList, msgCompList; byte[] ct; int connOpt; JMSException je;
/*  383 */       this.failIfQuiesce = getIntProperty("failIfQuiesce");
/*  384 */       this.syncpointAllGets = getBooleanProperty("XMSC_WMQ_SYNCPOINT_ALL_GETS");
/*  385 */       this.msgBatchSize = getIntProperty("XMSC_WMQ_MSG_BATCH_SIZE");
/*  386 */       this.pollingInterval = getIntProperty("XMSC_WMQ_POLLING_INTERVAL");
/*  387 */       this.targetClientMatching = getBooleanProperty("XMSC_WMQ_TARGET_CLIENT_MATCHING");
/*      */       
/*  389 */       this.persistenceFromMD = false;
/*      */       
/*  391 */       String persistenceFromMDProperty = "com.ibm.mq.jms.tuning.usePersistenceFromMD";
/*  392 */       PropertyStore.register(persistenceFromMDProperty, "");
/*  393 */       String value = PropertyStore.getStringProperty(persistenceFromMDProperty);
/*      */       
/*  395 */       this.persistenceFromMD = (value != null) ? value.equalsIgnoreCase("ON") : false;
/*  396 */       if (Trace.isOn) {
/*  397 */         Trace.traceData(this, "persistenceFromMD = " + this.persistenceFromMD, null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  405 */       if (username != null) {
/*  406 */         if (Trace.isOn) {
/*  407 */           Trace.traceData(this, "Setting username = " + username, null);
/*      */         }
/*  409 */         this.mqProperties.put("userID", username);
/*      */ 
/*      */         
/*  412 */         if (Trace.isOn) {
/*  413 */           Trace.traceData(this, "Setting AuthenticateBindings to true", null);
/*      */         }
/*  415 */         this.mqProperties.put("Bindings Authentication", Boolean.valueOf(true));
/*      */       } else {
/*      */         
/*  418 */         if (Trace.isOn) {
/*  419 */           Trace.traceData(this, "Setting username to blank", null);
/*      */         }
/*  421 */         this.mqProperties.put("userID", "");
/*      */         
/*  423 */         if (Trace.isOn) {
/*  424 */           Trace.traceData(this, "Setting AuthenticateBindings to false", null);
/*      */         }
/*  426 */         this.mqProperties.put("Bindings Authentication", Boolean.valueOf(false));
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  432 */       if (password != null) {
/*  433 */         if (Trace.isOn) {
/*  434 */           Trace.traceData(this, "Setting password", null);
/*      */         }
/*  436 */         this.mqProperties.put("password", password);
/*      */       } else {
/*      */         
/*  439 */         if (Trace.isOn) {
/*  440 */           Trace.traceData(this, "Setting password to blank", null);
/*      */         }
/*  442 */         this.mqProperties.put("password", "");
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  447 */       String appName = getStringProperty("XMSC_WMQ_APPNAME");
/*  448 */       if (appName != null) {
/*  449 */         this.mqProperties.put("APPNAME", appName);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  456 */       String ccdt = getStringProperty("XMSC_WMQ_CCDTURL");
/*  457 */       String channel = getStringProperty("XMSC_WMQ_CHANNEL");
/*  458 */       if (ccdt != null && channel != null && channel.length() > 0)
/*      */       {
/*  460 */         if (!channel.equals("SYSTEM.DEF.SVRCONN")) {
/*  461 */           MQException mqe = new MQException(2, 2423, this);
/*  462 */           JMSException jMSException = ConfigEnvironment.newException("MQJMS2005", 
/*  463 */               getStringProperty("XMSC_WMQ_QUEUE_MANAGER"));
/*  464 */           jMSException.setLinkedException((Exception)mqe);
/*      */           
/*  466 */           if (Trace.isOn) {
/*  467 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>(String,String,JmsPropertyContext)", (Throwable)jMSException, 3);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  473 */           throw jMSException;
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/*  478 */       int transportType = getIntProperty("XMSC_WMQ_CONNECTION_MODE");
/*  479 */       switch (transportType) {
/*      */         
/*      */         case 0:
/*  482 */           this.mqProperties.put("transport", "MQSeries Bindings");
/*      */ 
/*      */           
/*  485 */           sslCipherSuite = getStringProperty("XMSC_WMQ_SSL_CIPHER_SUITE");
/*  486 */           if (sslCipherSuite != null && sslCipherSuite.length() > 0) {
/*  487 */             this.mqProperties.put("SSL Cipher Suite", sslCipherSuite);
/*      */           }
/*      */           
/*  490 */           connTag = getBytesProperty("XMSC_WMQ_CONNECTION_TAG");
/*  491 */           if (connTag != null) {
/*  492 */             this.mqProperties.put("ConnTag Property", connTag);
/*      */           }
/*  494 */           connOptions = getIntProperty("XMSC_WMQ_CONNECT_OPTIONS");
/*  495 */           if (connOptions != 0) {
/*  496 */             this.mqProperties.put("connectOptions", Integer.valueOf(connOptions));
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case 3:
/*  502 */           this.mqProperties.put("transport", "MQJD");
/*      */           break;
/*      */         
/*      */         case 1:
/*  506 */           this.mqProperties.put("transport", "MQSeries");
/*  507 */           this.mqProperties.put("CCSID", Integer.valueOf(getIntProperty("XMSC_WMQ_QMGR_CCSID")));
/*  508 */           if (getStringProperty("XMSC_WMQ_CCDTURL") != null) {
/*      */             try {
/*  510 */               this.mqProperties.put("CCDT URL", new URL(getStringProperty("XMSC_WMQ_CCDTURL")));
/*      */             }
/*  512 */             catch (MalformedURLException e) {
/*      */               
/*  514 */               if (Trace.isOn) {
/*  515 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>(String,String,JmsPropertyContext)", e, 1);
/*      */               }
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  523 */           this.mqProperties.put("channel", getStringProperty("XMSC_WMQ_CHANNEL"));
/*      */           
/*  525 */           connName = (WMQConnectionNameList)getObjectProperty("XMSC_WMQ_CONNECTION_NAME_LIST_INT");
/*      */           
/*  527 */           this.mqProperties.put("hostname", ((WMQConnectionName)connName.get(0)).getHostname());
/*      */           
/*  529 */           this.mqProperties.put("port", Integer.valueOf(((WMQConnectionName)connName.get(0)).getPort()));
/*  530 */           this.mqProperties.put("KeyResetCount", Integer.valueOf(getIntProperty("XMSC_WMQ_SSL_KEY_RESETCOUNT")));
/*  531 */           this.mqProperties.put("SSL Fips Required", Boolean.valueOf(getBooleanProperty("XMSC_WMQ_SSL_FIPS_REQUIRED")));
/*      */ 
/*      */           
/*  534 */           sslCSuite = getStringProperty("XMSC_WMQ_SSL_CIPHER_SUITE");
/*  535 */           if (sslCSuite != null && sslCSuite.length() > 0) {
/*  536 */             this.mqProperties.put("SSL Cipher Suite", sslCSuite);
/*      */           }
/*      */           
/*  539 */           peerName = getObjectProperty("XMSC_WMQ_SSL_PEER_NAME");
/*  540 */           if (peerName != null) {
/*  541 */             this.mqProperties.put("SSL Peer Name", peerName);
/*      */           }
/*      */           
/*  544 */           sslCertStore = getSSLCertStores((JmsPropertyContext)this);
/*  545 */           if (sslCertStore != null) {
/*  546 */             this.mqProperties.put("SSL CertStores", sslCertStore);
/*      */           }
/*      */           
/*  549 */           sslSocketFactory = getObjectProperty("XMSC_WMQ_SSL_SOCKET_FACTORY");
/*  550 */           if (sslSocketFactory != null)
/*      */           {
/*  552 */             if (sslSocketFactory instanceof javax.net.ssl.SSLSocketFactory) {
/*  553 */               this.mqProperties.put("SSL Socket Factory", sslSocketFactory);
/*      */             } else {
/*      */               
/*  556 */               JMSException traceRet2 = ConfigEnvironment.newException("MQJMS1056", "sslSocketFactory");
/*      */               
/*  558 */               if (Trace.isOn) {
/*  559 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>(String,String,JmsPropertyContext)", (Throwable)traceRet2, 4);
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  565 */               throw traceRet2;
/*      */             } 
/*      */           }
/*      */ 
/*      */           
/*  570 */           localAddress = getStringProperty("XMSC_WMQ_LOCAL_ADDRESS");
/*  571 */           if (localAddress != null && !localAddress.equals(""))
/*      */           {
/*  573 */             this.mqProperties.put("Local Address Property", localAddress);
/*      */           }
/*      */ 
/*      */           
/*  577 */           hdrCompList = getHdrCompList((JmsPropertyContext)this);
/*  578 */           if (hdrCompList != null) {
/*  579 */             this.mqProperties.put("Header Compression Property", hdrCompList);
/*      */           }
/*  581 */           msgCompList = getMsgCompList((JmsPropertyContext)this);
/*  582 */           if (msgCompList != null) {
/*  583 */             this.mqProperties.put("ProviderMessage Compression Property", msgCompList);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  588 */           ct = getBytesProperty("XMSC_WMQ_CONNECTION_TAG");
/*  589 */           if (ct != null && !byteArraysEqual(ct, CMQC.MQCT_NONE)) {
/*  590 */             if (Trace.isOn) {
/*  591 */               Trace.traceData(this, "connTag is not null", null);
/*  592 */               Trace.traceData(this, "connTag contents = '" + Utils.bytesToHex(ct) + "'", null);
/*      */             } 
/*      */             
/*  595 */             this.mqProperties.put("ConnTag Property", ct);
/*      */           } 
/*      */           
/*  598 */           connOpt = getIntProperty("XMSC_WMQ_CONNECT_OPTIONS");
/*  599 */           if (connOpt != 0) {
/*  600 */             this.mqProperties.put("connectOptions", Integer.valueOf(connOpt));
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  607 */           supportThreeExits();
/*      */           break;
/*      */         
/*      */         default:
/*  611 */           je = ConfigEnvironment.newException("MQJMS1008", String.valueOf(transportType));
/*      */           
/*  613 */           if (Trace.isOn) {
/*  614 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>(String,String,JmsPropertyContext)", (Throwable)je, 5);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  620 */           throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  625 */       Long tmp = AccessController.<Long>doPrivileged(new PrivilegedAction<Long>()
/*      */           {
/*      */             public Long run()
/*      */             {
/*  629 */               if (Trace.isOn) {
/*  630 */                 Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>run()");
/*      */               }
/*      */               try {
/*  633 */                 Long traceRet1 = Long.getLong("com.ibm.mq.jms.tuning.eoqTimeout");
/*      */                 
/*  635 */                 if (Trace.isOn) {
/*  636 */                   Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>run()", traceRet1, 1);
/*      */                 }
/*  638 */                 return traceRet1;
/*      */               }
/*  640 */               catch (AccessControlException ace) {
/*  641 */                 if (Trace.isOn) {
/*  642 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>run()", ace);
/*  643 */                   Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>run()", null, 2);
/*      */                 } 
/*  645 */                 return null;
/*      */               } 
/*      */             }
/*      */           });
/*      */       
/*  650 */       if (null != tmp) {
/*  651 */         if (Trace.isOn) {
/*  652 */           Trace.traceData(this, "Setting eoqTimeout from System property = " + tmp.longValue(), null);
/*      */         }
/*  654 */         this.eoqTimeout = tmp.longValue();
/*      */       } else {
/*      */         
/*  657 */         if (Trace.isOn) {
/*  658 */           Trace.traceData(this, "cannot read eoqTimeout System property. Using default = -1", null);
/*      */         }
/*  660 */         this.eoqTimeout = -1L;
/*      */       } 
/*      */ 
/*      */       
/*  664 */       PropertyStore.register(HEADERSIZE_PROPERTY, -1L, Long.valueOf(-1L), Long.valueOf(2147483647L));
/*  665 */       setHeaderDataSize(PropertyStore.getLongPropertyObject(HEADERSIZE_PROPERTY).intValue());
/*      */       
/*  667 */       PropertyStore.register(SWEEPTIME_PROPERTY, 30000L, Long.valueOf(0L), Long.valueOf(Long.MAX_VALUE));
/*  668 */       setSweepTime(PropertyStore.getLongPropertyObject(SWEEPTIME_PROPERTY).longValue());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  675 */       this.qmgrName = getStringProperty("XMSC_WMQ_QUEUE_MANAGER");
/*      */       
/*  677 */       this.temporaryModelQ = getStringProperty("XMSC_WMQ_TEMPORARY_MODEL");
/*  678 */       this.messageRetention = getIntProperty("XMSC_WMQ_MESSAGE_RETENTION");
/*  679 */       this.tempQPrefix = getStringProperty("XMSC_WMQ_TEMP_Q_PREFIX");
/*  680 */       this.brkVersion = getIntProperty("brokerVersion");
/*  681 */       this.messageSelection = getIntProperty("XMSC_WMQ_MESSAGE_SELECTION");
/*  682 */       this.pubAckInterval = getIntProperty("XMSC_WMQ_PUB_ACK_INTERVAL");
/*  683 */       this.sparseSubscriptions = getBooleanProperty("XMSC_WMQ_SPARSE_SUBSCRIPTIONS");
/*  684 */       this.mapNameStyle = getBooleanProperty("XMSC_WMQ_MAP_NAME_STYLE");
/*      */       
/*  686 */       if (!(this instanceof MQXAConnection)) {
/*  687 */         this.receiveIsolation = getIntProperty("XMSC_WMQ_RECEIVE_ISOLATION");
/*  688 */         this.outcomeNotification = getBooleanProperty("XMSC_WMQ_OUTCOME_NOTIFICATION");
/*  689 */         this.processDuration = getIntProperty("XMSC_WMQ_PROCESS_DURATION");
/*  690 */         this.optimisticPublication = getBooleanProperty("XMSC_WMQ_OPT_PUB");
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  695 */       int fiq = getIntProperty("failIfQuiesce");
/*  696 */       if (fiq == 1) {
/*  697 */         this.tmpQOpenOptions |= 0x2000;
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
/*      */ 
/*      */       
/*  712 */       String sparseSubsProperty = "MQJMS_SPARSE_SUBS";
/*  713 */       PropertyStore.register(sparseSubsProperty, false);
/*  714 */       boolean ssProp = PropertyStore.getBooleanPropertyObject(sparseSubsProperty).booleanValue();
/*      */       
/*  716 */       if (ssProp) {
/*  717 */         if (Trace.isOn) {
/*  718 */           Trace.traceData(this, "sparseSubscriptions set by system property", null);
/*      */         }
/*  720 */         this.sparseSubscriptions = true;
/*  721 */         setBooleanProperty("XMSC_WMQ_SPARSE_SUBSCRIPTIONS", this.sparseSubscriptions);
/*      */       } 
/*      */ 
/*      */       
/*  725 */       if (this instanceof MQXAConnection) {
/*  726 */         this.initialQm = createQMXA();
/*  727 */         setResolvedQMName();
/*      */ 
/*      */         
/*      */         try {
/*  731 */           Hconn hconn = this.initialQm.getHconn();
/*  732 */           if (hconn.isConnToZos() && hconn.getQmgrAdvancedCapability() == QmgrAdvancedCapability.SUPPORTED) {
/*  733 */             zosClientAllowed = true;
/*      */           }
/*      */         }
/*  736 */         catch (JmqiException e) {
/*  737 */           jmqiException = e;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*  745 */           this.initialQm.disconnect();
/*      */         }
/*  747 */         catch (Exception e) {
/*  748 */           if (Trace.isOn) {
/*  749 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>(String,String,JmsPropertyContext)", e, 2);
/*      */ 
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */         finally {
/*      */ 
/*      */           
/*  758 */           if (Trace.isOn) {
/*  759 */             Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>(String,String,JmsPropertyContext)");
/*      */           }
/*  761 */           this.initialQm = null;
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  770 */         if (transportType == 0 && CSSystem.currentPlatform() == CSSystem.Platform.OS_ZSERIES) {
/*      */           
/*      */           try {
/*      */             
/*  774 */             JmqiSP spi = (JmqiSP)MQSESSION.getJmqiEnv().getMQI(0, 0);
/*  775 */             if (spi.isCICS()) {
/*  776 */               JMSException jMSException = ConfigEnvironment.newException("MQJMS6406");
/*  777 */               if (Trace.isOn) {
/*  778 */                 Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "<init>", (Throwable)jMSException, 1);
/*      */               }
/*      */               
/*  781 */               throw jMSException;
/*      */             } 
/*  783 */             if (spi.isIMS()) {
/*  784 */               JMSException jMSException = ConfigEnvironment.newException("MQJMS6407");
/*  785 */               if (Trace.isOn) {
/*  786 */                 Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "<init>", (Throwable)jMSException, 2);
/*      */               }
/*      */               
/*  789 */               throw jMSException;
/*      */             }
/*      */           
/*  792 */           } catch (JmqiException e) {
/*  793 */             if (Trace.isOn) {
/*  794 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>", (Throwable)e, 3);
/*      */             }
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/*  800 */         this.initialQm = createQMNonXA();
/*  801 */         setResolvedQMName();
/*      */         
/*      */         try {
/*  804 */           Hconn hconn = this.initialQm.getHconn();
/*  805 */           if (hconn.isConnToZos() && hconn.getQmgrAdvancedCapability() == QmgrAdvancedCapability.SUPPORTED) {
/*  806 */             zosClientAllowed = true;
/*      */           }
/*      */         }
/*  809 */         catch (JmqiException e) {
/*  810 */           jmqiException = e;
/*      */         } 
/*      */         
/*  813 */         short adminObjectType = this.jmsPropertyContext.getShortProperty("XMSC_ADMIN_OBJECT_TYPE");
/*  814 */         if ((adminObjectType & 0x100) != 0) {
/*      */ 
/*      */           
/*  817 */           MQRRSQueueManager rrsqm = new MQRRSQueueManager(this.initialQm);
/*      */           try {
/*  819 */             rrsqm.honourRRS();
/*      */           }
/*  821 */           catch (MQException mqe) {
/*  822 */             if (Trace.isOn) {
/*  823 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>(String,String,JmsPropertyContext)", (Throwable)mqe, 4);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  829 */             JMSException jMSException = ConfigEnvironment.newException("MQJMS2005", this.qmgrName);
/*  830 */             jMSException.setLinkedException((Exception)mqe);
/*      */             try {
/*  832 */               this.initialQm.disconnect();
/*      */             }
/*  834 */             catch (MQException mqe2) {
/*  835 */               if (Trace.isOn) {
/*  836 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>(String,String,JmsPropertyContext)", (Throwable)mqe2, 5);
/*      */               }
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  843 */             this.initialQm = null;
/*  844 */             if (Trace.isOn) {
/*  845 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>(String,String,JmsPropertyContext)", (Throwable)jMSException, 6);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  851 */             throw jMSException;
/*      */           }
/*      */         
/*      */         } 
/*      */       } 
/*  856 */     } catch (JMSException je) {
/*  857 */       if (Trace.isOn) {
/*  858 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>(String,String,JmsPropertyContext)", (Throwable)je, 7);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  864 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/*  868 */     if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && 
/*  869 */       getIntProperty("XMSC_WMQ_CONNECTION_MODE") == 1)
/*      */     {
/*  871 */       if (!WASSupport.getWASSupport().isWASCommonServicesPresent() && !zosClientAllowed) {
/*      */ 
/*      */         
/*  874 */         if (this.initialQm != null) {
/*      */           try {
/*  876 */             this.initialQm.disconnect();
/*      */           }
/*  878 */           catch (MQException e) {
/*  879 */             if (Trace.isOn) {
/*  880 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>", (Throwable)e, 6);
/*      */             }
/*      */           } 
/*      */           
/*  884 */           this.initialQm = null;
/*      */         } 
/*      */ 
/*      */         
/*  888 */         Log.log(this, "<init>", "JMSFMQ0005", null);
/*      */ 
/*      */         
/*  891 */         JMSException je = (JMSException)NLSServices.createException("JMSFMQ0005", null);
/*      */         
/*  893 */         if (jmqiException != null) {
/*  894 */           je.setLinkedException((Exception)jmqiException);
/*      */         }
/*      */         
/*  897 */         if (Trace.isOn) {
/*  898 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>(String,String,JmsPropertyContext)", (Throwable)je, 8);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  904 */         throw je;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  909 */     if (Trace.isOn) {
/*  910 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "<init>(String,String,JmsPropertyContext)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private synchronized void setResolvedQMName() throws JMSException {
/*  917 */     if (Trace.isOn) {
/*  918 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setResolvedQMName()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  924 */       if (this.initialQm != null) {
/*  925 */         Hconn hConn = this.initialQm.getHconn();
/*      */         
/*  927 */         assert hConn != null;
/*      */         
/*  929 */         this.qmName = hConn.getName();
/*  930 */         if (this.qmName != null) {
/*  931 */           this.qmName = this.qmName.trim();
/*      */         }
/*  933 */         setStringProperty("XMSC_WMQ_RESOLVED_QUEUE_MANAGER", this.qmName);
/*      */ 
/*      */         
/*  936 */         setIntProperty("XMSC_WMQ_COMMAND_LEVEL", hConn.getCmdLevel());
/*      */       }
/*      */     
/*  939 */     } catch (JmqiException e) {
/*  940 */       if (Trace.isOn) {
/*  941 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setResolvedQMName()", (Throwable)e);
/*      */       }
/*      */ 
/*      */       
/*  945 */       HashMap<String, Object> info = new HashMap<>();
/*      */       
/*  947 */       info.put("reason", Integer.valueOf(e.getReason()));
/*  948 */       info.put("compcode", Integer.valueOf(e.getCompCode()));
/*  949 */       info.put("hconn", this.initialQm.getHconn());
/*  950 */       Trace.ffst(this, "<init>", "XO008002", info, JMSException.class);
/*      */     } 
/*  952 */     if (Trace.isOn) {
/*  953 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setResolvedQMName()");
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
/*      */   private void addBrowser(MQConnectionBrowser browser) throws JMSException {
/*  966 */     if (Trace.isOn) {
/*  967 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "addBrowser(MQConnectionBrowser)", new Object[] { browser });
/*      */     }
/*      */     
/*  970 */     synchronized (this) {
/*      */       try {
/*  972 */         this.browsers.addElement(browser);
/*  973 */         switch (this.state) {
/*      */           case 1:
/*  975 */             if (Trace.isOn) {
/*  976 */               Trace.traceData(this, "ProviderConnection started. Starting browser.", null);
/*      */             }
/*  978 */             browser.startInternal();
/*  979 */             browser.activateQueueAgent();
/*      */             break;
/*      */           
/*      */           case 0:
/*      */           case 2:
/*  984 */             if (Trace.isOn) {
/*  985 */               Trace.traceData(this, "ProviderConnection not started. Leaving browser unstarted.", null);
/*      */             }
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  993 */       } catch (JMSException je) {
/*  994 */         if (Trace.isOn) {
/*  995 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "addBrowser(MQConnectionBrowser)", (Throwable)je);
/*      */         }
/*      */         
/*  998 */         this.browsers.removeElement(browser);
/*  999 */         if (Trace.isOn) {
/* 1000 */           Trace.traceData(this, "ConnectionBrowser start failed", null);
/*      */         }
/* 1002 */         if (Trace.isOn) {
/* 1003 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "addBrowser(MQConnectionBrowser)", (Throwable)je);
/*      */         }
/*      */         
/* 1006 */         throw je;
/*      */       } 
/*      */     } 
/* 1009 */     if (Trace.isOn) {
/* 1010 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "addBrowser(MQConnectionBrowser)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addSession(MQSession session) {
/* 1018 */     if (Trace.isOn) {
/* 1019 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "addSession(MQSession)", new Object[] { session });
/*      */     }
/*      */     
/* 1022 */     this.sessions.addElement(session);
/*      */ 
/*      */ 
/*      */     
/* 1026 */     if (this.sessions.size() % 250 == 0) {
/*      */ 
/*      */       
/* 1029 */       HashMap<String, Object> ffstData = new HashMap<>();
/* 1030 */       ffstData.put("sessions Vector size", Integer.toString(this.sessions.size()));
/* 1031 */       ffstData.put("current thread", Thread.currentThread().getName());
/* 1032 */       Trace.ffst(this, "addSession()", "XO00800B", ffstData, null);
/*      */     } 
/* 1034 */     if (Trace.isOn) {
/* 1035 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "addSession(MQSession)");
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
/*      */   public final synchronized void close() throws JMSException {
/* 1053 */     if (Trace.isOn) {
/* 1054 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "close()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1060 */       if (this.qServices != null && 
/* 1061 */         this.tempQqm != null) {
/*      */         
/*      */         try {
/*      */ 
/*      */ 
/*      */           
/* 1067 */           this.tempQqm.disconnect();
/* 1068 */           this.tempQqm = null;
/*      */         }
/* 1070 */         catch (Exception e) {
/* 1071 */           if (Trace.isOn) {
/* 1072 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "close()", e, 1);
/*      */           }
/*      */           
/* 1075 */           JMSException je = ConfigEnvironment.newException("MQJMS2003");
/* 1076 */           je.setLinkedException(e);
/* 1077 */           if (Trace.isOn) {
/* 1078 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "close()", (Throwable)je, 1);
/*      */           }
/*      */           
/* 1081 */           throw je;
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1090 */       if (this.cbBrokerSession != null) {
/* 1091 */         this.cbBrokerSession.close(true);
/* 1092 */         this.cbBrokerSession = null;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1097 */       if (this.state == 1 || this.state == 0) {
/*      */         
/* 1099 */         this.state = 2;
/*      */         
/* 1101 */         notifyBrowsers();
/* 1102 */         notifySessions();
/*      */       }
/* 1104 */       else if (this.state != 2) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1112 */         HashMap<String, Object> ffstData = new HashMap<>();
/* 1113 */         ffstData.put("WMQJMS Exception", ConfigEnvironment.newException("MQJMS1005", 
/* 1114 */               String.valueOf(this.state), "STATE_CLOSED"));
/*      */         
/* 1116 */         Trace.ffst(this, "close()", "XO008003", ffstData, JMSException.class);
/*      */         
/* 1118 */         JMSException je = ConfigEnvironment.newException("MQJMS1005", 
/* 1119 */             String.valueOf(this.state), "STATE_CLOSED");
/*      */         
/* 1121 */         if (Trace.isOn) {
/* 1122 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "close()", (Throwable)je, 2);
/*      */         }
/*      */         
/* 1125 */         throw je;
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
/* 1136 */       if (this.initialQm != null) {
/*      */         
/*      */         try {
/* 1139 */           this.initialQm.disconnect();
/*      */         }
/* 1141 */         catch (MQException mqe) {
/* 1142 */           if (Trace.isOn) {
/* 1143 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "close()", (Throwable)mqe, 2);
/*      */           }
/*      */         } 
/*      */         
/* 1147 */         this.initialQm = null;
/*      */       } 
/*      */       
/* 1150 */       if (this.qServices != null) {
/* 1151 */         this.qServices = null;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1156 */       if (this.psServices != null) {
/* 1157 */         this.subscriptionEngine.close();
/* 1158 */         this.psServices = null;
/*      */       }
/*      */     
/*      */     }
/* 1162 */     catch (JMSException je) {
/* 1163 */       if (Trace.isOn) {
/* 1164 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "close()", (Throwable)je, 3);
/*      */       }
/*      */       
/* 1167 */       if (Trace.isOn) {
/* 1168 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "close()", (Throwable)je, 3);
/*      */       }
/*      */       
/* 1171 */       throw je;
/*      */     } 
/*      */     
/* 1174 */     if (Trace.isOn) {
/* 1175 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "close()");
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
/*      */   private ProviderConnectionBrowser createConnectionBrowser(WMQDestination dest, String selector, ProviderMessageReferenceHandler mrh, int quantityHintP) throws JMSException {
/* 1199 */     if (Trace.isOn) {
/* 1200 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createConnectionBrowser(WMQDestination,String,ProviderMessageReferenceHandler,int)", new Object[] { dest, selector, mrh, 
/*      */             
/* 1202 */             Integer.valueOf(quantityHintP) });
/*      */     }
/*      */     
/* 1205 */     int quantityHint = quantityHintP;
/*      */ 
/*      */     
/*      */     try {
/* 1209 */       if (dest == null) {
/* 1210 */         String key = "MQJMS0003";
/* 1211 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 1212 */         InvalidDestinationException je = new InvalidDestinationException(msg, key);
/* 1213 */         if (Trace.isOn) {
/* 1214 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createConnectionBrowser(WMQDestination,String,ProviderMessageReferenceHandler,int)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */         
/* 1218 */         throw je;
/*      */       } 
/*      */       
/* 1221 */       if (dest.isTopic()) {
/*      */ 
/*      */ 
/*      */         
/* 1225 */         boolean mustInitialise = (this.servicesMgr == null || !this.servicesMgr.isPubSub());
/*      */         
/* 1227 */         if (mustInitialise)
/*      */         {
/* 1229 */           if (this.sessions != null && this.sessions.size() > 0) {
/* 1230 */             ((MQSession)this.sessions.elementAt(0)).addPubSubServices();
/*      */           }
/*      */           else {
/*      */             
/* 1234 */             ProviderSession s = createSessionInternal(false, 1, 0);
/* 1235 */             ((MQSession)s).addPubSubServices();
/* 1236 */             s.close(true);
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1247 */         if (Trace.isOn) {
/* 1248 */           Trace.traceData(this, "Checking queue is valid and could be accessed", null);
/*      */         }
/* 1250 */         dest.checkAccess(this);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1258 */       if (this.headerDataSize != -1) {
/* 1259 */         quantityHint = 1;
/* 1260 */         if (Trace.isOn) {
/* 1261 */           Trace.traceData(this, "quantityHint set to HEADER_DATA, as headerDataSize set to", Integer.valueOf(this.headerDataSize));
/*      */         }
/*      */       } 
/*      */       
/* 1265 */       if (Trace.isOn) {
/* 1266 */         Trace.traceData(this, "Creating new ConnectionBrowser", null);
/*      */       }
/*      */       
/* 1269 */       MQConnectionBrowser browser = new MQConnectionBrowser(this, dest, selector, mrh, quantityHint);
/*      */       
/* 1271 */       if (Trace.isOn) {
/* 1272 */         Trace.traceData(this, "Adding Browser to connection's list", null);
/*      */       }
/*      */       
/* 1275 */       addBrowser(browser);
/*      */       
/* 1277 */       if (Trace.isOn) {
/* 1278 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createConnectionBrowser(WMQDestination,String,ProviderMessageReferenceHandler,int)", browser);
/*      */       }
/*      */ 
/*      */       
/* 1282 */       return browser;
/*      */     }
/* 1284 */     catch (JMSException je) {
/* 1285 */       if (Trace.isOn) {
/* 1286 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createConnectionBrowser(WMQDestination,String,ProviderMessageReferenceHandler,int)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 1290 */       if (Trace.isOn) {
/* 1291 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createConnectionBrowser(WMQDestination,String,ProviderMessageReferenceHandler,int)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */       
/* 1295 */       throw je;
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
/*      */   public ProviderConnectionBrowser createConnectionBrowser(ProviderDestination destination, String selector, ProviderMessageReferenceHandler mrh, int quantityHint) throws JMSException {
/* 1316 */     if (Trace.isOn) {
/* 1317 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createConnectionBrowser(ProviderDestination,String,ProviderMessageReferenceHandler,int)", new Object[] { destination, selector, mrh, 
/*      */             
/* 1319 */             Integer.valueOf(quantityHint) });
/*      */     }
/*      */     
/* 1322 */     ProviderConnectionBrowser connectionBrowser = null;
/* 1323 */     if (destination.isQueue() || destination.isTopic()) {
/* 1324 */       WMQDestination d = (WMQDestination)destination;
/* 1325 */       connectionBrowser = createConnectionBrowser(d, selector, mrh, quantityHint);
/*      */     } else {
/*      */       
/* 1328 */       String key = "MQJMS0003";
/* 1329 */       String msg = ConfigEnvironment.getErrorMessage(key);
/* 1330 */       InvalidDestinationException je = new InvalidDestinationException(msg, key);
/* 1331 */       if (Trace.isOn) {
/* 1332 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createConnectionBrowser(ProviderDestination,String,ProviderMessageReferenceHandler,int)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 1336 */       throw je;
/*      */     } 
/*      */     
/* 1339 */     if (Trace.isOn) {
/* 1340 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createConnectionBrowser(ProviderDestination,String,ProviderMessageReferenceHandler,int)", connectionBrowser);
/*      */     }
/*      */ 
/*      */     
/* 1344 */     return connectionBrowser;
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
/*      */   public ProviderConnectionBrowser createDurableConnectionBrowser(ProviderDestination destination, String clientid, String subName, String selector, ProviderMessageReferenceHandler mrh, int quantityHint, boolean noLocal) throws JMSException {
/* 1374 */     if (Trace.isOn) {
/* 1375 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createDurableConnectionBrowser(ProviderDestination,String,String,String,ProviderMessageReferenceHandler,int,boolean)", new Object[] { destination, clientid, subName, selector, mrh, 
/*      */ 
/*      */             
/* 1378 */             Integer.valueOf(quantityHint), Boolean.valueOf(noLocal) });
/*      */     }
/*      */     
/* 1381 */     MQConnectionBrowser connectionBrowser = null;
/* 1382 */     if (destination.isTopic()) {
/*      */       
/* 1384 */       boolean mustInitialise = (this.servicesMgr == null || !this.servicesMgr.isPubSub());
/*      */       
/* 1386 */       if (mustInitialise)
/*      */       {
/* 1388 */         if (this.sessions != null && this.sessions.size() > 0) {
/* 1389 */           ((MQSession)this.sessions.elementAt(0)).addPubSubServices();
/*      */         }
/*      */         else {
/*      */           
/* 1393 */           ProviderSession s = createSessionInternal(false, 1, 0);
/* 1394 */           ((MQSession)s).addPubSubServices();
/* 1395 */           s.close(true);
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 1400 */       if (!((WMQDestination)destination).isTopic()) {
/* 1401 */         String key = "MQJMS0003";
/* 1402 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 1403 */         InvalidDestinationException je = new InvalidDestinationException(msg, key);
/* 1404 */         if (Trace.isOn) {
/* 1405 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createDurableConnectionBrowser(ProviderDestination,String,String,String,ProviderMessageReferenceHandler,int,boolean)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */         
/* 1409 */         throw je;
/*      */       } 
/* 1411 */       if (((WMQDestination)destination).isTemporary()) {
/*      */ 
/*      */         
/* 1414 */         JMSException je = ConfigEnvironment.newException("MQJMS0003");
/* 1415 */         if (Trace.isOn) {
/* 1416 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createDurableConnectionBrowser(ProviderDestination,String,String,String,ProviderMessageReferenceHandler,int,boolean)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */         
/* 1420 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1425 */       if (subName == null) {
/*      */         
/* 1427 */         JMSException je = ConfigEnvironment.newException("MQJMS3039");
/* 1428 */         if (Trace.isOn) {
/* 1429 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createDurableConnectionBrowser(ProviderDestination,String,String,String,ProviderMessageReferenceHandler,int,boolean)", (Throwable)je, 3);
/*      */         }
/*      */ 
/*      */         
/* 1433 */         throw je;
/*      */       } 
/*      */       
/* 1436 */       connectionBrowser = new MQConnectionBrowser(this, (WMQDestination)destination, selector, mrh, quantityHint, subName);
/* 1437 */       addBrowser(connectionBrowser);
/*      */     } 
/*      */     
/* 1440 */     if (Trace.isOn) {
/* 1441 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createDurableConnectionBrowser(ProviderDestination,String,String,String,ProviderMessageReferenceHandler,int,boolean)", connectionBrowser);
/*      */     }
/*      */ 
/*      */     
/* 1445 */     return connectionBrowser;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQQueueManager createQM() throws JMSException {
/*      */     try {
/* 1457 */       MQQueueManager qm1 = null;
/*      */       
/* 1459 */       if (Trace.isOn) {
/* 1460 */         Trace.traceData(this, "qmgrName = '" + this.qmgrName + "'", null);
/* 1461 */         if (this.mqProperties != null) {
/* 1462 */           Trace.traceData(this, "mqProperties:", null);
/* 1463 */           for (Map.Entry<String, Object> property : this.mqProperties.entrySet()) {
/* 1464 */             String k = property.getKey();
/*      */             
/* 1466 */             if (k != null) {
/* 1467 */               if (k.equals("password")) {
/* 1468 */                 Trace.traceData(this, k + " = '********'", null);
/*      */                 continue;
/*      */               } 
/* 1471 */               Object ob = property.getValue();
/* 1472 */               String v = ob.toString();
/* 1473 */               Trace.traceData(this, k + " = '" + v + "'", null);
/*      */             }
/*      */           
/*      */           } 
/*      */         } else {
/*      */           
/* 1479 */           Trace.traceData(this, "mqProperties = null", null);
/*      */         } 
/*      */         
/* 1482 */         Trace.traceData(this, "runningInWS = " + MQEnvironment.runningInWS(), null);
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 1487 */         qm1 = new MQQueueManager(this.qmgrName, this.mqProperties);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1498 */         MQQueueManagerEventListener el = new MQQueueManagerEventListener()
/*      */           {
/*      */             public void onConnectionBrokenException(MQQueueManager qm2, MQException mqe) {
/* 1501 */               if (!MQConnection.this.qmConnectionBrokenEventRecieved) {
/* 1502 */                 MQConnection.this.qmConnectionBrokenEventRecieved = true;
/* 1503 */                 JMSException je = ConfigEnvironment.newException("MQJMS1107");
/* 1504 */                 je.setLinkedException((Exception)mqe);
/* 1505 */                 MQConnection.this.deliverException(je, true);
/*      */               } 
/*      */             }
/*      */           };
/*      */ 
/*      */ 
/*      */         
/* 1512 */         qm1.setEventListener(el);
/*      */       
/*      */       }
/* 1515 */       catch (MQException e) {
/*      */         String key, msg; JMSSecurityException se; String skey, smsg; JMSSecurityException sse; Object xaReqP; boolean xaReq;
/* 1517 */         switch (e.reasonCode) {
/*      */ 
/*      */           
/*      */           case 2035:
/* 1521 */             key = "MQJMS2013";
/* 1522 */             msg = ConfigEnvironment.getErrorMessage(key);
/* 1523 */             se = new JMSSecurityException(msg, key);
/* 1524 */             se.setLinkedException((Exception)e);
/* 1525 */             se.initCause((Throwable)e);
/* 1526 */             throw se;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 2063:
/* 1533 */             skey = "MQJMS2013";
/* 1534 */             smsg = ConfigEnvironment.getErrorMessage(skey);
/* 1535 */             sse = new JMSSecurityException(smsg, skey);
/* 1536 */             sse.setLinkedException((Exception)e);
/* 1537 */             sse.initCause((Throwable)e);
/* 1538 */             throw sse;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 2012:
/* 1544 */             xaReqP = this.mqProperties.get("XAReq");
/* 1545 */             xaReq = false;
/* 1546 */             if (xaReqP != null && xaReqP instanceof Boolean) {
/* 1547 */               xaReq = ((Boolean)xaReqP).booleanValue();
/*      */             }
/*      */             
/* 1550 */             if (xaReq) {
/*      */ 
/*      */               
/* 1553 */               JMSException jMSException = ConfigEnvironment.newException("MQJMS2014");
/* 1554 */               jMSException.setLinkedException((Exception)e);
/* 1555 */               throw jMSException;
/*      */             } 
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1565 */         String detail = null;
/* 1566 */         String transport = (String)this.mqProperties.get("transport");
/* 1567 */         String hostname = (String)this.mqProperties.get("hostname");
/*      */         
/* 1569 */         if (transport != null && transport.equals("MQSeries")) {
/* 1570 */           if (hostname != null) {
/* 1571 */             detail = hostname + ":" + this.qmgrName;
/*      */           } else {
/*      */             
/* 1574 */             detail = "<null>:" + this.qmgrName;
/*      */           } 
/*      */         } else {
/*      */           
/* 1578 */           detail = this.qmgrName;
/*      */         } 
/*      */         
/* 1581 */         JMSException je = ConfigEnvironment.newException("MQJMS2005", detail);
/*      */         
/* 1583 */         je.setLinkedException((Exception)e);
/* 1584 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1592 */         if (Trace.isOn) {
/* 1593 */           Trace.data(this, "Attempting to query PSMode", null);
/*      */         }
/*      */         
/* 1596 */         int[] selectors = new int[1];
/* 1597 */         selectors[0] = 187;
/*      */         
/* 1599 */         int[] intAttrs = new int[1];
/* 1600 */         qm1.inquire(selectors, intAttrs, (byte[])null);
/*      */         
/* 1602 */         int psMode = intAttrs[0];
/*      */         
/* 1604 */         if (Trace.isOn) {
/* 1605 */           Trace.data(this, "Got PSMode", Integer.valueOf(psMode));
/*      */         }
/* 1607 */         setIntProperty("MQIA_PUBSUB_MODE", psMode);
/*      */       }
/* 1609 */       catch (Throwable t) {
/* 1610 */         if (Trace.isOn) {
/* 1611 */           Trace.data(this, "Exception thrown during inquire. Assuming PSMode is not valid for this QM", t);
/*      */         }
/* 1613 */         setIntProperty("MQIA_PUBSUB_MODE", 1);
/*      */       } 
/*      */       
/* 1616 */       return qm1;
/*      */     }
/* 1618 */     catch (JMSException je) {
/* 1619 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQQueueManager createQMNonXA() throws JMSException {
/* 1628 */     if (Trace.isOn) {
/* 1629 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createQMNonXA()");
/*      */     }
/*      */     
/*      */     try {
/*      */       MQQueueManager result;
/*      */       
/* 1635 */       synchronized (this.mqProperties) {
/* 1636 */         this.mqProperties.put("Group", NON_XAGROUP);
/* 1637 */         this.mqProperties.put("XAReq", Boolean.valueOf(false));
/*      */ 
/*      */ 
/*      */         
/* 1641 */         this.mqProperties.put("Thread affinity", Boolean.valueOf(false));
/*      */ 
/*      */ 
/*      */         
/* 1645 */         this.mqProperties.put("SPI", "SPI_ENABLE");
/*      */ 
/*      */ 
/*      */         
/* 1649 */         this.mqProperties.put("Use QM CCSID", Boolean.valueOf(true));
/*      */         
/* 1651 */         result = createQM();
/*      */       } 
/*      */       
/* 1654 */       if (Trace.isOn) {
/* 1655 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createQMNonXA()", result);
/*      */       }
/*      */       
/* 1658 */       return result;
/*      */     }
/* 1660 */     catch (JMSException je) {
/* 1661 */       if (Trace.isOn) {
/* 1662 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createQMNonXA()", (Throwable)je);
/*      */       }
/*      */       
/* 1665 */       if (Trace.isOn) {
/* 1666 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createQMNonXA()", (Throwable)je);
/*      */       }
/*      */       
/* 1669 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQQueueManager createQMXA() throws JMSException {
/* 1678 */     if (Trace.isOn) {
/* 1679 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createQMXA()");
/*      */     }
/*      */     
/*      */     try {
/*      */       MQQueueManager result;
/* 1684 */       synchronized (this.mqProperties) {
/* 1685 */         this.mqProperties.put("Group", XAGROUP);
/*      */ 
/*      */ 
/*      */         
/* 1689 */         this.mqProperties.put("XAReq", Boolean.valueOf(true));
/*      */ 
/*      */ 
/*      */         
/* 1693 */         this.mqProperties.put("Thread affinity", Boolean.valueOf(true));
/*      */ 
/*      */ 
/*      */         
/* 1697 */         this.mqProperties.put("SPI", "SPI_ENABLE");
/*      */ 
/*      */ 
/*      */         
/* 1701 */         this.mqProperties.put("Use QM CCSID", Boolean.valueOf(true));
/*      */         
/* 1703 */         result = createQM();
/*      */       } 
/* 1705 */       if (Trace.isOn) {
/* 1706 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createQMXA()", result);
/*      */       }
/*      */       
/* 1709 */       return result;
/*      */     }
/* 1711 */     catch (JMSException je) {
/* 1712 */       if (Trace.isOn) {
/* 1713 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createQMXA()", (Throwable)je);
/*      */       }
/*      */       
/* 1716 */       if (Trace.isOn) {
/* 1717 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createQMXA()", (Throwable)je);
/*      */       }
/*      */       
/* 1720 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ProviderSession createSessionInternal(boolean transacted, int acknowledgeMode, int distTranMode) throws JMSException {
/* 1731 */     if (Trace.isOn)
/* 1732 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSessionInternal(boolean,int,int)", new Object[] {
/* 1733 */             Boolean.valueOf(transacted), 
/* 1734 */             Integer.valueOf(acknowledgeMode), Integer.valueOf(distTranMode)
/*      */           }); 
/*      */     try {
/*      */       JMSException je;
/* 1738 */       MQQueueManager qmanager = null;
/*      */ 
/*      */       
/* 1741 */       if (!transacted) {
/* 1742 */         JMSException jMSException; switch (acknowledgeMode) {
/*      */           
/*      */           case 1:
/*      */           case 2:
/*      */           case 3:
/* 1747 */             if (Trace.isOn) {
/* 1748 */               Trace.traceData(this, "acknowledge mode" + acknowledgeMode, null);
/*      */             }
/*      */             break;
/*      */           default:
/* 1752 */             if (Trace.isOn) {
/* 1753 */               Trace.traceData(this, "invalid acknowledge mode" + acknowledgeMode, null);
/*      */             }
/* 1755 */             jMSException = ConfigEnvironment.newException("MQJMS1006", "acknowledge mode", 
/*      */                 
/* 1757 */                 String.valueOf(acknowledgeMode));
/* 1758 */             if (Trace.isOn) {
/* 1759 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSessionInternal(boolean,int,int)", (Throwable)jMSException, 1);
/*      */             }
/*      */             
/* 1762 */             throw jMSException;
/*      */         } 
/*      */ 
/*      */       
/*      */       } 
/* 1767 */       switch (distTranMode) {
/*      */         
/*      */         case 0:
/*      */         case 1:
/*      */         case 2:
/* 1772 */           if (Trace.isOn) {
/* 1773 */             Trace.traceData(this, "distributed transactional mode " + distTranMode, null);
/*      */           }
/*      */           break;
/*      */         default:
/* 1777 */           if (Trace.isOn) {
/* 1778 */             Trace.traceData(this, "Bad distributed transactional mode " + distTranMode, null);
/*      */           }
/*      */           
/* 1781 */           je = ConfigEnvironment.newException("MQJMS1016");
/* 1782 */           if (Trace.isOn) {
/* 1783 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSessionInternal(boolean,int,int)", (Throwable)je, 2);
/*      */           }
/*      */           
/* 1786 */           throw je;
/*      */       } 
/*      */ 
/*      */       
/* 1790 */       if (this.state == 2) {
/* 1791 */         String key = "MQJMS1004";
/* 1792 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 1793 */         IllegalStateException traceRet1 = new IllegalStateException(msg, key);
/* 1794 */         if (Trace.isOn) {
/* 1795 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSessionInternal(boolean,int,int)", (Throwable)traceRet1, 3);
/*      */         }
/*      */         
/* 1798 */         throw traceRet1;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1803 */       qmanager = getInitialQM();
/* 1804 */       if (qmanager == null) {
/* 1805 */         MQRRSQueueManager rrsqm; JMSException jMSException; if (getObjectProperty("XMSC_WMQ_CCDTURL") != null)
/*      */         {
/* 1807 */           this.mqProperties.put("channel", "");
/*      */         }
/* 1809 */         switch (distTranMode) {
/*      */           case 0:
/* 1811 */             qmanager = createQMNonXA();
/*      */             break;
/*      */           case 1:
/* 1814 */             qmanager = createQMXA();
/*      */             break;
/*      */           case 2:
/* 1817 */             qmanager = createQMNonXA();
/*      */ 
/*      */             
/* 1820 */             rrsqm = new MQRRSQueueManager(qmanager);
/*      */             try {
/* 1822 */               rrsqm.honourRRS();
/*      */             }
/* 1824 */             catch (MQException mqe) {
/* 1825 */               if (Trace.isOn) {
/* 1826 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSessionInternal(boolean,int,int)", (Throwable)mqe, 1);
/*      */               }
/*      */               
/* 1829 */               JMSException jMSException1 = ConfigEnvironment.newException("MQJMS2005", this.qmgrName);
/* 1830 */               jMSException1.setLinkedException((Exception)mqe);
/*      */               try {
/* 1832 */                 qmanager.disconnect();
/*      */               }
/* 1834 */               catch (MQException mqe2) {
/* 1835 */                 if (Trace.isOn) {
/* 1836 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSessionInternal(boolean,int,int)", (Throwable)mqe2, 2);
/*      */                 }
/*      */               } 
/*      */ 
/*      */               
/* 1841 */               qmanager = null;
/* 1842 */               if (Trace.isOn) {
/* 1843 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSessionInternal(boolean,int,int)", (Throwable)jMSException1, 4);
/*      */               }
/*      */               
/* 1846 */               throw jMSException1;
/*      */             } 
/*      */             break;
/*      */           
/*      */           default:
/* 1851 */             if (Trace.isOn) {
/* 1852 */               Trace.traceData(this, "Distibuted transactional mode unexpectedly changed! Now " + distTranMode, null);
/*      */             }
/* 1854 */             jMSException = ConfigEnvironment.newException("MQJMS1016");
/* 1855 */             if (Trace.isOn) {
/* 1856 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSessionInternal(boolean,int,int)", (Throwable)jMSException, 5);
/*      */             }
/*      */             
/* 1859 */             throw jMSException;
/*      */         } 
/*      */       
/*      */       } 
/* 1863 */       if (qmanager != null) {
/* 1864 */         setSupportsQAT2(qmanager.getSupportsQAT2());
/*      */       }
/* 1866 */       MQSession session = new MQSession(this, qmanager, transacted, acknowledgeMode);
/* 1867 */       session.setDistTransactionMode(distTranMode);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1872 */       addSession(session);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1879 */       if (this.state == 1) {
/*      */         try {
/* 1881 */           session.start();
/*      */         }
/* 1883 */         catch (JMSException e) {
/* 1884 */           if (Trace.isOn) {
/* 1885 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSessionInternal(boolean,int,int)", (Throwable)e, 3);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1890 */           HashMap<String, Object> ffstData = new HashMap<>();
/* 1891 */           ffstData.put("Exception", e);
/* 1892 */           ffstData.put("Message", "MQJMS1033");
/*      */           
/* 1894 */           Trace.ffst(this, "createSession(boolean, int, int)", "XO008004", ffstData, JMSException.class);
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 1899 */       if (Trace.isOn) {
/* 1900 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSessionInternal(boolean,int,int)", session);
/*      */       }
/*      */       
/* 1903 */       return session;
/*      */     
/*      */     }
/* 1906 */     catch (JMSException je) {
/* 1907 */       if (Trace.isOn) {
/* 1908 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSessionInternal(boolean,int,int)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 1911 */       if (Trace.isOn) {
/* 1912 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSessionInternal(boolean,int,int)", (Throwable)je, 6);
/*      */       }
/*      */       
/* 1915 */       throw je;
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
/*      */   public ProviderSession createSession(JmsPropertyContext jmsPropertyContext1) throws JMSException {
/* 1931 */     if (Trace.isOn) {
/* 1932 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSession(JmsPropertyContext)", new Object[] { jmsPropertyContext1 });
/*      */     }
/*      */     
/* 1935 */     ProviderSession providerSession = null;
/*      */     
/* 1937 */     if (jmsPropertyContext1 != null && jmsPropertyContext1.propertyExists("XMSC_TRANSACTED")) {
/* 1938 */       boolean transacted = jmsPropertyContext1.getBooleanProperty("XMSC_TRANSACTED");
/* 1939 */       int ackMode = jmsPropertyContext1.getIntProperty("XMSC_ACKNOWLEDGE_MODE");
/*      */       
/* 1941 */       short adminObjectType = jmsPropertyContext1.getShortProperty("XMSC_ADMIN_OBJECT_TYPE");
/*      */       
/* 1943 */       int distTranMode = 2;
/* 1944 */       if ((adminObjectType & 0x100) == 0) {
/* 1945 */         distTranMode = 0;
/*      */       }
/*      */       
/* 1948 */       providerSession = createSessionInternal(transacted, ackMode, distTranMode);
/*      */ 
/*      */       
/* 1951 */       if (providerSession != null) {
/* 1952 */         ((MQSession)providerSession).setPropertyContext(jmsPropertyContext1);
/*      */       
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1958 */       HashMap<String, Object> ffstData = new HashMap<>();
/* 1959 */       ffstData.put("jmsPropertyContext1", jmsPropertyContext1);
/* 1960 */       if (jmsPropertyContext1 != null) {
/* 1961 */         ffstData.put("XMSC_TRANSACTED", null);
/*      */       }
/* 1963 */       Trace.ffst(this, "createSession", "XO008001", ffstData, JMSException.class);
/*      */     } 
/*      */     
/* 1966 */     if (Trace.isOn) {
/* 1967 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSession(JmsPropertyContext)", providerSession);
/*      */     }
/*      */     
/* 1970 */     return providerSession;
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
/*      */   private String createUniqueID(MQQueueManager qm1) throws JMSException {
/* 1986 */     if (Trace.isOn) {
/* 1987 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createUniqueID(MQQueueManager)", new Object[] { qm1 });
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
/* 2005 */     String id = null;
/*      */     
/*      */     try {
/* 2008 */       MQQueue tmpQ = qm1.accessQueue("SYSTEM.JMS.REPORT.QUEUE", 8210);
/*      */ 
/*      */       
/* 2011 */       MQMessage testmsg = new MQMessage();
/*      */ 
/*      */       
/* 2014 */       MQPutMessageOptions pmo = new MQPutMessageOptions();
/* 2015 */       pmo.options |= 0x2;
/* 2016 */       pmo.options &= 0xFFFFFFFB;
/*      */       
/* 2018 */       tmpQ.put(testmsg, pmo);
/*      */ 
/*      */       
/*      */       try {
/* 2022 */         qm1.backout();
/*      */       
/*      */       }
/* 2025 */       catch (MQException e) {
/* 2026 */         if (Trace.isOn) {
/* 2027 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createUniqueID(MQQueueManager)", (Throwable)e, 1);
/*      */         }
/*      */ 
/*      */         
/* 2031 */         HashMap<String, Object> ffstData = new HashMap<>();
/* 2032 */         ffstData.put("Exception", e);
/* 2033 */         ffstData.put("Message", "MQJMS1016");
/* 2034 */         ffstData.put("Comment", "failed to backout conID message from queue");
/* 2035 */         Trace.ffst(this, "createUniqueID(MQQueueManager)", "XO008005", ffstData, JMSException.class);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2040 */       tmpQ.close();
/*      */       
/* 2042 */       id = Utils.bytesToHex(testmsg.messageId);
/*      */     
/*      */     }
/* 2045 */     catch (MQException e) {
/* 2046 */       if (Trace.isOn) {
/* 2047 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createUniqueID(MQQueueManager)", (Throwable)e, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2052 */       JMSException je = ConfigEnvironment.newException("MQJMS5087", "SYSTEM.JMS.REPORT.QUEUE", 
/*      */           
/* 2054 */           String.valueOf(e.reasonCode));
/* 2055 */       je.setLinkedException((Exception)e);
/*      */       
/* 2057 */       if (Trace.isOn) {
/* 2058 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createUniqueID(MQQueueManager)", (Throwable)je);
/*      */       }
/*      */       
/* 2061 */       throw je;
/*      */     } 
/*      */     
/* 2064 */     if (Trace.isOn) {
/* 2065 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createUniqueID(MQQueueManager)", id);
/*      */     }
/*      */     
/* 2068 */     return id;
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
/*      */   protected void deliverException(JMSException e) {
/* 2081 */     if (Trace.isOn) {
/* 2082 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "deliverException(JMSException)", new Object[] { e });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2087 */     ProviderExceptionListener el = this.exceptionListener;
/* 2088 */     if (el != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2096 */       JMSException je = e;
/* 2097 */       boolean isConnectionBroken = false;
/*      */       
/* 2099 */       while (je != null) {
/* 2100 */         Exception e1 = je.getLinkedException();
/* 2101 */         if (e1 == null || !(e1 instanceof JMSException)) {
/* 2102 */           if (e1 instanceof MQException) {
/* 2103 */             int iReasonCode = ((MQException)e1).reasonCode;
/*      */ 
/*      */ 
/*      */             
/* 2107 */             isConnectionBroken = Reason.isConnectionBroken(iReasonCode);
/*      */           } 
/*      */           
/*      */           break;
/*      */         } 
/* 2112 */         je = (JMSException)e1;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2119 */       el.onException(e, isConnectionBroken);
/*      */ 
/*      */     
/*      */     }
/* 2123 */     else if (Trace.isOn) {
/* 2124 */       Trace.traceData(this, "No exception listener - exception ignored", e);
/*      */     } 
/*      */ 
/*      */     
/* 2128 */     if (Trace.isOn) {
/* 2129 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "deliverException(JMSException)");
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
/*      */   private void deliverException(JMSException e, boolean connectionBroken) {
/* 2145 */     if (Trace.isOn) {
/* 2146 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "deliverException(JMSException,boolean)", new Object[] { e, 
/*      */             
/* 2148 */             Boolean.valueOf(connectionBroken) });
/*      */     }
/*      */ 
/*      */     
/* 2152 */     ProviderExceptionListener el = this.exceptionListener;
/* 2153 */     if (el != null) {
/*      */       
/* 2155 */       el.onException(e, connectionBroken);
/*      */     
/*      */     }
/* 2158 */     else if (Trace.isOn) {
/* 2159 */       Trace.traceData(this, "No exception listener - exception ignored", e);
/*      */     } 
/*      */     
/* 2162 */     if (Trace.isOn) {
/* 2163 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "deliverException(JMSException,boolean)");
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
/*      */   protected synchronized MQQueueManager getInitialQM() {
/* 2177 */     MQQueueManager qmanager = null;
/* 2178 */     if (this.initialQm != null) {
/* 2179 */       qmanager = this.initialQm;
/* 2180 */       this.initialQm = null;
/*      */     } 
/* 2182 */     if (Trace.isOn) {
/* 2183 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getInitialQM()", "getter", qmanager);
/*      */     }
/*      */     
/* 2186 */     return qmanager;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getBrkCCSubQueue() throws JMSException {
/* 2197 */     String brkCCSubQueue = getStringProperty("XMSC_WMQ_BROKER_CC_SUBQ");
/* 2198 */     if (Trace.isOn) {
/* 2199 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getBrkCCSubQueue()", "getter", brkCCSubQueue);
/*      */     }
/*      */     
/* 2202 */     return brkCCSubQueue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getBrkControlQueue() throws JMSException {
/* 2212 */     String brkControlQueue = getStringProperty("XMSC_WMQ_BROKER_CONTROLQ");
/* 2213 */     if (Trace.isOn) {
/* 2214 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getBrkControlQueue()", "getter", brkControlQueue);
/*      */     }
/*      */     
/* 2217 */     return brkControlQueue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getBrkOptLevel() throws JMSException {
/* 2225 */     if (Trace.isOn) {
/* 2226 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getBrkOptLevel()", "getter", 
/* 2227 */           Integer.valueOf(this.brkOptLevel));
/*      */     }
/* 2229 */     return this.brkOptLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getBrkPubQueue() throws JMSException {
/* 2235 */     String brkPubQueue = getStringProperty("XMSC_WMQ_BROKER_PUBQ");
/* 2236 */     if (Trace.isOn) {
/* 2237 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getBrkPubQueue()", "getter", brkPubQueue);
/*      */     }
/*      */     
/* 2240 */     return brkPubQueue;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getBrkQueueManager() throws JMSException {
/* 2246 */     String brkQueueManager = getStringProperty("XMSC_WMQ_BROKER_QMGR");
/* 2247 */     if (Trace.isOn) {
/* 2248 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getBrkQueueManager()", "getter", brkQueueManager);
/*      */     }
/*      */     
/* 2251 */     return brkQueueManager;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getBrkSubQueue() throws JMSException {
/* 2257 */     String brkSubQueue = getStringProperty("XMSC_WMQ_BROKER_SUBQ");
/* 2258 */     if (Trace.isOn) {
/* 2259 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getBrkSubQueue()", "getter", brkSubQueue);
/*      */     }
/*      */     
/* 2262 */     return brkSubQueue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getBrkVersion() throws JMSException {
/* 2270 */     if (Trace.isOn) {
/* 2271 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getBrkVersion()", "getter", 
/* 2272 */           Integer.valueOf(this.brkVersion));
/*      */     }
/* 2274 */     return this.brkVersion;
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
/*      */   protected MQSession getCBBrokerSession() throws JMSException {
/* 2288 */     synchronized (this.cbBrokerSessionLock) {
/* 2289 */       if (this.cbBrokerSession == null) {
/* 2290 */         if (Trace.isOn) {
/* 2291 */           Trace.traceData(this, "Creating cbBrokerSession", null);
/*      */         }
/*      */         
/* 2294 */         this.cbBrokerSession = (MQSession)createSessionInternal(false, 1, 0);
/* 2295 */         this.cbBrokerSession.addPubSubServices();
/*      */       } 
/*      */     } 
/*      */     
/* 2299 */     if (Trace.isOn) {
/* 2300 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getCBBrokerSession()", "getter", this.cbBrokerSession);
/*      */     }
/*      */     
/* 2303 */     return this.cbBrokerSession;
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
/*      */   protected String getClientID() throws JMSException {
/* 2317 */     String clientID = getStringProperty("XMSC_CLIENT_ID");
/* 2318 */     if (Trace.isOn) {
/* 2319 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getClientID()", "getter", clientID);
/*      */     }
/*      */     
/* 2322 */     return clientID;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getConnectionID() throws JMSException {
/* 2330 */     if (Trace.isOn) {
/* 2331 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getConnectionID()", "getter", this.connectionID);
/*      */     }
/*      */     
/* 2334 */     return this.connectionID;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected long getEoqTimeout() {
/* 2343 */     if (Trace.isOn) {
/* 2344 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getEoqTimeout()", "getter", 
/* 2345 */           Long.valueOf(this.eoqTimeout));
/*      */     }
/* 2347 */     return this.eoqTimeout;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getFailIfQuiesce() {
/* 2358 */     if (Trace.isOn) {
/* 2359 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getFailIfQuiesce()", "getter", 
/* 2360 */           Integer.valueOf(this.failIfQuiesce));
/*      */     }
/* 2362 */     return this.failIfQuiesce;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean getMapNameStyle() {
/* 2372 */     if (Trace.isOn) {
/* 2373 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMapNameStyle()", "getter", 
/* 2374 */           Boolean.valueOf(this.mapNameStyle));
/*      */     }
/* 2376 */     return this.mapNameStyle;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getMessageRetention() throws JMSException {
/* 2387 */     if (Trace.isOn) {
/* 2388 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMessageRetention()", "getter", 
/* 2389 */           Integer.valueOf(this.messageRetention));
/*      */     }
/* 2391 */     return this.messageRetention;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JMSServicesMgr getMQPubSubServices(boolean transacted, int acknowledgeMode, MQSession session) throws JMSException {
/* 2398 */     if (Trace.isOn)
/* 2399 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMQPubSubServices(boolean,int,MQSession)", new Object[] {
/* 2400 */             Boolean.valueOf(transacted), 
/* 2401 */             Integer.valueOf(acknowledgeMode), session
/*      */           }); 
/*      */     try {
/* 2404 */       if (this.psServices == null) {
/* 2405 */         setSubscriptionStores();
/* 2406 */         this.psServices = new MQPubSubServices(this, transacted, acknowledgeMode, session);
/*      */       } 
/*      */ 
/*      */       
/* 2410 */       if (this.servicesMgr == null) {
/* 2411 */         this.servicesMgr = new JMSServicesMgr();
/*      */       }
/* 2413 */       this.servicesMgr.setPubSubServices(this.psServices);
/*      */ 
/*      */ 
/*      */       
/* 2417 */       if (!session.pubsubInit)
/*      */       {
/* 2419 */         this.servicesMgr.initialisePubSub(session, transacted, acknowledgeMode);
/*      */       }
/*      */       
/* 2422 */       if (Trace.isOn) {
/* 2423 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMQPubSubServices(boolean,int,MQSession)", this.servicesMgr);
/*      */       }
/*      */       
/* 2426 */       return this.servicesMgr;
/*      */     }
/* 2428 */     catch (JMSException je) {
/* 2429 */       if (Trace.isOn) {
/* 2430 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMQPubSubServices(boolean,int,MQSession)", (Throwable)je);
/*      */       }
/*      */       
/* 2433 */       if (Trace.isOn) {
/* 2434 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMQPubSubServices(boolean,int,MQSession)", (Throwable)je);
/*      */       }
/*      */       
/* 2437 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JMSServicesMgr getMQQueueServices(MQQueueManager qm1, boolean transacted, int acknowledgeMode) throws JMSException {
/* 2448 */     if (Trace.isOn) {
/* 2449 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMQQueueServices(MQQueueManager,boolean,int)", new Object[] { qm1, 
/*      */             
/* 2451 */             Boolean.valueOf(transacted), Integer.valueOf(acknowledgeMode) });
/*      */     }
/*      */     try {
/* 2454 */       if (this.servicesMgr == null) {
/* 2455 */         this.servicesMgr = new JMSServicesMgr();
/*      */       }
/* 2457 */       if (this.qServices == null) {
/* 2458 */         this.qServices = new MQQueueServices(this, qm1, transacted, acknowledgeMode);
/*      */       }
/*      */       
/* 2461 */       this.servicesMgr.setQueueServices(this.qServices);
/* 2462 */       if (Trace.isOn) {
/* 2463 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMQQueueServices(MQQueueManager,boolean,int)", this.servicesMgr);
/*      */       }
/*      */       
/* 2466 */       return this.servicesMgr;
/*      */     }
/* 2468 */     catch (JMSException je) {
/* 2469 */       if (Trace.isOn) {
/* 2470 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMQQueueServices(MQQueueManager,boolean,int)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/* 2474 */       if (Trace.isOn) {
/* 2475 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMQQueueServices(MQQueueManager,boolean,int)", (Throwable)je);
/*      */       }
/*      */       
/* 2478 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getMsgBatchSize() {
/* 2485 */     if (Trace.isOn) {
/* 2486 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMsgBatchSize()", "getter", 
/* 2487 */           Integer.valueOf(this.msgBatchSize));
/*      */     }
/* 2489 */     return this.msgBatchSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getMsgSelection() throws JMSException {
/* 2497 */     if (Trace.isOn) {
/* 2498 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMsgSelection()", "getter", 
/* 2499 */           Integer.valueOf(this.messageSelection));
/*      */     }
/* 2501 */     return this.messageSelection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean getOptimisticPublication() throws JMSException {
/* 2511 */     if (Trace.isOn) {
/* 2512 */       Trace.traceData(this, "getOptimisticPublication = " + this.optimisticPublication, null);
/*      */     }
/* 2514 */     if (Trace.isOn) {
/* 2515 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getOptimisticPublication()", "getter", 
/* 2516 */           Boolean.valueOf(this.optimisticPublication));
/*      */     }
/* 2518 */     return this.optimisticPublication;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean getOutcomeNotification() throws JMSException {
/* 2528 */     if (Trace.isOn) {
/* 2529 */       Trace.traceData(this, "outcomeNotification = " + this.outcomeNotification, null);
/*      */     }
/* 2531 */     if (Trace.isOn) {
/* 2532 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getOutcomeNotification()", "getter", 
/* 2533 */           Boolean.valueOf(this.outcomeNotification));
/*      */     }
/* 2535 */     return this.outcomeNotification;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getPersistenceFromMD() {
/* 2543 */     if (Trace.isOn) {
/* 2544 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getPersistenceFromMD()", "getter", 
/* 2545 */           Boolean.valueOf(this.persistenceFromMD));
/*      */     }
/* 2547 */     return this.persistenceFromMD;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getPollingInterval() {
/* 2555 */     if (Trace.isOn) {
/* 2556 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getPollingInterval()", "getter", 
/* 2557 */           Integer.valueOf(this.pollingInterval));
/*      */     }
/* 2559 */     return this.pollingInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getProcessDuration() throws JMSException {
/* 2569 */     if (Trace.isOn) {
/* 2570 */       Trace.traceData(this, "processDuration = " + this.processDuration, null);
/*      */     }
/* 2572 */     if (Trace.isOn) {
/* 2573 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getProcessDuration()", "getter", 
/* 2574 */           Integer.valueOf(this.processDuration));
/*      */     }
/* 2576 */     return this.processDuration;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getPubAckInterval() throws JMSException {
/* 2584 */     if (Trace.isOn) {
/* 2585 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getPubAckInterval()", "getter", 
/* 2586 */           Integer.valueOf(this.pubAckInterval));
/*      */     }
/* 2588 */     return this.pubAckInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getReceiveIsolation() throws JMSException {
/* 2598 */     if (Trace.isOn) {
/* 2599 */       Trace.traceData(this, "receiveIsolation = " + this.receiveIsolation, null);
/*      */     }
/* 2601 */     if (Trace.isOn) {
/* 2602 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getReceiveIsolation()", "getter", 
/* 2603 */           Integer.valueOf(this.receiveIsolation));
/*      */     }
/* 2605 */     return this.receiveIsolation;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getRescanInterval() throws JMSException {
/* 2613 */     int rescanInterval = getIntProperty("XMSC_WMQ_RESCAN_INTERVAL");
/* 2614 */     if (Trace.isOn) {
/* 2615 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getRescanInterval()", "getter", 
/* 2616 */           Integer.valueOf(rescanInterval));
/*      */     }
/* 2618 */     return rescanInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected JMSServicesMgr getServicesMgr() {
/* 2624 */     if (this.servicesMgr == null) {
/* 2625 */       this.servicesMgr = new JMSServicesMgr();
/*      */     }
/* 2627 */     if (Trace.isOn) {
/* 2628 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getServicesMgr()", "getter", this.servicesMgr);
/*      */     }
/*      */     
/* 2631 */     return this.servicesMgr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean getSparseSubscriptions() {
/* 2638 */     if (Trace.isOn) {
/* 2639 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getSparseSubscriptions()", "getter", 
/* 2640 */           Boolean.valueOf(this.sparseSubscriptions));
/*      */     }
/* 2642 */     return this.sparseSubscriptions;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getStatusRefreshInterval() throws JMSException {
/* 2648 */     int statusRefreshInterval = getIntProperty("XMSC_WMQ_STATUS_REFRESH_INTERVAL");
/* 2649 */     if (Trace.isOn) {
/* 2650 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getStatusRefreshInterval()", "getter", 
/* 2651 */           Integer.valueOf(statusRefreshInterval));
/*      */     }
/* 2653 */     return statusRefreshInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQSubscriptionEngine getSubscriptionEngine() throws JMSException {
/* 2661 */     if (Trace.isOn) {
/* 2662 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getSubscriptionEngine()", "getter", this.subscriptionEngine);
/*      */     }
/*      */     
/* 2665 */     return this.subscriptionEngine;
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
/*      */   protected boolean getSupportsQAT2() {
/* 2678 */     if (Trace.isOn) {
/* 2679 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getSupportsQAT2()", "getter", 
/* 2680 */           Boolean.valueOf(this.supportsQAT2));
/*      */     }
/* 2682 */     return this.supportsQAT2;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean getSyncpointAllGets() {
/* 2688 */     if (Trace.isOn) {
/* 2689 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getSyncpointAllGets()", "getter", 
/* 2690 */           Boolean.valueOf(this.syncpointAllGets));
/*      */     }
/* 2692 */     return this.syncpointAllGets;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void notifyBrowsers() throws IntErrorException, JMSException {
/* 2702 */     if (Trace.isOn)
/* 2703 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifyBrowsers()");  try {
/*      */       Enumeration<MQConnectionBrowser> en; Vector<MQConnectionBrowser> browsersTmp; String detail; HashMap<String, Object> ffstData;
/*      */       String key;
/*      */       String msg;
/*      */       IntErrorException iee;
/* 2708 */       if (Trace.isOn) {
/* 2709 */         Trace.traceData(this, "notifying " + this.browsers
/* 2710 */             .size() + " browsers of state " + ((this.state == 2) ? "CLOSED" : ((this.state == 1) ? "STARTED" : ((this.state == 0) ? "STOPPED" : ("UNKNOWN (" + this.state + ")")))), null);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2732 */       switch (this.state) {
/*      */         case 0:
/* 2734 */           en = this.browsers.elements();
/* 2735 */           while (en.hasMoreElements()) {
/* 2736 */             MQConnectionBrowser browser = en.nextElement();
/*      */             try {
/* 2738 */               browser.deactivateQueueAgent();
/*      */             }
/* 2740 */             catch (JMSException je) {
/* 2741 */               if (Trace.isOn) {
/* 2742 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifyBrowsers()", (Throwable)je, 1);
/*      */               }
/*      */               
/* 2745 */               if (Trace.isOn) {
/* 2746 */                 Trace.traceData(this, "ignoring following exception from browser.deactivateQueueAgent:", null);
/*      */               }
/*      */             } 
/*      */           } 
/* 2750 */           en = this.browsers.elements();
/* 2751 */           while (en.hasMoreElements()) {
/* 2752 */             MQConnectionBrowser browser = en.nextElement();
/*      */             try {
/* 2754 */               browser.stop();
/*      */             }
/* 2756 */             catch (JMSException je) {
/* 2757 */               if (Trace.isOn) {
/* 2758 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifyBrowsers()", (Throwable)je, 2);
/*      */               }
/*      */               
/* 2761 */               if (Trace.isOn) {
/* 2762 */                 Trace.traceData(this, "ignoring exception from browser.stop", null);
/*      */               }
/*      */             } 
/*      */           } 
/*      */           break;
/*      */         
/*      */         case 1:
/* 2769 */           en = this.browsers.elements();
/* 2770 */           while (en.hasMoreElements()) {
/* 2771 */             MQConnectionBrowser browser = en.nextElement();
/*      */             try {
/* 2773 */               browser.startInternal();
/*      */             }
/* 2775 */             catch (JMSException je) {
/* 2776 */               if (Trace.isOn) {
/* 2777 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifyBrowsers()", (Throwable)je, 3);
/*      */               }
/*      */               
/* 2780 */               if (Trace.isOn) {
/* 2781 */                 Trace.traceData(this, "ignoring exception from browser.start", null);
/*      */               }
/*      */             } 
/*      */           } 
/*      */           
/* 2786 */           en = this.browsers.elements();
/* 2787 */           while (en.hasMoreElements()) {
/* 2788 */             MQConnectionBrowser browser = en.nextElement();
/*      */             try {
/* 2790 */               browser.activateQueueAgent();
/*      */             }
/* 2792 */             catch (JMSException je) {
/* 2793 */               if (Trace.isOn) {
/* 2794 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifyBrowsers()", (Throwable)je, 4);
/*      */               }
/*      */               
/* 2797 */               if (Trace.isOn) {
/* 2798 */                 Trace.traceData(this, "ignoring exception from browser.activateQueueAgent", null);
/*      */               }
/*      */             } 
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/* 2807 */           browsersTmp = (Vector<MQConnectionBrowser>)this.browsers.clone();
/*      */           
/* 2809 */           en = browsersTmp.elements();
/* 2810 */           while (en.hasMoreElements()) {
/* 2811 */             MQConnectionBrowser browser = en.nextElement();
/*      */             try {
/* 2813 */               browser.deactivateQueueAgent();
/*      */             }
/* 2815 */             catch (JMSException je) {
/* 2816 */               if (Trace.isOn) {
/* 2817 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifyBrowsers()", (Throwable)je, 5);
/*      */               }
/*      */               
/* 2820 */               if (Trace.isOn) {
/* 2821 */                 Trace.traceData(this, "ignoring exception from browser.deactivateQueueAgent", null);
/*      */               }
/*      */             } 
/*      */           } 
/*      */           
/* 2826 */           en = browsersTmp.elements();
/* 2827 */           while (en.hasMoreElements()) {
/* 2828 */             MQConnectionBrowser browser = en.nextElement();
/*      */             try {
/* 2830 */               browser.close(true);
/*      */             }
/* 2832 */             catch (JMSException je) {
/* 2833 */               if (Trace.isOn) {
/* 2834 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifyBrowsers()", (Throwable)je, 6);
/*      */               }
/*      */               
/* 2837 */               if (Trace.isOn) {
/* 2838 */                 Trace.traceData(this, "ignoring  exception from browser.close", null);
/*      */               }
/*      */             } 
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/* 2848 */           detail = ConfigEnvironment.getMessage("MQJMS1006", "state", String.valueOf(this.state));
/* 2849 */           ffstData = new HashMap<>();
/* 2850 */           ffstData.put("Message", "MQJMS1016");
/* 2851 */           ffstData.put("Comment", detail);
/* 2852 */           Trace.ffst(this, "notifyBrowsers()", "XO008007", ffstData, JMSException.class);
/*      */ 
/*      */           
/* 2855 */           key = "MQJMS1016";
/* 2856 */           msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 2857 */           iee = new IntErrorException(msg, key);
/* 2858 */           if (Trace.isOn) {
/* 2859 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifyBrowsers()", (Throwable)iee, 1);
/*      */           }
/*      */           
/* 2862 */           throw iee;
/*      */       } 
/*      */ 
/*      */     
/* 2866 */     } catch (JMSException je) {
/* 2867 */       if (Trace.isOn) {
/* 2868 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifyBrowsers()", (Throwable)je, 7);
/*      */       }
/*      */       
/* 2871 */       if (Trace.isOn) {
/* 2872 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifyBrowsers()", (Throwable)je, 2);
/*      */       }
/*      */       
/* 2875 */       throw je;
/*      */     } 
/*      */     
/* 2878 */     if (Trace.isOn) {
/* 2879 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifyBrowsers()");
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
/*      */   private void notifySessions() throws JMSException, IntErrorException {
/* 2894 */     if (Trace.isOn) {
/* 2895 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifySessions()");
/*      */     }
/*      */     
/*      */     try {
/* 2899 */       if (Trace.isOn) {
/* 2900 */         Trace.traceData(this, "notifying " + this.sessions.size() + " sessions for state " + this.state, null);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2905 */       Vector<MQSession> sessionsTmp = (Vector<MQSession>)this.sessions.clone();
/* 2906 */       Enumeration<MQSession> en = sessionsTmp.elements();
/*      */ 
/*      */ 
/*      */       
/* 2910 */       List<MQSession> sessionsStoppingWaitList = new ArrayList<>();
/*      */       
/* 2912 */       while (en.hasMoreElements()) {
/* 2913 */         MQSession session = en.nextElement(); try {
/*      */           boolean needToWait;
/* 2915 */           switch (this.state) {
/*      */             case 0:
/* 2917 */               needToWait = session.stopWithOptionalWait(false);
/* 2918 */               if (needToWait)
/*      */               {
/* 2920 */                 sessionsStoppingWaitList.add(session);
/*      */               }
/*      */               continue;
/*      */             case 1:
/* 2924 */               session.start();
/*      */               continue;
/*      */             case 2:
/* 2927 */               session.close(true);
/*      */               continue;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2936 */           String detail = ConfigEnvironment.getMessage("MQJMS1006", "state", String.valueOf(this.state));
/*      */           
/* 2938 */           HashMap<String, Object> ffstData = new HashMap<>();
/* 2939 */           ffstData.put("Message", "MQJMS1016");
/* 2940 */           ffstData.put("Comment", detail);
/* 2941 */           Trace.ffst(this, "notifySessions()", "XO008008", ffstData, JMSException.class);
/*      */ 
/*      */           
/* 2944 */           String key = "MQJMS1016";
/* 2945 */           String msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 2946 */           IntErrorException iee = new IntErrorException(msg, key);
/* 2947 */           if (Trace.isOn) {
/* 2948 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifySessions()", (Throwable)iee, 1);
/*      */           }
/*      */           
/* 2951 */           throw iee;
/*      */         
/*      */         }
/* 2954 */         catch (SessionClosedException e) {
/* 2955 */           if (Trace.isOn) {
/* 2956 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifySessions()", (Throwable)e, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2963 */           this.sessions.removeElement(session);
/*      */         }
/* 2965 */         catch (JMSException e) {
/* 2966 */           if (Trace.isOn) {
/* 2967 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifySessions()", (Throwable)e, 2);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2974 */           if (Trace.isOn) {
/* 2975 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifySessions()", (Throwable)e, 2);
/*      */           }
/*      */           
/* 2978 */           throw e;
/*      */         } 
/*      */       } 
/*      */       
/* 2982 */       for (MQSession session : sessionsStoppingWaitList) {
/* 2983 */         session.waitForAsyncConsumerToPause();
/*      */       }
/*      */     }
/* 2986 */     catch (JMSException je) {
/* 2987 */       if (Trace.isOn) {
/* 2988 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifySessions()", (Throwable)je, 3);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2994 */     if (Trace.isOn) {
/* 2995 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "notifySessions()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeBrowser(MQConnectionBrowser browser) {
/* 3003 */     if (Trace.isOn) {
/* 3004 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "removeBrowser(MQConnectionBrowser)", new Object[] { browser });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3009 */     boolean removed = this.browsers.removeElement(browser);
/* 3010 */     if (Trace.isOn) {
/* 3011 */       if (removed) {
/* 3012 */         Trace.traceData(this, "browser removed from vector", null);
/*      */       } else {
/*      */         
/* 3015 */         Trace.traceData(this, "browser was not found in vector", null);
/*      */       } 
/*      */     }
/*      */     
/* 3019 */     if (Trace.isOn) {
/* 3020 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "removeBrowser(MQConnectionBrowser)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeSession(MQSession session) {
/* 3029 */     if (Trace.isOn) {
/* 3030 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "removeSession(MQSession)", new Object[] { session });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3035 */     boolean removed = this.sessions.removeElement(session);
/* 3036 */     if (Trace.isOn) {
/* 3037 */       if (removed) {
/* 3038 */         Trace.traceData(this, "session removed from vector", null);
/*      */       } else {
/*      */         
/* 3041 */         Trace.traceData(this, "session was not found in vector", null);
/*      */       } 
/*      */     }
/* 3044 */     if (Trace.isOn) {
/* 3045 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "removeSession(MQSession)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setBrkOptLevel(int brkOptLevel) throws JMSException {
/* 3056 */     if (Trace.isOn) {
/* 3057 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setBrkOptLevel(int)", "setter", 
/* 3058 */           Integer.valueOf(brkOptLevel));
/*      */     }
/* 3060 */     this.brkOptLevel = brkOptLevel;
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
/*      */   public final void setExceptionListener(ProviderExceptionListener listener) throws JMSException {
/* 3075 */     if (Trace.isOn) {
/* 3076 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setExceptionListener(ProviderExceptionListener)", "setter", listener);
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
/* 3090 */     this.exceptionListener = listener;
/*      */   }
/*      */ 
/*      */   
/*      */   private void setSubscriptionStores() throws JMSException {
/* 3095 */     if (Trace.isOn) {
/* 3096 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setSubscriptionStores()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3102 */     MQQueueManager qmgr = null;
/* 3103 */     if (this.qm != null && this.qm.isConnected() && this.qm.isOpen()) {
/* 3104 */       qmgr = this.qm;
/*      */     } else {
/*      */       try {
/*      */         URL saveUrl;
/*      */         
/* 3109 */         String value = getStringProperty("XMSC_WMQ_CCDTURL");
/* 3110 */         if (value != null) {
/* 3111 */           saveUrl = new URL(value);
/*      */         } else {
/*      */           
/* 3114 */           saveUrl = null;
/*      */         } 
/* 3116 */         if (saveUrl != null && saveUrl.toString().length() > 0)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3123 */           setStringProperty("XMSC_WMQ_CCDTURL", null);
/*      */         }
/* 3125 */         qmgr = createQMNonXA();
/*      */         
/* 3127 */         setStringProperty("XMSC_WMQ_CCDTURL", (saveUrl == null) ? null : saveUrl.toString());
/*      */       }
/* 3129 */       catch (MalformedURLException e) {
/* 3130 */         if (Trace.isOn) {
/* 3131 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setSubscriptionStores()", e, 1);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3142 */       this.connectionID = createUniqueID(qmgr);
/*      */     }
/* 3144 */     catch (JMSException e) {
/* 3145 */       if (Trace.isOn) {
/* 3146 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setSubscriptionStores()", (Throwable)e, 2);
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/* 3151 */         if (Trace.isOn) {
/* 3152 */           Trace.traceData(this, "setSubScriptionStores()", "Disconnecting queue manager after exception", null);
/*      */         }
/* 3154 */         qmgr.disconnect();
/*      */       }
/* 3156 */       catch (MQException je) {
/* 3157 */         if (Trace.isOn) {
/* 3158 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setSubscriptionStores()", (Throwable)je, 3);
/*      */         }
/*      */         
/* 3161 */         if (Trace.isOn) {
/* 3162 */           Trace.traceData(this, "setSubScriptionStores()", "queue manager disconnect failed", null);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 3167 */       Exception linked = e.getLinkedException();
/*      */       
/* 3169 */       if ((((linked != null) ? 1 : 0) & ((this.psServices == null) ? 1 : 0)) != 0 && 
/* 3170 */         linked instanceof MQException) {
/* 3171 */         MQException mq = (MQException)e.getLinkedException();
/* 3172 */         if (mq.reasonCode == 2085) {
/*      */           
/* 3174 */           JMSException je = new JMSException(ConfigEnvironment.getErrorMessage("MQJMS1111"));
/* 3175 */           je.setLinkedException((Exception)e);
/* 3176 */           je.initCause((Throwable)e);
/* 3177 */           if (Trace.isOn) {
/* 3178 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setSubscriptionStores()", (Throwable)je, 1);
/*      */           }
/*      */           
/* 3181 */           throw je;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 3186 */       if (Trace.isOn) {
/* 3187 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setSubscriptionStores()", (Throwable)e, 2);
/*      */       }
/*      */       
/* 3190 */       throw e;
/*      */     } 
/*      */     
/* 3193 */     if (qmgr.name == null || qmgr.name.trim().equals("")) {
/* 3194 */       this.resolvedQMName = Utils.inquireString((MQManagedObject)qmgr, 2015);
/*      */     } else {
/*      */       
/* 3197 */       this.resolvedQMName = qmgr.name;
/*      */     } 
/*      */     
/* 3200 */     if (this.resolvedQMName == null) {
/* 3201 */       this.resolvedQMName = "";
/*      */     } else {
/*      */       
/* 3204 */       this.resolvedQMName = this.resolvedQMName.trim();
/*      */     } 
/*      */     
/* 3207 */     if (this.subscriptionEngine == null) {
/* 3208 */       JMSException je; switch (getIntProperty("XMSC_WMQ_SUBSCRIPTION_STORE")) {
/*      */ 
/*      */         
/*      */         case 0:
/* 3212 */           if (getIntProperty("XMSC_WMQ_CLONE_SUPPORT") == 1) {
/* 3213 */             if (Trace.isOn) {
/* 3214 */               Trace.traceData(this, "Cannot have clone support enabled for a queueSubscriptionEngine.", null);
/*      */             }
/* 3216 */             JMSException jMSException = ConfigEnvironment.newException("MQJMS4125", "SUBSTORE(QUEUE)", "CLONESUPP(ENABLED)");
/* 3217 */             if (Trace.isOn) {
/* 3218 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setSubscriptionStores()", (Throwable)jMSException, 3);
/*      */             }
/*      */             
/* 3221 */             throw jMSException;
/*      */           } 
/*      */           
/* 3224 */           this.subscriptionEngine = new MQQueueSubscriptionEngine(this, qmgr);
/*      */           break;
/*      */         
/*      */         case 1:
/* 3228 */           this.subscriptionEngine = new MQBrokerSubscriptionEngine(this, this.resolvedQMName);
/*      */           break;
/*      */         
/*      */         case 2:
/* 3232 */           this.subscriptionEngine = new MQMigrateSubscriptionEngine(this, qmgr, this.resolvedQMName);
/*      */           break;
/*      */         
/*      */         default:
/* 3236 */           je = new JMSException(ConfigEnvironment.getMessage("MQJMS1016"));
/*      */           
/* 3238 */           if (Trace.isOn) {
/* 3239 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setSubscriptionStores()", (Throwable)je, 4);
/*      */           }
/*      */           
/* 3242 */           throw je;
/*      */       } 
/*      */     
/*      */     } else {
/* 3246 */       Trace.traceData(this, "Sub Engine already registered with this connection", null);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 3251 */     if (getIntProperty("XMSC_WMQ_SUBSCRIPTION_STORE") == 1) {
/*      */       try {
/* 3253 */         qmgr.disconnect();
/*      */       }
/* 3255 */       catch (MQException je) {
/* 3256 */         if (Trace.isOn) {
/* 3257 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setSubscriptionStores()", (Throwable)je, 4);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3265 */     if (Trace.isOn) {
/* 3266 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setSubscriptionStores()");
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
/*      */   private void setSupportsQAT2(boolean b) {
/* 3280 */     if (Trace.isOn) {
/* 3281 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setSupportsQAT2(boolean)", "setter", 
/* 3282 */           Boolean.valueOf(b));
/*      */     }
/* 3284 */     this.supportsQAT2 = b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final synchronized void start() throws JMSException {
/* 3293 */     if (Trace.isOn) {
/* 3294 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "start()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 3299 */       if (this.state == 2) {
/* 3300 */         String key = "MQJMS1004";
/* 3301 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 3302 */         IllegalStateException traceRet1 = new IllegalStateException(msg, key);
/* 3303 */         if (Trace.isOn) {
/* 3304 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "start()", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/* 3307 */         throw traceRet1;
/*      */       } 
/* 3309 */       if (this.state != 1)
/*      */       {
/*      */         
/* 3312 */         if (this.state == 0) {
/*      */           
/* 3314 */           this.state = 1;
/* 3315 */           notifyBrowsers();
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 3321 */           HashMap<String, Object> ffstData = new HashMap<>();
/* 3322 */           ffstData.put("WMQJMS Exception", ConfigEnvironment.newException("MQJMS1005", 
/* 3323 */                 String.valueOf(this.state), "STATE_STARTED"));
/*      */           
/* 3325 */           Trace.ffst(this, "start()", "XO008009", ffstData, JMSException.class);
/*      */           
/* 3327 */           JMSException je = ConfigEnvironment.newException("MQJMS1005", String.valueOf(this.state), "STATE_STARTED");
/* 3328 */           if (Trace.isOn) {
/* 3329 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "start()", (Throwable)je, 2);
/*      */           }
/*      */           
/* 3332 */           throw je;
/*      */         } 
/*      */       }
/* 3335 */     } catch (JMSException je) {
/* 3336 */       if (Trace.isOn) {
/* 3337 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "start()", (Throwable)je);
/*      */       }
/*      */       
/* 3340 */       if (Trace.isOn) {
/* 3341 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "start()", (Throwable)je, 3);
/*      */       }
/*      */       
/* 3344 */       throw je;
/*      */     } 
/*      */     
/* 3347 */     if (Trace.isOn) {
/* 3348 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "start()");
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
/*      */   public final synchronized void stop() throws JMSException {
/* 3364 */     if (Trace.isOn) {
/* 3365 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "stop()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 3370 */       if (this.state == 2) {
/* 3371 */         String key = "MQJMS1004";
/* 3372 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 3373 */         IllegalStateException traceRet1 = new IllegalStateException(msg, key);
/* 3374 */         if (Trace.isOn) {
/* 3375 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "stop()", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/* 3378 */         throw traceRet1;
/*      */       } 
/* 3380 */       if (this.state == 1)
/*      */       {
/* 3382 */         this.state = 0;
/* 3383 */         notifyBrowsers();
/* 3384 */         notifySessions();
/*      */       }
/* 3386 */       else if (this.state != 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3393 */         HashMap<String, Object> ffstData = new HashMap<>();
/* 3394 */         ffstData.put("WMQJMS Exception", ConfigEnvironment.newException("MQJMS1005", 
/* 3395 */               String.valueOf(this.state), "STATE_STOPPED"));
/*      */         
/* 3397 */         Trace.ffst(this, "stop()", "XO00800A", ffstData, JMSException.class);
/*      */         
/* 3399 */         JMSException je = ConfigEnvironment.newException("MQJMS1005", String.valueOf(this.state), "STATE_STOPPED");
/* 3400 */         if (Trace.isOn) {
/* 3401 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "stop()", (Throwable)je, 2);
/*      */         }
/*      */         
/* 3404 */         throw je;
/*      */       }
/*      */     
/* 3407 */     } catch (JMSException je) {
/* 3408 */       if (Trace.isOn) {
/* 3409 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "stop()", (Throwable)je);
/*      */       }
/*      */       
/* 3412 */       if (Trace.isOn) {
/* 3413 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "stop()", (Throwable)je, 3);
/*      */       }
/*      */       
/* 3416 */       throw je;
/*      */     } 
/*      */     
/* 3419 */     if (Trace.isOn) {
/* 3420 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "stop()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void supportThreeExits() throws JMSException {
/* 3426 */     if (Trace.isOn) {
/* 3427 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "supportThreeExits()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3435 */     String securityExitName = getStringProperty("XMSC_WMQ_SECURITY_EXIT");
/* 3436 */     if (securityExitName != null) {
/* 3437 */       this.mqProperties.put("securityExit", securityExitName);
/* 3438 */       String securityExitInit = getStringProperty("XMSC_WMQ_SECURITY_EXIT_INIT");
/* 3439 */       if (securityExitInit != null) {
/* 3440 */         this.mqProperties.put("securitydExitData", securityExitInit);
/*      */       }
/*      */     } 
/*      */     
/* 3444 */     String receiveExitName = getStringProperty("XMSC_WMQ_RECEIVE_EXIT");
/* 3445 */     if (receiveExitName != null) {
/* 3446 */       this.mqProperties.put("receiveExit", receiveExitName);
/* 3447 */       String receiveExitInit = getStringProperty("XMSC_WMQ_RECEIVE_EXIT_INIT");
/* 3448 */       if (receiveExitInit != null) {
/* 3449 */         this.mqProperties.put("receiveExitData", receiveExitInit);
/*      */       }
/*      */     } 
/*      */     
/* 3453 */     String sendExitName = getStringProperty("XMSC_WMQ_SEND_EXIT");
/* 3454 */     if (sendExitName != null) {
/* 3455 */       this.mqProperties.put("sendExit", sendExitName);
/* 3456 */       String sendExitInit = getStringProperty("XMSC_WMQ_SEND_EXIT_INIT");
/* 3457 */       if (sendExitInit != null) {
/* 3458 */         this.mqProperties.put("sendExitData", sendExitInit);
/*      */       }
/*      */     } 
/* 3461 */     if (Trace.isOn) {
/* 3462 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "supportThreeExits()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void deleteTemporaryDestination(WMQDestination dest) throws JMSException {
/* 3473 */     if (Trace.isOn) {
/* 3474 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "deleteTemporaryDestination(WMQDestination)", new Object[] { dest });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 3479 */       MQQueue tmpQueue = this.temporaryDestinations.remove(dest);
/*      */       
/* 3481 */       if (tmpQueue == null) {
/*      */         
/* 3483 */         if (Trace.isOn) {
/* 3484 */           Trace.data(this, "Could not find underlying MQQueue for ProviderDestination", dest);
/*      */         }
/* 3486 */         if (Trace.isOn) {
/* 3487 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "deleteTemporaryDestination(WMQDestination)", 1);
/*      */         }
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*      */         JMSException je;
/*      */ 
/*      */         
/* 3499 */         int defType = tmpQueue.getDefinitionType();
/*      */         
/* 3501 */         switch (defType) {
/*      */ 
/*      */           
/*      */           case 1:
/* 3505 */             je = ConfigEnvironment.newException("MQJMS3003");
/* 3506 */             if (Trace.isOn) {
/* 3507 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "deleteTemporaryDestination(WMQDestination)", (Throwable)je, 1);
/*      */             }
/*      */             
/* 3510 */             throw je;
/*      */ 
/*      */           
/*      */           case 2:
/* 3514 */             tmpQueue.closeOptions = 2;
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 3:
/*      */             break;
/*      */ 
/*      */           
/*      */           default:
/* 3524 */             je = ConfigEnvironment.newException("MQJMS2010", String.valueOf(defType));
/* 3525 */             if (Trace.isOn) {
/* 3526 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "deleteTemporaryDestination(WMQDestination)", (Throwable)je, 2);
/*      */             }
/*      */             
/* 3529 */             throw je;
/*      */         } 
/*      */ 
/*      */         
/* 3533 */         tmpQueue.close();
/*      */ 
/*      */         
/* 3536 */         tmpQueue = null;
/*      */       
/*      */       }
/* 3539 */       catch (MQException e) {
/* 3540 */         if (Trace.isOn) {
/* 3541 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "deleteTemporaryDestination(WMQDestination)", (Throwable)e, 1);
/*      */         }
/*      */ 
/*      */         
/* 3545 */         JMSException je = ConfigEnvironment.newException("MQJMS3004");
/* 3546 */         je.setLinkedException((Exception)e);
/* 3547 */         if (Trace.isOn) {
/* 3548 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "deleteTemporaryDestination(WMQDestination)", (Throwable)je, 3);
/*      */         }
/*      */         
/* 3551 */         throw je;
/*      */       }
/*      */     
/* 3554 */     } catch (JMSException je) {
/* 3555 */       if (Trace.isOn) {
/* 3556 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "deleteTemporaryDestination(WMQDestination)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 3559 */       if (Trace.isOn) {
/* 3560 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "deleteTemporaryDestination(WMQDestination)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 3563 */       throw je;
/*      */     } 
/* 3565 */     if (Trace.isOn) {
/* 3566 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "deleteTemporaryDestination(WMQDestination)", 2);
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
/*      */   public WMQDestination createTemporaryDestination(int destType, JmsPropertyContext propContext) throws JMSException {
/*      */     WMQTemporaryTopic wMQTemporaryTopic;
/* 3579 */     if (Trace.isOn) {
/* 3580 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createTemporaryDestination(int,JmsPropertyContext)", new Object[] {
/*      */             
/* 3582 */             Integer.valueOf(destType), propContext
/*      */           });
/*      */     }
/* 3585 */     WMQDestination temporaryDestination = null;
/*      */     
/* 3587 */     if (destType == 1) {
/*      */       MQQueue newQueue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3594 */       if (this.tempQqm == null) {
/*      */         
/* 3596 */         this.tempQqm = createQMNonXA();
/*      */ 
/*      */         
/*      */         try {
/* 3600 */           int selector = 2015;
/* 3601 */           this.qmName = this.tempQqm.getAttributeString(selector, 48);
/*      */           
/* 3603 */           if (Trace.isOn) {
/* 3604 */             Trace.traceData(this, "qmName = " + ((null == this.qmName) ? "<NULL>" : this.qmName), null);
/* 3605 */             if (null == this.tempQqm) {
/* 3606 */               Trace.traceData(this, "tempQqm == null", null);
/*      */             } else {
/*      */               
/* 3609 */               Trace.traceData(this, "tempQqm.name == " + ((null == this.tempQqm.name) ? "<NULL>" : this.tempQqm.name), null);
/*      */             } 
/*      */           } 
/* 3612 */           if (null != this.qmName) {
/* 3613 */             this.qmName = this.qmName.trim();
/*      */           } else {
/*      */             
/* 3616 */             this.qmName = this.tempQqm.name;
/*      */           }
/*      */         
/*      */         }
/* 3620 */         catch (Exception e) {
/* 3621 */           if (Trace.isOn) {
/* 3622 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createTemporaryDestination(int,JmsPropertyContext)", e, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 3627 */           this.qmName = this.tempQqm.name;
/*      */         } 
/*      */       } 
/*      */       
/*      */       try {
/* 3632 */         if (Trace.isOn) {
/* 3633 */           Trace.traceData(this, "tempQPrefix = " + ((null == this.tempQPrefix) ? "<NULL>" : ("'" + this.tempQPrefix + "'")), null);
/*      */         }
/* 3635 */         if (this.tempQPrefix != null && this.tempQPrefix.length() > 0) {
/* 3636 */           newQueue = this.tempQqm.accessQueue(this.temporaryModelQ, this.tmpQOpenOptions, this.qmName, this.tempQPrefix, null);
/*      */         } else {
/*      */           
/* 3639 */           newQueue = this.tempQqm.accessQueue(this.temporaryModelQ, this.tmpQOpenOptions);
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 3647 */       catch (MQException e) {
/* 3648 */         if (Trace.isOn) {
/* 3649 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createTemporaryDestination(int,JmsPropertyContext)", (Throwable)e, 2);
/*      */         }
/*      */         
/* 3652 */         JMSException je = ConfigEnvironment.newException("MQJMS3000", this.temporaryModelQ);
/* 3653 */         je.setLinkedException((Exception)e);
/* 3654 */         if (Trace.isOn) {
/* 3655 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createTemporaryDestination(int,JmsPropertyContext)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 3658 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 3662 */       WMQTemporaryQueue wMQTemporaryQueue = new WMQTemporaryQueue("queue://" + this.qmName.trim() + "/" + newQueue.name.trim(), this, propContext);
/*      */       
/* 3664 */       this.temporaryDestinations.put(wMQTemporaryQueue, newQueue);
/*      */     }
/* 3666 */     else if (destType == 2) {
/*      */ 
/*      */ 
/*      */       
/* 3670 */       String name = "temporaryDest" + hashCode() + UUID.randomUUID().toString().replace("-", "");
/* 3671 */       wMQTemporaryTopic = new WMQTemporaryTopic(name, this, propContext);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/* 3679 */       JMSException traceRet1 = new JMSException("Invalid Destination domain type: " + destType);
/* 3680 */       if (Trace.isOn) {
/* 3681 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createTemporaryDestination(int,JmsPropertyContext)", (Throwable)traceRet1, 2);
/*      */       }
/*      */       
/* 3684 */       throw traceRet1;
/*      */     } 
/*      */     
/* 3687 */     if (Trace.isOn) {
/* 3688 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createTemporaryDestination(int,JmsPropertyContext)", wMQTemporaryTopic);
/*      */     }
/*      */     
/* 3691 */     return (WMQDestination)wMQTemporaryTopic;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Collection getSSLCertStores(JmsPropertyContext props) throws JMSException {
/* 3701 */     if (Trace.isOn) {
/* 3702 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getSSLCertStores(JmsPropertyContext)", new Object[] { props });
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
/* 3713 */     Collection<?> sslCertStoresCol = (Collection)props.getObjectProperty("XMSC_WMQ_SSL_CERT_STORES_COL");
/* 3714 */     if (sslCertStoresCol == null) {
/* 3715 */       String sslCertStoresStr = props.getStringProperty("XMSC_WMQ_SSL_CERT_STORES_STR");
/* 3716 */       if (sslCertStoresStr != null && !sslCertStoresStr.trim().equals("")) {
/*      */         try {
/* 3718 */           sslCertStoresCol = JmqiUtils.getCertStoreCollectionFromSpaceSeperatedString(sslCertStoresStr);
/*      */         }
/* 3720 */         catch (Exception e) {
/* 3721 */           if (Trace.isOn) {
/* 3722 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getSSLCertStores(JmsPropertyContext)", e);
/*      */           }
/*      */           
/* 3725 */           JMSException traceRet3 = ConfigEnvironment.newException("MQJMS1006", "XMSC_WMQ_SSL_CERT_STORES_STR", sslCertStoresStr);
/*      */ 
/*      */           
/* 3728 */           if (Trace.isOn) {
/* 3729 */             Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getSSLCertStores(JmsPropertyContext)", (Throwable)traceRet3);
/*      */           }
/*      */           
/* 3732 */           throw traceRet3;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 3737 */     if (Trace.isOn) {
/* 3738 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getSSLCertStores(JmsPropertyContext)", sslCertStoresCol);
/*      */     }
/*      */     
/* 3741 */     return sslCertStoresCol;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Collection getHdrCompList(JmsPropertyContext props) {
/* 3748 */     if (Trace.isOn) {
/* 3749 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getHdrCompList(JmsPropertyContext)", new Object[] { props });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 3754 */       Object<Object> value = (Object<Object>)props.getObjectProperty("XMSC_WMQ_HEADER_COMP");
/* 3755 */       if (value instanceof String) {
/*      */ 
/*      */ 
/*      */         
/* 3759 */         StringTokenizer st = new StringTokenizer((String)value);
/* 3760 */         Vector<Object> c = new Vector();
/* 3761 */         while (st.hasMoreTokens()) {
/* 3762 */           String tk = st.nextToken();
/* 3763 */           if (tk.equals("NONE")) {
/* 3764 */             c.add(Integer.valueOf(0)); continue;
/*      */           } 
/* 3766 */           if (tk.equals("SYSTEM")) {
/* 3767 */             c.add(Integer.valueOf(8));
/*      */             continue;
/*      */           } 
/* 3770 */           c.add(tk);
/*      */         } 
/*      */         
/* 3773 */         value = (Object<Object>)c;
/*      */       } 
/*      */       
/* 3776 */       Collection traceRet1 = (Collection)value;
/* 3777 */       if (Trace.isOn) {
/* 3778 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getHdrCompList(JmsPropertyContext)", traceRet1, 1);
/*      */       }
/*      */       
/* 3781 */       return traceRet1;
/*      */     }
/* 3783 */     catch (JMSException je) {
/* 3784 */       if (Trace.isOn) {
/* 3785 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getHdrCompList(JmsPropertyContext)", (Throwable)je);
/*      */       }
/*      */       
/* 3788 */       if (Trace.isOn) {
/* 3789 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getHdrCompList(JmsPropertyContext)", null, 2);
/*      */       }
/*      */       
/* 3792 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Collection getMsgCompList(JmsPropertyContext props) {
/* 3800 */     if (Trace.isOn) {
/* 3801 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMsgCompList(JmsPropertyContext)", new Object[] { props });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 3806 */       Object<Object> value = (Object<Object>)props.getObjectProperty("XMSC_WMQ_MSG_COMP");
/* 3807 */       if (value instanceof String) {
/*      */ 
/*      */ 
/*      */         
/* 3811 */         StringTokenizer st = new StringTokenizer((String)value);
/* 3812 */         Vector<Object> c = new Vector();
/* 3813 */         while (st.hasMoreTokens()) {
/* 3814 */           String tk = st.nextToken();
/* 3815 */           if (tk.equals("NONE")) {
/* 3816 */             c.add(Integer.valueOf(0)); continue;
/*      */           } 
/* 3818 */           if (tk.equals("RLE")) {
/* 3819 */             c.add(Integer.valueOf(1)); continue;
/*      */           } 
/* 3821 */           if (tk.equals("ZLIBFAST")) {
/* 3822 */             c.add(Integer.valueOf(2)); continue;
/*      */           } 
/* 3824 */           if (tk.equals("ZLIBHIGH")) {
/* 3825 */             c.add(Integer.valueOf(4));
/*      */             continue;
/*      */           } 
/* 3828 */           c.add(tk);
/*      */         } 
/*      */         
/* 3831 */         value = (Object<Object>)c;
/*      */       } 
/*      */       
/* 3834 */       Collection traceRet1 = (Collection)value;
/* 3835 */       if (Trace.isOn) {
/* 3836 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMsgCompList(JmsPropertyContext)", traceRet1, 1);
/*      */       }
/*      */       
/* 3839 */       return traceRet1;
/*      */     }
/* 3841 */     catch (JMSException je) {
/* 3842 */       if (Trace.isOn) {
/* 3843 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMsgCompList(JmsPropertyContext)", (Throwable)je);
/*      */       }
/*      */       
/* 3846 */       if (Trace.isOn) {
/* 3847 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMsgCompList(JmsPropertyContext)", null, 2);
/*      */       }
/*      */       
/* 3850 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean byteArraysEqual(byte[] a, byte[] b) {
/*      */     boolean result;
/* 3861 */     if (Trace.isOn) {
/* 3862 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "byteArraysEqual(byte [ ],byte [ ])", new Object[] { a, b });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 3867 */     if (a == b) {
/* 3868 */       result = true;
/*      */     
/*      */     }
/* 3871 */     else if (a.length != b.length) {
/* 3872 */       result = false;
/*      */     } else {
/*      */       
/* 3875 */       result = true;
/* 3876 */       int len = a.length;
/* 3877 */       for (int i = 0; i < len; i++) {
/* 3878 */         if (a[i] != b[i]) {
/* 3879 */           result = false;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/* 3885 */     if (Trace.isOn) {
/* 3886 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "byteArraysEqual(byte [ ],byte [ ])", 
/* 3887 */           Boolean.valueOf(result));
/*      */     }
/* 3889 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ProviderMetaData getMetaData(JmsPropertyContext parentMetaData) throws JMSException {
/* 3900 */     if (Trace.isOn) {
/* 3901 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMetaData(JmsPropertyContext)", new Object[] { parentMetaData });
/*      */     }
/*      */     
/* 3904 */     if (this.metaData == null)
/*      */     {
/* 3906 */       this.metaData = new MQConnectionMetaData(parentMetaData, this);
/*      */     }
/*      */     
/* 3909 */     if (Trace.isOn) {
/* 3910 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getMetaData(JmsPropertyContext)", this.metaData);
/*      */     }
/*      */     
/* 3913 */     return this.metaData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 3921 */     if (Trace.isOn) {
/* 3922 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "readObject(ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */     
/* 3925 */     NotSerializableException traceRet1 = new NotSerializableException();
/* 3926 */     if (Trace.isOn) {
/* 3927 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "readObject(ObjectInputStream)", traceRet1);
/*      */     }
/*      */     
/* 3930 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 3939 */     if (Trace.isOn) {
/* 3940 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "writeObject(ObjectOutputStream)", new Object[] { out });
/*      */     }
/*      */     
/* 3943 */     NotSerializableException traceRet1 = new NotSerializableException();
/* 3944 */     if (Trace.isOn) {
/* 3945 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "writeObject(ObjectOutputStream)", traceRet1);
/*      */     }
/*      */     
/* 3948 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object o) {
/* 3957 */     if (Trace.isOn) {
/* 3958 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "equals(Object)", new Object[] { o });
/*      */     }
/*      */     
/* 3961 */     boolean traceRet1 = super.equals(o);
/* 3962 */     if (Trace.isOn) {
/* 3963 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "equals(Object)", 
/* 3964 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 3966 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 3975 */     if (Trace.isOn) {
/* 3976 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "hashCode()");
/*      */     }
/* 3978 */     int traceRet1 = super.hashCode();
/* 3979 */     if (Trace.isOn) {
/* 3980 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "hashCode()", 
/* 3981 */           Integer.valueOf(traceRet1));
/*      */     }
/* 3983 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getSweepTime() {
/* 3993 */     if (Trace.isOn) {
/* 3994 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getSweepTime()", "getter", 
/* 3995 */           Long.valueOf(this.sweep_time));
/*      */     }
/* 3997 */     return this.sweep_time;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSweepTime(long newSweepTime) {
/* 4005 */     if (Trace.isOn) {
/* 4006 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setSweepTime(long)", "setter", 
/* 4007 */           Long.valueOf(newSweepTime));
/*      */     }
/*      */     
/* 4010 */     if (newSweepTime < 1000L || newSweepTime > 120000L) {
/*      */       
/* 4012 */       if (Trace.isOn) {
/* 4013 */         Trace.traceData(this, "sweep_time being set to possible invalid value: " + newSweepTime + " Using default value: ", 
/*      */             
/* 4015 */             Integer.valueOf(30000));
/*      */       }
/* 4017 */       this.sweep_time = 30000L;
/*      */     } else {
/*      */       
/* 4020 */       this.sweep_time = newSweepTime;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHeaderDataSize() {
/* 4029 */     if (Trace.isOn) {
/* 4030 */       Trace.traceData(this, "headerDataSize: ", Integer.valueOf(this.headerDataSize));
/*      */     }
/* 4032 */     if (Trace.isOn) {
/* 4033 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "getHeaderDataSize()", "getter", 
/* 4034 */           Integer.valueOf(this.headerDataSize));
/*      */     }
/* 4036 */     return this.headerDataSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHeaderDataSize(int newHeaderDataSize) {
/* 4044 */     if (Trace.isOn) {
/* 4045 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "setHeaderDataSize(int)", "setter", 
/* 4046 */           Integer.valueOf(newHeaderDataSize));
/*      */     }
/* 4048 */     if (Trace.isOn) {
/* 4049 */       Trace.traceData(this, "setHeaderDataSize(int) - setting headerDataSize to: ", Integer.valueOf(newHeaderDataSize));
/* 4050 */       if (newHeaderDataSize != -1 && newHeaderDataSize < 36) {
/* 4051 */         Trace.traceData(this, "headerDataSize (<36) possible incorrect value: ", Integer.valueOf(newHeaderDataSize));
/*      */       }
/*      */     } 
/* 4054 */     this.headerDataSize = newHeaderDataSize;
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
/*      */   public ProviderConnectionBrowser createSharedConnectionBrowser(ProviderDestination destination, String clientid, String subName, String selector, ProviderMessageReferenceHandler msgRefHandler, int quantityHint, boolean noLocal) throws JMSException {
/* 4066 */     if (Trace.isOn) {
/* 4067 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSharedConnectionBrowser(ProviderDestination,String,String,String,ProviderMessageReferenceHandler,int,boolean)", new Object[] { destination, clientid, subName, selector, msgRefHandler, 
/*      */ 
/*      */             
/* 4070 */             Integer.valueOf(quantityHint), Boolean.valueOf(noLocal) });
/*      */     }
/*      */     
/* 4073 */     JMSException je = ConfigEnvironment.newException("MQJMS6405");
/* 4074 */     if (Trace.isOn) {
/* 4075 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSharedConnectionBrowser(ProviderDestination,String,String,String,ProviderMessageReferenceHandler,int,boolean)", (Throwable)je);
/*      */     }
/*      */ 
/*      */     
/* 4079 */     throw je;
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
/*      */   public ProviderConnectionBrowser createSharedDurableConnectionBrowser(ProviderDestination destination, String clientid, String subName, String selector, ProviderMessageReferenceHandler msgRefHandler, int quantityHint, boolean noLocal) throws JMSException {
/* 4092 */     if (Trace.isOn) {
/* 4093 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSharedDurableConnectionBrowser(ProviderDestination,String,String,String,ProviderMessageReferenceHandler,int,boolean)", new Object[] { destination, clientid, subName, selector, msgRefHandler, 
/*      */ 
/*      */             
/* 4096 */             Integer.valueOf(quantityHint), Boolean.valueOf(noLocal) });
/*      */     }
/*      */     
/* 4099 */     JMSException je = ConfigEnvironment.newException("MQJMS6405");
/* 4100 */     if (Trace.isOn) {
/* 4101 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "createSharedDurableConnectionBrowser(ProviderDestination,String,String,String,ProviderMessageReferenceHandler,int,boolean)", (Throwable)je);
/*      */     }
/*      */ 
/*      */     
/* 4105 */     throw je;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dump(PrintWriter pw, int level) {
/* 4114 */     if (Trace.isOn) {
/* 4115 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "dump(PrintWriter,int)", new Object[] { pw, 
/* 4116 */             Integer.valueOf(level) });
/*      */     }
/* 4118 */     StringBuilder prefix = new StringBuilder();
/* 4119 */     for (int i = 0; i < level; i++) {
/* 4120 */       prefix.append("  ");
/*      */     }
/* 4122 */     pw.format("%s%s%n", new Object[] { prefix, toString() });
/* 4123 */     pw.format("%s  exceptionListener %s%n", new Object[] { prefix, String.valueOf(this.exceptionListener) });
/* 4124 */     pw.format("%s  temporaryModelQ %s%n", new Object[] { prefix, String.valueOf(this.temporaryModelQ) });
/* 4125 */     pw.format("%s  tempQPrefix %s%n", new Object[] { prefix, String.valueOf(this.tempQPrefix) });
/* 4126 */     if (Trace.isOn)
/* 4127 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", "dump(PrintWriter,int)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */