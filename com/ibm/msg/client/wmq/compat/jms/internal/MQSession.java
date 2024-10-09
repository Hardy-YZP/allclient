/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.jms.SessionClosedException;
/*      */ import com.ibm.mq.jms.SyntaxException;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import com.ibm.msg.client.provider.ProviderBytesMessage;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import com.ibm.msg.client.provider.ProviderMapMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessageConsumer;
/*      */ import com.ibm.msg.client.provider.ProviderMessageListener;
/*      */ import com.ibm.msg.client.provider.ProviderMessageProducer;
/*      */ import com.ibm.msg.client.provider.ProviderMessageReference;
/*      */ import com.ibm.msg.client.provider.ProviderObjectMessage;
/*      */ import com.ibm.msg.client.provider.ProviderQueueBrowser;
/*      */ import com.ibm.msg.client.provider.ProviderSession;
/*      */ import com.ibm.msg.client.provider.ProviderStreamMessage;
/*      */ import com.ibm.msg.client.provider.ProviderTextMessage;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQPropertyContext;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQTemporaryQueue;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQTemporaryTopic;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager;
/*      */ import java.io.IOException;
/*      */ import java.io.NotSerializableException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.Serializable;
/*      */ import java.util.HashMap;
/*      */ import java.util.Vector;
/*      */ import javax.jms.IllegalStateException;
/*      */ import javax.jms.InvalidDestinationException;
/*      */ import javax.jms.InvalidSelectorException;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.TransactionRolledBackException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQSession
/*      */   extends WMQPropertyContext
/*      */   implements ProviderSession, JMSAcknowledgePoint
/*      */ {
/*      */   private static final long serialVersionUID = 5764756656822339804L;
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQSession.java";
/*      */   private static final int DEFAULT_BROKER_TIMEOUT = 120000;
/*      */   protected static final int DEREGISTER_SHAREDQ_SUBSCRIBER = 5;
/*      */   protected static final int DEREGISTER_SUBSCRIBER = 3;
/*      */   protected static final int DIST_TRAN_NONE = 0;
/*      */   protected static final int DIST_TRAN_RRS = 2;
/*      */   protected static final int DIST_TRAN_XA = 1;
/*      */   
/*      */   static {
/*   92 */     if (Trace.isOn) {
/*   93 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQSession.java");
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
/*  122 */   protected static final JMSStringResources jmsStrings = new MQJMSStringResources();
/*      */   
/*      */   private static final int MQCA_QSG_NAME = 2040;
/*      */   
/*      */   private static final String MQPS_Q_NAME_B = " MQPSQName ";
/*      */   
/*      */   private static final String MQPS_STREAM_NAME_B = " MQPSStreamName ";
/*      */   
/*      */   private static final String PROBE_02 = "02";
/*      */   
/*      */   private static final String PROBE_03 = "03";
/*      */   
/*      */   private static final String PROBE_04 = "04";
/*      */   
/*      */   private static final String PROBE_05 = "05";
/*      */   
/*      */   protected static final String PUBLISH_CMD_PREFIX = "MQPSCommand Publish MQPSTopic ";
/*      */   
/*      */   protected static final int REGISTER_SHAREDQ_SUBSCRIBER = 4;
/*      */   
/*      */   protected static final int REGISTER_SUBSCRIBER = 2;
/*      */   
/*      */   private static final int STATE_CLOSED = 2;
/*      */   
/*      */   private static final int STATE_STARTED = 1;
/*      */   
/*      */   private static final int STATE_STOPPED = 0;
/*      */   protected int acknowledgeMode;
/*  150 */   private SessionAsyncHelper asyncHelper = null;
/*      */ 
/*      */   
/*  153 */   protected BrokerConnectionInfo brk = new BrokerConnectionInfo(null, null, null);
/*      */ 
/*      */   
/*      */   protected int brkOptLevel;
/*      */ 
/*      */   
/*      */   private MQGetMessageOptions brokerReponseGmo;
/*      */   
/*  161 */   private int brokerTimeout = 120000;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  167 */   private MQQueue cbInputQueue = null;
/*  168 */   protected int checkInterval = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean commitRequired = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  198 */   private Object commitLock = new Object();
/*      */   
/*  200 */   protected MQConnection connection = null;
/*      */ 
/*      */   
/*  203 */   private String deadLetterQueue = null;
/*      */ 
/*      */   
/*  206 */   private int distTransactionMode = 0;
/*      */   
/*      */   private boolean ITXSupportChecked = false;
/*      */   private boolean ITXSupported = false;
/*  210 */   private String lastQueueName = null;
/*      */   
/*      */   private boolean mapNameStyle = true;
/*  213 */   private Vector<JmsPropertyContext> messageConsumers = new Vector<>();
/*      */ 
/*      */   
/*      */   private ProviderMessageListener messageListener;
/*      */ 
/*      */   
/*      */   private boolean calledByAsf = false;
/*      */ 
/*      */   
/*  222 */   private Vector<JmsPropertyContext> messageProducers = new Vector<>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String messageQueueName;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  232 */   private JMSServicesMgr mqServices = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String nondur_subscribeQ;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean optimisticPublication = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean outcomeNotification = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean persistenceFromMD;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean playNice = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  267 */   private int processDuration = 0;
/*  268 */   protected int publishCount = 0;
/*      */ 
/*      */   
/*      */   protected boolean pubsubInit = false;
/*      */   
/*  273 */   private MQQueueManager qm = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  278 */   private int receiveIsolation = 0;
/*      */ 
/*      */   
/*  281 */   protected MQQueue replyQ = null;
/*      */ 
/*      */   
/*  284 */   private String resolvedQmName = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  289 */   private String resolvedQSGName = null;
/*      */ 
/*      */   
/*  292 */   protected byte[] responseCorrelId = new byte[24];
/*  293 */   protected int responseInterval = 0;
/*      */   protected boolean responseOnCommit = false;
/*  295 */   protected long responsePutTime = 0L;
/*      */ 
/*      */   
/*      */   protected boolean responseRequested = false;
/*      */ 
/*      */   
/*      */   protected byte[] sessionName;
/*      */ 
/*      */   
/*  304 */   private int state = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  313 */   private Object stateChangeSem = new Object();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String SUBSCRIBE_CMD_POSTFIX;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean transacted;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  329 */   private Object transactionLock = new TransactionLock();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  335 */   private Object brokerResponseLock = new BrokerResponseLock();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQSession(MQConnection connection, MQQueueManager qm, boolean transacted, int acknowledgeMode) throws JMSException {
/*  341 */     super((JmsPropertyContext)connection);
/*      */     
/*  343 */     if (Trace.isOn) {
/*  344 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "<init>(MQConnection,MQQueueManager,boolean,int)", new Object[] { connection, qm, 
/*      */             
/*  346 */             Boolean.valueOf(transacted), Integer.valueOf(acknowledgeMode) });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  351 */       this.connection = connection;
/*  352 */       this.transacted = transacted;
/*  353 */       this.acknowledgeMode = acknowledgeMode;
/*      */       
/*  355 */       calculateUseSPIP();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  362 */       if (connection != null) {
/*  363 */         this.persistenceFromMD = connection.getPersistenceFromMD();
/*      */       } else {
/*      */         
/*  366 */         this.persistenceFromMD = false;
/*      */       } 
/*      */       
/*  369 */       if (Trace.isOn) {
/*  370 */         Trace.traceData(this, "Resolving QMName", null);
/*      */       }
/*      */ 
/*      */       
/*  374 */       if (connection != null && connection.qm != null) {
/*      */         
/*      */         try {
/*  377 */           int selector1 = 2015;
/*  378 */           this.resolvedQmName = connection.qm.getAttributeString(selector1, 48).trim();
/*      */         }
/*  380 */         catch (Exception e1) {
/*  381 */           if (Trace.isOn) {
/*  382 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "<init>(MQConnection,MQQueueManager,boolean,int)", e1, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  387 */           this.resolvedQmName = connection.qm.name;
/*      */         }
/*      */       
/*  390 */       } else if (connection != null && connection.qmgrName != null) {
/*      */         
/*  392 */         this.resolvedQmName = connection.qmgrName;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  397 */       if (Trace.isOn) {
/*  398 */         Trace.traceData(this, "Resolving QSGName", null);
/*      */       }
/*  400 */       if (connection != null && connection.qm != null) {
/*      */         
/*      */         try {
/*  403 */           int selector2 = 2040;
/*  404 */           this.resolvedQSGName = connection.qm.getAttributeString(selector2, 4).trim();
/*      */         
/*      */         }
/*  407 */         catch (Exception e3) {
/*  408 */           if (Trace.isOn) {
/*  409 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "<init>(MQConnection,MQQueueManager,boolean,int)", e3, 2);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  414 */           this.resolvedQSGName = null;
/*  415 */           if (Trace.isOn) {
/*  416 */             Trace.traceData(this, "Setting QSGName to null", null);
/*      */           }
/*      */         } 
/*      */       } else {
/*      */         
/*  421 */         this.resolvedQSGName = null;
/*  422 */         if (Trace.isOn) {
/*  423 */           Trace.traceData(this, "No qm, setting QSGName to null", null);
/*      */         }
/*      */       } 
/*  426 */       if (Trace.isOn) {
/*  427 */         Trace.traceData(this, "QSGName set to " + this.resolvedQSGName, null);
/*      */       }
/*      */     }
/*  430 */     catch (Exception e2) {
/*  431 */       if (Trace.isOn) {
/*  432 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "<init>(MQConnection,MQQueueManager,boolean,int)", e2, 3);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  437 */     this.qm = qm;
/*  438 */     this.connection = connection;
/*  439 */     MQSubscriptionEngine subEngine = this.connection.getSubscriptionEngine();
/*  440 */     this.sessionName = null;
/*      */     
/*  442 */     if (subEngine instanceof MQBrokerSubscriptionEngine) {
/*  443 */       this.sessionName = ((MQBrokerSubscriptionEngine)subEngine).createSessionName(this);
/*      */     }
/*  445 */     else if (subEngine instanceof MQMigrateSubscriptionEngine) {
/*  446 */       this.sessionName = ((MQMigrateSubscriptionEngine)subEngine).createSessionName(this);
/*      */     } 
/*      */ 
/*      */     
/*  450 */     this.brk.controlQ = this.connection.getBrkControlQueue();
/*  451 */     this.brk.streamQ = this.connection.getBrkPubQueue();
/*  452 */     this.brk.qmName = this.connection.getBrkQueueManager();
/*      */     
/*  454 */     this.brkOptLevel = this.connection.getBrkOptLevel();
/*  455 */     this.nondur_subscribeQ = this.connection.getBrkSubQueue();
/*  456 */     this.mapNameStyle = this.connection.getMapNameStyle();
/*  457 */     this.SUBSCRIBE_CMD_POSTFIX = " MQPSStreamName " + this.brk.streamQ + " MQPSQName ";
/*      */     
/*  459 */     this.responseInterval = this.connection.getPubAckInterval();
/*  460 */     this.checkInterval = this.responseInterval / 2;
/*      */     
/*  462 */     this.receiveIsolation = this.connection.getReceiveIsolation();
/*  463 */     this.processDuration = this.connection.getProcessDuration();
/*  464 */     this.outcomeNotification = this.connection.getOutcomeNotification();
/*  465 */     this.optimisticPublication = this.connection.getOptimisticPublication();
/*      */     
/*  467 */     this.brokerReponseGmo = new MQGetMessageOptions();
/*  468 */     this.brokerReponseGmo.options = 1;
/*  469 */     this.brokerReponseGmo.waitInterval = this.brokerTimeout;
/*      */ 
/*      */     
/*  472 */     if (connection.getFailIfQuiesce() == 1) {
/*  473 */       this.brokerReponseGmo.options |= 0x2000;
/*      */     }
/*      */     
/*      */     try {
/*  477 */       int selector = 2015;
/*  478 */       this.resolvedQmName = qm.getAttributeString(selector, 48).trim();
/*      */     
/*      */     }
/*  481 */     catch (Exception e) {
/*  482 */       if (Trace.isOn) {
/*  483 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "<init>(MQConnection,MQQueueManager,boolean,int)", e, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  488 */       this.resolvedQmName = qm.name;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/*  493 */       int selector = 2040;
/*  494 */       this.resolvedQSGName = qm.getAttributeString(selector, 4).trim();
/*      */     
/*      */     }
/*  497 */     catch (Exception e) {
/*  498 */       if (Trace.isOn) {
/*  499 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "<init>(MQConnection,MQQueueManager,boolean,int)", e, 2);
/*      */       }
/*      */ 
/*      */       
/*  503 */       this.resolvedQSGName = null;
/*  504 */       if (Trace.isOn) {
/*  505 */         Trace.traceData(this, "Setting QSGName to null", null);
/*      */       }
/*      */     } 
/*  508 */     if (Trace.isOn) {
/*  509 */       Trace.traceData(this, "QSGName set to " + this.resolvedQSGName, null);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  514 */     if (Trace.isOn) {
/*  515 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "<init>(MQConnection,MQQueueManager,boolean,int)");
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
/*      */   public void _acknowledge(boolean forceAck) throws JMSException {
/*  543 */     if (Trace.isOn) {
/*  544 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "_acknowledge(boolean)", new Object[] {
/*  545 */             Boolean.valueOf(forceAck)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  552 */       switch (getAcknowledgeMode()) {
/*      */         case 1:
/*  554 */           if (Trace.isOn) {
/*  555 */             Trace.traceData(this, "AUTO_ACKNOWLEDGE ProviderSession. forceAck = " + forceAck, null);
/*      */           }
/*      */           break;
/*      */         
/*      */         case 3:
/*  560 */           if (Trace.isOn) {
/*  561 */             Trace.traceData(this, "DUPS_OK_ACKNOWELDGE ProviderSession. forceAck = " + forceAck, null);
/*      */           }
/*      */           break;
/*      */         
/*      */         case 0:
/*  566 */           if (Trace.isOn) {
/*  567 */             Trace.traceData(this, "SESSION_TRANSACTED ProviderSession. Returning", null);
/*      */           }
/*  569 */           if (Trace.isOn) {
/*  570 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "_acknowledge(boolean)", 1);
/*      */           }
/*      */           return;
/*      */ 
/*      */         
/*      */         case 2:
/*  576 */           if (Trace.isOn) {
/*  577 */             Trace.traceData(this, "CLIENT_ACKNOWLEDGE ProviderSession. Acknowledging message", null);
/*      */           }
/*  579 */           _acknowledgeInternal();
/*  580 */           if (Trace.isOn) {
/*  581 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "_acknowledge(boolean)", 2);
/*      */           }
/*      */           return;
/*      */ 
/*      */         
/*      */         default:
/*  587 */           if (Trace.isOn) {
/*  588 */             Trace.traceData(this, "Unknown AcknowledgeMode. GIving up and returning", null);
/*      */           }
/*      */ 
/*      */           
/*  592 */           if (Trace.isOn) {
/*  593 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "_acknowledge(boolean)", 3);
/*      */           }
/*      */           return;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  602 */       if (forceAck) {
/*  603 */         _acknowledgeInternal();
/*      */       
/*      */       }
/*      */     }
/*  607 */     catch (JMSException je) {
/*  608 */       if (Trace.isOn) {
/*  609 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "_acknowledge(boolean)", (Throwable)je);
/*      */       }
/*      */       
/*  612 */       if (Trace.isOn) {
/*  613 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "_acknowledge(boolean)", (Throwable)je);
/*      */       }
/*      */       
/*  616 */       throw je;
/*      */     } 
/*  618 */     if (Trace.isOn) {
/*  619 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "_acknowledge(boolean)", 4);
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
/*      */   protected void _acknowledgeInternal() throws JMSException {
/*  643 */     if (Trace.isOn) {
/*  644 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "_acknowledgeInternal()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  649 */       if (this.transacted) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  654 */         String key = "MQJMS1020";
/*  655 */         String msg = ConfigEnvironment.getErrorMessage(key);
/*  656 */         IllegalStateException illegalStateException = new IllegalStateException(msg, key);
/*      */         
/*  658 */         if (Trace.isOn) {
/*  659 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "_acknowledgeInternal()", (Throwable)illegalStateException, 1);
/*      */         }
/*      */         
/*  662 */         throw illegalStateException;
/*      */       } 
/*  664 */       if (this.qm == null) {
/*  665 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/*  666 */         if (Trace.isOn) {
/*  667 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "_acknowledgeInternal()", (Throwable)je, 2);
/*      */         }
/*      */         
/*  670 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  679 */         boolean async = false;
/*      */         try {
/*  681 */           async = (!this.outcomeNotification && this.qm.spiSupportsInherited());
/*      */         
/*      */         }
/*  684 */         catch (MQException mqe) {
/*  685 */           if (Trace.isOn) {
/*  686 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "_acknowledgeInternal()", (Throwable)mqe, 1);
/*      */           }
/*      */ 
/*      */           
/*  690 */           async = false;
/*      */         } 
/*  692 */         if (async) {
/*  693 */           this.qm.asyncCommit();
/*      */         } else {
/*      */           
/*  696 */           this.qm.commit();
/*      */         } 
/*  698 */         if (this.acknowledgeMode == 2)
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*  703 */           setCommitRequired(false);
/*      */         
/*      */         }
/*      */       }
/*  707 */       catch (MQException e) {
/*  708 */         if (Trace.isOn) {
/*  709 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "_acknowledgeInternal()", (Throwable)e, 2);
/*      */         }
/*      */         
/*  712 */         JMSException je = ConfigEnvironment.newException("MQJMS2009");
/*  713 */         je.setLinkedException((Exception)e);
/*  714 */         if (Trace.isOn) {
/*  715 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "_acknowledgeInternal()", (Throwable)je, 3);
/*      */         }
/*      */         
/*  718 */         throw je;
/*      */       }
/*      */     
/*  721 */     } catch (JMSException je) {
/*  722 */       if (Trace.isOn) {
/*  723 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "_acknowledgeInternal()", (Throwable)je, 3);
/*      */       }
/*      */       
/*  726 */       if (Trace.isOn) {
/*  727 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "_acknowledgeInternal()", (Throwable)je, 4);
/*      */       }
/*      */       
/*  730 */       throw je;
/*      */     } 
/*  732 */     if (Trace.isOn) {
/*  733 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "_acknowledgeInternal()");
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
/*      */   protected void addAsync(MQMessageConsumer consumer) {
/*  745 */     if (Trace.isOn) {
/*  746 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "addAsync(MQMessageConsumer)", new Object[] { consumer });
/*      */     }
/*      */ 
/*      */     
/*  750 */     if (this.asyncHelper == null) {
/*  751 */       if (Trace.isOn) {
/*  752 */         Trace.traceData(this, "creating asyncHelper Thread", null);
/*      */       }
/*      */ 
/*      */       
/*  756 */       this.asyncHelper = new SessionAsyncHelper(this.connection, this);
/*      */     } 
/*      */     
/*  759 */     this.asyncHelper.addReceiver(consumer);
/*      */     
/*  761 */     if (Trace.isOn) {
/*  762 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "addAsync(MQMessageConsumer)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addConsumer(ProviderMessageConsumer consumer) {
/*  773 */     if (Trace.isOn) {
/*  774 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "addConsumer(ProviderMessageConsumer)", new Object[] { consumer });
/*      */     }
/*      */     
/*  777 */     this.messageConsumers.addElement(consumer);
/*  778 */     if (Trace.isOn) {
/*  779 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "addConsumer(ProviderMessageConsumer)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addProducer(ProviderMessageProducer producer) {
/*  789 */     if (Trace.isOn) {
/*  790 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "addProducer(ProviderMessageProducer)", new Object[] { producer });
/*      */     }
/*      */     
/*  793 */     this.messageProducers.addElement(producer);
/*  794 */     if (Trace.isOn) {
/*  795 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "addProducer(ProviderMessageProducer)");
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
/*      */   protected void addPubSubServices() throws JMSException {
/*  808 */     if (Trace.isOn) {
/*  809 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "addPubSubServices()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  815 */       this.mqServices = this.connection.getMQPubSubServices(this.transacted, this.acknowledgeMode, this);
/*  816 */       this.pubsubInit = true;
/*      */     }
/*  818 */     catch (JMSException je) {
/*  819 */       if (Trace.isOn) {
/*  820 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "addPubSubServices()", (Throwable)je);
/*      */       }
/*      */       
/*  823 */       if (Trace.isOn) {
/*  824 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "addPubSubServices()", (Throwable)je);
/*      */       }
/*      */       
/*  827 */       throw je;
/*      */     } 
/*  829 */     if (Trace.isOn) {
/*  830 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "addPubSubServices()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addQueueServices() throws JMSException {
/*  841 */     if (Trace.isOn) {
/*  842 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "addQueueServices()");
/*      */     }
/*      */     
/*      */     try {
/*  846 */       if (this.mqServices == null || !this.mqServices.queueSet) {
/*  847 */         this.mqServices = this.connection.getMQQueueServices(this.qm, this.transacted, this.acknowledgeMode);
/*      */       }
/*      */     }
/*  850 */     catch (JMSException je) {
/*  851 */       if (Trace.isOn) {
/*  852 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "addQueueServices()", (Throwable)je);
/*      */       }
/*      */       
/*  855 */       if (Trace.isOn) {
/*  856 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "addQueueServices()", (Throwable)je);
/*      */       }
/*      */       
/*  859 */       throw je;
/*      */     } 
/*  861 */     if (Trace.isOn) {
/*  862 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "addQueueServices()");
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
/*      */   boolean callingFromOnMessage() {
/*      */     boolean result;
/*  876 */     if (Trace.isOn) {
/*  877 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "callingFromOnMessage()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  882 */     if (this.asyncHelper == null) {
/*  883 */       result = false;
/*      */     } else {
/*      */       
/*  886 */       result = this.asyncHelper.callingFromOnMessage();
/*      */     } 
/*  888 */     if (Trace.isOn) {
/*  889 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "callingFromOnMessage()", 
/*  890 */           Boolean.valueOf(result));
/*      */     }
/*  892 */     return result;
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
/*      */   public synchronized void close(boolean closingFromConnection) throws JMSException {
/*  926 */     if (Trace.isOn) {
/*  927 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "close(boolean)", new Object[] {
/*  928 */             Boolean.valueOf(closingFromConnection)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  937 */       if (getServicesMgr() != null && (getServicesMgr()).pubSubSet) {
/*  938 */         closeT();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  943 */       if (this.state == 1 || this.state == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  951 */         if (getAcknowledgeMode() == 1 || getAcknowledgeMode() == 3) {
/*      */           
/*      */           try {
/*      */             
/*  955 */             _acknowledge(true);
/*      */           }
/*  957 */           catch (Exception e) {
/*  958 */             if (Trace.isOn) {
/*  959 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "close(boolean)", e, 1);
/*      */             }
/*      */             
/*  962 */             if (Trace.isOn) {
/*  963 */               Trace.traceData(this, "_acknowledge() from close() failed : Not throwing Exception", null);
/*      */             }
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/*  969 */         this.state = 2;
/*      */ 
/*      */         
/*  972 */         synchronized (this.stateChangeSem) {
/*  973 */           this.stateChangeSem.notifyAll();
/*      */         } 
/*      */ 
/*      */         
/*  977 */         if (this.connection != null) {
/*  978 */           this.connection.removeSession(this);
/*      */         }
/*      */       }
/*  981 */       else if (this.state != 2) {
/*      */ 
/*      */ 
/*      */         
/*  985 */         JMSException je = ConfigEnvironment.newException("MQJMS1005", 
/*  986 */             String.valueOf(this.state), "STATE_CLOSED");
/*      */         
/*  988 */         if (Trace.isOn) {
/*  989 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "close(boolean)", (Throwable)je, 1);
/*      */         }
/*      */         
/*  992 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  999 */       if (this.asyncHelper != null) {
/* 1000 */         this.asyncHelper.shutdown();
/* 1001 */         this.asyncHelper = null;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1006 */       if (this.qm != null)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1013 */         if (this.distTransactionMode == 0 || (this.distTransactionMode == 2 && 
/* 1014 */           !Utils.isRRSTransactionInProgress())) {
/*      */ 
/*      */           
/*      */           try {
/* 1018 */             if (Trace.isOn) {
/* 1019 */               Trace.traceData(this, "backing out queueManager", null);
/*      */             }
/* 1021 */             this.qm.backout();
/*      */           }
/* 1023 */           catch (MQException e) {
/* 1024 */             JMSException je; HashMap<String, Serializable> ffstData; if (Trace.isOn) {
/* 1025 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "close(boolean)", (Throwable)e, 2);
/*      */             }
/*      */             
/* 1028 */             if (Trace.isOn) {
/* 1029 */               Trace.traceData(this, "backout failed.", null);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1036 */             switch (e.reasonCode) {
/*      */ 
/*      */               
/*      */               case 2071:
/*      */               case 2102:
/*      */               case 2124:
/*      */               case 2195:
/*      */               case 2219:
/* 1044 */                 je = ConfigEnvironment.newException("MQJMS1032", e.getMessage());
/* 1045 */                 je.setLinkedException((Exception)e);
/*      */                 
/* 1047 */                 if (Trace.isOn) {
/* 1048 */                   Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "close(boolean)", (Throwable)je, 2);
/*      */                 }
/*      */                 
/* 1051 */                 throw je;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               case 2009:
/*      */               case 2012:
/*      */               case 2018:
/*      */               case 2019:
/*      */               case 2101:
/*      */               case 2123:
/*      */               case 2130:
/*      */               case 2157:
/*      */               case 2162:
/* 1066 */                 if (Trace.isOn) {
/* 1067 */                   Trace.data(this, "Exception backing out queueManager (ignoring)", e);
/*      */                 }
/*      */                 break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               default:
/* 1076 */                 ffstData = new HashMap<>();
/* 1077 */                 ffstData.put("Exception", e);
/* 1078 */                 ffstData.put("RC", Integer.valueOf(e.reasonCode));
/* 1079 */                 ffstData.put("Message", "MQJMS1016");
/* 1080 */                 Trace.ffst(this, "close()", "02", ffstData, JMSException.class);
/*      */                 break;
/*      */             } 
/*      */ 
/*      */ 
/*      */           
/*      */           } 
/* 1087 */         } else if (Trace.isOn) {
/* 1088 */           if (this.distTransactionMode == 1) {
/* 1089 */             Trace.traceData(this, "not calling qm.backout since we are under XA", null);
/*      */           } else {
/*      */             
/* 1092 */             Trace.traceData(this, "not calling qm.backout since an RRS global transaction is in progress", null);
/*      */           } 
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1099 */       synchronized (getTransactionLock()) {
/* 1100 */         closeAllChildren(closingFromConnection);
/*      */       } 
/*      */ 
/*      */       
/* 1104 */       if (this.qm != null)
/*      */       {
/*      */ 
/*      */         
/* 1108 */         if (this.distTransactionMode == 1) {
/* 1109 */           if (Trace.isOn) {
/* 1110 */             Trace.traceData(this, "qm.disc postponed because this session is XA aware", null);
/*      */           }
/*      */         } else {
/*      */           
/*      */           try {
/* 1115 */             if (Trace.isOn) {
/* 1116 */               Trace.traceData(this, "disconnecting queueManager", null);
/*      */             }
/* 1118 */             this.qm.disconnect();
/*      */           
/*      */           }
/* 1121 */           catch (Exception e) {
/* 1122 */             if (Trace.isOn) {
/* 1123 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "close(boolean)", e, 3);
/*      */             }
/*      */             
/* 1126 */             JMSException je = ConfigEnvironment.newException("MQJMS2003");
/* 1127 */             je.setLinkedException(e);
/* 1128 */             if (Trace.isOn) {
/* 1129 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "close(boolean)", (Throwable)je, 3);
/*      */             }
/*      */             
/* 1132 */             throw je;
/*      */           }
/*      */           finally {
/*      */             
/* 1136 */             if (Trace.isOn) {
/* 1137 */               Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "close(boolean)");
/*      */             }
/*      */             
/* 1140 */             this.qm = null;
/*      */           }
/*      */         
/*      */         } 
/*      */       }
/* 1145 */     } catch (JMSException je) {
/* 1146 */       if (Trace.isOn) {
/* 1147 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "close(boolean)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 1150 */       if (Trace.isOn) {
/* 1151 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "close(boolean)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 1154 */       throw je;
/*      */     } 
/* 1156 */     if (Trace.isOn) {
/* 1157 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "close(boolean)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void closeAllChildren(boolean closingFromConnection) {
/*      */     // Byte code:
/*      */     //   0: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   3: ifeq -> 25
/*      */     //   6: aload_0
/*      */     //   7: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQSession'
/*      */     //   9: ldc 'closeAllChildren(boolean)'
/*      */     //   11: iconst_1
/*      */     //   12: anewarray java/lang/Object
/*      */     //   15: dup
/*      */     //   16: iconst_0
/*      */     //   17: iload_1
/*      */     //   18: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*      */     //   21: aastore
/*      */     //   22: invokestatic entry : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
/*      */     //   25: aload_0
/*      */     //   26: getfield messageProducers : Ljava/util/Vector;
/*      */     //   29: invokevirtual clone : ()Ljava/lang/Object;
/*      */     //   32: checkcast java/util/Vector
/*      */     //   35: astore_3
/*      */     //   36: aload_3
/*      */     //   37: invokevirtual elements : ()Ljava/util/Enumeration;
/*      */     //   40: astore_2
/*      */     //   41: aload_2
/*      */     //   42: invokeinterface hasMoreElements : ()Z
/*      */     //   47: ifeq -> 116
/*      */     //   50: aload_2
/*      */     //   51: invokeinterface nextElement : ()Ljava/lang/Object;
/*      */     //   56: checkcast com/ibm/msg/client/provider/ProviderMessageProducer
/*      */     //   59: astore #4
/*      */     //   61: aload #4
/*      */     //   63: iconst_1
/*      */     //   64: invokeinterface close : (Z)V
/*      */     //   69: goto -> 41
/*      */     //   72: astore #4
/*      */     //   74: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   77: ifeq -> 91
/*      */     //   80: aload_0
/*      */     //   81: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQSession'
/*      */     //   83: ldc 'closeAllChildren(boolean)'
/*      */     //   85: aload #4
/*      */     //   87: iconst_1
/*      */     //   88: invokestatic catchBlock : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   91: goto -> 41
/*      */     //   94: astore #4
/*      */     //   96: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   99: ifeq -> 113
/*      */     //   102: aload_0
/*      */     //   103: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQSession'
/*      */     //   105: ldc 'closeAllChildren(boolean)'
/*      */     //   107: aload #4
/*      */     //   109: iconst_2
/*      */     //   110: invokestatic catchBlock : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   113: goto -> 116
/*      */     //   116: aload_0
/*      */     //   117: getfield messageConsumers : Ljava/util/Vector;
/*      */     //   120: invokevirtual clone : ()Ljava/lang/Object;
/*      */     //   123: checkcast java/util/Vector
/*      */     //   126: astore #4
/*      */     //   128: aload #4
/*      */     //   130: invokevirtual elements : ()Ljava/util/Enumeration;
/*      */     //   133: astore_2
/*      */     //   134: aload_2
/*      */     //   135: invokeinterface hasMoreElements : ()Z
/*      */     //   140: ifeq -> 210
/*      */     //   143: aload_2
/*      */     //   144: invokeinterface nextElement : ()Ljava/lang/Object;
/*      */     //   149: checkcast com/ibm/msg/client/provider/ProviderMessageConsumer
/*      */     //   152: astore #5
/*      */     //   154: aload #5
/*      */     //   156: iconst_1
/*      */     //   157: aconst_null
/*      */     //   158: invokeinterface close : (ZLjava/util/concurrent/locks/ReentrantLock;)V
/*      */     //   163: goto -> 134
/*      */     //   166: astore #5
/*      */     //   168: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   171: ifeq -> 185
/*      */     //   174: aload_0
/*      */     //   175: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQSession'
/*      */     //   177: ldc 'closeAllChildren(boolean)'
/*      */     //   179: aload #5
/*      */     //   181: iconst_3
/*      */     //   182: invokestatic catchBlock : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   185: goto -> 134
/*      */     //   188: astore #5
/*      */     //   190: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   193: ifeq -> 207
/*      */     //   196: aload_0
/*      */     //   197: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQSession'
/*      */     //   199: ldc 'closeAllChildren(boolean)'
/*      */     //   201: aload #5
/*      */     //   203: iconst_4
/*      */     //   204: invokestatic catchBlock : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   207: goto -> 210
/*      */     //   210: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   213: ifeq -> 224
/*      */     //   216: aload_0
/*      */     //   217: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQSession'
/*      */     //   219: ldc 'closeAllChildren(boolean)'
/*      */     //   221: invokestatic exit : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   224: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1166	-> 0
/*      */     //   #1167	-> 6
/*      */     //   #1168	-> 18
/*      */     //   #1167	-> 22
/*      */     //   #1176	-> 25
/*      */     //   #1177	-> 36
/*      */     //   #1178	-> 41
/*      */     //   #1180	-> 50
/*      */     //   #1181	-> 61
/*      */     //   #1197	-> 69
/*      */     //   #1183	-> 72
/*      */     //   #1184	-> 74
/*      */     //   #1185	-> 80
/*      */     //   #1197	-> 91
/*      */     //   #1190	-> 94
/*      */     //   #1191	-> 96
/*      */     //   #1192	-> 102
/*      */     //   #1196	-> 113
/*      */     //   #1200	-> 116
/*      */     //   #1201	-> 128
/*      */     //   #1202	-> 134
/*      */     //   #1204	-> 143
/*      */     //   #1205	-> 154
/*      */     //   #1221	-> 163
/*      */     //   #1207	-> 166
/*      */     //   #1208	-> 168
/*      */     //   #1209	-> 174
/*      */     //   #1221	-> 185
/*      */     //   #1214	-> 188
/*      */     //   #1215	-> 190
/*      */     //   #1216	-> 196
/*      */     //   #1220	-> 207
/*      */     //   #1224	-> 210
/*      */     //   #1225	-> 216
/*      */     //   #1228	-> 224
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   61	8	4	mp	Lcom/ibm/msg/client/provider/ProviderMessageProducer;
/*      */     //   74	17	4	je	Ljavax/jms/JMSException;
/*      */     //   96	20	4	e	Ljava/util/NoSuchElementException;
/*      */     //   154	9	5	mc	Lcom/ibm/msg/client/provider/ProviderMessageConsumer;
/*      */     //   168	17	5	je	Ljavax/jms/JMSException;
/*      */     //   190	20	5	e	Ljava/util/NoSuchElementException;
/*      */     //   0	225	0	this	Lcom/ibm/msg/client/wmq/compat/jms/internal/MQSession;
/*      */     //   0	225	1	closingFromConnection	Z
/*      */     //   41	184	2	en	Ljava/util/Enumeration;
/*      */     //   36	189	3	producers	Ljava/util/Vector;
/*      */     //   128	97	4	consumers	Ljava/util/Vector;
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   41	184	2	en	Ljava/util/Enumeration<Lcom/ibm/msg/client/jms/JmsPropertyContext;>;
/*      */     //   36	189	3	producers	Ljava/util/Vector<Lcom/ibm/msg/client/jms/JmsPropertyContext;>;
/*      */     //   128	97	4	consumers	Ljava/util/Vector<Lcom/ibm/msg/client/jms/JmsPropertyContext;>;
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   50	69	72	javax/jms/JMSException
/*      */     //   50	69	94	java/util/NoSuchElementException
/*      */     //   143	163	166	javax/jms/JMSException
/*      */     //   143	163	188	java/util/NoSuchElementException
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
/*      */   private void closeT() {
/* 1241 */     if (Trace.isOn) {
/* 1242 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "closeT()");
/*      */     }
/*      */     
/* 1245 */     if (isStarted() && usingAsyncMode()) {
/*      */       try {
/* 1247 */         stop();
/*      */       }
/* 1249 */       catch (JMSException e) {
/* 1250 */         if (Trace.isOn) {
/* 1251 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "closeT()", (Throwable)e, 1);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1261 */     if (Trace.isOn) {
/* 1262 */       Trace.traceData(this, "getting Broker Response Lock", null);
/*      */     }
/* 1264 */     synchronized (this.brokerResponseLock) {
/* 1265 */       if (Trace.isOn) {
/* 1266 */         Trace.traceData(this, "got Broker Response Lock", null);
/*      */       }
/* 1268 */       if (this.replyQ != null) {
/*      */ 
/*      */ 
/*      */         
/* 1272 */         if (this.responseRequested == true) {
/*      */           try {
/* 1274 */             MQMsg2 response = new MQMsg2();
/* 1275 */             getServicesMgr().getBrokerResponse(this, response, false);
/*      */           
/*      */           }
/* 1278 */           catch (JMSException e) {
/* 1279 */             if (Trace.isOn) {
/* 1280 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "closeT()", (Throwable)e, 2);
/*      */             }
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/*      */         try {
/* 1287 */           this.replyQ.close();
/* 1288 */           this.replyQ = null;
/*      */         }
/* 1290 */         catch (MQException e) {
/* 1291 */           if (Trace.isOn) {
/* 1292 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "closeT()", (Throwable)e, 3);
/*      */           }
/*      */         } 
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
/* 1306 */     if (Trace.isOn) {
/* 1307 */       Trace.traceData(this, "releasing Broker Response Lock", null);
/*      */     }
/*      */ 
/*      */     
/* 1311 */     if (Trace.isOn) {
/* 1312 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "closeT()");
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
/*      */   public void commit() throws JMSException {
/* 1337 */     if (Trace.isOn) {
/* 1338 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commit()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1343 */     if (!this.transacted && this.acknowledgeMode != 2 && !this.calledByAsf) {
/* 1344 */       if (Trace.isOn) {
/* 1345 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commit()", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1350 */     if (this.calledByAsf || this.acknowledgeMode == 2) {
/*      */       
/*      */       try {
/* 1353 */         this.qm.commit();
/* 1354 */         setCommitRequired(false);
/*      */       }
/* 1356 */       catch (MQException mqe) {
/* 1357 */         if (Trace.isOn) {
/* 1358 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commit()", (Throwable)mqe);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1363 */       if (Trace.isOn) {
/* 1364 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commit()", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1369 */     if ((getServicesMgr()).queueSet) {
/* 1370 */       commitQ();
/*      */     }
/* 1372 */     if ((getServicesMgr()).pubSubSet) {
/* 1373 */       commitT();
/*      */     }
/*      */     else {
/*      */       
/* 1377 */       if (!this.transacted) {
/*      */         
/* 1379 */         String key = "MQJMS1019";
/* 1380 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 1381 */         IllegalStateException je = new IllegalStateException(msg, key);
/*      */         
/* 1383 */         if (Trace.isOn) {
/* 1384 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commit()", (Throwable)je, 1);
/*      */         }
/*      */         
/* 1387 */         throw je;
/*      */       } 
/*      */       
/* 1390 */       if (this.qm == null) {
/* 1391 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/* 1392 */         if (Trace.isOn) {
/* 1393 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commit()", (Throwable)je, 2);
/*      */         }
/*      */         
/* 1396 */         throw je;
/*      */       } 
/*      */     } 
/* 1399 */     if (Trace.isOn) {
/* 1400 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commit()", 3);
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
/*      */ 
/*      */ 
/*      */   
/*      */   private void commitQ() throws JMSException {
/* 1433 */     if (Trace.isOn) {
/* 1434 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitQ()");
/*      */     }
/*      */     try {
/* 1437 */       if (!this.transacted) {
/*      */ 
/*      */         
/* 1440 */         String key = "MQJMS1019";
/* 1441 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 1442 */         IllegalStateException je = new IllegalStateException(msg, key);
/*      */         
/* 1444 */         if (Trace.isOn) {
/* 1445 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitQ()", (Throwable)je, 1);
/*      */         }
/*      */         
/* 1448 */         throw je;
/*      */       } 
/*      */       
/* 1451 */       if (this.qm == null) {
/* 1452 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/* 1453 */         if (Trace.isOn) {
/* 1454 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitQ()", (Throwable)je, 2);
/*      */         }
/*      */         
/* 1457 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 1462 */         synchronized (getTransactionLock()) {
/* 1463 */           this.qm.commit();
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1468 */           setCommitRequired(false);
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 1473 */       catch (MQException e) {
/* 1474 */         JMSException je; if (Trace.isOn) {
/* 1475 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitQ()", (Throwable)e, 1);
/*      */         }
/*      */         
/* 1478 */         int compCode = e.completionCode;
/* 1479 */         int reasonCode = e.reasonCode;
/*      */ 
/*      */ 
/*      */         
/* 1483 */         if (compCode == 1 && reasonCode == 2003) {
/* 1484 */           String key = "MQJMS0011";
/* 1485 */           String msg = ConfigEnvironment.getErrorMessage(key);
/* 1486 */           TransactionRolledBackException transactionRolledBackException = new TransactionRolledBackException(msg, key);
/* 1487 */           transactionRolledBackException.setLinkedException((Exception)e);
/* 1488 */           transactionRolledBackException.initCause((Throwable)e);
/*      */         } else {
/*      */           
/* 1491 */           je = ConfigEnvironment.newException("MQJMS2009");
/* 1492 */           je.setLinkedException((Exception)e);
/*      */         } 
/*      */         
/* 1495 */         if (Trace.isOn) {
/* 1496 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitQ()", (Throwable)je, 3);
/*      */         }
/*      */         
/* 1499 */         throw je;
/*      */       }
/*      */     
/* 1502 */     } catch (JMSException je) {
/* 1503 */       if (Trace.isOn) {
/* 1504 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitQ()", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1507 */       if (Trace.isOn) {
/* 1508 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitQ()", (Throwable)je, 4);
/*      */       }
/*      */       
/* 1511 */       throw je;
/*      */     } 
/* 1513 */     if (Trace.isOn) {
/* 1514 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitQ()");
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
/*      */   private void commitT() throws JMSException {
/* 1537 */     if (Trace.isOn) {
/* 1538 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitT()");
/*      */     }
/*      */     try {
/* 1541 */       if (!this.transacted) {
/* 1542 */         String key = "MQJMS1019";
/* 1543 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 1544 */         IllegalStateException illegalStateException = new IllegalStateException(msg, key);
/* 1545 */         if (Trace.isOn) {
/* 1546 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitT()", (Throwable)illegalStateException, 1);
/*      */         }
/*      */         
/* 1549 */         throw illegalStateException;
/*      */       } 
/*      */       
/* 1552 */       if (this.qm == null) {
/* 1553 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/* 1554 */         if (Trace.isOn) {
/* 1555 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitT()", (Throwable)je, 2);
/*      */         }
/*      */         
/* 1558 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1567 */         synchronized (getTransactionLock()) {
/*      */ 
/*      */ 
/*      */           
/* 1571 */           boolean async = false;
/*      */           
/* 1573 */           async = (!this.outcomeNotification && supportsInherited());
/*      */           
/* 1575 */           if (async) {
/* 1576 */             this.qm.asyncCommit();
/*      */           } else {
/*      */             
/* 1579 */             this.qm.commit();
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1586 */           setCommitRequired(false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1592 */           if (this.responseOnCommit == true)
/*      */           {
/*      */ 
/*      */             
/* 1596 */             this.responseOnCommit = false;
/* 1597 */             this.responseRequested = true;
/* 1598 */             this.responsePutTime = System.currentTimeMillis();
/*      */ 
/*      */ 
/*      */             
/* 1602 */             if (getServicesMgr().checkForResponse(this) == true) {
/*      */               try {
/* 1604 */                 MQMsg2 response = new MQMsg2();
/*      */                 
/* 1606 */                 getServicesMgr().getBrokerResponse(this, response, false);
/*      */               
/*      */               }
/* 1609 */               catch (JMSException e) {
/* 1610 */                 if (Trace.isOn) {
/* 1611 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitT()", (Throwable)e, 1);
/*      */                 }
/*      */                 
/* 1614 */                 if (Trace.isOn) {
/* 1615 */                   Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitT()", (Throwable)e, 3);
/*      */                 }
/*      */                 
/* 1618 */                 throw e;
/*      */               }
/*      */             
/*      */             }
/*      */           }
/*      */         
/*      */         } 
/* 1625 */       } catch (MQException e) {
/* 1626 */         JMSException je; if (Trace.isOn) {
/* 1627 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitT()", (Throwable)e, 2);
/*      */         }
/*      */         
/* 1630 */         int compCode = e.completionCode;
/* 1631 */         int reasonCode = e.reasonCode;
/*      */ 
/*      */ 
/*      */         
/* 1635 */         if (compCode == 1 && reasonCode == 2003) {
/* 1636 */           TransactionRolledBackException transactionRolledBackException = new TransactionRolledBackException(ConfigEnvironment.getMessage("MQJMS0011"));
/* 1637 */           transactionRolledBackException.setLinkedException((Exception)e);
/* 1638 */           transactionRolledBackException.initCause((Throwable)e);
/*      */         } else {
/*      */           
/* 1641 */           je = ConfigEnvironment.newException("MQJMS2009");
/* 1642 */           je.setLinkedException((Exception)e);
/*      */         } 
/*      */         
/* 1645 */         if (this.responseOnCommit == true) {
/* 1646 */           this.responseOnCommit = false;
/*      */         }
/*      */         
/* 1649 */         if (Trace.isOn) {
/* 1650 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitT()", (Throwable)je, 4);
/*      */         }
/*      */         
/* 1653 */         throw je;
/*      */       }
/*      */     
/*      */     }
/* 1657 */     catch (JMSException je) {
/* 1658 */       if (Trace.isOn) {
/* 1659 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitT()", (Throwable)je, 3);
/*      */       }
/*      */       
/* 1662 */       if (Trace.isOn) {
/* 1663 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitT()", (Throwable)je, 5);
/*      */       }
/*      */       
/* 1666 */       throw je;
/*      */     } 
/* 1668 */     if (Trace.isOn) {
/* 1669 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "commitT()");
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
/*      */   private ProviderMessage consume(ProviderMessageReference mRef) throws JMSException {
/* 1692 */     if (Trace.isOn) {
/* 1693 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "consume(ProviderMessageReference)", new Object[] { mRef });
/*      */     }
/*      */     
/* 1696 */     int reasonCode = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1703 */       if (!(mRef instanceof MQMessageReference)) {
/* 1704 */         JMSException je = new JMSException("MQJMS1096");
/* 1705 */         if (Trace.isOn) {
/* 1706 */           Trace.traceData(this, "MessageReference is not an MQMessageReference.", null);
/*      */         }
/* 1708 */         if (Trace.isOn) {
/* 1709 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "consume(ProviderMessageReference)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 1712 */         throw je;
/*      */       } 
/*      */       
/* 1715 */       MQMessageReference mr = (MQMessageReference)mRef;
/*      */       
/* 1717 */       String queueName = mr.getReferenceQueue();
/* 1718 */       if (Trace.isOn) {
/* 1719 */         Trace.traceData(this, "Got referenceQueueName = '" + queueName + "'", null);
/*      */       }
/*      */       
/* 1722 */       this.messageQueueName = queueName;
/*      */ 
/*      */       
/* 1725 */       MQGetMessageOptions gmo = new MQGetMessageOptions(true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1732 */       MQQueueManager qm = getQM();
/*      */ 
/*      */       
/* 1735 */       if (Trace.isOn) {
/* 1736 */         Trace.traceData(this, "Setting up GMO", null);
/*      */       }
/*      */ 
/*      */       
/* 1740 */       if (mr.checkIfOptionalFieldIsPresent((byte)1) == true) {
/* 1741 */         System.arraycopy(mr.getMsgToken(), 0, gmo.msgToken, 0, 16);
/* 1742 */         gmo.matchOptions = 32;
/*      */       } else {
/*      */         
/* 1745 */         gmo.matchOptions = 1;
/*      */       } 
/*      */       
/* 1748 */       gmo.options = 8256;
/*      */ 
/*      */ 
/*      */       
/* 1752 */       if (calculateUseSPIP()) {
/* 1753 */         gmo.options |= 0x1000;
/*      */       } else {
/*      */         
/* 1756 */         gmo.options |= 0x2;
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
/*      */       try {
/* 1778 */         if (this.cbInputQueue == null || this.lastQueueName == null || !this.lastQueueName.equals(this.messageQueueName)) {
/* 1779 */           if (Trace.isOn) {
/* 1780 */             Trace.traceData(this, "MQQueue " + this.messageQueueName + " to be opened", null);
/*      */           }
/* 1782 */           if (this.cbInputQueue != null) {
/* 1783 */             this.cbInputQueue.close();
/*      */           }
/*      */           
/* 1786 */           this.cbInputQueue = qm.accessQueue(this.messageQueueName, 8194);
/*      */           
/* 1788 */           this.lastQueueName = this.messageQueueName;
/* 1789 */           if (Trace.isOn) {
/* 1790 */             Trace.traceData(this, "Got access to " + this.cbInputQueue.name, null);
/*      */           
/*      */           }
/*      */         }
/* 1794 */         else if (Trace.isOn) {
/* 1795 */           Trace.traceData(this, "using cached queue for " + this.messageQueueName, null);
/*      */         }
/*      */       
/*      */       }
/* 1799 */       catch (MQException mqe) {
/* 1800 */         if (Trace.isOn) {
/* 1801 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "consume(ProviderMessageReference)", (Throwable)mqe, 1);
/*      */         }
/*      */         
/* 1804 */         if (mqe.completionCode == 2) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1811 */           if (Trace.isOn) {
/* 1812 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "consume(ProviderMessageReference)", (Throwable)mqe, 2);
/*      */           }
/*      */           
/* 1815 */           throw mqe;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1823 */       JMSMessage outMessage = null;
/*      */       
/* 1825 */       if (this.cbInputQueue != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1836 */         if (mr.getDataQuantity() == 2) {
/*      */           
/* 1838 */           outMessage = mr.getJMSMessage();
/*      */ 
/*      */ 
/*      */           
/* 1842 */           MQJMSMessage dummyMessage = mr.getMQJMSMessage();
/* 1843 */           if (dummyMessage == null) {
/*      */             
/* 1845 */             dummyMessage = new MQJMSMessage();
/* 1846 */             dummyMessage.setMessageId(mr.getMessageId());
/* 1847 */             dummyMessage.setCorrelationId(mr.getCorrelId());
/*      */           } 
/* 1849 */           reasonCode = this.cbInputQueue.getMsg2NoExc(dummyMessage, gmo, mr.getMsgDataLength());
/* 1850 */           if (reasonCode == 2079) {
/* 1851 */             reasonCode = 0;
/*      */           }
/* 1853 */           if (reasonCode != 0) {
/* 1854 */             outMessage = null;
/*      */           }
/*      */         } else {
/*      */           
/* 1858 */           MQJMSMessage dummyMessage = mr.getMQJMSMessage();
/* 1859 */           if (dummyMessage == null) {
/* 1860 */             dummyMessage = new MQJMSMessage();
/* 1861 */             dummyMessage.setMessageId(mr.getMessageId());
/* 1862 */             dummyMessage.setCorrelationId(mr.getCorrelId());
/*      */           } 
/* 1864 */           gmo.options &= 0xFFFFFFBF;
/* 1865 */           reasonCode = this.cbInputQueue.getMsg2NoExc(dummyMessage, gmo);
/*      */           
/* 1867 */           if (reasonCode == 0) {
/* 1868 */             outMessage = dummyMessage.createJMSMessage(this, mr.getDestination());
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1883 */         if (reasonCode == 2033) {
/* 1884 */           if (Trace.isOn) {
/* 1885 */             Trace.traceData(this, "NO_MSG_AVAILABLE - message removed from queue", null);
/*      */           }
/* 1887 */           if (Trace.isOn) {
/* 1888 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "consume(ProviderMessageReference)", null, 1);
/*      */           }
/*      */           
/* 1891 */           return null;
/*      */         } 
/* 1893 */         if (reasonCode != 0) {
/* 1894 */           if (Trace.isOn) {
/* 1895 */             Trace.traceData(this, "Got MQRC " + reasonCode, null);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1901 */           MQException traceRet1 = new MQException(2, reasonCode, this);
/* 1902 */           if (Trace.isOn) {
/* 1903 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "consume(ProviderMessageReference)", (Throwable)traceRet1, 3);
/*      */           }
/*      */           
/* 1906 */           throw traceRet1;
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1912 */         if (Trace.isOn) {
/* 1913 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "consume(ProviderMessageReference)", null, 2);
/*      */         }
/*      */         
/* 1916 */         return null;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1921 */       if (!getTransacted()) {
/* 1922 */         outMessage._setSession(this);
/*      */       }
/*      */ 
/*      */       
/* 1926 */       outMessage.setGotByConsume(true);
/*      */       
/* 1928 */       if (Trace.isOn) {
/* 1929 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "consume(ProviderMessageReference)", outMessage, 3);
/*      */       }
/*      */       
/* 1932 */       return outMessage;
/*      */     
/*      */     }
/* 1935 */     catch (MQException mqe) {
/* 1936 */       if (Trace.isOn) {
/* 1937 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "consume(ProviderMessageReference)", (Throwable)mqe, 2);
/*      */       }
/*      */       
/* 1940 */       JMSException je = ConfigEnvironment.newException("MQJMS2002");
/* 1941 */       je.setLinkedException((Exception)mqe);
/*      */ 
/*      */       
/* 1944 */       this.connection.deliverException(je);
/*      */       
/* 1946 */       if (Trace.isOn) {
/* 1947 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "consume(ProviderMessageReference)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 1950 */       throw je;
/*      */     }
/* 1952 */     catch (IOException ioe) {
/* 1953 */       if (Trace.isOn) {
/* 1954 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "consume(ProviderMessageReference)", ioe, 3);
/*      */       }
/*      */       
/* 1957 */       if (Trace.isOn) {
/* 1958 */         Trace.traceData(this, "badly formatted message.", null);
/*      */       }
/* 1960 */       JMSException je = ConfigEnvironment.newException("MQJMS0006");
/* 1961 */       je.setLinkedException(ioe);
/*      */       
/* 1963 */       if (Trace.isOn) {
/* 1964 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "consume(ProviderMessageReference)", (Throwable)je, 5);
/*      */       }
/*      */       
/* 1967 */       throw je;
/*      */     }
/* 1969 */     catch (JMSException je) {
/* 1970 */       if (Trace.isOn) {
/* 1971 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "consume(ProviderMessageReference)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1976 */       this.connection.deliverException(je);
/*      */       
/* 1978 */       if (Trace.isOn) {
/* 1979 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "consume(ProviderMessageReference)", (Throwable)je, 6);
/*      */       }
/*      */       
/* 1982 */       throw je;
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
/*      */   private MQQueueBrowser createBrowser(WMQDestination queue, String messageSelector) throws JMSException {
/* 2010 */     if (Trace.isOn) {
/* 2011 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createBrowser(WMQDestination,String)", new Object[] { queue, messageSelector });
/*      */     }
/*      */     
/*      */     try {
/* 2015 */       if (queue == null || !queue.isQueue()) {
/* 2016 */         String key = "MQJMS0003";
/* 2017 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 2018 */         InvalidDestinationException je = new InvalidDestinationException(msg, key);
/* 2019 */         if (Trace.isOn) {
/* 2020 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createBrowser(WMQDestination,String)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 2023 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 2027 */       addQueueServices();
/*      */       
/* 2029 */       WMQDestination jmsQueue = queue;
/* 2030 */       String qmgrName = jmsQueue.getStringProperty("XMSC_WMQ_QUEUE_MANAGER");
/*      */ 
/*      */       
/* 2033 */       getServicesMgr().checkQueueAccess(jmsQueue, this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2039 */       if (qmgrName != null && !qmgrName.equals("") && !qmgrName.equals(this.resolvedQmName) && !qmgrName.equals(this.resolvedQSGName)) {
/* 2040 */         JMSException je = ConfigEnvironment.newException("MQJMS1017");
/* 2041 */         if (Trace.isOn) {
/* 2042 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createBrowser(WMQDestination,String)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 2045 */         throw je;
/*      */       } 
/*      */       
/* 2048 */       String queueName = jmsQueue.getName();
/* 2049 */       if (queueName == null) {
/* 2050 */         String key = "MQJMS0003";
/* 2051 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 2052 */         InvalidDestinationException je = new InvalidDestinationException(msg, key);
/* 2053 */         if (Trace.isOn) {
/* 2054 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createBrowser(WMQDestination,String)", (Throwable)je, 3);
/*      */         }
/*      */         
/* 2057 */         throw je;
/*      */       } 
/*      */       
/* 2060 */       MQQueueBrowser browse = new MQQueueBrowser(queue, messageSelector, this);
/*      */ 
/*      */       
/* 2063 */       if (Trace.isOn) {
/* 2064 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createBrowser(WMQDestination,String)", browse);
/*      */       }
/*      */       
/* 2067 */       return browse;
/*      */     }
/* 2069 */     catch (JMSException je) {
/* 2070 */       if (Trace.isOn) {
/* 2071 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createBrowser(WMQDestination,String)", (Throwable)je);
/*      */       }
/*      */       
/* 2074 */       if (Trace.isOn) {
/* 2075 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createBrowser(WMQDestination,String)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 2078 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ProviderQueueBrowser createBrowser(ProviderDestination destination, String selector, JmsPropertyContext propertyContext) throws JMSException {
/* 2089 */     if (Trace.isOn) {
/* 2090 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createBrowser(ProviderDestination,String,JmsPropertyContext)", new Object[] { destination, selector, propertyContext });
/*      */     }
/*      */ 
/*      */     
/* 2094 */     MQQueueBrowser mqQueueBrowser = createBrowser((WMQDestination)destination, selector);
/* 2095 */     mqQueueBrowser.setPropertyContext(propertyContext);
/* 2096 */     if (Trace.isOn) {
/* 2097 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createBrowser(ProviderDestination,String,JmsPropertyContext)", mqQueueBrowser);
/*      */     }
/*      */     
/* 2100 */     return mqQueueBrowser;
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
/*      */   public ProviderBytesMessage createBytesMessage() throws JMSException {
/* 2113 */     if (Trace.isOn) {
/* 2114 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createBytesMessage()");
/*      */     }
/*      */ 
/*      */     
/* 2118 */     ProviderBytesMessage traceRet1 = new JMSBytesMessage(jmsStrings);
/* 2119 */     if (Trace.isOn) {
/* 2120 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createBytesMessage()", traceRet1);
/*      */     }
/*      */     
/* 2123 */     return traceRet1;
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQMessageConsumer createConsumer(ProviderDestination destination, String messageSelector, boolean noLocal) throws JMSException {
/* 2170 */     if (Trace.isOn) {
/* 2171 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createConsumer(ProviderDestination,String,boolean)", new Object[] { destination, messageSelector, 
/*      */             
/* 2173 */             Boolean.valueOf(noLocal) });
/*      */     }
/*      */     try {
/* 2176 */       if (destination.isQueue()) {
/*      */ 
/*      */ 
/*      */         
/* 2180 */         MQMessageConsumer qMsgConsumer = createQConsumer((WMQDestination)destination, messageSelector);
/* 2181 */         addConsumer(qMsgConsumer);
/* 2182 */         if (Trace.isOn) {
/* 2183 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createConsumer(ProviderDestination,String,boolean)", qMsgConsumer, 1);
/*      */         }
/*      */         
/* 2186 */         return qMsgConsumer;
/*      */       } 
/*      */       
/* 2189 */       if (destination.isTopic()) {
/* 2190 */         MQMessageConsumer tMsgConsumer = createTConsumer((WMQDestination)destination, messageSelector, noLocal);
/* 2191 */         addConsumer(tMsgConsumer);
/* 2192 */         if (Trace.isOn) {
/* 2193 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createConsumer(ProviderDestination,String,boolean)", tMsgConsumer, 2);
/*      */         }
/*      */         
/* 2196 */         return tMsgConsumer;
/*      */       } 
/*      */ 
/*      */       
/* 2200 */       String key = "MQJMS0003";
/* 2201 */       String msg = ConfigEnvironment.getErrorMessage(key);
/* 2202 */       InvalidDestinationException je = new InvalidDestinationException(msg, key);
/* 2203 */       if (Trace.isOn) {
/* 2204 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createConsumer(ProviderDestination,String,boolean)", (Throwable)je, 1);
/*      */       }
/*      */       
/* 2207 */       throw je;
/*      */     
/*      */     }
/* 2210 */     catch (JMSException jexc) {
/* 2211 */       if (Trace.isOn) {
/* 2212 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createConsumer(ProviderDestination,String,boolean)", (Throwable)jexc);
/*      */       }
/*      */       
/* 2215 */       if (Trace.isOn) {
/* 2216 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createConsumer(ProviderDestination,String,boolean)", (Throwable)jexc, 2);
/*      */       }
/*      */       
/* 2219 */       throw jexc;
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
/*      */   public ProviderMessageConsumer createConsumer(ProviderDestination destination, String selector, boolean nolocal, JmsPropertyContext propertyContext) throws JMSException {
/* 2234 */     if (Trace.isOn) {
/* 2235 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createConsumer(ProviderDestination,String,boolean,JmsPropertyContext)", new Object[] { destination, selector, 
/*      */             
/* 2237 */             Boolean.valueOf(nolocal), propertyContext });
/*      */     }
/* 2239 */     MQMessageConsumer mqConsumer = createConsumer(destination, selector, nolocal);
/* 2240 */     mqConsumer.setPropertyContext(propertyContext);
/* 2241 */     if (Trace.isOn) {
/* 2242 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createConsumer(ProviderDestination,String,boolean,JmsPropertyContext)", mqConsumer);
/*      */     }
/*      */     
/* 2245 */     return mqConsumer;
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
/*      */   private MQMessageConsumer createDurableSubscriber(WMQDestination topic, String name, String selector, boolean noLocal) throws JMSException {
/* 2285 */     if (Trace.isOn) {
/* 2286 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createDurableSubscriber(WMQDestination,String,String,boolean)", new Object[] { topic, name, selector, 
/*      */             
/* 2288 */             Boolean.valueOf(noLocal) });
/*      */     }
/* 2290 */     MQSubscription subscription = null;
/* 2291 */     MQMessageConsumer retSub = null;
/* 2292 */     String durSubQName = null;
/*      */     
/* 2294 */     boolean shared_queue = true;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2299 */       addPubSubServices();
/* 2300 */       if (this.qm == null) {
/* 2301 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/* 2302 */         if (Trace.isOn) {
/* 2303 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createDurableSubscriber(WMQDestination,String,String,boolean)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 2306 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2311 */       if (!topic.isTopic()) {
/* 2312 */         String key = "MQJMS0003";
/* 2313 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 2314 */         InvalidDestinationException je = new InvalidDestinationException(msg, key);
/*      */         
/* 2316 */         if (Trace.isOn) {
/* 2317 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createDurableSubscriber(WMQDestination,String,String,boolean)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 2320 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2326 */       if (name == null || name.trim().equals("")) {
/* 2327 */         String key = "MQJMS3039";
/* 2328 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 2329 */         InvalidDestinationException invalidDestinationException = new InvalidDestinationException(msg, key);
/* 2330 */         if (Trace.isOn) {
/* 2331 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createDurableSubscriber(WMQDestination,String,String,boolean)", (Throwable)invalidDestinationException, 3);
/*      */         }
/*      */         
/* 2334 */         throw invalidDestinationException;
/*      */       } 
/*      */ 
/*      */       
/* 2338 */       WMQDestination mqtopic = topic;
/* 2339 */       if (mqtopic.containsAnyWildcard())
/*      */       {
/* 2341 */         if (mqtopic.getIntProperty("brokerVersion") != this.connection.getBrkVersion()) {
/* 2342 */           if (Trace.isOn) {
/* 2343 */             Trace.traceData(this, "Invalid wildcard.", null);
/*      */           }
/*      */           
/* 2346 */           String key = "MQJMS0003";
/* 2347 */           String msg = ConfigEnvironment.getErrorMessage(key);
/* 2348 */           InvalidDestinationException je = new InvalidDestinationException(msg, key);
/*      */           
/* 2350 */           if (Trace.isOn) {
/* 2351 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createDurableSubscriber(WMQDestination,String,String,boolean)", (Throwable)je, 4);
/*      */           }
/*      */           
/* 2354 */           throw je;
/*      */         } 
/*      */       }
/* 2357 */       if (topic.isTemporary()) {
/*      */ 
/*      */         
/* 2360 */         String key = "MQJMS0003";
/* 2361 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 2362 */         InvalidDestinationException invalidDestinationException = new InvalidDestinationException(msg, key);
/* 2363 */         if (Trace.isOn) {
/* 2364 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createDurableSubscriber(WMQDestination,String,String,boolean)", (Throwable)invalidDestinationException, 5);
/*      */         }
/*      */         
/* 2367 */         throw invalidDestinationException;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2377 */       durSubQName = topic.getStringProperty("brokerDurSubQueue");
/* 2378 */       if (durSubQName.trim().equals("")) {
/*      */ 
/*      */ 
/*      */         
/* 2382 */         durSubQName = "SYSTEM.JMS.D.*";
/*      */ 
/*      */       
/*      */       }
/* 2386 */       else if (!durSubQName.startsWith("SYSTEM.JMS.D.")) {
/* 2387 */         JMSException je = ConfigEnvironment.newException("MQJMS3021", durSubQName);
/*      */         
/* 2389 */         if (Trace.isOn) {
/* 2390 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createDurableSubscriber(WMQDestination,String,String,boolean)", (Throwable)je, 6);
/*      */         }
/*      */         
/* 2393 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2399 */       if (durSubQName.endsWith("*")) {
/* 2400 */         shared_queue = false;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2405 */       if (selector != null && !selector.equals("")) {
/*      */         try {
/* 2407 */           MQMessageSelector tempSelector = new MQMessageSelector();
/* 2408 */           tempSelector.setSelector(selector);
/*      */ 
/*      */         
/*      */         }
/* 2412 */         catch (SyntaxException e) {
/* 2413 */           if (Trace.isOn) {
/* 2414 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createDurableSubscriber(WMQDestination,String,String,boolean)", (Throwable)e, 1);
/*      */           }
/*      */           
/* 2417 */           String key = "MQJMS0004";
/* 2418 */           String msg = ConfigEnvironment.getErrorMessage(key);
/* 2419 */           InvalidSelectorException je = new InvalidSelectorException(msg, key);
/* 2420 */           je.setLinkedException((Exception)e);
/* 2421 */           je.initCause((Throwable)e);
/* 2422 */           if (Trace.isOn) {
/* 2423 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createDurableSubscriber(WMQDestination,String,String,boolean)", (Throwable)je, 7);
/*      */           }
/*      */           
/* 2426 */           throw je;
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2434 */       if (Trace.isOn) {
/* 2435 */         Trace.traceData(this, "Topic name = " + topic.getName(), null);
/* 2436 */         Trace.traceData(this, "Sub Q name = " + durSubQName, null);
/* 2437 */         Trace.traceData(this, "Name       = " + name, null);
/* 2438 */         Trace.traceData(this, "Selector   = " + selector, null);
/* 2439 */         Trace.traceData(this, "noLocal    = " + noLocal, null);
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 2444 */         subscription = this.connection.getSubscriptionEngine().openDurableSubscription(this, mqtopic, selector, noLocal, shared_queue, durSubQName, name);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2454 */         retSub = new MQMessageConsumer(mqtopic, this, subscription, this.transacted, this.acknowledgeMode);
/*      */ 
/*      */ 
/*      */         
/* 2458 */         addConsumer(retSub);
/*      */       }
/* 2460 */       catch (JMSException je) {
/* 2461 */         if (Trace.isOn) {
/* 2462 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createDurableSubscriber(WMQDestination,String,String,boolean)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 2465 */         if (Trace.isOn) {
/* 2466 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createDurableSubscriber(WMQDestination,String,String,boolean)", (Throwable)je, 8);
/*      */         }
/*      */         
/* 2469 */         throw je;
/*      */       }
/*      */     
/* 2472 */     } catch (JMSException je) {
/* 2473 */       if (Trace.isOn) {
/* 2474 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createDurableSubscriber(WMQDestination,String,String,boolean)", (Throwable)je, 3);
/*      */       }
/*      */       
/* 2477 */       if (Trace.isOn) {
/* 2478 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createDurableSubscriber(WMQDestination,String,String,boolean)", (Throwable)je, 9);
/*      */       }
/*      */       
/* 2481 */       throw je;
/*      */     } 
/* 2483 */     if (Trace.isOn) {
/* 2484 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createDurableSubscriber(WMQDestination,String,String,boolean)", retSub);
/*      */     }
/*      */     
/* 2487 */     return retSub;
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
/*      */   public ProviderMessageConsumer createDurableSubscriber(ProviderDestination destination, String subscriptionName, String selector, boolean nolocal, JmsPropertyContext propertyContext) throws JMSException {
/* 2502 */     if (Trace.isOn) {
/* 2503 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createDurableSubscriber(ProviderDestination,String,String,boolean,JmsPropertyContext)", new Object[] { destination, subscriptionName, selector, 
/*      */             
/* 2505 */             Boolean.valueOf(nolocal), propertyContext });
/*      */     }
/*      */     
/* 2508 */     MQMessageConsumer mqTopicSubscriber = createDurableSubscriber((WMQDestination)destination, subscriptionName, selector, nolocal);
/* 2509 */     mqTopicSubscriber.setPropertyContext(propertyContext);
/* 2510 */     if (Trace.isOn) {
/* 2511 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createDurableSubscriber(ProviderDestination,String,String,boolean,JmsPropertyContext)", mqTopicSubscriber);
/*      */     }
/*      */ 
/*      */     
/* 2515 */     return mqTopicSubscriber;
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
/*      */   public ProviderMapMessage createMapMessage() throws JMSException {
/* 2528 */     if (Trace.isOn) {
/* 2529 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createMapMessage()");
/*      */     }
/*      */     
/* 2532 */     JMSMapMessage message = new JMSMapMessage(jmsStrings);
/* 2533 */     message.mapNameStyle = this.mapNameStyle;
/* 2534 */     if (Trace.isOn) {
/* 2535 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createMapMessage()", message);
/*      */     }
/*      */     
/* 2538 */     return message;
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
/*      */   public ProviderMessage createMessage() throws JMSException {
/* 2552 */     if (Trace.isOn) {
/* 2553 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createMessage()");
/*      */     }
/*      */     
/* 2556 */     ProviderMessage traceRet1 = new JMSNullMessage(jmsStrings);
/* 2557 */     if (Trace.isOn) {
/* 2558 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createMessage()", traceRet1);
/*      */     }
/*      */     
/* 2561 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String createMessageID() {
/* 2570 */     if (Trace.isOn) {
/* 2571 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createMessageID()");
/*      */     }
/*      */ 
/*      */     
/* 2575 */     if (Trace.isOn) {
/* 2576 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createMessageID()", null);
/*      */     }
/*      */     
/* 2579 */     return null;
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
/*      */   public ProviderObjectMessage createObjectMessage() throws JMSException {
/* 2592 */     if (Trace.isOn) {
/* 2593 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createObjectMessage()");
/*      */     }
/*      */     
/* 2596 */     ProviderObjectMessage traceRet1 = new JMSObjectMessage(jmsStrings);
/* 2597 */     if (Trace.isOn) {
/* 2598 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createObjectMessage()", traceRet1);
/*      */     }
/*      */     
/* 2601 */     return traceRet1;
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
/*      */   public ProviderObjectMessage createObjectMessage(Serializable object) throws JMSException {
/* 2616 */     if (Trace.isOn) {
/* 2617 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createObjectMessage(Serializable)", new Object[] { object });
/*      */     }
/*      */     
/* 2620 */     ProviderObjectMessage traceRet1 = new JMSObjectMessage(jmsStrings, object);
/* 2621 */     if (Trace.isOn) {
/* 2622 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createObjectMessage(Serializable)", traceRet1);
/*      */     }
/*      */     
/* 2625 */     return traceRet1;
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
/*      */   private MQMessageProducer createProducer(ProviderDestination destination) throws JMSException {
/* 2654 */     if (Trace.isOn) {
/* 2655 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createProducer(ProviderDestination)", new Object[] { destination });
/*      */     }
/*      */     
/*      */     try {
/* 2659 */       if (destination == null) {
/* 2660 */         if (this.qm == null) {
/* 2661 */           JMSException jMSException = ConfigEnvironment.newException("MQJMS2004");
/* 2662 */           if (Trace.isOn) {
/* 2663 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createProducer(ProviderDestination)", (Throwable)jMSException, 1);
/*      */           }
/*      */           
/* 2666 */           throw jMSException;
/*      */         } 
/* 2668 */         MQMessageProducer qMsgProducer = new MQMessageProducer(null, this.qm, this.connection, this);
/* 2669 */         addProducer(qMsgProducer);
/* 2670 */         if (Trace.isOn) {
/* 2671 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createProducer(ProviderDestination)", qMsgProducer, 1);
/*      */         }
/*      */         
/* 2674 */         return qMsgProducer;
/*      */       } 
/* 2676 */       if (destination.isQueue()) {
/* 2677 */         MQMessageProducer qMsgProducer = createQProducer((WMQDestination)destination);
/* 2678 */         addProducer(qMsgProducer);
/*      */         
/* 2680 */         if (Trace.isOn) {
/* 2681 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createProducer(ProviderDestination)", qMsgProducer, 2);
/*      */         }
/*      */         
/* 2684 */         return qMsgProducer;
/*      */       } 
/* 2686 */       if (destination.isTopic()) {
/* 2687 */         MQMessageProducer tMsgProducer = (MQMessageProducer)createTProducer((WMQDestination)destination);
/* 2688 */         addProducer(tMsgProducer);
/* 2689 */         if (Trace.isOn) {
/* 2690 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createProducer(ProviderDestination)", tMsgProducer, 3);
/*      */         }
/*      */         
/* 2693 */         return tMsgProducer;
/*      */       } 
/*      */ 
/*      */       
/* 2697 */       String key = "MQJMS0003";
/* 2698 */       String msg = ConfigEnvironment.getErrorMessage(key);
/* 2699 */       InvalidDestinationException je = new InvalidDestinationException(msg, key);
/* 2700 */       if (Trace.isOn) {
/* 2701 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createProducer(ProviderDestination)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 2704 */       throw je;
/*      */     
/*      */     }
/* 2707 */     catch (JMSException jexc) {
/* 2708 */       if (Trace.isOn) {
/* 2709 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createProducer(ProviderDestination)", (Throwable)jexc);
/*      */       }
/*      */       
/* 2712 */       if (Trace.isOn) {
/* 2713 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createProducer(ProviderDestination)", (Throwable)jexc, 3);
/*      */       }
/*      */       
/* 2716 */       throw jexc;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ProviderMessageProducer createProducer(ProviderDestination destination, JmsPropertyContext propertyContext) throws JMSException {
/* 2727 */     if (Trace.isOn) {
/* 2728 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createProducer(ProviderDestination,JmsPropertyContext)", new Object[] { destination, propertyContext });
/*      */     }
/*      */ 
/*      */     
/* 2732 */     MQMessageProducer mqProducer = createProducer(destination);
/* 2733 */     mqProducer.setPropertyContext(propertyContext);
/* 2734 */     if (Trace.isOn) {
/* 2735 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createProducer(ProviderDestination,JmsPropertyContext)", mqProducer);
/*      */     }
/*      */     
/* 2738 */     return mqProducer;
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
/*      */   private MQMessageConsumer createQConsumer(WMQDestination queue, String messageSelector) throws JMSException {
/* 2758 */     if (Trace.isOn) {
/* 2759 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createQConsumer(WMQDestination,String)", new Object[] { queue, messageSelector });
/*      */     }
/*      */     
/* 2762 */     MQQueue mqqueue = null;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2767 */       addQueueServices();
/*      */ 
/*      */ 
/*      */       
/* 2771 */       if (this.qm == null) {
/* 2772 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/* 2773 */         if (Trace.isOn) {
/* 2774 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createQConsumer(WMQDestination,String)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 2777 */         throw je;
/*      */       } 
/*      */       
/* 2780 */       if (!queue.isQueue()) {
/* 2781 */         String key = "MQJMS0003";
/* 2782 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 2783 */         InvalidDestinationException je = new InvalidDestinationException(msg, key);
/* 2784 */         if (Trace.isOn) {
/* 2785 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createQConsumer(WMQDestination,String)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 2788 */         throw je;
/*      */       } 
/*      */       
/* 2791 */       WMQDestination queueSpec = queue;
/* 2792 */       if (Trace.isOn) {
/* 2793 */         Trace.traceData(this, "queueSpec: " + queueSpec, null);
/*      */       }
/*      */ 
/*      */       
/* 2797 */       getServicesMgr().checkQueueAccess(queueSpec, this);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2802 */       String qName = queueSpec.getName();
/*      */       
/* 2804 */       String qMgrName = queueSpec.getStringProperty("XMSC_WMQ_QUEUE_MANAGER");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2809 */       if (qMgrName != null && !qMgrName.equals("") && !qMgrName.equals(this.resolvedQmName) && !qMgrName.equals(this.resolvedQSGName)) {
/*      */ 
/*      */         
/* 2812 */         if (Trace.isOn) {
/* 2813 */           Trace.traceData(this, "qmgr " + qMgrName + " != " + this.resolvedQmName, null);
/*      */         }
/* 2815 */         JMSException je = ConfigEnvironment.newException("MQJMS1017");
/*      */         
/* 2817 */         if (Trace.isOn) {
/* 2818 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createQConsumer(WMQDestination,String)", (Throwable)je, 3);
/*      */         }
/*      */         
/* 2821 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 2825 */       int openOptions = 137;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2830 */       openOptions |= 0x20;
/*      */ 
/*      */       
/* 2833 */       int fiqBehaviour = queueSpec.getFailIfQuiesce();
/*      */       
/* 2835 */       if (fiqBehaviour == 1)
/*      */       {
/*      */         
/* 2838 */         openOptions |= 0x2000;
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/* 2843 */         mqqueue = this.qm.accessQueue(qName, openOptions);
/*      */       }
/* 2845 */       catch (MQException e) {
/* 2846 */         if (Trace.isOn) {
/* 2847 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createQConsumer(WMQDestination,String)", (Throwable)e, 1);
/*      */         }
/*      */         
/* 2850 */         JMSException je = getServicesMgr().getQueueOpenException(e);
/* 2851 */         if (Trace.isOn) {
/* 2852 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createQConsumer(WMQDestination,String)", (Throwable)je, 4);
/*      */         }
/*      */         
/* 2855 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2860 */       MQMessageConsumer msgConsumer = new MQMessageConsumer(queueSpec, messageSelector, mqqueue, this.transacted, this.acknowledgeMode, this);
/*      */       
/* 2862 */       if (Trace.isOn) {
/* 2863 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createQConsumer(WMQDestination,String)", msgConsumer);
/*      */       }
/*      */       
/* 2866 */       return msgConsumer;
/*      */     }
/* 2868 */     catch (JMSException je) {
/* 2869 */       if (Trace.isOn) {
/* 2870 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createQConsumer(WMQDestination,String)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 2873 */       if (mqqueue != null) {
/*      */         
/*      */         try {
/*      */           
/* 2877 */           mqqueue.close();
/*      */         }
/* 2879 */         catch (MQException mqe) {
/* 2880 */           if (Trace.isOn) {
/* 2881 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createQConsumer(WMQDestination,String)", (Throwable)mqe, 3);
/*      */           }
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 2887 */       if (Trace.isOn) {
/* 2888 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createQConsumer(WMQDestination,String)", (Throwable)je, 5);
/*      */       }
/*      */       
/* 2891 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQMessageProducer createQProducer(WMQDestination queue) throws JMSException {
/* 2902 */     if (Trace.isOn) {
/* 2903 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createQProducer(WMQDestination)", new Object[] { queue });
/*      */     }
/*      */     
/*      */     try {
/* 2907 */       WMQDestination queueSpec = null;
/* 2908 */       MQQueue mqqueue = null;
/*      */ 
/*      */       
/* 2911 */       addQueueServices();
/*      */       
/* 2913 */       if (this.qm == null) {
/* 2914 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/* 2915 */         if (Trace.isOn) {
/* 2916 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createQProducer(WMQDestination)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 2919 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2924 */       if (queue != null) {
/*      */ 
/*      */         
/* 2927 */         if (!queue.isQueue()) {
/* 2928 */           String key = "MQJMS0003";
/* 2929 */           String msg = ConfigEnvironment.getErrorMessage(key);
/* 2930 */           InvalidDestinationException je = new InvalidDestinationException(msg, key);
/* 2931 */           if (Trace.isOn) {
/* 2932 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createQProducer(WMQDestination)", (Throwable)je, 2);
/*      */           }
/*      */           
/* 2935 */           throw je;
/*      */         } 
/*      */ 
/*      */         
/* 2939 */         queueSpec = queue;
/* 2940 */         mqqueue = getServicesMgr().getOutputQueue(queueSpec, this);
/*      */       } 
/*      */ 
/*      */       
/* 2944 */       MQMessageProducer msgProducer = new MQMessageProducer(queueSpec, mqqueue, this);
/*      */       
/* 2946 */       if (Trace.isOn) {
/* 2947 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createQProducer(WMQDestination)", msgProducer);
/*      */       }
/*      */       
/* 2950 */       return msgProducer;
/*      */     }
/* 2952 */     catch (JMSException je) {
/* 2953 */       if (Trace.isOn) {
/* 2954 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createQProducer(WMQDestination)", (Throwable)je);
/*      */       }
/*      */       
/* 2957 */       if (Trace.isOn) {
/* 2958 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createQProducer(WMQDestination)", (Throwable)je, 3);
/*      */       }
/*      */       
/* 2961 */       throw je;
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
/*      */   public ProviderStreamMessage createStreamMessage() throws JMSException {
/* 2974 */     if (Trace.isOn) {
/* 2975 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createStreamMessage()");
/*      */     }
/*      */     
/* 2978 */     ProviderStreamMessage traceRet1 = new JMSStreamMessage(jmsStrings);
/* 2979 */     if (Trace.isOn) {
/* 2980 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createStreamMessage()", traceRet1);
/*      */     }
/*      */     
/* 2983 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQMessageConsumer createTConsumer(WMQDestination topic, String selector, boolean noLocal) throws JMSException {
/* 2991 */     if (Trace.isOn) {
/* 2992 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTConsumer(WMQDestination,String,boolean)", new Object[] { topic, selector, 
/*      */             
/* 2994 */             Boolean.valueOf(noLocal) });
/*      */     }
/*      */     
/* 2997 */     MQMessageConsumer subscriber = null;
/* 2998 */     MQSubscription subscription = null;
/*      */     
/* 3000 */     boolean shared_queue = true;
/*      */     try {
/* 3002 */       if (this.qm == null) {
/* 3003 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/* 3004 */         if (Trace.isOn) {
/* 3005 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTConsumer(WMQDestination,String,boolean)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 3008 */         throw je;
/*      */       } 
/*      */       
/* 3011 */       if (!topic.isTopic()) {
/* 3012 */         String key = "MQJMS0003";
/* 3013 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 3014 */         InvalidDestinationException je = new InvalidDestinationException(msg, key);
/*      */         
/* 3016 */         if (Trace.isOn) {
/* 3017 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTConsumer(WMQDestination,String,boolean)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 3020 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3025 */       addPubSubServices();
/*      */ 
/*      */       
/* 3028 */       WMQDestination mqtopic = topic;
/* 3029 */       if (mqtopic.containsAnyWildcard())
/*      */       {
/* 3031 */         if (mqtopic.getIntProperty("brokerVersion") != this.connection.getBrkVersion()) {
/* 3032 */           if (Trace.isOn) {
/* 3033 */             Trace.traceData(this, "Invalid wildcard.", null);
/*      */           }
/* 3035 */           String key = "MQJMS0003";
/* 3036 */           String msg = ConfigEnvironment.getErrorMessage(key);
/* 3037 */           InvalidDestinationException je = new InvalidDestinationException(msg, key);
/*      */           
/* 3039 */           if (Trace.isOn) {
/* 3040 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTConsumer(WMQDestination,String,boolean)", (Throwable)je, 3);
/*      */           }
/*      */           
/* 3043 */           throw je;
/*      */         } 
/*      */       }
/*      */       
/* 3047 */       if (topic.isTemporary()) {
/* 3048 */         WMQTemporaryTopic tt = (WMQTemporaryTopic)topic;
/*      */ 
/*      */         
/* 3051 */         if (tt.isDeleted()) {
/* 3052 */           String key = "MQJMS3019";
/* 3053 */           String msg = ConfigEnvironment.getErrorMessage(key);
/* 3054 */           InvalidDestinationException invalidDestinationException = new InvalidDestinationException(msg, key);
/* 3055 */           if (Trace.isOn) {
/* 3056 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTConsumer(WMQDestination,String,boolean)", (Throwable)invalidDestinationException, 4);
/*      */           }
/*      */           
/* 3059 */           throw invalidDestinationException;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 3064 */         if (!tt.isScopedBy(this.connection)) {
/* 3065 */           String key = "MQJMS3020";
/* 3066 */           String msg = ConfigEnvironment.getErrorMessage(key);
/* 3067 */           InvalidDestinationException invalidDestinationException = new InvalidDestinationException(msg, key);
/* 3068 */           if (Trace.isOn) {
/* 3069 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTConsumer(WMQDestination,String,boolean)", (Throwable)invalidDestinationException, 5);
/*      */           }
/*      */           
/* 3072 */           throw invalidDestinationException;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 3077 */       if (this.nondur_subscribeQ.trim().equals("")) {
/*      */ 
/*      */ 
/*      */         
/* 3081 */         this.nondur_subscribeQ = "SYSTEM.JMS.ND.*";
/*      */ 
/*      */       
/*      */       }
/* 3085 */       else if (!this.nondur_subscribeQ.startsWith("SYSTEM.JMS.ND.")) {
/* 3086 */         JMSException je = ConfigEnvironment.newException("MQJMS3021", this.nondur_subscribeQ);
/*      */ 
/*      */         
/* 3089 */         if (Trace.isOn) {
/* 3090 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTConsumer(WMQDestination,String,boolean)", (Throwable)je, 6);
/*      */         }
/*      */         
/* 3093 */         throw je;
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
/* 3105 */       if (this.nondur_subscribeQ.endsWith("*")) {
/*      */ 
/*      */         
/* 3108 */         shared_queue = false;
/* 3109 */         if (Trace.isOn) {
/* 3110 */           Trace.traceData(this, "Using multi-queue approach.", null);
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 3115 */         shared_queue = true;
/*      */       } 
/*      */       
/* 3118 */       if (Trace.isOn) {
/* 3119 */         Trace.traceData(this, "Prefix: SYSTEM.JMS.ND.", null);
/* 3120 */         Trace.traceData(this, "QName:  " + this.nondur_subscribeQ, null);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3126 */       subscription = this.connection.getSubscriptionEngine().openSubscription(this, mqtopic, selector, noLocal, shared_queue, this.nondur_subscribeQ);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 3134 */     catch (JMSException je) {
/* 3135 */       if (Trace.isOn) {
/* 3136 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTConsumer(WMQDestination,String,boolean)", (Throwable)je, 1);
/*      */       }
/*      */       
/* 3139 */       if (Trace.isOn) {
/* 3140 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTConsumer(WMQDestination,String,boolean)", (Throwable)je, 7);
/*      */       }
/*      */       
/* 3143 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3149 */       subscriber = new MQMessageConsumer(topic, this, subscription, this.transacted, this.acknowledgeMode);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 3154 */     catch (JMSException je) {
/* 3155 */       if (Trace.isOn) {
/* 3156 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTConsumer(WMQDestination,String,boolean)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 3159 */       if (subscription != null) {
/*      */         
/*      */         try {
/*      */           
/* 3163 */           subscription.close();
/*      */         }
/* 3165 */         catch (JMSException je2) {
/* 3166 */           if (Trace.isOn) {
/* 3167 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTConsumer(WMQDestination,String,boolean)", (Throwable)je2, 3);
/*      */           }
/*      */           
/* 3170 */           if (Trace.isOn) {
/* 3171 */             Trace.traceData(this, "Warning: could not close subcription - The subscription may remain active", null);
/*      */           }
/*      */         } 
/*      */       }
/*      */       
/* 3176 */       if (Trace.isOn) {
/* 3177 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTConsumer(WMQDestination,String,boolean)", (Throwable)je, 8);
/*      */       }
/*      */       
/* 3180 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/* 3184 */     if (Trace.isOn) {
/* 3185 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTConsumer(WMQDestination,String,boolean)", subscriber);
/*      */     }
/*      */     
/* 3188 */     return subscriber;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ProviderDestination createTemporaryDestination(int destType, JmsPropertyContext propertyContext) throws JMSException {
/*      */     WMQTemporaryTopic wMQTemporaryTopic;
/* 3198 */     if (Trace.isOn)
/* 3199 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTemporaryDestination(int,JmsPropertyContext)", new Object[] {
/*      */             
/* 3201 */             Integer.valueOf(destType), propertyContext
/*      */           }); 
/* 3203 */     WMQDestination mqTemporaryDestination = null;
/* 3204 */     if (destType == 1) {
/* 3205 */       WMQTemporaryQueue wMQTemporaryQueue = createTemporaryQueue(propertyContext);
/*      */     }
/* 3207 */     else if (destType == 2) {
/* 3208 */       wMQTemporaryTopic = createTemporaryTopic(propertyContext);
/*      */     } else {
/*      */       
/* 3211 */       String key = "MQJMS0003";
/* 3212 */       String msg = ConfigEnvironment.getErrorMessage(key);
/* 3213 */       InvalidDestinationException ide = new InvalidDestinationException(msg, key);
/* 3214 */       if (Trace.isOn) {
/* 3215 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTemporaryDestination(int,JmsPropertyContext)", (Throwable)ide);
/*      */       }
/*      */       
/* 3218 */       throw ide;
/*      */     } 
/* 3220 */     if (Trace.isOn) {
/* 3221 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTemporaryDestination(int,JmsPropertyContext)", wMQTemporaryTopic);
/*      */     }
/*      */     
/* 3224 */     return (ProviderDestination)wMQTemporaryTopic;
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
/*      */   private WMQTemporaryQueue createTemporaryQueue(JmsPropertyContext propertyContext) throws JMSException {
/* 3245 */     if (Trace.isOn) {
/* 3246 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTemporaryQueue(JmsPropertyContext)", new Object[] { propertyContext });
/*      */     }
/*      */     
/*      */     try {
/* 3250 */       if (this.connection == null) {
/* 3251 */         JMSException je = ConfigEnvironment.newException("MQJMS1018");
/* 3252 */         if (Trace.isOn) {
/* 3253 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTemporaryQueue(JmsPropertyContext)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 3256 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 3260 */       WMQTemporaryQueue traceRet1 = (WMQTemporaryQueue)this.connection.createTemporaryDestination(1, propertyContext);
/*      */       
/* 3262 */       if (Trace.isOn) {
/* 3263 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTemporaryQueue(JmsPropertyContext)", traceRet1);
/*      */       }
/*      */       
/* 3266 */       return traceRet1;
/*      */     }
/* 3268 */     catch (JMSException je) {
/* 3269 */       if (Trace.isOn) {
/* 3270 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTemporaryQueue(JmsPropertyContext)", (Throwable)je);
/*      */       }
/*      */       
/* 3273 */       if (Trace.isOn) {
/* 3274 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTemporaryQueue(JmsPropertyContext)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 3277 */       throw je;
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
/*      */   private WMQTemporaryTopic createTemporaryTopic(JmsPropertyContext propertyContext) throws JMSException {
/* 3293 */     if (Trace.isOn) {
/* 3294 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTemporaryTopic(JmsPropertyContext)", new Object[] { propertyContext });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3300 */       addPubSubServices();
/*      */     }
/* 3302 */     catch (JMSException je) {
/* 3303 */       if (Trace.isOn) {
/* 3304 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTemporaryTopic(JmsPropertyContext)", (Throwable)je);
/*      */       }
/*      */       
/* 3307 */       if (Trace.isOn) {
/* 3308 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTemporaryTopic(JmsPropertyContext)", (Throwable)je);
/*      */       }
/*      */       
/* 3311 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/* 3315 */     WMQTemporaryTopic traceRet1 = (WMQTemporaryTopic)this.connection.createTemporaryDestination(2, propertyContext);
/* 3316 */     if (Trace.isOn) {
/* 3317 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTemporaryTopic(JmsPropertyContext)", traceRet1);
/*      */     }
/*      */     
/* 3320 */     return traceRet1;
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
/*      */   public ProviderTextMessage createTextMessage() throws JMSException {
/* 3333 */     if (Trace.isOn) {
/* 3334 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTextMessage()");
/*      */     }
/*      */     
/* 3337 */     ProviderTextMessage traceRet1 = new JMSTextMessage(jmsStrings);
/* 3338 */     if (Trace.isOn) {
/* 3339 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTextMessage()", traceRet1);
/*      */     }
/*      */     
/* 3342 */     return traceRet1;
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
/*      */   public ProviderTextMessage createTextMessage(String string) throws JMSException {
/* 3355 */     if (Trace.isOn) {
/* 3356 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTextMessage(String)", new Object[] { string });
/*      */     }
/*      */     
/* 3359 */     ProviderTextMessage traceRet1 = new JMSTextMessage(jmsStrings, string);
/* 3360 */     if (Trace.isOn) {
/* 3361 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTextMessage(String)", traceRet1);
/*      */     }
/*      */     
/* 3364 */     return traceRet1;
/*      */   }
/*      */   
/*      */   private ProviderMessageProducer createTProducer(WMQDestination topic) throws JMSException {
/*      */     MQMessageProducer publisher;
/* 3369 */     if (Trace.isOn) {
/* 3370 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTProducer(WMQDestination)", new Object[] { topic });
/*      */     }
/*      */     
/* 3373 */     int openOptions = 16;
/*      */     
/* 3375 */     MQQueue mqBrkPubQ = null;
/* 3376 */     String mqBrkPubQName = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 3382 */       addPubSubServices();
/*      */ 
/*      */ 
/*      */       
/* 3386 */       mqBrkPubQName = this.connection.getBrkPubQueue();
/* 3387 */       if (topic != null && topic.isTopic()) {
/* 3388 */         String mqTempBrokerPubQ = topic.getStringProperty("XMSC_WMQ_BROKER_PUBQ");
/* 3389 */         if (mqTempBrokerPubQ != null && !mqTempBrokerPubQ.equals("")) {
/* 3390 */           mqBrkPubQName = mqTempBrokerPubQ;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 3395 */       if (topic != null && topic.isTopic() && 
/* 3396 */         topic.getFailIfQuiesce() == 1) {
/* 3397 */         openOptions |= 0x2000;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3404 */       if (this.qm == null) {
/* 3405 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/* 3406 */         if (Trace.isOn) {
/* 3407 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTProducer(WMQDestination)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 3410 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 3414 */       if (topic.isTemporary()) {
/* 3415 */         WMQTemporaryTopic tt = (WMQTemporaryTopic)topic;
/*      */ 
/*      */         
/* 3418 */         if (tt.isDeleted()) {
/*      */ 
/*      */           
/* 3421 */           JMSException e = new JMSException("TemporaryTopic already deleted");
/* 3422 */           if (Trace.isOn) {
/* 3423 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTProducer(WMQDestination)", (Throwable)e, 2);
/*      */           }
/*      */           
/* 3426 */           throw e;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3434 */       String brokerQMgrName = this.brk.qmName;
/* 3435 */       if (topic.isTopic()) {
/* 3436 */         String brokerTempQMgrName = topic.getStringProperty("XMSC_WMQ_BROKER_PUBQ_QMGR");
/* 3437 */         if (brokerTempQMgrName != null && !brokerTempQMgrName.equals("")) {
/* 3438 */           brokerQMgrName = brokerTempQMgrName;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 3444 */         mqBrkPubQ = this.qm.accessQueue(mqBrkPubQName, openOptions, brokerQMgrName, null, null);
/*      */       
/*      */       }
/* 3447 */       catch (MQException e) {
/* 3448 */         if (Trace.isOn) {
/* 3449 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTProducer(WMQDestination)", (Throwable)e, 1);
/*      */         }
/*      */         
/* 3452 */         if (Trace.isOn) {
/* 3453 */           Trace.traceData(this, "createTProducer failed to access publish queue because of " + e, null);
/*      */         }
/* 3455 */         JMSException je = ConfigEnvironment.newException("MQJMS2008", mqBrkPubQName);
/* 3456 */         je.setLinkedException((Exception)e);
/* 3457 */         if (Trace.isOn) {
/* 3458 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTProducer(WMQDestination)", (Throwable)je, 3);
/*      */         }
/*      */         
/* 3461 */         throw je;
/*      */       } 
/*      */       
/* 3464 */       publisher = new MQMessageProducer(topic, mqBrkPubQ, this.qm, this.connection, this);
/*      */ 
/*      */     
/*      */     }
/* 3468 */     catch (JMSException je) {
/* 3469 */       if (Trace.isOn) {
/* 3470 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTProducer(WMQDestination)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 3473 */       if (Trace.isOn) {
/* 3474 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTProducer(WMQDestination)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 3477 */       throw je;
/*      */     } 
/*      */     
/* 3480 */     if (Trace.isOn) {
/* 3481 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createTProducer(WMQDestination)", publisher);
/*      */     }
/*      */     
/* 3484 */     return publisher;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void deleteDurableSubscriber(String subscriptionName) throws JMSException {
/* 3493 */     if (Trace.isOn) {
/* 3494 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "deleteDurableSubscriber(String)", new Object[] { subscriptionName });
/*      */     }
/*      */     
/* 3497 */     unsubscribe(subscriptionName);
/* 3498 */     if (Trace.isOn) {
/* 3499 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "deleteDurableSubscriber(String)");
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
/*      */   public void deleteTemporaryDestination(ProviderDestination dest) throws JMSException {
/* 3511 */     if (Trace.isOn) {
/* 3512 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "deleteTemporaryDestination(ProviderDestination)", new Object[] { dest });
/*      */     }
/*      */     
/* 3515 */     if (dest.isTemporary()) {
/* 3516 */       ((WMQDestination)dest).delete();
/*      */     }
/* 3518 */     if (Trace.isOn) {
/* 3519 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "deleteTemporaryDestination(ProviderDestination)");
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
/*      */   void discQM() {
/* 3534 */     if (Trace.isOn) {
/* 3535 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "discQM()");
/*      */     }
/*      */     
/* 3538 */     if (this.qm != null) {
/*      */       try {
/* 3540 */         this.qm.disconnect();
/* 3541 */         this.qm = null;
/*      */       }
/* 3543 */       catch (MQException e) {
/* 3544 */         if (Trace.isOn) {
/* 3545 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "discQM()", (Throwable)e);
/*      */         }
/*      */         
/* 3548 */         if (Trace.isOn) {
/* 3549 */           Trace.traceData(this, "qm.disconnect failed.", null);
/*      */         }
/*      */       }
/*      */     
/*      */     }
/* 3554 */     else if (Trace.isOn) {
/* 3555 */       Trace.traceData(this, "qm already null", null);
/*      */     } 
/*      */ 
/*      */     
/* 3559 */     if (Trace.isOn) {
/* 3560 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "discQM()");
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
/*      */   protected final int getAcknowledgeMode() throws JMSException {
/* 3577 */     if (Trace.isOn) {
/* 3578 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getAcknowledgeMode()", "getter", 
/*      */           
/* 3580 */           Integer.valueOf(this.transacted ? 0 : this.acknowledgeMode));
/*      */     }
/* 3582 */     return this.transacted ? 0 : this.acknowledgeMode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected BrokerConnectionInfo getBrk() throws JMSException {
/* 3593 */     if (Trace.isOn) {
/* 3594 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getBrk()", "getter", this.brk);
/*      */     }
/*      */     
/* 3597 */     return this.brk;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected final boolean getCommitRequired() {
/* 3603 */     synchronized (this.commitLock) {
/* 3604 */       if (Trace.isOn) {
/* 3605 */         Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getCommitRequired()", "getter", 
/* 3606 */             Boolean.valueOf(this.commitRequired));
/*      */       }
/* 3608 */       return this.commitRequired;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getConnectionBrokerVersion() throws JMSException {
/* 3619 */     if (Trace.isOn) {
/* 3620 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getConnectionBrokerVersion()");
/*      */     }
/*      */     
/*      */     try {
/* 3624 */       int traceRet1 = this.connection.getBrkVersion();
/* 3625 */       if (Trace.isOn) {
/* 3626 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getConnectionBrokerVersion()", 
/* 3627 */             Integer.valueOf(traceRet1));
/*      */       }
/* 3629 */       return traceRet1;
/*      */     }
/* 3631 */     catch (JMSException je) {
/* 3632 */       if (Trace.isOn) {
/* 3633 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getConnectionBrokerVersion()", (Throwable)je);
/*      */       }
/*      */       
/* 3636 */       RuntimeException traceRet2 = new RuntimeException("MQJMS1016");
/* 3637 */       if (Trace.isOn) {
/* 3638 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getConnectionBrokerVersion()", traceRet2);
/*      */       }
/*      */       
/* 3641 */       throw traceRet2;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getConnectionID() throws JMSException {
/* 3652 */     if (Trace.isOn) {
/* 3653 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getConnectionID()");
/*      */     }
/*      */     
/*      */     try {
/* 3657 */       String traceRet1 = this.connection.getConnectionID();
/* 3658 */       if (Trace.isOn) {
/* 3659 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getConnectionID()", traceRet1);
/*      */       }
/*      */       
/* 3662 */       return traceRet1;
/*      */     }
/* 3664 */     catch (JMSException je) {
/* 3665 */       if (Trace.isOn) {
/* 3666 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getConnectionID()", (Throwable)je);
/*      */       }
/*      */       
/* 3669 */       RuntimeException traceRet2 = new RuntimeException("MQJMS1016");
/* 3670 */       if (Trace.isOn) {
/* 3671 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getConnectionID()", traceRet2);
/*      */       }
/*      */       
/* 3674 */       throw traceRet2;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getConnectionMsgSelection() throws JMSException {
/* 3685 */     if (Trace.isOn) {
/* 3686 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getConnectionMsgSelection()");
/*      */     }
/*      */     
/*      */     try {
/* 3690 */       int traceRet1 = this.connection.getMsgSelection();
/* 3691 */       if (Trace.isOn) {
/* 3692 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getConnectionMsgSelection()", 
/* 3693 */             Integer.valueOf(traceRet1));
/*      */       }
/* 3695 */       return traceRet1;
/*      */     }
/* 3697 */     catch (JMSException je) {
/* 3698 */       if (Trace.isOn) {
/* 3699 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getConnectionMsgSelection()", (Throwable)je);
/*      */       }
/*      */       
/* 3702 */       RuntimeException traceRet2 = new RuntimeException("MQJMS1016");
/* 3703 */       if (Trace.isOn) {
/* 3704 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getConnectionMsgSelection()", traceRet2);
/*      */       }
/*      */       
/* 3707 */       throw traceRet2;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int getDistTransactionMode() {
/* 3715 */     if (Trace.isOn) {
/* 3716 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getDistTransactionMode()", "getter", 
/* 3717 */           Integer.valueOf(this.distTransactionMode));
/*      */     }
/* 3719 */     return this.distTransactionMode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final String getDLQName() throws JMSException {
/* 3729 */     if (Trace.isOn) {
/* 3730 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getDLQName()", "getter", this.deadLetterQueue);
/*      */     }
/*      */     
/* 3733 */     return this.deadLetterQueue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final int getFailIfQuiesce() {
/* 3744 */     int traceRet1 = this.connection.getFailIfQuiesce();
/* 3745 */     if (Trace.isOn) {
/* 3746 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getFailIfQuiesce()", "getter", 
/* 3747 */           Integer.valueOf(traceRet1));
/*      */     }
/* 3749 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean getOptimisticPublication() throws JMSException {
/* 3760 */     if (Trace.isOn) {
/* 3761 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getOptimisticPublication()", "getter", 
/* 3762 */           Boolean.valueOf(this.optimisticPublication));
/*      */     }
/* 3764 */     return this.optimisticPublication;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean getOutcomeNotification() throws JMSException {
/* 3775 */     if (Trace.isOn) {
/* 3776 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getOutcomeNotification()", "getter", 
/* 3777 */           Boolean.valueOf(this.outcomeNotification));
/*      */     }
/* 3779 */     return this.outcomeNotification;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final boolean getPersistenceFromMD() {
/* 3790 */     if (Trace.isOn) {
/* 3791 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getPersistenceFromMD()", "getter", 
/* 3792 */           Boolean.valueOf(this.persistenceFromMD));
/*      */     }
/* 3794 */     return this.persistenceFromMD;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final boolean getPlayNice() {
/* 3801 */     if (Trace.isOn) {
/* 3802 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getPlayNice()", "getter", 
/* 3803 */           Boolean.valueOf(this.playNice));
/*      */     }
/* 3805 */     return this.playNice;
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
/*      */   protected int getProcessDuration() throws JMSException {
/* 3820 */     if (Trace.isOn) {
/* 3821 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getProcessDuration()", "getter", 
/* 3822 */           Integer.valueOf(this.processDuration));
/*      */     }
/* 3824 */     return this.processDuration;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final MQQueueManager getQM() {
/* 3833 */     if (Trace.isOn) {
/* 3834 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getQM()", "getter", this.qm);
/*      */     }
/*      */     
/* 3837 */     return this.qm;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final String getQMName() {
/* 3845 */     String traceRet1 = (this.qm != null) ? this.qm.name : null;
/* 3846 */     if (Trace.isOn) {
/* 3847 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getQMName()", "getter", traceRet1);
/*      */     }
/*      */     
/* 3850 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void setQM(MQQueueManager theQM) {
/* 3858 */     if (Trace.isOn) {
/* 3859 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "setQM(MQQueueManager)", "setter", theQM);
/*      */     }
/*      */ 
/*      */     
/* 3863 */     this.qm = theQM;
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
/*      */   protected int getReceiveIsolation() throws JMSException {
/* 3880 */     if (Trace.isOn) {
/* 3881 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getReceiveIsolation()", "getter", 
/* 3882 */           Integer.valueOf(this.receiveIsolation));
/*      */     }
/* 3884 */     return this.receiveIsolation;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQQueue getResponseQueue() throws JMSException {
/* 3894 */     MQQueue traceRet1 = getServicesMgr().getResponseQueue(this);
/* 3895 */     if (Trace.isOn) {
/* 3896 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getResponseQueue()", "getter", traceRet1);
/*      */     }
/*      */     
/* 3899 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JMSServicesMgr getServicesMgr() {
/* 3906 */     if (Trace.isOn) {
/* 3907 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getServicesMgr()");
/*      */     }
/*      */ 
/*      */     
/* 3911 */     if (this.connection == null) {
/* 3912 */       if (Trace.isOn) {
/* 3913 */         Trace.traceData(this, "Null ProviderConnection Value", null);
/*      */       }
/* 3915 */       if (Trace.isOn) {
/* 3916 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getServicesMgr()", null, 1);
/*      */       }
/*      */       
/* 3919 */       return null;
/*      */     } 
/* 3921 */     JMSServicesMgr traceRet1 = this.connection.getServicesMgr();
/* 3922 */     if (Trace.isOn) {
/* 3923 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getServicesMgr()", traceRet1, 2);
/*      */     }
/*      */     
/* 3926 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected byte[] getSessionName() throws JMSException {
/* 3936 */     if (Trace.isOn) {
/* 3937 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getSessionName()", "getter", this.sessionName);
/*      */     }
/*      */     
/* 3940 */     return this.sessionName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean getSparseSubscriptions() throws JMSException {
/* 3950 */     if (Trace.isOn) {
/* 3951 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getSparseSubscriptions()");
/*      */     }
/*      */     
/* 3954 */     if (this.connection != null) {
/* 3955 */       boolean traceRet1 = this.connection.getSparseSubscriptions();
/* 3956 */       if (Trace.isOn) {
/* 3957 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getSparseSubscriptions()", 
/* 3958 */             Boolean.valueOf(traceRet1), 1);
/*      */       }
/* 3960 */       return traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 3964 */     if (Trace.isOn) {
/* 3965 */       Trace.traceData(this, "getSparseSubscriptions, connection null.", null);
/*      */     }
/* 3967 */     if (Trace.isOn) {
/* 3968 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getSparseSubscriptions()", 
/* 3969 */           Boolean.valueOf(false), 2);
/*      */     }
/* 3971 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean getSyncpointAllGets() {
/* 3981 */     boolean traceRet1 = this.connection.getSyncpointAllGets();
/* 3982 */     if (Trace.isOn) {
/* 3983 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getSyncpointAllGets()", "getter", 
/* 3984 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 3986 */     return traceRet1;
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
/*      */   protected boolean getTransacted() throws JMSException {
/* 4014 */     if (Trace.isOn) {
/* 4015 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getTransacted()");
/*      */     }
/*      */     try {
/*      */       boolean traceRet1;
/* 4019 */       switch (this.distTransactionMode) {
/*      */         case 0:
/*      */         case 1:
/* 4022 */           if (Trace.isOn) {
/* 4023 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getTransacted()", 
/* 4024 */                 Boolean.valueOf(this.transacted), 1);
/*      */           }
/* 4026 */           return this.transacted;
/*      */         
/*      */         case 2:
/* 4029 */           traceRet1 = (this.transacted || Utils.isRRSTransactionInProgress());
/* 4030 */           if (Trace.isOn) {
/* 4031 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getTransacted()", 
/* 4032 */                 Boolean.valueOf(traceRet1), 2);
/*      */           }
/* 4034 */           return traceRet1;
/*      */       } 
/*      */ 
/*      */       
/* 4038 */       if (Trace.isOn) {
/* 4039 */         Trace.traceData(this, "Bad distributed transactional mode " + this.distTransactionMode, null);
/*      */       }
/* 4041 */       JMSException traceRet2 = ConfigEnvironment.newException("MQJMS1016");
/* 4042 */       if (Trace.isOn) {
/* 4043 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getTransacted()", (Throwable)traceRet2, 1);
/*      */       }
/*      */       
/* 4046 */       throw traceRet2;
/*      */     
/*      */     }
/* 4049 */     catch (JMSException je) {
/* 4050 */       if (Trace.isOn) {
/* 4051 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getTransacted()", (Throwable)je);
/*      */       }
/*      */       
/* 4054 */       if (Trace.isOn) {
/* 4055 */         Trace.traceData(this, "throwing " + je, null);
/* 4056 */         Exception le = je.getLinkedException();
/* 4057 */         if (le != null) {
/* 4058 */           Trace.traceData(this, "linked excpetion: " + le, null);
/*      */         }
/*      */       } 
/* 4061 */       if (Trace.isOn) {
/* 4062 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getTransacted()", (Throwable)je, 2);
/*      */       }
/*      */       
/* 4065 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final Object getTransactionLock() {
/* 4075 */     if (Trace.isOn) {
/* 4076 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getTransactionLock()", "getter", this.transactionLock);
/*      */     }
/*      */     
/* 4079 */     return this.transactionLock;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final Object getBrokerResponseLock() {
/* 4089 */     if (Trace.isOn) {
/* 4090 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getBrokerResponseLock", "getter", this.brokerResponseLock);
/*      */     }
/* 4092 */     if (Trace.isOn) {
/* 4093 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getBrokerResponseLock()", "getter", this.brokerResponseLock);
/*      */     }
/*      */     
/* 4096 */     return this.brokerResponseLock;
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
/*      */   protected final boolean hasMessageListener() {
/* 4108 */     if (Trace.isOn) {
/* 4109 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "hasMessageListener()");
/*      */     }
/*      */     
/* 4112 */     boolean traceRet1 = (this.messageListener != null);
/* 4113 */     if (Trace.isOn) {
/* 4114 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "hasMessageListener()", 
/* 4115 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 4117 */     return traceRet1;
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
/*      */   protected void inhibitITX() throws JMSException {
/* 4134 */     if (Trace.isOn) {
/* 4135 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "inhibitITX()");
/*      */     }
/* 4137 */     this.processDuration = 0;
/*      */     
/* 4139 */     switch (getAcknowledgeMode()) {
/*      */       case 0:
/* 4141 */         rollback();
/*      */         break;
/*      */       
/*      */       case 2:
/* 4145 */         recover();
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
/*      */     
/* 4157 */     String key = "MQJMS0011";
/* 4158 */     String msg = ConfigEnvironment.getErrorMessage(key);
/* 4159 */     TransactionRolledBackException transactionRolledBackException = new TransactionRolledBackException(msg, key);
/*      */     
/* 4161 */     if (Trace.isOn) {
/* 4162 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "inhibitITX()", (Throwable)transactionRolledBackException);
/*      */     }
/*      */     
/* 4165 */     throw transactionRolledBackException;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isStarted() {
/* 4175 */     boolean ret = (this.state == 1);
/* 4176 */     if (Trace.isOn) {
/* 4177 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "isStarted()", "getter", 
/* 4178 */           Boolean.valueOf(ret));
/*      */     }
/* 4180 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isStopped() {
/* 4190 */     boolean ret = (this.state == 0);
/* 4191 */     if (Trace.isOn) {
/* 4192 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "isStopped()", "getter", 
/* 4193 */           Boolean.valueOf(ret));
/*      */     }
/* 4195 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void enableMessageReferenceProcessing() {
/* 4205 */     if (Trace.isOn) {
/* 4206 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "enableMessageReferenceProcessing()", "Message reference processing is always enabled when using MQ messaging provider migration mode, so there is nothing to do here");
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
/*      */   public void loadMessageReference(ProviderMessageReference messageReference) throws JMSException {
/* 4218 */     if (Trace.isOn) {
/* 4219 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "loadMessageReference(ProviderMessageReference)", new Object[] { messageReference });
/*      */     }
/*      */ 
/*      */     
/* 4223 */     this.calledByAsf = true;
/* 4224 */     ProviderMessage msg = consume(messageReference);
/* 4225 */     assert messageReference instanceof MQMessageReference;
/* 4226 */     MQMessageReference mqMsgRef = (MQMessageReference)messageReference;
/* 4227 */     mqMsgRef.setJMSMessage(msg, 2);
/*      */     
/* 4229 */     if (Trace.isOn) {
/* 4230 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "loadMessageReference(ProviderMessageReference)");
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
/*      */   
/*      */   private void recover() throws JMSException {
/* 4261 */     if (Trace.isOn) {
/* 4262 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recover()");
/*      */     }
/* 4264 */     if ((getServicesMgr()).queueSet) {
/* 4265 */       recoverQ();
/*      */     }
/* 4267 */     if ((getServicesMgr()).pubSubSet) {
/* 4268 */       recoverT();
/*      */     }
/*      */     else {
/*      */       
/* 4272 */       if (this.transacted) {
/*      */         
/* 4274 */         String key = "MQJMS1020";
/* 4275 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 4276 */         IllegalStateException illegalStateException = new IllegalStateException(msg, key);
/* 4277 */         if (Trace.isOn) {
/* 4278 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recover()", (Throwable)illegalStateException, 1);
/*      */         }
/*      */         
/* 4281 */         throw illegalStateException;
/*      */       } 
/*      */       
/* 4284 */       if (this.qm == null) {
/* 4285 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/* 4286 */         if (Trace.isOn) {
/* 4287 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recover()", (Throwable)je, 2);
/*      */         }
/*      */         
/* 4290 */         throw je;
/*      */       } 
/*      */     } 
/* 4293 */     if (Trace.isOn) {
/* 4294 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recover()");
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
/*      */   private void recoverQ() throws JMSException {
/* 4314 */     if (Trace.isOn) {
/* 4315 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverQ()");
/*      */     }
/*      */     try {
/* 4318 */       if (this.transacted) {
/*      */         
/* 4320 */         String key = "MQJMS1020";
/* 4321 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 4322 */         IllegalStateException illegalStateException = new IllegalStateException(msg, key);
/* 4323 */         if (Trace.isOn) {
/* 4324 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverQ()", (Throwable)illegalStateException, 1);
/*      */         }
/*      */         
/* 4327 */         throw illegalStateException;
/*      */       } 
/*      */       
/* 4330 */       if (this.qm == null) {
/* 4331 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/* 4332 */         if (Trace.isOn) {
/* 4333 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverQ()", (Throwable)je, 2);
/*      */         }
/*      */         
/* 4336 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4345 */         setPlayNice(true);
/*      */ 
/*      */         
/* 4348 */         synchronized (getTransactionLock())
/*      */         {
/*      */           
/* 4351 */           setPlayNice(false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 4357 */           if (this.qm.isConnected()) {
/*      */             try {
/* 4359 */               this.qm.backout();
/*      */             }
/* 4361 */             catch (NullPointerException e) {
/* 4362 */               if (Trace.isOn) {
/* 4363 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverQ()", e, 1);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/* 4368 */               JMSException je = ConfigEnvironment.newException("MQJMS1021");
/* 4369 */               je.setLinkedException(e);
/* 4370 */               if (Trace.isOn) {
/* 4371 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverQ()", (Throwable)je, 3);
/*      */               }
/*      */               
/* 4374 */               throw je;
/*      */             
/*      */             }
/*      */           
/*      */           }
/* 4379 */           else if (Trace.isOn) {
/* 4380 */             Trace.traceData(this, "qm not connected - race condition?", null);
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 4387 */       catch (MQException e) {
/* 4388 */         if (Trace.isOn) {
/* 4389 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverQ()", (Throwable)e, 2);
/*      */         }
/*      */         
/* 4392 */         JMSException je = ConfigEnvironment.newException("MQJMS1021");
/* 4393 */         je.setLinkedException((Exception)e);
/* 4394 */         if (Trace.isOn) {
/* 4395 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverQ()", (Throwable)je, 4);
/*      */         }
/*      */         
/* 4398 */         throw je;
/*      */       }
/*      */     
/* 4401 */     } catch (JMSException je) {
/* 4402 */       if (Trace.isOn) {
/* 4403 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverQ()", (Throwable)je, 3);
/*      */       }
/*      */       
/* 4406 */       if (Trace.isOn) {
/* 4407 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverQ()", (Throwable)je, 5);
/*      */       }
/*      */       
/* 4410 */       throw je;
/*      */     } 
/* 4412 */     if (Trace.isOn) {
/* 4413 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverQ()");
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
/*      */   private void recoverT() throws JMSException {
/* 4433 */     if (Trace.isOn) {
/* 4434 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverT()");
/*      */     }
/*      */     
/*      */     try {
/* 4438 */       if (this.transacted) {
/* 4439 */         String key = "MQJMS1024";
/* 4440 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 4441 */         IllegalStateException illegalStateException = new IllegalStateException(msg, key);
/* 4442 */         if (Trace.isOn) {
/* 4443 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverT()", (Throwable)illegalStateException, 1);
/*      */         }
/*      */         
/* 4446 */         throw illegalStateException;
/*      */       } 
/*      */       
/* 4449 */       if (this.qm == null) {
/* 4450 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/* 4451 */         if (Trace.isOn) {
/* 4452 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverT()", (Throwable)je, 2);
/*      */         }
/*      */         
/* 4455 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4464 */         setPlayNice(true);
/*      */ 
/*      */         
/* 4467 */         synchronized (getTransactionLock())
/*      */         {
/*      */           
/* 4470 */           setPlayNice(false);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 4475 */           if (this.qm.isConnected()) {
/*      */             try {
/* 4477 */               this.qm.backout();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 4483 */               setCommitRequired(false);
/*      */             }
/* 4485 */             catch (NullPointerException e) {
/* 4486 */               if (Trace.isOn) {
/* 4487 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverT()", e, 1);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/* 4492 */               JMSException je = ConfigEnvironment.newException("MQJMS1021");
/* 4493 */               je.setLinkedException(e);
/* 4494 */               if (Trace.isOn) {
/* 4495 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverT()", (Throwable)je, 3);
/*      */               }
/*      */               
/* 4498 */               throw je;
/*      */             
/*      */             }
/*      */ 
/*      */           
/*      */           }
/* 4504 */           else if (Trace.isOn) {
/* 4505 */             Trace.traceData(this, "qm not connected - race condition?", null);
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 4512 */       catch (MQException e) {
/* 4513 */         if (Trace.isOn) {
/* 4514 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverT()", (Throwable)e, 2);
/*      */         }
/*      */         
/* 4517 */         JMSException je = ConfigEnvironment.newException("MQJMS1021");
/* 4518 */         je.setLinkedException((Exception)e);
/* 4519 */         if (Trace.isOn) {
/* 4520 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverT()", (Throwable)je, 4);
/*      */         }
/*      */         
/* 4523 */         throw je;
/*      */       }
/*      */     
/* 4526 */     } catch (JMSException je) {
/* 4527 */       if (Trace.isOn) {
/* 4528 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverT()", (Throwable)je, 3);
/*      */       }
/*      */       
/* 4531 */       if (Trace.isOn) {
/* 4532 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverT()", (Throwable)je, 5);
/*      */       }
/*      */       
/* 4535 */       throw je;
/*      */     } 
/* 4537 */     if (Trace.isOn) {
/* 4538 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recoverT()");
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
/*      */   public ProviderMessageReference recreateMessageReference(byte[] flatMR, ProviderDestination dest) throws JMSException {
/* 4559 */     if (Trace.isOn) {
/* 4560 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recreateMessageReference(byte [ ],ProviderDestination)", new Object[] { flatMR, dest });
/*      */     }
/*      */     
/*      */     try {
/* 4564 */       ProviderMessageReference traceRet1 = new MQMessageReference(this, flatMR, dest);
/* 4565 */       if (Trace.isOn) {
/* 4566 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recreateMessageReference(byte [ ],ProviderDestination)", traceRet1);
/*      */       }
/*      */       
/* 4569 */       return traceRet1;
/*      */     }
/* 4571 */     catch (JMSException je) {
/* 4572 */       if (Trace.isOn) {
/* 4573 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recreateMessageReference(byte [ ],ProviderDestination)", (Throwable)je);
/*      */       }
/*      */       
/* 4576 */       if (Trace.isOn) {
/* 4577 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "recreateMessageReference(byte [ ],ProviderDestination)", (Throwable)je);
/*      */       }
/*      */       
/* 4580 */       throw je;
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
/*      */   public String getDestinationURI(byte[] flatMR) throws JMSException {
/* 4593 */     if (Trace.isOn) {
/* 4594 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getDestinationURI(byte [ ])", new Object[] { flatMR });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4599 */     if (Trace.isOn) {
/* 4600 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "getDestinationURI(byte [ ])", null);
/*      */     }
/*      */     
/* 4603 */     return null;
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
/*      */   protected void redirectMessage(String qName, MQMsg2 message, boolean syncPoint) throws JMSException {
/* 4621 */     if (Trace.isOn) {
/* 4622 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean)", new Object[] { qName, message, 
/*      */             
/* 4624 */             Boolean.valueOf(syncPoint) });
/*      */     }
/* 4626 */     MQQueue queue = null;
/*      */     
/*      */     try {
/* 4629 */       if (this.qm == null) {
/* 4630 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/* 4631 */         if (Trace.isOn) {
/* 4632 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */         
/* 4636 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 4641 */         MQPutMessageOptions pmo = new MQPutMessageOptions();
/*      */ 
/*      */         
/* 4644 */         if (syncPoint) {
/* 4645 */           pmo.options |= 0x2;
/* 4646 */           pmo.options &= 0xFFFFFFFB;
/*      */         } else {
/*      */           
/* 4649 */           pmo.options |= 0x4;
/* 4650 */           pmo.options &= 0xFFFFFFFD;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4656 */         this.qm.putMsg2(qName, "", message, pmo);
/*      */       }
/* 4658 */       catch (MQException e) {
/* 4659 */         if (Trace.isOn) {
/* 4660 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean)", (Throwable)e, 1);
/*      */         }
/*      */ 
/*      */         
/* 4664 */         JMSException je = ConfigEnvironment.newException("MQJMS1022");
/* 4665 */         je.setLinkedException((Exception)e);
/* 4666 */         if (Trace.isOn) {
/* 4667 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */         
/* 4671 */         throw je;
/*      */       }
/*      */       finally {
/*      */         
/* 4675 */         if (Trace.isOn) {
/* 4676 */           Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean)");
/*      */         }
/*      */         
/* 4679 */         if (Trace.isOn) {
/* 4680 */           Trace.traceData(this, "redirectMessage finally block", null);
/*      */         }
/* 4682 */         if (queue != null) {
/*      */           try {
/* 4684 */             queue.close();
/*      */           }
/* 4686 */           catch (MQException e) {
/* 4687 */             if (Trace.isOn) {
/* 4688 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean)", (Throwable)e, 2);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 4693 */             HashMap<String, Serializable> ffstData = new HashMap<>();
/* 4694 */             ffstData.put("Exception", e);
/* 4695 */             ffstData.put("Message", "MQJMS2000");
/* 4696 */             Trace.ffst(this, "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean)", "05", ffstData, JMSException.class);
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 4706 */     catch (JMSException je) {
/* 4707 */       if (Trace.isOn) {
/* 4708 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 4713 */       if (Trace.isOn) {
/* 4714 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */       
/* 4718 */       throw je;
/*      */     } 
/*      */     
/* 4721 */     if (Trace.isOn) {
/* 4722 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean)");
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
/*      */   protected void removeAsync(MQMessageConsumer consumer) {
/* 4737 */     if (Trace.isOn) {
/* 4738 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "removeAsync(MQMessageConsumer)", new Object[] { consumer });
/*      */     }
/*      */     
/* 4741 */     if (this.asyncHelper != null)
/*      */     {
/* 4743 */       this.asyncHelper.removeReceiver(consumer);
/*      */     }
/*      */     
/* 4746 */     if (Trace.isOn) {
/* 4747 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "removeAsync(MQMessageConsumer)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeConsumer(ProviderMessageConsumer consumer) {
/* 4758 */     if (Trace.isOn) {
/* 4759 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "removeConsumer(ProviderMessageConsumer)", new Object[] { consumer });
/*      */     }
/*      */     
/* 4762 */     this.messageConsumers.removeElement(consumer);
/* 4763 */     if (Trace.isOn) {
/* 4764 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "removeConsumer(ProviderMessageConsumer)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeProducer(ProviderMessageProducer producer) {
/* 4775 */     if (Trace.isOn) {
/* 4776 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "removeProducer(ProviderMessageProducer)", new Object[] { producer });
/*      */     }
/*      */     
/* 4779 */     this.messageProducers.removeElement(producer);
/* 4780 */     if (Trace.isOn) {
/* 4781 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "removeProducer(ProviderMessageProducer)");
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
/*      */   protected void removePublisher(MQMessageProducer pub) throws JMSException {
/* 4795 */     if (Trace.isOn) {
/* 4796 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "removePublisher(MQMessageProducer)", new Object[] { pub });
/*      */     }
/*      */     
/* 4799 */     removeProducer(pub);
/* 4800 */     if (Trace.isOn) {
/* 4801 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "removePublisher(MQMessageProducer)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeSender(MQMessageProducer sender) throws JMSException {
/* 4812 */     if (Trace.isOn) {
/* 4813 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "removeSender(MQMessageProducer)", new Object[] { sender });
/*      */     }
/*      */     
/* 4816 */     removeProducer(sender);
/* 4817 */     if (Trace.isOn) {
/* 4818 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "removeSender(MQMessageProducer)");
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
/*      */   protected void responseRequested(long putTime, byte[] correlId) throws JMSException {
/* 4831 */     if (Trace.isOn) {
/* 4832 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "responseRequested(long,byte [ ])", new Object[] {
/* 4833 */             Long.valueOf(putTime), correlId
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4839 */     if (!this.transacted) {
/* 4840 */       this.responseRequested = true;
/* 4841 */       this.responsePutTime = putTime;
/*      */     } else {
/*      */       
/* 4844 */       this.responseOnCommit = true;
/*      */     } 
/*      */ 
/*      */     
/* 4848 */     System.arraycopy(correlId, 0, this.responseCorrelId, 0, correlId.length);
/* 4849 */     for (int i = correlId.length; i < this.responseCorrelId.length; i++) {
/* 4850 */       this.responseCorrelId[i] = 0;
/*      */     }
/*      */     
/* 4853 */     if (Trace.isOn) {
/* 4854 */       Trace.traceData(this, "setting responseCorrelId to ", this.responseCorrelId);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4859 */     this.publishCount = 0;
/*      */     
/* 4861 */     if (Trace.isOn) {
/* 4862 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "responseRequested(long,byte [ ])");
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
/*      */   public void rollback() throws JMSException {
/* 4885 */     if (Trace.isOn) {
/* 4886 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollback()");
/*      */     }
/*      */ 
/*      */     
/* 4890 */     if (!this.transacted && !this.calledByAsf) {
/* 4891 */       recover();
/* 4892 */       if (Trace.isOn) {
/* 4893 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollback()", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 4898 */     if (this.calledByAsf) {
/*      */       
/*      */       try {
/* 4901 */         this.qm.backout();
/*      */       }
/* 4903 */       catch (MQException mqe) {
/* 4904 */         if (Trace.isOn) {
/* 4905 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollback()", (Throwable)mqe);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 4910 */       if (Trace.isOn) {
/* 4911 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollback()", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 4916 */     if ((getServicesMgr()).queueSet) {
/* 4917 */       rollbackQ();
/*      */     }
/* 4919 */     if ((getServicesMgr()).pubSubSet) {
/* 4920 */       rollbackT();
/*      */     } else {
/*      */       
/* 4923 */       if (!this.transacted) {
/*      */ 
/*      */         
/* 4926 */         String key = "MQJMS1019";
/* 4927 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 4928 */         IllegalStateException je = new IllegalStateException(msg, key);
/*      */         
/* 4930 */         if (Trace.isOn) {
/* 4931 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollback()", (Throwable)je, 1);
/*      */         }
/*      */         
/* 4934 */         throw je;
/*      */       } 
/*      */       
/* 4937 */       if (this.qm == null) {
/* 4938 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/* 4939 */         if (Trace.isOn) {
/* 4940 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollback()", (Throwable)je, 2);
/*      */         }
/*      */         
/* 4943 */         throw je;
/*      */       } 
/*      */     } 
/* 4946 */     if (Trace.isOn) {
/* 4947 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollback()", 3);
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
/*      */   private void rollbackQ() throws JMSException {
/* 4966 */     if (Trace.isOn) {
/* 4967 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollbackQ()");
/*      */     }
/*      */     try {
/* 4970 */       if (!this.transacted) {
/*      */ 
/*      */         
/* 4973 */         String key = "MQJMS1019";
/* 4974 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 4975 */         IllegalStateException je = new IllegalStateException(msg, key);
/*      */         
/* 4977 */         if (Trace.isOn) {
/* 4978 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollbackQ()", (Throwable)je, 1);
/*      */         }
/*      */         
/* 4981 */         throw je;
/*      */       } 
/*      */       
/* 4984 */       if (this.qm == null) {
/* 4985 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/* 4986 */         if (Trace.isOn) {
/* 4987 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollbackQ()", (Throwable)je, 2);
/*      */         }
/*      */         
/* 4990 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 4996 */         synchronized (getTransactionLock()) {
/* 4997 */           this.qm.backout();
/* 4998 */           setCommitRequired(false);
/*      */         }
/*      */       
/* 5001 */       } catch (MQException e) {
/* 5002 */         if (Trace.isOn) {
/* 5003 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollbackQ()", (Throwable)e, 1);
/*      */         }
/*      */         
/* 5006 */         JMSException je = ConfigEnvironment.newException("MQJMS1023");
/* 5007 */         je.setLinkedException((Exception)e);
/* 5008 */         if (Trace.isOn) {
/* 5009 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollbackQ()", (Throwable)je, 3);
/*      */         }
/*      */         
/* 5012 */         throw je;
/*      */       }
/*      */     
/* 5015 */     } catch (JMSException je) {
/* 5016 */       if (Trace.isOn) {
/* 5017 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollbackQ()", (Throwable)je, 2);
/*      */       }
/*      */       
/* 5020 */       if (Trace.isOn) {
/* 5021 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollbackQ()", (Throwable)je, 4);
/*      */       }
/*      */       
/* 5024 */       throw je;
/*      */     } 
/* 5026 */     if (Trace.isOn) {
/* 5027 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollbackQ()");
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
/*      */   private void rollbackT() throws JMSException {
/* 5046 */     if (Trace.isOn) {
/* 5047 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollbackT()");
/*      */     }
/*      */     try {
/* 5050 */       if (!this.transacted) {
/* 5051 */         String key = "MQJMS1019";
/* 5052 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 5053 */         IllegalStateException illegalStateException = new IllegalStateException(msg, key);
/* 5054 */         if (Trace.isOn) {
/* 5055 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollbackT()", (Throwable)illegalStateException, 1);
/*      */         }
/*      */         
/* 5058 */         throw illegalStateException;
/*      */       } 
/*      */       
/* 5061 */       if (this.qm == null) {
/* 5062 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/* 5063 */         if (Trace.isOn) {
/* 5064 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollbackT()", (Throwable)je, 2);
/*      */         }
/*      */         
/* 5067 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 5073 */         synchronized (getTransactionLock()) {
/* 5074 */           this.qm.backout();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 5080 */           setCommitRequired(false);
/*      */ 
/*      */ 
/*      */           
/* 5084 */           if (this.responseOnCommit == true) {
/* 5085 */             this.responseOnCommit = false;
/*      */           }
/*      */         }
/*      */       
/* 5089 */       } catch (MQException e) {
/* 5090 */         if (Trace.isOn) {
/* 5091 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollbackT()", (Throwable)e, 1);
/*      */         }
/*      */         
/* 5094 */         JMSException je = ConfigEnvironment.newException("MQJMS1023");
/* 5095 */         je.setLinkedException((Exception)e);
/* 5096 */         if (Trace.isOn) {
/* 5097 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollbackT()", (Throwable)je, 3);
/*      */         }
/*      */         
/* 5100 */         throw je;
/*      */       }
/*      */     
/*      */     }
/* 5104 */     catch (JMSException je) {
/* 5105 */       if (Trace.isOn) {
/* 5106 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollbackT()", (Throwable)je, 2);
/*      */       }
/*      */       
/* 5109 */       if (Trace.isOn) {
/* 5110 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollbackT()", (Throwable)je, 4);
/*      */       }
/*      */       
/* 5113 */       throw je;
/*      */     } 
/* 5115 */     if (Trace.isOn) {
/* 5116 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "rollbackT()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setCommitRequired(boolean newVal) {
/* 5126 */     if (Trace.isOn) {
/* 5127 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "setCommitRequired(boolean)", "setter", 
/* 5128 */           Boolean.valueOf(newVal));
/*      */     }
/*      */     
/* 5131 */     synchronized (this.commitLock) {
/* 5132 */       this.commitRequired = newVal;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setDistTransactionMode(int newMode) {
/* 5141 */     if (Trace.isOn) {
/* 5142 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "setDistTransactionMode(int)", "setter", 
/* 5143 */           Integer.valueOf(newMode));
/*      */     }
/* 5145 */     this.distTransactionMode = newMode;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setPlayNice(boolean x) {
/* 5150 */     if (Trace.isOn) {
/* 5151 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "setPlayNice(boolean)", "setter", 
/* 5152 */           Boolean.valueOf(x));
/*      */     }
/* 5154 */     this.playNice = x;
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
/*      */   public void start() throws SessionClosedException, JMSException {
/* 5170 */     if (Trace.isOn) {
/* 5171 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "start()");
/*      */     }
/*      */     
/*      */     try {
/* 5175 */       if (this.state == 2) {
/* 5176 */         String msg = ConfigEnvironment.getErrorMessage("MQJMS1024");
/* 5177 */         SessionClosedException je = new SessionClosedException(msg);
/* 5178 */         if (Trace.isOn) {
/* 5179 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "start()", (Throwable)je, 1);
/*      */         }
/*      */         
/* 5182 */         throw je;
/*      */       } 
/* 5184 */       if (this.state != 1)
/*      */       {
/*      */         
/* 5187 */         if (this.state == 0) {
/*      */           
/* 5189 */           this.state = 1;
/*      */ 
/*      */           
/* 5192 */           synchronized (this.stateChangeSem) {
/* 5193 */             this.stateChangeSem.notifyAll();
/*      */           } 
/*      */         } else {
/*      */           
/* 5197 */           JMSException je = ConfigEnvironment.newException("MQJMS1005", 
/* 5198 */               String.valueOf(this.state), "STATE_STARTED");
/*      */           
/* 5200 */           if (Trace.isOn) {
/* 5201 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "start()", (Throwable)je, 2);
/*      */           }
/*      */           
/* 5204 */           throw je;
/*      */         } 
/*      */       }
/* 5207 */     } catch (JMSException je) {
/* 5208 */       if (Trace.isOn) {
/* 5209 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "start()", (Throwable)je);
/*      */       }
/*      */       
/* 5212 */       if (Trace.isOn) {
/* 5213 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "start()", (Throwable)je, 3);
/*      */       }
/*      */       
/* 5216 */       throw je;
/*      */     } 
/* 5218 */     if (Trace.isOn) {
/* 5219 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "start()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void stop() throws SessionClosedException, JMSException {
/* 5229 */     if (Trace.isOn) {
/* 5230 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "stop()");
/*      */     }
/*      */ 
/*      */     
/* 5234 */     stopWithOptionalWait(true);
/*      */     
/* 5236 */     if (Trace.isOn) {
/* 5237 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "stop()");
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
/*      */   public boolean stopWithOptionalWait(boolean waitUntilStopped) throws SessionClosedException, JMSException {
/* 5263 */     if (Trace.isOn)
/* 5264 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "stopWithOptionalWait(boolean)", new Object[] {
/*      */             
/* 5266 */             Boolean.valueOf(waitUntilStopped)
/*      */           }); 
/* 5268 */     boolean retVal = false;
/*      */     try {
/* 5270 */       if (this.state == 2) {
/* 5271 */         String msg = ConfigEnvironment.getErrorMessage("MQJMS1024");
/* 5272 */         SessionClosedException je = new SessionClosedException(msg);
/* 5273 */         if (Trace.isOn) {
/* 5274 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "stopWithOptionalWait(boolean)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 5277 */         throw je;
/*      */       } 
/* 5279 */       if (this.state == 1) {
/*      */         
/* 5281 */         this.state = 0;
/*      */ 
/*      */ 
/*      */         
/* 5285 */         synchronized (this.transactionLock) {
/*      */         
/*      */         } 
/*      */         
/* 5289 */         if (this.asyncHelper != null) {
/* 5290 */           if (waitUntilStopped) {
/*      */             
/* 5292 */             waitForAsyncConsumerToPause();
/*      */           } else {
/*      */             
/* 5295 */             retVal = true;
/*      */           }
/*      */         
/*      */         }
/* 5299 */       } else if (this.state != 0) {
/*      */ 
/*      */ 
/*      */         
/* 5303 */         JMSException je = ConfigEnvironment.newException("MQJMS1005", 
/* 5304 */             String.valueOf(this.state), "STATE_STOPPED");
/*      */         
/* 5306 */         if (Trace.isOn) {
/* 5307 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "stopWithOptionalWait(boolean)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 5310 */         throw je;
/*      */       }
/*      */     
/* 5313 */     } catch (JMSException je) {
/* 5314 */       if (Trace.isOn) {
/* 5315 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "stopWithOptionalWait(boolean)", (Throwable)je);
/*      */       }
/*      */       
/* 5318 */       if (Trace.isOn) {
/* 5319 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "stopWithOptionalWait(boolean)", (Throwable)je, 3);
/*      */       }
/*      */       
/* 5322 */       throw je;
/*      */     } 
/* 5324 */     if (Trace.isOn) {
/* 5325 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "stopWithOptionalWait(boolean)", 
/* 5326 */           Boolean.valueOf(retVal));
/*      */     }
/*      */     
/* 5329 */     return retVal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void waitForAsyncConsumerToPause() {
/* 5338 */     if (Trace.isOn) {
/* 5339 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "waitForAsyncConsumerToPause()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 5344 */     synchronized (this.transactionLock) {
/*      */     
/*      */     } 
/*      */     
/* 5348 */     if (this.asyncHelper != null) {
/* 5349 */       this.asyncHelper.waitForPaused();
/*      */     }
/* 5351 */     if (Trace.isOn) {
/* 5352 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "waitForAsyncConsumerToPause()");
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
/*      */   protected boolean supportsInherited() {
/* 5370 */     if (Trace.isOn) {
/* 5371 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "supportsInherited()");
/*      */     }
/*      */ 
/*      */     
/* 5375 */     if (this.distTransactionMode == 1 || this.distTransactionMode == 2) {
/*      */       
/* 5377 */       this.ITXSupported = false;
/*      */     }
/* 5379 */     else if (!this.ITXSupportChecked) {
/* 5380 */       if (Trace.isOn) {
/* 5381 */         Trace.traceData(this, "Haven't yet checked for session ITX support. Checking now", null);
/*      */       }
/*      */       
/*      */       try {
/* 5385 */         if (this.qm.spiSupportsInherited()) {
/* 5386 */           if (Trace.isOn) {
/* 5387 */             Trace.traceData(this, "Queue manager supports ITX", null);
/*      */           }
/* 5389 */           this.ITXSupported = true;
/* 5390 */           this.ITXSupportChecked = true;
/*      */         } else {
/*      */           
/* 5393 */           if (Trace.isOn) {
/* 5394 */             Trace.traceData(this, "Queue manager doesn't support ITX", null);
/*      */           }
/* 5396 */           this.ITXSupported = false;
/* 5397 */           this.ITXSupportChecked = true;
/*      */         }
/*      */       
/* 5400 */       } catch (MQException mqe) {
/* 5401 */         if (Trace.isOn) {
/* 5402 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "supportsInherited()", (Throwable)mqe);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 5408 */         if (Trace.isOn) {
/* 5409 */           Trace.traceData(this, "Exception thrown checking for ITX support. Assuming not supported", null);
/*      */         }
/* 5411 */         this.ITXSupported = false;
/* 5412 */         this.ITXSupportChecked = true;
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 5417 */     else if (Trace.isOn) {
/* 5418 */       Trace.traceData(this, "ITXSupport was previously checked. ITXSupported = " + this.ITXSupported, null);
/*      */     } 
/*      */ 
/*      */     
/* 5422 */     if (Trace.isOn) {
/* 5423 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "supportsInherited()", 
/* 5424 */           Boolean.valueOf(this.ITXSupported));
/*      */     }
/* 5426 */     return this.ITXSupported;
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
/*      */   private void unsubscribe(String name) throws JMSException {
/* 5451 */     if (Trace.isOn) {
/* 5452 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "unsubscribe(String)", new Object[] { name });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 5458 */       addPubSubServices();
/*      */       
/* 5460 */       MQSubscriptionEngine engine = this.connection.getSubscriptionEngine();
/* 5461 */       if (engine != null) {
/* 5462 */         engine.durableUnsubscribe(this, name);
/*      */       
/*      */       }
/*      */     }
/* 5466 */     catch (JMSException je) {
/* 5467 */       if (Trace.isOn) {
/* 5468 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "unsubscribe(String)", (Throwable)je, 1);
/*      */       }
/*      */       
/* 5471 */       JMSException newJE = null;
/*      */       try {
/* 5473 */         int rc = Integer.parseInt(je.getErrorCode());
/*      */         
/* 5475 */         if (rc == 3157)
/*      */         {
/* 5477 */           String key = "MQJMS3023";
/* 5478 */           String msg = ConfigEnvironment.getErrorMessage(key);
/* 5479 */           IllegalStateException illegalStateException = new IllegalStateException(msg, key);
/*      */           
/* 5481 */           illegalStateException.setLinkedException(je.getLinkedException());
/* 5482 */           illegalStateException.initCause(je.getLinkedException());
/*      */         }
/*      */         else
/*      */         {
/* 5486 */           newJE = je;
/*      */         }
/*      */       
/* 5489 */       } catch (NumberFormatException nfe) {
/* 5490 */         if (Trace.isOn) {
/* 5491 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "unsubscribe(String)", nfe, 2);
/*      */         }
/*      */ 
/*      */         
/* 5495 */         newJE = je;
/*      */       } 
/*      */ 
/*      */       
/* 5499 */       if (Trace.isOn) {
/* 5500 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "unsubscribe(String)", (Throwable)newJE);
/*      */       }
/*      */       
/* 5503 */       throw newJE;
/*      */     } 
/* 5505 */     if (Trace.isOn) {
/* 5506 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "unsubscribe(String)");
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
/*      */   protected boolean usingAsyncMode() {
/*      */     boolean result;
/* 5520 */     if (Trace.isOn) {
/* 5521 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "usingAsyncMode()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 5526 */     if (this.asyncHelper == null) {
/* 5527 */       result = false;
/*      */     } else {
/*      */       
/* 5530 */       result = this.asyncHelper.hasReceivers();
/*      */     } 
/* 5532 */     if (Trace.isOn) {
/* 5533 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "usingAsyncMode()", 
/* 5534 */           Boolean.valueOf(result));
/*      */     }
/* 5536 */     return result;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void waitForStart() throws SessionClosedException, JMSException {
/*      */     // Byte code:
/*      */     //   0: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   3: ifeq -> 15
/*      */     //   6: aload_0
/*      */     //   7: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQSession'
/*      */     //   9: ldc_w 'waitForStart()'
/*      */     //   12: invokestatic entry : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   15: aload_0
/*      */     //   16: getfield state : I
/*      */     //   19: ifne -> 73
/*      */     //   22: aload_0
/*      */     //   23: getfield stateChangeSem : Ljava/lang/Object;
/*      */     //   26: dup
/*      */     //   27: astore_1
/*      */     //   28: monitorenter
/*      */     //   29: aload_0
/*      */     //   30: getfield stateChangeSem : Ljava/lang/Object;
/*      */     //   33: ldc2_w 5000
/*      */     //   36: invokevirtual wait : (J)V
/*      */     //   39: goto -> 60
/*      */     //   42: astore_2
/*      */     //   43: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   46: ifeq -> 60
/*      */     //   49: aload_0
/*      */     //   50: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQSession'
/*      */     //   52: ldc_w 'waitForStart()'
/*      */     //   55: aload_2
/*      */     //   56: iconst_1
/*      */     //   57: invokestatic catchBlock : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   60: aload_1
/*      */     //   61: monitorexit
/*      */     //   62: goto -> 70
/*      */     //   65: astore_3
/*      */     //   66: aload_1
/*      */     //   67: monitorexit
/*      */     //   68: aload_3
/*      */     //   69: athrow
/*      */     //   70: goto -> 15
/*      */     //   73: aload_0
/*      */     //   74: getfield state : I
/*      */     //   77: lookupswitch default -> 142, 1 -> 104, 2 -> 107
/*      */     //   104: goto -> 253
/*      */     //   107: ldc_w 'MQJMS1024'
/*      */     //   110: invokestatic getErrorMessage : (Ljava/lang/String;)Ljava/lang/String;
/*      */     //   113: astore_1
/*      */     //   114: new com/ibm/mq/jms/SessionClosedException
/*      */     //   117: dup
/*      */     //   118: aload_1
/*      */     //   119: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   122: astore_2
/*      */     //   123: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   126: ifeq -> 140
/*      */     //   129: aload_0
/*      */     //   130: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQSession'
/*      */     //   132: ldc_w 'waitForStart()'
/*      */     //   135: aload_2
/*      */     //   136: iconst_1
/*      */     //   137: invokestatic throwing : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   140: aload_2
/*      */     //   141: athrow
/*      */     //   142: ldc_w 'MQJMS1006'
/*      */     //   145: ldc_w 'state'
/*      */     //   148: aload_0
/*      */     //   149: getfield state : I
/*      */     //   152: invokestatic valueOf : (I)Ljava/lang/String;
/*      */     //   155: invokestatic getMessage : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/String;
/*      */     //   158: astore_3
/*      */     //   159: new java/util/HashMap
/*      */     //   162: dup
/*      */     //   163: invokespecial <init> : ()V
/*      */     //   166: astore #4
/*      */     //   168: aload #4
/*      */     //   170: ldc 'Message'
/*      */     //   172: ldc 'MQJMS1016'
/*      */     //   174: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*      */     //   177: pop
/*      */     //   178: aload #4
/*      */     //   180: ldc_w 'Comment'
/*      */     //   183: aload_3
/*      */     //   184: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*      */     //   187: pop
/*      */     //   188: aload_0
/*      */     //   189: ldc_w 'waitForStart()'
/*      */     //   192: ldc_w '03'
/*      */     //   195: aload #4
/*      */     //   197: ldc javax/jms/JMSException
/*      */     //   199: invokestatic ffst : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/util/HashMap;Ljava/lang/Class;)V
/*      */     //   202: ldc 'MQJMS1016'
/*      */     //   204: new java/lang/StringBuilder
/*      */     //   207: dup
/*      */     //   208: invokespecial <init> : ()V
/*      */     //   211: ldc_w 'unknown value of state '
/*      */     //   214: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   217: aload_0
/*      */     //   218: getfield state : I
/*      */     //   221: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*      */     //   224: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   227: invokestatic newException : (Ljava/lang/String;Ljava/lang/Object;)Ljavax/jms/JMSException;
/*      */     //   230: astore #5
/*      */     //   232: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   235: ifeq -> 250
/*      */     //   238: aload_0
/*      */     //   239: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQSession'
/*      */     //   241: ldc_w 'waitForStart()'
/*      */     //   244: aload #5
/*      */     //   246: iconst_2
/*      */     //   247: invokestatic throwing : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   250: aload #5
/*      */     //   252: athrow
/*      */     //   253: goto -> 293
/*      */     //   256: astore_1
/*      */     //   257: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   260: ifeq -> 274
/*      */     //   263: aload_0
/*      */     //   264: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQSession'
/*      */     //   266: ldc_w 'waitForStart()'
/*      */     //   269: aload_1
/*      */     //   270: iconst_2
/*      */     //   271: invokestatic catchBlock : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   274: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   277: ifeq -> 291
/*      */     //   280: aload_0
/*      */     //   281: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQSession'
/*      */     //   283: ldc_w 'waitForStart()'
/*      */     //   286: aload_1
/*      */     //   287: iconst_3
/*      */     //   288: invokestatic throwing : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;I)V
/*      */     //   291: aload_1
/*      */     //   292: athrow
/*      */     //   293: getstatic com/ibm/msg/client/commonservices/trace/Trace.isOn : Z
/*      */     //   296: ifeq -> 308
/*      */     //   299: aload_0
/*      */     //   300: ldc 'com.ibm.msg.client.wmq.compat.jms.internal.MQSession'
/*      */     //   302: ldc_w 'waitForStart()'
/*      */     //   305: invokestatic exit : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   308: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #5556	-> 0
/*      */     //   #5557	-> 6
/*      */     //   #5562	-> 15
/*      */     //   #5563	-> 22
/*      */     //   #5565	-> 29
/*      */     //   #5573	-> 39
/*      */     //   #5567	-> 42
/*      */     //   #5568	-> 43
/*      */     //   #5569	-> 49
/*      */     //   #5574	-> 60
/*      */     //   #5578	-> 73
/*      */     //   #5581	-> 104
/*      */     //   #5585	-> 107
/*      */     //   #5586	-> 114
/*      */     //   #5587	-> 123
/*      */     //   #5588	-> 129
/*      */     //   #5591	-> 140
/*      */     //   #5596	-> 142
/*      */     //   #5597	-> 159
/*      */     //   #5598	-> 168
/*      */     //   #5599	-> 178
/*      */     //   #5600	-> 188
/*      */     //   #5602	-> 202
/*      */     //   #5603	-> 232
/*      */     //   #5604	-> 238
/*      */     //   #5607	-> 250
/*      */     //   #5621	-> 253
/*      */     //   #5611	-> 256
/*      */     //   #5612	-> 257
/*      */     //   #5613	-> 263
/*      */     //   #5616	-> 274
/*      */     //   #5617	-> 280
/*      */     //   #5620	-> 291
/*      */     //   #5622	-> 293
/*      */     //   #5623	-> 299
/*      */     //   #5625	-> 308
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   43	17	2	e	Ljava/lang/InterruptedException;
/*      */     //   114	28	1	msg	Ljava/lang/String;
/*      */     //   123	19	2	sce	Lcom/ibm/mq/jms/SessionClosedException;
/*      */     //   159	94	3	detail	Ljava/lang/String;
/*      */     //   168	85	4	ffstData	Ljava/util/HashMap;
/*      */     //   232	21	5	je	Ljavax/jms/JMSException;
/*      */     //   257	36	1	je	Ljavax/jms/JMSException;
/*      */     //   0	309	0	this	Lcom/ibm/msg/client/wmq/compat/jms/internal/MQSession;
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   168	85	4	ffstData	Ljava/util/HashMap<Ljava/lang/String;Ljava/lang/String;>;
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   15	253	256	javax/jms/JMSException
/*      */     //   29	39	42	java/lang/InterruptedException
/*      */     //   29	62	65	finally
/*      */     //   65	68	65	finally
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected long waitForStart(long timeout) throws SessionClosedException, JMSException {
/* 5647 */     if (Trace.isOn)
/* 5648 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "waitForStart(long)", new Object[] {
/* 5649 */             Long.valueOf(timeout) });  try {
/*      */       String msg; SessionClosedException sce; String detail;
/*      */       HashMap<String, String> ffstData;
/*      */       JMSException je;
/* 5653 */       long output = 0L;
/*      */       
/* 5655 */       long timeEntered = System.currentTimeMillis();
/* 5656 */       long endTime = timeEntered + timeout;
/*      */ 
/*      */       
/* 5659 */       long currTime = timeEntered;
/* 5660 */       while (this.state == 0 && currTime < endTime) {
/* 5661 */         synchronized (this.stateChangeSem) {
/*      */           try {
/* 5663 */             this.stateChangeSem.wait(endTime - currTime);
/*      */           }
/* 5665 */           catch (InterruptedException e) {
/* 5666 */             if (Trace.isOn) {
/* 5667 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "waitForStart(long)", e, 1);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 5672 */           currTime = System.currentTimeMillis();
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 5677 */       currTime = System.currentTimeMillis();
/*      */ 
/*      */       
/* 5680 */       switch (this.state) {
/*      */         
/*      */         case 1:
/* 5683 */           output = Math.max(0L, endTime - currTime);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 2:
/* 5688 */           msg = ConfigEnvironment.getErrorMessage("MQJMS1024");
/* 5689 */           sce = new SessionClosedException(msg);
/* 5690 */           if (Trace.isOn) {
/* 5691 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "waitForStart(long)", (Throwable)sce, 1);
/*      */           }
/*      */           
/* 5694 */           throw sce;
/*      */ 
/*      */ 
/*      */         
/*      */         case 0:
/* 5699 */           output = 0L;
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/* 5705 */           detail = ConfigEnvironment.getMessage("MQJMS1006", "state", String.valueOf(this.state));
/* 5706 */           ffstData = new HashMap<>();
/* 5707 */           ffstData.put("Message", "MQJMS1016");
/* 5708 */           ffstData.put("Comment", detail);
/* 5709 */           Trace.ffst(this, "waitForStart(long)", "04", ffstData, JMSException.class);
/*      */           
/* 5711 */           je = ConfigEnvironment.newException("MQJMS1016", "unknown value of state " + this.state);
/* 5712 */           if (Trace.isOn) {
/* 5713 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "waitForStart(long)", (Throwable)je, 2);
/*      */           }
/*      */           
/* 5716 */           throw je;
/*      */       } 
/*      */       
/* 5719 */       if (Trace.isOn) {
/* 5720 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "waitForStart(long)", 
/* 5721 */             Long.valueOf(output));
/*      */       }
/* 5723 */       return output;
/*      */     
/*      */     }
/* 5726 */     catch (JMSException je) {
/* 5727 */       if (Trace.isOn) {
/* 5728 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "waitForStart(long)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 5731 */       if (Trace.isOn) {
/* 5732 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "waitForStart(long)", (Throwable)je, 3);
/*      */       }
/*      */       
/* 5735 */       throw je;
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
/*      */   public boolean isMessageAlien(ProviderMessage message) throws JMSException {
/* 5748 */     if (Trace.isOn) {
/* 5749 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "isMessageAlien(ProviderMessage)", new Object[] { message });
/*      */     }
/*      */     
/* 5752 */     if (message instanceof JMSMessage) {
/* 5753 */       if (Trace.isOn) {
/* 5754 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "isMessageAlien(ProviderMessage)", 
/* 5755 */             Boolean.valueOf(false), 1);
/*      */       }
/* 5757 */       return false;
/*      */     } 
/* 5759 */     if (Trace.isOn) {
/* 5760 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "isMessageAlien(ProviderMessage)", 
/* 5761 */           Boolean.valueOf(true), 2);
/*      */     }
/* 5763 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInGlobalTransaction() {
/* 5771 */     boolean uow = (this.distTransactionMode == 1 || Utils.isRRSTransactionInProgress());
/* 5772 */     if (Trace.isOn) {
/* 5773 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "isInGlobalTransaction()", "getter", 
/* 5774 */           Boolean.valueOf(uow));
/*      */     }
/* 5776 */     return uow;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 5784 */     if (Trace.isOn) {
/* 5785 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "readObject(ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */     
/* 5788 */     NotSerializableException traceRet1 = new NotSerializableException();
/* 5789 */     if (Trace.isOn) {
/* 5790 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "readObject(ObjectInputStream)", traceRet1);
/*      */     }
/*      */     
/* 5793 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 5802 */     if (Trace.isOn) {
/* 5803 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "writeObject(ObjectOutputStream)", new Object[] { out });
/*      */     }
/*      */     
/* 5806 */     NotSerializableException traceRet1 = new NotSerializableException();
/* 5807 */     if (Trace.isOn) {
/* 5808 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "writeObject(ObjectOutputStream)", traceRet1);
/*      */     }
/*      */     
/* 5811 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 5820 */     if (Trace.isOn) {
/* 5821 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "hashCode()");
/*      */     }
/* 5823 */     int traceRet1 = super.hashCode();
/* 5824 */     if (Trace.isOn) {
/* 5825 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "hashCode()", 
/* 5826 */           Integer.valueOf(traceRet1));
/*      */     }
/* 5828 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object o) {
/* 5837 */     if (Trace.isOn) {
/* 5838 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "equals(Object)", new Object[] { o });
/*      */     }
/*      */     
/* 5841 */     boolean traceRet1 = super.equals(o);
/* 5842 */     if (Trace.isOn) {
/* 5843 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "equals(Object)", 
/* 5844 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 5846 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean calculateUseSPIP() throws JMSException {
/* 5856 */     if (Trace.isOn) {
/* 5857 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "calculateUseSPIP()");
/*      */     }
/*      */ 
/*      */     
/* 5861 */     boolean useSPIP = false;
/*      */     
/* 5863 */     if (!getTransacted() && this.acknowledgeMode != 2 && !getSyncpointAllGets()) {
/* 5864 */       useSPIP = true;
/*      */     } else {
/*      */       
/* 5867 */       useSPIP = false;
/*      */     } 
/* 5869 */     if (Trace.isOn) {
/* 5870 */       Trace.traceData(this, "calculateUseSPIP set useSPIP to ", Boolean.valueOf(useSPIP));
/*      */     }
/* 5872 */     if (Trace.isOn) {
/* 5873 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "calculateUseSPIP()", 
/* 5874 */           Boolean.valueOf(useSPIP));
/*      */     }
/* 5876 */     return useSPIP;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class TransactionLock
/*      */   {
/*      */     private TransactionLock() {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class BrokerResponseLock
/*      */   {
/*      */     private BrokerResponseLock() {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ProviderMessageConsumer createSharedConsumer(ProviderDestination destination, String sharedSubscriptionName, String selector, JmsPropertyContext propertySontext) throws JMSException {
/* 5904 */     if (Trace.isOn) {
/* 5905 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createSharedConsumer(ProviderDestination,String,String,JmsPropertyContext)", new Object[] { destination, sharedSubscriptionName, selector, propertySontext });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 5910 */     JMSException je = ConfigEnvironment.newException("MQJMS6405");
/* 5911 */     if (Trace.isOn) {
/* 5912 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createSharedConsumer(ProviderDestination,String,String,JmsPropertyContext)", (Throwable)je);
/*      */     }
/*      */     
/* 5915 */     throw je;
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
/*      */   public ProviderMessageConsumer createSharedDurableConsumer(ProviderDestination destination, String sharedSubscriptionName, String selector, JmsPropertyContext propertySontext) throws JMSException {
/* 5934 */     if (Trace.isOn) {
/* 5935 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createSharedDurableConsumer(ProviderDestination,String,String,JmsPropertyContext)", new Object[] { destination, sharedSubscriptionName, selector, propertySontext });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 5940 */     JMSException je = ConfigEnvironment.newException("MQJMS6405");
/* 5941 */     if (Trace.isOn) {
/* 5942 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "createSharedDurableConsumer(ProviderDestination,String,String,JmsPropertyContext)", (Throwable)je);
/*      */     }
/*      */     
/* 5945 */     throw je;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dump(PrintWriter pw, int level) {
/* 5954 */     if (Trace.isOn) {
/* 5955 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "dump(PrintWriter,int)", new Object[] { pw, 
/* 5956 */             Integer.valueOf(level) });
/*      */     }
/* 5958 */     StringBuilder prefix = new StringBuilder();
/* 5959 */     for (int i = 0; i < level; i++) {
/* 5960 */       prefix.append("  ");
/*      */     }
/* 5962 */     pw.format("%s%s%n", new Object[] { prefix, toString() });
/* 5963 */     if (this.connection == null) {
/* 5964 */       pw.format("%s  Parent Connection <null>%n", new Object[] { prefix });
/*      */     } else {
/*      */       
/* 5967 */       pw.format("%s  Parent Connection %s%n", new Object[] { prefix, this.connection.getClass().getName() + '@' + Integer.toHexString(this.connection.hashCode()) });
/*      */     } 
/* 5969 */     if (Trace.isOn)
/* 5970 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQSession", "dump(PrintWriter,int)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */