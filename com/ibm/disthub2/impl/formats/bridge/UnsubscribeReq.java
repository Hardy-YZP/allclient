/*     */ package com.ibm.disthub2.impl.formats.bridge;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
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
/*     */ public final class UnsubscribeReq
/*     */   extends ControlMessageBody
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  43 */   private static final DebugObject debug = new DebugObject("UnsubscribeReq");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ControlMessageBody create(String topic) {
/*     */     UnsubscribeReq ans;
/*  51 */     if (topic != null) {
/*  52 */       SubscribeId real = SubscribeId.create(topic);
/*  53 */       ans = new UnsubscribeReq(real);
/*     */     } else {
/*     */       
/*  56 */       Jgram jg = new Jgram(10);
/*  57 */       SingleHopControl msg = (SingleHopControl)jg.getPayload();
/*  58 */       ans = (UnsubscribeReq)msg.setBody(3);
/*     */     } 
/*  60 */     return ans;
/*     */   }
/*     */ 
/*     */   
/*     */   UnsubscribeReq(int type, MessageDataHandle cursor, SingleHopControl msg) {
/*  65 */     super(type, cursor, msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private UnsubscribeReq(SubscribeId compat) {
/*  74 */     this(-1, (MessageDataHandle)null, (SingleHopControl)null);
/*  75 */     this.msg = new SingleHopControl(this, compat.getNormalMessage());
/*  76 */     this.topicCompatible = compat;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  81 */     if (this.topicCompatible != null)
/*     */     {
/*  83 */       return ((SubscribeId)this.topicCompatible).getId();
/*     */     }
/*     */     
/*  86 */     return this.cursor.getInt(60);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(int val) {
/*  92 */     if (this.topicCompatible != null) {
/*     */       
/*  94 */       ((SubscribeId)this.topicCompatible).setId(val);
/*     */     } else {
/*     */       
/*  97 */       this.cursor.setInt(60, val);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getMode() {
/* 102 */     if (this.topicCompatible != null)
/* 103 */       return -1; 
/* 104 */     if (this.cursor.getChoice(170) == 0)
/* 105 */       return -1; 
/* 106 */     return this.cursor.getInt(61);
/*     */   }
/*     */   
/*     */   public String getName() {
/* 110 */     if (this.topicCompatible != null)
/* 111 */       return null; 
/* 112 */     if (this.cursor.getChoice(170) == 0)
/* 113 */       return null; 
/* 114 */     return this.cursor.getString(62);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\UnsubscribeReq.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */