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
/*     */ public abstract class RfpRESERVESPIOUT
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpRESERVESPIOUT.java";
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIOUT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpRESERVESPIOUT.java");
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
/*     */   protected RfpRESERVESPIOUT(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
/*  50 */     super(env, buffer, offset, spiVersion);
/*     */   }
/*     */   
/*  53 */   private static final byte[] rfpVB_ID_RESERVE_OUT = new byte[] { 83, 80, 82, 79 };
/*  54 */   private static final byte[] rfpVB_ID_RESERVE_OUT_ASCII = new byte[] { 83, 80, 82, 79 };
/*  55 */   private static final byte[] rfpVB_ID_RESERVE_OUT_EBCDIC = new byte[] { -30, -41, -39, -42 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/*  61 */     initEyecatcher(rfpVB_ID_RESERVE_OUT);
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
/*     */   public static RfpRESERVESPIOUT getInstance(JmqiEnvironment env, RemoteSession session, byte[] buffer, int offset, int targetVersion) {
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", new Object[] { env, session, buffer, 
/*     */             
/*  79 */             Integer.valueOf(offset), Integer.valueOf(targetVersion) });
/*     */     }
/*  81 */     RfpRESERVESPIOUT result = (RfpRESERVESPIOUT)session.getSpiStruct(6, 2);
/*     */     
/*  83 */     if (result == null) {
/*  84 */       switch (targetVersion) {
/*     */       
/*     */       } 
/*     */ 
/*     */       
/*  89 */       result = new RfpRESERVESPIOUT_V1(env, buffer, offset);
/*     */       
/*  91 */       session.putSpiStruct(6, 2, result);
/*     */     } else {
/*     */       
/*  94 */       result.setRfpBuffer(buffer);
/*  95 */       result.setRfpOffset(offset);
/*     */     } 
/*     */ 
/*     */     
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", result);
/*     */     }
/*     */     
/* 103 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkID() {
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIOUT", "checkID()");
/*     */     }
/* 112 */     boolean traceRet1 = ((this.buffer[this.offset] == rfpVB_ID_RESERVE_OUT_ASCII[0] && this.buffer[this.offset + 1] == rfpVB_ID_RESERVE_OUT_ASCII[1] && this.buffer[this.offset + 2] == rfpVB_ID_RESERVE_OUT_ASCII[2] && this.buffer[this.offset + 3] == rfpVB_ID_RESERVE_OUT_ASCII[3]) || (this.buffer[this.offset] == rfpVB_ID_RESERVE_OUT_EBCDIC[0] && this.buffer[this.offset + 1] == rfpVB_ID_RESERVE_OUT_EBCDIC[1] && this.buffer[this.offset + 2] == rfpVB_ID_RESERVE_OUT_EBCDIC[2] && this.buffer[this.offset + 3] == rfpVB_ID_RESERVE_OUT_EBCDIC[3]));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpRESERVESPIOUT", "checkID()", 
/*     */ 
/*     */ 
/*     */           
/* 127 */           Boolean.valueOf(traceRet1));
/*     */     }
/*     */     
/* 130 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public abstract int getBaseReservationTagOffset();
/*     */   
/*     */   public abstract int getActualReservation(boolean paramBoolean);
/*     */   
/*     */   public abstract int getTagIncrementOffset(boolean paramBoolean);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpRESERVESPIOUT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */