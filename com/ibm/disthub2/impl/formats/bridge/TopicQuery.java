/*    */ package com.ibm.disthub2.impl.formats.bridge;
/*    */ 
/*    */ import com.ibm.disthub2.client.MessageBodyHandle;
/*    */ import com.ibm.disthub2.impl.client.DebugObject;
/*    */ import com.ibm.disthub2.impl.formats.Envelop;
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
/*    */ public final class TopicQuery
/*    */   extends ControlMessageBody
/*    */   implements Envelop.Constants
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 36 */   private static final DebugObject debug = new DebugObject("TopicQuery");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   TopicQuery(int type, MessageDataHandle cursor, SingleHopControl msg) {
/* 43 */     super(type, cursor, msg);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTopicKey() {
/* 48 */     return this.cursor.getString(65);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String[] getNames() {
/* 54 */     MessageBodyHandle[] nameTable = this.cursor.getTable(66);
/* 55 */     String[] ans = new String[nameTable.length];
/* 56 */     for (int i = 0; i < nameTable.length; i++)
/* 57 */       ans[i] = nameTable[i].getString(0); 
/* 58 */     return ans;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\TopicQuery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */