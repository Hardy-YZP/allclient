/*     */ package com.ibm.msg.client.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.JMSRuntimeException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DetailedJMSException
/*     */   extends JMSException
/*     */   implements JmsExceptionDetail, JmsConvertableException
/*     */ {
/*     */   private static final long serialVersionUID = 8994644226281699639L;
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.msg.client.jms.DetailedJMSException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/DetailedJMSException.java");
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
/*  58 */   private static String lineSeparator = PropertyStore.line_separator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String explanation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String useraction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map<String, ? extends Object> inserts;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DetailedJMSException(String message, String id, String explanation, String useraction, Map<String, ? extends Object> inserts) {
/*  83 */     super(message, id);
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedJMSException", "<init>(String,String,String,String,Map<String , ? extends Object>)", new Object[] { message, id, explanation, useraction, inserts });
/*     */     }
/*     */ 
/*     */     
/*  89 */     this.explanation = explanation;
/*  90 */     this.useraction = useraction;
/*  91 */     this.inserts = inserts;
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSException", "<init>(String,String,String,String,Map<String , ? extends Object>)");
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
/* 109 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedJMSException", "getExplanation()", "getter", this.explanation);
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
/* 125 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedJMSException", "getUserAction()", "getter", this.useraction);
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
/* 145 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedJMSException", "getValue(String)", new Object[] { insertKey });
/*     */     }
/*     */     
/* 148 */     if (null == this.inserts) {
/* 149 */       if (Trace.isOn) {
/* 150 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSException", "getValue(String)", null, 1);
/*     */       }
/*     */       
/* 153 */       return null;
/*     */     } 
/* 155 */     String traceRet1 = String.valueOf(this.inserts.get(insertKey));
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSException", "getValue(String)", traceRet1, 2);
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
/* 175 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedJMSException", "getKeys()");
/*     */     }
/* 177 */     if (null == this.inserts) {
/* 178 */       if (Trace.isOn) {
/* 179 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSException", "getKeys()", null, 1);
/*     */       }
/* 181 */       return null;
/*     */     } 
/* 183 */     Iterator<String> traceRet1 = this.inserts.keySet().iterator();
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSException", "getKeys()", traceRet1, 2);
/*     */     }
/* 187 */     return traceRet1;
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
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedJMSException", "setLinkedException(Exception)", "setter", ex);
/*     */     }
/*     */     
/* 202 */     super.setLinkedException(ex);
/*     */     try {
/* 204 */       initCause(ex);
/*     */     }
/* 206 */     catch (Exception e) {
/* 207 */       if (Trace.isOn) {
/* 208 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.DetailedJMSException", "setLinkedException(Exception)", e);
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
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedJMSException", "toString()");
/*     */     }
/* 225 */     String result = super.toString();
/*     */     
/* 227 */     if (this.explanation != null) {
/* 228 */       result = result + lineSeparator + this.explanation;
/*     */     }
/*     */     
/* 231 */     if (this.useraction != null) {
/* 232 */       result = result + lineSeparator + this.useraction;
/*     */     }
/*     */     
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSException", "toString()", result);
/*     */     }
/* 238 */     return result;
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
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedJMSException", "writeReplace()");
/*     */     }
/* 252 */     Object traceRet1 = new JmsExceptionFactory((Throwable)this);
/* 253 */     if (Trace.isOn) {
/* 254 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSException", "writeReplace()", traceRet1);
/*     */     }
/*     */     
/* 257 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JMSRuntimeException getUnchecked() {
/* 265 */     Throwable cause = getLinkedException();
/*     */     
/* 267 */     JMSRuntimeException traceRet1 = new DetailedJMSRuntimeException(getMessage(), getErrorCode(), this.explanation, this.useraction, this.inserts, (cause != null) ? cause : (Throwable)this);
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedJMSException", "getUnchecked()", "getter", traceRet1);
/*     */     }
/*     */     
/* 272 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\DetailedJMSException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */