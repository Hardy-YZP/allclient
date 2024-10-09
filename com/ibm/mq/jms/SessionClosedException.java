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
/*    */ public class SessionClosedException
/*    */   extends JMSException
/*    */ {
/*    */   private static final long serialVersionUID = -2611009114682567595L;
/*    */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/SessionClosedException.java";
/*    */   
/*    */   static {
/* 43 */     if (Trace.isOn) {
/* 44 */       Trace.data("com.ibm.mq.jms.SessionClosedException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/SessionClosedException.java");
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
/*    */   public SessionClosedException(String str) {
/* 63 */     super(str);
/* 64 */     if (Trace.isOn) {
/* 65 */       Trace.entry(this, "com.ibm.mq.jms.SessionClosedException", "<init>(String)", new Object[] { str });
/*    */     }
/*    */     
/* 68 */     if (Trace.isOn)
/* 69 */       Trace.exit(this, "com.ibm.mq.jms.SessionClosedException", "<init>(String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\SessionClosedException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */