/*     */ package com.ibm.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.internal.JmsTextMessageImpl;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.ObjectStreamField;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JMSTextMessage
/*     */   extends JMSMessage
/*     */   implements TextMessage
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/jms/JMSTextMessage.java";
/*     */   static final long serialVersionUID = -7013263043146565366L;
/*     */   
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.jms.JMSTextMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/jms/JMSTextMessage.java");
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
/*  79 */   private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("messageText", String.class) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public JMSTextMessage() {
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.entry(this, "com.ibm.jms.JMSTextMessage", "<init>()");
/*     */     }
/*  97 */     assert false : "Do not call a message's default constructor, use javax.jms.Session methods instead";
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.exit(this, "com.ibm.jms.JMSTextMessage", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected JMSTextMessage(Message delegateMsg) {
/* 106 */     super(delegateMsg);
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.entry(this, "com.ibm.jms.JMSTextMessage", "<init>(Message)", new Object[] { delegateMsg });
/*     */     }
/*     */     
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.exit(this, "com.ibm.jms.JMSTextMessage", "<init>(Message)");
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
/*     */   public String getText() throws JMSException {
/* 124 */     String traceRet1 = ((TextMessage)this.delegateMsg).getText();
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.data(this, "com.ibm.jms.JMSTextMessage", "getText()", "getter", traceRet1);
/*     */     }
/* 128 */     return traceRet1;
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
/*     */   public void setText(String messageText) throws JMSException {
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.data(this, "com.ibm.jms.JMSTextMessage", "setText(String)", "setter", messageText);
/*     */     }
/* 144 */     ((TextMessage)this.delegateMsg).setText(messageText);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.entry(this, "com.ibm.jms.JMSTextMessage", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 165 */       String connectionType = "com.ibm.msg.client.wmq";
/* 166 */       Message superClassMsg = null;
/* 167 */       if (this.delegateMsg != null) {
/* 168 */         superClassMsg = this.delegateMsg;
/*     */       }
/* 170 */       this.delegateMsg = (Message)new JmsTextMessageImpl(connectionType, superClassMsg);
/*     */     }
/* 172 */     catch (JMSException e) {
/* 173 */       if (Trace.isOn) {
/* 174 */         Trace.catchBlock(this, "com.ibm.jms.JMSTextMessage", "readObject(java.io.ObjectInputStream)", (Throwable)e, 1);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 186 */     ObjectInputStream.GetField fields = in.readFields();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 204 */       if (!fields.defaulted("messageText"))
/*     */       {
/* 206 */         String text = (String)fields.get("messageText", (Object)null);
/* 207 */         if (text != null) {
/* 208 */           setText(text);
/*     */         }
/*     */       }
/*     */     
/* 212 */     } catch (JMSException je) {
/* 213 */       if (Trace.isOn) {
/* 214 */         Trace.catchBlock(this, "com.ibm.jms.JMSTextMessage", "readObject(java.io.ObjectInputStream)", (Throwable)je, 2);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.exit(this, "com.ibm.jms.JMSTextMessage", "readObject(java.io.ObjectInputStream)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.entry(this, "com.ibm.jms.JMSTextMessage", "writeObject(java.io.ObjectOutputStream)", new Object[] { out });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 237 */     ObjectOutputStream.PutField fields = out.putFields();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 243 */       fields.put("messageText", getText());
/*     */     }
/* 245 */     catch (JMSException je) {
/* 246 */       if (Trace.isOn) {
/* 247 */         Trace.catchBlock(this, "com.ibm.jms.JMSTextMessage", "writeObject(java.io.ObjectOutputStream)", (Throwable)je);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 256 */     out.writeFields();
/*     */     
/* 258 */     if (Trace.isOn)
/* 259 */       Trace.exit(this, "com.ibm.jms.JMSTextMessage", "writeObject(java.io.ObjectOutputStream)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\jms\JMSTextMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */