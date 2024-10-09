/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsDestination;
/*     */ import com.ibm.msg.client.jms.JmsTopic;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.ObjectStreamField;
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Topic;
/*     */ import javax.naming.NamingException;
/*     */ import javax.naming.RefAddr;
/*     */ import javax.naming.Reference;
/*     */ import javax.naming.Referenceable;
/*     */ import javax.naming.StringRefAddr;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQTopic
/*     */   extends MQDestination
/*     */   implements Topic, JmsTopic, Referenceable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = 3256711370610315851L;
/*     */   
/*     */   static {
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.data("com.ibm.mq.jms.MQTopic", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQTopic.java");
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
/*  70 */   private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("baseTopicName", String.class), new ObjectStreamField("brokerCCDurSubQueue", String.class), new ObjectStreamField("brokerDurSubQueue", String.class), new ObjectStreamField("brokerPubQueue", String.class), new ObjectStreamField("brokerPubQueueManager", String.class), new ObjectStreamField("brokerVersion", int.class) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQTopic() {
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.entry(this, "com.ibm.mq.jms.MQTopic", "<init>()");
/*     */     }
/*     */     
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.exit(this, "com.ibm.mq.jms.MQTopic", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQTopic(JmsDestination delegate) {
/*  93 */     super(delegate);
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.entry(this, "com.ibm.mq.jms.MQTopic", "<init>(JmsDestination)", new Object[] { delegate });
/*     */     }
/*     */ 
/*     */     
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.exit(this, "com.ibm.mq.jms.MQTopic", "<init>(JmsDestination)");
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
/*     */   public MQTopic(String topicName) throws JMSException {
/* 112 */     super(topicName);
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.entry(this, "com.ibm.mq.jms.MQTopic", "<init>(String)", new Object[] { topicName });
/*     */     }
/*     */     
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.exit(this, "com.ibm.mq.jms.MQTopic", "<init>(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBaseTopicName() {
/* 129 */     String traceRet1 = getName();
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.data(this, "com.ibm.mq.jms.MQTopic", "getBaseTopicName()", "getter", traceRet1);
/*     */     }
/* 133 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBrokerCCDurSubQueue() {
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.entry(this, "com.ibm.mq.jms.MQTopic", "getBrokerCCDurSubQueue()");
/*     */     }
/*     */     
/*     */     try {
/* 148 */       String traceRet1 = this.propertyDelegate.getStringProperty("brokerCCDurSubQueue");
/* 149 */       if (Trace.isOn) {
/* 150 */         Trace.exit(this, "com.ibm.mq.jms.MQTopic", "getBrokerCCDurSubQueue()", traceRet1, 1);
/*     */       }
/* 152 */       return traceRet1;
/*     */     }
/* 154 */     catch (JMSException je) {
/* 155 */       if (Trace.isOn) {
/* 156 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopic", "getBrokerCCDurSubQueue()", (Throwable)je);
/*     */       }
/* 158 */       if (Trace.isOn) {
/* 159 */         Trace.exit(this, "com.ibm.mq.jms.MQTopic", "getBrokerCCDurSubQueue()", null, 2);
/*     */       }
/* 161 */       return null;
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
/*     */   public String getBrokerDurSubQueue() {
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.entry(this, "com.ibm.mq.jms.MQTopic", "getBrokerDurSubQueue()");
/*     */     }
/*     */     
/*     */     try {
/* 178 */       String traceRet1 = this.propertyDelegate.getStringProperty("brokerDurSubQueue");
/* 179 */       if (Trace.isOn) {
/* 180 */         Trace.exit(this, "com.ibm.mq.jms.MQTopic", "getBrokerDurSubQueue()", traceRet1, 1);
/*     */       }
/* 182 */       return traceRet1;
/*     */     }
/* 184 */     catch (JMSException je) {
/* 185 */       if (Trace.isOn) {
/* 186 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopic", "getBrokerDurSubQueue()", (Throwable)je);
/*     */       }
/* 188 */       if (Trace.isOn) {
/* 189 */         Trace.exit(this, "com.ibm.mq.jms.MQTopic", "getBrokerDurSubQueue()", null, 2);
/*     */       }
/* 191 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBrokerPubQueue() {
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.entry(this, "com.ibm.mq.jms.MQTopic", "getBrokerPubQueue()");
/*     */     }
/*     */     
/*     */     try {
/* 207 */       String traceRet1 = this.propertyDelegate.getStringProperty("XMSC_WMQ_BROKER_PUBQ");
/* 208 */       if (Trace.isOn) {
/* 209 */         Trace.exit(this, "com.ibm.mq.jms.MQTopic", "getBrokerPubQueue()", traceRet1, 1);
/*     */       }
/* 211 */       return traceRet1;
/*     */     }
/* 213 */     catch (JMSException je) {
/* 214 */       if (Trace.isOn) {
/* 215 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopic", "getBrokerPubQueue()", (Throwable)je);
/*     */       }
/* 217 */       if (Trace.isOn) {
/* 218 */         Trace.exit(this, "com.ibm.mq.jms.MQTopic", "getBrokerPubQueue()", null, 2);
/*     */       }
/* 220 */       return null;
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
/*     */   public String getBrokerPubQueueManager() {
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.entry(this, "com.ibm.mq.jms.MQTopic", "getBrokerPubQueueManager()");
/*     */     }
/*     */     
/*     */     try {
/* 237 */       String traceRet1 = this.propertyDelegate.getStringProperty("XMSC_WMQ_BROKER_PUBQ_QMGR");
/* 238 */       if (Trace.isOn) {
/* 239 */         Trace.exit(this, "com.ibm.mq.jms.MQTopic", "getBrokerPubQueueManager()", traceRet1, 1);
/*     */       }
/* 241 */       return traceRet1;
/*     */     }
/* 243 */     catch (JMSException je) {
/* 244 */       if (Trace.isOn) {
/* 245 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopic", "getBrokerPubQueueManager()", (Throwable)je);
/*     */       }
/* 247 */       if (Trace.isOn) {
/* 248 */         Trace.exit(this, "com.ibm.mq.jms.MQTopic", "getBrokerPubQueueManager()", null, 2);
/*     */       }
/* 250 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBrokerVersion() {
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.entry(this, "com.ibm.mq.jms.MQTopic", "getBrokerVersion()");
/*     */     }
/*     */     
/*     */     try {
/* 265 */       int traceRet1 = this.propertyDelegate.getIntProperty("brokerVersion");
/* 266 */       if (Trace.isOn) {
/* 267 */         Trace.exit(this, "com.ibm.mq.jms.MQTopic", "getBrokerVersion()", 
/* 268 */             Integer.valueOf(traceRet1), 1);
/*     */       }
/* 270 */       return traceRet1;
/*     */     }
/* 272 */     catch (JMSException e) {
/* 273 */       if (Trace.isOn) {
/* 274 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopic", "getBrokerVersion()", (Throwable)e);
/*     */       }
/* 276 */       if (Trace.isOn) {
/* 277 */         Trace.exit(this, "com.ibm.mq.jms.MQTopic", "getBrokerVersion()", Integer.valueOf(0), 2);
/*     */       }
/* 279 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int getMulticast() {
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.traceData(this, "com.ibm.mq.jms.MQTopic", "getMulticast()", "Deprecated method called. Returning 0", null);
/*     */     }
/*     */     
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.data(this, "com.ibm.mq.jms.MQTopic", "getMulticast()", "getter", Integer.valueOf(0));
/*     */     }
/* 297 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Reference getReference() throws NamingException {
/* 307 */     String s = null;
/* 308 */     String u = null;
/* 309 */     Reference ref = new Reference(MQTopic.class.getName(), MQTopicFactory.class.getName(), null);
/*     */     
/* 311 */     ref.add(new StringRefAddr("VER", String.valueOf(getVersion())));
/*     */     
/* 313 */     s = getDescription();
/* 314 */     if (s != null) {
/* 315 */       ref.add(new StringRefAddr("DESC", s));
/*     */     }
/*     */     
/* 318 */     ref.add(new StringRefAddr("EXP", String.valueOf(getExpiry())));
/* 319 */     ref.add(new StringRefAddr("PRI", String.valueOf(getPriority())));
/* 320 */     ref.add(new StringRefAddr("PER", String.valueOf(getPersistence())));
/* 321 */     ref.add(new StringRefAddr("CCS", String.valueOf(getCCSID())));
/* 322 */     ref.add(new StringRefAddr("TC", String.valueOf(getTargetClient())));
/* 323 */     ref.add(new StringRefAddr("ENC", String.valueOf(getEncoding())));
/* 324 */     ref.add(new StringRefAddr("FIQ", String.valueOf(getFailIfQuiesce())));
/*     */     
/* 326 */     u = getAlternateUserId();
/* 327 */     if (u != null) {
/* 328 */       ref.add(new StringRefAddr("ALTU", u));
/*     */     }
/*     */     
/*     */     try {
/* 332 */       ref.add(new StringRefAddr("WCFMT", String.valueOf(getWildcardFormat())));
/*     */ 
/*     */     
/*     */     }
/* 336 */     catch (JMSException j) {
/* 337 */       if (Trace.isOn) {
/* 338 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopic", "getReference()", (Throwable)j, 1);
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 343 */       ref.add(new StringRefAddr("RACP", String.valueOf(getReadAheadClosePolicy())));
/*     */ 
/*     */     
/*     */     }
/* 347 */     catch (JMSException j) {
/* 348 */       if (Trace.isOn) {
/* 349 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopic", "getReference()", (Throwable)j, 2);
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 354 */       ref.add(new StringRefAddr("RAALD", String.valueOf(getReadAheadAllowed())));
/*     */ 
/*     */     
/*     */     }
/* 358 */     catch (JMSException j) {
/* 359 */       if (Trace.isOn) {
/* 360 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopic", "getReference()", (Throwable)j, 3);
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 365 */       ref.add(new StringRefAddr("PAALD", String.valueOf(getPutAsyncAllowed())));
/*     */ 
/*     */     
/*     */     }
/* 369 */     catch (JMSException j) {
/* 370 */       if (Trace.isOn) {
/* 371 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopic", "getReference()", (Throwable)j, 4);
/*     */       }
/*     */     } 
/*     */     try {
/* 375 */       ref.add(new StringRefAddr("MBODY", String.valueOf(getMessageBodyStyle())));
/*     */ 
/*     */     
/*     */     }
/* 379 */     catch (JMSException j) {
/* 380 */       if (Trace.isOn) {
/* 381 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopic", "getReference()", (Throwable)j, 5);
/*     */       }
/*     */     } 
/*     */     try {
/* 385 */       ref.add(new StringRefAddr("MDR", String.valueOf(getMQMDReadEnabled())));
/*     */ 
/*     */     
/*     */     }
/* 389 */     catch (JMSException j) {
/* 390 */       if (Trace.isOn) {
/* 391 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopic", "getReference()", (Throwable)j, 6);
/*     */       }
/*     */     } 
/*     */     try {
/* 395 */       ref.add(new StringRefAddr("MDW", String.valueOf(getMQMDWriteEnabled())));
/*     */ 
/*     */     
/*     */     }
/* 399 */     catch (JMSException j) {
/* 400 */       if (Trace.isOn) {
/* 401 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopic", "getReference()", (Throwable)j, 7);
/*     */       }
/*     */     } 
/*     */     try {
/* 405 */       ref.add(new StringRefAddr("MDCTX", String.valueOf(getMQMDMessageContext())));
/*     */ 
/*     */     
/*     */     }
/* 409 */     catch (JMSException j) {
/* 410 */       if (Trace.isOn) {
/* 411 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopic", "getReference()", (Throwable)j, 8);
/*     */       }
/*     */     } 
/*     */     
/* 415 */     s = getBaseTopicName();
/* 416 */     if (s != null) {
/* 417 */       ref.add(new StringRefAddr("TOP", s));
/*     */     }
/*     */     
/* 420 */     s = getBrokerDurSubQueue();
/* 421 */     if (s != null) {
/* 422 */       ref.add(new StringRefAddr("BDSUB", s));
/*     */     }
/*     */     
/* 425 */     s = getBrokerCCDurSubQueue();
/* 426 */     if (s != null) {
/* 427 */       ref.add(new StringRefAddr("CCDSUB", s));
/*     */     }
/*     */     
/* 430 */     ref.add(new StringRefAddr("BVER", String.valueOf(getBrokerVersion())));
/*     */ 
/*     */     
/* 433 */     s = getBrokerPubQueue();
/* 434 */     if (s != null) {
/* 435 */       ref.add(new StringRefAddr("BPUBQ", s));
/*     */     }
/* 437 */     s = getBrokerPubQueueManager();
/* 438 */     if (s != null) {
/* 439 */       ref.add(new StringRefAddr("BQM", s));
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 444 */       ref.add(new StringRefAddr("UMA", String.valueOf(getUnmappableAction())));
/*     */ 
/*     */     
/*     */     }
/* 448 */     catch (JMSException j) {
/* 449 */       if (Trace.isOn) {
/* 450 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopic", "getReference()", (Throwable)j, 9);
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 455 */       ref.add(new StringRefAddr("UMR", String.valueOf(getUnmappableReplacement())));
/*     */ 
/*     */     
/*     */     }
/* 459 */     catch (JMSException j) {
/* 460 */       if (Trace.isOn) {
/* 461 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopic", "getReference()", (Throwable)j, 10);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 467 */     Reference superClassReference = super.getReference();
/* 468 */     if (superClassReference != null) {
/* 469 */       Enumeration<?> e = superClassReference.getAll();
/* 470 */       while (e.hasMoreElements()) {
/* 471 */         RefAddr currentRefAddr = (RefAddr)e.nextElement();
/* 472 */         ref.add(currentRefAddr);
/*     */       } 
/*     */     } 
/*     */     
/* 476 */     if (Trace.isOn) {
/* 477 */       Trace.data(this, "com.ibm.mq.jms.MQTopic", "getReference()", "getter", ref);
/*     */     }
/* 479 */     return ref;
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
/*     */   public String getTopicURI() {
/* 492 */     String result = toURI();
/*     */     
/* 494 */     if (Trace.isOn) {
/* 495 */       Trace.data(this, "com.ibm.mq.jms.MQTopic", "getTopicURI()", "getter", result);
/*     */     }
/* 497 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTopicName() {
/* 505 */     if (Trace.isOn) {
/* 506 */       Trace.entry(this, "com.ibm.mq.jms.MQTopic", "getTopicName()");
/*     */     }
/*     */     try {
/* 509 */       String traceRet1 = super.getTopicName();
/* 510 */       if (Trace.isOn) {
/* 511 */         Trace.exit(this, "com.ibm.mq.jms.MQTopic", "getTopicName()", traceRet1, 1);
/*     */       }
/* 513 */       return traceRet1;
/*     */     }
/* 515 */     catch (JMSException e) {
/* 516 */       if (Trace.isOn) {
/* 517 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopic", "getTopicName()", (Throwable)e);
/*     */       }
/*     */ 
/*     */       
/* 521 */       if (Trace.isOn) {
/* 522 */         Trace.exit(this, "com.ibm.mq.jms.MQTopic", "getTopicName()", "", 2);
/*     */       }
/* 524 */       return "";
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 529 */     if (Trace.isOn) {
/* 530 */       Trace.entry(this, "com.ibm.mq.jms.MQTopic", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 540 */     ObjectInputStream.GetField fields = in.readFields();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 560 */       if (!fields.defaulted("baseTopicName")) {
/* 561 */         setBaseTopicName((String)fields.get("baseTopicName", (Object)null));
/*     */       }
/* 563 */       if (!fields.defaulted("brokerCCDurSubQueue")) {
/* 564 */         setBrokerCCDurSubQueue((String)fields.get("brokerCCDurSubQueue", (Object)null));
/*     */       }
/* 566 */       if (!fields.defaulted("brokerDurSubQueue")) {
/* 567 */         setBrokerDurSubQueue((String)fields.get("brokerDurSubQueue", (Object)null));
/*     */       }
/* 569 */       if (!fields.defaulted("brokerPubQueue")) {
/* 570 */         setBrokerPubQueue((String)fields.get("brokerPubQueue", (Object)null));
/*     */       }
/* 572 */       if (!fields.defaulted("brokerPubQueueManager")) {
/* 573 */         setBrokerPubQueueManager((String)fields.get("brokerPubQueueManager", (Object)null));
/*     */       }
/* 575 */       if (!fields.defaulted("brokerVersion")) {
/* 576 */         setBrokerVersion(fields.get("brokerVersion", 0));
/*     */       }
/*     */     }
/* 579 */     catch (JMSException je) {
/* 580 */       if (Trace.isOn) {
/* 581 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQTopic", "readObject(java.io.ObjectInputStream)", (Throwable)je);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 588 */     if (Trace.isOn) {
/* 589 */       Trace.exit(this, "com.ibm.mq.jms.MQTopic", "readObject(java.io.ObjectInputStream)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBaseTopicName(String tName) throws JMSException {
/* 600 */     if (Trace.isOn) {
/* 601 */       Trace.data(this, "com.ibm.mq.jms.MQTopic", "setBaseTopicName(String)", "setter", tName);
/*     */     }
/* 603 */     this.propertyDelegate.setStringProperty("XMSC_DESTINATION_NAME", tName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBrokerCCDurSubQueue(String queueName) throws JMSException {
/* 614 */     if (Trace.isOn) {
/* 615 */       Trace.data(this, "com.ibm.mq.jms.MQTopic", "setBrokerCCDurSubQueue(String)", "setter", queueName);
/*     */     }
/*     */     
/* 618 */     this.propertyDelegate.setStringProperty("brokerCCDurSubQueue", queueName);
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
/*     */   public void setBrokerDurSubQueue(String queueName) throws JMSException {
/* 630 */     if (Trace.isOn) {
/* 631 */       Trace.data(this, "com.ibm.mq.jms.MQTopic", "setBrokerDurSubQueue(String)", "setter", queueName);
/*     */     }
/*     */     
/* 634 */     this.propertyDelegate.setStringProperty("brokerDurSubQueue", queueName);
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
/*     */   public void setBrokerPubQueue(String queueName) throws JMSException {
/* 648 */     if (Trace.isOn) {
/* 649 */       Trace.data(this, "com.ibm.mq.jms.MQTopic", "setBrokerPubQueue(String)", "setter", queueName);
/*     */     }
/*     */     
/* 652 */     this.propertyDelegate.setStringProperty("XMSC_WMQ_BROKER_PUBQ", queueName);
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
/*     */   public void setBrokerPubQueueManager(String queueManagerName) throws JMSException {
/* 664 */     if (Trace.isOn) {
/* 665 */       Trace.data(this, "com.ibm.mq.jms.MQTopic", "setBrokerPubQueueManager(String)", "setter", queueManagerName);
/*     */     }
/*     */     
/* 668 */     this.propertyDelegate.setStringProperty("XMSC_WMQ_BROKER_PUBQ_QMGR", queueManagerName);
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
/*     */   public void setBrokerVersion(int version) throws JMSException {
/* 683 */     if (Trace.isOn) {
/* 684 */       Trace.data(this, "com.ibm.mq.jms.MQTopic", "setBrokerVersion(int)", "setter", 
/* 685 */           Integer.valueOf(version));
/*     */     }
/* 687 */     this.propertyDelegate.setIntProperty("brokerVersion", version);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setFromReference(Reference ref) throws JMSException {
/* 697 */     if (Trace.isOn) {
/* 698 */       Trace.data(this, "com.ibm.mq.jms.MQTopic", "setFromReference(Reference)", "setter", ref);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 705 */     RefAddr addr = ref.get("TOP");
/* 706 */     if (addr != null) {
/* 707 */       Object value = addr.getContent();
/* 708 */       if (value != null) {
/* 709 */         setBaseTopicName((String)value);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 714 */     setCommonFromReference(ref);
/*     */ 
/*     */ 
/*     */     
/* 718 */     addr = ref.get("BDSUB");
/* 719 */     if (addr != null) {
/* 720 */       Object value = addr.getContent();
/* 721 */       if (value != null) {
/* 722 */         setBrokerDurSubQueue((String)value);
/*     */       }
/*     */     } 
/* 725 */     addr = ref.get("CCDSUB");
/* 726 */     if (addr != null) {
/* 727 */       Object value = addr.getContent();
/* 728 */       if (value != null) {
/* 729 */         setBrokerCCDurSubQueue((String)value);
/*     */       }
/*     */     } 
/* 732 */     addr = ref.get("BVER");
/* 733 */     if (addr != null) {
/* 734 */       Object value = addr.getContent();
/* 735 */       if (value != null) {
/* 736 */         setBrokerVersion(Integer.parseInt((String)value));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 741 */     addr = ref.get("BPUBQ");
/* 742 */     if (addr != null) {
/* 743 */       Object value = addr.getContent();
/* 744 */       if (value != null) {
/* 745 */         setBrokerPubQueue((String)value);
/*     */       }
/*     */     } 
/* 748 */     addr = ref.get("BQM");
/* 749 */     if (addr != null) {
/* 750 */       Object value = addr.getContent();
/* 751 */       if (value != null) {
/* 752 */         setBrokerPubQueueManager((String)value);
/*     */       }
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
/*     */   @Deprecated
/*     */   public void setMulticast(int multicastMode) throws JMSException {
/* 766 */     if (Trace.isOn) {
/* 767 */       Trace.data(this, "com.ibm.mq.jms.MQTopic", "setMulticast(int)", "setter", 
/* 768 */           Integer.valueOf(multicastMode));
/*     */     }
/*     */     
/* 771 */     if (Trace.isOn) {
/* 772 */       Trace.traceData(this, "com.ibm.mq.jms.MQTopic", "setMulticast(int)", "Deprecated method called: " + multicastMode, null);
/*     */     }
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 777 */     if (Trace.isOn) {
/* 778 */       Trace.entry(this, "com.ibm.mq.jms.MQTopic", "writeObject(java.io.ObjectOutputStream)", new Object[] { out });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 786 */     ObjectOutputStream.PutField fields = out.putFields();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 791 */     fields.put("baseTopicName", getBaseTopicName());
/* 792 */     fields.put("brokerCCDurSubQueue", getBrokerCCDurSubQueue());
/* 793 */     fields.put("brokerDurSubQueue", getBrokerDurSubQueue());
/* 794 */     fields.put("brokerPubQueue", getBrokerPubQueue());
/* 795 */     fields.put("brokerPubQueueManager", getBrokerPubQueueManager());
/* 796 */     fields.put("brokerVersion", getBrokerVersion());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 801 */     out.writeFields();
/*     */     
/* 803 */     if (Trace.isOn) {
/* 804 */       Trace.exit(this, "com.ibm.mq.jms.MQTopic", "writeObject(java.io.ObjectOutputStream)");
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
/*     */   public String toString() {
/* 816 */     if (Trace.isOn) {
/* 817 */       Trace.entry(this, "com.ibm.mq.jms.MQTopic", "toString()");
/*     */     }
/* 819 */     String uri = toURI();
/* 820 */     String result = (uri == null) ? "topic://" : uri;
/* 821 */     if (Trace.isOn) {
/* 822 */       Trace.exit(this, "com.ibm.mq.jms.MQTopic", "toString()", result);
/*     */     }
/* 824 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQTopic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */