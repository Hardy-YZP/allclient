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
/*     */ class RfpPUTSPIIN_V1
/*     */   extends RfpPUTSPIIN
/*     */ {
/*     */   private static final int OPTIONS_OFFSET = 12;
/*     */   private static final int MSG_LENGTH_OFFSET = 16;
/*     */   
/*     */   static {
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpPUTSPIIN.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpPUTSPIIN_V1(JmqiEnvironment env, byte[] buffer, int offset) {
/* 169 */     super(env, buffer, offset, 1);
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
/*     */   public int getStructSize() {
/* 190 */     int traceRet1 = 20;
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V1", "getStructSize()", "getter", 
/* 193 */           Integer.valueOf(traceRet1));
/*     */     }
/* 195 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMessageLength(int bufferLength, boolean swap) {
/* 203 */     if (Trace.isOn)
/* 204 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V1", "setMessageLength(int,boolean)", new Object[] {
/* 205 */             Integer.valueOf(bufferLength), 
/* 206 */             Boolean.valueOf(swap)
/*     */           }); 
/* 208 */     this.dc.writeI32(bufferLength, getRfpBuffer(), this.offset + 16, swap);
/*     */     
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V1", "setMessageLength(int,boolean)");
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
/* 222 */     if (Trace.isOn)
/* 223 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V1", "setOptions(int,boolean)", new Object[] {
/* 224 */             Integer.valueOf(options), Boolean.valueOf(swap)
/*     */           }); 
/* 226 */     this.dc.writeI32(options, getRfpBuffer(), this.offset + 12, swap);
/*     */     
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V1", "setOptions(int,boolean)");
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
/*     */   public void setMsgIdReservation(int msgIdReservation, boolean swap) {
/* 241 */     if (Trace.isOn) {
/* 242 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V1", "setMsgIdReservation(int,boolean)", new Object[] {
/* 243 */             Integer.valueOf(msgIdReservation), 
/* 244 */             Boolean.valueOf(swap)
/*     */           });
/*     */     }
/*     */     
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V1", "setMsgIdReservation(int,boolean)");
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
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V1", "setDeliveryDelay(long,boolean)", new Object[] {
/* 262 */             Long.valueOf(deliveryDelay), 
/* 263 */             Boolean.valueOf(swap)
/*     */           });
/*     */     }
/*     */     
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V1", "setDeliveryDelay(long,boolean)");
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
/* 279 */     int traceRet1 = this.offset + getStructSize();
/* 280 */     if (Trace.isOn) {
/* 281 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN_V1", "getMsgOffset()", "getter", 
/* 282 */           Integer.valueOf(traceRet1));
/*     */     }
/* 284 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpPUTSPIIN_V1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */