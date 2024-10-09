/*    */ package com.ibm.mq;
/*    */ 
/*    */ import com.ibm.mq.jmqi.JmqiDefaultPropertyHandler;
/*    */ import com.ibm.mq.jmqi.JmqiDefaultThreadPoolFactory;
/*    */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*    */ import com.ibm.mq.jmqi.JmqiException;
/*    */ import com.ibm.mq.jmqi.JmqiFactory;
/*    */ import com.ibm.mq.jmqi.JmqiPropertyHandler;
/*    */ import com.ibm.mq.jmqi.JmqiThreadPoolFactory;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JmqiSESSION
/*    */ {
/*    */   static {
/* 40 */     if (Trace.isOn) {
/* 41 */       Trace.data("com.ibm.mq.JmqiSESSION", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/JmqiSESSION.java");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/* 46 */   private static JmqiEnvironment env = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized JmqiEnvironment getJmqiEnv() {
/* 53 */     if (env == null) {
/* 54 */       JmqiDefaultThreadPoolFactory threadPoolFactory = new JmqiDefaultThreadPoolFactory();
/* 55 */       JmqiDefaultPropertyHandler propertyHandler = new JmqiDefaultPropertyHandler();
/*    */       try {
/* 57 */         env = JmqiFactory.getInstance((JmqiThreadPoolFactory)threadPoolFactory, (JmqiPropertyHandler)propertyHandler);
/* 58 */         threadPoolFactory.setEnv(env);
/*    */       }
/* 60 */       catch (JmqiException e) {
/* 61 */         if (Trace.isOn) {
/* 62 */           Trace.catchBlock("com.ibm.mq.JmqiSESSION", "getJmqiEnv()", (Throwable)e);
/*    */         }
/*    */ 
/*    */         
/* 66 */         HashMap<String, Object> info = new HashMap<>();
/* 67 */         info.put("exception", e);
/* 68 */         Trace.ffst("com.ibm.mq.jmqi.JmqiSession", "getJmqiEnv", "XQ001001", info, null);
/*    */       } 
/*    */     } 
/* 71 */     if (Trace.isOn) {
/* 72 */       Trace.data("com.ibm.mq.JmqiSESSION", "getJmqiEnv()", "getter", env);
/*    */     }
/* 74 */     return env;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\JmqiSESSION.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */