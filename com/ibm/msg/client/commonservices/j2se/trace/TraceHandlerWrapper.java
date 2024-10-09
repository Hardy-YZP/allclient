/*    */ package com.ibm.msg.client.commonservices.j2se.trace;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.TraceHandler;
/*    */ import java.util.logging.Handler;
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
/*    */ public class TraceHandlerWrapper
/*    */   extends Handler
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/trace/TraceHandlerWrapper.java";
/*    */   private TraceHandler internalHandler;
/*    */   
/*    */   public TraceHandlerWrapper(TraceHandler internalHandler) {
/* 39 */     this.internalHandler = internalHandler;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setTraceHandler(TraceHandler newHandler) {
/* 46 */     this.internalHandler = newHandler;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TraceHandler getHandler() {
/* 53 */     return this.internalHandler;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void publish(LogRecord record) {
/* 61 */     this.internalHandler.publish(new TraceRecordImpl(record));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void flush() {
/* 69 */     this.internalHandler.flush();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() throws SecurityException {
/* 77 */     this.internalHandler.close();
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\trace\TraceHandlerWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */