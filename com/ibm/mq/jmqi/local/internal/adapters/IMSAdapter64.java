/*    */ package com.ibm.mq.jmqi.local.internal.adapters;
/*    */ 
/*    */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*    */ import com.ibm.mq.jmqi.local.LocalMQ;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*    */ 
/*    */ 
/*    */ public class IMSAdapter64
/*    */   extends Adapter
/*    */ {
/*    */   private static final String LIBRARY_NAME = "mqjims64";
/*    */   
/*    */   public IMSAdapter64(JmqiEnvironment env, LocalMQ mq) {
/* 39 */     super(env, mq);
/* 40 */     if (Trace.isOn) {
/* 41 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.IMSAdapter64", "<init>(JmqiEnvironment,LocalMQ)", new Object[] { env, mq });
/*    */     }
/*    */     
/* 44 */     if (Trace.isOn) {
/* 45 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.IMSAdapter64", "<init>(JmqiEnvironment,LocalMQ)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLibraryName() {
/* 56 */     String traceRet1 = "mqjims64";
/* 57 */     if (Trace.isOn) {
/* 58 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.IMSAdapter64", "getLibraryName()", "getter", "mqjims64");
/*    */     }
/*    */     
/* 61 */     return "mqjims64";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRXPBFlags() {
/* 70 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isSharedHandlesSupported() {
/* 79 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isWorkerThreadSupported() {
/* 88 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isIMS() {
/* 97 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\adapters\IMSAdapter64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */