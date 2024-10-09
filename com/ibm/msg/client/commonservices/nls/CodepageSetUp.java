/*    */ package com.ibm.msg.client.commonservices.nls;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CodepageSetUp
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/nls/CodepageSetUp.java";
/*    */   
/*    */   static {
/* 39 */     if (Trace.isOn) {
/* 40 */       Trace.data("com.ibm.msg.client.commonservices.nls.CodepageSetUp", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/nls/CodepageSetUp.java");
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
/*    */   public static void main(String[] args) {
/* 53 */     if (Trace.isOn) {
/* 54 */       Trace.entry("com.ibm.msg.client.commonservices.nls.CodepageSetUp", "main(String [ ])", new Object[] { args });
/*    */     }
/*    */     
/* 57 */     System.out.println(System.getProperty("console.encoding"));
/* 58 */     if (Trace.isOn)
/* 59 */       Trace.exit("com.ibm.msg.client.commonservices.nls.CodepageSetUp", "main(String [ ])"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\nls\CodepageSetUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */