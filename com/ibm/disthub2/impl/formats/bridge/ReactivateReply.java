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
/*     */ public final class ReactivateReply
/*     */   extends ControlMessageBody
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  66 */   private static final DebugObject debug = new DebugObject("ReactivateReply");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ReactivateReply create() {
/*  73 */     Jgram jg = new Jgram(10);
/*  74 */     SingleHopControl msg = (SingleHopControl)jg.getPayload();
/*  75 */     ReactivateReply ans = (ReactivateReply)msg.setBody(23);
/*  76 */     ans.setTmins(null);
/*  77 */     return ans;
/*     */   }
/*     */ 
/*     */   
/*     */   ReactivateReply(int type, MessageDataHandle cursor, SingleHopControl msg) {
/*  82 */     super(type, cursor, msg);
/*     */   }
/*     */   
/*     */   public String getReconnId() {
/*  86 */     return this.cursor.getString(79);
/*     */   }
/*     */   
/*     */   public void setReconnId(String val) {
/*  90 */     this.cursor.setString(79, val);
/*     */   }
/*     */   
/*     */   public int getSubId() {
/*  94 */     return this.cursor.getInt(80);
/*     */   }
/*     */   
/*     */   public void setSubId(int val) {
/*  98 */     this.cursor.setInt(80, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hashtable getTmins() {
/* 106 */     Hashtable<Object, Object> ans = new Hashtable<>();
/* 107 */     MessageBodyHandle[] tminsTab = this.cursor.getTable(81);
/* 108 */     for (int i = 0; i < tminsTab.length; i++) {
/* 109 */       long pid = tminsTab[i].getLong(0);
/* 110 */       long ts = tminsTab[i].getLong(1);
/* 111 */       ans.put(Long.valueOf(pid), Long.valueOf(ts));
/*     */     } 
/*     */     
/* 114 */     return ans;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTmins(Hashtable tss) {
/* 121 */     MessageBodyHandle[] tminsTab = (tss != null && tss.size() != 0) ? new MessageBodyHandle[tss.size()] : null;
/*     */     
/* 123 */     if (tss != null) {
/* 124 */       Enumeration<Long> pes = tss.keys();
/* 125 */       int i = 0;
/* 126 */       while (pes.hasMoreElements()) {
/* 127 */         Long pe = pes.nextElement();
/* 128 */         long stamp = ((Long)tss.get(pe)).longValue();
/* 129 */         tminsTab[i] = this.cursor.newTableRow(81);
/* 130 */         tminsTab[i].setLong(0, pe.longValue());
/* 131 */         tminsTab[i].setLong(1, stamp);
/* 132 */         i++;
/*     */       } 
/*     */     } 
/* 135 */     this.cursor.setTable(81, tminsTab);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 139 */     String rslt = this.cursor.getInt(80) + ",[";
/* 140 */     Hashtable tmins = getTmins();
/* 141 */     Enumeration<Long> pes = tmins.keys();
/* 142 */     while (pes.hasMoreElements()) {
/* 143 */       Long pe = pes.nextElement();
/* 144 */       Long ts = (Long)tmins.get(pe);
/* 145 */       rslt = rslt + "(" + pe + "," + ts + ")";
/*     */     } 
/* 147 */     rslt = rslt + "]";
/* 148 */     return rslt;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\ReactivateReply.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */