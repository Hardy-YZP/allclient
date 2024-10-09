/*      */ package com.ibm.msg.client.wmq.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.mq.constants.CMQC;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.JmqiUtils;
/*      */ import com.ibm.mq.jmqi.MQDLH;
/*      */ import com.ibm.mq.jmqi.MQGMO;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.MQPMO;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Hobj;
/*      */ import com.ibm.mq.jmqi.handles.PbyteBuffer;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.mq.jmqi.remote.api.RemoteHobj;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiSP;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.wmq.common.internal.Reason;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQConsumerOwner;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQUtils;
/*      */ import com.ibm.msg.client.wmq.common.internal.messages.WMQBytesMessage;
/*      */ import com.ibm.msg.client.wmq.common.internal.messages.WMQMarshal;
/*      */ import com.ibm.msg.client.wmq.common.internal.messages.WMQMessage;
/*      */ import com.ibm.msg.client.wmq.common.internal.messages.WMQReceiveMarshal;
/*      */ import com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.Arrays;
/*      */ import java.util.Enumeration;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.TimeZone;
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
/*      */ public class WMQPoison
/*      */ {
/*      */   static {
/*   92 */     if (Trace.isOn) {
/*   93 */       Trace.data("com.ibm.msg.client.wmq.internal.WMQPoison", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQPoison.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  100 */   private static String jmsDeadLetterStyleProperty = "com.ibm.msg.client.tuning.JmsDeadLetterStyle";
/*  101 */   private static String V7_JMS_DEAD_LETTER_STYLE = "V7";
/*  102 */   private static String V6_JMS_DEAD_LETTER_STYLE = "V6";
/*  103 */   private String jmsDeadLetterStyle = null; private boolean isBrowser = false;
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQPoison.java";
/*      */   private static final String useDefaultBOValues = "com.ibm.mq.jms.useDefaultBOValues";
/*      */   private JmqiEnvironment env;
/*      */   private JmqiMQ mq;
/*      */   private JmqiSP sp;
/*      */   private Hconn hconn;
/*      */   
/*      */   public void setBrowser(boolean isBrowser) {
/*  112 */     if (Trace.isOn) {
/*  113 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "setBrowser(boolean)", "setter", 
/*  114 */           Boolean.valueOf(isBrowser));
/*      */     }
/*  116 */     this.isBrowser = isBrowser;
/*      */   }
/*      */   private Hobj consumersHobj; private WMQConsumerOwner owner; private WMQDestination destination; private DestinationAttrs destAttrs; private QmAttrs qmAttrs; private String subscriptionQueue; private WMQSession session;
/*      */   static {
/*  120 */     if (Trace.isOn) {
/*  121 */       Trace.entry("com.ibm.msg.client.wmq.internal.WMQPoison", "static()");
/*      */     }
/*  123 */     PropertyStore.register(jmsDeadLetterStyleProperty, V6_JMS_DEAD_LETTER_STYLE, false);
/*  124 */     if (Trace.isOn) {
/*  125 */       Trace.exit("com.ibm.msg.client.wmq.internal.WMQPoison", "static()");
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
/*      */   WMQPoison(WMQConsumerOwner owner, WMQDestination destination, Hobj hobj, String subscriptionQueue) throws JMSException {
/*  160 */     if (Trace.isOn) {
/*  161 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "<init>(WMQConsumerOwner,WMQDestination,Hobj,String)", new Object[] { owner, destination, hobj, subscriptionQueue });
/*      */     }
/*      */ 
/*      */     
/*  165 */     this.owner = owner;
/*  166 */     this.destination = destination;
/*  167 */     this.subscriptionQueue = subscriptionQueue;
/*  168 */     this.env = owner.getJmqiEnvironment();
/*  169 */     this.mq = owner.getJmqiMQ();
/*  170 */     this.sp = (JmqiSP)this.mq;
/*  171 */     this.hconn = owner.getHconn();
/*  172 */     this.consumersHobj = hobj;
/*      */     
/*  174 */     this.jmsDeadLetterStyle = PropertyStore.getStringProperty(jmsDeadLetterStyleProperty);
/*  175 */     this.qmAttrs = new QmAttrs();
/*  176 */     this.destAttrs = new DestinationAttrs();
/*  177 */     if (Trace.isOn) {
/*  178 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "<init>(WMQConsumerOwner,WMQDestination,Hobj,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void setHobj(Hobj hobj) {
/*  185 */     if (Trace.isOn) {
/*  186 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "setHobj(Hobj)", "setter", hobj);
/*      */     }
/*      */     
/*  189 */     this.consumersHobj = hobj;
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
/*      */   boolean shouldMessageBeRequeued(int attempts) {
/*  201 */     if (Trace.isOn) {
/*  202 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "shouldMessageBeRequeued(int)", new Object[] {
/*  203 */             Integer.valueOf(attempts)
/*      */           });
/*      */     }
/*  206 */     if (this.destAttrs.getBOThresh() == 0) {
/*      */       
/*  208 */       if (Trace.isOn) {
/*  209 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "shouldMessageBeRequeued(int)", 
/*  210 */             Boolean.valueOf(false), 1);
/*      */       }
/*  212 */       return false;
/*      */     } 
/*      */     
/*  215 */     boolean requeueit = (this.destAttrs.getBOThresh() <= attempts);
/*  216 */     if (Trace.isOn) {
/*  217 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "shouldMessageBeRequeued(int)", 
/*  218 */           Boolean.valueOf(requeueit), 2);
/*      */     }
/*  220 */     return requeueit;
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
/*      */   void handlePoisonMessage(WMQMessage message) throws JMSException {
/*  233 */     if (Trace.isOn) {
/*  234 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(WMQMessage)", new Object[] { message });
/*      */     }
/*      */ 
/*      */     
/*  238 */     handlePoisonMessage(message, 0);
/*      */     
/*  240 */     if (Trace.isOn) {
/*  241 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(WMQMessage)");
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
/*      */   void handlePoisonMessage(WMQMessage message, int reason) throws JMSException {
/*  259 */     if (Trace.isOn) {
/*  260 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(WMQMessage,int)", new Object[] { message, 
/*  261 */             Integer.valueOf(reason) });
/*      */     }
/*      */     
/*  264 */     PoisonMessage pmsg = new PoisonMessage(message);
/*  265 */     handlePoisonMessage(pmsg, reason, (MQGMO)null);
/*      */     
/*  267 */     if (Trace.isOn) {
/*  268 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(WMQMessage,int)");
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
/*      */   ProviderMessage handlePoisonMessage(MQMD mqmd, ByteBuffer[] msgBuffers) throws JMSException {
/*  290 */     if (Trace.isOn) {
/*  291 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(MQMD,ByteBuffer [ ])", new Object[] { mqmd, msgBuffers });
/*      */     }
/*      */     
/*  294 */     ProviderMessage provMsg = handlePoisonMessage(mqmd, msgBuffers, (MQGMO)null);
/*  295 */     if (Trace.isOn) {
/*  296 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(MQMD,ByteBuffer [ ])", provMsg);
/*      */     }
/*      */     
/*  299 */     return provMsg;
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
/*      */   ProviderMessage handlePoisonMessage(MQMD mqmd, ByteBuffer[] msgBuffers, MQGMO gmo) throws JMSException {
/*  320 */     if (Trace.isOn) {
/*  321 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(MQMD,ByteBuffer [ ],MQGMO)", new Object[] { mqmd, msgBuffers, gmo });
/*      */     }
/*      */ 
/*      */     
/*  325 */     PoisonMessage pmsg = new PoisonMessage(mqmd, msgBuffers);
/*  326 */     ProviderMessage provMsg = handlePoisonMessage(pmsg, 0, gmo);
/*  327 */     if (Trace.isOn) {
/*  328 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(MQMD,ByteBuffer [ ],MQGMO)", provMsg);
/*      */     }
/*      */     
/*  331 */     return provMsg;
/*      */   }
/*      */   
/*      */   private ProviderMessage handlePoisonMessage(PoisonMessage pmsgP, int defaultReason, MQGMO gmo) throws JMSException {
/*  335 */     if (Trace.isOn) {
/*  336 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(PoisonMessage,int,MQGMO)", new Object[] { pmsgP, 
/*      */             
/*  338 */             Integer.valueOf(defaultReason), gmo });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  343 */     boolean closeRequired = false;
/*      */     
/*  345 */     PoisonMessage pmsg = pmsgP;
/*      */ 
/*      */     
/*  348 */     Phobj pHobj = null;
/*      */     
/*  350 */     Hobj previousConsumersHobj = this.consumersHobj;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  358 */       if (this.owner instanceof WMQSession) {
/*  359 */         this.session = (WMQSession)this.owner;
/*      */       }
/*  361 */       else if (this.owner instanceof WMQConnection) {
/*      */ 
/*      */ 
/*      */         
/*  365 */         WMQConnection connection = (WMQConnection)this.owner;
/*  366 */         connection.setIntProperty("XMSC_ACKNOWLEDGE_MODE", 1);
/*  367 */         connection.setBooleanProperty("XMSC_TRANSACTED", false);
/*  368 */         this.session = (WMQSession)connection.createSession((JmsPropertyContext)connection);
/*      */       } else {
/*      */         
/*  371 */         Trace.ffst(this, "handlePoisonMessage", "XN00S001", ffstInfo(), JMSException.class);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  377 */       boolean sessionCleanBeforeBORQ = !this.session.isInSyncPoint();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  383 */       if (this.isBrowser) {
/*  384 */         if (!(this.session instanceof WMQXASession) || ((WMQXASession)this.session).isXASessionActive()) {
/*  385 */           closeRequired = true;
/*  386 */           Pint rc = this.env.newPint();
/*  387 */           pHobj = this.env.newPhobj();
/*  388 */           pmsg = getBrowsedMessage(gmo, rc, pHobj);
/*      */ 
/*      */           
/*  391 */           this.consumersHobj = pHobj.getHobj();
/*      */ 
/*      */ 
/*      */           
/*  395 */           if (rc.x == 2033) {
/*  396 */             if (Trace.isOn) {
/*  397 */               Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(PoisonMessage,int, MQGMO)", null, 4);
/*      */             }
/*  399 */             if (Trace.isOn) {
/*  400 */               Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(PoisonMessage,int,MQGMO)", null, 1);
/*      */             }
/*      */             
/*  403 */             return null;
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  410 */           if (Trace.isOn) {
/*  411 */             Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(PoisonMessage,int,MQGMO)", "Unable to requeue the message as the session is not enlisted in a transaction, MsgId:" + 
/*      */ 
/*      */                 
/*  414 */                 JmqiTools.arrayToHexString(pmsg.mqmd.getMsgId()));
/*  415 */             Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(PoisonMessage,int, MQGMO)", null, 5);
/*      */           } 
/*  417 */           if (Trace.isOn) {
/*  418 */             Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(PoisonMessage,int,MQGMO)", null, 2);
/*      */           }
/*      */           
/*  421 */           return null;
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  427 */       boolean messageDeadLettered = false;
/*  428 */       boolean messageDiscarded = false;
/*      */       
/*  430 */       String qname = null;
/*  431 */       String qmname = null;
/*      */ 
/*      */ 
/*      */       
/*  435 */       int reason = backoutRequeue(pmsg);
/*  436 */       if (reason != 0) {
/*      */ 
/*      */ 
/*      */         
/*  440 */         if ((pmsg.getReport() & 0x8000000) != 134217728) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  446 */           qmname = ((WMQConnection)this.owner.getConnection()).getQueueManagerName();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  453 */           if (this.destAttrs.getRequeueQueueName() == null || this.destAttrs.getRequeueQueueName().length() == 0) {
/*  454 */             if (this.destination.isTopic()) {
/*  455 */               if (this.subscriptionQueue == null)
/*      */               {
/*      */                 
/*  458 */                 Trace.ffst(this, "deadletter", "XN00S002", ffstInfo(), JMSException.class);
/*      */               }
/*      */ 
/*      */               
/*  462 */               qname = this.subscriptionQueue;
/*      */             } else {
/*      */               
/*  465 */               qname = this.destination.getName();
/*      */             } 
/*      */           } else {
/*      */             
/*  469 */             qname = this.destAttrs.getRequeueQueueName();
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  477 */           if (defaultReason != 0) {
/*  478 */             reason = defaultReason;
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  484 */           deadletter(pmsg, qname, qmname, reason);
/*      */ 
/*      */           
/*  487 */           messageDeadLettered = true;
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  492 */           messageDiscarded = true;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  499 */         sendReport(pmsg, reason);
/*      */       } 
/*      */ 
/*      */       
/*  503 */       this.owner.syncpoint(true, sessionCleanBeforeBORQ, this.destination);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  513 */       if (messageDeadLettered)
/*      */       {
/*  515 */         HashMap<String, Object> inserts = new HashMap<>();
/*  516 */         String messageID = JmqiTools.arrayToHexString(pmsg.mqmd.getMsgId());
/*  517 */         inserts.put("XMSC_INSERT_MESSAGE_ID", messageID);
/*  518 */         inserts.put("XMSC_INSERT_DESTINATION_NAME", qname);
/*  519 */         inserts.put("XMSC_INSERT_QUEUE_MANAGER_NAME", qmname);
/*  520 */         Log.log(this, "handlePoisonMessage(PoisonMessage,int,MQGMO)", "JMSWMQ1116", inserts);
/*      */       
/*      */       }
/*  523 */       else if (messageDiscarded)
/*      */       {
/*  525 */         HashMap<String, Object> inserts = new HashMap<>();
/*  526 */         String messageID = JmqiTools.arrayToHexString(pmsg.mqmd.getMsgId());
/*  527 */         inserts.put("XMSC_INSERT_MESSAGE_ID", messageID);
/*  528 */         inserts.put("XMSC_INSERT_DESTINATION_NAME", qname);
/*  529 */         inserts.put("XMSC_INSERT_QUEUE_MANAGER_NAME", qmname);
/*  530 */         Log.log(this, "handlePoisonMessage(PoisonMessage,int,MQGMO)", "JMSWMQ1117", inserts);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  535 */     catch (JMSException je) {
/*  536 */       if (Trace.isOn) {
/*  537 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(PoisonMessage,int,MQGMO)", (Throwable)je, 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  543 */       this.owner.syncpoint(false, false, this.destination);
/*      */       
/*  545 */       if (Trace.isOn) {
/*  546 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(PoisonMessage,int,MQGMO)", (Throwable)je, 1);
/*      */       }
/*      */       
/*  549 */       throw je;
/*      */     }
/*  551 */     catch (RuntimeException rte) {
/*  552 */       if (Trace.isOn) {
/*  553 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(PoisonMessage,int,MQGMO)", rte, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  559 */       this.owner.syncpoint(false, false, this.destination);
/*      */       
/*  561 */       if (Trace.isOn) {
/*  562 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(PoisonMessage,int,MQGMO)", rte, 2);
/*      */       }
/*      */       
/*  565 */       throw rte;
/*      */     } finally {
/*      */       
/*  568 */       if (Trace.isOn) {
/*  569 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(PoisonMessage,int,MQGMO)");
/*      */       }
/*      */       
/*  572 */       if (this.isBrowser) {
/*      */ 
/*      */         
/*  575 */         this.consumersHobj = previousConsumersHobj;
/*  576 */         if (Trace.isOn) {
/*  577 */           Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(PoisonMessage,int,MQGMO)", "Reset consumersHobj to new  previous value " + this.consumersHobj);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  583 */         if (closeRequired) {
/*      */           
/*  585 */           Pint closeCc = this.env.newPint();
/*  586 */           Pint closeRc = this.env.newPint();
/*  587 */           this.mq.MQCLOSE(this.hconn, pHobj, 0, closeCc, closeRc);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  594 */       if (this.owner instanceof WMQConnection && 
/*  595 */         this.session != null) {
/*  596 */         this.session.close(false);
/*  597 */         this.session = null;
/*      */       } 
/*      */     } 
/*      */     
/*  601 */     if (Trace.isOn) {
/*  602 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "handlePoisonMessage(PoisonMessage,int,MQGMO)", null, 3);
/*      */     }
/*      */     
/*  605 */     return null;
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
/*      */   private PoisonMessage getBrowsedMessage(MQGMO gmo, Pint getRc, Phobj pHobj) throws JMSException {
/*  621 */     if (Trace.isOn) {
/*  622 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "getBrowsedMessage(MQGMO,Pint,Phobj)", new Object[] { gmo, getRc, pHobj });
/*      */     }
/*      */     
/*  625 */     if (Trace.isOn) {
/*  626 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "getBrowsedMessage(MQGMO,Pint,Phobj)");
/*      */     }
/*  628 */     PoisonMessage pmsg = null;
/*  629 */     Pint cc = this.env.newPint();
/*  630 */     Pint rc = this.env.newPint();
/*  631 */     if (Trace.isOn) {
/*  632 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "getBrowsedMessage(MQGMO,Pint,Phobj)", "Message got using browser, now attempting to re-get message from queue");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  638 */       MQOD od = this.env.newMQOD();
/*      */       
/*  640 */       od.setObjectType(1);
/*  641 */       od.setObjectName(this.destination.getName());
/*      */       
/*  643 */       int openOpts = 8322;
/*      */       
/*  645 */       this.mq.MQOPEN(this.hconn, od, openOpts, pHobj, cc, rc);
/*  646 */       checkJmqiCallSuccess("MQOPEN", "JMSWMQ0030", this.destination.getName(), cc, rc, "XN00S00F");
/*      */ 
/*      */       
/*  649 */       MQGMO newGMO = this.env.newMQGMO();
/*  650 */       if (newGMO.getVersion() < 3) {
/*  651 */         newGMO.setVersion(3);
/*      */       }
/*      */       
/*  654 */       int getOpts = 8194;
/*  655 */       newGMO.setOptions(getOpts);
/*  656 */       byte[] nullMsgToken = new byte[16];
/*  657 */       Arrays.fill(nullMsgToken, (byte)0);
/*  658 */       Pint getCc = this.env.newPint();
/*  659 */       MQMD newMQMD = this.env.newMQMD();
/*  660 */       PbyteBuffer msgBuffer = this.env.newPbyteBuffer();
/*  661 */       ByteBuffer newMsgBuf = null;
/*  662 */       if (gmo != null && !Arrays.equals(gmo.getMsgToken(), nullMsgToken)) {
/*      */         
/*  664 */         newGMO.setMatchOptions(32);
/*  665 */         newGMO.setMsgToken(gmo.getMsgToken());
/*      */         
/*  667 */         Pint msgTooSmallForBuffer = this.env.newPint();
/*  668 */         Pint dataLength = this.env.newPint();
/*  669 */         newMsgBuf = ((JmqiSP)this.mq).jmqiGet(this.hconn, pHobj
/*  670 */             .getHobj(), newMQMD, newGMO, -1, 2147483647, msgBuffer, msgTooSmallForBuffer, dataLength, getCc, getRc);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  682 */         this.session.operationPerformed(WMQConsumerOwner.Operation.GET, true);
/*      */       } else {
/*      */         
/*  685 */         if (Trace.isOn) {
/*  686 */           Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "getBrowsedMessage(MQGMO,Pint,Phobj)", "Unable to obtain message as msgToken is unavailable, aborting poison message handling");
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  692 */         getRc.x = 2033;
/*      */       } 
/*      */ 
/*      */       
/*  696 */       checkJmqiCallSuccess("jmqiGet", "JMSWMQ0030", this.destination.getName(), getCc, getRc, "XN00S010");
/*      */ 
/*      */       
/*  699 */       pmsg = new PoisonMessage(newMQMD, new ByteBuffer[] { newMsgBuf });
/*      */       
/*  701 */       if (Trace.isOn) {
/*  702 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "getBrowsedMessage(MQGMO,Pint,Phobj)", "Set consumersHobj to new hobj " + pHobj
/*      */ 
/*      */             
/*  705 */             .getHobj());
/*      */       }
/*      */     }
/*  708 */     catch (JMSException je) {
/*  709 */       if (Trace.isOn) {
/*  710 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "getBrowsedMessage(MQGMO,Pint,Phobj)", (Throwable)je);
/*      */       }
/*      */ 
/*      */       
/*  714 */       Pint closeCc = this.env.newPint();
/*  715 */       Pint closeRc = this.env.newPint();
/*  716 */       this.mq.MQCLOSE(this.hconn, pHobj, 0, closeCc, closeRc);
/*      */ 
/*      */       
/*  719 */       if (Trace.isOn) {
/*  720 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "getBrowsedMessage(MQGMO,Pint,Phobj)", (Throwable)je);
/*      */       }
/*  722 */       if (Trace.isOn) {
/*  723 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "getBrowsedMessage(MQGMO,Pint,Phobj)", (Throwable)je);
/*      */       }
/*      */       
/*  726 */       throw je;
/*      */     } 
/*  728 */     if (Trace.isOn) {
/*  729 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "getBrowsedMessage(MQGMO,Pint,Phobj)", pmsg);
/*      */     }
/*  731 */     if (Trace.isOn) {
/*  732 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "getBrowsedMessage(MQGMO,Pint,Phobj)", pmsg);
/*      */     }
/*      */     
/*  735 */     return pmsg;
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
/*      */   private int backoutRequeue(PoisonMessage pmsg) throws JMSException {
/*  748 */     if (Trace.isOn) {
/*  749 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "backoutRequeue(PoisonMessage)", new Object[] { pmsg });
/*      */     }
/*      */ 
/*      */     
/*  753 */     Pint cc = this.env.newPint();
/*  754 */     Pint rc = this.env.newPint();
/*      */     
/*  756 */     if (this.destAttrs.getRequeueQueueName() == null) {
/*      */ 
/*      */       
/*  759 */       if (Trace.isOn) {
/*  760 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "backoutRequeue(PoisonMessage)", 
/*  761 */             Integer.valueOf(2362), 1);
/*      */       }
/*      */       
/*  764 */       return 2362;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  771 */       pmsg.setTargetDestination(this.destAttrs.getRequeueDestination());
/*  772 */       put(pmsg, this.destAttrs.getRequeueQueueName(), null, false, cc, rc);
/*      */     }
/*  774 */     catch (JMSException je) {
/*  775 */       if (Trace.isOn) {
/*  776 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "backoutRequeue(PoisonMessage)", (Throwable)je);
/*      */       }
/*      */       
/*  779 */       Exception e = je.getLinkedException();
/*  780 */       if (e != null && e instanceof MQException) {
/*  781 */         MQException wmqex = (MQException)e;
/*  782 */         int reason = wmqex.getReason();
/*  783 */         if (reason != 0) {
/*      */           
/*  785 */           HashMap<Object, Object> inserts = new HashMap<>();
/*  786 */           String messageID = pmsg.getJMSMessageID();
/*  787 */           inserts.put("XMSC_INSERT_MESSAGE_ID", messageID);
/*  788 */           inserts.put("XMSC_INSERT_DESTINATION_NAME", this.destAttrs.getRequeueQueueName());
/*  789 */           inserts.put("XMSC_INSERT_REASON", Integer.toString(reason));
/*  790 */           Log.log(this, "backoutRequeue(PoisonMessage)", "JMSWMQ1120", inserts);
/*      */           
/*  792 */           if (Trace.isOn) {
/*  793 */             Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "backoutRequeue(PoisonMessage)", 
/*  794 */                 Integer.valueOf(reason), 2);
/*      */           }
/*  796 */           return reason;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  802 */       if (Trace.isOn) {
/*  803 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "backoutRequeue(PoisonMessage)", 
/*  804 */             Integer.valueOf(2362), 3);
/*      */       }
/*  806 */       return 2362;
/*      */     } 
/*  808 */     if (Trace.isOn) {
/*  809 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "backoutRequeue(PoisonMessage)", 
/*  810 */           Integer.valueOf(rc.x), 4);
/*      */     }
/*  812 */     return rc.x;
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
/*      */   private void deadletter(PoisonMessage pmsg, String qname, String qmname, int reason) throws JMSException {
/*  830 */     if (Trace.isOn) {
/*  831 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "deadletter(PoisonMessage,String,String,int)", new Object[] { pmsg, qname, qmname, 
/*      */             
/*  833 */             Integer.valueOf(reason) });
/*      */     }
/*      */     
/*  836 */     Pint cc = this.env.newPint();
/*  837 */     Pint rc = this.env.newPint();
/*      */ 
/*      */     
/*  840 */     if (this.qmAttrs.getDeadLetterQ() == null || this.qmAttrs.getDeadLetterQ().length() == 0) {
/*      */       
/*  842 */       Log.log(this, "driveExceptionListener(JMSException, boolean)", "JMSWMQ0032", null);
/*      */       
/*  844 */       JMSException jmsex = (JMSException)NLSServices.createException("JMSWMQ0032", null);
/*  845 */       if (Trace.isOn) {
/*  846 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "deadletter(PoisonMessage,String,String,int)", (Throwable)jmsex, 1);
/*      */       }
/*      */       
/*  849 */       throw jmsex;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  856 */       PoisonMessage deadmsg = (PoisonMessage)pmsg.clone();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  861 */       deadmsg.setTargetDestination(this.qmAttrs.getDeadLetterQDestination());
/*      */ 
/*      */ 
/*      */       
/*  865 */       if (Trace.isOn && 
/*  866 */         deadmsg.wmqMsg == null) {
/*  867 */         Trace.traceInfo(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "deadletter(PoisonMessage,String,String,int)", "message does not contain a provider message");
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
/*  878 */       if (this.jmsDeadLetterStyle.equalsIgnoreCase(V7_JMS_DEAD_LETTER_STYLE) && deadmsg.wmqMsg != null) {
/*      */ 
/*      */         
/*  881 */         if (Trace.isOn) {
/*  882 */           Trace.traceInfo(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "deadletter(PoisonMessage,String,String,int)", "com.ibm.msg.client.tuning.JmsDeadLetterStyle is set to " + this.jmsDeadLetterStyle + ". Dead lettering message as a BytesMessage");
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  888 */         deadmsg.convertToBytesMessage();
/*      */ 
/*      */       
/*      */       }
/*  892 */       else if (Trace.isOn) {
/*  893 */         Trace.traceInfo(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "deadletter(PoisonMessage,String,String,int)", "dead lettering message in it's original format");
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  899 */       MQDLH dlh = computeDLH(deadmsg, qname, qmname, reason);
/*  900 */       deadmsg.chainInDLH(dlh);
/*      */ 
/*      */       
/*  903 */       put(deadmsg, this.qmAttrs.getDeadLetterQ(), null, true, cc, rc);
/*      */     }
/*  905 */     catch (Exception e) {
/*  906 */       if (Trace.isOn) {
/*  907 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "deadletter(PoisonMessage,String,String,int)", e);
/*      */       }
/*      */       
/*  910 */       HashMap<String, Object> inserts = new HashMap<>();
/*  911 */       inserts.put("XMSC_INSERT_EXCEPTION", e);
/*  912 */       Log.log(this, "driveExceptionListener(JMSException, boolean)", "JMSWMQ0035", inserts);
/*      */       
/*  914 */       JMSException jmsex1 = (JMSException)NLSServices.createException("JMSWMQ1079", null);
/*  915 */       jmsex1.setLinkedException(e);
/*      */       
/*  917 */       JMSException jmsex = (JMSException)NLSServices.createException("JMSWMQ1080", null);
/*  918 */       jmsex.setLinkedException((Exception)jmsex1);
/*  919 */       if (Trace.isOn) {
/*  920 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "deadletter(PoisonMessage,String,String,int)", (Throwable)jmsex, 2);
/*      */       }
/*      */       
/*  923 */       throw jmsex;
/*      */     } 
/*  925 */     if (Trace.isOn) {
/*  926 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "deadletter(PoisonMessage,String,String,int)");
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
/*      */   private void put(PoisonMessage pmsg, String destname, String qmname, boolean newMsgId, Pint cc, Pint rc) throws JMSException {
/*  945 */     if (Trace.isOn) {
/*  946 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "put(PoisonMessage,String,String,boolean,Pint,Pint)", new Object[] { pmsg, destname, qmname, 
/*      */             
/*  948 */             Boolean.valueOf(newMsgId), cc, rc });
/*      */     }
/*  950 */     MQOD od = computeOD(destname, qmname);
/*  951 */     int opts = 8208;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  961 */     if (null == this.subscriptionQueue)
/*      */     {
/*      */       
/*  964 */       opts |= 0x200;
/*      */     }
/*      */     
/*  967 */     Phobj pHobj = this.env.newPhobj();
/*      */     
/*  969 */     this.mq.MQOPEN(this.hconn, od, opts, pHobj, cc, rc);
/*  970 */     checkJmqiCallSuccess("MQOPEN", "JMSWMQ0030", destname, cc, rc, "XN00S003");
/*      */     
/*  972 */     MQPMO pmo = computePMO(newMsgId);
/*  973 */     MQMD mqmd = pmsg.getMQMD();
/*  974 */     ByteBuffer[] buffs = pmsg.getByteBuffers();
/*  975 */     if (buffs != null) {
/*  976 */       for (int i = 0; i < buffs.length; i++) {
/*  977 */         buffs[i].rewind();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*  982 */     this.sp.jmqiPut(this.hconn, pHobj.getHobj(), mqmd, pmo, buffs, cc, rc);
/*      */     
/*  984 */     Pint cc2 = this.env.newPint();
/*  985 */     Pint rc2 = this.env.newPint();
/*  986 */     this.mq.MQCLOSE(this.hconn, pHobj, 0, cc2, rc2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  993 */     checkJmqiCallSuccess("MQPUT", "JMSWMQ0030", destname, cc, rc, "XN00S004");
/*  994 */     checkJmqiCallSuccess("MQCLOSE", "JMSWMQ0030", destname, cc2, rc2, "XN00S005");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1000 */     this.session.operationPerformed(WMQConsumerOwner.Operation.SYNCPUT, true);
/* 1001 */     if (Trace.isOn) {
/* 1002 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "put(PoisonMessage,String,String,boolean,Pint,Pint)");
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
/*      */   private MQDLH computeDLH(PoisonMessage pmsg, String qname, String qmname, int reason) throws JMSException {
/* 1019 */     if (Trace.isOn) {
/* 1020 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "computeDLH(PoisonMessage,String,String,int)", new Object[] { pmsg, qname, qmname, 
/*      */             
/* 1022 */             Integer.valueOf(reason) });
/*      */     }
/* 1024 */     MQMD mqmd = pmsg.getMQMD();
/* 1025 */     MQDLH dlh = this.env.newMQDLH();
/*      */ 
/*      */ 
/*      */     
/* 1029 */     dlh.setCodedCharSetId(mqmd.getCodedCharSetId());
/* 1030 */     dlh.setEncoding(mqmd.getEncoding());
/* 1031 */     dlh.setFormat(mqmd.getFormat());
/*      */     
/* 1033 */     dlh.setDestQMgrName(qmname);
/* 1034 */     dlh.setDestQName(qname);
/* 1035 */     dlh.setPutApplName("MQ JMS ConnectionConsumer");
/* 1036 */     dlh.setPutApplType(28);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1041 */     GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
/* 1042 */     int year = gc.get(1);
/* 1043 */     int month = gc.get(2) + 1;
/* 1044 */     int day = gc.get(5);
/* 1045 */     int hour = gc.get(11);
/* 1046 */     int min = gc.get(12);
/* 1047 */     int sec = gc.get(13);
/* 1048 */     int millis = gc.get(14);
/* 1049 */     dlh.setPutDateAndTime(year, month, day, hour, min, sec, millis);
/*      */ 
/*      */     
/* 1052 */     dlh.setReason(reason);
/* 1053 */     if (Trace.isOn) {
/* 1054 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "computeDLH(PoisonMessage,String,String,int)", dlh);
/*      */     }
/*      */     
/* 1057 */     return dlh;
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
/*      */   private void sendReport(PoisonMessage pmsg, int reason) throws JMSException {
/* 1069 */     if (Trace.isOn) {
/* 1070 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "sendReport(PoisonMessage,int)", new Object[] { pmsg, 
/* 1071 */             Integer.valueOf(reason) });
/*      */     }
/* 1073 */     int opts = pmsg.getReport();
/* 1074 */     int reportExcBits = opts & 0x7000000;
/* 1075 */     if (reportExcBits == 0) {
/*      */       
/* 1077 */       if (Trace.isOn) {
/* 1078 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "sendReport(PoisonMessage,int)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1084 */     MQMD mqmd = pmsg.getMQMD();
/* 1085 */     MQMD outmqmd = generateReportMQMD(mqmd, reason);
/* 1086 */     pmsg.setMQMD(outmqmd);
/*      */     
/* 1088 */     String replyToQM = trim(mqmd.getReplyToQMgr());
/* 1089 */     String replyTo = trim(mqmd.getReplyToQ());
/*      */ 
/*      */     
/* 1092 */     if (replyTo == null) {
/* 1093 */       deadletter(pmsg, null, replyToQM, 2085);
/*      */     }
/*      */     
/* 1096 */     PoisonMessage reportMsg = null;
/* 1097 */     Pint cc = this.env.newPint();
/* 1098 */     Pint rc = this.env.newPint();
/*      */     
/*      */     try {
/* 1101 */       boolean newMsgId = ((opts & 0x80) != 128);
/*      */ 
/*      */       
/* 1104 */       String uri = replyTo;
/* 1105 */       StringBuffer uriBuff = new StringBuffer("queue://");
/* 1106 */       if (replyToQM != null) {
/* 1107 */         uriBuff.append(replyToQM);
/*      */       }
/* 1109 */       uriBuff.append("/");
/* 1110 */       uriBuff.append(replyTo);
/* 1111 */       uri = uriBuff.toString();
/*      */       
/* 1113 */       assert this.owner instanceof JmsPropertyContext;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1118 */       WMQDestination replyToQ = new WMQDestination(1, uri, (JmsPropertyContext)this.owner);
/*      */       
/* 1120 */       reportMsg = generateReportMessage(pmsg, replyToQ, reportExcBits);
/* 1121 */       put(reportMsg, replyTo, replyToQM, newMsgId, cc, rc);
/*      */     }
/* 1123 */     catch (JMSException je) {
/* 1124 */       if (Trace.isOn) {
/* 1125 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "sendReport(PoisonMessage,int)", (Throwable)je);
/*      */       }
/*      */       
/* 1128 */       if (rc.x != 0) {
/*      */         
/* 1130 */         deadletter(reportMsg, replyTo, replyToQM, rc.x);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1147 */         HashMap<String, Object> inserts = new HashMap<>();
/* 1148 */         inserts.put("XMSC_DESTINATION_NAME", replyTo);
/* 1149 */         JMSException wrapper = (JMSException)NLSServices.createException("JMSWMQ0029", inserts);
/* 1150 */         wrapper.setLinkedException((Exception)je);
/* 1151 */         if (Trace.isOn) {
/* 1152 */           Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "sendReport(PoisonMessage,int)", (Throwable)wrapper);
/*      */         }
/*      */         
/* 1155 */         throw wrapper;
/*      */       } 
/*      */     } 
/* 1158 */     if (Trace.isOn) {
/* 1159 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "sendReport(PoisonMessage,int)", 2);
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
/*      */   private PoisonMessage generateReportMessage(PoisonMessage pmsg, WMQDestination replyToQ, int reportExcBits) throws JMSException {
/*      */     HashMap<String, Object> data;
/* 1176 */     if (Trace.isOn) {
/* 1177 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "generateReportMessage(PoisonMessage,WMQDestination,int)", new Object[] { pmsg, replyToQ, 
/*      */             
/* 1179 */             Integer.valueOf(reportExcBits) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1185 */     switch (reportExcBits) {
/*      */       
/*      */       case 16777216:
/* 1188 */         pmsg.truncateBody(0);
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 50331648:
/* 1195 */         pmsg.truncateBody(100);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 117440512:
/*      */         break;
/*      */ 
/*      */       
/*      */       default:
/* 1204 */         data = ffstInfo();
/* 1205 */         data.put("poison message", pmsg);
/* 1206 */         data.put("replyto", replyToQ);
/* 1207 */         data.put("reportExcBits", Integer.valueOf(reportExcBits));
/* 1208 */         Trace.ffst(this, "generateReportMessage", "XN00S007", data, JMSException.class); break;
/*      */     } 
/* 1210 */     if (Trace.isOn) {
/* 1211 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "generateReportMessage(PoisonMessage,WMQDestination,int)", pmsg);
/*      */     }
/*      */     
/* 1214 */     return pmsg;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQMD generateReportMQMD(MQMD mqmd, int reason) throws JMSException {
/* 1225 */     if (Trace.isOn) {
/* 1226 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "generateReportMQMD(MQMD,int)", new Object[] { mqmd, 
/* 1227 */             Integer.valueOf(reason) });
/*      */     }
/* 1229 */     MQMD out = this.env.newMQMD();
/*      */ 
/*      */ 
/*      */     
/* 1233 */     out.setVersion(2);
/* 1234 */     out.setReport(0);
/* 1235 */     out.setMsgType(4);
/* 1236 */     out.setExpiry(-1);
/* 1237 */     out.setFeedback(reason);
/* 1238 */     out.setEncoding(mqmd.getEncoding());
/* 1239 */     out.setCodedCharSetId(mqmd.getCodedCharSetId());
/* 1240 */     out.setFormat(mqmd.getFormat());
/* 1241 */     out.setPriority(mqmd.getPriority());
/* 1242 */     out.setPersistence(mqmd.getPersistence());
/*      */     
/* 1244 */     int opts = mqmd.getReport();
/* 1245 */     if ((opts & 0x80) == 128) {
/* 1246 */       out.setMsgId(mqmd.getMsgId());
/*      */     }
/*      */     else {
/*      */       
/* 1250 */       out.setMsgId(CMQC.MQMI_NONE);
/*      */     } 
/*      */     
/* 1253 */     if ((opts & 0x40) == 64) {
/* 1254 */       out.setCorrelId(mqmd.getCorrelId());
/*      */     }
/*      */     else {
/*      */       
/* 1258 */       out.setCorrelId(mqmd.getMsgId());
/*      */     } 
/*      */     
/* 1261 */     out.setBackoutCount(0);
/* 1262 */     out.setReplyToQ("");
/* 1263 */     out.setReplyToQMgr(((WMQConnection)this.owner.getConnection()).getQueueManagerName());
/* 1264 */     out.setPutApplType(28);
/* 1265 */     out.setPutApplName("MQ JMS Connection Consumer");
/*      */ 
/*      */     
/* 1268 */     if (Trace.isOn) {
/* 1269 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "generateReportMQMD(MQMD,int)", out);
/*      */     }
/*      */     
/* 1272 */     return out;
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
/*      */   private MQOD computeOD(String destname, String qmname) throws JMSException {
/* 1284 */     if (Trace.isOn) {
/* 1285 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "computeOD(String,String)", new Object[] { destname, qmname });
/*      */     }
/*      */     
/* 1288 */     MQOD od = this.env.newMQOD();
/*      */     
/* 1290 */     if (qmname != null) {
/* 1291 */       od.setObjectQMgrName(qmname);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1298 */     od.setObjectType(1);
/* 1299 */     od.setObjectName(destname);
/*      */     
/* 1301 */     if (Trace.isOn) {
/* 1302 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "computeOD(String,String)", od);
/*      */     }
/* 1304 */     return od;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MQPMO computePMO(boolean newMsgId) {
/* 1314 */     if (Trace.isOn)
/* 1315 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "computePMO(boolean)", new Object[] {
/* 1316 */             Boolean.valueOf(newMsgId)
/*      */           }); 
/* 1318 */     MQPMO pmo = this.env.newMQPMO();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1328 */     int options = 2;
/* 1329 */     if (null == this.subscriptionQueue) {
/* 1330 */       options |= 0x200;
/*      */     }
/*      */     
/* 1333 */     if (newMsgId) {
/* 1334 */       options |= 0x40;
/*      */     }
/* 1336 */     pmo.setOptions(options);
/* 1337 */     pmo.setContext(this.consumersHobj.getIntegerHandle());
/* 1338 */     if (Trace.isOn) {
/* 1339 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "computePMO(boolean)", pmo);
/*      */     }
/* 1341 */     return pmo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String trim(String text) {
/* 1351 */     if (Trace.isOn) {
/* 1352 */       Trace.entry("com.ibm.msg.client.wmq.internal.WMQPoison", "trim(String)", new Object[] { text });
/*      */     }
/* 1354 */     if (text == null || text.length() == 0) {
/* 1355 */       if (Trace.isOn) {
/* 1356 */         Trace.exit("com.ibm.msg.client.wmq.internal.WMQPoison", "trim(String)", null, 1);
/*      */       }
/* 1358 */       return null;
/*      */     } 
/*      */     
/* 1361 */     String tidied = text.trim();
/* 1362 */     if (tidied.length() == 0) {
/* 1363 */       if (Trace.isOn) {
/* 1364 */         Trace.exit("com.ibm.msg.client.wmq.internal.WMQPoison", "trim(String)", null, 2);
/*      */       }
/* 1366 */       return null;
/*      */     } 
/*      */ 
/*      */     
/* 1370 */     for (int i = 0; i < tidied.length(); i++) {
/* 1371 */       if (tidied.charAt(i) != '*') {
/* 1372 */         if (Trace.isOn) {
/* 1373 */           Trace.exit("com.ibm.msg.client.wmq.internal.WMQPoison", "trim(String)", tidied, 3);
/*      */         }
/*      */         
/* 1376 */         return tidied;
/*      */       } 
/*      */     } 
/* 1379 */     if (Trace.isOn) {
/* 1380 */       Trace.exit("com.ibm.msg.client.wmq.internal.WMQPoison", "trim(String)", null, 4);
/*      */     }
/* 1382 */     return null;
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
/*      */   private void checkJmqiCallSuccess(String method, String messageid, String targetDestination, Pint completionCode, Pint reason, String probeid) throws JMSException {
/* 1401 */     if (Trace.isOn) {
/* 1402 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "checkJmqiCallSuccess(String,String,String,Pint,Pint,String)", new Object[] { method, messageid, targetDestination, completionCode, reason, probeid });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1410 */     if (reason.x != 0 || completionCode.x != 0) {
/* 1411 */       if (Reason.isImpossibleReason(reason.x, completionCode.x, this.sp)) {
/* 1412 */         HashMap<String, Object> info = ffstInfo();
/* 1413 */         info.put("method", method);
/* 1414 */         info.put("reason", reason);
/* 1415 */         info.put("compcode", completionCode);
/* 1416 */         info.put("target destination", targetDestination);
/* 1417 */         info.put("hconn", this.hconn);
/* 1418 */         Trace.ffst("WMQPoison", "checkJmqiCallSuccess", probeid, info, JMSException.class);
/*      */       } 
/* 1420 */       if (Reason.isConnectionBroken(reason.x)) {
/* 1421 */         JMSException e = Reason.createException("JMSWMQ1107", null, reason.x, completionCode.x, this.env);
/* 1422 */         ((WMQConnection)this.owner.getConnection()).driveExceptionListener(e, true);
/*      */       } 
/*      */       
/* 1425 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1426 */       inserts.put("XMSC_INSERT_METHOD", method);
/* 1427 */       if (targetDestination != null) {
/* 1428 */         inserts.put("XMSC_DESTINATION_NAME", targetDestination);
/*      */       }
/* 1430 */       JMSException je = Reason.createException(messageid, inserts, reason.x, completionCode.x, this.env);
/* 1431 */       if (Trace.isOn) {
/* 1432 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "checkJmqiCallSuccess(String,String,String,Pint,Pint,String)", (Throwable)je);
/*      */       }
/*      */       
/* 1435 */       throw je;
/*      */     } 
/* 1437 */     if (Trace.isOn) {
/* 1438 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "checkJmqiCallSuccess(String,String,String,Pint,Pint,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private HashMap<String, Object> ffstInfo() {
/* 1445 */     if (Trace.isOn) {
/* 1446 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "ffstInfo()");
/*      */     }
/* 1448 */     HashMap<String, Object> info = new HashMap<>();
/* 1449 */     info.put("this", this);
/* 1450 */     info.put("env", this.env);
/* 1451 */     info.put("mq", this.mq);
/* 1452 */     info.put("hconn", this.hconn);
/* 1453 */     info.put("consumersHobj", this.consumersHobj);
/* 1454 */     info.put("owner", this.owner);
/* 1455 */     info.put("destination", this.destination);
/* 1456 */     if (this.destination != null) {
/* 1457 */       info.put("destination name", this.destination.getName());
/*      */     }
/* 1459 */     info.put("destAttrs", this.destAttrs);
/* 1460 */     info.put("qmAttrs", this.qmAttrs);
/* 1461 */     info.put("subscriptionQueue", this.subscriptionQueue);
/* 1462 */     info.put("session", this.session);
/* 1463 */     if (Trace.isOn) {
/* 1464 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQPoison", "ffstInfo()", info);
/*      */     }
/* 1466 */     return info;
/*      */   }
/*      */ 
/*      */   
/*      */   private class DestinationAttrs
/*      */   {
/*      */     private int BOThresh;
/*      */     
/*      */     private String BORQName;
/*      */     
/*      */     private WMQDestination BORQDestination;
/*      */     
/*      */     private int reconnectCycleWhenCached;
/*      */     
/*      */     private String baseQName;
/*      */     
/*      */     private DestinationAttrs() throws JMSException {
/* 1483 */       this(WMQPoison.this.destination.getName(), WMQPoison.this.destination.isQueue(), WMQPoison.this.destination.isManagedQueue(), 1);
/* 1484 */       if (Trace.isOn) {
/* 1485 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "<init>()");
/*      */       }
/*      */       
/* 1488 */       if (Trace.isOn) {
/* 1489 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "<init>()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     private DestinationAttrs(String destname, boolean isQueue, boolean isManagedQueue, int depth) throws JMSException {
/* 1495 */       if (Trace.isOn) {
/* 1496 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "<init>(String,boolean,boolean,int)", new Object[] { destname, 
/* 1497 */               Boolean.valueOf(isQueue), 
/* 1498 */               Boolean.valueOf(isManagedQueue), Integer.valueOf(depth) });
/*      */       }
/*      */       
/* 1501 */       initialiseCachedValues(destname, isQueue, isManagedQueue, depth);
/*      */       
/* 1503 */       if (Trace.isOn) {
/* 1504 */         Trace.data(this, "DestinationAttrs", "<init>(String,boolean,boolean,int)", "attrs", this);
/*      */       }
/* 1506 */       if (Trace.isOn) {
/* 1507 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "<init>(String,boolean,boolean,int)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private void initialiseCachedValues(String destname, boolean isQueue, boolean isManagedQueue, int depth) throws JMSException {
/* 1514 */       if (Trace.isOn) {
/* 1515 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "initialiseCachedValues(String,boolean,boolean,int)", new Object[] { destname, 
/* 1516 */               Boolean.valueOf(isQueue), 
/* 1517 */               Boolean.valueOf(isManagedQueue), Integer.valueOf(depth) });
/*      */       }
/*      */       
/* 1520 */       Hobj hobj = null;
/* 1521 */       Phobj pHobjQueue = null;
/* 1522 */       Pint cc = WMQPoison.this.env.newPint();
/* 1523 */       Pint rc = WMQPoison.this.env.newPint();
/*      */       
/* 1525 */       int[] pSelectors = { 22, 20, 2019, 2002 };
/* 1526 */       int[] pIntAttrs = new int[2];
/* 1527 */       byte[] pCharAttrs = new byte[96];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       do {
/* 1534 */         if (isQueue && !isManagedQueue) {
/* 1535 */           pHobjQueue = WMQPoison.this.env.newPhobj();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1547 */           String qMgrName = "";
/*      */           
/*      */           try {
/* 1550 */             Hconn hconn = WMQPoison.this.owner.getHconn();
/* 1551 */             qMgrName = hconn.getName().trim();
/*      */           }
/* 1553 */           catch (JmqiException e) {
/* 1554 */             if (Trace.isOn) {
/* 1555 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "initialiseCachedValues(String,boolean,boolean,int)", (Throwable)e);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 1560 */             HashMap<String, String> inserts = new HashMap<>();
/* 1561 */             inserts.put("XMSC_INSERT_COMP_CODE", Integer.toString(e.getCompCode()));
/* 1562 */             inserts.put("XMSC_INSERT_REASON", Integer.toString(e.getReason()));
/* 1563 */             inserts.put("XMSC_INSERT_METHOD", "initialiseCachedValues(String,boolean,boolean,int)");
/*      */ 
/*      */             
/* 1566 */             String exceptionMessage = "JMSWMQ2019";
/* 1567 */             JMSException je = (JMSException)NLSServices.createException(exceptionMessage, inserts);
/* 1568 */             je.setLinkedException((Exception)e);
/*      */             
/* 1570 */             if (Trace.isOn) {
/* 1571 */               Trace.throwing(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "initialiseCachedValues(String,boolean,boolean,int)", (Throwable)je);
/*      */             }
/*      */             
/* 1574 */             throw je;
/*      */           } 
/* 1576 */           MQOD od = WMQPoison.this.computeOD(destname, qMgrName);
/*      */ 
/*      */           
/* 1579 */           int opts = 8224;
/*      */           
/* 1581 */           WMQPoison.this.mq.MQOPEN(WMQPoison.this.hconn, od, opts, pHobjQueue, cc, rc);
/* 1582 */           WMQPoison.this.checkJmqiCallSuccess("MQOPEN", "JMSWMQ0030", destname, cc, rc, "XN00S008");
/*      */           
/* 1584 */           hobj = pHobjQueue.getHobj();
/* 1585 */           if (hobj instanceof RemoteHobj) {
/* 1586 */             ((RemoteHobj)hobj).setReopenable(false);
/*      */           }
/*      */         } else {
/*      */           
/* 1590 */           hobj = WMQPoison.this.consumersHobj;
/*      */         } 
/*      */         
/* 1593 */         WMQPoison.this.mq.MQINQ(WMQPoison.this.hconn, hobj, pSelectors.length, pSelectors, pIntAttrs.length, pIntAttrs, pCharAttrs.length, pCharAttrs, cc, rc);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1601 */         if (rc.x == 0 || rc.x == 2068 || rc.x == 2019)
/* 1602 */           continue;  WMQPoison.this.checkJmqiCallSuccess("MQINQ", "JMSWMQ0030", destname, cc, rc, "XN00S009");
/*      */ 
/*      */       
/*      */       }
/* 1606 */       while (rc.x == 2019);
/*      */       
/* 1608 */       int type = pIntAttrs[1];
/* 1609 */       this.BOThresh = pIntAttrs[0];
/*      */       
/* 1611 */       int ccsid = 0;
/*      */ 
/*      */       
/*      */       try {
/* 1615 */         ccsid = WMQPoison.this.hconn.getCcsid();
/* 1616 */         this.BORQName = JmqiUtils.qmgrBytesToString(WMQPoison.this.env, WMQPoison.this.hconn, pCharAttrs, 0, 48);
/* 1617 */         this.BORQName = WMQPoison.trim(this.BORQName);
/*      */         
/* 1619 */         this.BORQDestination = new WMQDestination(1, this.BORQName, null);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1624 */         this.baseQName = JmqiUtils.qmgrBytesToString(WMQPoison.this.env, WMQPoison.this.hconn, pCharAttrs, 48, 48);
/* 1625 */         this.baseQName = WMQPoison.trim(this.baseQName);
/*      */       
/*      */       }
/* 1628 */       catch (Exception e) {
/* 1629 */         if (Trace.isOn) {
/* 1630 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "initialiseCachedValues(String,boolean,boolean,int)", e, 1);
/*      */         }
/*      */         
/* 1633 */         HashMap<String, Object> inserts = new HashMap<>();
/* 1634 */         inserts.put("XMSC_DESTINATION_NAME", destname);
/* 1635 */         inserts.put("CCSID", Integer.valueOf(ccsid));
/* 1636 */         JMSException je = (JMSException)NLSServices.createException("JMSWMQ0033", inserts);
/* 1637 */         je.setLinkedException(e);
/* 1638 */         if (Trace.isOn) {
/* 1639 */           Trace.throwing(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "initialiseCachedValues(String,boolean,boolean,int)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 1642 */         throw je;
/*      */       } 
/*      */       
/* 1645 */       if (pHobjQueue != null) {
/* 1646 */         WMQPoison.this.mq.MQCLOSE(WMQPoison.this.hconn, pHobjQueue, 0, cc, rc);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1651 */         if (rc.x != 0 && rc.x != 2019) {
/* 1652 */           WMQPoison.this.checkJmqiCallSuccess("MQCLOSE", "JMSWMQ0030", destname, cc, rc, "XN00S00A");
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
/* 1663 */       if (type == 3 && this.baseQName != null && 
/* 1664 */         depth == 1) {
/*      */         try {
/* 1666 */           PropertyStore.register("com.ibm.mq.jms.useDefaultBOValues", false);
/* 1667 */           if (PropertyStore.getBooleanPropertyObject("com.ibm.mq.jms.useDefaultBOValues").booleanValue()) {
/* 1668 */             this.BOThresh = 0;
/* 1669 */             if (Trace.isOn) {
/* 1670 */               Trace.traceData(this, "useDefaultBOValues = TRUE, using the default values of BOThresh and BORQ\nBOThresh = " + this.BOThresh + " BORQName = " + this.BORQName, null);
/*      */             }
/*      */           } else {
/*      */             
/* 1674 */             if (Trace.isOn) {
/* 1675 */               Trace.data(this, "DestinationAttrs", "useDefaultBOValues = false. So querying BO values from the target queue", "attrs", this);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1682 */             DestinationAttrs baseDestAttrs = new DestinationAttrs(this.baseQName, isQueue, isManagedQueue, depth + 1);
/* 1683 */             this.BORQName = baseDestAttrs.BORQName;
/* 1684 */             this.BOThresh = baseDestAttrs.BOThresh;
/* 1685 */             this.BORQDestination = new WMQDestination(1, this.BORQName, null);
/*      */           }
/*      */         
/* 1688 */         } catch (JMSException e) {
/* 1689 */           if (Trace.isOn) {
/* 1690 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "initialiseCachedValues(String,boolean,boolean,int)", (Throwable)e, 2);
/*      */           }
/*      */           
/* 1693 */           MQException mqe = (MQException)e.getLinkedException();
/* 1694 */           if (mqe != null && mqe.reasonCode == 2035) {
/* 1695 */             this.BOThresh = 0;
/* 1696 */             if (Trace.isOn)
/*      */             {
/* 1698 */               Trace.traceWarning(this, "com.ibm.msg.client.wmq.internal.WMQPoison.DestinationAttrs", "MQRC_NOT_AUTHORIZED error returned when inquiring backout details from queue referenced by alias queue", e);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1704 */             HashMap<String, Object> inserts = new HashMap<>();
/* 1705 */             inserts.put("XMSC_INSERT_DESTINATION_NAME", this.baseQName);
/* 1706 */             inserts.put("XMSC_INSERT_ALIAS_DESTINATION_NAME", destname);
/* 1707 */             Log.log(this, "DestinationAttrs(String,Boolean,int)", "JMSWMQ2026", inserts);
/*      */ 
/*      */           
/*      */           }
/*      */           else {
/*      */ 
/*      */             
/* 1714 */             if (Trace.isOn) {
/* 1715 */               Trace.throwing(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "initialiseCachedValues(String,boolean,boolean,int)", (Throwable)e, 2);
/*      */             }
/*      */             
/* 1718 */             throw e;
/*      */           } 
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 1724 */       this.reconnectCycleWhenCached = ((JmqiSP)WMQPoison.this.mq).getReconnectCycle();
/*      */       
/* 1726 */       if (Trace.isOn) {
/* 1727 */         Trace.data(this, "DestinationAttrs", "initialiseCachedValues", "attrs", this);
/*      */       }
/*      */       
/* 1730 */       if (Trace.isOn) {
/* 1731 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "initialiseCachedValues(String,boolean,boolean,int)");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     private WMQDestination getRequeueDestination() throws JMSException {
/* 1737 */       if (Trace.isOn) {
/* 1738 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "getRequeueDestination()");
/*      */       }
/* 1740 */       refreshCache();
/*      */       
/* 1742 */       if (Trace.isOn) {
/* 1743 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "getRequeueDestination()", this.BORQDestination);
/*      */       }
/*      */       
/* 1746 */       return this.BORQDestination;
/*      */     }
/*      */     
/*      */     private String getRequeueQueueName() throws JMSException {
/* 1750 */       if (Trace.isOn) {
/* 1751 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "getRequeueQueueName()");
/*      */       }
/* 1753 */       refreshCache();
/*      */       
/* 1755 */       if (Trace.isOn) {
/* 1756 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "getRequeueQueueName()", this.BORQName);
/*      */       }
/*      */       
/* 1759 */       return this.BORQName;
/*      */     }
/*      */     
/*      */     private int getBOThresh() {
/* 1763 */       if (Trace.isOn) {
/* 1764 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "getBOThresh()");
/*      */       }
/*      */       try {
/* 1767 */         refreshCache();
/*      */       }
/* 1769 */       catch (JMSException e) {
/* 1770 */         if (Trace.isOn) {
/* 1771 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "getBOThresh()", (Throwable)e);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1776 */       if (Trace.isOn) {
/* 1777 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "getBOThresh()", this.BOThresh);
/*      */       }
/*      */       
/* 1780 */       return this.BOThresh;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void refreshCache() throws JMSException {
/* 1789 */       if (Trace.isOn) {
/* 1790 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "refreshCache()");
/*      */       }
/*      */       
/* 1793 */       if (((JmqiSP)WMQPoison.this.mq).getReconnectCycle() != this.reconnectCycleWhenCached) {
/* 1794 */         if (Trace.isOn) {
/* 1795 */           Trace.data(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "refreshCache()", "actually refreshing cache");
/*      */         }
/* 1797 */         initialiseCachedValues(WMQPoison.this.destination.getName(), WMQPoison.this.destination.isQueue(), WMQPoison.this.destination.isManagedQueue(), 1);
/*      */       } 
/*      */       
/* 1800 */       if (Trace.isOn) {
/* 1801 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.DestinationAttrs", "refreshCache()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1811 */       StringBuilder buff = new StringBuilder(super.toString());
/*      */       
/* 1813 */       buff.append(" BOThresh:" + this.BOThresh);
/* 1814 */       buff.append(" BORQName:" + ((this.BORQName == null) ? "<null>" : this.BORQName));
/* 1815 */       buff.append(" baseQName:" + ((this.baseQName == null) ? "<null>" : this.baseQName));
/* 1816 */       buff.append(" reconnectCycleWhenCached:" + String.valueOf(this.reconnectCycleWhenCached));
/* 1817 */       return buff.toString();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private class QmAttrs
/*      */   {
/*      */     private String deadLetterQ;
/*      */     
/*      */     private WMQDestination deadLetterQDestination;
/*      */     
/*      */     private int reconnectCycleWhenCached;
/*      */ 
/*      */     
/*      */     private QmAttrs() throws JMSException {
/* 1833 */       if (Trace.isOn) {
/* 1834 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.QmAttrs", "<init>()");
/*      */       }
/*      */       
/* 1837 */       initialiseCachedValues();
/*      */       
/* 1839 */       if (Trace.isOn) {
/* 1840 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.QmAttrs", "<init>()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     private void initialiseCachedValues() throws JMSException {
/* 1846 */       if (Trace.isOn) {
/* 1847 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.QmAttrs", "initialiseCachedValues()");
/*      */       }
/*      */       
/* 1850 */       Phobj pHobjQM = WMQPoison.this.env.newPhobj();
/* 1851 */       MQOD od = WMQPoison.this.env.newMQOD();
/* 1852 */       od.setObjectType(5);
/*      */       
/* 1854 */       Pint cc = WMQPoison.this.env.newPint();
/* 1855 */       Pint rc = WMQPoison.this.env.newPint();
/*      */ 
/*      */       
/* 1858 */       int opts = 8224;
/*      */       
/* 1860 */       WMQPoison.this.mq.MQOPEN(WMQPoison.this.hconn, od, opts, pHobjQM, cc, rc);
/* 1861 */       WMQPoison.this.checkJmqiCallSuccess("MQOPEN", "JMSWMQ0031", null, cc, rc, "XN00S00B");
/*      */       
/* 1863 */       int[] pSelectors = { 2006 };
/* 1864 */       byte[] pCharAttrs = new byte[48];
/*      */       
/* 1866 */       WMQPoison.this.mq.MQINQ(WMQPoison.this.hconn, pHobjQM.getHobj(), pSelectors.length, pSelectors, 0, null, pCharAttrs.length, pCharAttrs, cc, rc);
/* 1867 */       WMQPoison.this.checkJmqiCallSuccess("MQINQ", "JMSWMQ0031", null, cc, rc, "XN00S00C");
/*      */       
/* 1869 */       int ccsid = 0;
/*      */ 
/*      */       
/*      */       try {
/* 1873 */         ccsid = WMQPoison.this.hconn.getCcsid();
/* 1874 */         this.deadLetterQ = JmqiUtils.qmgrBytesToString(WMQPoison.this.env, WMQPoison.this.hconn, pCharAttrs, 0, 48);
/* 1875 */         this.deadLetterQ = WMQPoison.trim(this.deadLetterQ);
/*      */ 
/*      */         
/* 1878 */         this.deadLetterQDestination = new WMQDestination(1, this.deadLetterQ, null);
/*      */       
/*      */       }
/* 1881 */       catch (Exception e) {
/* 1882 */         if (Trace.isOn) {
/* 1883 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.QmAttrs", "initialiseCachedValues()", e);
/*      */         }
/* 1885 */         HashMap<String, Object> inserts = new HashMap<>();
/* 1886 */         inserts.put("CCSID", Integer.valueOf(ccsid));
/* 1887 */         JMSException je = (JMSException)NLSServices.createException("JMSWMQ0034", inserts);
/* 1888 */         je.setLinkedException(e);
/* 1889 */         if (Trace.isOn) {
/* 1890 */           Trace.throwing(this, "com.ibm.msg.client.wmq.internal.QmAttrs", "initialiseCachedValues()", (Throwable)je);
/*      */         }
/* 1892 */         throw je;
/*      */       } 
/*      */       
/* 1895 */       WMQPoison.this.mq.MQCLOSE(WMQPoison.this.hconn, pHobjQM, 0, cc, rc);
/* 1896 */       WMQPoison.this.checkJmqiCallSuccess("MQCLOSE", "JMSWMQ0031", null, cc, rc, "XN00S00D");
/*      */       
/* 1898 */       this.reconnectCycleWhenCached = ((JmqiSP)WMQPoison.this.mq).getReconnectCycle();
/*      */       
/* 1900 */       if (Trace.isOn) {
/* 1901 */         Trace.data(this, "QmAttrs", "initialiseCachedValues()", "attrs", this);
/*      */       }
/*      */       
/* 1904 */       if (Trace.isOn) {
/* 1905 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.QmAttrs", "initialiseCachedValues()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void refreshCache() throws JMSException {
/* 1915 */       if (Trace.isOn) {
/* 1916 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.QmAttrs", "refreshCache()");
/*      */       }
/*      */       
/* 1919 */       if (((JmqiSP)WMQPoison.this.mq).getReconnectCycle() != this.reconnectCycleWhenCached) {
/* 1920 */         if (Trace.isOn) {
/* 1921 */           Trace.data(this, "com.ibm.msg.client.wmq.internal.QmAttrs", "refreshCache()", "actually refreshing cache");
/*      */         }
/* 1923 */         initialiseCachedValues();
/*      */       } 
/*      */       
/* 1926 */       if (Trace.isOn) {
/* 1927 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.QmAttrs", "refreshCache()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1937 */       StringBuilder buff = new StringBuilder(super.toString());
/*      */       
/* 1939 */       buff.append(" deadLetterQ:" + ((this.deadLetterQ == null) ? "<null>" : this.deadLetterQ));
/* 1940 */       buff.append(" deadLetterQDestination:" + ((this.deadLetterQDestination == null) ? "<null>" : (String)this.deadLetterQDestination));
/* 1941 */       return buff.toString();
/*      */     }
/*      */     
/*      */     private String getDeadLetterQ() throws JMSException {
/* 1945 */       if (Trace.isOn) {
/* 1946 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.QmAttrs", "getRequeueDestination()");
/*      */       }
/* 1948 */       refreshCache();
/*      */       
/* 1950 */       if (Trace.isOn) {
/* 1951 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.QmAttrs", "getRequeueDestination()", this.deadLetterQ);
/*      */       }
/* 1953 */       return this.deadLetterQ;
/*      */     }
/*      */     
/*      */     private WMQDestination getDeadLetterQDestination() throws JMSException {
/* 1957 */       if (Trace.isOn) {
/* 1958 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.QmAttrs", "getDeadLetterQDestination()");
/*      */       }
/* 1960 */       refreshCache();
/*      */       
/* 1962 */       if (Trace.isOn) {
/* 1963 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.QmAttrs", "getDeadLetterQDestination()", this.deadLetterQDestination);
/*      */       }
/* 1965 */       return this.deadLetterQDestination;
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
/*      */   private class PoisonMessage
/*      */     implements Cloneable
/*      */   {
/* 1980 */     private WMQMessage wmqMsg = null;
/* 1981 */     private MQMD mqmd = null;
/* 1982 */     private ByteBuffer[] buffers = null;
/* 1983 */     private WMQDestination targetDestination = null;
/* 1984 */     private String jmsMessageID = "JMSMessageID_Not_Initialized";
/*      */     
/*      */     private PoisonMessage(WMQMessage msg) {
/* 1987 */       if (Trace.isOn) {
/* 1988 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "<init>(WMQMessage)", new Object[] { msg });
/*      */       }
/*      */       
/* 1991 */       this.wmqMsg = msg;
/* 1992 */       if (Trace.isOn) {
/* 1993 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "<init>(WMQMessage)");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     private PoisonMessage(MQMD mqmd, ByteBuffer[] msgBuffers) {
/* 1999 */       if (Trace.isOn) {
/* 2000 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "<init>(MQMD,ByteBuffer [ ])", new Object[] { mqmd, msgBuffers });
/*      */       }
/*      */       
/* 2003 */       WMQSession tmpSession = null;
/* 2004 */       boolean weCreatedTheTmpSession = false;
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2009 */         ByteBuffer merged = mergeByteBuffers(msgBuffers);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2014 */         if (WMQPoison.this.owner instanceof WMQSession) {
/* 2015 */           tmpSession = (WMQSession)WMQPoison.this.owner;
/*      */         }
/* 2017 */         else if (WMQPoison.this.owner instanceof WMQConnection) {
/*      */ 
/*      */ 
/*      */           
/* 2021 */           WMQConnection connection = (WMQConnection)WMQPoison.this.owner;
/* 2022 */           connection.setIntProperty("XMSC_ACKNOWLEDGE_MODE", 1);
/* 2023 */           connection.setBooleanProperty("XMSC_TRANSACTED", false);
/* 2024 */           tmpSession = (WMQSession)connection.createSession((JmsPropertyContext)connection);
/* 2025 */           weCreatedTheTmpSession = true;
/*      */         } else {
/*      */           
/* 2028 */           Trace.ffst(this, "PoisonMessage<init>", "XN00S001", WMQPoison.this.ffstInfo(), JMSException.class);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2036 */         WMQReceiveMarshal receiveMarshal = WMQMarshal.newReceiveMarshal(tmpSession, merged, 0);
/* 2037 */         receiveMarshal.importMQMDMesageBuffer(tmpSession, WMQPoison.this.destination, mqmd, merged, 0, merged.limit(), null);
/* 2038 */         this.wmqMsg = receiveMarshal.exportProviderMessage(false);
/*      */       
/*      */       }
/* 2041 */       catch (Exception e) {
/* 2042 */         if (Trace.isOn) {
/* 2043 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "<init>(MQMD,ByteBuffer [ ])", e, 1);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       finally {
/*      */         
/* 2050 */         if (Trace.isOn) {
/* 2051 */           Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "<init>(MQMD,ByteBuffer [ ])");
/*      */         }
/*      */         
/* 2054 */         if (Trace.isOn) {
/* 2055 */           Trace.data(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "Constructor(MQMD,buffer)", "Ensure MQMD is copied", null);
/*      */         }
/* 2057 */         this.mqmd = mqmd;
/* 2058 */         this.buffers = msgBuffers;
/*      */         
/* 2060 */         if (weCreatedTheTmpSession) {
/* 2061 */           Trace.data(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "Constructor(MQMD,buffer)", "Closing temporary session: ", tmpSession);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           try {
/* 2067 */             tmpSession.close(false);
/*      */           }
/* 2069 */           catch (JMSException e) {
/* 2070 */             if (Trace.isOn) {
/* 2071 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "<init>(MQMD,ByteBuffer [ ])", (Throwable)e, 2);
/*      */             }
/*      */             
/* 2074 */             Trace.data(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "Constructor(MQMD,buffer)", "Caught exception closing tmpSession: ", e
/*      */ 
/*      */ 
/*      */                 
/* 2078 */                 .getStackTrace());
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2084 */       if (Trace.isOn) {
/* 2085 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "<init>(MQMD,ByteBuffer [ ])");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WMQMessage getWMQMessage() throws JMSException {
/* 2093 */       if (this.wmqMsg == null) {
/*      */ 
/*      */ 
/*      */         
/* 2097 */         ByteBuffer merged = mergeByteBuffers(this.buffers);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2104 */         WMQReceiveMarshal receiveMarshal = WMQMarshal.newReceiveMarshal(WMQPoison.this.session, merged, 0);
/* 2105 */         receiveMarshal.importMQMDMesageBuffer(WMQPoison.this.session, WMQPoison.this.destination, this.mqmd, merged, 0, merged.limit(), null);
/* 2106 */         this.wmqMsg = receiveMarshal.exportProviderMessage(false);
/*      */       } 
/*      */       
/* 2109 */       this.mqmd = null;
/* 2110 */       this.buffers = null;
/* 2111 */       if (Trace.isOn) {
/* 2112 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "getWMQMessage()", "getter", this.wmqMsg);
/*      */       }
/*      */       
/* 2115 */       return this.wmqMsg;
/*      */     }
/*      */     
/*      */     private ByteBuffer mergeByteBuffers(ByteBuffer[] buffs) {
/* 2119 */       if (Trace.isOn) {
/* 2120 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "mergeByteBuffers(ByteBuffer [ ])", new Object[] { buffs });
/*      */       }
/*      */       
/* 2123 */       int capacity = 0;
/* 2124 */       int limit = 0;
/* 2125 */       for (int i = 0; i < buffs.length; i++) {
/*      */ 
/*      */         
/* 2128 */         buffs[i].rewind();
/* 2129 */         capacity += buffs[i].capacity();
/* 2130 */         limit += buffs[i].limit();
/*      */       } 
/*      */       
/* 2133 */       ByteBuffer merged = ByteBuffer.allocate(capacity);
/* 2134 */       for (int j = 0; j < buffs.length; j++) {
/* 2135 */         merged.put(buffs[j]);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2143 */       merged.limit(limit);
/* 2144 */       if (Trace.isOn) {
/* 2145 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "mergeByteBuffers(ByteBuffer [ ])", merged);
/*      */       }
/*      */       
/* 2148 */       return merged;
/*      */     }
/*      */     
/*      */     private void setTargetDestination(WMQDestination dest) {
/* 2152 */       if (Trace.isOn) {
/* 2153 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "setTargetDestination(WMQDestination)", "setter", dest);
/*      */       }
/*      */       
/* 2156 */       this.targetDestination = dest;
/*      */     }
/*      */     
/*      */     public String getJMSMessageID() {
/* 2160 */       return this.jmsMessageID;
/*      */     }
/*      */     
/*      */     private void calculateMqmdAndBuffers() throws JMSException {
/* 2164 */       if (Trace.isOn) {
/* 2165 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "calculateMqmdAndBuffers()");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2171 */       if (this.wmqMsg != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2176 */         if (this.wmqMsg.getJMSDestinationAsString() == null || this.wmqMsg.getJMSDestinationAsString().equals("")) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2181 */           if (this.targetDestination == null) {
/* 2182 */             this.targetDestination = new WMQDestination(1, "x", null);
/*      */           }
/* 2184 */           this.wmqMsg.setJMSDestinationAsString(this.targetDestination.toURI());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2191 */           this.mqmd = null;
/* 2192 */           this.buffers = null;
/*      */         } 
/*      */ 
/*      */         
/* 2196 */         this.jmsMessageID = this.wmqMsg.getJMSMessageID();
/*      */         
/* 2198 */         if (this.buffers == null || this.mqmd == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2206 */           WMQSendMarshal sendMarshal = WMQMarshal.newSendMarshal();
/* 2207 */           sendMarshal.importProviderMessage(WMQPoison.this.session, this.wmqMsg, WMQPoison.this.destination);
/*      */           
/* 2209 */           this.mqmd = sendMarshal.exportMQMD();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2215 */           this.mqmd.setMsgId(WMQUtils.stringToId(this.wmqMsg.getJMSMessageID()));
/* 2216 */           this.buffers = sendMarshal.exportMessageBuffers();
/*      */         } 
/*      */         
/* 2219 */         this.wmqMsg = null;
/*      */       } 
/* 2221 */       if (Trace.isOn) {
/* 2222 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "calculateMqmdAndBuffers()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private MQMD getMQMD() throws JMSException {
/* 2229 */       calculateMqmdAndBuffers();
/* 2230 */       if (Trace.isOn) {
/* 2231 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "getMQMD()", "getter", this.mqmd);
/*      */       }
/*      */       
/* 2234 */       return this.mqmd;
/*      */     }
/*      */     
/*      */     private void setMQMD(MQMD mqmd) {
/* 2238 */       if (Trace.isOn) {
/* 2239 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "setMQMD(MQMD)", "setter", mqmd);
/*      */       }
/*      */       
/* 2242 */       this.mqmd = mqmd;
/*      */     }
/*      */     
/*      */     private ByteBuffer[] getByteBuffers() throws JMSException {
/* 2246 */       calculateMqmdAndBuffers();
/* 2247 */       if (Trace.isOn) {
/* 2248 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "getByteBuffers()", "getter", this.buffers);
/*      */       }
/*      */       
/* 2251 */       return this.buffers;
/*      */     }
/*      */     private void chainInDLH(MQDLH dlh) throws JMSException, JmqiException {
/*      */       JmqiCodepage cp;
/* 2255 */       if (Trace.isOn) {
/* 2256 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "chainInDLH(MQDLH)", new Object[] { dlh });
/*      */       }
/*      */       
/* 2259 */       calculateMqmdAndBuffers();
/*      */ 
/*      */ 
/*      */       
/* 2263 */       this.mqmd.setFormat("MQDEAD  ");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2270 */       int ptrsize = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2277 */       boolean byteswap = ((this.mqmd.getEncoding() & 0xF) == 2);
/* 2278 */       int ccsid = this.mqmd.getCodedCharSetId();
/*      */       
/*      */       try {
/* 2281 */         cp = JmqiCodepage.getJmqiCodepage(WMQPoison.this.env, ccsid);
/*      */         
/* 2283 */         if (cp == null) {
/* 2284 */           UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException(Integer.toString(ccsid));
/* 2285 */           if (Trace.isOn) {
/* 2286 */             Trace.throwing(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "chainInDLH(MQDLH)", traceRet1, 1);
/*      */           }
/*      */           
/* 2289 */           throw traceRet1;
/*      */         }
/*      */       
/*      */       }
/* 2293 */       catch (UnsupportedEncodingException e) {
/* 2294 */         if (Trace.isOn) {
/* 2295 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "chainInDLH(MQDLH)", e, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2300 */         int defaultCharset = 1208;
/*      */         
/* 2302 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "chainInDLH(MQDLH)", "Unable to encode Dead Letter Header in requested CCSID: " + ccsid + " , defaulting to CCSID: " + defaultCharset);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2307 */         this.mqmd.setCodedCharSetId(defaultCharset);
/*      */ 
/*      */         
/*      */         try {
/* 2311 */           cp = JmqiCodepage.getJmqiCodepage(WMQPoison.this.env, defaultCharset);
/*      */           
/* 2313 */           if (cp == null) {
/* 2314 */             UnsupportedEncodingException traceRet2 = new UnsupportedEncodingException(Integer.toString(ccsid));
/* 2315 */             if (Trace.isOn) {
/* 2316 */               Trace.throwing(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "chainInDLH(MQDLH)", traceRet2, 2);
/*      */             }
/*      */             
/* 2319 */             throw traceRet2;
/*      */           }
/*      */         
/*      */         }
/* 2323 */         catch (UnsupportedEncodingException e1) {
/* 2324 */           if (Trace.isOn) {
/* 2325 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "chainInDLH(MQDLH)", e1, 2);
/*      */           }
/*      */           
/* 2328 */           HashMap<String, Object> inserts = new HashMap<>();
/* 2329 */           inserts.put("XMSC_INSERT_PROPERTY", "JMS_IBM_Character_Set");
/* 2330 */           inserts.put("XMSC_INSERT_VALUE", Integer.valueOf(defaultCharset));
/* 2331 */           JMSException je = (JMSException)NLSServices.createException("JMSCMQ1006", inserts);
/* 2332 */           if (Trace.isOn) {
/* 2333 */             Trace.throwing(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "chainInDLH(MQDLH)", (Throwable)je, 3);
/*      */           }
/*      */           
/* 2336 */           throw je;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2341 */       int dlhlen = MQDLH.getSize(WMQPoison.this.env, 1, 0);
/* 2342 */       byte[] dlhbytes = new byte[dlhlen];
/* 2343 */       dlh.writeToBuffer(dlhbytes, 0, 0, byteswap, cp, WMQPoison.this.mq);
/*      */       
/* 2345 */       ByteBuffer dlhbuff = ByteBuffer.allocate(dlhlen);
/* 2346 */       dlhbuff.put(dlhbytes);
/*      */       
/* 2348 */       ByteBuffer[] msgBuffers2 = new ByteBuffer[this.buffers.length + 1];
/* 2349 */       System.arraycopy(this.buffers, 0, msgBuffers2, 1, this.buffers.length);
/* 2350 */       msgBuffers2[0] = dlhbuff;
/* 2351 */       this.buffers = msgBuffers2;
/* 2352 */       if (Trace.isOn) {
/* 2353 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "chainInDLH(MQDLH)");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     private int getReport() throws JMSException {
/* 2359 */       calculateMqmdAndBuffers();
/* 2360 */       int report = this.mqmd.getReport();
/* 2361 */       if (Trace.isOn) {
/* 2362 */         Trace.data(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "getReport()", "getter", 
/* 2363 */             Integer.valueOf(report));
/*      */       }
/* 2365 */       return report;
/*      */     }
/*      */     
/*      */     private void truncateBody(int length) throws JMSException {
/* 2369 */       if (Trace.isOn)
/* 2370 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "truncateBody(int)", new Object[] {
/* 2371 */               Integer.valueOf(length)
/*      */             }); 
/* 2373 */       getWMQMessage();
/* 2374 */       if (length == 0) {
/* 2375 */         this.wmqMsg.clearBody();
/* 2376 */         this.mqmd = null;
/* 2377 */         this.buffers = null;
/*      */       } else {
/*      */         JmqiCodepage codepage;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2384 */         int encoding = this.wmqMsg.getIntProperty("JMS_IBM_Encoding");
/* 2385 */         String charset = this.wmqMsg.getStringProperty("JMS_IBM_Character_Set");
/*      */         
/*      */         try {
/* 2388 */           codepage = JmqiCodepage.getJmqiCodepage(WMQPoison.this.env, charset);
/*      */           
/* 2390 */           if (codepage == null) {
/* 2391 */             UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException(charset);
/* 2392 */             if (Trace.isOn) {
/* 2393 */               Trace.throwing(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "truncateBody(int)", traceRet1, 1);
/*      */             }
/*      */             
/* 2396 */             throw traceRet1;
/*      */           }
/*      */         
/*      */         }
/* 2400 */         catch (UnsupportedEncodingException e) {
/* 2401 */           if (Trace.isOn) {
/* 2402 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "truncateBody(int)", e);
/*      */           }
/*      */           
/* 2405 */           HashMap<String, Object> inserts = new HashMap<>();
/* 2406 */           inserts.put("XMSC_INSERT_PROPERTY", "JMS_IBM_Character_Set");
/* 2407 */           inserts.put("XMSC_INSERT_VALUE", charset);
/* 2408 */           JMSException je = (JMSException)NLSServices.createException("JMSCMQ1006", inserts);
/* 2409 */           if (Trace.isOn) {
/* 2410 */             Trace.throwing(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "truncateBody(int)", (Throwable)je, 2);
/*      */           }
/*      */           
/* 2413 */           throw je;
/*      */         } 
/*      */         
/* 2416 */         byte[] body = this.wmqMsg._exportBody(encoding, codepage);
/* 2417 */         if (body.length > length) {
/* 2418 */           byte[] body2 = new byte[length];
/* 2419 */           System.arraycopy(body, 0, body2, 0, length);
/* 2420 */           this.wmqMsg.clearBody();
/* 2421 */           this.wmqMsg._importBody(body2, 0, -1, encoding, codepage);
/*      */         } 
/*      */       } 
/* 2424 */       if (Trace.isOn) {
/* 2425 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "truncateBody(int)");
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
/*      */     public Object clone() {
/* 2439 */       if (Trace.isOn) {
/* 2440 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "clone()");
/*      */       }
/* 2442 */       PoisonMessage clone = null;
/*      */       try {
/* 2444 */         calculateMqmdAndBuffers();
/* 2445 */         MQMD newmqmd = WMQPoison.this.env.newMQMD();
/*      */ 
/*      */ 
/*      */         
/* 2449 */         newmqmd.setAccountingToken(this.mqmd.getAccountingToken());
/* 2450 */         newmqmd.setApplIdentityData(this.mqmd.getApplIdentityData());
/* 2451 */         newmqmd.setApplOriginData(this.mqmd.getApplOriginData());
/* 2452 */         newmqmd.setBackoutCount(this.mqmd.getBackoutCount());
/* 2453 */         newmqmd.setCodedCharSetId(this.mqmd.getCodedCharSetId());
/* 2454 */         newmqmd.setCorrelId(this.mqmd.getCorrelId());
/* 2455 */         newmqmd.setEncoding(this.mqmd.getEncoding());
/* 2456 */         newmqmd.setExpiry(this.mqmd.getExpiry());
/* 2457 */         newmqmd.setFeedback(this.mqmd.getFeedback());
/* 2458 */         newmqmd.setFormat(this.mqmd.getFormat());
/* 2459 */         newmqmd.setGroupId(this.mqmd.getGroupId());
/* 2460 */         newmqmd.setMsgFlags(this.mqmd.getMsgFlags());
/* 2461 */         newmqmd.setMsgId(this.mqmd.getMsgId());
/* 2462 */         newmqmd.setMsgSeqNumber(this.mqmd.getMsgSeqNumber());
/* 2463 */         newmqmd.setMsgType(this.mqmd.getMsgType());
/* 2464 */         newmqmd.setOffset(this.mqmd.getOffset());
/* 2465 */         newmqmd.setOriginalLength(this.mqmd.getOriginalLength());
/* 2466 */         newmqmd.setPersistence(this.mqmd.getPersistence());
/* 2467 */         newmqmd.setPriority(this.mqmd.getPriority());
/* 2468 */         newmqmd.setPutApplName(this.mqmd.getPutApplName());
/* 2469 */         newmqmd.setPutApplType(this.mqmd.getPutApplType());
/* 2470 */         newmqmd.setPutDate(this.mqmd.getPutDate());
/* 2471 */         newmqmd.setPutTime(this.mqmd.getPutTime());
/* 2472 */         newmqmd.setReplyToQ(this.mqmd.getReplyToQ());
/* 2473 */         newmqmd.setReplyToQMgr(this.mqmd.getReplyToQMgr());
/* 2474 */         newmqmd.setReport(this.mqmd.getReport());
/* 2475 */         newmqmd.setUserIdentifier(this.mqmd.getUserIdentifier());
/* 2476 */         newmqmd.setVersion(this.mqmd.getVersion());
/*      */         
/* 2478 */         ByteBuffer[] newbuffers = duplicate(this.buffers);
/* 2479 */         clone = new PoisonMessage(newmqmd, newbuffers);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2493 */         newbuffers = duplicate(this.buffers);
/* 2494 */         this.buffers = newbuffers;
/*      */       }
/* 2496 */       catch (JMSException je) {
/* 2497 */         if (Trace.isOn) {
/* 2498 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "clone()", (Throwable)je);
/*      */         }
/*      */ 
/*      */         
/* 2502 */         HashMap<String, Object> info = WMQPoison.this.ffstInfo();
/* 2503 */         info.put("exception", je);
/* 2504 */         Trace.ffst(this, "clone", "XN00S00E", info, null);
/*      */       } 
/* 2506 */       if (Trace.isOn) {
/* 2507 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "clone()", clone);
/*      */       }
/* 2509 */       return clone;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void convertToBytesMessage() throws JMSException {
/* 2519 */       if (Trace.isOn) {
/* 2520 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "convertToBytesMessage()");
/*      */       }
/*      */       
/* 2523 */       getWMQMessage();
/* 2524 */       if (this.wmqMsg instanceof WMQBytesMessage) {
/* 2525 */         if (Trace.isOn) {
/* 2526 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "convertToBytesMessage()", 1);
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 2532 */       WMQBytesMessage newMsg = new WMQBytesMessage();
/* 2533 */       duplicate(this.wmqMsg, (WMQMessage)newMsg);
/* 2534 */       this.wmqMsg = (WMQMessage)newMsg;
/* 2535 */       if (Trace.isOn) {
/* 2536 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "convertToBytesMessage()", 2);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private void duplicate(WMQMessage src, WMQMessage tgt) throws JMSException {
/*      */       JmqiCodepage codepage;
/* 2544 */       if (Trace.isOn) {
/* 2545 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "duplicate(WMQMessage,WMQMessage)", new Object[] { src, tgt });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2550 */       Enumeration<String> names = src.getPropertyNames();
/* 2551 */       while (names.hasMoreElements()) {
/* 2552 */         String name = names.nextElement();
/* 2553 */         Object value = src.getObjectProperty(name);
/* 2554 */         tgt.setObjectProperty(name, value);
/*      */       } 
/*      */ 
/*      */       
/* 2558 */       tgt.setJMSCorrelationID(src.getJMSCorrelationID());
/* 2559 */       tgt.setJMSDeliveryMode(src.getJMSDeliveryMode().intValue());
/* 2560 */       tgt.setJMSDestinationAsString(src.getJMSDestinationAsString());
/* 2561 */       Long expiration = src.getJMSExpiration();
/* 2562 */       assert expiration != null;
/* 2563 */       tgt.setJMSExpiration(expiration.longValue());
/* 2564 */       tgt.setTimeMillisFromWhichJMSExpirationWasBased(src
/* 2565 */           .getTimeMillisFromWhichJMSExpirationWasBased());
/*      */       
/* 2567 */       tgt.setJMSDeliveryDelay(src.getJMSDeliveryDelay());
/* 2568 */       tgt.setJMSDeliveryTime(src.getJMSDeliveryTime());
/*      */       
/* 2570 */       tgt.setJMSMessageID(src.getJMSMessageID());
/*      */       
/* 2572 */       Integer priority = src.getJMSPriority();
/* 2573 */       assert priority != null;
/* 2574 */       tgt.setJMSPriority(priority.intValue());
/*      */       
/* 2576 */       Boolean redelivered = src.getJMSRedelivered();
/* 2577 */       assert redelivered != null;
/* 2578 */       tgt.setJMSRedelivered(redelivered.booleanValue());
/*      */       
/* 2580 */       tgt.setJMSReplyToAsString(src.getJMSReplyToAsString());
/*      */       
/* 2582 */       Long timestamp = src.getJMSTimestamp();
/*      */       
/* 2584 */       if (timestamp != null) {
/* 2585 */         tgt.setJMSTimestamp(timestamp.longValue());
/*      */       }
/*      */       
/* 2588 */       tgt.setJMSType(src.getJMSType());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2594 */       int encoding = src.getIntProperty("JMS_IBM_Encoding");
/* 2595 */       String charset = src.getStringProperty("JMS_IBM_Character_Set");
/*      */       
/*      */       try {
/* 2598 */         codepage = JmqiCodepage.getJmqiCodepage(WMQPoison.this.env, charset);
/*      */         
/* 2600 */         if (codepage == null) {
/* 2601 */           UnsupportedEncodingException traceRet1 = new UnsupportedEncodingException(charset);
/* 2602 */           if (Trace.isOn) {
/* 2603 */             Trace.throwing(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "duplicate(WMQMessage,WMQMessage)", traceRet1, 1);
/*      */           }
/*      */           
/* 2606 */           throw traceRet1;
/*      */         }
/*      */       
/*      */       }
/* 2610 */       catch (UnsupportedEncodingException e) {
/* 2611 */         if (Trace.isOn) {
/* 2612 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "duplicate(WMQMessage,WMQMessage)", e);
/*      */         }
/*      */         
/* 2615 */         HashMap<String, Object> inserts = new HashMap<>();
/* 2616 */         inserts.put("XMSC_INSERT_PROPERTY", "JMS_IBM_Character_Set");
/* 2617 */         inserts.put("XMSC_INSERT_VALUE", charset);
/* 2618 */         JMSException je = (JMSException)NLSServices.createException("JMSCMQ1006", inserts);
/* 2619 */         if (Trace.isOn) {
/* 2620 */           Trace.throwing(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "duplicate(WMQMessage,WMQMessage)", (Throwable)je, 2);
/*      */         }
/*      */         
/* 2623 */         throw je;
/*      */       } 
/*      */       
/* 2626 */       byte[] payload = src._exportBody(encoding, codepage);
/* 2627 */       tgt._importBody(payload, 0, -1, encoding, codepage);
/* 2628 */       if (Trace.isOn) {
/* 2629 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "duplicate(WMQMessage,WMQMessage)");
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
/*      */     private ByteBuffer[] duplicate(ByteBuffer[] src) {
/* 2642 */       if (Trace.isOn) {
/* 2643 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "duplicate(ByteBuffer [ ])", new Object[] { src });
/*      */       }
/*      */       
/* 2646 */       ByteBuffer[] newbuffers = new ByteBuffer[src.length];
/* 2647 */       for (int i = 0; i < src.length; i++) {
/* 2648 */         ByteBuffer newbuff = ByteBuffer.allocate(src[i].capacity());
/* 2649 */         src[i].rewind();
/* 2650 */         byte[] array = src[i].array();
/* 2651 */         byte[] newarray = new byte[array.length];
/* 2652 */         System.arraycopy(array, 0, newarray, 0, array.length);
/* 2653 */         newbuff.put(newarray);
/* 2654 */         newbuff.limit(src[i].limit());
/* 2655 */         newbuffers[i] = newbuff;
/*      */       } 
/* 2657 */       if (Trace.isOn) {
/* 2658 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "duplicate(ByteBuffer [ ])", newbuffers);
/*      */       }
/*      */       
/* 2661 */       return newbuffers;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 2670 */       if (Trace.isOn) {
/* 2671 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "toString()");
/*      */       }
/* 2673 */       StringBuffer buff = new StringBuffer();
/*      */       try {
/* 2675 */         buff.append(super.toString());
/* 2676 */         buff.append(" mqmd=" + ((this.mqmd == null) ? "<null>" : this.mqmd.toString()));
/* 2677 */         buff.append(" buffers=" + ((this.buffers == null) ? "<null>" : Arrays.<ByteBuffer>asList(this.buffers).toString()));
/* 2678 */         buff.append(" wmqMsg=" + ((this.wmqMsg == null) ? "<null>" : this.wmqMsg.toString()));
/* 2679 */         buff.append(" targetDestination=" + ((this.targetDestination == null) ? "<null>" : (String)this.targetDestination));
/*      */       }
/* 2681 */       catch (Exception e) {
/* 2682 */         if (Trace.isOn) {
/* 2683 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "toString()", e);
/*      */         }
/* 2685 */         buff.append("Unknown");
/*      */       } 
/* 2687 */       String traceRet1 = buff.toString();
/* 2688 */       if (Trace.isOn) {
/* 2689 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.PoisonMessage", "toString()", traceRet1);
/*      */       }
/* 2691 */       return traceRet1;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\WMQPoison.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */