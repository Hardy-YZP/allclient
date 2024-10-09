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
/*    */ public final class MulticastControlReply
/*    */   extends ControlMessageBody
/*    */ {
/* 32 */   private static final DebugObject debug = new DebugObject("MulticastControlReply");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static MulticastControlReply create() {
/* 39 */     Jgram jg = new Jgram(10);
/* 40 */     SingleHopControl msg = (SingleHopControl)jg.getPayload();
/* 41 */     MulticastControlReply multicastControlReply = (MulticastControlReply)msg.setBody(33);
/* 42 */     return multicastControlReply;
/*    */   }
/*    */ 
/*    */   
/*    */   MulticastControlReply(int type, MessageDataHandle cursor, SingleHopControl msg) {
/* 47 */     super(type, cursor, msg);
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getMessage() {
/* 52 */     byte[] message = this.cursor.getByteArray(102);
/* 53 */     return message;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMessage(byte[] message) {
/* 58 */     this.cursor.setByteArray(102, message);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\MulticastControlReply.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */