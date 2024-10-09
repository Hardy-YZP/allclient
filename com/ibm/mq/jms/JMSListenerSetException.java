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
/*    */ @Deprecated
/*    */ public class JMSListenerSetException
/*    */   extends JMSException
/*    */ {
/*    */   private static final long serialVersionUID = 7952280710319445558L;
/*    */   
/*    */   static {
/* 53 */     if (Trace.isOn) {
/* 54 */       Trace.data("com.ibm.mq.jms.JMSListenerSetException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/JMSListenerSetException.java");
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
/*    */   public JMSListenerSetException(String reason) {
/* 71 */     super(reason);
/* 72 */     if (Trace.isOn) {
/* 73 */       Trace.entry(this, "com.ibm.mq.jms.JMSListenerSetException", "<init>(String)", new Object[] { reason });
/*    */     }
/*    */     
/* 76 */     if (Trace.isOn)
/* 77 */       Trace.exit(this, "com.ibm.mq.jms.JMSListenerSetException", "<init>(String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\JMSListenerSetException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */