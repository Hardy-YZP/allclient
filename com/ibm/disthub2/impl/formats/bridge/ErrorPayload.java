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
/*    */ public final class ErrorPayload
/*    */   extends Payload
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 40 */   private static final DebugObject debug = new DebugObject("ErrorPayload");
/*    */ 
/*    */ 
/*    */   
/*    */   public static ErrorPayload create() {
/* 45 */     return (ErrorPayload)(new Jgram(4)).getPayload();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   ErrorPayload(int type, MessageDataHandle cursor, Jgram jgram) {
/* 52 */     super(type, cursor, jgram);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long getId() {
/* 59 */     return this.cursor.getLong(33);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setId(long val) {
/* 66 */     this.cursor.setLong(33, val);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCode() {
/* 73 */     return this.cursor.getInt(34);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCode(int val) {
/* 80 */     this.cursor.setInt(34, val);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\ErrorPayload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */