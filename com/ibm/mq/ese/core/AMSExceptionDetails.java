/*     */ package com.ibm.mq.ese.core;
/*     */ 
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMSExceptionDetails
/*     */ {
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.ese.core.AMSExceptionDetails", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/AMSExceptionDetails.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMSExceptionDetails() {
/*  51 */     this(AmsErrorMessages.empty_message);
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.entry(this, "com.ibm.mq.ese.core.AMSExceptionDetails", "<init>()");
/*     */     }
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.exit(this, "com.ibm.mq.ese.core.AMSExceptionDetails", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public AMSExceptionDetails(String messageid) {
/*  62 */     this(messageid, new HashMap<>());
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.entry(this, "com.ibm.mq.ese.core.AMSExceptionDetails", "<init>(String)", new Object[] { messageid });
/*     */     }
/*     */     
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.exit(this, "com.ibm.mq.ese.core.AMSExceptionDetails", "<init>(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public AMSExceptionDetails(String messageid, HashMap<String, ? extends Object> inserts) {
/*  74 */     this(NLSServices.getMessage(messageid, inserts), messageid, 
/*  75 */         NLSServices.getExplanation(messageid, inserts), 
/*  76 */         NLSServices.getUserAction(messageid, inserts), inserts);
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.mq.ese.core.AMSExceptionDetails", "<init>(String,HashMap<String , ? extends Object>)", new Object[] { messageid, inserts });
/*     */     }
/*     */     
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.exit(this, "com.ibm.mq.ese.core.AMSExceptionDetails", "<init>(String,HashMap<String , ? extends Object>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AMSExceptionDetails(String msg, String messageid, String explanation, String useraction, HashMap<String, ? extends Object> inserts) {
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.entry(this, "com.ibm.mq.ese.core.AMSExceptionDetails", "<init>(String,String,String,String,HashMap<String , ? extends Object>)", new Object[] { msg, messageid, explanation, useraction, inserts });
/*     */     }
/*     */ 
/*     */     
/*  94 */     this.msg = msg;
/*  95 */     this.messageid = messageid;
/*  96 */     this.explanation = explanation;
/*  97 */     this.useraction = useraction;
/*     */     
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.exit(this, "com.ibm.mq.ese.core.AMSExceptionDetails", "<init>(String,String,String,String,HashMap<String , ? extends Object>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   String getMsgId() {
/* 107 */     String result = this.messageid;
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.data(this, "com.ibm.mq.ese.core.AMSExceptionDetails", "getMsgId()", "getter", result);
/*     */     }
/*     */     
/* 112 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.entry(this, "com.ibm.mq.ese.core.AMSExceptionDetails", "toString()");
/*     */     }
/*     */     
/* 121 */     StringBuilder buff = new StringBuilder("");
/* 122 */     if (this.msg != null) {
/* 123 */       buff.append(this.msg);
/*     */     }
/*     */     
/* 126 */     if (this.explanation != null) {
/* 127 */       buff.append(PropertyStore.line_separator + this.explanation);
/*     */     }
/* 129 */     if (this.useraction != null) {
/* 130 */       buff.append(PropertyStore.line_separator + this.useraction);
/*     */     }
/*     */     
/* 133 */     String traceRet1 = buff.toString();
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.exit(this, "com.ibm.mq.ese.core.AMSExceptionDetails", "toString()", traceRet1);
/*     */     }
/* 137 */     return traceRet1;
/*     */   }
/*     */   
/*     */   static final String getMsg(String messageid, HashMap<String, ? extends Object> inserts) {
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.entry("com.ibm.mq.ese.core.AMSExceptionDetails", "getMsg(String,HashMap<String , ? extends Object>)", new Object[] { messageid, inserts });
/*     */     }
/*     */     
/* 145 */     String msg = NLSServices.getMessage(messageid, inserts);
/* 146 */     if (msg == null) {
/* 147 */       msg = "";
/*     */     }
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.exit("com.ibm.mq.ese.core.AMSExceptionDetails", "getMsg(String,HashMap<String , ? extends Object>)", msg);
/*     */     }
/*     */     
/* 153 */     return msg;
/*     */   }
/*     */   
/*     */   static final String getMsg(String messageid) {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.entry("com.ibm.mq.ese.core.AMSExceptionDetails", "getMsg(String)", new Object[] { messageid });
/*     */     }
/*     */     
/* 161 */     String traceRet1 = getMsg(messageid, new HashMap<>());
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.exit("com.ibm.mq.ese.core.AMSExceptionDetails", "getMsg(String)", traceRet1);
/*     */     }
/* 165 */     return traceRet1;
/*     */   }
/*     */   
/* 168 */   private String messageid = null;
/*     */   private final String explanation;
/*     */   private final String msg;
/*     */   private final String useraction;
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\core\AMSExceptionDetails.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */