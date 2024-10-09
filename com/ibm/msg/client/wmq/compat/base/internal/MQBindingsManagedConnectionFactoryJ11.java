/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
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
/*     */ class MQBindingsManagedConnectionFactoryJ11
/*     */   implements MQManagedConnectionFactory
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQBindingsManagedConnectionFactoryJ11.java";
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQBindingsManagedConnectionFactoryJ11.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   private static JmqiEnvironment jmqiEnv = MQSESSION.getJmqiEnv();
/*     */   
/*     */   private static final long serialVersionUID = 4815162342L;
/*     */   
/*  56 */   private String fieldQMGR = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MQBindingsManagedConnectionFactoryJ11(String qmgr, Map<String, Object> mqProperties) {
/*  65 */     if (Trace.isOn) {
/*  66 */       if (mqProperties.containsKey("password")) {
/*  67 */         Map<String, Object> propsNotPasswd = new HashMap<>(mqProperties);
/*  68 */         propsNotPasswd.put("password", "********");
/*  69 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "<init>(String,Map)", new Object[] { qmgr, propsNotPasswd });
/*     */       
/*     */       }
/*     */       else {
/*     */         
/*  74 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "<init>(String,Map)", new Object[] { qmgr, mqProperties });
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  79 */     this.fieldQMGR = qmgr;
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "<init>(String,Map)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQManagedConnectionJ11 createManagedConnection(MQConnectionRequestInfo cxRequestInfo) throws MQResourceException, MQException {
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "createManagedConnection(MQConnectionRequestInfo)", new Object[] { cxRequestInfo });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  96 */     MQManagedConnectionJ11 traceRet1 = _createManagedConnection(cxRequestInfo);
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "createManagedConnection(MQConnectionRequestInfo)", traceRet1);
/*     */     }
/*     */ 
/*     */     
/* 102 */     return traceRet1;
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
/*     */   MQManagedConnectionJ11 _createManagedConnection(MQConnectionRequestInfo cxRequestInfo) throws MQResourceException, MQException {
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "_createManagedConnection(MQConnectionRequestInfo)", new Object[] { cxRequestInfo });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 121 */     if (!(cxRequestInfo instanceof BindingsConnectionRequestInfo)) {
/* 122 */       String msg = MQException.getNLSMsg("MQJI039");
/* 123 */       MQResourceException traceRet1 = new MQResourceException(msg);
/* 124 */       if (Trace.isOn) {
/* 125 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "_createManagedConnection(MQConnectionRequestInfo)", traceRet1);
/*     */       }
/*     */ 
/*     */       
/* 129 */       throw traceRet1;
/*     */     } 
/*     */     
/* 132 */     BindingsConnectionRequestInfo bCxReqInf = (BindingsConnectionRequestInfo)cxRequestInfo;
/*     */ 
/*     */     
/* 135 */     Hashtable<String, Object> properties = new Hashtable<>();
/* 136 */     properties.put("transport", "MQSeries Bindings");
/* 137 */     properties.put("connectOptions", Integer.valueOf(bCxReqInf.connectOptions));
/* 138 */     if (bCxReqInf.multiThread != null) {
/* 139 */       properties.put("Thread access", bCxReqInf.multiThread);
/*     */     }
/* 141 */     if (bCxReqInf.spi != null) {
/* 142 */       properties.put("SPI", bCxReqInf.spi);
/*     */     }
/*     */ 
/*     */     
/* 146 */     if (bCxReqInf.inheritTx != null) {
/* 147 */       if (Trace.isOn) {
/* 148 */         Trace.traceData(this, "put inheritTx = " + bCxReqInf.inheritTx, null);
/*     */       }
/* 150 */       properties.put("SPI_INHERIT_TX", bCxReqInf.inheritTx);
/*     */     } 
/*     */ 
/*     */     
/* 154 */     if (bCxReqInf.asyncCmt != null) {
/* 155 */       if (Trace.isOn) {
/* 156 */         Trace.traceData(this, "put asyncCmt  = " + bCxReqInf.asyncCmt, null);
/*     */       }
/* 158 */       properties.put("SPI_ASYNC_CMIT", bCxReqInf.asyncCmt);
/*     */     } 
/*     */     
/* 161 */     if (bCxReqInf.threadAffinity != null) {
/* 162 */       properties.put("Thread affinity", bCxReqInf.threadAffinity);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 167 */     if (bCxReqInf.userName != null) {
/* 168 */       properties.put("userID", bCxReqInf.userName);
/*     */     }
/* 170 */     if (bCxReqInf.password != null) {
/* 171 */       properties.put("password", bCxReqInf.password);
/*     */     }
/*     */     
/* 174 */     if (bCxReqInf.authenticateBindings != null) {
/* 175 */       properties.put("Bindings Authentication", bCxReqInf.authenticateBindings);
/*     */     }
/*     */     
/* 178 */     if (bCxReqInf.connTag != null) {
/* 179 */       properties.put("ConnTag Property", bCxReqInf.connTag);
/*     */     }
/*     */     
/* 182 */     if (bCxReqInf.appName != null) {
/* 183 */       properties.put("APPNAME", bCxReqInf.appName);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 189 */     MQManagedConnectionJ11 mancon = new MQManagedConnectionJ11(this.fieldQMGR, properties, cxRequestInfo, this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 199 */     Pint pCompCode = jmqiEnv.newPint();
/* 200 */     Pint pReason = jmqiEnv.newPint();
/*     */     
/* 202 */     mancon.authenticate(pCompCode, pReason);
/*     */     
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "_createManagedConnection(MQConnectionRequestInfo)", mancon);
/*     */     }
/*     */ 
/*     */     
/* 209 */     return mancon;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "hashCode()");
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
/* 227 */     int traceRet1 = getClass().hashCode();
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "hashCode()", 
/*     */           
/* 231 */           Integer.valueOf(traceRet1));
/*     */     }
/* 233 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "equals(Object)", new Object[] { obj });
/*     */     }
/*     */ 
/*     */     
/* 244 */     if (this == obj) {
/* 245 */       if (Trace.isOn) {
/* 246 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "equals(Object)", 
/*     */             
/* 248 */             Boolean.valueOf(true), 1);
/*     */       }
/* 250 */       return true;
/*     */     } 
/*     */     
/* 253 */     if (obj == null) {
/* 254 */       if (Trace.isOn) {
/* 255 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "equals(Object)", 
/*     */             
/* 257 */             Boolean.valueOf(false), 2);
/*     */       }
/* 259 */       return false;
/*     */     } 
/*     */     
/* 262 */     if (!obj.getClass().equals(getClass())) {
/* 263 */       if (Trace.isOn) {
/* 264 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "equals(Object)", 
/*     */             
/* 266 */             Boolean.valueOf(false), 3);
/*     */       }
/* 268 */       return false;
/*     */     } 
/*     */     
/* 271 */     MQBindingsManagedConnectionFactoryJ11 other = (MQBindingsManagedConnectionFactoryJ11)obj;
/*     */ 
/*     */     
/* 274 */     if (twoStringsEqual(this.fieldQMGR, other.fieldQMGR)) {
/* 275 */       if (Trace.isOn) {
/* 276 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "equals(Object)", 
/*     */             
/* 278 */             Boolean.valueOf(true), 4);
/*     */       }
/* 280 */       return true;
/*     */     } 
/*     */     
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "equals(Object)", 
/*     */           
/* 286 */           Boolean.valueOf(false), 5);
/*     */     }
/* 288 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "clone()");
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 299 */       Object traceRet1 = super.clone();
/* 300 */       if (Trace.isOn) {
/* 301 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "clone()", traceRet1);
/*     */       }
/*     */ 
/*     */       
/* 305 */       return traceRet1;
/*     */     }
/* 307 */     catch (CloneNotSupportedException e) {
/* 308 */       if (Trace.isOn) {
/* 309 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "clone()", e);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 314 */       IllegalAccessError traceRet2 = new IllegalAccessError();
/* 315 */       if (Trace.isOn) {
/* 316 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "clone()", traceRet2);
/*     */       }
/*     */ 
/*     */       
/* 320 */       throw traceRet2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean twoStringsEqual(String a, String b) {
/*     */     boolean result;
/* 332 */     if (Trace.isOn) {
/* 333 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "twoStringsEqual(String,String)", new Object[] { a, b });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 338 */     if (a == null && b == null) {
/* 339 */       result = true;
/*     */     }
/* 341 */     else if (a == null || b == null) {
/* 342 */       result = false;
/*     */     } else {
/*     */       
/* 345 */       result = a.equals(b);
/*     */     } 
/* 347 */     if (Trace.isOn) {
/* 348 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQBindingsManagedConnectionFactoryJ11", "twoStringsEqual(String,String)", 
/*     */           
/* 350 */           Boolean.valueOf(result));
/*     */     }
/* 352 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQBindingsManagedConnectionFactoryJ11.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */