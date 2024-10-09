/*     */ package com.ibm.disthub2.impl.formats.bridge;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.formats.Envelop;
/*     */ import com.ibm.disthub2.impl.formats.MessageDataHandle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MessageBody
/*     */   implements Envelop.Constants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  42 */   private static final DebugObject debug = new DebugObject("MessageBody");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int type;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected NormalMessage msg;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MessageDataHandle cursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MessageBody construct(int type, MessageDataHandle cursor, NormalMessage msg) {
/*  70 */     switch (type) {
/*     */       case 2:
/*  72 */         return new SubscribeReq(type, cursor, msg);
/*     */       case 3:
/*  74 */         return new SubscribeId(type, cursor, msg);
/*     */     } 
/*  76 */     return new MessageBody(type, cursor, msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MessageBody(int type, MessageDataHandle cursor, NormalMessage msg) {
/*  83 */     this.type = type;
/*  84 */     this.cursor = cursor;
/*  85 */     this.msg = msg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/*  92 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NormalMessage getNormalMessage() {
/*  99 */     return this.msg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Jgram getJgram() {
/* 106 */     return this.msg.getJgram();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\MessageBody.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */