/*     */ package com.ibm.disthub2.impl.client;
/*     */ 
/*     */ import com.ibm.disthub2.client.Event;
/*     */ import com.ibm.disthub2.client.Listener;
/*     */ import com.ibm.disthub2.client.SubscriptionListener;
/*     */ import com.ibm.disthub2.impl.util.Assert;
/*     */ import com.ibm.disthub2.spi.ExceptionConstants;
/*     */ import com.ibm.disthub2.spi.LogConstants;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Dispatcher
/*     */   implements Runnable, LogConstants, ExceptionConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  53 */   private static final DebugObject debug = new DebugObject("Dispatcher");
/*     */ 
/*     */   
/*     */   public Listener listener;
/*     */ 
/*     */   
/*     */   public SubscriptionListener sublistener;
/*     */ 
/*     */   
/*     */   public boolean durable = false;
/*     */ 
/*     */   
/*     */   private ConnectorImpl conn;
/*     */   
/*  67 */   private MessageImpl[] queue = new MessageImpl[16];
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int maxQueuedMessages = 1024;
/*     */ 
/*     */   
/*  74 */   private int consumed = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int enq;
/*     */ 
/*     */ 
/*     */   
/*     */   int deq;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dispatcher(Listener listener, ConnectorImpl conn) {
/*  89 */     this.listener = listener;
/*  90 */     this.conn = conn;
/*  91 */     this.durable = false;
/*  92 */     conn.setMaxWindowSize(1024);
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
/*     */   public Dispatcher(SubscriptionListener listener, ConnectorImpl conn) {
/* 104 */     this.sublistener = listener;
/* 105 */     this.conn = conn;
/* 106 */     this.durable = true;
/* 107 */     conn.setMaxWindowSize(1024);
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
/*     */   public synchronized void enqueue(MessageImpl msg) {
/* 119 */     this.queue[this.enq++] = msg;
/* 120 */     if (this.enq == this.queue.length)
/* 121 */       this.enq = 0; 
/* 122 */     if (this.enq == this.deq) {
/* 123 */       int len = this.queue.length;
/*     */ 
/*     */ 
/*     */       
/* 127 */       if ((BaseConfig.getBaseConfig()).ENABLE_CLIENT_FLOW_CONTROL && len > 1024) {
/* 128 */         Assert.failure("queue already reached max capacity, flow control failed");
/*     */       }
/* 130 */       MessageImpl[] newQueue = new MessageImpl[len * 2];
/* 131 */       System.arraycopy(this.queue, this.deq, newQueue, this.deq, len - this.deq);
/* 132 */       System.arraycopy(this.queue, 0, newQueue, len, this.enq);
/* 133 */       this.enq += len;
/* 134 */       this.queue = newQueue;
/*     */     } 
/* 136 */     notify();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void shutdown() {
/* 146 */     this.queue = null;
/* 147 */     notify();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void purgeQ(SubscriptionInfo subInfo) throws IOException {
/* 154 */     int n = 0;
/* 155 */     int i = this.deq;
/* 156 */     int j = this.deq;
/*     */     do {
/* 158 */       MessageImpl msg = this.queue[i];
/* 159 */       if (msg == null)
/* 160 */         break;  this.queue[i] = null;
/* 161 */       if (msg.subInfo == subInfo) {
/* 162 */         n++;
/*     */       } else {
/* 164 */         this.queue[j++] = msg;
/* 165 */         if (j == this.queue.length) j = 0; 
/*     */       } 
/* 167 */       i++;
/* 168 */       if (i != this.queue.length) continue;  i = 0;
/* 169 */     } while (i != this.enq);
/*     */     
/* 171 */     this.enq = j;
/* 172 */     if (n > 0) {
/* 173 */       int numMsgs = (this.enq - this.deq + this.enq - this.deq < 0) ? this.queue.length : 0;
/* 174 */       int freeSpace = 1024 - numMsgs;
/* 175 */       this.conn.advanceRecvWindow(freeSpace);
/*     */     } 
/* 177 */     notify();
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 182 */     boolean empty = false;
/*     */     while (true) {
/*     */       MessageImpl msg;
/* 185 */       synchronized (this) {
/* 186 */         while (this.enq == this.deq && this.queue != null) {
/*     */           try {
/* 188 */             wait();
/* 189 */           } catch (InterruptedException interruptedException) {}
/*     */         } 
/* 191 */         if (this.queue == null)
/*     */           return; 
/* 193 */         msg = this.queue[this.deq];
/* 194 */         this.queue[this.deq++] = null;
/* 195 */         if (this.deq == this.queue.length)
/* 196 */           this.deq = 0; 
/* 197 */         if (this.enq == this.deq) empty = true; 
/*     */       } 
/* 199 */       if (this.durable) {
/* 200 */         boolean persistent; if (msg == null) {
/* 201 */           this.sublistener.onMessage(null, null);
/*     */           return;
/*     */         } 
/* 204 */         SubscriptionInfo subInfo = msg.subInfo;
/* 205 */         Assert.condition((subInfo != null));
/*     */         
/* 207 */         if (msg.gapMsg || msg.silenceMsg) {
/* 208 */           persistent = true;
/*     */         } else {
/*     */           
/* 211 */           persistent = msg.getPersistent();
/*     */         } 
/* 213 */         if (subInfo.subMode == 1) {
/* 214 */           if (subInfo.active) {
/* 215 */             this.sublistener.onMessage(msg, subInfo.subid);
/*     */           }
/* 217 */         } else if (subInfo.subMode == 4) {
/* 218 */           boolean deliver = true;
/* 219 */           if (persistent) {
/* 220 */             synchronized (subInfo) {
/* 221 */               if (!msg.targetted) {
/* 222 */                 deliver = subInfo.greaterThanTmin(msg);
/*     */               }
/* 224 */               if (deliver) {
/* 225 */                 subInfo.advanceDelivered(msg);
/* 226 */                 subInfo.advanceTmin(msg);
/* 227 */                 if (!msg.silenceMsg)
/* 228 */                   subInfo.latest = msg; 
/*     */               } 
/*     */             } 
/* 231 */             if (deliver) {
/* 232 */               if (subInfo.active) {
/* 233 */                 if (msg.gapMsg) {
/* 234 */                   this.sublistener.gap((Event)msg, subInfo.reconnId);
/*     */                 } else {
/* 236 */                   this.sublistener.onMessage(msg, subInfo.reconnId);
/*     */                 }
/*     */               
/* 239 */               } else if (debug.debugIt(16)) {
/* 240 */                 debug.debug(-153415734321212L, "run", " delivery not done, reason=message delivery suppressed");
/*     */               
/*     */               }
/*     */ 
/*     */             
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 252 */           boolean deliver = true;
/* 253 */           synchronized (subInfo) {
/* 254 */             if (msg.getPersistent()) {
/* 255 */               deliver = subInfo.greaterThanTmin(msg);
/* 256 */               if (deliver) {
/* 257 */                 subInfo.advanceTmin(msg);
/*     */               }
/*     */             } else {
/* 260 */               deliver = (subInfo.subMode == 2);
/*     */             } 
/*     */           } 
/* 263 */           if (deliver) {
/* 264 */             this.sublistener.onMessage(msg, subInfo.subid);
/*     */           }
/*     */         } 
/*     */       } else {
/* 268 */         if (msg == null) {
/* 269 */           this.listener.onMessage(null);
/*     */           return;
/*     */         } 
/* 272 */         SubscriptionInfo subInfo = msg.subInfo;
/* 273 */         Assert.condition((subInfo == null));
/* 274 */         this.listener.onMessage(msg);
/*     */       } 
/*     */       
/* 277 */       this.consumed++;
/* 278 */       if (empty || this.consumed * 3 >= 1024)
/*     */         try {
/* 280 */           synchronized (this) {
/* 281 */             int numMsgs = this.enq - this.deq + ((this.enq - this.deq < 0) ? this.queue.length : 0);
/* 282 */             int freeSpace = 1024 - numMsgs;
/* 283 */             this.conn.advanceRecvWindow(freeSpace);
/*     */           } 
/* 285 */           this.consumed = 0;
/* 286 */           empty = false;
/* 287 */         } catch (IOException iOException) {} 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\client\Dispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */