/*      */ package com.ibm.mq.jmqi.remote.impl;
/*      */ 
/*      */ import com.ibm.mq.exits.MQCD;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiUtils;
/*      */ import com.ibm.mq.jmqi.MQSCO;
/*      */ import com.ibm.mq.jmqi.internal.Configuration;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.mq.jmqi.remote.api.RemoteFAP;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpSOCKACT;
/*      */ import com.ibm.mq.jmqi.remote.rfp.RfpTSH;
/*      */ import com.ibm.mq.jmqi.remote.util.RemotePeerName;
/*      */ import com.ibm.mq.jmqi.remote.util.RemoteSSLCRLHelper;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.commonservices.util.Cruise;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.math.BigInteger;
/*      */ import java.net.Inet4Address;
/*      */ import java.net.Inet6Address;
/*      */ import java.net.InetAddress;
/*      */ import java.net.InetSocketAddress;
/*      */ import java.net.Socket;
/*      */ import java.net.SocketAddress;
/*      */ import java.net.SocketException;
/*      */ import java.net.SocketTimeoutException;
/*      */ import java.net.UnknownHostException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.security.AccessController;
/*      */ import java.security.GeneralSecurityException;
/*      */ import java.security.KeyStore;
/*      */ import java.security.NoSuchAlgorithmException;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.security.PrivilegedActionException;
/*      */ import java.security.PrivilegedExceptionAction;
/*      */ import java.security.Provider;
/*      */ import java.security.Security;
/*      */ import java.security.cert.Certificate;
/*      */ import java.security.cert.X509Certificate;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import javax.net.ssl.HandshakeCompletedEvent;
/*      */ import javax.net.ssl.HandshakeCompletedListener;
/*      */ import javax.net.ssl.KeyManager;
/*      */ import javax.net.ssl.KeyManagerFactory;
/*      */ import javax.net.ssl.SNIHostName;
/*      */ import javax.net.ssl.SNIServerName;
/*      */ import javax.net.ssl.SSLContext;
/*      */ import javax.net.ssl.SSLParameters;
/*      */ import javax.net.ssl.SSLPeerUnverifiedException;
/*      */ import javax.net.ssl.SSLSession;
/*      */ import javax.net.ssl.SSLSocket;
/*      */ import javax.net.ssl.SSLSocketFactory;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RemoteTCPConnection
/*      */   extends RemoteConnection
/*      */   implements HandshakeCompletedListener
/*      */ {
/*      */   private static final int DEFAULT_BUFFER_SIZE = 32768;
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteTCPConnection.java";
/*      */   
/*      */   static {
/*   96 */     if (Trace.isOn) {
/*   97 */       Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteTCPConnection.java");
/*      */     }
/*      */     
/*      */     try {
/*  101 */       Class<?> cls = Class.forName("javax.net.ssl.SNIHostName");
/*  102 */       if (cls != null) {
/*  103 */         canDoSNI = true;
/*      */       } else {
/*      */         
/*  106 */         canDoSNI = false;
/*      */       }
/*      */     
/*  109 */     } catch (Exception e) {
/*  110 */       if (Trace.isOn) {
/*  111 */         Trace.catchBlock("com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "static()", e);
/*      */       }
/*  113 */       canDoSNI = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isSecure = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean canDoSNI;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean readOutboundSNI = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private Socket socket;
/*      */ 
/*      */ 
/*      */   
/*  139 */   private SSLSocket secureSocket = null;
/*      */ 
/*      */   
/*      */   private InputStream sockInStream;
/*      */ 
/*      */   
/*      */   private OutputStream sockOutStream;
/*      */ 
/*      */   
/*  148 */   private SSLSocketFactory sslSocketFactory = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Collection<?> sslCertStores;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean customSslSocketFactoryUsed = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean fipsRequired = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isTLS13 = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  175 */   private static volatile Boolean inFipsMode = null;
/*      */ 
/*      */   
/*  178 */   private String cipherSuite = null;
/*      */ 
/*      */   
/*      */   private boolean handshakeComplete = false;
/*      */ 
/*      */   
/*  184 */   private Object handshakeCompleteLock = new HandshakeCompleteLock();
/*      */ 
/*      */   
/*  187 */   private RemoteSSLCRLHelper crlHelper = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  193 */   private X509Certificate peerCertificate = null;
/*      */ 
/*      */   
/*  196 */   private String peerIssuerDN = null;
/*      */ 
/*      */   
/*  199 */   private String socketIpAddress = null;
/*      */ 
/*      */   
/*  202 */   private int socketIpAddressPort = 0;
/*      */ 
/*      */   
/*  205 */   private int socketPort = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  211 */   private static Map<String, Integer> localAddrPorts = new ConcurrentHashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int firstReadTimeout;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int sslResetPollTimeout;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  229 */   private int infiniteWaitChunkTime = 5000;
/*      */   
/*      */   private static class SendLock {
/*      */     private SendLock() {}
/*      */   }
/*      */   
/*  235 */   private SendLock sendLock = new SendLock();
/*      */   
/*      */   private final int defaultPortStart;
/*      */   
/*      */   private final int defaultPortEnd;
/*      */   
/*      */   private final int connectTimeout;
/*      */   
/*      */   private final int sndBuffSize;
/*      */   
/*      */   private final int rcvBuffSize;
/*      */   
/*      */   private final String mqRcvBlkTo;
/*      */   
/*      */   private int qmgrNegotiationFlowTimeout;
/*      */   
/*      */   private final int mqRcvBlkMin;
/*      */   
/*      */   private final boolean tcpKeepAlive;
/*      */   
/*      */   private final boolean tcpLinger;
/*      */   
/*      */   private boolean allowSNI;
/*      */   
/*      */   private String outboundSNI;
/*      */   
/*      */   private final boolean dnsLookupOnError;
/*      */ 
/*      */   
/*      */   RemoteTCPConnection(JmqiEnvironment env, RemoteConnectionSpecification connectionSpec, RemoteFAP fap, String qMgrName, int connectOptions, int maxFapLevel) throws JmqiException {
/*  265 */     super(env, connectionSpec.getJmqiFlags());
/*  266 */     if (Trace.isOn) {
/*  267 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "<init>(JmqiEnvironment,RemoteConnectionSpecification,RemoteFAP,String,int,int)", new Object[] { env, connectionSpec, fap, qMgrName, 
/*      */             
/*  269 */             Integer.valueOf(connectOptions), 
/*  270 */             Integer.valueOf(maxFapLevel) });
/*      */     }
/*  272 */     Configuration config = env.getConfiguration();
/*  273 */     this.sslResetPollTimeout = config.getIntValue(Configuration.ENV_MQSSLPOLLTIMEOUT);
/*  274 */     this.defaultPortStart = config.getIntValue(Configuration.TCP_STRPORT);
/*  275 */     this.defaultPortEnd = config.getIntValue(Configuration.TCP_ENDPORT);
/*  276 */     this.connectTimeout = config.getIntValue(Configuration.TCP_CONNECT_TIMEOUT);
/*  277 */     this.sndBuffSize = config.getIntValue(Configuration.TCP_CLNTSNDBUFFSIZE);
/*  278 */     this.rcvBuffSize = config.getIntValue(Configuration.TCP_CLNTRCVBUFFSIZE);
/*  279 */     this.mqRcvBlkTo = config.getStringValue(Configuration.ENV_MQRCVBLKTO);
/*  280 */     this.qmgrNegotiationFlowTimeout = config.getIntValue(Configuration.NEGOTIATIONFLOWTIMEOUT);
/*  281 */     this.mqRcvBlkMin = config.getIntValue(Configuration.ENV_MQRCVBLKMIN) * 1000;
/*  282 */     this.tcpKeepAlive = config.getTcpKeepAliveValue();
/*  283 */     this.tcpLinger = config.getTcpLingerValue();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  288 */     this.outboundSNI = config.getProperty((Configuration.CfgProperty)Configuration.OUTBOUND_SNI);
/*  289 */     if (this.outboundSNI != null) {
/*  290 */       this.outboundSNI = this.outboundSNI.toUpperCase();
/*  291 */       readOutboundSNI = true;
/*  292 */       if (this.outboundSNI.contentEquals("HOSTNAME")) {
/*  293 */         this.allowSNI = false;
/*      */       }
/*  295 */       else if (this.outboundSNI.contentEquals("CHANNEL")) {
/*  296 */         this.allowSNI = true;
/*      */       }
/*      */       else {
/*      */         
/*  300 */         this.allowSNI = true;
/*      */       } 
/*      */     } 
/*  303 */     if (!readOutboundSNI) {
/*  304 */       this.allowSNI = config.getBoolValue(Configuration.ALLOW_OUTBOUND_SNI);
/*      */     }
/*  306 */     this.dnsLookupOnError = config.getBoolValue(Configuration.TCP_DNSLOOKUPONERROR);
/*      */ 
/*      */     
/*  309 */     this.remoteConnectionSpec = connectionSpec;
/*  310 */     this.fap = fap;
/*  311 */     this.reconnectCycle = fap.getReconnectCycle();
/*  312 */     this.qMgrName = qMgrName;
/*  313 */     this.connectionOptions = connectOptions;
/*  314 */     checkAndSetCcsidOverride(connectionSpec.getCcsid());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  321 */       this.clientConn = (MQCD)connectionSpec.getMqcd().clone();
/*      */     }
/*  323 */     catch (CloneNotSupportedException e1) {
/*  324 */       if (Trace.isOn) {
/*  325 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "<init>(JmqiEnvironment,RemoteConnectionSpecification,RemoteFAP,String,int,int)", e1, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  330 */       HashMap<String, Object> ffstInfo = new HashMap<>();
/*  331 */       ffstInfo.put("MQCD", connectionSpec.getMqcd());
/*  332 */       ffstInfo.put("Exception message", e1.getMessage());
/*  333 */       ffstInfo.put("Description", "MQCD: CloneNotSupportedException");
/*  334 */       Trace.ffst(this, "<init>(JmqiEnvironment,RemoteConnectionSpecification,RemoteFAP,String,int,int)", "01", ffstInfo, null);
/*      */       
/*  336 */       JmqiException traceRet3 = new JmqiException(env, -1, null, 2, 2195, e1);
/*      */       
/*  338 */       if (Trace.isOn) {
/*  339 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "<init>(JmqiEnvironment,RemoteConnectionSpecification,RemoteFAP,String,int,int)", (Throwable)traceRet3, 1);
/*      */       }
/*      */       
/*  342 */       throw traceRet3;
/*      */     } 
/*      */     
/*  345 */     MQSCO mqsco = connectionSpec.getMqsco();
/*  346 */     if (mqsco != null) {
/*      */       try {
/*  348 */         this.sslConfig = (MQSCO)mqsco.clone();
/*      */       }
/*  350 */       catch (CloneNotSupportedException e2) {
/*  351 */         if (Trace.isOn) {
/*  352 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "<init>(JmqiEnvironment,RemoteConnectionSpecification,RemoteFAP,String,int,int)", e2, 2);
/*      */         }
/*      */ 
/*      */         
/*  356 */         HashMap<String, Object> ffstInfo = new HashMap<>();
/*  357 */         ffstInfo.put("MQSCO", mqsco);
/*  358 */         ffstInfo.put("Exception message", e2.getMessage());
/*  359 */         ffstInfo.put("Description", "MQSCO: CloneNotSupportedException");
/*  360 */         Trace.ffst(this, "<init>(JmqiEnvironment,RemoteConnectionSpecification,RemoteFAP,String,int,int)", "02", ffstInfo, null);
/*      */         
/*  362 */         JmqiException traceRet4 = new JmqiException(env, -1, null, 2, 2195, e2);
/*      */         
/*  364 */         if (Trace.isOn) {
/*  365 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "<init>(JmqiEnvironment,RemoteConnectionSpecification,RemoteFAP,String,int,int)", (Throwable)traceRet4, 2);
/*      */         }
/*      */ 
/*      */         
/*  369 */         throw traceRet4;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  374 */     this.jmqiFlags = connectionSpec.getJmqiFlags();
/*      */     
/*  376 */     this.sslSocketFactory = connectionSpec.getSslSocketFactory();
/*  377 */     this.sslCertStores = connectionSpec.getSslCertStores();
/*      */     
/*  379 */     if (maxFapLevel > 0 && getFapLevel() > maxFapLevel) {
/*  380 */       this.fapLevel = maxFapLevel;
/*      */     }
/*      */     
/*  383 */     if (Trace.isOn) {
/*  384 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "<init>(JmqiEnvironment,RemoteConnectionSpecification,RemoteFAP,String,int,int)");
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
/*      */   private InetSocketAddress parseConnectionName(String connectionNameP) throws JmqiException {
/*  399 */     if (Trace.isOn) {
/*  400 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "parseConnectionName(String)", new Object[] { connectionNameP });
/*      */     }
/*      */     
/*  403 */     String connectionName = connectionNameP;
/*      */     
/*  405 */     if (connectionName == null || connectionName.length() == 0) {
/*      */       
/*  407 */       JmqiException traceRet1 = new JmqiException(this.env, 9205, new String[] { null, null, null, getTrpType() }, 2, 2538, null);
/*      */ 
/*      */       
/*  410 */       if (Trace.isOn) {
/*  411 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "parseConnectionName(String)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/*  414 */       throw traceRet1;
/*      */     } 
/*      */     
/*  417 */     String hostname = null;
/*  418 */     int port = 0;
/*  419 */     connectionName = connectionName.trim();
/*  420 */     int openingBracket = connectionName.lastIndexOf('(');
/*  421 */     int closingBracket = connectionName.lastIndexOf(')');
/*      */     
/*  423 */     if (openingBracket == -1 && closingBracket == -1) {
/*      */       
/*  425 */       hostname = connectionName;
/*  426 */       port = 1414;
/*      */     }
/*      */     else {
/*      */       
/*  430 */       if (openingBracket == -1 || closingBracket == -1 || closingBracket != connectionName.length() - 1) {
/*      */         
/*  432 */         JmqiException traceRet2 = new JmqiException(this.env, 9205, new String[] { null, null, connectionName, getTrpType() }, 2, 2538, null);
/*      */         
/*  434 */         if (Trace.isOn) {
/*  435 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "parseConnectionName(String)", (Throwable)traceRet2, 2);
/*      */         }
/*      */         
/*  438 */         throw traceRet2;
/*      */       } 
/*      */       
/*  441 */       hostname = connectionName.substring(0, openingBracket);
/*      */       
/*  443 */       if (hostname.length() == 0 || hostname.indexOf('(') != -1 || hostname.indexOf(')') != -1) {
/*      */         
/*  445 */         JmqiException traceRet3 = new JmqiException(this.env, 9205, new String[] { null, null, hostname, getTrpType() }, 2, 2538, null);
/*      */         
/*  447 */         if (Trace.isOn) {
/*  448 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "parseConnectionName(String)", (Throwable)traceRet3, 3);
/*      */         }
/*      */         
/*  451 */         throw traceRet3;
/*      */       } 
/*      */       try {
/*  454 */         port = Integer.parseInt(connectionName.substring(openingBracket + 1, closingBracket));
/*  455 */         if (port < 0)
/*      */         {
/*  457 */           JmqiException traceRet4 = new JmqiException(this.env, 9205, new String[] { null, null, connectionName, getTrpType() }, 2, 2538, null);
/*      */           
/*  459 */           if (Trace.isOn) {
/*  460 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "parseConnectionName(String)", (Throwable)traceRet4, 4);
/*      */           }
/*      */           
/*  463 */           throw traceRet4;
/*      */         }
/*      */       
/*  466 */       } catch (NumberFormatException e) {
/*  467 */         if (Trace.isOn) {
/*  468 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "parseConnectionName(String)", e);
/*      */         }
/*      */ 
/*      */         
/*  472 */         JmqiException traceRet5 = new JmqiException(this.env, 9205, new String[] { null, null, connectionName, getTrpType() }, 2, 2538, null);
/*      */         
/*  474 */         if (Trace.isOn) {
/*  475 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "parseConnectionName(String)", (Throwable)traceRet5, 5);
/*      */         }
/*      */         
/*  478 */         throw traceRet5;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  484 */     InetAddress inetAddr = resolveHostname(hostname);
/*      */ 
/*      */     
/*  487 */     InetSocketAddress sockAddr = new InetSocketAddress(inetAddr, port);
/*  488 */     if (Trace.isOn) {
/*  489 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "parseConnectionName(String)", sockAddr);
/*      */     }
/*      */     
/*  492 */     return sockAddr;
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
/*      */   private InetAddress resolveHostname(final String hostname) throws JmqiException {
/*  504 */     if (Trace.isOn) {
/*  505 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "resolveHostname(final String)", new Object[] { hostname });
/*      */     }
/*      */     
/*  508 */     InetAddress inetAddr = null;
/*      */     
/*  510 */     Configuration configuration = this.env.getConfiguration();
/*  511 */     String mqIpAddrVer = configuration.getStringValue(Configuration.ENV_MQIPADDRV);
/*  512 */     if ("MQIPADDR_IPV6".equalsIgnoreCase(mqIpAddrVer)) {
/*      */       
/*      */       try {
/*  515 */         Object inetAddrTemp = AccessController.doPrivileged(new PrivilegedAction()
/*      */             {
/*      */               public Object run()
/*      */               {
/*  519 */                 if (Trace.isOn) {
/*  520 */                   Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "run()");
/*      */                 }
/*      */                 try {
/*  523 */                   Object traceRet1 = Inet6Address.getByName(hostname);
/*      */                   
/*  525 */                   if (Trace.isOn) {
/*  526 */                     Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.null", "run()", traceRet1, 1);
/*      */                   }
/*  528 */                   return traceRet1;
/*      */                 }
/*  530 */                 catch (UnknownHostException e) {
/*  531 */                   if (Trace.isOn) {
/*  532 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.null", "run()", e);
/*      */                   }
/*  534 */                   if (Trace.isOn) {
/*  535 */                     Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.null", "run()", e, 2);
/*      */                   }
/*  537 */                   return e;
/*      */                 } 
/*      */               }
/*      */             });
/*  541 */         if (inetAddrTemp instanceof UnknownHostException) {
/*  542 */           UnknownHostException traceRet2 = (UnknownHostException)inetAddrTemp;
/*  543 */           if (Trace.isOn) {
/*  544 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "resolveHostname(final String)", traceRet2, 1);
/*      */           }
/*      */           
/*  547 */           throw traceRet2;
/*      */         } 
/*  549 */         inetAddr = (InetAddress)inetAddrTemp;
/*      */       }
/*  551 */       catch (UnknownHostException e) {
/*  552 */         if (Trace.isOn) {
/*  553 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "resolveHostname(final String)", e, 1);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  560 */     else if ("MQIPADDR_IPV4".equalsIgnoreCase(mqIpAddrVer)) {
/*      */       
/*      */       try {
/*  563 */         Object inetAddrTemp = AccessController.doPrivileged(new PrivilegedAction()
/*      */             {
/*      */               public Object run()
/*      */               {
/*  567 */                 if (Trace.isOn) {
/*  568 */                   Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "run()");
/*      */                 }
/*      */                 try {
/*  571 */                   Object traceRet1 = Inet4Address.getByName(hostname);
/*      */                   
/*  573 */                   if (Trace.isOn) {
/*  574 */                     Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.null", "run()", traceRet1, 1);
/*      */                   }
/*  576 */                   return traceRet1;
/*      */                 }
/*  578 */                 catch (UnknownHostException e) {
/*  579 */                   if (Trace.isOn) {
/*  580 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.null", "run()", e);
/*      */                   }
/*  582 */                   if (Trace.isOn) {
/*  583 */                     Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.null", "run()", e, 2);
/*      */                   }
/*  585 */                   return e;
/*      */                 } 
/*      */               }
/*      */             });
/*  589 */         if (inetAddrTemp instanceof UnknownHostException) {
/*  590 */           UnknownHostException traceRet2 = (UnknownHostException)inetAddrTemp;
/*  591 */           if (Trace.isOn) {
/*  592 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "resolveHostname(final String)", traceRet2, 2);
/*      */           }
/*      */           
/*  595 */           throw traceRet2;
/*      */         } 
/*  597 */         inetAddr = (InetAddress)inetAddrTemp;
/*      */       }
/*  599 */       catch (UnknownHostException e) {
/*  600 */         if (Trace.isOn) {
/*  601 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "resolveHostname(final String)", e, 2);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  611 */     if (inetAddr == null) {
/*      */       try {
/*  613 */         Object inetAddrTemp = AccessController.doPrivileged(new PrivilegedAction()
/*      */             {
/*      */               public Object run()
/*      */               {
/*  617 */                 if (Trace.isOn) {
/*  618 */                   Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "run()");
/*      */                 }
/*      */                 try {
/*  621 */                   Object traceRet1 = InetAddress.getByName(hostname);
/*      */                   
/*  623 */                   if (Trace.isOn) {
/*  624 */                     Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.null", "run()", traceRet1, 1);
/*      */                   }
/*  626 */                   return traceRet1;
/*      */                 }
/*  628 */                 catch (UnknownHostException e) {
/*  629 */                   if (Trace.isOn) {
/*  630 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.null", "run()", e);
/*      */                   }
/*  632 */                   if (Trace.isOn) {
/*  633 */                     Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.null", "run()", e, 2);
/*      */                   }
/*  635 */                   return e;
/*      */                 } 
/*      */               }
/*      */             });
/*  639 */         if (inetAddrTemp instanceof UnknownHostException) {
/*  640 */           UnknownHostException traceRet2 = (UnknownHostException)inetAddrTemp;
/*  641 */           if (Trace.isOn) {
/*  642 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "resolveHostname(final String)", traceRet2, 3);
/*      */           }
/*      */           
/*  645 */           throw traceRet2;
/*      */         } 
/*  647 */         inetAddr = (InetAddress)inetAddrTemp;
/*      */       }
/*  649 */       catch (UnknownHostException e) {
/*  650 */         if (Trace.isOn) {
/*  651 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "resolveHostname(final String)", e, 3);
/*      */         }
/*      */ 
/*      */         
/*  655 */         JmqiException traceRet1 = new JmqiException(this.env, 9205, new String[] { null, null, hostname, getTrpType() }, 2, 2538, null);
/*      */         
/*  657 */         if (Trace.isOn) {
/*  658 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "resolveHostname(final String)", (Throwable)traceRet1, 4);
/*      */         }
/*      */         
/*  661 */         throw traceRet1;
/*      */       } 
/*      */     }
/*      */     
/*  665 */     if (Trace.isOn) {
/*  666 */       Trace.data(this, "resolveHostname(final String)", "Remote address", inetAddr.toString());
/*      */     }
/*      */     
/*  669 */     if (Trace.isOn) {
/*  670 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "resolveHostname(final String)", inetAddr);
/*      */     }
/*      */     
/*  673 */     return inetAddr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFirstPortToTry(String laHostname, int laStartPort, int laEndPort) {
/*  682 */     if (Trace.isOn) {
/*  683 */       Trace.entry("com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getFirstPortToTry(String, int, int)", new Object[] { laHostname, 
/*      */             
/*  685 */             Integer.valueOf(laStartPort), Integer.valueOf(laEndPort) });
/*      */     }
/*  687 */     int recommendation = laStartPort;
/*      */     
/*  689 */     if (laStartPort != laEndPort) {
/*  690 */       String key = makePortKey(laHostname, laStartPort, laEndPort);
/*  691 */       Integer iObj = localAddrPorts.get(key);
/*  692 */       if (iObj != null) {
/*  693 */         recommendation = iObj.intValue();
/*  694 */         if (recommendation < laStartPort || recommendation > laEndPort) {
/*  695 */           if (Trace.isOn) {
/*  696 */             Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getFirstPortToTry(String, int, int)", "found recommendation", 
/*      */                 
/*  698 */                 Integer.valueOf(recommendation));
/*      */           }
/*  700 */           recommendation = laStartPort;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  705 */     if (Trace.isOn) {
/*  706 */       Trace.exit("com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getFirstPortToTry(String, int, int)", recommendation);
/*      */     }
/*      */ 
/*      */     
/*  710 */     return recommendation;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void setFirstPortToTry(String laHostname, int laStartPort, int laEndPort, int port) {
/*  718 */     if (Trace.isOn) {
/*  719 */       Trace.entry("com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "setFirstPortToTry(String, int, int, int)", new Object[] { laHostname, 
/*      */             
/*  721 */             Integer.valueOf(laStartPort), Integer.valueOf(laEndPort), Integer.valueOf(port) });
/*      */     }
/*  723 */     String key = makePortKey(laHostname, laStartPort, laEndPort);
/*  724 */     if (Trace.isOn) {
/*  725 */       Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "setFirstPortToTry(String, int, int, int)", "key", key);
/*      */     }
/*      */     
/*  728 */     localAddrPorts.put(key, Integer.valueOf(port));
/*  729 */     if (Trace.isOn) {
/*  730 */       Trace.exit("com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "setFirstPortToTry(String, int, int, int)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static String makePortKey(String laHostname, int laStartPort, int laEndPort) {
/*  736 */     String key = laStartPort + ";" + laEndPort + ";";
/*  737 */     if (laHostname != null) {
/*  738 */       key = key + laHostname;
/*      */     }
/*  740 */     return key;
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
/*      */   @Cruise("This method used to have the signature: connectUsingLocalAddress(InetSocketAddress, String, String) - see defect 234741")
/*      */   private void bindAndConnectSocket(InetSocketAddress remoteSocketAddress, String localAddress, String sslCipherSpec) throws JmqiException {
/*  758 */     if (Trace.isOn) {
/*  759 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "bindAndConnectSocket(InetSocketAddress, String, String)", new Object[] { remoteSocketAddress
/*      */             
/*  761 */             .getHostName(), localAddress, sslCipherSpec });
/*      */     }
/*  763 */     boolean cipherSpecSpecified = (sslCipherSpec != null && sslCipherSpec.length() > 0);
/*      */     
/*  765 */     ConnectionSpecification connectionSpec = new ConnectionSpecification(localAddress);
/*  766 */     String localHostname = connectionSpec.localHostname;
/*  767 */     int portStart = connectionSpec.portStart;
/*  768 */     int portEnd = connectionSpec.portEnd;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  779 */     if (portStart > portEnd) {
/*  780 */       int tempPort = portStart;
/*  781 */       portStart = portEnd;
/*  782 */       portEnd = tempPort;
/*      */     } 
/*      */     
/*  785 */     if (portStart == 0) {
/*  786 */       portStart = portEnd;
/*      */     }
/*      */ 
/*      */     
/*  790 */     InetAddress localIpAddr = null;
/*  791 */     if (localHostname != null && localHostname.trim().length() != 0) {
/*  792 */       localIpAddr = resolveIpAddress(localHostname, remoteSocketAddress);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  797 */     Exception lastException = null;
/*      */     
/*  799 */     int range = portEnd - portStart;
/*  800 */     int port = getFirstPortToTry(localHostname, portStart, portEnd);
/*      */     
/*  802 */     String tcpCall = null;
/*      */     
/*  804 */     if (Trace.isOn) {
/*  805 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "bindAndConnectSocket(InetSocketAddress, String, String)", "portStart", 
/*      */           
/*  807 */           Integer.valueOf(portStart));
/*  808 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "bindAndConnectSocket(InetSocketAddress, String, String)", "portEnd", 
/*      */           
/*  810 */           Integer.valueOf(portStart));
/*  811 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "bindAndConnectSocket(InetSocketAddress, String, String)", "range", 
/*      */           
/*  813 */           Integer.valueOf(range));
/*  814 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "bindAndConnectSocket(InetSocketAddress, String, String)", "port", 
/*      */           
/*  816 */           Integer.valueOf(port));
/*      */     } 
/*      */     
/*  819 */     for (int attempt = 0; attempt <= range; attempt++) {
/*      */ 
/*      */       
/*  822 */       InetSocketAddress localSocketAddress = new InetSocketAddress(localIpAddr, port);
/*  823 */       if (Trace.isOn) {
/*  824 */         Trace.data(this, "bindAndConnectSocket", "Local address", localSocketAddress.toString());
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  830 */         setupSocket();
/*      */ 
/*      */         
/*  833 */         tcpCall = "Socket.bind";
/*  834 */         this.socket.bind(localSocketAddress);
/*      */ 
/*      */         
/*  837 */         tcpCall = "Socket.connect";
/*  838 */         connectSocket(remoteSocketAddress);
/*      */ 
/*      */ 
/*      */         
/*  842 */         if (cipherSpecSpecified)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  849 */           this.socket = makeSocketSecure(this.socket, remoteSocketAddress.getHostName(), remoteSocketAddress.getPort(), sslCipherSpec);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  854 */         this.socketPort = this.socket.getLocalPort();
/*  855 */         this.socketIpAddress = this.socket.getInetAddress().toString();
/*      */ 
/*      */         
/*  858 */         this.sockInStream = this.socket.getInputStream();
/*  859 */         this.sockOutStream = this.socket.getOutputStream();
/*      */ 
/*      */ 
/*      */         
/*  863 */         setInitialSocketTimeout();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         break;
/*  871 */       } catch (IOException e) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  876 */         lastException = e;
/*      */ 
/*      */         
/*  879 */         if (this.socket != null) {
/*      */           try {
/*  881 */             this.socket.close();
/*      */           }
/*  883 */           catch (IOException iOException) {}
/*      */         }
/*      */ 
/*      */         
/*  887 */         this.socket = null;
/*  888 */         this.secureSocket = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  899 */         port++;
/*  900 */         if (port > portEnd) {
/*  901 */           port = portStart;
/*      */         }
/*      */         
/*  904 */         if (Trace.isOn) {
/*  905 */           Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "bindAndConnectSocket(InetSocketAddress, String, String)", "nextPort calculated as", 
/*      */               
/*  907 */               Integer.valueOf(port));
/*      */         }
/*  909 */         setFirstPortToTry(localHostname, portStart, portEnd, port);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  915 */     if (this.socket == null) {
/*      */       
/*  917 */       JmqiException e = null;
/*      */ 
/*      */       
/*  920 */       if (lastException instanceof JmqiException) {
/*  921 */         e = (JmqiException)lastException;
/*      */       }
/*  923 */       else if (lastException instanceof java.net.BindException) {
/*      */ 
/*      */         
/*  926 */         e = new JmqiException(this.env, 9248, new String[] { tcpCall, null, getTrpType(), Integer.toString(port), JmqiTools.getExSumm(lastException) }, 2, 2538, lastException);
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  932 */         e = new JmqiException(this.env, 9204, new String[] { JmqiTools.getExSumm(lastException), null, remoteSocketAddress.toString(), getTrpType(), (tcpCall != null) ? tcpCall : JmqiTools.getFailingCall(lastException) }, 2, 2538, lastException);
/*      */       } 
/*      */ 
/*      */       
/*  936 */       if (Trace.isOn) {
/*  937 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "bindAndConnectSocket(InetSocketAddress, String, String)", (Throwable)e, 10);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  942 */       throw e;
/*      */     } 
/*      */     
/*  945 */     if (Trace.isOn) {
/*  946 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "bindAndConnectSocket(InetSocketAddress, String, String)");
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
/*      */   @Cruise("Method renamed from: setSocketTimeout()")
/*      */   private void setInitialSocketTimeout() throws JmqiException {
/*  962 */     if (Trace.isOn) {
/*  963 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "setInitialSocketTimeout()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  972 */     int desiredInitialSocketTimeoutMillis = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  978 */     if (this.mqRcvBlkTo != null) {
/*  979 */       int mqrcvblkto_int; if (Trace.isOn) {
/*  980 */         Trace.data(this, "setInitialSocketTimeout()", "MQRCVBLKTO defined, value", this.mqRcvBlkTo);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  986 */       int DEFAULT_SOCKET_TIMEOUT_SECONDS = 120;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  992 */       char firstChar = this.mqRcvBlkTo.charAt(0);
/*      */       try {
/*  994 */         if (firstChar == 'x' || firstChar == 'X' || firstChar == '+') {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1000 */           mqrcvblkto_int = 120;
/*      */         } else {
/*      */           
/* 1003 */           mqrcvblkto_int = Integer.parseInt(this.mqRcvBlkTo);
/*      */         }
/*      */       
/* 1006 */       } catch (NumberFormatException e) {
/* 1007 */         if (Trace.isOn) {
/* 1008 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "setInitialSocketTimeout()", e);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1015 */         mqrcvblkto_int = 120;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1020 */       if (this.mqRcvBlkMin > 0 && mqrcvblkto_int < this.mqRcvBlkMin) {
/* 1021 */         mqrcvblkto_int = this.mqRcvBlkMin;
/*      */       }
/*      */       
/* 1024 */       desiredInitialSocketTimeoutMillis = mqrcvblkto_int * 1000;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1031 */     if (this.qmgrNegotiationFlowTimeout > 0) {
/* 1032 */       if (Trace.isOn) {
/* 1033 */         Trace.data(this, "setInitialSocketTimeout()", "QmgrNegotiationFlowTimeout defined, value", 
/*      */ 
/*      */             
/* 1036 */             Integer.valueOf(this.qmgrNegotiationFlowTimeout));
/*      */       }
/*      */ 
/*      */       
/* 1040 */       if (this.qmgrNegotiationFlowTimeout > 2147483) {
/*      */         
/* 1042 */         this.qmgrNegotiationFlowTimeout = 2147483;
/* 1043 */         if (Trace.isOn) {
/* 1044 */           Trace.data(this, "setInitialSocketTimeout()", "QmgrNegotiationFlowTimeout too large.  Reducing to fit into an int.", 
/*      */ 
/*      */               
/* 1047 */               Integer.valueOf(this.qmgrNegotiationFlowTimeout));
/*      */         }
/*      */       } 
/*      */       
/* 1051 */       desiredInitialSocketTimeoutMillis = this.qmgrNegotiationFlowTimeout * 1000;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1056 */     if (desiredInitialSocketTimeoutMillis >= 0) {
/* 1057 */       if (Trace.isOn) {
/* 1058 */         Trace.data(this, "setInitialSocketTimeout()", "Setting initial socket timeout (in milliseconds) to", 
/*      */ 
/*      */             
/* 1061 */             Integer.valueOf(desiredInitialSocketTimeoutMillis));
/*      */       }
/*      */       
/*      */       try {
/* 1065 */         this.socket.setSoTimeout(desiredInitialSocketTimeoutMillis);
/*      */       }
/* 1067 */       catch (SocketException se) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1073 */         JmqiException traceRet9 = new JmqiException(this.env, 9213, new String[] { JmqiTools.getExSumm(se), null, "setSocketTimeout()", getTrpType(), "socket.setSoTimeout" }, 2, 2009, se);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1079 */         if (Trace.isOn) {
/* 1080 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "setInitialSocketTimeout()", (Throwable)traceRet9, 9);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1085 */         throw traceRet9;
/*      */       } 
/*      */     } 
/*      */     
/* 1089 */     if (Trace.isOn) {
/* 1090 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "setInitialSocketTimeout()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void connectSocket(final InetSocketAddress remoteSocketAddress) throws IOException {
/* 1096 */     if (Trace.isOn) {
/* 1097 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "connectSocket(InetSocketAddress)", new Object[] { remoteSocketAddress });
/*      */       
/* 1099 */       Trace.data(this, "connectSocket(InetSocketAddress)", "Connecting socket. Connection timeout (in seconds):", 
/* 1100 */           Integer.valueOf(this.connectTimeout));
/*      */     } 
/*      */     
/* 1103 */     IOException ex = AccessController.<IOException>doPrivileged(new PrivilegedAction<IOException>()
/*      */         {
/*      */ 
/*      */           
/*      */           public IOException run()
/*      */           {
/*      */             try {
/* 1110 */               if (RemoteTCPConnection.this.connectTimeout == -1) {
/* 1111 */                 RemoteTCPConnection.this.socket.connect(remoteSocketAddress);
/*      */               
/*      */               }
/*      */               else {
/*      */ 
/*      */                 
/* 1117 */                 RemoteTCPConnection.this.socket.connect(remoteSocketAddress, RemoteTCPConnection.this.connectTimeout * 1000);
/*      */               }
/*      */             
/* 1120 */             } catch (IOException connEx) {
/* 1121 */               return connEx;
/*      */             } 
/* 1123 */             return null;
/*      */           }
/*      */         });
/* 1126 */     if (ex != null) {
/*      */       
/* 1128 */       if (Trace.isOn) {
/* 1129 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "connectSocket(InetSocketAddress)", ex);
/*      */       }
/*      */ 
/*      */       
/* 1133 */       throw ex;
/*      */     } 
/*      */     
/* 1136 */     if (Trace.isOn) {
/* 1137 */       Trace.data(this, "connectSocket(InetSocketAddress)", "Socket connected", null);
/* 1138 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "connectSocket(InetSocketAddress)");
/*      */     } 
/*      */   }
/*      */   
/*      */   private void setupSocket() {
/* 1143 */     if (Trace.isOn) {
/* 1144 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "setupSocket()");
/*      */     }
/*      */     
/* 1147 */     this.socket = new Socket();
/*      */ 
/*      */     
/*      */     try {
/* 1151 */       this.socket.setTcpNoDelay(true);
/* 1152 */       if (this.sndBuffSize == -1) {
/* 1153 */         this.socket.setSendBufferSize(32768);
/* 1154 */         if (Trace.isOn) {
/* 1155 */           Trace.data(this, "setupSocket()", "Send buffer size set to default value:", Integer.valueOf(32768));
/*      */         }
/*      */       } else {
/*      */         
/* 1159 */         this.socket.setSendBufferSize(this.sndBuffSize);
/* 1160 */         if (Trace.isOn) {
/* 1161 */           Trace.data(this, "setupSocket()", "Send buffer size set to:", Integer.valueOf(this.sndBuffSize));
/*      */         }
/*      */       } 
/*      */       
/* 1165 */       if (this.rcvBuffSize == -1) {
/* 1166 */         this.socket.setReceiveBufferSize(32768);
/* 1167 */         if (Trace.isOn) {
/* 1168 */           Trace.data(this, "setupSocket()", "Receive buffer size set to default value:", Integer.valueOf(32768));
/*      */         }
/*      */       } else {
/*      */         
/* 1172 */         this.socket.setReceiveBufferSize(this.rcvBuffSize);
/* 1173 */         if (Trace.isOn) {
/* 1174 */           Trace.data(this, "setupSocket()", "Receive buffer size set to:", Integer.valueOf(this.rcvBuffSize));
/*      */         }
/*      */       }
/*      */     
/* 1178 */     } catch (IOException e) {
/*      */       
/* 1180 */       if (Trace.isOn) {
/* 1181 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "setupSocket()", e);
/*      */       }
/*      */     } 
/*      */     
/* 1185 */     if (Trace.isOn) {
/* 1186 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "setupSocket()");
/*      */     }
/*      */   }
/*      */   
/*      */   private InetAddress resolveIpAddress(final String localHostname, final InetSocketAddress remoteSocketAddress) throws JmqiException {
/* 1191 */     if (Trace.isOn) {
/* 1192 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "resolveIpAddress(InetSocketAddress, String, String)", new Object[] { localHostname, remoteSocketAddress });
/*      */     }
/*      */ 
/*      */     
/* 1196 */     InetAddress localIpAddr = AccessController.<InetAddress>doPrivileged(new PrivilegedAction<InetAddress>()
/*      */         {
/*      */           
/*      */           public InetAddress run()
/*      */           {
/* 1201 */             InetAddress tempAddr = null;
/*      */ 
/*      */             
/*      */             try {
/* 1205 */               if (remoteSocketAddress.getAddress() instanceof Inet4Address) {
/* 1206 */                 tempAddr = Inet4Address.getByName(localHostname);
/*      */               }
/* 1208 */               else if (remoteSocketAddress.getAddress() instanceof Inet6Address) {
/* 1209 */                 tempAddr = Inet6Address.getByName(localHostname);
/*      */               } else {
/*      */                 
/* 1212 */                 tempAddr = InetAddress.getByName(localHostname);
/*      */               }
/*      */             
/* 1215 */             } catch (UnknownHostException e) {
/* 1216 */               tempAddr = null;
/*      */             } 
/* 1218 */             return tempAddr;
/*      */           }
/*      */         });
/*      */     
/* 1222 */     if (localIpAddr == null) {
/*      */       
/* 1224 */       JmqiException traceRet = new JmqiException(this.env, 9913, new String[] { null, null, localHostname }, 2, 2538, null);
/*      */ 
/*      */       
/* 1227 */       if (Trace.isOn) {
/* 1228 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "resolveIpAddress(InetSocketAddress, String, String)", (Throwable)traceRet);
/*      */       }
/*      */ 
/*      */       
/* 1232 */       throw traceRet;
/*      */     } 
/*      */     
/* 1235 */     if (Trace.isOn) {
/* 1236 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "resolveIpAddress(InetSocketAddress, String, String)", localIpAddr);
/*      */     }
/*      */ 
/*      */     
/* 1240 */     return localIpAddr;
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
/*      */   protected void protocolSetHeartbeatInterval(int hbInt) throws JmqiException {
/* 1253 */     if (Trace.isOn) {
/* 1254 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSetHeartbeatInterval(int)", new Object[] {
/* 1255 */             Integer.valueOf(hbInt)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1264 */     if (isMultiplexingEnabled()) {
/* 1265 */       if (hbInt > 0) {
/* 1266 */         this.firstReadTimeout = hbInt * 1000;
/*      */       } else {
/*      */         
/* 1269 */         this.firstReadTimeout = 0;
/*      */       }
/*      */     
/*      */     }
/* 1273 */     else if (hbInt <= 2) {
/*      */ 
/*      */ 
/*      */       
/* 1277 */       this.firstReadTimeout = 5000;
/*      */     }
/* 1279 */     else if (hbInt < 60) {
/*      */ 
/*      */       
/* 1282 */       this.firstReadTimeout = hbInt * 2 * 1000;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1287 */       this.firstReadTimeout = (hbInt + 60) * 1000;
/*      */     } 
/*      */ 
/*      */     
/* 1291 */     if (this.mqRcvBlkTo != null) {
/* 1292 */       if (Trace.isOn) {
/* 1293 */         Trace.data(this, "protocolSetHeartbeatInterval(int)", "MQRCVBLKTO defined, set to", this.mqRcvBlkTo);
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/* 1298 */         char firstChar = this.mqRcvBlkTo.charAt(0);
/* 1299 */         if (firstChar == 'x' || firstChar == 'X') {
/* 1300 */           int rcvBlkMult = Integer.parseInt(this.mqRcvBlkTo.substring(1));
/* 1301 */           this.firstReadTimeout = hbInt * rcvBlkMult * 1000;
/*      */         }
/* 1303 */         else if (firstChar == '+') {
/* 1304 */           int rcvBlkAdd = Integer.parseInt(this.mqRcvBlkTo.substring(1));
/* 1305 */           this.firstReadTimeout = (hbInt + rcvBlkAdd) * 1000;
/*      */         } else {
/*      */           
/* 1308 */           int rcvBlkSpecific = Integer.parseInt(this.mqRcvBlkTo);
/* 1309 */           this.firstReadTimeout = rcvBlkSpecific * 1000;
/*      */         }
/*      */       
/* 1312 */       } catch (NumberFormatException e) {
/* 1313 */         if (Trace.isOn) {
/* 1314 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSetHeartbeatInterval(int)", e, 1);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1322 */     if (this.mqRcvBlkMin > 0 && this.firstReadTimeout < this.mqRcvBlkMin) {
/* 1323 */       if (Trace.isOn) {
/* 1324 */         Trace.data(this, "protocolSetHeartbeatInterval(int)", "MQRCVBLKMIN less than MQRCBLKTO, reducing timeout to (ms)", 
/*      */             
/* 1326 */             Integer.valueOf(this.mqRcvBlkMin));
/*      */       }
/* 1328 */       this.firstReadTimeout = this.mqRcvBlkMin;
/*      */     } 
/*      */ 
/*      */     
/* 1332 */     if (this.socket == null) {
/* 1333 */       HashMap<String, Object> info = new HashMap<>();
/* 1334 */       info.put("Description", "No socket");
/* 1335 */       Trace.ffst(this, "protocolSetHeartbeatInterval(int)", "01", info, null);
/* 1336 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*      */       
/* 1338 */       if (Trace.isOn) {
/* 1339 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSetHeartbeatInterval(int)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1342 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 1347 */       this.socket.setSoTimeout(this.firstReadTimeout);
/*      */     }
/* 1349 */     catch (IOException e) {
/* 1350 */       if (Trace.isOn) {
/* 1351 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSetHeartbeatInterval(int)", e, 2);
/*      */       }
/*      */ 
/*      */       
/* 1355 */       JmqiException traceRet2 = new JmqiException(this.env, 9213, new String[] { JmqiTools.getExSumm(e), null, null, getTrpType(), "Socket.setSoTimeout" }, 2, 2009, e);
/*      */       
/* 1357 */       if (Trace.isOn) {
/* 1358 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSetHeartbeatInterval(int)", (Throwable)traceRet2, 2);
/*      */       }
/*      */       
/* 1361 */       throw traceRet2;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1368 */     if (this.tcpKeepAlive) {
/*      */       try {
/* 1370 */         this.socket.setKeepAlive(true);
/*      */       }
/* 1372 */       catch (SocketException e) {
/* 1373 */         if (Trace.isOn) {
/* 1374 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSetHeartbeatInterval(int)", e, 3);
/*      */         }
/*      */ 
/*      */         
/* 1378 */         JmqiException traceRet2 = new JmqiException(this.env, 9213, new String[] { JmqiTools.getExSumm(e), null, null, getTrpType(), "Socket.setKeepAlive" }, 2, 2009, e);
/*      */         
/* 1380 */         if (Trace.isOn) {
/* 1381 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSetHeartbeatInterval(int)", (Throwable)traceRet2, 3);
/*      */         }
/*      */         
/* 1384 */         throw traceRet2;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1389 */     if (this.tcpLinger) {
/*      */       try {
/* 1391 */         this.socket.setSoLinger(true, 10);
/*      */       
/*      */       }
/* 1394 */       catch (SocketException e) {
/* 1395 */         if (Trace.isOn) {
/* 1396 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSetHeartbeatInterval(int)", e, 4);
/*      */         }
/*      */         
/* 1399 */         if (Trace.isOn) {
/* 1400 */           Trace.data(this, "protocolSetHeartbeatInterval(int)", "setsockopt(SO_LINGER) not supported.");
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/* 1405 */     if (Trace.isOn) {
/* 1406 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSetHeartbeatInterval(int)");
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
/*      */   void protocolConnect() throws JmqiException {
/* 1419 */     if (Trace.isOn) {
/* 1420 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolConnect()");
/*      */     }
/* 1422 */     if (this.negotiatedChannel.getTransportType() != 2) {
/*      */       
/* 1424 */       JmqiException traceRet1 = new JmqiException(this.env, 9915, new String[] { null, null, Integer.toString(this.negotiatedChannel.getTransportType()) }, 2, 2537, null);
/*      */ 
/*      */       
/* 1427 */       if (Trace.isOn) {
/* 1428 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolConnect()", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1431 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1435 */     InetSocketAddress remoteHost = parseConnectionName(this.negotiatedChannel.getConnectionName());
/* 1436 */     this.socketIpAddressPort = remoteHost.getPort();
/*      */ 
/*      */     
/* 1439 */     if (this.sslConfig != null) {
/*      */       
/* 1441 */       this.fipsRequired = (this.sslConfig.getFipsRequired() == 1);
/*      */ 
/*      */ 
/*      */       
/* 1445 */       if (this.crlHelper == null) {
/* 1446 */         this.crlHelper = new RemoteSSLCRLHelper(this.env, this);
/*      */       }
/* 1448 */       if (this.sslCertStores == null && 
/* 1449 */         this.sslConfig.getAuthInfoRecCount() != 0) {
/* 1450 */         this.sslCertStores = this.crlHelper.processAuthInfoRecords(this.sslConfig);
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1467 */     initialiseInFips(this.fipsRequired);
/* 1468 */     if (getInFips() != this.fipsRequired) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1473 */       JmqiException traceRet = new JmqiException(this.env, -1, null, 2, 2393, null);
/*      */       
/* 1475 */       if (Trace.isOn) {
/* 1476 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolConnect()", (Throwable)traceRet, 2);
/*      */       }
/*      */       
/* 1479 */       throw traceRet;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1484 */     String sslCipherSpec = this.negotiatedChannel.getSslCipherSpec();
/*      */     
/* 1486 */     if (sslCipherSpec != null) {
/* 1487 */       sslCipherSpec = sslCipherSpec.trim();
/*      */     }
/*      */     
/* 1490 */     if (this.fipsRequired && sslCipherSpec == null) {
/*      */       
/* 1492 */       JmqiException traceRet1 = new JmqiException(this.env, 9635, new String[] { null, null, getChannelName() }, 2, 2400, null);
/*      */ 
/*      */       
/* 1495 */       if (Trace.isOn) {
/* 1496 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolConnect()", (Throwable)traceRet1, 3);
/*      */       }
/*      */       
/* 1499 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1503 */     String localAddress = this.negotiatedChannel.getLocalAddress();
/*      */     
/* 1505 */     if (localAddress != null) {
/* 1506 */       localAddress = localAddress.trim();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1511 */     bindAndConnectSocket(remoteHost, localAddress, sslCipherSpec);
/*      */ 
/*      */     
/* 1514 */     if (localAddress == null && this.socket != null) {
/*      */       
/* 1516 */       String localAddressFromSocket = this.socket.getLocalAddress().getHostAddress();
/* 1517 */       int localPortFromSocket = this.socket.getLocalPort();
/* 1518 */       if (localPortFromSocket != -1)
/*      */       {
/* 1520 */         localAddressFromSocket = localAddressFromSocket + "(" + localPortFromSocket + ")";
/*      */       }
/*      */       
/* 1523 */       this.negotiatedChannel.setLocalAddress(localAddressFromSocket);
/*      */       
/* 1525 */       if (Trace.isOn) {
/* 1526 */         if (this.socket.getLocalSocketAddress() == null) {
/* 1527 */           Trace.data(this, "protocolConnect()", "socket.getLocalSocketAddress() :", "null");
/*      */         } else {
/*      */           
/* 1530 */           Trace.data(this, "protocolConnect()", "socket.getLocalSocketAddress().toString() :", this.socket.getLocalSocketAddress().toString());
/*      */         } 
/* 1532 */         Trace.data(this, "protocolConnect()", "Updated localAddress on negotiatedChannel :", this.negotiatedChannel.getLocalAddress());
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1537 */     if (this.secureSocket != null) {
/*      */ 
/*      */       
/* 1540 */       this.secureSocket.addHandshakeCompletedListener(this);
/*      */ 
/*      */       
/*      */       try {
/* 1544 */         IOException e = AccessController.<IOException>doPrivileged(new PrivilegedAction<IOException>()
/*      */             {
/*      */               public IOException run()
/*      */               {
/* 1548 */                 if (Trace.isOn) {
/* 1549 */                   Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "run()");
/*      */                 }
/*      */                 try {
/* 1552 */                   RemoteTCPConnection.this.secureSocket.startHandshake();
/*      */                 }
/* 1554 */                 catch (IOException e1) {
/* 1555 */                   if (Trace.isOn) {
/* 1556 */                     Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.null", "run()", e1);
/*      */                   }
/*      */                   
/* 1559 */                   if (Trace.isOn) {
/* 1560 */                     Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.null", "run()", e1, 1);
/*      */                   }
/* 1562 */                   return e1;
/*      */                 } 
/* 1564 */                 if (Trace.isOn) {
/* 1565 */                   Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.null", "run()", null, 2);
/*      */                 }
/* 1567 */                 return null;
/*      */               }
/*      */             });
/* 1570 */         if (e != null) {
/* 1571 */           if (Trace.isOn) {
/* 1572 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolConnect()", e, 4);
/*      */           }
/*      */           
/* 1575 */           throw e;
/*      */         }
/*      */       
/* 1578 */       } catch (IOException e) {
/* 1579 */         if (Trace.isOn) {
/* 1580 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolConnect()", e, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1585 */         JmqiException traceRet2 = new JmqiException(this.env, 9771, new String[] { JmqiTools.getExSumm(e), null, getRemoteHostDescr(), "SSLSocket.startHandshake", this.customSslSocketFactoryUsed ? this.sslSocketFactory.toString() : "default" }, 2, 2397, e);
/*      */         
/* 1587 */         if (Trace.isOn) {
/* 1588 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolConnect()", (Throwable)traceRet2, 5);
/*      */         }
/*      */         
/* 1591 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */       
/* 1595 */       synchronized (this.handshakeCompleteLock) {
/* 1596 */         while (!this.handshakeComplete) {
/*      */           try {
/* 1598 */             this.handshakeCompleteLock.wait();
/*      */           }
/* 1600 */           catch (InterruptedException e) {
/* 1601 */             if (Trace.isOn) {
/* 1602 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolConnect()", e, 2);
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/* 1607 */         this.handshakeComplete = false;
/*      */       } 
/*      */       
/* 1610 */       String peerDNString = this.peerCertificate.getSubjectX500Principal().getName();
/*      */       
/* 1612 */       BigInteger certSerialNumberAsBigInt = this.peerCertificate.getSerialNumber();
/*      */       
/* 1614 */       if (Trace.isOn) {
/* 1615 */         Trace.data(this, "protocolConnect()", "Received certificate serial number:", certSerialNumberAsBigInt);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1620 */       byte[] serialNumberAsByteArray = certSerialNumberAsBigInt.toByteArray();
/*      */       
/* 1622 */       StringBuilder certSerialNumberHex = new StringBuilder("SERIALNUMBER=");
/* 1623 */       for (int i = 0; i < serialNumberAsByteArray.length; i++) {
/* 1624 */         certSerialNumberHex.append(String.format("%02X:", new Object[] { Byte.valueOf(serialNumberAsByteArray[i]) }));
/*      */       } 
/*      */       
/* 1627 */       certSerialNumberHex.deleteCharAt(certSerialNumberHex.length() - 1);
/*      */ 
/*      */       
/* 1630 */       String peerDNStringWithSerialNumber = certSerialNumberHex + "," + peerDNString;
/*      */ 
/*      */       
/* 1633 */       String sslPeerMatchString = this.negotiatedChannel.getSslPeerName();
/*      */       
/* 1635 */       if (sslPeerMatchString != null && sslPeerMatchString.length() > 0) {
/*      */ 
/*      */         
/* 1638 */         RemotePeerName peerMatcher = new RemotePeerName(this.env, getChannelName(), sslPeerMatchString, true);
/*      */ 
/*      */         
/* 1641 */         RemotePeerName peerDN = new RemotePeerName(this.env, getChannelName(), peerDNStringWithSerialNumber, false);
/*      */ 
/*      */         
/* 1644 */         if (!peerMatcher.isMatchingPeerName(peerDN)) {
/*      */ 
/*      */           
/* 1647 */           JmqiException traceRet4 = new JmqiException(this.env, 9636, new String[] { null, null, getChannelName(), peerDNString, sslPeerMatchString }, 2, 2398, null);
/*      */           
/* 1649 */           if (Trace.isOn) {
/* 1650 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolConnect()", (Throwable)traceRet4, 6);
/*      */           }
/*      */           
/* 1653 */           throw traceRet4;
/*      */         } 
/*      */       } 
/*      */       
/* 1657 */       this.negotiatedChannel.setSslPeerName(peerDNString);
/*      */ 
/*      */       
/* 1660 */       if (this.sslCertStores != null) {
/* 1661 */         if (this.crlHelper == null) {
/* 1662 */           this.crlHelper = new RemoteSSLCRLHelper(this.env, this);
/*      */         }
/* 1664 */         this.crlHelper.checkCRL(this.peerCertificate, this.sslCertStores);
/*      */       } 
/*      */     } 
/*      */     
/* 1668 */     if (Trace.isOn) {
/* 1669 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolConnect()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean getInFips() {
/* 1675 */     return inFipsMode.booleanValue();
/*      */   }
/*      */   
/*      */   private static void initialiseInFips(boolean fipsRequired) {
/* 1679 */     if (inFipsMode == null) {
/* 1680 */       inFipsMode = Boolean.valueOf(fipsRequired);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void protocolDisconnect() {
/* 1689 */     if (Trace.isOn) {
/* 1690 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolDisconnect()");
/*      */     }
/*      */     
/*      */     try {
/* 1694 */       if (this.secureSocket != null) {
/* 1695 */         if (Trace.isOn) {
/* 1696 */           Trace.data(this, "protocolDisconnect()", "closing secure socket", this.secureSocket);
/*      */         }
/* 1698 */         this.secureSocket.close();
/*      */       }
/* 1700 */       else if (this.socket != null) {
/* 1701 */         if (Trace.isOn) {
/* 1702 */           Trace.data(this, "protocolDisconnect()", "closing socket", this.socket);
/* 1703 */           Trace.data(this, "protocolDisconnect()", "socket input stream is ", this.socket.getInputStream());
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1710 */         this.socket.shutdownInput();
/* 1711 */         this.socket.close();
/*      */       }
/*      */     
/* 1714 */     } catch (IOException e) {
/* 1715 */       if (Trace.isOn) {
/* 1716 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolDisconnect()", e);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1721 */     if (Trace.isOn) {
/* 1722 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolDisconnect()");
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
/*      */   void protocolTerminate() {
/* 1734 */     if (Trace.isOn) {
/* 1735 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolTerminate()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1740 */       if (this.secureSocket != null) {
/* 1741 */         this.secureSocket.setSoLinger(true, 0);
/*      */       }
/* 1743 */       else if (this.socket != null) {
/* 1744 */         this.socket.setSoLinger(true, 0);
/*      */       }
/*      */     
/* 1747 */     } catch (IOException e) {
/* 1748 */       if (Trace.isOn) {
/* 1749 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolTerminate()", e);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1754 */     protocolDisconnect();
/*      */     
/* 1756 */     if (Trace.isOn) {
/* 1757 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolTerminate()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean protocolSupportsAsyncMode() throws JmqiException {
/* 1767 */     if (Trace.isOn) {
/* 1768 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSupportsAsyncMode()");
/*      */     }
/* 1770 */     boolean multiplex = true;
/*      */     
/* 1772 */     if (Trace.isOn) {
/* 1773 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSupportsAsyncMode()", 
/* 1774 */           Boolean.valueOf(multiplex));
/*      */     }
/* 1776 */     return multiplex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void protocolSetupAsyncMode() throws JmqiException {
/* 1786 */     if (Trace.isOn) {
/* 1787 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSetupAsyncMode()");
/*      */     }
/*      */ 
/*      */     
/* 1791 */     if (Trace.isOn) {
/* 1792 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSetupAsyncMode()");
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
/*      */   public void send(byte[] buffer, int offset, int size, int segmentType, int tshType, int passwordOffset) throws JmqiException {
/* 1805 */     byte[] traceBuffer = null;
/* 1806 */     if (Trace.isOn) {
/* 1807 */       traceBuffer = sanitizeBuffer(buffer, size, passwordOffset);
/* 1808 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "send(byte [ ],int,int,int,int,int)", new Object[] { traceBuffer, 
/* 1809 */             Integer.valueOf(offset), Integer.valueOf(size), 
/* 1810 */             Integer.valueOf(segmentType), Integer.valueOf(tshType), Integer.valueOf(passwordOffset) });
/* 1811 */       ByteBuffer wrapper = ByteBuffer.wrap(traceBuffer);
/* 1812 */       wrapper.position(offset);
/* 1813 */       wrapper.limit(offset + size);
/* 1814 */       if (Trace.isOn) {
/* 1815 */         Trace.data(this, "send(byte [ ],int,int,int,int)", "Sending data", wrapper);
/*      */       }
/*      */     } 
/*      */     
/* 1819 */     if (this.asyncFailure != null) {
/* 1820 */       int reason = (((JmqiException)this.asyncFailure).getReason() == 2009) ? 2009 : 2202;
/*      */ 
/*      */ 
/*      */       
/* 1824 */       JmqiException traceRet2 = new JmqiException(this.env, 9206, new String[] { JmqiTools.getExSumm(this.asyncFailure), null, getRemoteHostDescr(), getTrpType(), "RemoteTCPConnection.send(byte [ ],int,int,int,int)" }, 2, reason, this.asyncFailure);
/*      */ 
/*      */       
/* 1827 */       if (Trace.isOn) {
/* 1828 */         Trace.throwing("com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "send(byte [ ],int,int,int,int)", (Throwable)traceRet2, 1);
/*      */       }
/*      */       
/* 1831 */       throw traceRet2;
/*      */     } 
/*      */ 
/*      */     
/* 1835 */     OutputStream sockOutStream2 = this.sockOutStream;
/*      */     
/* 1837 */     int totalBytesWritten = 0;
/* 1838 */     synchronized (this.sendLock) {
/* 1839 */       while (totalBytesWritten < size) {
/*      */         
/* 1841 */         int bytesWritten = 0;
/* 1842 */         int bytesToWrite = size - totalBytesWritten;
/* 1843 */         if (Trace.isOn) {
/* 1844 */           Trace.data(this, "send(byte [ ],int,int,int,int)", "Sending at least part - totalBytesWritten", 
/* 1845 */               Integer.valueOf(totalBytesWritten));
/* 1846 */           Trace.data(this, "send(byte [ ],int,int,int,int)", "                      - size             ", 
/* 1847 */               Integer.valueOf(size));
/* 1848 */           Trace.data(this, "send(byte [ ],int,int,int,int)", "                      - buffer           ", traceBuffer);
/*      */           
/* 1850 */           Trace.data(this, "send(byte [ ],int,int,int,int)", "                      - offset           ", 
/* 1851 */               Integer.valueOf(offset));
/*      */         } 
/* 1853 */         this.lastDataSend = System.currentTimeMillis();
/*      */         try {
/* 1855 */           sockOutStream2.write(buffer, offset + bytesWritten, bytesToWrite);
/*      */ 
/*      */           
/* 1858 */           bytesWritten = size;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/* 1868 */         catch (SocketException e) {
/*      */           
/* 1870 */           if (Trace.isOn) {
/* 1871 */             Trace.data("send(byte [ ],int,int,int,int)", "Processing a SocketException", null);
/*      */           }
/* 1873 */           boolean ignoreSocketException = false;
/* 1874 */           if (this.isSecure) {
/* 1875 */             if (Trace.isOn) {
/* 1876 */               Trace.data("send(byte [ ],int,int,int,int)", "Secure connection, checking whether we should ignore SocketException", null);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 1881 */             RfpTSH localTSH = allocateTSH(tshType, segmentType, null);
/* 1882 */             localTSH.setRfpBuffer(buffer);
/* 1883 */             localTSH.setRfpOffset(0);
/*      */             
/* 1885 */             if (localTSH.getSegmentType() == 12) {
/* 1886 */               if (Trace.isOn) {
/* 1887 */                 Trace.data("send(byte [ ],int,int,int,int)", "TSH is for a SOCKET ACTION, checking type", null);
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1893 */               RfpSOCKACT sockAct = new RfpSOCKACT(this.env, localTSH.getRfpBuffer(), localTSH.getRfpOffset() + localTSH.hdrSize());
/*      */ 
/*      */ 
/*      */               
/* 1897 */               if (sockAct.getType(true) == 2 || sockAct.getType(false) == 2) {
/* 1898 */                 if (Trace.isOn) {
/* 1899 */                   Trace.data("send(byte [ ],int,int,int,int)", "SOCKET ACTION type appears to be END_CONV", null);
/*      */                 }
/*      */                 
/* 1902 */                 ignoreSocketException = true;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           
/* 1907 */           if (ignoreSocketException) {
/* 1908 */             if (Trace.isOn) {
/* 1909 */               Trace.data("send(byte [ ],int,int,int,int)", "Ignoring the SocketException", null);
/*      */             }
/* 1911 */             bytesWritten = size;
/*      */           
/*      */           }
/*      */           else {
/*      */             
/* 1916 */             JmqiException traceRet2 = new JmqiException(this.env, 9206, new String[] { JmqiTools.getExSumm(e), null, getRemoteHostDescr(), getTrpType(), "OutputStream.write" }, 2, 2009, e);
/*      */             
/* 1918 */             throw traceRet2;
/*      */           }
/*      */         
/*      */         }
/* 1922 */         catch (IOException e) {
/*      */ 
/*      */ 
/*      */           
/* 1926 */           JmqiException traceRet2 = new JmqiException(this.env, 9206, new String[] { JmqiTools.getExSumm(e), null, getRemoteHostDescr(), getTrpType(), "OutputStream.write" }, 2, 2009, e);
/*      */           
/* 1928 */           throw traceRet2;
/*      */         } 
/*      */ 
/*      */         
/* 1932 */         if (bytesWritten < 0) {
/*      */ 
/*      */           
/* 1935 */           JmqiException traceRet6 = new JmqiException(this.env, 9206, new String[] { Integer.toString(bytesWritten), null, getRemoteHostDescr(), getTrpType(), "OutputStream.write" }, 2, 2009, null);
/*      */           
/* 1937 */           throw traceRet6;
/*      */         } 
/*      */ 
/*      */         
/* 1941 */         totalBytesWritten += bytesWritten;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1946 */     if (totalBytesWritten > size) {
/* 1947 */       JmqiException traceRet7 = new JmqiException(this.env, -1, null, 2, 2009, null);
/*      */       
/* 1949 */       HashMap<String, Object> info = new HashMap<>();
/* 1950 */       info.put("size", Integer.valueOf(size));
/* 1951 */       info.put("totalBytesWritten", Integer.valueOf(totalBytesWritten));
/* 1952 */       info.put("Description", "Expected to send 'size' bytes, but sent 'totalBytesWritten' bytes");
/* 1953 */       Trace.ffst(this, "send(byte [ ],int,int,int,int)", "01", info, null);
/* 1954 */       throw traceRet7;
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
/*      */   public int receive(byte[] buffer, int offset, int length) throws JmqiException {
/*      */     boolean infiniteTimeout;
/* 1981 */     if (Trace.isOn) {
/* 1982 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "receive(byte [ ],int,int)", new Object[] { buffer, 
/* 1983 */             Integer.valueOf(offset), Integer.valueOf(length) });
/*      */     }
/*      */     
/* 1986 */     if (this.asyncFailure != null) {
/* 1987 */       int reason = (((JmqiException)this.asyncFailure).getReason() == 2009) ? 2009 : 2202;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1992 */       JmqiException traceRet2 = new JmqiException(this.env, 9206, new String[] { JmqiTools.getExSumm(this.asyncFailure), null, getRemoteHostDescr(), getTrpType(), "RemoteTCPConnection.receive(byte [ ],int,int)" }, 2, reason, this.asyncFailure);
/*      */       
/* 1994 */       if (Trace.isOn) {
/* 1995 */         Trace.throwing("com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "receive(byte [ ],int,int)", (Throwable)traceRet2, 1);
/*      */       }
/*      */       
/* 1998 */       throw traceRet2;
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
/*      */     try {
/* 2010 */       infiniteTimeout = (this.socket.getSoTimeout() == 0);
/* 2011 */       if (infiniteTimeout) {
/* 2012 */         this.socket.setSoTimeout(this.infiniteWaitChunkTime);
/*      */       }
/*      */     }
/* 2015 */     catch (SocketException e) {
/* 2016 */       if (Trace.isOn) {
/* 2017 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "receive(byte [ ],int,int)", e, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2022 */       JmqiException traceRet5 = new JmqiException(this.env, 9213, new String[] { JmqiTools.getExSumm(e), null, null, getTrpType(), "sockInStream.read" }, 2, 2009, e);
/*      */       
/* 2024 */       if (Trace.isOn) {
/* 2025 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "receive(byte [ ],int,int)", (Throwable)traceRet5, 1);
/*      */       }
/*      */       
/* 2028 */       throw traceRet5;
/*      */     } 
/*      */     
/* 2031 */     int bytesRead = -1;
/*      */ 
/*      */     
/* 2034 */     boolean heartbeatSent = false;
/*      */     while (true) {
/* 2036 */       if (Trace.isOn) {
/* 2037 */         Trace.data(this, "receive(byte [ ],int,int)", "Attempting to read from ", this.sockInStream);
/*      */       }
/*      */       try {
/* 2040 */         bytesRead = this.sockInStream.read(buffer, offset, length);
/* 2041 */         this.lastDataRecv = System.currentTimeMillis();
/*      */         
/*      */         break;
/* 2044 */       } catch (SocketException e) {
/* 2045 */         if (Trace.isOn) {
/* 2046 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "receive(byte [ ],int,int)", e, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2051 */         JmqiException traceRet4 = new JmqiException(this.env, 9213, new String[] { JmqiTools.getExSumm(e), null, null, getTrpType(), "sockInStream.read" }, 2, 2009, e);
/*      */         
/* 2053 */         if (Trace.isOn) {
/* 2054 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "receive(byte [ ],int,int)", (Throwable)traceRet4, 2);
/*      */         }
/*      */         
/* 2057 */         throw traceRet4;
/*      */       
/*      */       }
/* 2060 */       catch (SocketTimeoutException stme) {
/* 2061 */         if (Trace.isOn) {
/* 2062 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "receive(byte [ ],int,int)", stme, 3);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2068 */         if (infiniteTimeout) {
/* 2069 */           if (Trace.isOn) {
/* 2070 */             Trace.data(this, "receive(byte [ ],int,int)", "Caught expected SocketTimeoutException at catch index 2, receive chunk time has elapsed, continuing.", stme);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           continue;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2082 */         if (heartbeatSent || !isMultiplexingEnabled()) {
/* 2083 */           bytesRead = -1;
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/*      */ 
/*      */         
/* 2090 */         sendHeartbeat(this.fap.getTls(), 1);
/*      */         
/* 2092 */         heartbeatSent = true;
/*      */         
/* 2094 */         int timeout = Math.min(60000, this.firstReadTimeout);
/*      */         
/*      */         try {
/* 2097 */           this.socket.setSoTimeout(timeout);
/*      */         }
/* 2099 */         catch (SocketException e) {
/* 2100 */           if (Trace.isOn) {
/* 2101 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "receive(byte [ ],int,int)", e, 4);
/*      */           }
/*      */ 
/*      */           
/* 2105 */           JmqiException traceRet1 = new JmqiException(this.env, 9213, new String[] { JmqiTools.getExSumm(e), null, null, getTrpType(), "Socket.setSoTimeout" }, 2, 2009, e);
/*      */           
/* 2107 */           if (Trace.isOn) {
/* 2108 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "receive(byte [ ],int,int)", (Throwable)traceRet1, 3);
/*      */           }
/*      */           
/* 2111 */           throw traceRet1;
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 2116 */       catch (IOException e) {
/* 2117 */         if (Trace.isOn) {
/* 2118 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "receive(byte [ ],int,int)", e, 5);
/*      */         }
/*      */ 
/*      */         
/* 2122 */         JmqiException traceRet2 = new JmqiException(this.env, 9208, new String[] { JmqiTools.getExSumm(e), null, getRemoteHostDescr(), getTrpType(), "InputStream.read" }, 2, 2009, e);
/*      */         
/* 2124 */         if (Trace.isOn) {
/* 2125 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "receive(byte [ ],int,int)", (Throwable)traceRet2, 4);
/*      */         }
/*      */         
/* 2128 */         throw traceRet2;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2133 */     if (heartbeatSent || infiniteTimeout) {
/*      */       try {
/* 2135 */         this.socket.setSoTimeout(this.firstReadTimeout);
/*      */       }
/* 2137 */       catch (SocketException e) {
/* 2138 */         if (Trace.isOn) {
/* 2139 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "receive(byte [ ],int,int)", e, 6);
/*      */         }
/*      */ 
/*      */         
/* 2143 */         JmqiException traceRet3 = new JmqiException(this.env, 9213, new String[] { JmqiTools.getExSumm(e), null, null, getTrpType(), "Socket.setSoTimeout" }, 2, 2009, e);
/*      */         
/* 2145 */         if (Trace.isOn) {
/* 2146 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "receive(byte [ ],int,int)", (Throwable)traceRet3, 5);
/*      */         }
/*      */         
/* 2149 */         throw traceRet3;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2154 */     if (Trace.isOn && bytesRead > 0) {
/* 2155 */       ByteBuffer wrapper = ByteBuffer.wrap(buffer);
/* 2156 */       wrapper.position(offset);
/* 2157 */       wrapper.limit(offset + bytesRead);
/* 2158 */       Trace.data(this, "receive(byte [ ],int,int)", "Received data", wrapper);
/*      */     } 
/*      */     
/* 2161 */     if (Trace.isOn) {
/* 2162 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "receive(byte [ ],int,int)", 
/* 2163 */           Integer.valueOf(bytesRead));
/*      */     }
/* 2165 */     return bytesRead;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void protocolSecureKeyReset() throws JmqiException {
/* 2173 */     if (Trace.isOn) {
/* 2174 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSecureKeyReset()");
/*      */     }
/*      */     
/* 2177 */     SSLSocket sslSocket = this.secureSocket;
/*      */     
/* 2179 */     SSLSession sslSession = sslSocket.getSession();
/*      */     
/* 2181 */     sslSession.invalidate();
/*      */     try {
/* 2183 */       sslSocket.startHandshake();
/*      */     }
/* 2185 */     catch (IOException e) {
/* 2186 */       if (Trace.isOn) {
/* 2187 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSecureKeyReset()", e, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2193 */       JmqiException traceRet2 = new JmqiException(this.env, 9771, new String[] { JmqiTools.getExSumm(e), null, getRemoteHostDescr(), "SSLSocket.startHandshake", this.customSslSocketFactoryUsed ? this.sslSocketFactory.toString() : "default" }, 2, 2397, e);
/*      */       
/* 2195 */       if (Trace.isOn) {
/* 2196 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSecureKeyReset()", (Throwable)traceRet2, 1);
/*      */       }
/*      */       
/* 2199 */       throw traceRet2;
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
/* 2212 */     int savedTimeout = 0;
/* 2213 */     boolean setTimeout = false;
/*      */     try {
/* 2215 */       savedTimeout = this.secureSocket.getSoTimeout();
/* 2216 */       this.secureSocket.setSoTimeout(this.sslResetPollTimeout);
/*      */       
/* 2218 */       setTimeout = true;
/*      */ 
/*      */       
/* 2221 */       synchronized (this.handshakeCompleteLock) {
/* 2222 */         while (!this.handshakeComplete) {
/*      */           try {
/*      */             try {
/* 2225 */               this.secureSocket.getInputStream().read();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2231 */               JmqiException traceRet2 = new JmqiException(this.env, 9771, new String[] { null, null, getRemoteHostDescr(), "SSLSocket.read succeeded!", this.customSslSocketFactoryUsed ? this.sslSocketFactory.toString() : "default" }, 2, 2397, null);
/*      */               
/* 2233 */               if (Trace.isOn) {
/* 2234 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSecureKeyReset()", (Throwable)traceRet2, 2);
/*      */               }
/*      */               
/* 2237 */               throw traceRet2;
/*      */             }
/* 2239 */             catch (SocketTimeoutException timeout) {
/* 2240 */               if (Trace.isOn) {
/* 2241 */                 Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSecureKeyReset()", timeout, 2);
/*      */               
/*      */               }
/*      */             
/*      */             }
/* 2246 */             catch (IOException e) {
/* 2247 */               if (Trace.isOn) {
/* 2248 */                 Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSecureKeyReset()", e, 3);
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2254 */               JmqiException traceRet2 = new JmqiException(this.env, 9771, new String[] { JmqiTools.getExSumm(e), null, getRemoteHostDescr(), "SSLSocket.read", this.customSslSocketFactoryUsed ? this.sslSocketFactory.toString() : "default" }, 2, 2397, e);
/*      */               
/* 2256 */               if (Trace.isOn) {
/* 2257 */                 Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSecureKeyReset()", (Throwable)traceRet2, 3);
/*      */               }
/*      */               
/* 2260 */               throw traceRet2;
/*      */             } 
/* 2262 */             this.handshakeCompleteLock.wait(1L);
/*      */           }
/* 2264 */           catch (InterruptedException e) {
/* 2265 */             if (Trace.isOn) {
/* 2266 */               Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSecureKeyReset()", e, 4);
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/* 2271 */         this.handshakeComplete = false;
/*      */       }
/*      */     
/* 2274 */     } catch (SocketException e) {
/* 2275 */       if (Trace.isOn) {
/* 2276 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSecureKeyReset()", e, 5);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2282 */       JmqiException traceRet2 = new JmqiException(this.env, 9771, new String[] { JmqiTools.getExSumm(e), null, getRemoteHostDescr(), "SSLSocket.get/setSoTimeout", this.customSslSocketFactoryUsed ? this.sslSocketFactory.toString() : "default" }, 2, 2397, e);
/*      */       
/* 2284 */       if (Trace.isOn) {
/* 2285 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSecureKeyReset()", (Throwable)traceRet2, 4);
/*      */       }
/*      */       
/* 2288 */       throw traceRet2;
/*      */     } finally {
/*      */       
/* 2291 */       if (Trace.isOn) {
/* 2292 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSecureKeyReset()");
/*      */       }
/* 2294 */       if (setTimeout) {
/*      */         try {
/* 2296 */           this.secureSocket.setSoTimeout(savedTimeout);
/*      */         }
/* 2298 */         catch (SocketException e) {
/* 2299 */           if (Trace.isOn) {
/* 2300 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSecureKeyReset()", e, 6);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2307 */           JmqiException traceRet2 = new JmqiException(this.env, 9771, new String[] { JmqiTools.getExSumm(e), null, getRemoteHostDescr(), "SSLSocket.startHandshake", this.customSslSocketFactoryUsed ? this.sslSocketFactory.toString() : "default" }, 2, 2397, e);
/*      */           
/* 2309 */           if (Trace.isOn) {
/* 2310 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSecureKeyReset()", (Throwable)traceRet2, 5);
/*      */           }
/*      */           
/* 2313 */           throw traceRet2;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 2318 */     if (Trace.isOn) {
/* 2319 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "protocolSecureKeyReset()");
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
/*      */   private void addServerNameIndication(String channelName) throws JmqiException {
/* 2332 */     if (Trace.isOn) {
/* 2333 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "addServerNameIndication()", new Object[] { channelName });
/*      */     }
/*      */     
/* 2336 */     StringBuilder buf = new StringBuilder(40);
/*      */ 
/*      */     
/* 2339 */     for (char ch : channelName.toCharArray()) {
/* 2340 */       if (ch == ' ') {
/*      */         break;
/*      */       }
/*      */       
/* 2344 */       if (Character.isUpperCase(ch)) {
/* 2345 */         buf.append(Character.toLowerCase(ch));
/*      */       
/*      */       }
/* 2348 */       else if (Character.isDigit(ch)) {
/* 2349 */         buf.append(ch);
/*      */       }
/*      */       else {
/*      */         
/* 2353 */         buf.append(String.format("%02x-", new Object[] { Integer.valueOf(ch) }));
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2358 */     SNIHostName serverName = null;
/*      */     try {
/* 2360 */       serverName = new SNIHostName(buf.toString() + ".chl.mq.ibm.com");
/*      */     }
/* 2362 */     catch (Exception e) {
/* 2363 */       BadChannelNameException bcne = new BadChannelNameException(e);
/* 2364 */       JmqiException traceRet = new JmqiException(this.env, -1, null, 2, 2393, bcne);
/*      */       
/* 2366 */       if (Trace.isOn) {
/* 2367 */         Trace.throwing(this, "RemoteTCPConnection", "addServerNameIndication()", (Throwable)traceRet);
/*      */       }
/* 2369 */       throw traceRet;
/*      */     } 
/*      */     
/* 2372 */     List<SNIServerName> serverNames = new ArrayList<>();
/* 2373 */     serverNames.add(serverName);
/*      */ 
/*      */     
/* 2376 */     if (Trace.isOn) {
/* 2377 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "addServerNameIndication() - SNI string is", serverName);
/*      */     }
/*      */     
/* 2380 */     SSLParameters params = this.secureSocket.getSSLParameters();
/* 2381 */     params.setServerNames(serverNames);
/* 2382 */     this.secureSocket.setSSLParameters(params);
/*      */     
/* 2384 */     if (Trace.isOn) {
/* 2385 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "addServerNameIndication()");
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
/*      */   private Socket makeSocketSecure(Socket socketToSecure, String remoteHostname, int remotePort, String sslCipherSpec) throws JmqiException {
/* 2405 */     if (Trace.isOn) {
/* 2406 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "makeSocketSecure(Socket,String,int,String)", new Object[] { socketToSecure, remoteHostname, 
/*      */             
/* 2408 */             Integer.valueOf(remotePort), sslCipherSpec });
/*      */     }
/*      */     
/* 2411 */     if (sslCipherSpec == null) {
/*      */       
/* 2413 */       JmqiException traceRet1 = new JmqiException(this.env, 9635, new String[] { null, null, getChannelName() }, 2, 2195, null);
/*      */       
/* 2415 */       if (Trace.isOn) {
/* 2416 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "makeSocketSecure(Socket,String,int,String)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 2419 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2426 */     if (this.sslSocketFactory == null) {
/* 2427 */       this.sslSocketFactory = chooseSocketFactory(sslCipherSpec);
/*      */     }
/*      */     
/* 2430 */     if (Trace.isOn) {
/* 2431 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "makeSocketSecure(Socket,String,int,String) - socket factory to use is", this.sslSocketFactory);
/*      */       
/* 2433 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "makeSocketSecure(Socket,String,int,String) - suites supported", 
/*      */           
/* 2435 */           flatten(this.sslSocketFactory.getSupportedCipherSuites()));
/*      */     } 
/*      */ 
/*      */     
/* 2439 */     if (this.cipherSuite == null) {
/* 2440 */       this.cipherSuite = parseCipherSpec(sslCipherSpec);
/*      */     }
/*      */     
/* 2443 */     if (this.fipsRequired && !JmqiUtils.isFipsCompatible(this.cipherSuite)) {
/*      */       
/* 2445 */       JmqiException traceRet1 = new JmqiException(this.env, 9635, new String[] { null, null, getChannelName() }, 2, 2393, null);
/*      */ 
/*      */       
/* 2448 */       if (Trace.isOn) {
/* 2449 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "makeSocketSecure(Socket,String,int,String)", (Throwable)traceRet1, 2);
/*      */       }
/*      */       
/* 2452 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 2457 */       this.secureSocket = (SSLSocket)this.sslSocketFactory.createSocket(socketToSecure, remoteHostname, remotePort, true);
/*      */     }
/* 2459 */     catch (IOException e) {
/* 2460 */       if (Trace.isOn) {
/* 2461 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "makeSocketSecure(Socket,String,int,String)", e, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2466 */       JmqiException traceRet2 = new JmqiException(this.env, 9771, new String[] { JmqiTools.getExSumm(e), null, getRemoteHostDescr(), "SSLSocket.createSocket", this.customSslSocketFactoryUsed ? this.sslSocketFactory.toString() : "default" }, 2, 2397, e);
/*      */       
/* 2468 */       if (Trace.isOn) {
/* 2469 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "makeSocketSecure(Socket,String,int,String)", (Throwable)traceRet2, 3);
/*      */       }
/*      */       
/* 2472 */       throw traceRet2;
/*      */     } 
/*      */ 
/*      */     
/* 2476 */     if (this.secureSocket == null) {
/*      */ 
/*      */       
/* 2479 */       JmqiException traceRet4 = new JmqiException(this.env, 9771, new String[] { null, null, getRemoteHostDescr(), "SSLSocket.createSocket", this.customSslSocketFactoryUsed ? this.sslSocketFactory.toString() : "default" }, 2, 2397, null);
/*      */       
/* 2481 */       if (Trace.isOn) {
/* 2482 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "makeSocketSecure(Socket,String,int,String)", (Throwable)traceRet4, 4);
/*      */       }
/*      */       
/* 2485 */       throw traceRet4;
/*      */     } 
/*      */     
/* 2488 */     if (Trace.isOn) {
/* 2489 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "makeSocketSecure(Socket,String,int,String) - secure socket is", this.secureSocket);
/*      */       
/* 2491 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "makeSocketSecure(Socket,String,int,String) - protocols supported", 
/*      */           
/* 2493 */           flatten(this.secureSocket.getSupportedProtocols()));
/* 2494 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "makeSocketSecure(Socket,String,int,String) - suites supported", 
/*      */           
/* 2496 */           flatten(this.secureSocket.getSupportedCipherSuites()));
/*      */     } 
/*      */ 
/*      */     
/* 2500 */     for (String supportedProtocol : this.secureSocket.getSupportedProtocols()) {
/* 2501 */       if (supportedProtocol.equalsIgnoreCase("TLSv1.3")) {
/*      */         
/* 2503 */         this.isTLS13 = true;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 2508 */     if (Trace.isOn) {
/* 2509 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "isTLS13", 
/* 2510 */           Boolean.valueOf(this.isTLS13));
/*      */     }
/*      */ 
/*      */     
/* 2514 */     this.isSecure = true;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2519 */     String[] protocols = JmqiUtils.ProtocolList.getProtocolList(this.cipherSuite, this.isTLS13);
/* 2520 */     if (protocols.length > 0) {
/* 2521 */       this.secureSocket.setEnabledProtocols(protocols);
/*      */       
/* 2523 */       String[] cipherSuites = JmqiUtils.protocolToCipherSuiteList(protocols, this.fipsRequired);
/* 2524 */       cipherSuites = removeUnsupportedCipherSuites(cipherSuites, this.secureSocket.getSupportedCipherSuites());
/* 2525 */       this.secureSocket.setEnabledCipherSuites(cipherSuites);
/*      */ 
/*      */       
/* 2528 */       if (this.allowSNI && canDoSNI) {
/* 2529 */         addServerNameIndication(this.negotiatedChannel.getChannelName());
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 2534 */       String[] cipherSuites = { this.cipherSuite };
/*      */       
/* 2536 */       String protocol = JmqiUtils.getProtocol(sslCipherSpec);
/* 2537 */       protocols = new String[] { protocol };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2545 */         this.secureSocket.setEnabledCipherSuites(cipherSuites);
/* 2546 */         this.secureSocket.setEnabledProtocols(protocols);
/*      */       }
/* 2548 */       catch (IllegalArgumentException e) {
/* 2549 */         if (Trace.isOn) {
/* 2550 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "makeSocketSecure(Socket,String,int,String)", e, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2555 */         JmqiException traceRet5 = new JmqiException(this.env, 9771, new String[] { JmqiTools.getExSumm(e), null, getRemoteHostDescr(), "SSLSocket.createSocket", this.customSslSocketFactoryUsed ? this.sslSocketFactory.toString() : "default" }, 2, 2393, e);
/*      */         
/* 2557 */         if (Trace.isOn) {
/* 2558 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "makeSocketSecure(Socket,String,int,String)", (Throwable)traceRet5, 5);
/*      */         }
/*      */         
/* 2561 */         throw traceRet5;
/*      */       } 
/*      */       
/* 2564 */       if (this.allowSNI && canDoSNI && supportsSNI(protocol))
/*      */       {
/* 2566 */         addServerNameIndication(this.negotiatedChannel.getChannelName());
/*      */       }
/*      */     } 
/* 2569 */     Socket traceRet3 = this.secureSocket;
/*      */     
/* 2571 */     if (Trace.isOn) {
/* 2572 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "makeSocketSecure(Socket,String,int,String)", traceRet3);
/*      */     }
/*      */     
/* 2575 */     return traceRet3;
/*      */   }
/*      */   protected boolean supportsSNI(String protocol) {
/*      */     boolean traceRet1;
/* 2579 */     if (Trace.isOn) {
/* 2580 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "supportsSNI(String)", new Object[] { protocol });
/*      */     }
/*      */ 
/*      */     
/* 2584 */     switch (protocol) {
/*      */       case "TLSv1.2":
/*      */       case "TLSv1.3":
/* 2587 */         traceRet1 = true;
/*      */         break;
/*      */       default:
/* 2590 */         traceRet1 = false; break;
/*      */     } 
/* 2592 */     if (Trace.isOn) {
/* 2593 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "supportsSNI(String)", Boolean.valueOf(traceRet1));
/*      */     }
/* 2595 */     return traceRet1;
/*      */   }
/*      */   
/*      */   private String flatten(String[] strings) {
/* 2599 */     if (Trace.isOn) {
/* 2600 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "flatten(String [ ])", new Object[] { strings });
/*      */     }
/*      */     
/* 2603 */     StringBuilder result = new StringBuilder();
/* 2604 */     for (String s : strings) {
/* 2605 */       if (result.length() > 0) {
/* 2606 */         result.append(',');
/*      */       }
/* 2608 */       result.append(s);
/*      */     } 
/* 2610 */     String traceRet1 = result.toString();
/* 2611 */     if (Trace.isOn) {
/* 2612 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "flatten(String [ ])", traceRet1);
/*      */     }
/* 2614 */     return traceRet1;
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
/*      */   public void handshakeCompleted(HandshakeCompletedEvent event) {
/* 2627 */     if (Trace.isOn) {
/* 2628 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "handshakeCompleted(HandshakeCompletedEvent)", new Object[] { event });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 2633 */       Certificate[] peerCertificateChain = event.getPeerCertificates();
/*      */       
/* 2635 */       if (peerCertificateChain != null && peerCertificateChain.length > 0 && peerCertificateChain[0] instanceof X509Certificate) {
/* 2636 */         this.peerCertificate = (X509Certificate)peerCertificateChain[0];
/* 2637 */         this.peerIssuerDN = this.peerCertificate.getIssuerX500Principal().getName();
/*      */       }
/*      */       else {
/*      */         
/* 2641 */         this.peerCertificate = null;
/*      */       }
/*      */     
/* 2644 */     } catch (SSLPeerUnverifiedException e) {
/* 2645 */       if (Trace.isOn) {
/* 2646 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "handshakeCompleted(HandshakeCompletedEvent)", e);
/*      */       }
/*      */ 
/*      */       
/* 2650 */       this.peerCertificate = null;
/*      */     } 
/*      */ 
/*      */     
/* 2654 */     synchronized (this.handshakeCompleteLock) {
/* 2655 */       this.handshakeComplete = true;
/* 2656 */       this.handshakeCompleteLock.notifyAll();
/*      */     } 
/*      */     
/* 2659 */     if (Trace.isOn) {
/* 2660 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "handshakeCompleted(HandshakeCompletedEvent)");
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
/*      */   private String parseCipherSpec(String cipherSpec) throws JmqiException {
/* 2673 */     if (Trace.isOn) {
/* 2674 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "parseCipherSpec(String)", new Object[] { cipherSpec });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2679 */     String cipherSuite = JmqiUtils.toCipherSuite(cipherSpec, this.fipsRequired);
/*      */ 
/*      */     
/* 2682 */     if (cipherSuite == null) {
/*      */       
/* 2684 */       JmqiException traceRet1 = new JmqiException(this.env, 9635, new String[] { null, null, getChannelName() }, 2, 2393, null);
/*      */ 
/*      */       
/* 2687 */       if (Trace.isOn) {
/* 2688 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "parseCipherSpec(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 2691 */       throw traceRet1;
/*      */     } 
/*      */     
/* 2694 */     if (Trace.isOn) {
/* 2695 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "parseCipherSpec(String)", cipherSuite);
/*      */     }
/* 2697 */     return cipherSuite;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String[] removeUnsupportedCipherSuites(String[] ciphersuitesToTrim, String[] supportedCipherSuites) throws JmqiException {
/* 2708 */     if (Trace.isOn) {
/* 2709 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "removeUnsupportedCipherSuites(String[], String[])", new Object[] { ciphersuitesToTrim, supportedCipherSuites });
/*      */     }
/*      */     
/* 2712 */     ArrayList<String> toReturn = new ArrayList<>();
/*      */     
/* 2714 */     for (String p1 : ciphersuitesToTrim) {
/* 2715 */       for (String p2 : supportedCipherSuites) {
/* 2716 */         if (p1.equals(p2)) {
/* 2717 */           toReturn.add(p1);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 2722 */     if (Trace.isOn) {
/* 2723 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "removeUnsupportedCipherSuites(String[], String[])", new Object[] { toReturn.toArray(new String[0]) });
/*      */     }
/* 2725 */     return toReturn.<String>toArray(new String[0]);
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
/*      */   private SSLSocketFactory chooseSocketFactory(String sslCipherSpec) throws JmqiException {
/* 2738 */     if (Trace.isOn) {
/* 2739 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "chooseSocketFactory(String)", new Object[] { sslCipherSpec });
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2796 */     SSLSocketFactory sslFact = null;
/*      */     
/* 2798 */     if (this.fipsRequired) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2804 */       if (Trace.isOn) {
/* 2805 */         Trace.data(this, "chooseSocketFactory(String sslCipherSpec)", "FIPS mode has been selected");
/*      */       }
/* 2807 */       sslFact = getFipsSocketFactory();
/*      */     } else {
/*      */       
/* 2810 */       sslFact = getNonFipsSocketFactory();
/*      */     } 
/*      */     
/* 2813 */     if (Trace.isOn) {
/* 2814 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "chooseSocketFactory(String)", sslFact);
/*      */     }
/* 2816 */     return sslFact;
/*      */   }
/*      */   
/*      */   private SSLSocketFactory getFipsSocketFactory() throws JmqiException {
/* 2820 */     if (Trace.isOn) {
/* 2821 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getFipsSocketFactory()");
/*      */     }
/*      */     
/* 2824 */     SSLSocketFactory sslFact = null;
/* 2825 */     boolean propertyChanged = ensureFIPSSystemPropertySet("true");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2832 */       Class<?> JCEFIPSProviderCls = JmqiTools.dynamicLoadClass(this.env, "com.ibm.crypto.plus.provider.IBMJCEPlusFIPS", 
/* 2833 */           getClass());
/* 2834 */       Provider JCEFIPSprovider = (Provider)JCEFIPSProviderCls.newInstance();
/* 2835 */       Security.insertProviderAt(JCEFIPSprovider, 1);
/*      */       
/* 2837 */       System.setProperty("com.ibm.jsse2.usefipsProviderName", "IBMJCEPlusFIPS");
/* 2838 */       System.setProperty("com.ibm.jsse2.usefipsprovider", "true");
/*      */       
/* 2840 */       Class<?> socketFactoryCls = JmqiTools.dynamicLoadClass(this.env, "com.ibm.jsse2.SSLSocketFactoryImpl", 
/* 2841 */           getClass());
/*      */       
/* 2843 */       sslFact = (SSLSocketFactory)socketFactoryCls.newInstance();
/*      */     
/*      */     }
/* 2846 */     catch (Exception e) {
/* 2847 */       if (Trace.isOn) {
/* 2848 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getFipsSocketFactory()", e, 1);
/*      */       }
/*      */ 
/*      */       
/* 2852 */       HashMap<String, Object> ffstInfo = new HashMap<>();
/* 2853 */       ffstInfo.put("Description", "JVM does not contain a FIPS compliant JSSE");
/* 2854 */       Trace.ffst(this, "getFipsSocketFactory()", "01", ffstInfo, null);
/* 2855 */       JmqiException traceRet = new JmqiException(this.env, -1, null, 2, 2393, null);
/*      */       
/* 2857 */       if (Trace.isOn) {
/* 2858 */         Trace.throwing(this, "RemoteTCPConnection", "getFipsSocketFactory()", (Throwable)traceRet, 1);
/*      */       }
/* 2860 */       if (Trace.isOn) {
/* 2861 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getFipsSocketFactory()", (Throwable)traceRet, 1);
/*      */       }
/*      */       
/* 2864 */       throw traceRet;
/*      */     } finally {
/*      */       
/* 2867 */       if (Trace.isOn) {
/* 2868 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getFipsSocketFactory()");
/*      */       }
/* 2870 */       if (propertyChanged) {
/*      */         try {
/* 2872 */           AccessController.doPrivileged(new PrivilegedExceptionAction()
/*      */               {
/*      */                 public Object run()
/*      */                 {
/* 2876 */                   if (Trace.isOn) {
/* 2877 */                     Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "run()");
/*      */                   }
/* 2879 */                   System.setProperty("com.ibm.jsse2.usefipsprovider", "false");
/* 2880 */                   if (Trace.isOn) {
/* 2881 */                     Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.null", "run()", null);
/*      */                   }
/* 2883 */                   return null;
/*      */                 }
/*      */               });
/*      */         }
/* 2887 */         catch (PrivilegedActionException e) {
/* 2888 */           if (Trace.isOn) {
/* 2889 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getFipsSocketFactory()", e, 2);
/*      */           }
/*      */ 
/*      */           
/* 2893 */           HashMap<String, Object> ffstInfo = new HashMap<>();
/* 2894 */           ffstInfo.put("Description", "Cannot set property com.ibm.jsse2.usefipsprovider");
/* 2895 */           ffstInfo.put("Exception message", e.getMessage());
/* 2896 */           Trace.ffst(this, "getFipsSocketFactory()", "02", ffstInfo, null);
/* 2897 */           JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2393, null);
/*      */           
/* 2899 */           if (Trace.isOn) {
/* 2900 */             Trace.throwing(this, "RemoteTCPConnection", "getFipsSocketFactory()", (Throwable)traceRet1, 2);
/*      */           }
/* 2902 */           if (Trace.isOn) {
/* 2903 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getFipsSocketFactory()", (Throwable)traceRet1, 2);
/*      */           }
/*      */           
/* 2906 */           throw traceRet1;
/*      */         } 
/*      */       }
/*      */     } 
/* 2910 */     if (Trace.isOn) {
/* 2911 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getFipsSocketFactory()", sslFact);
/*      */     }
/* 2913 */     return sslFact;
/*      */   }
/*      */   
/*      */   private SSLSocketFactory getNonFipsSocketFactory() throws JmqiException {
/* 2917 */     if (Trace.isOn) {
/* 2918 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getNonFipsSocketFactory()");
/*      */     }
/* 2920 */     SSLSocketFactory sslFact = null;
/* 2921 */     boolean propertyChanged = ensureFIPSSystemPropertySet("false");
/*      */     
/*      */     try {
/*      */       SSLContext sslContext;
/* 2925 */       KeyManager[] keyManArray = null;
/* 2926 */       if (JmqiUtils.usingIBMMappings()) {
/*      */ 
/*      */         
/* 2929 */         sslContext = SSLContext.getInstance("SSL_TLSv2");
/*      */       } else {
/*      */ 
/*      */         
/*      */         try {
/*      */           
/* 2935 */           sslContext = SSLContext.getInstance("TLSv1.3");
/*      */         }
/* 2937 */         catch (Exception e) {
/* 2938 */           sslContext = SSLContext.getInstance("TLSv1.2");
/*      */         } 
/*      */ 
/*      */         
/* 2942 */         String keyStoreFile = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*      */             {
/*      */               public String run()
/*      */               {
/* 2946 */                 if (Trace.isOn) {
/* 2947 */                   Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "run()");
/*      */                 }
/* 2949 */                 String ret = System.getProperty("javax.net.ssl.keyStore");
/* 2950 */                 if (Trace.isOn) {
/* 2951 */                   Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "run()", ret);
/*      */                 }
/* 2953 */                 return ret;
/*      */               }
/*      */             });
/*      */ 
/*      */         
/* 2958 */         String keyStorePassword = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*      */             {
/*      */               public String run()
/*      */               {
/* 2962 */                 if (Trace.isOn) {
/* 2963 */                   Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "run()");
/*      */                 }
/* 2965 */                 String ret = System.getProperty("javax.net.ssl.keyStorePassword");
/* 2966 */                 if (Trace.isOn) {
/* 2967 */                   Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "run()", "********");
/*      */                 }
/*      */                 
/* 2970 */                 return ret;
/*      */               }
/*      */             });
/*      */         
/* 2974 */         if (keyStoreFile != null && keyStorePassword != null) {
/* 2975 */           char[] keyStorePasswordArray = keyStorePassword.toCharArray();
/* 2976 */           KeyStore ks = KeyStore.getInstance("JKS");
/* 2977 */           ks.load(new FileInputStream(keyStoreFile), keyStorePasswordArray);
/*      */ 
/*      */           
/* 2980 */           KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
/* 2981 */           keyManagerFactory.init(ks, keyStorePasswordArray);
/* 2982 */           keyManArray = keyManagerFactory.getKeyManagers();
/*      */ 
/*      */         
/*      */         }
/* 2986 */         else if (Trace.isOn) {
/* 2987 */           Trace.data(this, "getNonFipsSocketFactory()", "Not using keystore as password and/or file was null");
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2995 */       if (Trace.isOn) {
/* 2996 */         Trace.data(this, "getNonFipsSocketFactory()", "Context", sslContext);
/* 2997 */         Trace.data(this, "getNonFipsSocketFactory()", "Context provider", sslContext.getProvider());
/* 2998 */         Trace.data(this, "getNonFipsSocketFactory()", "Context provider class", sslContext
/* 2999 */             .getProvider().getClass());
/*      */       } 
/*      */       
/* 3002 */       sslContext.init(keyManArray, null, null);
/* 3003 */       sslFact = sslContext.getSocketFactory();
/*      */     }
/* 3005 */     catch (NoSuchAlgorithmException nse) {
/* 3006 */       if (Trace.isOn) {
/* 3007 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getNonFipsSocketFactory()", nse, 1);
/*      */       }
/*      */       
/* 3010 */       if (Trace.isOn) {
/* 3011 */         Trace.data(this, "getNonFipsSocketFactory()", "Can't get SSL_TLSv2 Context");
/*      */       }
/*      */       
/* 3014 */       sslFact = (SSLSocketFactory)SSLSocketFactory.getDefault();
/*      */     
/*      */     }
/* 3017 */     catch (GeneralSecurityException e) {
/* 3018 */       if (Trace.isOn) {
/* 3019 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getNonFipsSocketFactory()", e, 1);
/*      */       }
/*      */       
/* 3022 */       HashMap<String, Object> ffstInfo = new HashMap<>();
/* 3023 */       ffstInfo.put("Description", "Error retrieving certificates from keystore");
/* 3024 */       ffstInfo.put("Exception message", e.getMessage());
/* 3025 */       Trace.ffst(this, "getNonFipsSocketFactory()", "01", ffstInfo, null);
/* 3026 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2393, e);
/*      */       
/* 3028 */       if (Trace.isOn) {
/* 3029 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getNonFipsSocketFactory()", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 3032 */       throw traceRet1;
/*      */     }
/* 3034 */     catch (IOException e) {
/* 3035 */       if (Trace.isOn) {
/* 3036 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getNonFipsSocketFactory()", e, 1);
/*      */       }
/*      */       
/* 3039 */       HashMap<String, Object> ffstInfo = new HashMap<>();
/* 3040 */       ffstInfo.put("Description", "Cannot open keystore file");
/* 3041 */       ffstInfo.put("Exception message", e.getMessage());
/* 3042 */       Trace.ffst(this, "getNonFipsSocketFactory()", "03", ffstInfo, null);
/* 3043 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2393, e);
/*      */       
/* 3045 */       if (Trace.isOn) {
/* 3046 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getNonFipsSocketFactory()", (Throwable)traceRet1, 3);
/*      */       }
/*      */       
/* 3049 */       throw traceRet1;
/*      */     } finally {
/*      */       
/* 3052 */       if (Trace.isOn) {
/* 3053 */         Trace.finallyBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getNonFipsSocketFactory()");
/*      */       }
/*      */       
/* 3056 */       if (propertyChanged) {
/*      */         try {
/* 3058 */           AccessController.doPrivileged(new PrivilegedExceptionAction()
/*      */               {
/*      */                 public Object run()
/*      */                 {
/* 3062 */                   if (Trace.isOn) {
/* 3063 */                     Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "run()");
/*      */                   }
/* 3065 */                   System.setProperty("com.ibm.jsse2.usefipsprovider", "true");
/* 3066 */                   if (Trace.isOn) {
/* 3067 */                     Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.null", "run()", null);
/*      */                   }
/* 3069 */                   return null;
/*      */                 }
/*      */               });
/*      */         }
/* 3073 */         catch (PrivilegedActionException e) {
/* 3074 */           if (Trace.isOn) {
/* 3075 */             Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getNonFipsSocketFactory()", e, 3);
/*      */           }
/*      */ 
/*      */           
/* 3079 */           HashMap<String, Object> ffstInfo = new HashMap<>();
/* 3080 */           ffstInfo.put("Description", "Cannot set property com.ibm.jsse2.usefipsprovider");
/* 3081 */           ffstInfo.put("Exception message", e.getMessage());
/* 3082 */           Trace.ffst(this, "getNonFipsSocketFactory()", "02", ffstInfo, null);
/* 3083 */           JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2393, null);
/*      */           
/* 3085 */           if (Trace.isOn) {
/* 3086 */             Trace.throwing(this, "RemoteTCPConnection", "getNonFipsSocketFactory()", (Throwable)traceRet1, 2);
/*      */           }
/* 3088 */           if (Trace.isOn) {
/* 3089 */             Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getNonFipsSocketFactory()", (Throwable)traceRet1, 2);
/*      */           }
/*      */           
/* 3092 */           throw traceRet1;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 3097 */     if (Trace.isOn) {
/* 3098 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getNonFipsSocketFactory()", sslFact);
/*      */     }
/* 3100 */     return sslFact;
/*      */   }
/*      */   
/*      */   private boolean ensureFIPSSystemPropertySet(final String desiredValue) throws JmqiException {
/* 3104 */     if (Trace.isOn) {
/* 3105 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "ensureFIPSSystemPropertySet(final String)", new Object[] { desiredValue });
/*      */     }
/*      */     
/* 3108 */     if (Trace.isOn) {
/* 3109 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "ensureFIPSSystemPropertySet(String)", new Object[] { desiredValue });
/*      */     }
/*      */     
/* 3112 */     String oldPropertyValue = null;
/* 3113 */     boolean propertyChanged = false;
/*      */     
/*      */     try {
/* 3116 */       oldPropertyValue = AccessController.<String>doPrivileged(new PrivilegedExceptionAction<String>()
/*      */           {
/*      */             public String run()
/*      */             {
/* 3120 */               if (Trace.isOn) {
/* 3121 */                 Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "run()");
/*      */               }
/* 3123 */               String traceRet1 = System.getProperty("com.ibm.jsse2.usefipsprovider");
/* 3124 */               if (Trace.isOn) {
/* 3125 */                 Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.null", "run()", traceRet1);
/*      */               }
/* 3127 */               return traceRet1;
/*      */             }
/*      */           });
/*      */     }
/* 3131 */     catch (PrivilegedActionException e) {
/* 3132 */       if (Trace.isOn) {
/* 3133 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "ensureFIPSSystemPropertySet(final String)", e, 1);
/*      */       }
/*      */ 
/*      */       
/* 3137 */       HashMap<String, Object> ffstInfo = new HashMap<>();
/* 3138 */       ffstInfo.put("Description", "Cannot get property com.ibm.jsse2.usefipsprovider");
/* 3139 */       ffstInfo.put("Exception message", e.getMessage());
/* 3140 */       Trace.ffst(this, "buildTLSV1_2SocketFactory()", "01", ffstInfo, null);
/* 3141 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2393, null);
/*      */       
/* 3143 */       if (Trace.isOn) {
/* 3144 */         Trace.throwing(this, "RemoteTCPConnection", "ensureFIPSSystemPropertySet(String)", (Throwable)traceRet1, 1);
/*      */       }
/* 3146 */       if (Trace.isOn) {
/* 3147 */         Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "ensureFIPSSystemPropertySet(final String)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 3150 */       throw traceRet1;
/*      */     } 
/*      */     
/* 3153 */     if (Trace.isOn) {
/* 3154 */       Trace.data(this, "ensureFIPSSystemPropertySet(String)", "value of property com.ibm.jsse2.usefipsprovider", oldPropertyValue);
/*      */     }
/*      */ 
/*      */     
/* 3158 */     if (oldPropertyValue == null) {
/* 3159 */       oldPropertyValue = "false";
/*      */     }
/*      */     
/* 3162 */     if (!oldPropertyValue.equals(desiredValue)) {
/*      */       try {
/* 3164 */         AccessController.doPrivileged(new PrivilegedExceptionAction()
/*      */             {
/*      */               public Object run()
/*      */               {
/* 3168 */                 if (Trace.isOn) {
/* 3169 */                   Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "run()");
/*      */                 }
/* 3171 */                 System.setProperty("com.ibm.jsse2.usefipsprovider", desiredValue);
/* 3172 */                 if (Trace.isOn) {
/* 3173 */                   Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.null", "run()", null);
/*      */                 }
/* 3175 */                 return null;
/*      */               }
/*      */             });
/*      */       }
/* 3179 */       catch (PrivilegedActionException e) {
/* 3180 */         if (Trace.isOn) {
/* 3181 */           Trace.catchBlock(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "ensureFIPSSystemPropertySet(final String)", e, 2);
/*      */         }
/*      */ 
/*      */         
/* 3185 */         HashMap<String, Object> ffstInfo = new HashMap<>();
/* 3186 */         ffstInfo.put("Description", "Cannot set property com.ibm.jsse2.usefipsprovider");
/* 3187 */         ffstInfo.put("Exception message", e.getMessage());
/* 3188 */         Trace.ffst(this, "ensureFIPSSystemPropertySet(String)", "02", ffstInfo, null);
/* 3189 */         JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2393, null);
/*      */         
/* 3191 */         if (Trace.isOn) {
/* 3192 */           Trace.throwing(this, "RemoteTCPConnection", "ensureFIPSSystemPropertySet(String)", (Throwable)traceRet1, 2);
/*      */         }
/* 3194 */         if (Trace.isOn) {
/* 3195 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "ensureFIPSSystemPropertySet(final String)", (Throwable)traceRet1, 2);
/*      */         }
/*      */         
/* 3198 */         throw traceRet1;
/*      */       } 
/* 3200 */       propertyChanged = true;
/*      */     } 
/* 3202 */     if (Trace.isOn) {
/* 3203 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "ensureFIPSSystemPropertySet(String)", 
/* 3204 */           Boolean.valueOf(propertyChanged));
/*      */     }
/* 3206 */     if (Trace.isOn) {
/* 3207 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "ensureFIPSSystemPropertySet(final String)", 
/* 3208 */           Boolean.valueOf(propertyChanged));
/*      */     }
/* 3210 */     return propertyChanged;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSecure() {
/* 3220 */     if (Trace.isOn) {
/* 3221 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "isSecure()", "getter", 
/* 3222 */           Boolean.valueOf(this.isSecure));
/*      */     }
/* 3224 */     return this.isSecure;
/*      */   }
/*      */ 
/*      */   
/*      */   boolean isEncrypted() {
/* 3229 */     boolean traceRet1 = (isSecure() && !this.cipherSuite.contains("NULL"));
/* 3230 */     if (Trace.isOn) {
/* 3231 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "isEncrypted()", "getter", 
/* 3232 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 3234 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRemoteCertIssuerDN() {
/* 3241 */     if (Trace.isOn) {
/* 3242 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getRemoteCertIssuerDN()", "getter", this.peerIssuerDN);
/*      */     }
/*      */     
/* 3245 */     return this.peerIssuerDN;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeTraceInfo(StringBuffer traceBuffer) {
/* 3254 */     traceBuffer.append(",peer=");
/* 3255 */     traceBuffer.append(this.socketIpAddress);
/* 3256 */     traceBuffer.append("(");
/* 3257 */     traceBuffer.append(this.socketIpAddressPort);
/* 3258 */     traceBuffer.append(")");
/* 3259 */     traceBuffer.append(",localport=");
/* 3260 */     traceBuffer.append(this.socketPort);
/* 3261 */     traceBuffer.append(",ssl=");
/* 3262 */     traceBuffer.append(this.isSecure ? this.cipherSuite : "no");
/*      */     
/* 3264 */     if (this.isSecure && this.peerCertificate != null) {
/* 3265 */       traceBuffer.append(",peerDN=\"");
/* 3266 */       traceBuffer.append(this.peerCertificate.getSubjectX500Principal().toString());
/* 3267 */       traceBuffer.append("\",issuerDN=\"");
/* 3268 */       traceBuffer.append(this.peerCertificate.getIssuerX500Principal().toString());
/* 3269 */       traceBuffer.append("\"");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String getRemoteHostDescr() {
/* 3278 */     String hostDescr = null;
/* 3279 */     if (this.socket != null) {
/* 3280 */       SocketAddress remoteHost = this.socket.getRemoteSocketAddress();
/* 3281 */       if (remoteHost != null) {
/* 3282 */         hostDescr = remoteHost.toString();
/*      */         
/* 3284 */         if (remoteHost instanceof InetSocketAddress && this.dnsLookupOnError) {
/* 3285 */           InetSocketAddress remoteInetHost = (InetSocketAddress)remoteHost;
/* 3286 */           hostDescr = hostDescr + " (" + remoteInetHost.getHostName() + ")";
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 3291 */     if (Trace.isOn) {
/* 3292 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getRemoteHostDescr()", "getter", hostDescr);
/*      */     }
/*      */     
/* 3295 */     return hostDescr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTrpType() {
/* 3305 */     if (Trace.isOn) {
/* 3306 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getTrpType()", "getter", "TCP");
/*      */     }
/* 3308 */     return "TCP";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSocketIpAddress() {
/* 3316 */     if (Trace.isOn) {
/* 3317 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getSocketIpAddress()", "getter", this.socketIpAddress);
/*      */     }
/* 3319 */     return this.socketIpAddress;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSocketIpPort() {
/* 3327 */     if (Trace.isOn) {
/* 3328 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection", "getSocketIpPort()", "getter", Integer.valueOf(this.socketIpAddressPort));
/*      */     }
/* 3330 */     return this.socketIpAddressPort;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class HandshakeCompleteLock
/*      */   {
/*      */     HandshakeCompleteLock() {
/* 3343 */       if (Trace.isOn) {
/* 3344 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.HandshakeCompleteLock", "<init>()");
/*      */       }
/* 3346 */       if (Trace.isOn) {
/* 3347 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.HandshakeCompleteLock", "<init>()");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private class ConnectionSpecification
/*      */   {
/*      */     private String localHostname;
/*      */     private int portStart;
/*      */     private int portEnd;
/*      */     
/*      */     ConnectionSpecification(String localAddressP) throws JmqiException {
/* 3360 */       if (Trace.isOn) {
/* 3361 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection$ConnectionSpecification", "<init>(String)", new Object[] { localAddressP });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3366 */       if (localAddressP != null) {
/*      */         
/* 3368 */         String localAddress = localAddressP.trim();
/*      */         
/* 3370 */         int openingBracket = localAddress.lastIndexOf('(');
/* 3371 */         int closingBracket = localAddress.lastIndexOf(')');
/*      */         
/* 3373 */         if (openingBracket == -1 && closingBracket == -1) {
/*      */           
/* 3375 */           this.localHostname = localAddress;
/* 3376 */           this.portStart = RemoteTCPConnection.this.defaultPortStart;
/* 3377 */           this.portEnd = RemoteTCPConnection.this.defaultPortEnd;
/*      */         }
/*      */         else {
/*      */           
/* 3381 */           checkAddressFormat(localAddress, openingBracket, closingBracket);
/*      */           
/* 3383 */           this.localHostname = localAddress.substring(0, openingBracket).trim();
/*      */ 
/*      */           
/* 3386 */           int commaIndex = localAddress.indexOf(',', openingBracket + 1);
/* 3387 */           int firstPortEnd = commaIndex;
/*      */           
/* 3389 */           if (firstPortEnd < 0) {
/* 3390 */             firstPortEnd = closingBracket;
/*      */           }
/*      */           
/* 3393 */           this.portStart = parsePort(localAddress, openingBracket, firstPortEnd);
/*      */ 
/*      */           
/* 3396 */           if (commaIndex != -1) {
/* 3397 */             this.portEnd = parsePort(localAddress, commaIndex, closingBracket);
/*      */           }
/*      */           else {
/*      */             
/* 3401 */             this.portEnd = this.portStart;
/*      */           } 
/*      */         } 
/*      */       } 
/* 3405 */       if (Trace.isOn) {
/* 3406 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection$ConnectionSpecification", "<init>(String)", 
/* 3407 */             toString());
/*      */       }
/*      */     }
/*      */     
/*      */     private int parsePort(String localAddress, int startOffset, int endOffset) throws JmqiException {
/* 3412 */       if (Trace.isOn) {
/* 3413 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection$ConnectionSpecification", "parsePort(String,int,int)", new Object[] { localAddress, 
/* 3414 */               Integer.valueOf(startOffset), Integer.valueOf(endOffset) });
/*      */       }
/* 3416 */       int port = 0;
/*      */       try {
/* 3418 */         port = Integer.parseInt(localAddress.substring(startOffset + 1, endOffset).trim());
/*      */       }
/* 3420 */       catch (NumberFormatException e) {
/*      */         
/* 3422 */         JmqiException traceRet = new JmqiException(RemoteTCPConnection.this.env, 9913, new String[] { JmqiTools.getExSumm(e), null, localAddress }, 2, 2538, e);
/*      */ 
/*      */         
/* 3425 */         if (Trace.isOn) {
/* 3426 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection$ConnectionSpecification", "parsePort(String,int,int)", (Throwable)traceRet, 1);
/*      */         }
/*      */ 
/*      */         
/* 3430 */         throw traceRet;
/*      */       } 
/*      */       
/* 3433 */       if (port < 0) {
/* 3434 */         JmqiException traceRet = new JmqiException(RemoteTCPConnection.this.env, 9913, new String[] { "parse error", null, localAddress }, 2, 2538, null);
/*      */ 
/*      */ 
/*      */         
/* 3438 */         if (Trace.isOn) {
/* 3439 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection$ConnectionSpecification", "parsePort(String,int,int)", (Throwable)traceRet, 3);
/*      */         }
/*      */ 
/*      */         
/* 3443 */         throw traceRet;
/*      */       } 
/* 3445 */       if (Trace.isOn) {
/* 3446 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection$ConnectionSpecification", "parsePort(String,int,int)");
/*      */       }
/*      */ 
/*      */       
/* 3450 */       return port;
/*      */     }
/*      */     
/*      */     private void checkAddressFormat(String localAddress, int openingBracket, int closingBracket) throws JmqiException {
/* 3454 */       if (Trace.isOn) {
/* 3455 */         Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection$ConnectionSpecification", "checkStringFormat(String,int,int)", new Object[] { localAddress, 
/* 3456 */               Integer.valueOf(openingBracket), Integer.valueOf(closingBracket) });
/*      */       }
/*      */       
/* 3459 */       if (openingBracket == -1 || closingBracket == -1 || closingBracket != localAddress
/*      */         
/* 3461 */         .length() - 1 || 
/* 3462 */         JmqiTools.countOf(localAddress, '(') > 1 || 
/* 3463 */         JmqiTools.countOf(localAddress, ')') > 1) {
/*      */         
/* 3465 */         JmqiException traceRet = new JmqiException(RemoteTCPConnection.this.env, 9913, new String[] { "parse error", null, localAddress }, 2, 2538, null);
/*      */ 
/*      */ 
/*      */         
/* 3469 */         if (Trace.isOn) {
/* 3470 */           Trace.throwing(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection$ConnectionSpecification", "checkStringFormat(String,int,int)", (Throwable)traceRet, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 3475 */         throw traceRet;
/*      */       } 
/*      */       
/* 3478 */       if (Trace.isOn) {
/* 3479 */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTCPConnection$ConnectionSpecification", "checkStringFormat(String,int,int)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 3486 */       return String.format("host: %s portStart: %d portEnd: %d", new Object[] { this.localHostname, Integer.valueOf(this.portStart), Integer.valueOf(this.portEnd) });
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\impl\RemoteTCPConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */