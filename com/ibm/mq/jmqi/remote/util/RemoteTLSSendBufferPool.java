/*     */ package com.ibm.mq.jmqi.remote.util;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemoteTLSSendBufferPool
/*     */   extends JmqiObject
/*     */   implements RemoteCommsBufferPoolInterface
/*     */ {
/*     */   static {
/*  42 */     if (Trace.isOn) {
/*  43 */       Trace.data("RemoteTLSSendBufferPool", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/util/RemoteTLSSendBufferPool.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  49 */   RemoteCommsBufferItem cachedBuffer = null;
/*     */   
/*     */   private volatile boolean toStringInProgress;
/*     */ 
/*     */   
/*     */   public RemoteTLSSendBufferPool(JmqiEnvironment env) {
/*  55 */     super(env);
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
/* 175 */     this.toStringInProgress = false;
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "RemoteTLSSendBufferPool", "<init>(JmqiEnvironment)", new Object[] { env }); 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "RemoteTLSSendBufferPool", "<init>(JmqiEnvironment)"); 
/*     */   }
/*     */   public String toString() {
/* 182 */     if (this.toStringInProgress)
/* 183 */       return String.format("RemoteTLSSendBufferPool @0x%x", new Object[] {
/* 184 */             Integer.valueOf(System.identityHashCode(this))
/*     */           }); 
/* 186 */     this.toStringInProgress = true;
/* 187 */     String result = String.format("RemoteTLSSendBufferPool @0x%x contains %s", new Object[] {
/*     */           
/* 189 */           Integer.valueOf(System.identityHashCode(this)), String.valueOf(this.cachedBuffer) });
/* 190 */     this.toStringInProgress = false;
/* 191 */     return result;
/*     */   }
/*     */   
/*     */   public RemoteCommsBuffer allocBuffer(int capacity) throws JmqiException {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "RemoteTLSSendBufferPool", "allocBuffer(int)", new Object[] { Integer.valueOf(capacity) }); 
/*     */     RemoteCommsBufferItem buffer = null;
/*     */     if (this.cachedBuffer != null && this.cachedBuffer.getCapacity() >= capacity) {
/*     */       buffer = this.cachedBuffer.reset(true);
/*     */     } else {
/*     */       buffer = (new RemoteCommsBufferItem(this.env, this, capacity)).reset(true);
/*     */     } 
/*     */     this.cachedBuffer = null;
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "RemoteTLSSendBufferPool", "allocBuffer(int)", buffer); 
/*     */     return buffer;
/*     */   }
/*     */   
/*     */   public void freeBuffer(RemoteCommsBuffer iBuffer) throws JmqiException {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "RemoteTLSSendBufferPool", "freeBuffer(RemoteCommsBuffer)", new Object[] { iBuffer }); 
/*     */     RemoteCommsBufferItem buffer = (RemoteCommsBufferItem)iBuffer;
/*     */     if (buffer.getPool() != this) {
/*     */       HashMap<String, Object> info = new HashMap<>();
/*     */       info.put("Buffer", buffer);
/*     */       info.put("Pool", this);
/*     */       info.put("Description", "Buffer was not allocated from this pool.");
/*     */       Trace.ffst(this, "freeBuffer(RemoteCommsBuffer)", "01", info, null);
/*     */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */       if (Trace.isOn)
/*     */         Trace.throwing(this, "RemoteTLSSendBufferPool", "freeBuffer(RemoteCommsBuffer)", (Throwable)traceRet1, 1); 
/*     */       throw traceRet1;
/*     */     } 
/*     */     boolean bufferFree = false;
/*     */     synchronized (buffer) {
/*     */       if (!buffer.isInUse() || buffer.getUseCount() <= 0) {
/*     */         HashMap<String, Object> info = new HashMap<>();
/*     */         info.put("Buffer", buffer);
/*     */         info.put("buffer.isInUse()", Boolean.valueOf(buffer.isInUse()));
/*     */         info.put("buffer.useCount", Integer.valueOf(buffer.getUseCount()));
/*     */         info.put("Description", "Buffer in invalid state.");
/*     */         Trace.ffst(this, "freeBuffer(RemoteCommsBuffer)", "01", info, null);
/*     */         JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */         if (Trace.isOn)
/*     */           Trace.throwing(this, "RemoteTLSSendBufferPool", "freeBuffer(RemoteCommsBuffer)", (Throwable)traceRet2, 2); 
/*     */         throw traceRet2;
/*     */       } 
/*     */       int useCount = buffer.getUseCount();
/*     */       useCount--;
/*     */       buffer.setUseCount(useCount);
/*     */       if (useCount == 0) {
/*     */         buffer.reset(false);
/*     */         bufferFree = true;
/*     */       } 
/*     */     } 
/*     */     if (bufferFree)
/*     */       this.cachedBuffer = buffer; 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "RemoteTLSSendBufferPool", "freeBuffer(RemoteCommsBuffer)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remot\\util\RemoteTLSSendBufferPool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */