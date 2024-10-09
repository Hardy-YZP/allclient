/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQSTS;
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
/*     */ public class MQAsyncStatus
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQAsyncStatus.java";
/*     */   
/*     */   static {
/*  35 */     if (Trace.isOn) {
/*  36 */       Trace.data("com.ibm.mq.MQAsyncStatus", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQAsyncStatus.java");
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
/*  49 */   public int putSuccessCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public int putWarningCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public int putFailureCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public int completionCode = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public int reasonCode = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public int objectType = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   public String objectName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public String objectQueueManagerName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public String resolvedObjectName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public String resolvedQueueManagerName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateAsyncStatus() throws MQException {
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.entry(this, "com.ibm.mq.MQAsyncStatus", "updateAsyncStatus()");
/*     */     }
/* 117 */     if (!this.qmgr.isConnected()) {
/* 118 */       MQException traceRet1 = new MQException(2, 2018, this);
/*     */ 
/*     */       
/* 121 */       if (Trace.isOn) {
/* 122 */         Trace.throwing(this, "com.ibm.mq.MQAsyncStatus", "updateAsyncStatus()", traceRet1, 1);
/*     */       }
/* 124 */       throw traceRet1;
/*     */     } 
/* 126 */     MQSESSION osession = this.qmgr.getSession();
/* 127 */     osession.MQSTAT(this.qmgr.Hconn.getHconn(), 0, this.jmqiStructure, this.pCompCode, this.pReason);
/*     */     
/* 129 */     if (this.pCompCode.x != 0) {
/*     */       
/* 131 */       MQException traceRet2 = new MQException(this.pCompCode.x, this.pReason.x, this, osession.getLastJmqiException());
/* 132 */       if (Trace.isOn) {
/* 133 */         Trace.throwing(this, "com.ibm.mq.MQAsyncStatus", "updateAsyncStatus()", traceRet2, 2);
/*     */       }
/* 135 */       throw traceRet2;
/*     */     } 
/* 137 */     this.putSuccessCount = this.jmqiStructure.getPutSuccessCount();
/* 138 */     this.putWarningCount = this.jmqiStructure.getPutWarningCount();
/* 139 */     this.putFailureCount = this.jmqiStructure.getPutFailureCount();
/* 140 */     this.completionCode = this.jmqiStructure.getCompCode();
/* 141 */     this.reasonCode = this.jmqiStructure.getReason();
/* 142 */     this.objectType = this.jmqiStructure.getObjectType();
/* 143 */     this.objectName = this.jmqiStructure.getObjectName();
/* 144 */     this.objectQueueManagerName = this.jmqiStructure.getObjectQMgrName();
/* 145 */     this.resolvedObjectName = this.jmqiStructure.getResolvedObjectName();
/* 146 */     this.resolvedQueueManagerName = this.jmqiStructure.getResolvedQMgrName();
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.exit(this, "com.ibm.mq.MQAsyncStatus", "updateAsyncStatus()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   private MQSTS jmqiStructure = MQSESSION.getJmqiEnv().newMQSTS();
/*     */ 
/*     */   
/* 158 */   private Pint pCompCode = new Pint();
/*     */ 
/*     */   
/* 161 */   private Pint pReason = new Pint();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private MQQueueManager qmgr;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQAsyncStatus(MQQueueManager qmgr) throws MQException {
/* 173 */     super(MQSESSION.getJmqiEnv());
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.entry(this, "com.ibm.mq.MQAsyncStatus", "<init>(MQQueueManager)", new Object[] { qmgr });
/*     */     }
/*     */     
/* 178 */     this.qmgr = qmgr;
/*     */     
/* 180 */     if (Trace.isOn)
/* 181 */       Trace.exit(this, "com.ibm.mq.MQAsyncStatus", "<init>(MQQueueManager)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQAsyncStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */