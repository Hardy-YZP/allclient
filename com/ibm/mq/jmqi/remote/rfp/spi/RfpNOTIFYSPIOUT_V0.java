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
/*     */ class RfpNOTIFYSPIOUT_V0
/*     */   extends RfpNOTIFYSPIOUT
/*     */ {
/*     */   static {
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIOUT_V0", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpNOTIFYSPIOUT.java");
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
/*     */   protected RfpNOTIFYSPIOUT_V0(JmqiEnvironment env, byte[] buffer, int offset) {
/* 130 */     super(env, buffer, offset, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength(boolean swap) {
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIOUT_V0", "getLength(boolean)", new Object[] {
/* 140 */             Boolean.valueOf(swap)
/*     */           });
/*     */     }
/* 143 */     int traceRet1 = -1;
/*     */ 
/*     */     
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIOUT_V0", "getLength(boolean)", 
/* 148 */           Integer.valueOf(traceRet1));
/*     */     }
/* 150 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 155 */     int traceRet1 = 8;
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIOUT_V0", "getStructSize()", "getter", 
/* 158 */           Integer.valueOf(traceRet1));
/*     */     }
/* 160 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpNOTIFYSPIOUT_V0.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */