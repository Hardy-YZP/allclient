/*      */ package com.ibm.msg.client.wmq.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.MQCBC;
/*      */ import com.ibm.mq.jmqi.MQCBD;
/*      */ import com.ibm.mq.jmqi.MQConsumer;
/*      */ import com.ibm.mq.jmqi.MQGMO;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.remote.api.RemoteHconn;
/*      */ import com.ibm.mq.jmqi.remote.impl.RemoteProxyQueue;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessageListener;
/*      */ import com.ibm.msg.client.wmq.common.internal.Reason;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQConsumerOwner;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.common.internal.messages.WMQMessage;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.HashMap;
/*      */ import java.util.concurrent.locks.ReentrantLock;
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
/*      */ class WMQAsyncConsumerShadow
/*      */   extends WMQConsumerShadow
/*      */   implements MQConsumer
/*      */ {
/*      */   private static final int closeWaitTime = 30000;
/*      */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQAsyncConsumerShadow.java";
/*      */   
/*      */   static {
/*   66 */     if (Trace.isOn) {
/*   67 */       Trace.data("com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQAsyncConsumerShadow.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean delayedCloseFinished = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   82 */   protected Object delayedCloseLock = new Object();
/*      */ 
/*      */   
/*   85 */   protected ProviderMessageListener listener = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   WMQAsyncConsumerShadow(JmsPropertyContext jmsProps, WMQConsumerOwner helper, WMQDestination destination, String selector, boolean nolocal, boolean shared, boolean durable, String subscriptionName) {
/*   96 */     super(jmsProps, helper, destination, selector, nolocal, shared, durable, subscriptionName, null);
/*   97 */     if (Trace.isOn) {
/*   98 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "<init>(JmsPropertyContext,WMQConsumerOwner,WMQDestination,String,boolean,boolean,boolean,String)", new Object[] { jmsProps, helper, destination, selector, 
/*      */             
/*  100 */             Boolean.valueOf(nolocal), 
/*  101 */             Boolean.valueOf(shared), Boolean.valueOf(durable), subscriptionName });
/*      */     }
/*  103 */     if (Trace.isOn) {
/*  104 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "<init>(JmsPropertyContext,WMQConsumerOwner,WMQDestination,String,boolean,boolean,boolean,String)");
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
/*      */   void closeInternal(boolean closeHobj) throws JMSException {
/*  117 */     if (Trace.isOn)
/*  118 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "closeInternal(boolean)", new Object[] {
/*  119 */             Boolean.valueOf(closeHobj)
/*      */           }); 
/*  121 */     closeInternal((ReentrantLock)null, closeHobj);
/*  122 */     if (Trace.isOn) {
/*  123 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "closeInternal(boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void closeInternal(ReentrantLock onMessageLock, boolean closeHobj) throws JMSException {
/*  134 */     if (Trace.isOn) {
/*  135 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "closeInternal(ReentrantLock,boolean)", new Object[] { onMessageLock, 
/*      */             
/*  137 */             Boolean.valueOf(closeHobj) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  143 */     if (!closeHobj) {
/*      */ 
/*      */       
/*  146 */       setMessageListener((ProviderMessageListener)null);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  152 */       Pint rc = this.env.newPint();
/*  153 */       Pint cc = this.env.newPint();
/*  154 */       boolean hobjAlreadyClosed = false;
/*      */ 
/*      */       
/*  157 */       int closePolicy = this.destination.getIntProperty("readAheadClosePolicy");
/*      */ 
/*      */ 
/*      */       
/*  161 */       if (this.hconn instanceof RemoteHconn && !((RemoteHconn)this.hconn).isStarted()) {
/*  162 */         for (RemoteProxyQueue pq : ((RemoteHconn)this.hconn).getDispatchQueueList()) {
/*  163 */           if (!pq.isLogicallyRemoved() && 
/*  164 */             !pq.isEmpty()) {
/*  165 */             if (pq.hasPersistent()) {
/*  166 */               HashMap<String, Object> data = new HashMap<>();
/*  167 */               data.put("hconn", this.hconn);
/*  168 */               data.put("Proxy Queue", pq);
/*  169 */               data.put("Reason", "PERSISTENT MESSAGE on proxy queue when closing stopped consumer");
/*  170 */               Trace.ffst(this, "closeInternal(reentrantLock,boolean)", "XN00M008", data, null);
/*      */               continue;
/*      */             } 
/*  173 */             if (Trace.isOn) {
/*  174 */               Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "closeInternal(reentrantLock,boolean)", "Non-persistent msg on remote proxy queue will be discarded.  ", "This is expected behaviour when using read-ahead and closing a stopped consumer");
/*      */             }
/*      */           } 
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
/*  189 */       if ((!(this.hconn instanceof RemoteHconn) && closePolicy == 2) || (this.hconn instanceof RemoteHconn && ((RemoteHconn)this.hconn)
/*      */ 
/*      */         
/*  192 */         .isStarted() && closePolicy == 2)) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  197 */         int closeOptions = 32;
/*      */         
/*  199 */         this.mq.MQCLOSE(this.hconn, this.phobj, closeOptions, cc, rc);
/*      */ 
/*      */         
/*  202 */         if (cc.x == 1 && rc.x == 2458) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  209 */           synchronized (this.delayedCloseLock)
/*      */           {
/*      */             
/*  212 */             this.helper.resumeAsyncService();
/*      */             
/*  214 */             if (onMessageLock != null) {
/*  215 */               assert onMessageLock.isHeldByCurrentThread();
/*  216 */               onMessageLock.unlock();
/*      */             } 
/*      */ 
/*      */             
/*  220 */             boolean relockHconn = this.helper.haveHconnLock();
/*  221 */             if (relockHconn) {
/*  222 */               this.helper.unlockHconn();
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             try {
/*  229 */               if (!this.delayedCloseFinished) {
/*      */                 try {
/*  231 */                   this.delayedCloseLock.wait(30000L);
/*      */                 }
/*  233 */                 catch (InterruptedException e) {
/*  234 */                   if (Trace.isOn) {
/*  235 */                     Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "closeInternal(ReentrantLock,boolean)", e);
/*      */                   }
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  242 */                 if (!this.delayedCloseFinished)
/*      */                 {
/*      */ 
/*      */ 
/*      */                   
/*  247 */                   if (!(this.hconn instanceof RemoteHconn) || ((RemoteHconn)this.hconn).isStarted()) {
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/*  252 */                     HashMap<String, String> inserts = new HashMap<>();
/*  253 */                     inserts.put("XMSC_DESTINATION_NAME", this.destination.getName());
/*  254 */                     JMSException traceRet1 = (JMSException)NLSServices.createException("JMSWMQ2001", inserts);
/*  255 */                     if (Trace.isOn) {
/*  256 */                       Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "closeInternal(ReentrantLock,boolean)", (Throwable)traceRet1);
/*      */                     }
/*      */                     
/*  259 */                     throw traceRet1;
/*      */                   } 
/*      */                 }
/*      */               } 
/*      */ 
/*      */               
/*  265 */               this.helper.suspendAsyncService();
/*      */             } finally {
/*      */               
/*  268 */               if (Trace.isOn) {
/*  269 */                 Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "closeInternal(ReentrantLock,boolean)");
/*      */               }
/*      */               
/*  272 */               if (relockHconn) {
/*  273 */                 this.helper.lockHconn();
/*      */               }
/*  275 */               if (onMessageLock != null) {
/*  276 */                 onMessageLock.lock();
/*      */               }
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         }
/*  283 */         else if (this.destination.isTopic()) {
/*      */           
/*  285 */           WMQMessageConsumer.checkJmqiCallSuccess("JMSWMQ2003", this.destination
/*  286 */               .getName(), "XMSC_DESTINATION_NAME", cc, rc, this.env, "XN00M001", this.helper, this.hconn);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  297 */           hobjAlreadyClosed = true;
/*      */         }
/*      */         else {
/*      */           
/*  301 */           WMQMessageConsumer.checkJmqiCallSuccess("JMSWMQ2000", this.destination
/*  302 */               .getName(), "XMSC_DESTINATION_NAME", cc, rc, this.env, "XN00M001", this.helper, this.hconn);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  316 */           this.helper.removeAsyncConsumer();
/*      */ 
/*      */           
/*  319 */           if (Trace.isOn) {
/*  320 */             Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "closeInternal(ReentrantLock,boolean)", 1);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  332 */       this.helper.removeAsyncConsumer();
/*      */       
/*  334 */       if (this.destination.isTopic()) {
/*      */ 
/*      */         
/*  337 */         this.mq.MQCLOSE(this.hconn, this.phsub, 0, cc, rc);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  342 */         if (this.shared && rc.x == 2429) {
/*      */           
/*  344 */           if (Trace.isOn) {
/*  345 */             Trace.traceData(this, "closeInternal(boolean)", "Subscription in use for shared consumer. Leaving subscription in place", rc);
/*      */           }
/*      */         }
/*      */         else {
/*      */           
/*  350 */           WMQMessageConsumer.checkJmqiCallSuccess("JMSWMQ0025", this.destination
/*  351 */               .getName(), "XMSC_DESTINATION_NAME", cc, rc, this.env, "XN004009", this.helper, this.hconn, true);
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
/*  364 */       if (!hobjAlreadyClosed) {
/*  365 */         this.mq.MQCLOSE(this.hconn, this.phobj, 0, cc, rc);
/*      */       }
/*      */     } 
/*      */     
/*  369 */     if (Trace.isOn) {
/*  370 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "closeInternal(ReentrantLock,boolean)", 2);
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
/*      */   MQCBD computeCBD() {
/*  382 */     if (Trace.isOn) {
/*  383 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "computeCBD()");
/*      */     }
/*  385 */     MQCBD cbd = this.env.newMQCBD();
/*  386 */     cbd.setCallbackFunction(this);
/*  387 */     cbd.setCallbackArea(this.phobj);
/*      */ 
/*      */     
/*  390 */     cbd.setCallbackType(1);
/*  391 */     if (Trace.isOn) {
/*  392 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "computeCBD()", cbd);
/*      */     }
/*      */     
/*  395 */     return cbd;
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
/*      */   public void consumer(Hconn hconn1, MQMD mqmd1, MQGMO mqgmo1, ByteBuffer pBuffer, MQCBC mqcbc) {
/*  409 */     if (Trace.isOn) {
/*  410 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", new Object[] { hconn1, mqmd1, mqgmo1, pBuffer, mqcbc });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  416 */     int callType = mqcbc.getCallType();
/*      */     
/*  418 */     if (callType == 6) {
/*  419 */       this.receiveCount++;
/*  420 */       this.lastReceiveTime = System.currentTimeMillis();
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
/*  436 */     if (this.hObjCached) {
/*  437 */       Object hint = mqcbc.getCallbackArea();
/*  438 */       MQConsumer callback = this.hObjCache.getMessageListenersForQueue((Phobj)hint);
/*  439 */       if (callback != null) {
/*  440 */         if (callback != this) {
/*  441 */           callback.consumer(hconn1, mqmd1, mqgmo1, pBuffer, mqcbc);
/*  442 */           if (Trace.isOn) {
/*  443 */             Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", 1);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */       } else {
/*  451 */         HashMap<String, Object> info = new HashMap<>();
/*  452 */         info.put("hint", hint);
/*  453 */         info.put("callback", "null");
/*  454 */         Trace.ffst(this, "consumer()", "XN00M007", info, null);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  464 */     if (callType == 6) {
/*      */       WMQMessage wMQMessage;
/*  466 */       if (this.receiveMarshal == null) {
/*  467 */         initializeReceiveMarshal();
/*      */       }
/*      */ 
/*      */       
/*  471 */       int dataLength = mqcbc.getDataLength();
/*      */ 
/*      */       
/*  474 */       if (dataLength <= 0 && 
/*  475 */         Trace.isOn) {
/*  476 */         Trace.traceData(this, "getMsgAsync (empty message)", null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  484 */       assert this.helper instanceof WMQSession;
/*      */ 
/*      */       
/*  487 */       this.receiveMarshal.importMQMDMesageBuffer(this.helper, this.destination, mqmd1, pBuffer, 0, dataLength, null);
/*      */       
/*  489 */       ProviderMessage resultMsg = null;
/*      */       
/*      */       try {
/*  492 */         wMQMessage = this.receiveMarshal.exportProviderMessage(false);
/*      */       }
/*  494 */       catch (JMSException e) {
/*  495 */         if (Trace.isOn) {
/*  496 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", (Throwable)e, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*  504 */           ((WMQConnection)this.helper.getConnection()).driveExceptionListener(e, false);
/*      */         }
/*  506 */         catch (JMSException e2) {
/*  507 */           if (Trace.isOn) {
/*  508 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", (Throwable)e2, 2);
/*      */           }
/*      */           
/*  511 */           HashMap<String, Object> info = new HashMap<>();
/*  512 */           info.put("exception1", e);
/*  513 */           info.put("exception2", e2);
/*  514 */           info.put("destination", this.destination.getName());
/*  515 */           info.put("marshal", this.receiveMarshal);
/*  516 */           Trace.ffst(this, "consume(Hconn,MQMD,MQGMO,ByteNuffer,MQCBC)", "XN004003", info, null);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  523 */       ByteBuffer[] msgBuffer = { pBuffer };
/*  524 */       boolean poisonous = poisonous(mqmd1, (ProviderMessage)wMQMessage, msgBuffer);
/*      */       
/*  526 */       if (wMQMessage != null && !poisonous) {
/*  527 */         int getMessageOptions = mqgmo1.getOptions();
/*  528 */         int mqmdPersistence = mqmd1.getPersistence();
/*  529 */         if (messageConsumedUnderSyncpoint(getMessageOptions, mqmdPersistence))
/*      */         {
/*  531 */           this.helper.operationPerformed(WMQConsumerOwner.Operation.GET, true);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  538 */       if (!poisonous) {
/*      */         try {
/*  540 */           honourNoLocal(mqmd1, (ProviderMessage)wMQMessage);
/*      */         }
/*  542 */         catch (JmqiException jmqiException) {
/*      */           try {
/*  544 */             WMQMessageConsumer.checkJmqiCallSuccess("JMSWMQ2002", this.destination
/*  545 */                 .getName(), "XMSC_DESTINATION_NAME", this.env
/*      */                 
/*  547 */                 .newPint(jmqiException.getCompCode()), this.env
/*  548 */                 .newPint(jmqiException.getReason()), this.env, "XN004100", this.helper, hconn1, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*  555 */           catch (JMSException e) {
/*  556 */             if (Trace.isOn) {
/*  557 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", (Throwable)e, 8);
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     }
/*  568 */     else if (callType == 7) {
/*  569 */       ProviderMessage resultMsg = null;
/*      */ 
/*      */       
/*      */       try {
/*  573 */         WMQGMO gmo = computeGMO(0);
/*      */ 
/*      */         
/*  576 */         gmo.setMatchOptions(32);
/*  577 */         gmo.setMsgToken(mqgmo1.getMsgToken());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  587 */         resultMsg = getMsg(gmo, mqcbc.getDataLength(), true);
/*      */       }
/*  589 */       catch (JMSException e) {
/*  590 */         if (Trace.isOn) {
/*  591 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", (Throwable)e, 5);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*  597 */           ((WMQConnection)this.helper.getConnection()).driveExceptionListener(e, false);
/*      */         }
/*  599 */         catch (JMSException e2) {
/*  600 */           if (Trace.isOn) {
/*  601 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", (Throwable)e2, 6);
/*      */           }
/*      */           
/*  604 */           HashMap<String, Object> info = new HashMap<>();
/*  605 */           info.put("exception1", e);
/*  606 */           info.put("exception2", e2);
/*  607 */           info.put("destination", this.destination.getName());
/*  608 */           info.put("msg type", "persistent");
/*  609 */           Trace.ffst(this, "consume(Hconn,MQMD,MQGMO,ByteNuffer,MQCBC)", "XN00M002", info, null);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  617 */       if (this.destination.isQueue()) {
/*  618 */         this.helper.operationPerformed(WMQConsumerOwner.Operation.GET, true);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  625 */         honourNoLocal(mqmd1, resultMsg);
/*      */       }
/*  627 */       catch (JmqiException jmqiException) {
/*      */         try {
/*  629 */           WMQMessageConsumer.checkJmqiCallSuccess("JMSWMQ2002", this.destination
/*  630 */               .getName(), "XMSC_DESTINATION_NAME", this.env
/*      */               
/*  632 */               .newPint(jmqiException.getCompCode()), this.env
/*  633 */               .newPint(jmqiException.getReason()), this.env, "XN004101", this.helper, hconn1, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  640 */         catch (JMSException e) {
/*  641 */           if (Trace.isOn) {
/*  642 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", (Throwable)e, 9);
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*  653 */     else if (callType == 5) {
/*      */       
/*      */       try {
/*  656 */         Pint consumerRC = this.env.newPint(mqcbc.getReason());
/*  657 */         Pint consumerCC = this.env.newPint(mqcbc.getCompCode());
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  662 */         if (consumerCC.x == 2 && consumerRC.x == 2518) {
/*  663 */           synchronized (this.delayedCloseLock)
/*      */           {
/*  665 */             this.delayedCloseFinished = true;
/*      */ 
/*      */             
/*  668 */             this.delayedCloseLock.notify();
/*      */           }
/*      */         
/*  671 */         } else if (consumerCC.x == 2 && consumerRC.x == 2517) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  677 */           HashMap<String, Object> data = new HashMap<>();
/*  678 */           data.put("destination", this.destination.getName());
/*  679 */           data.put("reason", Integer.valueOf(consumerRC.x));
/*  680 */           Trace.ffst(this, "consumer()", "XN00M003", data, null);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/*  686 */           WMQMessageConsumer.checkJmqiCallSuccess("JMSWMQ2002", this.destination
/*  687 */               .getName(), "XMSC_DESTINATION_NAME", consumerCC, consumerRC, this.env, "XN004004", this.helper, hconn1, true);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  698 */       catch (JMSException e) {
/*  699 */         if (Trace.isOn) {
/*  700 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", (Throwable)e, 7);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  707 */     if (Trace.isOn) {
/*  708 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "consumer(Hconn,MQMD,MQGMO,ByteBuffer,MQCBC)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void honourNoLocal(MQMD mqmd1, ProviderMessage msg) throws JmqiException {
/*  715 */     if (Trace.isOn) {
/*  716 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "honourNoLocal(MQMD,ProviderMessage)", new Object[] { mqmd1, msg });
/*      */     }
/*      */     
/*  719 */     if (this.nolocal) {
/*      */ 
/*      */ 
/*      */       
/*  723 */       String parentConnId = ((WMQConnection)this.helper.getConnection()).getHconn().getConnectionIdAsString();
/*  724 */       String messageConnId = null;
/*  725 */       if (msg != null) {
/*      */         try {
/*  727 */           messageConnId = msg.getStringProperty("JMS_IBM_ConnectionID");
/*      */         }
/*  729 */         catch (JMSException e) {
/*  730 */           if (Trace.isOn) {
/*  731 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "honourNoLocal(MQMD,ProviderMessage)", (Throwable)e);
/*      */           }
/*      */ 
/*      */           
/*  735 */           HashMap<String, Object> info = new HashMap<>();
/*  736 */           info.put("exception1", e);
/*  737 */           info.put("destination", this.destination.getName());
/*  738 */           info.put("msg", msg);
/*  739 */           Trace.ffst(this, "consume(Hconn,MQMD,MQGMO,ByteNuffer,MQCBC)", "XN00M004", info, null);
/*      */         } 
/*      */       }
/*      */       
/*  743 */       if (!parentConnId.equals(messageConnId)) {
/*      */ 
/*      */         
/*  746 */         callMessageListener(msg);
/*      */ 
/*      */       
/*      */       }
/*  750 */       else if (Trace.isOn) {
/*  751 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.ApiAsyncConsumerShadow", "noLocal, Message disgarded as ConnectionID matched our ConnectionID");
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  758 */       callMessageListener(msg);
/*      */     } 
/*  760 */     if (Trace.isOn) {
/*  761 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "honourNoLocal(MQMD,ProviderMessage)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void callMessageListener(ProviderMessage msg) {
/*  768 */     if (Trace.isOn) {
/*  769 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "callMessageListener(ProviderMessage)", new Object[] { msg });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  778 */     boolean haveHconnLock = this.helper.haveHconnLock();
/*  779 */     if (haveHconnLock) {
/*  780 */       this.helper.unlockHconn();
/*      */     }
/*  782 */     this.listener.onMessage(msg);
/*  783 */     if (haveHconnLock) {
/*  784 */       this.helper.lockHconn();
/*      */     }
/*      */     
/*  787 */     if (Trace.isOn) {
/*  788 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "callMessageListener(ProviderMessage)");
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
/*      */   private boolean poisonous(MQMD mqmd1, ProviderMessage msg, ByteBuffer[] pBuffer) {
/*  802 */     if (Trace.isOn) {
/*  803 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "poisonous(MQMD,ProviderMessage,ByteBuffer [ ])", new Object[] { mqmd1, msg, pBuffer });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  811 */       if (msg == null) {
/*  812 */         if (Trace.isOn) {
/*  813 */           Trace.traceInfo(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "poisonous(MQMD,ProviderMessage,ByteBuffer[])", "message does not contain a provider message. Moving to the backout queue");
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  818 */         initialisePoison();
/*  819 */         this.poison.handlePoisonMessage(mqmd1, pBuffer);
/*  820 */         if (Trace.isOn) {
/*  821 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "poisonous(MQMD,ProviderMessage,ByteBuffer[])", 
/*      */ 
/*      */               
/*  824 */               Boolean.valueOf(true), 5);
/*      */         }
/*      */         
/*  827 */         if (Trace.isOn) {
/*  828 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "poisonous(MQMD,ProviderMessage,ByteBuffer [ ])", 
/*  829 */               Boolean.valueOf(true), 1);
/*      */         }
/*  831 */         return true;
/*      */       } 
/*      */       
/*  834 */       if (mqmd1.getBackoutCount() == 0) {
/*  835 */         if (Trace.isOn) {
/*  836 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "poisonous(MQMD,ProviderMessage,ByteBuffer [ ])", 
/*  837 */               Boolean.valueOf(false), 2);
/*      */         }
/*  839 */         return false;
/*      */       } 
/*      */ 
/*      */       
/*  843 */       initialisePoison();
/*  844 */       if (this.poison.shouldMessageBeRequeued(mqmd1.getBackoutCount())) {
/*  845 */         this.poison.handlePoisonMessage((WMQMessage)msg);
/*  846 */         if (Trace.isOn) {
/*  847 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "poisonous(MQMD,ProviderMessage,ByteBuffer [ ])", 
/*  848 */               Boolean.valueOf(true), 3);
/*      */         }
/*  850 */         return true;
/*      */       }
/*      */     
/*  853 */     } catch (JMSException e) {
/*  854 */       if (Trace.isOn) {
/*  855 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "poisonous(MQMD,ProviderMessage,ByteBuffer [ ])", (Throwable)e, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  861 */       HashMap<String, Object> inserts = new HashMap<>();
/*  862 */       inserts.put("XMSC_INSERT_EXCEPTION", e);
/*  863 */       Log.log(this, "poisonous()", "JMSWMQ0036", inserts);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  871 */         boolean callExL = false;
/*  872 */         if (msg != null) {
/*  873 */           if (msg.getJMSDeliveryMode().intValue() == 2) {
/*  874 */             callExL = true;
/*      */           
/*      */           }
/*      */         }
/*  878 */         else if (mqmd1.getPersistence() == 1) {
/*  879 */           callExL = true;
/*      */         } 
/*      */         
/*  882 */         if (callExL == true) {
/*      */           
/*  884 */           boolean b = isConnectionBroken((Exception)e);
/*  885 */           ((WMQConnection)this.helper.getConnection()).driveExceptionListener(e, b);
/*      */         } 
/*      */         
/*  888 */         assert this.helper instanceof WMQSession;
/*  889 */         WMQSession session = (WMQSession)this.helper;
/*  890 */         session.rollback();
/*      */         
/*  892 */         this.helper.removeAsyncConsumer();
/*      */       }
/*  894 */       catch (JMSException e2) {
/*  895 */         if (Trace.isOn) {
/*  896 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "poisonous(MQMD,ProviderMessage,ByteBuffer [ ])", (Throwable)e2, 2);
/*      */         }
/*      */         
/*  899 */         HashMap<String, Object> info = new HashMap<>();
/*  900 */         info.put("exception1", e);
/*  901 */         info.put("exception2", e2);
/*  902 */         info.put("destination", this.destination.getName());
/*  903 */         info.put("mqmd", mqmd1);
/*  904 */         Trace.ffst(this, "consume(Hconn,MQMD,MQGMO,ByteNuffer,MQCBC)", "XN00M006", info, null);
/*      */       } 
/*      */       
/*  907 */       if (Trace.isOn) {
/*  908 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "poisonous(MQMD,ProviderMessage,ByteBuffer [ ])", 
/*  909 */             Boolean.valueOf(true), 4);
/*      */       }
/*  911 */       return true;
/*      */     } 
/*      */     
/*  914 */     if (Trace.isOn) {
/*  915 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "poisonous(MQMD,ProviderMessage,ByteBuffer [ ])", 
/*  916 */           Boolean.valueOf(false), 5);
/*      */     }
/*  918 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isConnectionBroken(Exception e) {
/*  928 */     if (Trace.isOn) {
/*  929 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "isConnectionBroken(Exception)", new Object[] { e });
/*      */     }
/*      */     
/*  932 */     boolean b = false;
/*  933 */     if (e instanceof JMSException) {
/*  934 */       JMSException je = (JMSException)e;
/*      */       
/*  936 */       b = isConnectionBroken(je.getLinkedException());
/*      */     }
/*  938 */     else if (e instanceof MQException) {
/*  939 */       MQException mqe = (MQException)e;
/*  940 */       b = Reason.isConnectionBroken(mqe.getReason());
/*      */     }
/*  942 */     else if (e instanceof JmqiException) {
/*  943 */       JmqiException jmqie = (JmqiException)e;
/*  944 */       b = Reason.isConnectionBroken(jmqie.getReason());
/*      */     } 
/*  946 */     if (Trace.isOn) {
/*  947 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "isConnectionBroken(Exception)", 
/*  948 */           Boolean.valueOf(b));
/*      */     }
/*  950 */     return b;
/*      */   }
/*      */   
/*      */   protected void deregisterMessageListener() throws JMSException {
/*  954 */     if (Trace.isOn) {
/*  955 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "deregisterMessageListener()");
/*      */     }
/*      */ 
/*      */     
/*  959 */     Pint rc = this.env.newPint();
/*  960 */     Pint cc = this.env.newPint();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  966 */     boolean deregisterCallback = true;
/*      */ 
/*      */ 
/*      */     
/*  970 */     if (this.hObjCached) {
/*  971 */       deregisterCallback = (this.hObjCache.countMessageListenersForQueue(this.phobj) == 1);
/*      */     }
/*      */     
/*  974 */     if (deregisterCallback) {
/*      */ 
/*      */       
/*  977 */       this.helper.lockHconn();
/*      */       try {
/*  979 */         boolean didSuspend = this.helper.suspendAsyncService();
/*      */ 
/*      */         
/*  982 */         MQCBD cbd = computeCBD();
/*      */         
/*  984 */         this.mq.MQCB(this.hconn, 512, cbd, this.hobj, this.env.newMQMD(), this.env.newMQGMO(), cc, rc);
/*      */         
/*  986 */         if (didSuspend) {
/*  987 */           this.helper.resumeAsyncService();
/*      */         }
/*      */ 
/*      */         
/*  991 */         WMQMessageConsumer.checkJmqiCallSuccess("JMSWMQ0021", this.destination
/*  992 */             .getName(), "XMSC_DESTINATION_NAME", cc, rc, this.env, "XN004002", this.helper, this.hconn, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1003 */         this.helper.removeAsyncConsumer();
/*      */       } finally {
/*      */         
/* 1006 */         this.helper.unlockHconn();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1011 */     if (this.hObjCached) {
/* 1012 */       this.hObjCache.detachMessageListenerFromQueue(this.phobj, this);
/*      */     }
/*      */     
/* 1015 */     if (Trace.isOn) {
/* 1016 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "deregisterMessageListener()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initialize() throws JMSException {
/* 1024 */     if (Trace.isOn) {
/* 1025 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "initialize()");
/*      */     }
/*      */     
/* 1028 */     super.initialize();
/*      */     
/* 1030 */     if (Trace.isOn) {
/* 1031 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "initialize()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   ProviderMessage receiveInternal(long timeOut) throws JMSException {
/* 1038 */     if (Trace.isOn)
/* 1039 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "receiveInternal(long)", new Object[] {
/* 1040 */             Long.valueOf(timeOut)
/*      */           }); 
/* 1042 */     if (Trace.isOn) {
/* 1043 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "receiveInternal(long)", null);
/*      */     }
/*      */     
/* 1046 */     return null;
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
/*      */   WMQGMO computeGMO(int waitTime) throws JMSException {
/* 1058 */     if (Trace.isOn)
/* 1059 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "computeGMO(int)", new Object[] {
/* 1060 */             Integer.valueOf(waitTime)
/*      */           }); 
/* 1062 */     WMQGMO gmo = super.computeGMO(waitTime);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1067 */     int options = gmo.getOptions();
/* 1068 */     options &= 0xFFFFFFFE;
/* 1069 */     options |= 0x2000;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1075 */     if ((options & 0x4) == 4) {
/* 1076 */       options &= 0xFFFFFFFB;
/* 1077 */       options |= 0x1000;
/*      */     } 
/*      */     
/* 1080 */     gmo.setOptions(options);
/* 1081 */     if (Trace.isOn) {
/* 1082 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "computeGMO(int)", gmo);
/*      */     }
/*      */     
/* 1085 */     return gmo;
/*      */   }
/*      */   
/*      */   protected void registerMessageListener() throws JMSException {
/* 1089 */     if (Trace.isOn) {
/* 1090 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "registerMessageListener()");
/*      */     }
/*      */     
/* 1093 */     registerMessageListener(false);
/* 1094 */     if (Trace.isOn) {
/* 1095 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "registerMessageListener()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void registerMessageListener(boolean isBrowserShadow) throws JMSException {
/* 1102 */     if (Trace.isOn)
/* 1103 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "registerMessageListener(boolean)", new Object[] {
/* 1104 */             Boolean.valueOf(isBrowserShadow)
/*      */           }); 
/* 1106 */     Pint rc = this.env.newPint();
/* 1107 */     Pint cc = this.env.newPint();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1119 */     boolean registerCallback = true;
/*      */ 
/*      */ 
/*      */     
/* 1123 */     if (this.hObjCached) {
/* 1124 */       this.hObjCache.attachMessageListenerToQueue(this.phobj, this);
/* 1125 */       registerCallback = (this.hObjCache.countMessageListenersForQueue(this.phobj) == 1);
/*      */     } 
/*      */     
/* 1128 */     if (registerCallback) {
/* 1129 */       MQCBD cbd = computeCBD();
/* 1130 */       int options = cbd.getOptions();
/*      */ 
/*      */       
/* 1133 */       options |= 0x2000;
/* 1134 */       options |= 0x4;
/*      */       
/* 1136 */       cbd.setOptions(options);
/*      */       
/* 1138 */       cbd.inhibitESE(isBrowserShadow);
/*      */ 
/*      */       
/* 1141 */       WMQGMO gmo = computeGMO(0);
/* 1142 */       resetMQMD(gmo);
/*      */ 
/*      */ 
/*      */       
/* 1146 */       this.helper.lockHconn();
/*      */       
/*      */       try {
/* 1149 */         boolean didSuspend = this.helper.suspendAsyncService();
/* 1150 */         this.mq.MQCB(this.hconn, 256, cbd, this.hobj, this.mqmd, gmo, cc, rc);
/*      */ 
/*      */         
/* 1153 */         WMQMessageConsumer.checkJmqiCallSuccess("JMSWMQ0020", this.destination
/* 1154 */             .getName(), "XMSC_DESTINATION_NAME", cc, rc, this.env, "XN004001", this.helper, this.hconn, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1164 */         if (didSuspend) {
/* 1165 */           this.helper.resumeAsyncService();
/*      */         }
/*      */ 
/*      */         
/* 1169 */         this.helper.addAsyncConsumer();
/*      */       } finally {
/*      */         
/* 1172 */         this.helper.unlockHconn();
/*      */       } 
/*      */     } 
/*      */     
/* 1176 */     if (Trace.isOn) {
/* 1177 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "registerMessageListener(boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setMessageListener(ProviderMessageListener newListener) throws JMSException {
/* 1185 */     if (Trace.isOn) {
/* 1186 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "setMessageListener(ProviderMessageListener)", "setter", newListener);
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
/* 1197 */     if (this.listener == null) {
/* 1198 */       if (newListener == null)
/*      */       {
/* 1200 */         if (Trace.isOn) {
/* 1201 */           Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "setMessageListener(ProviderMessageListener)", "No operation required, the local listener is null and this method was passed null");
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*      */         
/* 1209 */         this.listener = newListener;
/*      */         
/*      */         try {
/* 1212 */           registerMessageListener();
/*      */         }
/* 1214 */         catch (JMSException e) {
/* 1215 */           if (Trace.isOn) {
/* 1216 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "setMessageListener(ProviderMessageListener)", (Throwable)e);
/*      */           }
/*      */           
/* 1219 */           if (Trace.isOn) {
/* 1220 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "setMessageListener(ProviderMessageListener)", (Throwable)e);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1226 */           this.listener = null;
/*      */ 
/*      */           
/* 1229 */           if (Trace.isOn)
/*      */           {
/* 1231 */             Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "setMessageListener(ProviderMessageListener)", (Throwable)e);
/*      */           }
/* 1233 */           if (Trace.isOn) {
/* 1234 */             Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQAsyncConsumerShadow", "setMessageListener(ProviderMessageListener)", (Throwable)e);
/*      */           }
/*      */           
/* 1237 */           throw e;
/*      */         }
/*      */       
/*      */       }
/*      */     
/* 1242 */     } else if (newListener == null) {
/*      */       
/* 1244 */       deregisterMessageListener();
/* 1245 */       this.listener = null;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1250 */       this.listener = newListener;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\WMQAsyncConsumerShadow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */