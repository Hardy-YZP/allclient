/*     */ package com.ibm.msg.client.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.jms.InvalidDestinationRuntimeException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DetailedInvalidDestinationRuntimeException
/*     */   extends InvalidDestinationRuntimeException
/*     */   implements JmsExceptionDetail
/*     */ {
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.msg.client.jms.DetailedInvalidDestinationRuntimeException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/DetailedInvalidDestinationRuntimeException.java");
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
/*  57 */   private static String lineSeparator = PropertyStore.line_separator;
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
/*     */   public DetailedInvalidDestinationRuntimeException(String message, String id, String explanation, String useraction, Map<String, ? extends Object> inserts, Throwable cause) {
/*  84 */     super(message, id, cause);
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedInvalidDestinationRuntimeException", "<init>(String,String,String,String,Map<String , ? extends Object>,Throwable)", new Object[] { message, id, explanation, useraction, inserts, cause });
/*     */     }
/*     */ 
/*     */     
/*  90 */     this.explanation = explanation;
/*  91 */     this.useraction = useraction;
/*  92 */     this.inserts = inserts;
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidDestinationRuntimeException", "<init>(String,String,String,String,Map<String , ? extends Object>,Throwable)");
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
/* 110 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedInvalidDestinationRuntimeException", "getExplanation()", "getter", this.explanation);
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
/* 126 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedInvalidDestinationRuntimeException", "getUserAction()", "getter", this.useraction);
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
/* 146 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedInvalidDestinationRuntimeException", "getValue(String)", new Object[] { insertKey });
/*     */     }
/*     */     
/* 149 */     if (null == this.inserts) {
/* 150 */       if (Trace.isOn) {
/* 151 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidDestinationRuntimeException", "getValue(String)", null, 1);
/*     */       }
/*     */       
/* 154 */       return null;
/*     */     } 
/* 156 */     String traceRet1 = (String)this.inserts.get(insertKey);
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidDestinationRuntimeException", "getValue(String)", traceRet1, 2);
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
/* 176 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedInvalidDestinationRuntimeException", "getKeys()");
/*     */     }
/*     */     
/* 179 */     if (null == this.inserts) {
/* 180 */       if (Trace.isOn) {
/* 181 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidDestinationRuntimeException", "getKeys()", null, 1);
/*     */       }
/*     */       
/* 184 */       return null;
/*     */     } 
/* 186 */     Iterator<String> traceRet1 = this.inserts.keySet().iterator();
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidDestinationRuntimeException", "getKeys()", traceRet1, 2);
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
/*     */   public String toString() {
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedInvalidDestinationRuntimeException", "toString()");
/*     */     }
/*     */     
/* 205 */     String result = super.toString();
/*     */     
/* 207 */     if (this.explanation != null) {
/* 208 */       result = result + lineSeparator + this.explanation;
/*     */     }
/*     */     
/* 211 */     if (this.useraction != null) {
/* 212 */       result = result + lineSeparator + this.useraction;
/*     */     }
/*     */     
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidDestinationRuntimeException", "toString()", result);
/*     */     }
/*     */     
/* 219 */     return result;
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
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedInvalidDestinationRuntimeException", "writeReplace()");
/*     */     }
/*     */     
/* 234 */     Object traceRet1 = new JmsExceptionFactory((Throwable)this);
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedInvalidDestinationRuntimeException", "writeReplace()", traceRet1);
/*     */     }
/*     */     
/* 239 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\DetailedInvalidDestinationRuntimeException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */