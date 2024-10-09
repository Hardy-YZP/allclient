/*     */ package com.ibm.mq.ese.jmqi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
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
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ESEJMQI
/*     */   extends AbstractJMQISupport
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/jmqi/ESEJMQI.java";
/*     */   private static final String MQS_INTERCEPT_SERIALIZECONN = "mqs.intercept.serializeconn";
/*     */   private static final String MQS_INTERCEPT_BINDINGS = "mqs.intercept.bindings";
/*     */   private static final String MQS_DISABLE_ALL_INTERCEPT = "mqs.disable.all.intercept";
/*     */   
/*     */   static {
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.data("com.ibm.mq.ese.jmqi.ESEJMQI", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/jmqi/ESEJMQI.java");
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
/*     */   private boolean shouldIntercept = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ESEJMQI(JmqiEnvironment env, int options, JmqiMQ mq) throws JmqiException {
/* 106 */     super(env, options, mq);
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "<init>(JmqiEnvironment,int,JmqiMQ)", new Object[] { env, 
/* 109 */             Integer.valueOf(options), mq });
/*     */     }
/* 111 */     if (shouldDisableAllInterceptors() || (mq
/* 112 */       .isLocal() && !shouldInterceptBindingsMode())) {
/* 113 */       this.delegate = mq;
/* 114 */       this.shouldIntercept = false;
/*     */     }
/* 116 */     else if (shouldSerializeConn()) {
/* 117 */       InterceptedJmqiImpl target = new InterceptedJmqiImpl(env, options, mq);
/*     */       
/* 119 */       this.delegate = (JmqiMQ)new ConnSerializingJmqiImpl(env, options, (JmqiMQ)target);
/*     */     } else {
/* 121 */       this.delegate = (JmqiMQ)new InterceptedJmqiImpl(env, options, mq);
/*     */     } 
/*     */     
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "<init>(JmqiEnvironment,int,JmqiMQ)");
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
/*     */   private boolean shouldSerializeConn() {
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "shouldSerializeConn()");
/*     */     }
/*     */     
/* 141 */     String serialize = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*     */         {
/*     */           public String run()
/*     */           {
/* 145 */             if (Trace.isOn) {
/* 146 */               Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "run()");
/*     */             }
/*     */             try {
/* 149 */               String traceRet1 = System.getProperty("mqs.intercept.serializeconn");
/* 150 */               if (Trace.isOn) {
/* 151 */                 Trace.exit(this, "com.ibm.mq.ese.jmqi.null", "run()", traceRet1, 1);
/*     */               }
/* 153 */               return traceRet1;
/*     */             }
/* 155 */             catch (Throwable t) {
/* 156 */               if (Trace.isOn) {
/* 157 */                 Trace.catchBlock(this, "com.ibm.mq.ese.jmqi.null", "run()", t);
/*     */               }
/*     */               
/* 160 */               if (Trace.isOn) {
/* 161 */                 Trace.exit(this, "com.ibm.mq.ese.jmqi.null", "run()", null, 2);
/*     */               }
/* 163 */               return null;
/*     */             } 
/*     */           }
/*     */         });
/* 167 */     boolean traceRet2 = (serialize != null);
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "shouldSerializeConn()", 
/* 170 */           Boolean.valueOf(traceRet2));
/*     */     }
/* 172 */     return traceRet2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean shouldDisableAllInterceptors() {
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "shouldDisableAllInterceptors()");
/*     */     }
/*     */     
/* 183 */     String intercept = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*     */         {
/*     */           public String run()
/*     */           {
/* 187 */             if (Trace.isOn) {
/* 188 */               Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "run()");
/*     */             }
/*     */             try {
/* 191 */               String traceRet1 = System.getProperty("mqs.disable.all.intercept");
/* 192 */               if (Trace.isOn) {
/* 193 */                 Trace.exit(this, "com.ibm.mq.ese.jmqi.null", "run()", traceRet1, 1);
/*     */               }
/* 195 */               return traceRet1;
/*     */             }
/* 197 */             catch (Throwable t) {
/* 198 */               if (Trace.isOn) {
/* 199 */                 Trace.catchBlock(this, "com.ibm.mq.ese.jmqi.null", "run()", t);
/*     */               }
/*     */               
/* 202 */               if (Trace.isOn) {
/* 203 */                 Trace.exit(this, "com.ibm.mq.ese.jmqi.null", "run()", null, 2);
/*     */               }
/* 205 */               return null;
/*     */             } 
/*     */           }
/*     */         });
/* 209 */     boolean traceRet2 = (intercept != null);
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "shouldDisableAllInterceptors()", 
/* 212 */           Boolean.valueOf(traceRet2));
/*     */     }
/* 214 */     return traceRet2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean shouldInterceptBindingsMode() {
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "shouldInterceptBindingsMode()");
/*     */     }
/*     */     
/* 225 */     String intercept = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*     */         {
/*     */           public String run()
/*     */           {
/* 229 */             if (Trace.isOn) {
/* 230 */               Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "run()");
/*     */             }
/*     */             try {
/* 233 */               String traceRet1 = System.getProperty("mqs.intercept.bindings");
/* 234 */               if (Trace.isOn) {
/* 235 */                 Trace.exit(this, "com.ibm.mq.ese.jmqi.null", "run()", traceRet1, 1);
/*     */               }
/* 237 */               return traceRet1;
/*     */             }
/* 239 */             catch (Throwable t) {
/* 240 */               if (Trace.isOn) {
/* 241 */                 Trace.catchBlock(this, "com.ibm.mq.ese.jmqi.null", "run()", t);
/*     */               }
/*     */               
/* 244 */               if (Trace.isOn) {
/* 245 */                 Trace.exit(this, "com.ibm.mq.ese.jmqi.null", "run()", null, 2);
/*     */               }
/* 247 */               return null;
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 252 */     boolean traceRet2 = (intercept != null);
/* 253 */     if (Trace.isOn) {
/* 254 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "shouldInterceptBindingsMode()", 
/* 255 */           Boolean.valueOf(traceRet2));
/*     */     }
/* 257 */     return traceRet2;
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
/*     */   public void MQCB(Hconn hconn, int operation, MQCBD callbackDesc, Hobj hobj, MQMD msgDesc, MQGMO getMsgOpts, Pint compCode, Pint reason) {
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)", new Object[] { hconn, 
/*     */             
/* 274 */             Integer.valueOf(operation), callbackDesc, hobj, msgDesc, getMsgOpts, compCode, reason });
/*     */     }
/* 276 */     if (!this.shouldIntercept) {
/* 277 */       super.MQCB(hconn, operation, callbackDesc, hobj, msgDesc, getMsgOpts, compCode, reason);
/*     */       
/* 279 */       if (Trace.isOn) {
/* 280 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 285 */     this.delegate.MQCB(hconn, operation, callbackDesc, hobj, msgDesc, getMsgOpts, compCode, reason);
/*     */     
/* 287 */     if (Trace.isOn) {
/* 288 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)");
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
/* 304 */     if (Trace.isOn) {
/* 305 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", new Object[] { hconn, hobj, 
/* 306 */             Integer.valueOf(Options), compCode, reason });
/*     */     }
/* 308 */     if (!this.shouldIntercept) {
/* 309 */       super.MQCLOSE(hconn, hobj, Options, compCode, reason);
/* 310 */       if (Trace.isOn) {
/* 311 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)");
/*     */       }
/*     */       return;
/*     */     } 
/* 315 */     this.delegate.MQCLOSE(hconn, hobj, Options, compCode, reason);
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)");
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
/*     */   public void MQCONN(String mgrName, Phconn hconn, Pint compCode, Pint reason) {
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQCONN(String,Phconn,Pint,Pint)", new Object[] { mgrName, hconn, compCode, reason });
/*     */     }
/*     */     
/* 335 */     if (!this.shouldIntercept) {
/* 336 */       super.MQCONN(mgrName, hconn, compCode, reason);
/* 337 */       if (Trace.isOn) {
/* 338 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQCONN(String,Phconn,Pint,Pint)");
/*     */       }
/*     */       return;
/*     */     } 
/* 342 */     this.delegate.MQCONN(mgrName, hconn, compCode, reason);
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQCONN(String,Phconn,Pint,Pint)");
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
/*     */   public void MQCONNX(String mgrName, MQCNO connectOpts, Phconn hconn, Pint compCode, Pint reason) {
/* 359 */     if (Trace.isOn) {
/* 360 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQCONNX(String,MQCNO,Phconn,Pint,Pint)", new Object[] { mgrName, connectOpts, hconn, compCode, reason });
/*     */     }
/*     */     
/* 363 */     if (!this.shouldIntercept) {
/* 364 */       super.MQCONNX(mgrName, connectOpts, hconn, compCode, reason);
/* 365 */       if (Trace.isOn) {
/* 366 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQCONNX(String,MQCNO,Phconn,Pint,Pint)");
/*     */       }
/*     */       return;
/*     */     } 
/* 370 */     this.delegate.MQCONNX(mgrName, connectOpts, hconn, compCode, reason);
/* 371 */     if (Trace.isOn) {
/* 372 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQCONNX(String,MQCNO,Phconn,Pint,Pint)");
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
/*     */   public void MQDISC(Phconn hconn, Pint compCode, Pint reason) {
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQDISC(Phconn,Pint,Pint)", new Object[] { hconn, compCode, reason });
/*     */     }
/*     */     
/* 389 */     if (!this.shouldIntercept) {
/* 390 */       super.MQDISC(hconn, compCode, reason);
/* 391 */       if (Trace.isOn) {
/* 392 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQDISC(Phconn,Pint,Pint)");
/*     */       }
/*     */       return;
/*     */     } 
/* 396 */     this.delegate.MQDISC(hconn, compCode, reason);
/* 397 */     if (Trace.isOn) {
/* 398 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQDISC(Phconn,Pint,Pint)");
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
/* 416 */     if (Trace.isOn) {
/* 417 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, msgDesc, getMsgOpts, 
/*     */             
/* 419 */             Integer.valueOf(BufferLength), buffer, dataLength, compCode, reason });
/*     */     }
/* 421 */     if (!this.shouldIntercept) {
/* 422 */       super.MQGET(hconn, hobj, msgDesc, getMsgOpts, BufferLength, buffer, dataLength, compCode, reason);
/*     */       
/* 424 */       if (Trace.isOn) {
/* 425 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 430 */     this.delegate.MQGET(hconn, hobj, msgDesc, getMsgOpts, BufferLength, buffer, dataLength, compCode, reason);
/*     */     
/* 432 */     if (Trace.isOn) {
/* 433 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)");
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
/* 449 */     if (Trace.isOn) {
/* 450 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", new Object[] { hconn, objDesc, 
/* 451 */             Integer.valueOf(Options), hobj, compCode, reason });
/*     */     }
/* 453 */     if (!this.shouldIntercept) {
/* 454 */       super.MQOPEN(hconn, objDesc, Options, hobj, compCode, reason);
/* 455 */       if (Trace.isOn) {
/* 456 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)");
/*     */       }
/*     */       return;
/*     */     } 
/* 460 */     this.delegate.MQOPEN(hconn, objDesc, Options, hobj, compCode, reason);
/* 461 */     if (Trace.isOn) {
/* 462 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)");
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
/*     */   public void MQPUT(Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, int BufferLength, ByteBuffer buffer, Pint compCode, Pint reason) {
/* 478 */     if (Trace.isOn) {
/* 479 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, msgDesc, putMsgOpts, 
/*     */             
/* 481 */             Integer.valueOf(BufferLength), buffer, compCode, reason });
/*     */     }
/* 483 */     if (!this.shouldIntercept) {
/* 484 */       super.MQPUT(hconn, hobj, msgDesc, putMsgOpts, BufferLength, buffer, compCode, reason);
/*     */       
/* 486 */       if (Trace.isOn) {
/* 487 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 492 */     this.delegate.MQPUT(hconn, hobj, msgDesc, putMsgOpts, BufferLength, buffer, compCode, reason);
/*     */     
/* 494 */     if (Trace.isOn) {
/* 495 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)");
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
/* 513 */     if (Trace.isOn) {
/* 514 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, objDesc, msgDesc, putMsgOpts, 
/*     */             
/* 516 */             Integer.valueOf(BufferLength), buffer, compCode, reason });
/*     */     }
/* 518 */     if (!this.shouldIntercept) {
/* 519 */       super.MQPUT1(hconn, objDesc, msgDesc, putMsgOpts, BufferLength, buffer, compCode, reason);
/*     */       
/* 521 */       if (Trace.isOn) {
/* 522 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 527 */     this.delegate.MQPUT1(hconn, objDesc, msgDesc, putMsgOpts, BufferLength, buffer, compCode, reason);
/*     */     
/* 529 */     if (Trace.isOn) {
/* 530 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)");
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
/* 548 */     if (Trace.isOn) {
/* 549 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", new Object[] { mgrName, jmqiConnectOpts, connectOpts, parentHconn, hconn, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 553 */     if (!this.shouldIntercept) {
/* 554 */       super.jmqiConnect(mgrName, jmqiConnectOpts, connectOpts, parentHconn, hconn, compCode, reason);
/*     */       
/* 556 */       if (Trace.isOn) {
/* 557 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 562 */     ((JmqiSP)this.delegate).jmqiConnect(mgrName, jmqiConnectOpts, connectOpts, parentHconn, hconn, compCode, reason);
/*     */     
/* 564 */     if (Trace.isOn) {
/* 565 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)");
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
/* 585 */     if (Trace.isOn) {
/* 586 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", new Object[] { hconn, hobj, md, gmo, 
/*     */             
/* 588 */             Integer.valueOf(expectedMsgLength), Integer.valueOf(maxMsgLength), byteBuffer, msgTooSmallForBufferCount, dataLength, compCode, reason });
/*     */     }
/*     */     
/* 591 */     ByteBuffer ret = null;
/* 592 */     if (!this.shouldIntercept) {
/* 593 */       ret = super.jmqiGet(hconn, hobj, md, gmo, expectedMsgLength, maxMsgLength, byteBuffer, msgTooSmallForBufferCount, dataLength, compCode, reason);
/*     */ 
/*     */       
/* 596 */       if (Trace.isOn) {
/* 597 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", ret, 1);
/*     */       }
/*     */       
/* 600 */       return ret;
/*     */     } 
/* 602 */     ret = ((JmqiSP)this.delegate).jmqiGet(hconn, hobj, md, gmo, expectedMsgLength, maxMsgLength, byteBuffer, msgTooSmallForBufferCount, dataLength, compCode, reason);
/*     */ 
/*     */     
/* 605 */     if (Trace.isOn) {
/* 606 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", ret, 2);
/*     */     }
/*     */     
/* 609 */     return ret;
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
/* 623 */     if (Trace.isOn) {
/* 624 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", new Object[] { hconn, hobj, msgDesc, putMsgOpts, buffers, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 628 */     if (!this.shouldIntercept) {
/* 629 */       super.jmqiPut(hconn, hobj, msgDesc, putMsgOpts, buffers, compCode, reason);
/*     */       
/* 631 */       if (Trace.isOn) {
/* 632 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 637 */     ((JmqiSP)this.delegate).jmqiPut(hconn, hobj, msgDesc, putMsgOpts, buffers, compCode, reason);
/*     */     
/* 639 */     if (Trace.isOn) {
/* 640 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)");
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
/* 658 */     if (Trace.isOn) {
/* 659 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", new Object[] { hconn, objDesc, msgDesc, putMsgOpts, buffers, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 663 */     if (!this.shouldIntercept) {
/* 664 */       super.jmqiPut1(hconn, objDesc, msgDesc, putMsgOpts, buffers, compCode, reason);
/*     */       
/* 666 */       if (Trace.isOn) {
/* 667 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 672 */     ((JmqiSP)this.delegate).jmqiPut1(hconn, objDesc, msgDesc, putMsgOpts, buffers, compCode, reason);
/*     */     
/* 674 */     if (Trace.isOn) {
/* 675 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)");
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
/* 695 */     if (Trace.isOn) {
/* 696 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", new Object[] { hconn, objDesc, msgDesc, putMsgOpts, buffers, triplets, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 700 */     if (!this.shouldIntercept) {
/* 701 */       super.jmqiPut1WithTriplets(hconn, objDesc, msgDesc, putMsgOpts, buffers, triplets, compCode, reason);
/*     */       
/* 703 */       if (Trace.isOn) {
/* 704 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 709 */     ((JmqiSP)this.delegate).jmqiPut1WithTriplets(hconn, objDesc, msgDesc, putMsgOpts, buffers, triplets, compCode, reason);
/*     */     
/* 711 */     if (Trace.isOn) {
/* 712 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
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
/* 732 */     if (Trace.isOn) {
/* 733 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", new Object[] { hconn, hobj, msgDesc, putMsgOpts, buffers, triplets, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 737 */     if (!this.shouldIntercept) {
/* 738 */       super.jmqiPutWithTriplets(hconn, hobj, msgDesc, putMsgOpts, buffers, triplets, compCode, reason);
/*     */       
/* 740 */       if (Trace.isOn) {
/* 741 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 746 */     ((JmqiSP)this.delegate).jmqiPutWithTriplets(hconn, hobj, msgDesc, putMsgOpts, buffers, triplets, compCode, reason);
/*     */     
/* 748 */     if (Trace.isOn) {
/* 749 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "jmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
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
/* 765 */     if (Trace.isOn) {
/* 766 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiConnect(String,SpiConnectOptions,MQCNO,Phconn,Pint,Pint)", new Object[] { mgrName, spiConnectOpts, connectOpts, hconn, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 770 */     if (!this.shouldIntercept) {
/* 771 */       spiConnect(mgrName, (LpiPrivConnStruct)spiConnectOpts, connectOpts, hconn, compCode, reason);
/*     */       
/* 773 */       if (Trace.isOn) {
/* 774 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiConnect(String,SpiConnectOptions,MQCNO,Phconn,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 779 */     ((JmqiSP)this.delegate).spiConnect(mgrName, (LpiPrivConnStruct)spiConnectOpts, connectOpts, hconn, compCode, reason);
/*     */     
/* 781 */     if (Trace.isOn) {
/* 782 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiConnect(String,SpiConnectOptions,MQCNO,Phconn,Pint,Pint)");
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
/* 801 */     if (Trace.isOn) {
/* 802 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, mqmd, mqgmo, spiOptions, 
/*     */             
/* 804 */             Integer.valueOf(bufferLength), buffer, dataLength, compCode, reason });
/*     */     }
/*     */     
/* 807 */     if (!this.shouldIntercept) {
/* 808 */       super.spiGet(hconn, hobj, mqmd, mqgmo, spiOptions, bufferLength, buffer, dataLength, compCode, reason);
/*     */       
/* 810 */       if (Trace.isOn) {
/* 811 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 816 */     ((JmqiSP)this.delegate).spiGet(hconn, hobj, mqmd, mqgmo, spiOptions, bufferLength, buffer, dataLength, compCode, reason);
/*     */     
/* 818 */     if (Trace.isOn) {
/* 819 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)");
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
/* 836 */     if (Trace.isOn) {
/* 837 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiOpen(Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)", new Object[] { hconn, od, options, hobj, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 841 */     if (!this.shouldIntercept) {
/* 842 */       super.spiOpen(hconn, od, options, hobj, compCode, reason);
/* 843 */       if (Trace.isOn) {
/* 844 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiOpen(Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 849 */     ((JmqiSP)this.delegate).spiOpen(hconn, od, options, hobj, compCode, reason);
/* 850 */     if (Trace.isOn) {
/* 851 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiOpen(Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)");
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
/* 870 */     if (Trace.isOn) {
/* 871 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, mqmd, mqpmo, spiPutOpts, 
/*     */             
/* 873 */             Integer.valueOf(bufferLength), buffer, compCode, reason });
/*     */     }
/* 875 */     if (!this.shouldIntercept) {
/* 876 */       super.spiPut(hconn, hobj, mqmd, mqpmo, spiPutOpts, bufferLength, buffer, compCode, reason);
/*     */       
/* 878 */       if (Trace.isOn) {
/* 879 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 884 */     ((JmqiSP)this.delegate).spiPut(hconn, hobj, mqmd, mqpmo, spiPutOpts, bufferLength, buffer, compCode, reason);
/*     */     
/* 886 */     if (Trace.isOn) {
/* 887 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)");
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
/* 906 */     if (Trace.isOn) {
/* 907 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],Pint,Pint)", new Object[] { hconn, hobj, mqmd, mqpmo, spiPutOpts, buffers, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 911 */     if (!this.shouldIntercept) {
/* 912 */       super.spiPut(hconn, hobj, mqmd, mqpmo, spiPutOpts, buffers, compCode, reason);
/*     */       
/* 914 */       if (Trace.isOn) {
/* 915 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 920 */     ((JmqiSP)this.delegate).spiPut(hconn, hobj, mqmd, mqpmo, spiPutOpts, buffers, compCode, reason);
/*     */     
/* 922 */     if (Trace.isOn) {
/* 923 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],Pint,Pint)");
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
/* 940 */     if (Trace.isOn) {
/* 941 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", new Object[] { hconn, subDesc, hobj, hsub, compCode, reason });
/*     */     }
/*     */     
/* 944 */     if (!this.shouldIntercept) {
/* 945 */       super.MQSUB(hconn, subDesc, hobj, hsub, compCode, reason);
/* 946 */       if (Trace.isOn) {
/* 947 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 952 */     this.delegate.MQSUB(hconn, subDesc, hobj, hsub, compCode, reason);
/* 953 */     if (Trace.isOn) {
/* 954 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)");
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
/*     */   public void spiSubscribe(Hconn hconn, LpiSD plpiSD, MQSD subDesc, Phobj hobj, Phobj hsub, Pint compCode, Pint reason) {
/* 971 */     if (Trace.isOn) {
/* 972 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", new Object[] { hconn, plpiSD, subDesc, hobj, hsub, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 976 */     if (!this.shouldIntercept) {
/* 977 */       super.spiSubscribe(hconn, plpiSD, subDesc, hobj, hsub, compCode, reason);
/*     */       
/* 979 */       if (Trace.isOn) {
/* 980 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 985 */     ((JmqiSP)this.delegate).spiSubscribe(hconn, plpiSD, subDesc, hobj, hsub, compCode, reason);
/*     */     
/* 987 */     if (Trace.isOn)
/* 988 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.ESEJMQI", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\jmqi\ESEJMQI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */