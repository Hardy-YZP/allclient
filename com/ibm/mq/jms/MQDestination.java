/*      */ package com.ibm.mq.jms;
/*      */ 
/*      */ import com.ibm.jms.JMSDestination;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsDestination;
/*      */ import com.ibm.msg.client.jms.admin.JmsJndiDestinationImpl;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.ObjectStreamField;
/*      */ import java.io.Serializable;
/*      */ import java.nio.charset.CodingErrorAction;
/*      */ import java.util.HashMap;
/*      */ import javax.jms.Destination;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.TemporaryQueue;
/*      */ import javax.jms.TemporaryTopic;
/*      */ import javax.naming.NamingException;
/*      */ import javax.naming.RefAddr;
/*      */ import javax.naming.Reference;
/*      */ import javax.naming.StringRefAddr;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQDestination
/*      */   extends JmsJndiDestinationImpl
/*      */   implements Destination, JMSDestination, Serializable
/*      */ {
/*      */   static final long serialVersionUID = -648906405979968516L;
/*      */   
/*      */   static {
/*   57 */     if (Trace.isOn) {
/*   58 */       Trace.data("com.ibm.mq.jms.MQDestination", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQDestination.java");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   66 */     if (Trace.isOn) {
/*   67 */       Trace.entry("com.ibm.mq.jms.MQDestination", "static()");
/*      */     }
/*      */ 
/*      */     
/*   71 */     MQConnectionFactory.checkTracing();
/*      */     
/*   73 */     if (Trace.isOn) {
/*   74 */       Trace.exit("com.ibm.mq.jms.MQDestination", "static()");
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
/*   86 */   private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("alternateUserId", String.class), new ObjectStreamField("CCSID", int.class), new ObjectStreamField("receiveCCSID", int.class), new ObjectStreamField("receiveConversion", int.class), new ObjectStreamField("deliveryMode", int.class), new ObjectStreamField("description", String.class), new ObjectStreamField("encoding", int.class), new ObjectStreamField("expiry", long.class), new ObjectStreamField("failIfQuiesce", int.class), new ObjectStreamField("messageBodyStyle", int.class), new ObjectStreamField("MQMDMessageContext", int.class), new ObjectStreamField("MQMDReadEnabled", boolean.class), new ObjectStreamField("MQMDWriteEnabled", boolean.class), new ObjectStreamField("persistence", int.class), new ObjectStreamField("priority", int.class), new ObjectStreamField("putAsyncAllowed", int.class), new ObjectStreamField("readAheadAllowed", int.class), new ObjectStreamField("readAheadClosePolicy", int.class), new ObjectStreamField("targetClient", int.class), new ObjectStreamField("timeToLive", long.class), new ObjectStreamField("version", int.class), new ObjectStreamField("wildcardFormat", int.class), new ObjectStreamField("replyToStyle", int.class), new ObjectStreamField("unmappableAction", String.class), new ObjectStreamField("unmappableReplacement", byte.class) };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  143 */   protected transient JmsDestination propertyDelegate = (JmsDestination)this;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private transient boolean versionChangeAllowed = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  156 */   static transient String connectionType = "com.ibm.msg.client.wmq";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQDestination() {
/*  163 */     super(connectionType);
/*  164 */     if (Trace.isOn) {
/*  165 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "<init>()");
/*      */     }
/*      */     
/*  168 */     if (Trace.isOn) {
/*  169 */       Trace.exit(this, "com.ibm.mq.jms.MQDestination", "<init>()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQDestination(JmsDestination delegate) {
/*  179 */     if (Trace.isOn) {
/*  180 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "<init>(JmsDestination)", new Object[] { delegate });
/*      */     }
/*      */ 
/*      */     
/*  184 */     this.propertyDelegate = delegate;
/*      */     
/*  186 */     if (Trace.isOn) {
/*  187 */       Trace.exit(this, "com.ibm.mq.jms.MQDestination", "<init>(JmsDestination)");
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
/*      */   public MQDestination(String name) throws JMSException {
/*  199 */     super(connectionType, name);
/*  200 */     if (Trace.isOn) {
/*  201 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "<init>(String)", new Object[] { name });
/*      */     }
/*      */     
/*  204 */     if (Trace.isOn) {
/*  205 */       Trace.exit(this, "com.ibm.mq.jms.MQDestination", "<init>(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setupProviderDestination() {
/*  215 */     if (Trace.isOn) {
/*  216 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "setupProviderDestination()");
/*      */     }
/*      */     try {
/*  219 */       setProviderDestination();
/*      */     }
/*  221 */     catch (JMSException e) {
/*  222 */       if (Trace.isOn) {
/*  223 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "setupProviderDestination()", (Throwable)e);
/*      */       }
/*      */       
/*  226 */       HashMap<String, JMSException> info = new HashMap<>();
/*  227 */       info.put("exception", e);
/*  228 */       Trace.ffst(this, "setupProviderDestination()", "XF008001", info, null);
/*      */     } 
/*  230 */     if (Trace.isOn) {
/*  231 */       Trace.exit(this, "com.ibm.mq.jms.MQDestination", "setupProviderDestination()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String findCorrectField(String field) {
/*  242 */     String[][] fieldNames = { { "cCSID", "delegate", "property", "stringFromDestination", "reference" }, { "CCSID", null, null, null, null } };
/*      */     
/*  244 */     for (int i = 0; i < (fieldNames[0]).length; i++) {
/*  245 */       if (field.equals(fieldNames[0][i])) {
/*  246 */         return fieldNames[1][i];
/*      */       }
/*      */     } 
/*  249 */     return field;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String findCorrectMethod(String method) {
/*  258 */     String[][] methodNames = { { "setSslSocketFactory", "setSslResetCount", "setSslPeerName", "setSslFipsRequired", "setSslCipherSuite", "setSslCertStores_string", "setSslCertStores_coll", "setConnOptions", "setCcdtUrl" }, { "setSSLSocketFactory", "setSSLResetCount", "setSSLPeerName", "setSSLFipsRequired", "setSSLCipherSuite", "setSSLCertStores", "setSSLCertStores", "setMQConnectionOptions", "setCCDTURL" } };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  264 */     for (int i = 0; i < (methodNames[0]).length; i++) {
/*  265 */       if (method.equals(methodNames[0][i])) {
/*  266 */         return methodNames[1][i];
/*      */       }
/*      */     } 
/*  269 */     return method;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAlternateUserId() {
/*  278 */     if (Trace.isOn) {
/*  279 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "getAlternateUserId()");
/*      */     }
/*      */     
/*      */     try {
/*  283 */       String traceRet1 = this.propertyDelegate.getStringProperty("alternateUserId");
/*  284 */       if (Trace.isOn) {
/*  285 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getAlternateUserId()", traceRet1, 1);
/*      */       }
/*  287 */       return traceRet1;
/*      */     }
/*  289 */     catch (JMSException je) {
/*  290 */       if (Trace.isOn) {
/*  291 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "getAlternateUserId()", (Throwable)je);
/*      */       }
/*  293 */       if (Trace.isOn) {
/*  294 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getAlternateUserId()", null, 2);
/*      */       }
/*  296 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCCSID() {
/*  306 */     if (Trace.isOn) {
/*  307 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "getCCSID()");
/*      */     }
/*      */     
/*      */     try {
/*  311 */       int traceRet1 = this.propertyDelegate.getIntProperty("CCSID");
/*  312 */       if (Trace.isOn) {
/*  313 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getCCSID()", Integer.valueOf(traceRet1), 1);
/*      */       }
/*      */       
/*  316 */       return traceRet1;
/*      */     }
/*  318 */     catch (JMSException je) {
/*  319 */       if (Trace.isOn) {
/*  320 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "getCCSID()", (Throwable)je);
/*      */       }
/*  322 */       if (Trace.isOn) {
/*  323 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getCCSID()", Integer.valueOf(0), 2);
/*      */       }
/*  325 */       return 0;
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
/*      */   public String getUnmappableAction() throws JMSException {
/*  337 */     String retVal = this.propertyDelegate.getStringProperty("JMS_IBM_Unmappable_Action");
/*      */     
/*  339 */     if (Trace.isOn) {
/*  340 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "getUnmappableAction()", "getter", retVal);
/*      */     }
/*  342 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte getUnmappableReplacement() throws JMSException {
/*  352 */     Byte retVal = (Byte)this.propertyDelegate.getObjectProperty("JMS_IBM_Unmappable_Replacement");
/*      */     
/*  354 */     if (Trace.isOn) {
/*  355 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "getUnmappableReplacement()", "getter", retVal);
/*      */     }
/*      */     
/*  358 */     return retVal.byteValue();
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
/*      */   public int getReceiveCCSID() throws JMSException {
/*  377 */     int traceRet1 = this.propertyDelegate.getIntProperty("receiveCCSID");
/*  378 */     if (Trace.isOn) {
/*  379 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "getReceiveCCSID()", "getter", 
/*  380 */           Integer.valueOf(traceRet1));
/*      */     }
/*  382 */     return traceRet1;
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
/*      */   public int getReceiveConversion() throws JMSException {
/*  404 */     int traceRet1 = this.propertyDelegate.getIntProperty("receiveConversion");
/*  405 */     if (Trace.isOn) {
/*  406 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "getReceiveConversion()", "getter", 
/*  407 */           Integer.valueOf(traceRet1));
/*      */     }
/*  409 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDescription() {
/*  419 */     if (Trace.isOn) {
/*  420 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "getDescription()");
/*      */     }
/*      */     
/*      */     try {
/*  424 */       String traceRet1 = this.propertyDelegate.getStringProperty("destDescription");
/*  425 */       if (Trace.isOn) {
/*  426 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getDescription()", traceRet1, 1);
/*      */       }
/*  428 */       return traceRet1;
/*      */     }
/*  430 */     catch (JMSException je) {
/*  431 */       if (Trace.isOn) {
/*  432 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "getDescription()", (Throwable)je);
/*      */       }
/*  434 */       if (Trace.isOn) {
/*  435 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getDescription()", null, 2);
/*      */       }
/*  437 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEncoding() {
/*  447 */     if (Trace.isOn) {
/*  448 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "getEncoding()");
/*      */     }
/*      */     
/*      */     try {
/*  452 */       int traceRet1 = this.propertyDelegate.getIntProperty("encoding");
/*  453 */       if (Trace.isOn) {
/*  454 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getEncoding()", 
/*  455 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/*  457 */       return traceRet1;
/*      */     }
/*  459 */     catch (JMSException je) {
/*  460 */       if (Trace.isOn) {
/*  461 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "getEncoding()", (Throwable)je);
/*      */       }
/*  463 */       if (Trace.isOn) {
/*  464 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getEncoding()", Integer.valueOf(0), 2);
/*      */       }
/*  466 */       return 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getExpiry() {
/*  476 */     if (Trace.isOn) {
/*  477 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "getExpiry()");
/*      */     }
/*      */     
/*      */     try {
/*  481 */       long traceRet1 = this.propertyDelegate.getLongProperty("timeToLive");
/*  482 */       if (Trace.isOn) {
/*  483 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getExpiry()", Long.valueOf(traceRet1), 1);
/*      */       }
/*      */       
/*  486 */       return traceRet1;
/*      */     }
/*  488 */     catch (JMSException je) {
/*  489 */       if (Trace.isOn) {
/*  490 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "getExpiry()", (Throwable)je);
/*      */       }
/*  492 */       if (Trace.isOn) {
/*  493 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getExpiry()", Long.valueOf(0L), 2);
/*      */       }
/*  495 */       return 0L;
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
/*      */   public int getFailIfQuiesce() {
/*  510 */     if (Trace.isOn) {
/*  511 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "getFailIfQuiesce()");
/*      */     }
/*      */     
/*      */     try {
/*  515 */       int traceRet1 = this.propertyDelegate.getIntProperty("failIfQuiesce");
/*  516 */       if (Trace.isOn) {
/*  517 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getFailIfQuiesce()", 
/*  518 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/*  520 */       return traceRet1;
/*      */     }
/*  522 */     catch (JMSException je) {
/*  523 */       if (Trace.isOn) {
/*  524 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "getFailIfQuiesce()", (Throwable)je);
/*      */       }
/*  526 */       if (Trace.isOn) {
/*  527 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getFailIfQuiesce()", Integer.valueOf(0), 2);
/*      */       }
/*      */       
/*  530 */       return 0;
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
/*      */   public int getMQMDMessageContext() throws JMSException {
/*  548 */     int messageContext = this.propertyDelegate.getIntProperty("mdMessageContext");
/*  549 */     if (Trace.isOn) {
/*  550 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "getMQMDMessageContext()", "getter", 
/*  551 */           Integer.valueOf(messageContext));
/*      */     }
/*  553 */     return messageContext;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getMQMDWriteEnabled() throws JMSException {
/*  564 */     boolean writeEnabled = this.propertyDelegate.getBooleanProperty("mdWriteEnabled");
/*  565 */     if (Trace.isOn) {
/*  566 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "getMQMDWriteEnabled()", "getter", 
/*  567 */           Boolean.valueOf(writeEnabled));
/*      */     }
/*  569 */     return writeEnabled;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getMQMDReadEnabled() throws JMSException {
/*  580 */     boolean readEnabled = this.propertyDelegate.getBooleanProperty("mdReadEnabled");
/*  581 */     if (Trace.isOn) {
/*  582 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "getMQMDReadEnabled()", "getter", 
/*  583 */           Boolean.valueOf(readEnabled));
/*      */     }
/*  585 */     return readEnabled;
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
/*      */   public int getMessageBodyStyle() throws JMSException {
/*  602 */     int bodyStyle = this.propertyDelegate.getIntProperty("messageBody");
/*  603 */     if (Trace.isOn) {
/*  604 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "getMessageBodyStyle()", "getter", 
/*  605 */           Integer.valueOf(bodyStyle));
/*      */     }
/*  607 */     return bodyStyle;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPersistence() {
/*  616 */     if (Trace.isOn) {
/*  617 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "getPersistence()");
/*      */     }
/*      */     
/*      */     try {
/*  621 */       int traceRet1 = this.propertyDelegate.getIntProperty("deliveryMode");
/*  622 */       if (Trace.isOn) {
/*  623 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getPersistence()", 
/*  624 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/*  626 */       return traceRet1;
/*      */     }
/*  628 */     catch (JMSException je) {
/*  629 */       if (Trace.isOn) {
/*  630 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "getPersistence()", (Throwable)je);
/*      */       }
/*  632 */       if (Trace.isOn) {
/*  633 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getPersistence()", Integer.valueOf(0), 2);
/*      */       }
/*      */       
/*  636 */       return 0;
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
/*      */   public int getPriority() {
/*  651 */     if (Trace.isOn) {
/*  652 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "getPriority()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  657 */       int traceRet1 = this.propertyDelegate.getIntProperty("priority");
/*  658 */       if (Trace.isOn) {
/*  659 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getPriority()", 
/*  660 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/*  662 */       return traceRet1;
/*      */     }
/*  664 */     catch (JMSException je) {
/*  665 */       if (Trace.isOn) {
/*  666 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "getPriority()", (Throwable)je);
/*      */       }
/*  668 */       if (Trace.isOn) {
/*  669 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getPriority()", Integer.valueOf(0), 2);
/*      */       }
/*  671 */       return 0;
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
/*      */   public String getProperty(String name) {
/*  683 */     if (Trace.isOn) {
/*  684 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "getProperty(String)", new Object[] { name });
/*      */     }
/*      */     
/*  687 */     if (name == null) {
/*  688 */       if (Trace.isOn) {
/*  689 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getProperty(String)", null, 1);
/*      */       }
/*  691 */       return null;
/*      */     } 
/*      */     
/*      */     try {
/*  695 */       if (!propertyExists(name)) {
/*  696 */         if (Trace.isOn) {
/*  697 */           Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getProperty(String)", null, 2);
/*      */         }
/*  699 */         return null;
/*      */       } 
/*      */       
/*  702 */       String value = getStringProperty(name);
/*  703 */       if (Trace.isOn) {
/*  704 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getProperty(String)", value, 3);
/*      */       }
/*  706 */       return value;
/*      */     }
/*  708 */     catch (JMSException je) {
/*  709 */       if (Trace.isOn) {
/*  710 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "getProperty(String)", (Throwable)je);
/*      */       }
/*  712 */       if (Trace.isOn) {
/*  713 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getProperty(String)", null, 4);
/*      */       }
/*  715 */       return null;
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
/*      */   public int getPutAsyncAllowed() throws JMSException {
/*  739 */     int traceRet1 = this.propertyDelegate.getIntProperty("putAsyncAllowed");
/*  740 */     if (Trace.isOn) {
/*  741 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "getPutAsyncAllowed()", "getter", 
/*  742 */           Integer.valueOf(traceRet1));
/*      */     }
/*  744 */     return traceRet1;
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
/*      */   public int getReadAheadAllowed() throws JMSException {
/*  766 */     int traceRet1 = this.propertyDelegate.getIntProperty("readAheadAllowed");
/*  767 */     if (Trace.isOn) {
/*  768 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "getReadAheadAllowed()", "getter", 
/*  769 */           Integer.valueOf(traceRet1));
/*      */     }
/*  771 */     return traceRet1;
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
/*      */   public int getReadAheadClosePolicy() throws JMSException {
/*  789 */     int traceRet1 = this.propertyDelegate.getIntProperty("readAheadClosePolicy");
/*  790 */     if (Trace.isOn) {
/*  791 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "getReadAheadClosePolicy()", "getter", 
/*  792 */           Integer.valueOf(traceRet1));
/*      */     }
/*  794 */     return traceRet1;
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
/*      */   public Reference getReference() throws NamingException {
/*  808 */     Reference ref = new Reference(MQDestination.class.getName(), null, null);
/*      */     try {
/*  810 */       ref.add(new StringRefAddr("RTOST", String.valueOf(getReplyToStyle())));
/*  811 */       ref.add(new StringRefAddr("RCCS", String.valueOf(getReceiveCCSID())));
/*  812 */       ref.add(new StringRefAddr("RCNV", String.valueOf(getReceiveConversion())));
/*      */     }
/*  814 */     catch (JMSException e) {
/*  815 */       if (Trace.isOn) {
/*  816 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "getReference()", (Throwable)e);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  821 */     if (Trace.isOn) {
/*  822 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "getReference()", "getter", ref);
/*      */     }
/*  824 */     return ref;
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
/*      */   public String getStringFromDestination() {
/*  836 */     String traceRet1 = toURI();
/*  837 */     if (Trace.isOn) {
/*  838 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "getStringFromDestination()", "getter", traceRet1);
/*      */     }
/*      */     
/*  841 */     return traceRet1;
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
/*      */   public int getTargetClient() {
/*  856 */     if (Trace.isOn) {
/*  857 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "getTargetClient()");
/*      */     }
/*      */     
/*      */     try {
/*  861 */       int traceRet1 = this.propertyDelegate.getIntProperty("targetClient");
/*  862 */       if (Trace.isOn) {
/*  863 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getTargetClient()", 
/*  864 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/*  866 */       return traceRet1;
/*      */     }
/*  868 */     catch (JMSException je) {
/*  869 */       if (Trace.isOn) {
/*  870 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "getTargetClient()", (Throwable)je);
/*      */       }
/*  872 */       if (Trace.isOn) {
/*  873 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getTargetClient()", Integer.valueOf(0), 2);
/*      */       }
/*      */       
/*  876 */       return 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/*  885 */     if (Trace.isOn) {
/*  886 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "getVersion()");
/*      */     }
/*      */     
/*      */     try {
/*  890 */       int traceRet1 = this.propertyDelegate.getIntProperty("version");
/*  891 */       if (Trace.isOn) {
/*  892 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getVersion()", 
/*  893 */             Integer.valueOf(traceRet1), 1);
/*      */       }
/*  895 */       return traceRet1;
/*      */     }
/*  897 */     catch (JMSException je) {
/*  898 */       if (Trace.isOn) {
/*  899 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "getVersion()", (Throwable)je);
/*      */       }
/*  901 */       if (Trace.isOn) {
/*  902 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "getVersion()", Integer.valueOf(0), 2);
/*      */       }
/*  904 */       return 0;
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
/*      */   public int getWildcardFormat() throws JMSException {
/*  919 */     int traceRet1 = this.propertyDelegate.getIntProperty("wildcardFormat");
/*  920 */     if (Trace.isOn) {
/*  921 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "getWildcardFormat()", "getter", 
/*  922 */           Integer.valueOf(traceRet1));
/*      */     }
/*  924 */     return traceRet1;
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  928 */     if (Trace.isOn) {
/*  929 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  936 */     this.propertyDelegate = (JmsDestination)this;
/*  937 */     this.versionChangeAllowed = false;
/*  938 */     connectionType = "com.ibm.msg.client.wmq";
/*      */     
/*  940 */     setConnectionTypeName(connectionType);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  947 */       if (getProviderDestination() == null) {
/*  948 */         setDefaultProperties();
/*      */       }
/*      */     }
/*  951 */     catch (JMSException e) {
/*  952 */       if (Trace.isOn) {
/*  953 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "readObject(java.io.ObjectInputStream)", (Throwable)e, 1);
/*      */       }
/*      */ 
/*      */       
/*  957 */       HashMap<String, JMSException> info = new HashMap<>();
/*  958 */       info.put("exception", e);
/*  959 */       Trace.ffst(this, "readObject(ObjectInputStream)", "XF008002", info, JMSException.class);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  968 */     ObjectInputStream.GetField fields = in.readFields();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  984 */       if (!fields.defaulted("alternateUserId")) {
/*  985 */         setAlternateUserId((String)fields.get("alternateUserId", (Object)null));
/*      */       }
/*  987 */       if (!fields.defaulted("CCSID")) {
/*  988 */         setCCSID(fields.get("CCSID", 0));
/*      */       }
/*  990 */       if (!fields.defaulted("receiveCCSID")) {
/*  991 */         setReceiveCCSID(fields.get("receiveCCSID", 0));
/*      */       }
/*  993 */       if (!fields.defaulted("receiveConversion")) {
/*  994 */         setReceiveConversion(fields.get("receiveConversion", 0));
/*      */       }
/*  996 */       if (!fields.defaulted("description")) {
/*  997 */         setDescription((String)fields.get("description", (Object)null));
/*      */       }
/*  999 */       if (!fields.defaulted("encoding")) {
/* 1000 */         setEncoding(fields.get("encoding", 0));
/*      */       }
/* 1002 */       if (!fields.defaulted("expiry")) {
/* 1003 */         setExpiry(fields.get("expiry", 0L));
/*      */       }
/* 1005 */       if (!fields.defaulted("failIfQuiesce")) {
/* 1006 */         setFailIfQuiesce(fields.get("failIfQuiesce", 0));
/*      */       }
/* 1008 */       if (!fields.defaulted("messageBodyStyle")) {
/* 1009 */         setMessageBodyStyle(fields.get("messageBodyStyle", 0));
/*      */       }
/* 1011 */       if (!fields.defaulted("MQMDMessageContext")) {
/* 1012 */         setMQMDMessageContext(fields.get("MQMDMessageContext", 0));
/*      */       }
/* 1014 */       if (!fields.defaulted("MQMDReadEnabled")) {
/* 1015 */         setMQMDReadEnabled(fields.get("MQMDReadEnabled", false));
/*      */       }
/* 1017 */       if (!fields.defaulted("MQMDWriteEnabled")) {
/* 1018 */         setMQMDWriteEnabled(fields.get("MQMDWriteEnabled", false));
/*      */       }
/* 1020 */       if (!fields.defaulted("persistence")) {
/* 1021 */         setPersistence(fields.get("persistence", 0));
/*      */       }
/* 1023 */       if (!fields.defaulted("priority")) {
/* 1024 */         setPriority(fields.get("priority", 0));
/*      */       }
/* 1026 */       if (!fields.defaulted("putAsyncAllowed")) {
/* 1027 */         setPutAsyncAllowed(fields.get("putAsyncAllowed", 0));
/*      */       }
/* 1029 */       if (!fields.defaulted("readAheadAllowed")) {
/* 1030 */         setReadAheadAllowed(fields.get("readAheadAllowed", 0));
/*      */       }
/* 1032 */       if (!fields.defaulted("readAheadClosePolicy")) {
/* 1033 */         setReadAheadClosePolicy(fields.get("readAheadClosePolicy", 0));
/*      */       }
/* 1035 */       if (!fields.defaulted("targetClient")) {
/* 1036 */         setTargetClient(fields.get("targetClient", 0));
/*      */       }
/* 1038 */       if (!fields.defaulted("version")) {
/*      */         
/* 1040 */         this.versionChangeAllowed = true;
/* 1041 */         setVersion(fields.get("version", 0));
/* 1042 */         this.versionChangeAllowed = false;
/*      */       } 
/* 1044 */       if (!fields.defaulted("wildcardFormat")) {
/* 1045 */         setWildcardFormat(fields.get("wildcardFormat", 0));
/*      */       }
/* 1047 */       if (!fields.defaulted("replyToStyle")) {
/* 1048 */         setReplyToStyle(fields.get("replyToStyle", 0));
/*      */       }
/*      */       
/* 1051 */       if (!fields.defaulted("unmappableAction")) {
/* 1052 */         setUnmappableAction((String)fields.get("unmappableAction", CodingErrorAction.REPORT));
/*      */       }
/* 1054 */       if (!fields.defaulted("unmappableReplacement")) {
/* 1055 */         setUnmappableReplacement(Byte.valueOf(fields.get("unmappableReplacement", (byte)63)));
/*      */       
/*      */       }
/*      */     }
/* 1059 */     catch (JMSException je) {
/* 1060 */       if (Trace.isOn) {
/* 1061 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "readObject(java.io.ObjectInputStream)", (Throwable)je, 2);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1068 */     if (Trace.isOn) {
/* 1069 */       Trace.exit(this, "com.ibm.mq.jms.MQDestination", "readObject(java.io.ObjectInputStream)");
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
/*      */   public void setAlternateUserId(String altuser) throws JMSException {
/* 1081 */     if (Trace.isOn) {
/* 1082 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setAlternateUserId(String)", "setter", altuser);
/*      */     }
/*      */     
/* 1085 */     this.propertyDelegate.setStringProperty("alternateUserId", altuser);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCCSID(int ccsid) throws JMSException {
/* 1096 */     if (Trace.isOn) {
/* 1097 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setCCSID(int)", "setter", 
/* 1098 */           Integer.valueOf(ccsid));
/*      */     }
/* 1100 */     this.propertyDelegate.setIntProperty("CCSID", ccsid);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUnmappableAction(String unmappableAction) throws JMSException {
/* 1111 */     if (Trace.isOn) {
/* 1112 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setUnmappableAction(String)", "setter", unmappableAction);
/*      */     }
/*      */     
/* 1115 */     this.propertyDelegate.setStringProperty("JMS_IBM_Unmappable_Action", unmappableAction);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUnmappableReplacement(Byte unmappableReplacement) throws JMSException {
/* 1126 */     if (Trace.isOn) {
/* 1127 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setUnmappableReplacement(Byte)", "setter", unmappableReplacement);
/*      */     }
/*      */     
/* 1130 */     this.propertyDelegate.setObjectProperty("JMS_IBM_Unmappable_Replacement", unmappableReplacement);
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
/*      */   public void setReceiveCCSID(int ccsid) throws JMSException {
/* 1148 */     if (Trace.isOn) {
/* 1149 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setReceiveCCSID(int)", "setter", 
/* 1150 */           Integer.valueOf(ccsid));
/*      */     }
/* 1152 */     this.propertyDelegate.setIntProperty("receiveCCSID", ccsid);
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
/*      */   public void setReceiveConversion(int rcnvc) throws JMSException {
/* 1171 */     if (Trace.isOn) {
/* 1172 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setReceiveConversion(int)", "setter", 
/* 1173 */           Integer.valueOf(rcnvc));
/*      */     }
/* 1175 */     this.propertyDelegate.setIntProperty("receiveConversion", rcnvc);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setCommonFromReference(Reference ref) throws JMSException {
/* 1184 */     if (Trace.isOn) {
/* 1185 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setCommonFromReference(Reference)", "setter", ref);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1191 */     RefAddr addr = ref.get("ALTU");
/* 1192 */     if (addr != null) {
/* 1193 */       Object value = addr.getContent();
/* 1194 */       if (value != null) {
/* 1195 */         setAlternateUserId((String)value);
/*      */       }
/*      */     } 
/* 1198 */     addr = ref.get("VER");
/* 1199 */     if (addr != null) {
/* 1200 */       Object value = addr.getContent();
/* 1201 */       if (value != null) {
/*      */ 
/*      */         
/* 1204 */         this.versionChangeAllowed = true;
/* 1205 */         int storedVersion = Integer.parseInt((String)value);
/* 1206 */         setVersion(storedVersion);
/* 1207 */         this.versionChangeAllowed = false;
/*      */       } 
/*      */     } 
/* 1210 */     addr = ref.get("DESC");
/* 1211 */     if (addr != null) {
/* 1212 */       Object value = addr.getContent();
/* 1213 */       if (value != null) {
/* 1214 */         setDescription((String)value);
/*      */       }
/*      */     } 
/* 1217 */     addr = ref.get("CCS");
/* 1218 */     if (addr != null) {
/* 1219 */       Object value = addr.getContent();
/* 1220 */       if (value != null) {
/* 1221 */         setCCSID(Integer.parseInt((String)value));
/*      */       }
/*      */     } 
/* 1224 */     addr = ref.get("RCCS");
/* 1225 */     if (addr != null) {
/* 1226 */       Object value = addr.getContent();
/* 1227 */       if (value != null) {
/* 1228 */         setReceiveCCSID(Integer.parseInt((String)value));
/*      */       }
/*      */     } 
/* 1231 */     addr = ref.get("RCNV");
/* 1232 */     if (addr != null) {
/* 1233 */       Object value = addr.getContent();
/* 1234 */       if (value != null) {
/* 1235 */         setReceiveConversion(Integer.parseInt((String)value));
/*      */       }
/*      */     } 
/* 1238 */     addr = ref.get("EXP");
/* 1239 */     if (addr != null) {
/* 1240 */       Object value = addr.getContent();
/* 1241 */       if (value != null) {
/* 1242 */         setExpiry(Long.parseLong((String)value));
/*      */       }
/*      */     } 
/* 1245 */     addr = ref.get("PRI");
/* 1246 */     if (addr != null) {
/* 1247 */       Object value = addr.getContent();
/* 1248 */       if (value != null) {
/* 1249 */         setPriority(Integer.parseInt((String)value));
/*      */       }
/*      */     } 
/* 1252 */     addr = ref.get("PER");
/* 1253 */     if (addr != null) {
/* 1254 */       Object value = addr.getContent();
/* 1255 */       if (value != null) {
/* 1256 */         setPersistence(Integer.parseInt((String)value));
/*      */       }
/*      */     } 
/* 1259 */     addr = ref.get("TC");
/* 1260 */     if (addr != null) {
/* 1261 */       Object value = addr.getContent();
/* 1262 */       if (value != null) {
/* 1263 */         setTargetClient(Integer.parseInt((String)value));
/*      */       }
/*      */     } 
/* 1266 */     addr = ref.get("ENC");
/* 1267 */     if (addr != null) {
/* 1268 */       Object value = addr.getContent();
/* 1269 */       if (value != null) {
/* 1270 */         setEncoding(Integer.parseInt((String)value));
/*      */       }
/*      */     } 
/* 1273 */     addr = ref.get("FIQ");
/* 1274 */     if (addr != null) {
/* 1275 */       Object value = addr.getContent();
/* 1276 */       if (value != null) {
/* 1277 */         setFailIfQuiesce(Integer.parseInt((String)value));
/*      */       }
/*      */     } 
/* 1280 */     addr = ref.get("WCFMT");
/* 1281 */     if (addr != null) {
/* 1282 */       Object value = addr.getContent();
/* 1283 */       if (value != null) {
/* 1284 */         setWildcardFormat(Integer.parseInt((String)value));
/*      */       }
/*      */     } 
/* 1287 */     addr = ref.get("RACP");
/* 1288 */     if (addr != null) {
/* 1289 */       Object value = addr.getContent();
/* 1290 */       if (value != null) {
/* 1291 */         setReadAheadClosePolicy(Integer.parseInt((String)value));
/*      */       }
/*      */     } 
/* 1294 */     addr = ref.get("RAALD");
/* 1295 */     if (addr != null) {
/* 1296 */       Object value = addr.getContent();
/* 1297 */       if (value != null) {
/* 1298 */         setReadAheadAllowed(Integer.parseInt((String)value));
/*      */       }
/*      */     } 
/* 1301 */     addr = ref.get("PAALD");
/* 1302 */     if (addr != null) {
/* 1303 */       Object value = addr.getContent();
/* 1304 */       if (value != null) {
/* 1305 */         setPutAsyncAllowed(Integer.parseInt((String)value));
/*      */       }
/*      */     } 
/* 1308 */     addr = ref.get("MBODY");
/* 1309 */     if (addr != null) {
/* 1310 */       Object value = addr.getContent();
/* 1311 */       if (value != null) {
/* 1312 */         setMessageBodyStyle(Integer.parseInt((String)value));
/*      */       }
/*      */     } 
/* 1315 */     addr = ref.get("MDR");
/* 1316 */     if (addr != null) {
/* 1317 */       Object value = addr.getContent();
/* 1318 */       if (value != null) {
/* 1319 */         setMQMDReadEnabled(Boolean.valueOf((String)value).booleanValue());
/*      */       }
/*      */     } 
/* 1322 */     addr = ref.get("MDW");
/* 1323 */     if (addr != null) {
/* 1324 */       Object value = addr.getContent();
/* 1325 */       if (value != null) {
/* 1326 */         setMQMDWriteEnabled(Boolean.valueOf((String)value).booleanValue());
/*      */       }
/*      */     } 
/* 1329 */     addr = ref.get("MDCTX");
/* 1330 */     if (addr != null) {
/* 1331 */       Object value = addr.getContent();
/* 1332 */       if (value != null) {
/* 1333 */         setMQMDMessageContext(Integer.parseInt((String)value));
/*      */       }
/*      */     } 
/*      */     
/* 1337 */     addr = ref.get("RTOST");
/* 1338 */     if (addr != null) {
/* 1339 */       Object value = addr.getContent();
/* 1340 */       if (value != null) {
/* 1341 */         setReplyToStyle(Integer.parseInt((String)value));
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1346 */     addr = ref.get("UMA");
/* 1347 */     if (addr != null) {
/* 1348 */       Object value = addr.getContent();
/* 1349 */       if (value != null) {
/* 1350 */         setUnmappableAction((String)value);
/*      */       }
/*      */     } 
/*      */     
/* 1354 */     addr = ref.get("UMR");
/* 1355 */     if (addr != null) {
/* 1356 */       Object value = addr.getContent();
/* 1357 */       if (value != null) {
/* 1358 */         setUnmappableReplacement(Byte.valueOf(Byte.parseByte((String)value)));
/*      */       }
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
/*      */   public void setDescription(String description) {
/* 1371 */     if (Trace.isOn) {
/* 1372 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setDescription(String)", "setter", description);
/*      */     }
/*      */     
/*      */     try {
/* 1376 */       this.propertyDelegate.setStringProperty("destDescription", description);
/*      */     }
/* 1378 */     catch (JMSException je) {
/* 1379 */       if (Trace.isOn) {
/* 1380 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "setDescription(String)", (Throwable)je);
/*      */       }
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
/*      */   public void setEncoding(int encoding) throws JMSException {
/* 1394 */     if (Trace.isOn) {
/* 1395 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setEncoding(int)", "setter", 
/* 1396 */           Integer.valueOf(encoding));
/*      */     }
/* 1398 */     this.propertyDelegate.setIntProperty("encoding", encoding);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExpiry(long expiry) throws JMSException {
/* 1409 */     if (Trace.isOn) {
/* 1410 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setExpiry(long)", "setter", 
/* 1411 */           Long.valueOf(expiry));
/*      */     }
/* 1413 */     this.propertyDelegate.setLongProperty("timeToLive", expiry);
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
/*      */   public void setFailIfQuiesce(int fiq) throws JMSException {
/* 1428 */     if (Trace.isOn) {
/* 1429 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setFailIfQuiesce(int)", "setter", 
/* 1430 */           Integer.valueOf(fiq));
/*      */     }
/* 1432 */     this.propertyDelegate.setIntProperty("failIfQuiesce", fiq);
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
/*      */   public void setMQMDMessageContext(int messageContext) throws JMSException {
/* 1450 */     if (Trace.isOn) {
/* 1451 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setMQMDMessageContext(int)", "setter", 
/* 1452 */           Integer.valueOf(messageContext));
/*      */     }
/* 1454 */     this.propertyDelegate.setIntProperty("mdMessageContext", messageContext);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMQMDWriteEnabled(boolean writeEnabled) throws JMSException {
/* 1465 */     if (Trace.isOn) {
/* 1466 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setMQMDWriteEnabled(boolean)", "setter", 
/* 1467 */           Boolean.valueOf(writeEnabled));
/*      */     }
/* 1469 */     this.propertyDelegate.setBooleanProperty("mdWriteEnabled", writeEnabled);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMQMDReadEnabled(boolean readEnabled) throws JMSException {
/* 1480 */     if (Trace.isOn) {
/* 1481 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setMQMDReadEnabled(boolean)", "setter", 
/* 1482 */           Boolean.valueOf(readEnabled));
/*      */     }
/* 1484 */     this.propertyDelegate.setBooleanProperty("mdReadEnabled", readEnabled);
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
/*      */   public void setMessageBodyStyle(int bodyStyle) throws JMSException {
/* 1501 */     if (Trace.isOn) {
/* 1502 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setMessageBodyStyle(int)", "setter", 
/* 1503 */           Integer.valueOf(bodyStyle));
/*      */     }
/* 1505 */     this.propertyDelegate.setIntProperty("messageBody", bodyStyle);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPersistence(int persistence) throws JMSException {
/* 1515 */     if (Trace.isOn) {
/* 1516 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setPersistence(int)", "setter", 
/* 1517 */           Integer.valueOf(persistence));
/*      */     }
/* 1519 */     this.propertyDelegate.setIntProperty("deliveryMode", persistence);
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
/*      */   public void setPriority(int priority) throws JMSException {
/* 1536 */     if (Trace.isOn) {
/* 1537 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setPriority(int)", "setter", 
/* 1538 */           Integer.valueOf(priority));
/*      */     }
/* 1540 */     this.propertyDelegate.setIntProperty("priority", priority);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProperty(String name, String value) {
/* 1583 */     if (Trace.isOn) {
/* 1584 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "setProperty(String,String)", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1590 */       if (name != null && value != null) {
/* 1591 */         setStringProperty(name, value);
/*      */       }
/*      */     }
/* 1594 */     catch (JMSException je) {
/* 1595 */       if (Trace.isOn) {
/* 1596 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "setProperty(String,String)", (Throwable)je);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1601 */     if (Trace.isOn) {
/* 1602 */       Trace.exit(this, "com.ibm.mq.jms.MQDestination", "setProperty(String,String)");
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
/*      */   public void setPutAsyncAllowed(int paa) throws JMSException {
/* 1629 */     if (Trace.isOn) {
/* 1630 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setPutAsyncAllowed(int)", "setter", 
/* 1631 */           Integer.valueOf(paa));
/*      */     }
/* 1633 */     this.propertyDelegate.setIntProperty("putAsyncAllowed", paa);
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
/*      */   public void setReadAheadAllowed(int raa) throws JMSException {
/* 1658 */     if (Trace.isOn) {
/* 1659 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setReadAheadAllowed(int)", "setter", 
/* 1660 */           Integer.valueOf(raa));
/*      */     }
/* 1662 */     this.propertyDelegate.setIntProperty("readAheadAllowed", raa);
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
/*      */   public void setReadAheadClosePolicy(int racp) throws JMSException {
/* 1682 */     if (Trace.isOn) {
/* 1683 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setReadAheadClosePolicy(int)", "setter", 
/* 1684 */           Integer.valueOf(racp));
/*      */     }
/* 1686 */     this.propertyDelegate.setIntProperty("readAheadClosePolicy", racp);
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
/*      */   public void setTargetClient(int targetClient) throws JMSException {
/* 1702 */     if (Trace.isOn) {
/* 1703 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setTargetClient(int)", "setter", 
/* 1704 */           Integer.valueOf(targetClient));
/*      */     }
/* 1706 */     this.propertyDelegate.setIntProperty("targetClient", targetClient);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVersion(int version) throws JMSException {
/* 1716 */     if (Trace.isOn) {
/* 1717 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setVersion(int)", "setter", 
/* 1718 */           Integer.valueOf(version));
/*      */     }
/*      */     
/* 1721 */     if (!this.versionChangeAllowed) {
/* 1722 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ0012", null);
/* 1723 */       if (Trace.isOn) {
/* 1724 */         Trace.throwing(this, "com.ibm.mq.jms.MQDestination", "setVersion(int)", (Throwable)je);
/*      */       }
/* 1726 */       throw je;
/*      */     } 
/* 1728 */     this.propertyDelegate.setIntProperty("version", version);
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
/*      */   public void setWildcardFormat(int format) throws JMSException {
/* 1743 */     if (Trace.isOn) {
/* 1744 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setWildcardFormat(int)", "setter", 
/* 1745 */           Integer.valueOf(format));
/*      */     }
/* 1747 */     this.propertyDelegate.setIntProperty("wildcardFormat", format);
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
/*      */   public void setReplyToStyle(int style) throws JMSException {
/* 1769 */     if (Trace.isOn) {
/* 1770 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "setReplyToStyle(int)", "setter", 
/* 1771 */           Integer.valueOf(style));
/*      */     }
/* 1773 */     this.propertyDelegate.setIntProperty("XMSC_WMQ_REPLYTO_STYLE", style);
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
/*      */   public int getReplyToStyle() throws JMSException {
/* 1795 */     int traceRet1 = this.propertyDelegate.getIntProperty("XMSC_WMQ_REPLYTO_STYLE");
/* 1796 */     if (Trace.isOn) {
/* 1797 */       Trace.data(this, "com.ibm.mq.jms.MQDestination", "getReplyToStyle()", "getter", 
/* 1798 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1800 */     return traceRet1;
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
/*      */   protected Destination validateDestination() throws JMSException {
/* 1812 */     if (Trace.isOn) {
/* 1813 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "validateDestination()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1821 */     String name = getName();
/* 1822 */     if (name == null || name.equals("")) {
/* 1823 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1113", null);
/* 1824 */       if (Trace.isOn) {
/* 1825 */         Trace.throwing(this, "com.ibm.mq.jms.MQDestination", "validateDestination()", (Throwable)je);
/*      */       }
/* 1827 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1832 */     if (this instanceof MQTemporaryQueue) {
/* 1833 */       TemporaryQueue temporaryQueue = ((MQTemporaryQueue)this).getCommonTemporaryQueue();
/* 1834 */       if (Trace.isOn) {
/* 1835 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "validateDestination()", temporaryQueue, 1);
/*      */       }
/* 1837 */       return (Destination)temporaryQueue;
/*      */     } 
/* 1839 */     if (this instanceof MQTemporaryTopic) {
/* 1840 */       TemporaryTopic temporaryTopic = ((MQTemporaryTopic)this).getCommonTemporaryTopic();
/* 1841 */       if (Trace.isOn) {
/* 1842 */         Trace.exit(this, "com.ibm.mq.jms.MQDestination", "validateDestination()", temporaryTopic, 2);
/*      */       }
/* 1844 */       return (Destination)temporaryTopic;
/*      */     } 
/*      */     
/* 1847 */     if (Trace.isOn) {
/* 1848 */       Trace.exit(this, "com.ibm.mq.jms.MQDestination", "validateDestination()", this, 3);
/*      */     }
/* 1850 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 1856 */     if (Trace.isOn) {
/* 1857 */       Trace.entry(this, "com.ibm.mq.jms.MQDestination", "writeObject(java.io.ObjectOutputStream)", new Object[] { out });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1865 */     ObjectOutputStream.PutField fields = out.putFields();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1871 */       fields.put("alternateUserId", getAlternateUserId());
/* 1872 */       fields.put("CCSID", getCCSID());
/* 1873 */       fields.put("receiveCCSID", getReceiveCCSID());
/* 1874 */       fields.put("receiveConversion", getReceiveConversion());
/* 1875 */       fields.put("description", getDescription());
/* 1876 */       fields.put("encoding", getEncoding());
/* 1877 */       fields.put("expiry", getExpiry());
/* 1878 */       fields.put("failIfQuiesce", getFailIfQuiesce());
/* 1879 */       fields.put("messageBodyStyle", getMessageBodyStyle());
/* 1880 */       fields.put("MQMDMessageContext", getMQMDMessageContext());
/* 1881 */       fields.put("MQMDReadEnabled", getMQMDReadEnabled());
/* 1882 */       fields.put("MQMDWriteEnabled", getMQMDWriteEnabled());
/* 1883 */       fields.put("persistence", getPersistence());
/* 1884 */       fields.put("priority", getPriority());
/* 1885 */       fields.put("putAsyncAllowed", getPutAsyncAllowed());
/* 1886 */       fields.put("readAheadAllowed", getReadAheadAllowed());
/* 1887 */       fields.put("readAheadClosePolicy", getReadAheadClosePolicy());
/* 1888 */       fields.put("targetClient", getTargetClient());
/* 1889 */       fields.put("version", getVersion());
/* 1890 */       fields.put("wildcardFormat", getWildcardFormat());
/* 1891 */       fields.put("replyToStyle", getReplyToStyle());
/* 1892 */       fields.put("unmappableAction", getUnmappableAction());
/* 1893 */       fields.put("unmappableReplacement", getUnmappableReplacement());
/*      */     }
/* 1895 */     catch (JMSException je) {
/* 1896 */       if (Trace.isOn) {
/* 1897 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQDestination", "writeObject(java.io.ObjectOutputStream)", (Throwable)je);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1906 */     out.writeFields();
/*      */     
/* 1908 */     if (Trace.isOn) {
/* 1909 */       Trace.exit(this, "com.ibm.mq.jms.MQDestination", "writeObject(java.io.ObjectOutputStream)");
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
/*      */   protected static Destination proxyValidateDestination(Destination dest) throws JMSException {
/* 1926 */     if (Trace.isOn) {
/* 1927 */       Trace.entry("com.ibm.mq.jms.MQDestination", "proxyValidateDestination(Destination)", new Object[] { dest });
/*      */     }
/*      */     
/* 1930 */     if (dest instanceof MQDestination) {
/* 1931 */       Destination traceRet1 = ((MQDestination)dest).validateDestination();
/* 1932 */       if (Trace.isOn) {
/* 1933 */         Trace.exit("com.ibm.mq.jms.MQDestination", "proxyValidateDestination(Destination)", traceRet1, 1);
/*      */       }
/*      */       
/* 1936 */       return traceRet1;
/*      */     } 
/* 1938 */     if (Trace.isOn) {
/* 1939 */       Trace.exit("com.ibm.mq.jms.MQDestination", "proxyValidateDestination(Destination)", dest, 2);
/*      */     }
/*      */     
/* 1942 */     return dest;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static MQTemporaryTopic proxyCreateTemporaryTopic(TemporaryTopic tempTopic) throws JMSException {
/* 1949 */     if (Trace.isOn) {
/* 1950 */       Trace.entry("com.ibm.mq.jms.MQDestination", "proxyCreateTemporaryTopic(TemporaryTopic)", new Object[] { tempTopic });
/*      */     }
/*      */     
/* 1953 */     MQTemporaryTopic traceRet1 = new MQTemporaryTopic(tempTopic);
/* 1954 */     if (Trace.isOn) {
/* 1955 */       Trace.exit("com.ibm.mq.jms.MQDestination", "proxyCreateTemporaryTopic(TemporaryTopic)", traceRet1);
/*      */     }
/*      */     
/* 1958 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static MQTemporaryQueue proxyCreateTemporaryQueue(TemporaryQueue tempQueue) throws JMSException {
/* 1965 */     if (Trace.isOn) {
/* 1966 */       Trace.entry("com.ibm.mq.jms.MQDestination", "proxyCreateTemporaryQueue(TemporaryQueue)", new Object[] { tempQueue });
/*      */     }
/*      */     
/* 1969 */     MQTemporaryQueue traceRet1 = new MQTemporaryQueue(tempQueue);
/* 1970 */     if (Trace.isOn) {
/* 1971 */       Trace.exit("com.ibm.mq.jms.MQDestination", "proxyCreateTemporaryQueue(TemporaryQueue)", traceRet1);
/*      */     }
/*      */     
/* 1974 */     return traceRet1;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQDestination.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */