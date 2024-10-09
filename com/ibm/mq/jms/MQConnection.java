/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsConnection;
/*     */ import com.ibm.msg.client.jms.JmsConnectionBrowser;
/*     */ import com.ibm.msg.client.jms.JmsConnectionConsumer;
/*     */ import com.ibm.msg.client.jms.JmsConnectionMetaData;
/*     */ import com.ibm.msg.client.jms.JmsDestination;
/*     */ import com.ibm.msg.client.jms.JmsMessageReferenceHandler;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.jms.JmsQueue;
/*     */ import com.ibm.msg.client.jms.JmsSession;
/*     */ import com.ibm.msg.client.jms.JmsTopic;
/*     */ import javax.jms.Connection;
/*     */ import javax.jms.ConnectionConsumer;
/*     */ import javax.jms.ConnectionMetaData;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.ExceptionListener;
/*     */ import javax.jms.IllegalStateException;
/*     */ import javax.jms.InvalidDestinationException;
/*     */ import javax.jms.InvalidSelectorException;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Queue;
/*     */ import javax.jms.ServerSessionPool;
/*     */ import javax.jms.Session;
/*     */ import javax.jms.Topic;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQConnection
/*     */   extends MQRoot
/*     */   implements Connection, JmsConnection
/*     */ {
/*     */   static final long serialVersionUID = 1357803352856448348L;
/*     */   protected JmsConnection commonConn;
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.mq.jms.MQConnection", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQConnection.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MQConnection() {
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "<init>()");
/*     */     }
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws JMSException {
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "close()");
/*     */     }
/*  90 */     this.commonConn.close();
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "close()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionBrowser createConnectionBrowser(Queue queue, String selector, MessageReferenceHandler mrh, int quantityHint) throws JMSException {
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "createConnectionBrowser(Queue,String,MessageReferenceHandler,int)", new Object[] { queue, selector, mrh, 
/*     */             
/* 113 */             Integer.valueOf(quantityHint) });
/*     */     }
/*     */     
/* 116 */     JmsQueue jmsQueue = (queue == null) ? null : (JmsQueue)((MQQueue)queue).validateDestination();
/*     */ 
/*     */ 
/*     */     
/* 120 */     MQMessageReferenceHandler mqMessageReferenceHandler = null;
/* 121 */     if (mrh != null) {
/* 122 */       mqMessageReferenceHandler = new MQMessageReferenceHandler(mrh);
/*     */     }
/*     */     
/* 125 */     JmsConnectionBrowser jmsConnectionBrowser = this.commonConn.createConnectionBrowser((JmsDestination)jmsQueue, selector, mqMessageReferenceHandler, quantityHint);
/* 126 */     MQConnectionBrowser wrapper = new MQConnectionBrowser();
/* 127 */     wrapper.setDelegate(jmsConnectionBrowser);
/*     */     
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "createConnectionBrowser(Queue,String,MessageReferenceHandler,int)", wrapper);
/*     */     }
/*     */     
/* 133 */     return wrapper;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionBrowser createConnectionBrowser(Topic topic, String selector, MessageReferenceHandler mrh, int quantityHint) throws JMSException {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "createConnectionBrowser(Topic,String,MessageReferenceHandler,int)", new Object[] { topic, selector, mrh, 
/*     */             
/* 151 */             Integer.valueOf(quantityHint) });
/*     */     }
/*     */     
/* 154 */     JmsTopic jmsTopic = (topic == null) ? null : (JmsTopic)((MQTopic)topic).validateDestination();
/*     */ 
/*     */ 
/*     */     
/* 158 */     MQMessageReferenceHandler mqMessageReferenceHandler = null;
/* 159 */     if (mrh != null) {
/* 160 */       mqMessageReferenceHandler = new MQMessageReferenceHandler(mrh);
/*     */     }
/*     */     
/* 163 */     JmsConnectionBrowser jmsConnectionBrowser = this.commonConn.createConnectionBrowser((JmsDestination)jmsTopic, selector, mqMessageReferenceHandler, quantityHint);
/* 164 */     MQConnectionBrowser wrapper = new MQConnectionBrowser();
/* 165 */     wrapper.setDelegate(jmsConnectionBrowser);
/*     */     
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "createConnectionBrowser(Topic,String,MessageReferenceHandler,int)", wrapper);
/*     */     }
/*     */     
/* 171 */     return wrapper;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionConsumer createConnectionConsumer(Destination destination, String selector, ServerSessionPool ssp, int maxMessages) throws JMSException {
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "createConnectionConsumer(Destination,String,ServerSessionPool,int)", new Object[] { destination, selector, ssp, 
/*     */             
/* 199 */             Integer.valueOf(maxMessages) });
/*     */     }
/*     */     
/* 202 */     JmsDestination dest = (destination == null) ? null : (JmsDestination)((MQDestination)destination).validateDestination();
/* 203 */     MQConnectionConsumer wrapper = new MQConnectionConsumer();
/*     */     
/* 205 */     ConnectionConsumer cc = this.commonConn.createConnectionConsumer((Destination)dest, selector, ssp, maxMessages);
/* 206 */     wrapper.setDelegate((JmsConnectionConsumer)cc);
/*     */     
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "createConnectionConsumer(Destination,String,ServerSessionPool,int)", wrapper);
/*     */     }
/*     */     
/* 212 */     return wrapper;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionBrowser createDurableConnectionBrowser(Topic topic, String subName, String selector, MessageReferenceHandler mrh, int quantityHint, boolean noLocal) throws JMSException {
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "createDurableConnectionBrowser(Topic,String,String,MessageReferenceHandler,int,boolean)", new Object[] { topic, subName, selector, mrh, 
/*     */             
/* 240 */             Integer.valueOf(quantityHint), 
/* 241 */             Boolean.valueOf(noLocal) });
/*     */     }
/*     */     
/* 244 */     JmsTopic jmsTopic = (topic == null) ? null : (JmsTopic)((MQTopic)topic).validateDestination();
/*     */ 
/*     */ 
/*     */     
/* 248 */     MQMessageReferenceHandler mqMessageReferenceHandler = null;
/* 249 */     if (mrh != null) {
/* 250 */       mqMessageReferenceHandler = new MQMessageReferenceHandler(mrh);
/*     */     }
/*     */     
/* 253 */     JmsConnectionBrowser jmsConnectionBrowser = this.commonConn.createDurableConnectionBrowser(jmsTopic, subName, selector, mqMessageReferenceHandler, quantityHint, noLocal);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 259 */     MQConnectionBrowser wrapper = new MQConnectionBrowser();
/* 260 */     wrapper.setDelegate(jmsConnectionBrowser);
/*     */     
/* 262 */     if (Trace.isOn) {
/* 263 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "createDurableConnectionBrowser(Topic,String,String,MessageReferenceHandler,int,boolean)", wrapper);
/*     */     }
/*     */ 
/*     */     
/* 267 */     return wrapper;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionConsumer createDurableConnectionConsumer(Topic topic, String subName, String selector, ServerSessionPool ssp, int maxMessageCount) throws JMSException {
/* 295 */     if (Trace.isOn) {
/* 296 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "createDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", new Object[] { topic, subName, selector, ssp, 
/*     */             
/* 298 */             Integer.valueOf(maxMessageCount) });
/*     */     }
/* 300 */     JmsTopic jmsTopic = (topic == null) ? null : (JmsTopic)((MQTopic)topic).validateDestination();
/* 301 */     MQConnectionConsumer wrapper = new MQConnectionConsumer();
/*     */     
/* 303 */     ConnectionConsumer cc = this.commonConn.createDurableConnectionConsumer((Topic)jmsTopic, subName, selector, ssp, maxMessageCount);
/* 304 */     wrapper.setDelegate((JmsConnectionConsumer)cc);
/*     */     
/* 306 */     if (Trace.isOn) {
/* 307 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "createDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", wrapper);
/*     */     }
/*     */     
/* 310 */     return wrapper;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Session createSession(boolean transacted, int acknowledgeMode) throws JMSException {
/* 335 */     if (Trace.isOn)
/* 336 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "createSession(boolean,int)", new Object[] {
/* 337 */             Boolean.valueOf(transacted), Integer.valueOf(acknowledgeMode)
/*     */           }); 
/* 339 */     MQSession wrapper = new MQSession();
/* 340 */     wrapper.setDelegate((JmsSession)this.commonConn.createSession(transacted, acknowledgeMode));
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "createSession(boolean,int)", wrapper);
/*     */     }
/* 344 */     return wrapper;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClientID() throws JMSException {
/* 357 */     String traceRet1 = this.commonConn.getClientID();
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.data(this, "com.ibm.mq.jms.MQConnection", "getClientID()", "getter", traceRet1);
/*     */     }
/* 361 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExceptionListener getExceptionListener() throws JMSException {
/* 375 */     ExceptionListener traceRet1 = this.commonConn.getExceptionListener();
/* 376 */     if (Trace.isOn) {
/* 377 */       Trace.data(this, "com.ibm.mq.jms.MQConnection", "getExceptionListener()", "getter", traceRet1);
/*     */     }
/*     */     
/* 380 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionMetaData getMetaData() throws JMSException {
/* 394 */     MQConnectionMetaData metaData = new MQConnectionMetaData((JmsConnectionMetaData)this.commonConn.getMetaData());
/*     */     
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.data(this, "com.ibm.mq.jms.MQConnection", "getMetaData()", "getter", metaData);
/*     */     }
/* 399 */     return metaData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClientID(String clientID) throws JMSException {
/* 414 */     if (Trace.isOn) {
/* 415 */       Trace.data(this, "com.ibm.mq.jms.MQConnection", "setClientID(String)", "setter", clientID);
/*     */     }
/* 417 */     this.commonConn.setClientID(clientID);
/*     */   }
/*     */ 
/*     */   
/*     */   void setDelegate(JmsConnection commonConn) {
/* 422 */     if (Trace.isOn) {
/* 423 */       Trace.data(this, "com.ibm.mq.jms.MQConnection", "setDelegate(JmsConnection)", "setter", commonConn);
/*     */     }
/*     */     
/* 426 */     this.commonConn = commonConn;
/* 427 */     this.delegate = (JmsPropertyContext)commonConn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExceptionListener(ExceptionListener listener) throws JMSException {
/* 442 */     if (Trace.isOn) {
/* 443 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "setExceptionListener(ExceptionListener)", new Object[] { listener });
/*     */     }
/*     */     
/* 446 */     this.commonConn.setExceptionListener(listener);
/*     */     
/* 448 */     if (Trace.isOn) {
/* 449 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "setExceptionListener(ExceptionListener)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() throws JMSException {
/* 461 */     if (Trace.isOn) {
/* 462 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "start()");
/*     */     }
/* 464 */     this.commonConn.start();
/* 465 */     if (Trace.isOn) {
/* 466 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "start()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() throws JMSException {
/* 484 */     if (Trace.isOn) {
/* 485 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "stop()");
/*     */     }
/* 487 */     this.commonConn.stop();
/* 488 */     if (Trace.isOn) {
/* 489 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "stop()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 516 */     return String.valueOf(this.commonConn.toJson());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsConnectionBrowser createConnectionBrowser(JmsDestination destination, String selector, JmsMessageReferenceHandler messageRefHandler, int quantityHint) throws JMSException {
/* 535 */     if (Trace.isOn) {
/* 536 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "createConnectionBrowser(JmsDestination,String,JmsMessageReferenceHandler,int)", new Object[] { destination, selector, messageRefHandler, 
/*     */             
/* 538 */             Integer.valueOf(quantityHint) });
/*     */     }
/*     */     
/* 541 */     JmsConnectionBrowser jmsConnectionBrowser = this.commonConn.createConnectionBrowser(destination, selector, messageRefHandler, quantityHint);
/*     */     
/* 543 */     if (Trace.isOn) {
/* 544 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "createConnectionBrowser(JmsDestination,String,JmsMessageReferenceHandler,int)", jmsConnectionBrowser);
/*     */     }
/*     */ 
/*     */     
/* 548 */     return jmsConnectionBrowser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsConnectionBrowser createDurableConnectionBrowser(JmsTopic topic, String subName, String selector, JmsMessageReferenceHandler messageRefHandler, int quantityHint, boolean noLocal) throws JMSException {
/* 574 */     if (Trace.isOn) {
/* 575 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "createDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", new Object[] { topic, subName, selector, messageRefHandler, 
/*     */             
/* 577 */             Integer.valueOf(quantityHint), 
/* 578 */             Boolean.valueOf(noLocal) });
/*     */     }
/* 580 */     JmsConnectionBrowser jmsConnectionBrowser = this.commonConn.createDurableConnectionBrowser(topic, subName, selector, messageRefHandler, quantityHint, noLocal);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 587 */     if (Trace.isOn) {
/* 588 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "createDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", jmsConnectionBrowser);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 594 */     return jmsConnectionBrowser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int getReceiveIsolation() throws JMSException {
/* 606 */     int traceRet1 = getIntProperty("XMSC_WMQ_RECEIVE_ISOLATION");
/* 607 */     if (Trace.isOn) {
/* 608 */       Trace.data(this, "com.ibm.mq.jms.MQConnection", "getReceiveIsolation()", "getter", 
/* 609 */           Integer.valueOf(traceRet1));
/*     */     }
/* 611 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean getOutcomeNotification() throws JMSException {
/* 623 */     boolean traceRet1 = getBooleanProperty("XMSC_WMQ_OUTCOME_NOTIFICATION");
/* 624 */     if (Trace.isOn) {
/* 625 */       Trace.data(this, "com.ibm.mq.jms.MQConnection", "getOutcomeNotification()", "getter", 
/* 626 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 628 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int getProcessDuration() throws JMSException {
/* 640 */     int traceRet1 = getIntProperty("XMSC_WMQ_PROCESS_DURATION");
/* 641 */     if (Trace.isOn) {
/* 642 */       Trace.data(this, "com.ibm.mq.jms.MQConnection", "getProcessDuration()", "getter", 
/* 643 */           Integer.valueOf(traceRet1));
/*     */     }
/* 645 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean getOptimisticPublication() throws JMSException {
/* 656 */     boolean traceRet1 = getBooleanProperty("XMSC_WMQ_OPT_PUB");
/* 657 */     if (Trace.isOn) {
/* 658 */       Trace.data(this, "com.ibm.mq.jms.MQConnection", "getOptimisticPublication()", "getter", 
/* 659 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 661 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Session createSession() throws JMSException {
/* 681 */     if (Trace.isOn) {
/* 682 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "createSession()");
/*     */     }
/* 684 */     MQSession wrapper = new MQSession();
/* 685 */     wrapper.setDelegate((JmsSession)this.commonConn.createSession());
/* 686 */     if (Trace.isOn) {
/* 687 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "createSession()", wrapper);
/*     */     }
/* 689 */     return wrapper;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Session createSession(int sessionMode) throws JMSException {
/* 714 */     if (Trace.isOn)
/* 715 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "createSession(int)", new Object[] {
/* 716 */             Integer.valueOf(sessionMode)
/*     */           }); 
/* 718 */     MQSession wrapper = new MQSession();
/* 719 */     wrapper.setDelegate((JmsSession)this.commonConn.createSession(sessionMode));
/* 720 */     if (Trace.isOn) {
/* 721 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "createSession(int)", wrapper);
/*     */     }
/* 723 */     return wrapper;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionConsumer createSharedConnectionConsumer(Topic topic, String subscriptionName, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws IllegalStateException, InvalidDestinationException, InvalidSelectorException, JMSException {
/* 737 */     if (Trace.isOn) {
/* 738 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "createSharedConnectionConsumer(Topic,String,String,ServerSessionPool,int)", new Object[] { topic, subscriptionName, messageSelector, sessionPool, 
/*     */             
/* 740 */             Integer.valueOf(maxMessages) });
/*     */     }
/*     */     
/* 743 */     JmsTopic dest = (topic == null) ? null : (JmsTopic)((MQTopic)topic).validateDestination();
/* 744 */     MQConnectionConsumer wrapper = new MQConnectionConsumer();
/*     */     
/* 746 */     ConnectionConsumer cc = this.commonConn.createSharedConnectionConsumer((Topic)dest, subscriptionName, messageSelector, sessionPool, maxMessages);
/* 747 */     wrapper.setDelegate((JmsConnectionConsumer)cc);
/*     */     
/* 749 */     if (Trace.isOn) {
/* 750 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "createSharedConnectionConsumer(Topic,String,String,ServerSessionPool,int)", wrapper);
/*     */     }
/*     */     
/* 753 */     return wrapper;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionConsumer createSharedDurableConnectionConsumer(Topic topic, String subName, String selector, ServerSessionPool sessionPool, int maxMessages) throws IllegalStateException, InvalidDestinationException, InvalidSelectorException, JMSException {
/* 767 */     if (Trace.isOn) {
/* 768 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "createSharedDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", new Object[] { topic, subName, selector, sessionPool, 
/*     */             
/* 770 */             Integer.valueOf(maxMessages) });
/*     */     }
/*     */     
/* 773 */     JmsTopic jmsTopic = (topic == null) ? null : (JmsTopic)((MQTopic)topic).validateDestination();
/* 774 */     MQConnectionConsumer wrapper = new MQConnectionConsumer();
/*     */     
/* 776 */     ConnectionConsumer cc = this.commonConn.createSharedDurableConnectionConsumer((Topic)jmsTopic, subName, selector, sessionPool, maxMessages);
/* 777 */     wrapper.setDelegate((JmsConnectionConsumer)cc);
/*     */     
/* 779 */     if (Trace.isOn) {
/* 780 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "createSharedDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", wrapper);
/*     */     }
/*     */     
/* 783 */     return wrapper;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionBrowser createSharedConnectionBrowser(Topic topic, String subName, String selector, MessageReferenceHandler mrh, int quantityHint, boolean noLocal) throws JMSException {
/* 809 */     if (Trace.isOn) {
/* 810 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "createSharedConnectionBrowser(Topic,String,String,MessageReferenceHandler,int,boolean)", new Object[] { topic, subName, selector, mrh, 
/*     */             
/* 812 */             Integer.valueOf(quantityHint), 
/* 813 */             Boolean.valueOf(noLocal) });
/*     */     }
/*     */     
/* 816 */     JmsTopic jmsTopic = (topic == null) ? null : (JmsTopic)((MQTopic)topic).validateDestination();
/*     */ 
/*     */ 
/*     */     
/* 820 */     MQMessageReferenceHandler mqMessageReferenceHandler = null;
/* 821 */     if (mrh != null) {
/* 822 */       mqMessageReferenceHandler = new MQMessageReferenceHandler(mrh);
/*     */     }
/*     */     
/* 825 */     JmsConnectionBrowser jmsConnectionBrowser = this.commonConn.createSharedConnectionBrowser(jmsTopic, subName, selector, mqMessageReferenceHandler, quantityHint, noLocal);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 831 */     MQConnectionBrowser wrapper = new MQConnectionBrowser();
/* 832 */     wrapper.setDelegate(jmsConnectionBrowser);
/*     */     
/* 834 */     if (Trace.isOn) {
/* 835 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "createSharedConnectionBrowser(Topic,String,String,MessageReferenceHandler,int,boolean)", wrapper);
/*     */     }
/*     */ 
/*     */     
/* 839 */     return wrapper;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConnectionBrowser createSharedDurableConnectionBrowser(Topic topic, String subName, String selector, MessageReferenceHandler mrh, int quantityHint, boolean noLocal) throws JMSException {
/* 864 */     if (Trace.isOn) {
/* 865 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "createSharedDurableConnectionBrowser(Topic,String,String,MessageReferenceHandler,int,boolean)", new Object[] { topic, subName, selector, mrh, 
/*     */             
/* 867 */             Integer.valueOf(quantityHint), 
/* 868 */             Boolean.valueOf(noLocal) });
/*     */     }
/*     */     
/* 871 */     JmsTopic jmsTopic = (topic == null) ? null : (JmsTopic)((MQTopic)topic).validateDestination();
/*     */ 
/*     */ 
/*     */     
/* 875 */     MQMessageReferenceHandler mqMessageReferenceHandler = null;
/* 876 */     if (mrh != null) {
/* 877 */       mqMessageReferenceHandler = new MQMessageReferenceHandler(mrh);
/*     */     }
/*     */     
/* 880 */     JmsConnectionBrowser jmsConnectionBrowser = this.commonConn.createSharedDurableConnectionBrowser(jmsTopic, subName, selector, mqMessageReferenceHandler, quantityHint, noLocal);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 886 */     MQConnectionBrowser wrapper = new MQConnectionBrowser();
/* 887 */     wrapper.setDelegate(jmsConnectionBrowser);
/*     */     
/* 889 */     if (Trace.isOn) {
/* 890 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "createSharedDurableConnectionBrowser(Topic,String,String,MessageReferenceHandler,int,boolean)", wrapper);
/*     */     }
/*     */ 
/*     */     
/* 894 */     return wrapper;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsConnectionBrowser createSharedConnectionBrowser(JmsTopic topic, String subName, String selector, JmsMessageReferenceHandler messageRefHandler, int quantityHint, boolean noLocal) throws JMSException {
/* 920 */     if (Trace.isOn) {
/* 921 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "createSharedConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", new Object[] { topic, subName, selector, messageRefHandler, 
/*     */             
/* 923 */             Integer.valueOf(quantityHint), 
/* 924 */             Boolean.valueOf(noLocal) });
/*     */     }
/* 926 */     JmsConnectionBrowser jmsConnectionBrowser = this.commonConn.createSharedConnectionBrowser(topic, subName, selector, messageRefHandler, quantityHint, noLocal);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 933 */     if (Trace.isOn) {
/* 934 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "createSharedConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", jmsConnectionBrowser);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 940 */     return jmsConnectionBrowser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsConnectionBrowser createSharedDurableConnectionBrowser(JmsTopic topic, String subName, String selector, JmsMessageReferenceHandler messageRefHandler, int quantityHint, boolean noLocal) throws JMSException {
/* 966 */     if (Trace.isOn) {
/* 967 */       Trace.entry(this, "com.ibm.mq.jms.MQConnection", "createSharedDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", new Object[] { topic, subName, selector, messageRefHandler, 
/*     */             
/* 969 */             Integer.valueOf(quantityHint), 
/* 970 */             Boolean.valueOf(noLocal) });
/*     */     }
/* 972 */     JmsConnectionBrowser jmsConnectionBrowser = this.commonConn.createSharedDurableConnectionBrowser(topic, subName, selector, messageRefHandler, quantityHint, noLocal);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 979 */     if (Trace.isOn) {
/* 980 */       Trace.exit(this, "com.ibm.mq.jms.MQConnection", "createSharedDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", jmsConnectionBrowser);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 986 */     return jmsConnectionBrowser;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */