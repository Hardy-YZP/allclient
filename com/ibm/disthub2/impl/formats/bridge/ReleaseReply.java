/*    */ package com.ibm.disthub2.impl.formats.bridge;
/*    */ 
/*    */ import com.ibm.disthub2.impl.client.DebugObject;
/*    */ import com.ibm.disthub2.impl.formats.Envelop;
/*    */ import com.ibm.disthub2.impl.formats.MessageDataHandle;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ReleaseReply
/*    */   extends Payload
/*    */   implements Envelop.Constants
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 37 */   private static final DebugObject debug = new DebugObject("ReleaseReply");
/*    */ 
/*    */ 
/*    */   
/*    */   public static ReleaseReply create() {
/* 42 */     Jgram j = new Jgram(14);
/* 43 */     return (ReleaseReply)j.getPayload();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   ReleaseReply(int type, MessageDataHandle cursor, Jgram jgram) {
/* 50 */     super(type, cursor, jgram);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long getReleasePrefix() {
/* 57 */     return this.cursor.getLong(117);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setReleasePrefix(long val) {
/* 64 */     this.cursor.setLong(117, val);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSrcCellFrom() {
/* 71 */     return this.cursor.getString(118);
/*    */   }
/*    */   public String getSrcCellTo() {
/* 74 */     return this.cursor.getString(119);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSrcCellule(String from, String to) {
/* 79 */     this.cursor.setString(118, from);
/* 80 */     this.cursor.setString(119, to);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDstCellFrom() {
/* 87 */     return this.cursor.getString(120);
/*    */   }
/*    */   public String getDstCellTo() {
/* 90 */     return this.cursor.getString(121);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDstCellule(String from, String to) {
/* 95 */     this.cursor.setString(120, from);
/* 96 */     this.cursor.setString(121, to);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\ReleaseReply.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */