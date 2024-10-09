/*      */ package com.ibm.mq.headers.pcf;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.MQGetMessageOptions;
/*      */ import com.ibm.mq.MQMessage;
/*      */ import com.ibm.mq.MQPutMessageOptions;
/*      */ import com.ibm.mq.MQQueue;
/*      */ import com.ibm.mq.MQQueueManager;
/*      */ import com.ibm.mq.headers.MQDataException;
/*      */ import com.ibm.mq.internal.MQCommonServices;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.DataInput;
/*      */ import java.io.DataOutput;
/*      */ import java.io.IOException;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Vector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PCFAgent
/*      */   extends JmqiObject
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFAgent.java";
/*      */   static final String DEFAULT_MODEL_QUEUE_NAME = "SYSTEM.DEFAULT.MODEL.QUEUE";
/*      */   static final String DEFAULT_HOST_NAME = "localhost";
/*      */   static final String DEFAULT_CHANNEL_NAME = "SYSTEM.DEF.SVRCONN";
/*      */   public static final int MAXIMUM_ALLOWED_PREFIX_LENGTH = 32;
/*      */   
/*      */   static {
/*   85 */     if (Trace.isOn) {
/*   86 */       Trace.data("com.ibm.mq.headers.pcf.PCFAgent", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/PCFAgent.java");
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
/*  101 */   protected final MQPutMessageOptions pmo = new MQPutMessageOptions();
/*  102 */   protected final MQGetMessageOptions gmo = new MQGetMessageOptions();
/*      */   
/*  104 */   protected String modelQueueName = "SYSTEM.DEFAULT.MODEL.QUEUE";
/*  105 */   protected String replyQueuePrefix = "";
/*      */   protected MQQueueManager qmanager;
/*      */   protected MQQueue adminQueue;
/*      */   protected MQQueue replyQueue;
/*  109 */   protected int expiryTime = 300;
/*  110 */   protected int waitInterval = 30000;
/*  111 */   protected volatile int encoding = 273;
/*  112 */   protected volatile int defaultCharacterSet = 1208;
/*      */   protected String qmanager_name;
/*      */   protected int qmanager_level;
/*      */   protected int qmanager_platform;
/*  116 */   protected int qmanager_ccsid = this.defaultCharacterSet;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  133 */   private static String ENABLEPCFRESPONSEEXPIRY = "com.ibm.mq.pcf.enablePCFResponseExpiry";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String replyQueueName;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static JmqiEnvironment getJmqiEnv() {
/*  149 */     if (Trace.isOn) {
/*  150 */       Trace.data("com.ibm.mq.headers.pcf.PCFAgent", "getJmqiEnv()", "getter", MQCommonServices.jmqiEnv);
/*      */     }
/*      */     
/*  153 */     return MQCommonServices.jmqiEnv;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PCFAgent() {
/*  161 */     super(getJmqiEnv());
/*  162 */     if (Trace.isOn) {
/*  163 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "<init>()");
/*      */     }
/*  165 */     this.pmo.options = 128;
/*  166 */     this.gmo.options = 24577;
/*  167 */     this.gmo.waitInterval = this.waitInterval;
/*  168 */     if (Trace.isOn) {
/*  169 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "<init>()");
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
/*      */   public PCFAgent(MQQueueManager qmanager) throws MQDataException {
/*  182 */     this();
/*  183 */     if (Trace.isOn) {
/*  184 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "<init>(MQQueueManager)", new Object[] { qmanager });
/*      */     }
/*      */ 
/*      */     
/*  188 */     connect(qmanager);
/*  189 */     if (Trace.isOn) {
/*  190 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "<init>(MQQueueManager)");
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
/*      */   public PCFAgent(String host, int port, String channel) throws MQDataException {
/*  205 */     this();
/*  206 */     if (Trace.isOn) {
/*  207 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "<init>(String,int,String)", new Object[] { host, 
/*  208 */             Integer.valueOf(port), channel });
/*      */     }
/*      */     
/*  211 */     connect(host, port, channel);
/*  212 */     if (Trace.isOn) {
/*  213 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "<init>(String,int,String)");
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
/*      */   public PCFAgent(String qmanager) throws MQDataException {
/*  226 */     this();
/*  227 */     if (Trace.isOn) {
/*  228 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "<init>(String)", new Object[] { qmanager });
/*      */     }
/*      */ 
/*      */     
/*  232 */     connect(qmanager);
/*  233 */     if (Trace.isOn) {
/*  234 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "<init>(String)");
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
/*      */   public synchronized void connect(MQQueueManager qmanagerP) throws MQDataException {
/*  247 */     if (Trace.isOn) {
/*  248 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(MQQueueManager)", new Object[] { qmanagerP });
/*      */     }
/*      */     
/*  251 */     open(qmanagerP, true);
/*  252 */     if (Trace.isOn) {
/*  253 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(MQQueueManager)");
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
/*      */   public synchronized void connect(MQQueueManager qmanagerP, String targetQueue, String targetQmanager) throws MQDataException {
/*  268 */     if (Trace.isOn) {
/*  269 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(MQQueueManager,String,String)", new Object[] { qmanagerP, targetQueue, targetQmanager });
/*      */     }
/*      */     
/*  272 */     open(qmanagerP, targetQueue, targetQmanager, true);
/*  273 */     if (Trace.isOn) {
/*  274 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(MQQueueManager,String,String)");
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
/*      */   public synchronized void connect(String hostP, int port, String channelP) throws MQDataException {
/*  290 */     if (Trace.isOn) {
/*  291 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(String,int,String)", new Object[] { hostP, 
/*  292 */             Integer.valueOf(port), channelP });
/*      */     }
/*      */ 
/*      */     
/*  296 */     Hashtable<String, Object> props = new Hashtable<>();
/*  297 */     String host = hostP;
/*  298 */     String channel = channelP;
/*      */     
/*  300 */     props.put("port", Integer.valueOf(port));
/*      */     
/*  302 */     if (host == null || host.trim().length() == 0) {
/*  303 */       host = "localhost";
/*      */     }
/*  305 */     props.put("hostname", host);
/*      */     
/*  307 */     if (channel == null || channel.trim().length() == 0) {
/*  308 */       channel = "SYSTEM.DEF.SVRCONN";
/*      */     }
/*  310 */     props.put("channel", channel);
/*      */     
/*  312 */     MQQueueManager queueManager = null;
/*      */     
/*      */     try {
/*  315 */       queueManager = new MQQueueManager("", props);
/*      */       
/*  317 */       open(queueManager, false);
/*      */     }
/*  319 */     catch (Exception e) {
/*  320 */       if (Trace.isOn) {
/*  321 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(String,int,String)", e);
/*      */       }
/*      */       
/*  324 */       MQDataException traceRet1 = MQDataException.getMQDataException(e);
/*  325 */       if (Trace.isOn) {
/*  326 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(String,int,String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  329 */       throw traceRet1;
/*      */     } 
/*  331 */     if (Trace.isOn) {
/*  332 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(String,int,String)");
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
/*      */   public synchronized void connect(String hostP, int port, String channelP, String targetQueue, String targetQmanager) throws MQDataException {
/*  350 */     if (Trace.isOn) {
/*  351 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(String,int,String,String,String)", new Object[] { hostP, 
/*  352 */             Integer.valueOf(port), channelP, targetQueue, targetQmanager });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  357 */     Hashtable<String, Object> props = new Hashtable<>();
/*  358 */     String host = hostP;
/*  359 */     String channel = channelP;
/*      */     
/*  361 */     props.put("port", Integer.valueOf(port));
/*      */     
/*  363 */     if (host == null || host.trim().length() == 0) {
/*  364 */       host = "localhost";
/*      */     }
/*  366 */     props.put("hostname", host);
/*      */     
/*  368 */     if (channel == null || channel.trim().length() == 0) {
/*  369 */       channel = "SYSTEM.DEF.SVRCONN";
/*      */     }
/*  371 */     props.put("channel", channel);
/*      */     
/*  373 */     MQQueueManager queueManager = null;
/*      */     
/*      */     try {
/*  376 */       queueManager = new MQQueueManager("", props);
/*      */       
/*  378 */       open(queueManager, targetQueue, targetQmanager, false);
/*      */     }
/*  380 */     catch (Exception e) {
/*  381 */       if (Trace.isOn) {
/*  382 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(String,int,String,String,String)", e);
/*      */       }
/*      */       
/*  385 */       MQDataException traceRet1 = MQDataException.getMQDataException(e);
/*  386 */       if (Trace.isOn) {
/*  387 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(String,int,String,String,String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  390 */       throw traceRet1;
/*      */     } 
/*  392 */     if (Trace.isOn) {
/*  393 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(String,int,String,String,String)");
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
/*      */   public synchronized void connect(String qmanagerP) throws MQDataException {
/*  407 */     if (Trace.isOn) {
/*  408 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(String)", new Object[] { qmanagerP });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  413 */     Hashtable<String, Object> props = new Hashtable<>();
/*  414 */     props.put("port", Integer.valueOf(1414));
/*  415 */     props.put("hostname", "");
/*  416 */     props.put("channel", "");
/*      */     
/*  418 */     MQQueueManager queueManager = null;
/*      */     
/*      */     try {
/*  421 */       queueManager = new MQQueueManager(qmanagerP, props);
/*      */       
/*  423 */       open(queueManager, false);
/*      */     }
/*  425 */     catch (Exception e) {
/*  426 */       if (Trace.isOn) {
/*  427 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(String)", e);
/*      */       }
/*  429 */       MQDataException traceRet1 = MQDataException.getMQDataException(e);
/*  430 */       if (Trace.isOn) {
/*  431 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(String)", (Throwable)traceRet1);
/*      */       }
/*  433 */       throw traceRet1;
/*      */     } 
/*  435 */     if (Trace.isOn) {
/*  436 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(String)");
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
/*      */   public synchronized void connect(String qmanagerP, String targetQueue, String targetQmanager) throws MQDataException {
/*  452 */     if (Trace.isOn) {
/*  453 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(String,String,String)", new Object[] { qmanagerP, targetQueue, targetQmanager });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  458 */     Hashtable<String, Object> props = new Hashtable<>();
/*  459 */     props.put("port", Integer.valueOf(1414));
/*  460 */     props.put("hostname", "");
/*  461 */     props.put("channel", "");
/*      */     
/*  463 */     MQQueueManager queueManager = null;
/*      */     
/*      */     try {
/*  466 */       queueManager = new MQQueueManager(qmanagerP, props);
/*      */       
/*  468 */       open(queueManager, targetQueue, targetQmanager, false);
/*      */     }
/*  470 */     catch (Exception e) {
/*  471 */       if (Trace.isOn) {
/*  472 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(String,String,String)", e);
/*      */       }
/*      */       
/*  475 */       MQDataException traceRet1 = MQDataException.getMQDataException(e);
/*  476 */       if (Trace.isOn) {
/*  477 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(String,String,String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  480 */       throw traceRet1;
/*      */     } 
/*  482 */     if (Trace.isOn) {
/*  483 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "connect(String,String,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected synchronized void open(MQQueueManager qmanagerP, boolean external) throws MQDataException {
/*  489 */     if (Trace.isOn) {
/*  490 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "open(MQQueueManager,boolean)", new Object[] { qmanagerP, 
/*  491 */             Boolean.valueOf(external) });
/*      */     }
/*      */     try {
/*  494 */       open(qmanagerP, null, null, external);
/*      */     }
/*  496 */     catch (Exception e) {
/*  497 */       if (Trace.isOn) {
/*  498 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "open(MQQueueManager,boolean)", e);
/*      */       }
/*      */       
/*  501 */       MQDataException traceRet1 = MQDataException.getMQDataException(e);
/*  502 */       if (Trace.isOn) {
/*  503 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "open(MQQueueManager,boolean)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  506 */       throw traceRet1;
/*      */     } 
/*  508 */     if (Trace.isOn) {
/*  509 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "open(MQQueueManager,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected synchronized void open(MQQueueManager qmanagerP, String targetQueueP, String targetQmanager, boolean external) throws MQDataException {
/*  516 */     if (Trace.isOn) {
/*  517 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", new Object[] { qmanagerP, targetQueueP, targetQmanager, 
/*      */             
/*  519 */             Boolean.valueOf(external) });
/*      */     }
/*  521 */     String targetQueue = targetQueueP;
/*      */     try {
/*  523 */       disconnect();
/*      */     
/*      */     }
/*  526 */     catch (MQDataException mqe) {
/*  527 */       if (Trace.isOn) {
/*  528 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)mqe, 1);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  534 */     if (targetQueue == null || targetQueue.length() == 0) {
/*      */       try {
/*  536 */         targetQueue = qmanagerP.getCommandInputQueueName();
/*      */       }
/*  538 */       catch (Exception e) {
/*  539 */         if (Trace.isOn) {
/*  540 */           Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", e, 2);
/*      */         }
/*      */ 
/*      */         
/*  544 */         if (!external)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  556 */           disconnectInternalQueueManagerAfterFailure(qmanagerP);
/*      */         }
/*      */ 
/*      */         
/*  560 */         MQDataException traceRet1 = MQDataException.getMQDataException(e);
/*  561 */         if (Trace.isOn) {
/*  562 */           Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/*  565 */         throw traceRet1;
/*      */       } 
/*      */     }
/*      */     
/*  569 */     String prefix = this.replyQueuePrefix;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*      */       try {
/*  575 */         this.adminQueue = qmanagerP.accessQueue(targetQueue, 8240, targetQmanager, "", "mqm");
/*      */       }
/*  577 */       catch (MQException e) {
/*  578 */         if (Trace.isOn) {
/*  579 */           Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)e, 3);
/*      */         }
/*      */ 
/*      */         
/*  583 */         if (e.getReason() == 2045) {
/*      */           
/*  585 */           this.adminQueue = qmanagerP.accessQueue(targetQueue, 8208, targetQmanager, "", "mqm");
/*      */         } else {
/*  587 */           if (!external)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  599 */             disconnectInternalQueueManagerAfterFailure(qmanagerP);
/*      */           }
/*      */ 
/*      */           
/*  603 */           if (Trace.isOn) {
/*  604 */             Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)e, 2);
/*      */           }
/*      */           
/*  607 */           throw e;
/*      */         } 
/*      */       } 
/*      */       
/*  611 */       this.replyQueue = qmanagerP.accessQueue(this.modelQueueName, 8196, "", prefix, "mqm");
/*      */     }
/*  613 */     catch (Exception e) {
/*  614 */       if (Trace.isOn) {
/*  615 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", e, 4);
/*      */       }
/*      */       
/*  618 */       if (!external)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  630 */         disconnectInternalQueueManagerAfterFailure(qmanagerP);
/*      */       }
/*      */ 
/*      */       
/*  634 */       MQDataException traceRet2 = MQDataException.getMQDataException(e);
/*  635 */       if (Trace.isOn) {
/*  636 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)traceRet2, 3);
/*      */       }
/*      */       
/*  639 */       throw traceRet2;
/*      */     } 
/*      */     
/*  642 */     this.replyQueueName = this.replyQueue.name;
/*      */ 
/*      */     
/*  645 */     this.replyQueue.closeOptions = 2;
/*      */ 
/*      */     
/*      */     try {
/*  649 */       getBasicQmgrInfo(qmanagerP, true);
/*  650 */     } catch (MQDataException e) {
/*  651 */       if (Trace.isOn) {
/*  652 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)e, 5);
/*      */       }
/*      */       
/*  655 */       if (!external)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  667 */         disconnectInternalQueueManagerAfterFailure(qmanagerP);
/*      */       }
/*      */       
/*  670 */       if (Trace.isOn) {
/*  671 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)e, 4);
/*      */       }
/*      */       
/*  674 */       throw e;
/*      */     } 
/*      */     
/*  677 */     if (!external)
/*      */     {
/*      */ 
/*      */       
/*  681 */       this.qmanager = qmanagerP;
/*      */     }
/*      */     
/*  684 */     if (Trace.isOn) {
/*  685 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)");
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
/*      */   private void disconnectInternalQueueManagerAfterFailure(MQQueueManager qm) {
/*  716 */     if (Trace.isOn) {
/*  717 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "disconnectInternalQueueManagerAfterFailure(MQQueueManager)", new Object[] { qm });
/*      */     }
/*      */     
/*      */     try {
/*  721 */       qm.disconnect();
/*  722 */     } catch (MQException mqe) {
/*      */       
/*  724 */       if (Trace.isOn) {
/*  725 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "disconnectInternalQueueManagerAfterFailure(MQQueueManager)", (Throwable)mqe);
/*      */       }
/*      */     } 
/*      */     
/*  729 */     if (Trace.isOn) {
/*  730 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "disconnectInternalQueueManagerAfterFailure(MQQueueManager)");
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
/*      */   private void getBasicQmgrInfo(MQQueueManager qmgr, boolean tryBacklevel) throws MQDataException {
/*  751 */     if (Trace.isOn) {
/*  752 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "getBasicQmgrInfo(MQQueueManager,boolean)", new Object[] { qmgr, 
/*      */             
/*  754 */             Boolean.valueOf(tryBacklevel) });
/*      */     }
/*      */     
/*      */     try {
/*  758 */       this.qmanager_level = qmgr.getCommandLevel();
/*      */     }
/*  760 */     catch (Exception e) {
/*  761 */       if (Trace.isOn) {
/*  762 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "getBasicQmgrInfo(MQQueueManager,boolean)", e, 1);
/*      */       }
/*      */       
/*  765 */       MQDataException traceRet1 = MQDataException.getMQDataException(e);
/*  766 */       if (Trace.isOn) {
/*  767 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "getBasicQmgrInfo(MQQueueManager,boolean)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/*  770 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  776 */     int type = 16;
/*  777 */     int version = 3;
/*      */     
/*  779 */     if (tryBacklevel) {
/*  780 */       type = 1;
/*  781 */       version = 1;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/*  786 */       MQMessage message = setRequestMQMD(new MQMessage());
/*  787 */       MQCFH.write((DataOutput)message, 2, 1, type, version);
/*  788 */       MQCFIL.write((DataOutput)message, 1001, new int[] { 32, 2015, 31 });
/*      */       
/*      */       try {
/*  791 */         this.adminQueue.put(message, this.pmo);
/*      */       }
/*  793 */       catch (Exception e) {
/*  794 */         if (Trace.isOn) {
/*  795 */           Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "getBasicQmgrInfo(MQQueueManager,boolean)", e, 2);
/*      */         }
/*      */         
/*  798 */         MQDataException traceRet2 = MQDataException.getMQDataException(e);
/*  799 */         if (Trace.isOn) {
/*  800 */           Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "getBasicQmgrInfo(MQQueueManager,boolean)", (Throwable)traceRet2, 2);
/*      */         }
/*      */         
/*  803 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */       
/*  807 */       message.messageId = null;
/*  808 */       message.encoding = this.encoding;
/*  809 */       message.characterSet = this.defaultCharacterSet;
/*      */       try {
/*  811 */         this.replyQueue.get(message, this.gmo);
/*      */       }
/*  813 */       catch (Exception e) {
/*  814 */         if (Trace.isOn) {
/*  815 */           Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "getBasicQmgrInfo(MQQueueManager,boolean)", e, 3);
/*      */         }
/*      */         
/*  818 */         MQDataException traceRet3 = MQDataException.getMQDataException(e);
/*  819 */         if (Trace.isOn) {
/*  820 */           Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "getBasicQmgrInfo(MQQueueManager,boolean)", (Throwable)traceRet3, 3);
/*      */         }
/*      */         
/*  823 */         throw traceRet3;
/*      */       } 
/*      */ 
/*      */       
/*  827 */       MQCFH cfh = new MQCFH((DataInput)message);
/*      */       
/*  829 */       if (cfh.getReason() == 0) {
/*  830 */         int parameterCount = cfh.getParameterCount();
/*  831 */         while (parameterCount-- > 0) {
/*  832 */           String temp; PCFParameter p = PCFParameter.nextParameter((DataInput)message);
/*      */           
/*  834 */           switch (p.getParameter()) {
/*      */             case 32:
/*  836 */               this.qmanager_platform = ((MQCFIN)p).getIntValue();
/*      */             
/*      */             case 2015:
/*  839 */               temp = ((MQCFST)p).getString();
/*  840 */               this.qmanager_name = (temp != null) ? temp.trim() : null;
/*      */             
/*      */             case 2:
/*  843 */               this.qmanager_ccsid = ((MQCFIN)p).getIntValue();
/*      */ 
/*      */             
/*      */             case 31:
/*  847 */               this.qmanager_level = ((MQCFIN)p).getIntValue();
/*      */           } 
/*      */ 
/*      */ 
/*      */         
/*      */         } 
/*  853 */       } else if ((cfh.getReason() == 3001 || cfh.getReason() == 3003) && tryBacklevel == true) {
/*      */ 
/*      */         
/*  856 */         getBasicQmgrInfo(qmgr, false);
/*      */       } else {
/*  858 */         MQDataException traceRet4 = new MQDataException(cfh.getCompCode(), cfh.getReason(), this);
/*  859 */         if (Trace.isOn) {
/*  860 */           Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "getBasicQmgrInfo(MQQueueManager,boolean)", (Throwable)traceRet4, 4);
/*      */         }
/*      */         
/*  863 */         throw traceRet4;
/*      */       }
/*      */     
/*  866 */     } catch (IOException e) {
/*  867 */       if (Trace.isOn) {
/*  868 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "getBasicQmgrInfo(MQQueueManager,boolean)", e, 4);
/*      */       }
/*      */       
/*  871 */       MQDataException returnException = new MQDataException(2, 2033, this);
/*  872 */       returnException.initCause(e);
/*  873 */       if (Trace.isOn) {
/*  874 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "getBasicQmgrInfo(MQQueueManager,boolean)", (Throwable)returnException, 5);
/*      */       }
/*      */       
/*  877 */       throw returnException;
/*      */     } 
/*  879 */     if (Trace.isOn) {
/*  880 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "getBasicQmgrInfo(MQQueueManager,boolean)");
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
/*      */   public synchronized void disconnect() throws MQDataException {
/*  898 */     if (Trace.isOn) {
/*  899 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "disconnect()");
/*      */     }
/*      */     try {
/*  902 */       if (this.adminQueue != null) {
/*  903 */         this.adminQueue.close();
/*      */       }
/*      */       
/*  906 */       if (this.replyQueue != null) {
/*  907 */         this.replyQueue.close();
/*      */       }
/*      */       
/*  910 */       if (this.qmanager != null) {
/*  911 */         this.qmanager.disconnect();
/*      */       }
/*      */     }
/*  914 */     catch (Exception e) {
/*  915 */       if (Trace.isOn) {
/*  916 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "disconnect()", e);
/*      */       }
/*  918 */       MQDataException traceRet1 = MQDataException.getMQDataException(e);
/*  919 */       if (Trace.isOn) {
/*  920 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "disconnect()", (Throwable)traceRet1);
/*      */       }
/*  922 */       throw traceRet1;
/*      */     }
/*      */     finally {
/*      */       
/*  926 */       if (Trace.isOn) {
/*  927 */         Trace.finallyBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "disconnect()");
/*      */       }
/*  929 */       this.adminQueue = null;
/*  930 */       this.replyQueue = null;
/*  931 */       this.replyQueueName = null;
/*  932 */       this.qmanager = null;
/*  933 */       this.qmanager_name = null;
/*  934 */       this.qmanager_platform = 0;
/*      */     } 
/*  936 */     if (Trace.isOn) {
/*  937 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "disconnect()");
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
/*      */   public synchronized MQMessage[] send(int command, PCFParameter[] parameters) throws MQDataException, IOException {
/*      */     PCFAgentResponseTracker tracker;
/*  956 */     if (Trace.isOn)
/*  957 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "send(int,PCFParameter [ ])", new Object[] {
/*  958 */             Integer.valueOf(command), parameters
/*      */           }); 
/*  960 */     if (this.adminQueue == null) {
/*  961 */       MQDataException traceRet1 = new MQDataException(2, 6124, this);
/*      */       
/*  963 */       if (Trace.isOn) {
/*  964 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "send(int,PCFParameter [ ])", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/*  967 */       throw traceRet1;
/*      */     } 
/*      */     
/*  970 */     MQMessage message = setRequestMQMD(new MQMessage());
/*      */ 
/*      */     
/*  973 */     int count = (parameters == null) ? 0 : parameters.length;
/*      */     
/*  975 */     if (this.qmanager_platform == 1) {
/*      */ 
/*      */       
/*  978 */       tracker = new PCFAgentResponseTracker390();
/*  979 */       MQCFH.write((DataOutput)message, command, count, 16, 3);
/*      */     }
/*      */     else {
/*      */       
/*  983 */       tracker = new PCFAgentResponseTrackerNon390();
/*      */       
/*  985 */       int version = 1;
/*      */       
/*  987 */       for (int j = 0; j < count && version < 3; j++) {
/*  988 */         version = Math.max(version, parameters[j].getHeaderVersion());
/*      */       }
/*      */       
/*  991 */       MQCFH.write((DataOutput)message, command, count, 1, version);
/*      */     } 
/*      */     
/*  994 */     for (int i = 0; i < count; i++) {
/*  995 */       parameters[i].write((DataOutput)message);
/*      */     }
/*      */     try {
/*  998 */       this.adminQueue.put(message, this.pmo);
/*      */     }
/* 1000 */     catch (Exception e) {
/* 1001 */       if (Trace.isOn) {
/* 1002 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "send(int,PCFParameter [ ])", e, 1);
/*      */       }
/*      */       
/* 1005 */       MQDataException traceRet2 = MQDataException.getMQDataException(e);
/* 1006 */       if (Trace.isOn) {
/* 1007 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "send(int,PCFParameter [ ])", (Throwable)traceRet2, 2);
/*      */       }
/*      */       
/* 1010 */       throw traceRet2;
/*      */     } 
/*      */     
/* 1013 */     byte[] correlationId = message.correlationId;
/* 1014 */     Vector<MQMessage> v = new Vector<>();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/* 1020 */       message = new MQMessage();
/* 1021 */       message.correlationId = correlationId;
/* 1022 */       message.encoding = this.encoding;
/* 1023 */       message.characterSet = this.defaultCharacterSet;
/*      */       try {
/* 1025 */         this.replyQueue.get(message, this.gmo);
/*      */       }
/* 1027 */       catch (Exception e) {
/* 1028 */         if (Trace.isOn) {
/* 1029 */           Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "send(int,PCFParameter [ ])", e, 2);
/*      */         }
/*      */         
/* 1032 */         MQDataException traceRet3 = MQDataException.getMQDataException(e);
/* 1033 */         if (Trace.isOn) {
/* 1034 */           Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "send(int,PCFParameter [ ])", (Throwable)traceRet3, 3);
/*      */         }
/*      */         
/* 1037 */         throw traceRet3;
/*      */       } 
/* 1039 */       v.addElement(message);
/*      */     }
/* 1041 */     while (!tracker.isLast(message));
/*      */     
/* 1043 */     MQMessage[] responses = new MQMessage[v.size()];
/*      */     
/* 1045 */     v.copyInto((Object[])responses);
/*      */     
/* 1047 */     if (Trace.isOn) {
/* 1048 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "send(int,PCFParameter [ ])", responses);
/*      */     }
/*      */     
/* 1051 */     return responses;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQMessage setRequestMQMD(MQMessage message) throws MQDataException {
/* 1062 */     if (Trace.isOn) {
/* 1063 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "setRequestMQMD(MQMessage)", new Object[] { message });
/*      */     }
/*      */     
/* 1066 */     if (this.qmanager_level < 500) {
/*      */       
/*      */       try {
/* 1069 */         message.setVersion(1);
/*      */       }
/* 1071 */       catch (Exception e) {
/* 1072 */         if (Trace.isOn) {
/* 1073 */           Trace.catchBlock(this, "com.ibm.mq.headers.pcf.PCFAgent", "setRequestMQMD(MQMessage)", e);
/*      */         }
/*      */         
/* 1076 */         MQDataException traceRet1 = MQDataException.getMQDataException(e);
/* 1077 */         if (Trace.isOn) {
/* 1078 */           Trace.throwing(this, "com.ibm.mq.headers.pcf.PCFAgent", "setRequestMQMD(MQMessage)", (Throwable)traceRet1);
/*      */         }
/*      */         
/* 1081 */         throw traceRet1;
/*      */       } 
/*      */     }
/*      */     
/* 1085 */     message.messageType = 1;
/* 1086 */     message.expiry = this.expiryTime;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1091 */     PropertyStore.register(ENABLEPCFRESPONSEEXPIRY, false);
/* 1092 */     boolean enablePCFResponseExpiry = PropertyStore.getBooleanPropertyObject(ENABLEPCFRESPONSEEXPIRY).booleanValue();
/* 1093 */     if (enablePCFResponseExpiry) {
/* 1094 */       message.report = 16448;
/*      */     } else {
/* 1096 */       message.report = 64;
/*      */     } 
/*      */     
/* 1099 */     message.feedback = 0;
/* 1100 */     message.format = "MQADMIN ";
/* 1101 */     message.encoding = this.encoding;
/* 1102 */     message.characterSet = this.qmanager_ccsid;
/* 1103 */     message.replyToQueueName = this.replyQueueName;
/* 1104 */     message.replyToQueueManagerName = "";
/* 1105 */     message.persistence = 0;
/*      */     
/* 1107 */     if (Trace.isOn) {
/* 1108 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "setRequestMQMD(MQMessage)", message);
/*      */     }
/* 1110 */     return message;
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
/*      */   public void setWaitInterval(int seconds) {
/* 1140 */     if (Trace.isOn) {
/* 1141 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFAgent", "setWaitInterval(int)", "setter", 
/* 1142 */           Integer.valueOf(seconds));
/*      */     }
/* 1144 */     setWaitInterval(seconds, seconds);
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
/*      */   public void setWaitInterval(int wait, int expiry) {
/* 1174 */     if (Trace.isOn)
/* 1175 */       Trace.entry(this, "com.ibm.mq.headers.pcf.PCFAgent", "setWaitInterval(int,int)", new Object[] {
/* 1176 */             Integer.valueOf(wait), Integer.valueOf(expiry)
/*      */           }); 
/* 1178 */     if (wait >= 0) {
/* 1179 */       this.gmo.waitInterval = this.waitInterval = wait * 1000;
/*      */     }
/*      */     
/* 1182 */     if (expiry >= 0) {
/* 1183 */       this.expiryTime = expiry * 10;
/*      */     }
/* 1185 */     if (Trace.isOn) {
/* 1186 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "setWaitInterval(int,int)");
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
/*      */   public int getWaitInterval() {
/* 1198 */     int traceRet1 = this.waitInterval / 1000;
/* 1199 */     if (Trace.isOn) {
/* 1200 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFAgent", "getWaitInterval()", "getter", 
/* 1201 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1203 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExpiry() {
/* 1213 */     int traceRet1 = this.expiryTime / 10;
/* 1214 */     if (Trace.isOn) {
/* 1215 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFAgent", "getExpiry()", "getter", 
/* 1216 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1218 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized String getQManagerName() {
/* 1228 */     if (Trace.isOn) {
/* 1229 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFAgent", "getQManagerName()", "getter", this.qmanager_name);
/*      */     }
/*      */     
/* 1232 */     return this.qmanager_name;
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
/*      */   public String getReplyQueueName() {
/* 1244 */     if (Trace.isOn) {
/* 1245 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFAgent", "getReplyQueueName()", "getter", this.replyQueueName);
/*      */     }
/*      */     
/* 1248 */     return this.replyQueueName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEncoding(int encoding) {
/* 1259 */     if (Trace.isOn) {
/* 1260 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFAgent", "setEncoding(int)", "setter", 
/* 1261 */           Integer.valueOf(encoding));
/*      */     }
/* 1263 */     this.encoding = encoding;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCharacterSet(int ccsid) {
/* 1274 */     if (Trace.isOn) {
/* 1275 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFAgent", "setCharacterSet(int)", "setter", 
/* 1276 */           Integer.valueOf(ccsid));
/*      */     }
/* 1278 */     this.defaultCharacterSet = ccsid;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized int getPlatform() {
/* 1289 */     if (Trace.isOn) {
/* 1290 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFAgent", "getPlatform()", "getter", 
/* 1291 */           Integer.valueOf(this.qmanager_platform));
/*      */     }
/* 1293 */     return this.qmanager_platform;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCommandLevel() {
/* 1303 */     if (Trace.isOn) {
/* 1304 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFAgent", "getCommandLevel()", "getter", 
/* 1305 */           Integer.valueOf(this.qmanager_level));
/*      */     }
/* 1307 */     return this.qmanager_level;
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
/*      */   public String getReplyQueuePrefix() {
/* 1319 */     if (Trace.isOn) {
/* 1320 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFAgent", "getReplyQueuePrefix()", "getter", this.replyQueuePrefix);
/*      */     }
/*      */     
/* 1323 */     return this.replyQueuePrefix;
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
/*      */   public void setReplyQueuePrefix(String prefixP) {
/* 1350 */     if (Trace.isOn) {
/* 1351 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFAgent", "setReplyQueuePrefix(String)", "setter", prefixP);
/*      */     }
/*      */     
/* 1354 */     String prefix = prefixP;
/* 1355 */     if (prefix == null || prefix.length() == 0) {
/* 1356 */       this.replyQueuePrefix = "";
/*      */     } else {
/*      */       
/* 1359 */       if (prefix.length() > 32) {
/* 1360 */         prefix = prefix.substring(0, 32);
/*      */       }
/* 1362 */       if (prefix.indexOf("*") != prefix.length() - 1) {
/* 1363 */         prefix = prefix.concat("*");
/*      */       }
/* 1365 */       this.replyQueuePrefix = prefix;
/*      */     } 
/* 1367 */     if (Trace.isOn) {
/* 1368 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFAgent", "setReplyQueuePrefix(String)", "setting prefix to:", this.replyQueuePrefix);
/*      */       
/* 1370 */       Trace.exit(this, "com.ibm.mq.headers.pcf.PCFAgent", "setReplyQueuePrefix(String)");
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
/*      */   public String getModelQueueName() {
/* 1382 */     if (Trace.isOn) {
/* 1383 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFAgent", "getModelQueueName()", "getter", this.modelQueueName);
/*      */     }
/*      */     
/* 1386 */     return this.modelQueueName;
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
/*      */   public void setModelQueueName(String name) {
/* 1400 */     if (Trace.isOn) {
/* 1401 */       Trace.data(this, "com.ibm.mq.headers.pcf.PCFAgent", "setModelQueueName(String)", "setter", name);
/*      */     }
/*      */     
/* 1404 */     if (name == null) {
/* 1405 */       this.modelQueueName = "SYSTEM.DEFAULT.MODEL.QUEUE";
/*      */     } else {
/* 1407 */       this.modelQueueName = name;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\PCFAgent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */