/*     */ package com.ibm.msg.client.wmq.common.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.MQConsumer;
/*     */ import com.ibm.mq.jmqi.handles.Phobj;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WMQHobjCache
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQHobjCache.java";
/*     */   private HashMap<QueueCacheKey, Phobj> queueCache;
/*     */   private HashMap<Phobj, QueueCacheUsageEntry> queueCacheUsage;
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQHobjCache.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class QueueCacheKey
/*     */   {
/*     */     private int openOptions;
/*     */ 
/*     */ 
/*     */     
/*     */     private String queueName;
/*     */ 
/*     */ 
/*     */     
/*     */     private String selector;
/*     */ 
/*     */ 
/*     */     
/*     */     private QueueCacheKey(String qName, String selector, int options) {
/*  71 */       if (Trace.isOn) {
/*  72 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.QueueCacheKey", "<init>(String,String,int)", new Object[] { qName, selector, 
/*  73 */               Integer.valueOf(options) });
/*     */       }
/*  75 */       this.queueName = qName;
/*  76 */       this.selector = selector;
/*  77 */       this.openOptions = options;
/*  78 */       if (Trace.isOn) {
/*  79 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.QueueCacheKey", "<init>(String,String,int)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean equals(Object obj) {
/*  90 */       if (Trace.isOn) {
/*  91 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.QueueCacheKey", "equals(Object)", new Object[] { obj });
/*     */       }
/*     */       
/*  94 */       boolean result = true;
/*  95 */       if (obj instanceof QueueCacheKey) {
/*  96 */         QueueCacheKey other = (QueueCacheKey)obj;
/*  97 */         result &= this.queueName.equals(other.queueName);
/*     */         
/*  99 */         if (this.selector != null) {
/* 100 */           result &= this.selector.equals(other.selector);
/*     */         }
/* 102 */         else if (other.selector != null) {
/* 103 */           result &= other.selector.equals(this.selector);
/*     */         }
/* 105 */         else if (this.selector == null && other.selector == null) {
/* 106 */           int j = result & true;
/*     */         } else {
/*     */           
/* 109 */           result = false;
/*     */         } 
/* 111 */         int i = result & ((this.openOptions == other.openOptions) ? 1 : 0);
/*     */       } else {
/*     */         
/* 114 */         result = false;
/*     */       } 
/*     */       
/* 117 */       if (Trace.isOn) {
/* 118 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.QueueCacheKey", "equals(Object)", 
/* 119 */             Boolean.valueOf(result));
/*     */       }
/* 121 */       return result;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 129 */       if (Trace.isOn) {
/* 130 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.QueueCacheKey", "hashCode()");
/*     */       }
/* 132 */       int result = this.openOptions;
/*     */       
/* 134 */       if (this.selector != null) {
/* 135 */         result += this.selector.hashCode();
/*     */       }
/* 137 */       if (this.queueName != null) {
/* 138 */         result += this.queueName.hashCode();
/*     */       }
/* 140 */       if (Trace.isOn) {
/* 141 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.QueueCacheKey", "hashCode()", 
/* 142 */             Integer.valueOf(result));
/*     */       }
/* 144 */       return result;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class QueueCacheUsageEntry
/*     */   {
/*     */     private int count;
/*     */     
/*     */     private WMQHobjCache.QueueCacheKey key;
/*     */     
/*     */     private List<MQConsumer> messageListeners;
/*     */ 
/*     */     
/*     */     private QueueCacheUsageEntry(WMQHobjCache.QueueCacheKey key) {
/* 159 */       if (Trace.isOn) {
/* 160 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.QueueCacheUsageEntry", "<init>(QueueCacheKey)", new Object[] { key });
/*     */       }
/*     */       
/* 163 */       this.key = key;
/* 164 */       this.count = 1;
/* 165 */       this.messageListeners = new LinkedList<>();
/* 166 */       if (Trace.isOn) {
/* 167 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.QueueCacheUsageEntry", "<init>(QueueCacheKey)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     MQConsumer getMessageListener() {
/* 174 */       MQConsumer traceRet1 = (this.messageListeners.size() > 0) ? this.messageListeners.get(0) : null;
/* 175 */       if (Trace.isOn) {
/* 176 */         Trace.data(this, "com.ibm.msg.client.wmq.common.internal.QueueCacheUsageEntry", "getMessageListener()", "getter", traceRet1);
/*     */       }
/*     */       
/* 179 */       return traceRet1;
/*     */     }
/*     */     
/*     */     void addMessageListener(MQConsumer msgListener) {
/* 183 */       if (Trace.isOn) {
/* 184 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.QueueCacheUsageEntry", "addMessageListener(MQConsumer)", new Object[] { msgListener });
/*     */       }
/*     */       
/* 187 */       this.messageListeners.add(msgListener);
/* 188 */       if (Trace.isOn) {
/* 189 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.QueueCacheUsageEntry", "addMessageListener(MQConsumer)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     boolean removeMessageListener(MQConsumer msgListener) {
/* 196 */       if (Trace.isOn) {
/* 197 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.QueueCacheUsageEntry", "removeMessageListener(MQConsumer)", new Object[] { msgListener });
/*     */       }
/*     */       
/* 200 */       this.messageListeners.remove(msgListener);
/* 201 */       boolean empty = (this.messageListeners.size() == 0);
/* 202 */       if (Trace.isOn) {
/* 203 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.QueueCacheUsageEntry", "removeMessageListener(MQConsumer)", 
/* 204 */             Boolean.valueOf(empty));
/*     */       }
/* 206 */       return empty;
/*     */     }
/*     */     
/*     */     int countMessageListeners() {
/* 210 */       if (Trace.isOn) {
/* 211 */         Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.QueueCacheUsageEntry", "countMessageListeners()");
/*     */       }
/*     */       
/* 214 */       int traceRet1 = this.messageListeners.size();
/* 215 */       if (Trace.isOn) {
/* 216 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.QueueCacheUsageEntry", "countMessageListeners()", 
/* 217 */             Integer.valueOf(traceRet1));
/*     */       }
/* 219 */       return traceRet1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addQueue(Phobj phobj, String queueName, String selector, int options) {
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "addQueue(Phobj,String,String,int)", new Object[] { phobj, queueName, selector, 
/*     */             
/* 250 */             Integer.valueOf(options) });
/*     */     }
/*     */     
/* 253 */     initializeMaps();
/*     */ 
/*     */     
/* 256 */     QueueCacheKey key = new QueueCacheKey(queueName, selector, options);
/*     */ 
/*     */ 
/*     */     
/* 260 */     if (this.queueCache.containsKey(key) || this.queueCacheUsage.containsKey(phobj)) {
/*     */       
/* 262 */       HashMap<String, Object> info = new HashMap<>();
/* 263 */       info.put("queueCache", this.queueCache);
/* 264 */       info.put("queueCacheUsage", this.queueCacheUsage);
/* 265 */       Trace.ffst(this, "addQueueToCache()", "XN00N001", info, null);
/*     */     } 
/*     */     
/* 268 */     this.queueCache.put(key, phobj);
/* 269 */     this.queueCacheUsage.put(phobj, new QueueCacheUsageEntry(key));
/*     */     
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "addQueue(Phobj,String,String,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized void initializeMaps() {
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "initializeMaps()");
/*     */     }
/*     */ 
/*     */     
/* 284 */     if (this.queueCache == null) {
/* 285 */       this.queueCache = new HashMap<>();
/*     */     }
/* 287 */     if (this.queueCacheUsage == null) {
/* 288 */       this.queueCacheUsage = new HashMap<>();
/*     */     }
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "initializeMaps()");
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
/*     */   public synchronized Phobj getQueue(String queueName, String selector, int options) {
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "getQueue(String,String,int)", new Object[] { queueName, selector, 
/* 309 */             Integer.valueOf(options) });
/*     */     }
/*     */ 
/*     */     
/* 313 */     initializeMaps();
/*     */ 
/*     */     
/* 316 */     QueueCacheKey key = new QueueCacheKey(queueName, selector, options);
/*     */ 
/*     */     
/* 319 */     Phobj phobjTemp = this.queueCache.get(key);
/*     */ 
/*     */ 
/*     */     
/* 323 */     if (phobjTemp != null) {
/*     */       
/* 325 */       QueueCacheUsageEntry usage = this.queueCacheUsage.get(phobjTemp);
/* 326 */       if (usage == null) {
/*     */         
/* 328 */         HashMap<String, Object> info = new HashMap<>();
/* 329 */         info.put("queueCache", this.queueCache);
/* 330 */         info.put("queueCacheUsage", this.queueCacheUsage);
/* 331 */         Trace.ffst(this, "getQueueFromCache()", "XN00N003", info, null);
/*     */       }
/*     */       else {
/*     */         
/* 335 */         usage.count++;
/*     */       } 
/*     */     } 
/*     */     
/* 339 */     if (Trace.isOn) {
/* 340 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "getQueue(String,String,int)", phobjTemp);
/*     */     }
/*     */     
/* 343 */     return phobjTemp;
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
/*     */   public synchronized boolean removeQueue(Phobj phobj) {
/* 356 */     if (Trace.isOn) {
/* 357 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "removeQueue(Phobj)", new Object[] { phobj });
/*     */     }
/*     */ 
/*     */     
/* 361 */     initializeMaps();
/*     */ 
/*     */     
/* 364 */     QueueCacheUsageEntry usage = this.queueCacheUsage.get(phobj);
/*     */ 
/*     */     
/* 367 */     if (usage != null) {
/*     */       
/* 369 */       usage.count--;
/* 370 */       if (usage.count == 0) {
/*     */ 
/*     */         
/* 373 */         usage = this.queueCacheUsage.remove(phobj);
/*     */         
/* 375 */         this.queueCache.remove(usage.key);
/*     */ 
/*     */ 
/*     */         
/* 379 */         if (Trace.isOn) {
/* 380 */           Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "removeQueue(Phobj)", 
/* 381 */               Boolean.valueOf(true), 1);
/*     */         }
/* 383 */         return true;
/*     */       } 
/* 385 */       if (usage.count < 0)
/*     */       {
/* 387 */         HashMap<String, Object> info = new HashMap<>();
/* 388 */         info.put("usage count", Integer.valueOf(usage.count));
/* 389 */         Trace.ffst(this, "removeQueueFromCache()", "XN00N004", info, null);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 394 */       HashMap<String, Object> info = new HashMap<>();
/* 395 */       info.put("queueCacheUsage", this.queueCacheUsage);
/* 396 */       info.put("usage entry", "null!!!!!");
/* 397 */       Trace.ffst(this, "removeQueueFromCache()", "XN00N005", info, null);
/*     */     } 
/*     */     
/* 400 */     if (Trace.isOn) {
/* 401 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "removeQueue(Phobj)", 
/* 402 */           Boolean.valueOf(false), 2);
/*     */     }
/* 404 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized int countQueue(Phobj phobj) {
/* 414 */     if (Trace.isOn) {
/* 415 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "countQueue(Phobj)", new Object[] { phobj });
/*     */     }
/*     */ 
/*     */     
/* 419 */     int count = 0;
/*     */ 
/*     */     
/* 422 */     if (this.queueCache != null && this.queueCacheUsage != null) {
/* 423 */       QueueCacheUsageEntry usage = this.queueCacheUsage.get(phobj);
/* 424 */       if (usage != null) {
/* 425 */         count = usage.count;
/*     */       }
/*     */     } 
/*     */     
/* 429 */     if (Trace.isOn) {
/* 430 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "countQueue(Phobj)", 
/* 431 */           Integer.valueOf(count));
/*     */     }
/* 433 */     return count;
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
/*     */   public synchronized void attachMessageListenerToQueue(Phobj phobj, MQConsumer msgListener) {
/* 448 */     if (Trace.isOn) {
/* 449 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "attachMessageListenerToQueue(Phobj,MQConsumer)", new Object[] { phobj, msgListener });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 455 */     if (this.queueCache == null || this.queueCacheUsage == null) {
/* 456 */       HashMap<String, Object> info = new HashMap<>();
/* 457 */       info.put("cache", "null!!!!!");
/* 458 */       Trace.ffst(this, "attachMessageListenerToQueue()", "XM00B001", info, null);
/*     */     } 
/*     */ 
/*     */     
/* 462 */     QueueCacheUsageEntry usage = this.queueCacheUsage.get(phobj);
/*     */ 
/*     */ 
/*     */     
/* 466 */     if (usage != null) {
/* 467 */       usage.addMessageListener(msgListener);
/*     */     }
/*     */     else {
/*     */       
/* 471 */       HashMap<String, Object> info = new HashMap<>();
/* 472 */       info.put("queueCacheUsage", this.queueCacheUsage);
/* 473 */       info.put("usage entry", "null!!!!!");
/* 474 */       Trace.ffst(this, "attachMessageListenerToQueue()", "XM00B002", info, null);
/*     */     } 
/*     */     
/* 477 */     if (Trace.isOn) {
/* 478 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "attachMessageListenerToQueue(Phobj,MQConsumer)");
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
/*     */   public synchronized boolean detachMessageListenerFromQueue(Phobj phobj, MQConsumer msgListener) {
/* 494 */     if (Trace.isOn) {
/* 495 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "detachMessageListenerFromQueue(Phobj,MQConsumer)", new Object[] { phobj, msgListener });
/*     */     }
/*     */ 
/*     */     
/* 499 */     boolean lastMessageListener = true;
/*     */ 
/*     */ 
/*     */     
/* 503 */     if (this.queueCache == null || this.queueCacheUsage == null) {
/* 504 */       HashMap<String, Object> info = new HashMap<>();
/* 505 */       info.put("cache", "null!!!!!");
/* 506 */       Trace.ffst(this, "detachMessageListenerFromQueue()", "XM00B003", info, null);
/* 507 */       if (Trace.isOn) {
/* 508 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "detachMessageListenerFromQueue(Phobj,MQConsumer)", 
/* 509 */             Boolean.valueOf(lastMessageListener), 1);
/*     */       }
/*     */       
/* 512 */       return lastMessageListener;
/*     */     } 
/*     */ 
/*     */     
/* 516 */     QueueCacheUsageEntry usage = this.queueCacheUsage.get(phobj);
/*     */ 
/*     */ 
/*     */     
/* 520 */     if (usage != null) {
/* 521 */       lastMessageListener = usage.removeMessageListener(msgListener);
/*     */     }
/*     */     else {
/*     */       
/* 525 */       HashMap<String, Object> info = new HashMap<>();
/* 526 */       info.put("queueCacheUsage", this.queueCacheUsage);
/* 527 */       info.put("usage entry", "null!!!!!");
/* 528 */       Trace.ffst(this, "detachMessageListenerFromQueue()", "XM00B004", info, null);
/*     */     } 
/*     */     
/* 531 */     if (Trace.isOn) {
/* 532 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "detachMessageListenerFromQueue(Phobj,MQConsumer)", 
/* 533 */           Boolean.valueOf(lastMessageListener), 2);
/*     */     }
/*     */     
/* 536 */     return lastMessageListener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized MQConsumer getMessageListenersForQueue(Phobj phobj) {
/* 544 */     if (Trace.isOn) {
/* 545 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "getMessageListenersForQueue(Phobj)", new Object[] { phobj });
/*     */     }
/*     */ 
/*     */     
/* 549 */     MQConsumer callback = null;
/*     */ 
/*     */     
/* 552 */     if (this.queueCache == null || this.queueCacheUsage == null) {
/* 553 */       if (Trace.isOn) {
/* 554 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "getMessageListenersForQueue(Phobj)", callback, 1);
/*     */       }
/*     */       
/* 557 */       return callback;
/*     */     } 
/*     */ 
/*     */     
/* 561 */     QueueCacheUsageEntry usage = this.queueCacheUsage.get(phobj);
/*     */ 
/*     */     
/* 564 */     if (usage != null) {
/* 565 */       callback = usage.getMessageListener();
/*     */     }
/*     */     
/* 568 */     if (Trace.isOn) {
/* 569 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "getMessageListenersForQueue(Phobj)", callback, 2);
/*     */     }
/*     */     
/* 572 */     return callback;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized int countMessageListenersForQueue(Phobj phobj) {
/* 580 */     if (Trace.isOn) {
/* 581 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "countMessageListenersForQueue(Phobj)", new Object[] { phobj });
/*     */     }
/*     */ 
/*     */     
/* 585 */     int count = 0;
/*     */ 
/*     */     
/* 588 */     if (this.queueCache == null || this.queueCacheUsage == null) {
/* 589 */       if (Trace.isOn) {
/* 590 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "countMessageListenersForQueue(Phobj)", 
/* 591 */             Integer.valueOf(count), 1);
/*     */       }
/* 593 */       return count;
/*     */     } 
/*     */ 
/*     */     
/* 597 */     QueueCacheUsageEntry usage = this.queueCacheUsage.get(phobj);
/*     */ 
/*     */     
/* 600 */     if (usage != null) {
/* 601 */       count = usage.countMessageListeners();
/*     */     }
/*     */     
/* 604 */     if (Trace.isOn) {
/* 605 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQHobjCache", "countMessageListenersForQueue(Phobj)", 
/* 606 */           Integer.valueOf(count), 2);
/*     */     }
/* 608 */     return count;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\WMQHobjCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */