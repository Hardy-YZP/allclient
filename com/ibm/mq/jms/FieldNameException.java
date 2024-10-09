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
/*    */ @Deprecated
/*    */ public class FieldNameException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 8856443597837875679L;
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/FieldNameException.java";
/*    */   
/*    */   static {
/* 52 */     if (Trace.isOn) {
/* 53 */       Trace.data("com.ibm.mq.jms.FieldNameException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/FieldNameException.java");
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
/*    */   public FieldNameException() {
/* 71 */     if (Trace.isOn) {
/* 72 */       Trace.entry(this, "com.ibm.mq.jms.FieldNameException", "<init>()");
/*    */     }
/* 74 */     if (Trace.isOn) {
/* 75 */       Trace.exit(this, "com.ibm.mq.jms.FieldNameException", "<init>()");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FieldNameException(String fieldName) {
/* 85 */     super(fieldName);
/* 86 */     if (Trace.isOn) {
/* 87 */       Trace.entry(this, "com.ibm.mq.jms.FieldNameException", "<init>(String)", new Object[] { fieldName });
/*    */     }
/*    */     
/* 90 */     if (Trace.isOn)
/* 91 */       Trace.exit(this, "com.ibm.mq.jms.FieldNameException", "<init>(String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\FieldNameException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */