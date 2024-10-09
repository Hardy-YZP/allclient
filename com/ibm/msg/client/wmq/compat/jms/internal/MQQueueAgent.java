/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.disthub2.impl.matching.BadMessageFormatMatchingException;
/*      */ import com.ibm.disthub2.impl.matching.FormattedMessage;
/*      */ import com.ibm.disthub2.impl.matching.InvalidTopicSyntaxException;
/*      */ import com.ibm.disthub2.impl.matching.MatchSpace;
/*      */ import com.ibm.disthub2.impl.matching.MatchingContext;
/*      */ import com.ibm.disthub2.impl.matching.MatchingException;
/*      */ import com.ibm.disthub2.impl.matching.QuerySyntaxException;
/*      */ import com.ibm.disthub2.impl.matching.selector.EvalContext;
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class MQQueueAgent
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQQueueAgent.java";
/*      */   private String mqQmgrName;
/*      */   private String mqQueueName;
/*      */   static final int DOMAIN_PTP = 0;
/*      */   static final int DOMAIN_PS = 1;
/*      */   private int domainFlag;
/*      */   static final int DELIVERED = 0;
/*      */   static final int NOT_DELIVERED = 1;
/*      */   static final int MSG_SELECTOR_FAILED = 2;
/*      */   static final int NO_CONSUMER = 3;
/*      */   
/*      */   static {
/*   97 */     if (Trace.isOn) {
/*   98 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQQueueAgent.java");
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
/*  127 */   Vector browsers = new Vector();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean allBrowsersReceiving = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  140 */   private int activeCount = 0;
/*      */ 
/*      */   
/*  143 */   private MQQueueAgentThread thread = null;
/*      */   
/*      */   private static final int QAT_STOPPED = 0;
/*      */   
/*      */   private static final int QAT_STARTED = 1;
/*      */   
/*      */   private static final int QAT_STOPPING = 2;
/*      */   
/*  151 */   private int threadStatus = 0;
/*      */ 
/*      */   
/*  154 */   private Object threadLock = new Object();
/*      */ 
/*      */ 
/*      */   
/*      */   static final int defWaitTime = 5000;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean useCorrelId = true;
/*      */ 
/*      */ 
/*      */   
/*      */   static final String EXCLUSIVE_SUB_KEY = "XXX-Exclusive-Subscriber-Key-XXX";
/*      */ 
/*      */ 
/*      */   
/*      */   boolean retainAllMessages = false;
/*      */ 
/*      */   
/*      */   private int version;
/*      */ 
/*      */   
/*  176 */   private Hashtable pubSubBrowserLookup = new Hashtable<>();
/*      */   
/*      */   private static final int MQQUEUE_AGENT_V1 = 1;
/*      */   
/*      */   private static final int MQQUEUE_AGENT_V2 = 2;
/*      */   
/*  182 */   int useSelectors = 0;
/*      */ 
/*      */   
/*  185 */   int needData = 0;
/*      */ 
/*      */   
/*  188 */   int quantityRequired = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MatchSpace matchSpace;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private WMQDestination providerDestination;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final String PTPTOPIC = "PtPTopic";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static MQQueueAgent getQueueAgent(MQConnection connection, WMQDestination dest, String queueName, boolean correlIdAsIdentity) throws JMSException {
/*  210 */     if (Trace.isOn) {
/*  211 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "getQueueAgent(MQConnection,WMQDestination,String,boolean)", new Object[] { connection, dest, queueName, 
/*      */             
/*  213 */             Boolean.valueOf(correlIdAsIdentity) });
/*      */     }
/*  215 */     MQQueueAgent result = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*      */       int domain;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  229 */       if (dest.isQueue()) {
/*  230 */         domain = 0;
/*  231 */       } else if (dest.isTopic()) {
/*  232 */         domain = 1;
/*      */       } else {
/*  234 */         JMSException traceRet1 = ConfigEnvironment.newException("MQJMS1088");
/*  235 */         if (Trace.isOn) {
/*  236 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "getQueueAgent(MQConnection,WMQDestination,String,boolean)", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/*  239 */         throw traceRet1;
/*      */       } 
/*  241 */       MQSession sess = (MQSession)connection.createSessionInternal(true, 0, 0);
/*      */       
/*  243 */       String qmName = resolveQmgrName(sess);
/*  244 */       String qmID = resolveQmgrID(sess, qmName);
/*  245 */       String qName = resolveQueue(sess, queueName);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  251 */       String providerConnUID = Integer.toHexString(System.identityHashCode(connection));
/*      */       
/*  253 */       String agentId = qmID + "/" + qName + "/" + providerConnUID;
/*      */       
/*  255 */       sess.close(false);
/*      */       
/*  257 */       if (Trace.isOn) {
/*  258 */         Trace.traceData("MQQueueAgent", "QueueAgent ID is " + agentId, null);
/*      */       }
/*      */ 
/*      */       
/*  262 */       result = new MQQueueAgent(qmName, queueName, agentId, domain, correlIdAsIdentity);
/*      */ 
/*      */ 
/*      */       
/*  266 */       result.setProviderDestination(dest);
/*      */       
/*  268 */       if (Trace.isOn) {
/*  269 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "getQueueAgent(MQConnection,WMQDestination,String,boolean)", result);
/*      */       }
/*      */       
/*  272 */       return result;
/*      */     
/*      */     }
/*  275 */     catch (JMSException je) {
/*  276 */       if (Trace.isOn) {
/*  277 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "getQueueAgent(MQConnection,WMQDestination,String,boolean)", (Throwable)je);
/*      */       }
/*      */       
/*  280 */       if (Trace.isOn) {
/*  281 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "getQueueAgent(MQConnection,WMQDestination,String,boolean)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  284 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void setProviderDestination(WMQDestination dest) {
/*  290 */     if (Trace.isOn) {
/*  291 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "setProviderDestination(WMQDestination)", "setter", dest);
/*      */     }
/*      */     
/*  294 */     this.providerDestination = dest;
/*      */   }
/*      */ 
/*      */   
/*      */   public WMQDestination getProviderDestination() {
/*  299 */     if (Trace.isOn) {
/*  300 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "getProviderDestination()", "getter", this.providerDestination);
/*      */     }
/*      */     
/*  303 */     return this.providerDestination;
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
/*      */   private MQQueueAgent(String qMgrName, String queueName, String agentId, int domain, boolean correlIdAsIdentity) throws JMSException {
/*  318 */     if (Trace.isOn) {
/*  319 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "<init>(String,String,String,int,boolean)", new Object[] { qMgrName, queueName, agentId, 
/*      */             
/*  321 */             Integer.valueOf(domain), Boolean.valueOf(correlIdAsIdentity) });
/*      */     }
/*      */     
/*  324 */     this.mqQmgrName = qMgrName;
/*  325 */     this.mqQueueName = queueName;
/*  326 */     this.domainFlag = domain;
/*  327 */     this.useCorrelId = correlIdAsIdentity;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  333 */     this.version = 1;
/*  334 */     this.matchSpace = new MatchSpace();
/*  335 */     if (Trace.isOn) {
/*  336 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "<init>(String,String,String,int,boolean)");
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
/*      */   private static String resolveQmgrName(MQSession session) throws JMSException {
/*  349 */     if (Trace.isOn) {
/*  350 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQmgrName(MQSession)", new Object[] { session });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  357 */       MQQueueManager qmgr = session.getQM();
/*      */ 
/*      */       
/*  360 */       String qmName = Utils.inquireString((MQManagedObject)qmgr, 2015);
/*  361 */       if (qmName == null) {
/*      */         
/*  363 */         JMSException e = ConfigEnvironment.newException("MQJMS1072");
/*  364 */         if (Trace.isOn) {
/*  365 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQmgrName(MQSession)", (Throwable)e, 1);
/*      */         }
/*      */         
/*  368 */         throw e;
/*      */       } 
/*      */       
/*  371 */       if (Trace.isOn) {
/*  372 */         Trace.traceData("MQQueueAgent", "Fully resolved Qmgr Name is " + qmName, null);
/*      */       }
/*  374 */       if (Trace.isOn) {
/*  375 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQmgrName(MQSession)", qmName);
/*      */       }
/*      */       
/*  378 */       return qmName;
/*      */     }
/*  380 */     catch (JMSException je) {
/*  381 */       if (Trace.isOn) {
/*  382 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQmgrName(MQSession)", (Throwable)je);
/*      */       }
/*      */       
/*  385 */       if (Trace.isOn) {
/*  386 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQmgrName(MQSession)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  389 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String resolveQmgrID(MQSession session, String qmName) throws JMSException {
/*  400 */     if (Trace.isOn) {
/*  401 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQmgrID(MQSession,String)", new Object[] { session, qmName });
/*      */     }
/*      */     
/*  404 */     if (Trace.isOn) {
/*  405 */       Trace.entry("MQQueueAgent", "resolveQmgrID");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  410 */     MQQueueManager qmgr = session.getQM();
/*      */ 
/*      */     
/*  413 */     String qmID = Utils.inquireString((MQManagedObject)qmgr, 2032);
/*  414 */     if (qmID == null) {
/*  415 */       if (Trace.isOn) {
/*  416 */         Trace.traceData("MQQueueAgent", "Inquire failed - falling back to qmgr name", null);
/*      */       }
/*  418 */       qmID = qmName;
/*      */     } 
/*      */     
/*  421 */     if (Trace.isOn) {
/*  422 */       Trace.traceData("MQQueueAgent", "Qmgr ID " + qmID, null);
/*      */     }
/*  424 */     if (Trace.isOn) {
/*  425 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQmgrID(MQSession,String)", qmID);
/*      */     }
/*      */     
/*  428 */     return qmID;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String resolveQueue(MQSession session, String queueName) throws JMSException {
/*  438 */     if (Trace.isOn) {
/*  439 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQueue(MQSession,String)", new Object[] { session, queueName });
/*      */     }
/*      */ 
/*      */     
/*  443 */     MQQueue q = null;
/*      */     
/*      */     try {
/*      */       int defType;
/*      */       JMSException traceRet2;
/*  448 */       MQQueueManager qmgr = session.getQM();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  454 */         if (Trace.isOn) {
/*  455 */           Trace.traceData("MQQueueAgent", "resolveQueue is opening queue", null);
/*      */         }
/*      */         
/*  458 */         q = qmgr.accessQueue(queueName, 8232);
/*  459 */         if (Trace.isOn) {
/*  460 */           Trace.traceData("MQQueueAgent", "resolveQueue opened queue OK", null);
/*      */         }
/*      */       }
/*  463 */       catch (MQException mqe) {
/*  464 */         if (Trace.isOn) {
/*  465 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQueue(MQSession,String)", (Throwable)mqe, 1);
/*      */         }
/*      */         
/*  468 */         JMSException e = ConfigEnvironment.newException("MQJMS2008", queueName);
/*  469 */         e.setLinkedException((Exception)mqe);
/*  470 */         if (Trace.isOn) {
/*  471 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQueue(MQSession,String)", (Throwable)e, 1);
/*      */         }
/*      */         
/*  474 */         throw e;
/*      */       } 
/*      */ 
/*      */       
/*  478 */       int type = Utils.inquireInt((MQManagedObject)q, 20);
/*      */       
/*  480 */       switch (type) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 1:
/*  491 */           defType = Utils.inquireInt((MQManagedObject)q, 7);
/*  492 */           if (defType != 1) {
/*      */             
/*  494 */             String qName = Utils.inquireString((MQManagedObject)q, 2016);
/*  495 */             if (!queueName.trim().equals(qName.trim())) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  505 */               q.closeOptions = 1;
/*      */               try {
/*  507 */                 q.close();
/*  508 */                 q = null;
/*      */               }
/*  510 */               catch (MQException mqe) {
/*  511 */                 if (Trace.isOn) {
/*  512 */                   Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQueue(MQSession,String)", (Throwable)mqe, 2);
/*      */                 }
/*      */                 
/*  515 */                 if (Trace.isOn) {
/*  516 */                   Trace.traceData("MQQueueAgent", "Warning: could not close queue on implicit close", null);
/*      */                 }
/*      */               } 
/*  519 */               if (Trace.isOn) {
/*  520 */                 Trace.traceData("MQQueueAgent", "Queue is a QMODEL", null);
/*      */               }
/*  522 */               JMSException traceRet1 = ConfigEnvironment.newException("MQJMS1073");
/*  523 */               if (Trace.isOn) {
/*  524 */                 Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQueue(MQSession,String)", (Throwable)traceRet1, 2);
/*      */               }
/*      */               
/*  527 */               throw traceRet1;
/*      */             }  break;
/*  529 */           }  if (Trace.isOn) {
/*  530 */             Trace.traceData("MQQueueAgent", "Queue is a QLOCAL", null);
/*      */           }
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 3:
/*  543 */           if (Trace.isOn) {
/*  544 */             Trace.traceData("MQQueueAgent", "Queue " + queueName + " is a QALIAS", null);
/*      */           }
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/*  560 */           if (Trace.isOn) {
/*  561 */             Trace.traceData("MQQueueAgent", "Unexpected response when determining Queue type: " + type, null);
/*      */           }
/*  563 */           traceRet2 = ConfigEnvironment.newException("MQJMS1073");
/*  564 */           if (Trace.isOn) {
/*  565 */             Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQueue(MQSession,String)", (Throwable)traceRet2, 3);
/*      */           }
/*      */           
/*  568 */           throw traceRet2;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  576 */         if (Trace.isOn) {
/*  577 */           Trace.traceData("MQQueueAgent", "resolveQueue is closing queue", null);
/*      */         }
/*      */         
/*  580 */         q.close();
/*  581 */         q = null;
/*      */         
/*  583 */         if (Trace.isOn) {
/*  584 */           Trace.traceData("MQQueueAgent", "resolveQueue closed queue okay", null);
/*      */         }
/*      */       }
/*  587 */       catch (MQException mqe) {
/*  588 */         if (Trace.isOn) {
/*  589 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQueue(MQSession,String)", (Throwable)mqe, 3);
/*      */         }
/*      */         
/*  592 */         JMSException e = ConfigEnvironment.newException("MQJMS2000");
/*  593 */         if (Trace.isOn) {
/*  594 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQueue(MQSession,String)", (Throwable)e, 4);
/*      */         }
/*      */         
/*  597 */         throw e;
/*      */       } 
/*      */       
/*  600 */       if (Trace.isOn) {
/*  601 */         Trace.traceData("MQQueueAgent", "Fully resolved Queue name is " + queueName, null);
/*      */       }
/*  603 */       if (Trace.isOn) {
/*  604 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQueue(MQSession,String)", queueName);
/*      */       }
/*      */       
/*  607 */       return queueName;
/*      */     
/*      */     }
/*  610 */     catch (JMSException jmse) {
/*  611 */       if (Trace.isOn) {
/*  612 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQueue(MQSession,String)", (Throwable)jmse, 4);
/*      */       }
/*      */       
/*  615 */       if (Trace.isOn) {
/*  616 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQueue(MQSession,String)", (Throwable)jmse, 5);
/*      */       }
/*      */       
/*  619 */       throw jmse;
/*      */     }
/*      */     finally {
/*      */       
/*  623 */       if (Trace.isOn) {
/*  624 */         Trace.finallyBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQueue(MQSession,String)");
/*      */       }
/*      */       
/*      */       try {
/*  628 */         if (q != null) {
/*  629 */           q.close();
/*      */         }
/*      */       }
/*  632 */       catch (MQException mqe) {
/*  633 */         if (Trace.isOn) {
/*  634 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "resolveQueue(MQSession,String)", (Throwable)mqe, 5);
/*      */         }
/*      */         
/*  637 */         if (Trace.isOn) {
/*  638 */           Trace.traceData("MQQueueAgent", "Warning: could not close queue on implicit close", null);
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
/*      */   
/*      */   protected void activate(MQConnection conn) throws JMSException {
/*  656 */     if (Trace.isOn) {
/*  657 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "activate(MQConnection)", new Object[] { conn });
/*      */     }
/*      */ 
/*      */     
/*  661 */     synchronized (this.threadLock) {
/*      */ 
/*      */       
/*  664 */       while (this.threadStatus == 2) {
/*  665 */         if (Trace.isOn) {
/*  666 */           Trace.traceData(this, "Thread stopping...", null);
/*      */         }
/*      */         try {
/*  669 */           this.threadLock.wait();
/*      */         }
/*  671 */         catch (InterruptedException ie) {
/*  672 */           if (Trace.isOn) {
/*  673 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "activate(MQConnection)", ie);
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  679 */       this.activeCount++;
/*  680 */       if (this.threadStatus == 0) {
/*  681 */         JMSException traceRet2; if (null == conn && Trace.isOn) {
/*  682 */           Trace.traceData(this, "connection is null!", null);
/*      */         }
/*      */ 
/*      */         
/*  686 */         if (conn != null && conn.getSupportsQAT2() == true) {
/*  687 */           this.version = 2;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  692 */         String qatVersionProperty = "com.ibm.mq.jms.tuning.QATVersion";
/*  693 */         PropertyStore.register(qatVersionProperty, "");
/*  694 */         String qatVersion = PropertyStore.getStringProperty(qatVersionProperty);
/*      */         
/*  696 */         if (qatVersion != null && (qatVersion.equals("1") || qatVersion.equals("2"))) {
/*  697 */           this.version = Integer.parseInt(qatVersion);
/*      */         }
/*      */         
/*  700 */         if (Trace.isOn) {
/*  701 */           Trace.traceData(this, "Creating MQQueueAgentThread, version " + this.version, null);
/*      */         }
/*  703 */         switch (this.version) {
/*      */ 
/*      */           
/*      */           case 1:
/*  707 */             this.thread = new MQQueueAgentThread1Impl(this, conn, this.mqQmgrName, this.mqQueueName);
/*      */             break;
/*      */           case 2:
/*  710 */             this.thread = new MQQueueAgentThread2Impl(this, conn, this.mqQmgrName, this.mqQueueName);
/*      */             break;
/*      */           default:
/*  713 */             traceRet2 = ConfigEnvironment.newException("MQJMS1099");
/*  714 */             if (Trace.isOn) {
/*  715 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "activate(MQConnection)", (Throwable)traceRet2);
/*      */             }
/*      */             
/*  718 */             throw traceRet2;
/*      */         } 
/*      */         
/*  721 */         this.threadStatus = 1;
/*  722 */         if (this.retainAllMessages) {
/*  723 */           this.thread.retainAllMessages();
/*      */         }
/*  725 */         this.thread.go();
/*      */       
/*      */       }
/*  728 */       else if (this.retainAllMessages) {
/*  729 */         this.thread.retainAllMessages();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  734 */     if (Trace.isOn) {
/*  735 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "activate(MQConnection)");
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
/*      */   protected void deactivate() throws JMSException {
/*  752 */     if (Trace.isOn) {
/*  753 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "deactivate()");
/*      */     }
/*      */ 
/*      */     
/*  757 */     synchronized (this.threadLock) {
/*  758 */       this.activeCount--;
/*  759 */       if (this.activeCount == 0) {
/*      */         
/*  761 */         this.thread.quit();
/*  762 */         this.threadStatus = 2;
/*  763 */         this.retainAllMessages = false;
/*      */       } 
/*      */     } 
/*      */     
/*  767 */     if (Trace.isOn) {
/*  768 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "deactivate()");
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
/*      */   protected boolean configChangePending() {
/*  783 */     if (Trace.isOn) {
/*  784 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "configChangePending()");
/*      */     }
/*      */ 
/*      */     
/*  788 */     boolean traceRet1 = !this.allBrowsersReceiving;
/*  789 */     if (Trace.isOn) {
/*  790 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "configChangePending()", 
/*  791 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  793 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void performConfigChange() {
/*  803 */     if (Trace.isOn) {
/*  804 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "performConfigChange()");
/*      */     }
/*      */ 
/*      */     
/*  808 */     synchronized (this.browsers) {
/*  809 */       if (!this.allBrowsersReceiving) {
/*  810 */         if (Trace.isOn) {
/*  811 */           Trace.traceData(this, "Setting all ConnectionBrowsers to receive messages", null);
/*      */         }
/*  813 */         Enumeration<MQConnectionBrowser> enumVar = this.browsers.elements();
/*  814 */         while (enumVar.hasMoreElements()) {
/*  815 */           MQConnectionBrowser cb = enumVar.nextElement();
/*  816 */           if (!cb.receiveFlag.receiving) {
/*  817 */             cb.receiveFlag.receiving = true;
/*  818 */             if (Trace.isOn) {
/*  819 */               Trace.traceData(this, "ConnectionBrowser dataQuantityHint: ", Integer.valueOf(cb.getQuantityHint()));
/*      */             }
/*  821 */             if (cb.getQuantityHint() != 0) {
/*  822 */               this.needData++;
/*      */             }
/*  824 */             if (cb.getSelectorString() != null && !cb.getSelectorString().equals("")) {
/*  825 */               this.useSelectors++;
/*      */             }
/*      */             
/*  828 */             if (cb.getQuantityHint() == 0 && cb.getSelectorString() != null && !cb.getSelectorString().equals("")) {
/*  829 */               if (Trace.isOn) {
/*  830 */                 Trace.traceData(this, "DataQuantityHint and selector mismatch. Forcing use of FULL_DATA", null);
/*      */               }
/*  832 */               this.quantityRequired = 2; continue;
/*      */             } 
/*  834 */             if (Trace.isOn) {
/*  835 */               Trace.traceData(this, "Using dataQuantityHint as specified. dq: " + cb.getQuantityHint(), null);
/*      */             }
/*  837 */             this.quantityRequired = cb.getQuantityHint();
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  842 */         this.allBrowsersReceiving = true;
/*      */       } 
/*      */     } 
/*      */     
/*  846 */     if (Trace.isOn) {
/*  847 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "performConfigChange()");
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
/*      */   protected void deliverException(JMSException je) {
/*      */     MQConnection[] connections;
/*  878 */     if (Trace.isOn) {
/*  879 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "deliverException(JMSException)", new Object[] { je });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  884 */     int num = 0;
/*      */     
/*  886 */     synchronized (this.browsers) {
/*  887 */       connections = new MQConnection[this.browsers.size()];
/*      */       
/*  889 */       Enumeration<MQConnectionBrowser> enumVar = this.browsers.elements();
/*  890 */       while (enumVar.hasMoreElements()) {
/*  891 */         MQConnectionBrowser browser = enumVar.nextElement();
/*  892 */         MQConnection conn = browser.getConnection();
/*  893 */         boolean found = false;
/*  894 */         for (int j = 0; j < num && !found; j++) {
/*  895 */           if (connections[j] == conn) {
/*  896 */             found = true;
/*      */           }
/*      */         } 
/*  899 */         if (!found) {
/*  900 */           connections[num] = conn;
/*  901 */           num++;
/*      */         } 
/*      */       } 
/*      */     } 
/*  905 */     if (Trace.isOn) {
/*  906 */       Trace.traceData(this, num + " unique Connections using QueueAgent", null);
/*      */     }
/*      */     
/*  909 */     for (int i = 0; i < num; i++) {
/*  910 */       connections[i].deliverException(je);
/*      */     }
/*      */     
/*  913 */     if (Trace.isOn) {
/*  914 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "deliverException(JMSException)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getDomain() {
/*  924 */     if (Trace.isOn) {
/*  925 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "getDomain()", "getter", 
/*  926 */           Integer.valueOf(this.domainFlag));
/*      */     }
/*  928 */     return this.domainFlag;
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
/*      */   protected void addBrowser(MQConnectionBrowser browser, boolean retain) {
/*  942 */     if (Trace.isOn) {
/*  943 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "addBrowser(MQConnectionBrowser,boolean)", new Object[] { browser, 
/*  944 */             Boolean.valueOf(retain) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  952 */     synchronized (this.browsers) {
/*      */       
/*  954 */       if (!this.browsers.contains(browser)) {
/*  955 */         if (Trace.isOn) {
/*  956 */           Trace.traceData(this, "Adding browser to Vector", null);
/*      */         }
/*  958 */         this.browsers.addElement(browser);
/*      */         
/*  960 */         if (this.domainFlag == 0) {
/*  961 */           if (Trace.isOn) {
/*  962 */             Trace.traceData(this, "This is a Point-to-Point agent", null);
/*      */           }
/*      */ 
/*      */           
/*      */           try {
/*  967 */             if (Trace.isOn) {
/*  968 */               Trace.traceData(this, "Adding Browser to MatchSpace", null);
/*  969 */               Trace.traceData(this, "Browser = \n" + browser.toString(), null);
/*      */             } 
/*      */             
/*  972 */             if (null != browser.getSelectorString())
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  980 */               if (Trace.isOn) {
/*  981 */                 Trace.traceData(this, "Passing the browser to the MatchSpace", null);
/*  982 */                 Trace.traceData(this, "Browser = \n" + browser.toString(), null);
/*      */               } 
/*  984 */               this.matchSpace.put("PtPTopic", browser.getSelectorString(), browser, null, null);
/*  985 */               if (Trace.isOn) {
/*  986 */                 Trace.traceData(this, "A copy of the browser has been successfully added to the MatchSpace", null);
/*      */               
/*      */               }
/*      */             }
/*      */           
/*      */           }
/*  992 */           catch (MatchingException e) {
/*  993 */             if (Trace.isOn) {
/*  994 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "addBrowser(MQConnectionBrowser,boolean)", (Throwable)e, 1);
/*      */             }
/*      */             
/*  997 */             if (Trace.isOn) {
/*  998 */               Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "addBrowser(MQConnectionBrowser,boolean)", 1);
/*      */             }
/*      */ 
/*      */             
/*      */             return;
/* 1003 */           } catch (InvalidTopicSyntaxException e) {
/* 1004 */             if (Trace.isOn) {
/* 1005 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "addBrowser(MQConnectionBrowser,boolean)", (Throwable)e, 2);
/*      */             }
/*      */             
/* 1008 */             if (Trace.isOn) {
/* 1009 */               Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "addBrowser(MQConnectionBrowser,boolean)", 2);
/*      */             }
/*      */ 
/*      */             
/*      */             return;
/* 1014 */           } catch (QuerySyntaxException e) {
/* 1015 */             if (Trace.isOn) {
/* 1016 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "addBrowser(MQConnectionBrowser,boolean)", (Throwable)e, 3);
/*      */             }
/*      */             
/* 1019 */             if (Trace.isOn) {
/* 1020 */               Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "addBrowser(MQConnectionBrowser,boolean)", 3);
/*      */             }
/*      */             
/*      */             return;
/*      */           } 
/* 1025 */           if (Trace.isOn) {
/* 1026 */             Trace.traceData(this, "Browser added to MatchSpace.", null);
/*      */           }
/*      */         } else {
/* 1029 */           if (Trace.isOn) {
/* 1030 */             Trace.traceData(this, "This is a PubSub QueueAgent", null);
/*      */           }
/* 1032 */           if (this.useCorrelId) {
/* 1033 */             if (Trace.isOn) {
/* 1034 */               Trace.traceData(this, "Browser is Pub/Sub, and uses correlIdAsIdentity, so storing its correlId in lookup table", null);
/* 1035 */               Trace.traceData(this, "correlId = '" + Utils.bytesToHex(browser.getCorrelId()) + "'", null);
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1045 */             this.pubSubBrowserLookup.put(Utils.bytesToHex(browser.getCorrelId()), browser);
/*      */           } else {
/* 1047 */             if (Trace.isOn) {
/* 1048 */               Trace.traceData(this, "Browser is Pub/Sub, but does NOT use correlIdAsIdentity. correlId storage not required", null);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1054 */             this.pubSubBrowserLookup.put("XXX-Exclusive-Subscriber-Key-XXX", browser);
/*      */           } 
/*      */           
/* 1057 */           if (Trace.isOn) {
/* 1058 */             Trace.traceData(this, "Adding browser with selector: " + browser.getSelectorString(), null);
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1064 */         browser.receiveFlag.receiving = false;
/* 1065 */         this.allBrowsersReceiving = false;
/* 1066 */         if (retain) {
/* 1067 */           this.retainAllMessages = true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1072 */     if (Trace.isOn) {
/* 1073 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "addBrowser(MQConnectionBrowser,boolean)", 4);
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
/*      */   protected void removeBrowser(MQConnectionBrowser browser) throws JMSException {
/* 1090 */     if (Trace.isOn) {
/* 1091 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "removeBrowser(MQConnectionBrowser)", new Object[] { browser });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1100 */     synchronized (this.threadLock) {
/* 1101 */       if (this.threadStatus == 2) {
/* 1102 */         this.thread.waitForEnd();
/* 1103 */         this.threadStatus = 0;
/* 1104 */         this.thread = null;
/* 1105 */         this.threadLock.notifyAll();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1112 */     synchronized (this.browsers) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1121 */       boolean removed = this.browsers.removeElement(browser);
/* 1122 */       if (Trace.isOn) {
/* 1123 */         Trace.traceData(this, "browser " + (removed ? "removed from " : "not found in ") + " Vector.", null);
/*      */       }
/*      */       
/* 1126 */       if (browser.isPubSub()) {
/* 1127 */         if (this.useCorrelId) {
/*      */           
/* 1129 */           this.pubSubBrowserLookup.remove(Utils.bytesToHex(browser.getCorrelId()));
/*      */         } else {
/* 1131 */           this.pubSubBrowserLookup.remove("XXX-Exclusive-Subscriber-Key-XXX");
/*      */         } 
/*      */       } else {
/*      */         
/*      */         try {
/* 1136 */           this.matchSpace.remove(browser);
/*      */         }
/* 1138 */         catch (Exception e) {
/* 1139 */           if (Trace.isOn) {
/* 1140 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "removeBrowser(MQConnectionBrowser)", e);
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1147 */       if (browser.receiveFlag.receiving == true && removed) {
/*      */ 
/*      */         
/* 1150 */         if (browser.getQuantityHint() != 0 && this.needData > 0) {
/* 1151 */           this.needData--;
/*      */         }
/* 1153 */         if (browser.getSelectorString() != null && !browser.getSelectorString().equals("") && this.useSelectors > 0) {
/* 1154 */           this.useSelectors--;
/*      */         }
/* 1156 */         if (this.needData == 0 && this.useSelectors == 0) {
/* 1157 */           if (Trace.isOn) {
/* 1158 */             Trace.traceData(this, "needData and useSelectors are zero. Using MR.NO_DATA", null);
/*      */           }
/* 1160 */           this.quantityRequired = 0;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1165 */     if (Trace.isOn) {
/* 1166 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "removeBrowser(MQConnectionBrowser)");
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
/*      */   protected void startBrowsers() {
/* 1179 */     if (Trace.isOn) {
/* 1180 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "startBrowsers()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1185 */     Enumeration<MQConnectionBrowser> enumVar = this.browsers.elements();
/* 1186 */     while (enumVar.hasMoreElements()) {
/* 1187 */       MQConnectionBrowser browser = enumVar.nextElement();
/* 1188 */       if (browser.getException() == null) {
/* 1189 */         browser.endDeliver();
/*      */       }
/*      */     } 
/* 1192 */     if (Trace.isOn) {
/* 1193 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "startBrowsers()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void getMatches(String topic, MQMessageReference msg, SearchResults result) throws MatchingException, BadMessageFormatMatchingException, JMSException {
/* 1201 */     if (Trace.isOn) {
/* 1202 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "getMatches(String,MQMessageReference,SearchResults)", new Object[] { topic, msg, result });
/*      */     }
/*      */     
/* 1205 */     ProviderMessage msgJMS = msg.getMessage();
/*      */ 
/*      */     
/* 1208 */     MQMsg2 msgMQSeries = msg.getMQJMSMessage();
/*      */     
/* 1210 */     FormattedMessage fMessage = new SelectorDataAccessor(msgJMS, msgMQSeries);
/*      */ 
/*      */ 
/*      */     
/* 1214 */     MatchingContext eContext = new MatchingContext();
/* 1215 */     eContext.setMessage(fMessage);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1220 */     this.matchSpace.get(topic, (EvalContext)eContext, result);
/* 1221 */     if (Trace.isOn) {
/* 1222 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "getMatches(String,MQMessageReference,SearchResults)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Vector getBrowsers() {
/* 1230 */     if (Trace.isOn) {
/* 1231 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "getBrowsers()", "getter", this.browsers);
/*      */     }
/*      */     
/* 1234 */     return this.browsers;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Hashtable getPubSubBrowserLookup() {
/* 1239 */     if (Trace.isOn) {
/* 1240 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "getPubSubBrowserLookup()", "getter", this.pubSubBrowserLookup);
/*      */     }
/*      */     
/* 1243 */     return this.pubSubBrowserLookup;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean getUseCorrelId() {
/* 1248 */     if (Trace.isOn) {
/* 1249 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "getUseCorrelId()", "getter", 
/* 1250 */           Boolean.valueOf(this.useCorrelId));
/*      */     }
/* 1252 */     return this.useCorrelId;
/*      */   }
/*      */ 
/*      */   
/*      */   protected int getRequiredDataQuantity() {
/* 1257 */     if (Trace.isOn) {
/* 1258 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgent", "getRequiredDataQuantity()", "getter", 
/* 1259 */           Integer.valueOf(this.quantityRequired));
/*      */     }
/* 1261 */     return this.quantityRequired;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQQueueAgent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */