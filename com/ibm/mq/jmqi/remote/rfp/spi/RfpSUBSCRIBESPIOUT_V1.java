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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class RfpSUBSCRIBESPIOUT_V1
/*     */   extends RfpSUBSCRIBESPIOUT
/*     */ {
/*     */   private static final int HSUB_OFFSET = 12;
/*     */   private static final int SPISD_OFFSET = 16;
/*     */   
/*     */   static {
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIOUT_V1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpSUBSCRIBESPIOUT.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   private int mqsdOffset = 0;
/* 182 */   private int structSize = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpSUBSCRIBESPIOUT_V1(JmqiEnvironment env, byte[] buffer, int offset, int lpiSdSize, int mqSdSize) {
/* 191 */     super(env, buffer, offset, 1);
/* 192 */     this.mqsdOffset = 16 + lpiSdSize;
/* 193 */     this.structSize = this.mqsdOffset + mqSdSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIOUT_V1", "getStructSize()", "getter", 
/* 203 */           Integer.valueOf(this.structSize));
/*     */     }
/* 205 */     return this.structSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHSub(boolean swap) {
/* 213 */     if (Trace.isOn)
/* 214 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIOUT_V1", "getHSub(boolean)", new Object[] {
/* 215 */             Boolean.valueOf(swap)
/*     */           }); 
/* 217 */     int traceRet1 = this.dc.readI32(getRfpBuffer(), this.offset + 12, swap);
/*     */ 
/*     */     
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIOUT_V1", "getHSub(boolean)", 
/* 222 */           Integer.valueOf(traceRet1));
/*     */     }
/* 224 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMqsdOffset() {
/* 232 */     int traceRet1 = this.offset + this.mqsdOffset;
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIOUT_V1", "getMqsdOffset()", "getter", 
/* 235 */           Integer.valueOf(traceRet1));
/*     */     }
/* 237 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpiSdOffset() {
/* 245 */     int traceRet1 = this.offset + 16;
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIOUT_V1", "getSpiSdOffset()", "getter", 
/* 248 */           Integer.valueOf(traceRet1));
/*     */     }
/* 250 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setlpiSdSize(int lpiSdSize) {
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIOUT_V1", "setlpiSdSize(int)", "setter", 
/* 257 */           Integer.valueOf(lpiSdSize));
/*     */     }
/* 259 */     this.mqsdOffset = 16 + lpiSdSize;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setmqSdSize(int mqSdSize) {
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIOUT_V1", "setmqSdSize(int)", "setter", 
/* 266 */           Integer.valueOf(mqSdSize));
/*     */     }
/* 268 */     this.structSize = this.mqsdOffset + mqSdSize;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpSUBSCRIBESPIOUT_V1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */