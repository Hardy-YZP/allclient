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
/*     */ class RfpRESERVESPIOUT_V1
/*     */   extends RfpRESERVESPIOUT
/*     */ {
/*     */   private static final int ACTUAL_RESERVATION_OFFSET = 12;
/*     */   private static final int TAG_INCREMENT_OFFSET_OFFSET = 16;
/*     */   public static final int SIZE_TO_BASE_TAG = 20;
/*     */   
/*     */   static {
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIOUT_V1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpRESERVESPIOUT.java");
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
/*     */   protected RfpRESERVESPIOUT_V1(JmqiEnvironment env, byte[] buffer, int offset) {
/* 170 */     super(env, buffer, offset, 1);
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
/*     */   public int getActualReservation(boolean swap) {
/* 182 */     if (Trace.isOn)
/* 183 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIOUT_V1", "getActualReservation(boolean)", new Object[] {
/* 184 */             Boolean.valueOf(swap)
/*     */           }); 
/* 186 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 12, swap);
/*     */ 
/*     */     
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIOUT_V1", "getActualReservation(boolean)", 
/* 191 */           Integer.valueOf(traceRet1));
/*     */     }
/* 193 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTagIncrementOffset(boolean swap) {
/* 199 */     if (Trace.isOn)
/* 200 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIOUT_V1", "getTagIncrementOffset(boolean)", new Object[] {
/* 201 */             Boolean.valueOf(swap)
/*     */           }); 
/* 203 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 16, swap);
/*     */ 
/*     */     
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIOUT_V1", "getTagIncrementOffset(boolean)", 
/* 208 */           Integer.valueOf(traceRet1));
/*     */     }
/* 210 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 218 */     int traceRet1 = 20;
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIOUT_V1", "getStructSize()", "getter", 
/* 221 */           Integer.valueOf(traceRet1));
/*     */     }
/* 223 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBaseReservationTagOffset() {
/* 231 */     int traceRet1 = this.offset + getStructSize();
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIOUT_V1", "getBaseReservationTagOffset()", "getter", 
/* 234 */           Integer.valueOf(traceRet1));
/*     */     }
/* 236 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpRESERVESPIOUT_V1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */