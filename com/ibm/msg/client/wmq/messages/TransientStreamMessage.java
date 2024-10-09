/*     */ package com.ibm.msg.client.wmq.messages;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.provider.ProviderSession;
/*     */ import com.ibm.msg.client.provider.ProviderStreamMessage;
/*     */ import com.ibm.msg.client.wmq.common.internal.messages.WMQStreamMessage;
/*     */ import com.ibm.msg.client.wmq.compat.jms.internal.JMSStreamMessage;
/*     */ import com.ibm.msg.client.wmq.compat.jms.internal.MQSession;
/*     */ import java.util.ArrayList;
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
/*     */ public class TransientStreamMessage
/*     */   extends TransientMessage
/*     */   implements ProviderStreamMessage
/*     */ {
/*     */   private static final long serialVersionUID = -9203783229043607847L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/messages/TransientStreamMessage.java";
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.msg.client.wmq.messages.TransientStreamMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/messages/TransientStreamMessage.java");
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
/*  61 */   private ArrayList<Object> stream = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int streamPos;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransientStreamMessage() throws JMSException {
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "<init>()");
/*     */     }
/*  75 */     this.messageClass = "jms_stream";
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object readObject() throws JMSException {
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "readObject()");
/*     */     }
/*  89 */     if (this.streamPos >= this.stream.size()) {
/*  90 */       JMSException je = (JMSException)NLSServices.createException("JMSWMQ0017", null);
/*     */       
/*  92 */       if (Trace.isOn) {
/*  93 */         Trace.throwing(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "readObject()", (Throwable)je);
/*     */       }
/*     */       
/*  96 */       throw je;
/*     */     } 
/*     */     
/*  99 */     Object object = this.stream.get(this.streamPos);
/* 100 */     this.streamPos++;
/*     */     
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "readObject()", object);
/*     */     }
/*     */     
/* 106 */     return object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() throws JMSException {
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "reset()");
/*     */     }
/* 116 */     this.streamPos = 0;
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "reset()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stepBack() throws JMSException {
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "stepBack()");
/*     */     }
/* 130 */     if (this.streamPos > 0) {
/* 131 */       this.streamPos--;
/*     */     }
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "stepBack()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void clearBody() throws JMSException {
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "clearBody()");
/*     */     }
/* 147 */     this.streamPos = 0;
/* 148 */     this.stream.clear();
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "clearBody()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeBoolean(boolean value) throws JMSException {
/* 159 */     if (Trace.isOn)
/* 160 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeBoolean(boolean)", new Object[] {
/* 161 */             Boolean.valueOf(value)
/*     */           }); 
/* 163 */     this.stream.add(Boolean.valueOf(value));
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeBoolean(boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeByte(byte value) throws JMSException {
/* 175 */     if (Trace.isOn)
/* 176 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeByte(byte)", new Object[] {
/* 177 */             Byte.valueOf(value)
/*     */           }); 
/* 179 */     this.stream.add(Byte.valueOf(value));
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeByte(byte)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeBytes(byte[] value) throws JMSException {
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeBytes(byte [ ])", new Object[] { value });
/*     */     }
/*     */     
/* 195 */     this.stream.add(value);
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeBytes(byte [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeBytes(byte[] value, int offset, int length) throws JMSException {
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeBytes(byte [ ],int,int)", new Object[] { value, 
/* 209 */             Integer.valueOf(offset), 
/* 210 */             Integer.valueOf(length) });
/*     */     }
/* 212 */     byte[] bytes = new byte[length];
/* 213 */     System.arraycopy(value, offset, bytes, 0, length);
/* 214 */     this.stream.add(bytes);
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeBytes(byte [ ],int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeChar(char value) throws JMSException {
/* 226 */     if (Trace.isOn)
/* 227 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeChar(char)", new Object[] {
/* 228 */             Character.valueOf(value)
/*     */           }); 
/* 230 */     this.stream.add(Character.valueOf(value));
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeChar(char)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeDouble(double value) throws JMSException {
/* 242 */     if (Trace.isOn)
/* 243 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeDouble(double)", new Object[] {
/* 244 */             Double.valueOf(value)
/*     */           }); 
/* 246 */     this.stream.add(Double.valueOf(value));
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeDouble(double)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeFloat(float value) throws JMSException {
/* 258 */     if (Trace.isOn)
/* 259 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeFloat(float)", new Object[] {
/* 260 */             Float.valueOf(value)
/*     */           }); 
/* 262 */     this.stream.add(Float.valueOf(value));
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeFloat(float)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeInt(int value) throws JMSException {
/* 274 */     if (Trace.isOn)
/* 275 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeInt(int)", new Object[] {
/* 276 */             Integer.valueOf(value)
/*     */           }); 
/* 278 */     this.stream.add(Integer.valueOf(value));
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeInt(int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeLong(long value) throws JMSException {
/* 289 */     if (Trace.isOn)
/* 290 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeLong(long)", new Object[] {
/* 291 */             Long.valueOf(value)
/*     */           }); 
/* 293 */     this.stream.add(Long.valueOf(value));
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeLong(long)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeObject(Object value) throws JMSException {
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeObject(Object)", new Object[] { value });
/*     */     }
/*     */     
/* 309 */     this.stream.add(value);
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeObject(Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeShort(short value) throws JMSException {
/* 321 */     if (Trace.isOn)
/* 322 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeShort(short)", new Object[] {
/* 323 */             Short.valueOf(value)
/*     */           }); 
/* 325 */     this.stream.add(Short.valueOf(value));
/* 326 */     if (Trace.isOn) {
/* 327 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeShort(short)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeString(String value) throws JMSException {
/* 337 */     if (Trace.isOn) {
/* 338 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeString(String)", new Object[] { value });
/*     */     }
/*     */     
/* 341 */     this.stream.add(value);
/* 342 */     if (Trace.isOn) {
/* 343 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "writeString(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Object> getStreamData() throws JMSException {
/* 353 */     if (Trace.isOn) {
/* 354 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "getStreamData()", "getter", this.stream);
/*     */     }
/*     */     
/* 357 */     return this.stream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMessage convertIntoWMQMessage(ProviderSession session) throws JMSException {
/* 365 */     if (Trace.isOn) {
/* 366 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "convertIntoWMQMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */     
/* 369 */     WMQStreamMessage wMQStreamMessage = new WMQStreamMessage();
/* 370 */     setJMSPropsOnProviderMessage((ProviderMessage)wMQStreamMessage);
/* 371 */     setPCPropertiesOnProviderMessage((ProviderMessage)wMQStreamMessage);
/*     */ 
/*     */     
/* 374 */     for (int i = 0; i < this.stream.size(); i++) {
/* 375 */       wMQStreamMessage.writeObject(this.stream.get(i));
/*     */     }
/* 377 */     if (Trace.isOn) {
/* 378 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "convertIntoWMQMessage(ProviderSession)", wMQStreamMessage);
/*     */     }
/*     */     
/* 381 */     return (ProviderMessage)wMQStreamMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderMessage convertIntoMQMessage(ProviderSession session) throws JMSException {
/* 389 */     if (Trace.isOn) {
/* 390 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "convertIntoMQMessage(ProviderSession)", new Object[] { session });
/*     */     }
/*     */     
/* 393 */     ProviderStreamMessage providerStreamMessage = ((MQSession)session).createStreamMessage();
/* 394 */     setJMSPropsOnProviderMessage((ProviderMessage)providerStreamMessage);
/* 395 */     setPCPropertiesOnProviderMessage((ProviderMessage)providerStreamMessage);
/*     */ 
/*     */     
/* 398 */     for (int i = 0; i < this.stream.size(); i++) {
/* 399 */       ((JMSStreamMessage)providerStreamMessage).writeObject(this.stream.get(i));
/*     */     }
/* 401 */     if (Trace.isOn) {
/* 402 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientStreamMessage", "convertIntoMQMessage(ProviderSession)", providerStreamMessage);
/*     */     }
/*     */     
/* 405 */     return (ProviderMessage)providerStreamMessage;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\messages\TransientStreamMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */