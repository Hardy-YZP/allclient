/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessageReference;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager;
/*      */ import java.io.IOException;
/*      */ import java.util.HashMap;
/*      */ import javax.jms.IllegalStateException;
/*      */ import javax.jms.InvalidDestinationException;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.JMSSecurityException;
/*      */ import javax.jms.ResourceAllocationException;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQQueueServices
/*      */ {
/*      */   static final int MQCA_QSG_NAME = 2040;
/*      */   private static final String PROBE_01 = "01";
/*      */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQQueueServices.java";
/*      */   
/*      */   static {
/*   67 */     if (Trace.isOn) {
/*   68 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQQueueServices.java");
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
/*   89 */   private MQQueue cbInputQueue = null;
/*      */   
/*   91 */   private String lastQueueName = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private String messageQueueName;
/*      */ 
/*      */ 
/*      */   
/*   99 */   private String resolvedQmName = null;
/*      */   
/*  101 */   private String resolvedQSGName = null;
/*      */   
/*      */   MQQueueServices() {
/*  104 */     if (Trace.isOn) {
/*  105 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "<init>()");
/*      */     }
/*      */     
/*  108 */     if (Trace.isOn) {
/*  109 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "<init>()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQQueueServices(MQConnection connection, MQQueueManager qm, boolean transacted, int acknowledgeMode) throws JMSException {
/*  119 */     if (Trace.isOn) {
/*  120 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "<init>(MQConnection,MQQueueManager,boolean,int)", new Object[] { connection, qm, 
/*      */             
/*  122 */             Boolean.valueOf(transacted), Integer.valueOf(acknowledgeMode) });
/*      */     }
/*      */     try {
/*  125 */       int selector = 2015;
/*  126 */       this.resolvedQmName = qm.getAttributeString(selector, 48).trim();
/*      */     
/*      */     }
/*  129 */     catch (Exception e) {
/*  130 */       if (Trace.isOn) {
/*  131 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "<init>(MQConnection,MQQueueManager,boolean,int)", e, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  136 */       this.resolvedQmName = qm.name;
/*      */     } 
/*      */ 
/*      */     
/*  140 */     if (Trace.isOn) {
/*  141 */       Trace.traceData(this, "Resolving QSGName", null);
/*      */     }
/*      */     try {
/*  144 */       int selector = 2040;
/*  145 */       this.resolvedQSGName = qm.getAttributeString(selector, 4).trim();
/*      */     
/*      */     }
/*  148 */     catch (Exception e) {
/*  149 */       if (Trace.isOn) {
/*  150 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "<init>(MQConnection,MQQueueManager,boolean,int)", e, 2);
/*      */       }
/*      */ 
/*      */       
/*  154 */       this.resolvedQSGName = null;
/*  155 */       if (Trace.isOn) {
/*  156 */         Trace.traceData(this, "Setting QSGName to null", null);
/*      */       }
/*      */     } 
/*  159 */     if (Trace.isOn) {
/*  160 */       Trace.traceData(this, "QSGName set to " + this.resolvedQSGName, null);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  188 */     if (Trace.isOn) {
/*  189 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "<init>(MQConnection,MQQueueManager,boolean,int)");
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
/*      */   void checkQueueAccess(WMQDestination queue, MQSession session) throws JMSException {
/*  204 */     if (Trace.isOn) {
/*  205 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "checkQueueAccess(WMQDestination,MQSession)", new Object[] { queue, session });
/*      */     }
/*      */     
/*  208 */     if (queue.isTemporary()) {
/*  209 */       queue.checkAccess(session.connection);
/*      */     }
/*  211 */     if (Trace.isOn) {
/*  212 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "checkQueueAccess(WMQDestination,MQSession)");
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
/*      */   protected void commitQ(MQSession queueSession, MQSession session) throws JMSException {
/*  232 */     if (Trace.isOn) {
/*  233 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "commitQ(MQSession,MQSession)", new Object[] { queueSession, session });
/*      */     }
/*      */     
/*      */     try {
/*  237 */       if (!queueSession.transacted) {
/*      */ 
/*      */         
/*  240 */         String key = "MQJMS1019";
/*  241 */         String msg = ConfigEnvironment.getErrorMessage(key);
/*  242 */         IllegalStateException je = new IllegalStateException(msg, key);
/*      */         
/*  244 */         if (Trace.isOn) {
/*  245 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "commitQ(MQSession,MQSession)", (Throwable)je, 1);
/*      */         }
/*      */         
/*  248 */         throw je;
/*      */       } 
/*      */       
/*  251 */       if (session.getQM() == null) {
/*  252 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/*  253 */         if (Trace.isOn) {
/*  254 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "commitQ(MQSession,MQSession)", (Throwable)je, 2);
/*      */         }
/*      */         
/*  257 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  265 */         synchronized (queueSession.getTransactionLock()) {
/*  266 */           session.getQM().commit();
/*      */         }
/*      */       
/*      */       }
/*  270 */       catch (MQException e) {
/*  271 */         JMSException je; if (Trace.isOn) {
/*  272 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "commitQ(MQSession,MQSession)", (Throwable)e, 1);
/*      */         }
/*      */         
/*  275 */         int compCode = e.completionCode;
/*  276 */         int reasonCode = e.reasonCode;
/*      */ 
/*      */ 
/*      */         
/*  280 */         if (compCode == 1 && reasonCode == 2003) {
/*  281 */           String key = "MQJMS0011";
/*  282 */           String msg = ConfigEnvironment.getErrorMessage(key);
/*  283 */           TransactionRolledBackException transactionRolledBackException = new TransactionRolledBackException(msg, key);
/*  284 */           transactionRolledBackException.setLinkedException((Exception)e);
/*  285 */           transactionRolledBackException.initCause((Throwable)e);
/*      */         } else {
/*      */           
/*  288 */           je = ConfigEnvironment.newException("MQJMS2009");
/*  289 */           je.setLinkedException((Exception)e);
/*      */         } 
/*      */         
/*  292 */         if (Trace.isOn) {
/*  293 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "commitQ(MQSession,MQSession)", (Throwable)je, 3);
/*      */         }
/*      */         
/*  296 */         throw je;
/*      */       }
/*      */     
/*  299 */     } catch (JMSException je) {
/*  300 */       if (Trace.isOn) {
/*  301 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "commitQ(MQSession,MQSession)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  304 */       if (Trace.isOn) {
/*  305 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "commitQ(MQSession,MQSession)", (Throwable)je, 4);
/*      */       }
/*      */       
/*  308 */       throw je;
/*      */     } 
/*  310 */     if (Trace.isOn) {
/*  311 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "commitQ(MQSession,MQSession)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected ProviderMessage consume(byte[] flattenedRef, MQSession session) throws JMSException {
/*  318 */     if (Trace.isOn) {
/*  319 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(byte [ ],MQSession)", new Object[] { flattenedRef, session });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  324 */       if (Trace.isOn) {
/*  325 */         Trace.traceData(this, "recreating MessageReference", null);
/*      */       }
/*      */       
/*  328 */       ProviderMessageReference newRef = recreateMessageReference(flattenedRef, session);
/*      */       
/*  330 */       if (Trace.isOn) {
/*  331 */         Trace.traceData(this, "Recreated MessageReference. Trying to consume message", null);
/*      */       }
/*  333 */       ProviderMessage newMessage = consume(newRef, session);
/*      */       
/*  335 */       if (Trace.isOn) {
/*  336 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(byte [ ],MQSession)", newMessage);
/*      */       }
/*      */       
/*  339 */       return newMessage;
/*      */     }
/*  341 */     catch (JMSException je) {
/*  342 */       if (Trace.isOn) {
/*  343 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(byte [ ],MQSession)", (Throwable)je);
/*      */       }
/*      */       
/*  346 */       if (Trace.isOn) {
/*  347 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(byte [ ],MQSession)", (Throwable)je);
/*      */       }
/*      */       
/*  350 */       throw je;
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
/*      */   protected ProviderMessage consume(ProviderMessageReference mRef, MQSession session) throws JMSException {
/*  366 */     if (Trace.isOn) {
/*  367 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(ProviderMessageReference,MQSession)", new Object[] { mRef, session });
/*      */     }
/*      */     
/*  370 */     int reasonCode = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  376 */       if (!(mRef instanceof MQMessageReference)) {
/*  377 */         JMSException je = new JMSException("MQJMS1096");
/*  378 */         if (Trace.isOn) {
/*  379 */           Trace.traceData(this, "MessageReference is not an MQMessageReference.", null);
/*      */         }
/*  381 */         if (Trace.isOn) {
/*  382 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(ProviderMessageReference,MQSession)", (Throwable)je, 1);
/*      */         }
/*      */         
/*  385 */         throw je;
/*      */       } 
/*      */       
/*  388 */       MQMessageReference mr = (MQMessageReference)mRef;
/*      */       
/*  390 */       String queueName = mr.getReferenceQueue();
/*  391 */       if (Trace.isOn) {
/*  392 */         Trace.traceData(this, "Got referenceQueueName = '" + queueName + "'", null);
/*      */       }
/*      */       
/*  395 */       this.messageQueueName = queueName;
/*      */ 
/*      */       
/*  398 */       MQGetMessageOptions gmo = new MQGetMessageOptions(true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  404 */       MQQueueManager qm = session.getQM();
/*      */ 
/*      */       
/*  407 */       if (Trace.isOn) {
/*  408 */         Trace.traceData(this, "Setting up GMO", null);
/*      */       }
/*  410 */       gmo.matchOptions = 1;
/*  411 */       gmo.options = 8256;
/*      */ 
/*      */       
/*  414 */       if (session.calculateUseSPIP()) {
/*  415 */         gmo.options |= 0x1000;
/*      */       } else {
/*      */         
/*  418 */         gmo.options |= 0x2;
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
/*      */       try {
/*  439 */         if (this.cbInputQueue == null || !this.messageQueueName.equals(this.lastQueueName)) {
/*  440 */           if (Trace.isOn) {
/*  441 */             Trace.traceData(this, "Queue " + this.messageQueueName + " to be opened", null);
/*      */           }
/*  443 */           if (this.cbInputQueue != null) {
/*  444 */             this.cbInputQueue.close();
/*      */           }
/*      */           
/*  447 */           this.cbInputQueue = qm.accessQueue(this.messageQueueName, 8194);
/*      */           
/*  449 */           this.lastQueueName = this.messageQueueName;
/*  450 */           if (Trace.isOn) {
/*  451 */             Trace.traceData(this, "Got access to " + this.cbInputQueue.name, null);
/*      */           
/*      */           }
/*      */         }
/*  455 */         else if (Trace.isOn) {
/*  456 */           Trace.traceData(this, "using cached queue for " + this.messageQueueName, null);
/*      */         }
/*      */       
/*      */       }
/*  460 */       catch (MQException mqe) {
/*  461 */         if (Trace.isOn) {
/*  462 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(ProviderMessageReference,MQSession)", (Throwable)mqe, 1);
/*      */         }
/*      */         
/*  465 */         if (mqe.completionCode == 2) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  470 */           if (Trace.isOn) {
/*  471 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(ProviderMessageReference,MQSession)", null, 1);
/*      */           }
/*      */           
/*  474 */           return null;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  481 */       JMSMessage outMessage = null;
/*      */       
/*  483 */       if (this.cbInputQueue != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  494 */         if (mr.getDataQuantity() == 2) {
/*      */           
/*  496 */           outMessage = mr.getJMSMessage();
/*      */ 
/*      */ 
/*      */           
/*  500 */           MQJMSMessage dummyMessage = mr.getMQJMSMessage();
/*  501 */           if (dummyMessage == null) {
/*      */             
/*  503 */             dummyMessage = new MQJMSMessage();
/*  504 */             dummyMessage.setMessageId(mr.getMessageId());
/*  505 */             dummyMessage.setCorrelationId(mr.getCorrelId());
/*      */           } 
/*  507 */           reasonCode = this.cbInputQueue.getMsg2NoExc(dummyMessage, gmo, 0);
/*  508 */           if (reasonCode == 2079) {
/*  509 */             reasonCode = 0;
/*      */           }
/*  511 */           if (reasonCode != 0) {
/*  512 */             outMessage = null;
/*      */           }
/*      */         } else {
/*      */           
/*  516 */           MQJMSMessage dummyMessage = mr.getMQJMSMessage();
/*  517 */           if (dummyMessage == null) {
/*  518 */             dummyMessage = new MQJMSMessage();
/*  519 */             dummyMessage.setMessageId(mr.getMessageId());
/*  520 */             dummyMessage.setCorrelationId(mr.getCorrelId());
/*      */           } 
/*  522 */           gmo.options &= 0xFFFFFFBF;
/*  523 */           reasonCode = this.cbInputQueue.getMsg2NoExc(dummyMessage, gmo);
/*      */           
/*  525 */           if (reasonCode == 0) {
/*  526 */             outMessage = dummyMessage.createJMSMessage(session, mr.getDestination());
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
/*  541 */         if (reasonCode == 2033) {
/*  542 */           if (Trace.isOn) {
/*  543 */             Trace.traceData(this, "NO_MSG_AVAILABLE - message removed from queue", null);
/*      */           }
/*  545 */           if (Trace.isOn) {
/*  546 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(ProviderMessageReference,MQSession)", null, 2);
/*      */           }
/*      */           
/*  549 */           return null;
/*      */         } 
/*  551 */         if (reasonCode != 0) {
/*  552 */           if (Trace.isOn) {
/*  553 */             Trace.traceData(this, "Got MQRC " + reasonCode, null);
/*      */           }
/*      */           
/*  556 */           MQException traceRet1 = new MQException(2, reasonCode, this);
/*  557 */           if (Trace.isOn) {
/*  558 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(ProviderMessageReference,MQSession)", (Throwable)traceRet1, 2);
/*      */           }
/*      */           
/*  561 */           throw traceRet1;
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  567 */         if (Trace.isOn) {
/*  568 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(ProviderMessageReference,MQSession)", null, 3);
/*      */         }
/*      */         
/*  571 */         return null;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  576 */       if (!session.getTransacted()) {
/*  577 */         outMessage._setSession(session);
/*      */       }
/*      */       
/*  580 */       if (Trace.isOn) {
/*  581 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(ProviderMessageReference,MQSession)", outMessage, 4);
/*      */       }
/*      */       
/*  584 */       return outMessage;
/*      */     
/*      */     }
/*  587 */     catch (MQException mqe) {
/*  588 */       if (Trace.isOn) {
/*  589 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(ProviderMessageReference,MQSession)", (Throwable)mqe, 2);
/*      */       }
/*      */       
/*  592 */       JMSException je = ConfigEnvironment.newException("MQJMS2002");
/*  593 */       je.setLinkedException((Exception)mqe);
/*  594 */       if (Trace.isOn) {
/*  595 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(ProviderMessageReference,MQSession)", (Throwable)je, 3);
/*      */       }
/*      */       
/*  598 */       throw je;
/*      */     }
/*  600 */     catch (IOException ioe) {
/*  601 */       if (Trace.isOn) {
/*  602 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(ProviderMessageReference,MQSession)", ioe, 3);
/*      */       }
/*      */       
/*  605 */       if (Trace.isOn) {
/*  606 */         Trace.traceData(this, "Badly formatted message.", null);
/*      */       }
/*  608 */       JMSException je = ConfigEnvironment.newException("MQJMS0006");
/*  609 */       je.setLinkedException(ioe);
/*  610 */       if (Trace.isOn) {
/*  611 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(ProviderMessageReference,MQSession)", (Throwable)je, 4);
/*      */       }
/*      */       
/*  614 */       throw je;
/*      */     }
/*  616 */     catch (JMSException je) {
/*  617 */       if (Trace.isOn) {
/*  618 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(ProviderMessageReference,MQSession)", (Throwable)je, 4);
/*      */       }
/*      */       
/*  621 */       if (Trace.isOn) {
/*  622 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "consume(ProviderMessageReference,MQSession)", (Throwable)je, 5);
/*      */       }
/*      */       
/*  625 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MQQueue getOutputQueue(WMQDestination dest, MQSession session) throws JMSException {
/*  635 */     if (Trace.isOn) {
/*  636 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getOutputQueue(WMQDestination,MQSession)", new Object[] { dest, session });
/*      */     }
/*      */     
/*      */     try {
/*  640 */       MQQueue mqqueue = null;
/*      */       
/*  642 */       if (dest == null) {
/*  643 */         String key = "MQJMS0003";
/*  644 */         String msg = ConfigEnvironment.getErrorMessage(key);
/*  645 */         InvalidDestinationException je = new InvalidDestinationException(msg, key);
/*      */         
/*  647 */         if (Trace.isOn) {
/*  648 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getOutputQueue(WMQDestination,MQSession)", (Throwable)je, 1);
/*      */         }
/*      */         
/*  651 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*  655 */       String qName = dest.getName();
/*  656 */       String qmName = dest.getStringProperty("XMSC_WMQ_QUEUE_MANAGER");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  661 */       int openOptions = 16;
/*      */ 
/*      */       
/*  664 */       if (dest.getIntProperty("failIfQuiesce") == 1) {
/*  665 */         openOptions |= 0x2000;
/*      */       }
/*      */ 
/*      */       
/*  669 */       openOptions |= MQMessageProducer.computeMessageContextOptions(dest);
/*      */       try {
/*  671 */         mqqueue = session.getQM().accessQueue(qName, openOptions, qmName, null, "");
/*      */       }
/*  673 */       catch (MQException e) {
/*  674 */         if (Trace.isOn) {
/*  675 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getOutputQueue(WMQDestination,MQSession)", (Throwable)e, 1);
/*      */         }
/*      */         
/*  678 */         JMSException je = getQueueOpenException(e, qName);
/*  679 */         if (Trace.isOn) {
/*  680 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getOutputQueue(WMQDestination,MQSession)", (Throwable)je, 2);
/*      */         }
/*      */         
/*  683 */         throw je;
/*      */       } 
/*      */       
/*  686 */       if (Trace.isOn) {
/*  687 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getOutputQueue(WMQDestination,MQSession)", mqqueue);
/*      */       }
/*      */       
/*  690 */       return mqqueue;
/*      */     }
/*  692 */     catch (JMSException je) {
/*  693 */       if (Trace.isOn) {
/*  694 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getOutputQueue(WMQDestination,MQSession)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  697 */       if (Trace.isOn) {
/*  698 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getOutputQueue(WMQDestination,MQSession)", (Throwable)je, 3);
/*      */       }
/*      */       
/*  701 */       throw je;
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
/*      */   MQQueue getQueueForBrowse(WMQDestination queueSpec, MQSession session) throws JMSException {
/*  716 */     if (Trace.isOn) {
/*  717 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getQueueForBrowse(WMQDestination,MQSession)", new Object[] { queueSpec, session });
/*      */     }
/*      */     
/*      */     try {
/*      */       MQQueue result;
/*      */       
/*  723 */       if (queueSpec == null) {
/*      */ 
/*      */         
/*  726 */         String key = "MQJMS0003";
/*  727 */         String msg = ConfigEnvironment.getErrorMessage(key);
/*  728 */         InvalidDestinationException je = new InvalidDestinationException(msg, key);
/*  729 */         if (Trace.isOn) {
/*  730 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getQueueForBrowse(WMQDestination,MQSession)", (Throwable)je, 1);
/*      */         }
/*      */         
/*  733 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*  737 */       if (session.getQM() == null) {
/*  738 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/*  739 */         if (Trace.isOn) {
/*  740 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getQueueForBrowse(WMQDestination,MQSession)", (Throwable)je, 2);
/*      */         }
/*      */         
/*  743 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  749 */       String qmgrName = queueSpec.getStringProperty("XMSC_WMQ_QUEUE_MANAGER");
/*      */       
/*  751 */       if (qmgrName != null && !qmgrName.equals("") && !qmgrName.equals(this.resolvedQmName) && !qmgrName.equals(this.resolvedQSGName)) {
/*      */ 
/*      */         
/*  754 */         JMSException je = ConfigEnvironment.newException("MQJMS1017");
/*  755 */         if (Trace.isOn) {
/*  756 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getQueueForBrowse(WMQDestination,MQSession)", (Throwable)je, 3);
/*      */         }
/*      */         
/*  759 */         throw je;
/*      */       } 
/*      */       
/*  762 */       String qName = queueSpec.getName();
/*  763 */       int openOptions = 40;
/*      */ 
/*      */       
/*  766 */       if (queueSpec.getIntProperty("failIfQuiesce") == 1) {
/*  767 */         openOptions |= 0x2000;
/*      */       }
/*      */       
/*      */       try {
/*  771 */         result = session.getQM().accessQueue(qName, openOptions);
/*      */       
/*      */       }
/*  774 */       catch (MQException e) {
/*  775 */         if (Trace.isOn) {
/*  776 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getQueueForBrowse(WMQDestination,MQSession)", (Throwable)e, 1);
/*      */         }
/*      */         
/*  779 */         JMSException je = getQueueOpenException(e, qName);
/*  780 */         if (Trace.isOn) {
/*  781 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getQueueForBrowse(WMQDestination,MQSession)", (Throwable)je, 4);
/*      */         }
/*      */         
/*  784 */         throw je;
/*      */       } 
/*      */       
/*  787 */       if (Trace.isOn) {
/*  788 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getQueueForBrowse(WMQDestination,MQSession)", result);
/*      */       }
/*      */       
/*  791 */       return result;
/*      */     }
/*  793 */     catch (JMSException je) {
/*  794 */       MQQueue result; if (Trace.isOn) {
/*  795 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getQueueForBrowse(WMQDestination,MQSession)", (Throwable)result, 2);
/*      */       }
/*      */       
/*  798 */       if (Trace.isOn) {
/*  799 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getQueueForBrowse(WMQDestination,MQSession)", (Throwable)result, 5);
/*      */       }
/*      */       
/*  802 */       throw result;
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
/*      */   protected JMSException getQueueOpenException(MQException mqe, String qName) {
/*      */     InvalidDestinationException invalidDestinationException;
/*      */     ResourceAllocationException resourceAllocationException;
/*      */     JMSSecurityException jMSSecurityException;
/*      */     JMSException je;
/*  819 */     if (Trace.isOn) {
/*  820 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getQueueOpenException(MQException,String)", new Object[] { mqe, qName });
/*      */     }
/*      */     
/*  823 */     String key = "MQJMS2008";
/*  824 */     String message = ConfigEnvironment.getErrorMessage(key, qName);
/*      */ 
/*      */ 
/*      */     
/*  828 */     switch (mqe.reasonCode) {
/*      */       case 2001:
/*      */       case 2011:
/*      */       case 2043:
/*      */       case 2052:
/*      */       case 2057:
/*      */       case 2058:
/*      */       case 2082:
/*      */       case 2085:
/*      */       case 2086:
/*      */       case 2087:
/*      */       case 2091:
/*      */       case 2092:
/*      */       case 2152:
/*      */       case 2153:
/*      */       case 2184:
/*      */       case 2194:
/*      */       case 2196:
/*      */       case 2197:
/*      */       case 2198:
/*      */       case 2199:
/*  849 */         invalidDestinationException = new InvalidDestinationException(message, key);
/*      */         break;
/*      */       
/*      */       case 2017:
/*      */       case 2071:
/*      */       case 2102:
/*      */       case 2192:
/*  856 */         resourceAllocationException = new ResourceAllocationException(message, key);
/*      */         break;
/*      */       
/*      */       case 2035:
/*  860 */         jMSSecurityException = new JMSSecurityException(message, key);
/*      */         break;
/*      */       
/*      */       default:
/*  864 */         je = new JMSException(message, key);
/*      */         break;
/*      */     } 
/*  867 */     je.setLinkedException((Exception)mqe);
/*  868 */     je.initCause((Throwable)mqe);
/*      */     
/*  870 */     if (Trace.isOn) {
/*  871 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "getQueueOpenException(MQException,String)", je);
/*      */     }
/*      */     
/*  874 */     return je;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ProviderMessageReference recreateMessageReference(byte[] flatMR, MQSession session) throws JMSException {
/*  881 */     if (Trace.isOn) {
/*  882 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "recreateMessageReference(byte [ ],MQSession)", new Object[] { flatMR, session });
/*      */     }
/*      */     
/*      */     try {
/*  886 */       ProviderMessageReference traceRet1 = new MQMessageReference(session, flatMR, null);
/*  887 */       if (Trace.isOn) {
/*  888 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "recreateMessageReference(byte [ ],MQSession)", traceRet1);
/*      */       }
/*      */       
/*  891 */       return traceRet1;
/*      */     
/*      */     }
/*  894 */     catch (JMSException je) {
/*  895 */       if (Trace.isOn) {
/*  896 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "recreateMessageReference(byte [ ],MQSession)", (Throwable)je);
/*      */       }
/*      */       
/*  899 */       if (Trace.isOn) {
/*  900 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "recreateMessageReference(byte [ ],MQSession)", (Throwable)je);
/*      */       }
/*      */       
/*  903 */       throw je;
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
/*      */   void redirectMessage(String qName, MQMsg2 message, boolean syncPoint, MQSession session) throws JMSException {
/*  917 */     if (Trace.isOn) {
/*  918 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean,MQSession)", new Object[] { qName, message, 
/*      */             
/*  920 */             Boolean.valueOf(syncPoint), session });
/*      */     }
/*  922 */     MQQueue queue = null;
/*      */     
/*      */     try {
/*  925 */       if (session.getQM() == null) {
/*  926 */         JMSException je = ConfigEnvironment.newException("MQJMS2004");
/*  927 */         if (Trace.isOn) {
/*  928 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean,MQSession)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */         
/*  932 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  937 */         MQPutMessageOptions pmo = new MQPutMessageOptions();
/*      */ 
/*      */         
/*  940 */         if (syncPoint) {
/*  941 */           pmo.options |= 0x2;
/*  942 */           pmo.options &= 0xFFFFFFFB;
/*      */         } else {
/*      */           
/*  945 */           pmo.options |= 0x4;
/*  946 */           pmo.options &= 0xFFFFFFFD;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  952 */         session.getQM().putMsg2(qName, "", message, pmo);
/*      */       
/*      */       }
/*  955 */       catch (MQException e) {
/*  956 */         if (Trace.isOn) {
/*  957 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean,MQSession)", (Throwable)e, 1);
/*      */         }
/*      */ 
/*      */         
/*  961 */         JMSException je = ConfigEnvironment.newException("MQJMS1022");
/*  962 */         je.setLinkedException((Exception)e);
/*  963 */         if (Trace.isOn) {
/*  964 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean,MQSession)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */         
/*  968 */         throw je;
/*      */       }
/*      */       finally {
/*      */         
/*  972 */         if (Trace.isOn) {
/*  973 */           Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean,MQSession)");
/*      */         }
/*      */ 
/*      */         
/*  977 */         if (queue != null) {
/*      */           try {
/*  979 */             queue.close();
/*      */           }
/*  981 */           catch (MQException e) {
/*  982 */             if (Trace.isOn) {
/*  983 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean,MQSession)", (Throwable)e, 2);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  990 */             HashMap<Object, Object> ffstData = new HashMap<>();
/*  991 */             ffstData.put("Exception", e);
/*  992 */             ffstData.put("Message", "MQJMS2000");
/*  993 */             Trace.ffst(this, "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean,MQSession)", "01", ffstData, JMSException.class);
/*      */           }
/*      */         
/*      */         }
/*      */       }
/*      */     
/*      */     }
/* 1000 */     catch (JMSException je) {
/* 1001 */       if (Trace.isOn) {
/* 1002 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean,MQSession)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1007 */       if (Trace.isOn) {
/* 1008 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean,MQSession)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */       
/* 1012 */       throw je;
/*      */     } 
/*      */     
/* 1015 */     if (Trace.isOn)
/* 1016 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueServices", "redirectMessage(String,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2,boolean,MQSession)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQQueueServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */