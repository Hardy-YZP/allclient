/*     */ package com.ibm.msg.client.wmq.messages;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.provider.ProviderObjectMessage;
/*     */ import com.ibm.msg.client.provider.ProviderSession;
/*     */ import com.ibm.msg.client.wmq.common.internal.messages.WMQObjectMessage;
/*     */ import com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage;
/*     */ import com.ibm.msg.client.wmq.compat.jms.internal.MQSession;
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
/*     */ public class TransientObjectMessage
/*     */   extends TransientMessage
/*     */   implements ProviderObjectMessage
/*     */ {
/*     */   private static final long serialVersionUID = 1789907758388323742L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/messages/TransientObjectMessage.java";
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.msg.client.wmq.messages.TransientObjectMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/messages/TransientObjectMessage.java");
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
/*  56 */   private byte[] objBytes = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public TransientObjectMessage() {
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientObjectMessage", "<init>()");
/*     */     }
/*  64 */     this.messageClass = "jms_object";
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientObjectMessage", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearBody() throws JMSException {
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientObjectMessage", "clearBody()");
/*     */     }
/*  79 */     this.objBytes = null;
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientObjectMessage", "clearBody()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getSerializedObject() throws JMSException {
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientObjectMessage", "getSerializedObject()", "getter", this.objBytes);
/*     */     }
/*     */     
/*  94 */     return this.objBytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSerializedObject(byte[] objectBytes) throws JMSException {
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientObjectMessage", "setSerializedObject(byte [ ])", "setter", objectBytes);
/*     */     }
/*     */     
/* 105 */     this.objBytes = objectBytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMessage convertIntoWMQMessage(ProviderSession session) throws JMSException {
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientObjectMessage", "convertIntoWMQMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */     
/* 117 */     WMQObjectMessage wMQObjectMessage = new WMQObjectMessage();
/* 118 */     setJMSPropsOnProviderMessage((ProviderMessage)wMQObjectMessage);
/* 119 */     setPCPropertiesOnProviderMessage((ProviderMessage)wMQObjectMessage);
/* 120 */     wMQObjectMessage.setSerializedObject(getSerializedObject());
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientObjectMessage", "convertIntoWMQMessage(ProviderSession)", wMQObjectMessage);
/*     */     }
/*     */     
/* 125 */     return (ProviderMessage)wMQObjectMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMessage convertIntoMQMessage(ProviderSession session) throws JMSException {
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientObjectMessage", "convertIntoMQMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */     
/* 137 */     ProviderObjectMessage providerObjectMessage = ((MQSession)session).createObjectMessage();
/* 138 */     setJMSPropsOnProviderMessage((ProviderMessage)providerObjectMessage);
/* 139 */     setPCPropertiesOnProviderMessage((ProviderMessage)providerObjectMessage);
/* 140 */     ((JMSObjectMessage)providerObjectMessage).setSerializedObject(getSerializedObject());
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientObjectMessage", "convertIntoMQMessage(ProviderSession)", providerObjectMessage);
/*     */     }
/*     */     
/* 145 */     return (ProviderMessage)providerObjectMessage;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\messages\TransientObjectMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */