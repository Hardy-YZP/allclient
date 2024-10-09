/*     */ package com.ibm.msg.client.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.jms.MessageEOFException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DetailedMessageEOFException
/*     */   extends MessageEOFException
/*     */   implements JmsExceptionDetail
/*     */ {
/*     */   private static final long serialVersionUID = 4861549387853701641L;
/*     */   private String explanation;
/*     */   private String useraction;
/*     */   private Map<String, ? extends Object> inserts;
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.msg.client.jms.DetailedMessageEOFException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/DetailedMessageEOFException.java");
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
/*     */   public DetailedMessageEOFException(String message, String id, String explanation, String useraction, Map<String, ? extends Object> inserts) {
/*  80 */     super(message, id);
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageEOFException", "<init>(String,String,String,String,Map<String , ? extends Object>)", new Object[] { message, id, explanation, useraction, inserts });
/*     */     }
/*     */ 
/*     */     
/*  86 */     this.explanation = explanation;
/*  87 */     this.useraction = useraction;
/*  88 */     this.inserts = inserts;
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageEOFException", "<init>(String,String,String,String,Map<String , ? extends Object>)");
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
/*     */   public String getExplanation() {
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedMessageEOFException", "getExplanation()", "getter", this.explanation);
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
/*     */   
/*     */   public String getUserAction() {
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedMessageEOFException", "getUserAction()", "getter", this.useraction);
/*     */     }
/*     */     
/* 123 */     return this.useraction;
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
/*     */   public String getValue(String insertKey) {
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageEOFException", "getValue(String)", new Object[] { insertKey });
/*     */     }
/*     */     
/* 142 */     if (null == this.inserts) {
/* 143 */       if (Trace.isOn) {
/* 144 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageEOFException", "getValue(String)", null, 1);
/*     */       }
/*     */       
/* 147 */       return null;
/*     */     } 
/* 149 */     String traceRet1 = (String)this.inserts.get(insertKey);
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageEOFException", "getValue(String)", traceRet1, 2);
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
/*     */   
/*     */   public Iterator<String> getKeys() {
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageEOFException", "getKeys()");
/*     */     }
/* 170 */     if (null == this.inserts) {
/* 171 */       if (Trace.isOn) {
/* 172 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageEOFException", "getKeys()", null, 1);
/*     */       }
/*     */       
/* 175 */       return null;
/*     */     } 
/* 177 */     Iterator<String> traceRet1 = this.inserts.keySet().iterator();
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageEOFException", "getKeys()", traceRet1, 2);
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
/* 194 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedMessageEOFException", "setLinkedException(Exception)", "setter", ex);
/*     */     }
/*     */     
/* 197 */     super.setLinkedException(ex);
/*     */     try {
/* 199 */       initCause(ex);
/*     */     }
/* 201 */     catch (Exception e) {
/* 202 */       if (Trace.isOn) {
/* 203 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.DetailedMessageEOFException", "setLinkedException(Exception)", e);
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
/* 218 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageEOFException", "toString()");
/*     */     }
/* 220 */     String result = super.toString();
/*     */     
/* 222 */     if (this.explanation != null) {
/* 223 */       result = result + PropertyStore.line_separator + this.explanation;
/*     */     }
/*     */     
/* 226 */     if (this.useraction != null) {
/* 227 */       result = result + PropertyStore.line_separator + this.useraction;
/*     */     }
/*     */     
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageEOFException", "toString()", result);
/*     */     }
/*     */     
/* 234 */     return result;
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
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageEOFException", "writeReplace()");
/*     */     }
/* 248 */     Object traceRet1 = new JmsExceptionFactory((Throwable)this);
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageEOFException", "writeReplace()", traceRet1);
/*     */     }
/*     */     
/* 253 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\DetailedMessageEOFException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */