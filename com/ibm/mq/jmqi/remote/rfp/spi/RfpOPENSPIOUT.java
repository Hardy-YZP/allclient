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
/*     */ public abstract class RfpOPENSPIOUT
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpOPENSPIOUT.java";
/*     */   
/*     */   static {
/*  35 */     if (Trace.isOn) {
/*  36 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpOPENSPIOUT.java");
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
/*     */   protected RfpOPENSPIOUT(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
/*  49 */     super(env, buffer, offset, spiVersion);
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
/*     */   public static RfpOPENSPIOUT getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion, int optionsSize, int odSize) {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int,int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  69 */             Integer.valueOf(offset), Integer.valueOf(targetVersion), 
/*  70 */             Integer.valueOf(optionsSize), Integer.valueOf(odSize) });
/*     */     }
/*  72 */     RfpOPENSPIOUT result = (RfpOPENSPIOUT)remoteSession.getSpiStruct(12, 2);
/*     */     
/*  74 */     if (result == null) {
/*  75 */       switch (targetVersion) {
/*     */         case 1:
/*  77 */           result = new RfpOPENSPIOUT_V1(env, buffer, offset, optionsSize, odSize);
/*     */           break;
/*     */         
/*     */         default:
/*  81 */           result = new RfpOPENSPIOUT_V2(env, buffer, offset, optionsSize, odSize); break;
/*     */       } 
/*  83 */       remoteSession.putSpiStruct(12, 2, result);
/*     */     } else {
/*  85 */       result.setRfpBuffer(buffer);
/*  86 */       result.setRfpOffset(offset);
/*  87 */       result.setOptionsSize(optionsSize);
/*  88 */       result.setOdSize(odSize);
/*     */     } 
/*     */     
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int,int,int)", result);
/*     */     }
/*     */     
/*  95 */     return result;
/*     */   }
/*     */   
/*  98 */   private static final byte[] rfpVB_ID_OPEN_OUT = new byte[] { 83, 80, 79, 79 };
/*  99 */   private static final byte[] rfpVB_ID_OPEN_OUT_ASCII = new byte[] { 83, 80, 79, 79 };
/* 100 */   private static final byte[] rfpVB_ID_OPEN_OUT_EBCDIC = new byte[] { -30, -41, -42, -42 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 106 */     initEyecatcher(rfpVB_ID_OPEN_OUT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkID() {
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT", "checkID()");
/*     */     }
/* 115 */     boolean traceRet1 = ((this.buffer[this.offset] == rfpVB_ID_OPEN_OUT_ASCII[0] && this.buffer[this.offset + 1] == rfpVB_ID_OPEN_OUT_ASCII[1] && this.buffer[this.offset + 2] == rfpVB_ID_OPEN_OUT_ASCII[2] && this.buffer[this.offset + 3] == rfpVB_ID_OPEN_OUT_ASCII[3]) || (this.buffer[this.offset] == rfpVB_ID_OPEN_OUT_EBCDIC[0] && this.buffer[this.offset + 1] == rfpVB_ID_OPEN_OUT_EBCDIC[1] && this.buffer[this.offset + 2] == rfpVB_ID_OPEN_OUT_EBCDIC[2] && this.buffer[this.offset + 3] == rfpVB_ID_OPEN_OUT_EBCDIC[3]));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.spi.RfpOPENSPIOUT", "checkID()", 
/*     */ 
/*     */ 
/*     */           
/* 128 */           Boolean.valueOf(traceRet1));
/*     */     }
/*     */     
/* 131 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public abstract int getOptionsOffset();
/*     */   
/*     */   public abstract int getOdOffset();
/*     */   
/*     */   public abstract int getPolicyErrorQueueOffset();
/*     */   
/*     */   public abstract int getPolicyDataOffsetOffset();
/*     */   
/*     */   public abstract int getPolicyDataLengthOffset();
/*     */   
/*     */   protected abstract void setOptionsSize(int paramInt);
/*     */   
/*     */   protected abstract void setOdSize(int paramInt);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpOPENSPIOUT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */