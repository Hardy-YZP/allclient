/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmqiFactory
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiFactory.java";
/*  37 */   private static Map<String, JmqiSystemEnvironment> mqiCache = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int LOCAL_SERVER = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int LOCAL_CLIENT = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int REMOTE = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static JmqiEnvironment getInstance(JmqiTraceHandlerAdapter trace, JmqiThreadPoolFactory threadPoolFactory, JmqiPropertyHandler propertyHandler) throws JmqiException {
/*  74 */     return getInstance(threadPoolFactory, propertyHandler);
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
/*     */   public static JmqiEnvironment getInstance(JmqiThreadPoolFactory threadPoolFactory, JmqiPropertyHandler propertyHandler) throws JmqiException {
/*  93 */     JmqiSystemEnvironment env = null;
/*     */ 
/*     */ 
/*     */     
/*  97 */     StringBuffer buffer = new StringBuffer();
/*  98 */     buffer.append(threadPoolFactory.getClass().getName());
/*  99 */     buffer.append(':');
/* 100 */     buffer.append(propertyHandler.getClass().getName());
/* 101 */     String key = buffer.toString();
/*     */ 
/*     */     
/* 104 */     env = mqiCache.get(key);
/* 105 */     if (env == null) {
/* 106 */       synchronized (mqiCache) {
/*     */         
/* 108 */         env = mqiCache.get(key);
/* 109 */         if (env == null) {
/*     */           
/* 111 */           env = new JmqiSystemEnvironment(threadPoolFactory, propertyHandler);
/* 112 */           mqiCache.put(key, env);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 118 */     return (JmqiEnvironment)env;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\JmqiFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */