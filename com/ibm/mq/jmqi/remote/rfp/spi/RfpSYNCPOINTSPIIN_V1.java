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
/*     */ class RfpSYNCPOINTSPIIN_V1
/*     */   extends RfpSYNCPOINTSPIIN
/*     */ {
/*     */   private static final int OPTIONS_OFFSET = 12;
/*     */   private static final int ACTION_OFFSET = 16;
/*     */   
/*     */   static {
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpSYNCPOINTSPIIN_V1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpSYNCPOINTSPIIN.java");
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
/*     */   public RfpSYNCPOINTSPIIN_V1(JmqiEnvironment env, byte[] buffer, int offset) {
/* 151 */     super(env, buffer, offset, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options, boolean swap) {
/* 160 */     if (Trace.isOn)
/* 161 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSYNCPOINTSPIIN_V1", "setOptions(int,boolean)", new Object[] {
/* 162 */             Integer.valueOf(options), Boolean.valueOf(swap)
/*     */           }); 
/* 164 */     this.dc.writeI32(options, this.buffer, this.offset + 12, swap);
/*     */ 
/*     */     
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSYNCPOINTSPIIN_V1", "setOptions(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAction(int action, boolean swap) {
/* 177 */     if (Trace.isOn)
/* 178 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSYNCPOINTSPIIN_V1", "setAction(int,boolean)", new Object[] {
/* 179 */             Integer.valueOf(action), Boolean.valueOf(swap)
/*     */           }); 
/* 181 */     this.dc.writeI32(action, this.buffer, this.offset + 16, swap);
/*     */ 
/*     */     
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSYNCPOINTSPIIN_V1", "setAction(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 193 */     int traceRet1 = 20;
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSYNCPOINTSPIIN_V1", "getStructSize()", "getter", 
/* 196 */           Integer.valueOf(traceRet1));
/*     */     }
/* 198 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpSYNCPOINTSPIIN_V1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */