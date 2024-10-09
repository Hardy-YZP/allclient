/*    */ package com.ibm.msg.client.commonservices.locking;
/*    */ 
/*    */ import java.util.concurrent.locks.Lock;
/*    */ import java.util.concurrent.locks.ReentrantReadWriteLock;
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
/*    */ public class TraceableReadWriteLock
/*    */   extends ReentrantReadWriteLock
/*    */ {
/*    */   private static final long serialVersionUID = 2639171956966385305L;
/*    */   
/*    */   public abstract class TraceableRWLock
/*    */     extends TraceableLock
/*    */   {
/*    */     TraceableRWLock(Lock delegate) {
/* 37 */       super(delegate);
/*    */     }
/*    */     
/*    */     public Thread getOwner() {
/* 41 */       return TraceableReadWriteLock.this.getOwner();
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 46 */       String result = String.format("%s - parent:(%s) Owned by (%s)", new Object[] { super.toString(), this.this$0.toString(), getOwner() });
/* 47 */       return result;
/*    */     }
/*    */   }
/*    */   
/*    */   public class TraceableReadLock
/*    */     extends TraceableRWLock {
/*    */     TraceableReadLock(ReentrantReadWriteLock.ReadLock delegate) {
/* 54 */       super(delegate);
/*    */     }
/*    */   }
/*    */   
/*    */   public class TraceableWriteLock
/*    */     extends TraceableRWLock {
/*    */     TraceableWriteLock(ReentrantReadWriteLock.WriteLock delegate) {
/* 61 */       super(delegate);
/*    */     }
/*    */   }
/*    */   
/*    */   public TraceableReadWriteLock() {
/* 66 */     super(true);
/*    */   }
/*    */   
/*    */   public TraceableReadLock getReadLock() {
/* 70 */     return new TraceableReadLock(super.readLock());
/*    */   }
/*    */   
/*    */   public TraceableWriteLock getWriteLock() {
/* 74 */     return new TraceableWriteLock(super.writeLock());
/*    */   }
/*    */ 
/*    */   
/*    */   public ReentrantReadWriteLock.ReadLock readLock() {
/* 79 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   
/*    */   public ReentrantReadWriteLock.WriteLock writeLock() {
/* 84 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\locking\TraceableReadWriteLock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */