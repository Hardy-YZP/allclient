/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ByteArrayOutputStream;
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
/*     */ class MQOutputStream
/*     */   extends ByteArrayOutputStream
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQOutputStream.java";
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.MQOutputStream", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQOutputStream.java");
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
/*     */   public MQOutputStream() {
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.entry(this, "com.ibm.mq.MQOutputStream", "<init>()");
/*     */     }
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.exit(this, "com.ibm.mq.MQOutputStream", "<init>()");
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
/*     */   public MQOutputStream(int size) {
/*  81 */     super(size);
/*  82 */     if (Trace.isOn)
/*  83 */       Trace.entry(this, "com.ibm.mq.MQOutputStream", "<init>(int)", new Object[] {
/*  84 */             Integer.valueOf(size)
/*     */           }); 
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.exit(this, "com.ibm.mq.MQOutputStream", "<init>(int)");
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
/*     */   public byte[] toByteArray() {
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.entry(this, "com.ibm.mq.MQOutputStream", "toByteArray()");
/*     */     }
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.data(this, "toByteArray()", "Buffer is padded so returning a copy without the padding", "");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     byte[] traceRet1 = super.toByteArray();
/*     */     
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.mq.MQOutputStream", "toByteArray()", traceRet1);
/*     */     }
/* 116 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQOutputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */