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
/*    */ @Deprecated
/*    */ public class JMSNotActiveException
/*    */   extends JMSException
/*    */ {
/*    */   private static final long serialVersionUID = -6134000540680191637L;
/*    */   
/*    */   static {
/* 56 */     if (Trace.isOn) {
/* 57 */       Trace.data("com.ibm.mq.jms.JMSNotActiveException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/JMSNotActiveException.java");
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
/*    */   public JMSNotActiveException(String reason) {
/* 74 */     super(reason);
/* 75 */     if (Trace.isOn) {
/* 76 */       Trace.entry(this, "com.ibm.mq.jms.JMSNotActiveException", "<init>(String)", new Object[] { reason });
/*    */     }
/*    */     
/* 79 */     if (Trace.isOn)
/* 80 */       Trace.exit(this, "com.ibm.mq.jms.JMSNotActiveException", "<init>(String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\JMSNotActiveException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */