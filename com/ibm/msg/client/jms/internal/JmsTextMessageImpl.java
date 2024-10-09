/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.provider.ProviderTextMessage;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Message;
/*     */ import javax.jms.TextMessage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsTextMessageImpl
/*     */   extends JmsMessageImpl
/*     */   implements TextMessage
/*     */ {
/*     */   private static final long serialVersionUID = 4900836031892307240L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsTextMessageImpl.java";
/*     */   private ProviderTextMessage providerTextMessage;
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsTextMessageImpl.java");
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
/*     */   public JmsTextMessageImpl(JmsSessionImpl session) throws JMSException {
/*  69 */     super(session);
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "<init>(JmsSessionImpl)", new Object[] { session });
/*     */     }
/*     */ 
/*     */     
/*  75 */     this.messageType = "jms_text";
/*     */     
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "<init>(JmsSessionImpl)");
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
/*     */   public JmsTextMessageImpl(String connectionType, Message message) throws JMSException {
/*  94 */     super(connectionType, message);
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "<init>(String,Message)", new Object[] { connectionType, message });
/*     */     }
/*     */ 
/*     */     
/* 100 */     this.messageType = "jms_text";
/*     */     
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "<init>(String,Message)");
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
/*     */   JmsTextMessageImpl(ProviderTextMessage newMsg, JmsSessionImpl newSess, String connectionTypeName) throws JMSException {
/* 116 */     super((ProviderMessage)newMsg, newSess, connectionTypeName);
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "<init>(ProviderTextMessage,JmsSessionImpl,String)", new Object[] { newMsg, newSess, connectionTypeName });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     this.providerTextMessage = newMsg;
/* 125 */     this.messageType = "jms_text";
/*     */ 
/*     */ 
/*     */     
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "<init>(ProviderTextMessage,JmsSessionImpl,String)");
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
/*     */   JmsTextMessageImpl(JmsSessionImpl session, TextMessage textMessage) throws JMSException {
/* 142 */     super(session, (Message)textMessage);
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "<init>(JmsSessionImpl,TextMessage)", new Object[] { session, textMessage });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 149 */     setText(textMessage.getText());
/*     */     
/* 151 */     this.messageType = "jms_text";
/*     */     
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "<init>(JmsSessionImpl,TextMessage)");
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
/*     */   JmsTextMessageImpl(JmsSessionImpl session, Message message, String text) throws JMSException {
/* 166 */     super(session, message);
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "<init>(JmsSessionImpl,Message,String)", new Object[] { session, message, text });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 173 */     setText(text);
/*     */     
/* 175 */     this.messageType = "jms_text";
/*     */     
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "<init>(JmsSessionImpl,Message,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setText(String txt) throws JMSException {
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "setText(String)", "setter", txt);
/*     */     }
/*     */ 
/*     */     
/* 193 */     checkBodyWriteable("setText");
/* 194 */     this.providerTextMessage.setText(txt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getText() throws JMSException {
/* 205 */     String txt = this.providerTextMessage.getText();
/*     */     
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "getText()", "getter", txt);
/*     */     }
/*     */     
/* 211 */     return txt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "toString()");
/*     */     }
/*     */     
/* 225 */     String val = super.toString();
/*     */     try {
/* 227 */       String thisText = getText();
/*     */       
/* 229 */       if (thisText == null) {
/* 230 */         thisText = "<null>";
/*     */       
/*     */       }
/* 233 */       else if (thisText.length() > 100) {
/* 234 */         thisText = thisText.substring(0, 100) + " ...";
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 239 */       val = val + "\n" + thisText;
/*     */     
/*     */     }
/* 242 */     catch (JMSException e) {
/* 243 */       if (Trace.isOn) {
/* 244 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "toString()", (Throwable)e);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 251 */     if (Trace.isOn) {
/* 252 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "toString()", val);
/*     */     }
/* 254 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ProviderMessage createProviderMessage(JmsSessionImpl session) throws Exception {
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "createProviderMessage(JmsSessionImpl)", new Object[] { session });
/*     */     }
/*     */ 
/*     */     
/* 268 */     if (session != null) {
/* 269 */       this.providerTextMessage = getProviderMessageFactory().createTextMessage(session.getProviderSession());
/*     */     } else {
/*     */       
/* 272 */       this.providerTextMessage = getProviderMessageFactory().createTextMessage(null);
/*     */     } 
/*     */     
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsTextMessageImpl", "createProviderMessage(JmsSessionImpl)", this.providerTextMessage);
/*     */     }
/*     */     
/* 279 */     return (ProviderMessage)this.providerTextMessage;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsTextMessageImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */