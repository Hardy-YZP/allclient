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
/*     */ 
/*     */ public abstract class RfpPUTSPIOUT
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpPUTSPIOUT.java";
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIOUT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpPUTSPIOUT.java");
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
/*  49 */   private static final byte[] rfpVB_ID_PUT_OUT = new byte[] { 83, 80, 80, 79 };
/*  50 */   private static final byte[] rfpVB_ID_PUT_OUT_ASCII = new byte[] { 83, 80, 80, 79 };
/*  51 */   private static final byte[] rfpVB_ID_PUT_OUT_EBCDIC = new byte[] { -30, -41, -41, -42 };
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpPUTSPIOUT(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
/*  56 */     super(env, buffer, offset, spiVersion);
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
/*     */   public static RfpPUTSPIOUT getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion) {
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  74 */             Integer.valueOf(offset), Integer.valueOf(targetVersion) });
/*     */     }
/*  76 */     RfpPUTSPIOUT result = (RfpPUTSPIOUT)remoteSession.getSpiStruct(2, 2);
/*     */     
/*  78 */     if (result == null) {
/*  79 */       switch (targetVersion) {
/*     */ 
/*     */         
/*     */         case 0:
/*  83 */           result = new RfpPUTSPIOUT_V0(env, buffer, offset);
/*     */           break;
/*     */         
/*     */         default:
/*  87 */           result = new RfpPUTSPIOUT_V1(env, buffer, offset); break;
/*     */       } 
/*  89 */       remoteSession.putSpiStruct(2, 2, result);
/*     */     } else {
/*     */       
/*  92 */       result.setRfpBuffer(buffer);
/*  93 */       result.setRfpOffset(offset);
/*     */     } 
/*     */ 
/*     */     
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", result);
/*     */     }
/*     */     
/* 101 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 107 */     initEyecatcher(rfpVB_ID_PUT_OUT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkID() {
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIOUT", "checkID()");
/*     */     }
/* 116 */     boolean traceRet1 = ((this.buffer[this.offset] == rfpVB_ID_PUT_OUT_ASCII[0] && this.buffer[this.offset + 1] == rfpVB_ID_PUT_OUT_ASCII[1] && this.buffer[this.offset + 2] == rfpVB_ID_PUT_OUT_ASCII[2] && this.buffer[this.offset + 3] == rfpVB_ID_PUT_OUT_ASCII[3]) || (this.buffer[this.offset] == rfpVB_ID_PUT_OUT_EBCDIC[0] && this.buffer[this.offset + 1] == rfpVB_ID_PUT_OUT_EBCDIC[1] && this.buffer[this.offset + 2] == rfpVB_ID_PUT_OUT_EBCDIC[2] && this.buffer[this.offset + 3] == rfpVB_ID_PUT_OUT_EBCDIC[3]));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIOUT", "checkID()", 
/*     */ 
/*     */ 
/*     */           
/* 131 */           Boolean.valueOf(traceRet1));
/*     */     }
/*     */     
/* 134 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpPUTSPIOUT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */