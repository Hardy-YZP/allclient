/*     */ package com.ibm.msg.client.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ public class DetailedJMSRuntimeException
/*     */   extends JMSRuntimeException
/*     */   implements JmsExceptionDetail
/*     */ {
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.msg.client.jms.DetailedJMSRuntimeException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/DetailedJMSRuntimeException.java");
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
/*     */   public DetailedJMSRuntimeException(String message, String id, String explanation, String useraction, Map<String, ? extends Object> inserts, Throwable cause) {
/*  83 */     super(message, id, cause);
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedJMSRuntimeException", "<init>(String,String,String,String,Map<String , ? extends Object>,Throwable)", new Object[] { message, id, explanation, useraction, inserts, cause });
/*     */     }
/*     */ 
/*     */     
/*  89 */     this.explanation = explanation;
/*  90 */     this.useraction = useraction;
/*  91 */     this.inserts = inserts;
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSRuntimeException", "<init>(String,String,String,String,Map<String , ? extends Object>,Throwable)");
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
/* 109 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedJMSRuntimeException", "getExplanation()", "getter", this.explanation);
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
/* 125 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedJMSRuntimeException", "getUserAction()", "getter", this.useraction);
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
/* 145 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedJMSRuntimeException", "getValue(String)", new Object[] { insertKey });
/*     */     }
/*     */     
/* 148 */     if (null == this.inserts) {
/* 149 */       if (Trace.isOn) {
/* 150 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSRuntimeException", "getValue(String)", null, 1);
/*     */       }
/*     */       
/* 153 */       return null;
/*     */     } 
/* 155 */     String traceRet1 = (String)this.inserts.get(insertKey);
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSRuntimeException", "getValue(String)", traceRet1, 2);
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
/* 175 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedJMSRuntimeException", "getKeys()");
/*     */     }
/* 177 */     if (null == this.inserts) {
/* 178 */       if (Trace.isOn) {
/* 179 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSRuntimeException", "getKeys()", null, 1);
/*     */       }
/*     */       
/* 182 */       return null;
/*     */     } 
/* 184 */     Iterator<String> traceRet1 = this.inserts.keySet().iterator();
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSRuntimeException", "getKeys()", traceRet1, 2);
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
/*     */   public String toString() {
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedJMSRuntimeException", "toString()");
/*     */     }
/* 202 */     String result = super.toString();
/*     */     
/* 204 */     if (this.explanation != null) {
/* 205 */       result = result + lineSeparator + this.explanation;
/*     */     }
/*     */     
/* 208 */     if (this.useraction != null) {
/* 209 */       result = result + lineSeparator + this.useraction;
/*     */     }
/*     */     
/* 212 */     if (Trace.isOn) {
/* 213 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSRuntimeException", "toString()", result);
/*     */     }
/*     */     
/* 216 */     return result;
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
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedJMSRuntimeException", "writeReplace()");
/*     */     }
/* 230 */     Object traceRet1 = new JmsExceptionFactory((Throwable)this);
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedJMSRuntimeException", "writeReplace()", traceRet1);
/*     */     }
/*     */     
/* 235 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\DetailedJMSRuntimeException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */