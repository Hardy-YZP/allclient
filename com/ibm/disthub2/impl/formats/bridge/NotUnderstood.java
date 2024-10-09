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
/*    */ public final class NotUnderstood
/*    */   extends Payload
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 41 */   private static final DebugObject debug = new DebugObject("NotUnderstood");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static NotUnderstood create() {
/* 47 */     return (NotUnderstood)(new Jgram(9)).getPayload();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   NotUnderstood(int type, MessageDataHandle cursor, Jgram jgram) {
/* 54 */     super(type, cursor, jgram);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getVersion() {
/* 61 */     return this.cursor.getInt(0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setVersion(int val) {
/* 68 */     this.cursor.setInt(0, val);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\NotUnderstood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */