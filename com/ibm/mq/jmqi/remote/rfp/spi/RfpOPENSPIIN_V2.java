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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class RfpOPENSPIIN_V2
/*     */   extends RfpOPENSPIIN_V1
/*     */ {
/*     */   private int policyErrorQueueOffset;
/*     */   private int policyDataOffsetOffset;
/*     */   private int policyDataLengthOffset;
/*     */   
/*     */   static {
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN_V2", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpOPENSPIIN.java");
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
/*     */ 
/*     */   
/*     */   protected RfpOPENSPIIN_V2(JmqiEnvironment env, byte[] buffer, int offset) {
/* 288 */     super(env, buffer, offset, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptionsSize(int optionsSize) {
/* 297 */     if (Trace.isOn) {
/* 298 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN_V2", "setOptionsSize(int)", "setter", 
/* 299 */           Integer.valueOf(optionsSize));
/*     */     }
/* 301 */     this.policyErrorQueueOffset = 12 + optionsSize;
/* 302 */     this.policyDataOffsetOffset = this.policyErrorQueueOffset + 48;
/* 303 */     this.policyDataLengthOffset = this.policyDataOffsetOffset + 4;
/* 304 */     this.odOffset = this.policyDataLengthOffset + 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPolicyErrorQueueOffset() {
/* 309 */     int traceRet1 = this.offset + this.policyErrorQueueOffset;
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN_V2", "getPolicyErrorQueueOffset()", "getter", 
/* 312 */           Integer.valueOf(traceRet1));
/*     */     }
/* 314 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPolicyDataOffsetOffset() {
/* 319 */     int traceRet1 = this.offset + this.policyDataOffsetOffset;
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN_V2", "getPolicyDataOffsetOffset()", "getter", 
/* 322 */           Integer.valueOf(traceRet1));
/*     */     }
/* 324 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPolicyDataLengthOffset() {
/* 329 */     int traceRet1 = this.offset + this.policyDataLengthOffset;
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN_V2", "getPolicyDataLengthOffset()", "getter", 
/* 332 */           Integer.valueOf(traceRet1));
/*     */     }
/* 334 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpOPENSPIIN_V2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */