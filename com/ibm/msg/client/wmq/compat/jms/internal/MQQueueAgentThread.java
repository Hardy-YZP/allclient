/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.disthub2.impl.matching.BadMessageFormatMatchingException;
/*      */ import com.ibm.disthub2.impl.matching.MatchingException;
/*      */ import com.ibm.disthub2.impl.util.FastVector;
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.msg.client.commonservices.CSIException;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.commonservices.workqueue.WorkQueueManager;
/*      */ import com.ibm.msg.client.wmq.common.internal.Reason;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMessage;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager;
/*      */ import java.io.IOException;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.Hashtable;
/*      */ import java.util.TimeZone;
/*      */ import java.util.Vector;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ abstract class MQQueueAgentThread
/*      */   implements Runnable
/*      */ {
/*      */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQQueueAgentThread.java";
/*      */   protected static final long SWEEP_TIMEOUT = 30000L;
/*      */   protected static final long REDELIVER_INTERVAL = 60000L;
/*      */   
/*      */   static {
/*   73 */     if (Trace.isOn) {
/*   74 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQQueueAgentThread.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean startSweep = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected long sweepStartedTime;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   95 */   protected long sweepTimeoutStart = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String QUEUEAGENTTHREAD_SLEEPTIME = "com.ibm.mq.jms.tuning.queueAgentThreadSleepTime";
/*      */ 
/*      */ 
/*      */   
/*      */   protected long sleeptime;
/*      */ 
/*      */ 
/*      */   
/*  108 */   private JMSException lastException = null;
/*      */ 
/*      */ 
/*      */   
/*      */   MQQueueAgent mqQueueAgent;
/*      */ 
/*      */ 
/*      */   
/*      */   String qmgrName;
/*      */ 
/*      */ 
/*      */   
/*      */   String qName;
/*      */ 
/*      */ 
/*      */   
/*      */   MQConnection jmsConnection;
/*      */ 
/*      */   
/*      */   MQSession jmsSession;
/*      */ 
/*      */   
/*      */   MQQueueManager mqQueueManager;
/*      */ 
/*      */   
/*      */   MQQueue mqQueue;
/*      */ 
/*      */   
/*  136 */   private String mqBORQName = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String useDefaultBOValues = "com.ibm.mq.jms.useDefaultBOValues";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean puttingToBORQ = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int backoutThreshold;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean retainMessages = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean fullMessages = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean active = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean quit = false;
/*      */ 
/*      */ 
/*      */   
/*  174 */   private JMSException exception = null;
/*      */ 
/*      */   
/*      */   int oldCount;
/*      */ 
/*      */   
/*      */   int newCount;
/*      */ 
/*      */   
/*      */   int failedCount;
/*      */   
/*  185 */   int browserChoice = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean messagesToDeliver = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  195 */   protected final MQGetMessageOptions mqGMO = new MQGetMessageOptions(this instanceof MQQueueAgentThread1Impl);
/*      */ 
/*      */ 
/*      */   
/*      */   protected int options;
/*      */ 
/*      */   
/*  202 */   private Thread queueAgentThreadThread = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQQueueAgentThread(MQQueueAgent mqQueueAgent, MQConnection jmsConnection, String qmgrName, String qName) throws JMSException {
/*  216 */     if (Trace.isOn) {
/*  217 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "<init>(MQQueueAgent,MQConnection,String,String)", new Object[] { mqQueueAgent, jmsConnection, qmgrName, qName });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  222 */     this.mqQueueAgent = mqQueueAgent;
/*  223 */     this.jmsConnection = jmsConnection;
/*  224 */     this.qmgrName = qmgrName;
/*  225 */     this.qName = qName;
/*      */ 
/*      */ 
/*      */     
/*  229 */     PropertyStore.register("com.ibm.mq.jms.tuning.queueAgentThreadSleepTime", 5000L, Long.valueOf(0L), null);
/*      */ 
/*      */     
/*  232 */     this
/*  233 */       .sleeptime = PropertyStore.getLongPropertyObject("com.ibm.mq.jms.tuning.queueAgentThreadSleepTime").longValue();
/*      */     
/*  235 */     if (Trace.isOn) {
/*  236 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "<init>(MQQueueAgent,MQConnection,String,String)");
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
/*      */   void go() throws JMSException {
/*  253 */     if (Trace.isOn) {
/*  254 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "go()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  259 */       WorkQueueManager.enqueue(this, 0, false);
/*      */     
/*      */     }
/*  262 */     catch (CSIException ce) {
/*  263 */       if (Trace.isOn) {
/*  264 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "go()", (Throwable)ce, 1);
/*      */       }
/*      */       
/*  267 */       HashMap<String, CSIException> m = new HashMap<>();
/*  268 */       m.put("exception", ce);
/*  269 */       Trace.ffst(this, "start()", "XO00A001", m, JMSException.class);
/*      */     } 
/*      */     
/*  272 */     synchronized (this) {
/*  273 */       while (!this.active && this.exception == null) {
/*      */         try {
/*  275 */           if (Trace.isOn) {
/*  276 */             Trace.traceData(this, "go() waiting for thread to start up", null);
/*      */           }
/*      */           
/*  279 */           wait();
/*  280 */           if (Trace.isOn) {
/*  281 */             Trace.traceData(this, "go() woken up", null);
/*      */           }
/*      */         }
/*  284 */         catch (InterruptedException ie) {
/*  285 */           if (Trace.isOn) {
/*  286 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "go()", ie, 2);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  296 */     if (Trace.isOn) {
/*  297 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "go()");
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
/*      */   void retainAllMessages() {
/*  309 */     if (Trace.isOn) {
/*  310 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "retainAllMessages()");
/*      */     }
/*      */     
/*  313 */     this.retainMessages = true;
/*  314 */     if (Trace.isOn) {
/*  315 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "retainAllMessages()");
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
/*      */   void quit() {
/*  330 */     if (Trace.isOn) {
/*  331 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "quit()");
/*      */     }
/*      */     
/*  334 */     this.quit = true;
/*  335 */     if (Trace.isOn) {
/*  336 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "quit()");
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
/*      */   void waitForEnd() {
/*  349 */     if (Trace.isOn) {
/*  350 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "waitForEnd()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  357 */     if (!Thread.currentThread().equals(this.queueAgentThreadThread)) {
/*  358 */       synchronized (this) {
/*  359 */         while (this.active && this.exception == null) {
/*      */           try {
/*  361 */             wait();
/*      */           }
/*  363 */           catch (InterruptedException ie) {
/*  364 */             if (Trace.isOn) {
/*  365 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "waitForEnd()", ie);
/*      */             }
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
/*  377 */     if (Trace.isOn) {
/*  378 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "waitForEnd()");
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
/*      */   void setup() throws JMSException {
/*  393 */     if (Trace.isOn) {
/*  394 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "setup()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  403 */       this.mqQueueManager = this.jmsConnection.createQMNonXA();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  409 */       this.jmsSession = new MQSession(this.jmsConnection, this.mqQueueManager, false, 0);
/*      */       
/*  411 */       if (Trace.isOn) {
/*  412 */         Trace.traceData(this, "session created", null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  420 */         this.mqQueue = this.mqQueueManager.accessQueue(this.qName, this.options);
/*      */       
/*      */       }
/*  423 */       catch (MQException mqe) {
/*  424 */         if (Trace.isOn) {
/*  425 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "setup()", (Throwable)mqe, 1);
/*      */         }
/*      */         
/*  428 */         JMSException je = ConfigEnvironment.newException("MQJMS2008", this.qName);
/*      */         
/*  430 */         je.setLinkedException((Exception)mqe);
/*  431 */         if (Trace.isOn) {
/*  432 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "setup()", (Throwable)je, 1);
/*      */         }
/*      */         
/*  435 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*  439 */       this.mqGMO.options |= 0x2000;
/*  440 */       this.mqGMO.matchOptions = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  452 */       int mqQueueType = Utils.inquireInt((MQManagedObject)this.mqQueue, 20);
/*  453 */       if (mqQueueType == 3) {
/*      */         
/*  455 */         String baseQueueName = Utils.inquireString((MQManagedObject)this.mqQueue, 2002);
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*  460 */           PropertyStore.register("com.ibm.mq.jms.useDefaultBOValues", false);
/*      */           
/*  462 */           if (PropertyStore.getBooleanPropertyObject("com.ibm.mq.jms.useDefaultBOValues")
/*  463 */             .booleanValue()) {
/*  464 */             if (Trace.isOn) {
/*  465 */               Trace.traceData(this, "useDefaultBOValues =com.ibm.mq.jms.useDefaultBOValues", null);
/*      */             }
/*      */             
/*  468 */             this.backoutThreshold = -1;
/*      */           } else {
/*      */             
/*  471 */             int options1 = 8224;
/*      */ 
/*      */ 
/*      */             
/*  475 */             MQQueue mqBaseQueue = this.mqQueueManager.accessQueue(baseQueueName, options1);
/*  476 */             int baseQueueType = Utils.inquireInt((MQManagedObject)mqBaseQueue, 20);
/*      */             
/*  478 */             if (baseQueueType == 7) {
/*  479 */               mqBaseQueue.close();
/*  480 */               int options_forClusterQ = 8232;
/*      */ 
/*      */               
/*  483 */               mqBaseQueue = this.mqQueueManager.accessQueue(baseQueueName, options_forClusterQ);
/*      */             } 
/*      */ 
/*      */             
/*  487 */             this.backoutThreshold = Utils.inquireInt((MQManagedObject)mqBaseQueue, 22);
/*      */             
/*  489 */             mqBaseQueue.close();
/*      */           }
/*      */         
/*  492 */         } catch (MQException mqe) {
/*  493 */           if (Trace.isOn) {
/*  494 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "setup()", (Throwable)mqe, 2);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  502 */           this.backoutThreshold = -1;
/*      */           
/*  504 */           HashMap<String, Object> inserts = new HashMap<>();
/*  505 */           inserts.put("XMSC_INSERT_DESTINATION_NAME", baseQueueName);
/*      */           
/*  507 */           inserts.put("XMSC_INSERT_STRING", this.qName);
/*  508 */           inserts.put("XMSC_INSERT_VALUE", Integer.valueOf(this.backoutThreshold));
/*      */           
/*  510 */           inserts.put("XMSC_INSERT_EXCEPTION", mqe);
/*  511 */           inserts.put("XMSC_INSERT_COMP_CODE", Integer.valueOf(mqe
/*  512 */                 .getCompCode()));
/*  513 */           inserts.put("XMSC_INSERT_REASON", 
/*  514 */               Integer.valueOf(mqe.getReason()));
/*  515 */           Log.log(this, "setup()", "MQJMS1115", inserts);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  522 */         this.backoutThreshold = Utils.inquireInt((MQManagedObject)this.mqQueue, 22);
/*      */       } 
/*      */       
/*  525 */       if (this.backoutThreshold == -1) {
/*  526 */         if (Trace.isOn) {
/*  527 */           Trace.traceData(this, "Inquire on backoutThreshold failed. Defaulting to 20.", null);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  532 */         this.backoutThreshold = 20;
/*      */       }
/*      */     
/*  535 */     } catch (JMSException e) {
/*  536 */       if (Trace.isOn) {
/*  537 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "setup()", (Throwable)e, 3);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  545 */         if (this.jmsSession != null) {
/*  546 */           this.jmsSession.close(false);
/*      */         }
/*  548 */         if (this.mqQueueManager != null) {
/*  549 */           this.mqQueueManager.disconnect();
/*      */         }
/*      */       }
/*  552 */       catch (JMSException e2) {
/*  553 */         if (Trace.isOn) {
/*  554 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "setup()", (Throwable)e2, 4);
/*      */         
/*      */         }
/*      */       }
/*  558 */       catch (MQException e2) {
/*  559 */         if (Trace.isOn) {
/*  560 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "setup()", (Throwable)e2, 5);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  565 */       this.jmsSession = null;
/*  566 */       this.mqQueueManager = null;
/*  567 */       this.mqQueue = null;
/*      */       
/*  569 */       if (Trace.isOn) {
/*  570 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "setup()", (Throwable)e, 2);
/*      */       }
/*      */       
/*  573 */       throw e;
/*      */     } 
/*      */     
/*  576 */     if (Trace.isOn) {
/*  577 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "setup()");
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
/*      */   private void shutdown() throws JMSException {
/*  592 */     if (Trace.isOn) {
/*  593 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "shutdown()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  598 */       if (this.jmsSession != null) {
/*  599 */         this.jmsSession.close(false);
/*      */       }
/*      */     }
/*  602 */     catch (JMSException je) {
/*  603 */       if (Trace.isOn) {
/*  604 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "shutdown()", (Throwable)je);
/*      */       
/*      */       }
/*      */     }
/*      */     finally {
/*      */       
/*  610 */       if (Trace.isOn) {
/*  611 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "shutdown()");
/*      */       }
/*      */       
/*  614 */       this.jmsSession = null;
/*  615 */       this.mqQueueManager = null;
/*  616 */       this.mqQueue = null;
/*      */     } 
/*  618 */     if (Trace.isOn) {
/*  619 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "shutdown()");
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
/*      */   void processMessage(MQMessageReference msgRef) throws JMSException {
/*  672 */     if (Trace.isOn) {
/*  673 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)", new Object[] { msgRef });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  678 */       int i = 0;
/*  679 */       Vector<?> browsers = null;
/*  680 */       Hashtable<?, ?> psBrowsers = null;
/*  681 */       MQConnectionBrowser receiver = null;
/*      */       
/*  683 */       boolean okay = true;
/*  684 */       if (this.backoutThreshold != 0 && msgRef
/*  685 */         .getBackoutCount() >= this.backoutThreshold) {
/*  686 */         if (Trace.isOn) {
/*  687 */           Trace.traceData(this, "backoutThreshold reached", null);
/*      */         }
/*  689 */         okay = false;
/*      */         try {
/*  691 */           backoutRequeue(msgRef);
/*      */         }
/*  693 */         catch (JMSException je) {
/*  694 */           int reason; if (Trace.isOn) {
/*  695 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)", (Throwable)je, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  702 */           if (Trace.isOn) {
/*  703 */             Trace.traceData(this, "ProviderMessage requeue failed. Treating it as a bad message", null);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  708 */           Exception e = je.getLinkedException();
/*      */           
/*  710 */           if (e != null && e instanceof MQException) {
/*  711 */             reason = ((MQException)e).reasonCode;
/*      */           } else {
/*  713 */             reason = 2362;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  718 */             this.puttingToBORQ = false;
/*      */           } 
/*      */           
/*      */           try {
/*  722 */             removeBadMessage(msgRef, reason);
/*      */           }
/*  724 */           catch (JMSException je2) {
/*  725 */             if (Trace.isOn) {
/*  726 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)", (Throwable)je2, 2);
/*      */             }
/*      */ 
/*      */             
/*  730 */             if (Trace.isOn) {
/*  731 */               Trace.traceData(this, "removeBadMessage failed.", null);
/*      */             }
/*      */             
/*  734 */             if (Trace.isOn) {
/*  735 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)", (Throwable)je, 1);
/*      */             }
/*      */ 
/*      */             
/*  739 */             throw je;
/*      */           } 
/*      */         } finally {
/*      */           
/*  743 */           if (Trace.isOn) {
/*  744 */             Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)");
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  751 */           this.puttingToBORQ = false;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  760 */       if (okay && msgRef
/*  761 */         .getDataQuantity() != 0) {
/*  762 */         if (Trace.isOn) {
/*  763 */           Trace.traceData(this, "Checking format of message", null);
/*      */         }
/*      */         try {
/*  766 */           msgRef.getJMSMessage();
/*      */         }
/*  768 */         catch (JMSException je) {
/*  769 */           if (Trace.isOn) {
/*  770 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)", (Throwable)je, 3);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  775 */           if (Trace.isOn) {
/*  776 */             Trace.traceData(this, "ProviderMessage is badly formatted", null);
/*      */           }
/*      */           
/*  779 */           okay = false;
/*      */           try {
/*  781 */             removeBadMessage(msgRef, 2364);
/*      */           
/*      */           }
/*  784 */           catch (JMSException je2) {
/*  785 */             if (Trace.isOn) {
/*  786 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)", (Throwable)je2, 4);
/*      */             }
/*      */ 
/*      */             
/*  790 */             if (Trace.isOn) {
/*  791 */               Trace.traceData(this, "removeBadMessage failed.", null);
/*      */             }
/*      */             
/*  794 */             if (Trace.isOn) {
/*  795 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)", (Throwable)je2, 2);
/*      */             }
/*      */ 
/*      */             
/*  799 */             throw je2;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  804 */       if (okay) {
/*      */         boolean found;
/*      */ 
/*      */         
/*  808 */         msgRef.setReferenceQueue(this.qName);
/*  809 */         msgRef.setDomain(this.mqQueueAgent.getDomain());
/*      */         
/*  811 */         switch (this.mqQueueAgent.getDomain()) {
/*      */ 
/*      */           
/*      */           case 0:
/*  815 */             if (Trace.isOn) {
/*  816 */               Trace.traceData(this, "In the PtP domain", null);
/*      */             }
/*  818 */             browsers = new Vector();
/*  819 */             found = false;
/*  820 */             synchronized (this.mqQueueAgent.browsers) {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  825 */               if (this.mqQueueAgent.useSelectors > 0) {
/*      */                 
/*  827 */                 if (Trace.isOn) {
/*  828 */                   Trace.traceData(this, "Selectors in use", null);
/*      */                 }
/*      */                 try {
/*  831 */                   if (Trace.isOn) {
/*  832 */                     Trace.traceData(this, "Checking for suitable browsers", null);
/*      */                   }
/*      */ 
/*      */                   
/*  836 */                   SearchResults sResults = new SearchResults();
/*  837 */                   this.mqQueueAgent.getMatches("PtPTopic", msgRef, sResults);
/*      */ 
/*      */                   
/*  840 */                   if (sResults.m_receivers.m_count > 0) {
/*  841 */                     if (Trace.isOn) {
/*  842 */                       Trace.traceData(this, sResults.m_receivers.m_count + " matches found", null);
/*      */                     }
/*      */ 
/*      */ 
/*      */                     
/*  847 */                     FastVector vList = sResults.m_receivers;
/*  848 */                     browsers = vList.makeVector();
/*      */                   }
/*      */                 
/*      */                 }
/*  852 */                 catch (MatchingException e) {
/*  853 */                   if (Trace.isOn) {
/*  854 */                     Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)", (Throwable)e, 5);
/*      */                   }
/*      */ 
/*      */                   
/*  858 */                   if (Trace.isOn) {
/*  859 */                     Trace.traceData(this, "MatchingException thrown trying to get suitable browser matches", null);
/*      */                   }
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/*  865 */                   JMSException je = ConfigEnvironment.newException("MQJMS6233", e);
/*      */ 
/*      */                   
/*  868 */                   je.setLinkedException((Exception)e);
/*  869 */                   if (Trace.isOn) {
/*  870 */                     Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)", (Throwable)je, 3);
/*      */                   }
/*      */ 
/*      */                   
/*  874 */                   throw je;
/*      */                 }
/*  876 */                 catch (BadMessageFormatMatchingException e) {
/*  877 */                   if (Trace.isOn) {
/*  878 */                     Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)", (Throwable)e, 6);
/*      */                   }
/*      */ 
/*      */                   
/*  882 */                   if (Trace.isOn) {
/*  883 */                     Trace.traceData(this, "BadMessageFormatException thrown trying to get suitable browser matches", null);
/*      */                   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/*  890 */                   JMSException je = ConfigEnvironment.newException("MQJMS6233", e);
/*      */ 
/*      */                   
/*  893 */                   je.setLinkedException((Exception)e);
/*  894 */                   if (Trace.isOn) {
/*  895 */                     Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)", (Throwable)je, 4);
/*      */                   }
/*      */ 
/*      */                   
/*  899 */                   throw je;
/*      */                 } 
/*      */               } else {
/*      */                 
/*  903 */                 if (Trace.isOn) {
/*  904 */                   Trace.traceData(this, "No Selectors in use, all browsers are suitable", null);
/*      */                 }
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  910 */                 browsers = this.mqQueueAgent.getBrowsers();
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
/*  921 */               if (browsers.size() == 0) {
/*      */ 
/*      */                 
/*  924 */                 if (Trace.isOn) {
/*  925 */                   Trace.traceData(this, "No Suitable browsers found. Setting message unwanted", null);
/*      */                 }
/*      */ 
/*      */ 
/*      */                 
/*  930 */                 msgRef.setIsWanted(false);
/*      */                 
/*  932 */                 if (!this.retainMessages) {
/*  933 */                   if (Trace.isOn) {
/*  934 */                     Trace.traceData(this, "MessageRetention off, removing the message", null);
/*      */                   }
/*      */ 
/*      */ 
/*      */                   
/*  939 */                   removeBadMessage(msgRef, 2363);
/*      */                 }
/*  941 */                 else if (Trace.isOn) {
/*  942 */                   Trace.traceData(this, "MessageRetention on, keeping message on queue", null);
/*      */                 
/*      */                 }
/*      */ 
/*      */               
/*      */               }
/*      */               else {
/*      */ 
/*      */                 
/*  951 */                 this.browserChoice++;
/*  952 */                 i = this.browserChoice % browsers.size();
/*  953 */                 if (Trace.isOn) {
/*  954 */                   Trace.traceData(this, "Choosing suitable browser: browserChoice = " + this.browserChoice + ", number of choices = " + browsers
/*      */ 
/*      */ 
/*      */                       
/*  958 */                       .size() + ", choosing number " + i, null);
/*      */                 }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  965 */                 int max = browsers.size();
/*  966 */                 for (int j = 0; j < max && !found; j++) {
/*      */                   
/*  968 */                   receiver = (MQConnectionBrowser)browsers.elementAt((i + j) % max);
/*  969 */                   if (receiver.receiveFlag.receiving == true) {
/*  970 */                     found = true;
/*      */                   }
/*      */                 } 
/*      */               } 
/*      */             } 
/*  975 */             if (found) {
/*  976 */               if (Trace.isOn) {
/*  977 */                 Trace.traceData(this, "Delivering to:\n" + receiver
/*  978 */                     .toString(), null);
/*      */               }
/*      */               
/*  981 */               this.messagesToDeliver = true;
/*  982 */               deliverMsgRef(receiver, msgRef);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 1:
/* 1006 */             if (Trace.isOn) {
/* 1007 */               Trace.traceData(this, "In the PubSub domain", null);
/*      */             }
/* 1009 */             synchronized (this.mqQueueAgent.browsers) {
/*      */ 
/*      */               
/* 1012 */               receiver = null;
/*      */ 
/*      */               
/* 1015 */               psBrowsers = this.mqQueueAgent.getPubSubBrowserLookup();
/*      */               
/* 1017 */               if (this.mqQueueAgent.getUseCorrelId()) {
/*      */                 
/* 1019 */                 if (Trace.isOn) {
/* 1020 */                   Trace.traceData(this, "Shared queue, searching for browser with correlId =" + 
/*      */ 
/*      */                       
/* 1023 */                       Utils.bytesToHex(msgRef
/* 1024 */                         .getCorrelId()), null);
/*      */                 }
/*      */                 
/* 1027 */                 receiver = (MQConnectionBrowser)psBrowsers.get(Utils.bytesToHex(msgRef.getCorrelId()));
/* 1028 */                 if (null == receiver) {
/* 1029 */                   if (Trace.isOn) {
/* 1030 */                     Trace.traceData(this, "Could not find a suitable entry in the Hashtable", null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/*      */                   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*      */                 }
/* 1044 */                 else if (!receiver.receiveFlag.receiving) {
/* 1045 */                   receiver = null;
/* 1046 */                   if (Trace.isOn) {
/* 1047 */                     Trace.traceData(this, "Only suitable entry in the Hashtable is not yet active", null);
/*      */ 
/*      */                   
/*      */                   }
/*      */ 
/*      */                 
/*      */                 }
/*      */ 
/*      */               
/*      */               }
/*      */               else {
/*      */ 
/*      */                 
/* 1060 */                 receiver = (MQConnectionBrowser)psBrowsers.get("XXX-Exclusive-Subscriber-Key-XXX");
/*      */               } 
/*      */               
/* 1063 */               if (receiver == null && Trace.isOn) {
/* 1064 */                 Trace.traceData(this, "No suitable browsers found", null);
/*      */               }
/*      */ 
/*      */               
/* 1068 */               if (receiver != null && receiver
/* 1069 */                 .getMessageSelector() != null) {
/*      */ 
/*      */                 
/* 1072 */                 if (Trace.isOn) {
/* 1073 */                   Trace.traceData(this, "receiving subscription uses selectors", null);
/*      */                 }
/*      */ 
/*      */ 
/*      */                 
/*      */                 try {
/* 1079 */                   if (Trace.isOn) {
/* 1080 */                     if (null == this.jmsConnection) {
/* 1081 */                       Trace.traceData(this, "*** jmsConnection is null!", null);
/*      */ 
/*      */                       
/* 1084 */                       throw new NullPointerException();
/*      */                     } 
/* 1086 */                     Trace.traceData(this, "msgSelection = " + this.jmsConnection
/* 1087 */                         .getMsgSelection(), null);
/*      */                   }
/*      */                 
/*      */                 }
/* 1091 */                 catch (Throwable t) {
/* 1092 */                   if (Trace.isOn) {
/* 1093 */                     Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)", t, 7);
/*      */                   }
/*      */                 } 
/*      */ 
/*      */ 
/*      */                 
/* 1099 */                 if (this.jmsConnection.getMsgSelection() != 1) {
/* 1100 */                   if (Trace.isOn) {
/* 1101 */                     Trace.traceData(this, "Using client side selectors", null);
/*      */                   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 1109 */                   if (!msgRef.matches(receiver
/* 1110 */                       .getMessageSelector())) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/* 1117 */                     MQMessage discardMessage = new MQMessage();
/* 1118 */                     discardMessage
/* 1119 */                       .messageId = msgRef.getMessageId();
/*      */                     
/* 1121 */                     MQGetMessageOptions gmo = new MQGetMessageOptions();
/* 1122 */                     gmo.options = 64;
/*      */                     
/* 1124 */                     gmo.matchOptions = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/*      */                     try {
/* 1131 */                       this.mqQueue.get(discardMessage, gmo, 0);
/*      */                     
/*      */                     }
/* 1134 */                     catch (MQException mqe) {
/* 1135 */                       if (Trace.isOn) {
/* 1136 */                         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)", (Throwable)mqe, 8);
/*      */                       }
/*      */ 
/*      */                       
/* 1140 */                       if (mqe.reasonCode != 2033 && mqe.reasonCode != 2079) {
/*      */ 
/*      */ 
/*      */ 
/*      */                         
/* 1145 */                         JMSException je = ConfigEnvironment.newException("MQJMS1031");
/* 1146 */                         je.setLinkedException((Exception)mqe);
/*      */                         
/* 1148 */                         if (Trace.isOn) {
/* 1149 */                           Trace.traceData(this, "Unable to discard message.", null);
/*      */                         }
/*      */ 
/*      */ 
/*      */                         
/* 1154 */                         if (Trace.isOn) {
/* 1155 */                           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)", (Throwable)je, 5);
/*      */                         }
/*      */ 
/*      */                         
/* 1159 */                         throw je;
/* 1160 */                       }  if (Trace.isOn) {
/* 1161 */                         Trace.traceData(this, "Ignoring reason code = " + mqe.reasonCode, null);
/*      */                       }
/*      */                     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/*      */                     break;
/*      */                   } 
/* 1178 */                 } else if (Trace.isOn) {
/* 1179 */                   Trace.traceData(this, "Using Broker side selector, message should already be appropriate", null);
/*      */ 
/*      */                 
/*      */                 }
/*      */ 
/*      */               
/*      */               }
/* 1186 */               else if (Trace.isOn) {
/* 1187 */                 Trace.traceData(this, "No Selectors in use, all browsers are suitable", null);
/*      */               } 
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1194 */             if (receiver != null) {
/* 1195 */               if (receiver.receiveFlag.receiving == true) {
/* 1196 */                 if (Trace.isOn) {
/* 1197 */                   Trace.traceData(this, "Delivering to:\n" + receiver
/* 1198 */                       .toString(), null);
/*      */                 }
/* 1200 */                 this.messagesToDeliver = true;
/* 1201 */                 deliverMsgRef(receiver, msgRef); break;
/*      */               } 
/* 1203 */               if (Trace.isOn) {
/* 1204 */                 Trace.traceData(this, "Receiver not yet active", null);
/*      */               }
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */             
/* 1211 */             if (Trace.isOn) {
/* 1212 */               Trace.traceData(this, "No Suitable browser found. Setting message unwanted", null);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 1217 */             msgRef.setIsWanted(false);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/* 1228 */             if (Trace.isOn) {
/* 1229 */               Trace.traceData(this, "Unknown domain, not doing anything", null);
/*      */             }
/*      */             break;
/*      */         } 
/*      */ 
/*      */       
/*      */       } 
/* 1236 */     } catch (JMSException je) {
/* 1237 */       if (Trace.isOn) {
/* 1238 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)", (Throwable)je, 9);
/*      */       }
/*      */       
/* 1241 */       if (Trace.isOn) {
/* 1242 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)", (Throwable)je, 6);
/*      */       }
/*      */       
/* 1245 */       throw je;
/*      */     } 
/*      */     
/* 1248 */     if (Trace.isOn) {
/* 1249 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "processMessage(MQMessageReference)");
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
/*      */   protected void deliverMsgRef(MQConnectionBrowser browser, MQMessageReference msgRef) throws JMSException {
/* 1268 */     if (Trace.isOn) {
/* 1269 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "deliverMsgRef(MQConnectionBrowser,MQMessageReference)", new Object[] { browser, msgRef });
/*      */     }
/*      */     
/* 1272 */     browser.deliver(msgRef);
/* 1273 */     if (Trace.isOn) {
/* 1274 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "deliverMsgRef(MQConnectionBrowser,MQMessageReference)");
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
/*      */   void removeBadMessage(MQMessageReference msgRef, int reason) throws JMSException {
/* 1307 */     if (Trace.isOn) {
/* 1308 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "removeBadMessage(MQMessageReference,int)", new Object[] { msgRef, 
/* 1309 */             Integer.valueOf(reason) });
/*      */     }
/*      */     
/*      */     try {
/* 1313 */       MQJMSMessage mess = msgRef.getMQJMSMessage();
/* 1314 */       if ((mess.getReport() & 0x8000000) == 134217728) {
/* 1315 */         if (Trace.isOn) {
/* 1316 */           Trace.traceData(this, "Discarding message as per MQMD.Report", null);
/*      */         }
/*      */         
/* 1319 */         discard(msgRef, reason);
/*      */       } else {
/* 1321 */         if (Trace.isOn) {
/* 1322 */           Trace.traceData(this, "Dead-lettering message as per MQMD.Report", null);
/*      */         }
/*      */         
/* 1325 */         deadLetter(msgRef, reason);
/*      */       }
/*      */     
/* 1328 */     } catch (JMSException jmse) {
/* 1329 */       if (Trace.isOn) {
/* 1330 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "removeBadMessage(MQMessageReference,int)", (Throwable)jmse);
/*      */       }
/*      */       
/* 1333 */       if (Trace.isOn) {
/* 1334 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "removeBadMessage(MQMessageReference,int)", (Throwable)jmse);
/*      */       }
/*      */       
/* 1337 */       throw jmse;
/*      */     } 
/* 1339 */     if (Trace.isOn) {
/* 1340 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "removeBadMessage(MQMessageReference,int)");
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
/*      */   private void deadLetter(MQMessageReference msgRef, int reason) throws JMSException {
/* 1358 */     if (Trace.isOn) {
/* 1359 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "deadLetter(MQMessageReference,int)", new Object[] { msgRef, 
/* 1360 */             Integer.valueOf(reason) });
/*      */     }
/* 1362 */     MQMessage msg = null;
/* 1363 */     MQMessage reportMessage = null;
/* 1364 */     String replyQ = null;
/* 1365 */     String replyQMgr = null;
/* 1366 */     String mqDLQName = null;
/*      */     
/*      */     try {
/* 1369 */       msg = retrieveMessage(msgRef);
/*      */ 
/*      */       
/* 1372 */       mqDLQName = Utils.inquireString((MQManagedObject)this.mqQueueManager, 2006);
/*      */       
/* 1374 */       if (Trace.isOn) {
/* 1375 */         Trace.traceData(this, "DLQ = <" + mqDLQName + ">", null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1381 */       if (mqDLQName == null || mqDLQName.length() == 0) {
/* 1382 */         if (Trace.isOn) {
/* 1383 */           Trace.traceData(this, "Unable to find DLQ name - is one defined?", null);
/*      */         }
/*      */ 
/*      */         
/* 1387 */         JMSException je = ConfigEnvironment.newException("MQJMS1079");
/* 1388 */         if (Trace.isOn) {
/* 1389 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "deadLetter(MQMessageReference,int)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */         
/* 1393 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 1397 */       if ((msg.report & 0x7000000) != 0) {
/*      */ 
/*      */ 
/*      */         
/* 1401 */         reportMessage = generateReport(msg, reason);
/* 1402 */         replyQ = msg.replyToQueueName.trim();
/* 1403 */         replyQMgr = msg.replyToQueueManagerName.trim();
/*      */       } 
/*      */ 
/*      */       
/* 1407 */       if (Trace.isOn) {
/* 1408 */         Trace.traceData(this, "Constructing DLH", null);
/*      */       }
/*      */       
/* 1411 */       DLH dlh = new DLH();
/* 1412 */       dlh.reason = reason;
/* 1413 */       dlh.destQName = this.qName;
/* 1414 */       dlh.destQMgrName = this.qmgrName;
/* 1415 */       dlh.putApplType = 28;
/* 1416 */       dlh.putApplName = "MQ JMS ConnectionConsumer";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1422 */       if (this.puttingToBORQ == true) {
/* 1423 */         dlh.destQName = this.mqBORQName;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1428 */       GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
/* 1429 */       dlh.putDate = Utils.getDate(gc);
/* 1430 */       dlh.putTime = Utils.getTime(gc);
/*      */ 
/*      */       
/* 1433 */       dlh.write(msg);
/*      */ 
/*      */       
/* 1436 */       put1WithContext(msg, mqDLQName, null);
/*      */       
/* 1438 */       if (reportMessage != null) {
/*      */         
/*      */         try {
/* 1441 */           put1WithContext(reportMessage, replyQ, replyQMgr);
/*      */         }
/* 1443 */         catch (MQException mqe) {
/* 1444 */           if (Trace.isOn) {
/* 1445 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "deadLetter(MQMessageReference,int)", (Throwable)mqe, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1450 */           dlh.destQName = replyQ;
/* 1451 */           dlh.destQMgrName = replyQMgr;
/* 1452 */           dlh.reason = mqe.reasonCode;
/* 1453 */           dlh.write(reportMessage);
/*      */           
/* 1455 */           put1WithContext(reportMessage, mqDLQName, null);
/*      */         }
/*      */       
/*      */       }
/* 1459 */     } catch (MQException mqe) {
/* 1460 */       if (Trace.isOn) {
/* 1461 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "deadLetter(MQMessageReference,int)", (Throwable)mqe, 2);
/*      */       }
/*      */ 
/*      */       
/* 1465 */       if (mqe.reasonCode == 2033) {
/*      */         
/* 1467 */         if (Trace.isOn) {
/* 1468 */           Trace.traceData(this, "ProviderMessage to dead-letter has disappeared! Continue as normal", null);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1478 */       else if (msg != null && msg.persistence == 0) {
/*      */         
/* 1480 */         if (Trace.isOn) {
/* 1481 */           Trace.traceData(this, "Could not send report message, but message is non-persistent - so subsume the error", null);
/*      */         }
/*      */       } else {
/*      */ 
/*      */         
/*      */         try {
/*      */ 
/*      */ 
/*      */           
/* 1490 */           this.mqQueueManager.backout();
/*      */         }
/* 1492 */         catch (MQException mqe2) {
/* 1493 */           if (Trace.isOn) {
/* 1494 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "deadLetter(MQMessageReference,int)", (Throwable)mqe2, 3);
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1501 */         JMSException je = ConfigEnvironment.newException("MQJMS1079");
/* 1502 */         je.setLinkedException((Exception)mqe);
/* 1503 */         if (Trace.isOn) {
/* 1504 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "deadLetter(MQMessageReference,int)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 1507 */         throw je;
/*      */       }
/*      */     
/* 1510 */     } catch (JMSException je) {
/* 1511 */       if (Trace.isOn) {
/* 1512 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "deadLetter(MQMessageReference,int)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */       
/* 1516 */       if (msg != null && msg.persistence == 0) {
/* 1517 */         if (Trace.isOn) {
/* 1518 */           Trace.traceData(this, "ProviderMessage is non-persistent so ignoring error", null);
/*      */         }
/*      */       } else {
/*      */ 
/*      */         
/*      */         try {
/*      */           
/* 1525 */           this.mqQueueManager.backout();
/*      */         }
/* 1527 */         catch (MQException mqe) {
/* 1528 */           if (Trace.isOn) {
/* 1529 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "deadLetter(MQMessageReference,int)", (Throwable)mqe, 5);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1534 */         if (Trace.isOn) {
/* 1535 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "deadLetter(MQMessageReference,int)", (Throwable)je, 3);
/*      */         }
/*      */         
/* 1538 */         throw je;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 1544 */       this.mqQueueManager.commit();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1549 */       if (reason == 2364)
/*      */       {
/* 1551 */         HashMap<Object, Object> inserts = new HashMap<>();
/* 1552 */         inserts.put("XMSC_INSERT_MESSAGE_ID", 
/* 1553 */             Utils.bytesToHex(msg.messageId));
/* 1554 */         inserts.put("XMSC_INSERT_DESTINATION_NAME", this.qName);
/* 1555 */         inserts.put("XMSC_INSERT_QUEUE_MANAGER_NAME", this.mqQueueManager.name);
/*      */         
/* 1557 */         inserts.put("XMSC_INSERT_STRING", mqDLQName
/* 1558 */             .substring(0, 48));
/* 1559 */         Log.log(this, "deadLetter()", "MQJMS1116", inserts);
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1564 */     catch (MQException mqe) {
/* 1565 */       if (Trace.isOn) {
/* 1566 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "deadLetter(MQMessageReference,int)", (Throwable)mqe, 6);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1571 */       JMSException je = ConfigEnvironment.newException("MQJMS1079");
/* 1572 */       je.setLinkedException((Exception)mqe);
/*      */       
/* 1574 */       if (Trace.isOn) {
/* 1575 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "deadLetter(MQMessageReference,int)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 1578 */       throw je;
/*      */     } 
/*      */     
/* 1581 */     if (Trace.isOn) {
/* 1582 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "deadLetter(MQMessageReference,int)");
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
/*      */   void backoutRequeue(MQMessageReference msgRef) throws JMSException {
/* 1601 */     if (Trace.isOn) {
/* 1602 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "backoutRequeue(MQMessageReference)", new Object[] { msgRef });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1608 */       if (this.mqBORQName == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1616 */         int type = Utils.inquireInt((MQManagedObject)this.mqQueue, 20);
/* 1617 */         if (type == 3) {
/* 1618 */           if (Trace.isOn) {
/* 1619 */             Trace.traceData(this, "MQQueue " + this.mqQueue.name + "is an ALIAS queue.", null);
/*      */           }
/*      */ 
/*      */           
/* 1623 */           String baseQueueName = Utils.inquireString((MQManagedObject)this.mqQueue, 2002);
/*      */ 
/*      */           
/* 1626 */           if (Trace.isOn) {
/* 1627 */             Trace.traceData(this, "Attempting to read Backout Requeue Queue from underlying LOCAL queue " + baseQueueName, null);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           try {
/* 1636 */             int options1 = 8224;
/*      */ 
/*      */ 
/*      */             
/* 1640 */             MQQueue baseMqQueue = this.mqQueueManager.accessQueue(baseQueueName, options1);
/*      */             
/* 1642 */             int baseQueueType = Utils.inquireInt((MQManagedObject)baseMqQueue, 20);
/*      */             
/* 1644 */             if (baseQueueType == 7) {
/* 1645 */               baseMqQueue.close();
/* 1646 */               int options_forClusterQ = 8232;
/*      */ 
/*      */               
/* 1649 */               baseMqQueue = this.mqQueueManager.accessQueue(baseQueueName, options_forClusterQ);
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1655 */             this.mqBORQName = Utils.inquireString((MQManagedObject)baseMqQueue, 2019);
/*      */ 
/*      */             
/* 1658 */             baseMqQueue.close();
/*      */           }
/* 1660 */           catch (MQException mqe) {
/* 1661 */             if (Trace.isOn) {
/* 1662 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "backoutRequeue(MQMessageReference)", (Throwable)mqe, 1);
/*      */             }
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 1669 */           this.mqBORQName = Utils.inquireString((MQManagedObject)this.mqQueue, 2019);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1674 */       if (this.mqBORQName == null || this.mqBORQName.equals("")) {
/*      */         
/* 1676 */         JMSException jmse = ConfigEnvironment.newException("MQJMS1080");
/* 1677 */         if (Trace.isOn) {
/* 1678 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "backoutRequeue(MQMessageReference)", (Throwable)jmse, 1);
/*      */         }
/*      */ 
/*      */         
/* 1682 */         throw jmse;
/*      */       } 
/*      */       
/* 1685 */       this.mqBORQName = this.mqBORQName.trim();
/*      */ 
/*      */       
/* 1688 */       if (Trace.isOn) {
/* 1689 */         Trace.traceData(this, "BORQ = <" + this.mqBORQName + ">", null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1698 */         MQMessage msg = retrieveMessage(msgRef);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1705 */         this.puttingToBORQ = true;
/*      */         
/* 1707 */         put1WithContext(msg, this.mqBORQName, null);
/*      */ 
/*      */         
/* 1710 */         this.puttingToBORQ = false;
/*      */         
/* 1712 */         this.mqQueueManager.commit();
/*      */       }
/* 1714 */       catch (MQException mqe) {
/* 1715 */         if (Trace.isOn) {
/* 1716 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "backoutRequeue(MQMessageReference)", (Throwable)mqe, 2);
/*      */         }
/*      */         
/* 1719 */         if (mqe.reasonCode == 2033) {
/*      */           
/* 1721 */           if (Trace.isOn) {
/* 1722 */             Trace.traceData(this, "ProviderMessage to requeue has disappeared! Continue as normal", null);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1727 */           if (Trace.isOn) {
/* 1728 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "backoutRequeue(MQMessageReference)", 1);
/*      */           }
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/*      */         try {
/* 1736 */           this.mqQueueManager.backout();
/*      */         }
/* 1738 */         catch (MQException mqe2) {
/* 1739 */           if (Trace.isOn) {
/* 1740 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "backoutRequeue(MQMessageReference)", (Throwable)mqe2, 3);
/*      */           }
/*      */ 
/*      */           
/* 1744 */           if (Trace.isOn) {
/* 1745 */             Trace.traceData(this, "WARNING: backout after failed requeue failed!", null);
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1753 */         JMSException jmse = ConfigEnvironment.newException("MQJMS1081");
/* 1754 */         jmse.setLinkedException((Exception)mqe);
/* 1755 */         if (Trace.isOn) {
/* 1756 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "backoutRequeue(MQMessageReference)", (Throwable)jmse, 2);
/*      */         }
/*      */         
/* 1759 */         throw jmse;
/*      */       }
/*      */     
/*      */     }
/* 1763 */     catch (JMSException je) {
/* 1764 */       if (Trace.isOn) {
/* 1765 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "backoutRequeue(MQMessageReference)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 1768 */       if (Trace.isOn) {
/* 1769 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "backoutRequeue(MQMessageReference)", (Throwable)je, 3);
/*      */       }
/*      */       
/* 1772 */       throw je;
/*      */     } 
/* 1774 */     if (Trace.isOn) {
/* 1775 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "backoutRequeue(MQMessageReference)", 2);
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
/*      */   private void discard(MQMessageReference msgRef, int reason) throws JMSException {
/* 1798 */     if (Trace.isOn) {
/* 1799 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "discard(MQMessageReference,int)", new Object[] { msgRef, 
/* 1800 */             Integer.valueOf(reason) });
/*      */     }
/* 1802 */     MQMessage discardMessage = null;
/* 1803 */     MQMessage reportMessage = null;
/*      */ 
/*      */     
/*      */     try {
/* 1807 */       discardMessage = retrieveMessage(msgRef);
/*      */       
/* 1809 */       if ((discardMessage.report & 0x7000000) != 0) {
/*      */ 
/*      */         
/* 1812 */         reportMessage = generateReport(discardMessage, reason);
/*      */         
/* 1814 */         String replyQ = discardMessage.replyToQueueName.trim();
/*      */         
/* 1816 */         String replyQMgr = discardMessage.replyToQueueManagerName.trim();
/*      */ 
/*      */         
/*      */         try {
/* 1820 */           put1WithContext(reportMessage, replyQ, replyQMgr);
/*      */         }
/* 1822 */         catch (MQException mqe) {
/* 1823 */           if (Trace.isOn) {
/* 1824 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "discard(MQMessageReference,int)", (Throwable)mqe, 1);
/*      */           }
/*      */ 
/*      */           
/* 1828 */           if (Trace.isOn) {
/* 1829 */             Trace.traceData(this, "Could not put report message to replyToQ", null);
/*      */ 
/*      */             
/* 1832 */             Trace.traceData(this, "Attempting to put to DLQ instead", null);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1841 */           String mqDLQName = Utils.inquireString((MQManagedObject)this.mqQueueManager, 2006);
/*      */           
/* 1843 */           if (Trace.isOn) {
/* 1844 */             Trace.traceData(this, "DLQ = <" + mqDLQName + ">", null);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1851 */           if (mqDLQName == null || mqDLQName.length() == 0) {
/* 1852 */             if (Trace.isOn) {
/* 1853 */               Trace.traceData(this, "Unable to find DLQ name - is one defined?", null);
/*      */             }
/*      */ 
/*      */             
/* 1857 */             JMSException je = ConfigEnvironment.newException("MQJMS1079");
/* 1858 */             if (Trace.isOn) {
/* 1859 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "discard(MQMessageReference,int)", (Throwable)je, 1);
/*      */             }
/*      */ 
/*      */             
/* 1863 */             throw je;
/*      */           } 
/*      */ 
/*      */           
/* 1867 */           DLH dlh = new DLH();
/* 1868 */           dlh.reason = mqe.reasonCode;
/* 1869 */           dlh.destQName = replyQ;
/* 1870 */           dlh.destQMgrName = replyQMgr;
/* 1871 */           dlh.putApplType = 28;
/* 1872 */           dlh.putApplName = "MQ JMS ConnectionConsumer";
/*      */ 
/*      */ 
/*      */           
/* 1876 */           GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
/* 1877 */           dlh.putDate = Utils.getDate(gc);
/* 1878 */           dlh.putTime = Utils.getTime(gc);
/*      */ 
/*      */           
/* 1881 */           dlh.write(reportMessage);
/*      */ 
/*      */           
/* 1884 */           put1WithContext(reportMessage, mqDLQName, null);
/*      */         }
/*      */       
/*      */       } 
/* 1888 */     } catch (MQException mqe) {
/* 1889 */       if (Trace.isOn) {
/* 1890 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "discard(MQMessageReference,int)", (Throwable)mqe, 2);
/*      */       }
/*      */ 
/*      */       
/* 1894 */       if (mqe.reasonCode == 2033) {
/*      */         
/* 1896 */         if (Trace.isOn) {
/* 1897 */           Trace.traceData(this, "ProviderMessage to discard has disappeared! Continue as normal", null);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1907 */       else if (discardMessage != null && discardMessage.persistence == 0) {
/*      */         
/* 1909 */         if (Trace.isOn) {
/* 1910 */           Trace.traceData(this, "Could not send report message, but message is non-persistent - so subsume the error", null);
/*      */         }
/*      */       } else {
/*      */ 
/*      */         
/*      */         try {
/*      */ 
/*      */ 
/*      */           
/* 1919 */           this.mqQueueManager.backout();
/*      */         }
/* 1921 */         catch (MQException mqe2) {
/* 1922 */           if (Trace.isOn) {
/* 1923 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "discard(MQMessageReference,int)", (Throwable)mqe2, 3);
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1930 */         JMSException je = ConfigEnvironment.newException("MQJMS1082");
/* 1931 */         je.setLinkedException((Exception)mqe);
/*      */         
/* 1933 */         if (Trace.isOn) {
/* 1934 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "discard(MQMessageReference,int)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 1937 */         throw je;
/*      */       }
/*      */     
/* 1940 */     } catch (JMSException je) {
/* 1941 */       if (Trace.isOn) {
/* 1942 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "discard(MQMessageReference,int)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */       
/* 1946 */       if (discardMessage != null && discardMessage.persistence == 0) {
/*      */         
/* 1948 */         if (Trace.isOn) {
/* 1949 */           Trace.traceData(this, "ProviderMessage is non-persistent so ignoring error", null);
/*      */         }
/*      */       } else {
/*      */ 
/*      */         
/*      */         try {
/*      */           
/* 1956 */           this.mqQueueManager.backout();
/*      */         }
/* 1958 */         catch (MQException mqe) {
/* 1959 */           if (Trace.isOn) {
/* 1960 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "discard(MQMessageReference,int)", (Throwable)mqe, 5);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1965 */         if (Trace.isOn) {
/* 1966 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "discard(MQMessageReference,int)", (Throwable)je, 3);
/*      */         }
/*      */         
/* 1969 */         throw je;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 1975 */       this.mqQueueManager.commit();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1980 */       if (reason == 2364)
/*      */       {
/* 1982 */         HashMap<Object, Object> inserts = new HashMap<>();
/* 1983 */         inserts.put("XMSC_INSERT_MESSAGE_ID", 
/* 1984 */             Utils.bytesToHex(discardMessage.messageId));
/* 1985 */         inserts.put("XMSC_INSERT_DESTINATION_NAME", this.qName);
/* 1986 */         inserts.put("XMSC_INSERT_QUEUE_MANAGER_NAME", this.mqQueueManager.name);
/*      */         
/* 1988 */         Log.log(this, "discard()", "MQJMS1117", inserts);
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1993 */     catch (MQException mqe) {
/* 1994 */       if (Trace.isOn) {
/* 1995 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "discard(MQMessageReference,int)", (Throwable)mqe, 6);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2000 */       JMSException je = ConfigEnvironment.newException("MQJMS1082");
/* 2001 */       je.setLinkedException((Exception)mqe);
/* 2002 */       if (Trace.isOn) {
/* 2003 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "discard(MQMessageReference,int)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 2006 */       throw je;
/*      */     } 
/* 2008 */     if (Trace.isOn) {
/* 2009 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "discard(MQMessageReference,int)");
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
/*      */   private MQMessage generateReport(MQMessage msg, int reason) throws JMSException {
/* 2030 */     if (Trace.isOn) {
/* 2031 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "generateReport(MQMessage,int)", new Object[] { msg, 
/* 2032 */             Integer.valueOf(reason) });
/*      */     }
/*      */     
/* 2035 */     MQMessage out = new MQMessage();
/*      */ 
/*      */ 
/*      */     
/* 2039 */     out.report = 0;
/* 2040 */     out.messageType = 4;
/* 2041 */     out.expiry = -1;
/* 2042 */     out.feedback = reason;
/* 2043 */     out.encoding = msg.encoding;
/* 2044 */     out.characterSet = msg.characterSet;
/* 2045 */     out.format = msg.format;
/* 2046 */     out.priority = msg.priority;
/* 2047 */     out.persistence = msg.persistence;
/*      */     
/* 2049 */     if ((msg.report & 0x80) == 128) {
/* 2050 */       out.messageId = msg.messageId;
/*      */     }
/*      */ 
/*      */     
/* 2054 */     if ((msg.report & 0x40) == 64) {
/* 2055 */       out.correlationId = msg.correlationId;
/*      */     } else {
/*      */       
/* 2058 */       out.correlationId = msg.messageId;
/*      */     } 
/*      */     
/* 2061 */     out.backoutCount = 0;
/* 2062 */     out.replyToQueueName = "";
/* 2063 */     out.replyToQueueManagerName = this.qmgrName;
/* 2064 */     out.putApplicationType = 28;
/* 2065 */     out.putApplicationName = "MQ JMS ConnectionConsumer";
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2070 */       if ((msg.report & 0x7000000) == 117440512) {
/* 2071 */         byte[] buffer = new byte[msg.getMessageLength()];
/* 2072 */         msg.seek(0);
/* 2073 */         msg.readFully(buffer);
/* 2074 */         out.write(buffer);
/* 2075 */       } else if ((msg.report & 0x3000000) == 50331648) {
/*      */ 
/*      */ 
/*      */         
/* 2079 */         msg.seek(0);
/*      */         
/* 2081 */         int bytesread = 0;
/* 2082 */         String format = msg.format;
/* 2083 */         int origCharacterSet = msg.characterSet;
/* 2084 */         int origEncoding = msg.encoding;
/* 2085 */         boolean done = false;
/*      */         
/* 2087 */         int length = 0;
/* 2088 */         int nextEncoding = 0;
/* 2089 */         int nextCharacterSet = 0;
/* 2090 */         String nextFormat = null;
/*      */         
/* 2092 */         while (!done) {
/*      */           try {
/* 2094 */             if (format.startsWith("MQH")) {
/*      */               
/* 2096 */               msg.skipBytes(8);
/* 2097 */               length = msg.readInt();
/* 2098 */               nextEncoding = msg.readInt();
/* 2099 */               nextCharacterSet = msg.readInt();
/* 2100 */               nextFormat = msg.readStringOfByteLength(8);
/* 2101 */               msg.skipBytes(length - 28);
/*      */             }
/* 2103 */             else if (format.equals("MQDEAD  ")) {
/*      */               
/* 2105 */               length = 172;
/* 2106 */               msg.skipBytes(108);
/*      */               
/* 2108 */               nextEncoding = msg.readInt();
/* 2109 */               nextCharacterSet = msg.readInt();
/* 2110 */               nextFormat = msg.readStringOfByteLength(8);
/* 2111 */               msg.skipBytes(48);
/* 2112 */             } else if (format.equals("MQXMIT  ")) {
/*      */               
/* 2114 */               length = 428;
/*      */               
/* 2116 */               msg.skipBytes(128);
/* 2117 */               nextEncoding = msg.readInt();
/* 2118 */               nextCharacterSet = msg.readInt();
/* 2119 */               nextFormat = msg.readStringOfByteLength(8);
/* 2120 */               msg.skipBytes(284);
/* 2121 */             } else if (format.equals("MQCICS  ")) {
/*      */               
/* 2123 */               msg.skipBytes(8);
/* 2124 */               length = msg.readInt();
/* 2125 */               msg.skipBytes(8);
/* 2126 */               nextFormat = msg.readStringOfByteLength(8);
/* 2127 */               nextEncoding = msg.encoding;
/* 2128 */               nextCharacterSet = msg.characterSet;
/* 2129 */               msg.skipBytes(length - 36);
/*      */             } else {
/*      */               
/* 2132 */               done = true;
/*      */             } 
/*      */             
/* 2135 */             if (!done)
/*      */             {
/* 2137 */               bytesread += length;
/* 2138 */               format = nextFormat;
/* 2139 */               msg.encoding = nextEncoding;
/* 2140 */               msg.characterSet = nextCharacterSet;
/*      */             }
/*      */           
/* 2143 */           } catch (IOException ioe) {
/* 2144 */             if (Trace.isOn) {
/* 2145 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "generateReport(MQMessage,int)", ioe, 1);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2153 */             done = true;
/*      */           } 
/*      */         } 
/*      */         
/* 2157 */         msg.encoding = origEncoding;
/* 2158 */         msg.characterSet = origCharacterSet;
/*      */         
/* 2160 */         msg.seek(0);
/* 2161 */         byte[] buffer = new byte[Math.min(bytesread + 100, msg
/* 2162 */               .getMessageLength())];
/* 2163 */         msg.readFully(buffer);
/* 2164 */         out.write(buffer);
/*      */       }
/*      */     
/* 2167 */     } catch (IOException ioe) {
/* 2168 */       if (Trace.isOn) {
/* 2169 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "generateReport(MQMessage,int)", ioe, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2174 */       JMSException jmse = ConfigEnvironment.newException("MQJMS1016");
/* 2175 */       jmse.setLinkedException(ioe);
/* 2176 */       if (Trace.isOn) {
/* 2177 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "generateReport(MQMessage,int)", (Throwable)jmse);
/*      */       }
/*      */       
/* 2180 */       throw jmse;
/*      */     } 
/*      */ 
/*      */     
/* 2184 */     if (Trace.isOn) {
/* 2185 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "generateReport(MQMessage,int)", out);
/*      */     }
/*      */     
/* 2188 */     return out;
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
/*      */   private MQMessage retrieveMessage(MQMessageReference msgRef) throws MQException {
/* 2207 */     if (Trace.isOn) {
/* 2208 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "retrieveMessage(MQMessageReference)", new Object[] { msgRef });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2213 */     MQMessage message = new MQMessage();
/* 2214 */     message.messageId = msgRef.getMessageId();
/*      */     
/* 2216 */     MQGetMessageOptions gmo = new MQGetMessageOptions();
/* 2217 */     gmo.options = 2;
/*      */     
/* 2219 */     if (msgRef
/* 2220 */       .checkIfOptionalFieldIsPresent((byte)1) == true) {
/* 2221 */       System.arraycopy(msgRef.getMsgToken(), 0, gmo.msgToken, 0, 16);
/*      */       
/* 2223 */       gmo.matchOptions = 32;
/*      */     } else {
/* 2225 */       gmo.matchOptions = 1;
/*      */     } 
/*      */     
/* 2228 */     this.mqQueue.get(message, gmo);
/*      */     
/* 2230 */     if (Trace.isOn) {
/* 2231 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "retrieveMessage(MQMessageReference)", message);
/*      */     }
/*      */     
/* 2234 */     return message;
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
/*      */   private void put1WithContext(MQMessage msg, String qName, String qmgrName) throws MQException {
/* 2257 */     if (Trace.isOn) {
/* 2258 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "put1WithContext(MQMessage,String,String)", new Object[] { msg, qName, qmgrName });
/*      */     }
/*      */     
/* 2261 */     MQQueue destQ = null;
/*      */ 
/*      */     
/*      */     try {
/* 2265 */       int openOptions = 8720;
/*      */       
/* 2267 */       destQ = this.mqQueueManager.accessQueue(qName, openOptions, qmgrName, null, null);
/*      */ 
/*      */ 
/*      */       
/* 2271 */       MQPutMessageOptions pmo = new MQPutMessageOptions();
/* 2272 */       pmo.options = 514;
/* 2273 */       pmo.contextReference = this.mqQueue;
/* 2274 */       destQ.put(msg, pmo);
/*      */     } finally {
/*      */       
/* 2277 */       if (Trace.isOn) {
/* 2278 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "put1WithContext(MQMessage,String,String)");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2284 */       if (destQ != null && destQ.isOpen()) {
/*      */         try {
/* 2286 */           destQ.close();
/*      */         }
/* 2288 */         catch (MQException mqe) {
/* 2289 */           if (Trace.isOn) {
/* 2290 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "put1WithContext(MQMessage,String,String)", (Throwable)mqe);
/*      */           }
/*      */ 
/*      */           
/* 2294 */           if (Trace.isOn) {
/* 2295 */             Trace.traceData(this, "Could not close destination queue. Carrying on regardless.", null);
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2304 */     if (Trace.isOn) {
/* 2305 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "put1WithContext(MQMessage,String,String)");
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
/*      */   public void run() {
/* 2323 */     if (Trace.isOn) {
/* 2324 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "run()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 2329 */       this.queueAgentThreadThread = Thread.currentThread();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2336 */         setup();
/*      */       }
/* 2338 */       catch (JMSException je) {
/* 2339 */         if (Trace.isOn) {
/* 2340 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "run()", (Throwable)je, 1);
/*      */         }
/*      */         
/* 2343 */         if (Trace.isOn) {
/* 2344 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "run()", (Throwable)je, 1);
/*      */         }
/*      */         
/* 2347 */         throw je;
/*      */       } 
/*      */       
/* 2350 */       if (Trace.isOn) {
/* 2351 */         Trace.traceData(this, "setup() returned", null);
/*      */       }
/*      */       
/* 2354 */       int waitTime = 5000;
/*      */ 
/*      */       
/* 2357 */       synchronized (this) {
/* 2358 */         this.active = true;
/* 2359 */         if (Trace.isOn) {
/* 2360 */           Trace.traceData(this, "Notifying waiting thread", null);
/*      */         }
/* 2362 */         notifyAll();
/*      */       } 
/*      */       
/* 2365 */       if (Trace.isOn) {
/* 2366 */         Trace.traceData(this, "Waiting thread notified", null);
/* 2367 */         Trace.traceData(this, "quit flag is " + (this.quit ? "true" : "false"), null);
/*      */       } 
/*      */ 
/*      */       
/* 2371 */       while (!this.quit) {
/* 2372 */         if (Trace.isOn) {
/* 2373 */           Trace.traceData(this, "Starting to browse message", null);
/*      */         }
/*      */         
/* 2376 */         this.newCount = 0;
/* 2377 */         this.oldCount = 0;
/* 2378 */         this.failedCount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2383 */         if (this.mqQueueAgent.configChangePending() && 
/* 2384 */           updateConfig()) {
/* 2385 */           this.mqQueueAgent.performConfigChange();
/*      */         }
/*      */ 
/*      */         
/* 2389 */         MQMessageReference messageRef = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/* 2398 */           messageRef = browse(this.mqQueueAgent.getRequiredDataQuantity(), waitTime);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2406 */           if (messageRef != null) {
/* 2407 */             if (Trace.isOn) {
/* 2408 */               Trace.traceData(this, "Got message to process", null);
/*      */             }
/* 2410 */             processMessage(messageRef);
/*      */             
/* 2412 */             this.lastException = null; continue;
/*      */           } 
/* 2414 */           if (Trace.isOn) {
/* 2415 */             Trace.traceData(this, "No message received", null);
/*      */ 
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 2421 */         catch (JMSException jmsException) {
/* 2422 */           if (Trace.isOn) {
/* 2423 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "run()", (Throwable)jmsException, 2);
/*      */           }
/*      */ 
/*      */           
/* 2427 */           if (Trace.isOn) {
/* 2428 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "run()", (Throwable)jmsException, 6);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2435 */           Exception linkedException = jmsException.getLinkedException();
/* 2436 */           if (linkedException instanceof MQException && 
/*      */             
/* 2438 */             Reason.isConnectionBroken(((MQException)linkedException)
/* 2439 */               .getReason())) {
/* 2440 */             if (Trace.isOn) {
/* 2441 */               Trace.traceData(this, "Connection Broken exception", null);
/*      */               
/* 2443 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "run()", (Throwable)jmsException, 2);
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2450 */             if (Trace.isOn) {
/* 2451 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "run()", (Throwable)jmsException, 2);
/*      */             }
/*      */ 
/*      */             
/* 2455 */             throw jmsException;
/*      */           } 
/*      */           
/* 2458 */           if (Trace.isOn) {
/* 2459 */             Trace.traceData(this, "Not a Connection Broken exception", null);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2466 */           if (this.lastException == null || !isSameException(this.lastException, jmsException)) {
/* 2467 */             this.lastException = jmsException;
/* 2468 */             this.mqQueueAgent.deliverException(jmsException);
/*      */           } 
/*      */ 
/*      */           
/* 2472 */           if (shouldThreadSleep(linkedException)) {
/*      */             
/*      */             try {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2481 */               if (Trace.isOn) {
/* 2482 */                 Trace.traceData(this, "Sleeping for " + this.sleeptime + " ms, before executing browse", null);
/*      */               }
/*      */ 
/*      */               
/* 2486 */               Thread.sleep(this.sleeptime);
/*      */             }
/* 2488 */             catch (InterruptedException ie) {
/* 2489 */               if (Trace.isOn) {
/* 2490 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "run()", ie, 3);
/*      */               }
/*      */               
/* 2493 */               if (Trace.isOn) {
/* 2494 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "run()", ie, 7);
/*      */               }
/*      */             }
/*      */           
/*      */           }
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 2504 */     catch (JMSException je) {
/* 2505 */       if (Trace.isOn) {
/* 2506 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "run()", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2512 */       if (Trace.isOn) {
/* 2513 */         Trace.traceData(this, "Fatal error duing run() - leaving exception for another thread to pick up", null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2519 */       this.exception = je;
/*      */     }
/* 2521 */     catch (Exception e) {
/* 2522 */       if (Trace.isOn) {
/* 2523 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "run()", e, 5);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2528 */       JMSException je = ConfigEnvironment.newException("MQJMS1016");
/* 2529 */       je.setLinkedException(e);
/* 2530 */       this.exception = je;
/*      */     } finally {
/*      */       
/* 2533 */       if (Trace.isOn) {
/* 2534 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "run()");
/*      */       }
/*      */ 
/*      */       
/* 2538 */       if (Trace.isOn) {
/* 2539 */         Trace.traceData(this, "QueueAgent about to stop; start consumers", null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2549 */       this.mqQueueAgent.startBrowsers();
/*      */ 
/*      */       
/* 2552 */       if (Trace.isOn) {
/* 2553 */         Trace.traceData(this, "new message count    : " + this.newCount, null);
/*      */         
/* 2555 */         Trace.traceData(this, "old message count    : " + this.oldCount, null);
/*      */         
/* 2557 */         Trace.traceData(this, "failed message count : " + this.failedCount, null);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2563 */         shutdown();
/*      */       }
/* 2565 */       catch (JMSException je) {
/* 2566 */         if (Trace.isOn) {
/* 2567 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "run()", (Throwable)je, 6);
/*      */         }
/*      */ 
/*      */         
/* 2571 */         if (this.exception == null) {
/* 2572 */           this.exception = je;
/*      */         }
/*      */       }
/* 2575 */       catch (Exception e) {
/* 2576 */         if (Trace.isOn) {
/* 2577 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "run()", e, 7);
/*      */         }
/*      */ 
/*      */         
/* 2581 */         if (this.exception == null) {
/* 2582 */           this
/* 2583 */             .exception = ConfigEnvironment.newException("MQJMS1016");
/* 2584 */           this.exception.setLinkedException(e);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2589 */       synchronized (this) {
/* 2590 */         this.active = false;
/* 2591 */         notifyAll();
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2600 */       if (this.exception != null) {
/* 2601 */         this.mqQueueAgent.deliverException(this.exception);
/*      */       }
/*      */     } 
/*      */     
/* 2605 */     if (Trace.isOn) {
/* 2606 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "run()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFullMessages(boolean newVal) {
/* 2613 */     if (Trace.isOn) {
/* 2614 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "setFullMessages(boolean)", "setter", 
/* 2615 */           Boolean.valueOf(newVal));
/*      */     }
/* 2617 */     this.fullMessages = newVal;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getFullMessages() {
/* 2622 */     if (Trace.isOn) {
/* 2623 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "getFullMessages()", "getter", 
/* 2624 */           Boolean.valueOf(this.fullMessages));
/*      */     }
/* 2626 */     return this.fullMessages;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void deliverNow() {
/* 2636 */     if (Trace.isOn) {
/* 2637 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "deliverNow()");
/*      */     }
/*      */     
/* 2640 */     if (this.messagesToDeliver) {
/* 2641 */       this.mqQueueAgent.startBrowsers();
/* 2642 */       this.messagesToDeliver = false;
/*      */     } 
/* 2644 */     if (Trace.isOn) {
/* 2645 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "deliverNow()");
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
/*      */   private boolean isSameException(JMSException oldException, JMSException newException) {
/* 2659 */     if (Trace.isOn) {
/* 2660 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "isSameException(JMSException,JMSException)", new Object[] { oldException, newException });
/*      */     }
/*      */     
/* 2663 */     if (Trace.isOn) {
/* 2664 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "isSameException()", new Object[] { oldException, newException });
/*      */     }
/*      */     
/* 2667 */     boolean isSameException = false;
/* 2668 */     Exception oldLinkedException = oldException.getLinkedException();
/* 2669 */     Exception newLinkedException = newException.getLinkedException();
/*      */ 
/*      */     
/* 2672 */     if (oldLinkedException != null && newLinkedException != null) {
/*      */ 
/*      */       
/* 2675 */       if (oldLinkedException instanceof MQException && newLinkedException instanceof MQException) {
/*      */         
/* 2677 */         if (Trace.isOn) {
/* 2678 */           Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "isSameException()", "Checking if MQExceptions reason codes are equal");
/*      */         }
/*      */ 
/*      */         
/* 2682 */         if (((MQException)oldLinkedException).getReason() == ((MQException)newLinkedException).getReason())
/*      */         {
/* 2684 */           isSameException = true;
/*      */         }
/*      */       } else {
/*      */         
/* 2688 */         if (Trace.isOn) {
/* 2689 */           Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "isSameException()", "Checking if LinkedExceptions messages are equal");
/*      */         }
/*      */         
/* 2692 */         if (oldLinkedException.getMessage() != null && oldLinkedException
/* 2693 */           .getMessage().equals(newLinkedException.getMessage())) {
/* 2694 */           isSameException = true;
/*      */         }
/*      */       } 
/* 2697 */     } else if (oldLinkedException == null && newLinkedException == null) {
/*      */ 
/*      */       
/* 2700 */       if (Trace.isOn) {
/* 2701 */         Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "isSameException()", "No LinkedException, checking JMSException error code");
/*      */       }
/*      */       
/* 2704 */       if (oldException.getMessage() != null && oldException
/* 2705 */         .getMessage().equals(newException.getMessage())) {
/* 2706 */         isSameException = true;
/*      */       }
/*      */     } 
/*      */     
/* 2710 */     if (Trace.isOn)
/* 2711 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "isSameException()", new Object[] {
/* 2712 */             Boolean.valueOf(isSameException)
/*      */           }); 
/* 2714 */     if (Trace.isOn) {
/* 2715 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "isSameException(JMSException,JMSException)", 
/* 2716 */           Boolean.valueOf(isSameException));
/*      */     }
/* 2718 */     return isSameException;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean shouldThreadSleep(Exception exception) {
/* 2729 */     if (Trace.isOn) {
/* 2730 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "shouldThreadSleep(Exception)", new Object[] { exception });
/*      */     }
/*      */     
/* 2733 */     if (Trace.isOn) {
/* 2734 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "shouldThreadSleep()", new Object[] { exception });
/*      */     }
/*      */ 
/*      */     
/* 2738 */     boolean shouldSleep = false;
/* 2739 */     if (exception instanceof MQException) {
/* 2740 */       int reason = ((MQException)exception).getReason();
/* 2741 */       switch (reason) {
/*      */         case 2016:
/* 2743 */           shouldSleep = true;
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 2750 */     if (Trace.isOn)
/* 2751 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "shouldThreadSleep()", new Object[] {
/* 2752 */             Boolean.valueOf(shouldSleep)
/*      */           }); 
/* 2754 */     if (Trace.isOn) {
/* 2755 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread", "shouldThreadSleep(Exception)", 
/* 2756 */           Boolean.valueOf(shouldSleep));
/*      */     }
/* 2758 */     return shouldSleep;
/*      */   }
/*      */   
/*      */   abstract MQMessageReference browse(int paramInt, long paramLong) throws JMSException;
/*      */   
/*      */   abstract boolean updateConfig() throws JMSException;
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQQueueAgentThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */