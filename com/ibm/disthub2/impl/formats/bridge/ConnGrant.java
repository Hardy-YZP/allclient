/*     */ package com.ibm.disthub2.impl.formats.bridge;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.formats.MessageDataHandle;
/*     */ import com.ibm.disthub2.spi.ServerExceptionConstants;
/*     */ import com.ibm.disthub2.spi.ServerLogConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ConnGrant
/*     */   extends Payload
/*     */   implements ServerLogConstants, ServerExceptionConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  43 */   private static final DebugObject debug = new DebugObject("ConnGrant");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ConnGrant create(int serverId, int connectionCount, int clientId, String cId, String pId) {
/*  51 */     if (debug.debugIt(32))
/*  52 */       debug.debug(-165922073994779L, "create"); 
/*  53 */     ConnGrant ans = (ConnGrant)(new Jgram(5)).getPayload();
/*  54 */     ans.setServer(serverId);
/*  55 */     ans.setConnection(connectionCount);
/*  56 */     ans.setClient(clientId);
/*  57 */     ans.setCPId(cId, pId);
/*  58 */     if (debug.debugIt(64))
/*  59 */       debug.debug(-142394261359015L, "create"); 
/*  60 */     return ans;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ConnGrant(int type, MessageDataHandle cursor, Jgram jgram) {
/*  67 */     super(type, cursor, jgram);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getServer() {
/*  74 */     return this.cursor.getInt(35);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServer(int val) {
/*  81 */     this.cursor.setInt(35, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getConnection() {
/*  88 */     return this.cursor.getInt(36);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnection(int val) {
/*  95 */     this.cursor.setInt(36, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getClient() {
/* 102 */     return this.cursor.getInt(37);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClient(int val) {
/* 109 */     this.cursor.setInt(37, val);
/*     */   }
/*     */   
/*     */   public String getClientId() {
/* 113 */     if (this.cursor.getChoice(159) != 1)
/* 114 */       return null; 
/* 115 */     return this.cursor.getString(38);
/*     */   }
/*     */   
/*     */   public String getPublisherId() {
/* 119 */     if (this.cursor.getChoice(159) != 1)
/* 120 */       return null; 
/* 121 */     return this.cursor.getString(39);
/*     */   }
/*     */   
/*     */   public void setCPId(String cId, String pId) {
/* 125 */     if (debug.debugIt(32))
/* 126 */       debug.debug(-165922073994779L, "setCPId"); 
/* 127 */     this.cursor.setChoice(159, 1);
/* 128 */     this.cursor.setString(38, cId);
/* 129 */     this.cursor.setString(39, pId);
/* 130 */     if (debug.debugIt(64))
/* 131 */       debug.debug(-142394261359015L, "setCPId"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\ConnGrant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */