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
/*     */ class RfpACTIVATESPIOUT_V0
/*     */   extends RfpACTIVATESPIOUT
/*     */ {
/*     */   static {
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIOUT_V0", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpACTIVATESPIOUT.java");
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
/*     */   protected RfpACTIVATESPIOUT_V0(JmqiEnvironment env, byte[] buffer, int offset) {
/* 142 */     super(env, buffer, offset, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength(boolean swap) {
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIOUT_V0", "getLength(boolean)", new Object[] {
/* 152 */             Boolean.valueOf(swap)
/*     */           });
/*     */     }
/* 155 */     int traceRet1 = -1;
/*     */ 
/*     */     
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIOUT_V0", "getLength(boolean)", 
/* 160 */           Integer.valueOf(traceRet1));
/*     */     }
/* 162 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 167 */     int traceRet1 = 8;
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIOUT_V0", "getStructSize()", "getter", 
/* 170 */           Integer.valueOf(traceRet1));
/*     */     }
/* 172 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpACTIVATESPIOUT_V0.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */