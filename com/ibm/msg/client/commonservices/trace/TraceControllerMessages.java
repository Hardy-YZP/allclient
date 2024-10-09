/*    */ package com.ibm.msg.client.commonservices.trace;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import java.util.MissingResourceException;
/*    */ import java.util.ResourceBundle;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TraceControllerMessages
/*    */ {
/*    */   private static final String BUNDLE_NAME = "com.ibm.msg.client.commonservices.trace.controllerMessages";
/* 12 */   private static ResourceBundle RESOURCE_BUNDLE = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getString(String key) {
/* 21 */     if (RESOURCE_BUNDLE == null) {
/* 22 */       RESOURCE_BUNDLE = ResourceBundle.getBundle("com.ibm.msg.client.commonservices.trace.controllerMessages", Locale.getDefault());
/*    */     }
/*    */     
/*    */     try {
/* 26 */       return RESOURCE_BUNDLE.getString(key);
/*    */     }
/* 28 */     catch (MissingResourceException e) {
/* 29 */       return '!' + key + '!';
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\trace\TraceControllerMessages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */