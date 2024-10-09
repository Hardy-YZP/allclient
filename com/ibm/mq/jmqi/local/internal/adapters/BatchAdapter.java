/*     */ package com.ibm.mq.jmqi.local.internal.adapters;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiMQ;
/*     */ import com.ibm.mq.jmqi.MQCNO;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.internal.Configuration;
/*     */ import com.ibm.mq.jmqi.local.LocalHconn;
/*     */ import com.ibm.mq.jmqi.local.LocalMQ;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.LpiPrivConnStruct;
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
/*     */ public abstract class BatchAdapter
/*     */   extends Adapter
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/BatchAdapter.java";
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.jmqi.local.internal.adapters.BatchAdapter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/BatchAdapter.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean presumeAbortRequired = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BatchAdapter(JmqiEnvironment env, LocalMQ mq) {
/*  63 */     super(env, mq);
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.BatchAdapter", "<init>(JmqiEnvironment,LocalMQ)", new Object[] { env, mq });
/*     */     }
/*     */ 
/*     */     
/*  69 */     if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES) {
/*     */       
/*  71 */       String terminationMode = env.getConfiguration().getStringValue(Configuration.terminationModeProperty);
/*  72 */       if (terminationMode != null && terminationMode.trim().equalsIgnoreCase("compatibility")) {
/*     */         
/*  74 */         this.presumeAbortRequired = false;
/*     */       } else {
/*  76 */         this.presumeAbortRequired = true;
/*     */       } 
/*     */     } 
/*     */     
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.BatchAdapter", "<init>(JmqiEnvironment,LocalMQ)");
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
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.BatchAdapter", "isSharedHandlesSupported()", "getter", 
/*  94 */           Boolean.valueOf(false));
/*     */     }
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWorkerThreadSupported() {
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.BatchAdapter", "isWorkerThreadSupported()", "getter", 
/* 106 */           Boolean.valueOf(true));
/*     */     }
/* 108 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRXPBFlags() {
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.BatchAdapter", "getRXPBFlags()", "getter", 
/* 118 */           Integer.valueOf(0));
/*     */     }
/* 120 */     return 0;
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
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.BatchAdapter", "authenticate(Hconn,String,String,final Pint,final Pint)", new Object[] { hconn, userId, (password == null) ? password : 
/*     */ 
/*     */             
/* 137 */             Integer.valueOf(password.length()), pCompCode, pReason });
/*     */     }
/*     */     
/* 140 */     this.mq.authenticate_dummy(hconn, userId, password, pCompCode, pReason);
/*     */     
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.BatchAdapter", "authenticate(Hconn,String,String,final Pint,final Pint)");
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
/*     */   public void connect(int requestedConnTypeP, boolean isTraceActive, byte[] qmName, byte[] rxpbBuf, byte[] pSpiConnectOptsP, byte[] pConnectOptsP, LocalHconn phconn, int environment, Pint pCompCode, Pint pReason) throws JmqiException {
/* 169 */     if (Trace.isOn)
/* 170 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.BatchAdapter", "connect(int,boolean,byte [ ],byte [ ],byte [ ],byte [ ],LocalHconn, int, Pint,Pint)", new Object[] {
/*     */             
/* 172 */             Integer.valueOf(requestedConnTypeP), Boolean.valueOf(isTraceActive), qmName, rxpbBuf, pSpiConnectOptsP, pConnectOptsP, phconn, 
/* 173 */             Integer.valueOf(environment), pCompCode, pReason
/*     */           }); 
/* 175 */     JmqiEnvironment env = this.mq.getEnv();
/* 176 */     int requestedConnType = requestedConnTypeP;
/* 177 */     byte[] pSpiConnectOpts = pSpiConnectOptsP;
/* 178 */     byte[] pConnectOpts = pConnectOptsP;
/* 179 */     if (this.presumeAbortRequired == true) {
/*     */       
/* 181 */       requestedConnType = 2;
/*     */ 
/*     */ 
/*     */       
/* 185 */       JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)env;
/* 186 */       int ptrSize = LocalMQ.getPtrSize();
/* 187 */       boolean swap = LocalMQ.getSwap();
/* 188 */       JmqiCodepage nativeCp = env.getNativeCharSet();
/*     */       
/* 190 */       LpiPrivConnStruct spiConnectOpts = sysenv.newSpiConnectOptions();
/* 191 */       if (pSpiConnectOpts == null) {
/*     */ 
/*     */ 
/*     */         
/* 195 */         spiConnectOpts.setOptions(32896);
/* 196 */         spiConnectOpts.setVersion(2);
/*     */         
/* 198 */         int spiLen = spiConnectOpts.getRequiredBufferSize(ptrSize, nativeCp);
/* 199 */         pSpiConnectOpts = new byte[spiLen];
/* 200 */         spiConnectOpts.writeToBuffer(pSpiConnectOpts, 0, ptrSize, swap, nativeCp, (JmqiMQ)this.mq);
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 206 */         spiConnectOpts.readFromBuffer(pSpiConnectOpts, 0, ptrSize, swap, nativeCp, (JmqiMQ)this.mq);
/*     */ 
/*     */         
/* 209 */         spiConnectOpts.setOptions(spiConnectOpts.getOptions() | 0x80 | 0x8000);
/*     */ 
/*     */         
/* 212 */         spiConnectOpts.writeToBuffer(pSpiConnectOpts, 0, ptrSize, swap, nativeCp, (JmqiMQ)this.mq);
/*     */       } 
/* 214 */       if (pConnectOpts == null) {
/*     */ 
/*     */         
/* 217 */         MQCNO cno = sysenv.newMQCNO();
/*     */         
/* 219 */         int cnoLen = cno.getRequiredBufferSize(ptrSize, nativeCp);
/* 220 */         pConnectOpts = new byte[cnoLen];
/* 221 */         cno.writeToBuffer(pConnectOpts, 0, ptrSize, swap, nativeCp, (JmqiMQ)this.mq);
/*     */       } 
/*     */     } 
/*     */     
/* 225 */     super.connect(requestedConnType, isTraceActive, qmName, rxpbBuf, pSpiConnectOpts, pConnectOpts, phconn, environment, pCompCode, pReason);
/*     */     
/* 227 */     if (Trace.isOn)
/* 228 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.BatchAdapter", "connect(int,boolean,byte [ ],byte [ ],byte [ ],byte [ ],LocalHconn,Pint,Pint)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\adapters\BatchAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */