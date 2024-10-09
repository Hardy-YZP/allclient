/*      */ package com.ibm.msg.client.wmq.common.internal.messages;
/*      */ 
/*      */ import com.ibm.mq.constants.CMQC;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQRFH;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import com.ibm.msg.client.wmq.common.WMQThreadLocalStorage;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQCommonConnection;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQConsumerOwner;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQUtils;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import javax.jms.JMSException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class WMQSendMarshal
/*      */   extends WMQMarshal
/*      */ {
/*      */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQSendMarshal.java";
/*      */   private static JmqiCodepage cachedJmqiCodepage;
/*      */   
/*      */   static {
/*   69 */     if (Trace.isOn) {
/*   70 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQSendMarshal.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   91 */   private ByteBuffer[] messageBuffers = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   96 */   private int messageBuffersIndex = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean exported = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   WMQSendMarshal() {
/*  111 */     if (Trace.isOn) {
/*  112 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "<init>()");
/*      */     }
/*      */ 
/*      */     
/*  116 */     if (Trace.isOn) {
/*  117 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "<init>()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void constructMessageBuffers() throws JMSException {
/*  129 */     if (Trace.isOn) {
/*  130 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "constructMessageBuffers()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  135 */     int bodyEncoding = WMQMarshalUtils.calculateMessageBodyEncoding(this.destination, this.providerMessage);
/*      */     
/*  137 */     JmqiCodepage bodyCodepage = WMQMarshalUtils.getMessageCodepage(this.env, this.destination, this.providerMessage, bodyEncoding);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  142 */     this.messageBuffers = new ByteBuffer[2];
/*  143 */     this.messageBuffersIndex = 0;
/*      */ 
/*      */     
/*  146 */     writeMessageProperties(bodyEncoding, bodyCodepage);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  151 */     if (this.providerMessage instanceof WMQTextMessage) {
/*      */       
/*  153 */       WMQThreadLocalStorage tls = this.owner.getThreadLocalStorage();
/*  154 */       WMQTextMessage tm = (WMQTextMessage)this.providerMessage;
/*  155 */       ByteBuffer bodyByteBuffer = tm._exportBodyUsingTls(bodyEncoding, bodyCodepage, tls);
/*      */ 
/*      */       
/*  158 */       if (bodyByteBuffer != null) {
/*  159 */         this.messageBuffers[this.messageBuffersIndex++] = bodyByteBuffer;
/*      */ 
/*      */         
/*  162 */         if (Trace.isOn) {
/*  163 */           Trace.data(this, "constructMessageBuffers()", "Body of the message", bodyByteBuffer);
/*      */         
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  171 */       byte[] body = this.providerMessage._exportBody(bodyEncoding, bodyCodepage);
/*      */       
/*  173 */       if (body != null) {
/*  174 */         this.messageBuffers[this.messageBuffersIndex++] = ByteBuffer.wrap(body);
/*      */ 
/*      */         
/*  177 */         if (Trace.isOn) {
/*  178 */           Trace.data(this, "constructMessageBuffers()", "Body of the message", body);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  185 */     if (this.messageBuffersIndex > 0) {
/*  186 */       ByteBuffer[] tmp = new ByteBuffer[this.messageBuffersIndex];
/*  187 */       for (int i = 0; i < this.messageBuffersIndex; i++) {
/*  188 */         ByteBuffer current = this.messageBuffers[i];
/*  189 */         if (current != null) {
/*  190 */           tmp[i] = current;
/*      */         }
/*      */       } 
/*  193 */       this.messageBuffers = tmp;
/*      */     } else {
/*  195 */       this.messageBuffers = null;
/*      */     } 
/*      */     
/*  198 */     if (Trace.isOn) {
/*  199 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "constructMessageBuffers()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void constructMQMD() throws JMSException {
/*      */     HashMap<String, Object> info;
/*  212 */     if (Trace.isOn) {
/*  213 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "constructMQMD()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  219 */     this.mqmd = this.env.newMQMD();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  232 */     this.mqmd.setCodedCharSetId(1208);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  251 */     this.mqmd.setPriority(this.providerMessage.getJMSPriority().intValue());
/*      */ 
/*      */     
/*  254 */     byte[] correlIdBytes = this.providerMessage.getJMSCorrelationIDAsBytes();
/*      */     
/*  256 */     if (correlIdBytes != null) {
/*  257 */       this.mqmd.setCorrelId(correlIdBytes);
/*      */     } else {
/*  259 */       String correlIdString = this.providerMessage.getJMSCorrelationID();
/*      */       
/*  261 */       if (correlIdString == null) {
/*  262 */         this.mqmd.setCorrelId(CMQC.MQCI_NONE);
/*      */       } else {
/*      */         byte[] cid;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*  270 */           cid = WMQUtils.computeBytesFromText(correlIdString, 
/*  271 */               JmqiCodepage.getJmqiCodepage(this.env, 1208));
/*      */         }
/*  273 */         catch (NullPointerException e) {
/*  274 */           if (Trace.isOn) {
/*  275 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "constructMQMD()", e, 1);
/*      */           }
/*      */ 
/*      */           
/*  279 */           HashMap<String, Object> inserts = new HashMap<>();
/*  280 */           inserts.put("XMSC_INSERT_PROPERTY", "JMS_IBM_Character_Set");
/*  281 */           inserts.put("XMSC_INSERT_VALUE", Integer.valueOf(1208));
/*  282 */           JMSException je = (JMSException)NLSServices.createException("JMSCMQ1006", inserts);
/*  283 */           if (Trace.isOn) {
/*  284 */             Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "constructMQMD()", (Throwable)je, 1);
/*      */           }
/*      */           
/*  287 */           throw je;
/*      */         } 
/*  289 */         this.mqmd.setCorrelId(cid);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  297 */     int deliveryMode = this.providerMessage.getJMSDeliveryMode().intValue();
/*  298 */     switch (deliveryMode) {
/*      */       case 1:
/*  300 */         this.mqmd.setPersistence(0);
/*      */         break;
/*      */       case 2:
/*  303 */         this.mqmd.setPersistence(1);
/*      */         break;
/*      */       default:
/*  306 */         info = new HashMap<>();
/*  307 */         info.put("message", "Invalid DeliveryMode");
/*  308 */         info.put("DeliveryMode", Integer.valueOf(deliveryMode));
/*  309 */         Trace.ffst(this, "constructMQMD()", "XN007004", info, JMSException.class);
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  314 */     if (this.providerMessage instanceof WMQTextMessage || this.providerMessage instanceof WMQStreamMessage || this.providerMessage instanceof WMQMapMessage) {
/*      */ 
/*      */       
/*  317 */       this.mqmd.setFormat("MQSTR   ");
/*      */     } else {
/*  319 */       this.mqmd.setFormat("        ");
/*      */     } 
/*      */ 
/*      */     
/*  323 */     long expiration = this.providerMessage.getJMSExpiration().longValue();
/*  324 */     if (expiration == 0L) {
/*  325 */       this.mqmd.setExpiry(-1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  351 */       long expiry, expiryBaseTime = this.providerMessage.getTimeMillisFromWhichJMSExpirationWasBased();
/*  352 */       if (expiryBaseTime != -1L) {
/*  353 */         expiry = expiration - expiryBaseTime;
/*      */       } else {
/*  355 */         Long timestamp = this.providerMessage.getJMSTimestamp();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  366 */         if (timestamp == null) timestamp = Long.valueOf(0L);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  372 */         expiry = expiration - ((timestamp.longValue() > 0L) ? timestamp.longValue() : System.currentTimeMillis());
/*      */       } 
/*      */       
/*  375 */       expiry /= 100L;
/*      */ 
/*      */ 
/*      */       
/*  379 */       if (expiry < 1L) {
/*  380 */         this.mqmd.setExpiry(1);
/*  381 */       } else if (expiry < 2147483647L) {
/*  382 */         this.mqmd.setExpiry((int)expiry);
/*      */       } else {
/*  384 */         this.mqmd.setExpiry(-1);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  389 */     String replyToDestination = this.providerMessage.getJMSReplyToAsString();
/*      */     
/*  391 */     if (replyToDestination != null && replyToDestination.trim().length() > 0) {
/*  392 */       WMQDestinationURIParser wMQDestinationURIParser = new WMQDestinationURIParser(replyToDestination);
/*      */       
/*  394 */       if (wMQDestinationURIParser.getDomain() == 1) {
/*      */         
/*  396 */         String qName = wMQDestinationURIParser.getDestinationName();
/*  397 */         if (qName != null) {
/*  398 */           this.mqmd.setReplyToQ(qName);
/*      */         }
/*      */         
/*  401 */         String qMgrName = wMQDestinationURIParser.getQmName();
/*  402 */         if (qMgrName != null) {
/*  403 */           this.mqmd.setReplyToQMgr(qMgrName);
/*      */         }
/*      */         
/*  406 */         this.mqmd.setMsgType(1);
/*      */       } 
/*      */     } else {
/*      */       
/*  410 */       this.mqmd.setMsgType(8);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  417 */     Enumeration<String> props = this.providerMessage.getPropertyNames();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  422 */     WMQDestinationURIParser uriParser = new WMQDestinationURIParser(this.providerMessage.getJMSDestinationAsString());
/*  423 */     boolean isQueue = (uriParser.getDomain() == 1);
/*      */     
/*  425 */     String name = null;
/*  426 */     while (props.hasMoreElements()) {
/*  427 */       name = props.nextElement();
/*      */ 
/*      */       
/*  430 */       if (name.startsWith("JMS")) {
/*      */         
/*  432 */         if (Trace.isOn) {
/*  433 */           Trace.traceData(this, "constructMQMD()", "processing propery", name);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  441 */         if (isQueue && name.equals("JMSXGroupID")) {
/*      */           
/*  443 */           String jmsxGroupId = this.providerMessage.getStringProperty("JMSXGroupID");
/*      */           
/*  445 */           if (jmsxGroupId != null) {
/*  446 */             if (jmsxGroupId.startsWith("ID:")) {
/*  447 */               this.mqmd.setGroupId(WMQUtils.stringToId(jmsxGroupId));
/*      */             } else {
/*      */               byte[] gid;
/*      */               
/*      */               try {
/*  452 */                 gid = WMQUtils.computeBytesFromText(jmsxGroupId, 
/*  453 */                     JmqiCodepage.getJmqiCodepage(this.env, 1208));
/*      */               }
/*  455 */               catch (NullPointerException e) {
/*  456 */                 if (Trace.isOn) {
/*  457 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "constructMQMD()", e, 2);
/*      */                 }
/*      */ 
/*      */                 
/*  461 */                 HashMap<String, Object> inserts = new HashMap<>();
/*  462 */                 inserts.put("XMSC_INSERT_PROPERTY", "JMS_IBM_Character_Set");
/*  463 */                 inserts.put("XMSC_INSERT_VALUE", Integer.valueOf(1208));
/*  464 */                 JMSException je = (JMSException)NLSServices.createException("JMSCMQ1006", inserts);
/*  465 */                 if (Trace.isOn) {
/*  466 */                   Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "constructMQMD()", (Throwable)je, 2);
/*      */                 }
/*      */ 
/*      */                 
/*  470 */                 throw je;
/*      */               } 
/*  472 */               this.mqmd.setGroupId(gid);
/*      */             } 
/*      */             
/*  475 */             this.mqmd.setMsgFlags(this.mqmd.getMsgFlags() | 0x8);
/*      */             
/*  477 */             this.mqmd.setVersion(2); continue;
/*      */           } 
/*  479 */           if (Trace.isOn) {
/*  480 */             Trace.traceData(this, "constructMQMD()", "Ignoring null jmsxGroupId", null);
/*      */           }
/*      */ 
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/*  487 */         if (isQueue && name.equals("JMSXGroupSeq")) {
/*      */           
/*      */           try {
/*  490 */             int seqNumber = this.providerMessage.getIntProperty("JMSXGroupSeq");
/*  491 */             if (seqNumber > 0) {
/*  492 */               this.mqmd.setMsgSeqNumber(seqNumber);
/*  493 */               this.mqmd.setMsgFlags(this.mqmd.getMsgFlags() | 0x8);
/*      */               
/*  495 */               this.mqmd.setVersion(2); continue;
/*      */             } 
/*  497 */             if (Trace.isOn) {
/*  498 */               Trace.traceData(this, "constructMQMD()", "Ignoring invalid seqNumber", 
/*      */                   
/*  500 */                   Integer.valueOf(seqNumber));
/*      */             
/*      */             }
/*      */           }
/*  504 */           catch (NumberFormatException nfe) {
/*  505 */             if (Trace.isOn) {
/*  506 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "constructMQMD()", nfe, 3);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  512 */             if (Trace.isOn) {
/*  513 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "constructMQMD()", nfe);
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  518 */               String groupSeqNo = this.providerMessage.getStringProperty("JMSXGroupSeq");
/*  519 */               Trace.traceData(this, "constructMQMD()", "Ignoring invalid value for JMSXGroupSeq property.", groupSeqNo);
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           continue;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  531 */         if (name.startsWith("JMS_IBM_")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  538 */           if (name.equals("JMS_IBM_Format")) {
/*      */             
/*  540 */             String format = this.providerMessage.getStringProperty("JMS_IBM_Format");
/*  541 */             this.mqmd.setFormat(format); continue;
/*  542 */           }  if (name.equals("JMS_IBM_MsgType")) {
/*      */             
/*  544 */             int msgType = this.providerMessage.getIntProperty("JMS_IBM_MsgType");
/*  545 */             this.mqmd.setMsgType(msgType); continue;
/*  546 */           }  if (name.equals("JMS_IBM_Feedback")) {
/*  547 */             this.mqmd.setFeedback(this.providerMessage
/*  548 */                 .getIntProperty("JMS_IBM_Feedback"));
/*      */             
/*      */             continue;
/*      */           } 
/*  552 */           if (isQueue && name
/*  553 */             .equals("JMS_IBM_Last_Msg_In_Group")) {
/*  554 */             if (this.providerMessage
/*  555 */               .getBooleanProperty("JMS_IBM_Last_Msg_In_Group"))
/*  556 */               this.mqmd.setMsgFlags(this.mqmd.getMsgFlags() | 0x10); 
/*      */             continue;
/*      */           } 
/*  559 */           if (name.startsWith("JMS_IBM_Report")) {
/*      */             
/*  561 */             updateMQMDReport(name, "JMS_IBM_Report_Exception", 117440512);
/*      */ 
/*      */             
/*  564 */             updateMQMDReport(name, "JMS_IBM_Report_Expiration", 14680064);
/*      */ 
/*      */             
/*  567 */             updateMQMDReport(name, "JMS_IBM_Report_COA", 1792);
/*      */ 
/*      */             
/*  570 */             updateMQMDReport(name, "JMS_IBM_Report_COD", 14336);
/*      */ 
/*      */             
/*  573 */             updateMQMDReport(name, "JMS_IBM_Report_PAN", 1);
/*      */ 
/*      */             
/*  576 */             updateMQMDReport(name, "JMS_IBM_Report_NAN", 2);
/*      */ 
/*      */             
/*  579 */             updateMQMDReport(name, "JMS_IBM_Report_Pass_Msg_ID", 128);
/*      */ 
/*      */             
/*  582 */             updateMQMDReport(name, "JMS_IBM_Report_Pass_Correl_ID", 64);
/*      */ 
/*      */             
/*  585 */             updateMQMDReport(name, "JMS_IBM_Report_Discard_Msg", 134217728);
/*      */ 
/*      */             
/*  588 */             updateMQMDReport(name, "JMS_IBM_Report_Pass_Discard_And_Expiry", 16384);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  595 */     if (Trace.isOn) {
/*  596 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "constructMQMD()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void exportMQMDAndMessageBuffers() throws JMSException {
/*  603 */     if (Trace.isOn) {
/*  604 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "exportMQMDAndMessageBuffers()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  610 */     if (!this.exported) {
/*  611 */       if (Trace.isOn) {
/*  612 */         Trace.data(this, "exportMQMDAndMessageBuffers()", "Preparing MQMD and Message Buffers for export", null);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  617 */       if (!this.imported)
/*      */       {
/*  619 */         Trace.ffst(this, "exportMQMDAndMessageBuffers()", "XM006001", null, null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  626 */       this.providerMessage.setJMSRedelivered(false);
/*  627 */       this.providerMessage.setIntProperty("JMSXDeliveryCount", 0);
/*      */ 
/*      */ 
/*      */       
/*  631 */       constructMQMD();
/*      */ 
/*      */       
/*  634 */       updateMQMD();
/*      */ 
/*      */ 
/*      */       
/*  638 */       constructMessageBuffers();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  645 */       WMQMarshalUtils.overrideHeaderFromMQMDProps((ProviderDestination)this.destination, this.providerMessage, this.mqmd);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  651 */       this.exported = true;
/*      */     }
/*  653 */     else if (Trace.isOn) {
/*  654 */       Trace.data(this, "exportMQMDAndMessageBuffers()", "Using earlier exported MQMD and Message Buffers", null);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  659 */     if (Trace.isOn) {
/*  660 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "exportMQMDAndMessageBuffers()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ByteBuffer[] exportMessageBuffers() throws JMSException {
/*  673 */     if (Trace.isOn) {
/*  674 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "exportMessageBuffers()");
/*      */     }
/*      */ 
/*      */     
/*  678 */     exportMQMDAndMessageBuffers();
/*      */ 
/*      */     
/*  681 */     if (Trace.isOn) {
/*  682 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "exportMessageBuffers()", this.messageBuffers);
/*      */     }
/*      */     
/*  685 */     return this.messageBuffers;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQMD exportMQMD() throws JMSException {
/*  695 */     if (Trace.isOn) {
/*  696 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "exportMQMD()");
/*      */     }
/*      */ 
/*      */     
/*  700 */     exportMQMDAndMessageBuffers();
/*      */ 
/*      */     
/*  703 */     if (Trace.isOn) {
/*  704 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "exportMQMD()", this.mqmd);
/*      */     }
/*      */     
/*  707 */     return this.mqmd;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void importProviderMessage(WMQConsumerOwner anOwner, WMQMessage message, WMQDestination aDestination) {
/*  721 */     if (Trace.isOn) {
/*  722 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "importProviderMessage(WMQConsumerOwner,WMQMessage,WMQDestination)", new Object[] { anOwner, message, aDestination });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  727 */     if (anOwner == null || message == null || aDestination == null) {
/*  728 */       HashMap<String, Object> info = new HashMap<>();
/*  729 */       info.put("anOwner", anOwner);
/*  730 */       info.put("message", message);
/*  731 */       info.put("aDestination", aDestination);
/*  732 */       Trace.ffst(this, "importProviderMessage(WMQConsumerOwner,WMQMessage,WMQDestination)", "XN007001", info, null);
/*      */ 
/*      */ 
/*      */       
/*  736 */       if (Trace.isOn) {
/*  737 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "importProviderMessage(WMQConsumerOwner,WMQMessage,WMQDestination)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/*  745 */     resetSendState();
/*      */     
/*  747 */     this.imported = true;
/*      */     
/*  749 */     this.owner = anOwner;
/*  750 */     this.providerMessage = message;
/*  751 */     this.destination = aDestination;
/*      */     
/*  753 */     this.env = this.owner.getJmqiEnvironment();
/*  754 */     if (this.env == null) {
/*  755 */       Trace.ffst(this, "importProviderMessage(WMQConsumerOwner,WMQMessage,WMQDestination)", "XN00L001", null, null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  761 */     if (Trace.isOn) {
/*  762 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "importProviderMessage(WMQConsumerOwner,WMQMessage,WMQDestination)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void resetSendState() {
/*  775 */     if (Trace.isOn) {
/*  776 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "resetSendState()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  781 */     resetState();
/*      */ 
/*      */     
/*  784 */     this.messageBuffers = null;
/*  785 */     this.messageBuffersIndex = 0;
/*  786 */     this.exported = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  792 */     if (Trace.isOn) {
/*  793 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "resetSendState()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateMQMD() throws JMSException {
/*      */     HashMap<String, Object> info;
/*  821 */     if (Trace.isOn) {
/*  822 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "updateMQMD()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  831 */     int destinationPriority = this.destination.getIntProperty("priority");
/*  832 */     if (destinationPriority == -1) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  837 */       this.providerMessage.setJMSPriority(4);
/*      */       
/*  839 */       this.mqmd.setPriority(-1);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  846 */     int destinationPersistence = this.destination.getIntProperty("deliveryMode");
/*  847 */     switch (destinationPersistence) {
/*      */       case -2:
/*      */       case 1:
/*      */       case 2:
/*      */         break;
/*      */       
/*      */       case -1:
/*  854 */         this.providerMessage
/*  855 */           .setJMSDeliveryMode(-2);
/*  856 */         this.mqmd.setPersistence(2);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 3:
/*  862 */         if (!this.destination.isNPHighCheckDone()) {
/*  863 */           WMQCommonConnection connection = this.owner.getConnection();
/*  864 */           if (connection == null) {
/*  865 */             HashMap<String, Object> hashMap = new HashMap<>();
/*  866 */             hashMap.put("methodName", "updateMD()");
/*  867 */             hashMap.put("message", "Connection is null");
/*  868 */             hashMap.put("destination", this.destination);
/*  869 */             hashMap.put("MQMD", this.mqmd);
/*  870 */             hashMap.put("WMQMessage", this.providerMessage);
/*  871 */             Trace.ffst(this, "updateMD()", "XN00L002", hashMap, JMSException.class);
/*      */           } 
/*      */           
/*  874 */           boolean persistenceFromMD = true;
/*  875 */           if (connection != null) {
/*  876 */             persistenceFromMD = connection.getPersistenceFromMD();
/*      */           }
/*      */           
/*  879 */           boolean targetClientMQ = (this.destination.getIntProperty("targetClient") == 1);
/*      */ 
/*      */           
/*  882 */           if (!persistenceFromMD && !targetClientMQ && 
/*  883 */             WMQUtils.isNPMClassHigh(this.owner, this.destination)) {
/*  884 */             this.destination.setNPHighSupported(true);
/*      */           } else {
/*  886 */             this.destination.setNPHighSupported(false);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  895 */         if (this.destination.getNPHighSupported()) {
/*  896 */           this.mqmd.setPersistence(0);
/*      */         }
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       default:
/*  905 */         info = new HashMap<>();
/*  906 */         info.put("methodName", "updateMD()");
/*  907 */         info.put("message", "Invalid value for WMQ_PERSISTENCE");
/*  908 */         info.put("destination", this.destination);
/*  909 */         info.put("persistence", Integer.valueOf(destinationPersistence));
/*  910 */         info.put("MQMD", this.mqmd);
/*  911 */         info.put("WMQMessage", this.providerMessage);
/*  912 */         Trace.ffst(this, "updateMD()", "XN00L003", info, JMSException.class);
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  923 */     if (this.destination.getBooleanProperty("mdWriteEnabled") == true && this.providerMessage
/*  924 */       .propertyExists("JMS_IBM_MQMD_Encoding")) {
/*      */       
/*  926 */       int value = this.providerMessage.getIntProperty("JMS_IBM_MQMD_Encoding");
/*  927 */       this.mqmd.setEncoding(value);
/*      */     } 
/*      */     
/*  930 */     if (Trace.isOn) {
/*  931 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "updateMQMD()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateMQMDReport(String namedProperty, String reportProperty, int mask) throws JMSException {
/*  948 */     if (Trace.isOn) {
/*  949 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "updateMQMDReport(String,String,int)", new Object[] { namedProperty, reportProperty, 
/*      */             
/*  951 */             Integer.valueOf(mask) });
/*      */     }
/*  953 */     if (namedProperty.equals(reportProperty)) {
/*  954 */       int oldReport = this.mqmd.getReport();
/*  955 */       int enableValue = this.providerMessage.getIntProperty(reportProperty);
/*  956 */       int newReport = oldReport | enableValue & mask;
/*  957 */       this.mqmd.setReport(newReport);
/*      */     } 
/*  959 */     if (Trace.isOn) {
/*  960 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "updateMQMDReport(String,String,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateProviderMessageFromMQMD(MQMD newMQMD) throws JMSException {
/*  977 */     if (Trace.isOn) {
/*  978 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "updateProviderMessageFromMQMD(MQMD)", new Object[] { newMQMD });
/*      */     }
/*      */ 
/*      */     
/*  982 */     if (newMQMD == null) {
/*  983 */       Trace.ffst(this, "updateProviderMessageFromMQMD(MQMD)", "XN007002", null, JMSException.class);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  988 */     this.mqmd = newMQMD;
/*      */ 
/*      */     
/*  991 */     updateProviderMessageFromMQMD();
/*      */     
/*  993 */     if (Trace.isOn) {
/*  994 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "updateProviderMessageFromMQMD(MQMD)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean areMessagePropertiesRequired() throws JMSException {
/*      */     int targetClient;
/*      */     HashMap<String, Object> info;
/* 1008 */     if (Trace.isOn) {
/* 1009 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "areMessagePropertiesRequired()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1014 */     boolean propsRequired = true;
/*      */ 
/*      */     
/* 1017 */     int messageBodyStyle = this.destination.getIntProperty("messageBody");
/* 1018 */     switch (messageBodyStyle) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/* 1024 */         targetClient = this.destination.getIntProperty("targetClient");
/* 1025 */         if (targetClient == 0) {
/* 1026 */           propsRequired = true; break;
/* 1027 */         }  if (targetClient == 1) {
/* 1028 */           propsRequired = false;
/*      */           break;
/*      */         } 
/* 1031 */         info = new HashMap<>();
/* 1032 */         info.put("targetClient", 
/* 1033 */             Integer.valueOf(targetClient));
/* 1034 */         Trace.ffst(this, "areMessagePropertiesRequired()", "XN00L004", info, JMSException.class);
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 0:
/* 1042 */         propsRequired = true;
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 1:
/* 1048 */         propsRequired = false;
/*      */         break;
/*      */       
/*      */       default:
/* 1052 */         info = new HashMap<>();
/* 1053 */         info.put("messageBody", 
/* 1054 */             Integer.valueOf(messageBodyStyle));
/* 1055 */         Trace.ffst(this, "areMessagePropertiesRequired()", "XN00L005", info, JMSException.class);
/*      */         break;
/*      */     } 
/*      */     
/* 1059 */     if (Trace.isOn) {
/* 1060 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "areMessagePropertiesRequired()", 
/* 1061 */           Boolean.valueOf(propsRequired));
/*      */     }
/* 1063 */     return propsRequired;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeMQRFH2(int messageBodyEncoding, int messageBodyCcsid) throws JMSException {
/* 1075 */     if (Trace.isOn)
/* 1076 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "writeMQRFH2(int,int)", new Object[] {
/* 1077 */             Integer.valueOf(messageBodyEncoding), 
/* 1078 */             Integer.valueOf(messageBodyCcsid)
/*      */           }); 
/* 1080 */     boolean persistenceFromMD = WMQMarshalUtils.getPersistenceFromMD(this.owner);
/* 1081 */     String messageBodyFormat = this.mqmd.getFormat();
/*      */     
/* 1083 */     JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)this.env;
/*      */ 
/*      */     
/* 1086 */     WMQThreadLocalStorage tls = this.owner.getThreadLocalStorage();
/* 1087 */     JmqiTls jTls = sysenv.getJmqiTls((JmqiComponentTls)tls);
/*      */ 
/*      */     
/* 1090 */     MQRFH rfh = null;
/* 1091 */     if (tls.mqrfh == null) {
/* 1092 */       rfh = this.env.newMQRFH(5);
/* 1093 */       rfh.setVersion(2);
/*      */ 
/*      */       
/* 1096 */       tls.mqrfh = rfh;
/*      */     } else {
/* 1098 */       rfh = tls.mqrfh;
/*      */     } 
/* 1100 */     rfh.setEncoding(messageBodyEncoding);
/* 1101 */     rfh.setCodedCharSetId(messageBodyCcsid);
/* 1102 */     rfh.setFormat(messageBodyFormat);
/*      */ 
/*      */     
/* 1105 */     setRfhFolder(rfh, 0, (String)null, this.providerMessage._getMcdFolder());
/* 1106 */     setRfhFolder(rfh, 1, (String)null, this.providerMessage
/* 1107 */         ._getJmsFolder(persistenceFromMD));
/* 1108 */     setRfhFolder(rfh, 2, (String)null, this.providerMessage._getMQExtFolder());
/* 1109 */     setRfhFolder(rfh, 3, "<usr></usr>", this.providerMessage._getUsrFolder());
/*      */ 
/*      */     
/* 1112 */     boolean swap = false;
/* 1113 */     if ((this.mqmd.getEncoding() & 0xF) == 2) {
/* 1114 */       swap = true;
/*      */     }
/*      */     
/* 1117 */     int ptrSize = 0;
/*      */     
/* 1119 */     JmqiMQ mq = this.owner.getJmqiMQ();
/*      */     
/* 1121 */     if (cachedJmqiCodepage == null) {
/*      */       
/*      */       try {
/* 1124 */         cachedJmqiCodepage = JmqiCodepage.getJmqiCodepage(this.env, this.mqmd
/* 1125 */             .getCodedCharSetId());
/*      */         
/* 1127 */         if (cachedJmqiCodepage == null)
/*      */         {
/* 1129 */           UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException(String.valueOf(this.mqmd.getCodedCharSetId()));
/* 1130 */           if (Trace.isOn) {
/* 1131 */             Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "writeMQRFH2(int,int)", traceRet1, 1);
/*      */           }
/*      */           
/* 1134 */           throw traceRet1;
/*      */         }
/*      */       
/*      */       }
/* 1138 */       catch (UnsupportedEncodingException e) {
/* 1139 */         if (Trace.isOn) {
/* 1140 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "writeMQRFH2(int,int)", e, 1);
/*      */         }
/*      */         
/* 1143 */         HashMap<String, Object> inserts = new HashMap<>();
/* 1144 */         inserts.put("XMSC_INSERT_PROPERTY", "JMS_IBM_Character_Set");
/* 1145 */         inserts.put("XMSC_INSERT_VALUE", Integer.valueOf(this.mqmd.getCodedCharSetId()));
/* 1146 */         JMSException je = (JMSException)NLSServices.createException("JMSCMQ1006", inserts);
/* 1147 */         if (Trace.isOn) {
/* 1148 */           Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "writeMQRFH2(int,int)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 1151 */         throw je;
/*      */       } 
/*      */     }
/*      */     
/* 1155 */     int size = 0;
/*      */     try {
/* 1157 */       size = rfh.getRequiredBufferSize(mq, ptrSize, cachedJmqiCodepage, swap);
/*      */       
/* 1159 */       if (size > tls.rfhBuffer.length) {
/* 1160 */         tls.rfhBuffer = new byte[size];
/*      */       }
/*      */       
/* 1163 */       rfh.writeToBuffer(tls.rfhBuffer, 0, ptrSize, swap, cachedJmqiCodepage, jTls);
/*      */     
/*      */     }
/* 1166 */     catch (JmqiException e) {
/* 1167 */       if (Trace.isOn) {
/* 1168 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "writeMQRFH2(int,int)", (Throwable)e, 2);
/*      */       }
/*      */       
/* 1171 */       HashMap<String, Object> info = new HashMap<>();
/* 1172 */       info.put("messageBodyEncoding", 
/* 1173 */           Integer.valueOf(messageBodyEncoding));
/* 1174 */       info.put("messageBodyCcsid", Integer.valueOf(messageBodyCcsid));
/* 1175 */       info.put("messageBodyFormat", messageBodyFormat);
/* 1176 */       info.put("swap", Boolean.valueOf(swap));
/* 1177 */       info.put("size", Integer.valueOf(size));
/* 1178 */       info.put("MQMD", (this.mqmd != null) ? this.mqmd.toString() : "mqmd is null");
/* 1179 */       info.put("MQRFH", rfh.toString());
/* 1180 */       info.put("JmqiCodepage", (cachedJmqiCodepage != null) ? cachedJmqiCodepage
/*      */ 
/*      */           
/* 1183 */           .toString() : "jmqiCodePage is null");
/* 1184 */       info.put("rfhBuffer length", (tls.rfhBuffer != null) ? ("" + tls.rfhBuffer.length) : "rfhBuffer is null");
/*      */       
/* 1186 */       info.put("WMQMessage", (this.providerMessage != null) ? this.providerMessage
/* 1187 */           .toString() : "providerMessage is null");
/*      */       
/* 1189 */       info.put("WMQDestination", (this.destination != null) ? this.destination
/* 1190 */           .toURI() : "destination is null");
/*      */       
/* 1192 */       WMQMarshalUtils.addInfo(e, info);
/* 1193 */       Trace.ffst(this, JmqiTools.getExSumm((Throwable)e), "XN00L006", info, JMSException.class);
/*      */     } 
/*      */ 
/*      */     
/* 1197 */     if (tls.rfhBuffer != null)
/*      */     {
/* 1199 */       this.messageBuffers[this.messageBuffersIndex++] = ByteBuffer.wrap(tls.rfhBuffer, 0, size);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1204 */     if (Trace.isOn) {
/* 1205 */       byte[] traceData = new byte[size];
/* 1206 */       System.arraycopy(tls.rfhBuffer, 0, traceData, 0, size);
/* 1207 */       Trace.data(this, "constructMessageBuffers()", "MQRFH2 header for the message", traceData);
/*      */     } 
/*      */     
/* 1210 */     if (Trace.isOn) {
/* 1211 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "writeMQRFH2(int,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setRfhFolder(MQRFH rfh, int index, String empty, String folderString) {
/* 1227 */     String folderData = null;
/*      */     
/* 1229 */     if (folderString != null) {
/* 1230 */       folderData = folderString;
/*      */       
/* 1232 */       if (folderString.equals(empty)) {
/* 1233 */         folderData = null;
/*      */       }
/*      */     } 
/*      */     
/* 1237 */     rfh.setNameValueData(index, folderData);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeMessageProperties(int bodyEncoding, JmqiCodepage bodyCodepage) throws JMSException {
/* 1252 */     if (Trace.isOn) {
/* 1253 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "writeMessageProperties(int,JmqiCodepage)", new Object[] {
/* 1254 */             Integer.valueOf(bodyEncoding), bodyCodepage
/*      */           });
/*      */     }
/* 1257 */     boolean messagePropertiesRequired = areMessagePropertiesRequired();
/*      */     
/* 1259 */     if (messagePropertiesRequired == true) {
/*      */       
/* 1261 */       writeMQRFH2(bodyEncoding, bodyCodepage.getCCSID());
/*      */ 
/*      */ 
/*      */       
/* 1265 */       this.mqmd.setFormat("MQHRF2  ");
/*      */     }
/*      */     else {
/*      */       
/* 1269 */       this.mqmd.setEncoding(bodyEncoding);
/* 1270 */       this.mqmd.setCodedCharSetId(bodyCodepage.getCCSID());
/*      */     } 
/* 1272 */     if (Trace.isOn) {
/* 1273 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "writeMessageProperties(int,JmqiCodepage)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void informSendMarshal(MQMD md) throws JMSException {
/* 1288 */     if (Trace.isOn) {
/* 1289 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "informSendMarshal(MQMD)", new Object[] { md });
/*      */     }
/*      */     
/* 1292 */     updateProviderMessageFromMQMD(md);
/*      */     
/* 1294 */     resetSendState();
/*      */ 
/*      */     
/* 1297 */     if (Trace.isOn)
/* 1298 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal", "informSendMarshal(MQMD)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQSendMarshal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */