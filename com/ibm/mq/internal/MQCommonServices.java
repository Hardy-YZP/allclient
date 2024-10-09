/*     */ package com.ibm.mq.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiDefaultPropertyHandler;
/*     */ import com.ibm.mq.jmqi.JmqiDefaultThreadPoolFactory;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiFactory;
/*     */ import com.ibm.mq.jmqi.JmqiPropertyHandler;
/*     */ import com.ibm.mq.jmqi.JmqiThreadPoolFactory;
/*     */ import com.ibm.mq.jmqi.system.JmqiComponent;
/*     */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQCommonServices
/*     */   implements JmqiComponent
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/internal/MQCommonServices.java";
/*     */   public static final JmqiEnvironment jmqiEnv;
/*     */   public static final int jmqiCompId;
/*     */   public static final MQCommonServices jmqiComponent;
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.internal.MQCommonServices", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/internal/MQCommonServices.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class MQCommonServicesInitializationException
/*     */     extends RuntimeException
/*     */   {
/*     */     static final long serialVersionUID = -2451892550460914442L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public MQCommonServicesInitializationException(Throwable cause) {
/*  63 */       super(cause);
/*  64 */       if (Trace.isOn) {
/*  65 */         Trace.entry(this, "com.ibm.mq.internal.MQCommonServicesInitializationException", "<init>(Throwable)", new Object[] { cause });
/*     */       }
/*     */       
/*  68 */       if (Trace.isOn) {
/*  69 */         Trace.exit(this, "com.ibm.mq.internal.MQCommonServicesInitializationException", "<init>(Throwable)");
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public static final JmqiThreadPoolFactory threadPool = (JmqiThreadPoolFactory)new JmqiDefaultThreadPoolFactory();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public static final JmqiPropertyHandler propertyHandler = (JmqiPropertyHandler)new JmqiDefaultPropertyHandler();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getJmqiComponentName() {
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.data(this, "com.ibm.mq.internal.MQCommonServices", "getJmqiComponentName()", "getter", "com.ibm.mq");
/*     */     }
/*     */     
/* 105 */     return "com.ibm.mq";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiComponentTls newTlsObject() {
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.entry(this, "com.ibm.mq.internal.MQCommonServices", "newTlsObject()");
/*     */     }
/* 116 */     JmqiComponentTls traceRet1 = new MQThreadLocalStorage();
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.exit(this, "com.ibm.mq.internal.MQCommonServices", "newTlsObject()", traceRet1);
/*     */     }
/* 120 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MQThreadLocalStorage getTls() {
/* 128 */     MQThreadLocalStorage traceRet1 = (MQThreadLocalStorage)((JmqiSystemEnvironment)jmqiEnv).getComponentTls(jmqiCompId);
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.data("com.ibm.mq.internal.MQCommonServices", "getTls()", "getter", traceRet1);
/*     */     }
/* 132 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.entry("com.ibm.mq.internal.MQCommonServices", "static()");
/*     */     }
/*     */     try {
/* 146 */       jmqiEnv = JmqiFactory.getInstance(threadPool, propertyHandler);
/* 147 */       jmqiComponent = new MQCommonServices();
/* 148 */       jmqiCompId = ((JmqiSystemEnvironment)jmqiEnv).registerComponent(jmqiComponent);
/*     */     }
/* 150 */     catch (JmqiException e) {
/* 151 */       if (Trace.isOn) {
/* 152 */         Trace.catchBlock("com.ibm.mq.internal.MQCommonServices", "static()", (Throwable)e);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 160 */       MQCommonServicesInitializationException traceRet1 = new MQCommonServicesInitializationException((Throwable)e);
/*     */       
/* 162 */       if (Trace.isOn) {
/* 163 */         Trace.throwing("com.ibm.mq.internal.MQCommonServices", "static()", traceRet1);
/*     */       }
/* 165 */       throw traceRet1;
/*     */     } 
/*     */     
/* 168 */     jmqiEnv.setCaller('B');
/* 169 */     if (Trace.isOn)
/* 170 */       Trace.exit("com.ibm.mq.internal.MQCommonServices", "static()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\internal\MQCommonServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */