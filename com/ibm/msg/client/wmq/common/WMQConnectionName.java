/*     */ package com.ibm.msg.client.wmq.common;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WMQConnectionName
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2174857328193645055L;
/*     */   static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/WMQConnectionName.java";
/*     */   private String hostname;
/*     */   private int port;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.msg.client.wmq.common.WMQConnectionName", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/WMQConnectionName.java");
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
/*     */   public WMQConnectionName(String hostname, int port) {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.WMQConnectionName", "<init>(String,int)", new Object[] { hostname, 
/*  61 */             Integer.valueOf(port) });
/*     */     }
/*  63 */     this.hostname = hostname.trim();
/*  64 */     this.port = port;
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.WMQConnectionName", "<init>(String,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHostname() {
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.data(this, "com.ibm.msg.client.wmq.common.WMQConnectionName", "getHostname()", "getter", this.hostname);
/*     */     }
/*     */     
/*  79 */     return this.hostname;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHostname(String hostname) {
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.data(this, "com.ibm.msg.client.wmq.common.WMQConnectionName", "setHostname(String)", "setter", hostname);
/*     */     }
/*     */     
/*  90 */     this.hostname = (hostname == null) ? "" : hostname.trim();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPort() {
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.data(this, "com.ibm.msg.client.wmq.common.WMQConnectionName", "getPort()", "getter", 
/*  99 */           Integer.valueOf(this.port));
/*     */     }
/* 101 */     return this.port;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPort(int port) {
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.data(this, "com.ibm.msg.client.wmq.common.WMQConnectionName", "setPort(int)", "setter", 
/* 110 */           Integer.valueOf(port));
/*     */     }
/* 112 */     this.port = port;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.WMQConnectionName", "toString()");
/*     */     }
/* 123 */     String traceRet1 = this.hostname + "(" + this.port + ")";
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.WMQConnectionName", "toString()", traceRet1);
/*     */     }
/*     */     
/* 128 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\WMQConnectionName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */