/*    */ package com.ibm.msg.client.wmq.internal;
/*    */ 
/*    */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*    */ import com.ibm.mq.jmqi.MQGMO;
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
/*    */ public class WMQGMO
/*    */   extends MQGMO
/*    */ {
/*    */   private byte[] correlationID;
/*    */   private byte[] messageID;
/*    */   
/*    */   protected WMQGMO(JmqiEnvironment env) {
/* 36 */     super(env);
/*    */   }
/*    */ 
/*    */   
/*    */   byte[] getCorrelationID() {
/* 41 */     return this.correlationID;
/*    */   }
/*    */   
/*    */   void setCorrelationID(byte[] correlationID) {
/* 45 */     this.correlationID = correlationID;
/*    */   }
/*    */   
/*    */   byte[] getMessageID() {
/* 49 */     return this.messageID;
/*    */   }
/*    */   
/*    */   void setMessageID(byte[] messageID) {
/* 53 */     this.messageID = messageID;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\WMQGMO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */