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
/*    */ public final class UnsubscribeReply
/*    */   extends ControlMessageBody
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 38 */   private static final DebugObject debug = new DebugObject("UnsubscribeReply");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static UnsubscribeReply create() {
/* 46 */     Jgram jg = new Jgram(10);
/* 47 */     SingleHopControl msg = (SingleHopControl)jg.getPayload();
/* 48 */     UnsubscribeReply ans = (UnsubscribeReply)msg.setBody(4);
/* 49 */     msg.getCursor().setChoice(171, 0);
/*    */     
/* 51 */     return ans;
/*    */   }
/*    */ 
/*    */   
/*    */   UnsubscribeReply(int type, MessageDataHandle cursor, SingleHopControl msg) {
/* 56 */     super(type, cursor, msg);
/*    */   }
/*    */   
/*    */   public void setStatus(int status) {
/* 60 */     this.cursor.setChoice(171, 1);
/*    */     
/* 62 */     this.cursor.setInt(63, status);
/*    */   }
/*    */   
/*    */   public int getStatus() {
/* 66 */     if (this.cursor.getChoice(171) == 0)
/* 67 */       return 0; 
/* 68 */     return this.cursor.getInt(63);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\UnsubscribeReply.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */