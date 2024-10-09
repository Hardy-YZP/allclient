/*    */ package com.ibm.mq.ese.intercept;
/*    */ 
/*    */ import com.ibm.mq.jmqi.handles.Hconn;
/*    */ import com.ibm.mq.jmqi.handles.Phconn;
/*    */ import com.ibm.mq.jmqi.handles.Pint;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*    */ public class JmqiDiscInterceptorImpl
/*    */   extends AbstractJmqiInterceptor
/*    */   implements JmqiDiscInterceptor
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiDiscInterceptorImpl.java";
/*    */   
/*    */   static {
/* 39 */     if (Trace.isOn) {
/* 40 */       Trace.data("com.ibm.mq.ese.intercept.JmqiDiscInterceptorImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiDiscInterceptorImpl.java");
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
/*    */   
/*    */   public void beforeMQDISC(Phconn phconn, Pint cc, Pint rc) {
/* 53 */     if (Trace.isOn) {
/* 54 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiDiscInterceptorImpl", "beforeMQDISC(Phconn,Pint,Pint)", new Object[] { phconn, cc, rc });
/*    */     }
/*    */     
/* 57 */     if (Trace.isOn) {
/* 58 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiDiscInterceptorImpl", "beforeMQDISC(Phconn,Pint,Pint)");
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
/*    */   public void afterMQDISC(Hconn disconnectedHconn, Phconn phconn, Pint cc, Pint rc) {
/* 70 */     if (Trace.isOn) {
/* 71 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiDiscInterceptorImpl", "afterMQDISC(Hconn,Phconn,Pint,Pint)", new Object[] { disconnectedHconn, phconn, cc, rc });
/*    */     }
/*    */ 
/*    */     
/* 75 */     ConnectionContext ctxt = this.contextContainer.removeContext(disconnectedHconn);
/* 76 */     if (ctxt != null) {
/* 77 */       this.contextContainer.removeAllTempQinfos(disconnectedHconn, ctxt.getQmgrName());
/*    */       
/* 79 */       if (ctxt.isCachePut1Dests()) {
/* 80 */         ctxt.clearQopNoneMQPUT1();
/*    */       }
/*    */     } 
/*    */     
/* 84 */     if (Trace.isOn)
/* 85 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiDiscInterceptorImpl", "afterMQDISC(Hconn,Phconn,Pint,Pint)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\JmqiDiscInterceptorImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */