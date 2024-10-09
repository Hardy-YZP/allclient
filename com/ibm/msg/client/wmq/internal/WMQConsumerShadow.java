/*      */ package com.ibm.msg.client.wmq.internal;
/*      */ 
/*      */ import com.ibm.mq.constants.CMQC;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.JmqiUtils;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.MQSD;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Hobj;
/*      */ import com.ibm.mq.jmqi.handles.PbyteBuffer;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.system.JmqiSP;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.LpiSD;
/*      */ import com.ibm.mq.jmqi.system.LpiSDSubProps;
/*      */ import com.ibm.mq.jmqi.system.SpiOpenOptions;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.commonservices.util.Cruise;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessageListener;
/*      */ import com.ibm.msg.client.wmq.common.internal.Reason;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQConsumerOwner;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQHobjCache;
/*      */ import com.ibm.msg.client.wmq.common.internal.messages.WMQMarshal;
/*      */ import com.ibm.msg.client.wmq.common.internal.messages.WMQMessage;
/*      */ import com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.charset.Charset;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.concurrent.atomic.AtomicBoolean;
/*      */ import java.util.concurrent.locks.ReentrantLock;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
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
/*      */ abstract class WMQConsumerShadow
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQConsumerShadow.java";
/*      */   public static final String receiveConversionCCSIDPropertyName = "com.ibm.msg.client.wmq.receiveConversionCCSID";
/*      */   
/*      */   static {
/*   83 */     if (Trace.isOn) {
/*   84 */       Trace.data("com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQConsumerShadow.java");
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
/*  101 */   private int receiveCCSID = 0;
/*      */   private static final long defaultCCSID = 1208L;
/*  103 */   private static final Long MIN_VALID_CCSID = Long.valueOf(-1L);
/*  104 */   private static final Long MAX_VALID_CCSID = Long.valueOf(2147483647L);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  109 */   protected int gmoConvertOption = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isBrowser = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final int MSG_LENGTH_NOT_KNOWN_IN_ADVANCE = -1;
/*      */ 
/*      */ 
/*      */   
/*  123 */   private static final byte[] MQMI_NONE = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*      */   
/*  125 */   protected Hconn hconn = null;
/*  126 */   protected Hobj hobj = null;
/*  127 */   protected JmqiMQ mq = null;
/*  128 */   protected Phobj phobj = null;
/*  129 */   protected Phobj phsub = null;
/*  130 */   protected MQMD mqmd = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  136 */   protected WMQGMO cachedGmo = null;
/*      */ 
/*      */ 
/*      */   
/*  140 */   protected WMQReceiveMarshal receiveMarshal = null;
/*      */   
/*      */   protected WMQConsumerOwner helper;
/*      */   protected WMQDestination destination;
/*  144 */   protected JmqiEnvironment env = null;
/*      */   
/*      */   protected String selector;
/*      */   
/*      */   protected SelectionDetails selectionDetails;
/*      */   
/*      */   protected String subscriptionName;
/*      */   
/*      */   protected boolean transacted;
/*      */   
/*      */   protected int ackMode;
/*      */   
/*      */   protected boolean nolocal;
/*      */   
/*      */   protected boolean shared;
/*      */   protected boolean durable;
/*      */   private boolean running = true;
/*  161 */   protected JmsPropertyContext jmsProps = null;
/*      */ 
/*      */   
/*  164 */   protected WMQPoison poison = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected WMQHobjCache hObjCache;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean hObjCached = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  180 */   private Pint dataLength = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  185 */   private PbyteBuffer getMessageDataBuffer = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  191 */   private Pint msgsTooSmallForBuffer = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  197 */   protected LpiSD spisd = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] subID;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  207 */   protected int receiveCount = 0;
/*      */   
/*  209 */   protected long lastReceiveTime = 0L;
/*      */   
/*  211 */   private AtomicBoolean isReceiveInProgress = new AtomicBoolean(false);
/*  212 */   private AtomicBoolean closeCalled = new AtomicBoolean(false);
/*  213 */   private Object receiveCloseLock = new Object();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   WMQConsumerShadow(JmsPropertyContext jmsProps, WMQConsumerOwner helper, WMQDestination destination, String selector, boolean nolocal, boolean shared, boolean durable, String subscriptionName, byte[] subID) {
/*  233 */     if (Trace.isOn) {
/*  234 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "<init>(JmsPropertyContext,WMQConsumerOwner,WMQDestination,String,boolean,boolean,boolean,String,byte [ ])", new Object[] { jmsProps, helper, destination, selector, 
/*      */             
/*  236 */             Boolean.valueOf(nolocal), 
/*  237 */             Boolean.valueOf(shared), Boolean.valueOf(durable), subscriptionName, subID });
/*      */     }
/*  239 */     this.helper = helper;
/*  240 */     this.destination = destination;
/*  241 */     this.selector = selector;
/*  242 */     this.selectionDetails = SelectionDetails.getSelectionDetails(selector);
/*  243 */     this.subscriptionName = subscriptionName;
/*  244 */     this.env = helper.getJmqiEnvironment();
/*  245 */     this.hconn = helper.getHconn();
/*  246 */     this.mq = helper.getJmqiMQ();
/*  247 */     this.transacted = helper.getTransacted();
/*  248 */     this.ackMode = helper.getAckMode();
/*  249 */     this.jmsProps = jmsProps;
/*  250 */     this.nolocal = nolocal;
/*  251 */     this.shared = shared;
/*  252 */     this.durable = durable;
/*      */     
/*  254 */     this.msgsTooSmallForBuffer = this.env.newPint();
/*  255 */     this.getMessageDataBuffer = this.env.newPbyteBuffer();
/*  256 */     this.subID = subID;
/*      */ 
/*      */     
/*  259 */     checkSetGMOCONVERT();
/*      */ 
/*      */     
/*  262 */     this.hObjCache = helper.getHobjCache();
/*      */     
/*  264 */     if (Trace.isOn) {
/*  265 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "<init>(JmsPropertyContext,WMQConsumerOwner,WMQDestination,String,boolean,boolean,boolean,String,byte [ ])");
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
/*      */   void closeInternal(ReentrantLock onMessageLock, boolean closeHobj) throws JMSException {
/*  285 */     if (Trace.isOn) {
/*  286 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "closeInternal(ReentrantLock,boolean)", new Object[] { onMessageLock, 
/*      */             
/*  288 */             Boolean.valueOf(closeHobj) });
/*      */     }
/*  290 */     closeInternal(closeHobj);
/*  291 */     if (Trace.isOn) {
/*  292 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "closeInternal(ReentrantLock,boolean)");
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
/*      */   void close() throws JMSException {
/*  304 */     if (Trace.isOn) {
/*  305 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "close()");
/*      */     }
/*  307 */     close(null);
/*  308 */     if (Trace.isOn) {
/*  309 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "close()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void close(ReentrantLock onMessageLock) throws JMSException {
/*  315 */     close(onMessageLock, true);
/*      */   }
/*      */ 
/*      */   
/*      */   void close(ReentrantLock onMessageLock, boolean suspendConnectionAsyncService) throws JMSException {
/*  320 */     if (Trace.isOn) {
/*  321 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "close(ReentrantLock,boolean)", new Object[] { onMessageLock, 
/*  322 */             Boolean.valueOf(suspendConnectionAsyncService) });
/*      */     }
/*      */     
/*  325 */     boolean relockMessageLock = false;
/*      */     try {
/*  327 */       synchronized (this.receiveCloseLock) {
/*  328 */         this.closeCalled.set(true);
/*      */       } 
/*      */       
/*  331 */       Pint rc = this.env.newPint();
/*  332 */       Pint cc = this.env.newPint();
/*      */ 
/*      */ 
/*      */       
/*  336 */       boolean asfMode = (this.helper == this.helper.getConnection());
/*      */ 
/*      */       
/*  339 */       this.helper.incrementCloseCounter();
/*      */       
/*  341 */       if (onMessageLock != null) {
/*  342 */         onMessageLock.unlock();
/*  343 */         relockMessageLock = true;
/*      */       } 
/*  345 */       boolean asyncDidSuspend = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  352 */       boolean connectionDidSuspend = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  359 */       JMSException savedException = null;
/*      */       
/*      */       try {
/*  362 */         asyncDidSuspend = this.helper.suspendAsyncService();
/*      */         
/*  364 */         if (!asfMode && suspendConnectionAsyncService) {
/*  365 */           connectionDidSuspend = ((WMQConnection)this.helper.getConnection()).suspendAsyncService();
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  372 */         int count = 0;
/*      */         
/*      */         do {
/*      */           String exceptionMsg;
/*  376 */           if (Trace.isOn) {
/*  377 */             HashMap<String, Object> data = new HashMap<>();
/*  378 */             data.put("isReceiveInProgress", this.isReceiveInProgress.toString());
/*  379 */             data.put("count", Integer.valueOf(count));
/*  380 */             Trace.traceData(this, "Entering jmqiNotify loop", data);
/*      */           } 
/*      */ 
/*      */           
/*  384 */           ((JmqiSP)this.mq).jmqiCancelWaitingGets(((WMQConnection)this.helper.getConnection()).getHconn(), this.hconn, this.helper.getHconn(), cc, rc);
/*      */ 
/*      */           
/*  387 */           switch (rc.x) {
/*      */             case 0:
/*      */             case 2107:
/*      */               break;
/*      */             
/*      */             default:
/*  393 */               exceptionMsg = this.destination.isTopic() ? "JMSWMQ2003" : "JMSWMQ2000";
/*      */               
/*  395 */               WMQMessageConsumer.checkJmqiCallSuccess(exceptionMsg, this.destination
/*  396 */                   .getName(), "XMSC_DESTINATION_NAME", cc, rc, this.env, "XN00P001", this.helper, this.hconn);
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
/*  407 */         } while (this.isReceiveInProgress.get() && count++ < 3);
/*      */       
/*      */       }
/*  410 */       catch (JMSException je) {
/*  411 */         if (Trace.isOn) {
/*  412 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "close(ReentrantLock,boolean)", (Throwable)je);
/*      */         }
/*      */         
/*  415 */         savedException = je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  420 */       this.helper.lockHconn();
/*      */       
/*      */       try {
/*      */         try {
/*  424 */           if (savedException != null) {
/*  425 */             if (Trace.isOn) {
/*  426 */               Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "close(ReentrantLock,boolean)", (Throwable)savedException);
/*      */             }
/*      */             
/*  429 */             throw savedException;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           try {
/*  437 */             boolean closeHobj = true;
/*  438 */             if (this.hObjCached) {
/*  439 */               closeHobj = (this.hObjCache.countQueue(this.phobj) == 1);
/*      */             }
/*      */ 
/*      */             
/*  443 */             closeInternal(null, closeHobj);
/*      */ 
/*      */             
/*  446 */             if (this.hObjCached && 
/*  447 */               this.hObjCache.removeQueue(this.phobj)) {
/*  448 */               this.hObjCached = false;
/*      */             }
/*      */           }
/*      */           finally {
/*      */             
/*  453 */             if (Trace.isOn) {
/*  454 */               Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "close(ReentrantLock,boolean)", 1);
/*      */             }
/*      */           }
/*      */         
/*      */         }
/*      */         finally {
/*      */           
/*  461 */           if (Trace.isOn) {
/*  462 */             Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "close(ReentrantLock,boolean)", 2);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  468 */           this.helper.decrementCloseCounter();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           try {
/*  476 */             if (!asfMode && connectionDidSuspend) {
/*  477 */               ((WMQConnection)this.helper.getConnection()).resumeAsyncService();
/*      */             }
/*      */ 
/*      */             
/*  481 */             if (asyncDidSuspend) {
/*  482 */               this.helper.resumeAsyncService();
/*      */             
/*      */             }
/*      */           }
/*      */           finally {
/*      */             
/*  488 */             this.helper.signalHconn();
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  494 */           if (this.subscriptionName != null) {
/*  495 */             this.helper.removeSubscription(this.subscriptionName);
/*      */           }
/*      */         }
/*      */       
/*      */       } finally {
/*      */         
/*  501 */         this.helper.unlockHconn();
/*      */       } 
/*      */     } finally {
/*      */       
/*  505 */       if (Trace.isOn) {
/*  506 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "close(ReentrantLock,boolean)", 3);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  511 */       synchronized (this.receiveCloseLock) {
/*  512 */         this.closeCalled.set(true);
/*      */       } 
/*      */       
/*  515 */       if (relockMessageLock)
/*      */       {
/*  517 */         onMessageLock.lock();
/*      */       }
/*      */     } 
/*  520 */     if (Trace.isOn) {
/*  521 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "close(ReentrantLock,boolean)");
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
/*      */   WMQGMO computeGMO(int waitTime) throws JMSException {
/*  537 */     if (Trace.isOn) {
/*  538 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "computeGMO(int)", new Object[] {
/*  539 */             Integer.valueOf(waitTime)
/*      */           });
/*      */     }
/*      */     
/*  543 */     if (this.receiveMarshal == null) {
/*  544 */       initializeReceiveMarshal();
/*      */     }
/*      */ 
/*      */     
/*  548 */     if (this.cachedGmo == null) {
/*      */       
/*  550 */       this.cachedGmo = new WMQGMO(this.env);
/*  551 */       int getOptions = 0;
/*      */ 
/*      */ 
/*      */       
/*  555 */       boolean alwaysSync = this.jmsProps.getBooleanProperty("XMSC_WMQ_SYNCPOINT_ALL_GETS");
/*      */       
/*  557 */       if (this.transacted || alwaysSync || this.ackMode == 2) {
/*      */         
/*  559 */         getOptions |= 0x2;
/*      */       }
/*      */       else {
/*      */         
/*  563 */         getOptions |= 0x1000;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  568 */         int fiq = this.destination.getIntProperty("failIfQuiesce");
/*      */         
/*  570 */         if (fiq == 1) {
/*  571 */           if (Trace.isOn) {
/*  572 */             Trace.data(this, "Get fail-if-quiesce = yes", null);
/*      */           }
/*  574 */           getOptions |= 0x2000;
/*      */         } else {
/*      */           
/*  577 */           if (Trace.isOn) {
/*  578 */             Trace.data(this, "Get fail-if-quiesce = no", null);
/*      */           }
/*  580 */           getOptions &= 0xFFFFDFFF;
/*      */         }
/*      */       
/*  583 */       } catch (JMSException jmsex) {
/*  584 */         if (Trace.isOn) {
/*  585 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "computeGMO(int)", (Throwable)jmsex);
/*      */         }
/*      */         
/*  588 */         if (Trace.isOn) {
/*  589 */           Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "computeGMO(int)", (Throwable)jmsex);
/*      */         }
/*      */         
/*  592 */         throw jmsex;
/*      */       } 
/*      */       
/*  595 */       getOptions |= 0x1;
/*  596 */       getOptions |= 0x2000000;
/*  597 */       getOptions |= this.gmoConvertOption;
/*      */ 
/*      */ 
/*      */       
/*  601 */       if (this.receiveMarshal instanceof com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshalMH) {
/*  602 */         if (this.cachedGmo.getVersion() < 4) {
/*  603 */           this.cachedGmo.setVersion(4);
/*      */         }
/*  605 */         getOptions &= 0xFDFFFFFF;
/*  606 */         getOptions |= 0x8000000;
/*  607 */         long messageHandleDummy = (System.identityHashCode(this.hconn) | 0x2);
/*  608 */         this.cachedGmo.setMessageHandle(messageHandleDummy);
/*      */       } 
/*      */       
/*  611 */       this.cachedGmo.setOptions(getOptions);
/*      */       
/*  613 */       setMatchOptions(this.selectionDetails, this.cachedGmo);
/*      */     
/*      */     }
/*  616 */     else if (Trace.isOn) {
/*  617 */       Trace.data(this, "Using cached MQGMO", this.cachedGmo);
/*      */     } 
/*      */ 
/*      */     
/*  621 */     this.cachedGmo.setWaitInterval(waitTime);
/*      */     
/*  623 */     if (Trace.isOn) {
/*  624 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "computeGMO(int)", this.cachedGmo);
/*      */     }
/*      */     
/*  627 */     return this.cachedGmo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   LpiSD computeLpiSD() throws JMSException {
/*  636 */     if (Trace.isOn) {
/*  637 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "computeLpiSD()");
/*      */     }
/*      */     
/*  640 */     LpiSD sd = ((JmqiSystemEnvironment)this.env).newSpiSD();
/*  641 */     int options = computeQueueOpenOptions();
/*  642 */     sd.setDestOpenOptions(options);
/*      */ 
/*      */     
/*  645 */     LpiSDSubProps sdSubProps = sd.getSubProps();
/*      */ 
/*      */ 
/*      */     
/*  649 */     sdSubProps.setPSPropertyStyle(3);
/*      */     
/*  651 */     int spiOptions = 1073741824;
/*      */ 
/*      */     
/*  654 */     if (this.subscriptionName != null) {
/*      */ 
/*      */ 
/*      */       
/*  658 */       if (this.shared) {
/*  659 */         spiOptions |= 0x10;
/*  660 */         spiOptions |= 0x40;
/*      */       }
/*  662 */       else if (this.durable) {
/*      */ 
/*      */         
/*  665 */         int cloneSupport = this.jmsProps.getIntProperty("XMSC_WMQ_CLONE_SUPPORT");
/*  666 */         if (cloneSupport == 1)
/*      */         {
/*      */ 
/*      */           
/*  670 */           spiOptions |= 0x10;
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
/*  683 */       sd.setOptions(spiOptions);
/*      */     } 
/*      */     
/*  686 */     if (Trace.isOn) {
/*  687 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "computeLpiSD()", sd);
/*      */     }
/*  689 */     return sd;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   SpiOpenOptions computeSpiOpenOptions(int queueOpenOptions) {
/*  698 */     if (Trace.isOn)
/*  699 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "computeSpiOpenOptions(int)", new Object[] {
/*  700 */             Integer.valueOf(queueOpenOptions)
/*      */           }); 
/*  702 */     int qoo = 0;
/*  703 */     SpiOpenOptions spiOO = ((JmqiSystemEnvironment)this.env).newSpiOpenOptions();
/*  704 */     qoo |= 0x1;
/*  705 */     qoo |= 0x2;
/*  706 */     spiOO.setOptions(queueOpenOptions);
/*  707 */     spiOO.setLpiOptions(qoo);
/*  708 */     if (Trace.isOn) {
/*  709 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "computeSpiOpenOptions(int)", spiOO);
/*      */     }
/*      */     
/*  712 */     return spiOO;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initialize() throws JMSException {
/*  720 */     if (Trace.isOn) {
/*  721 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "initialize()");
/*      */     }
/*      */     
/*  724 */     Pint rc = this.env.newPint();
/*  725 */     Pint cc = this.env.newPint();
/*      */     
/*  727 */     this.dataLength = this.env.newPint();
/*      */ 
/*      */ 
/*      */     
/*  731 */     this.mqmd = this.env.newMQMD();
/*      */     
/*  733 */     if (Trace.isOn) {
/*  734 */       Trace.data(this, "initialize()", "gmoConvertOption=" + this.gmoConvertOption + " receiveCCSID=" + this.receiveCCSID, null);
/*      */     }
/*      */     
/*  737 */     this.mqmd.setCodedCharSetId(this.receiveCCSID);
/*  738 */     this.mqmd.setVersion(2);
/*      */ 
/*      */     
/*  741 */     if (this.destination.isTopic()) {
/*      */       
/*  743 */       int subscriptionOptions = computeSubscriptionOptions();
/*      */       
/*  745 */       this.phobj = this.env.newPhobj();
/*  746 */       this.phsub = this.env.newPhobj();
/*  747 */       MQSD mqsd = this.env.newMQSD();
/*      */       
/*  749 */       this.spisd = computeLpiSD();
/*      */ 
/*      */ 
/*      */       
/*  753 */       mqsd.getResObjectString().setVsBufSize(10240);
/*  754 */       mqsd.getSelectionString().setVsBufSize(10240);
/*      */       
/*  756 */       mqsd.getObjectString().setVsString(this.destination.getName());
/*  757 */       mqsd.getObjectString().setVsBufSize(10240);
/*      */ 
/*      */       
/*  760 */       String streamName = this.jmsProps.getStringProperty("XMSC_WMQ_BROKER_PUBQ");
/*      */       
/*  762 */       if (!streamName.equals("SYSTEM.BROKER.DEFAULT.STREAM")) {
/*  763 */         mqsd.setObjectName(streamName);
/*      */       }
/*      */ 
/*      */       
/*  767 */       mqsd.getSelectionString().setVsString(this.selector);
/*  768 */       mqsd.getSelectionString().setVsBufSize(10240);
/*      */       
/*  770 */       if (this.subscriptionName != null) {
/*  771 */         mqsd.getSubName().setVsString(this.subscriptionName);
/*  772 */         mqsd.getSubName().setVsBufSize(10240);
/*      */       } 
/*      */       
/*  775 */       if (this.shared) {
/*  776 */         String userData = "Shared Subscription";
/*  777 */         mqsd.getSubUserData().setVsString(userData);
/*  778 */         mqsd.getSubUserData().setVsBufSize((userData.getBytes(Charset.defaultCharset())).length);
/*      */       } 
/*      */       
/*  781 */       mqsd.setOptions(subscriptionOptions);
/*      */ 
/*      */       
/*  784 */       this.phobj.setHobj(CMQC.jmqi_MQHO_NONE);
/*      */ 
/*      */       
/*  787 */       ((JmqiSP)this.mq).spiSubscribe(this.hconn, this.spisd, mqsd, this.phobj, this.phsub, cc, rc);
/*      */ 
/*      */       
/*  790 */       if (Trace.isOn) {
/*  791 */         StringBuilder details = new StringBuilder();
/*  792 */         details.append("Requested subscription details:\n");
/*  793 */         if (this.subscriptionName != null) {
/*  794 */           details.append("Requested SubscriptionName: ");
/*  795 */           details.append(this.subscriptionName);
/*  796 */           details.append('\n');
/*      */         } 
/*  798 */         details.append("Requested TopicString:      ");
/*  799 */         details.append(this.destination.getName());
/*  800 */         details.append('\n');
/*  801 */         details.append("Requested Stream Name:      ");
/*  802 */         details.append(streamName);
/*  803 */         details.append('\n');
/*  804 */         details.append("Requested selector:         ");
/*  805 */         details.append(this.selector);
/*  806 */         details.append('\n');
/*  807 */         details.append("Using subscriptionOptions:  ");
/*  808 */         details.append(subscriptionOptions);
/*  809 */         details.append('\n');
/*      */         
/*  811 */         Trace.data(this, "initialize()", details.toString(), null);
/*      */         
/*  813 */         details.delete(0, details.length());
/*  814 */         String reutrnedSubscriptionName = mqsd.getSubName().getVsString();
/*  815 */         String returnedTopic = mqsd.getResolvedObjectString().getVsString();
/*  816 */         String returnedStream = mqsd.getObjectName();
/*  817 */         String returnedSelectorStr = mqsd.getSelectionString().getVsString();
/*      */         
/*  819 */         details.append("Returned subscription details:\n");
/*  820 */         if (this.subscriptionName != null) {
/*  821 */           details.append("Returned SubscriptionName: ");
/*  822 */           details.append(reutrnedSubscriptionName);
/*  823 */           details.append('\n');
/*      */         } 
/*  825 */         details.append("Returned full TopicString: ");
/*  826 */         details.append(returnedTopic);
/*  827 */         details.append('\n');
/*  828 */         details.append("Returned Stream Name:      ");
/*  829 */         details.append(returnedStream);
/*  830 */         details.append('\n');
/*  831 */         details.append("Returned selector:         ");
/*  832 */         details.append(this.selector);
/*  833 */         details.append('\n');
/*  834 */         details.append("Returned subscriptionOptions:  ");
/*  835 */         details.append(returnedSelectorStr);
/*  836 */         details.append('\n');
/*  837 */         details.append("CompletionCode = ");
/*  838 */         details.append(cc.x);
/*  839 */         details.append('\n');
/*  840 */         details.append("ReasonnCode = ");
/*  841 */         details.append(rc.x);
/*  842 */         details.append('\n');
/*      */         
/*  844 */         Trace.data(this, "initialize()", details.toString(), null);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  849 */       if (rc.x == 29440) {
/*      */ 
/*      */         
/*  852 */         HashMap<String, String> hashMap = new HashMap<>();
/*  853 */         hashMap.put("XMSC_SUBSCRIPTION_NAME", this.subscriptionName);
/*  854 */         hashMap.put("XMSC_DESTINATION_NAME", this.destination.getName());
/*  855 */         JMSException je = Reason.createException("JMSWMQ2025", hashMap, 2432, 2, this.env);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  861 */         if (Trace.isOn) {
/*  862 */           Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "initialize()", (Throwable)je, 1);
/*      */         }
/*      */         
/*  865 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  874 */       if (this.subscriptionName != null) {
/*  875 */         boolean detailsDifferent = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  883 */         if (rc.x == 2510 || rc.x == 2524) {
/*  884 */           detailsDifferent = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  897 */         else if (rc.x == 2533 || rc.x == 2515) {
/*  898 */           if (Trace.isOn) {
/*  899 */             if (rc.x == 2533) {
/*  900 */               Trace.data(this, "initialize()", "Call to ALTER sub failed with MQRC_DEST_CLASS_NOT_ALTERABLE. Attempting to RESUME sub instead.", null);
/*      */             
/*      */             }
/*      */             else {
/*      */ 
/*      */               
/*  906 */               Trace.data(this, "initialize()", "Call to ALTER sub failed with MQRC_GROUPING_ALTERABLE. Attempting to RESUME sub instead.", null);
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  913 */           subscriptionOptions &= 0xFFFFFFFE;
/*  914 */           subscriptionOptions |= 0x4;
/*  915 */           mqsd.setOptions(subscriptionOptions);
/*      */ 
/*      */           
/*  918 */           ((JmqiSP)this.mq).spiSubscribe(this.hconn, this.spisd, mqsd, this.phobj, this.phsub, cc, rc);
/*      */           
/*  920 */           if (Trace.isOn) {
/*      */ 
/*      */             
/*  923 */             StringBuffer details = new StringBuffer();
/*  924 */             String returnedSubscriptionName = mqsd.getSubName().getVsString();
/*  925 */             String returnedTopic = mqsd.getResolvedObjectString().getVsString();
/*  926 */             String returnedStream = mqsd.getObjectName();
/*  927 */             String returnedSelectorStr = mqsd.getSelectionString().getVsString();
/*      */             
/*  929 */             details.append("Returned subscription details:\n");
/*  930 */             if (this.subscriptionName != null) {
/*  931 */               details.append("Returned SubscriptionName: ");
/*  932 */               details.append(returnedSubscriptionName);
/*  933 */               details.append('\n');
/*      */             } 
/*  935 */             details.append("Returned full TopicString: ");
/*  936 */             details.append(returnedTopic);
/*  937 */             details.append('\n');
/*  938 */             details.append("Returned Stream Name:      ");
/*  939 */             details.append(returnedStream);
/*  940 */             details.append('\n');
/*  941 */             details.append("Returned selector:         ");
/*  942 */             details.append(this.selector);
/*  943 */             details.append('\n');
/*  944 */             details.append("Returned subscriptionOptions:  ");
/*  945 */             details.append(returnedSelectorStr);
/*  946 */             details.append('\n');
/*  947 */             details.append("CompletionCode = ");
/*  948 */             details.append(cc.x);
/*  949 */             details.append('\n');
/*  950 */             details.append("ReasonnCode = ");
/*  951 */             details.append(rc.x);
/*  952 */             details.append('\n');
/*      */             
/*  954 */             Trace.data(this, "initialize()", details.toString(), null);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  963 */         if (detailsDifferent) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  971 */           if (!this.durable) {
/*      */             
/*  973 */             HashMap<String, String> hashMap = new HashMap<>();
/*  974 */             hashMap.put("XMSC_DESTINATION_NAME", this.destination.getName());
/*  975 */             hashMap.put("XMSC_SELECTOR_STRING", (this.selector == null || this.selector.equals("")) ? "none" : this.selector);
/*      */             
/*  977 */             WMQMessageConsumer.checkJmqiCallSuccess("JMSWMQ0026", (HashMap)hashMap, cc, rc, this.env, "XN00P002", this.helper, this.hconn);
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
/*  994 */           WMQMessageConsumer.deleteDurableSubscription(this.helper, this.subscriptionName);
/*      */ 
/*      */           
/*  997 */           this.phobj.setHobj(CMQC.jmqi_MQHO_NONE);
/*      */ 
/*      */ 
/*      */           
/* 1001 */           mqsd = this.env.newMQSD();
/* 1002 */           mqsd.getResObjectString().setVsBufSize(10240);
/* 1003 */           mqsd.getObjectString().setVsString(this.destination.getName());
/* 1004 */           mqsd.getObjectString().setVsBufSize(10240);
/* 1005 */           mqsd.getSelectionString().setVsString(this.selector);
/* 1006 */           mqsd.getSelectionString().setVsBufSize(10240);
/* 1007 */           mqsd.getSubName().setVsString(this.subscriptionName);
/* 1008 */           mqsd.getSubName().setVsBufSize(10240);
/* 1009 */           mqsd.setOptions(subscriptionOptions);
/*      */ 
/*      */           
/* 1012 */           if (!streamName.equals("SYSTEM.BROKER.DEFAULT.STREAM")) {
/* 1013 */             mqsd.setObjectName(streamName);
/*      */           }
/*      */ 
/*      */           
/* 1017 */           ((JmqiSP)this.mq).spiSubscribe(this.hconn, this.spisd, mqsd, this.phobj, this.phsub, cc, rc);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1023 */       HashMap<String, String> inserts = new HashMap<>();
/* 1024 */       inserts.put("XMSC_DESTINATION_NAME", this.destination.getName());
/* 1025 */       inserts.put("XMSC_SELECTOR_STRING", (this.selector == null || this.selector.equals("")) ? "none" : this.selector);
/*      */       
/* 1027 */       WMQMessageConsumer.checkJmqiCallSuccess("JMSWMQ0026", (HashMap)inserts, cc, rc, this.env, "XN00400B", this.helper, this.hconn);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1040 */       int queueOpenOptions = computeQueueOpenOptions();
/*      */ 
/*      */       
/* 1043 */       if (this.hObjCache != null) {
/* 1044 */         this.phobj = this.hObjCache.getQueue(this.destination.getName(), this.selector, queueOpenOptions);
/*      */       } else {
/*      */         
/* 1047 */         this.phobj = null;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1052 */       if (this.phobj != null) {
/* 1053 */         this.hObjCached = true;
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1063 */         if (this.subID != null) {
/* 1064 */           this.spisd = computeLpiSD();
/* 1065 */           int options = this.spisd.getOptions();
/*      */           
/* 1067 */           options |= 0x20000;
/* 1068 */           this.spisd.setOptions(options);
/* 1069 */           this.spisd.setSubId(this.subID);
/*      */           
/* 1071 */           this.phobj = this.env.newPhobj();
/* 1072 */           this.phsub = this.env.newPhobj();
/*      */           
/* 1074 */           MQSD mqsd = this.env.newMQSD();
/* 1075 */           int subscriptionOptions = 36;
/* 1076 */           mqsd.setOptions(subscriptionOptions);
/*      */           
/* 1078 */           ((JmqiSP)this.mq).spiSubscribe(this.hconn, this.spisd, mqsd, this.phobj, this.phsub, cc, rc);
/*      */ 
/*      */           
/* 1081 */           if (rc.x != 2440) {
/*      */             
/* 1083 */             HashMap<String, String> inserts = new HashMap<>();
/* 1084 */             inserts.put("XMSC_DESTINATION_NAME", this.destination.getName());
/* 1085 */             inserts.put("XMSC_SELECTOR_STRING", (this.selector == null || this.selector.equals("")) ? "none" : this.selector);
/*      */             
/* 1087 */             WMQMessageConsumer.checkJmqiCallSuccess("JMSWMQ0026", (HashMap)inserts, cc, rc, this.env, "XN00P002", this.helper, this.hconn);
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
/* 1098 */         if (this.subID == null || rc.x == 2440) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1105 */           this.phobj = this.env.newPhobj();
/* 1106 */           MQOD mqod = this.env.newMQOD();
/* 1107 */           mqod.setObjectName(this.destination.getName());
/* 1108 */           mqod.setObjectQMgrName(this.destination.getStringProperty("XMSC_WMQ_QUEUE_MANAGER"));
/*      */           
/* 1110 */           if (this.selectionDetails == null) {
/* 1111 */             this.selectionDetails = SelectionDetails.getSelectionDetails(this.selector);
/*      */           }
/* 1113 */           if (this.selectionDetails.getSelectionType() == SelectionType.FULL_SELECTOR) {
/*      */             
/* 1115 */             mqod.setVersion(4);
/* 1116 */             mqod.getSelectionString().setVsString(this.selector);
/* 1117 */             mqod.getSelectionString().setVsBufSize(10240);
/*      */           } 
/*      */ 
/*      */           
/* 1121 */           if ((queueOpenOptions & 0x1000) == 4096) {
/* 1122 */             mqod.setAlternateUserId(this.destination.getStringProperty("alternateUserId"));
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
/* 1139 */           SpiOpenOptions spiOO = computeSpiOpenOptions(queueOpenOptions);
/*      */           
/* 1141 */           ((JmqiSP)this.mq).spiOpen(this.hconn, mqod, spiOO, this.phobj, cc, rc);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1146 */           if (rc.x == 0 && cc.x == 0 && this.destination.getName() != null && !this.destination.getName().equals(mqod.getObjectName().trim())) {
/*      */             
/* 1148 */             this.mq.MQCLOSE(this.hconn, this.phobj, 0, cc, rc);
/*      */ 
/*      */             
/* 1151 */             HashMap<String, String> inserts = new HashMap<>();
/* 1152 */             inserts.put("XMSC_DESTINATION_NAME", this.destination.getName());
/* 1153 */             JMSException je = Reason.createException("JMSWMQ2022", inserts, 2152, 2, this.env);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1159 */             if (Trace.isOn) {
/* 1160 */               Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "initialize()", (Throwable)je, 2);
/*      */             }
/*      */             
/* 1163 */             throw je;
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1169 */         String messageId = "JMSWMQ2008";
/* 1170 */         if (rc.x == 2045) {
/* 1171 */           messageId = "JMSWMQ1017";
/*      */         }
/* 1173 */         WMQMessageConsumer.checkJmqiCallSuccess(messageId, this.destination
/* 1174 */             .getName(), "XMSC_DESTINATION_NAME", cc, rc, this.env, "XN00400C", this.helper, this.hconn);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1184 */         if (this.hObjCache != null && !(this instanceof WMQQueueEnumeration.WMQSyncBrowserShadow)) {
/* 1185 */           this.hObjCache.addQueue(this.phobj, this.destination.getName(), this.selector, queueOpenOptions);
/* 1186 */           this.hObjCached = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1192 */     this.hobj = this.phobj.getHobj();
/*      */     
/* 1194 */     if (this.destination.isTopic()) {
/* 1195 */       String subQName = getSubscriptionQueue();
/*      */       
/* 1197 */       this.jmsProps.setStringProperty("XMSC_SUBSCRIBER_Q_NAME", subQName);
/*      */     } 
/*      */     
/* 1200 */     if (Trace.isOn) {
/* 1201 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "initialize()");
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
/*      */   private String getSubscriberQueueName(Hconn hconn, Hobj hobj) throws JMSException {
/* 1221 */     if (Trace.isOn) {
/* 1222 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getSubscriberQueueName(Hconn,Hobj)", new Object[] { hconn, hobj });
/*      */     }
/*      */ 
/*      */     
/* 1226 */     String qName = null;
/* 1227 */     Pint cc = this.env.newPint();
/* 1228 */     Pint rc = this.env.newPint();
/*      */     
/*      */     try {
/* 1231 */       int[] pSelectors = { 2016 };
/* 1232 */       int[] pIntAttrs = new int[0];
/* 1233 */       byte[] pCharAttrs = new byte[48];
/*      */       
/* 1235 */       this.mq.MQINQ(hconn, hobj, pSelectors.length, pSelectors, pIntAttrs.length, pIntAttrs, pCharAttrs.length, pCharAttrs, cc, rc);
/*      */       
/* 1237 */       qName = JmqiUtils.qmgrBytesToString(this.env, hconn, pCharAttrs, 0, 48);
/* 1238 */       qName = qName.trim();
/*      */     }
/* 1240 */     catch (JmqiException jmqie) {
/* 1241 */       if (Trace.isOn) {
/* 1242 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getSubscriberQueueName(Hconn,Hobj)", (Throwable)jmqie, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1247 */       qName = null;
/*      */     }
/* 1249 */     catch (UnsupportedEncodingException uee) {
/* 1250 */       if (Trace.isOn) {
/* 1251 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getSubscriberQueueName(Hconn,Hobj)", uee, 2);
/*      */       }
/*      */ 
/*      */       
/* 1255 */       qName = null;
/*      */     } finally {
/*      */       
/* 1258 */       if (Trace.isOn) {
/* 1259 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getSubscriberQueueName(Hconn,Hobj)");
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1264 */     if (Trace.isOn) {
/* 1265 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getSubscriberQueueName(Hconn,Hobj)", qName);
/*      */     }
/*      */     
/* 1268 */     return qName;
/*      */   }
/*      */ 
/*      */   
/*      */   private void checkSetGMOCONVERT() {
/* 1273 */     if (Trace.isOn) {
/* 1274 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "checkSetGMOCONVERT()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1280 */     PropertyStore.register("com.ibm.msg.client.wmq.receiveConversionCCSID", 1208L, MIN_VALID_CCSID, MAX_VALID_CCSID);
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1285 */       if (PropertyStore.wasOverridden("com.ibm.msg.client.wmq.receiveConversionCCSID", null)) {
/*      */ 
/*      */         
/* 1288 */         this.receiveCCSID = (int)PropertyStore.getLongPropertyObject("com.ibm.msg.client.wmq.receiveConversionCCSID").longValue();
/* 1289 */         this.gmoConvertOption = 16384;
/*      */ 
/*      */         
/* 1292 */         if (this.receiveCCSID == 0) {
/* 1293 */           this.receiveCCSID = this.env.getNativeCcsid();
/*      */         }
/*      */       }
/* 1296 */       else if (this.destination.getIntProperty("receiveConversion") == 2) {
/* 1297 */         this.receiveCCSID = this.destination.getIntProperty("receiveCCSID");
/* 1298 */         this.gmoConvertOption = 16384;
/*      */ 
/*      */         
/* 1301 */         if (this.receiveCCSID == 0) {
/* 1302 */           this.receiveCCSID = this.env.getNativeCcsid();
/*      */         
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 1308 */         this.receiveCCSID = 1208;
/* 1309 */         this.gmoConvertOption = 0;
/*      */       }
/*      */     
/* 1312 */     } catch (JMSException e) {
/* 1313 */       if (Trace.isOn) {
/* 1314 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "checkSetGMOCONVERT()", (Throwable)e);
/*      */       }
/*      */ 
/*      */       
/* 1318 */       HashMap<String, Object> data = new HashMap<>();
/* 1319 */       data.put("exception", e);
/* 1320 */       data.put("destination", this.destination);
/* 1321 */       Trace.ffst(this, "checkSetGMOCONVERT()", "XN00P003", data, null);
/*      */     } 
/*      */     
/* 1324 */     if (Trace.isOn) {
/* 1325 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "checkSetGMOCONVERT()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initialize(WMQConsumerShadow previousShadow) throws JMSException {
/* 1335 */     if (Trace.isOn) {
/* 1336 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "initialize(WMQConsumerShadow)", new Object[] { previousShadow });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1341 */     if (previousShadow == null) {
/* 1342 */       initialize();
/*      */     }
/*      */     else {
/*      */       
/* 1346 */       this.destination = previousShadow.destination;
/* 1347 */       this.env = previousShadow.env;
/* 1348 */       this.hconn = previousShadow.hconn;
/* 1349 */       this.helper = previousShadow.helper;
/* 1350 */       this.hobj = previousShadow.hobj;
/* 1351 */       this.jmsProps = previousShadow.jmsProps;
/* 1352 */       this.mq = previousShadow.mq;
/* 1353 */       this.phobj = previousShadow.phobj;
/* 1354 */       this.phsub = previousShadow.phsub;
/* 1355 */       this.spisd = previousShadow.spisd;
/* 1356 */       this.mqmd = previousShadow.mqmd;
/* 1357 */       this.running = previousShadow.running;
/* 1358 */       this.selector = previousShadow.selector;
/* 1359 */       this.subscriptionName = previousShadow.subscriptionName;
/* 1360 */       this.transacted = previousShadow.transacted;
/* 1361 */       this.hObjCached = previousShadow.hObjCached;
/* 1362 */       this.hObjCache = previousShadow.hObjCache;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1368 */     if (Trace.isOn) {
/* 1369 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "initialize(WMQConsumerShadow)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean qmIsZOS() {
/* 1376 */     if (Trace.isOn) {
/* 1377 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "qmIsZOS()");
/*      */     }
/*      */     try {
/* 1380 */       boolean traceRet1 = (this.hconn.getPlatform() == 1);
/* 1381 */       if (Trace.isOn) {
/* 1382 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "qmIsZOS()", 
/* 1383 */             Boolean.valueOf(traceRet1), 1);
/*      */       }
/* 1385 */       return traceRet1;
/*      */     }
/* 1387 */     catch (JmqiException e) {
/* 1388 */       if (Trace.isOn) {
/* 1389 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "qmIsZOS()", (Throwable)e);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1396 */       if (Trace.isOn) {
/* 1397 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "qmIsZOS()", 
/* 1398 */             Boolean.valueOf(false), 2);
/*      */       }
/* 1400 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void resetMQMD(WMQGMO gmo) {
/* 1411 */     if (Trace.isOn) {
/* 1412 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "resetMQMD(WMQGMO)", new Object[] { gmo });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1418 */     this.mqmd.setCodedCharSetId(this.receiveCCSID);
/* 1419 */     this.mqmd.setEncoding(273);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1427 */     int matchOptions = gmo.getMatchOptions();
/* 1428 */     if ((matchOptions & 0x1) == 0) {
/* 1429 */       this.mqmd.setMsgId(MQMI_NONE);
/*      */     } else {
/*      */       
/* 1432 */       this.mqmd.setMsgId(gmo.getMessageID());
/*      */     } 
/*      */     
/* 1435 */     if ((matchOptions & 0x2) == 0) {
/* 1436 */       this.mqmd.setCorrelId(MQMI_NONE);
/*      */     } else {
/*      */       
/* 1439 */       this.mqmd.setCorrelId(gmo.getCorrelationID());
/*      */     } 
/*      */     
/* 1442 */     if (Trace.isOn) {
/* 1443 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "resetMQMD(WMQGMO)");
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
/*      */   ProviderMessage receive(long timeOut) throws JMSException {
/* 1458 */     if (Trace.isOn)
/* 1459 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "receive(long)", new Object[] {
/* 1460 */             Long.valueOf(timeOut)
/*      */           }); 
/* 1462 */     ProviderMessage traceRet1 = receiveInternal(timeOut);
/* 1463 */     if (Trace.isOn) {
/* 1464 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "receive(long)", traceRet1);
/*      */     }
/*      */     
/* 1467 */     return traceRet1;
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
/*      */   private boolean isFiq() throws JMSException {
/* 1490 */     if (Trace.isOn) {
/* 1491 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "isFiq()");
/*      */     }
/*      */     try {
/* 1494 */       int fiq = this.destination.getIntProperty("failIfQuiesce");
/*      */       
/* 1496 */       if (fiq == 1) {
/* 1497 */         if (Trace.isOn) {
/* 1498 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "isFiq()", 
/* 1499 */               Boolean.valueOf(true), 1);
/*      */         }
/* 1501 */         return true;
/*      */       } 
/* 1503 */       if (Trace.isOn) {
/* 1504 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "isFiq()", 
/* 1505 */             Boolean.valueOf(false), 2);
/*      */       }
/* 1507 */       return false;
/*      */     }
/* 1509 */     catch (JMSException jmsex) {
/* 1510 */       if (Trace.isOn) {
/* 1511 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "isFiq()", (Throwable)jmsex);
/*      */       }
/*      */       
/* 1514 */       if (Trace.isOn) {
/* 1515 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "isFiq()", (Throwable)jmsex);
/*      */       }
/* 1517 */       throw jmsex;
/*      */     } 
/*      */   }
/*      */   
/*      */   private String getAlternateUserId() throws JMSException {
/* 1522 */     if (Trace.isOn) {
/* 1523 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getAlternateUserId()");
/*      */     }
/*      */     try {
/* 1526 */       String altuser = this.destination.getStringProperty("alternateUserId");
/*      */       
/* 1528 */       if (Trace.isOn) {
/* 1529 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getAlternateUserId()", altuser);
/*      */       }
/*      */       
/* 1532 */       return altuser;
/*      */     }
/* 1534 */     catch (JMSException jmsex) {
/* 1535 */       if (Trace.isOn) {
/* 1536 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getAlternateUserId()", (Throwable)jmsex);
/*      */       }
/*      */       
/* 1539 */       if (Trace.isOn) {
/* 1540 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getAlternateUserId()", (Throwable)jmsex);
/*      */       }
/* 1542 */       throw jmsex;
/*      */     } 
/*      */   }
/*      */   
/*      */   int computeQueueOpenOptions() throws JMSException {
/* 1547 */     if (Trace.isOn) {
/* 1548 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "computeQueueOpenOptions()");
/*      */     }
/*      */ 
/*      */     
/* 1552 */     int options = 33;
/* 1553 */     if (isFiq()) {
/* 1554 */       options |= 0x2000;
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1559 */       int readA = this.destination.getIntProperty("readAheadAllowed");
/*      */ 
/*      */ 
/*      */       
/* 1563 */       if (readA == 1) {
/* 1564 */         if (Trace.isOn) {
/* 1565 */           Trace.data(this, "Open read-ahead-allowed = enabled", null);
/*      */         }
/* 1567 */         options |= 0x100000;
/*      */       }
/* 1569 */       else if (readA == 0) {
/* 1570 */         if (Trace.isOn) {
/* 1571 */           Trace.data(this, "Open read-ahead-allowed = disabled", null);
/*      */         }
/* 1573 */         options |= 0x80000;
/*      */       }
/*      */     
/* 1576 */     } catch (JMSException jmsex) {
/* 1577 */       if (Trace.isOn) {
/* 1578 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "computeQueueOpenOptions()", (Throwable)jmsex);
/*      */       }
/*      */       
/* 1581 */       if (Trace.isOn) {
/* 1582 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "computeQueueOpenOptions()", (Throwable)jmsex);
/*      */       }
/*      */       
/* 1585 */       throw jmsex;
/*      */     } 
/*      */ 
/*      */     
/* 1589 */     String altuser = getAlternateUserId();
/* 1590 */     if (altuser != null) {
/* 1591 */       options |= 0x1000;
/*      */     }
/*      */     
/* 1594 */     if (Trace.isOn) {
/* 1595 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "computeQueueOpenOptions()", 
/* 1596 */           Integer.valueOf(options));
/*      */     }
/* 1598 */     return options;
/*      */   }
/*      */   
/*      */   int computeSubscriptionOptions() throws JMSException {
/* 1602 */     if (Trace.isOn) {
/* 1603 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "computeSubscriptionOptions()");
/*      */     }
/*      */     
/* 1606 */     int options = 34;
/* 1607 */     if (isFiq()) {
/* 1608 */       options |= 0x2000;
/*      */     } else {
/*      */       
/* 1611 */       options &= 0xFFFFDFFF;
/*      */     } 
/* 1613 */     if (this.durable) {
/* 1614 */       options |= 0x209;
/*      */     }
/* 1616 */     else if (this.shared) {
/* 1617 */       options |= 0x201;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1625 */     int wildcard = this.destination.getIntProperty("wildcardFormat");
/*      */ 
/*      */     
/* 1628 */     if (wildcard == 0) {
/* 1629 */       options |= 0x200000;
/*      */     }
/* 1631 */     else if (wildcard == 1) {
/* 1632 */       options |= 0x100000;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1638 */     int readA = this.destination.getIntProperty("readAheadAllowed");
/* 1639 */     if (readA == 1) {
/* 1640 */       options |= 0x10000000;
/*      */     }
/* 1642 */     else if (readA == 0) {
/* 1643 */       options |= 0x8000000;
/*      */     } 
/*      */     
/* 1646 */     if (Trace.isOn) {
/* 1647 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "computeSubscriptionOptions()", 
/* 1648 */           Integer.valueOf(options));
/*      */     }
/* 1650 */     return options;
/*      */   }
/*      */   
/*      */   void initialisePoison() throws JMSException {
/* 1654 */     if (Trace.isOn) {
/* 1655 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "initialisePoison()");
/*      */     }
/*      */     
/* 1658 */     if (null == this.poison) {
/*      */       
/* 1660 */       String subQueue = getSubscriptionQueue();
/* 1661 */       this.poison = new WMQPoison(this.helper, this.destination, this.hobj, subQueue);
/*      */       
/* 1663 */       this.poison.setBrowser(this.isBrowser);
/*      */     } 
/*      */     
/* 1666 */     if (Trace.isOn) {
/* 1667 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "initialisePoison()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String getSubscriptionQueue() {
/* 1677 */     String subQueue = null;
/* 1678 */     if (this.spisd != null && this.spisd.getSubProps() != null) {
/* 1679 */       subQueue = this.spisd.getSubProps().getDestinationQName();
/*      */     }
/* 1681 */     if (Trace.isOn) {
/* 1682 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getSubscriptionQueue()", "getter", subQueue);
/*      */     }
/*      */     
/* 1685 */     return subQueue;
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
/*      */   ProviderMessage getMsg(WMQGMO gmo, int msgLength, boolean throwExceptionForNoMsg) throws JMSException {
/* 1699 */     if (Trace.isOn) {
/* 1700 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getMsg(WMQGMO,int,boolean)", new Object[] { gmo, 
/* 1701 */             Integer.valueOf(msgLength), 
/* 1702 */             Boolean.valueOf(throwExceptionForNoMsg) });
/*      */     }
/* 1704 */     ProviderMessage traceRet1 = getMsg(gmo, msgLength, throwExceptionForNoMsg, this.env.newPint(), this.env.newPint());
/* 1705 */     if (Trace.isOn) {
/* 1706 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getMsg(WMQGMO,int,boolean)", traceRet1);
/*      */     }
/*      */     
/* 1709 */     return traceRet1;
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
/*      */   ProviderMessage getMsg(WMQGMO gmo, int msgLength, boolean throwExceptionForNoMsg, Pint cc, Pint rc) throws JMSException {
/* 1724 */     if (Trace.isOn) {
/* 1725 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getMsg(WMQGMO,int,boolean,Pint,Pint)", new Object[] { gmo, 
/* 1726 */             Integer.valueOf(msgLength), 
/* 1727 */             Boolean.valueOf(throwExceptionForNoMsg), cc, rc });
/*      */     }
/*      */     
/*      */     try {
/*      */       WMQMessage wMQMessage;
/* 1732 */       synchronized (this.receiveCloseLock) {
/* 1733 */         if (this.closeCalled.get()) {
/*      */ 
/*      */           
/* 1736 */           if (Trace.isOn) {
/* 1737 */             Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getMsg(WMQGMO,int,boolean,Pint,Pint)", null, 1);
/*      */           }
/*      */           
/* 1740 */           return null;
/*      */         } 
/* 1742 */         this.isReceiveInProgress.set(true);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1748 */       assert this.helper instanceof WMQSession;
/*      */ 
/*      */       
/* 1751 */       resetMQMD(gmo);
/*      */ 
/*      */       
/* 1754 */       ByteBuffer msgBuffer = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1760 */       this.helper.lockHconn();
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1765 */         if (this.closeCalled.get()) {
/* 1766 */           if (Trace.isOn) {
/* 1767 */             Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getMsg(WMQGMO,int,boolean,Pint,Pint)", null, 2);
/*      */           }
/*      */           
/* 1770 */           return null;
/*      */         } 
/*      */         
/* 1773 */         while (this.helper.getCloseCounter() > 0) {
/*      */           try {
/* 1775 */             this.helper.awaitHconn();
/*      */           }
/* 1777 */           catch (InterruptedException e) {
/* 1778 */             if (Trace.isOn) {
/* 1779 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getMsg(WMQGMO,int,boolean,Pint,Pint)", e, 1);
/*      */             }
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1787 */         if (this.closeCalled.get()) {
/* 1788 */           if (Trace.isOn) {
/* 1789 */             Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getMsg(WMQGMO,int,boolean,Pint,Pint)", null, 3);
/*      */           }
/*      */           
/* 1792 */           return null;
/*      */         } 
/*      */ 
/*      */         
/* 1796 */         msgBuffer = ((JmqiSP)this.mq).jmqiGet(this.hconn, this.hobj, this.mqmd, gmo, msgLength, 2147483647, this.getMessageDataBuffer, this.msgsTooSmallForBuffer, this.dataLength, cc, rc);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       finally {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1810 */         this.helper.unlockHconn();
/*      */       } 
/*      */       
/* 1813 */       if (cc.x != 2)
/*      */       {
/* 1815 */         if (Trace.isOn) {
/* 1816 */           Trace.traceData(this, "Message size is ", Integer.toString(this.dataLength.x));
/*      */         }
/*      */       }
/*      */ 
/*      */       
/* 1821 */       switch (rc.x) {
/*      */         case 0:
/*      */         case 2110:
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 2150:
/* 1833 */           if (!this.isBrowser && 
/* 1834 */             this.mqmd.getBackoutCount() > 0) {
/*      */             
/* 1836 */             initialisePoison();
/* 1837 */             if (this.poison.shouldMessageBeRequeued(this.mqmd.getBackoutCount())) {
/* 1838 */               ProviderMessage pmsg = this.poison.handlePoisonMessage(this.mqmd, new ByteBuffer[] { msgBuffer });
/* 1839 */               if (Trace.isOn) {
/* 1840 */                 Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getMsg(WMQGMO,int,boolean,Pint,Pint)", pmsg, 4);
/*      */               }
/*      */               
/* 1843 */               return pmsg;
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/* 1852 */           if (!throwExceptionForNoMsg && rc.x == 2033) {
/* 1853 */             if (Trace.isOn) {
/* 1854 */               Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getMsg(WMQGMO,int,boolean,Pint,Pint)", null, 5);
/*      */             }
/*      */             
/* 1857 */             return null;
/*      */           } 
/* 1859 */           WMQMessageConsumer.checkJmqiCallSuccess("JMSWMQ2002", this.destination
/* 1860 */               .getName(), "XMSC_DESTINATION_NAME", cc, rc, this.env, "XN004008", this.helper, this.hconn);
/*      */           break;
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
/* 1872 */       if (this.mqmd.getBackoutCount() > 0) {
/*      */         
/* 1874 */         initialisePoison();
/* 1875 */         if (this.poison.shouldMessageBeRequeued(this.mqmd.getBackoutCount())) {
/* 1876 */           ProviderMessage pmsg = this.poison.handlePoisonMessage(this.mqmd, new ByteBuffer[] { msgBuffer }, gmo);
/* 1877 */           if (Trace.isOn) {
/* 1878 */             Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getMsg(WMQGMO,int,boolean,Pint,Pint)", pmsg, 6);
/*      */           }
/*      */           
/* 1881 */           return pmsg;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1891 */       if (this.receiveMarshal == null) {
/* 1892 */         initializeReceiveMarshal();
/*      */       }
/*      */ 
/*      */       
/* 1896 */       this.receiveMarshal.importMQMDMesageBuffer(this.helper, this.destination, this.mqmd, msgBuffer, 0, this.dataLength.x, gmo);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1903 */       ProviderMessage resultMsg = null;
/*      */       try {
/* 1905 */         wMQMessage = this.receiveMarshal.exportProviderMessage(false);
/*      */       }
/* 1907 */       catch (JMSException jmsEx) {
/*      */ 
/*      */ 
/*      */         
/* 1911 */         if (Trace.isOn) {
/* 1912 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getMsg(WMQGMO,int,boolean)", (Throwable)jmsEx, 2);
/* 1913 */           Trace.data(this, "getMsg(WMQGMO,int,boolean)", "Provider message is invalid. Performing poison message handling", null);
/*      */         } 
/*      */         
/* 1916 */         HashMap<String, Object> data = new HashMap<>();
/* 1917 */         data.put("XMSC_MSG_DATA", msgBuffer.array());
/* 1918 */         data.put("XMSC_INSERT_EXCEPTION", jmsEx);
/* 1919 */         data.put("XMSC_DATA", this.mqmd.toString());
/* 1920 */         Log.log(this, "getMsg(WMQGMO,int,boolean,Pint,Pint)", "JMSWMQ2023", data);
/*      */ 
/*      */         
/* 1923 */         initialisePoison();
/* 1924 */         ProviderMessage pmsg = this.poison.handlePoisonMessage(this.mqmd, new ByteBuffer[] { msgBuffer });
/* 1925 */         if (Trace.isOn) {
/* 1926 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getMsg(WMQGMO,int,boolean)", pmsg, 4);
/*      */         }
/* 1928 */         if (Trace.isOn) {
/* 1929 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getMsg(WMQGMO,int,boolean,Pint,Pint)", pmsg, 8);
/*      */         }
/*      */         
/* 1932 */         return pmsg;
/*      */       } 
/* 1934 */       if (Trace.isOn) {
/* 1935 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getMsg(WMQGMO,int,boolean,Pint,Pint)", wMQMessage, 9);
/*      */       }
/*      */       
/* 1938 */       return (ProviderMessage)wMQMessage;
/*      */     } finally {
/*      */       
/* 1941 */       if (Trace.isOn) {
/* 1942 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getMsg(WMQGMO,int,boolean,Pint,Pint)");
/*      */       }
/*      */       
/* 1945 */       this.isReceiveInProgress.set(false);
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
/*      */   protected void setMatchOptions(SelectionDetails selectionDetails, WMQGMO gmo) {
/* 1960 */     if (Trace.isOn) {
/* 1961 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "setMatchOptions(SelectionDetails,WMQGMO)", new Object[] { selectionDetails, gmo });
/*      */     }
/*      */ 
/*      */     
/* 1965 */     switch (selectionDetails.getSelectionType()) {
/*      */       case CORRELID_ONLY:
/* 1967 */         if (Trace.isOn) {
/* 1968 */           Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "setMatchOptions(SelectionDetails,WMQGMO)", "setting GMO to select by correlation ID");
/*      */         }
/*      */         
/* 1971 */         gmo.setCorrelationID(selectionDetails.getId());
/* 1972 */         gmo.setMatchOptions(gmo.getMatchOptions() | 0x2);
/*      */         break;
/*      */       case MESSAGEID_ONLY:
/* 1975 */         if (Trace.isOn) {
/* 1976 */           Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "setMatchOptions(SelectionDetails,WMQGMO)", "setting GMO to select by message ID");
/*      */         }
/*      */         
/* 1979 */         gmo.setMessageID(selectionDetails.getId());
/* 1980 */         gmo.setMatchOptions(gmo.getMatchOptions() | 0x1);
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1990 */     if (Trace.isOn) {
/* 1991 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "setMatchOptions(SelectionDetails,WMQGMO)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isRunning() {
/* 1998 */     if (Trace.isOn) {
/* 1999 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "isRunning()", "getter", 
/* 2000 */           Boolean.valueOf(this.running));
/*      */     }
/* 2002 */     return this.running;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setRunning(boolean running) throws JMSException {
/* 2010 */     if (Trace.isOn) {
/* 2011 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "setRunning(boolean)", "setter", 
/* 2012 */           Boolean.valueOf(running));
/*      */     }
/* 2014 */     this.running = running;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void initializeReceiveMarshal() {
/* 2025 */     if (this.helper instanceof WMQSession && (this.jmsProps instanceof WMQMessageConsumer || this.jmsProps instanceof WMQQueueEnumeration) && this.destination != null) {
/*      */       boolean forceRFH2;
/*      */       try {
/* 2028 */         int messageBodyStyle = this.destination.getIntProperty("messageBody");
/* 2029 */         forceRFH2 = (messageBodyStyle == 1);
/*      */       }
/* 2031 */       catch (JMSException jmsex) {
/*      */         
/* 2033 */         forceRFH2 = true;
/*      */       } 
/* 2035 */       this.receiveMarshal = WMQMarshal.newReceiveMarshal(this.helper, forceRFH2);
/*      */     } else {
/*      */       
/* 2038 */       this.receiveMarshal = WMQMarshal.newReceiveMarshal();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getReceiveCount() {
/* 2046 */     if (Trace.isOn) {
/* 2047 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getReceiveCount()", "getter", 
/* 2048 */           Integer.valueOf(this.receiveCount));
/*      */     }
/* 2050 */     return this.receiveCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected long getLastReceiveTime() {
/* 2057 */     if (Trace.isOn) {
/* 2058 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "getLastReceiveTime()", "getter", 
/* 2059 */           Long.valueOf(this.lastReceiveTime));
/*      */     }
/* 2061 */     return this.lastReceiveTime;
/*      */   }
/*      */   
/*      */   protected enum SelectionType {
/* 2065 */     NO_SELECTOR, CORRELID_ONLY, MESSAGEID_ONLY, FULL_SELECTOR;
/*      */   }
/*      */ 
/*      */   
/*      */   protected static class SelectionDetails
/*      */   {
/*      */     private static final String JMSCORRELATIONID = "JMSCorrelationID";
/*      */     
/*      */     private static final String JMSMESSAGEID = "JMSMessageID";
/*      */     
/*      */     private static final int ID_LENGTH = 24;
/*      */     
/*      */     private static final String ID_TYPE_TAG = "idType";
/*      */     
/*      */     private static final String ID_STRING_TAG = "idString";
/*      */     
/*      */     private static final Pattern ID_SELECTOR_PATTERN;
/*      */     
/*      */     WMQConsumerShadow.SelectionType selectionType;
/*      */     byte[] id;
/*      */     String selector;
/*      */     
/*      */     static {
/* 2088 */       String idTypes = String.format("(%s|%s)", new Object[] { "JMSCorrelationID", "JMSMessageID" });
/*      */ 
/*      */       
/* 2091 */       String idPattern = String.format("(\\p{XDigit}{2}){1,%d}", new Object[] { Integer.valueOf(24) });
/*      */ 
/*      */       
/* 2094 */       String selectorPattern = String.format("(?<%s>%s)='ID:(?<%s>%s)'", new Object[] { "idType", idTypes, "idString", idPattern });
/*      */       
/* 2096 */       ID_SELECTOR_PATTERN = Pattern.compile(selectorPattern);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     WMQConsumerShadow.SelectionType getSelectionType() {
/* 2104 */       return this.selectionType;
/*      */     }
/*      */     
/*      */     byte[] getId() {
/* 2108 */       return this.id;
/*      */     }
/*      */     
/*      */     String getSelector() {
/* 2112 */       return this.selector;
/*      */     }
/*      */     
/*      */     SelectionDetails(WMQConsumerShadow.SelectionType selectionType, byte[] id, String selector) {
/* 2116 */       this.selectionType = selectionType;
/* 2117 */       this.id = id;
/* 2118 */       this.selector = selector;
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/* 2123 */       StringBuilder result = new StringBuilder();
/* 2124 */       result.append("SelectionDetails @0x" + Integer.toHexString(System.identityHashCode(this)));
/* 2125 */       result.append(" type = " + this.selectionType);
/* 2126 */       result.append(" id = " + JmqiUtils.arrayToHexString(this.id));
/* 2127 */       result.append(" selector = {" + this.selector + "}");
/* 2128 */       return result.toString();
/*      */     }
/*      */     
/*      */     static SelectionDetails getSelectionDetails(String selector) {
/* 2132 */       if (Trace.isOn) {
/* 2133 */         Trace.entry("com.ibm.msg.client.wmq.internal.WMQConsumerShadow$SelectionDetails", "getSelectionDetails(String)", new Object[] { selector });
/*      */       }
/*      */ 
/*      */       
/* 2137 */       SelectionDetails result = null;
/*      */ 
/*      */       
/* 2140 */       if (selector == null) {
/* 2141 */         result = new SelectionDetails(WMQConsumerShadow.SelectionType.NO_SELECTOR, null, null);
/*      */       }
/*      */       else {
/*      */         
/* 2145 */         String packedSelector = selector.replace(" ", "");
/* 2146 */         if (packedSelector.length() == 0) {
/* 2147 */           result = new SelectionDetails(WMQConsumerShadow.SelectionType.NO_SELECTOR, null, null);
/*      */         }
/*      */         else {
/*      */           
/* 2151 */           result = generateIdSelectionDetails(packedSelector);
/* 2152 */           if (result == null) {
/* 2153 */             result = new SelectionDetails(WMQConsumerShadow.SelectionType.FULL_SELECTOR, null, selector);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 2158 */       if (Trace.isOn) {
/* 2159 */         Trace.exit("com.ibm.msg.client.wmq.internal.WMQConsumerShadow$SelectionDetails", "getSelectionDetails(String)", result);
/*      */       }
/*      */       
/* 2162 */       return result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static SelectionDetails generateIdSelectionDetails(String selector) {
/* 2171 */       if (Trace.isOn) {
/* 2172 */         Trace.entry("com.ibm.msg.client.wmq.internal.WMQConsumerShadow$SelectionDetails", "generateIdSelectionDetails(String)", new Object[] { selector });
/*      */       }
/*      */ 
/*      */       
/* 2176 */       Matcher selectorMatcher = ID_SELECTOR_PATTERN.matcher(selector);
/*      */       
/* 2178 */       if (!selectorMatcher.matches()) {
/* 2179 */         if (Trace.isOn) {
/* 2180 */           Trace.exit("com.ibm.msg.client.wmq.internal.WMQConsumerShadow$SelectionDetails", "generateIdSelectionDetails(String)", null, 0);
/*      */         }
/*      */         
/* 2183 */         return null;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2190 */       WMQConsumerShadow.SelectionType type = selectorMatcher.group("idType").equals("JMSMessageID") ? WMQConsumerShadow.SelectionType.MESSAGEID_ONLY : WMQConsumerShadow.SelectionType.CORRELID_ONLY;
/*      */       
/* 2192 */       SelectionDetails result = new SelectionDetails(type, hexIdToBytes(selectorMatcher.group("idString")), selector);
/*      */       
/* 2194 */       if (Trace.isOn) {
/* 2195 */         Trace.exit("com.ibm.msg.client.wmq.internal.WMQConsumerShadow$SelectionDetails", "generateIdSelectionDetails(String)", result, 1);
/*      */       }
/*      */ 
/*      */       
/* 2199 */       return result;
/*      */     }
/*      */     
/*      */     private static byte[] hexIdToBytes(String hexIdString) {
/* 2203 */       if (Trace.isOn) {
/* 2204 */         Trace.entry("com.ibm.msg.client.wmq.internal.WMQConsumerShadow$SelectionDetails", "hexIdToBytes(String)", new Object[] { hexIdString });
/*      */       }
/*      */ 
/*      */       
/* 2208 */       byte[] result = new byte[24];
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       int i;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2218 */       for (i = 0; i < hexIdString.length() / 2; i++) {
/* 2219 */         int charPairOffset = i * 2;
/* 2220 */         result[i] = (byte)Integer.parseInt(hexIdString.substring(charPairOffset, charPairOffset + 2), 16);
/*      */       } 
/*      */       
/* 2223 */       Arrays.fill(result, i, 24, (byte)0);
/*      */       
/* 2225 */       if (Trace.isOn) {
/* 2226 */         Trace.exit("com.ibm.msg.client.wmq.internal.WMQConsumerShadow$SelectionDetails", "hexIdToBytes(String)", result);
/*      */       }
/*      */       
/* 2229 */       return result;
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
/*      */   @Cruise("This method used to have the signature messageConsumedUnderSyncpoint(MQGMO)")
/*      */   boolean messageConsumedUnderSyncpoint(int getMessageOptions, int mqmdPersistence) {
/* 2244 */     if (Trace.isOn) {
/* 2245 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "messageConsumedUnderSyncpoint(int,int)", new Object[] {
/*      */             
/* 2247 */             Integer.valueOf(getMessageOptions), Integer.valueOf(mqmdPersistence)
/*      */           });
/*      */     }
/*      */     
/* 2251 */     boolean returnValue = true;
/*      */ 
/*      */     
/* 2254 */     if ((getMessageOptions & 0x4) == 4) {
/* 2255 */       if (Trace.isOn) {
/* 2256 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "messageConsumedUnderSyncpoint(int,int)", "MQGMO_NO_SYNCPOINT specified. Returning false");
/*      */       }
/*      */ 
/*      */       
/* 2260 */       returnValue = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 2268 */     else if (mqmdPersistence == 0 && (getMessageOptions & 0x1000) == 4096) {
/*      */ 
/*      */ 
/*      */       
/* 2272 */       if (Trace.isOn) {
/* 2273 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "messageConsumedUnderSyncpoint(int,int)", "MQGMO_SYNCPOINT_IF_PERSISTENT specified, and the MQMD indiciates that the message is not persistent. Returning false");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2278 */       returnValue = false;
/*      */     } 
/*      */ 
/*      */     
/* 2282 */     if (Trace.isOn) {
/* 2283 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQConsumerShadow", "messageConsumedUnderSyncpoint(int,int)", 
/* 2284 */           Boolean.valueOf(returnValue));
/*      */     }
/* 2286 */     return returnValue;
/*      */   }
/*      */   
/*      */   abstract void closeInternal(boolean paramBoolean) throws JMSException;
/*      */   
/*      */   abstract ProviderMessage receiveInternal(long paramLong) throws JMSException;
/*      */   
/*      */   abstract void setMessageListener(ProviderMessageListener paramProviderMessageListener) throws JMSException;
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\WMQConsumerShadow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */