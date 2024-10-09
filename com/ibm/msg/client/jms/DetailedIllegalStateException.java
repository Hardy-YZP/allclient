/*     */ package com.ibm.msg.client.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.jms.IllegalStateException;
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
/*     */ public class DetailedIllegalStateException
/*     */   extends IllegalStateException
/*     */   implements JmsExceptionDetail, JmsConvertableException
/*     */ {
/*     */   private static final long serialVersionUID = 6609320049773790274L;
/*     */   private String explanation;
/*     */   private String useraction;
/*     */   private Map<String, ? extends Object> inserts;
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.msg.client.jms.DetailedIllegalStateException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/DetailedIllegalStateException.java");
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
/*     */   public DetailedIllegalStateException(String message, String id, String explanation, String useraction, Map<String, ? extends Object> inserts) {
/*  84 */     super(message, id);
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedIllegalStateException", "<init>(String,String,String,String,Map<String , ? extends Object>)", new Object[] { message, id, explanation, useraction, inserts });
/*     */     }
/*     */ 
/*     */     
/*  90 */     this.explanation = explanation;
/*  91 */     this.useraction = useraction;
/*  92 */     this.inserts = inserts;
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedIllegalStateException", "<init>(String,String,String,String,Map<String , ? extends Object>)");
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
/* 110 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedIllegalStateException", "getExplanation()", "getter", this.explanation);
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
/* 126 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedIllegalStateException", "getUserAction()", "getter", this.useraction);
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
/* 146 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedIllegalStateException", "getValue(String)", new Object[] { insertKey });
/*     */     }
/*     */     
/* 149 */     if (null == this.inserts) {
/* 150 */       if (Trace.isOn) {
/* 151 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedIllegalStateException", "getValue(String)", null, 1);
/*     */       }
/*     */       
/* 154 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 158 */     Object value = this.inserts.get(insertKey);
/* 159 */     String traceRet1 = (value == null) ? null : value.toString();
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedIllegalStateException", "getValue(String)", traceRet1, 2);
/*     */     }
/*     */     
/* 164 */     return traceRet1;
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
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedIllegalStateException", "getKeys()");
/*     */     }
/* 181 */     if (null == this.inserts) {
/* 182 */       if (Trace.isOn) {
/* 183 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedIllegalStateException", "getKeys()", null, 1);
/*     */       }
/*     */       
/* 186 */       return null;
/*     */     } 
/* 188 */     Iterator<String> traceRet1 = this.inserts.keySet().iterator();
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedIllegalStateException", "getKeys()", traceRet1, 2);
/*     */     }
/*     */     
/* 193 */     return traceRet1;
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
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedIllegalStateException", "setLinkedException(Exception)", "setter", ex);
/*     */     }
/*     */     
/* 208 */     super.setLinkedException(ex);
/*     */     try {
/* 210 */       initCause(ex);
/*     */     }
/* 212 */     catch (Exception e) {
/* 213 */       if (Trace.isOn) {
/* 214 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.DetailedIllegalStateException", "setLinkedException(Exception)", e);
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
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedIllegalStateException", "toString()");
/*     */     }
/* 231 */     String result = super.toString();
/*     */     
/* 233 */     if (this.explanation != null) {
/* 234 */       result = result + PropertyStore.line_separator + this.explanation;
/*     */     }
/*     */     
/* 237 */     if (this.useraction != null) {
/* 238 */       result = result + PropertyStore.line_separator + this.useraction;
/*     */     }
/*     */     
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedIllegalStateException", "toString()", result);
/*     */     }
/*     */     
/* 245 */     return result;
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
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedIllegalStateException", "writeReplace()");
/*     */     }
/* 259 */     Object traceRet1 = new JmsExceptionFactory((Throwable)this);
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedIllegalStateException", "writeReplace()", traceRet1);
/*     */     }
/*     */     
/* 264 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JMSRuntimeException getUnchecked() {
/* 272 */     Throwable cause = getLinkedException();
/*     */     
/* 274 */     DetailedIllegalStateRuntimeException detailedIllegalStateRuntimeException = new DetailedIllegalStateRuntimeException(getMessage(), getErrorCode(), this.explanation, this.useraction, this.inserts, (cause != null) ? cause : (Throwable)this);
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedIllegalStateException", "getUnchecked()", "getter", detailedIllegalStateRuntimeException);
/*     */     }
/*     */     
/* 279 */     return (JMSRuntimeException)detailedIllegalStateRuntimeException;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\DetailedIllegalStateException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */