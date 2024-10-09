/*    */ package com.ibm.msg.client.commonservices.j2se.trace;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.TraceFormatter;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TraceFormatterWrapper
/*    */   extends Formatter
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/trace/TraceFormatterWrapper.java";
/*    */   private TraceFormatter internalFormatter;
/*    */   
/*    */   public TraceFormatterWrapper(TraceFormatter internalFormatter) {
/* 39 */     this.internalFormatter = internalFormatter;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setTraceFormatter(TraceFormatter newFormatter) {
/* 46 */     this.internalFormatter = newFormatter;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TraceFormatter getFormatter() {
/* 53 */     return this.internalFormatter;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String format(LogRecord record) {
/* 61 */     return this.internalFormatter.format(new TraceRecordImpl(record));
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\trace\TraceFormatterWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */