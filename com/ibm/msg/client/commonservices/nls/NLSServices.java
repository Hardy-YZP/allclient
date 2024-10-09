/*     */ package com.ibm.msg.client.commonservices.nls;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.CSIException;
/*     */ import com.ibm.msg.client.commonservices.CSIListener;
/*     */ import com.ibm.msg.client.commonservices.CommonServices;
/*     */ import com.ibm.msg.client.commonservices.provider.nls.CSPNLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.security.AccessControlException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
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
/*     */ public final class NLSServices
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/nls/NLSServices.java";
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.msg.client.commonservices.nls.NLSServices", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices/src/com/ibm/msg/client/commonservices/nls/NLSServices.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   private static CSPNLSServices functionalNLSServices = null;
/*     */   private static boolean initialized = false;
/*     */   private static boolean listening = false;
/*     */   
/*     */   static {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.entry("com.ibm.msg.client.commonservices.nls.NLSServices", "static()");
/*     */     }
/*     */     try {
/*  62 */       initialize();
/*     */     }
/*  64 */     catch (CSIException csie) {
/*  65 */       if (Trace.isOn) {
/*  66 */         Trace.catchBlock("com.ibm.msg.client.commonservices.nls.NLSServices", "static()", (Throwable)csie);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.exit("com.ibm.msg.client.commonservices.nls.NLSServices", "static()");
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
/*     */   public static void addCatalogue(String bundlefilename, String namespace) throws MissingResourceException {
/*  88 */     functionalNLSServices.addCatalogue(bundlefilename, namespace, NLSServices.class);
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
/*     */   public static void addCatalogue(String bundlefilename, String namespace, Class<?> loadingClass) throws MissingResourceException {
/* 103 */     functionalNLSServices.addCatalogue(bundlefilename, namespace, loadingClass);
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
/*     */   public static String getMessage(String key) {
/* 115 */     Object[] inserts = null;
/* 116 */     String msg = functionalNLSServices.getMessage(key, inserts);
/* 117 */     return msg;
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
/*     */   public static String getMessage(String key, Object... inserts) {
/* 129 */     String msg = functionalNLSServices.getMessage(key, inserts);
/* 130 */     return msg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initialize() throws CSIException {
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.entry("com.ibm.msg.client.commonservices.nls.NLSServices", "initialize()");
/*     */     }
/*     */ 
/*     */     
/* 142 */     if (initialized) {
/* 143 */       if (Trace.isOn) {
/* 144 */         Trace.exit("com.ibm.msg.client.commonservices.nls.NLSServices", "initialize()", 1);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*     */     try {
/* 151 */       functionalNLSServices = CommonServices.getNLSServices();
/* 152 */       initialized = true;
/*     */     }
/* 154 */     catch (CSIException csie) {
/* 155 */       if (Trace.isOn) {
/* 156 */         Trace.data("com.ibm.msg.client.commonservices.nls.NLSServices", "initialize()", "Caught expected exception", csie);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 161 */       if (!listening) {
/*     */ 
/*     */         
/* 164 */         functionalNLSServices = new PINLSServices();
/*     */ 
/*     */ 
/*     */         
/* 168 */         CSIListener listener = new CSIListener()
/*     */           {
/*     */             public void onCSIInitialize()
/*     */             {
/* 172 */               if (Trace.isOn) {
/* 173 */                 Trace.entry(this, "com.ibm.msg.client.commonservices.nls.NLSServices", "onCSIInitialize()");
/*     */               }
/*     */               
/*     */               try {
/* 177 */                 NLSServices.initialize();
/* 178 */                 NLSServices.listening = false;
/* 179 */                 CommonServices.removeCSIListener(this);
/*     */               }
/* 181 */               catch (CSIException csie2) {
/* 182 */                 if (Trace.isOn) {
/* 183 */                   Trace.catchBlock(this, "com.ibm.msg.client.commonservices.nls.null", "onCSIInitialize()", (Throwable)csie2);
/*     */                 }
/*     */ 
/*     */                 
/* 187 */                 HashMap<String, CSIException> hash = new HashMap<>();
/* 188 */                 hash.put("Exception", csie2);
/* 189 */                 Trace.ffst(this, "onCSIInitialize", "XC002001", hash, CSIException.class);
/*     */               } 
/* 191 */               if (Trace.isOn) {
/* 192 */                 Trace.exit(this, "com.ibm.msg.client.commonservices.nls.null", "onCSIInitialize()");
/*     */               }
/*     */             }
/*     */           };
/*     */ 
/*     */ 
/*     */         
/* 199 */         CommonServices.addCSIListener(listener);
/* 200 */         listening = true;
/*     */       } 
/*     */       
/* 203 */       if (Trace.isOn) {
/* 204 */         Trace.throwing("com.ibm.msg.client.commonservices.nls.NLSServices", "initialize()", (Throwable)csie);
/*     */       }
/* 206 */       throw csie;
/*     */     } 
/*     */     
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.exit("com.ibm.msg.client.commonservices.nls.NLSServices", "initialize()", 2);
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
/*     */   public static Exception createException(String messageid, HashMap<String, ? extends Object> inserts) {
/* 226 */     Exception ex = functionalNLSServices.createException(messageid, inserts);
/* 227 */     return ex;
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
/*     */   public static String getMessage(String key, HashMap<String, ? extends Object> inserts) {
/* 239 */     String msg = functionalNLSServices.getMessage(key, inserts);
/* 240 */     return msg;
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
/*     */   public static String getExplanation(String key, HashMap<String, ? extends Object> inserts) {
/* 252 */     String explanation = functionalNLSServices.getExplanation(key, inserts);
/* 253 */     return explanation;
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
/*     */   public static String getUserAction(String key, HashMap<String, ? extends Object> inserts) {
/* 265 */     String userAction = functionalNLSServices.getUserAction(key, inserts);
/* 266 */     return userAction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isWindowsLatinCodepage() {
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.entry("com.ibm.msg.client.commonservices.nls.NLSServices", "isWindowsLatinCodepage()");
/*     */     }
/*     */     
/* 281 */     Locale loc = Locale.getDefault();
/* 282 */     String language = loc.getLanguage();
/* 283 */     String osName = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*     */         {
/*     */           public String run()
/*     */           {
/* 287 */             if (Trace.isOn) {
/* 288 */               Trace.entry(this, "com.ibm.msg.client.commonservices.nls.NLSServices", "run()");
/*     */             }
/*     */             try {
/* 291 */               String traceRet1 = System.getProperty("os.name");
/* 292 */               if (Trace.isOn) {
/* 293 */                 Trace.exit(this, "com.ibm.msg.client.commonservices.nls.null", "run()", traceRet1, 1);
/*     */               }
/* 295 */               return traceRet1;
/*     */             }
/* 297 */             catch (AccessControlException ace) {
/* 298 */               if (Trace.isOn) {
/* 299 */                 Trace.catchBlock(this, "com.ibm.msg.client.commonservices.nls.null", "run()", ace);
/*     */               }
/* 301 */               if (Trace.isOn) {
/* 302 */                 Trace.exit(this, "com.ibm.msg.client.commonservices.nls.null", "run()", "", 2);
/*     */               }
/* 304 */               return "";
/*     */             } 
/*     */           }
/*     */         });
/* 308 */     if (osName != null && osName.indexOf("Windows") != -1 && (language
/* 309 */       .equals("en") || language.equals("fr") || language.equals("de") || language
/* 310 */       .equals("es") || language.equals("it") || language.equals("cs") || language
/* 311 */       .equals("hu") || language.equals("pl") || language.equals("ru") || language
/* 312 */       .equals("pt"))) {
/* 313 */       if (Trace.isOn) {
/* 314 */         Trace.exit("com.ibm.msg.client.commonservices.nls.NLSServices", "isWindowsLatinCodepage()", 
/* 315 */             Boolean.valueOf(true), 1);
/*     */       }
/* 317 */       return true;
/*     */     } 
/* 319 */     if (Trace.isOn) {
/* 320 */       Trace.exit("com.ibm.msg.client.commonservices.nls.NLSServices", "isWindowsLatinCodepage()", 
/* 321 */           Boolean.valueOf(false), 2);
/*     */     }
/* 323 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\nls\NLSServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */