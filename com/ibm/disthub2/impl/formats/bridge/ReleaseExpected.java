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
/*    */ public final class ReleaseExpected
/*    */   extends Payload
/*    */   implements Envelop.Constants
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 37 */   private static final DebugObject debug = new DebugObject("Release");
/*    */ 
/*    */ 
/*    */   
/*    */   public static ReleaseExpected create() {
/* 42 */     Jgram j = new Jgram(18);
/* 43 */     return (ReleaseExpected)j.getPayload();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   ReleaseExpected(int type, MessageDataHandle cursor, Jgram jgram) {
/* 50 */     super(type, cursor, jgram);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long getStamp() {
/* 57 */     return this.cursor.getLong(141);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setStamp(long val) {
/* 64 */     this.cursor.setLong(141, val);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSrcCellFrom() {
/* 69 */     return this.cursor.getString(142);
/*    */   }
/*    */   public String getSrcCellTo() {
/* 72 */     return this.cursor.getString(143);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSrcCellule(String from, String to) {
/* 77 */     this.cursor.setString(142, from);
/* 78 */     this.cursor.setString(143, to);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\ReleaseExpected.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */