/*     */ package com.ibm.mq.jmqi.remote.impl;
/*     */ 
/*     */ import com.ibm.mq.exits.MQCD;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.remote.exit.RemoteExitChain;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemoteExitChainPair
/*     */   extends JmqiObject
/*     */ {
/*  38 */   private RemoteExitChain connSendExits = null;
/*  39 */   private RemoteExitChain connRcvExits = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object sendExits;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String sendExitsUserData;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object receiveExits;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String receiveExitsUserData;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String exitClassPath;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RemoteExitChainPair(JmqiEnvironment env, Object sendExits, String sendExitsUserData, Object receiveExits, String receiveExitsUserData, String exitClassPath) {
/*  70 */     super(env);
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteExitChainPair", "<init>(JmqiEnvironment,Object,String,Object,String,String}", new Object[] { sendExits, sendExitsUserData, receiveExits, receiveExitsUserData, exitClassPath });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     this.sendExits = sendExits;
/*  79 */     this.sendExitsUserData = sendExitsUserData;
/*  80 */     this.receiveExits = receiveExits;
/*  81 */     this.receiveExitsUserData = receiveExitsUserData;
/*  82 */     this.exitClassPath = exitClassPath;
/*     */     
/*  84 */     Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteExitChainPair", "<init>(JmqiEnvironment,Object,String,Object,String,String}");
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
/*     */   void loadAndInitExits(RemoteConnection connection, boolean firstConv) throws JmqiException {
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteExitChainPair", "loadAndInitExits(RemoteConnection,boolean)", new Object[] { connection, 
/*     */             
/* 103 */             Boolean.valueOf(firstConv) });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 108 */     MQCD connectionMQCD = connection.getClientConn();
/*     */     
/* 110 */     MQCD negotiatedMQCD = connection.getNegotiatedChannel();
/*     */     
/* 112 */     if (this.sendExits != null || connectionMQCD.getSendExitsDefined() != 0) {
/* 113 */       this.connSendExits = new RemoteExitChain(this.env, 13);
/* 114 */       this.connSendExits.loadExits(connectionMQCD, this.sendExits, connection, this.sendExitsUserData, this.exitClassPath);
/*     */       
/* 116 */       this.connSendExits.initExits(negotiatedMQCD, connection.getFapLevel(), connection.getRemoteProductId(), firstConv);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 121 */     if (this.receiveExits != null || connectionMQCD.getReceiveExitsDefined() != 0) {
/* 122 */       this.connRcvExits = new RemoteExitChain(this.env, 14);
/* 123 */       this.connRcvExits.loadExits(connectionMQCD, this.receiveExits, connection, this.receiveExitsUserData, this.exitClassPath);
/*     */       
/* 125 */       this.connRcvExits.initExits(negotiatedMQCD, connection.getFapLevel(), connection.getRemoteProductId(), firstConv);
/*     */     } 
/*     */ 
/*     */     
/* 129 */     Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteExitChainPair", "loadAndInitExits(RemoteConnection,boolean)");
/*     */   }
/*     */ 
/*     */   
/*     */   RemoteExitChain getConnSendExits() {
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteExitChainPair", "getConnSendExits()", this.connSendExits);
/*     */     }
/*     */     
/* 138 */     return this.connSendExits;
/*     */   }
/*     */   
/*     */   RemoteExitChain getConnRcvExits() {
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.data(this, "com.ibm.mq.jmqi.remote.impl.RemoteExitChainPair", "getConnRcvExits()", this.connRcvExits);
/*     */     }
/*     */     
/* 146 */     return this.connRcvExits;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\impl\RemoteExitChainPair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */