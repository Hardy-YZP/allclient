/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.mq.jms.NoBrokerResponseException;
/*     */ import com.ibm.mq.jms.PublishSubscribeSetupException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.provider.ProviderMessageReference;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*     */ import javax.jms.JMSException;
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
/*     */ public class JMSServicesMgr
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSServicesMgr.java";
/*     */   private PublishSubscribeSetupException sme;
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSServicesMgr.java");
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
/*  58 */   private MQQueueServices queueServices = null;
/*     */   
/*  60 */   private MQPubSubServices pubSubServices = null;
/*     */   
/*     */   boolean pubSubSet = false;
/*     */   
/*     */   boolean queueSet = false;
/*     */   
/*     */   JMSServicesMgr() {
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "<init>()");
/*     */     }
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setQueueServices(MQQueueServices qs) {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "setQueueServices(MQQueueServices)", "setter", qs);
/*     */     }
/*     */     
/*  81 */     this.queueServices = qs;
/*  82 */     this.queueSet = true;
/*     */   }
/*     */   
/*     */   protected void setPubSubServices(MQPubSubServices pss) {
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "setPubSubServices(MQPubSubServices)", "setter", pss);
/*     */     }
/*     */     
/*  90 */     this.pubSubServices = pss;
/*  91 */     this.pubSubSet = true;
/*     */   }
/*     */   
/*     */   protected boolean isPubSub() {
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "isPubSub()", "getter", 
/*  97 */           Boolean.valueOf(this.pubSubSet));
/*     */     }
/*  99 */     return this.pubSubSet;
/*     */   }
/*     */   
/*     */   protected boolean isPTP() {
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "isPTP()", "getter", 
/* 105 */           Boolean.valueOf(this.queueSet));
/*     */     }
/* 107 */     return this.queueSet;
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
/*     */   void closePS(MQSession session) throws JMSException {
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "closePS(MQSession)", new Object[] { session });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 125 */       if (this.pubSubSet) {
/* 126 */         this.pubSubServices.closePubSub(session);
/*     */       }
/*     */     }
/* 129 */     catch (JMSException je) {
/* 130 */       if (Trace.isOn) {
/* 131 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "closePS(MQSession)", (Throwable)je);
/*     */       }
/*     */       
/* 134 */       if (Trace.isOn) {
/* 135 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "closePS(MQSession)", (Throwable)je);
/*     */       }
/*     */       
/* 138 */       throw je;
/*     */     } 
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "closePS(MQSession)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected JMSException getQueueOpenException(MQException mqe) throws JMSException {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getQueueOpenException(MQException)", new Object[] { mqe });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     if (this.queueSet) {
/* 156 */       JMSException traceRet1 = this.queueServices.getQueueOpenException(mqe, "");
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getQueueOpenException(MQException)", traceRet1);
/*     */       }
/*     */       
/* 161 */       return traceRet1;
/*     */     } 
/* 163 */     this
/* 164 */       .sme = new PublishSubscribeSetupException(ConfigEnvironment.newException("MQJMS1111").getMessage());
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getQueueOpenException(MQException)", (Throwable)this.sme);
/*     */     }
/*     */     
/* 169 */     throw this.sme;
/*     */   }
/*     */   
/*     */   void checkQueueAccess(WMQDestination queue, MQSession session) throws JMSException {
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "checkQueueAccess(WMQDestination,MQSession)", new Object[] { queue, session });
/*     */     }
/*     */ 
/*     */     
/* 178 */     if (this.queueSet) {
/* 179 */       this.queueServices.checkQueueAccess(queue, session);
/*     */     }
/*     */     else {
/*     */       
/* 183 */       this
/* 184 */         .sme = new PublishSubscribeSetupException(ConfigEnvironment.newException("MQJMS1111").getMessage());
/* 185 */       if (Trace.isOn) {
/* 186 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "checkQueueAccess(WMQDestination,MQSession)", (Throwable)this.sme);
/*     */       }
/*     */       
/* 189 */       throw this.sme;
/*     */     } 
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "checkQueueAccess(WMQDestination,MQSession)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MQQueue getOutputQueue(WMQDestination dest, MQSession session) throws JMSException {
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getOutputQueue(WMQDestination,MQSession)", new Object[] { dest, session });
/*     */     }
/*     */ 
/*     */     
/* 205 */     if (this.queueSet) {
/*     */       try {
/* 207 */         MQQueue traceRet1 = this.queueServices.getOutputQueue(dest, session);
/*     */         
/* 209 */         if (Trace.isOn) {
/* 210 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getOutputQueue(WMQDestination,MQSession)", traceRet1);
/*     */         }
/*     */         
/* 213 */         return traceRet1;
/*     */       }
/* 215 */       catch (JMSException je) {
/* 216 */         if (Trace.isOn) {
/* 217 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getOutputQueue(WMQDestination,MQSession)", (Throwable)je);
/*     */         }
/*     */         
/* 220 */         if (Trace.isOn) {
/* 221 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getOutputQueue(WMQDestination,MQSession)", (Throwable)je, 1);
/*     */         }
/*     */         
/* 224 */         throw je;
/*     */       } 
/*     */     }
/*     */     
/* 228 */     this
/* 229 */       .sme = new PublishSubscribeSetupException(ConfigEnvironment.newException("MQJMS1111").getMessage());
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getOutputQueue(WMQDestination,MQSession)", (Throwable)this.sme, 2);
/*     */     }
/*     */     
/* 234 */     throw this.sme;
/*     */   }
/*     */ 
/*     */   
/*     */   MQQueue getQueueForBrowse(WMQDestination queueSpec, MQSession session) throws JMSException {
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getQueueForBrowse(WMQDestination,MQSession)", new Object[] { queueSpec, session });
/*     */     }
/*     */ 
/*     */     
/* 244 */     if (this.queueSet) {
/*     */       
/*     */       try {
/* 247 */         MQQueue traceRet1 = this.queueServices.getQueueForBrowse(queueSpec, session);
/* 248 */         if (Trace.isOn) {
/* 249 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getQueueForBrowse(WMQDestination,MQSession)", traceRet1);
/*     */         }
/*     */         
/* 252 */         return traceRet1;
/*     */       }
/* 254 */       catch (JMSException je) {
/* 255 */         if (Trace.isOn) {
/* 256 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getQueueForBrowse(WMQDestination,MQSession)", (Throwable)je);
/*     */         }
/*     */         
/* 259 */         if (Trace.isOn) {
/* 260 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getQueueForBrowse(WMQDestination,MQSession)", (Throwable)je, 1);
/*     */         }
/*     */         
/* 263 */         throw je;
/*     */       } 
/*     */     }
/*     */     
/* 267 */     this
/* 268 */       .sme = new PublishSubscribeSetupException(ConfigEnvironment.newException("MQJMS1111").getMessage());
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getQueueForBrowse(WMQDestination,MQSession)", (Throwable)this.sme, 2);
/*     */     }
/*     */     
/* 273 */     throw this.sme;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getBrokerResponse(MQSession session, MQMsg2 response, boolean immediateResponse) throws NoBrokerResponseException, JMSException {
/* 284 */     if (Trace.isOn) {
/* 285 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getBrokerResponse(MQSession,MQMsg2,boolean)", new Object[] { session, response, 
/*     */             
/* 287 */             Boolean.valueOf(immediateResponse) });
/*     */     }
/* 289 */     if (this.pubSubSet) {
/* 290 */       this.pubSubServices.getBrokerResponse(session, response, immediateResponse);
/*     */     } else {
/* 292 */       this
/* 293 */         .sme = new PublishSubscribeSetupException(ConfigEnvironment.newException("MQJMS1111").getMessage());
/* 294 */       if (Trace.isOn) {
/* 295 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getBrokerResponse(MQSession,MQMsg2,boolean)", (Throwable)this.sme);
/*     */       }
/*     */       
/* 298 */       throw this.sme;
/*     */     } 
/* 300 */     if (Trace.isOn) {
/* 301 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getBrokerResponse(MQSession,MQMsg2,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean checkForResponse(MQSession session) throws JMSException {
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "checkForResponse(MQSession)", new Object[] { session });
/*     */     }
/*     */     
/* 312 */     if (this.pubSubSet) {
/*     */       try {
/* 314 */         boolean traceRet1 = this.pubSubServices.checkForResponse(session);
/* 315 */         if (Trace.isOn) {
/* 316 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "checkForResponse(MQSession)", 
/* 317 */               Boolean.valueOf(traceRet1), 1);
/*     */         }
/* 319 */         return traceRet1;
/*     */       }
/* 321 */       catch (JMSException je) {
/* 322 */         if (Trace.isOn) {
/* 323 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "checkForResponse(MQSession)", (Throwable)je);
/*     */         }
/*     */         
/* 326 */         if (Trace.isOn) {
/* 327 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "checkForResponse(MQSession)", (Throwable)je);
/*     */         }
/*     */         
/* 330 */         throw je;
/*     */       } 
/*     */     }
/* 333 */     if (Trace.isOn) {
/* 334 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "checkForResponse(MQSession)", 
/* 335 */           Boolean.valueOf(false), 2);
/*     */     }
/* 337 */     return false;
/*     */   }
/*     */   
/*     */   boolean getSparseSubscriptions(MQSession session) {
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getSparseSubscriptions(MQSession)", new Object[] { session });
/*     */     }
/*     */     
/* 345 */     if (this.pubSubSet) {
/* 346 */       boolean traceRet1 = this.pubSubServices.getSparseSubscriptions(session);
/* 347 */       if (Trace.isOn) {
/* 348 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getSparseSubscriptions(MQSession)", 
/* 349 */             Boolean.valueOf(traceRet1), 1);
/*     */       }
/* 351 */       return traceRet1;
/*     */     } 
/* 353 */     if (Trace.isOn) {
/* 354 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getSparseSubscriptions(MQSession)", 
/* 355 */           Boolean.valueOf(false), 2);
/*     */     }
/* 357 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ProviderMessage consume(ProviderMessageReference mRef, MQSession session) throws JMSException {
/* 362 */     if (Trace.isOn) {
/* 363 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "consume(ProviderMessageReference,MQSession)", new Object[] { mRef, session });
/*     */     }
/*     */     
/* 366 */     if (this.queueSet) {
/* 367 */       ProviderMessage traceRet1 = this.queueServices.consume(mRef, session);
/* 368 */       if (Trace.isOn) {
/* 369 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "consume(ProviderMessageReference,MQSession)", traceRet1);
/*     */       }
/*     */       
/* 372 */       return traceRet1;
/*     */     } 
/* 374 */     this
/* 375 */       .sme = new PublishSubscribeSetupException(ConfigEnvironment.newException("MQJMS1112").getMessage());
/* 376 */     if (Trace.isOn) {
/* 377 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "consume(ProviderMessageReference,MQSession)", (Throwable)this.sme);
/*     */     }
/*     */     
/* 380 */     throw this.sme;
/*     */   }
/*     */   
/*     */   protected ProviderMessage consume(byte[] flattenedRef, MQSession session) throws JMSException {
/* 384 */     if (Trace.isOn) {
/* 385 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "consume(byte [ ],MQSession)", new Object[] { flattenedRef, session });
/*     */     }
/*     */     
/* 388 */     if (this.queueSet) {
/* 389 */       ProviderMessage traceRet1 = this.queueServices.consume(flattenedRef, session);
/* 390 */       if (Trace.isOn) {
/* 391 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "consume(byte [ ],MQSession)", traceRet1);
/*     */       }
/*     */       
/* 394 */       return traceRet1;
/*     */     } 
/* 396 */     this
/* 397 */       .sme = new PublishSubscribeSetupException(ConfigEnvironment.newException("MQJMS1112").getMessage());
/* 398 */     if (Trace.isOn) {
/* 399 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "consume(byte [ ],MQSession)", (Throwable)this.sme);
/*     */     }
/*     */     
/* 402 */     throw this.sme;
/*     */   }
/*     */ 
/*     */   
/*     */   void redirectMessage(String qName, MQMsg2 message, boolean syncPoint, MQSession session) throws JMSException {
/* 407 */     if (Trace.isOn) {
/* 408 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean,MQSession)", new Object[] { qName, message, 
/*     */             
/* 410 */             Boolean.valueOf(syncPoint), session });
/*     */     }
/* 412 */     if (this.queueSet) {
/* 413 */       this.queueServices.redirectMessage(qName, message, syncPoint, session);
/*     */     } else {
/* 415 */       this
/* 416 */         .sme = new PublishSubscribeSetupException(ConfigEnvironment.newException("MQJMS1111").getMessage());
/* 417 */       if (Trace.isOn) {
/* 418 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean,MQSession)", (Throwable)this.sme);
/*     */       }
/*     */ 
/*     */       
/* 422 */       throw this.sme;
/*     */     } 
/* 424 */     if (Trace.isOn) {
/* 425 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean,MQSession)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ProviderMessageReference recreateMessageReference(byte[] flatMR, MQSession session) throws JMSException {
/* 434 */     if (Trace.isOn) {
/* 435 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "recreateMessageReference(byte [ ],MQSession)", new Object[] { flatMR, session });
/*     */     }
/*     */     
/* 438 */     if (this.queueSet) {
/* 439 */       ProviderMessageReference traceRet1 = this.queueServices.recreateMessageReference(flatMR, session);
/* 440 */       if (Trace.isOn) {
/* 441 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "recreateMessageReference(byte [ ],MQSession)", traceRet1);
/*     */       }
/*     */       
/* 444 */       return traceRet1;
/*     */     } 
/* 446 */     this
/* 447 */       .sme = new PublishSubscribeSetupException(ConfigEnvironment.newException("MQJMS1111").getMessage());
/* 448 */     if (Trace.isOn) {
/* 449 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "recreateMessageReference(byte [ ],MQSession)", (Throwable)this.sme);
/*     */     }
/*     */     
/* 452 */     throw this.sme;
/*     */   }
/*     */ 
/*     */   
/*     */   void sendCommand(String topicName, int command, String postfix, byte[] subscriberId, boolean wait, boolean nonDurable, MQSession session) throws JMSException {
/* 457 */     if (Trace.isOn) {
/* 458 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)", new Object[] { topicName, 
/*     */             
/* 460 */             Integer.valueOf(command), postfix, subscriberId, Boolean.valueOf(wait), 
/* 461 */             Boolean.valueOf(nonDurable), session });
/*     */     }
/* 463 */     if (this.pubSubSet) {
/*     */       try {
/* 465 */         this.pubSubServices.sendCommand(topicName, command, postfix, subscriberId, wait, nonDurable, session);
/*     */       
/*     */       }
/* 468 */       catch (JMSException je) {
/* 469 */         if (Trace.isOn) {
/* 470 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)", (Throwable)je);
/*     */         }
/*     */         
/* 473 */         if (Trace.isOn) {
/* 474 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)", (Throwable)je, 1);
/*     */         }
/*     */         
/* 477 */         throw je;
/*     */       } 
/*     */     } else {
/* 480 */       this
/* 481 */         .sme = new PublishSubscribeSetupException(ConfigEnvironment.newException("MQJMS1111").getMessage());
/* 482 */       if (Trace.isOn) {
/* 483 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)", (Throwable)this.sme, 2);
/*     */       }
/*     */       
/* 486 */       throw this.sme;
/*     */     } 
/* 488 */     if (Trace.isOn) {
/* 489 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "sendCommand(String,int,String,byte [ ],boolean,boolean,MQSession)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean requestResponse(MQSession session) throws JMSException {
/* 496 */     if (Trace.isOn) {
/* 497 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "requestResponse(MQSession)", new Object[] { session });
/*     */     }
/*     */     
/* 500 */     if (this.pubSubSet) {
/*     */       try {
/* 502 */         boolean traceRet1 = this.pubSubServices.requestResponse(session);
/* 503 */         if (Trace.isOn) {
/* 504 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "requestResponse(MQSession)", 
/* 505 */               Boolean.valueOf(traceRet1));
/*     */         }
/* 507 */         return traceRet1;
/*     */       }
/* 509 */       catch (JMSException je) {
/* 510 */         if (Trace.isOn) {
/* 511 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "requestResponse(MQSession)", (Throwable)je);
/*     */         }
/*     */         
/* 514 */         if (Trace.isOn) {
/* 515 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "requestResponse(MQSession)", (Throwable)je, 1);
/*     */         }
/*     */         
/* 518 */         throw je;
/*     */       } 
/*     */     }
/* 521 */     this
/* 522 */       .sme = new PublishSubscribeSetupException(ConfigEnvironment.newException("MQJMS1111").getMessage());
/* 523 */     if (Trace.isOn) {
/* 524 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "requestResponse(MQSession)", (Throwable)this.sme, 2);
/*     */     }
/*     */     
/* 527 */     throw this.sme;
/*     */   }
/*     */ 
/*     */   
/*     */   MQQueue getResponseQueue(MQSession session) throws JMSException {
/* 532 */     if (Trace.isOn) {
/* 533 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getResponseQueue(MQSession)", new Object[] { session });
/*     */     }
/*     */     
/* 536 */     if (this.pubSubSet) {
/*     */       
/*     */       try {
/* 539 */         MQQueue traceRet1 = this.pubSubServices.getResponseQueue(session);
/* 540 */         if (Trace.isOn) {
/* 541 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getResponseQueue(MQSession)", traceRet1, 1);
/*     */         }
/*     */         
/* 544 */         return traceRet1;
/*     */       }
/* 546 */       catch (JMSException je) {
/* 547 */         if (Trace.isOn) {
/* 548 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getResponseQueue(MQSession)", (Throwable)je);
/*     */         }
/*     */ 
/*     */         
/* 552 */         if (Trace.isOn) {
/* 553 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getResponseQueue(MQSession)", (Throwable)je);
/*     */         }
/*     */         
/* 556 */         throw je;
/*     */       } 
/*     */     }
/* 559 */     if (Trace.isOn) {
/* 560 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "getResponseQueue(MQSession)", null, 2);
/*     */     }
/*     */     
/* 563 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initialisePubSub(MQSession session, boolean transacted, int acknowledgeMode) throws JMSException {
/* 571 */     if (Trace.isOn) {
/* 572 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "initialisePubSub(MQSession,boolean,int)", new Object[] { session, 
/*     */             
/* 574 */             Boolean.valueOf(transacted), Integer.valueOf(acknowledgeMode) });
/*     */     }
/* 576 */     if (this.pubSubSet) {
/*     */       try {
/* 578 */         this.pubSubServices.initialisePubSub(session.connection, transacted, acknowledgeMode, session);
/*     */       }
/* 580 */       catch (JMSException je) {
/* 581 */         if (Trace.isOn) {
/* 582 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "initialisePubSub(MQSession,boolean,int)", (Throwable)je);
/*     */         }
/*     */         
/* 585 */         if (Trace.isOn) {
/* 586 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "initialisePubSub(MQSession,boolean,int)", (Throwable)je);
/*     */         }
/*     */         
/* 589 */         throw je;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 594 */     if (Trace.isOn)
/* 595 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSServicesMgr", "initialisePubSub(MQSession,boolean,int)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\JMSServicesMgr.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */