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
/*     */ 
/*     */ public final class Nack
/*     */   extends Payload
/*     */   implements Envelop.Constants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  41 */   private static final DebugObject debug = new DebugObject("Nack");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Nack create() {
/*  47 */     Jgram j = new Jgram(15);
/*  48 */     j.setPriority((byte)20);
/*  49 */     return (Nack)j.getPayload();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Nack(int type, MessageDataHandle cursor, Jgram jgram) {
/*  56 */     super(type, cursor, jgram);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getStartstamp() {
/*  63 */     return this.cursor.getLong(122);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartstamp(long val) {
/*  70 */     this.cursor.setLong(122, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getEndstamp() {
/*  77 */     return this.cursor.getLong(123);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEndstamp(long val) {
/*  84 */     this.cursor.setLong(123, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getForce() {
/*  91 */     return this.cursor.getBoolean(124);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setForce(boolean val) {
/*  98 */     this.cursor.setBoolean(124, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getIsCs() {
/* 105 */     return this.cursor.getBoolean(125);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsCs(boolean val) {
/* 112 */     this.cursor.setBoolean(125, val);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCuriousD() {
/* 118 */     return this.cursor.getBoolean(126);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCuriousD(boolean val) {
/* 125 */     this.cursor.setBoolean(126, val);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSrcCellFrom() {
/* 131 */     return this.cursor.getString(127);
/*     */   }
/*     */   public String getSrcCellTo() {
/* 134 */     return this.cursor.getString(128);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSrcCellule(String from, String to) {
/* 139 */     this.cursor.setString(127, from);
/* 140 */     this.cursor.setString(128, to);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDstCellFrom() {
/* 147 */     return this.cursor.getString(129);
/*     */   }
/*     */   public String getDstCellTo() {
/* 150 */     return this.cursor.getString(130);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDstCellule(String from, String to) {
/* 155 */     this.cursor.setString(129, from);
/* 156 */     this.cursor.setString(130, to);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\Nack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */