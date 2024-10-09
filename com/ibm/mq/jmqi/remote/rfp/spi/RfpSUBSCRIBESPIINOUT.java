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
/*     */ public abstract class RfpSUBSCRIBESPIINOUT
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpSUBSCRIBESPIINOUT.java";
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIINOUT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpSUBSCRIBESPIINOUT.java");
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
/*  49 */   private static final byte[] rfpVB_ID_SUBSCRIBE_INOUT = new byte[] { 83, 80, 66, 85 };
/*     */   
/*  51 */   private static final byte[] rfpVB_ID_SUBSCRIBE_INOUT_ASCII = new byte[] { 83, 80, 66, 85 };
/*     */   
/*  53 */   private static final byte[] rfpVB_ID_SUBSCRIBE_INOUT_EBCDIC = new byte[] { -30, -41, -62, -28 };
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpSUBSCRIBESPIINOUT(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
/*  58 */     super(env, buffer, offset, spiVersion);
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
/*     */   public static RfpSUBSCRIBESPIINOUT getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion) {
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIINOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  76 */             Integer.valueOf(offset), Integer.valueOf(targetVersion) });
/*     */     }
/*  78 */     RfpSUBSCRIBESPIINOUT result = (RfpSUBSCRIBESPIINOUT)remoteSession.getSpiStruct(7, 0);
/*     */     
/*  80 */     if (result == null) {
/*  81 */       switch (targetVersion) {
/*     */ 
/*     */         
/*     */         case 0:
/*  85 */           result = new RfpSUBSCRIBESPIINOUT_V0(env, buffer, offset);
/*     */           break;
/*     */         
/*     */         default:
/*  89 */           result = new RfpSUBSCRIBESPIINOUT_V1(env, buffer, offset); break;
/*     */       } 
/*  91 */       remoteSession.putSpiStruct(7, 0, result);
/*     */     } else {
/*     */       
/*  94 */       result.setRfpBuffer(buffer);
/*  95 */       result.setRfpOffset(offset);
/*     */     } 
/*     */ 
/*     */     
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIINOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", result);
/*     */     }
/*     */     
/* 103 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 109 */     initEyecatcher(rfpVB_ID_SUBSCRIBE_INOUT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpSUBSCRIBESPIINOUT", "getStructSize()", "getter", 
/* 119 */           Integer.valueOf(12));
/*     */     }
/* 121 */     return 12;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpSUBSCRIBESPIINOUT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */