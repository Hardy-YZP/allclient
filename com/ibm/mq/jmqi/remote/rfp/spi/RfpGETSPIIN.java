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
/*     */ public abstract class RfpGETSPIIN
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpGETSPIIN.java";
/*     */   
/*     */   static {
/*  34 */     if (Trace.isOn) {
/*  35 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIIN", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpGETSPIIN.java");
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
/*     */   protected RfpGETSPIIN(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
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
/*     */   public static RfpGETSPIIN getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion) {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIIN", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  66 */             Integer.valueOf(offset), Integer.valueOf(targetVersion) });
/*     */     }
/*  68 */     RfpGETSPIIN result = (RfpGETSPIIN)remoteSession.getSpiStruct(3, 1);
/*     */     
/*  70 */     if (result == null) {
/*  71 */       switch (targetVersion) {
/*     */         case 1:
/*  73 */           result = new RfpGETSPIIN_V1(env, buffer, offset);
/*     */           break;
/*     */         
/*     */         default:
/*  77 */           result = new RfpGETSPIIN_V2(env, buffer, offset); break;
/*     */       } 
/*  79 */       remoteSession.putSpiStruct(3, 1, result);
/*     */     } else {
/*  81 */       result.setRfpBuffer(buffer);
/*  82 */       result.setRfpOffset(offset);
/*     */     } 
/*     */     
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIIN", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", result);
/*     */     }
/*     */     
/*  89 */     return result;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   private static final byte[] rfpVB_ID_GET_IN = new byte[] { 83, 80, 71, 73 };
/*     */   
/* 119 */   private static final byte[] rfpVB_ID_GET_IN_ASCII = new byte[] { 83, 80, 71, 73 };
/*     */   
/* 121 */   private static final byte[] rfpVB_ID_GET_IN_EBCDIC = new byte[] { -30, -41, -57, -55 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 127 */     initEyecatcher(rfpVB_ID_GET_IN);
/*     */   }
/*     */   
/*     */   public abstract void setOptions(int paramInt, boolean paramBoolean);
/*     */   
/*     */   public abstract void setMaxMsgLength(int paramInt, boolean paramBoolean);
/*     */   
/*     */   public abstract void setBatchInterval(int paramInt, boolean paramBoolean);
/*     */   
/*     */   public abstract void setBatchSize(int paramInt, boolean paramBoolean);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpGETSPIIN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */