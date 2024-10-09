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
/*    */ public class CICSAdapter64
/*    */   extends CICSAdapter
/*    */ {
/*    */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/CICSAdapter64.java";
/*    */   
/*    */   static {
/* 35 */     if (Trace.isOn) {
/* 36 */       Trace.data("com.ibm.mq.jmqi.local.internal.adapters.CICSAdapter64", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/CICSAdapter64.java");
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
/*    */   
/*    */   public CICSAdapter64(JmqiEnvironment env, LocalMQ mq) {
/* 52 */     super(env, mq);
/* 53 */     if (Trace.isOn) {
/* 54 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.CICSAdapter64", "<init>(JmqiEnvironment,LocalMQ)", new Object[] { env, mq });
/*    */     }
/*    */     
/* 57 */     if (Trace.isOn) {
/* 58 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.CICSAdapter64", "<init>(JmqiEnvironment,LocalMQ)");
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
/* 69 */     String traceRet1 = this.mq.getZosCicsLibraryName64();
/* 70 */     if (Trace.isOn) {
/* 71 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.CICSAdapter64", "getLibraryName()", "getter", traceRet1);
/*    */     }
/*    */     
/* 74 */     return traceRet1;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\adapters\CICSAdapter64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */