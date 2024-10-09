/*      */ package com.ibm.msg.client.wmq.internal;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.MQPMO;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Hobj;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.system.JmqiSP;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.SpiOpenOptions;
/*      */ import com.ibm.mq.jmqi.system.SpiPutOptions;
/*      */ import com.ibm.mq.jmqi.system.zrfp.Triplet;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.TableBuilder;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessageProducer;
/*      */ import com.ibm.msg.client.wmq.common.internal.Reason;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQConsumerOwner;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQDestination;
/*      */ import com.ibm.msg.client.wmq.common.internal.WMQPropertyContext;
/*      */ import com.ibm.msg.client.wmq.common.internal.messages.WMQMarshal;
/*      */ import com.ibm.msg.client.wmq.common.internal.messages.WMQMessage;
/*      */ import com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshal;
/*      */ import com.ibm.msg.client.wmq.common.internal.messages.WMQSendMarshalMH;
/*      */ import java.io.PrintWriter;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.HashMap;
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
/*      */ public class WMQMessageProducer
/*      */   extends WMQPropertyContext
/*      */   implements ProviderMessageProducer
/*      */ {
/*      */   private static final long serialVersionUID = 2752307110812311630L;
/*      */   static final String sccsid = "@(#) com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQMessageProducer.java, jmscc.wmq, k000 1.92 09/05/28 16:07:17";
/*      */   
/*      */   static {
/*   78 */     if (Trace.isOn) {
/*   79 */       Trace.data("com.ibm.msg.client.wmq.internal.WMQMessageProducer", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq/src/com/ibm/msg/client/wmq/internal/WMQMessageProducer.java");
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
/*      */   abstract class ProducerShadow
/*      */   {
/*  103 */     WMQDestination destination = null;
/*      */ 
/*      */     
/*      */     boolean identified;
/*      */ 
/*      */     
/*  109 */     WMQDestination lastUsedDestination = null;
/*      */ 
/*      */     
/*  112 */     MQOD mqod = null;
/*      */ 
/*      */     
/*  115 */     MQPMO pmo = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  120 */     private WMQSendMarshal sendMarshal = null;
/*      */ 
/*      */ 
/*      */     
/*  124 */     int defaultPutResponseType = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     abstract void close() throws JMSException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     abstract int computeOpenOptions();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void computePMO(WMQMessage message) throws JMSException {
/*  144 */       if (Trace.isOn) {
/*  145 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.ProducerShadow", "computePMO(WMQMessage)", new Object[] { message });
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  152 */       if (this.pmo == null) {
/*  153 */         this.pmo = WMQMessageProducer.this.env.newMQPMO();
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
/*  165 */       if (this.destination != this.lastUsedDestination) {
/*      */ 
/*      */         
/*  168 */         int options = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  183 */         JmqiSP sp = (JmqiSP)WMQMessageProducer.this.mq;
/*      */         
/*  185 */         if (!sp.isCICS() && !sp.isIMS()) {
/*  186 */           if (WMQMessageProducer.this.session.getTransacted()) {
/*  187 */             if (Trace.isOn) {
/*  188 */               Trace.data(this, "computePMO()", "Put under syncpoint = true", null);
/*      */             }
/*  190 */             options |= 0x2;
/*      */           } else {
/*  192 */             if (Trace.isOn) {
/*  193 */               Trace.data(this, "computePMO()", "Put under syncpoint = false", null);
/*      */             }
/*  195 */             options |= 0x4;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  206 */         else if (!WMQMessageProducer.this.session.getTransacted() && WMQMessageProducer.this.session.getAckMode() == 1) {
/*  207 */           if (Trace.isOn) {
/*  208 */             Trace.data(this, "computePMO()", "CICS JVMSERVER/IMS: Put under syncpoint = false", null);
/*      */           }
/*  210 */           options |= 0x4;
/*      */         } else {
/*  212 */           if (Trace.isOn) {
/*  213 */             Trace.data(this, "computePMO()", "CICS JVMSERVER/IMS: Put under syncpoint = true", null);
/*      */           }
/*  215 */           options |= 0x2;
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
/*      */         
/*  230 */         int putAsyncValue = this.destination.getIntProperty("putAsyncAllowed");
/*      */         
/*  232 */         switch (putAsyncValue) {
/*      */           
/*      */           case 1:
/*  235 */             if (Trace.isOn) {
/*  236 */               Trace.data(this, "computePMO()", "Sync or Async put = MQPMO_ASYNC_RESPONSE", null);
/*      */             }
/*  238 */             options |= 0x10000;
/*      */             break;
/*      */           
/*      */           case 0:
/*  242 */             if (Trace.isOn) {
/*  243 */               Trace.data(this, "computePMO()", "Sync or Async put = MQPMO_SYNC_RESPONSE", null);
/*      */             }
/*  245 */             options |= 0x20000;
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/*  252 */             if (this.destination.isTopic()) {
/*  253 */               if (Trace.isOn) {
/*  254 */                 Trace.data(this, "computePMO()", "Sync or Async put = MQPMO_RESPONSE_AS_TOPIC_DEF", null);
/*      */               }
/*  256 */               options |= 0x0;
/*      */               break;
/*      */             } 
/*  259 */             if (Trace.isOn) {
/*  260 */               Trace.data(this, "computePMO()", "Sync or Async put = MQPMO_RESPONSE_AS_Q_DEF", null);
/*      */             }
/*  262 */             options |= 0x0;
/*      */             break;
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
/*      */         
/*  278 */         int fiq = this.destination.getIntProperty("failIfQuiesce");
/*      */         
/*  280 */         if (fiq == 1) {
/*  281 */           if (Trace.isOn) {
/*  282 */             Trace.data(this, "computePMO()", "Put fail-if-quiesce = yes", null);
/*      */           }
/*  284 */           options |= 0x2000;
/*      */         } else {
/*  286 */           if (Trace.isOn) {
/*  287 */             Trace.data(this, "computePMO()", "Put fail-if-quiesce = no", null);
/*      */           }
/*  289 */           options &= 0xFFFFDFFF;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  295 */         int messageContextOptions = computeMessageContextOptions();
/*  296 */         options |= messageContextOptions;
/*      */ 
/*      */         
/*  299 */         if (Trace.isOn) {
/*  300 */           Trace.data(this, "computePMO()", "PMO options", Integer.valueOf(options));
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  305 */         this.pmo.setOptions(options);
/*      */ 
/*      */         
/*  308 */         this.lastUsedDestination = this.destination;
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
/*  330 */       boolean newMessageId = true;
/*  331 */       if (this.destination.getBooleanProperty("mdWriteEnabled") == true) {
/*      */ 
/*      */         
/*  334 */         if (message.propertyExists("JMS_IBM_MQMD_MsgId")) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  339 */           newMessageId = false;
/*      */         }
/*      */         else {
/*      */           
/*  343 */           newMessageId = true;
/*      */         } 
/*      */       } else {
/*      */         
/*  347 */         newMessageId = true;
/*      */       } 
/*  349 */       int pmoOptions = this.pmo.getOptions();
/*  350 */       if (newMessageId) {
/*  351 */         pmoOptions |= 0x40;
/*      */       } else {
/*  353 */         pmoOptions &= 0xFFFFFFBF;
/*      */       } 
/*  355 */       this.pmo.setOptions(pmoOptions);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  365 */       if (this.destination.isTopic())
/*      */       {
/*      */         
/*  368 */         if (message.propertyExists("JMS_IBM_Retain")) {
/*  369 */           int retain = message.getIntProperty("JMS_IBM_Retain");
/*  370 */           if (retain == 1) {
/*  371 */             if (Trace.isOn) {
/*  372 */               Trace.data(this, "computePMO()", "Retained publication: using MQPMO_RETAIN", null);
/*      */             }
/*  374 */             int options = this.pmo.getOptions();
/*  375 */             options |= 0x200000;
/*  376 */             this.pmo.setOptions(options);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  388 */             this.lastUsedDestination = null;
/*      */           } 
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/*  394 */       if (this.sendMarshal instanceof WMQSendMarshalMH)
/*      */       {
/*  396 */         if (this.pmo.getVersion() < 3) {
/*  397 */           this.pmo.setVersion(3);
/*      */         }
/*      */       }
/*      */       
/*  401 */       if (Trace.isOn) {
/*  402 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.ProducerShadow", "computePMO(WMQMessage)");
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
/*      */     int computeMessageContextOptions() throws JMSException {
/*  420 */       if (Trace.isOn) {
/*  421 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.ProducerShadow", "computeMessageContextOptions()");
/*      */       }
/*  423 */       int messageContextOptions = 0;
/*  424 */       int value = this.destination.getIntProperty("mdMessageContext");
/*  425 */       if (value == 1) {
/*      */ 
/*      */         
/*  428 */         messageContextOptions = 1024;
/*  429 */       } else if (value == 2) {
/*      */ 
/*      */         
/*  432 */         messageContextOptions = 2048;
/*      */       } else {
/*      */         
/*  435 */         messageContextOptions = 0;
/*      */       } 
/*  437 */       if (Trace.isOn) {
/*  438 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.ProducerShadow", "computeMessageContextOptions()", 
/*  439 */             Integer.valueOf(messageContextOptions));
/*      */       }
/*  441 */       return messageContextOptions;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void informSession() {
/*  449 */       if (Trace.isOn) {
/*  450 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.ProducerShadow", "informSession()");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  455 */       int pmoOptions = this.pmo.getOptions();
/*      */       
/*  457 */       if (Trace.isOn) {
/*  458 */         Trace.data(this, "informSession()", "PMO options read", Integer.valueOf(pmoOptions));
/*      */       }
/*      */ 
/*      */       
/*  462 */       boolean underSyncpoint = ((pmoOptions & 0x2) == 2);
/*      */ 
/*      */       
/*  465 */       WMQConsumerOwner.Operation op = null;
/*  466 */       if ((pmoOptions & 0x20000) == 131072) {
/*      */         
/*  468 */         op = WMQConsumerOwner.Operation.SYNCPUT;
/*  469 */       } else if ((pmoOptions & 0x10000) == 65536) {
/*      */ 
/*      */         
/*  472 */         op = WMQConsumerOwner.Operation.ASYNCPUT;
/*  473 */       } else if ((pmoOptions & 0x0) == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  481 */         if (this.defaultPutResponseType == 1) {
/*  482 */           op = WMQConsumerOwner.Operation.SYNCPUT;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  495 */           op = WMQConsumerOwner.Operation.ASYNCPUT;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  502 */       WMQMessageProducer.this.session.operationPerformed(op, underSyncpoint);
/*      */ 
/*      */       
/*  505 */       if (Trace.isOn) {
/*  506 */         Trace.data(this, "informSession()", (op == WMQConsumerOwner.Operation.SYNCPUT) ? "Heuristic says last put was sync" : "Heuristic says last put was Async", null);
/*      */       }
/*      */ 
/*      */       
/*  510 */       if (Trace.isOn) {
/*  511 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.ProducerShadow", "informSession()");
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
/*      */     abstract void initialise() throws JMSException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void send(WMQDestination destAtSendCall, WMQMessage message) throws JMSException {
/*  539 */       if (Trace.isOn) {
/*  540 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.ProducerShadow", "send(WMQDestination,WMQMessage)", new Object[] { destAtSendCall, message });
/*      */       }
/*      */ 
/*      */       
/*  544 */       if (this.identified) {
/*      */ 
/*      */ 
/*      */         
/*  548 */         if (destAtSendCall != null && this.destination != destAtSendCall) {
/*  549 */           HashMap<String, Object> info = new HashMap<>();
/*  550 */           info.put("destination at instantiation time", this.destination);
/*  551 */           info.put("destination at send call", destAtSendCall);
/*  552 */           Trace.ffst(this, "send(WMQDestination,WMQMessage)", "XN005006", info, JMSException.class);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  557 */         this.destination = destAtSendCall;
/*      */       } 
/*      */       
/*  560 */       if (this.destination.isTopic()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  566 */         WMQConnection connection = (WMQConnection)WMQMessageProducer.this.session.getConnection();
/*  567 */         WMQMessageProducer.this.ensureNonNull("send", "producer's connection", connection);
/*      */         try {
/*  569 */           message.setStringProperty("JMS_IBM_ConnectionID", connection
/*  570 */               .getHconn().getConnectionIdAsString());
/*  571 */         } catch (JmqiException jmqiException) {
/*  572 */           WMQMessageProducer.this.checkJmqiCallSuccess("JMSWMQ2007", this.destination, "MFB001001", WMQMessageProducer.this
/*  573 */               .env.newPint(jmqiException.getCompCode()), WMQMessageProducer.this.env.newPint(jmqiException.getReason()));
/*      */         } 
/*  575 */         message.setStringProperty("XMSC_CLIENT_ID", connection.getStringProperty("XMSC_CLIENT_ID"));
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  580 */       if (this.sendMarshal == null) {
/*  581 */         this.sendMarshal = WMQMarshal.newSendMarshal(WMQMessageProducer.this.session, false);
/*      */       }
/*      */ 
/*      */       
/*  585 */       this.sendMarshal.importProviderMessage(WMQMessageProducer.this.session, message, this.destination);
/*      */ 
/*      */       
/*  588 */       MQMD md = this.sendMarshal.exportMQMD();
/*  589 */       WMQMessageProducer.this.ensureNonNull("send", "MD for message to put", md);
/*      */ 
/*      */       
/*  592 */       ByteBuffer[] buffers = this.sendMarshal.exportMessageBuffers();
/*      */ 
/*      */       
/*  595 */       Triplet[] triplets = null;
/*  596 */       if (this.sendMarshal instanceof WMQSendMarshalMH) {
/*  597 */         WMQSendMarshalMH sm = (WMQSendMarshalMH)this.sendMarshal;
/*  598 */         triplets = sm.getTriplets();
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  603 */       computePMO(message);
/*      */       
/*  605 */       SpiPutOptions spo = computeSpiPutOptions(message);
/*      */ 
/*      */       
/*  608 */       sendInternal(md, buffers, triplets, spo);
/*      */ 
/*      */       
/*  611 */       informSession();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  618 */       this.sendMarshal.informSendMarshal(md);
/*      */       
/*  620 */       if (Trace.isOn) {
/*  621 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.ProducerShadow", "send(WMQDestination,WMQMessage)");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     private SpiPutOptions computeSpiPutOptions(WMQMessage message) throws JMSException {
/*  627 */       if (Trace.isOn) {
/*  628 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.ProducerShadow", "computeSpiPutOptions(WMQMessage)", new Object[] { message });
/*      */       }
/*      */ 
/*      */       
/*  632 */       SpiPutOptions spo = null;
/*  633 */       long deliveryDelay = message.getJMSDeliveryDelay();
/*  634 */       if (deliveryDelay != 0L) {
/*  635 */         spo = ((JmqiSystemEnvironment)WMQMessageProducer.this.env).newSpiPutOptions();
/*  636 */         spo.setVersion(4);
/*  637 */         spo.setDeliveryDelay(deliveryDelay);
/*      */       } 
/*  639 */       if (Trace.isOn) {
/*  640 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.ProducerShadow", "computeSpiPutOptions(WMQMessage)", spo);
/*      */       }
/*      */       
/*  643 */       return spo;
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
/*      */     abstract void sendInternal(MQMD param1MQMD, ByteBuffer[] param1ArrayOfByteBuffer, Triplet[] param1ArrayOfTriplet, SpiPutOptions param1SpiPutOptions) throws JMSException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void updateMQOD() throws JMSException {
/*  665 */       if (Trace.isOn) {
/*  666 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.ProducerShadow", "updateMQOD()");
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
/*  677 */       if (this.destination.isQueue()) {
/*  678 */         if (Trace.isOn) {
/*  679 */           Trace.data(this, "updateMQOD()", "Messaging style for this send: Point-to-point", this.destination
/*  680 */               .toURI());
/*      */         }
/*  682 */         this.mqod.setObjectType(1);
/*  683 */         this.mqod.setObjectName(this.destination.getName());
/*  684 */         this.mqod.setObjectQMgrName(this.destination.getStringProperty("XMSC_WMQ_QUEUE_MANAGER"));
/*  685 */       } else if (this.destination.isTopic()) {
/*  686 */         if (Trace.isOn) {
/*  687 */           Trace.data(this, "updateMQOD()", "Messaging style for this send: Publish/Subscribe", this.destination
/*  688 */               .toURI());
/*      */         }
/*  690 */         this.mqod.setVersion(4);
/*  691 */         this.mqod.setObjectType(8);
/*      */ 
/*      */ 
/*      */         
/*  695 */         this.mqod.getObjectString().setVsString(this.destination.getName());
/*  696 */         this.mqod.getObjectString().setVsBufSize(10240);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  701 */         String streamName = WMQMessageProducer.this.getStringProperty("XMSC_WMQ_BROKER_PUBQ");
/*      */         
/*  703 */         if (!streamName.equals("SYSTEM.BROKER.DEFAULT.STREAM")) {
/*  704 */           this.mqod.setObjectName(streamName);
/*      */         }
/*      */       } else {
/*      */         
/*  708 */         HashMap<String, Object> info = new HashMap<>();
/*  709 */         info.put("methodName", "updateMQOD()");
/*  710 */         info.put("message", "destination type is unexpected");
/*  711 */         info.put("destination", this.destination);
/*  712 */         Trace.ffst(this, "updateMQOD()", "XN005007", info, JMSException.class);
/*      */       } 
/*      */       
/*  715 */       if (Trace.isOn) {
/*  716 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.ProducerShadow", "updateMQOD()");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   class SpiIdentifiedProducerShadow
/*      */     extends ProducerShadow
/*      */   {
/*  729 */     private Hobj destinationHobj = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     SpiIdentifiedProducerShadow(WMQDestination dest) {
/*  737 */       if (Trace.isOn) {
/*  738 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.SpiIdentifiedProducerShadow", "<init>(WMQDestination)", new Object[] { dest });
/*      */       }
/*      */       
/*  741 */       this.identified = true;
/*  742 */       this.destination = dest;
/*  743 */       if (Trace.isOn) {
/*  744 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.SpiIdentifiedProducerShadow", "<init>(WMQDestination)");
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
/*      */     void close() throws JMSException {
/*  756 */       if (Trace.isOn) {
/*  757 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.SpiIdentifiedProducerShadow", "close()");
/*      */       }
/*  759 */       int closeOptions = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  768 */       Pint completionCode = WMQMessageProducer.this.env.newPint();
/*  769 */       Pint reasonCode = WMQMessageProducer.this.env.newPint();
/*      */       
/*  771 */       Phobj phobj = WMQMessageProducer.this.env.newPhobj();
/*  772 */       phobj.setHobj(this.destinationHobj);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  778 */       WMQMessageProducer.this.session.lockHconn();
/*      */       
/*      */       try {
/*  781 */         WMQMessageProducer.this.session.incrementCloseCounter();
/*      */         
/*  783 */         boolean didSuspend = false;
/*      */ 
/*      */         
/*      */         try {
/*  787 */           didSuspend = WMQMessageProducer.this.session.suspendAsyncService();
/*      */           
/*  789 */           WMQMessageProducer.this.mq.MQCLOSE(WMQMessageProducer.this.hconn, phobj, closeOptions, completionCode, reasonCode);
/*      */         } finally {
/*  791 */           if (Trace.isOn) {
/*  792 */             Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.SpiIdentifiedProducerShadow", "close()");
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  800 */           WMQMessageProducer.this.session.decrementCloseCounter();
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           try {
/*  806 */             if (didSuspend) {
/*  807 */               WMQMessageProducer.this.session.resumeAsyncService();
/*      */             }
/*      */           }
/*      */           finally {
/*      */             
/*  812 */             WMQMessageProducer.this.session.signalHconn();
/*      */           } 
/*      */         } 
/*      */       } finally {
/*      */         
/*  817 */         WMQMessageProducer.this.session.unlockHconn();
/*      */       } 
/*      */ 
/*      */       
/*  821 */       WMQMessageProducer.this.checkJmqiCallSuccess(
/*  822 */           this.destination.isQueue() ? "JMSWMQ2000" : "JMSWMQ2003", this.destination, "XN005002", completionCode, reasonCode);
/*      */       
/*  824 */       if (Trace.isOn) {
/*  825 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.SpiIdentifiedProducerShadow", "close()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void initialise() throws JMSException {
/*  836 */       if (Trace.isOn) {
/*  837 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.SpiIdentifiedProducerShadow", "initialise()");
/*      */       }
/*      */       
/*  840 */       this.mqod = WMQMessageProducer.this.env.newMQOD();
/*      */ 
/*      */       
/*  843 */       updateMQOD();
/*      */       
/*  845 */       Phobj phobj = null;
/*  846 */       SpiOpenOptions spiOO = null;
/*      */ 
/*      */       
/*  849 */       int openOptions = computeOpenOptions();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  859 */       int fiq = this.destination.getIntProperty("failIfQuiesce");
/*      */       
/*  861 */       if (fiq == 1) {
/*  862 */         if (Trace.isOn) {
/*  863 */           Trace.data(this, "initialise()", "Open fail-if-quiesce = yes", null);
/*      */         }
/*  865 */         openOptions |= 0x2000;
/*      */       } else {
/*      */         
/*  868 */         if (Trace.isOn) {
/*  869 */           Trace.data(this, "initialise()", "Open fail-if-quiesce = no", null);
/*      */         }
/*  871 */         openOptions &= 0xFFFFDFFF;
/*      */       } 
/*      */ 
/*      */       
/*  875 */       String altuser = this.destination.getStringProperty("alternateUserId");
/*      */       
/*  877 */       if (altuser != null) {
/*  878 */         this.mqod.setAlternateUserId(this.destination.getStringProperty("alternateUserId"));
/*  879 */         openOptions |= 0x1000;
/*      */       } else {
/*  881 */         openOptions &= 0xFFFFEFFF;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  887 */       int messageContextOptions = computeMessageContextOptions();
/*  888 */       openOptions |= messageContextOptions;
/*      */       
/*  890 */       phobj = WMQMessageProducer.this.env.newPhobj();
/*      */       
/*  892 */       spiOO = ((JmqiSystemEnvironment)WMQMessageProducer.this.env).newSpiOpenOptions();
/*  893 */       spiOO.setOptions(openOptions);
/*      */ 
/*      */ 
/*      */       
/*  897 */       ((JmqiSP)WMQMessageProducer.this.mq).spiOpen(WMQMessageProducer.this.hconn, this.mqod, spiOO, phobj, WMQMessageProducer.this.cc, WMQMessageProducer.this.rc);
/*      */ 
/*      */       
/*  900 */       WMQMessageProducer.this.checkJmqiCallSuccess(
/*  901 */           this.destination.isQueue() ? "JMSWMQ2008" : "JMSWMQ2006", this.destination, "XN005003");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  909 */       if (WMQMessageProducer.this.rc.x == 0 && WMQMessageProducer.this.cc.x == 0 && 
/*  910 */         this.destination.isQueue() && this.destination.getName() != null && 
/*  911 */         !this.destination.getName().equals(this.mqod.getObjectName().trim())) {
/*      */         
/*  913 */         WMQMessageProducer.this.mq.MQCLOSE(WMQMessageProducer.this.hconn, phobj, 0, WMQMessageProducer.this.cc, WMQMessageProducer.this.rc);
/*      */ 
/*      */         
/*  916 */         HashMap<String, String> inserts = new HashMap<>();
/*  917 */         inserts.put("XMSC_DESTINATION_NAME", this.destination.getName());
/*  918 */         JMSException je = Reason.createException("JMSWMQ2022", inserts, 2152, 2, WMQMessageProducer.this
/*  919 */             .env);
/*      */         
/*  921 */         if (Trace.isOn) {
/*  922 */           Trace.throwing(this, "com.ibm.msg.client.wmq.internal.SpiIdentifiedProducerShadow", "initialise()", (Throwable)je);
/*      */         }
/*      */         
/*  925 */         throw je;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  930 */       this.destinationHobj = phobj.getHobj();
/*  931 */       WMQMessageProducer.this.ensureNonNull("initialise", "Destination hobj after MQOPEN", this.destinationHobj);
/*      */       
/*  933 */       this.defaultPutResponseType = spiOO.getDefPutResponseType();
/*      */       
/*  935 */       if (Trace.isOn) {
/*  936 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.SpiIdentifiedProducerShadow", "initialise()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void sendInternal(MQMD md, ByteBuffer[] buffers, Triplet[] triplets, SpiPutOptions spo) throws JMSException {
/*  946 */       if (Trace.isOn) {
/*  947 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.SpiIdentifiedProducerShadow", "sendInternal(MQMD,ByteBuffer [ ],Triplet [ ],SpiPutOptions)", new Object[] { md, buffers, triplets, spo });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  952 */       String messageId = "JMSWMQ2007";
/*      */       
/*  954 */       WMQMessageProducer.this.cc.x = 0;
/*  955 */       WMQMessageProducer.this.rc.x = 0;
/*      */ 
/*      */       
/*  958 */       if (spo == null) {
/*  959 */         if (triplets == null) {
/*  960 */           WMQMessageProducer.this.mqSPI.jmqiPut(WMQMessageProducer.this.hconn, this.destinationHobj, md, this.pmo, buffers, WMQMessageProducer.this.cc, WMQMessageProducer.this.rc);
/*      */         } else {
/*  962 */           WMQMessageProducer.this.mqSPI.jmqiPutWithTriplets(WMQMessageProducer.this.hconn, this.destinationHobj, md, this.pmo, buffers, triplets, WMQMessageProducer.this.cc, WMQMessageProducer.this.rc);
/*      */         } 
/*      */       } else {
/*  965 */         if (spo.getDeliveryDelay() != 0L) {
/*  966 */           messageId = "JMSWMQ2024";
/*      */         }
/*  968 */         WMQMessageProducer.this.mqSPI.spiPut(WMQMessageProducer.this.hconn, this.destinationHobj, md, this.pmo, spo, buffers, WMQMessageProducer.this.cc, WMQMessageProducer.this.rc);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  973 */       WMQMessageProducer.this.checkJmqiCallSuccess(messageId, this.destination, "XN005004");
/*      */       
/*  975 */       if (Trace.isOn) {
/*  976 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.SpiIdentifiedProducerShadow", "sendInternal(MQMD,ByteBuffer [ ],Triplet [ ],SpiPutOptions)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int computeOpenOptions() {
/*  984 */       if (Trace.isOn) {
/*  985 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.SpiIdentifiedProducerShadow", "computeOpenOptions()");
/*      */       }
/*      */ 
/*      */       
/*  989 */       if (Trace.isOn) {
/*  990 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.SpiIdentifiedProducerShadow", "computeOpenOptions()", 
/*  991 */             Integer.valueOf(16));
/*      */       }
/*  993 */       return 16;
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
/*      */   class SpiUnidentifiedProducerShadow
/*      */     extends ProducerShadow
/*      */   {
/*      */     SpiUnidentifiedProducerShadow(WMQDestination dest) {
/* 1009 */       if (Trace.isOn) {
/* 1010 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.SpiUnidentifiedProducerShadow", "<init>(WMQDestination)", new Object[] { dest });
/*      */       }
/*      */       
/* 1013 */       this.identified = false;
/* 1014 */       this.destination = dest;
/* 1015 */       if (Trace.isOn) {
/* 1016 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.SpiUnidentifiedProducerShadow", "<init>(WMQDestination)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void close() throws JMSException {
/* 1027 */       if (Trace.isOn) {
/* 1028 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.SpiUnidentifiedProducerShadow", "close()");
/*      */       }
/*      */       
/* 1031 */       if (Trace.isOn) {
/* 1032 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.SpiUnidentifiedProducerShadow", "close()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void initialise() throws JMSException {
/* 1042 */       if (Trace.isOn) {
/* 1043 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.SpiUnidentifiedProducerShadow", "initialise()");
/*      */       }
/*      */       
/* 1046 */       if (Trace.isOn) {
/* 1047 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.SpiUnidentifiedProducerShadow", "initialise()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     int computeOpenOptions() {
/* 1054 */       if (Trace.isOn) {
/* 1055 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.SpiUnidentifiedProducerShadow", "computeOpenOptions()");
/*      */       }
/*      */ 
/*      */       
/* 1059 */       if (Trace.isOn) {
/* 1060 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.SpiUnidentifiedProducerShadow", "computeOpenOptions()", 
/* 1061 */             Integer.valueOf(16));
/*      */       }
/* 1063 */       return 16;
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
/*      */     void sendInternal(MQMD md, ByteBuffer[] buffers, Triplet[] triplets, SpiPutOptions spo) throws JMSException {
/* 1076 */       if (Trace.isOn) {
/* 1077 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.SpiUnidentifiedProducerShadow", "sendInternal(MQMD,ByteBuffer [ ],Triplet [ ],SpiPutOptions)", new Object[] { md, buffers, triplets, spo });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1082 */       WMQMessageProducer.this.cc.x = 0;
/* 1083 */       WMQMessageProducer.this.rc.x = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1088 */       this.mqod = WMQMessageProducer.this.env.newMQOD();
/*      */ 
/*      */       
/* 1091 */       updateMQOD();
/*      */       
/* 1093 */       String messageId = "JMSWMQ2007";
/*      */ 
/*      */       
/* 1096 */       if (spo == null) {
/* 1097 */         if (triplets == null) {
/* 1098 */           WMQMessageProducer.this.mqSPI.jmqiPut1(WMQMessageProducer.this.hconn, this.mqod, md, this.pmo, buffers, WMQMessageProducer.this.cc, WMQMessageProducer.this.rc);
/*      */         } else {
/* 1100 */           WMQMessageProducer.this.mqSPI.jmqiPut1WithTriplets(WMQMessageProducer.this.hconn, this.mqod, md, this.pmo, buffers, triplets, WMQMessageProducer.this.cc, WMQMessageProducer.this.rc);
/*      */         } 
/*      */       } else {
/* 1103 */         if (spo.getDeliveryDelay() != 0L) {
/* 1104 */           messageId = "JMSWMQ2024";
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1110 */         Phobj pHobj = WMQMessageProducer.this.env.newPhobj();
/* 1111 */         WMQMessageProducer.this.mq.MQOPEN(WMQMessageProducer.this.hconn, this.mqod, computeOpenOptions(), pHobj, WMQMessageProducer.this.cc, WMQMessageProducer.this.rc);
/* 1112 */         if (WMQMessageProducer.this.cc.x == 0) {
/* 1113 */           WMQMessageProducer.this.mqSPI.spiPut(WMQMessageProducer.this.hconn, pHobj.getHobj(), md, this.pmo, spo, buffers, WMQMessageProducer.this.cc, WMQMessageProducer.this.rc);
/* 1114 */           if (WMQMessageProducer.this.cc.x == 0) {
/* 1115 */             WMQMessageProducer.this.mq.MQCLOSE(WMQMessageProducer.this.hconn, pHobj, 0, WMQMessageProducer.this.cc, WMQMessageProducer.this.rc);
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1123 */       WMQMessageProducer.this.checkJmqiCallSuccess(messageId, this.destination, "XN005005");
/*      */       
/* 1125 */       if (Trace.isOn) {
/* 1126 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.SpiUnidentifiedProducerShadow", "sendInternal(MQMD,ByteBuffer [ ],Triplet [ ],SpiPutOptions)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   class zOSDumpQmgrShadow
/*      */     extends SpiIdentifiedProducerShadow
/*      */   {
/*      */     public static final String ZOS_SERVICE1_DUMP_QUEUE = "SERVICE1.DUMP";
/*      */     
/*      */     public static final String ZOS_SERVICE2_DUMP_QUEUE = "SERVICE2.DUMP";
/*      */     
/*      */     private static final int MQOO_DUMP_QMGRANDCHIN = 3;
/*      */     
/*      */     private boolean dumpChinit;
/*      */ 
/*      */     
/*      */     zOSDumpQmgrShadow(WMQDestination dest) {
/* 1146 */       this(dest, false);
/* 1147 */       if (Trace.isOn) {
/* 1148 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.zOSDumpQmgrShadow", "<init>(WMQDestination)", new Object[] { dest });
/*      */       }
/*      */       
/* 1151 */       if (Trace.isOn) {
/* 1152 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.zOSDumpQmgrShadow", "<init>(WMQDestination)");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     zOSDumpQmgrShadow(WMQDestination dest, boolean dumpChinit) {
/* 1158 */       super(dest);
/* 1159 */       if (Trace.isOn) {
/* 1160 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.zOSDumpQmgrShadow", "<init>(WMQDestination,boolean)", new Object[] { dest, 
/* 1161 */               Boolean.valueOf(dumpChinit) });
/*      */       }
/* 1163 */       this.dumpChinit = dumpChinit;
/* 1164 */       if (Trace.isOn) {
/* 1165 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.zOSDumpQmgrShadow", "<init>(WMQDestination,boolean)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     void updateMQOD() throws JMSException {
/* 1172 */       if (Trace.isOn) {
/* 1173 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.zOSDumpQmgrShadow", "updateMQOD()");
/*      */       }
/*      */       
/* 1176 */       this.mqod.setObjectType(999);
/* 1177 */       if (this.dumpChinit) {
/* 1178 */         this.mqod.setObjectName("SERVICE2.DUMP");
/*      */       } else {
/* 1180 */         this.mqod.setObjectName("SERVICE1.DUMP");
/*      */       } 
/*      */       
/* 1183 */       if (Trace.isOn) {
/* 1184 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.zOSDumpQmgrShadow", "updateMQOD()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     int computeOpenOptions() {
/* 1191 */       if (Trace.isOn) {
/* 1192 */         Trace.entry(this, "com.ibm.msg.client.wmq.internal.zOSDumpQmgrShadow", "computeOpenOptions()");
/*      */       }
/*      */       
/* 1195 */       int options = 16;
/* 1196 */       if (this.dumpChinit) {
/* 1197 */         options |= 0x3;
/*      */       }
/* 1199 */       if (Trace.isOn) {
/* 1200 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.zOSDumpQmgrShadow", "computeOpenOptions()", 
/* 1201 */             Integer.valueOf(options));
/*      */       }
/* 1203 */       return options;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1209 */   private transient Pint cc = null;
/*      */ 
/*      */   
/*      */   private transient JmqiEnvironment env;
/*      */ 
/*      */   
/* 1215 */   private transient Hconn hconn = null;
/*      */ 
/*      */   
/*      */   private boolean isClosed = false;
/*      */ 
/*      */   
/*      */   private transient JmqiMQ mq;
/*      */ 
/*      */   
/*      */   private transient JmqiSP mqSPI;
/*      */ 
/*      */   
/* 1227 */   private transient Pint rc = null;
/*      */ 
/*      */   
/* 1230 */   private WMQSession session = null;
/*      */ 
/*      */   
/* 1233 */   private int sendCount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1244 */   private transient ProducerShadow shadow = null;
/*      */   
/* 1246 */   protected long lastSendTime = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   WMQMessageProducer(WMQSession session, WMQDestination destination, JmsPropertyContext propertyContext) throws JMSException {
/* 1260 */     super(propertyContext);
/* 1261 */     if (Trace.isOn) {
/* 1262 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "<init>(WMQSession,WMQDestination,JmsPropertyContext)", new Object[] { session, destination, propertyContext });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1268 */     Trace.registerFFSTObject(this);
/*      */     
/* 1270 */     this.session = session;
/* 1271 */     ensureNonNull("ctor", "Producer's session", this.session);
/*      */     
/* 1273 */     this.env = session.getJmqiEnvironment();
/* 1274 */     ensureNonNull("ctor", "Session's JmqiEnvironment", this.env);
/*      */     
/* 1276 */     this.hconn = session.getHconn();
/* 1277 */     ensureNonNull("ctor", "Session's Hconn", this.hconn);
/*      */     
/* 1279 */     this.mq = session.getJmqiMQ();
/* 1280 */     this.mqSPI = (JmqiSP)this.mq;
/* 1281 */     ensureNonNull("ctor", "Session's JmqiMQ", this.mq);
/*      */     
/* 1283 */     this.cc = this.env.newPint();
/* 1284 */     this.rc = this.env.newPint();
/*      */     
/* 1286 */     this.shadow = getShadowInstance(destination);
/*      */     
/* 1288 */     this.shadow.initialise();
/*      */     
/* 1290 */     if (Trace.isOn) {
/* 1291 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "<init>(WMQSession,WMQDestination,JmsPropertyContext)");
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
/*      */   private void checkJmqiCallSuccess(String messageid, WMQDestination destination, String probeid) throws JMSException {
/* 1308 */     if (Trace.isOn) {
/* 1309 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "checkJmqiCallSuccess(String,WMQDestination,String)", new Object[] { messageid, destination, probeid });
/*      */     }
/*      */ 
/*      */     
/* 1313 */     checkJmqiCallSuccess(messageid, destination, probeid, this.cc, this.rc);
/* 1314 */     if (Trace.isOn) {
/* 1315 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "checkJmqiCallSuccess(String,WMQDestination,String)");
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
/*      */   private void checkJmqiCallSuccess(String messageid, WMQDestination destination, String probeid, Pint completionCode, Pint reasonCode) throws JMSException {
/* 1334 */     if (Trace.isOn) {
/* 1335 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "checkJmqiCallSuccess(String,WMQDestination,String,Pint,Pint)", new Object[] { messageid, destination, probeid, completionCode, reasonCode });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1340 */     if (reasonCode.x != 0 || completionCode.x != 0) {
/* 1341 */       if (Reason.isImpossibleReason(reasonCode.x, completionCode.x, this.mqSPI)) {
/* 1342 */         HashMap<String, Object> info = new HashMap<>();
/* 1343 */         info.put("messageid", messageid);
/* 1344 */         info.put("destination name", destination.getName());
/* 1345 */         info.put("reason", reasonCode);
/* 1346 */         info.put("compcode", completionCode);
/* 1347 */         info.put("hconn", this.hconn);
/* 1348 */         Trace.ffst(this, "checkJmqiCallSuccess", probeid, info, JMSException.class);
/*      */       } 
/* 1350 */       if (Reason.isConnectionBroken(reasonCode.x)) {
/* 1351 */         JMSException e = Reason.createException("JMSWMQ1107", null, reasonCode.x, completionCode.x, this.env);
/*      */         
/* 1353 */         ((WMQConnection)this.session.getConnection()).driveExceptionListener(e, true);
/*      */       } 
/*      */       
/* 1356 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1357 */       inserts.put("XMSC_DESTINATION_NAME", destination.getName());
/* 1358 */       JMSException je = Reason.createException(messageid, inserts, reasonCode.x, completionCode.x, this.env);
/* 1359 */       if (Trace.isOn) {
/* 1360 */         Trace.throwing(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "checkJmqiCallSuccess(String,WMQDestination,String,Pint,Pint)", (Throwable)je);
/*      */       }
/*      */       
/* 1363 */       throw je;
/*      */     } 
/* 1365 */     if (Trace.isOn) {
/* 1366 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "checkJmqiCallSuccess(String,WMQDestination,String,Pint,Pint)");
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
/*      */   public void close(boolean closingFromSession) throws JMSException {
/* 1380 */     if (Trace.isOn) {
/* 1381 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "close(boolean)", new Object[] {
/* 1382 */             Boolean.valueOf(closingFromSession)
/*      */           });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1388 */       if (this.isClosed) {
/* 1389 */         if (Trace.isOn) {
/* 1390 */           Trace.data(this, "close()", "Producer already closed. Early return.", null);
/*      */         }
/* 1392 */         if (Trace.isOn) {
/* 1393 */           Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "close(boolean)", 1);
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/* 1398 */       this.shadow.close();
/* 1399 */       this.isClosed = true;
/*      */     } finally {
/* 1401 */       if (Trace.isOn) {
/* 1402 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "close(boolean)");
/*      */       }
/*      */ 
/*      */       
/* 1406 */       Trace.deRegisterFFSTObject(this);
/*      */     } 
/*      */     
/* 1409 */     if (Trace.isOn) {
/* 1410 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "close(boolean)", 2);
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
/*      */   private void ensureNonNull(String methodName, String name, Object object) throws JMSException {
/* 1425 */     if (object == null) {
/* 1426 */       HashMap<String, Object> info = new HashMap<>();
/* 1427 */       info.put("methodName", methodName);
/* 1428 */       info.put("object", name);
/* 1429 */       info.put("message", "Object is null");
/* 1430 */       Trace.ffst(this, "ensureNonNull(String,String,Object)", "XN005001", info, JMSException.class);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected WMQSession getSession() {
/* 1438 */     if (Trace.isOn) {
/* 1439 */       Trace.data(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "getSession()", "getter", this.session);
/*      */     }
/* 1441 */     return this.session;
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
/*      */   private ProducerShadow getShadowInstance(WMQDestination destination) {
/* 1456 */     ProducerShadow generatedShadow = null;
/*      */     
/* 1458 */     boolean unidentified = (destination == null);
/*      */     
/* 1460 */     if (unidentified) {
/* 1461 */       generatedShadow = new SpiUnidentifiedProducerShadow(destination);
/* 1462 */     } else if (destination.getName().endsWith("SERVICE1.DUMP")) {
/* 1463 */       generatedShadow = new zOSDumpQmgrShadow(destination);
/* 1464 */     } else if (destination.getName().endsWith("SERVICE2.DUMP")) {
/* 1465 */       generatedShadow = new zOSDumpQmgrShadow(destination, true);
/*      */     } else {
/* 1467 */       generatedShadow = new SpiIdentifiedProducerShadow(destination);
/*      */     } 
/* 1469 */     return generatedShadow;
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
/*      */   public void send(ProviderDestination destAtSendCall, ProviderMessage message) throws JMSException {
/* 1482 */     if (Trace.isOn) {
/* 1483 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "send(ProviderDestination,ProviderMessage)", new Object[] { destAtSendCall, message });
/*      */     }
/*      */ 
/*      */     
/* 1487 */     this.shadow.send((WMQDestination)destAtSendCall, (WMQMessage)message);
/* 1488 */     this.sendCount++;
/* 1489 */     this.lastSendTime = System.currentTimeMillis();
/*      */     
/* 1491 */     if (Trace.isOn) {
/* 1492 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "send(ProviderDestination,ProviderMessage)");
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
/*      */   public String toString() {
/* 1504 */     if (Trace.isOn) {
/* 1505 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "toString()");
/*      */     }
/* 1507 */     TableBuilder builder = new TableBuilder();
/* 1508 */     builder.append("Instance", super.toString());
/* 1509 */     if (this.session == null) {
/* 1510 */       builder.append("Parent Session", "<null>");
/*      */     } else {
/* 1512 */       builder.append("Parent Session", this.session
/* 1513 */           .getClass().getName() + '@' + Integer.toHexString(this.session.hashCode()));
/*      */     } 
/* 1515 */     builder.append("shadow", this.shadow);
/* 1516 */     builder.append("isClosed", Boolean.valueOf(this.isClosed));
/*      */     
/* 1518 */     String traceRet1 = builder.toString();
/* 1519 */     if (Trace.isOn) {
/* 1520 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "toString()", traceRet1);
/*      */     }
/* 1522 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dump(PrintWriter pw, int level) {
/* 1531 */     if (Trace.isOn) {
/* 1532 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "dump(PrintWriter,int)", new Object[] { pw, 
/* 1533 */             Integer.valueOf(level) });
/*      */     }
/* 1535 */     String prefix = Trace.buildPrefix(level);
/* 1536 */     pw.format("%s%s%n", new Object[] { prefix, super.toString() });
/* 1537 */     if (this.creator != null) {
/* 1538 */       pw.format("%s  created by \"%s\" (id: %d) @ %s - stack :%n", new Object[] { prefix, this.creator
/* 1539 */             .getName(), Long.valueOf(this.creator.getId()), Trace.formatTimeStamp(this.createTime) });
/* 1540 */       Trace.dumpStack(pw, level + 1, this.createStack);
/*      */     } 
/* 1542 */     if (this.session == null) {
/* 1543 */       pw.format("%s  Parent Session <null>%n", new Object[] { prefix });
/*      */     } else {
/* 1545 */       pw.format("%s  Parent Session %s@%x%n", new Object[] { prefix, this.session.getClass().getName(), Integer.valueOf(this.session.hashCode()) });
/*      */     } 
/*      */     
/* 1548 */     pw.format("%s  shadow %s%n", new Object[] { prefix, String.valueOf(this.shadow) });
/* 1549 */     pw.format("%s  isClosed %b%n", new Object[] { prefix, Boolean.valueOf(this.isClosed) });
/* 1550 */     pw.format("%s  sent %d message(s)%n", new Object[] { prefix, Integer.valueOf(this.sendCount) });
/* 1551 */     pw.format("%s  last message sent @ %s%n", new Object[] { prefix, Trace.formatTimeStamp(this.lastSendTime) });
/* 1552 */     if (this.shadow.destination == null) {
/* 1553 */       pw.format("%s  Shadow destination is null!%n", new Object[] { prefix });
/*      */     } else {
/* 1555 */       this.shadow.destination.dump(pw, level + 1);
/*      */     } 
/* 1557 */     if (Trace.isOn) {
/* 1558 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "dump(PrintWriter,int)");
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
/*      */   public boolean providerPriorityValidate(int priority) {
/* 1571 */     if (Trace.isOn) {
/* 1572 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "providerPriorityValidate(int)", new Object[] {
/* 1573 */             Integer.valueOf(priority)
/*      */           });
/*      */     }
/* 1576 */     PropertyStore.register("com.ibm.mq.jms.SupportMQExtensions", false);
/*      */     
/* 1578 */     boolean supportMQExtensions = PropertyStore.getBooleanPropertyObject("com.ibm.mq.jms.SupportMQExtensions").booleanValue();
/*      */ 
/*      */     
/* 1581 */     if (supportMQExtensions && -1 == priority) {
/* 1582 */       if (Trace.isOn) {
/* 1583 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "providerPriorityValidate(int)", 
/* 1584 */             Boolean.valueOf(true), 1);
/*      */       }
/* 1586 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1591 */     if (Trace.isOn) {
/* 1592 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "providerPriorityValidate(int)", 
/* 1593 */           Boolean.valueOf(false), 2);
/*      */     }
/* 1595 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean providerTimeToLiveValidate(long timeToLive) {
/* 1606 */     if (Trace.isOn) {
/* 1607 */       Trace.entry(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "providerTimeToLiveValidate(long)", new Object[] {
/* 1608 */             Long.valueOf(timeToLive)
/*      */           });
/*      */     }
/* 1611 */     PropertyStore.register("com.ibm.mq.jms.SupportMQExtensions", false);
/*      */     
/* 1613 */     boolean supportMQExtensions = PropertyStore.getBooleanPropertyObject("com.ibm.mq.jms.SupportMQExtensions").booleanValue();
/*      */     
/* 1615 */     if (supportMQExtensions && -2L == timeToLive) {
/* 1616 */       if (Trace.isOn) {
/* 1617 */         Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "providerTimeToLiveValidate(long)", 
/* 1618 */             Boolean.valueOf(true), 1);
/*      */       }
/* 1620 */       return true;
/*      */     } 
/* 1622 */     if (Trace.isOn) {
/* 1623 */       Trace.exit(this, "com.ibm.msg.client.wmq.internal.WMQMessageProducer", "providerTimeToLiveValidate(long)", 
/* 1624 */           Boolean.valueOf(false), 2);
/*      */     }
/* 1626 */     return false;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\internal\WMQMessageProducer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */