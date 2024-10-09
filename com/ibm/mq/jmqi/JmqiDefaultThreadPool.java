/*     */ package com.ibm.mq.jmqi;
/*     */ 
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
/*     */ public class JmqiDefaultThreadPool
/*     */   implements JmqiThreadPool
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiDefaultThreadPool.java";
/*     */   JmqiEnvironment env;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.jmqi.JmqiDefaultThreadPool", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiDefaultThreadPool.java");
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
/*     */   public JmqiDefaultThreadPool(JmqiEnvironment env) {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiDefaultThreadPool", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  59 */     this.env = env;
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiDefaultThreadPool", "<init>(JmqiEnvironment)");
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
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiDefaultThreadPool", "enqueue(Runnable)", new Object[] { job });
/*     */     }
/*     */     
/*     */     try {
/*  79 */       WorkQueueManager.enqueue(job);
/*     */     }
/*  81 */     catch (CSIException e) {
/*  82 */       if (Trace.isOn) {
/*  83 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.JmqiDefaultThreadPool", "enqueue(Runnable)", (Throwable)e);
/*     */       }
/*  85 */       Integer cc = Integer.valueOf(2);
/*  86 */       Integer rc = Integer.valueOf(2195);
/*  87 */       HashMap<String, Object> data = new HashMap<>();
/*     */       
/*  89 */       data.put("CompCode", cc);
/*  90 */       data.put("Reason", rc);
/*  91 */       data.put("job", job);
/*  92 */       data.put("exception", e);
/*  93 */       Trace.ffst(this, "enqueue(Runnable)", "XN009001", data, null);
/*     */ 
/*     */ 
/*     */       
/*  97 */       JmqiException e2 = new JmqiException(this.env, -1, null, cc.intValue(), rc.intValue(), (Throwable)e);
/*  98 */       if (Trace.isOn) {
/*  99 */         Trace.throwing(this, "com.ibm.mq.jmqi.JmqiDefaultThreadPool", "enqueue(Runnable)", e2);
/*     */       }
/*     */       
/* 102 */       throw e2;
/*     */     } 
/* 104 */     if (Trace.isOn)
/* 105 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiDefaultThreadPool", "enqueue(Runnable)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\JmqiDefaultThreadPool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */