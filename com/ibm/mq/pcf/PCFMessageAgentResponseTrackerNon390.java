/*     */ package com.ibm.mq.pcf;
/*     */ 
/*     */ import com.ibm.mq.MQException;
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
/* 438 */     if (Trace.isOn) {
/* 439 */       Trace.data("com.ibm.mq.pcf.PCFMessageAgentResponseTrackerNon390", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFMessageAgent.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 449 */   int responseCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 457 */   int lastResponseSeqNumber = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 463 */   static String ENABLESTRICTPCFRESPONSECHECKING = "com.ibm.mq.pcf.enableStrictPCFResponseChecking";
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isLast(PCFMessage response) throws MQException, IOException {
/* 468 */     if (Trace.isOn) {
/* 469 */       Trace.entry(this, "com.ibm.mq.pcf.PCFMessageAgentResponseTrackerNon390", "isLast(PCFMessage)", new Object[] { response });
/*     */     }
/*     */ 
/*     */     
/* 473 */     boolean isLast = false;
/*     */ 
/*     */     
/* 476 */     PropertyStore.register(ENABLESTRICTPCFRESPONSECHECKING, false);
/*     */     
/* 478 */     boolean strictPCFResponseCheckingEnabled = PropertyStore.getBooleanPropertyObject(ENABLESTRICTPCFRESPONSECHECKING).booleanValue();
/*     */     
/* 480 */     if (Trace.isOn) {
/* 481 */       Trace.data(this, "com.ibm.mq.pcf.PCFMessageAgentResponseTrackerNon390", "isLast(PCFMessage)", "enableStrictPCFResponseChecking: ", 
/*     */           
/* 483 */           Boolean.valueOf(strictPCFResponseCheckingEnabled));
/*     */     }
/*     */ 
/*     */     
/* 487 */     this.responseCount++;
/*     */ 
/*     */     
/* 490 */     int controlField = response.getControl();
/*     */ 
/*     */ 
/*     */     
/* 494 */     if (controlField == 1) {
/* 495 */       this.lastResponseSeqNumber = response.getMsgSeqNumber();
/* 496 */       if (Trace.isOn) {
/* 497 */         Trace.data(this, "com.ibm.mq.pcf.PCFMessageAgentResponseTrackerNon390", "isLast(PCFMessage)", "Setting lastResponseSeqNumber to: ", 
/*     */             
/* 499 */             Integer.valueOf(this.lastResponseSeqNumber));
/* 500 */         Trace.data(this, "com.ibm.mq.pcf.PCFMessageAgentResponseTrackerNon390", "isLast(PCFMessage)", "Number of responses processed so far", 
/*     */             
/* 502 */             Integer.valueOf(this.responseCount));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 507 */       if (!strictPCFResponseCheckingEnabled) {
/* 508 */         if (Trace.isOn) {
/* 509 */           Trace.data(this, "com.ibm.mq.pcf.PCFMessageAgentResponseTrackerNon390", "isLast(PCFMessage)", "Assuming all responses have been processed ");
/*     */         }
/*     */         
/* 512 */         isLast = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 517 */     if (strictPCFResponseCheckingEnabled)
/*     */     {
/*     */       
/* 520 */       if (this.responseCount == this.lastResponseSeqNumber) {
/*     */ 
/*     */ 
/*     */         
/* 524 */         if (Trace.isOn) {
/* 525 */           Trace.data(this, "com.ibm.mq.pcf.PCFMessageAgentResponseTrackerNon390", "isLast(PCFMessage)", "Strict response checking has been enabled, and all responses have been processed");
/*     */         }
/*     */ 
/*     */         
/* 529 */         isLast = true;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 534 */         if (Trace.isOn) {
/* 535 */           Trace.data(this, "com.ibm.mq.pcf.PCFMessageAgentResponseTrackerNon390", "isLast(PCFMessage)", "Strict response checking has been enabled, and additional responses are expected.");
/*     */         }
/*     */ 
/*     */         
/* 539 */         isLast = false;
/*     */       } 
/*     */     }
/*     */     
/* 543 */     if (Trace.isOn) {
/* 544 */       Trace.exit(this, "com.ibm.mq.pcf.PCFMessageAgentResponseTrackerNon390", "isLast(PCFMessage)", 
/* 545 */           Boolean.valueOf(isLast));
/*     */     }
/* 547 */     return isLast;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\PCFMessageAgentResponseTrackerNon390.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */