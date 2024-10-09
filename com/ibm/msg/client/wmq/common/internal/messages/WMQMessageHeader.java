/*     */ package com.ibm.msg.client.wmq.common.internal.messages;
/*     */ 
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQUtils;
/*     */ import java.util.Arrays;
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
/*     */ abstract class WMQMessageHeader
/*     */   extends WMQMessageProps
/*     */ {
/*     */   private static final long serialVersionUID = 2771883720834528112L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQMessageHeader.java";
/*     */   private static final int DEFAULT_DELIVERY_MODE = 2;
/*     */   private static final long DEFAULT_EXPIRATION = 0L;
/*     */   private static final long DEFAULT_DELIVERY_DELAY = 0L;
/*     */   private static final long DEFAULT_DELIVERY_TIME = 0L;
/*     */   private static final int DEFAULT_PRIORITY = 4;
/*     */   private static final boolean DEFAULT_REDELIVERED = false;
/*     */   private static final long DEFAULT_TIMESTAMP = -1L;
/*     */   
/*     */   static {
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.data("com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.common/src/com/ibm/msg/client/wmq/common/internal/messages/WMQMessageHeader.java");
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
/*  74 */   private static final byte[] DEFAULT_CORRELID_BYTES = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   private long jmsDeliveryDelay = 0L;
/*  81 */   private long jmsDeliveryTime = 0L;
/*  82 */   private int jmsDeliveryMode = 2;
/*  83 */   private int jmsPriority = 4;
/*  84 */   private long timeMillisFromWhichJMSExpirationWasBased = -1L;
/*  85 */   private long jmsExpiration = 0L;
/*  86 */   private long jmsTimestamp = -1L;
/*     */   private boolean jmsRedelivered = false;
/*  88 */   private String jmsMessageID = null;
/*  89 */   private String jmsDestinationAsString = null;
/*  90 */   private String jmsReplyToAsString = null;
/*  91 */   private String jmsType = null;
/*  92 */   private String jmsCorrelationID = null;
/*  93 */   private byte[] jmsCorrelationIDAsBytes = null;
/*     */ 
/*     */   
/*  96 */   private byte[] wmqMsgToken = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private int wmqMsgLength;
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWmqMsgLength() {
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getWmqMsgLength()", "getter", 
/* 107 */           Integer.valueOf(this.wmqMsgLength));
/*     */     }
/* 109 */     return this.wmqMsgLength;
/*     */   }
/*     */   
/*     */   public void setWmqMsgLength(int wmqMsgLength) {
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setWmqMsgLength(int)", "setter", 
/* 115 */           Integer.valueOf(wmqMsgLength));
/*     */     }
/* 117 */     this.wmqMsgLength = wmqMsgLength;
/*     */   }
/*     */   
/*     */   public byte[] getWmqMsgToken() {
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getWmqMsgToken()", "getter", this.wmqMsgToken);
/*     */     }
/*     */     
/* 125 */     return this.wmqMsgToken;
/*     */   }
/*     */   
/*     */   public void setWmqMsgToken(byte[] wmqMsgToken) {
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setWmqMsgToken(byte [ ])", "setter", wmqMsgToken);
/*     */     }
/*     */     
/* 133 */     this.wmqMsgToken = wmqMsgToken;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   protected String messageClass = "jms_none";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   protected String jmsType_Domain = null;
/* 146 */   protected String jmsType_Format = null;
/* 147 */   protected String jmsType_Set = null;
/* 148 */   protected String jmsType_Type = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean hideDeliveryMode = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getJMSCorrelationID() throws JMSException {
/* 163 */     if (this.jmsCorrelationID == null && this.jmsCorrelationIDAsBytes != null && 
/* 164 */       !Arrays.equals(this.jmsCorrelationIDAsBytes, DEFAULT_CORRELID_BYTES)) {
/* 165 */       this.jmsCorrelationID = WMQUtils.idToString(this.jmsCorrelationIDAsBytes);
/*     */     }
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getJMSCorrelationID()", "getter", this.jmsCorrelationID);
/*     */     }
/*     */     
/* 171 */     return this.jmsCorrelationID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
/* 181 */     if (this.jmsCorrelationIDAsBytes == null && this.jmsCorrelationID != null) {
/*     */       try {
/* 183 */         this.jmsCorrelationIDAsBytes = WMQUtils.computeBytesFromText(this.jmsCorrelationID, JmqiCodepage.getJmqiCodepage(null, 1208));
/*     */       }
/* 185 */       catch (NullPointerException e) {
/* 186 */         if (Trace.isOn) {
/* 187 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getJMSCorrelationIDAsBytes()", e);
/*     */         }
/*     */ 
/*     */         
/* 191 */         HashMap<String, Object> inserts = new HashMap<>();
/* 192 */         inserts.put("XMSC_INSERT_PROPERTY", "JMS_IBM_Character_Set");
/* 193 */         inserts.put("XMSC_INSERT_VALUE", Integer.valueOf(1208));
/* 194 */         JMSException je = (JMSException)NLSServices.createException("JMSCMQ1006", inserts);
/* 195 */         if (Trace.isOn) {
/* 196 */           Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getJMSCorrelationIDAsBytes()", (Throwable)je);
/*     */         }
/*     */         
/* 199 */         throw je;
/*     */       } 
/*     */     }
/*     */     
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getJMSCorrelationIDAsBytes()", "getter", this.jmsCorrelationIDAsBytes);
/*     */     }
/*     */     
/* 207 */     return this.jmsCorrelationIDAsBytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getJMSDeliveryDelay() throws JMSException {
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getJMSDeliveryDelay()", "getter", 
/* 219 */           Long.valueOf(this.jmsDeliveryDelay));
/*     */     }
/* 221 */     return this.jmsDeliveryDelay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getJMSDeliveryTime() throws JMSException {
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getJMSDeliveryTime()", "getter", 
/* 233 */           Long.valueOf(this.jmsDeliveryTime));
/*     */     }
/* 235 */     return this.jmsDeliveryTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSDeliveryDelay(long deliveryDelay) throws JMSException {
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setJMSDeliveryDelay(long)", "setter", 
/* 247 */           Long.valueOf(deliveryDelay));
/*     */     }
/* 249 */     this.jmsDeliveryDelay = deliveryDelay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSDeliveryTime(long deliveryTime) throws JMSException {
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setJMSDeliveryTime(long)", "setter", 
/* 261 */           Long.valueOf(deliveryTime));
/*     */     }
/* 263 */     this.jmsDeliveryTime = deliveryTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getJMSDeliveryMode() throws JMSException {
/* 272 */     Integer dm = Integer.valueOf(this.jmsDeliveryMode);
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getJMSDeliveryMode()", "getter", dm);
/*     */     }
/*     */     
/* 277 */     return dm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getJMSDestinationAsString() throws JMSException {
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getJMSDestinationAsString()", "getter", this.jmsDestinationAsString);
/*     */     }
/*     */     
/* 290 */     return this.jmsDestinationAsString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Long getJMSExpiration() throws JMSException {
/* 299 */     Long exp = Long.valueOf(this.jmsExpiration);
/* 300 */     if (Trace.isOn) {
/* 301 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getJMSExpiration()", "getter", exp);
/*     */     }
/*     */     
/* 304 */     return exp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getTimeMillisFromWhichJMSExpirationWasBased() {
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getTimeMillisFromWhichJMSExpirationWasBased()", "getter", 
/*     */           
/* 316 */           Long.valueOf(this.timeMillisFromWhichJMSExpirationWasBased));
/*     */     }
/* 318 */     return this.timeMillisFromWhichJMSExpirationWasBased;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getJMSMessageID() throws JMSException {
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getJMSMessageID()", "getter", this.jmsMessageID);
/*     */     }
/*     */     
/* 331 */     return this.jmsMessageID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getJMSPriority() throws JMSException {
/* 340 */     Integer pri = Integer.valueOf(this.jmsPriority);
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getJMSPriority()", "getter", pri);
/*     */     }
/*     */     
/* 345 */     return pri;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean getJMSRedelivered() throws JMSException {
/* 354 */     Boolean redelivered = Boolean.valueOf(this.jmsRedelivered);
/* 355 */     if (Trace.isOn) {
/* 356 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getJMSRedelivered()", "getter", redelivered);
/*     */     }
/*     */     
/* 359 */     return redelivered;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getJMSReplyToAsString() throws JMSException {
/* 368 */     if (Trace.isOn) {
/* 369 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getJMSReplyToAsString()", "getter", this.jmsReplyToAsString);
/*     */     }
/*     */     
/* 372 */     return this.jmsReplyToAsString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Long getJMSTimestamp() throws JMSException {
/* 381 */     Long ts = (this.jmsTimestamp == -1L) ? null : Long.valueOf(this.jmsTimestamp);
/* 382 */     if (Trace.isOn) {
/* 383 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getJMSTimestamp()", "getter", ts);
/*     */     }
/*     */     
/* 386 */     return ts;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getJMSType() throws JMSException {
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "getJMSType()", "getter", this.jmsType);
/*     */     }
/*     */     
/* 400 */     return this.jmsType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSCorrelationID(String correlID) throws JMSException {
/* 408 */     if (Trace.isOn) {
/* 409 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setJMSCorrelationID(String)", "setter", correlID);
/*     */     }
/*     */ 
/*     */     
/* 413 */     if (correlID == null) {
/* 414 */       this.jmsCorrelationID = null;
/* 415 */       this.jmsCorrelationIDAsBytes = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 421 */     else if (correlID.startsWith("ID:")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 429 */       this.jmsCorrelationIDAsBytes = WMQUtils.stringToId(correlID);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 434 */       this.jmsCorrelationIDAsBytes = null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 439 */     this.jmsCorrelationID = correlID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSCorrelationIDAsBytes(byte[] correlIDBytes) throws JMSException {
/* 447 */     if (Trace.isOn) {
/* 448 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setJMSCorrelationIDAsBytes(byte [ ])", "setter", correlIDBytes);
/*     */     }
/*     */ 
/*     */     
/* 452 */     if (correlIDBytes == null) {
/* 453 */       this.jmsCorrelationIDAsBytes = null;
/* 454 */       this.jmsCorrelationID = null;
/*     */     } else {
/*     */       
/* 457 */       this.jmsCorrelationIDAsBytes = new byte[correlIDBytes.length];
/* 458 */       System.arraycopy(correlIDBytes, 0, this.jmsCorrelationIDAsBytes, 0, correlIDBytes.length);
/*     */ 
/*     */       
/* 461 */       this.jmsCorrelationID = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSDeliveryMode(int deliveryMode) throws JMSException {
/* 470 */     if (Trace.isOn) {
/* 471 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setJMSDeliveryMode(int)", "setter", 
/* 472 */           Integer.valueOf(deliveryMode));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 477 */     if (deliveryMode == -2) {
/* 478 */       this.jmsDeliveryMode = 2;
/* 479 */       this.hideDeliveryMode = true;
/*     */     }
/* 481 */     else if (deliveryMode == -3) {
/* 482 */       this.jmsDeliveryMode = -2;
/* 483 */       this.hideDeliveryMode = true;
/*     */     } else {
/*     */       
/* 486 */       this.jmsDeliveryMode = deliveryMode;
/* 487 */       this.hideDeliveryMode = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSDestinationAsString(String newDestinationAsString) throws JMSException {
/* 496 */     if (Trace.isOn) {
/* 497 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setJMSDestinationAsString(String)", "setter", newDestinationAsString);
/*     */     }
/*     */ 
/*     */     
/* 501 */     this.jmsDestinationAsString = newDestinationAsString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSExpiration(long expiration) throws JMSException {
/* 509 */     if (Trace.isOn) {
/* 510 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setJMSExpiration(long)", "setter", 
/* 511 */           Long.valueOf(expiration));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 516 */     this.timeMillisFromWhichJMSExpirationWasBased = -1L;
/* 517 */     this.jmsExpiration = expiration;
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
/*     */   public void setJMSExpirationFromMQMDExpiry(int mqmdExpiry) {
/* 536 */     this.timeMillisFromWhichJMSExpirationWasBased = System.currentTimeMillis();
/* 537 */     this.jmsExpiration = this.timeMillisFromWhichJMSExpirationWasBased + mqmdExpiry * 100L;
/* 538 */     if (Trace.isOn) {
/*     */ 
/*     */ 
/*     */       
/* 542 */       Object[] objs = { Integer.valueOf(mqmdExpiry), Long.valueOf(this.timeMillisFromWhichJMSExpirationWasBased), Long.valueOf(this.jmsExpiration) };
/* 543 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setJMSExpirationFromMQMDExpiry(int)", "setter", objs);
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
/*     */   public void setTimeMillisFromWhichJMSExpirationWasBased(long timestamp) {
/* 561 */     if (Trace.isOn) {
/* 562 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setJMSExpirationFromMQMDExpiry(int)", "setter", 
/* 563 */           Long.valueOf(timestamp));
/*     */     }
/* 565 */     this.timeMillisFromWhichJMSExpirationWasBased = timestamp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSMessageID(String messageID) throws JMSException {
/* 573 */     if (Trace.isOn) {
/* 574 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setJMSMessageID(String)", "setter", messageID);
/*     */     }
/*     */ 
/*     */     
/* 578 */     this.jmsMessageID = messageID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSPriority(int priority) throws JMSException {
/* 586 */     if (Trace.isOn) {
/* 587 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setJMSPriority(int)", "setter", 
/* 588 */           Integer.valueOf(priority));
/*     */     }
/*     */     
/* 591 */     this.jmsPriority = priority;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSRedelivered(boolean redelivered) throws JMSException {
/* 599 */     if (Trace.isOn) {
/* 600 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setJMSRedelivered(boolean)", "setter", 
/* 601 */           Boolean.valueOf(redelivered));
/*     */     }
/*     */     
/* 604 */     this.jmsRedelivered = redelivered;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSReplyToAsString(String newReplyToAsString) throws JMSException {
/* 612 */     if (Trace.isOn) {
/* 613 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setJMSReplyToAsString(String)", "setter", newReplyToAsString);
/*     */     }
/*     */ 
/*     */     
/* 617 */     this.jmsReplyToAsString = newReplyToAsString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSTimestamp(long timestamp) throws JMSException {
/* 625 */     if (Trace.isOn) {
/* 626 */       Trace.data(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setJMSTimestamp(long)", "setter", 
/* 627 */           Long.valueOf(timestamp));
/*     */     }
/*     */     
/* 630 */     this.jmsTimestamp = timestamp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSType(String type) throws JMSException {
/*     */     String path;
/* 638 */     if (Trace.isOn) {
/* 639 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setJMSType(String)", new Object[] { type });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 645 */     this.jmsType = type;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 653 */     if (type == null || !type.startsWith("mcd://")) {
/* 654 */       this.jmsType_Domain = null;
/* 655 */       this.jmsType_Set = null;
/* 656 */       this.jmsType_Type = null;
/* 657 */       this.jmsType_Format = null;
/* 658 */       if (Trace.isOn) {
/* 659 */         Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setJMSType(String)", 1);
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
/*     */       return;
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
/* 685 */     if (!"jms_text".equals(this.messageClass) && !"jms_bytes".equals(this.messageClass)) {
/* 686 */       throwBadJMSTypeException(type);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 695 */     String msDomain = null;
/* 696 */     String msSet = null;
/* 697 */     String msType = null;
/* 698 */     String msFormat = null;
/*     */ 
/*     */     
/* 701 */     int index = 6;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 712 */     int query = type.indexOf('?', index);
/*     */     
/* 714 */     if (query == -1) {
/* 715 */       path = type;
/*     */     } else {
/*     */       
/* 718 */       path = type.substring(0, query);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 723 */     int separator = path.indexOf('/', index);
/*     */     
/* 725 */     if (separator == -1) {
/*     */ 
/*     */ 
/*     */       
/* 729 */       if (path.length() <= 6) {
/* 730 */         throwBadJMSTypeException(type);
/*     */       }
/* 732 */       msDomain = path.substring(index);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 738 */       if (separator == index) {
/* 739 */         throwBadJMSTypeException(type);
/*     */       }
/*     */       
/* 742 */       msDomain = path.substring(index, separator);
/* 743 */       index = separator + 1;
/*     */ 
/*     */       
/* 746 */       separator = path.indexOf('/', index);
/*     */       
/* 748 */       if (separator == -1) {
/* 749 */         msSet = path.substring(index);
/*     */       } else {
/*     */         
/* 752 */         msSet = path.substring(index, separator);
/* 753 */         index = separator + 1;
/*     */         
/* 755 */         msType = path.substring(index);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 760 */     if (query > 0)
/*     */     {
/*     */       
/* 763 */       if (type.regionMatches(true, query, "?format=", 0, 8)) {
/* 764 */         msFormat = type.substring(query + 8);
/*     */         
/* 766 */         if (msFormat.indexOf('&') != -1) {
/* 767 */           throwBadJMSTypeException(type);
/*     */         }
/*     */       } else {
/*     */         
/* 771 */         throwBadJMSTypeException(type);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 776 */     this.jmsType_Domain = msDomain;
/* 777 */     this.jmsType_Set = msSet;
/* 778 */     this.jmsType_Type = msType;
/* 779 */     this.jmsType_Format = msFormat;
/* 780 */     if (Trace.isOn) {
/* 781 */       Trace.exit(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "setJMSType(String)", 2);
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
/*     */   private void throwBadJMSTypeException(String value) throws JMSException {
/* 793 */     if (Trace.isOn) {
/* 794 */       Trace.entry(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "throwBadJMSTypeException(String)", new Object[] { value });
/*     */     }
/*     */     
/* 797 */     HashMap<String, String> inserts = new HashMap<>();
/* 798 */     inserts.put("XMSC_INSERT_PROPERTY", "JMSType");
/* 799 */     inserts.put("XMSC_INSERT_VALUE", value);
/* 800 */     JMSException je = (JMSException)NLSServices.createException("JMSCMQ1006", inserts);
/* 801 */     if (Trace.isOn) {
/* 802 */       Trace.throwing(this, "com.ibm.msg.client.wmq.common.internal.messages.WMQMessageHeader", "throwBadJMSTypeException(String)", (Throwable)je);
/*     */     }
/*     */     
/* 805 */     throw je;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\common\internal\messages\WMQMessageHeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */