/*     */ package com.ibm.mq.ese.intercept;
/*     */ 
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.mq.ese.service.EseMQException;
/*     */ import com.ibm.mq.jmqi.MQSD;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.handles.Phobj;
/*     */ import com.ibm.mq.jmqi.handles.Pint;
/*     */ import com.ibm.mq.jmqi.system.LpiSD;
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
/*     */ public class JmqiSubscribeInterceptorImpl
/*     */   extends AbstractJmqiInterceptor
/*     */   implements JmqiSubscribeInterceptor
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiSubscribeInterceptorImpl.java";
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.mq.ese.intercept.JmqiSubscribeInterceptorImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiSubscribeInterceptorImpl.java");
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
/*  58 */   private static final String CLASS = JmqiSubscribeInterceptorImpl.class
/*  59 */     .getName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void afterSpiSubscribe(Hconn hconn, LpiSD plpiSD, MQSD subDesc, Phobj phobj, Phobj phsub, Pint cc, Pint rc) {
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiSubscribeInterceptorImpl", "afterSpiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", new Object[] { hconn, plpiSD, subDesc, phobj, phsub, cc, rc });
/*     */     }
/*     */ 
/*     */     
/*  77 */     afterSubscribe(hconn, subDesc, phobj, phsub, cc, rc);
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiSubscribeInterceptorImpl", "afterSpiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)");
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
/*     */   public void afterMQSUB(Hconn hconn, MQSD subDesc, Phobj phobj, Phobj phsub, Pint cc, Pint rc) {
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiSubscribeInterceptorImpl", "afterMQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", new Object[] { hconn, subDesc, phobj, phsub, cc, rc });
/*     */     }
/*     */ 
/*     */     
/* 100 */     afterSubscribe(hconn, subDesc, phobj, phsub, cc, rc);
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiSubscribeInterceptorImpl", "afterMQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)");
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
/*     */   private void afterSubscribe(Hconn hconn, MQSD subDesc, Phobj phobj, Phobj phsub, Pint cc, Pint rc) {
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiSubscribeInterceptorImpl", "afterSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", new Object[] { hconn, subDesc, phobj, phsub, cc, rc });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 126 */     String meth = "afterSubscribe";
/* 127 */     ConnectionContext context = null;
/*     */     try {
/* 129 */       context = getContext(hconn);
/*     */     }
/* 131 */     catch (EseMQException e) {
/* 132 */       if (Trace.isOn) {
/* 133 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiSubscribeInterceptorImpl", "afterSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", (Throwable)e);
/*     */       }
/*     */       
/* 136 */       HashMap<String, Exception> data = new HashMap<>();
/* 137 */       data.put("exception", e);
/* 138 */       Trace.ffst(CLASS, meth, "MP007001", data, null);
/* 139 */       AmsErrorMessages.logException(CLASS, meth, (Throwable)e);
/* 140 */       if (Trace.isOn) {
/* 141 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiSubscribeInterceptorImpl", "afterSubscribe(Hconn, MQSD, Phobj, Phobj, Pint, Pint)", (Throwable)e);
/*     */       }
/*     */       
/* 144 */       setErrorReasonCode(cc, rc, e.getReason());
/* 145 */       if (Trace.isOn) {
/* 146 */         Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiSubscribeInterceptorImpl", "afterSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 151 */     SmqiObject eseObject = new SmqiObject(phobj.getHobj(), context);
/* 152 */     context.addSmqiObject(eseObject);
/* 153 */     eseObject.setMqObjectType(8);
/* 154 */     eseObject.setSecPolicy(this.policyService.createNonePolicy(""));
/*     */     
/* 156 */     SmqiObject eseObject2 = new SmqiObject(phsub.getHobj(), context);
/* 157 */     context.addSmqiObject(eseObject2);
/* 158 */     eseObject2.setMqObjectType(8);
/* 159 */     eseObject2.setSecPolicy(this.policyService.createNonePolicy(""));
/*     */     
/* 161 */     if (Trace.isOn)
/* 162 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiSubscribeInterceptorImpl", "afterSubscribe(Hconn,MQSD,Phobj,Phobj,Pint,Pint)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\JmqiSubscribeInterceptorImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */