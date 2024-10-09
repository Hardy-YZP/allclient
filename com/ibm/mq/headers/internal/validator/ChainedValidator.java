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
/*    */ public final class ChainedValidator
/*    */   extends FieldValidator
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/validator/ChainedValidator.java";
/*    */   final FieldValidator current;
/*    */   final FieldValidator previous;
/*    */   
/*    */   static {
/* 34 */     if (Trace.isOn) {
/* 35 */       Trace.data("com.ibm.mq.headers.internal.validator.ChainedValidator", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/validator/ChainedValidator.java");
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
/*    */   public ChainedValidator(FieldValidator current, FieldValidator previous) {
/* 51 */     if (Trace.isOn) {
/* 52 */       Trace.entry(this, "com.ibm.mq.headers.internal.validator.ChainedValidator", "<init>(FieldValidator,FieldValidator)", new Object[] { current, previous });
/*    */     }
/*    */     
/* 55 */     this.current = current;
/* 56 */     this.previous = previous;
/* 57 */     if (Trace.isOn) {
/* 58 */       Trace.exit(this, "com.ibm.mq.headers.internal.validator.ChainedValidator", "<init>(FieldValidator,FieldValidator)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void validate(Header h) throws MQDataException, IOException {
/* 69 */     if (Trace.isOn) {
/* 70 */       Trace.entry(this, "com.ibm.mq.headers.internal.validator.ChainedValidator", "validate(Header)", new Object[] { h });
/*    */     }
/*    */     
/* 73 */     this.previous.validate(h);
/* 74 */     this.current.validate(h);
/* 75 */     if (Trace.isOn)
/* 76 */       Trace.exit(this, "com.ibm.mq.headers.internal.validator.ChainedValidator", "validate(Header)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\validator\ChainedValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */