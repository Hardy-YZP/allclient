/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiUtils;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Phconn;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ import javax.net.ssl.SSLSocketFactory;
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
/*     */ public class MQManagedConnectionJ11
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQManagedConnectionJ11.java";
/*     */   MQConnectionOptions cno;
/*     */   
/*     */   static {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQManagedConnectionJ11.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private volatile boolean connected = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private MQConnectionRequestInfo initialCxReqInf;
/*     */ 
/*     */ 
/*     */   
/*     */   JmqiConnectOptions jmqiCno;
/*     */ 
/*     */ 
/*     */   
/*  81 */   private JmqiEnvironment jmqiEnv = MQSESSION.getJmqiEnv();
/*     */ 
/*     */   
/*     */   private Phconn phconn;
/*     */ 
/*     */   
/*  87 */   private Hashtable<String, Object> properties = new Hashtable<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String qmgrName;
/*     */ 
/*     */ 
/*     */   
/*  96 */   Vector<MQQueueManager> qmgrs = new Vector<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private volatile MQSESSION session;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MQManagedConnectionJ11(String qmgrNameP, Hashtable<String, Object> properties, MQConnectionRequestInfo cxRequestInfo, MQManagedConnectionFactory mcf) throws MQException {
/* 117 */     if (Trace.isOn) {
/* 118 */       if (properties.containsKey("password")) {
/* 119 */         Hashtable<String, Object> propsNotPasswd = new Hashtable<>(properties);
/* 120 */         propsNotPasswd.put("password", "********");
/* 121 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "<init>(String,Hashtable,MQConnectionRequestInfo,MQManagedConnectionFactory)", new Object[] { qmgrNameP, propsNotPasswd, cxRequestInfo, mcf });
/*     */       }
/*     */       else {
/*     */         
/* 125 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "<init>(String,Hashtable,MQConnectionRequestInfo,MQManagedConnectionFactory)", new Object[] { qmgrNameP, properties, cxRequestInfo, mcf });
/*     */       } 
/*     */     }
/*     */     
/* 129 */     String qmgrName = qmgrNameP;
/*     */     
/* 131 */     this.phconn = this.jmqiEnv.newPhconn();
/* 132 */     Pint pCompCode = this.jmqiEnv.newPint();
/* 133 */     Pint pReason = this.jmqiEnv.newPint();
/*     */     
/* 135 */     if (qmgrName == null) {
/* 136 */       qmgrName = "";
/*     */     }
/* 138 */     this.qmgrName = qmgrName;
/*     */ 
/*     */ 
/*     */     
/* 142 */     if (properties == null || cxRequestInfo == null || mcf == null) {
/* 143 */       HashMap<String, Object> data = new HashMap<>();
/*     */ 
/*     */       
/* 146 */       if (properties != null && properties.containsKey("password")) {
/* 147 */         Hashtable<String, Object> propsNotPasswd = new Hashtable<>(properties);
/* 148 */         propsNotPasswd.put("password", "********");
/* 149 */         data.put("properties", propsNotPasswd);
/*     */       } else {
/*     */         
/* 152 */         data.put("properties", properties);
/*     */       } 
/* 154 */       data.put("cxRequestInfo", cxRequestInfo);
/* 155 */       data.put("mcf", mcf);
/*     */       
/* 157 */       Trace.ffst(this, "MQManagedConnectionJ11", "XO00E001", data, NullPointerException.class);
/*     */     } 
/*     */     
/* 160 */     this.initialCxReqInf = cxRequestInfo;
/*     */     
/* 162 */     this.properties = properties;
/* 163 */     this.connected = false;
/*     */ 
/*     */     
/* 166 */     this.session = MQSESSION.getSession(this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     String transport = getStringProperty("transport");
/*     */ 
/*     */     
/* 177 */     URL ccdtUrl = null;
/* 178 */     if (transport.equals("MQSeries Client")) {
/*     */       
/* 180 */       Object ccdtUrlObject = getProperty("CCDT URL");
/* 181 */       if (ccdtUrlObject != null && ccdtUrlObject instanceof URL)
/*     */       {
/* 183 */         ccdtUrl = (URL)ccdtUrlObject;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 188 */     this.cno = constructCNO(qmgrName, properties);
/* 189 */     this.jmqiCno = constructJmqiCNO(transport, ccdtUrl);
/*     */     
/* 191 */     this.session.MQCONNX_j(qmgrName, this.jmqiCno, this.cno, this.phconn, pCompCode, pReason);
/*     */     
/* 193 */     if (pCompCode.x != 0 || pReason.x != 0) {
/*     */       
/* 195 */       this.session = null;
/*     */ 
/*     */ 
/*     */       
/* 199 */       MQException me = new MQException(pCompCode.x, pReason.x, this);
/*     */       
/* 201 */       me.initCause((Throwable)this.jmqiEnv.getLastException());
/*     */       
/* 203 */       if (Trace.isOn)
/*     */       {
/* 205 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "<init>(String,Hashtable,MQConnectionRequestInfo,MQManagedConnectionFactory)", (Throwable)me, 2);
/*     */       }
/*     */       
/* 208 */       throw me;
/*     */     } 
/*     */     
/* 211 */     this.connected = true;
/*     */     
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "<init>(String,Hashtable,MQConnectionRequestInfo,MQManagedConnectionFactory)");
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
/*     */   protected boolean authenticate(Pint pCompCode, Pint pReason) throws MQException {
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "authenticate(Pint,Pint)", new Object[] { pCompCode, pReason });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 236 */       MQSESSION session = MQSESSION.getSession(this);
/* 237 */       if (Trace.isOn) {
/* 238 */         Trace.traceData(this, "Session associated with this managed connection is " + session, null);
/*     */       }
/*     */       
/* 241 */       String userId = getStringProperty("userID", "");
/* 242 */       String password = getStringProperty("password", "");
/*     */       
/* 244 */       pCompCode.x = 0;
/*     */       
/* 246 */       if (null != userId && !userId.trim().equals("")) {
/* 247 */         session.authenticate(this.phconn.getHconn(), userId, password, pCompCode, pReason);
/*     */       }
/*     */ 
/*     */       
/* 251 */       if (pCompCode.x == 2) {
/* 252 */         if (Trace.isOn) {
/* 253 */           Trace.traceData(this, "Failed to authenticate userId and Password: rc=" + pReason.x, null);
/*     */         }
/* 255 */         MQException me = new MQException(pCompCode.x, pReason.x, this);
/* 256 */         if (Trace.isOn) {
/* 257 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "authenticate(Pint,Pint)", (Throwable)me, 1);
/*     */         }
/*     */ 
/*     */         
/* 261 */         throw me;
/*     */       } 
/*     */       
/* 264 */       if (Trace.isOn) {
/* 265 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "authenticate(Pint,Pint)", 
/* 266 */             Boolean.valueOf(true));
/*     */       }
/* 268 */       return true;
/*     */     }
/* 270 */     catch (Exception e) {
/* 271 */       if (Trace.isOn) {
/* 272 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "authenticate(Pint,Pint)", e);
/*     */       }
/*     */ 
/*     */       
/* 276 */       if (e instanceof MQException) {
/* 277 */         MQException traceRet1 = (MQException)e;
/* 278 */         if (Trace.isOn) {
/* 279 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "authenticate(Pint,Pint)", (Throwable)traceRet1, 2);
/*     */         }
/*     */ 
/*     */         
/* 283 */         throw traceRet1;
/*     */       } 
/*     */       
/* 286 */       if (e instanceof MQResourceException && ((MQResourceException)e).getLinkedException() != null && ((MQResourceException)e)
/* 287 */         .getLinkedException() instanceof MQException) {
/* 288 */         MQException traceRet2 = (MQException)((MQResourceException)e).getLinkedException();
/* 289 */         if (Trace.isOn) {
/* 290 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "authenticate(Pint,Pint)", (Throwable)traceRet2, 3);
/*     */         }
/*     */ 
/*     */         
/* 294 */         throw traceRet2;
/*     */       } 
/*     */ 
/*     */       
/* 298 */       MQException mqe = new MQException(2, 2102, this);
/*     */       
/* 300 */       if (Trace.isOn) {
/* 301 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "authenticate(Pint,Pint)", (Throwable)mqe, 4);
/*     */       }
/*     */       
/* 304 */       throw mqe;
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
/*     */   private MQConnectionOptions constructCNO(String queueManagerName, Hashtable<String, Object> properties) throws MQException {
/* 317 */     if (Trace.isOn) {
/* 318 */       if (properties.containsKey("password")) {
/* 319 */         Hashtable<String, Object> propsNotPasswd = new Hashtable<>(properties);
/* 320 */         propsNotPasswd.put("password", "********");
/* 321 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "constructCNO(String,Hashtable<String,Object>)", new Object[] { queueManagerName, propsNotPasswd });
/*     */       }
/*     */       else {
/*     */         
/* 325 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "constructCNO(String,Hashtable<String,Object>)", new Object[] { queueManagerName, properties });
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 330 */     MQConnectionOptions mqcno = new MQConnectionOptions();
/*     */ 
/*     */     
/* 333 */     int options = getIntegerProperty("connectOptions");
/*     */     
/* 335 */     int check = options;
/*     */     
/* 337 */     String transport = getStringProperty("transport");
/*     */ 
/*     */     
/* 340 */     check &= 0xFFFFFC60;
/*     */     
/* 342 */     if (check != 0) {
/*     */       
/* 344 */       MQException traceRet1 = new MQException(2, 2046, this);
/* 345 */       if (Trace.isOn) {
/* 346 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "constructCNO(String,Hashtable<String,Object>)", (Throwable)traceRet1, 1);
/*     */       }
/* 348 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 354 */     if (transport.equals("MQSeries Client")) {
/* 355 */       if (options != 0) {
/* 356 */         check = options;
/* 357 */         check &= 0xFFFFFDFF;
/* 358 */         if (check == 0 && options != 0) {
/* 359 */           options -= 512;
/*     */         }
/*     */       } 
/* 362 */       if (options != 0) {
/* 363 */         check = options;
/* 364 */         check &= 0xFFFFFEFF;
/* 365 */         if (check == 0 && options != 0) {
/* 366 */           options -= 256;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 373 */       if ((options & 0x8) != 0 || (options & 0x10) != 0) {
/* 374 */         MQException mqe = new MQException(2, 2046, this);
/* 375 */         if (Trace.isOn) {
/* 376 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "constructCNO(String,Hashtable<String,Object>)", (Throwable)mqe, 2);
/*     */         }
/* 378 */         throw mqe;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 385 */     boolean sslFipsRequired = false;
/* 386 */     if (transport.equals("MQSeries Client")) {
/*     */       
/* 388 */       mqcno.setVersion(4);
/*     */       
/* 390 */       MQSSLConfigurationOptions sco = new MQSSLConfigurationOptions();
/*     */       
/* 392 */       sco.setVersion(2);
/*     */       
/* 394 */       mqcno.setMQSCO(sco);
/*     */       
/* 396 */       sslFipsRequired = ((Boolean)properties.get("SSL Fips Required")).booleanValue();
/* 397 */       sco.setFipsRequired(sslFipsRequired);
/*     */       
/* 399 */       int i = getIntegerProperty("KeyResetCount", 0);
/*     */ 
/*     */       
/* 402 */       if (i < 0 || i > 999999999) {
/* 403 */         MQException traceRet2 = new MQException(2, 2409, this);
/* 404 */         if (Trace.isOn) {
/* 405 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "constructCNO(String,Hashtable<String,Object>)", (Throwable)traceRet2, 3);
/*     */         }
/* 407 */         throw traceRet2;
/*     */       } 
/* 409 */       if (i > 0) {
/* 410 */         sco.setKeyResetCount(i);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 415 */     MQChannelDefinition clientConn = null;
/*     */     
/* 417 */     if (transport.equals("MQSeries Client")) {
/* 418 */       if (!properties.containsKey("CCDT URL")) {
/*     */         
/* 420 */         clientConn = new MQChannelDefinition();
/*     */         
/* 422 */         clientConn.channelName = (String)properties.get("channel");
/* 423 */         clientConn.queueManagerName = queueManagerName;
/* 424 */         clientConn.maxMessageLength = 104857600;
/*     */         
/* 426 */         StringBuffer connectionName = new StringBuffer();
/* 427 */         connectionName.append((String)properties.get("hostname"));
/* 428 */         Integer port = (Integer)properties.get("port");
/* 429 */         if (port != null) {
/* 430 */           connectionName.append("(");
/* 431 */           connectionName.append(port.toString());
/* 432 */           connectionName.append(")");
/*     */         } 
/* 434 */         clientConn.connectionName = connectionName.toString();
/*     */         
/* 436 */         clientConn.port = (port == null) ? 0 : port.intValue();
/* 437 */         clientConn.remoteUserId = (String)properties.get("userID");
/* 438 */         clientConn.remotePassword = (String)properties.get("password");
/* 439 */         clientConn.sslPeerName = (String)properties.get("SSL Peer Name");
/*     */         
/* 441 */         StringBuffer localAddress = new StringBuffer();
/*     */         
/* 443 */         Object object = properties.get("FIREWALL_SETTINGS");
/* 444 */         if (object != null) {
/* 445 */           if (object instanceof Hashtable) {
/* 446 */             Hashtable<String, Object> hashtable = (Hashtable<String, Object>)object;
/* 447 */             String address = (String)hashtable.get("HOST");
/* 448 */             String startPort = (String)hashtable.get("START_PORT");
/* 449 */             String endPort = (String)hashtable.get("END_PORT");
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 454 */             if (address != null) {
/* 455 */               localAddress.append(address);
/*     */             }
/* 457 */             if (startPort != null || endPort != null) {
/* 458 */               localAddress.append("(");
/* 459 */               if (startPort != null) {
/* 460 */                 localAddress.append(startPort);
/*     */               }
/* 462 */               if (endPort != null) {
/* 463 */                 localAddress.append(",");
/* 464 */                 localAddress.append(endPort);
/*     */               } 
/* 466 */               localAddress.append(")");
/*     */             } 
/*     */           } else {
/*     */             
/* 470 */             localAddress.append(object.toString());
/*     */           } 
/* 472 */           clientConn.localAddress = localAddress.toString();
/*     */         } 
/*     */ 
/*     */         
/* 476 */         String sslCipherSuite = getStringProperty("SSL Cipher Suite");
/*     */ 
/*     */ 
/*     */         
/* 480 */         if (sslCipherSuite != null) {
/* 481 */           String sslCipherSpec = JmqiUtils.toCipherSpec(sslCipherSuite, sslFipsRequired);
/* 482 */           if (sslCipherSpec != null) {
/* 483 */             clientConn.sslCipherSpec = sslCipherSpec;
/*     */           }
/*     */           else {
/*     */             
/* 487 */             throw new MQException(2, 2400, this);
/*     */           } 
/*     */         } 
/*     */         
/* 491 */         clientConn.securityUserData = "";
/* 492 */         clientConn.sendUserData = "";
/* 493 */         clientConn.receiveUserData = "";
/* 494 */         clientConn.hdrCompList = (Collection<Integer>)properties.get("Header Compression Property");
/* 495 */         clientConn.msgCompList = (Collection<Integer>)properties.get("ProviderMessage Compression Property");
/* 496 */         clientConn.sharingConversations = 0;
/*     */       } 
/* 498 */       mqcno.setMQCD(clientConn);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 503 */     Object obj = getProperty("ConnTag Property");
/* 504 */     boolean set = false;
/* 505 */     if (obj != null && obj instanceof byte[]) {
/* 506 */       byte[] b = (byte[])obj;
/* 507 */       for (int i = 0; i < b.length; i++) {
/* 508 */         if (b[i] != 0) {
/* 509 */           set = true;
/*     */         }
/*     */       } 
/* 512 */       if (set == true) {
/* 513 */         mqcno.setVersion(3);
/* 514 */         mqcno.setConnTag(b);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 521 */     String username = getStringProperty("userID", null);
/* 522 */     String password = getStringProperty("password", null);
/* 523 */     if (Trace.isOn) {
/* 524 */       Trace.traceData(this, "MQCSP UserName = " + username, null);
/*     */     }
/* 526 */     if (password != null && 
/* 527 */       Trace.isOn) {
/* 528 */       Trace.traceData(this, "MQCSP password length = " + password.length(), null);
/*     */     }
/*     */ 
/*     */     
/* 532 */     if (username != null && password != null && username.trim().length() > 0) {
/*     */       
/* 534 */       mqcno.setVersion(5);
/*     */       
/* 536 */       MQConnectionSecurityParameters csp = new MQConnectionSecurityParameters();
/* 537 */       csp.setCSPUserId(username);
/* 538 */       csp.setCSPPassword(password);
/* 539 */       csp.setAuthenticationType(1);
/*     */       
/* 541 */       mqcno.setMQCSP(csp);
/* 542 */       if (Trace.isOn) {
/* 543 */         Trace.traceData(this, " Constructed CSP AuthType " + csp.getAuthenticationType(), null);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 548 */     if (Trace.isOn) {
/* 549 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "constructCNO(String,Hashtable<String,Object>)", mqcno);
/*     */     }
/* 551 */     return mqcno;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JmqiConnectOptions constructJmqiCNO(String transport, URL ccdtUrl) throws MQException {
/* 560 */     if (Trace.isOn) {
/* 561 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "constructJmqiCNO(String,URL)", new Object[] { transport, ccdtUrl });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 566 */     JmqiSystemEnvironment jmqiSystemEnv = (JmqiSystemEnvironment)MQSESSION.getJmqiEnv();
/*     */     
/* 568 */     JmqiConnectOptions jmqiCNO = jmqiSystemEnv.newJmqiConnectOptions();
/*     */ 
/*     */     
/* 571 */     String userId = getStringProperty("userID");
/* 572 */     if (userId != null && userId.length() > 0) {
/* 573 */       jmqiCNO.setUserIdentifier(userId);
/*     */     }
/* 575 */     String password = getStringProperty("password");
/* 576 */     if (password != null && password.length() > 0) {
/* 577 */       jmqiCNO.setPassword(password);
/*     */     }
/*     */ 
/*     */     
/* 581 */     jmqiCNO.setApplicationName(getStringProperty("APPNAME"));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 586 */     jmqiCNO.setCcdtUrl(ccdtUrl);
/* 587 */     if (ccdtUrl != null) {
/* 588 */       jmqiCNO.setFlag(64);
/* 589 */       jmqiCNO.setSharingConversations(0);
/*     */     } 
/*     */     
/* 592 */     jmqiCNO.setFlag(256);
/*     */ 
/*     */     
/* 595 */     jmqiCNO.setSecurityExit(getProperty("securityExit"));
/* 596 */     jmqiCNO.setReceiveExits(getProperty("receiveExit"));
/* 597 */     jmqiCNO.setSendExits(getProperty("sendExit"));
/*     */     
/* 599 */     jmqiCNO.setSecurityExitUserData((String)getProperty("securitydExitData"));
/* 600 */     jmqiCNO.setReceiveExitsUserData((String)getProperty("receiveExitData"));
/* 601 */     jmqiCNO.setSendExitsUserData((String)getProperty("sendExitData"));
/*     */ 
/*     */     
/* 604 */     jmqiCNO.setCrlCertStores((Collection)getProperty("SSL CertStores"));
/*     */ 
/*     */     
/* 607 */     jmqiCNO.setSslSocketFactory((SSLSocketFactory)getProperty("SSL Socket Factory"));
/*     */ 
/*     */     
/* 610 */     int ccsid = getIntegerProperty("CCSID");
/* 611 */     if (ccsid > 0) {
/* 612 */       jmqiCNO.setQueueManagerCCSID(ccsid);
/*     */     }
/* 614 */     if (Trace.isOn) {
/* 615 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "constructJmqiCNO(String,URL)", jmqiCNO);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 623 */     jmqiCNO.setFapLevel(13);
/*     */     
/* 625 */     return jmqiCNO;
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
/*     */   public synchronized void destroy() throws MQResourceException, MQException {
/* 640 */     if (Trace.isOn) {
/* 641 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "destroy()");
/*     */     }
/*     */ 
/*     */     
/* 645 */     Pint pCompCode = this.jmqiEnv.newPint();
/* 646 */     Pint pReason = this.jmqiEnv.newPint();
/*     */ 
/*     */     
/* 649 */     Enumeration<MQQueueManager> e = this.qmgrs.elements();
/* 650 */     while (e.hasMoreElements()) {
/* 651 */       MQQueueManager qm = e.nextElement();
/* 652 */       synchronized (qm) {
/* 653 */         if (qm.isConnected()) {
/* 654 */           qm.cleanup();
/*     */         }
/*     */       } 
/*     */     } 
/* 658 */     this.qmgrs.removeAllElements();
/*     */     
/* 660 */     if (this.connected) {
/* 661 */       this.session.MQDISC(this.phconn, pCompCode, pReason);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 666 */       String msg = MQException.getNLSMsg("MQJI040");
/* 667 */       MQResourceException traceRet1 = new MQResourceException(msg);
/* 668 */       if (Trace.isOn) {
/* 669 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "destroy()", traceRet1, 1);
/*     */       }
/*     */       
/* 672 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 677 */     this.connected = false;
/* 678 */     this.session = null;
/*     */     
/* 680 */     if (pCompCode.x != 0 || pReason.x != 0) {
/* 681 */       MQException mqe = new MQException(pCompCode.x, pReason.x, this);
/*     */       
/* 683 */       if (Trace.isOn) {
/* 684 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "destroy()", (Throwable)mqe, 2);
/*     */       }
/*     */       
/* 687 */       throw mqe;
/*     */     } 
/*     */     
/* 690 */     if (Trace.isOn) {
/* 691 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "destroy()");
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
/*     */   void fireConnectionClosedEvent(MQQueueManager qmgr) throws MQException {
/* 704 */     if (Trace.isOn) {
/* 705 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "fireConnectionClosedEvent(MQQueueManager)", new Object[] { qmgr });
/*     */     }
/*     */     
/* 708 */     this.qmgrs.removeElement(qmgr);
/*     */     
/*     */     try {
/* 711 */       destroy();
/*     */     }
/* 713 */     catch (MQResourceException mqre) {
/* 714 */       if (Trace.isOn) {
/* 715 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "fireConnectionClosedEvent(MQQueueManager)", mqre);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 720 */       HashMap<String, Object> data = new HashMap<>();
/* 721 */       data.put("exception:", mqre);
/* 722 */       Trace.ffst(this, "fireConnectionClosedEvent", "XO00E002", data, null);
/*     */     } 
/*     */     
/* 725 */     if (Trace.isOn) {
/* 726 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "fireConnectionClosedEvent(MQQueueManager)");
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
/*     */   public synchronized Object getConnection(MQConnectionRequestInfo cxRequestInfo) throws MQResourceException, MQException {
/* 748 */     if (Trace.isOn) {
/* 749 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "getConnection(MQConnectionRequestInfo)", new Object[] { cxRequestInfo });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 756 */     if (!this.connected) {
/* 757 */       String msg = MQException.getNLSMsg("MQJI040");
/* 758 */       MQResourceException re = new MQResourceException(msg);
/* 759 */       if (Trace.isOn) {
/* 760 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "getConnection(MQConnectionRequestInfo)", re);
/*     */       }
/*     */       
/* 763 */       throw re;
/*     */     } 
/*     */ 
/*     */     
/* 767 */     MQQueueManager qm = new MQQueueManager(this);
/*     */ 
/*     */     
/* 770 */     this.qmgrs.addElement(qm);
/*     */     
/* 772 */     if (Trace.isOn) {
/* 773 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "getConnection(MQConnectionRequestInfo)", qm);
/*     */     }
/*     */     
/* 776 */     return qm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Hconn getHconn() {
/* 786 */     Hconn traceRet1 = this.phconn.getHconn();
/* 787 */     if (Trace.isOn) {
/* 788 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "getHconn()", "getter", traceRet1);
/*     */     }
/*     */     
/* 791 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getIntegerProperty(Object key) {
/* 800 */     if (Trace.isOn) {
/* 801 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "getIntegerProperty(Object)", new Object[] { key });
/*     */     }
/*     */     
/* 804 */     int traceRet1 = getIntegerProperty(key, 0);
/* 805 */     if (Trace.isOn) {
/* 806 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "getIntegerProperty(Object)", 
/* 807 */           Integer.valueOf(traceRet1));
/*     */     }
/* 809 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getIntegerProperty(Object key, int defaultValue) {
/*     */     int value;
/* 819 */     if (Trace.isOn) {
/* 820 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "getIntegerProperty(Object,int)", new Object[] { key, 
/* 821 */             Integer.valueOf(defaultValue) });
/*     */     }
/*     */     
/* 824 */     Object object = getProperty(key);
/*     */     
/* 826 */     if (object != null && object instanceof Integer) {
/* 827 */       value = ((Integer)object).intValue();
/*     */     } else {
/*     */       
/* 830 */       value = defaultValue;
/*     */     } 
/*     */     
/* 833 */     if (Trace.isOn) {
/* 834 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "getIntegerProperty(Object,int)", 
/* 835 */           Integer.valueOf(value));
/*     */     }
/* 837 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQSESSION getMQSESSION() {
/* 847 */     if (Trace.isOn) {
/* 848 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "getMQSESSION()", "getter", this.session);
/*     */     }
/*     */     
/* 851 */     return this.session;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Object getProperty(Object key) {
/* 861 */     return this.properties.get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getQmgrName() {
/* 868 */     if (Trace.isOn) {
/* 869 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "getQmgrName()", "getter", this.qmgrName);
/*     */     }
/*     */     
/* 872 */     return this.qmgrName;
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
/*     */   public String getStringProperty(Object key) {
/* 884 */     return getStringProperty(key, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getStringProperty(Object key, String defaultValue) {
/*     */     String value;
/* 895 */     if (Trace.isOn) {
/* 896 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "getStringProperty(Object,String)", new Object[] { key, defaultValue });
/*     */     }
/*     */     
/* 899 */     Object object = getProperty(key);
/*     */     
/* 901 */     if (object != null && object instanceof String) {
/* 902 */       value = (String)object;
/*     */     } else {
/*     */       
/* 905 */       value = defaultValue;
/*     */     } 
/*     */     
/* 908 */     if (Trace.isOn) {
/* 909 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "getStringProperty(Object,String)", (key == "password") ? "********" : value);
/*     */     }
/*     */     
/* 912 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isConnected() {
/* 917 */     if (Trace.isOn) {
/* 918 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "isConnected()", "getter", 
/* 919 */           Boolean.valueOf(this.connected));
/*     */     }
/* 921 */     return this.connected;
/*     */   }
/*     */ 
/*     */   
/*     */   MQConnectionRequestInfo getInitialCxReqInf() {
/* 926 */     if (Trace.isOn) {
/* 927 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQManagedConnectionJ11", "getInitialCxReqInf()", "getter", this.initialCxReqInf);
/*     */     }
/*     */     
/* 930 */     return this.initialCxReqInf;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQManagedConnectionJ11.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */