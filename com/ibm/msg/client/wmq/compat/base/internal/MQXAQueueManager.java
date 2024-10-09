/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiXA;
/*     */ import com.ibm.mq.jmqi.JmqiXAResource;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import javax.transaction.xa.XAException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQXAQueueManager
/*     */ {
/*     */   static final int MQCA_Q_MGR_NAME = 2015;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQXAQueueManager.java";
/*     */   
/*     */   static {
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQXAQueueManager", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQXAQueueManager.java");
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
/*  71 */   protected static final Object XAGROUP = new Object();
/*     */   
/*  73 */   private MQQueueManager qm = null;
/*     */ 
/*     */   
/*  76 */   private JmqiXAResource rm = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQXAQueueManager(MQQueueManager qm) {
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAQueueManager", "<init>(MQQueueManager)", new Object[] { qm });
/*     */     }
/*     */     
/*  97 */     this.qm = qm;
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAQueueManager", "<init>(MQQueueManager)");
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
/*     */   public void close() {
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAQueueManager", "close()");
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 118 */       this.rm.close();
/*     */     }
/* 120 */     catch (Exception e) {
/* 121 */       if (Trace.isOn) {
/* 122 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAQueueManager", "close()", e, 1);
/*     */       }
/*     */ 
/*     */       
/* 126 */       if (Trace.isOn) {
/* 127 */         Trace.traceData(this, "failed to close XAResource", null);
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 132 */       this.qm.disconnect();
/*     */     }
/* 134 */     catch (Exception e) {
/* 135 */       if (Trace.isOn) {
/* 136 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAQueueManager", "close()", e, 2);
/*     */       }
/*     */ 
/*     */       
/* 140 */       if (Trace.isOn) {
/* 141 */         Trace.traceData(this, "failed to disconnect queue manager", null);
/*     */       }
/*     */     } 
/*     */     
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAQueueManager", "close()");
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
/*     */   public JmqiXAResource getXAResource() throws XAException {
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAQueueManager", "getXAResource()");
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 166 */       if (this.rm == null) {
/* 167 */         MQSESSION mqsession = this.qm.getSession();
/* 168 */         Hconn hConn = mqsession.mqManCon.getHconn();
/* 169 */         JmqiXA jmqiXa = mqsession.jXA;
/*     */         
/* 171 */         JmqiEnvironment jmqiEnv = MQSESSION.getJmqiEnv();
/* 172 */         this.rm = jmqiEnv.newJmqiXAResource(jmqiXa, hConn);
/*     */       } 
/*     */       
/* 175 */       if (Trace.isOn) {
/* 176 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAQueueManager", "getXAResource()", this.rm);
/*     */       }
/*     */       
/* 179 */       return this.rm;
/*     */     
/*     */     }
/* 182 */     catch (XAException e) {
/* 183 */       if (Trace.isOn) {
/* 184 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAQueueManager", "getXAResource()", e);
/*     */       }
/*     */       
/* 187 */       if (Trace.isOn) {
/* 188 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQXAQueueManager", "getXAResource()", e);
/*     */       }
/*     */       
/* 191 */       throw e;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQXAQueueManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */