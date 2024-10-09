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
/*     */ 
/*     */ 
/*     */ public class Payload
/*     */   implements Envelop.Constants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  44 */   private static final DebugObject debug = new DebugObject("Payload");
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
/*     */   private Jgram jgram;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MessageDataHandle cursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Payload construct(int type, MessageDataHandle cursor, Jgram jgram) {
/*  73 */     switch (type) {
/*     */       case 4:
/*  75 */         return new ErrorPayload(type, cursor, jgram);
/*     */       case 5:
/*  77 */         return new ConnGrant(type, cursor, jgram);
/*     */       case 6:
/*  79 */         return new ConnFail(type, cursor, jgram);
/*     */       case 1:
/*  81 */         return new NormalMessage(type, cursor, jgram);
/*     */       case 7:
/*  83 */         return new FeatureExchange(type, cursor, jgram);
/*     */       case 8:
/*  85 */         return new QopUpdate(type, cursor, jgram);
/*     */       case 9:
/*  87 */         return new NotUnderstood(type, cursor, jgram);
/*     */       case 10:
/*  89 */         return new SingleHopControl(type, cursor, jgram);
/*     */       case 13:
/*  91 */         return new Ack(type, cursor, jgram);
/*     */       case 17:
/*  93 */         return new AckExpected(type, cursor, jgram);
/*     */       case 15:
/*  95 */         return new Nack(type, cursor, jgram);
/*     */       case 14:
/*  97 */         return new ReleaseReply(type, cursor, jgram);
/*     */       case 18:
/*  99 */         return new ReleaseExpected(type, cursor, jgram);
/*     */       case 16:
/* 101 */         return new Silence(type, cursor, jgram);
/*     */       case 12:
/* 103 */         return new PreValue(type, cursor, jgram);
/*     */     } 
/* 105 */     return new Payload(type, cursor, jgram);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Payload(int type, MessageDataHandle cursor, Jgram jgram) {
/* 112 */     this.type = type;
/* 113 */     this.cursor = cursor;
/* 114 */     this.jgram = jgram;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 121 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Jgram getJgram() {
/* 128 */     return this.jgram;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageDataHandle getCursor() {
/* 135 */     return this.cursor;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\Payload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */