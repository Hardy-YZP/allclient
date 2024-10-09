/*     */ package com.ibm.msg.client.commonservices.workqueue;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleWorkQueueItem
/*     */   extends WorkQueueItem
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/workqueue/SimpleWorkQueueItem.java";
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.msg.client.commonservices.workqueue.SimpleWorkQueueItem", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/workqueue/SimpleWorkQueueItem.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SimpleWorkQueueItem(Runnable newTask) {
/*  53 */     super(newTask);
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.SimpleWorkQueueItem", "<init>(Runnable)", new Object[] { newTask });
/*     */     }
/*     */     
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.SimpleWorkQueueItem", "<init>(Runnable)");
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
/*     */   public void runItem() {
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.SimpleWorkQueueItem", "runItem()");
/*     */     }
/*     */     
/*  77 */     while (getState() != 3) {
/*     */ 
/*     */       
/*  80 */       if (getState() == 1) {
/*     */         try {
/*  82 */           synchronized (this) {
/*  83 */             if (getState() == 1) {
/*  84 */               wait();
/*     */             }
/*     */           }
/*     */         
/*  88 */         } catch (InterruptedException e) {
/*  89 */           if (Trace.isOn) {
/*  90 */             Trace.catchBlock(this, "com.ibm.msg.client.commonservices.workqueue.SimpleWorkQueueItem", "runItem()", e);
/*     */           }
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/*  96 */       if (getState() == 0) {
/*     */         
/*  98 */         setState(2);
/*  99 */         runTask();
/*     */         
/* 101 */         setState(0);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 107 */       if (!repeats()) {
/* 108 */         end();
/*     */       }
/*     */     } 
/* 111 */     setState(4);
/* 112 */     if (Trace.isOn)
/* 113 */       Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.SimpleWorkQueueItem", "runItem()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\workqueue\SimpleWorkQueueItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */