/*     */ package com.ibm.mq.ese.intercept;
/*     */ 
/*     */ import com.ibm.mq.constants.QmgrSplCapability;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.mq.ese.service.EseMQException;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.MQCNO;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Phconn;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.mq.jmqi.system.SpiConnectOptions;
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
/*     */ public class JmqiConnInterceptorImpl
/*     */   extends AbstractJmqiInterceptor
/*     */   implements JmqiConnInterceptor
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiConnInterceptorImpl.java";
/*     */   
/*     */   static {
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.data("com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiConnInterceptorImpl.java");
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
/*  66 */   private static final String CLASS = JmqiConnInterceptorImpl.class.getName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void beforeJmqiConnect(String mgrName, JmqiConnectOptions jmqiConnectOpts, MQCNO connectOpts, Hconn parentHconn, Phconn pHconn, Pint pCompCode, Pint pReason) {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "beforeJmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", new Object[] { mgrName, jmqiConnectOpts, connectOpts, parentHconn, pHconn, pCompCode, pReason });
/*     */     }
/*     */ 
/*     */     
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "beforeJmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)");
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
/*     */   public void beforeMQCONN(String mgrName, Phconn pHconn, Pint pCompCode, Pint pReason) {
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "beforeMQCONN(String,Phconn,Pint,Pint)", new Object[] { mgrName, pHconn, pCompCode, pReason });
/*     */     }
/*     */     
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "beforeMQCONN(String,Phconn,Pint,Pint)");
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
/*     */   public void beforeMQCONNX(String mgrName, MQCNO connectOpts, Phconn pHconn, Pint pCompCode, Pint pReason) {
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "beforeMQCONNX(String,MQCNO,Phconn,Pint,Pint)", new Object[] { mgrName, connectOpts, pHconn, pCompCode, pReason });
/*     */     }
/*     */ 
/*     */     
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "beforeMQCONNX(String,MQCNO,Phconn,Pint,Pint)");
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
/*     */   public void beforeSpiConnect(String mgrName, SpiConnectOptions spiConnectOpts, MQCNO connectOpts, Phconn pHconn, Pint pCompCode, Pint pReason) {
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "beforeSpiConnect(String,SpiConnectOptions,MQCNO,Phconn,Pint,Pint)", new Object[] { mgrName, spiConnectOpts, connectOpts, pHconn, pCompCode, pReason });
/*     */     }
/*     */ 
/*     */     
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "beforeSpiConnect(String,SpiConnectOptions,MQCNO,Phconn,Pint,Pint)");
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
/*     */   public void afterJmqiConnect(QmgrSplCapability capability, String mgrName, JmqiConnectOptions jmqiConnectOpts, MQCNO connectOpts, Hconn parentHconn, Phconn pHconn, Pint pCc, Pint pRc) {
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "afterJmqiConnect(QmgrSplCapability,String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", new Object[] { capability, mgrName, jmqiConnectOpts, connectOpts, parentHconn, pHconn, pCc, pRc });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 165 */     afterConnect(capability, mgrName, pHconn.getHconn(), pCc, pRc);
/*     */     
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "afterJmqiConnect(QmgrSplCapability,String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)");
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
/*     */   public void afterMQCONN(QmgrSplCapability capability, String mgrName, Phconn pHconn, Pint pCc, Pint pRc) {
/* 183 */     if (Trace.isOn) {
/* 184 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "afterMQCONN(QmgrSplCapability,String,Phconn,Pint,Pint)", new Object[] { capability, mgrName, pHconn, pCc, pRc });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 189 */     afterConnect(capability, mgrName, pHconn.getHconn(), pCc, pRc);
/*     */     
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "afterMQCONN(QmgrSplCapability,String,Phconn,Pint,Pint)");
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
/*     */   public void afterMQCONNX(QmgrSplCapability capability, String mgrName, MQCNO connectOpts, Phconn pHconn, Pint pCc, Pint pRc) {
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "afterMQCONNX(QmgrSplCapability,String,MQCNO,Phconn,Pint,Pint)", new Object[] { capability, mgrName, connectOpts, pHconn, pCc, pRc });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 214 */     afterConnect(capability, mgrName, pHconn.getHconn(), pCc, pRc);
/*     */     
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "afterMQCONNX(QmgrSplCapability,String,MQCNO,Phconn,Pint,Pint)");
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
/*     */   public void afterSpiConnect(QmgrSplCapability capability, String mgrName, SpiConnectOptions spiConnOpts, MQCNO connectOpts, Phconn pHconn, Pint pCc, Pint pRc) {
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "afterSpiConnect(QmgrSplCapability,String,SpiConnectOptions,MQCNO,Phconn,Pint,Pint)", new Object[] { capability, mgrName, spiConnOpts, connectOpts, pHconn, pCc, pRc });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 240 */     afterConnect(capability, mgrName, pHconn.getHconn(), pCc, pRc);
/*     */     
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "afterSpiConnect(QmgrSplCapability,String,SpiConnectOptions,MQCNO,Phconn,Pint,Pint)");
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
/*     */   private void afterConnect(QmgrSplCapability capability, String mgrName, Hconn hconn, Pint cc, Pint rc) {
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "afterConnect(QmgrSplCapability,String,Hconn,Pint,Pint)", new Object[] { capability, mgrName, hconn, cc, rc });
/*     */     }
/*     */ 
/*     */     
/* 262 */     if (cc.x == 2) {
/*     */       return;
/*     */     }
/*     */     try {
/* 266 */       validate(mgrName, hconn, cc, rc);
/* 267 */       String name = pickQmgrName(mgrName, hconn);
/* 268 */       String qmgrDlq = pickQmgrDlqName(hconn);
/* 269 */       this.contextContainer.newContext(capability, hconn, name, qmgrDlq);
/*     */     }
/* 271 */     catch (JmqiException e) {
/* 272 */       if (Trace.isOn) {
/* 273 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "afterConnect(QmgrSplCapability,String,Hconn,Pint,Pint)", (Throwable)e, 1);
/*     */       }
/*     */       
/* 276 */       AmsErrorMessages.logException(CLASS, "afterConnect(QmgrSplCapability,String,Hconn,Pint,Pint)", (Throwable)e);
/* 277 */       if (Trace.isOn) {
/* 278 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "afterConnect(String, Hconn, Pint, Pint)", (Throwable)e);
/*     */       }
/*     */       
/* 281 */       setErrorReasonCode(cc, rc, e.getReason());
/* 282 */       JmqiTls jTls = getTls();
/* 283 */       jTls.lastException = e;
/*     */     }
/* 285 */     catch (EseMQException e) {
/* 286 */       if (Trace.isOn) {
/* 287 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "afterConnect(QmgrSplCapability,String,Hconn,Pint,Pint)", (Throwable)e, 2);
/*     */       }
/*     */ 
/*     */       
/* 291 */       AmsErrorMessages.logException(CLASS, "afterConnect(QmgrSplCapability,String,Hconn,Pint,Pint)", (Throwable)e);
/* 292 */       if (Trace.isOn) {
/* 293 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "afterConnect(String, Hconn, Pint, Pint)", (Throwable)e);
/*     */       }
/*     */       
/* 296 */       setErrorReasonCode(cc, rc, e.getReason());
/*     */     } 
/* 298 */     if (Trace.isOn) {
/* 299 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "afterConnect(QmgrSplCapability,String,Hconn,Pint,Pint)");
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
/*     */   private String pickQmgrDlqName(Hconn hconn) throws EseMQException {
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "pickQmgrDlqName(Hconn)", new Object[] { hconn });
/*     */     }
/*     */     
/* 317 */     String dlqName = this.mqService.inqQmgrDlq(hconn);
/* 318 */     if (dlqName == null) {
/* 319 */       dlqName = "";
/*     */     }
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "pickQmgrDlqName(Hconn)", dlqName);
/*     */     }
/*     */     
/* 325 */     return dlqName;
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
/*     */   private String pickQmgrName(String mgrName, Hconn hconn) throws JmqiException {
/* 337 */     if (Trace.isOn) {
/* 338 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "pickQmgrName(String,Hconn)", new Object[] { mgrName, hconn });
/*     */     }
/*     */     
/* 341 */     String name = hconn.getName();
/* 342 */     if (name == null || name.trim().length() == 0) {
/* 343 */       name = mgrName;
/*     */     }
/* 345 */     if (name == null || name.trim().length() == 0) {
/* 346 */       JmqiException traceRet1 = new JmqiException(this.env, -1, new String[0], 2, 2058, new IllegalArgumentException("No queue manager name must not be empty"));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 351 */       if (Trace.isOn) {
/* 352 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "pickQmgrName(String,Hconn)", (Throwable)traceRet1);
/*     */       }
/*     */       
/* 355 */       throw traceRet1;
/*     */     } 
/* 357 */     String traceRet2 = name.trim();
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "pickQmgrName(String,Hconn)", traceRet2);
/*     */     }
/*     */     
/* 362 */     return traceRet2;
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
/*     */   private void validate(String mgrName, Hconn hconn, Pint cc, Pint rc) throws JmqiException {
/* 375 */     if (Trace.isOn) {
/* 376 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "validate(String,Hconn,Pint,Pint)", new Object[] { mgrName, hconn, cc, rc });
/*     */     }
/*     */     
/* 379 */     if (cc == null) {
/* 380 */       JmqiException traceRet1 = new JmqiException(this.env, -1, new String[0], 2, 2195, new NullPointerException());
/*     */ 
/*     */ 
/*     */       
/* 384 */       if (Trace.isOn) {
/* 385 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "validate(String,Hconn,Pint,Pint)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 388 */       throw traceRet1;
/*     */     } 
/* 390 */     if (rc == null) {
/* 391 */       JmqiException traceRet2 = new JmqiException(this.env, -1, new String[0], 2, 2195, new NullPointerException());
/*     */ 
/*     */ 
/*     */       
/* 395 */       if (Trace.isOn) {
/* 396 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "validate(String,Hconn,Pint,Pint)", (Throwable)traceRet2, 2);
/*     */       }
/*     */       
/* 399 */       throw traceRet2;
/*     */     } 
/* 401 */     if (hconn == null) {
/* 402 */       JmqiException traceRet3 = new JmqiException(this.env, -1, new String[0], 2, 2018, new NullPointerException());
/*     */ 
/*     */ 
/*     */       
/* 406 */       if (Trace.isOn) {
/* 407 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "validate(String,Hconn,Pint,Pint)", (Throwable)traceRet3, 3);
/*     */       }
/*     */       
/* 410 */       throw traceRet3;
/*     */     } 
/* 412 */     if (hconn != null && 
/* 413 */       hconn.getName() == null && mgrName == null) {
/* 414 */       JmqiException traceRet4 = new JmqiException(this.env, -1, new String[0], 2, 2058, new NullPointerException());
/*     */ 
/*     */ 
/*     */       
/* 418 */       if (Trace.isOn) {
/* 419 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "validate(String,Hconn,Pint,Pint)", (Throwable)traceRet4, 4);
/*     */       }
/*     */       
/* 422 */       throw traceRet4;
/*     */     } 
/*     */     
/* 425 */     if (Trace.isOn)
/* 426 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiConnInterceptorImpl", "validate(String,Hconn,Pint,Pint)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\JmqiConnInterceptorImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */