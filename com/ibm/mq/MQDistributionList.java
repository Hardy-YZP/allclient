/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.JmqiMQ;
/*     */ import com.ibm.mq.jmqi.MQOD;
/*     */ import com.ibm.mq.jmqi.system.JmqiSP;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.SpiOpenOptions;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQDistributionList
/*     */   extends MQManagedObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQDistributionList.java";
/*     */   private MQSESSION osession;
/*     */   private int knownDestCount;
/*     */   private int unknownDestCount;
/*     */   private int invalidDestCount;
/*     */   private MQQueueManager mgr;
/*     */   private Pint completionCode;
/*     */   private Pint reason;
/*     */   private MQDistributionListItem[] items;
/*     */   
/*     */   static {
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.data("com.ibm.mq.MQDistributionList", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQDistributionList.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQDistributionList(MQQueueManager qMgr, MQDistributionListItem[] litems, int openOptions, String alternateUserId) throws MQException {
/*     */     int queueManagerCmdLevel;
/*  85 */     this.osession = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 572 */     this.mgr = null;
/* 573 */     this.completionCode = new Pint();
/* 574 */     this.reason = new Pint();
/*     */     
/* 576 */     this.items = null;
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.MQDistributionList", "<init>(MQQueueManager,MQDistributionListItem [ ],int,String)", new Object[] { qMgr, litems, Integer.valueOf(openOptions), alternateUserId }); 
/*     */     String fid = "<init>(MQQueueManager,MQDistributionListItem [ ],int,String)";
/*     */     if (Trace.isOn)
/*     */       Trace.data(this, "<init>(MQQueueManager,MQDistributionListItem [ ],int,String)", "sccsid", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQDistributionList.java"); 
/*     */     if (qMgr == null) {
/*     */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI001");
/*     */       if (Trace.isOn)
/*     */         Trace.throwing(this, "com.ibm.mq.MQDistributionList", "<init>(MQQueueManager,MQDistributionListItem [ ],int,String)", traceRet1, 1); 
/*     */       throw traceRet1;
/*     */     } 
/*     */     this.osession = qMgr.getSession();
/*     */     if (!qMgr.connected) {
/*     */       MQException traceRet2 = new MQException(2, 2018, this, "MQJI002");
/*     */       if (Trace.isOn)
/*     */         Trace.throwing(this, "com.ibm.mq.MQDistributionList", "<init>(MQQueueManager,MQDistributionListItem [ ],int,String)", traceRet2, 2); 
/*     */       throw traceRet2;
/*     */     } 
/*     */     if (!qMgr.getDistributionListCapable()) {
/*     */       MQException traceRet3 = new MQException(2, 2044, this, "MQJI030");
/*     */       if (Trace.isOn)
/*     */         Trace.throwing(this, "com.ibm.mq.MQDistributionList", "<init>(MQQueueManager,MQDistributionListItem [ ],int,String)", traceRet3, 3); 
/*     */       throw traceRet3;
/*     */     } 
/*     */     if (litems == null) {
/*     */       MQException traceRet4 = new MQException(2, 2155, this, "MQJI003");
/*     */       if (Trace.isOn)
/*     */         Trace.throwing(this, "com.ibm.mq.MQDistributionList", "<init>(MQQueueManager,MQDistributionListItem [ ],int,String)", traceRet4, 4); 
/*     */       throw traceRet4;
/*     */     } 
/*     */     for (int rec = 0; rec < litems.length; rec++) {
/*     */       if (litems[rec] == null) {
/*     */         MQException traceRet5 = new MQException(2, 2154, this, "MQJI006");
/*     */         if (Trace.isOn)
/*     */           Trace.throwing(this, "com.ibm.mq.MQDistributionList", "<init>(MQQueueManager,MQDistributionListItem [ ],int,String)", traceRet5, 5); 
/*     */         throw traceRet5;
/*     */       } 
/*     */       if (rec > 0)
/*     */         litems[rec].setPreviousDistributedItem(litems[rec - 1]); 
/*     */       if (rec < litems.length - 1)
/*     */         litems[rec].setNextDistributedItem(litems[rec + 1]); 
/*     */     } 
/*     */     this.items = litems;
/*     */     MQOD od = new MQOD(this.items);
/*     */     if (alternateUserId != null && alternateUserId.length() > 0)
/*     */       od.AlternateUserId = alternateUserId; 
/*     */     this.Hconn = qMgr.Hconn;
/*     */     this.connected = qMgr.connected;
/*     */     if (Trace.isOn)
/*     */       for (int i = 0; i < litems.length; i++) {
/*     */         String traceQMgr = (litems[i]).queueManagerName;
/*     */         String traceQ = (litems[i]).queueName;
/*     */         if (traceQMgr == null)
/*     */           traceQMgr = "<null>"; 
/*     */         if (traceQ == null)
/*     */           traceQ = "<null>"; 
/*     */         Trace.data(this, "<init>(MQQueueManager,MQDistributionListItem [ ],int,String)", "DistributionList: '", traceQMgr + "' '" + traceQ);
/*     */       }  
/*     */     JmqiMQ jmqi = this.osession.getJmqi();
/*     */     try {
/*     */       queueManagerCmdLevel = this.Hconn.getHconn().getCmdLevel();
/*     */     } catch (JmqiException e) {
/*     */       if (Trace.isOn)
/*     */         Trace.catchBlock(this, "com.ibm.mq.MQDistributionList", "<init>(MQQueueManager,MQDistributionListItem [ ],int,String)", (Throwable)e); 
/*     */       MQException traceRet6 = new MQException(e.getCompCode(), e.getReason(), this, e);
/*     */       if (Trace.isOn)
/*     */         Trace.throwing(this, "com.ibm.mq.MQDistributionList", "<init>(MQQueueManager,MQDistributionListItem [ ],int,String)", traceRet6, 6); 
/*     */       throw traceRet6;
/*     */     } 
/*     */     if (queueManagerCmdLevel >= 700) {
/*     */       JmqiSP jmqiSP = (JmqiSP)jmqi;
/*     */       SpiOpenOptions spiOpenOptions = ((JmqiSystemEnvironment)this.env).newSpiOpenOptions();
/*     */       spiOpenOptions.setOptions(openOptions);
/*     */       MQOD mqObjectDecriptor = od.getJMQIStructure();
/*     */       jmqiSP.spiOpen(this.Hconn.getHconn(), mqObjectDecriptor, spiOpenOptions, this.Hobj, this.completionCode, this.reason);
/*     */       od.updateFromJMQIStructure();
/*     */     } else {
/*     */       this.osession.MQOPEN(this.Hconn.getHconn(), od, openOptions, this.Hobj, this.completionCode, this.reason);
/*     */     } 
/*     */     if (this.completionCode.x == 2) {
/*     */       this.resourceOpen = false;
/*     */       this.isOpen = false;
/*     */       this.openStatus = false;
/*     */       MQException mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*     */       qMgr.errorOccurred(mqe);
/*     */       if (Trace.isOn)
/*     */         Trace.throwing(this, "com.ibm.mq.MQDistributionList", "<init>(MQQueueManager,MQDistributionListItem [ ],int,String)", mqe, 7); 
/*     */       throw mqe;
/*     */     } 
/*     */     this.resourceOpen = true;
/*     */     this.isOpen = true;
/*     */     this.openStatus = true;
/*     */     this.mgr = qMgr;
/*     */     this.openOptions = openOptions;
/*     */     this.parentQmgr = qMgr;
/*     */     this.connectionReference = qMgr;
/*     */     if (alternateUserId != null)
/*     */       this.alternateUserId = alternateUserId; 
/*     */     qMgr.registerDistributionList(this);
/*     */     this.knownDestCount = od.getKnownDestCount();
/*     */     this.unknownDestCount = od.getUnknownDestCount();
/*     */     this.invalidDestCount = od.getInvalidDestCount();
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.MQDistributionList", "<init>(MQQueueManager,MQDistributionListItem [ ],int,String)"); 
/*     */   }
/*     */   
/*     */   public synchronized void put(MQMessage message, MQPutMessageOptions putMessageOptions) throws MQException {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.MQDistributionList", "put(MQMessage,MQPutMessageOptions)", new Object[] { message, putMessageOptions }); 
/*     */     String fid = "put(MQMessage,MQPutMessageOptions)";
/*     */     MQMD putmd = message;
/*     */     if (message == null) {
/*     */       MQException traceRet1 = new MQException(2, 2026, this, "MQJI008");
/*     */       if (Trace.isOn)
/*     */         Trace.throwing(this, "com.ibm.mq.MQDistributionList", "put(MQMessage,MQPutMessageOptions)", traceRet1, 1); 
/*     */       throw traceRet1;
/*     */     } 
/*     */     if (putMessageOptions == null) {
/*     */       MQException traceRet2 = new MQException(2, 2173, this, "MQJI009");
/*     */       if (Trace.isOn)
/*     */         Trace.throwing(this, "com.ibm.mq.MQDistributionList", "put(MQMessage,MQPutMessageOptions)", traceRet2, 2); 
/*     */       throw traceRet2;
/*     */     } 
/*     */     if (!this.connected) {
/*     */       MQException traceRet3 = new MQException(2, 2018, this, "MQJI002");
/*     */       if (Trace.isOn)
/*     */         Trace.throwing(this, "com.ibm.mq.MQDistributionList", "put(MQMessage,MQPutMessageOptions)", traceRet3, 3); 
/*     */       throw traceRet3;
/*     */     } 
/*     */     if (!this.openStatus) {
/*     */       MQException traceRet4 = new MQException(2, 2019, this, "MQJI011");
/*     */       if (Trace.isOn)
/*     */         Trace.throwing(this, "com.ibm.mq.MQDistributionList", "put(MQMessage,MQPutMessageOptions)", traceRet4, 4); 
/*     */       throw traceRet4;
/*     */     } 
/*     */     synchronized (message) {
/*     */       MQException exception = null;
/*     */       try {
/*     */         int qmCcsid;
/*     */         try {
/*     */           qmCcsid = this.Hconn.getHconn().getCcsid();
/*     */         } catch (JmqiException e) {
/*     */           if (Trace.isOn)
/*     */             Trace.catchBlock(this, "com.ibm.mq.MQDistributionList", "put(MQMessage,MQPutMessageOptions)", (Throwable)e, 1); 
/*     */           qmCcsid = 0;
/*     */         } 
/*     */         message.performProcessingBeforePut(qmCcsid);
/*     */         ByteBuffer msgData = message.getBuffer();
/*     */         if (Trace.isOn) {
/*     */           Trace.data(this, "put(MQMessage,MQPutMessageOptions)", "message", "Message length = " + msgData.capacity() + " bytes.");
/*     */           Trace.data(this, "put(MQMessage,MQPutMessageOptions)", "put options = " + putMessageOptions.options + "\nmessage type = " + message.messageType + "\nencoding = " + message.encoding + "\ncharacter set = " + message.characterSet + "\nformat = " + message.format + "\nmessage id, correlation id, groupId  follow:", "");
/*     */           Trace.data(this, "put(MQMessage,MQPutMessageOptions)", "messageId", message.messageId);
/*     */           Trace.data(this, "put(MQMessage,MQPutMessageOptions)", "correlId", message.correlationId);
/*     */           Trace.data(this, "put(MQMessage,MQPutMessageOptions)", "grouId", message.groupId);
/*     */         } 
/*     */         if ((putMessageOptions.options & 0x300) != 0) {
/*     */           if (Trace.isOn)
/*     */             Trace.data(this, "put(MQMessage,MQPutMessageOptions)", "Checking Context Reference", ""); 
/*     */           if (putMessageOptions.contextReference != null) {
/*     */             putMessageOptions.contextReferenceHandle = putMessageOptions.contextReference.getHandle();
/*     */             if (putMessageOptions.contextReferenceHandle == -1) {
/*     */               MQException traceRet5 = new MQException(2, 2097, this);
/*     */               if (Trace.isOn)
/*     */                 Trace.throwing(this, "com.ibm.mq.MQDistributionList", "put(MQMessage,MQPutMessageOptions)", traceRet5, 5); 
/*     */               throw traceRet5;
/*     */             } 
/*     */             if (this.mgr != putMessageOptions.contextReference.mgr) {
/*     */               if (Trace.isOn)
/*     */                 Trace.data(this, "put(MQMessage,MQPutMessageOptions)", "MQDistributionList:put", "Connection references do not match"); 
/*     */               MQException traceRet6 = new MQException(2, 2097, this);
/*     */               if (Trace.isOn)
/*     */                 Trace.throwing(this, "com.ibm.mq.MQDistributionList", "put(MQMessage,MQPutMessageOptions)", traceRet6, 6); 
/*     */               throw traceRet6;
/*     */             } 
/*     */             if (Trace.isOn)
/*     */               Trace.data(this, "put(MQMessage,MQPutMessageOptions)", "reference handle", "Obtained context reference handle:" + putMessageOptions.contextReferenceHandle); 
/*     */           } else {
/*     */             if (Trace.isOn)
/*     */               Trace.data(this, "put(MQMessage,MQPutMessageOptions)", "Context reference queue is null", ""); 
/*     */             MQException traceRet7 = new MQException(2, 2097, this);
/*     */             if (Trace.isOn)
/*     */               Trace.throwing(this, "com.ibm.mq.MQDistributionList", "put(MQMessage,MQPutMessageOptions)", traceRet7, 7); 
/*     */             throw traceRet7;
/*     */           } 
/*     */         } 
/*     */         putMessageOptions.version2(this.items);
/*     */         this.osession.MQPUT(this.Hconn.getHconn(), this.Hobj.getHobj(), putmd, putMessageOptions, msgData.limit(), msgData, this.completionCode, this.reason);
/*     */         if (Trace.isOn)
/*     */           Trace.data(this, "put(MQMessage,MQPutMessageOptions)", "Returned message Id : ", message.messageId); 
/*     */         if (this.completionCode.x != 0 || this.reason.x != 0) {
/*     */           MQException mqe = new MQException(this.completionCode.x, this.reason.x, this, this.osession.getLastJmqiException());
/*     */           this.parentQmgr.errorOccurred(mqe);
/*     */           if (Trace.isOn)
/*     */             Trace.throwing(this, "com.ibm.mq.MQDistributionList", "put(MQMessage,MQPutMessageOptions)", mqe, 8); 
/*     */           throw mqe;
/*     */         } 
/*     */       } catch (MQException e) {
/*     */         if (Trace.isOn)
/*     */           Trace.catchBlock(this, "com.ibm.mq.MQDistributionList", "put(MQMessage,MQPutMessageOptions)", e, 2); 
/*     */         exception = e;
/*     */       } finally {
/*     */         if (Trace.isOn)
/*     */           Trace.finallyBlock(this, "com.ibm.mq.MQDistributionList", "put(MQMessage,MQPutMessageOptions)"); 
/*     */         try {
/*     */           message.performProcessingAfterPut();
/*     */         } catch (MQException e) {
/*     */           if (Trace.isOn)
/*     */             Trace.catchBlock(this, "com.ibm.mq.MQDistributionList", "put(MQMessage,MQPutMessageOptions)", e, 3); 
/*     */           if (exception == null)
/*     */             exception = e; 
/*     */         } 
/*     */         if (exception != null) {
/*     */           if (Trace.isOn)
/*     */             Trace.throwing(this, "com.ibm.mq.MQDistributionList", "put(MQMessage,MQPutMessageOptions)", exception, 9); 
/*     */           throw exception;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.MQDistributionList", "put(MQMessage,MQPutMessageOptions)"); 
/*     */   }
/*     */   
/*     */   public synchronized void close() throws MQException {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.MQDistributionList", "close()"); 
/*     */     super.close();
/*     */     if (this.mgr != null)
/*     */       this.mgr.unregisterDistributionList(this); 
/*     */     this.isOpen = false;
/*     */     this.openStatus = false;
/*     */     this.mgr = null;
/*     */     this.connectionReference = null;
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.MQDistributionList", "close()"); 
/*     */   }
/*     */   
/*     */   public int getValidDestinationCount() {
/*     */     int traceRet1 = this.knownDestCount + this.unknownDestCount;
/*     */     if (Trace.isOn)
/*     */       Trace.data(this, "com.ibm.mq.MQDistributionList", "getValidDestinationCount()", "getter", Integer.valueOf(traceRet1)); 
/*     */     return traceRet1;
/*     */   }
/*     */   
/*     */   public int getInvalidDestinationCount() {
/*     */     if (Trace.isOn)
/*     */       Trace.data(this, "com.ibm.mq.MQDistributionList", "getInvalidDestinationCount()", "getter", Integer.valueOf(this.invalidDestCount)); 
/*     */     return this.invalidDestCount;
/*     */   }
/*     */   
/*     */   public MQDistributionListItem getFirstDistributionListItem() {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.MQDistributionList", "getFirstDistributionListItem()"); 
/*     */     if (this.items == null || this.items.length == 0) {
/*     */       if (Trace.isOn)
/*     */         Trace.exit(this, "com.ibm.mq.MQDistributionList", "getFirstDistributionListItem()", null, 1); 
/*     */       return null;
/*     */     } 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.MQDistributionList", "getFirstDistributionListItem()", this.items[0], 2); 
/*     */     return this.items[0];
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQDistributionList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */