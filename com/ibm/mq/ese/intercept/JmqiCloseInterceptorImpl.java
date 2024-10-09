/*    */ package com.ibm.mq.ese.intercept;
/*    */ 
/*    */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*    */ import com.ibm.mq.ese.service.EseMQException;
/*    */ import com.ibm.mq.jmqi.handles.Hconn;
/*    */ import com.ibm.mq.jmqi.handles.Hobj;
/*    */ import com.ibm.mq.jmqi.handles.Phobj;
/*    */ import com.ibm.mq.jmqi.handles.Pint;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JmqiCloseInterceptorImpl
/*    */   extends AbstractJmqiInterceptor
/*    */   implements JmqiCloseInterceptor
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiCloseInterceptorImpl.java";
/*    */   
/*    */   static {
/* 43 */     if (Trace.isOn) {
/* 44 */       Trace.data("com.ibm.mq.ese.intercept.JmqiCloseInterceptorImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiCloseInterceptorImpl.java");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 55 */   private static final String CLASS = JmqiCloseInterceptorImpl.class
/* 56 */     .getName();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void afterMQCLOSE(Hobj closingHobj, Hconn hconn, Phobj phobj, int options, Pint cc, Pint rc) {
/* 65 */     if (Trace.isOn) {
/* 66 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiCloseInterceptorImpl", "afterMQCLOSE(Hobj,Hconn,Phobj,int,Pint,Pint)", new Object[] { closingHobj, hconn, phobj, 
/*    */             
/* 68 */             Integer.valueOf(options), cc, rc });
/*    */     }
/*    */     
/* 71 */     String meth = "afterMQCLOSE";
/*    */     
/*    */     try {
/* 74 */       ConnectionContext context = getContext(hconn);
/* 75 */       context.free(closingHobj);
/* 76 */       String qmgrName = context.getQmgrName();
/* 77 */       this.contextContainer.removeTempQinfo(closingHobj, qmgrName);
/*    */     }
/* 79 */     catch (EseMQException e) {
/* 80 */       if (Trace.isOn) {
/* 81 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiCloseInterceptorImpl", "afterMQCLOSE(Hobj,Hconn,Phobj,int,Pint,Pint)", (Throwable)e);
/*    */       }
/*    */       
/* 84 */       HashMap<String, Exception> data = new HashMap<>();
/* 85 */       data.put("exception", e);
/* 86 */       Trace.ffst(CLASS, meth, "MP002001", data, null);
/* 87 */       AmsErrorMessages.logException(CLASS, meth, (Throwable)e);
/* 88 */       if (Trace.isOn) {
/* 89 */         Trace.catchBlock(this, "com.ibm.mq.ese.intercept.JmqiCloseInterceptorImpl", "afterMQCLOSE(Hobj, Hconn, Phobj, int, Pint, Pint)", (Throwable)e);
/*    */       }
/*    */       
/* 92 */       setErrorReasonCode(cc, rc, e.getReason());
/*    */     } 
/*    */     
/* 95 */     if (Trace.isOn)
/* 96 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiCloseInterceptorImpl", "afterMQCLOSE(Hobj,Hconn,Phobj,int,Pint,Pint)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\JmqiCloseInterceptorImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */