/*    */ package com.ibm.mq.headers.pcf;
/*    */ 
/*    */ import com.ibm.mq.headers.MQDataException;
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
/*    */ public abstract class PCFMessageAgentResponseTracker
/*    */ {
/*    */   static {
/* 41 */     if (Trace.isOn) {
/* 42 */       Trace.data("com.ibm.mq.headers.pcf.PCFMessageAgentResponseTracker", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFMessageAgentResponseTracker.java");
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
/*    */ 
/*    */   
/*    */   public static PCFMessageAgentResponseTracker getInstance(boolean isZos) {
/* 60 */     return isZos ? new PCFMessageAgentResponseTracker390() : new PCFMessageAgentResponseTrackerNon390();
/*    */   }
/*    */   
/*    */   public abstract boolean isLast(PCFMessage paramPCFMessage) throws MQDataException, IOException;
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\PCFMessageAgentResponseTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */