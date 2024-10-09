/*     */ package com.ibm.msg.client.commonservices.j2se;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.CommonServices;
/*     */ import com.ibm.msg.client.commonservices.j2se.commandmanager.CommandManagerImpl;
/*     */ import com.ibm.msg.client.commonservices.j2se.wmqsupport.LoggerImpl;
/*     */ import com.ibm.msg.client.commonservices.j2se.wmqsupport.PropertyStoreImpl;
/*     */ import com.ibm.msg.client.commonservices.j2se.wmqsupport.TracerImpl;
/*     */ import com.ibm.msg.client.commonservices.j2se.workqueue.WorkQueueManagerImplementation;
/*     */ import com.ibm.msg.client.commonservices.provider.CSPCommonServices;
/*     */ import com.ibm.msg.client.commonservices.provider.commandmanager.CSPCommandManager;
/*     */ import com.ibm.msg.client.commonservices.provider.log.CSPLog;
/*     */ import com.ibm.msg.client.commonservices.provider.nls.CSPNLSServices;
/*     */ import com.ibm.msg.client.commonservices.provider.propertystore.CSPPropertyStore;
/*     */ import com.ibm.msg.client.commonservices.provider.trace.CSPTrace;
/*     */ import com.ibm.msg.client.commonservices.provider.workqueue.CSPWorkQueueManager;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommonServicesImplementation
/*     */   implements CSPCommonServices
/*     */ {
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.msg.client.commonservices.j2se.CommonServicesImplementation", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.commonservices.j2se/src/com/ibm/msg/client/commonservices/j2se/CommonServicesImplementation.java");
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
/*     */   public CSPTrace getTrace() {
/*  64 */     TracerImpl tracerImpl = new TracerImpl();
/*     */     
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.CommonServicesImplementation", "getTrace()", "getter", tracerImpl);
/*     */     }
/*     */     
/*  70 */     return (CSPTrace)tracerImpl;
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
/*     */   public CSPLog getLog() {
/*  82 */     LoggerImpl loggerImpl = new LoggerImpl();
/*     */     
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.CommonServicesImplementation", "getLog()", "getter", loggerImpl);
/*     */     }
/*     */     
/*  88 */     return (CSPLog)loggerImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSPNLSServices getNLSServices() {
/*  99 */     CSPNLSServices traceRet1 = new NLSServices();
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.CommonServicesImplementation", "getNLSServices()", "getter", traceRet1);
/*     */     }
/*     */     
/* 104 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSPWorkQueueManager getWorkQueueManager() {
/* 115 */     WorkQueueManagerImplementation workQueueManagerImplementation = new WorkQueueManagerImplementation();
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.CommonServicesImplementation", "getWorkQueueManager()", "getter", workQueueManagerImplementation);
/*     */     }
/*     */     
/* 120 */     return (CSPWorkQueueManager)workQueueManagerImplementation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSPCommandManager getCommandManager() {
/* 131 */     CommandManagerImpl commandManagerImpl = new CommandManagerImpl();
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.CommonServicesImplementation", "getCommandManager()", "getter", commandManagerImpl);
/*     */     }
/*     */     
/* 136 */     return (CSPCommandManager)commandManagerImpl;
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
/*     */   public CSPPropertyStore getPropertyStore() {
/* 148 */     PropertyStoreImpl propertyStoreImpl = new PropertyStoreImpl();
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.data(this, "com.ibm.msg.client.commonservices.j2se.CommonServicesImplementation", "getPropertyStore()", "getter", propertyStoreImpl);
/*     */     }
/*     */     
/* 153 */     return (CSPPropertyStore)propertyStoreImpl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static Properties getProductization() {
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.data("com.ibm.msg.client.commonservices.j2se.CommonServicesImplementation", "getPropertyStore() - deprecated", "getter");
/*     */     }
/*     */     
/* 167 */     Properties traceRet1 = CommonServices.getProductization();
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.data("com.ibm.msg.client.commonservices.j2se.CommonServicesImplementation", "getProductization()", "getter", traceRet1);
/*     */     }
/*     */     
/* 172 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\j2se\CommonServicesImplementation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */