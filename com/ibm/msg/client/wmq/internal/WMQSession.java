/*      */ package com.ibm.msg.client.wmq.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.JmqiReconnectionListener;
/*      */ import com.ibm.mq.jmqi.MQCNO;
/*      */ import com.ibm.mq.jmqi.MQSTS;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Phconn;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.mq.jmqi.remote.api.RemoteHconn;
/*      */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*      */ import com.ibm.mq.jmqi.system.JmqiSP;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.msg.client.commonservices.locking.TraceableReentrantLock;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.trace.TableBuilder;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import com.ibm.msg.client.jms.JmsReadablePropertyContext;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessageConsumer;
/*      */ import com.ibm.msg.client.provider.ProviderMessageProducer;
/*      */ import com.ibm.msg.client.provider.ProviderMessageReference;
/*      */ import com.ibm.msg.client.provider.ProviderQueueBrowser;
/*      */ import com.ibm.msg.client.provider.ProviderSession;
/*      */ import com.ibm.msg.client.wmq.common.WMQCommonUtils;
/*      */ import com.ibm.msg.client.wmq.common.WMQThreadLocalStorage;
/*      */ import com.ibm.msg.client.wmq.common.internal.Reason;
/*      */ import com.ibm.msg.client.wmq.common.internal.StringableProperty;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQCommonConnection;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQCommonSession;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQConsumerOwner;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQHobjCache;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQPropertyContext;
/*      */ import java.io.IOException;
/*      */ import java.io.NotSerializableException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.locks.ReentrantLock;
/*      */ import javax.jms.JMSException;
/*      */ import org.json.JSONObject;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class WMQSession
/*      */   extends WMQPropertyContext
/*      */   implements ProviderSession, WMQConsumerOwner, WMQCommonSession, JmqiReconnectionListener
/*      */ {
/*      */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQSession.java";
/*      */   private static final long serialVersionUID = 1489582154L;
/*      */   private int ackMode;
/*      */   private transient WMQSyncConsumerShadow asfConsumer;
/*      */   private WMQDestination asfDestination;
/*      */   private transient AsyncPutCounter asyncPutCounter;
/*      */   private WMQConnection connection;
/*      */   
/*      */   static {
/*  128 */     if (Trace.isOn) {
/*  129 */       Trace.data("com.ibm.msg.client.wmq.internal.WMQSession", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQSession.java");
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
/*      */   private static class AsyncPutCounter
/*      */   {
/*  145 */     private int counter = 0;
/*      */     private final int limit;
/*      */     private final boolean off;
/*      */     
/*      */     private AsyncPutCounter(JmsPropertyContext propertyContext) throws JMSException {
/*  150 */       if (Trace.isOn) {
/*  151 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.AsyncPutCounter", "<init>(JmsPropertyContext)", new Object[] { propertyContext });
/*      */       }
/*      */       
/*  154 */       this.limit = propertyContext.getIntProperty("XMSC_WMQ_SEND_CHECK_COUNT");
/*  155 */       this.off = (0 == this.limit);
/*  156 */       if (Trace.isOn) {
/*  157 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.AsyncPutCounter", "<init>(JmsPropertyContext)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private synchronized int getCounter() {
/*  164 */       if (Trace.isOn) {
/*  165 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.AsyncPutCounter", "getCounter()", "getter", 
/*  166 */             Integer.valueOf(this.counter));
/*      */       }
/*  168 */       return this.counter;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private synchronized boolean incrementAndCheckLimit() {
/*  178 */       if (Trace.isOn) {
/*  179 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.AsyncPutCounter", "incrementAndCheckLimit()");
/*      */       }
/*      */       
/*  182 */       if (this.off) {
/*  183 */         if (Trace.isOn) {
/*  184 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.AsyncPutCounter", "incrementAndCheckLimit()", 
/*  185 */               Boolean.valueOf(false), 1);
/*      */         }
/*  187 */         return false;
/*      */       } 
/*  189 */       this.counter++;
/*  190 */       if (this.counter == this.limit) {
/*  191 */         this.counter = 0;
/*      */       }
/*  193 */       boolean isZero = (0 == this.counter);
/*  194 */       if (Trace.isOn) {
/*  195 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.AsyncPutCounter", "incrementAndCheckLimit()", 
/*  196 */             Boolean.valueOf(isZero), 2);
/*      */       }
/*  198 */       return isZero;
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
/*      */   static String createFullSubscriptionName(String clientid, String subscriptionName, String resolvedQMName, String streamName, boolean durable, boolean shared) {
/*  216 */     if (Trace.isOn) {
/*  217 */       Trace.entry("com.ibm.msg.client.wmq.internal.WMQSession", "createFullSubscriptionName(String,String,String,String,boolean,boolean)", new Object[] { clientid, subscriptionName, resolvedQMName, streamName, 
/*      */             
/*  219 */             Boolean.valueOf(durable), 
/*  220 */             Boolean.valueOf(shared) });
/*      */     }
/*  222 */     StringBuffer name = new StringBuffer();
/*      */     
/*  224 */     name.append("JMS:");
/*      */ 
/*      */ 
/*      */     
/*  228 */     if (!durable) {
/*  229 */       name.append("ND:");
/*      */     }
/*  231 */     name.append(WMQCommonUtils.escapeString(resolvedQMName));
/*  232 */     name.append(":");
/*  233 */     name.append(WMQCommonUtils.escapeString(clientid));
/*  234 */     name.append(":");
/*  235 */     name.append(WMQCommonUtils.escapeString(subscriptionName));
/*      */     
/*  237 */     if (!streamName.equals("SYSTEM.BROKER.DEFAULT.STREAM")) {
/*  238 */       name.append(WMQCommonUtils.escapeString(streamName));
/*      */     }
/*      */     
/*  241 */     String traceRet1 = name.toString();
/*  242 */     if (Trace.isOn) {
/*  243 */       Trace.exit("com.ibm.msg.client.wmq.internal.WMQSession", "createFullSubscriptionName(String,String,String,String,boolean,boolean)", traceRet1);
/*      */     }
/*      */     
/*  246 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean didRecovAsyncPut = false;
/*      */ 
/*      */   
/*      */   private transient JmqiEnvironment environment;
/*      */ 
/*      */   
/*      */   private int jmqiCompId;
/*      */ 
/*      */   
/*      */   private transient Hconn hconn;
/*      */ 
/*      */   
/*      */   private transient WMQConsumerOwnerShadow helper;
/*      */ 
/*      */   
/*      */   private WMQHobjCache hobjCache;
/*      */ 
/*      */   
/*      */   private boolean inSyncpoint = false;
/*      */ 
/*      */   
/*      */   private transient JmqiMQ mq;
/*      */ 
/*      */   
/*      */   private transient JmqiSP mqSPI;
/*      */ 
/*      */   
/*      */   private String queueManagerName;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class AsfConsumerInUseLock
/*      */     extends TraceableReentrantLock
/*      */   {
/*      */     private static final long serialVersionUID = -594645649336160064L;
/*      */ 
/*      */ 
/*      */     
/*      */     private AsfConsumerInUseLock() {}
/*      */   }
/*      */   
/*  292 */   private ReentrantLock asfConsumerInUseLock = (ReentrantLock)new AsfConsumerInUseLock();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean processMessageReferences = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean propertyRefreshNeeded = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   WMQSession(WMQConnection connection, int ackMode, JmsPropertyContext propertyContext) throws JMSException {
/*  329 */     super(propertyContext);
/*  330 */     if (Trace.isOn) {
/*  331 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "<init>(WMQConnection,int,JmsPropertyContext)", new Object[] { connection, 
/*      */             
/*  333 */             Integer.valueOf(ackMode), propertyContext });
/*      */     }
/*  335 */     this.connection = connection;
/*  336 */     this.ackMode = ackMode;
/*      */ 
/*      */     
/*  339 */     Trace.registerFFSTObject(this);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  344 */     this.hobjCache = new WMQHobjCache();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  350 */     short adminObjectType = getShortProperty("XMSC_ADMIN_OBJECT_TYPE");
/*      */     
/*  352 */     if ((adminObjectType & 0x100) == 0) {
/*  353 */       connect();
/*      */     } else {
/*      */       
/*  356 */       connectRRS();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  362 */     this.helper = new WMQConsumerOwnerShadow(this, this.queueManagerName);
/*      */     
/*  364 */     if (Trace.isOn) {
/*  365 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "<init>(WMQConnection,int,JmsPropertyContext)");
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
/*      */   private void connect() throws JMSException {
/*  377 */     if (Trace.isOn) {
/*  378 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "connect()");
/*      */     }
/*      */     
/*  381 */     this.queueManagerName = this.connection.getQueueManagerName();
/*  382 */     this.environment = this.connection.getJmqiEnvironment();
/*  383 */     this.jmqiCompId = this.connection.getJmqiCompId();
/*  384 */     this.mq = this.connection.getJmqiMQ();
/*  385 */     this.mqSPI = (JmqiSP)this.mq;
/*  386 */     this.asyncPutCounter = new AsyncPutCounter((JmsPropertyContext)this);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  391 */     if (this.mqSPI.isIMS()) {
/*  392 */       this.hconn = this.connection.getHconn();
/*      */     }
/*      */     else {
/*      */       
/*  396 */       JmqiConnectOptions jmqiConnOpts = this.connection.getJmqiConnectOptions();
/*  397 */       jmqiConnOpts.setReconnectionListener(this);
/*  398 */       MQCNO connOpts = this.connection.getConnectOptions();
/*  399 */       Phconn phconn = this.environment.newPhconn();
/*  400 */       Pint cc = this.environment.newPint();
/*  401 */       Pint rc = this.environment.newPint();
/*  402 */       Pint cc2 = this.environment.newPint();
/*  403 */       Pint rc2 = this.environment.newPint();
/*      */ 
/*      */ 
/*      */       
/*  407 */       Hconn parentHconn = this.connection.getHconn();
/*  408 */       phconn.setHconn(parentHconn);
/*      */       
/*  410 */       this.mqSPI.jmqiConnect(this.queueManagerName, jmqiConnOpts, connOpts, parentHconn, phconn, cc, rc);
/*      */ 
/*      */       
/*  413 */       if (cc.x == 1 && rc.x == 2267) {
/*  414 */         cc.x = 0;
/*  415 */         rc.x = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  420 */       if (this.mqSPI.isIMS() && rc.x == 2002) {
/*  421 */         cc.x = 0;
/*  422 */         rc.x = 0;
/*      */       } 
/*      */       
/*  425 */       if (cc.x == 1) {
/*  426 */         this.mq.MQDISC(phconn, cc2, rc2);
/*      */       }
/*      */ 
/*      */       
/*  430 */       if (cc.x == 0) {
/*  431 */         String userid = jmqiConnOpts.getUserIdentifier();
/*  432 */         if (userid != null && !"".equals(userid.trim())) {
/*  433 */           Hconn hconn2 = phconn.getHconn();
/*  434 */           String password = jmqiConnOpts.getPassword();
/*  435 */           this.mq.authenticate(hconn2, userid, password, cc, rc);
/*  436 */           if (cc.x != 0) {
/*  437 */             this.mq.MQDISC(phconn, cc2, rc2);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  442 */       if (cc.x != 0) {
/*  443 */         if (Reason.isImpossibleReason(rc.x, cc.x, this.mqSPI)) {
/*  444 */           HashMap<String, Object> info = new HashMap<>();
/*  445 */           info.put("reason", rc);
/*  446 */           info.put("compcode", cc);
/*  447 */           info.put("queuemanager", this.queueManagerName);
/*  448 */           info.put("jmqiConnOpts", jmqiConnOpts);
/*  449 */           info.put("connOpts", connOpts);
/*  450 */           Trace.ffst(this, "<init>(WMQConnection,int,JmsPropertyContext)", "XN008001", info, JMSException.class);
/*      */         } 
/*  452 */         if (Reason.isConnectionBroken(rc.x)) {
/*  453 */           JMSException e = Reason.createException("JMSWMQ1107", null, rc.x, cc.x, this.environment);
/*  454 */           this.connection.driveExceptionListener(e, true);
/*      */         } 
/*      */         
/*  457 */         HashMap<String, String> inserts = new HashMap<>();
/*  458 */         inserts.put("XMSC_INSERT_QUEUE_MANAGER", this.queueManagerName);
/*  459 */         inserts.put("XMSC_WMQ_CONNECTION_MODE", WMQConnection.connectionModeMappings[getIntProperty("XMSC_WMQ_CONNECTION_MODE")]);
/*  460 */         inserts.put("XMSC_WMQ_HOST_NAME", getStringProperty("XMSC_WMQ_CONNECTION_NAME_LIST_INT"));
/*  461 */         JMSException je = Reason.createException("JMSWMQ0018", inserts, rc.x, cc.x, this.environment);
/*  462 */         if (Trace.isOn) {
/*  463 */           Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQSession", "connect()", (Throwable)je);
/*      */         }
/*  465 */         throw je;
/*      */       } 
/*  467 */       this.hconn = phconn.getHconn();
/*      */     } 
/*      */     
/*  470 */     if (Trace.isOn) {
/*  471 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "connect()");
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
/*      */   private void connectRRS() throws JMSException {
/*  489 */     if (Trace.isOn) {
/*  490 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "connectRRS()");
/*      */     }
/*      */     
/*  493 */     this.queueManagerName = this.connection.getQueueManagerName();
/*  494 */     this.environment = this.connection.getJmqiEnvironment();
/*  495 */     this.jmqiCompId = this.connection.getJmqiCompId();
/*  496 */     this.mq = this.connection.getJmqiMQ();
/*  497 */     this.mqSPI = (JmqiSP)this.mq;
/*  498 */     this.asyncPutCounter = new AsyncPutCounter((JmsPropertyContext)this);
/*      */ 
/*      */     
/*  501 */     JmqiConnectOptions jmqiConnOpts = this.connection.getJmqiConnectOptions();
/*  502 */     MQCNO connOpts = this.connection.getConnectOptions();
/*  503 */     Phconn phconn = this.environment.newPhconn();
/*  504 */     Pint cc = this.environment.newPint();
/*  505 */     Pint rc = this.environment.newPint();
/*  506 */     Pint cc2 = this.environment.newPint();
/*  507 */     Pint rc2 = this.environment.newPint();
/*      */ 
/*      */ 
/*      */     
/*  511 */     Hconn parentHconn = this.connection.getHconn();
/*  512 */     this.mqSPI.jmqiConnect(this.queueManagerName, jmqiConnOpts, connOpts, parentHconn, phconn, cc, rc);
/*      */ 
/*      */     
/*  515 */     if (cc.x == 1) {
/*  516 */       switch (rc.x) {
/*      */         case 2002:
/*      */         case 2267:
/*  519 */           cc.x = 0;
/*  520 */           rc.x = 0;
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  528 */     if (cc.x == 1) {
/*  529 */       this.mq.MQDISC(phconn, cc2, rc2);
/*      */     }
/*      */     
/*  532 */     if (cc.x != 0) {
/*  533 */       if (Reason.isImpossibleReason(rc.x, cc.x, null)) {
/*  534 */         HashMap<String, Object> info = new HashMap<>();
/*  535 */         info.put("reason", rc);
/*  536 */         info.put("compcode", cc);
/*  537 */         info.put("queuemanager", this.queueManagerName);
/*  538 */         info.put("jmqiConnOpts", jmqiConnOpts);
/*  539 */         info.put("connOpts", connOpts);
/*  540 */         Trace.ffst(this, "<init>(WMQConnection,int,JmsPropertyContext)", "XN008001", info, JMSException.class);
/*      */       } 
/*  542 */       if (Reason.isConnectionBroken(rc.x)) {
/*  543 */         JMSException e = Reason.createException("JMSWMQ1107", null, rc.x, cc.x, this.environment);
/*  544 */         this.connection.driveExceptionListener(e, true);
/*      */       } 
/*      */       
/*  547 */       HashMap<String, String> inserts = new HashMap<>();
/*  548 */       inserts.put("XMSC_INSERT_QUEUE_MANAGER", this.queueManagerName);
/*  549 */       inserts.put("XMSC_WMQ_CONNECTION_MODE", WMQConnection.connectionModeMappings[getIntProperty("XMSC_WMQ_CONNECTION_MODE")]);
/*  550 */       inserts.put("XMSC_WMQ_HOST_NAME", getStringProperty("XMSC_WMQ_CONNECTION_NAME_LIST_INT"));
/*  551 */       JMSException je = Reason.createException("JMSWMQ0018", inserts, rc.x, cc.x, this.environment);
/*  552 */       if (Trace.isOn) {
/*  553 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQSession", "connectRRS()", (Throwable)je);
/*      */       }
/*  555 */       throw je;
/*      */     } 
/*  557 */     this.hconn = phconn.getHconn();
/*      */ 
/*      */     
/*  560 */     this.mqSPI.honourRRS(this.hconn, cc, rc);
/*      */     
/*  562 */     if (cc.x != 0) {
/*  563 */       HashMap<String, Object> info = new HashMap<>();
/*  564 */       info.put("reason", rc);
/*  565 */       info.put("compcode", cc);
/*  566 */       info.put("queuemanager", this.queueManagerName);
/*  567 */       Trace.ffst(this, "connectRRS()", "XN008009", info, JMSException.class);
/*      */     } 
/*      */     
/*  570 */     if (Trace.isOn) {
/*  571 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "connectRRS()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void enableMessageReferenceProcessing() {
/*  582 */     if (Trace.isOn) {
/*  583 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "enableMessageReferenceProcessing");
/*      */       
/*  585 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "Waiting to lock asfConsumerInUseLock:" + this.asfConsumerInUseLock);
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
/*  596 */     this.asfConsumerInUseLock.lock();
/*      */     try {
/*  598 */       this.processMessageReferences = true;
/*      */     } finally {
/*      */       
/*  601 */       this.asfConsumerInUseLock.unlock();
/*      */     } 
/*  603 */     if (Trace.isOn) {
/*  604 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "asfConsumerInUseLock released:" + this.asfConsumerInUseLock);
/*      */       
/*  606 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "enableMessageReferenceProcessing");
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
/*      */   public void addAsyncConsumer() throws JMSException {
/*  618 */     if (Trace.isOn) {
/*  619 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "addAsyncConsumer()");
/*      */     }
/*  621 */     this.helper.addAsyncConsumer();
/*      */     
/*  623 */     if (Trace.isOn) {
/*  624 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "addAsyncConsumer()");
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
/*      */   private void callMQSTAT(Pint reason, Pint compcode) throws JMSException {
/*  638 */     if (Trace.isOn) {
/*  639 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "callMQSTAT(Pint,Pint)", new Object[] { reason, compcode });
/*      */     }
/*      */     
/*  642 */     MQSTS sts = this.environment.newMQSTS();
/*      */ 
/*      */ 
/*      */     
/*  646 */     boolean didSuspend = suspendAsyncService();
/*  647 */     this.mq.MQSTAT(this.hconn, 0, sts, compcode, reason);
/*  648 */     if (didSuspend) {
/*  649 */       resumeAsyncService();
/*      */     }
/*      */     
/*  652 */     if (0 != reason.x) {
/*  653 */       if (Reason.isImpossibleReason(reason.x, compcode.x, null)) {
/*  654 */         HashMap<String, Object> info = new HashMap<>();
/*  655 */         info.put("reason", reason);
/*  656 */         info.put("compcode", compcode);
/*  657 */         info.put("queuemanager", this.queueManagerName);
/*  658 */         info.put("hconn", this.hconn);
/*  659 */         Trace.ffst(this, "callMQSTAT()", "XN008006", info, JMSException.class);
/*      */       } 
/*  661 */       if (Reason.isConnectionBroken(reason.x)) {
/*  662 */         JMSException e = Reason.createException("JMSWMQ1107", null, reason.x, compcode.x, this.environment);
/*  663 */         this.connection.driveExceptionListener(e, true);
/*      */       } 
/*  665 */       JMSException je = Reason.createException("MQSTAT", reason.x, compcode.x, this.environment);
/*  666 */       if (Trace.isOn) {
/*  667 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQSession", "callMQSTAT(Pint,Pint)", (Throwable)je, 1);
/*      */       }
/*      */       
/*  670 */       throw je;
/*      */     } 
/*      */     
/*  673 */     if (sts.getReason() != 0 || sts.getPutFailureCount() != 0 || sts.getPutWarningCount() != 0) {
/*      */       
/*  675 */       StringBuffer wmqMsgbuff = new StringBuffer("JMSWMQ0002");
/*  676 */       wmqMsgbuff.append(": ");
/*  677 */       String wmqMsg = NLSServices.getMessage("JMSWMQ0002", new Object[] {
/*  678 */             Integer.toString(sts.getCompCode()), Integer.toString(sts.getReason()), sts
/*  679 */             .getObjectName(), sts.getResolvedObjectName(), 
/*  680 */             Integer.toString(sts.getObjectType()), sts.getObjectQMgrName(), sts
/*  681 */             .getResolvedQMgrName(), Integer.toString(sts.getPutFailureCount()), 
/*  682 */             Integer.toString(sts.getPutSuccessCount()), 
/*  683 */             Integer.toString(sts.getPutWarningCount()) });
/*  684 */       wmqMsgbuff.append(wmqMsg);
/*  685 */       wmqMsg = wmqMsgbuff.toString();
/*  686 */       MQException wmqex = new MQException(wmqMsg, "JMSWMQ0002", sts.getReason(), sts.getCompCode());
/*      */       
/*  688 */       JMSException je2 = (JMSException)NLSServices.createException("JMSWMQ0028", null);
/*  689 */       je2.setLinkedException((Exception)wmqex);
/*      */       
/*  691 */       if (Trace.isOn) {
/*  692 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQSession", "callMQSTAT(Pint,Pint)", (Throwable)je2, 2);
/*      */       }
/*      */       
/*  695 */       throw je2;
/*      */     } 
/*  697 */     if (Trace.isOn) {
/*  698 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "callMQSTAT(Pint,Pint)");
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
/*      */   private void callMQSTATQuietly(Pint reason, Pint compcode, WMQConsumerOwner.Operation operation, boolean invokeExceptionListener) {
/*  712 */     if (Trace.isOn) {
/*  713 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "callMQSTATQuietly(Pint,Pint,Operation,boolean)", new Object[] { reason, compcode, operation, 
/*  714 */             Boolean.valueOf(invokeExceptionListener) });
/*      */     }
/*      */     try {
/*  717 */       callMQSTAT(reason, compcode);
/*      */     }
/*  719 */     catch (JMSException je) {
/*  720 */       if (Trace.isOn) {
/*  721 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQSession", "callMQSTATQuietly(Pint,Pint,Operation,boolean)", (Throwable)je, 1);
/*      */       }
/*      */       
/*  724 */       if (invokeExceptionListener) {
/*      */         try {
/*  726 */           this.connection.driveExceptionListener(je, Reason.isConnectionBroken(reason.x));
/*      */         }
/*  728 */         catch (JMSException je2) {
/*  729 */           if (Trace.isOn) {
/*  730 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQSession", "callMQSTATQuietly(Pint,Pint,Operation,boolean)", (Throwable)je2, 2);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  737 */           HashMap<String, Object> ffstInfo = new HashMap<>();
/*  738 */           ffstInfo.put("Operation", (null == operation) ? "<null>" : operation.toString());
/*  739 */           ffstInfo.put("JMSException1", je);
/*  740 */           ffstInfo.put("JMSException2", je2);
/*  741 */           Trace.ffst(this, "operationPerformed", "XN008007", ffstInfo, null);
/*      */         } 
/*      */       }
/*      */     } 
/*  745 */     if (Trace.isOn) {
/*  746 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "callMQSTATQuietly(Pint,Pint,Operation,boolean)");
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
/*      */   public void close(boolean closingFromConnection) throws JMSException {
/*  765 */     if (Trace.isOn) {
/*  766 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "close(boolean)", new Object[] {
/*  767 */             Boolean.valueOf(closingFromConnection)
/*      */           });
/*      */     }
/*      */     try {
/*  771 */       Pint cc = this.environment.newPint();
/*  772 */       Pint rc = this.environment.newPint();
/*  773 */       if (this.ackMode != 0 && this.asyncPutCounter.getCounter() > 0)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  779 */         callMQSTATQuietly(rc, cc, (WMQConsumerOwner.Operation)null, true);
/*      */       }
/*      */ 
/*      */       
/*  783 */       disconnect();
/*      */     } finally {
/*      */       
/*  786 */       if (Trace.isOn) {
/*  787 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQSession", "close(boolean)");
/*      */       }
/*      */ 
/*      */       
/*  791 */       Trace.deRegisterFFSTObject(this);
/*      */     } 
/*      */     
/*  794 */     if (Trace.isOn) {
/*  795 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "close(boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void disconnect() throws JMSException {
/*  806 */     if (Trace.isOn) {
/*  807 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "disconnect()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  812 */     if (!this.mqSPI.isIMS()) {
/*  813 */       Pint cc = this.environment.newPint();
/*  814 */       Pint rc = this.environment.newPint();
/*  815 */       Phconn phconn = new Phconn(this.environment);
/*  816 */       phconn.setHconn(this.hconn);
/*  817 */       this.mq.MQDISC(phconn, cc, rc);
/*      */       
/*  819 */       if (0 != rc.x) {
/*  820 */         if (Reason.isImpossibleReason(rc.x, cc.x, null)) {
/*  821 */           HashMap<String, Object> info = new HashMap<>();
/*  822 */           info.put("reason", rc);
/*  823 */           info.put("compcode", cc);
/*  824 */           info.put("queuemanager", this.queueManagerName);
/*  825 */           info.put("hconn", this.hconn);
/*  826 */           Trace.ffst(this, "close()", "XN008003", info, JMSException.class);
/*      */         } 
/*  828 */         if (Reason.isConnectionBroken(rc.x)) {
/*  829 */           JMSException e = Reason.createException("JMSWMQ1107", null, rc.x, cc.x, this.environment);
/*  830 */           this.connection.driveExceptionListener(e, true);
/*      */         } else {
/*      */           
/*  833 */           HashMap<String, String> inserts = new HashMap<>();
/*  834 */           inserts.put("XMSC_INSERT_QUEUE_MANAGER", this.queueManagerName);
/*  835 */           inserts.put("XMSC_WMQ_CONNECTION_MODE", getStringProperty("XMSC_WMQ_CONNECTION_MODE"));
/*  836 */           inserts.put("XMSC_WMQ_HOST_NAME", getStringProperty("XMSC_WMQ_CONNECTION_NAME_LIST_INT"));
/*  837 */           JMSException je = Reason.createException("JMSWMQ0019", inserts, rc.x, cc.x, this.environment);
/*      */           
/*  839 */           if (Trace.isOn) {
/*  840 */             Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQSession", "disconnect()", (Throwable)je);
/*      */           }
/*  842 */           throw je;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  847 */     if (Trace.isOn) {
/*  848 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "disconnect()");
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
/*      */   public synchronized void commit() throws JMSException {
/*  864 */     if (Trace.isOn) {
/*  865 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "commit()");
/*      */     }
/*  867 */     syncpoint(true);
/*  868 */     if (Trace.isOn) {
/*  869 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "commit()");
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
/*      */   public ProviderQueueBrowser createBrowser(ProviderDestination destination, String selector, JmsPropertyContext propertyContext) throws JMSException {
/*  888 */     if (Trace.isOn) {
/*  889 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createBrowser(ProviderDestination,String,JmsPropertyContext)", new Object[] { destination, selector, propertyContext });
/*      */     }
/*      */ 
/*      */     
/*  893 */     ProviderQueueBrowser browser = new WMQQueueBrowser(this, destination, selector, propertyContext);
/*  894 */     if (Trace.isOn) {
/*  895 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createBrowser(ProviderDestination,String,JmsPropertyContext)", browser);
/*      */     }
/*      */     
/*  898 */     return browser;
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
/*      */   public ProviderMessageConsumer createConsumer(ProviderDestination destination, String selector, boolean nolocal, JmsPropertyContext propertyContext) throws JMSException {
/*  924 */     if (Trace.isOn) {
/*  925 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createConsumer(ProviderDestination,String,boolean,JmsPropertyContext)", new Object[] { destination, selector, 
/*      */             
/*  927 */             Boolean.valueOf(nolocal), propertyContext });
/*      */     }
/*  929 */     ProviderMessageConsumer consumer = new WMQMessageConsumer((WMQDestination)destination, this, null, selector, nolocal, false, false, propertyContext);
/*      */     
/*  931 */     if (Trace.isOn) {
/*  932 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createConsumer(ProviderDestination,String,boolean,JmsPropertyContext)", consumer);
/*      */     }
/*      */     
/*  935 */     return consumer;
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
/*      */   public ProviderMessageConsumer createDurableSubscriber(ProviderDestination destination, String subscriptionName, String selector, boolean nolocal, JmsPropertyContext propertyContext) throws JMSException {
/*  961 */     if (Trace.isOn) {
/*  962 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createDurableSubscriber(ProviderDestination,String,String,boolean,JmsPropertyContext)", new Object[] { destination, subscriptionName, selector, 
/*      */             
/*  964 */             Boolean.valueOf(nolocal), propertyContext });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  969 */     if (null == subscriptionName || subscriptionName.length() == 0) {
/*  970 */       Trace.ffst(this, "createDurableSubscriber", "XN008004", null, JMSException.class);
/*      */     }
/*      */     
/*  973 */     String fullSubName = createFullSubscriptionName(subscriptionName, true, false);
/*      */     
/*  975 */     ProviderMessageConsumer consumer = new WMQMessageConsumer((WMQDestination)destination, this, fullSubName, selector, nolocal, false, true, propertyContext);
/*      */ 
/*      */     
/*  978 */     if (Trace.isOn) {
/*  979 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createDurableSubscriber(ProviderDestination,String,String,boolean,JmsPropertyContext)", consumer);
/*      */     }
/*      */ 
/*      */     
/*  983 */     return consumer;
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
/*      */   public ProviderMessageConsumer createSharedConsumer(ProviderDestination destination, String sharedSubscriptionName, String selector, JmsPropertyContext propertyContext) throws JMSException {
/* 1003 */     if (Trace.isOn) {
/* 1004 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createSharedConsumer(ProviderDestination,String,String,JmsPropertyContext)", new Object[] { destination, sharedSubscriptionName, selector, propertyContext });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1010 */     if (null == sharedSubscriptionName || sharedSubscriptionName.length() == 0) {
/* 1011 */       Trace.ffst(this, "createSharedSubscriber", "XN008004", null, JMSException.class);
/*      */     }
/*      */     
/* 1014 */     String fullSubName = createFullSubscriptionName(sharedSubscriptionName, false, true);
/*      */     
/* 1016 */     ProviderMessageConsumer consumer = new WMQMessageConsumer((WMQDestination)destination, this, fullSubName, selector, false, true, false, propertyContext);
/*      */     
/* 1018 */     if (Trace.isOn) {
/* 1019 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createSharedConsumer(ProviderDestination,String,String,JmsPropertyContext)", consumer);
/*      */     }
/*      */     
/* 1022 */     return consumer;
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
/*      */   public ProviderMessageConsumer createSharedDurableConsumer(ProviderDestination destination, String sharedSubscriptionName, String selector, JmsPropertyContext propertyContext) throws JMSException {
/* 1043 */     if (Trace.isOn) {
/* 1044 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createSharedDurableConsumer(ProviderDestination,String,String,JmsPropertyContext)", new Object[] { destination, sharedSubscriptionName, selector, propertyContext });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1050 */     if (null == sharedSubscriptionName || sharedSubscriptionName.length() == 0) {
/* 1051 */       Trace.ffst(this, "createSharedDurableSubscriber", "XN008004", null, JMSException.class);
/*      */     }
/*      */     
/* 1054 */     String fullSubName = createFullSubscriptionName(sharedSubscriptionName, true, true);
/*      */     
/* 1056 */     ProviderMessageConsumer consumer = new WMQMessageConsumer((WMQDestination)destination, this, fullSubName, selector, false, true, true, propertyContext);
/*      */ 
/*      */     
/* 1059 */     if (Trace.isOn) {
/* 1060 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createSharedDurableConsumer(ProviderDestination,String,String,JmsPropertyContext)", consumer);
/*      */     }
/*      */ 
/*      */     
/* 1064 */     return consumer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String createFullSubscriptionName(String subscriptionName, boolean durable, boolean shared) throws JMSException {
/* 1073 */     if (Trace.isOn) {
/* 1074 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createFullSubscriptionName(String,boolean,boolean)", new Object[] { subscriptionName, 
/*      */             
/* 1076 */             Boolean.valueOf(durable), Boolean.valueOf(shared) });
/*      */     }
/* 1078 */     String clientid = getStringProperty("XMSC_CLIENT_ID");
/*      */     
/* 1080 */     String resolvedQMName = getStringProperty("XMSC_WMQ_RESOLVED_QUEUE_MANAGER");
/* 1081 */     String streamName = getStringProperty("XMSC_WMQ_BROKER_PUBQ");
/*      */     
/* 1083 */     String name = createFullSubscriptionName(clientid, subscriptionName, resolvedQMName, streamName, durable, shared);
/*      */     
/* 1085 */     if (Trace.isOn) {
/* 1086 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createFullSubscriptionName(String,boolean,boolean)", name);
/*      */     }
/*      */     
/* 1089 */     return name;
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
/*      */   public String createMessageID() {
/* 1102 */     if (Trace.isOn) {
/* 1103 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createMessageID()");
/*      */     }
/* 1105 */     if (Trace.isOn) {
/* 1106 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createMessageID()", null);
/*      */     }
/* 1108 */     return null;
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
/*      */   public ProviderMessageProducer createProducer(ProviderDestination destination, JmsPropertyContext propertyContext) throws JMSException {
/* 1123 */     if (Trace.isOn) {
/* 1124 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createProducer(ProviderDestination,JmsPropertyContext)", new Object[] { destination, propertyContext });
/*      */     }
/*      */ 
/*      */     
/* 1128 */     ProviderMessageProducer producer = new WMQMessageProducer(this, (WMQDestination)destination, propertyContext);
/* 1129 */     if (Trace.isOn) {
/* 1130 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createProducer(ProviderDestination,JmsPropertyContext)", producer);
/*      */     }
/*      */     
/* 1133 */     return producer;
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
/*      */   public ProviderDestination createTemporaryDestination(int destType, JmsPropertyContext propertyContext) throws JMSException {
/* 1148 */     if (Trace.isOn)
/* 1149 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createTemporaryDestination(int,JmsPropertyContext)", new Object[] {
/*      */             
/* 1151 */             Integer.valueOf(destType), propertyContext
/*      */           }); 
/* 1153 */     WMQDestination wMQDestination = this.connection.createTemporaryDestination(destType, propertyContext);
/* 1154 */     if (Trace.isOn) {
/* 1155 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "createTemporaryDestination(int,JmsPropertyContext)", wMQDestination);
/*      */     }
/*      */     
/* 1158 */     return (ProviderDestination)wMQDestination;
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
/*      */   public void deleteDurableSubscriber(String subscriptionName) throws JMSException {
/* 1171 */     if (Trace.isOn) {
/* 1172 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "deleteDurableSubscriber(String)", new Object[] { subscriptionName });
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
/* 1185 */     String fullSubName = createFullSubscriptionName(subscriptionName, true, true);
/*      */     try {
/* 1187 */       WMQMessageConsumer.deleteDurableSubscription(this, fullSubName);
/*      */     }
/* 1189 */     catch (JMSException je) {
/* 1190 */       if (Trace.isOn) {
/* 1191 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQSession", "deleteDurableSubscriber(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1194 */       Throwable t = je.getCause();
/* 1195 */       if (t instanceof MQException) {
/* 1196 */         MQException mqe = (MQException)t;
/* 1197 */         int rc = mqe.getReason();
/*      */         
/* 1199 */         if (rc == 2428)
/*      */         {
/*      */ 
/*      */           
/* 1203 */           fullSubName = createFullSubscriptionName(subscriptionName, true, false);
/* 1204 */           WMQMessageConsumer.deleteDurableSubscription(this, fullSubName);
/*      */         
/*      */         }
/*      */         else
/*      */         {
/* 1209 */           if (Trace.isOn) {
/* 1210 */             Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQSession", "deleteDurableSubscriber(String)", (Throwable)je, 1);
/*      */           }
/*      */           
/* 1213 */           throw je;
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1219 */         if (Trace.isOn) {
/* 1220 */           Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQSession", "deleteDurableSubscriber(String)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 1223 */         throw je;
/*      */       } 
/*      */     } 
/*      */     
/* 1227 */     if (Trace.isOn) {
/* 1228 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "deleteDurableSubscriber(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAckMode() {
/* 1239 */     if (Trace.isOn) {
/* 1240 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "getAckMode()", "getter", 
/* 1241 */           Integer.valueOf(this.ackMode));
/*      */     }
/* 1243 */     return this.ackMode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public WMQCommonConnection getConnection() {
/* 1252 */     if (Trace.isOn) {
/* 1253 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "getConnection()", "getter", this.connection);
/*      */     }
/*      */     
/* 1256 */     return this.connection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Hconn getHconn() {
/* 1264 */     if (Trace.isOn) {
/* 1265 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "getHconn()", "getter", this.hconn);
/*      */     }
/* 1267 */     return this.hconn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JmqiEnvironment getJmqiEnvironment() {
/* 1275 */     if (Trace.isOn) {
/* 1276 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "getJmqiEnvironment()", "getter", this.environment);
/*      */     }
/*      */     
/* 1279 */     return this.environment;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JmqiMQ getJmqiMQ() {
/* 1288 */     if (Trace.isOn) {
/* 1289 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "getJmqiMQ()", "getter", this.mq);
/*      */     }
/* 1291 */     return this.mq;
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
/*      */   public boolean isInGlobalTransaction() {
/* 1342 */     boolean xa = false;
/* 1343 */     boolean rrs = false;
/*      */     try {
/* 1345 */       short adminObjectType = getShortProperty("XMSC_ADMIN_OBJECT_TYPE");
/* 1346 */       xa = ((adminObjectType & 0x40) != 0);
/* 1347 */       rrs = ((adminObjectType & 0x100) != 0);
/*      */     }
/* 1349 */     catch (JMSException e) {
/* 1350 */       if (Trace.isOn) {
/* 1351 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQSession", "isInGlobalTransaction()", (Throwable)e);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1356 */     boolean isInUOW = ((rrs && Utils.isRRSTransactionInProgress()) || xa);
/*      */ 
/*      */     
/* 1359 */     if (!isInUOW) {
/* 1360 */       isInUOW = (this.mqSPI.isCICS() || this.mqSPI.isIMS());
/*      */     }
/*      */     
/* 1363 */     if (Trace.isOn) {
/* 1364 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "isInGlobalTransaction()", "getter", 
/* 1365 */           Boolean.valueOf(isInUOW));
/*      */     }
/* 1367 */     return isInUOW;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getTransacted() {
/* 1376 */     boolean transacted = (0 == this.ackMode);
/* 1377 */     boolean rrs = false;
/*      */     
/*      */     try {
/* 1380 */       short adminObjectType = getShortProperty("XMSC_ADMIN_OBJECT_TYPE");
/* 1381 */       rrs = ((adminObjectType & 0x100) != 0);
/*      */     }
/* 1383 */     catch (JMSException e) {
/* 1384 */       if (Trace.isOn) {
/* 1385 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQSession", "getTransacted()", (Throwable)e);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1390 */     boolean result = (transacted || (rrs && Utils.isRRSTransactionInProgress()));
/*      */     
/* 1392 */     if (Trace.isOn) {
/* 1393 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "getTransacted()", "getter", 
/* 1394 */           Boolean.valueOf(result));
/*      */     }
/* 1396 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAsyncRunning() {
/* 1407 */     boolean traceRet1 = this.helper.isAsyncRunning();
/* 1408 */     if (Trace.isOn) {
/* 1409 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "isAsyncRunning()", "getter", 
/* 1410 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1412 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSubscriptionInUse(String subscriptionName) {
/* 1422 */     if (Trace.isOn) {
/* 1423 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "isSubscriptionInUse(String)", new Object[] { subscriptionName });
/*      */     }
/*      */     
/* 1426 */     boolean traceRet1 = this.helper.isSubscriptionInUse(subscriptionName);
/* 1427 */     if (Trace.isOn) {
/* 1428 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "isSubscriptionInUse(String)", 
/* 1429 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1431 */     return traceRet1;
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
/*      */   public void loadMessageReference(ProviderMessageReference messageReference) throws JMSException {
/* 1444 */     if (Trace.isOn) {
/* 1445 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "loadMessageReference(ProviderMessageReference)", new Object[] { messageReference });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1450 */     WMQMessageReference msgRef = (WMQMessageReference)messageReference;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1455 */     WMQDestination destination = msgRef.getDestination();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1469 */     boolean lockObtained = false;
/* 1470 */     boolean remoteHconn = true;
/*      */     
/*      */     try {
/* 1473 */       if (this.hconn instanceof RemoteHconn) {
/* 1474 */         if (Trace.isOn) {
/* 1475 */           Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "loadMessageReference(ProviderMessageReference)", "Session is using Hconn " + this.hconn + ". Waiting to lock asfConsumerInUseLock:" + this.asfConsumerInUseLock);
/*      */         }
/*      */         
/* 1478 */         this.asfConsumerInUseLock.lock();
/* 1479 */         lockObtained = true;
/*      */       } else {
/*      */         
/* 1482 */         if (Trace.isOn) {
/* 1483 */           Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "loadMessageReference(ProviderMessageReference)", "Session is using LocalHconn " + this.hconn + ". Trying to lock asfConsumerInUseLock:" + this.asfConsumerInUseLock);
/*      */         }
/*      */         
/* 1486 */         remoteHconn = false;
/* 1487 */         lockObtained = this.asfConsumerInUseLock.tryLock();
/*      */       } 
/* 1489 */       if (lockObtained) {
/*      */         
/* 1491 */         if (Trace.isOn) {
/* 1492 */           Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "loadMessageReference(ProviderMessageReference)", "asfConsumerInUseLock obtained:" + this.asfConsumerInUseLock);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1501 */         if (remoteHconn || this.processMessageReferences) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1514 */           closeExistingAsfMessageConsumerIfRequired(destination);
/*      */           
/* 1516 */           if (null == this.asfConsumer) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1532 */             this.asfConsumer = new WMQSyncConsumerShadow((JmsPropertyContext)this, this, destination, null, false, false, false, null, msgRef.getSubID())
/*      */               {
/*      */                 int computeQueueOpenOptions() throws JMSException
/*      */                 {
/* 1536 */                   if (Trace.isOn) {
/* 1537 */                     Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "computeQueueOpenOptions()");
/*      */                   }
/*      */                   
/* 1540 */                   int options = super.computeQueueOpenOptions();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 1547 */                   options |= 0x2;
/* 1548 */                   options &= 0xFFFFFFFE;
/*      */ 
/*      */                   
/* 1551 */                   options |= 0x80000;
/*      */ 
/*      */ 
/*      */                   
/* 1555 */                   options &= 0xFFFFFFFF;
/* 1556 */                   options &= 0xFFEFFFFF;
/* 1557 */                   if (Trace.isOn) {
/* 1558 */                     Trace.exit(this, "com.ibm.msg.client.wmq.internal.null", "computeQueueOpenOptions()", 
/* 1559 */                         Integer.valueOf(options));
/*      */                   }
/* 1561 */                   return options;
/*      */                 }
/*      */ 
/*      */                 
/*      */                 int computeSubscriptionOptions() throws JMSException {
/* 1566 */                   if (Trace.isOn) {
/* 1567 */                     Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "computeSubscriptionOptions()");
/*      */                   }
/*      */                   
/* 1570 */                   int options = super.computeSubscriptionOptions();
/*      */                   
/* 1572 */                   options |= 0x80000;
/*      */ 
/*      */ 
/*      */                   
/* 1576 */                   options &= 0xFFFFFFFF;
/* 1577 */                   options &= 0xFFEFFFFF;
/* 1578 */                   if (Trace.isOn) {
/* 1579 */                     Trace.exit(this, "com.ibm.msg.client.wmq.internal.null", "computeSubscriptionOptions()", 
/* 1580 */                         Integer.valueOf(options));
/*      */                   }
/* 1582 */                   return options;
/*      */                 }
/*      */ 
/*      */                 
/*      */                 void close(ReentrantLock onMessageLock) throws JMSException {
/* 1587 */                   close(onMessageLock, false);
/*      */                 }
/*      */               };
/*      */ 
/*      */             
/* 1592 */             this.asfConsumer.initialize();
/*      */             
/* 1594 */             this.asfDestination = destination;
/*      */           } 
/*      */ 
/*      */           
/* 1598 */           byte[] token = msgRef.getToken();
/* 1599 */           if (Trace.isOn) {
/* 1600 */             Trace.data(this, "loadMessageReference", "message token", JmqiTools.arrayToHexString(token));
/*      */           }
/* 1602 */           int msgLen = msgRef.getMsgLength();
/* 1603 */           ProviderMessage message = this.asfConsumer.receive(token, msgLen);
/* 1604 */           msgRef.setMessage(message);
/*      */ 
/*      */ 
/*      */         
/*      */         }
/* 1609 */         else if (Trace.isOn) {
/* 1610 */           Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "loadMessageReference(ProviderMessageReference)", "Session has already been stopped. Aborting current message delivery");
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1619 */       else if (Trace.isOn) {
/* 1620 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "loadMessageReference(ProviderMessageReference)", "Unable to get asfConsumerInUseLock " + this.asfConsumerInUseLock + ". Aborting current message delivery");
/*      */       }
/*      */     
/*      */     }
/*      */     finally {
/*      */       
/* 1626 */       if (lockObtained) {
/*      */         
/* 1628 */         this.asfConsumerInUseLock.unlock();
/* 1629 */         if (Trace.isOn) {
/* 1630 */           Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "loadMessageReference(ProviderMessageReference)", "asfConsumerInUseLock released:" + this.asfConsumerInUseLock);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1636 */     if (Trace.isOn) {
/* 1637 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "loadMessageReference(ProviderMessageReference)");
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
/*      */   private void closeExistingAsfMessageConsumerIfRequired(WMQDestination messageReferenceDestination) throws JMSException {
/* 1657 */     if (Trace.isOn) {
/* 1658 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "closeExistingAsfMessageConsumerIfRequired(WMQDestination)");
/*      */     }
/*      */ 
/*      */     
/* 1662 */     if (this.asfConsumer != null && !messageReferenceDestination.equals(this.asfDestination)) {
/* 1663 */       this.asfConsumer.close();
/* 1664 */       this.asfConsumer = null;
/* 1665 */       this.asfDestination = null;
/*      */     } 
/*      */     
/* 1668 */     if (Trace.isOn) {
/* 1669 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "closeExistingAsfMessageConsumerIfRequired(WMQDestination)");
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
/*      */   public void operationPerformed(WMQConsumerOwner.Operation operation, boolean syncpoint) {
/* 1687 */     if (Trace.isOn) {
/* 1688 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "operationPerformed(Operation,boolean)", new Object[] { operation, 
/*      */             
/* 1690 */             Boolean.valueOf(syncpoint) });
/*      */     }
/* 1692 */     if (syncpoint) {
/* 1693 */       this.inSyncpoint = true;
/* 1694 */       if (WMQConsumerOwner.Operation.ASYNCPUT == operation) {
/* 1695 */         this.didRecovAsyncPut = true;
/*      */       }
/*      */     }
/* 1698 */     else if (operation.isAnyPut() && this.asyncPutCounter.incrementAndCheckLimit()) {
/* 1699 */       Pint reason = this.environment.newPint();
/* 1700 */       Pint compcode = this.environment.newPint();
/* 1701 */       callMQSTATQuietly(reason, compcode, operation, true);
/*      */     } 
/* 1703 */     if (Trace.isOn) {
/* 1704 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "operationPerformed(Operation,boolean)");
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
/*      */   public String getDestinationURI(byte[] flatMR) throws JMSException {
/* 1719 */     if (Trace.isOn) {
/* 1720 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "getDestinationURI(byte [ ])", new Object[] { flatMR });
/*      */     }
/*      */     
/* 1723 */     String uri = WMQMessageReference.getDestinationURI(flatMR);
/* 1724 */     if (Trace.isOn) {
/* 1725 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "getDestinationURI(byte [ ])", uri);
/*      */     }
/*      */     
/* 1728 */     return uri;
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
/*      */   public ProviderMessageReference recreateMessageReference(byte[] flatMR, ProviderDestination dest) throws JMSException {
/* 1743 */     if (Trace.isOn) {
/* 1744 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "recreateMessageReference(byte [ ],ProviderDestination)", new Object[] { flatMR, dest });
/*      */     }
/*      */     
/* 1747 */     ProviderMessageReference mr = new WMQMessageReference(flatMR, dest);
/* 1748 */     if (Trace.isOn) {
/* 1749 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "recreateMessageReference(byte [ ],ProviderDestination)", mr);
/*      */     }
/*      */     
/* 1752 */     return mr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeAsyncConsumer() throws JMSException {
/* 1762 */     if (Trace.isOn) {
/* 1763 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "removeAsyncConsumer()");
/*      */     }
/* 1765 */     this.helper.removeAsyncConsumer();
/* 1766 */     if (Trace.isOn) {
/* 1767 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "removeAsyncConsumer()");
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
/*      */   public WMQHobjCache getHobjCache() {
/* 1780 */     if (Trace.isOn) {
/* 1781 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "getHobjCache()", "getter", this.hobjCache);
/*      */     }
/*      */     
/* 1784 */     return this.hobjCache;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeSubscription(String subscriptionName) {
/* 1793 */     if (Trace.isOn) {
/* 1794 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "removeSubscription(String)", new Object[] { subscriptionName });
/*      */     }
/* 1796 */     this.helper.removeSubscription(subscriptionName);
/* 1797 */     if (Trace.isOn) {
/* 1798 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "removeSubscription(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resumeAsyncService() throws JMSException {
/* 1809 */     if (Trace.isOn) {
/* 1810 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "resumeAsyncService()");
/*      */     }
/*      */     
/* 1813 */     this.helper.resumeAsyncService();
/* 1814 */     if (Trace.isOn) {
/* 1815 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "resumeAsyncService()");
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
/*      */   public synchronized void rollback() throws JMSException {
/* 1830 */     if (Trace.isOn) {
/* 1831 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "rollback()");
/*      */     }
/* 1833 */     syncpoint(false);
/* 1834 */     if (Trace.isOn) {
/* 1835 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "rollback()");
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
/*      */   public void start() throws JMSException {
/* 1847 */     if (Trace.isOn) {
/* 1848 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "start()");
/*      */     }
/*      */     
/* 1851 */     this.helper.start();
/*      */     
/* 1853 */     if (Trace.isOn) {
/* 1854 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "start()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void stop() throws JMSException {
/* 1865 */     if (Trace.isOn) {
/* 1866 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "stop()");
/*      */     }
/*      */     
/* 1869 */     if (Trace.isOn) {
/* 1870 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "stop()", "Waiting for asfConsumerInUseLock:" + this.asfConsumerInUseLock);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1875 */     this.asfConsumerInUseLock.lock();
/*      */     try {
/* 1877 */       if (Trace.isOn) {
/* 1878 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "stop()", "asfConsumerInUseLock obtained:" + this.asfConsumerInUseLock);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1883 */       if (this.asfConsumer != null) {
/* 1884 */         this.asfConsumer.close();
/* 1885 */         this.asfConsumer = null;
/*      */       } 
/*      */     } finally {
/*      */       
/* 1889 */       if (Trace.isOn) {
/* 1890 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQSession", "stop()");
/*      */       }
/*      */ 
/*      */       
/* 1894 */       this.processMessageReferences = false;
/*      */ 
/*      */       
/* 1897 */       this.asfConsumerInUseLock.unlock();
/* 1898 */       if (Trace.isOn) {
/* 1899 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "stop()", "asfConsumerInUseLock released:" + this.asfConsumerInUseLock);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1904 */     this.helper.stop();
/* 1905 */     if (Trace.isOn) {
/* 1906 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "stop()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean suspendAsyncService() throws JMSException {
/* 1917 */     if (Trace.isOn) {
/* 1918 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "suspendAsyncService()");
/*      */     }
/*      */     
/* 1921 */     boolean didSuspend = this.helper.suspendAsyncService();
/* 1922 */     if (Trace.isOn) {
/* 1923 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "suspendAsyncService()", 
/* 1924 */           Boolean.valueOf(didSuspend));
/*      */     }
/* 1926 */     return didSuspend;
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
/*      */   private void syncpoint(boolean commit) throws JMSException {
/* 1940 */     if (Trace.isOn)
/* 1941 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "syncpoint(boolean)", new Object[] {
/* 1942 */             Boolean.valueOf(commit)
/*      */           }); 
/* 1944 */     Pint compcode = this.environment.newPint();
/* 1945 */     Pint reason = this.environment.newPint();
/* 1946 */     JMSException je = null;
/*      */     
/* 1948 */     if (this.inSyncpoint || 0 == this.ackMode) {
/* 1949 */       this.inSyncpoint = false;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1954 */       boolean didSuspend = suspendAsyncService();
/* 1955 */       if (commit) {
/* 1956 */         this.mq.MQCMIT(this.hconn, compcode, reason);
/*      */         
/* 1958 */         if (compcode.x == 1 && reason.x == 2408) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1963 */           compcode.x = 0;
/* 1964 */           reason.x = 0;
/*      */         } 
/*      */       } else {
/*      */         
/* 1968 */         this.mq.MQBACK(this.hconn, compcode, reason);
/*      */       } 
/* 1970 */       if (didSuspend) {
/* 1971 */         resumeAsyncService();
/*      */       }
/*      */       
/* 1974 */       if (0 != reason.x) {
/* 1975 */         String method = commit ? "MQCMIT" : "MQBACK";
/* 1976 */         if (Reason.isImpossibleReason(reason.x, compcode.x, this.mqSPI)) {
/* 1977 */           HashMap<String, Object> info = new HashMap<>();
/* 1978 */           info.put("reason", reason);
/* 1979 */           info.put("compcode", compcode);
/* 1980 */           info.put("queuemanager", this.queueManagerName);
/* 1981 */           info.put("method", method);
/* 1982 */           info.put("hconn", this.hconn);
/* 1983 */           Trace.ffst(this, "syncpoint()", "XN008005", info, JMSException.class);
/*      */         } 
/* 1985 */         if (Reason.isConnectionBroken(reason.x)) {
/* 1986 */           JMSException e = Reason.createException("JMSWMQ1107", null, reason.x, compcode.x, this.environment);
/* 1987 */           this.connection.driveExceptionListener(e, true);
/*      */         } 
/* 1989 */         je = Reason.createException(method, reason.x, compcode.x, this.environment);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1995 */     if (0 == this.ackMode && this.didRecovAsyncPut) {
/*      */       
/*      */       try {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2013 */         if (je == null) {
/* 2014 */           callMQSTATQuietly(reason, compcode, (WMQConsumerOwner.Operation)null, false);
/*      */         } else {
/*      */           
/* 2017 */           callMQSTAT(reason, compcode);
/*      */         }
/*      */       
/* 2020 */       } catch (JMSException je2) {
/* 2021 */         if (Trace.isOn) {
/* 2022 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQSession", "syncpoint(boolean)", (Throwable)je2);
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
/* 2033 */         if (commit)
/*      */         {
/*      */ 
/*      */           
/* 2037 */           je.setLinkedException((Exception)je2);
/*      */         }
/*      */       } finally {
/*      */         
/* 2041 */         if (Trace.isOn) {
/* 2042 */           Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQSession", "syncpoint(boolean)");
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2047 */         if (0 == reason.x) {
/* 2048 */           this.didRecovAsyncPut = false;
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/* 2053 */     if (null != je) {
/* 2054 */       if (Trace.isOn) {
/* 2055 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQSession", "syncpoint(boolean)", (Throwable)je);
/*      */       }
/*      */       
/* 2058 */       throw je;
/*      */     } 
/* 2060 */     if (Trace.isOn) {
/* 2061 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "syncpoint(boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public WMQThreadLocalStorage getThreadLocalStorage() {
/* 2071 */     JmqiSystemEnvironment sysenv = (JmqiSystemEnvironment)this.environment;
/* 2072 */     WMQThreadLocalStorage traceRet1 = (WMQThreadLocalStorage)sysenv.getComponentTls(this.jmqiCompId);
/* 2073 */     if (Trace.isOn) {
/* 2074 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "getThreadLocalStorage()", "getter", traceRet1);
/*      */     }
/*      */     
/* 2077 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void decrementCloseCounter() {
/* 2085 */     if (Trace.isOn) {
/* 2086 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "decrementCloseCounter()");
/*      */     }
/* 2088 */     this.helper.decrementCloseCounter();
/* 2089 */     if (Trace.isOn) {
/* 2090 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "decrementCloseCounter()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void incrementCloseCounter() {
/* 2100 */     if (Trace.isOn) {
/* 2101 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "incrementCloseCounter()");
/*      */     }
/* 2103 */     this.helper.incrementCloseCounter();
/* 2104 */     if (Trace.isOn) {
/* 2105 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "incrementCloseCounter()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCloseCounter() {
/* 2116 */     int traceRet1 = this.helper.getCloseCounter();
/* 2117 */     if (Trace.isOn) {
/* 2118 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "getCloseCounter()", "getter", 
/* 2119 */           Integer.valueOf(traceRet1));
/*      */     }
/* 2121 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 2130 */     if (Trace.isOn) {
/* 2131 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "toString()");
/*      */     }
/* 2133 */     TableBuilder builder = new TableBuilder();
/* 2134 */     builder.append("Instance", super.toString());
/* 2135 */     if (this.connection == null) {
/* 2136 */       builder.append("Parent Connection", "<null>");
/*      */     } else {
/*      */       
/* 2139 */       builder.append("Parent Connection", this.connection.getClass().getName() + '@' + Integer.toHexString(this.connection.hashCode()));
/*      */     } 
/* 2141 */     builder.append("ackMode", Integer.valueOf(this.ackMode));
/* 2142 */     builder.append("asfConsumer", this.asfConsumer);
/* 2143 */     builder.append("asfDestination", this.asfDestination);
/* 2144 */     builder.append("asyncPutCounter", this.asyncPutCounter);
/* 2145 */     builder.append("didRecovAsyncPut", Boolean.valueOf(this.didRecovAsyncPut));
/* 2146 */     builder.append("helper", this.helper);
/* 2147 */     builder.append("inSyncpoint", Boolean.valueOf(this.inSyncpoint));
/* 2148 */     builder.append("queueManagerName", this.queueManagerName);
/*      */     
/* 2150 */     String traceRet1 = builder.toString();
/* 2151 */     if (Trace.isOn) {
/* 2152 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "toString()", traceRet1);
/*      */     }
/* 2154 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONObject toJson() {
/* 2162 */     return StringableProperty.jsonIfy((JmsReadablePropertyContext)this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dump(PrintWriter pw, int level) {
/* 2170 */     if (Trace.isOn) {
/* 2171 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "dump(PrintWriter,int)", new Object[] { pw, 
/* 2172 */             Integer.valueOf(level) });
/*      */     }
/* 2174 */     String prefix = Trace.buildPrefix(level);
/* 2175 */     pw.format("%s%s%n", new Object[] { prefix, super.toString() });
/* 2176 */     if (this.creator != null) {
/* 2177 */       pw.format("%s  created by \"%s\" (id: %d) @ %s - stack :%n", new Object[] { prefix, this.creator
/* 2178 */             .getName(), Long.valueOf(this.creator.getId()), Trace.formatTimeStamp(this.createTime) });
/* 2179 */       Trace.dumpStack(pw, level + 1, this.createStack);
/*      */     } 
/* 2181 */     if (this.connection == null) {
/* 2182 */       pw.format("%s  Parent Connection <null>%n", new Object[] { prefix });
/*      */     } else {
/*      */       
/* 2185 */       pw.format("%s  Parent Connection %s@%x%n", new Object[] { prefix, this.connection.getClass().getName(), Integer.valueOf(this.connection.hashCode()) });
/*      */     } 
/*      */     
/* 2188 */     pw.format("%s  ackMode 0x%x%n", new Object[] { prefix, Integer.valueOf(this.ackMode) });
/* 2189 */     pw.format("%s  asfConsumer %s%n", new Object[] { prefix, String.valueOf(this.asfConsumer) });
/* 2190 */     pw.format("%s  asfDestination %s%n", new Object[] { prefix, String.valueOf(this.asfDestination) });
/* 2191 */     pw.format("%s  asyncPutCounter %s%n", new Object[] { prefix, String.valueOf(this.asyncPutCounter) });
/* 2192 */     pw.format("%s  helper %s%n", new Object[] { prefix, String.valueOf(this.helper) });
/* 2193 */     pw.format("%s  inSyncpoint %b%n", new Object[] { prefix, Boolean.valueOf(this.inSyncpoint) });
/* 2194 */     pw.format("%s  queueManagerName %s%n", new Object[] { prefix, String.valueOf(this.queueManagerName) });
/* 2195 */     if (Trace.isOn) {
/* 2196 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "dump(PrintWriter,int)");
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
/*      */   public boolean isMessageAlien(ProviderMessage message) throws JMSException {
/* 2210 */     if (Trace.isOn) {
/* 2211 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "isMessageAlien(ProviderMessage)", new Object[] { message });
/*      */     }
/*      */     
/* 2214 */     if (message instanceof com.ibm.msg.client.wmq.common.internal.messages.WMQMessage) {
/* 2215 */       if (Trace.isOn) {
/* 2216 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "isMessageAlien(ProviderMessage)", 
/* 2217 */             Boolean.valueOf(false), 1);
/*      */       }
/* 2219 */       return false;
/*      */     } 
/* 2221 */     if (Trace.isOn) {
/* 2222 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "isMessageAlien(ProviderMessage)", 
/* 2223 */           Boolean.valueOf(true), 2);
/*      */     }
/* 2225 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean isInSyncPoint() {
/* 2230 */     if (Trace.isOn) {
/* 2231 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "isInSyncPoint()", "getter", 
/* 2232 */           Boolean.valueOf(this.inSyncpoint));
/*      */     }
/* 2234 */     return this.inSyncpoint;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 2242 */     if (Trace.isOn) {
/* 2243 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "readObject(ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */     
/* 2246 */     NotSerializableException traceRet1 = new NotSerializableException();
/* 2247 */     if (Trace.isOn) {
/* 2248 */       Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQSession", "readObject(ObjectInputStream)", traceRet1);
/*      */     }
/*      */     
/* 2251 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 2260 */     if (Trace.isOn) {
/* 2261 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "writeObject(ObjectOutputStream)", new Object[] { out });
/*      */     }
/*      */     
/* 2264 */     NotSerializableException traceRet1 = new NotSerializableException();
/* 2265 */     if (Trace.isOn) {
/* 2266 */       Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQSession", "writeObject(ObjectOutputStream)", traceRet1);
/*      */     }
/*      */     
/* 2269 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/* 2278 */     if (Trace.isOn) {
/* 2279 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "equals(final Object)", new Object[] { obj });
/*      */     }
/*      */     
/* 2282 */     boolean traceRet1 = super.equals(obj);
/* 2283 */     if (Trace.isOn) {
/* 2284 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "equals(final Object)", 
/* 2285 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 2287 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 2295 */     if (Trace.isOn) {
/* 2296 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "hashCode()");
/*      */     }
/* 2298 */     int traceRet1 = super.hashCode();
/* 2299 */     if (Trace.isOn) {
/* 2300 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "hashCode()", 
/* 2301 */           Integer.valueOf(traceRet1));
/*      */     }
/* 2303 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void syncpoint(boolean commit, boolean forceTransactedCommit, WMQDestination destination) throws JMSException {
/* 2313 */     if (Trace.isOn) {
/* 2314 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "syncpoint(boolean,boolean,WMQDestination)", new Object[] {
/* 2315 */             Boolean.valueOf(commit), 
/* 2316 */             Boolean.valueOf(forceTransactedCommit), destination
/*      */           });
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
/* 2328 */     if (getTransacted() || getAckMode() == 2) {
/* 2329 */       if (commit && forceTransactedCommit && !isInGlobalTransaction()) {
/* 2330 */         commit();
/*      */       
/*      */       }
/*      */     }
/* 2334 */     else if (commit) {
/* 2335 */       commit();
/*      */     } else {
/*      */       
/* 2338 */       rollback();
/*      */     } 
/*      */     
/* 2341 */     if (Trace.isOn) {
/* 2342 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "syncpoint(boolean,boolean,WMQDestination)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reconnected() {
/* 2353 */     if (Trace.isOn) {
/* 2354 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "reconnected()");
/*      */     }
/*      */     
/* 2357 */     this.propertyRefreshNeeded = true;
/*      */     
/* 2359 */     if (Trace.isOn) {
/* 2360 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "reconnected()");
/*      */     }
/*      */   }
/*      */   
/*      */   private void updateQmNameEtc() throws JMSException {
/* 2365 */     if (Trace.isOn) {
/* 2366 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQSession", "updateQmNameEtc()");
/*      */     }
/*      */     
/*      */     try {
/* 2370 */       Hconn myHconn = getHconn();
/* 2371 */       if (myHconn instanceof RemoteHconn)
/*      */       {
/* 2373 */         ((RemoteHconn)myHconn).enterCall(false, false);
/*      */       }
/*      */       
/*      */       try {
/* 2377 */         String qmName = myHconn.getName();
/* 2378 */         if (qmName != null) {
/* 2379 */           qmName = qmName.trim();
/*      */         }
/* 2381 */         setStringProperty("XMSC_WMQ_RESOLVED_QUEUE_MANAGER", qmName);
/*      */         
/* 2383 */         setStringProperty("XMSC_WMQ_RESOLVED_QUEUE_MANAGER_ID", myHconn.getUid());
/*      */         
/* 2385 */         setStringProperty("XMSC_WMQ_CONNECTION_ID", myHconn.getConnectionIdAsString());
/*      */         
/* 2387 */         String qsgName = myHconn.getQsgName();
/* 2388 */         if (qsgName != null && qsgName.equals(this.queueManagerName))
/*      */         {
/* 2390 */           setStringProperty("XMSC_WMQ_RESOLVED_QUEUE_SHARING_GROUP_NAME", qsgName);
/*      */         }
/*      */         
/* 2393 */         byte[] connTag = myHconn.getConnTag();
/* 2394 */         if (connTag != null) {
/* 2395 */           setStringProperty("XMSC_WMQ_RESOLVED_CONNECTION_TAG", new String(connTag));
/*      */         }
/* 2397 */         this.propertyRefreshNeeded = false;
/*      */       }
/*      */       finally {
/*      */         
/* 2401 */         if (myHconn instanceof RemoteHconn)
/*      */         {
/* 2403 */           ((RemoteHconn)myHconn).leaveCall(0);
/*      */         }
/*      */       }
/*      */     
/*      */     }
/* 2408 */     catch (JmqiException e) {
/* 2409 */       if (Trace.isOn) {
/* 2410 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQSession", "updateQmNameEtc()", (Throwable)e, 1);
/*      */       }
/*      */ 
/*      */       
/* 2414 */       HashMap<String, String> inserts = new HashMap<>();
/* 2415 */       inserts.put("XMSC_INSERT_COMP_CODE", Integer.toString(e.getCompCode()));
/* 2416 */       inserts.put("XMSC_INSERT_REASON", Integer.toString(e.getReason()));
/* 2417 */       inserts.put("XMSC_INSERT_METHOD", "updateQmNameEtc()");
/*      */       
/* 2419 */       String exceptionMessage = "JMSWMQ2019";
/*      */ 
/*      */       
/* 2422 */       JMSException je = Reason.createException(exceptionMessage, inserts, e.getReason(), e.getCompCode(), this.environment);
/* 2423 */       je.setLinkedException((Exception)e);
/* 2424 */       if (Trace.isOn) {
/* 2425 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQSession", "updateQmNameEtc()", (Throwable)je, 3);
/*      */       }
/* 2427 */       throw je;
/*      */     } 
/* 2429 */     if (Trace.isOn) {
/* 2430 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQSession", "updateQmNameEtc()");
/*      */     }
/*      */   }
/*      */   
/* 2434 */   private static final Set<String> specialProperties = new HashSet<>();
/*      */   static {
/* 2436 */     specialProperties.add("XMSC_WMQ_RESOLVED_QUEUE_MANAGER");
/* 2437 */     specialProperties.add("XMSC_WMQ_RESOLVED_QUEUE_MANAGER_ID");
/*      */     
/* 2439 */     specialProperties.add("XMSC_WMQ_RESOLVED_QUEUE_SHARING_GROUP_NAME");
/* 2440 */     specialProperties.add("XMSC_WMQ_RESOLVED_CONNECTION_TAG");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getObjectProperty(String name) throws JMSException {
/* 2448 */     if (specialProperties.contains(name) && this.propertyRefreshNeeded) {
/* 2449 */       updateQmNameEtc();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2456 */     return super.getObjectProperty(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void lockHconn() {
/* 2465 */     if (Trace.isOn) {
/* 2466 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "lockHconn", null);
/*      */     }
/* 2468 */     this.helper.lockHconn();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unlockHconn() {
/* 2476 */     if (Trace.isOn) {
/* 2477 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "unlockHconn", null);
/*      */     }
/* 2479 */     this.helper.unlockHconn();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void awaitHconn() throws InterruptedException {
/* 2488 */     if (Trace.isOn) {
/* 2489 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "awaitHconn", null);
/*      */     }
/* 2491 */     this.helper.awaitHconn();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void signalHconn() {
/* 2499 */     if (Trace.isOn) {
/* 2500 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "signalHconn", null);
/*      */     }
/* 2502 */     this.helper.signalHconn();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean haveHconnLock() {
/* 2510 */     boolean result = this.helper.haveHconnLock();
/* 2511 */     if (Trace.isOn) {
/* 2512 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQSession", "haveHconnLock()", null);
/*      */     }
/* 2514 */     return result;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\WMQSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */