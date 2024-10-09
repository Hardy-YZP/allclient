/*     */ package com.ibm.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.internal.JmsObjectMessageImpl;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.ObjectStreamField;
/*     */ import java.io.Serializable;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Message;
/*     */ import javax.jms.ObjectMessage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JMSObjectMessage
/*     */   extends JMSMessage
/*     */   implements ObjectMessage
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/jms/JMSObjectMessage.java";
/*     */   static final long serialVersionUID = -9160649637541619341L;
/*     */   
/*     */   static {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.data("com.ibm.jms.JMSObjectMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/jms/JMSObjectMessage.java");
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
/*  82 */   private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("messageBytes", byte[].class) };
/*     */ 
/*     */ 
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
/*     */   public JMSObjectMessage() {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, "com.ibm.jms.JMSObjectMessage", "<init>()");
/*     */     }
/*  99 */     assert false : "Do not call a message's default constructor, use javax.jms.Session methods instead";
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.exit(this, "com.ibm.jms.JMSObjectMessage", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected JMSObjectMessage(Message delegateMsg) {
/* 108 */     super(delegateMsg);
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.entry(this, "com.ibm.jms.JMSObjectMessage", "<init>(Message)", new Object[] { delegateMsg });
/*     */     }
/*     */     
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.jms.JMSObjectMessage", "<init>(Message)");
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
/*     */   public Serializable getObject() throws JMSException {
/* 126 */     Serializable traceRet1 = ((ObjectMessage)this.delegateMsg).getObject();
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.data(this, "com.ibm.jms.JMSObjectMessage", "getObject()", "getter", traceRet1);
/*     */     }
/* 130 */     return traceRet1;
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
/*     */   private void setObjectAsBytes(byte[] bytes) throws JMSException {
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.data(this, "com.ibm.jms.JMSObjectMessage", "setObjectAsBytes(byte [ ])", "setter", bytes);
/*     */     }
/*     */ 
/*     */     
/* 148 */     if (this.delegateMsg instanceof JmsObjectMessageImpl) {
/* 149 */       ((JmsObjectMessageImpl)this.delegateMsg).setObjectAsBytes(bytes);
/*     */     
/*     */     }
/* 152 */     else if (Trace.isOn) {
/* 153 */       Trace.data(this, "com.ibm.jms.JMSObjectMessage", "setObjectAsBytes(byte[])", "Could not set Object as delegateMessage is not correct type", this.delegateMsg.getClass()
/* 154 */           .getName());
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
/*     */   public void setObject(Serializable object) throws JMSException {
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.data(this, "com.ibm.jms.JMSObjectMessage", "setObject(Serializable)", "setter", object);
/*     */     }
/*     */     
/* 173 */     ((ObjectMessage)this.delegateMsg).setObject(object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 183 */     if (Trace.isOn) {
/* 184 */       Trace.entry(this, "com.ibm.jms.JMSObjectMessage", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 194 */       String connectionType = "com.ibm.msg.client.wmq";
/* 195 */       Message superClassMsg = null;
/* 196 */       if (this.delegateMsg != null) {
/* 197 */         superClassMsg = this.delegateMsg;
/*     */       }
/* 199 */       this.delegateMsg = (Message)new JmsObjectMessageImpl(connectionType, superClassMsg);
/*     */     }
/* 201 */     catch (JMSException e) {
/* 202 */       if (Trace.isOn) {
/* 203 */         Trace.catchBlock(this, "com.ibm.jms.JMSObjectMessage", "readObject(java.io.ObjectInputStream)", (Throwable)e, 1);
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
/* 215 */     ObjectInputStream.GetField fields = in.readFields();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 233 */       if (!fields.defaulted("messageBytes"))
/*     */       {
/* 235 */         byte[] messageBytes = (byte[])fields.get("messageBytes", (Object)null);
/* 236 */         if (messageBytes != null) {
/* 237 */           setObjectAsBytes(messageBytes);
/*     */         }
/*     */       }
/*     */     
/* 241 */     } catch (JMSException je) {
/* 242 */       if (Trace.isOn) {
/* 243 */         Trace.catchBlock(this, "com.ibm.jms.JMSObjectMessage", "readObject(java.io.ObjectInputStream)", (Throwable)je, 2);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.exit(this, "com.ibm.jms.JMSObjectMessage", "readObject(java.io.ObjectInputStream)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.entry(this, "com.ibm.jms.JMSObjectMessage", "writeObject(java.io.ObjectOutputStream)", new Object[] { out });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 266 */     ObjectOutputStream.PutField fields = out.putFields();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 272 */       ByteArrayOutputStream serialisedObject = new ByteArrayOutputStream();
/* 273 */       ObjectOutputStream objOut = new ObjectOutputStream(serialisedObject);
/* 274 */       objOut.writeObject(getObject());
/* 275 */       fields.put("messageBytes", serialisedObject.toByteArray());
/*     */     }
/* 277 */     catch (JMSException je) {
/* 278 */       if (Trace.isOn) {
/* 279 */         Trace.catchBlock(this, "com.ibm.jms.JMSObjectMessage", "writeObject(java.io.ObjectOutputStream)", (Throwable)je);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 288 */     out.writeFields();
/*     */     
/* 290 */     if (Trace.isOn)
/* 291 */       Trace.exit(this, "com.ibm.jms.JMSObjectMessage", "writeObject(java.io.ObjectOutputStream)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\jms\JMSObjectMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */