/*     */ package com.ibm.msg.client.commonservices.nls;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.provider.nls.CSPNLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.HashMap;
/*     */ import java.util.MissingResourceException;
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
/*     */ public class PINLSServices
/*     */   implements CSPNLSServices
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/nls/PINLSServices.java";
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.msg.client.commonservices.nls.PINLSServices", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/nls/PINLSServices.java");
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
/*     */   public void addCatalogue(String bundlefilename, String namespace, Class<?> loadingClass) throws MissingResourceException {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.entry(this, "com.ibm.msg.client.commonservices.nls.PINLSServices", "addCatalogue(String,String,Class<?>)", new Object[] { bundlefilename, namespace, loadingClass });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.exit(this, "com.ibm.msg.client.commonservices.nls.PINLSServices", "addCatalogue(String,String,Class<?>)");
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
/*     */   public String getMessage(String key, Object... inserts) {
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.entry(this, "com.ibm.msg.client.commonservices.nls.PINLSServices", "getMessage(String,Object...)", new Object[] { key, inserts });
/*     */     }
/*     */ 
/*     */     
/*  78 */     StringBuffer retVal = new StringBuffer(key);
/*     */     
/*  80 */     if (inserts != null && inserts.length > 0) {
/*  81 */       for (int i = 0; i < inserts.length; i++) {
/*  82 */         retVal.append(", " + inserts[i].toString());
/*     */       }
/*     */     }
/*     */     
/*  86 */     String traceRet1 = retVal.toString();
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.exit(this, "com.ibm.msg.client.commonservices.nls.PINLSServices", "getMessage(String,Object...)", traceRet1);
/*     */     }
/*     */     
/*  91 */     return traceRet1;
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
/*     */   public Exception createException(String messageId, HashMap<String, ? extends Object> inserts) {
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.entry(this, "com.ibm.msg.client.commonservices.nls.PINLSServices", "createException(String,HashMap<String , ? extends Object>)", new Object[] { messageId, inserts });
/*     */     }
/*     */ 
/*     */     
/* 112 */     Trace.ffst(this, "createException(String,HashMap)", "XC003001", null, null);
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.msg.client.commonservices.nls.PINLSServices", "createException(String,HashMap<String , ? extends Object>)", null);
/*     */     }
/*     */     
/* 117 */     return null;
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
/*     */   public String getMessage(String key, HashMap<String, ? extends Object> inserts) {
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.entry(this, "com.ibm.msg.client.commonservices.nls.PINLSServices", "getMessage(String,HashMap<String , ? extends Object>)", new Object[] { key, inserts });
/*     */     }
/*     */     
/* 136 */     Trace.ffst(this, "getMessage(String,HashMap)", "XC003002", null, null);
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.exit(this, "com.ibm.msg.client.commonservices.nls.PINLSServices", "getMessage(String,HashMap<String , ? extends Object>)", null);
/*     */     }
/*     */     
/* 141 */     return null;
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
/*     */   public String getExplanation(String key, HashMap<String, ? extends Object> inserts) {
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.entry(this, "com.ibm.msg.client.commonservices.nls.PINLSServices", "getExplanation(String,HashMap<String , ? extends Object>)", new Object[] { key, inserts });
/*     */     }
/*     */     
/* 160 */     Trace.ffst(this, "getExplanation(String,HashMap)", "XC003003", null, null);
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.exit(this, "com.ibm.msg.client.commonservices.nls.PINLSServices", "getExplanation(String,HashMap<String , ? extends Object>)", null);
/*     */     }
/*     */     
/* 165 */     return null;
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
/*     */   public String getUserAction(String key, HashMap<String, ? extends Object> inserts) {
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.entry(this, "com.ibm.msg.client.commonservices.nls.PINLSServices", "getUserAction(String,HashMap<String , ? extends Object>)", new Object[] { key, inserts });
/*     */     }
/*     */     
/* 184 */     Trace.ffst(this, "getUserAction(String,HashMap)", "XC003004", null, null);
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.exit(this, "com.ibm.msg.client.commonservices.nls.PINLSServices", "getUserAction(String,HashMap<String , ? extends Object>)", null);
/*     */     }
/*     */     
/* 189 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\nls\PINLSServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */