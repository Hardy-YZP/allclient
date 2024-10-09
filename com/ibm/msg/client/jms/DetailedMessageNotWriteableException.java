/*     */ package com.ibm.msg.client.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.jms.JMSRuntimeException;
/*     */ import javax.jms.MessageNotWriteableException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DetailedMessageNotWriteableException
/*     */   extends MessageNotWriteableException
/*     */   implements JmsExceptionDetail, JmsConvertableException
/*     */ {
/*     */   private static final long serialVersionUID = 4145439380761266764L;
/*     */   private String explanation;
/*     */   private String useraction;
/*     */   private Map<String, ? extends Object> inserts;
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/DetailedMessageNotWriteableException.java");
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
/*     */   public DetailedMessageNotWriteableException(String message, String id, String explanation, String useraction, Map<String, ? extends Object> inserts) {
/*  83 */     super(message, id);
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "<init>(String,String,String,String,Map<String , ? extends Object>)", new Object[] { message, id, explanation, useraction, inserts });
/*     */     }
/*     */ 
/*     */     
/*  89 */     this.explanation = explanation;
/*  90 */     this.useraction = useraction;
/*  91 */     this.inserts = inserts;
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "<init>(String,String,String,String,Map<String , ? extends Object>)");
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
/*     */   public String getExplanation() {
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "getExplanation()", "getter", this.explanation);
/*     */     }
/*     */     
/* 110 */     return this.explanation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserAction() {
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "getUserAction()", "getter", this.useraction);
/*     */     }
/*     */     
/* 124 */     return this.useraction;
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
/*     */   public String getValue(String insertKey) {
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "getValue(String)", new Object[] { insertKey });
/*     */     }
/*     */     
/* 142 */     if (null == this.inserts) {
/* 143 */       if (Trace.isOn) {
/* 144 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "getValue(String)", null, 1);
/*     */       }
/*     */       
/* 147 */       return null;
/*     */     } 
/* 149 */     String traceRet1 = (String)this.inserts.get(insertKey);
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "getValue(String)", traceRet1, 2);
/*     */     }
/*     */     
/* 154 */     return traceRet1;
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
/*     */   public Iterator<String> getKeys() {
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "getKeys()");
/*     */     }
/*     */     
/* 170 */     if (null == this.inserts) {
/* 171 */       if (Trace.isOn) {
/* 172 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "getKeys()", null, 1);
/*     */       }
/*     */       
/* 175 */       return null;
/*     */     } 
/* 177 */     Iterator<String> traceRet1 = this.inserts.keySet().iterator();
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "getKeys()", traceRet1, 2);
/*     */     }
/*     */     
/* 182 */     return traceRet1;
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
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "setLinkedException(Exception)", "setter", ex);
/*     */     }
/*     */     
/* 197 */     super.setLinkedException(ex);
/*     */     try {
/* 199 */       initCause(ex);
/*     */     }
/* 201 */     catch (Exception e) {
/* 202 */       if (Trace.isOn) {
/* 203 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "setLinkedException(Exception)", e);
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
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "toString()");
/*     */     }
/*     */     
/* 221 */     String result = super.toString();
/*     */     
/* 223 */     if (this.explanation != null) {
/* 224 */       result = result + " " + this.explanation;
/*     */     }
/*     */     
/* 227 */     if (this.useraction != null) {
/* 228 */       result = result + " " + this.useraction;
/*     */     }
/*     */     
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "toString()", result);
/*     */     }
/*     */     
/* 235 */     return result;
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
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "writeReplace()");
/*     */     }
/*     */     
/* 250 */     Object traceRet1 = new JmsExceptionFactory((Throwable)this);
/* 251 */     if (Trace.isOn) {
/* 252 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "writeReplace()", traceRet1);
/*     */     }
/*     */     
/* 255 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JMSRuntimeException getUnchecked() {
/* 263 */     Throwable cause = getLinkedException();
/*     */     
/* 265 */     DetailedMessageNotWriteableRuntimeException detailedMessageNotWriteableRuntimeException = new DetailedMessageNotWriteableRuntimeException(getMessage(), getErrorCode(), this.explanation, this.useraction, this.inserts, (cause != null) ? cause : (Throwable)this);
/*     */ 
/*     */     
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableException", "getUnchecked()", "getter", detailedMessageNotWriteableRuntimeException);
/*     */     }
/*     */     
/* 272 */     return (JMSRuntimeException)detailedMessageNotWriteableRuntimeException;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\DetailedMessageNotWriteableException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */