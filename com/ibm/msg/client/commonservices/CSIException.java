/*    */ package com.ibm.msg.client.commonservices;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CSIException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 19998754325995L;
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/CSIException.java";
/*    */   
/*    */   static {
/* 32 */     if (Trace.isOn) {
/* 33 */       Trace.data("com.ibm.msg.client.commonservices.CSIException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/CSIException.java");
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
/*    */   public CSIException(String text) {
/* 50 */     super(text);
/* 51 */     if (Trace.isOn) {
/* 52 */       Trace.entry(this, "com.ibm.msg.client.commonservices.CSIException", "<init>(String)", new Object[] { text });
/*    */     }
/*    */     
/* 55 */     if (Trace.isOn) {
/* 56 */       Trace.exit(this, "com.ibm.msg.client.commonservices.CSIException", "<init>(String)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CSIException(String text, Throwable cause) {
/* 67 */     super(text, cause);
/* 68 */     if (Trace.isOn) {
/* 69 */       Trace.entry(this, "com.ibm.msg.client.commonservices.CSIException", "<init>(String,Throwable)", new Object[] { text, cause });
/*    */     }
/*    */     
/* 72 */     if (Trace.isOn)
/* 73 */       Trace.exit(this, "com.ibm.msg.client.commonservices.CSIException", "<init>(String,Throwable)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\CSIException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */