/*     */ package com.ibm.mq.jmqi.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.text.MessageFormat;
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
/*     */ public class JmqiResource
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/JmqiResource.java";
/*     */   public static final String MID_OpFailed = "MQJE114";
/*     */   public static final String MID_OpFailedResourceClosed = "MQJE118";
/*     */   public static final String MID_Internal = "MQJE999";
/*     */   public static final String EXPLANATION = "MQJE115";
/*     */   public static final String ACTION = "MQJE116";
/*     */   public static final String CANT_FIND_NATIVE_CHARSET = "MQJE117";
/*  57 */   private static ResourceBundle myMessages = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.entry("com.ibm.mq.jmqi.internal.JmqiResource", "static()");
/*     */     }
/*  68 */     String bundleName = "com.ibm.mq.jmqi.internal.messages";
/*     */     try {
/*  70 */       myMessages = ResourceBundle.getBundle(bundleName);
/*     */     }
/*  72 */     catch (Throwable e) {
/*  73 */       if (Trace.isOn) {
/*  74 */         Trace.catchBlock("com.ibm.mq.jmqi.internal.JmqiResource", "static()", e);
/*     */       }
/*  76 */       System.err.println("JmqiResource: bundle '" + bundleName + "' not found");
/*  77 */       e.printStackTrace();
/*     */     } 
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.exit("com.ibm.mq.jmqi.internal.JmqiResource", "static()");
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
/*     */   public static String getStringIfAvailable(JmqiEnvironment env, String key, String[] inserts) {
/* 105 */     String formattedMessage = null;
/*     */     
/* 107 */     if (myMessages != null) {
/*     */       try {
/* 109 */         String message = myMessages.getString(key);
/* 110 */         formattedMessage = MessageFormat.format(message, (Object[])inserts);
/*     */       }
/* 112 */       catch (MissingResourceException missingResourceException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 117 */     return formattedMessage;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getString(String key, String[] inserts) {
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.entry("com.ibm.mq.jmqi.internal.JmqiResource", "getString(String,String [ ])", new Object[] { key, inserts });
/*     */     }
/*     */ 
/*     */     
/* 137 */     String formattedMessage = null;
/*     */     
/*     */     try {
/* 140 */       String message = myMessages.getString(key);
/* 141 */       formattedMessage = MessageFormat.format(message, (Object[])inserts);
/*     */     }
/* 143 */     catch (NullPointerException npe) {
/* 144 */       if (Trace.isOn) {
/* 145 */         Trace.catchBlock("com.ibm.mq.jmqi.internal.JmqiResource", "getString(String,String [ ])", npe, 1);
/*     */       
/*     */       }
/*     */     }
/* 149 */     catch (MissingResourceException mre) {
/* 150 */       if (Trace.isOn) {
/* 151 */         Trace.catchBlock("com.ibm.mq.jmqi.internal.JmqiResource", "getString(String,String [ ])", mre, 2);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 156 */     if (formattedMessage == null) {
/* 157 */       StringBuffer buffer = new StringBuffer();
/* 158 */       buffer.append("Message not found for ");
/* 159 */       buffer.append(key);
/* 160 */       for (int i = 0; i < inserts.length; i++) {
/* 161 */         buffer.append(", ");
/* 162 */         buffer.append(inserts[i].toString());
/*     */       } 
/* 164 */       formattedMessage = buffer.toString();
/*     */     } 
/*     */     
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit("com.ibm.mq.jmqi.internal.JmqiResource", "getString(String,String [ ])", formattedMessage);
/*     */     }
/*     */     
/* 171 */     return formattedMessage;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getString(String key, String insert) {
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.entry("com.ibm.mq.jmqi.internal.JmqiResource", "getString(String,String)", new Object[] { key, insert });
/*     */     }
/*     */ 
/*     */     
/* 191 */     String message = key;
/*     */     
/*     */     try {
/* 194 */       message = myMessages.getString(key);
/*     */     }
/* 196 */     catch (NullPointerException e) {
/* 197 */       if (Trace.isOn) {
/* 198 */         Trace.catchBlock("com.ibm.mq.jmqi.internal.JmqiResource", "getString(String,String)", e, 1);
/*     */       
/*     */       }
/*     */     }
/* 202 */     catch (MissingResourceException e) {
/* 203 */       if (Trace.isOn) {
/* 204 */         Trace.catchBlock("com.ibm.mq.jmqi.internal.JmqiResource", "getString(String,String)", e, 2);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 209 */     String traceRet1 = MessageFormat.format(message, new Object[] { insert });
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.exit("com.ibm.mq.jmqi.internal.JmqiResource", "getString(String,String)", traceRet1);
/*     */     }
/*     */     
/* 214 */     return traceRet1;
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
/*     */ 
/*     */   
/*     */   public static String getString(String key) {
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.entry("com.ibm.mq.jmqi.internal.JmqiResource", "getString(String)", new Object[] { key });
/*     */     }
/*     */ 
/*     */     
/* 233 */     String message = key;
/*     */     
/*     */     try {
/* 236 */       message = myMessages.getString(key);
/*     */     }
/* 238 */     catch (NullPointerException npe) {
/* 239 */       if (Trace.isOn) {
/* 240 */         Trace.catchBlock("com.ibm.mq.jmqi.internal.JmqiResource", "getString(String)", npe, 1);
/*     */       }
/*     */     }
/* 243 */     catch (MissingResourceException mre) {
/* 244 */       if (Trace.isOn) {
/* 245 */         Trace.catchBlock("com.ibm.mq.jmqi.internal.JmqiResource", "getString(String)", mre, 2);
/*     */       }
/*     */     } 
/*     */     
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.exit("com.ibm.mq.jmqi.internal.JmqiResource", "getString(String)", message);
/*     */     }
/* 252 */     return message;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\JmqiResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */