/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   implements MQManagedConnectionFactory
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQClientManagedConnectionFactoryJ11.java";
/*     */   private static final long serialVersionUID = 4815162342L;
/*     */   
/*     */   static {
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQClientManagedConnectionFactoryJ11.java");
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
/*  79 */   private int fieldCCSID = 819;
/*     */   
/*  81 */   private String fieldChannel = "";
/*     */   
/*  83 */   private int fieldPortNumber = 1414;
/*     */   
/*  85 */   private String fieldQMGR = "";
/*     */   
/*  87 */   private String fieldServerName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MQClientManagedConnectionFactoryJ11(String qmgr, Map<String, Object> mqProperties) {
/*  96 */     if (Trace.isOn) {
/*  97 */       if (mqProperties.containsKey("password")) {
/*  98 */         Map<String, Object> propsNotPasswd = new HashMap<>(mqProperties);
/*  99 */         propsNotPasswd.put("password", "********");
/* 100 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "<init>(String,Map)", new Object[] { qmgr, propsNotPasswd });
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 105 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "<init>(String,Map)", new Object[] { qmgr, mqProperties });
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 111 */     this.fieldQMGR = qmgr;
/*     */ 
/*     */ 
/*     */     
/* 115 */     String svrName = MQEnvironment.getStringProperty("hostname", mqProperties);
/* 116 */     this.fieldServerName = (svrName != null) ? svrName : "localhost";
/* 117 */     this.fieldPortNumber = MQEnvironment.getIntegerProperty("port", mqProperties);
/* 118 */     this.fieldChannel = MQEnvironment.getStringProperty("channel", mqProperties);
/* 119 */     this.fieldCCSID = MQEnvironment.getIntegerProperty("CCSID", mqProperties);
/*     */     
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "<init>(String,Map)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQManagedConnectionJ11 createManagedConnection(MQConnectionRequestInfo cxRequestInfo) throws MQResourceException, MQException {
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "createManagedConnection(MQConnectionRequestInfo)", new Object[] { cxRequestInfo });
/*     */     }
/*     */ 
/*     */     
/* 136 */     MQManagedConnectionJ11 traceRet1 = _createManagedConnection(cxRequestInfo);
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "createManagedConnection(MQConnectionRequestInfo)", traceRet1);
/*     */     }
/*     */ 
/*     */     
/* 142 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   MQManagedConnectionJ11 _createManagedConnection(MQConnectionRequestInfo cxRequestInfo) throws MQResourceException, MQException {
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "_createManagedConnection(MQConnectionRequestInfo)", new Object[] { cxRequestInfo });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     if (!(cxRequestInfo instanceof ClientConnectionRequestInfo)) {
/* 155 */       String msg = MQException.getNLSMsg("MQJI039");
/* 156 */       MQResourceException traceRet1 = new MQResourceException(msg);
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "_createManagedConnection(MQConnectionRequestInfo)", traceRet1);
/*     */       }
/*     */ 
/*     */       
/* 162 */       throw traceRet1;
/*     */     } 
/*     */     
/* 165 */     ClientConnectionRequestInfo cCxReqInf = (ClientConnectionRequestInfo)cxRequestInfo;
/*     */ 
/*     */     
/* 168 */     Hashtable<String, Object> properties = new Hashtable<>();
/* 169 */     properties.put("transport", "MQSeries Client");
/* 170 */     properties.put("hostname", this.fieldServerName);
/* 171 */     properties.put("port", Integer.valueOf(this.fieldPortNumber));
/* 172 */     properties.put("channel", this.fieldChannel);
/* 173 */     properties.put("CCSID", Integer.valueOf(this.fieldCCSID));
/*     */     
/* 175 */     properties.put("connectOptions", Integer.valueOf(cCxReqInf.connectOptions));
/* 176 */     if (cCxReqInf.securityExit != null) {
/* 177 */       properties.put("securityExit", cCxReqInf.securityExit);
/*     */     }
/* 179 */     if (cCxReqInf.sendExit != null) {
/* 180 */       properties.put("sendExit", cCxReqInf.sendExit);
/*     */     }
/* 182 */     if (cCxReqInf.receiveExit != null) {
/* 183 */       properties.put("receiveExit", cCxReqInf.receiveExit);
/*     */     }
/*     */     
/* 186 */     if (cCxReqInf.securityExitData != null) {
/* 187 */       properties.put("securitydExitData", cCxReqInf.securityExitData);
/*     */     }
/* 189 */     if (cCxReqInf.sendExitData != null) {
/* 190 */       properties.put("sendExitData", cCxReqInf.sendExitData);
/*     */     }
/* 192 */     if (cCxReqInf.receiveExitData != null) {
/* 193 */       properties.put("receiveExitData", cCxReqInf.receiveExitData);
/*     */     }
/*     */     
/* 196 */     if (cCxReqInf.userName != null) {
/* 197 */       properties.put("userID", cCxReqInf.userName);
/*     */     }
/* 199 */     if (cCxReqInf.password != null) {
/* 200 */       properties.put("password", cCxReqInf.password);
/*     */     }
/* 202 */     if (cCxReqInf.spi != null) {
/* 203 */       properties.put("SPI", cCxReqInf.spi);
/*     */     }
/*     */     
/* 206 */     if (cCxReqInf.ccdtUrl != null) {
/* 207 */       properties.put("CCDT URL", cCxReqInf.ccdtUrl);
/*     */     }
/*     */ 
/*     */     
/* 211 */     if (cCxReqInf.inheritTx != null) {
/* 212 */       if (Trace.isOn) {
/* 213 */         Trace.traceData(this, "put inheritTx = " + cCxReqInf.inheritTx, null);
/*     */       }
/* 215 */       properties.put("SPI_INHERIT_TX", cCxReqInf.inheritTx);
/*     */     } 
/*     */     
/* 218 */     if (cCxReqInf.asyncCmt != null) {
/* 219 */       if (Trace.isOn) {
/* 220 */         Trace.traceData(this, "put asyncCmt  = " + cCxReqInf.asyncCmt, null);
/*     */       }
/* 222 */       properties.put("SPI_ASYNC_CMIT", cCxReqInf.asyncCmt);
/*     */     } 
/*     */ 
/*     */     
/* 226 */     if (cCxReqInf.xaRequired) {
/* 227 */       properties.put("XAReq", Boolean.valueOf(true));
/*     */     }
/*     */ 
/*     */     
/* 231 */     if (cCxReqInf.useQmCcsid) {
/* 232 */       properties.put("Use QM CCSID", Boolean.valueOf(true));
/*     */     }
/*     */ 
/*     */     
/* 236 */     if (cCxReqInf.useBatching != null) {
/* 237 */       properties.put("Batching enabled", cCxReqInf.useBatching);
/*     */     }
/* 239 */     if (cCxReqInf.batchFloor != null) {
/* 240 */       properties.put("Batch size floor", cCxReqInf.batchFloor);
/*     */     }
/* 242 */     if (cCxReqInf.batchCeiling != null) {
/* 243 */       properties.put("Batch size ceiling", cCxReqInf.batchCeiling);
/*     */     }
/* 245 */     if (cCxReqInf.batchThreshold != null) {
/* 246 */       properties.put("Batching threshold", cCxReqInf.batchThreshold);
/*     */     }
/* 248 */     if (cCxReqInf.batchInterval != null) {
/* 249 */       properties.put("Batch interval", cCxReqInf.batchInterval);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 254 */     if (cCxReqInf.sslCipherSuite != null) {
/* 255 */       properties.put("SSL Cipher Suite", cCxReqInf.sslCipherSuite);
/*     */     }
/*     */     
/* 258 */     if (cCxReqInf.sslPeername != null) {
/* 259 */       properties.put("SSL Peer Name", cCxReqInf.sslPeername);
/*     */     }
/*     */     
/* 262 */     if (cCxReqInf.sslCertStores != null) {
/* 263 */       properties.put("SSL CertStores", cCxReqInf.sslCertStores);
/*     */     }
/*     */     
/* 266 */     if (cCxReqInf.sslSocketFactory != null) {
/* 267 */       properties.put("SSL Socket Factory", cCxReqInf.sslSocketFactory);
/*     */     }
/*     */     
/* 270 */     if (cCxReqInf.sslResetCount != null) {
/* 271 */       properties.put("KeyResetCount", cCxReqInf.sslResetCount);
/*     */     }
/*     */     
/* 274 */     if (cCxReqInf.sslFipsRequired != null) {
/* 275 */       properties.put("SSL Fips Required", cCxReqInf.sslFipsRequired);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 283 */     if (cCxReqInf.localAddress != null) {
/* 284 */       properties.put("FIREWALL_SETTINGS", cCxReqInf.localAddress);
/*     */     }
/*     */     
/* 287 */     if (cCxReqInf.hdrCompList != null) {
/* 288 */       properties.put("Header Compression Property", cCxReqInf.hdrCompList);
/*     */     }
/* 290 */     if (cCxReqInf.msgCompList != null) {
/* 291 */       properties.put("ProviderMessage Compression Property", cCxReqInf.msgCompList);
/*     */     }
/* 293 */     if (cCxReqInf.connTag != null) {
/* 294 */       properties.put("ConnTag Property", cCxReqInf.connTag);
/*     */     }
/* 296 */     if (cCxReqInf.appName != null) {
/* 297 */       properties.put("APPNAME", cCxReqInf.appName);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 302 */     MQManagedConnectionJ11 mancon = new MQManagedConnectionJ11(this.fieldQMGR, properties, cCxReqInf, this);
/*     */     
/* 304 */     if (Trace.isOn) {
/* 305 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "_createManagedConnection(MQConnectionRequestInfo)", mancon);
/*     */     }
/*     */ 
/*     */     
/* 309 */     return mancon;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "clone()");
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 321 */       Object traceRet1 = super.clone();
/* 322 */       if (Trace.isOn) {
/* 323 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "clone()", traceRet1);
/*     */       }
/*     */ 
/*     */       
/* 327 */       return traceRet1;
/*     */     }
/* 329 */     catch (CloneNotSupportedException e) {
/* 330 */       if (Trace.isOn) {
/* 331 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "clone()", e);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 336 */       IllegalAccessError traceRet2 = new IllegalAccessError();
/* 337 */       if (Trace.isOn) {
/* 338 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "clone()", traceRet2);
/*     */       }
/*     */ 
/*     */       
/* 342 */       throw traceRet2;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 348 */     if (Trace.isOn) {
/* 349 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "equals(Object)", new Object[] { obj });
/*     */     }
/*     */ 
/*     */     
/* 353 */     if (this == obj) {
/* 354 */       if (Trace.isOn) {
/* 355 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "equals(Object)", 
/*     */             
/* 357 */             Boolean.valueOf(true), 1);
/*     */       }
/* 359 */       return true;
/*     */     } 
/*     */     
/* 362 */     if (obj == null) {
/* 363 */       if (Trace.isOn) {
/* 364 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "equals(Object)", 
/*     */             
/* 366 */             Boolean.valueOf(false), 2);
/*     */       }
/* 368 */       return false;
/*     */     } 
/*     */     
/* 371 */     if (!obj.getClass().equals(getClass())) {
/* 372 */       if (Trace.isOn) {
/* 373 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "equals(Object)", 
/*     */             
/* 375 */             Boolean.valueOf(false), 3);
/*     */       }
/* 377 */       return false;
/*     */     } 
/*     */     
/* 380 */     MQClientManagedConnectionFactoryJ11 other = (MQClientManagedConnectionFactoryJ11)obj;
/*     */     
/* 382 */     if (twoStringsEqual(this.fieldQMGR, other.fieldQMGR) && this.fieldServerName
/*     */       
/* 384 */       .equals(other.fieldServerName) && this.fieldPortNumber == other.fieldPortNumber && this.fieldChannel
/*     */       
/* 386 */       .equals(other.fieldChannel) && this.fieldCCSID == other.fieldCCSID) {
/*     */       
/* 388 */       if (Trace.isOn) {
/* 389 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "equals(Object)", 
/*     */             
/* 391 */             Boolean.valueOf(true), 4);
/*     */       }
/* 393 */       return true;
/*     */     } 
/*     */     
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "equals(Object)", 
/*     */           
/* 399 */           Boolean.valueOf(false), 5);
/*     */     }
/* 401 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 406 */     if (Trace.isOn) {
/* 407 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "hashCode()");
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
/* 418 */     int traceRet1 = getClass().hashCode();
/* 419 */     if (Trace.isOn) {
/* 420 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "hashCode()", 
/*     */           
/* 422 */           Integer.valueOf(traceRet1));
/*     */     }
/* 424 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean twoStringsEqual(String a, String b) {
/*     */     boolean result;
/* 433 */     if (Trace.isOn) {
/* 434 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "twoStringsEqual(String,String)", new Object[] { a, b });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 439 */     if (a == null && b == null) {
/* 440 */       result = true;
/*     */     }
/* 442 */     else if (a == null || b == null) {
/* 443 */       result = false;
/*     */     } else {
/*     */       
/* 446 */       result = a.equals(b);
/*     */     } 
/* 448 */     if (Trace.isOn) {
/* 449 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQClientManagedConnectionFactoryJ11", "twoStringsEqual(String,String)", 
/*     */           
/* 451 */           Boolean.valueOf(result));
/*     */     }
/* 453 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQClientManagedConnectionFactoryJ11.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */