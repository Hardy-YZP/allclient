/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.MQCNO;
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
/*     */ public class RXPBWithCNO
/*     */   extends RXPB
/*     */ {
/*     */   MQCNO copyMQCNO;
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.jmqi.system.RXPBWithCNO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/RXPBWithCNO.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RXPBWithCNO(JmqiEnvironment env, MQCNO rxpbCNO) {
/*  64 */     super(env);
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.entry(this, "com.ibm.mq.jmqi.system.RXPBwithCNO", "<init>(JmqiEnvironment, MQCNO)", new Object[] { env, rxpbCNO });
/*     */     }
/*     */     
/*  69 */     setFlags(getFlags() | 0x40);
/*     */     try {
/*  71 */       this.copyMQCNO = (MQCNO)rxpbCNO.clone();
/*     */     }
/*  73 */     catch (CloneNotSupportedException e) {
/*  74 */       if (Trace.isOn) {
/*  75 */         Trace.catchBlock(this, "com.ibm.mq.jmqi.system.RXPBwithCNO", "<init>(JmqiEnvironment, MQCNO)", e);
/*     */       }
/*     */     } 
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.exit(this, "com.ibm.mq.jmqi.system.RXPBwithCNO", "<init>(JmqiEnvironment, MQCNO)");
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
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/*  92 */     if (Trace.isOn)
/*  93 */       Trace.entry(this, "com.ibm.mq.jmqi.system.RXPBwithCNO", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/*  94 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/*  96 */     int traceRet1 = super.getRequiredBufferSize(ptrSize, cp) + this.copyMQCNO.getRequiredBufferSize(ptrSize, cp);
/*     */     
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.exit(this, "com.ibm.mq.jmqi.system.RXPBwithCNO", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 100 */           Integer.valueOf(traceRet1));
/*     */     }
/* 102 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.entry(this, "com.ibm.mq.jmqi.system.RXPBwithCNO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 114 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 116 */     int pos = offset;
/* 117 */     pos = super.writeToBuffer(buffer, pos, ptrSize, swap, cp, tls);
/* 118 */     pos = this.copyMQCNO.writeToBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*     */     
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.exit(this, "com.ibm.mq.jmqi.system.RXPBwithCNO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 122 */           Integer.valueOf(pos));
/*     */     }
/* 124 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int flags) {
/* 131 */     if (Trace.isOn)
/* 132 */       Trace.entry(this, "com.ibm.mq.jmqi.system.RXPBwithCNO", "setFlags(int)", new Object[] {
/* 133 */             Integer.valueOf(flags)
/*     */           }); 
/* 135 */     super.setFlags(flags | 0x40);
/* 136 */     if (Trace.isOn)
/* 137 */       Trace.exit(this, "com.ibm.mq.jmqi.system.RXPBwithCNO", "setFlags(int)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\RXPBWithCNO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */