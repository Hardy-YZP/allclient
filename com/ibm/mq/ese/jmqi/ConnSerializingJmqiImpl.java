/*     */ package com.ibm.mq.ese.jmqi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiMQ;
/*     */ import com.ibm.mq.jmqi.MQCBD;
/*     */ import com.ibm.mq.jmqi.MQCNO;
/*     */ import com.ibm.mq.jmqi.MQGMO;
/*     */ import com.ibm.mq.jmqi.MQMD;
/*     */ import com.ibm.mq.jmqi.MQOD;
/*     */ import com.ibm.mq.jmqi.MQPMO;
/*     */ import com.ibm.mq.jmqi.MQSD;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
/*     */ import com.ibm.mq.jmqi.handles.PbyteBuffer;
/*     */ import com.ibm.mq.jmqi.handles.Phconn;
/*     */ import com.ibm.mq.jmqi.handles.Phobj;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.monitoring.AbstractJMQISupport;
/*     */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*     */ import com.ibm.mq.jmqi.system.JmqiSP;
/*     */ import com.ibm.mq.jmqi.system.LpiPrivConnStruct;
/*     */ import com.ibm.mq.jmqi.system.LpiSD;
/*     */ import com.ibm.mq.jmqi.system.SpiConnectOptions;
/*     */ import com.ibm.mq.jmqi.system.SpiGetOptions;
/*     */ import com.ibm.mq.jmqi.system.SpiOpenOptions;
/*     */ import com.ibm.mq.jmqi.system.SpiPutOptions;
/*     */ import com.ibm.mq.jmqi.system.zrfp.Triplet;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConnSerializingJmqiImpl
/*     */   extends AbstractJMQISupport
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/jmqi/ConnSerializingJmqiImpl.java";
/*     */   
/*     */   static {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.data("com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/jmqi/ConnSerializingJmqiImpl.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class Lock
/*     */   {
/*     */     private Lock() {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   private Map<Hconn, Lock> locks = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnSerializingJmqiImpl(JmqiEnvironment env, int options, JmqiMQ mq) {
/*  91 */     super(env, options, mq);
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "<init>(JmqiEnvironment,int,JmqiMQ)", new Object[] { env, 
/*  94 */             Integer.valueOf(options), mq });
/*     */     }
/*  96 */     this.locks = Collections.synchronizedMap(new HashMap<>());
/*  97 */     this.delegate = mq;
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "<init>(JmqiEnvironment,int,JmqiMQ)");
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
/*     */   public void MQCB(Hconn hconn, int operation, MQCBD callbackDesc, Hobj hobj, MQMD msgDesc, MQGMO getMsgOpts, Pint compCode, Pint reason) {
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)", new Object[] { hconn, 
/*     */             
/* 119 */             Integer.valueOf(operation), callbackDesc, hobj, msgDesc, getMsgOpts, compCode, reason });
/*     */     }
/* 121 */     Object lock = this.locks.get(hconn);
/* 122 */     if (lock != null) {
/* 123 */       synchronized (lock) {
/* 124 */         this.delegate.MQCB(hconn, operation, callbackDesc, hobj, msgDesc, getMsgOpts, compCode, reason);
/*     */       } 
/*     */     } else {
/*     */       
/* 128 */       compCode.x = 2;
/* 129 */       reason.x = 2018;
/*     */     } 
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)");
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
/*     */   public void MQCLOSE(Hconn hconn, Phobj hobj, int Options, Pint compCode, Pint reason) {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", new Object[] { hconn, hobj, 
/* 150 */             Integer.valueOf(Options), compCode, reason });
/*     */     }
/*     */     
/* 153 */     Object lock = this.locks.get(hconn);
/* 154 */     if (lock != null) {
/* 155 */       synchronized (lock) {
/* 156 */         this.delegate.MQCLOSE(hconn, hobj, Options, compCode, reason);
/*     */       } 
/*     */     } else {
/* 159 */       compCode.x = 2;
/* 160 */       reason.x = 2018;
/*     */     } 
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)");
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
/*     */   public void MQCONN(String mgrName, Phconn hconn, Pint compCode, Pint reason) {
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQCONN(String,Phconn,Pint,Pint)", new Object[] { mgrName, hconn, compCode, reason });
/*     */     }
/*     */     
/* 182 */     this.delegate.MQCONN(mgrName, hconn, compCode, reason);
/* 183 */     if (compCode.x == 2) {
/* 184 */       if (Trace.isOn) {
/* 185 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQCONN(String,Phconn,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 190 */     this.locks.put(hconn.getHconn(), new Lock());
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQCONN(String,Phconn,Pint,Pint)");
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
/*     */   public void MQCONNX(String mgrName, MQCNO connectOpts, Phconn hconn, Pint compCode, Pint reason) {
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQCONNX(String,MQCNO,Phconn,Pint,Pint)", new Object[] { mgrName, connectOpts, hconn, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 213 */     this.delegate.MQCONNX(mgrName, connectOpts, hconn, compCode, reason);
/* 214 */     if (compCode.x == 2) {
/* 215 */       if (Trace.isOn) {
/* 216 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQCONNX(String,MQCNO,Phconn,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 221 */     this.locks.put(hconn.getHconn(), new Lock());
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQCONNX(String,MQCNO,Phconn,Pint,Pint)");
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
/*     */   public void MQDISC(Phconn hconn, Pint compCode, Pint reason) {
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQDISC(Phconn,Pint,Pint)", new Object[] { hconn, compCode, reason });
/*     */     }
/*     */     
/* 241 */     Object lock = this.locks.remove(hconn.getHconn());
/* 242 */     if (lock != null) {
/* 243 */       synchronized (lock) {
/* 244 */         this.delegate.MQDISC(hconn, compCode, reason);
/*     */       } 
/*     */     } else {
/* 247 */       compCode.x = 2;
/* 248 */       reason.x = 2018;
/*     */     } 
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQDISC(Phconn,Pint,Pint)");
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
/*     */   public void MQGET(Hconn hconn, Hobj hobj, MQMD msgDesc, MQGMO getMsgOpts, int BufferLength, ByteBuffer buffer, Pint dataLength, Pint compCode, Pint reason) {
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, msgDesc, getMsgOpts, 
/*     */             
/* 272 */             Integer.valueOf(BufferLength), buffer, dataLength, compCode, reason });
/*     */     }
/* 274 */     Object lock = this.locks.get(hconn);
/* 275 */     if (lock != null) {
/* 276 */       synchronized (lock) {
/* 277 */         this.delegate.MQGET(hconn, hobj, msgDesc, getMsgOpts, BufferLength, buffer, dataLength, compCode, reason);
/*     */       } 
/*     */     } else {
/*     */       
/* 281 */       compCode.x = 2;
/* 282 */       reason.x = 2018;
/*     */     } 
/* 284 */     if (Trace.isOn) {
/* 285 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)");
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
/*     */   public void MQOPEN(Hconn hconn, MQOD objDesc, int Options, Phobj hobj, Pint compCode, Pint reason) {
/* 301 */     if (Trace.isOn) {
/* 302 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", new Object[] { hconn, objDesc, 
/*     */             
/* 304 */             Integer.valueOf(Options), hobj, compCode, reason });
/*     */     }
/* 306 */     Object lock = this.locks.get(hconn);
/* 307 */     if (lock != null) {
/* 308 */       synchronized (lock) {
/* 309 */         this.delegate
/* 310 */           .MQOPEN(hconn, objDesc, Options, hobj, compCode, reason);
/*     */       } 
/*     */     } else {
/* 313 */       compCode.x = 2;
/* 314 */       reason.x = 2018;
/*     */     } 
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)");
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
/*     */   public void MQPUT(Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, int BufferLength, ByteBuffer buffer, Pint compCode, Pint reason) {
/* 334 */     if (Trace.isOn) {
/* 335 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, msgDesc, putMsgOpts, 
/*     */             
/* 337 */             Integer.valueOf(BufferLength), buffer, compCode, reason });
/*     */     }
/* 339 */     Object lock = this.locks.get(hconn);
/* 340 */     if (lock != null) {
/* 341 */       synchronized (lock) {
/* 342 */         this.delegate.MQPUT(hconn, hobj, msgDesc, putMsgOpts, BufferLength, buffer, compCode, reason);
/*     */       } 
/*     */     } else {
/*     */       
/* 346 */       compCode.x = 2;
/* 347 */       reason.x = 2018;
/*     */     } 
/* 349 */     if (Trace.isOn) {
/* 350 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)");
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
/*     */   public void MQPUT1(Hconn hconn, MQOD objDesc, MQMD msgDesc, MQPMO putMsgOpts, int BufferLength, ByteBuffer buffer, Pint compCode, Pint reason) {
/* 368 */     if (Trace.isOn) {
/* 369 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, objDesc, msgDesc, putMsgOpts, 
/*     */             
/* 371 */             Integer.valueOf(BufferLength), buffer, compCode, reason });
/*     */     }
/* 373 */     Object lock = this.locks.get(hconn);
/* 374 */     if (lock != null) {
/* 375 */       synchronized (lock) {
/* 376 */         this.delegate.MQPUT1(hconn, objDesc, msgDesc, putMsgOpts, BufferLength, buffer, compCode, reason);
/*     */       } 
/*     */     } else {
/*     */       
/* 380 */       compCode.x = 2;
/* 381 */       reason.x = 2018;
/*     */     } 
/* 383 */     if (Trace.isOn) {
/* 384 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)");
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
/*     */   public void jmqiConnect(String mgrName, JmqiConnectOptions jmqiConnectOpts, MQCNO connectOpts, Hconn parentHconn, Phconn hconn, Pint compCode, Pint reason) {
/* 402 */     if (Trace.isOn) {
/* 403 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", new Object[] { mgrName, jmqiConnectOpts, connectOpts, parentHconn, hconn, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 407 */     ((JmqiSP)this.delegate).jmqiConnect(mgrName, jmqiConnectOpts, connectOpts, parentHconn, hconn, compCode, reason);
/*     */     
/* 409 */     if (compCode.x == 2) {
/* 410 */       if (Trace.isOn) {
/* 411 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 416 */     this.locks.put(hconn.getHconn(), new Lock());
/* 417 */     if (Trace.isOn) {
/* 418 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)");
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
/*     */   public ByteBuffer jmqiGet(Hconn hconn, Hobj hobj, MQMD md, MQGMO gmo, int expectedMsgLength, int maxMsgLength, PbyteBuffer byteBuffer, Pint msgTooSmallForBufferCount, Pint dataLength, Pint compCode, Pint reason) {
/* 438 */     if (Trace.isOn) {
/* 439 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "jmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", new Object[] { hconn, hobj, md, gmo, 
/*     */             
/* 441 */             Integer.valueOf(expectedMsgLength), Integer.valueOf(maxMsgLength), byteBuffer, msgTooSmallForBufferCount, dataLength, compCode, reason });
/*     */     }
/*     */     
/* 444 */     ByteBuffer ret = null;
/* 445 */     Object lock = this.locks.get(hconn);
/* 446 */     if (lock != null) {
/* 447 */       synchronized (lock)
/*     */       {
/* 449 */         ret = ((JmqiSP)this.delegate).jmqiGet(hconn, hobj, md, gmo, expectedMsgLength, maxMsgLength, byteBuffer, msgTooSmallForBufferCount, dataLength, compCode, reason);
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 455 */       compCode.x = 2;
/* 456 */       reason.x = 2018;
/*     */     } 
/* 458 */     if (Trace.isOn) {
/* 459 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "jmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", ret);
/*     */     }
/*     */     
/* 462 */     return ret;
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
/*     */   public void jmqiPut(Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, ByteBuffer[] buffers, Pint compCode, Pint reason) {
/* 476 */     if (Trace.isOn) {
/* 477 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", new Object[] { hconn, hobj, msgDesc, putMsgOpts, buffers, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 481 */     Object lock = this.locks.get(hconn);
/* 482 */     if (lock != null) {
/* 483 */       synchronized (lock) {
/* 484 */         ((JmqiSP)this.delegate).jmqiPut(hconn, hobj, msgDesc, putMsgOpts, buffers, compCode, reason);
/*     */       } 
/*     */     } else {
/*     */       
/* 488 */       compCode.x = 2;
/* 489 */       reason.x = 2018;
/*     */     } 
/* 491 */     if (Trace.isOn) {
/* 492 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)");
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
/*     */   public void jmqiPut1(Hconn hconn, MQOD objDesc, MQMD msgDesc, MQPMO putMsgOpts, ByteBuffer[] buffers, Pint compCode, Pint reason) {
/* 510 */     if (Trace.isOn) {
/* 511 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", new Object[] { hconn, objDesc, msgDesc, putMsgOpts, buffers, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 515 */     Object lock = this.locks.get(hconn);
/* 516 */     if (lock != null) {
/* 517 */       synchronized (lock) {
/* 518 */         ((JmqiSP)this.delegate).jmqiPut1(hconn, objDesc, msgDesc, putMsgOpts, buffers, compCode, reason);
/*     */       } 
/*     */     } else {
/*     */       
/* 522 */       compCode.x = 2;
/* 523 */       reason.x = 2018;
/*     */     } 
/* 525 */     if (Trace.isOn) {
/* 526 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)");
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
/*     */   public void jmqiPut1WithTriplets(Hconn hconn, MQOD objDesc, MQMD msgDesc, MQPMO putMsgOpts, ByteBuffer[] buffers, Triplet[] triplets, Pint compCode, Pint reason) {
/* 546 */     if (Trace.isOn) {
/* 547 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", new Object[] { hconn, objDesc, msgDesc, putMsgOpts, buffers, triplets, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 551 */     Object lock = this.locks.get(hconn);
/* 552 */     if (lock != null) {
/* 553 */       synchronized (lock) {
/* 554 */         ((JmqiSP)this.delegate).jmqiPut1WithTriplets(hconn, objDesc, msgDesc, putMsgOpts, buffers, triplets, compCode, reason);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 559 */       compCode.x = 2;
/* 560 */       reason.x = 2018;
/*     */     } 
/* 562 */     if (Trace.isOn) {
/* 563 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
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
/*     */   public void jmqiPutWithTriplets(Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, ByteBuffer[] buffers, Triplet[] triplets, Pint compCode, Pint reason) {
/* 583 */     if (Trace.isOn) {
/* 584 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "jmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", new Object[] { hconn, hobj, msgDesc, putMsgOpts, buffers, triplets, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 588 */     Object lock = this.locks.get(hconn);
/* 589 */     if (lock != null) {
/* 590 */       synchronized (lock) {
/* 591 */         ((JmqiSP)this.delegate).jmqiPutWithTriplets(hconn, hobj, msgDesc, putMsgOpts, buffers, triplets, compCode, reason);
/*     */       } 
/*     */     } else {
/*     */       
/* 595 */       compCode.x = 2;
/* 596 */       reason.x = 2018;
/*     */     } 
/* 598 */     if (Trace.isOn) {
/* 599 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "jmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
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
/*     */   public void spiConnect(String mgrName, SpiConnectOptions spiConnectOpts, MQCNO connectOpts, Phconn hconn, Pint compCode, Pint reason) {
/* 615 */     if (Trace.isOn) {
/* 616 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "spiConnect(String,SpiConnectOptions,MQCNO,Phconn,Pint,Pint)", new Object[] { mgrName, spiConnectOpts, connectOpts, hconn, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 620 */     ((JmqiSP)this.delegate).spiConnect(mgrName, (LpiPrivConnStruct)spiConnectOpts, connectOpts, hconn, compCode, reason);
/*     */     
/* 622 */     if (compCode.x == 2) {
/* 623 */       if (Trace.isOn) {
/* 624 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "spiConnect(String,SpiConnectOptions,MQCNO,Phconn,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 629 */     this.locks.put(hconn.getHconn(), new Lock());
/* 630 */     if (Trace.isOn) {
/* 631 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "spiConnect(String,SpiConnectOptions,MQCNO,Phconn,Pint,Pint)");
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
/*     */   public void spiGet(Hconn hconn, Hobj hobj, MQMD mqmd, MQGMO mqgmo, SpiGetOptions spiOptions, int bufferLength, ByteBuffer buffer, Pint dataLength, Pint compCode, Pint reason) {
/* 650 */     if (Trace.isOn) {
/* 651 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, mqmd, mqgmo, spiOptions, 
/*     */             
/* 653 */             Integer.valueOf(bufferLength), buffer, dataLength, compCode, reason });
/*     */     }
/*     */     
/* 656 */     Object lock = this.locks.get(hconn);
/* 657 */     if (lock != null) {
/* 658 */       synchronized (lock) {
/* 659 */         ((JmqiSP)this.delegate).spiGet(hconn, hobj, mqmd, mqgmo, spiOptions, bufferLength, buffer, dataLength, compCode, reason);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 664 */       compCode.x = 2;
/* 665 */       reason.x = 2018;
/*     */     } 
/* 667 */     if (Trace.isOn) {
/* 668 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)");
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
/*     */   public void spiOpen(Hconn hconn, MQOD od, SpiOpenOptions options, Phobj hobj, Pint compCode, Pint reason) {
/* 685 */     if (Trace.isOn) {
/* 686 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "spiOpen(Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)", new Object[] { hconn, od, options, hobj, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 690 */     Object lock = this.locks.get(hconn);
/* 691 */     if (lock != null) {
/* 692 */       synchronized (lock) {
/* 693 */         ((JmqiSP)this.delegate).spiOpen(hconn, od, options, hobj, compCode, reason);
/*     */       } 
/*     */     } else {
/*     */       
/* 697 */       compCode.x = 2;
/* 698 */       reason.x = 2018;
/*     */     } 
/* 700 */     if (Trace.isOn) {
/* 701 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "spiOpen(Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)");
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
/*     */   public void spiPut(Hconn hconn, Hobj hobj, MQMD mqmd, MQPMO mqpmo, SpiPutOptions spiPutOpts, int bufferLength, ByteBuffer buffer, Pint compCode, Pint reason) {
/* 720 */     if (Trace.isOn) {
/* 721 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, mqmd, mqpmo, spiPutOpts, 
/*     */             
/* 723 */             Integer.valueOf(bufferLength), buffer, compCode, reason });
/*     */     }
/* 725 */     Object lock = this.locks.get(hconn);
/* 726 */     if (lock != null) {
/* 727 */       synchronized (lock) {
/* 728 */         ((JmqiSP)this.delegate).spiPut(hconn, hobj, mqmd, mqpmo, spiPutOpts, bufferLength, buffer, compCode, reason);
/*     */       } 
/*     */     } else {
/*     */       
/* 732 */       compCode.x = 2;
/* 733 */       reason.x = 2018;
/*     */     } 
/* 735 */     if (Trace.isOn) {
/* 736 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)");
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
/*     */   public void spiPut(Hconn hconn, Hobj hobj, MQMD mqmd, MQPMO mqpmo, SpiPutOptions spiPutOpts, ByteBuffer[] buffers, Pint compCode, Pint reason) {
/* 755 */     if (Trace.isOn) {
/* 756 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],Pint,Pint)", new Object[] { hconn, hobj, mqmd, mqpmo, spiPutOpts, buffers, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 760 */     Object lock = this.locks.get(hconn);
/* 761 */     if (lock != null) {
/* 762 */       synchronized (lock) {
/* 763 */         ((JmqiSP)this.delegate).spiPut(hconn, hobj, mqmd, mqpmo, spiPutOpts, buffers, compCode, reason);
/*     */       } 
/*     */     } else {
/*     */       
/* 767 */       compCode.x = 2;
/* 768 */       reason.x = 2018;
/*     */     } 
/* 770 */     if (Trace.isOn) {
/* 771 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],Pint,Pint)");
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
/*     */   public void MQSUB(Hconn hconn, MQSD subDesc, Phobj hobj, Phobj hsub, Pint compCode, Pint reason) {
/* 788 */     if (Trace.isOn) {
/* 789 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", new Object[] { hconn, subDesc, hobj, hsub, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 793 */     Object lock = this.locks.get(hconn);
/* 794 */     if (lock != null) {
/* 795 */       synchronized (lock) {
/* 796 */         this.delegate.MQSUB(hconn, subDesc, hobj, hsub, compCode, reason);
/*     */       } 
/*     */     } else {
/* 799 */       compCode.x = 2;
/* 800 */       reason.x = 2018;
/*     */     } 
/* 802 */     if (Trace.isOn) {
/* 803 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)");
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
/*     */   public void spiSubscribe(Hconn hconn, LpiSD plpiSD, MQSD subDesc, Phobj hobj, Phobj hsub, Pint compCode, Pint reason) {
/* 821 */     if (Trace.isOn) {
/* 822 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", new Object[] { hconn, plpiSD, subDesc, hobj, hsub, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 826 */     Object lock = this.locks.get(hconn);
/* 827 */     if (lock != null) {
/* 828 */       synchronized (lock) {
/* 829 */         ((JmqiSP)this.delegate).spiSubscribe(hconn, plpiSD, subDesc, hobj, hsub, compCode, reason);
/*     */       } 
/*     */     } else {
/*     */       
/* 833 */       compCode.x = 2;
/* 834 */       reason.x = 2018;
/*     */     } 
/* 836 */     if (Trace.isOn)
/* 837 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ConnSerializingJmqiImpl", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\jmqi\ConnSerializingJmqiImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */