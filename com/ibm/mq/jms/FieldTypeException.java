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
/*    */ @Deprecated
/*    */ public class FieldTypeException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 4502500547036440177L;
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/FieldTypeException.java";
/*    */   
/*    */   static {
/* 53 */     if (Trace.isOn) {
/* 54 */       Trace.data("com.ibm.mq.jms.FieldTypeException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/FieldTypeException.java");
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
/*    */   public FieldTypeException() {
/* 72 */     if (Trace.isOn) {
/* 73 */       Trace.entry(this, "com.ibm.mq.jms.FieldTypeException", "<init>()");
/*    */     }
/* 75 */     if (Trace.isOn) {
/* 76 */       Trace.exit(this, "com.ibm.mq.jms.FieldTypeException", "<init>()");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FieldTypeException(String detail) {
/* 86 */     super(detail);
/* 87 */     if (Trace.isOn) {
/* 88 */       Trace.entry(this, "com.ibm.mq.jms.FieldTypeException", "<init>(String)", new Object[] { detail });
/*    */     }
/*    */     
/* 91 */     if (Trace.isOn)
/* 92 */       Trace.exit(this, "com.ibm.mq.jms.FieldTypeException", "<init>(String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\FieldTypeException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */