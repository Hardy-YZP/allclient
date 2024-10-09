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
/*    */ public final class MulticastControlReq
/*    */   extends ControlMessageBody
/*    */ {
/* 34 */   private static final DebugObject debug = new DebugObject("MulticastControlReq");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static MulticastControlReq create() {
/* 41 */     Jgram jg = new Jgram(10);
/* 42 */     SingleHopControl msg = (SingleHopControl)jg.getPayload();
/* 43 */     MulticastControlReq multicastControlReq = (MulticastControlReq)msg.setBody(32);
/* 44 */     return multicastControlReq;
/*    */   }
/*    */   
/*    */   MulticastControlReq(int type, MessageDataHandle cursor, SingleHopControl msg) {
/* 48 */     super(type, cursor, msg);
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getMessage() {
/* 53 */     byte[] message = this.cursor.getByteArray(101);
/* 54 */     return message;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMessage(byte[] message) {
/* 59 */     this.cursor.setByteArray(101, message);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\MulticastControlReq.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */