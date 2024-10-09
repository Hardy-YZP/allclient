/*     */ package com.ibm.mq.jmqi.remote.impl;
/*     */ 
/*     */ import com.ibm.mq.exits.MQCD;
/*     */ import com.ibm.mq.exits.MQCSP;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQCNO;
/*     */ import com.ibm.mq.jmqi.MQSCO;
/*     */ import com.ibm.mq.jmqi.remote.api.RemoteFAP;
/*     */ import com.ibm.mq.jmqi.remote.api.RemoteHconn;
/*     */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemoteConnectionPool
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteConnectionPool.java";
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteConnectionPool", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteConnectionPool.java");
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
/*  60 */   private RemoteFAP fap = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   private ConcurrentMap<RemoteConnectionSpecificationKey, RemoteConnectionSpecification> allConnectionSpecs = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteConnectionPool(JmqiEnvironment jmqiEnv, RemoteFAP fap) {
/*  76 */     super(jmqiEnv);
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionPool", "<init>(JmqiEnvironment,RemoteFAP)", new Object[] { jmqiEnv, fap });
/*     */     }
/*     */     
/*  81 */     this.fap = fap;
/*     */     
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionPool", "<init>(JmqiEnvironment,RemoteFAP)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteSession getSession(RemoteTls tls, MQCNO connectOptions, MQCSP mqcsp, MQCD mqcd, MQSCO mqsco, String qmgrName, JmqiConnectOptions jmqiConnectOptions, RemoteHconn parentHconn) throws JmqiException {
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionPool", "getSession(RemoteTls,MQCNO,MQCSP,MQCD,MQSCO,String,JmqiConnectOptions,RemoteHconn)", new Object[] { tls, connectOptions, mqcsp, mqcd, mqsco, qmgrName, jmqiConnectOptions, parentHconn });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 101 */     RemoteConnectionSpecificationKey specKey = new RemoteConnectionSpecificationKey(this.env, this.fap, this, mqcd, mqsco, jmqiConnectOptions, qmgrName);
/*     */ 
/*     */     
/* 104 */     int fapLevel = (jmqiConnectOptions != null) ? jmqiConnectOptions.getFapLevel() : 16;
/* 105 */     RemoteConnectionSpecification candidateConnectionSpec = new RemoteConnectionSpecification(this.env, specKey, fapLevel);
/*     */     
/* 107 */     RemoteConnectionSpecification connectionSpec = this.allConnectionSpecs.putIfAbsent(specKey, candidateConnectionSpec);
/*     */ 
/*     */     
/* 110 */     if (connectionSpec != null) {
/* 111 */       if (Trace.isOn) {
/* 112 */         Trace.data(this, "getSession(RemoteTls,MQCNO,MQCSP,MQCD,MQSCO,String,int,SSLSocketFactory,Collection<?>,String,String,Object,String,Object,String,Object,String,String,int,int)", "A suitable spec was found", connectionSpec);
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 118 */       if (Trace.isOn) {
/* 119 */         Trace.data(this, "getSession(RemoteTls,MQCNO,MQCSP,MQCD,MQSCO,String,int,SSLSocketFactory,Collection<?>,String,String,Object,String,Object,String,Object,String,String,int,int)", "A suitable spec was not found - added this one", candidateConnectionSpec);
/*     */       }
/*     */ 
/*     */       
/* 123 */       connectionSpec = candidateConnectionSpec;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     RemoteSession session = (jmqiConnectOptions != null) ? connectionSpec.getSession(tls, mqcsp, connectOptions, jmqiConnectOptions.getSendExits(), jmqiConnectOptions.getSendExitsUserData(), jmqiConnectOptions.getReceiveExits(), jmqiConnectOptions.getReceiveExitsUserData(), parentHconn, jmqiConnectOptions.jmsChannelSharingMode()) : connectionSpec.getSession(tls, mqcsp, connectOptions, null, null, null, null, parentHconn, JmqiConnectOptions.ChannelSharingMode.GLOBAL);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 143 */     this.allConnectionSpecs.putIfAbsent(specKey, connectionSpec);
/*     */     
/* 145 */     if (jmqiConnectOptions.getRebalancingListener() != null) {
/* 146 */       session.getParentConnection().setRebalancedByResourceAdapter(true);
/*     */     }
/*     */     
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionPool", "getSession(RemoteTls,MQCNO,MQCSP,MQCD,MQSCO,String,JmqiConnectOptions,RemoteHconn)", session);
/*     */     }
/*     */ 
/*     */     
/* 154 */     return session;
/*     */   }
/*     */   
/*     */   void removeSpec(RemoteConnectionSpecificationKey specKey) {
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionPool", "removeSpec(RemoteConnectionSpecification)", new Object[] { specKey });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 165 */     this.allConnectionSpecs.remove(specKey);
/*     */     
/* 167 */     if (Trace.isOn)
/* 168 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteConnectionPool", "removeSpec(RemoteConnectionSpecification)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\impl\RemoteConnectionPool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */