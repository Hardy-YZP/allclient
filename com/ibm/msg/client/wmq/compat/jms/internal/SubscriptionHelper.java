/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.jms.BrokerCommandFailedException;
/*      */ import com.ibm.mq.jms.NoBrokerResponseException;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQC;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMessage;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager;
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
/*      */ public class SubscriptionHelper
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/SubscriptionHelper.java";
/*      */   private static final int NON_DURABLE = 0;
/*      */   private static final int DURABLE = 1;
/*      */   private static final int REGISTER_SUBSCRIBER = 0;
/*      */   private static final int DEREGISTER_SUBSCRIBER = 1;
/*      */   private static final int REGISTER_SHAREDQ_SUBSCRIBER = 2;
/*      */   private static final int DEREGISTER_SHAREDQ_SUBSCRIBER = 3;
/*      */   private static final String CLASSNAME = "com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper";
/*      */   private static final int BROKER_TIMEOUT = 120000;
/*      */   
/*      */   static {
/*   64 */     if (Trace.isOn) {
/*   65 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/SubscriptionHelper.java");
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
/*      */   static String validateQueueName(String queueName, int durability) throws JMSException {
/*  107 */     if (Trace.isOn) {
/*  108 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "validateQueueName(String,int)", new Object[] { queueName, 
/*  109 */             Integer.valueOf(durability) });
/*      */     }
/*      */     
/*  112 */     String ret = null;
/*      */     
/*  114 */     if (queueName.trim().equals("")) {
/*      */ 
/*      */ 
/*      */       
/*  118 */       if (Trace.isOn) {
/*  119 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "queueName is blank; reverting to MQJMSv1 approach", null);
/*      */       }
/*      */       
/*  122 */       if (durability == 0) {
/*  123 */         ret = "SYSTEM.JMS.ND.*";
/*      */       } else {
/*  125 */         ret = "SYSTEM.JMS.D.*";
/*      */       } 
/*      */     } else {
/*      */       
/*  129 */       if ((durability == 0 && !queueName.startsWith("SYSTEM.JMS.ND.")) || (durability == 1 && 
/*  130 */         !queueName.startsWith("SYSTEM.JMS.D."))) {
/*      */         
/*  132 */         JMSException je = ConfigEnvironment.newException("MQJMS3021", queueName);
/*      */         
/*  134 */         if (Trace.isOn) {
/*  135 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "validateQueueName(String,int)", (Throwable)je);
/*      */         }
/*      */         
/*  138 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*  142 */       ret = queueName;
/*      */     } 
/*      */     
/*  145 */     if (Trace.isOn) {
/*  146 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "validateQueueName(String,int)", ret);
/*      */     }
/*      */     
/*  149 */     return ret;
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
/*      */   static String createSubscriberQueue(MQQueueManager qm, String queuePrefix) throws JMSException {
/*  167 */     if (Trace.isOn) {
/*  168 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "createSubscriberQueue(MQQueueManager,String)", new Object[] { qm, queuePrefix });
/*      */     }
/*      */     
/*      */     try {
/*  172 */       String retName = null;
/*      */       
/*  174 */       if (!queuePrefix.endsWith("*")) {
/*      */         
/*  176 */         JMSException traceRet1 = ConfigEnvironment.newException("MQJMS3021", queuePrefix);
/*      */         
/*  178 */         if (Trace.isOn) {
/*  179 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "createSubscriberQueue(MQQueueManager,String)", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/*  182 */         throw traceRet1;
/*      */       } 
/*  184 */       if (Trace.isOn) {
/*  185 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Creating a new PERMDYN queue", null);
/*  186 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Model:  SYSTEM.JMS.MODEL.QUEUE", null);
/*  187 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Prefix: " + queuePrefix, null);
/*      */       } 
/*      */       
/*  190 */       MQQueue dynQueue = qm.accessQueue("SYSTEM.JMS.MODEL.QUEUE", 8225, null, queuePrefix, null);
/*      */ 
/*      */       
/*  193 */       retName = dynQueue.name;
/*  194 */       dynQueue.close();
/*  195 */       if (Trace.isOn) {
/*  196 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "createSubscriberQueue(MQQueueManager,String)", retName);
/*      */       }
/*      */       
/*  199 */       return retName;
/*      */     }
/*  201 */     catch (MQException mqe) {
/*  202 */       if (Trace.isOn) {
/*  203 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "createSubscriberQueue(MQQueueManager,String)", (Throwable)mqe);
/*      */       }
/*      */       
/*  206 */       JMSException je = ConfigEnvironment.newException("MQJMS2008", "Queue prefix: " + queuePrefix);
/*      */       
/*  208 */       je.setLinkedException((Exception)mqe);
/*      */       
/*  210 */       if (Trace.isOn) {
/*  211 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "createSubscriberQueue(MQQueueManager,String)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  214 */       throw je;
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
/*      */   static MQQueue getSubscriberQueue(MQQueueManager qm, String queueName) throws JMSException {
/*  233 */     if (Trace.isOn) {
/*  234 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "getSubscriberQueue(MQQueueManager,String)", new Object[] { qm, queueName });
/*      */     }
/*      */     
/*      */     try {
/*  238 */       MQQueue retQueue = null;
/*      */       
/*  240 */       if (queueName.endsWith("*")) {
/*      */ 
/*      */         
/*  243 */         if (Trace.isOn) {
/*  244 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Using multi-queue approach", null);
/*  245 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Model:  SYSTEM.JMS.MODEL.QUEUE", null);
/*  246 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Prefix: " + queueName, null);
/*      */         } 
/*      */         
/*  249 */         retQueue = qm.accessQueue("SYSTEM.JMS.MODEL.QUEUE", 8225, null, queueName, null);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  254 */         if (Trace.isOn) {
/*  255 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Using shared-queue approach", null);
/*      */         }
/*      */         
/*  258 */         retQueue = qm.accessQueue(queueName, 8225);
/*      */       } 
/*      */ 
/*      */       
/*  262 */       if (Trace.isOn) {
/*  263 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "QName:  " + retQueue.name, null);
/*      */       }
/*  265 */       if (Trace.isOn) {
/*  266 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "getSubscriberQueue(MQQueueManager,String)", retQueue);
/*      */       }
/*      */       
/*  269 */       return retQueue;
/*      */     
/*      */     }
/*  272 */     catch (MQException mqe) {
/*  273 */       if (Trace.isOn) {
/*  274 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "getSubscriberQueue(MQQueueManager,String)", (Throwable)mqe);
/*      */       }
/*      */       
/*  277 */       JMSException je = ConfigEnvironment.newException("MQJMS2008", queueName);
/*      */       
/*  279 */       je.setLinkedException((Exception)mqe);
/*      */       
/*  281 */       if (Trace.isOn) {
/*  282 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "getSubscriberQueue(MQQueueManager,String)", (Throwable)je);
/*      */       }
/*      */       
/*  285 */       throw je;
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
/*      */   static void sendBrokerCommand(MQQueueManager qm, BrokerConnectionInfo brk, int brkVersion, int commandType, String topic, String mqBrkSubQName, byte[] correlId, boolean report, String userId) throws JMSException {
/*      */     JMSException je;
/*  314 */     if (Trace.isOn) {
/*  315 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "sendBrokerCommand(MQQueueManager,BrokerConnectionInfo,int,int,String,String,byte [ ],boolean,String)", new Object[] { qm, brk, 
/*      */             
/*  317 */             Integer.valueOf(brkVersion), Integer.valueOf(commandType), topic, mqBrkSubQName, correlId, 
/*  318 */             Boolean.valueOf(report), userId });
/*      */     }
/*      */     
/*  321 */     MQBrokerMessage bm = null;
/*  322 */     switch (brkVersion) {
/*      */       case -1:
/*  324 */         bm = new RFH1BrokerMessageImpl();
/*      */         break;
/*      */       case 0:
/*  327 */         bm = new RFH1BrokerMessageImpl();
/*      */         break;
/*      */       case 1:
/*  330 */         bm = new RFH2BrokerMessageImpl();
/*      */         break;
/*      */       
/*      */       default:
/*  334 */         je = ConfigEnvironment.newException("MQJMS1016");
/*  335 */         if (Trace.isOn) {
/*  336 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "sendBrokerCommand(MQQueueManager,BrokerConnectionInfo,int,int,String,String,byte [ ],boolean,String)", (Throwable)je, 1);
/*      */         }
/*      */ 
/*      */         
/*  340 */         throw je;
/*      */     } 
/*      */ 
/*      */     
/*  344 */     switch (commandType) {
/*      */       case 0:
/*  346 */         bm.set("MQPSCommand", "RegSub");
/*      */         break;
/*      */       case 1:
/*  349 */         bm.set("MQPSCommand", "DeregSub");
/*      */         break;
/*      */       case 2:
/*  352 */         bm.set("MQPSCommand", "RegSub");
/*  353 */         bm.setOption("MQPSRegOpts", 1);
/*      */         break;
/*      */       
/*      */       case 3:
/*  357 */         bm.set("MQPSCommand", "DeregSub");
/*  358 */         bm.setOption("MQPSRegOpts", 1);
/*      */         break;
/*      */ 
/*      */       
/*      */       default:
/*  363 */         je = ConfigEnvironment.newException("MQJMS1016");
/*  364 */         if (Trace.isOn) {
/*  365 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "sendBrokerCommand(MQQueueManager,BrokerConnectionInfo,int,int,String,String,byte [ ],boolean,String)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */         
/*  369 */         throw je;
/*      */     } 
/*      */     
/*  372 */     bm.set("MQPSTopic", topic);
/*  373 */     bm.set("MQPSStreamName", brk.streamQ);
/*  374 */     bm.set("MQPSQName", mqBrkSubQName);
/*      */     
/*  376 */     MQMsg2 msg = new MQMsg2();
/*      */ 
/*      */     
/*      */     try {
/*  380 */       int openOptions = 8208;
/*  381 */       MQPutMessageOptions pmo = new MQPutMessageOptions();
/*  382 */       pmo.options = 8196;
/*  383 */       if (userId != null) {
/*  384 */         msg.setUserId(userId);
/*  385 */         openOptions |= 0x400;
/*  386 */         pmo.options |= 0x400;
/*      */       } 
/*      */ 
/*      */       
/*  390 */       msg.setFormat(bm.getHeaderFormat());
/*  391 */       bm.writeToMessage(msg);
/*  392 */       msg.setReplyToQueueName("SYSTEM.JMS.REPORT.QUEUE");
/*  393 */       if (report) {
/*  394 */         msg.setReport(3);
/*      */       }
/*  396 */       if (correlId != null) {
/*  397 */         msg.setCorrelationId(correlId);
/*      */       }
/*      */ 
/*      */       
/*  401 */       qm.putMsg2(brk.controlQ, brk.qmName, msg, pmo);
/*      */     }
/*  403 */     catch (MQException mqe) {
/*  404 */       if (Trace.isOn) {
/*  405 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "sendBrokerCommand(MQQueueManager,BrokerConnectionInfo,int,int,String,String,byte [ ],boolean,String)", (Throwable)mqe, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  415 */       if (mqe.completionCode != 1 || mqe.reasonCode != 2104) {
/*      */ 
/*      */         
/*  418 */         JMSException jMSException = ConfigEnvironment.newException("MQJMS2007");
/*  419 */         jMSException.setLinkedException((Exception)mqe);
/*  420 */         if (Trace.isOn) {
/*  421 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "sendBrokerCommand(MQQueueManager,BrokerConnectionInfo,int,int,String,String,byte [ ],boolean,String)", (Throwable)jMSException, 3);
/*      */         }
/*      */ 
/*      */         
/*  425 */         throw jMSException;
/*      */       } 
/*      */     } 
/*      */     
/*  429 */     if (report) {
/*      */ 
/*      */ 
/*      */       
/*  433 */       MQMsg2 response = new MQMsg2();
/*  434 */       MQQueue replyQ = null;
/*      */       try {
/*  436 */         replyQ = qm.accessQueue("SYSTEM.JMS.REPORT.QUEUE", 8193);
/*      */         
/*  438 */         response.setCorrelationId(msg.getMessageId());
/*      */         
/*  440 */         MQGetMessageOptions gmo = new MQGetMessageOptions();
/*  441 */         gmo.options = 1;
/*  442 */         gmo.waitInterval = 120000;
/*      */         
/*  444 */         replyQ.getMsg2(response, gmo);
/*      */       
/*      */       }
/*  447 */       catch (MQException mqe) {
/*  448 */         if (Trace.isOn) {
/*  449 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "sendBrokerCommand(MQQueueManager,BrokerConnectionInfo,int,int,String,String,byte [ ],boolean,String)", (Throwable)mqe, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  455 */         NoBrokerResponseException noBrokerResponseException = new NoBrokerResponseException("Unable to retreive report message", Integer.toString(mqe.reasonCode));
/*  456 */         noBrokerResponseException.setLinkedException((Exception)mqe);
/*      */         
/*  458 */         if (Trace.isOn) {
/*  459 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "sendBrokerCommand(MQQueueManager,BrokerConnectionInfo,int,int,String,String,byte [ ],boolean,String)", (Throwable)noBrokerResponseException, 4);
/*      */         }
/*      */ 
/*      */         
/*  463 */         throw noBrokerResponseException;
/*      */       }
/*      */       finally {
/*      */         
/*  467 */         if (Trace.isOn) {
/*  468 */           Trace.finallyBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "sendBrokerCommand(MQQueueManager,BrokerConnectionInfo,int,int,String,String,byte [ ],boolean,String)");
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  473 */         if (replyQ != null) {
/*      */           try {
/*  475 */             replyQ.close();
/*      */           }
/*  477 */           catch (MQException e) {
/*  478 */             if (Trace.isOn) {
/*  479 */               Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "sendBrokerCommand(MQQueueManager,BrokerConnectionInfo,int,int,String,String,byte [ ],boolean,String)", (Throwable)e, 3);
/*      */             }
/*      */ 
/*      */             
/*  483 */             if (Trace.isOn) {
/*  484 */               Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "sendBrokerCommand failed to close replyQ.", null);
/*      */             }
/*      */           } 
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  494 */       checkResponse(response);
/*      */     } 
/*  496 */     if (Trace.isOn) {
/*  497 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "sendBrokerCommand(MQQueueManager,BrokerConnectionInfo,int,int,String,String,byte [ ],boolean,String)");
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
/*      */   static void sendBrokerCommand(MQQueueManager qm, BrokerConnectionInfo brk, int brkVersion, int commandType, String topic, String mqBrkSubQName, byte[] correlId, boolean report) throws JMSException {
/*  511 */     if (Trace.isOn) {
/*  512 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "sendBrokerCommand(MQQueueManager,BrokerConnectionInfo,int,int,String,String,byte [ ],boolean)", new Object[] { qm, brk, 
/*      */             
/*  514 */             Integer.valueOf(brkVersion), Integer.valueOf(commandType), topic, mqBrkSubQName, correlId, 
/*  515 */             Boolean.valueOf(report) });
/*      */     }
/*      */     
/*  518 */     sendBrokerCommand(qm, brk, brkVersion, commandType, topic, mqBrkSubQName, correlId, report, null);
/*      */     
/*  520 */     if (Trace.isOn) {
/*  521 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "sendBrokerCommand(MQQueueManager,BrokerConnectionInfo,int,int,String,String,byte [ ],boolean)");
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
/*      */   static void deleteSubscriber(MQQueueSubscription subscription) throws JMSException {
/*      */     String fullName;
/*  537 */     if (Trace.isOn) {
/*  538 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriber(MQQueueSubscription)", new Object[] { subscription });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  543 */     if (subscription.isDurable()) {
/*  544 */       fullName = subscription.getClientId() + ":" + subscription.getSubName();
/*      */     } else {
/*  546 */       fullName = "";
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  551 */     MQSubEntry subEntry = new MQSubEntry(fullName, subscription.getTopic(), subscription.getQueueName(), subscription.getSelector(), subscription.getNoLocal(), subscription.getStatusMgrId(), subscription.isSharedQueue());
/*      */     
/*  553 */     MQQueueManager qm = subscription.getMQSession().getQM();
/*  554 */     BrokerConnectionInfo brk = subscription.getMQSession().getBrk();
/*  555 */     MQQueue subQ = subscription.getSubscriberQueue();
/*  556 */     subEntry.setSubscriberId(subscription.getCorrelationId());
/*      */     
/*  558 */     deleteSubscriber(qm, brk, subEntry, subQ);
/*  559 */     if (Trace.isOn) {
/*  560 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriber(MQQueueSubscription)");
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
/*      */   static void deleteSubscriber(MQQueueManager qm, BrokerConnectionInfo brk, MQSubEntry subEntry) throws JMSException {
/*  586 */     if (Trace.isOn) {
/*  587 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriber(MQQueueManager,BrokerConnectionInfo,MQSubEntry)", new Object[] { qm, brk, subEntry });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  594 */     MQQueue subQ = accessQueue(qm, subEntry);
/*  595 */     deleteSubscriber(qm, brk, subEntry, subQ);
/*      */     
/*  597 */     if (Trace.isOn) {
/*  598 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriber(MQQueueManager,BrokerConnectionInfo,MQSubEntry)");
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
/*      */   static void deleteSubscriber(MQQueueManager qm, BrokerConnectionInfo brk, MQSubEntry subEntry, MQQueue queue) throws JMSException {
/*  626 */     if (Trace.isOn) {
/*  627 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriber(MQQueueManager,BrokerConnectionInfo,MQSubEntry,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", new Object[] { qm, brk, subEntry, queue });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  634 */     JMSException deregException = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  640 */       int deregType = subEntry.getSharedQueue() ? 3 : 1;
/*      */ 
/*      */ 
/*      */       
/*  644 */       int brokerVer = 0;
/*  645 */       String ctxtUserId = null;
/*  646 */       boolean retry = true;
/*  647 */       while (retry) {
/*      */ 
/*      */         
/*      */         try {
/*  651 */           sendBrokerCommand(qm, brk, brokerVer, deregType, subEntry.getTopic(), subEntry
/*  652 */               .getQName(), subEntry.getSubscriberId(), true, ctxtUserId);
/*  653 */           retry = false;
/*      */         
/*      */         }
/*  656 */         catch (BrokerCommandFailedException e) {
/*  657 */           if (Trace.isOn) {
/*  658 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriber(MQQueueManager,BrokerConnectionInfo,MQSubEntry,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)e, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  663 */           int rc = e.getReason();
/*  664 */           switch (rc) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             case 3073:
/*  676 */               if (brokerVer == 0 || brokerVer == -1) {
/*      */                 
/*  678 */                 if (Trace.isOn) {
/*  679 */                   Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Failed using RFH1 retrying dereg with an RFH2 \n" + e, null);
/*      */                 }
/*      */                 
/*  682 */                 brokerVer = 1;
/*  683 */                 retry = true;
/*      */                 
/*      */                 continue;
/*      */               } 
/*  687 */               brokerCommandFailedException1 = e;
/*  688 */               retry = false;
/*  689 */               if (Trace.isOn) {
/*  690 */                 Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Failed to deregister.", null);
/*      */               }
/*      */               continue;
/*      */ 
/*      */             
/*      */             case 3078:
/*  696 */               if (ctxtUserId == null) {
/*  697 */                 if (Trace.isOn) {
/*  698 */                   Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "retrying deregister with set_id_ctxt", null);
/*      */                 }
/*  700 */                 ctxtUserId = e.getUserId();
/*  701 */                 retry = true;
/*      */                 continue;
/*      */               } 
/*  704 */               if (Trace.isOn) {
/*  705 */                 Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Failed to deregister.", null);
/*      */               }
/*  707 */               brokerCommandFailedException1 = e;
/*  708 */               retry = false;
/*      */               continue;
/*      */ 
/*      */             
/*      */             case 3072:
/*  713 */               if ((brokerVer == 0 || brokerVer == -1) && (subEntry
/*  714 */                 .getTopic().indexOf('+') != -1 || subEntry
/*  715 */                 .getTopic().indexOf('#') != -1 || subEntry
/*  716 */                 .getTopic().indexOf('*') != -1 || subEntry.getTopic().indexOf('?') != -1)) {
/*      */                 
/*  718 */                 if (Trace.isOn) {
/*  719 */                   Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Retrying with RFH2 deregsiter", null);
/*      */                 }
/*  721 */                 brokerVer = 1;
/*  722 */                 retry = true;
/*      */                 continue;
/*      */               } 
/*  725 */               if (Trace.isOn) {
/*  726 */                 Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Failed to deregister.", null);
/*      */               }
/*  728 */               brokerCommandFailedException1 = e;
/*  729 */               retry = false;
/*      */               continue;
/*      */           } 
/*      */           
/*  733 */           BrokerCommandFailedException brokerCommandFailedException1 = e;
/*  734 */           retry = false;
/*  735 */           if (Trace.isOn) {
/*  736 */             Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Failed to deregister.", null);
/*      */           
/*      */           }
/*      */         
/*      */         }
/*  741 */         catch (JMSException je) {
/*  742 */           if (Trace.isOn) {
/*  743 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriber(MQQueueManager,BrokerConnectionInfo,MQSubEntry,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)je, 2);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  748 */           deregException = je;
/*  749 */           retry = false;
/*  750 */           if (Trace.isOn) {
/*  751 */             Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Failed to deregister.", null);
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  759 */       deleteSubscriberMessages(subEntry, queue);
/*      */ 
/*      */       
/*  762 */       if (deregException != null) {
/*  763 */         if (Trace.isOn) {
/*  764 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriber(MQQueueManager,BrokerConnectionInfo,MQSubEntry,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)deregException, 1);
/*      */         }
/*      */ 
/*      */         
/*  768 */         throw deregException;
/*      */       }
/*      */     
/*      */     }
/*  772 */     catch (JMSException je) {
/*  773 */       if (Trace.isOn) {
/*  774 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriber(MQQueueManager,BrokerConnectionInfo,MQSubEntry,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  780 */       if (deregException != null && je != deregException) {
/*  781 */         if (Trace.isOn) {
/*  782 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "NOTE! Throwing pending deregException instead!", null);
/*      */         }
/*  784 */         if (Trace.isOn) {
/*  785 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriber(MQQueueManager,BrokerConnectionInfo,MQSubEntry,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)deregException, 2);
/*      */         }
/*      */ 
/*      */         
/*  789 */         throw deregException;
/*      */       } 
/*      */ 
/*      */       
/*  793 */       if (Trace.isOn) {
/*  794 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriber(MQQueueManager,BrokerConnectionInfo,MQSubEntry,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */       
/*  798 */       throw je;
/*      */     } 
/*      */     
/*  801 */     if (Trace.isOn) {
/*  802 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriber(MQQueueManager,BrokerConnectionInfo,MQSubEntry,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)");
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
/*      */   static void deleteSubscriberMessages(MQQueueManager qm, MQSubEntry subEntry) throws JMSException {
/*  823 */     if (Trace.isOn) {
/*  824 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages(MQQueueManager,MQSubEntry)", new Object[] { qm, subEntry });
/*      */     }
/*      */     
/*  827 */     MQQueue subQ = null;
/*  828 */     String qName = subEntry.getQName();
/*      */     
/*      */     try {
/*  831 */       if (subEntry.getSharedQueue()) {
/*  832 */         if (Trace.isOn) {
/*  833 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Opening queue AS_Q_DEF", null);
/*      */         }
/*  835 */         subQ = qm.accessQueue(qName, 8193);
/*  836 */         if (Trace.isOn) {
/*  837 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Opened OK", null);
/*      */         }
/*      */       } else {
/*  840 */         if (Trace.isOn) {
/*  841 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Opening queue EXCLUSIVE", null);
/*      */         }
/*  843 */         subQ = qm.accessQueue(qName, 8196);
/*  844 */         if (Trace.isOn) {
/*  845 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Opened OK", null);
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*  850 */     catch (MQException mqe) {
/*  851 */       JMSException je; if (Trace.isOn) {
/*  852 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages(MQQueueManager,MQSubEntry)", (Throwable)mqe);
/*      */       }
/*      */       
/*  855 */       switch (mqe.reasonCode) {
/*      */         
/*      */         case 2052:
/*      */         case 2085:
/*  859 */           if (Trace.isOn) {
/*  860 */             Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Warning: SubQ '" + qName + "' missing. Continuing though.", null);
/*      */           }
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/*  867 */           je = ConfigEnvironment.newException("MQJMS3014", qName);
/*      */           
/*  869 */           je.setLinkedException((Exception)mqe);
/*  870 */           if (Trace.isOn) {
/*  871 */             Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages(MQQueueManager,MQSubEntry)", (Throwable)je);
/*      */           }
/*      */           
/*  874 */           throw je;
/*      */       } 
/*      */     
/*      */     } 
/*  878 */     deleteSubscriberMessages(subEntry, subQ);
/*      */     
/*  880 */     if (Trace.isOn) {
/*  881 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages(MQQueueManager,MQSubEntry)");
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
/*      */   static void deleteSubscriberMessages(MQQueueSubscription subscription) throws JMSException {
/*      */     String fullName;
/*  897 */     if (Trace.isOn) {
/*  898 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages(MQQueueSubscription)", new Object[] { subscription });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  903 */     if (subscription.isDurable()) {
/*  904 */       fullName = subscription.getClientId() + ":" + subscription.getSubName();
/*      */     } else {
/*  906 */       fullName = "";
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  911 */     MQSubEntry subEntry = new MQSubEntry(fullName, subscription.getTopic(), subscription.getQueueName(), subscription.getSelector(), subscription.getNoLocal(), subscription.getStatusMgrId(), subscription.isSharedQueue());
/*      */     
/*  913 */     MQQueue subQ = subscription.getSubscriberQueue();
/*  914 */     subEntry.setSubscriberId(subscription.getCorrelationId());
/*      */     
/*  916 */     deleteSubscriberMessages(subEntry, subQ);
/*  917 */     if (Trace.isOn) {
/*  918 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages(MQQueueSubscription)");
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
/*      */   static void deleteSubscriberMessages(MQSubEntry subEntry, MQQueue subQ) throws JMSException {
/*  939 */     if (Trace.isOn) {
/*  940 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages(MQSubEntry,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", new Object[] { subEntry, subQ });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  946 */       if (!subEntry.getSharedQueue() && subQ != null) {
/*      */         
/*      */         try {
/*  949 */           if (Trace.isOn) {
/*  950 */             Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Closing & deleting non-shared queue", null);
/*      */           }
/*      */           
/*  953 */           subQ.closeOptions = 2;
/*  954 */           subQ.close();
/*      */           
/*  956 */           if (Trace.isOn) {
/*  957 */             Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Close & delete completed OK", null);
/*      */           
/*      */           }
/*      */         }
/*  961 */         catch (MQException mqe) {
/*  962 */           if (Trace.isOn) {
/*  963 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages(MQSubEntry,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)mqe, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  969 */           subQ.closeOptions = 0;
/*      */ 
/*      */           
/*  972 */           JMSException je = ConfigEnvironment.newException("MQJMS3017", subEntry
/*  973 */               .getQName());
/*  974 */           je.setLinkedException((Exception)mqe);
/*  975 */           if (Trace.isOn) {
/*  976 */             Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages(MQSubEntry,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)je, 1);
/*      */           }
/*      */ 
/*      */           
/*  980 */           throw je;
/*      */         }
/*      */       
/*  983 */       } else if (subEntry.getSharedQueue() && subQ != null) {
/*      */         
/*      */         try {
/*  986 */           if (Trace.isOn) {
/*  987 */             Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages(e,q) attempting shared queue message cleanup.", null);
/*      */           }
/*      */ 
/*      */           
/*  991 */           MQMessage msg = new MQMessage();
/*  992 */           msg.correlationId = subEntry.getSubscriberId();
/*  993 */           MQGetMessageOptions gmo = new MQGetMessageOptions();
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  998 */           gmo.options = 8256;
/*      */           
/* 1000 */           gmo.matchOptions = 2;
/*      */ 
/*      */ 
/*      */           
/* 1004 */           int cleanupCount = 0;
/* 1005 */           boolean cleanupComplete = false;
/* 1006 */           while (!cleanupComplete) {
/*      */             
/*      */             try {
/* 1009 */               msg.messageId = MQC.MQMI_NONE;
/* 1010 */               subQ.get(msg, gmo, 1);
/* 1011 */               cleanupCount++;
/*      */             
/*      */             }
/* 1014 */             catch (MQException ex) {
/* 1015 */               if (Trace.isOn) {
/* 1016 */                 Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages(MQSubEntry,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)ex, 2);
/*      */               }
/*      */ 
/*      */               
/* 1020 */               if (ex.reasonCode == 2079) {
/*      */                 
/* 1022 */                 cleanupCount++;
/*      */                 
/*      */                 continue;
/*      */               } 
/* 1026 */               cleanupComplete = true;
/* 1027 */               if (Trace.isOn) {
/* 1028 */                 if (ex.reasonCode == 2033) {
/* 1029 */                   Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages cleanup complete: 2033 reached. Removed " + cleanupCount + " messages.", null);
/*      */                   
/*      */                   continue;
/*      */                 } 
/*      */                 
/* 1034 */                 Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages cleanup: Unexpected MQException thrown", null);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1041 */           subQ.close();
/*      */         
/*      */         }
/* 1044 */         catch (MQException mqe) {
/* 1045 */           if (Trace.isOn) {
/* 1046 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages(MQSubEntry,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)mqe, 3);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1051 */           JMSException je = ConfigEnvironment.newException("MQJMS3017", subEntry
/* 1052 */               .getQName());
/* 1053 */           je.setLinkedException((Exception)mqe);
/* 1054 */           if (Trace.isOn) {
/* 1055 */             Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages(MQSubEntry,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)je, 2);
/*      */           }
/*      */ 
/*      */           
/* 1059 */           throw je;
/*      */         }
/*      */       
/*      */       } 
/* 1063 */     } catch (JMSException je) {
/* 1064 */       if (Trace.isOn) {
/* 1065 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages(MQSubEntry,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1070 */       MQException e = (MQException)je.getLinkedException();
/*      */       
/* 1072 */       switch (e.reasonCode) {
/*      */ 
/*      */ 
/*      */         
/*      */         case 2052:
/*      */         case 2085:
/* 1078 */           if (Trace.isOn) {
/* 1079 */             Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages: Warning: subscription queue " + subEntry
/* 1080 */                 .getQName() + " couldn't be found", null);
/*      */           }
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/* 1087 */           if (Trace.isOn) {
/* 1088 */             Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages(MQSubEntry,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)je, 3);
/*      */           }
/*      */ 
/*      */           
/* 1092 */           throw je;
/*      */       } 
/*      */     
/*      */     } 
/* 1096 */     if (Trace.isOn) {
/* 1097 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "deleteSubscriberMessages(MQSubEntry,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)");
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
/*      */   static MQQueue createOrOpenQueue(MQQueueManager qm, String qName, boolean shared) throws MQException {
/* 1120 */     if (Trace.isOn) {
/* 1121 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "createOrOpenQueue(MQQueueManager,String,boolean)", new Object[] { qm, qName, 
/*      */             
/* 1123 */             Boolean.valueOf(shared) });
/*      */     }
/*      */     
/* 1126 */     MQQueue retQ = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1131 */     int openOptions = 8226;
/*      */     
/* 1133 */     if (qName.endsWith("*")) {
/*      */       
/* 1135 */       retQ = qm.accessQueue("SYSTEM.JMS.MODEL.QUEUE", openOptions, null, qName, null);
/*      */     } else {
/*      */       
/* 1138 */       retQ = qm.accessQueue(qName, openOptions);
/*      */     } 
/* 1140 */     if (Trace.isOn) {
/* 1141 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "createOrOpenQueue(MQQueueManager,String,boolean)", retQ);
/*      */     }
/*      */     
/* 1144 */     return retQ;
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
/*      */   private static MQQueue accessQueue(MQQueueManager qm, MQSubEntry e) throws JMSException {
/* 1163 */     if (Trace.isOn) {
/* 1164 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "accessQueue(MQQueueManager,MQSubEntry)", new Object[] { qm, e });
/*      */     }
/*      */ 
/*      */     
/* 1168 */     MQQueue q = null;
/*      */ 
/*      */     
/*      */     try {
/* 1172 */       if (e.getSharedQueue()) {
/* 1173 */         q = qm.accessQueue(e.getQName(), 8193);
/*      */       } else {
/* 1175 */         q = qm.accessQueue(e.getQName(), 8196);
/*      */       }
/*      */     
/*      */     }
/* 1179 */     catch (MQException mqe) {
/* 1180 */       JMSException je; if (Trace.isOn) {
/* 1181 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "accessQueue(MQQueueManager,MQSubEntry)", (Throwable)mqe);
/*      */       }
/*      */       
/* 1184 */       switch (mqe.reasonCode) {
/*      */ 
/*      */         
/*      */         case 2052:
/*      */         case 2085:
/* 1189 */           if (Trace.isOn) {
/* 1190 */             Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Warning: Subscription queue '" + e.getQName() + "' no longer exists.", null);
/*      */           }
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/* 1198 */           je = ConfigEnvironment.newException("MQJMS3014");
/* 1199 */           je.setLinkedException((Exception)mqe);
/* 1200 */           if (Trace.isOn) {
/* 1201 */             Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "accessQueue(MQQueueManager,MQSubEntry)", (Throwable)je);
/*      */           }
/*      */           
/* 1204 */           throw je;
/*      */       } 
/*      */ 
/*      */     
/*      */     } 
/* 1209 */     if (Trace.isOn) {
/* 1210 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "accessQueue(MQQueueManager,MQSubEntry)", q);
/*      */     }
/*      */     
/* 1213 */     return q;
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
/*      */   protected static void checkResponse(MQMsg2 response) throws BrokerCommandFailedException, JMSException {
/*      */     String format;
/* 1229 */     if (Trace.isOn) {
/* 1230 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "checkResponse(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", new Object[] { response });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1236 */     if (response == null) {
/* 1237 */       if (Trace.isOn) {
/* 1238 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "response is NULL", null);
/*      */       }
/* 1240 */       JMSException je = ConfigEnvironment.newException("MQJMS1074");
/* 1241 */       if (Trace.isOn) {
/* 1242 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "checkResponse(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)je, 1);
/*      */       }
/*      */       
/* 1245 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1251 */       if (Trace.isOn) {
/* 1252 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Getting format", null);
/*      */       }
/* 1254 */       format = response.getFormat();
/* 1255 */       if (Trace.isOn) {
/* 1256 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Got format: " + format, null);
/*      */       }
/*      */     }
/* 1259 */     catch (Exception e) {
/* 1260 */       if (Trace.isOn) {
/* 1261 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "checkResponse(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", e, 1);
/*      */       }
/*      */ 
/*      */       
/* 1265 */       JMSException je = ConfigEnvironment.newException("MQJMS1087");
/*      */       
/* 1267 */       if (Trace.isOn) {
/* 1268 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "checkResponse(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1271 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/* 1275 */     if (format.compareTo("MQHRF   ") == 0) {
/* 1276 */       int compCode, reason; String reasonText; if (Trace.isOn) {
/* 1277 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "RFH1 header found", null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1285 */       RFH rfh = null;
/*      */       try {
/* 1287 */         rfh = new RFH(response);
/*      */       
/*      */       }
/* 1290 */       catch (JMSException je) {
/* 1291 */         if (Trace.isOn) {
/* 1292 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "checkResponse(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */         
/* 1296 */         if (Trace.isOn) {
/* 1297 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "checkResponse(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)je, 3);
/*      */         }
/*      */         
/* 1300 */         throw je;
/*      */       } 
/*      */       
/*      */       try {
/* 1304 */         compCode = Integer.parseInt(rfh.getValue("MQPSCompCode"));
/* 1305 */         reason = Integer.parseInt(rfh.getValue("MQPSReason"));
/* 1306 */         reasonText = rfh.getValue("MQPSReasonText");
/*      */         
/* 1308 */         if (Trace.isOn) {
/* 1309 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "MQPSCompCode returned " + compCode, null);
/* 1310 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "MQPSReason returned " + reason, null);
/* 1311 */           if (reasonText != null) {
/* 1312 */             Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "MQPSReasonText returned " + reasonText, null);
/*      */           }
/*      */         }
/*      */       
/* 1316 */       } catch (Exception e) {
/* 1317 */         if (Trace.isOn) {
/* 1318 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "checkResponse(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", e, 3);
/*      */         }
/*      */ 
/*      */         
/* 1322 */         JMSException je = ConfigEnvironment.newException("MQJMS1087");
/* 1323 */         if (Trace.isOn) {
/* 1324 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "checkResponse(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)je, 4);
/*      */         }
/*      */         
/* 1327 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 1331 */       if (compCode == 2) {
/*      */ 
/*      */         
/* 1334 */         BrokerCommandFailedException bcfe = new BrokerCommandFailedException("Broker command failed: " + reasonText + " Reason code " + reason, Integer.toString(reason));
/* 1335 */         bcfe.setReason(reason);
/*      */         
/* 1337 */         if (reason == 3078) {
/*      */           
/* 1339 */           String userId = rfh.getValue("MQPSUserId");
/* 1340 */           bcfe.setUserId(userId);
/*      */         } 
/*      */         
/* 1343 */         if (Trace.isOn) {
/* 1344 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "checkResponse(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)bcfe, 5);
/*      */         }
/*      */         
/* 1347 */         throw bcfe;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1352 */       if (compCode == 1 && reason == 3081) {
/*      */ 
/*      */ 
/*      */         
/* 1356 */         BrokerCommandFailedException bcfe = new BrokerCommandFailedException("Broker command failed: " + reasonText + " Reason code " + reason, Integer.toString(reason));
/* 1357 */         bcfe.setReason(reason);
/*      */         
/* 1359 */         if (Trace.isOn) {
/* 1360 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "checkResponse(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)bcfe, 6);
/*      */         }
/*      */         
/* 1363 */         throw bcfe;
/*      */       } 
/*      */     } else {
/*      */       String str1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1374 */       if (Trace.isOn) {
/* 1375 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "RFH2 header found", null);
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
/*      */       try {
/* 1388 */         RFH2 responseMsg = new RFH2(response);
/* 1389 */         str1 = responseMsg.getNameValueString();
/*      */ 
/*      */         
/* 1392 */         int index1 = str1.indexOf("<pscr>");
/* 1393 */         int index2 = str1.indexOf("</pscr>");
/* 1394 */         if (index1 > 0 && index2 > 0) {
/* 1395 */           str1 = str1.substring(index1, index2 + 7);
/*      */         }
/*      */         
/* 1398 */         if (Trace.isOn) {
/* 1399 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Response NameValue CCSID is " + responseMsg
/* 1400 */               .getCodedCharSetId(), null);
/* 1401 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Response NameValue string is " + str1, null);
/*      */         }
/*      */       
/* 1404 */       } catch (Exception e) {
/* 1405 */         if (Trace.isOn) {
/* 1406 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "checkResponse(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", e, 4);
/*      */         }
/*      */ 
/*      */         
/* 1410 */         JMSException je = ConfigEnvironment.newException("MQJMS1087");
/*      */         
/* 1412 */         if (Trace.isOn) {
/* 1413 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "checkResponse(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)je, 7);
/*      */         }
/*      */         
/* 1416 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 1420 */       if (!str1.startsWith("<pscr><Completion>")) {
/* 1421 */         if (Trace.isOn)
/*      */         {
/* 1423 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "nameValueString does not start with <pscr><Completion>", null);
/*      */         }
/*      */         
/* 1426 */         JMSException je = ConfigEnvironment.newException("MQJMS1087");
/*      */         
/* 1428 */         if (Trace.isOn) {
/* 1429 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "checkResponse(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)je, 8);
/*      */         }
/*      */         
/* 1432 */         throw je;
/*      */       } 
/*      */       
/* 1435 */       if (Trace.isOn) {
/* 1436 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "nameValueString DID start with <pscr><Completion>", null);
/* 1437 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Extract the completion value", null);
/*      */       } 
/*      */       
/* 1440 */       String Completion = str1.substring(str1.indexOf("<Completion>") + 12, str1
/* 1441 */           .indexOf("</Completion>"));
/*      */       
/* 1443 */       if (Completion.compareTo("ok") != 0) {
/*      */ 
/*      */         
/* 1446 */         if (Trace.isOn) {
/* 1447 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Extract the reason value", null);
/*      */         }
/*      */         
/* 1450 */         String ReasonString = str1.substring(str1.indexOf("<Reason>") + 8, str1
/* 1451 */             .indexOf("</Reason>"));
/* 1452 */         if (Trace.isOn) {
/* 1453 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Extract numeric reason code", null);
/*      */         }
/* 1455 */         int reason = Integer.parseInt(ReasonString);
/* 1456 */         if (Trace.isOn) {
/* 1457 */           Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Numeric reason code expected", null);
/*      */         }
/*      */         
/* 1460 */         BrokerCommandFailedException bcfe = new BrokerCommandFailedException("Broker command failed: Reason code " + reason, Integer.toString(reason));
/* 1461 */         bcfe.setReason(reason);
/*      */         
/* 1463 */         if (Trace.isOn) {
/* 1464 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "checkResponse(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)bcfe, 9);
/*      */         }
/*      */         
/* 1467 */         throw bcfe;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1473 */     if (Trace.isOn) {
/* 1474 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "checkResponse(com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)");
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
/*      */   private static void MQPUT1(MQQueueManager qm, String brokerQMName, String qName, MQJMSMessage msg, boolean setIdentityContext) throws JMSException {
/* 1497 */     if (Trace.isOn) {
/* 1498 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "MQPUT1(MQQueueManager,String,String,MQJMSMessage,boolean)", new Object[] { qm, brokerQMName, qName, msg, 
/*      */             
/* 1500 */             Boolean.valueOf(setIdentityContext) });
/*      */     }
/* 1502 */     if (Trace.isOn) {
/* 1503 */       Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "About to perform an MQPUT1. Params follow:", null);
/* 1504 */       Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "   QueueManager:       '" + qm.name + "'", null);
/* 1505 */       Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "   BrokerQueueManager: '" + brokerQMName + "'", null);
/* 1506 */       Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "   Queue:              '" + qName + "'", null);
/* 1507 */       Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "   ProviderMessage:            '" + msg.toString() + "'", null);
/*      */     } 
/*      */ 
/*      */     
/* 1511 */     MQQueue q = null;
/*      */     try {
/* 1513 */       int openOptions = 8208;
/* 1514 */       if (setIdentityContext) {
/* 1515 */         openOptions |= 0x400;
/*      */       }
/* 1517 */       q = qm.accessQueue(qName, openOptions, brokerQMName, null, null);
/*      */     
/*      */     }
/* 1520 */     catch (MQException mqe) {
/* 1521 */       if (Trace.isOn) {
/* 1522 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "MQPUT1(MQQueueManager,String,String,MQJMSMessage,boolean)", (Throwable)mqe, 1);
/*      */       }
/*      */       
/* 1525 */       JMSException je = ConfigEnvironment.newException("MQJMS2008", qName);
/*      */       
/* 1527 */       je.setLinkedException((Exception)mqe);
/*      */       
/* 1529 */       if (Trace.isOn) {
/* 1530 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Failed to access broker queue.", null);
/*      */       }
/* 1532 */       if (Trace.isOn) {
/* 1533 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "MQPUT1(MQQueueManager,String,String,MQJMSMessage,boolean)", (Throwable)je, 1);
/*      */       }
/*      */       
/* 1536 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 1541 */       MQPutMessageOptions pmo = new MQPutMessageOptions();
/* 1542 */       pmo.options |= 0x2000;
/* 1543 */       if (setIdentityContext) {
/* 1544 */         pmo.options |= 0x400;
/*      */       }
/* 1546 */       q.putMsg2(msg, pmo);
/*      */     
/*      */     }
/* 1549 */     catch (MQException mqe) {
/* 1550 */       if (Trace.isOn) {
/* 1551 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "MQPUT1(MQQueueManager,String,String,MQJMSMessage,boolean)", (Throwable)mqe, 2);
/*      */       }
/*      */       
/* 1554 */       JMSException je = ConfigEnvironment.newException("MQJMS3011");
/* 1555 */       je.setLinkedException((Exception)mqe);
/*      */       
/* 1557 */       if (Trace.isOn) {
/* 1558 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Failed to MQPUT broker command.", null);
/*      */       }
/*      */       
/* 1561 */       if (Trace.isOn) {
/* 1562 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "MQPUT1(MQQueueManager,String,String,MQJMSMessage,boolean)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1565 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 1570 */       q.close();
/*      */     
/*      */     }
/* 1573 */     catch (MQException mqe) {
/* 1574 */       if (Trace.isOn) {
/* 1575 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "MQPUT1(MQQueueManager,String,String,MQJMSMessage,boolean)", (Throwable)mqe, 3);
/*      */       }
/*      */ 
/*      */       
/* 1579 */       JMSException je = ConfigEnvironment.newException("MQJMS2000");
/* 1580 */       je.setLinkedException((Exception)mqe);
/*      */       
/* 1582 */       if (Trace.isOn) {
/* 1583 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "Failed to close broker command.", null);
/*      */       }
/*      */       
/* 1586 */       if (Trace.isOn) {
/* 1587 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "MQPUT1(MQQueueManager,String,String,MQJMSMessage,boolean)", (Throwable)je, 3);
/*      */       }
/*      */       
/* 1590 */       throw je;
/*      */     } 
/*      */     
/* 1593 */     if (Trace.isOn) {
/* 1594 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "MQPUT1(MQQueueManager,String,String,MQJMSMessage,boolean)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void MQPUT1(MQQueueManager qm, String brokerQMName, String qName, MQJMSMessage msg) throws JMSException {
/* 1605 */     if (Trace.isOn) {
/* 1606 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "MQPUT1(MQQueueManager,String,String,MQJMSMessage)", new Object[] { qm, brokerQMName, qName, msg });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1611 */     MQPUT1(qm, brokerQMName, qName, msg, false);
/* 1612 */     if (Trace.isOn)
/* 1613 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.SubscriptionHelper", "MQPUT1(MQQueueManager,String,String,MQJMSMessage)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\SubscriptionHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */