/*     */ package com.ibm.mq.ese.intercept;
/*     */ 
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.mq.ese.service.EseMQException;
/*     */ import com.ibm.mq.jmqi.MQCBD;
/*     */ import com.ibm.mq.jmqi.MQConsumer;
/*     */ import com.ibm.mq.jmqi.MQGMO;
/*     */ import com.ibm.mq.jmqi.MQMD;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*     */ public class JmqiCBInterceptorImpl
/*     */   extends AbstractJmqiInterceptor
/*     */   implements JmqiCBInterceptor
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiCBInterceptorImpl.java";
/*     */   
/*     */   static {
/*  48 */     if (Trace.isOn) {
/*  49 */       Trace.data("com.ibm.mq.ese.intercept.JmqiCBInterceptorImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiCBInterceptorImpl.java");
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
/*  63 */   private static final String CLASS = JmqiCBInterceptorImpl.class.getName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JmqiGetInterceptor getInterceptor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void beforeMQCB(Hconn hconn, int operation, MQCBD callbackDesc, Hobj hobj, MQMD msgDesc, MQGMO getMsgOpts, Pint cc, Pint rc) {
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiCBInterceptorImpl", "beforeMQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)", new Object[] { hconn, 
/*     */             
/*  86 */             Integer.valueOf(operation), callbackDesc, hobj, msgDesc, getMsgOpts, cc, rc });
/*     */     }
/*     */     
/*  89 */     String meth = "beforeMQCB";
/*     */     try {
/*  91 */       if (!validate(operation, callbackDesc) || callbackDesc
/*  92 */         .inhibitESE()) {
/*  93 */         if (Trace.isOn) {
/*  94 */           Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiCBInterceptorImpl", "beforeMQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)");
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*  99 */       MQConsumer mqConsumer = callbackDesc.getCallbackFunction();
/* 100 */       SmqiObject smqiObject = getSmqiObject(hconn, hobj, meth);
/* 101 */       MQGMO gmoCopy = this.mqService.copyGetMsgOpts(this.env.newMQGMO(), getMsgOpts);
/*     */ 
/*     */       
/* 104 */       ConsumerProxy eseConsumer = new ConsumerProxy(mqConsumer, callbackDesc.getMaxMsgLength(), smqiObject, msgDesc, gmoCopy, this.env);
/*     */       
/* 106 */       eseConsumer.setGetInterceptor(this.getInterceptor);
/* 107 */       callbackDesc.setCallbackFunction(eseConsumer);
/*     */       
/* 109 */       if (smqiObject.getSecPolicy().getQop() != 0) {
/* 110 */         int opts = getMsgOpts.getOptions();
/* 111 */         opts &= 0xFFFFFFBF;
/* 112 */         getMsgOpts.setOptions(opts);
/*     */       }
/*     */     
/*     */     }
/* 116 */     catch (EseMQException e) {
/* 117 */       if (Trace.isOn) {
/* 118 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiCBInterceptorImpl", "beforeMQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)", (Throwable)e);
/*     */       }
/*     */       
/* 121 */       HashMap<String, Exception> data = new HashMap<>();
/* 122 */       data.put("exception", e);
/* 123 */       Trace.ffst(CLASS, meth, "MP001001", data, null);
/* 124 */       setErrorReasonCode(cc, rc, e.getReason());
/* 125 */       AmsErrorMessages.logException(CLASS, meth, (Throwable)e);
/* 126 */       if (Trace.isOn) {
/* 127 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiCBInterceptorImpl", "beforeMQCB(Hconn, int, MQCBD, Hobj, MQMD, MQGMO, Pint, Pint)", (Throwable)e);
/*     */       }
/*     */       
/* 130 */       setErrorReasonCode(cc, rc, e.getReason());
/*     */     } 
/*     */     
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiCBInterceptorImpl", "beforeMQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)");
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
/*     */   private boolean validate(int operation, MQCBD callbackDesc) {
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiCBInterceptorImpl", "validate(int,MQCBD)", new Object[] {
/* 149 */             Integer.valueOf(operation), callbackDesc
/*     */           });
/*     */     }
/* 152 */     boolean ret = true;
/*     */     
/* 154 */     if (!isOptionSet(operation, 256)) {
/* 155 */       if (Trace.isOn) {
/* 156 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiCBInterceptorImpl", "validate(int, MQCBD)", "not MQOP_REGISTER, skipping", "");
/*     */       }
/*     */       
/* 159 */       ret = false;
/*     */     } 
/*     */ 
/*     */     
/* 163 */     if (!isOptionSet(callbackDesc.getCallbackType(), 1)) {
/*     */       
/* 165 */       if (Trace.isOn) {
/* 166 */         Trace.traceInfo(this, "com.ibm.mq.ese.intercept.JmqiCBInterceptorImpl", "validate(int, MQCBD)", "not MQCBT_MESSAGE_CONSUMER, skipping", "");
/*     */       }
/*     */       
/* 169 */       ret = false;
/*     */     } 
/*     */     
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiCBInterceptorImpl", "validate(int,MQCBD)", 
/* 174 */           Boolean.valueOf(ret));
/*     */     }
/* 176 */     return ret;
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
/*     */   public void afterMQCB(Hconn hconn, int operation, MQCBD callbackDesc, Hobj hobj, MQMD msgDesc, MQGMO getMsgOpts, Pint cc, Pint rc) throws EseMQException {
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiCBInterceptorImpl", "afterMQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)", new Object[] { hconn, 
/*     */             
/* 192 */             Integer.valueOf(operation), callbackDesc, hobj, msgDesc, getMsgOpts, cc, rc });
/*     */     }
/*     */     
/* 195 */     if (!validate(operation, callbackDesc)) {
/* 196 */       if (Trace.isOn) {
/* 197 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiCBInterceptorImpl", "afterMQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 203 */     MQConsumer consumer = callbackDesc.getCallbackFunction();
/* 204 */     if (consumer instanceof ConsumerProxy) {
/* 205 */       ConsumerProxy eseConsumer = (ConsumerProxy)consumer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 212 */       if (isOptionSet(eseConsumer.getGetMsgOpts().getOptions(), 64)) {
/*     */         
/* 214 */         int opts = getMsgOpts.getOptions();
/* 215 */         opts |= 0x40;
/* 216 */         getMsgOpts.setOptions(opts);
/*     */       } 
/*     */     } 
/*     */     
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiCBInterceptorImpl", "afterMQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJmqiGetInterceptor(JmqiGetInterceptor getInterceptor) {
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.data(this, "com.ibm.mq.ese.intercept.JmqiCBInterceptorImpl", "setJmqiGetInterceptor(JmqiGetInterceptor)", "setter", getInterceptor);
/*     */     }
/*     */     
/* 232 */     this.getInterceptor = getInterceptor;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\JmqiCBInterceptorImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */