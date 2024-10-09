/*     */ package com.ibm.disthub2.impl.client;
/*     */ 
/*     */ import java.io.OutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Logger
/*     */ {
/*  41 */   private static String theLoggerPath = "com.ibm.disthub2.impl.jms";
/*     */   
/*     */   private static Logger theLogger;
/*     */   
/*     */   private static boolean fDone = false;
/*     */ 
/*     */   
/*     */   public abstract void connectInternal();
/*     */ 
/*     */   
/*     */   public abstract boolean logItInternal(long paramLong);
/*     */ 
/*     */   
/*     */   public abstract int releaseEventsInternal(OutputStream paramOutputStream);
/*     */   
/*     */   public abstract void debugInternal(long paramLong, Object paramObject, String paramString, Object[] paramArrayOfObject);
/*     */   
/*     */   public abstract void logInternal(long paramLong, String paramString, Object[] paramArrayOfObject);
/*     */   
/*     */   public static void setLoggerPath(String path) {
/*  61 */     if (path != null) {
/*  62 */       theLoggerPath = path;
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
/*     */   public static void start() {
/*     */     try {
/*  75 */       String classname = theLoggerPath + ".LoggerImpl";
/*     */ 
/*     */       
/*  78 */       theLogger = (Logger)Class.forName(classname).newInstance();
/*     */     
/*     */     }
/*  81 */     catch (Exception e) {
/*     */ 
/*     */       
/*     */       try {
/*     */ 
/*     */         
/*  87 */         theLogger = (Logger)Class.forName("com.ibm.disthub2.impl.jms.LoggerImpl").newInstance();
/*     */       }
/*  89 */       catch (Exception e2) {
/*     */         
/*  91 */         theLogger = null;
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
/*     */   public static void connect() {
/* 104 */     if (theLogger != null) theLogger.connectInternal();
/*     */   
/*     */   }
/*     */   
/*     */   public static boolean logIt(long type) {
/* 109 */     if (theLogger != null) return theLogger.logItInternal(type); 
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int releaseEvents(OutputStream S) {
/* 115 */     if (theLogger != null) return theLogger.releaseEventsInternal(S); 
/* 116 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void debug(long type, Object caller, String module, Object[] args) {
/* 121 */     if (theLogger != null) theLogger.debugInternal(type, caller, module, args);
/*     */   
/*     */   }
/*     */   
/*     */   public static void log(long type, String module, Object[] args) {
/* 126 */     if (theLogger != null) theLogger.logInternal(type, module, args);
/*     */   
/*     */   }
/*     */   
/*     */   public static void log(long type, String module) {
/* 131 */     log(type, module, new Object[0]);
/*     */   }
/*     */   
/*     */   public static void log(long type, String module, Object a1) {
/* 135 */     log(type, module, new Object[] { a1 });
/*     */   }
/*     */   
/*     */   public static void log(long type, String module, Object a1, Object a2) {
/* 139 */     log(type, module, new Object[] { a1, a2 });
/*     */   }
/*     */   
/*     */   public static void log(long type, String module, Object a1, Object a2, Object a3) {
/* 143 */     log(type, module, new Object[] { a1, a2, a3 });
/*     */   }
/*     */   
/*     */   public static void log(long type, String module, Object a1, Object a2, Object a3, Object a4) {
/* 147 */     log(type, module, new Object[] { a1, a2, a3, a4 });
/*     */   }
/*     */   
/*     */   public static void log(long type, String module, Object a1, Object a2, Object a3, Object a4, Object a5) {
/* 151 */     log(type, module, new Object[] { a1, a2, a3, a4, a5 });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void fatalError(Throwable e) {
/*     */     try {
/* 162 */       log(-215071858178937L, "Logger.fatalError", e);
/* 163 */       if (fDone)
/* 164 */         return;  fDone = true;
/* 165 */       ClientServices.main.fatalError(e);
/* 166 */       Thread.currentThread().join();
/* 167 */     } catch (Throwable throwable) {}
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\disthub2\impl\client\Logger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */