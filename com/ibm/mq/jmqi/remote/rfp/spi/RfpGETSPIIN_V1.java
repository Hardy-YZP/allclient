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
/*     */ class RfpGETSPIIN_V1
/*     */   extends RfpGETSPIIN
/*     */ {
/*     */   protected static final int BATCHSIZE_OFFSET = 12;
/*     */   protected static final int BATCHINTERVAL_OFFSET = 16;
/*     */   protected static final int MAXMSGLENGTH_OFFSET = 20;
/*     */   
/*     */   static {
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIIN_V1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpGETSPIIN.java");
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
/*     */   
/*     */   protected RfpGETSPIIN_V1(JmqiEnvironment env, byte[] buffer, int offset) {
/* 157 */     this(env, buffer, offset, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected RfpGETSPIIN_V1(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
/* 162 */     super(env, buffer, offset, spiVersion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBatchSize(int batchSize, boolean swap) {
/* 170 */     if (Trace.isOn)
/* 171 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIIN_V1", "setBatchSize(int,boolean)", new Object[] {
/* 172 */             Integer.valueOf(batchSize), 
/* 173 */             Boolean.valueOf(swap)
/*     */           }); 
/* 175 */     this.dc.writeI32(batchSize, getRfpBuffer(), this.offset + 12, swap);
/*     */     
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIIN_V1", "setBatchSize(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBatchInterval(int batchInterval, boolean swap) {
/* 189 */     if (Trace.isOn)
/* 190 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIIN_V1", "setBatchInterval(int,boolean)", new Object[] {
/* 191 */             Integer.valueOf(batchInterval), 
/* 192 */             Boolean.valueOf(swap)
/*     */           }); 
/* 194 */     this.dc.writeI32(batchInterval, getRfpBuffer(), this.offset + 16, swap);
/*     */     
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIIN_V1", "setBatchInterval(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxMsgLength(int maxMsgLength, boolean swap) {
/* 208 */     if (Trace.isOn)
/* 209 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIIN_V1", "setMaxMsgLength(int,boolean)", new Object[] {
/* 210 */             Integer.valueOf(maxMsgLength), 
/* 211 */             Boolean.valueOf(swap)
/*     */           }); 
/* 213 */     this.dc.writeI32(maxMsgLength, getRfpBuffer(), this.offset + 20, swap);
/*     */     
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIIN_V1", "setMaxMsgLength(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options, boolean swap) {
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIIN_V1", "setOptions(int,boolean)", new Object[] {
/* 229 */             Integer.valueOf(options), Boolean.valueOf(swap)
/*     */           });
/*     */     }
/*     */     
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIIN_V1", "setOptions(int,boolean)");
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
/* 245 */     int traceRet1 = 24;
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIIN_V1", "getStructSize()", "getter", 
/* 248 */           Integer.valueOf(traceRet1));
/*     */     }
/* 250 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpGETSPIIN_V1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */