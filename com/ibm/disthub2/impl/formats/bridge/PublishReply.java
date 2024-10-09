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
/*    */ public final class PublishReply
/*    */   extends ControlMessageBody
/*    */   implements Envelop.Constants
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 40 */   private static final DebugObject debug = new DebugObject("PublishReply");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static PublishReply create() {
/* 47 */     Jgram jg = new Jgram(10);
/* 48 */     SingleHopControl msg = (SingleHopControl)jg.getPayload();
/* 49 */     return (PublishReply)msg.setBody(13);
/*    */   }
/*    */ 
/*    */   
/*    */   PublishReply(int type, MessageDataHandle cursor, SingleHopControl msg) {
/* 54 */     super(type, cursor, msg);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getStatus() {
/* 60 */     return this.cursor.getInt(64);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setStatus(int val) {
/* 66 */     this.cursor.setInt(64, val);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\PublishReply.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */