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
/*     */ public abstract class RfpPUTSPIIN
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpPUTSPIIN.java";
/*     */   
/*     */   static {
/*  34 */     if (Trace.isOn) {
/*  35 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpPUTSPIIN.java");
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
/*     */   public RfpPUTSPIIN(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
/*  54 */     super(env, buffer, offset, spiVersion);
/*     */   }
/*     */   
/*  57 */   private static final byte[] rfpVB_ID_PUT_IN = new byte[] { 83, 80, 80, 73 };
/*     */   
/*  59 */   private static final byte[] rfpVB_ID_PUT_IN_ASCII = new byte[] { 83, 80, 80, 73 };
/*     */   
/*  61 */   private static final byte[] rfpVB_ID_PUT_IN_EBCDIC = new byte[] { -30, -41, -41, -55 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static RfpPUTSPIIN getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion) {
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  79 */             Integer.valueOf(offset), Integer.valueOf(targetVersion) });
/*     */     }
/*  81 */     RfpPUTSPIIN result = (RfpPUTSPIIN)remoteSession.getSpiStruct(2, 1);
/*     */     
/*  83 */     if (result == null) {
/*  84 */       switch (targetVersion) {
/*     */ 
/*     */         
/*     */         case 1:
/*  88 */           result = new RfpPUTSPIIN_V1(env, buffer, offset);
/*     */           break;
/*     */         case 2:
/*  91 */           result = new RfpPUTSPIIN_V2(env, buffer, offset);
/*     */           break;
/*     */         
/*     */         default:
/*  95 */           result = new RfpPUTSPIIN_V3(env, buffer, offset); break;
/*     */       } 
/*  97 */       remoteSession.putSpiStruct(2, 1, result);
/*     */     } else {
/*     */       
/* 100 */       result.setRfpBuffer(buffer);
/* 101 */       result.setRfpOffset(offset);
/*     */     } 
/*     */     
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpPUTSPIIN", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", result);
/*     */     }
/*     */     
/* 108 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 114 */     initEyecatcher(rfpVB_ID_PUT_IN);
/*     */   }
/*     */   
/*     */   public abstract void setOptions(int paramInt, boolean paramBoolean);
/*     */   
/*     */   public abstract void setMessageLength(int paramInt, boolean paramBoolean);
/*     */   
/*     */   public abstract void setMsgIdReservation(int paramInt, boolean paramBoolean);
/*     */   
/*     */   public abstract void setDeliveryDelay(long paramLong, boolean paramBoolean);
/*     */   
/*     */   public abstract int getMsgOffset();
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpPUTSPIIN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */