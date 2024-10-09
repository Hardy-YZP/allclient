/*      */ package com.ibm.msg.client.wmq.compat.base.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.constants.CMQC;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.handles.Hobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.internal.Configuration;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.TimeZone;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQQueue
/*      */   extends MQManagedObject
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQQueue.java";
/*      */   private static final int MQ_Q_NAME_LENGTH = 48;
/*      */   private static final int MQCA_BASE_Q_NAME = 2002;
/*      */   private static final int MQCA_REMOTE_Q_MGR_NAME = 2017;
/*      */   private static final int MQCA_REMOTE_Q_NAME = 2018;
/*      */   private static final int MQIA_CURRENT_Q_DEPTH = 3;
/*      */   private static final int MQIA_DEFINITION_TYPE = 7;
/*      */   private static final int MQIA_NPM_CLASS = 78;
/*      */   private static final int MQIA_Q_TYPE = 20;
/*      */   public static final int spiGETOPT_INHERIT = 1;
/*      */   public static final int spiGETOPT_SHORT_TXN = 16;
/*      */   public static final int spiPUTOPT_ASYNC = 32;
/*      */   public static final int spiPUTOPT_BLANK_PADDED = 1;
/*      */   public static final int spiPUTOPT_DEFERRED = 4;
/*      */   public static final int spiPUTOPT_NONE = 0;
/*      */   public static final int spiPUTOPT_PUT_AND_FORGET = 8;
/*      */   public static final int spiPUTOPT_SYNCPOINT_IF_YOU_LIKE = 2;
/*      */   public static final int spiGETOPT_COMMIT = 2;
/*      */   public static final int spiGETOPT_COMMIT_ASYNC = 8;
/*      */   public static final int spiGETOPT_COMMIT_IF_YOU_LIKE = 4;
/*      */   
/*      */   static {
/*   47 */     if (Trace.isOn) {
/*   48 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQQueue.java");
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
/*   96 */   private static final byte[] MQMI_NONE = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int MQNPM_CLASS_HIGH = 10;
/*      */ 
/*      */ 
/*      */   
/*  105 */   private static JmqiEnvironment jmqiEnv = MQSESSION.getJmqiEnv();
/*      */   
/*  107 */   private Pint msgLength = jmqiEnv.newPint();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  112 */   private int DefaultMsgLength = Default_DefaultMsgLength;
/*      */   
/*  114 */   private static int Default_DefaultMsgLength = 4096;
/*      */ 
/*      */   
/*  117 */   private ByteBuffer spiGetBuffer = ByteBuffer.wrap(new byte[this.DefaultMsgLength]);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  122 */   private static int smallMsgsBufferReductionThreshold = 10;
/*      */   
/*      */   private static final String smallMsgsBufferReductionThresholdProperty = "com.ibm.msg.client.wmq.compat.base.internal.MQQueue.smallMsgsBufferReductionThreshold";
/*  125 */   protected ByteBuffer baseJavaGetBuffer = ByteBuffer.wrap(new byte[this.DefaultMsgLength]);
/*      */   
/*  127 */   protected volatile MQQueueManager mgr = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  132 */   private int msgsTooSmallForBuffer = 0;
/*  133 */   private GregorianCalendar putCalendar = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/*  139 */     if (Trace.isOn) {
/*  140 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "static()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  149 */     PropertyStore.register("com.ibm.msg.client.wmq.compat.base.internal.MQQueue.smallMsgsBufferReductionThreshold", smallMsgsBufferReductionThreshold, Long.valueOf(0L), null);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  154 */     smallMsgsBufferReductionThreshold = (int)PropertyStore.getLongProperty("com.ibm.msg.client.wmq.compat.base.internal.MQQueue.smallMsgsBufferReductionThreshold");
/*      */     
/*  156 */     Configuration config = new Configuration(jmqiEnv);
/*  157 */     Default_DefaultMsgLength = config.getIntValue(Configuration.defaultMaxMsgSizeProperty);
/*  158 */     if (Trace.isOn) {
/*  159 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "Set defaultMessageLength (com.ibm.mq.jmqi.defaultMaxMsgSize) to ", 
/*  160 */           Integer.valueOf(Default_DefaultMsgLength));
/*      */     }
/*      */     
/*  163 */     if (Trace.isOn) {
/*  164 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "static()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQQueue() {
/*  173 */     if (Trace.isOn) {
/*  174 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "<init>()");
/*      */     }
/*  176 */     if (Trace.isOn) {
/*  177 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "<init>()");
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
/*      */   public MQQueue(MQQueueManager qMgr, String queueName, int openOptions, String queueManagerName, String dynamicQueueName, String alternateUserId) throws MQException {
/*  230 */     if (Trace.isOn) {
/*  231 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "<init>(MQQueueManager,String,int,String,String,String)", new Object[] { qMgr, queueName, 
/*      */             
/*  233 */             Integer.valueOf(openOptions), queueManagerName, dynamicQueueName, alternateUserId });
/*      */     }
/*      */     
/*  236 */     if (qMgr == null) {
/*  237 */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI001");
/*  238 */       if (Trace.isOn) {
/*  239 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "<init>(MQQueueManager,String,int,String,String,String)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/*  242 */       throw traceRet1;
/*      */     } 
/*      */     
/*  245 */     if (!qMgr.connected) {
/*  246 */       MQException traceRet2 = new MQException(2, 2018, this, "MQJI002");
/*  247 */       if (Trace.isOn) {
/*  248 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "<init>(MQQueueManager,String,int,String,String,String)", (Throwable)traceRet2, 2);
/*      */       }
/*      */       
/*  251 */       throw traceRet2;
/*      */     } 
/*      */     
/*  254 */     MQOD od = new MQOD();
/*      */     
/*  256 */     if (queueName != null && queueName.length() > 0) {
/*  257 */       od.ObjectName = queueName;
/*      */     }
/*      */     
/*  260 */     if (queueManagerName != null && queueManagerName.length() > 0) {
/*  261 */       od.ObjectQMgrName = queueManagerName;
/*      */     }
/*      */     
/*  264 */     if (dynamicQueueName != null && dynamicQueueName.length() > 0) {
/*  265 */       od.DynamicQName = dynamicQueueName;
/*      */     }
/*      */     
/*  268 */     if (alternateUserId != null && alternateUserId.length() > 0) {
/*  269 */       od.AlternateUserId = alternateUserId;
/*      */     }
/*      */     
/*  272 */     this.hconn = qMgr.hconn;
/*  273 */     this.connected = qMgr.connected;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  279 */     if (Trace.isOn) {
/*  280 */       Trace.traceData(this, "queue = " + od.ObjectName + "\nqueue manager = " + od.ObjectQMgrName + "\ndynamic queue name = " + od.DynamicQName + "\nalternate user id = " + od.AlternateUserId + "\noptions = " + openOptions, null);
/*      */     }
/*      */ 
/*      */     
/*  284 */     this.osession = qMgr.getSession();
/*  285 */     this.osession.MQOPEN(this.hconn, od, openOptions, this.phobj, this.completionCode, this.reason);
/*      */ 
/*      */     
/*  288 */     if (this.completionCode.x != 0 || this.reason.x != 0) {
/*  289 */       this.resourceOpen = false;
/*  290 */       MQException mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*      */       
/*  292 */       qMgr.errorOccurred(mqe);
/*      */       
/*  294 */       if (Trace.isOn) {
/*  295 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "<init>(MQQueueManager,String,int,String,String,String)", (Throwable)mqe, 3);
/*      */       }
/*      */       
/*  298 */       throw mqe;
/*      */     } 
/*      */ 
/*      */     
/*  302 */     this.resourceOpen = true;
/*  303 */     this.mgr = qMgr;
/*      */ 
/*      */     
/*  306 */     this.name = od.ObjectName;
/*  307 */     this.openOptions = openOptions;
/*      */     
/*  309 */     this.openStatus = true;
/*      */     
/*  311 */     this.parentQmgr = qMgr;
/*  312 */     this.connectionReference = qMgr;
/*  313 */     if (alternateUserId != null) {
/*  314 */       this.alternateUserId = alternateUserId;
/*      */     }
/*  316 */     this.mqca_description = 2013;
/*      */     
/*  318 */     qMgr.registerQueue(this);
/*      */     
/*  320 */     if (Trace.isOn) {
/*  321 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "<init>(MQQueueManager,String,int,String,String,String)");
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
/*      */   public final int _getConnectionCCSID() {
/*  335 */     if (Trace.isOn) {
/*  336 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "_getConnectionCCSID()");
/*      */     }
/*      */     
/*  339 */     if (this.osession == null) {
/*  340 */       this.osession = this.mgr.getSession();
/*      */     }
/*  342 */     int traceRet1 = this.osession.getConnectionCCSID();
/*  343 */     if (Trace.isOn) {
/*  344 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "_getConnectionCCSID()", 
/*  345 */           Integer.valueOf(traceRet1));
/*      */     }
/*  347 */     return traceRet1;
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
/*      */   public void close() throws MQException {
/*  360 */     if (Trace.isOn) {
/*  361 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "close()");
/*      */     }
/*      */     
/*  364 */     super.close();
/*  365 */     if (this.connectionReference != null) {
/*  366 */       this.connectionReference.unregisterQueue(this);
/*      */     }
/*  368 */     this.mgr = null;
/*  369 */     this.connectionReference = null;
/*      */     
/*  371 */     if (Trace.isOn) {
/*  372 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "close()");
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
/*      */   public void get(MQMessage message) throws MQException {
/*  391 */     if (Trace.isOn) {
/*  392 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "get(MQMessage)", new Object[] { message });
/*      */     }
/*      */     
/*  395 */     get(message, new MQGetMessageOptions(true));
/*  396 */     if (Trace.isOn) {
/*  397 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "get(MQMessage)");
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
/*      */   public void get(MQMessage message, MQGetMessageOptions getMessageOptions) throws MQException {
/*  431 */     if (Trace.isOn) {
/*  432 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "get(MQMessage,MQGetMessageOptions)", new Object[] { message, getMessageOptions });
/*      */     }
/*      */ 
/*      */     
/*  436 */     MQException mqe = null;
/*  437 */     synchronized (this) {
/*  438 */       int DefaultMsgLength = Default_DefaultMsgLength;
/*      */ 
/*      */ 
/*      */       
/*  442 */       if (message == null) {
/*  443 */         MQException traceRet1 = new MQException(2, 2026, this, "MQJI025");
/*  444 */         if (Trace.isOn) {
/*  445 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "get(MQMessage,MQGetMessageOptions)", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/*  448 */         throw traceRet1;
/*      */       } 
/*      */       
/*  451 */       if (getMessageOptions == null) {
/*  452 */         MQException traceRet2 = new MQException(2, 2186, this, "MQJI026");
/*      */ 
/*      */ 
/*      */         
/*  456 */         if (Trace.isOn) {
/*  457 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "get(MQMessage,MQGetMessageOptions)", (Throwable)traceRet2, 2);
/*      */         }
/*      */         
/*  460 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  465 */       MQMD getmd = message;
/*  466 */       Pint msgLength = jmqiEnv.newPint();
/*  467 */       byte[] prevMsgId = null;
/*  468 */       byte[] prevCorrelId = null;
/*  469 */       int prevMatchOptions = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  476 */       prevMatchOptions = getMessageOptions.matchOptions;
/*  477 */       if (prevMatchOptions == 0 || prevMatchOptions == 1) {
/*      */ 
/*      */         
/*  480 */         prevCorrelId = message.correlationId;
/*  481 */         message.correlationId = MQMI_NONE;
/*  482 */         getMessageOptions.matchOptions |= 0x2;
/*      */       } 
/*  484 */       if (prevMatchOptions == 0 || prevMatchOptions == 2) {
/*      */ 
/*      */         
/*  487 */         prevMsgId = message.messageId;
/*  488 */         message.messageId = MQMI_NONE;
/*  489 */         getMessageOptions.matchOptions |= 0x1;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  494 */       int encoding = message.encoding;
/*  495 */       int ccsid = message.characterSet;
/*      */       
/*  497 */       byte[] msgId = new byte[1];
/*  498 */       byte[] correlId = new byte[1];
/*      */       
/*  500 */       if (message.messageId != null) {
/*  501 */         msgId = new byte[message.messageId.length];
/*  502 */         System.arraycopy(message.messageId, 0, msgId, 0, message.messageId.length);
/*      */       } else {
/*  504 */         message.messageId = MQC.MQMI_NONE;
/*  505 */         if (Trace.isOn) {
/*  506 */           Trace.traceData(this, "Saved NullPointerException on message.messageId", null);
/*      */         }
/*      */       } 
/*      */       
/*  510 */       if (message.correlationId != null) {
/*      */         
/*  512 */         correlId = new byte[message.correlationId.length];
/*  513 */         System.arraycopy(message.correlationId, 0, correlId, 0, message.correlationId.length);
/*      */       }
/*  515 */       else if (Trace.isOn) {
/*  516 */         Trace.traceData(this, "Saved NullPointerException on message.correlationId", null);
/*      */       } 
/*      */ 
/*      */       
/*  520 */       if (message.getBufferSizeHint() != -1) {
/*  521 */         DefaultMsgLength = message.getBufferSizeHint();
/*  522 */         this.baseJavaGetBuffer = ByteBuffer.wrap(new byte[DefaultMsgLength]);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  529 */       if ((getMessageOptions.options & 0x1006) == 0)
/*      */       {
/*  531 */         getMessageOptions.options |= 0x4;
/*      */       }
/*      */       
/*  534 */       if (this.osession == null && 
/*  535 */         this.mgr != null) {
/*  536 */         this.osession = this.mgr.getSession();
/*      */       }
/*      */ 
/*      */       
/*  540 */       if (!this.connected || this.osession == null) {
/*  541 */         MQException traceRet3 = new MQException(2, 2018, this, "MQJI002");
/*  542 */         if (Trace.isOn) {
/*  543 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "get(MQMessage,MQGetMessageOptions)", (Throwable)traceRet3, 3);
/*      */         }
/*      */         
/*  546 */         throw traceRet3;
/*      */       } 
/*      */       
/*  549 */       if (!this.resourceOpen) {
/*  550 */         MQException traceRet4 = new MQException(2, 2019, this, "MQJI027");
/*  551 */         if (Trace.isOn) {
/*  552 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "get(MQMessage,MQGetMessageOptions)", (Throwable)traceRet4, 4);
/*      */         }
/*      */         
/*  555 */         throw traceRet4;
/*      */       } 
/*      */       
/*  558 */       if (Trace.isOn) {
/*      */         
/*  560 */         Trace.traceData(this, "get options = " + getMessageOptions.options + "\nwait interval = " + getMessageOptions.waitInterval, null);
/*  561 */         Trace.traceData(this, "message id: ", message.messageId);
/*  562 */         Trace.traceData(this, "corelation id: ", message.correlationId);
/*      */       } 
/*      */       
/*  565 */       this.osession.MQGET(this.hconn, this.phobj.getHobj(), getmd, getMessageOptions, this.baseJavaGetBuffer
/*  566 */           .limit(), this.baseJavaGetBuffer, msgLength, this.completionCode, this.reason);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  575 */       if (this.completionCode.x != 0 && this.reason.x == 2010) {
/*  576 */         if (Trace.isOn) {
/*  577 */           Trace.traceData(this, "Retrying MQGET with buffer size reduced to " + msgLength.x, null);
/*      */         }
/*  579 */         DefaultMsgLength = msgLength.x;
/*  580 */         this.baseJavaGetBuffer = ByteBuffer.wrap(new byte[DefaultMsgLength]);
/*  581 */         this.osession.MQGET(this.hconn, this.phobj.getHobj(), getmd, getMessageOptions, this.baseJavaGetBuffer
/*  582 */             .limit(), this.baseJavaGetBuffer, msgLength, this.completionCode, this.reason);
/*      */       } 
/*      */       
/*  585 */       if (Trace.isOn) {
/*  586 */         Trace.traceData(this, "ProviderMessage is " + msgLength.x + " bytes long.", null);
/*      */       }
/*      */       
/*  589 */       while (this.completionCode.x != 0 && this.reason.x == 2080) {
/*  590 */         if (Trace.isOn) {
/*  591 */           Trace.traceData(this, "Retrying MQGET with increased buffer size.", null);
/*      */         }
/*  593 */         DefaultMsgLength = msgLength.x;
/*  594 */         this.baseJavaGetBuffer = ByteBuffer.wrap(new byte[DefaultMsgLength]);
/*      */ 
/*      */         
/*  597 */         getmd.encoding = encoding;
/*  598 */         getmd.characterSet = ccsid;
/*  599 */         getmd.messageId = new byte[msgId.length];
/*  600 */         System.arraycopy(msgId, 0, getmd.messageId, 0, msgId.length);
/*  601 */         getmd.correlationId = new byte[correlId.length];
/*  602 */         System.arraycopy(correlId, 0, getmd.correlationId, 0, correlId.length);
/*      */         
/*  604 */         this.osession.MQGET(this.hconn, this.phobj.getHobj(), getmd, getMessageOptions, this.baseJavaGetBuffer
/*  605 */             .limit(), this.baseJavaGetBuffer, msgLength, this.completionCode, this.reason);
/*      */       } 
/*      */       
/*  608 */       if (msgLength.x <= 0 && 
/*  609 */         Trace.isOn) {
/*  610 */         Trace.traceData(this, "get (empty message)", null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  619 */       if (prevMsgId != null || prevCorrelId != null) {
/*  620 */         getMessageOptions.matchOptions = prevMatchOptions;
/*      */       }
/*  622 */       if (this.completionCode.x == 2) {
/*  623 */         if (prevMsgId != null) {
/*  624 */           message.messageId = prevMsgId;
/*      */         }
/*  626 */         if (prevCorrelId != null) {
/*  627 */           message.correlationId = prevCorrelId;
/*      */         }
/*      */       } 
/*      */       
/*  631 */       message.setMessageData(this.baseJavaGetBuffer, Math.min(this.baseJavaGetBuffer.limit(), msgLength.x), msgLength.x);
/*      */       
/*  633 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/*  634 */         mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  639 */     if (mqe != null) {
/*  640 */       this.parentQmgr.errorOccurred(mqe);
/*      */       
/*  642 */       if (Trace.isOn) {
/*  643 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "get(MQMessage,MQGetMessageOptions)", (Throwable)mqe, 5);
/*      */       }
/*      */       
/*  646 */       throw mqe;
/*      */     } 
/*      */ 
/*      */     
/*  650 */     if (Trace.isOn) {
/*  651 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "get(MQMessage,MQGetMessageOptions)");
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
/*      */   public void get(MQMessage message, MQGetMessageOptions getMessageOptions, int MaxMsgSize) throws MQException {
/*  699 */     if (Trace.isOn) {
/*  700 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "get(MQMessage,MQGetMessageOptions,int)", new Object[] { message, getMessageOptions, 
/*      */             
/*  702 */             Integer.valueOf(MaxMsgSize) });
/*      */     }
/*      */     
/*  705 */     byte[] prevMsgId = null;
/*  706 */     byte[] prevCorrelId = null;
/*  707 */     int prevMatchOptions = 0;
/*      */     
/*  709 */     MQException mqe = null;
/*      */     
/*  711 */     synchronized (this) {
/*      */ 
/*      */       
/*  714 */       if (message == null) {
/*  715 */         MQException traceRet1 = new MQException(2, 2026, this, "MQJI025");
/*  716 */         if (Trace.isOn) {
/*  717 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "get(MQMessage,MQGetMessageOptions,int)", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/*  720 */         throw traceRet1;
/*      */       } 
/*      */       
/*  723 */       if (MaxMsgSize < 0) {
/*  724 */         MQException traceRet2 = new MQException(2, 2005, this);
/*  725 */         if (Trace.isOn) {
/*  726 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "get(MQMessage,MQGetMessageOptions,int)", (Throwable)traceRet2, 2);
/*      */         }
/*      */         
/*  729 */         throw traceRet2;
/*      */       } 
/*      */       
/*  732 */       if (getMessageOptions == null) {
/*  733 */         MQException traceRet3 = new MQException(2, 2186, this, "MQJI026");
/*  734 */         if (Trace.isOn) {
/*  735 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "get(MQMessage,MQGetMessageOptions,int)", (Throwable)traceRet3, 3);
/*      */         }
/*      */         
/*  738 */         throw traceRet3;
/*      */       } 
/*      */ 
/*      */       
/*  742 */       if ((getMessageOptions.options & 0x1006) == 0)
/*      */       {
/*  744 */         getMessageOptions.options |= 0x4;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  752 */       prevMatchOptions = getMessageOptions.matchOptions;
/*  753 */       if (prevMatchOptions == 0 || prevMatchOptions == 1) {
/*      */ 
/*      */         
/*  756 */         prevCorrelId = message.correlationId;
/*  757 */         message.correlationId = MQMI_NONE;
/*  758 */         getMessageOptions.matchOptions |= 0x2;
/*      */       } 
/*  760 */       if (prevMatchOptions == 0 || prevMatchOptions == 2) {
/*      */ 
/*      */         
/*  763 */         prevMsgId = message.messageId;
/*  764 */         message.messageId = MQMI_NONE;
/*  765 */         getMessageOptions.matchOptions |= 0x1;
/*      */       } 
/*      */ 
/*      */       
/*  769 */       MQMD getmd = message;
/*  770 */       Pint msgLength = jmqiEnv.newPint();
/*  771 */       ByteBuffer buffer = ByteBuffer.wrap(new byte[MaxMsgSize]);
/*      */ 
/*      */       
/*  774 */       if (this.osession == null && 
/*  775 */         this.mgr != null) {
/*  776 */         this.osession = this.mgr.getSession();
/*      */       }
/*      */ 
/*      */       
/*  780 */       if (!this.connected || this.osession == null) {
/*  781 */         MQException traceRet4 = new MQException(2, 2018, this, "MQJI002");
/*  782 */         if (Trace.isOn) {
/*  783 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "get(MQMessage,MQGetMessageOptions,int)", (Throwable)traceRet4, 4);
/*      */         }
/*      */         
/*  786 */         throw traceRet4;
/*      */       } 
/*      */       
/*  789 */       if (!this.resourceOpen) {
/*  790 */         MQException traceRet5 = new MQException(2, 2019, this, "MQJI027");
/*  791 */         if (Trace.isOn) {
/*  792 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "get(MQMessage,MQGetMessageOptions,int)", (Throwable)traceRet5, 5);
/*      */         }
/*      */         
/*  795 */         throw traceRet5;
/*      */       } 
/*      */       
/*  798 */       if (Trace.isOn) {
/*      */         
/*  800 */         Trace.traceData(this, "get options = " + getMessageOptions.options + "\nwait interval = " + getMessageOptions.waitInterval, null);
/*  801 */         Trace.traceData(this, "message id: ", message.messageId);
/*  802 */         Trace.traceData(this, "correlation id: ", message.correlationId);
/*      */       } 
/*      */       
/*  805 */       this.osession.MQGET(this.hconn, this.phobj.getHobj(), getmd, getMessageOptions, buffer
/*  806 */           .limit(), buffer, msgLength, this.completionCode, this.reason);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  815 */       if (this.completionCode.x != 0 && this.reason.x == 2010) {
/*  816 */         if (Trace.isOn) {
/*  817 */           Trace.traceData(this, "Retrying MQGET with buffer size reduced to " + msgLength.x, null);
/*      */         }
/*  819 */         buffer = ByteBuffer.wrap(new byte[msgLength.x]);
/*  820 */         this.osession.MQGET(this.hconn, this.phobj.getHobj(), getmd, getMessageOptions, buffer.limit(), buffer, msgLength, this.completionCode, this.reason);
/*      */       } 
/*      */       
/*  823 */       if (Trace.isOn) {
/*  824 */         Trace.traceData(this, "ProviderMessage is " + msgLength.x + " bytes long.", null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  831 */       if (prevMsgId != null || prevCorrelId != null) {
/*  832 */         getMessageOptions.matchOptions = prevMatchOptions;
/*      */       }
/*  834 */       if (this.completionCode.x == 2) {
/*  835 */         if (prevMsgId != null) {
/*  836 */           message.messageId = prevMsgId;
/*      */         }
/*  838 */         if (prevCorrelId != null) {
/*  839 */           message.correlationId = prevCorrelId;
/*      */         }
/*      */       } 
/*      */       
/*  843 */       message.setMessageData(buffer, Math.min(buffer.limit(), msgLength.x), msgLength.x);
/*      */       
/*  845 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/*  846 */         mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  851 */     if (mqe != null) {
/*  852 */       this.parentQmgr.errorOccurred(mqe);
/*      */       
/*  854 */       if (Trace.isOn) {
/*  855 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "get(MQMessage,MQGetMessageOptions,int)", (Throwable)mqe, 6);
/*      */       }
/*      */       
/*  858 */       throw mqe;
/*      */     } 
/*      */ 
/*      */     
/*  862 */     if (Trace.isOn) {
/*  863 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "get(MQMessage,MQGetMessageOptions,int)");
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
/*      */   public int getCurrentDepth() throws MQException {
/*  879 */     int traceRet1 = getInt(3);
/*  880 */     if (Trace.isOn) {
/*  881 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getCurrentDepth()", "getter", 
/*  882 */           Integer.valueOf(traceRet1));
/*      */     }
/*  884 */     return traceRet1;
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
/*      */   public int getDefinitionType() throws MQException {
/*  901 */     int traceRet1 = getInt(7);
/*  902 */     if (Trace.isOn) {
/*  903 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getDefinitionType()", "getter", 
/*  904 */           Integer.valueOf(traceRet1));
/*      */     }
/*  906 */     return traceRet1;
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
/*      */   final Hobj getHandle() {
/*  918 */     if (Trace.isOn) {
/*  919 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getHandle()");
/*      */     }
/*  921 */     if (!this.resourceOpen) {
/*  922 */       Hobj traceRet1 = CMQC.jmqi_MQHO_UNUSABLE_HOBJ;
/*  923 */       if (Trace.isOn) {
/*  924 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getHandle()", traceRet1, 1);
/*      */       }
/*      */       
/*  927 */       return traceRet1;
/*      */     } 
/*  929 */     Hobj traceRet2 = this.phobj.getHobj();
/*  930 */     if (Trace.isOn) {
/*  931 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getHandle()", traceRet2, 2);
/*      */     }
/*      */     
/*  934 */     return traceRet2;
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
/*      */   public void getMsg2(MQMsg2 message, MQGetMessageOptions getMessageOptions) throws MQException {
/*  952 */     if (Trace.isOn) {
/*  953 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2(MQMsg2,MQGetMessageOptions)", new Object[] { message, getMessageOptions });
/*      */     }
/*      */ 
/*      */     
/*  957 */     MQException mqe = null;
/*      */     
/*  959 */     synchronized (this) {
/*      */       
/*  961 */       getMsg2Int(message, getMessageOptions, this.completionCode, this.reason, 0);
/*      */       
/*  963 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/*  964 */         mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*      */       }
/*      */     } 
/*      */     
/*  968 */     if (mqe != null) {
/*  969 */       this.parentQmgr.errorOccurred(mqe);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  974 */       if (Trace.isOn) {
/*  975 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2(MQMsg2,MQGetMessageOptions)", (Throwable)mqe);
/*      */       }
/*      */       
/*  978 */       throw mqe;
/*      */     } 
/*      */     
/*  981 */     if (Trace.isOn) {
/*  982 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2(MQMsg2,MQGetMessageOptions)");
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
/*      */   public void getMsg2(MQMsg2 message, MQGetMessageOptions getMessageOptions, int MaxMsgSize) throws MQException {
/* 1002 */     if (Trace.isOn) {
/* 1003 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2(MQMsg2,MQGetMessageOptions,int)", new Object[] { message, getMessageOptions, 
/*      */             
/* 1005 */             Integer.valueOf(MaxMsgSize) });
/*      */     }
/*      */     
/* 1008 */     MQException mqe = null;
/*      */     
/* 1010 */     synchronized (this) {
/*      */       
/* 1012 */       getMsg2Int(message, getMessageOptions, MaxMsgSize, this.completionCode, this.reason);
/*      */       
/* 1014 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/* 1015 */         mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*      */       }
/*      */     } 
/*      */     
/* 1019 */     if (mqe != null) {
/* 1020 */       this.parentQmgr.errorOccurred(mqe);
/*      */ 
/*      */ 
/*      */       
/* 1024 */       if (Trace.isOn) {
/* 1025 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2(MQMsg2,MQGetMessageOptions,int)", (Throwable)mqe);
/*      */       }
/*      */       
/* 1028 */       throw mqe;
/*      */     } 
/*      */     
/* 1031 */     if (Trace.isOn) {
/* 1032 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2(MQMsg2,MQGetMessageOptions,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getMsg2Int(MQMsg2 message, MQGetMessageOptions getMessageOptions, int MaxMsgSize, Pint completionCode, Pint reasonCode) {
/* 1042 */     if (Trace.isOn) {
/* 1043 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2Int(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", new Object[] { message, getMessageOptions, 
/*      */             
/* 1045 */             Integer.valueOf(MaxMsgSize), completionCode, reasonCode });
/*      */     }
/*      */     
/* 1048 */     byte[] prevMsgId = null;
/* 1049 */     byte[] prevCorrelId = null;
/* 1050 */     int prevMatchOptions = 0;
/*      */     
/* 1052 */     if (message == null) {
/*      */       
/* 1054 */       completionCode.x = 2;
/* 1055 */       reasonCode.x = 2026;
/* 1056 */       if (Trace.isOn) {
/* 1057 */         Trace.traceData(this, "getMsg2Int (cc=" + completionCode + ", rc=" + reasonCode.x + ")", null);
/*      */       }
/* 1059 */       if (Trace.isOn) {
/* 1060 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2Int(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1066 */     if (MaxMsgSize < 0) {
/* 1067 */       completionCode.x = 2;
/* 1068 */       reasonCode.x = 2005;
/* 1069 */       if (Trace.isOn) {
/* 1070 */         Trace.traceData(this, "getMsg2Int (cc=" + completionCode + ", rc=" + reasonCode.x + ")", null);
/*      */       }
/* 1072 */       if (Trace.isOn) {
/* 1073 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2Int(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1079 */     if (getMessageOptions == null) {
/*      */       
/* 1081 */       completionCode.x = 2;
/* 1082 */       reasonCode.x = 2186;
/* 1083 */       if (Trace.isOn) {
/* 1084 */         Trace.traceData(this, "getMsg2Int (cc=" + completionCode + ", rc=" + reasonCode.x + ")", null);
/*      */       }
/* 1086 */       if (Trace.isOn) {
/* 1087 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2Int(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 3);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1094 */     if ((getMessageOptions.options & 0x1006) == 0) {
/* 1095 */       getMessageOptions.options |= 0x4;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1102 */     prevMatchOptions = getMessageOptions.matchOptions;
/* 1103 */     if (prevMatchOptions == 0 || prevMatchOptions == 1) {
/* 1104 */       prevCorrelId = message.getCorrelationId();
/* 1105 */       message.setCorrelationId(MQMI_NONE);
/* 1106 */       getMessageOptions.matchOptions |= 0x2;
/*      */     } 
/* 1108 */     if (prevMatchOptions == 0 || prevMatchOptions == 2) {
/* 1109 */       prevMsgId = message.getMessageId();
/* 1110 */       message.setMessageId(MQMI_NONE);
/* 1111 */       getMessageOptions.matchOptions |= 0x1;
/*      */     } 
/*      */     
/* 1114 */     Pint msgLength = jmqiEnv.newPint();
/* 1115 */     ByteBuffer buffer = ByteBuffer.wrap(new byte[MaxMsgSize]);
/*      */     
/* 1117 */     if (this.osession == null && this.mgr != null) {
/* 1118 */       this.osession = this.mgr.getSession();
/*      */     }
/*      */     
/* 1121 */     if (!this.connected || this.osession == null) {
/*      */       
/* 1123 */       completionCode.x = 2;
/* 1124 */       reasonCode.x = 2018;
/* 1125 */       if (Trace.isOn) {
/* 1126 */         Trace.traceData(this, "getMsg2Int (cc=" + completionCode + ", rc=" + reasonCode.x + ")", null);
/*      */       }
/* 1128 */       if (Trace.isOn) {
/* 1129 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2Int(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 4);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1135 */     if (!this.resourceOpen) {
/*      */       
/* 1137 */       completionCode.x = 2;
/* 1138 */       reasonCode.x = 2019;
/* 1139 */       if (Trace.isOn) {
/* 1140 */         Trace.traceData(this, "getMsg2Int (cc=" + completionCode + ", rc=" + reasonCode.x + ")", null);
/*      */       }
/* 1142 */       if (Trace.isOn) {
/* 1143 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2Int(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 5);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1149 */     if (Trace.isOn) {
/* 1150 */       Trace.traceData(this, "get options = " + getMessageOptions.options + "\nwait interval = " + getMessageOptions.waitInterval, null);
/* 1151 */       Trace.traceData(this, "message id: ", message.getMessageId());
/* 1152 */       Trace.traceData(this, "correlation id: ", message.getCorrelationId());
/*      */     } 
/*      */     
/* 1155 */     this.osession.MQGET(this.hconn, this.phobj.getHobj(), message, getMessageOptions, buffer.limit(), buffer, msgLength, completionCode, reasonCode);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1163 */     if (completionCode.x != 0 && this.reason.x == 2010) {
/* 1164 */       if (Trace.isOn) {
/* 1165 */         Trace.traceData(this, "Retrying MQGET with buffer size reduced to " + msgLength.x, null);
/*      */       }
/* 1167 */       buffer = ByteBuffer.wrap(new byte[msgLength.x]);
/* 1168 */       this.osession.MQGET(this.hconn, this.phobj.getHobj(), message, getMessageOptions, buffer.limit(), buffer, msgLength, completionCode, reasonCode);
/*      */     } 
/*      */     
/* 1171 */     if (Trace.isOn) {
/* 1172 */       Trace.traceData(this, "ProviderMessage is " + msgLength.x + " bytes long.", null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1179 */     if (prevMsgId != null || prevCorrelId != null) {
/* 1180 */       getMessageOptions.matchOptions = prevMatchOptions;
/*      */     }
/*      */     
/* 1183 */     if (completionCode.x == 2) {
/*      */       
/* 1185 */       if (prevMsgId != null) {
/* 1186 */         message.setMessageId(prevMsgId);
/*      */       }
/* 1188 */       if (prevCorrelId != null) {
/* 1189 */         message.setCorrelationId(prevCorrelId);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1194 */     message.setMessageData(buffer, Math.min(buffer.limit(), msgLength.x));
/*      */     
/* 1196 */     if (Trace.isOn) {
/* 1197 */       Trace.traceData(this, "getMsg2Int completed with cc=" + completionCode.x + ", rc=" + reasonCode.x + ")", null);
/*      */     }
/*      */     
/* 1200 */     if (Trace.isOn) {
/* 1201 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2Int(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 6);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private synchronized void getMsg2Int(MQMsg2 message, MQGetMessageOptions getMessageOptions, Pint completionCode, Pint reasonCode, int expectedMsgLength) {
/* 1210 */     if (Trace.isOn) {
/* 1211 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2Int(MQMsg2,MQGetMessageOptions,Pint,Pint,int)", new Object[] { message, getMessageOptions, completionCode, reasonCode, 
/*      */             
/* 1213 */             Integer.valueOf(expectedMsgLength) });
/*      */     }
/*      */     
/* 1216 */     if (expectedMsgLength > 0) {
/* 1217 */       this.DefaultMsgLength = expectedMsgLength;
/*      */     }
/*      */     
/* 1220 */     if (message == null) {
/*      */       
/* 1222 */       completionCode.x = 2;
/* 1223 */       reasonCode.x = 2026;
/* 1224 */       if (Trace.isOn) {
/* 1225 */         Trace.traceData(this, "getMsg2Int (cc=" + completionCode.x + ", rc=" + reasonCode.x, null);
/*      */       }
/* 1227 */       if (Trace.isOn) {
/* 1228 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2Int(MQMsg2,MQGetMessageOptions,Pint,Pint,int)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1234 */     if (getMessageOptions == null) {
/*      */       
/* 1236 */       completionCode.x = 2;
/* 1237 */       reasonCode.x = 2186;
/* 1238 */       if (Trace.isOn) {
/* 1239 */         Trace.traceData(this, "getMsg2Int (cc=" + completionCode.x + ", rc=" + reasonCode.x, null);
/*      */       }
/* 1241 */       if (Trace.isOn) {
/* 1242 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2Int(MQMsg2,MQGetMessageOptions,Pint,Pint,int)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1248 */     Pint msgLength = jmqiEnv.newPint();
/* 1249 */     byte[] prevMsgId = null;
/* 1250 */     byte[] prevCorrelId = null;
/* 1251 */     int prevMatchOptions = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1257 */     prevMatchOptions = getMessageOptions.matchOptions;
/* 1258 */     if (prevMatchOptions == 0 || prevMatchOptions == 1) {
/* 1259 */       prevCorrelId = message.getCorrelationId();
/* 1260 */       message.setCorrelationId(MQMI_NONE);
/* 1261 */       getMessageOptions.matchOptions |= 0x2;
/*      */     } 
/* 1263 */     if (prevMatchOptions == 0 || prevMatchOptions == 2) {
/* 1264 */       prevMsgId = message.getMessageId();
/* 1265 */       message.setMessageId(MQMI_NONE);
/* 1266 */       getMessageOptions.matchOptions |= 0x1;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1271 */     int encoding = message.getEncoding();
/* 1272 */     int ccsid = message.getCharacterSet();
/* 1273 */     byte[] msgId = message.getMessageId();
/* 1274 */     byte[] correlId = message.getCorrelationId();
/*      */     
/* 1276 */     ByteBuffer buffer = ByteBuffer.wrap(new byte[this.DefaultMsgLength]);
/*      */ 
/*      */     
/* 1279 */     if ((getMessageOptions.options & 0x1006) == 0) {
/* 1280 */       getMessageOptions.options |= 0x4;
/*      */     }
/*      */     
/* 1283 */     if (this.osession == null && this.mgr != null) {
/* 1284 */       this.osession = this.mgr.getSession();
/*      */     }
/*      */     
/* 1287 */     if (!this.connected || this.osession == null) {
/*      */       
/* 1289 */       completionCode.x = 2;
/* 1290 */       reasonCode.x = 2018;
/* 1291 */       if (Trace.isOn) {
/* 1292 */         Trace.traceData(this, "getMsg2Int (cc=" + completionCode.x + ", rc=" + reasonCode.x, null);
/*      */       }
/* 1294 */       if (Trace.isOn) {
/* 1295 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2Int(MQMsg2,MQGetMessageOptions,Pint,Pint,int)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1301 */     if (!this.resourceOpen) {
/*      */       
/* 1303 */       completionCode.x = 2;
/* 1304 */       reasonCode.x = 2019;
/* 1305 */       if (Trace.isOn) {
/* 1306 */         Trace.traceData(this, "getMsg2Int (cc=" + completionCode.x + ", rc=" + reasonCode.x, null);
/*      */       }
/* 1308 */       if (Trace.isOn) {
/* 1309 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2Int(MQMsg2,MQGetMessageOptions,Pint,Pint,int)", 4);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1315 */     if (Trace.isOn) {
/* 1316 */       Trace.traceData(this, "get options = " + getMessageOptions.options + "\nwait interval = " + getMessageOptions.waitInterval, null);
/* 1317 */       Trace.traceData(this, "message id: ", message.getMessageId());
/* 1318 */       Trace.traceData(this, "correlation id: ", message.getCorrelationId());
/*      */     } 
/*      */     
/* 1321 */     this.osession.MQGET(this.hconn, this.phobj.getHobj(), message, getMessageOptions, buffer.limit(), buffer, msgLength, completionCode, reasonCode);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1330 */     if (completionCode.x != 0 && this.reason.x == 2010) {
/* 1331 */       if (Trace.isOn) {
/* 1332 */         Trace.traceData(this, "Retrying MQGET with buffer size reduced to " + msgLength.x, null);
/*      */       }
/* 1334 */       this.DefaultMsgLength = msgLength.x;
/* 1335 */       buffer = ByteBuffer.wrap(new byte[this.DefaultMsgLength]);
/* 1336 */       this.osession.MQGET(this.hconn, this.phobj.getHobj(), message, getMessageOptions, buffer
/* 1337 */           .limit(), buffer, msgLength, completionCode, reasonCode);
/*      */     } 
/*      */     
/* 1340 */     if (Trace.isOn) {
/* 1341 */       Trace.traceData(this, "ProviderMessage is " + msgLength.x + " bytes long.", null);
/*      */     }
/*      */     
/* 1344 */     while (completionCode.x != 0 && reasonCode.x == 2080) {
/* 1345 */       Trace.traceData(this, "Retrying MQGET with increased buffer size.", null);
/* 1346 */       this.DefaultMsgLength = msgLength.x;
/* 1347 */       buffer = ByteBuffer.wrap(new byte[this.DefaultMsgLength]);
/*      */ 
/*      */       
/* 1350 */       message.setEncoding(encoding);
/* 1351 */       message.setCharacterSet(ccsid);
/* 1352 */       message.setMessageId(msgId);
/* 1353 */       message.setCorrelationId(correlId);
/*      */       
/* 1355 */       this.osession.MQGET(this.hconn, this.phobj.getHobj(), message, getMessageOptions, buffer.limit(), buffer, msgLength, completionCode, reasonCode);
/*      */     } 
/*      */     
/* 1358 */     if (msgLength.x <= 0 && 
/* 1359 */       Trace.isOn) {
/* 1360 */       Trace.traceData(this, "getMsg2Int (empty message)", null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1367 */     if (prevMsgId != null || prevCorrelId != null) {
/* 1368 */       getMessageOptions.matchOptions = prevMatchOptions;
/*      */     }
/*      */     
/* 1371 */     if (completionCode.x == 2) {
/*      */       
/* 1373 */       if (prevMsgId != null) {
/* 1374 */         message.setMessageId(prevMsgId);
/*      */       }
/* 1376 */       if (prevCorrelId != null) {
/* 1377 */         message.setCorrelationId(prevCorrelId);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1386 */     if (completionCode.x != 2 || reasonCode.x == 2033) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1391 */       if (this.DefaultMsgLength > msgLength.x * 2) {
/* 1392 */         this.msgsTooSmallForBuffer++;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1397 */         this.msgsTooSmallForBuffer = 0;
/*      */       } 
/* 1399 */       if (this.msgsTooSmallForBuffer >= smallMsgsBufferReductionThreshold) {
/*      */         
/* 1401 */         if (Trace.isOn) {
/* 1402 */           Trace.traceData(this, "Reducing buffer size. msgsTooSmallForBuffer = " + this.msgsTooSmallForBuffer, null);
/*      */         }
/* 1404 */         this.DefaultMsgLength = Default_DefaultMsgLength;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1409 */         this.msgsTooSmallForBuffer = 0;
/*      */       } 
/*      */     } 
/*      */     
/* 1413 */     message.setMessageData(buffer, Math.min(buffer.limit(), msgLength.x));
/*      */     
/* 1415 */     if (Trace.isOn) {
/* 1416 */       Trace.traceData(this, "getMsg2Int completed with cc=" + completionCode.x + ", rc=" + reasonCode.x + ")", null);
/*      */     }
/*      */     
/* 1419 */     if (Trace.isOn) {
/* 1420 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2Int(MQMsg2,MQGetMessageOptions,Pint,Pint,int)", 5);
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
/*      */   public synchronized int getMsg2NoExc(MQMsg2 message, MQGetMessageOptions getMessageOptions) {
/* 1440 */     if (Trace.isOn) {
/* 1441 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2NoExc(MQMsg2,MQGetMessageOptions)", new Object[] { message, getMessageOptions });
/*      */     }
/*      */ 
/*      */     
/* 1445 */     getMsg2Int(message, getMessageOptions, this.completionCode, this.reason, 0);
/*      */     
/* 1447 */     if (Trace.isOn) {
/* 1448 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2NoExc(MQMsg2,MQGetMessageOptions)", 
/* 1449 */           Integer.valueOf(this.reason.x));
/*      */     }
/* 1451 */     return this.reason.x;
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
/*      */   public synchronized int getMsg2NoExc(MQMsg2 message, MQGetMessageOptions getMessageOptions, int MaxMsgSize) {
/* 1468 */     if (Trace.isOn) {
/* 1469 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2NoExc(MQMsg2,MQGetMessageOptions,int)", new Object[] { message, getMessageOptions, 
/*      */             
/* 1471 */             Integer.valueOf(MaxMsgSize) });
/*      */     }
/*      */ 
/*      */     
/* 1475 */     getMsg2Int(message, getMessageOptions, MaxMsgSize, this.completionCode, this.reason);
/*      */     
/* 1477 */     if (Trace.isOn) {
/* 1478 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "getMsg2NoExc(MQMsg2,MQGetMessageOptions,int)", 
/* 1479 */           Integer.valueOf(this.reason.x));
/*      */     }
/* 1481 */     return this.reason.x;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isNPMClassHigh() {
/* 1492 */     if (Trace.isOn) {
/* 1493 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "isNPMClassHigh()");
/*      */     }
/*      */ 
/*      */     
/* 1497 */     MQQueue myQueue = null;
/* 1498 */     boolean opened = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*      */       try {
/* 1505 */         if (isOpen() && (this.openOptions & 0x20) == 32) {
/* 1506 */           if (Trace.isOn) {
/* 1507 */             Trace.traceData(this, "Not reopening queue as already opened for inquire", null);
/*      */           }
/* 1509 */           opened = false;
/* 1510 */           myQueue = this;
/*      */         } else {
/* 1512 */           myQueue = this.mgr.accessQueue(this.name, 8224);
/* 1513 */           opened = true;
/*      */         }
/*      */       
/* 1516 */       } catch (Exception e) {
/* 1517 */         if (Trace.isOn) {
/* 1518 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "isNPMClassHigh()", e, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1523 */         myQueue = null;
/*      */       } 
/*      */ 
/*      */       
/* 1527 */       boolean res = (myQueue != null && myQueue.getInt(78) == 10);
/*      */       
/* 1529 */       if (Trace.isOn) {
/* 1530 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "isNPMClassHigh()", 
/* 1531 */             Boolean.valueOf(res), 1);
/*      */       }
/* 1533 */       return res;
/*      */     }
/* 1535 */     catch (MQException mq) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     finally {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1644 */       if (Trace.isOn) {
/* 1645 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "isNPMClassHigh()");
/*      */       }
/*      */       
/*      */       try {
/* 1649 */         if (myQueue != null && opened) {
/* 1650 */           myQueue.close();
/*      */         }
/*      */       }
/* 1653 */       catch (MQException mqe) {
/* 1654 */         if (Trace.isOn) {
/* 1655 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "isNPMClassHigh()", (Throwable)mqe, 4);
/*      */         }
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
/*      */ 
/*      */   
/*      */   public void put(MQMessage message) throws MQException {
/* 1672 */     if (Trace.isOn) {
/* 1673 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "put(MQMessage)", new Object[] { message });
/*      */     }
/*      */     
/* 1676 */     put(message, new MQPutMessageOptions());
/* 1677 */     if (Trace.isOn) {
/* 1678 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "put(MQMessage)");
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
/*      */   public void put(MQMessage message, MQPutMessageOptions putMessageOptions) throws MQException {
/* 1722 */     if (Trace.isOn) {
/* 1723 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "put(MQMessage,MQPutMessageOptions)", new Object[] { message, putMessageOptions });
/*      */     }
/*      */ 
/*      */     
/* 1727 */     MQException mqe = null;
/*      */     
/* 1729 */     synchronized (this) {
/*      */ 
/*      */       
/* 1732 */       MQMD putmd = message;
/*      */ 
/*      */       
/* 1735 */       if (message == null) {
/* 1736 */         MQException traceRet1 = new MQException(2, 2026, this, "MQJI028");
/* 1737 */         if (Trace.isOn) {
/* 1738 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "put(MQMessage,MQPutMessageOptions)", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/* 1741 */         throw traceRet1;
/*      */       } 
/*      */       
/* 1744 */       if (putMessageOptions == null) {
/* 1745 */         MQException traceRet2 = new MQException(2, 2173, this, "MQJI029");
/* 1746 */         if (Trace.isOn) {
/* 1747 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "put(MQMessage,MQPutMessageOptions)", (Throwable)traceRet2, 2);
/*      */         }
/*      */         
/* 1750 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */       
/* 1754 */       putMessageOptions.invalidDestCount = 1;
/* 1755 */       putMessageOptions.knownDestCount = 0;
/* 1756 */       putMessageOptions.unknownDestCount = 0;
/*      */ 
/*      */       
/* 1759 */       if ((putMessageOptions.options & 0x6) == 0) {
/* 1760 */         putMessageOptions.options |= 0x4;
/*      */       }
/*      */       
/* 1763 */       if (this.osession == null && 
/* 1764 */         this.mgr != null) {
/* 1765 */         this.osession = this.mgr.getSession();
/*      */       }
/*      */ 
/*      */       
/* 1769 */       if (!this.connected || this.osession == null) {
/* 1770 */         MQException traceRet3 = new MQException(2, 2018, this, "MQJI002");
/* 1771 */         if (Trace.isOn) {
/* 1772 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "put(MQMessage,MQPutMessageOptions)", (Throwable)traceRet3, 3);
/*      */         }
/*      */         
/* 1775 */         throw traceRet3;
/*      */       } 
/*      */       
/* 1778 */       if (!this.resourceOpen) {
/* 1779 */         MQException traceRet4 = new MQException(2, 2019, this, "MQJI027");
/* 1780 */         if (Trace.isOn) {
/* 1781 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "put(MQMessage,MQPutMessageOptions)", (Throwable)traceRet4, 4);
/*      */         }
/*      */         
/* 1784 */         throw traceRet4;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1789 */       if ((putMessageOptions.options & 0x300) != 0)
/*      */       {
/*      */         
/* 1792 */         if (putMessageOptions.contextReference != null) {
/* 1793 */           putMessageOptions
/* 1794 */             .contextReferenceHandle = putMessageOptions.contextReference.getHandle();
/* 1795 */           if (putMessageOptions.contextReferenceHandle == CMQC.jmqi_MQHO_UNUSABLE_HOBJ) {
/* 1796 */             MQException traceRet5 = new MQException(2, 2097, this);
/*      */ 
/*      */             
/* 1799 */             if (Trace.isOn) {
/* 1800 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "put(MQMessage,MQPutMessageOptions)", (Throwable)traceRet5, 5);
/*      */             }
/*      */             
/* 1803 */             throw traceRet5;
/*      */           } 
/*      */           
/* 1806 */           if (this.mgr != putMessageOptions.contextReference.mgr) {
/* 1807 */             if (Trace.isOn) {
/* 1808 */               Trace.traceData(this, "ProviderConnection references do not match", null);
/*      */             }
/*      */             
/* 1811 */             MQException traceRet6 = new MQException(2, 2097, this);
/*      */ 
/*      */             
/* 1814 */             if (Trace.isOn) {
/* 1815 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "put(MQMessage,MQPutMessageOptions)", (Throwable)traceRet6, 6);
/*      */             }
/*      */             
/* 1818 */             throw traceRet6;
/*      */           } 
/*      */           
/* 1821 */           if (Trace.isOn) {
/* 1822 */             Trace.traceData(this, "Obtained context reference handle:" + putMessageOptions.contextReferenceHandle, null);
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/* 1827 */           if (Trace.isOn) {
/* 1828 */             Trace.traceData(this, "Context reference queue is null", null);
/*      */           }
/*      */           
/* 1831 */           MQException traceRet7 = new MQException(2, 2097, this);
/*      */ 
/*      */           
/* 1834 */           if (Trace.isOn) {
/* 1835 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "put(MQMessage,MQPutMessageOptions)", (Throwable)traceRet7, 7);
/*      */           }
/*      */           
/* 1838 */           throw traceRet7;
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1846 */       if ((putMessageOptions.options & 0x800) != 0) {
/* 1847 */         if (putmd.putDateTime == null) {
/*      */           
/* 1849 */           putmd.putDate = MQMD.getDate(null);
/* 1850 */           putmd.putTime = MQMD.getTime(null);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 1856 */           if (this.putCalendar == null) {
/*      */             
/* 1858 */             TimeZone gmt = TimeZone.getTimeZone("GMT");
/* 1859 */             this.putCalendar = new GregorianCalendar(gmt);
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1864 */           this.putCalendar.setTime(putmd.putDateTime.getTime());
/* 1865 */           putmd.putDate = MQMD.getDate(this.putCalendar);
/* 1866 */           putmd.putTime = MQMD.getTime(this.putCalendar);
/*      */         } 
/*      */       }
/*      */       
/* 1870 */       ByteBuffer msgData = ByteBuffer.wrap(message.toByteArray());
/*      */       
/* 1872 */       if (Trace.isOn) {
/*      */         
/* 1874 */         Trace.traceData(this, "ProviderMessage length = " + msgData.limit() + " bytes.", null);
/*      */         
/* 1876 */         Trace.traceData(this, "put options = " + putMessageOptions.options + "\nmessage type = " + message.messageType + "\nencoding = " + message.encoding + "\ncharacter set = " + message.characterSet + "\nformat = " + message.format, null);
/*      */         
/* 1878 */         Trace.traceData(this, "message id: ", message.messageId);
/* 1879 */         Trace.traceData(this, "correlation id: ", message.correlationId);
/*      */       } 
/*      */       
/* 1882 */       this.osession.MQPUT(this.hconn, this.phobj.getHobj(), putmd, putMessageOptions, msgData
/* 1883 */           .limit(), msgData, this.completionCode, this.reason);
/*      */       
/* 1885 */       if (Trace.isOn)
/*      */       {
/* 1887 */         Trace.traceData(this, "Returned message id follows:", message.messageId);
/*      */       }
/* 1889 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/* 1890 */         mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1895 */     if (mqe != null) {
/* 1896 */       this.parentQmgr.errorOccurred(mqe);
/*      */       
/* 1898 */       if (Trace.isOn) {
/* 1899 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "put(MQMessage,MQPutMessageOptions)", (Throwable)mqe, 8);
/*      */       }
/*      */       
/* 1902 */       throw mqe;
/*      */     } 
/*      */ 
/*      */     
/* 1906 */     if (Trace.isOn) {
/* 1907 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "put(MQMessage,MQPutMessageOptions)");
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
/*      */   public void putMsg2(MQMsg2 message, MQPutMessageOptions putMessageOptions) throws MQException {
/* 1934 */     if (Trace.isOn) {
/* 1935 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", new Object[] { message, putMessageOptions });
/*      */     }
/*      */ 
/*      */     
/* 1939 */     MQException mqe = null;
/*      */     
/* 1941 */     synchronized (this) {
/* 1942 */       if (message == null) {
/*      */         
/* 1944 */         MQException traceRet1 = new MQException(2, 2026, this, "MQJI028");
/* 1945 */         if (Trace.isOn) {
/* 1946 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/* 1949 */         throw traceRet1;
/*      */       } 
/*      */       
/* 1952 */       if (putMessageOptions == null) {
/*      */         
/* 1954 */         MQException traceRet2 = new MQException(2, 2173, this, "MQJI029");
/* 1955 */         if (Trace.isOn) {
/* 1956 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", (Throwable)traceRet2, 2);
/*      */         }
/*      */         
/* 1959 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */       
/* 1963 */       putMessageOptions.invalidDestCount = 1;
/* 1964 */       putMessageOptions.knownDestCount = 0;
/* 1965 */       putMessageOptions.unknownDestCount = 0;
/*      */ 
/*      */       
/* 1968 */       if ((putMessageOptions.options & 0x6) == 0) {
/* 1969 */         putMessageOptions.options |= 0x4;
/*      */       }
/*      */       
/* 1972 */       if (this.osession == null && this.mgr != null) {
/* 1973 */         this.osession = this.mgr.getSession();
/*      */       }
/*      */       
/* 1976 */       if (!this.connected || this.osession == null) {
/* 1977 */         MQException traceRet3 = new MQException(2, 2018, this, "MQJI002");
/* 1978 */         if (Trace.isOn) {
/* 1979 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", (Throwable)traceRet3, 3);
/*      */         }
/*      */         
/* 1982 */         throw traceRet3;
/*      */       } 
/*      */       
/* 1985 */       if (!this.resourceOpen) {
/* 1986 */         MQException traceRet4 = new MQException(2, 2019, this, "MQJI027");
/* 1987 */         if (Trace.isOn) {
/* 1988 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", (Throwable)traceRet4, 4);
/*      */         }
/*      */         
/* 1991 */         throw traceRet4;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1996 */       if ((putMessageOptions.options & 0x300) != 0) {
/* 1997 */         if (putMessageOptions.contextReference != null) {
/* 1998 */           putMessageOptions.contextReferenceHandle = putMessageOptions.contextReference.getHandle();
/* 1999 */           if (putMessageOptions.contextReferenceHandle == CMQC.jmqi_MQHO_UNUSABLE_HOBJ) {
/* 2000 */             MQException traceRet5 = new MQException(2, 2097, this);
/* 2001 */             if (Trace.isOn) {
/* 2002 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", (Throwable)traceRet5, 5);
/*      */             }
/*      */             
/* 2005 */             throw traceRet5;
/*      */           } 
/* 2007 */           if (this.mgr != putMessageOptions.contextReference.mgr) {
/* 2008 */             if (Trace.isOn) {
/* 2009 */               Trace.traceData(this, "ProviderConnection references do not match", null);
/*      */             }
/*      */             
/* 2012 */             MQException traceRet6 = new MQException(2, 2097, this);
/* 2013 */             if (Trace.isOn) {
/* 2014 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", (Throwable)traceRet6, 6);
/*      */             }
/*      */             
/* 2017 */             throw traceRet6;
/*      */           } 
/* 2019 */           if (Trace.isOn) {
/* 2020 */             Trace.traceData(this, "Obtained context reference handle:" + putMessageOptions.contextReferenceHandle, null);
/*      */           }
/*      */         } else {
/* 2023 */           if (Trace.isOn) {
/* 2024 */             Trace.traceData(this, "Context reference queue is null", null);
/*      */           }
/*      */           
/* 2027 */           MQException traceRet7 = new MQException(2, 2097, this);
/* 2028 */           if (Trace.isOn) {
/* 2029 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", (Throwable)traceRet7, 7);
/*      */           }
/*      */           
/* 2032 */           throw traceRet7;
/*      */         } 
/*      */       }
/*      */       
/* 2036 */       ByteBuffer msgData = message.getInternalBuffer();
/* 2037 */       int messageDataLength = message.getMessageDataLength();
/*      */       
/* 2039 */       if (Trace.isOn) {
/* 2040 */         Trace.traceData(this, "ProviderMessage length = " + messageDataLength + " bytes.", null);
/*      */         
/* 2042 */         Trace.traceData(this, "put options = " + putMessageOptions.options + "\nmessage type = " + message.getMessageType() + "\nencoding = " + message.getEncoding() + "\ncharacter set = " + message
/* 2043 */             .getCharacterSet() + "\nformat = " + message.getFormat(), null);
/* 2044 */         Trace.traceData(this, "message id: ", message.getMessageId());
/* 2045 */         Trace.traceData(this, "correlation id: ", message.getCorrelationId());
/*      */       } 
/*      */       
/* 2048 */       this.osession.MQPUT(this.hconn, this.phobj.getHobj(), message, putMessageOptions, messageDataLength, msgData, this.completionCode, this.reason);
/*      */       
/* 2050 */       if (Trace.isOn) {
/* 2051 */         Trace.traceData(this, "Returned message id follows:", message.getMessageId());
/*      */       }
/* 2053 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/* 2054 */         mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*      */       }
/*      */     } 
/*      */     
/* 2058 */     if (mqe != null) {
/* 2059 */       this.parentQmgr.errorOccurred(mqe);
/* 2060 */       if (Trace.isOn) {
/* 2061 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", (Throwable)mqe, 8);
/*      */       }
/*      */       
/* 2064 */       throw mqe;
/*      */     } 
/*      */     
/* 2067 */     if (Trace.isOn) {
/* 2068 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)");
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
/*      */   public void spiBatchedGet(MQMsg2 message, MQGetMessageOptions getMessageOptions) throws MQException {
/* 2099 */     if (Trace.isOn) {
/* 2100 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGet(MQMsg2,MQGetMessageOptions)", new Object[] { message, getMessageOptions });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2108 */     MQException exception = null;
/*      */     
/* 2110 */     synchronized (this) {
/*      */       
/*      */       try {
/* 2113 */         spiBatchedGetInt(message, getMessageOptions, this.completionCode, this.reason);
/*      */       }
/* 2115 */       catch (MQException mqe) {
/* 2116 */         if (Trace.isOn) {
/* 2117 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGet(MQMsg2,MQGetMessageOptions)", (Throwable)mqe);
/*      */         }
/*      */         
/* 2120 */         if (Trace.isOn) {
/* 2121 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGet(MQMsg2,MQGetMessageOptions)", (Throwable)mqe, 1);
/*      */         }
/*      */         
/* 2124 */         throw mqe;
/*      */       } 
/*      */       
/* 2127 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/* 2128 */         exception = new MQException(this.completionCode.x, this.reason.x, this);
/*      */       }
/*      */     } 
/*      */     
/* 2132 */     if (exception != null) {
/* 2133 */       this.parentQmgr.errorOccurred(exception);
/* 2134 */       if (Trace.isOn) {
/* 2135 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGet(MQMsg2,MQGetMessageOptions)", (Throwable)exception, 2);
/*      */       }
/*      */       
/* 2138 */       throw exception;
/*      */     } 
/* 2140 */     if (Trace.isOn) {
/* 2141 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGet(MQMsg2,MQGetMessageOptions)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void spiBatchedGetInt(MQMsg2 message, MQGetMessageOptions getMessageOptions, int MaxMsgSize, Pint cc, Pint rc) throws MQException {
/* 2152 */     if (Trace.isOn) {
/* 2153 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetInt(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", new Object[] { message, getMessageOptions, 
/*      */             
/* 2155 */             Integer.valueOf(MaxMsgSize), cc, rc });
/*      */     }
/*      */     
/* 2158 */     if (message == null) {
/* 2159 */       if (Trace.isOn) {
/* 2160 */         Trace.traceData(this, "message is null", null);
/*      */       }
/*      */       
/* 2163 */       cc.x = 2;
/* 2164 */       rc.x = 2026;
/* 2165 */       if (Trace.isOn) {
/* 2166 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetInt(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 2172 */     if (MaxMsgSize < 0) {
/* 2173 */       if (Trace.isOn) {
/* 2174 */         Trace.traceData(this, "MaxMsgSize < 0 - Buffer length error", null);
/*      */       }
/* 2176 */       cc.x = 2;
/* 2177 */       rc.x = 2005;
/* 2178 */       if (Trace.isOn) {
/* 2179 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetInt(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 2185 */     if (getMessageOptions == null) {
/* 2186 */       if (Trace.isOn) {
/* 2187 */         Trace.traceData(this, "getMessageOptions is null", null);
/*      */       }
/*      */       
/* 2190 */       cc.x = 2;
/* 2191 */       rc.x = 2186;
/* 2192 */       if (Trace.isOn) {
/* 2193 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetInt(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 2199 */     byte[] prevMsgId = null;
/* 2200 */     byte[] prevCorrelId = null;
/* 2201 */     int prevMatchOptions = 0;
/*      */ 
/*      */     
/* 2204 */     if ((getMessageOptions.options & 0x1006) == 0) {
/* 2205 */       getMessageOptions.options |= 0x4;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2212 */     prevMatchOptions = getMessageOptions.matchOptions;
/* 2213 */     if (prevMatchOptions == 0 || prevMatchOptions == 1) {
/* 2214 */       prevCorrelId = message.getCorrelationId();
/* 2215 */       message.setCorrelationId(MQMI_NONE);
/* 2216 */       getMessageOptions.matchOptions |= 0x2;
/*      */     } 
/* 2218 */     if (prevMatchOptions == 0 || prevMatchOptions == 2) {
/* 2219 */       prevMsgId = message.getMessageId();
/* 2220 */       message.setMessageId(MQMI_NONE);
/* 2221 */       getMessageOptions.matchOptions |= 0x1;
/*      */     } 
/*      */     
/* 2224 */     Pint msgLength = jmqiEnv.newPint();
/* 2225 */     byte[] array = new byte[MaxMsgSize];
/* 2226 */     ByteBuffer buffer = ByteBuffer.wrap(array);
/*      */     
/* 2228 */     if (this.osession == null && this.mgr != null) {
/* 2229 */       this.osession = this.mgr.getSession();
/*      */     }
/*      */     
/* 2232 */     if (!this.connected || this.osession == null) {
/* 2233 */       if (Trace.isOn) {
/* 2234 */         Trace.traceData(this, "not connected or osession is null", null);
/*      */       }
/*      */       
/* 2237 */       cc.x = 2;
/* 2238 */       rc.x = 2018;
/* 2239 */       if (Trace.isOn) {
/* 2240 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetInt(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 4);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 2246 */     if (!this.resourceOpen) {
/* 2247 */       if (Trace.isOn) {
/* 2248 */         Trace.traceData(this, "resource not open", null);
/*      */       }
/*      */       
/* 2251 */       cc.x = 2;
/* 2252 */       rc.x = 2019;
/* 2253 */       if (Trace.isOn) {
/* 2254 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetInt(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 5);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 2260 */     if (Trace.isOn) {
/* 2261 */       Trace.traceData(this, "get options = " + getMessageOptions.options + "\nwait interval = " + getMessageOptions.waitInterval, null);
/* 2262 */       Trace.traceData(this, "message id:", message.getMessageId());
/* 2263 */       Trace.traceData(this, "correlation id: ", message.getCorrelationId());
/*      */     } 
/*      */     
/* 2266 */     this.osession.spiBatchedGet(this.hconn, this.phobj.getHobj(), message, getMessageOptions, buffer.limit(), buffer, msgLength, cc, rc);
/*      */     
/* 2268 */     if (Trace.isOn) {
/* 2269 */       Trace.traceData(this, "ProviderMessage is " + msgLength.x + " bytes long.", null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2275 */     if (prevMsgId != null || prevCorrelId != null) {
/* 2276 */       getMessageOptions.matchOptions = prevMatchOptions;
/*      */     }
/*      */     
/* 2279 */     if (cc.x == 2) {
/*      */       
/* 2281 */       if (prevMsgId != null) {
/* 2282 */         message.setMessageId(prevMsgId);
/*      */       }
/* 2284 */       if (prevCorrelId != null) {
/* 2285 */         message.setCorrelationId(prevCorrelId);
/*      */       }
/*      */     } 
/*      */     
/* 2289 */     message.setMessageData(buffer, Math.min(buffer.limit(), msgLength.x));
/*      */     
/* 2291 */     if (Trace.isOn) {
/* 2292 */       Trace.traceData(this, "spiBatchedGetMsg2Int completed with cc=" + cc.x + ", rc=" + rc.x + ")", null);
/*      */     }
/*      */     
/* 2295 */     if (Trace.isOn) {
/* 2296 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetInt(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 6);
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
/*      */   private void spiBatchedGetInt(MQMsg2 message, MQGetMessageOptions getMessageOptions, Pint cc, Pint rc) throws MQException {
/* 2308 */     if (Trace.isOn) {
/* 2309 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetInt(MQMsg2,MQGetMessageOptions,Pint,Pint)", new Object[] { message, getMessageOptions, cc, rc });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2314 */     if (message == null) {
/* 2315 */       if (Trace.isOn) {
/* 2316 */         Trace.traceData(this, "message is null", null);
/*      */       }
/*      */       
/* 2319 */       cc.x = 2;
/* 2320 */       rc.x = 2026;
/* 2321 */       if (Trace.isOn) {
/* 2322 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetInt(MQMsg2,MQGetMessageOptions,Pint,Pint)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 2328 */     if (getMessageOptions == null) {
/* 2329 */       if (Trace.isOn) {
/* 2330 */         Trace.traceData(this, "getMessageOptions is null", null);
/*      */       }
/*      */       
/* 2333 */       cc.x = 2;
/* 2334 */       rc.x = 2186;
/* 2335 */       if (Trace.isOn) {
/* 2336 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetInt(MQMsg2,MQGetMessageOptions,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 2342 */     byte[] prevMsgId = null;
/* 2343 */     byte[] prevCorrelId = null;
/* 2344 */     int prevMatchOptions = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2350 */     prevMatchOptions = getMessageOptions.matchOptions;
/* 2351 */     if (prevMatchOptions == 0 || prevMatchOptions == 1) {
/* 2352 */       prevCorrelId = message.getCorrelationId();
/* 2353 */       message.setCorrelationId(MQMI_NONE);
/* 2354 */       getMessageOptions.matchOptions |= 0x2;
/*      */     } 
/* 2356 */     if (prevMatchOptions == 0 || prevMatchOptions == 2) {
/* 2357 */       prevMsgId = message.getMessageId();
/* 2358 */       message.setMessageId(MQMI_NONE);
/* 2359 */       getMessageOptions.matchOptions |= 0x1;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2364 */     int encoding = message.getEncoding();
/* 2365 */     int ccsid = message.getCharacterSet();
/*      */     
/* 2367 */     if (this.osession == null && this.mgr != null) {
/* 2368 */       this.osession = this.mgr.getSession();
/*      */     }
/*      */     
/* 2371 */     ByteBuffer buffer = message.getInternalBuffer();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2376 */     int minBufferSize = 0;
/*      */     try {
/* 2378 */       minBufferSize = message.sizeOfMQMD() + getMessageOptions.sizeOfMQGetMessageOptions(this.hconn) + 1;
/*      */     }
/* 2380 */     catch (MQException mqe) {
/* 2381 */       if (Trace.isOn) {
/* 2382 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetInt(MQMsg2,MQGetMessageOptions,Pint,Pint)", (Throwable)mqe);
/*      */       }
/*      */       
/* 2385 */       cc.x = mqe.getCompCode();
/* 2386 */       rc.x = mqe.getReason();
/* 2387 */       if (Trace.isOn) {
/* 2388 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetInt(MQMsg2,MQGetMessageOptions,Pint,Pint)", 3);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2398 */     int idealBufferSize = 0;
/* 2399 */     if (buffer == null) {
/* 2400 */       idealBufferSize = 1024;
/*      */     } else {
/* 2402 */       idealBufferSize = buffer.limit();
/*      */     } 
/* 2404 */     if (idealBufferSize < minBufferSize) {
/* 2405 */       idealBufferSize = minBufferSize;
/*      */     }
/* 2407 */     if (this.osession != null && idealBufferSize < this.osession.getMaxMessageSizeForBatch()) {
/* 2408 */       idealBufferSize = this.osession.getMaxMessageSizeForBatch();
/*      */     }
/* 2410 */     if (buffer == null || buffer.limit() < idealBufferSize) {
/* 2411 */       byte[] array = new byte[idealBufferSize];
/* 2412 */       buffer = ByteBuffer.wrap(array);
/*      */     } 
/*      */ 
/*      */     
/* 2416 */     if ((getMessageOptions.options & 0x1006) == 0) {
/* 2417 */       getMessageOptions.options |= 0x4;
/*      */     }
/*      */     
/* 2420 */     if (!this.connected) {
/* 2421 */       if (Trace.isOn) {
/* 2422 */         Trace.traceData(this, "not connected or osession is null", null);
/*      */       }
/*      */       
/* 2425 */       cc.x = 2;
/* 2426 */       rc.x = 2018;
/* 2427 */       if (Trace.isOn) {
/* 2428 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetInt(MQMsg2,MQGetMessageOptions,Pint,Pint)", 4);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 2434 */     if (!this.resourceOpen) {
/* 2435 */       if (Trace.isOn) {
/* 2436 */         Trace.traceData(this, "resource is not open", null);
/*      */       }
/*      */       
/* 2439 */       cc.x = 2;
/* 2440 */       rc.x = 2019;
/* 2441 */       if (Trace.isOn) {
/* 2442 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetInt(MQMsg2,MQGetMessageOptions,Pint,Pint)", 5);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 2448 */     if (Trace.isOn) {
/* 2449 */       Trace.traceData(this, "get options = " + getMessageOptions.options + "\nwait interval = " + getMessageOptions.waitInterval, null);
/* 2450 */       Trace.traceData(this, "message id: ", message.getMessageId());
/* 2451 */       Trace.traceData(this, "correlation id: ", message.getCorrelationId());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2456 */     cc.x = 0;
/* 2457 */     rc.x = 0;
/* 2458 */     this.osession.spiBatchedGet(this.hconn, this.phobj.getHobj(), message, getMessageOptions, buffer.limit(), buffer, this.msgLength, cc, rc);
/*      */     
/* 2460 */     if (Trace.isOn) {
/* 2461 */       Trace.traceData(this, "ProviderMessage is " + this.msgLength.x + " bytes long.", null);
/*      */     }
/*      */ 
/*      */     
/* 2465 */     if (cc.x != 0 && rc.x == 2080) {
/* 2466 */       if (Trace.isOn) {
/* 2467 */         Trace.traceData(this, "Retrying get with buffer size increased to " + this.msgLength.x, null);
/*      */       }
/* 2469 */       buffer = ByteBuffer.wrap(new byte[this.msgLength.x]);
/*      */ 
/*      */       
/* 2472 */       message.setEncoding(encoding);
/* 2473 */       message.setCharacterSet(ccsid);
/*      */ 
/*      */ 
/*      */       
/* 2477 */       this.osession.spiBatchedGet(this.hconn, this.phobj.getHobj(), message, getMessageOptions, buffer.limit(), buffer, this.msgLength, cc, rc);
/* 2478 */       if (Trace.isOn) {
/* 2479 */         Trace.traceData(this, "Buffer size after retry is " + this.msgLength.x, null);
/*      */       }
/*      */     } 
/*      */     
/* 2483 */     if (this.msgLength.x <= 0 && Trace.isOn) {
/* 2484 */       Trace.traceData(this, "spiBatchedGetInt (empty message)", null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2490 */     if (prevMsgId != null || prevCorrelId != null) {
/* 2491 */       getMessageOptions.matchOptions = prevMatchOptions;
/*      */     }
/*      */     
/* 2494 */     if (cc.x == 2 || rc.x == 2080) {
/*      */       
/* 2496 */       if (prevMsgId != null) {
/* 2497 */         message.setMessageId(prevMsgId);
/*      */       }
/* 2499 */       if (prevCorrelId != null) {
/* 2500 */         message.setCorrelationId(prevCorrelId);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2505 */     message.setInternalBuffer(buffer, Math.min(buffer.limit(), this.msgLength.x));
/*      */     
/* 2507 */     if (Trace.isOn) {
/* 2508 */       Trace.traceData(this, "spiBatchedGetInt completed with cc=" + cc.x + ", rc=" + rc.x + ")", null);
/*      */     }
/* 2510 */     if (Trace.isOn) {
/* 2511 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetInt(MQMsg2,MQGetMessageOptions,Pint,Pint)", 6);
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
/*      */   public synchronized int spiBatchedGetNoExc(MQMsg2 message, MQGetMessageOptions getMessageOptions, int MaxMsgLen) {
/* 2528 */     if (Trace.isOn) {
/* 2529 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetNoExc(MQMsg2,MQGetMessageOptions,int)", new Object[] { message, getMessageOptions, 
/*      */             
/* 2531 */             Integer.valueOf(MaxMsgLen) });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 2536 */       spiBatchedGetInt(message, getMessageOptions, MaxMsgLen, this.completionCode, this.reason);
/*      */     }
/* 2538 */     catch (MQException mqe) {
/* 2539 */       if (Trace.isOn) {
/* 2540 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetNoExc(MQMsg2,MQGetMessageOptions,int)", (Throwable)mqe);
/*      */       }
/*      */       
/* 2543 */       this.reason.x = mqe.reasonCode;
/*      */     } 
/*      */     
/* 2546 */     if (Trace.isOn) {
/* 2547 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiBatchedGetNoExc(MQMsg2,MQGetMessageOptions,int)", 
/* 2548 */           Integer.valueOf(this.reason.x));
/*      */     }
/* 2550 */     return this.reason.x;
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
/*      */   public void spiDeferredPut(MQMsg2 message, MQPutMessageOptions putMessageOptions) throws MQException {
/* 2564 */     if (Trace.isOn) {
/* 2565 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiDeferredPut(MQMsg2,MQPutMessageOptions)", new Object[] { message, putMessageOptions });
/*      */     }
/*      */ 
/*      */     
/* 2569 */     MQException mqe = null;
/*      */     
/* 2571 */     synchronized (this) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2576 */       if (message == null) {
/* 2577 */         if (Trace.isOn) {
/* 2578 */           Trace.traceData(this, "message is null", null);
/*      */         }
/* 2580 */         MQException traceRet1 = new MQException(2, 2026, this, "MQJI028");
/* 2581 */         if (Trace.isOn) {
/* 2582 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiDeferredPut(MQMsg2,MQPutMessageOptions)", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/* 2585 */         throw traceRet1;
/*      */       } 
/*      */       
/* 2588 */       if (putMessageOptions == null) {
/* 2589 */         if (Trace.isOn) {
/* 2590 */           Trace.traceData(this, "putMessageOptions is null", null);
/*      */         }
/* 2592 */         MQException traceRet2 = new MQException(2, 2173, this, "MQJI029");
/* 2593 */         if (Trace.isOn) {
/* 2594 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiDeferredPut(MQMsg2,MQPutMessageOptions)", (Throwable)traceRet2, 2);
/*      */         }
/*      */         
/* 2597 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */       
/* 2601 */       putMessageOptions.invalidDestCount = 1;
/* 2602 */       putMessageOptions.knownDestCount = 0;
/* 2603 */       putMessageOptions.unknownDestCount = 0;
/*      */ 
/*      */       
/* 2606 */       if ((putMessageOptions.options & 0x6) == 0) {
/* 2607 */         putMessageOptions.options |= 0x4;
/*      */       }
/*      */       
/* 2610 */       MQSESSION osession = null;
/* 2611 */       if (this.mgr != null) {
/* 2612 */         osession = this.mgr.getSession();
/*      */       }
/*      */       
/* 2615 */       if (!this.connected || osession == null) {
/* 2616 */         if (Trace.isOn) {
/* 2617 */           Trace.traceData(this, "not connected or osession is null", null);
/*      */         }
/* 2619 */         MQException traceRet3 = new MQException(2, 2018, this, "MQJI002");
/* 2620 */         if (Trace.isOn) {
/* 2621 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiDeferredPut(MQMsg2,MQPutMessageOptions)", (Throwable)traceRet3, 3);
/*      */         }
/*      */         
/* 2624 */         throw traceRet3;
/*      */       } 
/*      */       
/* 2627 */       if (!this.resourceOpen) {
/* 2628 */         if (Trace.isOn) {
/* 2629 */           Trace.traceData(this, "resource not open", null);
/*      */         }
/* 2631 */         MQException traceRet4 = new MQException(2, 2019, this, "MQJI027");
/* 2632 */         if (Trace.isOn) {
/* 2633 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiDeferredPut(MQMsg2,MQPutMessageOptions)", (Throwable)traceRet4, 4);
/*      */         }
/*      */         
/* 2636 */         throw traceRet4;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2641 */       if ((putMessageOptions.options & 0x300) != 0) {
/* 2642 */         if (putMessageOptions.contextReference != null) {
/*      */           
/* 2644 */           putMessageOptions.contextReferenceHandle = putMessageOptions.contextReference.getHandle();
/*      */           
/* 2646 */           if (putMessageOptions.contextReferenceHandle == CMQC.jmqi_MQHO_UNUSABLE_HOBJ) {
/* 2647 */             if (Trace.isOn) {
/* 2648 */               Trace.traceData(this, "context handle error", null);
/*      */             }
/* 2650 */             MQException traceRet5 = new MQException(2, 2097, this);
/* 2651 */             if (Trace.isOn) {
/* 2652 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiDeferredPut(MQMsg2,MQPutMessageOptions)", (Throwable)traceRet5, 5);
/*      */             }
/*      */             
/* 2655 */             throw traceRet5;
/*      */           } 
/*      */           
/* 2658 */           if (this.mgr != putMessageOptions.contextReference.mgr) {
/* 2659 */             if (Trace.isOn) {
/* 2660 */               Trace.traceData(this, "ProviderConnection references do not match", null);
/*      */             }
/*      */             
/* 2663 */             MQException traceRet6 = new MQException(2, 2097, this);
/* 2664 */             if (Trace.isOn) {
/* 2665 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiDeferredPut(MQMsg2,MQPutMessageOptions)", (Throwable)traceRet6, 6);
/*      */             }
/*      */             
/* 2668 */             throw traceRet6;
/*      */           } 
/* 2670 */           if (Trace.isOn) {
/* 2671 */             Trace.traceData(this, "Obtained context reference handle:" + putMessageOptions.contextReferenceHandle, null);
/*      */           }
/*      */         } else {
/* 2674 */           if (Trace.isOn) {
/* 2675 */             Trace.traceData(this, "Context reference queue is null", null);
/*      */           }
/*      */           
/* 2678 */           MQException traceRet7 = new MQException(2, 2097, this);
/* 2679 */           if (Trace.isOn) {
/* 2680 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiDeferredPut(MQMsg2,MQPutMessageOptions)", (Throwable)traceRet7, 7);
/*      */           }
/*      */           
/* 2683 */           throw traceRet7;
/*      */         } 
/*      */       }
/*      */       
/* 2687 */       int messageDataLength = message.getMessageDataLength();
/* 2688 */       ByteBuffer msgData = message.getInternalBuffer();
/*      */       
/* 2690 */       if (Trace.isOn) {
/* 2691 */         Trace.traceData(this, "ProviderMessage length = " + messageDataLength + " butes.", null);
/* 2692 */         Trace.traceData(this, "put options = " + putMessageOptions.options + "\nmessage type = " + message.getMessageType() + "\nencoding = " + message.getEncoding() + "\ncharacter set = " + message
/* 2693 */             .getCharacterSet() + "\nformat = " + message.getFormat(), null);
/* 2694 */         Trace.traceData(this, "message id: ", message.getMessageId());
/* 2695 */         Trace.traceData(this, "correlation id: ", message.getCorrelationId());
/*      */       } 
/*      */ 
/*      */       
/* 2699 */       osession.spiDefPut(this.hconn, this.phobj.getHobj(), message, putMessageOptions, messageDataLength, msgData, this.completionCode, this.reason);
/*      */       
/* 2701 */       if (Trace.isOn) {
/* 2702 */         Trace.traceData(this, "Returned message id follows:", message.getMessageId());
/*      */       }
/* 2704 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/* 2705 */         mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*      */       }
/*      */     } 
/*      */     
/* 2709 */     if (mqe != null) {
/* 2710 */       this.parentQmgr.errorOccurred(mqe);
/* 2711 */       if (Trace.isOn) {
/* 2712 */         Trace.traceData(this, "spiDefPut returned with completionCode: " + this.completionCode.x + " and reason: " + this.reason.x, null);
/*      */       }
/* 2714 */       if (Trace.isOn) {
/* 2715 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiDeferredPut(MQMsg2,MQPutMessageOptions)", (Throwable)mqe, 8);
/*      */       }
/*      */       
/* 2718 */       throw mqe;
/*      */     } 
/*      */     
/* 2721 */     if (Trace.isOn) {
/* 2722 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiDeferredPut(MQMsg2,MQPutMessageOptions)");
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
/*      */   public synchronized void spiGet(MQMsg2 message, MQGetMessageOptions getMessageOptions, int spiOptions) throws MQException {
/* 2741 */     if (Trace.isOn) {
/* 2742 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiGet(MQMsg2,MQGetMessageOptions,int)", new Object[] { message, getMessageOptions, 
/*      */             
/* 2744 */             Integer.valueOf(spiOptions) });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 2749 */       MQException mqe = null;
/*      */       
/* 2751 */       synchronized (this) {
/* 2752 */         spiGetInt(message, getMessageOptions, spiOptions, this.completionCode, this.reason);
/*      */ 
/*      */         
/* 2755 */         if (this.completionCode.x != 0 || this.reason.x != 0) {
/* 2756 */           mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*      */         }
/*      */       } 
/* 2759 */       if (mqe != null) {
/* 2760 */         this.parentQmgr.errorOccurred(mqe);
/* 2761 */         if (Trace.isOn) {
/* 2762 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiGet(MQMsg2,MQGetMessageOptions,int)", (Throwable)mqe, 1);
/*      */         }
/*      */         
/* 2765 */         throw mqe;
/*      */       }
/*      */     
/*      */     }
/* 2769 */     catch (MQException mqe) {
/* 2770 */       if (Trace.isOn) {
/* 2771 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiGet(MQMsg2,MQGetMessageOptions,int)", (Throwable)mqe);
/*      */       }
/*      */ 
/*      */       
/* 2775 */       if (Trace.isOn) {
/* 2776 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiGet(MQMsg2,MQGetMessageOptions,int)", (Throwable)mqe, 2);
/*      */       }
/*      */       
/* 2779 */       throw mqe;
/*      */     } 
/*      */     
/* 2782 */     if (Trace.isOn) {
/* 2783 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiGet(MQMsg2,MQGetMessageOptions,int)");
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
/*      */   private void spiGetInt(MQMsg2 message, MQGetMessageOptions getMessageOptions, int spiOptions, Pint cc, Pint rc) throws MQException {
/* 2802 */     if (Trace.isOn) {
/* 2803 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiGetInt(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", new Object[] { message, getMessageOptions, 
/*      */             
/* 2805 */             Integer.valueOf(spiOptions), cc, rc });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 2810 */       if (message == null) {
/*      */         
/* 2812 */         cc.x = 2;
/* 2813 */         rc.x = 2026;
/* 2814 */         if (Trace.isOn) {
/* 2815 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiGetInt(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 1);
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 2821 */       if (getMessageOptions == null) {
/*      */         
/* 2823 */         cc.x = 2;
/* 2824 */         rc.x = 2186;
/* 2825 */         if (Trace.isOn) {
/* 2826 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiGetInt(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 2);
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 2832 */       Pint msgLength = jmqiEnv.newPint();
/* 2833 */       byte[] prevMsgId = null;
/* 2834 */       byte[] prevCorrelId = null;
/* 2835 */       int prevMatchOptions = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2841 */       prevMatchOptions = getMessageOptions.matchOptions;
/* 2842 */       if (prevMatchOptions == 0 || prevMatchOptions == 1) {
/* 2843 */         prevCorrelId = message.getCorrelationId();
/* 2844 */         message.setCorrelationId(MQMI_NONE);
/* 2845 */         getMessageOptions.matchOptions |= 0x2;
/*      */       } 
/* 2847 */       if (prevMatchOptions == 0 || prevMatchOptions == 2) {
/* 2848 */         prevMsgId = message.getMessageId();
/* 2849 */         message.setMessageId(MQMI_NONE);
/* 2850 */         getMessageOptions.matchOptions |= 0x1;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2855 */       int encoding = message.getEncoding();
/* 2856 */       int ccsid = message.getCharacterSet();
/* 2857 */       byte[] msgId = message.getMessageId();
/* 2858 */       byte[] correlId = message.getCorrelationId();
/*      */       
/* 2860 */       if (this.osession == null && this.mgr != null) {
/* 2861 */         this.osession = this.mgr.getSession();
/*      */       }
/*      */ 
/*      */       
/* 2865 */       if ((getMessageOptions.options & 0x1006) == 0) {
/* 2866 */         getMessageOptions.options |= 0x4;
/*      */       }
/*      */       
/* 2869 */       if (!this.connected || this.osession == null) {
/*      */         
/* 2871 */         cc.x = 2;
/* 2872 */         rc.x = 2018;
/* 2873 */         if (Trace.isOn) {
/* 2874 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiGetInt(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 3);
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 2880 */       if (!this.resourceOpen) {
/*      */         
/* 2882 */         cc.x = 2;
/* 2883 */         rc.x = 2019;
/* 2884 */         if (Trace.isOn) {
/* 2885 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiGetInt(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 4);
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 2891 */       if (Trace.isOn) {
/* 2892 */         Trace.traceData(this, "get options = " + getMessageOptions.options + "\nwait interval = " + getMessageOptions.waitInterval, null);
/* 2893 */         Trace.traceData(this, "message id", message.getMessageId());
/* 2894 */         Trace.traceData(this, "correlation id: ", message.getCorrelationId());
/*      */       } 
/*      */       
/* 2897 */       this.osession.spiGet(this.hconn, this.phobj.getHobj(), message, getMessageOptions, spiOptions, this.spiGetBuffer.limit(), this.spiGetBuffer, msgLength, cc, rc);
/*      */       
/* 2899 */       if (Trace.isOn) {
/* 2900 */         Trace.traceData(this, "ProviderMessage is " + msgLength.x + " bytes long.", null);
/*      */       }
/*      */       
/* 2903 */       while (cc.x != 0 && rc.x == 2080) {
/* 2904 */         if (Trace.isOn) {
/* 2905 */           Trace.traceData(this, "Retrying get with increased buffer size.", null);
/*      */         }
/* 2907 */         this.spiGetBuffer = ByteBuffer.wrap(new byte[msgLength.x]);
/*      */ 
/*      */         
/* 2910 */         message.setEncoding(encoding);
/* 2911 */         message.setCharacterSet(ccsid);
/* 2912 */         message.setMessageId(msgId);
/* 2913 */         message.setCorrelationId(correlId);
/*      */         
/* 2915 */         this.osession.spiGet(this.hconn, this.phobj.getHobj(), message, getMessageOptions, spiOptions, this.spiGetBuffer.limit(), this.spiGetBuffer, msgLength, cc, rc);
/*      */       } 
/*      */       
/* 2918 */       if (msgLength.x <= 0 && 
/* 2919 */         Trace.isOn) {
/* 2920 */         Trace.traceData(this, "got empty message", null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2927 */       if (prevMsgId != null || prevCorrelId != null) {
/* 2928 */         getMessageOptions.matchOptions = prevMatchOptions;
/*      */       }
/*      */       
/* 2931 */       if (cc.x == 2) {
/*      */         
/* 2933 */         if (prevMsgId != null) {
/* 2934 */           message.setMessageId(prevMsgId);
/*      */         }
/* 2936 */         if (prevCorrelId != null) {
/* 2937 */           message.setCorrelationId(prevCorrelId);
/*      */         }
/*      */       } 
/*      */       
/* 2941 */       message.setInternalBuffer(this.spiGetBuffer, Math.min(this.spiGetBuffer.limit(), msgLength.x));
/*      */       
/* 2943 */       if (Trace.isOn) {
/* 2944 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiGetInt(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 5);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/* 2949 */     } catch (MQException mqe) {
/* 2950 */       if (Trace.isOn) {
/* 2951 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiGetInt(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", (Throwable)mqe);
/*      */ 
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     finally {
/*      */ 
/*      */       
/* 2960 */       if (Trace.isOn) {
/* 2961 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiGetInt(MQMsg2,MQGetMessageOptions,int,Pint,Pint)");
/*      */       }
/*      */       
/* 2964 */       if (Trace.isOn) {
/* 2965 */         Trace.traceData(this, "spiGetInt completed with cc = " + cc.x + ", rc = " + rc.x, null);
/*      */       }
/*      */     } 
/* 2968 */     if (Trace.isOn) {
/* 2969 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiGetInt(MQMsg2,MQGetMessageOptions,int,Pint,Pint)", 6);
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
/*      */   public void spiPut(MQMsg2 message, MQPutMessageOptions putMessageOptions, int spiOpts) throws MQException {
/* 2983 */     if (Trace.isOn) {
/* 2984 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiPut(MQMsg2,MQPutMessageOptions,int)", new Object[] { message, putMessageOptions, 
/*      */             
/* 2986 */             Integer.valueOf(spiOpts) });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 2991 */       if (message == null) {
/* 2992 */         if (Trace.isOn) {
/* 2993 */           Trace.traceData(this, "Sanity check failed: null message", null);
/*      */         }
/* 2995 */         MQException traceRet1 = new MQException(2, 2026, this, "MQJI028");
/* 2996 */         if (Trace.isOn) {
/* 2997 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiPut(MQMsg2,MQPutMessageOptions,int)", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/* 3000 */         throw traceRet1;
/*      */       } 
/*      */       
/* 3003 */       if (putMessageOptions == null) {
/* 3004 */         if (Trace.isOn) {
/* 3005 */           Trace.traceData(this, "Sanity check failed: null PutMessageOptions", null);
/*      */         }
/* 3007 */         MQException traceRet2 = new MQException(2, 2173, this, "MQJI029");
/* 3008 */         if (Trace.isOn) {
/* 3009 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiPut(MQMsg2,MQPutMessageOptions,int)", (Throwable)traceRet2, 2);
/*      */         }
/*      */         
/* 3012 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3017 */       putMessageOptions.invalidDestCount = 1;
/* 3018 */       putMessageOptions.knownDestCount = 0;
/* 3019 */       putMessageOptions.unknownDestCount = 0;
/*      */ 
/*      */       
/* 3022 */       if ((putMessageOptions.options & 0x6) == 0) {
/* 3023 */         putMessageOptions.options |= 0x4;
/*      */       }
/*      */       
/* 3026 */       MQSESSION osession = null;
/* 3027 */       if (this.mgr != null) {
/* 3028 */         osession = this.mgr.getSession();
/*      */       }
/*      */       
/* 3031 */       if (!this.connected || osession == null) {
/* 3032 */         if (Trace.isOn) {
/* 3033 */           Trace.traceData(this, "ProviderConnection check failed.", null);
/*      */         }
/* 3035 */         MQException traceRet3 = new MQException(2, 2018, this, "MQJI002");
/* 3036 */         if (Trace.isOn) {
/* 3037 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiPut(MQMsg2,MQPutMessageOptions,int)", (Throwable)traceRet3, 3);
/*      */         }
/*      */         
/* 3040 */         throw traceRet3;
/*      */       } 
/*      */       
/* 3043 */       if (!this.resourceOpen) {
/* 3044 */         if (Trace.isOn) {
/* 3045 */           Trace.traceData(this, "hObj check failed. resource closed.", null);
/*      */         }
/* 3047 */         MQException traceRet4 = new MQException(2, 2019, this, "MQJI027");
/* 3048 */         if (Trace.isOn) {
/* 3049 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiPut(MQMsg2,MQPutMessageOptions,int)", (Throwable)traceRet4, 4);
/*      */         }
/*      */         
/* 3052 */         throw traceRet4;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3058 */         if ((putMessageOptions.options & 0x300) != 0) {
/* 3059 */           if (putMessageOptions.contextReference != null) {
/*      */             
/* 3061 */             putMessageOptions.contextReferenceHandle = putMessageOptions.contextReference.getHandle();
/*      */             
/* 3063 */             if (putMessageOptions.contextReferenceHandle == CMQC.jmqi_MQHO_UNUSABLE_HOBJ) {
/* 3064 */               if (Trace.isOn) {
/* 3065 */                 Trace.traceData(this, "could not get contextReferenceHandle", null);
/*      */               }
/* 3067 */               MQException traceRet5 = new MQException(2, 2097, this);
/* 3068 */               if (Trace.isOn) {
/* 3069 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiPut(MQMsg2,MQPutMessageOptions,int)", (Throwable)traceRet5, 5);
/*      */               }
/*      */               
/* 3072 */               throw traceRet5;
/*      */             } 
/*      */             
/* 3075 */             if (this.mgr != putMessageOptions.contextReference.mgr) {
/* 3076 */               if (Trace.isOn) {
/* 3077 */                 Trace.traceData(this, "ProviderConnection references do not match", null);
/*      */               }
/* 3079 */               MQException traceRet6 = new MQException(2, 2097, this);
/* 3080 */               if (Trace.isOn) {
/* 3081 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiPut(MQMsg2,MQPutMessageOptions,int)", (Throwable)traceRet6, 6);
/*      */               }
/*      */               
/* 3084 */               throw traceRet6;
/*      */             } 
/* 3086 */             if (Trace.isOn) {
/* 3087 */               Trace.traceData(this, "Obtained context reference handle:" + putMessageOptions.contextReferenceHandle, null);
/*      */             }
/*      */           } else {
/* 3090 */             if (Trace.isOn) {
/* 3091 */               Trace.traceData(this, "Context reference queue is null", null);
/*      */             }
/* 3093 */             MQException traceRet7 = new MQException(2, 2097, this);
/* 3094 */             if (Trace.isOn) {
/* 3095 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiPut(MQMsg2,MQPutMessageOptions,int)", (Throwable)traceRet7, 7);
/*      */             }
/*      */             
/* 3098 */             throw traceRet7;
/*      */           }
/*      */         
/*      */         }
/* 3102 */       } catch (MQException mqe) {
/* 3103 */         if (Trace.isOn) {
/* 3104 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiPut(MQMsg2,MQPutMessageOptions,int)", (Throwable)mqe, 1);
/*      */         }
/*      */         
/* 3107 */         if (Trace.isOn) {
/* 3108 */           Trace.traceData(this, "Failed to process Context Reference.", null);
/*      */         }
/* 3110 */         if (Trace.isOn) {
/* 3111 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiPut(MQMsg2,MQPutMessageOptions,int)", (Throwable)mqe, 8);
/*      */         }
/*      */         
/* 3114 */         throw mqe;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 3119 */         MQException mqe = null;
/*      */         
/* 3121 */         synchronized (this) {
/* 3122 */           int messageDataLength = message.getMessageDataLength();
/* 3123 */           ByteBuffer msgData = message.getInternalBuffer();
/*      */           
/* 3125 */           if (Trace.isOn) {
/* 3126 */             Trace.traceData(this, "ProviderMessage length = " + messageDataLength + " butes.", null);
/* 3127 */             Trace.traceData(this, "\nput options   = " + putMessageOptions.options + "\nspi options   = " + spiOpts + "\nmessage type  = " + message.getMessageType() + "\nencoding      = " + message
/* 3128 */                 .getEncoding() + "\ncharacter set = " + message.getCharacterSet() + "\nformat        = " + message.getFormat(), null);
/*      */             
/* 3130 */             Trace.traceData(this, "message id: ", message.getMessageId());
/* 3131 */             Trace.traceData(this, "correlation id: ", message.getCorrelationId());
/*      */           } 
/*      */ 
/*      */           
/* 3135 */           osession.spiPut(this.hconn, this.phobj.getHobj(), message, putMessageOptions, spiOpts, messageDataLength, msgData, this.completionCode, this.reason);
/*      */           
/* 3137 */           if (Trace.isOn) {
/* 3138 */             Trace.traceData(this, "Returned message id follows:", message.getMessageId());
/*      */           }
/* 3140 */           if (this.completionCode.x != 0 || this.reason.x != 0) {
/* 3141 */             mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*      */           }
/*      */         } 
/*      */         
/* 3145 */         if (mqe != null) {
/* 3146 */           this.parentQmgr.errorOccurred(mqe);
/* 3147 */           if (Trace.isOn) {
/* 3148 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiPut(MQMsg2,MQPutMessageOptions,int)", (Throwable)mqe, 9);
/*      */           }
/*      */           
/* 3151 */           throw mqe;
/*      */         }
/*      */       
/*      */       }
/* 3155 */       catch (MQException mqe) {
/* 3156 */         if (Trace.isOn) {
/* 3157 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiPut(MQMsg2,MQPutMessageOptions,int)", (Throwable)mqe, 2);
/*      */         }
/*      */         
/* 3160 */         if (Trace.isOn) {
/* 3161 */           Trace.traceData(this, "Failed to put message.", null);
/*      */         }
/* 3163 */         if (Trace.isOn) {
/* 3164 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiPut(MQMsg2,MQPutMessageOptions,int)", (Throwable)mqe, 10);
/*      */         }
/*      */         
/* 3167 */         throw mqe;
/*      */       }
/*      */     
/* 3170 */     } catch (MQException mqe) {
/* 3171 */       if (Trace.isOn) {
/* 3172 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiPut(MQMsg2,MQPutMessageOptions,int)", (Throwable)mqe, 3);
/*      */       }
/*      */       
/* 3175 */       if (Trace.isOn) {
/* 3176 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiPut(MQMsg2,MQPutMessageOptions,int)", (Throwable)mqe, 11);
/*      */       }
/*      */       
/* 3179 */       throw mqe;
/*      */     } finally {
/*      */       
/* 3182 */       if (Trace.isOn) {
/* 3183 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiPut(MQMsg2,MQPutMessageOptions,int)");
/*      */       }
/*      */       
/* 3186 */       if (Trace.isOn) {
/* 3187 */         Trace.traceData(this, "spiPut completed with cc = " + this.completionCode.x + " rc = " + this.reason.x, null);
/*      */       }
/*      */     } 
/*      */     
/* 3191 */     if (Trace.isOn)
/* 3192 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueue", "spiPut(MQMsg2,MQPutMessageOptions,int)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */