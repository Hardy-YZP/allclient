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
/*    */ public class RfpMQCLOSE
/*    */   extends RfpStructure
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpMQCLOSE.java";
/*    */   private static final int OPTIONS_OFFSET = 0;
/*    */   public static final int SIZE = 4;
/*    */   
/*    */   static {
/* 33 */     if (Trace.isOn) {
/* 34 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpMQCLOSE", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpMQCLOSE.java");
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
/*    */   public RfpMQCLOSE(JmqiEnvironment env, byte[] buffer, int offset) {
/* 51 */     super(env, buffer, offset);
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
/*    */   public void setOptions(int options, boolean swap) {
/* 68 */     if (Trace.isOn)
/* 69 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCLOSE", "setOptions(int,boolean)", new Object[] {
/* 70 */             Integer.valueOf(options), Boolean.valueOf(swap)
/*    */           }); 
/* 72 */     this.dc.writeI32(options, this.buffer, this.offset + 0, swap);
/*    */     
/* 74 */     if (Trace.isOn) {
/* 75 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCLOSE", "setOptions(int,boolean)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getOptions(boolean swap) {
/* 84 */     if (Trace.isOn)
/* 85 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCLOSE", "getOptions(boolean)", new Object[] {
/* 86 */             Boolean.valueOf(swap)
/*    */           }); 
/* 88 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 0, swap);
/*    */     
/* 90 */     if (Trace.isOn) {
/* 91 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQCLOSE", "getOptions(boolean)", 
/* 92 */           Integer.valueOf(traceRet1));
/*    */     }
/* 94 */     return traceRet1;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpMQCLOSE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */