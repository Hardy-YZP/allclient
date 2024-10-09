/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.Log.Log;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsConvertableException;
/*     */ import java.util.HashMap;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.JMSRuntimeException;
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
/*     */ public class JmsErrorUtils
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsErrorUtils.java";
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.msg.client.jms.internal.JmsErrorUtils", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsErrorUtils.java");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsErrorUtils", "static()");
/*     */     }
/*  58 */     NLSServices.addCatalogue("com.ibm.msg.client.jms.internal.resources.JMSCC_MessageResourceBundle", "JMSCC", JmsErrorUtils.class);
/*     */ 
/*     */     
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsErrorUtils", "static()");
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
/*     */   public static String getMessage(String key, String insert1) {
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsErrorUtils", "getMessage(String,String)", new Object[] { key, insert1 });
/*     */     }
/*     */     
/*  80 */     String traceRet1 = NLSServices.getMessage(key, new Object[] { insert1 });
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsErrorUtils", "getMessage(String,String)", traceRet1);
/*     */     }
/*     */     
/*  85 */     return traceRet1;
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
/*     */   public static String getMessage(String key, HashMap<String, ? extends Object> inserts) {
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsErrorUtils", "getMessage(String,HashMap<String , ? extends Object>)", new Object[] { key, inserts });
/*     */     }
/*     */     
/* 102 */     String traceRet1 = NLSServices.getMessage(key, inserts);
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsErrorUtils", "getMessage(String,HashMap<String , ? extends Object>)", traceRet1);
/*     */     }
/*     */     
/* 107 */     return traceRet1;
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
/*     */   public static Exception createException(String messageId, HashMap<String, Object> inserts) {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsErrorUtils", "createException(String,HashMap<String , Object>)", new Object[] { messageId, inserts });
/*     */     }
/*     */     
/* 126 */     Exception traceRet1 = NLSServices.createException(messageId, inserts);
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsErrorUtils", "createException(String,HashMap<String , Object>)", traceRet1);
/*     */     }
/*     */     
/* 131 */     return traceRet1;
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
/*     */   public static void log(Object parentClass, String methodSignature, String key, HashMap<String, Object> inserts) {
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsErrorUtils", "log(Object,String,String,HashMap<String , Object>)", new Object[] { parentClass, methodSignature, key, inserts });
/*     */     }
/*     */ 
/*     */     
/* 151 */     Log.log(parentClass, methodSignature, key, inserts);
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsErrorUtils", "log(Object,String,String,HashMap<String , Object>)");
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
/*     */   public static JMSRuntimeException convertJMSException(JMSException je) throws JMSRuntimeException {
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsErrorUtils", "convertJMSException(JMSException)", new Object[] { je });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 172 */       if (je instanceof JmsConvertableException) {
/* 173 */         JMSRuntimeException traceRet1 = ((JmsConvertableException)je).getUnchecked();
/* 174 */         if (Trace.isOn) {
/* 175 */           Trace.throwing("com.ibm.msg.client.jms.internal.JmsErrorUtils", "convertJMSException(JMSException)", (Throwable)traceRet1, 1);
/*     */         }
/*     */         
/* 178 */         throw traceRet1;
/*     */       } 
/*     */ 
/*     */       
/* 182 */       JMSRuntimeException jre = new JMSRuntimeException(je.getLocalizedMessage(), je.getErrorCode(), (Throwable)je);
/* 183 */       Trace.throwing("com.ibm.msg.client.jms.internal.JmsErrorUtils", "handleJMSException(JMSException)", (Throwable)jre);
/* 184 */       if (Trace.isOn) {
/* 185 */         Trace.throwing("com.ibm.msg.client.jms.internal.JmsErrorUtils", "convertJMSException(JMSException)", (Throwable)jre, 2);
/*     */       }
/*     */       
/* 188 */       throw jre;
/*     */     } finally {
/*     */       
/* 191 */       if (Trace.isOn)
/* 192 */         Trace.finallyBlock("com.ibm.msg.client.jms.internal.JmsErrorUtils", "convertJMSException(JMSException)"); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsErrorUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */