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
/*    */ public class NoMsgListenerException
/*    */   extends JMSException
/*    */ {
/*    */   private static final long serialVersionUID = 2651355629014319185L;
/*    */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/NoMsgListenerException.java";
/*    */   
/*    */   static {
/* 37 */     if (Trace.isOn) {
/* 38 */       Trace.data("com.ibm.mq.jms.NoMsgListenerException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/NoMsgListenerException.java");
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
/*    */   public NoMsgListenerException(String str) {
/* 55 */     super(str);
/* 56 */     if (Trace.isOn) {
/* 57 */       Trace.entry(this, "com.ibm.mq.jms.NoMsgListenerException", "<init>(String)", new Object[] { str });
/*    */     }
/*    */     
/* 60 */     if (Trace.isOn)
/* 61 */       Trace.exit(this, "com.ibm.mq.jms.NoMsgListenerException", "<init>(String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\NoMsgListenerException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */