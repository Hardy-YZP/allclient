/*      */ package com.ibm.mq;
/*      */ 
/*      */ import com.ibm.mq.constants.CMQC;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.handles.Hobj;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.IOException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.Enumeration;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQQueue
/*      */   extends MQDestination
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQQueue.java";
/*      */   
/*      */   static {
/*   90 */     if (Trace.isOn) {
/*   91 */       Trace.data("com.ibm.mq.MQQueue", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQQueue.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  101 */   protected MQQueueManager mgr = null;
/*      */   
/*      */   private static final int MQIA_CURRENT_Q_DEPTH = 3;
/*      */   
/*      */   private static final int MQIA_DEFINITION_TYPE = 7;
/*      */   
/*      */   private static final int MQIA_Q_TYPE = 20;
/*      */   
/*      */   private static final int MQIA_MAX_Q_DEPTH = 15;
/*      */   
/*      */   private static final int MQIA_MAX_MSG_LENGTH = 13;
/*      */   
/*      */   private static final int MQIA_OPEN_INPUT_COUNT = 17;
/*      */   
/*      */   private static final int MQIA_OPEN_OUTPUT_COUNT = 18;
/*      */   
/*      */   private static final int MQIA_SHAREABILITY = 23;
/*      */   
/*      */   private static final int MQIA_INHIBIT_PUT = 10;
/*      */   
/*      */   private static final int MQIA_INHIBIT_GET = 9;
/*      */   
/*      */   private static final int MQIA_TRIGGER_CONTROL = 24;
/*      */   
/*      */   private static final int CMQCA_TRIGGER_DATA = 2023;
/*      */   
/*      */   private static final int MQ_TRIGGER_DATA_LENGTH = 64;
/*      */   
/*      */   private static final int MQIA_TRIGGER_DEPTH = 29;
/*      */   
/*      */   private static final int MQIA_TRIGGER_MSG_PRIORITY = 26;
/*      */   private static final int MQIA_TRIGGER_TYPE = 28;
/*      */   private static final int MQIA_NPM_CLASS = 78;
/*      */   private static final int CMQCA_REMOTE_Q_MGR_NAME = 2017;
/*      */   private static final int CMQCA_REMOTE_Q_NAME = 2018;
/*      */   private static final int CMQCA_BASE_Q_NAME = 2002;
/*      */   private static final int MQNPM_CLASS_HIGH = 10;
/*      */   private static final int MQ_Q_NAME_LENGTH = 48;
/*      */   
/*      */   protected MQQueue() {
/*  141 */     super(1);
/*  142 */     if (Trace.isOn) {
/*  143 */       Trace.entry(this, "com.ibm.mq.MQQueue", "<init>()");
/*      */     }
/*      */     
/*  146 */     if (Trace.isOn) {
/*  147 */       Trace.exit(this, "com.ibm.mq.MQQueue", "<init>()");
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
/*      */   public MQQueue(MQQueueManager qMgr, String queueName, int openOptions, String queueManagerName, String dynamicQueueName, String alternateUserId) throws MQException {
/*  207 */     this(qMgr, queueName, openOptions, queueManagerName, dynamicQueueName, alternateUserId, 1);
/*      */   }
/*      */   
/*      */   protected MQQueue(MQQueueManager qMgr, String queueName, int openOptions, String queueManagerName, String dynamicQueueName, String alternateUserId, int objectType) throws MQException {
/*  211 */     super(objectType, qMgr, queueName, openOptions, alternateUserId);
/*  212 */     if (Trace.isOn) {
/*  213 */       Trace.entry(this, "com.ibm.mq.MQQueue", "<init>(MQQueueManager,String,int,String,String,String)", new Object[] { qMgr, queueName, 
/*      */             
/*  215 */             Integer.valueOf(openOptions), queueManagerName, dynamicQueueName, alternateUserId });
/*      */     }
/*      */     
/*  218 */     String fid = "<init>(MQQueueManager,String,int,String,String,String)";
/*      */ 
/*      */     
/*  221 */     this.mgr = qMgr;
/*  222 */     this.mqca_description = 2013;
/*      */     
/*  224 */     MQOD mqObjectDescriptor = createMQOD();
/*  225 */     if (isValidStringParameter(queueManagerName)) {
/*  226 */       mqObjectDescriptor.setObjectQMgrName(queueManagerName);
/*      */     }
/*  228 */     if (isValidStringParameter(dynamicQueueName)) {
/*  229 */       mqObjectDescriptor.setDynamicQName(dynamicQueueName);
/*      */     }
/*      */     
/*  232 */     if (Trace.isOn) {
/*  233 */       Trace.data(this, "<init>(MQQueueManager,String,int,String,String,String)", "queue = " + mqObjectDescriptor.getObjectName() + "\nqueue manager = " + mqObjectDescriptor
/*  234 */           .getObjectQMgrName() + "\ndynamic queue name = " + mqObjectDescriptor
/*  235 */           .getDynamicQName() + "\nalternate user id = " + mqObjectDescriptor
/*  236 */           .getAlternateUserId() + "\noptions = " + openOptions, "");
/*      */     }
/*      */ 
/*      */     
/*  240 */     open(mqObjectDescriptor);
/*      */     
/*  242 */     qMgr.registerQueue(this);
/*      */     
/*  244 */     if (Trace.isOn) {
/*  245 */       Trace.exit(this, "com.ibm.mq.MQQueue", "<init>(MQQueueManager,String,int,String,String,String)");
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
/*      */   public void putMsg2(MQMsg2 message, MQPutMessageOptions putMessageOptions) throws MQException {
/*  266 */     if (Trace.isOn) {
/*  267 */       Trace.entry(this, "com.ibm.mq.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", new Object[] { message, putMessageOptions });
/*      */     }
/*      */     
/*  270 */     String fid = "putMsg2(MQMsg2,MQPutMessageOptions)";
/*  271 */     MQException mqe = null;
/*  272 */     synchronized (this) {
/*  273 */       if (message == null) {
/*  274 */         MQException traceRet1 = new MQException(2, 2026, this, "MQJI028");
/*      */         
/*  276 */         if (Trace.isOn) {
/*  277 */           Trace.throwing(this, "com.ibm.mq.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", traceRet1, 1);
/*      */         }
/*      */         
/*  280 */         throw traceRet1;
/*      */       } 
/*      */       
/*  283 */       if (putMessageOptions == null) {
/*  284 */         MQException traceRet2 = new MQException(2, 2173, this, "MQJI029");
/*  285 */         if (Trace.isOn) {
/*  286 */           Trace.throwing(this, "com.ibm.mq.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", traceRet2, 2);
/*      */         }
/*      */         
/*  289 */         throw traceRet2;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  294 */       putMessageOptions.invalidDestCount = 1;
/*  295 */       putMessageOptions.knownDestCount = 0;
/*  296 */       putMessageOptions.unknownDestCount = 0;
/*      */ 
/*      */       
/*  299 */       if ((putMessageOptions.options & 0x6) == 0) {
/*  300 */         putMessageOptions.options |= 0x4;
/*      */       }
/*      */       
/*  303 */       if (this.osession == null && this.mgr != null) {
/*  304 */         this.osession = this.mgr.getSession();
/*      */       }
/*      */       
/*  307 */       if (!this.connected || this.osession == null) {
/*  308 */         MQException traceRet3 = new MQException(2, 2018, this, "MQJI002");
/*  309 */         if (Trace.isOn) {
/*  310 */           Trace.throwing(this, "com.ibm.mq.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", traceRet3, 3);
/*      */         }
/*      */         
/*  313 */         throw traceRet3;
/*      */       } 
/*      */       
/*  316 */       if (!this.resourceOpen) {
/*  317 */         MQException traceRet4 = new MQException(2, 2019, this, "MQJI027");
/*  318 */         if (Trace.isOn) {
/*  319 */           Trace.throwing(this, "com.ibm.mq.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", traceRet4, 4);
/*      */         }
/*      */         
/*  322 */         throw traceRet4;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  327 */       if ((putMessageOptions.options & 0x300) != 0) {
/*  328 */         if (putMessageOptions.contextReference != null) {
/*  329 */           putMessageOptions.contextReferenceHandle = putMessageOptions.contextReference.getHandle();
/*  330 */           if (putMessageOptions.contextReferenceHandle == -1) {
/*  331 */             MQException traceRet5 = new MQException(2, 2097, this);
/*  332 */             if (Trace.isOn) {
/*  333 */               Trace.throwing(this, "com.ibm.mq.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", traceRet5, 5);
/*      */             }
/*      */             
/*  336 */             throw traceRet5;
/*      */           } 
/*  338 */           if (this.mgr != putMessageOptions.contextReference.mgr) {
/*  339 */             if (Trace.isOn) {
/*  340 */               Trace.data(this, "putMsg2(MQMsg2,MQPutMessageOptions)", "Connection references do not match", "");
/*      */             }
/*  342 */             MQException traceRet6 = new MQException(2, 2097, this);
/*  343 */             if (Trace.isOn) {
/*  344 */               Trace.throwing(this, "com.ibm.mq.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", traceRet6, 6);
/*      */             }
/*      */             
/*  347 */             throw traceRet6;
/*      */           } 
/*  349 */           if (Trace.isOn) {
/*  350 */             Trace.data(this, "putMsg2(MQMsg2,MQPutMessageOptions)", "Obtained context reference handle:", Integer.toString(putMessageOptions.contextReferenceHandle));
/*      */           }
/*      */         } else {
/*  353 */           if (Trace.isOn) {
/*  354 */             Trace.data(this, "putMsg2(MQMsg2,MQPutMessageOptions)", "Context reference queue is null", "");
/*      */           }
/*  356 */           MQException traceRet7 = new MQException(2, 2097, this);
/*  357 */           if (Trace.isOn) {
/*  358 */             Trace.throwing(this, "com.ibm.mq.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", traceRet7, 7);
/*      */           }
/*      */           
/*  361 */           throw traceRet7;
/*      */         } 
/*      */       }
/*      */       
/*  365 */       ByteBuffer msgData = message.getInternalBuffer();
/*  366 */       int messageDataLength = message.getMessageDataLength();
/*      */       
/*  368 */       if (Trace.isOn) {
/*  369 */         Trace.data(this, "putMsg2(MQMsg2,MQPutMessageOptions)", "Message length = ", Integer.toString(messageDataLength));
/*      */         
/*  371 */         Trace.data(this, "putMsg2(MQMsg2,MQPutMessageOptions)", "put options = " + putMessageOptions.options + "\nmessage type = " + message.getMessageType() + "\nencoding = " + message.getEncoding() + "\ncharacter set = " + message
/*  372 */             .getCharacterSet() + "\nformat = " + message.getFormat() + "\nmessage id, correlation id follow:", "");
/*  373 */         Trace.data(this, "putMsg2(MQMsg2,MQPutMessageOptions)", "messageId = ", message.getMessageId());
/*  374 */         Trace.data(this, "putMsg2(MQMsg2,MQPutMessageOptions)", "correlId = ", message.getCorrelationId());
/*      */       } 
/*      */       
/*  377 */       this.osession.MQPUT(this.Hconn.getHconn(), this.Hobj.getHobj(), message, putMessageOptions, messageDataLength, msgData, this.completionCode, this.reason);
/*      */       
/*  379 */       if (Trace.isOn) {
/*  380 */         Trace.data(this, "putMsg2(MQMsg2,MQPutMessageOptions)", "Returned messageId = ", message.getMessageId());
/*      */       }
/*  382 */       if (this.completionCode.x != 0 || this.reason.x != 0) {
/*  383 */         mqe = new MQException(this.completionCode.x, this.reason.x, this, this.osession.getLastJmqiException());
/*      */       }
/*      */     } 
/*      */     
/*  387 */     if (mqe != null) {
/*  388 */       this.parentQmgr.errorOccurred(mqe);
/*  389 */       if (Trace.isOn) {
/*  390 */         Trace.throwing(this, "com.ibm.mq.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)", mqe, 8);
/*      */       }
/*  392 */       throw mqe;
/*      */     } 
/*  394 */     if (Trace.isOn) {
/*  395 */       Trace.exit(this, "com.ibm.mq.MQQueue", "putMsg2(MQMsg2,MQPutMessageOptions)");
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
/*      */   public void putMsg2(MQMsg2 message) throws MQException {
/*  414 */     if (Trace.isOn) {
/*  415 */       Trace.entry(this, "com.ibm.mq.MQQueue", "putMsg2(MQMsg2)", new Object[] { message });
/*      */     }
/*  417 */     putMsg2(message, new MQPutMessageOptions());
/*      */     
/*  419 */     if (Trace.isOn) {
/*  420 */       Trace.exit(this, "com.ibm.mq.MQQueue", "putMsg2(MQMsg2)");
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
/*      */   public synchronized void putForwardMessage(MQMessage message) throws MQException, IOException {
/*  435 */     if (Trace.isOn) {
/*  436 */       Trace.entry(this, "com.ibm.mq.MQQueue", "putForwardMessage(MQMessage)", new Object[] { message });
/*      */     }
/*      */     
/*  439 */     putForwardMessage(message, new MQPutMessageOptions());
/*      */     
/*  441 */     if (Trace.isOn) {
/*  442 */       Trace.exit(this, "com.ibm.mq.MQQueue", "putForwardMessage(MQMessage)");
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
/*      */   public synchronized void putForwardMessage(MQMessage message, MQPutMessageOptions putMessageOptions) throws MQException, IOException {
/*  456 */     if (Trace.isOn) {
/*  457 */       Trace.entry(this, "com.ibm.mq.MQQueue", "putForwardMessage(MQMessage,MQPutMessageOptions)", new Object[] { message, putMessageOptions });
/*      */     }
/*      */     
/*  460 */     if (message == null) {
/*  461 */       MQException traceRet1 = new MQException(2, 2026, this);
/*      */       
/*  463 */       if (Trace.isOn) {
/*  464 */         Trace.throwing(this, "com.ibm.mq.MQQueue", "putForwardMessage(MQMessage,MQPutMessageOptions)", traceRet1, 1);
/*      */       }
/*      */       
/*  467 */       throw traceRet1;
/*      */     } 
/*      */     
/*  470 */     if (putMessageOptions == null) {
/*  471 */       MQException traceRet1 = new MQException(2, 2173, this);
/*      */       
/*  473 */       if (Trace.isOn) {
/*  474 */         Trace.throwing(this, "com.ibm.mq.MQQueue", "putForwardMessage(MQMessage,MQPutMessageOptions)", traceRet1, 2);
/*      */       }
/*      */       
/*  477 */       throw traceRet1;
/*      */     } 
/*      */     
/*  480 */     MQMessage newMsg = new MQMessage();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  485 */     if ((putMessageOptions.options & 0x800000) != 0) {
/*  486 */       newMsg = copyMDFromOldMsgIntoFwdMsg(message, newMsg);
/*  487 */       putMessageOptions.options &= 0xFF7FFFFF;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  493 */     Enumeration<String> propertyNames = message.getPropertyNames("%");
/*  494 */     MQPropertyDescriptor descriptor = new MQPropertyDescriptor();
/*  495 */     while (propertyNames.hasMoreElements()) {
/*      */       
/*  497 */       String name = propertyNames.nextElement();
/*  498 */       Object value = message.getObjectProperty(name, descriptor);
/*  499 */       if (validToCopy(descriptor.copyOptions, 2)) {
/*  500 */         newMsg.setObjectProperty(name, descriptor, value);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  505 */     put(newMsg, putMessageOptions);
/*  506 */     if (Trace.isOn) {
/*  507 */       Trace.exit(this, "com.ibm.mq.MQQueue", "putForwardMessage(MQMessage,MQPutMessageOptions)");
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
/*      */   public synchronized void putReplyMessage(MQMessage message) throws MQException, IOException {
/*  521 */     if (Trace.isOn) {
/*  522 */       Trace.entry(this, "com.ibm.mq.MQQueue", "putReplyMessage(MQMessage)", new Object[] { message });
/*      */     }
/*      */     
/*  525 */     putReplyMessage(message, new MQPutMessageOptions());
/*      */     
/*  527 */     if (Trace.isOn) {
/*  528 */       Trace.exit(this, "com.ibm.mq.MQQueue", "putReplyMessage(MQMessage)");
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
/*      */   public synchronized void putReplyMessage(MQMessage message, MQPutMessageOptions putMessageOptions) throws MQException, IOException {
/*  542 */     if (Trace.isOn) {
/*  543 */       Trace.entry(this, "com.ibm.mq.MQQueue", "putReplyMessage(MQMessage,MQPutMessageOptions)", new Object[] { message, putMessageOptions });
/*      */     }
/*      */     
/*  546 */     if (message == null) {
/*  547 */       MQException traceRet1 = new MQException(2, 2026, this);
/*      */       
/*  549 */       if (Trace.isOn) {
/*  550 */         Trace.throwing(this, "com.ibm.mq.MQQueue", "putReplyMessage(MQMessage,MQPutMessageOptions)", traceRet1, 1);
/*      */       }
/*      */       
/*  553 */       throw traceRet1;
/*      */     } 
/*      */     
/*  556 */     if (putMessageOptions == null) {
/*  557 */       MQException traceRet1 = new MQException(2, 2173, this);
/*      */       
/*  559 */       if (Trace.isOn) {
/*  560 */         Trace.throwing(this, "com.ibm.mq.MQQueue", "putReplyMessage(MQMessage,MQPutMessageOptions)", traceRet1, 2);
/*      */       }
/*      */       
/*  563 */       throw traceRet1;
/*      */     } 
/*      */     
/*  566 */     MQMessage newMsg = new MQMessage();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  571 */     if ((putMessageOptions.options & 0x800000) != 0) {
/*  572 */       newMsg = copyMDFromOldMsgIntoReplyMsg(message, newMsg, putMessageOptions.options);
/*  573 */       putMessageOptions.options &= 0xFF7FFFFF;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  578 */     Enumeration<String> propertyNames = message.getPropertyNames("%");
/*  579 */     MQPropertyDescriptor descriptor = new MQPropertyDescriptor();
/*  580 */     while (propertyNames.hasMoreElements()) {
/*      */       
/*  582 */       String name = propertyNames.nextElement();
/*  583 */       Object value = message.getObjectProperty(name, descriptor);
/*  584 */       if (validToCopy(descriptor.copyOptions, 8)) {
/*  585 */         newMsg.setObjectProperty(name, descriptor, value);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  590 */     put(newMsg, putMessageOptions);
/*  591 */     if (Trace.isOn) {
/*  592 */       Trace.exit(this, "com.ibm.mq.MQQueue", "putReplyMessage(MQMessage,MQPutMessageOptions)");
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
/*      */   public synchronized void putReportMessage(MQMessage message) throws MQException, IOException {
/*  606 */     if (Trace.isOn) {
/*  607 */       Trace.entry(this, "com.ibm.mq.MQQueue", "putReportMessage(MQMessage)", new Object[] { message });
/*      */     }
/*      */     
/*  610 */     putReportMessage(message, new MQPutMessageOptions());
/*      */     
/*  612 */     if (Trace.isOn) {
/*  613 */       Trace.exit(this, "com.ibm.mq.MQQueue", "putReportMessage(MQMessage)");
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
/*      */   public synchronized void putReportMessage(MQMessage message, MQPutMessageOptions putMessageOptions) throws MQException, IOException {
/*  627 */     if (Trace.isOn) {
/*  628 */       Trace.entry(this, "com.ibm.mq.MQQueue", "putReportMessage(MQMessage,MQPutMessageOptions)", new Object[] { message, putMessageOptions });
/*      */     }
/*      */     
/*  631 */     if (message == null) {
/*  632 */       MQException traceRet1 = new MQException(2, 2026, this);
/*      */       
/*  634 */       if (Trace.isOn) {
/*  635 */         Trace.throwing(this, "com.ibm.mq.MQQueue", "putReportMessage(MQMessage,MQPutMessageOptions)", traceRet1, 1);
/*      */       }
/*      */       
/*  638 */       throw traceRet1;
/*      */     } 
/*      */     
/*  641 */     if (putMessageOptions == null) {
/*  642 */       MQException traceRet1 = new MQException(2, 2173, this);
/*      */       
/*  644 */       if (Trace.isOn) {
/*  645 */         Trace.throwing(this, "com.ibm.mq.MQQueue", "putReportMessage(MQMessage,MQPutMessageOptions)", traceRet1, 2);
/*      */       }
/*      */       
/*  648 */       throw traceRet1;
/*      */     } 
/*      */     
/*  651 */     MQMessage newMsg = new MQMessage();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  656 */     newMsg = copyMDFromOldMsgIntoReportMsg(message, newMsg, putMessageOptions.options);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  661 */     Enumeration<String> propertyNames = message.getPropertyNames("%");
/*  662 */     MQPropertyDescriptor descriptor = new MQPropertyDescriptor();
/*  663 */     while (propertyNames.hasMoreElements()) {
/*      */       
/*  665 */       String name = propertyNames.nextElement();
/*  666 */       Object value = message.getObjectProperty(name, descriptor);
/*  667 */       if (validToCopy(descriptor.copyOptions, 16)) {
/*  668 */         newMsg.setObjectProperty(name, descriptor, value);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  673 */     put(newMsg, putMessageOptions);
/*  674 */     if (Trace.isOn) {
/*  675 */       Trace.exit(this, "com.ibm.mq.MQQueue", "putReportMessage(MQMessage,MQPutMessageOptions)");
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
/*      */   private boolean validToCopy(int oldMsgCopyOpts, int newMsgCopyOpts) {
/*  688 */     if (Trace.isOn)
/*  689 */       Trace.entry(this, "com.ibm.mq.MQQueue", "validToCopy(int,int)", new Object[] {
/*  690 */             Integer.valueOf(oldMsgCopyOpts), Integer.valueOf(newMsgCopyOpts)
/*      */           }); 
/*  692 */     boolean traceRet1 = false;
/*  693 */     if ((oldMsgCopyOpts & 0x1) != 0) {
/*  694 */       traceRet1 = true;
/*  695 */     } else if ((oldMsgCopyOpts & 0x16) == newMsgCopyOpts) {
/*  696 */       traceRet1 = true;
/*  697 */     } else if ((oldMsgCopyOpts & newMsgCopyOpts) == newMsgCopyOpts) {
/*  698 */       traceRet1 = true;
/*      */     } 
/*      */     
/*  701 */     if (Trace.isOn) {
/*  702 */       Trace.exit(this, "com.ibm.mq.MQQueue", "validToCopy(int,int)", Boolean.valueOf(traceRet1));
/*      */     }
/*  704 */     return traceRet1;
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
/*      */   private MQMessage copyMDFromOldMsgIntoFwdMsg(MQMessage oldMsg, MQMessage newMsg) throws MQException, IOException {
/*  717 */     if (Trace.isOn) {
/*  718 */       Trace.entry(this, "com.ibm.mq.MQQueue", "copyMDFromOldMsgIntoFwdMsg(MQMessage,MQMessage)", new Object[] { oldMsg, newMsg });
/*      */     }
/*      */     
/*  721 */     newMsg.accountingToken = oldMsg.accountingToken;
/*  722 */     newMsg.applicationIdData = oldMsg.applicationIdData;
/*  723 */     newMsg.applicationOriginData = oldMsg.applicationOriginData;
/*  724 */     newMsg.backoutCount = oldMsg.backoutCount;
/*  725 */     newMsg.characterSet = oldMsg.characterSet;
/*  726 */     newMsg.correlationId = oldMsg.correlationId;
/*  727 */     newMsg.encoding = oldMsg.encoding;
/*  728 */     newMsg.expiry = oldMsg.expiry;
/*  729 */     newMsg.feedback = oldMsg.feedback;
/*  730 */     newMsg.format = oldMsg.format;
/*  731 */     newMsg.groupId = oldMsg.groupId;
/*  732 */     newMsg.messageFlags = oldMsg.messageFlags;
/*  733 */     newMsg.messageId = oldMsg.messageId;
/*  734 */     newMsg.messageSequenceNumber = oldMsg.messageSequenceNumber;
/*  735 */     newMsg.messageType = oldMsg.messageType;
/*  736 */     newMsg.offset = oldMsg.offset;
/*  737 */     newMsg.originalLength = oldMsg.originalLength;
/*  738 */     newMsg.persistence = oldMsg.persistence;
/*  739 */     newMsg.priority = oldMsg.priority;
/*  740 */     newMsg.putApplicationName = oldMsg.putApplicationName;
/*  741 */     newMsg.putApplicationType = oldMsg.putApplicationType;
/*  742 */     newMsg.putDateTime = oldMsg.putDateTime;
/*  743 */     newMsg.replyToQueueManagerName = oldMsg.replyToQueueManagerName;
/*  744 */     newMsg.replyToQueueName = oldMsg.replyToQueueName;
/*  745 */     newMsg.report = oldMsg.report;
/*  746 */     newMsg.userId = oldMsg.userId;
/*  747 */     byte[] b = new byte[oldMsg.getMessageLength()];
/*  748 */     oldMsg.seek(0);
/*  749 */     oldMsg.readFully(b);
/*  750 */     newMsg.write(b);
/*      */     
/*  752 */     if (Trace.isOn) {
/*  753 */       Trace.exit(this, "com.ibm.mq.MQQueue", "copyMDFromOldMsgIntoFwdMsg(MQMessage,MQMessage)", newMsg);
/*      */     }
/*      */     
/*  756 */     return newMsg;
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
/*      */   private MQMessage copyMDFromOldMsgIntoReplyMsg(MQMessage oldMsg, MQMessage newMsg, int putOpts) throws MQException, IOException {
/*  770 */     if (Trace.isOn) {
/*  771 */       Trace.entry(this, "com.ibm.mq.MQQueue", "copyMDFromOldMsgIntoReplyMsg(MQMessage,MQMessage,int)", new Object[] { oldMsg, newMsg, 
/*      */             
/*  773 */             Integer.valueOf(putOpts) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  779 */     if ((oldMsg.report & 0x4000) != 0 && (oldMsg.report & 0x8000000) != 0) {
/*  780 */       newMsg.report = 134217728;
/*      */     } else {
/*  782 */       newMsg.report = 0;
/*      */     } 
/*      */     
/*  785 */     newMsg.messageType = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  790 */     if ((oldMsg.report & 0x4000) != 0) {
/*  791 */       newMsg.expiry = oldMsg.expiry;
/*      */     } else {
/*  793 */       newMsg.expiry = -1;
/*      */     } 
/*      */     
/*  796 */     newMsg.feedback = 0;
/*      */     
/*  798 */     if ((oldMsg.report & 0x80) != 0) {
/*  799 */       newMsg.messageId = oldMsg.messageId;
/*      */     }
/*  801 */     else if ((putOpts & 0x40) == 0) {
/*  802 */       newMsg.messageId = CMQC.MQMI_NONE;
/*      */     } 
/*      */ 
/*      */     
/*  806 */     if ((oldMsg.report & 0x40) != 0) {
/*  807 */       newMsg.correlationId = oldMsg.correlationId;
/*      */     }
/*  809 */     else if ((putOpts & 0x80) == 0) {
/*  810 */       newMsg.correlationId = oldMsg.correlationId;
/*      */     } 
/*      */     
/*  813 */     newMsg.backoutCount = 0;
/*  814 */     newMsg.replyToQueueName = "";
/*  815 */     newMsg.replyToQueueManagerName = "";
/*  816 */     newMsg.groupId = CMQC.MQGI_NONE;
/*  817 */     newMsg.messageSequenceNumber = 1;
/*  818 */     newMsg.offset = 0;
/*  819 */     newMsg.messageFlags = 0;
/*  820 */     newMsg.originalLength = -1;
/*  821 */     byte[] b = new byte[oldMsg.getMessageLength()];
/*  822 */     oldMsg.seek(0);
/*  823 */     oldMsg.readFully(b);
/*  824 */     newMsg.write(b);
/*      */     
/*  826 */     if (Trace.isOn) {
/*  827 */       Trace.exit(this, "com.ibm.mq.MQQueue", "copyMDFromOldMsgIntoReplyMsg(MQMessage,MQMessage,int)", newMsg);
/*      */     }
/*      */     
/*  830 */     return newMsg;
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
/*      */   private MQMessage copyMDFromOldMsgIntoReportMsg(MQMessage oldMsg, MQMessage newMsg, int putOpts) throws MQException, IOException {
/*  844 */     if (Trace.isOn) {
/*  845 */       Trace.entry(this, "com.ibm.mq.MQQueue", "copyMDFromOldMsgIntoReportMsg(MQMessage,MQMessage,int)", new Object[] { oldMsg, newMsg, 
/*      */             
/*  847 */             Integer.valueOf(putOpts) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  853 */     if ((oldMsg.report & 0x4000) != 0 && (oldMsg.report & 0x8000000) != 0) {
/*  854 */       newMsg.report = 134217728;
/*      */     } else {
/*  856 */       newMsg.report = 0;
/*      */     } 
/*      */     
/*  859 */     newMsg.messageType = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  864 */     if ((oldMsg.report & 0x4000) != 0) {
/*  865 */       newMsg.expiry = oldMsg.expiry;
/*      */     } else {
/*  867 */       newMsg.expiry = -1;
/*      */     } 
/*  869 */     newMsg.feedback = oldMsg.feedback;
/*      */     
/*  871 */     if ((oldMsg.report & 0x80) != 0) {
/*  872 */       newMsg.messageId = oldMsg.messageId;
/*      */     }
/*  874 */     else if ((putOpts & 0x40) == 0) {
/*  875 */       newMsg.messageId = CMQC.MQMI_NONE;
/*      */     } 
/*      */ 
/*      */     
/*  879 */     if ((oldMsg.report & 0x40) != 0) {
/*  880 */       newMsg.correlationId = oldMsg.correlationId;
/*      */     }
/*  882 */     else if ((putOpts & 0x80) == 0) {
/*  883 */       newMsg.correlationId = oldMsg.correlationId;
/*      */     } 
/*      */     
/*  886 */     newMsg.backoutCount = 0;
/*  887 */     newMsg.replyToQueueName = "";
/*  888 */     newMsg.replyToQueueManagerName = "";
/*  889 */     byte[] b = new byte[oldMsg.getMessageLength()];
/*  890 */     oldMsg.seek(0);
/*  891 */     oldMsg.readFully(b);
/*  892 */     newMsg.write(b);
/*      */     
/*  894 */     if (Trace.isOn) {
/*  895 */       Trace.exit(this, "com.ibm.mq.MQQueue", "copyMDFromOldMsgIntoReportMsg(MQMessage,MQMessage,int)", newMsg);
/*      */     }
/*      */     
/*  898 */     return newMsg;
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
/*      */   public synchronized void close() throws MQException {
/*  910 */     if (Trace.isOn) {
/*  911 */       Trace.entry(this, "com.ibm.mq.MQQueue", "close()");
/*      */     }
/*  913 */     super.close();
/*  914 */     if (this.connectionReference != null) {
/*  915 */       this.connectionReference.unregisterQueue(this);
/*      */     }
/*  917 */     this.mgr = null;
/*  918 */     this.connectionReference = null;
/*      */     
/*  920 */     if (Trace.isOn) {
/*  921 */       Trace.exit(this, "com.ibm.mq.MQQueue", "close()");
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
/*      */   public int getQueueType() throws MQException {
/*  939 */     int traceRet1 = getInt(20);
/*  940 */     if (Trace.isOn) {
/*  941 */       Trace.data(this, "com.ibm.mq.MQQueue", "getQueueType()", "getter", 
/*  942 */           Integer.valueOf(traceRet1));
/*      */     }
/*  944 */     return traceRet1;
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
/*      */   public int getCurrentDepth() throws MQException {
/*  958 */     int traceRet1 = getInt(3);
/*  959 */     if (Trace.isOn) {
/*  960 */       Trace.data(this, "com.ibm.mq.MQQueue", "getCurrentDepth()", "getter", 
/*  961 */           Integer.valueOf(traceRet1));
/*      */     }
/*  963 */     return traceRet1;
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
/*  980 */     int traceRet1 = getInt(7);
/*  981 */     if (Trace.isOn) {
/*  982 */       Trace.data(this, "com.ibm.mq.MQQueue", "getDefinitionType()", "getter", 
/*  983 */           Integer.valueOf(traceRet1));
/*      */     }
/*  985 */     return traceRet1;
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
/*      */   public int getMaximumDepth() throws MQException {
/*  999 */     int traceRet1 = getInt(15);
/* 1000 */     if (Trace.isOn) {
/* 1001 */       Trace.data(this, "com.ibm.mq.MQQueue", "getMaximumDepth()", "getter", 
/* 1002 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1004 */     return traceRet1;
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
/*      */   public int getMaximumMessageLength() throws MQException {
/* 1017 */     int traceRet1 = getInt(13);
/* 1018 */     if (Trace.isOn) {
/* 1019 */       Trace.data(this, "com.ibm.mq.MQQueue", "getMaximumMessageLength()", "getter", 
/* 1020 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1022 */     return traceRet1;
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
/*      */   public int getOpenInputCount() throws MQException {
/* 1035 */     int traceRet1 = getInt(17);
/* 1036 */     if (Trace.isOn) {
/* 1037 */       Trace.data(this, "com.ibm.mq.MQQueue", "getOpenInputCount()", "getter", 
/* 1038 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1040 */     return traceRet1;
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
/*      */   public int getOpenOutputCount() throws MQException {
/* 1053 */     int traceRet1 = getInt(18);
/* 1054 */     if (Trace.isOn) {
/* 1055 */       Trace.data(this, "com.ibm.mq.MQQueue", "getOpenOutputCount()", "getter", 
/* 1056 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1058 */     return traceRet1;
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
/*      */   public int getShareability() throws MQException {
/* 1074 */     int traceRet1 = getInt(23);
/* 1075 */     if (Trace.isOn) {
/* 1076 */       Trace.data(this, "com.ibm.mq.MQQueue", "getShareability()", "getter", 
/* 1077 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1079 */     return traceRet1;
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
/*      */   public int getInhibitPut() throws MQException {
/* 1095 */     int traceRet1 = getInt(10);
/* 1096 */     if (Trace.isOn) {
/* 1097 */       Trace.data(this, "com.ibm.mq.MQQueue", "getInhibitPut()", "getter", 
/* 1098 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1100 */     return traceRet1;
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
/*      */   public void setInhibitPut(int inhibit) throws MQException {
/* 1116 */     if (Trace.isOn) {
/* 1117 */       Trace.data(this, "com.ibm.mq.MQQueue", "setInhibitPut(int)", "setter", 
/* 1118 */           Integer.valueOf(inhibit));
/*      */     }
/* 1120 */     setInt(10, inhibit);
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
/*      */   public int getInhibitGet() throws MQException {
/* 1136 */     int traceRet1 = getInt(9);
/* 1137 */     if (Trace.isOn) {
/* 1138 */       Trace.data(this, "com.ibm.mq.MQQueue", "getInhibitGet()", "getter", 
/* 1139 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1141 */     return traceRet1;
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
/*      */   public void setInhibitGet(int inhibit) throws MQException {
/* 1157 */     if (Trace.isOn) {
/* 1158 */       Trace.data(this, "com.ibm.mq.MQQueue", "setInhibitGet(int)", "setter", 
/* 1159 */           Integer.valueOf(inhibit));
/*      */     }
/* 1161 */     setInt(9, inhibit);
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
/*      */   public int getTriggerControl() throws MQException {
/* 1177 */     int traceRet1 = getInt(24);
/* 1178 */     if (Trace.isOn) {
/* 1179 */       Trace.data(this, "com.ibm.mq.MQQueue", "getTriggerControl()", "getter", 
/* 1180 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1182 */     return traceRet1;
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
/*      */   public void setTriggerControl(int trigger) throws MQException {
/* 1198 */     if (Trace.isOn) {
/* 1199 */       Trace.data(this, "com.ibm.mq.MQQueue", "setTriggerControl(int)", "setter", 
/* 1200 */           Integer.valueOf(trigger));
/*      */     }
/* 1202 */     setInt(24, trigger);
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
/*      */   public String getTriggerData() throws MQException {
/* 1215 */     String traceRet1 = getString(2023, 64);
/* 1216 */     if (Trace.isOn) {
/* 1217 */       Trace.data(this, "com.ibm.mq.MQQueue", "getTriggerData()", "getter", traceRet1);
/*      */     }
/* 1219 */     return traceRet1;
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
/*      */   public void setTriggerData(String data) throws MQException {
/* 1233 */     if (Trace.isOn) {
/* 1234 */       Trace.data(this, "com.ibm.mq.MQQueue", "setTriggerData(String)", "setter", data);
/*      */     }
/* 1236 */     setString(2023, data, 64);
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
/*      */   public int getTriggerDepth() throws MQException {
/* 1249 */     int traceRet1 = getInt(29);
/* 1250 */     if (Trace.isOn) {
/* 1251 */       Trace.data(this, "com.ibm.mq.MQQueue", "getTriggerDepth()", "getter", 
/* 1252 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1254 */     return traceRet1;
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
/*      */   public void setTriggerDepth(int depth) throws MQException {
/* 1267 */     if (Trace.isOn) {
/* 1268 */       Trace.data(this, "com.ibm.mq.MQQueue", "setTriggerDepth(int)", "setter", 
/* 1269 */           Integer.valueOf(depth));
/*      */     }
/* 1271 */     setInt(29, depth);
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
/*      */   public int getTriggerMessagePriority() throws MQException {
/* 1285 */     int traceRet1 = getInt(26);
/* 1286 */     if (Trace.isOn) {
/* 1287 */       Trace.data(this, "com.ibm.mq.MQQueue", "getTriggerMessagePriority()", "getter", 
/* 1288 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1290 */     return traceRet1;
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
/*      */   public void setTriggerMessagePriority(int priority) throws MQException {
/* 1305 */     if (Trace.isOn) {
/* 1306 */       Trace.data(this, "com.ibm.mq.MQQueue", "setTriggerMessagePriority(int)", "setter", 
/* 1307 */           Integer.valueOf(priority));
/*      */     }
/* 1309 */     setInt(26, priority);
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
/*      */   public int getTriggerType() throws MQException {
/* 1326 */     int traceRet1 = getInt(28);
/* 1327 */     if (Trace.isOn) {
/* 1328 */       Trace.data(this, "com.ibm.mq.MQQueue", "getTriggerType()", "getter", 
/* 1329 */           Integer.valueOf(traceRet1));
/*      */     }
/* 1331 */     return traceRet1;
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
/*      */   public void setTriggerType(int type) throws MQException {
/* 1349 */     if (Trace.isOn) {
/* 1350 */       Trace.data(this, "com.ibm.mq.MQQueue", "setTriggerType(int)", "setter", 
/* 1351 */           Integer.valueOf(type));
/*      */     }
/* 1353 */     setInt(28, type);
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
/*      */   public boolean isNPMClassHigh() {
/* 1365 */     if (Trace.isOn) {
/* 1366 */       Trace.entry(this, "com.ibm.mq.MQQueue", "isNPMClassHigh()");
/*      */     }
/* 1368 */     String fid = "isNPMClassHigh()";
/* 1369 */     MQQueue myQueue = null;
/*      */     
/*      */     try {
/*      */       try {
/* 1373 */         myQueue = this.mgr.accessQueue(this.name, 32);
/*      */       }
/* 1375 */       catch (Exception e) {
/* 1376 */         if (Trace.isOn) {
/* 1377 */           Trace.catchBlock(this, "com.ibm.mq.MQQueue", "isNPMClassHigh()", e, 1);
/*      */         }
/*      */ 
/*      */         
/* 1381 */         myQueue = null;
/*      */       } 
/*      */ 
/*      */       
/* 1385 */       boolean res = (myQueue != null && myQueue.getInt(78) == 10);
/*      */       
/* 1387 */       if (Trace.isOn) {
/* 1388 */         Trace.data(this, "isNPMClassHigh()", "isNPMClassHigh=", Boolean.toString(res));
/*      */       }
/*      */       
/* 1391 */       if (Trace.isOn) {
/* 1392 */         Trace.exit(this, "com.ibm.mq.MQQueue", "isNPMClassHigh()", Boolean.valueOf(res), 1);
/*      */       }
/* 1394 */       return res;
/*      */     }
/* 1396 */     catch (MQException mq) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1521 */       if (Trace.isOn) {
/* 1522 */         Trace.finallyBlock(this, "com.ibm.mq.MQQueue", "isNPMClassHigh()");
/*      */       }
/*      */       try {
/* 1525 */         if (myQueue != null) {
/* 1526 */           myQueue.close();
/*      */         }
/*      */       }
/* 1529 */       catch (MQException mqe) {
/* 1530 */         if (Trace.isOn) {
/* 1531 */           Trace.catchBlock(this, "com.ibm.mq.MQQueue", "isNPMClassHigh()", mqe, 6);
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
/*      */   final int getHandle() {
/* 1546 */     Hobj hobj = CMQC.jmqi_MQHO_UNUSABLE_HOBJ;
/* 1547 */     if (this.resourceOpen) {
/* 1548 */       hobj = this.Hobj.getHobj();
/*      */     }
/* 1550 */     int traceRet1 = hobj.getIntegerHandle();
/*      */     
/* 1552 */     if (Trace.isOn) {
/* 1553 */       Trace.data(this, "com.ibm.mq.MQQueue", "getHandle()", "getter", Integer.valueOf(traceRet1));
/*      */     }
/* 1555 */     return traceRet1;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */