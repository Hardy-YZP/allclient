/*    */ package com.ibm.mq;
/*    */ 
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
/*    */ final class Pint
/*    */   extends Pint
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/Pint.java";
/*    */   
/*    */   static {
/* 52 */     if (Trace.isOn) {
/* 53 */       Trace.data("com.ibm.mq.Pint", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/Pint.java");
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
/*    */   public Pint() {
/* 69 */     super(MQSESSION.getJmqiEnv());
/* 70 */     if (Trace.isOn) {
/* 71 */       Trace.entry(this, "com.ibm.mq.Pint", "<init>()");
/*    */     }
/* 73 */     if (Trace.isOn) {
/* 74 */       Trace.exit(this, "com.ibm.mq.Pint", "<init>()");
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
/*    */   public Pint(int val) {
/* 86 */     super(MQSESSION.getJmqiEnv(), val);
/* 87 */     if (Trace.isOn) {
/* 88 */       Trace.entry(this, "com.ibm.mq.Pint", "<init>(int)", new Object[] { Integer.valueOf(val) });
/*    */     }
/* 90 */     if (Trace.isOn)
/* 91 */       Trace.exit(this, "com.ibm.mq.Pint", "<init>(int)"); 
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\Pint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */