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
/*     */ class RfpGETSPIINOUT_V1
/*     */   extends RfpGETSPIINOUT
/*     */ {
/*     */   private static final int MSG_DESC_OFFSET = 12;
/*     */   
/*     */   static {
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIINOUT_V1", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpGETSPIINOUT.java");
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
/* 185 */   private int structSize = 0;
/* 186 */   private int getMsgOptsOffset = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpGETSPIINOUT_V1(JmqiEnvironment env, byte[] buffer, int offset, int msgDescSize, int getMsgOptsSize) {
/* 195 */     this(env, buffer, offset, msgDescSize, getMsgOptsSize, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpGETSPIINOUT_V1(JmqiEnvironment env, byte[] buffer, int offset, int msgDescSize, int getMsgOptsSize, int spiVersion) {
/* 206 */     super(env, buffer, offset, spiVersion);
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIINOUT_V1", "<init>(JmqiEnvironment,byte [ ],int,int,int,int)", new Object[] { env, buffer, 
/*     */             
/* 210 */             Integer.valueOf(offset), Integer.valueOf(msgDescSize), Integer.valueOf(getMsgOptsSize), 
/* 211 */             Integer.valueOf(spiVersion) });
/*     */     }
/* 213 */     setMsgDescSize(msgDescSize);
/* 214 */     setGetMsgOptsSize(getMsgOptsSize);
/*     */ 
/*     */ 
/*     */     
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIINOUT_V1", "<init>(JmqiEnvironment,byte [ ],int,int,int,int)");
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
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIINOUT_V1", "getStructSize()", "getter", 
/* 232 */           Integer.valueOf(this.structSize));
/*     */     }
/* 234 */     return this.structSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMsgDescOffset() {
/* 242 */     int traceRet1 = this.offset + 12;
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIINOUT_V1", "getMsgDescOffset()", "getter", 
/* 245 */           Integer.valueOf(traceRet1));
/*     */     }
/* 247 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGetMsgOptsOffset() {
/* 255 */     int traceRet1 = this.offset + this.getMsgOptsOffset;
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIINOUT_V1", "getGetMsgOptsOffset()", "getter", 
/* 258 */           Integer.valueOf(traceRet1));
/*     */     }
/* 260 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setGetMsgOptsSize(int getMsgOptsSize) {
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIINOUT_V1", "setGetMsgOptsSize(int)", "setter", 
/* 267 */           Integer.valueOf(getMsgOptsSize));
/*     */     }
/* 269 */     this.structSize = this.getMsgOptsOffset + getMsgOptsSize;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setMsgDescSize(int msgDescSize) {
/* 274 */     if (Trace.isOn) {
/* 275 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIINOUT_V1", "setMsgDescSize(int)", "setter", 
/* 276 */           Integer.valueOf(msgDescSize));
/*     */     }
/* 278 */     this.getMsgOptsOffset = 12 + msgDescSize;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpGETSPIINOUT_V1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */