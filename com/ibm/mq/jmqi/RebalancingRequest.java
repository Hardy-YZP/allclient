/*    */ package com.ibm.mq.jmqi;
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
/*    */ public class RebalancingRequest
/*    */ {
/*    */   private String qmName;
/*    */   private String qmId;
/*    */   
/*    */   public RebalancingRequest(String qmName, String qmId) {
/* 38 */     this.qmName = qmName;
/* 39 */     this.qmId = qmId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getQmName() {
/* 46 */     return this.qmName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getQmId() {
/* 53 */     return this.qmId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     return String.format("qmName:'%s' qmId:'%s'", new Object[] { this.qmName, this.qmId });
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\RebalancingRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */