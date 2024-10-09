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
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class AckExpected
/*    */   extends Payload
/*    */   implements Envelop.Constants
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 40 */   private static final DebugObject debug = new DebugObject("Ack");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static AckExpected create() {
/* 46 */     Jgram j = new Jgram(17);
/* 47 */     return (AckExpected)j.getPayload();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   AckExpected(int type, MessageDataHandle cursor, Jgram jgram) {
/* 54 */     super(type, cursor, jgram);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long getStamp() {
/* 61 */     return this.cursor.getLong(138);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setStamp(long val) {
/* 68 */     this.cursor.setLong(138, val);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSrcCellFrom() {
/* 73 */     return this.cursor.getString(139);
/*    */   }
/*    */   public String getSrcCellTo() {
/* 76 */     return this.cursor.getString(140);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSrcCellule(String from, String to) {
/* 81 */     this.cursor.setString(139, from);
/* 82 */     this.cursor.setString(140, to);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\AckExpected.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */