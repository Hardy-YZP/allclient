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
/*     */ public abstract class RfpRESERVESPIIN
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpRESERVESPIIN.java";
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIIN", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpRESERVESPIIN.java");
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
/*     */   protected RfpRESERVESPIIN(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
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
/*     */   public static RfpRESERVESPIIN getInstance(JmqiEnvironment env, RemoteSession session, byte[] buffer, int offset, int targetVersion) {
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIIN", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", new Object[] { env, session, buffer, 
/*     */             
/*  68 */             Integer.valueOf(offset), Integer.valueOf(targetVersion) });
/*     */     }
/*  70 */     RfpRESERVESPIIN result = (RfpRESERVESPIIN)session.getSpiStruct(6, 1);
/*     */     
/*  72 */     if (result == null) {
/*  73 */       switch (targetVersion) {
/*     */       
/*     */       } 
/*     */ 
/*     */       
/*  78 */       result = new RfpRESERVESPIIN_V1(env, buffer, offset);
/*     */       
/*  80 */       session.putSpiStruct(6, 1, result);
/*     */     } else {
/*     */       
/*  83 */       result.setRfpBuffer(buffer);
/*  84 */       result.setRfpOffset(offset);
/*     */     } 
/*     */ 
/*     */     
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIIN", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", result);
/*     */     }
/*     */     
/*  92 */     return result;
/*     */   }
/*     */   
/*  95 */   private static final byte[] rfpVB_ID_RESERVE_IN = new byte[] { 83, 80, 82, 73 };
/*     */   
/*  97 */   private static final byte[] rfpVB_ID_RESERVE_IN_ASCII = new byte[] { 83, 80, 82, 73 };
/*     */   
/*  99 */   private static final byte[] rfpVB_ID_RESERVE_IN_EBCDIC = new byte[] { -30, -41, -39, -55 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 105 */     initEyecatcher(rfpVB_ID_RESERVE_IN);
/*     */   }
/*     */   
/*     */   public abstract void setTagSize(int paramInt, boolean paramBoolean);
/*     */   
/*     */   public abstract void setTagReservation(int paramInt, boolean paramBoolean);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpRESERVESPIIN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */