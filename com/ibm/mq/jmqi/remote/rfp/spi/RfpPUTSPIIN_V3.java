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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class RfpPUTSPIIN_V3
/*     */   extends RfpPUTSPIIN
/*     */ {
/*     */   private static final int OPTIONS_OFFSET = 12;
/*     */   private static final int MSG_LENGTH_OFFSET = 16;
/*     */   private static final int MSGIDRESERVATION_OFFSET = 20;
/*     */   private static final int DELIVERYDELAYHIGH_OFFSET = 24;
/*     */   private static final int DELIVERYDELAYLOW_OFFSET = 28;
/*     */   
/*     */   static {
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V3", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpPUTSPIIN.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpPUTSPIIN_V3(JmqiEnvironment env, byte[] buffer, int offset) {
/* 434 */     super(env, buffer, offset, 3);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 461 */     int traceRet1 = 32;
/* 462 */     if (Trace.isOn) {
/* 463 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V3", "getStructSize()", "getter", 
/* 464 */           Integer.valueOf(traceRet1));
/*     */     }
/* 466 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMessageLength(int bufferLength, boolean swap) {
/* 474 */     if (Trace.isOn)
/* 475 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V3", "setMessageLength(int,boolean)", new Object[] {
/* 476 */             Integer.valueOf(bufferLength), 
/* 477 */             Boolean.valueOf(swap)
/*     */           }); 
/* 479 */     this.dc.writeI32(bufferLength, getRfpBuffer(), this.offset + 16, swap);
/*     */     
/* 481 */     if (Trace.isOn) {
/* 482 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V3", "setMessageLength(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options, boolean swap) {
/* 493 */     if (Trace.isOn)
/* 494 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V3", "setOptions(int,boolean)", new Object[] {
/* 495 */             Integer.valueOf(options), Boolean.valueOf(swap)
/*     */           }); 
/* 497 */     this.dc.writeI32(options, getRfpBuffer(), this.offset + 12, swap);
/*     */     
/* 499 */     if (Trace.isOn) {
/* 500 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V3", "setOptions(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMsgIdReservation(int msgIdReservation, boolean swap) {
/* 511 */     if (Trace.isOn)
/* 512 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V3", "setMsgIdReservation(int,boolean)", new Object[] {
/* 513 */             Integer.valueOf(msgIdReservation), 
/* 514 */             Boolean.valueOf(swap)
/*     */           }); 
/* 516 */     this.dc.writeI32(msgIdReservation, getRfpBuffer(), this.offset + 20, swap);
/*     */     
/* 518 */     if (Trace.isOn) {
/* 519 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V3", "setMsgIdReservation(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDeliveryDelay(long deliveryDelay, boolean swap) {
/* 530 */     if (Trace.isOn) {
/* 531 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V3", "setDeliveryDelay(long,boolean)", new Object[] {
/* 532 */             Long.valueOf(deliveryDelay), 
/* 533 */             Boolean.valueOf(swap)
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 540 */     this.dc.writeI32((int)(deliveryDelay >> 32L), getRfpBuffer(), this.offset + 24, swap);
/* 541 */     this.dc.writeI32((int)(deliveryDelay & 0xFFFFFFFFFFFFFFFFL), getRfpBuffer(), this.offset + 28, swap);
/*     */     
/* 543 */     if (Trace.isOn) {
/* 544 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V3", "setDeliveryDelay(long,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMsgOffset() {
/* 555 */     int traceRet1 = this.offset + getStructSize();
/* 556 */     if (Trace.isOn) {
/* 557 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V3", "getMsgOffset()", "getter", 
/* 558 */           Integer.valueOf(traceRet1));
/*     */     }
/* 560 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpPUTSPIIN_V3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */