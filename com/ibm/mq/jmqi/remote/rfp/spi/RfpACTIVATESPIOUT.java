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
/*     */ public abstract class RfpACTIVATESPIOUT
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpACTIVATESPIOUT.java";
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIOUT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpACTIVATESPIOUT.java");
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
/*  49 */   static final byte[] rfpVB_ID_ACTIVATE_OUT = new byte[] { 83, 80, 65, 79 };
/*  50 */   static final byte[] rfpVB_ID_ACTIVATE_OUT_ASCII = new byte[] { 83, 80, 65, 79 };
/*  51 */   static final byte[] rfpVB_ID_ACTIVATE_OUT_EBCDIC = new byte[] { -30, -41, -63, -42 };
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpACTIVATESPIOUT(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
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
/*     */   public static RfpACTIVATESPIOUT getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion) {
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  74 */             Integer.valueOf(offset), Integer.valueOf(targetVersion) });
/*     */     }
/*  76 */     RfpACTIVATESPIOUT result = (RfpACTIVATESPIOUT)remoteSession.getSpiStruct(4, 2);
/*     */     
/*  78 */     if (result == null) {
/*  79 */       switch (targetVersion) {
/*     */ 
/*     */         
/*     */         case 0:
/*  83 */           result = new RfpACTIVATESPIOUT_V0(env, buffer, offset);
/*     */           break;
/*     */         
/*     */         default:
/*  87 */           result = new RfpACTIVATESPIOUT_V1(env, buffer, offset); break;
/*     */       } 
/*  89 */       remoteSession.putSpiStruct(4, 2, result);
/*     */     } else {
/*     */       
/*  92 */       result.setRfpBuffer(buffer);
/*  93 */       result.setRfpOffset(offset);
/*     */     } 
/*     */ 
/*     */     
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", result);
/*     */     }
/*     */     
/* 101 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 107 */     initEyecatcher(rfpVB_ID_ACTIVATE_OUT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIOUT", "getStructSize()", "getter", 
/* 117 */           Integer.valueOf(12));
/*     */     }
/* 119 */     return 12;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpACTIVATESPIOUT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */