/*     */ package com.ibm.mq.ese.intercept;
/*     */ 
/*     */ import com.ibm.mq.constants.QmgrSplCapability;
/*     */ import com.ibm.mq.ese.util.SystemUtils;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmqiContextContainer
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiContextContainer.java";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.ese.intercept.JmqiContextContainer", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiContextContainer.java");
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
/*  60 */   private final ConcurrentHashMap<Hconn, ConnectionContext> conninfo = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   private Map<String, TemporaryQueueInfo> tempQueueInfos = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   private Object tempQueueInfosLock = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   private Boolean cryptoCapable = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionContext newContext(QmgrSplCapability capability, Hconn hconn, String name, String dlqName) {
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "newContext(final QmgrSplCapability,final Hconn,String,String)", new Object[] { capability, hconn, name, dlqName });
/*     */     }
/*     */ 
/*     */     
/*  99 */     ConnectionContext context = new ConnectionContext(hconn, name, dlqName, capability);
/*     */     
/* 101 */     String noput1Cache = SystemUtils.getEnv("MQS_NO_PUT1_QOP_CACHE", null);
/* 102 */     context.setCachePut1Dests((noput1Cache == null));
/*     */     
/* 104 */     ConnectionContext oldContext = this.conninfo.putIfAbsent(hconn, context);
/* 105 */     if (oldContext != null) {
/* 106 */       context = oldContext;
/*     */     }
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "newContext(final QmgrSplCapability,final Hconn,String,String)", context);
/*     */     }
/*     */     
/* 112 */     return context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionContext getContext(Hconn hconn) {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "getContext(final Hconn)", new Object[] { hconn });
/*     */     }
/*     */     
/* 126 */     if (hconn == null) {
/* 127 */       if (Trace.isOn) {
/* 128 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "getContext(final Hconn)", null, 1);
/*     */       }
/*     */       
/* 131 */       return null;
/*     */     } 
/* 133 */     ConnectionContext traceRet1 = this.conninfo.get(hconn);
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "getContext(final Hconn)", traceRet1, 2);
/*     */     }
/*     */     
/* 138 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionContext removeContext(Hconn hconn) {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "removeContext(Hconn)", new Object[] { hconn });
/*     */     }
/*     */     
/* 152 */     ConnectionContext traceRet1 = this.conninfo.remove(hconn);
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "removeContext(Hconn)", traceRet1);
/*     */     }
/*     */     
/* 157 */     return traceRet1;
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
/*     */   public TemporaryQueueInfo getTemporaryQueueInfo(String objectName, String qmgrName) {
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "getTemporaryQueueInfo(String,String)", new Object[] { objectName, qmgrName });
/*     */     }
/*     */     
/* 176 */     synchronized (this.tempQueueInfosLock) {
/* 177 */       String key = buildTempQKey(objectName, qmgrName);
/* 178 */       TemporaryQueueInfo traceRet1 = this.tempQueueInfos.get(key);
/* 179 */       if (Trace.isOn) {
/* 180 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "getTemporaryQueueInfo(String,String)", traceRet1);
/*     */       }
/*     */       
/* 183 */       return traceRet1;
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
/*     */   public TemporaryQueueInfo newTempQinfo(Hconn hconn, String qmgr, SmqiObject eseModelQueue) {
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "newTempQinfo(Hconn,String,SmqiObject)", new Object[] { hconn, qmgr, eseModelQueue });
/*     */     }
/*     */     
/* 204 */     TemporaryQueueInfo temp = null;
/* 205 */     synchronized (this.tempQueueInfosLock) {
/* 206 */       String key = buildTempQKey(eseModelQueue.getResolvedName(), qmgr);
/* 207 */       if (this.tempQueueInfos.containsKey(key.toString())) {
/* 208 */         temp = this.tempQueueInfos.get(key);
/*     */       } else {
/* 210 */         temp = new TemporaryQueueInfo(hconn, qmgr, eseModelQueue);
/* 211 */         this.tempQueueInfos.put(key, temp);
/*     */       } 
/*     */     } 
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "newTempQinfo(Hconn,String,SmqiObject)", temp);
/*     */     }
/*     */     
/* 218 */     return temp;
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
/*     */   public SmqiObject getModelQueueInfo(String qmgrName, Hobj childHobj) {
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "getModelQueueInfo(String,Hobj)", new Object[] { qmgrName, childHobj });
/*     */     }
/*     */     
/* 236 */     synchronized (this.tempQueueInfosLock) {
/* 237 */       for (TemporaryQueueInfo tmp : this.tempQueueInfos.values()) {
/* 238 */         SmqiObject tmpQinfo = tmp.getModelQinfo();
/* 239 */         if (tmp.getQmgrName().equals(qmgrName) && tmp
/* 240 */           .getChildHobjs().contains(childHobj)) {
/* 241 */           if (Trace.isOn) {
/* 242 */             Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "getModelQueueInfo(String,Hobj)", tmpQinfo, 1);
/*     */           }
/*     */           
/* 245 */           return tmpQinfo;
/*     */         } 
/*     */       } 
/*     */     } 
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "getModelQueueInfo(String,Hobj)", null, 2);
/*     */     }
/*     */     
/* 253 */     return null;
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
/*     */   public TemporaryQueueInfo removeTempQinfo(Hobj hobj, String qmgrName) {
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "removeTempQinfo(Hobj,String)", new Object[] { hobj, qmgrName });
/*     */     }
/*     */     
/* 269 */     String toRemove = null;
/* 270 */     TemporaryQueueInfo ret = null;
/* 271 */     synchronized (this.tempQueueInfosLock) {
/* 272 */       for (TemporaryQueueInfo tmp : this.tempQueueInfos.values()) {
/* 273 */         SmqiObject tmpQinfo = tmp.getModelQinfo();
/* 274 */         if (tmp.getQmgrName().equals(qmgrName)) {
/* 275 */           if (hobj.equals(tmpQinfo.getHobj())) {
/* 276 */             toRemove = buildTempQKey(tmpQinfo.getResolvedName(), qmgrName);
/*     */             break;
/*     */           } 
/* 279 */           if (tmp.getChildHobjs().contains(hobj)) {
/* 280 */             tmp.getChildHobjs().remove(hobj);
/*     */           }
/*     */         } 
/*     */       } 
/* 284 */       if (toRemove != null) {
/* 285 */         ret = this.tempQueueInfos.remove(toRemove);
/*     */       }
/*     */     } 
/* 288 */     if (Trace.isOn) {
/* 289 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "removeTempQinfo(Hobj,String)", ret);
/*     */     }
/*     */     
/* 292 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String buildTempQKey(String resolvedQName, String qmgrName) {
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "buildTempQKey(String,String)", new Object[] { resolvedQName, qmgrName });
/*     */     }
/*     */     
/* 306 */     StringBuilder buffer = new StringBuilder(97);
/* 307 */     buffer.append(resolvedQName).append('@').append(qmgrName);
/* 308 */     String traceRet1 = buffer.toString();
/* 309 */     if (Trace.isOn) {
/* 310 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "buildTempQKey(String,String)", traceRet1);
/*     */     }
/*     */     
/* 313 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTempQinfoChild(String objectName, String qmgrName, Hobj child) {
/* 324 */     if (Trace.isOn) {
/* 325 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "addTempQinfoChild(String,String,Hobj)", new Object[] { objectName, qmgrName, child });
/*     */     }
/*     */     
/* 328 */     String key = buildTempQKey(objectName, qmgrName);
/* 329 */     synchronized (this.tempQueueInfosLock) {
/*     */       
/* 331 */       TemporaryQueueInfo tempqinfo = this.tempQueueInfos.get(key);
/* 332 */       if (!tempqinfo.getModelQinfo().getHobj().equals(child) && 
/* 333 */         !tempqinfo.getChildHobjs().contains(child)) {
/* 334 */         tempqinfo.addChildHobj(child);
/*     */       }
/*     */     } 
/* 337 */     if (Trace.isOn) {
/* 338 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "addTempQinfoChild(String,String,Hobj)");
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
/*     */   public int removeAllTempQinfos(Hconn hconn, String qmgrName) {
/* 351 */     if (Trace.isOn) {
/* 352 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "removeAllTempQinfos(Hconn,String)", new Object[] { hconn, qmgrName });
/*     */     }
/*     */     
/* 355 */     int count = 0;
/* 356 */     List<String> toRemove = new LinkedList<>();
/* 357 */     synchronized (this.tempQueueInfosLock) {
/* 358 */       for (TemporaryQueueInfo tmp : this.tempQueueInfos.values()) {
/* 359 */         SmqiObject tmpQinfo = tmp.getModelQinfo();
/* 360 */         if (tmp.getHconn().equals(hconn)) {
/* 361 */           toRemove.add(buildTempQKey(tmpQinfo.getResolvedName(), qmgrName));
/*     */         }
/*     */       } 
/*     */       
/* 365 */       for (String key : toRemove) {
/* 366 */         this.tempQueueInfos.remove(key);
/* 367 */         count++;
/*     */       } 
/*     */     } 
/* 370 */     if (Trace.isOn) {
/* 371 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "removeAllTempQinfos(Hconn,String)", 
/* 372 */           Integer.valueOf(count));
/*     */     }
/* 374 */     return count;
/*     */   }
/*     */   
/*     */   Map<Hconn, ConnectionContext> getContexts() {
/* 378 */     Map<Hconn, ConnectionContext> traceRet1 = new HashMap<>(this.conninfo);
/* 379 */     if (Trace.isOn) {
/* 380 */       Trace.data(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "getContexts()", "getter", traceRet1);
/*     */     }
/*     */     
/* 383 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasProtectedConn(Hconn hconn) {
/* 393 */     if (Trace.isOn) {
/* 394 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "hasProtectedConn(Hconn)", new Object[] { hconn });
/*     */     }
/*     */     
/* 397 */     ConnectionContext context = getContext(hconn);
/* 398 */     if (context == null) {
/* 399 */       if (Trace.isOn) {
/* 400 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "hasProtectedConn(Hconn)", 
/* 401 */             Boolean.valueOf(true), 1);
/*     */       }
/* 403 */       return true;
/*     */     } 
/*     */     
/* 406 */     boolean traceRet1 = context.isProtectedConn().get();
/* 407 */     if (Trace.isOn) {
/* 408 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "hasProtectedConn(Hconn)", 
/* 409 */           Boolean.valueOf(traceRet1), 2);
/*     */     }
/* 411 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCryptoCapable() {
/* 419 */     if (Trace.isOn) {
/* 420 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "isCryptoCapable()");
/*     */     }
/* 422 */     if (this.cryptoCapable == null) {
/* 423 */       if (Trace.isOn) {
/* 424 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "isCryptoCapable()", 
/* 425 */             Boolean.valueOf(false), 1);
/*     */       }
/* 427 */       return false;
/*     */     } 
/* 429 */     if (Trace.isOn) {
/* 430 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "isCryptoCapable()", 
/* 431 */           Boolean.valueOf(this.cryptoCapable.booleanValue()), 2);
/*     */     }
/* 433 */     return this.cryptoCapable.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCryptoCapable(boolean cap) {
/* 442 */     if (Trace.isOn) {
/* 443 */       Trace.data(this, "com.ibm.mq.ese.intercept.JmqiContextContainer", "setCryptoCapable(boolean)", "setter", 
/* 444 */           Boolean.valueOf(cap));
/*     */     }
/* 446 */     if (this.cryptoCapable == null)
/* 447 */       this.cryptoCapable = Boolean.valueOf(cap); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\JmqiContextContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */