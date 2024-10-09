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
/*     */ class RfpOPENSPIOUT_V1
/*     */   extends RfpOPENSPIOUT
/*     */ {
/*     */   protected static final int OPTIONS_OFFSET = 12;
/*     */   
/*     */   static {
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpOPENSPIOUT.java");
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
/* 184 */   protected int odOffset = 0;
/* 185 */   protected int structSize = 0;
/*     */   
/*     */   private void initStructSizes(int optionsSize, int odSize) {
/* 188 */     if (Trace.isOn)
/* 189 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V1", "initStructSizes(int,int)", new Object[] {
/* 190 */             Integer.valueOf(optionsSize), 
/* 191 */             Integer.valueOf(odSize)
/*     */           }); 
/* 193 */     this.odOffset = 12 + optionsSize;
/* 194 */     this.structSize = this.odOffset + odSize;
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V1", "initStructSizes(int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpOPENSPIOUT_V1(JmqiEnvironment env, byte[] buffer, int offset, int optionsSize, int odSize) {
/* 204 */     super(env, buffer, offset, 1);
/* 205 */     initStructSizes(optionsSize, odSize);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpOPENSPIOUT_V1(JmqiEnvironment env, byte[] buffer, int offset, int optionsSize, int odSize, int spiVersion) {
/* 211 */     super(env, buffer, offset, spiVersion);
/* 212 */     initStructSizes(optionsSize, odSize);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V1", "getStructSize()", "getter", 
/* 220 */           Integer.valueOf(this.structSize));
/*     */     }
/* 222 */     return this.structSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOdOffset() {
/* 230 */     int traceRet1 = this.offset + this.odOffset;
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V1", "getOdOffset()", "getter", 
/* 233 */           Integer.valueOf(traceRet1));
/*     */     }
/* 235 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptionsOffset() {
/* 243 */     int traceRet1 = this.offset + 12;
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V1", "getOptionsOffset()", "getter", 
/* 246 */           Integer.valueOf(traceRet1));
/*     */     }
/* 248 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setOptionsSize(int optionsSize) {
/* 253 */     if (Trace.isOn) {
/* 254 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V1", "setOptionsSize(int)", "setter", 
/* 255 */           Integer.valueOf(optionsSize));
/*     */     }
/* 257 */     this.odOffset = 12 + optionsSize;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setOdSize(int odSize) {
/* 262 */     if (Trace.isOn) {
/* 263 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V1", "setOdSize(int)", "setter", 
/* 264 */           Integer.valueOf(odSize));
/*     */     }
/* 266 */     this.structSize = this.odOffset + odSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPolicyErrorQueueOffset() {
/* 271 */     int traceRet1 = -1;
/* 272 */     if (Trace.isOn) {
/* 273 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V1", "getPolicyErrorQueueOffset()", "getter", 
/* 274 */           Integer.valueOf(traceRet1));
/*     */     }
/* 276 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPolicyDataOffsetOffset() {
/* 281 */     int traceRet1 = -1;
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V1", "getPolicyDataOffsetOffset()", "getter", 
/* 284 */           Integer.valueOf(traceRet1));
/*     */     }
/* 286 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPolicyDataLengthOffset() {
/* 291 */     int traceRet1 = -1;
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT_V1", "getPolicyDataLengthOffset()", "getter", 
/* 294 */           Integer.valueOf(traceRet1));
/*     */     }
/* 296 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpOPENSPIOUT_V1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */