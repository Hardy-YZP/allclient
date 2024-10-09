/*    */ package com.ibm.disthub2.impl.formats.bridge;
/*    */ 
/*    */ import com.ibm.disthub2.impl.client.DebugObject;
/*    */ import com.ibm.disthub2.impl.client.MessageImpl;
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
/*    */ public final class SubscribeReq
/*    */   extends MessageBody
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 42 */   private static final DebugObject debug = new DebugObject("SubscribeReq");
/*    */ 
/*    */ 
/*    */   
/*    */   public static SubscribeReq create(String topic) {
/* 47 */     Jgram jg = new Jgram(1);
/* 48 */     NormalMessage msg = (NormalMessage)jg.getPayload();
/* 49 */     jg.setTopic(topic);
/* 50 */     msg.setDefaults();
/* 51 */     SubscribeReq ans = (SubscribeReq)msg.setBody(2);
/* 52 */     return ans;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   SubscribeReq(int type, MessageDataHandle cursor, NormalMessage msg) {
/* 59 */     super(type, cursor, msg);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSubject() {
/* 66 */     String topic = this.cursor.getString(14);
/* 67 */     if (getJgram().areTopicsPrefixed())
/* 68 */       topic = MessageImpl.toDefaultInternalTopic(topic); 
/* 69 */     return topic;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSubject(String val) {
/* 76 */     if (getJgram().areTopicsPrefixed())
/* 77 */       val = MessageImpl.toDefaultExternalTopic(val); 
/* 78 */     this.cursor.setString(14, val);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getQuery() {
/* 85 */     return this.cursor.getString(15);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setQuery(String val) {
/* 92 */     this.cursor.setString(15, val);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\SubscribeReq.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */