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
/*     */ public abstract class RfpOPENSPIIN
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpOPENSPIIN.java";
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpOPENSPIIN.java");
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
/*     */   protected RfpOPENSPIIN(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
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
/*     */   public static RfpOPENSPIIN getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion) {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  69 */             Integer.valueOf(offset), Integer.valueOf(targetVersion) });
/*     */     }
/*  71 */     RfpOPENSPIIN result = (RfpOPENSPIIN)remoteSession.getSpiStruct(12, 1);
/*     */     
/*  73 */     if (result == null) {
/*  74 */       switch (targetVersion) {
/*     */         case 1:
/*  76 */           result = new RfpOPENSPIIN_V1(env, buffer, offset);
/*     */           break;
/*     */         
/*     */         default:
/*  80 */           result = new RfpOPENSPIIN_V2(env, buffer, offset); break;
/*     */       } 
/*  82 */       remoteSession.putSpiStruct(12, 1, result);
/*     */     } else {
/*  84 */       result.setRfpBuffer(buffer);
/*  85 */       result.setRfpOffset(offset);
/*     */     } 
/*     */     
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIIN", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", result);
/*     */     }
/*     */     
/*  92 */     return result;
/*     */   }
/*     */   
/*  95 */   private static final byte[] rfpVB_ID_OPEN_IN = new byte[] { 83, 80, 79, 73 };
/*     */   
/*  97 */   private static final byte[] rfpVB_ID_OPEN_IN_ASCII = new byte[] { 83, 80, 79, 73 };
/*     */   
/*  99 */   private static final byte[] rfpVB_ID_OPEN_IN_EBCDIC = new byte[] { -30, -41, -42, -55 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 105 */     initEyecatcher(rfpVB_ID_OPEN_IN);
/*     */   }
/*     */   
/*     */   public abstract int getOptionsOffset();
/*     */   
/*     */   public abstract int getOdOffset();
/*     */   
/*     */   public abstract int getPolicyErrorQueueOffset();
/*     */   
/*     */   public abstract int getPolicyDataOffsetOffset();
/*     */   
/*     */   public abstract int getPolicyDataLengthOffset();
/*     */   
/*     */   public abstract void setOptionsSize(int paramInt);
/*     */   
/*     */   public abstract void setOdSize(int paramInt);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpOPENSPIIN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */