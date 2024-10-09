/*    */ package com.ibm.mq.headers.internal.validator;
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
/*    */ public class MQHeaderValidationException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 4585408292349346017L;
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/validator/MQHeaderValidationException.java";
/*    */   
/*    */   static {
/* 29 */     if (Trace.isOn) {
/* 30 */       Trace.data("com.ibm.mq.headers.internal.validator.MQHeaderValidationException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/validator/MQHeaderValidationException.java");
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
/*    */   public MQHeaderValidationException(String message) {
/* 47 */     super(message);
/* 48 */     if (Trace.isOn) {
/* 49 */       Trace.entry(this, "com.ibm.mq.headers.internal.validator.MQHeaderValidationException", "<init>(String)", new Object[] { message });
/*    */     }
/*    */     
/* 52 */     if (Trace.isOn)
/* 53 */       Trace.exit(this, "com.ibm.mq.headers.internal.validator.MQHeaderValidationException", "<init>(String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\validator\MQHeaderValidationException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */