/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
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
/*     */ public class spiMQRRSQueueManager
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/spiMQRRSQueueManager.java";
/*     */   static final String copyright_notice = "Licensed Materials - Property of IBM 5724-H72, 5655-R36, 5724-L26, 5655-L82                (c) Copyright IBM Corp. 2008 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   private MQQueueManager qmgr;
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.MQRRSQueueManager", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/spiMQRRSQueueManager.java");
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
/*     */   public spiMQRRSQueueManager(MQQueueManager qmgr) {
/*  79 */     super(MQSESSION.getJmqiEnv());
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.entry(this, "com.ibm.mq.MQRRSQueueManager", "<init>(MQQueueManager)", new Object[] { qmgr });
/*     */     }
/*     */     
/*  84 */     this.qmgr = qmgr;
/*     */     
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.exit(this, "com.ibm.mq.MQRRSQueueManager", "<init>(MQQueueManager)");
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
/*     */   public void honourRRS() throws MQException {
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.entry(this, "com.ibm.mq.MQRRSQueueManager", "honourRRS()");
/*     */     }
/*     */ 
/*     */     
/* 108 */     this.qmgr.honourRRS();
/*     */     
/* 110 */     if (Trace.isOn)
/* 111 */       Trace.exit(this, "com.ibm.mq.MQRRSQueueManager", "honourRRS()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\spiMQRRSQueueManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */