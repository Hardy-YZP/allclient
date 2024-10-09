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
/*     */ public abstract class RfpACTIVATESPIINOUT
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpACTIVATESPIINOUT.java";
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIINOUT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpACTIVATESPIINOUT.java");
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
/*  49 */   static final byte[] rfpVB_ID_ACTIVATE_INOUT = new byte[] { 83, 80, 65, 85 };
/*  50 */   static final byte[] rfpVB_ID_ACTIVATE_INOUT_ASCII = new byte[] { 83, 80, 65, 85 };
/*  51 */   static final byte[] rfpVB_ID_ACTIVATE_INOUT_EBCDIC = new byte[] { -30, -41, -63, -28 };
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpACTIVATESPIINOUT(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
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
/*     */   public static RfpACTIVATESPIINOUT getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion) {
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIINOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  74 */             Integer.valueOf(offset), Integer.valueOf(targetVersion) });
/*     */     }
/*  76 */     RfpACTIVATESPIINOUT result = (RfpACTIVATESPIINOUT)remoteSession.getSpiStruct(4, 0);
/*     */     
/*  78 */     if (result == null) {
/*  79 */       switch (targetVersion) {
/*     */ 
/*     */         
/*     */         case 0:
/*  83 */           result = new RfpACTIVATESPIINOUT_V0(env, buffer, offset);
/*     */           break;
/*     */         
/*     */         default:
/*  87 */           result = new RfpACTIVATESPIINOUT_V1(env, buffer, offset); break;
/*     */       } 
/*  89 */       remoteSession.putSpiStruct(4, 0, result);
/*     */     } else {
/*     */       
/*  92 */       result.setRfpBuffer(buffer);
/*  93 */       result.setRfpOffset(offset);
/*     */     } 
/*     */ 
/*     */     
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIINOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", result);
/*     */     }
/*     */     
/* 101 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 107 */     initEyecatcher(rfpVB_ID_ACTIVATE_INOUT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIINOUT", "getStructSize()", "getter", 
/* 117 */           Integer.valueOf(12));
/*     */     }
/* 119 */     return 12;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpACTIVATESPIINOUT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */