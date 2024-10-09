/*     */ package com.ibm.mq.ese.intercept;
/*     */ 
/*     */ import com.ibm.mq.ese.core.AMBIException;
/*     */ import com.ibm.mq.ese.core.AMBIHeader;
/*     */ import com.ibm.mq.ese.core.EseUser;
/*     */ import com.ibm.mq.ese.core.SecurityPolicy;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.mq.ese.service.EseMQException;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.MQMD;
/*     */ import com.ibm.mq.jmqi.MQOD;
/*     */ import com.ibm.mq.jmqi.MQPMO;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.mq.jmqi.system.SpiPutOptions;
/*     */ import com.ibm.mq.jmqi.system.zrfp.Triplet;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmqiPutInterceptorImpl
/*     */   extends AbstractJmqiInterceptor
/*     */   implements JmqiPutInterceptor
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiPutInterceptorImpl.java";
/*     */   
/*     */   static {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.data("com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiPutInterceptorImpl.java");
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
/*  74 */   private static final String CLASS = JmqiPutInterceptorImpl.class.getName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQPutContext beforeJmqiPut(Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, ByteBuffer[] buffers, Pint cc, Pint rc) {
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforeJmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", new Object[] { hconn, hobj, msgDesc, putMsgOpts, buffers, cc, rc });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  96 */     MessageBufferProcessor mbproc = new MessageBufferProcessorImpl(this.env, this.jmqi);
/*     */     
/*  98 */     mbproc.wrap(msgDesc, buffers);
/*  99 */     MQPutContext putContext = beforePut(new MQPutParameters(hconn, hobj, msgDesc, putMsgOpts, cc, rc), mbproc);
/*     */ 
/*     */     
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforeJmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", putContext);
/*     */     }
/*     */     
/* 106 */     return putContext;
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
/*     */   public MQPutContext beforeMQPUT(Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, int bufferLength, ByteBuffer buffer, Pint cc, Pint rc) {
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforeMQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, msgDesc, putMsgOpts, 
/*     */             
/* 127 */             Integer.valueOf(bufferLength), buffer, cc, rc });
/*     */     }
/*     */     
/* 130 */     MessageBufferProcessor mbproc = new MessageBufferProcessorImpl(this.env, this.jmqi);
/*     */     
/* 132 */     mbproc.wrap(msgDesc, buffer);
/* 133 */     MQPutContext putContext = beforePut(new MQPutParameters(hconn, hobj, msgDesc, putMsgOpts, cc, rc), mbproc);
/*     */ 
/*     */     
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforeMQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", putContext);
/*     */     }
/*     */     
/* 140 */     return putContext;
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
/*     */   public MQPutContext beforeSpiPut(Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, SpiPutOptions spiPutOpts, int bufferLength, ByteBuffer buffer, Pint cc, Pint rc) {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforeSpiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, msgDesc, putMsgOpts, spiPutOpts, 
/*     */             
/* 160 */             Integer.valueOf(bufferLength), buffer, cc, rc });
/*     */     }
/*     */     
/* 163 */     MessageBufferProcessor mbproc = new MessageBufferProcessorImpl(this.env, this.jmqi);
/*     */     
/* 165 */     mbproc.wrap(msgDesc, buffer);
/* 166 */     MQPutContext esePutData = beforePut(new MQPutParameters(hconn, hobj, msgDesc, putMsgOpts, cc, rc), mbproc);
/*     */ 
/*     */     
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforeSpiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", esePutData);
/*     */     }
/*     */     
/* 173 */     return esePutData;
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
/*     */   public MQPutContext beforeSpiPut(Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, SpiPutOptions spiPutOpts, ByteBuffer[] buffers, Pint cc, Pint rc) {
/* 190 */     if (Trace.isOn) {
/* 191 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforeSpiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],Pint,Pint)", new Object[] { hconn, hobj, msgDesc, putMsgOpts, spiPutOpts, buffers, cc, rc });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 196 */     MessageBufferProcessor mbproc = new MessageBufferProcessorImpl(this.env, this.jmqi);
/*     */     
/* 198 */     mbproc.wrap(msgDesc, buffers);
/* 199 */     MQPutContext putContext = beforePut(new MQPutParameters(hconn, hobj, msgDesc, putMsgOpts, cc, rc), mbproc);
/*     */ 
/*     */     
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforeSpiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],Pint,Pint)", putContext);
/*     */     }
/*     */     
/* 206 */     return putContext;
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
/*     */   public void afterJmqiPut(MQPutContext esePutData, Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, ByteBuffer[] buffers, Pint cc, Pint rc) {
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterJmqiPut(MQPutContext,Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", new Object[] { esePutData, hconn, hobj, msgDesc, putMsgOpts, buffers, cc, rc });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 231 */     afterPut(esePutData, new MQPutParameters(hconn, hobj, msgDesc, putMsgOpts, cc, rc));
/*     */ 
/*     */     
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterJmqiPut(MQPutContext,Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)");
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
/*     */   public void afterMQPUT(MQPutContext putContext, Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, int bufferLength, ByteBuffer buffer, Pint cc, Pint rc) {
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterMQPUT(MQPutContext,Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", new Object[] { putContext, hconn, hobj, msgDesc, putMsgOpts, 
/*     */             
/* 258 */             Integer.valueOf(bufferLength), buffer, cc, rc });
/*     */     }
/*     */     
/* 261 */     afterPut(putContext, new MQPutParameters(hconn, hobj, msgDesc, putMsgOpts, cc, rc));
/*     */ 
/*     */     
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterMQPUT(MQPutContext,Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)");
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
/*     */   public void afterSpiPut(MQPutContext esePutBuffer, Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, SpiPutOptions spiPutOpts, int bufferLength, ByteBuffer buffer, Pint cc, Pint rc) {
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterSpiPut(MQPutContext,Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", new Object[] { esePutBuffer, hconn, hobj, msgDesc, putMsgOpts, spiPutOpts, 
/*     */ 
/*     */             
/* 290 */             Integer.valueOf(bufferLength), buffer, cc, rc });
/*     */     }
/*     */     
/* 293 */     afterPut(esePutBuffer, new MQPutParameters(hconn, hobj, msgDesc, putMsgOpts, cc, rc));
/*     */ 
/*     */     
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterSpiPut(MQPutContext,Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)");
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
/*     */   public void afterSpiPut(MQPutContext esePutData, Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, SpiPutOptions spiPutOpts, ByteBuffer[] buffers, Pint cc, Pint rc) {
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterSpiPut(MQPutContext,Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],Pint,Pint)", new Object[] { esePutData, hconn, hobj, msgDesc, putMsgOpts, spiPutOpts, buffers, cc, rc });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 324 */     afterPut(esePutData, new MQPutParameters(hconn, hobj, msgDesc, putMsgOpts, cc, rc));
/*     */ 
/*     */     
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterSpiPut(MQPutContext,Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],Pint,Pint)");
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
/*     */   public void beforeJmqiPutWithTriplets(Hconn hconn, Hobj hobj, MQMD mqmd, MQPMO mqpmo, ByteBuffer[] buffer, Triplet[] triplets, Pint compCode, Pint reason) {
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforeJmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", new Object[] { hconn, hobj, mqmd, mqpmo, buffer, triplets, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 345 */     if (Trace.isOn) {
/* 346 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforeJmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
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
/*     */   public void afterJmqiPutWithTriplets(Hconn hconn, Hobj hobj, MQMD mqmd, MQPMO mqpmo, ByteBuffer[] buffer, Triplet[] triplets, Pint compCode, Pint reason) {
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterJmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", new Object[] { hconn, hobj, mqmd, mqpmo, buffer, triplets, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 363 */     if (Trace.isOn) {
/* 364 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterJmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
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
/*     */   public void beforeJmqiPut1WithTriplets(Hconn hconn, MQOD mqod, MQMD mqmd, MQPMO mqpmo, ByteBuffer[] buffer, Triplet[] triplets, Pint compCode, Pint reason) {
/* 376 */     if (Trace.isOn) {
/* 377 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforeJmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", new Object[] { hconn, mqod, mqmd, mqpmo, buffer, triplets, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforeJmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
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
/*     */   public void afterJmqiPut1WithTriplets(Hconn hconn, MQOD mqod, MQMD mqmd, MQPMO mqpmo, ByteBuffer[] buffer, Triplet[] triplets, Pint compCode, Pint reason) {
/* 394 */     if (Trace.isOn) {
/* 395 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterJmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", new Object[] { hconn, mqod, mqmd, mqpmo, buffer, triplets, compCode, reason });
/*     */     }
/*     */ 
/*     */     
/* 399 */     if (Trace.isOn) {
/* 400 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterJmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
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
/*     */   private MQPutContext beforePut(MQPutParameters parms, MessageBufferProcessor mbproc) {
/* 418 */     if (Trace.isOn) {
/* 419 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforePut(MQPutParameters,MessageBufferProcessor)", new Object[] { parms, mbproc });
/*     */     }
/*     */ 
/*     */     
/* 423 */     String meth = "beforePut";
/* 424 */     if (parms.cc == null || parms.rc == null) {
/* 425 */       if (Trace.isOn) {
/* 426 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforePut(MQPutParamaeters, MessageBufferProcessor)", "Illegal parameters: CompCode or Reason are NULL", "");
/*     */       }
/*     */ 
/*     */       
/* 430 */       if (Trace.isOn) {
/* 431 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforePut(MQPutParameters,MessageBufferProcessor)", null, 1);
/*     */       }
/*     */       
/* 434 */       return null;
/*     */     } 
/* 436 */     if (!validateInput(parms)) {
/* 437 */       HashMap<String, Exception> data = new HashMap<>();
/* 438 */       Trace.ffst(CLASS, "beforePut", "MP006001", data, null);
/* 439 */       if (Trace.isOn) {
/* 440 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforePut(MQPutParameters,MessageBufferProcessor)", null, 2);
/*     */       }
/*     */       
/* 443 */       return null;
/*     */     } 
/* 445 */     MQPutContext ret = null; try {
/*     */       HashMap<String, Object> inserts; HashMap<String, Exception> data;
/* 447 */       SmqiObject smqiObject = getSmqiObject(parms.hconn, parms.hobj, meth);
/* 448 */       int qop = smqiObject.getSecPolicy().getQop();
/*     */       
/* 450 */       switch (qop) {
/*     */         case 0:
/* 452 */           if (Trace.isOn) {
/* 453 */             Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforePut(MQPutParameters, MessageBufferProcessor)", "QOP is NONE", null);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 458 */           ret = new MQPutContext(mbproc.getAllBuffers(), parms.msgDesc.getFormat(), mbproc, smqiObject);
/*     */           break;
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/* 463 */           if (!this.contextContainer.isCryptoCapable()) {
/* 464 */             EseMQException traceRet1 = new EseMQException(new SecurityException(), 2063);
/* 465 */             if (Trace.isOn) {
/* 466 */               Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforePut(MQPutParameters,MessageBufferProcessor)", (Throwable)traceRet1);
/*     */             }
/*     */             
/* 469 */             throw traceRet1;
/*     */           } 
/* 471 */           ret = protect(smqiObject, mbproc, parms);
/*     */           break;
/*     */         default:
/* 474 */           if (Trace.isOn) {
/* 475 */             Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforePut(MQPutParamaeters, MessageBufferProcessor)", "Invalid protection type " + qop + " for queue " + smqiObject
/*     */                 
/* 477 */                 .getResolvedName(), "");
/*     */           }
/*     */           
/* 480 */           inserts = new HashMap<>();
/* 481 */           inserts.put("AMS_INSERT_QUALITY_OF_PROTECTION_NO", Integer.valueOf(qop));
/* 482 */           inserts.put("AMS_INSERT_Q_NAME", smqiObject.getResolvedName());
/* 483 */           AmsErrorMessages.log(CLASS, meth, AmsErrorMessages.mqm_s_put_invalid_qop, inserts);
/* 484 */           data = new HashMap<>();
/* 485 */           Trace.ffst(CLASS, "beforePut", "MP006009", data, null);
/* 486 */           setErrorReasonCode(parms.cc, parms.rc, 2035);
/* 487 */           if (Trace.isOn) {
/* 488 */             Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforePut(MQPutParameters,MessageBufferProcessor)", null, 3);
/*     */           }
/*     */           
/* 491 */           return null;
/*     */       } 
/*     */     
/* 494 */     } catch (JmqiException e) {
/* 495 */       if (Trace.isOn) {
/* 496 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforePut(MQPutParameters,MessageBufferProcessor)", (Throwable)e, 1);
/*     */       }
/*     */       
/* 499 */       HashMap<String, Exception> data = new HashMap<>();
/* 500 */       data.put("exception", e);
/* 501 */       Trace.ffst(CLASS, "beforePut", "MP006002", data, null);
/* 502 */       setErrorReasonCode(parms.cc, parms.rc, e.getReason());
/* 503 */       AmsErrorMessages.logException(CLASS, meth, (Throwable)e);
/* 504 */       if (Trace.isOn) {
/* 505 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforePut(MQPutParamaeters, MessageBufferProcessor)", (Throwable)e);
/*     */       }
/*     */       
/* 508 */       JmqiTls jTls = getTls();
/* 509 */       jTls.lastException = e;
/* 510 */       ret = null;
/*     */     }
/* 512 */     catch (EseMQException e) {
/* 513 */       if (Trace.isOn) {
/* 514 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforePut(MQPutParameters,MessageBufferProcessor)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 517 */       HashMap<String, Exception> data = new HashMap<>();
/* 518 */       data.put("exception", e);
/* 519 */       Trace.ffst(CLASS, "beforePut", "MP006003", data, null);
/* 520 */       AmsErrorMessages.logException(CLASS, meth, (Throwable)e);
/* 521 */       if (Trace.isOn) {
/* 522 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforePut(MQPutParamaeters, MessageBufferProcessor)", (Throwable)e);
/*     */       }
/*     */       
/* 525 */       setErrorReasonCode(parms.cc, parms.rc, e.getReason());
/* 526 */       ret = null;
/*     */     }
/* 528 */     catch (AMBIException e) {
/* 529 */       if (Trace.isOn) {
/* 530 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforePut(MQPutParameters,MessageBufferProcessor)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 533 */       HashMap<String, Exception> data = new HashMap<>();
/* 534 */       data.put("exception", e);
/* 535 */       Trace.ffst(CLASS, "beforePut", "MP006004", data, null);
/* 536 */       AmsErrorMessages.logException(CLASS, meth, (Throwable)e);
/* 537 */       if (Trace.isOn) {
/* 538 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforePut(MQPutParamaeters, MessageBufferProcessor)", (Throwable)e);
/*     */       }
/*     */       
/* 541 */       setErrorReasonCode(parms.cc, parms.rc, 2063);
/* 542 */       ret = null;
/*     */     }
/* 544 */     catch (Exception e) {
/* 545 */       if (Trace.isOn) {
/* 546 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforePut(MQPutParameters,MessageBufferProcessor)", e, 4);
/*     */       }
/*     */       
/* 549 */       HashMap<String, Exception> data = new HashMap<>();
/* 550 */       data.put("exception", e);
/* 551 */       Trace.ffst(CLASS, "beforePut", "MP006005", data, null);
/* 552 */       AmsErrorMessages.logException(CLASS, meth, e);
/* 553 */       if (Trace.isOn) {
/* 554 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforePut(MQPutParamaeters, MessageBufferProcessor)", e);
/*     */       }
/*     */       
/* 557 */       setErrorReasonCode(parms.cc, parms.rc, 2195);
/* 558 */       ret = null;
/*     */     } 
/*     */     
/* 561 */     if (Trace.isOn) {
/* 562 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "beforePut(MQPutParameters,MessageBufferProcessor)", ret, 4);
/*     */     }
/*     */     
/* 565 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean validateInput(MQPutParameters parms) {
/* 575 */     if (Trace.isOn) {
/* 576 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "validateInput(MQPutParameters)", new Object[] { parms });
/*     */     }
/*     */     
/* 579 */     if (parms.hconn == null) {
/* 580 */       setErrorReasonCode(parms.cc, parms.rc, 2018);
/* 581 */       if (Trace.isOn) {
/* 582 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "validateInput(MQPutParameters)", 
/* 583 */             Boolean.valueOf(false), 1);
/*     */       }
/* 585 */       return false;
/*     */     } 
/* 587 */     if (parms.hobj == null) {
/* 588 */       setErrorReasonCode(parms.cc, parms.rc, 2019);
/* 589 */       if (Trace.isOn) {
/* 590 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "validateInput(MQPutParameters)", 
/* 591 */             Boolean.valueOf(false), 2);
/*     */       }
/* 593 */       return false;
/*     */     } 
/* 595 */     if (parms.msgDesc == null) {
/* 596 */       setErrorReasonCode(parms.cc, parms.rc, 2026);
/* 597 */       if (Trace.isOn) {
/* 598 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "validateInput(MQPutParameters)", 
/* 599 */             Boolean.valueOf(false), 3);
/*     */       }
/* 601 */       return false;
/*     */     } 
/* 603 */     if (parms.putMsgOpts == null) {
/* 604 */       setErrorReasonCode(parms.cc, parms.rc, 2173);
/* 605 */       if (Trace.isOn) {
/* 606 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "validateInput(MQPutParameters)", 
/* 607 */             Boolean.valueOf(false), 4);
/*     */       }
/* 609 */       return false;
/*     */     } 
/* 611 */     if (Trace.isOn) {
/* 612 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "validateInput(MQPutParameters)", 
/* 613 */           Boolean.valueOf(true), 5);
/*     */     }
/* 615 */     return true;
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
/*     */   private MQPutContext protect(SmqiObject smqiObject, MessageBufferProcessor mbproc, MQPutParameters parms) throws JmqiException, AMBIException {
/* 632 */     if (Trace.isOn) {
/* 633 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "protect(SmqiObject,MessageBufferProcessor,MQPutParameters)", new Object[] { smqiObject, mbproc, parms });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 638 */     mbproc.process(parms.hconn.getCcsid());
/* 639 */     if (Trace.isOn) {
/* 640 */       Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "protect(SmqiObject, MessageBufferProcessor, MQPutParameters)", "Successfully processed message headers", "");
/*     */     }
/*     */ 
/*     */     
/* 644 */     SecurityPolicy policy = smqiObject.getSecPolicy();
/* 645 */     AMBIHeader header = new AMBIHeader(policy.getQop());
/* 646 */     int payloadLength = mbproc.getPayloadLength();
/* 647 */     header.setOrigSize(payloadLength);
/*     */     try {
/* 649 */       header.setPolicyname(policy.getName());
/* 650 */       header.setOrigFormat(mbproc.getPayloadFormat());
/*     */     }
/* 652 */     catch (UnsupportedEncodingException e) {
/* 653 */       if (Trace.isOn) {
/* 654 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "protect(SmqiObject,MessageBufferProcessor,MQPutParameters)", e);
/*     */       }
/*     */       
/* 657 */       HashMap<String, String> inserts = new HashMap<>();
/* 658 */       inserts.put("AMS_INSERT_CHARACTER_ENCODING", "UTF-8");
/* 659 */       EseMQException ex = new EseMQException(AmsErrorMessages.mju_ambi_header_convert_error_EseMQException, inserts, e);
/* 660 */       ex.setReason(2330);
/* 661 */       if (Trace.isOn) {
/* 662 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "protect(SmqiObject,MessageBufferProcessor,MQPutParameters)", (Throwable)ex);
/*     */       }
/*     */       
/* 665 */       throw ex;
/*     */     } 
/* 667 */     mbproc.setPayloadFormat("        ", 1208);
/* 668 */     byte[] entireBody = mbproc.getPayloadOnly();
/* 669 */     if (entireBody == null || payloadLength == 0) {
/* 670 */       entireBody = new byte[] { 0 };
/* 671 */       byte[] nullMsgFlag = new byte[8];
/* 672 */       nullMsgFlag[0] = 1;
/* 673 */       header.setFlag(nullMsgFlag);
/* 674 */       header.setOrigSize(1);
/*     */     } 
/* 676 */     byte[] protData = null;
/* 677 */     if (header.getOrigSize() == 0) {
/* 678 */       protData = new byte[0];
/*     */     } else {
/*     */       
/* 681 */       EseUser user = smqiObject.getUser();
/* 682 */       protData = this.cryptoService.protect(entireBody, smqiObject, header, user);
/*     */     } 
/* 684 */     byte[] hdrData = header.toByteArray();
/* 685 */     byte[] protHeaderAndData = new byte[hdrData.length + protData.length];
/* 686 */     System.arraycopy(hdrData, 0, protHeaderAndData, 0, hdrData.length);
/* 687 */     System.arraycopy(protData, 0, protHeaderAndData, hdrData.length, protData.length);
/*     */ 
/*     */     
/* 690 */     ByteBuffer[] newBuff = mbproc.setProtectedPayload(protHeaderAndData);
/*     */     
/* 692 */     MQPutContext putContext = new MQPutContext(newBuff, header.getOrigFormat(), mbproc, smqiObject);
/*     */     
/* 694 */     if (Trace.isOn) {
/* 695 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "protect(SmqiObject,MessageBufferProcessor,MQPutParameters)", putContext);
/*     */     }
/*     */     
/* 698 */     return putContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void afterPut(MQPutContext putContext, MQPutParameters parms) {
/* 708 */     if (Trace.isOn) {
/* 709 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterPut(MQPutContext,MQPutParameters)", new Object[] { putContext, parms });
/*     */     }
/*     */ 
/*     */     
/* 713 */     String meth = "afterPut";
/* 714 */     if (putContext == null || putContext.getSmqiObject() == null) {
/* 715 */       HashMap<String, Exception> data = new HashMap<>();
/* 716 */       Trace.ffst(CLASS, meth, "MP006008", data, null);
/* 717 */       HashMap<String, Object> inserts = new HashMap<>();
/* 718 */       inserts.put("AMS_INSERT_MQ_ERROR_CODE", Integer.valueOf(2019).toString());
/* 719 */       AmsErrorMessages.log(CLASS, meth, AmsErrorMessages.mqm_s_put_env_not_found, inserts);
/* 720 */       if (Trace.isOn) {
/* 721 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterPut(MQPutContext, MQPutParameters)", "Could not resolve queue information from ESE context", "");
/*     */       }
/*     */ 
/*     */       
/* 725 */       setErrorReasonCode(parms.cc, parms.rc, 2019);
/* 726 */       if (Trace.isOn) {
/* 727 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterPut(MQPutContext,MQPutParameters)", 1);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 732 */     SecurityPolicy policy = putContext.getSmqiObject().getSecPolicy();
/* 733 */     if (policy == null) {
/* 734 */       HashMap<String, Exception> data = new HashMap<>();
/* 735 */       Trace.ffst(CLASS, meth, "MP006007", data, null);
/* 736 */       setErrorReasonCode(parms.cc, parms.rc, 2035);
/* 737 */       if (Trace.isOn) {
/* 738 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterPut(MQPutContext, MQPutParameters)", "Could not find security policy information in Hobj", "");
/*     */       }
/*     */ 
/*     */       
/* 742 */       if (Trace.isOn) {
/* 743 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterPut(MQPutContext,MQPutParameters)", 2);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 748 */     if (Trace.isOn) {
/* 749 */       Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterPut(MQPutContext, MQPutParameters)", "Security policy information found in Hobj ", policy);
/*     */     }
/*     */ 
/*     */     
/* 753 */     int qop = policy.getQop();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 772 */       if (qop != 0) {
/* 773 */         String origFormat = putContext.getOrigFormat();
/*     */ 
/*     */         
/* 776 */         putContext.getMessageBufferProcessor().setPayloadFormat(origFormat, parms.hconn
/* 777 */             .getCcsid());
/*     */       }
/*     */     
/* 780 */     } catch (JmqiException e) {
/* 781 */       if (Trace.isOn) {
/* 782 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterPut(MQPutContext,MQPutParameters)", (Throwable)e);
/*     */       }
/*     */       
/* 785 */       HashMap<String, Exception> data = new HashMap<>();
/* 786 */       data.put("exception", e);
/* 787 */       Trace.ffst(CLASS, meth, "MP006006", data, null);
/* 788 */       AmsErrorMessages.logException(CLASS, meth, (Throwable)e);
/* 789 */       if (Trace.isOn) {
/* 790 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterPut(MQPutContext, MQPutParameters)", (Throwable)e);
/*     */       }
/*     */       
/* 793 */       setErrorReasonCode(parms.cc, parms.rc, e.getReason());
/* 794 */       JmqiTls tls = getTls();
/* 795 */       tls.lastException = e;
/* 796 */       if (Trace.isOn) {
/* 797 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterPut(MQPutContext,MQPutParameters)", 3);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 803 */     if (Trace.isOn) {
/* 804 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiPutInterceptorImpl", "afterPut(MQPutContext,MQPutParameters)", 4);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class MQPutParameters
/*     */   {
/*     */     public Hconn hconn;
/*     */ 
/*     */     
/*     */     public Hobj hobj;
/*     */ 
/*     */     
/*     */     public MQMD msgDesc;
/*     */ 
/*     */     
/*     */     public MQPMO putMsgOpts;
/*     */ 
/*     */     
/*     */     public Pint cc;
/*     */ 
/*     */     
/*     */     public Pint rc;
/*     */ 
/*     */     
/*     */     public MQPutParameters(Hconn hconn, Hobj hobj, MQMD mqmd, MQPMO pmo, Pint cc, Pint rc) {
/* 831 */       if (Trace.isOn) {
/* 832 */         Trace.entry(this, "com.ibm.mq.ese.intercept.MQPutParameters", "<init>(Hconn,Hobj,MQMD,MQPMO,Pint,Pint)", new Object[] { hconn, hobj, mqmd, pmo, cc, rc });
/*     */       }
/*     */       
/* 835 */       this.hconn = hconn;
/* 836 */       this.hobj = hobj;
/* 837 */       this.msgDesc = mqmd;
/* 838 */       this.putMsgOpts = pmo;
/* 839 */       this.cc = cc;
/* 840 */       this.rc = rc;
/* 841 */       if (Trace.isOn)
/* 842 */         Trace.exit(this, "com.ibm.mq.ese.intercept.MQPutParameters", "<init>(Hconn,Hobj,MQMD,MQPMO,Pint,Pint)"); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\JmqiPutInterceptorImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */