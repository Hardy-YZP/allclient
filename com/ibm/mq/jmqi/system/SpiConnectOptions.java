/*    */ package com.ibm.mq.jmqi.system;
/*    */ 
/*    */ import com.ibm.mq.jmqi.JmqiEnvironment;
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
/*    */ @Deprecated
/*    */ public class SpiConnectOptions
/*    */   extends LpiPrivConnStruct
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/SpiConnectOptions.java";
/*    */   
/*    */   protected SpiConnectOptions(JmqiEnvironment env) {
/* 43 */     super(env);
/* 44 */     if (Trace.isOn) {
/* 45 */       Trace.entry(this, "com.ibm.mq.jmqi.system.SpiConnectOptions", "<init>(JmqiEnvironment)", new Object[] { env });
/*    */     }
/*    */     
/* 48 */     if (Trace.isOn)
/* 49 */       Trace.exit(this, "com.ibm.mq.jmqi.system.SpiConnectOptions", "<init>(JmqiEnvironment)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\SpiConnectOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */