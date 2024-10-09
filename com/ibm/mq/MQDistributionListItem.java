/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQDistributionListItem
/*     */   extends MQMessageTracker
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQDistributionListItem.java";
/*     */   
/*     */   static {
/*  49 */     if (Trace.isOn) {
/*  50 */       Trace.data("com.ibm.mq.MQDistributionListItem", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQDistributionListItem.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public String queueManagerName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public String queueName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public int completionCode = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public int reasonCode = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private MQDistributionListItem next;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private MQDistributionListItem prev;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQDistributionListItem getNextDistributedItem() {
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.data(this, "com.ibm.mq.MQDistributionListItem", "getNextDistributedItem()", "getter", this.next);
/*     */     }
/*     */     
/* 116 */     return this.next;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQDistributionListItem getPreviousDistributedItem() {
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.data(this, "com.ibm.mq.MQDistributionListItem", "getPreviousDistributedItem()", "getter", this.prev);
/*     */     }
/*     */     
/* 130 */     return this.prev;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MQDistributionListItem() {
/* 136 */     this.next = null;
/* 137 */     this.prev = null;
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.MQDistributionListItem", "<init>()"); 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.MQDistributionListItem", "<init>()"); 
/*     */   }
/*     */   
/*     */   protected void setNextDistributedItem(MQDistributionListItem item) {
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.data(this, "com.ibm.mq.MQDistributionListItem", "setNextDistributedItem(MQDistributionListItem)", "setter", item);
/*     */     }
/*     */     
/* 149 */     this.next = item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setPreviousDistributedItem(MQDistributionListItem item) {
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.data(this, "com.ibm.mq.MQDistributionListItem", "setPreviousDistributedItem(MQDistributionListItem)", "setter", item);
/*     */     }
/*     */     
/* 163 */     this.prev = item;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQDistributionListItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */