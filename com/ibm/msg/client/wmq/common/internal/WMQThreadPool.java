/*     */ package com.ibm.msg.client.wmq.common.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiThreadPool;
/*     */ import com.ibm.msg.client.commonservices.CSIException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.commonservices.workqueue.WorkQueueManager;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WMQThreadPool
/*     */   implements JmqiThreadPool
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQThreadPool.java";
/*     */   JmqiEnvironment env;
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.msg.client.wmq.common.internal.WMQThreadPool", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQThreadPool.java");
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
/*     */   public WMQThreadPool(JmqiEnvironment env) {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQThreadPool", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  62 */     this.env = env;
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQThreadPool", "<init>(JmqiEnvironment)");
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
/*     */   public void enqueue(Runnable job) throws JmqiException {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQThreadPool", "enqueue(Runnable)", new Object[] { job });
/*     */     }
/*     */     
/*     */     try {
/*  82 */       WorkQueueManager.enqueue(job);
/*     */     }
/*  84 */     catch (CSIException e) {
/*  85 */       if (Trace.isOn) {
/*  86 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.WMQThreadPool", "enqueue(Runnable)", (Throwable)e);
/*     */       }
/*     */       
/*  89 */       Integer cc = Integer.valueOf(2);
/*  90 */       Integer rc = Integer.valueOf(2195);
/*  91 */       HashMap<String, Object> data = new HashMap<>();
/*     */       
/*  93 */       data.put("CompCode", cc);
/*  94 */       data.put("Reason", rc);
/*  95 */       data.put("job", job);
/*  96 */       data.put("exception", e);
/*  97 */       Trace.ffst(this, "enqueue(Runnable)", "XN009001", data, null);
/*     */ 
/*     */ 
/*     */       
/* 101 */       JmqiException e2 = new JmqiException(this.env, -1, null, cc.intValue(), rc.intValue(), (Throwable)e);
/* 102 */       if (Trace.isOn) {
/* 103 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.WMQThreadPool", "enqueue(Runnable)", (Throwable)e2);
/*     */       }
/*     */       
/* 106 */       throw e2;
/*     */     } 
/* 108 */     if (Trace.isOn)
/* 109 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQThreadPool", "enqueue(Runnable)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\WMQThreadPool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */