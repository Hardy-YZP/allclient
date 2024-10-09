/*    */ package com.ibm.mq.headers.internal.validator;
/*    */ 
/*    */ import com.ibm.mq.headers.MQDataException;
/*    */ import com.ibm.mq.headers.internal.Header;
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
/*    */ 
/*    */ public class NullValidator
/*    */   extends FieldValidator
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/validator/NullValidator.java";
/*    */   
/*    */   static {
/* 35 */     if (Trace.isOn) {
/* 36 */       Trace.data("com.ibm.mq.headers.internal.validator.NullValidator", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/validator/NullValidator.java");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public static final NullValidator INSTANCE = new NullValidator();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void validate(Header header) throws MQDataException, IOException {
/* 53 */     if (Trace.isOn) {
/* 54 */       Trace.entry(this, "com.ibm.mq.headers.internal.validator.NullValidator", "validate(Header)", new Object[] { header });
/*    */     }
/*    */ 
/*    */     
/* 58 */     if (Trace.isOn)
/* 59 */       Trace.exit(this, "com.ibm.mq.headers.internal.validator.NullValidator", "validate(Header)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\validator\NullValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */