/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQPMO;
/*     */ import com.ibm.mq.jmqi.MQPMR;
/*     */ import com.ibm.mq.jmqi.MQRR;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQPutMessageOptions
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQPutMessageOptions.java";
/*     */   
/*     */   static {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.data("com.ibm.mq.MQPutMessageOptions", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQPutMessageOptions.java");
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
/*  82 */   private MQPMO jmqiStructure = MQSESSION.getJmqiEnv().newMQPMO();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean noReadBack = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public int options = 0;
/*     */   
/* 111 */   public int contextReferenceHandle = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public MQQueue contextReference = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public String resolvedQueueName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public String resolvedQueueManagerName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public int knownDestCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public int unknownDestCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public int invalidDestCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   public int recordFields = 0;
/*     */   
/*     */   private MQRR[] responseRecords;
/*     */   
/*     */   private MQDistributionListItem[] ditems;
/*     */   
/*     */   public MQPutMessageOptions() {
/* 165 */     super(MQSESSION.getJmqiEnv());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 210 */     this.responseRecords = null;
/* 211 */     this.ditems = null;
/*     */     if (Trace.isOn) {
/*     */       Trace.entry(this, "com.ibm.mq.MQPutMessageOptions", "<init>()");
/*     */     }
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.MQPutMessageOptions", "<init>()"); 
/*     */   }
/*     */   
/*     */   protected final void version2(MQDistributionListItem[] ditems) {
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.entry(this, "com.ibm.mq.MQPutMessageOptions", "version2(MQDistributionListItem [ ])", new Object[] { ditems });
/*     */     }
/*     */     
/* 224 */     this.jmqiStructure.setVersion(2);
/*     */ 
/*     */     
/* 227 */     this.ditems = ditems;
/* 228 */     this.responseRecords = new MQRR[ditems.length];
/* 229 */     MQPMR[] putMsgRecords = new MQPMR[ditems.length];
/* 230 */     for (int i = 0; i < ditems.length; i++) {
/* 231 */       this.responseRecords[i] = MQSESSION.getJmqiEnv().newMQRR();
/* 232 */       putMsgRecords[i] = MQSESSION.getJmqiEnv().newMQPMR();
/*     */     } 
/* 234 */     this.jmqiStructure.setRecsPresent(ditems.length);
/* 235 */     this.jmqiStructure.setPutMsgRecords(putMsgRecords);
/* 236 */     this.jmqiStructure.setResponseRecords(this.responseRecords);
/*     */     
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.exit(this, "com.ibm.mq.MQPutMessageOptions", "version2(MQDistributionListItem [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void updateDistributionListItems() {
/* 251 */     if (Trace.isOn) {
/* 252 */       Trace.entry(this, "com.ibm.mq.MQPutMessageOptions", "updateDistributionListItems()");
/*     */     }
/* 254 */     if (Trace.isOn) {
/* 255 */       Trace.exit(this, "com.ibm.mq.MQPutMessageOptions", "updateDistributionListItems()");
/*     */     }
/*     */   }
/*     */   
/*     */   public MQPutMessageOptions(boolean noReadBack) {
/*     */     this();
/*     */     if (Trace.isOn) {
/*     */       Trace.entry(this, "com.ibm.mq.MQPutMessageOptions", "<init>(boolean)", new Object[] { Boolean.valueOf(noReadBack) });
/*     */     }
/*     */     if (noReadBack == true) {
/*     */       this.noReadBack = true;
/*     */     }
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.MQPutMessageOptions", "<init>(boolean)"); 
/*     */   }
/*     */   
/*     */   protected MQPMO getJMQIStructure() {
/* 272 */     this.jmqiStructure.setOptions(this.options);
/* 273 */     this.jmqiStructure.setContext(this.contextReferenceHandle);
/* 274 */     this.jmqiStructure.setResolvedQName(this.resolvedQueueName);
/* 275 */     this.jmqiStructure.setResolvedQMgrName(this.resolvedQueueManagerName);
/* 276 */     this.jmqiStructure.setKnownDestCount(this.knownDestCount);
/* 277 */     this.jmqiStructure.setUnknownDestCount(this.unknownDestCount);
/* 278 */     this.jmqiStructure.setInvalidDestCount(this.invalidDestCount);
/*     */     
/* 280 */     int localRecordFields = this.recordFields;
/* 281 */     this.jmqiStructure.setPutMsgRecFields(localRecordFields);
/* 282 */     if (this.ditems != null) {
/* 283 */       MQPMR[] jmqiPutMsgRecs = this.jmqiStructure.getPutMsgRecords();
/* 284 */       MQRR[] jmqiReponseRecords = this.jmqiStructure.getResponseRecords();
/* 285 */       for (int i = 0; i < this.jmqiStructure.getRecsPresent(); i++) {
/*     */         
/* 287 */         jmqiReponseRecords[i].setCompCode((this.ditems[i]).completionCode);
/* 288 */         jmqiReponseRecords[i].setReason((this.ditems[i]).reasonCode);
/*     */ 
/*     */         
/* 291 */         if (localRecordFields != 0) {
/* 292 */           if ((localRecordFields & 0x1) != 0) {
/* 293 */             jmqiPutMsgRecs[i].setMsgId((this.ditems[i]).messageId);
/*     */           }
/* 295 */           if ((localRecordFields & 0x2) != 0) {
/* 296 */             jmqiPutMsgRecs[i].setCorrelId((this.ditems[i]).correlationId);
/*     */           }
/* 298 */           if ((localRecordFields & 0x4) != 0) {
/* 299 */             jmqiPutMsgRecs[i].setGroupId((this.ditems[i]).groupId);
/*     */           }
/* 301 */           if ((localRecordFields & 0x8) != 0) {
/* 302 */             jmqiPutMsgRecs[i].setFeedback((this.ditems[i]).feedback);
/*     */           }
/* 304 */           if ((localRecordFields & 0x10) != 0) {
/* 305 */             jmqiPutMsgRecs[i].setAccountingToken((this.ditems[i]).accountingToken);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 311 */     if (Trace.isOn) {
/* 312 */       Trace.data(this, "com.ibm.mq.MQPutMessageOptions", "getJMQIStructure()", "getter", this.jmqiStructure);
/*     */     }
/*     */     
/* 315 */     return this.jmqiStructure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateFromJMQIStructure() {
/* 324 */     if (Trace.isOn) {
/* 325 */       Trace.entry(this, "com.ibm.mq.MQPutMessageOptions", "updateFromJMQIStructure()");
/*     */     }
/* 327 */     if (!this.noReadBack) {
/* 328 */       this.options = this.jmqiStructure.getOptions();
/* 329 */       this.contextReferenceHandle = this.jmqiStructure.getContext();
/* 330 */       this.resolvedQueueName = this.jmqiStructure.getResolvedQName();
/* 331 */       this.resolvedQueueManagerName = this.jmqiStructure.getResolvedQMgrName();
/* 332 */       this.knownDestCount = this.jmqiStructure.getKnownDestCount();
/* 333 */       this.unknownDestCount = this.jmqiStructure.getUnknownDestCount();
/* 334 */       this.invalidDestCount = this.jmqiStructure.getInvalidDestCount();
/*     */       
/* 336 */       int localRecordFields = this.jmqiStructure.getPutMsgRecFields();
/* 337 */       this.recordFields = localRecordFields;
/* 338 */       if (this.ditems != null) {
/*     */ 
/*     */         
/* 341 */         MQRR[] responseRecords = this.jmqiStructure.getResponseRecords();
/* 342 */         MQPMR[] jmqiPutMsgRecs = this.jmqiStructure.getPutMsgRecords();
/* 343 */         for (int i = 0; i < this.jmqiStructure.getRecsPresent(); i++) {
/* 344 */           (this.ditems[i]).completionCode = responseRecords[i].getCompCode();
/* 345 */           (this.ditems[i]).reasonCode = responseRecords[i].getReason();
/*     */           
/* 347 */           if (localRecordFields != 0) {
/* 348 */             if ((localRecordFields & 0x1) != 0) {
/* 349 */               (this.ditems[i]).messageId = jmqiPutMsgRecs[i].getMsgId();
/*     */             }
/* 351 */             if ((localRecordFields & 0x2) != 0) {
/* 352 */               (this.ditems[i]).correlationId = jmqiPutMsgRecs[i].getCorrelId();
/*     */             }
/* 354 */             if ((localRecordFields & 0x4) != 0) {
/* 355 */               (this.ditems[i]).groupId = jmqiPutMsgRecs[i].getGroupId();
/*     */             }
/* 357 */             if ((localRecordFields & 0x8) != 0) {
/* 358 */               (this.ditems[i]).feedback = jmqiPutMsgRecs[i].getFeedback();
/*     */             }
/* 360 */             if ((localRecordFields & 0x10) != 0) {
/* 361 */               (this.ditems[i]).accountingToken = jmqiPutMsgRecs[i].getAccountingToken();
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 368 */     if (Trace.isOn)
/* 369 */       Trace.exit(this, "com.ibm.mq.MQPutMessageOptions", "updateFromJMQIStructure()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQPutMessageOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */