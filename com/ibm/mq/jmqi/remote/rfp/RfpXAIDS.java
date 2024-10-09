/*    */ package com.ibm.mq.jmqi.remote.rfp;
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
/*    */ public class RfpXAIDS
/*    */   extends RfpStructure
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpXAIDS.java";
/*    */   private static final int COUNT_OFFSET = 0;
/*    */   public static final int SIZE_TO_XAIDS = 4;
/*    */   
/*    */   static {
/* 34 */     if (Trace.isOn) {
/* 35 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpXAIDS", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpXAIDS.java");
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
/*    */   public RfpXAIDS(JmqiEnvironment env, byte[] buffer, int offset) {
/* 53 */     super(env, buffer, offset);
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
/*    */   public void setCount(int count, boolean swap) {
/* 71 */     if (Trace.isOn)
/* 72 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpXAIDS", "setCount(int,boolean)", new Object[] {
/* 73 */             Integer.valueOf(count), Boolean.valueOf(swap)
/*    */           }); 
/* 75 */     this.dc.writeI32(count, this.buffer, this.offset + 0, swap);
/*    */     
/* 77 */     if (Trace.isOn) {
/* 78 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpXAIDS", "setCount(int,boolean)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCount(boolean swap) {
/* 86 */     if (Trace.isOn)
/* 87 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpXAIDS", "getCount(boolean)", new Object[] {
/* 88 */             Boolean.valueOf(swap)
/*    */           }); 
/* 90 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 0, swap);
/*    */     
/* 92 */     if (Trace.isOn) {
/* 93 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpXAIDS", "getCount(boolean)", 
/* 94 */           Integer.valueOf(traceRet1));
/*    */     }
/* 96 */     return traceRet1;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpXAIDS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */