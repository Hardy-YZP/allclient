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
/*     */ public abstract class RfpUNSUBSCRIBESPIIN
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpUNSUBSCRIBESPIIN.java";
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIIN", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpUNSUBSCRIBESPIIN.java");
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
/*     */   protected RfpUNSUBSCRIBESPIIN(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
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
/*     */   public static RfpUNSUBSCRIBESPIIN getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion, int spiUsdSize) {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIIN", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  69 */             Integer.valueOf(offset), Integer.valueOf(targetVersion), 
/*  70 */             Integer.valueOf(spiUsdSize) });
/*     */     }
/*  72 */     RfpUNSUBSCRIBESPIIN result = (RfpUNSUBSCRIBESPIIN)remoteSession.getSpiStruct(8, 1);
/*     */     
/*  74 */     if (result == null) {
/*  75 */       switch (targetVersion) {
/*     */       
/*     */       } 
/*     */ 
/*     */       
/*  80 */       result = new RfpUNSUBSCRIBESPIIN_V1(env, buffer, offset, spiUsdSize);
/*     */       
/*  82 */       remoteSession.putSpiStruct(8, 1, result);
/*     */     } else {
/*     */       
/*  85 */       result.setRfpBuffer(buffer);
/*  86 */       result.setRfpOffset(offset);
/*  87 */       result.setSpiUsdSize(spiUsdSize);
/*     */     } 
/*     */ 
/*     */     
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIIN", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int,int)", result);
/*     */     }
/*     */     
/*  95 */     return result;
/*     */   }
/*     */   
/*  98 */   private static final byte[] rfpVB_ID_UNSUBSCRIBE_IN = new byte[] { 83, 80, 85, 73 };
/*     */   
/* 100 */   private static final byte[] rfpVB_ID_UNSUBSCRIBE_IN_ASCII = new byte[] { 83, 80, 85, 73 };
/*     */   
/* 102 */   private static final byte[] rfpVB_ID_UNSUBSCRIBE_IN_EBCDIC = new byte[] { -30, -41, -28, -55 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 108 */     initEyecatcher(rfpVB_ID_UNSUBSCRIBE_IN);
/*     */   }
/*     */   
/*     */   public abstract int getSpiUsdOffset();
/*     */   
/*     */   protected abstract void setSpiUsdSize(int paramInt);
/*     */   
/*     */   public abstract int getStructSize();
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpUNSUBSCRIBESPIIN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */