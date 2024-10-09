/*    */ package com.ibm.msg.client.wmq.internal;
/*    */ 
/*    */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*    */ import com.ibm.mq.jmqi.JmqiMQ;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*    */ import com.ibm.msg.client.provider.ProviderXAConnection;
/*    */ import com.ibm.msg.client.provider.ProviderXASession;
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
/*    */ public class WMQXAConnection
/*    */   extends WMQConnection
/*    */   implements ProviderXAConnection
/*    */ {
/*    */   private static final long serialVersionUID = -1224764132302610448L;
/*    */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQXAConnection.java";
/*    */   
/*    */   static {
/* 39 */     if (Trace.isOn) {
/* 40 */       Trace.data("com.ibm.msg.client.wmq.internal.WMQXAConnection", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQXAConnection.java");
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WMQXAConnection(JmqiEnvironment jmqiEnvironment, JmqiMQ jmqiMq, int jmqiCompId, JmsPropertyContext connectionProps) throws JMSException {
/* 67 */     super(jmqiEnvironment, jmqiMq, jmqiCompId, connectionProps);
/* 68 */     if (Trace.isOn) {
/* 69 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQXAConnection", "<init>(JmqiEnvironment,JmqiMQ,int,JmsPropertyContext)", new Object[] { jmqiEnvironment, jmqiMq, 
/*    */             
/* 71 */             Integer.valueOf(jmqiCompId), connectionProps });
/*    */     }
/* 73 */     if (Trace.isOn) {
/* 74 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQXAConnection", "<init>(JmqiEnvironment,JmqiMQ,int,JmsPropertyContext)");
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
/*    */   public ProviderXASession createXASession(JmsPropertyContext properties) throws JMSException {
/* 88 */     if (Trace.isOn) {
/* 89 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQXAConnection", "createXASession(JmsPropertyContext)", new Object[] { properties });
/*    */     }
/*    */     
/* 92 */     WMQXASession xasession = new WMQXASession(this, properties);
/* 93 */     if (Trace.isOn) {
/* 94 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQXAConnection", "createXASession(JmsPropertyContext)", xasession);
/*    */     }
/*    */     
/* 97 */     return xasession;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\WMQXAConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */