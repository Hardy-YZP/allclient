/*    */ package com.ibm.msg.client.commonservices.propertystore;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.CSIException;
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
/*    */ public class PropertiesFileException
/*    */   extends CSIException
/*    */ {
/*    */   private static final long serialVersionUID = 1109874351626454L;
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/propertystore/PropertiesFileException.java";
/*    */   
/*    */   static {
/* 33 */     if (Trace.isOn) {
/* 34 */       Trace.data("com.ibm.msg.client.commonservices.propertystore.PropertiesFileException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/propertystore/PropertiesFileException.java");
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
/*    */ 
/*    */   
/*    */   public PropertiesFileException(String text, Throwable cause) {
/* 55 */     super(text, cause);
/* 56 */     if (Trace.isOn) {
/* 57 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PropertiesFileException", "<init>(String,Throwable)", new Object[] { text, cause });
/*    */     }
/*    */     
/* 60 */     if (Trace.isOn)
/* 61 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PropertiesFileException", "<init>(String,Throwable)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\propertystore\PropertiesFileException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */