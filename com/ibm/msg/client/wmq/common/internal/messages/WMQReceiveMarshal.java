/*      */ package com.ibm.msg.client.wmq.common.internal.messages;
/*      */ 
/*      */ import com.ibm.mq.jmqi.MQGMO;
/*      */ import com.ibm.mq.jmqi.MQHeader;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQRFH;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.Utils;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.wmq.common.WMQThreadLocalStorage;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQCommonConnection;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQConsumerOwner;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDateConverter;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQUtils;
/*      */ import java.nio.ByteBuffer;
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
/*      */ public class WMQReceiveMarshal
/*      */   extends WMQMarshal
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQReceiveMarshal.java";
/*      */   
/*      */   static {
/*   62 */     if (Trace.isOn) {
/*   63 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQReceiveMarshal.java");
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
/*   75 */   private static JmqiCodepage cachedJmqiCodepage1 = null;
/*   76 */   private static JmqiCodepage cachedJmqiCodepage2 = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   81 */   private static int cachedCcsid1 = -1;
/*   82 */   private static int cachedCcsid2 = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   92 */   protected byte[] buffer = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   98 */   private int bufferEnd = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  103 */   private int bufferStart = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  110 */   protected int ccsidFromLastHeaderBeforeBody = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  117 */   protected int encodingFromLastHeaderBeforeBody = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  124 */   protected String formatFromLastHeaderBeforeBody = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean hasMessageProperties = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  134 */   private int defaultReplyToStyle = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   WMQReceiveMarshal() {
/*  142 */     if (Trace.isOn) {
/*  143 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "<init>()");
/*      */     }
/*      */ 
/*      */     
/*  147 */     this.defaultReplyToStyle = WMQMarshalUtils.getDefaultReplyToStyle();
/*      */     
/*  149 */     if (Trace.isOn) {
/*  150 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "<init>()");
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
/*      */   protected int constructProviderMessageFromProperties(int messageBodyStyle) throws JMSException {
/*  166 */     return constructProviderMessageFromRFH2(messageBodyStyle);
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
/*      */   private int constructProviderMessageFromRFH2(int messageBodyStyle) throws JMSException {
/*  178 */     if (Trace.isOn) {
/*  179 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "constructProviderMessageFromRFH2(int)", new Object[] {
/*  180 */             Integer.valueOf(messageBodyStyle)
/*      */           });
/*      */     }
/*  183 */     int pos = 0;
/*      */ 
/*      */     
/*      */     try {
/*  187 */       String mcdFolder = null;
/*  188 */       String usrFolder = null;
/*  189 */       String jmsFolder = null;
/*  190 */       String mqextFolder = null;
/*  191 */       String mqpsFolder = null;
/*      */       
/*  193 */       boolean jmsFolderParsed = false;
/*  194 */       int ptrSize = 4;
/*  195 */       this.hasMessageProperties = false;
/*      */ 
/*      */ 
/*      */       
/*  199 */       JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)this.env;
/*  200 */       WMQThreadLocalStorage tls = this.owner.getThreadLocalStorage();
/*  201 */       JmqiTls jTls = sysenv.getJmqiTls((JmqiComponentTls)tls);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  206 */       MQRFH rfh = this.env.newMQRFH(10);
/*  207 */       MQHeader header = rfh.getMqHeader();
/*      */ 
/*      */       
/*  210 */       header.setFormat(this.mqmd.getFormat());
/*      */ 
/*      */ 
/*      */       
/*  214 */       int encoding = this.mqmd.getEncoding();
/*  215 */       int previousCcsid = this.owner.getHconn().getCcsid();
/*  216 */       int ccsid = this.mqmd.getCodedCharSetId();
/*      */ 
/*      */ 
/*      */       
/*  220 */       if (cachedCcsid1 == ccsid && cachedJmqiCodepage1 != null) {
/*      */         
/*  222 */         JmqiCodepage cp = cachedJmqiCodepage1;
/*      */       } else {
/*      */         
/*  225 */         JmqiCodepage cp = JmqiCodepage.getJmqiCodepage(this.env, ccsid);
/*  226 */         if (cp == null) {
/*  227 */           WMQMarshalUtils.throwBadCcsidException(ccsid);
/*      */         }
/*      */         
/*  230 */         cachedCcsid1 = ccsid;
/*  231 */         cachedJmqiCodepage1 = cp;
/*      */       } 
/*      */ 
/*      */       
/*  235 */       JmqiCodepage hconnCP = JmqiCodepage.getJmqiCodepage(this.env, this.owner.getHconn().getCcsid());
/*  236 */       if (hconnCP == null) {
/*  237 */         WMQMarshalUtils.throwBadCcsidException(this.owner.getHconn().getCcsid());
/*      */       }
/*      */       
/*  240 */       long formatLong = MQHeader.convertFormatToLong(this.env, hconnCP, this.mqmd.getFormat());
/*  241 */       long mqheader = formatLong & 0xFFFFFF0000000000L;
/*      */       
/*  243 */       if (Trace.isOn) {
/*  244 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.messages.WMQReceiveMarshal", "Format:    " + this.mqmd.getFormat());
/*  245 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.messages.WMQReceiveMarshal", "formatLong:" + Long.toHexString(formatLong));
/*  246 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.messages.WMQReceiveMarshal", "mqheader:  " + Long.toHexString(mqheader));
/*      */       } 
/*      */       
/*  249 */       while (mqheader == 5571313378871214080L || mqheader == -3109515640373772288L) {
/*      */         JmqiCodepage jmqiCodepage;
/*  251 */         if (ccsid == -2) {
/*  252 */           ccsid = previousCcsid;
/*      */         }
/*      */ 
/*      */         
/*  256 */         if (cachedCcsid2 == ccsid && cachedJmqiCodepage2 != null) {
/*      */           
/*  258 */           jmqiCodepage = cachedJmqiCodepage2;
/*      */         } else {
/*      */           
/*  261 */           jmqiCodepage = JmqiCodepage.getJmqiCodepage(this.env, ccsid);
/*  262 */           if (jmqiCodepage == null) {
/*  263 */             WMQMarshalUtils.throwBadCcsidException(ccsid);
/*      */           }
/*      */           
/*  266 */           cachedCcsid2 = ccsid;
/*  267 */           cachedJmqiCodepage2 = jmqiCodepage;
/*      */         } 
/*      */         
/*  270 */         boolean swap = ((encoding & 0xF) == 2);
/*      */         
/*  272 */         int bodyPos = header.readFromBuffer(this.buffer, pos, ptrSize, swap, jmqiCodepage, jTls, false);
/*      */         
/*  274 */         if (formatLong == 5571313732236222496L || formatLong == -3109514705028104128L) {
/*  275 */           this.hasMessageProperties = true;
/*  276 */           rfh.readBodyFromBuffer(this.buffer, bodyPos, ptrSize, swap, jmqiCodepage, jTls);
/*  277 */           int numberOfFolders = rfh.getNameValueDataLength();
/*  278 */           for (int i = 0; i < numberOfFolders; i++) {
/*  279 */             String folder = rfh.getNameValueData(i);
/*  280 */             if (mcdFolder == null && folder.startsWith("<mcd>")) {
/*  281 */               mcdFolder = folder;
/*      */             }
/*  283 */             else if (usrFolder == null && folder.startsWith("<usr>")) {
/*  284 */               usrFolder = folder;
/*      */             }
/*  286 */             else if (jmsFolder == null && folder.startsWith("<jms>")) {
/*  287 */               jmsFolder = folder;
/*      */             }
/*  289 */             else if (mqextFolder == null && folder.startsWith("<mqext>")) {
/*  290 */               mqextFolder = folder;
/*      */             }
/*  292 */             else if (mqpsFolder == null && folder.startsWith("<mqps>")) {
/*  293 */               mqpsFolder = folder;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/*  298 */         pos += header.getStrucLength();
/*  299 */         encoding = header.getEncoding();
/*  300 */         previousCcsid = ccsid;
/*  301 */         ccsid = header.getCodedCharSetId();
/*  302 */         formatLong = header.getFormatLong(jmqiCodepage);
/*  303 */         mqheader = formatLong & 0xFFFFFF0000000000L;
/*      */       } 
/*      */ 
/*      */       
/*  307 */       this.encodingFromLastHeaderBeforeBody = encoding;
/*      */ 
/*      */ 
/*      */       
/*  311 */       if (ccsid == -2) {
/*  312 */         this.ccsidFromLastHeaderBeforeBody = previousCcsid;
/*      */       } else {
/*      */         
/*  315 */         this.ccsidFromLastHeaderBeforeBody = ccsid;
/*      */       } 
/*      */       
/*  318 */       this.formatFromLastHeaderBeforeBody = header.getFormat();
/*      */       
/*  320 */       if (Trace.isOn) {
/*  321 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.messages.WMQReceiveMarshal", "constructProviderMessageFromRFH2(int)", "All of the headers have been processed");
/*      */ 
/*      */ 
/*      */         
/*  325 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.messages.WMQReceiveMarshal", "constructProviderMessageFromRFH2(int)", "Format of last header in message: " + this.formatFromLastHeaderBeforeBody);
/*      */ 
/*      */ 
/*      */         
/*  329 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.messages.WMQReceiveMarshal", "constructProviderMessageFromRFH2(int)", "CCSID of message body: " + this.ccsidFromLastHeaderBeforeBody);
/*      */ 
/*      */ 
/*      */         
/*  333 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.messages.WMQReceiveMarshal", "constructProviderMessageFromRFH2(int)", "Encoding of message body: " + this.encodingFromLastHeaderBeforeBody);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  344 */       String fallbackMessageClass = null;
/*  345 */       if (formatLong == 5571325835654209568L || formatLong == -3109486074469007296L) {
/*      */         
/*  347 */         fallbackMessageClass = "jms_text";
/*      */       }
/*      */       else {
/*      */         
/*  351 */         fallbackMessageClass = "jms_bytes";
/*      */       } 
/*      */ 
/*      */       
/*  355 */       String forcedMessageClass = null;
/*  356 */       if (messageBodyStyle == 1) {
/*  357 */         forcedMessageClass = "jms_bytes";
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  362 */       if (mcdFolder != null) {
/*  363 */         this.providerMessage = WMQMessage._parseMcdFolder(mcdFolder, fallbackMessageClass, forcedMessageClass);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  368 */       else if (forcedMessageClass == null) {
/*  369 */         if (pos >= getSafeBufferEnd())
/*      */         {
/*  371 */           this.providerMessage = new WMQNullMessage();
/*  372 */           this.providerMessage.isNullMessage = true;
/*      */         }
/*  374 */         else if (formatLong == 5571325835654209568L || formatLong == -3109486074469007296L)
/*      */         {
/*  376 */           this.providerMessage = new WMQTextMessage();
/*  377 */           this.providerMessage.isNullMessage = false;
/*      */         }
/*      */         else
/*      */         {
/*  381 */           this.providerMessage = new WMQBytesMessage();
/*      */         }
/*      */       
/*      */       }
/*  385 */       else if (forcedMessageClass.equals("jms_bytes")) {
/*  386 */         this.providerMessage = new WMQBytesMessage();
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  396 */       if (jmsFolder != null) {
/*  397 */         boolean persistenceFromMD = WMQMarshalUtils.getPersistenceFromMD(this.owner);
/*  398 */         this.providerMessage._parseJmsFolder(jmsFolder, persistenceFromMD);
/*  399 */         jmsFolderParsed = true;
/*      */       } 
/*      */       
/*  402 */       if (usrFolder != null) {
/*  403 */         this.providerMessage._parseUsrFolder(usrFolder);
/*      */       }
/*      */       
/*  406 */       if (mqextFolder != null) {
/*  407 */         this.providerMessage._parseMQExtFolder(mqextFolder);
/*      */       }
/*      */       
/*  410 */       if (mqpsFolder != null) {
/*  411 */         this.providerMessage._parseMQPsFolder(mqpsFolder);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  419 */       if (!jmsFolderParsed) {
/*  420 */         this.providerMessage.setJMSDeliveryMode(-3);
/*      */       }
/*      */     }
/*  423 */     catch (RuntimeException re) {
/*  424 */       throw re;
/*      */     }
/*  426 */     catch (Exception e) {
/*  427 */       if (Trace.isOn) {
/*  428 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "constructProviderMessageFromRFH2(int)", e);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  433 */       byte[] msgIDBytes = this.mqmd.getMsgId();
/*  434 */       String msgID = Utils.bytesToHex(msgIDBytes).toString();
/*  435 */       byte[] correlIDBytes = this.mqmd.getCorrelId();
/*  436 */       String correlID = Utils.bytesToHex(correlIDBytes).toString();
/*      */       
/*  438 */       HashMap<String, Object> inserts = new HashMap<>();
/*  439 */       inserts.put("XMSC_MESSAGE_ID", msgID);
/*  440 */       inserts.put("XMSC_CORRELATION_ID", correlID);
/*  441 */       inserts.put("XMSC_MESSAGE_BUFFER_POSITION", Integer.valueOf(pos));
/*  442 */       inserts.put("XMSC_MESSAGE_BUFFER", this.buffer);
/*  443 */       Log.log(this, "constructProviderMessageFromRFH2(int)", "JMSCMQ0018", inserts);
/*      */       
/*  445 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ1000", inserts);
/*      */       
/*  447 */       je.setLinkedException(e);
/*      */       
/*  449 */       if (Trace.isOn) {
/*  450 */         Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "constructProviderMessageFromRFH2(int)", (Throwable)je);
/*      */       }
/*      */       
/*  453 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/*  457 */     if (Trace.isOn) {
/*  458 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "constructProviderMessageFromRFH2(int)", 
/*  459 */           Integer.valueOf(pos));
/*      */     }
/*  461 */     return pos;
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
/*      */   private WMQMessage createProviderMessage(boolean createHeadersOnly) throws JMSException {
/*  473 */     if (Trace.isOn) {
/*  474 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "createProviderMessage(boolean)", new Object[] {
/*  475 */             Boolean.valueOf(createHeadersOnly)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  483 */     int messageBodyStyle = this.destination.getIntProperty("messageBody");
/*      */ 
/*      */     
/*  486 */     int currentOffset = constructProviderMessageFromProperties(messageBodyStyle);
/*      */ 
/*      */ 
/*      */     
/*  490 */     updateProviderMessageFromMQMD();
/*      */ 
/*      */     
/*  493 */     if (!createHeadersOnly) {
/*  494 */       addBodyToProviderMessage(currentOffset, messageBodyStyle);
/*      */     }
/*      */     
/*  497 */     this.providerMessage.setWmqMsgToken(this.msgToken);
/*  498 */     this.providerMessage.setWmqMsgLength(this.bufferEnd - this.bufferStart);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  503 */     if (Trace.isOn) {
/*  504 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "createProviderMessage(boolean)", this.providerMessage);
/*      */     }
/*      */     
/*  507 */     return this.providerMessage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addBodyToProviderMessage(int currentOffset, int messageBodyStyle) throws JMSException {
/*      */     HashMap<String, Object> info;
/*  518 */     if (Trace.isOn) {
/*  519 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "addBodyToProviderMessage(int,int)", new Object[] {
/*  520 */             Integer.valueOf(currentOffset), 
/*  521 */             Integer.valueOf(messageBodyStyle)
/*      */           });
/*      */     }
/*  524 */     int offsetForBody = -1;
/*      */ 
/*      */     
/*  527 */     int ccsidForBody = 0;
/*  528 */     int encodingForBody = 0;
/*  529 */     String formatForBody = null;
/*      */ 
/*      */     
/*  532 */     JmqiCodepage codepage = null;
/*      */     
/*  534 */     switch (messageBodyStyle) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 0:
/*      */       case 2:
/*  542 */         ccsidForBody = this.ccsidFromLastHeaderBeforeBody;
/*  543 */         encodingForBody = this.encodingFromLastHeaderBeforeBody;
/*  544 */         formatForBody = this.formatFromLastHeaderBeforeBody;
/*  545 */         codepage = WMQUtils.getCodepage(this.env, ccsidForBody, encodingForBody);
/*  546 */         offsetForBody = currentOffset;
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 1:
/*  556 */         ccsidForBody = this.mqmd.getCodedCharSetId();
/*  557 */         encodingForBody = this.mqmd.getEncoding();
/*  558 */         formatForBody = this.mqmd.getFormat();
/*  559 */         codepage = WMQUtils.getCodepage(this.env, ccsidForBody, encodingForBody);
/*  560 */         offsetForBody = this.bufferStart;
/*      */         break;
/*      */       
/*      */       default:
/*  564 */         info = new HashMap<>();
/*  565 */         info.put("messageBody", Integer.valueOf(messageBodyStyle));
/*  566 */         Trace.ffst(this, "addBodyToProviderMessage(int,int)", "XN00K001", info, JMSException.class);
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  573 */     if (offsetForBody < 0 || offsetForBody > getSafeBufferEnd()) {
/*      */       
/*  575 */       info = new HashMap<>();
/*  576 */       info.put("offsetForBody", Integer.valueOf(offsetForBody));
/*  577 */       Trace.ffst(this, "addBodyToProviderMessage(int,int)", "XN00K002", info, JMSException.class);
/*      */     } 
/*      */ 
/*      */     
/*  581 */     this.providerMessage._importBody(this.buffer, offsetForBody, this.bufferEnd, encodingForBody, codepage);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  587 */     if (ccsidForBody > 0) {
/*  588 */       this.providerMessage.setStringProperty("JMS_IBM_Character_Set", codepage.getCharsetName());
/*      */ 
/*      */     
/*      */     }
/*  592 */     else if (Trace.isOn) {
/*  593 */       Trace.data(this, "addBodyToProviderMessage(int,int)", "Unexpected value for ccsidForBody", Integer.valueOf(ccsidForBody));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  598 */     this.providerMessage.setIntProperty("JMS_IBM_Encoding", encodingForBody);
/*  599 */     if (encodingForBody <= 0)
/*      */     {
/*  601 */       if (Trace.isOn) {
/*  602 */         Trace.data(this, "addBodyToProviderMessage(int,int)", "Unexpected value for encodingForBody", Integer.valueOf(encodingForBody));
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  608 */     if (formatForBody != null) {
/*  609 */       this.providerMessage.setStringProperty("JMS_IBM_Format", formatForBody);
/*      */     
/*      */     }
/*  612 */     else if (Trace.isOn) {
/*  613 */       Trace.data(this, "addBodyToProviderMessage(int,int)", "formatForBody is null", null);
/*      */     } 
/*      */ 
/*      */     
/*  617 */     if (Trace.isOn) {
/*  618 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "addBodyToProviderMessage(int,int)");
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
/*      */   public WMQMessage exportProviderMessage(boolean exportHeadersOnly) throws JMSException {
/*  632 */     if (Trace.isOn) {
/*  633 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "exportProviderMessage(boolean)", new Object[] {
/*  634 */             Boolean.valueOf(exportHeadersOnly)
/*      */           });
/*      */     }
/*      */ 
/*      */     
/*  639 */     if (!this.imported) {
/*  640 */       if (Trace.isOn) {
/*  641 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "exportProviderMessage(boolean)", null, 1);
/*      */       }
/*      */       
/*  644 */       return null;
/*      */     } 
/*      */ 
/*      */     
/*  648 */     createProviderMessage(exportHeadersOnly);
/*      */ 
/*      */     
/*  651 */     if (Trace.isOn) {
/*  652 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "exportProviderMessage(boolean)", this.providerMessage, 2);
/*      */     }
/*      */     
/*  655 */     return this.providerMessage;
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
/*      */   protected int getSafeBufferEnd() {
/*  667 */     int end = (this.bufferEnd == -1) ? this.buffer.length : this.bufferEnd;
/*  668 */     return end;
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
/*      */   public void importMQMDMesageBuffer(WMQConsumerOwner anOwner, WMQDestination destination, MQMD mqmd, ByteBuffer buffer, int dataStart, int dataEnd, MQGMO gmo) {
/*  690 */     if (Trace.isOn) {
/*  691 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "importMQMDMesageBuffer(WMQConsumerOwner,WMQDestination,MQMD,ByteBuffer,int,int,MQGMO)", new Object[] { anOwner, destination, mqmd, buffer, 
/*      */             
/*  693 */             Integer.valueOf(dataStart), 
/*  694 */             Integer.valueOf(dataEnd), gmo });
/*      */     }
/*      */     
/*  697 */     if (anOwner == null || mqmd == null || buffer == null || destination == null) {
/*  698 */       HashMap<String, Object> info = new HashMap<>();
/*  699 */       info.put("anOwner", anOwner);
/*  700 */       info.put("mqmd", mqmd);
/*  701 */       info.put("buffer", buffer);
/*  702 */       info.put("destination", destination);
/*  703 */       Trace.ffst(this, "importMQMDMesageBuffer(WMQConsumerOwner,WMQDestination,MQMD,ByteBuffer,int,int)", "XN006001", info, null);
/*  704 */       if (Trace.isOn) {
/*  705 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "importMQMDMesageBuffer(WMQConsumerOwner,WMQDestination,MQMD,ByteBuffer,int,int,MQGMO)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/*  713 */     if (dataEnd != -1 && dataEnd < dataStart) {
/*  714 */       HashMap<String, Object> info = new HashMap<>();
/*  715 */       info.put("start", Integer.valueOf(dataStart));
/*  716 */       info.put("end", Integer.valueOf(dataEnd));
/*  717 */       Trace.ffst(this, "importMQMDMesageBuffer(WMQConsumerOwner,WMQDestination,MQMD,ByteBuffer,int,int)", "XN006002", info, null);
/*      */     } 
/*      */ 
/*      */     
/*  721 */     resetReceiveState();
/*      */     
/*  723 */     this.imported = true;
/*      */     
/*  725 */     this.owner = anOwner;
/*  726 */     this.destination = destination;
/*  727 */     this.mqmd = mqmd;
/*  728 */     this.buffer = buffer.array();
/*  729 */     this.bufferStart = dataStart;
/*  730 */     this.bufferEnd = dataEnd;
/*      */     
/*  732 */     this.env = this.owner.getJmqiEnvironment();
/*      */ 
/*      */     
/*  735 */     if (gmo != null) {
/*  736 */       this.msgToken = gmo.getMsgToken();
/*      */     }
/*      */     
/*  739 */     if (this.env == null) {
/*  740 */       Trace.ffst(this, "importMQMDMesageBuffer(WMQConsumerOwner,WMQDestination,MQMD,ByteBuffer,int,int)", "XN00K003", null, null);
/*      */     }
/*      */     
/*  743 */     if (Trace.isOn)
/*      */     {
/*      */       
/*  746 */       Trace.data(this, "importMQMDMesageBuffer(WMQConsumerOwner,WMQDestination,MQMD,ByteBuffer,int,int)", "Imported/received buffer", buffer);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  752 */     if (Trace.isOn) {
/*  753 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "importMQMDMesageBuffer(WMQConsumerOwner,WMQDestination,MQMD,ByteBuffer,int,int,MQGMO)", 2);
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
/*      */   private void resetReceiveState() {
/*  766 */     if (Trace.isOn) {
/*  767 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "resetReceiveState()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  772 */     resetState();
/*      */ 
/*      */     
/*  775 */     this.buffer = null;
/*  776 */     this.bufferStart = 0;
/*  777 */     this.bufferEnd = -1;
/*      */     
/*  779 */     this.hasMessageProperties = false;
/*      */     
/*  781 */     this.encodingFromLastHeaderBeforeBody = 0;
/*  782 */     this.ccsidFromLastHeaderBeforeBody = 0;
/*  783 */     this.formatFromLastHeaderBeforeBody = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  789 */     if (Trace.isOn) {
/*  790 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "resetReceiveState()");
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
/*      */   private void updateFromMQMDReport(String reportProperty, int fullReport, int mask) throws JMSException {
/*  803 */     if (Trace.isOn) {
/*  804 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "updateFromMQMDReport(String,int,int)", new Object[] { reportProperty, 
/*      */             
/*  806 */             Integer.valueOf(fullReport), Integer.valueOf(mask) });
/*      */     }
/*  808 */     int reportSubset = fullReport & mask;
/*  809 */     if (reportSubset != 0) {
/*  810 */       this.providerMessage.setIntProperty(reportProperty, reportSubset);
/*      */     }
/*  812 */     if (Trace.isOn) {
/*  813 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "updateFromMQMDReport(String,int,int)");
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
/*      */   protected void updateProviderMessageFromMQMD() throws JMSException {
/*  840 */     if (Trace.isOn) {
/*  841 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "updateProviderMessageFromMQMD()");
/*      */     }
/*      */ 
/*      */     
/*  845 */     super.updateProviderMessageFromMQMD();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  854 */     if (this.providerMessage.getJMSCorrelationID() == null) {
/*  855 */       byte[] cid = this.mqmd.getCorrelId();
/*  856 */       if (cid != null) {
/*  857 */         this.providerMessage.setJMSCorrelationIDAsBytes(cid);
/*      */       
/*      */       }
/*  860 */       else if (Trace.isOn) {
/*  861 */         Trace.data(this, "updateProviderMessageFromMQMD()", "byte[] value for MQMD.CorrelId is null", null);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  869 */     if (this.providerMessage.getJMSTimestamp() == null) {
/*  870 */       String putTimeString = this.mqmd.getPutTime();
/*  871 */       String putDateString = this.mqmd.getPutDate();
/*  872 */       if (putTimeString != null && !putTimeString.trim().equals("") && putDateString != null && !putDateString.trim().equals("")) {
/*  873 */         long putTime = WMQDateConverter.mqDateTimeToMillis(putTimeString, putDateString);
/*  874 */         this.providerMessage.setJMSTimestamp(putTime);
/*      */       
/*      */       }
/*  877 */       else if (Trace.isOn) {
/*  878 */         Trace.data(this, "updateProviderMessageFromMQMD()", "String value for MQMD.PutTime is null or empty", putTimeString);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  887 */     if (this.providerMessage.getJMSExpiration().longValue() == 0L) {
/*  888 */       int expiry = this.mqmd.getExpiry();
/*  889 */       if (expiry > 0) {
/*  890 */         this.providerMessage.setJMSExpirationFromMQMDExpiry(expiry);
/*      */       
/*      */       }
/*  893 */       else if (Trace.isOn) {
/*  894 */         Trace.data(this, "updateProviderMessageFromMQMD()", "Unexpected value for MQMD.Expiry", Integer.valueOf(expiry));
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  900 */     int backoutCount = this.mqmd.getBackoutCount();
/*  901 */     if (backoutCount > 0) {
/*  902 */       this.providerMessage.setJMSRedelivered(true);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  907 */     this.providerMessage.setIntProperty("JMSXDeliveryCount", backoutCount + 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  914 */     int replyToStyle = this.destination.getIntProperty("XMSC_WMQ_REPLYTO_STYLE");
/*  915 */     boolean goWithMD = false;
/*  916 */     if ((replyToStyle == 0 && this.defaultReplyToStyle == 1) || replyToStyle == 1)
/*      */     {
/*  918 */       goWithMD = true;
/*      */     }
/*      */     
/*  921 */     String replyToURI = this.providerMessage.getJMSReplyToAsString();
/*  922 */     boolean noReplyTo = (replyToURI == null);
/*  923 */     if (Trace.isOn) {
/*  924 */       Trace.data(this, "updateProviderMessageFromMQMD()", "ReplyTo not set from RFH2", Boolean.valueOf(noReplyTo));
/*      */     }
/*      */     
/*  927 */     String mqmdReplyToQ = this.mqmd.getReplyToQ();
/*  928 */     mqmdReplyToQ = (mqmdReplyToQ != null) ? mqmdReplyToQ.trim() : null;
/*  929 */     if (Trace.isOn) {
/*  930 */       Trace.data(this, "updateProviderMessageFromMQMD()", "String value for MQMD.ReplyTo", mqmdReplyToQ);
/*      */     }
/*      */     
/*  933 */     if (goWithMD && mqmdReplyToQ != null && !mqmdReplyToQ.equals("")) {
/*      */       
/*  935 */       String mqmdReplyToQMgr = this.mqmd.getReplyToQMgr();
/*  936 */       mqmdReplyToQMgr = (mqmdReplyToQMgr == null) ? "" : mqmdReplyToQMgr.trim();
/*      */       
/*  938 */       if (Trace.isOn) {
/*  939 */         Trace.data(this, "updateProviderMessageFromMQMD()", "String value for MQMD.ReplyToQMgr", mqmdReplyToQMgr);
/*      */       }
/*      */       
/*  942 */       StringBuffer newReplyToURI = new StringBuffer("queue://");
/*  943 */       newReplyToURI.append(mqmdReplyToQMgr).append("/").append(mqmdReplyToQ);
/*      */ 
/*      */       
/*  946 */       String replyToParamters = null;
/*  947 */       if (replyToURI != null) {
/*  948 */         int positionOfQuestionMark = replyToURI.indexOf('?');
/*  949 */         if (positionOfQuestionMark > 0) {
/*  950 */           replyToParamters = replyToURI.substring(positionOfQuestionMark);
/*      */ 
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  959 */         boolean targetClientMatching = true;
/*  960 */         WMQCommonConnection wMQCommonConnection = this.owner.getConnection();
/*  961 */         if (wMQCommonConnection != null)
/*      */         {
/*      */           
/*  964 */           targetClientMatching = wMQCommonConnection.getBooleanProperty("XMSC_WMQ_TARGET_CLIENT_MATCHING");
/*      */         }
/*      */         
/*  967 */         if (!this.hasMessageProperties && targetClientMatching) {
/*  968 */           if (Trace.isOn) {
/*  969 */             Trace.data(this, "updateProviderMessageFromMQMD()", "Messages sent to ReplyTo will not contain RFH2 header", null);
/*      */           }
/*  971 */           replyToParamters = "?targetClient=1";
/*      */         } 
/*      */       } 
/*      */       
/*  975 */       if (replyToParamters != null) {
/*  976 */         newReplyToURI.append(replyToParamters);
/*      */       }
/*      */ 
/*      */       
/*  980 */       this.providerMessage.setJMSReplyToAsString(newReplyToURI.toString());
/*      */     } 
/*      */ 
/*      */     
/*  984 */     int msgType = this.mqmd.getMsgType();
/*  985 */     this.providerMessage.setIntProperty("JMS_IBM_MsgType", msgType);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  990 */     int feedback = this.mqmd.getFeedback();
/*  991 */     if (feedback != 0) {
/*  992 */       this.providerMessage.setIntProperty("JMS_IBM_Feedback", feedback);
/*      */     
/*      */     }
/*  995 */     else if (Trace.isOn) {
/*  996 */       Trace.data(this, "updateProviderMessageFromMQMD()", "Numeric value for MQMD.Feedback is MQFB_NONE", Integer.valueOf(feedback));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1001 */     int report = this.mqmd.getReport();
/*      */     
/* 1003 */     if (report != 0) {
/*      */       
/* 1005 */       updateFromMQMDReport("JMS_IBM_Report_Exception", report, 117440512);
/* 1006 */       updateFromMQMDReport("JMS_IBM_Report_Expiration", report, 14680064);
/* 1007 */       updateFromMQMDReport("JMS_IBM_Report_COA", report, 1792);
/* 1008 */       updateFromMQMDReport("JMS_IBM_Report_COD", report, 14336);
/* 1009 */       updateFromMQMDReport("JMS_IBM_Report_PAN", report, 1);
/* 1010 */       updateFromMQMDReport("JMS_IBM_Report_NAN", report, 2);
/* 1011 */       updateFromMQMDReport("JMS_IBM_Report_Pass_Msg_ID", report, 128);
/* 1012 */       updateFromMQMDReport("JMS_IBM_Report_Pass_Correl_ID", report, 64);
/* 1013 */       updateFromMQMDReport("JMS_IBM_Report_Discard_Msg", report, 134217728);
/* 1014 */       updateFromMQMDReport("JMS_IBM_Report_Pass_Discard_And_Expiry", report, 16384);
/*      */     } 
/*      */ 
/*      */     
/* 1018 */     int msgFlags = this.mqmd.getMsgFlags();
/* 1019 */     if (msgFlags != 0) {
/* 1020 */       if ((msgFlags & 0x10) != 0) {
/* 1021 */         this.providerMessage.setBooleanProperty("JMS_IBM_Last_Msg_In_Group", true);
/*      */       
/*      */       }
/*      */     }
/* 1025 */     else if (Trace.isOn) {
/* 1026 */       Trace.data(this, "updateProviderMessageFromMQMD()", "Numeric value for MQMD.MsgFlags is MQMF_NONE", Integer.valueOf(msgFlags));
/*      */     } 
/*      */ 
/*      */     
/* 1030 */     if (Trace.isOn)
/* 1031 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal", "updateProviderMessageFromMQMD()"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQReceiveMarshal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */