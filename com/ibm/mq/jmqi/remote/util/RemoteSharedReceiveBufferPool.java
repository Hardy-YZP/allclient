/*     */ package com.ibm.mq.jmqi.remote.util;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemoteSharedReceiveBufferPool
/*     */   extends JmqiObject
/*     */   implements RemoteCommsBufferPoolInterface
/*     */ {
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("RemoteSharedReceiveBufferPool", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/util/RemoteSharedReceiveBufferPool.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   Queue<RemoteCommsBufferItem> pool = new LinkedBlockingQueue<>();
/*  54 */   int lastCapacitySeen = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteSharedReceiveBufferPool(JmqiEnvironment env) {
/*  60 */     super(env);
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.entry(this, "RemoteSharedReceiveBufferPool", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.exit(this, "RemoteSharedReceiveBufferPool", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteCommsBuffer allocBuffer(int capacity) throws JmqiException {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "RemoteSharedReceiveBufferPool", "allocBuffer(int)", new Object[] {
/*     */             
/*  80 */             Integer.valueOf(capacity)
/*     */           });
/*     */     }
/*  83 */     if (this.lastCapacitySeen == -1) {
/*  84 */       this.lastCapacitySeen = capacity;
/*  85 */     } else if (this.lastCapacitySeen != capacity) {
/*     */ 
/*     */       
/*  88 */       HashMap<String, Object> info = new HashMap<>();
/*  89 */       info.put("Pool", this);
/*  90 */       info.put("capacity", Integer.valueOf(capacity));
/*  91 */       info.put("lastCapacitySeen", Integer.valueOf(this.lastCapacitySeen));
/*  92 */       info.put("Description", "capacity changed!");
/*  93 */       Trace.ffst(this, "allocBuffer(in)", "01", info, null);
/*  94 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */ 
/*     */ 
/*     */       
/*  98 */       if (Trace.isOn) {
/*  99 */         Trace.throwing(this, "RemoteSharedReceiveBufferPool", "allocBuffer(int)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 102 */       throw traceRet1;
/*     */     } 
/*     */     
/* 105 */     RemoteCommsBufferItem buffer = this.pool.poll();
/* 106 */     if (buffer == null) {
/* 107 */       buffer = (new RemoteCommsBufferItem(this.env, this, capacity)).reset(true);
/*     */     } else {
/* 109 */       buffer.reset(true);
/*     */     } 
/*     */     
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.exit(this, "RemoteSharedReceiveBufferPool", "allocBuffer(int)", buffer);
/*     */     }
/*     */     
/* 116 */     return buffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void freeBuffer(RemoteCommsBuffer iBuffer) throws JmqiException {
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.entry(this, "RemoteSharedReceiveBufferPool", "freeBuffer(RemoteCommsBuffer)", new Object[] { iBuffer });
/*     */     }
/*     */ 
/*     */     
/* 129 */     RemoteCommsBufferItem buffer = (RemoteCommsBufferItem)iBuffer;
/*     */     
/* 131 */     if (buffer.getPool() != this) {
/* 132 */       HashMap<String, Object> info = new HashMap<>();
/* 133 */       info.put("Buffer", buffer);
/* 134 */       info.put("Pool", this);
/* 135 */       info.put("Description", "Buffer was not allocated from this pool.");
/* 136 */       Trace.ffst(this, "freeBuffer(RemoteCommsBuffer)", "01", info, null);
/* 137 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */ 
/*     */ 
/*     */       
/* 141 */       if (Trace.isOn) {
/* 142 */         Trace.throwing(this, "RemoteSharedReceiveBufferPool", "freeBuffer(RemoteCommsBuffer)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 145 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 150 */     boolean bufferFree = false;
/* 151 */     synchronized (buffer) {
/*     */       
/* 153 */       if (!buffer.isInUse() || buffer.getUseCount() <= 0) {
/* 154 */         HashMap<String, Object> info = new HashMap<>();
/* 155 */         info.put("Buffer", buffer);
/* 156 */         info.put("buffer.isInUse()", Boolean.valueOf(buffer.isInUse()));
/* 157 */         info.put("buffer.useCount", Integer.valueOf(buffer.getUseCount()));
/* 158 */         info.put("Description", "Buffer in invalid state.");
/* 159 */         Trace.ffst(this, "freeBuffer(RemoteCommsBuffer)", "01", info, null);
/*     */         
/* 161 */         JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */ 
/*     */         
/* 164 */         if (Trace.isOn) {
/* 165 */           Trace.throwing(this, "RemoteSharedReceiveBufferPool", "freeBuffer(RemoteCommsBuffer)", (Throwable)traceRet2, 2);
/*     */         }
/*     */         
/* 168 */         throw traceRet2;
/*     */       } 
/*     */ 
/*     */       
/* 172 */       int useCount = buffer.getUseCount();
/*     */       
/* 174 */       useCount--;
/*     */       
/* 176 */       buffer.setUseCount(useCount);
/*     */ 
/*     */ 
/*     */       
/* 180 */       if (useCount == 0) {
/*     */         
/* 182 */         buffer.reset(false);
/* 183 */         bufferFree = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 188 */     if (bufferFree) {
/* 189 */       this.pool.offer(buffer);
/*     */     }
/*     */     
/* 192 */     if (Trace.isOn)
/* 193 */       Trace.exit(this, "RemoteSharedReceiveBufferPool", "freeBuffer(RemoteCommsBuffer)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remot\\util\RemoteSharedReceiveBufferPool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */