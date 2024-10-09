/*    */ package com.ibm.mq.pcf;
/*    */ 
/*    */ import com.ibm.mq.constants.MQConstants;
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
/*    */ @Deprecated
/*    */ public abstract class PCFConstants
/*    */   extends MQConstants
/*    */ {
/*    */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFConstants.java";
/*    */   static final String PARAMETER_PATTERN = "MQIA_.*|MQCA_.*|MQBA_.*|MQGA_.*|MQIAMO_.*|MQIAMO64_.*|MQIACF_.*|MQIACH_.*|MQCAMO_.*|MQCACF_.*|MQCACH_.*|MQGACF_.*|MQBACF_.*";
/*    */   static final String PCF_EXCEPTION_PATTERN = "MQRCCF_.*";
/*    */   
/*    */   static {
/* 36 */     if (Trace.isOn) {
/* 37 */       Trace.data("com.ibm.mq.pcf.PCFConstants", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFConstants.java");
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
/*    */   public static String lookupParameter(int parameter) {
/* 67 */     if (Trace.isOn)
/* 68 */       Trace.entry("com.ibm.mq.pcf.PCFConstants", "lookupParameter(int)", new Object[] {
/* 69 */             Integer.valueOf(parameter)
/*    */           }); 
/* 71 */     String traceRet1 = (parameter == 0) ? null : lookup(parameter, "MQIA_.*|MQCA_.*|MQBA_.*|MQGA_.*|MQIAMO_.*|MQIAMO64_.*|MQIACF_.*|MQIACH_.*|MQCAMO_.*|MQCACF_.*|MQCACH_.*|MQGACF_.*|MQBACF_.*");
/* 72 */     if (Trace.isOn) {
/* 73 */       Trace.exit("com.ibm.mq.pcf.PCFConstants", "lookupParameter(int)", traceRet1);
/*    */     }
/* 75 */     return traceRet1;
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
/*    */   public static String lookupPCFReasonCode(int reason) {
/* 91 */     if (Trace.isOn)
/* 92 */       Trace.entry("com.ibm.mq.pcf.PCFConstants", "lookupPCFReasonCode(int)", new Object[] {
/* 93 */             Integer.valueOf(reason)
/*    */           }); 
/* 95 */     String traceRet1 = lookup(reason, "MQRCCF_.*");
/* 96 */     if (Trace.isOn) {
/* 97 */       Trace.exit("com.ibm.mq.pcf.PCFConstants", "lookupPCFReasonCode(int)", traceRet1);
/*    */     }
/* 99 */     return traceRet1;
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\PCFConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */