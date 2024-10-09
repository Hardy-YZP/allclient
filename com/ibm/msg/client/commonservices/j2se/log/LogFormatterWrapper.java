/*    */ package com.ibm.msg.client.commonservices.j2se.log;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.Log.LogFormatter;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import java.util.logging.Formatter;
/*    */ import java.util.logging.LogRecord;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LogFormatterWrapper
/*    */   extends Formatter
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/log/LogFormatterWrapper.java";
/*    */   private LogFormatter internalFormatter;
/*    */   
/*    */   static {
/* 35 */     if (Trace.isOn) {
/* 36 */       Trace.data("com.ibm.msg.client.commonservices.j2se.log.LogFormatterWrapper", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/log/LogFormatterWrapper.java");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LogFormatterWrapper(LogFormatter internalFormatter) {
/* 50 */     if (Trace.isOn) {
/* 51 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.LogFormatterWrapper", "<init>(LogFormatter)", new Object[] { internalFormatter });
/*    */     }
/*    */     
/* 54 */     this.internalFormatter = internalFormatter;
/* 55 */     if (Trace.isOn) {
/* 56 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.LogFormatterWrapper", "<init>(LogFormatter)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String format(LogRecord record) {
/* 67 */     if (Trace.isOn) {
/* 68 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.log.LogFormatterWrapper", "format(LogRecord)", new Object[] { record });
/*    */     }
/*    */     
/* 71 */     String traceRet1 = this.internalFormatter.format(new LogRecordImpl(record));
/* 72 */     if (Trace.isOn) {
/* 73 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.log.LogFormatterWrapper", "format(LogRecord)", traceRet1);
/*    */     }
/*    */     
/* 76 */     return traceRet1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LogFormatter getFormatter() {
/* 83 */     if (Trace.isOn) {
/* 84 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogFormatterWrapper", "getFormatter()", "getter", this.internalFormatter);
/*    */     }
/*    */     
/* 87 */     return this.internalFormatter;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setTraceFormatter(LogFormatter newFormatter) {
/* 94 */     if (Trace.isOn) {
/* 95 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.log.LogFormatterWrapper", "setTraceFormatter(LogFormatter)", "setter", newFormatter);
/*    */     }
/*    */     
/* 98 */     this.internalFormatter = newFormatter;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\log\LogFormatterWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */