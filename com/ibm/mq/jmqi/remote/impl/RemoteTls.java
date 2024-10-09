/*     */ package com.ibm.mq.jmqi.remote.impl;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.remote.api.RemoteHconn;
/*     */ import com.ibm.mq.jmqi.remote.util.RemoteTLSReceiveBufferPool;
/*     */ import com.ibm.mq.jmqi.remote.util.RemoteTLSSendBufferPool;
/*     */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
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
/*     */ public class RemoteTls
/*     */   extends JmqiComponentTls
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteTls.java";
/*     */   public boolean inExit;
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.jmqi.remote.impl.RemoteTls", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/impl/RemoteTls.java");
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
/*  56 */   public byte[] tshBuffer = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public volatile boolean isDispatchThread = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public volatile RemoteHconn dispatchHconn;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public volatile boolean isReconnectThread = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public volatile boolean isReceiveThread = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public volatile boolean needToCheckForReconnect = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public volatile boolean inMQCTLorMQCB = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public RemoteTLSSendBufferPool sendBufferPool = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public RemoteTLSReceiveBufferPool receiveBufferPool = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteTls(JmqiEnvironment env) {
/* 105 */     this.sendBufferPool = new RemoteTLSSendBufferPool(env);
/* 106 */     this.receiveBufferPool = new RemoteTLSReceiveBufferPool(env);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.impl.RemoteTls", "toString()");
/*     */     }
/* 117 */     StringBuilder buffer = new StringBuilder(256);
/* 118 */     buffer.append(String.format("RemoteTLS@0X%x : ", new Object[] { Integer.valueOf(System.identityHashCode(this)) }));
/* 119 */     buffer.append(String.format("tshBuffer%s ", new Object[] { (this.tshBuffer == null) ? " null" : String.format("@0X%x ", new Object[] { Integer.valueOf(System.identityHashCode(this.tshBuffer)) }) }));
/* 120 */     buffer.append(String.format("isDispatchThread:%b isReconnectThread:%b isReceiveThread:%b inMQCTL:%b ", new Object[] { Boolean.valueOf(this.isDispatchThread), Boolean.valueOf(this.isReconnectThread), Boolean.valueOf(this.isReceiveThread), Boolean.valueOf(this.inMQCTLorMQCB) }));
/* 121 */     buffer.append(String.format("%s ", new Object[] { this.sendBufferPool.toString() }));
/* 122 */     buffer.append(String.format("%s ", new Object[] { this.receiveBufferPool.toString() }));
/* 123 */     String traceRet1 = buffer.toString();
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.impl.RemoteTls", "toString()", traceRet1);
/*     */     }
/* 127 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\impl\RemoteTls.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */