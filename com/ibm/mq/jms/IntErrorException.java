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
/*    */ @Deprecated
/*    */ public class IntErrorException
/*    */   extends JMSException
/*    */ {
/*    */   private static final long serialVersionUID = 1583240899725603715L;
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/IntErrorException.java";
/*    */   
/*    */   static {
/* 38 */     if (Trace.isOn) {
/* 39 */       Trace.data("com.ibm.mq.jms.IntErrorException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/IntErrorException.java");
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
/*    */   IntErrorException(String str) {
/* 53 */     super(str);
/* 54 */     if (Trace.isOn) {
/* 55 */       Trace.entry(this, "com.ibm.mq.jms.IntErrorException", "<init>(String)", new Object[] { str });
/*    */     }
/* 57 */     if (Trace.isOn) {
/* 58 */       Trace.exit(this, "com.ibm.mq.jms.IntErrorException", "<init>(String)");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   IntErrorException(String msg, String code) {
/* 64 */     super(msg, code);
/* 65 */     if (Trace.isOn) {
/* 66 */       Trace.entry(this, "com.ibm.mq.jms.IntErrorException", "<init>(String,String)", new Object[] { msg, code });
/*    */     }
/*    */     
/* 69 */     if (Trace.isOn)
/* 70 */       Trace.exit(this, "com.ibm.mq.jms.IntErrorException", "<init>(String,String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\IntErrorException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */