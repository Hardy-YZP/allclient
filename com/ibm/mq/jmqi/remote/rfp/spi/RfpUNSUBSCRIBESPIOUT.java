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
/*     */ public abstract class RfpUNSUBSCRIBESPIOUT
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpUNSUBSCRIBESPIOUT.java";
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIOUT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpUNSUBSCRIBESPIOUT.java");
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
/*     */   protected RfpUNSUBSCRIBESPIOUT(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
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
/*     */   public static RfpUNSUBSCRIBESPIOUT getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion, int spiUsdSize) {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  69 */             Integer.valueOf(offset), Integer.valueOf(targetVersion), 
/*  70 */             Integer.valueOf(spiUsdSize) });
/*     */     }
/*  72 */     RfpUNSUBSCRIBESPIOUT result = (RfpUNSUBSCRIBESPIOUT)remoteSession.getSpiStruct(8, 2);
/*     */     
/*  74 */     if (result == null) {
/*  75 */       switch (targetVersion) {
/*     */       
/*     */       } 
/*     */ 
/*     */       
/*  80 */       result = new RfpUNSUBSCRIBESPIOUT_V1(env, buffer, offset, spiUsdSize);
/*     */       
/*  82 */       remoteSession.putSpiStruct(8, 2, result);
/*     */     } else {
/*     */       
/*  85 */       result.setRfpBuffer(buffer);
/*  86 */       result.setRfpOffset(offset);
/*  87 */       result.setSpiUsdSize(spiUsdSize);
/*     */     } 
/*     */ 
/*     */     
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int,int)", result);
/*     */     }
/*     */     
/*  95 */     return result;
/*     */   }
/*     */   
/*  98 */   private static final byte[] rfpVB_ID_UNSUBSCRIBE_OUT = new byte[] { 83, 80, 85, 79 };
/*  99 */   private static final byte[] rfpVB_ID_UNSUBSCRIBE_OUT_ASCII = new byte[] { 83, 80, 85, 79 };
/* 100 */   private static final byte[] rfpVB_ID_UNSUBSCRIBE_OUT_EBCDIC = new byte[] { -30, -41, -28, -42 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 106 */     initEyecatcher(rfpVB_ID_UNSUBSCRIBE_OUT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkID() {
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIOUT", "checkID()");
/*     */     }
/* 115 */     boolean traceRet1 = ((this.buffer[this.offset] == rfpVB_ID_UNSUBSCRIBE_OUT_ASCII[0] && this.buffer[this.offset + 1] == rfpVB_ID_UNSUBSCRIBE_OUT_ASCII[1] && this.buffer[this.offset + 2] == rfpVB_ID_UNSUBSCRIBE_OUT_ASCII[2] && this.buffer[this.offset + 3] == rfpVB_ID_UNSUBSCRIBE_OUT_ASCII[3]) || (this.buffer[this.offset] == rfpVB_ID_UNSUBSCRIBE_OUT_EBCDIC[0] && this.buffer[this.offset + 1] == rfpVB_ID_UNSUBSCRIBE_OUT_EBCDIC[1] && this.buffer[this.offset + 2] == rfpVB_ID_UNSUBSCRIBE_OUT_EBCDIC[2] && this.buffer[this.offset + 3] == rfpVB_ID_UNSUBSCRIBE_OUT_EBCDIC[3]));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpUNSUBSCRIBESPIOUT", "checkID()", 
/*     */ 
/*     */ 
/*     */           
/* 130 */           Boolean.valueOf(traceRet1));
/*     */     }
/*     */     
/* 133 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public abstract int getSpiUsdOffset();
/*     */   
/*     */   protected abstract void setSpiUsdSize(int paramInt);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpUNSUBSCRIBESPIOUT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */