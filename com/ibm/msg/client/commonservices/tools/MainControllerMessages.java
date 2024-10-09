/*    */ package com.ibm.msg.client.commonservices.tools;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import java.util.Locale;
/*    */ import java.util.MissingResourceException;
/*    */ import java.util.ResourceBundle;
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
/*    */ public class MainControllerMessages
/*    */ {
/*    */   private static final String BUNDLE_NAME = "com.ibm.msg.client.commonservices.tools.mainControllerMessages";
/*    */   
/*    */   static {
/* 36 */     if (Trace.isOn) {
/* 37 */       Trace.data("com.ibm.msg.client.commonservices.tools.MainControllerMessages", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/tools/MainControllerMessages.java");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   private static ResourceBundle RESOURCE_BUNDLE = null;
/*    */   
/*    */   private MainControllerMessages() {
/* 48 */     if (Trace.isOn) {
/* 49 */       Trace.entry(this, "com.ibm.msg.client.commonservices.tools.MainControllerMessages", "<init>()");
/*    */     }
/*    */     
/* 52 */     if (Trace.isOn) {
/* 53 */       Trace.exit(this, "com.ibm.msg.client.commonservices.tools.MainControllerMessages", "<init>()");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getString(String key) {
/* 64 */     if (Trace.isOn) {
/* 65 */       Trace.entry("com.ibm.msg.client.commonservices.tools.MainControllerMessages", "getString(String)", new Object[] { key });
/*    */     }
/*    */     
/* 68 */     if (RESOURCE_BUNDLE == null) {
/* 69 */       RESOURCE_BUNDLE = ResourceBundle.getBundle("com.ibm.msg.client.commonservices.tools.mainControllerMessages", Locale.getDefault());
/*    */     }
/*    */     
/*    */     try {
/* 73 */       String traceRet1 = RESOURCE_BUNDLE.getString(key);
/* 74 */       if (Trace.isOn) {
/* 75 */         Trace.exit("com.ibm.msg.client.commonservices.tools.MainControllerMessages", "getString(String)", traceRet1, 1);
/*    */       }
/*    */       
/* 78 */       return traceRet1;
/*    */     }
/* 80 */     catch (MissingResourceException e) {
/* 81 */       if (Trace.isOn) {
/* 82 */         Trace.catchBlock("com.ibm.msg.client.commonservices.tools.MainControllerMessages", "getString(String)", e);
/*    */       }
/*    */       
/* 85 */       String traceRet2 = '!' + key + '!';
/* 86 */       if (Trace.isOn) {
/* 87 */         Trace.exit("com.ibm.msg.client.commonservices.tools.MainControllerMessages", "getString(String)", traceRet2, 2);
/*    */       }
/*    */       
/* 90 */       return traceRet2;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\tools\MainControllerMessages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */