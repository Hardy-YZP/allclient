/*    */ package com.ibm.mq.jmqi.internal;
/*    */ 
/*    */ import com.ibm.mq.jmqi.handles.Hobj;
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
/*    */ public abstract class HobjAdapter
/*    */   implements Hobj
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/internal/HobjAdapter.java";
/*    */   
/*    */   public int getIntegerHandle() {
/* 48 */     if (Trace.isOn) {
/* 49 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HobjAdapter", "getIntegerHandle()", "getter", 
/* 50 */           Integer.valueOf(0));
/*    */     }
/* 52 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     if (Trace.isOn) {
/* 61 */       Trace.entry(this, "com.ibm.mq.jmqi.internal.HobjAdapter", "toString()");
/*    */     }
/* 63 */     if (Trace.isOn) {
/* 64 */       Trace.exit(this, "com.ibm.mq.jmqi.internal.HobjAdapter", "toString()", "MQHO_NONE");
/*    */     }
/* 66 */     return "MQHO_NONE";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte[] getAMSPolicy() {
/* 74 */     if (Trace.isOn) {
/* 75 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HobjAdapter", "getAMSPolicy()", "getter", null);
/*    */     }
/* 77 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getAMSErrorQueue() {
/* 85 */     if (Trace.isOn) {
/* 86 */       Trace.data(this, "com.ibm.mq.jmqi.internal.HobjAdapter", "getAMSErrorQueue()", "getter", null);
/*    */     }
/*    */     
/* 89 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\internal\HobjAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */