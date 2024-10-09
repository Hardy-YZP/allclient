/*     */ package com.ibm.mq.jmqi.local.internal.adapters;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.local.LocalHconn;
/*     */ import com.ibm.mq.jmqi.local.LocalMQ;
/*     */ import com.ibm.mq.jmqi.system.RXPB;
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
/*     */ public abstract class InternalRRSAdapter
/*     */   extends RRSAdapter
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/InternalRRSAdapter.java";
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.jmqi.local.internal.adapters.InternalRRSAdapter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/InternalRRSAdapter.java");
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
/*     */   public InternalRRSAdapter(JmqiEnvironment env, LocalMQ mq) {
/*  56 */     super(env, mq);
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.InternalRRSAdapter", "<init>(JmqiEnvironment,LocalMQ)", new Object[] { env, mq });
/*     */     }
/*     */     
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.InternalRRSAdapter", "<init>(JmqiEnvironment,LocalMQ)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSharedHandlesSupported() {
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.InternalRRSAdapter", "isSharedHandlesSupported()", "getter", 
/*  75 */           Boolean.valueOf(false));
/*     */     }
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWorkerThreadSupported() {
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.InternalRRSAdapter", "isWorkerThreadSupported()", "getter", 
/*  88 */           Boolean.valueOf(true));
/*     */     }
/*  90 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRXPBFlags() {
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.InternalRRSAdapter", "getRXPBFlags()", "getter", 
/*     */           
/* 101 */           Integer.valueOf(49));
/*     */     }
/*     */     
/* 104 */     return 49;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void honourRRS(Hconn hconn, Pint pCompCode, Pint pReason) throws JmqiException {
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.InternalRRSAdapter", "honourRRS(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*     */     }
/*     */     
/* 119 */     LocalHconn localhconn = this.mq.getLocalHconn(hconn);
/* 120 */     RXPB rxpb = localhconn.getRxpb();
/* 121 */     int flags = rxpb.getFlags();
/* 122 */     flags &= 0xFFFFFFFE;
/* 123 */     rxpb.setFlags(flags);
/*     */     
/* 125 */     if (Trace.isOn)
/* 126 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.InternalRRSAdapter", "honourRRS(Hconn,Pint,Pint)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\adapters\InternalRRSAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */