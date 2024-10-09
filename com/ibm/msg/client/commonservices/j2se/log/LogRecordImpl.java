/*     */ package com.ibm.msg.client.commonservices.j2se.log;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.Log.LogRecord;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.logging.LogRecord;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LogRecordImpl
/*     */   implements LogRecord
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/log/LogRecordImpl.java";
/*     */   LogRecord internalRecord;
/*     */   
/*     */   static {
/*  33 */     if (Trace.isOn) {
/*  34 */       Trace.data("com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/log/LogRecordImpl.java");
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
/*     */   public LogRecordImpl(LogRecord record) {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "<init>(LogRecord)", new Object[] { record });
/*     */     }
/*     */     
/*  52 */     this.internalRecord = record;
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "<init>(LogRecord)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage() {
/*  65 */     String traceRet1 = this.internalRecord.getMessage();
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "getMessage()", "getter", traceRet1);
/*     */     }
/*     */     
/*  70 */     return traceRet1;
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
/*     */   public long getMillis() {
/*  85 */     long traceRet1 = this.internalRecord.getMillis();
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "getMillis()", "getter", 
/*  88 */           Long.valueOf(traceRet1));
/*     */     }
/*  90 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] getParameters() {
/*  98 */     Object[] traceRet1 = this.internalRecord.getParameters();
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "getParameters()", "getter", traceRet1);
/*     */     }
/*     */     
/* 103 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getSequenceNumber() {
/* 111 */     long traceRet1 = this.internalRecord.getSequenceNumber();
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "getSequenceNumber()", "getter", 
/* 114 */           Long.valueOf(traceRet1));
/*     */     }
/* 116 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSourceClassName() {
/* 124 */     String traceRet1 = this.internalRecord.getSourceClassName();
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "getSourceClassName()", "getter", traceRet1);
/*     */     }
/*     */     
/* 129 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSourceMethodName() {
/* 137 */     String traceRet1 = this.internalRecord.getSourceMethodName();
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "getSourceMethodName()", "getter", traceRet1);
/*     */     }
/*     */     
/* 142 */     return traceRet1;
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
/*     */   public int getThreadID() {
/* 156 */     int traceRet1 = this.internalRecord.getThreadID();
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "getThreadID()", "getter", 
/* 159 */           Integer.valueOf(traceRet1));
/*     */     }
/* 161 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Throwable getThrown() {
/* 169 */     Throwable traceRet1 = this.internalRecord.getThrown();
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "getThrown()", "getter", traceRet1);
/*     */     }
/*     */     
/* 174 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMessage(String message) {
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "setMessage(String)", "setter", message);
/*     */     }
/*     */     
/* 186 */     this.internalRecord.setMessage(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMillis(long millis) {
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "setMillis(long)", "setter", 
/* 196 */           Long.valueOf(millis));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 205 */     this.internalRecord.setMillis(millis);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameters(Object[] parameters) {
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "setParameters(Object [ ])", "setter", parameters);
/*     */     }
/*     */     
/* 217 */     this.internalRecord.setParameters(parameters);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSequenceNumber(long seq) {
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "setSequenceNumber(long)", "setter", 
/* 227 */           Long.valueOf(seq));
/*     */     }
/* 229 */     this.internalRecord.setSequenceNumber(seq);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSourceClassName(String sourceClassName) {
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "setSourceClassName(String)", "setter", sourceClassName);
/*     */     }
/*     */     
/* 241 */     this.internalRecord.setSourceClassName(sourceClassName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSourceMethodName(String sourceMethodName) {
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "setSourceMethodName(String)", "setter", sourceMethodName);
/*     */     }
/*     */     
/* 253 */     this.internalRecord.setSourceMethodName(sourceMethodName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThreadID(int threadID) {
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "setThreadID(int)", "setter", 
/* 262 */           Integer.valueOf(threadID));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 271 */     this.internalRecord.setThreadID(threadID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThrown(Throwable thrown) {
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogRecordImpl", "setThrown(Throwable)", "setter", thrown);
/*     */     }
/*     */     
/* 283 */     this.internalRecord.setThrown(thrown);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\log\LogRecordImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */