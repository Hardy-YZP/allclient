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
/*    */ @Deprecated
/*    */ public class JMSInvalidParameterException
/*    */   extends JMSException
/*    */ {
/*    */   private static final long serialVersionUID = -1234295660252694771L;
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/JMSInvalidParameterException.java";
/*    */   
/*    */   static {
/* 48 */     if (Trace.isOn) {
/* 49 */       Trace.data("com.ibm.mq.jms.JMSInvalidParameterException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/JMSInvalidParameterException.java");
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
/*    */   private JMSInvalidParameterException() {
/* 67 */     super("Invalid parameter value");
/* 68 */     if (Trace.isOn) {
/* 69 */       Trace.entry(this, "com.ibm.mq.jms.JMSInvalidParameterException", "<init>()");
/*    */     }
/* 71 */     if (Trace.isOn) {
/* 72 */       Trace.exit(this, "com.ibm.mq.jms.JMSInvalidParameterException", "<init>()");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JMSInvalidParameterException(String exceptionText) {
/* 82 */     super(exceptionText);
/* 83 */     if (Trace.isOn) {
/* 84 */       Trace.entry(this, "com.ibm.mq.jms.JMSInvalidParameterException", "<init>(String)", new Object[] { exceptionText });
/*    */     }
/*    */     
/* 87 */     if (Trace.isOn)
/* 88 */       Trace.exit(this, "com.ibm.mq.jms.JMSInvalidParameterException", "<init>(String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\JMSInvalidParameterException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */