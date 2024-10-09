/*    */ package com.ibm.mq.ese.intercept;
/*    */ 
/*    */ import com.ibm.mq.ese.core.AMBIException;
/*    */ import com.ibm.mq.ese.service.EseMQException;
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
/*    */ 
/*    */ public class UnprotectException
/*    */   extends Exception
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/UnprotectException.java";
/*    */   private static final long serialVersionUID = 3003152892300216069L;
/*    */   private AMBIException ambiException;
/*    */   
/*    */   static {
/* 37 */     if (Trace.isOn) {
/* 38 */       Trace.data("com.ibm.mq.ese.intercept.UnprotectException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/UnprotectException.java");
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
/*    */   
/*    */   public UnprotectException(AMBIException e) {
/* 58 */     super((Throwable)e);
/*    */     
/* 60 */     if (Trace.isOn) {
/* 61 */       Trace.entry(this, "com.ibm.mq.ese.intercept.UnprotectException", "<init>(AMBIException)", new Object[] { e });
/*    */     }
/*    */     
/* 64 */     this.ambiException = e;
/* 65 */     if (Trace.isOn) {
/* 66 */       Trace.exit(this, "com.ibm.mq.ese.intercept.UnprotectException", "<init>(AMBIException)");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public AMBIException getAmbiException() {
/* 72 */     if (Trace.isOn) {
/* 73 */       Trace.data(this, "com.ibm.mq.ese.intercept.UnprotectException", "getAmbiException()", "getter", this.ambiException);
/*    */     }
/*    */     
/* 76 */     return this.ambiException;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getReasonCode() {
/* 82 */     int traceRet1 = (this.ambiException instanceof EseMQException) ? ((EseMQException)this.ambiException).getReason() : 2063;
/*    */     
/* 84 */     if (Trace.isOn) {
/* 85 */       Trace.data(this, "com.ibm.mq.ese.intercept.UnprotectException", "getReasonCode()", "getter", 
/* 86 */           Integer.valueOf(traceRet1));
/*    */     }
/* 88 */     return traceRet1;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\UnprotectException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */