/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.Properties;
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
/*     */ public class MQDataException
/*     */   extends Exception
/*     */ {
/*     */   private static final long serialVersionUID = -2769117710955610926L;
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQDataException.java";
/*     */   protected static final String MQJE001 = "MQJE001";
/*     */   protected static final String MQJE001b = "MQJE001b";
/*     */   
/*     */   static {
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.data("com.ibm.mq.headers.MQDataException", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQDataException.java");
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
/*     */   
/* 116 */   public static volatile OutputStreamWriter log = null;
/*     */ 
/*     */   
/* 119 */   private static ResourceBundle exceptionMessages = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int completionCode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int reasonCode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public transient Object exceptionSource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   private String ostrMessage = null;
/*     */ 
/*     */   
/*     */   private final String msgId;
/*     */   
/*     */   private static boolean isInitialised = false;
/*     */   
/* 154 */   private static final Object initialiseLock = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initialiseBaseJavaClasses() {
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.entry(this, "com.ibm.mq.headers.MQDataException", "initialiseBaseJavaClasses()");
/*     */     }
/*     */     
/* 165 */     if (!isInitialised) {
/* 166 */       synchronized (initialiseLock) {
/* 167 */         if (!isInitialised) {
/* 168 */           initialiseBaseJavaClasses_inner();
/* 169 */           isInitialised = true;
/*     */         } 
/*     */       } 
/*     */     }
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.exit(this, "com.ibm.mq.headers.MQDataException", "initialiseBaseJavaClasses()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void initialiseBaseJavaClasses_inner() {
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.entry(this, "com.ibm.mq.headers.MQDataException", "initialiseBaseJavaClasses_inner()");
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 186 */       exceptionMessages = ResourceBundle.getBundle("mqji");
/*     */     }
/* 188 */     catch (MissingResourceException ex) {
/* 189 */       if (Trace.isOn) {
/* 190 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQDataException", "initialiseBaseJavaClasses_inner()", ex);
/*     */       }
/*     */ 
/*     */       
/* 194 */       Properties p = System.getProperties();
/* 195 */       ClassLoader cl = Thread.currentThread().getContextClassLoader();
/* 196 */       System.err.println("Unable to load message properties file - mqji(xx).properties");
/* 197 */       System.err.println("CLASSPATH           = " + p.getProperty("java.class.path"));
/* 198 */       System.err.println("THREADCLASSLOADER   = " + cl.toString());
/*     */     } 
/*     */     
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.exit(this, "com.ibm.mq.headers.MQDataException", "initialiseBaseJavaClasses_inner()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCompCode() {
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.data(this, "com.ibm.mq.headers.MQDataException", "getCompCode()", "getter", 
/* 215 */           Integer.valueOf(this.completionCode));
/*     */     }
/* 217 */     return this.completionCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getErrorCode() {
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.data(this, "com.ibm.mq.headers.MQDataException", "getErrorCode()", "getter", this.msgId);
/*     */     }
/* 229 */     return this.msgId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReason() {
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.data(this, "com.ibm.mq.headers.MQDataException", "getReason()", "getter", 
/* 240 */           Integer.valueOf(this.reasonCode));
/*     */     }
/* 242 */     return this.reasonCode;
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
/*     */   public MQDataException(int completionCode, int reasonCode, Object source) {
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.entry(this, "com.ibm.mq.headers.MQDataException", "<init>(int,int,Object)", new Object[] {
/* 258 */             Integer.valueOf(completionCode), Integer.valueOf(reasonCode), source
/*     */           });
/*     */     }
/* 261 */     initialiseBaseJavaClasses();
/*     */     
/* 263 */     this.completionCode = completionCode;
/* 264 */     this.reasonCode = reasonCode;
/* 265 */     this.exceptionSource = source;
/*     */     
/* 267 */     this.msgId = "";
/*     */ 
/*     */     
/* 270 */     if (source instanceof Throwable) {
/* 271 */       initCause((Throwable)source);
/*     */     }
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.exit(this, "com.ibm.mq.headers.MQDataException", "<init>(int,int,Object)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MQDataException getMQDataException(Exception e) {
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.entry("com.ibm.mq.headers.MQDataException", "getMQDataException(Exception)", new Object[] { e });
/*     */     }
/*     */ 
/*     */     
/* 290 */     if (e instanceof MQDataException) {
/* 291 */       if (Trace.isOn) {
/* 292 */         Trace.exit("com.ibm.mq.headers.MQDataException", "getMQDataException(Exception)", e, 0);
/*     */       }
/*     */ 
/*     */       
/* 296 */       return (MQDataException)e;
/*     */     } 
/*     */     
/*     */     try {
/* 300 */       Class<?> MQExceptionWrapperClass = Class.forName("com.ibm.mq.headers.MQExceptionWrapper");
/* 301 */       Constructor<?> MQExceptionWrapperConstructor = MQExceptionWrapperClass.getConstructor(new Class[] { Exception.class });
/*     */       
/* 303 */       MQDataException traceRet1 = (MQDataException)MQExceptionWrapperConstructor.newInstance(new Object[] { e });
/* 304 */       if (Trace.isOn) {
/* 305 */         Trace.exit("com.ibm.mq.headers.MQDataException", "getMQDataException(Exception)", traceRet1, 1);
/*     */       }
/*     */       
/* 308 */       return traceRet1;
/*     */     }
/* 310 */     catch (RuntimeException rte) {
/* 311 */       if (Trace.isOn) {
/* 312 */         Trace.catchBlock("com.ibm.mq.headers.MQDataException", "getMQDataException(Exception)", rte, 1);
/*     */       }
/*     */       
/* 315 */       if (Trace.isOn) {
/* 316 */         Trace.throwing("com.ibm.mq.headers.MQDataException", "getMQDataException(Exception)", rte);
/*     */       }
/*     */       
/* 319 */       throw rte;
/*     */     }
/* 321 */     catch (Exception ignored) {
/* 322 */       if (Trace.isOn) {
/* 323 */         Trace.catchBlock("com.ibm.mq.headers.MQDataException", "getMQDataException(Exception)", ignored, 2);
/*     */       }
/*     */ 
/*     */       
/* 327 */       MQDataException dataException = null;
/* 328 */       if (e instanceof java.io.EOFException) {
/* 329 */         dataException = new MQDataException(2, 6114, e);
/*     */       } else {
/* 331 */         dataException = new MQDataException(2, 2195, e);
/*     */       } 
/* 333 */       if (Trace.isOn) {
/* 334 */         Trace.exit("com.ibm.mq.headers.MQDataException", "getMQDataException(Exception)", dataException, 2);
/*     */       }
/*     */       
/* 337 */       return dataException;
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
/*     */   public String getMessage() {
/* 352 */     if (this.ostrMessage == null)
/*     */     {
/*     */       
/* 355 */       if (exceptionMessages == null) {
/* 356 */         this.ostrMessage = "Message catalog not found, completion=" + this.completionCode + ", reason=" + this.reasonCode;
/* 357 */       } else if (this.msgId == null || this.msgId.length() == 0) {
/*     */         
/* 359 */         String message = exceptionMessages.getString("MQJE001b");
/* 360 */         String[] inserts = new String[2];
/* 361 */         inserts[0] = Integer.toString(this.completionCode);
/* 362 */         inserts[1] = Integer.toString(this.reasonCode);
/* 363 */         this.ostrMessage = MessageFormat.format(message, (Object[])inserts);
/*     */       }
/*     */       else {
/*     */         
/* 367 */         String explanation = exceptionMessages.getString(this.msgId);
/*     */ 
/*     */         
/* 370 */         String message = exceptionMessages.getString("MQJE001");
/* 371 */         String[] inserts = new String[3];
/* 372 */         inserts[0] = Integer.toString(this.completionCode);
/* 373 */         inserts[1] = Integer.toString(this.reasonCode);
/* 374 */         inserts[2] = explanation;
/* 375 */         this.ostrMessage = MessageFormat.format(message, new Object[] { inserts });
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.data(this, "com.ibm.mq.headers.MQDataException", "getMessage()", "getter", this.ostrMessage);
/*     */     }
/*     */     
/* 385 */     return this.ostrMessage;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQDataException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */