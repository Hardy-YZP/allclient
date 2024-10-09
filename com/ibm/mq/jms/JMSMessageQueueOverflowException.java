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
/*    */ public class JMSMessageQueueOverflowException
/*    */   extends JMSException
/*    */ {
/*    */   private static final long serialVersionUID = -8966546606118312900L;
/*    */   
/*    */   static {
/* 57 */     if (Trace.isOn) {
/* 58 */       Trace.data("com.ibm.mq.jms.JMSMessageQueueOverflowException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/JMSMessageQueueOverflowException.java");
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
/*    */   public JMSMessageQueueOverflowException(String exceptionText) {
/* 75 */     super(exceptionText);
/* 76 */     if (Trace.isOn) {
/* 77 */       Trace.entry(this, "com.ibm.mq.jms.JMSMessageQueueOverflowException", "<init>(String)", new Object[] { exceptionText });
/*    */     }
/*    */     
/* 80 */     if (Trace.isOn)
/* 81 */       Trace.exit(this, "com.ibm.mq.jms.JMSMessageQueueOverflowException", "<init>(String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\JMSMessageQueueOverflowException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */