/*     */ package com.ibm.mq.jmqi.remote.rfp.spi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
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
/*     */ public abstract class RfpPUTSPIINOUT
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpPUTSPIINOUT.java";
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIINOUT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpPUTSPIINOUT.java");
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
/*     */   public RfpPUTSPIINOUT(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
/*  57 */     super(env, buffer, offset, spiVersion);
/*     */   }
/*     */   
/*  60 */   private static final byte[] rfpVB_ID_PUT_INOUT = new byte[] { 83, 80, 80, 85 };
/*  61 */   private static final byte[] rfpVB_ID_PUT_INOUT_ASCII = new byte[] { 83, 80, 80, 85 };
/*  62 */   private static final byte[] rfpVB_ID_PUT_INOUT_EBCDIC = new byte[] { -30, -41, -41, -28 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RfpPUTSPIINOUT getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion, int msgDescSize, int putMsgOptsSize) throws JmqiException {
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIINOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int,int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  84 */             Integer.valueOf(offset), Integer.valueOf(targetVersion), 
/*  85 */             Integer.valueOf(msgDescSize), Integer.valueOf(putMsgOptsSize) });
/*     */     }
/*  87 */     RfpPUTSPIINOUT result = (RfpPUTSPIINOUT)remoteSession.getSpiStruct(2, 0);
/*     */     
/*  89 */     if (result == null) {
/*  90 */       switch (targetVersion) {
/*     */       
/*     */       } 
/*     */       
/*  94 */       result = new RfpPUTSPIINOUT_V1(env, buffer, offset, msgDescSize, putMsgOptsSize);
/*     */       
/*  96 */       remoteSession.putSpiStruct(2, 0, result);
/*     */     } else {
/*     */       
/*  99 */       result.setRfpBuffer(buffer);
/* 100 */       result.setRfpOffset(offset);
/* 101 */       result.setMsgDescSize(msgDescSize);
/* 102 */       result.setPutMsgOptsSize(putMsgOptsSize);
/*     */     } 
/*     */ 
/*     */     
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIINOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int,int,int)", result);
/*     */     }
/*     */     
/* 110 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 116 */     initEyecatcher(rfpVB_ID_PUT_INOUT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkID() {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIINOUT", "checkID()");
/*     */     }
/* 125 */     boolean traceRet1 = ((this.buffer[this.offset] == rfpVB_ID_PUT_INOUT_ASCII[0] && this.buffer[this.offset + 1] == rfpVB_ID_PUT_INOUT_ASCII[1] && this.buffer[this.offset + 2] == rfpVB_ID_PUT_INOUT_ASCII[2] && this.buffer[this.offset + 3] == rfpVB_ID_PUT_INOUT_ASCII[3]) || (this.buffer[this.offset] == rfpVB_ID_PUT_INOUT_EBCDIC[0] && this.buffer[this.offset + 1] == rfpVB_ID_PUT_INOUT_EBCDIC[1] && this.buffer[this.offset + 2] == rfpVB_ID_PUT_INOUT_EBCDIC[2] && this.buffer[this.offset + 3] == rfpVB_ID_PUT_INOUT_EBCDIC[3]));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIINOUT", "checkID()", 
/*     */ 
/*     */ 
/*     */           
/* 140 */           Boolean.valueOf(traceRet1));
/*     */     }
/*     */     
/* 143 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public abstract int getMsgDescOffset();
/*     */   
/*     */   public abstract int getPutMsgOptsOffset();
/*     */   
/*     */   protected abstract void setMsgDescSize(int paramInt);
/*     */   
/*     */   protected abstract void setPutMsgOptsSize(int paramInt);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpPUTSPIINOUT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */