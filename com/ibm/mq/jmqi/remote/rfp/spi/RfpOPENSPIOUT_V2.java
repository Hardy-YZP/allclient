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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class RfpOPENSPIOUT_V2
/*     */   extends RfpOPENSPIOUT_V1
/*     */ {
/*     */   private int policyErrorQueueOffset;
/*     */   private int policyDataOffsetOffset;
/*     */   private int policyDataLengthOffset;
/*     */   
/*     */   static {
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V2", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpOPENSPIOUT.java");
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
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpOPENSPIOUT_V2(JmqiEnvironment env, byte[] buffer, int offset, int optionsSize, int odSize) {
/* 328 */     super(env, buffer, offset, optionsSize, odSize, 2);
/* 329 */     if (Trace.isOn) {
/* 330 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V2", "<init>(JmqiEnvironment,byte [ ],int,int,int)", new Object[] { env, buffer, 
/*     */             
/* 332 */             Integer.valueOf(offset), Integer.valueOf(optionsSize), Integer.valueOf(odSize) });
/*     */     }
/*     */     
/* 335 */     this.policyErrorQueueOffset = 12 + optionsSize;
/* 336 */     this.policyDataOffsetOffset = this.policyErrorQueueOffset + 48;
/* 337 */     this.policyDataLengthOffset = this.policyDataOffsetOffset + 4;
/* 338 */     this.odOffset = this.policyDataLengthOffset + 4;
/* 339 */     this.structSize = this.policyDataLengthOffset + odSize;
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V2", "<init>(JmqiEnvironment,byte [ ],int,int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setOptionsSize(int optionsSize) {
/* 349 */     if (Trace.isOn) {
/* 350 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V2", "setOptionsSize(int)", "setter", 
/* 351 */           Integer.valueOf(optionsSize));
/*     */     }
/*     */     
/* 354 */     this.policyErrorQueueOffset = 12 + optionsSize;
/* 355 */     this.policyDataOffsetOffset = this.policyErrorQueueOffset + 48;
/* 356 */     this.policyDataLengthOffset = this.policyDataOffsetOffset + 4;
/* 357 */     this.odOffset = this.policyDataLengthOffset + 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPolicyErrorQueueOffset() {
/* 362 */     int traceRet1 = this.offset + this.policyErrorQueueOffset;
/* 363 */     if (Trace.isOn) {
/* 364 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V2", "getPolicyErrorQueueOffset()", "getter", 
/* 365 */           Integer.valueOf(traceRet1));
/*     */     }
/* 367 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPolicyDataOffsetOffset() {
/* 372 */     int traceRet1 = this.offset + this.policyDataOffsetOffset;
/* 373 */     if (Trace.isOn) {
/* 374 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V2", "getPolicyDataOffsetOffset()", "getter", 
/* 375 */           Integer.valueOf(traceRet1));
/*     */     }
/* 377 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPolicyDataLengthOffset() {
/* 382 */     int traceRet1 = this.offset + this.policyDataLengthOffset;
/* 383 */     if (Trace.isOn) {
/* 384 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V2", "getPolicyDataLengthOffset()", "getter", 
/* 385 */           Integer.valueOf(traceRet1));
/*     */     }
/* 387 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpOPENSPIOUT_V2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */