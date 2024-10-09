/*    */ package com.ibm.mq;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ 
/*    */ class MQConnectionAllocationException
/*    */   extends MQResourceException {
/*    */   private static final long serialVersionUID = -8799231110115858256L;
/*    */   
/*    */   static {
/* 10 */     if (Trace.isOn) {
/* 11 */       Trace.data("com.ibm.mq.MQConnectionAllocationException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQConnectionAllocationException.java");
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
/*    */   public MQConnectionAllocationException(int compCode, int reasonCode, MQException e) {
/* 23 */     super(compCode, reasonCode, e);
/* 24 */     if (Trace.isOn) {
/* 25 */       Trace.entry(this, "com.ibm.mq.MQConnectionAllocationException", "<init>(int,int,MQException)", new Object[] {
/* 26 */             Integer.valueOf(compCode), 
/* 27 */             Integer.valueOf(reasonCode), e
/*    */           });
/*    */     }
/* 30 */     if (Trace.isOn) {
/* 31 */       Trace.exit(this, "com.ibm.mq.MQConnectionAllocationException", "<init>(int,int,MQException)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public MQConnectionAllocationException(int compCode, int reasonCode, Object source, String msgId) {
/* 38 */     super(compCode, reasonCode, source, msgId);
/* 39 */     if (Trace.isOn) {
/* 40 */       Trace.entry(this, "com.ibm.mq.MQConnectionAllocationException", "<init>(int,int,Object,String)", new Object[] {
/* 41 */             Integer.valueOf(compCode), 
/* 42 */             Integer.valueOf(reasonCode), source, msgId
/*    */           });
/*    */     }
/* 45 */     if (Trace.isOn)
/* 46 */       Trace.exit(this, "com.ibm.mq.MQConnectionAllocationException", "<init>(int,int,Object,String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQConnectionAllocationException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */