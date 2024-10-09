/*     */ package com.ibm.msg.client.wmq.messages;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.provider.ProviderSession;
/*     */ import com.ibm.msg.client.provider.ProviderTextMessage;
/*     */ import com.ibm.msg.client.wmq.common.internal.messages.WMQTextMessage;
/*     */ import com.ibm.msg.client.wmq.compat.jms.internal.JMSTextMessage;
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
/*     */ public class TransientTextMessage
/*     */   extends TransientMessage
/*     */   implements ProviderTextMessage
/*     */ {
/*     */   private static final long serialVersionUID = 7956271908542965035L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/messages/TransientTextMessage.java";
/*     */   private String text;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.msg.client.wmq.messages.TransientTextMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/messages/TransientTextMessage.java");
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
/*     */   public TransientTextMessage() {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientTextMessage", "<init>()");
/*     */     }
/*  63 */     this.messageClass = "jms_text";
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientTextMessage", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getText() throws JMSException {
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientTextMessage", "getText()", "getter", this.text);
/*     */     }
/*     */     
/*  78 */     return this.text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setText(String text) throws JMSException {
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientTextMessage", "setText(String)", "setter", text);
/*     */     }
/*     */     
/*  89 */     this.text = text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearBody() {
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientTextMessage", "clearBody()");
/*     */     }
/* 100 */     this.text = null;
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientTextMessage", "clearBody()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMessage convertIntoWMQMessage(ProviderSession session) throws JMSException {
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientTextMessage", "convertIntoWMQMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */     
/* 116 */     WMQTextMessage wMQTextMessage = new WMQTextMessage();
/* 117 */     setJMSPropsOnProviderMessage((ProviderMessage)wMQTextMessage);
/* 118 */     setPCPropertiesOnProviderMessage((ProviderMessage)wMQTextMessage);
/* 119 */     wMQTextMessage.setText(getText());
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientTextMessage", "convertIntoWMQMessage(ProviderSession)", wMQTextMessage);
/*     */     }
/*     */     
/* 124 */     return (ProviderMessage)wMQTextMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMessage convertIntoMQMessage(ProviderSession session) throws JMSException {
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientTextMessage", "convertIntoMQMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */     
/* 136 */     ProviderTextMessage providerTextMessage = ((MQSession)session).createTextMessage();
/* 137 */     setJMSPropsOnProviderMessage((ProviderMessage)providerTextMessage);
/* 138 */     setPCPropertiesOnProviderMessage((ProviderMessage)providerTextMessage);
/* 139 */     ((JMSTextMessage)providerTextMessage).setText(getText());
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientTextMessage", "convertIntoMQMessage(ProviderSession)", providerTextMessage);
/*     */     }
/*     */     
/* 144 */     return (ProviderMessage)providerTextMessage;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\messages\TransientTextMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */