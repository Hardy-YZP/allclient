/*     */ package com.ibm.mq.jmqi.local.internal.adapters;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.MQCNO;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.local.LocalHconn;
/*     */ import com.ibm.mq.jmqi.local.LocalMQ;
/*     */ import com.ibm.mq.jmqi.local.internal.base.Native;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.RXPB;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.commonservices.util.Cruise;
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
/*     */ public abstract class Adapter
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/Adapter.java";
/*     */   public static final int AUTHENTICATION_NONE = 0;
/*     */   public static final int AUTHENTICATION_DUMMY = 1;
/*     */   public static final int AUTHENTICATION_NATIVE = 2;
/*     */   public static final int CONNECT_MQCONN = 0;
/*     */   public static final int CONNECT_MQCONNX = 1;
/*     */   public static final int CONNECT_SPICONNECT = 2;
/*     */   protected LocalMQ mq;
/*     */   
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.mq.jmqi.local.internal.adapters.Adapter", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.local/src/com/ibm/mq/jmqi/local/internal/adapters/Adapter.java");
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
/*     */   public Adapter(JmqiEnvironment env, LocalMQ mq) {
/*  76 */     if (Trace.isOn) {
/*  77 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.Adapter", "<init>(JmqiEnvironment,LocalMQ)", new Object[] { env, mq });
/*     */     }
/*     */     
/*  80 */     this.mq = mq;
/*     */     
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.Adapter", "<init>(JmqiEnvironment,LocalMQ)");
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
/*     */   public String getAlternateLibraryName() {
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.Adapter", "getAlternateLibraryName()", "getter", null);
/*     */     }
/*     */     
/* 113 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.Adapter", "toString()");
/*     */     }
/* 125 */     StringBuffer buffer = new StringBuffer();
/* 126 */     buffer.append("className: " + getClass().getName() + ", libraryName: " + getLibraryName());
/* 127 */     String traceRet1 = buffer.toString();
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.Adapter", "toString()", traceRet1);
/*     */     }
/*     */     
/* 132 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Cruise("This method used to have the signature: public RXPB buildNewRxpb(JmqiEnvironment env)")
/*     */   public RXPB buildNewRxpb(JmqiEnvironment env, MQCNO cno) {
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.Adapter", "buildNewRxpb(JmqiEnvironment, MQCNO)", new Object[] { env, cno });
/*     */     }
/*     */ 
/*     */     
/* 148 */     RXPB rxpb = null;
/* 149 */     if (env instanceof JmqiSystemEnvironment) {
/* 150 */       JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)env;
/* 151 */       rxpb = sysenv.newRXPB();
/*     */       
/* 153 */       rxpb.setVersion(2);
/*     */       
/* 155 */       int flags = getRXPBFlags();
/* 156 */       rxpb.setFlags(flags);
/*     */     } 
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.Adapter", "buildNewRxpb(JmqiEnvironment, MQCNO)", rxpb);
/*     */     }
/*     */     
/* 162 */     return rxpb;
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
/*     */   public boolean isRRS() {
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.Adapter", "isRRS()", "getter", 
/* 176 */           Boolean.valueOf(false));
/*     */     }
/* 178 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void honourRRS(Hconn hconn, Pint pCompCode, Pint pReason) throws JmqiException {
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.Adapter", "honourRRS(Hconn,Pint,Pint)", new Object[] { hconn, pCompCode, pReason });
/*     */     }
/*     */     
/* 192 */     pCompCode.x = 2;
/* 193 */     pReason.x = 2012;
/*     */     
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.data(this, "honourRRS(Hconn,Pint,Pint)", "honourRRS called outside WebSphere!");
/*     */     }
/*     */     
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.Adapter", "honourRRS(Hconn,Pint,Pint)");
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
/*     */   public void authenticate(Hconn hconn, String userId, String password, Pint pCompCode, Pint pReason) {
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.Adapter", "authenticate(Hconn,String,String,final Pint,final Pint)", new Object[] { hconn, userId, (password == null) ? password : 
/*     */ 
/*     */             
/* 222 */             Integer.valueOf(password.length()), pCompCode, pReason });
/*     */     }
/*     */     
/* 225 */     pCompCode.x = 0;
/* 226 */     pReason.x = 0;
/*     */     
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.Adapter", "authenticate(Hconn,String,String,final Pint,final Pint)");
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
/*     */   public void connect(int requestedConnType, boolean isTraceActive, byte[] qmName, byte[] rxpbBuf, byte[] pSpiConnectOpts, byte[] pConnectOpts, LocalHconn phconn, int environment, Pint pCompCode, Pint pReason) throws JmqiException {
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.entry(this, "com.ibm.mq.jmqi.local.internal.adapters.Adapter", "connect(int,boolean,byte [ ],byte [ ],byte [ ],byte [ ],LocalHconn, int, Pint,Pint)", new Object[] {
/*     */             
/* 260 */             Integer.valueOf(requestedConnType), Boolean.valueOf(isTraceActive), qmName, rxpbBuf, pSpiConnectOpts, pConnectOpts, phconn, 
/* 261 */             Integer.valueOf(environment), pCompCode, pReason
/*     */           });
/*     */     }
/* 264 */     switch (requestedConnType) {
/*     */       case 0:
/* 266 */         Native.MQCONN(isTraceActive, qmName, rxpbBuf, phconn, pCompCode, pReason);
/*     */         break;
/*     */       case 1:
/* 269 */         Native.MQCONNX(isTraceActive, qmName, rxpbBuf, pConnectOpts, phconn, pCompCode, pReason);
/*     */         break;
/*     */       case 2:
/* 272 */         if (environment != -1) {
/* 273 */           Native.spiConnect_with_environment(isTraceActive, qmName, rxpbBuf, pSpiConnectOpts, pConnectOpts, phconn, environment, pCompCode, pReason); break;
/*     */         } 
/* 275 */         Native.spiConnect(isTraceActive, qmName, rxpbBuf, pSpiConnectOpts, pConnectOpts, phconn, pCompCode, pReason);
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       default:
/* 281 */         pCompCode.x = 2;
/* 282 */         pReason.x = 2298;
/*     */         break;
/*     */     } 
/*     */     
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.exit(this, "com.ibm.mq.jmqi.local.internal.adapters.Adapter", "connect(int,boolean,byte [ ],byte [ ],byte [ ],byte [ ],LocalHconn,Pint,Pint)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCICS() {
/* 297 */     if (Trace.isOn) {
/* 298 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.Adapter", "isCICS()", "getter", 
/* 299 */           Boolean.valueOf(false));
/*     */     }
/* 301 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIMS() {
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.data(this, "com.ibm.mq.jmqi.local.internal.adapters.Adapter", "isIMS()", "getter", 
/* 310 */           Boolean.valueOf(false));
/*     */     }
/* 312 */     return false;
/*     */   }
/*     */   
/*     */   public abstract boolean isSharedHandlesSupported();
/*     */   
/*     */   public abstract boolean isWorkerThreadSupported();
/*     */   
/*     */   public abstract String getLibraryName();
/*     */   
/*     */   public abstract int getRXPBFlags();
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\local\internal\adapters\Adapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */