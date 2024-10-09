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
/*    */ public class WASAdapter64
/*    */   extends WASAdapter
/*    */ {
/*    */   public static final String sccsid3 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/WASAdapter64.java";
/*    */   
/*    */   static {
/* 33 */     if (Trace.isOn) {
/* 34 */       Trace.data("com.ibm.mq.jmqi.local.internal.adapters.WASAdapter64", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/WASAdapter64.java");
/*    */     }
/*    */   }
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
/*    */   public WASAdapter64(JmqiEnvironment env, LocalMQ mq) {
/* 49 */     super(env, mq);
/* 50 */     if (Trace.isOn) {
/* 51 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.WASAdapter64", "<init>(JmqiEnvironment,LocalMQ)", new Object[] { env, mq });
/*    */     }
/*    */     
/* 54 */     if (Trace.isOn) {
/* 55 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.WASAdapter64", "<init>(JmqiEnvironment,LocalMQ)");
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
/* 66 */     String traceRet1 = this.mq.getZosWASLibraryName64();
/* 67 */     if (Trace.isOn) {
/* 68 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.WASAdapter64", "getLibraryName()", "getter", traceRet1);
/*    */     }
/*    */     
/* 71 */     return traceRet1;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\adapters\WASAdapter64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */