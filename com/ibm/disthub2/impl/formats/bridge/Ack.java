/*     */ package com.ibm.disthub2.impl.formats.bridge;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.formats.Envelop;
/*     */ import com.ibm.disthub2.impl.formats.MessageDataHandle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Ack
/*     */   extends Payload
/*     */   implements Envelop.Constants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  40 */   private static final DebugObject debug = new DebugObject("Ack");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Ack create() {
/*  46 */     Jgram j = new Jgram(13);
/*  47 */     j.setPriority((byte)20);
/*  48 */     return (Ack)j.getPayload();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Ack(int type, MessageDataHandle cursor, Jgram jgram) {
/*  55 */     super(type, cursor, jgram);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getAckPrefix() {
/*  62 */     return this.cursor.getLong(112);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAckPrefix(long val) {
/*  69 */     this.cursor.setLong(112, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSrcCellFrom() {
/*  76 */     return this.cursor.getString(113);
/*     */   }
/*     */   public String getSrcCellTo() {
/*  79 */     return this.cursor.getString(114);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSrcCellule(String from, String to) {
/*  84 */     this.cursor.setString(113, from);
/*  85 */     this.cursor.setString(114, to);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDstCellFrom() {
/*  92 */     return this.cursor.getString(115);
/*     */   }
/*     */   public String getDstCellTo() {
/*  95 */     return this.cursor.getString(116);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDstCellule(String from, String to) {
/* 100 */     this.cursor.setString(115, from);
/* 101 */     this.cursor.setString(116, to);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\Ack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */