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
/*    */ public final class TopicQueryReply
/*    */   extends ControlMessageBody
/*    */   implements Envelop.Constants
/*    */ {
/*    */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/* 35 */   private static final DebugObject debug = new DebugObject("TopicQueryReply");
/*    */ 
/*    */   
/*    */   public static TopicQueryReply create() {
/* 39 */     Jgram jg = new Jgram(10);
/* 40 */     SingleHopControl msg = (SingleHopControl)jg.getPayload();
/* 41 */     return (TopicQueryReply)msg.setBody(15);
/*    */   }
/*    */ 
/*    */   
/*    */   TopicQueryReply(int type, MessageDataHandle cursor, SingleHopControl msg) {
/* 46 */     super(type, cursor, msg);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTopicKey(String key) {
/* 51 */     this.cursor.setString(67, key);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setValues(String[] names, String[] values) {
/* 57 */     MessageBodyHandle[] valTable = new MessageBodyHandle[names.length];
/* 58 */     for (int i = 0; i < valTable.length; i++) {
/* 59 */       valTable[i] = this.cursor.newTableRow(68);
/* 60 */       valTable[i].setString(0, names[i]);
/* 61 */       valTable[i].setString(1, values[i]);
/*    */     } 
/* 63 */     this.cursor.setTable(68, valTable);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\TopicQueryReply.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */