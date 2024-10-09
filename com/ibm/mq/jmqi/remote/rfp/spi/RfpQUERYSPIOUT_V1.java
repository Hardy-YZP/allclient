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
/*     */ class RfpQUERYSPIOUT_V1
/*     */   extends RfpQUERYSPIOUT
/*     */ {
/*     */   static {
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIOUT_V1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpQUERYSPIOUT.java");
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
/* 167 */   private static int ARRAYSIZE_OFFSET = 12;
/* 168 */   private static int ARRAY_OFFSET = ARRAYSIZE_OFFSET + 4;
/*     */ 
/*     */   
/*     */   protected RfpQUERYSPIOUT_V1(JmqiEnvironment env, byte[] buffer, int offset) {
/* 172 */     super(env, buffer, offset, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getArraySize(boolean swap) {
/* 181 */     if (Trace.isOn)
/* 182 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIOUT_V1", "getArraySize(boolean)", new Object[] {
/* 183 */             Boolean.valueOf(swap)
/*     */           }); 
/* 185 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + ARRAYSIZE_OFFSET, swap);
/*     */ 
/*     */     
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIOUT_V1", "getArraySize(boolean)", 
/* 190 */           Integer.valueOf(traceRet1));
/*     */     }
/* 192 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RfpVerbArray[] getArray(boolean swap) {
/* 202 */     if (Trace.isOn)
/* 203 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIOUT_V1", "getArray(boolean)", new Object[] {
/* 204 */             Boolean.valueOf(swap)
/*     */           }); 
/* 206 */     int arraySize = getArraySize(swap);
/* 207 */     RfpVerbArray[] result = new RfpVerbArray[arraySize];
/* 208 */     int arrayOffset = this.offset + ARRAY_OFFSET;
/* 209 */     for (int i = 0; i < arraySize; i++) {
/* 210 */       result[i] = new RfpVerbArray(this.env);
/* 211 */       arrayOffset = result[i].readFromBuffer(this.buffer, arrayOffset, swap);
/*     */     } 
/*     */ 
/*     */     
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIOUT_V1", "getArray(boolean)", result);
/*     */     }
/*     */     
/* 219 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIOUT_V1", "getStructSize()", "getter", 
/* 229 */           Integer.valueOf(ARRAY_OFFSET));
/*     */     }
/* 231 */     return ARRAY_OFFSET;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpQUERYSPIOUT_V1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */