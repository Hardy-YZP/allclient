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
/*     */ class RfpOPENSPIIN_V1
/*     */   extends RfpOPENSPIIN
/*     */ {
/*     */   protected static final int OPTIONS_OFFSET = 12;
/*     */   
/*     */   static {
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN_V1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpOPENSPIIN.java");
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
/* 165 */   protected int odOffset = 0;
/* 166 */   protected int structSize = 0;
/*     */ 
/*     */   
/*     */   protected RfpOPENSPIIN_V1(JmqiEnvironment env, byte[] buffer, int offset) {
/* 170 */     super(env, buffer, offset, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected RfpOPENSPIIN_V1(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
/* 175 */     super(env, buffer, offset, spiVersion);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN_V1", "getStructSize()", "getter", 
/* 183 */           Integer.valueOf(this.structSize));
/*     */     }
/* 185 */     return this.structSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOdOffset() {
/* 193 */     int traceRet1 = this.offset + this.odOffset;
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN_V1", "getOdOffset()", "getter", 
/* 196 */           Integer.valueOf(traceRet1));
/*     */     }
/* 198 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptionsOffset() {
/* 206 */     int traceRet1 = this.offset + 12;
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN_V1", "getOptionsOffset()", "getter", 
/* 209 */           Integer.valueOf(traceRet1));
/*     */     }
/* 211 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOptionsSize(int optionsSize) {
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN_V1", "setOptionsSize(int)", "setter", 
/* 218 */           Integer.valueOf(optionsSize));
/*     */     }
/* 220 */     this.odOffset = 12 + optionsSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOdSize(int odSize) {
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN_V1", "setOdSize(int)", "setter", 
/* 227 */           Integer.valueOf(odSize));
/*     */     }
/* 229 */     this.structSize = this.odOffset + odSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPolicyErrorQueueOffset() {
/* 234 */     int traceRet1 = -1;
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN_V1", "getPolicyErrorQueueOffset()", "getter", 
/* 237 */           Integer.valueOf(traceRet1));
/*     */     }
/* 239 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPolicyDataOffsetOffset() {
/* 244 */     int traceRet1 = -1;
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN_V1", "getPolicyDataOffsetOffset()", "getter", 
/* 247 */           Integer.valueOf(traceRet1));
/*     */     }
/* 249 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPolicyDataLengthOffset() {
/* 254 */     int traceRet1 = -1;
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN_V1", "getPolicyDataLengthOffset()", "getter", 
/* 257 */           Integer.valueOf(traceRet1));
/*     */     }
/* 259 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpOPENSPIIN_V1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */