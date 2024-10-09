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
/*     */ public abstract class RfpGETSPIINOUT
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpGETSPIINOUT.java";
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIINOUT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpGETSPIINOUT.java");
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
/*     */   public RfpGETSPIINOUT(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
/*  56 */     super(env, buffer, offset, spiVersion);
/*     */   }
/*     */   
/*  59 */   private static final byte[] rfpVB_ID_GET_INOUT = new byte[] { 83, 80, 71, 85 };
/*  60 */   private static final byte[] rfpVB_ID_GET_INOUT_ASCII = new byte[] { 83, 80, 71, 85 };
/*  61 */   private static final byte[] rfpVB_ID_GET_INOUT_EBCDIC = new byte[] { -30, -41, -57, -28 };
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
/*     */   public static RfpGETSPIINOUT getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion, int msgDescSize, int getMsgOptsSize) {
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIINOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int,int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  81 */             Integer.valueOf(offset), Integer.valueOf(targetVersion), 
/*  82 */             Integer.valueOf(msgDescSize), Integer.valueOf(getMsgOptsSize) });
/*     */     }
/*  84 */     RfpGETSPIINOUT result = (RfpGETSPIINOUT)remoteSession.getSpiStruct(3, 0);
/*     */     
/*  86 */     if (result == null) {
/*  87 */       switch (targetVersion) {
/*     */         case 1:
/*  89 */           result = new RfpGETSPIINOUT_V1(env, buffer, offset, msgDescSize, getMsgOptsSize);
/*     */           break;
/*     */         
/*     */         default:
/*  93 */           result = new RfpGETSPIINOUT_V2(env, buffer, offset, msgDescSize, getMsgOptsSize); break;
/*     */       } 
/*  95 */       remoteSession.putSpiStruct(3, 0, result);
/*     */     } else {
/*     */       
/*  98 */       result.setRfpBuffer(buffer);
/*  99 */       result.setRfpOffset(offset);
/* 100 */       result.setMsgDescSize(msgDescSize);
/* 101 */       result.setGetMsgOptsSize(getMsgOptsSize);
/*     */     } 
/*     */ 
/*     */     
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIINOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int,int,int)", result);
/*     */     }
/*     */     
/* 109 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 116 */     initEyecatcher(rfpVB_ID_GET_INOUT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkID() {
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIINOUT", "checkID()");
/*     */     }
/* 126 */     boolean traceRet1 = ((this.buffer[this.offset] == rfpVB_ID_GET_INOUT_ASCII[0] && this.buffer[this.offset + 1] == rfpVB_ID_GET_INOUT_ASCII[1] && this.buffer[this.offset + 2] == rfpVB_ID_GET_INOUT_ASCII[2] && this.buffer[this.offset + 3] == rfpVB_ID_GET_INOUT_ASCII[3]) || (this.buffer[this.offset] == rfpVB_ID_GET_INOUT_EBCDIC[0] && this.buffer[this.offset + 1] == rfpVB_ID_GET_INOUT_EBCDIC[1] && this.buffer[this.offset + 2] == rfpVB_ID_GET_INOUT_EBCDIC[2] && this.buffer[this.offset + 3] == rfpVB_ID_GET_INOUT_EBCDIC[3]));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpGETSPIINOUT", "checkID()", 
/*     */ 
/*     */ 
/*     */           
/* 141 */           Boolean.valueOf(traceRet1));
/*     */     }
/*     */     
/* 144 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public abstract int getMsgDescOffset();
/*     */   
/*     */   public abstract int getGetMsgOptsOffset();
/*     */   
/*     */   protected abstract void setGetMsgOptsSize(int paramInt);
/*     */   
/*     */   protected abstract void setMsgDescSize(int paramInt);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpGETSPIINOUT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */