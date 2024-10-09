/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsMessageReference;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQMessageReference
/*     */   implements MessageReference, JmsMessageReference
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQMessageReference.java";
/*     */   private JmsMessageReference commonMessageReference;
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.mq.jms.MQMessageReference", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQMessageReference.java");
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
/*     */   MQMessageReference(JmsMessageReference jmsMessageReference) {
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageReference", "<init>(JmsMessageReference)", new Object[] { jmsMessageReference });
/*     */     }
/*     */     
/*  66 */     this.commonMessageReference = jmsMessageReference;
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageReference", "<init>(JmsMessageReference)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/*     */     MQMessageReference clone;
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageReference", "clone()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  87 */       clone = (MQMessageReference)super.clone();
/*  88 */       clone
/*  89 */         .commonMessageReference = (JmsMessageReference)this.commonMessageReference.clone();
/*     */     }
/*  91 */     catch (CloneNotSupportedException e) {
/*  92 */       if (Trace.isOn) {
/*  93 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQMessageReference", "clone()", e);
/*     */       }
/*     */ 
/*     */       
/*  97 */       RuntimeException traceRet1 = new RuntimeException(e);
/*  98 */       if (Trace.isOn) {
/*  99 */         Trace.throwing(this, "com.ibm.mq.jms.MQMessageReference", "clone()", traceRet1);
/*     */       }
/* 101 */       throw traceRet1;
/*     */     } 
/*     */     
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageReference", "clone()", clone);
/*     */     }
/* 107 */     return clone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object other) {
/* 118 */     if (null == other || !(other instanceof MQMessageReference)) {
/* 119 */       return false;
/*     */     }
/* 121 */     MQMessageReference mr = (MQMessageReference)other;
/* 122 */     if (null == mr.commonMessageReference) {
/* 123 */       return false;
/*     */     }
/*     */     
/* 126 */     boolean equal = this.commonMessageReference.equals(mr.commonMessageReference);
/* 127 */     return equal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 137 */     int hc = this.commonMessageReference.hashCode();
/* 138 */     return hc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] flatten() throws JMSException {
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageReference", "flatten()");
/*     */     }
/* 150 */     byte[] result = this.commonMessageReference.flatten();
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageReference", "flatten()", result);
/*     */     }
/* 154 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDataQuantity() throws JMSException {
/* 163 */     int result = this.commonMessageReference.getDataQuantity();
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.data(this, "com.ibm.mq.jms.MQMessageReference", "getDataQuantity()", "getter", 
/* 166 */           Integer.valueOf(result));
/*     */     }
/* 168 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Message getMessage() throws JMSException {
/* 177 */     Message result = this.commonMessageReference.getMessage();
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.data(this, "com.ibm.mq.jms.MQMessageReference", "getMessage()", "getter", result);
/*     */     }
/* 181 */     return result;
/*     */   }
/*     */   
/*     */   void setDelegate(JmsMessageReference commonMessageReference) {
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.data(this, "com.ibm.mq.jms.MQMessageReference", "setDelegate(JmsMessageReference)", "setter", commonMessageReference);
/*     */     }
/*     */     
/* 189 */     this.commonMessageReference = commonMessageReference;
/*     */   }
/*     */   
/*     */   JmsMessageReference getDelegate() {
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.data(this, "com.ibm.mq.jms.MQMessageReference", "getDelegate()", "getter", this.commonMessageReference);
/*     */     }
/*     */     
/* 197 */     return this.commonMessageReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 207 */     StringBuffer buff = new StringBuffer();
/*     */     try {
/* 209 */       buff.append("MessageReference(");
/* 210 */       buff.append(System.identityHashCode(this));
/* 211 */       buff.append(") ");
/* 212 */       if (null == this.commonMessageReference) {
/* 213 */         buff.append(" message(<null>)");
/*     */       } else {
/* 215 */         buff.append(this.commonMessageReference.toString());
/*     */       }
/*     */     
/* 218 */     } catch (Throwable t) {
/* 219 */       buff.append("<ERROR>");
/*     */     } 
/* 221 */     return buff.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isManagedQueue() {
/* 229 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQMessageReference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */