/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.disthub2.impl.client.BaseConfig;
/*     */ import com.ibm.disthub2.impl.client.SessionConfig;
/*     */ import com.ibm.disthub2.spi.ClientTranslate;
/*     */ import com.ibm.disthub2.spi.ExceptionBuilder;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.compat.jms.internal.services.MQJMSTranslator;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.jms.JMSException;
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
/*     */ public final class ConfigEnvironment
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/ConfigEnvironment.java";
/*     */   private static ResourceBundle logMsgCat;
/*     */   private static final String MQJMS_LOG_MSGCAT_DEFAULT_FILE = "MQJMS_MessageResourceBundle";
/*     */   private static final String MQJMS_LOG_MSGCAT_PATH = "com.ibm.msg.client.wmq.compat.jms.internal.services.resources.";
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/ConfigEnvironment.java");
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
/*     */ 
/*     */ 
/*     */     
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "static()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  77 */     String bundle = "com.ibm.msg.client.wmq.compat.jms.internal.services.resources.MQJMS_MessageResourceBundle";
/*     */     
/*     */     try {
/*  80 */       logMsgCat = ResourceBundle.getBundle(bundle);
/*     */     }
/*  82 */     catch (MissingResourceException e) {
/*  83 */       if (Trace.isOn) {
/*  84 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "static()", e);
/*     */       }
/*     */       
/*  87 */       System.err.println("ERROR: unable to locate resource bundle '" + bundle + "'. Probable installation problem.");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  92 */     BaseConfig.initialize(SessionConfig.class);
/*     */ 
/*     */     
/*  95 */     MQJMSTranslator msgTranslator = new MQJMSTranslator();
/*  96 */     ExceptionBuilder.setTranslator((ClientTranslate)msgTranslator);
/*     */     
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getErrorMessage(String key) {
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getErrorMessage(String)", new Object[] { key });
/*     */     }
/*     */     
/* 114 */     StringBuffer result = new StringBuffer(key);
/*     */     
/* 116 */     result.append(": ");
/*     */     
/*     */     try {
/* 119 */       result.append(logMsgCat.getString(key));
/*     */     }
/* 121 */     catch (MissingResourceException e) {
/* 122 */       if (Trace.isOn) {
/* 123 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getErrorMessage(String)", e);
/*     */       }
/*     */       
/* 126 */       result.append("<message not found>");
/*     */     } 
/*     */     
/* 129 */     String traceRet1 = result.toString();
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getErrorMessage(String)", traceRet1);
/*     */     }
/*     */     
/* 134 */     return traceRet1;
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
/*     */   public static String getErrorMessage(String key, Object insert) {
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getErrorMessage(String,Object)", new Object[] { key, insert });
/*     */     }
/*     */     
/* 150 */     StringBuffer result = new StringBuffer(key);
/*     */     
/* 152 */     result.append(": ");
/*     */     
/*     */     try {
/* 155 */       String msg = logMsgCat.getString(key);
/* 156 */       result.append(MessageFormat.format(msg, new Object[] { insert }));
/*     */     }
/* 158 */     catch (MissingResourceException e) {
/* 159 */       if (Trace.isOn) {
/* 160 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getErrorMessage(String,Object)", e);
/*     */       }
/*     */       
/* 163 */       result.append("<message not found>");
/*     */     } 
/*     */     
/* 166 */     String traceRet1 = result.toString();
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getErrorMessage(String,Object)", traceRet1);
/*     */     }
/*     */     
/* 171 */     return traceRet1;
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
/*     */   public static String getErrorMessage(String key, Object insert1, Object insert2) {
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getErrorMessage(String,Object,Object)", new Object[] { key, insert1, insert2 });
/*     */     }
/*     */     
/* 188 */     StringBuffer result = new StringBuffer(key);
/*     */     
/* 190 */     result.append(": ");
/*     */     
/*     */     try {
/* 193 */       String msg = logMsgCat.getString(key);
/* 194 */       result.append(MessageFormat.format(msg, new Object[] { insert1, insert2 }));
/*     */     }
/* 196 */     catch (MissingResourceException e) {
/* 197 */       if (Trace.isOn) {
/* 198 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getErrorMessage(String,Object,Object)", e);
/*     */       }
/*     */       
/* 201 */       result.append("<message not found>");
/*     */     } 
/*     */     
/* 204 */     String traceRet1 = result.toString();
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getErrorMessage(String,Object,Object)", traceRet1);
/*     */     }
/*     */     
/* 209 */     return traceRet1;
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
/*     */   public static String getErrorMessage(String key, Object insert1, Object insert2, Object insert3) {
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getErrorMessage(String,Object,Object,Object)", new Object[] { key, insert1, insert2, insert3 });
/*     */     }
/*     */ 
/*     */     
/* 228 */     StringBuffer result = new StringBuffer(key);
/*     */     
/* 230 */     result.append(": ");
/*     */     
/*     */     try {
/* 233 */       String msg = logMsgCat.getString(key);
/* 234 */       result.append(MessageFormat.format(msg, new Object[] { insert1, insert2, insert3 }));
/*     */     }
/* 236 */     catch (MissingResourceException e) {
/* 237 */       if (Trace.isOn) {
/* 238 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getErrorMessage(String,Object,Object,Object)", e);
/*     */       }
/*     */       
/* 241 */       result.append("<message not found>");
/*     */     } 
/*     */     
/* 244 */     String traceRet1 = result.toString();
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getErrorMessage(String,Object,Object,Object)", traceRet1);
/*     */     }
/*     */     
/* 249 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getMessage(String key) {
/*     */     String result;
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getMessage(String)", new Object[] { key });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 267 */       result = logMsgCat.getString(key);
/*     */     }
/* 269 */     catch (MissingResourceException e) {
/* 270 */       if (Trace.isOn) {
/* 271 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getMessage(String)", e);
/*     */       }
/*     */       
/* 274 */       result = "<message not found>";
/*     */     } 
/*     */     
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getMessage(String)", result);
/*     */     }
/*     */     
/* 281 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getMessage(String key, Object insert) {
/*     */     String result;
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getMessage(String,Object)", new Object[] { key, insert });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 300 */       String msg = logMsgCat.getString(key);
/* 301 */       result = MessageFormat.format(msg, new Object[] { insert });
/*     */     }
/* 303 */     catch (MissingResourceException e) {
/* 304 */       if (Trace.isOn) {
/* 305 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getMessage(String,Object)", e);
/*     */       }
/*     */       
/* 308 */       result = "<message not found>";
/*     */     } 
/*     */     
/* 311 */     if (Trace.isOn) {
/* 312 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getMessage(String,Object)", result);
/*     */     }
/*     */     
/* 315 */     return result;
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
/*     */   public static String getMessage(String key, Object insert1, Object insert2) {
/*     */     String result;
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getMessage(String,Object,Object)", new Object[] { key, insert1, insert2 });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 335 */       String msg = logMsgCat.getString(key);
/* 336 */       result = MessageFormat.format(msg, new Object[] { insert1, insert2 });
/*     */     }
/* 338 */     catch (MissingResourceException e) {
/* 339 */       if (Trace.isOn) {
/* 340 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getMessage(String,Object,Object)", e);
/*     */       }
/*     */       
/* 343 */       result = "<message not found>";
/*     */     } 
/*     */     
/* 346 */     if (Trace.isOn) {
/* 347 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "getMessage(String,Object,Object)", result);
/*     */     }
/*     */     
/* 350 */     return result;
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
/*     */   public static JMSException newException(String key) {
/* 368 */     if (Trace.isOn) {
/* 369 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "newException(String)", new Object[] { key });
/*     */     }
/*     */ 
/*     */     
/* 373 */     JMSException traceRet1 = new MyJMSException(getErrorMessage(key), key);
/*     */     
/* 375 */     if (Trace.isOn) {
/* 376 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "newException(String)", traceRet1);
/*     */     }
/*     */     
/* 379 */     return traceRet1;
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
/*     */   public static JMSException newException(String key, Object insert) {
/* 398 */     if (Trace.isOn) {
/* 399 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "newException(String,Object)", new Object[] { key, insert });
/*     */     }
/*     */ 
/*     */     
/* 403 */     JMSException traceRet1 = new MyJMSException(getErrorMessage(key, insert), key);
/*     */     
/* 405 */     if (Trace.isOn) {
/* 406 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "newException(String,Object)", traceRet1);
/*     */     }
/*     */     
/* 409 */     return traceRet1;
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
/*     */   public static JMSException newException(String key, Object insert1, Object insert2) {
/* 429 */     if (Trace.isOn) {
/* 430 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "newException(String,Object,Object)", new Object[] { key, insert1, insert2 });
/*     */     }
/*     */     
/* 433 */     JMSException traceRet1 = new MyJMSException(getErrorMessage(key, insert1, insert2), key);
/*     */ 
/*     */     
/* 436 */     if (Trace.isOn) {
/* 437 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.ConfigEnvironment", "newException(String,Object,Object)", traceRet1);
/*     */     }
/*     */     
/* 440 */     return traceRet1;
/*     */   }
/*     */   
/*     */   private static class MyJMSException
/*     */     extends JMSException
/*     */   {
/*     */     private static final long serialVersionUID = 2990027238128327166L;
/*     */     
/*     */     MyJMSException(String reason, String errorCode) {
/* 449 */       super(reason, errorCode);
/* 450 */       if (Trace.isOn) {
/* 451 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MyJMSException", "<init>(String,String)", new Object[] { reason, errorCode });
/*     */       }
/*     */       
/* 454 */       if (Trace.isOn) {
/* 455 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MyJMSException", "<init>(String,String)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setLinkedException(Exception exception) {
/* 463 */       if (Trace.isOn) {
/* 464 */         Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MyJMSException", "setLinkedException(Exception)", "setter", exception);
/*     */       }
/*     */       
/* 467 */       super.setLinkedException(exception);
/*     */       try {
/* 469 */         initCause(exception);
/*     */       }
/* 471 */       catch (IllegalStateException ise) {
/* 472 */         if (Trace.isOn) {
/* 473 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MyJMSException", "setLinkedException(Exception)", ise);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 482 */       if (Trace.isOn) {
/* 483 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MyJMSException", "toString()");
/*     */       }
/*     */       
/* 486 */       String traceRet1 = getClass().getSuperclass().getName() + ": " + getMessage();
/* 487 */       if (Trace.isOn) {
/* 488 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MyJMSException", "toString()", traceRet1);
/*     */       }
/*     */       
/* 491 */       return traceRet1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\ConfigEnvironment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */