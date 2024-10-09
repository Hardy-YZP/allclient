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
/*     */ public class RfpNOTIFYSPIOUT
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpNOTIFYSPIOUT.java";
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIOUT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpNOTIFYSPIOUT.java");
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
/*     */   protected RfpNOTIFYSPIOUT(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
/*  51 */     super(env, buffer, offset, spiVersion);
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
/*     */   public static RfpNOTIFYSPIOUT getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion) {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  69 */             Integer.valueOf(offset), Integer.valueOf(targetVersion) });
/*     */     }
/*  71 */     RfpNOTIFYSPIOUT result = (RfpNOTIFYSPIOUT)remoteSession.getSpiStruct(11, 2);
/*     */     
/*  73 */     if (result == null) {
/*  74 */       switch (targetVersion) {
/*     */ 
/*     */         
/*     */         case 0:
/*  78 */           result = new RfpNOTIFYSPIOUT_V0(env, buffer, offset);
/*     */           break;
/*     */         
/*     */         default:
/*  82 */           result = new RfpNOTIFYSPIOUT_V1(env, buffer, offset); break;
/*     */       } 
/*  84 */       remoteSession.putSpiStruct(11, 2, result);
/*     */     } else {
/*     */       
/*  87 */       result.setRfpBuffer(buffer);
/*  88 */       result.setRfpOffset(offset);
/*     */     } 
/*     */ 
/*     */     
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpNOTIFYSPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", result);
/*     */     }
/*     */     
/*  96 */     return result;
/*     */   }
/*     */   
/*  99 */   private static final byte[] rfpVB_ID_NOTIFY_OUT = new byte[] { 83, 80, 78, 79 };
/*     */   
/* 101 */   private static final byte[] rfpVB_ID_NOTIFY_OUT_ASCII = new byte[] { 83, 80, 78, 79 };
/*     */   
/* 103 */   private static final byte[] rfpVB_ID_SUBSCRIBE_OUT_EBCDIC = new byte[] { -30, -41, -43, -42 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 109 */     initEyecatcher(rfpVB_ID_NOTIFY_OUT);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpNOTIFYSPIOUT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */