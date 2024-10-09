/*     */ package com.ibm.mq.jmqi.local.internal.adapters;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.MQCNO;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.local.LocalHconn;
/*     */ import com.ibm.mq.jmqi.local.LocalMQ;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
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
/*     */ public abstract class CICSAdapter
/*     */   extends Adapter
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/CICSAdapter.java";
/*     */   
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.mq.jmqi.local.internal.adapters.CICSAdapter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/CICSAdapter.java");
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
/*     */   public CICSAdapter(JmqiEnvironment env, LocalMQ mq) {
/*  60 */     super(env, mq);
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.CICSAdapter", "<init>(JmqiEnvironment,LocalMQ)", new Object[] { env, mq });
/*     */     }
/*     */     
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.CICSAdapter", "<init>(JmqiEnvironment,LocalMQ)");
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
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.CICSAdapter", "isSharedHandlesSupported()", "getter", 
/*  79 */           Boolean.valueOf(false));
/*     */     }
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWorkerThreadSupported() {
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.CICSAdapter", "isWorkerThreadSupported()", "getter", 
/*  91 */           Boolean.valueOf(false));
/*     */     }
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRXPBFlags() {
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.CICSAdapter", "getRXPBFlags()", "getter", 
/* 103 */           Integer.valueOf(0));
/*     */     }
/* 105 */     return 0;
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
/*     */   public void authenticate(Hconn hconn, String userId, String password, Pint pCompCode, Pint pReason) {
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.CICSAdapter", "authenticate(Hconn,String,String,final Pint,final Pint)", new Object[] { hconn, userId, (password == null) ? password : 
/*     */ 
/*     */             
/* 122 */             Integer.valueOf(password.length()), pCompCode, pReason });
/*     */     }
/*     */     
/* 125 */     this.mq.authenticate_dummy(hconn, userId, password, pCompCode, pReason);
/*     */     
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.CICSAdapter", "authenticate(Hconn,String,String,final Pint,final Pint)");
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
/*     */   public boolean isCICS() {
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.CICSAdapter", "isCICS()", "getter", 
/* 142 */           Boolean.valueOf(true));
/*     */     }
/* 144 */     return true;
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
/*     */   public void connect(int requestedConnTypeP, boolean isTraceActive, byte[] qmName, byte[] rxpbBuf, byte[] pSpiConnectOpts, byte[] pConnectOpts, LocalHconn phconn, int environment, Pint pCompCode, Pint pReason) throws JmqiException {
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.CICSAdapter", "connect(int,boolean,byte [ ],byte [ ],byte [ ],byte [ ],LocalHconn,Pint,Pint)", new Object[] {
/*     */             
/* 172 */             Integer.valueOf(requestedConnTypeP), Boolean.valueOf(isTraceActive), qmName, rxpbBuf, pSpiConnectOpts, pConnectOpts, 
/* 173 */             Integer.valueOf(environment), phconn, pCompCode, pReason
/*     */           });
/*     */     }
/* 176 */     int requestedConnType = requestedConnTypeP;
/*     */     
/* 178 */     if (requestedConnType == 1)
/*     */     {
/* 180 */       if (pConnectOpts == null) {
/*     */ 
/*     */         
/* 183 */         requestedConnType = 0;
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 191 */         JmqiEnvironment env = this.mq.getEnv();
/* 192 */         JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)env;
/* 193 */         int compId = this.mq.getTlsComponentId();
/* 194 */         JmqiComponentTls tls = sysenv.getComponentTls(compId);
/* 195 */         JmqiTls jTls = sysenv.getJmqiTls(tls);
/* 196 */         int ptrSize = LocalMQ.getPtrSize();
/* 197 */         boolean swap = LocalMQ.getSwap();
/* 198 */         JmqiCodepage nativeCp = env.getNativeCharSet();
/*     */ 
/*     */         
/* 201 */         MQCNO connectOpts = sysenv.newMQCNO();
/*     */ 
/*     */         
/* 204 */         connectOpts.readFromBuffer(pConnectOpts, 0, true, ptrSize, swap, nativeCp, jTls);
/*     */ 
/*     */         
/* 207 */         if (connectOpts.getVersion() == 1 && connectOpts.getOptions() == 0)
/*     */         {
/* 209 */           requestedConnType = 0;
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 219 */     super.connect(requestedConnType, isTraceActive, qmName, rxpbBuf, pSpiConnectOpts, pConnectOpts, phconn, environment, pCompCode, pReason);
/*     */ 
/*     */     
/* 222 */     if (Trace.isOn)
/* 223 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.CICSAdapter", "connect(int,boolean,byte [ ],byte [ ],byte [ ],byte [ ],LocalHconn,Pint,Pint)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\adapters\CICSAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */