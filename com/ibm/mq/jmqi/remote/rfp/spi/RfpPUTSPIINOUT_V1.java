/*     */ package com.ibm.mq.jmqi.remote.rfp.spi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
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
/*     */ class RfpPUTSPIINOUT_V1
/*     */   extends RfpPUTSPIINOUT
/*     */ {
/*     */   private static final int MSG_DESC_OFFSET = 12;
/*     */   
/*     */   static {
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIINOUT_V1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpPUTSPIINOUT.java");
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
/* 184 */   private int structSize = 0;
/* 185 */   private int putMsgOptsOffset = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpPUTSPIINOUT_V1(JmqiEnvironment env, byte[] buffer, int offset, int msgDescSize, int putMsgOptsSize) throws JmqiException {
/* 195 */     super(env, buffer, offset, 1);
/* 196 */     this.putMsgOptsOffset = 12 + msgDescSize;
/* 197 */     this.structSize = this.putMsgOptsOffset + putMsgOptsSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIINOUT_V1", "getStructSize()", "getter", 
/* 207 */           Integer.valueOf(this.structSize));
/*     */     }
/* 209 */     return this.structSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMsgDescOffset() {
/* 217 */     int traceRet1 = this.offset + 12;
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIINOUT_V1", "getMsgDescOffset()", "getter", 
/* 220 */           Integer.valueOf(traceRet1));
/*     */     }
/* 222 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPutMsgOptsOffset() {
/* 230 */     int traceRet1 = this.offset + this.putMsgOptsOffset;
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIINOUT_V1", "getPutMsgOptsOffset()", "getter", 
/* 233 */           Integer.valueOf(traceRet1));
/*     */     }
/* 235 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setMsgDescSize(int msgDescSize) {
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIINOUT_V1", "setMsgDescSize(int)", "setter", 
/* 242 */           Integer.valueOf(msgDescSize));
/*     */     }
/* 244 */     this.putMsgOptsOffset = 12 + msgDescSize;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setPutMsgOptsSize(int putMsgOptsSize) {
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIINOUT_V1", "setPutMsgOptsSize(int)", "setter", 
/* 251 */           Integer.valueOf(putMsgOptsSize));
/*     */     }
/* 253 */     this.structSize = this.putMsgOptsOffset + putMsgOptsSize;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpPUTSPIINOUT_V1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */