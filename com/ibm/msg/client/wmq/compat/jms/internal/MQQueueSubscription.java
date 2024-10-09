/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQMessage;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*     */ import java.util.Arrays;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQQueueSubscription
/*     */   extends MQSubscription
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQQueueSubscription.java";
/*     */   static final String SUBENTRY_SIGNATURE = "MQJMS_PS_SUBENTRY_v2";
/*     */   static final String SUBENTRY_v1SIGNATURE = "MQJMS_PS_ADMIN_ENTRY";
/*     */   static final String SIGNATURE = "MQJMS_PS_SUBENTRY_v2";
/*     */   private char subscriberState;
/*     */   private byte[] statusMgrId;
/*     */   
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscription", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQQueueSubscription.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean validity = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQQueueSubscription(MQSubscriptionEngine subscriptionEngine, MQSession session, boolean durable, boolean sharedQueue, String qmgrName, String clientId, String subName, WMQDestination topic, String selector, boolean noLocal, String queueName, MQQueue subscriberQueue, byte[] correlationId) {
/* 104 */     super(subscriptionEngine, session, durable, sharedQueue, qmgrName, clientId, subName, topic, selector, noLocal, queueName, subscriberQueue, correlationId);
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscription", "<init>(MQSubscriptionEngine,MQSession,boolean,boolean,String,String,String,WMQDestination,String,boolean,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,byte [ ])", new Object[] { subscriptionEngine, session, 
/*     */             
/* 108 */             Boolean.valueOf(durable), 
/* 109 */             Boolean.valueOf(sharedQueue), qmgrName, clientId, subName, topic, selector, 
/* 110 */             Boolean.valueOf(noLocal), queueName, subscriberQueue, correlationId });
/*     */     }
/*     */     
/* 113 */     this.subscriberState = 'u';
/* 114 */     this.validity = true;
/*     */     
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscription", "<init>(MQSubscriptionEngine,MQSession,boolean,boolean,String,String,String,WMQDestination,String,boolean,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,byte [ ])");
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
/*     */ 
/*     */   
/*     */   public MQQueueSubscription(MQSubscriptionEngine subscriptionEngine, MQSession session, MQMessage m) {
/* 136 */     super(subscriptionEngine, session, false, false, session.getQMName(), null, null, null, null, false, null, null, null);
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscription", "<init>(MQSubscriptionEngine,MQSession,com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", new Object[] { subscriptionEngine, session, m });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 144 */     int version = 2;
/*     */     try {
/* 146 */       setQmgrName(session.getQMName());
/*     */ 
/*     */       
/* 149 */       String sig = m.readStringOfByteLength("MQJMS_PS_SUBENTRY_v2".length());
/*     */       
/* 151 */       if (!sig.equals("MQJMS_PS_SUBENTRY_v2"))
/*     */       {
/* 153 */         if (!sig.equals("MQJMS_PS_SUBENTRY_v2")) {
/* 154 */           version = 2;
/* 155 */           if (sig.equals("MQJMS_PS_ADMIN_ENTRY")) {
/* 156 */             if (Trace.isOn) {
/* 157 */               Trace.traceData(this, "v1 style subscriber entry found on DurSubAdmin queue.", null);
/*     */             }
/* 159 */             version = 1;
/*     */           } else {
/* 161 */             if (Trace.isOn) {
/* 162 */               Trace.traceData(this, "Non-DurSubEntry message on DurSubAdmin queue!", null);
/*     */             }
/* 164 */             if (Trace.isOn) {
/* 165 */               Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscription", "<init>(MQSubscriptionEngine,MQSession,com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", 1);
/*     */             }
/*     */ 
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 174 */       String fullName = null;
/*     */       
/* 176 */       int l = m.readInt();
/* 177 */       if (l > 0) {
/* 178 */         fullName = m.readStringOfByteLength(l);
/* 179 */         int p = fullName.indexOf(":");
/* 180 */         if (p == -1) {
/* 181 */           Exception traceRet1 = new Exception();
/* 182 */           if (Trace.isOn) {
/* 183 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscription", "<init>(MQSubscriptionEngine,MQSession,com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", traceRet1);
/*     */           }
/*     */ 
/*     */           
/* 187 */           throw traceRet1;
/*     */         } 
/* 189 */         String clientId = fullName.substring(0, p);
/* 190 */         String subName = fullName.substring(p + 1);
/* 191 */         setClientId(clientId);
/* 192 */         setSubName(subName);
/*     */       } else {
/* 194 */         setClientId("");
/* 195 */         setSubName("");
/*     */       } 
/*     */       
/* 198 */       l = m.readInt();
/* 199 */       setTopic(m.readStringOfByteLength(l));
/*     */       
/* 201 */       l = m.readInt();
/* 202 */       setQueueName(m.readStringOfByteLength(l));
/*     */       
/* 204 */       l = m.readInt();
/* 205 */       if (l > 0) {
/* 206 */         setSelector(m.readStringOfByteLength(l));
/*     */       } else {
/* 208 */         setSelector("");
/*     */       } 
/*     */       
/* 211 */       setNoLocal((m.readChar() == 'y'));
/* 212 */       if (version != 1) {
/* 213 */         setSharedQueue((m.readChar() == 'y'));
/* 214 */         this.subscriberState = m.readChar();
/*     */         
/* 216 */         this.statusMgrId = m.correlationId;
/* 217 */         setCorrelationId(m.messageId);
/*     */       } 
/*     */       
/* 220 */       this.validity = true;
/*     */     }
/* 222 */     catch (Exception e) {
/* 223 */       if (Trace.isOn) {
/* 224 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscription", "<init>(MQSubscriptionEngine,MQSession,com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", e);
/*     */       }
/*     */ 
/*     */       
/* 228 */       if (Trace.isOn) {
/* 229 */         Trace.traceData(this, "Could not create MQQueueSubscription.", null);
/*     */       }
/* 231 */       this.validity = false;
/*     */     } 
/*     */     
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscription", "<init>(MQSubscriptionEngine,MQSession,com.ibm.msg.client.wmq.compat.base.internal.MQMessage)", 2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setSubscriberState(char subscriberState) {
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscription", "setSubscriberState(char)", "setter", 
/* 247 */           Character.valueOf(subscriberState));
/*     */     }
/* 249 */     this.subscriberState = subscriberState;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   char getSubscriberState() {
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscription", "getSubscriberState()", "getter", 
/* 257 */           Character.valueOf(this.subscriberState));
/*     */     }
/* 259 */     return this.subscriberState;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setStatusMgrId(byte[] statusMgrId) {
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscription", "setStatusMgrId(byte [ ])", "setter", statusMgrId);
/*     */     }
/*     */     
/* 269 */     this.statusMgrId = statusMgrId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   byte[] getStatusMgrId() {
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscription", "getStatusMgrId()", "getter", this.statusMgrId);
/*     */     }
/*     */     
/* 279 */     return this.statusMgrId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isValid() {
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscription", "isValid()", "getter", 
/* 287 */           Boolean.valueOf(this.validity));
/*     */     }
/* 289 */     return this.validity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MQMessage toMessage() {
/* 300 */     if (Trace.isOn) {
/* 301 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscription", "toMessage()");
/*     */     }
/*     */ 
/*     */     
/* 305 */     String subName = getSubName();
/* 306 */     String clientId = getClientId();
/* 307 */     String topic = getTopic();
/* 308 */     String queueName = getQueueName();
/* 309 */     String selector = getSelector();
/* 310 */     byte[] correlationId = getCorrelationId();
/*     */     
/* 312 */     if (Trace.isOn) {
/* 313 */       Trace.traceData(this, "subName: " + subName, null);
/* 314 */       Trace.traceData(this, "clientId: " + clientId, null);
/* 315 */       Trace.traceData(this, "topic: " + topic, null);
/* 316 */       Trace.traceData(this, "queueName: " + queueName, null);
/* 317 */       Trace.traceData(this, "selector: " + selector, null);
/* 318 */       Trace.traceData(this, "correlationId: " + Arrays.toString(correlationId), null);
/*     */     } 
/*     */ 
/*     */     
/* 322 */     MQMessage newMessage = new MQMessage();
/*     */     
/*     */     try {
/*     */       String fullName;
/*     */       
/* 327 */       if (isDurable()) {
/* 328 */         fullName = clientId + ":" + subName;
/*     */       } else {
/* 330 */         fullName = "";
/*     */       } 
/*     */       
/* 333 */       if (Trace.isOn) {
/* 334 */         Trace.traceData(this, "Writing Signature: MQJMS_PS_SUBENTRY_v2", null);
/*     */       }
/*     */       
/* 337 */       newMessage.writeString("MQJMS_PS_SUBENTRY_v2");
/*     */       
/* 339 */       if (Trace.isOn) {
/* 340 */         Trace.traceData(this, "Writing fullNameLength: " + fullName.length(), null);
/*     */       }
/* 342 */       newMessage.writeInt(fullName.length());
/* 343 */       if (Trace.isOn) {
/* 344 */         Trace.traceData(this, "Writing fullName: " + fullName, null);
/*     */       }
/* 346 */       newMessage.writeString(fullName);
/*     */       
/* 348 */       newMessage.writeInt(topic.length());
/* 349 */       newMessage.writeString(topic);
/*     */       
/* 351 */       newMessage.writeInt(queueName.length());
/* 352 */       newMessage.writeString(queueName);
/*     */       
/* 354 */       if (selector == null) {
/* 355 */         newMessage.writeInt(0);
/*     */       } else {
/* 357 */         newMessage.writeInt(selector.length());
/* 358 */         newMessage.writeString(selector);
/*     */       } 
/*     */       
/* 361 */       newMessage.writeChar(getNoLocal() ? 121 : 110);
/* 362 */       newMessage.writeChar(isSharedQueue() ? 121 : 110);
/* 363 */       newMessage.writeChar(this.subscriberState);
/*     */       
/* 365 */       if (correlationId != null) {
/* 366 */         newMessage.messageId = correlationId;
/*     */       }
/* 368 */       if (this.statusMgrId != null) {
/* 369 */         newMessage.correlationId = this.statusMgrId;
/*     */       
/*     */       }
/*     */     }
/* 373 */     catch (Exception e) {
/* 374 */       if (Trace.isOn) {
/* 375 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscription", "toMessage()", e);
/*     */       }
/*     */       
/* 378 */       if (Trace.isOn) {
/* 379 */         Trace.traceData(this, "Could not convert MQQueueSubscription to MQMessage", null);
/*     */       }
/* 381 */       newMessage = null;
/*     */     } 
/*     */     
/* 384 */     if (Trace.isOn) {
/* 385 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscription", "toMessage()", newMessage);
/*     */     }
/*     */     
/* 388 */     return newMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFullName() {
/* 396 */     String traceRet1 = getClientId() + ":" + getSubName();
/* 397 */     if (Trace.isOn) {
/* 398 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueSubscription", "getFullName()", "getter", traceRet1);
/*     */     }
/*     */     
/* 401 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQQueueSubscription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */