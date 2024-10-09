/*      */ package com.ibm.mq.jms;
/*      */ 
/*      */ import com.ibm.jms.JMSBytesMessage;
/*      */ import com.ibm.jms.JMSMapMessage;
/*      */ import com.ibm.jms.JMSMessage;
/*      */ import com.ibm.jms.JMSObjectMessage;
/*      */ import com.ibm.jms.JMSStreamMessage;
/*      */ import com.ibm.jms.JMSTextMessage;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsDestination;
/*      */ import com.ibm.msg.client.jms.JmsMessageConsumer;
/*      */ import com.ibm.msg.client.jms.JmsMessageProducer;
/*      */ import com.ibm.msg.client.jms.JmsMessageReference;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import com.ibm.msg.client.jms.JmsQueue;
/*      */ import com.ibm.msg.client.jms.JmsQueueBrowser;
/*      */ import com.ibm.msg.client.jms.JmsSession;
/*      */ import com.ibm.msg.client.jms.JmsTopic;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import javax.jms.BytesMessage;
/*      */ import javax.jms.Destination;
/*      */ import javax.jms.IllegalStateException;
/*      */ import javax.jms.InvalidDestinationException;
/*      */ import javax.jms.InvalidSelectorException;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.MapMessage;
/*      */ import javax.jms.Message;
/*      */ import javax.jms.MessageConsumer;
/*      */ import javax.jms.MessageListener;
/*      */ import javax.jms.MessageProducer;
/*      */ import javax.jms.ObjectMessage;
/*      */ import javax.jms.Queue;
/*      */ import javax.jms.QueueBrowser;
/*      */ import javax.jms.Session;
/*      */ import javax.jms.StreamMessage;
/*      */ import javax.jms.TemporaryQueue;
/*      */ import javax.jms.TemporaryTopic;
/*      */ import javax.jms.TextMessage;
/*      */ import javax.jms.Topic;
/*      */ import javax.jms.TopicSubscriber;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQSession
/*      */   extends MQRoot
/*      */   implements Session, JmsSession
/*      */ {
/*      */   private static final long serialVersionUID = 1136989430032568493L;
/*      */   int ackMode;
/*      */   protected JmsSession commonSess;
/*      */   boolean transacted;
/*      */   
/*      */   static {
/*   82 */     if (Trace.isOn) {
/*   83 */       Trace.data("com.ibm.mq.jms.MQSession", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQSession.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class FacadeMessageListener
/*      */     implements MessageListener
/*      */   {
/*   95 */     private MessageListener listener = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public FacadeMessageListener(MessageListener listener) {
/*  103 */       if (Trace.isOn) {
/*  104 */         Trace.entry(this, "com.ibm.mq.jms.FacadeMessageListener", "<init>(MessageListener)", new Object[] { listener });
/*      */       }
/*      */       
/*  107 */       this.listener = listener;
/*  108 */       if (Trace.isOn) {
/*  109 */         Trace.exit(this, "com.ibm.mq.jms.FacadeMessageListener", "<init>(MessageListener)");
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
/*      */     public void onMessage(Message message) {
/*  122 */       if (Trace.isOn) {
/*  123 */         Trace.entry(this, "com.ibm.mq.jms.FacadeMessageListener", "onMessage(Message)", new Object[] { message });
/*      */       }
/*      */ 
/*      */       
/*  127 */       JMSMessage wrapper = MQSession.MessageProxy.wrapMessage(message);
/*      */       
/*  129 */       this.listener.onMessage((Message)wrapper);
/*      */       
/*  131 */       if (Trace.isOn) {
/*  132 */         Trace.exit(this, "com.ibm.mq.jms.FacadeMessageListener", "onMessage(Message)");
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
/*      */   protected static class MessageProxy
/*      */     extends JMSMessage
/*      */   {
/*      */     protected static JMSMessage wrapMessage(Message message) {
/*  148 */       if (Trace.isOn) {
/*  149 */         Trace.entry("com.ibm.mq.jms.MessageProxy", "wrapMessage(Message)", new Object[] { message });
/*      */       }
/*  151 */       JMSMessage wrapper = null;
/*  152 */       if (message instanceof BytesMessage) {
/*  153 */         JMSBytesMessage jMSBytesMessage = proxyCreateBytesMessage(message);
/*      */       }
/*  155 */       else if (message instanceof MapMessage) {
/*  156 */         JMSMapMessage jMSMapMessage = proxyCreateMapMessage(message);
/*      */       }
/*  158 */       else if (message instanceof StreamMessage) {
/*  159 */         JMSStreamMessage jMSStreamMessage = proxyCreateStreamMessage(message);
/*      */       }
/*  161 */       else if (message instanceof ObjectMessage) {
/*  162 */         JMSObjectMessage jMSObjectMessage = proxyCreateObjectMessage(message);
/*      */       }
/*  164 */       else if (message instanceof TextMessage) {
/*  165 */         JMSTextMessage jMSTextMessage = proxyCreateTextMessage(message);
/*      */       }
/*  167 */       else if (message != null) {
/*  168 */         wrapper = proxyCreateMessage(message);
/*      */       } 
/*  170 */       if (Trace.isOn) {
/*  171 */         Trace.exit("com.ibm.mq.jms.MessageProxy", "wrapMessage(Message)", wrapper);
/*      */       }
/*  173 */       return wrapper;
/*      */     }
/*      */     
/*      */     protected static JMSMapMessage proxyCreateMapMessage(Message delegateMsg) {
/*  177 */       if (Trace.isOn) {
/*  178 */         Trace.entry("com.ibm.mq.jms.MessageProxy", "proxyCreateMapMessage(Message)", new Object[] { delegateMsg });
/*      */       }
/*      */       
/*  181 */       JMSMapMessage traceRet1 = createMapMessage(delegateMsg);
/*  182 */       if (Trace.isOn) {
/*  183 */         Trace.exit("com.ibm.mq.jms.MessageProxy", "proxyCreateMapMessage(Message)", traceRet1);
/*      */       }
/*  185 */       return traceRet1;
/*      */     }
/*      */     
/*      */     protected static JMSObjectMessage proxyCreateObjectMessage(Message delegateMsg) {
/*  189 */       if (Trace.isOn) {
/*  190 */         Trace.entry("com.ibm.mq.jms.MessageProxy", "proxyCreateObjectMessage(Message)", new Object[] { delegateMsg });
/*      */       }
/*      */       
/*  193 */       JMSObjectMessage traceRet1 = createObjectMessage(delegateMsg);
/*  194 */       if (Trace.isOn) {
/*  195 */         Trace.exit("com.ibm.mq.jms.MessageProxy", "proxyCreateObjectMessage(Message)", traceRet1);
/*      */       }
/*  197 */       return traceRet1;
/*      */     }
/*      */     
/*      */     protected static JMSTextMessage proxyCreateTextMessage(Message delegateMsg) {
/*  201 */       if (Trace.isOn) {
/*  202 */         Trace.entry("com.ibm.mq.jms.MessageProxy", "proxyCreateTextMessage(Message)", new Object[] { delegateMsg });
/*      */       }
/*      */       
/*  205 */       JMSTextMessage traceRet1 = createTextMessage(delegateMsg);
/*  206 */       if (Trace.isOn) {
/*  207 */         Trace.exit("com.ibm.mq.jms.MessageProxy", "proxyCreateTextMessage(Message)", traceRet1);
/*      */       }
/*  209 */       return traceRet1;
/*      */     }
/*      */     
/*      */     protected static JMSStreamMessage proxyCreateStreamMessage(Message delegateMsg) {
/*  213 */       if (Trace.isOn) {
/*  214 */         Trace.entry("com.ibm.mq.jms.MessageProxy", "proxyCreateStreamMessage(Message)", new Object[] { delegateMsg });
/*      */       }
/*      */       
/*  217 */       JMSStreamMessage traceRet1 = createStreamMessage(delegateMsg);
/*  218 */       if (Trace.isOn) {
/*  219 */         Trace.exit("com.ibm.mq.jms.MessageProxy", "proxyCreateStreamMessage(Message)", traceRet1);
/*      */       }
/*  221 */       return traceRet1;
/*      */     }
/*      */     
/*      */     protected static JMSMessage proxyCreateMessage(Message delegateMsg) {
/*  225 */       if (Trace.isOn) {
/*  226 */         Trace.entry("com.ibm.mq.jms.MessageProxy", "proxyCreateMessage(Message)", new Object[] { delegateMsg });
/*      */       }
/*      */       
/*  229 */       JMSMessage traceRet1 = createMessage(delegateMsg);
/*  230 */       if (Trace.isOn) {
/*  231 */         Trace.exit("com.ibm.mq.jms.MessageProxy", "proxyCreateMessage(Message)", traceRet1);
/*      */       }
/*  233 */       return traceRet1;
/*      */     }
/*      */     
/*      */     protected static JMSBytesMessage proxyCreateBytesMessage(Message delegateMsg) {
/*  237 */       if (Trace.isOn) {
/*  238 */         Trace.entry("com.ibm.mq.jms.MessageProxy", "proxyCreateBytesMessage(Message)", new Object[] { delegateMsg });
/*      */       }
/*      */       
/*  241 */       JMSBytesMessage traceRet1 = createBytesMessage(delegateMsg);
/*  242 */       if (Trace.isOn) {
/*  243 */         Trace.exit("com.ibm.mq.jms.MessageProxy", "proxyCreateBytesMessage(Message)", traceRet1);
/*      */       }
/*  245 */       return traceRet1;
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
/*      */   MQSession() {
/*  260 */     if (Trace.isOn) {
/*  261 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "<init>()");
/*      */     }
/*  263 */     if (Trace.isOn) {
/*  264 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "<init>()");
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
/*      */   public void close() throws JMSException {
/*  277 */     if (Trace.isOn) {
/*  278 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "close()");
/*      */     }
/*  280 */     this.commonSess.close();
/*      */     
/*  282 */     if (Trace.isOn) {
/*  283 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "close()");
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
/*      */   public void commit() throws JMSException {
/*  297 */     if (Trace.isOn) {
/*  298 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "commit()");
/*      */     }
/*  300 */     this.commonSess.commit();
/*      */     
/*  302 */     if (Trace.isOn) {
/*  303 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "commit()");
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
/*      */   public QueueBrowser createBrowser(Queue queue) throws JMSException {
/*  318 */     if (Trace.isOn) {
/*  319 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createBrowser(Queue)", new Object[] { queue });
/*      */     }
/*  321 */     MQQueueBrowser wrapper = new MQQueueBrowser();
/*  322 */     JmsQueue wrappedQueue = null;
/*      */     
/*  324 */     if (queue != null) {
/*  325 */       wrappedQueue = (JmsQueue)((MQQueue)queue).validateDestination();
/*      */     }
/*      */     
/*  328 */     wrapper.setDelegate((JmsQueueBrowser)this.commonSess.createBrowser((Queue)wrappedQueue));
/*  329 */     if (Trace.isOn) {
/*  330 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createBrowser(Queue)", wrapper);
/*      */     }
/*  332 */     return wrapper;
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
/*      */   public QueueBrowser createBrowser(Queue queue, String messageSelector) throws JMSException {
/*  349 */     if (Trace.isOn) {
/*  350 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createBrowser(Queue,String)", new Object[] { queue, messageSelector });
/*      */     }
/*      */     
/*  353 */     MQQueueBrowser wrapper = new MQQueueBrowser();
/*  354 */     JmsQueue theQueue = null;
/*      */     
/*  356 */     if (queue != null) {
/*  357 */       theQueue = (JmsQueue)((MQQueue)queue).validateDestination();
/*      */     }
/*      */     
/*  360 */     wrapper.setDelegate((JmsQueueBrowser)this.commonSess.createBrowser((Queue)theQueue, messageSelector));
/*  361 */     if (Trace.isOn) {
/*  362 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createBrowser(Queue,String)", wrapper);
/*      */     }
/*  364 */     return wrapper;
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
/*      */   public BytesMessage createBytesMessage() throws JMSException {
/*  376 */     if (Trace.isOn) {
/*  377 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createBytesMessage()");
/*      */     }
/*  379 */     JMSBytesMessage wrapper = MessageProxy.proxyCreateBytesMessage((Message)this.commonSess.createBytesMessage());
/*  380 */     if (Trace.isOn) {
/*  381 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createBytesMessage()", wrapper);
/*      */     }
/*  383 */     return (BytesMessage)wrapper;
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
/*      */   public MessageConsumer createConsumer(Destination destination) throws JMSException {
/*      */     MQMessageConsumer wrapper;
/*  397 */     if (Trace.isOn) {
/*  398 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createConsumer(Destination)", new Object[] { destination });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  403 */     if (destination instanceof Queue) {
/*  404 */       wrapper = new MQQueueReceiver();
/*      */     } else {
/*      */       
/*  407 */       wrapper = new MQTopicSubscriber();
/*      */     } 
/*      */     
/*  410 */     JmsDestination queue = null;
/*      */     
/*  412 */     if (destination != null) {
/*  413 */       queue = (JmsDestination)((MQDestination)destination).validateDestination();
/*      */     }
/*      */     
/*  416 */     wrapper.setDelegate((JmsMessageConsumer)this.commonSess.createConsumer((Destination)queue));
/*  417 */     if (Trace.isOn) {
/*  418 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createConsumer(Destination)", wrapper);
/*      */     }
/*  420 */     return wrapper;
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
/*      */   public MessageConsumer createConsumer(Destination destination, String messageSelector) throws JMSException {
/*      */     MQMessageConsumer wrapper;
/*  433 */     if (Trace.isOn) {
/*  434 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createConsumer(Destination,String)", new Object[] { destination, messageSelector });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  439 */     if (destination instanceof Queue) {
/*  440 */       wrapper = new MQQueueReceiver();
/*      */     } else {
/*      */       
/*  443 */       wrapper = new MQTopicSubscriber();
/*      */     } 
/*      */     
/*  446 */     JmsDestination queue = null;
/*      */     
/*  448 */     if (destination != null) {
/*  449 */       queue = (JmsDestination)((MQDestination)destination).validateDestination();
/*      */     }
/*      */     
/*  452 */     wrapper.setDelegate((JmsMessageConsumer)this.commonSess.createConsumer((Destination)queue, messageSelector));
/*  453 */     if (Trace.isOn) {
/*  454 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createConsumer(Destination,String)", wrapper);
/*      */     }
/*  456 */     return wrapper;
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
/*      */   public MessageConsumer createConsumer(Destination destination, String messageSelector, boolean noLocal) throws JMSException {
/*      */     MQMessageConsumer wrapper;
/*  472 */     if (Trace.isOn) {
/*  473 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createConsumer(Destination,String,boolean)", new Object[] { destination, messageSelector, 
/*  474 */             Boolean.valueOf(noLocal) });
/*      */     }
/*      */ 
/*      */     
/*  478 */     if (destination instanceof Queue) {
/*  479 */       wrapper = new MQQueueReceiver();
/*      */     } else {
/*      */       
/*  482 */       wrapper = new MQTopicSubscriber();
/*      */     } 
/*  484 */     JmsDestination queue = null;
/*      */     
/*  486 */     if (destination != null) {
/*  487 */       queue = (JmsDestination)((MQDestination)destination).validateDestination();
/*      */     }
/*      */     
/*  490 */     wrapper.setDelegate((JmsMessageConsumer)this.commonSess.createConsumer((Destination)queue, messageSelector, noLocal));
/*  491 */     if (Trace.isOn) {
/*  492 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createConsumer(Destination,String,boolean)", wrapper);
/*      */     }
/*      */     
/*  495 */     return wrapper;
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
/*      */   public TopicSubscriber createDurableSubscriber(Topic topic, String name) throws JMSException {
/*  510 */     if (Trace.isOn) {
/*  511 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createDurableSubscriber(Topic,String)", new Object[] { topic, name });
/*      */     }
/*      */     
/*  514 */     MQTopicSubscriber wrapper = new MQTopicSubscriber();
/*      */     
/*  516 */     JmsTopic theTopic = null;
/*      */     
/*  518 */     if (topic != null) {
/*  519 */       theTopic = (JmsTopic)((MQTopic)topic).validateDestination();
/*      */     }
/*  521 */     wrapper.setDelegate((JmsMessageConsumer)this.commonSess.createDurableSubscriber((Topic)theTopic, name));
/*  522 */     if (Trace.isOn) {
/*  523 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createDurableSubscriber(Topic,String)", wrapper);
/*      */     }
/*      */     
/*  526 */     return wrapper;
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
/*      */   public TopicSubscriber createDurableSubscriber(Topic topic, String name, String selector, boolean noLocal) throws JMSException {
/*  545 */     if (Trace.isOn) {
/*  546 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createDurableSubscriber(Topic,String,String,boolean)", new Object[] { topic, name, selector, 
/*  547 */             Boolean.valueOf(noLocal) });
/*      */     }
/*  549 */     MQTopicSubscriber wrapper = new MQTopicSubscriber();
/*  550 */     JmsTopic theTopic = null;
/*      */     
/*  552 */     if (topic != null) {
/*  553 */       theTopic = (JmsTopic)((MQTopic)topic).validateDestination();
/*      */     }
/*  555 */     wrapper.setDelegate((JmsMessageConsumer)this.commonSess.createDurableSubscriber((Topic)theTopic, name, selector, noLocal));
/*  556 */     if (Trace.isOn) {
/*  557 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createDurableSubscriber(Topic,String,String,boolean)", wrapper);
/*      */     }
/*      */     
/*  560 */     return wrapper;
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
/*      */   public MapMessage createMapMessage() throws JMSException {
/*  573 */     if (Trace.isOn) {
/*  574 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createMapMessage()");
/*      */     }
/*  576 */     JMSMapMessage wrapper = MessageProxy.proxyCreateMapMessage((Message)this.commonSess.createMapMessage());
/*  577 */     if (Trace.isOn) {
/*  578 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createMapMessage()", wrapper);
/*      */     }
/*  580 */     return (MapMessage)wrapper;
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
/*      */   public Message createMessage() throws JMSException {
/*  594 */     if (Trace.isOn) {
/*  595 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createMessage()");
/*      */     }
/*  597 */     JMSMessage wrapper = MessageProxy.proxyCreateMessage(this.commonSess.createMessage());
/*  598 */     if (Trace.isOn) {
/*  599 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createMessage()", wrapper);
/*      */     }
/*  601 */     return (Message)wrapper;
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
/*      */   public ObjectMessage createObjectMessage() throws JMSException {
/*  615 */     if (Trace.isOn) {
/*  616 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createObjectMessage()");
/*      */     }
/*  618 */     JMSObjectMessage wrapper = MessageProxy.proxyCreateObjectMessage((Message)this.commonSess.createObjectMessage());
/*  619 */     if (Trace.isOn) {
/*  620 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createObjectMessage()", wrapper);
/*      */     }
/*  622 */     return (ObjectMessage)wrapper;
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
/*      */   public ObjectMessage createObjectMessage(Serializable object) throws JMSException {
/*  637 */     if (Trace.isOn) {
/*  638 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createObjectMessage(Serializable)", new Object[] { object });
/*      */     }
/*      */     
/*  641 */     JMSObjectMessage wrapper = MessageProxy.proxyCreateObjectMessage((Message)this.commonSess.createObjectMessage(object));
/*      */     
/*  643 */     if (Trace.isOn) {
/*  644 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createObjectMessage(Serializable)", wrapper);
/*      */     }
/*  646 */     return (ObjectMessage)wrapper;
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
/*      */   public MessageProducer createProducer(Destination destinationP) throws JMSException {
/*      */     MQMessageProducer wrapper;
/*  660 */     if (Trace.isOn) {
/*  661 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createProducer(Destination)", new Object[] { destinationP });
/*      */     }
/*      */ 
/*      */     
/*  665 */     Destination destination = destinationP;
/*      */ 
/*      */     
/*  668 */     if (null != destination && destination instanceof Queue) {
/*  669 */       wrapper = new MQQueueSender();
/*      */     }
/*  671 */     else if (null != destination && destination instanceof Topic) {
/*  672 */       wrapper = new MQTopicPublisher();
/*      */     } else {
/*      */       
/*  675 */       wrapper = new MQMessageProducer();
/*      */     } 
/*      */ 
/*      */     
/*  679 */     if (destination != null) {
/*  680 */       destination = ((MQDestination)destination).validateDestination();
/*      */     }
/*      */ 
/*      */     
/*  684 */     wrapper.setDelegate((JmsMessageProducer)this.commonSess.createProducer(destination));
/*      */     
/*  686 */     if (Trace.isOn) {
/*  687 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createProducer(Destination)", wrapper);
/*      */     }
/*  689 */     return wrapper;
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
/*      */   public Queue createQueue(String queueName) throws JMSException {
/*  701 */     if (Trace.isOn) {
/*  702 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createQueue(String)", new Object[] { queueName });
/*      */     }
/*      */ 
/*      */     
/*  706 */     MQQueue wrapper = (MQQueue)this.commonSess.createQueue(queueName);
/*  707 */     if (Trace.isOn) {
/*  708 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createQueue(String)", wrapper);
/*      */     }
/*  710 */     return wrapper;
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
/*      */   public StreamMessage createStreamMessage() throws JMSException {
/*  724 */     if (Trace.isOn) {
/*  725 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createStreamMessage()");
/*      */     }
/*  727 */     JMSStreamMessage wrapper = MessageProxy.proxyCreateStreamMessage((Message)this.commonSess.createStreamMessage());
/*  728 */     if (Trace.isOn) {
/*  729 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createStreamMessage()", wrapper);
/*      */     }
/*  731 */     return (StreamMessage)wrapper;
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
/*      */   public TemporaryQueue createTemporaryQueue() throws JMSException {
/*  755 */     if (Trace.isOn) {
/*  756 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createTemporaryQueue()");
/*      */     }
/*  758 */     TemporaryQueue commonQueue = this.commonSess.createTemporaryQueue();
/*  759 */     MQTemporaryQueue wrapper = new MQTemporaryQueue(commonQueue);
/*  760 */     if (Trace.isOn) {
/*  761 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createTemporaryQueue()", wrapper);
/*      */     }
/*  763 */     return wrapper;
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
/*      */   public TemporaryTopic createTemporaryTopic() throws JMSException {
/*  777 */     if (Trace.isOn) {
/*  778 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createTemporaryTopic()");
/*      */     }
/*  780 */     TemporaryTopic commonTopic = this.commonSess.createTemporaryTopic();
/*  781 */     MQTemporaryTopic wrapper = new MQTemporaryTopic(commonTopic);
/*  782 */     if (Trace.isOn) {
/*  783 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createTemporaryTopic()", wrapper);
/*      */     }
/*  785 */     return wrapper;
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
/*      */   public TextMessage createTextMessage() throws JMSException {
/*  798 */     if (Trace.isOn) {
/*  799 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createTextMessage()");
/*      */     }
/*  801 */     JMSTextMessage wrapper = MessageProxy.proxyCreateTextMessage((Message)this.commonSess.createTextMessage());
/*  802 */     if (Trace.isOn) {
/*  803 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createTextMessage()", wrapper);
/*      */     }
/*  805 */     return (TextMessage)wrapper;
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
/*      */   public TextMessage createTextMessage(String string) throws JMSException {
/*  819 */     if (Trace.isOn) {
/*  820 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createTextMessage(String)", new Object[] { string });
/*      */     }
/*      */     
/*  823 */     JMSTextMessage wrapper = MessageProxy.proxyCreateTextMessage((Message)this.commonSess.createTextMessage(string));
/*  824 */     if (Trace.isOn) {
/*  825 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createTextMessage(String)", wrapper);
/*      */     }
/*  827 */     return (TextMessage)wrapper;
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
/*      */   public Topic createTopic(String topicName) throws JMSException {
/*  842 */     if (Trace.isOn) {
/*  843 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createTopic(String)", new Object[] { topicName });
/*      */     }
/*      */ 
/*      */     
/*  847 */     MQTopic wrapper = (MQTopic)this.commonSess.createTopic(topicName);
/*  848 */     if (Trace.isOn) {
/*  849 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createTopic(String)", wrapper);
/*      */     }
/*  851 */     return wrapper;
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
/*      */   public int getAcknowledgeMode() throws JMSException {
/*  865 */     int traceRet1 = this.commonSess.getAcknowledgeMode();
/*  866 */     if (Trace.isOn) {
/*  867 */       Trace.data(this, "com.ibm.mq.jms.MQSession", "getAcknowledgeMode()", "getter", 
/*  868 */           Integer.valueOf(traceRet1));
/*      */     }
/*  870 */     return traceRet1;
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
/*      */   public MessageListener getMessageListener() throws JMSException {
/*  883 */     FacadeMessageListener wrapper = (FacadeMessageListener)this.commonSess.getMessageListener();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  888 */     MessageListener listener = null;
/*  889 */     if (wrapper != null) {
/*  890 */       listener = wrapper.listener;
/*      */     }
/*  892 */     if (Trace.isOn) {
/*  893 */       Trace.data(this, "com.ibm.mq.jms.MQSession", "getMessageListener()", "getter", listener);
/*      */     }
/*  895 */     return listener;
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
/*      */   public boolean getTransacted() throws JMSException {
/*  909 */     boolean traceRet1 = this.commonSess.getTransacted();
/*  910 */     if (Trace.isOn) {
/*  911 */       Trace.data(this, "com.ibm.mq.jms.MQSession", "getTransacted()", "getter", 
/*  912 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  914 */     return traceRet1;
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
/*      */   public void recover() throws JMSException {
/*  928 */     if (Trace.isOn) {
/*  929 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "recover()");
/*      */     }
/*  931 */     this.commonSess.recover();
/*      */     
/*  933 */     if (Trace.isOn) {
/*  934 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "recover()");
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
/*      */   public void rollback() throws JMSException {
/*  949 */     if (Trace.isOn) {
/*  950 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "rollback()");
/*      */     }
/*  952 */     this.commonSess.rollback();
/*      */     
/*  954 */     if (Trace.isOn) {
/*  955 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "rollback()");
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
/*      */   public void run() {
/*  970 */     if (Trace.isOn) {
/*  971 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "run()");
/*      */     }
/*  973 */     this.commonSess.run();
/*  974 */     if (Trace.isOn) {
/*  975 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "run()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void setDelegate(JmsSession commonSess) {
/*  982 */     if (Trace.isOn) {
/*  983 */       Trace.data(this, "com.ibm.mq.jms.MQSession", "setDelegate(JmsSession)", "setter", commonSess);
/*      */     }
/*  985 */     this.commonSess = commonSess;
/*  986 */     this.delegate = (JmsPropertyContext)commonSess;
/*      */   }
/*      */   
/*      */   JmsSession getDelegate() {
/*  990 */     if (Trace.isOn) {
/*  991 */       Trace.data(this, "com.ibm.mq.jms.MQSession", "getDelegate()", "getter", this.commonSess);
/*      */     }
/*  993 */     return this.commonSess;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMessageListener(MessageListener listener) throws JMSException {
/* 1004 */     if (Trace.isOn) {
/* 1005 */       Trace.data(this, "com.ibm.mq.jms.MQSession", "setMessageListener(MessageListener)", "setter", listener);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1010 */     FacadeMessageListener wrapper = null;
/* 1011 */     if (listener != null) {
/* 1012 */       wrapper = new FacadeMessageListener(listener);
/*      */     }
/* 1014 */     this.commonSess.setMessageListener(wrapper);
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
/*      */   public void unsubscribe(String name) throws JMSException {
/* 1029 */     if (Trace.isOn) {
/* 1030 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "unsubscribe(String)", new Object[] { name });
/*      */     }
/* 1032 */     this.commonSess.unsubscribe(name);
/* 1033 */     if (Trace.isOn) {
/* 1034 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "unsubscribe(String)");
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
/*      */   public Message consume(byte[] flattenedRef) throws JMSException {
/* 1048 */     if (Trace.isOn) {
/* 1049 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "consume(byte [ ])", new Object[] { flattenedRef });
/*      */     }
/*      */     
/* 1052 */     Message msg = this.commonSess.consume(flattenedRef);
/* 1053 */     JMSMessage jMSMessage = MessageProxy.wrapMessage(msg);
/* 1054 */     if (Trace.isOn) {
/* 1055 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "consume(byte [ ])", jMSMessage);
/*      */     }
/* 1057 */     return (Message)jMSMessage;
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
/*      */   public void deliver(List<?> messageReferences) throws JMSException {
/* 1070 */     if (Trace.isOn) {
/* 1071 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "deliver(List)", new Object[] { messageReferences });
/*      */     }
/*      */     
/* 1074 */     if (null == messageReferences || messageReferences.size() == 0) {
/* 1075 */       if (Trace.isOn) {
/* 1076 */         Trace.exit(this, "com.ibm.mq.jms.MQSession", "deliver(List)", 1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1086 */     ArrayList<JmsMessageReference> newList = new ArrayList<>();
/* 1087 */     Iterator<?> it = messageReferences.iterator();
/* 1088 */     while (it.hasNext()) {
/* 1089 */       Object o = it.next();
/* 1090 */       if (o instanceof MQMessageReference) {
/* 1091 */         o = ((MQMessageReference)o).getDelegate();
/*      */       }
/* 1093 */       newList.add((JmsMessageReference)o);
/*      */     } 
/*      */     
/* 1096 */     this.commonSess.deliver(newList);
/*      */     
/* 1098 */     if (Trace.isOn) {
/* 1099 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "deliver(List)", 2);
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
/*      */   public JmsMessageReference recreateMessageReference(byte[] flatMR) throws JMSException {
/* 1115 */     if (Trace.isOn) {
/* 1116 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "recreateMessageReference(byte [ ])", new Object[] { flatMR });
/*      */     }
/*      */     
/* 1119 */     JmsMessageReference jmsMr = this.commonSess.recreateMessageReference(flatMR);
/* 1120 */     MQMessageReference mqMr = new MQMessageReference(jmsMr);
/* 1121 */     if (Trace.isOn) {
/* 1122 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "recreateMessageReference(byte [ ])", mqMr);
/*      */     }
/* 1124 */     return mqMr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkConsumerDestinationIsQueue(Destination destination) throws JMSException {
/* 1135 */     if (Trace.isOn) {
/* 1136 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "checkConsumerDestinationIsQueue(Destination)", new Object[] { destination });
/*      */     }
/* 1138 */     if (destination instanceof MQTopic) {
/*      */       
/* 1140 */       HashMap<String, String> inserts = new HashMap<>();
/* 1141 */       inserts.put("XMSC_INSERT_METHOD", "createConsumer()");
/* 1142 */       inserts.put("XMSC_INSERT_TYPE", destination.getClass().getName());
/* 1143 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 1144 */       if (Trace.isOn) {
/* 1145 */         Trace.throwing(this, "com.ibm.mq.jms.MQSession", "checkConsumerDestinationIsQueue(Destination)", (Throwable)je, 1);
/*      */       }
/*      */       
/* 1148 */       throw je;
/*      */     } 
/* 1150 */     if (!(destination instanceof MQQueue)) {
/*      */ 
/*      */ 
/*      */       
/* 1154 */       JMSException je2 = (JMSException)NLSServices.createException("JMSMQ0003", null);
/* 1155 */       if (Trace.isOn) {
/* 1156 */         Trace.throwing(this, "com.ibm.mq.jms.MQSession", "checkConsumerDestinationIsQueue(Destination)", (Throwable)je2, 2);
/*      */       }
/*      */       
/* 1159 */       throw je2;
/*      */     } 
/* 1161 */     if (Trace.isOn) {
/* 1162 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "checkConsumerDestinationIsQueue(Destination)");
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
/*      */   protected void checkProducerDestinationIsQueue(Destination destination) throws JMSException {
/* 1175 */     if (Trace.isOn) {
/* 1176 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "checkProducerDestinationIsQueue(Destination)", new Object[] { destination });
/*      */     }
/*      */     
/* 1179 */     if (destination != null && !(destination instanceof MQQueue)) {
/*      */       
/* 1181 */       HashMap<String, String> inserts = new HashMap<>();
/* 1182 */       inserts.put("XMSC_INSERT_METHOD", "createProducer()");
/* 1183 */       inserts.put("XMSC_INSERT_TYPE", destination.getClass().getName());
/* 1184 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 1185 */       if (Trace.isOn) {
/* 1186 */         Trace.throwing(this, "com.ibm.mq.jms.MQSession", "checkProducerDestinationIsQueue(Destination)", (Throwable)je);
/*      */       }
/*      */       
/* 1189 */       throw je;
/*      */     } 
/* 1191 */     if (Trace.isOn) {
/* 1192 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "checkProducerDestinationIsQueue(Destination)");
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
/*      */   protected void checkConsumerDestinationIsTopic(Destination destination) throws JMSException {
/* 1205 */     if (Trace.isOn) {
/* 1206 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "checkConsumerDestinationIsTopic(Destination)", new Object[] { destination });
/*      */     }
/* 1208 */     if (destination instanceof MQQueue) {
/*      */       
/* 1210 */       HashMap<String, String> inserts = new HashMap<>();
/* 1211 */       inserts.put("XMSC_INSERT_METHOD", "createConsumer()");
/* 1212 */       inserts.put("XMSC_INSERT_TYPE", destination.getClass().getName());
/* 1213 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 1214 */       if (Trace.isOn) {
/* 1215 */         Trace.throwing(this, "com.ibm.mq.jms.MQSession", "checkConsumerDestinationIsTopic(Destination)", (Throwable)je, 1);
/*      */       }
/*      */       
/* 1218 */       throw je;
/*      */     } 
/* 1220 */     if (!(destination instanceof MQTopic)) {
/*      */ 
/*      */ 
/*      */       
/* 1224 */       JMSException traceRet3 = (JMSException)NLSServices.createException("JMSMQ0003", null);
/* 1225 */       if (Trace.isOn) {
/* 1226 */         Trace.throwing(this, "com.ibm.mq.jms.MQSession", "checkConsumerDestinationIsTopic(Destination)", (Throwable)traceRet3, 2);
/*      */       }
/*      */       
/* 1229 */       throw traceRet3;
/*      */     } 
/* 1231 */     if (Trace.isOn) {
/* 1232 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "checkConsumerDestinationIsTopic(Destination)");
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
/*      */   protected void checkProducerDestinationIsTopic(Destination destination) throws JMSException {
/* 1245 */     if (Trace.isOn) {
/* 1246 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "checkProducerDestinationIsTopic(Destination)", new Object[] { destination });
/*      */     }
/*      */     
/* 1249 */     if (destination != null && !(destination instanceof MQTopic)) {
/*      */       
/* 1251 */       HashMap<String, String> inserts = new HashMap<>();
/* 1252 */       inserts.put("XMSC_INSERT_METHOD", "createProducer()");
/* 1253 */       inserts.put("XMSC_INSERT_TYPE", destination.getClass().getName());
/* 1254 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1112", inserts);
/* 1255 */       if (Trace.isOn) {
/* 1256 */         Trace.throwing(this, "com.ibm.mq.jms.MQSession", "checkProducerDestinationIsTopic(Destination)", (Throwable)je);
/*      */       }
/*      */       
/* 1259 */       throw je;
/*      */     } 
/* 1261 */     if (Trace.isOn) {
/* 1262 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "checkProducerDestinationIsTopic(Destination)");
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
/*      */   public void clearMessageReferences() {
/* 1274 */     if (Trace.isOn) {
/* 1275 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "clearMessageReferences()");
/*      */     }
/* 1277 */     this.commonSess.clearMessageReferences();
/* 1278 */     if (Trace.isOn) {
/* 1279 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "clearMessageReferences()");
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
/*      */   public MessageConsumer createDurableConsumer(Topic topic, String name) throws InvalidDestinationException, IllegalStateException, JMSException {
/* 1293 */     if (Trace.isOn) {
/* 1294 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createDurableConsumer(Topic,String)", new Object[] { topic, name });
/*      */     }
/*      */ 
/*      */     
/* 1298 */     MQTopicSubscriber wrapper = new MQTopicSubscriber();
/*      */     
/* 1300 */     JmsTopic theTopic = null;
/*      */     
/* 1302 */     if (topic != null) {
/* 1303 */       theTopic = (JmsTopic)((MQTopic)topic).validateDestination();
/*      */     }
/* 1305 */     wrapper.setDelegate((JmsMessageConsumer)this.commonSess.createDurableSubscriber((Topic)theTopic, name));
/* 1306 */     if (Trace.isOn) {
/* 1307 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createDurableConsumer(Topic,String)", wrapper);
/*      */     }
/* 1309 */     return wrapper;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageConsumer createDurableConsumer(Topic topic, String name, String selector, boolean noLocal) throws InvalidDestinationException, InvalidSelectorException, IllegalStateException, JMSException {
/* 1320 */     if (Trace.isOn) {
/* 1321 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createDurableConsumer(Topic,String,String,boolean)", new Object[] { topic, name, selector, 
/*      */             
/* 1323 */             Boolean.valueOf(noLocal) });
/*      */     }
/*      */     
/* 1326 */     MQTopicSubscriber wrapper = new MQTopicSubscriber();
/* 1327 */     JmsTopic theTopic = null;
/*      */     
/* 1329 */     if (topic != null) {
/* 1330 */       theTopic = (JmsTopic)((MQTopic)topic).validateDestination();
/*      */     }
/* 1332 */     wrapper.setDelegate((JmsMessageConsumer)this.commonSess.createDurableSubscriber((Topic)theTopic, name, selector, noLocal));
/* 1333 */     if (Trace.isOn) {
/* 1334 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createDurableConsumer(Topic,String,String,boolean)", wrapper);
/*      */     }
/*      */     
/* 1337 */     return wrapper;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName) throws JMSException, InvalidDestinationException, InvalidSelectorException {
/* 1348 */     if (Trace.isOn) {
/* 1349 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createSharedConsumer(Topic,String)", new Object[] { topic, sharedSubscriptionName });
/*      */     }
/*      */ 
/*      */     
/* 1353 */     MQMessageConsumer wrapper = new MQTopicSubscriber();
/*      */     
/* 1355 */     JmsTopic jmsTopic = null;
/*      */     
/* 1357 */     if (topic != null) {
/* 1358 */       jmsTopic = (JmsTopic)((MQTopic)topic).validateDestination();
/*      */     }
/*      */     
/* 1361 */     wrapper.setDelegate((JmsMessageConsumer)this.commonSess.createSharedConsumer((Topic)jmsTopic, sharedSubscriptionName));
/* 1362 */     if (Trace.isOn) {
/* 1363 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createSharedConsumer(Topic,String)", wrapper);
/*      */     }
/* 1365 */     return wrapper;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName, String messageSelector) throws JMSException, InvalidDestinationException, InvalidSelectorException {
/* 1376 */     if (Trace.isOn) {
/* 1377 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createSharedConsumer(Topic,String,String)", new Object[] { topic, sharedSubscriptionName, messageSelector });
/*      */     }
/*      */ 
/*      */     
/* 1381 */     MQMessageConsumer wrapper = new MQTopicSubscriber();
/*      */     
/* 1383 */     JmsTopic jmsTopic = null;
/*      */     
/* 1385 */     if (topic != null) {
/* 1386 */       jmsTopic = (JmsTopic)((MQTopic)topic).validateDestination();
/*      */     }
/*      */     
/* 1389 */     wrapper.setDelegate((JmsMessageConsumer)this.commonSess.createSharedConsumer((Topic)jmsTopic, sharedSubscriptionName, messageSelector));
/* 1390 */     if (Trace.isOn) {
/* 1391 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createSharedConsumer(Topic,String,String)", wrapper);
/*      */     }
/*      */     
/* 1394 */     return wrapper;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageConsumer createSharedDurableConsumer(Topic topic, String sharedSubscriptionName) throws JMSException, InvalidDestinationException {
/* 1404 */     if (Trace.isOn) {
/* 1405 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createSharedDurableConsumer(Topic,String)", new Object[] { topic, sharedSubscriptionName });
/*      */     }
/*      */ 
/*      */     
/* 1409 */     MQTopicSubscriber wrapper = new MQTopicSubscriber();
/*      */     
/* 1411 */     JmsTopic theTopic = null;
/*      */     
/* 1413 */     if (topic != null) {
/* 1414 */       theTopic = (JmsTopic)((MQTopic)topic).validateDestination();
/*      */     }
/* 1416 */     wrapper.setDelegate((JmsMessageConsumer)this.commonSess.createSharedDurableConsumer((Topic)theTopic, sharedSubscriptionName));
/* 1417 */     if (Trace.isOn) {
/* 1418 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createSharedDurableConsumer(Topic,String)", wrapper);
/*      */     }
/*      */     
/* 1421 */     return wrapper;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageConsumer createSharedDurableConsumer(Topic topic, String sharedSubscriptionName, String messageSelector) throws InvalidDestinationException, IllegalStateException, JMSException {
/* 1432 */     if (Trace.isOn) {
/* 1433 */       Trace.entry(this, "com.ibm.mq.jms.MQSession", "createSharedDurableConsumer(Topic,String,String)", new Object[] { topic, sharedSubscriptionName, messageSelector });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1438 */     MQTopicSubscriber wrapper = new MQTopicSubscriber();
/*      */     
/* 1440 */     JmsTopic theTopic = null;
/*      */     
/* 1442 */     if (topic != null) {
/* 1443 */       theTopic = (JmsTopic)((MQTopic)topic).validateDestination();
/*      */     }
/* 1445 */     wrapper.setDelegate((JmsMessageConsumer)this.commonSess.createSharedDurableConsumer((Topic)theTopic, sharedSubscriptionName, messageSelector));
/* 1446 */     if (Trace.isOn) {
/* 1447 */       Trace.exit(this, "com.ibm.mq.jms.MQSession", "createSharedDurableConsumer(Topic,String,String)", wrapper);
/*      */     }
/*      */     
/* 1450 */     return wrapper;
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
/*      */   public String toString() {
/* 1476 */     return String.valueOf(this.commonSess.toJson());
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */