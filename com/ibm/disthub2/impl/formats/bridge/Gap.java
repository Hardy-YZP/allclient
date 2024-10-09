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
/*    */ 
/*    */ 
/*    */ public final class Gap
/*    */   extends ControlMessageBody
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 39 */   private static final DebugObject debug = new DebugObject("Gap");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Gap create(int tid, long pe, long start, long end) {
/* 46 */     Jgram j = new Jgram(10);
/* 47 */     SingleHopControl msg = (SingleHopControl)j.getPayload();
/*    */     
/* 49 */     msg.setTrack(0);
/* 50 */     Gap ans = (Gap)msg.setBody(29);
/* 51 */     ans.setTargetId(tid);
/* 52 */     ans.setPubend(pe);
/* 53 */     ans.setStartstamp(start);
/* 54 */     ans.setEndstamp(end);
/* 55 */     return ans;
/*    */   }
/*    */   
/*    */   Gap(int type, MessageDataHandle cursor, SingleHopControl msg) {
/* 59 */     super(type, cursor, msg);
/*    */   }
/*    */   
/*    */   public void setTargetId(int tid) {
/* 63 */     this.cursor.setInt(91, tid);
/*    */   }
/*    */   
/*    */   public int getTargetId() {
/* 67 */     return this.cursor.getInt(91);
/*    */   }
/*    */   
/*    */   public void setPubend(long pe) {
/* 71 */     this.cursor.setLong(92, pe);
/*    */   }
/*    */   
/*    */   public long getPubend() {
/* 75 */     return this.cursor.getLong(92);
/*    */   }
/*    */   
/*    */   public void setStartstamp(long start) {
/* 79 */     this.cursor.setLong(93, start);
/*    */   }
/*    */   
/*    */   public long getStartstamp() {
/* 83 */     return this.cursor.getLong(93);
/*    */   }
/*    */   
/*    */   public void setEndstamp(long end) {
/* 87 */     this.cursor.setLong(94, end);
/*    */   }
/*    */   
/*    */   public long getEndstamp() {
/* 91 */     return this.cursor.getLong(94);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\Gap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */