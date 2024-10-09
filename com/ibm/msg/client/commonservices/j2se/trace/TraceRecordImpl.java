/*     */ package com.ibm.msg.client.commonservices.j2se.trace;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.TraceRecord;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TraceRecordImpl
/*     */   implements TraceRecord
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/trace/TraceRecordImpl.java";
/*     */   LogRecord internalRecord;
/*     */   
/*     */   public TraceRecordImpl(LogRecord record) {
/*  41 */     this.internalRecord = record;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLevel() {
/*  49 */     return this.internalRecord.getLevel().intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage() {
/*  57 */     return this.internalRecord.getMessage();
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
/*  72 */     return this.internalRecord.getMillis();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] getParameters() {
/*  80 */     return this.internalRecord.getParameters();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getSequenceNumber() {
/*  88 */     return this.internalRecord.getSequenceNumber();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSourceClassName() {
/*  96 */     return this.internalRecord.getSourceClassName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSourceMethodName() {
/* 104 */     return this.internalRecord.getSourceMethodName();
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
/*     */   public int getThreadID() {
/* 119 */     return this.internalRecord.getThreadID();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Throwable getThrown() {
/* 127 */     return this.internalRecord.getThrown();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLevel(int level) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMessage(String message) {
/* 144 */     this.internalRecord.setMessage(message);
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
/*     */   public void setMillis(long millis) {
/* 159 */     this.internalRecord.setMillis(millis);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameters(Object[] parameters) {
/* 167 */     this.internalRecord.setParameters(parameters);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSequenceNumber(long seq) {
/* 175 */     this.internalRecord.setSequenceNumber(seq);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSourceClassName(String sourceClassName) {
/* 183 */     this.internalRecord.setSourceClassName(sourceClassName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSourceMethodName(String sourceMethodName) {
/* 191 */     this.internalRecord.setSourceMethodName(sourceMethodName);
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
/*     */   public void setThreadID(int threadID) {
/* 206 */     this.internalRecord.setThreadID(threadID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThrown(Throwable thrown) {
/* 214 */     this.internalRecord.setThrown(thrown);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\trace\TraceRecordImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */