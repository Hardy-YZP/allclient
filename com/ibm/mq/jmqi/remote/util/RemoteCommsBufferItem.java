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
/*     */ class RemoteCommsBufferItem
/*     */   extends JmqiObject
/*     */   implements RemoteCommsBuffer
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/util/RemoteCommsBufferItem.java";
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/util/RemoteCommsBufferItem.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   private RemoteCommsBufferPoolInterface pool = null;
/*     */ 
/*     */   
/*  57 */   private byte[] buffer = null;
/*     */ 
/*     */   
/*     */   private int useCount;
/*     */ 
/*     */   
/*     */   private boolean inUse;
/*     */ 
/*     */   
/*     */   private int dataAvailable;
/*     */ 
/*     */   
/*     */   private int dataUsed;
/*     */ 
/*     */   
/*     */   private int dataPosition;
/*     */ 
/*     */   
/*  75 */   private RemoteCommsBufferItem nextBuffer = null;
/*     */ 
/*     */   
/*  78 */   private RemoteCommsBufferItem prevBuffer = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemoteCommsBufferItem(JmqiEnvironment env, RemoteCommsBufferPoolInterface pool, int capacity) {
/*  85 */     super(env);
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "<init>(JmqiEnvironment,RemoteCommsBufferPool,int)", new Object[] { env, pool, 
/*     */             
/*  89 */             Integer.valueOf(capacity) });
/*     */     }
/*  91 */     this.pool = pool;
/*  92 */     this.buffer = new byte[capacity];
/*     */     
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "<init>(JmqiEnvironment,RemoteCommsBufferPool,int)");
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
/*     */   public void addUseCount(int useCount) throws JmqiException {
/* 110 */     if (Trace.isOn)
/* 111 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "addUseCount(int)", new Object[] {
/* 112 */             Integer.valueOf(useCount)
/*     */           }); 
/* 114 */     ensureInUse();
/*     */     
/* 116 */     synchronized (this) {
/* 117 */       this.useCount += useCount;
/*     */     } 
/*     */     
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "addUseCount(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void free() throws JmqiException {
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "free()");
/*     */     }
/* 134 */     ensureInUse();
/* 135 */     this.pool.freeBuffer(this);
/*     */     
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "free()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBuffer() throws JmqiException {
/* 149 */     ensureInUse();
/*     */     
/* 151 */     return this.buffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDataAvailable() throws JmqiException {
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.data(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "getDataAvailable()", "getter", 
/* 162 */           Integer.valueOf(this.dataAvailable));
/*     */     }
/* 164 */     return this.dataAvailable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDataUsed() throws JmqiException {
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.data(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "getDataUsed()", "getter", 
/* 175 */           Integer.valueOf(this.dataUsed));
/*     */     }
/* 177 */     return this.dataUsed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDataPosition() throws JmqiException {
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.data(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "getDataPosition()", "getter", 
/* 188 */           Integer.valueOf(this.dataPosition));
/*     */     }
/* 190 */     return this.dataPosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDataAvailable(int dataAvailable) {
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.data(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "setDataAvailable(int)", "setter", 
/* 200 */           Integer.valueOf(dataAvailable));
/*     */     }
/*     */     
/* 203 */     this.dataAvailable = dataAvailable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDataUsed(int dataUsed) {
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.data(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "setDataUsed(int)", "setter", 
/* 213 */           Integer.valueOf(dataUsed));
/*     */     }
/*     */     
/* 216 */     this.dataUsed = dataUsed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDataPosition(int dataPosition) throws JmqiException {
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.data(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "setDataPosition(int)", "setter", 
/* 226 */           Integer.valueOf(dataPosition));
/*     */     }
/*     */     
/* 229 */     this.dataPosition = dataPosition;
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
/*     */   RemoteCommsBufferItem getNextBuffer() {
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.data(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "getNextBuffer()", "getter", this.nextBuffer);
/*     */     }
/*     */     
/* 245 */     return this.nextBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setNextBuffer(RemoteCommsBufferItem nextBuffer) {
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.data(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "setNextBuffer(RemoteCommsBufferItem)", "setter", nextBuffer);
/*     */     }
/*     */ 
/*     */     
/* 257 */     this.nextBuffer = nextBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RemoteCommsBufferItem getPrevBuffer() {
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.data(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "getPrevBuffer()", "getter", this.prevBuffer);
/*     */     }
/*     */     
/* 269 */     return this.prevBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setPrevBuffer(RemoteCommsBufferItem prevBuffer) {
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.data(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "setPrevBuffer(RemoteCommsBufferItem)", "setter", prevBuffer);
/*     */     }
/*     */ 
/*     */     
/* 281 */     this.prevBuffer = prevBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isInUse() {
/* 289 */     if (Trace.isOn) {
/* 290 */       Trace.data(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "isInUse()", "getter", 
/* 291 */           Boolean.valueOf(this.inUse));
/*     */     }
/* 293 */     return this.inUse;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getUseCount() {
/* 303 */     if (Trace.isOn) {
/* 304 */       Trace.data(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "getUseCount()", "getter", 
/* 305 */           Integer.valueOf(this.useCount));
/*     */     }
/* 307 */     return this.useCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setUseCount(int useCount) {
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.data(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "setUseCount(int)", "setter", 
/* 318 */           Integer.valueOf(useCount));
/*     */     }
/*     */     
/* 321 */     this.useCount = useCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RemoteCommsBufferItem reset(boolean inUse) {
/* 332 */     if (Trace.isOn) {
/* 333 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "reset(boolean)", new Object[] {
/* 334 */             Boolean.valueOf(inUse)
/*     */           });
/*     */     }
/* 337 */     if (inUse) {
/* 338 */       this.useCount = 1;
/* 339 */       this.inUse = true;
/*     */     } else {
/*     */       
/* 342 */       this.useCount = 0;
/* 343 */       this.inUse = false;
/*     */     } 
/*     */     
/* 346 */     this.nextBuffer = null;
/* 347 */     this.prevBuffer = null;
/*     */     
/* 349 */     setDataAvailable(0);
/* 350 */     setDataUsed(0);
/*     */     
/* 352 */     if (Trace.isOn) {
/* 353 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "reset(boolean)");
/*     */     }
/* 355 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   RemoteCommsBufferPoolInterface getPool() {
/* 365 */     if (Trace.isOn) {
/* 366 */       Trace.data(this, "com.ibm.mq.jmqi.remote.util.RemoteCommsBufferItem", "getPool()", "getter", this.pool);
/*     */     }
/*     */     
/* 369 */     return this.pool;
/*     */   }
/*     */ 
/*     */   
/*     */   int getCapacity() {
/* 374 */     return this.buffer.length;
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
/*     */   private void ensureInUse() throws JmqiException {
/* 388 */     if (!this.inUse) {
/* 389 */       HashMap<String, Object> ffstInfo = new HashMap<>();
/* 390 */       ffstInfo.put("buffer", toString());
/* 391 */       ffstInfo.put("Description", "This buffer has already been freed");
/* 392 */       Trace.ffst(this, "ensureInUse()", "01", ffstInfo, null);
/* 393 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2195, null);
/*     */       
/* 395 */       throw traceRet1;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remot\\util\RemoteCommsBufferItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */