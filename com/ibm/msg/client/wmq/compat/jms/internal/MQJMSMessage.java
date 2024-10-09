/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQSESSION;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.charset.CharacterCodingException;
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
/*      */ public class MQJMSMessage
/*      */   extends MQMsg2
/*      */ {
/*      */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQJMSMessage.java";
/*      */   public static final String CLASSNAME = "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage";
/*      */   private static final int RFH_CHARACTER_SET = 1208;
/*      */   
/*      */   static {
/*   82 */     if (Trace.isOn) {
/*   83 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQJMSMessage.java");
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
/*  101 */   private static final byte[] SPACES = new byte[] { 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32 };
/*      */ 
/*      */   
/*      */   private static final String RFH_CHAR_SET_STRING = "UTF8";
/*      */ 
/*      */   
/*      */   private static final String MQRFH_STRUC_ID = "RFH ";
/*      */ 
/*      */   
/*      */   private static final int MQRFH_VERSION_1 = 1;
/*      */ 
/*      */   
/*      */   private static final int MQRFH_VERSION_2 = 2;
/*      */ 
/*      */   
/*      */   private static final int MQRFH_NO_FLAGS = 0;
/*      */ 
/*      */   
/*      */   protected static final String MQFMT_RF_HEADER_1 = "MQHRF   ";
/*      */ 
/*      */   
/*      */   protected static final String MQFMT_RF_HEADER_2 = "MQHRF2  ";
/*      */ 
/*      */   
/*      */   private static final int MQRFH_STRUC_LENGTH_FIXED_1 = 32;
/*      */ 
/*      */   
/*      */   private static final int MQRFH_STRUC_LENGTH_FIXED_2 = 36;
/*      */ 
/*      */   
/*      */   private static final int MQCCSI_INHERIT = -2;
/*      */ 
/*      */   
/*      */   private static final long MQHEADER_ASCII = 5571313378871214080L;
/*      */ 
/*      */   
/*      */   private static final long MQHEADER_EBCDIC = -3109515640373772288L;
/*      */ 
/*      */   
/*      */   private static final long MQHEADER_MASK = -1099511627776L;
/*      */ 
/*      */   
/*      */   protected static final long MQRFH2_ASCII = 5571313732236222496L;
/*      */ 
/*      */   
/*      */   protected static final long MQRFH2_EBCDIC = -3109514705028104128L;
/*      */ 
/*      */   
/*      */   protected static final long MQRFH1_ASCII = 5571313732235042848L;
/*      */   
/*      */   protected static final long MQRFH1_EBCDIC = -3109514705039769536L;
/*      */   
/*      */   private static final long MQSTR_ASCII = 5571325835654209568L;
/*      */   
/*      */   private static final long MQSTR_EBCDIC = -3109486074469007296L;
/*      */   
/*      */   private static final String BLANK_STRING15 = "               ";
/*      */   
/*  159 */   private static final Integer ZERO = Integer.valueOf(0);
/*      */ 
/*      */ 
/*      */   
/*  163 */   private static final byte[] MQ_NONE = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*      */ 
/*      */   
/*      */   private static final int RFH2_FIXED_SIZE = 36;
/*      */ 
/*      */   
/*      */   private static final int MQRFH_VERSION_OFFSET = 4;
/*      */ 
/*      */   
/*      */   private static final int RFH2_LENGTH_OFFSET = 8;
/*      */ 
/*      */   
/*      */   private static final int RFH2_ENCODING_OFFSET = 12;
/*      */ 
/*      */   
/*      */   private static final int RFH2_CHARSET_OFFSET = 16;
/*      */ 
/*      */   
/*      */   private static final int RFH2_FORMAT_OFFSET = 20;
/*      */   
/*      */   private static final int RFH_CHAR_SET_OFFSET = 32;
/*      */   
/*  185 */   private byte[] cachedFixedRFH2 = null;
/*      */ 
/*      */   
/*      */   private int cachedRFH2Length;
/*      */ 
/*      */   
/*      */   private int cachedRFH2Encoding;
/*      */ 
/*      */   
/*      */   private int cachedRFH2CharSet;
/*      */ 
/*      */   
/*      */   private boolean persistenceFromMD;
/*      */ 
/*      */   
/*      */   private boolean targetClientMatching = true;
/*      */ 
/*      */   
/*      */   private boolean hasRFH2 = false;
/*      */   
/*  205 */   private byte[] formatAsByteArray = new byte[8];
/*      */ 
/*      */ 
/*      */   
/*      */   private int dataQuantityHint;
/*      */ 
/*      */   
/*      */   String cachedRFH2Format;
/*      */ 
/*      */   
/*  215 */   private static int defaultReplyToStyle = 1;
/*      */   
/*      */   static {
/*  218 */     if (Trace.isOn) {
/*  219 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "static()");
/*      */     }
/*      */ 
/*      */     
/*  223 */     String overrideReplyToStyle = PropertyStore.getStringProperty("com.ibm.mq.jms.replyToStyle");
/*  224 */     if (overrideReplyToStyle != null) {
/*  225 */       if (overrideReplyToStyle.equalsIgnoreCase("MQMD")) {
/*  226 */         defaultReplyToStyle = 1;
/*      */       }
/*  228 */       else if (overrideReplyToStyle.equalsIgnoreCase("RFH2")) {
/*  229 */         defaultReplyToStyle = 2;
/*      */       } else {
/*      */         
/*  232 */         defaultReplyToStyle = 1;
/*      */       } 
/*      */     } else {
/*      */       
/*  236 */       defaultReplyToStyle = 1;
/*      */     } 
/*      */     
/*  239 */     if (Trace.isOn) {
/*  240 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "static()");
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
/*      */   JMSMessage createJMSMessage(MQSession session, ProviderDestination destination, int dataQuantity) throws JMSException, IOException {
/*  261 */     if (Trace.isOn) {
/*  262 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination,int)", new Object[] { session, destination, 
/*      */             
/*  264 */             Integer.valueOf(dataQuantity) });
/*      */     }
/*  266 */     this.dataQuantityHint = dataQuantity;
/*  267 */     JMSMessage jmsMsg = null;
/*      */     try {
/*  269 */       jmsMsg = createJMSMessage(session, destination);
/*      */     }
/*  271 */     catch (IOException ioe) {
/*  272 */       if (Trace.isOn) {
/*  273 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination,int)", ioe, 1);
/*      */       }
/*      */       
/*  276 */       if (Trace.isOn) {
/*  277 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination,int)", ioe, 1);
/*      */       }
/*      */       
/*  280 */       throw ioe;
/*      */     }
/*  282 */     catch (JMSException e) {
/*  283 */       if (Trace.isOn) {
/*  284 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination,int)", (Throwable)e, 2);
/*      */       }
/*      */       
/*  287 */       if (Trace.isOn) {
/*  288 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination,int)", (Throwable)e, 2);
/*      */         
/*  290 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination,int)", (Throwable)e, 2);
/*      */       } 
/*      */       
/*  293 */       if (Trace.isOn) {
/*  294 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination,int)", (Throwable)e, 2);
/*      */       }
/*      */       
/*  297 */       throw e;
/*      */     } finally {
/*      */       
/*  300 */       if (Trace.isOn) {
/*  301 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination,int)");
/*      */       }
/*      */ 
/*      */       
/*  305 */       this.dataQuantityHint = 0;
/*  306 */       if (Trace.isOn) {
/*  307 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination, int)", jmsMsg);
/*      */       }
/*      */     } 
/*      */     
/*  311 */     if (Trace.isOn) {
/*  312 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination,int)", jmsMsg);
/*      */     }
/*      */     
/*  315 */     return jmsMsg;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JMSMessage createJMSMessage(MQSession session, ProviderDestination destination) throws JMSException, IOException {
/*  346 */     if (Trace.isOn) {
/*  347 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination)", new Object[] { session, destination });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  353 */     int origCharacterSet = getCharacterSet();
/*  354 */     int origEncoding = getEncoding();
/*      */ 
/*      */     
/*  357 */     if (session != null && session.connection != null) {
/*  358 */       this.persistenceFromMD = session.getPersistenceFromMD();
/*  359 */       this.targetClientMatching = session.connection.targetClientMatching;
/*      */     } 
/*      */     try {
/*  362 */       JMSMessage jmsMsg = null;
/*  363 */       String currentFormat = null;
/*  364 */       int currentOffset = 0;
/*      */ 
/*      */ 
/*      */       
/*  368 */       String folder = null;
/*  369 */       String mcdFolder = null;
/*  370 */       String usrFolder = null;
/*  371 */       String jmsFolder = null;
/*  372 */       String mqextFolder = null;
/*  373 */       int mcdOffset = 0;
/*  374 */       int mcdLength = 0;
/*  375 */       int usrOffset = 0;
/*  376 */       int usrLength = 0;
/*  377 */       int jmsOffset = 0;
/*  378 */       int jmsLength = 0;
/*  379 */       int mqextOffset = 0;
/*  380 */       int mqextLength = 0;
/*      */       
/*  382 */       this.hasRFH2 = false;
/*  383 */       boolean rfh2InUtf8 = false;
/*  384 */       boolean jmsFolderParsed = false;
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
/*  395 */       boolean messageBodyMQ = false;
/*  396 */       if (destination != null && destination.getIntProperty("messageBody") == 1) {
/*  397 */         messageBodyMQ = true;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  407 */       if (Trace.isOn) {
/*  408 */         Trace.traceData(this, "Resetting read position to start", null);
/*      */       }
/*  410 */       resetReadPosition();
/*      */       
/*  412 */       int currentEncoding = origEncoding;
/*      */ 
/*      */ 
/*      */       
/*  416 */       long currentFormatLong = getFormatAsLong();
/*  417 */       int fmtCset = 1208;
/*  418 */       int fmtEnc = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  426 */       currentOffset = 0;
/*  427 */       int hdrStartOffset = 0;
/*  428 */       while ((currentFormatLong & 0xFFFFFF0000000000L) == 5571313378871214080L || (currentFormatLong & 0xFFFFFF0000000000L) == -3109515640373772288L) {
/*      */         
/*  430 */         skipReadingBytes(8);
/*  431 */         int hdrLength = readInt(currentEncoding);
/*  432 */         int newEnc = readInt(currentEncoding);
/*  433 */         int sugNextCset = readInt(currentEncoding);
/*      */ 
/*      */         
/*  436 */         if (currentFormatLong == 5571313732236222496L || currentFormatLong == -3109514705028104128L) {
/*  437 */           this.hasRFH2 = true;
/*      */ 
/*      */           
/*  440 */           currentFormatLong = readInt(1) << 32L;
/*  441 */           currentFormatLong += readInt(1) & 0xFFFFFFFFL;
/*      */ 
/*      */ 
/*      */           
/*  445 */           fmtCset = getCharacterSet();
/*  446 */           fmtEnc = currentEncoding;
/*      */           
/*  448 */           skipReadingBytes(4);
/*      */ 
/*      */           
/*  451 */           int csetAsInt = readInt(currentEncoding);
/*  452 */           if (csetAsInt == 1208) {
/*      */             
/*  454 */             rfh2InUtf8 = true;
/*      */           } else {
/*      */             
/*  457 */             rfh2InUtf8 = false;
/*      */           } 
/*  459 */           currentOffset += 36;
/*      */ 
/*      */           
/*  462 */           while (currentOffset < hdrLength + hdrStartOffset) {
/*  463 */             int ll = readInt(currentEncoding);
/*  464 */             currentOffset += 4;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  469 */             if (rfh2InUtf8) {
/*  470 */               byte[] md = getMessageDataRef();
/*  471 */               int i = currentOffset;
/*  472 */               if (md[i + 0] == 60 && md[i + 1] == 109 && md[i + 2] == 99 && md[i + 3] == 100 && md[i + 4] == 62)
/*      */               {
/*  474 */                 mcdOffset = currentOffset;
/*  475 */                 mcdLength = ll;
/*      */               }
/*  477 */               else if (md[i + 0] == 60 && md[i + 1] == 106 && md[i + 2] == 109 && md[i + 3] == 115 && md[i + 4] == 62)
/*      */               {
/*  479 */                 jmsOffset = currentOffset;
/*  480 */                 jmsLength = ll;
/*      */               }
/*  482 */               else if (md[i + 0] == 60 && md[i + 1] == 117 && md[i + 2] == 115 && md[i + 3] == 114 && md[i + 4] == 62)
/*      */               {
/*  484 */                 usrOffset = currentOffset;
/*  485 */                 usrLength = ll;
/*      */               }
/*  487 */               else if (md[i + 0] == 60 && md[i + 1] == 109 && md[i + 2] == 113 && md[i + 3] == 101 && md[i + 4] == 120 && md[i + 5] == 116 && md[i + 6] == 62)
/*      */               {
/*  489 */                 mqextOffset = currentOffset;
/*  490 */                 mqextLength = ll;
/*      */               }
/*      */             
/*      */             } else {
/*      */               
/*  495 */               folder = JmqiCodepage.getJmqiCodepage(this.env, csetAsInt, currentEncoding).bytesToString(getMessageDataRef(), currentOffset, ll);
/*      */               
/*  497 */               if (Trace.isOn) {
/*  498 */                 Trace.traceData(this, "createJMSMessage found Folder " + folder, null);
/*      */               }
/*  500 */               if (folder.startsWith("<mcd>")) {
/*  501 */                 mcdFolder = folder;
/*      */               }
/*  503 */               else if (folder.startsWith("<usr>")) {
/*  504 */                 usrFolder = folder;
/*      */               }
/*  506 */               else if (folder.startsWith("<jms>")) {
/*  507 */                 jmsFolder = folder;
/*      */               }
/*  509 */               else if (folder.startsWith("<mqext>")) {
/*  510 */                 mqextFolder = folder;
/*      */               } 
/*      */             } 
/*      */             
/*  514 */             currentOffset += ll;
/*  515 */             skipReadingBytes(ll);
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  521 */           currentFormatLong = readInt(1) << 32L;
/*  522 */           currentFormatLong += readInt(1) & 0xFFFFFFFFL;
/*      */ 
/*      */ 
/*      */           
/*  526 */           fmtCset = getCharacterSet();
/*  527 */           fmtEnc = currentEncoding;
/*      */ 
/*      */           
/*  530 */           skipReadingBytes(hdrLength - 28);
/*  531 */           currentOffset += hdrLength;
/*      */         } 
/*      */ 
/*      */         
/*  535 */         currentEncoding = newEnc;
/*  536 */         setEncoding(newEnc);
/*      */         
/*  538 */         hdrStartOffset += hdrLength;
/*      */ 
/*      */         
/*  541 */         if (sugNextCset != -2)
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*  546 */           setCharacterSet(sugNextCset);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  553 */       if (rfh2InUtf8 && mcdLength > 0) {
/*  554 */         String messageClass; byte[] msgData = getMessageDataRef();
/*      */         
/*  556 */         if (currentFormatLong == 5571325835654209568L || currentFormatLong == -3109486074469007296L) {
/*  557 */           messageClass = "jms_text";
/*  558 */           currentFormat = "MQSTR   ";
/*      */         } else {
/*      */           
/*  561 */           messageClass = "jms_bytes";
/*      */         } 
/*      */         
/*  564 */         jmsMsg = JMSMessage._parseMcdFolderUtf8(session, MQSession.jmsStrings, messageClass, msgData, mcdOffset, mcdLength, messageBodyMQ);
/*      */         
/*  566 */         if (jmsLength > 0) {
/*  567 */           jmsMsg._parseJmsFolderUtf8(msgData, jmsOffset, jmsLength, session, this.persistenceFromMD);
/*  568 */           jmsFolderParsed = true;
/*      */         } 
/*      */         
/*  571 */         if (usrLength > 0) {
/*  572 */           usrFolder = new String(msgData, usrOffset, usrLength, "UTF8");
/*  573 */           jmsMsg._parseUsrFolder(usrFolder);
/*      */         } 
/*  575 */         if (mqextLength > 0) {
/*  576 */           jmsMsg._parseMQExtFolderUtf8(msgData, mqextOffset, mqextLength);
/*      */         }
/*      */       }
/*  579 */       else if (mcdFolder != null) {
/*      */         String messageClass;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  585 */         if (currentFormatLong == 5571325835654209568L || currentFormatLong == -3109486074469007296L) {
/*  586 */           messageClass = "jms_text";
/*  587 */           currentFormat = "MQSTR   ";
/*      */         }
/*      */         else {
/*      */           
/*  591 */           messageClass = "jms_bytes";
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  599 */         jmsMsg = JMSMessage._parseMcdFolder(session, MQSession.jmsStrings, mcdFolder, messageClass, messageBodyMQ);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  606 */         if (jmsFolder != null) {
/*      */           
/*  608 */           jmsMsg._parseJmsFolder(session, jmsFolder, this.persistenceFromMD);
/*  609 */           jmsFolderParsed = true;
/*      */         } 
/*      */         
/*  612 */         if (usrFolder != null) {
/*  613 */           jmsMsg._parseUsrFolder(usrFolder);
/*      */         }
/*      */         
/*  616 */         if (mqextFolder != null) {
/*  617 */           jmsMsg._parseMQExtFolder(mqextFolder);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  633 */         if (messageBodyMQ == true) {
/*  634 */           jmsMsg = (JMSMessage)session.createBytesMessage();
/*      */         }
/*  636 */         else if (getMessageDataLength() == 0) {
/*  637 */           jmsMsg = (JMSMessage)session.createMessage();
/*      */         }
/*  639 */         else if (currentFormatLong == 5571325835654209568L || currentFormatLong == -3109486074469007296L) {
/*  640 */           currentFormat = "MQSTR   ";
/*  641 */           jmsMsg = (JMSMessage)session.createTextMessage();
/*      */         } else {
/*      */           
/*  644 */           jmsMsg = (JMSMessage)session.createBytesMessage();
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  654 */         if (rfh2InUtf8) {
/*      */           
/*  656 */           byte[] msgData = getMessageDataRef();
/*      */           
/*  658 */           if (jmsLength > 0) {
/*  659 */             jmsMsg._parseJmsFolderUtf8(msgData, jmsOffset, jmsLength, session, this.persistenceFromMD);
/*  660 */             jmsFolderParsed = true;
/*      */           } 
/*  662 */           if (usrLength > 0) {
/*  663 */             usrFolder = new String(msgData, usrOffset, usrLength, "UTF8");
/*  664 */             jmsMsg._parseUsrFolder(usrFolder);
/*      */           } 
/*  666 */           if (mqextLength > 0) {
/*  667 */             jmsMsg._parseMQExtFolderUtf8(msgData, mqextOffset, mqextLength);
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/*  672 */           if (jmsFolder != null) {
/*      */             
/*  674 */             jmsMsg._parseJmsFolder(session, jmsFolder, this.persistenceFromMD);
/*  675 */             jmsFolderParsed = true;
/*      */           } 
/*      */           
/*  678 */           if (usrFolder != null) {
/*  679 */             jmsMsg._parseUsrFolder(usrFolder);
/*      */           }
/*      */           
/*  682 */           if (mqextFolder != null) {
/*  683 */             jmsMsg._parseMQExtFolder(mqextFolder);
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  690 */       if (!jmsFolderParsed) {
/*  691 */         jmsMsg.setJMSDeliveryMode(-3);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  700 */       setMQMDPropsFromHeader(destination, jmsMsg, origCharacterSet, origEncoding);
/*      */ 
/*      */ 
/*      */       
/*  704 */       setHeaderFromMQMD(jmsMsg, destination);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  710 */       if (currentFormat == null) {
/*  711 */         this.formatAsByteArray[0] = (byte)(int)(currentFormatLong >>> 56L & 0xFFL);
/*  712 */         this.formatAsByteArray[1] = (byte)(int)(currentFormatLong >>> 48L & 0xFFL);
/*  713 */         this.formatAsByteArray[2] = (byte)(int)(currentFormatLong >>> 40L & 0xFFL);
/*  714 */         this.formatAsByteArray[3] = (byte)(int)(currentFormatLong >>> 32L & 0xFFL);
/*  715 */         this.formatAsByteArray[4] = (byte)(int)(currentFormatLong >>> 24L & 0xFFL);
/*  716 */         this.formatAsByteArray[5] = (byte)(int)(currentFormatLong >>> 16L & 0xFFL);
/*  717 */         this.formatAsByteArray[6] = (byte)(int)(currentFormatLong >>> 8L & 0xFFL);
/*  718 */         this.formatAsByteArray[7] = (byte)(int)(currentFormatLong >>> 0L & 0xFFL);
/*      */         
/*  720 */         currentFormat = JmqiCodepage.getJmqiCodepage(this.env, fmtCset, fmtEnc).bytesToString(this.formatAsByteArray);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  726 */       setHeaderFromMQMD_on_Receive(jmsMsg, currentFormat);
/*      */ 
/*      */       
/*  729 */       if (this.dataQuantityHint != 1) {
/*      */ 
/*      */         
/*  732 */         if (messageBodyMQ == true)
/*      */         {
/*  734 */           jmsMsg._importBody(getMessageData(), 0, getEncoding(), 
/*  735 */               JmqiCodepage.getJmqiCodepage(this.env, getCharacterSet(), getEncoding()));
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  740 */         else if (currentOffset < getMessageDataLength())
/*      */         {
/*  742 */           jmsMsg._importBody(getMessageData(), currentOffset, getEncoding(), 
/*  743 */               JmqiCodepage.getJmqiCodepage(this.env, getCharacterSet(), getEncoding()));
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  748 */         Trace.traceData(this, "createJMSMessage - HEADER_DATA. ignoring JMS body. dataQuantityHint: ", Integer.valueOf(this.dataQuantityHint));
/*  749 */         this.dataQuantityHint = 0;
/*      */       } 
/*      */ 
/*      */       
/*  753 */       jmsMsg._setReadOnly();
/*      */       
/*  755 */       if (Trace.isOn) {
/*  756 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination)", jmsMsg);
/*      */       }
/*      */       
/*  759 */       return jmsMsg;
/*      */     }
/*  761 */     catch (IOException e) {
/*  762 */       if (Trace.isOn) {
/*  763 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination)", e, 1);
/*      */       }
/*      */ 
/*      */       
/*  767 */       if (Trace.isOn) {
/*  768 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination)", e, 1);
/*      */       }
/*      */       
/*  771 */       throw e;
/*      */     }
/*  773 */     catch (MQException e) {
/*  774 */       if (Trace.isOn) {
/*  775 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination)", (Throwable)e, 2);
/*      */       }
/*      */ 
/*      */       
/*  779 */       JMSException je = new JMSException("MQJMS1000");
/*  780 */       je.setLinkedException((Exception)e);
/*  781 */       je.initCause((Throwable)e);
/*  782 */       if (Trace.isOn) {
/*  783 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  786 */       throw je;
/*      */     }
/*  788 */     catch (JMSException je) {
/*  789 */       if (Trace.isOn) {
/*  790 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */       
/*  794 */       if (Trace.isOn) {
/*  795 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination)", (Throwable)je, 3);
/*      */       }
/*      */       
/*  798 */       throw je;
/*      */     } finally {
/*      */       
/*  801 */       if (Trace.isOn) {
/*  802 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "createJMSMessage(MQSession,ProviderDestination)");
/*      */       }
/*      */ 
/*      */       
/*  806 */       setCharacterSet(origCharacterSet);
/*  807 */       setEncoding(origEncoding);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setHeaderFromMQMD(ProviderMessage jmsMsg) throws JMSException {
/*  813 */     if (Trace.isOn) {
/*  814 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "setHeaderFromMQMD(ProviderMessage)", "setter", jmsMsg);
/*      */     }
/*      */     
/*  817 */     setHeaderFromMQMD(jmsMsg, (ProviderDestination)null);
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
/*      */   protected void setHeaderFromMQMD(ProviderMessage jmsMsg, ProviderDestination destination) throws JMSException {
/*  855 */     if (Trace.isOn) {
/*  856 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "setHeaderFromMQMD(ProviderMessage,ProviderDestination)", new Object[] { jmsMsg, destination });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  866 */       jmsMsg.setJMSPriority(getPriority());
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
/*  880 */       if (jmsMsg.getJMSDeliveryMode().intValue() == -2 || this.persistenceFromMD == true) {
/*  881 */         if (getPersistence() == 0) {
/*  882 */           jmsMsg.setJMSDeliveryMode(1);
/*      */         }
/*  884 */         else if (getPersistence() == 1) {
/*  885 */           jmsMsg.setJMSDeliveryMode(2);
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */ 
/*      */           
/*  893 */           jmsMsg.setJMSDeliveryMode(-1);
/*      */         } 
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
/*      */ 
/*      */       
/*  907 */       String replyToURI = jmsMsg.getJMSReplyToAsString();
/*      */ 
/*      */       
/*  910 */       if (jmsMsg instanceof JMSMessage) {
/*      */         
/*  912 */         if (Trace.isOn) {
/*  913 */           Trace.traceData(this, "message is a JMSMessage", null);
/*      */         }
/*      */ 
/*      */         
/*  917 */         JMSMessage ibmMsg = (JMSMessage)jmsMsg;
/*      */ 
/*      */ 
/*      */         
/*  921 */         ibmMsg._setCcsidForStrings(_getCcsid(), _getIsCcsidAscii(), _doStringsNeedCcsidConversion());
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  926 */         ibmMsg._setJMSMessageIDAsBytes(getMessageId());
/*      */         
/*  928 */         ibmMsg._setJMSXUserIDFromBytes(getUserIdAsBytes());
/*      */ 
/*      */         
/*  931 */         ibmMsg._setJMSXPutAppIDFromBytes(getPutApplicationNameAsBytes());
/*      */         
/*  933 */         if ((getMessageFlags() & 0x8) != 0 || (
/*  934 */           getMessageFlags() & 0x10) != 0) {
/*      */           
/*  936 */           ibmMsg._setJMSXGroupSeqFromInt(getMessageSequenceNumber());
/*      */ 
/*      */           
/*  939 */           String groupIDString = JMSMessage._idToString(getGroupId());
/*  940 */           if (groupIDString != null && 
/*  941 */             ibmMsg.getStringProperty("JMSXGroupID") == null) {
/*  942 */             ibmMsg._setJMSXObjectProperty("JMSXGroupID", groupIDString);
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  948 */         ibmMsg._setJMSIBMPutDateFromBytes(getPutDateAsBytes());
/*      */ 
/*      */         
/*  951 */         ibmMsg._setJMSIBMPutTimeFromBytes(getPutTimeAsBytes());
/*      */ 
/*      */         
/*  954 */         ibmMsg._setJMSIBMPutApplTypeFromInt(getPutApplicationType());
/*      */       }
/*      */       else {
/*      */         
/*  958 */         if (Trace.isOn) {
/*  959 */           Trace.traceData(this, "message is from another vendor, only updating subset of properties", null);
/*      */         }
/*      */ 
/*      */         
/*  963 */         jmsMsg.setJMSMessageID(JMSMessage._idToString(getMessageId()));
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
/*  997 */       boolean goWithMD = true;
/*  998 */       if (destination != null) {
/*      */         
/* 1000 */         int replyToStyle = destination.getIntProperty("XMSC_WMQ_REPLYTO_STYLE");
/* 1001 */         if ((replyToStyle == 0 && defaultReplyToStyle == 1) || replyToStyle == 1) {
/*      */ 
/*      */           
/* 1004 */           goWithMD = true;
/*      */         } else {
/*      */           
/* 1007 */           goWithMD = false;
/*      */         } 
/*      */       } 
/*      */       
/* 1011 */       String mqmdReplyToQ = getReplyToQueueName();
/* 1012 */       mqmdReplyToQ = (mqmdReplyToQ != null) ? mqmdReplyToQ.trim() : null;
/*      */       
/* 1014 */       if (goWithMD && mqmdReplyToQ != null && !mqmdReplyToQ.equals(""))
/*      */       {
/* 1016 */         String mqmdReplyToQMgr = getReplyToQueueManagerName().trim();
/*      */         
/* 1018 */         StringBuffer replyToDestURI = new StringBuffer("queue://");
/* 1019 */         replyToDestURI.append(mqmdReplyToQMgr).append("/").append(mqmdReplyToQ);
/*      */         
/* 1021 */         String replyToParamters = null;
/* 1022 */         if (replyToURI != null) {
/* 1023 */           int positionOfQuestionMark = replyToURI.indexOf('?');
/* 1024 */           if (positionOfQuestionMark > 0) {
/* 1025 */             replyToParamters = replyToURI.substring(positionOfQuestionMark);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */ 
/*      */         
/*      */         }
/* 1035 */         else if (!this.hasRFH2 && this.targetClientMatching) {
/*      */ 
/*      */           
/* 1038 */           replyToParamters = "?targetClient=1";
/*      */         } 
/*      */ 
/*      */         
/* 1042 */         if (replyToParamters != null) {
/* 1043 */           replyToDestURI.append(replyToParamters);
/*      */         }
/* 1045 */         jmsMsg.setJMSReplyToAsString(replyToDestURI.toString());
/*      */       }
/*      */     
/* 1048 */     } catch (MQException e) {
/* 1049 */       if (Trace.isOn) {
/* 1050 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "setHeaderFromMQMD(ProviderMessage,ProviderDestination)", (Throwable)e);
/*      */       }
/*      */ 
/*      */       
/* 1054 */       JMSException je = new JMSException("MQJMS1000");
/* 1055 */       je.setLinkedException((Exception)e);
/* 1056 */       je.initCause((Throwable)e);
/* 1057 */       if (Trace.isOn) {
/* 1058 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "setHeaderFromMQMD(ProviderMessage,ProviderDestination)", (Throwable)je);
/*      */       }
/*      */       
/* 1061 */       throw je;
/*      */     } 
/*      */     
/* 1064 */     if (Trace.isOn) {
/* 1065 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "setHeaderFromMQMD(ProviderMessage,ProviderDestination)");
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
/*      */ 
/*      */ 
/*      */   
/*      */   private void setHeaderFromMQMD_on_Receive(JMSMessage jmsMsg, String bodyFormat) throws JMSException {
/* 1095 */     if (Trace.isOn) {
/* 1096 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "setHeaderFromMQMD_on_Receive(JMSMessage,String)", new Object[] { jmsMsg, bodyFormat });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1106 */       if (jmsMsg.getJMSCorrelationID() == null) {
/* 1107 */         jmsMsg.setJMSCorrelationIDAsBytes(getCorrelationIdRef());
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1113 */       if (jmsMsg.getJMSTimestamp() == null) {
/* 1114 */         jmsMsg.setJMSTimestamp(getPutTimeMillis());
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1122 */       if (jmsMsg.getJMSExpiration().longValue() == 0L && getExpiry() > 0)
/*      */       {
/* 1124 */         jmsMsg.setJMSExpiration(System.currentTimeMillis() + (getExpiry() * 100));
/*      */       }
/*      */ 
/*      */       
/* 1128 */       if (getBackoutCount() > 0) {
/* 1129 */         jmsMsg.setJMSRedelivered(true);
/*      */       }
/*      */ 
/*      */       
/* 1133 */       jmsMsg._setJMSXDeliveryCountFromInt(getBackoutCount() + 1);
/*      */ 
/*      */       
/* 1136 */       jmsMsg._setJMSIBMMsgTypeFromInt(getMessageType());
/*      */ 
/*      */       
/* 1139 */       jmsMsg._setJMSXObjectProperty("JMS_IBM_Format", bodyFormat);
/*      */ 
/*      */       
/* 1142 */       if (getFeedback() != 0) {
/* 1143 */         jmsMsg._setJMSIBMFeedbackFromInt(getFeedback());
/*      */       }
/*      */ 
/*      */       
/* 1147 */       int report = getReport();
/* 1148 */       int report_subset = report & 0x7000000;
/* 1149 */       if (report_subset != 0) {
/* 1150 */         jmsMsg._setJMSIBMReportExceptionFromInt(report_subset);
/*      */       }
/*      */ 
/*      */       
/* 1154 */       report_subset = report & 0xE00000;
/* 1155 */       if (report_subset != 0) {
/* 1156 */         jmsMsg._setJMSIBMReportExpirationFromInt(report_subset);
/*      */       }
/*      */ 
/*      */       
/* 1160 */       report_subset = report & 0x700;
/* 1161 */       if (report_subset != 0) {
/* 1162 */         jmsMsg._setJMSIBMReportCOAFromInt(report_subset);
/*      */       }
/*      */ 
/*      */       
/* 1166 */       report_subset = report & 0x3800;
/* 1167 */       if (report_subset != 0) {
/* 1168 */         jmsMsg._setJMSIBMReportCODFromInt(report_subset);
/*      */       }
/*      */ 
/*      */       
/* 1172 */       report_subset = report & 0x1;
/* 1173 */       if (report_subset != 0) {
/* 1174 */         jmsMsg._setJMSIBMReportPANFromInt(report_subset);
/*      */       }
/*      */ 
/*      */       
/* 1178 */       report_subset = report & 0x2;
/* 1179 */       if (report_subset != 0) {
/* 1180 */         jmsMsg._setJMSIBMReportNANFromInt(report_subset);
/*      */       }
/*      */ 
/*      */       
/* 1184 */       report_subset = report & 0x40;
/* 1185 */       if (report_subset != 0) {
/* 1186 */         jmsMsg._setJMSIBMPassCorrelIDFromInt(report_subset);
/*      */       }
/*      */ 
/*      */       
/* 1190 */       report_subset = report & 0x80;
/* 1191 */       if (report_subset != 0) {
/* 1192 */         jmsMsg._setJMSIBMReportMsgIDFromInt(report_subset);
/*      */       }
/*      */ 
/*      */       
/* 1196 */       report_subset = report & 0x8000000;
/* 1197 */       if (report_subset != 0) {
/* 1198 */         jmsMsg._setJMSIBMReportDiscardFromInt(report_subset);
/*      */       }
/*      */ 
/*      */       
/* 1202 */       if ((getMessageFlags() & 0x10) != 0) {
/* 1203 */         jmsMsg._setJMSIBMLastMsgInGroupFromBool(true);
/*      */       
/*      */       }
/*      */     }
/* 1207 */     catch (MQException e) {
/* 1208 */       if (Trace.isOn) {
/* 1209 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "setHeaderFromMQMD_on_Receive(JMSMessage,String)", (Throwable)e);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1214 */       JMSException je = new JMSException("MQJMS1000");
/*      */       
/* 1216 */       je.setLinkedException((Exception)e);
/* 1217 */       je.initCause((Throwable)e);
/* 1218 */       if (Trace.isOn) {
/* 1219 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "setHeaderFromMQMD_on_Receive(JMSMessage,String)", (Throwable)je);
/*      */       }
/*      */       
/* 1222 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/* 1226 */     if (Trace.isOn) {
/* 1227 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "setHeaderFromMQMD_on_Receive(JMSMessage,String)");
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
/*      */   protected void write(JMSMessage jmsMsg, boolean rfhReq, int characterSet, ProviderDestination destination) throws JMSException {
/* 1246 */     if (Trace.isOn) {
/* 1247 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "write(JMSMessage,boolean,int,ProviderDestination)", new Object[] { jmsMsg, 
/*      */             
/* 1249 */             Boolean.valueOf(rfhReq), Integer.valueOf(characterSet), destination });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1255 */       jmsMsg.setJMSRedelivered(false);
/* 1256 */       jmsMsg._setJMSXObjectProperty("JMSXDeliveryCount", ZERO);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1263 */       writeMQMD(jmsMsg);
/*      */       
/* 1265 */       if (rfhReq == true) {
/*      */ 
/*      */ 
/*      */         
/* 1269 */         writeRFH2(jmsMsg, characterSet);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1274 */         setCharacterSet(characterSet);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1280 */       overrideHeaderFromMQMDProps(destination, jmsMsg);
/*      */ 
/*      */ 
/*      */       
/* 1284 */       byte[] body = jmsMsg._exportBody(getEncoding(), JmqiCodepage.getJmqiCodepage(this.env, characterSet, getEncoding()));
/* 1285 */       if (body != null) {
/* 1286 */         appendByteArray(body);
/*      */ 
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1292 */     catch (IOException ex) {
/* 1293 */       if (Trace.isOn) {
/* 1294 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "write(JMSMessage,boolean,int,ProviderDestination)", ex, 1);
/*      */       }
/*      */       
/* 1297 */       String exString = ConfigEnvironment.getErrorMessage("MQJMS1065");
/*      */       
/* 1299 */       JMSException jmsEx = new JMSException(exString);
/* 1300 */       jmsEx.setLinkedException(ex);
/* 1301 */       jmsEx.initCause(ex);
/* 1302 */       if (Trace.isOn) {
/* 1303 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "write(JMSMessage,boolean,int,ProviderDestination)", (Throwable)jmsEx, 1);
/*      */       }
/*      */       
/* 1306 */       throw jmsEx;
/*      */     }
/* 1308 */     catch (MQException e) {
/* 1309 */       if (Trace.isOn) {
/* 1310 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "write(JMSMessage,boolean,int,ProviderDestination)", (Throwable)e, 2);
/*      */       }
/*      */ 
/*      */       
/* 1314 */       JMSException je = new JMSException("MQJMS1000");
/* 1315 */       je.setLinkedException((Exception)e);
/* 1316 */       je.initCause((Throwable)e);
/* 1317 */       if (Trace.isOn) {
/* 1318 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "write(JMSMessage,boolean,int,ProviderDestination)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1321 */       throw je;
/*      */     } 
/* 1323 */     if (Trace.isOn) {
/* 1324 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "write(JMSMessage,boolean,int,ProviderDestination)");
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
/*      */   private void writeMQMD(JMSMessage jmsMsg) throws JMSException {
/* 1349 */     if (Trace.isOn) {
/* 1350 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "writeMQMD(JMSMessage)", new Object[] { jmsMsg });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1356 */       setPriority(jmsMsg.getJMSPriority().intValue());
/*      */ 
/*      */ 
/*      */       
/* 1360 */       setMessageId(MQ_NONE);
/*      */ 
/*      */       
/* 1363 */       byte[] cidBytes = jmsMsg.getJMSCorrelationIDAsBytes();
/* 1364 */       if (cidBytes != null) {
/*      */ 
/*      */         
/* 1367 */         setCorrelationId(cidBytes);
/*      */       }
/*      */       else {
/*      */         
/* 1371 */         String cidString = jmsMsg.getJMSCorrelationID();
/* 1372 */         if (cidString == null) {
/* 1373 */           setCorrelationId(MQ_NONE);
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/*      */             
/* 1379 */             setCorrelationId(cidString.getBytes("UTF8"));
/*      */           }
/* 1381 */           catch (UnsupportedEncodingException uee) {
/* 1382 */             if (Trace.isOn) {
/* 1383 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "writeMQMD(JMSMessage)", uee, 1);
/*      */             }
/*      */             
/* 1386 */             int key = 1022;
/* 1387 */             String exString = MQSession.jmsStrings.getErrorMessage(key);
/* 1388 */             JMSException jmsEx = new JMSException(exString, MQSession.jmsStrings.getNativeKey(key));
/* 1389 */             jmsEx.setLinkedException(uee);
/* 1390 */             jmsEx.initCause(uee);
/* 1391 */             if (Trace.isOn) {
/* 1392 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "writeMQMD(JMSMessage)", (Throwable)jmsEx, 1);
/*      */             }
/*      */             
/* 1395 */             throw jmsEx;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1401 */       int deliveryMode = jmsMsg.getJMSDeliveryMode().intValue();
/* 1402 */       if (deliveryMode == 1) {
/* 1403 */         setPersistence(0);
/*      */       }
/* 1405 */       else if (deliveryMode == 2) {
/* 1406 */         setPersistence(1);
/*      */       } else {
/*      */         
/* 1409 */         String exString = ConfigEnvironment.getErrorMessage("MQJMS1042");
/* 1410 */         JMSException traceRet1 = new JMSException(exString);
/* 1411 */         if (Trace.isOn) {
/* 1412 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "writeMQMD(JMSMessage)", (Throwable)traceRet1, 2);
/*      */         }
/*      */         
/* 1415 */         throw traceRet1;
/*      */       } 
/*      */ 
/*      */       
/* 1419 */       if (jmsMsg instanceof JMSTextMessage || jmsMsg instanceof JMSStreamMessage || jmsMsg instanceof JMSMapMessage) {
/* 1420 */         setFormat("MQSTR   ");
/*      */       } else {
/*      */         
/* 1423 */         setFormat("        ");
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1432 */       long timeToLive = jmsMsg._getTimeToLive();
/*      */       
/* 1434 */       if (timeToLive == 0L) {
/* 1435 */         setExpiry(-1);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1440 */       else if (timeToLive < 214748364700L) {
/*      */         
/* 1442 */         setExpiry((int)((timeToLive + 100L) / 100L));
/*      */       }
/*      */       else {
/*      */         
/* 1446 */         setExpiry(-1);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1451 */       String replyTo = jmsMsg.getJMSReplyToAsString();
/*      */       
/* 1453 */       resetReplyToQueueName();
/* 1454 */       resetReplyToQueueManagerName();
/*      */       
/* 1456 */       if (replyTo != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1464 */         WMQDestinationURIParser jmsReplyToParser = new WMQDestinationURIParser();
/* 1465 */         jmsReplyToParser.setUri(replyTo);
/*      */         
/* 1467 */         if (jmsReplyToParser.getDomain() == 1) {
/* 1468 */           String qName = jmsReplyToParser.getDestinationName();
/* 1469 */           if (qName != null) {
/* 1470 */             setReplyToQueueName(qName);
/*      */           }
/*      */           
/* 1473 */           String qMgrName = jmsReplyToParser.getQmName();
/* 1474 */           if (qMgrName != null) {
/* 1475 */             setReplyToQueueManagerName(qMgrName);
/*      */           }
/*      */           
/* 1478 */           setMessageType(1);
/*      */         } else {
/*      */           
/* 1481 */           setMessageType(8);
/*      */         } 
/*      */       } else {
/*      */         
/* 1485 */         setMessageType(8);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1491 */       Enumeration<?> propNames = jmsMsg.getPropertyNames();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1500 */       setReport(0);
/* 1501 */       setFeedback(0);
/* 1502 */       setGroupId(MQ_NONE);
/* 1503 */       setMessageSequenceNumber(1);
/* 1504 */       setOffset(0);
/* 1505 */       setMessageFlags(0);
/* 1506 */       setOriginalLength(-1);
/*      */ 
/*      */       
/* 1509 */       String dest = jmsMsg.getJMSDestinationAsString();
/* 1510 */       boolean isQueue = dest.startsWith("queue://");
/*      */       
/* 1512 */       while (propNames.hasMoreElements()) {
/* 1513 */         String name = (String)propNames.nextElement();
/*      */ 
/*      */         
/* 1516 */         if (name.startsWith("JMS")) {
/* 1517 */           if (Trace.isOn) {
/* 1518 */             Trace.traceData(this, "Processing property " + name, null);
/*      */           }
/*      */ 
/*      */           
/* 1522 */           if (isQueue && name.equals("JMSXGroupID")) {
/* 1523 */             String jmsxGroupId = jmsMsg.getStringProperty("JMSXGroupID");
/* 1524 */             if (jmsxGroupId != null) {
/* 1525 */               if (jmsxGroupId.startsWith("ID:")) {
/* 1526 */                 setGroupId(jmsMsg._stringToId(jmsxGroupId));
/*      */               } else {
/*      */                 
/* 1529 */                 setGroupId(jmsxGroupId.getBytes("UTF8"));
/*      */               } 
/* 1531 */               setMessageFlags(getMessageFlags() | 0x8);
/*      */             } 
/*      */             continue;
/*      */           } 
/* 1535 */           if (isQueue && name.equals("JMSXGroupSeq")) {
/* 1536 */             int jmsxGroupSeq = 0;
/*      */             try {
/* 1538 */               jmsxGroupSeq = jmsMsg.getIntProperty("JMSXGroupSeq");
/*      */             }
/* 1540 */             catch (Exception jmsEx) {
/* 1541 */               if (Trace.isOn) {
/* 1542 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "writeMQMD(JMSMessage)", jmsEx, 2);
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1548 */               if (Trace.isOn) {
/* 1549 */                 Trace.traceData(this, "Invalid value passed to JMSXGroupSeq " + jmsEx, null);
/*      */               }
/*      */             } 
/*      */             
/* 1553 */             if (jmsxGroupSeq > 0) {
/* 1554 */               setMessageSequenceNumber(jmsMsg._getJMSXGroupSeqAsInt());
/* 1555 */               setMessageFlags(getMessageFlags() | 0x8);
/*      */             }  continue;
/*      */           } 
/* 1558 */           if (name.startsWith("JMS_IBM_"))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1566 */             if (name.equals("JMS_IBM_Format")) {
/* 1567 */               setFormat(jmsMsg.getStringProperty("JMS_IBM_Format")); continue;
/*      */             } 
/* 1569 */             if (name.equals("JMS_IBM_MsgType")) {
/* 1570 */               setMessageType(jmsMsg._getJMSIBMMsgTypeAsInt()); continue;
/*      */             } 
/* 1572 */             if (name.equals("JMS_IBM_Feedback")) {
/* 1573 */               setFeedback(jmsMsg._getJMSIBMFeedbackAsInt()); continue;
/*      */             } 
/* 1575 */             if (name.equals("JMS_IBM_Report_Exception")) {
/* 1576 */               setReport(getReport() | jmsMsg._getJMSIBMReportExceptionAsInt() & 0x7000000); continue;
/*      */             } 
/* 1578 */             if (name.equals("JMS_IBM_Report_Expiration")) {
/* 1579 */               setReport(getReport() | jmsMsg._getJMSIBMReportExpirationAsInt() & 0xE00000); continue;
/*      */             } 
/* 1581 */             if (name.equals("JMS_IBM_Report_COA")) {
/* 1582 */               setReport(getReport() | jmsMsg._getJMSIBMReportCOAAsInt() & 0x700); continue;
/*      */             } 
/* 1584 */             if (name.equals("JMS_IBM_Report_COD")) {
/* 1585 */               setReport(getReport() | jmsMsg._getJMSIBMReportCODAsInt() & 0x3800); continue;
/*      */             } 
/* 1587 */             if (name.equals("JMS_IBM_Report_PAN")) {
/* 1588 */               setReport(getReport() | jmsMsg._getJMSIBMReportPANAsInt() & 0x1); continue;
/*      */             } 
/* 1590 */             if (name.equals("JMS_IBM_Report_NAN")) {
/* 1591 */               setReport(getReport() | jmsMsg._getJMSIBMReportNANAsInt() & 0x2); continue;
/*      */             } 
/* 1593 */             if (name.equals("JMS_IBM_Report_Pass_Msg_ID")) {
/* 1594 */               setReport(getReport() | jmsMsg._getJMSIBMReportMsgIDAsInt() & 0x80); continue;
/*      */             } 
/* 1596 */             if (name.equals("JMS_IBM_Report_Pass_Correl_ID")) {
/* 1597 */               setReport(getReport() | jmsMsg._getJMSIBMPassCorrelIDAsInt() & 0x40); continue;
/*      */             } 
/* 1599 */             if (name.equals("JMS_IBM_Report_Discard_Msg")) {
/* 1600 */               setReport(getReport() | jmsMsg._getJMSIBMReportDiscardAsInt() & 0x8000000);
/*      */               continue;
/*      */             } 
/* 1603 */             if (name.equals("JMS_IBM_Last_Msg_In_Group") && isQueue)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1611 */               if (jmsMsg._getJMSIBMLastMsgInGroupAsBool()) {
/* 1612 */                 setMessageFlags(getMessageFlags() | 0x10);
/*      */               
/*      */               }
/*      */             
/*      */             }
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1624 */     catch (UnsupportedEncodingException ex) {
/* 1625 */       if (Trace.isOn) {
/* 1626 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "writeMQMD(JMSMessage)", ex, 3);
/*      */       }
/*      */       
/* 1629 */       JMSException jmsEx = ConfigEnvironment.newException("MQJMS1059");
/* 1630 */       jmsEx.setLinkedException(ex);
/* 1631 */       jmsEx.initCause(ex);
/* 1632 */       if (Trace.isOn) {
/* 1633 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "writeMQMD(JMSMessage)", (Throwable)jmsEx, 3);
/*      */       }
/*      */       
/* 1636 */       throw jmsEx;
/*      */     }
/* 1638 */     catch (MQException e) {
/* 1639 */       if (Trace.isOn) {
/* 1640 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "writeMQMD(JMSMessage)", (Throwable)e, 4);
/*      */       }
/*      */       
/* 1643 */       JMSException jmsEx = ConfigEnvironment.newException("MQJMS1016");
/* 1644 */       jmsEx.setLinkedException((Exception)e);
/* 1645 */       jmsEx.initCause((Throwable)e);
/* 1646 */       if (Trace.isOn) {
/* 1647 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "writeMQMD(JMSMessage)", (Throwable)jmsEx, 4);
/*      */       }
/*      */       
/* 1650 */       throw jmsEx;
/*      */     } 
/*      */     
/* 1653 */     if (Trace.isOn) {
/* 1654 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "writeMQMD(JMSMessage)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeRFH(String Directive, JMSMessage jmsMsg, boolean RFH2req, int bodyCharacterSetP, ProviderDestination destination) throws JMSException, IOException {
/* 1665 */     if (Trace.isOn) {
/* 1666 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "writeRFH(String,JMSMessage,boolean,int,ProviderDestination)", new Object[] { Directive, jmsMsg, 
/*      */             
/* 1668 */             Boolean.valueOf(RFH2req), Integer.valueOf(bodyCharacterSetP), destination });
/*      */     }
/* 1670 */     int bodyCharacterSet = bodyCharacterSetP;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1677 */       String PaddedNVPairs = Directive + "               ";
/*      */ 
/*      */       
/* 1680 */       int NVPairsLength = PaddedNVPairs.length() / 16 * 16;
/*      */ 
/*      */       
/* 1683 */       String NameValueString = PaddedNVPairs.substring(0, NVPairsLength);
/*      */       
/* 1685 */       int encoding = getEncoding();
/* 1686 */       int cset = getCharacterSet();
/*      */       
/* 1688 */       JmqiCodepage codepage = JmqiCodepage.getJmqiCodepage(this.env, cset, encoding);
/*      */ 
/*      */ 
/*      */       
/* 1692 */       appendByteArray(codepage.stringToBytes("RFH "));
/* 1693 */       appendInt(1, encoding);
/* 1694 */       appendInt(32 + NVPairsLength, encoding);
/*      */       
/* 1696 */       appendInt(encoding, encoding);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1701 */       if (bodyCharacterSet == 0 || bodyCharacterSet == -2)
/*      */       {
/*      */ 
/*      */ 
/*      */         
/* 1706 */         bodyCharacterSet = cset;
/*      */       }
/*      */       
/* 1709 */       if (RFH2req) {
/*      */         
/* 1711 */         appendInt(cset, encoding);
/* 1712 */         appendByteArray(codepage.stringToBytes("MQHRF2  "));
/*      */       } else {
/*      */         
/* 1715 */         appendInt(bodyCharacterSet, encoding);
/*      */ 
/*      */         
/* 1718 */         if (jmsMsg instanceof JMSTextMessage || jmsMsg instanceof JMSStreamMessage || jmsMsg instanceof JMSMapMessage) {
/*      */ 
/*      */           
/* 1721 */           appendByteArray(codepage.stringToBytes("MQSTR   "));
/*      */         } else {
/*      */           
/* 1724 */           appendByteArray(codepage.stringToBytes("        "));
/*      */         } 
/* 1726 */         setFormat("MQHRF   ");
/*      */       } 
/* 1728 */       appendInt(0, encoding);
/* 1729 */       appendByteArray(codepage.stringToBytes(NameValueString));
/*      */       
/* 1731 */       write(jmsMsg, RFH2req, bodyCharacterSet, destination);
/*      */     }
/* 1733 */     catch (MQException e) {
/* 1734 */       if (Trace.isOn) {
/* 1735 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "writeRFH(String,JMSMessage,boolean,int,ProviderDestination)", (Throwable)e);
/*      */       }
/*      */ 
/*      */       
/* 1739 */       JMSException je = new JMSException("MQJMS1000");
/* 1740 */       je.setLinkedException((Exception)e);
/* 1741 */       je.initCause((Throwable)e);
/* 1742 */       if (Trace.isOn) {
/* 1743 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "writeRFH(String,JMSMessage,boolean,int,ProviderDestination)", (Throwable)je);
/*      */       }
/*      */       
/* 1746 */       throw je;
/*      */     } 
/* 1748 */     if (Trace.isOn) {
/* 1749 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "writeRFH(String,JMSMessage,boolean,int,ProviderDestination)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int reverseInt(int integer) {
/* 1757 */     if (Trace.isOn)
/* 1758 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "reverseInt(int)", new Object[] {
/* 1759 */             Integer.valueOf(integer)
/*      */           }); 
/* 1761 */     int regetni = 0;
/* 1762 */     regetni = (integer >> 24 & 0xFF) << 0 | (integer >> 16 & 0xFF) << 8 | (integer >> 8 & 0xFF) << 16 | (integer >> 0 & 0xFF) << 24;
/* 1763 */     if (Trace.isOn) {
/* 1764 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "reverseInt(int)", 
/* 1765 */           Integer.valueOf(regetni));
/*      */     }
/* 1767 */     return regetni;
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
/*      */   protected byte[] buildCacheableRFH(String directive, int bodyCharacterSet, int enc) throws JMSException {
/* 1783 */     if (Trace.isOn) {
/* 1784 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "buildCacheableRFH(String,int,int)", new Object[] { directive, 
/*      */             
/* 1786 */             Integer.valueOf(bodyCharacterSet), Integer.valueOf(enc) });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1792 */       ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 1793 */       DataOutputStream dos = new DataOutputStream(baos);
/*      */ 
/*      */ 
/*      */       
/* 1797 */       String PaddedNVPairs = directive + "               ";
/* 1798 */       int NVPairsLength = PaddedNVPairs.length() / 16 * 16;
/* 1799 */       String NameValueString = PaddedNVPairs.substring(0, NVPairsLength);
/*      */       
/* 1801 */       int version = 1;
/* 1802 */       int length = 32 + NVPairsLength;
/* 1803 */       int encoding = getEncoding();
/* 1804 */       int cset = getCharacterSet();
/* 1805 */       JmqiCodepage codepage = JmqiCodepage.getJmqiCodepage(this.env, cset, encoding);
/*      */ 
/*      */       
/* 1808 */       if ((enc & 0xF) == 2) {
/* 1809 */         version = reverseInt(version);
/* 1810 */         length = reverseInt(length);
/* 1811 */         encoding = reverseInt(encoding);
/* 1812 */         cset = reverseInt(cset);
/*      */       } 
/*      */ 
/*      */       
/* 1816 */       dos.write(codepage.stringToBytes("RFH "));
/* 1817 */       dos.writeInt(version);
/* 1818 */       dos.writeInt(length);
/* 1819 */       dos.writeInt(encoding);
/*      */ 
/*      */ 
/*      */       
/* 1823 */       dos.writeInt(cset);
/* 1824 */       dos.write(codepage.stringToBytes("MQHRF2  "));
/*      */       
/* 1826 */       dos.writeInt(0);
/* 1827 */       dos.write(codepage.stringToBytes(NameValueString));
/*      */       
/* 1829 */       dos.flush();
/* 1830 */       byte[] traceRet1 = baos.toByteArray();
/* 1831 */       if (Trace.isOn) {
/* 1832 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "buildCacheableRFH(String,int,int)", traceRet1);
/*      */       }
/*      */       
/* 1835 */       return traceRet1;
/*      */     }
/* 1837 */     catch (IOException e) {
/* 1838 */       if (Trace.isOn) {
/* 1839 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "buildCacheableRFH(String,int,int)", e);
/*      */       }
/*      */ 
/*      */       
/* 1843 */       JMSException je = new JMSException("MQJMS1000");
/* 1844 */       je.setLinkedException(e);
/* 1845 */       je.initCause(e);
/* 1846 */       if (Trace.isOn) {
/* 1847 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "buildCacheableRFH(String,int,int)", (Throwable)je);
/*      */       }
/*      */       
/* 1850 */       throw je;
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
/*      */   private final void insertIntIntoByteArray(int i, byte[] array, int off, int encoding) {
/* 1865 */     if (Trace.isOn) {
/* 1866 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "insertIntIntoByteArray(int,byte [ ],int,int)", new Object[] {
/* 1867 */             Integer.valueOf(i), array, 
/* 1868 */             Integer.valueOf(off), Integer.valueOf(encoding)
/*      */           });
/*      */     }
/* 1871 */     switch (encoding & 0xF) {
/*      */       case 0:
/*      */       case 1:
/* 1874 */         array[off + 0] = (byte)(i >>> 24 & 0xFF);
/* 1875 */         array[off + 1] = (byte)(i >>> 16 & 0xFF);
/* 1876 */         array[off + 2] = (byte)(i >>> 8 & 0xFF);
/* 1877 */         array[off + 3] = (byte)(i >>> 0 & 0xFF);
/*      */         break;
/*      */       case 2:
/* 1880 */         array[off + 3] = (byte)(i >>> 24 & 0xFF);
/* 1881 */         array[off + 2] = (byte)(i >>> 16 & 0xFF);
/* 1882 */         array[off + 1] = (byte)(i >>> 8 & 0xFF);
/* 1883 */         array[off + 0] = (byte)(i >>> 0 & 0xFF);
/*      */         break;
/*      */       default:
/* 1886 */         if (Trace.isOn) {
/* 1887 */           Trace.traceData(this, "Invalid encoding in insertIntIntoByteArray: " + (encoding & 0xF), null);
/*      */         }
/*      */         break;
/*      */     } 
/* 1891 */     if (Trace.isOn) {
/* 1892 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "insertIntIntoByteArray(int,byte [ ],int,int)");
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
/*      */   private void writeRFH2(JMSMessage jmsMsg, int bodyCharacterSet) throws JMSException, IOException, MQException {
/*      */     byte[] usrBytes;
/*      */     int usrPad, usrLength;
/*      */     byte[] pscBytes;
/*      */     int pscPad, pscLength;
/*      */     byte[] mqextBytes;
/*      */     int mqextPad, mqextLength;
/* 1916 */     if (Trace.isOn) {
/* 1917 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "writeRFH2(JMSMessage,int)", new Object[] { jmsMsg, 
/* 1918 */             Integer.valueOf(bodyCharacterSet) });
/*      */     }
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
/* 1938 */     byte[] mcdBytes = jmsMsg._getMcdFolder().getBytes("UTF8");
/* 1939 */     int mcdPad = 3 - (mcdBytes.length - 1) % 4;
/* 1940 */     int mcdLength = mcdBytes.length + mcdPad;
/*      */     
/* 1942 */     byte[] jmsBytes = jmsMsg._getJmsFolder(this.persistenceFromMD).getBytes("UTF8");
/* 1943 */     int jmsPad = 3 - (jmsBytes.length - 1) % 4;
/* 1944 */     int jmsLength = jmsBytes.length + jmsPad;
/*      */ 
/*      */     
/* 1947 */     int rfhLength = 44 + mcdLength + jmsLength;
/*      */ 
/*      */ 
/*      */     
/* 1951 */     String rfhString = jmsMsg._getUsrFolder();
/* 1952 */     if (rfhString.equals("<usr></usr>")) {
/* 1953 */       usrBytes = null;
/* 1954 */       usrPad = 0;
/* 1955 */       usrLength = 0;
/*      */     } else {
/*      */       
/* 1958 */       usrBytes = rfhString.getBytes("UTF8");
/* 1959 */       usrPad = 3 - (usrBytes.length - 1) % 4;
/* 1960 */       usrLength = usrBytes.length + usrPad;
/* 1961 */       rfhLength += usrLength;
/* 1962 */       rfhLength += 4;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1967 */     rfhString = jmsMsg._getPscFolder();
/* 1968 */     if (rfhString.equals("<psc></psc>")) {
/* 1969 */       pscBytes = null;
/* 1970 */       pscPad = 0;
/* 1971 */       pscLength = 0;
/*      */     } else {
/*      */       
/* 1974 */       pscBytes = rfhString.getBytes("UTF8");
/* 1975 */       pscPad = 3 - (pscBytes.length - 1) % 4;
/* 1976 */       pscLength = pscBytes.length + pscPad;
/* 1977 */       rfhLength += pscLength;
/* 1978 */       rfhLength += 4;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1983 */     rfhString = jmsMsg._getMQExtFolder();
/* 1984 */     if (rfhString == null) {
/* 1985 */       mqextBytes = null;
/* 1986 */       mqextPad = 0;
/* 1987 */       mqextLength = 0;
/*      */     } else {
/*      */       
/* 1990 */       mqextBytes = rfhString.getBytes("UTF8");
/* 1991 */       mqextPad = 3 - (mqextBytes.length - 1) % 4;
/* 1992 */       mqextLength = mqextBytes.length + mqextPad;
/* 1993 */       rfhLength += mqextLength;
/* 1994 */       rfhLength += 4;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1999 */     if (this.cachedFixedRFH2 == null) {
/* 2000 */       this.cachedRFH2Length = rfhLength;
/* 2001 */       this.cachedRFH2Encoding = getEncoding();
/* 2002 */       this.cachedRFH2CharSet = bodyCharacterSet;
/* 2003 */       this.cachedRFH2Format = getFormat();
/*      */ 
/*      */ 
/*      */       
/* 2007 */       int off = 0;
/* 2008 */       this.cachedFixedRFH2 = new byte[36];
/* 2009 */       insertStrIntoByteArray("RFH ", 4, this.cachedFixedRFH2, off, getCharacterSet(), this.cachedRFH2Encoding);
/*      */ 
/*      */       
/* 2012 */       off += "RFH ".length();
/* 2013 */       insertIntIntoByteArray(2, this.cachedFixedRFH2, off, this.cachedRFH2Encoding);
/* 2014 */       off += 4;
/* 2015 */       insertIntIntoByteArray(this.cachedRFH2Length, this.cachedFixedRFH2, off, this.cachedRFH2Encoding);
/* 2016 */       off += 4;
/* 2017 */       insertIntIntoByteArray(this.cachedRFH2Encoding, this.cachedFixedRFH2, off, this.cachedRFH2Encoding);
/* 2018 */       off += 4;
/* 2019 */       insertIntIntoByteArray(this.cachedRFH2CharSet, this.cachedFixedRFH2, off, this.cachedRFH2Encoding);
/* 2020 */       off += 4;
/* 2021 */       insertStrIntoByteArray(this.cachedRFH2Format, 8, this.cachedFixedRFH2, off, getCharacterSet(), this.cachedRFH2Encoding);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2026 */       off += 8;
/* 2027 */       insertIntIntoByteArray(0, this.cachedFixedRFH2, off, this.cachedRFH2Encoding);
/* 2028 */       off += 4;
/* 2029 */       insertIntIntoByteArray(1208, this.cachedFixedRFH2, off, this.cachedRFH2Encoding);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 2035 */       boolean newEncoding = false;
/*      */       
/* 2037 */       if (this.cachedRFH2Encoding != getEncoding()) {
/* 2038 */         newEncoding = true;
/* 2039 */         this.cachedRFH2Encoding = getEncoding();
/* 2040 */         insertIntIntoByteArray(this.cachedRFH2Encoding, this.cachedFixedRFH2, 12, this.cachedRFH2Encoding);
/*      */         
/* 2042 */         insertIntIntoByteArray(1208, this.cachedFixedRFH2, 32, this.cachedRFH2Encoding);
/* 2043 */         insertIntIntoByteArray(2, this.cachedFixedRFH2, 4, this.cachedRFH2Encoding);
/*      */       } 
/*      */       
/* 2046 */       if (newEncoding || this.cachedRFH2Length != rfhLength) {
/* 2047 */         this.cachedRFH2Length = rfhLength;
/* 2048 */         insertIntIntoByteArray(this.cachedRFH2Length, this.cachedFixedRFH2, 8, this.cachedRFH2Encoding);
/*      */       } 
/*      */       
/* 2051 */       if (newEncoding || this.cachedRFH2CharSet != bodyCharacterSet) {
/* 2052 */         this.cachedRFH2CharSet = bodyCharacterSet;
/*      */         
/* 2054 */         insertIntIntoByteArray(this.cachedRFH2CharSet, this.cachedFixedRFH2, 16, this.cachedRFH2Encoding);
/*      */       } 
/* 2056 */       if (!this.cachedRFH2Format.equals(getFormat())) {
/* 2057 */         this.cachedRFH2Format = getFormat();
/* 2058 */         insertStrIntoByteArray(this.cachedRFH2Format, 8, this.cachedFixedRFH2, 20, getCharacterSet(), this.cachedRFH2Encoding);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2068 */     setFormat("MQHRF2  ");
/*      */ 
/*      */     
/* 2071 */     appendByteArray(this.cachedFixedRFH2);
/*      */ 
/*      */     
/* 2074 */     if (pscBytes != null) {
/* 2075 */       appendInt(pscLength, getEncoding());
/* 2076 */       appendByteArray(pscBytes);
/* 2077 */       appendByteArray(SPACES, 0, pscPad);
/*      */     } 
/*      */ 
/*      */     
/* 2081 */     appendInt(mcdLength, getEncoding());
/* 2082 */     appendByteArray(mcdBytes);
/* 2083 */     appendByteArray(SPACES, 0, mcdPad);
/*      */     
/* 2085 */     appendInt(jmsLength, getEncoding());
/* 2086 */     appendByteArray(jmsBytes);
/* 2087 */     appendByteArray(SPACES, 0, jmsPad);
/*      */     
/* 2089 */     if (mqextBytes != null) {
/* 2090 */       appendInt(mqextLength, getEncoding());
/* 2091 */       appendByteArray(mqextBytes);
/* 2092 */       appendByteArray(SPACES, 0, mqextPad);
/*      */     } 
/*      */     
/* 2095 */     if (usrBytes != null) {
/* 2096 */       appendInt(usrLength, getEncoding());
/* 2097 */       appendByteArray(usrBytes);
/* 2098 */       appendByteArray(SPACES, 0, usrPad);
/*      */     } 
/* 2100 */     if (Trace.isOn) {
/* 2101 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "writeRFH2(JMSMessage,int)");
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
/*      */   private void insertStrIntoByteArray(String src, int dstLen, byte[] dst, int offset, int ccsid, int enc) throws JMSException {
/* 2119 */     if (Trace.isOn) {
/* 2120 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "insertStrIntoByteArray(String,int,byte [ ],int,int,int)", new Object[] { src, 
/*      */             
/* 2122 */             Integer.valueOf(dstLen), dst, Integer.valueOf(offset), Integer.valueOf(ccsid), 
/* 2123 */             Integer.valueOf(enc) });
/*      */     }
/*      */     try {
/* 2126 */       JmqiCodepage codepage = JmqiCodepage.getJmqiCodepage(this.env, ccsid, enc);
/*      */       
/* 2128 */       int lenCopied = 0;
/*      */       
/* 2130 */       if (src != null) {
/*      */         
/* 2132 */         byte[] b = codepage.stringToBytes(src);
/*      */ 
/*      */         
/* 2135 */         System.arraycopy(b, 0, dst, offset, b.length);
/* 2136 */         lenCopied = b.length;
/*      */       } else {
/*      */         
/* 2139 */         lenCopied = 0;
/*      */       } 
/*      */ 
/*      */       
/* 2143 */       if (dstLen > lenCopied) {
/* 2144 */         byte spaceByte = codepage.spaceByte;
/*      */         
/* 2146 */         for (int j = lenCopied; j < dstLen; j++) {
/* 2147 */           dst[offset + j] = spaceByte;
/*      */         
/*      */         }
/*      */       }
/*      */     
/*      */     }
/* 2153 */     catch (CharacterCodingException|UnsupportedEncodingException e) {
/* 2154 */       if (Trace.isOn) {
/* 2155 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "insertStrIntoByteArray(String,int,byte [ ],int,int,int)", e);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2160 */       JMSException jmsEx = ConfigEnvironment.newException("MQJMS1016");
/* 2161 */       jmsEx.setLinkedException(e);
/* 2162 */       if (Trace.isOn) {
/* 2163 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "insertStrIntoByteArray(String,int,byte [ ],int,int,int)", (Throwable)jmsEx);
/*      */       }
/*      */       
/* 2166 */       throw jmsEx;
/*      */     } 
/* 2168 */     if (Trace.isOn) {
/* 2169 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "insertStrIntoByteArray(String,int,byte [ ],int,int,int)");
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
/*      */   protected static int getCCSID(String charSet) throws JMSException {
/*      */     JmqiCodepage cp;
/* 2184 */     if (Trace.isOn) {
/* 2185 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "getCCSID(String)", new Object[] { charSet });
/*      */     }
/*      */     
/* 2188 */     int retVal = 0;
/*      */ 
/*      */     
/* 2191 */     JmqiEnvironment env = MQSESSION.getJmqiEnv();
/*      */     
/*      */     try {
/* 2194 */       cp = JmqiCodepage.getJmqiCodepage(env, charSet);
/*      */       
/* 2196 */       if (cp == null) {
/* 2197 */         UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException(charSet);
/* 2198 */         if (Trace.isOn) {
/* 2199 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "getCCSID(String)", traceRet1, 1);
/*      */         }
/*      */         
/* 2202 */         throw traceRet1;
/*      */       }
/*      */     
/*      */     }
/* 2206 */     catch (UnsupportedEncodingException e) {
/* 2207 */       if (Trace.isOn) {
/* 2208 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "getCCSID(String)", e);
/*      */       }
/*      */       
/* 2211 */       JMSException bve = ConfigEnvironment.newException("MQJMS1006", "JMS_IBM_Character_Set", charSet);
/* 2212 */       if (Trace.isOn) {
/* 2213 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "getCCSID(String)", (Throwable)bve, 2);
/*      */       }
/*      */       
/* 2216 */       throw bve;
/*      */     } 
/*      */     
/* 2219 */     retVal = cp.getCCSID();
/*      */     
/* 2221 */     if (Trace.isOn) {
/* 2222 */       Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "Mapped " + charSet + " -> " + retVal, null);
/*      */     }
/* 2224 */     if (Trace.isOn) {
/* 2225 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "getCCSID(String)", 
/* 2226 */           Integer.valueOf(retVal));
/*      */     }
/* 2228 */     return retVal;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setPersistenceFromMD(boolean x) {
/* 2233 */     if (Trace.isOn) {
/* 2234 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "setPersistenceFromMD(boolean)", "setter", 
/* 2235 */           Boolean.valueOf(x));
/*      */     }
/* 2237 */     this.persistenceFromMD = x;
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
/*      */   private void setMQMDPropsFromHeader(ProviderDestination destination, JMSMessage message, int origCharacterSet, int origEncoding) throws JMSException, MQException {
/* 2262 */     if (Trace.isOn) {
/* 2263 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "setMQMDPropsFromHeader(ProviderDestination,JMSMessage,int,int)", new Object[] { destination, message, 
/*      */             
/* 2265 */             Integer.valueOf(origCharacterSet), Integer.valueOf(origEncoding) });
/*      */     }
/* 2267 */     if (destination != null && destination.getBooleanProperty("mdReadEnabled") == true) {
/*      */       
/* 2269 */       int report = getReport();
/* 2270 */       message.setIntProperty("JMS_IBM_MQMD_Report", report);
/*      */ 
/*      */       
/* 2273 */       int msgType = getMessageType();
/* 2274 */       message.setIntProperty("JMS_IBM_MQMD_MsgType", msgType);
/*      */ 
/*      */       
/* 2277 */       int expiry = getExpiry();
/* 2278 */       message.setIntProperty("JMS_IBM_MQMD_Expiry", expiry);
/*      */ 
/*      */       
/* 2281 */       int feedback = getFeedback();
/* 2282 */       message.setIntProperty("JMS_IBM_MQMD_Feedback", feedback);
/*      */ 
/*      */       
/* 2285 */       message.setIntProperty("JMS_IBM_MQMD_Encoding", origEncoding);
/*      */ 
/*      */       
/* 2288 */       message.setIntProperty("JMS_IBM_MQMD_CodedCharSetId", origCharacterSet);
/*      */ 
/*      */       
/* 2291 */       String format = getFormat();
/* 2292 */       message.setStringProperty("JMS_IBM_MQMD_Format", format);
/*      */ 
/*      */       
/* 2295 */       int priority = getPriority();
/* 2296 */       message.setIntProperty("JMS_IBM_MQMD_Priority", priority);
/*      */ 
/*      */       
/* 2299 */       int persistence = getPersistence();
/* 2300 */       message.setIntProperty("JMS_IBM_MQMD_Persistence", persistence);
/*      */ 
/*      */       
/* 2303 */       byte[] msgId = getMessageId();
/* 2304 */       message.setObjectProperty("JMS_IBM_MQMD_MsgId", msgId);
/*      */ 
/*      */       
/* 2307 */       byte[] correlId = getCorrelationId();
/* 2308 */       message.setObjectProperty("JMS_IBM_MQMD_CorrelId", correlId);
/*      */ 
/*      */       
/* 2311 */       int backoutCount = getBackoutCount();
/* 2312 */       message.setIntProperty("JMS_IBM_MQMD_BackoutCount", backoutCount);
/*      */ 
/*      */       
/* 2315 */       String replyToQ = getReplyToQueueName();
/* 2316 */       message.setStringProperty("JMS_IBM_MQMD_ReplyToQ", replyToQ);
/*      */ 
/*      */       
/* 2319 */       String replyToQMgr = getReplyToQueueManagerName();
/* 2320 */       message.setStringProperty("JMS_IBM_MQMD_ReplyToQMgr", replyToQMgr);
/*      */ 
/*      */       
/* 2323 */       String userIdentifier = getUserId();
/* 2324 */       message.setStringProperty("JMS_IBM_MQMD_UserIdentifier", userIdentifier);
/*      */ 
/*      */       
/* 2327 */       byte[] accountingToken = getAccountingToken();
/* 2328 */       message.setObjectProperty("JMS_IBM_MQMD_AccountingToken", accountingToken);
/*      */ 
/*      */       
/* 2331 */       String applIdentityData = getApplicationIdData();
/* 2332 */       message.setStringProperty("JMS_IBM_MQMD_ApplIdentityData", applIdentityData);
/*      */ 
/*      */       
/* 2335 */       int putApplType = getPutApplicationType();
/* 2336 */       message.setIntProperty("JMS_IBM_MQMD_PutApplType", putApplType);
/*      */ 
/*      */       
/* 2339 */       String putApplName = getPutApplicationName();
/* 2340 */       message.setStringProperty("JMS_IBM_MQMD_PutApplName", putApplName);
/*      */ 
/*      */       
/* 2343 */       String putDate = getPutDate();
/* 2344 */       message.setStringProperty("JMS_IBM_MQMD_PutDate", putDate);
/*      */ 
/*      */       
/* 2347 */       String putTime = getPutTime();
/* 2348 */       message.setStringProperty("JMS_IBM_MQMD_PutTime", putTime);
/*      */ 
/*      */       
/* 2351 */       String applOriginData = getApplicationOriginData();
/* 2352 */       message.setStringProperty("JMS_IBM_MQMD_ApplOriginData", applOriginData);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2357 */       byte[] groupId = getGroupId();
/* 2358 */       message.setObjectProperty("JMS_IBM_MQMD_GroupId", groupId);
/*      */ 
/*      */       
/* 2361 */       int msgSeqNumber = getMessageSequenceNumber();
/* 2362 */       message.setIntProperty("JMS_IBM_MQMD_MsgSeqNumber", msgSeqNumber);
/*      */ 
/*      */       
/* 2365 */       int offset = getOffset();
/* 2366 */       message.setIntProperty("JMS_IBM_MQMD_Offset", offset);
/*      */ 
/*      */       
/* 2369 */       int msgFlags = getMessageFlags();
/* 2370 */       message.setIntProperty("JMS_IBM_MQMD_MsgFlags", msgFlags);
/*      */ 
/*      */       
/* 2373 */       int originalLength = getOriginalLength();
/* 2374 */       message.setIntProperty("JMS_IBM_MQMD_OriginalLength", originalLength);
/*      */     } 
/*      */     
/* 2377 */     if (Trace.isOn) {
/* 2378 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "setMQMDPropsFromHeader(ProviderDestination,JMSMessage,int,int)");
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
/*      */   private void overrideHeaderFromMQMDProps(ProviderDestination destination, ProviderMessage message) throws JMSException, MQException {
/* 2405 */     if (Trace.isOn) {
/* 2406 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "overrideHeaderFromMQMDProps(ProviderDestination,ProviderMessage)", new Object[] { destination, message });
/*      */     }
/*      */ 
/*      */     
/* 2410 */     if (destination != null && destination.getBooleanProperty("mdWriteEnabled") == true) {
/*      */       
/* 2412 */       boolean mqmdV2required = false;
/*      */       
/* 2414 */       if (message.propertyExists("JMS_IBM_MQMD_Report")) {
/* 2415 */         int value = message.getIntProperty("JMS_IBM_MQMD_Report");
/* 2416 */         setReport(value);
/*      */       } 
/*      */       
/* 2419 */       if (message.propertyExists("JMS_IBM_MQMD_MsgType")) {
/* 2420 */         int value = message.getIntProperty("JMS_IBM_MQMD_MsgType");
/* 2421 */         setMessageType(value);
/*      */       } 
/*      */       
/* 2424 */       if (message.propertyExists("JMS_IBM_MQMD_Expiry")) {
/* 2425 */         int value = message.getIntProperty("JMS_IBM_MQMD_Expiry");
/* 2426 */         setExpiry(value);
/*      */       } 
/*      */       
/* 2429 */       if (message.propertyExists("JMS_IBM_MQMD_Feedback")) {
/* 2430 */         int value = message.getIntProperty("JMS_IBM_MQMD_Feedback");
/* 2431 */         setFeedback(value);
/*      */       } 
/*      */       
/* 2434 */       if (message.propertyExists("JMS_IBM_MQMD_Encoding")) {
/* 2435 */         int value = message.getIntProperty("JMS_IBM_MQMD_Encoding");
/* 2436 */         setEncoding(value);
/*      */       } 
/*      */       
/* 2439 */       if (message.propertyExists("JMS_IBM_MQMD_CodedCharSetId")) {
/* 2440 */         int value = message.getIntProperty("JMS_IBM_MQMD_CodedCharSetId");
/* 2441 */         setCharacterSet(value);
/*      */       } 
/*      */       
/* 2444 */       if (message.propertyExists("JMS_IBM_MQMD_Format")) {
/* 2445 */         String value = message.getStringProperty("JMS_IBM_MQMD_Format");
/* 2446 */         setFormat(value);
/*      */       } 
/*      */       
/* 2449 */       if (message.propertyExists("JMS_IBM_MQMD_Priority")) {
/* 2450 */         int value = message.getIntProperty("JMS_IBM_MQMD_Priority");
/* 2451 */         setPriority(value);
/*      */       } 
/*      */       
/* 2454 */       if (message.propertyExists("JMS_IBM_MQMD_Persistence")) {
/* 2455 */         int value = message.getIntProperty("JMS_IBM_MQMD_Persistence");
/* 2456 */         setPersistence(value);
/*      */       } 
/*      */       
/* 2459 */       if (message.propertyExists("JMS_IBM_MQMD_MsgId")) {
/* 2460 */         Object value = message.getObjectProperty("JMS_IBM_MQMD_MsgId");
/* 2461 */         byte[] safeValue = objectAsByteArray("JMS_IBM_MQMD_MsgId", value);
/* 2462 */         setMessageId(safeValue);
/*      */       } 
/*      */       
/* 2465 */       if (message.propertyExists("JMS_IBM_MQMD_CorrelId")) {
/* 2466 */         Object value = message.getObjectProperty("JMS_IBM_MQMD_CorrelId");
/* 2467 */         byte[] safeValue = objectAsByteArray("JMS_IBM_MQMD_CorrelId", value);
/* 2468 */         setCorrelationId(safeValue);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2474 */       if (message.propertyExists("JMS_IBM_MQMD_ReplyToQ")) {
/* 2475 */         String value = message.getStringProperty("JMS_IBM_MQMD_ReplyToQ");
/* 2476 */         setReplyToQueueName(value);
/*      */       } 
/*      */       
/* 2479 */       if (message.propertyExists("JMS_IBM_MQMD_ReplyToQMgr")) {
/* 2480 */         String value = message.getStringProperty("JMS_IBM_MQMD_ReplyToQMgr");
/* 2481 */         setReplyToQueueManagerName(value);
/*      */       } 
/*      */       
/* 2484 */       if (message.propertyExists("JMS_IBM_MQMD_UserIdentifier")) {
/* 2485 */         String value = message.getStringProperty("JMS_IBM_MQMD_UserIdentifier");
/* 2486 */         setUserId(value);
/*      */       } 
/*      */       
/* 2489 */       if (message.propertyExists("JMS_IBM_MQMD_AccountingToken")) {
/* 2490 */         Object value = message.getObjectProperty("JMS_IBM_MQMD_AccountingToken");
/* 2491 */         byte[] safeValue = objectAsByteArray("JMS_IBM_MQMD_AccountingToken", value);
/* 2492 */         setAccountingToken(safeValue);
/*      */       } 
/*      */       
/* 2495 */       if (message.propertyExists("JMS_IBM_MQMD_ApplIdentityData")) {
/* 2496 */         String value = message.getStringProperty("JMS_IBM_MQMD_ApplIdentityData");
/* 2497 */         setApplicationIdData(value);
/*      */       } 
/*      */       
/* 2500 */       if (message.propertyExists("JMS_IBM_MQMD_PutApplType")) {
/* 2501 */         int value = message.getIntProperty("JMS_IBM_MQMD_PutApplType");
/* 2502 */         setPutApplicationType(value);
/*      */       } 
/*      */       
/* 2505 */       if (message.propertyExists("JMS_IBM_MQMD_PutApplName")) {
/* 2506 */         String value = message.getStringProperty("JMS_IBM_MQMD_PutApplName");
/* 2507 */         setPutApplicationName(value);
/*      */       } 
/*      */       
/* 2510 */       if (message.propertyExists("JMS_IBM_MQMD_PutDate")) {
/* 2511 */         String value = message.getStringProperty("JMS_IBM_MQMD_PutDate");
/* 2512 */         setPutDate(value);
/*      */       } 
/*      */       
/* 2515 */       if (message.propertyExists("JMS_IBM_MQMD_PutTime")) {
/* 2516 */         String value = message.getStringProperty("JMS_IBM_MQMD_PutTime");
/* 2517 */         setPutTime(value);
/*      */       } 
/*      */       
/* 2520 */       if (message.propertyExists("JMS_IBM_MQMD_ApplOriginData")) {
/* 2521 */         String value = message.getStringProperty("JMS_IBM_MQMD_ApplOriginData");
/* 2522 */         setApplicationOriginData(value);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2527 */       if (message.propertyExists("JMS_IBM_MQMD_GroupId")) {
/* 2528 */         Object value = message.getObjectProperty("JMS_IBM_MQMD_GroupId");
/* 2529 */         byte[] safeValue = objectAsByteArray("JMS_IBM_MQMD_GroupId", value);
/* 2530 */         setGroupId(safeValue);
/* 2531 */         mqmdV2required = true;
/*      */       } 
/*      */       
/* 2534 */       if (message.propertyExists("JMS_IBM_MQMD_MsgSeqNumber")) {
/* 2535 */         int value = message.getIntProperty("JMS_IBM_MQMD_MsgSeqNumber");
/* 2536 */         setMessageSequenceNumber(value);
/* 2537 */         mqmdV2required = true;
/*      */       } 
/*      */       
/* 2540 */       if (message.propertyExists("JMS_IBM_MQMD_Offset")) {
/* 2541 */         int value = message.getIntProperty("JMS_IBM_MQMD_Offset");
/* 2542 */         setOffset(value);
/* 2543 */         mqmdV2required = true;
/*      */       } 
/*      */       
/* 2546 */       if (message.propertyExists("JMS_IBM_MQMD_MsgFlags")) {
/* 2547 */         int value = message.getIntProperty("JMS_IBM_MQMD_MsgFlags");
/* 2548 */         setMessageFlags(value);
/* 2549 */         mqmdV2required = true;
/*      */       } 
/*      */       
/* 2552 */       if (message.propertyExists("JMS_IBM_MQMD_OriginalLength")) {
/* 2553 */         int value = message.getIntProperty("JMS_IBM_MQMD_OriginalLength");
/* 2554 */         setOriginalLength(value);
/* 2555 */         mqmdV2required = true;
/*      */       } 
/*      */       
/* 2558 */       if (getVersion() == 1 && mqmdV2required) {
/* 2559 */         setVersion(2);
/*      */       }
/*      */     } 
/*      */     
/* 2563 */     if (Trace.isOn) {
/* 2564 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "overrideHeaderFromMQMDProps(ProviderDestination,ProviderMessage)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static byte[] objectAsByteArray(String propName, Object object) throws JMSException {
/* 2574 */     if (Trace.isOn) {
/* 2575 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "objectAsByteArray(String,Object)", new Object[] { propName, object });
/*      */     }
/*      */     
/* 2578 */     byte[] result = null;
/* 2579 */     if (object instanceof byte[]) {
/* 2580 */       result = (byte[])object;
/*      */     }
/*      */     else {
/*      */       
/* 2584 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2585 */       inserts.put("XMSC_INSERT_PROPERTY", propName);
/* 2586 */       inserts.put("XMSC_INSERT_VALUE", object);
/* 2587 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ1006", inserts);
/* 2588 */       if (Trace.isOn) {
/* 2589 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "objectAsByteArray(String,Object)", (Throwable)je);
/*      */       }
/*      */       
/* 2592 */       throw je;
/*      */     } 
/* 2594 */     if (Trace.isOn) {
/* 2595 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQJMSMessage", "objectAsByteArray(String,Object)", result);
/*      */     }
/*      */     
/* 2598 */     return result;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQJMSMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */