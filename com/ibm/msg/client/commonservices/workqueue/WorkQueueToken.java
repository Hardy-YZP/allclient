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
/*     */ public class WorkQueueToken
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/workqueue/WorkQueueToken.java";
/*     */   private WorkQueueItem item;
/*     */   
/*     */   static {
/*  41 */     if (Trace.isOn) {
/*  42 */       Trace.data("com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/workqueue/WorkQueueToken.java");
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
/*     */   WorkQueueToken(WorkQueueItem newItem) {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "<init>(WorkQueueItem)", new Object[] { newItem });
/*     */     }
/*     */     
/*  62 */     this.item = newItem;
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "<init>(WorkQueueItem)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void end() {
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "end()");
/*     */     }
/*  78 */     this.item.end();
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "end()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getRepeats() {
/*  90 */     boolean traceRet1 = this.item.repeats();
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.data(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "getRepeats()", "getter", 
/*  93 */           Boolean.valueOf(traceRet1));
/*     */     }
/*  95 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getState() {
/* 103 */     int traceRet1 = this.item.getState();
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.data(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "getState()", "getter", 
/* 106 */           Integer.valueOf(traceRet1));
/*     */     }
/* 108 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStateString() {
/* 117 */     String stateString = this.item.getStateString();
/*     */     
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.data(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "getStateString()", "getter", stateString);
/*     */     }
/*     */     
/* 123 */     return stateString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pause() {
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "pause()");
/*     */     }
/* 135 */     this.item.pause();
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "pause()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resume() {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "resume()");
/*     */     }
/* 151 */     this.item.resume();
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "resume()");
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
/*     */   public void setRepeats(boolean repeats) {
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.data(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "setRepeats(boolean)", "setter", 
/* 169 */           Boolean.valueOf(repeats));
/*     */     }
/* 171 */     this.item.setRepeats(repeats);
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
/*     */   public Throwable getLastThrown() {
/* 183 */     Throwable lastThrown = this.item.getLastThrown();
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.data(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "getLastThrown()", "getter", lastThrown);
/*     */     }
/*     */     
/* 188 */     return lastThrown;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void waitForCompletion() {
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "waitForCompletion()");
/*     */     }
/*     */     
/* 200 */     this.item.waitForCompletion();
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "waitForCompletion()");
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
/*     */   public void waitForCompletion(long timeout) {
/* 216 */     if (Trace.isOn)
/* 217 */       Trace.entry(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "waitForCompletion(long)", new Object[] {
/* 218 */             Long.valueOf(timeout)
/*     */           }); 
/* 220 */     this.item.waitForCompletion(timeout);
/* 221 */     if (Trace.isOn)
/* 222 */       Trace.exit(this, "com.ibm.msg.client.commonservices.workqueue.WorkQueueToken", "waitForCompletion(long)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\workqueue\WorkQueueToken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */