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
/*     */ public class RfpXA_COMPLETE
/*     */   extends RfpStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpXA_COMPLETE.java";
/*     */   private static final int HANDLE_OFFSET = 0;
/*     */   private static final int RETVAL_OFFSET = 4;
/*     */   public static final int SIZE = 8;
/*     */   
/*     */   static {
/*  34 */     if (Trace.isOn) {
/*  35 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpXA_COMPLETE", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpXA_COMPLETE.java");
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
/*     */   public RfpXA_COMPLETE(JmqiEnvironment env, byte[] buffer, int offset) {
/*  53 */     super(env, buffer, offset);
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
/*     */   public void setHandle(int handle, boolean swap) {
/*  72 */     if (Trace.isOn)
/*  73 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpXA_COMPLETE", "setHandle(int,boolean)", new Object[] {
/*  74 */             Integer.valueOf(handle), Boolean.valueOf(swap)
/*     */           }); 
/*  76 */     this.dc.writeI32(handle, this.buffer, this.offset + 0, swap);
/*     */     
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpXA_COMPLETE", "setHandle(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRetval(int retval, boolean swap) {
/*  88 */     if (Trace.isOn)
/*  89 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpXA_COMPLETE", "setRetval(int,boolean)", new Object[] {
/*  90 */             Integer.valueOf(retval), Boolean.valueOf(swap)
/*     */           }); 
/*  92 */     this.dc.writeI32(retval, this.buffer, this.offset + 4, swap);
/*     */     
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpXA_COMPLETE", "setRetval(int,boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHandle(boolean swap) {
/* 104 */     if (Trace.isOn)
/* 105 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpXA_COMPLETE", "getHandle(boolean)", new Object[] {
/* 106 */             Boolean.valueOf(swap)
/*     */           }); 
/* 108 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 0, swap);
/*     */     
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpXA_COMPLETE", "getHandle(boolean)", 
/* 112 */           Integer.valueOf(traceRet1));
/*     */     }
/* 114 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRetval(boolean swap) {
/* 122 */     if (Trace.isOn)
/* 123 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpXA_COMPLETE", "getRetval(boolean)", new Object[] {
/* 124 */             Boolean.valueOf(swap)
/*     */           }); 
/* 126 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 4, swap);
/*     */     
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpXA_COMPLETE", "getRetval(boolean)", 
/* 130 */           Integer.valueOf(traceRet1));
/*     */     }
/* 132 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpXA_COMPLETE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */