/*     */ package com.ibm.msg.client.commonservices.j2se;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*     */ import com.ibm.msg.client.commonservices.provider.nls.CSPNLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.Set;
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
/*     */ public class NLSServices
/*     */   implements CSPNLSServices
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/NLSServices.java";
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.msg.client.commonservices.j2se.NLSServices", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/NLSServices.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   private HashMap<String, ResourceBundle> bundles = new HashMap<>();
/*  61 */   private HashMap<String, ResourceBundle> englishBundles = new HashMap<>();
/*     */   private boolean initialised = false;
/*  63 */   private Object initLock = new Object();
/*     */   
/*  65 */   private Object defaultExceptionLock = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String defaultException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCatalogue(final String name, String namespace, final Class<?> loadingClass) {
/*  82 */     ResourceBundle rb = AccessController.<ResourceBundle>doPrivileged(new PrivilegedAction<ResourceBundle>()
/*     */         {
/*     */           public ResourceBundle run()
/*     */           {
/*  86 */             ResourceBundle traceRet1 = NLSServices.this.addCatalogue(name, loadingClass, Locale.getDefault());
/*  87 */             return traceRet1;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  92 */     if (null != rb) {
/*  93 */       synchronized (this.bundles) {
/*  94 */         this.bundles.put(namespace, rb);
/*     */       } 
/*     */     }
/*     */     
/*  98 */     ResourceBundle english = AccessController.<ResourceBundle>doPrivileged(new PrivilegedAction<ResourceBundle>()
/*     */         {
/*     */           public ResourceBundle run()
/*     */           {
/* 102 */             ResourceBundle traceRet1 = NLSServices.this.addCatalogue(name, loadingClass, Locale.US);
/* 103 */             return traceRet1;
/*     */           }
/*     */         });
/*     */     
/* 107 */     if (null != english) {
/* 108 */       synchronized (this.englishBundles) {
/* 109 */         this.englishBundles.put(namespace, english);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ResourceBundle addCatalogue(String name, Class<?> loadingClass, Locale locale) {
/* 120 */     ClassLoader threadClassloader = Thread.currentThread().getContextClassLoader();
/* 121 */     ClassLoader classClassloader = loadingClass.getClassLoader();
/* 122 */     ResourceBundle rb = null;
/* 123 */     MissingResourceException mreStep1 = null;
/* 124 */     MissingResourceException mreStep2 = null;
/*     */ 
/*     */     
/* 127 */     if (threadClassloader != null) {
/*     */       try {
/* 129 */         rb = ResourceBundle.getBundle(name, locale, threadClassloader);
/*     */       }
/* 131 */       catch (MissingResourceException mre) {
/* 132 */         mreStep1 = mre;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 137 */     if (rb == null && classClassloader != null) {
/*     */       try {
/* 139 */         rb = ResourceBundle.getBundle(name, locale, classClassloader);
/*     */       }
/* 141 */       catch (MissingResourceException mre2) {
/* 142 */         mreStep2 = mre2;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 147 */     if (rb == null) {
/*     */       try {
/* 149 */         rb = ResourceBundle.getBundle(name);
/*     */       }
/* 151 */       catch (MissingResourceException mre3) {
/*     */ 
/*     */ 
/*     */         
/* 155 */         HashMap<String, Object> info = new HashMap<>();
/* 156 */         info.put("Exception", mre3);
/* 157 */         info.put("Exception Step 1", mreStep1);
/* 158 */         info.put("Exception Step 2", mreStep2);
/* 159 */         info.put("ThreadContext Classloader", threadClassloader);
/* 160 */         info.put("Class Classloader", classClassloader);
/* 161 */         Trace.ffst(this, "addCatalogue(String,String)", "XS001001", info, null);
/*     */       } 
/*     */     }
/* 164 */     return rb;
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
/*     */   public String getMessage(String key, Object... inserts) {
/* 178 */     String msg = getOptionalMessage(key);
/* 179 */     if (null == msg) {
/*     */       
/* 181 */       HashMap<String, Object> info = new HashMap<>();
/* 182 */       info.put("key", key);
/* 183 */       if (null != inserts) {
/* 184 */         for (int i = 0; i < inserts.length; i++) {
/* 185 */           info.put("insert" + i, inserts[i]);
/*     */         }
/*     */       }
/* 188 */       Trace.ffst(this, "getMessage(String,Object[])", "XS001002", info, null);
/* 189 */       msg = key + ' ' + Arrays.toString(inserts);
/*     */     }
/*     */     else {
/*     */       
/* 193 */       msg = embedInserts(msg, inserts);
/*     */     } 
/*     */     
/* 196 */     return msg;
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
/*     */   public String getMessage(String key, HashMap<String, ? extends Object> inserts) {
/* 210 */     Object[] insertArray = insertMapToArray(key, inserts);
/* 211 */     String msg = getMessage(key, insertArray);
/* 212 */     return msg;
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
/*     */   public String getExplanation(String key, HashMap<String, ? extends Object> inserts) {
/* 226 */     String explanation = getOptionalMessage(key + ".explanation");
/* 227 */     if (null != explanation) {
/* 228 */       Object[] insertArray = insertMapToArray(key, inserts);
/* 229 */       explanation = embedInserts(explanation, insertArray);
/*     */     } 
/* 231 */     return explanation;
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
/*     */   public String getUserAction(String key, HashMap<String, ? extends Object> inserts) {
/* 245 */     String useraction = getOptionalMessage(key + ".useraction");
/* 246 */     if (null != useraction) {
/* 247 */       Object[] insertArray = insertMapToArray(key, inserts);
/* 248 */       useraction = embedInserts(useraction, insertArray);
/*     */     } 
/* 250 */     return useraction;
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
/*     */   public Exception createException(String messageid, HashMap<String, ? extends Object> inserts) {
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.entry(this, "com.ibm.msg.client.commonservices.j2se.NLSServices", "createException(String,HashMap<String , ? extends Object>)", new Object[] { messageid, inserts });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 274 */     StringBuilder buff = new StringBuilder(messageid);
/* 275 */     buff.append(": ");
/* 276 */     buff.append(getMessage(messageid, inserts));
/* 277 */     String msg = buff.toString();
/*     */     
/* 279 */     String explanation = getExplanation(messageid, inserts);
/* 280 */     String useraction = getUserAction(messageid, inserts);
/*     */     
/* 282 */     String exception = null;
/* 283 */     String excMsg = getOptionalMessage(messageid + ".exception");
/* 284 */     if (null == excMsg) {
/* 285 */       exception = getDefaultException();
/*     */     } else {
/*     */       
/* 288 */       exception = excMsg;
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
/* 299 */     Exception ex = null;
/* 300 */     Class<?> clazz = null;
/*     */     try {
/* 302 */       clazz = CSSystem.dynamicLoadClass(exception, getClass());
/*     */     }
/* 304 */     catch (ClassNotFoundException e1) {
/* 305 */       if (Trace.isOn) {
/* 306 */         Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.NLSServices", "createException(String,HashMap<String , ? extends Object>)", e1, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 312 */       HashMap<String, Object> info = new HashMap<>();
/* 313 */       info.put("exception name", exception);
/* 314 */       info.put("exception caught", e1);
/* 315 */       Trace.ffst(this, "createException(String,HashMap)", "XS001003", info, null);
/*     */ 
/*     */       
/* 318 */       clazz = Exception.class;
/*     */     } 
/*     */     
/*     */     try {
/* 322 */       Constructor<?> constructor = null;
/* 323 */       Object[] parameters = null;
/* 324 */       if (exception.startsWith("com.ibm.")) {
/* 325 */         Class<?>[] signature = new Class[] { String.class, String.class, String.class, String.class, Map.class };
/* 326 */         constructor = clazz.getConstructor(signature);
/* 327 */         parameters = new Object[] { msg, messageid, explanation, useraction, inserts };
/*     */       } else {
/*     */         
/* 330 */         Class<?>[] signature = new Class[] { String.class };
/* 331 */         constructor = clazz.getConstructor(signature);
/* 332 */         parameters = new Object[] { msg };
/*     */       } 
/* 334 */       ex = (Exception)constructor.newInstance(parameters);
/*     */     }
/* 336 */     catch (Exception e) {
/* 337 */       if (Trace.isOn) {
/* 338 */         Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.NLSServices", "createException(String,HashMap<String , ? extends Object>)", e, 2);
/*     */       }
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
/* 352 */       HashMap<String, Object> info = new HashMap<>();
/* 353 */       info.put("message id", messageid);
/* 354 */       info.put("exception", e);
/* 355 */       info.put("exception name", exception);
/* 356 */       if (null != inserts) {
/* 357 */         info.putAll(inserts);
/*     */       }
/* 359 */       Trace.ffst(this, "createException(String,HashMap)", "XS001004", info, null);
/*     */     } 
/*     */     
/* 362 */     if (ex == null) {
/* 363 */       ex = new Exception("Unable to create exception - check FFDC output for information");
/*     */     }
/*     */     
/* 366 */     if (Trace.isOn) {
/* 367 */       Trace.exit(this, "com.ibm.msg.client.commonservices.j2se.NLSServices", "createException(String,HashMap<String , ? extends Object>)", ex);
/*     */     }
/*     */     
/* 370 */     return ex;
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
/*     */   private String getDefaultException() {
/* 382 */     synchronized (this.defaultExceptionLock) {
/* 383 */       if (this.defaultException == null) {
/* 384 */         StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
/* 385 */         for (StackTraceElement stackTraceElement : stackTraceElements) {
/* 386 */           String className = stackTraceElement.getClassName();
/* 387 */           if (className.startsWith("com.ibm") && className.contains("jakarta")) {
/* 388 */             this.defaultException = "com.ibm.msg.client.jakarta.jms.DetailedJMSException";
/*     */             break;
/*     */           } 
/*     */         } 
/* 392 */         if (this.defaultException == null) {
/* 393 */           this.defaultException = "com.ibm.msg.client.jms.DetailedJMSException";
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 398 */     return this.defaultException;
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
/*     */   private void initialise() {
/* 412 */     synchronized (this.initLock) {
/* 413 */       if (!this.initialised) {
/* 414 */         addCatalogue("com.ibm.msg.client.commonservices.resources.JMSCS_MessageResourceBundle", "JMSCS", 
/* 415 */             getClass());
/* 416 */         this.initialised = true;
/*     */       } 
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
/*     */   private String getOptionalMessage(String key) {
/* 433 */     initialise();
/*     */     
/* 435 */     String msg = getOptionalMessage(key, this.bundles);
/* 436 */     if (msg != null) {
/* 437 */       return msg;
/*     */     }
/* 439 */     String traceRet1 = getOptionalMessage(key, this.englishBundles);
/* 440 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getOptionalMessage(String key, HashMap<String, ResourceBundle> searchBundles) {
/* 445 */     String msg = null;
/* 446 */     synchronized (searchBundles) {
/* 447 */       String namespace = null;
/* 448 */       ResourceBundle bundle = null;
/*     */       
/* 450 */       Set<Map.Entry<String, ResourceBundle>> entries = searchBundles.entrySet();
/* 451 */       for (Map.Entry<String, ResourceBundle> entry : entries) {
/* 452 */         namespace = entry.getKey();
/* 453 */         bundle = entry.getValue();
/*     */         
/* 455 */         if (key.startsWith(namespace)) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */       
/* 460 */       if (null == namespace) {
/*     */         
/* 462 */         HashMap<String, Object> info = new HashMap<>();
/* 463 */         info.put("Namespace", null);
/* 464 */         Trace.ffst(this, "getOptionalMessage(String)", "XS001005", info, null);
/*     */       } 
/*     */       
/* 467 */       if (bundle != null) {
/*     */         
/*     */         try {
/*     */ 
/*     */           
/* 472 */           Enumeration<String> keys = bundle.getKeys();
/* 473 */           while (keys.hasMoreElements()) {
/* 474 */             if (key.equals(keys.nextElement())) {
/* 475 */               msg = bundle.getString(key);
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/* 480 */         } catch (Exception e) {
/* 481 */           if (Trace.isOn) {
/* 482 */             Trace.catchBlock(this, "com.ibm.msg.client.commonservices.j2se.NLSServices", "getOptionalMessage(String,HashMap)", e);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 492 */     return msg;
/*     */   }
/*     */ 
/*     */   
/*     */   private String embedInserts(String message, Object[] inserts) {
/* 497 */     if (null == inserts) {
/* 498 */       return message;
/*     */     }
/*     */     
/* 501 */     MessageFormat msgFormatter = new MessageFormat(message);
/* 502 */     StringBuffer msgbuff = new StringBuffer();
/* 503 */     msgbuff = msgFormatter.format(inserts, msgbuff, (FieldPosition)null);
/* 504 */     String result = msgbuff.toString();
/* 505 */     return result;
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
/*     */   private Object[] insertMapToArray(String messageid, HashMap<String, ? extends Object> insertsP) {
/* 520 */     HashMap<String, ? extends Object> inserts = insertsP;
/* 521 */     if (inserts != null && inserts.size() == 0)
/*     */     {
/* 523 */       inserts = null;
/*     */     }
/* 525 */     String insertConstants = getOptionalMessage(messageid + ".inserts");
/* 526 */     if ((null == inserts && null != insertConstants) || (null != inserts && null == insertConstants)) {
/*     */ 
/*     */       
/* 529 */       HashMap<String, Object> info = new HashMap<>();
/* 530 */       info.put("message id", messageid);
/* 531 */       if (null != inserts) {
/* 532 */         info.putAll(inserts);
/*     */       }
/* 534 */       if (null != insertConstants) {
/* 535 */         info.put("insert constants", insertConstants);
/*     */       }
/* 537 */       Trace.ffst(this, "insertMapToArray(String,HashMap)", "XS001006", info, null);
/*     */     } 
/*     */     
/* 540 */     Object[] insertArray = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 545 */     if (null != inserts && null != insertConstants) {
/* 546 */       String[] insertStrings = insertConstants.split(",");
/* 547 */       insertArray = new Object[insertStrings.length];
/*     */       
/* 549 */       for (int idx = 0; idx < insertStrings.length; idx++) {
/* 550 */         String key = insertStrings[idx].trim();
/* 551 */         if (!inserts.containsKey(key)) {
/*     */           
/* 553 */           HashMap<String, Object> info = new HashMap<>();
/* 554 */           info.put("message id", messageid);
/* 555 */           info.put("index", Integer.valueOf(idx));
/* 556 */           info.putAll(inserts);
/* 557 */           info.put("insert constants", insertConstants);
/* 558 */           Trace.ffst(this, "insertMapToArray(String,HashMap)", "XS001007", info, null);
/*     */         } 
/*     */         
/* 561 */         insertArray[idx] = inserts.get(key);
/*     */       } 
/* 563 */       return insertArray;
/*     */     } 
/* 565 */     return insertArray;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\NLSServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */