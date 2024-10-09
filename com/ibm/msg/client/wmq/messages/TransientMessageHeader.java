/*     */ package com.ibm.msg.client.wmq.messages;
/*     */ 
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQUtils;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public class TransientMessageHeader
/*     */   extends TransientMessageProps
/*     */ {
/*     */   private static final long serialVersionUID = 2045246072882327995L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/messages/TransientMessageHeader.java";
/*     */   private static final int DEFAULT_DELIVERY_MODE = 2;
/*     */   private static final long DEFAULT_EXPIRATION = 0L;
/*     */   private static final long DEFAULT_DELIVERY_DELAY = 0L;
/*     */   private static final long DEFAULT_DELIVERY_TIME = 0L;
/*     */   private static final int DEFAULT_PRIORITY = 4;
/*     */   private static final boolean DEFAULT_REDELIVERED = false;
/*     */   private static final long DEFAULT_TIMESTAMP = 0L;
/*     */   
/*     */   static {
/*  42 */     if (Trace.isOn) {
/*  43 */       Trace.data("com.ibm.msg.client.wmq.messages.TransientMessageHeader", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.factories/src/com/ibm/msg/client/wmq/messages/TransientMessageHeader.java");
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
/*  65 */   private static final byte[] DEFAULT_CORRELID_BYTES = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   private long jmsDeliveryDelay = 0L;
/*  72 */   private long jmsDeliveryTime = 0L;
/*  73 */   private int jmsDeliveryMode = 2;
/*  74 */   private int jmsPriority = 4;
/*  75 */   private long jmsExpiration = 0L;
/*  76 */   private long jmsTimestamp = 0L;
/*     */   private boolean jmsRedelivered = false;
/*  78 */   private String jmsMessageID = null;
/*  79 */   private String jmsDestinationAsString = null;
/*  80 */   private String jmsReplyToAsString = null;
/*  81 */   private String jmsType = null;
/*  82 */   private String jmsCorrelationID = null;
/*  83 */   private byte[] jmsCorrelationIDAsBytes = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   protected String messageClass = "jms_none";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   protected String jmsType_Domain = null;
/*  98 */   protected String jmsType_Format = null;
/*  99 */   protected String jmsType_Set = null;
/* 100 */   protected String jmsType_Type = null;
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
/* 114 */     if (this.jmsCorrelationID == null && this.jmsCorrelationIDAsBytes != null && !Arrays.equals(this.jmsCorrelationIDAsBytes, DEFAULT_CORRELID_BYTES)) {
/* 115 */       this.jmsCorrelationID = WMQUtils.idToString(this.jmsCorrelationIDAsBytes);
/*     */     }
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "getJMSCorrelationID()", "getter", this.jmsCorrelationID);
/*     */     }
/*     */     
/* 121 */     return this.jmsCorrelationID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
/* 131 */     if (this.jmsCorrelationIDAsBytes == null && this.jmsCorrelationID != null) {
/*     */       try {
/* 133 */         this.jmsCorrelationIDAsBytes = WMQUtils.computeBytesFromText(this.jmsCorrelationID, JmqiCodepage.getJmqiCodepage(null, 1208));
/*     */       }
/* 135 */       catch (NullPointerException e) {
/* 136 */         if (Trace.isOn) {
/* 137 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "getJMSCorrelationIDAsBytes()", e);
/*     */         }
/*     */         
/* 140 */         HashMap<String, Object> inserts = new HashMap<>();
/* 141 */         inserts.put("CCSID", "ccsid:" + Integer.toString(1208));
/* 142 */         JMSException je = (JMSException)NLSServices.createException("JMSWMQ1046", inserts);
/* 143 */         if (Trace.isOn) {
/* 144 */           Trace.throwing(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "getJMSCorrelationIDAsBytes()", (Throwable)je);
/*     */         }
/*     */         
/* 147 */         throw je;
/*     */       } 
/*     */     }
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "getJMSCorrelationIDAsBytes()", "getter", this.jmsCorrelationIDAsBytes);
/*     */     }
/*     */     
/* 154 */     return this.jmsCorrelationIDAsBytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getJMSDeliveryMode() throws JMSException {
/* 163 */     Integer dm = Integer.valueOf(this.jmsDeliveryMode);
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "getJMSDeliveryMode()", "getter", dm);
/*     */     }
/*     */     
/* 168 */     return dm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getJMSDeliveryDelay() throws JMSException {
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "getJMSDeliveryDelay()", "getter", 
/* 180 */           Long.valueOf(this.jmsDeliveryDelay));
/*     */     }
/* 182 */     return this.jmsDeliveryDelay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSDeliveryDelay(long deliveryDelay) throws JMSException {
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "setJMSDeliveryDelay(long)", "setter", 
/* 194 */           Long.valueOf(deliveryDelay));
/*     */     }
/* 196 */     this.jmsDeliveryDelay = deliveryDelay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getJMSDeliveryTime() throws JMSException {
/* 206 */     if (Trace.isOn) {
/* 207 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "getJMSDeliveryTime()", "getter", 
/* 208 */           Long.valueOf(this.jmsDeliveryTime));
/*     */     }
/* 210 */     return this.jmsDeliveryTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSDeliveryTime(long deliveryTime) throws JMSException {
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "setJMSDeliveryTime(long)", "setter", 
/* 222 */           Long.valueOf(deliveryTime));
/*     */     }
/* 224 */     this.jmsDeliveryTime = deliveryTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getJMSDestinationAsString() throws JMSException {
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "getJMSDestinationAsString()", "getter", this.jmsDestinationAsString);
/*     */     }
/*     */     
/* 237 */     return this.jmsDestinationAsString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Long getJMSExpiration() throws JMSException {
/* 246 */     Long exp = Long.valueOf(this.jmsExpiration);
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "getJMSExpiration()", "getter", exp);
/*     */     }
/*     */     
/* 251 */     return exp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getJMSMessageID() throws JMSException {
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "getJMSMessageID()", "getter", this.jmsMessageID);
/*     */     }
/*     */     
/* 264 */     return this.jmsMessageID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getJMSPriority() throws JMSException {
/* 273 */     Integer pri = Integer.valueOf(this.jmsPriority);
/* 274 */     if (Trace.isOn) {
/* 275 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "getJMSPriority()", "getter", pri);
/*     */     }
/*     */     
/* 278 */     return pri;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean getJMSRedelivered() throws JMSException {
/* 287 */     Boolean redelivered = Boolean.valueOf(this.jmsRedelivered);
/* 288 */     if (Trace.isOn) {
/* 289 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "getJMSRedelivered()", "getter", redelivered);
/*     */     }
/*     */     
/* 292 */     return redelivered;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getJMSReplyToAsString() throws JMSException {
/* 301 */     if (Trace.isOn) {
/* 302 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "getJMSReplyToAsString()", "getter", this.jmsReplyToAsString);
/*     */     }
/*     */     
/* 305 */     return this.jmsReplyToAsString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Long getJMSTimestamp() throws JMSException {
/* 314 */     Long ts = Long.valueOf(this.jmsTimestamp);
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "getJMSTimestamp()", "getter", ts);
/*     */     }
/*     */     
/* 319 */     return ts;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getJMSType() throws JMSException {
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "getJMSType()", "getter", this.jmsType);
/*     */     }
/*     */     
/* 332 */     return this.jmsType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSCorrelationID(String correlID) throws JMSException {
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "setJMSCorrelationID(String)", "setter", correlID);
/*     */     }
/*     */ 
/*     */     
/* 346 */     if (correlID == null) {
/* 347 */       this.jmsCorrelationID = null;
/* 348 */       this.jmsCorrelationIDAsBytes = null;
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 353 */     else if (correlID.startsWith("ID:")) {
/*     */       
/* 355 */       this.jmsCorrelationIDAsBytes = WMQUtils.stringToId(correlID);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 360 */       this.jmsCorrelationIDAsBytes = null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 365 */     this.jmsCorrelationID = correlID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSCorrelationIDAsBytes(byte[] correlIDBytes) throws JMSException {
/* 374 */     if (Trace.isOn) {
/* 375 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "setJMSCorrelationIDAsBytes(byte [ ])", "setter", correlIDBytes);
/*     */     }
/*     */ 
/*     */     
/* 379 */     if (correlIDBytes == null) {
/* 380 */       this.jmsCorrelationIDAsBytes = null;
/* 381 */       this.jmsCorrelationID = null;
/*     */     } else {
/* 383 */       this.jmsCorrelationIDAsBytes = new byte[correlIDBytes.length];
/* 384 */       System.arraycopy(correlIDBytes, 0, this.jmsCorrelationIDAsBytes, 0, correlIDBytes.length);
/*     */ 
/*     */       
/* 387 */       this.jmsCorrelationID = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSDeliveryMode(int deliveryMode) throws JMSException {
/* 397 */     if (Trace.isOn) {
/* 398 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "setJMSDeliveryMode(int)", "setter", 
/* 399 */           Integer.valueOf(deliveryMode));
/*     */     }
/*     */ 
/*     */     
/* 403 */     if (deliveryMode == -2) {
/* 404 */       this.jmsDeliveryMode = 2;
/* 405 */       this.hideDeliveryMode = true;
/* 406 */     } else if (deliveryMode == -3) {
/* 407 */       this.jmsDeliveryMode = -2;
/* 408 */       this.hideDeliveryMode = true;
/*     */     } else {
/* 410 */       this.jmsDeliveryMode = deliveryMode;
/* 411 */       this.hideDeliveryMode = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSDestinationAsString(String newDestinationAsString) throws JMSException {
/* 421 */     if (Trace.isOn) {
/* 422 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "setJMSDestinationAsString(String)", "setter", newDestinationAsString);
/*     */     }
/*     */     
/* 425 */     this.jmsDestinationAsString = newDestinationAsString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSExpiration(long expiration) throws JMSException {
/* 434 */     if (Trace.isOn) {
/* 435 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "setJMSExpiration(long)", "setter", 
/* 436 */           Long.valueOf(expiration));
/*     */     }
/* 438 */     this.jmsExpiration = expiration;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSMessageID(String messageID) throws JMSException {
/* 447 */     if (Trace.isOn) {
/* 448 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "setJMSMessageID(String)", "setter", messageID);
/*     */     }
/*     */     
/* 451 */     this.jmsMessageID = messageID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSPriority(int priority) throws JMSException {
/* 460 */     if (Trace.isOn) {
/* 461 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "setJMSPriority(int)", "setter", 
/* 462 */           Integer.valueOf(priority));
/*     */     }
/* 464 */     this.jmsPriority = priority;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSRedelivered(boolean redelivered) throws JMSException {
/* 473 */     if (Trace.isOn) {
/* 474 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "setJMSRedelivered(boolean)", "setter", 
/* 475 */           Boolean.valueOf(redelivered));
/*     */     }
/* 477 */     this.jmsRedelivered = redelivered;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSReplyToAsString(String newReplyToAsString) throws JMSException {
/* 486 */     if (Trace.isOn) {
/* 487 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "setJMSReplyToAsString(String)", "setter", newReplyToAsString);
/*     */     }
/*     */     
/* 490 */     this.jmsReplyToAsString = newReplyToAsString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSTimestamp(long timestamp) throws JMSException {
/* 499 */     if (Trace.isOn) {
/* 500 */       Trace.data(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "setJMSTimestamp(long)", "setter", 
/* 501 */           Long.valueOf(timestamp));
/*     */     }
/* 503 */     this.jmsTimestamp = timestamp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJMSType(String type) throws JMSException {
/*     */     String path;
/* 512 */     if (Trace.isOn) {
/* 513 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "setJMSType(String)", new Object[] { type });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 519 */     this.jmsType = type;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 527 */     if (type == null || !type.startsWith("mcd://")) {
/* 528 */       this.jmsType_Domain = null;
/* 529 */       this.jmsType_Set = null;
/* 530 */       this.jmsType_Type = null;
/* 531 */       this.jmsType_Format = null;
/* 532 */       if (Trace.isOn) {
/* 533 */         Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "setJMSType(String)", 1);
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
/* 554 */     String msDomain = null;
/* 555 */     String msSet = null;
/* 556 */     String msType = null;
/* 557 */     String msFormat = null;
/*     */ 
/*     */     
/* 560 */     int index = 6;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 571 */     int query = type.indexOf('?', index);
/*     */     
/* 573 */     if (query == -1) {
/* 574 */       path = type;
/*     */     } else {
/* 576 */       path = type.substring(0, query);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 581 */     int separator = path.indexOf('/', index);
/*     */     
/* 583 */     if (separator == -1) {
/*     */ 
/*     */ 
/*     */       
/* 587 */       if (path.length() <= 6) {
/* 588 */         throwBadJMSTypeException(type);
/*     */       }
/* 590 */       msDomain = path.substring(index);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 595 */       if (separator == index) {
/* 596 */         throwBadJMSTypeException(type);
/*     */       }
/*     */       
/* 599 */       msDomain = path.substring(index, separator);
/* 600 */       index = separator + 1;
/*     */ 
/*     */       
/* 603 */       separator = path.indexOf('/', index);
/*     */       
/* 605 */       if (separator == -1) {
/* 606 */         msSet = path.substring(index);
/*     */       } else {
/* 608 */         msSet = path.substring(index, separator);
/* 609 */         index = separator + 1;
/* 610 */         msType = path.substring(index);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 615 */     if (query > 0)
/*     */     {
/*     */       
/* 618 */       if (type.regionMatches(true, query, "?format=", 0, 8)) {
/* 619 */         msFormat = type.substring(query + 8);
/*     */         
/* 621 */         if (msFormat.indexOf('&') != -1) {
/* 622 */           throwBadJMSTypeException(type);
/*     */         }
/*     */       } else {
/* 625 */         throwBadJMSTypeException(type);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 630 */     this.jmsType_Domain = msDomain;
/* 631 */     this.jmsType_Set = msSet;
/* 632 */     this.jmsType_Type = msType;
/* 633 */     this.jmsType_Format = msFormat;
/* 634 */     if (Trace.isOn) {
/* 635 */       Trace.exit(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "setJMSType(String)", 2);
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
/* 647 */     if (Trace.isOn) {
/* 648 */       Trace.entry(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "throwBadJMSTypeException(String)", new Object[] { value });
/*     */     }
/*     */     
/* 651 */     HashMap<String, String> inserts = new HashMap<>();
/* 652 */     inserts.put("XMSC_INSERT_PROPERTY", "JMSType");
/* 653 */     inserts.put("XMSC_INSERT_VALUE", value);
/* 654 */     JMSException je = (JMSException)NLSServices.createException("JMSWMQ1006", inserts);
/* 655 */     if (Trace.isOn) {
/* 656 */       Trace.throwing(this, "com.ibm.msg.client.wmq.messages.TransientMessageHeader", "throwBadJMSTypeException(String)", (Throwable)je);
/*     */     }
/*     */     
/* 659 */     throw je;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\messages\TransientMessageHeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */