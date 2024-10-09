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
/*     */ public abstract class ExternalRRSAdapter
/*     */   extends RRSAdapter
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/ExternalRRSAdapter.java";
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.jmqi.local.internal.adapters.ExternalRRSAdapter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/ExternalRRSAdapter.java");
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
/*     */   public ExternalRRSAdapter(JmqiEnvironment env, LocalMQ mq) {
/*  56 */     super(env, mq);
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.ExternalRRSAdapter", "<init>(JmqiEnvironment,LocalMQ)", new Object[] { env, mq });
/*     */     }
/*     */     
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.ExternalRRSAdapter", "<init>(JmqiEnvironment,LocalMQ)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRXPBFlags() {
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.ExternalRRSAdapter", "getRXPBFlags()", "getter", 
/*     */           
/*  76 */           Integer.valueOf(49));
/*     */     }
/*     */     
/*  79 */     return 49;
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
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.ExternalRRSAdapter", "honourRRS(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*     */     }
/*     */     
/*  94 */     LocalHconn localhconn = this.mq.getLocalHconn(hconn);
/*  95 */     RXPB rxpb = localhconn.getRxpb();
/*  96 */     int flags = rxpb.getFlags();
/*  97 */     flags &= 0xFFFFFFFE;
/*  98 */     rxpb.setFlags(flags);
/*     */     
/* 100 */     if (Trace.isOn)
/* 101 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.ExternalRRSAdapter", "honourRRS(Hconn,Pint,Pint)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\adapters\ExternalRRSAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */