/*    */ package com.ibm.msg.client.wmq.common.internal;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*    */ import com.ibm.msg.client.provider.ProviderConnection;
/*    */ import java.util.HashMap;
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
/*    */ 
/*    */ 
/*    */ public class WMQTemporaryQueue
/*    */   extends WMQDestination
/*    */ {
/*    */   private static final long serialVersionUID = 4559670524300336080L;
/*    */   
/*    */   static {
/* 40 */     if (Trace.isOn) {
/* 41 */       Trace.data("com.ibm.msg.client.wmq.common.internal.WMQTemporaryQueue", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/WMQTemporaryQueue.java");
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
/*    */   
/*    */   public WMQTemporaryQueue(String name, ProviderConnection connection, JmsPropertyContext props) throws JMSException {
/* 64 */     super(true, 1, name, connection, props);
/* 65 */     if (Trace.isOn) {
/* 66 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.WMQTemporaryQueue", "<init>(String,ProviderConnection,JmsPropertyContext)", new Object[] { name, connection, props });
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 71 */     if (Trace.isOn) {
/* 72 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.WMQTemporaryQueue", "<init>(String,ProviderConnection,JmsPropertyContext)");
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
/*    */   public void checkAccess(WMQCommonConnection connection) throws JMSException {
/* 89 */     if (connection != this.connection) {
/* 90 */       HashMap<String, String> inserts = new HashMap<>();
/* 91 */       inserts.put("XMSC_DESTINATION_NAME", getName());
/* 92 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ0003", inserts);
/*    */       
/* 94 */       throw je;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\WMQTemporaryQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */