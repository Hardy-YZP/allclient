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
/*     */ public abstract class RfpQUERYSPIOUT
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpQUERYSPIOUT.java";
/*     */   
/*     */   static {
/*  37 */     if (Trace.isOn) {
/*  38 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIOUT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpQUERYSPIOUT.java");
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
/*  49 */   private static final byte[] rfpVB_ID_QUERY_OUT = new byte[] { 83, 80, 81, 79 };
/*  50 */   private static final byte[] rfpVB_ID_QUERY_OUT_ASCII = new byte[] { 83, 80, 81, 79 };
/*  51 */   private static final byte[] rfpVB_ID_QUERY_OUT_EBCDIC = new byte[] { -30, -41, -40, -42 };
/*     */ 
/*     */ 
/*     */   
/*     */   protected RfpQUERYSPIOUT(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
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
/*     */   public static RfpQUERYSPIOUT getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion) {
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  74 */             Integer.valueOf(offset), Integer.valueOf(targetVersion) });
/*     */     }
/*  76 */     RfpQUERYSPIOUT result = (RfpQUERYSPIOUT)remoteSession.getSpiStruct(1, 2);
/*     */     
/*  78 */     if (result == null) {
/*  79 */       switch (targetVersion) {
/*     */       
/*     */       } 
/*     */ 
/*     */       
/*  84 */       result = new RfpQUERYSPIOUT_V1(env, buffer, offset);
/*     */       
/*  86 */       remoteSession.putSpiStruct(1, 2, result);
/*     */     }
/*     */     else {
/*     */       
/*  90 */       result.setRfpBuffer(buffer);
/*  91 */       result.setRfpOffset(offset);
/*     */     } 
/*     */ 
/*     */     
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", result);
/*     */     }
/*     */     
/*  99 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 105 */     initEyecatcher(rfpVB_ID_QUERY_OUT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkID() {
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIOUT", "checkID()");
/*     */     }
/* 114 */     boolean traceRet1 = ((this.buffer[this.offset] == rfpVB_ID_QUERY_OUT_ASCII[0] && this.buffer[this.offset + 1] == rfpVB_ID_QUERY_OUT_ASCII[1] && this.buffer[this.offset + 2] == rfpVB_ID_QUERY_OUT_ASCII[2] && this.buffer[this.offset + 3] == rfpVB_ID_QUERY_OUT_ASCII[3]) || (this.buffer[this.offset] == rfpVB_ID_QUERY_OUT_EBCDIC[0] && this.buffer[this.offset + 1] == rfpVB_ID_QUERY_OUT_EBCDIC[1] && this.buffer[this.offset + 2] == rfpVB_ID_QUERY_OUT_EBCDIC[2] && this.buffer[this.offset + 3] == rfpVB_ID_QUERY_OUT_EBCDIC[3]));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpQUERYSPIOUT", "checkID()", 
/*     */ 
/*     */ 
/*     */           
/* 129 */           Boolean.valueOf(traceRet1));
/*     */     }
/*     */     
/* 132 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public abstract int getArraySize(boolean paramBoolean);
/*     */   
/*     */   public abstract RfpVerbArray[] getArray(boolean paramBoolean);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpQUERYSPIOUT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */