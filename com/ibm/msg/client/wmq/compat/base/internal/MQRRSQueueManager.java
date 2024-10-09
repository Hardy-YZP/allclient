/*    */ package com.ibm.msg.client.wmq.compat.base.internal;
/*    */ 
/*    */ import com.ibm.mq.MQException;
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
/*    */ public class MQRRSQueueManager
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQRRSQueueManager.java";
/*    */   private MQQueueManager qmgr;
/*    */   
/*    */   static {
/* 50 */     if (Trace.isOn) {
/* 51 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQRRSQueueManager", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQRRSQueueManager.java");
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
/*    */   public MQRRSQueueManager(MQQueueManager qmgr) {
/* 65 */     if (Trace.isOn) {
/* 66 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQRRSQueueManager", "<init>(MQQueueManager)", new Object[] { qmgr });
/*    */     }
/*    */     
/* 69 */     this.qmgr = qmgr;
/* 70 */     if (Trace.isOn) {
/* 71 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQRRSQueueManager", "<init>(MQQueueManager)");
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
/*    */   public void honourRRS() throws MQException {
/* 86 */     if (Trace.isOn) {
/* 87 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQRRSQueueManager", "honourRRS()");
/*    */     }
/*    */ 
/*    */     
/* 91 */     if (Trace.isOn) {
/* 92 */       Trace.traceData(this, "honourRRS() - passing through to MQQueueManager", null);
/*    */     }
/* 94 */     this.qmgr.honourRRS();
/* 95 */     if (Trace.isOn)
/* 96 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQRRSQueueManager", "honourRRS()"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQRRSQueueManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */