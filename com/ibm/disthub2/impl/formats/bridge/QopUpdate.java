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
/*    */ 
/*    */ public final class QopUpdate
/*    */   extends Payload
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 42 */   private static final DebugObject debug = new DebugObject("QopUpdate");
/*    */ 
/*    */ 
/*    */   
/*    */   public static QopUpdate create(String topic, byte qop) {
/* 47 */     QopUpdate newQ = (QopUpdate)(new Jgram(8)).getPayload();
/*    */     
/* 49 */     newQ.setSubject(topic);
/* 50 */     newQ.setQopCode(qop);
/* 51 */     return newQ;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   QopUpdate(int type, MessageDataHandle cursor, Jgram jgram) {
/* 58 */     super(type, cursor, jgram);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSubject() {
/* 65 */     return this.cursor.getString(45);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSubject(String val) {
/* 72 */     this.cursor.setString(45, val);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte getQopCode() {
/* 79 */     return this.cursor.getByte(44);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setQopCode(byte val) {
/* 86 */     this.cursor.setByte(44, val);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\QopUpdate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */