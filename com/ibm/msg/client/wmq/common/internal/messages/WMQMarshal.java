/*     */ package com.ibm.msg.client.wmq.common.internal.messages;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.MQMD;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderDestination;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQCommonSession;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQConsumerOwner;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQUtils;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.HashMap;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class WMQMarshal
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQMarshal.java";
/*     */   public static final String trimJMSXUserID = "com.ibm.mq.jms.trimJMSXUserID";
/*     */   
/*     */   static {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshal", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQMarshal.java");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     PropertyStore.register("com.ibm.mq.jms.trimJMSXUserID", false);
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
/*     */   protected boolean imported = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   protected MQMD mqmd = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   protected byte[] msgToken = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   protected WMQMessage providerMessage = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   protected WMQDestination destination = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   protected WMQConsumerOwner owner = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   protected JmqiEnvironment env = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static WMQSendMarshal newSendMarshal() {
/* 133 */     WMQSendMarshal sendMarshal = new WMQSendMarshal();
/* 134 */     return sendMarshal;
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
/*     */   public static WMQSendMarshal newSendMarshal(WMQConsumerOwner owner, boolean forceRFH2) {
/* 146 */     boolean useInternalFormat = forceRFH2 ? false : WMQMarshalUtils.useInternalFormat(owner);
/*     */     
/* 148 */     WMQSendMarshal sendMarshal = useInternalFormat ? new WMQSendMarshalMH() : new WMQSendMarshal();
/*     */     
/* 150 */     return sendMarshal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static WMQReceiveMarshal newReceiveMarshal() {
/* 160 */     WMQReceiveMarshal receiveMarshal = new WMQReceiveMarshal();
/* 161 */     return receiveMarshal;
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
/*     */   public static WMQReceiveMarshal newReceiveMarshal(WMQConsumerOwner owner, boolean forceRFH2) {
/* 173 */     boolean useInternalFormat = forceRFH2 ? false : WMQMarshalUtils.useInternalFormat(owner);
/*     */     
/* 175 */     WMQReceiveMarshal receiveMarshal = useInternalFormat ? new WMQReceiveMarshalMH() : new WMQReceiveMarshal();
/*     */     
/* 177 */     return receiveMarshal;
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
/*     */   public static WMQReceiveMarshal newReceiveMarshal(WMQCommonSession session, ByteBuffer buffer, int offset) {
/* 190 */     if (session == null || buffer == null || buffer.array() == null || offset < 0) {
/* 191 */       Trace.ffst("WMQMarshal", "newReceiveMarshal(WMQSession,ByteBuffer,int)", "XM00A001", null, null);
/* 192 */       return null;
/*     */     } 
/*     */     
/* 195 */     JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)session.getJmqiEnvironment();
/* 196 */     JmqiDC dc = sysenv.getDC();
/*     */ 
/*     */     
/* 199 */     int strucID = dc.readI32(buffer.array(), offset, false);
/*     */     
/* 201 */     WMQReceiveMarshal receiveMarshal = (strucID == 1515341392) ? new WMQReceiveMarshalMH() : new WMQReceiveMarshal();
/*     */     
/* 203 */     return receiveMarshal;
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
/*     */   protected synchronized void resetState() {
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMarshal", "resetState()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 223 */     this.imported = false;
/*     */     
/* 225 */     this.mqmd = null;
/* 226 */     this.owner = null;
/* 227 */     this.env = null;
/* 228 */     this.providerMessage = null;
/* 229 */     this.destination = null;
/*     */     
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMarshal", "resetState()");
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
/*     */   
/*     */   protected synchronized void updateProviderMessageFromMQMD() throws JMSException {
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMarshal", "updateProviderMessageFromMQMD()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 288 */       WMQMarshalUtils.setMQMDPropsFromHeader((ProviderDestination)this.destination, this.providerMessage, this.mqmd);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 296 */       int priority = this.mqmd.getPriority();
/* 297 */       if (priority >= 0) {
/* 298 */         this.providerMessage.setJMSPriority(priority);
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 306 */         if (Trace.isOn) {
/* 307 */           Trace.data(this, "updateProviderMessageFromMQMD()", "Unexpected or AS_Q_DEF value for MQMD.Priority", Integer.valueOf(priority));
/*     */         }
/* 309 */         this.providerMessage.setJMSPriority(priority);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 328 */       boolean persistenceFromMD = WMQMarshalUtils.getPersistenceFromMD(this.owner);
/* 329 */       if (this.providerMessage.getJMSDeliveryMode().intValue() == -2 || persistenceFromMD == true) {
/* 330 */         int persistence = this.mqmd.getPersistence();
/* 331 */         if (persistence == 0) {
/* 332 */           this.providerMessage.setJMSDeliveryMode(1);
/*     */         }
/* 334 */         else if (persistence == 1) {
/* 335 */           this.providerMessage.setJMSDeliveryMode(2);
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 341 */           if (Trace.isOn) {
/* 342 */             Trace.data(this, "updateProviderMessageFromMQMD()", "Unexpected or AS_Q_DEF value for MQMD.Persistence", 
/*     */ 
/*     */                 
/* 345 */                 Integer.valueOf(persistence));
/*     */           }
/* 347 */           this.providerMessage.setJMSDeliveryMode(-1);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 352 */       byte[] messageId = this.mqmd.getMsgId();
/* 353 */       if (messageId != null) {
/* 354 */         String messageIdString = WMQUtils.idToString(messageId);
/* 355 */         if (messageIdString != null) {
/* 356 */           this.providerMessage.setJMSMessageID(messageIdString);
/*     */         
/*     */         }
/* 359 */         else if (Trace.isOn) {
/* 360 */           Trace.data(this, "updateProviderMessageFromMQMD()", "String value for MQMD.MsgId is null", null);
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 365 */       else if (Trace.isOn) {
/* 366 */         Trace.data(this, "updateProviderMessageFromMQMD()", "byte[] value for MQMD.MsgId is null", null);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 371 */       String userId = this.mqmd.getUserIdentifier();
/* 372 */       if (userId != null) {
/*     */         
/* 374 */         Boolean isTrim = PropertyStore.getBooleanPropertyObject("com.ibm.mq.jms.trimJMSXUserID");
/* 375 */         if (isTrim != null && isTrim.booleanValue()) {
/* 376 */           userId = userId.trim();
/* 377 */           if (Trace.isOn) {
/* 378 */             Trace.data(this, "updateProviderMessageFromMQMD()", "JMXUserID without pad", userId);
/*     */           }
/*     */         } 
/* 381 */         this.providerMessage.setStringProperty("JMSXUserID", userId);
/*     */       
/*     */       }
/* 384 */       else if (Trace.isOn) {
/* 385 */         Trace.data(this, "updateProviderMessageFromMQMD()", "String value for MQMD.UserIdentifier is null", null);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 390 */       String appIdString = this.mqmd.getPutApplName();
/* 391 */       if (appIdString != null) {
/* 392 */         this.providerMessage.setStringProperty("JMSXAppID", appIdString);
/*     */       
/*     */       }
/* 395 */       else if (Trace.isOn) {
/* 396 */         Trace.data(this, "updateProviderMessageFromMQMD()", "String value for MQMD.PutApplName is null", null);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 401 */       int messageFlags = this.mqmd.getMsgFlags();
/* 402 */       if (messageFlags != 0) {
/* 403 */         if ((messageFlags & 0x8) != 0 || (messageFlags & 0x10) != 0)
/*     */         {
/*     */           
/* 406 */           int seqNumber = this.mqmd.getMsgSeqNumber();
/* 407 */           this.providerMessage.setIntProperty("JMSXGroupSeq", seqNumber);
/*     */ 
/*     */           
/* 410 */           byte[] groupId = this.mqmd.getGroupId();
/* 411 */           if (groupId != null && 
/* 412 */             this.providerMessage.getStringProperty("JMSXGroupID") == null) {
/* 413 */             String groupIDString = WMQUtils.idToString(groupId);
/* 414 */             if (groupIDString != null) {
/* 415 */               this.providerMessage.setStringProperty("JMSXGroupID", groupIDString);
/*     */             }
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 422 */       else if (Trace.isOn) {
/*     */         
/* 424 */         Trace.data(this, "updateProviderMessageFromMQMD()", "Numeric value for MQMD.MsgFlags is MQMF_NONE", Integer.valueOf(messageFlags));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 429 */       String putDate = this.mqmd.getPutDate();
/* 430 */       if (putDate != null) {
/* 431 */         this.providerMessage.setStringProperty("JMS_IBM_PutDate", putDate);
/*     */       
/*     */       }
/* 434 */       else if (Trace.isOn) {
/* 435 */         Trace.data(this, "updateProviderMessageFromMQMD()", "String value for MQMD.PutDate is null", null);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 440 */       String putTime = this.mqmd.getPutTime();
/* 441 */       if (putTime != null) {
/* 442 */         this.providerMessage.setStringProperty("JMS_IBM_PutTime", putTime);
/*     */       
/*     */       }
/* 445 */       else if (Trace.isOn) {
/* 446 */         Trace.data(this, "updateProviderMessageFromMQMD()", "String value for MQMD.PutTime is null", null);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 451 */       this.providerMessage.setIntProperty("JMS_IBM_PutApplType", this.mqmd.getPutApplType());
/*     */     
/*     */     }
/* 454 */     catch (Exception e) {
/* 455 */       if (Trace.isOn) {
/* 456 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMarshal", "updateProviderMessageFromMQMD()", e);
/*     */       }
/*     */       
/* 459 */       HashMap<String, Object> inserts = new HashMap<>();
/* 460 */       inserts.put("XMSC_MESSAGE_ID", this.providerMessage.getJMSMessageID());
/* 461 */       inserts.put("XMSC_CORRELATION_ID", this.providerMessage.getJMSCorrelationID());
/*     */       
/* 463 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ1000", inserts);
/* 464 */       je.setLinkedException(e);
/* 465 */       if (Trace.isOn) {
/* 466 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMarshal", "updateProviderMessageFromMQMD()", (Throwable)je);
/*     */       }
/*     */       
/* 469 */       throw je;
/*     */     } 
/* 471 */     if (Trace.isOn)
/* 472 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMarshal", "updateProviderMessageFromMQMD()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQMarshal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */