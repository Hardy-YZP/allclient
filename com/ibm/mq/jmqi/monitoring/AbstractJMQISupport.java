/*    */ package com.ibm.mq.jmqi.monitoring;
/*    */ 
/*    */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*    */ import com.ibm.mq.jmqi.JmqiMQ;
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
/*    */ public abstract class AbstractJMQISupport
/*    */   extends JmqiInterceptAdapter
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/monitoring/AbstractJMQISupport.java";
/*    */   protected JmqiMQ delegate;
/*    */   protected JmqiEnvironment env;
/*    */   
/*    */   static {
/* 51 */     if (Trace.isOn) {
/* 52 */       Trace.data("com.ibm.mq.jmqi.monitoring.AbstractJMQISupport", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/monitoring/AbstractJMQISupport.java");
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected AbstractJMQISupport(JmqiEnvironment env, int options, JmqiMQ mq) {
/* 73 */     super(env, options, mq);
/* 74 */     if (Trace.isOn) {
/* 75 */       Trace.entry(this, "com.ibm.mq.jmqi.monitoring.AbstractJMQISupport", "<init>(JmqiEnvironment,int,JmqiMQ)", new Object[] { env, 
/* 76 */             Integer.valueOf(options), mq });
/*    */     }
/* 78 */     this.env = env;
/* 79 */     if (Trace.isOn)
/* 80 */       Trace.exit(this, "com.ibm.mq.jmqi.monitoring.AbstractJMQISupport", "<init>(JmqiEnvironment,int,JmqiMQ)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\monitoring\AbstractJMQISupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */