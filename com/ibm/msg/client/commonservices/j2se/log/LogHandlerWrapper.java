/*     */ package com.ibm.msg.client.commonservices.j2se.log;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.Log.LogHandler;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.logging.Handler;
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
/*     */ public class LogHandlerWrapper
/*     */   extends Handler
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/log/LogHandlerWrapper.java";
/*     */   private LogHandler internalHandler;
/*     */   
/*     */   static {
/*  35 */     if (Trace.isOn) {
/*  36 */       Trace.data("com.ibm.msg.client.commonservices.j2se.log.LogHandlerWrapper", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/log/LogHandlerWrapper.java");
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
/*     */   public LogHandlerWrapper(LogHandler internalHandler) {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.LogHandlerWrapper", "<init>(LogHandler)", new Object[] { internalHandler });
/*     */     }
/*     */     
/*  54 */     this.internalHandler = internalHandler;
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.LogHandlerWrapper", "<init>(LogHandler)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws SecurityException {
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.LogHandlerWrapper", "close()");
/*     */     }
/*     */     
/*  71 */     this.internalHandler.close();
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.LogHandlerWrapper", "close()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() {
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.LogHandlerWrapper", "flush()");
/*     */     }
/*     */     
/*  87 */     this.internalHandler.flush();
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.LogHandlerWrapper", "flush()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LogHandler getHandler() {
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogHandlerWrapper", "getHandler()", "getter", this.internalHandler);
/*     */     }
/*     */     
/* 102 */     return this.internalHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void publish(LogRecord record) {
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.LogHandlerWrapper", "publish(LogRecord)", new Object[] { record });
/*     */     }
/*     */     
/* 114 */     this.internalHandler.publish(new LogRecordImpl(record));
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.LogHandlerWrapper", "publish(LogRecord)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTraceHandler(LogHandler newHandler) {
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogHandlerWrapper", "setTraceHandler(LogHandler)", "setter", newHandler);
/*     */     }
/*     */     
/* 130 */     this.internalHandler = newHandler;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\log\LogHandlerWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */