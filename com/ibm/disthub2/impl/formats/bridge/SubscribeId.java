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
/*    */ public final class SubscribeId
/*    */   extends MessageBody
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 40 */   private static final DebugObject debug = new DebugObject("SubscribeId");
/*    */ 
/*    */ 
/*    */   
/*    */   public static SubscribeId create(String topic) {
/* 45 */     Jgram jg = new Jgram(1);
/* 46 */     NormalMessage msg = (NormalMessage)jg.getPayload();
/* 47 */     jg.setTopic(topic);
/* 48 */     msg.setDefaults();
/* 49 */     return (SubscribeId)msg.setBody(3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   SubscribeId(int type, MessageDataHandle cursor, NormalMessage msg) {
/* 56 */     super(type, cursor, msg);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getId() {
/* 63 */     return this.cursor.getInt(0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setId(int val) {
/* 70 */     this.cursor.setInt(0, val);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\SubscribeId.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */