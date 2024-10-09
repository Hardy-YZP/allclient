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
/*     */ class RfpRESERVESPIIN_V1
/*     */   extends RfpRESERVESPIIN
/*     */ {
/*     */   private static final int TAG_SIZE_OFFSET = 12;
/*     */   private static final int TAG_RESERVATION_OFFSET = 16;
/*     */   public static final int SIZE_CURRENT = 20;
/*     */   public static final int SIZE_V1 = 20;
/*     */   
/*     */   static {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIIN_V1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpRESERVESPIIN.java");
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
/*     */   protected RfpRESERVESPIIN_V1(JmqiEnvironment env, byte[] buffer, int offset) {
/* 140 */     super(env, buffer, offset, 1);
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
/*     */   public void setTagSize(int tagSize, boolean swap) {
/* 156 */     if (Trace.isOn)
/* 157 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIIN_V1", "setTagSize(int,boolean)", new Object[] {
/* 158 */             Integer.valueOf(tagSize), Boolean.valueOf(swap)
/*     */           }); 
/* 160 */     this.dc.writeI32(tagSize, this.buffer, this.offset + 12, swap);
/*     */ 
/*     */     
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIIN_V1", "setTagSize(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTagReservation(int tagReservation, boolean swap) {
/* 173 */     if (Trace.isOn)
/* 174 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIIN_V1", "setTagReservation(int,boolean)", new Object[] {
/* 175 */             Integer.valueOf(tagReservation), 
/* 176 */             Boolean.valueOf(swap)
/*     */           }); 
/* 178 */     this.dc.writeI32(tagReservation, this.buffer, this.offset + 16, swap);
/*     */ 
/*     */     
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIIN_V1", "setTagReservation(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 193 */     int traceRet1 = 20;
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIIN_V1", "getStructSize()", "getter", 
/* 196 */           Integer.valueOf(traceRet1));
/*     */     }
/* 198 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpRESERVESPIIN_V1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */