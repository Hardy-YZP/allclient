/*     */ package com.ibm.msg.client.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.jms.InvalidSelectorRuntimeException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DetailedInvalidSelectorRuntimeException
/*     */   extends InvalidSelectorRuntimeException
/*     */   implements JmsExceptionDetail
/*     */ {
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.msg.client.jms.DetailedInvalidSelectorRuntimeException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/DetailedInvalidSelectorRuntimeException.java");
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
/*  56 */   private static String lineSeparator = PropertyStore.line_separator;
/*     */ 
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
/*     */   
/*     */   public DetailedInvalidSelectorRuntimeException(String message, String id, String explanation, String useraction, Map<String, ? extends Object> inserts, Throwable cause) {
/*  83 */     super(message, id, cause);
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorRuntimeException", "<init>(String,String,String,String,Map<String , ? extends Object>,Throwable)", new Object[] { message, id, explanation, useraction, inserts, cause });
/*     */     }
/*     */ 
/*     */     
/*  89 */     this.explanation = explanation;
/*  90 */     this.useraction = useraction;
/*  91 */     this.inserts = inserts;
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorRuntimeException", "<init>(String,String,String,String,Map<String , ? extends Object>,Throwable)");
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
/* 109 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorRuntimeException", "getExplanation()", "getter", this.explanation);
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
/* 125 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorRuntimeException", "getUserAction()", "getter", this.useraction);
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
/* 145 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorRuntimeException", "getValue(String)", new Object[] { insertKey });
/*     */     }
/*     */     
/* 148 */     if (null == this.inserts) {
/* 149 */       if (Trace.isOn) {
/* 150 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorRuntimeException", "getValue(String)", null, 1);
/*     */       }
/*     */       
/* 153 */       return null;
/*     */     } 
/* 155 */     String traceRet1 = (String)this.inserts.get(insertKey);
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorRuntimeException", "getValue(String)", traceRet1, 2);
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
/* 175 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorRuntimeException", "getKeys()");
/*     */     }
/*     */     
/* 178 */     if (null == this.inserts) {
/* 179 */       if (Trace.isOn) {
/* 180 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorRuntimeException", "getKeys()", null, 1);
/*     */       }
/*     */       
/* 183 */       return null;
/*     */     } 
/* 185 */     Iterator<String> traceRet1 = this.inserts.keySet().iterator();
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorRuntimeException", "getKeys()", traceRet1, 2);
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
/*     */   public String toString() {
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorRuntimeException", "toString()");
/*     */     }
/*     */     
/* 204 */     String result = super.toString();
/*     */     
/* 206 */     if (this.explanation != null) {
/* 207 */       result = result + lineSeparator + this.explanation;
/*     */     }
/*     */     
/* 210 */     if (this.useraction != null) {
/* 211 */       result = result + lineSeparator + this.useraction;
/*     */     }
/*     */     
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorRuntimeException", "toString()", result);
/*     */     }
/*     */     
/* 218 */     return result;
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
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorRuntimeException", "writeReplace()");
/*     */     }
/*     */     
/* 233 */     Object traceRet1 = new JmsExceptionFactory((Throwable)this);
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidSelectorRuntimeException", "writeReplace()", traceRet1);
/*     */     }
/*     */     
/* 238 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\DetailedInvalidSelectorRuntimeException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */