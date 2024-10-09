/*     */ package com.ibm.mq.ese.util;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SystemUtils
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/util/SystemUtils.java";
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.ese.util.SystemUtils", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/util/SystemUtils.java");
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
/*     */   public static String getSystemProperty(final String key, final String defaultValue) {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.entry("com.ibm.mq.ese.util.SystemUtils", "getSystemProperty(final String,final String)", new Object[] { key, defaultValue });
/*     */     }
/*     */ 
/*     */     
/*  61 */     String traceRet1 = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*     */         {
/*     */           public String run()
/*     */           {
/*     */             try {
/*  66 */               return System.getProperty(key);
/*     */             }
/*  68 */             catch (Throwable t) {
/*  69 */               if (Trace.isOn) {
/*  70 */                 Trace.catchBlock("com.ibm.mq.ese.util.SystemUtils", "getSystemProperty(final String,final String)", t);
/*     */               }
/*     */ 
/*     */               
/*  74 */               if (Trace.isOn) {
/*  75 */                 Trace.exit("com.ibm.mq.ese.util.SystemUtils", "getSystemProperty(final String,final String)", defaultValue, 2);
/*     */               }
/*     */               
/*  78 */               return defaultValue;
/*     */             } 
/*     */           }
/*     */         });
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit("com.ibm.mq.ese.util.SystemUtils", "getSystemProperty(final String,final String)", traceRet1, 1);
/*     */     }
/*     */     
/*  86 */     return traceRet1;
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
/*     */   public static String getEnv(final String key, final String defaultValue) {
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.entry("com.ibm.mq.ese.util.SystemUtils", "getEnv(final String,final String)", new Object[] { key, defaultValue });
/*     */     }
/*     */ 
/*     */     
/* 103 */     String traceRet1 = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*     */         {
/*     */           public String run()
/*     */           {
/*     */             try {
/* 108 */               return System.getenv(key);
/*     */             }
/* 110 */             catch (Throwable t) {
/* 111 */               if (Trace.isOn) {
/* 112 */                 Trace.catchBlock("com.ibm.mq.ese.util.SystemUtils", "getEnv(final String,final String)", t);
/*     */               }
/*     */ 
/*     */               
/* 116 */               if (Trace.isOn) {
/* 117 */                 Trace.exit("com.ibm.mq.ese.util.SystemUtils", "getEnv(final String,final String)", defaultValue, 2);
/*     */               }
/*     */               
/* 120 */               return defaultValue;
/*     */             } 
/*     */           }
/*     */         });
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.exit("com.ibm.mq.ese.util.SystemUtils", "getEnv(final String,final String)", traceRet1, 1);
/*     */     }
/*     */     
/* 128 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\es\\util\SystemUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */