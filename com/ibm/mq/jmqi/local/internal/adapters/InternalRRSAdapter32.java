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
/*    */ public class InternalRRSAdapter32
/*    */   extends InternalRRSAdapter
/*    */ {
/*    */   public static final String sccsid3 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/InternalRRSAdapter32.java";
/*    */   
/*    */   static {
/* 33 */     if (Trace.isOn) {
/* 34 */       Trace.data("com.ibm.mq.jmqi.local.internal.adapters.InternalRRSAdapter32", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/InternalRRSAdapter32.java");
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
/*    */   public InternalRRSAdapter32(JmqiEnvironment env, LocalMQ mq) {
/* 49 */     super(env, mq);
/* 50 */     if (Trace.isOn) {
/* 51 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.InternalRRSAdapter32", "<init>(JmqiEnvironment,LocalMQ)", new Object[] { env, mq });
/*    */     }
/*    */ 
/*    */     
/* 55 */     if (Trace.isOn) {
/* 56 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.InternalRRSAdapter32", "<init>(JmqiEnvironment,LocalMQ)");
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
/* 67 */     String traceRet1 = this.mq.getZosInternalRRSLibraryName();
/* 68 */     if (Trace.isOn) {
/* 69 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.InternalRRSAdapter32", "getLibraryName()", "getter", traceRet1);
/*    */     }
/*    */     
/* 72 */     return traceRet1;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\adapters\InternalRRSAdapter32.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */