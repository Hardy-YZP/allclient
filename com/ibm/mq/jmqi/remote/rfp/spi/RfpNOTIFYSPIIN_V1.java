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
/*     */ class RfpNOTIFYSPIIN_V1
/*     */   extends RfpNOTIFYSPIIN
/*     */ {
/*     */   private static final int OPTIONS_OFFSET = 12;
/*     */   private static final int REASON_OFFSET = 16;
/*     */   private static final int CONNECTIONID_OFFSET = 20;
/*     */   
/*     */   static {
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIIN_V1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpNOTIFYSPIIN.java");
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
/*     */   protected RfpNOTIFYSPIIN_V1(JmqiEnvironment env, byte[] buffer, int offset) {
/* 146 */     super(env, buffer, offset, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options, boolean swap) {
/* 156 */     if (Trace.isOn)
/* 157 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIIN_V1", "setOptions(int,boolean)", new Object[] {
/* 158 */             Integer.valueOf(options), Boolean.valueOf(swap)
/*     */           }); 
/* 160 */     this.dc.writeI32(options, this.buffer, this.offset + 12, swap);
/*     */ 
/*     */     
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIIN_V1", "setOptions(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReason(int reason, boolean swap) {
/* 175 */     if (Trace.isOn)
/* 176 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIIN_V1", "setReason(int,boolean)", new Object[] {
/* 177 */             Integer.valueOf(reason), Boolean.valueOf(swap)
/*     */           }); 
/* 179 */     this.dc.writeI32(reason, this.buffer, this.offset + 16, swap);
/*     */ 
/*     */     
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIIN_V1", "setReason(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnectionId(byte[] connectionId) {
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIIN_V1", "setConnectionId(byte [ ])", "setter", connectionId);
/*     */     }
/*     */     
/* 196 */     System.arraycopy(connectionId, 0, this.buffer, this.offset + 20, 24);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 205 */     int traceRet1 = 44;
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIIN_V1", "getStructSize()", "getter", 
/* 208 */           Integer.valueOf(traceRet1));
/*     */     }
/* 210 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpNOTIFYSPIIN_V1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */