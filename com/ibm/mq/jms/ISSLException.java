/*    */ package com.ibm.mq.jms;
/*    */ 
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
/*    */ 
/*    */ @Deprecated
/*    */ public class ISSLException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 8159701958930414316L;
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/ISSLException.java";
/*    */   
/*    */   static {
/* 36 */     if (Trace.isOn) {
/* 37 */       Trace.data("com.ibm.mq.jms.ISSLException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/ISSLException.java");
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
/*    */   public ISSLException() {
/* 55 */     if (Trace.isOn) {
/* 56 */       Trace.entry(this, "com.ibm.mq.jms.ISSLException", "<init>()");
/*    */     }
/* 58 */     if (Trace.isOn) {
/* 59 */       Trace.exit(this, "com.ibm.mq.jms.ISSLException", "<init>()");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ISSLException(String reason) {
/* 69 */     super(reason);
/* 70 */     if (Trace.isOn) {
/* 71 */       Trace.entry(this, "com.ibm.mq.jms.ISSLException", "<init>(String)", new Object[] { reason });
/*    */     }
/* 73 */     if (Trace.isOn)
/* 74 */       Trace.exit(this, "com.ibm.mq.jms.ISSLException", "<init>(String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\ISSLException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */