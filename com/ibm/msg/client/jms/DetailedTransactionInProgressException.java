/*     */ package com.ibm.msg.client.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.jms.JMSRuntimeException;
/*     */ import javax.jms.TransactionInProgressException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DetailedTransactionInProgressException
/*     */   extends TransactionInProgressException
/*     */   implements JmsExceptionDetail, JmsConvertableException
/*     */ {
/*     */   private static final long serialVersionUID = 4581209000815194390L;
/*     */   private String explanation;
/*     */   private String useraction;
/*     */   private Map<String, ? extends Object> inserts;
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.msg.client.jms.DetailedTransactionInProgressException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/DetailedTransactionInProgressException.java");
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
/*     */   public DetailedTransactionInProgressException(String message, String id, String explanation, String useraction, Map<String, ? extends Object> inserts) {
/*  84 */     super(message, id);
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedTransactionInProgressException", "<init>(String,String,String,String,Map<String , ? extends Object>)", new Object[] { message, id, explanation, useraction, inserts });
/*     */     }
/*     */ 
/*     */     
/*  90 */     this.explanation = explanation;
/*  91 */     this.useraction = useraction;
/*  92 */     this.inserts = inserts;
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedTransactionInProgressException", "<init>(String,String,String,String,Map<String , ? extends Object>)");
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
/* 110 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedTransactionInProgressException", "getExplanation()", "getter", this.explanation);
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
/* 126 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedTransactionInProgressException", "getUserAction()", "getter", this.useraction);
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
/* 146 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedTransactionInProgressException", "getValue(String)", new Object[] { insertKey });
/*     */     }
/*     */     
/* 149 */     if (null == this.inserts) {
/* 150 */       if (Trace.isOn) {
/* 151 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedTransactionInProgressException", "getValue(String)", null, 1);
/*     */       }
/*     */       
/* 154 */       return null;
/*     */     } 
/* 156 */     String traceRet1 = (String)this.inserts.get(insertKey);
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedTransactionInProgressException", "getValue(String)", traceRet1, 2);
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
/* 176 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedTransactionInProgressException", "getKeys()");
/*     */     }
/*     */     
/* 179 */     if (null == this.inserts) {
/* 180 */       if (Trace.isOn) {
/* 181 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedTransactionInProgressException", "getKeys()", null, 1);
/*     */       }
/*     */       
/* 184 */       return null;
/*     */     } 
/* 186 */     Iterator<String> traceRet1 = this.inserts.keySet().iterator();
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedTransactionInProgressException", "getKeys()", traceRet1, 2);
/*     */     }
/*     */     
/* 191 */     return traceRet1;
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
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedTransactionInProgressException", "setLinkedException(Exception)", "setter", ex);
/*     */     }
/*     */     
/* 206 */     super.setLinkedException(ex);
/*     */     try {
/* 208 */       initCause(ex);
/*     */     }
/* 210 */     catch (Exception e) {
/* 211 */       if (Trace.isOn) {
/* 212 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.DetailedTransactionInProgressException", "setLinkedException(Exception)", e);
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
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedTransactionInProgressException", "toString()");
/*     */     }
/*     */     
/* 230 */     String result = super.toString();
/*     */     
/* 232 */     if (this.explanation != null) {
/* 233 */       result = result + PropertyStore.line_separator + this.explanation;
/*     */     }
/*     */     
/* 236 */     if (this.useraction != null) {
/* 237 */       result = result + PropertyStore.line_separator + this.useraction;
/*     */     }
/*     */     
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedTransactionInProgressException", "toString()", result);
/*     */     }
/*     */     
/* 244 */     return result;
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
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedTransactionInProgressException", "writeReplace()");
/*     */     }
/*     */     
/* 259 */     Object traceRet1 = new JmsExceptionFactory((Throwable)this);
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedTransactionInProgressException", "writeReplace()", traceRet1);
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
/* 274 */     DetailedTransactionInProgressRuntimeException detailedTransactionInProgressRuntimeException = new DetailedTransactionInProgressRuntimeException(getMessage(), getErrorCode(), this.explanation, this.useraction, this.inserts, (cause != null) ? cause : (Throwable)this);
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedTransactionInProgressException", "getUnchecked()", "getter", detailedTransactionInProgressRuntimeException);
/*     */     }
/*     */     
/* 279 */     return (JMSRuntimeException)detailedTransactionInProgressRuntimeException;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\DetailedTransactionInProgressException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */