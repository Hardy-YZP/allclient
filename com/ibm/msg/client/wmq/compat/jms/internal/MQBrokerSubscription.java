/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MQBrokerSubscription
/*     */   extends MQSubscription
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQBrokerSubscription.java";
/*     */   private byte[] deferredMessageId;
/*     */   
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscription", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQBrokerSubscription.java");
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
/*     */   public MQBrokerSubscription(MQSubscriptionEngine subscriptionEngine) {
/*  76 */     super(subscriptionEngine);
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscription", "<init>(MQSubscriptionEngine)", new Object[] { subscriptionEngine });
/*     */     }
/*     */     
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscription", "<init>(MQSubscriptionEngine)");
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
/*     */   public MQBrokerSubscription(MQSubscriptionEngine subscriptionEngine, MQSession session, boolean durable, boolean sharedQueue, String qmgrName, String clientId, String subName, WMQDestination topic, String selector, boolean noLocal, String queueName, MQQueue subscriberQueue, byte[] correlationId) {
/* 103 */     super(subscriptionEngine, session, durable, sharedQueue, qmgrName, clientId, subName, topic, selector, noLocal, queueName, subscriberQueue, correlationId);
/*     */     
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscription", "<init>(MQSubscriptionEngine,MQSession,boolean,boolean,String,String,String,WMQDestination,String,boolean,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,byte [ ])", new Object[] { subscriptionEngine, session, 
/*     */             
/* 108 */             Boolean.valueOf(durable), 
/* 109 */             Boolean.valueOf(sharedQueue), qmgrName, clientId, subName, topic, selector, 
/* 110 */             Boolean.valueOf(noLocal), queueName, subscriberQueue, correlationId });
/*     */     }
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscription", "<init>(MQSubscriptionEngine,MQSession,boolean,boolean,String,String,String,WMQDestination,String,boolean,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,byte [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFullName() {
/* 124 */     String traceRet1 = "JMS:" + getQmgrName() + ":" + getClientId() + ":" + getSubName();
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscription", "getFullName()", "getter", traceRet1);
/*     */     }
/*     */     
/* 129 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEscapedFullName() {
/* 135 */     String traceRet1 = "JMS:" + escapeString(getQmgrName()) + ":" + escapeString(getClientId()) + ":" + escapeString(getSubName());
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscription", "getEscapedFullName()", "getter", traceRet1);
/*     */     }
/*     */ 
/*     */     
/* 141 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getDeferredMsgId() {
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscription", "getDeferredMsgId()", "getter", this.deferredMessageId);
/*     */     }
/*     */     
/* 150 */     return this.deferredMessageId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeferredMsgId(byte[] deferredMessageId) {
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscription", "setDeferredMsgId(byte [ ])", "setter", deferredMessageId);
/*     */     }
/*     */     
/* 159 */     this.deferredMessageId = deferredMessageId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String escapeString(String bean) {
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscription", "escapeString(String)", new Object[] { bean });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 176 */     StringBuffer buffer = new StringBuffer();
/*     */     
/* 178 */     char c = Character.MIN_VALUE;
/* 179 */     String s = null;
/*     */ 
/*     */ 
/*     */     
/* 183 */     if (bean == null) {
/* 184 */       if (Trace.isOn) {
/* 185 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscription", "escapeString(String)", "", 1);
/*     */       }
/*     */       
/* 188 */       return "";
/*     */     } 
/* 190 */     for (int i = 0; i < bean.length(); i++) {
/* 191 */       c = bean.charAt(i);
/*     */       
/* 193 */       switch (c) {
/*     */         
/*     */         case '"':
/*     */         case ':':
/*     */         case ';':
/*     */         case '\\':
/* 199 */           s = "0000" + Integer.toString(c, 16);
/* 200 */           buffer.append("\\u" + s.substring(s.length() - 4, s.length()));
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/* 206 */           if (c < ' ' || c > '~') {
/* 207 */             s = "0000" + Integer.toString(c, 16);
/* 208 */             buffer.append("\\u" + s.substring(s.length() - 4, s.length())); break;
/*     */           } 
/* 210 */           buffer.append(c);
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 220 */     String traceRet1 = buffer.toString();
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQBrokerSubscription", "escapeString(String)", traceRet1, 2);
/*     */     }
/*     */     
/* 225 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQBrokerSubscription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */