/*     */ package com.ibm.disthub2.impl.formats.bridge;
/*     */ 
/*     */ import com.ibm.disthub2.client.MessageBodyHandle;
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.formats.MessageDataHandle;
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
/*     */ public final class ReleaseReq
/*     */   extends ControlMessageBody
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  66 */   private static final DebugObject debug = new DebugObject("ReleaseReq");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ReleaseReq create() {
/*  73 */     Jgram jg = new Jgram(10);
/*  74 */     SingleHopControl msg = (SingleHopControl)jg.getPayload();
/*  75 */     ReleaseReq ans = (ReleaseReq)msg.setBody(26);
/*     */ 
/*     */     
/*  78 */     ans.setReplyExpected(false);
/*  79 */     return ans;
/*     */   }
/*     */ 
/*     */   
/*     */   ReleaseReq(int type, MessageDataHandle cursor, SingleHopControl msg) {
/*  84 */     super(type, cursor, msg);
/*     */   }
/*     */   
/*     */   public void setReconnId(String val) {
/*  88 */     this.cursor.setString(85, val);
/*     */   }
/*     */   
/*     */   public String getReconnId() {
/*  92 */     return this.cursor.getString(85);
/*     */   }
/*     */   
/*     */   public void setReplyExpected(boolean val) {
/*  96 */     this.cursor.setBoolean(86, val);
/*     */   }
/*     */   
/*     */   public boolean getReplyExpected() {
/* 100 */     return this.cursor.getBoolean(86);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hashtable getReleaseTime() {
/* 108 */     Hashtable<Object, Object> ans = new Hashtable<>();
/* 109 */     MessageBodyHandle[] releaseTimeTab = this.cursor.getTable(87);
/* 110 */     for (int i = 0; i < releaseTimeTab.length; i++) {
/* 111 */       long pid = releaseTimeTab[i].getLong(0);
/* 112 */       long ts = releaseTimeTab[i].getLong(1);
/* 113 */       ans.put(Long.valueOf(pid), Long.valueOf(ts));
/*     */     } 
/*     */     
/* 116 */     return ans;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReleaseTime(Hashtable tss) {
/* 123 */     MessageBodyHandle[] releaseTimeTab = (tss != null && tss.size() != 0) ? new MessageBodyHandle[tss.size()] : null;
/*     */     
/* 125 */     if (tss != null) {
/* 126 */       Enumeration<Long> pes = tss.keys();
/* 127 */       int i = 0;
/* 128 */       while (pes.hasMoreElements()) {
/* 129 */         Long pe = pes.nextElement();
/* 130 */         long stamp = ((Long)tss.get(pe)).longValue();
/* 131 */         releaseTimeTab[i].setLong(0, pe.longValue());
/* 132 */         releaseTimeTab[i].setLong(1, stamp);
/*     */       } 
/*     */     } 
/* 135 */     this.cursor.setTable(87, releaseTimeTab);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 139 */     String rslt = getReconnId();
/* 140 */     rslt = rslt + "," + getReplyExpected() + "(";
/* 141 */     MessageBodyHandle[] releaseTimeTab = this.cursor.getTable(87);
/* 142 */     for (int i = 0; i < releaseTimeTab.length; i++) {
/* 143 */       long pid = releaseTimeTab[i].getLong(0);
/* 144 */       long ts = releaseTimeTab[i].getLong(1);
/* 145 */       rslt = rslt + "[" + pid + "," + ts + "]";
/*     */     } 
/* 147 */     rslt = rslt + ")";
/* 148 */     return rslt;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\ReleaseReq.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */