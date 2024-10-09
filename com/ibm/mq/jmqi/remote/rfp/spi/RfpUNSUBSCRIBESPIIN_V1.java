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
/*     */ class RfpUNSUBSCRIBESPIIN_V1
/*     */   extends RfpUNSUBSCRIBESPIIN
/*     */ {
/*     */   private static final int SPIUSD_OFFSET = 12;
/*     */   private int spiUsdSize;
/*     */   
/*     */   static {
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIIN_V1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpUNSUBSCRIBESPIIN.java");
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
/*     */   protected RfpUNSUBSCRIBESPIIN_V1(JmqiEnvironment env, byte[] buffer, int offset, int spiUsdSize) {
/* 144 */     super(env, buffer, offset, 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 150 */     this.spiUsdSize = 0;
/*     */     this.spiUsdSize = spiUsdSize;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpiUsdOffset() {
/* 157 */     int traceRet1 = this.offset + 12;
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIIN_V1", "getSpiUsdOffset()", "getter", 
/* 160 */           Integer.valueOf(traceRet1));
/*     */     }
/* 162 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 170 */     int traceRet1 = 12 + this.spiUsdSize;
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIIN_V1", "getStructSize()", "getter", 
/* 173 */           Integer.valueOf(traceRet1));
/*     */     }
/* 175 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setSpiUsdSize(int spiUsdSize) {
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIIN_V1", "setSpiUsdSize(int)", "setter", 
/* 182 */           Integer.valueOf(spiUsdSize));
/*     */     }
/* 184 */     this.spiUsdSize = spiUsdSize;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpUNSUBSCRIBESPIIN_V1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */