/*    */ package com.ibm.disthub2.impl.formats.bridge;
/*    */ 
/*    */ import com.ibm.disthub2.impl.client.DebugObject;
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
/*    */ public final class FreeWindowAdvertisement
/*    */   extends ControlMessageBody
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 37 */   private static final DebugObject debug = new DebugObject("FreeWindowAdvertisement");
/*    */ 
/*    */ 
/*    */   
/*    */   public static FreeWindowAdvertisement create() {
/* 42 */     Jgram jg = new Jgram(10);
/* 43 */     SingleHopControl msg = (SingleHopControl)jg.getPayload();
/*    */ 
/*    */     
/* 46 */     msg.setTrack(-1);
/* 47 */     FreeWindowAdvertisement ans = (FreeWindowAdvertisement)msg.setBody(31);
/* 48 */     ans.setStart(0L);
/* 49 */     ans.setNumAvailable(0L);
/* 50 */     return ans;
/*    */   }
/*    */ 
/*    */   
/*    */   FreeWindowAdvertisement(int type, MessageDataHandle cursor, SingleHopControl msg) {
/* 55 */     super(type, cursor, msg);
/*    */   }
/*    */   
/*    */   public void setStart(long v) {
/* 59 */     this.cursor.setLong(99, v);
/*    */   }
/*    */   
/*    */   public long getStart() {
/* 63 */     return this.cursor.getLong(99);
/*    */   }
/*    */   
/*    */   public void setNumAvailable(long v) {
/* 67 */     this.cursor.setLong(100, v);
/*    */   }
/*    */   
/*    */   public long getNumAvailable() {
/* 71 */     return this.cursor.getLong(100);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 75 */     String rslt = "(";
/* 76 */     rslt = rslt + this.cursor.getLong(99) + ",";
/* 77 */     rslt = rslt + this.cursor.getLong(100) + ")";
/* 78 */     return rslt;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\FreeWindowAdvertisement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */