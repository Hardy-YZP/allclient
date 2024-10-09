/*      */ package com.ibm.msg.client.jms.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.CSIException;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.commonservices.workqueue.WorkQueueManager;
/*      */ import com.ibm.msg.client.jms.JmsDestination;
/*      */ import com.ibm.msg.client.jms.JmsMessage;
/*      */ import com.ibm.msg.client.jms.JmsMessageProducer;
/*      */ import com.ibm.msg.client.jms.admin.JmsDestinationImpl;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessageProducer;
/*      */ import com.ibm.msg.client.provider.ProviderSession;
/*      */ import java.io.IOException;
/*      */ import java.io.NotSerializableException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.concurrent.locks.ReentrantLock;
/*      */ import javax.jms.CompletionListener;
/*      */ import javax.jms.Destination;
/*      */ import javax.jms.IllegalStateException;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.Message;
/*      */ import javax.jms.Session;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JmsMessageProducerImpl
/*      */   extends JmsPropertyContextImpl
/*      */   implements JmsMessageProducer
/*      */ {
/*      */   private static final long serialVersionUID = -1195401497038673446L;
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsMessageProducerImpl.java";
/*      */   private static final int defaultDeliveryMode = 2;
/*      */   private static final int defaultPriority = 4;
/*      */   private static final long defaultTimeToLive = 0L;
/*      */   private static final long defaultDeliveryDelay = 0L;
/*      */   private static final boolean defaultDisableMessageTimestamp = false;
/*      */   private static final boolean defaultDisableMessageID = false;
/*      */   private JmsDestinationImpl destination;
/*      */   private ProviderMessageProducer providerProducer;
/*      */   
/*      */   static {
/*   69 */     if (Trace.isOn) {
/*   70 */       Trace.data("com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsMessageProducerImpl.java");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  126 */     PropertyStore.register("com.ibm.msg.client.jms.ByteStreamReadOnlyAfterSend", false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected JmsMessageProducerImpl(Destination dest, JmsSessionImpl session) throws JMSException {
/*  131 */     super((Map<String, Object>)session, true);
/*  132 */     if (Trace.isOn) {
/*  133 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "<init>(Destination,JmsSessionImpl)", new Object[] { dest, session });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  140 */     setIntProperty("deliveryMode", 2);
/*  141 */     setIntProperty("priority", 4);
/*  142 */     setLongProperty("timeToLive", 0L);
/*  143 */     setLongProperty("deliveryDelay", 0L);
/*      */     
/*  145 */     setBooleanProperty("XMSC_DISABLE_MSG_TS", false);
/*  146 */     setBooleanProperty("XMSC_DISABLE_MSG_ID", false);
/*      */ 
/*      */     
/*      */     try {
/*  150 */       setLongProperty("XMSC_OBJECT_IDENTITY", System.identityHashCode(this));
/*      */     }
/*  152 */     catch (JMSException e) {
/*  153 */       if (Trace.isOn) {
/*  154 */         Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "<init>(Destination,JmsSessionImpl)", "Caught expected exception", e);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  161 */     checkNotAlienDestination(dest);
/*      */ 
/*      */     
/*  164 */     this.destination = (JmsDestinationImpl)dest;
/*      */     
/*  166 */     this.session = session;
/*  167 */     this.sessionSyncLock = session.getSessionSyncLock();
/*      */     
/*  169 */     JmsConnectionMetaDataImpl md = (JmsConnectionMetaDataImpl)session.getConnection().getMetaData();
/*  170 */     this.isCICSUnmanaged = md.doesConnectionSupport("XMSC_CAPABILITY_NATIVE_CICS_UNMANAGED");
/*  171 */     this.isIMS = md.doesConnectionSupport("XMSC_CAPABILITY_NATIVE_IMS");
/*      */ 
/*      */     
/*  174 */     this.byteStreamReadOnlyAfterSend = PropertyStore.getBooleanPropertyObject("com.ibm.msg.client.jms.ByteStreamReadOnlyAfterSend").booleanValue();
/*      */     
/*  176 */     if (Trace.isOn)
/*  177 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "<init>(Destination,JmsSessionImpl)"); 
/*      */   }
/*      */   private JmsSessionImpl session = null;
/*      */   protected State state = new State(1);
/*      */   private final ProducerProperties producerProps = new ProducerProperties();
/*      */   private boolean byteStreamReadOnlyAfterSend = false;
/*      */   private JmsSessionImpl.ReentrantDoubleLock sessionSyncLock;
/*      */   private final boolean isCICSUnmanaged;
/*      */   private final boolean isIMS;
/*      */   
/*      */   public void close() throws JMSException {
/*  188 */     if (Trace.isOn) {
/*  189 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "close()");
/*      */     }
/*  191 */     close(false);
/*  192 */     if (Trace.isOn) {
/*  193 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "close()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close(boolean closingFromSession) throws JMSException {
/*  204 */     if (Trace.isOn) {
/*  205 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "close(boolean)", new Object[] {
/*  206 */             Boolean.valueOf(closingFromSession)
/*      */           });
/*      */     }
/*  209 */     JmsTls jTls = JmsTls.getInstance();
/*  210 */     if (jTls.inCompletionListener() && jTls.completionListenerSession() == this.session) {
/*  211 */       HashMap<String, Object> inserts = new HashMap<>();
/*  212 */       inserts.put("XMSC_INSERT_METHOD", "close()");
/*  213 */       inserts.put("XMSC_INSERT_NAME", "completion");
/*      */       
/*  215 */       IllegalStateException ise = (IllegalStateException)JmsErrorUtils.createException("JMSCC0012", inserts);
/*  216 */       if (Trace.isOn) {
/*  217 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "close(boolean)", (Throwable)ise);
/*      */       }
/*      */       
/*  220 */       throw ise;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  225 */     if (!this.session.isAsync()) {
/*  226 */       this.sessionSyncLock.getExclusiveLock();
/*      */     }
/*      */     try {
/*  229 */       ReentrantLock onMessageLock = this.session.getOnMessageLock();
/*      */       
/*  231 */       onMessageLock.lock();
/*      */       
/*      */       try {
/*  234 */         if (this.state.close()) {
/*  235 */           if (Trace.isOn) {
/*  236 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "close(boolean)", 1);
/*      */           }
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/*  242 */         if (this.providerProducer != null) {
/*  243 */           this.providerProducer.close(closingFromSession);
/*      */ 
/*      */           
/*  246 */           this.session.removeProducer(this);
/*      */         } 
/*      */       } finally {
/*      */         
/*  250 */         if (Trace.isOn) {
/*  251 */           Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "close(boolean)", 1);
/*      */         }
/*      */         
/*  254 */         onMessageLock.unlock();
/*      */       } 
/*      */     } finally {
/*      */       
/*  258 */       if (Trace.isOn) {
/*  259 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "close(boolean)", 2);
/*      */       }
/*      */       
/*  262 */       if (this.sessionSyncLock.hasExclusiveLock()) {
/*  263 */         this.sessionSyncLock.unlockExclusiveLock();
/*      */       }
/*      */     } 
/*      */     
/*  267 */     if (Trace.isOn) {
/*  268 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "close(boolean)", 2);
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
/*      */   public int getDeliveryMode() throws JMSException {
/*  280 */     this.state.checkNotClosed("JMSCC0026");
/*      */     
/*  282 */     int dm = getIntProperty("deliveryMode");
/*      */     
/*  284 */     if (Trace.isOn) {
/*  285 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "getDeliveryMode()", "getter", 
/*  286 */           Integer.valueOf(dm));
/*      */     }
/*  288 */     return dm;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Destination getDestination() throws JMSException {
/*  297 */     this.state.checkNotClosed("JMSCC0026");
/*      */     
/*  299 */     if (Trace.isOn) {
/*  300 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "getDestination()", "getter", this.destination);
/*      */     }
/*      */     
/*  303 */     return (Destination)this.destination;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getDisableMessageID() throws JMSException {
/*  312 */     this.state.checkNotClosed("JMSCC0026");
/*      */     
/*  314 */     boolean disableMessageID = getBooleanProperty("XMSC_DISABLE_MSG_ID");
/*      */     
/*  316 */     if (Trace.isOn) {
/*  317 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "getDisableMessageID()", "getter", 
/*  318 */           Boolean.valueOf(disableMessageID));
/*      */     }
/*  320 */     return disableMessageID;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getDisableMessageTimestamp() throws JMSException {
/*  329 */     this.state.checkNotClosed("JMSCC0026");
/*      */     
/*  331 */     boolean disableMessageTimestamp = getBooleanProperty("XMSC_DISABLE_MSG_TS");
/*      */     
/*  333 */     if (Trace.isOn) {
/*  334 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "getDisableMessageTimestamp()", "getter", 
/*  335 */           Boolean.valueOf(disableMessageTimestamp));
/*      */     }
/*  337 */     return disableMessageTimestamp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPriority() throws JMSException {
/*  346 */     this.state.checkNotClosed("JMSCC0026");
/*      */     
/*  348 */     int pri = getIntProperty("priority");
/*      */     
/*  350 */     if (Trace.isOn) {
/*  351 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "getPriority()", "getter", 
/*  352 */           Integer.valueOf(pri));
/*      */     }
/*  354 */     return pri;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getTimeToLive() throws JMSException {
/*  363 */     this.state.checkNotClosed("JMSCC0026");
/*      */     
/*  365 */     long ttl = getLongProperty("timeToLive");
/*      */     
/*  367 */     if (Trace.isOn) {
/*  368 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "getTimeToLive()", "getter", 
/*  369 */           Long.valueOf(ttl));
/*      */     }
/*  371 */     return ttl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDeliveryMode(int deliveryMode) throws JMSException {
/*  379 */     if (Trace.isOn) {
/*  380 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "setDeliveryMode(int)", "setter", 
/*  381 */           Integer.valueOf(deliveryMode));
/*      */     }
/*      */     
/*  384 */     this.state.checkNotClosed("JMSCC0026");
/*      */ 
/*      */     
/*  387 */     setIntProperty("deliveryMode", deliveryMode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void validateDeliveryMode(int deliveryMode) throws JMSException {
/*  395 */     if (Trace.isOn) {
/*  396 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "validateDeliveryMode(int)", new Object[] {
/*  397 */             Integer.valueOf(deliveryMode)
/*      */           });
/*      */     }
/*  400 */     if (deliveryMode != 1 && deliveryMode != 2) {
/*  401 */       HashMap<String, Object> inserts = new HashMap<>();
/*  402 */       inserts.put("XMSC_INSERT_VALUE", "" + deliveryMode);
/*  403 */       inserts.put("XMSC_INSERT_NAME", "JMSDeliveryMode");
/*  404 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0005", inserts);
/*  405 */       if (Trace.isOn) {
/*  406 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "validateDeliveryMode(int)", (Throwable)je);
/*      */       }
/*      */       
/*  409 */       throw je;
/*      */     } 
/*      */     
/*  412 */     if (Trace.isOn) {
/*  413 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "validateDeliveryMode(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDisableMessageID(boolean disableMsgID) throws JMSException {
/*  424 */     if (Trace.isOn) {
/*  425 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "setDisableMessageID(boolean)", "setter", 
/*  426 */           Boolean.valueOf(disableMsgID));
/*      */     }
/*      */     
/*  429 */     setBooleanProperty("XMSC_DISABLE_MSG_ID", disableMsgID);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDisableMessageTimestamp(boolean disableMsgTS) throws JMSException {
/*  438 */     if (Trace.isOn) {
/*  439 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "setDisableMessageTimestamp(boolean)", "setter", 
/*  440 */           Boolean.valueOf(disableMsgTS));
/*      */     }
/*      */     
/*  443 */     setBooleanProperty("XMSC_DISABLE_MSG_TS", disableMsgTS);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPriority(int priority) throws JMSException {
/*  452 */     if (Trace.isOn) {
/*  453 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "setPriority(int)", "setter", 
/*  454 */           Integer.valueOf(priority));
/*      */     }
/*      */     
/*  457 */     this.state.checkNotClosed("JMSCC0026");
/*      */ 
/*      */     
/*  460 */     setIntProperty("priority", priority);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void validatePriority(int pri) throws JMSException {
/*  470 */     if (Trace.isOn) {
/*  471 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "validatePriority(int)", new Object[] {
/*  472 */             Integer.valueOf(pri)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  478 */     boolean valid = (pri >= 0 && pri <= 9) ? true : this.providerProducer.providerPriorityValidate(pri);
/*      */     
/*  480 */     if (!valid) {
/*  481 */       HashMap<String, Object> inserts = new HashMap<>();
/*  482 */       inserts.put("XMSC_INSERT_VALUE", "" + pri);
/*  483 */       inserts.put("XMSC_INSERT_NAME", "JMSPriority");
/*  484 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0005", inserts);
/*  485 */       if (Trace.isOn) {
/*  486 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "validatePriority(int)", (Throwable)je);
/*      */       }
/*      */       
/*  489 */       throw je;
/*      */     } 
/*      */     
/*  492 */     if (Trace.isOn) {
/*  493 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "validatePriority(int)");
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
/*      */   private void validateDeliveryDelay(long delay) throws JMSException {
/*  505 */     if (Trace.isOn) {
/*  506 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "validateDeliveryDelay(long)", new Object[] {
/*  507 */             Long.valueOf(delay)
/*      */           });
/*      */     }
/*  510 */     boolean valid = (delay >= 0L);
/*      */     
/*  512 */     if (!valid) {
/*  513 */       HashMap<String, Object> inserts = new HashMap<>();
/*  514 */       inserts.put("XMSC_INSERT_VALUE", "" + delay);
/*  515 */       inserts.put("XMSC_INSERT_NAME", "JMSDeliveryDelay");
/*  516 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0005", inserts);
/*  517 */       if (Trace.isOn) {
/*  518 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "validateDeliveryDelay(long)", (Throwable)je);
/*      */       }
/*      */       
/*  521 */       throw je;
/*      */     } 
/*      */     
/*  524 */     if (Trace.isOn) {
/*  525 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "validateDeliveryDelay(long)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTimeToLive(long timeToLive) throws JMSException {
/*  536 */     if (Trace.isOn) {
/*  537 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "setTimeToLive(long)", "setter", 
/*  538 */           Long.valueOf(timeToLive));
/*      */     }
/*      */     
/*  541 */     this.state.checkNotClosed("JMSCC0026");
/*      */     
/*  543 */     setLongProperty("timeToLive", timeToLive);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void validateTimeToLive(long timeToLive) throws JMSException {
/*  554 */     if (Trace.isOn) {
/*  555 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "validateTimeToLive(long)", new Object[] {
/*  556 */             Long.valueOf(timeToLive)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  564 */     boolean valid = (timeToLive >= 0L) ? true : this.providerProducer.providerTimeToLiveValidate(timeToLive);
/*      */     
/*  566 */     if (!valid) {
/*  567 */       HashMap<String, Object> inserts = new HashMap<>();
/*  568 */       inserts.put("XMSC_INSERT_VALUE", "" + timeToLive);
/*  569 */       inserts.put("XMSC_INSERT_NAME", "timeToLive");
/*  570 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0005", inserts);
/*  571 */       if (Trace.isOn) {
/*  572 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "validateTimeToLive(long)", (Throwable)je);
/*      */       }
/*      */       
/*  575 */       throw je;
/*      */     } 
/*      */     
/*  578 */     if (Trace.isOn) {
/*  579 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "validateTimeToLive(long)");
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
/*      */   private void crossValidateDeliveryDelay(long deliveryDelay, long timeToLive, Message message) throws JMSException {
/*  595 */     if (Trace.isOn) {
/*  596 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "crossValidateDeliveryDelay(long,long,Message)", new Object[] {
/*  597 */             Long.valueOf(deliveryDelay), 
/*  598 */             Long.valueOf(timeToLive), message
/*      */           });
/*      */     }
/*  601 */     if (deliveryDelay > 0L) {
/*  602 */       if (timeToLive > 0L && deliveryDelay >= timeToLive) {
/*      */         
/*  604 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0113", null);
/*  605 */         if (Trace.isOn) {
/*  606 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "crossValidateDeliveryDelay(long,long,Message)", (Throwable)je, 1);
/*      */         }
/*      */         
/*  609 */         throw je;
/*      */       } 
/*      */       
/*  612 */       if (message.propertyExists("JMS_IBM_Retain") && message
/*  613 */         .getIntProperty("JMS_IBM_Retain") == 1) {
/*      */         
/*  615 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0114", null);
/*  616 */         if (Trace.isOn) {
/*  617 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "crossValidateDeliveryDelay(long,long,Message)", (Throwable)je, 2);
/*      */         }
/*      */         
/*  620 */         throw je;
/*      */       } 
/*      */       
/*  623 */       if (message.getStringProperty("JMSXGroupID") != null || message
/*  624 */         .getStringProperty("JMSXGroupSeq") != null) {
/*      */         
/*  626 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0115", null);
/*  627 */         if (Trace.isOn) {
/*  628 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "crossValidateDeliveryDelay(long,long,Message)", (Throwable)je, 3);
/*      */         }
/*      */         
/*  631 */         throw je;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  636 */     if (Trace.isOn) {
/*  637 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "crossValidateDeliveryDelay(long,long,Message)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ProviderMessageProducer getProviderProducer() {
/*  647 */     if (Trace.isOn) {
/*  648 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "getProviderProducer()", "getter", this.providerProducer);
/*      */     }
/*      */     
/*  651 */     return this.providerProducer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProviderProducer(ProviderMessageProducer providerProducer) {
/*  659 */     if (Trace.isOn) {
/*  660 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "setProviderProducer(ProviderMessageProducer)", "setter", providerProducer);
/*      */     }
/*      */     
/*  663 */     this.providerProducer = providerProducer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void validateProperty(String name, Object value) throws JMSException {
/*  673 */     if (Trace.isOn) {
/*  674 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "validateProperty(String,Object)", new Object[] { name, value });
/*      */     }
/*      */ 
/*      */     
/*  678 */     this.state.checkNotClosed("JMSCC0026");
/*      */     
/*  680 */     boolean isValid = true;
/*      */     
/*  682 */     if (name.equals("deliveryMode")) {
/*  683 */       isValid = value instanceof Integer;
/*  684 */       validateDeliveryMode(((Integer)value).intValue());
/*      */     }
/*  686 */     else if (name.equals("timeToLive")) {
/*  687 */       isValid = value instanceof Long;
/*  688 */       validateTimeToLive(((Long)value).longValue());
/*      */     }
/*  690 */     else if (name.equals("priority")) {
/*  691 */       isValid = value instanceof Integer;
/*  692 */       validatePriority(((Integer)value).intValue());
/*      */     }
/*  694 */     else if (name.equals("deliveryDelay")) {
/*  695 */       isValid = value instanceof Long;
/*  696 */       validateDeliveryDelay(((Long)value).longValue());
/*      */     } 
/*      */     
/*  699 */     if (!isValid) {
/*  700 */       HashMap<String, Object> inserts = new HashMap<>();
/*  701 */       inserts.put("XMSC_INSERT_VALUE", "" + value);
/*  702 */       inserts.put("XMSC_INSERT_NAME", name);
/*  703 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0005", inserts);
/*  704 */       if (Trace.isOn) {
/*  705 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "validateProperty(String,Object)", (Throwable)je);
/*      */       }
/*      */       
/*  708 */       throw je;
/*      */     } 
/*  710 */     if (Trace.isOn) {
/*  711 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "validateProperty(String,Object)");
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
/*      */   private void checkTemporaryDest(JmsDestinationImpl dest) throws JMSException {
/*  724 */     if (Trace.isOn) {
/*  725 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkTemporaryDest(JmsDestinationImpl)", new Object[] { dest });
/*      */     }
/*      */     
/*  728 */     boolean isDeleted = false;
/*  729 */     if (dest instanceof JmsTemporaryQueueImpl) {
/*  730 */       isDeleted = ((JmsTemporaryQueueImpl)dest).isDeleted();
/*      */     }
/*  732 */     else if (dest instanceof JmsTemporaryTopicImpl) {
/*  733 */       isDeleted = ((JmsTemporaryTopicImpl)dest).isDeleted();
/*      */     } 
/*      */     
/*  736 */     if (isDeleted) {
/*      */       
/*  738 */       HashMap<String, Object> inserts = new HashMap<>();
/*  739 */       inserts.put("XMSC_DESTINATION_NAME", dest.toString());
/*  740 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC3019", inserts);
/*      */       
/*  742 */       if (Trace.isOn) {
/*  743 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkTemporaryDest(JmsDestinationImpl)", (Throwable)je);
/*      */       }
/*      */       
/*  746 */       throw je;
/*      */     } 
/*      */     
/*  749 */     if (Trace.isOn) {
/*  750 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkTemporaryDest(JmsDestinationImpl)");
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
/*      */   private void sendMessage(Message message, JmsDestinationImpl dest) throws JMSException {
/*      */     JmsMessageImpl jmsMessage;
/*  770 */     if (Trace.isOn) {
/*  771 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "sendMessage(Message,JmsDestinationImpl)", new Object[] { message, dest });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  777 */     boolean alienMessage = isAlienMessage(message);
/*      */     
/*  779 */     if (alienMessage) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  785 */       jmsMessage = JmsMessageImpl.messageToJmsMessageImpl(this.session, message);
/*      */     }
/*      */     else {
/*      */       
/*  789 */       jmsMessage = (JmsMessageImpl)message;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  795 */     ProviderMessage providerMessage = jmsMessage.getProviderMessageFactory().convertMessageUsingSession(jmsMessage.getProviderMessage(), this.session
/*  796 */         .getProviderSession());
/*      */ 
/*      */     
/*  799 */     int deliveryMode = this.producerProps.getEffectiveDeliveryMode();
/*  800 */     int priority = this.producerProps.getEffectivePriority();
/*  801 */     long timeToLive = this.producerProps.getEffectiveTTL();
/*  802 */     long deliveryDelay = this.producerProps.getDeliveryDelay();
/*      */ 
/*      */     
/*  805 */     providerMessage.setJMSDeliveryMode(deliveryMode);
/*  806 */     providerMessage.setJMSPriority(priority);
/*  807 */     providerMessage.setJMSDestinationAsString(JmsDestinationImplProxy.getProviderDestination(dest).toURI());
/*      */ 
/*      */     
/*  810 */     boolean disableMessageID = getBooleanProperty("XMSC_DISABLE_MSG_ID");
/*  811 */     if (!disableMessageID) {
/*      */       
/*  813 */       providerMessage.setJMSMessageID(this.session.getProviderSession().createMessageID());
/*      */     } else {
/*      */       
/*  816 */       providerMessage.setJMSMessageID(null);
/*      */     } 
/*      */ 
/*      */     
/*  820 */     boolean disableMessageTimestamp = getBooleanProperty("XMSC_DISABLE_MSG_TS");
/*  821 */     long timeNow = 0L;
/*      */     
/*  823 */     if (timeToLive != 0L || !disableMessageTimestamp) {
/*  824 */       timeNow = System.currentTimeMillis();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  830 */     if (timeToLive == 0L) {
/*  831 */       providerMessage.setJMSExpiration(0L);
/*      */     } else {
/*      */       
/*  834 */       providerMessage.setJMSExpiration(timeNow + timeToLive);
/*      */     } 
/*      */     
/*  837 */     if (deliveryDelay > 0L) {
/*  838 */       providerMessage.setJMSDeliveryDelay(deliveryDelay);
/*      */     }
/*  840 */     providerMessage.setJMSDeliveryTime(System.currentTimeMillis() + deliveryDelay);
/*      */ 
/*      */     
/*  843 */     if (!disableMessageTimestamp) {
/*      */       
/*  845 */       providerMessage.setJMSTimestamp(timeNow);
/*      */     } else {
/*      */       
/*  848 */       providerMessage.setJMSTimestamp(0L);
/*      */     } 
/*      */ 
/*      */     
/*  852 */     ProviderDestination providerDestination = (this.destination == null) ? JmsDestinationImplProxy.getProviderDestination(dest) : null;
/*      */ 
/*      */     
/*  855 */     this.providerProducer.send(providerDestination, providerMessage);
/*      */     
/*  857 */     if (alienMessage) {
/*  858 */       boolean needsUpdate = true;
/*      */       
/*  860 */       if (message instanceof JmsMessage && 
/*  861 */         jmsMessage.equals(((JmsMessage)message).getDelegate())) {
/*  862 */         needsUpdate = false;
/*      */       }
/*      */       
/*  865 */       if (needsUpdate)
/*      */       {
/*  867 */         updateAlienMessage(message, providerMessage, (Destination)dest);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  873 */     jmsMessage.invalidateToStringCache();
/*      */     
/*  875 */     if (Trace.isOn) {
/*  876 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "sendMessage(Message,JmsDestinationImpl)");
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
/*      */   private boolean isAlienMessage(Message message) throws JMSException {
/*  890 */     if (Trace.isOn) {
/*  891 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "isAlienMessage(Message)", new Object[] { message });
/*      */     }
/*      */ 
/*      */     
/*  895 */     boolean result = false;
/*      */     
/*  897 */     boolean sameType = message instanceof JmsMessageImpl;
/*  898 */     if (!sameType) {
/*      */       
/*  900 */       result = true;
/*      */     } else {
/*      */       
/*  903 */       boolean sameConnection = ((JmsMessageImpl)message).getConnectionTypeName().equals(this.session.getConnectionTypeName());
/*      */       
/*  905 */       if (!sameConnection) {
/*      */         
/*  907 */         result = true;
/*      */       }
/*      */       else {
/*      */         
/*  911 */         ProviderSession providerSession = this.session.getProviderSession();
/*  912 */         result = providerSession.isMessageAlien(((JmsMessageImpl)message).getProviderMessage());
/*      */       } 
/*      */     } 
/*      */     
/*  916 */     if (Trace.isOn) {
/*  917 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "isAlienMessage(Message)", 
/*  918 */           Boolean.valueOf(result));
/*      */     }
/*  920 */     return result;
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
/*      */   private void updateAlienMessage(Message message, ProviderMessage providerMessage, Destination dest) throws JMSException {
/*  932 */     if (Trace.isOn) {
/*  933 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "updateAlienMessage(Message,ProviderMessage,Destination)", new Object[] { message, providerMessage, dest });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  938 */     message.setJMSPriority(providerMessage.getJMSPriority().intValue());
/*  939 */     message.setJMSDeliveryMode(providerMessage.getJMSDeliveryMode().intValue());
/*  940 */     message.setJMSMessageID(providerMessage.getJMSMessageID());
/*  941 */     message.setJMSExpiration(providerMessage.getJMSExpiration().longValue());
/*  942 */     message.setJMSTimestamp(providerMessage.getJMSTimestamp().longValue());
/*  943 */     message.setJMSDestination(dest);
/*      */ 
/*      */     
/*      */     try {
/*  947 */       message.setStringProperty("JMSXAppID", providerMessage.getStringProperty("JMSXAppID"));
/*  948 */       message.setStringProperty("JMSXUserID", providerMessage.getStringProperty("JMSXUserID"));
/*  949 */       message.setStringProperty("JMSXGroupID", providerMessage.getStringProperty("JMSXGroupID"));
/*  950 */       message.setIntProperty("JMSXGroupSeq", providerMessage.getIntProperty("JMSXGroupSeq"));
/*      */     }
/*  952 */     catch (Exception e) {
/*  953 */       if (Trace.isOn) {
/*  954 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "updateAlienMessage(Message,ProviderMessage,Destination)", e, 1);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  962 */       message.setStringProperty("JMS_IBM_PutDate", providerMessage.getStringProperty("JMS_IBM_PutDate"));
/*  963 */       message.setStringProperty("JMS_IBM_PutTime", providerMessage.getStringProperty("JMS_IBM_PutTime"));
/*  964 */       message.setIntProperty("JMS_IBM_PutApplType", providerMessage.getIntProperty("JMS_IBM_PutApplType"));
/*      */     }
/*  966 */     catch (Exception e) {
/*  967 */       if (Trace.isOn) {
/*  968 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "updateAlienMessage(Message,ProviderMessage,Destination)", e, 2);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  973 */     if (Trace.isOn) {
/*  974 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "updateAlienMessage(Message,ProviderMessage,Destination)");
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
/*      */   private void checkNotIdentifiedProducer() throws UnsupportedOperationException {
/*  987 */     if (Trace.isOn) {
/*  988 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotIdentifiedProducer()");
/*      */     }
/*      */     
/*  991 */     if (this.destination != null) {
/*      */       
/*  993 */       UnsupportedOperationException uoe = (UnsupportedOperationException)JmsErrorUtils.createException("JMSCC0030", null);
/*  994 */       if (Trace.isOn) {
/*  995 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotIdentifiedProducer()", uoe);
/*      */       }
/*      */       
/*  998 */       throw uoe;
/*      */     } 
/* 1000 */     if (Trace.isOn) {
/* 1001 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotIdentifiedProducer()");
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
/*      */   private void checkNotUnidentifiedProducer() throws UnsupportedOperationException {
/* 1014 */     if (Trace.isOn) {
/* 1015 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotUnidentifiedProducer()");
/*      */     }
/*      */     
/* 1018 */     if (this.destination == null) {
/*      */       
/* 1020 */       UnsupportedOperationException uoe = (UnsupportedOperationException)JmsErrorUtils.createException("JMSCC0029", null);
/* 1021 */       if (Trace.isOn) {
/* 1022 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotUnidentifiedProducer()", uoe);
/*      */       }
/*      */       
/* 1025 */       throw uoe;
/*      */     } 
/* 1027 */     if (Trace.isOn) {
/* 1028 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotUnidentifiedProducer()");
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
/*      */   private void checkNotNullMessage(Message message) throws JMSException {
/* 1041 */     if (Trace.isOn) {
/* 1042 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotNullMessage(Message)", new Object[] { message });
/*      */     }
/*      */     
/* 1045 */     if (message == null) {
/* 1046 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0098", null);
/* 1047 */       if (Trace.isOn) {
/* 1048 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotNullMessage(Message)", (Throwable)je);
/*      */       }
/*      */       
/* 1051 */       throw je;
/*      */     } 
/* 1053 */     if (Trace.isOn) {
/* 1054 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotNullMessage(Message)");
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
/*      */   private void checkNotNullDestination(Destination destination) throws JMSException {
/* 1067 */     if (Trace.isOn) {
/* 1068 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotNullDestination(Destination)", new Object[] { destination });
/*      */     }
/*      */     
/* 1071 */     if (destination == null) {
/* 1072 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0085", null);
/*      */       
/* 1074 */       if (Trace.isOn) {
/* 1075 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotNullDestination(Destination)", (Throwable)je);
/*      */       }
/*      */       
/* 1078 */       throw je;
/*      */     } 
/* 1080 */     if (Trace.isOn) {
/* 1081 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotNullDestination(Destination)");
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
/*      */   private void checkNotNullCompletionListener(CompletionListener listener) throws IllegalArgumentException {
/* 1094 */     if (Trace.isOn) {
/* 1095 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotNullCompletionListener(CompletionListener)", new Object[] { listener });
/*      */     }
/*      */     
/* 1098 */     if (listener == null) {
/* 1099 */       IllegalArgumentException iae = (IllegalArgumentException)JmsErrorUtils.createException("JMSCC0031", null);
/*      */ 
/*      */       
/* 1102 */       if (Trace.isOn) {
/* 1103 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotNullCompletionListener(CompletionListener)", iae);
/*      */       }
/*      */       
/* 1106 */       throw iae;
/*      */     } 
/* 1108 */     if (Trace.isOn) {
/* 1109 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotNullCompletionListener(CompletionListener)");
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
/*      */   private void checkNotAlienDestination(Destination destination) throws JMSException {
/* 1123 */     if (Trace.isOn) {
/* 1124 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotAlienDestination(Destination)", new Object[] { destination });
/*      */     }
/*      */     
/* 1127 */     if (destination != null && !(destination instanceof JmsDestinationImpl)) {
/* 1128 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1129 */       inserts.put("XMSC_DESTINATION_NAME", destination.toString());
/* 1130 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0099", inserts);
/*      */       
/* 1132 */       if (Trace.isOn) {
/* 1133 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotAlienDestination(Destination)", (Throwable)je);
/*      */       }
/*      */       
/* 1136 */       throw je;
/*      */     } 
/* 1138 */     if (Trace.isOn) {
/* 1139 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotAlienDestination(Destination)");
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
/*      */   private void checkNotMixedDomain(Destination dest) throws JMSException {
/* 1152 */     if (Trace.isOn) {
/* 1153 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotMixedDomain(Destination)", new Object[] { dest });
/*      */     }
/*      */     
/* 1156 */     if ((dest instanceof javax.jms.Queue && this instanceof javax.jms.TopicPublisher) || (dest instanceof javax.jms.Topic && this instanceof javax.jms.QueueSender)) {
/* 1157 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1158 */       String method = (dest instanceof javax.jms.Queue) ? "send(Queue)" : "send(Topic)";
/* 1159 */       inserts.put("XMSC_INSERT_METHOD", method);
/* 1160 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 1161 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1112", inserts);
/* 1162 */       if (Trace.isOn) {
/* 1163 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotMixedDomain(Destination)", (Throwable)je);
/*      */       }
/*      */       
/* 1166 */       throw je;
/*      */     } 
/* 1168 */     if (Trace.isOn) {
/* 1169 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "checkNotMixedDomain(Destination)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class ProducerProperties
/*      */   {
/*      */     private static final int unsetDeliveryMode = -2147483648;
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int unsetPriority = -2147483648;
/*      */ 
/*      */ 
/*      */     
/*      */     private static final long unsetTTL = -2147483648L;
/*      */ 
/*      */     
/*      */     private JmsDestination dest;
/*      */ 
/*      */     
/* 1192 */     private int inDeliveryModeOverride = Integer.MIN_VALUE;
/*      */     
/* 1194 */     private int inPriorityOverride = Integer.MIN_VALUE;
/*      */     
/* 1196 */     private long inTTLOverride = -2147483648L;
/*      */ 
/*      */     
/* 1199 */     private int inDeliveryMode = Integer.MIN_VALUE;
/*      */     
/* 1201 */     private int inPriority = Integer.MIN_VALUE;
/*      */     
/* 1203 */     private long inTTL = -2147483648L;
/*      */     
/* 1205 */     private long deliveryDelay = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     ProducerProperties() {
/* 1211 */       if (Trace.isOn) {
/* 1212 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.ProducerProperties", "<init>()");
/*      */       }
/*      */       
/* 1215 */       if (Trace.isOn) {
/* 1216 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.ProducerProperties", "<init>()");
/*      */       }
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
/*      */     void update(JmsDestination destination, int deliveryMode, int priority, long ttl, long deliveryDelay) throws JMSException {
/* 1233 */       if (Trace.isOn) {
/* 1234 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.ProducerProperties", "update(JmsDestination,int,int,long,long)", new Object[] { destination, 
/*      */               
/* 1236 */               Integer.valueOf(deliveryMode), Integer.valueOf(priority), Long.valueOf(ttl), 
/* 1237 */               Long.valueOf(deliveryDelay) });
/*      */       }
/*      */       
/* 1240 */       this.dest = destination;
/*      */ 
/*      */ 
/*      */       
/* 1244 */       this.inDeliveryMode = deliveryMode;
/* 1245 */       this.inPriority = priority;
/* 1246 */       this.inTTL = ttl;
/*      */       
/* 1248 */       this.inDeliveryModeOverride = Integer.MIN_VALUE;
/* 1249 */       this.inPriorityOverride = Integer.MIN_VALUE;
/* 1250 */       this.inTTLOverride = -2147483648L;
/* 1251 */       this.deliveryDelay = deliveryDelay;
/*      */       
/* 1253 */       calcOverrides();
/* 1254 */       if (Trace.isOn) {
/* 1255 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.ProducerProperties", "update(JmsDestination,int,int,long,long)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void calcOverrides() throws JMSException {
/* 1267 */       if (Trace.isOn) {
/* 1268 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.ProducerProperties", "calcOverrides()");
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
/* 1285 */       int dlv = this.dest.getIntProperty("deliveryMode");
/* 1286 */       if (dlv == 1 || dlv == 2) {
/* 1287 */         this.inDeliveryModeOverride = dlv;
/*      */       }
/*      */       
/* 1290 */       int pri = this.dest.getIntProperty("priority");
/* 1291 */       if (pri != -2) {
/* 1292 */         this.inPriorityOverride = pri;
/*      */       }
/*      */       
/* 1295 */       long ttl = this.dest.getLongProperty("timeToLive");
/* 1296 */       if (ttl != -2L) {
/* 1297 */         this.inTTLOverride = ttl;
/*      */       }
/*      */       
/* 1300 */       if (Trace.isOn) {
/* 1301 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.ProducerProperties", "calcOverrides()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int getEffectiveDeliveryMode() {
/* 1312 */       int effectiveDeliveryMode = Integer.MIN_VALUE;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1317 */       if (this.inDeliveryModeOverride != Integer.MIN_VALUE) {
/*      */         
/* 1319 */         effectiveDeliveryMode = this.inDeliveryModeOverride;
/*      */       }
/*      */       else {
/*      */         
/* 1323 */         effectiveDeliveryMode = this.inDeliveryMode;
/*      */       } 
/*      */       
/* 1326 */       if (effectiveDeliveryMode == Integer.MIN_VALUE) {
/* 1327 */         HashMap<String, String> info = new HashMap<>();
/* 1328 */         info.put("destination", this.dest.toString());
/* 1329 */         info.put("effectiveDeliveryMode", "" + effectiveDeliveryMode);
/* 1330 */         info.put("inDeliveryModeOverride", "" + this.inDeliveryModeOverride);
/* 1331 */         info.put("inDeliveryMode", "" + this.inDeliveryMode);
/* 1332 */         Trace.ffst(this, "getEffectiveDeliveryMode()", "XJ007001", info, null);
/*      */       } 
/*      */       
/* 1335 */       if (Trace.isOn) {
/* 1336 */         Trace.data(this, "com.ibm.msg.client.jms.internal.ProducerProperties", "getEffectiveDeliveryMode()", "getter", 
/* 1337 */             Integer.valueOf(effectiveDeliveryMode));
/*      */       }
/* 1339 */       return effectiveDeliveryMode;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int getEffectivePriority() {
/* 1348 */       int effectivePriority = Integer.MIN_VALUE;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1353 */       if (this.inPriorityOverride != Integer.MIN_VALUE) {
/*      */         
/* 1355 */         effectivePriority = this.inPriorityOverride;
/*      */       }
/*      */       else {
/*      */         
/* 1359 */         effectivePriority = this.inPriority;
/*      */       } 
/*      */       
/* 1362 */       if (effectivePriority == Integer.MIN_VALUE) {
/* 1363 */         HashMap<String, String> info = new HashMap<>();
/* 1364 */         info.put("destination", this.dest.toString());
/* 1365 */         info.put("effectivePriority", "" + effectivePriority);
/* 1366 */         info.put("inPriorityOverride", "" + this.inPriorityOverride);
/* 1367 */         info.put("inPriority", "" + this.inPriority);
/* 1368 */         Trace.ffst(this, "getEffectivePriority()", "XJ007002", info, null);
/*      */       } 
/*      */       
/* 1371 */       if (Trace.isOn) {
/* 1372 */         Trace.data(this, "com.ibm.msg.client.jms.internal.ProducerProperties", "getEffectivePriority()", "getter", 
/* 1373 */             Integer.valueOf(effectivePriority));
/*      */       }
/* 1375 */       return effectivePriority;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     long getEffectiveTTL() {
/* 1384 */       long effectiveTTL = -2147483648L;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1389 */       if (this.inTTLOverride != -2147483648L) {
/*      */         
/* 1391 */         effectiveTTL = this.inTTLOverride;
/*      */       }
/*      */       else {
/*      */         
/* 1395 */         effectiveTTL = this.inTTL;
/*      */       } 
/*      */       
/* 1398 */       if (effectiveTTL == -2147483648L) {
/* 1399 */         HashMap<String, String> info = new HashMap<>();
/* 1400 */         info.put("destination", this.dest.toString());
/* 1401 */         info.put("effectiveTTL", "" + effectiveTTL);
/* 1402 */         info.put("inTTLOverride", "" + this.inTTLOverride);
/* 1403 */         info.put("inTTL", "" + this.inTTL);
/* 1404 */         Trace.ffst(this, "getEffectiveTTL()", "XJ007003", info, null);
/*      */       } 
/*      */       
/* 1407 */       if (Trace.isOn) {
/* 1408 */         Trace.data(this, "com.ibm.msg.client.jms.internal.ProducerProperties", "getEffectiveTTL()", "getter", 
/* 1409 */             Long.valueOf(effectiveTTL));
/*      */       }
/* 1411 */       return effectiveTTL;
/*      */     }
/*      */     
/*      */     long getDeliveryDelay() {
/* 1415 */       if (Trace.isOn) {
/* 1416 */         Trace.data(this, "com.ibm.msg.client.jms.internal.ProducerProperties", "getDeliveryDelay()", "getter", 
/* 1417 */             Long.valueOf(this.deliveryDelay));
/*      */       }
/* 1419 */       return this.deliveryDelay;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 1426 */     if (Trace.isOn) {
/* 1427 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "writeObject(ObjectOutputStream)", new Object[] { out });
/*      */     }
/*      */     
/* 1430 */     NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsMessageProducer");
/* 1431 */     if (Trace.isOn) {
/* 1432 */       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "writeObject(ObjectOutputStream)", traceRet1);
/*      */     }
/*      */     
/* 1435 */     throw traceRet1;
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException {
/* 1439 */     if (Trace.isOn) {
/* 1440 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "readObject(ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */     
/* 1443 */     NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsMessageProducer");
/* 1444 */     if (Trace.isOn) {
/* 1445 */       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "readObject(ObjectInputStream)", traceRet1);
/*      */     }
/*      */     
/* 1448 */     throw traceRet1;
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
/*      */   public void send(Message message) throws JMSException {
/* 1471 */     if (Trace.isOn) {
/* 1472 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Message)", new Object[] { message });
/*      */     }
/*      */ 
/*      */     
/* 1476 */     int deliveryMode = getIntProperty("deliveryMode");
/* 1477 */     int priority = getIntProperty("priority");
/* 1478 */     long timeToLive = getLongProperty("timeToLive");
/* 1479 */     long deliveryDelay = getLongProperty("deliveryDelay");
/*      */ 
/*      */     
/* 1482 */     this.sessionSyncLock.getExclusiveLock();
/*      */ 
/*      */     
/*      */     try {
/* 1486 */       sendInternal(true, this.destination, message, deliveryMode, priority, timeToLive, deliveryDelay, (CompletionListener)null, false);
/*      */     }
/*      */     finally {
/*      */       
/* 1490 */       if (Trace.isOn) {
/* 1491 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Message)");
/*      */       }
/*      */       
/* 1494 */       if (this.sessionSyncLock.hasExclusiveLock()) {
/* 1495 */         this.sessionSyncLock.unlockExclusiveLock();
/*      */       }
/*      */     } 
/*      */     
/* 1499 */     if (Trace.isOn) {
/* 1500 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Message)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void send(Destination dest, Message message) throws JMSException {
/* 1511 */     if (Trace.isOn) {
/* 1512 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Destination,Message)", new Object[] { dest, message });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1517 */     checkNotNullDestination(dest);
/*      */ 
/*      */     
/* 1520 */     checkNotAlienDestination(dest);
/*      */ 
/*      */     
/* 1523 */     checkNotMixedDomain(dest);
/*      */     
/* 1525 */     int deliveryMode = getIntProperty("deliveryMode");
/* 1526 */     int priority = getIntProperty("priority");
/* 1527 */     long timeToLive = getLongProperty("timeToLive");
/* 1528 */     long deliveryDelay = getLongProperty("deliveryDelay");
/*      */ 
/*      */     
/* 1531 */     this.sessionSyncLock.getExclusiveLock();
/*      */ 
/*      */     
/*      */     try {
/* 1535 */       sendInternal(false, (JmsDestinationImpl)dest, message, deliveryMode, priority, timeToLive, deliveryDelay, (CompletionListener)null, false);
/*      */     } finally {
/*      */       
/* 1538 */       if (Trace.isOn) {
/* 1539 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Destination,Message)");
/*      */       }
/*      */       
/* 1542 */       if (this.sessionSyncLock.hasExclusiveLock()) {
/* 1543 */         this.sessionSyncLock.unlockExclusiveLock();
/*      */       }
/*      */     } 
/*      */     
/* 1547 */     if (Trace.isOn) {
/* 1548 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Destination,Message)");
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
/*      */   public void send(Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
/* 1560 */     if (Trace.isOn) {
/* 1561 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Message,int,int,long)", new Object[] { message, 
/* 1562 */             Integer.valueOf(deliveryMode), 
/* 1563 */             Integer.valueOf(priority), Long.valueOf(timeToLive) });
/*      */     }
/*      */     
/* 1566 */     long deliveryDelay = getLongProperty("deliveryDelay");
/*      */ 
/*      */     
/* 1569 */     this.sessionSyncLock.getExclusiveLock();
/*      */ 
/*      */     
/*      */     try {
/* 1573 */       sendInternal(true, this.destination, message, deliveryMode, priority, timeToLive, deliveryDelay, (CompletionListener)null, true);
/*      */     }
/*      */     finally {
/*      */       
/* 1577 */       if (Trace.isOn) {
/* 1578 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Message,int,int,long)");
/*      */       }
/*      */       
/* 1581 */       if (this.sessionSyncLock.hasExclusiveLock()) {
/* 1582 */         this.sessionSyncLock.unlockExclusiveLock();
/*      */       }
/*      */     } 
/*      */     
/* 1586 */     if (Trace.isOn) {
/* 1587 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Message,int,int,long)");
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
/*      */   public void send(Destination dest, Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
/* 1599 */     if (Trace.isOn) {
/* 1600 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Destination,Message,int,int,long)", new Object[] { dest, message, 
/*      */             
/* 1602 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), Long.valueOf(timeToLive) });
/*      */     }
/*      */ 
/*      */     
/* 1606 */     checkNotNullDestination(dest);
/*      */ 
/*      */     
/* 1609 */     checkNotAlienDestination(dest);
/*      */ 
/*      */     
/* 1612 */     checkNotMixedDomain(dest);
/*      */     
/* 1614 */     long deliveryDelay = getLongProperty("deliveryDelay");
/*      */ 
/*      */     
/* 1617 */     this.sessionSyncLock.getExclusiveLock();
/*      */ 
/*      */     
/*      */     try {
/* 1621 */       sendInternal(false, (JmsDestinationImpl)dest, message, deliveryMode, priority, timeToLive, deliveryDelay, (CompletionListener)null, true);
/*      */     } finally {
/*      */       
/* 1624 */       if (Trace.isOn) {
/* 1625 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Destination,Message,int,int,long)");
/*      */       }
/*      */       
/* 1628 */       if (this.sessionSyncLock.hasExclusiveLock()) {
/* 1629 */         this.sessionSyncLock.unlockExclusiveLock();
/*      */       }
/*      */     } 
/*      */     
/* 1633 */     if (Trace.isOn) {
/* 1634 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Destination,Message,int,int,long)");
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
/*      */   public void send(Message message, CompletionListener completionListener) throws JMSException {
/* 1647 */     if (Trace.isOn) {
/* 1648 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Message,CompletionListener)", new Object[] { message, completionListener });
/*      */     }
/*      */ 
/*      */     
/* 1652 */     this.session.connection.checkValidFnUsage(JmsConnectionImpl.JMS2Function.ASYNCHRONOUS_SEND);
/*      */ 
/*      */ 
/*      */     
/* 1656 */     if (this.isCICSUnmanaged || this.isIMS) {
/* 1657 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1658 */       inserts.put("XMSC_INSERT_METHOD", "send(Message,CompletionListener)");
/*      */       
/* 1660 */       String messageID = this.isCICSUnmanaged ? "JMSCC6001" : "JMSCC6011";
/* 1661 */       JMSException je = (JMSException)JmsErrorUtils.createException(messageID, inserts);
/*      */       
/* 1663 */       if (Trace.isOn) {
/* 1664 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Message,CompletionListener)", (Throwable)je, 1);
/*      */       }
/*      */       
/* 1667 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/* 1671 */     checkNotNullCompletionListener(completionListener);
/*      */     
/* 1673 */     int deliveryMode = getIntProperty("deliveryMode");
/* 1674 */     int priority = getIntProperty("priority");
/* 1675 */     long timeToLive = getLongProperty("timeToLive");
/* 1676 */     long deliveryDelay = getLongProperty("deliveryDelay");
/*      */     
/* 1678 */     getSyncLockForAsyncSend();
/*      */ 
/*      */     
/*      */     try {
/* 1682 */       sendInternal(true, this.destination, message, deliveryMode, priority, timeToLive, deliveryDelay, completionListener, false);
/*      */     } finally {
/*      */       
/* 1685 */       if (Trace.isOn) {
/* 1686 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Message,CompletionListener)");
/*      */       }
/*      */       
/* 1689 */       this.sessionSyncLock.unlockSharedLock();
/*      */     } 
/*      */     
/* 1692 */     if (Trace.isOn) {
/* 1693 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Message,CompletionListener)");
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
/*      */   public void send(Destination dest, Message message, CompletionListener completionListener) throws JMSException {
/* 1705 */     if (Trace.isOn) {
/* 1706 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Destination,Message,CompletionListener)", new Object[] { dest, message, completionListener });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1711 */     this.session.connection.checkValidFnUsage(JmsConnectionImpl.JMS2Function.ASYNCHRONOUS_SEND);
/*      */ 
/*      */ 
/*      */     
/* 1715 */     if (this.isCICSUnmanaged || this.isIMS) {
/* 1716 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1717 */       inserts.put("XMSC_INSERT_METHOD", "send(Destination,Message,CompletionListener)");
/*      */       
/* 1719 */       String messageID = this.isCICSUnmanaged ? "JMSCC6001" : "JMSCC6011";
/* 1720 */       JMSException je = (JMSException)JmsErrorUtils.createException(messageID, inserts);
/*      */       
/* 1722 */       if (Trace.isOn) {
/* 1723 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Destination,Message,CompletionListener)", (Throwable)je, 1);
/*      */       }
/*      */       
/* 1726 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/* 1730 */     checkNotNullCompletionListener(completionListener);
/*      */ 
/*      */     
/* 1733 */     checkNotNullDestination(dest);
/*      */ 
/*      */     
/* 1736 */     checkNotAlienDestination(dest);
/*      */ 
/*      */     
/* 1739 */     checkNotMixedDomain(dest);
/*      */     
/* 1741 */     int deliveryMode = getIntProperty("deliveryMode");
/* 1742 */     int priority = getIntProperty("priority");
/* 1743 */     long timeToLive = getLongProperty("timeToLive");
/* 1744 */     long deliveryDelay = getLongProperty("deliveryDelay");
/*      */ 
/*      */     
/* 1747 */     getSyncLockForAsyncSend();
/*      */ 
/*      */     
/*      */     try {
/* 1751 */       sendInternal(false, (JmsDestinationImpl)dest, message, deliveryMode, priority, timeToLive, deliveryDelay, completionListener, false);
/*      */     } finally {
/*      */       
/* 1754 */       if (Trace.isOn) {
/* 1755 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Destination,Message,CompletionListener)");
/*      */       }
/*      */       
/* 1758 */       this.sessionSyncLock.unlockSharedLock();
/*      */     } 
/*      */     
/* 1761 */     if (Trace.isOn) {
/* 1762 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Destination,Message,CompletionListener)");
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
/*      */   public void send(Message message, int deliveryMode, int priority, long timeToLive, CompletionListener completionListener) throws JMSException {
/* 1775 */     if (Trace.isOn) {
/* 1776 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Message,int,int,long,CompletionListener)", new Object[] { message, 
/*      */             
/* 1778 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), Long.valueOf(timeToLive), completionListener });
/*      */     }
/*      */ 
/*      */     
/* 1782 */     this.session.connection.checkValidFnUsage(JmsConnectionImpl.JMS2Function.ASYNCHRONOUS_SEND);
/*      */ 
/*      */ 
/*      */     
/* 1786 */     if (this.isCICSUnmanaged || this.isIMS) {
/* 1787 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1788 */       inserts.put("XMSC_INSERT_METHOD", "send(Message,int,int,long,CompletionListener)");
/*      */       
/* 1790 */       String messageID = this.isCICSUnmanaged ? "JMSCC6001" : "JMSCC6011";
/* 1791 */       JMSException je = (JMSException)JmsErrorUtils.createException(messageID, inserts);
/*      */       
/* 1793 */       if (Trace.isOn) {
/* 1794 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Message,int,int,long,CompletionListener)", (Throwable)je, 1);
/*      */       }
/*      */       
/* 1797 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/* 1801 */     checkNotNullCompletionListener(completionListener);
/*      */     
/* 1803 */     long deliveryDelay = getLongProperty("deliveryDelay");
/*      */ 
/*      */     
/* 1806 */     getSyncLockForAsyncSend();
/*      */ 
/*      */     
/*      */     try {
/* 1810 */       sendInternal(true, this.destination, message, deliveryMode, priority, timeToLive, deliveryDelay, completionListener, true);
/*      */     }
/*      */     finally {
/*      */       
/* 1814 */       if (Trace.isOn) {
/* 1815 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Message,int,int,long,CompletionListener)");
/*      */       }
/*      */       
/* 1818 */       this.sessionSyncLock.unlockSharedLock();
/*      */     } 
/*      */     
/* 1821 */     if (Trace.isOn) {
/* 1822 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Message,int,int,long,CompletionListener)");
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
/*      */   public void send(Destination dest, Message message, int deliveryMode, int priority, long timeToLive, CompletionListener completionListener) throws JMSException {
/* 1835 */     if (Trace.isOn) {
/* 1836 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Destination,Message,int,int,long,CompletionListener)", new Object[] { dest, message, 
/*      */             
/* 1838 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), Long.valueOf(timeToLive), completionListener });
/*      */     }
/*      */ 
/*      */     
/* 1842 */     this.session.connection.checkValidFnUsage(JmsConnectionImpl.JMS2Function.ASYNCHRONOUS_SEND);
/*      */ 
/*      */ 
/*      */     
/* 1846 */     if (this.isCICSUnmanaged || this.isIMS) {
/* 1847 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1848 */       inserts.put("XMSC_INSERT_METHOD", "send(Destination,Message,int,int,long,CompletionListener)");
/*      */       
/* 1850 */       String messageID = this.isCICSUnmanaged ? "JMSCC6001" : "JMSCC6011";
/* 1851 */       JMSException je = (JMSException)JmsErrorUtils.createException(messageID, inserts);
/*      */       
/* 1853 */       if (Trace.isOn) {
/* 1854 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Destination,Message,int,int,long,CompletionListener)", (Throwable)je, 1);
/*      */       }
/*      */       
/* 1857 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/* 1861 */     checkNotNullCompletionListener(completionListener);
/*      */ 
/*      */     
/* 1864 */     checkNotNullDestination(dest);
/*      */ 
/*      */     
/* 1867 */     checkNotAlienDestination(dest);
/*      */ 
/*      */     
/* 1870 */     checkNotMixedDomain(dest);
/*      */     
/* 1872 */     long deliveryDelay = getLongProperty("deliveryDelay");
/*      */ 
/*      */     
/* 1875 */     getSyncLockForAsyncSend();
/*      */ 
/*      */     
/*      */     try {
/* 1879 */       sendInternal(false, (JmsDestinationImpl)dest, message, deliveryMode, priority, timeToLive, deliveryDelay, completionListener, true);
/*      */     } finally {
/*      */       
/* 1882 */       if (Trace.isOn) {
/* 1883 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Destination,Message,int,int,long,CompletionListener)");
/*      */       }
/*      */       
/* 1886 */       this.sessionSyncLock.unlockSharedLock();
/*      */     } 
/*      */     
/* 1889 */     if (Trace.isOn) {
/* 1890 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "send(Destination,Message,int,int,long,CompletionListener)");
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
/*      */   void sendInternal(boolean inIdentifiedContext, JmsDestinationImpl dest, Message message, int deliveryMode, int priority, long timeToLive, long deliveryDelay, CompletionListener pCompletionListener, boolean validateProperties) throws JMSException {
/* 1920 */     if (Trace.isOn) {
/* 1921 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "sendInternal(boolean,JmsDestinationImpl,Message,int,int,long,long,CompletionListener,boolean)", new Object[] {
/*      */             
/* 1923 */             Boolean.valueOf(inIdentifiedContext), dest, message, 
/* 1924 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), Long.valueOf(timeToLive), 
/* 1925 */             Long.valueOf(deliveryDelay), pCompletionListener, Boolean.valueOf(validateProperties)
/*      */           });
/*      */     }
/* 1928 */     CompletionListener completionListener = (pCompletionListener == null) ? null : new JmsCompletionListenerWrapper(pCompletionListener, (Session)this.session);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1937 */       this.state.checkNotClosed("JMSCC0026");
/*      */ 
/*      */       
/* 1940 */       this.session.checkSynchronousUsage("sendInternal");
/*      */       
/* 1942 */       if (inIdentifiedContext) {
/*      */ 
/*      */         
/* 1945 */         checkNotUnidentifiedProducer();
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1950 */         checkNotIdentifiedProducer();
/*      */       } 
/*      */ 
/*      */       
/* 1954 */       checkNotNullMessage(message);
/*      */ 
/*      */       
/* 1957 */       checkTemporaryDest(dest);
/*      */       
/* 1959 */       crossValidateDeliveryDelay(deliveryDelay, timeToLive, message);
/*      */       
/* 1961 */       if (validateProperties) {
/* 1962 */         validatePriority(priority);
/* 1963 */         validateDeliveryMode(deliveryMode);
/* 1964 */         validateTimeToLive(timeToLive);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1973 */       if (this.session.isAsync()) {
/*      */ 
/*      */ 
/*      */         
/* 1977 */         synchronousSendInternal(dest, message, deliveryMode, priority, timeToLive, deliveryDelay, completionListener);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1983 */       else if (completionListener != null) {
/*      */ 
/*      */         
/* 1986 */         asynchronousSendInternal(dest, message, deliveryMode, priority, timeToLive, deliveryDelay, completionListener);
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1993 */         synchronousSendInternal(dest, message, deliveryMode, priority, timeToLive, deliveryDelay, (CompletionListener)null);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1998 */       if (this.byteStreamReadOnlyAfterSend) {
/* 1999 */         if (message instanceof JmsBytesMessageImpl) {
/* 2000 */           ((JmsBytesMessageImpl)message).reset();
/* 2001 */         } else if (message instanceof JmsStreamMessageImpl) {
/* 2002 */           ((JmsStreamMessageImpl)message).reset();
/*      */         }
/*      */       
/*      */       }
/*      */     } finally {
/*      */       
/* 2008 */       if (Trace.isOn) {
/* 2009 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "sendInternal(boolean,JmsDestinationImpl,Message,int,int,long,long,CompletionListener,boolean)");
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2015 */     if (Trace.isOn) {
/* 2016 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "sendInternal(boolean,JmsDestinationImpl,Message,int,int,long,long,CompletionListener,boolean)");
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
/*      */   void synchronousSendInternal(JmsDestinationImpl dest, Message message, int deliveryMode, int priority, long timeToLive, long deliveryDelay, CompletionListener completionListener) throws JMSException {
/* 2045 */     if (Trace.isOn) {
/* 2046 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "synchronousSendInternal(JmsDestinationImpl,Message,int,int,long,long,CompletionListener)", new Object[] { dest, message, 
/*      */             
/* 2048 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), 
/* 2049 */             Long.valueOf(timeToLive), Long.valueOf(deliveryDelay), completionListener });
/*      */     }
/*      */ 
/*      */     
/* 2053 */     this.producerProps.update((JmsDestination)dest, deliveryMode, priority, timeToLive, deliveryDelay);
/*      */     
/* 2055 */     sendMessage(message, dest);
/*      */ 
/*      */     
/* 2058 */     if (completionListener != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2063 */       JmsSessionImpl.CompletionListenerRunner listenerRunner = new JmsSessionImpl.CompletionListenerRunner(completionListener, message, null);
/*      */       try {
/* 2065 */         WorkQueueManager.enqueue(listenerRunner, 0, false);
/*      */       }
/* 2067 */       catch (CSIException e) {
/* 2068 */         if (Trace.isOn) {
/* 2069 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "synchronousSendInternal(JmsDestinationImpl,Message,int,int,long,long,CompletionListener)", (Throwable)e);
/*      */         }
/*      */ 
/*      */         
/* 2073 */         JMSException je = new JMSException(e.getMessage());
/* 2074 */         je.setLinkedException((Exception)e);
/* 2075 */         if (Trace.isOn) {
/* 2076 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "synchronousSendInternal(JmsDestinationImpl,Message,int,int,long,long,CompletionListener)", (Throwable)je);
/*      */         }
/*      */ 
/*      */         
/* 2080 */         throw je;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2087 */     if (Trace.isOn) {
/* 2088 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "synchronousSendInternal(JmsDestinationImpl,Message,int,int,long,long,CompletionListener)");
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
/*      */   void asynchronousSendInternal(JmsDestinationImpl dest, Message message, int deliveryMode, int priority, long timeToLive, long deliveryDelay, CompletionListener completionListenerP) throws JMSException {
/* 2103 */     if (Trace.isOn) {
/* 2104 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "asynchronousSendInternal(JmsDestinationImpl,Message,int,int,long,long,CompletionListener)", new Object[] { dest, message, 
/*      */             
/* 2106 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), 
/* 2107 */             Long.valueOf(timeToLive), Long.valueOf(deliveryDelay), completionListenerP });
/*      */     }
/*      */     
/* 2110 */     CompletionListener completionListener = completionListenerP;
/*      */     
/* 2112 */     Message sendingMessage = this.session.duplicate(message);
/* 2113 */     this.session.getClass(); JmsSessionImpl.SendDetails details = new JmsSessionImpl.SendDetails(this.session, this, false, dest, message, sendingMessage, deliveryMode, priority, timeToLive, deliveryDelay, false, completionListener);
/*      */ 
/*      */     
/* 2116 */     this.session.loadMessageForSend(details);
/*      */     
/* 2118 */     if (Trace.isOn) {
/* 2119 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "asynchronousSendInternal(JmsDestinationImpl,Message,int,int,long,long,CompletionListener)");
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
/*      */   public long getDeliveryDelay() throws JMSException {
/* 2132 */     if (Trace.isOn) {
/* 2133 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "getDeliveryDelay()");
/*      */     }
/*      */     
/* 2136 */     if (propertyExists("deliveryDelay")) {
/* 2137 */       long traceRet1 = getLongProperty("deliveryDelay");
/* 2138 */       if (Trace.isOn) {
/* 2139 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "getDeliveryDelay()", 
/* 2140 */             Long.valueOf(traceRet1), 1);
/*      */       }
/* 2142 */       return traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 2146 */     if (Trace.isOn) {
/* 2147 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "getDeliveryDelay()", 
/* 2148 */           Long.valueOf(0L), 2);
/*      */     }
/* 2150 */     return 0L;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDeliveryDelay(long deliveryDelay) throws JMSException {
/* 2158 */     if (Trace.isOn) {
/* 2159 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "setDeliveryDelay(long)", "setter", 
/* 2160 */           Long.valueOf(deliveryDelay));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2165 */     if (deliveryDelay != 0L) {
/* 2166 */       this.session.connection.checkValidFnUsage(JmsConnectionImpl.JMS2Function.DELAYED_DELIVERY);
/*      */     }
/*      */ 
/*      */     
/* 2170 */     setLongProperty("deliveryDelay", deliveryDelay);
/*      */   }
/*      */ 
/*      */   
/*      */   private void getSyncLockForAsyncSend() {
/* 2175 */     if (Trace.isOn) {
/* 2176 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "getSyncLockForAsyncSend()");
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
/* 2187 */     if (Trace.isOn) {
/* 2188 */       Trace.data(this, "getSyncLockForAsyncSend()", "getting shared sessionSyncLock", this.sessionSyncLock);
/*      */     }
/* 2190 */     this.sessionSyncLock.getSharedLock();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2196 */     if (this.sessionSyncLock.getExclusiveQueueLength() > 0) {
/* 2197 */       if (Trace.isOn) {
/* 2198 */         Trace.data(this, "getSyncLockForAsyncSend()", this.sessionSyncLock
/*      */             
/* 2200 */             .getExclusiveQueueLength() + " threads already waiting for exclusive lock. Waiting for exclusive lock", this.sessionSyncLock);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2205 */       this.sessionSyncLock.getExclusiveLock();
/*      */ 
/*      */       
/* 2208 */       if (Trace.isOn) {
/* 2209 */         Trace.data(this, "getSyncLockForAsyncSend()", "got exclusive sessionSyncLock. Downgrading to shared lock", this.sessionSyncLock);
/*      */       }
/* 2211 */       this.sessionSyncLock.downGradeLock();
/*      */     
/*      */     }
/* 2214 */     else if (Trace.isOn) {
/* 2215 */       Trace.data(this, "getSyncLockForAsyncSend()", this.sessionSyncLock
/*      */           
/* 2217 */           .getExclusiveQueueLength() + " threads already waiting for exclusive lock. Waiting for exclusive lock", this.sessionSyncLock);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2225 */     if (Trace.isOn) {
/* 2226 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "getSyncLockForAsyncSend()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dump(PrintWriter pw, int level) {
/* 2237 */     if (Trace.isOn) {
/* 2238 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "dump(PrintWriter,int)", new Object[] { pw, 
/* 2239 */             Integer.valueOf(level) });
/*      */     }
/* 2241 */     String prefix = Trace.buildPrefix(level);
/* 2242 */     pw.format("%s%s (%s)\n", new Object[] { prefix, stringifyMe(), (this.providerProducer == null) ? "<null>" : 
/* 2243 */           String.format("%s@%x", new Object[] { this.providerProducer.getClass().getName(), Integer.valueOf(this.providerProducer.hashCode()) }) });
/* 2244 */     super.dump(pw, level + 1);
/* 2245 */     if (Trace.isOn)
/* 2246 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageProducerImpl", "dump(PrintWriter,int)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsMessageProducerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */