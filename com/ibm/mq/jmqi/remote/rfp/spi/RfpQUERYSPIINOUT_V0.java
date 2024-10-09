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
/*     */ 
/*     */ 
/*     */ class RfpQUERYSPIINOUT_V0
/*     */   extends RfpQUERYSPIINOUT
/*     */ {
/*     */   static {
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIINOUT_V0", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpQUERYSPIINOUT.java");
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
/*     */   protected RfpQUERYSPIINOUT_V0(JmqiEnvironment env, byte[] buffer, int offset) {
/* 146 */     super(env, buffer, offset, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength(boolean swap) {
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIINOUT_V0", "getLength(boolean)", new Object[] {
/* 156 */             Boolean.valueOf(swap)
/*     */           });
/*     */     }
/* 159 */     int traceRet1 = -1;
/*     */ 
/*     */     
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIINOUT_V0", "getLength(boolean)", 
/* 164 */           Integer.valueOf(traceRet1));
/*     */     }
/* 166 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 171 */     int traceRet1 = 8;
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIINOUT_V0", "getStructSize()", "getter", 
/* 174 */           Integer.valueOf(traceRet1));
/*     */     }
/* 176 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpQUERYSPIINOUT_V0.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */