/*    */ package com.ibm.msg.client.commonservices.locking;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import java.util.concurrent.CountDownLatch;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TraceableLatch
/*    */ {
/*    */   CountDownLatch delegate;
/*    */   private String className;
/*    */   
/*    */   public TraceableLatch(CountDownLatch delegate) {
/* 36 */     this.className = String.format("%s(%s)", new Object[] { getClass().getCanonicalName(), delegate.getClass().getSimpleName() });
/* 37 */     if (Trace.isOn) {
/* 38 */       Trace.entry(this.className, "<init>", new Object[] { delegate });
/*    */     }
/* 40 */     this.delegate = delegate;
/* 41 */     if (Trace.isOn) {
/* 42 */       Trace.exit(this.className, "<init>");
/*    */     }
/*    */   }
/*    */   
/*    */   public void await() throws InterruptedException {
/* 47 */     if (Trace.isOn) {
/* 48 */       Trace.entry(this, this.className, "await()");
/* 49 */       Trace.data(this, this.className, "delegate", this.delegate.toString());
/*    */     } 
/* 51 */     this.delegate.await();
/* 52 */     if (Trace.isOn) {
/* 53 */       Trace.exit(this, this.className, "await()");
/*    */     }
/*    */   }
/*    */   
/*    */   public void countDown() {
/* 58 */     if (Trace.isOn) {
/* 59 */       Trace.entry(this, this.className, "countDown()");
/* 60 */       Trace.data(this, this.className, "delegate", this.delegate.toString());
/*    */     } 
/* 62 */     this.delegate.countDown();
/* 63 */     if (Trace.isOn) {
/* 64 */       Trace.exit(this, this.className, "countDown()");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     return this.delegate.toString();
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\locking\TraceableLatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */