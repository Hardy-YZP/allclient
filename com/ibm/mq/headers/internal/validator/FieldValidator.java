/*    */ package com.ibm.mq.headers.internal.validator;
/*    */ 
/*    */ import com.ibm.mq.headers.MQDataException;
/*    */ import com.ibm.mq.headers.internal.Header;
/*    */ import com.ibm.mq.internal.MQCommonServices;
/*    */ import com.ibm.mq.jmqi.JmqiObject;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import java.io.IOException;
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
/*    */ public abstract class FieldValidator
/*    */   extends JmqiObject
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/validator/FieldValidator.java";
/*    */   
/*    */   static {
/* 36 */     if (Trace.isOn) {
/* 37 */       Trace.data("com.ibm.mq.headers.internal.validator.FieldValidator", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/validator/FieldValidator.java");
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
/*    */   protected FieldValidator() {
/* 53 */     super(MQCommonServices.jmqiEnv);
/* 54 */     if (Trace.isOn) {
/* 55 */       Trace.entry(this, "com.ibm.mq.headers.internal.validator.FieldValidator", "<init>()");
/*    */     }
/* 57 */     if (Trace.isOn)
/* 58 */       Trace.exit(this, "com.ibm.mq.headers.internal.validator.FieldValidator", "<init>()"); 
/*    */   }
/*    */   
/*    */   public abstract void validate(Header paramHeader) throws MQDataException, IOException;
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\validator\FieldValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */