/*     */ package com.ibm.mq.jmqi.remote.rfp;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
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
/*     */ public class RfpESH
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpESH.java";
/*     */   static final int ERROR_DATA_LENGTH_OFFSET = 0;
/*     */   static final int RETURN_CODE_OFFSET = 4;
/*     */   public static final int SIZE = 8;
/*     */   
/*     */   static {
/*  35 */     if (Trace.isOn) {
/*  36 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpESH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpESH.java");
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
/*     */   public RfpESH(JmqiEnvironment env, byte[] buffer, int offset) {
/*  69 */     super(env, buffer, offset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getErrorDataLength(boolean swap) {
/*  76 */     if (Trace.isOn)
/*  77 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpESH", "getErrorDataLength(boolean)", new Object[] {
/*  78 */             Boolean.valueOf(swap)
/*     */           }); 
/*  80 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 0, swap);
/*     */     
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpESH", "getErrorDataLength(boolean)", 
/*  84 */           Integer.valueOf(traceRet1));
/*     */     }
/*  86 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReturnCode(boolean swap) {
/*  92 */     if (Trace.isOn)
/*  93 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpESH", "getReturnCode(boolean)", new Object[] {
/*  94 */             Boolean.valueOf(swap)
/*     */           }); 
/*  96 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 4, swap);
/*     */     
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpESH", "getReturnCode(boolean)", 
/* 100 */           Integer.valueOf(traceRet1));
/*     */     }
/* 102 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setErrorDataLength(int errorDataLength, boolean swap) {
/* 109 */     if (Trace.isOn)
/* 110 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpESH", "setErrorDataLength(int,boolean)", new Object[] {
/* 111 */             Integer.valueOf(errorDataLength), Boolean.valueOf(swap)
/*     */           }); 
/* 113 */     this.dc.writeI32(errorDataLength, this.buffer, this.offset + 0, swap);
/*     */     
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpESH", "setErrorDataLength(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReturnCode(int returnCode, boolean swap) {
/* 125 */     if (Trace.isOn)
/* 126 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpESH", "setReturnCode(int,boolean)", new Object[] {
/* 127 */             Integer.valueOf(returnCode), Boolean.valueOf(swap)
/*     */           }); 
/* 129 */     this.dc.writeI32(returnCode, this.buffer, this.offset + 4, swap);
/*     */     
/* 131 */     if (Trace.isOn)
/* 132 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpESH", "setReturnCode(int,boolean)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpESH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */