/*      */ package com.ibm.mq.jmqi.internal;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.system.internal.JmqiIniFile;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.nio.charset.CodingErrorAction;
/*      */ import java.security.AccessControlException;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.ArrayList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Configuration
/*      */   extends JmqiObject
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/Configuration.java";
/*      */   private static final int CLIENT_INI_FILE = 0;
/*      */   private static final int MQS_INI_FILE = 1;
/*      */   private static final int NumberOfConfigurationFiles = 2;
/*      */   public static BoolCfgProperty TCP_KEEPALIVE;
/*      */   public static BoolCfgProperty TCP_LINGER;
/*      */   public static IntCfgProperty TCP_STRPORT;
/*      */   public static BoolCfgProperty TCP_DNSLOOKUPONERROR;
/*      */   public static IntCfgProperty TCP_ENDPORT;
/*      */   public static IntCfgProperty TCP_CONNECT_TIMEOUT;
/*      */   public static IntCfgProperty TCP_CLNTRCVBUFFSIZE;
/*      */   public static IntCfgProperty TCP_CLNTSNDBUFFSIZE;
/*      */   public static IntCfgProperty MESSAGEBUFFER_MAXIMUMSIZE;
/*      */   public static IntCfgProperty MESSAGEBUFFER_UPDATEPERCENTAGE;
/*      */   public static IntCfgProperty MESSAGEBUFFER_PURGETIME;
/*      */   public static StringCfgProperty CLIENTEXITPATH_EXITSDEFAULTPATH;
/*      */   public static StringCfgProperty CLIENTEXITPATH_EXITSDEFAULTPATH64;
/*      */   public static StringCfgProperty TESTLOCATEINI_RULE;
/*      */   public static BoolCfgProperty MESSAGEPROPERTIES_FORCE_RFH2;
/*      */   public static BoolCfgProperty CCSID_MAPCCSID932TOSJIS;
/*      */   public static BoolCfgProperty CCSID_MAPCCSID1200TOUNICODEBIGUNMARKED;
/*      */   public static BoolCfgProperty CCSID_MAPUTF16BYTEORDERBYCCSID;
/*      */   public static StringCfgProperty CCSID_MAPCCSID1200FAMILYTOSPECIFICCHARSET;
/*      */   public static StringCfgProperty XA_TRANSACTION_MANAGER;
/*      */   public static BoolCfgProperty ENV_AMQ_DISABLE_CLIENT_AMS;
/*      */   public static StringCfgProperty JMQI_LIB_PATH;
/*      */   public static StringCfgProperty ENV_MQCLNTCF;
/*      */   public static StringCfgProperty ENV_MQ_FILE_PATH;
/*      */   public static StringCfgProperty ENV_MQCHLURL;
/*      */   public static StringCfgProperty ENV_MQ_LCLADDR;
/*      */   public static StringCfgProperty ENV_MQRCVBLKTO;
/*      */   public static IntCfgProperty NEGOTIATIONFLOWTIMEOUT;
/*      */   public static IntCfgProperty ENV_MQRCVBLKMIN;
/*      */   private static StringCfgProperty ENV_MQ_COMM;
/*      */   public static BoolCfgProperty ENV_MQSSLFIPS;
/*      */   public static IntCfgProperty ENV_MQSSLRESET;
/*      */   public static IntCfgProperty ENV_MQSSLPOLLTIMEOUT;
/*      */   public static BoolCfgProperty ALLOW_OUTBOUND_SNI;
/*      */   public static ChoiceCfgProperty OUTBOUND_SNI;
/*      */   public static final String OUTBOUND_SNI_CHANNEL = "CHANNEL";
/*      */   public static final String OUTBOUND_SNI_HOSTNAME = "HOSTNAME";
/*      */   public static StringCfgProperty ENV_MQIPADDRV;
/*      */   public static BoolCfgProperty ENV_MQPUT1DEFSYNC;
/*      */   public static IntCfgProperty ENV_MQCCSID;
/*      */   public static IntCfgProperty ENV_MQTCPTIMEOUT;
/*      */   public static StringCfgProperty webSphereServerTypeProperty;
/*      */   public static StringCfgProperty requestedAdapterProperty;
/*      */   public static IntCfgProperty smallMsgsBufferReductionThresholdProperty;
/*      */   public static IntCfgProperty defaultMaxMsgSizeProperty;
/*      */   public static IntCfgProperty threadWaitTimeoutProperty;
/*      */   public static BoolCfgProperty IGNORE_CERT_LABEL;
/*      */   public static StringCfgProperty EXIT_CLASSPATH;
/*      */   public static StringCfgProperty CHANNEL_RECON;
/*      */   public static StringCfgProperty CHANNEL_RECONDELAY;
/*      */   public static IntCfgProperty ENV_MQRMTREQ_POLLTIME;
/*      */   public static StringCfgProperty terminationModeProperty;
/*      */   public static BoolCfgProperty skipQSGNameCheck;
/*      */   public static BoolCfgProperty disableXACCDTCheck;
/*      */   public static BoolCfgProperty USE_MQCSP_AUTHENTICATION;
/*      */   public static ChoiceCfgProperty PASSWORD_PROTECTION;
/*      */   public static final String PASSWORD_PROTECTION_COMPATIBLE = "COMPATIBLE";
/*      */   public static final String PASSWORD_PROTECTION_OPTIONAL = "OPTIONAL";
/*      */   public static final String PASSWORD_PROTECTION_ALWAYS = "ALWAYS";
/*      */   public static ChoiceCfgProperty AMQ_RANDOM_NUMBER_TYPE;
/*      */   public static final String AMQ_RANDOM_NUMBER_TYPE_STANDARD = "STANDARD";
/*      */   public static final String AMQ_RANDOM_NUMBER_TYPE_FAST = "FAST";
/*      */   public static ChoiceCfgProperty UNMAPPABLE_ACTION;
/*  319 */   public static final String UNMAPPABLE_ACTION_REPORT = CodingErrorAction.REPORT.toString();
/*      */   
/*  321 */   public static final String UNMAPPABLE_ACTION_REPLACE = CodingErrorAction.REPLACE.toString();
/*      */   
/*  323 */   public static final String UNMAPPABLE_ACTION_IGNORE = CodingErrorAction.IGNORE.toString();
/*      */ 
/*      */ 
/*      */   
/*      */   public static IntCfgProperty UNMAPPABLE_REPLACEMENT;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String ALT_FILENAME = "mqs.ini";
/*      */ 
/*      */   
/*      */   private static final String DEFAULT_FILENAME = "mqclient.ini";
/*      */ 
/*      */   
/*      */   private static boolean initialised = false;
/*      */ 
/*      */   
/*      */   private JmqiIniFile[] configurationFiles;
/*      */ 
/*      */ 
/*      */   
/*      */   public Configuration(final JmqiEnvironment env) {
/*  345 */     super(env);
/*  346 */     if (Trace.isOn) {
/*  347 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.Configuration", "<init>(final JmqiEnvironment)", new Object[] { env });
/*      */     }
/*      */ 
/*      */     
/*  351 */     initialiseProperties(env);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  357 */     this.configurationFiles = new JmqiIniFile[2];
/*  358 */     AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/*  362 */             if (Trace.isOn) {
/*  363 */               Trace.entry(this, "com.ibm.mq.jmqi.internal.Configuration", "run()");
/*      */             }
/*  365 */             Configuration.this.configurationFiles[0] = new JmqiIniFile(env, Configuration.this.findClientIni());
/*  366 */             Configuration.this.configurationFiles[1] = new JmqiIniFile(env, Configuration.this.findAltIni());
/*      */             
/*  368 */             if (Trace.isOn) {
/*  369 */               Trace.exit(this, "com.ibm.mq.jmqi.internal.null", "run()", null);
/*      */             }
/*  371 */             return null;
/*      */           }
/*      */         });
/*      */     
/*  375 */     if (Trace.isOn) {
/*  376 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.Configuration", "<init>(final JmqiEnvironment)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static synchronized void initialiseProperties(JmqiEnvironment env) {
/*  387 */     if (Trace.isOn) {
/*  388 */       Trace.entry("com.ibm.mq.jmqi.internal.Configuration", "initialiseProperties(JmqiEnvironment)", new Object[] { env });
/*      */     }
/*      */     
/*  391 */     if (!initialised) {
/*      */       
/*  393 */       CfgProperty c = TCP_KEEPALIVE = new BoolCfgProperty(env, false, 1);
/*  394 */       c.addStanzaName(0, "TCP", "KeepAlive");
/*  395 */       c.addPropertyHandlerNameSuffix("TCP.KeepAlive");
/*  396 */       c = TCP_LINGER = new BoolCfgProperty(env, false, 1);
/*  397 */       c.addStanzaName(0, "TCP", "Linger");
/*  398 */       c.addPropertyHandlerNameSuffix("TCP.Linger");
/*  399 */       c = TCP_DNSLOOKUPONERROR = new BoolCfgProperty(env, true, 1);
/*  400 */       c.addStanzaName(0, "TCP", "DNSLookupOnError");
/*  401 */       c.addPropertyHandlerNameSuffix("TCP.DNSLookupOnError");
/*  402 */       c = TCP_STRPORT = new IntCfgProperty(env, 0, 0);
/*  403 */       c.addStanzaName(0, "TCP", "StrPort");
/*  404 */       c.addPropertyHandlerNameSuffix("TCP.StrPort");
/*  405 */       c = TCP_ENDPORT = new IntCfgProperty(env, 0, 0);
/*  406 */       c.addStanzaName(0, "TCP", "EndPort");
/*  407 */       c.addPropertyHandlerNameSuffix("TCP.EndPort");
/*  408 */       c = TCP_CONNECT_TIMEOUT = new IntCfgProperty(env, -1, 0);
/*  409 */       c.addStanzaName(0, "TCP", "Connect_Timeout");
/*  410 */       c.addPropertyHandlerNameSuffix("TCP.Connect_Timeout");
/*  411 */       c = TCP_CLNTRCVBUFFSIZE = new IntCfgProperty(env, 32768, 1024);
/*  412 */       c.addStanzaName(0, "TCP", "ClntRcvBuffSize");
/*  413 */       c.addPropertyHandlerNameSuffix("TCP.ClntRcvBuffSize");
/*  414 */       c = TCP_CLNTSNDBUFFSIZE = new IntCfgProperty(env, 32768, 1024);
/*  415 */       c.addStanzaName(0, "TCP", "ClntSndBuffSize");
/*  416 */       c.addPropertyHandlerNameSuffix("TCP.ClntSndBuffSize");
/*      */ 
/*      */ 
/*      */       
/*  420 */       c = MESSAGEBUFFER_MAXIMUMSIZE = new IntCfgProperty(env, 100, 0, 999999);
/*  421 */       c.addStanzaName(0, "MessageBuffer", "MaximumSize");
/*  422 */       c.addPropertyHandlerNameSuffix("MessageBuffer.MaximumSize");
/*      */ 
/*      */       
/*  425 */       c = MESSAGEBUFFER_UPDATEPERCENTAGE = new IntCfgProperty(env, 20, 0, 100);
/*  426 */       c.addStanzaName(0, "MessageBuffer", "UpdatePercentage");
/*  427 */       c.addPropertyHandlerNameSuffix("MessageBuffer.UpdatePercentage");
/*      */ 
/*      */ 
/*      */       
/*  431 */       c = MESSAGEBUFFER_PURGETIME = new IntCfgProperty(env, 0, 0, 999999);
/*  432 */       c.addStanzaName(0, "MessageBuffer", "PurgeTime");
/*  433 */       c.addPropertyHandlerNameSuffix("MessageBuffer.PurgeTime");
/*  434 */       c = CLIENTEXITPATH_EXITSDEFAULTPATH = new StringCfgProperty(env, null, 1);
/*  435 */       c.addStanzaName(0, "ClientExitPath", "ExitsDefaultPath");
/*  436 */       c.addPropertyHandlerNameSuffix("ClientExitPath.ExitsDefaultPath");
/*  437 */       c = CLIENTEXITPATH_EXITSDEFAULTPATH64 = new StringCfgProperty(env, null, 1);
/*  438 */       c.addStanzaName(0, "ClientExitPath", "ExitsDefaultPath64");
/*  439 */       c.addPropertyHandlerNameSuffix("ClientExitPath.ExitsDefaultPath64");
/*  440 */       c = MESSAGEPROPERTIES_FORCE_RFH2 = new BoolCfgProperty(env, false, 1);
/*  441 */       c.addStanzaName(0, "MessageProperties", "PropctlToFORCE_RFH2");
/*  442 */       c.addPropertyHandlerNameSuffix("MessageProperties.PropctlToFORCE_RFH2");
/*  443 */       c = ENV_MQCLNTCF = new StringCfgProperty(env, null, 1);
/*  444 */       c.addPropertyHandlerNameSuffix("MQCLNTCF");
/*  445 */       c.addEnvironmentVariableName("MQCLNTCF");
/*  446 */       if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_WINDOWS) {
/*  447 */         c = ENV_MQ_FILE_PATH = new StringCfgProperty(env, "C:/Program Files/IBM/MQ", 1);
/*  448 */         c.addRegistryName("WorkPath");
/*      */       } else {
/*      */         
/*  451 */         c = ENV_MQ_FILE_PATH = new StringCfgProperty(env, "/var/mqm", 1);
/*      */       } 
/*  453 */       c.addPropertyHandlerNameSuffix("MQ_DATA_PATH");
/*  454 */       c.addEnvironmentVariableName("MQ_DATA_PATH");
/*  455 */       c = ENV_MQCHLURL = new StringCfgProperty(env, null, 1);
/*  456 */       c.addPropertyHandlerName("CCDTURL");
/*  457 */       c = ENV_MQ_LCLADDR = new StringCfgProperty(env, null, 1);
/*  458 */       c.addPropertyHandlerNameSuffix("MQ_LCLADDR");
/*  459 */       c.addEnvironmentVariableName("com.ibm.mq.localAddress");
/*  460 */       c = ENV_MQRCVBLKTO = new StringCfgProperty(env, null, 1);
/*  461 */       c.addPropertyHandlerNameSuffix("MQRCVBLKTO");
/*  462 */       c.addEnvironmentVariableName("com.ibm.mq.tuning.socketGrainTimeout");
/*  463 */       c.addStanzaName(0, "TCP", "blockingreceivetimeout");
/*      */       
/*  465 */       c = NEGOTIATIONFLOWTIMEOUT = new IntCfgProperty(env, 0, 0);
/*  466 */       c.addPropertyHandlerNameSuffix("TCP.QmgrNegotiationFlowTimeout");
/*  467 */       c.addEnvironmentVariableName("com.ibm.mq.cfg.TCP.QmgrNegotiationFlowTimeout");
/*  468 */       c.addStanzaName(0, "TCP", "QmgrNegotiationFlowTimeout");
/*      */       
/*  470 */       c = ENV_MQRCVBLKMIN = new IntCfgProperty(env, -1, 1);
/*  471 */       c.addPropertyHandlerNameSuffix("MQRCVBLKMIN");
/*  472 */       c.addEnvironmentVariableName("MQRCVBLKMIN");
/*  473 */       c.addStanzaName(0, "TCP", "minblockingreceivetimeout");
/*  474 */       c = ENV_MQ_COMM = new StringCfgProperty(env, null, 1);
/*  475 */       c.addPropertyHandlerNameSuffix("MQ_COMM");
/*  476 */       c = ENV_MQSSLFIPS = new BoolCfgProperty(env, false, 1);
/*  477 */       c.addPropertyHandlerNameSuffix("MQSSLFIPS");
/*  478 */       c.addEnvironmentVariableName("MQSSLFIPS");
/*  479 */       c = ENV_MQSSLRESET = new IntCfgProperty(env, -1, 0);
/*  480 */       c.addPropertyHandlerNameSuffix("MQSSLRESET");
/*  481 */       c.addEnvironmentVariableName("MQSSLRESET");
/*  482 */       c = ENV_MQSSLPOLLTIMEOUT = new IntCfgProperty(env, 10, 1);
/*  483 */       c.addPropertyHandlerNameSuffix("MQSSLPOLLTIMEOUT");
/*  484 */       c.addEnvironmentVariableName("MQSSLPOLLTIMEOUT");
/*  485 */       c = ALLOW_OUTBOUND_SNI = new BoolCfgProperty(env, true, 1);
/*  486 */       c.addPropertyHandlerNameSuffix("SSL.allowOutboundSNI");
/*  487 */       c.addStanzaName(0, "SSL", "AllowOutboundSNI");
/*  488 */       c = OUTBOUND_SNI = new ChoiceCfgProperty(env, new String[] { "CHANNEL", "HOSTNAME" });
/*  489 */       c.addPropertyHandlerNameSuffix("SSL.outboundSNI");
/*  490 */       c.addStanzaName(0, "SSL", "OutboundSNI");
/*  491 */       c = ENV_MQIPADDRV = new StringCfgProperty(env, null, 1);
/*  492 */       c.addPropertyHandlerNameSuffix("MQIPADDRV");
/*  493 */       c.addEnvironmentVariableName("MQIPADDRV");
/*  494 */       c = ENV_MQCCSID = new IntCfgProperty(env, -1, 0);
/*  495 */       c.addPropertyHandlerNameSuffix("MQCCSID");
/*  496 */       c.addEnvironmentVariableName("MQCCSID");
/*  497 */       c = ENV_MQTCPTIMEOUT = new IntCfgProperty(env, -1, 10000);
/*  498 */       c.addPropertyHandlerNameSuffix("MQTCPTIMEOUT");
/*  499 */       c.addEnvironmentVariableName("MQTCPTIMEOUT");
/*  500 */       c = TESTLOCATEINI_RULE = new StringCfgProperty(env, null, 1);
/*  501 */       c.addStanzaName(0, "TestLocateIni", "Rule");
/*  502 */       c.addStanzaName(1, "TestLocateIni", "Rule");
/*  503 */       c = webSphereServerTypeProperty = new StringCfgProperty(env, null, 1);
/*  504 */       c.addPropertyHandlerName("com.ibm.websphere.ServerType");
/*  505 */       c = requestedAdapterProperty = new StringCfgProperty(env, null, 1);
/*  506 */       c.addPropertyHandlerName("com.ibm.mq.adapter");
/*  507 */       c = smallMsgsBufferReductionThresholdProperty = new IntCfgProperty(env, 10, 0);
/*  508 */       c.addPropertyHandlerName("com.ibm.mq.jmqi.smallMsgBufferReductionThreshold");
/*  509 */       c = defaultMaxMsgSizeProperty = new IntCfgProperty(env, 4096, 0);
/*  510 */       c.addPropertyHandlerName("com.ibm.mq.jmqi.defaultMaxMsgSize");
/*  511 */       c = threadWaitTimeoutProperty = new IntCfgProperty(env, 20000, 0);
/*  512 */       c.addPropertyHandlerName("com.ibm.mq.jmqi.threadWaitTimeout");
/*  513 */       c = EXIT_CLASSPATH = new StringCfgProperty(env, null, 1);
/*  514 */       c.addPropertyHandlerName("com.ibm.mq.exitClasspath");
/*  515 */       c.addStanzaName(0, "ClientExitPath", "JavaExitsClasspath");
/*  516 */       c.addPropertyHandlerName("exitClasspath");
/*  517 */       c.addPropertyHandlerNameSuffix("ClientExitPath.JavaExitsClasspath");
/*  518 */       c = ENV_MQPUT1DEFSYNC = new BoolCfgProperty(env, false, 1);
/*  519 */       c.addStanzaName(0, "CHANNELS", "Put1DefaultAlwaysSync");
/*  520 */       c.addEnvironmentVariableName("MQPUT1DEFSYNC");
/*  521 */       c.addPropertyHandlerNameSuffix("Channels.Put1DefaultAlwaysSync");
/*  522 */       c = ENV_MQRMTREQ_POLLTIME = new IntCfgProperty(env, 10000, 3000);
/*  523 */       c.addPropertyHandlerNameSuffix("MQRMTREQ_POLLTIME");
/*  524 */       c.addPropertyHandlerName("com.ibm.mq.polling.RemoteRequestEntry");
/*  525 */       c = CHANNEL_RECON = new StringCfgProperty(env, "NO", 2);
/*  526 */       c.addStanzaName(0, "Channels", "DefRecon");
/*  527 */       c.addPropertyHandlerNameSuffix("Channels.DefRecon");
/*  528 */       c = CHANNEL_RECONDELAY = new StringCfgProperty(env, null, 1);
/*  529 */       c.addStanzaName(0, "Channels", "ReconDelay");
/*  530 */       c.addPropertyHandlerNameSuffix("Channels.ReconDelay");
/*  531 */       c = JMQI_LIB_PATH = new StringCfgProperty(env, null, 1);
/*  532 */       c.addPropertyHandlerNameSuffix("jmqi.libpath");
/*  533 */       c = terminationModeProperty = new StringCfgProperty(env, null, 1);
/*  534 */       c.addPropertyHandlerName("com.ibm.mq.termination.mode");
/*  535 */       c = CCSID_MAPCCSID932TOSJIS = new BoolCfgProperty(env, false, 1);
/*  536 */       c.addEnvironmentVariableName("CCSID.MAPCCSID932TOSJIS");
/*  537 */       c.addPropertyHandlerNameSuffix("CCSID.MapCCSID932ToSJIS");
/*  538 */       c.addStanzaName(0, "CCSID", "MapCCSID932ToSJIS");
/*  539 */       c = CCSID_MAPCCSID1200TOUNICODEBIGUNMARKED = new BoolCfgProperty(env, false, 1);
/*  540 */       c.addEnvironmentVariableName("CCSID.MAPCCSID1200TOUNICODEBIGUNMARKED");
/*  541 */       c.addPropertyHandlerNameSuffix("CCSID.MapCCSID1200ToUnicodeBigUnmarked");
/*  542 */       c.addStanzaName(0, "CCSID", "MapCCSID1200ToUnicodeBigUnmarked");
/*  543 */       c = skipQSGNameCheck = new BoolCfgProperty(env, false, -1);
/*  544 */       c.addPropertyHandlerNameSuffix("skipQSGNameCheck");
/*  545 */       c = disableXACCDTCheck = new BoolCfgProperty(env, false, 2);
/*  546 */       c.addEnvironmentVariableName("com.ibm.mq.disableXACCDTCheck");
/*  547 */       c.addPropertyHandlerName("com.ibm.mq.disableXACCDTCheck");
/*  548 */       c = CCSID_MAPUTF16BYTEORDERBYCCSID = new BoolCfgProperty(env, false, 1);
/*  549 */       c.addEnvironmentVariableName("CCSID.MAPUTF16BYTEORDERBYCCSID");
/*  550 */       c.addPropertyHandlerNameSuffix("CCSID.MapUtf16ByteOrderByCCSID");
/*  551 */       c.addStanzaName(0, "CCSID", "MapUtf16ByteOrderByCCSID");
/*  552 */       c = USE_MQCSP_AUTHENTICATION = new BoolCfgProperty(env, true, 1);
/*  553 */       c.addEnvironmentVariableName("com.ibm.mq.jmqi.useMQCSPauthentication");
/*  554 */       c.addPropertyHandlerNameSuffix("jmqi.useMQCSPauthentication");
/*  555 */       c.addStanzaName(0, "JMQI", "useMQCSPauthentication");
/*      */       
/*  557 */       c = PASSWORD_PROTECTION = new ChoiceCfgProperty(env, new String[] { "Compatible", "Always", "Optional" });
/*  558 */       c.addEnvironmentVariableName("com.ibm.mq.jmqi.PasswordProtection");
/*  559 */       c.addPropertyHandlerNameSuffix("jmqi.PasswordProtection");
/*  560 */       c.addStanzaName(0, "Channels", "PasswordProtection");
/*      */       
/*  562 */       c = AMQ_RANDOM_NUMBER_TYPE = new ChoiceCfgProperty(env, new String[] { "Standard", "Fast" });
/*  563 */       c.addEnvironmentVariableName("AMQ_RANDOM_NUMBER_TYPE");
/*  564 */       c.addPropertyHandlerNameSuffix("jmqi.AmqRandomNumberType");
/*      */       
/*  566 */       c = IGNORE_CERT_LABEL = new BoolCfgProperty(env, false, 2);
/*  567 */       c.addPropertyHandlerName("com.ibm.mq.jmqi.ignoreCCDTCertificateLabel");
/*      */       
/*  569 */       c = UNMAPPABLE_ACTION = new ChoiceCfgProperty(env, new String[] { UNMAPPABLE_ACTION_REPORT, UNMAPPABLE_ACTION_REPLACE, UNMAPPABLE_ACTION_IGNORE });
/*  570 */       c.addPropertyHandlerNameSuffix("jmqi.UnmappableCharacterAction");
/*      */       
/*  572 */       c = UNMAPPABLE_REPLACEMENT = new IntCfgProperty(env, 63, 0, 255);
/*  573 */       c.addPropertyHandlerNameSuffix("jmqi.UnmappableCharacterReplacement");
/*      */       
/*  575 */       c = ENV_AMQ_DISABLE_CLIENT_AMS = new BoolCfgProperty(env, false, -1);
/*  576 */       c.addEnvironmentVariableName("AMQ_DISABLE_CLIENT_AMS");
/*  577 */       c.addPropertyHandlerNameSuffix("AMQ_DISABLE_CLIENT_AMS");
/*  578 */       c.addStanzaName(0, "Security", "DisableClientAMS");
/*      */       
/*  580 */       c = CCSID_MAPCCSID1200FAMILYTOSPECIFICCHARSET = new StringCfgProperty(env, null, 1);
/*  581 */       c.addEnvironmentVariableName("CCSID.MAPCCSID1200FAMILYTOSPECIFICCHARSET");
/*  582 */       c.addPropertyHandlerNameSuffix("CCSID.MapCcsid1200FamilyToSpecificCharset");
/*  583 */       c.addStanzaName(0, "CCSID", "MapCcsid1200FamilyToSpecificCharset");
/*      */       
/*  585 */       c = XA_TRANSACTION_MANAGER = new StringCfgProperty(env, null, 1);
/*  586 */       c.addPropertyHandlerNameSuffix("jmqi.xaTransactionManager");
/*      */       
/*  588 */       initialised = true;
/*      */     } 
/*      */     
/*  591 */     if (Trace.isOn) {
/*  592 */       Trace.exit("com.ibm.mq.jmqi.internal.Configuration", "initialiseProperties(JmqiEnvironment)");
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
/*      */   public String getStringValue(StringCfgProperty stringProperty) {
/*  605 */     String value = stringProperty.parseValue(getProperty(stringProperty));
/*      */     
/*  607 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getChoiceValue(ChoiceCfgProperty choiceProperty) {
/*  618 */     String propertyString = getProperty(choiceProperty);
/*  619 */     if (propertyString == null) {
/*  620 */       propertyString = choiceProperty.validChoices[0];
/*      */     }
/*  622 */     String value = choiceProperty.parseValue(propertyString);
/*      */     
/*  624 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getIntValue(IntCfgProperty intProperty) {
/*  635 */     int value = intProperty.parseValue(getProperty(intProperty));
/*      */     
/*  637 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getBoolValue(BoolCfgProperty boolProperty) {
/*  648 */     boolean value = boolProperty.parseValue(getProperty(boolProperty));
/*      */     
/*  650 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean wasDefaulted(CfgProperty cfgProperty) {
/*  658 */     return cfgProperty.wasDefaulted;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class CfgProperty
/*      */     extends JmqiObject
/*      */   {
/*      */     protected ArrayList<String>[] stanzaNames;
/*      */ 
/*      */     
/*  670 */     protected ArrayList<String> propertyHandlerNames = new ArrayList<>();
/*  671 */     protected ArrayList<String> environmentVariableNames = new ArrayList<>();
/*  672 */     protected ArrayList<String> registryNames = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean wasDefaulted = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private CfgProperty(JmqiEnvironment env) {
/*  685 */       super(env);
/*  686 */       if (Trace.isOn) {
/*  687 */         Trace.entry(this, "com.ibm.mq.jmqi.internal.CfgProperty", "<init>(JmqiEnvironment)", new Object[] { env });
/*      */       }
/*      */       
/*  690 */       this.stanzaNames = (ArrayList<String>[])new ArrayList[2];
/*  691 */       for (int i = 0; i < 2; i++) {
/*  692 */         this.stanzaNames[i] = new ArrayList<>();
/*      */       }
/*      */       
/*  695 */       if (Trace.isOn) {
/*  696 */         Trace.exit(this, "com.ibm.mq.jmqi.internal.CfgProperty", "<init>(JmqiEnvironment)");
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
/*      */     
/*      */     void addStanzaName(int index, String stanza, String name) {
/*  710 */       String fullname = stanza + "." + name;
/*  711 */       this.stanzaNames[index].add(fullname);
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
/*      */     void addPropertyHandlerName(String propertyHandlerName) {
/*  725 */       this.propertyHandlerNames.add(propertyHandlerName);
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
/*      */     void addPropertyHandlerNameSuffix(String propertyHandlerName) {
/*  739 */       addPropertyHandlerName("com.ibm.mq.cfg." + propertyHandlerName);
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
/*      */     void addEnvironmentVariableName(String environmentVariableName) {
/*  751 */       this.environmentVariableNames.add(environmentVariableName);
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
/*      */     void addRegistryName(String registryName) {
/*  764 */       this.registryNames.add(registryName);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class StringCfgProperty
/*      */     extends CfgProperty
/*      */   {
/*  774 */     private int minLength = -1;
/*  775 */     private int maxLength = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private String defaultValue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private StringCfgProperty(JmqiEnvironment env, String defaultValue, int minLength) {
/*  794 */       super(env);
/*  795 */       this.defaultValue = defaultValue;
/*  796 */       this.minLength = minLength;
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
/*      */     protected String parseValue(String stringValue) {
/*  820 */       boolean valid = (stringValue != null);
/*  821 */       valid = (valid && (this.minLength < 0 || stringValue.length() >= this.minLength));
/*  822 */       valid = (valid && (this.maxLength < 0 || stringValue.length() <= this.maxLength));
/*      */       
/*  824 */       return valid ? stringValue : this.defaultValue;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class ChoiceCfgProperty
/*      */     extends CfgProperty
/*      */   {
/*      */     String[] validChoices;
/*      */ 
/*      */ 
/*      */     
/*      */     protected ChoiceCfgProperty(JmqiEnvironment env, String[] validChoices) {
/*  838 */       super(env);
/*  839 */       if (Trace.isOn) {
/*  840 */         Trace.entry(this, "com.ibm.mq.jmqi.internal.ChoiceCfgProperty", "<init>(JmqiEnvironment,String [ ])", new Object[] { env, validChoices });
/*      */       }
/*      */       
/*  843 */       this.validChoices = new String[validChoices.length];
/*  844 */       for (int i = 0; i < validChoices.length; i++) {
/*  845 */         this.validChoices[i] = validChoices[i].toUpperCase();
/*      */       }
/*  847 */       if (Trace.isOn) {
/*  848 */         Trace.exit(this, "com.ibm.mq.jmqi.internal.ChoiceCfgProperty", "<init>(JmqiEnvironment,String [ ])");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected String parseValue(String value) {
/*  855 */       if (Trace.isOn) {
/*  856 */         Trace.entry(this, "com.ibm.mq.jmqi.internal.ChoiceCfgProperty", "parseValue(String)", new Object[] { value });
/*      */       }
/*      */       
/*  859 */       String valueAsUpper = value.toUpperCase();
/*  860 */       for (String choice : this.validChoices) {
/*  861 */         if (choice.equals(valueAsUpper)) {
/*  862 */           if (Trace.isOn) {
/*  863 */             Trace.exit(this, "com.ibm.mq.jmqi.internal.ChoiceCfgProperty", "parseValue(String)", choice, 1);
/*      */           }
/*      */           
/*  866 */           return choice;
/*      */         } 
/*      */       } 
/*      */       
/*  870 */       if (Trace.isOn) {
/*  871 */         Trace.exit(this, "com.ibm.mq.jmqi.internal.ChoiceCfgProperty", "parseValue(String)", this.validChoices[0], 2);
/*      */       }
/*      */       
/*  874 */       return this.validChoices[0];
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class IntCfgProperty
/*      */     extends CfgProperty
/*      */   {
/*      */     private boolean minSet;
/*      */     
/*      */     private boolean maxSet;
/*      */     
/*      */     private int minValue;
/*      */     
/*      */     private int maxValue;
/*      */     
/*      */     private int defaultValue;
/*      */ 
/*      */     
/*      */     protected IntCfgProperty(JmqiEnvironment env, int defaultValue) {
/*  894 */       super(env);
/*  895 */       this.defaultValue = defaultValue;
/*  896 */       this.minSet = false;
/*  897 */       this.maxSet = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected IntCfgProperty(JmqiEnvironment env, int defaultValue, int minValue) {
/*  907 */       super(env);
/*  908 */       this.defaultValue = defaultValue;
/*  909 */       this.minValue = minValue;
/*  910 */       this.minSet = true;
/*  911 */       this.maxSet = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected IntCfgProperty(JmqiEnvironment env, int defaultValue, int minValue, int maxValue) {
/*  922 */       super(env);
/*  923 */       this.defaultValue = defaultValue;
/*  924 */       this.minValue = minValue;
/*  925 */       this.minSet = true;
/*  926 */       this.maxValue = maxValue;
/*  927 */       this.maxSet = true;
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
/*      */     protected int parseValue(String stringValue) {
/*  940 */       boolean valid = (stringValue != null);
/*  941 */       int value = 0;
/*  942 */       if (valid) {
/*      */         try {
/*  944 */           value = Integer.parseInt(stringValue);
/*      */         }
/*  946 */         catch (NumberFormatException e) {
/*  947 */           valid = false;
/*      */         } 
/*  949 */         valid = (valid && (!this.minSet || value >= this.minValue));
/*  950 */         valid = (valid && (!this.maxSet || value <= this.maxValue));
/*      */       } 
/*  952 */       return valid ? value : this.defaultValue;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class BoolCfgProperty
/*      */     extends CfgProperty
/*      */   {
/*      */     private static final int Y_N = 1;
/*      */ 
/*      */     
/*      */     private static final int TRUE_FALSE = 2;
/*      */ 
/*      */     
/*      */     private static final int ONE_ZERO = 3;
/*      */ 
/*      */     
/*      */     private static final int ANY = -1;
/*      */     
/*      */     private int type;
/*      */     
/*      */     private boolean defaultValue;
/*      */ 
/*      */     
/*      */     private BoolCfgProperty(JmqiEnvironment env, boolean defaultValue, int type) {
/*  978 */       super(env);
/*  979 */       this.defaultValue = defaultValue;
/*  980 */       this.type = type;
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
/*      */     protected boolean parseValue(String stringValue) {
/*  994 */       boolean value = this.defaultValue;
/*  995 */       if (stringValue != null) {
/*  996 */         if ((this.type == -1 || this.type == 1) && stringValue.startsWith("Y")) {
/*  997 */           value = true;
/*      */         }
/*  999 */         else if ((this.type == -1 || this.type == 1) && stringValue.startsWith("N")) {
/* 1000 */           value = false;
/*      */         }
/* 1002 */         else if ((this.type == -1 || this.type == 2) && stringValue.equalsIgnoreCase("TRUE")) {
/* 1003 */           value = true;
/*      */         }
/* 1005 */         else if ((this.type == -1 || this.type == 2) && stringValue.equalsIgnoreCase("FALSE")) {
/* 1006 */           value = false;
/*      */         }
/* 1008 */         else if ((this.type == -1 || this.type == 3) && stringValue.equalsIgnoreCase("1")) {
/* 1009 */           value = true;
/*      */         }
/* 1011 */         else if ((this.type == -1 || this.type == 3) && stringValue.equalsIgnoreCase("0")) {
/* 1012 */           value = false;
/*      */         } 
/*      */       }
/*      */       
/* 1016 */       return value;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private InputStream findClientIni() {
/* 1026 */     if (Trace.isOn) {
/* 1027 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.Configuration", "findClientIni()");
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
/* 1039 */     InputStream iniStream = null;
/*      */     
/* 1041 */     iniStream = iniFileAsStreamEnvVar();
/*      */     
/* 1043 */     if (iniStream == null) {
/* 1044 */       iniStream = iniFileAsStreamPwd("mqclient.ini");
/*      */     }
/*      */     
/* 1047 */     if (iniStream == null) {
/* 1048 */       iniStream = iniFileAsStreamWmqDataDir("mqclient.ini");
/*      */     }
/*      */     
/* 1051 */     if (iniStream == null) {
/* 1052 */       iniStream = iniFileAsStreamUserHome("mqclient.ini");
/*      */     }
/*      */     
/* 1055 */     if (Trace.isOn) {
/* 1056 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.Configuration", "findClientIni()", iniStream);
/*      */     }
/* 1058 */     return iniStream;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private InputStream findAltIni() {
/* 1067 */     if (Trace.isOn) {
/* 1068 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.Configuration", "findAltIni()");
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
/* 1080 */     InputStream iniStream = null;
/*      */     
/* 1082 */     iniStream = iniFileAsStreamPwd("mqs.ini");
/*      */     
/* 1084 */     if (iniStream == null) {
/* 1085 */       iniStream = iniFileAsStreamWmqDataDir("mqs.ini");
/*      */     }
/*      */     
/* 1088 */     if (iniStream == null) {
/* 1089 */       iniStream = iniFileAsStreamUserHome("mqs.ini");
/*      */     }
/*      */     
/* 1092 */     if (Trace.isOn) {
/* 1093 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.Configuration", "findAltIni()", iniStream);
/*      */     }
/* 1095 */     return iniStream;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private InputStream iniFileAsStreamEnvVar() {
/* 1103 */     if (Trace.isOn) {
/* 1104 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.Configuration", "iniFileAsStreamEnvVar()");
/*      */     }
/* 1106 */     InputStream inputStream = null;
/*      */     try {
/* 1108 */       String clientIniEnvVar = getStringValue(ENV_MQCLNTCF);
/* 1109 */       if (clientIniEnvVar != null && clientIniEnvVar.length() > 0) {
/*      */         
/*      */         try {
/* 1112 */           inputStream = (new URL(clientIniEnvVar)).openStream();
/*      */         }
/* 1114 */         catch (MalformedURLException e) {
/* 1115 */           if (Trace.isOn) {
/* 1116 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.internal.Configuration", "iniFileAsStreamEnvVar()", e, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1121 */           File iniFile = new File(clientIniEnvVar);
/* 1122 */           if (iniFile.isFile()) {
/* 1123 */             inputStream = JmqiTools.newFileInputStream(clientIniEnvVar);
/*      */           }
/*      */         }
/*      */       
/*      */       }
/* 1128 */     } catch (IOException e) {
/* 1129 */       if (Trace.isOn) {
/* 1130 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.internal.Configuration", "iniFileAsStreamEnvVar()", e, 2);
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1135 */     catch (AccessControlException e) {
/* 1136 */       if (Trace.isOn) {
/* 1137 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.internal.Configuration", "iniFileAsStreamEnvVar()", e, 3);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1143 */     if (Trace.isOn) {
/* 1144 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.Configuration", "iniFileAsStreamEnvVar()", inputStream);
/*      */     }
/*      */     
/* 1147 */     return inputStream;
/*      */   }
/*      */   
/*      */   private InputStream iniFileAsStreamPwd(String fileName) {
/* 1151 */     if (Trace.isOn) {
/* 1152 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.Configuration", "iniFileAsStreamPwd(String)", new Object[] { fileName });
/*      */     }
/*      */     
/* 1155 */     InputStream inputStream = null;
/*      */     try {
/* 1157 */       File pwdFile = new File(fileName);
/* 1158 */       if (pwdFile.isFile()) {
/* 1159 */         inputStream = new FileInputStream(pwdFile);
/*      */       }
/*      */     }
/* 1162 */     catch (IOException e) {
/* 1163 */       if (Trace.isOn) {
/* 1164 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.internal.Configuration", "iniFileAsStreamPwd(String)", e, 1);
/*      */ 
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1170 */     catch (AccessControlException e) {
/* 1171 */       if (Trace.isOn) {
/* 1172 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.internal.Configuration", "iniFileAsStreamPwd(String)", e, 2);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1177 */     if (Trace.isOn) {
/* 1178 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.Configuration", "iniFileAsStreamPwd(String)", inputStream);
/*      */     }
/*      */     
/* 1181 */     return inputStream;
/*      */   }
/*      */   
/*      */   private InputStream iniFileAsStreamWmqDataDir(String fileName) {
/* 1185 */     if (Trace.isOn) {
/* 1186 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.Configuration", "iniFileAsStreamWmqDataDir(String)", new Object[] { fileName });
/*      */     }
/*      */     
/* 1189 */     InputStream inputStream = null;
/*      */     try {
/* 1191 */       String filePathEnvVar = getStringValue(ENV_MQ_FILE_PATH);
/*      */       
/* 1193 */       filePathEnvVar = filePathEnvVar + File.separator + fileName;
/* 1194 */       File iniFile = new File(filePathEnvVar);
/* 1195 */       if (iniFile.isFile()) {
/* 1196 */         inputStream = new FileInputStream(iniFile);
/*      */       }
/*      */     }
/* 1199 */     catch (IOException e) {
/* 1200 */       if (Trace.isOn) {
/* 1201 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.internal.Configuration", "iniFileAsStreamWmqDataDir(String)", e, 1);
/*      */ 
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1207 */     catch (AccessControlException e) {
/* 1208 */       if (Trace.isOn) {
/* 1209 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.internal.Configuration", "iniFileAsStreamWmqDataDir(String)", e, 2);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1214 */     if (Trace.isOn) {
/* 1215 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.Configuration", "iniFileAsStreamWmqDataDir(String)", inputStream);
/*      */     }
/*      */     
/* 1218 */     return inputStream;
/*      */   }
/*      */   
/*      */   private InputStream iniFileAsStreamUserHome(String fileName) {
/* 1222 */     if (Trace.isOn) {
/* 1223 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.Configuration", "iniFileAsStreamUserHome(String)", new Object[] { fileName });
/*      */     }
/*      */     
/* 1226 */     InputStream inputStream = null;
/*      */     try {
/* 1228 */       String userHome = JmqiTools.getUserHome();
/* 1229 */       if (userHome != null) {
/* 1230 */         userHome = userHome + File.separator + fileName;
/* 1231 */         File iniFile = new File(userHome);
/* 1232 */         if (iniFile.isFile()) {
/* 1233 */           inputStream = new FileInputStream(iniFile);
/*      */         }
/*      */       }
/*      */     
/* 1237 */     } catch (IOException e) {
/* 1238 */       if (Trace.isOn) {
/* 1239 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.internal.Configuration", "iniFileAsStreamUserHome(String)", e, 1);
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1244 */     catch (AccessControlException e) {
/* 1245 */       if (Trace.isOn) {
/* 1246 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.internal.Configuration", "iniFileAsStreamUserHome(String)", e, 2);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1251 */     if (Trace.isOn) {
/* 1252 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.Configuration", "iniFileAsStreamUserHome(String)", inputStream);
/*      */     }
/*      */     
/* 1255 */     return inputStream;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getProperty(CfgProperty cfgProperty) {
/* 1264 */     String result = null;
/*      */ 
/*      */     
/*      */     int i;
/*      */     
/* 1269 */     for (i = 0; result == null && i < cfgProperty.propertyHandlerNames.size(); i++) {
/* 1270 */       String name = cfgProperty.propertyHandlerNames.get(i);
/* 1271 */       result = this.env.getProperty(name);
/*      */       
/* 1273 */       if (Trace.isOn && result != null) {
/* 1274 */         Trace.data(this, "com.ibm.mq.jmqi.internal.Configuration", "getProperty(CfgProperty)", "(JVM Property handler)", result);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1282 */     for (i = 0; result == null && i < cfgProperty.environmentVariableNames.size(); i++) {
/* 1283 */       String name = cfgProperty.environmentVariableNames.get(i);
/* 1284 */       result = JmqiTools.getEnvironmentProperty(name);
/*      */       
/* 1286 */       if (Trace.isOn && result != null) {
/* 1287 */         Trace.data(this, "com.ibm.mq.jmqi.internal.Configuration", "getProperty(CfgProperty)", "(Environment variable)", result);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1295 */     for (i = 0; result == null && i < 2; i++) {
/* 1296 */       ArrayList<String> list = cfgProperty.stanzaNames[i];
/* 1297 */       for (int j = 0; result == null && j < list.size(); j++) {
/* 1298 */         String key = "com.ibm.mq.cfg." + (String)list.get(j);
/* 1299 */         result = this.configurationFiles[i].getAttributeValue(key);
/*      */       } 
/*      */       
/* 1302 */       if (Trace.isOn && result != null) {
/* 1303 */         Trace.data(this, "com.ibm.mq.jmqi.internal.Configuration", "getProperty(CfgProperty)", "(Configuration INI files)", result);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1311 */     for (i = 0; result == null && i < cfgProperty.registryNames.size(); i++) {
/* 1312 */       String name = cfgProperty.registryNames.get(i);
/* 1313 */       result = JmqiTools.getRegistryProperty(name);
/*      */       
/* 1315 */       if (Trace.isOn && result != null) {
/* 1316 */         Trace.data(this, "com.ibm.mq.jmqi.internal.Configuration", "getProperty(CfgProperty)", "(Windows registry)", result);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1321 */     if (result == null) {
/* 1322 */       if (Trace.isOn) {
/* 1323 */         Trace.data(this, "com.ibm.mq.jmqi.internal.Configuration", "getProperty(CfgProperty)", "(Configuration property not set - assuming default value)", result);
/*      */       }
/*      */       
/* 1326 */       cfgProperty.wasDefaulted = true;
/*      */     } else {
/*      */       
/* 1329 */       cfgProperty.wasDefaulted = false;
/*      */     } 
/*      */     
/* 1332 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getTcpKeepAliveValue() {
/* 1339 */     boolean value = TCP_KEEPALIVE.defaultValue;
/* 1340 */     String mqComm = getStringValue(ENV_MQ_COMM);
/* 1341 */     if (mqComm != null) {
/* 1342 */       mqComm = mqComm.toLowerCase();
/*      */       
/* 1344 */       if (mqComm.indexOf("nokeepalive") >= 0) {
/* 1345 */         value = false;
/*      */       } else {
/*      */         
/* 1348 */         value = true;
/*      */       } 
/*      */     } else {
/*      */       
/* 1352 */       value = getBoolValue(TCP_KEEPALIVE);
/*      */     } 
/*      */     
/* 1355 */     if (Trace.isOn) {
/* 1356 */       Trace.data(this, "com.ibm.mq.jmqi.internal.Configuration", "getTcpKeepAliveValue()", "getter", 
/* 1357 */           Boolean.valueOf(value));
/*      */     }
/* 1359 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getTcpLingerValue() {
/* 1366 */     boolean value = TCP_LINGER.defaultValue;
/* 1367 */     String mqComm = getStringValue(ENV_MQ_COMM);
/* 1368 */     if (mqComm != null) {
/* 1369 */       mqComm = mqComm.toLowerCase();
/*      */       
/* 1371 */       if (mqComm.indexOf("linger") >= 0) {
/* 1372 */         value = true;
/*      */       } else {
/*      */         
/* 1375 */         value = false;
/*      */       } 
/*      */     } 
/*      */     
/* 1379 */     if (Trace.isOn) {
/* 1380 */       Trace.data(this, "com.ibm.mq.jmqi.internal.Configuration", "getTcpLingerValue()", "getter", 
/* 1381 */           Boolean.valueOf(value));
/*      */     }
/* 1383 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getJavaExitsClasspath() {
/* 1390 */     String traceRet1 = getStringValue(EXIT_CLASSPATH);
/* 1391 */     if (Trace.isOn) {
/* 1392 */       Trace.data(this, "com.ibm.mq.jmqi.internal.Configuration", "getJavaExitsClasspath()", "getter", traceRet1);
/*      */     }
/*      */     
/* 1395 */     return traceRet1;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\Configuration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */