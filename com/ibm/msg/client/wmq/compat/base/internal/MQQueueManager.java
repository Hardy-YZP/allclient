/*      */ package com.ibm.msg.client.wmq.compat.base.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.wmq.common.internal.Reason;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
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
/*      */ public class MQQueueManager
/*      */   extends MQManagedObject
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQQueueManager.java";
/*      */   private static final int MQ_Q_NAME_LENGTH = 48;
/*      */   private static final int MQCA_COMMAND_INPUT_Q_NAME = 2003;
/*      */   private static final int MQIA_SYNCPOINT = 30;
/*      */   private static final int MQOT_Q_MGR = 5;
/*      */   private Vector<MQQueue> queues;
/*      */   private MQQueueManagerEventListener qmEventListener;
/*      */   
/*      */   static {
/*   42 */     if (Trace.isOn) {
/*   43 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQQueueManager.java");
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
/*   54 */     if (Trace.isOn) {
/*   55 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "static()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   61 */     MQException.logExclude(Integer.valueOf(0));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   67 */     new MQException(0, 0, null);
/*      */     
/*   69 */     MQException.log = null;
/*   70 */     if (Trace.isOn) {
/*   71 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "static()");
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
/*   89 */   private MQManagedConnectionJ11 mqManCon = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   97 */   private MQQueueManager copy = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  102 */   private MQQueueManager original = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  107 */   private volatile MQException exceptionForDisconnect = null;
/*      */ 
/*      */   
/*      */   private boolean allowErrorEvents = false;
/*      */ 
/*      */   
/*      */   private boolean forSPI = false;
/*      */ 
/*      */   
/*      */   private boolean inheritTx = false;
/*      */ 
/*      */   
/*      */   private boolean asyncCmt = false;
/*      */ 
/*      */   
/*      */   private boolean supportsQAT2 = false;
/*      */   
/*      */   protected volatile boolean connectStatus = false;
/*      */   
/*  126 */   private int commandLevel = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isConnected() {
/*  136 */     if (Trace.isOn) {
/*  137 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "isConnected()", "getter", 
/*      */ 
/*      */           
/*  140 */           Boolean.valueOf(this.connectStatus));
/*      */     }
/*  142 */     return this.connectStatus;
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
/*      */   public String getCommandInputQueueName() throws MQException {
/*  158 */     String traceRet1 = getString(2003, 48);
/*      */     
/*  160 */     if (Trace.isOn) {
/*  161 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "getCommandInputQueueName()", "getter", traceRet1);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  166 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCommandLevel() throws MQException {
/*  177 */     if (this.commandLevel == -1) {
/*  178 */       this.commandLevel = getInt(31);
/*      */     }
/*  180 */     if (Trace.isOn) {
/*  181 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "getCommandLevel()", "getter", 
/*      */ 
/*      */ 
/*      */           
/*  185 */           Integer.valueOf(this.commandLevel));
/*      */     }
/*  187 */     return this.commandLevel;
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
/*      */   public int getSyncpointAvailability() throws MQException {
/*  204 */     int traceRet1 = getInt(30);
/*  205 */     if (Trace.isOn) {
/*  206 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "getSyncpointAvailability()", "getter", 
/*      */ 
/*      */ 
/*      */           
/*  210 */           Integer.valueOf(traceRet1));
/*      */     }
/*  212 */     return traceRet1;
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
/*      */   public MQQueueManager(String queueManagerName) throws MQException {
/*  253 */     if (Trace.isOn) {
/*  254 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "<init>(String)", new Object[] { queueManagerName });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  260 */     initialize(procure(queueManagerName, (Map<String, Object>)null));
/*  261 */     this.allowErrorEvents = true;
/*      */     
/*  263 */     if (Trace.isOn) {
/*  264 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "<init>(String)");
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
/*      */   public MQQueueManager(String queueManagerName, Map<String, Object> mqProperties) throws MQException {
/*  288 */     if (Trace.isOn) {
/*  289 */       if (mqProperties.containsKey("password")) {
/*  290 */         Map<String, Object> propsNotPasswd = new HashMap<>(mqProperties);
/*      */         
/*  292 */         propsNotPasswd.put("password", "********");
/*  293 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "<init>(String,Map<String,Object>)", new Object[] { queueManagerName, propsNotPasswd });
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  299 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "<init>(String,Map<String,Object>)", new Object[] { queueManagerName, mqProperties });
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  307 */     initialize(procure(queueManagerName, mqProperties));
/*  308 */     this.allowErrorEvents = true;
/*      */     
/*  310 */     if (Trace.isOn) {
/*  311 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "<init>(String,Hashtable)");
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
/*      */   MQQueueManager(MQManagedConnectionJ11 mancon) throws MQException {
/*  330 */     if (Trace.isOn) {
/*  331 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "<init>(MQManagedConnectionJ11)", new Object[] { mancon });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  337 */     this.pszName = mancon.getQmgrName();
/*  338 */     this.name = this.pszName;
/*      */     
/*  340 */     this.queues = new Vector<>(5);
/*      */     
/*  342 */     this.qmEventListener = null;
/*      */     
/*  344 */     this.mqManCon = mancon;
/*  345 */     this.osession = mancon.getMQSESSION();
/*  346 */     this.hconn = mancon.getHconn();
/*      */     
/*  348 */     this.connected = this.mqManCon.isConnected();
/*  349 */     this.connectStatus = this.connected;
/*  350 */     this.connectionReference = this;
/*  351 */     this.parentQmgr = this;
/*      */     
/*  353 */     this.resourceOpen = false;
/*      */     
/*  355 */     this.openStatus = false;
/*      */     
/*  357 */     this.forSPI = "SPI_ENABLE".equals(mancon
/*  358 */         .getStringProperty("SPI"));
/*  359 */     this.inheritTx = false;
/*      */     
/*  361 */     if (this.connected) {
/*  362 */       if (Trace.isOn) {
/*  363 */         Trace.traceData(this, "Opening Qmgr for inquire", null);
/*      */       }
/*  365 */       MQOD mqod = new MQOD();
/*  366 */       mqod.ObjectType = 5;
/*      */       
/*  368 */       this.osession.MQOPEN(this.hconn, mqod, 8224, this.phobj, this.completionCode, this.reason);
/*      */ 
/*      */       
/*  371 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/*  372 */         MQException traceRet1 = new MQException(this.completionCode.x, this.reason.x, this);
/*      */         
/*  374 */         if (Trace.isOn) {
/*  375 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "<init>(MQManagedConnectionJ11)", (Throwable)traceRet1, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  380 */         throw traceRet1;
/*      */       } 
/*      */       
/*  383 */       this.resourceOpen = true;
/*      */       
/*  385 */       this.openStatus = true;
/*      */ 
/*      */ 
/*      */       
/*  389 */       this.osession.resolveV2Support(this.hconn, this.phobj.getHobj(), this.completionCode, this.reason);
/*      */       
/*  391 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/*  392 */         MQException traceRet2 = new MQException(this.completionCode.x, this.reason.x, this);
/*      */         
/*  394 */         JmqiException e = MQSESSION.getJmqiEnv().getLastException();
/*  395 */         traceRet2.initCause((Throwable)e);
/*  396 */         if (Trace.isOn) {
/*  397 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "<init>(MQManagedConnectionJ11)", (Throwable)traceRet2, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  402 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */       
/*  406 */       setSupportsQAT2(this.osession.supportsQAT2);
/*      */     } 
/*      */     
/*  409 */     if (Trace.isOn) {
/*  410 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "<init>(MQManagedConnectionJ11)");
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
/*      */   private void initialize(MQQueueManager qmgr) {
/*  433 */     if (Trace.isOn) {
/*  434 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "initialize(MQQueueManager)", new Object[] { qmgr });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  440 */     synchronized (qmgr) {
/*  441 */       this.pszName = qmgr.pszName;
/*  442 */       this.name = qmgr.name;
/*      */       
/*  444 */       this.queues = qmgr.queues;
/*      */       
/*  446 */       this.mqManCon = qmgr.mqManCon;
/*  447 */       this.osession = qmgr.osession;
/*  448 */       this.hconn = qmgr.hconn;
/*  449 */       this.phobj = qmgr.phobj;
/*      */       
/*  451 */       this.connected = qmgr.connected;
/*  452 */       this.connectStatus = qmgr.connectStatus;
/*  453 */       this.resourceOpen = qmgr.resourceOpen;
/*      */       
/*  455 */       this.openStatus = qmgr.openStatus;
/*      */       
/*  457 */       this.forSPI = qmgr.forSPI;
/*  458 */       setSupportsQAT2(qmgr.getSupportsQAT2());
/*      */       
/*  460 */       this.parentQmgr = this;
/*  461 */       this.connectionReference = this;
/*  462 */       this.original = qmgr;
/*  463 */       qmgr.copy = this;
/*      */     } 
/*      */     
/*  466 */     if (Trace.isOn) {
/*  467 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "initialize(MQQueueManager)");
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
/*      */   public void disconnect() throws MQException {
/*  485 */     if (Trace.isOn) {
/*  486 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "disconnect()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  492 */     this.allowErrorEvents = false;
/*  493 */     synchronized (this) {
/*  494 */       if (!this.connected) {
/*  495 */         if (Trace.isOn) {
/*  496 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "disconnect()", 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */ 
/*      */       
/*  505 */       cleanup();
/*      */     } 
/*      */ 
/*      */     
/*  509 */     synchronized (this) {
/*  510 */       if (this.original != null) {
/*  511 */         this.mqManCon.fireConnectionClosedEvent(this.original);
/*      */       } else {
/*  513 */         this.mqManCon.fireConnectionClosedEvent(this);
/*      */       } 
/*  515 */       if (this.exceptionForDisconnect != null) {
/*      */         
/*  517 */         if (Trace.isOn) {
/*  518 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "disconnect()", (Throwable)this.exceptionForDisconnect);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  523 */         throw this.exceptionForDisconnect;
/*      */       } 
/*      */     } 
/*      */     
/*  527 */     if (Trace.isOn) {
/*  528 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "disconnect()", 2);
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
/*      */   public synchronized void commit() throws MQException {
/*  553 */     if (Trace.isOn) {
/*  554 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "commit()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  560 */     if (!this.connected) {
/*  561 */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*      */       
/*  563 */       if (Trace.isOn) {
/*  564 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "commit()", (Throwable)traceRet1, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  569 */       throw traceRet1;
/*      */     } 
/*      */     
/*  572 */     this.osession.MQCMIT(this.hconn, this.completionCode, this.reason);
/*      */     
/*  574 */     if (this.completionCode.x != 0 || this.reason.x != 0) {
/*  575 */       MQException mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*  576 */       errorOccurred(mqe);
/*      */       
/*  578 */       if (Trace.isOn) {
/*  579 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "commit()", (Throwable)mqe, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  584 */       throw mqe;
/*      */     } 
/*      */     
/*  587 */     if (Trace.isOn) {
/*  588 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "commit()");
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
/*      */   public synchronized void backout() throws MQException {
/*  611 */     if (Trace.isOn) {
/*  612 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "backout()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  618 */     if (!this.connected) {
/*  619 */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*      */       
/*  621 */       if (Trace.isOn) {
/*  622 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "backout()", (Throwable)traceRet1, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  627 */       throw traceRet1;
/*      */     } 
/*      */     
/*  630 */     this.osession.MQBACK(this.hconn, this.completionCode, this.reason);
/*      */     
/*  632 */     if (this.completionCode.x != 0 || this.reason.x != 0) {
/*  633 */       MQException mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*  634 */       errorOccurred(mqe);
/*  635 */       if (Trace.isOn) {
/*  636 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "backout()", (Throwable)mqe, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  641 */       throw mqe;
/*      */     } 
/*      */     
/*  644 */     if (Trace.isOn) {
/*  645 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "backout()");
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
/*      */   MQSESSION getSession() {
/*  657 */     if (Trace.isOn) {
/*  658 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "getSession()", "getter", this.osession);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  663 */     return this.osession;
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
/*      */   public synchronized void putMsg2(String qName, String qmName, MQMsg2 msg2, MQPutMessageOptions pmo) throws MQException {
/*  687 */     if (Trace.isOn) {
/*  688 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "putMsg2(String,String,MQMsg2,MQPutMessageOptions)", new Object[] { qName, qmName, msg2, pmo });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  694 */     if (!this.connected) {
/*  695 */       MQException traceRet1 = new MQException(2, 2018, this);
/*      */       
/*  697 */       if (Trace.isOn) {
/*  698 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "putMsg2(String,String,MQMsg2,MQPutMessageOptions)", (Throwable)traceRet1, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  704 */       throw traceRet1;
/*      */     } 
/*      */     
/*  707 */     MQOD od = new MQOD();
/*  708 */     if (qName != null && qName.length() > 0) {
/*  709 */       od.ObjectName = qName;
/*      */     }
/*      */     
/*  712 */     if (qmName != null && qmName.length() > 0) {
/*  713 */       od.ObjectQMgrName = qmName;
/*      */     }
/*      */     
/*  716 */     ByteBuffer msgData = null;
/*  717 */     if (msg2 != null) {
/*  718 */       msgData = msg2.getInternalBuffer();
/*  719 */       this.osession.MQPUT1(this.hconn, od, msg2, pmo, msg2.getMessageDataLength(), msgData, this.completionCode, this.reason);
/*      */     } else {
/*      */       
/*  722 */       this.osession.MQPUT1(this.hconn, od, (MQMsg2)null, pmo, 0, (ByteBuffer)null, this.completionCode, this.reason);
/*      */     } 
/*      */ 
/*      */     
/*  726 */     if (this.completionCode.x != 0 || this.reason.x != 0) {
/*  727 */       MQException mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*  728 */       errorOccurred(mqe);
/*  729 */       if (Trace.isOn) {
/*  730 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "putMsg2(String,String,MQMsg2,MQPutMessageOptions)", (Throwable)mqe, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  736 */       throw mqe;
/*      */     } 
/*  738 */     if (Trace.isOn) {
/*  739 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "putMsg2(String,String,MQMsg2,MQPutMessageOptions)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized MQQueue accessQueue(String queueName, int openOptions, String queueManagerName, String dynamicQueueName, String alternateUserId) throws MQException {
/*  828 */     if (Trace.isOn) {
/*  829 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "accessQueue(String,int,String,String,String)", new Object[] { queueName, 
/*      */ 
/*      */ 
/*      */             
/*  833 */             Integer.valueOf(openOptions), queueManagerName, dynamicQueueName, alternateUserId });
/*      */     }
/*      */ 
/*      */     
/*  837 */     if (!this.connected) {
/*  838 */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*      */       
/*  840 */       if (Trace.isOn) {
/*  841 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "accessQueue(String,int,String,String,String)", (Throwable)traceRet1, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  847 */       throw traceRet1;
/*      */     } 
/*      */     
/*  850 */     if (Trace.isOn) {
/*  851 */       Trace.traceData(this, "inheritTx = " + this.inheritTx, null);
/*  852 */       Trace.traceData(this, "asyncCmt = " + this.asyncCmt, null);
/*      */     } 
/*      */     
/*  855 */     MQOD od = new MQOD();
/*      */     
/*  857 */     if (queueName != null && queueName.length() > 0) {
/*  858 */       od.ObjectName = queueName;
/*      */     }
/*      */     
/*  861 */     if (queueManagerName != null && queueManagerName.length() > 0) {
/*  862 */       od.ObjectQMgrName = queueManagerName;
/*      */     }
/*      */     
/*  865 */     if (dynamicQueueName != null && dynamicQueueName.length() > 0) {
/*  866 */       od.DynamicQName = dynamicQueueName;
/*      */     }
/*      */     
/*  869 */     if (alternateUserId != null && alternateUserId.length() > 0) {
/*  870 */       od.AlternateUserId = alternateUserId;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  876 */     MQQueue q = new MQQueue();
/*  877 */     q.hconn = this.hconn;
/*  878 */     q.connected = this.connected;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  883 */     if (Trace.isOn) {
/*  884 */       Trace.traceData(this, "queue = " + od.ObjectName + "\nqueue manager = " + od.ObjectQMgrName + "\ndynamic queue name = " + od.DynamicQName + "\nalternate user id = " + od.AlternateUserId + "\noptions = " + openOptions, null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  891 */     this.osession.MQOPEN(q.hconn, od, openOptions, q.phobj, this.completionCode, this.reason);
/*      */ 
/*      */     
/*  894 */     if (this.completionCode.x != 0 || this.reason.x != 0) {
/*  895 */       q.resourceOpen = false;
/*  896 */       MQException mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*  897 */       errorOccurred(mqe);
/*  898 */       if (Trace.isOn) {
/*  899 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "accessQueue(String,int,String,String,String)", (Throwable)mqe, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  904 */       throw mqe;
/*      */     } 
/*      */     
/*  907 */     q.resourceOpen = true;
/*  908 */     q.mgr = this;
/*      */     
/*  910 */     this.queues.addElement(q);
/*      */ 
/*      */     
/*  913 */     q.name = od.ObjectName;
/*  914 */     q.openOptions = openOptions;
/*      */ 
/*      */     
/*  917 */     q.openStatus = true;
/*      */     
/*  919 */     q.parentQmgr = this;
/*  920 */     q.connectionReference = this;
/*  921 */     if (alternateUserId != null) {
/*  922 */       q.alternateUserId = alternateUserId;
/*      */     }
/*  924 */     q.mqca_description = 2013;
/*      */     
/*  926 */     if (Trace.isOn) {
/*  927 */       Trace.traceData(this, "Opened queue name = " + q.name, null);
/*      */     }
/*  929 */     if (Trace.isOn) {
/*  930 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "accessQueue(String,int,String,String,String)", q);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  935 */     return q;
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
/*      */   public synchronized MQQueue accessQueue(String queueName, int openOptions) throws MQException {
/*  957 */     if (Trace.isOn) {
/*  958 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "accessQueue(String,int)", new Object[] { queueName, 
/*      */ 
/*      */ 
/*      */             
/*  962 */             Integer.valueOf(openOptions) });
/*      */     }
/*  964 */     MQQueue traceRet1 = accessQueue(queueName, openOptions, (String)null, (String)null, (String)null);
/*      */     
/*  966 */     if (Trace.isOn) {
/*  967 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "accessQueue(String,int)", traceRet1);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  972 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void cleanup() {
/*  983 */     if (Trace.isOn) {
/*  984 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "cleanup()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  990 */     if (this.copy != null) {
/*      */ 
/*      */       
/*  993 */       synchronized (this.copy) {
/*  994 */         if (Trace.isOn) {
/*  995 */           Trace.traceData(this, "Delegating cleanup to " + this.copy, null);
/*      */         }
/*  997 */         this.copy.cleanup();
/*      */         
/*  999 */         this.connectStatus = false;
/* 1000 */         this.connected = false;
/*      */         
/* 1002 */         this.openStatus = false;
/* 1003 */         this.resourceOpen = false;
/*      */       } 
/*      */     } else {
/*      */       
/* 1007 */       Vector<MQQueue> qVector = new Vector<>(this.queues);
/*      */       
/* 1009 */       for (int i = 0; i < qVector.size(); i++) {
/* 1010 */         MQQueue q = qVector.elementAt(i);
/* 1011 */         if (q.resourceOpen) {
/* 1012 */           if (Trace.isOn);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1034 */       this.queues.removeAllElements();
/* 1035 */       if (Trace.isOn) {
/* 1036 */         Trace.traceData(this, "All queues closed.", null);
/*      */       }
/*      */ 
/*      */       
/* 1040 */       this.qmEventListener = null;
/*      */       
/*      */       try {
/* 1043 */         close();
/* 1044 */       } catch (MQException ex) {
/* 1045 */         if (Trace.isOn) {
/* 1046 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "cleanup()", (Throwable)ex, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1051 */         if (Trace.isOn) {
/* 1052 */           Trace.traceData(this, "Exception during close, proceeding anyway.", null);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1058 */       this.connectStatus = false;
/* 1059 */       this.connected = false;
/* 1060 */       this.connectionReference = null;
/*      */     } 
/*      */     
/* 1063 */     if (Trace.isOn) {
/* 1064 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "cleanup()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void registerQueue(MQQueue q) {
/* 1074 */     if (Trace.isOn) {
/* 1075 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "registerQueue(MQQueue)", new Object[] { q });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1080 */     this.queues.addElement(q);
/* 1081 */     if (Trace.isOn) {
/* 1082 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "registerQueue(MQQueue)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void unregisterQueue(MQQueue q) {
/* 1092 */     if (Trace.isOn) {
/* 1093 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "unregisterQueue(MQQueue)", new Object[] { q });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1098 */     this.queues.removeElement(q);
/* 1099 */     if (Trace.isOn) {
/* 1100 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "unregisterQueue(MQQueue)");
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
/*      */   void errorOccurred(MQException mqe) {
/* 1114 */     if (Trace.isOn) {
/* 1115 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "errorOccurred(MQException)", new Object[] { mqe });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1121 */     if (this.allowErrorEvents && mqe.completionCode == 2) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1126 */       MQQueueManagerEventListener el = this.qmEventListener;
/* 1127 */       if (el != null && Reason.isConnectionBroken(mqe.reasonCode)) {
/* 1128 */         el.onConnectionBrokenException(this, mqe);
/*      */       }
/*      */     } 
/*      */     
/* 1132 */     if (Trace.isOn) {
/* 1133 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "errorOccurred(MQException)");
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
/*      */   void setExceptionForDisconnect(MQException mqe) {
/* 1145 */     if (Trace.isOn) {
/* 1146 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "setExceptionForDisconnect(MQException)", "setter", mqe);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1151 */     this.exceptionForDisconnect = mqe;
/* 1152 */     if (this.copy != null) {
/* 1153 */       this.copy.exceptionForDisconnect = mqe;
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
/*      */   synchronized void honourRRS() throws MQException {
/* 1169 */     if (Trace.isOn) {
/* 1170 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "honourRRS()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1177 */     this.osession.honourRRS(this.hconn, this.completionCode, this.reason);
/*      */     
/* 1179 */     if (this.completionCode.x != 0 || this.reason.x != 0) {
/* 1180 */       MQException mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*      */       
/* 1182 */       if (Trace.isOn) {
/* 1183 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "honourRRS()", (Throwable)mqe);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1188 */       throw mqe;
/*      */     } 
/*      */     
/* 1191 */     if (Trace.isOn) {
/* 1192 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "honourRRS()");
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
/*      */   public int _getConnectionCCSID() {
/* 1210 */     if (Trace.isOn) {
/* 1211 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "_getConnectionCCSID()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1216 */     int traceRet1 = this.osession.getConnectionCCSID();
/* 1217 */     if (Trace.isOn) {
/* 1218 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "_getConnectionCCSID()", 
/*      */ 
/*      */           
/* 1221 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1223 */     return traceRet1;
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
/*      */   public boolean getSupportsQAT2() {
/* 1235 */     if (Trace.isOn) {
/* 1236 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "getSupportsQAT2()", "getter", 
/*      */ 
/*      */ 
/*      */           
/* 1240 */           Boolean.valueOf(this.supportsQAT2));
/*      */     }
/* 1242 */     return this.supportsQAT2;
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
/*      */   public void setSupportsQAT2(boolean b) {
/* 1256 */     if (Trace.isOn) {
/* 1257 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "setSupportsQAT2(boolean)", "setter", 
/*      */ 
/*      */           
/* 1260 */           Boolean.valueOf(b));
/*      */     }
/* 1262 */     this.supportsQAT2 = b;
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
/*      */   public MQQueueManagerEventListener getEventListener() {
/* 1274 */     if (Trace.isOn) {
/* 1275 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "getEventListener()", "getter", this.qmEventListener);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1280 */     return this.qmEventListener;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEventListener(MQQueueManagerEventListener el) {
/* 1291 */     if (Trace.isOn) {
/* 1292 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "setEventListener(MQQueueManagerEventListener)", "setter", el);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1298 */     this.qmEventListener = el;
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
/*      */   private String determineTransport(Map<String, Object> mqProperties) throws MQException {
/* 1320 */     if (Trace.isOn) {
/* 1321 */       if (mqProperties.containsKey("password")) {
/* 1322 */         Map<String, Object> propsNotPasswd = new HashMap<>(mqProperties);
/*      */         
/* 1324 */         propsNotPasswd.put("password", "********");
/* 1325 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "determineTransport(Map<String,Object>)", new Object[] { propsNotPasswd });
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1331 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "determineTransport(Map<String,Object>)", new Object[] { mqProperties });
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1340 */     String trans = MQEnvironment.getStringProperty("transport", mqProperties);
/*      */ 
/*      */ 
/*      */     
/* 1344 */     if (trans.equals("MQSeries")) {
/*      */       
/* 1346 */       String hostname = MQEnvironment.getStringProperty("hostname", mqProperties);
/*      */ 
/*      */       
/* 1349 */       if (hostname == null || hostname.trim().equals("")) {
/* 1350 */         trans = "MQSeries Bindings";
/*      */       } else {
/*      */         
/* 1353 */         trans = "MQSeries Client";
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1359 */     if (!trans.equals("MQSeries Client")) {
/*      */       
/* 1361 */       String scphs = MQEnvironment.getStringProperty("SSL Cipher Suite", mqProperties);
/*      */ 
/*      */       
/* 1364 */       if (scphs != null && !scphs.trim().equals("")) {
/* 1365 */         MQException traceRet1 = new MQException(2, 2396, null);
/*      */ 
/*      */         
/* 1368 */         if (Trace.isOn) {
/* 1369 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "determineTransport(Hashtable)", (Throwable)traceRet1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1374 */         throw traceRet1;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1379 */     if (Trace.isOn) {
/* 1380 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "determineTransport(Hashtable)", trans);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1385 */     return trans;
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
/*      */   private MQQueueManager obtainBaseMQQueueManager(String qmgr, Map<String, Object> mqProperties) throws MQException {
/* 1401 */     if (Trace.isOn) {
/* 1402 */       if (mqProperties.containsKey("password")) {
/* 1403 */         Map<String, Object> propsNotPasswd = new HashMap<>(mqProperties);
/*      */         
/* 1405 */         propsNotPasswd.put("password", "********");
/* 1406 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "obtainBaseMQQueueManager(String,Map<String,Object>)", new Object[] { qmgr, propsNotPasswd });
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1412 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "obtainBaseMQQueueManager(String,Map<String,Object>)", new Object[] { qmgr, mqProperties });
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1421 */     String transport = determineTransport(mqProperties);
/*      */ 
/*      */ 
/*      */     
/* 1425 */     MQManagedConnectionFactory mcf = MQSESSION.getMQManagedConnectionFactory(transport, qmgr, mqProperties);
/* 1426 */     MQConnectionRequestInfo cxReqInf = MQSESSION.getConnectionRequestInfo(transport, mqProperties, false);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1432 */       MQManagedConnectionJ11 mc = mcf.createManagedConnection(cxReqInf);
/* 1433 */       MQQueueManager m = (MQQueueManager)mc.getConnection(cxReqInf);
/* 1434 */       if (Trace.isOn) {
/* 1435 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "obtainBaseMQQueueManager(String,Hashtable)", m);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1440 */       return m;
/* 1441 */     } catch (MQResourceException e) {
/* 1442 */       if (Trace.isOn) {
/* 1443 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "obtainBaseMQQueueManager(String,Hashtable)", e);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1448 */       MQException traceRet1 = processException(e);
/* 1449 */       if (Trace.isOn) {
/* 1450 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "obtainBaseMQQueueManager(String,Hashtable)", (Throwable)traceRet1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1455 */       throw traceRet1;
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
/*      */   private MQException processException(MQResourceException e) {
/* 1468 */     if (Trace.isOn) {
/* 1469 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "processException(MQResourceException)", new Object[] { e });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1475 */     if (e.getMessage().equals("allocateConnection")) {
/*      */       
/* 1477 */       MQException traceRet1 = new MQException(2, 2025, "no more connections available");
/*      */ 
/*      */       
/* 1480 */       if (Trace.isOn) {
/* 1481 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "processException(MQResourceException)", traceRet1, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1486 */       return traceRet1;
/*      */     } 
/*      */     
/* 1489 */     Exception le = e.getLinkedException();
/* 1490 */     if (le instanceof MQException) {
/* 1491 */       MQException traceRet2 = (MQException)le;
/* 1492 */       if (Trace.isOn) {
/* 1493 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "processException(MQResourceException)", traceRet2, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1498 */       return traceRet2;
/*      */     } 
/*      */     
/* 1501 */     MQException traceRet3 = new MQException(2, 2195, this);
/*      */     
/* 1503 */     if (Trace.isOn) {
/* 1504 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "processException(MQResourceException)", traceRet3, 3);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1509 */     return traceRet3;
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
/*      */   private MQQueueManager procure(String queueManagerName, Map<String, Object> mqProperties) throws MQException {
/* 1521 */     if (Trace.isOn) {
/* 1522 */       if (mqProperties.containsKey("password")) {
/* 1523 */         Map<String, Object> propsNotPasswd = new HashMap<>(mqProperties);
/*      */         
/* 1525 */         propsNotPasswd.put("password", "********");
/* 1526 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "procure(String,Map<String,Object>)", new Object[] { queueManagerName, propsNotPasswd });
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1532 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "procure(String,Map<String,Object>)", new Object[] { queueManagerName, mqProperties });
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1541 */     MQQueueManager baseQM = obtainBaseMQQueueManager(queueManagerName, mqProperties);
/*      */ 
/*      */ 
/*      */     
/* 1545 */     if (Trace.isOn) {
/* 1546 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "procure(String,Hashtable)", baseQM);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1551 */     return baseQM;
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
/*      */   public synchronized void asyncCommit() throws MQException {
/* 1573 */     if (Trace.isOn) {
/* 1574 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "asyncCommit()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1581 */       if (!this.connected) {
/* 1582 */         if (Trace.isOn) {
/* 1583 */           Trace.traceData(this, "not connected", null);
/*      */         }
/* 1585 */         MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*      */ 
/*      */         
/* 1588 */         if (Trace.isOn) {
/* 1589 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "asyncCommit()", (Throwable)traceRet1, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1594 */         throw traceRet1;
/*      */       } 
/*      */       
/* 1597 */       MQSESSION osession = getSession();
/* 1598 */       osession.spiAsyncCmit(this.hconn, this.completionCode, this.reason);
/*      */       
/* 1600 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/* 1601 */         MQException mqe = new MQException(this.completionCode.x, this.reason.x, this);
/*      */         
/* 1603 */         errorOccurred(mqe);
/*      */         
/* 1605 */         if (Trace.isOn) {
/* 1606 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "asyncCommit()", (Throwable)mqe, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1611 */         throw mqe;
/*      */       } 
/*      */     } finally {
/*      */       
/* 1615 */       if (Trace.isOn) {
/* 1616 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "asyncCommit()");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1621 */       if (Trace.isOn) {
/* 1622 */         Trace.traceData(this, "asyncCommit completed with completionCode: " + this.completionCode.x + " reason: " + this.reason.x, null);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1628 */     if (Trace.isOn) {
/* 1629 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "asyncCommit()");
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
/*      */   public synchronized void spiActivateDeferred(byte[] msgId) throws MQException {
/* 1650 */     if (Trace.isOn) {
/* 1651 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "spiActivateDeferred(byte [ ])", new Object[] { msgId });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1657 */     if (!this.connected) {
/* 1658 */       if (Trace.isOn) {
/* 1659 */         Trace.traceData(this, "not connected", null);
/*      */       }
/* 1661 */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*      */       
/* 1663 */       if (Trace.isOn) {
/* 1664 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "spiActivateDeferred(byte [ ])", (Throwable)traceRet1, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1669 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1673 */     if (msgId == null || msgId.length != 24) {
/* 1674 */       if (Trace.isOn) {
/* 1675 */         Trace.traceData(this, "msgId is null or msgId.length != MQC.MQ_MSG_ID_LENGTH", null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1682 */       MQException traceRet2 = new MQException(2, 2206, this);
/*      */       
/* 1684 */       if (Trace.isOn) {
/* 1685 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "spiActivateDeferred(byte [ ])", (Throwable)traceRet2, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1690 */       throw traceRet2;
/*      */     } 
/*      */     
/* 1693 */     MQSESSION osession = getSession();
/* 1694 */     osession.spiDefActivate(this.hconn, msgId, this.completionCode, this.reason);
/*      */     
/* 1696 */     if (this.completionCode.x != 0 || this.reason.x != 0) {
/* 1697 */       MQException mqe = new MQException(this.completionCode.x, this.reason.x, this);
/* 1698 */       errorOccurred(mqe);
/*      */       
/* 1700 */       if (Trace.isOn) {
/* 1701 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "spiActivateDeferred(byte [ ])", (Throwable)mqe, 3);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1706 */       throw mqe;
/*      */     } 
/*      */     
/* 1709 */     if (Trace.isOn) {
/* 1710 */       Trace.traceData(this, "spiActivateDeferred completed with completionCode: " + this.completionCode.x + " reason: " + this.reason.x, null);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1715 */     if (Trace.isOn) {
/* 1716 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "spiActivateDeferred(byte [ ])");
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
/*      */   public synchronized void spiCancelDeferred(byte[] msgId) throws MQException {
/* 1736 */     if (Trace.isOn) {
/* 1737 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "spiCancelDeferred(byte [ ])", new Object[] { msgId });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1743 */     if (!this.connected) {
/* 1744 */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*      */       
/* 1746 */       if (Trace.isOn) {
/* 1747 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "spiCancelDeferred(byte [ ])", (Throwable)traceRet1, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1752 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/* 1756 */     if (msgId == null || msgId.length != 24) {
/* 1757 */       MQException traceRet2 = new MQException(2, 2206, this);
/*      */       
/* 1759 */       if (Trace.isOn) {
/* 1760 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "spiCancelDeferred(byte [ ])", (Throwable)traceRet2, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1765 */       throw traceRet2;
/*      */     } 
/*      */     
/* 1768 */     MQSESSION osession = getSession();
/* 1769 */     osession.spiDefCancel(this.hconn, msgId, this.completionCode, this.reason);
/*      */     
/* 1771 */     if (this.completionCode.x != 0 || this.reason.x != 0) {
/* 1772 */       MQException mqe = new MQException(this.completionCode.x, this.reason.x, this);
/* 1773 */       errorOccurred(mqe);
/* 1774 */       if (Trace.isOn) {
/* 1775 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "spiCancelDeferred(byte [ ])", (Throwable)mqe, 3);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1780 */       throw mqe;
/*      */     } 
/*      */     
/* 1783 */     if (Trace.isOn) {
/* 1784 */       Trace.traceData(this, "spiCancelDeferred completed with completionCode: " + this.completionCode.x + " reason: " + this.reason.x, null);
/*      */     }
/*      */ 
/*      */     
/* 1788 */     if (Trace.isOn) {
/* 1789 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "spiCancelDeferred(byte [ ])");
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
/*      */   public synchronized boolean spiSupportsDeferred() throws MQException {
/* 1809 */     if (Trace.isOn) {
/* 1810 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "spiSupportsDeferred()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1816 */     if (!this.connected) {
/* 1817 */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*      */       
/* 1819 */       if (Trace.isOn) {
/* 1820 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "spiSupportsDeferred()", (Throwable)traceRet1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1825 */       throw traceRet1;
/*      */     } 
/*      */     
/* 1828 */     MQSESSION osession = getSession();
/* 1829 */     boolean out = osession.spiSupportsDeferred();
/*      */     
/* 1831 */     if (Trace.isOn) {
/* 1832 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "spiSupportsDeferred()", 
/*      */ 
/*      */           
/* 1835 */           Boolean.valueOf(out));
/*      */     }
/* 1837 */     return out;
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
/*      */   public synchronized boolean spiSupportsInherited() throws MQException {
/* 1851 */     if (Trace.isOn) {
/* 1852 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "spiSupportsInherited()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1858 */     if (!this.connected) {
/* 1859 */       if (Trace.isOn) {
/* 1860 */         Trace.traceData(this, "not connected", null);
/*      */       }
/* 1862 */       MQException traceRet1 = new MQException(2, 2018, this, "MQJI002");
/*      */       
/* 1864 */       if (Trace.isOn) {
/* 1865 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "spiSupportsInherited()", (Throwable)traceRet1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1870 */       throw traceRet1;
/*      */     } 
/*      */     
/* 1873 */     MQSESSION osession = getSession();
/* 1874 */     boolean out = osession.spiSupportsInherited();
/*      */     
/* 1876 */     if (Trace.isOn) {
/* 1877 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "spiSupportsInherited()", 
/*      */ 
/*      */           
/* 1880 */           Boolean.valueOf(out));
/*      */     }
/* 1882 */     return out;
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
/*      */   static String translate(String suite, int source) {
/* 1897 */     if (Trace.isOn) {
/* 1898 */       Trace.entry("com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "translate(String,int)", new Object[] { suite, 
/*      */ 
/*      */             
/* 1901 */             Integer.valueOf(source) });
/*      */     }
/* 1903 */     String spec = "";
/* 1904 */     if (suite != null) {
/* 1905 */       String[][] strings = { { "SSL_RSA_WITH_DES_CBC_SHA", "SSL_RSA_EXPORT1024_WITH_DES_CBC_SHA", "SSL_RSA_WITH_NULL_MD5", "SSL_RSA_WITH_NULL_SHA", "SSL_RSA_EXPORT_WITH_RC2_CBC_40_MD5", "SSL_RSA_EXPORT1024_WITH_RC4_56_SHA", "SSL_RSA_WITH_RC4_128_MD5", "SSL_RSA_EXPORT_WITH_RC4_40_MD5", "SSL_RSA_WITH_RC4_128_SHA", "SSL_RSA_WITH_3DES_EDE_CBC_SHA", "SSL_RSA_WITH_AES_128_CBC_SHA", "SSL_RSA_WITH_AES_256_CBC_SHA", "SSL_RSA_WITH_DES_CBC_SHA", "SSL_RSA_WITH_3DES_EDE_CBC_SHA", "SSL_RSA_FIPS_WITH_DES_CBC_SHA", "SSL_RSA_FIPS_WITH_3DES_EDE_CBC_SHA" }, { "DES_SHA_EXPORT", "DES_SHA_EXPORT1024", "NULL_MD5", "NULL_SHA", "RC2_MD5_EXPORT", "RC4_56_SHA_EXPORT1024", "RC4_MD5_US", "RC4_MD5_EXPORT", "RC4_SHA_US", "TRIPLE_DES_SHA_US", "TLS_RSA_WITH_AES_128_CBC_SHA", "TLS_RSA_WITH_AES_256_CBC_SHA", "TLS_RSA_WITH_DES_CBC_SHA", "TLS_RSA_WITH_3DES_EDE_CBC_SHA", "FIPS_WITH_DES_CBC_SHA", "FIPS_WITH_3DES_EDE_CBC_SHA" } };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1932 */       String[] ssource = strings[1];
/* 1933 */       String[] sdest = strings[0];
/* 1934 */       if (source == 0) {
/* 1935 */         ssource = strings[0];
/* 1936 */         sdest = strings[1];
/*      */       } 
/*      */       
/* 1939 */       for (int i = 0; i < ssource.length; i++) {
/* 1940 */         if (suite.equals(ssource[i])) {
/* 1941 */           spec = sdest[i];
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1947 */     if (Trace.isOn) {
/* 1948 */       Trace.exit("com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager", "translate(String,int)", spec);
/*      */     }
/*      */ 
/*      */     
/* 1952 */     return spec;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQQueueManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */