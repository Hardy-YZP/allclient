/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.commonservices.util.SerializationValidator;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.provider.ProviderObjectMessage;
/*     */ import com.ibm.msg.client.provider.ProviderSelfSerializingObjectMessage;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
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
/*     */ public class JmsObjectMessageImpl
/*     */   extends JmsMessageImpl
/*     */   implements ObjectMessage
/*     */ {
/*     */   private static final long serialVersionUID = 718892436974350599L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsObjectMessageImpl.java";
/*     */   ProviderObjectMessage providerObjectMessage;
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsObjectMessageImpl.java");
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
/*  77 */   private transient String cachedObjectToString = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsObjectMessageImpl(JmsSessionImpl session) throws JMSException {
/*  85 */     super(session);
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "<init>(JmsSessionImpl)", new Object[] { session });
/*     */     }
/*     */ 
/*     */     
/*  91 */     this.providerObjectMessage = (ProviderObjectMessage)getProviderMessage();
/*     */     
/*  93 */     this.messageType = "jms_object";
/*     */     
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "<init>(JmsSessionImpl)");
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
/*     */   public JmsObjectMessageImpl(String connectionType, Message message) throws JMSException {
/* 112 */     super(connectionType, message);
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "<init>(String,Message)", new Object[] { connectionType, message });
/*     */     }
/*     */ 
/*     */     
/* 118 */     this.providerObjectMessage = (ProviderObjectMessage)getProviderMessage();
/* 119 */     this.messageType = "jms_object";
/*     */     
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "<init>(String,Message)");
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
/*     */   public JmsObjectMessageImpl(JmsSessionImpl session, Serializable serializedObj) throws JMSException {
/* 137 */     this(session);
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "<init>(JmsSessionImpl,Serializable)", new Object[] { session, serializedObj });
/*     */     }
/*     */ 
/*     */     
/* 143 */     this.messageType = "jms_object";
/* 144 */     setObject(serializedObj);
/*     */     
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "<init>(JmsSessionImpl,Serializable)");
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
/*     */   public JmsObjectMessageImpl(JmsSessionImpl session, Message message, Serializable serializedObj) throws JMSException {
/* 163 */     super(session, message);
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "<init>(JmsSessionImpl,Message,Serializable)", new Object[] { session, message, serializedObj });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 170 */     this.messageType = "jms_object";
/* 171 */     setObject(serializedObj);
/*     */     
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "<init>(JmsSessionImpl,Message,Serializable)");
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
/*     */   public JmsObjectMessageImpl(ProviderObjectMessage newMsg, JmsSessionImpl newSess, String connectionTypeName) throws JMSException {
/* 191 */     super((ProviderMessage)newMsg, newSess, connectionTypeName);
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "<init>(ProviderObjectMessage,JmsSessionImpl,String)", new Object[] { newMsg, newSess, connectionTypeName });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 199 */     this.providerObjectMessage = newMsg;
/* 200 */     this.messageType = "jms_object";
/*     */ 
/*     */ 
/*     */     
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "<init>(ProviderObjectMessage,JmsSessionImpl,String)");
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
/*     */   JmsObjectMessageImpl(JmsSessionImpl session, ObjectMessage objectMessage) throws JMSException {
/* 217 */     super(session, (Message)objectMessage);
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "<init>(JmsSessionImpl,ObjectMessage)", new Object[] { session, objectMessage });
/*     */     }
/*     */ 
/*     */     
/* 223 */     this.providerObjectMessage = (ProviderObjectMessage)getProviderMessage();
/*     */     
/* 225 */     this.messageType = "jms_object";
/*     */ 
/*     */     
/* 228 */     setObject(objectMessage.getObject());
/*     */     
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "<init>(JmsSessionImpl,ObjectMessage)");
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
/*     */   public String toString() {
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "toString()");
/*     */     }
/* 246 */     String val = super.toString();
/*     */     
/* 248 */     if (this.cachedObjectToString == null) {
/*     */       
/* 250 */       String objString = null;
/* 251 */       Object obj = null;
/*     */       
/*     */       try {
/* 254 */         obj = getObjectInternal();
/*     */       }
/* 256 */       catch (Exception e) {
/* 257 */         if (Trace.isOn) {
/* 258 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "toString()", e);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 263 */         objString = e.getMessage();
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 268 */       if (objString == null)
/*     */       {
/* 270 */         if (obj == null) {
/* 271 */           objString = "<null>";
/*     */         } else {
/*     */           
/* 274 */           objString = obj.getClass().toString();
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 279 */       this.cachedObjectToString = objString;
/*     */     } 
/*     */ 
/*     */     
/* 283 */     String traceRet1 = val + "\n" + this.cachedObjectToString;
/* 284 */     if (Trace.isOn) {
/* 285 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "toString()", traceRet1);
/*     */     }
/*     */     
/* 288 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObject(Serializable obj) throws JMSException {
/* 297 */     if (Trace.isOn) {
/* 298 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "setObject(Serializable)", "setter", obj);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 304 */       checkBodyWriteable("setObject");
/*     */       
/* 306 */       if (obj != null) {
/* 307 */         Class<? extends Serializable> objClass = (Class)obj.getClass();
/* 308 */         String objCanonicalName = getName(objClass);
/* 309 */         SerializationValidator.getInstance().validateClassName(objCanonicalName, SerializationValidator.Mode.SERIALIZE);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 317 */       if (this.providerObjectMessage instanceof ProviderSelfSerializingObjectMessage) {
/* 318 */         ((ProviderSelfSerializingObjectMessage)this.providerObjectMessage).setSelfSerializedObject(obj);
/*     */       } else {
/*     */         
/* 321 */         ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 322 */         ObjectOutputStream oos = new ObjectOutputStream(baos);
/*     */ 
/*     */         
/* 325 */         oos.writeObject(obj);
/*     */         
/* 327 */         this.providerObjectMessage.setSerializedObject(baos.toByteArray());
/*     */       } 
/*     */       
/* 330 */       this.cachedObjectToString = null;
/*     */     
/*     */     }
/* 333 */     catch (IOException e) {
/* 334 */       if (Trace.isOn) {
/* 335 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "setObject(Serializable)", e);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 340 */       HashMap<String, Object> inserts = new HashMap<>();
/* 341 */       inserts.put("XMSC_INSERT_EXCEPTION", e);
/* 342 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0052", inserts);
/* 343 */       je.setLinkedException(e);
/* 344 */       if (Trace.isOn) {
/* 345 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "setObject(Serializable)", (Throwable)je);
/*     */       }
/*     */       
/* 348 */       throw je;
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
/* 360 */     StringBuilder name = new StringBuilder(objClass.getCanonicalName());
/* 361 */     if (objClass.isMemberClass()) {
/*     */       
/* 363 */       int lastDotPos = name.lastIndexOf(".");
/* 364 */       name.replace(lastDotPos, lastDotPos + 1, "$");
/*     */     } 
/* 366 */     return name.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Serializable getObject() throws JMSException {
/* 375 */     Serializable obj = null;
/*     */     
/*     */     try {
/* 378 */       obj = getObjectInternal();
/*     */     
/*     */     }
/* 381 */     catch (ClassNotFoundException e) {
/* 382 */       if (Trace.isOn) {
/* 383 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "getObject()", e, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 388 */       HashMap<String, Object> inserts = new HashMap<>();
/* 389 */       inserts.put("XMSC_INSERT_EXCEPTION", e);
/* 390 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0053", inserts);
/* 391 */       je.setLinkedException(e);
/* 392 */       if (Trace.isOn) {
/* 393 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "getObject()", (Throwable)je, 1);
/*     */       }
/*     */       
/* 396 */       throw je;
/*     */     }
/* 398 */     catch (Exception e) {
/* 399 */       if (Trace.isOn) {
/* 400 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "getObject()", e, 2);
/*     */       }
/*     */ 
/*     */       
/* 404 */       HashMap<String, Object> inserts = new HashMap<>();
/* 405 */       inserts.put("XMSC_INSERT_EXCEPTION", e);
/* 406 */       JMSException je2 = (JMSException)JmsErrorUtils.createException("JMSCC0053", inserts);
/* 407 */       je2.setLinkedException(e);
/* 408 */       if (Trace.isOn) {
/* 409 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "getObject()", (Throwable)je2, 2);
/*     */       }
/*     */       
/* 412 */       throw je2;
/*     */     } 
/*     */     
/* 415 */     if (Trace.isOn) {
/* 416 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "getObject()", "getter", obj);
/*     */     }
/*     */     
/* 419 */     return obj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Serializable getObjectInternal() throws Exception {
/* 430 */     Serializable obj = null;
/*     */     
/* 432 */     if (this.providerObjectMessage instanceof ProviderSelfSerializingObjectMessage) {
/* 433 */       obj = ((ProviderSelfSerializingObjectMessage)this.providerObjectMessage).getSelfSerializedObject();
/*     */     } else {
/*     */       
/* 436 */       byte[] byteForm = this.providerObjectMessage.getSerializedObject();
/*     */       
/* 438 */       if (byteForm != null) {
/*     */         
/* 440 */         ByteArrayInputStream bais = new ByteArrayInputStream(byteForm);
/*     */ 
/*     */         
/* 443 */         JmsObjectInputStream ois = new JmsObjectInputStream(bais);
/*     */         
/* 445 */         obj = (Serializable)ois.readObject();
/*     */         
/* 447 */         bais.close();
/* 448 */         ois.close();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 453 */     if (Trace.isOn) {
/* 454 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "getObjectInternal()", "getter", obj);
/*     */     }
/*     */     
/* 457 */     return obj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ProviderMessage createProviderMessage(JmsSessionImpl session) throws Exception {
/* 464 */     if (Trace.isOn) {
/* 465 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "createProviderMessage(JmsSessionImpl)", new Object[] { session });
/*     */     }
/*     */ 
/*     */     
/* 469 */     if (session != null) {
/* 470 */       this.providerObjectMessage = getProviderMessageFactory().createObjectMessage(session.getProviderSession());
/*     */     } else {
/*     */       
/* 473 */       this.providerObjectMessage = getProviderMessageFactory().createObjectMessage(null);
/*     */     } 
/*     */     
/* 476 */     if (Trace.isOn) {
/* 477 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "createProviderMessage(JmsSessionImpl)", this.providerObjectMessage);
/*     */     }
/*     */     
/* 480 */     return (ProviderMessage)this.providerObjectMessage;
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
/*     */   public void setObjectAsBytes(byte[] bytes) throws JMSException {
/* 492 */     if (Trace.isOn) {
/* 493 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsObjectMessageImpl", "setObjectAsBytes(byte [ ])", "setter", bytes);
/*     */     }
/*     */ 
/*     */     
/* 497 */     this.providerObjectMessage.setSerializedObject(bytes);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsObjectMessageImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */