/*     */ package com.ibm.mq.jmqi.remote.rfp.spi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
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
/*     */ class RfpSYNCPOINTSPIINOUT_V0
/*     */   extends RfpSYNCPOINTSPIINOUT
/*     */ {
/*     */   static {
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpSYNCPOINTSPIINOUT_V0", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpSYNCPOINTSPIINOUT.java");
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
/*     */   protected RfpSYNCPOINTSPIINOUT_V0(JmqiEnvironment env, byte[] buffer, int offset) {
/* 144 */     super(env, buffer, offset, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength(boolean swap) {
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSYNCPOINTSPIINOUT_V0", "getLength(boolean)", new Object[] {
/* 154 */             Boolean.valueOf(swap)
/*     */           });
/*     */     }
/* 157 */     int traceRet1 = -1;
/*     */ 
/*     */     
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSYNCPOINTSPIINOUT_V0", "getLength(boolean)", 
/* 162 */           Integer.valueOf(traceRet1));
/*     */     }
/* 164 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 169 */     int traceRet1 = 8;
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSYNCPOINTSPIINOUT_V0", "getStructSize()", "getter", 
/* 172 */           Integer.valueOf(traceRet1));
/*     */     }
/* 174 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpSYNCPOINTSPIINOUT_V0.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */