/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.internal.Configuration;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.net.URL;
/*     */ import java.util.Arrays;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
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
/*     */ class ClientConnectionRequestInfo
/*     */   extends MQConnectionRequestInfo
/*     */   implements Cloneable
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/ClientConnectionRequestInfo.java";
/*     */   public int connectOptions;
/*     */   public Object securityExit;
/*     */   public String securityExitUserData;
/*     */   public Object sendExit;
/*     */   public String sendExitUserData;
/*     */   public Object receiveExit;
/*     */   public String receiveExitUserData;
/*     */   public Object exitClasspath;
/*     */   public String userName;
/*     */   public String password;
/*     */   public boolean xaRequired;
/*     */   public boolean useQmCcsid;
/*     */   
/*     */   static {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.data("com.ibm.mq.ClientConnectionRequestInfo", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/ClientConnectionRequestInfo.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public String sslCipherSuite = null;
/*     */ 
/*     */   
/* 134 */   public String sslPeername = null;
/*     */ 
/*     */   
/* 137 */   public Object sslCertStores = null;
/*     */ 
/*     */   
/* 140 */   public Object sslSocketFactory = null;
/*     */ 
/*     */   
/* 143 */   public Object hdrCompList = null;
/*     */ 
/*     */   
/* 146 */   public Object msgCompList = null;
/*     */ 
/*     */   
/* 149 */   public Integer sslResetCount = null;
/*     */ 
/*     */   
/* 152 */   public Boolean sslFipsRequired = null;
/*     */ 
/*     */   
/* 155 */   public String localAddress = "";
/*     */ 
/*     */   
/* 158 */   public byte[] connTag = null;
/*     */ 
/*     */   
/* 161 */   public String appName = null;
/*     */ 
/*     */   
/* 164 */   public Boolean useMQCSP = null;
/*     */ 
/*     */   
/* 167 */   public URL ccdtUrl = null;
/*     */ 
/*     */   
/* 170 */   public int sharingConversations = 10;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int jmqiFlags;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ClientConnectionRequestInfo(Hashtable<String, Object> props, URL ccdtUrl) throws MQException {
/* 189 */     if (Trace.isOn)
/* 190 */       Trace.entry(this, "com.ibm.mq.ClientConnectionRequestInfo", "<init>(Hashtable,URL)", new Object[] {
/* 191 */             Trace.sanitizeMap(props, "password", "XXXXXXXXXXXX"), ccdtUrl
/*     */           }); 
/* 193 */     this.ccdtUrl = ccdtUrl;
/* 194 */     complexPopulate(props, ccdtUrl);
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.exit(this, "com.ibm.mq.ClientConnectionRequestInfo", "<init>(Hashtable,URL)");
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
/*     */   private void complexPopulate(Hashtable<String, Object> props, URL ccdtUrl) throws MQException {
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.entry(this, "com.ibm.mq.ClientConnectionRequestInfo", "complexPopulate(Hashtable<String , Object>,URL)", new Object[] {
/* 216 */             Trace.sanitizeMap(props, "password", "XXXXXXXXXXXX"), ccdtUrl
/*     */           });
/*     */     }
/*     */     
/* 220 */     this.hasVariablePortion = true;
/*     */     
/* 222 */     this.appName = MQEnvironment.getStringProperty("APPNAME", props);
/* 223 */     this.useMQCSP = (Boolean)MQEnvironment.getObjectProperty("Use MQCSP authentication", props);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 228 */     this.connectOptions = MQEnvironment.getIntegerProperty("connectOptions", props);
/* 229 */     if (ccdtUrl == null) {
/*     */ 
/*     */       
/* 232 */       if (MQEnvironment.getObjectProperty("channelSecurityExit", props) != null) {
/* 233 */         this.securityExit = MQEnvironment.getObjectProperty("channelSecurityExit", props);
/* 234 */         this.securityExitUserData = (String)MQEnvironment.getObjectProperty("channelSecurityExitUserData", props);
/*     */       } else {
/* 236 */         this.securityExit = MQEnvironment.getObjectProperty("securityExit", props);
/* 237 */         this.securityExitUserData = (String)MQEnvironment.getObjectProperty("securityExitUserData", props);
/*     */       } 
/* 239 */       if (MQEnvironment.getObjectProperty("channelReceiveExit", props) != null) {
/* 240 */         this.receiveExit = MQEnvironment.getObjectProperty("channelReceiveExit", props);
/* 241 */         this.receiveExitUserData = (String)MQEnvironment.getObjectProperty("channelReceiveExitUserData", props);
/*     */       } else {
/* 243 */         this.receiveExit = MQEnvironment.getObjectProperty("receiveExit", props);
/* 244 */         this.receiveExitUserData = (String)MQEnvironment.getObjectProperty("receiveExitUserData", props);
/*     */       } 
/* 246 */       if (MQEnvironment.getObjectProperty("channelSendExit", props) != null) {
/* 247 */         this.sendExit = MQEnvironment.getObjectProperty("channelSendExit", props);
/* 248 */         this.sendExitUserData = (String)MQEnvironment.getObjectProperty("channelSendExitUserData", props);
/*     */       } else {
/* 250 */         this.sendExit = MQEnvironment.getObjectProperty("sendExit", props);
/* 251 */         this.sendExitUserData = (String)MQEnvironment.getObjectProperty("sendExitUserData", props);
/*     */       } 
/* 253 */       this.exitClasspath = MQEnvironment.getObjectProperty("exitClasspath", props);
/* 254 */       this.sslCipherSuite = MQEnvironment.getStringProperty("SSL Cipher Suite", props);
/* 255 */       this.sslPeername = MQEnvironment.getStringProperty("SSL Peer Name", props);
/* 256 */       this.hdrCompList = MQEnvironment.getObjectProperty("Header Compression Property", props);
/* 257 */       this.msgCompList = MQEnvironment.getObjectProperty("Message Compression Property", props);
/* 258 */       this.connTag = (byte[])MQEnvironment.getObjectProperty("ConnTag Property", props);
/* 259 */       this.localAddress = MQEnvironment.getStringProperty("Local Address Property", props);
/* 260 */       this.sharingConversations = MQEnvironment.getIntegerProperty("sharingConversations", props);
/*     */     } 
/* 262 */     this.userName = MQEnvironment.getStringProperty("userID", props);
/* 263 */     this.password = MQEnvironment.getStringProperty("password", props);
/* 264 */     this.sslCertStores = MQEnvironment.getObjectProperty("SSL CertStores", props);
/* 265 */     if (this.sslCertStores instanceof Vector) {
/* 266 */       this.sslCertStores = ((Vector)this.sslCertStores).clone();
/*     */     }
/* 268 */     this.sslSocketFactory = MQEnvironment.getObjectProperty("SSL Socket Factory", props);
/* 269 */     this.sslResetCount = (Integer)MQEnvironment.getObjectProperty("KeyResetCount", props);
/* 270 */     this.sslFipsRequired = (Boolean)MQEnvironment.getObjectProperty("SSL Fips Required", props);
/*     */ 
/*     */     
/* 273 */     if (props != null) {
/* 274 */       Object jmqiFlagsObj = props.get("JMQI FLAGS");
/* 275 */       if (jmqiFlagsObj != null && jmqiFlagsObj instanceof Integer) {
/* 276 */         this.jmqiFlags = ((Integer)jmqiFlagsObj).intValue();
/*     */       }
/*     */     } 
/*     */     
/* 280 */     setClassFields(props);
/*     */     
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.exit(this, "com.ibm.mq.ClientConnectionRequestInfo", "complexPopulate(Hashtable<String , Object>,URL)");
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
/*     */   private void setClassFields(Hashtable<String, Object> props) throws MQException {
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.data(this, "com.ibm.mq.ClientConnectionRequestInfo", "setClassFields(Hashtable<String , Object>)", "setter", 
/* 298 */           Trace.sanitizeMap(props, "password", "XXXXXXXXXXXX"));
/*     */     }
/* 300 */     if (props != null) {
/* 301 */       this.xaRequired = false;
/* 302 */       Object xar = props.get("XAReq");
/* 303 */       if (xar != null && 
/* 304 */         xar instanceof Boolean) {
/* 305 */         this.xaRequired = ((Boolean)xar).booleanValue();
/*     */       }
/*     */       
/* 308 */       this.useQmCcsid = false;
/*     */       
/* 310 */       Object object = props.get("Use QM CCSID");
/* 311 */       if (object != null && object instanceof Boolean) {
/* 312 */         this.useQmCcsid = ((Boolean)object).booleanValue();
/*     */       }
/*     */     } 
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
/* 338 */     Configuration config = this.env.getConfiguration();
/* 339 */     boolean useConfigMQCSP = config.getBoolValue(Configuration.USE_MQCSP_AUTHENTICATION);
/* 340 */     boolean defaultConfigMQCSP = config.wasDefaulted((Configuration.CfgProperty)Configuration.USE_MQCSP_AUTHENTICATION);
/*     */     
/* 342 */     if (Trace.isOn) {
/* 343 */       Trace.data(this, "com.ibm.mq.ClientConnectionRequestInfo", "setClassFields()", "useMQCSP", (this.useMQCSP != null) ? this.useMQCSP : "not set");
/* 344 */       Trace.data(this, "com.ibm.mq.ClientConnectionRequestInfo", "setClassFields()", "useConfigMQCSP", !defaultConfigMQCSP ? Boolean.valueOf(useConfigMQCSP) : "not set");
/*     */     } 
/*     */     
/* 347 */     if (this.password != null && this.password.trim().length() >= 0) {
/* 348 */       if ((this.useMQCSP != null && !this.useMQCSP.booleanValue()) || (this.useMQCSP == null && !useConfigMQCSP)) {
/*     */         
/* 350 */         if (this.password.trim().length() > 12) {
/* 351 */           throw new MQException(2, 2012, this);
/*     */         }
/* 353 */         this.jmqiFlags &= 0xFFFFFF7F;
/*     */       } else {
/*     */         
/* 356 */         this.jmqiFlags |= 0x80;
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 362 */     else if ((this.useMQCSP != null && this.useMQCSP.booleanValue()) || (this.useMQCSP == null && useConfigMQCSP && !defaultConfigMQCSP)) {
/*     */       
/* 364 */       this.jmqiFlags |= 0x80;
/*     */     } else {
/*     */       
/* 367 */       this.jmqiFlags &= 0xFFFFFF7F;
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
/*     */   public int variableHashCode() {
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.entry(this, "com.ibm.mq.ClientConnectionRequestInfo", "variableHashCode()");
/*     */     }
/*     */     
/* 385 */     int out = 0;
/*     */ 
/*     */ 
/*     */     
/* 389 */     if (Trace.isOn) {
/* 390 */       Trace.exit(this, "com.ibm.mq.ClientConnectionRequestInfo", "variableHashCode()", 
/* 391 */           Integer.valueOf(out));
/*     */     }
/* 393 */     return out;
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
/*     */   public int fixedHashCode() {
/* 406 */     if (Trace.isOn) {
/* 407 */       Trace.entry(this, "com.ibm.mq.ClientConnectionRequestInfo", "fixedHashCode()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 413 */     int out = 31 * this.connectOptions;
/* 414 */     if (this.userName != null) {
/* 415 */       out += 37 * this.userName.hashCode();
/*     */     }
/* 417 */     if (this.xaRequired) {
/* 418 */       out += 43;
/*     */     }
/* 420 */     if (this.useQmCcsid) {
/* 421 */       out += 47;
/*     */     }
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
/* 435 */     if (this.sslCipherSuite != null) {
/* 436 */       out += 79 * this.sslCipherSuite.hashCode();
/*     */     }
/* 438 */     if (this.sslPeername != null) {
/* 439 */       out += 83 * this.sslPeername.hashCode();
/*     */     }
/* 441 */     if (this.sslCertStores != null) {
/* 442 */       out += 89 * this.sslCertStores.hashCode();
/*     */     }
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
/* 465 */     if (this.localAddress != null) {
/* 466 */       out += 101 * this.localAddress.hashCode();
/*     */     }
/* 468 */     if (this.hdrCompList != null) {
/* 469 */       out += 103 * this.hdrCompList.hashCode();
/*     */     }
/* 471 */     if (this.msgCompList != null) {
/* 472 */       out += 107 * this.msgCompList.hashCode();
/*     */     }
/* 474 */     if (this.connTag != null) {
/* 475 */       out += 109 * Arrays.hashCode(this.connTag);
/*     */     }
/*     */     
/* 478 */     if (this.sslResetCount != null) {
/* 479 */       out += 113 * this.sslResetCount.hashCode();
/*     */     }
/* 481 */     if (this.sslFipsRequired != null) {
/* 482 */       out += 127 * this.sslFipsRequired.hashCode();
/*     */     }
/*     */     
/* 485 */     if (this.ccdtUrl != null) {
/* 486 */       out += 131 * this.ccdtUrl.toString().hashCode();
/*     */     }
/* 488 */     out += this.sharingConversations * 137;
/* 489 */     out += 139 * this.jmqiFlags;
/* 490 */     if (this.appName != null) {
/* 491 */       out += 149 * this.appName.hashCode();
/*     */     }
/* 493 */     if (this.useMQCSP != null) {
/* 494 */       out += 151 * this.useMQCSP.hashCode();
/*     */     }
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
/* 510 */     if (Trace.isOn) {
/* 511 */       Trace.exit(this, "com.ibm.mq.ClientConnectionRequestInfo", "fixedHashCode()", 
/* 512 */           Integer.valueOf(out));
/*     */     }
/* 514 */     return out;
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
/*     */   public boolean variableIsSuitable(MQManagedConnectionJ11 mc) {
/* 528 */     if (Trace.isOn) {
/* 529 */       Trace.entry(this, "com.ibm.mq.ClientConnectionRequestInfo", "variableIsSuitable(MQManagedConnectionJ11)", new Object[] { mc });
/*     */     }
/*     */     
/* 532 */     if (mc == null) {
/*     */       
/* 534 */       if (Trace.isOn) {
/* 535 */         Trace.exit(this, "com.ibm.mq.ClientConnectionRequestInfo", "variableIsSuitable(MQManagedConnectionJ11)", 
/* 536 */             Boolean.valueOf(false), 1);
/*     */       }
/* 538 */       return false;
/*     */     } 
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
/* 552 */     if (Trace.isOn) {
/* 553 */       Trace.exit(this, "com.ibm.mq.ClientConnectionRequestInfo", "variableIsSuitable(MQManagedConnectionJ11)", 
/* 554 */           Boolean.valueOf(true), 2);
/*     */     }
/* 556 */     return true;
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
/*     */   public boolean fixedEquals(Object obj) {
/* 571 */     if (Trace.isOn) {
/* 572 */       Trace.entry(this, "com.ibm.mq.ClientConnectionRequestInfo", "fixedEquals(Object)", new Object[] { obj });
/*     */     }
/*     */ 
/*     */     
/* 576 */     if (this == obj) {
/*     */       
/* 578 */       if (Trace.isOn) {
/* 579 */         Trace.exit(this, "com.ibm.mq.ClientConnectionRequestInfo", "fixedEquals(Object)", 
/* 580 */             Boolean.valueOf(true), 1);
/*     */       }
/* 582 */       return true;
/*     */     } 
/* 584 */     if (obj == null) {
/* 585 */       if (Trace.isOn) {
/* 586 */         Trace.exit(this, "com.ibm.mq.ClientConnectionRequestInfo", "fixedEquals(Object)", 
/* 587 */             Boolean.valueOf(false), 2);
/*     */       }
/* 589 */       return false;
/*     */     } 
/* 591 */     if (!obj.getClass().equals(getClass())) {
/* 592 */       if (Trace.isOn) {
/* 593 */         Trace.exit(this, "com.ibm.mq.ClientConnectionRequestInfo", "fixedEquals(Object)", 
/* 594 */             Boolean.valueOf(false), 3);
/*     */       }
/* 596 */       return false;
/*     */     } 
/*     */     
/* 599 */     ClientConnectionRequestInfo other = (ClientConnectionRequestInfo)obj;
/*     */     try {
/* 601 */       if (this.connectOptions == other.connectOptions && 
/* 602 */         objEquals(this.userName, other.userName) && 
/* 603 */         objEquals(this.password, other.password) && 
/* 604 */         objEquals(this.securityExit, other.securityExit) && 
/* 605 */         objEquals(this.sendExit, other.sendExit) && 
/* 606 */         objEquals(this.receiveExit, other.receiveExit) && this.xaRequired == other.xaRequired && this.useQmCcsid == other.useQmCcsid && 
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
/* 617 */         objEquals(this.sslCipherSuite, other.sslCipherSuite) && 
/* 618 */         objEquals(this.sslPeername, other.sslPeername) && 
/* 619 */         objEquals(this.sslCertStores, other.sslCertStores) && 
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
/* 641 */         objEquals(this.hdrCompList, other.hdrCompList) && 
/* 642 */         objEquals(this.msgCompList, other.msgCompList) && 
/* 643 */         byteArrayEquals(this.connTag, other.connTag) && 
/* 644 */         objEquals(this.sslResetCount, other.sslResetCount) && 
/* 645 */         objEquals(this.sslFipsRequired, other.sslFipsRequired) && 
/* 646 */         objEquals(this.localAddress, other.localAddress) && 
/* 647 */         objEquals(this.ccdtUrl, other.ccdtUrl) && 
/* 648 */         objEquals(this.appName, other.appName) && 
/* 649 */         objEquals(this.useMQCSP, other.useMQCSP) && this.jmqiFlags == other.jmqiFlags && this.sharingConversations == other.sharingConversations)
/*     */       {
/*     */         
/* 652 */         if (Trace.isOn) {
/* 653 */           Trace.exit(this, "com.ibm.mq.ClientConnectionRequestInfo", "fixedEquals(Object)", 
/* 654 */               Boolean.valueOf(true), 4);
/*     */         }
/* 656 */         return true;
/*     */       }
/*     */     
/* 659 */     } catch (Exception e) {
/* 660 */       if (Trace.isOn) {
/* 661 */         Trace.catchBlock(this, "com.ibm.mq.ClientConnectionRequestInfo", "fixedEquals(Object)", e);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 666 */     if (Trace.isOn) {
/* 667 */       Trace.exit(this, "com.ibm.mq.ClientConnectionRequestInfo", "fixedEquals(Object)", 
/* 668 */           Boolean.valueOf(false), 5);
/*     */     }
/* 670 */     return false;
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
/*     */   public boolean variableEquals(Object obj) {
/* 682 */     if (Trace.isOn) {
/* 683 */       Trace.entry(this, "com.ibm.mq.ClientConnectionRequestInfo", "variableEquals(Object)", new Object[] { obj });
/*     */     }
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
/* 697 */     if (Trace.isOn) {
/* 698 */       Trace.exit(this, "com.ibm.mq.ClientConnectionRequestInfo", "variableEquals(Object)", 
/* 699 */           Boolean.valueOf(true));
/*     */     }
/* 701 */     return true;
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
/*     */   
/*     */   private static boolean byteArrayEquals(byte[] bytes1, byte[] bytes2) {
/* 723 */     if (Trace.isOn) {
/* 724 */       Trace.entry("com.ibm.mq.ClientConnectionRequestInfo", "byteArrayEquals(byte [ ],byte [ ])", new Object[] { bytes1, bytes2 });
/*     */     }
/*     */     
/* 727 */     if (bytes1 == bytes2) {
/* 728 */       if (Trace.isOn) {
/* 729 */         Trace.exit("com.ibm.mq.ClientConnectionRequestInfo", "byteArrayEquals(byte [ ],byte [ ])", 
/* 730 */             Boolean.valueOf(true), 1);
/*     */       }
/* 732 */       return true;
/*     */     } 
/* 734 */     if (bytes1 == null || bytes2 == null) {
/* 735 */       if (Trace.isOn) {
/* 736 */         Trace.exit("com.ibm.mq.ClientConnectionRequestInfo", "byteArrayEquals(byte [ ],byte [ ])", 
/* 737 */             Boolean.valueOf(false), 2);
/*     */       }
/* 739 */       return false;
/*     */     } 
/* 741 */     if (bytes1.length != bytes2.length) {
/* 742 */       if (Trace.isOn) {
/* 743 */         Trace.exit("com.ibm.mq.ClientConnectionRequestInfo", "byteArrayEquals(byte [ ],byte [ ])", 
/* 744 */             Boolean.valueOf(false), 3);
/*     */       }
/* 746 */       return false;
/*     */     } 
/* 748 */     for (int i = 0; i < bytes1.length; i++) {
/* 749 */       if (bytes1[i] != bytes2[i]) {
/* 750 */         if (Trace.isOn) {
/* 751 */           Trace.exit("com.ibm.mq.ClientConnectionRequestInfo", "byteArrayEquals(byte [ ],byte [ ])", 
/* 752 */               Boolean.valueOf(false), 4);
/*     */         }
/* 754 */         return false;
/*     */       } 
/*     */     } 
/* 757 */     if (Trace.isOn) {
/* 758 */       Trace.exit("com.ibm.mq.ClientConnectionRequestInfo", "byteArrayEquals(byte [ ],byte [ ])", 
/* 759 */           Boolean.valueOf(true), 5);
/*     */     }
/* 761 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ClientConnectionRequestInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */