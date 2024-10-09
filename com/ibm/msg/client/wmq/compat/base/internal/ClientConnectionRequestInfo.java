/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.compat.network.FWHelper;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Map;
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
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/ClientConnectionRequestInfo.java";
/*     */   public String appName;
/*     */   public Object asyncCmt;
/*     */   
/*     */   static {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/ClientConnectionRequestInfo.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean byteArrayEquals(byte[] bytes1, byte[] bytes2) {
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "byteArrayEquals(byte [ ],byte [ ])", new Object[] { bytes1, bytes2 });
/*     */     }
/*     */     
/*  77 */     if (bytes1 == bytes2) {
/*  78 */       if (Trace.isOn) {
/*  79 */         Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "byteArrayEquals(byte [ ],byte [ ])", 
/*  80 */             Boolean.valueOf(true), 1);
/*     */       }
/*  82 */       return true;
/*     */     } 
/*  84 */     if (bytes1 == null || bytes2 == null) {
/*  85 */       if (Trace.isOn) {
/*  86 */         Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "byteArrayEquals(byte [ ],byte [ ])", 
/*  87 */             Boolean.valueOf(false), 2);
/*     */       }
/*  89 */       return false;
/*     */     } 
/*  91 */     if (bytes1.length != bytes2.length) {
/*  92 */       if (Trace.isOn) {
/*  93 */         Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "byteArrayEquals(byte [ ],byte [ ])", 
/*  94 */             Boolean.valueOf(false), 3);
/*     */       }
/*  96 */       return false;
/*     */     } 
/*  98 */     for (int i = 0; i < bytes1.length; i++) {
/*  99 */       if (bytes1[i] != bytes2[i]) {
/* 100 */         if (Trace.isOn) {
/* 101 */           Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "byteArrayEquals(byte [ ],byte [ ])", 
/* 102 */               Boolean.valueOf(false), 4);
/*     */         }
/* 104 */         return false;
/*     */       } 
/*     */     } 
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "byteArrayEquals(byte [ ],byte [ ])", 
/* 109 */           Boolean.valueOf(true), 5);
/*     */     }
/* 111 */     return true;
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
/* 122 */   public Integer batchCeiling = null;
/*     */ 
/*     */   
/* 125 */   public Integer batchFloor = null;
/*     */   
/* 127 */   public Integer batchInterval = null;
/*     */   
/* 129 */   public Integer batchThreshold = null;
/*     */ 
/*     */   
/* 132 */   public Object ccdtUrl = null;
/*     */ 
/*     */   
/*     */   public int connectOptions;
/*     */ 
/*     */   
/* 138 */   public byte[] connTag = null;
/*     */ 
/*     */   
/* 141 */   public Object hdrCompList = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object inheritTx;
/*     */ 
/*     */ 
/*     */   
/* 150 */   public Object localAddress = "";
/*     */ 
/*     */   
/* 153 */   public Object msgCompList = null;
/*     */ 
/*     */   
/*     */   public String password;
/*     */ 
/*     */   
/*     */   public String receiveExit;
/*     */ 
/*     */   
/*     */   public String receiveExitData;
/*     */ 
/*     */   
/*     */   public String securityExit;
/*     */ 
/*     */   
/*     */   public String securityExitData;
/*     */ 
/*     */   
/*     */   public String sendExit;
/*     */ 
/*     */   
/*     */   public String sendExitData;
/*     */ 
/*     */   
/*     */   public String spi;
/*     */   
/* 179 */   public Object sslCertStores = null;
/*     */ 
/*     */   
/* 182 */   public String sslCipherSuite = null;
/*     */ 
/*     */   
/* 185 */   public Boolean sslFipsRequired = null;
/*     */ 
/*     */   
/* 188 */   public String sslPeername = null;
/*     */ 
/*     */   
/* 191 */   public Integer sslResetCount = null;
/*     */ 
/*     */   
/* 194 */   public Object sslSocketFactory = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 199 */   public Boolean useBatching = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean useQmCcsid;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String userName;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean xaRequired;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ClientConnectionRequestInfo(Hashtable<String, Object> props) throws MQException {
/* 222 */     if (Trace.isOn) {
/* 223 */       if (props.containsKey("password")) {
/* 224 */         Hashtable<String, Object> propsNotPasswd = new Hashtable<>(props);
/* 225 */         propsNotPasswd.put("password", "********");
/* 226 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "<init>(Hashtable)", new Object[] { propsNotPasswd });
/*     */       }
/*     */       else {
/*     */         
/* 230 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "<init>(Hashtable)", new Object[] { props });
/*     */       } 
/*     */     }
/*     */     
/* 234 */     complexPopulate(props);
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "<init>(Hashtable)");
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
/*     */   ClientConnectionRequestInfo(Map<String, Object> mqProperties, boolean fromUrl) throws MQException {
/* 255 */     if (Trace.isOn) {
/* 256 */       if (mqProperties.containsKey("password")) {
/* 257 */         Map<String, Object> propsNotPasswd = new HashMap<>(mqProperties);
/* 258 */         propsNotPasswd.put("password", "********");
/* 259 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "<init>(Map,boolean)", new Object[] { propsNotPasswd, 
/* 260 */               Boolean.valueOf(fromUrl) });
/*     */       } else {
/*     */         
/* 263 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "<init>(Map,boolean)", new Object[] { mqProperties, 
/* 264 */               Boolean.valueOf(fromUrl) });
/*     */       } 
/*     */     }
/*     */     
/* 268 */     if (fromUrl) {
/* 269 */       simplePopulate(mqProperties);
/*     */     } else {
/*     */       
/* 272 */       complexPopulate(mqProperties);
/*     */     } 
/* 274 */     if (Trace.isOn) {
/* 275 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "<init>(Hashtable,boolean)");
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
/*     */   private void complexPopulate(Map<String, Object> mqProperties) throws MQException {
/* 291 */     if (Trace.isOn) {
/* 292 */       if (mqProperties.containsKey("password")) {
/* 293 */         Map<String, Object> propsNotPasswd = new HashMap<>(mqProperties);
/* 294 */         propsNotPasswd.put("password", "********");
/* 295 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "complexPopulate(Map)", new Object[] { propsNotPasswd });
/*     */       }
/*     */       else {
/*     */         
/* 299 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "complexPopulate(Map)", new Object[] { mqProperties });
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 306 */     this.hasVariablePortion = true;
/*     */     
/* 308 */     this.ccdtUrl = MQEnvironment.getObjectProperty("CCDT URL", mqProperties);
/* 309 */     this.connectOptions = MQEnvironment.getIntegerProperty("connectOptions", mqProperties);
/* 310 */     this.securityExit = (String)MQEnvironment.getObjectProperty("securityExit", mqProperties);
/*     */     
/* 312 */     this.receiveExit = (String)MQEnvironment.getObjectProperty("receiveExit", mqProperties);
/* 313 */     this.sendExit = (String)MQEnvironment.getObjectProperty("sendExit", mqProperties);
/*     */     
/* 315 */     this.securityExitData = (String)MQEnvironment.getObjectProperty("securitydExitData", mqProperties);
/*     */     
/* 317 */     this.receiveExitData = (String)MQEnvironment.getObjectProperty("receiveExitData", mqProperties);
/*     */     
/* 319 */     this.sendExitData = (String)MQEnvironment.getObjectProperty("sendExitData", mqProperties);
/*     */ 
/*     */     
/* 322 */     this.userName = MQEnvironment.getStringProperty("userID", mqProperties);
/* 323 */     this.password = MQEnvironment.getStringProperty("password", mqProperties);
/* 324 */     this.spi = MQEnvironment.getStringProperty("SPI", mqProperties);
/*     */ 
/*     */     
/* 327 */     this.inheritTx = MQEnvironment.getObjectProperty("SPI_INHERIT_TX", mqProperties);
/* 328 */     this.asyncCmt = MQEnvironment.getObjectProperty("SPI_ASYNC_CMIT", mqProperties);
/*     */     
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.traceData(this, "inheritTx = " + this.inheritTx, null);
/* 332 */       Trace.traceData(this, "asyncCmt = " + this.asyncCmt, null);
/*     */     } 
/*     */     
/* 335 */     this.sslCipherSuite = MQEnvironment.getStringProperty("SSL Cipher Suite", mqProperties);
/*     */     
/* 337 */     this.sslPeername = MQEnvironment.getStringProperty("SSL Peer Name", mqProperties);
/*     */     
/* 339 */     this.sslCertStores = MQEnvironment.getObjectProperty("SSL CertStores", mqProperties);
/*     */     
/* 341 */     if (this.sslCertStores instanceof Vector) {
/* 342 */       this.sslCertStores = ((Vector)this.sslCertStores).clone();
/*     */     }
/*     */     
/* 345 */     this.sslSocketFactory = MQEnvironment.getObjectProperty("SSL Socket Factory", mqProperties);
/*     */ 
/*     */     
/* 348 */     this.hdrCompList = MQEnvironment.getObjectProperty("Header Compression Property", mqProperties);
/*     */     
/* 350 */     this.msgCompList = MQEnvironment.getObjectProperty("ProviderMessage Compression Property", mqProperties);
/*     */     
/* 352 */     this.sslResetCount = (Integer)MQEnvironment.getObjectProperty("KeyResetCount", mqProperties);
/*     */ 
/*     */     
/* 355 */     this.sslFipsRequired = (Boolean)MQEnvironment.getObjectProperty("SSL Fips Required", mqProperties);
/*     */ 
/*     */     
/* 358 */     this.connTag = (byte[])MQEnvironment.getObjectProperty("ConnTag Property", mqProperties);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 365 */     String localAddressStr = MQEnvironment.getStringProperty("Local Address Property", mqProperties);
/*     */ 
/*     */     
/* 368 */     if (localAddressStr != null) {
/*     */       
/* 370 */       FWHelper.encodeToObject(localAddressStr);
/* 371 */       this.localAddress = localAddressStr;
/*     */     } 
/*     */     
/* 374 */     setClassFields(mqProperties);
/* 375 */     if (Trace.isOn) {
/* 376 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "complexPopulate(Map)");
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
/*     */   public boolean fixedEquals(Object obj) {
/* 390 */     if (Trace.isOn) {
/* 391 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "fixedEquals(Object)", new Object[] { obj });
/*     */     }
/*     */ 
/*     */     
/* 395 */     if (this == obj) {
/* 396 */       if (Trace.isOn) {
/* 397 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "fixedEquals(Object)", 
/*     */             
/* 399 */             Boolean.valueOf(true), 1);
/*     */       }
/* 401 */       return true;
/*     */     } 
/* 403 */     if (obj == null) {
/* 404 */       if (Trace.isOn) {
/* 405 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "fixedEquals(Object)", 
/*     */             
/* 407 */             Boolean.valueOf(false), 2);
/*     */       }
/* 409 */       return false;
/*     */     } 
/* 411 */     if (!obj.getClass().equals(getClass())) {
/* 412 */       if (Trace.isOn) {
/* 413 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "fixedEquals(Object)", 
/*     */             
/* 415 */             Boolean.valueOf(false), 3);
/*     */       }
/* 417 */       return false;
/*     */     } 
/*     */     
/* 420 */     ClientConnectionRequestInfo other = (ClientConnectionRequestInfo)obj;
/*     */     try {
/* 422 */       if (this.connectOptions == other.connectOptions && objEquals(this.userName, other.userName) && objEquals(this.password, other.password) && objEquals(this.securityExit, other.securityExit) && 
/* 423 */         objEquals(this.sendExit, other.sendExit) && objEquals(this.receiveExit, other.receiveExit) && this.xaRequired == other.xaRequired && this.useQmCcsid == other.useQmCcsid && 
/* 424 */         objEquals(this.spi, other.spi) && objEquals(this.inheritTx, other.inheritTx) && objEquals(this.asyncCmt, other.asyncCmt) && objEquals(this.useBatching, other.useBatching) && 
/* 425 */         objEquals(this.batchFloor, other.batchFloor) && objEquals(this.batchCeiling, other.batchCeiling) && objEquals(this.batchThreshold, other.batchThreshold) && 
/* 426 */         objEquals(this.batchInterval, other.batchInterval) && objEquals(this.sslCipherSuite, other.sslCipherSuite) && objEquals(this.sslPeername, other.sslPeername) && 
/* 427 */         objEquals(this.sslCertStores, other.sslCertStores) && 
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
/* 449 */         objEquals(this.hdrCompList, other.hdrCompList) && objEquals(this.msgCompList, other.msgCompList) && byteArrayEquals(this.connTag, other.connTag) && 
/* 450 */         objEquals(this.sslResetCount, other.sslResetCount) && objEquals(this.sslFipsRequired, other.sslFipsRequired)) {
/* 451 */         if (Trace.isOn) {
/* 452 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "fixedEquals(Object)", 
/*     */               
/* 454 */               Boolean.valueOf(true), 4);
/*     */         }
/* 456 */         return true;
/*     */       }
/*     */     
/* 459 */     } catch (Exception e) {
/* 460 */       if (Trace.isOn) {
/* 461 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "fixedEquals(Object)", e);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 467 */     if (Trace.isOn) {
/* 468 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "fixedEquals(Object)", 
/* 469 */           Boolean.valueOf(false), 5);
/*     */     }
/* 471 */     return false;
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
/*     */   public int fixedHashCode() {
/* 483 */     if (Trace.isOn) {
/* 484 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "fixedHashCode()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 491 */     int out = 31 * this.connectOptions;
/* 492 */     if (this.userName != null) {
/* 493 */       out += 37 * this.userName.hashCode();
/*     */     }
/*     */     
/* 496 */     if (this.xaRequired) {
/* 497 */       out += 43;
/*     */     }
/* 499 */     if (this.useQmCcsid) {
/* 500 */       out += 47;
/*     */     }
/* 502 */     if (this.spi != null) {
/* 503 */       out += 53 * this.spi.hashCode();
/*     */     }
/* 505 */     if (this.useBatching != null) {
/* 506 */       out += 59 * this.useBatching.hashCode();
/*     */     }
/* 508 */     if (this.batchFloor != null) {
/* 509 */       out += 61 * this.batchFloor.hashCode();
/*     */     }
/* 511 */     if (this.batchCeiling != null) {
/* 512 */       out += 67 * this.batchCeiling.hashCode();
/*     */     }
/* 514 */     if (this.batchThreshold != null) {
/* 515 */       out += 71 * this.batchThreshold.hashCode();
/*     */     }
/* 517 */     if (this.batchInterval != null) {
/* 518 */       out += 73 * this.batchInterval.hashCode();
/*     */     }
/* 520 */     if (this.sslCipherSuite != null) {
/* 521 */       out += 79 * this.sslCipherSuite.hashCode();
/*     */     }
/* 523 */     if (this.sslPeername != null) {
/* 524 */       out += 83 * this.sslPeername.hashCode();
/*     */     }
/* 526 */     if (this.sslCertStores != null) {
/* 527 */       out += 89 * this.sslCertStores.hashCode();
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
/* 552 */     if (this.hdrCompList != null) {
/* 553 */       out += 103 * this.hdrCompList.hashCode();
/*     */     }
/* 555 */     if (this.msgCompList != null) {
/* 556 */       out += 107 * this.msgCompList.hashCode();
/*     */     }
/* 558 */     if (this.connTag != null) {
/* 559 */       out += 109 * Arrays.hashCode(this.connTag);
/*     */     }
/*     */ 
/*     */     
/* 563 */     if (this.sslResetCount != null) {
/* 564 */       out += 113 * this.sslResetCount.hashCode();
/*     */     }
/* 566 */     if (this.sslFipsRequired != null) {
/* 567 */       out += 127 * this.sslFipsRequired.hashCode();
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
/* 586 */     if (Trace.isOn) {
/* 587 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "fixedHashCode()", 
/* 588 */           Integer.valueOf(out));
/*     */     }
/* 590 */     return out;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getInt(Map<String, Object> mqProperties, String key) {
/* 597 */     if (Trace.isOn) {
/* 598 */       if (mqProperties.containsKey("password")) {
/* 599 */         Map<String, Object> propsNotPasswd = new HashMap<>(mqProperties);
/* 600 */         propsNotPasswd.put("password", "********");
/* 601 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "getInt(Map,String)", new Object[] { propsNotPasswd, key });
/*     */       }
/*     */       else {
/*     */         
/* 605 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "getInt(Map,String)", new Object[] { mqProperties, key });
/*     */       } 
/*     */     }
/*     */     
/* 609 */     Integer integer = (Integer)mqProperties.get(key);
/* 610 */     int result = 0;
/* 611 */     if (integer != null) {
/* 612 */       result = integer.intValue();
/*     */     }
/* 614 */     if (Trace.isOn) {
/* 615 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "getInt(Map,String)", 
/* 616 */           Integer.valueOf(result));
/*     */     }
/* 618 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setClassFields(Map<String, Object> mqProperties) {
/* 624 */     if (Trace.isOn) {
/* 625 */       if (mqProperties.containsKey("password")) {
/* 626 */         Map<String, Object> propsNotPasswd = new HashMap<>(mqProperties);
/* 627 */         propsNotPasswd.put("password", "********");
/* 628 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "setClassFields(Map)", new Object[] { propsNotPasswd });
/*     */       }
/*     */       else {
/*     */         
/* 632 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "setClassFields(Map)", new Object[] { mqProperties });
/*     */       } 
/*     */     }
/*     */     
/* 636 */     if (mqProperties != null) {
/* 637 */       this.xaRequired = false;
/* 638 */       Object xar = mqProperties.get("XAReq");
/* 639 */       if (xar != null && 
/* 640 */         xar instanceof Boolean) {
/* 641 */         this.xaRequired = ((Boolean)xar).booleanValue();
/*     */       }
/*     */ 
/*     */       
/* 645 */       this.useQmCcsid = false;
/*     */       
/* 647 */       Object object = mqProperties.get("Use QM CCSID");
/* 648 */       if (object != null && object instanceof Boolean) {
/* 649 */         this.useQmCcsid = ((Boolean)object).booleanValue();
/*     */       }
/*     */       
/* 652 */       object = mqProperties.get("Batching enabled");
/* 653 */       if (object != null && object instanceof Boolean) {
/* 654 */         this.useBatching = (Boolean)object;
/*     */       }
/*     */       
/* 657 */       object = mqProperties.get("Batch size floor");
/* 658 */       if (object != null && object instanceof Integer) {
/* 659 */         this.batchFloor = (Integer)object;
/*     */       }
/*     */       
/* 662 */       object = mqProperties.get("Batch size ceiling");
/* 663 */       if (object != null && object instanceof Integer) {
/* 664 */         this.batchCeiling = (Integer)object;
/*     */       }
/*     */       
/* 667 */       object = mqProperties.get("Batching threshold");
/* 668 */       if (object != null && object instanceof Integer) {
/* 669 */         this.batchThreshold = (Integer)object;
/*     */       }
/*     */       
/* 672 */       object = mqProperties.get("Batch interval");
/* 673 */       if (object != null && object instanceof Integer) {
/* 674 */         this.batchInterval = (Integer)object;
/*     */       }
/*     */       
/* 677 */       object = mqProperties.get("APPNAME");
/* 678 */       if (object != null && object instanceof String) {
/* 679 */         this.appName = (String)object;
/*     */       }
/*     */     } 
/*     */     
/* 683 */     if (Trace.isOn) {
/* 684 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "setClassFields(Map)");
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
/*     */   private void simplePopulate(Map<String, Object> mqProperties) throws MQException {
/* 699 */     if (Trace.isOn) {
/* 700 */       if (mqProperties.containsKey("password")) {
/* 701 */         Map<String, Object> propsNotPasswd = new HashMap<>(mqProperties);
/* 702 */         propsNotPasswd.put("password", "********");
/* 703 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "simplePopulate(Map)", new Object[] { propsNotPasswd });
/*     */       }
/*     */       else {
/*     */         
/* 707 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "simplePopulate(Map)", new Object[] { mqProperties });
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
/* 721 */     this.hasVariablePortion = true;
/*     */     
/* 723 */     this.ccdtUrl = mqProperties.get("CCDT URL");
/* 724 */     this.connectOptions = getInt(mqProperties, "connectOptions");
/* 725 */     this.securityExit = (String)mqProperties.get("securityExit");
/* 726 */     this.receiveExit = (String)mqProperties.get("receiveExit");
/* 727 */     this.sendExit = (String)mqProperties.get("sendExit");
/*     */     
/* 729 */     this.securityExitData = (String)mqProperties.get("securitydExitData");
/* 730 */     this.receiveExitData = (String)mqProperties.get("receiveExitData");
/* 731 */     this.sendExitData = (String)mqProperties.get("sendExitData");
/*     */ 
/*     */     
/* 734 */     this.userName = MQEnvironment.getStringProperty("userID", mqProperties);
/* 735 */     this.password = MQEnvironment.getStringProperty("password", mqProperties);
/* 736 */     this.spi = (String)mqProperties.get("SPI");
/*     */ 
/*     */     
/* 739 */     this.inheritTx = mqProperties.get("SPI_INHERIT_TX");
/* 740 */     this.asyncCmt = mqProperties.get("SPI_ASYNC_CMIT");
/*     */     
/* 742 */     if (Trace.isOn) {
/* 743 */       Trace.traceData(this, "inheritTx = " + this.inheritTx, null);
/* 744 */       Trace.traceData(this, "asyncCmt = " + this.asyncCmt, null);
/*     */     } 
/*     */     
/* 747 */     this.sslCipherSuite = (String)mqProperties.get("SSL Cipher Suite");
/* 748 */     this.sslPeername = (String)mqProperties.get("SSL Peer Name");
/* 749 */     this.sslCertStores = mqProperties.get("SSL CertStores");
/*     */     
/* 751 */     if (this.sslCertStores instanceof Vector) {
/* 752 */       this.sslCertStores = ((Vector)this.sslCertStores).clone();
/*     */     }
/*     */     
/* 755 */     this.sslSocketFactory = mqProperties.get("SSL Socket Factory");
/* 756 */     this.hdrCompList = mqProperties.get("Header Compression Property");
/* 757 */     this.msgCompList = mqProperties.get("ProviderMessage Compression Property");
/* 758 */     this.sslResetCount = (Integer)mqProperties.get("KeyResetCount");
/* 759 */     this.connTag = (byte[])mqProperties.get("ConnTag Property");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 766 */     String localAddressStr = (String)mqProperties.get("Local Address Property");
/*     */     
/* 768 */     if (localAddressStr != null && !localAddressStr.equals("")) {
/*     */       
/* 770 */       FWHelper.encodeToObject(localAddressStr);
/* 771 */       this.localAddress = localAddressStr;
/*     */     } 
/*     */     
/* 774 */     setClassFields(mqProperties);
/* 775 */     if (Trace.isOn) {
/* 776 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "simplePopulate(Map)");
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
/*     */   public boolean variableEquals(Object obj) {
/* 789 */     if (Trace.isOn) {
/* 790 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "variableEquals(Object)", new Object[] { obj });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 795 */     ClientConnectionRequestInfo other = (ClientConnectionRequestInfo)obj;
/*     */     
/* 797 */     if (!objEquals(this.localAddress, other.localAddress)) {
/* 798 */       if (Trace.isOn) {
/* 799 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "variableEquals(Object)", 
/*     */             
/* 801 */             Boolean.valueOf(false), 1);
/*     */       }
/* 803 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 807 */     if (Trace.isOn) {
/* 808 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "variableEquals(Object)", 
/* 809 */           Boolean.valueOf(true), 2);
/*     */     }
/* 811 */     return true;
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
/* 824 */     if (Trace.isOn) {
/* 825 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "variableHashCode()");
/*     */     }
/*     */ 
/*     */     
/* 829 */     int out = 0;
/*     */ 
/*     */     
/* 832 */     if (this.localAddress != null) {
/* 833 */       out += 101 * this.localAddress.hashCode();
/*     */     }
/*     */ 
/*     */     
/* 837 */     if (Trace.isOn) {
/* 838 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "variableHashCode()", 
/* 839 */           Integer.valueOf(out));
/*     */     }
/* 841 */     return out;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean variableIsSuitable(MQManagedConnectionJ11 mc) {
/* 852 */     if (Trace.isOn) {
/* 853 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "variableIsSuitable(MQManagedConnectionJ11)", new Object[] { mc });
/*     */     }
/*     */ 
/*     */     
/* 857 */     if (mc == null) {
/* 858 */       if (Trace.isOn) {
/* 859 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "variableIsSuitable(MQManagedConnectionJ11)", 
/*     */             
/* 861 */             Boolean.valueOf(false), 1);
/*     */       }
/* 863 */       return false;
/*     */     } 
/* 865 */     synchronized (mc) {
/*     */       
/* 867 */       if (!FWHelper.fuzzyCompare(mc, this.localAddress)) {
/* 868 */         if (Trace.isOn) {
/* 869 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "variableIsSuitable(MQManagedConnectionJ11)", 
/*     */               
/* 871 */               Boolean.valueOf(false), 2);
/*     */         }
/* 873 */         return false;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 878 */     if (Trace.isOn) {
/* 879 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.ClientConnectionRequestInfo", "variableIsSuitable(MQManagedConnectionJ11)", 
/* 880 */           Boolean.valueOf(true), 3);
/*     */     }
/* 882 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\ClientConnectionRequestInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */