/*     */ package com.ibm.msg.client.wmq.messages;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.provider.ProviderSession;
/*     */ import com.ibm.msg.client.wmq.common.internal.messages.WMQNullMessage;
/*     */ import com.ibm.msg.client.wmq.compat.jms.internal.MQSession;
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
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
/*     */ public class TransientMessage
/*     */   extends TransientMessageHeader
/*     */   implements ProviderMessage, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4767727094325610499L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/messages/TransientMessage.java";
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.msg.client.wmq.messages.TransientMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/messages/TransientMessage.java");
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
/*     */   public void clearBody() throws JMSException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMessage convertIntoWMQMessage(ProviderSession session) throws JMSException {
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessage", "convertIntoWMQMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */     
/*  71 */     WMQNullMessage wMQNullMessage = new WMQNullMessage();
/*  72 */     setJMSPropsOnProviderMessage((ProviderMessage)wMQNullMessage);
/*  73 */     setPCPropertiesOnProviderMessage((ProviderMessage)wMQNullMessage);
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessage", "convertIntoWMQMessage(ProviderSession)", wMQNullMessage);
/*     */     }
/*     */     
/*  78 */     return (ProviderMessage)wMQNullMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMessage convertIntoMQMessage(ProviderSession session) throws JMSException {
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessage", "convertIntoMQMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */     
/*  91 */     ProviderMessage retValue = ((MQSession)session).createMessage();
/*  92 */     setJMSPropsOnProviderMessage(retValue);
/*  93 */     setPCPropertiesOnProviderMessage(retValue);
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessage", "convertIntoMQMessage(ProviderSession)", retValue);
/*     */     }
/*     */     
/*  98 */     return retValue;
/*     */   }
/*     */   
/*     */   protected void setJMSPropsOnProviderMessage(ProviderMessage message) throws JMSException {
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessage", "setJMSPropsOnProviderMessage(ProviderMessage)", "setter", message);
/*     */     }
/*     */     
/* 106 */     message.setJMSCorrelationID(getJMSCorrelationID());
/* 107 */     Integer deliveryMode = getJMSDeliveryMode();
/* 108 */     assert deliveryMode != null;
/*     */     
/* 110 */     message.setJMSDeliveryMode(deliveryMode.intValue());
/* 111 */     message.setJMSDestinationAsString(getJMSDestinationAsString());
/*     */     
/* 113 */     Long expiration = getJMSExpiration();
/* 114 */     assert expiration != null;
/* 115 */     message.setJMSExpiration(expiration.longValue());
/*     */     
/* 117 */     message.setJMSMessageID(getJMSMessageID());
/*     */     
/* 119 */     Integer priority = getJMSPriority();
/* 120 */     assert priority != null;
/* 121 */     message.setJMSPriority(priority.intValue());
/*     */     
/* 123 */     Boolean redelivered = getJMSRedelivered();
/* 124 */     assert redelivered != null;
/* 125 */     message.setJMSRedelivered(redelivered.booleanValue());
/*     */     
/* 127 */     message.setJMSReplyToAsString(getJMSReplyToAsString());
/*     */     
/* 129 */     Long timestamp = getJMSTimestamp();
/* 130 */     assert timestamp != null;
/* 131 */     message.setJMSTimestamp(timestamp.longValue());
/*     */     
/* 133 */     message.setJMSType(getJMSType());
/*     */   }
/*     */   
/*     */   protected void setPCPropertiesOnProviderMessage(ProviderMessage message) throws JMSException {
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessage", "setPCPropertiesOnProviderMessage(ProviderMessage)", "setter", message);
/*     */     }
/*     */     
/* 141 */     Enumeration<String> e = getPropertyNames();
/* 142 */     while (e.hasMoreElements()) {
/* 143 */       String key = e.nextElement();
/* 144 */       message.setObjectProperty(key, getObjectProperty(key));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasBody() {
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessage", "hasBody()");
/*     */     }
/*     */     
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessage", "hasBody()", 
/* 156 */           Boolean.valueOf(false));
/*     */     }
/* 158 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\messages\TransientMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */