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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class RfpUNSUBSCRIBESPIOUT_V1
/*     */   extends RfpUNSUBSCRIBESPIOUT
/*     */ {
/*     */   private static final int SPIUSD_OFFSET = 12;
/*     */   private int spiUsdSize;
/*     */   
/*     */   static {
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIOUT_V1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpUNSUBSCRIBESPIOUT.java");
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
/*     */   
/*     */   protected RfpUNSUBSCRIBESPIOUT_V1(JmqiEnvironment env, byte[] buffer, int offset, int spiUsdSize) {
/* 163 */     super(env, buffer, offset, 1);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 168 */     this.spiUsdSize = 0;
/*     */     this.spiUsdSize = spiUsdSize;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpiUsdOffset() {
/* 175 */     int traceRet1 = this.offset + 12;
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIOUT_V1", "getSpiUsdOffset()", "getter", 
/* 178 */           Integer.valueOf(traceRet1));
/*     */     }
/* 180 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 185 */     int traceRet1 = 12 + this.spiUsdSize;
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIOUT_V1", "getStructSize()", "getter", 
/* 188 */           Integer.valueOf(traceRet1));
/*     */     }
/* 190 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setSpiUsdSize(int spiUsdSize) {
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIOUT_V1", "setSpiUsdSize(int)", "setter", 
/* 197 */           Integer.valueOf(spiUsdSize));
/*     */     }
/* 199 */     this.spiUsdSize = spiUsdSize;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpUNSUBSCRIBESPIOUT_V1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */