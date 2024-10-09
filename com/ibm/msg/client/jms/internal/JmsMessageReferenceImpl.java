/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsMessageReference;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.provider.ProviderMessageReference;
/*     */ import java.util.HashMap;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Message;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsMessageReferenceImpl
/*     */   implements JmsMessageReference
/*     */ {
/*     */   private ProviderMessageReference providerMessageReference;
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.msg.client.jms.internal.JmsMessageReferenceImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsMessageReferenceImpl.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   private Message message = null;
/*     */ 
/*     */   
/*     */   private String connectionTypeName;
/*     */ 
/*     */   
/*     */   JmsMessageReferenceImpl(String connectionTypeName, ProviderMessageReference providerMessageReference) {
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceImpl", "<init>(String,ProviderMessageReference)", new Object[] { connectionTypeName, providerMessageReference });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  68 */     this.connectionTypeName = connectionTypeName;
/*  69 */     this.providerMessageReference = providerMessageReference;
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceImpl", "<init>(String,ProviderMessageReference)");
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
/*     */   public int getDataQuantity() throws JMSException {
/*  85 */     int traceRet1 = this.providerMessageReference.getDataQuantity();
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceImpl", "getDataQuantity()", "getter", 
/*  88 */           Integer.valueOf(traceRet1));
/*     */     }
/*  90 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Message getMessage() throws JMSException {
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceImpl", "getMessage()");
/*     */     }
/*     */     
/* 105 */     if (null == this.message) {
/* 106 */       ProviderMessage pm = this.providerMessageReference.getMessage();
/* 107 */       if (null == pm) {
/* 108 */         if (Trace.isOn) {
/* 109 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceImpl", "getMessage()", null, 1);
/*     */         }
/*     */         
/* 112 */         return null;
/*     */       } 
/*     */       
/* 115 */       this.message = JmsMessageImpl.inboundJmsInstance(pm, null, this.connectionTypeName);
/*     */     } 
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceImpl", "getMessage()", this.message, 2);
/*     */     }
/*     */     
/* 121 */     return this.message;
/*     */   }
/*     */   
/*     */   Message getMessage(JmsSessionImpl session) throws JMSException {
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceImpl", "getMessage(JmsSessionImpl)", new Object[] { session });
/*     */     }
/*     */ 
/*     */     
/* 130 */     if (this.message == null) {
/* 131 */       ProviderMessage providerMessage = this.providerMessageReference.getMessage();
/* 132 */       if (providerMessage != null) {
/* 133 */         this.message = JmsMessageImpl.inboundJmsInstance(providerMessage, session, this.connectionTypeName);
/*     */       }
/*     */     } 
/*     */     
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceImpl", "getMessage(JmsSessionImpl)", this.message);
/*     */     }
/*     */     
/* 141 */     return this.message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] flatten() throws JMSException {
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceImpl", "flatten()");
/*     */     }
/*     */     
/* 155 */     byte[] traceRet1 = this.providerMessageReference.flatten();
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceImpl", "flatten()", traceRet1);
/*     */     }
/*     */     
/* 160 */     return traceRet1;
/*     */   }
/*     */   
/*     */   ProviderMessageReference getDelegate() {
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceImpl", "getDelegate()", "getter", this.providerMessageReference);
/*     */     }
/*     */     
/* 168 */     return this.providerMessageReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceImpl", "clone()");
/*     */     }
/*     */     
/* 180 */     JmsMessageReferenceImpl clone = null;
/*     */     try {
/* 182 */       clone = (JmsMessageReferenceImpl)super.clone();
/* 183 */       ProviderMessageReference other = (ProviderMessageReference)this.providerMessageReference.clone();
/*     */       
/* 185 */       clone.connectionTypeName = this.connectionTypeName;
/* 186 */       clone.providerMessageReference = other;
/*     */     }
/* 188 */     catch (CloneNotSupportedException e) {
/* 189 */       if (Trace.isOn) {
/* 190 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceImpl", "clone()", e);
/*     */       }
/*     */       
/* 193 */       HashMap<String, Object> data = new HashMap<>();
/* 194 */       data.put("exception", e);
/* 195 */       Trace.ffst(this, "clone()", "XJ00B001", data, null);
/*     */     } 
/*     */     
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceImpl", "clone()", clone);
/*     */     }
/*     */     
/* 202 */     return clone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 212 */     int hc = this.providerMessageReference.hashCode();
/* 213 */     return hc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object other) {
/* 223 */     if (null == other || !(other instanceof JmsMessageReferenceImpl)) {
/* 224 */       return false;
/*     */     }
/* 226 */     JmsMessageReferenceImpl jmri = (JmsMessageReferenceImpl)other;
/* 227 */     if (null == jmri.providerMessageReference) {
/* 228 */       return false;
/*     */     }
/* 230 */     boolean equal = this.providerMessageReference.equals(jmri.providerMessageReference);
/* 231 */     return equal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 241 */     StringBuffer buff = new StringBuffer();
/*     */     try {
/* 243 */       buff.append("MessageReference(");
/* 244 */       buff.append(Integer.toHexString(System.identityHashCode(this)));
/* 245 */       buff.append(") connectionTypeName(");
/* 246 */       if (null == this.connectionTypeName) {
/* 247 */         buff.append("<null>");
/*     */       } else {
/*     */         
/* 250 */         buff.append(this.connectionTypeName);
/*     */       } 
/*     */       
/* 253 */       buff.append(") message(");
/* 254 */       if (null == this.message) {
/* 255 */         buff.append("<null>");
/*     */       } else {
/*     */         
/* 258 */         buff.append(this.message.toString());
/*     */       } 
/*     */       
/* 261 */       buff.append(") providerMessageReference(");
/* 262 */       if (null == this.providerMessageReference) {
/* 263 */         buff.append("<null>");
/*     */       } else {
/*     */         
/* 266 */         buff.append(this.providerMessageReference.toString());
/*     */       } 
/* 268 */       buff.append(")");
/*     */     }
/* 270 */     catch (Throwable t) {
/* 271 */       buff.append("<ERROR>");
/*     */     } 
/* 273 */     return buff.toString();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsMessageReferenceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */