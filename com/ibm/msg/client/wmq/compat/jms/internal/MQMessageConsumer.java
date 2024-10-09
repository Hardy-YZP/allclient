/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.MQHeader;
/*      */ import com.ibm.mq.jmqi.MQRFH;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiComponentTls;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.mq.jms.NoMsgListenerException;
/*      */ import com.ibm.mq.jms.SessionClosedException;
/*      */ import com.ibm.mq.jms.SyntaxException;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessageConsumer;
/*      */ import com.ibm.msg.client.provider.ProviderMessageListener;
/*      */ import com.ibm.msg.client.wmq.common.internal.Reason;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQPropertyContext;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMessage;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQSESSION;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.NotSerializableException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.Serializable;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.Hashtable;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.TimeZone;
/*      */ import java.util.concurrent.locks.ReentrantLock;
/*      */ import javax.jms.IllegalStateException;
/*      */ import javax.jms.InvalidDestinationException;
/*      */ import javax.jms.InvalidSelectorException;
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
/*      */ public class MQMessageConsumer
/*      */   extends WMQPropertyContext
/*      */   implements ProviderMessageConsumer
/*      */ {
/*      */   private static final long serialVersionUID = -7819506386906934953L;
/*      */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQMessageConsumer.java";
/*      */   
/*      */   static {
/*   86 */     if (Trace.isOn) {
/*   87 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQMessageConsumer.java");
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
/*  111 */   private static int CHUNKTIME = 5000;
/*      */ 
/*      */   
/*      */   static final int DEFAULT_MAX_ASYNC_ATTEMPTS = 5;
/*      */ 
/*      */   
/*      */   private static final int NO_SYNCPOINT = 3;
/*      */ 
/*      */   
/*      */   private static final String PROBE_01 = "01";
/*      */ 
/*      */   
/*      */   private static final String PROBE_02 = "02";
/*      */ 
/*      */   
/*      */   private static final String PROBE_03 = "03";
/*      */ 
/*      */   
/*      */   private static final int SPIP = 2;
/*      */ 
/*      */   
/*      */   private static final int SYNCPOINT = 1;
/*      */ 
/*      */   
/*      */   protected static final String useDefaultBOValues = "com.ibm.mq.jms.useDefaultBOValues";
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
/*      */   private static final long MQRFH2_ASCII = 5571313732236222496L;
/*      */   
/*      */   private static final long MQRFH2_EBCDIC = -3109514705028104128L;
/*      */   
/*      */   private int acknowledgeMode;
/*      */ 
/*      */   
/*      */   static {
/*  155 */     if (Trace.isOn) {
/*  156 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "static()");
/*      */     }
/*      */ 
/*      */     
/*  160 */     String chunkTimeProperty = "com.ibm.mq.jms.ChunkTime";
/*  161 */     PropertyStore.register(chunkTimeProperty, 5000L, Long.valueOf(5000L), Long.valueOf(2147483647L));
/*  162 */     long chunkTimeValue = PropertyStore.getLongProperty(chunkTimeProperty);
/*      */     
/*  164 */     CHUNKTIME = (int)chunkTimeValue;
/*      */     
/*  166 */     if (Trace.isOn) {
/*  167 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "static()");
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
/*  184 */   private String backoutRetryQueue = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  190 */   private int backoutThreshold = 20;
/*      */ 
/*      */ 
/*      */   
/*      */   private MQJMSMessage baseMessage;
/*      */ 
/*      */ 
/*      */   
/*  198 */   private String baseQueueName = null;
/*      */ 
/*      */   
/*      */   private boolean closed = false;
/*      */ 
/*      */   
/*      */   private boolean closing = false;
/*      */ 
/*      */   
/*      */   private boolean durable;
/*      */ 
/*      */   
/*      */   private int getOptions;
/*      */ 
/*      */   
/*      */   private int browseOptions;
/*      */ 
/*      */   
/*      */   private int deleteOptions;
/*      */   
/*      */   private int acceptOptions;
/*      */   
/*      */   private MQGetMessageOptions gmo;
/*      */   
/*  222 */   private ProviderMessageListener listener = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  229 */   private int maxAsyncAttempts = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  234 */   private MQMessageSelector messageSelector = null;
/*      */ 
/*      */   
/*  237 */   private String mqDLQName = null;
/*      */ 
/*      */   
/*  240 */   private String name = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean needsRestart = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  255 */   private Hashtable<String, Object> nextGenSeenList = new Hashtable<>();
/*      */   
/*      */   private boolean noLocal = false;
/*  258 */   private Object nullValue = new Object();
/*      */   
/*  260 */   private Hashtable<String, Object> prevGenSeenList = new Hashtable<>();
/*      */ 
/*      */   
/*  263 */   private MQQueue queue = null;
/*      */ 
/*      */   
/*      */   private long rescanTimeInterval;
/*      */   
/*  268 */   private long rescanTimeMillis = 0L;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean running = true;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean selectorSet = false;
/*      */ 
/*      */ 
/*      */   
/*  280 */   private MQSession session = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean setForPTP = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean setForPubSub = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean shared_queue = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean specialCase;
/*      */ 
/*      */ 
/*      */   
/*  300 */   private byte[] subscriberId = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  305 */   MQSubscription subscription = null;
/*      */ 
/*      */   
/*  308 */   private int syncPoint = 1;
/*      */ 
/*      */   
/*      */   private boolean transacted;
/*      */ 
/*      */   
/*  314 */   private int type = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean useBrowse = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private WMQDestination destination;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQMessageConsumer(WMQDestination queueSpec, String selectorString, MQQueue queue, boolean transacted, int acknowledgeModeP, MQSession session) throws JMSException {
/*  349 */     super(null);
/*  350 */     if (Trace.isOn) {
/*  351 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "<init>(WMQDestination,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,boolean,int,MQSession)", new Object[] { queueSpec, selectorString, queue, 
/*      */             
/*  353 */             Boolean.valueOf(transacted), 
/*  354 */             Integer.valueOf(acknowledgeModeP), session });
/*      */     }
/*      */     
/*  357 */     int acknowledgeMode1 = acknowledgeModeP;
/*      */ 
/*      */     
/*  360 */     this.destination = queueSpec;
/*      */ 
/*      */ 
/*      */     
/*  364 */     this.setForPTP = true;
/*      */     
/*  366 */     acknowledgeMode1 = session.getAcknowledgeMode();
/*      */ 
/*      */     
/*  369 */     this.baseMessage = new MQJMSMessage();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  375 */     this.gmo = new MQGetMessageOptions(true);
/*      */ 
/*      */     
/*  378 */     this.rescanTimeInterval = session.connection.getRescanInterval();
/*      */ 
/*      */     
/*  381 */     if (queueSpec.getIntProperty("failIfQuiesce") == 1) {
/*  382 */       this.gmo.options |= 0x2000;
/*  383 */       this.browseOptions |= 0x2000;
/*  384 */       this.acceptOptions |= 0x2000;
/*  385 */       this.deleteOptions |= 0x2000;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  390 */     this.gmo.matchOptions = 0;
/*      */     
/*  392 */     this.specialCase = setMessageSelector(selectorString, this.baseMessage, this.gmo);
/*      */ 
/*      */ 
/*      */     
/*  396 */     if (!this.specialCase) {
/*  397 */       this.gmo.matchOptions = 0;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  406 */     if (selectorString == null || selectorString.trim().length() == 0) {
/*  407 */       this.useBrowse = false;
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/*  416 */       this.useBrowse = !this.specialCase;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  426 */     if (queueSpec.getIntProperty("deliveryMode") == 3 && 
/*  427 */       queue != null && !queue.isNPMClassHigh()) {
/*  428 */       String key = "MQJMS0003";
/*  429 */       String msg = ConfigEnvironment.getErrorMessage(key);
/*  430 */       InvalidDestinationException je = new InvalidDestinationException(msg, key);
/*  431 */       if (Trace.isOn) {
/*  432 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "<init>(WMQDestination,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,boolean,int,MQSession)", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */       
/*  436 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/*  440 */     this.queue = queue;
/*  441 */     this.acknowledgeMode = acknowledgeMode1;
/*  442 */     this.session = session;
/*  443 */     this.transacted = transacted;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  450 */     setSyncpointBehaviour(true);
/*      */     
/*  452 */     this.getOptions = this.gmo.options;
/*      */     
/*  454 */     this.browseOptions |= 0x10;
/*  455 */     this.acceptOptions |= 0x140;
/*  456 */     this.deleteOptions |= 0x144;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  461 */       String borq = "";
/*  462 */       int thresh = 0;
/*      */ 
/*      */       
/*  465 */       this.type = Utils.inquireInt((MQManagedObject)this.queue, 20);
/*  466 */       if (Trace.isOn) {
/*  467 */         Trace.traceData(this, "queue type: " + this.type, null);
/*      */       }
/*      */       
/*  470 */       int[] selectors = { 22, 2019 };
/*  471 */       int[] intAttrs = new int[1];
/*      */ 
/*      */       
/*  474 */       char[] charAttrs = new char[48];
/*      */       
/*  476 */       if (this.type == 3) {
/*  477 */         if (Trace.isOn) {
/*  478 */           Trace.traceData(this, "QUEUE is an ALIAS queue, checking TARGQ", null);
/*      */         }
/*  480 */         PropertyStore.register("com.ibm.mq.jms.useDefaultBOValues", false);
/*      */         
/*  482 */         if (!PropertyStore.getBooleanPropertyObject("com.ibm.mq.jms.useDefaultBOValues").booleanValue())
/*      */         {
/*      */           
/*  485 */           this.baseQueueName = Utils.inquireString((MQManagedObject)this.queue, 2002);
/*      */ 
/*      */           
/*      */           try {
/*  489 */             int options = 8224;
/*  490 */             int[] selectors_type = { 20 };
/*  491 */             int[] intAttrs_type = new int[1];
/*      */             
/*  493 */             MQQueue baseMqQueue = this.session.getQM().accessQueue(this.baseQueueName, options);
/*      */             
/*  495 */             baseMqQueue.inquire(selectors_type, intAttrs_type, (byte[])null);
/*  496 */             if (intAttrs_type[0] == 7) {
/*  497 */               baseMqQueue.close();
/*  498 */               int options_forClusterQ = 8232;
/*  499 */               baseMqQueue = this.session.getQM().accessQueue(this.baseQueueName, options_forClusterQ);
/*      */             } 
/*      */             
/*  502 */             baseMqQueue.inquire(selectors, intAttrs, charAttrs);
/*      */             
/*  504 */             baseMqQueue.close();
/*      */           }
/*  506 */           catch (MQException mqe) {
/*  507 */             if (Trace.isOn) {
/*  508 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "<init>(WMQDestination,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,boolean,int,MQSession)", (Throwable)mqe, 1);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  515 */             HashMap<String, Object> inserts = new HashMap<>();
/*  516 */             inserts.put("XMSC_INSERT_DESTINATION_NAME", this.baseQueueName);
/*  517 */             inserts.put("XMSC_INSERT_STRING", this.destination.getName());
/*  518 */             inserts.put("XMSC_INSERT_VALUE", Integer.valueOf(this.backoutThreshold));
/*  519 */             inserts.put("XMSC_INSERT_EXCEPTION", mqe);
/*  520 */             inserts.put("XMSC_INSERT_COMP_CODE", Integer.valueOf(mqe.getCompCode()));
/*  521 */             inserts.put("XMSC_INSERT_REASON", Integer.valueOf(mqe.getReason()));
/*  522 */             Log.log(this, "MQMessageConsumer(WMQDestination,String,MQQueue,boolean,int,MQSession)", "MQJMS1115", inserts);
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  532 */         queue.inquire(selectors, intAttrs, charAttrs);
/*      */       } 
/*      */       
/*  535 */       borq = new String(charAttrs);
/*      */       
/*  537 */       thresh = intAttrs[0];
/*      */       
/*  539 */       if (Trace.isOn) {
/*  540 */         Trace.traceData(this, "returned from inquire, threshold = " + thresh + ", borq = '" + borq + "' type: " + this.type, null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  546 */       this.backoutThreshold = thresh;
/*      */ 
/*      */       
/*  549 */       if (thresh != 0) {
/*  550 */         this.maxAsyncAttempts = thresh;
/*      */       }
/*  552 */       if (borq.length() != 0) {
/*  553 */         this.backoutRetryQueue = borq;
/*      */       } else {
/*      */         
/*  556 */         this.backoutRetryQueue = session.getDLQName();
/*      */       }
/*      */     
/*      */     }
/*  560 */     catch (RuntimeException rte) {
/*  561 */       if (Trace.isOn) {
/*  562 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "<init>(WMQDestination,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,boolean,int,MQSession)", rte, 2);
/*      */       }
/*      */ 
/*      */       
/*  566 */       if (Trace.isOn) {
/*  567 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "<init>(WMQDestination,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,boolean,int,MQSession)", rte, 2);
/*      */       }
/*      */ 
/*      */       
/*  571 */       throw rte;
/*      */     }
/*  573 */     catch (Exception e) {
/*  574 */       if (Trace.isOn) {
/*  575 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "<init>(WMQDestination,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,boolean,int,MQSession)", e, 3);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  583 */     if (Trace.isOn) {
/*  584 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "<init>(WMQDestination,String,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,boolean,int,MQSession)");
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
/*      */   protected MQMessageConsumer(WMQDestination topicSpec, MQSession session, MQSubscription subscription, boolean transacted, int acknowledgeModeP) throws JMSException {
/*  612 */     super(null);
/*  613 */     if (Trace.isOn) {
/*  614 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "<init>(WMQDestination,MQSession,MQSubscription,boolean,int)", new Object[] { topicSpec, session, subscription, 
/*      */             
/*  616 */             Boolean.valueOf(transacted), Integer.valueOf(acknowledgeModeP) });
/*      */     }
/*      */     
/*  619 */     int acknowledgeMode1 = acknowledgeModeP;
/*      */ 
/*      */     
/*  622 */     this.destination = topicSpec;
/*      */ 
/*      */ 
/*      */     
/*  626 */     this.setForPubSub = true;
/*      */ 
/*      */     
/*  629 */     acknowledgeMode1 = session.getAcknowledgeMode();
/*  630 */     this.queue = subscription.getSubscriberQueue();
/*  631 */     this.session = session;
/*  632 */     this.transacted = transacted;
/*      */     
/*  634 */     if (Trace.isOn && this.queue != null) {
/*  635 */       Trace.traceData(this, this.queue + "(" + this.queue.name + ")", null);
/*      */     }
/*      */ 
/*      */     
/*  639 */     this.noLocal = subscription.getNoLocal();
/*  640 */     this.name = subscription.getQueueName();
/*  641 */     this.subscriberId = subscription.getCorrelationId();
/*  642 */     this.shared_queue = subscription.isSharedQueue();
/*      */     
/*  644 */     String selectorString = subscription.getSelector();
/*      */ 
/*      */     
/*  647 */     this.subscription = subscription;
/*      */     
/*  649 */     this.acknowledgeMode = acknowledgeMode1;
/*      */     
/*  651 */     if (Trace.isOn) {
/*  652 */       Trace.traceData(this, "Queue name = " + this.name, null);
/*      */     }
/*      */     
/*  655 */     if (this.name.startsWith("SYSTEM.JMS.D.")) {
/*  656 */       if (Trace.isOn) {
/*  657 */         Trace.traceData(this, "durable set to TRUE", null);
/*      */       }
/*  659 */       this.durable = true;
/*      */     } else {
/*      */       
/*  662 */       if (Trace.isOn) {
/*  663 */         Trace.traceData(this, "durable set to FALSE", null);
/*      */       }
/*  665 */       this.durable = false;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/*  670 */       setMessageSelector(selectorString);
/*      */     
/*      */     }
/*  673 */     catch (JMSException je) {
/*  674 */       if (Trace.isOn) {
/*  675 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "<init>(WMQDestination,MQSession,MQSubscription,boolean,int)", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  682 */       this.session = null;
/*  683 */       this.queue = null;
/*      */       
/*  685 */       if (Trace.isOn) {
/*  686 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "<init>(WMQDestination,MQSession,MQSubscription,boolean,int)", (Throwable)je, 1);
/*      */       }
/*      */       
/*  689 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/*  693 */     if (selectorString == null || selectorString.length() == 0) {
/*  694 */       this.selectorSet = false;
/*      */     } else {
/*      */       
/*  697 */       this.selectorSet = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  704 */       int[] selectors = { 22, 20 };
/*  705 */       int[] intAttrs = new int[2];
/*  706 */       this.queue.inquire(selectors, intAttrs, (byte[])null);
/*  707 */       String borq = this.queue.getAttributeString(2019, 48).trim();
/*      */       
/*  709 */       int thresh = intAttrs[0];
/*      */ 
/*      */ 
/*      */       
/*  713 */       this.backoutThreshold = thresh;
/*      */       
/*  715 */       this.type = intAttrs[1];
/*      */       
/*  717 */       if (Trace.isOn) {
/*  718 */         Trace.traceData(this, "returned from inquire, threshold = " + thresh + ", borq = '" + borq + "'", null);
/*      */       }
/*      */       
/*  721 */       if (thresh != 0) {
/*  722 */         this.maxAsyncAttempts = thresh;
/*      */       }
/*  724 */       if (borq.length() != 0) {
/*  725 */         this.backoutRetryQueue = borq;
/*      */       } else {
/*      */         
/*  728 */         this.backoutRetryQueue = session.getDLQName();
/*      */       }
/*      */     
/*  731 */     } catch (Exception e) {
/*  732 */       if (Trace.isOn) {
/*  733 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "<init>(WMQDestination,MQSession,MQSubscription,boolean,int)", e, 2);
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
/*  745 */     this.gmo = new MQGetMessageOptions(true);
/*      */     
/*  747 */     if (this.shared_queue) {
/*      */       
/*  749 */       this.gmo.matchOptions = 2;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  754 */       this.gmo.matchOptions = 0;
/*      */     } 
/*      */     
/*  757 */     this.getOptions = this.gmo.options;
/*      */     
/*  759 */     this.browseOptions |= 0x10;
/*  760 */     this.acceptOptions |= 0x140;
/*  761 */     this.deleteOptions |= 0x144;
/*      */ 
/*      */     
/*  764 */     if (topicSpec.getIntProperty("failIfQuiesce") == 1) {
/*  765 */       this.gmo.options |= 0x2000;
/*  766 */       this.browseOptions |= 0x2000;
/*  767 */       this.acceptOptions |= 0x2000;
/*  768 */       this.deleteOptions |= 0x2000;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  774 */     setSyncpointBehaviour(false);
/*      */ 
/*      */     
/*  777 */     this.baseMessage = new MQJMSMessage();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  785 */     if ((this.noLocal || this.selectorSet) && session.getSparseSubscriptions() && this.syncPoint != 3) {
/*  786 */       if (Trace.isOn) {
/*  787 */         Trace.traceData(this, "browse mode", null);
/*      */       }
/*  789 */       this.useBrowse = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  798 */     if (topicSpec.getIntProperty("deliveryMode") == 3) {
/*  799 */       MQQueue subQ = subscription.getSubscriberQueue();
/*  800 */       if (subQ == null || !subQ.isNPMClassHigh()) {
/*      */         
/*  802 */         this.session = null;
/*  803 */         this.queue = null;
/*      */         
/*  805 */         if (subscription.isDurable()) {
/*  806 */           session.connection.getSubscriptionEngine().closeDurableSubscription(subscription);
/*      */         } else {
/*      */           
/*  809 */           session.connection.getSubscriptionEngine().closeSubscription(subscription);
/*      */         } 
/*      */         
/*  812 */         String key = "MQJMS0003";
/*  813 */         String msg = ConfigEnvironment.getErrorMessage(key);
/*  814 */         InvalidDestinationException je = new InvalidDestinationException(msg, key);
/*  815 */         if (Trace.isOn) {
/*  816 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "<init>(WMQDestination,MQSession,MQSubscription,boolean,int)", (Throwable)je, 2);
/*      */         }
/*      */         
/*  819 */         throw je;
/*      */       } 
/*      */     } 
/*      */     
/*  823 */     if (Trace.isOn) {
/*  824 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "<init>(WMQDestination,MQSession,MQSubscription,boolean,int)");
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
/*      */   private void backoutRequeue(MQJMSMessage msg) throws JMSException {
/*  851 */     if (Trace.isOn) {
/*  852 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "backoutRequeue(MQJMSMessage)", new Object[] { msg });
/*      */     }
/*      */ 
/*      */     
/*  856 */     MQQueue destQ = null;
/*      */     
/*      */     try {
/*  859 */       if (this.backoutRetryQueue == null || this.backoutRetryQueue.length() == 0) {
/*      */ 
/*      */         
/*  862 */         if (this.type == 0) {
/*  863 */           this.type = Utils.inquireInt((MQManagedObject)this.queue, 20);
/*      */         }
/*      */         
/*  866 */         if (this.type == 3) {
/*  867 */           if (Trace.isOn) {
/*  868 */             Trace.traceData(this, "Queue " + this.queue.name + "is an ALIAS queue.", null);
/*      */           }
/*      */ 
/*      */           
/*  872 */           if (this.baseQueueName == null || this.baseQueueName.length() == 0) {
/*  873 */             this.baseQueueName = Utils.inquireString((MQManagedObject)this.queue, 2002);
/*      */           }
/*  875 */           if (Trace.isOn) {
/*  876 */             Trace.traceData(this, "Attempting to read Backout Requeue Queue from underlying LOCAL queue " + this.baseQueueName, null);
/*      */           }
/*      */ 
/*      */           
/*      */           try {
/*  881 */             int options = 8232;
/*      */ 
/*      */             
/*  884 */             MQQueue baseMqQueue = this.session.getQM().accessQueue(this.baseQueueName, options);
/*      */ 
/*      */ 
/*      */             
/*  888 */             this.backoutRetryQueue = Utils.inquireString((MQManagedObject)baseMqQueue, 2019);
/*      */             
/*  890 */             baseMqQueue.close();
/*      */           }
/*  892 */           catch (MQException mqe) {
/*  893 */             if (Trace.isOn) {
/*  894 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "backoutRequeue(MQJMSMessage)", (Throwable)mqe, 1);
/*      */             }
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  901 */           this.backoutRetryQueue = Utils.inquireString((MQManagedObject)this.queue, 2019);
/*      */         } 
/*      */       } 
/*      */       
/*  905 */       if (this.backoutRetryQueue == null || this.backoutRetryQueue.length() == 0) {
/*  906 */         JMSException jmse = ConfigEnvironment.newException("MQJMS1080");
/*  907 */         if (Trace.isOn) {
/*  908 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "backoutRequeue(MQJMSMessage)", (Throwable)jmse, 1);
/*      */         }
/*      */         
/*  911 */         throw jmse;
/*      */       } 
/*      */       
/*  914 */       this.backoutRetryQueue = this.backoutRetryQueue.trim();
/*      */ 
/*      */       
/*  917 */       if (Trace.isOn) {
/*  918 */         Trace.traceData(this, "BORQ = <" + this.backoutRetryQueue + ">", null);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  924 */         int openOptions = 8720;
/*      */         
/*  926 */         destQ = this.session.getQM().accessQueue(this.backoutRetryQueue, openOptions, "", null, null);
/*      */ 
/*      */         
/*  929 */         MQPutMessageOptions pmo = new MQPutMessageOptions();
/*  930 */         pmo.options = 514;
/*  931 */         pmo.contextReference = this.queue;
/*      */         
/*  933 */         destQ.putMsg2(msg, pmo);
/*      */         
/*  935 */         if (this.session.getTransacted() || this.session.getAcknowledgeMode() == 2) {
/*      */ 
/*      */ 
/*      */           
/*  939 */           if (Trace.isOn) {
/*  940 */             Trace.traceData(this, "Unable to commit requeued message as session is transacted or Client Ack", null);
/*      */           }
/*      */         } else {
/*      */           
/*  944 */           if (Trace.isOn) {
/*  945 */             Trace.traceData(this, "commiting requeued message", null);
/*      */           }
/*  947 */           this.session.getQM().commit();
/*      */         }
/*      */       
/*      */       }
/*  951 */       catch (MQException mqe) {
/*  952 */         if (Trace.isOn) {
/*  953 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "backoutRequeue(MQJMSMessage)", (Throwable)mqe, 2);
/*      */         }
/*      */         
/*  956 */         if (mqe.reasonCode == 2033) {
/*      */           
/*  958 */           if (Trace.isOn) {
/*  959 */             Trace.traceData(this, "ProviderMessage to requeue has disappeared! Continue as normal", null);
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  968 */         else if (Trace.isOn) {
/*  969 */           Trace.traceData(this, "Unable to requeue message", null);
/*      */         } 
/*      */ 
/*      */         
/*  973 */         JMSException jmse = ConfigEnvironment.newException("MQJMS1081");
/*  974 */         jmse.setLinkedException((Exception)mqe);
/*  975 */         if (Trace.isOn) {
/*  976 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "backoutRequeue(MQJMSMessage)", (Throwable)jmse, 2);
/*      */         }
/*      */         
/*  979 */         throw jmse;
/*      */       }
/*      */     
/*      */     }
/*  983 */     catch (JMSException je) {
/*  984 */       if (Trace.isOn) {
/*  985 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "backoutRequeue(MQJMSMessage)", (Throwable)je, 3);
/*      */       }
/*      */       
/*  988 */       if (Trace.isOn) {
/*  989 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "backoutRequeue(MQJMSMessage)", (Throwable)je, 3);
/*      */       }
/*      */       
/*  992 */       throw je;
/*      */     } finally {
/*      */       
/*  995 */       if (Trace.isOn) {
/*  996 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "backoutRequeue(MQJMSMessage)");
/*      */       }
/*      */       
/*      */       try {
/* 1000 */         if (destQ != null) {
/* 1001 */           destQ.close();
/*      */         }
/*      */       }
/* 1004 */       catch (MQException mqe) {
/* 1005 */         if (Trace.isOn) {
/* 1006 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "backoutRequeue(MQJMSMessage)", (Throwable)mqe, 4);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1012 */     if (Trace.isOn) {
/* 1013 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "backoutRequeue(MQJMSMessage)");
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
/*      */   public void close(boolean closingFromSession, ReentrantLock ignored) throws JMSException {
/* 1038 */     if (Trace.isOn)
/* 1039 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "close(boolean,ReentrantLock)", new Object[] {
/* 1040 */             Boolean.valueOf(closingFromSession), ignored
/*      */           }); 
/* 1042 */     if (this.setForPTP) {
/* 1043 */       closeQ();
/*      */     }
/* 1045 */     else if (this.setForPubSub) {
/* 1046 */       closeT();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1051 */     if (Trace.isOn) {
/* 1052 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "close(boolean,ReentrantLock)");
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
/*      */   private void closeQ() throws JMSException {
/* 1066 */     if (Trace.isOn) {
/* 1067 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "closeQ()");
/*      */     }
/*      */     try {
/* 1070 */       this.closing = true;
/* 1071 */       synchronized (this) {
/* 1072 */         if (this.session != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1077 */           this.session.setPlayNice(true);
/*      */ 
/*      */           
/* 1080 */           this.session.removeConsumer(this);
/*      */         } 
/*      */ 
/*      */         
/* 1084 */         if (this.listener != null) {
/* 1085 */           if (this.session != null) {
/* 1086 */             this.session.removeAsync(this);
/*      */           }
/* 1088 */           this.listener = null;
/*      */         } 
/*      */ 
/*      */         
/* 1092 */         this.closed = true;
/*      */         
/* 1094 */         if (this.queue != null) {
/*      */           
/*      */           try {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1101 */             this.queue.close();
/* 1102 */             this.queue = null;
/*      */           }
/* 1104 */           catch (MQException e) {
/* 1105 */             if (Trace.isOn) {
/* 1106 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "closeQ()", (Throwable)e, 1);
/*      */             }
/*      */             
/* 1109 */             JMSException je = ConfigEnvironment.newException("MQJMS2000");
/* 1110 */             je.setLinkedException((Exception)e);
/* 1111 */             if (Trace.isOn) {
/* 1112 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "closeQ()", (Throwable)je, 1);
/*      */             }
/*      */             
/* 1115 */             throw je;
/*      */           } 
/*      */         }
/*      */         
/* 1119 */         if (this.session != null) {
/* 1120 */           this.session.setPlayNice(false);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1125 */         if (Trace.isOn) {
/* 1126 */           Trace.traceData(this, "Leaving the session object in the Consumer", null);
/*      */         }
/*      */       }
/*      */     
/*      */     }
/* 1131 */     catch (JMSException je) {
/* 1132 */       if (Trace.isOn) {
/* 1133 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "closeQ()", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1136 */       if (Trace.isOn) {
/* 1137 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "closeQ()", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1140 */       throw je;
/*      */     } finally {
/*      */       
/* 1143 */       if (Trace.isOn) {
/* 1144 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "closeQ()");
/*      */       }
/*      */ 
/*      */       
/* 1148 */       if (this.session != null) {
/* 1149 */         this.session.setPlayNice(false);
/*      */       }
/*      */     } 
/* 1152 */     if (Trace.isOn) {
/* 1153 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "closeQ()");
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
/*      */   private void closeT() throws JMSException {
/* 1178 */     if (Trace.isOn) {
/* 1179 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "closeT()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1184 */     boolean removeSubscriber = true;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1189 */     boolean subscriptionRemovedFromList = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1196 */       if (this.session != null) {
/* 1197 */         this.session.setPlayNice(true);
/*      */       }
/* 1199 */       this.closing = true;
/* 1200 */       synchronized (this)
/*      */       {
/* 1202 */         if (this.listener != null) {
/* 1203 */           if (this.session != null) {
/* 1204 */             this.session.removeAsync(this);
/*      */           }
/* 1206 */           this.listener = null;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1211 */         this.closed = true;
/*      */         try {
/* 1213 */           if (null != this.session && null != this.session.getQM() && 
/* 1214 */             this.session.getQM().spiSupportsInherited() && (this.session
/* 1215 */             .getAcknowledgeMode() == 1 || this.session.getAcknowledgeMode() == 3))
/*      */           {
/*      */             
/* 1218 */             this.session._acknowledgeInternal();
/*      */           
/*      */           }
/*      */         }
/* 1222 */         catch (MQException mqe) {
/* 1223 */           if (Trace.isOn) {
/* 1224 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "closeT()", (Throwable)mqe, 1);
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1232 */         if (!this.durable) {
/*      */           
/*      */           try {
/* 1235 */             this.subscription.close();
/* 1236 */             this.queue = null;
/*      */           }
/* 1238 */           catch (JMSException je) {
/* 1239 */             if (Trace.isOn) {
/* 1240 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "closeT()", (Throwable)je, 2);
/*      */             }
/*      */             
/* 1243 */             Exception e = je.getLinkedException();
/* 1244 */             if (e instanceof MQException && ((MQException)e).reasonCode == 2055) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1251 */               removeSubscriber = false;
/* 1252 */               if (Trace.isOn) {
/* 1253 */                 Trace.traceData(this, "close ignoring MQException 2055", null);
/*      */               }
/*      */             } else {
/*      */               
/* 1257 */               if (Trace.isOn) {
/* 1258 */                 Trace.traceData(this, "close() ignoring exception", null);
/*      */               }
/* 1260 */               this.queue = null;
/*      */             }
/*      */           
/*      */           } 
/* 1264 */         } else if (this.queue != null) {
/*      */ 
/*      */           
/*      */           try {
/*      */ 
/*      */ 
/*      */             
/* 1271 */             this.subscription.close();
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1276 */             subscriptionRemovedFromList = true;
/*      */             
/* 1278 */             this.queue.close();
/* 1279 */             this.queue = null;
/*      */           }
/* 1281 */           catch (MQException e) {
/* 1282 */             if (Trace.isOn) {
/* 1283 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "closeT()", (Throwable)e, 3);
/*      */             }
/*      */             
/* 1286 */             JMSException je = ConfigEnvironment.newException("MQJMS3017", this.subscription.getQueueName());
/* 1287 */             je.setLinkedException((Exception)e);
/* 1288 */             if (Trace.isOn) {
/* 1289 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "closeT()", (Throwable)je, 1);
/*      */             }
/*      */             
/* 1292 */             throw je;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 1297 */         if (this.session != null && removeSubscriber) {
/* 1298 */           this.session.removeConsumer(this);
/*      */         }
/*      */ 
/*      */         
/* 1302 */         if (this.session != null) {
/* 1303 */           this.session.setPlayNice(false);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1308 */         if (Trace.isOn) {
/* 1309 */           Trace.traceData(this, "Leaving the session object in the Consumer", null);
/*      */         }
/*      */       }
/*      */     
/*      */     }
/* 1314 */     catch (JMSException je) {
/* 1315 */       if (Trace.isOn) {
/* 1316 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "closeT()", (Throwable)je, 4);
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
/* 1337 */       if (this.subscription.isDurable())
/*      */       {
/*      */         
/* 1340 */         if (!subscriptionRemovedFromList) {
/*      */           
/* 1342 */           Exception linkedException = je.getLinkedException();
/* 1343 */           if (Trace.isOn) {
/* 1344 */             Trace.traceData(this, "JMSException thrown while closing a durable subscription", null);
/* 1345 */             Trace.traceData(this, "Linked Exception:", linkedException);
/*      */           } 
/*      */ 
/*      */           
/* 1349 */           if (linkedException != null && 
/* 1350 */             linkedException instanceof MQException) {
/* 1351 */             int reasonCode = ((MQException)linkedException).getReason();
/* 1352 */             if (Reason.isConnectionBroken(reasonCode)) {
/*      */               
/*      */               try {
/*      */                 
/* 1356 */                 if (Trace.isOn) {
/* 1357 */                   Trace.traceData(this, "The JMSException was thrown because of a broken connection. Checking if the subscription needs to be removed from the subscription list", null);
/*      */                 }
/*      */ 
/*      */ 
/*      */                 
/* 1362 */                 this.subscription.remove();
/*      */               }
/* 1364 */               catch (JMSException jmsEx) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1370 */                 if (Trace.isOn) {
/* 1371 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "closeT()", (Throwable)jmsEx, 5);
/*      */                 }
/*      */               } 
/*      */             }
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1380 */           subscriptionRemovedFromList = true;
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 1385 */       if (Trace.isOn) {
/* 1386 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "closeT()", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1389 */       throw je;
/*      */     }
/*      */     finally {
/*      */       
/* 1393 */       if (Trace.isOn) {
/* 1394 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "closeT()");
/*      */       }
/*      */ 
/*      */       
/* 1398 */       if (this.session != null) {
/* 1399 */         this.session.setPlayNice(false);
/*      */       }
/*      */     } 
/* 1402 */     if (Trace.isOn) {
/* 1403 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "closeT()");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void commitIfRequired(boolean messageWanted, boolean calledFromgetMessage) throws JMSException {
/* 1484 */     if (Trace.isOn)
/* 1485 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "commitIfRequired(boolean,boolean)", new Object[] {
/* 1486 */             Boolean.valueOf(messageWanted), 
/* 1487 */             Boolean.valueOf(calledFromgetMessage)
/*      */           }); 
/* 1489 */     if (Trace.isOn) {
/* 1490 */       Trace.traceData(this, "Message is" + (messageWanted ? " " : " not ") + "wanted", null);
/*      */       
/* 1492 */       Trace.traceData(this, "commitIfRequired is called from" + (calledFromgetMessage ? " getMessage " : " receiveAsync ") + "method", null);
/*      */       
/* 1494 */       String tmp = "Not Applicable";
/* 1495 */       String tmp1 = "false";
/* 1496 */       switch (this.acknowledgeMode) {
/*      */         case 1:
/* 1498 */           tmp = "Session.AUTO_ACKNOWLEDGE";
/*      */           break;
/*      */         case 3:
/* 1501 */           tmp = "Session.DUPS_OK_ACKNOWLEDGE";
/*      */           break;
/*      */         case 2:
/* 1504 */           tmp = "Session.CLIENT_ACKNOWLEDGE";
/*      */           break;
/*      */         case 0:
/* 1507 */           tmp1 = "true";
/*      */           break;
/*      */         default:
/* 1510 */           tmp = "Invalid";
/* 1511 */           tmp1 = "false";
/*      */           break;
/*      */       } 
/* 1514 */       Trace.traceData(this, "Session.acknowledgeMode = " + tmp + " Transacted = " + tmp1, null);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1520 */     if ((this.session.usingAsyncMode() || this.session.hasMessageListener()) && calledFromgetMessage) {
/* 1521 */       if (this.setForPubSub) {
/* 1522 */         if (Trace.isOn) {
/* 1523 */           Trace.traceData(this, "We are in PubSub async delivery mode", null);
/*      */         }
/* 1525 */         this.session.setCommitRequired(true);
/*      */       } else {
/*      */         
/* 1528 */         if (Trace.isOn) {
/* 1529 */           Trace.traceData(this, "We are in PTP async delivery mode", null);
/*      */         }
/* 1531 */         if (messageWanted) {
/* 1532 */           this.session.setCommitRequired(true);
/*      */         } else {
/*      */           
/* 1535 */           if (!this.session.getCommitRequired() && this.session.transacted) {
/* 1536 */             if (Trace.isOn) {
/* 1537 */               Trace.traceData(this, "Message unwanted and commit not required, committing.", null);
/*      */             }
/* 1539 */             this.session.commit();
/*      */           } 
/* 1541 */           this.session.setCommitRequired(false);
/*      */         } 
/*      */       } 
/* 1544 */       if (Trace.isOn) {
/* 1545 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "commitIfRequired(boolean,boolean)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 1554 */       if (Trace.isOn) {
/* 1555 */         Trace.traceData(this, "ProviderSession.acknowledgeMode = " + this.acknowledgeMode, null);
/*      */       }
/*      */       
/* 1558 */       if (this.session.supportsInherited() && this.setForPubSub) {
/* 1559 */         JMSException je; if (Trace.isOn) {
/* 1560 */           Trace.traceData(this, "QueueManager supports ITX calls", null);
/*      */         }
/*      */         
/* 1563 */         switch (this.acknowledgeMode) {
/*      */ 
/*      */           
/*      */           case 1:
/*      */           case 3:
/* 1568 */             if (messageWanted && !this.session.usingAsyncMode() && !this.session.hasMessageListener() && this.session.getProcessDuration() == 0) {
/*      */ 
/*      */               
/* 1571 */               if (this.syncPoint == 2) {
/*      */                 
/* 1573 */                 if (this.baseMessage.getPersistence() == 1) {
/* 1574 */                   this.session._acknowledgeInternal();
/*      */                 }
/*      */               }
/* 1577 */               else if (this.syncPoint == 1) {
/* 1578 */                 this.session._acknowledgeInternal();
/*      */               } 
/* 1580 */               this.session.setCommitRequired(false);
/*      */               break;
/*      */             } 
/* 1583 */             this.session.setCommitRequired(true);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 0:
/*      */           case 2:
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/* 1597 */             je = ConfigEnvironment.newException("MQJMS1001", 
/* 1598 */                 Integer.valueOf(this.acknowledgeMode));
/* 1599 */             if (Trace.isOn) {
/* 1600 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "commitIfRequired(boolean,boolean)", (Throwable)je, 1);
/*      */             }
/*      */ 
/*      */             
/* 1604 */             throw je;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1610 */       } else if (this.session.getDistTransactionMode() == 0 || !this.session.getTransacted()) {
/*      */         JMSException je;
/* 1612 */         if (Trace.isOn) {
/* 1613 */           Trace.traceData(this, "QueueManager doesn't support ITX calls or we're in PTP mode", null);
/*      */         }
/*      */         
/* 1616 */         switch (this.acknowledgeMode) {
/*      */ 
/*      */           
/*      */           case 1:
/*      */           case 3:
/* 1621 */             if (this.syncPoint == 2) {
/*      */               
/* 1623 */               if (this.baseMessage.getPersistence() == 1) {
/* 1624 */                 this.session._acknowledgeInternal();
/*      */               }
/*      */             }
/* 1627 */             else if (this.syncPoint == 1) {
/* 1628 */               this.session._acknowledgeInternal();
/*      */             } 
/* 1630 */             this.session.setCommitRequired(false);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 0:
/*      */           case 2:
/* 1645 */             if (!messageWanted && !this.session.getCommitRequired()) {
/* 1646 */               if (this.acknowledgeMode == 0) {
/* 1647 */                 this.session.commit();
/*      */               } else {
/*      */                 
/* 1650 */                 this.session._acknowledgeInternal();
/*      */               } 
/*      */ 
/*      */               
/* 1654 */               this.session.setCommitRequired(false);
/*      */ 
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/* 1663 */             this.session.setCommitRequired(true);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/* 1670 */             je = ConfigEnvironment.newException("MQJMS1001", 
/* 1671 */                 Integer.valueOf(this.acknowledgeMode));
/* 1672 */             if (Trace.isOn) {
/* 1673 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "commitIfRequired(boolean,boolean)", (Throwable)je, 2);
/*      */             }
/*      */ 
/*      */             
/* 1677 */             throw je;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1685 */       } else if (Trace.isOn) {
/* 1686 */         Trace.traceData(this, "ProviderSession.getDistTransactionMode = " + this.session.getDistTransactionMode(), null);
/* 1687 */         Trace.traceData(this, "In a possible global transactional mode. Skipping any commit or acknowledge attempts.", null);
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1692 */     catch (JMSException je) {
/* 1693 */       if (Trace.isOn) {
/* 1694 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "commitIfRequired(boolean,boolean)", (Throwable)je);
/*      */       }
/*      */       
/* 1697 */       if (Trace.isOn) {
/* 1698 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "commitIfRequired(boolean,boolean)", (Throwable)je, 3);
/*      */       }
/*      */       
/* 1701 */       throw je;
/*      */     } 
/* 1703 */     if (Trace.isOn) {
/* 1704 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "commitIfRequired(boolean,boolean)", 2);
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
/*      */   private void deadLetter(MQJMSMessage msg, int reason) throws JMSException {
/* 1720 */     if (Trace.isOn) {
/* 1721 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "deadLetter(MQJMSMessage,int)", new Object[] { msg, 
/* 1722 */             Integer.valueOf(reason) });
/*      */     }
/*      */     
/* 1725 */     MQMessage reportMessage = null;
/* 1726 */     String replyQ = null;
/* 1727 */     String replyQMgr = null;
/* 1728 */     MQQueue DLQ = null;
/* 1729 */     MQQueue RQ = null;
/*      */ 
/*      */     
/*      */     try {
/* 1733 */       if (this.mqDLQName == null) {
/* 1734 */         this.mqDLQName = Utils.inquireString((MQManagedObject)this.session.getQM(), 2006);
/*      */       }
/* 1736 */       if (Trace.isOn) {
/* 1737 */         Trace.traceData(this, "DLQ = <" + this.mqDLQName + ">", null);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1742 */       if (this.mqDLQName == null || this.mqDLQName.length() == 0) {
/* 1743 */         if (Trace.isOn) {
/* 1744 */           Trace.traceData(this, "Unable to find DLQ name - is one defined?", null);
/*      */         }
/* 1746 */         JMSException je = ConfigEnvironment.newException("MQJMS1079");
/* 1747 */         if (Trace.isOn) {
/* 1748 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "deadLetter(MQJMSMessage,int)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 1751 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 1755 */       if ((msg.getReport() & 0x7000000) != 0) {
/*      */         
/* 1757 */         reportMessage = generateReport(msg, reason);
/* 1758 */         replyQ = msg.getReplyToQueueName().trim();
/* 1759 */         replyQMgr = msg.getReplyToQueueManagerName().trim();
/*      */       } 
/*      */ 
/*      */       
/* 1763 */       if (Trace.isOn) {
/* 1764 */         Trace.traceData(this, "Constructing DLH", null);
/*      */       }
/*      */       
/* 1767 */       DLH dlh = new DLH();
/* 1768 */       dlh.reason = reason;
/* 1769 */       if (this.backoutRetryQueue == null || this.backoutRetryQueue.length() == 0) {
/*      */         
/* 1771 */         dlh.destQName = this.queue.name;
/*      */       }
/*      */       else {
/*      */         
/* 1775 */         dlh.destQName = this.backoutRetryQueue;
/*      */       } 
/*      */       
/* 1778 */       dlh.destQMgrName = this.session.getQMName();
/* 1779 */       dlh.putApplType = 28;
/* 1780 */       dlh.putApplName = "MQ JMS ConnectionConsumer";
/*      */ 
/*      */       
/* 1783 */       GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
/* 1784 */       dlh.putDate = Utils.getDate(gc);
/* 1785 */       dlh.putTime = Utils.getTime(gc);
/* 1786 */       MQMessage deadMsg = new MQMessage();
/*      */ 
/*      */ 
/*      */       
/* 1790 */       dlh.write(deadMsg, msg);
/*      */ 
/*      */ 
/*      */       
/* 1794 */       int openOptions = 8720;
/* 1795 */       DLQ = this.session.getQM().accessQueue(this.mqDLQName, openOptions, this.session.getQMName(), null, null);
/*      */       
/* 1797 */       MQPutMessageOptions pmo = new MQPutMessageOptions();
/* 1798 */       pmo.options = 514;
/* 1799 */       pmo.contextReference = this.queue;
/* 1800 */       DLQ.put(deadMsg, pmo);
/*      */       
/* 1802 */       if (reportMessage != null) {
/*      */         
/*      */         try {
/* 1805 */           openOptions = 8720;
/* 1806 */           RQ = this.session.getQM().accessQueue(replyQ, openOptions, replyQMgr, null, null);
/*      */ 
/*      */           
/* 1809 */           pmo = new MQPutMessageOptions();
/* 1810 */           pmo.options = 514;
/* 1811 */           pmo.contextReference = this.queue;
/* 1812 */           RQ.put(reportMessage, pmo);
/*      */         
/*      */         }
/* 1815 */         catch (MQException mqe) {
/* 1816 */           if (Trace.isOn) {
/* 1817 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "deadLetter(MQJMSMessage,int)", (Throwable)mqe, 1);
/*      */           }
/*      */ 
/*      */           
/* 1821 */           dlh.destQName = replyQ;
/* 1822 */           dlh.destQMgrName = replyQMgr;
/* 1823 */           dlh.reason = mqe.reasonCode;
/* 1824 */           dlh.write(reportMessage);
/*      */           
/* 1826 */           DLQ.put(reportMessage, pmo);
/*      */         }
/*      */       
/*      */       }
/* 1830 */     } catch (MQException mqe) {
/* 1831 */       if (Trace.isOn) {
/* 1832 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "deadLetter(MQJMSMessage,int)", (Throwable)mqe, 2);
/*      */       }
/*      */ 
/*      */       
/* 1836 */       if (mqe.reasonCode == 2033) {
/*      */         
/* 1838 */         if (Trace.isOn) {
/* 1839 */           Trace.traceData(this, "ProviderMessage to dead-letter has disappeared! Continue as normal", null);
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/* 1846 */       else if (msg != null && msg.getPersistence() == 0) {
/* 1847 */         if (Trace.isOn) {
/* 1848 */           Trace.traceData(this, "Could not send report message, but message isnon-persistent - so subsume the error", null);
/*      */         }
/*      */       } else {
/*      */ 
/*      */         
/*      */         try {
/* 1854 */           if (Trace.isOn) {
/* 1855 */             Trace.traceData(this, "backing out attempt to send Report message", null);
/*      */           }
/* 1857 */           if (this.session.getTransacted() || this.session.getAcknowledgeMode() == 2) {
/*      */ 
/*      */ 
/*      */             
/* 1861 */             if (Trace.isOn) {
/* 1862 */               Trace.traceData(this, "Unable to backout Report message as session is transacted or Client Ack", null);
/*      */             }
/*      */           } else {
/*      */             
/* 1866 */             if (Trace.isOn) {
/* 1867 */               Trace.traceData(this, "backing out Report message", null);
/*      */             }
/* 1869 */             this.session.getQM().backout();
/*      */           }
/*      */         
/* 1872 */         } catch (MQException mqe2) {
/* 1873 */           if (Trace.isOn) {
/* 1874 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "deadLetter(MQJMSMessage,int)", (Throwable)mqe2, 3);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1879 */         JMSException je = ConfigEnvironment.newException("MQJMS1079");
/* 1880 */         je.setLinkedException((Exception)mqe);
/*      */         
/* 1882 */         if (Trace.isOn) {
/* 1883 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "deadLetter(MQJMSMessage,int)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 1886 */         throw je;
/*      */       }
/*      */     
/* 1889 */     } catch (JMSException je) {
/* 1890 */       if (Trace.isOn) {
/* 1891 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "deadLetter(MQJMSMessage,int)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */       
/* 1895 */       if (msg != null && msg.getPersistence() == 0) {
/* 1896 */         if (Trace.isOn) {
/* 1897 */           Trace.traceData(this, "ProviderMessage is non-persistent so ignoring error", null);
/*      */         }
/*      */       } else {
/*      */         
/*      */         try {
/* 1902 */           if (Trace.isOn) {
/* 1903 */             Trace.traceData(this, "backing out attempt to DLQ message", null);
/*      */           }
/* 1905 */           if (this.session.getTransacted() || this.session.getAcknowledgeMode() == 2) {
/*      */ 
/*      */ 
/*      */             
/* 1909 */             if (Trace.isOn) {
/* 1910 */               Trace.traceData(this, "Unable to backout DLQ'd message as session is transacted or Client Ack", null);
/*      */             }
/*      */           } else {
/*      */             
/* 1914 */             if (Trace.isOn) {
/* 1915 */               Trace.traceData(this, "backing out DLQ'd message", null);
/*      */             }
/* 1917 */             this.session.getQM().backout();
/*      */           }
/*      */         
/* 1920 */         } catch (MQException mqe) {
/* 1921 */           if (Trace.isOn) {
/* 1922 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "deadLetter(MQJMSMessage,int)", (Throwable)mqe, 5);
/*      */           }
/*      */         } 
/*      */         
/* 1926 */         if (Trace.isOn) {
/* 1927 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "deadLetter(MQJMSMessage,int)", (Throwable)je, 3);
/*      */         }
/*      */         
/* 1930 */         throw je;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 1936 */       if (DLQ != null) {
/* 1937 */         DLQ.close();
/*      */       }
/* 1939 */       if (RQ != null) {
/* 1940 */         RQ.close();
/*      */       }
/* 1942 */       if (this.session.getTransacted() || this.session.getAcknowledgeMode() == 2) {
/*      */ 
/*      */ 
/*      */         
/* 1946 */         if (Trace.isOn) {
/* 1947 */           Trace.traceData(this, "Unable to commit requeued message as session is transacted or Client Ack", null);
/*      */         }
/*      */       } else {
/*      */         
/* 1951 */         if (Trace.isOn) {
/* 1952 */           Trace.traceData(this, "commiting requeued message", null);
/*      */         }
/* 1954 */         this.session.getQM().commit();
/*      */       }
/*      */     
/* 1957 */     } catch (MQException mqe) {
/* 1958 */       if (Trace.isOn) {
/* 1959 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "deadLetter(MQJMSMessage,int)", (Throwable)mqe, 6);
/*      */       }
/*      */ 
/*      */       
/* 1963 */       JMSException je = ConfigEnvironment.newException("MQJMS1079");
/* 1964 */       je.setLinkedException((Exception)mqe);
/* 1965 */       if (Trace.isOn) {
/* 1966 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "deadLetter(MQJMSMessage,int)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 1969 */       throw je;
/*      */     } 
/* 1971 */     if (Trace.isOn) {
/* 1972 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "deadLetter(MQJMSMessage,int)");
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
/*      */   private void discard(MQJMSMessage msgRef, int reason) throws JMSException {
/* 1989 */     if (Trace.isOn) {
/* 1990 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "discard(MQJMSMessage,int)", new Object[] { msgRef, 
/* 1991 */             Integer.valueOf(reason) });
/*      */     }
/*      */     
/* 1994 */     MQJMSMessage discardMessage = msgRef;
/* 1995 */     MQMessage reportMessage = null;
/*      */     
/*      */     try {
/* 1998 */       if ((discardMessage.getReport() & 0x7000000) != 0)
/*      */       {
/* 2000 */         reportMessage = generateReport(discardMessage, reason);
/*      */         
/* 2002 */         String replyQ = discardMessage.getReplyToQueueName().trim();
/* 2003 */         String replyQMgr = discardMessage.getReplyToQueueManagerName().trim();
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 2008 */           int openOptions = 8720;
/* 2009 */           MQQueue RQ = this.session.getQM().accessQueue(replyQ, openOptions, replyQMgr, null, null);
/*      */ 
/*      */           
/* 2012 */           MQPutMessageOptions pmo = new MQPutMessageOptions();
/* 2013 */           pmo.options = 514;
/* 2014 */           pmo.contextReference = this.queue;
/* 2015 */           RQ.put(reportMessage, pmo);
/* 2016 */           RQ.close();
/*      */         }
/* 2018 */         catch (MQException mqe) {
/* 2019 */           if (Trace.isOn) {
/* 2020 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "discard(MQJMSMessage,int)", (Throwable)mqe, 1);
/*      */           }
/*      */           
/* 2023 */           if (Trace.isOn) {
/* 2024 */             Trace.traceData(this, "Could not put report message to replyToQ", null);
/* 2025 */             Trace.traceData(this, "Attempting to put to DLQ instead", null);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2032 */           if (this.mqDLQName == null) {
/* 2033 */             this.mqDLQName = Utils.inquireString((MQManagedObject)this.session.getQM(), 2006);
/*      */           }
/* 2035 */           if (Trace.isOn) {
/* 2036 */             Trace.traceData(this, "DLQ = <" + this.mqDLQName + ">", null);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 2041 */           if (this.mqDLQName == null || this.mqDLQName.length() == 0) {
/* 2042 */             if (Trace.isOn) {
/* 2043 */               Trace.traceData(this, "Unable to find DLQ name - is one defined?", null);
/*      */             }
/* 2045 */             JMSException je = ConfigEnvironment.newException("MQJMS1079");
/* 2046 */             if (Trace.isOn) {
/* 2047 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "discard(MQJMSMessage,int)", (Throwable)je, 1);
/*      */             }
/*      */             
/* 2050 */             throw je;
/*      */           } 
/*      */ 
/*      */           
/* 2054 */           DLH dlh = new DLH();
/* 2055 */           dlh.reason = mqe.reasonCode;
/* 2056 */           dlh.destQName = replyQ;
/* 2057 */           dlh.destQMgrName = replyQMgr;
/* 2058 */           dlh.putApplType = 28;
/* 2059 */           dlh.putApplName = "MQ JMS ConnectionConsumer";
/*      */ 
/*      */           
/* 2062 */           GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
/* 2063 */           dlh.putDate = Utils.getDate(gc);
/* 2064 */           dlh.putTime = Utils.getTime(gc);
/*      */ 
/*      */           
/* 2067 */           dlh.write(reportMessage);
/*      */ 
/*      */           
/* 2070 */           int openOptions = 8720;
/* 2071 */           MQQueue DLQ = this.session.getQM().accessQueue(this.mqDLQName, openOptions, this.session
/*      */               
/* 2073 */               .getQMName(), null, null);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2078 */           MQPutMessageOptions pmo = new MQPutMessageOptions();
/* 2079 */           pmo.options = 514;
/* 2080 */           pmo.contextReference = this.queue;
/* 2081 */           DLQ.put(reportMessage, pmo);
/* 2082 */           DLQ.close();
/*      */         }
/*      */       
/*      */       }
/*      */     
/* 2087 */     } catch (MQException mqe) {
/* 2088 */       if (Trace.isOn) {
/* 2089 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "discard(MQJMSMessage,int)", (Throwable)mqe, 2);
/*      */       }
/*      */ 
/*      */       
/* 2093 */       if (mqe.reasonCode == 2033) {
/*      */         
/* 2095 */         if (Trace.isOn) {
/* 2096 */           Trace.traceData(this, "ProviderMessage to discard has disappeared! Continue as normal", null);
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/* 2103 */       else if (discardMessage.getPersistence() == 0) {
/* 2104 */         if (Trace.isOn) {
/* 2105 */           Trace.traceData(this, "Could not send report message, but message isnon-persistent - so subsume the error", null);
/*      */         }
/*      */       } else {
/*      */ 
/*      */         
/*      */         try {
/*      */           
/* 2112 */           if (this.session.getTransacted() || this.session.getAcknowledgeMode() == 2) {
/*      */ 
/*      */ 
/*      */             
/* 2116 */             if (Trace.isOn) {
/* 2117 */               Trace.traceData(this, "Unable to backout the message as session is transacted or Client Ack", null);
/*      */             }
/*      */           } else {
/*      */             
/* 2121 */             if (Trace.isOn) {
/* 2122 */               Trace.traceData(this, "backing out the message", null);
/*      */             }
/* 2124 */             this.session.getQM().backout();
/*      */           }
/*      */         
/* 2127 */         } catch (MQException mqe2) {
/* 2128 */           if (Trace.isOn) {
/* 2129 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "discard(MQJMSMessage,int)", (Throwable)mqe2, 3);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 2134 */         JMSException je = ConfigEnvironment.newException("MQJMS1082");
/* 2135 */         je.setLinkedException((Exception)mqe);
/* 2136 */         if (Trace.isOn) {
/* 2137 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "discard(MQJMSMessage,int)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 2140 */         throw je;
/*      */       }
/*      */     
/* 2143 */     } catch (JMSException je) {
/* 2144 */       if (Trace.isOn) {
/* 2145 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "discard(MQJMSMessage,int)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */       
/* 2149 */       if (discardMessage.getPersistence() == 0) {
/* 2150 */         if (Trace.isOn) {
/* 2151 */           Trace.traceData(this, "ProviderMessage is non-persistent so ignoring error", null);
/*      */         }
/*      */       } else {
/*      */         
/*      */         try {
/* 2156 */           if (this.session.getTransacted() || this.session.getAcknowledgeMode() == 2) {
/*      */ 
/*      */ 
/*      */             
/* 2160 */             if (Trace.isOn) {
/* 2161 */               Trace.traceData(this, "Unable to backout the message as session is transacted or Client Ack", null);
/*      */             }
/*      */           } else {
/*      */             
/* 2165 */             if (Trace.isOn) {
/* 2166 */               Trace.traceData(this, "backing out the message", null);
/*      */             }
/* 2168 */             this.session.getQM().backout();
/*      */           }
/*      */         
/* 2171 */         } catch (MQException mqe) {
/* 2172 */           if (Trace.isOn) {
/* 2173 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "discard(MQJMSMessage,int)", (Throwable)mqe, 5);
/*      */           }
/*      */         } 
/*      */         
/* 2177 */         if (Trace.isOn) {
/* 2178 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "discard(MQJMSMessage,int)", (Throwable)je, 3);
/*      */         }
/*      */         
/* 2181 */         throw je;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 2187 */       if (this.session.getTransacted() || this.session.getAcknowledgeMode() == 2) {
/*      */ 
/*      */ 
/*      */         
/* 2191 */         if (Trace.isOn) {
/* 2192 */           Trace.traceData(this, "Unable to commit the report message as session is transacted or Client Ack", null);
/*      */         }
/*      */       } else {
/*      */         
/* 2196 */         if (Trace.isOn) {
/* 2197 */           Trace.traceData(this, "commiting the report message", null);
/*      */         }
/* 2199 */         this.session.getQM().commit();
/*      */       }
/*      */     
/*      */     }
/* 2203 */     catch (MQException mqe) {
/* 2204 */       if (Trace.isOn) {
/* 2205 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "discard(MQJMSMessage,int)", (Throwable)mqe, 6);
/*      */       }
/*      */ 
/*      */       
/* 2209 */       JMSException je = ConfigEnvironment.newException("MQJMS1082");
/* 2210 */       je.setLinkedException((Exception)mqe);
/* 2211 */       if (Trace.isOn) {
/* 2212 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "discard(MQJMSMessage,int)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 2215 */       throw je;
/*      */     } 
/* 2217 */     if (Trace.isOn) {
/* 2218 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "discard(MQJMSMessage,int)");
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
/*      */   private MQMessage generateReport(MQJMSMessage msg, int reason) throws JMSException {
/* 2237 */     if (Trace.isOn) {
/* 2238 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "generateReport(MQJMSMessage,int)", new Object[] { msg, 
/* 2239 */             Integer.valueOf(reason) });
/*      */     }
/*      */     
/* 2242 */     MQMessage out = new MQMessage();
/* 2243 */     ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
/* 2244 */     DataOutputStream dataBuffer = new DataOutputStream(byteBuffer);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2250 */       out.report = 0;
/* 2251 */       out.messageType = 4;
/* 2252 */       out.expiry = -1;
/* 2253 */       out.feedback = reason;
/* 2254 */       out.encoding = msg.getEncoding();
/* 2255 */       out.characterSet = msg.getCharacterSet();
/* 2256 */       out.format = msg.getFormat();
/* 2257 */       out.priority = msg.getPriority();
/* 2258 */       out.persistence = msg.getPersistence();
/*      */       
/* 2260 */       if ((msg.getReport() & 0x80) == 128) {
/* 2261 */         out.messageId = msg.getMessageId();
/*      */       }
/*      */ 
/*      */       
/* 2265 */       if ((msg.getReport() & 0x40) == 64) {
/* 2266 */         out.correlationId = msg.getCorrelationId();
/*      */       }
/*      */       else {
/*      */         
/* 2270 */         out.correlationId = msg.getMessageId();
/*      */       } 
/*      */       
/* 2273 */       out.backoutCount = 0;
/* 2274 */       out.replyToQueueName = "";
/* 2275 */       out.replyToQueueManagerName = this.session.getQMName();
/* 2276 */       out.putApplicationType = 28;
/* 2277 */       out.putApplicationName = "MQ JMS Message Consumer";
/*      */ 
/*      */ 
/*      */       
/* 2281 */       if ((msg.getReport() & 0x7000000) == 117440512) {
/* 2282 */         byte[] buffer = msg.getMessageData();
/* 2283 */         out.write(buffer);
/*      */       }
/* 2285 */       else if ((msg.getReport() & 0x3000000) == 50331648) {
/*      */ 
/*      */ 
/*      */         
/* 2289 */         String format = msg.getFormat();
/* 2290 */         int origCharacterSet = msg.getCharacterSet();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 2298 */           if (format.startsWith("MQH")) {
/* 2299 */             msg.writeTo(dataBuffer, origCharacterSet, true);
/*      */           }
/*      */         }
/* 2302 */         catch (IOException ioe) {
/* 2303 */           if (Trace.isOn) {
/* 2304 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "generateReport(MQJMSMessage,int)", ioe, 1);
/*      */           }
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 2311 */     catch (IOException ioe) {
/* 2312 */       if (Trace.isOn) {
/* 2313 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "generateReport(MQJMSMessage,int)", ioe, 2);
/*      */       }
/*      */ 
/*      */       
/* 2317 */       JMSException jmse = ConfigEnvironment.newException("MQJMS1016");
/* 2318 */       jmse.setLinkedException(ioe);
/* 2319 */       if (Trace.isOn) {
/* 2320 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "generateReport(MQJMSMessage,int)", (Throwable)jmse, 1);
/*      */       }
/*      */       
/* 2323 */       throw jmse;
/*      */     }
/* 2325 */     catch (MQException mqe) {
/* 2326 */       if (Trace.isOn) {
/* 2327 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "generateReport(MQJMSMessage,int)", (Throwable)mqe, 3);
/*      */       }
/*      */ 
/*      */       
/* 2331 */       JMSException jmse = ConfigEnvironment.newException("MQJMS1016");
/* 2332 */       jmse.setLinkedException((Exception)mqe);
/* 2333 */       if (Trace.isOn) {
/* 2334 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "generateReport(MQJMSMessage,int)", (Throwable)jmse, 2);
/*      */       }
/*      */       
/* 2337 */       throw jmse;
/*      */     } 
/*      */ 
/*      */     
/* 2341 */     if (Trace.isOn) {
/* 2342 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "generateReport(MQJMSMessage,int)", out);
/*      */     }
/*      */     
/* 2345 */     return out;
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
/*      */   private String getIDFromMessage(MQMsg2 message) {
/* 2364 */     if (Trace.isOn) {
/* 2365 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getIDFromMessage(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", new Object[] { message });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2372 */     if (message == null) {
/* 2373 */       if (Trace.isOn) {
/* 2374 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getIDFromMessage(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", null, 1);
/*      */       }
/*      */       
/* 2377 */       return null;
/*      */     } 
/*      */     
/* 2380 */     byte[] mid = message.getMessageId();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2389 */     switch (mid[0]) {
/*      */       
/*      */       case 65:
/* 2392 */         if (mid[1] != 77 || mid[2] != 81 || mid[3] != 32) {
/*      */           
/* 2394 */           if (Trace.isOn) {
/* 2395 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getIDFromMessage(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", null, 2);
/*      */           }
/*      */           
/* 2398 */           return null;
/*      */         } 
/*      */         break;
/*      */ 
/*      */       
/*      */       case 67:
/* 2404 */         if (mid[1] != 83 || mid[2] != 81 || mid[3] != 32) {
/*      */           
/* 2406 */           if (Trace.isOn) {
/* 2407 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getIDFromMessage(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", null, 3);
/*      */           }
/*      */           
/* 2410 */           return null;
/*      */         } 
/*      */         break;
/*      */ 
/*      */       
/*      */       case -63:
/* 2416 */         if (mid[1] != -44 || mid[2] != -40 || mid[3] != 64) {
/*      */           
/* 2418 */           if (Trace.isOn) {
/* 2419 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getIDFromMessage(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", null, 4);
/*      */           }
/*      */           
/* 2422 */           return null;
/*      */         } 
/*      */         break;
/*      */ 
/*      */       
/*      */       case -61:
/* 2428 */         if (mid[1] != -30 || mid[2] != -40 || mid[3] != 64) {
/*      */           
/* 2430 */           if (Trace.isOn) {
/* 2431 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getIDFromMessage(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", null, 5);
/*      */           }
/*      */           
/* 2434 */           return null;
/*      */         } 
/*      */         break;
/*      */ 
/*      */       
/*      */       default:
/* 2440 */         if (Trace.isOn) {
/* 2441 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getIDFromMessage(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", null, 6);
/*      */         }
/*      */         
/* 2444 */         return null;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2451 */     StringBuffer result = new StringBuffer();
/* 2452 */     for (int i = 4; i < mid.length; i++) {
/* 2453 */       int byteValue = mid[i] & 0xFF;
/* 2454 */       if (byteValue < 16) {
/* 2455 */         result.append("0");
/*      */       }
/* 2457 */       result.append(Integer.toHexString(byteValue));
/*      */     } 
/*      */     
/* 2460 */     String traceRet1 = result.toString();
/* 2461 */     if (Trace.isOn) {
/* 2462 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getIDFromMessage(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", traceRet1, 7);
/*      */     }
/*      */     
/* 2465 */     return traceRet1;
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
/*      */   private JMSMessage getMessage(long timeOut) throws JMSException {
/* 2495 */     if (Trace.isOn)
/* 2496 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", new Object[] {
/* 2497 */             Long.valueOf(timeOut)
/*      */           }); 
/* 2499 */     JMSMessage result = null;
/* 2500 */     String uniqueID = null;
/*      */     
/* 2502 */     int thisWaitTime = 0;
/* 2503 */     long totalTimeWaited = 0L;
/* 2504 */     boolean browseFirst = false;
/*      */     
/* 2506 */     boolean beyondRescanTime = false;
/*      */ 
/*      */     
/*      */     try {
/* 2510 */       if (Trace.isOn) {
/* 2511 */         Trace.traceData(this, "useBrowse = " + this.useBrowse, null);
/* 2512 */         if (this.setForPTP) {
/* 2513 */           Trace.traceData(this, "Set for PTP", null);
/*      */         }
/* 2515 */         else if (this.setForPubSub) {
/* 2516 */           Trace.traceData(this, "Set for PubSub", null);
/*      */         } else {
/*      */           
/* 2519 */           Trace.traceData(this, "Not set for either domain", null);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2525 */       if (!this.setForPTP && !this.setForPubSub) {
/* 2526 */         if (Trace.isOn) {
/* 2527 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", null, 1);
/*      */         }
/*      */         
/* 2530 */         return null;
/*      */       } 
/*      */ 
/*      */       
/* 2534 */       if (this.setForPubSub && this.session.supportsInherited() && (timeOut > 0L || timeOut == -1L) && this.session.getCommitRequired() && (this.session
/* 2535 */         .getAcknowledgeMode() == 2 || this.session.getAcknowledgeMode() == 0) && this.session
/* 2536 */         .getReceiveIsolation() == 1 && this.session.getProcessDuration() == 1)
/*      */       {
/*      */ 
/*      */         
/* 2540 */         this.session.inhibitITX();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2546 */       int retryCount = 0;
/* 2547 */       long endTime = System.currentTimeMillis() + timeOut;
/* 2548 */       boolean firstScanDone = false;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       do {
/* 2554 */         if (this.closing) {
/* 2555 */           if (Trace.isOn) {
/* 2556 */             Trace.traceData(this, "ABORTING scan because consumer closing", null);
/*      */           }
/* 2558 */           if (Trace.isOn) {
/* 2559 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", null, 2);
/*      */           }
/*      */           
/* 2562 */           return null;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2567 */         if (timeOut == -1L) {
/* 2568 */           this.gmo.waitInterval = CHUNKTIME;
/* 2569 */           endTime = -1L;
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */ 
/*      */           
/* 2578 */           this.gmo.waitInterval = (int)Math.min(endTime - System.currentTimeMillis(), CHUNKTIME);
/*      */ 
/*      */           
/* 2581 */           if (this.gmo.waitInterval < 0 && !firstScanDone) {
/* 2582 */             this.gmo.waitInterval = 0;
/*      */           }
/*      */ 
/*      */           
/* 2586 */           if (this.gmo.waitInterval <= 0 && firstScanDone) {
/* 2587 */             if (Trace.isOn) {
/* 2588 */               Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", null, 3);
/*      */             }
/*      */             
/* 2591 */             return null;
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
/* 2602 */         thisWaitTime = this.gmo.waitInterval;
/* 2603 */         if (Trace.isOn) {
/* 2604 */           Trace.traceData(this, "Current gmo.waitInterval = ", Integer.valueOf(this.gmo.waitInterval));
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2610 */         if (this.useBrowse) {
/* 2611 */           if (Trace.isOn) {
/* 2612 */             Trace.traceData(this, "Setting up for browse", null);
/*      */           }
/*      */           
/* 2615 */           this.gmo.options = this.browseOptions;
/*      */           
/* 2617 */           if (this.setForPTP) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2624 */             if (this.needsRestart) {
/* 2625 */               if (Trace.isOn) {
/* 2626 */                 Trace.traceData(this, "getMessage using browse first", null);
/*      */               }
/* 2628 */               this.gmo.options &= 0xFFFFFFDF;
/* 2629 */               this.gmo.options |= 0x10;
/* 2630 */               browseFirst = true;
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2635 */               this.needsRestart = false;
/*      */ 
/*      */               
/* 2638 */               this.rescanTimeMillis = System.currentTimeMillis();
/* 2639 */               this.rescanTimeMillis += this.rescanTimeInterval;
/* 2640 */               if (this.rescanTimeMillis != 0L && this.rescanTimeMillis < endTime) {
/* 2641 */                 this.gmo.waitInterval = (int)Math.max(this.rescanTimeMillis - System.currentTimeMillis(), 0L);
/*      */               }
/*      */ 
/*      */               
/* 2645 */               if (Trace.isOn) {
/* 2646 */                 Trace.traceData(this, "rescanTimeMillis = " + this.rescanTimeMillis + ", rescanTimeInterval =" + this.rescanTimeInterval, null);
/*      */               }
/*      */             } else {
/*      */               
/* 2650 */               if (Trace.isOn) {
/* 2651 */                 Trace.traceData(this, "getMessage using browse next", null);
/*      */               }
/* 2653 */               this.gmo.options &= 0xFFFFFFEF;
/* 2654 */               this.gmo.options |= 0x20;
/* 2655 */               browseFirst = false;
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2664 */             long now = System.currentTimeMillis();
/*      */             
/* 2666 */             if (now >= this.rescanTimeMillis) {
/* 2667 */               beyondRescanTime = true;
/* 2668 */               thisWaitTime = 0;
/*      */             
/*      */             }
/* 2671 */             else if (this.rescanTimeMillis - now < thisWaitTime) {
/* 2672 */               thisWaitTime = (int)(this.rescanTimeMillis - now);
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2681 */           this.gmo.options &= 0xFFFFFFFD;
/* 2682 */           this.gmo.options &= 0xFFFFEFFF;
/* 2683 */           this.gmo.options |= 0x4;
/*      */         }
/*      */         else {
/*      */           
/* 2687 */           this.gmo.options = this.getOptions;
/*      */ 
/*      */           
/* 2690 */           this.gmo.options &= 0xFFFFFFDF;
/*      */           
/* 2692 */           this.gmo.options |= getSyncPointOptions();
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2697 */         if (Trace.isOn) {
/* 2698 */           Trace.data(this, "setting waitInterval for this get = ", Integer.toString(thisWaitTime));
/*      */         }
/*      */         
/* 2701 */         this.gmo.waitInterval = thisWaitTime;
/*      */         
/* 2703 */         if (timeOut == 0L) {
/* 2704 */           if (Trace.isOn) {
/* 2705 */             Trace.traceData(this, "getMessage setting no wait", null);
/*      */           }
/* 2707 */           this.gmo.options &= 0xFFFFFFFE;
/*      */         } else {
/*      */           
/* 2710 */           if (Trace.isOn) {
/* 2711 */             Trace.traceData(this, "getMessage setting waitInterval", null);
/*      */           }
/* 2713 */           this.gmo.options |= 0x1;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2719 */         int spiOptions = 0;
/* 2720 */         if (this.setForPubSub) {
/* 2721 */           if (this.shared_queue) {
/* 2722 */             this.baseMessage.setCorrelationId(this.subscriberId);
/*      */           }
/*      */           
/* 2725 */           spiOptions = getSPIOptions(retryCount);
/*      */ 
/*      */         
/*      */         }
/* 2729 */         else if (!this.specialCase) {
/* 2730 */           this.gmo.matchOptions = 0;
/*      */         } 
/*      */ 
/*      */         
/* 2734 */         if (Trace.isOn) {
/* 2735 */           Trace.traceData(this, "getMessage about to MQGet. retry #" + retryCount++, null);
/*      */         }
/*      */         
/*      */         try {
/* 2739 */           if (this.setForPubSub && this.syncPoint == 3 && !this.durable) {
/*      */             
/* 2741 */             if (Trace.isOn) {
/* 2742 */               Trace.traceData(this, "PubSub outside syncpoint. Using batched get", null);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2749 */             this.queue.getMsg2(this.baseMessage, this.gmo);
/*      */           }
/* 2751 */           else if (spiOptions != 0) {
/*      */             
/* 2753 */             if (Trace.isOn) {
/* 2754 */               Trace.traceData(this, "Attempting to optimise, using spiOptions = " + spiOptions, null);
/*      */             }
/* 2756 */             this.queue.spiGet(this.baseMessage, this.gmo, spiOptions);
/*      */           }
/*      */           else {
/*      */             
/* 2760 */             if (Trace.isOn) {
/* 2761 */               Trace.traceData(this, "No spi optimisation. Using getMsg2", null);
/*      */             }
/* 2763 */             this.queue.getMsg2(this.baseMessage, this.gmo);
/*      */           } 
/*      */           
/* 2766 */           if (Trace.isOn) {
/* 2767 */             Trace.traceData(this, "get call completed", null);
/*      */           }
/*      */           
/* 2770 */           if (this.syncPoint == 2 && this.baseMessage.getPersistence() == 1) {
/* 2771 */             this.session.setCommitRequired(true);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2778 */           boolean messageSuitable = true;
/*      */ 
/*      */ 
/*      */           
/* 2782 */           if (this.setForPubSub) {
/*      */             
/* 2784 */             if (this.noLocal) {
/* 2785 */               messageSuitable = !isLocalMessage();
/*      */             }
/* 2787 */             result = null;
/*      */           
/*      */           }
/* 2790 */           else if (this.useBrowse) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2795 */             messageSuitable = isNewPTPMessage();
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2801 */           if (messageSuitable) {
/*      */             
/* 2803 */             result = isMessageSelected();
/* 2804 */             if (result == null) {
/* 2805 */               messageSuitable = false;
/*      */             }
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2812 */           if (this.useBrowse) {
/* 2813 */             if (messageSuitable || this.setForPubSub) {
/* 2814 */               boolean removed = removeBrowsedMessage(messageSuitable);
/* 2815 */               if (!removed) {
/* 2816 */                 result = null;
/* 2817 */                 if (Trace.isOn) {
/* 2818 */                   Trace.data(this, "getMessage", "Browsed message not removed");
/*      */                 }
/*      */               } 
/*      */ 
/*      */               
/* 2823 */               if (browseFirst) {
/* 2824 */                 this.needsRestart = true;
/*      */               }
/*      */             }
/*      */             else {
/*      */               
/* 2829 */               if (uniqueID != null)
/*      */               {
/* 2831 */                 this.nextGenSeenList.put(uniqueID, this.nullValue);
/*      */               }
/*      */ 
/*      */               
/* 2835 */               if (this.backoutThreshold != 0 && this.baseMessage.getBackoutCount() >= this.backoutThreshold)
/*      */               {
/*      */                 
/* 2838 */                 if (Trace.isOn) {
/* 2839 */                   Trace.traceData(this, "unwanted PTP message get with SAVE_ALL_CONTEXT for backout", null);
/*      */                 }
/*      */               }
/*      */ 
/*      */               
/* 2844 */               result = null;
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2868 */           if (result != null && this.backoutThreshold != 0 && this.baseMessage
/* 2869 */             .getBackoutCount() >= this.backoutThreshold) {
/* 2870 */             if (Trace.isOn) {
/* 2871 */               Trace.traceData(this, "backoutThreshold reached", null);
/*      */             }
/*      */             try {
/* 2874 */               backoutRequeue(this.baseMessage);
/*      */               
/* 2876 */               messageSuitable = false;
/*      */             }
/* 2878 */             catch (JMSException je) {
/* 2879 */               int reason; if (Trace.isOn) {
/* 2880 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", (Throwable)je, 1);
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2887 */               if (Trace.isOn) {
/* 2888 */                 Trace.traceData(this, "ProviderMessage requeue failed. Treating it as a bad message", null);
/*      */               }
/*      */               
/* 2891 */               Exception e = je.getLinkedException();
/*      */               
/* 2893 */               if (e != null && e instanceof MQException) {
/* 2894 */                 reason = ((MQException)e).reasonCode;
/*      */               } else {
/*      */                 
/* 2897 */                 reason = 2362;
/*      */               } 
/*      */               
/*      */               try {
/* 2901 */                 removeBadMessage(this.baseMessage, reason);
/*      */                 
/* 2903 */                 messageSuitable = false;
/*      */               }
/* 2905 */               catch (JMSException je2) {
/* 2906 */                 if (Trace.isOn) {
/* 2907 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", (Throwable)je2, 2);
/*      */                 }
/*      */ 
/*      */                 
/* 2911 */                 if (Trace.isOn) {
/* 2912 */                   Trace.traceData(this, "removeBadMessage failed", null);
/*      */                 }
/* 2914 */                 if (Trace.isOn) {
/* 2915 */                   Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", (Throwable)je2, 1);
/*      */                 }
/*      */ 
/*      */                 
/* 2919 */                 throw je2;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2934 */           if (result != null) {
/* 2935 */             commitIfRequired(messageSuitable, true);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2943 */           if (!messageSuitable) {
/* 2944 */             result = null;
/*      */           }
/*      */           
/* 2947 */           switch (this.session.getAcknowledgeMode()) {
/*      */             
/*      */             case 0:
/*      */             case 2:
/* 2951 */               if (messageSuitable && result != null) {
/* 2952 */                 result._setSession(this.session);
/* 2953 */                 this.session.setCommitRequired(true);
/*      */               } 
/*      */               break;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2965 */         } catch (MQException mqe) {
/* 2966 */           if (Trace.isOn) {
/* 2967 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", (Throwable)mqe, 3);
/*      */           }
/*      */ 
/*      */           
/* 2971 */           if (mqe.reasonCode == 2033) {
/* 2972 */             firstScanDone = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2982 */             if (System.currentTimeMillis() >= this.rescanTimeMillis) {
/* 2983 */               beyondRescanTime = true;
/*      */             }
/* 2985 */             if (browseFirst || beyondRescanTime) {
/* 2986 */               this.needsRestart = true;
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 2991 */             if ((this.gmo.options & 0x1) == 1) {
/* 2992 */               totalTimeWaited += thisWaitTime;
/*      */             }
/* 2994 */             if (Trace.isOn) {
/* 2995 */               Trace.traceData(this, "used " + totalTimeWaited + " of " + timeOut + " ms", null);
/*      */             }
/* 2997 */             if (timeOut != -1L && totalTimeWaited >= timeOut) {
/* 2998 */               if (Trace.isOn) {
/* 2999 */                 Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", null, 4);
/*      */               }
/*      */               
/* 3002 */               return null;
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3009 */             if (this.useBrowse) {
/* 3010 */               if (Trace.isOn) {
/* 3011 */                 Trace.traceData(this, "requesting restart", null);
/*      */               }
/*      */               
/* 3014 */               this.needsRestart = true;
/*      */ 
/*      */ 
/*      */               
/* 3018 */               this.prevGenSeenList = this.nextGenSeenList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3024 */               int newSize = this.prevGenSeenList.size();
/* 3025 */               if (newSize < 10) {
/* 3026 */                 newSize = 10;
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3032 */               this.nextGenSeenList = new Hashtable<>(newSize);
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/* 3037 */             if (Trace.isOn) {
/* 3038 */               Trace.traceData(this, "MQException thrown while getting message", null);
/*      */             }
/*      */             
/* 3041 */             JMSException je = ConfigEnvironment.newException("MQJMS2002");
/* 3042 */             je.setLinkedException((Exception)mqe);
/* 3043 */             if (Trace.isOn) {
/* 3044 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", (Throwable)je, 2);
/*      */             }
/*      */             
/* 3047 */             throw je;
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 3053 */       while (result == null && this.running == true);
/*      */ 
/*      */       
/* 3056 */       if (!this.running && result == null) {
/* 3057 */         if (Trace.isOn) {
/* 3058 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", null, 5);
/*      */         }
/*      */         
/* 3061 */         return null;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3066 */       if (result != null) {
/* 3067 */         result.setGotByConsume(false);
/*      */       }
/* 3069 */       if (Trace.isOn) {
/* 3070 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", result, 6);
/*      */       }
/*      */       
/* 3073 */       return result;
/*      */     
/*      */     }
/* 3076 */     catch (SessionClosedException sce) {
/* 3077 */       if (Trace.isOn) {
/* 3078 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", (Throwable)sce, 4);
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
/* 3089 */       if (Trace.isOn) {
/* 3090 */         Trace.traceData(this, "SessionClosedException thrown. Returning null rather than throwing the Exception on.", null);
/*      */       }
/*      */       
/* 3093 */       if (Trace.isOn) {
/* 3094 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", null, 7);
/*      */       }
/*      */       
/* 3097 */       return null;
/*      */     }
/* 3099 */     catch (IllegalStateException ise) {
/* 3100 */       if (Trace.isOn) {
/* 3101 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", (Throwable)ise, 5);
/*      */       }
/*      */       
/* 3104 */       String message = ise.getMessage();
/* 3105 */       if (message.equals("MQJMS1024")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3112 */         if (Trace.isOn) {
/* 3113 */           Trace.traceData(this, "IllegalStateException thrown. The ProviderSession has been closed so we cannot continue. Returning null rather than throwing the Exception on.", null);
/*      */         }
/*      */ 
/*      */         
/* 3117 */         if (Trace.isOn) {
/* 3118 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", null, 8);
/*      */         }
/*      */         
/* 3121 */         return null;
/*      */       } 
/*      */       
/* 3124 */       if (Trace.isOn) {
/* 3125 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", (Throwable)ise, 3);
/*      */       }
/*      */       
/* 3128 */       throw ise;
/*      */ 
/*      */     
/*      */     }
/* 3132 */     catch (Exception e) {
/* 3133 */       if (Trace.isOn) {
/* 3134 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", e, 6);
/*      */       }
/*      */       
/* 3137 */       JMSException je = null;
/* 3138 */       if (e instanceof JMSException) {
/* 3139 */         je = (JMSException)e;
/*      */       } else {
/*      */         
/* 3142 */         je = ConfigEnvironment.newException("MQJMS2002");
/* 3143 */         je.setLinkedException(e);
/*      */       } 
/* 3145 */       if (Trace.isOn) {
/* 3146 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getMessage(long)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 3149 */       throw je;
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
/*      */   private int getSPIOptions(int retryCount) throws JMSException {
/* 3162 */     if (Trace.isOn) {
/* 3163 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getSPIOptions(int)", new Object[] {
/* 3164 */             Integer.valueOf(retryCount)
/*      */           });
/*      */     }
/* 3167 */     int options = 0;
/*      */ 
/*      */ 
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
/* 3180 */       if (!this.setForPubSub || !this.session.supportsInherited()) {
/* 3181 */         if (Trace.isOn) {
/* 3182 */           Trace.traceData(this, "Not enabling SPI options.", null);
/*      */         }
/* 3184 */         options = 0;
/* 3185 */         if (Trace.isOn) {
/* 3186 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getSPIOptions(int)", 
/* 3187 */               Integer.valueOf(options), 1);
/*      */         }
/* 3189 */         return options;
/*      */       } 
/*      */ 
/*      */       
/* 3193 */       if (this.syncPoint != 3) {
/*      */         
/* 3195 */         if (Trace.isOn) {
/* 3196 */           String tmp = "";
/* 3197 */           switch (this.syncPoint) {
/*      */             case 1:
/* 3199 */               tmp = "SYNCPOINT";
/*      */               break;
/*      */             case 2:
/* 3202 */               tmp = "SPIP";
/*      */               break;
/*      */             case 3:
/* 3205 */               tmp = "NO_SYNCPOINT";
/*      */               break;
/*      */             default:
/* 3208 */               tmp = "Invalid";
/*      */               break;
/*      */           } 
/* 3211 */           Trace.traceData(this, "syncpoint = " + tmp + ", durable = " + this.durable, null);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3217 */         int ackMode = this.session.getAcknowledgeMode();
/*      */         
/* 3219 */         switch (ackMode) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 1:
/*      */           case 3:
/* 3227 */             if (this.session.getCommitRequired()) {
/* 3228 */               if (Trace.isOn) {
/* 3229 */                 Trace.traceData(this, "Commit required, using spiGETOPT_COMMIT", null);
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3240 */               if ((ackMode == 3 && !this.session.getOutcomeNotification()) || retryCount > 0) {
/*      */                 
/* 3242 */                 if (Trace.isOn) {
/* 3243 */                   Trace.traceData(this, "DUPS_OK and outcomeNotification(false). Using spiGETOPT_COMMIT_ASYNC", null);
/*      */                 }
/* 3245 */                 options += 8;
/*      */                 
/*      */                 break;
/*      */               } 
/* 3249 */               options += 2;
/*      */             } 
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 0:
/*      */           case 2:
/* 3269 */             options = 0;
/*      */             break;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3294 */         if (this.session.getProcessDuration() == 1) {
/* 3295 */           options += 16;
/* 3296 */           if (this.session.getReceiveIsolation() == 1) {
/* 3297 */             options++;
/*      */           
/*      */           }
/*      */         }
/*      */       
/*      */       }
/* 3303 */       else if (Trace.isOn) {
/* 3304 */         Trace.traceData(this, "Not operating under syncpoint, no point in using spiGet", null);
/*      */       } 
/*      */ 
/*      */       
/* 3308 */       if (Trace.isOn) {
/* 3309 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getSPIOptions(int)", 
/* 3310 */             Integer.valueOf(options), 2);
/*      */       }
/* 3312 */       return options;
/*      */     }
/* 3314 */     catch (JMSException je) {
/* 3315 */       if (Trace.isOn) {
/* 3316 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getSPIOptions(int)", (Throwable)je);
/*      */       }
/*      */       
/* 3319 */       if (Trace.isOn) {
/* 3320 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getSPIOptions(int)", (Throwable)je);
/*      */       }
/*      */       
/* 3323 */       throw je;
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
/*      */   private int getSyncPointOptions() throws IntErrorException {
/* 3339 */     if (Trace.isOn)
/* 3340 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getSyncPointOptions()");  try {
/*      */       String detail; HashMap<String, String> ffstData;
/*      */       String key, msg;
/*      */       IntErrorException iee;
/* 3344 */       if (Trace.isOn) {
/* 3345 */         Trace.traceData(this, "getSyncPointOptions", null);
/*      */       }
/*      */       
/* 3348 */       int result = 0;
/*      */       
/* 3350 */       switch (this.syncPoint) {
/*      */         case 1:
/* 3352 */           if (Trace.isOn) {
/* 3353 */             Trace.traceData(this, "Setting for SYNCPOINT", null);
/*      */           }
/* 3355 */           result |= 0x2;
/* 3356 */           result &= 0xFFFFFFFB;
/* 3357 */           result &= 0xFFFFEFFF;
/*      */           break;
/*      */         case 3:
/* 3360 */           if (Trace.isOn) {
/* 3361 */             Trace.traceData(this, "Setting for NO_SYNCPOINT", null);
/*      */           }
/* 3363 */           result |= 0x4;
/* 3364 */           result &= 0xFFFFFFFD;
/* 3365 */           result &= 0xFFFFEFFF;
/*      */           break;
/*      */         case 2:
/* 3368 */           if (Trace.isOn) {
/* 3369 */             Trace.traceData(this, "Setting for SPIP", null);
/*      */           }
/* 3371 */           result |= 0x1000;
/* 3372 */           result &= 0xFFFFFFFB;
/* 3373 */           result &= 0xFFFFFFFD;
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/* 3378 */           if (Trace.isOn) {
/* 3379 */             Trace.traceData(this, "Unrecognized syncPoint setting. About to log the error", null);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3386 */           detail = ConfigEnvironment.getMessage("MQJMS1006", "syncPoint", String.valueOf(this.syncPoint));
/*      */           
/* 3388 */           ffstData = new HashMap<>();
/* 3389 */           ffstData.put("Message", "MQJMS1016");
/* 3390 */           ffstData.put("Comment", detail);
/* 3391 */           Trace.ffst(this, "getSyncPointOptions()", "03", ffstData, IntErrorException.class);
/*      */ 
/*      */           
/* 3394 */           key = "MQJMS1016";
/* 3395 */           msg = ConfigEnvironment.getErrorMessage(key, detail);
/* 3396 */           iee = new IntErrorException(msg, key);
/* 3397 */           if (Trace.isOn) {
/* 3398 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getSyncPointOptions()", (Throwable)iee, 1);
/*      */           }
/*      */           
/* 3401 */           throw iee;
/*      */       } 
/*      */ 
/*      */       
/* 3405 */       if (Trace.isOn) {
/* 3406 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getSyncPointOptions()", 
/* 3407 */             Integer.valueOf(result));
/*      */       }
/* 3409 */       return result;
/*      */     
/*      */     }
/* 3412 */     catch (IntErrorException e) {
/* 3413 */       if (Trace.isOn) {
/* 3414 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getSyncPointOptions()", (Throwable)e);
/*      */       }
/*      */       
/* 3417 */       if (Trace.isOn) {
/* 3418 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "getSyncPointOptions()", (Throwable)e, 2);
/*      */       }
/*      */       
/* 3421 */       throw e;
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
/*      */   public void handlePoisonMessage(ProviderMessage message) throws JMSException {
/* 3434 */     if (Trace.isOn) {
/* 3435 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "handlePoisonMessage(ProviderMessage)", new Object[] { message });
/*      */     }
/*      */     
/*      */     try {
/* 3439 */       backoutRequeue(this.baseMessage);
/*      */     }
/* 3441 */     catch (JMSException je) {
/* 3442 */       int reason; if (Trace.isOn) {
/* 3443 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "handlePoisonMessage(ProviderMessage)", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3448 */       if (Trace.isOn) {
/* 3449 */         Trace.traceData(this, "ProviderMessage requeue failed. Treating it as a bad message", null);
/*      */       }
/*      */       
/* 3452 */       Exception e = je.getLinkedException();
/*      */       
/* 3454 */       if (e != null && e instanceof MQException) {
/* 3455 */         reason = ((MQException)e).reasonCode;
/*      */       } else {
/*      */         
/* 3458 */         reason = 2362;
/*      */       } 
/*      */       
/*      */       try {
/* 3462 */         removeBadMessage(this.baseMessage, reason);
/*      */       }
/* 3464 */       catch (JMSException je2) {
/* 3465 */         if (Trace.isOn) {
/* 3466 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "handlePoisonMessage(ProviderMessage)", (Throwable)je2, 2);
/*      */         }
/*      */         
/* 3469 */         if (Trace.isOn) {
/* 3470 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "handlePoisonMessage(ProviderMessage)", (Throwable)je2);
/*      */         }
/*      */         
/* 3473 */         throw je2;
/*      */       } 
/*      */     } 
/* 3476 */     if (Trace.isOn) {
/* 3477 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "handlePoisonMessage(ProviderMessage)");
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
/*      */   public boolean shouldMessageBeRequeued(int attempts, ProviderMessage message) {
/* 3494 */     if (Trace.isOn) {
/* 3495 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "shouldMessageBeRequeued(int,ProviderMessage)", new Object[] {
/* 3496 */             Integer.valueOf(attempts), message
/*      */           });
/*      */     }
/* 3499 */     boolean requeue = (attempts >= this.backoutThreshold);
/* 3500 */     if (Trace.isOn) {
/* 3501 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "shouldMessageBeRequeued(int,ProviderMessage)", 
/* 3502 */           Boolean.valueOf(requeue));
/*      */     }
/* 3504 */     return requeue;
/*      */   }
/*      */   
/*      */   private boolean isLocalMessage() throws MQException, JMSException {
/* 3508 */     if (Trace.isOn) {
/* 3509 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isLocalMessage()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3515 */     boolean localMessage = false;
/* 3516 */     RFH rfh1 = null;
/*      */ 
/*      */     
/*      */     try {
/*      */       long format;
/*      */ 
/*      */       
/*      */       try {
/* 3524 */         format = this.baseMessage.getFormatAsLong();
/*      */       }
/* 3526 */       catch (Exception e) {
/* 3527 */         if (Trace.isOn) {
/* 3528 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isLocalMessage()", e, 1);
/*      */         }
/*      */ 
/*      */         
/* 3532 */         JMSException je = ConfigEnvironment.newException("MQJMS1087");
/*      */         
/* 3534 */         if (Trace.isOn) {
/* 3535 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isLocalMessage()", (Throwable)je, 1);
/*      */         }
/*      */         
/* 3538 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 3542 */       String id = null;
/* 3543 */       if (format == 5571313732235042848L || format == -3109514705039769536L) {
/* 3544 */         if (Trace.isOn) {
/* 3545 */           Trace.traceData(this, "Received message has RFH1 header", null);
/*      */         }
/*      */ 
/*      */         
/*      */         try {
/* 3550 */           rfh1 = new RFH(this.baseMessage);
/* 3551 */           if (rfh1 != null) {
/* 3552 */             id = rfh1.getValue("UNIQUE_CONNECTION_ID");
/*      */           }
/*      */         }
/* 3555 */         catch (JMSException je) {
/* 3556 */           if (Trace.isOn) {
/* 3557 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isLocalMessage()", (Throwable)je, 2);
/*      */           }
/*      */ 
/*      */           
/* 3561 */           if (Trace.isOn) {
/* 3562 */             Trace.traceData(this, "couldn't create an RFH from the incoming message", null);
/*      */           }
/*      */         }
/* 3565 */         catch (NoSuchElementException e) {
/*      */           
/* 3567 */           if (Trace.isOn) {
/* 3568 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isLocalMessage()", e, 3);
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/* 3576 */       else if (format == 5571313732236222496L || format == -3109514705028104128L) {
/* 3577 */         if (Trace.isOn) {
/* 3578 */           Trace.traceData(this, "Received message has RFH2 header", null);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 3586 */           JmqiEnvironment env = MQSESSION.getJmqiEnv();
/* 3587 */           JmqiSystemEnvironment jmqiSysEnv = MQSESSION.getJmqiSysEnv();
/* 3588 */           JmqiComponentTls tls = jmqiSysEnv.getComponentTls(MQSESSION.jmsv6CompId);
/* 3589 */           JmqiTls jTls = jmqiSysEnv.getJmqiTls(tls);
/*      */           
/* 3591 */           int encoding = this.baseMessage.getEncoding();
/* 3592 */           int ccsid = this.baseMessage.getCharacterSet();
/* 3593 */           long mqheader = format & 0xFFFFFF0000000000L;
/* 3594 */           byte[] buffer = this.baseMessage.getMessageData();
/* 3595 */           int pos = 0;
/*      */           
/* 3597 */           JmqiCodepage cp = JmqiCodepage.getJmqiCodepage(env, ccsid);
/* 3598 */           int previousCcsid = ccsid;
/*      */           
/* 3600 */           int ptrSize = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3605 */           MQRFH rfh = env.newMQRFH(10);
/* 3606 */           MQHeader header = rfh.getMqHeader();
/*      */ 
/*      */           
/* 3609 */           header.setFormat(this.baseMessage.getFormat());
/*      */           
/* 3611 */           long formatLong = MQHeader.convertFormatToLong(env, cp, this.baseMessage.getFormat());
/*      */           
/* 3613 */           while ((mqheader == 5571313378871214080L || mqheader == -3109515640373772288L) && id == null)
/*      */           {
/* 3615 */             if (ccsid == -2) {
/* 3616 */               ccsid = previousCcsid;
/*      */             }
/*      */             
/* 3619 */             if (ccsid != previousCcsid) {
/* 3620 */               cp = JmqiCodepage.getJmqiCodepage(env, ccsid);
/*      */             }
/*      */             
/* 3623 */             boolean swap = ((encoding & 0xF) == 2);
/* 3624 */             int bodyPos = header.readFromBuffer(buffer, pos, ptrSize, swap, cp, jTls, false);
/*      */             
/* 3626 */             if (formatLong == 5571313732236222496L || formatLong == -3109514705028104128L) {
/* 3627 */               rfh.readBodyFromBuffer(buffer, bodyPos, ptrSize, swap, cp, jTls);
/* 3628 */               int numberOfFolders = rfh.getNameValueDataLength();
/* 3629 */               for (int i = 0; i < numberOfFolders; i++) {
/* 3630 */                 String folder = rfh.getNameValueData(i);
/*      */                 
/* 3632 */                 int idStartTagPos = folder.indexOf("<UNIQUE_CONNECTION_ID>");
/* 3633 */                 int idEndTagPos = folder.indexOf("</UNIQUE_CONNECTION_ID>");
/* 3634 */                 if (idStartTagPos != -1 && idEndTagPos != -1) {
/* 3635 */                   id = folder.substring(idStartTagPos + 22, idEndTagPos);
/*      */                   
/*      */                   break;
/*      */                 } 
/*      */               } 
/*      */             } 
/* 3641 */             pos += header.getStrucLength();
/* 3642 */             encoding = header.getEncoding();
/* 3643 */             previousCcsid = ccsid;
/* 3644 */             ccsid = header.getCodedCharSetId();
/* 3645 */             formatLong = header.getFormatLong(cp);
/* 3646 */             mqheader = formatLong & 0xFFFFFF0000000000L;
/*      */           }
/*      */         
/* 3649 */         } catch (RuntimeException rte) {
/* 3650 */           if (Trace.isOn) {
/* 3651 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isLocalMessage()", rte, 4);
/*      */           }
/*      */           
/* 3654 */           if (Trace.isOn) {
/* 3655 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isLocalMessage()", rte, 2);
/*      */           }
/*      */           
/* 3658 */           throw rte;
/*      */         }
/* 3660 */         catch (Exception e) {
/* 3661 */           if (Trace.isOn) {
/* 3662 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isLocalMessage()", e, 5);
/*      */           }
/*      */ 
/*      */           
/* 3666 */           JMSException je = ConfigEnvironment.newException("MQJMS1087");
/*      */           
/* 3668 */           if (Trace.isOn) {
/* 3669 */             Trace.traceData(this, "Create and Throwing " + je, null);
/*      */           }
/* 3671 */           if (Trace.isOn) {
/* 3672 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isLocalMessage()", (Throwable)je, 3);
/*      */           }
/*      */           
/* 3675 */           throw je;
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 3681 */         JMSException je = ConfigEnvironment.newException("MQJMS1087");
/*      */         
/* 3683 */         if (Trace.isOn) {
/* 3684 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isLocalMessage()", (Throwable)je, 4);
/*      */         }
/*      */         
/* 3687 */         throw je;
/*      */       } 
/*      */       
/* 3690 */       if (id == null) {
/* 3691 */         if (Trace.isOn) {
/* 3692 */           Trace.traceData(this, "No UNIQUE_CONNECTION_ID in message", null);
/*      */         }
/*      */       }
/* 3695 */       else if (id.equals(this.session.getConnectionID())) {
/* 3696 */         if (Trace.isOn) {
/* 3697 */           Trace.traceData(this, "IDs match (" + id + "), therefore the message is local, and needs to be discarded. Setting retry=true", null);
/*      */         }
/*      */         
/* 3700 */         localMessage = true;
/*      */       } else {
/*      */         
/* 3703 */         if (Trace.isOn) {
/* 3704 */           Trace.traceData(this, "IDs don't match. Incoming=" + id + ", ProviderConnection=" + this.session.getConnectionID() + ". ProviderMessage is non-local, therefore valid", null);
/*      */         }
/*      */         
/* 3707 */         localMessage = false;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3714 */       this.baseMessage.resetReadPosition();
/*      */       
/* 3716 */       if (Trace.isOn) {
/* 3717 */         Trace.traceData(this, "isLocalMessage returning " + localMessage, null);
/*      */       }
/* 3719 */       if (Trace.isOn) {
/* 3720 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isLocalMessage()", 
/* 3721 */             Boolean.valueOf(localMessage));
/*      */       }
/* 3723 */       return localMessage;
/*      */     }
/* 3725 */     catch (JMSException je) {
/* 3726 */       JMSException jMSException1; if (Trace.isOn) {
/* 3727 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isLocalMessage()", (Throwable)jMSException1, 6);
/*      */       }
/*      */       
/* 3730 */       if (Trace.isOn) {
/* 3731 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isLocalMessage()", (Throwable)jMSException1, 5);
/*      */       }
/*      */       
/* 3734 */       throw jMSException1;
/*      */     }
/* 3736 */     catch (MQException mqe) {
/* 3737 */       if (Trace.isOn) {
/* 3738 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isLocalMessage()", (Throwable)mqe, 7);
/*      */       }
/*      */       
/* 3741 */       if (Trace.isOn) {
/* 3742 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isLocalMessage()", (Throwable)mqe, 6);
/*      */       }
/*      */       
/* 3745 */       throw mqe;
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
/*      */   private JMSMessage isMessageSelected() throws JMSException {
/* 3760 */     if (Trace.isOn) {
/* 3761 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isMessageSelected()");
/*      */     }
/*      */     
/*      */     try {
/* 3765 */       JMSMessage result = null;
/*      */       
/*      */       try {
/* 3768 */         result = this.baseMessage.createJMSMessage(this.session, (ProviderDestination)this.destination);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 3779 */       catch (JMSException je) {
/* 3780 */         if (Trace.isOn) {
/* 3781 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isMessageSelected()", (Throwable)je, 1);
/*      */         }
/*      */         
/* 3784 */         if (Trace.isOn) {
/* 3785 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isMessageSelected()", (Throwable)je, 1);
/*      */         }
/*      */         
/* 3788 */         throw je;
/*      */       }
/* 3790 */       catch (IOException ioe) {
/* 3791 */         if (Trace.isOn) {
/* 3792 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isMessageSelected()", ioe, 2);
/*      */         }
/*      */         
/* 3795 */         if (Trace.isOn) {
/* 3796 */           Trace.traceData(this, "IOException thrown creating JMSMessage, Wrapping in new JMSException", null);
/*      */         }
/* 3798 */         JMSException je = ConfigEnvironment.newException("MQJMS1000");
/* 3799 */         je.setLinkedException(ioe);
/* 3800 */         if (Trace.isOn) {
/* 3801 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isMessageSelected()", (Throwable)je, 2);
/*      */         }
/*      */         
/* 3804 */         throw je;
/*      */       } 
/*      */       
/* 3807 */       boolean messageSuitable = true;
/* 3808 */       if ((this.setForPTP && this.useBrowse) || (this.setForPubSub && this.selectorSet && this.subscription.getFilter() == null)) {
/*      */         
/* 3810 */         if (Trace.isOn) {
/* 3811 */           Trace.traceData(this, "About to check selector", null);
/*      */         }
/* 3813 */         messageSuitable = this.messageSelector.isSelected(result, this.baseMessage);
/*      */       } else {
/*      */         
/* 3816 */         if (Trace.isOn) {
/* 3817 */           Trace.traceData(this, "isMessageSelected just returning JMSMessage", null);
/*      */         }
/* 3819 */         if (Trace.isOn) {
/* 3820 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isMessageSelected()", result, 1);
/*      */         }
/*      */         
/* 3823 */         return result;
/*      */       } 
/*      */       
/* 3826 */       if (messageSuitable) {
/* 3827 */         if (Trace.isOn) {
/* 3828 */           Trace.traceData(this, "isMessageSelected true, returning JMSMessage", null);
/*      */         }
/* 3830 */         if (Trace.isOn) {
/* 3831 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isMessageSelected()", result, 2);
/*      */         }
/*      */         
/* 3834 */         return result;
/*      */       } 
/*      */       
/* 3837 */       if (Trace.isOn) {
/* 3838 */         Trace.traceData(this, "isMessageSelected false, returning null", null);
/*      */       }
/* 3840 */       if (Trace.isOn) {
/* 3841 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isMessageSelected()", null, 3);
/*      */       }
/*      */       
/* 3844 */       return null;
/*      */     
/*      */     }
/* 3847 */     catch (JMSException je) {
/* 3848 */       if (Trace.isOn) {
/* 3849 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isMessageSelected()", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3858 */       backoutFailedMsgs();
/*      */       
/* 3860 */       if (Trace.isOn) {
/* 3861 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isMessageSelected()", (Throwable)je, 3);
/*      */       }
/*      */       
/* 3864 */       throw je;
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
/*      */   private void backoutFailedMsgs() throws JMSException {
/* 3881 */     if (Trace.isOn) {
/* 3882 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "backoutFailedMsgs()");
/*      */     }
/*      */     
/* 3885 */     if (this.backoutThreshold != 0 && this.baseMessage.getBackoutCount() >= this.backoutThreshold) {
/* 3886 */       if (Trace.isOn) {
/* 3887 */         Trace.traceData(this, "backoutThreshold reached", null);
/*      */       }
/*      */       try {
/* 3890 */         backoutRequeue(this.baseMessage);
/*      */       }
/* 3892 */       catch (JMSException je) {
/* 3893 */         int reason; if (Trace.isOn) {
/* 3894 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "backoutFailedMsgs()", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3900 */         if (Trace.isOn) {
/* 3901 */           Trace.traceData(this, "Message requeue failed. Treating it as a bad message", null);
/*      */         }
/*      */         
/* 3904 */         Exception e = je.getLinkedException();
/*      */         
/* 3906 */         if (e != null && e instanceof MQException) {
/* 3907 */           reason = ((MQException)e).reasonCode;
/*      */         } else {
/*      */           
/* 3910 */           reason = 2362;
/*      */         } 
/*      */         
/*      */         try {
/* 3914 */           removeBadMessage(this.baseMessage, reason);
/*      */         
/*      */         }
/* 3917 */         catch (JMSException je2) {
/* 3918 */           if (Trace.isOn) {
/* 3919 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "backoutFailedMsgs()", (Throwable)je2, 2);
/*      */           }
/*      */           
/* 3922 */           if (Trace.isOn) {
/* 3923 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "backoutFailedMsgs()", (Throwable)je2);
/*      */           }
/*      */           
/* 3926 */           if (Trace.isOn) {
/* 3927 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "backoutFailedMsgs()", (Throwable)je2);
/*      */           }
/*      */           
/* 3930 */           throw je2;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 3935 */       commitIfRequired(false, true);
/* 3936 */       switch (this.session.getAcknowledgeMode()) {
/*      */         
/*      */         case 0:
/*      */         case 2:
/* 3940 */           this.session.setCommitRequired(true);
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 3949 */     if (Trace.isOn) {
/* 3950 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "backoutFailedMsgs()");
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
/*      */   private boolean isNewPTPMessage() {
/* 3963 */     if (Trace.isOn) {
/* 3964 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isNewPTPMessage()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3970 */     String uniqueID = null;
/* 3971 */     uniqueID = getIDFromMessage(this.baseMessage);
/*      */     
/* 3973 */     boolean seen = (uniqueID != null && this.prevGenSeenList.containsKey(uniqueID) && !this.nextGenSeenList.containsKey(uniqueID));
/*      */     
/* 3975 */     if (seen) {
/* 3976 */       if (Trace.isOn) {
/* 3977 */         Trace.traceData(this, "message found in seen list. Skipping.", null);
/*      */       }
/*      */ 
/*      */       
/* 3981 */       this.nextGenSeenList.put(uniqueID, this.nullValue);
/*      */ 
/*      */       
/* 3984 */       if (Trace.isOn) {
/* 3985 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isNewPTPMessage()", 
/* 3986 */             Boolean.valueOf(false), 1);
/*      */       }
/* 3988 */       return false;
/*      */     } 
/*      */     
/* 3991 */     if (Trace.isOn) {
/* 3992 */       Trace.traceData(this, "isNewPTPMessage returning true", null);
/*      */     }
/* 3994 */     if (Trace.isOn) {
/* 3995 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "isNewPTPMessage()", 
/* 3996 */           Boolean.valueOf(true), 2);
/*      */     }
/* 3998 */     return true;
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
/*      */   public ProviderMessage receive() throws JMSException {
/* 4018 */     if (Trace.isOn) {
/* 4019 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receive()");
/*      */     }
/*      */     
/*      */     try {
/* 4023 */       ProviderMessage traceRet1 = receiveInternal(-1L);
/* 4024 */       if (Trace.isOn) {
/* 4025 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receive()", traceRet1);
/*      */       }
/*      */       
/* 4028 */       return traceRet1;
/*      */     }
/* 4030 */     catch (JMSException je) {
/* 4031 */       if (Trace.isOn) {
/* 4032 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receive()", (Throwable)je);
/*      */       }
/*      */       
/* 4035 */       if (Trace.isOn) {
/* 4036 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receive()", (Throwable)je);
/*      */       }
/*      */       
/* 4039 */       throw je;
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
/*      */   public ProviderMessage receive(long timeout) throws JMSException {
/* 4058 */     if (Trace.isOn)
/* 4059 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receive(long)", new Object[] {
/* 4060 */             Long.valueOf(timeout)
/*      */           }); 
/* 4062 */     long mytimeout = timeout;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 4072 */       if (timeout == 0L) {
/* 4073 */         mytimeout = -1L;
/*      */       }
/* 4075 */       else if (timeout == -1L) {
/* 4076 */         mytimeout = 0L;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4082 */       ProviderMessage traceRet1 = receiveInternal(mytimeout);
/*      */       
/* 4084 */       if (Trace.isOn) {
/* 4085 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receive(long)", traceRet1);
/*      */       }
/*      */       
/* 4088 */       return traceRet1;
/*      */     }
/* 4090 */     catch (JMSException je) {
/* 4091 */       if (Trace.isOn) {
/* 4092 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receive(long)", (Throwable)je);
/*      */       }
/*      */       
/* 4095 */       if (Trace.isOn) {
/* 4096 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receive(long)", (Throwable)je);
/*      */       }
/*      */       
/* 4099 */       throw je;
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
/*      */   protected boolean receiveAsync(long timeOut) throws NoMsgListenerException, JMSException {
/* 4139 */     if (Trace.isOn) {
/* 4140 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveAsync(long)", new Object[] {
/* 4141 */             Long.valueOf(timeOut)
/*      */           });
/*      */     }
/* 4144 */     boolean msgDelivered = false;
/*      */     
/*      */     try {
/*      */       boolean transactedLocal;
/* 4148 */       if (Trace.isOn) {
/* 4149 */         Trace.traceData(this, "Performing initial validity checks", null);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 4154 */       synchronized (this) {
/* 4155 */         if (this.closing || this.closed) {
/* 4156 */           if (Trace.isOn) {
/* 4157 */             Trace.traceData(this, "ProviderMessageConsumer is closing or closed.", null);
/* 4158 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveAsync(long)", 
/* 4159 */                 Boolean.valueOf(false), 1);
/*      */           } 
/* 4161 */           return false;
/*      */         } 
/* 4163 */         if (this.queue == null) {
/* 4164 */           JMSException je = ConfigEnvironment.newException("MQJMS2001");
/* 4165 */           if (Trace.isOn) {
/* 4166 */             Trace.traceData(this, "Queue is null.", null);
/* 4167 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveAsync(long)", (Throwable)je, 1);
/*      */           } 
/*      */           
/* 4170 */           throw je;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4177 */       if (!this.setForPTP && !this.setForPubSub) {
/* 4178 */         if (Trace.isOn) {
/* 4179 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveAsync(long)", 
/* 4180 */               Boolean.valueOf(false), 2);
/*      */         }
/* 4182 */         return false;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 4187 */       if (!this.session.isStarted()) {
/* 4188 */         if (Trace.isOn) {
/* 4189 */           Trace.traceData(this, "ProviderSession is not started, returning immediately", null);
/* 4190 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveAsync(long)", 
/* 4191 */               Boolean.valueOf(false), 3);
/*      */         } 
/* 4193 */         return false;
/*      */       } 
/*      */ 
/*      */       
/* 4197 */       JMSMessage message = null;
/*      */       
/* 4199 */       ProviderMessageListener cachedListener = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4216 */       synchronized (this) {
/* 4217 */         if (this.closed) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 4227 */           if (Trace.isOn) {
/* 4228 */             Trace.traceData(this, "ProviderMessageConsumer is closed.", null);
/* 4229 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveAsync(long)", 
/* 4230 */                 Boolean.valueOf(false), 4);
/*      */           } 
/* 4232 */           return false;
/*      */         } 
/*      */ 
/*      */         
/* 4236 */         cachedListener = this.listener;
/* 4237 */         if (cachedListener == null) {
/* 4238 */           JMSException je = ConfigEnvironment.newException("MQJMS1012");
/* 4239 */           if (Trace.isOn) {
/* 4240 */             Trace.traceData(this, "No registered ProviderMessageListener.", null);
/* 4241 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveAsync(long)", (Throwable)je, 2);
/*      */           } 
/*      */           
/* 4244 */           throw je;
/*      */         } 
/*      */         
/* 4247 */         if (Trace.isOn) {
/* 4248 */           Trace.traceData(this, "Validity checks passed.", null);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4254 */         transactedLocal = this.session.getTransacted();
/*      */ 
/*      */         
/* 4257 */         message = getMessage(timeOut);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 4262 */       if (message != null) {
/*      */ 
/*      */         
/* 4265 */         if (!transactedLocal && this.acknowledgeMode == 2) {
/* 4266 */           message._setSession(this.session);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4275 */         if (Trace.isOn) {
/* 4276 */           Trace.traceData(this, "entering async delivery block", null);
/*      */         }
/*      */         
/* 4279 */         boolean retry = true;
/* 4280 */         int attempts = 0;
/*      */         
/* 4282 */         while (retry) {
/*      */           try {
/* 4284 */             retry = false;
/* 4285 */             attempts++;
/* 4286 */             cachedListener.onMessage(message);
/*      */           }
/* 4288 */           catch (Throwable t) {
/*      */             
/* 4290 */             if (Trace.isOn) {
/* 4291 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveAsync(long)", t, 1);
/*      */             }
/*      */ 
/*      */             
/* 4295 */             if (Trace.isOn) {
/* 4296 */               Trace.traceData(this, "Exception thrown from ProviderMessageListener.onMessage()", null);
/*      */             }
/*      */             
/* 4299 */             HashMap<String, Serializable> ffstData = new HashMap<>();
/* 4300 */             ffstData.put("Exception", t);
/* 4301 */             ffstData.put("Message", "MQJMS1034");
/* 4302 */             Trace.ffst(this, "receiveAsync(long)", "01", ffstData, JMSException.class);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 4308 */             if ((this.acknowledgeMode == 1 || this.acknowledgeMode == 3) && attempts < this.maxAsyncAttempts) {
/*      */               
/* 4310 */               if (Trace.isOn) {
/* 4311 */                 Trace.traceData(this, "We will attempt to redeliver this message", null);
/*      */               }
/* 4313 */               retry = true;
/* 4314 */               message.setJMSRedelivered(true);
/*      */               
/*      */               continue;
/*      */             } 
/*      */             
/*      */             try {
/* 4320 */               if (this.backoutRetryQueue == null) {
/* 4321 */                 JMSException je = ConfigEnvironment.newException("MQJMS1022");
/* 4322 */                 if (Trace.isOn) {
/* 4323 */                   Trace.traceData(this, "receiveAsync() no backout queue defined.", null);
/*      */                 }
/* 4325 */                 if (Trace.isOn) {
/* 4326 */                   Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveAsync(long)", (Throwable)je, 3);
/*      */                 }
/*      */ 
/*      */                 
/* 4330 */                 throw je;
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/* 4335 */               boolean syncRedirect = true;
/* 4336 */               if (this.syncPoint == 3 || (this.syncPoint == 2 && this.baseMessage.getPersistence() != 1))
/*      */               {
/*      */ 
/*      */                 
/* 4340 */                 syncRedirect = false;
/*      */               }
/*      */               
/* 4343 */               this.session.redirectMessage(this.backoutRetryQueue, this.baseMessage, syncRedirect);
/*      */               
/* 4345 */               if (this.syncPoint == 2 && this.baseMessage.getPersistence() != 1)
/*      */               {
/* 4347 */                 this.session._acknowledgeInternal();
/*      */               }
/*      */             }
/* 4350 */             catch (JMSException je) {
/* 4351 */               if (Trace.isOn) {
/* 4352 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveAsync(long)", (Throwable)je, 2);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/* 4357 */               if (Trace.isOn) {
/* 4358 */                 Trace.traceData(this, "failed to redirect message. Closing receiver", null);
/*      */               }
/*      */               
/* 4361 */               ffstData = new HashMap<>();
/* 4362 */               ffstData.put("Exception", je);
/* 4363 */               ffstData.put("Message", "MQJMS1022");
/* 4364 */               Trace.ffst(this, "receiveAsync(long)", "02", ffstData, JMSException.class);
/*      */ 
/*      */               
/* 4367 */               close(false, null);
/*      */ 
/*      */               
/* 4370 */               if (Trace.isOn) {
/* 4371 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveAsync(long)", (Throwable)je, 4);
/*      */               }
/*      */ 
/*      */               
/* 4375 */               throw je;
/*      */             } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4399 */         if (this.session.getCommitRequired()) {
/* 4400 */           commitIfRequired(true, false);
/*      */         }
/* 4402 */         if (this.session.getAcknowledgeMode() == 2 || this.session.getAcknowledgeMode() == 0) {
/* 4403 */           message._setSession(this.session);
/*      */         }
/*      */         
/* 4406 */         msgDelivered = true;
/*      */       } 
/*      */ 
/*      */       
/* 4410 */       if (Trace.isOn) {
/* 4411 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveAsync(long)", 
/* 4412 */             Boolean.valueOf(msgDelivered), 5);
/*      */       }
/* 4414 */       return msgDelivered;
/*      */     
/*      */     }
/* 4417 */     catch (JMSException je) {
/* 4418 */       if (Trace.isOn) {
/* 4419 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveAsync(long)", (Throwable)je, 3);
/*      */       }
/*      */       
/* 4422 */       if (Trace.isOn) {
/* 4423 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveAsync(long)", (Throwable)je, 6);
/*      */       }
/*      */       
/* 4426 */       throw je;
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
/*      */   private ProviderMessage receiveInternal(long timeOut) throws NoMsgListenerException, JMSException {
/* 4467 */     if (Trace.isOn)
/* 4468 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveInternal(long)", new Object[] {
/* 4469 */             Long.valueOf(timeOut) }); 
/*      */     try {
/*      */       long timeLeft;
/* 4472 */       JMSMessage result = null;
/*      */ 
/*      */ 
/*      */       
/* 4476 */       if (Trace.isOn) {
/* 4477 */         Trace.traceData(this, "Performing initial validity checks", null);
/*      */       }
/*      */       
/* 4480 */       if (this.closed || this.session == null) {
/* 4481 */         String msg; if (Trace.isOn) {
/* 4482 */           Trace.traceData(this, "ProviderSession is closed or null.", null);
/*      */         }
/*      */         
/* 4485 */         if (this.setForPubSub) {
/* 4486 */           msg = ConfigEnvironment.getErrorMessage("MQJMS3034");
/*      */         }
/* 4488 */         else if (this.setForPTP) {
/* 4489 */           msg = ConfigEnvironment.getErrorMessage("MQJMS3033");
/*      */         } else {
/*      */           
/* 4492 */           msg = ConfigEnvironment.getErrorMessage("MQJMS3038");
/*      */         } 
/*      */         
/* 4495 */         IllegalStateException illegalStateException = new IllegalStateException(msg);
/* 4496 */         if (Trace.isOn) {
/* 4497 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveInternal(long)", (Throwable)illegalStateException, 1);
/*      */         }
/*      */         
/* 4500 */         throw illegalStateException;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4509 */       if (this.queue == null) {
/* 4510 */         if (Trace.isOn) {
/* 4511 */           Trace.traceData(this, "Queue is null.", null);
/*      */         }
/* 4513 */         JMSException je = ConfigEnvironment.newException("MQJMS2001");
/* 4514 */         if (Trace.isOn) {
/* 4515 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveInternal(long)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 4518 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 4522 */       if (!this.session.callingFromOnMessage() && (this.session.usingAsyncMode() || this.session.hasMessageListener())) {
/* 4523 */         if (Trace.isOn) {
/* 4524 */           Trace.traceData(this, "ProviderSession is in async mode.", null);
/*      */         }
/* 4526 */         JMSException je = ConfigEnvironment.newException("MQJMS1013");
/* 4527 */         if (Trace.isOn) {
/* 4528 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveInternal(long)", (Throwable)je, 3);
/*      */         }
/*      */         
/* 4531 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 4535 */       if (timeOut != -1L && (timeOut > 2147483647L || timeOut < 0L)) {
/* 4536 */         if (Trace.isOn) {
/* 4537 */           Trace.traceData(this, "Invalid timeOut.", null);
/*      */         }
/* 4539 */         JMSException je = ConfigEnvironment.newException("MQJMS1067");
/* 4540 */         if (Trace.isOn) {
/* 4541 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveInternal(long)", (Throwable)je, 4);
/*      */         }
/*      */         
/* 4544 */         throw je;
/*      */       } 
/*      */       
/* 4547 */       if (Trace.isOn) {
/* 4548 */         Trace.traceData(this, "Finished validity checks", null);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 4553 */       if (timeOut == 0L && this.session.isStopped()) {
/* 4554 */         if (Trace.isOn) {
/* 4555 */           Trace.traceData(this, "early return. ProviderSession closed and timeOut = 0", null);
/*      */         }
/* 4557 */         if (Trace.isOn) {
/* 4558 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveInternal(long)", null, 1);
/*      */         }
/*      */         
/* 4561 */         return null;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4567 */       if (Trace.isOn) {
/* 4568 */         Trace.traceData(this, "receiveInternal - waiting for session start", null);
/*      */       }
/* 4570 */       if (timeOut == -1L) {
/* 4571 */         this.session.waitForStart();
/* 4572 */         timeLeft = -1L;
/*      */       } else {
/*      */         
/* 4575 */         timeLeft = this.session.waitForStart(timeOut);
/*      */       } 
/* 4577 */       if (Trace.isOn) {
/* 4578 */         Trace.traceData(this, "receiveInternal - session started", null);
/*      */       }
/*      */ 
/*      */       
/* 4582 */       if (timeOut > 0L && timeLeft == 0L) {
/* 4583 */         if (Trace.isOn) {
/* 4584 */           Trace.traceData(this, "Out of time waiting for ProviderSession to start. Returning null", null);
/*      */         }
/* 4586 */         if (Trace.isOn) {
/* 4587 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveInternal(long)", null, 2);
/*      */         }
/*      */         
/* 4590 */         return null;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4596 */       if (!this.session.isStarted() || this.closed) {
/* 4597 */         if (Trace.isOn) {
/* 4598 */           Trace.traceData(this, "ProviderSession stopped or closed while getting transation Lock. Returning null", null);
/*      */         }
/* 4600 */         if (Trace.isOn) {
/* 4601 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveInternal(long)", null, 3);
/*      */         }
/*      */         
/* 4604 */         return null;
/*      */       } 
/*      */       
/* 4607 */       result = getMessage(timeLeft);
/*      */       
/* 4609 */       if (Trace.isOn) {
/* 4610 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveInternal(long)", result, 4);
/*      */       }
/*      */       
/* 4613 */       return result;
/*      */     
/*      */     }
/* 4616 */     catch (JMSException je) {
/* 4617 */       if (Trace.isOn) {
/* 4618 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveInternal(long)", (Throwable)je);
/*      */       }
/*      */       
/* 4621 */       if (Trace.isOn) {
/* 4622 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveInternal(long)", (Throwable)je, 5);
/*      */       }
/*      */       
/* 4625 */       throw je;
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
/*      */   public ProviderMessage receiveNoWait() throws JMSException {
/* 4638 */     if (Trace.isOn) {
/* 4639 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveNoWait()");
/*      */     }
/*      */     
/*      */     try {
/* 4643 */       ProviderMessage traceRet1 = receiveInternal(0L);
/* 4644 */       if (Trace.isOn) {
/* 4645 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveNoWait()", traceRet1);
/*      */       }
/*      */       
/* 4648 */       return traceRet1;
/*      */     }
/* 4650 */     catch (JMSException je) {
/* 4651 */       if (Trace.isOn) {
/* 4652 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveNoWait()", (Throwable)je);
/*      */       }
/*      */       
/* 4655 */       if (Trace.isOn) {
/* 4656 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "receiveNoWait()", (Throwable)je);
/*      */       }
/*      */       
/* 4659 */       throw je;
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
/*      */   private void removeBadMessage(MQJMSMessage msg, int reason) throws JMSException {
/* 4673 */     if (Trace.isOn) {
/* 4674 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "removeBadMessage(MQJMSMessage,int)", new Object[] { msg, 
/* 4675 */             Integer.valueOf(reason) });
/*      */     }
/*      */     
/*      */     try {
/* 4679 */       if ((msg.getReport() & 0x8000000) == 134217728) {
/* 4680 */         if (Trace.isOn) {
/* 4681 */           Trace.traceData(this, "Discarding message as per MQMD.Report", null);
/*      */         }
/* 4683 */         discard(msg, reason);
/*      */       } else {
/*      */         
/* 4686 */         if (Trace.isOn) {
/* 4687 */           Trace.traceData(this, "Dead-lettering message as per MQMD.Report", null);
/*      */         }
/* 4689 */         deadLetter(msg, reason);
/*      */       }
/*      */     
/* 4692 */     } catch (JMSException jmse) {
/* 4693 */       if (Trace.isOn) {
/* 4694 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "removeBadMessage(MQJMSMessage,int)", (Throwable)jmse);
/*      */       }
/*      */       
/* 4697 */       if (Trace.isOn) {
/* 4698 */         Trace.traceData(this, "throwing " + jmse, null);
/*      */       }
/* 4700 */       if (Trace.isOn) {
/* 4701 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "removeBadMessage(MQJMSMessage,int)", (Throwable)jmse);
/*      */       }
/*      */       
/* 4704 */       throw jmse;
/*      */     } 
/*      */     
/* 4707 */     if (Trace.isOn) {
/* 4708 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "removeBadMessage(MQJMSMessage,int)");
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
/*      */   private boolean removeBrowsedMessage(boolean messageSuitable) throws JMSException {
/* 4728 */     if (Trace.isOn) {
/* 4729 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "removeBrowsedMessage(boolean)", new Object[] {
/* 4730 */             Boolean.valueOf(messageSuitable)
/*      */           });
/*      */     }
/*      */     
/*      */     try {
/* 4735 */       boolean success = true;
/*      */       
/* 4737 */       int currentOptions = this.gmo.options;
/* 4738 */       if (messageSuitable) {
/* 4739 */         this.gmo.options = this.acceptOptions;
/*      */       } else {
/*      */         
/* 4742 */         this.gmo.options = this.deleteOptions;
/*      */       } 
/*      */       
/*      */       try {
/* 4746 */         int rc = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4753 */         if (this.backoutThreshold != 0 && this.baseMessage.getBackoutCount() >= this.backoutThreshold) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 4758 */           this.gmo.options = this.acceptOptions;
/*      */ 
/*      */           
/* 4761 */           this.gmo.options |= getSyncPointOptions();
/* 4762 */           this.gmo.options &= 0xFFFFFFBF;
/*      */           
/* 4764 */           rc = this.queue.getMsg2NoExc(this.baseMessage, this.gmo);
/* 4765 */           success = true;
/*      */         } else {
/*      */           
/* 4768 */           rc = this.queue.getMsg2NoExc(this.baseMessage, this.gmo, 1);
/* 4769 */           success = true;
/*      */         } 
/*      */ 
/*      */         
/* 4773 */         if (rc == 2079)
/*      */         {
/* 4775 */           if (Trace.isOn) {
/* 4776 */             Trace.traceData(this, "Accepted truncated message. Expected result", null);
/*      */           }
/* 4778 */           success = true;
/*      */         }
/* 4780 */         else if (rc == 2034)
/*      */         {
/*      */ 
/*      */           
/* 4784 */           success = false;
/*      */           
/* 4786 */           if (Trace.isOn) {
/* 4787 */             Trace.traceData(this, "couldn't dequeue message - message expired or competing consumers?", null);
/*      */           }
/*      */         }
/* 4790 */         else if (rc == 2033)
/*      */         {
/*      */           
/* 4793 */           success = false;
/*      */           
/* 4795 */           if (Trace.isOn) {
/* 4796 */             Trace.traceData(this, "couldn't dequeue message - message expired or competing consumers?", null);
/*      */           }
/*      */         }
/* 4799 */         else if (rc != 0)
/*      */         {
/* 4801 */           if (Trace.isOn) {
/* 4802 */             Trace.traceData(this, "Unexpected reason code from getMsg2()", null);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 4822 */           MQException mqe = new MQException(-1, rc, this);
/*      */ 
/*      */ 
/*      */           
/* 4826 */           JMSException je = ConfigEnvironment.newException("MQJMS2002");
/* 4827 */           je.setLinkedException((Exception)mqe);
/*      */           
/* 4829 */           if (Trace.isOn) {
/* 4830 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "removeBrowsedMessage(boolean)", (Throwable)je, 1);
/*      */           }
/*      */           
/* 4833 */           throw je;
/*      */         }
/*      */       
/*      */       }
/*      */       finally {
/*      */         
/* 4839 */         if (Trace.isOn) {
/* 4840 */           Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "removeBrowsedMessage(boolean)");
/*      */         }
/*      */ 
/*      */         
/* 4844 */         this.gmo.options = currentOptions;
/*      */       } 
/*      */       
/* 4847 */       if (Trace.isOn) {
/* 4848 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "removeBrowsedMessage(boolean)", 
/* 4849 */             Boolean.valueOf(success));
/*      */       }
/* 4851 */       return success;
/*      */     
/*      */     }
/* 4854 */     catch (JMSException je) {
/* 4855 */       if (Trace.isOn) {
/* 4856 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "removeBrowsedMessage(boolean)", (Throwable)je);
/*      */       }
/*      */       
/* 4859 */       if (Trace.isOn) {
/* 4860 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "removeBrowsedMessage(boolean)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 4863 */       throw je;
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
/*      */   public void setMessageListener(ProviderMessageListener listener) throws JMSException {
/* 4882 */     if (Trace.isOn) {
/* 4883 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageListener(ProviderMessageListener)", "setter", listener);
/*      */     }
/*      */ 
/*      */     
/* 4887 */     if (this.setForPTP) {
/* 4888 */       setMessageListenerQ(listener);
/*      */     }
/* 4890 */     else if (this.setForPubSub) {
/* 4891 */       setMessageListenerT(listener);
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
/*      */   private synchronized void setMessageListenerQ(ProviderMessageListener listener) throws JMSException {
/* 4919 */     if (Trace.isOn) {
/* 4920 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageListenerQ(ProviderMessageListener)", "setter", listener);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 4926 */       if (this.closed || this.session == null) {
/* 4927 */         String msg = ConfigEnvironment.getErrorMessage("MQJMS3033");
/* 4928 */         IllegalStateException illegalStateException = new IllegalStateException(msg);
/* 4929 */         if (Trace.isOn) {
/* 4930 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageListenerQ(ProviderMessageListener)", (Throwable)illegalStateException, 1);
/*      */         }
/*      */         
/* 4933 */         throw illegalStateException;
/*      */       } 
/*      */ 
/*      */       
/* 4937 */       if (listener != null && this.session.hasMessageListener()) {
/* 4938 */         JMSException je = ConfigEnvironment.newException("MQJMS1013");
/* 4939 */         if (Trace.isOn) {
/* 4940 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageListenerQ(ProviderMessageListener)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 4943 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 4947 */       this.listener = listener;
/*      */       
/* 4949 */       if (listener == null) {
/* 4950 */         this.session.removeAsync(this);
/*      */       } else {
/*      */         
/* 4953 */         this.session.addAsync(this);
/*      */       }
/*      */     
/* 4956 */     } catch (JMSException je) {
/* 4957 */       if (Trace.isOn) {
/* 4958 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageListenerQ(ProviderMessageListener)", (Throwable)je);
/*      */       }
/*      */       
/* 4961 */       if (Trace.isOn) {
/* 4962 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageListenerQ(ProviderMessageListener)", (Throwable)je, 3);
/*      */       }
/*      */       
/* 4965 */       throw je;
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
/*      */   private synchronized void setMessageListenerT(ProviderMessageListener listener) throws JMSException {
/* 4989 */     if (Trace.isOn) {
/* 4990 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageListenerT(ProviderMessageListener)", "setter", listener);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 4997 */       if (this.closed || this.session == null) {
/* 4998 */         String msg = ConfigEnvironment.getErrorMessage("MQJMS3034");
/* 4999 */         IllegalStateException illegalStateException = new IllegalStateException(msg);
/* 5000 */         if (Trace.isOn) {
/* 5001 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageListenerT(ProviderMessageListener)", (Throwable)illegalStateException, 1);
/*      */         }
/*      */         
/* 5004 */         throw illegalStateException;
/*      */       } 
/*      */ 
/*      */       
/* 5008 */       if (listener != null && this.session.hasMessageListener()) {
/* 5009 */         JMSException je = ConfigEnvironment.newException("MQJMS1013");
/* 5010 */         if (Trace.isOn) {
/* 5011 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageListenerT(ProviderMessageListener)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 5014 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 5018 */       this.listener = listener;
/*      */       
/* 5020 */       if (listener == null) {
/* 5021 */         this.session.removeAsync(this);
/*      */       } else {
/*      */         
/* 5024 */         this.session.addAsync(this);
/*      */       }
/*      */     
/*      */     }
/* 5028 */     catch (JMSException je) {
/* 5029 */       if (Trace.isOn) {
/* 5030 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageListenerT(ProviderMessageListener)", (Throwable)je);
/*      */       }
/*      */       
/* 5033 */       if (Trace.isOn) {
/* 5034 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageListenerT(ProviderMessageListener)", (Throwable)je, 3);
/*      */       }
/*      */       
/* 5037 */       throw je;
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
/*      */   private void setMessageSelector(String selectorP) throws InvalidSelectorException {
/* 5050 */     if (Trace.isOn) {
/* 5051 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageSelector(String)", "setter", selectorP);
/*      */     }
/*      */ 
/*      */     
/* 5055 */     String selector = selectorP;
/*      */ 
/*      */     
/*      */     try {
/* 5059 */       if (this.messageSelector == null) {
/* 5060 */         this.messageSelector = new MQMessageSelector();
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/* 5065 */         if (selector != null) {
/*      */           
/* 5067 */           if (selector.equals("\000")) {
/* 5068 */             SyntaxException traceRet1 = new SyntaxException();
/* 5069 */             if (Trace.isOn) {
/* 5070 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageSelector(String)", (Throwable)traceRet1, 1);
/*      */             }
/*      */             
/* 5073 */             throw traceRet1;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 5078 */           boolean allChar = false;
/* 5079 */           boolean keepChecking = true;
/* 5080 */           int i = 0;
/*      */           
/* 5082 */           while (keepChecking && i < selector.length()) {
/* 5083 */             if (selector.charAt(i) == ' ') {
/* 5084 */               allChar = true;
/*      */             } else {
/*      */               
/* 5087 */               allChar = false;
/* 5088 */               keepChecking = false;
/*      */             } 
/* 5090 */             i++;
/*      */           } 
/*      */ 
/*      */           
/* 5094 */           if (selector.length() == 0 || allChar) {
/* 5095 */             selector = null;
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 5100 */         this.messageSelector.setSelector(selector);
/*      */       
/*      */       }
/* 5103 */       catch (SyntaxException e) {
/* 5104 */         if (Trace.isOn) {
/* 5105 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageSelector(String)", (Throwable)e, 1);
/*      */         }
/*      */         
/* 5108 */         String key = "MQJMS0004";
/* 5109 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 5110 */         InvalidSelectorException je = new InvalidSelectorException(msg, key);
/* 5111 */         je.setLinkedException((Exception)e);
/* 5112 */         je.initCause((Throwable)e);
/* 5113 */         if (Trace.isOn) {
/* 5114 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageSelector(String)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 5117 */         throw je;
/*      */       }
/*      */     
/* 5120 */     } catch (InvalidSelectorException je) {
/* 5121 */       if (Trace.isOn) {
/* 5122 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageSelector(String)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 5125 */       if (Trace.isOn) {
/* 5126 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageSelector(String)", (Throwable)je, 3);
/*      */       }
/*      */       
/* 5129 */       throw je;
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
/*      */   private boolean setMessageSelector(String selectorP, MQMsg2 message, MQGetMessageOptions gmo) throws InvalidSelectorException {
/* 5153 */     if (Trace.isOn) {
/* 5154 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageSelector(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions)", new Object[] { selectorP, message, gmo });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 5159 */     String selector = selectorP;
/*      */ 
/*      */     
/*      */     try {
/*      */       boolean result;
/*      */       
/* 5165 */       if (this.messageSelector == null) {
/* 5166 */         this.messageSelector = new MQMessageSelector();
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/* 5171 */         if (selector != null) {
/*      */           
/* 5173 */           if (selector.equals("\000")) {
/* 5174 */             SyntaxException traceRet1 = new SyntaxException();
/* 5175 */             if (Trace.isOn) {
/* 5176 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageSelector(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions)", (Throwable)traceRet1, 1);
/*      */             }
/*      */ 
/*      */             
/* 5180 */             throw traceRet1;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 5185 */           boolean allChar = false;
/* 5186 */           boolean keepChecking = true;
/* 5187 */           int i = 0;
/*      */           
/* 5189 */           while (keepChecking && i < selector.length()) {
/* 5190 */             if (selector.charAt(i) == ' ') {
/* 5191 */               allChar = true;
/*      */             } else {
/*      */               
/* 5194 */               allChar = false;
/* 5195 */               keepChecking = false;
/*      */             } 
/* 5197 */             i++;
/*      */           } 
/*      */ 
/*      */           
/* 5201 */           if (selector.length() == 0 || allChar) {
/* 5202 */             selector = null;
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 5207 */         result = this.messageSelector.setSelector(selector, message, gmo);
/*      */       
/*      */       }
/* 5210 */       catch (SyntaxException e) {
/* 5211 */         if (Trace.isOn) {
/* 5212 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageSelector(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions)", (Throwable)e, 1);
/*      */         }
/*      */ 
/*      */         
/* 5216 */         String key = "MQJMS0004";
/* 5217 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 5218 */         InvalidSelectorException je = new InvalidSelectorException(msg, key);
/* 5219 */         je.setLinkedException((Exception)e);
/* 5220 */         je.initCause((Throwable)e);
/* 5221 */         if (Trace.isOn) {
/* 5222 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageSelector(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */         
/* 5226 */         throw je;
/*      */       } 
/*      */       
/* 5229 */       if (Trace.isOn) {
/* 5230 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageSelector(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions)", 
/*      */             
/* 5232 */             Boolean.valueOf(result));
/*      */       }
/* 5234 */       return result;
/*      */     
/*      */     }
/* 5237 */     catch (InvalidSelectorException je) {
/* 5238 */       if (Trace.isOn) {
/* 5239 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageSelector(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */       
/* 5243 */       if (Trace.isOn) {
/* 5244 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setMessageSelector(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */       
/* 5248 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setSyncpointBehaviour(boolean forceSPIP) {
/* 5258 */     if (Trace.isOn) {
/* 5259 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "setSyncpointBehaviour(boolean)", "setter", 
/* 5260 */           Boolean.valueOf(forceSPIP));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5270 */     boolean alwaysSync = this.session.getSyncpointAllGets();
/*      */     
/* 5272 */     if (this.transacted || alwaysSync || this.acknowledgeMode == 2 || this.session.getDistTransactionMode() == 2) {
/*      */       
/* 5274 */       this.syncPoint = 1;
/* 5275 */       this.getOptions |= 0x2;
/* 5276 */       this.acceptOptions |= 0x2;
/* 5277 */       if (Trace.isOn) {
/* 5278 */         Trace.traceData(this, "syncpoint enabled", null);
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 5283 */     else if (this.durable || forceSPIP) {
/*      */       
/* 5285 */       this.syncPoint = 2;
/* 5286 */       this.getOptions |= 0x1000;
/* 5287 */       this.acceptOptions |= 0x1000;
/* 5288 */       if (Trace.isOn) {
/* 5289 */         Trace.traceData(this, "using syncpoint if persistent", null);
/*      */ 
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 5298 */       this.syncPoint = 3;
/* 5299 */       this.getOptions |= 0x4;
/*      */ 
/*      */       
/* 5302 */       this.acceptOptions |= 0x4;
/* 5303 */       if (Trace.isOn) {
/* 5304 */         Trace.traceData(this, "syncpoint disabled", null);
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
/*      */   public void start(boolean deliverImmediately) throws JMSException {
/* 5317 */     if (Trace.isOn) {
/* 5318 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "start(boolean)", new Object[] {
/* 5319 */             Boolean.valueOf(deliverImmediately)
/*      */           });
/*      */     }
/* 5322 */     this.running = true;
/*      */     
/* 5324 */     if (Trace.isOn) {
/* 5325 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "start(boolean)");
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
/*      */   public void stop() throws JMSException {
/* 5337 */     if (Trace.isOn) {
/* 5338 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "stop()");
/*      */     }
/* 5340 */     this.running = false;
/* 5341 */     if (Trace.isOn) {
/* 5342 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "stop()");
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
/*      */   public boolean equals(Object o) {
/* 5356 */     if (Trace.isOn) {
/* 5357 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "equals(Object)", new Object[] { o });
/*      */     }
/*      */ 
/*      */     
/* 5361 */     boolean equals = super.equals(o);
/*      */     
/* 5363 */     if (equals)
/*      */     {
/* 5365 */       if (o instanceof MQMessageConsumer) {
/*      */         
/* 5367 */         if (this.listener != null)
/*      */         {
/* 5369 */           equals = this.listener.equals(((MQMessageConsumer)o).listener);
/*      */         }
/*      */         else
/*      */         {
/* 5373 */           equals = (((MQMessageConsumer)o).listener == null);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 5378 */         equals = false;
/*      */       } 
/*      */     }
/*      */     
/* 5382 */     if (Trace.isOn) {
/* 5383 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "equals(Object)", 
/* 5384 */           Boolean.valueOf(equals));
/*      */     }
/* 5386 */     return equals;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 5395 */     if (Trace.isOn) {
/* 5396 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "hashCode()");
/*      */     }
/*      */     
/* 5399 */     int traceRet1 = super.hashCode();
/* 5400 */     if (Trace.isOn) {
/* 5401 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "hashCode()", 
/* 5402 */           Integer.valueOf(traceRet1));
/*      */     }
/* 5404 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 5413 */     if (Trace.isOn) {
/* 5414 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "readObject(ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */     
/* 5417 */     NotSerializableException traceRet1 = new NotSerializableException();
/* 5418 */     if (Trace.isOn) {
/* 5419 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "readObject(ObjectInputStream)", traceRet1);
/*      */     }
/*      */     
/* 5422 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 5431 */     if (Trace.isOn) {
/* 5432 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "writeObject(ObjectOutputStream)", new Object[] { out });
/*      */     }
/*      */     
/* 5435 */     NotSerializableException traceRet1 = new NotSerializableException();
/* 5436 */     if (Trace.isOn) {
/* 5437 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "writeObject(ObjectOutputStream)", traceRet1);
/*      */     }
/*      */     
/* 5440 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ProviderMessage lockMessage(long timeout) throws JMSException {
/* 5449 */     if (Trace.isOn)
/* 5450 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "lockMessage(long)", new Object[] {
/* 5451 */             Long.valueOf(timeout)
/*      */           }); 
/* 5453 */     JMSException jmse = ConfigEnvironment.newException("MQJMS1016");
/* 5454 */     if (Trace.isOn) {
/* 5455 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "lockMessage(long)", (Throwable)jmse);
/*      */     }
/*      */     
/* 5458 */     throw jmse;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeLockedMessage(ProviderMessage msg) throws JMSException {
/* 5466 */     if (Trace.isOn) {
/* 5467 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "removeLockedMessage(ProviderMessage)", new Object[] { msg });
/*      */     }
/*      */     
/* 5470 */     JMSException jmse = ConfigEnvironment.newException("MQJMS1016");
/* 5471 */     if (Trace.isOn) {
/* 5472 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "removeLockedMessage(ProviderMessage)", (Throwable)jmse);
/*      */     }
/*      */     
/* 5475 */     throw jmse;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unlockMessage() throws JMSException {
/* 5483 */     if (Trace.isOn) {
/* 5484 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "unlockMessage()");
/*      */     }
/*      */     
/* 5487 */     JMSException jmse = ConfigEnvironment.newException("MQJMS1016");
/* 5488 */     if (Trace.isOn) {
/* 5489 */       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "unlockMessage()", (Throwable)jmse);
/*      */     }
/*      */     
/* 5492 */     throw jmse;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dump(PrintWriter pw, int level) {
/* 5500 */     if (Trace.isOn) {
/* 5501 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "dump(PrintWriter,int)", new Object[] { pw, 
/* 5502 */             Integer.valueOf(level) });
/*      */     }
/* 5504 */     StringBuilder prefix = new StringBuilder();
/* 5505 */     for (int i = 0; i < level; i++) {
/* 5506 */       prefix.append("  ");
/*      */     }
/* 5508 */     pw.format("%s%s\n", new Object[] { prefix, toString() });
/* 5509 */     if (this.session == null) {
/* 5510 */       pw.format("%s  Parent Session <null>\n", new Object[] { prefix });
/*      */     } else {
/*      */       
/* 5513 */       pw.format("%s  Parent Session %s\n", new Object[] { prefix, this.session.getClass().getName() + '@' + Integer.toHexString(this.session.hashCode()) });
/*      */     } 
/*      */     
/* 5516 */     pw.format("%s  destination %s\n", new Object[] { prefix, String.valueOf(this.destination) });
/* 5517 */     if (Trace.isOn)
/* 5518 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageConsumer", "dump(PrintWriter,int)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQMessageConsumer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */