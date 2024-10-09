/*    */ package com.ibm.mq.jms;
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
/*    */ @Deprecated
/*    */ public class PublishSubscribeSetupException
/*    */   extends JMSException
/*    */ {
/*    */   private static final long serialVersionUID = -7647544164548116098L;
/*    */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/PublishSubscribeSetupException.java";
/*    */   
/*    */   static {
/* 37 */     if (Trace.isOn) {
/* 38 */       Trace.data("com.ibm.mq.jms.PublishSubscribeSetupException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/PublishSubscribeSetupException.java");
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
/*    */   public PublishSubscribeSetupException(String reason) {
/* 55 */     super(reason);
/* 56 */     if (Trace.isOn) {
/* 57 */       Trace.entry(this, "com.ibm.mq.jms.PublishSubscribeSetupException", "<init>(String)", new Object[] { reason });
/*    */     }
/*    */     
/* 60 */     if (Trace.isOn)
/* 61 */       Trace.exit(this, "com.ibm.mq.jms.PublishSubscribeSetupException", "<init>(String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\PublishSubscribeSetupException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */