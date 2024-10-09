/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.commonservices.util.SerializationValidator;
/*     */ import com.ibm.msg.client.provider.ProviderObjectMessage;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQObjectInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   implements ProviderObjectMessage
/*     */ {
/*     */   static final long serialVersionUID = -9160649637541619341L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSObjectMessage.java";
/*     */   
/*     */   static {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/JMSObjectMessage.java");
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
/* 108 */   byte[] messageBytes = null;
/*     */ 
/*     */ 
/*     */   
/*     */   int dataStart;
/*     */ 
/*     */ 
/*     */   
/*     */   boolean readOnly = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JMSObjectMessage(JMSStringResources jmsStrings) throws JMSException {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "<init>(JMSStringResources)", new Object[] { jmsStrings });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 128 */     this.messageClass = "jms_object";
/*     */ 
/*     */     
/* 131 */     this.jmsStrings = jmsStrings;
/*     */     
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "<init>(JMSStringResources)");
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
/*     */   public JMSObjectMessage(JMSStringResources jmsStrings, Serializable object) throws JMSException {
/* 158 */     this(jmsStrings);
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "<init>(JMSStringResources,Serializable)", new Object[] { jmsStrings, object });
/*     */     }
/*     */ 
/*     */     
/* 164 */     setObject(object);
/*     */     
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "<init>(JMSStringResources,Serializable)");
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
/*     */   public byte[] _exportBody(int encoding, JmqiCodepage codepage) throws JMSException {
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "_exportBody(int,JmqiCodepage)", new Object[] {
/* 188 */             Integer.valueOf(encoding), codepage
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 195 */     if (this.messageBytes != null && 
/* 196 */       this.dataStart != 0) {
/*     */       
/* 198 */       int length = this.messageBytes.length - this.dataStart;
/* 199 */       byte[] t = new byte[length];
/* 200 */       System.arraycopy(this.messageBytes, this.dataStart, t, 0, length);
/*     */       
/* 202 */       this.messageBytes = t;
/* 203 */       this.dataStart = 0;
/*     */     } 
/*     */ 
/*     */     
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "_exportBody(int,JmqiCodepage)", this.messageBytes);
/*     */     }
/*     */     
/* 211 */     return this.messageBytes;
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
/*     */   public void _importBody(byte[] wireformat, int dataStart, int encoding, JmqiCodepage codepage) throws JMSException {
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "_importBody(byte [ ],int,int,JmqiCodepage)", new Object[] { wireformat, 
/*     */             
/* 228 */             Integer.valueOf(dataStart), Integer.valueOf(encoding), codepage });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 233 */     this.messageBytes = wireformat;
/* 234 */     this.dataStart = dataStart;
/*     */     
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "_importBody(byte [ ],int,int,JmqiCodepage)");
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
/*     */   public void clearBody() throws JMSException {
/* 251 */     if (Trace.isOn) {
/* 252 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "clearBody()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 257 */     this.readOnly = false;
/*     */ 
/*     */     
/* 260 */     this.messageBytes = null;
/*     */     
/* 262 */     if (Trace.isOn) {
/* 263 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "clearBody()");
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
/*     */   public Serializable getObject() throws JMSException {
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "getObject()");
/*     */     }
/*     */     
/* 282 */     Serializable object = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 287 */       if (this.messageBytes != null) {
/* 288 */         ByteArrayInputStream inputStream = new ByteArrayInputStream(this.messageBytes, this.dataStart, this.messageBytes.length - this.dataStart);
/*     */ 
/*     */ 
/*     */         
/* 292 */         ClassLoader classLoader = AccessController.<ClassLoader>doPrivileged(new PrivilegedAction<ClassLoader>()
/*     */             {
/*     */               public Object run()
/*     */               {
/* 296 */                 if (Trace.isOn) {
/* 297 */                   Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "run()");
/*     */                 }
/*     */ 
/*     */ 
/*     */                 
/* 302 */                 ClassLoader cl = Thread.currentThread().getContextClassLoader();
/* 303 */                 if (Trace.isOn) {
/* 304 */                   Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.null", "run()", cl);
/*     */                 }
/* 306 */                 return cl;
/*     */               }
/*     */             });
/* 309 */         if (classLoader != null) {
/*     */           
/* 311 */           MQObjectInputStream mQObjectInputStream = new MQObjectInputStream(inputStream, classLoader);
/* 312 */           object = (Serializable)mQObjectInputStream.readObject();
/*     */         } else {
/*     */           
/* 315 */           JMSException jmsEx = newMessageFormatException(1024);
/* 316 */           if (Trace.isOn) {
/* 317 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "getObject()", (Throwable)jmsEx, 1);
/*     */           }
/*     */           
/* 320 */           throw jmsEx;
/*     */         }
/*     */       
/*     */       } 
/* 324 */     } catch (IOException ex) {
/* 325 */       if (Trace.isOn) {
/* 326 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "getObject()", ex, 1);
/*     */       }
/*     */       
/* 329 */       JMSException jmsEx = newMessageFormatException(1024);
/* 330 */       jmsEx.setLinkedException(ex);
/* 331 */       if (Trace.isOn) {
/* 332 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "getObject()", (Throwable)jmsEx, 2);
/*     */       }
/*     */       
/* 335 */       throw jmsEx;
/*     */     }
/* 337 */     catch (ClassNotFoundException ex) {
/* 338 */       if (Trace.isOn) {
/* 339 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "getObject()", ex, 2);
/*     */       }
/*     */       
/* 342 */       JMSException jmsEx = newMessageFormatException(1024);
/* 343 */       jmsEx.setLinkedException(ex);
/* 344 */       if (Trace.isOn) {
/* 345 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "getObject()", (Throwable)jmsEx, 3);
/*     */       }
/*     */       
/* 348 */       throw jmsEx;
/*     */     } 
/*     */     
/* 351 */     if (Trace.isOn) {
/* 352 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "getObject()", object);
/*     */     }
/*     */     
/* 355 */     return object;
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
/*     */   public void setObject(Serializable objectP) throws JMSException {
/* 373 */     if (Trace.isOn) {
/* 374 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "setObject(Serializable)", "setter", objectP);
/*     */     }
/*     */ 
/*     */     
/* 378 */     Serializable object = objectP;
/*     */ 
/*     */ 
/*     */     
/* 382 */     if (this.readOnly == true) {
/* 383 */       JMSException traceRet1 = newMessageNotWriteableException();
/* 384 */       if (Trace.isOn) {
/* 385 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "setObject(Serializable)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 388 */       throw traceRet1;
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 393 */       if (objectP != null) {
/* 394 */         Class<? extends Serializable> objClass = (Class)objectP.getClass();
/* 395 */         String objCanonicalName = getName(objClass);
/* 396 */         SerializationValidator.getInstance().validateClassName(objCanonicalName, SerializationValidator.Mode.SERIALIZE);
/*     */       } 
/*     */       
/* 399 */       ByteArrayOutputStream serialisedObject = new ByteArrayOutputStream();
/* 400 */       ObjectOutputStream objOut = new ObjectOutputStream(serialisedObject);
/* 401 */       objOut.writeObject(object);
/* 402 */       this.messageBytes = serialisedObject.toByteArray();
/* 403 */       this.dataStart = 0;
/*     */ 
/*     */       
/* 406 */       object = null;
/*     */     }
/* 408 */     catch (IOException ex) {
/* 409 */       if (Trace.isOn) {
/* 410 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "setObject(Serializable)", ex);
/*     */       }
/*     */       
/* 413 */       JMSException jmsEx = newMessageFormatException(1023);
/* 414 */       jmsEx.setLinkedException(ex);
/* 415 */       if (Trace.isOn) {
/* 416 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "setObject(Serializable)", (Throwable)jmsEx, 2);
/*     */       }
/*     */       
/* 419 */       throw jmsEx;
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
/*     */   private String getName(Class<? extends Serializable> objClass) {
/* 431 */     StringBuilder name = new StringBuilder(objClass.getCanonicalName());
/* 432 */     if (objClass.isMemberClass()) {
/*     */       
/* 434 */       int lastDotPos = name.lastIndexOf(".");
/* 435 */       name.replace(lastDotPos, lastDotPos + 1, "$");
/*     */     } 
/* 437 */     return name.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 447 */     if (Trace.isOn) {
/* 448 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "toString()");
/*     */     }
/*     */     
/* 451 */     StringBuffer retval = new StringBuffer();
/*     */ 
/*     */     
/* 454 */     retval.append(super.toString());
/* 455 */     retval.append("\n");
/*     */ 
/*     */     
/*     */     try {
/* 459 */       Serializable object = getObject();
/*     */       
/* 461 */       if (object == null) {
/* 462 */         retval.append("<null>");
/*     */       }
/*     */       else {
/*     */         
/* 466 */         retval.append(object.getClass());
/*     */       }
/*     */     
/* 469 */     } catch (JMSException ex) {
/* 470 */       if (Trace.isOn) {
/* 471 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "toString()", (Throwable)ex);
/*     */       }
/*     */       
/* 474 */       retval.append(this.jmsStrings.getMessage(1025, ex));
/* 475 */       retval.append(">");
/*     */     } 
/*     */     
/* 478 */     String traceRet1 = retval.toString();
/* 479 */     if (Trace.isOn) {
/* 480 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "toString()", traceRet1);
/*     */     }
/*     */     
/* 483 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void _setBodyReadOnly() {
/* 489 */     if (Trace.isOn) {
/* 490 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "_setBodyReadOnly()");
/*     */     }
/*     */     
/* 493 */     this.readOnly = true;
/* 494 */     if (Trace.isOn) {
/* 495 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "_setBodyReadOnly()");
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
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 510 */     if (Trace.isOn) {
/* 511 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */     
/* 514 */     in.defaultReadObject();
/*     */ 
/*     */     
/* 517 */     if (this.messageClass.equals("jms_object")) {
/* 518 */       this.messageClass = "jms_object";
/*     */     }
/* 520 */     if (Trace.isOn) {
/* 521 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "readObject(java.io.ObjectInputStream)");
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
/*     */   public void setSerializedObject(byte[] objectBytes) throws JMSException {
/* 533 */     if (Trace.isOn) {
/* 534 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "setSerializedObject(byte [ ])", "setter", objectBytes);
/*     */     }
/*     */     
/* 537 */     _importBody(objectBytes, 0, 0, (JmqiCodepage)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getSerializedObject() throws JMSException {
/* 546 */     byte[] traceRet1 = _exportBody(0, (JmqiCodepage)null);
/* 547 */     if (Trace.isOn) {
/* 548 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "getSerializedObject()", "getter", traceRet1);
/*     */     }
/*     */     
/* 551 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasBody() {
/* 560 */     if (Trace.isOn) {
/* 561 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "hasBody()");
/*     */     }
/*     */     
/* 564 */     if (Trace.isOn) {
/* 565 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "hasBody()", 
/* 566 */           Boolean.valueOf(false));
/*     */     }
/* 568 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getJMSDeliveryTime() throws JMSException {
/* 577 */     if (Trace.isOn) {
/* 578 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "getJMSDeliveryTime()", "getter", 
/* 579 */           Long.valueOf(0L));
/*     */     }
/* 581 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSDeliveryTime(long deliveryTime) throws JMSException {
/* 589 */     if (Trace.isOn)
/* 590 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.JMSObjectMessage", "setJMSDeliveryTime(long)", "setter", 
/* 591 */           Long.valueOf(deliveryTime)); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\JMSObjectMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */