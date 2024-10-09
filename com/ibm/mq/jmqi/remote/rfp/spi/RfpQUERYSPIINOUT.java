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
/*     */ public abstract class RfpQUERYSPIINOUT
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpQUERYSPIINOUT.java";
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIINOUT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpQUERYSPIINOUT.java");
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
/*  49 */   private static final byte[] rfpVB_ID_QUERY_INOUT = new byte[] { 83, 80, 81, 85 };
/*     */   
/*  51 */   private static final byte[] rfpVB_ID_QUERY_INOUT_ASCII = new byte[] { 83, 80, 81, 85 };
/*     */   
/*  53 */   private static final byte[] rfpVB_ID_QUERY_INOUT_EBCDIC = new byte[] { -30, -41, -40, -28 };
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpQUERYSPIINOUT(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
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
/*     */   public static RfpQUERYSPIINOUT getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion) {
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIINOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  76 */             Integer.valueOf(offset), Integer.valueOf(targetVersion) });
/*     */     }
/*  78 */     RfpQUERYSPIINOUT result = (RfpQUERYSPIINOUT)remoteSession.getSpiStruct(1, 0);
/*     */     
/*  80 */     if (result == null) {
/*  81 */       switch (targetVersion) {
/*     */ 
/*     */         
/*     */         case 0:
/*  85 */           result = new RfpQUERYSPIINOUT_V0(env, buffer, offset);
/*     */           break;
/*     */ 
/*     */         
/*     */         default:
/*  90 */           result = new RfpQUERYSPIINOUT_V1(env, buffer, offset); break;
/*     */       } 
/*  92 */       remoteSession.putSpiStruct(1, 0, result);
/*     */     }
/*     */     else {
/*     */       
/*  96 */       result.setRfpBuffer(buffer);
/*  97 */       result.setRfpOffset(offset);
/*     */     } 
/*     */ 
/*     */     
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIINOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", result);
/*     */     }
/*     */     
/* 105 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 111 */     initEyecatcher(rfpVB_ID_QUERY_INOUT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructSize() {
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.data(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIINOUT", "getStructSize()", "getter", 
/* 121 */           Integer.valueOf(12));
/*     */     }
/* 123 */     return 12;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpQUERYSPIINOUT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */