/*    */ package com.ibm.mq.jmqi.handles;
/*    */ 
/*    */ import com.ibm.mq.constants.CMQC;
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
/*    */ public class Phconn
/*    */   extends JmqiObject
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/handles/Phconn.java";
/*    */   private Hconn hconn;
/*    */   
/*    */   static {
/* 34 */     if (Trace.isOn) {
/* 35 */       Trace.data("com.ibm.mq.jmqi.handles.Phconn", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/handles/Phconn.java");
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
/*    */   public Phconn(JmqiEnvironment env) {
/* 55 */     super(env);
/* 56 */     if (Trace.isOn) {
/* 57 */       Trace.entry(this, "com.ibm.mq.jmqi.handles.Phconn", "<init>(JmqiEnvironment)", new Object[] { env });
/*    */     }
/*    */     
/* 60 */     this.hconn = CMQC.jmqi_MQHC_DEF_HCONN;
/*    */     
/* 62 */     if (Trace.isOn) {
/* 63 */       Trace.exit(this, "com.ibm.mq.jmqi.handles.Phconn", "<init>(JmqiEnvironment)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Hconn getHconn() {
/* 73 */     if (Trace.isOn) {
/* 74 */       Trace.data(this, "com.ibm.mq.jmqi.handles.Phconn", "getHconn()", "getter", this.hconn);
/*    */     }
/* 76 */     return this.hconn;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setHconn(Hconn hconn) {
/* 84 */     if (Trace.isOn) {
/* 85 */       Trace.data(this, "com.ibm.mq.jmqi.handles.Phconn", "setHconn(Hconn)", "setter", hconn);
/*    */     }
/* 87 */     this.hconn = hconn;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 97 */     return (this.hconn != null) ? this.hconn.toString() : "<null>";
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\handles\Phconn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */