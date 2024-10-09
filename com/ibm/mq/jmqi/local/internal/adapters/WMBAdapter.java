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
/*     */ public abstract class WMBAdapter
/*     */   extends RRSAdapter
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/WMBAdapter.java";
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.jmqi.local.internal.adapters.WMBAdapter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/WMBAdapter.java");
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
/*     */   public WMBAdapter(JmqiEnvironment env, LocalMQ mq) {
/*  55 */     super(env, mq);
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.WMBAdapter", "<init>(JmqiEnvironment,LocalMQ)", new Object[] { env, mq });
/*     */     }
/*     */     
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.WMBAdapter", "<init>(JmqiEnvironment,LocalMQ)");
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
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.WMBAdapter", "getRXPBFlags()", "getter", 
/*     */           
/*  75 */           Integer.valueOf(49));
/*     */     }
/*     */ 
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
/*     */   
/*     */   public void honourRRS(Hconn hconn, Pint pCompCode, Pint pReason) throws JmqiException {
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.WMBAdapter", "honourRRS(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*     */     }
/*     */     
/*  95 */     LocalHconn localhconn = this.mq.getLocalHconn(hconn);
/*  96 */     RXPB rxpb = localhconn.getRxpb();
/*  97 */     int flags = rxpb.getFlags();
/*  98 */     flags &= 0xFFFFFFFE;
/*  99 */     rxpb.setFlags(flags);
/*     */     
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.WMBAdapter", "honourRRS(Hconn,Pint,Pint)");
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
/*     */   public void authenticate(Hconn hconn, String userId, String password, Pint pCompCode, Pint pReason) {
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.WMBAdapter", "authenticate(Hconn,String,String,final Pint,final Pint)", new Object[] { hconn, userId, (password == null) ? password : 
/*     */ 
/*     */             
/* 122 */             Integer.valueOf(password.length()), pCompCode, pReason });
/*     */     }
/*     */     
/* 125 */     this.mq.authenticate_native(hconn, userId, password, pCompCode, pReason);
/*     */     
/* 127 */     if (Trace.isOn)
/* 128 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.WMBAdapter", "authenticate(Hconn,String,String,final Pint,final Pint)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\adapters\WMBAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */