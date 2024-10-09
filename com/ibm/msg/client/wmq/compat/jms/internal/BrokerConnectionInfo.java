/*    */ package com.ibm.msg.client.wmq.compat.jms.internal;
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
/*    */ class BrokerConnectionInfo
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/BrokerConnectionInfo.java";
/*    */   
/*    */   static {
/* 42 */     if (Trace.isOn) {
/* 43 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.BrokerConnectionInfo", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/BrokerConnectionInfo.java");
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
/* 54 */   String controlQ = null;
/*    */   
/* 56 */   String qmName = null;
/* 57 */   String streamQ = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   BrokerConnectionInfo(String qmName, String controlQ, String streamQ) {
/* 68 */     if (Trace.isOn) {
/* 69 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.BrokerConnectionInfo", "<init>(String,String,String)", new Object[] { qmName, controlQ, streamQ });
/*    */     }
/*    */     
/* 72 */     this.qmName = qmName;
/* 73 */     this.controlQ = controlQ;
/* 74 */     this.streamQ = streamQ;
/* 75 */     if (Trace.isOn)
/* 76 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.BrokerConnectionInfo", "<init>(String,String,String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\BrokerConnectionInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */