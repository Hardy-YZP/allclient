/*    */ package com.ibm.msg.client.wmq.common.internal;
/*    */ 
/*    */ import com.ibm.mq.jmqi.JmqiDefaultThreadPoolFactory;
/*    */ import com.ibm.mq.jmqi.JmqiThreadPoolFactory;
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
/*    */ @Deprecated
/*    */ public class WMQThreadPoolFactory
/*    */   extends JmqiDefaultThreadPoolFactory
/*    */   implements JmqiThreadPoolFactory
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQThreadPoolFactory.java";
/*    */   
/*    */   static {
/* 36 */     if (Trace.isOn)
/* 37 */       Trace.data("com.ibm.msg.client.wmq.common.internal.WMQThreadPoolFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQThreadPoolFactory.java"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\WMQThreadPoolFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */