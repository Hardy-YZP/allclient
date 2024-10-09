/*     */ package com.ibm.mq.ese.intercept;
/*     */ 
/*     */ import com.ibm.mq.constants.QmgrSplCapability;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ConnectionContext
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/ConnectionContext.java";
/*     */   private final Hconn hconn;
/*     */   
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.mq.ese.intercept.ConnectionContext", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/ConnectionContext.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   private final ConcurrentHashMap<Hobj, SmqiObject> objects = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String qmgrName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String qmgrDlqName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   private AtomicBoolean isProtectedConn = new AtomicBoolean(false);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private QmgrSplCapability splCapability;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   private Set<String> noProtPut1Dests = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   private ReentrantReadWriteLock noProtPut1DestsLock = new ReentrantReadWriteLock();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean cachePut1Dests;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionContext(Hconn hconn, String name, String dlqName, QmgrSplCapability capability) {
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.entry(this, "com.ibm.mq.ese.intercept.ConnectionContext", "<init>(Hconn,String,String,QmgrSplCapability)", new Object[] { hconn, name, dlqName, capability });
/*     */     }
/*     */ 
/*     */     
/* 122 */     if (hconn == null) {
/* 123 */       IllegalArgumentException traceRet1 = new IllegalArgumentException("hconn must not be null");
/* 124 */       if (Trace.isOn) {
/* 125 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.ConnectionContext", "<init>(Hconn,String,String,QmgrSplCapability)", traceRet1);
/*     */       }
/*     */       
/* 128 */       throw traceRet1;
/*     */     } 
/* 130 */     this.hconn = hconn;
/* 131 */     this.qmgrName = name;
/* 132 */     this.qmgrDlqName = dlqName;
/* 133 */     this.splCapability = capability;
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.exit(this, "com.ibm.mq.ese.intercept.ConnectionContext", "<init>(Hconn,String,String,QmgrSplCapability)");
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
/*     */   public void addSmqiObject(SmqiObject obj) {
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.entry(this, "com.ibm.mq.ese.intercept.ConnectionContext", "addSmqiObject(SmqiObject)", new Object[] { obj });
/*     */     }
/*     */     
/* 154 */     this.objects.put(obj.getHobj(), obj);
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.exit(this, "com.ibm.mq.ese.intercept.ConnectionContext", "addSmqiObject(SmqiObject)");
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
/*     */   public SmqiObject findObject(Hobj hobj) {
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.entry(this, "com.ibm.mq.ese.intercept.ConnectionContext", "findObject(Hobj)", new Object[] { hobj });
/*     */     }
/*     */     
/* 173 */     SmqiObject traceRet1 = this.objects.get(hobj);
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.exit(this, "com.ibm.mq.ese.intercept.ConnectionContext", "findObject(Hobj)", traceRet1);
/*     */     }
/* 177 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SmqiObject free(Hobj hobj) {
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.entry(this, "com.ibm.mq.ese.intercept.ConnectionContext", "free(Hobj)", new Object[] { hobj });
/*     */     }
/*     */     
/* 192 */     SmqiObject traceRet1 = this.objects.remove(hobj);
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.exit(this, "com.ibm.mq.ese.intercept.ConnectionContext", "free(Hobj)", traceRet1);
/*     */     }
/* 196 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.entry(this, "com.ibm.mq.ese.intercept.ConnectionContext", "toString()");
/*     */     }
/* 209 */     StringBuilder str = new StringBuilder();
/* 210 */     str.append(this.qmgrName).append(';').append(this.hconn);
/* 211 */     str.append(";SPL=").append(this.splCapability);
/* 212 */     String traceRet1 = str.toString();
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.exit(this, "com.ibm.mq.ese.intercept.ConnectionContext", "toString()", traceRet1);
/*     */     }
/* 216 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Hconn getHconn() {
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.data(this, "com.ibm.mq.ese.intercept.ConnectionContext", "getHconn()", "getter", this.hconn);
/*     */     }
/* 225 */     return this.hconn;
/*     */   }
/*     */   
/*     */   public String getQmgrName() {
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.data(this, "com.ibm.mq.ese.intercept.ConnectionContext", "getQmgrName()", "getter", this.qmgrName);
/*     */     }
/*     */     
/* 233 */     return this.qmgrName;
/*     */   }
/*     */   
/*     */   public String getQmgrDlqName() {
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.data(this, "com.ibm.mq.ese.intercept.ConnectionContext", "getQmgrDlqName()", "getter", this.qmgrDlqName);
/*     */     }
/*     */     
/* 241 */     return this.qmgrDlqName;
/*     */   }
/*     */ 
/*     */   
/*     */   Map<Hobj, SmqiObject> getEseObjects() {
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.data(this, "com.ibm.mq.ese.intercept.ConnectionContext", "getEseObjects()", "getter", this.objects);
/*     */     }
/*     */     
/* 250 */     return this.objects;
/*     */   }
/*     */   
/*     */   public AtomicBoolean isProtectedConn() {
/* 254 */     if (Trace.isOn) {
/* 255 */       Trace.entry(this, "com.ibm.mq.ese.intercept.ConnectionContext", "isProtectedConn()");
/*     */     }
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.exit(this, "com.ibm.mq.ese.intercept.ConnectionContext", "isProtectedConn()", this.isProtectedConn);
/*     */     }
/*     */     
/* 261 */     return this.isProtectedConn;
/*     */   }
/*     */   
/*     */   public QmgrSplCapability getSplCapability() {
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.data(this, "com.ibm.mq.ese.intercept.ConnectionContext", "getSplCapability()", "getter", this.splCapability);
/*     */     }
/*     */     
/* 269 */     return this.splCapability;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCachePut1Dests() {
/* 280 */     if (Trace.isOn) {
/* 281 */       Trace.data(this, "com.ibm.mq.ese.intercept.ConnectionContext", "isCachePut1Dests()", "getter", 
/* 282 */           Boolean.valueOf(this.cachePut1Dests));
/*     */     }
/* 284 */     return this.cachePut1Dests;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCachePut1Dests(boolean cachePut1Dests) {
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.data(this, "com.ibm.mq.ese.intercept.ConnectionContext", "setCachePut1Dests(boolean)", "setter", 
/* 294 */           Boolean.valueOf(cachePut1Dests));
/*     */     }
/* 296 */     this.cachePut1Dests = cachePut1Dests;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsQopNoneMQPUT1(String name) {
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.entry(this, "com.ibm.mq.ese.intercept.ConnectionContext", "containsQopNoneMQPUT1(String)", new Object[] { name });
/*     */     }
/*     */     
/* 311 */     this.noProtPut1DestsLock.readLock().lock();
/*     */     try {
/* 313 */       boolean traceRet1 = this.noProtPut1Dests.contains(name);
/* 314 */       if (Trace.isOn) {
/* 315 */         Trace.exit(this, "com.ibm.mq.ese.intercept.ConnectionContext", "containsQopNoneMQPUT1(String)", 
/* 316 */             Boolean.valueOf(traceRet1));
/*     */       }
/* 318 */       return traceRet1;
/*     */     } finally {
/*     */       
/* 321 */       if (Trace.isOn) {
/* 322 */         Trace.finallyBlock(this, "com.ibm.mq.ese.intercept.ConnectionContext", "containsQopNoneMQPUT1(String)");
/*     */       }
/*     */       
/* 325 */       this.noProtPut1DestsLock.readLock().unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void storeQopNoneMQPUT1(String name) {
/* 336 */     if (Trace.isOn) {
/* 337 */       Trace.entry(this, "com.ibm.mq.ese.intercept.ConnectionContext", "storeQopNoneMQPUT1(String)", new Object[] { name });
/*     */     }
/*     */     
/* 340 */     this.noProtPut1DestsLock.writeLock().lock();
/*     */     try {
/* 342 */       this.noProtPut1Dests.add(name);
/*     */     } finally {
/*     */       
/* 345 */       if (Trace.isOn) {
/* 346 */         Trace.finallyBlock(this, "com.ibm.mq.ese.intercept.ConnectionContext", "storeQopNoneMQPUT1(String)");
/*     */       }
/*     */       
/* 349 */       this.noProtPut1DestsLock.writeLock().unlock();
/*     */     } 
/* 351 */     if (Trace.isOn) {
/* 352 */       Trace.exit(this, "com.ibm.mq.ese.intercept.ConnectionContext", "storeQopNoneMQPUT1(String)");
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
/*     */   public void removeQopNoneMQPUT1(String name) {
/* 364 */     if (Trace.isOn) {
/* 365 */       Trace.entry(this, "com.ibm.mq.ese.intercept.ConnectionContext", "removeQopNoneMQPUT1(String)", new Object[] { name });
/*     */     }
/*     */     
/* 368 */     this.noProtPut1DestsLock.writeLock().lock();
/*     */     try {
/* 370 */       this.noProtPut1Dests.remove(name);
/*     */     } finally {
/*     */       
/* 373 */       if (Trace.isOn) {
/* 374 */         Trace.finallyBlock(this, "com.ibm.mq.ese.intercept.ConnectionContext", "removeQopNoneMQPUT1(String)");
/*     */       }
/*     */       
/* 377 */       this.noProtPut1DestsLock.writeLock().unlock();
/*     */     } 
/* 379 */     if (Trace.isOn) {
/* 380 */       Trace.exit(this, "com.ibm.mq.ese.intercept.ConnectionContext", "removeQopNoneMQPUT1(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearQopNoneMQPUT1() {
/* 389 */     if (Trace.isOn) {
/* 390 */       Trace.entry(this, "com.ibm.mq.ese.intercept.ConnectionContext", "clearQopNoneMQPUT1()");
/*     */     }
/* 392 */     this.noProtPut1DestsLock.writeLock().lock();
/*     */     try {
/* 394 */       this.noProtPut1Dests.clear();
/*     */     } finally {
/*     */       
/* 397 */       if (Trace.isOn) {
/* 398 */         Trace.finallyBlock(this, "com.ibm.mq.ese.intercept.ConnectionContext", "clearQopNoneMQPUT1()");
/*     */       }
/*     */       
/* 401 */       this.noProtPut1DestsLock.writeLock().unlock();
/*     */     } 
/* 403 */     if (Trace.isOn)
/* 404 */       Trace.exit(this, "com.ibm.mq.ese.intercept.ConnectionContext", "clearQopNoneMQPUT1()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\ConnectionContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */