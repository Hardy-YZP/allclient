/*     */ package com.ibm.disthub2.impl.formats.bridge;
/*     */ 
/*     */ import com.ibm.disthub2.client.MessageBodyHandle;
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.client.MessageImpl;
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
/*     */ public final class ReactivateReq
/*     */   extends ControlMessageBody
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  67 */   private static final DebugObject debug = new DebugObject("ReactivateReq");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ControlMessageBody create() {
/*  74 */     Jgram jg = new Jgram(10);
/*  75 */     SingleHopControl msg = (SingleHopControl)jg.getPayload();
/*  76 */     ReactivateReq ans = (ReactivateReq)msg.setBody(22);
/*  77 */     return ans;
/*     */   }
/*     */ 
/*     */   
/*     */   ReactivateReq(int type, MessageDataHandle cursor, SingleHopControl msg) {
/*  82 */     super(type, cursor, msg);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getReconnId() {
/*  87 */     String reconnId = this.cursor.getString(75);
/*  88 */     return reconnId;
/*     */   }
/*     */   
/*     */   public void setReconnId(String val) {
/*  92 */     this.cursor.setString(75, val);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSubject() {
/*  97 */     String topic = this.cursor.getString(76);
/*  98 */     if (getJgram().areTopicsPrefixed()) {
/*  99 */       topic = MessageImpl.toDefaultInternalTopic(topic);
/*     */     }
/* 101 */     return topic;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSubject(String val) {
/* 106 */     if (getJgram().areTopicsPrefixed()) {
/* 107 */       val = MessageImpl.toDefaultExternalTopic(val);
/*     */     }
/* 109 */     this.cursor.setString(76, val);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQuery() {
/* 114 */     return this.cursor.getString(77);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setQuery(String val) {
/* 119 */     this.cursor.setString(77, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hashtable getStartTime() {
/* 127 */     Hashtable<Object, Object> ans = new Hashtable<>();
/* 128 */     MessageBodyHandle[] startTimeTab = this.cursor.getTable(78);
/* 129 */     for (int i = 0; i < startTimeTab.length; i++) {
/* 130 */       long pid = startTimeTab[i].getLong(0);
/* 131 */       long ts = startTimeTab[i].getLong(1);
/* 132 */       ans.put(Long.valueOf(pid), Long.valueOf(ts));
/*     */     } 
/*     */     
/* 135 */     return ans;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartTime(Hashtable tss) {
/* 142 */     MessageBodyHandle[] startTimeTab = (tss != null && tss.size() != 0) ? new MessageBodyHandle[tss.size()] : null;
/*     */     
/* 144 */     if (tss != null) {
/* 145 */       Enumeration<Long> pes = tss.keys();
/* 146 */       int i = 0;
/* 147 */       while (pes.hasMoreElements()) {
/* 148 */         Long pe = pes.nextElement();
/* 149 */         long stamp = ((Long)tss.get(pe)).longValue();
/* 150 */         startTimeTab[i].setLong(0, pe.longValue());
/* 151 */         startTimeTab[i].setLong(1, stamp);
/* 152 */         i++;
/*     */       } 
/*     */     } 
/* 155 */     this.cursor.setTable(78, startTimeTab);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\ReactivateReq.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */