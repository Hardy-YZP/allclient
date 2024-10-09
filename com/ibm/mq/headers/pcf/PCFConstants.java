/*     */ package com.ibm.mq.headers.pcf;
/*     */ 
/*     */ import com.ibm.mq.constants.MQConstants;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PCFConstants
/*     */   extends MQConstants
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFConstants.java";
/*     */   static final String PARAMETER_PATTERN = "MQIA_.*|MQCA_.*|MQBA_.*|MQGA_.*|MQIAMO_.*|MQIAMO64_.*|MQIACF_.*|MQIACH_.*|MQCAMO_.*|MQCACF_.*|MQCACH_.*|MQGACF_.*|MQBACF_.*";
/*     */   static final String PCF_EXCEPTION_PATTERN = "MQRCCF_.*";
/*     */   
/*     */   static {
/*  33 */     if (Trace.isOn) {
/*  34 */       Trace.data("com.ibm.mq.headers.pcf.PCFConstants", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFConstants.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String lookupParameter(int parameter) {
/*  66 */     if (Trace.isOn)
/*  67 */       Trace.entry("com.ibm.mq.headers.pcf.PCFConstants", "lookupParameter(int)", new Object[] {
/*  68 */             Integer.valueOf(parameter)
/*     */           }); 
/*  70 */     String traceRet1 = (parameter == 0) ? null : lookup(parameter, "MQIA_.*|MQCA_.*|MQBA_.*|MQGA_.*|MQIAMO_.*|MQIAMO64_.*|MQIACF_.*|MQIACH_.*|MQCAMO_.*|MQCACF_.*|MQCACH_.*|MQGACF_.*|MQBACF_.*");
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.exit("com.ibm.mq.headers.pcf.PCFConstants", "lookupParameter(int)", traceRet1);
/*     */     }
/*     */     
/*  75 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String lookupPCFReasonCode(int reason) {
/*  91 */     if (Trace.isOn)
/*  92 */       Trace.entry("com.ibm.mq.headers.pcf.PCFConstants", "lookupPCFReasonCode(int)", new Object[] {
/*  93 */             Integer.valueOf(reason)
/*     */           }); 
/*  95 */     String traceRet1 = lookup(reason, "MQRCCF_.*");
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.exit("com.ibm.mq.headers.pcf.PCFConstants", "lookupPCFReasonCode(int)", traceRet1);
/*     */     }
/*     */     
/* 100 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\PCFConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */