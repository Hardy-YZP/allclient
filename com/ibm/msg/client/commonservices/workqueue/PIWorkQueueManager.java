/*     */ package com.ibm.msg.client.commonservices.workqueue;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.CSIException;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.provider.workqueue.CSPWorkQueueManager;
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
/*     */ public class PIWorkQueueManager
/*     */   implements CSPWorkQueueManager
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/workqueue/PIWorkQueueManager.java";
/*     */   
/*     */   static {
/*  35 */     if (Trace.isOn) {
/*  36 */       Trace.data("com.ibm.msg.client.commonservices.workqueue.PIWorkQueueManager", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/workqueue/PIWorkQueueManager.java");
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
/*     */   public void enqueueItem(WorkQueueItem newItem, int priority) throws CSIException {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.PIWorkQueueManager", "enqueueItem(WorkQueueItem,int)", new Object[] { newItem, 
/*  52 */             Integer.valueOf(priority) });
/*     */     }
/*  54 */     String msg = NLSServices.getMessage("JMSCS0002");
/*  55 */     CSIException thrown = new CSIException(msg);
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.throwing(this, "com.ibm.msg.client.commonservices.workqueue.PIWorkQueueManager", "enqueueItem(WorkQueueItem,int)", (Throwable)thrown);
/*     */     }
/*     */     
/*  60 */     throw thrown;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int fillThreadPool() throws CSIException {
/*  69 */     if (Trace.isOn) {
/*  70 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.PIWorkQueueManager", "fillThreadPool()");
/*     */     }
/*     */     
/*  73 */     String msg = NLSServices.getMessage("JMSCS0002");
/*  74 */     CSIException thrown = new CSIException(msg);
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.throwing(this, "com.ibm.msg.client.commonservices.workqueue.PIWorkQueueManager", "fillThreadPool()", (Throwable)thrown);
/*     */     }
/*     */     
/*  79 */     throw thrown;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int emptyThreadPool() throws CSIException {
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.PIWorkQueueManager", "emptyThreadPool()");
/*     */     }
/*     */     
/*  92 */     String msg = NLSServices.getMessage("JMSCS0002");
/*  93 */     CSIException thrown = new CSIException(msg);
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.throwing(this, "com.ibm.msg.client.commonservices.workqueue.PIWorkQueueManager", "emptyThreadPool()", (Throwable)thrown);
/*     */     }
/*     */     
/*  98 */     throw thrown;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrentThreadPoolSize() throws CSIException {
/* 107 */     String msg = NLSServices.getMessage("JMSCS0002");
/* 108 */     CSIException thrown = new CSIException(msg);
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.throwing(this, "com.ibm.msg.client.commonservices.workqueue.PIWorkQueueManager", "getCurrentThreadPoolSize()", (Throwable)thrown);
/*     */     }
/*     */     
/* 113 */     throw thrown;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() throws CSIException {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.PIWorkQueueManager", "start()");
/*     */     }
/*     */     
/* 126 */     String msg = NLSServices.getMessage("JMSCS0002");
/* 127 */     CSIException thrown = new CSIException(msg);
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.throwing(this, "com.ibm.msg.client.commonservices.workqueue.PIWorkQueueManager", "start()", (Throwable)thrown);
/*     */     }
/*     */     
/* 132 */     throw thrown;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pause() throws CSIException {
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.PIWorkQueueManager", "pause()");
/*     */     }
/*     */     
/* 145 */     String msg = NLSServices.getMessage("JMSCS0002");
/* 146 */     CSIException thrown = new CSIException(msg);
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.throwing(this, "com.ibm.msg.client.commonservices.workqueue.PIWorkQueueManager", "pause()", (Throwable)thrown);
/*     */     }
/*     */     
/* 151 */     throw thrown;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws CSIException {
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.PIWorkQueueManager", "close()");
/*     */     }
/*     */     
/* 164 */     String msg = NLSServices.getMessage("JMSCS0002");
/* 165 */     CSIException thrown = new CSIException(msg);
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.throwing(this, "com.ibm.msg.client.commonservices.workqueue.PIWorkQueueManager", "close()", (Throwable)thrown);
/*     */     }
/*     */     
/* 170 */     throw thrown;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\workqueue\PIWorkQueueManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */