/*     */ package com.ibm.msg.client.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.jms.MessageNotWriteableRuntimeException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DetailedMessageNotWriteableRuntimeException
/*     */   extends MessageNotWriteableRuntimeException
/*     */   implements JmsExceptionDetail
/*     */ {
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.msg.client.jms.DetailedMessageNotWriteableRuntimeException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/DetailedMessageNotWriteableRuntimeException.java");
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
/*     */   public DetailedMessageNotWriteableRuntimeException(String message, String id, String explanation, String useraction, Map<String, ? extends Object> inserts, Throwable cause) {
/*  83 */     super(message, id, cause);
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableRuntimeException", "<init>(String,String,String,String,Map<String , ? extends Object>,Throwable)", new Object[] { message, id, explanation, useraction, inserts, cause });
/*     */     }
/*     */ 
/*     */     
/*  89 */     this.explanation = explanation;
/*  90 */     this.useraction = useraction;
/*  91 */     this.inserts = inserts;
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableRuntimeException", "<init>(String,String,String,String,Map<String , ? extends Object>,Throwable)");
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
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableRuntimeException", "getExplanation()", "getter", this.explanation);
/*     */     }
/*     */     
/* 111 */     return this.explanation;
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
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.data(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableRuntimeException", "getUserAction()", "getter", this.useraction);
/*     */     }
/*     */     
/* 126 */     return this.useraction;
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
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableRuntimeException", "getValue(String)", new Object[] { insertKey });
/*     */     }
/*     */     
/* 145 */     if (null == this.inserts) {
/* 146 */       if (Trace.isOn) {
/* 147 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableRuntimeException", "getValue(String)", null, 1);
/*     */       }
/*     */       
/* 150 */       return null;
/*     */     } 
/* 152 */     String traceRet1 = (String)this.inserts.get(insertKey);
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableRuntimeException", "getValue(String)", traceRet1, 2);
/*     */     }
/*     */     
/* 157 */     return traceRet1;
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
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableRuntimeException", "getKeys()");
/*     */     }
/*     */     
/* 174 */     if (null == this.inserts) {
/* 175 */       if (Trace.isOn) {
/* 176 */         Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableRuntimeException", "getKeys()", null, 1);
/*     */       }
/*     */       
/* 179 */       return null;
/*     */     } 
/* 181 */     Iterator<String> traceRet1 = this.inserts.keySet().iterator();
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableRuntimeException", "getKeys()", traceRet1, 2);
/*     */     }
/*     */     
/* 186 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableRuntimeException", "toString()");
/*     */     }
/*     */     
/* 200 */     String result = super.toString();
/*     */     
/* 202 */     if (this.explanation != null) {
/* 203 */       result = result + lineSeparator + this.explanation;
/*     */     }
/*     */     
/* 206 */     if (this.useraction != null) {
/* 207 */       result = result + lineSeparator + this.useraction;
/*     */     }
/*     */     
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableRuntimeException", "toString()", result);
/*     */     }
/*     */     
/* 214 */     return result;
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
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.entry(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableRuntimeException", "writeReplace()");
/*     */     }
/*     */     
/* 229 */     Object traceRet1 = new JmsExceptionFactory((Throwable)this);
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.exit(this, "com.ibm.msg.client.jms.DetailedMessageNotWriteableRuntimeException", "writeReplace()", traceRet1);
/*     */     }
/*     */     
/* 234 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\DetailedMessageNotWriteableRuntimeException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */