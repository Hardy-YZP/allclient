/*    */ package com.ibm.mq.jmqi;
/*    */ 
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
/*    */ public class JmqiDefaultThreadPoolFactory
/*    */   implements JmqiThreadPoolFactory
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiDefaultThreadPoolFactory.java";
/*    */   JmqiEnvironment env;
/*    */   
/*    */   static {
/* 33 */     if (Trace.isOn) {
/* 34 */       Trace.data("com.ibm.mq.jmqi.JmqiDefaultThreadPoolFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiDefaultThreadPoolFactory.java");
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setEnv(JmqiEnvironment env) {
/* 51 */     if (Trace.isOn) {
/* 52 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiDefaultThreadPoolFactory", "setEnv(JmqiEnvironment)", "setter", env);
/*    */     }
/*    */     
/* 55 */     this.env = env;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JmqiThreadPool getThreadPool() {
/* 63 */     JmqiThreadPool traceRet1 = new JmqiDefaultThreadPool(this.env);
/* 64 */     if (Trace.isOn) {
/* 65 */       Trace.data(this, "com.ibm.mq.jmqi.JmqiDefaultThreadPoolFactory", "getThreadPool()", "getter", traceRet1);
/*    */     }
/*    */     
/* 68 */     return traceRet1;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\JmqiDefaultThreadPoolFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */