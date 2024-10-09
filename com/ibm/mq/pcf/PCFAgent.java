/*      */ package com.ibm.mq.pcf;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.MQGetMessageOptions;
/*      */ import com.ibm.mq.MQMessage;
/*      */ import com.ibm.mq.MQPutMessageOptions;
/*      */ import com.ibm.mq.MQQueue;
/*      */ import com.ibm.mq.MQQueueManager;
/*      */ import com.ibm.mq.internal.MQCommonServices;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
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
/*      */ @Deprecated
/*      */ public class PCFAgent
/*      */   extends JmqiObject
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFAgent.java";
/*      */   static final String copyright = "Copyright (c) IBM Corp. 1998, 2004   All rights reserved.";
/*      */   static final String DEFAULT_MODEL_QUEUE_NAME = "SYSTEM.DEFAULT.MODEL.QUEUE";
/*      */   static final String DEFAULT_HOST_NAME = "localhost";
/*      */   static final String DEFAULT_CHANNEL_NAME = "SYSTEM.DEF.SVRCONN";
/*      */   public static final int MAXIMUM_ALLOWED_PREFIX_LENGTH = 32;
/*      */   
/*      */   static {
/*   82 */     if (Trace.isOn) {
/*   83 */       Trace.data("com.ibm.mq.pcf.PCFAgent", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/PCFAgent.java");
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
/*      */   private boolean ccsid_specified = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  134 */   private static String ENABLEPCFRESPONSEEXPIRY = "com.ibm.mq.pcf.enablePCFResponseExpiry";
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
/*      */   public static JmqiEnvironment getJmqiEnv() {
/*  148 */     if (Trace.isOn) {
/*  149 */       Trace.data("com.ibm.mq.pcf.PCFAgent", "getJmqiEnv()", "getter", MQCommonServices.jmqiEnv);
/*      */     }
/*  151 */     return MQCommonServices.jmqiEnv;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PCFAgent() {
/*  159 */     super(getJmqiEnv());
/*  160 */     if (Trace.isOn) {
/*  161 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "<init>()");
/*      */     }
/*  163 */     this.pmo.options = 128;
/*  164 */     this.gmo.options = 24577;
/*  165 */     this.gmo.waitInterval = this.waitInterval;
/*  166 */     if (Trace.isOn) {
/*  167 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "<init>()");
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
/*      */   public PCFAgent(MQQueueManager qmanager) throws MQException {
/*  180 */     this();
/*  181 */     if (Trace.isOn) {
/*  182 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "<init>(MQQueueManager)", new Object[] { qmanager });
/*      */     }
/*      */ 
/*      */     
/*  186 */     connect(qmanager);
/*  187 */     if (Trace.isOn) {
/*  188 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "<init>(MQQueueManager)");
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
/*      */   public PCFAgent(String host, int port, String channel) throws MQException {
/*  203 */     this();
/*  204 */     if (Trace.isOn) {
/*  205 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "<init>(String,int,String)", new Object[] { host, 
/*  206 */             Integer.valueOf(port), channel });
/*      */     }
/*      */     
/*  209 */     connect(host, port, channel);
/*  210 */     if (Trace.isOn) {
/*  211 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "<init>(String,int,String)");
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
/*      */   public PCFAgent(String qmanager) throws MQException {
/*  224 */     this();
/*  225 */     if (Trace.isOn) {
/*  226 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "<init>(String)", new Object[] { qmanager });
/*      */     }
/*      */     
/*  229 */     connect(qmanager);
/*  230 */     if (Trace.isOn) {
/*  231 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "<init>(String)");
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
/*      */   public synchronized void connect(MQQueueManager qmanagerP) throws MQException {
/*  244 */     if (Trace.isOn) {
/*  245 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "connect(MQQueueManager)", new Object[] { qmanagerP });
/*      */     }
/*      */     
/*  248 */     open(qmanagerP, true);
/*  249 */     if (Trace.isOn) {
/*  250 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "connect(MQQueueManager)");
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
/*      */   public synchronized void connect(MQQueueManager qmanagerP, String targetQueue, String targetQmanager) throws MQException {
/*  265 */     if (Trace.isOn) {
/*  266 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "connect(MQQueueManager,String,String)", new Object[] { qmanagerP, targetQueue, targetQmanager });
/*      */     }
/*      */     
/*  269 */     open(qmanagerP, targetQueue, targetQmanager, true);
/*  270 */     if (Trace.isOn) {
/*  271 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "connect(MQQueueManager,String,String)");
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
/*      */   public synchronized void connect(String hostP, int port, String channelP) throws MQException {
/*  286 */     if (Trace.isOn) {
/*  287 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "connect(String,int,String)", new Object[] { hostP, 
/*  288 */             Integer.valueOf(port), channelP });
/*      */     }
/*      */ 
/*      */     
/*  292 */     Hashtable<String, Object> props = new Hashtable<>();
/*  293 */     String host = hostP;
/*  294 */     String channel = channelP;
/*      */     
/*  296 */     props.put("port", Integer.valueOf(port));
/*  297 */     if (host == null || host.trim().length() == 0) {
/*  298 */       host = "localhost";
/*      */     }
/*  300 */     props.put("hostname", host);
/*      */     
/*  302 */     if (channel == null || channel.trim().length() == 0) {
/*  303 */       channel = "SYSTEM.DEF.SVRCONN";
/*      */     }
/*  305 */     props.put("channel", channel);
/*  306 */     MQQueueManager queueManager = null;
/*      */     
/*  308 */     queueManager = new MQQueueManager("", props);
/*      */     
/*  310 */     open(queueManager, false);
/*  311 */     if (Trace.isOn) {
/*  312 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "connect(String,int,String)");
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
/*      */   public synchronized void connect(String hostP, int port, String channelP, String targetQueue, String targetQmanager) throws MQException {
/*  330 */     if (Trace.isOn) {
/*  331 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "connect(String,int,String,String,String)", new Object[] { hostP, 
/*  332 */             Integer.valueOf(port), channelP, targetQueue, targetQmanager });
/*      */     }
/*      */ 
/*      */     
/*  336 */     Hashtable<String, Object> props = new Hashtable<>();
/*  337 */     String host = hostP;
/*  338 */     String channel = channelP;
/*      */     
/*  340 */     props.put("port", Integer.valueOf(port));
/*  341 */     if (host == null || host.trim().length() == 0) {
/*  342 */       host = "localhost";
/*      */     }
/*  344 */     props.put("hostname", host);
/*      */     
/*  346 */     if (channel == null || channel.trim().length() == 0) {
/*  347 */       channel = "SYSTEM.DEF.SVRCONN";
/*      */     }
/*  349 */     props.put("channel", channel);
/*      */     
/*  351 */     MQQueueManager queueManager = null;
/*      */     
/*  353 */     queueManager = new MQQueueManager("", props);
/*      */     
/*  355 */     open(queueManager, targetQueue, targetQmanager, false);
/*      */     
/*  357 */     if (Trace.isOn) {
/*  358 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "connect(String,int,String,String,String)");
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
/*      */   public synchronized void connect(String qmanagerP) throws MQException {
/*  371 */     if (Trace.isOn) {
/*  372 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "connect(String)", new Object[] { qmanagerP });
/*      */     }
/*      */ 
/*      */     
/*  376 */     Hashtable<String, Object> props = new Hashtable<>();
/*  377 */     props.put("port", Integer.valueOf(1414));
/*  378 */     props.put("hostname", "");
/*  379 */     props.put("channel", "");
/*      */     
/*  381 */     MQQueueManager queueManager = null;
/*      */     
/*  383 */     queueManager = new MQQueueManager(qmanagerP, props);
/*      */     
/*  385 */     open(queueManager, false);
/*      */     
/*  387 */     if (Trace.isOn) {
/*  388 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "connect(String)");
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
/*      */   public synchronized void connect(String qmanagerP, String targetQueue, String targetQmanager) throws MQException {
/*  404 */     if (Trace.isOn) {
/*  405 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "connect(String,String,String)", new Object[] { qmanagerP, targetQueue, targetQmanager });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  410 */     Hashtable<String, Object> props = new Hashtable<>();
/*  411 */     props.put("port", Integer.valueOf(1414));
/*  412 */     props.put("hostname", "");
/*  413 */     props.put("channel", "");
/*      */     
/*  415 */     MQQueueManager queueManager = null;
/*      */     
/*  417 */     queueManager = new MQQueueManager(qmanagerP, props);
/*      */     
/*  419 */     open(queueManager, targetQueue, targetQmanager, false);
/*      */     
/*  421 */     if (Trace.isOn) {
/*  422 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "connect(String,String,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected synchronized void open(MQQueueManager qmanagerP, boolean external) throws MQException {
/*  428 */     if (Trace.isOn) {
/*  429 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "open(MQQueueManager,boolean)", new Object[] { qmanagerP, 
/*  430 */             Boolean.valueOf(external) });
/*      */     }
/*  432 */     open(qmanagerP, null, null, external);
/*  433 */     if (Trace.isOn) {
/*  434 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "open(MQQueueManager,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected synchronized void open(MQQueueManager qmanagerP, String targetQueueP, String targetQmanager, boolean external) throws MQException {
/*  440 */     if (Trace.isOn) {
/*  441 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", new Object[] { qmanagerP, targetQueueP, targetQmanager, 
/*  442 */             Boolean.valueOf(external) });
/*      */     }
/*  444 */     String targetQueue = targetQueueP;
/*      */     try {
/*  446 */       disconnect();
/*      */     
/*      */     }
/*  449 */     catch (MQException mqe) {
/*  450 */       if (Trace.isOn) {
/*  451 */         Trace.catchBlock(this, "com.ibm.mq.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)mqe, 1);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  457 */     if (targetQueue == null || targetQueue.length() == 0) {
/*      */       try {
/*  459 */         targetQueue = qmanagerP.getCommandInputQueueName();
/*  460 */       } catch (MQException e) {
/*  461 */         if (Trace.isOn) {
/*  462 */           Trace.catchBlock(this, "com.ibm.mq.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)e, 3);
/*      */         }
/*  464 */         if (!external)
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
/*  476 */           disconnectInternalQueueManagerAfterFailure(qmanagerP);
/*      */         }
/*      */ 
/*      */         
/*  480 */         if (Trace.isOn) {
/*  481 */           Trace.throwing(this, "com.ibm.mq.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)e, 2);
/*      */         }
/*  483 */         throw e;
/*      */       } 
/*      */     }
/*      */     
/*  487 */     String prefix = this.replyQueuePrefix;
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  492 */       this.adminQueue = qmanagerP.accessQueue(targetQueue, 8240, targetQmanager, "", "mqm");
/*      */     }
/*  494 */     catch (MQException e) {
/*  495 */       if (Trace.isOn) {
/*  496 */         Trace.catchBlock(this, "com.ibm.mq.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)e, 2);
/*      */       }
/*      */ 
/*      */       
/*  500 */       if (e.getReason() == 2045) {
/*      */ 
/*      */         
/*      */         try {
/*  504 */           this.adminQueue = qmanagerP.accessQueue(targetQueue, 8208, targetQmanager, "", "mqm");
/*  505 */         } catch (MQException ex) {
/*  506 */           if (Trace.isOn) {
/*  507 */             Trace.catchBlock(this, "com.ibm.mq.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)ex, 6);
/*      */           }
/*  509 */           if (!external)
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
/*  521 */             disconnectInternalQueueManagerAfterFailure(qmanagerP);
/*      */           }
/*      */ 
/*      */           
/*  525 */           if (Trace.isOn) {
/*  526 */             Trace.throwing(this, "com.ibm.mq.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)ex, 5);
/*      */           }
/*      */         } 
/*      */       } else {
/*      */         
/*  531 */         if (!external)
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
/*  543 */           disconnectInternalQueueManagerAfterFailure(qmanagerP);
/*      */         }
/*      */ 
/*      */         
/*  547 */         if (Trace.isOn) {
/*  548 */           Trace.throwing(this, "com.ibm.mq.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)e);
/*      */         }
/*      */         
/*  551 */         throw e;
/*      */       } 
/*      */     } 
/*      */     
/*      */     try {
/*  556 */       this.replyQueue = qmanagerP.accessQueue(this.modelQueueName, 8196, "", prefix, "mqm");
/*  557 */     } catch (MQException e) {
/*  558 */       if (Trace.isOn) {
/*  559 */         Trace.catchBlock(this, "com.ibm.mq.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)e, 4);
/*      */       }
/*  561 */       if (!external)
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
/*  573 */         disconnectInternalQueueManagerAfterFailure(qmanagerP);
/*      */       }
/*      */ 
/*      */       
/*  577 */       if (Trace.isOn) {
/*  578 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)e, 3);
/*      */       }
/*  580 */       throw e;
/*      */     } 
/*  582 */     this.replyQueueName = this.replyQueue.name;
/*      */ 
/*      */     
/*  585 */     this.replyQueue.closeOptions = 2;
/*      */ 
/*      */     
/*      */     try {
/*  589 */       getBasicQmgrInfo(qmanagerP, true);
/*  590 */     } catch (MQException e) {
/*  591 */       if (Trace.isOn) {
/*  592 */         Trace.catchBlock(this, "com.ibm.mq.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)e, 5);
/*      */       }
/*      */       
/*  595 */       if (!external)
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
/*  607 */         disconnectInternalQueueManagerAfterFailure(qmanagerP);
/*      */       }
/*      */       
/*  610 */       if (Trace.isOn) {
/*  611 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)", (Throwable)e, 4);
/*      */       }
/*      */       
/*  614 */       throw e;
/*      */     } 
/*      */     
/*  617 */     if (!external)
/*      */     {
/*      */ 
/*      */       
/*  621 */       this.qmanager = qmanagerP;
/*      */     }
/*      */     
/*  624 */     if (Trace.isOn) {
/*  625 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "open(MQQueueManager,String,String,boolean)");
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
/*      */   private void disconnectInternalQueueManagerAfterFailure(MQQueueManager qm) {
/*  655 */     if (Trace.isOn) {
/*  656 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "disconnectInternalQueueManagerAfterFailure(MQQueueManager)", new Object[] { qm });
/*      */     }
/*      */     
/*      */     try {
/*  660 */       qm.disconnect();
/*  661 */     } catch (MQException mqe) {
/*      */       
/*  663 */       if (Trace.isOn) {
/*  664 */         Trace.catchBlock(this, "com.ibm.mq.pcf.PCFAgent", "disconnectInternalQueueManagerAfterFailure(MQQueueManager)", (Throwable)mqe);
/*      */       }
/*      */     } 
/*      */     
/*  668 */     if (Trace.isOn) {
/*  669 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "disconnectInternalQueueManagerAfterFailure(MQQueueManager)");
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
/*      */   private void getBasicQmgrInfo(MQQueueManager qmgr, boolean tryBacklevel) throws MQException {
/*  690 */     if (Trace.isOn) {
/*  691 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "getBasicQmgrInfo(MQQueueManager,boolean)", new Object[] { qmgr, 
/*  692 */             Boolean.valueOf(tryBacklevel) });
/*      */     }
/*      */     
/*  695 */     this.qmanager_level = qmgr.getCommandLevel();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  700 */     int type = 16;
/*  701 */     int version = 3;
/*      */     
/*  703 */     if (tryBacklevel) {
/*  704 */       type = 1;
/*  705 */       version = 1;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/*  710 */       MQMessage message = setRequestMQMD(new MQMessage());
/*  711 */       MQCFH.write(message, 2, 1, type, version);
/*  712 */       MQCFIL.write(message, 1001, new int[] { 32, 2015, 2, 31 });
/*      */       
/*  714 */       this.adminQueue.put(message, this.pmo);
/*      */ 
/*      */       
/*  717 */       message.messageId = null;
/*  718 */       message.encoding = this.encoding;
/*  719 */       message.characterSet = this.defaultCharacterSet;
/*  720 */       this.replyQueue.get(message, this.gmo);
/*      */ 
/*      */       
/*  723 */       MQCFH cfh = new MQCFH(message);
/*      */       
/*  725 */       if (cfh.reason == 0) {
/*  726 */         int parameterCount = cfh.parameterCount;
/*  727 */         while (parameterCount-- > 0) {
/*  728 */           String temp; PCFParameter p = PCFParameter.nextParameter(message);
/*      */           
/*  730 */           switch (p.getParameter()) {
/*      */             case 32:
/*  732 */               this.qmanager_platform = ((MQCFIN)p).value;
/*      */             
/*      */             case 2015:
/*  735 */               temp = ((MQCFST)p).string;
/*  736 */               if (temp != null) {
/*  737 */                 this.qmanager_name = ((MQCFST)p).string.trim(); continue;
/*      */               } 
/*  739 */               this.qmanager_name = null;
/*      */ 
/*      */             
/*      */             case 2:
/*  743 */               this.qmanager_ccsid = ((MQCFIN)p).value;
/*      */ 
/*      */             
/*      */             case 31:
/*  747 */               this.qmanager_level = ((MQCFIN)p).value;
/*      */           } 
/*      */ 
/*      */ 
/*      */         
/*      */         } 
/*  753 */       } else if ((cfh.reason == 3001 || cfh.reason == 3003) && tryBacklevel == true) {
/*      */ 
/*      */         
/*  756 */         getBasicQmgrInfo(qmgr, false);
/*      */       } else {
/*  758 */         MQException traceRet1 = new MQException(cfh.compCode, cfh.reason, this);
/*  759 */         if (Trace.isOn) {
/*  760 */           Trace.throwing(this, "com.ibm.mq.pcf.PCFAgent", "getBasicQmgrInfo(MQQueueManager,boolean)", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/*  763 */         throw traceRet1;
/*      */       }
/*      */     
/*  766 */     } catch (IOException e) {
/*  767 */       if (Trace.isOn) {
/*  768 */         Trace.catchBlock(this, "com.ibm.mq.pcf.PCFAgent", "getBasicQmgrInfo(MQQueueManager,boolean)", e);
/*      */       }
/*      */       
/*  771 */       MQException returnException = new MQException(2, 2195, this);
/*  772 */       returnException.initCause(e);
/*  773 */       if (Trace.isOn) {
/*  774 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFAgent", "getBasicQmgrInfo(MQQueueManager,boolean)", (Throwable)returnException, 2);
/*      */       }
/*      */       
/*  777 */       throw returnException;
/*      */     } 
/*      */     
/*  780 */     if (Trace.isOn) {
/*  781 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "getBasicQmgrInfo(MQQueueManager,boolean)");
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
/*      */   public synchronized void disconnect() throws MQException {
/*  798 */     if (Trace.isOn) {
/*  799 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "disconnect()");
/*      */     }
/*      */     try {
/*  802 */       if (this.adminQueue != null) {
/*  803 */         this.adminQueue.close();
/*      */       }
/*  805 */       if (this.replyQueue != null) {
/*  806 */         this.replyQueue.close();
/*      */       }
/*  808 */       if (this.qmanager != null) {
/*  809 */         this.qmanager.disconnect();
/*      */       }
/*      */     } finally {
/*      */       
/*  813 */       if (Trace.isOn) {
/*  814 */         Trace.finallyBlock(this, "com.ibm.mq.pcf.PCFAgent", "disconnect()");
/*      */       }
/*  816 */       this.adminQueue = null;
/*  817 */       this.replyQueue = null;
/*  818 */       this.replyQueueName = null;
/*  819 */       this.qmanager = null;
/*  820 */       this.qmanager_name = null;
/*  821 */       this.qmanager_platform = 0;
/*      */     } 
/*  823 */     if (Trace.isOn) {
/*  824 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "disconnect()");
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
/*      */   public synchronized MQMessage[] send(int command, PCFParameter[] parameters) throws MQException, IOException {
/*      */     PCFAgentResponseTracker tracker;
/*  843 */     if (Trace.isOn)
/*  844 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "send(int,PCFParameter [ ])", new Object[] {
/*  845 */             Integer.valueOf(command), parameters
/*      */           }); 
/*  847 */     if (this.adminQueue == null) {
/*  848 */       MQException traceRet1 = new MQException(2, 6124, this);
/*  849 */       if (Trace.isOn) {
/*  850 */         Trace.throwing(this, "com.ibm.mq.pcf.PCFAgent", "send(int,PCFParameter [ ])", (Throwable)traceRet1);
/*      */       }
/*  852 */       throw traceRet1;
/*      */     } 
/*      */     
/*  855 */     MQMessage message = setRequestMQMD(new MQMessage());
/*      */ 
/*      */     
/*  858 */     int count = (parameters == null) ? 0 : parameters.length;
/*      */     
/*  860 */     if (this.qmanager_platform == 1) {
/*      */ 
/*      */       
/*  863 */       tracker = new PCFAgentResponseTracker390();
/*  864 */       MQCFH.write(message, command, count, 16, 3);
/*      */     }
/*      */     else {
/*      */       
/*  868 */       tracker = new PCFAgentResponseTrackerNon390();
/*      */       
/*  870 */       int version = 1;
/*      */       
/*  872 */       for (int j = 0; j < count && version < 3; j++) {
/*  873 */         version = Math.max(version, parameters[j].getHeaderVersion());
/*      */       }
/*      */       
/*  876 */       MQCFH.write(message, command, count, 1, version);
/*      */     } 
/*      */     
/*  879 */     for (int i = 0; i < count; i++) {
/*  880 */       parameters[i].write((DataOutput)message);
/*      */     }
/*      */     
/*  883 */     this.adminQueue.put(message, this.pmo);
/*      */     
/*  885 */     byte[] correlationId = message.correlationId;
/*  886 */     Vector<MQMessage> v = new Vector<>();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/*  892 */       message = new MQMessage();
/*  893 */       message.correlationId = correlationId;
/*  894 */       message.encoding = this.encoding;
/*  895 */       message.characterSet = this.defaultCharacterSet;
/*      */       
/*  897 */       this.replyQueue.get(message, this.gmo);
/*  898 */       v.addElement(message);
/*      */     }
/*  900 */     while (!tracker.isLast(message));
/*      */     
/*  902 */     MQMessage[] responses = new MQMessage[v.size()];
/*      */     
/*  904 */     v.copyInto((Object[])responses);
/*      */     
/*  906 */     if (Trace.isOn) {
/*  907 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "send(int,PCFParameter [ ])", responses);
/*      */     }
/*  909 */     return responses;
/*      */   }
/*      */   
/*      */   protected MQMessage setRequestMQMD(MQMessage message) throws MQException {
/*  913 */     if (Trace.isOn) {
/*  914 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "setRequestMQMD(MQMessage)", new Object[] { message });
/*      */     }
/*      */     
/*  917 */     if (this.qmanager_level < 500)
/*      */     {
/*      */       
/*  920 */       message.setVersion(1);
/*      */     }
/*      */     
/*  923 */     message.messageType = 1;
/*  924 */     message.expiry = this.expiryTime;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  929 */     PropertyStore.register(ENABLEPCFRESPONSEEXPIRY, false);
/*  930 */     boolean enablePCFResponseExpiry = PropertyStore.getBooleanPropertyObject(ENABLEPCFRESPONSEEXPIRY).booleanValue();
/*  931 */     if (enablePCFResponseExpiry) {
/*  932 */       message.report = 16448;
/*      */     } else {
/*  934 */       message.report = 64;
/*      */     } 
/*      */     
/*  937 */     message.feedback = 0;
/*  938 */     message.format = "MQADMIN ";
/*  939 */     message.encoding = this.encoding;
/*  940 */     message.characterSet = this.ccsid_specified ? this.defaultCharacterSet : this.qmanager_ccsid;
/*  941 */     message.replyToQueueName = this.replyQueueName;
/*  942 */     message.replyToQueueManagerName = "";
/*  943 */     message.persistence = 0;
/*      */     
/*  945 */     if (Trace.isOn) {
/*  946 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "setRequestMQMD(MQMessage)", message);
/*      */     }
/*  948 */     return message;
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
/*  978 */     if (Trace.isOn) {
/*  979 */       Trace.data(this, "com.ibm.mq.pcf.PCFAgent", "setWaitInterval(int)", "setter", 
/*  980 */           Integer.valueOf(seconds));
/*      */     }
/*  982 */     setWaitInterval(seconds, seconds);
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
/* 1012 */     if (Trace.isOn)
/* 1013 */       Trace.entry(this, "com.ibm.mq.pcf.PCFAgent", "setWaitInterval(int,int)", new Object[] {
/* 1014 */             Integer.valueOf(wait), Integer.valueOf(expiry)
/*      */           }); 
/* 1016 */     if (wait >= 0) {
/* 1017 */       this.gmo.waitInterval = this.waitInterval = wait * 1000;
/*      */     }
/*      */     
/* 1020 */     if (expiry >= 0) {
/* 1021 */       this.expiryTime = expiry * 10;
/*      */     }
/* 1023 */     if (Trace.isOn) {
/* 1024 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "setWaitInterval(int,int)");
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
/* 1036 */     int traceRet1 = this.waitInterval / 1000;
/* 1037 */     if (Trace.isOn) {
/* 1038 */       Trace.data(this, "com.ibm.mq.pcf.PCFAgent", "getWaitInterval()", "getter", 
/* 1039 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1041 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExpiry() {
/* 1051 */     int traceRet1 = this.expiryTime / 10;
/* 1052 */     if (Trace.isOn) {
/* 1053 */       Trace.data(this, "com.ibm.mq.pcf.PCFAgent", "getExpiry()", "getter", 
/* 1054 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1056 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized String getQManagerName() {
/* 1066 */     if (Trace.isOn) {
/* 1067 */       Trace.data(this, "com.ibm.mq.pcf.PCFAgent", "getQManagerName()", "getter", this.qmanager_name);
/*      */     }
/* 1069 */     return this.qmanager_name;
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
/* 1081 */     if (Trace.isOn) {
/* 1082 */       Trace.data(this, "com.ibm.mq.pcf.PCFAgent", "getReplyQueueName()", "getter", this.replyQueueName);
/*      */     }
/*      */     
/* 1085 */     return this.replyQueueName;
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
/* 1096 */     if (Trace.isOn) {
/* 1097 */       Trace.data(this, "com.ibm.mq.pcf.PCFAgent", "setEncoding(int)", "setter", 
/* 1098 */           Integer.valueOf(encoding));
/*      */     }
/* 1100 */     this.encoding = encoding;
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
/* 1111 */     if (Trace.isOn) {
/* 1112 */       Trace.data(this, "com.ibm.mq.pcf.PCFAgent", "setCharacterSet(int)", "setter", 
/* 1113 */           Integer.valueOf(ccsid));
/*      */     }
/* 1115 */     this.ccsid_specified = true;
/* 1116 */     this.defaultCharacterSet = ccsid;
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
/* 1127 */     if (Trace.isOn) {
/* 1128 */       Trace.data(this, "com.ibm.mq.pcf.PCFAgent", "getPlatform()", "getter", 
/* 1129 */           Integer.valueOf(this.qmanager_platform));
/*      */     }
/* 1131 */     return this.qmanager_platform;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCommandLevel() {
/* 1141 */     if (Trace.isOn) {
/* 1142 */       Trace.data(this, "com.ibm.mq.pcf.PCFAgent", "getCommandLevel()", "getter", 
/* 1143 */           Integer.valueOf(this.qmanager_level));
/*      */     }
/* 1145 */     return this.qmanager_level;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getReplyQueuePrefix() {
/* 1156 */     if (Trace.isOn) {
/* 1157 */       Trace.data(this, "com.ibm.mq.pcf.PCFAgent", "getReplyQueuePrefix()", "getter", this.replyQueuePrefix);
/*      */     }
/*      */     
/* 1160 */     return this.replyQueuePrefix;
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
/* 1187 */     if (Trace.isOn) {
/* 1188 */       Trace.data(this, "com.ibm.mq.pcf.PCFAgent", "setReplyQueuePrefix(String)", "setter", prefixP);
/*      */     }
/*      */     
/* 1191 */     String prefix = prefixP;
/* 1192 */     if (prefix == null || prefix.length() == 0) {
/* 1193 */       this.replyQueuePrefix = "";
/*      */     } else {
/*      */       
/* 1196 */       if (prefix.length() > 32) {
/* 1197 */         prefix = prefix.substring(0, 32);
/*      */       }
/* 1199 */       if (prefix.indexOf("*") != prefix.length() - 1) {
/* 1200 */         prefix = prefix.concat("*");
/*      */       }
/* 1202 */       this.replyQueuePrefix = prefix;
/*      */     } 
/* 1204 */     if (Trace.isOn) {
/* 1205 */       Trace.data(this, "com.ibm.mq.pcf.PCFAgent", "setReplyQueuePrefix(String)", "setting prefix to:", this.replyQueuePrefix);
/*      */       
/* 1207 */       Trace.exit(this, "com.ibm.mq.pcf.PCFAgent", "setReplyQueuePrefix(String)");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getModelQueueName() {
/* 1217 */     if (Trace.isOn) {
/* 1218 */       Trace.data(this, "com.ibm.mq.pcf.PCFAgent", "getModelQueueName()", "getter", this.modelQueueName);
/*      */     }
/*      */     
/* 1221 */     return this.modelQueueName;
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
/* 1235 */     if (Trace.isOn) {
/* 1236 */       Trace.data(this, "com.ibm.mq.pcf.PCFAgent", "setModelQueueName(String)", "setter", name);
/*      */     }
/* 1238 */     if (name == null) {
/* 1239 */       this.modelQueueName = "SYSTEM.DEFAULT.MODEL.QUEUE";
/*      */     } else {
/* 1241 */       this.modelQueueName = name;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\PCFAgent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */