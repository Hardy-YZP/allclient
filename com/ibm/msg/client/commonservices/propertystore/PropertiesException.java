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
/*    */ public class PropertiesException
/*    */   extends CSIException
/*    */ {
/*    */   private static final long serialVersionUID = 1109874351626454L;
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/propertystore/PropertiesException.java";
/*    */   
/*    */   static {
/* 33 */     if (Trace.isOn) {
/* 34 */       Trace.data("com.ibm.msg.client.commonservices.propertystore.PropertiesFileException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/propertystore/PropertiesException.java");
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
/*    */   public PropertiesException(String text) {
/* 55 */     super(text);
/* 56 */     if (Trace.isOn) {
/* 57 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PropertiesException", "<init>(String)", new Object[] { text });
/*    */     }
/*    */     
/* 60 */     if (Trace.isOn) {
/* 61 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PropertiesException", "<init>(String)");
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
/*    */   public PropertiesException(String text, Throwable cause) {
/* 73 */     super(text, cause);
/* 74 */     if (Trace.isOn) {
/* 75 */       Trace.entry(this, "com.ibm.msg.client.commonservices.propertystore.PropertiesException", "<init>(String,Throwable)", new Object[] { text, cause });
/*    */     }
/*    */     
/* 78 */     if (Trace.isOn)
/* 79 */       Trace.exit(this, "com.ibm.msg.client.commonservices.propertystore.PropertiesException", "<init>(String,Throwable)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\propertystore\PropertiesException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */