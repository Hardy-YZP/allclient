/*     */ package com.ibm.mq.ese.core;
/*     */ 
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
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
/*     */ public class AMBIException
/*     */   extends Exception
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/AMBIException.java";
/*     */   private static final long serialVersionUID = 2373693568822990241L;
/*     */   protected AMSExceptionDetails details;
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.ese.core.AMBIException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/AMBIException.java");
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
/*     */   public AMBIException(Throwable cause) {
/*  62 */     super(cause);
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.entry(this, "com.ibm.mq.ese.core.AMBIException", "<init>(Throwable)", new Object[] { cause });
/*     */     }
/*     */     
/*  67 */     this.details = new AMSExceptionDetails(AmsErrorMessages.empty_message);
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.exit(this, "com.ibm.mq.ese.core.AMBIException", "<init>(Throwable)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public AMBIException(String msg, String messageid, String explanation, String useraction, HashMap<String, ? extends Object> inserts) {
/*  75 */     super(msg);
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry(this, "com.ibm.mq.ese.core.AMBIException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)", new Object[] { msg, messageid, explanation, useraction, inserts });
/*     */     }
/*     */ 
/*     */     
/*  81 */     this.details = new AMSExceptionDetails(msg, messageid, explanation, useraction, inserts);
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit(this, "com.ibm.mq.ese.core.AMBIException", "<init>(String,String,String,String,HashMap<String , ? extends Object>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AMBIException(String messageid, HashMap<String, ? extends Object> inserts) {
/*  90 */     super(AMSExceptionDetails.getMsg(messageid, inserts));
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry(this, "com.ibm.mq.ese.core.AMBIException", "<init>(String,HashMap<String , ? extends Object>)", new Object[] { messageid, inserts });
/*     */     }
/*     */     
/*  95 */     this.details = new AMSExceptionDetails(messageid, inserts);
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.exit(this, "com.ibm.mq.ese.core.AMBIException", "<init>(String,HashMap<String , ? extends Object>)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AMBIException(String messageid, HashMap<String, ? extends Object> inserts, Throwable cause) {
/* 104 */     super(AMSExceptionDetails.getMsg(messageid, inserts), cause);
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.entry(this, "com.ibm.mq.ese.core.AMBIException", "<init>(String,HashMap<String , ? extends Object>,Throwable)", new Object[] { messageid, inserts, cause });
/*     */     }
/*     */ 
/*     */     
/* 110 */     this.details = new AMSExceptionDetails(messageid, inserts);
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.exit(this, "com.ibm.mq.ese.core.AMBIException", "<init>(String,HashMap<String , ? extends Object>,Throwable)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AMBIException(String messageid) {
/* 119 */     super(AMSExceptionDetails.getMsg(messageid));
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.mq.ese.core.AMBIException", "<init>(String)", new Object[] { messageid });
/*     */     }
/*     */     
/* 124 */     this.details = new AMSExceptionDetails(messageid);
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.exit(this, "com.ibm.mq.ese.core.AMBIException", "<init>(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public AMBIException(String messageid, Throwable cause) {
/* 132 */     super(AMSExceptionDetails.getMsg(messageid), cause);
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.entry(this, "com.ibm.mq.ese.core.AMBIException", "<init>(String,Throwable)", new Object[] { messageid, cause });
/*     */     }
/*     */     
/* 137 */     this.details = new AMSExceptionDetails(messageid);
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.exit(this, "com.ibm.mq.ese.core.AMBIException", "<init>(String,Throwable)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMsgId() {
/* 145 */     String result = this.details.getMsgId();
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.data(this, "com.ibm.mq.ese.core.AMBIException", "getMsgId()", "getter", result);
/*     */     }
/*     */     
/* 150 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.entry(this, "com.ibm.mq.ese.core.AMBIException", "toString()");
/*     */     }
/* 159 */     String traceRet1 = this.details.toString();
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.exit(this, "com.ibm.mq.ese.core.AMBIException", "toString()", traceRet1);
/*     */     }
/* 163 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\core\AMBIException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */