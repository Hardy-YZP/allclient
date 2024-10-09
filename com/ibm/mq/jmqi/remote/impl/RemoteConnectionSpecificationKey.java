/*     */ package com.ibm.mq.jmqi.remote.impl;
/*     */ 
/*     */ import com.ibm.mq.exits.MQCD;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQSCO;
/*     */ import com.ibm.mq.jmqi.remote.api.RemoteFAP;
/*     */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Collection;
/*     */ import java.util.Objects;
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
/*     */ public class RemoteConnectionSpecificationKey
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteConnectionSpecificationKey.java";
/*     */   private final RemoteFAP fap;
/*     */   private final RemoteConnectionPool connectionPool;
/*     */   private final MQCD mqcd;
/*     */   private final MQSCO mqsco;
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteConnectionSpecificationKey.java");
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
/*  62 */   private int jmqiFlags = 0;
/*  63 */   private SSLSocketFactory sslSocketFactory = null;
/*  64 */   private Collection<?> sslCertStores = null;
/*  65 */   private String uidFlowUserId = null;
/*  66 */   private String uidFlowPassword = null;
/*  67 */   private Object securityExit = null;
/*  68 */   private String securityExitUserData = null;
/*  69 */   private int ccsid = 0;
/*     */   private int reconnectCycle;
/*     */   private String qmgrName;
/*  72 */   private String exitClassPath = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasRebalancingListener = false;
/*     */ 
/*     */ 
/*     */   
/*     */   private volatile int hashCache;
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteConnectionSpecificationKey(JmqiEnvironment jmqiEnv, RemoteFAP fap, RemoteConnectionPool remoteConnectionPool, MQCD mqcd, MQSCO mqsco, JmqiConnectOptions jmqiConnectOptions, String qmgrName) {
/*  85 */     super(jmqiEnv);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 170 */     this.hashCache = 0; if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "<init>(JmqiEnvironment, RemoteFAP, RemoteConnectionPool,MQCD,MQSCO,JmqiConnectOptions,String)", new Object[] { jmqiEnv, fap, remoteConnectionPool, mqcd, mqsco, jmqiConnectOptions, qmgrName });  this.fap = fap; this.connectionPool = remoteConnectionPool; this.mqcd = mqcd; this.mqsco = mqsco; if (jmqiConnectOptions != null) {
/*     */       this.jmqiFlags = jmqiConnectOptions.getFlags(); this.sslSocketFactory = jmqiConnectOptions.getSslSocketFactory(); this.sslCertStores = jmqiConnectOptions.getCrlCertStores(); this.uidFlowUserId = jmqiConnectOptions.getUserIdentifier(); this.uidFlowPassword = jmqiConnectOptions.getPassword(); this.securityExit = jmqiConnectOptions.getSecurityExit(); this.securityExitUserData = jmqiConnectOptions.getSecurityExitUserData(); this.exitClassPath = jmqiConnectOptions.getExitClassPath(); this.ccsid = jmqiConnectOptions.getQueueManagerCCSID(); this.hasRebalancingListener = (jmqiConnectOptions.getRebalancingListener() != null);
/*     */     } 
/*     */     this.qmgrName = qmgrName;
/*     */     this.reconnectCycle = RemoteReconnectThread.getReconnectCycle();
/*     */     if (Trace.isOn)
/* 177 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "<init>(JmqiEnvironment, RemoteFAP, RemoteConnectionPool,MQCD,MQSCO,JmqiConnectOptions,String)");  } public int hashCode() { if (Trace.isOn) {
/* 178 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "hashCode()");
/*     */     }
/*     */     
/* 181 */     if (this.hashCache == 0) {
/* 182 */       this.hashCache = Objects.hash(new Object[] { this.fap, this.connectionPool, this.mqcd, this.mqsco, 
/* 183 */             Integer.valueOf(this.jmqiFlags), this.sslSocketFactory, this.sslCertStores, this.uidFlowUserId, this.uidFlowPassword, 
/*     */ 
/*     */             
/* 186 */             getSecurityExit(), getSecurityExitUserData(), this.exitClassPath, 
/*     */             
/* 188 */             Integer.valueOf(this.ccsid), 
/* 189 */             Integer.valueOf(this.reconnectCycle), this.qmgrName, 
/*     */             
/* 191 */             Boolean.valueOf(this.hasRebalancingListener) });
/*     */     }
/*     */     
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "hashCode()", 
/* 196 */           Integer.valueOf(this.hashCache));
/*     */     }
/* 198 */     return this.hashCache; }
/*     */   public boolean equals(Object other) { if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "equals(Object)", new Object[] { other });  if (!(other instanceof RemoteConnectionSpecificationKey)) { if (Trace.isOn)
/*     */         Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "equals(Object)", Boolean.valueOf(false), 1);  return false; }  RemoteConnectionSpecificationKey candidate = (RemoteConnectionSpecificationKey)other; boolean result = (Objects.equals(candidate.fap, this.fap) && Objects.equals(candidate.connectionPool, this.connectionPool) && Objects.equals(candidate.mqcd, this.mqcd) && Objects.equals(candidate.mqsco, this.mqsco) && candidate.jmqiFlags == this.jmqiFlags && Objects.equals(candidate.sslSocketFactory, this.sslSocketFactory) && Objects.equals(candidate.sslCertStores, this.sslCertStores) && Objects.equals(candidate.uidFlowUserId, this.uidFlowUserId) && Objects.equals(candidate.uidFlowPassword, this.uidFlowPassword) && Objects.equals(candidate.getSecurityExit(), getSecurityExit()) && Objects.equals(candidate.getSecurityExitUserData(), getSecurityExitUserData()) && Objects.equals(candidate.exitClassPath, this.exitClassPath) && candidate.reconnectCycle == this.reconnectCycle && candidate.ccsid == this.ccsid && Objects.equals(candidate.qmgrName, this.qmgrName) && candidate.hasRebalancingListener == this.hasRebalancingListener); if (Trace.isOn)
/* 202 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "equals(Object)", Boolean.valueOf(result), 2);  return result; } void removeSelf() { if (Trace.isOn) {
/* 203 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "removeSelf()");
/*     */     }
/* 205 */     if (this.connectionPool != null) {
/* 206 */       this.connectionPool.removeSpec(this);
/*     */     }
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "removeSelf()");
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MQCD getMqcd() {
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "getMqcd()", "getter", this.mqcd);
/*     */     }
/* 220 */     return this.mqcd;
/*     */   }
/*     */   
/*     */   MQSCO getMqsco() {
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "getMqsco()", "getter", this.mqsco);
/*     */     }
/*     */     
/* 228 */     return this.mqsco;
/*     */   }
/*     */   
/*     */   int getJmqiFlags() {
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "getJmqiFlags()", "getter", 
/* 234 */           Integer.valueOf(this.jmqiFlags));
/*     */     }
/* 236 */     return this.jmqiFlags;
/*     */   }
/*     */   
/*     */   SSLSocketFactory getSslSocketFactory() {
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "getSslSocketFactory()", "getter", this.sslSocketFactory);
/*     */     }
/*     */     
/* 244 */     return this.sslSocketFactory;
/*     */   }
/*     */   
/*     */   Collection<?> getSslCertStores() {
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "getSslCertStores()", "getter", this.sslCertStores);
/*     */     }
/*     */     
/* 252 */     return this.sslCertStores;
/*     */   }
/*     */   
/*     */   String getUidFlowUserId() {
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "getUidFlowUserId()", "getter", this.uidFlowUserId);
/*     */     }
/*     */     
/* 260 */     return this.uidFlowUserId;
/*     */   }
/*     */ 
/*     */   
/*     */   String getUidFlowPassword() {
/* 265 */     return this.uidFlowPassword;
/*     */   }
/*     */   
/*     */   int getCcsid() {
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "getCcsid()", "getter", 
/* 271 */           Integer.valueOf(this.ccsid));
/*     */     }
/* 273 */     return this.ccsid;
/*     */   }
/*     */   
/*     */   String getExitClassPath() {
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "getExitClassPath()", "getter", this.exitClassPath);
/*     */     }
/*     */     
/* 281 */     return this.exitClassPath;
/*     */   }
/*     */   
/*     */   RemoteFAP getFap() {
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "getFap()", "getter", this.fap);
/*     */     }
/*     */     
/* 289 */     return this.fap;
/*     */   }
/*     */   
/*     */   String getQmgrName() {
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "getQmgrName()", "getter", this.qmgrName);
/*     */     }
/*     */     
/* 297 */     return this.qmgrName;
/*     */   }
/*     */   
/*     */   Object getSecurityExit() {
/* 301 */     if (Trace.isOn) {
/* 302 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "getSecurityExit()", "getter", this.securityExit);
/*     */     }
/*     */     
/* 305 */     return this.securityExit;
/*     */   }
/*     */   
/*     */   String getSecurityExitUserData() {
/* 309 */     if (Trace.isOn) {
/* 310 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionSpecificationKey", "getSecurityExitUserData()", "getter", this.securityExitUserData);
/*     */     }
/*     */     
/* 313 */     return this.securityExitUserData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean hasRebalancingListener() {
/* 320 */     return this.hasRebalancingListener;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\impl\RemoteConnectionSpecificationKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */