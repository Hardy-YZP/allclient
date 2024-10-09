/*     */ package com.ibm.mq.headers.pcf;
/*     */ 
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
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
/*     */ 
/*     */ final class PCFMessageAgentResponseTrackerNon390
/*     */   extends PCFMessageAgentResponseTracker
/*     */ {
/*     */   static {
/*  68 */     if (Trace.isOn) {
/*  69 */       Trace.data("com.ibm.mq.headers.pcf.PCFMessageAgentResponseTrackerNon390", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFMessageAgentResponseTracker.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   int responseCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   int lastResponseSeqNumber = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   static String ENABLESTRICTPCFRESPONSECHECKING = "com.ibm.mq.pcf.enableStrictPCFResponseChecking";
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLast(PCFMessage response) throws MQDataException, IOException {
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFMessageAgentResponseTrackerNon390", "isLast(PCFMessage)", new Object[] { response });
/*     */     }
/*     */ 
/*     */     
/* 102 */     boolean isLast = false;
/*     */ 
/*     */     
/* 105 */     PropertyStore.register(ENABLESTRICTPCFRESPONSECHECKING, false);
/*     */     
/* 107 */     boolean strictPCFResponseCheckingEnabled = PropertyStore.getBooleanPropertyObject(ENABLESTRICTPCFRESPONSECHECKING).booleanValue();
/*     */     
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessageAgentResponseTrackerNon390", "isLast(PCFMessage)", "enableStrictPCFResponseChecking: ", 
/*     */           
/* 112 */           Boolean.valueOf(strictPCFResponseCheckingEnabled));
/*     */     }
/*     */ 
/*     */     
/* 116 */     this.responseCount++;
/*     */ 
/*     */     
/* 119 */     int controlField = response.getControl();
/*     */ 
/*     */ 
/*     */     
/* 123 */     if (controlField == 1) {
/* 124 */       this.lastResponseSeqNumber = response.getMsgSeqNumber();
/* 125 */       if (Trace.isOn) {
/* 126 */         Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessageAgentResponseTrackerNon390", "isLast(PCFMessage)", "Setting lastResponseSeqNumber to: ", 
/*     */             
/* 128 */             Integer.valueOf(this.lastResponseSeqNumber));
/* 129 */         Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessageAgentResponseTrackerNon390", "isLast(PCFMessage)", "Number of responses processed so far", 
/*     */             
/* 131 */             Integer.valueOf(this.responseCount));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 136 */       if (!strictPCFResponseCheckingEnabled) {
/* 137 */         if (Trace.isOn) {
/* 138 */           Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessageAgentResponseTrackerNon390", "isLast(PCFMessage)", "Assuming all responses have been processed ");
/*     */         }
/*     */         
/* 141 */         isLast = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 146 */     if (strictPCFResponseCheckingEnabled)
/*     */     {
/*     */       
/* 149 */       if (this.responseCount == this.lastResponseSeqNumber) {
/*     */ 
/*     */ 
/*     */         
/* 153 */         if (Trace.isOn) {
/* 154 */           Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessageAgentResponseTrackerNon390", "isLast(PCFMessage)", "Strict response checking has been enabled, and all responses have been processed");
/*     */         }
/*     */ 
/*     */         
/* 158 */         isLast = true;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 163 */         if (Trace.isOn) {
/* 164 */           Trace.data(this, "com.ibm.mq.headers.pcf.PCFMessageAgentResponseTrackerNon390", "isLast(PCFMessage)", "Strict response checking has been enabled, and additional responses are expected.");
/*     */         }
/*     */ 
/*     */         
/* 168 */         isLast = false;
/*     */       } 
/*     */     }
/*     */     
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFMessageAgentResponseTrackerNon390", "isLast(PCFMessage)", 
/* 174 */           Boolean.valueOf(isLast));
/*     */     }
/* 176 */     return isLast;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\PCFMessageAgentResponseTrackerNon390.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */