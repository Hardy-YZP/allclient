/*     */ package com.ibm.msg.client.wmq.common.internal.messages;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.MQMD;
/*     */ import com.ibm.mq.jmqi.handles.Hconn;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderDestination;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQCommonConnection;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQCommonSession;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQConsumerOwner;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.charset.CodingErrorAction;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ abstract class WMQMarshalUtils
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQMarshalUtils.java";
/*     */   static final int CCSID_UTF8 = 1208;
/*     */   static final String CHAR_SET_UTF8 = "UTF8";
/*     */   static final long MQHEADER_ASCII = 5571313378871214080L;
/*     */   static final long MQHEADER_EBCDIC = -3109515640373772288L;
/*     */   static final long MQHEADER_MASK = -1099511627776L;
/*     */   static final long MQRFH2_ASCII = 5571313732236222496L;
/*     */   static final long MQRFH2_EBCDIC = -3109514705028104128L;
/*     */   static final long MQSTR_ASCII = 5571325835654209568L;
/*     */   static final long MQSTR_EBCDIC = -3109486074469007296L;
/*     */   private static final String useInternalFormatPropertyName = "zrfp";
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshalUtils", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQMarshalUtils.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   private static Boolean useInternalFormatPropertyValue = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshalUtils", "static()");
/*     */     }
/*     */     
/* 116 */     PropertyStore.register("zrfp", false);
/*     */ 
/*     */     
/* 119 */     useInternalFormatPropertyValue = PropertyStore.getBooleanPropertyObject("zrfp");
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshalUtils", "static()");
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
/*     */   static boolean useInternalFormat(WMQConsumerOwner owner) {
/*     */     int connectionMode;
/* 136 */     if (!useInternalFormatPropertyValue.booleanValue()) {
/* 137 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 143 */       connectionMode = owner.getIntProperty("XMSC_WMQ_CONNECTION_MODE");
/*     */     }
/* 145 */     catch (JMSException e) {
/*     */       
/* 147 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 151 */     switch (connectionMode)
/*     */     { case 1:
/* 153 */         result = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 159 */         return result; }  boolean result = false; return result;
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
/*     */   static void overrideHeaderFromMQMDProps(ProviderDestination destination, ProviderMessage message, MQMD mqmd) throws JMSException {
/* 183 */     if (Trace.isOn) {
/* 184 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshalUtils", "overrideHeaderFromMQMDProps(ProviderDestination,ProviderMessage,MQMD)", new Object[] { destination, message, mqmd });
/*     */     }
/*     */ 
/*     */     
/* 188 */     if (destination.getBooleanProperty("mdWriteEnabled") == true) {
/*     */       
/* 190 */       boolean mqmdV2required = false;
/*     */       
/* 192 */       if (message.propertyExists("JMS_IBM_MQMD_Report")) {
/* 193 */         int value = message.getIntProperty("JMS_IBM_MQMD_Report");
/* 194 */         mqmd.setReport(value);
/*     */       } 
/*     */       
/* 197 */       if (message.propertyExists("JMS_IBM_MQMD_MsgType")) {
/* 198 */         int value = message.getIntProperty("JMS_IBM_MQMD_MsgType");
/* 199 */         mqmd.setMsgType(value);
/*     */       } 
/*     */       
/* 202 */       if (message.propertyExists("JMS_IBM_MQMD_Expiry")) {
/* 203 */         int value = message.getIntProperty("JMS_IBM_MQMD_Expiry");
/* 204 */         mqmd.setExpiry(value);
/*     */       } 
/*     */       
/* 207 */       if (message.propertyExists("JMS_IBM_MQMD_Feedback")) {
/* 208 */         int value = message.getIntProperty("JMS_IBM_MQMD_Feedback");
/* 209 */         mqmd.setFeedback(value);
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
/* 224 */       if (message.propertyExists("JMS_IBM_MQMD_CodedCharSetId")) {
/* 225 */         int value = message.getIntProperty("JMS_IBM_MQMD_CodedCharSetId");
/* 226 */         mqmd.setCodedCharSetId(value);
/*     */       } 
/*     */       
/* 229 */       if (message.propertyExists("JMS_IBM_MQMD_Format")) {
/* 230 */         String value = message.getStringProperty("JMS_IBM_MQMD_Format");
/* 231 */         mqmd.setFormat(value);
/*     */       } 
/*     */       
/* 234 */       if (message.propertyExists("JMS_IBM_MQMD_Priority")) {
/* 235 */         int value = message.getIntProperty("JMS_IBM_MQMD_Priority");
/* 236 */         mqmd.setPriority(value);
/*     */       } 
/*     */       
/* 239 */       if (message.propertyExists("JMS_IBM_MQMD_Persistence")) {
/* 240 */         int value = message.getIntProperty("JMS_IBM_MQMD_Persistence");
/* 241 */         mqmd.setPersistence(value);
/*     */       } 
/*     */       
/* 244 */       if (message.propertyExists("JMS_IBM_MQMD_MsgId")) {
/* 245 */         Object value = message.getObjectProperty("JMS_IBM_MQMD_MsgId");
/* 246 */         byte[] safeValue = objectAsByteArray("JMS_IBM_MQMD_MsgId", value);
/* 247 */         mqmd.setMsgId(safeValue);
/*     */       } 
/*     */       
/* 250 */       if (message.propertyExists("JMS_IBM_MQMD_CorrelId")) {
/* 251 */         Object value = message.getObjectProperty("JMS_IBM_MQMD_CorrelId");
/* 252 */         byte[] safeValue = objectAsByteArray("JMS_IBM_MQMD_CorrelId", value);
/* 253 */         mqmd.setCorrelId(safeValue);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 259 */       if (message.propertyExists("JMS_IBM_MQMD_ReplyToQ")) {
/* 260 */         String value = message.getStringProperty("JMS_IBM_MQMD_ReplyToQ");
/* 261 */         mqmd.setReplyToQ(value);
/*     */       } 
/*     */       
/* 264 */       if (message.propertyExists("JMS_IBM_MQMD_ReplyToQMgr")) {
/* 265 */         String value = message.getStringProperty("JMS_IBM_MQMD_ReplyToQMgr");
/* 266 */         mqmd.setReplyToQMgr(value);
/*     */       } 
/*     */       
/* 269 */       if (message.propertyExists("JMS_IBM_MQMD_UserIdentifier")) {
/* 270 */         String value = message.getStringProperty("JMS_IBM_MQMD_UserIdentifier");
/* 271 */         mqmd.setUserIdentifier(value);
/*     */       } 
/*     */       
/* 274 */       if (message.propertyExists("JMS_IBM_MQMD_AccountingToken")) {
/* 275 */         Object value = message.getObjectProperty("JMS_IBM_MQMD_AccountingToken");
/* 276 */         byte[] safeValue = objectAsByteArray("JMS_IBM_MQMD_AccountingToken", value);
/* 277 */         mqmd.setAccountingToken(safeValue);
/*     */       } 
/*     */       
/* 280 */       if (message.propertyExists("JMS_IBM_MQMD_ApplIdentityData")) {
/* 281 */         String value = message.getStringProperty("JMS_IBM_MQMD_ApplIdentityData");
/* 282 */         mqmd.setApplIdentityData(value);
/*     */       } 
/*     */       
/* 285 */       if (message.propertyExists("JMS_IBM_MQMD_PutApplType")) {
/* 286 */         int value = message.getIntProperty("JMS_IBM_MQMD_PutApplType");
/* 287 */         mqmd.setPutApplType(value);
/*     */       } 
/*     */       
/* 290 */       if (message.propertyExists("JMS_IBM_MQMD_PutApplName")) {
/* 291 */         String value = message.getStringProperty("JMS_IBM_MQMD_PutApplName");
/* 292 */         mqmd.setPutApplName(value);
/*     */       } 
/*     */       
/* 295 */       if (message.propertyExists("JMS_IBM_MQMD_PutDate")) {
/* 296 */         String value = message.getStringProperty("JMS_IBM_MQMD_PutDate");
/* 297 */         mqmd.setPutDate(value);
/*     */       } 
/*     */       
/* 300 */       if (message.propertyExists("JMS_IBM_MQMD_PutTime")) {
/* 301 */         String value = message.getStringProperty("JMS_IBM_MQMD_PutTime");
/* 302 */         mqmd.setPutTime(value);
/*     */       } 
/*     */       
/* 305 */       if (message.propertyExists("JMS_IBM_MQMD_ApplOriginData")) {
/* 306 */         String value = message.getStringProperty("JMS_IBM_MQMD_ApplOriginData");
/* 307 */         mqmd.setApplOriginData(value);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 312 */       if (message.propertyExists("JMS_IBM_MQMD_GroupId")) {
/* 313 */         Object value = message.getObjectProperty("JMS_IBM_MQMD_GroupId");
/* 314 */         byte[] safeValue = objectAsByteArray("JMS_IBM_MQMD_GroupId", value);
/* 315 */         mqmd.setGroupId(safeValue);
/* 316 */         mqmdV2required = true;
/*     */       } 
/*     */       
/* 319 */       if (message.propertyExists("JMS_IBM_MQMD_MsgSeqNumber")) {
/* 320 */         int value = message.getIntProperty("JMS_IBM_MQMD_MsgSeqNumber");
/* 321 */         mqmd.setMsgSeqNumber(value);
/* 322 */         mqmdV2required = true;
/*     */       } 
/*     */       
/* 325 */       if (message.propertyExists("JMS_IBM_MQMD_Offset")) {
/* 326 */         int value = message.getIntProperty("JMS_IBM_MQMD_Offset");
/* 327 */         mqmd.setOffset(value);
/* 328 */         mqmdV2required = true;
/*     */       } 
/*     */       
/* 331 */       if (message.propertyExists("JMS_IBM_MQMD_MsgFlags")) {
/* 332 */         int value = message.getIntProperty("JMS_IBM_MQMD_MsgFlags");
/* 333 */         mqmd.setMsgFlags(value);
/* 334 */         mqmdV2required = true;
/*     */       } 
/*     */       
/* 337 */       if (message.propertyExists("JMS_IBM_MQMD_OriginalLength")) {
/* 338 */         int value = message.getIntProperty("JMS_IBM_MQMD_OriginalLength");
/* 339 */         mqmd.setOriginalLength(value);
/* 340 */         mqmdV2required = true;
/*     */       } 
/*     */       
/* 343 */       if (mqmd.getVersion() == 1 && mqmdV2required) {
/* 344 */         mqmd.setVersion(2);
/*     */       }
/*     */     } 
/* 347 */     if (Trace.isOn) {
/* 348 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshalUtils", "overrideHeaderFromMQMDProps(ProviderDestination,ProviderMessage,MQMD)");
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
/*     */   static void setMQMDPropsFromHeader(ProviderDestination destination, ProviderMessage message, MQMD mqmd) throws JMSException {
/* 373 */     if (Trace.isOn) {
/* 374 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshalUtils", "setMQMDPropsFromHeader(ProviderDestination,ProviderMessage,MQMD)", new Object[] { destination, message, mqmd });
/*     */     }
/*     */ 
/*     */     
/* 378 */     if (destination.getBooleanProperty("mdReadEnabled") == true) {
/*     */       
/* 380 */       int report = mqmd.getReport();
/* 381 */       message.setIntProperty("JMS_IBM_MQMD_Report", report);
/*     */ 
/*     */       
/* 384 */       int msgType = mqmd.getMsgType();
/* 385 */       message.setIntProperty("JMS_IBM_MQMD_MsgType", msgType);
/*     */ 
/*     */       
/* 388 */       int expiry = mqmd.getExpiry();
/* 389 */       message.setIntProperty("JMS_IBM_MQMD_Expiry", expiry);
/*     */ 
/*     */       
/* 392 */       int feedback = mqmd.getFeedback();
/* 393 */       message.setIntProperty("JMS_IBM_MQMD_Feedback", feedback);
/*     */ 
/*     */       
/* 396 */       int encoding = mqmd.getEncoding();
/* 397 */       message.setIntProperty("JMS_IBM_MQMD_Encoding", encoding);
/*     */ 
/*     */       
/* 400 */       int codedCharSetId = mqmd.getCodedCharSetId();
/* 401 */       message.setIntProperty("JMS_IBM_MQMD_CodedCharSetId", codedCharSetId);
/*     */ 
/*     */       
/* 404 */       String format = mqmd.getFormat();
/* 405 */       message.setStringProperty("JMS_IBM_MQMD_Format", format);
/*     */ 
/*     */       
/* 408 */       int priority = mqmd.getPriority();
/* 409 */       message.setIntProperty("JMS_IBM_MQMD_Priority", priority);
/*     */ 
/*     */       
/* 412 */       int persistence = mqmd.getPersistence();
/* 413 */       message.setIntProperty("JMS_IBM_MQMD_Persistence", persistence);
/*     */ 
/*     */       
/* 416 */       byte[] msgId = mqmd.getMsgId();
/* 417 */       message.setObjectProperty("JMS_IBM_MQMD_MsgId", msgId);
/*     */ 
/*     */       
/* 420 */       byte[] correlId = mqmd.getCorrelId();
/* 421 */       message.setObjectProperty("JMS_IBM_MQMD_CorrelId", correlId);
/*     */ 
/*     */       
/* 424 */       int backoutCount = mqmd.getBackoutCount();
/* 425 */       message.setIntProperty("JMS_IBM_MQMD_BackoutCount", backoutCount);
/*     */ 
/*     */       
/* 428 */       String replyToQ = mqmd.getReplyToQ();
/* 429 */       message.setStringProperty("JMS_IBM_MQMD_ReplyToQ", replyToQ);
/*     */ 
/*     */       
/* 432 */       String replyToQMgr = mqmd.getReplyToQMgr();
/* 433 */       message.setStringProperty("JMS_IBM_MQMD_ReplyToQMgr", replyToQMgr);
/*     */ 
/*     */       
/* 436 */       String userIdentifier = mqmd.getUserIdentifier();
/* 437 */       message.setStringProperty("JMS_IBM_MQMD_UserIdentifier", userIdentifier);
/*     */ 
/*     */       
/* 440 */       byte[] accountingToken = mqmd.getAccountingToken();
/* 441 */       message.setObjectProperty("JMS_IBM_MQMD_AccountingToken", accountingToken);
/*     */ 
/*     */       
/* 444 */       String applIdentityData = mqmd.getApplIdentityData();
/* 445 */       message.setStringProperty("JMS_IBM_MQMD_ApplIdentityData", applIdentityData);
/*     */ 
/*     */       
/* 448 */       int putApplType = mqmd.getPutApplType();
/* 449 */       message.setIntProperty("JMS_IBM_MQMD_PutApplType", putApplType);
/*     */ 
/*     */       
/* 452 */       String putApplName = mqmd.getPutApplName();
/* 453 */       message.setStringProperty("JMS_IBM_MQMD_PutApplName", putApplName);
/*     */ 
/*     */       
/* 456 */       String putDate = mqmd.getPutDate();
/* 457 */       message.setStringProperty("JMS_IBM_MQMD_PutDate", putDate);
/*     */ 
/*     */       
/* 460 */       String putTime = mqmd.getPutTime();
/* 461 */       message.setStringProperty("JMS_IBM_MQMD_PutTime", putTime);
/*     */ 
/*     */       
/* 464 */       String applOriginData = mqmd.getApplOriginData();
/* 465 */       message.setStringProperty("JMS_IBM_MQMD_ApplOriginData", applOriginData);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 470 */       byte[] groupId = mqmd.getGroupId();
/* 471 */       message.setObjectProperty("JMS_IBM_MQMD_GroupId", groupId);
/*     */ 
/*     */       
/* 474 */       int msgSeqNumber = mqmd.getMsgSeqNumber();
/* 475 */       message.setIntProperty("JMS_IBM_MQMD_MsgSeqNumber", msgSeqNumber);
/*     */ 
/*     */       
/* 478 */       int offset = mqmd.getOffset();
/* 479 */       message.setIntProperty("JMS_IBM_MQMD_Offset", offset);
/*     */ 
/*     */       
/* 482 */       int msgFlags = mqmd.getMsgFlags();
/* 483 */       message.setIntProperty("JMS_IBM_MQMD_MsgFlags", msgFlags);
/*     */ 
/*     */       
/* 486 */       int originalLength = mqmd.getOriginalLength();
/* 487 */       message.setIntProperty("JMS_IBM_MQMD_OriginalLength", originalLength);
/*     */     } 
/*     */     
/* 490 */     if (Trace.isOn) {
/* 491 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshalUtils", "setMQMDPropsFromHeader(ProviderDestination,ProviderMessage,MQMD)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] objectAsByteArray(String propName, Object object) throws JMSException {
/* 502 */     byte[] result = null;
/* 503 */     if (object instanceof byte[]) {
/* 504 */       result = (byte[])object;
/*     */     }
/*     */     else {
/*     */       
/* 508 */       HashMap<String, Object> inserts = new HashMap<>();
/* 509 */       inserts.put("XMSC_INSERT_PROPERTY", propName);
/* 510 */       inserts.put("XMSC_INSERT_VALUE", object);
/* 511 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ1006", inserts);
/* 512 */       throw je;
/*     */     } 
/* 514 */     return result;
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
/*     */   static void addInfo(JmqiException e, Map<String, Object> info) {
/* 527 */     info.put("COMPCODE", Integer.valueOf(e.getCompCode()));
/* 528 */     info.put("REASON", Integer.valueOf(e.getReason()));
/*     */     
/* 530 */     String value = e.getWmqMsgExplanation();
/* 531 */     if (value != null) {
/* 532 */       info.put("MSGEXPLANATION", value);
/*     */     }
/* 534 */     value = e.getWmqMsgSummary();
/* 535 */     if (value != null) {
/* 536 */       info.put("MSGSUMMARY", value);
/*     */     }
/* 538 */     value = e.getWmqMsgUserResponse();
/* 539 */     if (value != null) {
/* 540 */       info.put("MSGUSERRESPONSE", value);
/*     */     }
/* 542 */     info.put("MSGSEVERITY", Integer.valueOf(e.getWmqMsgSeverity()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int getQueueManagerCcsid(WMQCommonSession session) {
/* 552 */     if (Trace.isOn) {
/* 553 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshalUtils", "getQueueManagerCcsid(WMQCommonSession)", new Object[] { session });
/*     */     }
/*     */     
/* 556 */     int ccsid = 0;
/* 557 */     Hconn hconn = null;
/*     */     try {
/* 559 */       hconn = session.getHconn();
/* 560 */       ccsid = hconn.getCcsid();
/*     */     }
/* 562 */     catch (JmqiException e) {
/* 563 */       if (Trace.isOn) {
/* 564 */         Trace.catchBlock("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshalUtils", "getQueueManagerCcsid(WMQCommonSession)", (Throwable)e);
/*     */       }
/*     */       
/* 567 */       HashMap<String, Object> info = new HashMap<>();
/* 568 */       info.put("HConn", hconn);
/* 569 */       addInfo(e, info);
/* 570 */       Trace.ffst("WMQMarshalUtils", JmqiTools.getExSumm((Throwable)e), "XM008001", info, null);
/*     */     } 
/* 572 */     if (Trace.isOn) {
/* 573 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshalUtils", "getQueueManagerCcsid(WMQCommonSession)", 
/* 574 */           Integer.valueOf(ccsid));
/*     */     }
/* 576 */     return ccsid;
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
/*     */   static void throwBadCcsidException(int ccsid) throws JMSException {
/* 588 */     HashMap<String, Object> inserts = new HashMap<>();
/* 589 */     inserts.put("CCSID", "ccsid:" + Integer.toString(ccsid));
/* 590 */     JMSException je = (JMSException)NLSServices.createException("JMSCMQ1046", inserts);
/* 591 */     throw je;
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
/*     */   static boolean getPersistenceFromMD(WMQConsumerOwner owner) {
/* 607 */     if (owner == null) {
/* 608 */       HashMap<String, Object> info = new HashMap<>();
/* 609 */       info.put("session", "<null>");
/* 610 */       Trace.ffst("WMQMarshalUtils", "getPersistenceFromMD()", "XN00E001", info, null);
/* 611 */       return true;
/*     */     } 
/*     */     
/* 614 */     WMQCommonConnection connection = owner.getConnection();
/*     */ 
/*     */     
/* 617 */     if (connection == null) {
/* 618 */       HashMap<String, Object> info = new HashMap<>();
/* 619 */       info.put("connection", "<null>");
/* 620 */       Trace.ffst("WMQMarshalUtils", "getPersistenceFromMD()", "XN00E002", info, null);
/* 621 */       return true;
/*     */     } 
/*     */     
/* 624 */     boolean result = connection.getPersistenceFromMD();
/* 625 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int getDefaultReplyToStyle() {
/* 634 */     int result = 1;
/*     */     
/* 636 */     String overrideReplyToStyle = PropertyStore.getStringProperty("com.ibm.mq.jms.replyToStyle");
/* 637 */     if (overrideReplyToStyle != null) {
/* 638 */       if (overrideReplyToStyle.equalsIgnoreCase("MQMD")) {
/* 639 */         result = 1;
/*     */       }
/* 641 */       else if (overrideReplyToStyle.equalsIgnoreCase("RFH2")) {
/* 642 */         result = 2;
/*     */       } else {
/*     */         
/* 645 */         result = 1;
/*     */       } 
/*     */     }
/*     */     
/* 649 */     if (Trace.isOn) {
/* 650 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshalUtils", "getDefaultReplyToStyle()", "getter", 
/* 651 */           Integer.valueOf(result));
/*     */     }
/* 653 */     return result;
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
/*     */   static JmqiCodepage getMessageCodepage(JmqiEnvironment env, WMQDestination destination, WMQMessage providerMessage, int encoding) throws JMSException {
/* 667 */     if (Trace.isOn) {
/* 668 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshalUtils", "getMessageCodepage(JmqiEnvironment,WMQDestination,WMQMessage,int)", new Object[] { env, destination, providerMessage, 
/*     */             
/* 670 */             Integer.valueOf(encoding) });
/*     */     }
/* 672 */     int destinationCcsid = destination.getIntProperty("CCSID");
/*     */     
/* 674 */     String msgCharSet = providerMessage.getStringProperty("JMS_IBM_Character_Set");
/*     */ 
/*     */ 
/*     */     
/* 678 */     Integer msgCcsid = null;
/* 679 */     if (isPositiveInteger(msgCharSet)) {
/*     */       try {
/* 681 */         msgCcsid = Integer.valueOf(msgCharSet);
/*     */       }
/* 683 */       catch (NumberFormatException numberFormatException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 688 */     String unmappableActionName = providerMessage.getStringProperty("JMS_IBM_Unmappable_Action");
/* 689 */     if (unmappableActionName == null) {
/* 690 */       unmappableActionName = destination.getStringProperty("JMS_IBM_Unmappable_Action");
/*     */     }
/* 692 */     CodingErrorAction unmappableAction = null;
/* 693 */     for (CodingErrorAction action : new CodingErrorAction[] { CodingErrorAction.REPLACE, CodingErrorAction.IGNORE, CodingErrorAction.REPORT }) {
/* 694 */       if (action.toString().equals(unmappableActionName)) {
/* 695 */         unmappableAction = action;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 700 */     Byte umr = (Byte)providerMessage.getObjectProperty("JMS_IBM_Unmappable_Replacement");
/* 701 */     if (umr == null) {
/* 702 */       umr = (Byte)destination.getObjectProperty("JMS_IBM_Unmappable_Replacement");
/*     */     }
/* 704 */     (new byte[1])[0] = umr.byteValue(); byte[] unMappableReplacement = (umr != null) ? new byte[1] : null;
/*     */     
/* 706 */     JmqiCodepage msgCp = null;
/*     */ 
/*     */     
/*     */     try {
/* 710 */       if (msgCcsid != null) {
/* 711 */         msgCp = JmqiCodepage.getJmqiCodepage(env, msgCcsid.intValue(), unmappableAction, unMappableReplacement, encoding);
/*     */       }
/* 713 */       else if (msgCharSet != null) {
/* 714 */         msgCp = JmqiCodepage.getJmqiCodepage(env, msgCharSet, unmappableAction, unMappableReplacement);
/*     */       }
/*     */       else {
/*     */         
/* 718 */         msgCp = JmqiCodepage.getJmqiCodepage(env, destinationCcsid, unmappableAction, unMappableReplacement, encoding);
/*     */       }
/*     */     
/*     */     }
/* 722 */     catch (UnsupportedEncodingException e) {
/* 723 */       if (Trace.isOn) {
/* 724 */         Trace.catchBlock("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshalUtils", "getMessageCodepage(JmqiEnvironment,WMQDestination,WMQMessage,int)", e);
/*     */       }
/*     */       
/* 727 */       HashMap<String, Object> inserts = new HashMap<>();
/* 728 */       inserts.put("XMSC_INSERT_PROPERTY", "JMS_IBM_Character_Set");
/* 729 */       inserts.put("XMSC_INSERT_VALUE", "Message Character Set: " + msgCharSet + ", Message CCSID: " + msgCcsid + ", destination CCSID: " + destinationCcsid);
/*     */       
/* 731 */       JMSException je = (JMSException)NLSServices.createException("JMSCMQ1006", inserts);
/* 732 */       je.setLinkedException(e);
/* 733 */       if (Trace.isOn) {
/* 734 */         Trace.throwing("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshalUtils", "getMessageCodepage(JmqiEnvironment,WMQDestination,WMQMessage,int)", (Throwable)je);
/*     */       }
/*     */       
/* 737 */       throw je;
/*     */     } 
/*     */     
/* 740 */     if (Trace.isOn) {
/* 741 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshalUtils", "getMessageCodepage(JmqiEnvironment,WMQDestination,WMQMessage,int)", msgCp);
/*     */     }
/*     */     
/* 744 */     return msgCp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isPositiveInteger(String s) {
/* 754 */     if (s == null || s.length() == 0) {
/* 755 */       return false;
/*     */     }
/* 757 */     for (int i = 0; i < s.length(); i++) {
/* 758 */       char c = s.charAt(i);
/* 759 */       if (c < '0' || c > '9') {
/* 760 */         return false;
/*     */       }
/*     */     } 
/* 763 */     return true;
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
/*     */   static int calculateMessageBodyEncoding(WMQDestination destination, WMQMessage providerMessage) throws JMSException {
/* 775 */     if (Trace.isOn) {
/* 776 */       Trace.entry("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshalUtils", "calculateMessageBodyEncoding(WMQDestination,WMQMessage)", new Object[] { destination, providerMessage });
/*     */     }
/*     */ 
/*     */     
/* 780 */     int encoding = 0;
/*     */     
/* 782 */     if (providerMessage.getObjectProperty("JMS_IBM_Encoding") != null) {
/* 783 */       encoding = providerMessage.getIntProperty("JMS_IBM_Encoding");
/*     */     }
/*     */     else {
/*     */       
/* 787 */       encoding = destination.getIntProperty("encoding");
/*     */       
/* 789 */       if (encoding == 0) {
/* 790 */         encoding = 273;
/*     */       }
/*     */     } 
/*     */     
/* 794 */     if (Trace.isOn) {
/* 795 */       Trace.exit("com.ibm.msg.client.wmq.common.internal.messages.WMQMarshalUtils", "calculateMessageBodyEncoding(WMQDestination,WMQMessage)", 
/* 796 */           Integer.valueOf(encoding));
/*     */     }
/* 798 */     return encoding;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQMarshalUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */