/*     */ package com.ibm.disthub2.impl.formats.bridge;
/*     */ 
/*     */ import com.ibm.disthub2.client.MessageBodyHandle;
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.formats.MessageDataHandle;
/*     */ import com.ibm.disthub2.impl.multicast.MulticastTopic;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class MulticastTopicsUpdate
/*     */   extends ControlMessageBody
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  41 */   private static final DebugObject debug = new DebugObject("MulticastTopicsUpdate");
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean ipv6 = false;
/*     */ 
/*     */   
/*     */   private MulticastTopic[] multicastTopics;
/*     */ 
/*     */ 
/*     */   
/*     */   public static MulticastTopicsUpdate create() {
/*  53 */     Jgram jg = new Jgram(10);
/*  54 */     SingleHopControl msg = (SingleHopControl)jg.getPayload();
/*  55 */     MulticastTopicsUpdate multicastTopicsUpdate = (MulticastTopicsUpdate)msg.setBody(34);
/*  56 */     return multicastTopicsUpdate;
/*     */   }
/*     */ 
/*     */   
/*     */   MulticastTopicsUpdate(int type, MessageDataHandle cursor, SingleHopControl msg) {
/*  61 */     super(type, cursor, msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void rebuildP() {
/*  68 */     int numRows = 0;
/*     */     
/*  70 */     if (this.multicastTopics != null)
/*     */     {
/*  72 */       numRows = this.multicastTopics.length;
/*     */     }
/*     */     
/*  75 */     MessageBodyHandle[] rows = new MessageBodyHandle[this.multicastTopics.length];
/*     */     
/*  77 */     if (this.multicastTopics != null)
/*     */     {
/*  79 */       if (this.multicastTopics[0] != null)
/*     */       {
/*  81 */         for (int i = 0; i < rows.length; i++) {
/*     */           
/*  83 */           rows[i] = this.cursor.newTableRow(103);
/*  84 */           rows[i].setString(1, (this.multicastTopics[i]).partitionLabel);
/*  85 */           rows[i].setString(0, (this.multicastTopics[i]).topic);
/*     */           
/*  87 */           if (ipv6Connection() && (this.multicastTopics[i]).groupAddressIpv6 != null) {
/*  88 */             rows[i].setString(2, (this.multicastTopics[i]).groupAddressIpv6);
/*     */           } else {
/*  90 */             rows[i].setString(2, (this.multicastTopics[i]).groupAddress);
/*     */           } 
/*     */           
/*  93 */           rows[i].setBoolean(3, (this.multicastTopics[i]).enabled);
/*  94 */           rows[i].setBoolean(4, (this.multicastTopics[i]).reliable);
/*  95 */           rows[i].setByte(5, (this.multicastTopics[i]).qop);
/*  96 */           rows[i].setByteArray(6, this.multicastTopics[i].getKey());
/*  97 */           rows[i].setLong(7, (this.multicastTopics[i]).timeStamp);
/*     */         } 
/*     */       }
/*     */     }
/*     */     
/* 102 */     this.cursor.setTable(103, rows);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIpv6Connection() {
/* 107 */     this.ipv6 = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ipv6Connection() {
/* 112 */     return this.ipv6;
/*     */   }
/*     */   
/*     */   public void setMulticastTopics(MulticastTopic[] multicastTopics) {
/* 116 */     this.multicastTopics = multicastTopics;
/* 117 */     rebuildP();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\MulticastTopicsUpdate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */