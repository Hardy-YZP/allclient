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
/*     */ class RfpGETSPIOUT_V1
/*     */   extends RfpGETSPIOUT
/*     */ {
/*     */   protected static final int GETSTATUS_OFFSET = 12;
/*     */   protected static final int MSGLENGTH_OFFSET = 16;
/*     */   
/*     */   static {
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT_V1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpGETSPIOUT.java");
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
/*     */   protected RfpGETSPIOUT_V1(JmqiEnvironment env, byte[] buffer, int offset) {
/* 184 */     super(env, buffer, offset, 1);
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT_V1", "<init>(JmqiEnvironment,byte [ ],int)", new Object[] { env, buffer, 
/* 187 */             Integer.valueOf(offset) });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT_V1", "<init>(JmqiEnvironment,byte [ ],int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpGETSPIOUT_V1(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
/* 200 */     super(env, buffer, offset, spiVersion);
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT_V1", "<init>(JmqiEnvironment,byte [ ],int,int)", new Object[] { env, buffer, 
/*     */             
/* 204 */             Integer.valueOf(offset), Integer.valueOf(spiVersion) });
/*     */     }
/*     */ 
/*     */     
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT_V1", "<init>(JmqiEnvironment,byte [ ],int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGetStatus(boolean swap) {
/* 220 */     if (Trace.isOn)
/* 221 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT_V1", "getGetStatus(boolean)", new Object[] {
/* 222 */             Boolean.valueOf(swap)
/*     */           }); 
/* 224 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 12, swap);
/*     */     
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT_V1", "getGetStatus(boolean)", 
/* 228 */           Integer.valueOf(traceRet1));
/*     */     }
/* 230 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMsgLength(boolean swap) {
/* 238 */     if (Trace.isOn)
/* 239 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT_V1", "getMsgLength(boolean)", new Object[] {
/* 240 */             Boolean.valueOf(swap)
/*     */           }); 
/* 242 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 16, swap);
/*     */     
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT_V1", "getMsgLength(boolean)", 
/* 246 */           Integer.valueOf(traceRet1));
/*     */     }
/* 248 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInherited(boolean swap) {
/* 256 */     if (Trace.isOn)
/* 257 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT_V1", "getInherited(boolean)", new Object[] {
/* 258 */             Boolean.valueOf(swap)
/*     */           }); 
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT_V1", "getInherited(boolean)", 
/* 262 */           Integer.valueOf(0));
/*     */     }
/* 264 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 272 */     int traceRet1 = 20;
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT_V1", "getStructSize()", "getter", 
/* 275 */           Integer.valueOf(traceRet1));
/*     */     }
/* 277 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpGETSPIOUT_V1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */