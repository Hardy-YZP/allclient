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
/*    */ 
/*    */ 
/*    */ public final class AppSilence
/*    */   extends ControlMessageBody
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 41 */   private static final DebugObject debug = new DebugObject("AppSilence");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static AppSilence create(int tid, long pe, long start, long end) {
/* 48 */     Jgram j = new Jgram(10);
/* 49 */     SingleHopControl msg = (SingleHopControl)j.getPayload();
/*    */     
/* 51 */     msg.setTrack(0);
/* 52 */     AppSilence ans = (AppSilence)msg.setBody(30);
/* 53 */     ans.setTargetId(tid);
/* 54 */     ans.setPubend(pe);
/* 55 */     ans.setStartstamp(start);
/* 56 */     ans.setEndstamp(end);
/* 57 */     return ans;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   AppSilence(int type, MessageDataHandle cursor, SingleHopControl msg) {
/* 64 */     super(type, cursor, msg);
/*    */   }
/*    */   
/*    */   public void setTargetId(int tid) {
/* 68 */     this.cursor.setInt(95, tid);
/*    */   }
/*    */   
/*    */   public int getTargetId() {
/* 72 */     return this.cursor.getInt(95);
/*    */   }
/*    */   
/*    */   public void setPubend(long pe) {
/* 76 */     this.cursor.setLong(96, pe);
/*    */   }
/*    */   
/*    */   public long getPubend() {
/* 80 */     return this.cursor.getLong(96);
/*    */   }
/*    */   
/*    */   public void setStartstamp(long start) {
/* 84 */     this.cursor.setLong(97, start);
/*    */   }
/*    */   
/*    */   public long getStartstamp() {
/* 88 */     return this.cursor.getLong(97);
/*    */   }
/*    */   
/*    */   public void setEndstamp(long end) {
/* 92 */     this.cursor.setLong(98, end);
/*    */   }
/*    */   
/*    */   public long getEndstamp() {
/* 96 */     return this.cursor.getLong(98);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\AppSilence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */