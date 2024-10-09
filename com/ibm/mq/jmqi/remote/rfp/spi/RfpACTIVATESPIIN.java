/*     */ package com.ibm.mq.jmqi.remote.rfp.spi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.remote.impl.RemoteSession;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
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
/*     */ public abstract class RfpACTIVATESPIIN
/*     */   extends RfpVERBSTRUCT
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpACTIVATESPIIN.java";
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIIN", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/spi/RfpACTIVATESPIIN.java");
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
/*     */   protected RfpACTIVATESPIIN(JmqiEnvironment env, byte[] buffer, int offset, int spiVersion) {
/*  54 */     super(env, buffer, offset, spiVersion);
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
/*     */   public static RfpACTIVATESPIIN getInstance(JmqiEnvironment env, RemoteSession remoteSession, byte[] buffer, int offset, int targetVersion) {
/*  69 */     if (Trace.isOn) {
/*  70 */       Trace.entry("com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIIN", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", new Object[] { env, remoteSession, buffer, 
/*     */             
/*  72 */             Integer.valueOf(offset), Integer.valueOf(targetVersion) });
/*     */     }
/*  74 */     RfpACTIVATESPIIN result = (RfpACTIVATESPIIN)remoteSession.getSpiStruct(4, 1);
/*     */     
/*  76 */     if (result == null) {
/*  77 */       switch (targetVersion) {
/*     */       
/*     */       } 
/*  80 */       result = new RfpACTIVATESPIIN_V1(env, buffer, offset);
/*     */       
/*  82 */       remoteSession.putSpiStruct(4, 1, result);
/*     */     } else {
/*     */       
/*  85 */       result.setRfpBuffer(buffer);
/*  86 */       result.setRfpOffset(offset);
/*     */     } 
/*     */ 
/*     */     
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.exit("com.ibm.mq.jmqi.remote.rfp.spi.RfpACTIVATESPIIN", "getInstance(JmqiEnvironment,RemoteSession,byte [ ],int,int)", result);
/*     */     }
/*     */     
/*  94 */     return result;
/*     */   }
/*     */   
/*  97 */   static final byte[] rfpVB_ID_ACTIVATE_IN = new byte[] { 83, 80, 65, 73 };
/*  98 */   static final byte[] rfpVB_ID_ACTIVATE_IN_ASCII = new byte[] { 83, 80, 65, 73 };
/*  99 */   static final byte[] rfpVB_ID_ACTIVATE_IN_EBCDIC = new byte[] { -30, -41, -63, -55 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEyecatcher() {
/* 105 */     initEyecatcher(rfpVB_ID_ACTIVATE_IN);
/*     */   }
/*     */   
/*     */   public abstract void setOptions(int paramInt, boolean paramBoolean);
/*     */   
/*     */   public abstract void setQName(String paramString, JmqiCodepage paramJmqiCodepage, JmqiTls paramJmqiTls) throws JmqiException;
/*     */   
/*     */   public abstract void setQMgrName(String paramString, JmqiCodepage paramJmqiCodepage, JmqiTls paramJmqiTls) throws JmqiException;
/*     */   
/*     */   public abstract void setMsgId(byte[] paramArrayOfbyte);
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\spi\RfpACTIVATESPIIN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */