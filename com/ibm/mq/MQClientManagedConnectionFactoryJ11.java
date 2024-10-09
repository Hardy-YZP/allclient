/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ import java.io.NotSerializableException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Hashtable;
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
/*     */ 
/*     */ 
/*     */ class MQClientManagedConnectionFactoryJ11
/*     */   extends JmqiObject
/*     */   implements MQManagedConnectionFactory
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQClientManagedConnectionFactoryJ11.java";
/*     */   static final long serialVersionUID = -1400003784925523083L;
/*     */   
/*     */   static {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.data("com.ibm.mq.MQClientManagedConnectionFactoryJ11", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQClientManagedConnectionFactoryJ11.java");
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
/*  74 */   private String fieldQMGR = "";
/*  75 */   private String fieldServerName = "";
/*  76 */   private int fieldPortNumber = 1414;
/*  77 */   private String fieldChannel = "";
/*  78 */   private int fieldCCSID = 819;
/*     */ 
/*     */   
/*  81 */   PrintWriter logWriter = null;
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
/*     */   MQClientManagedConnectionFactoryJ11(String qmgr, Hashtable props) {
/*  96 */     super(MQSESSION.getJmqiEnv());
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.entry(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "<init>(String,Hashtable)", new Object[] { qmgr, 
/*  99 */             Trace.sanitizeMap(props, "password", "XXXXXXXXXXXX") });
/*     */     }
/* 101 */     setQMGR(qmgr);
/*     */ 
/*     */ 
/*     */     
/* 105 */     String svrName = MQEnvironment.getStringProperty("hostname", props);
/* 106 */     setServerName((svrName != null) ? svrName : "localhost");
/* 107 */     setPortNumber(MQEnvironment.getIntegerProperty("port", props));
/* 108 */     setChannel(MQEnvironment.getStringProperty("channel", props));
/* 109 */     setCCSID(MQEnvironment.getIntegerProperty("CCSID", props));
/*     */     
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.exit(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "<init>(String,Hashtable)");
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
/*     */   public void setQMGR(String value) {
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.data(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "setQMGR(String)", "setter", value);
/*     */     }
/*     */     
/* 133 */     this.fieldQMGR = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQMGR() {
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.data(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "getQMGR()", "getter", this.fieldQMGR);
/*     */     }
/*     */     
/* 147 */     return this.fieldQMGR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServerName(String value) {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.data(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "setServerName(String)", "setter", value);
/*     */     }
/*     */     
/* 161 */     this.fieldServerName = value.trim();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getServerName() {
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.data(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "getServerName()", "getter", this.fieldServerName);
/*     */     }
/*     */     
/* 175 */     return this.fieldServerName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPortNumber(int value) {
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.data(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "setPortNumber(int)", "setter", 
/* 187 */           Integer.valueOf(value));
/*     */     }
/* 189 */     this.fieldPortNumber = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPortNumber() {
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.data(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "getPortNumber()", "getter", 
/* 201 */           Integer.valueOf(this.fieldPortNumber));
/*     */     }
/* 203 */     return this.fieldPortNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChannel(String value) {
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.data(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "setChannel(String)", "setter", value);
/*     */     }
/*     */     
/* 217 */     this.fieldChannel = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getChannel() {
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.data(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "getChannel()", "getter", this.fieldChannel);
/*     */     }
/*     */     
/* 231 */     return this.fieldChannel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCCSID(int value) {
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.data(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "setCCSID(int)", "setter", 
/* 243 */           Integer.valueOf(value));
/*     */     }
/* 245 */     this.fieldCCSID = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCCSID() {
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.data(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "getCCSID()", "getter", 
/* 257 */           Integer.valueOf(this.fieldCCSID));
/*     */     }
/* 259 */     return this.fieldCCSID;
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
/*     */   MQManagedConnectionJ11 _createManagedConnection(MQConnectionRequestInfo cxRequestInfo, boolean mode) throws MQResourceException {
/*     */     MQManagedConnectionJ11 mancon;
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.entry(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "_createManagedConnection(MQConnectionRequestInfo,boolean)", new Object[] { cxRequestInfo, 
/*     */             
/* 280 */             Boolean.valueOf(mode) });
/*     */     }
/*     */     
/* 283 */     if (!(cxRequestInfo instanceof ClientConnectionRequestInfo)) {
/* 284 */       MQResourceException traceRet1 = new MQResourceException(2, 2043, this, "MQJI039");
/*     */       
/* 286 */       if (Trace.isOn) {
/* 287 */         Trace.throwing(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "_createManagedConnection(MQConnectionRequestInfo,boolean)", traceRet1, 1);
/*     */       }
/*     */       
/* 290 */       throw traceRet1;
/*     */     } 
/*     */     
/* 293 */     ClientConnectionRequestInfo cCxReqInf = (ClientConnectionRequestInfo)cxRequestInfo;
/*     */ 
/*     */ 
/*     */     
/* 297 */     Hashtable<Object, Object> properties = new Hashtable<>();
/* 298 */     properties.put("transport", "MQSeries Client");
/* 299 */     properties.put("hostname", getServerName());
/* 300 */     properties.put("port", Integer.valueOf(getPortNumber()));
/* 301 */     properties.put("channel", getChannel());
/* 302 */     properties.put("CCSID", Integer.valueOf(getCCSID()));
/*     */     
/* 304 */     if (cCxReqInf.appName != null) {
/* 305 */       properties.put("APPNAME", cCxReqInf.appName);
/*     */     }
/*     */     
/* 308 */     if (cCxReqInf.useMQCSP != null) {
/* 309 */       properties.put("Use MQCSP authentication", cCxReqInf.useMQCSP);
/*     */     }
/*     */     
/* 312 */     if (cCxReqInf.ccdtUrl != null) {
/* 313 */       properties.put("CCDT URL", cCxReqInf.ccdtUrl);
/*     */     }
/*     */     
/* 316 */     properties.put("connectOptions", Integer.valueOf(cCxReqInf.connectOptions));
/* 317 */     properties.put("sharingConversations", Integer.valueOf(cCxReqInf.sharingConversations));
/* 318 */     if (cCxReqInf.securityExit != null) {
/* 319 */       properties.put("channelSecurityExit", cCxReqInf.securityExit);
/*     */     } else {
/* 321 */       properties.remove("channelSecurityExit");
/*     */     } 
/*     */     
/* 324 */     if (cCxReqInf.securityExitUserData != null) {
/* 325 */       properties.put("channelSecurityExitUserData", cCxReqInf.securityExitUserData);
/*     */     } else {
/* 327 */       properties.remove("channelSecurityExitUserData");
/*     */     } 
/*     */     
/* 330 */     if (cCxReqInf.sendExit != null) {
/* 331 */       properties.put("channelSendExit", cCxReqInf.sendExit);
/*     */     } else {
/* 333 */       properties.remove("channelSendExit");
/*     */     } 
/*     */     
/* 336 */     if (cCxReqInf.sendExitUserData != null) {
/* 337 */       properties.put("channelSendExitUserData", cCxReqInf.sendExitUserData);
/*     */     } else {
/* 339 */       properties.remove("channelSendExitUserData");
/*     */     } 
/*     */     
/* 342 */     if (cCxReqInf.receiveExit != null) {
/* 343 */       properties.put("channelReceiveExit", cCxReqInf.receiveExit);
/*     */     } else {
/* 345 */       properties.remove("channelReceiveExit");
/*     */     } 
/*     */     
/* 348 */     if (cCxReqInf.receiveExitUserData != null) {
/* 349 */       properties.put("channelReceiveExitUserData", cCxReqInf.receiveExitUserData);
/*     */     } else {
/* 351 */       properties.remove("channelReceiveExitUserData");
/*     */     } 
/*     */     
/* 354 */     if (cCxReqInf.exitClasspath != null) {
/* 355 */       properties.put("exitClasspath", cCxReqInf.exitClasspath);
/*     */     } else {
/* 357 */       properties.remove("exitClasspath");
/*     */     } 
/*     */     
/* 360 */     if (cCxReqInf.userName != null) {
/* 361 */       properties.put("userID", cCxReqInf.userName);
/*     */     }
/*     */     
/* 364 */     if (cCxReqInf.password != null) {
/* 365 */       properties.put("password", cCxReqInf.password);
/*     */     }
/*     */ 
/*     */     
/* 369 */     if (cCxReqInf.xaRequired) {
/* 370 */       properties.put("XAReq", Boolean.valueOf(true));
/*     */     }
/*     */ 
/*     */     
/* 374 */     if (cCxReqInf.useQmCcsid) {
/* 375 */       properties.put("Use QM CCSID", Boolean.valueOf(true));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 380 */     if (cCxReqInf.sslCipherSuite != null) {
/* 381 */       properties.put("SSL Cipher Suite", cCxReqInf.sslCipherSuite);
/*     */     } else {
/* 383 */       properties.remove("SSL Cipher Suite");
/*     */     } 
/*     */     
/* 386 */     if (cCxReqInf.sslPeername != null) {
/* 387 */       properties.put("SSL Peer Name", cCxReqInf.sslPeername);
/*     */     } else {
/* 389 */       properties.remove("SSL Peer Name");
/*     */     } 
/*     */     
/* 392 */     if (cCxReqInf.sslCertStores != null) {
/* 393 */       properties.put("SSL CertStores", cCxReqInf.sslCertStores);
/*     */     } else {
/* 395 */       properties.remove("SSL CertStores");
/*     */     } 
/*     */     
/* 398 */     if (cCxReqInf.sslSocketFactory != null) {
/* 399 */       properties.put("SSL Socket Factory", cCxReqInf.sslSocketFactory);
/*     */     } else {
/* 401 */       properties.remove("SSL Socket Factory");
/*     */     } 
/*     */     
/* 404 */     if (cCxReqInf.sslResetCount != null) {
/* 405 */       properties.put("KeyResetCount", cCxReqInf.sslResetCount);
/*     */     } else {
/* 407 */       properties.remove("KeyResetCount");
/*     */     } 
/*     */     
/* 410 */     if (cCxReqInf.sslFipsRequired != null) {
/* 411 */       properties.put("SSL Fips Required", cCxReqInf.sslFipsRequired);
/*     */     } else {
/*     */       
/* 414 */       properties.remove("SSL Fips Required");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 421 */     if (cCxReqInf.localAddress != null) {
/* 422 */       properties.put("Local Address Property", cCxReqInf.localAddress);
/*     */     }
/*     */     
/* 425 */     if (cCxReqInf.hdrCompList != null) {
/* 426 */       properties.put("Header Compression Property", cCxReqInf.hdrCompList);
/*     */     } else {
/* 428 */       properties.remove("Header Compression Property");
/*     */     } 
/*     */     
/* 431 */     if (cCxReqInf.msgCompList != null) {
/* 432 */       properties.put("Message Compression Property", cCxReqInf.msgCompList);
/*     */     } else {
/* 434 */       properties.remove("Message Compression Property");
/*     */     } 
/*     */     
/* 437 */     if (cCxReqInf.connTag != null) {
/* 438 */       properties.put("ConnTag Property", cCxReqInf.connTag);
/*     */     } else {
/* 440 */       properties.remove("ConnTag Property");
/*     */     } 
/*     */     
/* 443 */     if (cCxReqInf.jmqiFlags != 0) {
/* 444 */       properties.put("JMQI FLAGS", Integer.valueOf(cCxReqInf.jmqiFlags));
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 449 */       mancon = new MQManagedConnectionJ11(this.fieldQMGR, properties, cxRequestInfo, this);
/*     */     }
/* 451 */     catch (MQException mqe) {
/* 452 */       if (Trace.isOn) {
/* 453 */         Trace.catchBlock(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "_createManagedConnection(MQConnectionRequestInfo,boolean)", mqe);
/*     */       }
/*     */       
/* 456 */       MQResourceException re = ReasonCodeInfo.getResourceException(mqe);
/* 457 */       if (Trace.isOn) {
/* 458 */         Trace.throwing(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "_createManagedConnection(MQConnectionRequestInfo,boolean)", re, 2);
/*     */       }
/*     */       
/* 461 */       throw re;
/*     */     } 
/* 463 */     if (Trace.isOn) {
/* 464 */       Trace.exit(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "_createManagedConnection(MQConnectionRequestInfo,boolean)", mancon);
/*     */     }
/*     */     
/* 467 */     return mancon;
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
/*     */   public MQManagedConnectionJ11 createManagedConnection(MQConnectionRequestInfo cxRequestInfo) throws MQResourceException {
/* 482 */     if (Trace.isOn) {
/* 483 */       Trace.entry(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "createManagedConnection(MQConnectionRequestInfo)", new Object[] { cxRequestInfo });
/*     */     }
/*     */     
/* 486 */     MQManagedConnectionJ11 traceRet1 = _createManagedConnection(cxRequestInfo, true);
/*     */     
/* 488 */     if (Trace.isOn) {
/* 489 */       Trace.exit(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "createManagedConnection(MQConnectionRequestInfo)", traceRet1);
/*     */     }
/*     */     
/* 492 */     return traceRet1;
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
/*     */   public int hashCode() {
/* 505 */     if (Trace.isOn) {
/* 506 */       Trace.entry(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "hashCode()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 514 */     int traceRet1 = getClass().hashCode();
/*     */     
/* 516 */     if (Trace.isOn) {
/* 517 */       Trace.exit(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "hashCode()", 
/* 518 */           Integer.valueOf(traceRet1));
/*     */     }
/* 520 */     return traceRet1;
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
/*     */   public boolean equals(Object obj) {
/* 534 */     if (Trace.isOn) {
/* 535 */       Trace.entry(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "equals(Object)", new Object[] { obj });
/*     */     }
/*     */     
/* 538 */     if (this == obj) {
/*     */       
/* 540 */       if (Trace.isOn) {
/* 541 */         Trace.exit(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "equals(Object)", 
/* 542 */             Boolean.valueOf(true), 1);
/*     */       }
/* 544 */       return true;
/*     */     } 
/*     */     
/* 547 */     if (obj == null) {
/* 548 */       if (Trace.isOn) {
/* 549 */         Trace.exit(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "equals(Object)", 
/* 550 */             Boolean.valueOf(false), 2);
/*     */       }
/* 552 */       return false;
/*     */     } 
/*     */     
/* 555 */     if (!obj.getClass().equals(getClass())) {
/* 556 */       if (Trace.isOn) {
/* 557 */         Trace.exit(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "equals(Object)", 
/* 558 */             Boolean.valueOf(false), 3);
/*     */       }
/* 560 */       return false;
/*     */     } 
/*     */     
/* 563 */     MQClientManagedConnectionFactoryJ11 other = (MQClientManagedConnectionFactoryJ11)obj;
/*     */ 
/*     */     
/* 566 */     if (twoStringsEqual(this.fieldQMGR, other.fieldQMGR) && this.fieldServerName.equals(other.fieldServerName) && this.fieldPortNumber == other.fieldPortNumber && this.fieldChannel
/* 567 */       .equals(other.fieldChannel) && this.fieldCCSID == other.fieldCCSID) {
/* 568 */       if (Trace.isOn) {
/* 569 */         Trace.exit(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "equals(Object)", 
/* 570 */             Boolean.valueOf(true), 4);
/*     */       }
/* 572 */       return true;
/*     */     } 
/*     */     
/* 575 */     if (Trace.isOn) {
/* 576 */       Trace.exit(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "equals(Object)", 
/* 577 */           Boolean.valueOf(false), 5);
/*     */     }
/* 579 */     return false;
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
/*     */   public Object clone() {
/* 591 */     if (Trace.isOn) {
/* 592 */       Trace.entry(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "clone()");
/*     */     }
/*     */     try {
/* 595 */       Object traceRet1 = super.clone();
/*     */       
/* 597 */       if (Trace.isOn) {
/* 598 */         Trace.exit(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "clone()", traceRet1);
/*     */       }
/* 600 */       return traceRet1;
/*     */     }
/* 602 */     catch (CloneNotSupportedException e) {
/* 603 */       if (Trace.isOn) {
/* 604 */         Trace.catchBlock(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "clone()", e);
/*     */       }
/*     */       
/* 607 */       IllegalAccessError traceRet2 = new IllegalAccessError();
/* 608 */       if (Trace.isOn) {
/* 609 */         Trace.throwing(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "clone()", traceRet2);
/*     */       }
/*     */       
/* 612 */       throw traceRet2;
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
/*     */   static boolean twoStringsEqual(String a, String b) {
/*     */     boolean result;
/* 627 */     if (Trace.isOn) {
/* 628 */       Trace.entry("com.ibm.mq.MQClientManagedConnectionFactoryJ11", "twoStringsEqual(String,String)", new Object[] { a, b });
/*     */     }
/*     */ 
/*     */     
/* 632 */     if (a == null && b == null) {
/* 633 */       result = true;
/* 634 */     } else if (a == null || b == null) {
/* 635 */       result = false;
/*     */     } else {
/* 637 */       result = a.equals(b);
/*     */     } 
/* 639 */     if (Trace.isOn) {
/* 640 */       Trace.exit("com.ibm.mq.MQClientManagedConnectionFactoryJ11", "twoStringsEqual(String,String)", 
/* 641 */           Boolean.valueOf(result));
/*     */     }
/* 643 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 651 */     if (Trace.isOn) {
/* 652 */       Trace.entry(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "writeObject(java.io.ObjectOutputStream)", new Object[] { out });
/*     */     }
/*     */     
/* 655 */     NotSerializableException traceRet1 = new NotSerializableException();
/*     */     
/* 657 */     if (Trace.isOn) {
/* 658 */       Trace.throwing(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "writeObject(java.io.ObjectOutputStream)", traceRet1);
/*     */     }
/*     */     
/* 661 */     throw traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 669 */     if (Trace.isOn) {
/* 670 */       Trace.entry(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */     
/* 673 */     NotSerializableException traceRet1 = new NotSerializableException();
/*     */     
/* 675 */     if (Trace.isOn) {
/* 676 */       Trace.throwing(this, "com.ibm.mq.MQClientManagedConnectionFactoryJ11", "readObject(java.io.ObjectInputStream)", traceRet1);
/*     */     }
/*     */     
/* 679 */     throw traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQClientManagedConnectionFactoryJ11.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */