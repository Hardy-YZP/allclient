/*    */ package com.ibm.msg.client.wmq.common.internal;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*    */ import com.ibm.msg.client.provider.ProviderConnection;
/*    */ import javax.jms.JMSException;
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
/*    */ public class WMQTemporaryTopic
/*    */   extends WMQDestination
/*    */ {
/*    */   private static final long serialVersionUID = -1319373669255084239L;
/*    */   
/*    */   static {
/* 36 */     if (Trace.isOn) {
/* 37 */       Trace.data("com.ibm.msg.client.wmq.common.internal.WMQTemporaryTopic", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQTemporaryTopic.java");
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WMQTemporaryTopic(String name, ProviderConnection connection, JmsPropertyContext props) throws JMSException {
/* 59 */     super(true, 2, name, connection, props);
/* 60 */     if (Trace.isOn) {
/* 61 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQTemporaryTopic", "<init>(String,ProviderConnection,JmsPropertyContext)", new Object[] { name, connection, props });
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 66 */     if (Trace.isOn)
/* 67 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQTemporaryTopic", "<init>(String,ProviderConnection,JmsPropertyContext)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\WMQTemporaryTopic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */