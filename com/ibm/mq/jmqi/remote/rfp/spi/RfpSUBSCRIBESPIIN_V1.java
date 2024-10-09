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
/*     */ class RfpSUBSCRIBESPIIN_V1
/*     */   extends RfpSUBSCRIBESPIIN
/*     */ {
/*     */   private static final int HSUB_OFFSET = 12;
/*     */   private static final int SPISD_OFFSET = 16;
/*     */   private int mqsdOffset;
/*     */   private int structSize;
/*     */   
/*     */   static {
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIIN_V1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpSUBSCRIBESPIIN.java");
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
/*     */   protected RfpSUBSCRIBESPIIN_V1(JmqiEnvironment env, byte[] buffer, int offset) {
/* 157 */     super(env, buffer, offset, 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     this.mqsdOffset = 0;
/* 164 */     this.structSize = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHSub(int hSub, boolean swap) {
/* 171 */     if (Trace.isOn)
/* 172 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIIN_V1", "setHSub(int,boolean)", new Object[] {
/* 173 */             Integer.valueOf(hSub), Boolean.valueOf(swap)
/*     */           }); 
/* 175 */     this.dc.writeI32(hSub, getRfpBuffer(), this.offset + 12, swap);
/*     */ 
/*     */     
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIIN_V1", "setHSub(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMqsdOffset() {
/* 190 */     int traceRet1 = this.offset + this.mqsdOffset;
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIIN_V1", "getMqsdOffset()", "getter", 
/* 193 */           Integer.valueOf(traceRet1));
/*     */     }
/* 195 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpiSdOffset() {
/* 203 */     int traceRet1 = this.offset + 16;
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIIN_V1", "getSpiSdOffset()", "getter", 
/* 206 */           Integer.valueOf(traceRet1));
/*     */     }
/* 208 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setlpiSdSize(int lpiSdSize) {
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIIN_V1", "setlpiSdSize(int)", "setter", 
/* 215 */           Integer.valueOf(lpiSdSize));
/*     */     }
/* 217 */     this.mqsdOffset = 16 + lpiSdSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setmqSdSize(int mqSdSize) {
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIIN_V1", "setmqSdSize(int)", "setter", 
/* 224 */           Integer.valueOf(mqSdSize));
/*     */     }
/* 226 */     this.structSize = this.mqsdOffset + mqSdSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIIN_V1", "getStructSize()", "getter", 
/* 236 */           Integer.valueOf(this.structSize));
/*     */     }
/* 238 */     return this.structSize;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpSUBSCRIBESPIIN_V1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */