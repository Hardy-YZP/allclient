/*      */ package com.ibm.mq.ese.intercept;
/*      */ 
/*      */ import com.ibm.mq.constants.CMQC;
/*      */ import com.ibm.mq.ese.conv.CcsidException;
/*      */ import com.ibm.mq.ese.core.AMBIException;
/*      */ import com.ibm.mq.ese.core.AMBIHeader;
/*      */ import com.ibm.mq.ese.core.EseUser;
/*      */ import com.ibm.mq.ese.core.IncorrectHeaderException;
/*      */ import com.ibm.mq.ese.core.SecurityPolicy;
/*      */ import com.ibm.mq.ese.core.X500NameWrapper;
/*      */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*      */ import com.ibm.mq.ese.prot.MessageUnprotectInfo;
/*      */ import com.ibm.mq.ese.service.EseMQException;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiUtils;
/*      */ import com.ibm.mq.jmqi.MQGMO;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Hobj;
/*      */ import com.ibm.mq.jmqi.handles.PbyteBuffer;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.mq.jmqi.system.SpiGetOptions;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.DataInputStream;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JmqiGetInterceptorImpl
/*      */   extends AbstractJmqiInterceptor
/*      */   implements JmqiGetInterceptor
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiGetInterceptorImpl.java";
/*      */   
/*      */   static {
/*   68 */     if (Trace.isOn) {
/*   69 */       Trace.data("com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiGetInterceptorImpl.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   80 */   private static final String CLASS = JmqiGetInterceptorImpl.class.getName();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQGetContext beforeJmqiGet(Hconn hconn, Hobj hobj, MQMD md, MQGMO gmo, int expectedMsgLength, int maxMsgLength, PbyteBuffer byteBuffer, Pint msgTooSmallForBufferCount, Pint dataLength, Pint cc, Pint rc) {
/*   95 */     if (Trace.isOn) {
/*   96 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "beforeJmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", new Object[] { hconn, hobj, md, gmo, 
/*      */             
/*   98 */             Integer.valueOf(expectedMsgLength), Integer.valueOf(maxMsgLength), byteBuffer, msgTooSmallForBufferCount, dataLength, cc, rc });
/*      */     }
/*      */ 
/*      */     
/*  102 */     MQGetContext mqgetContext = beforeGet(new MQGetParameters(hconn, hobj, md, gmo, maxMsgLength, byteBuffer
/*  103 */           .getBuffer(), dataLength, cc, rc));
/*      */ 
/*      */     
/*  106 */     if (Trace.isOn) {
/*  107 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "beforeJmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", mqgetContext);
/*      */     }
/*      */     
/*  110 */     return mqgetContext;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQGetContext beforeMQGET(Hconn hconn, Hobj hobj, MQMD md, MQGMO gmo, int bufferLength, ByteBuffer buffer, Pint dataLength, Pint cc, Pint rc) {
/*  125 */     if (Trace.isOn) {
/*  126 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "beforeMQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, md, gmo, 
/*  127 */             Integer.valueOf(bufferLength), buffer, dataLength, cc, rc });
/*      */     }
/*      */     
/*  130 */     MQGetContext mqgetContext = beforeGet(new MQGetParameters(hconn, hobj, md, gmo, bufferLength, buffer, dataLength, cc, rc));
/*      */ 
/*      */ 
/*      */     
/*  134 */     buffer.clear();
/*      */     
/*  136 */     if (Trace.isOn) {
/*  137 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "beforeMQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", mqgetContext);
/*      */     }
/*      */     
/*  140 */     return mqgetContext;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQGetContext beforeSpiGet(Hconn hconn, Hobj hobj, MQMD md, MQGMO gmo, SpiGetOptions spiOptions, int bufferLength, ByteBuffer buffer, Pint dataLength, Pint cc, Pint rc) {
/*  155 */     if (Trace.isOn) {
/*  156 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "beforeSpiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, md, gmo, spiOptions, 
/*      */             
/*  158 */             Integer.valueOf(bufferLength), buffer, dataLength, cc, rc });
/*      */     }
/*      */ 
/*      */     
/*  162 */     MQGetContext mqgetContext = beforeGet(new MQGetParameters(hconn, hobj, md, gmo, bufferLength, buffer, dataLength, cc, rc));
/*      */ 
/*      */     
/*  165 */     if (Trace.isOn) {
/*  166 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "beforeSpiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", mqgetContext);
/*      */     }
/*      */     
/*  169 */     return mqgetContext;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQGetContext beforeJmqiGetMessage(Hconn hconn, Hobj hobj, MQMD md, MQGMO gmo, int bufferLength, ByteBuffer buffer, Pint dataLength, Pint cc, Pint rc) {
/*  184 */     if (Trace.isOn) {
/*  185 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "beforeJmqiGetMessage(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, md, gmo, 
/*      */             
/*  187 */             Integer.valueOf(bufferLength), buffer, dataLength, cc, rc });
/*      */     }
/*      */     
/*  190 */     MQGetContext mqgetContext = beforeGet(new MQGetParameters(hconn, hobj, md, gmo, bufferLength, buffer, dataLength, cc, rc));
/*      */ 
/*      */     
/*  193 */     if (Trace.isOn) {
/*  194 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "beforeJmqiGetMessage(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", mqgetContext);
/*      */     }
/*      */     
/*  197 */     return mqgetContext;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void afterJmqiGet(MQGetContext eseGetContext, Hconn hconn, Hobj hobj, MQMD md, MQGMO gmo, int expectedMsgLength, int maxMsgLength, PbyteBuffer byteBuffer, Pint msgTooSmallForBufferCount, Pint dataLength, Pint cc, Pint rc) {
/*  214 */     if (Trace.isOn) {
/*  215 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "afterJmqiGet(MQGetContext,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", new Object[] { eseGetContext, hconn, hobj, md, gmo, 
/*      */             
/*  217 */             Integer.valueOf(expectedMsgLength), 
/*  218 */             Integer.valueOf(maxMsgLength), byteBuffer, msgTooSmallForBufferCount, dataLength, cc, rc });
/*      */     }
/*      */     
/*  221 */     afterGet(eseGetContext, new MQGetParameters(hconn, hobj, md, gmo, maxMsgLength, byteBuffer
/*  222 */           .getBuffer(), dataLength, cc, rc));
/*      */     
/*  224 */     if (Trace.isOn) {
/*  225 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "afterJmqiGet(MQGetContext,Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void afterJmqiGetMessage(MQGetContext eseGetData, Hconn hconn, Hobj hobj, MQMD mqmd, MQGMO mqgmo, int bufferLength, ByteBuffer buffer, Pint dataLength, Pint cc, Pint rc) {
/*  244 */     if (Trace.isOn) {
/*  245 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "afterJmqiGetMessage(MQGetContext,Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { eseGetData, hconn, hobj, mqmd, mqgmo, 
/*      */             
/*  247 */             Integer.valueOf(bufferLength), buffer, dataLength, cc, rc });
/*      */     }
/*      */ 
/*      */     
/*  251 */     afterGet(eseGetData, new MQGetParameters(hconn, hobj, mqmd, mqgmo, bufferLength, buffer, dataLength, cc, rc));
/*      */ 
/*      */     
/*  254 */     if (Trace.isOn) {
/*  255 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "afterJmqiGetMessage(MQGetContext,Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void afterMQGET(MQGetContext eseGetData, Hconn hconn, Hobj hobj, MQMD msgDesc, MQGMO getMsgOpts, int bufferLength, ByteBuffer buffer, Pint dataLength, Pint cc, Pint rc) {
/*  274 */     if (Trace.isOn) {
/*  275 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "afterMQGET(MQGetContext,Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { eseGetData, hconn, hobj, msgDesc, getMsgOpts, 
/*      */             
/*  277 */             Integer.valueOf(bufferLength), buffer, dataLength, cc, rc });
/*      */     }
/*      */ 
/*      */     
/*  281 */     afterGet(eseGetData, new MQGetParameters(hconn, hobj, msgDesc, getMsgOpts, bufferLength, buffer, dataLength, cc, rc));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  286 */     if (buffer != null) {
/*  287 */       buffer.rewind();
/*      */     }
/*      */     
/*  290 */     if (Trace.isOn) {
/*  291 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "afterMQGET(MQGetContext,Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void afterSpiGet(MQGetContext eseGetData, Hconn hconn, Hobj hobj, MQMD msgDesc, MQGMO getMsgOpts, SpiGetOptions spiOptions, int bufferLength, ByteBuffer buffer, Pint dataLength, Pint cc, Pint rc) {
/*  311 */     if (Trace.isOn) {
/*  312 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "afterSpiGet(MQGetContext,Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { eseGetData, hconn, hobj, msgDesc, getMsgOpts, spiOptions, 
/*      */             
/*  314 */             Integer.valueOf(bufferLength), buffer, dataLength, cc, rc });
/*      */     }
/*      */     
/*  317 */     afterGet(eseGetData, new MQGetParameters(hconn, hobj, msgDesc, getMsgOpts, bufferLength, buffer, dataLength, cc, rc));
/*      */ 
/*      */     
/*  320 */     if (Trace.isOn) {
/*  321 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "afterSpiGet(MQGetContext,Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQGetContext beforeGet(MQGetParameters parms) {
/*  335 */     if (Trace.isOn) {
/*  336 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "beforeGet(MQGetParameters)", new Object[] { parms });
/*      */     }
/*      */ 
/*      */     
/*  340 */     String meth = "beforeGet";
/*  341 */     if (!validateInput(parms)) {
/*  342 */       MQGetContext traceRet1 = new MQGetContext(parms.msgDesc, parms.getMsgOpts);
/*  343 */       if (Trace.isOn) {
/*  344 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "beforeGet(MQGetParameters)", traceRet1, 1);
/*      */       }
/*      */       
/*  347 */       return traceRet1;
/*      */     } 
/*  349 */     SmqiObject smqiObject = null;
/*      */     try {
/*  351 */       smqiObject = getSmqiObject(parms.hconn, parms.hobj, meth);
/*      */     }
/*  353 */     catch (EseMQException e) {
/*  354 */       if (Trace.isOn) {
/*  355 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "beforeGet(MQGetParameters)", (Throwable)e);
/*      */       }
/*      */       
/*  358 */       HashMap<String, Exception> data = new HashMap<>();
/*  359 */       data.put("exception", e);
/*  360 */       Trace.ffst(CLASS, meth, "MP004001", data, null);
/*  361 */       AmsErrorMessages.logException(CLASS, meth, (Throwable)e);
/*  362 */       if (Trace.isOn) {
/*  363 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "beforeGet(MQGetParameters)", (Throwable)e);
/*      */       }
/*      */       
/*  366 */       setErrorReasonCode(parms.cc, parms.rc, e.getReason());
/*  367 */       MQGetContext traceRet2 = new MQGetContext(parms.msgDesc, parms.getMsgOpts);
/*  368 */       if (Trace.isOn) {
/*  369 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "beforeGet(MQGetParameters)", traceRet2, 2);
/*      */       }
/*      */       
/*  372 */       return traceRet2;
/*      */     } 
/*  374 */     int qop = smqiObject.getSecPolicy().getQop();
/*  375 */     MQGetContext ret = null;
/*  376 */     parms.cc.x = 0;
/*  377 */     parms.rc.x = 0;
/*  378 */     if (qop == 0) {
/*  379 */       ret = new MQGetContext(parms.msgDesc, parms.getMsgOpts, smqiObject);
/*      */     } else {
/*      */       
/*  382 */       if (isOptionSet(parms.getMsgOpts.getOptions(), 1) && parms.getMsgOpts.getWaitInterval() != -1) {
/*  383 */         if (Trace.isOn) {
/*  384 */           Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "beforeGet(MQGetParameters parms)", "Limited wait for a message is requested, ms: ", 
/*      */ 
/*      */               
/*  387 */               String.valueOf(parms.getMsgOpts.getWaitInterval()));
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  398 */         smqiObject.setNoMsgTime(System.currentTimeMillis() + parms.getMsgOpts.getWaitInterval());
/*      */       } 
/*      */       
/*  401 */       if (!this.contextContainer.isCryptoCapable()) {
/*  402 */         parms.cc.x = 2;
/*  403 */         parms.rc.x = 2063;
/*  404 */         if (Trace.isOn) {
/*  405 */           Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "beforeGet(MQGetParameters)", ret, 3);
/*      */         }
/*      */         
/*  408 */         return ret;
/*      */       } 
/*      */       
/*  411 */       ret = new MQGetContext(this.env.copyMQMD(parms.msgDesc), this.mqService.copyGetMsgOpts(this.env.newMQGMO(), parms.getMsgOpts), smqiObject);
/*  412 */       if (isOptionSet(parms.getMsgOpts.getOptions(), 64)) {
/*      */         
/*  414 */         int opts = parms.getMsgOpts.getOptions();
/*  415 */         opts &= 0xFFFFFFBF;
/*  416 */         parms.getMsgOpts.setOptions(opts);
/*      */       } 
/*      */     } 
/*      */     
/*  420 */     if (Trace.isOn) {
/*  421 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "beforeGet(MQGetParameters)", ret, 4);
/*      */     }
/*      */     
/*  424 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean validateInput(MQGetParameters parms) {
/*  434 */     if (Trace.isOn) {
/*  435 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateInput(MQGetParameters)", new Object[] { parms });
/*      */     }
/*      */ 
/*      */     
/*  439 */     boolean ret = true;
/*  440 */     if (parms.cc == null || parms.rc == null) {
/*  441 */       if (Trace.isOn) {
/*  442 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateInput(MQGetParameters)", "Illegal parameters: CompCode or Reason are NULL", new Object[] { parms.cc, parms.rc });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  447 */       ret = false;
/*      */     }
/*  449 */     else if (parms.hconn == null) {
/*  450 */       setErrorReasonCode(parms.cc, parms.rc, 2018);
/*  451 */       ret = false;
/*      */     }
/*  453 */     else if (parms.hobj == null) {
/*  454 */       setErrorReasonCode(parms.cc, parms.rc, 2019);
/*  455 */       ret = false;
/*      */     }
/*  457 */     else if (parms.msgDesc == null) {
/*  458 */       setErrorReasonCode(parms.cc, parms.rc, 2026);
/*  459 */       ret = false;
/*      */     }
/*  461 */     else if (parms.getMsgOpts == null) {
/*  462 */       setErrorReasonCode(parms.cc, parms.rc, 2186);
/*  463 */       ret = false;
/*      */     }
/*  465 */     else if (parms.dataLength == null) {
/*  466 */       setErrorReasonCode(parms.cc, parms.rc, 2010);
/*  467 */       ret = false;
/*      */     } 
/*      */     
/*  470 */     if (Trace.isOn) {
/*  471 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateInput(MQGetParameters)", 
/*  472 */           Boolean.valueOf(ret));
/*      */     }
/*  474 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void afterGet(MQGetContext mqgetContext, MQGetParameters parms) {
/*  484 */     if (Trace.isOn) {
/*  485 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "afterGet(MQGetContext,MQGetParameters)", new Object[] { mqgetContext, parms });
/*      */     }
/*      */ 
/*      */     
/*  489 */     if (!validateInput(parms)) {
/*  490 */       HashMap<String, Exception> data = new HashMap<>();
/*  491 */       Trace.ffst(CLASS, "afterGet", "MP004007", data, null);
/*  492 */       if (Trace.isOn) {
/*  493 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "afterGet(MQGetContext,MQGetParameters)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  498 */     if (!validateInputAfter(mqgetContext, parms)) {
/*  499 */       HashMap<String, Exception> data = new HashMap<>();
/*  500 */       Trace.ffst(CLASS, "afterGet", "MP004008", data, null);
/*  501 */       if (Trace.isOn) {
/*  502 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "afterGet(MQGetContext,MQGetParameters)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  507 */     SecurityPolicy policy = mqgetContext.getSecPolicy();
/*  508 */     if (policy.getQop() != 0) {
/*  509 */       restoreGmoOpts(mqgetContext, parms);
/*  510 */       unprotectMessage(mqgetContext, parms);
/*      */     } 
/*      */     
/*  513 */     if (Trace.isOn) {
/*  514 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "afterGet(MQGetContext,MQGetParameters)", 3);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean validateInputAfter(MQGetContext mqgetContext, MQGetParameters parms) {
/*  530 */     if (Trace.isOn) {
/*  531 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateInputAfter(MQGetContext,MQGetParameters)", new Object[] { mqgetContext, parms });
/*      */     }
/*      */ 
/*      */     
/*  535 */     String meth = "validateInputAfter";
/*  536 */     boolean ret = true;
/*  537 */     SmqiObject smqiObject = mqgetContext.getSmqiObject();
/*  538 */     if (smqiObject == null) {
/*  539 */       HashMap<String, Object> inserts = new HashMap<>();
/*  540 */       inserts.put("AMS_INSERT_INTERNAL_ERROR_CODE", Integer.valueOf(2019).toString());
/*  541 */       AmsErrorMessages.log(CLASS, meth, AmsErrorMessages.mqm_s_get_cant_open_qinfo, inserts);
/*  542 */       if (Trace.isOn) {
/*  543 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateInputAfter(MQGetContext, MQGetParameters)", "Could not resolve queue information from context", "");
/*      */       }
/*      */ 
/*      */       
/*  547 */       setErrorReasonCode(parms.cc, parms.rc, 2019);
/*  548 */       ret = false;
/*      */     } else {
/*      */       
/*  551 */       SecurityPolicy policy = smqiObject.getSecPolicy();
/*  552 */       if (policy == null) {
/*  553 */         setErrorReasonCode(parms.cc, parms.rc, 2035);
/*  554 */         if (Trace.isOn) {
/*  555 */           Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateInputAfter(MQGetContext, MQGetParameters)", "Could not find security policy information in Hobj", "");
/*      */         }
/*      */ 
/*      */         
/*  559 */         ret = false;
/*      */       
/*      */       }
/*  562 */       else if (Trace.isOn) {
/*  563 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateInputAfter(MQGetContext, MQGetParameters)", "Security policy information found in Hobj", policy);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  570 */     if (Trace.isOn) {
/*  571 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateInputAfter(MQGetContext,MQGetParameters)", 
/*  572 */           Boolean.valueOf(ret));
/*      */     }
/*  574 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void unprotectMessage(MQGetContext context, MQGetParameters parms) {
/*  584 */     if (Trace.isOn) {
/*  585 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "unprotectMessage(MQGetContext,MQGetParameters)", new Object[] { context, parms });
/*      */     }
/*      */ 
/*      */     
/*  589 */     String meth = "unprotectMessage";
/*  590 */     MsgStatus msgStatus = new MsgStatus();
/*      */ 
/*      */     
/*  593 */     if (parms.cc.x == 1 && parms.rc.x == 2110 && 
/*  594 */       isOptionSet(parms.getMsgOpts.getOptions(), 16384)) {
/*  595 */       parms.cc.x = 0;
/*  596 */       parms.rc.x = 0;
/*  597 */       context.setFormatErrorNeglected(true);
/*  598 */       if (Trace.isOn) {
/*  599 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "unprotectMessage(MQGetContext, MQGetParameters)", "Conversion requested, setting MQRC_NONE", "");
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  608 */     if ((parms.rc.x != 2080 && parms.rc.x != 0) || parms.cc.x >= 2) {
/*  609 */       if (Trace.isOn) {
/*  610 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "unprotectMessage(MQGetContext,MQGetParameters)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  617 */     if (parms.buffer == null) {
/*  618 */       parms.cc.x = 2;
/*  619 */       parms.rc.x = 2004;
/*  620 */       if (Trace.isOn) {
/*  621 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "unprotectMessage(MQGetContext, MQGetParameters)", "buffer must not be null", "");
/*      */       }
/*      */ 
/*      */       
/*  625 */       if (Trace.isOn) {
/*  626 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "unprotectMessage(MQGetContext,MQGetParameters)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*      */     try {
/*  633 */       if (parms.rc.x == 2080) {
/*      */         
/*  635 */         if (isOptionSet(context.getSmqiObject().getOpenOpts(), 8) && (
/*  636 */           isOptionSet(parms.getMsgOpts.getOptions(), 16) || 
/*  637 */           isOptionSet(parms.getMsgOpts.getOptions(), 32) || 
/*  638 */           isOptionSet(parms.getMsgOpts.getOptions(), 2048) || 
/*  639 */           isOptionSet(parms.getMsgOpts
/*  640 */             .getOptions(), 256))) {
/*      */           
/*  642 */           getBrowse(parms, context, msgStatus);
/*      */         } else {
/*      */           
/*  645 */           getNoBrowse(parms, context, msgStatus);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  650 */         msgStatus.isComplete = true;
/*  651 */         if (!isBrowse(parms.getMsgOpts)) {
/*  652 */           msgStatus.isRemoved = true;
/*      */         }
/*  654 */         process(context, parms, msgStatus);
/*      */       }
/*      */     
/*  657 */     } catch (JmqiException e) {
/*  658 */       if (Trace.isOn) {
/*  659 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "unprotectMessage(MQGetContext,MQGetParameters)", (Throwable)e, 1);
/*      */       }
/*      */       
/*  662 */       if (Trace.isOn) {
/*  663 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "unprotectMessage(MQGetContext, MQGetParameters)", (Throwable)e);
/*      */       }
/*      */       
/*  666 */       onException(context, parms, meth, msgStatus, (Exception)e, e.getReason());
/*  667 */       JmqiTls tls = getTls();
/*  668 */       tls.lastException = e;
/*      */     }
/*  670 */     catch (UnprotectException e) {
/*  671 */       if (Trace.isOn) {
/*  672 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "unprotectMessage(MQGetContext,MQGetParameters)", e, 2);
/*      */       }
/*      */       
/*  675 */       if (Trace.isOn) {
/*  676 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "unprotectMessage(MQGetContext, MQGetParameters)", e);
/*      */       }
/*      */       
/*  679 */       onExceptionNoFFDC(context, parms, meth, msgStatus, (Exception)e.getAmbiException(), e
/*  680 */           .getReasonCode());
/*      */     }
/*  682 */     catch (Exception e) {
/*  683 */       if (Trace.isOn) {
/*  684 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "unprotectMessage(MQGetContext,MQGetParameters)", e, 3);
/*      */       }
/*      */       
/*  687 */       if (Trace.isOn) {
/*  688 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "unprotectMessage(MQGetContext, MQGetParameters)", e);
/*      */       }
/*      */       
/*  691 */       onException(context, parms, meth, msgStatus, e, 2195);
/*      */     } 
/*      */ 
/*      */     
/*  695 */     if (Trace.isOn) {
/*  696 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "unprotectMessage(MQGetContext,MQGetParameters)", 3);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getBrowse(MQGetParameters parms, MQGetContext getContext, MsgStatus msgStatus) throws AMBIException, JmqiException, UnprotectException {
/*  715 */     if (Trace.isOn) {
/*  716 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getBrowse(MQGetParameters,MQGetContext,MsgStatus)", new Object[] { parms, getContext, msgStatus });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  721 */     MQMD mqmdCopy = this.env.copyMQMD(getContext.getOrigMQMD());
/*  722 */     MQGMO gmoCopy = this.mqService.copyGetMsgOpts(this.env.newMQGMO(), getContext.getOrigGmo());
/*      */ 
/*      */     
/*  725 */     if (isOptionSet(parms.getMsgOpts.getOptions(), 16) || isOptionSet(parms.getMsgOpts.getOptions(), 32) || 
/*  726 */       isOptionSet(parms.getMsgOpts.getOptions(), 2048)) {
/*      */ 
/*      */       
/*  729 */       int opts = gmoCopy.getOptions();
/*  730 */       opts &= 0xFFFFFFBF;
/*  731 */       opts &= 0xFFFFFFFE;
/*  732 */       opts |= 0x0;
/*  733 */       gmoCopy.setOptions(opts);
/*  734 */       ByteBuffer newBuffer = getInLoop(parms, getContext, parms.hobj, mqmdCopy, gmoCopy, 0);
/*      */       
/*  736 */       if (parms.cc.x == 0) {
/*  737 */         int origGmoVersion = parms.getMsgOpts.getVersion();
/*  738 */         int origGmoOpts = parms.getMsgOpts.getOptions();
/*  739 */         copyMQMD(parms.msgDesc, mqmdCopy);
/*  740 */         this.mqService.copyGetMsgOpts(parms.getMsgOpts, gmoCopy);
/*  741 */         parms.getMsgOpts.setVersion(origGmoVersion);
/*  742 */         parms.getMsgOpts.setOptions(origGmoOpts);
/*      */         
/*  744 */         msgStatus.isComplete = true;
/*  745 */         parms.newBuffer = newBuffer;
/*  746 */         process(getContext, parms, msgStatus);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  753 */       if (isOptionSet(getContext.getOrigGmo().getOptions(), 64)) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  758 */         int opts = gmoCopy.getOptions();
/*  759 */         opts &= 0xFFFFFFBF;
/*  760 */         opts &= 0xFFFFFFFE;
/*  761 */         opts |= 0x0;
/*  762 */         gmoCopy.setOptions(opts);
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  768 */         int opts = gmoCopy.getOptions();
/*  769 */         opts &= 0xFFFFFFBF;
/*  770 */         opts &= 0xFFFFFEFF;
/*  771 */         opts |= 0x800;
/*  772 */         opts &= 0xFFFFFFFE;
/*  773 */         opts |= 0x0;
/*  774 */         gmoCopy.setOptions(opts);
/*      */       } 
/*  776 */       ByteBuffer newBuffer = getInLoop(parms, getContext, parms.hobj, mqmdCopy, gmoCopy, 0);
/*      */       
/*  778 */       if (parms.cc.x == 0) {
/*  779 */         int origGmoVersion = parms.getMsgOpts.getVersion();
/*  780 */         int origGmoOpts = parms.getMsgOpts.getOptions();
/*  781 */         copyMQMD(parms.msgDesc, mqmdCopy);
/*  782 */         this.mqService.copyGetMsgOpts(parms.getMsgOpts, gmoCopy);
/*  783 */         parms.getMsgOpts.setVersion(origGmoVersion);
/*  784 */         parms.getMsgOpts.setOptions(origGmoOpts);
/*      */         
/*  786 */         msgStatus.isComplete = true;
/*  787 */         parms.newBuffer = newBuffer;
/*  788 */         process(getContext, parms, msgStatus);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  796 */       if (parms.rc.x == 0 && !isOptionSet(getContext.getOrigGmo().getOptions(), 64))
/*      */       {
/*  798 */         if (removeMsgUnderCursor(parms, getContext)) {
/*  799 */           msgStatus.isRemoved = true;
/*      */         }
/*      */       }
/*      */     } 
/*  803 */     if (Trace.isOn) {
/*  804 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getBrowse(MQGetParameters,MQGetContext,MsgStatus)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean removeMsgUnderCursor(MQGetParameters parms, MQGetContext getContext) {
/*  822 */     if (Trace.isOn) {
/*  823 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "removeMsgUnderCursor(MQGetParameters,MQGetContext)", new Object[] { parms, getContext });
/*      */     }
/*      */ 
/*      */     
/*  827 */     boolean ret = false;
/*  828 */     MQMD mqmdTmp = this.env.copyMQMD(parms.msgDesc);
/*  829 */     MQGMO gmoCopy = this.mqService.copyGetMsgOpts(this.env.newMQGMO(), getContext.getOrigGmo());
/*  830 */     int opts = gmoCopy.getOptions();
/*  831 */     opts |= 0x40;
/*  832 */     opts |= 0x100;
/*  833 */     opts &= 0xFFFFF7FF;
/*  834 */     opts &= 0xFFFFBFFF;
/*  835 */     opts &= 0xFFFFFFFE;
/*  836 */     gmoCopy.setOptions(opts);
/*  837 */     Pint dataLengthTmp = this.env.newPint();
/*  838 */     Pint ccTmp = this.env.newPint();
/*  839 */     Pint rcTmp = this.env.newPint();
/*  840 */     ByteBuffer buffTmp = ByteBuffer.allocate(0);
/*  841 */     this.jmqi.MQGET(parms.hconn, parms.hobj, mqmdTmp, gmoCopy, 0, buffTmp, dataLengthTmp, ccTmp, rcTmp);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  847 */     if (rcTmp.x == 2079 || rcTmp.x == 0) {
/*      */ 
/*      */       
/*  850 */       ret = true;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  855 */       cleanUpAfterFailedRemoval(parms, dataLengthTmp.x, ccTmp.x, rcTmp.x);
/*      */     } 
/*      */     
/*  858 */     if (Trace.isOn) {
/*  859 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "removeMsgUnderCursor(MQGetParameters,MQGetContext)", 
/*  860 */           Boolean.valueOf(ret));
/*      */     }
/*  862 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void cleanUpAfterFailedRemoval(MQGetParameters parms, int dataLength, int cc, int rc) {
/*  875 */     if (Trace.isOn) {
/*  876 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "cleanUpAfterFailedRemoval(MQGetParameters,int,int,int)", new Object[] { parms, 
/*      */             
/*  878 */             Integer.valueOf(dataLength), Integer.valueOf(cc), Integer.valueOf(rc) });
/*      */     }
/*      */     
/*  881 */     parms.cc.x = cc;
/*  882 */     parms.rc.x = rc;
/*  883 */     parms.dataLength.x = dataLength;
/*  884 */     Arrays.fill(parms.buffer.array(), (byte)0);
/*  885 */     parms.buffer.clear();
/*  886 */     parms.buffer.limit(Math.min(dataLength, parms.buffer.capacity()));
/*  887 */     if (parms.getMsgOpts.getVersion() >= 3) {
/*  888 */       parms.getMsgOpts.setReturnedLength(dataLength);
/*      */     }
/*      */     
/*  891 */     if (Trace.isOn) {
/*  892 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "cleanUpAfterFailedRemoval(MQGetParameters,int,int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ByteBuffer getInLoop(MQGetParameters parms, MQGetContext getContext, Hobj hobjToUse, MQMD mqmdCopy, MQGMO gmoCopy, int spiOptions) {
/*  910 */     if (Trace.isOn) {
/*  911 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getInLoop(MQGetParameters,MQGetContext,Hobj,MQMD,MQGMO,int)", new Object[] { parms, getContext, hobjToUse, mqmdCopy, gmoCopy, 
/*      */             
/*  913 */             Integer.valueOf(spiOptions) });
/*      */     }
/*      */     
/*  916 */     String meth = "getInLoop";
/*  917 */     ByteBuffer newBuffer = null;
/*  918 */     int bufSize = 0;
/*      */ 
/*      */     
/*      */     do {
/*  922 */       if (isOptionSet(parms.getMsgOpts.getOptions(), 1) && parms.getMsgOpts.getWaitInterval() != -1)
/*      */       {
/*      */ 
/*      */ 
/*      */         
/*  927 */         if (getContext.isAsyncConsume() && parms.getMsgOpts.getWaitInterval() == 0) {
/*  928 */           int opts = gmoCopy.getOptions();
/*  929 */           opts &= 0xFFFFFFFE;
/*  930 */           opts |= 0x0;
/*  931 */           gmoCopy.setOptions(opts);
/*      */         } else {
/*      */           
/*  934 */           long diff = getContext.getSmqiObject().getNoMsgTime() - System.currentTimeMillis();
/*      */           
/*  936 */           if (diff <= 0L) {
/*      */             
/*  938 */             if (Trace.isOn) {
/*  939 */               Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getInLoop(MQGetParameters, MQGetContext, Hobj, MQMD, MQGMO)", "waited long enough to return MQRC_NO_MSG_AVAILABLE", "");
/*      */             }
/*      */ 
/*      */             
/*  943 */             parms.cc.x = 2;
/*  944 */             parms.rc.x = 2033;
/*      */             
/*      */             break;
/*      */           } 
/*      */           
/*  949 */           parms.getMsgOpts.setWaitInterval((int)diff);
/*  950 */           Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "beforeGet(MQGetParameters parms)", "waiting up for a matching message, up to ms: ", 
/*      */ 
/*      */               
/*  953 */               String.valueOf(parms.getMsgOpts.getWaitInterval()));
/*      */         } 
/*      */       }
/*      */       
/*  957 */       bufSize = Math.max(bufSize, parms.dataLength.x);
/*  958 */       newBuffer = ByteBuffer.allocate(bufSize);
/*      */ 
/*      */       
/*  961 */       this.jmqi.MQGET(parms.hconn, hobjToUse, mqmdCopy, gmoCopy, bufSize, newBuffer, parms.dataLength, parms.cc, parms.rc);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  973 */     while (parms.rc.x == 2080);
/*  974 */     if (parms.rc.x == 2110) {
/*  975 */       getContext.setFormatErrorNeglected(true);
/*  976 */       parms.cc.x = 0;
/*  977 */       parms.rc.x = 0;
/*  978 */       if (Trace.isOn) {
/*  979 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getInLoop(MQGetParameters, MQGetContext, Hobj, MQMD, MQGMO)", "Treating MQRC_FORMAT_ERROR as MQCC_OK, MQRC_NONE", "");
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  984 */     if (parms.cc.x != 0) {
/*  985 */       HashMap<String, Object> inserts = new HashMap<>();
/*  986 */       inserts.put("AMS_INSERT_MQ_COMPLETION_CODE", Integer.valueOf(parms.cc.x).toString());
/*  987 */       inserts.put("AMS_INSERT_MQ_REASON_CODE", Integer.valueOf(parms.rc.x).toString());
/*  988 */       AmsErrorMessages.log(CLASS, meth, AmsErrorMessages.mqm_s_get_retrieve_msg_failed, inserts);
/*      */     } 
/*      */     
/*  991 */     if (Trace.isOn) {
/*  992 */       Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getInLoop(MQGetParameters, MQGetContext, Hobj, MQMD, MQGMO)", "bufferLength", 
/*      */           
/*  994 */           Integer.valueOf(bufSize));
/*      */     }
/*  996 */     if (Trace.isOn) {
/*  997 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getInLoop(MQGetParameters,MQGetContext,Hobj,MQMD,MQGMO,int)", newBuffer);
/*      */     }
/*      */     
/* 1000 */     return newBuffer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getNoBrowse(MQGetParameters parms, MQGetContext getContext, MsgStatus msgStatus) throws AMBIException, JmqiException, UnprotectException {
/* 1016 */     if (Trace.isOn) {
/* 1017 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getNoBrowse(MQGetParameters,MQGetContext,MsgStatus)", new Object[] { parms, getContext, msgStatus });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1022 */     String meth = "getNoBrowse";
/*      */ 
/*      */ 
/*      */     
/* 1026 */     if (isOptionSet(getContext.getOrigGmo().getOptions(), 64)) {
/*      */ 
/*      */       
/* 1029 */       MQMD mqmdCopy = this.env.copyMQMD(getContext.getOrigMQMD());
/* 1030 */       MQGMO gmoCopy = this.mqService.copyGetMsgOpts(this.env.newMQGMO(), getContext.getOrigGmo());
/* 1031 */       int opts = gmoCopy.getOptions();
/* 1032 */       opts &= 0xFFFFFFBF;
/* 1033 */       opts &= 0xFFFFFFFE;
/* 1034 */       opts |= 0x0;
/* 1035 */       gmoCopy.setOptions(opts);
/*      */       
/* 1037 */       ByteBuffer newBuffer = getInLoop(parms, getContext, parms.hobj, mqmdCopy, gmoCopy, 0);
/*      */       
/* 1039 */       if (parms.cc.x == 0) {
/* 1040 */         int origGmoVersion = parms.getMsgOpts.getVersion();
/* 1041 */         int origGmoOpts = parms.getMsgOpts.getOptions();
/* 1042 */         copyMQMD(parms.msgDesc, mqmdCopy);
/* 1043 */         this.mqService.copyGetMsgOpts(parms.getMsgOpts, gmoCopy);
/* 1044 */         parms.getMsgOpts.setVersion(origGmoVersion);
/* 1045 */         parms.getMsgOpts.setOptions(origGmoOpts);
/*      */         
/* 1047 */         msgStatus.isComplete = true;
/* 1048 */         msgStatus.isRemoved = true;
/* 1049 */         parms.newBuffer = newBuffer;
/* 1050 */         process(getContext, parms, msgStatus);
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1061 */       boolean mqOpenCalled = false;
/* 1062 */       Phobj pNewHobj = this.env.newPhobj();
/* 1063 */       Pint tmpCC = this.env.newPint();
/* 1064 */       Pint tmpRC = this.env.newPint();
/* 1065 */       MQOD od = this.env.newMQOD();
/* 1066 */       od.setObjectName(getContext.getSmqiObject().getResolvedName());
/* 1067 */       this.jmqi.MQOPEN(parms.hconn, od, 8, pNewHobj, tmpCC, tmpRC);
/*      */ 
/*      */       
/* 1070 */       if (tmpCC.x == 2) {
/* 1071 */         parms.cc.x = 2;
/* 1072 */         parms.rc.x = 2063;
/* 1073 */         HashMap<String, Object> inserts = new HashMap<>();
/* 1074 */         inserts.put("AMS_INSERT_MQ_REASON_CODE", Integer.valueOf(tmpRC.x).toString());
/* 1075 */         AmsErrorMessages.log(CLASS, meth, AmsErrorMessages.mqm_s_open_real_open_error, inserts);
/*      */       } else {
/*      */         
/*      */         try {
/* 1079 */           mqOpenCalled = true;
/*      */           
/*      */           while (true) {
/* 1082 */             MQMD mqmdCopy = this.env.copyMQMD(getContext.getOrigMQMD());
/* 1083 */             MQGMO gmoCopy = this.mqService.copyGetMsgOpts(this.env.newMQGMO(), getContext.getOrigGmo());
/* 1084 */             if (gmoCopy.getVersion() < 3) {
/* 1085 */               gmoCopy.setVersion(3);
/*      */             }
/* 1087 */             int opts = gmoCopy.getOptions();
/* 1088 */             opts &= 0xFFFFFFBF;
/* 1089 */             opts &= 0xFFFFFEFF;
/* 1090 */             opts &= 0xFFFFFF7F;
/* 1091 */             opts &= 0xFFFFFFFD;
/* 1092 */             opts &= 0xFFFFEFFF;
/* 1093 */             opts &= 0xFFFFFFEF;
/* 1094 */             opts |= 0x20;
/* 1095 */             opts &= 0xFFFFFFFE;
/* 1096 */             opts |= 0x0;
/* 1097 */             gmoCopy.setOptions(opts);
/*      */             
/* 1099 */             ByteBuffer newBuffer = getInLoop(parms, getContext, pNewHobj
/* 1100 */                 .getHobj(), mqmdCopy, gmoCopy, 0);
/* 1101 */             if (parms.cc.x == 0) {
/* 1102 */               int origGmoVersion = parms.getMsgOpts.getVersion();
/* 1103 */               int origGmoOpts = parms.getMsgOpts.getOptions();
/* 1104 */               copyMQMD(parms.msgDesc, mqmdCopy);
/* 1105 */               this.mqService.copyGetMsgOpts(parms.getMsgOpts, gmoCopy);
/* 1106 */               parms.getMsgOpts.setVersion(origGmoVersion);
/* 1107 */               parms.getMsgOpts.setOptions(origGmoOpts);
/*      */               
/* 1109 */               msgStatus.isComplete = true;
/* 1110 */               parms.newBuffer = newBuffer;
/* 1111 */               byte[] msgToken = gmoCopy.getMsgToken();
/* 1112 */               getContext.setMsgToken(msgToken);
/* 1113 */               process(getContext, parms, msgStatus);
/*      */             } 
/*      */             
/* 1116 */             if (parms.rc.x == 0 && !isOptionSet(getContext.getOrigGmo()
/* 1117 */                 .getOptions(), 64)) {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1122 */               if (removeMsgByMsgToken(parms, getContext)) {
/*      */                 
/* 1124 */                 msgStatus.isRemoved = true;
/*      */                 break;
/*      */               } 
/* 1127 */               if (parms.rc.x != 2033) {
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1132 */                 msgStatus.isComplete = msgStatus.isRemoved = false;
/*      */                 
/*      */                 break;
/*      */               } 
/*      */               
/*      */               continue;
/*      */             } 
/*      */             
/*      */             break;
/*      */           } 
/*      */         } finally {
/* 1143 */           if (Trace.isOn) {
/* 1144 */             Trace.finallyBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getNoBrowse(MQGetParameters,MQGetContext,MsgStatus)");
/*      */           }
/*      */           
/* 1147 */           if (mqOpenCalled) {
/* 1148 */             this.jmqi.MQCLOSE(parms.hconn, pNewHobj, 0, tmpCC, tmpRC);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1154 */     if (Trace.isOn) {
/* 1155 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getNoBrowse(MQGetParameters,MQGetContext,MsgStatus)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean removeMsgByMsgToken(MQGetParameters parms, MQGetContext getContext) {
/* 1173 */     if (Trace.isOn) {
/* 1174 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "removeMsgByMsgToken(MQGetParameters,MQGetContext)", new Object[] { parms, getContext });
/*      */     }
/*      */ 
/*      */     
/* 1178 */     boolean ret = false;
/* 1179 */     MQGMO gmoCopy = this.mqService.copyGetMsgOpts(this.env.newMQGMO(), getContext.getOrigGmo());
/* 1180 */     int opts = gmoCopy.getOptions();
/* 1181 */     opts |= 0x40;
/* 1182 */     opts &= 0xFFFFBFFF;
/* 1183 */     opts &= 0xFFFFFFFE;
/* 1184 */     opts &= 0xFFFFFFF7;
/* 1185 */     gmoCopy.setOptions(opts);
/*      */     
/* 1187 */     if (gmoCopy.getVersion() < 3) {
/* 1188 */       gmoCopy.setVersion(3);
/*      */     }
/* 1190 */     if (getContext.getMsgToken() == null || Arrays.equals(getContext.getMsgToken(), CMQC.MQMTOK_NONE)) {
/* 1191 */       gmoCopy.setMatchOptions(3);
/*      */     } else {
/*      */       
/* 1194 */       gmoCopy.setMatchOptions(32);
/* 1195 */       gmoCopy.setMsgToken(getContext.getMsgToken());
/*      */     } 
/*      */     
/* 1198 */     Pint dataLengthTmp = this.env.newPint();
/* 1199 */     Pint ccTmp = this.env.newPint();
/* 1200 */     Pint rcTmp = this.env.newPint();
/* 1201 */     ByteBuffer buffTmp = ByteBuffer.allocate(1);
/* 1202 */     MQMD mqmdTmp = this.env.copyMQMD(parms.msgDesc);
/* 1203 */     this.jmqi.MQGET(parms.hconn, parms.hobj, mqmdTmp, gmoCopy, 0, buffTmp, dataLengthTmp, ccTmp, rcTmp);
/*      */ 
/*      */     
/* 1206 */     if (rcTmp.x == 2079 || rcTmp.x == 0) {
/*      */       
/* 1208 */       ret = true;
/*      */     }
/*      */     else {
/*      */       
/* 1212 */       cleanUpAfterFailedRemoval(parms, 0, ccTmp.x, rcTmp.x);
/*      */     } 
/*      */     
/* 1215 */     if (Trace.isOn) {
/* 1216 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "removeMsgByMsgToken(MQGetParameters,MQGetContext)", 
/* 1217 */           Boolean.valueOf(ret));
/*      */     }
/* 1219 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void process(MQGetContext context, MQGetParameters parms, MsgStatus msgStatus) throws JmqiException, UnprotectException, AMBIException {
/* 1237 */     if (Trace.isOn) {
/* 1238 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "process(MQGetContext,MQGetParameters,MsgStatus)", new Object[] { context, parms, msgStatus });
/*      */     }
/*      */ 
/*      */     
/* 1242 */     String meth = "process";
/* 1243 */     SecurityPolicy secPolicy = context.getSecPolicy();
/*      */     
/* 1245 */     MessageBufferProcessor mbproc = processMessageHeaders(parms);
/* 1246 */     AMBIHeader hdr = getAmbiHeader(secPolicy.getTolerateFlag(), mbproc);
/* 1247 */     byte[] converted = null;
/* 1248 */     if (hdr == null && secPolicy.getTolerateFlag() == 1) {
/* 1249 */       if (Trace.isOn) {
/* 1250 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "process(MQGetContext, MQGetParameters, MsgStatus)", "tolerate not protected message", "");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1255 */       constructOutput(context, parms, mbproc, parms.newBuffer, true);
/* 1256 */       if (context.isFormatErrorNeglected() && parms.rc.x != 2080) {
/* 1257 */         parms.cc.x = 1;
/* 1258 */         parms.rc.x = 2110;
/*      */       } 
/*      */     } else {
/*      */       
/* 1262 */       validateQop(hdr, secPolicy);
/*      */       
/* 1264 */       SmqiObject smqiObject = context.getSmqiObject();
/* 1265 */       MessageUnprotectInfo unprotectedInfo = unprotect(smqiObject.getUser(), secPolicy, hdr, smqiObject, mbproc);
/* 1266 */       validateUnprotected(parms, hdr, smqiObject, unprotectedInfo);
/*      */ 
/*      */       
/* 1269 */       if (hdr.isNullMessage()) {
/* 1270 */         if (Trace.isOn) {
/* 1271 */           Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "process(MQGetContext, MQGetParameters, MsgStatus)", "null flag is set in the PDMQ header", "");
/*      */         }
/*      */ 
/*      */         
/* 1275 */         mbproc.setPayloadFormat(hdr.getOrigFormat(), parms.hconn
/* 1276 */             .getCcsid());
/* 1277 */         constructNullMsgOutput(parms, mbproc);
/*      */       } else {
/*      */         
/* 1280 */         mbproc.setPayloadFormat(hdr.getOrigFormat(), parms.hconn.getCcsid());
/*      */ 
/*      */         
/* 1283 */         if (notTruncatedMsgFailed(parms, mbproc, unprotectedInfo
/* 1284 */             .getUnprotectedData())) {
/* 1285 */           converted = convert(context, parms, mbproc, unprotectedInfo
/* 1286 */               .getUnprotectedData());
/*      */         } else {
/*      */           
/* 1289 */           converted = unprotectedInfo.getUnprotectedData();
/*      */         } 
/* 1291 */         if (msgStatus.isRemoved && mbproc.getMqHeadersLength() + converted.length > parms.buffer
/* 1292 */           .capacity() && !isOptionSet(context.getOrigGmo().getOptions(), 64)) {
/*      */ 
/*      */           
/* 1295 */           HashMap<String, Object> inserts = new HashMap<>();
/* 1296 */           inserts.put("AMS_INSERT_Q_NAME", smqiObject.getResolvedName());
/* 1297 */           inserts.put("AMS_INSERT_MQ_COMPLETION_CODE", Integer.valueOf(2).toString());
/* 1298 */           inserts.put("AMS_INSERT_MQ_REASON_CODE", Integer.valueOf(2120).toString());
/* 1299 */           EseMQException ex = new EseMQException(AmsErrorMessages.mqm_s_get_converted_msg_too_big, inserts);
/* 1300 */           ex.setReason(2120);
/* 1301 */           onException(context, parms, meth, msgStatus, (Exception)ex, 2120);
/*      */         } 
/*      */         
/* 1304 */         constructOutput(context, parms, mbproc, 
/* 1305 */             ByteBuffer.wrap(converted), false);
/*      */       } 
/*      */     } 
/*      */     
/* 1309 */     if (Trace.isOn) {
/* 1310 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "process(MQGetContext,MQGetParameters,MsgStatus)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean notTruncatedMsgFailed(MQGetParameters parms, MessageBufferProcessor mbproc, byte[] unprotectedData) {
/* 1326 */     if (Trace.isOn) {
/* 1327 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "notTruncatedMsgFailed(MQGetParameters,MessageBufferProcessor,byte [ ])", new Object[] { parms, mbproc, unprotectedData });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1332 */     boolean traceRet1 = (isOptionSet(parms.getMsgOpts.getOptions(), 64) || (unprotectedData != null && mbproc.getMqHeadersLength() + unprotectedData.length <= parms.getMaxMsgLength()));
/* 1333 */     if (Trace.isOn) {
/* 1334 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "notTruncatedMsgFailed(MQGetParameters,MessageBufferProcessor,byte [ ])", 
/*      */           
/* 1336 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1338 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] convert(MQGetContext context, MQGetParameters parms, MessageBufferProcessor mbproc, byte[] toConvert) throws CcsidException, JmqiException {
/* 1356 */     if (Trace.isOn) {
/* 1357 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "convert(MQGetContext,MQGetParameters,MessageBufferProcessor,byte [ ])", new Object[] { context, parms, mbproc, toConvert });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1362 */     byte[] converted = toConvert;
/*      */     try {
/* 1364 */       if (toConvert != null && 
/* 1365 */         isOptionSet(parms.getMsgOpts.getOptions(), 16384))
/*      */       {
/*      */ 
/*      */         
/* 1369 */         if ("MQSTR   ".equals(mbproc.getPayloadFormat())) {
/* 1370 */           int srcCCSID = mbproc.getPayloadCcsid();
/* 1371 */           int dstCCSID = context.getOrigMQMD().getCodedCharSetId();
/* 1372 */           int encodingDst = context.getOrigMQMD().getEncoding();
/* 1373 */           int dftCCSID = parms.hconn.getCcsid();
/*      */           
/* 1375 */           converted = this.ccsidConverter.convert(toConvert, srcCCSID, dstCCSID, dftCCSID);
/* 1376 */           if (dstCCSID == 0) {
/* 1377 */             mbproc.setPayloadEncodingCcsid(encodingDst, dftCCSID);
/*      */           } else {
/*      */             
/* 1380 */             mbproc.setPayloadEncodingCcsid(encodingDst, dstCCSID);
/*      */           } 
/*      */         } else {
/*      */           
/* 1384 */           context.setFormatErrorNeglected(true);
/*      */         }
/*      */       
/*      */       }
/*      */     }
/* 1389 */     catch (CcsidException e) {
/* 1390 */       if (Trace.isOn) {
/* 1391 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "convert(MQGetContext,MQGetParameters,MessageBufferProcessor,byte [ ])", (Throwable)e);
/*      */       }
/*      */       
/* 1394 */       if (e.getCause() instanceof JmqiException) {
/* 1395 */         JmqiException traceRet1 = (JmqiException)e.getCause();
/* 1396 */         if (Trace.isOn) {
/* 1397 */           Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "convert(MQGetContext,MQGetParameters,MessageBufferProcessor,byte [ ])", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/* 1400 */         throw traceRet1;
/*      */       } 
/*      */       
/* 1403 */       if (Trace.isOn) {
/* 1404 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "convert(MQGetContext,MQGetParameters,MessageBufferProcessor,byte [ ])", (Throwable)e, 2);
/*      */       }
/*      */       
/* 1407 */       throw e;
/*      */     } 
/*      */     
/* 1410 */     if (Trace.isOn) {
/* 1411 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "convert(MQGetContext,MQGetParameters,MessageBufferProcessor,byte [ ])", converted);
/*      */     }
/*      */     
/* 1414 */     return converted;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void constructNullMsgOutput(MQGetParameters parms, MessageBufferProcessor mbproc) {
/* 1426 */     if (Trace.isOn) {
/* 1427 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "constructNullMsgOutput(MQGetParameters,MessageBufferProcessor)", new Object[] { parms, mbproc });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1433 */     int pdmqHeaderOffset = mbproc.getPayloadPos();
/*      */     
/* 1435 */     parms.dataLength.x = pdmqHeaderOffset;
/* 1436 */     if (parms.getMsgOpts.getVersion() >= 3) {
/* 1437 */       parms.getMsgOpts.setReturnedLength(pdmqHeaderOffset);
/*      */     }
/*      */     
/* 1440 */     clearBuffer(parms.buffer, pdmqHeaderOffset);
/* 1441 */     if (pdmqHeaderOffset <= parms.buffer.capacity()) {
/* 1442 */       parms.buffer.limit(pdmqHeaderOffset);
/*      */     }
/*      */     
/* 1445 */     if (Trace.isOn) {
/* 1446 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "constructNullMsgOutput(MQGetParameters,MessageBufferProcessor)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void constructOutput(MQGetContext mqGetContext, MQGetParameters parms, MessageBufferProcessor mbproc, ByteBuffer converted, boolean convertedContainsHeaders) {
/* 1464 */     if (Trace.isOn) {
/* 1465 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "constructOutput(MQGetContext,MQGetParameters,MessageBufferProcessor,ByteBuffer,boolean)", new Object[] { mqGetContext, parms, mbproc, converted, 
/*      */ 
/*      */             
/* 1468 */             Boolean.valueOf(convertedContainsHeaders) });
/*      */     }
/*      */ 
/*      */     
/* 1472 */     int totalLength = convertedContainsHeaders ? converted.limit() : (mbproc.getMqHeadersLength() + converted.limit());
/* 1473 */     if (totalLength == 0) {
/* 1474 */       if (Trace.isOn) {
/* 1475 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "constructOutput(MQGetContext, MQGetParameters, MessageBufferProcessor, ByteBuffer, boolean)", "message size is 0", "");
/*      */       }
/*      */ 
/*      */       
/* 1479 */       parms.dataLength.x = 0;
/* 1480 */       if (parms.getMsgOpts.getVersion() >= 3) {
/* 1481 */         parms.getMsgOpts.setReturnedLength(0);
/*      */       }
/* 1483 */       flipBufferIfNecessary(parms.buffer);
/* 1484 */       Arrays.fill(parms.buffer.array(), (byte)0);
/* 1485 */       parms.buffer.limit(0);
/* 1486 */       parms.cc.x = 0;
/* 1487 */       parms.rc.x = 0;
/*      */     
/*      */     }
/* 1490 */     else if (parms.getMaxMsgLength() < totalLength) {
/*      */       
/* 1492 */       if (mbproc.getEntireBuffer() != parms.buffer)
/*      */       {
/*      */ 
/*      */         
/* 1496 */         if (parms.getMaxMsgLength() < mbproc.getMqHeadersLength()) {
/* 1497 */           System.arraycopy(mbproc.getEntireBuffer().array(), 0, parms.buffer
/* 1498 */               .array(), 0, parms
/* 1499 */               .getMaxMsgLength());
/*      */         } else {
/*      */           
/* 1502 */           System.arraycopy(mbproc.getEntireBuffer().array(), 0, parms.buffer
/* 1503 */               .array(), 0, mbproc
/* 1504 */               .getMqHeadersLength());
/*      */         } 
/*      */       }
/*      */       
/* 1508 */       if (parms.getMaxMsgLength() > mbproc.getMqHeadersLength() && converted != parms.buffer) {
/* 1509 */         System.arraycopy(converted.array(), 0, parms.buffer
/* 1510 */             .array(), mbproc.getMqHeadersLength(), parms
/* 1511 */             .getMaxMsgLength() - mbproc.getMqHeadersLength());
/*      */       }
/* 1513 */       if (parms.getMsgOpts.getVersion() >= 3) {
/* 1514 */         parms.getMsgOpts.setReturnedLength(parms.getMaxMsgLength());
/*      */       }
/* 1516 */       parms.cc.x = 1;
/* 1517 */       if (isOptionSet(mqGetContext.getOrigGmo().getOptions(), 64)) {
/*      */         
/* 1519 */         parms.rc.x = 2079;
/*      */       } else {
/*      */         
/* 1522 */         parms.rc.x = 2080;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1527 */       if (mbproc.getEntireBuffer() != parms.buffer) {
/* 1528 */         System.arraycopy(mbproc.getEntireBuffer().array(), 0, parms.buffer
/* 1529 */             .array(), 0, mbproc
/* 1530 */             .getMqHeadersLength());
/*      */       }
/*      */       
/* 1533 */       if (converted != parms.buffer) {
/* 1534 */         System.arraycopy(converted.array(), 0, parms.buffer
/* 1535 */             .array(), mbproc.getMqHeadersLength(), converted
/* 1536 */             .limit());
/* 1537 */         parms.buffer.limit(totalLength);
/* 1538 */         if ((parms.buffer.array()).length > mbproc.getMqHeadersLength() + converted.limit());
/*      */       } 
/*      */ 
/*      */       
/* 1542 */       if (parms.getMsgOpts.getVersion() >= 3) {
/* 1543 */         parms.getMsgOpts.setReturnedLength(totalLength);
/*      */       }
/* 1545 */       parms.cc.x = 0;
/* 1546 */       parms.rc.x = 0;
/*      */     } 
/*      */     
/* 1549 */     parms.dataLength.x = totalLength;
/*      */     
/* 1551 */     if (Trace.isOn) {
/* 1552 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "constructOutput(MQGetContext,MQGetParameters,MessageBufferProcessor,ByteBuffer,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isValidSigner(SecurityPolicy secPolicy, String senderDN) throws AMBIException {
/* 1570 */     if (Trace.isOn) {
/* 1571 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "isValidSigner(SecurityPolicy,String)", new Object[] { secPolicy, senderDN });
/*      */     }
/*      */ 
/*      */     
/* 1575 */     boolean ret = false;
/* 1576 */     if (senderDN != null) {
/* 1577 */       X500NameWrapper senderWrapper = new X500NameWrapper(senderDN);
/* 1578 */       List<String> signersList = secPolicy.getSignerDNs();
/* 1579 */       if (signersList == null || signersList.size() == 0) {
/* 1580 */         if (Trace.isOn) {
/* 1581 */           Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "isValidSigner(SecurityPolicy,String)", 
/* 1582 */               Boolean.valueOf(true), 1);
/*      */         }
/* 1584 */         return true;
/*      */       } 
/* 1586 */       for (String signer : signersList) {
/* 1587 */         X500NameWrapper dn = new X500NameWrapper(signer);
/* 1588 */         if (Trace.isOn) {
/* 1589 */           Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "isValidSigner(SecurityPolicy, String)", "Comparing: ", new Object[] { dn, senderWrapper });
/*      */         }
/*      */ 
/*      */         
/* 1593 */         if (dn.isEqual(senderWrapper)) {
/* 1594 */           ret = true;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1600 */     if (Trace.isOn) {
/* 1601 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "isValidSigner(SecurityPolicy,String)", 
/* 1602 */           Boolean.valueOf(ret), 2);
/*      */     }
/* 1604 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void restoreGmoOpts(MQGetContext context, MQGetParameters parms) {
/* 1614 */     if (Trace.isOn) {
/* 1615 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "restoreGmoOpts(MQGetContext,MQGetParameters)", new Object[] { context, parms });
/*      */     }
/*      */     
/* 1618 */     int origGmoOpts = context.getOrigGmo().getOptions();
/* 1619 */     if (isOptionSet(origGmoOpts, 64)) {
/* 1620 */       parms.getMsgOpts.setOptions(parms.getMsgOpts.getOptions() | 0x40);
/*      */     }
/* 1622 */     if (isOptionSet(origGmoOpts, 16384)) {
/* 1623 */       parms.getMsgOpts.setOptions(parms.getMsgOpts.getOptions() | 0x4000);
/*      */     }
/* 1625 */     if (Trace.isOn) {
/* 1626 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "restoreGmoOpts(MQGetContext,MQGetParameters)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private AMBIHeader getAmbiHeader(int tolerateFlag, MessageBufferProcessor mbproc) throws UnprotectException {
/* 1642 */     if (Trace.isOn) {
/* 1643 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getAmbiHeader(int,MessageBufferProcessor)", new Object[] {
/* 1644 */             Integer.valueOf(tolerateFlag), mbproc
/*      */           });
/*      */     }
/*      */     
/* 1648 */     AMBIHeader hdr = null;
/*      */     try {
/* 1650 */       hdr = readAmbiHeader(mbproc, tolerateFlag);
/* 1651 */       if (hdr == null && tolerateFlag == 1) {
/* 1652 */         if (Trace.isOn) {
/* 1653 */           Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getAmbiHeader(int,MessageBufferProcessor)", null, 1);
/*      */         }
/*      */         
/* 1656 */         return null;
/*      */       } 
/* 1658 */       validateAmbiHeader(hdr);
/*      */     
/*      */     }
/* 1661 */     catch (IncorrectHeaderException e) {
/* 1662 */       if (Trace.isOn) {
/* 1663 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getAmbiHeader(int,MessageBufferProcessor)", (Throwable)e);
/*      */       }
/*      */       
/* 1666 */       AMBIException ex = new AMBIException(AmsErrorMessages.mjm_msg_error_unprotect, (Throwable)e);
/* 1667 */       UnprotectException traceRet1 = new UnprotectException(ex);
/* 1668 */       if (Trace.isOn) {
/* 1669 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getAmbiHeader(int,MessageBufferProcessor)", traceRet1);
/*      */       }
/*      */       
/* 1672 */       throw traceRet1;
/*      */     } 
/* 1674 */     displayHeader(hdr);
/* 1675 */     if (Trace.isOn) {
/* 1676 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getAmbiHeader(int,MessageBufferProcessor)", hdr, 2);
/*      */     }
/*      */     
/* 1679 */     return hdr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void onException(MQGetContext getContext, MQGetParameters parms, String meth, MsgStatus msgStatus, Exception e, int rc) {
/* 1697 */     if (Trace.isOn) {
/* 1698 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "onException(MQGetContext,MQGetParameters,String,MsgStatus,Exception,int)", new Object[] { getContext, parms, meth, msgStatus, e, 
/*      */             
/* 1700 */             Integer.valueOf(rc) });
/*      */     }
/* 1702 */     HashMap<String, Exception> data = new HashMap<>();
/* 1703 */     data.put("exception", e);
/* 1704 */     Trace.ffst(CLASS, meth, "MP004002", data, null);
/* 1705 */     onExceptionNoFFDC(getContext, parms, meth, msgStatus, e, rc);
/* 1706 */     if (Trace.isOn) {
/* 1707 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "onException(MQGetContext,MQGetParameters,String,MsgStatus,Exception,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void onExceptionNoFFDC(MQGetContext getContext, MQGetParameters parms, String meth, MsgStatus msgStatus, Exception e, int rc) {
/* 1728 */     if (Trace.isOn) {
/* 1729 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "onExceptionNoFFDC(MQGetContext,MQGetParameters,String,MsgStatus,Exception,int)", new Object[] { getContext, parms, meth, msgStatus, e, 
/*      */             
/* 1731 */             Integer.valueOf(rc) });
/*      */     }
/* 1733 */     AmsErrorMessages.logException(CLASS, meth, e);
/* 1734 */     setErrorReasonCode(parms.cc, parms.rc, rc);
/* 1735 */     handleGetError(parms, getContext, parms.rc.x, msgStatus);
/* 1736 */     if (Trace.isOn) {
/* 1737 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "onExceptionNoFFDC(MQGetContext,MQGetParameters,String,MsgStatus,Exception,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void validateUnprotected(MQGetParameters parms, AMBIHeader hdr, SmqiObject smqiObject, MessageUnprotectInfo unprotected) throws UnprotectException {
/* 1758 */     if (Trace.isOn) {
/* 1759 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters,AMBIHeader,SmqiObject,MessageUnprotectInfo)", new Object[] { parms, hdr, smqiObject, unprotected });
/*      */     }
/*      */ 
/*      */     
/*      */     try { IncorrectHeaderException ex;
/*      */       
/* 1765 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1766 */       switch (hdr.getQop()) {
/*      */         case 1:
/* 1768 */           inserts.put("AMS_INSERT_Q_NAME", smqiObject.getResolvedName());
/* 1769 */           inserts.put("AMS_INSERT_DISTINGUISHED_NAME", unprotected.getSenderDN());
/* 1770 */           inserts.put("AMS_INSERT_TIMESTAMP", unprotected.getSignDate());
/* 1771 */           inserts.put("AMS_INSERT_SIGNATURE_ALGORITHM", unprotected.getSignAlg());
/*      */           break;
/*      */         
/*      */         case 2:
/* 1775 */           inserts.put("AMS_INSERT_Q_NAME", smqiObject.getResolvedName());
/* 1776 */           inserts.put("AMS_INSERT_DISTINGUISHED_NAME", unprotected.getSenderDN());
/* 1777 */           inserts.put("AMS_INSERT_TIMESTAMP", unprotected.getSignDate());
/* 1778 */           inserts.put("AMS_INSERT_SIGNATURE_ALGORITHM", unprotected.getSignAlg());
/* 1779 */           inserts.put("AMS_INSERT_ENCRYPTION_ALGORITHM", unprotected.getEncAlg());
/*      */           break;
/*      */         case 3:
/* 1782 */           inserts.put("AMS_INSERT_Q_NAME", smqiObject.getResolvedName());
/* 1783 */           inserts.put("AMS_INSERT_ENCRYPTION_ALGORITHM", unprotected.getEncAlg());
/*      */           break;
/*      */         
/*      */         default:
/* 1787 */           ex = new IncorrectHeaderException(AmsErrorMessages.mqm_s_get_invalid_protection);
/* 1788 */           if (Trace.isOn) {
/* 1789 */             Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters,AMBIHeader,SmqiObject,MessageUnprotectInfo)", (Throwable)ex, 1);
/*      */           }
/*      */           
/* 1792 */           throw ex;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1797 */       int unprotLength = (unprotected.getUnprotectedData()).length;
/* 1798 */       if (unprotLength != hdr.getOrigSize()) {
/* 1799 */         if (Trace.isOn) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1805 */           String traceInfo = "Unprotected message size " + unprotLength + " != original size " + hdr.getOrigSize() + "; queue is " + smqiObject.getResolvedName() + " qmgr is " + smqiObject.getContext().getQmgrName();
/*      */           
/* 1807 */           Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters, AMBIHeader, SmqiObject, MessageUnprotectInfo)", traceInfo, "");
/*      */         } 
/*      */ 
/*      */         
/* 1811 */         inserts.put("AMS_INSERT_MESSAGE_SIZE", Integer.valueOf(unprotLength));
/* 1812 */         inserts.put("AMS_INSERT_ORIGINAL_MESSAGE_SIZE", Integer.toString(hdr.getOrigSize()));
/* 1813 */         AMBIException exx = new AMBIException(AmsErrorMessages.mqm_s_get_unprotect_size_mismatch, inserts);
/* 1814 */         AMBIException aMBIException1 = new AMBIException(AmsErrorMessages.mjm_msg_error_unprotect, (Throwable)exx);
/* 1815 */         UnprotectException traceRet1 = new UnprotectException(aMBIException1);
/* 1816 */         if (Trace.isOn) {
/* 1817 */           Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters,AMBIHeader,SmqiObject,MessageUnprotectInfo)", traceRet1, 2);
/*      */         }
/*      */         
/* 1820 */         throw traceRet1;
/*      */       } 
/*      */ 
/*      */       
/* 1824 */       if ((unprotected.wasSigned() && hdr.getProtType() == 4) || (unprotected
/* 1825 */         .wasSealed() && hdr.getProtType() == 8) || (unprotected
/* 1826 */         .wasConfidential() && hdr.getProtType() == 64)) {
/* 1827 */         if (Trace.isOn) {
/* 1828 */           Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters, AMBIHeader, SmqiObject, MessageUnprotectInfo)", "Message QoP matches the indication in the header", "");
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1835 */         if (Trace.isOn) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1841 */           String traceInfo = "Message QoP " + unprotected.getProtType() + " doesn't match " + hdr.getProtType() + " indicated in the header; queue is " + smqiObject.getResolvedName() + " qmgr is " + smqiObject.getContext().getQmgrName();
/*      */           
/* 1843 */           Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters, AMBIHeader, SmqiObject, MessageUnprotectInfo)", traceInfo, "");
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1848 */         String id = JmqiUtils.arrayToHexString(parms.msgDesc.getMsgId());
/* 1849 */         inserts.put("AMS_INSERT_QM_NAME", smqiObject.getContext().getQmgrName());
/* 1850 */         inserts.put("AMS_INSERT_Q_NAME", smqiObject.getResolvedName());
/* 1851 */         inserts.put("AMS_INSERT_MESSAGE_ID", id);
/* 1852 */         QopMismatchException qopMismatchException = new QopMismatchException(AmsErrorMessages.mqm_s_get_unprotect_qop_mismatch, inserts);
/* 1853 */         if (Trace.isOn) {
/* 1854 */           Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters,AMBIHeader,SmqiObject,MessageUnprotectInfo)", (Throwable)qopMismatchException, 3);
/*      */         }
/*      */         
/* 1857 */         throw qopMismatchException;
/*      */       } 
/* 1859 */       if (unprotected.wasConfidential() || 
/* 1860 */         isValidSigner(smqiObject.getSecPolicy(), unprotected
/* 1861 */           .getSenderDN()))
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1932 */         if (Trace.isOn)
/* 1933 */           Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters,AMBIHeader,SmqiObject,MessageUnprotectInfo)");  return; }  if (Trace.isOn)
/*      */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters, AMBIHeader, SmqiObject, MessageUnprotectInfo)", "DN not in singer DNs' list: ", unprotected.getSenderDN());  AMBIException aMBIException = new AMBIException(AmsErrorMessages.mqm_s_get_signer_dn_mismatch); UnprotectException traceRet2 = new UnprotectException(aMBIException); if (Trace.isOn)
/*      */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters,AMBIHeader,SmqiObject,MessageUnprotectInfo)", traceRet2, 4);  throw traceRet2; } catch (IncorrectHeaderException e) { if (Trace.isOn)
/*      */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters,AMBIHeader,SmqiObject,MessageUnprotectInfo)", (Throwable)e, 1);  AMBIException ex = new AMBIException(AmsErrorMessages.mjm_msg_error_unprotect, (Throwable)e); UnprotectException traceRet3 = new UnprotectException(ex); if (Trace.isOn)
/*      */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters,AMBIHeader,SmqiObject,MessageUnprotectInfo)", traceRet3, 5);  throw traceRet3; } catch (QopMismatchException e) { if (Trace.isOn)
/*      */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters,AMBIHeader,SmqiObject,MessageUnprotectInfo)", (Throwable)e, 2);  AMBIException ex = new AMBIException(AmsErrorMessages.mjm_msg_error_unprotect, (Throwable)e); UnprotectException traceRet4 = new UnprotectException(ex); if (Trace.isOn)
/*      */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters,AMBIHeader,SmqiObject,MessageUnprotectInfo)", traceRet4, 6);  throw traceRet4; }
/*      */     catch (UnprotectException e) { if (Trace.isOn)
/*      */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters,AMBIHeader,SmqiObject,MessageUnprotectInfo)", e, 3);  if (Trace.isOn)
/*      */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters,AMBIHeader,SmqiObject,MessageUnprotectInfo)", e, 7);  throw e; }
/*      */     catch (Exception e) { if (Trace.isOn)
/*      */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters,AMBIHeader,SmqiObject,MessageUnprotectInfo)", e, 4);  AMBIException ex = new AMBIException(AmsErrorMessages.mjm_msg_error_unprotect, e); UnprotectException traceRet5 = new UnprotectException(ex); if (Trace.isOn)
/*      */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateUnprotected(MQGetParameters,AMBIHeader,SmqiObject,MessageUnprotectInfo)", traceRet5, 8);  throw traceRet5; }
/* 1946 */      } private void displayHeader(AMBIHeader hdr) { if (Trace.isOn) {
/* 1947 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "displayHeader(AMBIHeader)", new Object[] { hdr });
/*      */     }
/*      */     
/* 1950 */     StringBuilder h = new StringBuilder("\n ------ PDMQ Header -----\n");
/* 1951 */     h.append("\t MajorVersion : '").append(hdr.getVersionMajor());
/* 1952 */     h.append("' MinorVersion : '").append(hdr.getVersionMinor())
/* 1953 */       .append("'");
/* 1954 */     h.append("\n\t HeaderEncoding : '").append(hdr.getHeaderEncoding());
/* 1955 */     h.append("' HeaderCCSID : '").append(hdr.getHeaderCCSID()).append("'");
/* 1956 */     h.append("\n\t Dynamic Queue : '").append(hdr
/* 1957 */         .getDynamicQueueName().trim()).append("'");
/* 1958 */     h.append("\n\t HeaderSize : '").append(hdr.getHeaderSize());
/* 1959 */     h.append("' DataOffset : '").append(hdr.getDataOffset());
/* 1960 */     h.append("' ProtType : '").append(hdr.getProtType()).append("'");
/* 1961 */     h.append("\n\t OrigFormat : '").append(hdr.getOrigFormat());
/* 1962 */     h.append("' OrigSize : '").append(hdr.getOrigSize()).append("'");
/* 1963 */     byte[] flagarray = hdr.getFlag();
/* 1964 */     byte flag = 0;
/* 1965 */     if (flagarray != null && flagarray.length > 0) {
/* 1966 */       flag = flagarray[0];
/*      */     }
/* 1968 */     h.append("\t Flag : '").append(flag).append("'");
/* 1969 */     if (Trace.isOn) {
/* 1970 */       Trace.data(this, h.toString(), null);
/*      */     }
/* 1972 */     if (Trace.isOn) {
/* 1973 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "displayHeader(AMBIHeader)");
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MessageBufferProcessor processMessageHeaders(MQGetParameters parms) throws JmqiException {
/* 1988 */     if (Trace.isOn) {
/* 1989 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "processMessageHeaders(MQGetParameters)", new Object[] { parms });
/*      */     }
/*      */     
/* 1992 */     if (Trace.isOn) {
/* 1993 */       Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "processMessageHeaders(MQGetContext, MQGetParameters)", "default CCSID is ", 
/*      */           
/* 1995 */           Integer.valueOf(parms.hconn.getCcsid()));
/*      */     }
/*      */     
/* 1998 */     MessageBufferProcessor mbproc = new MessageBufferProcessorImpl(this.env, this.jmqi);
/*      */     
/* 2000 */     flipBufferIfNecessary(parms.newBuffer);
/* 2001 */     mbproc.wrap(parms.msgDesc, parms.newBuffer);
/* 2002 */     mbproc.process(parms.hconn.getCcsid());
/*      */     
/* 2004 */     if (Trace.isOn) {
/* 2005 */       Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "processMessageHeaders(MQGetContext, MQGetParameters)", "Successfully processed message headers", "");
/*      */     }
/*      */ 
/*      */     
/* 2009 */     if (Trace.isOn) {
/* 2010 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "processMessageHeaders(MQGetParameters)", mbproc);
/*      */     }
/*      */     
/* 2013 */     return mbproc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void clearBuffer(ByteBuffer buffer, int startingFrom) {
/* 2023 */     if (Trace.isOn) {
/* 2024 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "clearBuffer(ByteBuffer,int)", new Object[] { buffer, 
/* 2025 */             Integer.valueOf(startingFrom) });
/*      */     }
/* 2027 */     if (buffer != null) {
/* 2028 */       flipBufferIfNecessary(buffer);
/* 2029 */       int prevLimit = buffer.limit();
/* 2030 */       buffer.limit(buffer.capacity());
/* 2031 */       int end = buffer.capacity();
/* 2032 */       Arrays.fill(buffer.array(), startingFrom, end, (byte)0);
/* 2033 */       buffer.limit(prevLimit);
/*      */     } 
/* 2035 */     if (Trace.isOn) {
/* 2036 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "clearBuffer(ByteBuffer,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void validateAmbiHeader(AMBIHeader hdr) throws IncorrectHeaderException {
/* 2053 */     if (Trace.isOn) {
/* 2054 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateAmbiHeader(AMBIHeader)", new Object[] { hdr });
/*      */     }
/*      */ 
/*      */     
/* 2058 */     String meth = "validateAmbiHeader";
/* 2059 */     if (hdr == null) {
/* 2060 */       IncorrectHeaderException ex = new IncorrectHeaderException(AmsErrorMessages.mju_ambi_header_invalid);
/* 2061 */       if (Trace.isOn) {
/* 2062 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateAmbiHeader(AMBIHeader)", (Throwable)ex, 1);
/*      */       }
/*      */       
/* 2065 */       throw ex;
/*      */     } 
/* 2067 */     if (hdr.getVersionMajor() < 2 || hdr.getVersionMajor() > 3) {
/* 2068 */       if (Trace.isOn) {
/* 2069 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateAmbiHeader(AMBIHeader)", "PDMQ version number does not match", "");
/*      */       }
/*      */       
/* 2072 */       AmsErrorMessages.log(CLASS, meth, AmsErrorMessages.mqm_s_get_ivmqhdr_invalid);
/* 2073 */       IncorrectHeaderException ex = new IncorrectHeaderException(AmsErrorMessages.mqm_s_get_ivmqhdr_invalid);
/* 2074 */       if (Trace.isOn) {
/* 2075 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateAmbiHeader(AMBIHeader)", (Throwable)ex, 2);
/*      */       }
/*      */       
/* 2078 */       throw ex;
/*      */     } 
/*      */     
/* 2081 */     if (Trace.isOn) {
/* 2082 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateAmbiHeader(AMBIHeader)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private AMBIHeader readAmbiHeader(MessageBufferProcessor mbproc, int tolerate) throws IncorrectHeaderException {
/* 2099 */     if (Trace.isOn) {
/* 2100 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "readAmbiHeader(MessageBufferProcessor,int)", new Object[] { mbproc, 
/* 2101 */             Integer.valueOf(tolerate) });
/*      */     }
/*      */ 
/*      */     
/* 2105 */     AMBIHeader hdr = null;
/*      */     try {
/* 2107 */       byte[] protMsg = mbproc.getEntireBuffer().array();
/* 2108 */       int pdmqHeaderOffset = mbproc.getPayloadPos();
/* 2109 */       if (mbproc.getPayloadLength() < 104 || protMsg.length < 104) {
/*      */         
/* 2111 */         if (tolerate == 1) {
/* 2112 */           if (Trace.isOn) {
/* 2113 */             Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "readAmbiHeader(MessageBufferProcessor,int)", null, 1);
/*      */           }
/*      */           
/* 2116 */           return null;
/*      */         } 
/* 2118 */         IncorrectHeaderException ex = new IncorrectHeaderException(AmsErrorMessages.mju_ambi_header_invalid);
/* 2119 */         if (Trace.isOn) {
/* 2120 */           Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "readAmbiHeader(MessageBufferProcessor,int)", (Throwable)ex, 1);
/*      */         }
/*      */         
/* 2123 */         throw ex;
/*      */       } 
/*      */       
/* 2126 */       int ambiHeaderSize = (AMBIHeader.getVersion(protMsg, pdmqHeaderOffset) == 3) ? 112 : 104;
/*      */ 
/*      */       
/* 2129 */       byte[] hdrData = new byte[ambiHeaderSize];
/* 2130 */       System.arraycopy(protMsg, pdmqHeaderOffset, hdrData, 0, 
/* 2131 */           Math.min(protMsg.length, ambiHeaderSize));
/*      */       
/* 2133 */       ByteArrayInputStream bais = new ByteArrayInputStream(hdrData);
/* 2134 */       DataInputStream dis = new DataInputStream(bais);
/* 2135 */       byte[] structid = new byte[4];
/* 2136 */       int read = dis.read(structid, 0, 4);
/*      */       
/* 2138 */       if (read != 4) {
/* 2139 */         IncorrectHeaderException ex = new IncorrectHeaderException(AmsErrorMessages.mju_ambi_header_invalid);
/* 2140 */         if (Trace.isOn) {
/* 2141 */           Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "readAmbiHeader(MessageBufferProcessor,int)", (Throwable)ex, 2);
/*      */         }
/*      */         
/* 2144 */         throw ex;
/*      */       } 
/*      */       
/* 2147 */       if (!AMBIHeader.verifyHeaderID(structid)) {
/* 2148 */         if (tolerate == 1) {
/* 2149 */           if (Trace.isOn) {
/* 2150 */             Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "readAmbiHeader(MessageBufferProcessor,int)", null, 2);
/*      */           }
/*      */           
/* 2153 */           return null;
/*      */         } 
/* 2155 */         IncorrectHeaderException ex = new IncorrectHeaderException(AmsErrorMessages.mju_ambi_header_invalid);
/* 2156 */         if (Trace.isOn) {
/* 2157 */           Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "readAmbiHeader(MessageBufferProcessor,int)", (Throwable)ex, 3);
/*      */         }
/*      */         
/* 2160 */         throw ex;
/*      */       } 
/* 2162 */       if (!mbproc.getPayloadFormat().equals("        ")) {
/* 2163 */         if (tolerate == 1) {
/* 2164 */           if (Trace.isOn) {
/* 2165 */             Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "readAmbiHeader(MessageBufferProcessor,int)", null, 3);
/*      */           }
/*      */           
/* 2168 */           return null;
/*      */         } 
/* 2170 */         if (Trace.isOn) {
/* 2171 */           Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "readAmbiHeader(MessageBufferProcessor, int", "wrong message format", "");
/*      */         }
/*      */ 
/*      */         
/* 2175 */         IncorrectHeaderException ex = new IncorrectHeaderException(AmsErrorMessages.mqm_s_get_ivmqhdr_invalid);
/* 2176 */         ex.setReason(2110);
/* 2177 */         if (Trace.isOn) {
/* 2178 */           Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "readAmbiHeader(MessageBufferProcessor,int)", (Throwable)ex, 4);
/*      */         }
/*      */         
/* 2181 */         throw ex;
/*      */       } 
/* 2183 */       hdr = new AMBIHeader(hdrData);
/* 2184 */       this.ccsidConverter.convertHeader(hdr);
/*      */     }
/* 2186 */     catch (Exception e) {
/* 2187 */       if (Trace.isOn) {
/* 2188 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "readAmbiHeader(MessageBufferProcessor,int)", e);
/*      */       }
/*      */       
/* 2191 */       IncorrectHeaderException ex = new IncorrectHeaderException(AmsErrorMessages.mqm_s_get_ivmqhdr_invalid, e);
/* 2192 */       if (Trace.isOn) {
/* 2193 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "readAmbiHeader(MessageBufferProcessor,int)", (Throwable)ex, 5);
/*      */       }
/*      */       
/* 2196 */       throw ex;
/*      */     } 
/*      */     
/* 2199 */     if (Trace.isOn) {
/* 2200 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "readAmbiHeader(MessageBufferProcessor,int)", hdr, 4);
/*      */     }
/*      */     
/* 2203 */     return hdr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MessageUnprotectInfo unprotect(EseUser user, SecurityPolicy policy, AMBIHeader hdr, SmqiObject smqiObject, MessageBufferProcessor mbproc) throws UnprotectException {
/* 2218 */     if (Trace.isOn) {
/* 2219 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "unprotect(EseUser,SecurityPolicy,MessageBufferProcessor)", new Object[] { user, policy, mbproc });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2224 */     MessageUnprotectInfo info = null;
/*      */     try {
/* 2226 */       byte[] protectedData = getDataToUnprotect(mbproc, hdr, policy.getQop());
/* 2227 */       info = this.cryptoService.unprotect(protectedData, policy, hdr, smqiObject, user);
/*      */     
/*      */     }
/* 2230 */     catch (AMBIException e) {
/* 2231 */       if (Trace.isOn) {
/* 2232 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "unprotect(EseUser,SecurityPolicy,MessageBufferProcessor)", (Throwable)e);
/*      */       }
/*      */       
/* 2235 */       AMBIException ex = new AMBIException(AmsErrorMessages.mjm_msg_error_unprotect, (Throwable)e);
/* 2236 */       UnprotectException traceRet1 = new UnprotectException(ex);
/* 2237 */       if (Trace.isOn) {
/* 2238 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "unprotect(EseUser,SecurityPolicy,MessageBufferProcessor)", traceRet1);
/*      */       }
/*      */       
/* 2241 */       throw traceRet1;
/*      */     } 
/*      */     
/* 2244 */     if (Trace.isOn) {
/* 2245 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "unprotect(EseUser,SecurityPolicy,MessageBufferProcessor)", info);
/*      */     }
/*      */     
/* 2248 */     return info;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] getDataToUnprotect(MessageBufferProcessor mbproc, AMBIHeader hdr, int qop) throws IncorrectHeaderException {
/* 2262 */     if (Trace.isOn) {
/* 2263 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getDataToUnprotect(MessageBufferProcessor, AMBIHeader, qop)", new Object[] { mbproc, hdr, 
/* 2264 */             Integer.valueOf(qop) });
/*      */     }
/*      */     
/* 2267 */     byte[] msg = mbproc.getEntireBuffer().array();
/*      */     
/* 2269 */     int length = mbproc.getPayloadLength();
/*      */     
/* 2271 */     int pdmqHeaderOffset = mbproc.getPayloadPos();
/*      */     
/* 2273 */     int dataOffset = pdmqHeaderOffset + hdr.getDataOffset();
/* 2274 */     if (dataOffset > msg.length) {
/* 2275 */       if (Trace.isOn) {
/* 2276 */         StringBuilder sb = new StringBuilder("dataOffset:");
/* 2277 */         sb.append(dataOffset).append("; msg.length:")
/* 2278 */           .append(msg.length);
/* 2279 */         sb.append("; length:").append(length);
/*      */         
/* 2281 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getDataToUnprotect(MessageBufferProcessor, AMBIHeader, qo)", sb
/*      */             
/* 2283 */             .toString(), "");
/*      */       } 
/* 2285 */       IncorrectHeaderException ex = new IncorrectHeaderException(AmsErrorMessages.mqm_s_get_ivmqhdr_invalid);
/* 2286 */       if (Trace.isOn) {
/* 2287 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getDataToUnprotect(MessageBufferProcessor, AMBIHeader, qo)", (Throwable)ex, 1);
/*      */       }
/*      */       
/* 2290 */       throw ex;
/*      */     } 
/*      */     
/* 2293 */     byte[] msgData = new byte[length - hdr.getDataOffset()];
/* 2294 */     if (dataOffset + msgData.length > msg.length) {
/* 2295 */       if (Trace.isOn) {
/* 2296 */         StringBuilder sb = new StringBuilder("dataOffset:");
/* 2297 */         sb.append(dataOffset).append("; msg.length:")
/* 2298 */           .append(msg.length);
/* 2299 */         sb.append("; msgData.length").append(msgData.length);
/*      */         
/* 2301 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getDataToUnprotect(MessageBufferProcessor, AMBIHeader, qo)", sb
/*      */             
/* 2303 */             .toString(), "");
/*      */       } 
/*      */       
/* 2306 */       IncorrectHeaderException ex = new IncorrectHeaderException(AmsErrorMessages.mqm_s_get_ivmqhdr_invalid);
/* 2307 */       if (Trace.isOn) {
/* 2308 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getDataToUnprotect(MessageBufferProcessor, AMBIHeader, qo)", (Throwable)ex, 2);
/*      */       }
/*      */       
/* 2311 */       throw ex;
/*      */     } 
/* 2313 */     System.arraycopy(msg, dataOffset, msgData, 0, msgData.length);
/*      */     
/* 2315 */     if (Trace.isOn) {
/* 2316 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "getDataToUnprotect(MessageBufferProcessor, AMBIHeader, qo)", msgData);
/*      */     }
/*      */     
/* 2319 */     return msgData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void validateQop(AMBIHeader hdr, SecurityPolicy policy) throws UnprotectException {
/* 2331 */     if (Trace.isOn) {
/* 2332 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateQop(AMBIHeader,SecurityPolicy)", new Object[] { hdr, policy });
/*      */     }
/*      */ 
/*      */     
/* 2336 */     int policyQop = policy.getQop();
/* 2337 */     int msgQop = hdr.getQop();
/*      */     
/* 2339 */     if (msgQop < policyQop) {
/* 2340 */       if (Trace.isOn) {
/* 2341 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateQop(AMBIHeader, SecurityPolicy)", "qop required is  " + policyQop + ", message qop is " + msgQop, "");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2346 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2347 */       inserts.put("AMS_INSERT_QUALITY_OF_PROTECTION", Integer.valueOf(hdr.getProtType()));
/* 2348 */       inserts.put("AMS_INSERT_EXPECTED_QUALITY_OF_PROTECTION", Integer.valueOf(policyQop));
/* 2349 */       inserts.put("AMS_INSERT_Q_NAME", policy.getName());
/* 2350 */       QopMismatchException ex = new QopMismatchException(AmsErrorMessages.mqm_s_get_qop_mismatch, inserts);
/* 2351 */       UnprotectException traceRet1 = new UnprotectException(new AMBIException((Throwable)ex));
/* 2352 */       if (Trace.isOn) {
/* 2353 */         Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateQop(AMBIHeader,SecurityPolicy)", traceRet1);
/*      */       }
/*      */       
/* 2356 */       throw traceRet1;
/*      */     } 
/*      */     
/* 2359 */     if (Trace.isOn) {
/* 2360 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "validateQop(AMBIHeader,SecurityPolicy)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void handleGetError(MQGetParameters parms, MQGetContext getContext, int dlhReason, MsgStatus msgStatus) {
/* 2376 */     if (Trace.isOn) {
/* 2377 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "handleGetError(MQGetParameters,MQGetContext,int,MsgStatus)", new Object[] { parms, getContext, 
/* 2378 */             Integer.valueOf(dlhReason), msgStatus });
/*      */     }
/*      */     
/* 2381 */     String meth = "handleGetError";
/* 2382 */     if (isBrowse(parms.getMsgOpts)) {
/* 2383 */       if (Trace.isOn) {
/* 2384 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "handleGetError(MQGetParameters, MQGetContext, int, MsgStatus)", "BROWSE, so do not put the message to error handling queue", "");
/*      */       }
/*      */ 
/*      */       
/* 2388 */       if (Trace.isOn) {
/* 2389 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "handleGetError(MQGetParameters,MQGetContext,int,MsgStatus)");
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 2394 */     int ccOrig = parms.cc.x;
/* 2395 */     int rcOrig = parms.rc.x;
/*      */     try {
/* 2397 */       SmqiObject smqiObject = getContext.getSmqiObject();
/* 2398 */       String errorq = smqiObject.getSecPolicy().getErrorQName();
/* 2399 */       String qmgr = smqiObject.getContext().getQmgrName();
/* 2400 */       String queue = smqiObject.getResolvedName();
/*      */       
/* 2402 */       boolean inSyncpoint = false;
/* 2403 */       if (isOptionSet(parms.getMsgOpts.getOptions(), 2) || (isOptionSet(parms.getMsgOpts.getOptions(), 4096) && parms.msgDesc
/*      */         
/* 2405 */         .getPersistence() == 1)) {
/* 2406 */         inSyncpoint = true;
/*      */       }
/* 2408 */       if (!msgStatus.isRemoved) {
/* 2409 */         if (isOptionSet(parms.getMsgOpts.getOptions(), 256)) {
/*      */           
/* 2411 */           if (removeMsgUnderCursor(parms, getContext)) {
/* 2412 */             msgStatus.isRemoved = true;
/*      */           
/*      */           }
/*      */         }
/* 2416 */         else if (getContext.getMsgToken() != null) {
/* 2417 */           if (removeMsgByMsgToken(parms, getContext)) {
/* 2418 */             msgStatus.isRemoved = true;
/*      */           }
/*      */         } else {
/*      */           
/* 2422 */           HashMap<String, Exception> data = new HashMap<>();
/* 2423 */           Trace.ffst(CLASS, meth, "MP004003", data, null);
/* 2424 */           HashMap<String, Object> inserts = new HashMap<>();
/* 2425 */           inserts.put("AMS_INSERT_Q_NAME", queue);
/* 2426 */           inserts.put("AMS_INSERT_MQ_COMPLETION_CODE", Integer.valueOf(2).toString());
/* 2427 */           inserts.put("AMS_INSERT_MQ_REASON_CODE", Integer.valueOf(2331).toString());
/* 2428 */           EseMQException ex = new EseMQException(AmsErrorMessages.mqm_s_get_dlq_failed_mqget, inserts);
/* 2429 */           ex.setReason(rcOrig);
/* 2430 */           if (Trace.isOn) {
/* 2431 */             Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "handleGetError(MQGetParameters,MQGetContext,int,MsgStatus)", (Throwable)ex, 1);
/*      */           }
/*      */           
/* 2434 */           throw ex;
/*      */         } 
/*      */       }
/*      */       
/* 2438 */       if (!msgStatus.isRemoved) {
/*      */ 
/*      */         
/* 2441 */         HashMap<String, Exception> data = new HashMap<>();
/* 2442 */         Trace.ffst(CLASS, meth, "MP004004", data, null);
/* 2443 */         HashMap<String, Object> inserts = new HashMap<>();
/* 2444 */         inserts.put("AMS_INSERT_Q_NAME", queue);
/* 2445 */         inserts.put("AMS_INSERT_MQ_COMPLETION_CODE", Integer.valueOf(parms.cc.x).toString());
/* 2446 */         inserts.put("AMS_INSERT_MQ_REASON_CODE", Integer.valueOf(parms.rc.x).toString());
/* 2447 */         EseMQException ex = new EseMQException(AmsErrorMessages.mqm_s_get_dlq_failed_mqget, inserts);
/* 2448 */         ex.setReason(rcOrig);
/* 2449 */         if (Trace.isOn) {
/* 2450 */           Trace.throwing(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "handleGetError(MQGetParameters,MQGetContext,int,MsgStatus)", (Throwable)ex, 2);
/*      */         }
/*      */         
/* 2453 */         throw ex;
/*      */       } 
/*      */       
/* 2456 */       String qmgrDlqName = this.contextContainer.getContext(parms.hconn).getQmgrDlqName();
/* 2457 */       this.mqService.putInErrorq(parms.hconn, errorq, qmgrDlqName, qmgr, queue, parms.msgDesc, inSyncpoint, parms.newBuffer, dlhReason);
/*      */ 
/*      */ 
/*      */       
/* 2461 */       parms.dataLength.x = 0;
/* 2462 */       if (parms.getMsgOpts.getVersion() >= 3) {
/* 2463 */         parms.getMsgOpts.setReturnedLength(0);
/*      */       }
/* 2465 */       parms.msgDesc.setFormat("MQSTR   ");
/*      */     }
/* 2467 */     catch (EseMQException e) {
/* 2468 */       if (Trace.isOn) {
/* 2469 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "handleGetError(MQGetParameters,MQGetContext,int,MsgStatus)", (Throwable)e, 1);
/*      */       }
/*      */       
/* 2472 */       HashMap<String, Exception> data = new HashMap<>();
/* 2473 */       Trace.ffst(CLASS, meth, "MP004005", data, null);
/* 2474 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2475 */       inserts.put("AMS_INSERT_MQ_REASON_CODE", Integer.valueOf(e.getReason()).toString());
/* 2476 */       AmsErrorMessages.log(CLASS, meth, AmsErrorMessages.mqm_s_get_error_q_failed, inserts);
/* 2477 */       AmsErrorMessages.logException(CLASS, meth, (Throwable)e);
/* 2478 */       if (Trace.isOn) {
/* 2479 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "handleGetError(MQGetParameters, MQGetContext, int, MsgStatus)", (Throwable)e);
/*      */       }
/*      */       
/* 2482 */       if (e.getCause() instanceof JmqiException) {
/* 2483 */         JmqiException jmqiException = (JmqiException)e.getCause();
/* 2484 */         JmqiTls tls = getTls();
/* 2485 */         tls.lastException = jmqiException;
/*      */       }
/*      */     
/* 2488 */     } catch (Exception e) {
/* 2489 */       if (Trace.isOn) {
/* 2490 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "handleGetError(MQGetParameters,MQGetContext,int,MsgStatus)", e, 2);
/*      */       }
/*      */       
/* 2493 */       HashMap<String, Exception> data = new HashMap<>();
/* 2494 */       Trace.ffst(CLASS, meth, "MP004006", data, null);
/* 2495 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2496 */       inserts.put("AMS_INSERT_MQ_REASON_CODE", Integer.valueOf(2195).toString());
/* 2497 */       AmsErrorMessages.log(CLASS, meth, AmsErrorMessages.mqm_s_get_error_q_failed, inserts);
/* 2498 */       AmsErrorMessages.logException(CLASS, meth, e);
/* 2499 */       if (Trace.isOn) {
/* 2500 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "handleGetError(MQGetParameters, MQGetContext, int, MsgStatus)", e);
/*      */       }
/*      */     }
/*      */     finally {
/*      */       
/* 2505 */       if (Trace.isOn) {
/* 2506 */         Trace.finallyBlock(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "handleGetError(MQGetParameters,MQGetContext,int,MsgStatus)");
/*      */       }
/*      */       
/* 2509 */       parms.cc.x = ccOrig;
/* 2510 */       parms.rc.x = rcOrig;
/*      */     } 
/*      */     
/* 2513 */     if (Trace.isOn) {
/* 2514 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "handleGetError(MQGetParameters,MQGetContext,int,MsgStatus)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isBrowse(MQGMO mqgmo) {
/* 2526 */     if (Trace.isOn) {
/* 2527 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "isBrowse(MQGMO)", new Object[] { mqgmo });
/*      */     }
/*      */ 
/*      */     
/* 2531 */     boolean traceRet1 = (mqgmo != null && (isOptionSet(mqgmo.getOptions(), 16) || isOptionSet(mqgmo.getOptions(), 32) || isOptionSet(mqgmo
/* 2532 */         .getOptions(), 2048)));
/* 2533 */     if (Trace.isOn) {
/* 2534 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "isBrowse(MQGMO)", 
/* 2535 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 2537 */     return traceRet1;
/*      */   }
/*      */   
/*      */   private void copyMQMD(MQMD dest, MQMD src) {
/* 2541 */     if (Trace.isOn) {
/* 2542 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "copyMQMD(MQMD,final MQMD)", new Object[] { dest, src });
/*      */     }
/*      */ 
/*      */     
/* 2546 */     dest.setReport(src.getReport());
/* 2547 */     dest.setMsgType(src.getMsgType());
/* 2548 */     dest.setExpiry(src.getExpiry());
/* 2549 */     dest.setFeedback(src.getFeedback());
/* 2550 */     dest.setEncoding(src.getEncoding());
/* 2551 */     dest.setCodedCharSetId(src.getCodedCharSetId());
/* 2552 */     dest.setFormat(src.getFormat());
/* 2553 */     dest.setPriority(src.getPriority());
/* 2554 */     dest.setPersistence(src.getPersistence());
/* 2555 */     dest.setMsgId(src.getMsgId());
/* 2556 */     dest.setCorrelId(src.getCorrelId());
/* 2557 */     dest.setBackoutCount(src.getBackoutCount());
/* 2558 */     dest.setReplyToQ(src.getReplyToQ());
/* 2559 */     dest.setReplyToQMgr(src.getReplyToQMgr());
/* 2560 */     dest.setUserIdentifier(src.getUserIdentifier());
/* 2561 */     dest.setAccountingToken(src.getAccountingToken());
/* 2562 */     dest.setApplIdentityData(src.getApplIdentityData());
/* 2563 */     dest.setPutApplType(src.getPutApplType());
/* 2564 */     dest.setPutApplName(src.getPutApplName());
/* 2565 */     dest.setPutDate(src.getPutDate());
/* 2566 */     dest.setPutTime(src.getPutTime());
/* 2567 */     dest.setApplOriginData(src.getApplOriginData());
/*      */     
/* 2569 */     if (src.getVersion() >= 2 && dest.getVersion() >= 2) {
/* 2570 */       dest.setGroupId(src.getGroupId());
/* 2571 */       dest.setMsgSeqNumber(src.getMsgSeqNumber());
/* 2572 */       dest.setOffset(src.getOffset());
/* 2573 */       dest.setMsgFlags(src.getMsgFlags());
/* 2574 */       dest.setOriginalLength(src.getOriginalLength());
/*      */     } 
/* 2576 */     if (Trace.isOn) {
/* 2577 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiGetInterceptorImpl", "copyMQMD(MQMD,final MQMD)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final class MQGetParameters
/*      */   {
/*      */     public ByteBuffer newBuffer;
/*      */     
/*      */     public Hconn hconn;
/*      */     
/*      */     public Hobj hobj;
/*      */     
/*      */     public MQMD msgDesc;
/*      */     
/*      */     public MQGMO getMsgOpts;
/*      */     
/*      */     public int maxMsgLength;
/*      */     
/*      */     public Pint dataLength;
/*      */     
/*      */     public ByteBuffer buffer;
/*      */     
/*      */     public Pint cc;
/*      */     
/*      */     public Pint rc;
/*      */     
/*      */     public int ccOrig;
/*      */     
/*      */     public int rcOrig;
/*      */ 
/*      */     
/*      */     public MQGetParameters(Hconn hconn, Hobj hobj, MQMD mqmd, MQGMO gmo, int maxMsgLength, ByteBuffer buffer, Pint dataLength, Pint cc, Pint rc) {
/* 2611 */       if (Trace.isOn) {
/* 2612 */         Trace.entry(this, "com.ibm.mq.ese.intercept.MQGetParameters", "<init>(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, mqmd, gmo, 
/*      */               
/* 2614 */               Integer.valueOf(maxMsgLength), buffer, dataLength, cc, rc });
/*      */       }
/* 2616 */       this.hconn = hconn;
/* 2617 */       this.hobj = hobj;
/* 2618 */       this.msgDesc = mqmd;
/* 2619 */       this.getMsgOpts = gmo;
/* 2620 */       this.maxMsgLength = maxMsgLength;
/* 2621 */       this.buffer = buffer;
/* 2622 */       this.dataLength = dataLength;
/* 2623 */       this.cc = cc;
/* 2624 */       this.rc = rc;
/* 2625 */       this.ccOrig = cc.x;
/* 2626 */       this.rcOrig = rc.x;
/* 2627 */       this.newBuffer = buffer;
/* 2628 */       if (Trace.isOn) {
/* 2629 */         Trace.exit(this, "com.ibm.mq.ese.intercept.MQGetParameters", "<init>(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getMaxMsgLength() {
/* 2638 */       int traceRet1 = (this.maxMsgLength < this.buffer.capacity()) ? this.maxMsgLength : this.buffer.capacity();
/* 2639 */       if (Trace.isOn) {
/* 2640 */         Trace.data(this, "com.ibm.mq.ese.intercept.MQGetParameters", "getMaxMsgLength()", "getter", 
/* 2641 */             Integer.valueOf(traceRet1));
/*      */       }
/* 2643 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/* 2648 */       if (Trace.isOn) {
/* 2649 */         Trace.entry(this, "com.ibm.mq.ese.intercept.MQGetParameters", "toString()");
/*      */       }
/* 2651 */       StringBuilder sb = new StringBuilder();
/* 2652 */       sb.append("hconn:").append(this.hconn);
/* 2653 */       sb.append(", hobj:").append(this.hobj);
/* 2654 */       sb.append(", mqmd:").append(this.msgDesc);
/* 2655 */       sb.append(", mqgmo:").append(this.getMsgOpts);
/* 2656 */       sb.append(", maxMsgLength:").append(this.maxMsgLength);
/* 2657 */       sb.append(", dataLength:").append(this.dataLength);
/* 2658 */       sb.append(", cc:").append(this.cc);
/* 2659 */       sb.append(", rc:").append(this.rc);
/* 2660 */       String traceRet1 = sb.toString();
/* 2661 */       if (Trace.isOn) {
/* 2662 */         Trace.exit(this, "com.ibm.mq.ese.intercept.MQGetParameters", "toString()", traceRet1);
/*      */       }
/* 2664 */       return traceRet1;
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class MsgStatus {
/*      */     public boolean isComplete = false;
/*      */     public boolean isRemoved = false;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\JmqiGetInterceptorImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */