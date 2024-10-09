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
/*     */ 
/*     */ public abstract class RfpNOTIFYSPIIN
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpNOTIFYSPIIN.java";
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIIN", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpNOTIFYSPIIN.java");
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
/*     */   protected RfpNOTIFYSPIIN(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
/*  52 */     super(env, buffer, offset, spiVersion);
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
/*     */   public static RfpNOTIFYSPIIN getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion) {
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIIN", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  70 */             Integer.valueOf(offset), Integer.valueOf(targetVersion) });
/*     */     }
/*  72 */     RfpNOTIFYSPIIN result = (RfpNOTIFYSPIIN)remoteSession.getSpiStruct(11, 1);
/*     */     
/*  74 */     if (result == null) {
/*  75 */       switch (targetVersion) {
/*     */       
/*     */       } 
/*     */       
/*  79 */       result = new RfpNOTIFYSPIIN_V1(env, buffer, offset);
/*     */       
/*  81 */       remoteSession.putSpiStruct(11, 1, result);
/*     */     } else {
/*     */       
/*  84 */       result.setRfpBuffer(buffer);
/*  85 */       result.setRfpOffset(offset);
/*     */     } 
/*     */ 
/*     */     
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIIN", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", result);
/*     */     }
/*     */     
/*  93 */     return result;
/*     */   }
/*     */   
/*  96 */   private static final byte[] rfpVB_ID_NOTIFY_IN = new byte[] { 83, 80, 78, 73 };
/*     */   
/*  98 */   private static final byte[] rfpVB_ID_NOTIFY_IN_ASCII = new byte[] { 83, 80, 78, 73 };
/*     */   
/* 100 */   private static final byte[] rfpVB_ID_NOTIFY_IN_EBCDIC = new byte[] { -30, -41, -43, -55 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 106 */     initEyecatcher(rfpVB_ID_NOTIFY_IN);
/*     */   }
/*     */   
/*     */   public abstract void setOptions(int paramInt, boolean paramBoolean);
/*     */   
/*     */   public abstract void setReason(int paramInt, boolean paramBoolean);
/*     */   
/*     */   public abstract void setConnectionId(byte[] paramArrayOfbyte);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpNOTIFYSPIIN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */