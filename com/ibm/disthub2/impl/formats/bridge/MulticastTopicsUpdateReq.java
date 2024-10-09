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
/*    */ public final class MulticastTopicsUpdateReq
/*    */   extends ControlMessageBody
/*    */ {
/* 34 */   private static final DebugObject debug = new DebugObject("MulticastTopicsUpdateReq");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static MulticastTopicsUpdateReq create() {
/* 41 */     Jgram jg = new Jgram(10);
/* 42 */     SingleHopControl msg = (SingleHopControl)jg.getPayload();
/* 43 */     MulticastTopicsUpdateReq multicastTopicsUpdateReq = (MulticastTopicsUpdateReq)msg.setBody(35);
/* 44 */     return multicastTopicsUpdateReq;
/*    */   }
/*    */   
/*    */   MulticastTopicsUpdateReq(int type, MessageDataHandle cursor, SingleHopControl msg) {
/* 48 */     super(type, cursor, msg);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTopic() {
/* 53 */     String topic = this.cursor.getString(104);
/* 54 */     return topic;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMessage(String topic) {
/* 59 */     this.cursor.setString(104, topic);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\MulticastTopicsUpdateReq.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */