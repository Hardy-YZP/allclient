/*    */ package com.ibm.mq.jmqi;
/*    */ 
/*    */ import com.ibm.msg.client.commonservices.trace.Trace;
/*    */ import java.io.NotSerializableException;
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
/*    */ public abstract class JmqiObject
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiObject.java";
/*    */   protected JmqiEnvironment env;
/*    */   
/*    */   static {
/* 35 */     if (Trace.isOn) {
/* 36 */       Trace.data("com.ibm.mq.jmqi.JmqiObject", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/JmqiObject.java");
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
/* 51 */   public static int COMP_JM = 59;
/*    */ 
/*    */   
/* 54 */   public static int COMP_JN = 60;
/*    */ 
/*    */   
/* 57 */   public static int COMP_JO = 61;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected JmqiObject(JmqiEnvironment env) {
/* 64 */     if (Trace.isOn) {
/* 65 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiObject", "<init>(JmqiEnvironment)", new Object[] { env });
/*    */     }
/*    */     
/* 68 */     this.env = env;
/* 69 */     if (Trace.isOn) {
/* 70 */       Trace.exit(this, "com.ibm.mq.jmqi.JmqiObject", "<init>(JmqiEnvironment)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected JmqiObject() throws NotSerializableException {
/* 81 */     if (Trace.isOn) {
/* 82 */       Trace.entry(this, "com.ibm.mq.jmqi.JmqiObject", "<init>()");
/*    */     }
/* 84 */     NotSerializableException traceRet1 = new NotSerializableException();
/* 85 */     if (Trace.isOn) {
/* 86 */       Trace.throwing(this, "com.ibm.mq.jmqi.JmqiObject", "<init>()", traceRet1);
/*    */     }
/* 88 */     throw traceRet1;
/*    */   }
/*    */ 
/*    */   
/*    */   public JmqiEnvironment getJmqiEnvironment() {
/* 93 */     return this.env;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\JmqiObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */