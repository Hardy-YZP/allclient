/*    */ package com.ibm.mq.jms;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import com.ibm.msg.client.jms.JmsSession;
/*    */ import com.ibm.msg.client.jms.JmsXAConnection;
/*    */ import com.ibm.msg.client.jms.JmsXASession;
/*    */ import javax.jms.JMSException;
/*    */ import javax.jms.XAConnection;
/*    */ import javax.jms.XASession;
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
/*    */ public class MQXAConnection
/*    */   extends MQConnection
/*    */   implements XAConnection, JmsXAConnection
/*    */ {
/*    */   private static final long serialVersionUID = 1671527406082905353L;
/*    */   
/*    */   static {
/* 41 */     if (Trace.isOn) {
/* 42 */       Trace.data("com.ibm.mq.jms.MQXAConnection", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQXAConnection.java");
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
/*    */   MQXAConnection() {
/* 55 */     if (Trace.isOn) {
/* 56 */       Trace.entry(this, "com.ibm.mq.jms.MQXAConnection", "<init>()");
/*    */     }
/* 58 */     if (Trace.isOn) {
/* 59 */       Trace.exit(this, "com.ibm.mq.jms.MQXAConnection", "<init>()");
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
/*    */   public XASession createXASession() throws JMSException {
/* 73 */     if (Trace.isOn) {
/* 74 */       Trace.entry(this, "com.ibm.mq.jms.MQXAConnection", "createXASession()");
/*    */     }
/*    */     
/* 77 */     MQXASession wrapper = new MQXASession();
/* 78 */     JmsXAConnection xaconn = (JmsXAConnection)this.commonConn;
/* 79 */     XASession xasess = xaconn.createXASession();
/* 80 */     JmsXASession jmsxasess = (JmsXASession)xasess;
/* 81 */     wrapper.setDelegate((JmsSession)jmsxasess);
/*    */     
/* 83 */     if (Trace.isOn) {
/* 84 */       Trace.exit(this, "com.ibm.mq.jms.MQXAConnection", "createXASession()", wrapper);
/*    */     }
/* 86 */     return wrapper;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQXAConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */