/*    */ package com.ibm.mq.jmqi.local.internal.base;
/*    */ import com.ibm.mq.jmqi.JmqiException;
/*    */ import com.ibm.mq.jmqi.handles.Hobj;
/*    */ import com.ibm.mq.jmqi.handles.Pint;
/*    */ import com.ibm.mq.jmqi.local.LocalHconn;
/*    */ 
/*    */ public class Native {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/base/Native.java";
/*    */   
/*    */   public static native String adapter_detect();
/*    */   
/*    */   public static native boolean init_conversion(boolean paramBoolean, NativeTraceHandler paramNativeTraceHandler, Pint[] paramArrayOfPint) throws JmqiException;
/*    */   
/*    */   public static native void MQBACK(boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void MQBEGIN(boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void MQCB(boolean paramBoolean, JmqiEnvironment paramJmqiEnvironment, int paramInt1, Class<?> paramClass, Object paramObject, int paramInt2, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt3, LocalHobj paramLocalHobj, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, byte[] paramArrayOfbyte5, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void MQCLOSE(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, LocalHobj paramLocalHobj, int paramInt2, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void MQCMIT(boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void MQCONN(boolean paramBoolean, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, LocalHconn paramLocalHconn, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void MQCONNX(boolean paramBoolean, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, LocalHconn paramLocalHconn, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void MQCTL(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt2, byte[] paramArrayOfbyte3, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void MQDISC(boolean paramBoolean, LocalHconn paramLocalHconn, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void jmqiDisc(int paramInt, boolean paramBoolean, LocalHconn paramLocalHconn, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void MQGET(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, int paramInt3, byte[] paramArrayOfbyte5, Pint paramPint1, Pint paramPint2, Pint paramPint3) throws JmqiException;
/*    */   
/*    */   public static native void MQINQ(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt2, int paramInt3, byte[] paramArrayOfbyte3, int paramInt4, byte[] paramArrayOfbyte4, int paramInt5, byte[] paramArrayOfbyte5, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void MQOPEN(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, int paramInt2, Hobj paramHobj, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   static {
/* 41 */     if (Trace.isOn)
/* 42 */       Trace.data("com.ibm.mq.jmqi.local.internal.base.Native", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/base/Native.java"); 
/*    */   } public static native void MQPUT(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, int paramInt3, byte[] paramArrayOfbyte5, Pint paramPint1, Pint paramPint2) throws JmqiException; public static native void jmqiPut(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, int paramInt3, byte[][] paramArrayOfbyte, int[] paramArrayOfint1, int[] paramArrayOfint2, Pint paramPint1, Pint paramPint2) throws JmqiException; public static native void MQPUT1(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, byte[] paramArrayOfbyte5, int paramInt2, byte[] paramArrayOfbyte6, Pint paramPint1, Pint paramPint2) throws JmqiException; public static native void jmqiPut1(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, byte[] paramArrayOfbyte5, int paramInt2, byte[][] paramArrayOfbyte, int[] paramArrayOfint1, int[] paramArrayOfint2, Pint paramPint1, Pint paramPint2) throws JmqiException; public static native void MQSET(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt2, int paramInt3, byte[] paramArrayOfbyte3, int paramInt4, byte[] paramArrayOfbyte4, int paramInt5, byte[] paramArrayOfbyte5, Pint paramPint1, Pint paramPint2) throws JmqiException; public static native void MQSTAT(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt2, byte[] paramArrayOfbyte3, Pint paramPint1, Pint paramPint2) throws JmqiException; public static native void MQSUB(boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, Hobj paramHobj1, Hobj paramHobj2, Pint paramPint1, Pint paramPint2) throws JmqiException; public static native void MQSUBRQ(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, Hobj paramHobj, int paramInt2, byte[] paramArrayOfbyte3, Pint paramPint1, Pint paramPint2) throws JmqiException; public static native void MQCRTMH(boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, Hmsg paramHmsg, Pint paramPint1, Pint paramPint2) throws JmqiException; public static native void MQDLTMH(boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, Hmsg paramHmsg, byte[] paramArrayOfbyte3, Pint paramPint1, Pint paramPint2) throws JmqiException; public static native void MQSETMP(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, long paramLong, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, byte[] paramArrayOfbyte5, int paramInt2, int paramInt3, byte[] paramArrayOfbyte6, Pint paramPint1, Pint paramPint2) throws JmqiException; public static native void MQINQMP(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, long paramLong, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, byte[] paramArrayOfbyte5, Pint paramPint1, int paramInt2, byte[] paramArrayOfbyte6, Pint paramPint2, Pint paramPint3, Pint paramPint4) throws JmqiException; public static native void MQDLTMP(boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, long paramLong, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, Pint paramPint1, Pint paramPint2) throws JmqiException; public static native void MQMHBUF(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, long paramLong, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, byte[] paramArrayOfbyte5, int paramInt2, byte[] paramArrayOfbyte6, Pint paramPint1, Pint paramPint2, Pint paramPint3) throws JmqiException; public static native void MQBUFMH(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, long paramLong, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, int paramInt2, byte[] paramArrayOfbyte5, Pint paramPint1, Pint paramPint2, Pint paramPint3) throws JmqiException; public static native void spiSyncPoint(boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, Pint paramPint1, Pint paramPint2) throws JmqiException; public static native void spiGet(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, byte[] paramArrayOfbyte5, int paramInt3, byte[] paramArrayOfbyte6, Pint paramPint1, Pint paramPint2, Pint paramPint3) throws JmqiException; public static native void spiPut(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, byte[] paramArrayOfbyte5, int paramInt3, byte[] paramArrayOfbyte6, Pint paramPint1, Pint paramPint2) throws JmqiException; public static native void spiActivateMessage(boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, Pint paramPint1, Pint paramPint2) throws JmqiException; public static native void lpiSpiSubscribe(boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, Hobj paramHobj1, Hobj paramHobj2, Pint paramPint1, Pint paramPint2); public static native void lpiSpiUnsubscribe(boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, Pint paramPint1, Pint paramPint2); public static native void spiConnect(boolean paramBoolean, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, LocalHconn paramLocalHconn, Pint paramPint1, Pint paramPint2) throws JmqiException; public static native void spiConnect_with_environment(boolean paramBoolean, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, LocalHconn paramLocalHconn, int paramInt, Pint paramPint1, Pint paramPint2) throws JmqiException; public static native int zstXAClose(boolean paramBoolean, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws JmqiException;
/*    */   public static native int zstXACommit(boolean paramBoolean, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws JmqiException;
/*    */   public static native int zstXAComplete(boolean paramBoolean, Pint paramPint1, Pint paramPint2, int paramInt1, int paramInt2) throws JmqiException;
/*    */   public static native int zstXAEnd(boolean paramBoolean, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws JmqiException;
/*    */   public static native int zstXAForget(boolean paramBoolean, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws JmqiException;
/*    */   public static native int zstXAOpen(boolean paramBoolean, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws JmqiException;
/*    */   public static native int zstXAOpen_tm(boolean paramBoolean, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws JmqiException;
/*    */   public static native int zstXAPrepare(boolean paramBoolean, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws JmqiException;
/*    */   public static native int zstXARecover(boolean paramBoolean, byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) throws JmqiException;
/* 52 */   public static int compID = 0;
/*    */   
/*    */   public static native int zstXARollback(boolean paramBoolean, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws JmqiException;
/*    */   
/*    */   public static native int zstXAStart(boolean paramBoolean, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws JmqiException;
/*    */   
/*    */   public static native void getMetaData(boolean paramBoolean, byte[] paramArrayOfbyte, Pint paramPint1, Pint paramPint2);
/*    */   
/*    */   public static native void vpiConvertData(boolean paramBoolean1, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, Pint paramPint1, int paramInt5, int paramInt6, Pint paramPint2, Pint paramPint3, Pint paramPint4);
/*    */   
/*    */   public static native void authenticate(boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void spiNotify(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt2, byte[] paramArrayOfbyte3, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void lpiSPIAdoptUser(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, int paramInt2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, int paramInt3, byte[] paramArrayOfbyte5, int paramInt4, byte[] paramArrayOfbyte6, byte[] paramArrayOfbyte7, Pint paramPint1, int paramInt5, byte[] paramArrayOfbyte8, byte[] paramArrayOfbyte9, int paramInt6, byte[] paramArrayOfbyte10, int paramInt7, byte[] paramArrayOfbyte11, Pint paramPint2, Pint paramPint3);
/*    */   
/*    */   public static native void lpiSPICHLAUTH(boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, Pint paramPint1, Pint paramPint2);
/*    */   
/*    */   public static native void spiOpen(boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, Hobj paramHobj, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void spiDefine(boolean paramBoolean1, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, boolean paramBoolean2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, int paramInt2, byte[] paramArrayOfbyte5, int paramInt3, byte[] paramArrayOfbyte6, int paramInt4, byte[] paramArrayOfbyte7, byte[] paramArrayOfbyte8, int paramInt5, byte[] paramArrayOfbyte9, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void spiInquire(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, int paramInt2, byte[] paramArrayOfbyte4, int paramInt3, byte[] paramArrayOfbyte5, int paramInt4, byte[] paramArrayOfbyte6, byte[] paramArrayOfbyte7, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void spiDelete(boolean paramBoolean1, int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, boolean paramBoolean2, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void lpiSPISubscriptionRequest(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, Hobj paramHobj, int paramInt2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, Pint paramPint1, Pint paramPint2) throws JmqiException;
/*    */   
/*    */   public static native void lpiSPIInquireNamedSubscribers(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, int paramInt2, byte[] paramArrayOfbyte5, Pint paramPint1, Pint paramPint2, Pint paramPint3, Pint paramPint4);
/*    */   
/*    */   public static native void lpiSPIMapCredentials(boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, byte[] paramArrayOfbyte5, byte[] paramArrayOfbyte6, byte[] paramArrayOfbyte7, byte[] paramArrayOfbyte8, byte[] paramArrayOfbyte9, Pint paramPint1, Pint paramPint2);
/*    */   
/*    */   public static native void lpiSPICheckPrivileged(boolean paramBoolean, int paramInt1, byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2, byte[] paramArrayOfbyte3, byte[] paramArrayOfbyte4, int paramInt2, Pint paramPint1, Pint paramPint2);
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\base\Native.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */