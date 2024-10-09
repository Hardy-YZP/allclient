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
/*    */ 
/*    */ @Deprecated
/*    */ public class JMSParameterIsNullException
/*    */   extends JMSException
/*    */ {
/*    */   private static final long serialVersionUID = 5666658157993758871L;
/*    */   
/*    */   static {
/* 58 */     if (Trace.isOn) {
/* 59 */       Trace.data("com.ibm.mq.jms.JMSParameterIsNullException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/JMSParameterIsNullException.java");
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
/*    */   public JMSParameterIsNullException(String exceptionText) {
/* 76 */     super(exceptionText);
/* 77 */     if (Trace.isOn) {
/* 78 */       Trace.entry(this, "com.ibm.mq.jms.JMSParameterIsNullException", "<init>(String)", new Object[] { exceptionText });
/*    */     }
/*    */     
/* 81 */     if (Trace.isOn)
/* 82 */       Trace.exit(this, "com.ibm.mq.jms.JMSParameterIsNullException", "<init>(String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\JMSParameterIsNullException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */