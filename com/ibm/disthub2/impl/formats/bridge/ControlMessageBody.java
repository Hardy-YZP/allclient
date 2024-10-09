/*     */ package com.ibm.disthub2.impl.formats.bridge;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.formats.Envelop;
/*     */ import com.ibm.disthub2.impl.formats.MessageDataHandle;
/*     */ import com.ibm.disthub2.impl.util.Assert;
/*     */ import com.ibm.disthub2.spi.LogConstants;
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
/*     */ public class ControlMessageBody
/*     */   implements Envelop.Constants, LogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  68 */   private static final DebugObject debug = new DebugObject("ControlMessageBody");
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
/*     */   protected SingleHopControl msg;
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
/*     */   protected MessageBody topicCompatible;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ControlMessageBody create(int type) {
/*  98 */     if (debug.debugIt(32)) {
/*  99 */       debug.debug(-165922073994779L, "create", Integer.valueOf(type));
/*     */     }
/* 101 */     ControlMessageBody result = SingleHopControl.create().setBody(type);
/*     */     
/* 103 */     if (debug.debugIt(64)) {
/* 104 */       debug.debug(-142394261359015L, "create", result);
/*     */     }
/* 106 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ControlMessageBody create(String topic) {
/* 116 */     if (debug.debugIt(32)) {
/* 117 */       debug.debug(-165922073994779L, "create", topic);
/*     */     }
/* 119 */     NormalMessage real = NormalMessage.create(topic);
/* 120 */     ControlMessageBody result = new ControlMessageBody(real);
/*     */     
/* 122 */     if (debug.debugIt(64)) {
/* 123 */       debug.debug(-142394261359015L, "create", result);
/*     */     }
/* 125 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ControlMessageBody construct(int type, MessageDataHandle cursor, SingleHopControl msg) {
/*     */     ControlMessageBody result;
/* 138 */     if (debug.debugIt(32)) {
/* 139 */       debug.debug(-165922073994779L, "construct", Integer.valueOf(type), cursor, msg);
/*     */     }
/*     */     
/* 142 */     switch (type) {
/*     */       case 1:
/* 144 */         result = new SubscribeReqSHC(type, cursor, msg);
/*     */         break;
/*     */       case 2:
/* 147 */         result = new SubscribeReply(type, cursor, msg);
/*     */         break;
/*     */       case 3:
/* 150 */         result = new UnsubscribeReq(type, cursor, msg);
/*     */         break;
/*     */       case 4:
/* 153 */         result = new UnsubscribeReply(type, cursor, msg);
/*     */         break;
/*     */       case 32:
/* 156 */         result = new MulticastControlReq(type, cursor, msg);
/*     */         break;
/*     */       case 33:
/* 159 */         result = new MulticastControlReply(type, cursor, msg);
/*     */         break;
/*     */       case 34:
/* 162 */         result = new MulticastTopicsUpdate(type, cursor, msg);
/*     */         break;
/*     */       case 35:
/* 165 */         result = new MulticastTopicsUpdateReq(type, cursor, msg);
/*     */         break;
/*     */       case 13:
/* 168 */         result = new PublishReply(type, cursor, msg);
/*     */         break;
/*     */       case 14:
/* 171 */         result = new TopicQuery(type, cursor, msg);
/*     */         break;
/*     */       case 15:
/* 174 */         result = new TopicQueryReply(type, cursor, msg);
/*     */         break;
/*     */       case 28:
/* 177 */         result = new EndOfCatchup(type, cursor, msg);
/*     */         break;
/*     */       case 22:
/* 180 */         result = new ReactivateReq(type, cursor, msg);
/*     */         break;
/*     */       case 23:
/* 183 */         result = new ReactivateReply(type, cursor, msg);
/*     */         break;
/*     */       case 26:
/* 186 */         result = new ReleaseReq(type, cursor, msg);
/*     */         break;
/*     */       case 29:
/* 189 */         result = new Gap(type, cursor, msg);
/*     */         break;
/*     */       case 30:
/* 192 */         result = new AppSilence(type, cursor, msg);
/*     */         break;
/*     */       case 31:
/* 195 */         result = new FreeWindowAdvertisement(type, cursor, msg);
/*     */         break;
/*     */       default:
/* 198 */         result = new ControlMessageBody(type, cursor, msg);
/*     */         break;
/*     */     } 
/* 201 */     if (debug.debugIt(64)) {
/* 202 */       debug.debug(-142394261359015L, "construct", result);
/*     */     }
/* 204 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static ControlMessageBody construct(SingleHopControl msg, MessageBody compat) {
/* 216 */     if (debug.debugIt(32)) {
/* 217 */       debug.debug(-165922073994779L, "construct", msg, compat);
/*     */     }
/* 219 */     ControlMessageBody newBody = null;
/* 220 */     switch (compat.getType()) {
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 225 */         newBody = new SubscribeReply(-1, null, msg);
/*     */         break;
/*     */       
/*     */       case 1:
/* 229 */         newBody = new ControlMessageBody(-1, null, msg);
/*     */         break;
/*     */       
/*     */       default:
/* 233 */         Assert.failure("ControlMessageBody.construct(): unexpected MessageBody type"); break;
/*     */     } 
/* 235 */     newBody.topicCompatible = compat;
/*     */     
/* 237 */     if (debug.debugIt(64)) {
/* 238 */       debug.debug(-142394261359015L, "construct", newBody);
/*     */     }
/* 240 */     return newBody;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ControlMessageBody(int type, MessageDataHandle cursor, SingleHopControl msg) {
/* 245 */     if (debug.debugIt(32)) {
/* 246 */       debug.debug(-165922073994779L, "ControlMessageBody", Integer.valueOf(type), cursor, msg);
/*     */     }
/* 248 */     this.type = type;
/* 249 */     this.cursor = cursor;
/* 250 */     this.msg = msg;
/*     */     
/* 252 */     if (debug.debugIt(64)) {
/* 253 */       debug.debug(-142394261359015L, "ControlMessageBody");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ControlMessageBody(NormalMessage compat) {
/* 266 */     this(-1, null, null);
/* 267 */     this.msg = new SingleHopControl(this, compat);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getType() {
/* 272 */     if (debug.debugIt(32)) {
/* 273 */       debug.debug(-165922073994779L, "getType");
/*     */     }
/* 275 */     if (debug.debugIt(64)) {
/* 276 */       debug.debug(-142394261359015L, "getType", Integer.valueOf(this.type));
/*     */     }
/* 278 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public SingleHopControl getSingleHopControl() {
/* 283 */     if (debug.debugIt(32)) {
/* 284 */       debug.debug(-165922073994779L, "getSingleHopControl");
/*     */     }
/* 286 */     if (debug.debugIt(64)) {
/* 287 */       debug.debug(-142394261359015L, "getSingleHopControl", this.msg);
/*     */     }
/* 289 */     return this.msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public Jgram getJgram() {
/* 294 */     if (debug.debugIt(32)) {
/* 295 */       debug.debug(-165922073994779L, "getJgram");
/*     */     }
/* 297 */     Jgram result = this.msg.getJgram();
/*     */     
/* 299 */     if (debug.debugIt(64)) {
/* 300 */       debug.debug(-142394261359015L, "getJgram", result);
/*     */     }
/* 302 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\ControlMessageBody.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */