/*    */ package com.ibm.mq;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ 
/*    */ class MQResourceException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 8491698102170598310L;
/*    */   protected int compCode;
/*    */   protected int reasonCode;
/*    */   
/*    */   static {
/* 13 */     if (Trace.isOn) {
/* 14 */       Trace.data("com.ibm.mq.MQResourceException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQResourceException.java");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected MQException exception;
/*    */ 
/*    */   
/*    */   protected Object source;
/*    */ 
/*    */   
/*    */   protected String msgId;
/*    */ 
/*    */ 
/*    */   
/*    */   public MQResourceException(int compCode, int reasonCode, MQException e) {
/* 31 */     if (Trace.isOn) {
/* 32 */       Trace.entry(this, "com.ibm.mq.MQResourceException", "<init>(int,int,MQException)", new Object[] {
/* 33 */             Integer.valueOf(compCode), Integer.valueOf(reasonCode), e
/*    */           });
/*    */     }
/* 36 */     this.compCode = compCode;
/* 37 */     this.reasonCode = reasonCode;
/* 38 */     this.exception = e;
/*    */     
/* 40 */     if (Trace.isOn) {
/* 41 */       Trace.exit(this, "com.ibm.mq.MQResourceException", "<init>(int,int,MQException)");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public MQResourceException(int compCode, int reasonCode, Object source, String msgId) {
/* 47 */     if (Trace.isOn) {
/* 48 */       Trace.entry(this, "com.ibm.mq.MQResourceException", "<init>(int,int,Object,String)", new Object[] {
/* 49 */             Integer.valueOf(compCode), Integer.valueOf(reasonCode), source, msgId
/*    */           });
/*    */     }
/* 52 */     this.compCode = compCode;
/* 53 */     this.reasonCode = reasonCode;
/* 54 */     this.source = source;
/* 55 */     this.msgId = msgId;
/* 56 */     if (Trace.isOn) {
/* 57 */       Trace.exit(this, "com.ibm.mq.MQResourceException", "<init>(int,int,Object,String)");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public MQException getLinkedException() {
/* 63 */     if (Trace.isOn) {
/* 64 */       Trace.data(this, "com.ibm.mq.MQResourceException", "getLinkedException()", "getter", this.exception);
/*    */     }
/*    */     
/* 67 */     return this.exception;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQResourceException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */