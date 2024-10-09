/*    */ package com.ibm.mq.headers;
/*    */ 
/*    */ import com.ibm.mq.MQException;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*    */ public class MQExceptionWrapper
/*    */   extends MQDataException
/*    */ {
/*    */   private static final long serialVersionUID = -6990731725270107563L;
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQExceptionWrapper.java";
/*    */   
/*    */   static {
/* 34 */     if (Trace.isOn) {
/* 35 */       Trace.data("com.ibm.mq.headers.MQExceptionWrapper", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQExceptionWrapper.java");
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MQExceptionWrapper(Exception e) {
/* 54 */     super((castToMQException(e)).completionCode, (castToMQException(e)).reasonCode, 
/* 55 */         (castToMQException(e)).exceptionSource);
/* 56 */     if (Trace.isOn) {
/* 57 */       Trace.entry(this, "com.ibm.mq.headers.MQExceptionWrapper", "<init>(Exception)", new Object[] { e });
/*    */     }
/*    */     
/* 60 */     if (Trace.isOn) {
/* 61 */       Trace.exit(this, "com.ibm.mq.headers.MQExceptionWrapper", "<init>(Exception)");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private static MQException castToMQException(Exception e) {
/* 67 */     if (e instanceof MQException) {
/* 68 */       return (MQException)e;
/*    */     }
/*    */     
/* 71 */     if (e instanceof MQExceptionWrapper) {
/* 72 */       Exception cause = (Exception)e.getCause();
/* 73 */       if (cause instanceof MQException) {
/* 74 */         return (MQException)cause;
/*    */       }
/*    */     } 
/*    */     
/* 78 */     throw new RuntimeException("MQException expected but not found", e);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQExceptionWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */