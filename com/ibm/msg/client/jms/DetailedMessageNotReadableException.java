/*     */ package com.ibm.msg.client.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.jms.MessageNotReadableException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DetailedMessageNotReadableException
/*     */   extends MessageNotReadableException
/*     */   implements JmsExceptionDetail
/*     */ {
/*     */   private static final long serialVersionUID = 6596607013862909944L;
/*     */   private String explanation;
/*     */   private String useraction;
/*     */   private Map<String, ? extends Object> inserts;
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.msg.client.jms.DetailedMessageNotReadableException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/DetailedMessageNotReadableException.java");
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
/*     */   public DetailedMessageNotReadableException(String message, String id, String explanation, String useraction, Map<String, ? extends Object> inserts) {
/*  81 */     super(message, id);
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageNotReadableException", "<init>(String,String,String,String,Map<String , ? extends Object>)", new Object[] { message, id, explanation, useraction, inserts });
/*     */     }
/*     */ 
/*     */     
/*  87 */     this.explanation = explanation;
/*  88 */     this.useraction = useraction;
/*  89 */     this.inserts = inserts;
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotReadableException", "<init>(String,String,String,String,Map<String , ? extends Object>)");
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
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedMessageNotReadableException", "getExplanation()", "getter", this.explanation);
/*     */     }
/*     */     
/* 108 */     return this.explanation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserAction() {
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedMessageNotReadableException", "getUserAction()", "getter", this.useraction);
/*     */     }
/*     */     
/* 122 */     return this.useraction;
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
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageNotReadableException", "getValue(String)", new Object[] { insertKey });
/*     */     }
/*     */     
/* 140 */     if (null == this.inserts) {
/* 141 */       if (Trace.isOn) {
/* 142 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotReadableException", "getValue(String)", null, 1);
/*     */       }
/*     */       
/* 145 */       return null;
/*     */     } 
/* 147 */     String traceRet1 = (String)this.inserts.get(insertKey);
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotReadableException", "getValue(String)", traceRet1, 2);
/*     */     }
/*     */     
/* 152 */     return traceRet1;
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
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageNotReadableException", "getKeys()");
/*     */     }
/*     */     
/* 168 */     if (null == this.inserts) {
/* 169 */       if (Trace.isOn) {
/* 170 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotReadableException", "getKeys()", null, 1);
/*     */       }
/*     */       
/* 173 */       return null;
/*     */     } 
/* 175 */     Iterator<String> traceRet1 = this.inserts.keySet().iterator();
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotReadableException", "getKeys()", traceRet1, 2);
/*     */     }
/*     */     
/* 180 */     return traceRet1;
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
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedMessageNotReadableException", "setLinkedException(Exception)", "setter", ex);
/*     */     }
/*     */     
/* 195 */     super.setLinkedException(ex);
/*     */     try {
/* 197 */       initCause(ex);
/*     */     }
/* 199 */     catch (Exception e) {
/* 200 */       if (Trace.isOn) {
/* 201 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.DetailedMessageNotReadableException", "setLinkedException(Exception)", e);
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
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageNotReadableException", "toString()");
/*     */     }
/*     */     
/* 219 */     String result = super.toString();
/*     */     
/* 221 */     if (this.explanation != null) {
/* 222 */       result = result + " " + this.explanation;
/*     */     }
/*     */     
/* 225 */     if (this.useraction != null) {
/* 226 */       result = result + " " + this.useraction;
/*     */     }
/*     */     
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotReadableException", "toString()", result);
/*     */     }
/*     */     
/* 233 */     return result;
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
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageNotReadableException", "writeReplace()");
/*     */     }
/*     */     
/* 248 */     Object traceRet1 = new JmsExceptionFactory((Throwable)this);
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotReadableException", "writeReplace()", traceRet1);
/*     */     }
/*     */     
/* 253 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\DetailedMessageNotReadableException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */