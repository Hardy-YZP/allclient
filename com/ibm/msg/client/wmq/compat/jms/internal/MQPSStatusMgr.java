/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.msg.client.commonservices.CSIException;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.commonservices.workqueue.WorkQueueManager;
/*      */ import com.ibm.msg.client.commonservices.workqueue.WorkQueueToken;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQC;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMessage;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager;
/*      */ import java.io.IOException;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Map;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class MQPSStatusMgr
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQPSStatusMgr.java";
/*      */   private static final String JVM_MSG_SIGNATURE = "MQJMS_PS_JVM_STATUS_MSG";
/*      */   private static Hashtable<String, StatusMgrConnection> statusMgrConnections;
/*      */   
/*      */   static {
/*  112 */     if (Trace.isOn) {
/*  113 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQPSStatusMgr.java");
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
/*  143 */   private static Object statusMgrConnectionsLock = new Object();
/*      */ 
/*      */ 
/*      */   
/*      */   private MQQueueManager cleanup_qmgr;
/*      */ 
/*      */ 
/*      */   
/*      */   private String cleanup_controlQName;
/*      */ 
/*      */   
/*      */   private String cleanup_streamQName;
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQPSStatusMgr(MQQueueManager qm, String brokerControlQName, String brokerStreamQName) {
/*  159 */     if (Trace.isOn) {
/*  160 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "<init>(MQQueueManager,String,String)", new Object[] { qm, brokerControlQName, brokerStreamQName });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  165 */     this.cleanup_qmgr = qm;
/*  166 */     this.cleanup_controlQName = brokerControlQName;
/*  167 */     this.cleanup_streamQName = brokerStreamQName;
/*      */     
/*  169 */     if (Trace.isOn) {
/*  170 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "<init>(MQQueueManager,String,String)");
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
/*      */   protected byte[] addConnection(MQConnection topicConn) throws JMSException {
/*      */     String lookupString;
/*  193 */     if (Trace.isOn) {
/*  194 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "addConnection(MQConnection)", new Object[] { topicConn });
/*      */     }
/*      */ 
/*      */     
/*  198 */     Map<String, Object> mqProperties = topicConn.mqProperties;
/*  199 */     String topicConnectionId = topicConn.getConnectionID();
/*      */ 
/*      */     
/*  202 */     StatusMgrConnection conn = null;
/*      */ 
/*      */ 
/*      */     
/*  206 */     if (mqProperties.get("transport").equals("MQSeries Bindings")) {
/*  207 */       lookupString = this.cleanup_qmgr.name.trim();
/*      */     } else {
/*  209 */       lookupString = this.cleanup_qmgr.name.trim() + mqProperties.get("hostname") + mqProperties.get("channel") + mqProperties.get("port");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  215 */     synchronized (statusMgrConnectionsLock) {
/*      */ 
/*      */ 
/*      */       
/*  219 */       if (statusMgrConnections == null) {
/*  220 */         statusMgrConnections = new Hashtable<>();
/*      */       }
/*      */       else {
/*      */         
/*  224 */         conn = statusMgrConnections.get(lookupString);
/*      */       } 
/*      */       
/*  227 */       if (conn != null) {
/*      */ 
/*      */         
/*  230 */         conn.addUser(topicConnectionId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*  240 */           this.cleanup_qmgr.disconnect();
/*  241 */           this.cleanup_qmgr = null;
/*      */         }
/*  243 */         catch (MQException e) {
/*  244 */           if (Trace.isOn) {
/*  245 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "addConnection(MQConnection)", (Throwable)e);
/*      */           }
/*      */           
/*  248 */           JMSException je = ConfigEnvironment.newException("MQJMS3005", e.toString());
/*  249 */           je.setLinkedException((Exception)e);
/*  250 */           if (Trace.isOn) {
/*  251 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "addConnection(MQConnection)", (Throwable)je);
/*      */           }
/*      */           
/*  254 */           throw je;
/*      */         } 
/*      */       } else {
/*      */         
/*  258 */         conn = new StatusMgrConnection(this.cleanup_qmgr, topicConn);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  264 */         statusMgrConnections.put(lookupString, conn);
/*  265 */         this.cleanup_qmgr = null;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  270 */     if (Trace.isOn) {
/*  271 */       Trace.traceData(this, "addConnection: JVM reference id = " + Arrays.toString(conn.jvmReferenceId()), null);
/*      */     }
/*      */     
/*  274 */     byte[] traceRet1 = conn.jvmReferenceId();
/*  275 */     if (Trace.isOn) {
/*  276 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "addConnection(MQConnection)", traceRet1);
/*      */     }
/*      */     
/*  279 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeConnection(String qmgrName, Map<?, ?> mqProperties, String topicConnectionId) throws JMSException {
/*      */     String lookupString;
/*  291 */     if (Trace.isOn) {
/*  292 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "removeConnection(String,Map,String)", new Object[] { qmgrName, mqProperties, topicConnectionId });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  301 */     if (mqProperties.get("transport").equals("MQSeries Bindings")) {
/*  302 */       lookupString = qmgrName;
/*      */     } else {
/*  304 */       lookupString = qmgrName + mqProperties.get("hostname") + mqProperties.get("channel") + mqProperties.get("port");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  310 */     synchronized (statusMgrConnectionsLock) {
/*      */       
/*  312 */       StatusMgrConnection conn = statusMgrConnections.get(lookupString);
/*      */       
/*  314 */       if (conn != null) {
/*      */ 
/*      */ 
/*      */         
/*  318 */         if (conn.removeUser(topicConnectionId) == 0) {
/*  319 */           conn = statusMgrConnections.remove(lookupString);
/*  320 */           if (conn == null)
/*      */           {
/*  322 */             if (Trace.isOn) {
/*  323 */               Trace.traceData(this, "removeConnection error: hashtable remove failed", null);
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  332 */       else if (Trace.isOn) {
/*  333 */         Trace.traceData(this, "removeConnection error: connection not found. Called from TC finalizer after initial close?", null);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  339 */     if (Trace.isOn) {
/*  340 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "removeConnection(String,Map,String)");
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
/*      */   private void cleanup(MQQueueManager qm) {
/*  371 */     if (Trace.isOn) {
/*  372 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "cleanup(MQQueueManager)", new Object[] { qm });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  378 */       BrokerConnectionInfo brk = new BrokerConnectionInfo(null, this.cleanup_controlQName, this.cleanup_streamQName);
/*      */       
/*  380 */       int openOptions = 8218;
/*  381 */       MQQueue statusQueue = qm.accessQueue("SYSTEM.JMS.PS.STATUS.QUEUE", openOptions);
/*      */ 
/*      */       
/*  384 */       int jvmMsgs = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*      */         while (true) {
/*  393 */           boolean unexpectedProblems = false;
/*      */ 
/*      */           
/*  396 */           MQMessage jvmMessage = new MQMessage();
/*  397 */           jvmMessage.correlationId = JMSC.PS_JVM_REFMSG_CORRELID;
/*      */           
/*  399 */           MQGetMessageOptions gmo = new MQGetMessageOptions();
/*      */           
/*  401 */           if (jvmMsgs == 0) {
/*  402 */             gmo.options = 16;
/*      */           
/*      */           }
/*      */           else {
/*      */             
/*  407 */             gmo.options = 32;
/*      */           } 
/*      */           
/*  410 */           statusQueue.get(jvmMessage, gmo);
/*      */ 
/*      */           
/*  413 */           boolean validMsg = false;
/*  414 */           String jvmMsgText = "";
/*  415 */           if (jvmMessage.getMessageLength() > 0) {
/*  416 */             jvmMsgText = jvmMessage.readLine();
/*      */           }
/*      */ 
/*      */           
/*  420 */           if (jvmMsgText.indexOf("MQJMS_PS_JVM_STATUS_MSG") != 0) {
/*  421 */             if (Trace.isOn) {
/*  422 */               Trace.traceData(this, "cleanup: Invalid JVM message signature found within status queue message! Removing.", null);
/*      */             }
/*      */             try {
/*  425 */               statusQueue.get(jvmMessage);
/*      */             }
/*  427 */             catch (MQException e) {
/*  428 */               if (Trace.isOn) {
/*  429 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "cleanup(MQQueueManager)", (Throwable)e, 1);
/*      */               }
/*      */ 
/*      */               
/*  433 */               if (Trace.isOn) {
/*  434 */                 Trace.traceData(this, "cleanup: remove of invalid msg failed, ignoring.", null);
/*      */               }
/*      */             }
/*      */           
/*  438 */           } else if (jvmMsgText.indexOf("backout") != -1) {
/*      */ 
/*      */             
/*  441 */             if (jvmMessage.backoutCount > 0) {
/*  442 */               validMsg = true;
/*      */             
/*      */             }
/*  445 */             else if (Trace.isOn) {
/*  446 */               Trace.traceData(this, "cleanup: invalid backout count detected, ignoring message.", null);
/*      */             } 
/*      */           } else {
/*  449 */             validMsg = true;
/*      */           } 
/*      */ 
/*      */           
/*  453 */           byte[] jvmReferenceId = jvmMessage.messageId;
/*      */           
/*  455 */           if (validMsg) {
/*      */ 
/*      */ 
/*      */             
/*  459 */             int ndRecs = 0;
/*      */             try {
/*      */               while (true) {
/*  462 */                 MQMessage inactiveSubMsg = new MQMessage();
/*      */                 
/*  464 */                 inactiveSubMsg.correlationId = jvmReferenceId;
/*      */ 
/*      */ 
/*      */                 
/*  468 */                 gmo = new MQGetMessageOptions();
/*  469 */                 if (ndRecs == 0) {
/*  470 */                   gmo.options = 16;
/*      */                 }
/*      */                 else {
/*      */                   
/*  474 */                   gmo.options = 32;
/*      */                 } 
/*      */                 
/*  477 */                 statusQueue.get(inactiveSubMsg, gmo);
/*      */ 
/*      */                 
/*  480 */                 MQSubEntry ndSubEntry = new MQSubEntry(inactiveSubMsg);
/*      */ 
/*      */ 
/*      */                 
/*      */                 try {
/*  485 */                   SubscriptionHelper.deleteSubscriber(qm, brk, ndSubEntry);
/*      */                 }
/*  487 */                 catch (JMSException ex) {
/*  488 */                   if (Trace.isOn) {
/*  489 */                     Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "cleanup(MQQueueManager)", (Throwable)ex, 2);
/*      */                   }
/*      */ 
/*      */                   
/*  493 */                   unexpectedProblems = true;
/*  494 */                   if (Trace.isOn) {
/*  495 */                     Trace.traceData(this, "cleanup: Non durable record search caught unexpected exception.", null);
/*      */                   }
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  502 */                 if (!unexpectedProblems) {
/*      */                   try {
/*  504 */                     statusQueue.get(inactiveSubMsg);
/*      */                   }
/*  506 */                   catch (MQException e) {
/*  507 */                     if (Trace.isOn) {
/*  508 */                       Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "cleanup(MQQueueManager)", (Throwable)e, 3);
/*      */                     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/*  517 */                     unexpectedProblems = true;
/*  518 */                     if (Trace.isOn) {
/*  519 */                       Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "cleanup(MQQueueManager)", (Throwable)e, 1);
/*      */                     }
/*      */ 
/*      */                     
/*  523 */                     throw e;
/*      */                   } 
/*      */                 }
/*      */                 
/*  527 */                 ndRecs++;
/*      */               } 
/*      */               break;
/*  530 */             } catch (MQException ex) {
/*  531 */               if (Trace.isOn) {
/*  532 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "cleanup(MQQueueManager)", (Throwable)ex, 4);
/*      */               }
/*      */               
/*  535 */               if (ex.reasonCode == 2033) {
/*  536 */                 if (Trace.isOn) {
/*  537 */                   Trace.traceData(this, "cleanup: Finished retrieving all non durable records (caught 2033). Found " + ndRecs + " records", null);
/*      */                 }
/*      */               } else {
/*  540 */                 unexpectedProblems = true;
/*  541 */                 if (Trace.isOn) {
/*  542 */                   Trace.traceData(this, "cleanup: Non durable record search caught unexpected exception.", null);
/*      */                 }
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/*  548 */               int dRecs = 0;
/*  549 */               boolean msgFound = true;
/*  550 */               MQSubAdmin admin = null;
/*      */               
/*      */               try {
/*  553 */                 admin = new MQSubAdmin(qm);
/*  554 */                 while (msgFound) {
/*      */                   
/*  556 */                   MQSubEntry dSubEntry = null;
/*  557 */                   if (dRecs == 0) {
/*      */ 
/*      */ 
/*      */                     
/*  561 */                     dSubEntry = admin.getResolved(qm, brk, null, jvmReferenceId, false);
/*      */                   }
/*      */                   else {
/*      */                     
/*  565 */                     dSubEntry = admin.getResolved(qm, brk, null, jvmReferenceId, true);
/*      */                   } 
/*      */                   
/*  568 */                   if (dSubEntry != null) {
/*      */ 
/*      */                     
/*  571 */                     if (dSubEntry.getSubscriberState() == 'u') {
/*      */                       
/*      */                       try {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                         
/*  579 */                         dSubEntry.setSubscriberState('i');
/*      */ 
/*      */                         
/*  582 */                         dSubEntry.setStatusMgrId(MQC.MQCI_NONE);
/*      */ 
/*      */                         
/*  585 */                         admin.add(dSubEntry, false);
/*      */ 
/*      */ 
/*      */ 
/*      */                         
/*  590 */                         admin.remove(dSubEntry);
/*      */                       }
/*  592 */                       catch (JMSException je) {
/*  593 */                         if (Trace.isOn) {
/*  594 */                           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "cleanup(MQQueueManager)", (Throwable)je, 5);
/*      */                         }
/*      */ 
/*      */                         
/*  598 */                         unexpectedProblems = true;
/*  599 */                         if (Trace.isOn) {
/*  600 */                           Trace.traceData(this, "cleanup: Durable record search caught unexpected exception.", null);
/*      */                         }
/*      */                       } 
/*      */                     }
/*      */ 
/*      */                     
/*  606 */                     dRecs++; continue;
/*      */                   } 
/*  608 */                   msgFound = false;
/*      */                 } 
/*      */ 
/*      */ 
/*      */                 
/*  613 */                 admin.close();
/*  614 */                 if (Trace.isOn) {
/*  615 */                   Trace.traceData(this, "cleanup: Finished retrieving all durable records. Found " + dRecs + " records", null);
/*      */                 }
/*      */               }
/*  618 */               catch (JMSException je) {
/*  619 */                 if (Trace.isOn) {
/*  620 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "cleanup(MQQueueManager)", (Throwable)je, 6);
/*      */                 }
/*      */ 
/*      */                 
/*  624 */                 if (admin != null) {
/*  625 */                   admin.close();
/*      */                 }
/*  627 */                 unexpectedProblems = true;
/*  628 */                 if (Trace.isOn) {
/*  629 */                   Trace.traceData(this, "cleanup: Durable record search caught unexpected exception.", null);
/*      */                 }
/*      */               } 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               try {
/*  637 */                 gmo.options = 16;
/*  638 */                 statusQueue.get(jvmMessage, gmo);
/*      */ 
/*      */ 
/*      */                 
/*  642 */                 if (!unexpectedProblems) {
/*  643 */                   statusQueue.get(jvmMessage);
/*      */                 }
/*      */               }
/*  646 */               catch (MQException e) {
/*  647 */                 if (Trace.isOn) {
/*  648 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "cleanup(MQQueueManager)", (Throwable)e, 7);
/*      */                 }
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  654 */                 if (Trace.isOn) {
/*  655 */                   Trace.traceData(this, "cleanup: status queue jvm msg remove failed.", null);
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           
/*  661 */           jvmMsgs++;
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  666 */       catch (MQException ex) {
/*  667 */         if (Trace.isOn) {
/*  668 */           Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "cleanup(MQQueueManager)", "Caught expected exception at catch index 8", ex);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  673 */         if (Trace.isOn) {
/*  674 */           if (ex.reasonCode == 2033) {
/*  675 */             Trace.traceData(this, "cleanup: Finished retrieving all JVM reference messages (caught 2033). Found " + jvmMsgs + " msgs", null);
/*      */           } else {
/*  677 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "cleanup(MQQueueManager)", (Throwable)ex, 8);
/*      */           } 
/*  679 */           Trace.traceData(this, "cleanup: JVM ref. msg search caught unexpected exception.", null);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  684 */       catch (IOException ex) {
/*  685 */         if (Trace.isOn) {
/*  686 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "cleanup(MQQueueManager)", ex, 9);
/*      */         }
/*      */         
/*  689 */         if (Trace.isOn) {
/*  690 */           Trace.traceData(this, "cleanup: JVM ref. msg search caught unexpected IO exception.", null);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  695 */       statusQueue.close();
/*      */     
/*      */     }
/*  698 */     catch (RuntimeException rte) {
/*  699 */       if (Trace.isOn) {
/*  700 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "cleanup(MQQueueManager)", rte, 10);
/*      */       }
/*      */       
/*  703 */       if (Trace.isOn) {
/*  704 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "cleanup(MQQueueManager)", rte, 2);
/*      */       }
/*      */       
/*  707 */       throw rte;
/*      */     }
/*  709 */     catch (Exception ex) {
/*  710 */       if (Trace.isOn) {
/*  711 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "cleanup(MQQueueManager)", ex, 11);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  716 */     if (Trace.isOn) {
/*  717 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "cleanup(MQQueueManager)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void close() {
/* 1186 */     if (Trace.isOn) {
/* 1187 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "close()");
/*      */     }
/*      */ 
/*      */     
/* 1191 */     this.cleanup_qmgr = null;
/*      */ 
/*      */     
/* 1194 */     synchronized (statusMgrConnectionsLock) {
/* 1195 */       if (statusMgrConnections != null && 
/* 1196 */         statusMgrConnections.isEmpty()) {
/* 1197 */         statusMgrConnections = null;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1202 */     if (Trace.isOn) {
/* 1203 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQPSStatusMgr", "close()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   class StatusMgrConnection
/*      */     implements Runnable
/*      */   {
/*      */     public static final int MAX_SHUTDOWN_TIME = 5000;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private MQQueueManager qmgr;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private MQQueue statusQueue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1233 */     private byte[] jvmReferenceId = null;
/*      */ 
/*      */ 
/*      */     
/* 1237 */     private Object longRunningTranLock = new Object();
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean longRunningTranWaiting = false;
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean longRunningTranPosted = false;
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean longRunningTranEnd = false;
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean runFinished = false;
/*      */ 
/*      */ 
/*      */     
/*      */     private int refreshDelay;
/*      */ 
/*      */ 
/*      */     
/*      */     private MQConnection topicConn;
/*      */ 
/*      */     
/*      */     private Vector<String> usageVector;
/*      */ 
/*      */     
/*      */     private WorkQueueToken workQueueToken;
/*      */ 
/*      */ 
/*      */     
/*      */     StatusMgrConnection(MQQueueManager qm, MQConnection topicConn) throws JMSException {
/* 1272 */       if (Trace.isOn) {
/* 1273 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "<init>(MQQueueManager,MQConnection)", new Object[] { qm, topicConn });
/*      */       }
/*      */ 
/*      */       
/* 1277 */       this.qmgr = qm;
/* 1278 */       this.topicConn = topicConn;
/*      */       
/* 1280 */       this.refreshDelay = topicConn.getStatusRefreshInterval();
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1285 */         this.statusQueue = qm.accessQueue("SYSTEM.JMS.PS.STATUS.QUEUE", 8210);
/*      */ 
/*      */         
/* 1288 */         putJVMMessage(this.qmgr);
/*      */ 
/*      */         
/* 1291 */         this.usageVector = new Vector<>();
/*      */ 
/*      */         
/* 1294 */         addUser(topicConn.getConnectionID());
/*      */ 
/*      */         
/*      */         try {
/* 1298 */           this.workQueueToken = WorkQueueManager.enqueue(this, 0, false);
/*      */         }
/* 1300 */         catch (CSIException ce) {
/* 1301 */           if (Trace.isOn) {
/* 1302 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "<init>(MQQueueManager,MQConnection)", (Throwable)ce, 1);
/*      */           }
/*      */ 
/*      */           
/* 1306 */           HashMap<String, CSIException> m = new HashMap<>();
/* 1307 */           m.put("exception", ce);
/* 1308 */           Trace.ffst(this, "start()", "XO009001", m, JMSException.class);
/*      */         }
/*      */       
/*      */       }
/* 1312 */       catch (MQException ex) {
/* 1313 */         JMSException je; if (Trace.isOn) {
/* 1314 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "<init>(MQQueueManager,MQConnection)", (Throwable)ex, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1320 */         if (ex.reasonCode == 2085) {
/*      */           
/* 1322 */           je = ConfigEnvironment.newException("MQJMS3014", "SYSTEM.JMS.PS.STATUS.QUEUE");
/*      */         } else {
/* 1324 */           je = ConfigEnvironment.newException("MQJMS3005", ex.toString());
/*      */         } 
/*      */         
/* 1327 */         je.setLinkedException((Exception)ex);
/* 1328 */         if (Trace.isOn) {
/* 1329 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "<init>(MQQueueManager,MQConnection)", (Throwable)je);
/*      */         }
/*      */         
/* 1332 */         throw je;
/*      */       } 
/* 1334 */       if (Trace.isOn) {
/* 1335 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "<init>(MQQueueManager,MQConnection)");
/*      */       }
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
/*      */     void addUser(String topicConnIDToken) {
/* 1348 */       if (Trace.isOn) {
/* 1349 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "addUser(String)", new Object[] { topicConnIDToken });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1354 */       if (!this.usageVector.contains(topicConnIDToken)) {
/* 1355 */         this.usageVector.addElement(topicConnIDToken);
/*      */ 
/*      */       
/*      */       }
/* 1359 */       else if (Trace.isOn) {
/* 1360 */         Trace.traceData(this, "addUser error: token already exists.", null);
/*      */       } 
/*      */       
/* 1363 */       if (Trace.isOn) {
/* 1364 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "addUser(String)");
/*      */       }
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
/*      */     int removeUser(String topicConnIDToken) throws JMSException {
/* 1378 */       if (Trace.isOn) {
/* 1379 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "removeUser(String)", new Object[] { topicConnIDToken });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1384 */       if (this.usageVector.contains(topicConnIDToken))
/*      */       {
/*      */ 
/*      */         
/* 1388 */         this.usageVector.removeElement(topicConnIDToken);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1394 */       int usageCount = this.usageVector.size();
/*      */ 
/*      */       
/* 1397 */       if (usageCount == 0) {
/* 1398 */         if (Trace.isOn) {
/* 1399 */           Trace.traceData(this, "removeUser: no users left; closing connection.", null);
/*      */         }
/* 1401 */         close();
/*      */       } 
/*      */       
/* 1404 */       if (Trace.isOn) {
/* 1405 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "removeUser(String)", 
/* 1406 */             Integer.valueOf(usageCount));
/*      */       }
/* 1408 */       return usageCount;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void putJVMMessage(MQQueueManager qm) throws JMSException {
/* 1418 */       if (Trace.isOn) {
/* 1419 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "putJVMMessage(MQQueueManager)", new Object[] { qm });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1426 */         MQMessage jvmMessage = new MQMessage();
/*      */ 
/*      */ 
/*      */         
/* 1430 */         if (this.jvmReferenceId != null) {
/* 1431 */           jvmMessage.messageId = this.jvmReferenceId;
/*      */         }
/*      */         
/* 1434 */         jvmMessage.correlationId = JMSC.PS_JVM_REFMSG_CORRELID;
/*      */ 
/*      */         
/* 1437 */         jvmMessage.writeString("MQJMS_PS_JVM_STATUS_MSG");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1445 */         jvmMessage.writeString("backout");
/*      */ 
/*      */         
/* 1448 */         this.statusQueue.put(jvmMessage);
/*      */ 
/*      */ 
/*      */         
/* 1452 */         if (this.jvmReferenceId == null) {
/* 1453 */           this.jvmReferenceId = jvmMessage.messageId;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1458 */         MQGetMessageOptions gmo = new MQGetMessageOptions();
/* 1459 */         gmo.options = 2;
/* 1460 */         this.statusQueue.get(jvmMessage, gmo);
/*      */ 
/*      */         
/* 1463 */         jvmMessage.clearMessage();
/*      */         
/* 1465 */         jvmMessage.writeString("MQJMS_PS_JVM_STATUS_MSG");
/*      */         
/* 1467 */         jvmMessage.writeString("commit");
/*      */ 
/*      */         
/* 1470 */         MQPutMessageOptions pmo = new MQPutMessageOptions();
/* 1471 */         pmo.options = 2;
/* 1472 */         this.statusQueue.put(jvmMessage, pmo);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1481 */       catch (MQException e) {
/* 1482 */         if (Trace.isOn) {
/* 1483 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "putJVMMessage(MQQueueManager)", (Throwable)e, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1489 */         JMSException je = ConfigEnvironment.newException("MQJMS3005", e.toString());
/*      */         
/* 1491 */         je.setLinkedException((Exception)e);
/*      */         
/* 1493 */         if (Trace.isOn) {
/* 1494 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "putJVMMessage(MQQueueManager)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 1497 */         throw je;
/*      */       }
/* 1499 */       catch (IOException e) {
/* 1500 */         if (Trace.isOn) {
/* 1501 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "putJVMMessage(MQQueueManager)", e, 2);
/*      */         }
/*      */         
/* 1504 */         JMSException je = ConfigEnvironment.newException("MQJMS3005", e.toString());
/* 1505 */         je.setLinkedException(e);
/* 1506 */         if (Trace.isOn) {
/* 1507 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "putJVMMessage(MQQueueManager)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 1510 */         throw je;
/*      */       } 
/*      */       
/* 1513 */       if (Trace.isOn) {
/* 1514 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "putJVMMessage(MQQueueManager)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void removeJVMMessage(MQQueueManager qm) throws JMSException {
/* 1526 */       if (Trace.isOn) {
/* 1527 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "removeJVMMessage(MQQueueManager)", new Object[] { qm });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1535 */         MQGetMessageOptions gmo = new MQGetMessageOptions();
/* 1536 */         gmo.options = 2;
/*      */ 
/*      */         
/* 1539 */         MQMessage jvmMessage = new MQMessage();
/* 1540 */         jvmMessage.messageId = this.jvmReferenceId;
/*      */ 
/*      */ 
/*      */         
/* 1544 */         jvmMessage.correlationId = JMSC.PS_JVM_REFMSG_CORRELID;
/*      */         
/* 1546 */         this.statusQueue.get(jvmMessage, gmo);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1551 */         if (qm != null) {
/* 1552 */           qm.commit();
/*      */         }
/* 1554 */         else if (Trace.isOn) {
/* 1555 */           Trace.traceData(this, "Saved NullPointerException on qm.commit", null);
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 1560 */       catch (MQException e) {
/* 1561 */         if (Trace.isOn) {
/* 1562 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "removeJVMMessage(MQQueueManager)", (Throwable)e);
/*      */         }
/*      */         
/* 1565 */         JMSException je = ConfigEnvironment.newException("MQJMS3005", e.toString());
/* 1566 */         je.setLinkedException((Exception)e);
/* 1567 */         if (Trace.isOn) {
/* 1568 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "removeJVMMessage(MQQueueManager)", (Throwable)je);
/*      */         }
/*      */         
/* 1571 */         throw je;
/*      */       } 
/*      */       
/* 1574 */       if (Trace.isOn) {
/* 1575 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "removeJVMMessage(MQQueueManager)");
/*      */       }
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
/*      */     private void refreshJVMMessage(MQQueueManager qm) throws JMSException {
/* 1591 */       if (Trace.isOn) {
/* 1592 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "refreshJVMMessage(MQQueueManager)", new Object[] { qm });
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
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1618 */         MQMessage jvmMessage = new MQMessage();
/*      */ 
/*      */ 
/*      */         
/* 1622 */         jvmMessage.messageId = this.jvmReferenceId;
/* 1623 */         jvmMessage.correlationId = JMSC.PS_JVM_REFMSG_CORRELID;
/*      */ 
/*      */         
/* 1626 */         jvmMessage.writeString("MQJMS_PS_JVM_STATUS_MSG");
/* 1627 */         jvmMessage.writeString("backout");
/* 1628 */         this.statusQueue.put(jvmMessage);
/*      */ 
/*      */         
/* 1631 */         MQGetMessageOptions gmo = new MQGetMessageOptions();
/* 1632 */         gmo.options = 2;
/* 1633 */         this.statusQueue.get(jvmMessage, gmo);
/*      */ 
/*      */         
/* 1636 */         qm.commit();
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1641 */         this.statusQueue.get(jvmMessage, gmo);
/*      */ 
/*      */         
/* 1644 */         jvmMessage.clearMessage();
/*      */         
/* 1646 */         jvmMessage.writeString("MQJMS_PS_JVM_STATUS_MSG");
/*      */         
/* 1648 */         jvmMessage.writeString("commit");
/*      */ 
/*      */         
/* 1651 */         MQPutMessageOptions pmo = new MQPutMessageOptions();
/* 1652 */         pmo.options = 2;
/* 1653 */         this.statusQueue.put(jvmMessage, pmo);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1663 */       catch (MQException ex) {
/* 1664 */         if (Trace.isOn) {
/* 1665 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "refreshJVMMessage(MQQueueManager)", (Throwable)ex, 1);
/*      */         }
/*      */         
/* 1668 */         JMSException je = ConfigEnvironment.newException("MQJMS3005", ex.toString());
/* 1669 */         je.setLinkedException((Exception)ex);
/* 1670 */         if (Trace.isOn) {
/* 1671 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "refreshJVMMessage(MQQueueManager)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 1674 */         throw je;
/*      */       }
/* 1676 */       catch (IOException e) {
/* 1677 */         if (Trace.isOn) {
/* 1678 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "refreshJVMMessage(MQQueueManager)", e, 2);
/*      */         }
/*      */         
/* 1681 */         JMSException je = ConfigEnvironment.newException("MQJMS3005", e.toString());
/* 1682 */         je.setLinkedException(e);
/*      */         
/* 1684 */         if (Trace.isOn) {
/* 1685 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "refreshJVMMessage(MQQueueManager)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 1688 */         throw je;
/*      */       } 
/*      */       
/* 1691 */       if (Trace.isOn) {
/* 1692 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "refreshJVMMessage(MQQueueManager)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     byte[] jvmReferenceId() {
/* 1703 */       if (Trace.isOn) {
/* 1704 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "jvmReferenceId()");
/*      */       }
/*      */       
/* 1707 */       if (Trace.isOn) {
/* 1708 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "jvmReferenceId()", this.jvmReferenceId);
/*      */       }
/*      */       
/* 1711 */       return this.jvmReferenceId;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void close() throws JMSException {
/* 1722 */       if (Trace.isOn) {
/* 1723 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "close()");
/*      */       }
/*      */ 
/*      */       
/* 1727 */       boolean WakeUp = false;
/*      */ 
/*      */ 
/*      */       
/* 1731 */       this.workQueueToken.end();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1736 */       synchronized (this.longRunningTranLock) {
/* 1737 */         this.longRunningTranEnd = true;
/* 1738 */         if (this.longRunningTranWaiting) {
/* 1739 */           this.longRunningTranPosted = true;
/* 1740 */           WakeUp = true;
/*      */         } 
/*      */       } 
/*      */       
/* 1744 */       if (Trace.isOn) {
/* 1745 */         Trace.traceData(this, "close ending run() loop", null);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1756 */         if (WakeUp) {
/* 1757 */           MQQueueManager newQM = this.topicConn.createQMNonXA();
/* 1758 */           this.topicConn = null;
/*      */ 
/*      */           
/* 1761 */           int openOptions = 8208;
/* 1762 */           MQQueue statusQueue = newQM.accessQueue("SYSTEM.JMS.PS.STATUS.QUEUE", openOptions);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1767 */           MQMessage terminator = new MQMessage();
/* 1768 */           terminator.messageId = this.jvmReferenceId;
/* 1769 */           terminator.correlationId = JMSC.PS_TERMMSG_CORRELID;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1774 */           terminator.expiry = 36000;
/* 1775 */           terminator.persistence = 0;
/* 1776 */           statusQueue.put(terminator);
/*      */ 
/*      */           
/* 1779 */           newQM.disconnect();
/*      */         } 
/*      */ 
/*      */         
/* 1783 */         waitForRunFinished();
/*      */ 
/*      */         
/* 1786 */         this.usageVector = null;
/* 1787 */         this.qmgr = null;
/* 1788 */         this.jvmReferenceId = null;
/*      */       }
/* 1790 */       catch (MQException e) {
/* 1791 */         if (Trace.isOn) {
/* 1792 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "close()", (Throwable)e);
/*      */         }
/*      */         
/* 1795 */         JMSException je = ConfigEnvironment.newException("MQJMS3005", e.toString());
/* 1796 */         je.setLinkedException((Exception)e);
/*      */         
/* 1798 */         if (Trace.isOn) {
/* 1799 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "close()", (Throwable)je);
/*      */         }
/*      */         
/* 1802 */         throw je;
/*      */       } 
/* 1804 */       if (Trace.isOn) {
/* 1805 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "close()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private synchronized void setRunFinished() {
/* 1815 */       if (Trace.isOn) {
/* 1816 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "setRunFinished()");
/*      */       }
/*      */       
/* 1819 */       this.runFinished = true;
/* 1820 */       notifyAll();
/* 1821 */       if (Trace.isOn) {
/* 1822 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "setRunFinished()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private synchronized void waitForRunFinished() {
/* 1830 */       if (Trace.isOn) {
/* 1831 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "waitForRunFinished()");
/*      */       }
/*      */ 
/*      */       
/* 1835 */       long now = System.currentTimeMillis();
/* 1836 */       long end = now + 5000L;
/*      */       
/* 1838 */       while (!this.runFinished && now < end) {
/*      */         try {
/* 1840 */           wait(end - now);
/*      */         }
/* 1842 */         catch (InterruptedException e) {
/* 1843 */           if (Trace.isOn) {
/* 1844 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "waitForRunFinished()", e);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1849 */         now = System.currentTimeMillis();
/*      */       } 
/*      */       
/* 1852 */       if (Trace.isOn) {
/* 1853 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "waitForRunFinished()");
/*      */       }
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
/*      */     public void run() {
/* 1882 */       if (Trace.isOn) {
/* 1883 */         Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "run()");
/*      */       }
/*      */       
/* 1886 */       boolean missingTerminator = false;
/*      */ 
/*      */ 
/*      */       
/* 1890 */       MQPSStatusMgr.this.cleanup(this.qmgr);
/*      */ 
/*      */       
/*      */       try {
/* 1894 */         MQMessage terminator = new MQMessage();
/* 1895 */         MQGetMessageOptions gmo = new MQGetMessageOptions();
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1900 */         synchronized (this.longRunningTranLock) {
/* 1901 */           this.longRunningTranWaiting = !this.longRunningTranEnd;
/*      */         } 
/*      */         
/* 1904 */         while (this.longRunningTranWaiting) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1910 */           terminator.messageId = this.jvmReferenceId;
/* 1911 */           terminator.correlationId = JMSC.PS_TERMMSG_CORRELID;
/*      */           
/* 1913 */           gmo.options = 1;
/* 1914 */           gmo.waitInterval = this.refreshDelay;
/*      */ 
/*      */           
/*      */           try {
/* 1918 */             if (Trace.isOn) {
/* 1919 */               Trace.traceData(this, "run() attempting get/wait", null);
/*      */             }
/* 1921 */             this.statusQueue.get(terminator, gmo);
/*      */             
/* 1923 */             synchronized (this.longRunningTranLock) {
/* 1924 */               this.longRunningTranWaiting = false;
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/* 1929 */             if (!this.longRunningTranEnd)
/*      */             {
/* 1931 */               if (Trace.isOn) {
/* 1932 */                 Trace.traceData(this, "run() error: invalid message retrieved?", null);
/*      */               
/*      */               }
/*      */             }
/*      */           }
/* 1937 */           catch (MQException ex) {
/* 1938 */             if (Trace.isOn) {
/* 1939 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "run()", (Throwable)ex, 1);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1947 */             if (ex.reasonCode == 2033) {
/* 1948 */               if (Trace.isOn) {
/* 1949 */                 Trace.traceData(this, "run() get/wait finished with 2033 - refreshing JVM msg", null);
/*      */               }
/*      */ 
/*      */               
/* 1953 */               refreshJVMMessage(this.qmgr);
/* 1954 */               synchronized (this.longRunningTranLock) {
/* 1955 */                 if (this.longRunningTranPosted)
/*      */                 {
/*      */ 
/*      */ 
/*      */                   
/* 1960 */                   if (missingTerminator) {
/* 1961 */                     this.longRunningTranWaiting = false;
/*      */                   } else {
/* 1963 */                     missingTerminator = true;
/*      */                   } 
/*      */                 }
/*      */               } 
/*      */ 
/*      */               
/*      */               continue;
/*      */             } 
/*      */ 
/*      */             
/* 1973 */             if (Trace.isOn) {
/* 1974 */               Trace.traceData(this, "run() error: caught unexpected exception attempting get/wait.", null);
/*      */             }
/*      */             
/* 1977 */             JMSException je = ConfigEnvironment.newException("MQJMS3005", ex.toString());
/* 1978 */             je.setLinkedException((Exception)ex);
/*      */             
/* 1980 */             if (Trace.isOn) {
/* 1981 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "run()", (Throwable)je);
/*      */             }
/*      */             
/* 1984 */             throw je;
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
/* 1997 */         removeJVMMessage(this.qmgr);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 2004 */       catch (JMSException je) {
/* 2005 */         if (Trace.isOn) {
/* 2006 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "run()", (Throwable)je, 2);
/*      */         }
/*      */       }
/*      */       finally {
/*      */         
/* 2011 */         if (Trace.isOn) {
/* 2012 */           Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "run()");
/*      */         }
/*      */ 
/*      */         
/*      */         try {
/* 2017 */           this.qmgr.disconnect();
/*      */         
/*      */         }
/* 2020 */         catch (Exception ex) {
/* 2021 */           if (Trace.isOn) {
/* 2022 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "run()", ex, 3);
/*      */           }
/*      */         } 
/*      */         
/* 2026 */         this.qmgr = null;
/*      */ 
/*      */         
/* 2029 */         setRunFinished();
/*      */       } 
/*      */ 
/*      */       
/* 2033 */       if (Trace.isOn)
/* 2034 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.StatusMgrConnection", "run()"); 
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQPSStatusMgr.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */