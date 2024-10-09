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
/*    */ public class RfpMQSTAT
/*    */   extends RfpStructure
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpMQSTAT.java";
/*    */   private static final int TYPE_OFFSET = 0;
/*    */   private static final int STATUS_INFO_OFFSET = 4;
/*    */   public static final int SIZE = 4;
/*    */   
/*    */   static {
/* 34 */     if (Trace.isOn) {
/* 35 */       Trace.data("com.ibm.mq.jmqi.remote.rfp.RfpMQSTAT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi.remote/src/com/ibm/mq/jmqi/remote/rfp/RfpMQSTAT.java");
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
/*    */   public RfpMQSTAT(JmqiEnvironment env, byte[] buffer, int offset) {
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
/*    */   
/*    */   public void setType(int type, boolean swap) {
/* 72 */     if (Trace.isOn)
/* 73 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQSTAT", "setType(int,boolean)", new Object[] {
/* 74 */             Integer.valueOf(type), Boolean.valueOf(swap)
/*    */           }); 
/* 76 */     this.dc.writeI32(type, this.buffer, this.offset + 0, swap);
/*    */     
/* 78 */     if (Trace.isOn) {
/* 79 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQSTAT", "setType(int,boolean)");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getType(boolean swap) {
/* 87 */     if (Trace.isOn)
/* 88 */       Trace.entry(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQSTAT", "getType(boolean)", new Object[] {
/* 89 */             Boolean.valueOf(swap)
/*    */           }); 
/* 91 */     int traceRet1 = this.dc.readI32(this.buffer, this.offset + 0, swap);
/*    */     
/* 93 */     if (Trace.isOn) {
/* 94 */       Trace.exit(this, "com.ibm.mq.jmqi.remote.rfp.RfpMQSTAT", "getType(boolean)", 
/* 95 */           Integer.valueOf(traceRet1));
/*    */     }
/* 97 */     return traceRet1;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\remote\rfp\RfpMQSTAT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */