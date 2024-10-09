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
/*     */ public abstract class RfpGETSPIOUT
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpGETSPIOUT.java";
/*     */   
/*     */   static {
/*  34 */     if (Trace.isOn) {
/*  35 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpGETSPIOUT.java");
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
/*     */   protected RfpGETSPIOUT(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
/*  48 */     super(env, buffer, offset, spiVersion);
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
/*     */   public static RfpGETSPIOUT getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion) {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  66 */             Integer.valueOf(offset), Integer.valueOf(targetVersion) });
/*     */     }
/*  68 */     RfpGETSPIOUT result = (RfpGETSPIOUT)remoteSession.getSpiStruct(3, 2);
/*     */     
/*  70 */     if (result == null) {
/*  71 */       switch (targetVersion) {
/*     */         case 1:
/*  73 */           result = new RfpGETSPIOUT_V1(env, buffer, offset);
/*     */           break;
/*     */         
/*     */         default:
/*  77 */           result = new RfpGETSPIOUT_V2(env, buffer, offset); break;
/*     */       } 
/*  79 */       remoteSession.putSpiStruct(3, 2, result);
/*     */     } else {
/*  81 */       result.setRfpBuffer(buffer);
/*  82 */       result.setRfpOffset(offset);
/*     */     } 
/*     */     
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", result);
/*     */     }
/*     */     
/*  89 */     return result;
/*     */   }
/*     */   
/*  92 */   private static final byte[] rfpVB_ID_GET_OUT = new byte[] { 83, 80, 71, 79 };
/*  93 */   private static final byte[] rfpVB_ID_GET_OUT_ASCII = new byte[] { 83, 80, 71, 79 };
/*  94 */   private static final byte[] rfpVB_ID_GET_OUT_EBCDIC = new byte[] { -30, -41, -57, -42 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 100 */     initEyecatcher(rfpVB_ID_GET_OUT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkID() {
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT", "checkID()");
/*     */     }
/* 109 */     boolean traceRet1 = ((this.buffer[this.offset] == rfpVB_ID_GET_OUT_ASCII[0] && this.buffer[this.offset + 1] == rfpVB_ID_GET_OUT_ASCII[1] && this.buffer[this.offset + 2] == rfpVB_ID_GET_OUT_ASCII[2] && this.buffer[this.offset + 3] == rfpVB_ID_GET_OUT_ASCII[3]) || (this.buffer[this.offset] == rfpVB_ID_GET_OUT_EBCDIC[0] && this.buffer[this.offset + 1] == rfpVB_ID_GET_OUT_EBCDIC[1] && this.buffer[this.offset + 2] == rfpVB_ID_GET_OUT_EBCDIC[2] && this.buffer[this.offset + 3] == rfpVB_ID_GET_OUT_EBCDIC[3]));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT", "checkID()", 
/*     */ 
/*     */ 
/*     */           
/* 122 */           Boolean.valueOf(traceRet1));
/*     */     }
/*     */     
/* 125 */     return traceRet1;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMsgOffset() {
/* 150 */     int traceRet1 = this.offset + getStructSize();
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIOUT", "getMsgOffset()", "getter", 
/* 153 */           Integer.valueOf(traceRet1));
/*     */     }
/* 155 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public abstract int getGetStatus(boolean paramBoolean);
/*     */   
/*     */   public abstract int getMsgLength(boolean paramBoolean);
/*     */   
/*     */   public abstract int getInherited(boolean paramBoolean);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpGETSPIOUT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */