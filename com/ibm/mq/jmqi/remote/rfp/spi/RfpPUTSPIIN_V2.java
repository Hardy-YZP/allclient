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
/*     */ class RfpPUTSPIIN_V2
/*     */   extends RfpPUTSPIIN
/*     */ {
/*     */   private static final int OPTIONS_OFFSET = 12;
/*     */   private static final int MSG_LENGTH_OFFSET = 16;
/*     */   private static final int MSGIDRESERVATION_OFFSET = 20;
/*     */   
/*     */   static {
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V2", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpPUTSPIIN.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpPUTSPIIN_V2(JmqiEnvironment env, byte[] buffer, int offset) {
/* 301 */     super(env, buffer, offset, 2);
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
/*     */   public int getStructSize() {
/* 324 */     int traceRet1 = 24;
/* 325 */     if (Trace.isOn) {
/* 326 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V2", "getStructSize()", "getter", 
/* 327 */           Integer.valueOf(traceRet1));
/*     */     }
/* 329 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMessageLength(int bufferLength, boolean swap) {
/* 337 */     if (Trace.isOn)
/* 338 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V2", "setMessageLength(int,boolean)", new Object[] {
/* 339 */             Integer.valueOf(bufferLength), 
/* 340 */             Boolean.valueOf(swap)
/*     */           }); 
/* 342 */     this.dc.writeI32(bufferLength, getRfpBuffer(), this.offset + 16, swap);
/*     */     
/* 344 */     if (Trace.isOn) {
/* 345 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V2", "setMessageLength(int,boolean)");
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
/* 356 */     if (Trace.isOn)
/* 357 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V2", "setOptions(int,boolean)", new Object[] {
/* 358 */             Integer.valueOf(options), Boolean.valueOf(swap)
/*     */           }); 
/* 360 */     this.dc.writeI32(options, getRfpBuffer(), this.offset + 12, swap);
/*     */     
/* 362 */     if (Trace.isOn) {
/* 363 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V2", "setOptions(int,boolean)");
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
/* 374 */     if (Trace.isOn)
/* 375 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V2", "setMsgIdReservation(int,boolean)", new Object[] {
/* 376 */             Integer.valueOf(msgIdReservation), 
/* 377 */             Boolean.valueOf(swap)
/*     */           }); 
/* 379 */     this.dc.writeI32(msgIdReservation, getRfpBuffer(), this.offset + 20, swap);
/*     */     
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V2", "setMsgIdReservation(int,boolean)");
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
/* 393 */     if (Trace.isOn) {
/* 394 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V2", "setDeliveryDelay(long,boolean)", new Object[] {
/* 395 */             Long.valueOf(deliveryDelay), 
/* 396 */             Boolean.valueOf(swap)
/*     */           });
/*     */     }
/*     */     
/* 400 */     if (Trace.isOn) {
/* 401 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V2", "setDeliveryDelay(long,boolean)");
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
/* 412 */     int traceRet1 = this.offset + getStructSize();
/* 413 */     if (Trace.isOn) {
/* 414 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V2", "getMsgOffset()", "getter", 
/* 415 */           Integer.valueOf(traceRet1));
/*     */     }
/* 417 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpPUTSPIIN_V2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */