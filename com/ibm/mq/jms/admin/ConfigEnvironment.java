/*     */ package com.ibm.mq.jms.admin;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.HashMap;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConfigEnvironment
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/ConfigEnvironment.java";
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.jms.admin.ConfigEnvironment", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/ConfigEnvironment.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   static ResourceBundle bundle = null;
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.entry("com.ibm.mq.jms.admin.ConfigEnvironment", "static()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  59 */       bundle = ResourceBundle.getBundle("com.ibm.mq.jms.admin.resources.JMSADM_MessageResourceBundle");
/*     */     }
/*  61 */     catch (MissingResourceException mre) {
/*  62 */       if (Trace.isOn) {
/*  63 */         Trace.catchBlock("com.ibm.mq.jms.admin.ConfigEnvironment", "static()", mre);
/*     */       }
/*     */ 
/*     */       
/*  67 */       HashMap<Object, Object> info = new HashMap<>();
/*  68 */       info.put("Exception", mre);
/*  69 */       Trace.ffst("ConfigEnvironment", "static", "XA001001", info, null);
/*     */     } 
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.exit("com.ibm.mq.jms.admin.ConfigEnvironment", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static String getErrorMessage(String key, String detail) {
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.entry("com.ibm.mq.jms.admin.ConfigEnvironment", "getErrorMessage(String,String)", new Object[] { key, detail });
/*     */     }
/*     */     
/*  89 */     String message = bundle.getString(key);
/*  90 */     String traceRet1 = (message == null) ? null : formatMessage(message, new String[] { detail });
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.exit("com.ibm.mq.jms.admin.ConfigEnvironment", "getErrorMessage(String,String)", traceRet1);
/*     */     }
/*     */     
/*  95 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static String getMessage(String msg) {
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.entry("com.ibm.mq.jms.admin.ConfigEnvironment", "getMessage(String)", new Object[] { msg });
/*     */     }
/*     */ 
/*     */     
/* 111 */     String traceRet1 = bundle.getString(msg);
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.exit("com.ibm.mq.jms.admin.ConfigEnvironment", "getMessage(String)", traceRet1);
/*     */     }
/* 115 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static Object getMessage(String key, String valRaw, String val) {
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.entry("com.ibm.mq.jms.admin.ConfigEnvironment", "getMessage(String,String,String)", new Object[] { key, valRaw, val });
/*     */     }
/*     */     
/* 132 */     String message = bundle.getString(key);
/* 133 */     String traceRet1 = (message == null) ? null : formatMessage(message, new String[] { valRaw, val });
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.exit("com.ibm.mq.jms.admin.ConfigEnvironment", "getMessage(String,String,String)", traceRet1);
/*     */     }
/*     */     
/* 138 */     return traceRet1;
/*     */   }
/*     */   
/*     */   private static String formatMessage(String message, String... inserts) {
/* 142 */     if (null == inserts) {
/* 143 */       return message;
/*     */     }
/*     */     
/* 146 */     MessageFormat msgFormatter = new MessageFormat(message);
/* 147 */     StringBuffer msgbuff = new StringBuffer();
/* 148 */     msgbuff = msgFormatter.format((Object[])inserts, msgbuff, (FieldPosition)null);
/* 149 */     return msgbuff.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static void start() {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.entry("com.ibm.mq.jms.admin.ConfigEnvironment", "start()");
/*     */     }
/*     */ 
/*     */     
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.exit("com.ibm.mq.jms.admin.ConfigEnvironment", "start()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static Object getMessage(String key, String errorString1) {
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.entry("com.ibm.mq.jms.admin.ConfigEnvironment", "getMessage(String,String)", new Object[] { key, errorString1 });
/*     */     }
/*     */ 
/*     */     
/* 182 */     String message = bundle.getString(key);
/* 183 */     String traceRet1 = (message == null) ? null : formatMessage(message, new String[] { errorString1 });
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.exit("com.ibm.mq.jms.admin.ConfigEnvironment", "getMessage(String,String)", traceRet1);
/*     */     }
/* 187 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\ConfigEnvironment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */