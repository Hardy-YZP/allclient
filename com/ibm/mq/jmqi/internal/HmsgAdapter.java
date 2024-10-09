/*    */ package com.ibm.mq.jmqi.internal;
/*    */ 
/*    */ import com.ibm.mq.jmqi.handles.Hmsg;
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
/*    */ public abstract class HmsgAdapter
/*    */   implements Hmsg
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/HmsgAdapter.java";
/*    */   
/*    */   public long getLongHandle() {
/* 48 */     if (Trace.isOn) {
/* 49 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HmsgAdapter", "getLongHandle()", "getter", 
/* 50 */           Long.valueOf(0L));
/*    */     }
/* 52 */     return 0L;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     if (Trace.isOn) {
/* 61 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.HmsgAdapter", "toString()");
/*    */     }
/* 63 */     if (Trace.isOn) {
/* 64 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.HmsgAdapter", "toString()", "MQHM_NONE");
/*    */     }
/* 66 */     return "MQHM_NONE";
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\HmsgAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */