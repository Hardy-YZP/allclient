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
/*    */ public final class ConnFail
/*    */   extends Payload
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 41 */   private static final DebugObject debug = new DebugObject("ConnFail");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ConnFail create() {
/* 47 */     return (ConnFail)(new Jgram(6)).getPayload();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   ConnFail(int type, MessageDataHandle cursor, Jgram jgram) {
/* 54 */     super(type, cursor, jgram);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCode() {
/* 61 */     return this.cursor.getInt(40);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCode(int val) {
/* 68 */     this.cursor.setInt(40, val);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getVersion() {
/* 75 */     return this.cursor.getInt(41);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setVersion(int val) {
/* 82 */     this.cursor.setInt(41, val);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\ConnFail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */