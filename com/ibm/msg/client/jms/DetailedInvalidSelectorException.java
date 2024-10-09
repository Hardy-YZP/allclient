/*     */ package com.ibm.msg.client.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.jms.InvalidSelectorException;
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
/*     */ public class DetailedInvalidSelectorException
/*     */   extends InvalidSelectorException
/*     */   implements JmsExceptionDetail, JmsConvertableException
/*     */ {
/*     */   private static final long serialVersionUID = 2081679850020470430L;
/*     */   private String explanation;
/*     */   private String useraction;
/*     */   private Map<String, ? extends Object> inserts;
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.msg.client.jms.DetailedInvalidSelectorException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/DetailedInvalidSelectorException.java");
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
/*     */   public DetailedInvalidSelectorException(String message, String id, String explanation, String useraction, Map<String, ? extends Object> inserts) {
/*  84 */     super(message, id);
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorException", "<init>(String,String,String,String,Map<String , ? extends Object>)", new Object[] { message, id, explanation, useraction, inserts });
/*     */     }
/*     */ 
/*     */     
/*  90 */     this.explanation = explanation;
/*  91 */     this.useraction = useraction;
/*  92 */     this.inserts = inserts;
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorException", "<init>(String,String,String,String,Map<String , ? extends Object>)");
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
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorException", "getExplanation()", "getter", this.explanation);
/*     */     }
/*     */     
/* 113 */     return this.explanation;
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
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorException", "getUserAction()", "getter", this.useraction);
/*     */     }
/*     */     
/* 129 */     return this.useraction;
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
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorException", "getValue(String)", new Object[] { insertKey });
/*     */     }
/*     */     
/* 149 */     if (null == this.inserts) {
/* 150 */       if (Trace.isOn) {
/* 151 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorException", "getValue(String)", null, 1);
/*     */       }
/*     */       
/* 154 */       return null;
/*     */     } 
/* 156 */     String traceRet1 = (String)this.inserts.get(insertKey);
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorException", "getValue(String)", traceRet1, 2);
/*     */     }
/*     */     
/* 161 */     return traceRet1;
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
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorException", "getKeys()");
/*     */     }
/* 178 */     if (null == this.inserts) {
/* 179 */       if (Trace.isOn) {
/* 180 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorException", "getKeys()", null, 1);
/*     */       }
/*     */       
/* 183 */       return null;
/*     */     } 
/* 185 */     Iterator<String> traceRet1 = this.inserts.keySet().iterator();
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorException", "getKeys()", traceRet1, 2);
/*     */     }
/*     */     
/* 190 */     return traceRet1;
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
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorException", "setLinkedException(Exception)", "setter", ex);
/*     */     }
/*     */     
/* 205 */     super.setLinkedException(ex);
/*     */     try {
/* 207 */       initCause(ex);
/*     */     }
/* 209 */     catch (Exception e) {
/* 210 */       if (Trace.isOn) {
/* 211 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorException", "setLinkedException(Exception)", e);
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
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorException", "toString()");
/*     */     }
/* 228 */     String result = super.toString();
/*     */     
/* 230 */     if (this.explanation != null) {
/* 231 */       result = result + PropertyStore.line_separator + this.explanation;
/*     */     }
/*     */     
/* 234 */     if (this.useraction != null) {
/* 235 */       result = result + PropertyStore.line_separator + this.useraction;
/*     */     }
/*     */     
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorException", "toString()", result);
/*     */     }
/*     */     
/* 242 */     return result;
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
/* 253 */     if (Trace.isOn) {
/* 254 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorException", "writeReplace()");
/*     */     }
/*     */     
/* 257 */     Object traceRet1 = new JmsExceptionFactory((Throwable)this);
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorException", "writeReplace()", traceRet1);
/*     */     }
/*     */     
/* 262 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JMSRuntimeException getUnchecked() {
/* 270 */     Throwable cause = getLinkedException();
/*     */     
/* 272 */     DetailedInvalidSelectorRuntimeException detailedInvalidSelectorRuntimeException = new DetailedInvalidSelectorRuntimeException(getMessage(), getErrorCode(), this.explanation, this.useraction, this.inserts, (cause != null) ? cause : (Throwable)this);
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorException", "getUnchecked()", "getter", detailedInvalidSelectorRuntimeException);
/*     */     }
/*     */     
/* 277 */     return (JMSRuntimeException)detailedInvalidSelectorRuntimeException;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\DetailedInvalidSelectorException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */