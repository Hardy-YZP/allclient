/*     */ package com.ibm.disthub2.impl.formats.bridge;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.client.MessageImpl;
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
/*     */ 
/*     */ 
/*     */ public final class SubscribeReqSHC
/*     */   extends ControlMessageBody
/*     */   implements Envelop.Constants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  48 */   private static final DebugObject debug = new DebugObject("SubscribeReqSHC");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ControlMessageBody create(String topic) {
/*     */     SubscribeReqSHC ans;
/*  56 */     if (topic != null) {
/*  57 */       SubscribeReq real = SubscribeReq.create(topic);
/*  58 */       ans = new SubscribeReqSHC(real);
/*     */     } else {
/*     */       
/*  61 */       Jgram jg = new Jgram(10);
/*  62 */       SingleHopControl msg = (SingleHopControl)jg.getPayload();
/*  63 */       ans = (SubscribeReqSHC)msg.setBody(1);
/*     */     } 
/*  65 */     return ans;
/*     */   }
/*     */ 
/*     */   
/*     */   SubscribeReqSHC(int type, MessageDataHandle cursor, SingleHopControl msg) {
/*  70 */     super(type, cursor, msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SubscribeReqSHC(SubscribeReq compat) {
/*  79 */     this(-1, (MessageDataHandle)null, (SingleHopControl)null);
/*  80 */     this.msg = new SingleHopControl(this, compat.getNormalMessage());
/*  81 */     this.topicCompatible = compat;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSubject() {
/*  86 */     if (this.topicCompatible != null)
/*     */     {
/*  88 */       return ((SubscribeReq)this.topicCompatible).getSubject();
/*     */     }
/*     */     
/*  91 */     String topic = this.cursor.getString(48);
/*  92 */     if (getJgram().areTopicsPrefixed())
/*  93 */       topic = MessageImpl.toDefaultInternalTopic(topic); 
/*  94 */     return topic;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubject(String val) {
/* 100 */     if (this.topicCompatible != null) {
/*     */       
/* 102 */       ((SubscribeReq)this.topicCompatible).setSubject(val);
/*     */     } else {
/*     */       
/* 105 */       if (getJgram().areTopicsPrefixed())
/* 106 */         val = MessageImpl.toDefaultExternalTopic(val); 
/* 107 */       this.cursor.setString(48, val);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getQuery() {
/* 113 */     if (this.topicCompatible != null)
/*     */     {
/* 115 */       return ((SubscribeReq)this.topicCompatible).getQuery();
/*     */     }
/*     */     
/* 118 */     return this.cursor.getString(49);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQuery(String val) {
/* 124 */     if (this.topicCompatible != null) {
/*     */       
/* 126 */       ((SubscribeReq)this.topicCompatible).setQuery(val);
/*     */     } else {
/*     */       
/* 129 */       this.cursor.setString(49, val);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getMulticatEnable() {
/* 135 */     if (this.cursor.getChoice(164) == 1)
/*     */     {
/* 137 */       return this.cursor.getBoolean(52);
/*     */     }
/*     */ 
/*     */     
/* 141 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getMulticastReliable() {
/* 147 */     if (this.cursor.getChoice(164) == 1)
/*     */     {
/* 149 */       return this.cursor.getBoolean(53);
/*     */     }
/*     */ 
/*     */     
/* 153 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void initGDFlags() {
/* 159 */     this.cursor.setChoice(163, 1);
/*     */     
/* 161 */     this.cursor.setInt(50, 2);
/* 162 */     this.cursor.setString(51, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getQoS() {
/* 170 */     if (this.topicCompatible != null) {
/* 171 */       return 1;
/*     */     }
/* 173 */     if (!this.cursor.isPresent(50)) {
/* 174 */       return 1;
/*     */     }
/* 176 */     return this.cursor.getInt(50);
/*     */   }
/*     */   
/*     */   public void setQoS(int mode) {
/* 180 */     if (this.topicCompatible != null) {
/* 181 */       throw new IllegalStateException("Feature not available in old message format");
/*     */     }
/* 183 */     if (mode != 1 && mode != 3 && mode != 2 && mode != 4)
/*     */     {
/*     */ 
/*     */       
/* 187 */       throw new IllegalArgumentException("Illegal subscription guarantee mode!");
/*     */     }
/* 189 */     if (this.cursor.getChoice(163) != 1) {
/* 190 */       initGDFlags();
/*     */     }
/*     */     
/* 193 */     this.cursor.setInt(50, mode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubscriptionName() {
/* 203 */     if (this.topicCompatible != null) {
/* 204 */       return null;
/*     */     }
/* 206 */     if (this.cursor.getChoice(163) == 0) {
/* 207 */       return null;
/*     */     }
/* 209 */     return this.cursor.getString(51);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\SubscribeReqSHC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */