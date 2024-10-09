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
/*    */ public class SyntaxException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = -5530637566066233752L;
/*    */   
/*    */   static {
/* 53 */     if (Trace.isOn) {
/* 54 */       Trace.data("com.ibm.mq.jms.SyntaxException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/SyntaxException.java");
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
/*    */   public SyntaxException() {
/* 70 */     if (Trace.isOn) {
/* 71 */       Trace.entry(this, "com.ibm.mq.jms.SyntaxException", "<init>()");
/*    */     }
/* 73 */     if (Trace.isOn) {
/* 74 */       Trace.exit(this, "com.ibm.mq.jms.SyntaxException", "<init>()");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SyntaxException(String detail) {
/* 85 */     super(detail);
/* 86 */     if (Trace.isOn) {
/* 87 */       Trace.entry(this, "com.ibm.mq.jms.SyntaxException", "<init>(String)", new Object[] { detail });
/*    */     }
/*    */     
/* 90 */     if (Trace.isOn)
/* 91 */       Trace.exit(this, "com.ibm.mq.jms.SyntaxException", "<init>(String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\SyntaxException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */