/*    */ package com.ibm.mq.headers.internal;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import java.text.FieldPosition;
/*    */ import java.text.MessageFormat;
/*    */ import java.util.MissingResourceException;
/*    */ import java.util.ResourceBundle;
/*    */ 
/*    */ public class HeaderMessages
/*    */ {
/*    */   static {
/* 12 */     if (Trace.isOn) {
/* 13 */       Trace.data("com.ibm.mq.headers.internal.HeaderMessages", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/HeaderMessages.java");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/internal/HeaderMessages.java";
/*    */   
/*    */   private static final String BUNDLE_NAME = "com.ibm.mq.headers.internal.HeaderMessages";
/*    */   
/* 23 */   private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("com.ibm.mq.headers.internal.HeaderMessages");
/*    */   
/*    */   private HeaderMessages() {
/* 26 */     if (Trace.isOn) {
/* 27 */       Trace.entry(this, "com.ibm.mq.headers.internal.HeaderMessages", "<init>()");
/*    */     }
/*    */     
/* 30 */     if (Trace.isOn) {
/* 31 */       Trace.exit(this, "com.ibm.mq.headers.internal.HeaderMessages", "<init>()");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getMessage(String key) {
/* 41 */     if (Trace.isOn) {
/* 42 */       Trace.entry("com.ibm.mq.headers.internal.HeaderMessages", "getMessage(String)", new Object[] { key });
/*    */     }
/*    */     
/* 45 */     String traceRet1 = getMessage(key, null);
/* 46 */     if (Trace.isOn) {
/* 47 */       Trace.exit("com.ibm.mq.headers.internal.HeaderMessages", "getMessage(String)", traceRet1);
/*    */     }
/*    */     
/* 50 */     return traceRet1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getMessage(String key, Object[] inserts) {
/* 59 */     if (Trace.isOn) {
/* 60 */       Trace.entry("com.ibm.mq.headers.internal.HeaderMessages", "getMessage(String,Object [ ])", new Object[] { key, inserts });
/*    */     }
/*    */     
/*    */     try {
/* 64 */       String traceRet1 = key + ":" + embedInserts(RESOURCE_BUNDLE.getString(key), inserts);
/* 65 */       if (Trace.isOn) {
/* 66 */         Trace.exit("com.ibm.mq.headers.internal.HeaderMessages", "getMessage(String,Object [ ])", traceRet1, 1);
/*    */       }
/*    */       
/* 69 */       return traceRet1;
/*    */     }
/* 71 */     catch (MissingResourceException e) {
/* 72 */       if (Trace.isOn) {
/* 73 */         Trace.catchBlock("com.ibm.mq.headers.internal.HeaderMessages", "getMessage(String,Object [ ])", e);
/*    */       }
/*    */       
/* 76 */       String traceRet2 = '!' + key + '!';
/* 77 */       if (Trace.isOn) {
/* 78 */         Trace.exit("com.ibm.mq.headers.internal.HeaderMessages", "getMessage(String,Object [ ])", traceRet2, 2);
/*    */       }
/*    */       
/* 81 */       return traceRet2;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static String embedInserts(String message, Object[] inserts) {
/* 87 */     if (null == inserts) {
/* 88 */       return message;
/*    */     }
/*    */     
/* 91 */     MessageFormat msgFormatter = new MessageFormat(message);
/* 92 */     StringBuffer msgbuff = new StringBuffer();
/* 93 */     msgbuff = msgFormatter.format(inserts, msgbuff, (FieldPosition)null);
/* 94 */     String result = msgbuff.toString();
/* 95 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\internal\HeaderMessages.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */