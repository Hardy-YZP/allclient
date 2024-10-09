/*    */ package com.ibm.mq.jmqi.system;
/*    */ 
/*    */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*    */ import com.ibm.mq.jmqi.JmqiObject;
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
/*    */ 
/*    */ public abstract class JmqiRunnable
/*    */   extends JmqiObject
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/JmqiRunnable.java";
/* 48 */   public Throwable jmqiRunnableException = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JmqiRunnable(JmqiEnvironment env) {
/* 54 */     super(env);
/* 55 */     if (Trace.isOn) {
/* 56 */       Trace.entry(this, "com.ibm.mq.jmqi.system.JmqiRunnable", "<init>(JmqiEnvironment)", new Object[] { env });
/*    */     }
/*    */     
/* 59 */     if (Trace.isOn)
/* 60 */       Trace.exit(this, "com.ibm.mq.jmqi.system.JmqiRunnable", "<init>(JmqiEnvironment)"); 
/*    */   }
/*    */   
/*    */   public abstract void run() throws Exception;
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\JmqiRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */