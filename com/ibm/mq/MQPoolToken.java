/*    */ package com.ibm.mq;
/*    */ 
/*    */ import com.ibm.mq.jmqi.JmqiObject;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MQPoolToken
/*    */   extends JmqiObject
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQPoolToken.java";
/*    */   
/*    */   static {
/* 59 */     if (Trace.isOn) {
/* 60 */       Trace.data("com.ibm.mq.MQPoolToken", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQPoolToken.java");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MQPoolToken() {
/* 69 */     super(MQSESSION.getJmqiEnv());
/* 70 */     if (Trace.isOn) {
/* 71 */       Trace.entry(this, "com.ibm.mq.MQPoolToken", "<init>()");
/*    */     }
/* 73 */     if (Trace.isOn)
/* 74 */       Trace.exit(this, "com.ibm.mq.MQPoolToken", "<init>()"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQPoolToken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */