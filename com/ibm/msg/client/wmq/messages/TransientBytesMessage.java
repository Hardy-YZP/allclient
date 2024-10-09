/*     */ package com.ibm.msg.client.wmq.messages;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderBytesMessage;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.provider.ProviderSession;
/*     */ import com.ibm.msg.client.wmq.common.internal.messages.WMQBytesMessage;
/*     */ import com.ibm.msg.client.wmq.compat.jms.internal.JMSBytesMessage;
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
/*     */ public class TransientBytesMessage
/*     */   extends TransientMessage
/*     */   implements ProviderBytesMessage
/*     */ {
/*     */   private static final long serialVersionUID = 2147424868495621529L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/messages/TransientBytesMessage.java";
/*     */   private byte[] bytes;
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.msg.client.wmq.messages.TransientBytesMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/messages/TransientBytesMessage.java");
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
/*     */   public TransientBytesMessage() {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientBytesMessage", "<init>()");
/*     */     }
/*  62 */     this.messageClass = "jms_bytes";
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientBytesMessage", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearBody() throws JMSException {
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientBytesMessage", "clearBody()");
/*     */     }
/*  77 */     this.bytes = new byte[0];
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientBytesMessage", "clearBody()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBytes() throws JMSException {
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientBytesMessage", "getBytes()", "getter", this.bytes);
/*     */     }
/*     */     
/*  92 */     return this.bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBytes(byte[] bytes) throws JMSException {
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientBytesMessage", "setBytes(byte [ ])", "setter", bytes);
/*     */     }
/*     */     
/* 103 */     this.bytes = bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMessage convertIntoWMQMessage(ProviderSession session) throws JMSException {
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientBytesMessage", "convertIntoWMQMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */     
/* 115 */     WMQBytesMessage wMQBytesMessage = new WMQBytesMessage();
/* 116 */     setJMSPropsOnProviderMessage((ProviderMessage)wMQBytesMessage);
/* 117 */     setPCPropertiesOnProviderMessage((ProviderMessage)wMQBytesMessage);
/* 118 */     wMQBytesMessage.setBytes(getBytes());
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientBytesMessage", "convertIntoWMQMessage(ProviderSession)", wMQBytesMessage);
/*     */     }
/*     */     
/* 123 */     return (ProviderMessage)wMQBytesMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMessage convertIntoMQMessage(ProviderSession session) throws JMSException {
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientBytesMessage", "convertIntoMQMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */     
/* 135 */     ProviderBytesMessage providerBytesMessage = ((MQSession)session).createBytesMessage();
/* 136 */     setJMSPropsOnProviderMessage((ProviderMessage)providerBytesMessage);
/* 137 */     setPCPropertiesOnProviderMessage((ProviderMessage)providerBytesMessage);
/* 138 */     ((JMSBytesMessage)providerBytesMessage).setBytes(getBytes());
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientBytesMessage", "convertIntoMQMessage(ProviderSession)", providerBytesMessage);
/*     */     }
/*     */     
/* 143 */     return (ProviderMessage)providerBytesMessage;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\messages\TransientBytesMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */