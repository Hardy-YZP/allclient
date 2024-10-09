/*     */ package com.ibm.mq.jmqi.local.internal.adapters;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.MQCNO;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.local.LocalHconn;
/*     */ import com.ibm.mq.jmqi.local.LocalMQ;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.RXPB;
/*     */ import com.ibm.mq.jmqi.system.RXPBWithCNO;
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
/*     */ public abstract class WASAdapter
/*     */   extends RRSAdapter
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/WASAdapter.java";
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.jmqi.local.internal.adapters.WASAdapter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/WASAdapter.java");
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
/*     */   public WASAdapter(JmqiEnvironment env, LocalMQ mq) {
/*  57 */     super(env, mq);
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.WASAdapter", "<init>(JmqiEnvironment,LocalMQ)", new Object[] { env, mq });
/*     */     }
/*     */     
/*  62 */     if (Trace.isOn) {
/*  63 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.WASAdapter", "<init>(JmqiEnvironment,LocalMQ)");
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
/*     */   public RXPB buildNewRxpb(JmqiEnvironment env, MQCNO cno) {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.WASAdapter", "buildNewRxpb(JmqiEnvironment, MQCNO)", new Object[] { env, cno });
/*     */     }
/*     */ 
/*     */     
/*  82 */     RXPB rxpb = null;
/*  83 */     if (env instanceof JmqiSystemEnvironment) {
/*  84 */       JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)env;
/*  85 */       if (cno != null && cno.getSecurityParms() != null && cno.getSecurityParms().getCspUserId() != null) {
/*  86 */         RXPBWithCNO rXPBWithCNO = sysenv.newRXPBWithCNO(cno);
/*     */         
/*  88 */         rXPBWithCNO.setVersion(2);
/*     */         
/*  90 */         int flags = getRXPBFlags();
/*  91 */         rXPBWithCNO.setFlags(flags);
/*     */       } else {
/*  93 */         rxpb = super.buildNewRxpb(env, cno);
/*     */       } 
/*     */     } 
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.WASAdapter", "buildNewRxpb(JmqiEnvironment, MQCNO)", rxpb);
/*     */     }
/*     */     
/* 100 */     return rxpb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRXPBFlags() {
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.WASAdapter", "getRXPBFlags()", "getter", 
/* 110 */           Integer.valueOf(1));
/*     */     }
/* 112 */     return 1;
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
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.WASAdapter", "honourRRS(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*     */     }
/*     */     
/* 127 */     LocalHconn localhconn = this.mq.getLocalHconn(hconn);
/* 128 */     RXPB rxpb = localhconn.getRxpb();
/* 129 */     int flags = rxpb.getFlags();
/* 130 */     flags &= 0xFFFFFFFE;
/* 131 */     rxpb.setFlags(flags);
/*     */     
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.WASAdapter", "honourRRS(Hconn,Pint,Pint)");
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
/*     */   public void authenticate(Hconn hconn, String userId, String password, Pint pCompCode, Pint pReason) {
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.WASAdapter", "authenticate(Hconn,String,String,final Pint,final Pint)", new Object[] { hconn, userId, (password == null) ? password : 
/*     */ 
/*     */             
/* 155 */             Integer.valueOf(password.length()), pCompCode, pReason });
/*     */     }
/*     */ 
/*     */     
/* 159 */     this.mq.authenticate_native(hconn, userId, password, pCompCode, pReason);
/*     */     
/* 161 */     if (Trace.isOn)
/* 162 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.WASAdapter", "authenticate(Hconn,String,String,final Pint,final Pint)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\adapters\WASAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */