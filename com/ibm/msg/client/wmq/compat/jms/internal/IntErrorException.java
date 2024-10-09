/*    */ package com.ibm.msg.client.wmq.compat.jms.internal;
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
/*    */ public class IntErrorException
/*    */   extends JMSException
/*    */ {
/*    */   private static final long serialVersionUID = 5962697188504691179L;
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/IntErrorException.java";
/*    */   
/*    */   static {
/* 35 */     if (Trace.isOn) {
/* 36 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.IntErrorException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/IntErrorException.java");
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
/*    */   IntErrorException(String str) {
/* 53 */     super(str);
/* 54 */     if (Trace.isOn) {
/* 55 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.IntErrorException", "<init>(String)", new Object[] { str });
/*    */     }
/*    */     
/* 58 */     if (Trace.isOn) {
/* 59 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.IntErrorException", "<init>(String)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   IntErrorException(String msg, String code) {
/* 67 */     super(msg, code);
/* 68 */     if (Trace.isOn) {
/* 69 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.IntErrorException", "<init>(String,String)", new Object[] { msg, code });
/*    */     }
/*    */     
/* 72 */     if (Trace.isOn)
/* 73 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.IntErrorException", "<init>(String,String)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\IntErrorException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */