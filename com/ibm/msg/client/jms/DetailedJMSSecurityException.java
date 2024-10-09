/*     */ package com.ibm.msg.client.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.jms.JMSRuntimeException;
/*     */ import javax.jms.JMSSecurityException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DetailedJMSSecurityException
/*     */   extends JMSSecurityException
/*     */   implements JmsExceptionDetail, JmsConvertableException
/*     */ {
/*     */   private static final long serialVersionUID = 7443851881937738846L;
/*     */   private String explanation;
/*     */   private String useraction;
/*     */   private Map<String, ? extends Object> inserts;
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.msg.client.jms.DetailedJMSSecurityException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/DetailedJMSSecurityException.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DetailedJMSSecurityException(String message, String id, String explanation, String useraction, Map<String, ? extends Object> inserts) {
/*  83 */     super(message, id);
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedJMSSecurityException", "<init>(String,String,String,String,Map<String , ? extends Object>)", new Object[] { message, id, explanation, useraction, inserts });
/*     */     }
/*     */ 
/*     */     
/*  89 */     this.explanation = explanation;
/*  90 */     this.useraction = useraction;
/*  91 */     this.inserts = inserts;
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSSecurityException", "<init>(String,String,String,String,Map<String , ? extends Object>)");
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
/*     */   public String getExplanation() {
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedJMSSecurityException", "getExplanation()", "getter", this.explanation);
/*     */     }
/*     */     
/* 112 */     return this.explanation;
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
/*     */   public String getUserAction() {
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedJMSSecurityException", "getUserAction()", "getter", this.useraction);
/*     */     }
/*     */     
/* 128 */     return this.useraction;
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
/*     */   public String getValue(String insertKey) {
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedJMSSecurityException", "getValue(String)", new Object[] { insertKey });
/*     */     }
/*     */     
/* 148 */     if (null == this.inserts) {
/* 149 */       if (Trace.isOn) {
/* 150 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSSecurityException", "getValue(String)", null, 1);
/*     */       }
/*     */       
/* 153 */       return null;
/*     */     } 
/* 155 */     String traceRet1 = (String)this.inserts.get(insertKey);
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSSecurityException", "getValue(String)", traceRet1, 2);
/*     */     }
/*     */     
/* 160 */     return traceRet1;
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
/*     */   public Iterator<String> getKeys() {
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedJMSSecurityException", "getKeys()");
/*     */     }
/* 177 */     if (null == this.inserts) {
/* 178 */       if (Trace.isOn) {
/* 179 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSSecurityException", "getKeys()", null, 1);
/*     */       }
/*     */       
/* 182 */       return null;
/*     */     } 
/* 184 */     Iterator<String> traceRet1 = this.inserts.keySet().iterator();
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSSecurityException", "getKeys()", traceRet1, 2);
/*     */     }
/*     */     
/* 189 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLinkedException(Exception ex) {
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedJMSSecurityException", "setLinkedException(Exception)", "setter", ex);
/*     */     }
/*     */     
/* 204 */     super.setLinkedException(ex);
/*     */     try {
/* 206 */       initCause(ex);
/*     */     }
/* 208 */     catch (Exception e) {
/* 209 */       if (Trace.isOn) {
/* 210 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.DetailedJMSSecurityException", "setLinkedException(Exception)", e);
/*     */       }
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
/*     */   public String toString() {
/* 224 */     if (Trace.isOn) {
/* 225 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedJMSSecurityException", "toString()");
/*     */     }
/* 227 */     String result = super.toString();
/*     */     
/* 229 */     if (this.explanation != null) {
/* 230 */       result = result + PropertyStore.line_separator + this.explanation;
/*     */     }
/*     */     
/* 233 */     if (this.useraction != null) {
/* 234 */       result = result + PropertyStore.line_separator + this.useraction;
/*     */     }
/*     */     
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSSecurityException", "toString()", result);
/*     */     }
/*     */     
/* 241 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object writeReplace() throws ObjectStreamException {
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedJMSSecurityException", "writeReplace()");
/*     */     }
/* 255 */     Object traceRet1 = new JmsExceptionFactory((Throwable)this);
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSSecurityException", "writeReplace()", traceRet1);
/*     */     }
/*     */     
/* 260 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JMSRuntimeException getUnchecked() {
/* 268 */     Throwable cause = getLinkedException();
/*     */     
/* 270 */     DetailedJMSSecurityRuntimeException detailedJMSSecurityRuntimeException = new DetailedJMSSecurityRuntimeException(getMessage(), getErrorCode(), this.explanation, this.useraction, this.inserts, (cause != null) ? cause : (Throwable)this);
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedJMSSecurityException", "getUnchecked()", "getter", detailedJMSSecurityRuntimeException);
/*     */     }
/*     */     
/* 275 */     return (JMSRuntimeException)detailedJMSSecurityRuntimeException;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\DetailedJMSSecurityException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */