/*     */ package com.ibm.disthub2.impl.client;
/*     */ 
/*     */ import com.ibm.disthub2.client.Topic;
/*     */ import com.ibm.disthub2.impl.util.StampPair;
/*     */ import com.ibm.disthub2.impl.util.VectorClock;
/*     */ import com.ibm.disthub2.spi.ExceptionConstants;
/*     */ import com.ibm.disthub2.spi.LogConstants;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SubscriptionInfo
/*     */   implements LogConstants, ExceptionConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  73 */   private static final DebugObject debug = new DebugObject("SubscriptionInfo");
/*     */   
/*     */   public String reconnId;
/*     */   
/*     */   public int subid;
/*  78 */   public String topic = "t";
/*     */   public Topic rawtopic;
/*  80 */   public String selector = "s";
/*     */   
/*     */   public int subMode;
/*  83 */   public String appName = null;
/*     */   
/*     */   public boolean isJMSdurable = false;
/*     */   public boolean nonDurable = true;
/*  87 */   public Object tsi = null;
/*     */   public boolean active = false;
/*  89 */   public int matchIndex = 0;
/*     */   
/*  91 */   public VectorClock Tmin = new VectorClock();
/*  92 */   public VectorClock Delivered = new VectorClock();
/*     */   
/*  94 */   public Hashtable CatchupDone = new Hashtable<>();
/*     */   
/*  96 */   public MessageImpl latest = null;
/*     */   
/*     */   public boolean acked = true;
/*     */   
/*     */   public boolean multicastEnabled = false;
/*     */   
/*     */   public boolean multicastReliable = false;
/*     */   
/*     */   public Object[][] parsedTopic;
/*     */   
/*     */   public Object parsedSelector;
/*     */ 
/*     */   
/*     */   public SubscriptionInfo(String id) {
/* 110 */     this.reconnId = id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SubscriptionInfo() {}
/*     */ 
/*     */   
/*     */   public boolean greaterThanTmin(MessageImpl msg) {
/*     */     long p;
/*     */     long t;
/* 121 */     if (msg.silenceMsg || msg.gapMsg) {
/* 122 */       p = msg.gsPub;
/* 123 */       t = msg.gsTic;
/*     */     } else {
/*     */       
/* 126 */       p = msg.getPubendID();
/* 127 */       t = msg.getStamp();
/*     */     } 
/* 129 */     return (t > this.Tmin.get(Long.valueOf(p)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void advanceTmin(MessageImpl msg) {
/*     */     long p, t;
/* 137 */     if (msg.silenceMsg || msg.gapMsg) {
/* 138 */       p = msg.gsPub;
/* 139 */       t = msg.gsTic;
/*     */     } else {
/*     */       
/* 142 */       p = msg.getPubendID();
/* 143 */       t = msg.getStamp();
/*     */     } 
/* 145 */     Long pid = Long.valueOf(p);
/* 146 */     if (t > this.Tmin.get(pid)) {
/* 147 */       this.Tmin.set(pid, t);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean advanceDelivered(MessageImpl msg) {
/*     */     long p, t;
/* 156 */     if (msg.silenceMsg || msg.gapMsg) {
/* 157 */       p = msg.gsPub;
/* 158 */       t = msg.gsTic;
/*     */     } else {
/*     */       
/* 161 */       p = msg.getPubendID();
/* 162 */       t = msg.getStamp();
/*     */     } 
/* 164 */     Long pid = Long.valueOf(p);
/* 165 */     if (t > this.Delivered.get(pid)) {
/* 166 */       this.Delivered.set(pid, t);
/* 167 */       return true;
/*     */     } 
/*     */     
/* 170 */     return false;
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
/*     */   public void initializeCatchup() {
/* 182 */     Enumeration<StampPair> dStamps = this.Delivered.allElements();
/* 183 */     while (dStamps.hasMoreElements()) {
/*     */       
/* 185 */       StampPair dsp = dStamps.nextElement();
/* 186 */       Long pid = Long.valueOf(dsp.pid);
/* 187 */       if (this.Tmin.get(pid) > dsp.stamp) {
/* 188 */         changeCatchup(pid, false);
/*     */         continue;
/*     */       } 
/* 191 */       changeCatchup(pid, true);
/*     */     } 
/*     */ 
/*     */     
/* 195 */     Enumeration<StampPair> tStamps = this.Tmin.allElements();
/* 196 */     while (tStamps.hasMoreElements()) {
/*     */       
/* 198 */       StampPair tsp = tStamps.nextElement();
/* 199 */       Long pid = Long.valueOf(tsp.pid);
/* 200 */       if (tsp.stamp > this.Delivered.get(pid)) {
/* 201 */         changeCatchup(pid, false);
/*     */         continue;
/*     */       } 
/* 204 */       changeCatchup(pid, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeCatchup(Long pubend, boolean state) {
/* 214 */     Boolean flag = Boolean.valueOf(state);
/* 215 */     this.CatchupDone.put(pubend, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCatchup(long pubend) {
/* 222 */     Long pid = Long.valueOf(pubend);
/* 223 */     Boolean flag = (Boolean)this.CatchupDone.get(pid);
/* 224 */     if (flag != null) {
/* 225 */       return flag.booleanValue();
/*     */     }
/*     */     
/* 228 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCatchupEmpty() {
/* 234 */     return this.CatchupDone.isEmpty();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 238 */     return "\nTmins= " + this.Tmin.toString() + " \nDelivered= " + this.Delivered
/* 239 */       .toString() + " \nCatchup= " + this.CatchupDone
/* 240 */       .toString() + " \n";
/*     */   }
/*     */   
/*     */   public boolean isMulticastEnabled() {
/* 244 */     return this.multicastEnabled;
/*     */   }
/*     */   
/*     */   public void setMulticastEnabled(boolean multicastEnabled) {
/* 248 */     this.multicastEnabled = multicastEnabled;
/*     */   }
/*     */   
/*     */   public boolean isMulticastReliable() {
/* 252 */     return this.multicastReliable;
/*     */   }
/*     */   
/*     */   public void setMulticastReliable(boolean multicastReliable) {
/* 256 */     this.multicastReliable = multicastReliable;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\client\SubscriptionInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */