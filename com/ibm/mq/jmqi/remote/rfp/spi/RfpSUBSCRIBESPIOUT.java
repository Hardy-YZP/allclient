/*     */ package com.ibm.mq.jmqi.remote.rfp.spi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.remote.impl.RemoteSession;
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
/*     */ public abstract class RfpSUBSCRIBESPIOUT
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpSUBSCRIBESPIOUT.java";
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIOUT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpSUBSCRIBESPIOUT.java");
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
/*     */   protected RfpSUBSCRIBESPIOUT(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
/*  50 */     super(env, buffer, offset, spiVersion);
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
/*     */   public static RfpSUBSCRIBESPIOUT getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion, int lpiSdSize, int mqSdSize) {
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int,int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  70 */             Integer.valueOf(offset), Integer.valueOf(targetVersion), 
/*  71 */             Integer.valueOf(lpiSdSize), Integer.valueOf(mqSdSize) });
/*     */     }
/*  73 */     RfpSUBSCRIBESPIOUT result = (RfpSUBSCRIBESPIOUT)remoteSession.getSpiStruct(7, 2);
/*     */     
/*  75 */     if (result == null) {
/*  76 */       switch (targetVersion) {
/*     */       
/*     */       } 
/*     */ 
/*     */       
/*  81 */       result = new RfpSUBSCRIBESPIOUT_V1(env, buffer, offset, lpiSdSize, mqSdSize);
/*     */       
/*  83 */       remoteSession.putSpiStruct(7, 2, result);
/*     */     } else {
/*     */       
/*  86 */       result.setRfpBuffer(buffer);
/*  87 */       result.setRfpOffset(offset);
/*  88 */       result.setlpiSdSize(lpiSdSize);
/*  89 */       result.setmqSdSize(mqSdSize);
/*     */     } 
/*     */ 
/*     */     
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int,int,int)", result);
/*     */     }
/*     */     
/*  97 */     return result;
/*     */   }
/*     */   
/* 100 */   private static final byte[] rfpVB_ID_SUBSCRIBE_OUT = new byte[] { 83, 80, 66, 79 };
/* 101 */   private static final byte[] rfpVB_ID_SUBSCRIBE_OUT_ASCII = new byte[] { 83, 80, 66, 79 };
/* 102 */   private static final byte[] rfpVB_ID_SUBSCRIBE_OUT_EBCDIC = new byte[] { -30, -41, -62, -42 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 108 */     initEyecatcher(rfpVB_ID_SUBSCRIBE_OUT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkID() {
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIOUT", "checkID()");
/*     */     }
/* 117 */     boolean traceRet1 = ((this.buffer[this.offset] == rfpVB_ID_SUBSCRIBE_OUT_ASCII[0] && this.buffer[this.offset + 1] == rfpVB_ID_SUBSCRIBE_OUT_ASCII[1] && this.buffer[this.offset + 2] == rfpVB_ID_SUBSCRIBE_OUT_ASCII[2] && this.buffer[this.offset + 3] == rfpVB_ID_SUBSCRIBE_OUT_ASCII[3]) || (this.buffer[this.offset] == rfpVB_ID_SUBSCRIBE_OUT_EBCDIC[0] && this.buffer[this.offset + 1] == rfpVB_ID_SUBSCRIBE_OUT_EBCDIC[1] && this.buffer[this.offset + 2] == rfpVB_ID_SUBSCRIBE_OUT_EBCDIC[2] && this.buffer[this.offset + 3] == rfpVB_ID_SUBSCRIBE_OUT_EBCDIC[3]));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIOUT", "checkID()", 
/*     */ 
/*     */ 
/*     */           
/* 132 */           Boolean.valueOf(traceRet1));
/*     */     }
/*     */     
/* 135 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public abstract int getHSub(boolean paramBoolean);
/*     */   
/*     */   public abstract int getSpiSdOffset();
/*     */   
/*     */   public abstract int getMqsdOffset();
/*     */   
/*     */   protected abstract void setmqSdSize(int paramInt);
/*     */   
/*     */   protected abstract void setlpiSdSize(int paramInt);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpSUBSCRIBESPIOUT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */