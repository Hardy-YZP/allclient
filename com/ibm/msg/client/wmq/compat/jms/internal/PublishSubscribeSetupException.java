/*    */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*    */ public class PublishSubscribeSetupException
/*    */   extends JMSException
/*    */ {
/*    */   private static final long serialVersionUID = -7647544164548116098L;
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/PublishSubscribeSetupException.java";
/*    */   
/*    */   static {
/* 36 */     if (Trace.isOn) {
/* 37 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.PublishSubscribeSetupException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/PublishSubscribeSetupException.java");
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
/*    */   private PublishSubscribeSetupException() {
/* 52 */     super("JMS Not Supported Exception");
/* 53 */     if (Trace.isOn) {
/* 54 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.PublishSubscribeSetupException", "<init>()");
/*    */     }
/*    */     
/* 57 */     if (Trace.isOn) {
/* 58 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.PublishSubscribeSetupException", "<init>()");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PublishSubscribeSetupException(String reason) {
/* 69 */     super(reason);
/* 70 */     if (Trace.isOn) {
/* 71 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.PublishSubscribeSetupException", "<init>(String)", new Object[] { reason });
/*    */     }
/*    */ 
/*    */     
/* 75 */     if (Trace.isOn)
/* 76 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.PublishSubscribeSetupException", "<init>(String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\PublishSubscribeSetupException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */