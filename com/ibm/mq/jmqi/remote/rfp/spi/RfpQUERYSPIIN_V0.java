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
/*     */ class RfpQUERYSPIIN_V0
/*     */   extends RfpQUERYSPIIN
/*     */ {
/*     */   static {
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIIN_V0", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpQUERYSPIIN.java");
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
/*     */   protected RfpQUERYSPIIN_V0(JmqiEnvironment env, byte[] buffer, int offset) {
/* 135 */     super(env, buffer, offset, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength(boolean swap) {
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIIN_V0", "getLength(boolean)", new Object[] {
/* 146 */             Boolean.valueOf(swap)
/*     */           });
/*     */     }
/* 149 */     int traceRet1 = -1;
/*     */ 
/*     */     
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIIN_V0", "getLength(boolean)", 
/* 154 */           Integer.valueOf(traceRet1));
/*     */     }
/* 156 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 164 */     int traceRet1 = 8;
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIIN_V0", "getStructSize()", "getter", 
/* 167 */           Integer.valueOf(traceRet1));
/*     */     }
/* 169 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpQUERYSPIIN_V0.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */