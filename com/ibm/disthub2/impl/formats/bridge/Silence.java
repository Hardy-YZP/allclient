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
/*     */ public final class Silence
/*     */   extends Payload
/*     */   implements Envelop.Constants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  41 */   private static final DebugObject debug = new DebugObject("Silence");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Silence create() {
/*  47 */     Jgram j = new Jgram(16);
/*  48 */     return (Silence)j.getPayload();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Silence(int type, MessageDataHandle cursor, Jgram jgram) {
/*  55 */     super(type, cursor, jgram);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getFprefix() {
/*  62 */     return this.cursor.getLong(132);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFprefix(long val) {
/*  69 */     this.cursor.setLong(132, val);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLprefix() {
/*  75 */     return this.cursor.getLong(131);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLprefix(long val) {
/*  82 */     this.cursor.setLong(131, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getStartstamp() {
/*  89 */     return this.cursor.getLong(133);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartstamp(long val) {
/*  96 */     this.cursor.setLong(133, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getEndstamp() {
/* 103 */     return this.cursor.getLong(134);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEndstamp(long val) {
/* 110 */     this.cursor.setLong(134, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getForce() {
/* 117 */     return this.cursor.getBoolean(135);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setForce(boolean val) {
/* 124 */     this.cursor.setBoolean(135, val);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSrcCellFrom() {
/* 130 */     return this.cursor.getString(136);
/*     */   }
/*     */   public String getSrcCellTo() {
/* 133 */     return this.cursor.getString(137);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSrcCellule(String from, String to) {
/* 138 */     this.cursor.setString(136, from);
/* 139 */     this.cursor.setString(137, to);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\Silence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */