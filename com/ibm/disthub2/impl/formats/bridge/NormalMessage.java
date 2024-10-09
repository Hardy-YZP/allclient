/*     */ package com.ibm.disthub2.impl.formats.bridge;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
/*     */ import com.ibm.disthub2.impl.client.MessageImpl;
/*     */ import com.ibm.disthub2.impl.formats.Envelop;
/*     */ import com.ibm.disthub2.impl.formats.MessageDataHandle;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class NormalMessage
/*     */   extends Payload
/*     */   implements LogConstants, Envelop.Constants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  73 */   private static final DebugObject debug = new DebugObject("NormalMessage");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private MessageBody body;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NormalMessage create(String topic) {
/*  84 */     if (debug.debugIt(32)) {
/*  85 */       debug.debug(-165922073994779L, "create", topic);
/*     */     }
/*     */ 
/*     */     
/*  89 */     Jgram j = new Jgram(1);
/*  90 */     j.setTopic(topic);
/*  91 */     NormalMessage msg = (NormalMessage)j.getPayload();
/*  92 */     msg.setBody(1);
/*  93 */     msg.setDefaults();
/*     */ 
/*     */     
/*  96 */     if (debug.debugIt(64)) {
/*  97 */       debug.debug(-142394261359015L, "create", msg);
/*     */     }
/*     */ 
/*     */     
/* 101 */     return msg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaults() {
/* 112 */     if (debug.debugIt(32)) {
/* 113 */       debug.debug(-165922073994779L, "setDefaults");
/*     */     }
/*     */ 
/*     */     
/* 117 */     setReply((String)null);
/* 118 */     setTrack(0);
/* 119 */     setMid(0L);
/*     */ 
/*     */     
/* 122 */     if (debug.debugIt(64)) {
/* 123 */       debug.debug(-142394261359015L, "setDefaults");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   NormalMessage(int type, MessageDataHandle cursor, Jgram jgram) {
/* 131 */     super(type, cursor, jgram);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMid() {
/* 138 */     if (debug.debugIt(32)) {
/* 139 */       debug.debug(-165922073994779L, "getMid");
/*     */     }
/*     */ 
/*     */     
/* 143 */     long result = this.cursor.getLong(11);
/*     */ 
/*     */     
/* 146 */     if (debug.debugIt(64)) {
/* 147 */       debug.debug(-142394261359015L, "getMid", Long.valueOf(result));
/*     */     }
/*     */ 
/*     */     
/* 151 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMid(long val) {
/* 158 */     if (debug.debugIt(32)) {
/* 159 */       debug.debug(-165922073994779L, "setMid", Long.valueOf(val));
/*     */     }
/*     */ 
/*     */     
/* 163 */     this.cursor.setLong(11, val);
/*     */ 
/*     */     
/* 166 */     if (debug.debugIt(64)) {
/* 167 */       debug.debug(-142394261359015L, "setMid");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReply() {
/* 176 */     if (debug.debugIt(32)) {
/* 177 */       debug.debug(-165922073994779L, "getReply");
/*     */     }
/*     */ 
/*     */     
/* 181 */     String result = null;
/* 182 */     if (this.cursor.getChoice(150) == 1) {
/* 183 */       String reply = this.cursor.getString(12);
/* 184 */       if (getJgram().areTopicsPrefixed()) {
/* 185 */         reply = MessageImpl.toDefaultInternalTopic(reply);
/*     */       }
/* 187 */       result = reply;
/*     */     } 
/*     */ 
/*     */     
/* 191 */     if (debug.debugIt(64)) {
/* 192 */       debug.debug(-142394261359015L, "getReply", result);
/*     */     }
/*     */ 
/*     */     
/* 196 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReply(String val) {
/* 203 */     if (debug.debugIt(32)) {
/* 204 */       debug.debug(-165922073994779L, "setReply", val);
/*     */     }
/*     */ 
/*     */     
/* 208 */     if (val == null) {
/* 209 */       this.cursor.setChoice(150, 0);
/*     */     }
/*     */     else {
/*     */       
/* 213 */       if (getJgram().areTopicsPrefixed()) {
/* 214 */         val = MessageImpl.toDefaultExternalTopic(val);
/*     */       }
/* 216 */       this.cursor.setString(12, val);
/*     */     } 
/*     */ 
/*     */     
/* 220 */     if (debug.debugIt(64)) {
/* 221 */       debug.debug(-142394261359015L, "setReply");
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTopic() {
/* 238 */     if (debug.debugIt(32)) {
/* 239 */       debug.debug(-165922073994779L, "getTopic");
/*     */     }
/*     */ 
/*     */     
/* 243 */     String result = getJgram().getTopic();
/*     */ 
/*     */     
/* 246 */     if (debug.debugIt(64)) {
/* 247 */       debug.debug(-142394261359015L, "getTopic", result);
/*     */     }
/*     */ 
/*     */     
/* 251 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTopic(String val) {
/* 258 */     if (debug.debugIt(32)) {
/* 259 */       debug.debug(-165922073994779L, "setTopic", val);
/*     */     }
/*     */ 
/*     */     
/* 263 */     getJgram().setTopic(val);
/*     */ 
/*     */     
/* 266 */     if (debug.debugIt(64)) {
/* 267 */       debug.debug(-142394261359015L, "setTopic");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTrack() {
/* 277 */     if (debug.debugIt(32)) {
/* 278 */       debug.debug(-165922073994779L, "getTrack");
/*     */     }
/*     */ 
/*     */     
/* 282 */     int result = 0;
/* 283 */     if (this.cursor.getChoice(151) == 1) {
/* 284 */       result = this.cursor.getInt(13);
/*     */     }
/*     */ 
/*     */     
/* 288 */     if (debug.debugIt(64)) {
/* 289 */       debug.debug(-142394261359015L, "getTrack", Integer.valueOf(result));
/*     */     }
/*     */ 
/*     */     
/* 293 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTrack(int val) {
/* 300 */     if (debug.debugIt(32)) {
/* 301 */       debug.debug(-165922073994779L, "setTrack", Integer.valueOf(val));
/*     */     }
/*     */ 
/*     */     
/* 305 */     if (val == 0) {
/* 306 */       this.cursor.setChoice(151, 0);
/*     */     }
/*     */     else {
/*     */       
/* 310 */       this.cursor.setInt(13, val);
/*     */     } 
/*     */ 
/*     */     
/* 314 */     if (debug.debugIt(64)) {
/* 315 */       debug.debug(-142394261359015L, "setTrack");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReply() {
/* 325 */     if (debug.debugIt(32)) {
/* 326 */       debug.debug(-165922073994779L, "isReply");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 331 */     boolean result = (this.cursor.getChoice(151) == 1 && this.cursor.getChoice(150) == 0);
/*     */ 
/*     */     
/* 334 */     if (debug.debugIt(64)) {
/* 335 */       debug.debug(-142394261359015L, "isReply", Boolean.valueOf(result));
/*     */     }
/*     */ 
/*     */     
/* 339 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageBody getBody() {
/* 346 */     if (debug.debugIt(32)) {
/* 347 */       debug.debug(-165922073994779L, "getBody");
/*     */     }
/*     */ 
/*     */     
/* 351 */     if (this.body == null) {
/* 352 */       this.body = MessageBody.construct(this.cursor.getChoice(152), this.cursor, this);
/*     */     }
/*     */ 
/*     */     
/* 356 */     if (debug.debugIt(64)) {
/* 357 */       debug.debug(-142394261359015L, "getBody", this.body);
/*     */     }
/*     */ 
/*     */     
/* 361 */     return this.body;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageBody setBody(int type) {
/* 368 */     if (debug.debugIt(32)) {
/* 369 */       debug.debug(-165922073994779L, "setBody", Integer.valueOf(type));
/*     */     }
/*     */ 
/*     */     
/* 373 */     this.cursor.setChoice(152, type);
/* 374 */     this.body = MessageBody.construct(type, this.cursor, this);
/*     */ 
/*     */     
/* 377 */     if (debug.debugIt(64)) {
/* 378 */       debug.debug(-142394261359015L, "setBody", this.body);
/*     */     }
/*     */ 
/*     */     
/* 382 */     return this.body;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\NormalMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */