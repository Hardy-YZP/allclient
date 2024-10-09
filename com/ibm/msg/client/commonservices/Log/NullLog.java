/*     */ package com.ibm.msg.client.commonservices.Log;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.provider.log.CSPLog;
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
/*     */ 
/*     */ 
/*     */ public class NullLog
/*     */   implements CSPLog
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/Log/NullLog.java";
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.msg.client.commonservices.Log.NullLog", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/Log/NullLog.java");
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
/*     */   public void close() {
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.entry(this, "com.ibm.msg.client.commonservices.Log.NullLog", "close()");
/*     */     }
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.exit(this, "com.ibm.msg.client.commonservices.Log.NullLog", "close()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.entry(this, "com.ibm.msg.client.commonservices.Log.NullLog", "initialize()");
/*     */     }
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.exit(this, "com.ibm.msg.client.commonservices.Log.NullLog", "initialize()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void log(Object parentClass, String parentClassName, String methodSignature, String key, HashMap<String, ? extends Object> inserts) {
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.entry(this, "com.ibm.msg.client.commonservices.Log.NullLog", "log(Object,String,String,String,HashMap<String , ? extends Object>)", new Object[] { parentClass, parentClassName, methodSignature, key, inserts });
/*     */     }
/*     */ 
/*     */     
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.exit(this, "com.ibm.msg.client.commonservices.Log.NullLog", "log(Object,String,String,String,HashMap<String , ? extends Object>)");
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
/*     */   public void logNLS(Object parentClass, String parentClassName, String methodSignature, String NLSMessage) {
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.entry(this, "com.ibm.msg.client.commonservices.Log.NullLog", "logNLS(Object,String,String,String)", new Object[] { parentClass, parentClassName, methodSignature, NLSMessage });
/*     */     }
/*     */ 
/*     */     
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.exit(this, "com.ibm.msg.client.commonservices.Log.NullLog", "logNLS(Object,String,String,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLogFormatter(LogFormatter newFormatter) {
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.data(this, "com.ibm.msg.client.commonservices.Log.NullLog", "setLogFormatter(LogFormatter)", "setter", newFormatter);
/*     */     }
/*     */ 
/*     */     
/* 125 */     newFormatter.getClass();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLogHandler(LogHandler newHandler) {
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.data(this, "com.ibm.msg.client.commonservices.Log.NullLog", "setLogHandler(LogHandler)", "setter", newHandler);
/*     */     }
/*     */ 
/*     */     
/* 137 */     newHandler.getClass();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\Log\NullLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */