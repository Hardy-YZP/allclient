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
/*    */ 
/*    */ @Deprecated
/*    */ public class JMSNotSupportedException
/*    */   extends JMSException
/*    */ {
/*    */   private static final long serialVersionUID = 5839921257247867961L;
/*    */   
/*    */   static {
/* 59 */     if (Trace.isOn) {
/* 60 */       Trace.data("com.ibm.mq.jms.JMSNotSupportedException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/JMSNotSupportedException.java");
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
/*    */   public JMSNotSupportedException(String reason) {
/* 77 */     super(reason);
/* 78 */     if (Trace.isOn) {
/* 79 */       Trace.entry(this, "com.ibm.mq.jms.JMSNotSupportedException", "<init>(String)", new Object[] { reason });
/*    */     }
/*    */     
/* 82 */     if (Trace.isOn)
/* 83 */       Trace.exit(this, "com.ibm.mq.jms.JMSNotSupportedException", "<init>(String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\JMSNotSupportedException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */