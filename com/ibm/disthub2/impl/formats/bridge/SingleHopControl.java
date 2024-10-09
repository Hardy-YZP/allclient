/*     */ package com.ibm.disthub2.impl.formats.bridge;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.DebugObject;
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
/*     */ 
/*     */ 
/*     */ public final class SingleHopControl
/*     */   extends Payload
/*     */   implements LogConstants
/*     */ {
/*     */   private static final String copyright = "Licensed Material - Property of IBM \n5648-C63 (c) Copyright IBM Corp. 2000, 2001 - All Rights Reserved. \nUS Government Users Restricted Rights - Use, duplication or disclosure \nrestricted by GSA ADP Schedule Contract with IBM Corp.";
/*  70 */   private static final DebugObject debug = new DebugObject("SingleHopControl");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ControlMessageBody body;
/*     */ 
/*     */ 
/*     */   
/*     */   private NormalMessage topicCompatible;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SingleHopControl create() {
/*  85 */     if (debug.debugIt(32)) {
/*  86 */       debug.debug(-165922073994779L, "create");
/*     */     }
/*     */     
/*  89 */     SingleHopControl ans = (SingleHopControl)(new Jgram(10)).getPayload();
/*     */     
/*  91 */     if (debug.debugIt(64)) {
/*  92 */       debug.debug(-142394261359015L, "create", ans);
/*     */     }
/*     */     
/*  95 */     return ans;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SingleHopControl(int type, MessageDataHandle cursor, Jgram jgram) {
/* 102 */     super(type, cursor, jgram);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   SingleHopControl(ControlMessageBody body, NormalMessage compat) {
/* 113 */     this(-1, null, null);
/*     */     
/* 115 */     if (debug.debugIt(32)) {
/* 116 */       debug.debug(-165922073994779L, "SingleHopControl", body, compat);
/*     */     }
/*     */     
/* 119 */     this.body = body;
/* 120 */     this.topicCompatible = compat;
/*     */     
/* 122 */     if (debug.debugIt(64)) {
/* 123 */       debug.debug(-142394261359015L, "SingleHopControl");
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
/*     */   public SingleHopControl(NormalMessage compat) {
/* 135 */     this(-1, null, null);
/*     */     
/* 137 */     if (debug.debugIt(32)) {
/* 138 */       debug.debug(-165922073994779L, "SingleHopControl", compat);
/*     */     }
/*     */     
/* 141 */     this.body = ControlMessageBody.construct(this, compat.getBody());
/* 142 */     this.topicCompatible = compat;
/*     */     
/* 144 */     if (debug.debugIt(64)) {
/* 145 */       debug.debug(-142394261359015L, "SingleHopControl");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTrack() {
/* 154 */     if (debug.debugIt(32)) {
/* 155 */       debug.debug(-165922073994779L, "getTrack");
/*     */     }
/*     */     
/* 158 */     int result = 0;
/* 159 */     if (this.topicCompatible != null) {
/*     */       
/* 161 */       result = this.topicCompatible.getTrack();
/*     */     }
/* 163 */     else if (this.cursor.getChoice(161) == 1) {
/* 164 */       result = this.cursor.getInt(47);
/*     */     } 
/*     */     
/* 167 */     if (debug.debugIt(64)) {
/* 168 */       debug.debug(-142394261359015L, "getTrack", Integer.valueOf(result));
/*     */     }
/*     */     
/* 171 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTrack(int val) {
/* 178 */     if (debug.debugIt(32)) {
/* 179 */       debug.debug(-165922073994779L, "setTrack", Integer.valueOf(val));
/*     */     }
/*     */     
/* 182 */     if (this.topicCompatible != null) {
/*     */       
/* 184 */       this.topicCompatible.setTrack(val);
/*     */     
/*     */     }
/* 187 */     else if (val == 0) {
/* 188 */       this.cursor.setChoice(161, 0);
/*     */     }
/*     */     else {
/*     */       
/* 192 */       this.cursor.setInt(47, val);
/*     */     } 
/*     */ 
/*     */     
/* 196 */     if (debug.debugIt(64)) {
/* 197 */       debug.debug(-142394261359015L, "setTrack");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ControlMessageBody getBody() {
/* 205 */     if (debug.debugIt(32)) {
/* 206 */       debug.debug(-165922073994779L, "getBody");
/*     */     }
/*     */     
/* 209 */     if (this.body == null) {
/* 210 */       Assert.condition((this.topicCompatible == null));
/* 211 */       this.body = ControlMessageBody.construct(this.cursor.getChoice(162), this.cursor, this);
/*     */     } 
/*     */ 
/*     */     
/* 215 */     if (debug.debugIt(64)) {
/* 216 */       debug.debug(-142394261359015L, "getBody", this.body);
/*     */     }
/*     */     
/* 219 */     return this.body;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ControlMessageBody setBody(int type) {
/* 226 */     if (debug.debugIt(32)) {
/* 227 */       debug.debug(-165922073994779L, "setBody", Integer.valueOf(type));
/*     */     }
/*     */     
/* 230 */     Assert.condition((this.topicCompatible == null));
/* 231 */     this.cursor.setChoice(162, type);
/* 232 */     this.body = ControlMessageBody.construct(type, this.cursor, this);
/* 233 */     if (debug.debugIt(64)) {
/* 234 */       debug.debug(-142394261359015L, "setBody", this.body);
/*     */     }
/*     */     
/* 237 */     return this.body;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMid() {
/* 247 */     if (debug.debugIt(32)) {
/* 248 */       debug.debug(-165922073994779L, "getMid");
/*     */     }
/*     */     
/* 251 */     long result = -1L;
/* 252 */     if (this.topicCompatible != null)
/*     */     {
/* 254 */       result = this.topicCompatible.getMid();
/*     */     }
/*     */     
/* 257 */     if (debug.debugIt(64)) {
/* 258 */       debug.debug(-142394261359015L, "getMid", Long.valueOf(result));
/*     */     }
/*     */     
/* 261 */     return result;
/*     */   }
/*     */   
/*     */   public void setMid(long val) {
/* 265 */     if (debug.debugIt(32)) {
/* 266 */       debug.debug(-165922073994779L, "setMid", Long.valueOf(val));
/*     */     }
/*     */     
/* 269 */     if (this.topicCompatible != null)
/*     */     {
/* 271 */       this.topicCompatible.setMid(val);
/*     */     }
/*     */     
/* 274 */     if (debug.debugIt(64)) {
/* 275 */       debug.debug(-142394261359015L, "setMid");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/*     */     int result;
/* 285 */     if (debug.debugIt(32)) {
/* 286 */       debug.debug(-165922073994779L, "getType");
/*     */     }
/*     */ 
/*     */     
/* 290 */     if (this.topicCompatible != null) {
/*     */       
/* 292 */       result = this.topicCompatible.getType();
/*     */     } else {
/*     */       
/* 295 */       result = super.getType();
/*     */     } 
/*     */     
/* 298 */     if (debug.debugIt(64)) {
/* 299 */       debug.debug(-142394261359015L, "getType", Integer.valueOf(result));
/*     */     }
/*     */     
/* 302 */     return result;
/*     */   }
/*     */   public Jgram getJgram() {
/*     */     Jgram result;
/* 306 */     if (debug.debugIt(32)) {
/* 307 */       debug.debug(-165922073994779L, "getJgram");
/*     */     }
/*     */ 
/*     */     
/* 311 */     if (this.topicCompatible != null) {
/*     */       
/* 313 */       result = this.topicCompatible.getJgram();
/*     */     } else {
/*     */       
/* 316 */       result = super.getJgram();
/*     */     } 
/*     */     
/* 319 */     if (debug.debugIt(64)) {
/* 320 */       debug.debug(-142394261359015L, "getJgram", result);
/*     */     }
/*     */     
/* 323 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\formats\bridge\SingleHopControl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */