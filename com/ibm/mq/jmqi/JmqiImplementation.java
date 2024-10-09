/*    */ package com.ibm.mq.jmqi;
/*    */ 
/*    */ import com.ibm.mq.jmqi.handles.Hconn;
/*    */ import com.ibm.mq.jmqi.handles.Hobj;
/*    */ import com.ibm.mq.jmqi.handles.Pint;
/*    */ import com.ibm.mq.jmqi.system.JmqiComponent;
/*    */ import com.ibm.mq.jmqi.system.JmqiSP;
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import java.nio.ByteBuffer;
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
/*    */ public abstract class JmqiImplementation
/*    */   extends JmqiObject
/*    */   implements JmqiMQ, JmqiSP, JmqiXA, JmqiComponent
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiImplementation.java";
/*    */   
/*    */   static {
/* 43 */     if (Trace.isOn) {
/* 44 */       Trace.data("com.ibm.mq.jmqi.JmqiImplementation", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiImplementation.java");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract void jmqiGetInternal(Hconn paramHconn, Hobj paramHobj, MQMD paramMQMD, MQGMO paramMQGMO, int paramInt, ByteBuffer paramByteBuffer, Pint paramPint1, Pint paramPint2, Pint paramPint3);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected JmqiImplementation(JmqiEnvironment env) {
/* 59 */     super(env);
/* 60 */     if (Trace.isOn) {
/* 61 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiImplementation", "<init>(JmqiEnvironment)", new Object[] { env });
/*    */     }
/*    */     
/* 64 */     if (Trace.isOn)
/* 65 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiImplementation", "<init>(JmqiEnvironment)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\JmqiImplementation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */