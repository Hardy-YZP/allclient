/*      */ package com.ibm.msg.client.jms.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.cssystem.WASSupport;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.DetailedJMSException;
/*      */ import com.ibm.msg.client.jms.JmsProducer;
/*      */ import com.ibm.msg.client.jms.admin.JmsDestinationImpl;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.Serializable;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.atomic.AtomicBoolean;
/*      */ import javax.jms.BytesMessage;
/*      */ import javax.jms.CompletionListener;
/*      */ import javax.jms.Destination;
/*      */ import javax.jms.InvalidDestinationRuntimeException;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.JMSProducer;
/*      */ import javax.jms.JMSRuntimeException;
/*      */ import javax.jms.MapMessage;
/*      */ import javax.jms.Message;
/*      */ import javax.jms.MessageFormatRuntimeException;
/*      */ import javax.jms.MessageNotWriteableRuntimeException;
/*      */ import javax.jms.ObjectMessage;
/*      */ import javax.jms.Session;
/*      */ import javax.jms.TextMessage;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JmsProducerImpl
/*      */   implements JmsProducer, JmsCloseableResource
/*      */ {
/*      */   static {
/*   65 */     if (Trace.isOn) {
/*   66 */       Trace.data("com.ibm.msg.client.jms.internal.JmsProducerImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsProducerImpl.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*   72 */   private JmsSessionImpl session = null;
/*   73 */   private JmsMessageProducerImpl producer = null;
/*   74 */   private JmsContextImpl context = null;
/*      */   
/*   76 */   private JmsPropertyContextImpl deliveryProperties = null;
/*      */ 
/*      */   
/*      */   private static final int defaultDeliveryMode = 2;
/*      */ 
/*      */   
/*      */   private static final int defaultPriority = 4;
/*      */ 
/*      */   
/*      */   private static final long defaultTimeToLive = 0L;
/*      */ 
/*      */   
/*      */   private static final long defaultDeliveryDelay = 0L;
/*      */   
/*      */   private static final boolean defaultDisableMessageTimestamp = false;
/*      */   
/*      */   private static final boolean defaultDisableMessageID = false;
/*      */   
/*   94 */   private JmsMessageImpl propertiesMessage = null;
/*   95 */   private AtomicBoolean propsSet = new AtomicBoolean(false);
/*      */   
/*   97 */   private CompletionListener completionListener = null;
/*      */ 
/*      */   
/*      */   private final boolean isCICSUnmanaged;
/*      */ 
/*      */   
/*      */   private final boolean isIMS;
/*      */   
/*      */   private final boolean isWAS;
/*      */ 
/*      */   
/*      */   JmsProducerImpl(JmsContextImpl context, Session session, JmsMessageProducerImpl producer) throws JMSException {
/*  109 */     if (Trace.isOn) {
/*  110 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "<init>(JmsContextImpl,Session,JmsMessageProducerImpl)", new Object[] { context, session, producer });
/*      */     }
/*      */ 
/*      */     
/*  114 */     this.session = (JmsSessionImpl)session;
/*  115 */     this.producer = producer;
/*  116 */     this.context = context;
/*      */ 
/*      */     
/*  119 */     this.propertiesMessage = new JmsMessageImpl(this.session);
/*      */     
/*  121 */     JmsConnectionMetaDataImpl md = (JmsConnectionMetaDataImpl)context.getMetaData();
/*  122 */     this.isCICSUnmanaged = md.doesConnectionSupport("XMSC_CAPABILITY_NATIVE_CICS_UNMANAGED");
/*  123 */     this.isIMS = md.doesConnectionSupport("XMSC_CAPABILITY_NATIVE_IMS");
/*      */ 
/*      */     
/*  126 */     WASSupport.WASRuntimeHelper helper = (WASSupport.WASRuntimeHelper)PropertyStore.getObjectProperty("com.ibm.mq.connector.JCARuntimeHelper");
/*  127 */     if (Trace.isOn) {
/*  128 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "<init>(JmsContextImpl,Session,JmsMessageProducerImpl)", "Got runtime helper object:" + helper);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  134 */     this.isWAS = (helper != null && helper.getEnvironment() != 0);
/*      */ 
/*      */     
/*  137 */     this.deliveryProperties = new JmsProducerDeliveryProperties(context);
/*  138 */     this.deliveryProperties.setIntProperty("deliveryMode", 2);
/*  139 */     this.deliveryProperties.setIntProperty("priority", 4);
/*  140 */     this.deliveryProperties.setLongProperty("timeToLive", 0L);
/*  141 */     this.deliveryProperties.setLongProperty("deliveryDelay", 0L);
/*  142 */     this.deliveryProperties.setBooleanProperty("XMSC_DISABLE_MSG_ID", false);
/*  143 */     this.deliveryProperties.setBooleanProperty("XMSC_DISABLE_MSG_TS", false);
/*      */     
/*  145 */     if (Trace.isOn) {
/*  146 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "<init>(JmsContextImpl,Session,JmsMessageProducerImpl)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() {
/*  154 */     if (Trace.isOn) {
/*  155 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "close()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  160 */     this.context.removeCloseable(this);
/*      */     
/*  162 */     if (Trace.isOn) {
/*  163 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "close()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSProducer clearProperties() throws JMSRuntimeException {
/*  170 */     if (Trace.isOn) {
/*  171 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "clearProperties()");
/*      */     }
/*      */     try {
/*  174 */       this.propertiesMessage.clearProperties();
/*  175 */       this.propsSet.set(false);
/*      */     }
/*  177 */     catch (JMSException je) {
/*  178 */       if (Trace.isOn) {
/*  179 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "clearProperties()", (Throwable)je);
/*      */       }
/*      */       
/*  182 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  183 */       if (Trace.isOn) {
/*  184 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "clearProperties()", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  187 */       throw traceRet1;
/*      */     } 
/*  189 */     if (Trace.isOn) {
/*  190 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "clearProperties()", this);
/*      */     }
/*      */     
/*  193 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public CompletionListener getAsync() throws JMSRuntimeException {
/*  199 */     this.context.checkValidFnUsage(JmsConnectionImpl.JMS2Function.ASYNCHRONOUS_SEND);
/*      */     
/*  201 */     if (Trace.isOn) {
/*  202 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getAsync()", "getter", this.completionListener);
/*      */     }
/*      */     
/*  205 */     return this.completionListener;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getDeliveryDelay() throws JMSRuntimeException {
/*  210 */     if (Trace.isOn) {
/*  211 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDeliveryDelay()");
/*      */     }
/*      */     
/*      */     try {
/*  215 */       this.context.checkValidFnUsage(JmsConnectionImpl.JMS2Function.DELAYED_DELIVERY);
/*      */       
/*  217 */       long traceRet1 = this.deliveryProperties.getLongProperty("deliveryDelay");
/*  218 */       if (Trace.isOn) {
/*  219 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDeliveryDelay()", 
/*  220 */             Long.valueOf(traceRet1));
/*      */       }
/*  222 */       return traceRet1;
/*      */     }
/*  224 */     catch (JMSException je) {
/*  225 */       if (Trace.isOn) {
/*  226 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDeliveryDelay()", (Throwable)je);
/*      */       }
/*      */       
/*  229 */       JMSRuntimeException traceRet2 = JmsErrorUtils.convertJMSException(je);
/*  230 */       if (Trace.isOn) {
/*  231 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDeliveryDelay()", (Throwable)traceRet2);
/*      */       }
/*      */       
/*  234 */       throw traceRet2;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDeliveryMode() throws JMSRuntimeException {
/*  241 */     if (Trace.isOn) {
/*  242 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDeliveryMode()");
/*      */     }
/*      */     try {
/*  245 */       int traceRet1 = this.deliveryProperties.getIntProperty("deliveryMode");
/*  246 */       if (Trace.isOn) {
/*  247 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDeliveryMode()", 
/*  248 */             Integer.valueOf(traceRet1));
/*      */       }
/*  250 */       return traceRet1;
/*      */     }
/*  252 */     catch (JMSException e) {
/*  253 */       if (Trace.isOn) {
/*  254 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDeliveryMode()", (Throwable)e);
/*      */       }
/*      */       
/*  257 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/*  258 */       je.initCause((Throwable)e);
/*  259 */       if (Trace.isOn) {
/*  260 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDeliveryMode()", (Throwable)je);
/*      */       }
/*      */       
/*  263 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getDisableMessageID() throws JMSRuntimeException {
/*  269 */     if (Trace.isOn) {
/*  270 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDisableMessageID()");
/*      */     }
/*      */     
/*      */     try {
/*  274 */       boolean traceRet1 = this.deliveryProperties.getBooleanProperty("XMSC_DISABLE_MSG_ID");
/*  275 */       if (Trace.isOn) {
/*  276 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDisableMessageID()", 
/*  277 */             Boolean.valueOf(traceRet1));
/*      */       }
/*  279 */       return traceRet1;
/*      */     }
/*  281 */     catch (JMSException e) {
/*  282 */       if (Trace.isOn) {
/*  283 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDisableMessageID()", (Throwable)e);
/*      */       }
/*      */       
/*  286 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/*  287 */       je.initCause((Throwable)e);
/*  288 */       if (Trace.isOn) {
/*  289 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDisableMessageID()", (Throwable)je);
/*      */       }
/*      */       
/*  292 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getDisableMessageTimestamp() throws JMSRuntimeException {
/*  298 */     if (Trace.isOn) {
/*  299 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDisableMessageTimestamp()");
/*      */     }
/*      */     
/*      */     try {
/*  303 */       boolean traceRet1 = this.deliveryProperties.getBooleanProperty("XMSC_DISABLE_MSG_TS");
/*  304 */       if (Trace.isOn) {
/*  305 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDisableMessageTimestamp()", 
/*  306 */             Boolean.valueOf(traceRet1));
/*      */       }
/*  308 */       return traceRet1;
/*      */     }
/*  310 */     catch (JMSException e) {
/*  311 */       if (Trace.isOn) {
/*  312 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDisableMessageTimestamp()", (Throwable)e);
/*      */       }
/*      */       
/*  315 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/*  316 */       je.initCause((Throwable)e);
/*  317 */       if (Trace.isOn) {
/*  318 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDisableMessageTimestamp()", (Throwable)je);
/*      */       }
/*      */       
/*  321 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String getJMSCorrelationID() throws JMSRuntimeException {
/*  327 */     if (Trace.isOn) {
/*  328 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getJMSCorrelationID()");
/*      */     }
/*      */     
/*      */     try {
/*  332 */       String traceRet1 = this.propertiesMessage.getJMSCorrelationID();
/*  333 */       if (Trace.isOn) {
/*  334 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getJMSCorrelationID()", traceRet1);
/*      */       }
/*      */       
/*  337 */       return traceRet1;
/*      */     }
/*  339 */     catch (JMSException e) {
/*  340 */       if (Trace.isOn) {
/*  341 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getJMSCorrelationID()", (Throwable)e);
/*      */       }
/*      */       
/*  344 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/*  345 */       je.initCause((Throwable)e);
/*  346 */       if (Trace.isOn) {
/*  347 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getJMSCorrelationID()", (Throwable)je);
/*      */       }
/*      */       
/*  350 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getJMSCorrelationIDAsBytes() throws JMSRuntimeException {
/*  357 */     if (Trace.isOn) {
/*  358 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getJMSCorrelationIDAsBytes()");
/*      */     }
/*      */     
/*      */     try {
/*  362 */       byte[] traceRet1 = this.propertiesMessage.getJMSCorrelationIDAsBytes();
/*  363 */       if (Trace.isOn) {
/*  364 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getJMSCorrelationIDAsBytes()", traceRet1);
/*      */       }
/*      */       
/*  367 */       return traceRet1;
/*      */     }
/*  369 */     catch (JMSException e) {
/*  370 */       if (Trace.isOn) {
/*  371 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getJMSCorrelationIDAsBytes()", (Throwable)e);
/*      */       }
/*      */       
/*  374 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/*  375 */       je.initCause((Throwable)e);
/*  376 */       if (Trace.isOn) {
/*  377 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getJMSCorrelationIDAsBytes()", (Throwable)je);
/*      */       }
/*      */       
/*  380 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Destination getJMSReplyTo() throws JMSRuntimeException {
/*  386 */     if (Trace.isOn) {
/*  387 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getJMSReplyTo()");
/*      */     }
/*      */     try {
/*  390 */       Destination traceRet1 = this.propertiesMessage.getJMSReplyTo();
/*  391 */       if (Trace.isOn) {
/*  392 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getJMSReplyTo()", traceRet1);
/*      */       }
/*      */       
/*  395 */       return traceRet1;
/*      */     }
/*  397 */     catch (JMSException e) {
/*  398 */       if (Trace.isOn) {
/*  399 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getJMSReplyTo()", (Throwable)e);
/*      */       }
/*      */       
/*  402 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/*  403 */       je.initCause((Throwable)e);
/*  404 */       if (Trace.isOn) {
/*  405 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getJMSReplyTo()", (Throwable)je);
/*      */       }
/*      */       
/*  408 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String getJMSType() throws JMSRuntimeException {
/*  414 */     if (Trace.isOn) {
/*  415 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getJMSType()");
/*      */     }
/*      */     try {
/*  418 */       String traceRet1 = this.propertiesMessage.getJMSType();
/*  419 */       if (Trace.isOn) {
/*  420 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getJMSType()", traceRet1);
/*      */       }
/*      */       
/*  423 */       return traceRet1;
/*      */     }
/*  425 */     catch (JMSException e) {
/*  426 */       if (Trace.isOn) {
/*  427 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getJMSType()", (Throwable)e);
/*      */       }
/*      */       
/*  430 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/*  431 */       je.initCause((Throwable)e);
/*  432 */       if (Trace.isOn) {
/*  433 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getJMSType()", (Throwable)je);
/*      */       }
/*      */       
/*  436 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPriority() throws JMSRuntimeException {
/*  442 */     if (Trace.isOn) {
/*  443 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getPriority()");
/*      */     }
/*      */     try {
/*  446 */       int traceRet1 = this.deliveryProperties.getIntProperty("priority");
/*  447 */       if (Trace.isOn) {
/*  448 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getPriority()", 
/*  449 */             Integer.valueOf(traceRet1));
/*      */       }
/*  451 */       return traceRet1;
/*      */     }
/*  453 */     catch (JMSException e) {
/*  454 */       if (Trace.isOn) {
/*  455 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getPriority()", (Throwable)e);
/*      */       }
/*      */       
/*  458 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/*  459 */       je.initCause((Throwable)e);
/*  460 */       if (Trace.isOn) {
/*  461 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getPriority()", (Throwable)je);
/*      */       }
/*      */       
/*  464 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public long getTimeToLive() throws JMSRuntimeException {
/*  470 */     if (Trace.isOn) {
/*  471 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getTimeToLive()");
/*      */     }
/*      */     try {
/*  474 */       long traceRet1 = this.deliveryProperties.getLongProperty("timeToLive");
/*  475 */       if (Trace.isOn) {
/*  476 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getTimeToLive()", 
/*  477 */             Long.valueOf(traceRet1));
/*      */       }
/*  479 */       return traceRet1;
/*      */     }
/*  481 */     catch (JMSException e) {
/*  482 */       if (Trace.isOn) {
/*  483 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getTimeToLive()", (Throwable)e);
/*      */       }
/*      */       
/*  486 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/*  487 */       je.initCause((Throwable)e);
/*  488 */       if (Trace.isOn) {
/*  489 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getTimeToLive()", (Throwable)je);
/*      */       }
/*      */       
/*  492 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSProducer send(Destination arg0, byte[] arg1) throws MessageFormatRuntimeException, InvalidDestinationRuntimeException, JMSRuntimeException {
/*  501 */     if (Trace.isOn) {
/*  502 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,byte [ ])", new Object[] { arg0, arg1 });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  508 */       BytesMessage bytesMessage = new JmsBytesMessageImpl(this.session, this.propertiesMessage, arg1);
/*  509 */       updateInternalProducer();
/*  510 */       if (this.completionListener != null) {
/*  511 */         this.producer.send(arg0, (Message)bytesMessage, this.completionListener);
/*      */       } else {
/*      */         
/*  514 */         this.producer.send(arg0, (Message)bytesMessage);
/*      */       }
/*      */     
/*  517 */     } catch (JMSException je) {
/*  518 */       if (Trace.isOn) {
/*  519 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,byte [ ])", (Throwable)je);
/*      */       }
/*      */       
/*  522 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  523 */       if (Trace.isOn) {
/*  524 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,byte [ ])", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  527 */       throw traceRet1;
/*      */     } 
/*      */     
/*  530 */     if (Trace.isOn) {
/*  531 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,byte [ ])", this);
/*      */     }
/*      */     
/*  534 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSProducer send(Destination arg0, Map<String, Object> arg1) throws MessageFormatRuntimeException, InvalidDestinationRuntimeException, JMSRuntimeException {
/*  542 */     if (Trace.isOn) {
/*  543 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,Map<String , Object>)", new Object[] { arg0, arg1 });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  549 */       MapMessage mapMessage = new JmsMapMessageImpl(this.session, this.propertiesMessage, arg1);
/*  550 */       updateInternalProducer();
/*  551 */       if (this.completionListener != null) {
/*  552 */         this.producer.send(arg0, (Message)mapMessage, this.completionListener);
/*      */       } else {
/*      */         
/*  555 */         this.producer.send(arg0, (Message)mapMessage);
/*      */       }
/*      */     
/*  558 */     } catch (JMSException je) {
/*  559 */       if (Trace.isOn) {
/*  560 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,Map<String , Object>)", (Throwable)je);
/*      */       }
/*      */       
/*  563 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  564 */       if (Trace.isOn) {
/*  565 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,Map<String , Object>)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  568 */       throw traceRet1;
/*      */     } 
/*      */     
/*  571 */     if (Trace.isOn) {
/*  572 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,Map<String , Object>)", this);
/*      */     }
/*      */     
/*  575 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSProducer send(Destination arg0, Message arg1) throws MessageFormatRuntimeException, InvalidDestinationRuntimeException, MessageNotWriteableRuntimeException, JMSRuntimeException {
/*  584 */     if (Trace.isOn) {
/*  585 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,Message)", new Object[] { arg0, arg1 });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  591 */       if (arg1 == null) {
/*  592 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0098", null);
/*  593 */         if (Trace.isOn) {
/*  594 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,Message)", (Throwable)je, 1);
/*      */         }
/*      */         
/*  597 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*  601 */       if (this.propsSet.get()) {
/*  602 */         copyMessageState(this.propertiesMessage, arg1);
/*      */       }
/*  604 */       updateInternalProducer();
/*  605 */       if (this.completionListener != null) {
/*  606 */         this.producer.send(arg0, arg1, this.completionListener);
/*      */       } else {
/*      */         
/*  609 */         this.producer.send(arg0, arg1);
/*      */       }
/*      */     
/*      */     }
/*  613 */     catch (JMSException je) {
/*  614 */       if (Trace.isOn) {
/*  615 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,Message)", (Throwable)je);
/*      */       }
/*      */       
/*  618 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  619 */       if (Trace.isOn) {
/*  620 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,Message)", (Throwable)traceRet1, 2);
/*      */       }
/*      */       
/*  623 */       throw traceRet1;
/*      */     } 
/*      */     
/*  626 */     if (Trace.isOn) {
/*  627 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,Message)", this);
/*      */     }
/*      */     
/*  630 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void copyMessageState(JmsMessageImpl sourceMessage, Message destinationMessage) throws JMSException {
/*  638 */     if (Trace.isOn) {
/*  639 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "copyMessageState(JmsMessageImpl,Message)", new Object[] { sourceMessage, destinationMessage });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  644 */     if (sourceMessage == null) {
/*  645 */       if (Trace.isOn) {
/*  646 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "copyMessageState(JmsMessageImpl,Message)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  653 */     if (destinationMessage instanceof JmsMessageImpl) {
/*  654 */       ((JmsMessageImpl)destinationMessage).checkPropertiesWriteable(null);
/*      */     }
/*      */ 
/*      */     
/*  658 */     if (sourceMessage.getJMSCorrelationID() != null) {
/*  659 */       if (destinationMessage.getJMSCorrelationID() != null && Trace.isOn) {
/*      */         
/*  661 */         HashMap<String, Object> data = new HashMap<>();
/*  662 */         data.put("Value in Message", destinationMessage
/*  663 */             .getJMSCorrelationID());
/*  664 */         data.put("Value in Producer", sourceMessage
/*  665 */             .getJMSCorrelationID());
/*  666 */         Trace.traceData(this, "copyMessageState(Message)", "Overriding JmsCorrelationID in message with value set in JMSProducer", data);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  673 */       destinationMessage.setJMSCorrelationID(sourceMessage
/*  674 */           .getJMSCorrelationID());
/*      */     } 
/*      */     
/*  677 */     if (sourceMessage.getJMSType() != null) {
/*  678 */       if (destinationMessage.getJMSType() != null && Trace.isOn) {
/*  679 */         HashMap<String, Object> data = new HashMap<>();
/*  680 */         data.put("Value in Message", destinationMessage.getJMSType());
/*  681 */         data.put("Value in Producer", sourceMessage.getJMSType());
/*  682 */         Trace.traceData(this, "copyMessageState(Message)", "Overriding JmsType in message with value set in JMSProducer", data);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  689 */       destinationMessage.setJMSType(sourceMessage.getJMSType());
/*      */     } 
/*      */     
/*  692 */     if (sourceMessage.getJMSReplyTo() != null) {
/*      */       JmsDestinationImpl jmsDestinationImpl;
/*      */ 
/*      */       
/*  696 */       if (destinationMessage.getJMSReplyTo() != null && Trace.isOn) {
/*  697 */         HashMap<String, Object> data = new HashMap<>();
/*  698 */         data.put("Value in Message", destinationMessage.getJMSReplyTo());
/*  699 */         data.put("Value in Producer", sourceMessage.getJMSReplyTo());
/*  700 */         Trace.traceData(this, "copyMessageState(Message)", "Overriding JmsReplyTo in message with value set in JMSProducer", data);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  706 */       Destination replyTo = sourceMessage.getJMSReplyTo();
/*  707 */       if (replyTo instanceof JmsDestinationImpl) {
/*  708 */         jmsDestinationImpl = sourceMessage.createDestination(sourceMessage
/*  709 */             .getConnectionTypeName(), (JmsDestinationImpl)replyTo);
/*      */       }
/*      */       
/*  712 */       destinationMessage.setJMSReplyTo((Destination)jmsDestinationImpl);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  719 */     Enumeration<?> propertyNames = sourceMessage.getPropertyNames();
/*  720 */     while (propertyNames != null && propertyNames.hasMoreElements()) {
/*  721 */       String name = (String)propertyNames.nextElement();
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  726 */         if (destinationMessage.getObjectProperty(name) != null && 
/*  727 */           Trace.isOn) {
/*  728 */           HashMap<String, Object> data = new HashMap<>();
/*  729 */           data.put("propertyName", name);
/*  730 */           data.put("Value in Message", destinationMessage
/*  731 */               .getObjectProperty(name));
/*  732 */           data.put("Value in Producer", sourceMessage
/*  733 */               .getObjectProperty(name));
/*  734 */           Trace.traceData(this, "copyMessageState(Message)", "Overriding property in message with value set in JMSProducer", data);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  741 */         Object value = sourceMessage.getObjectProperty(name);
/*  742 */         destinationMessage.setObjectProperty(name, value);
/*      */       }
/*  744 */       catch (Exception e) {
/*  745 */         if (Trace.isOn) {
/*  746 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "copyMessageState(JmsMessageImpl,Message)", e);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  752 */     if (Trace.isOn) {
/*  753 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "copyMessageState(JmsMessageImpl,Message)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSProducer send(Destination arg0, Serializable arg1) throws MessageFormatRuntimeException, InvalidDestinationRuntimeException, JMSRuntimeException {
/*  764 */     if (Trace.isOn) {
/*  765 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,Serializable)", new Object[] { arg0, arg1 });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  771 */       ObjectMessage objectMessage = new JmsObjectMessageImpl(this.session, this.propertiesMessage, arg1);
/*  772 */       updateInternalProducer();
/*  773 */       if (this.completionListener != null) {
/*  774 */         this.producer.send(arg0, (Message)objectMessage, this.completionListener);
/*      */       } else {
/*      */         
/*  777 */         this.producer.send(arg0, (Message)objectMessage);
/*      */       }
/*      */     
/*  780 */     } catch (JMSException je) {
/*  781 */       if (Trace.isOn) {
/*  782 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,Serializable)", (Throwable)je);
/*      */       }
/*      */       
/*  785 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  786 */       if (Trace.isOn) {
/*  787 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,Serializable)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  790 */       throw traceRet1;
/*      */     } 
/*      */     
/*  793 */     if (Trace.isOn) {
/*  794 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,Serializable)", this);
/*      */     }
/*      */     
/*  797 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSProducer send(Destination arg0, String arg1) throws MessageFormatRuntimeException, InvalidDestinationRuntimeException, JMSRuntimeException {
/*  805 */     if (Trace.isOn) {
/*  806 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,String)", new Object[] { arg0, arg1 });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  812 */       TextMessage textMessage = new JmsTextMessageImpl(this.session, this.propertiesMessage, arg1);
/*  813 */       updateInternalProducer();
/*  814 */       if (this.completionListener != null) {
/*  815 */         this.producer.send(arg0, (Message)textMessage, this.completionListener);
/*      */       } else {
/*      */         
/*  818 */         this.producer.send(arg0, (Message)textMessage);
/*      */       }
/*      */     
/*  821 */     } catch (JMSException je) {
/*  822 */       if (Trace.isOn) {
/*  823 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,String)", (Throwable)je);
/*      */       }
/*      */       
/*  826 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  827 */       if (Trace.isOn) {
/*  828 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  831 */       throw traceRet1;
/*      */     } 
/*      */     
/*  834 */     if (Trace.isOn) {
/*  835 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "send(Destination,String)", this);
/*      */     }
/*      */     
/*  838 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setAsync(CompletionListener completionListener) throws JMSRuntimeException {
/*  843 */     if (Trace.isOn) {
/*  844 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setAsync(CompletionListener)", new Object[] { completionListener });
/*      */     }
/*      */     
/*  847 */     this.context.checkValidFnUsage(JmsConnectionImpl.JMS2Function.ASYNCHRONOUS_SEND);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  852 */     if (this.isCICSUnmanaged || this.isIMS || this.isWAS) {
/*  853 */       HashMap<String, Object> inserts = new HashMap<>();
/*  854 */       inserts.put("XMSC_INSERT_METHOD", "setAsync(CompletionListener)");
/*      */       
/*  856 */       String messageID = this.isCICSUnmanaged ? "JMSCC6001" : (this.isIMS ? "JMSCC6011" : "JMSCC6015");
/*      */ 
/*      */       
/*  859 */       DetailedJMSException e = (DetailedJMSException)JmsErrorUtils.createException(messageID, inserts);
/*      */       
/*  861 */       JMSRuntimeException je = e.getUnchecked();
/*  862 */       if (Trace.isOn) {
/*  863 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setAsync(CompletionListener)", (Throwable)je, 1);
/*      */       }
/*      */       
/*  866 */       throw je;
/*      */     } 
/*      */     
/*  869 */     this.completionListener = completionListener;
/*  870 */     if (Trace.isOn) {
/*  871 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setAsync(CompletionListener)", this);
/*      */     }
/*      */     
/*  874 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setDeliveryDelay(long delay) throws JMSRuntimeException {
/*  879 */     if (Trace.isOn) {
/*  880 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setDeliveryDelay(long)", new Object[] {
/*  881 */             Long.valueOf(delay)
/*      */           });
/*      */     }
/*      */     try {
/*  885 */       this.context.checkValidFnUsage(JmsConnectionImpl.JMS2Function.ASYNCHRONOUS_SEND);
/*      */       
/*  887 */       this.deliveryProperties.setLongProperty("deliveryDelay", delay);
/*      */     }
/*  889 */     catch (JMSException e) {
/*  890 */       if (Trace.isOn) {
/*  891 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setDeliveryDelay(long)", (Throwable)e);
/*      */       }
/*      */       
/*  894 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/*  895 */       je.initCause((Throwable)e);
/*  896 */       if (Trace.isOn) {
/*  897 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setDeliveryDelay(long)", (Throwable)je);
/*      */       }
/*      */       
/*  900 */       throw je;
/*      */     } 
/*      */     
/*  903 */     if (Trace.isOn) {
/*  904 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setDeliveryDelay(long)", this);
/*      */     }
/*      */     
/*  907 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setDeliveryMode(int deliveryMode) throws JMSRuntimeException {
/*  912 */     if (Trace.isOn) {
/*  913 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setDeliveryMode(int)", new Object[] {
/*  914 */             Integer.valueOf(deliveryMode)
/*      */           });
/*      */     }
/*      */     try {
/*  918 */       this.deliveryProperties.setIntProperty("deliveryMode", deliveryMode);
/*      */     }
/*  920 */     catch (JMSException e) {
/*  921 */       if (Trace.isOn) {
/*  922 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setDeliveryMode(int)", (Throwable)e);
/*      */       }
/*      */       
/*  925 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/*  926 */       je.initCause((Throwable)e);
/*  927 */       if (Trace.isOn) {
/*  928 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setDeliveryMode(int)", (Throwable)je);
/*      */       }
/*      */       
/*  931 */       throw je;
/*      */     } 
/*      */     
/*  934 */     if (Trace.isOn) {
/*  935 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setDeliveryMode(int)", this);
/*      */     }
/*      */     
/*  938 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setDisableMessageID(boolean disableMsgID) throws JMSRuntimeException {
/*  943 */     if (Trace.isOn) {
/*  944 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setDisableMessageID(boolean)", new Object[] {
/*  945 */             Boolean.valueOf(disableMsgID)
/*      */           });
/*      */     }
/*      */     try {
/*  949 */       this.deliveryProperties.setBooleanProperty("XMSC_DISABLE_MSG_ID", disableMsgID);
/*      */     }
/*  951 */     catch (JMSException e) {
/*  952 */       if (Trace.isOn) {
/*  953 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setDisableMessageID(boolean)", (Throwable)e);
/*      */       }
/*      */       
/*  956 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/*  957 */       je.initCause((Throwable)e);
/*  958 */       if (Trace.isOn) {
/*  959 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setDisableMessageID(boolean)", (Throwable)je);
/*      */       }
/*      */       
/*  962 */       throw je;
/*      */     } 
/*      */     
/*  965 */     if (Trace.isOn) {
/*  966 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setDisableMessageID(boolean)", this);
/*      */     }
/*      */     
/*  969 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setDisableMessageTimestamp(boolean disableMsgTS) throws JMSRuntimeException {
/*  974 */     if (Trace.isOn) {
/*  975 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setDisableMessageTimestamp(boolean)", new Object[] {
/*  976 */             Boolean.valueOf(disableMsgTS)
/*      */           });
/*      */     }
/*      */     try {
/*  980 */       this.deliveryProperties.setBooleanProperty("XMSC_DISABLE_MSG_TS", disableMsgTS);
/*      */     }
/*  982 */     catch (JMSException e) {
/*  983 */       if (Trace.isOn) {
/*  984 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setDisableMessageTimestamp(boolean)", (Throwable)e);
/*      */       }
/*      */       
/*  987 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/*  988 */       je.initCause((Throwable)e);
/*  989 */       if (Trace.isOn) {
/*  990 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setDisableMessageTimestamp(boolean)", (Throwable)je);
/*      */       }
/*      */       
/*  993 */       throw je;
/*      */     } 
/*      */     
/*  996 */     if (Trace.isOn) {
/*  997 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setDisableMessageTimestamp(boolean)", this);
/*      */     }
/*      */     
/* 1000 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setJMSCorrelationID(String correl) throws JMSRuntimeException {
/* 1005 */     if (Trace.isOn) {
/* 1006 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setJMSCorrelationID(String)", new Object[] { correl });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1011 */       this.propertiesMessage.setJMSCorrelationID(correl);
/* 1012 */       this.propsSet.set(true);
/*      */     }
/* 1014 */     catch (JMSException e) {
/* 1015 */       if (Trace.isOn) {
/* 1016 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setJMSCorrelationID(String)", (Throwable)e);
/*      */       }
/*      */       
/* 1019 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1020 */       je.initCause((Throwable)e);
/* 1021 */       if (Trace.isOn) {
/* 1022 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setJMSCorrelationID(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1025 */       throw je;
/*      */     } 
/*      */     
/* 1028 */     if (Trace.isOn) {
/* 1029 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setJMSCorrelationID(String)", this);
/*      */     }
/*      */     
/* 1032 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setJMSCorrelationIDAsBytes(byte[] correlIdBytes) throws JMSRuntimeException {
/* 1037 */     if (Trace.isOn) {
/* 1038 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setJMSCorrelationIDAsBytes(byte [ ])", new Object[] { correlIdBytes });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1043 */       this.propertiesMessage.setJMSCorrelationIDAsBytes(correlIdBytes);
/* 1044 */       this.propsSet.set(true);
/*      */     }
/* 1046 */     catch (JMSException e) {
/* 1047 */       if (Trace.isOn) {
/* 1048 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setJMSCorrelationIDAsBytes(byte [ ])", (Throwable)e);
/*      */       }
/*      */       
/* 1051 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1052 */       je.initCause((Throwable)e);
/* 1053 */       if (Trace.isOn) {
/* 1054 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setJMSCorrelationIDAsBytes(byte [ ])", (Throwable)je);
/*      */       }
/*      */       
/* 1057 */       throw je;
/*      */     } 
/*      */     
/* 1060 */     if (Trace.isOn) {
/* 1061 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setJMSCorrelationIDAsBytes(byte [ ])", this);
/*      */     }
/*      */     
/* 1064 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setJMSReplyTo(Destination destination) throws JMSRuntimeException {
/* 1069 */     if (Trace.isOn) {
/* 1070 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setJMSReplyTo(Destination)", new Object[] { destination });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1075 */       this.propertiesMessage.setJMSReplyTo(destination);
/* 1076 */       this.propsSet.set(true);
/*      */     }
/* 1078 */     catch (JMSException e) {
/* 1079 */       if (Trace.isOn) {
/* 1080 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setJMSReplyTo(Destination)", (Throwable)e);
/*      */       }
/*      */       
/* 1083 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1084 */       je.initCause((Throwable)e);
/* 1085 */       if (Trace.isOn) {
/* 1086 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setJMSReplyTo(Destination)", (Throwable)je);
/*      */       }
/*      */       
/* 1089 */       throw je;
/*      */     } 
/*      */     
/* 1092 */     if (Trace.isOn) {
/* 1093 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setJMSReplyTo(Destination)", this);
/*      */     }
/*      */     
/* 1096 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setJMSType(String jmsType) throws JMSRuntimeException {
/* 1101 */     if (Trace.isOn) {
/* 1102 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setJMSType(String)", new Object[] { jmsType });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1107 */       this.propertiesMessage.setJMSType(jmsType);
/* 1108 */       this.propsSet.set(true);
/*      */     }
/* 1110 */     catch (JMSException e) {
/* 1111 */       if (Trace.isOn) {
/* 1112 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setJMSType(String)", (Throwable)e);
/*      */       }
/*      */       
/* 1115 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1116 */       je.initCause((Throwable)e);
/* 1117 */       if (Trace.isOn) {
/* 1118 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setJMSType(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1121 */       throw je;
/*      */     } 
/*      */     
/* 1124 */     if (Trace.isOn) {
/* 1125 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setJMSType(String)", this);
/*      */     }
/*      */     
/* 1128 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setPriority(int priority) throws JMSRuntimeException {
/* 1133 */     if (Trace.isOn) {
/* 1134 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setPriority(int)", new Object[] {
/* 1135 */             Integer.valueOf(priority)
/*      */           });
/*      */     }
/*      */     try {
/* 1139 */       this.deliveryProperties.setIntProperty("priority", priority);
/*      */     }
/* 1141 */     catch (JMSException e) {
/* 1142 */       if (Trace.isOn) {
/* 1143 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setPriority(int)", (Throwable)e);
/*      */       }
/*      */       
/* 1146 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1147 */       je.initCause((Throwable)e);
/* 1148 */       if (Trace.isOn) {
/* 1149 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setPriority(int)", (Throwable)je);
/*      */       }
/*      */       
/* 1152 */       throw je;
/*      */     } 
/*      */     
/* 1155 */     if (Trace.isOn) {
/* 1156 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setPriority(int)", this);
/*      */     }
/*      */     
/* 1159 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setProperty(String name, boolean value) throws JMSRuntimeException, IllegalArgumentException {
/* 1164 */     if (Trace.isOn) {
/* 1165 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,boolean)", new Object[] { name, 
/* 1166 */             Boolean.valueOf(value) });
/*      */     }
/*      */     
/*      */     try {
/* 1170 */       this.propertiesMessage.setBooleanProperty(name, value);
/* 1171 */       this.propsSet.set(true);
/*      */     }
/* 1173 */     catch (JMSException e) {
/* 1174 */       if (Trace.isOn) {
/* 1175 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,boolean)", (Throwable)e);
/*      */       }
/*      */       
/* 1178 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1179 */       je.initCause((Throwable)e);
/* 1180 */       if (Trace.isOn) {
/* 1181 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,boolean)", (Throwable)je);
/*      */       }
/*      */       
/* 1184 */       throw je;
/*      */     } 
/*      */     
/* 1187 */     if (Trace.isOn) {
/* 1188 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,boolean)", this);
/*      */     }
/*      */     
/* 1191 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setProperty(String name, byte value) throws JMSRuntimeException, IllegalArgumentException {
/* 1196 */     if (Trace.isOn) {
/* 1197 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,byte)", new Object[] { name, 
/* 1198 */             Byte.valueOf(value) });
/*      */     }
/*      */     
/*      */     try {
/* 1202 */       this.propertiesMessage.setByteProperty(name, value);
/* 1203 */       this.propsSet.set(true);
/*      */     }
/* 1205 */     catch (JMSException e) {
/* 1206 */       if (Trace.isOn) {
/* 1207 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,byte)", (Throwable)e);
/*      */       }
/*      */       
/* 1210 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1211 */       je.initCause((Throwable)e);
/* 1212 */       if (Trace.isOn) {
/* 1213 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,byte)", (Throwable)je);
/*      */       }
/*      */       
/* 1216 */       throw je;
/*      */     } 
/*      */     
/* 1219 */     if (Trace.isOn) {
/* 1220 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,byte)", this);
/*      */     }
/*      */     
/* 1223 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setProperty(String name, double value) throws JMSRuntimeException, IllegalArgumentException {
/* 1228 */     if (Trace.isOn) {
/* 1229 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,double)", new Object[] { name, 
/* 1230 */             Double.valueOf(value) });
/*      */     }
/*      */     
/*      */     try {
/* 1234 */       this.propertiesMessage.setDoubleProperty(name, value);
/*      */       
/* 1236 */       this.propsSet.set(true);
/*      */     }
/* 1238 */     catch (JMSException e) {
/* 1239 */       if (Trace.isOn) {
/* 1240 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,double)", (Throwable)e);
/*      */       }
/*      */       
/* 1243 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1244 */       je.initCause((Throwable)e);
/* 1245 */       if (Trace.isOn) {
/* 1246 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,double)", (Throwable)je);
/*      */       }
/*      */       
/* 1249 */       throw je;
/*      */     } 
/*      */     
/* 1252 */     if (Trace.isOn) {
/* 1253 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,double)", this);
/*      */     }
/*      */     
/* 1256 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setProperty(String name, float value) throws JMSRuntimeException, IllegalArgumentException {
/* 1261 */     if (Trace.isOn) {
/* 1262 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,float)", new Object[] { name, 
/* 1263 */             Float.valueOf(value) });
/*      */     }
/*      */     
/*      */     try {
/* 1267 */       this.propertiesMessage.setFloatProperty(name, value);
/* 1268 */       this.propsSet.set(true);
/*      */     }
/* 1270 */     catch (JMSException e) {
/* 1271 */       if (Trace.isOn) {
/* 1272 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,float)", (Throwable)e);
/*      */       }
/*      */       
/* 1275 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1276 */       je.initCause((Throwable)e);
/* 1277 */       if (Trace.isOn) {
/* 1278 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,float)", (Throwable)je);
/*      */       }
/*      */       
/* 1281 */       throw je;
/*      */     } 
/*      */     
/* 1284 */     if (Trace.isOn) {
/* 1285 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,float)", this);
/*      */     }
/*      */     
/* 1288 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setProperty(String name, int value) throws JMSRuntimeException, IllegalArgumentException {
/* 1293 */     if (Trace.isOn) {
/* 1294 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,int)", new Object[] { name, 
/* 1295 */             Integer.valueOf(value) });
/*      */     }
/*      */     
/*      */     try {
/* 1299 */       this.propertiesMessage.setIntProperty(name, value);
/* 1300 */       this.propsSet.set(true);
/*      */     }
/* 1302 */     catch (JMSException e) {
/* 1303 */       if (Trace.isOn) {
/* 1304 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,int)", (Throwable)e);
/*      */       }
/*      */       
/* 1307 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1308 */       je.initCause((Throwable)e);
/* 1309 */       if (Trace.isOn) {
/* 1310 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,int)", (Throwable)je);
/*      */       }
/*      */       
/* 1313 */       throw je;
/*      */     } 
/*      */     
/* 1316 */     if (Trace.isOn) {
/* 1317 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,int)", this);
/*      */     }
/*      */     
/* 1320 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setProperty(String name, long value) throws JMSRuntimeException, IllegalArgumentException {
/* 1325 */     if (Trace.isOn) {
/* 1326 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,long)", new Object[] { name, 
/* 1327 */             Long.valueOf(value) });
/*      */     }
/*      */     
/*      */     try {
/* 1331 */       this.propertiesMessage.setLongProperty(name, value);
/* 1332 */       this.propsSet.set(true);
/*      */     }
/* 1334 */     catch (JMSException e) {
/* 1335 */       if (Trace.isOn) {
/* 1336 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,long)", (Throwable)e);
/*      */       }
/*      */       
/* 1339 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1340 */       je.initCause((Throwable)e);
/* 1341 */       if (Trace.isOn) {
/* 1342 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,long)", (Throwable)je);
/*      */       }
/*      */       
/* 1345 */       throw je;
/*      */     } 
/*      */     
/* 1348 */     if (Trace.isOn) {
/* 1349 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,long)", this);
/*      */     }
/*      */     
/* 1352 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSProducer setProperty(String name, Object value) throws JMSRuntimeException, IllegalArgumentException, MessageFormatRuntimeException {
/* 1360 */     if (Trace.isOn) {
/* 1361 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,Object)", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1366 */       this.propertiesMessage.setObjectProperty(name, value);
/* 1367 */       this.propsSet.set(true);
/*      */     }
/* 1369 */     catch (JMSException e) {
/* 1370 */       if (Trace.isOn) {
/* 1371 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,Object)", (Throwable)e);
/*      */       }
/*      */       
/* 1374 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1375 */       je = JmsErrorUtils.convertJMSException(e);
/* 1376 */       je.initCause((Throwable)e);
/* 1377 */       if (Trace.isOn) {
/* 1378 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,Object)", (Throwable)je);
/*      */       }
/*      */       
/* 1381 */       throw je;
/*      */     } 
/*      */     
/* 1384 */     if (Trace.isOn) {
/* 1385 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,Object)", this);
/*      */     }
/*      */     
/* 1388 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setProperty(String name, short value) throws JMSRuntimeException, IllegalArgumentException {
/* 1393 */     if (Trace.isOn) {
/* 1394 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,short)", new Object[] { name, 
/* 1395 */             Short.valueOf(value) });
/*      */     }
/*      */     
/*      */     try {
/* 1399 */       this.propertiesMessage.setShortProperty(name, value);
/* 1400 */       this.propsSet.set(true);
/*      */     }
/* 1402 */     catch (JMSException e) {
/* 1403 */       if (Trace.isOn) {
/* 1404 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,short)", (Throwable)e);
/*      */       }
/*      */       
/* 1407 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1408 */       je.initCause((Throwable)e);
/* 1409 */       if (Trace.isOn) {
/* 1410 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,short)", (Throwable)je);
/*      */       }
/*      */       
/* 1413 */       throw je;
/*      */     } 
/*      */     
/* 1416 */     if (Trace.isOn) {
/* 1417 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,short)", this);
/*      */     }
/*      */     
/* 1420 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setProperty(String name, String value) throws JMSRuntimeException, IllegalArgumentException {
/* 1425 */     if (Trace.isOn) {
/* 1426 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,String)", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1431 */       this.propertiesMessage.setStringProperty(name, value);
/* 1432 */       this.propsSet.set(true);
/*      */     }
/* 1434 */     catch (JMSException e) {
/* 1435 */       if (Trace.isOn) {
/* 1436 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,String)", (Throwable)e);
/*      */       }
/*      */       
/* 1439 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1440 */       je.initCause((Throwable)e);
/* 1441 */       if (Trace.isOn) {
/* 1442 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,String)", (Throwable)je);
/*      */       }
/*      */       
/* 1445 */       throw je;
/*      */     } 
/*      */     
/* 1448 */     if (Trace.isOn) {
/* 1449 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setProperty(String,String)", this);
/*      */     }
/*      */     
/* 1452 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public JMSProducer setTimeToLive(long timeToLive) throws JMSRuntimeException {
/* 1457 */     if (Trace.isOn) {
/* 1458 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setTimeToLive(long)", new Object[] {
/* 1459 */             Long.valueOf(timeToLive)
/*      */           });
/*      */     }
/*      */     try {
/* 1463 */       this.deliveryProperties.setLongProperty("timeToLive", timeToLive);
/*      */     }
/* 1465 */     catch (JMSException e) {
/* 1466 */       if (Trace.isOn) {
/* 1467 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setTimeToLive(long)", (Throwable)e);
/*      */       }
/*      */       
/* 1470 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1471 */       je.initCause((Throwable)e);
/* 1472 */       if (Trace.isOn) {
/* 1473 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setTimeToLive(long)", (Throwable)je);
/*      */       }
/*      */       
/* 1476 */       throw je;
/*      */     } 
/*      */     
/* 1479 */     if (Trace.isOn) {
/* 1480 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "setTimeToLive(long)", this);
/*      */     }
/*      */     
/* 1483 */     return (JMSProducer)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getBooleanProperty(String name) throws JMSRuntimeException, MessageFormatRuntimeException {
/* 1488 */     if (Trace.isOn) {
/* 1489 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getBooleanProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1494 */       boolean traceRet1 = this.propertiesMessage.getBooleanProperty(name);
/* 1495 */       if (Trace.isOn) {
/* 1496 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getBooleanProperty(String)", 
/* 1497 */             Boolean.valueOf(traceRet1));
/*      */       }
/* 1499 */       return traceRet1;
/*      */     }
/* 1501 */     catch (JMSException e) {
/* 1502 */       if (Trace.isOn) {
/* 1503 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getBooleanProperty(String)", (Throwable)e);
/*      */       }
/*      */       
/* 1506 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1507 */       je.initCause((Throwable)e);
/* 1508 */       if (Trace.isOn) {
/* 1509 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getBooleanProperty(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1512 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public byte getByteProperty(String name) throws JMSRuntimeException, MessageFormatRuntimeException {
/* 1518 */     if (Trace.isOn) {
/* 1519 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getByteProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1524 */       byte traceRet1 = this.propertiesMessage.getByteProperty(name);
/* 1525 */       if (Trace.isOn) {
/* 1526 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getByteProperty(String)", 
/* 1527 */             Byte.valueOf(traceRet1));
/*      */       }
/* 1529 */       return traceRet1;
/*      */     }
/* 1531 */     catch (JMSException e) {
/* 1532 */       if (Trace.isOn) {
/* 1533 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getByteProperty(String)", (Throwable)e);
/*      */       }
/*      */       
/* 1536 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1537 */       je.initCause((Throwable)e);
/* 1538 */       if (Trace.isOn) {
/* 1539 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getByteProperty(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1542 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public double getDoubleProperty(String name) throws JMSRuntimeException, MessageFormatRuntimeException {
/* 1548 */     if (Trace.isOn) {
/* 1549 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDoubleProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1554 */       double traceRet1 = this.propertiesMessage.getDoubleProperty(name);
/* 1555 */       if (Trace.isOn) {
/* 1556 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDoubleProperty(String)", 
/* 1557 */             Double.valueOf(traceRet1));
/*      */       }
/* 1559 */       return traceRet1;
/*      */     }
/* 1561 */     catch (JMSException e) {
/* 1562 */       if (Trace.isOn) {
/* 1563 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDoubleProperty(String)", (Throwable)e);
/*      */       }
/*      */       
/* 1566 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1567 */       je.initCause((Throwable)e);
/* 1568 */       if (Trace.isOn) {
/* 1569 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getDoubleProperty(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1572 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public float getFloatProperty(String name) throws JMSRuntimeException, MessageFormatRuntimeException {
/* 1578 */     if (Trace.isOn) {
/* 1579 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getFloatProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1584 */       float traceRet1 = this.propertiesMessage.getFloatProperty(name);
/* 1585 */       if (Trace.isOn) {
/* 1586 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getFloatProperty(String)", 
/* 1587 */             Float.valueOf(traceRet1));
/*      */       }
/* 1589 */       return traceRet1;
/*      */     }
/* 1591 */     catch (JMSException e) {
/* 1592 */       if (Trace.isOn) {
/* 1593 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getFloatProperty(String)", (Throwable)e);
/*      */       }
/*      */       
/* 1596 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1597 */       je.initCause((Throwable)e);
/* 1598 */       if (Trace.isOn) {
/* 1599 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getFloatProperty(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1602 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getIntProperty(String name) throws JMSRuntimeException, MessageFormatRuntimeException {
/* 1608 */     if (Trace.isOn) {
/* 1609 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getIntProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1614 */       int traceRet1 = this.propertiesMessage.getIntProperty(name);
/* 1615 */       if (Trace.isOn) {
/* 1616 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getIntProperty(String)", 
/* 1617 */             Integer.valueOf(traceRet1));
/*      */       }
/* 1619 */       return traceRet1;
/*      */     }
/* 1621 */     catch (JMSException e) {
/* 1622 */       if (Trace.isOn) {
/* 1623 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getIntProperty(String)", (Throwable)e);
/*      */       }
/*      */       
/* 1626 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1627 */       je.initCause((Throwable)e);
/* 1628 */       if (Trace.isOn) {
/* 1629 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getIntProperty(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1632 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public long getLongProperty(String name) throws JMSRuntimeException, MessageFormatRuntimeException {
/* 1638 */     if (Trace.isOn) {
/* 1639 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getLongProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1644 */       long traceRet1 = this.propertiesMessage.getLongProperty(name);
/* 1645 */       if (Trace.isOn) {
/* 1646 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getLongProperty(String)", 
/* 1647 */             Long.valueOf(traceRet1));
/*      */       }
/* 1649 */       return traceRet1;
/*      */     }
/* 1651 */     catch (JMSException e) {
/* 1652 */       if (Trace.isOn) {
/* 1653 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getLongProperty(String)", (Throwable)e);
/*      */       }
/*      */       
/* 1656 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1657 */       je.initCause((Throwable)e);
/* 1658 */       if (Trace.isOn) {
/* 1659 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getLongProperty(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1662 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getObjectProperty(String name) throws JMSRuntimeException {
/* 1669 */     if (Trace.isOn) {
/* 1670 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getObjectProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1675 */       Object traceRet1 = this.propertiesMessage.getObjectProperty(name);
/* 1676 */       if (Trace.isOn) {
/* 1677 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getObjectProperty(String)", traceRet1);
/*      */       }
/*      */       
/* 1680 */       return traceRet1;
/*      */     }
/* 1682 */     catch (JMSException e) {
/* 1683 */       if (Trace.isOn) {
/* 1684 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getObjectProperty(String)", (Throwable)e);
/*      */       }
/*      */       
/* 1687 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1688 */       je.initCause((Throwable)e);
/* 1689 */       if (Trace.isOn) {
/* 1690 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getObjectProperty(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1693 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Set<String> getPropertyNames() throws JMSRuntimeException {
/* 1699 */     if (Trace.isOn) {
/* 1700 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getPropertyNames()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1705 */       Enumeration<String> e = this.propertiesMessage.getPropertyNames();
/* 1706 */       HashSet<String> set = new HashSet<>();
/*      */       
/* 1708 */       while (e.hasMoreElements()) {
/* 1709 */         set.add(e.nextElement());
/*      */       }
/*      */       
/* 1712 */       if (Trace.isOn) {
/* 1713 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getPropertyNames()", set);
/*      */       }
/*      */       
/* 1716 */       return set;
/*      */     }
/* 1718 */     catch (JMSException e) {
/* 1719 */       if (Trace.isOn) {
/* 1720 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getPropertyNames()", (Throwable)e);
/*      */       }
/*      */       
/* 1723 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1724 */       je.initCause((Throwable)e);
/* 1725 */       if (Trace.isOn) {
/* 1726 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getPropertyNames()", (Throwable)je);
/*      */       }
/*      */       
/* 1729 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public short getShortProperty(String name) throws JMSRuntimeException, MessageFormatRuntimeException {
/* 1735 */     if (Trace.isOn) {
/* 1736 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getShortProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1741 */       short traceRet1 = this.propertiesMessage.getShortProperty(name);
/* 1742 */       if (Trace.isOn) {
/* 1743 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getShortProperty(String)", 
/* 1744 */             Short.valueOf(traceRet1));
/*      */       }
/* 1746 */       return traceRet1;
/*      */     }
/* 1748 */     catch (JMSException e) {
/* 1749 */       if (Trace.isOn) {
/* 1750 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getShortProperty(String)", (Throwable)e);
/*      */       }
/*      */       
/* 1753 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1754 */       je.initCause((Throwable)e);
/* 1755 */       if (Trace.isOn) {
/* 1756 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getShortProperty(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1759 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String getStringProperty(String name) throws JMSRuntimeException, MessageFormatRuntimeException {
/* 1765 */     if (Trace.isOn) {
/* 1766 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getStringProperty(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1771 */       String traceRet1 = this.propertiesMessage.getStringProperty(name);
/* 1772 */       if (Trace.isOn) {
/* 1773 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getStringProperty(String)", traceRet1);
/*      */       }
/*      */       
/* 1776 */       return traceRet1;
/*      */     }
/* 1778 */     catch (JMSException e) {
/* 1779 */       if (Trace.isOn) {
/* 1780 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getStringProperty(String)", (Throwable)e);
/*      */       }
/*      */       
/* 1783 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1784 */       je.initCause((Throwable)e);
/* 1785 */       if (Trace.isOn) {
/* 1786 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "getStringProperty(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1789 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean propertyExists(String name) throws JMSRuntimeException {
/* 1795 */     if (Trace.isOn) {
/* 1796 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "propertyExists(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1801 */       boolean traceRet1 = this.propertiesMessage.propertyExists(name);
/* 1802 */       if (Trace.isOn) {
/* 1803 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "propertyExists(String)", 
/* 1804 */             Boolean.valueOf(traceRet1));
/*      */       }
/* 1806 */       return traceRet1;
/*      */     }
/* 1808 */     catch (JMSException e) {
/* 1809 */       if (Trace.isOn) {
/* 1810 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "propertyExists(String)", (Throwable)e);
/*      */       }
/*      */       
/* 1813 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 1814 */       je.initCause((Throwable)e);
/* 1815 */       if (Trace.isOn) {
/* 1816 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "propertyExists(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1819 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void dump(PrintWriter pw, int level) {
/* 1825 */     if (Trace.isOn) {
/* 1826 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "dump(PrintWriter,int)", new Object[] { pw, 
/* 1827 */             Integer.valueOf(level) });
/*      */     }
/* 1829 */     String prefix = Trace.buildPrefix(level);
/* 1830 */     pw.format("%s%s@%x (%s)\n", new Object[] { prefix, getClass().getName(), Integer.valueOf(hashCode()), (this.producer == null) ? "<null>" : 
/* 1831 */           String.format("%s@%x", new Object[] { this.producer.getClass().getName(), Integer.valueOf(this.producer.hashCode()) }) });
/* 1832 */     pw.format("%s  completionListener %s", new Object[] { prefix, (this.completionListener == null) ? "<null>" : 
/* 1833 */           String.format("%s@%x", new Object[] { this.completionListener.getClass().getName(), Integer.valueOf(this.completionListener.hashCode()) }) });
/* 1834 */     if (this.producer != null) {
/* 1835 */       this.producer.dump(pw, level + 1);
/*      */     }
/* 1837 */     if (Trace.isOn) {
/* 1838 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl", "dump(PrintWriter,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateInternalProducer() throws JMSException {
/* 1846 */     int deliveryMode = this.deliveryProperties.getIntProperty("deliveryMode");
/* 1847 */     int priority = this.deliveryProperties.getIntProperty("priority");
/* 1848 */     long timeToLive = this.deliveryProperties.getLongProperty("timeToLive");
/* 1849 */     long deliveryDelay = this.deliveryProperties.getLongProperty("deliveryDelay");
/* 1850 */     boolean disableMsgID = this.deliveryProperties.getBooleanProperty("XMSC_DISABLE_MSG_ID");
/* 1851 */     boolean disableMsgTS = this.deliveryProperties.getBooleanProperty("XMSC_DISABLE_MSG_TS");
/*      */     
/* 1853 */     this.producer.setDeliveryMode(deliveryMode);
/* 1854 */     this.producer.setPriority(priority);
/* 1855 */     this.producer.setTimeToLive(timeToLive);
/* 1856 */     this.producer.setDeliveryDelay(deliveryDelay);
/* 1857 */     this.producer.setDisableMessageID(disableMsgID);
/* 1858 */     this.producer.setDisableMessageTimestamp(disableMsgTS);
/*      */   }
/*      */ 
/*      */   
/*      */   static class JmsProducerDeliveryProperties
/*      */     extends JmsPropertyContextImpl
/*      */   {
/*      */     private static final long serialVersionUID = 3186319622186790812L;
/*      */     
/*      */     private JmsContextImpl context;
/*      */     
/*      */     public JmsProducerDeliveryProperties(JmsContextImpl context) {
/* 1870 */       if (Trace.isOn) {
/* 1871 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl.JmsProducerDeliveryProperties", "<init>(JmsContextImpl)", new Object[] { context });
/*      */       }
/*      */ 
/*      */       
/* 1875 */       this.context = context;
/* 1876 */       if (Trace.isOn) {
/* 1877 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl.JmsProducerDeliveryProperties", "<init>(JmsContextImpl)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void validateProperty(String name, Object value) throws JMSException {
/* 1884 */       if (Trace.isOn) {
/* 1885 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl.JmsProducerDeliveryProperties", "validateProperty(String, Object)", new Object[] { name, value });
/*      */       }
/*      */ 
/*      */       
/* 1889 */       this.context.validateProducerProperty(name, value);
/*      */       
/* 1891 */       if (Trace.isOn)
/* 1892 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProducerImpl.JmsProducerDeliveryProperties", "validateProperty(String, Object)"); 
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsProducerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */