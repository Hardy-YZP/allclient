/*      */ package com.ibm.msg.client.jms.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.CSIException;
/*      */ import com.ibm.msg.client.commonservices.Log.Log;
/*      */ import com.ibm.msg.client.commonservices.locking.TraceableReentrantLock;
/*      */ import com.ibm.msg.client.commonservices.monitor.MonitorAgent;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.commonservices.workqueue.WorkQueueManager;
/*      */ import com.ibm.msg.client.commonservices.workqueue.WorkQueueToken;
/*      */ import com.ibm.msg.client.jms.DetailedJMSException;
/*      */ import com.ibm.msg.client.jms.JmsConnection;
/*      */ import com.ibm.msg.client.jms.JmsDestination;
/*      */ import com.ibm.msg.client.jms.JmsMessage;
/*      */ import com.ibm.msg.client.jms.JmsMessageReference;
/*      */ import com.ibm.msg.client.jms.JmsQueue;
/*      */ import com.ibm.msg.client.jms.JmsSession;
/*      */ import com.ibm.msg.client.jms.JmsTopic;
/*      */ import com.ibm.msg.client.jms.admin.JmsDestinationImpl;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import com.ibm.msg.client.provider.ProviderFactoryFactory;
/*      */ import com.ibm.msg.client.provider.ProviderJmsFactory;
/*      */ import com.ibm.msg.client.provider.ProviderMessageConsumer;
/*      */ import com.ibm.msg.client.provider.ProviderMessageFactory;
/*      */ import com.ibm.msg.client.provider.ProviderMessageProducer;
/*      */ import com.ibm.msg.client.provider.ProviderMessageReference;
/*      */ import com.ibm.msg.client.provider.ProviderQueueBrowser;
/*      */ import com.ibm.msg.client.provider.ProviderSession;
/*      */ import com.ibm.msg.client.provider.ProviderXASession;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.NotSerializableException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.Serializable;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.BlockingQueue;
/*      */ import java.util.concurrent.LinkedBlockingDeque;
/*      */ import java.util.concurrent.LinkedBlockingQueue;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import java.util.concurrent.locks.ReentrantLock;
/*      */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*      */ import javax.jms.BytesMessage;
/*      */ import javax.jms.CompletionListener;
/*      */ import javax.jms.Destination;
/*      */ import javax.jms.IllegalStateException;
/*      */ import javax.jms.InvalidDestinationException;
/*      */ import javax.jms.InvalidSelectorException;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.JMSRuntimeException;
/*      */ import javax.jms.MapMessage;
/*      */ import javax.jms.Message;
/*      */ import javax.jms.MessageConsumer;
/*      */ import javax.jms.MessageListener;
/*      */ import javax.jms.MessageProducer;
/*      */ import javax.jms.ObjectMessage;
/*      */ import javax.jms.Queue;
/*      */ import javax.jms.QueueBrowser;
/*      */ import javax.jms.StreamMessage;
/*      */ import javax.jms.TemporaryQueue;
/*      */ import javax.jms.TemporaryTopic;
/*      */ import javax.jms.TextMessage;
/*      */ import javax.jms.Topic;
/*      */ import javax.jms.TopicSubscriber;
/*      */ import javax.management.NotCompliantMBeanException;
/*      */ import javax.management.StandardMBean;
/*      */ import org.json.JSONObject;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JmsSessionImpl
/*      */   extends JmsPropertyContextImpl
/*      */   implements JmsSession
/*      */ {
/*      */   private static final long serialVersionUID = -270784834968901154L;
/*      */   static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsSessionImpl.java";
/*      */   private boolean transacted;
/*      */   
/*      */   static {
/*  124 */     if (Trace.isOn) {
/*  125 */       Trace.data("com.ibm.msg.client.jms.internal.JmsSessionImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsSessionImpl.java");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  246 */     PropertyStore.register("com.ibm.msg.client.wmq.suppressBrowseMarkMessageWarning", false);
/*      */   }
/*      */   private boolean clearUpProviderXASession = false;
/*      */   private int acknowledgeMode;
/*      */   private ProviderSession providerSession;
/*      */   protected JmsConnectionImpl connection;
/*      */   private MessageListener messageListener;
/*      */   private ProviderFactoryFactory providerFactory;
/*      */   private String connectionTypeName;
/*      */   private int uncommittedReceiveCount = 0;
/*      */   private final int dupsCommitThreshold;
/*      */   private State state = new State(2);
/*      */   private static final class OnMessageLock extends TraceableReentrantLock {
/*      */     private static final long serialVersionUID = -6003466809869033963L;
/*      */     private OnMessageLock() {} }
/*      */   private final ReentrantLock onMessageLock = (ReentrantLock)new OnMessageLock();
/*      */   private final ReentrantDoubleLock sessionSyncLock = new ReentrantDoubleLock(true);
/*      */   private final Set<JmsMessageProducerImpl> producers;
/*  264 */   private final Set<JmsMessageConsumerImpl> syncConsumers; final Set<JmsMessageConsumerImpl> asyncConsumers; private final Object consumerListsLock = new Object(); private final List<JmsQueueBrowserImpl> browsers; private List<JmsMessageReference> messageReferences; Thread messageListenerThread = null; volatile Thread asyncPutThread = null; private boolean onMessageHasThrownException = false; private boolean nullMsgOnDelivery = false; public static final String SUPPRESS_BROWSE_MARK_MSG = "com.ibm.msg.client.wmq.suppressBrowseMarkMessageWarning"; private boolean suppressBrowseWithMarkMsg = false; private final boolean isCICSUnmanaged; private final boolean isIMS; LinkedBlockingDeque<SendDetails> sendQueue; public static final String asyncSendTimeoutProperty = "com.ibm.msg.client.jms.asyncSendWaitTimeout"; long asyncSendTimeoutPropertyDefault; int maximumQueueDepth; long asyncSendWaitTimeout; WorkQueueToken queueToken; SendQueueProcessor2 queueProcessor2; private MessageLoadingLock messageLoadingLock; public static final String sendQueueProcessorPollTimeout = "com.ibm.msg.client.jms.asyncSendPollTimeout"; public static final String sendQueueProcessorMaximumDepth = "com.ibm.msg.client.jms.asyncSendMaxBufferSize"; public void close() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close()");  close(false); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close()");  } protected void close(boolean closingFromConnection) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close(boolean)", new Object[] { Boolean.valueOf(closingFromConnection) });  JmsTls jTls = JmsTls.getInstance(); if (jTls.inCompletionListener() && jTls.completionListenerSession() == this) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", "close()"); inserts.put("XMSC_INSERT_NAME", "completion"); IllegalStateException ise = (IllegalStateException)JmsErrorUtils.createException("JMSCC0012", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close(boolean)", (Throwable)ise, 1);  throw ise; }  this.connection.removeSession(this); JMSException firstException = null; try { stop(false); } catch (JMSException je) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close(boolean)", (Throwable)je, 1);  if (firstException == null) firstException = je;  }  if (!isAsync()) { this.sessionSyncLock.getExclusiveLock(); if (this.queueProcessor2 != null) this.queueProcessor2.close();  }  this.onMessageLock.lock(); try { if (this.queueToken != null) { this.queueToken.end(); this.queueToken = null; }  try { if (this.state.close()) { if (this.clearUpProviderXASession && !((ProviderXASession)this.providerSession).isXASessionActive()) { Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close(boolean)", "Clearing up ProviderXASession", this.providerSession); this.providerSession.close(closingFromConnection); this.clearUpProviderXASession = false; }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close(boolean)", 1);  return; }  synchronized (this.syncConsumers) { Object[] cons = this.syncConsumers.toArray(); for (int i = 0; i < cons.length; i++) { try { ((JmsMessageConsumerImpl)cons[i]).close(true); } catch (JMSException je) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close(boolean)", (Throwable)je, 2);  if (firstException == null) firstException = je;  }  }  this.syncConsumers.clear(); }  if (!(this instanceof com.ibm.msg.client.jms.JmsXASession) && !(this.providerSession instanceof ProviderXASession) && !this.isCICSUnmanaged && !this.isIMS) try { HashMap<String, Integer> data; switch (this.acknowledgeMode) { case 0: rollbackTransaction(); break;case 2: rollbackTransaction(); break;case 3: commitTransaction(); break;case 1: break;default: data = new HashMap<>(); data.put("ackmode", Integer.valueOf(this.acknowledgeMode)); Trace.ffst(this, "close()", "XJ003002", data, JMSException.class); break; }  } catch (JMSException je) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close(boolean)", (Throwable)je, 3);  if (firstException == null) firstException = je;  }   synchronized (this.producers) { Object[] prods = this.producers.toArray(); for (int i = 0; i < prods.length; i++) { try { ((JmsMessageProducerImpl)prods[i]).close(true); } catch (JMSException je) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close(boolean)", (Throwable)je, 4);  if (firstException == null) firstException = je;  }  }  this.producers.clear(); }  synchronized (this.asyncConsumers) { Object[] cons = this.asyncConsumers.toArray(); for (int i = 0; i < cons.length; i++) { try { ((JmsMessageConsumerImpl)cons[i]).close(true); } catch (JMSException je) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close(boolean)", (Throwable)je, 5);  if (firstException == null) firstException = je;  }  }  this.asyncConsumers.clear(); }  synchronized (this.browsers) { JmsQueueBrowserImpl[] browsersSnapShot = this.browsers.<JmsQueueBrowserImpl>toArray(new JmsQueueBrowserImpl[this.browsers.size()]); for (JmsQueueBrowserImpl browser : browsersSnapShot) { try { browser.close(true); } catch (JMSException je) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close(boolean)", (Throwable)je, 6);  if (firstException == null) firstException = je;  }  }  }  if (!(this.providerSession instanceof ProviderXASession) || !((ProviderXASession)this.providerSession).isXASessionActive()) { try { this.providerSession.close(closingFromConnection); } catch (JMSException je) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close(boolean)", (Throwable)je, 7);  if (firstException == null) firstException = je;  }  } else { Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close(boolean)", "Keeping ProviderXASession open", this.providerSession); this.clearUpProviderXASession = true; }  if (firstException != null) { if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close(boolean)", (Throwable)firstException, 2);  throw firstException; }  } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close(boolean)", 1);  this.onMessageLock.unlock(); }  } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close(boolean)", 2);  while (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "close(boolean)", 2);  } public void commit() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "commit()");  JmsTls jTls = JmsTls.getInstance(); if (jTls.inCompletionListener() && jTls.completionListenerSession() == this) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", "commit()"); inserts.put("XMSC_INSERT_NAME", "completion"); IllegalStateException ise = (IllegalStateException)JmsErrorUtils.createException("JMSCC0012", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "commit()", (Throwable)ise, 1);  throw ise; }  if (this.isCICSUnmanaged || this.isIMS) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", "commit()"); String messageID = this.isCICSUnmanaged ? "JMSCC6002" : "JMSCC6012"; IllegalStateException ise = (IllegalStateException)JmsErrorUtils.createException(messageID, inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "commit()", (Throwable)ise, 3);  throw ise; }  if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); checkSynchronousUsage("commit"); if (this.acknowledgeMode != 0) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", "commit"); JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0014", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "commit()", (Throwable)je, 3);  throw je; }  commitTransaction(); } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "commit()");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "commit()");  } public QueueBrowser createBrowser(Queue queue, String selector) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createBrowser(Queue,String)", new Object[] { queue, selector });  checkSynchronousUsage("createBrowser"); checkQueueDomain("createBrowser"); JmsQueueBrowserImpl jmsBrowser = null; if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); if (!(queue instanceof JmsDestinationImpl)) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_DESTINATION_NAME", queue); JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0099", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createBrowser(Queue,String)", (Throwable)je, 1);  throw je; }  if (queue instanceof JmsTemporaryQueueImpl && ((JmsTemporaryQueueImpl)queue).getConnection() != this.connection) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_DESTINATION_NAME", queue); JMSException je2 = (JMSException)JmsErrorUtils.createException("JMSCC3003", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createBrowser(Queue,String)", (Throwable)je2, 2);  throw je2; }  jmsBrowser = new JmsQueueBrowserImpl(this, (JmsQueue)queue, selector); ProviderDestination providerDestination = JmsDestinationImplProxy.getProviderDestination((JmsDestinationImpl)queue); ProviderQueueBrowser browser = this.providerSession.createBrowser(providerDestination, selector, jmsBrowser); jmsBrowser.setProviderBrowser(browser); synchronized (this.browsers) { this.browsers.add(jmsBrowser); }  JmsDestinationImplProxy.incrementUseCount((JmsDestinationImpl)queue); } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createBrowser(Queue,String)");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createBrowser(Queue,String)", jmsBrowser);  }  return (QueueBrowser)jmsBrowser; } public QueueBrowser createBrowser(Queue queue) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createBrowser(Queue)", new Object[] { queue });  checkSynchronousUsage("createBrowser"); checkQueueDomain("createBrowser"); QueueBrowser traceRet1 = createBrowser(queue, (String)null); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createBrowser(Queue)", traceRet1);  return traceRet1; } public BytesMessage createBytesMessage() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createBytesMessage()");  checkSynchronousUsage("createBytesMessage"); BytesMessage msg = null; if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); msg = new JmsBytesMessageImpl(this); } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createBytesMessage()");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createBytesMessage()", msg);  }  return msg; } public MessageConsumer createConsumer(Destination dest, String selector, boolean noLocal) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createConsumer(Destination,String,boolean)", new Object[] { dest, selector, Boolean.valueOf(noLocal) });  checkSynchronousUsage("createConsumer"); checkNotMixedDomain(dest, "createConsumer"); JmsMessageConsumerImpl jmsConsumer = null; if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); if (!(dest instanceof JmsDestinationImpl)) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_DESTINATION_NAME", dest); JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0099", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createConsumer(Destination,String,boolean)", (Throwable)je, 1);  throw je; }  if (!((JmsDestinationImpl)dest).getStringProperty("XMSC_CONNECTION_TYPE_NAME").equals(this.connectionTypeName)) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_CONNECTION_TYPE_NAME", this.connectionTypeName); inserts.put("XMSC_CONNECTION_TYPE", ((JmsDestinationImpl)dest).getStringProperty("XMSC_CONNECTION_TYPE_NAME")); JMSException je2 = (JMSException)JmsErrorUtils.createException("JMSCC0092", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createConsumer(Destination,String,boolean)", (Throwable)je2, 2);  throw je2; }  if ((dest instanceof JmsTemporaryQueueImpl && ((JmsTemporaryQueueImpl)dest).getConnection() != this.connection) || (dest instanceof JmsTemporaryTopicImpl && ((JmsTemporaryTopicImpl)dest).getConnection() != this.connection)) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_DESTINATION_NAME", dest); JMSException je2 = (JMSException)JmsErrorUtils.createException("JMSCC3003", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createConsumer(Destination,String,boolean)", (Throwable)je2, 3);  throw je2; }  synchronized (this.state) { if (dest instanceof Queue) { jmsConsumer = new JmsQueueReceiverImpl(this, (Queue)dest, selector, noLocal); } else if (dest instanceof Topic) { jmsConsumer = new JmsTopicSubscriberImpl(this, (Topic)dest, selector, noLocal, null, false, false); } else { jmsConsumer = new JmsMessageConsumerImpl(this, dest, selector, noLocal, null, false, false); }  ProviderDestination providerDestination = JmsDestinationImplProxy.getProviderDestination((JmsDestinationImpl)dest); ProviderMessageConsumer consumer = this.providerSession.createConsumer(providerDestination, selector, noLocal, jmsConsumer); jmsConsumer.setProviderConsumer(consumer); if (getState() == 1) { jmsConsumer.start(); } else { jmsConsumer.stop(); }  this.syncConsumers.add(jmsConsumer); JmsDestinationImplProxy.incrementUseCount((JmsDestinationImpl)dest); }  } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createConsumer(Destination,String,boolean)");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createConsumer(Destination,String,boolean)", jmsConsumer);  }  return (MessageConsumer)jmsConsumer; } public MessageConsumer createConsumer(Destination dest, String selector) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createConsumer(Destination,String)", new Object[] { dest, selector });  checkSynchronousUsage("createConsumer"); checkNotMixedDomain(dest, "createConsumer"); MessageConsumer traceRet1 = createConsumer(dest, selector, false); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createConsumer(Destination,String)", traceRet1);  return traceRet1; } public MessageConsumer createConsumer(Destination dest) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createConsumer(Destination)", new Object[] { dest });  checkSynchronousUsage("createConsumer"); checkNotMixedDomain(dest, "createConsumer"); MessageConsumer traceRet1 = createConsumer(dest, (String)null, false); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createConsumer(Destination)", traceRet1);  return traceRet1; } public TopicSubscriber createDurableSubscriber(Topic topic, String subscriptionName, String selector, boolean noLocal) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createDurableSubscriber(Topic,String,String,boolean)", new Object[] { topic, subscriptionName, selector, Boolean.valueOf(noLocal) });  checkSynchronousUsage("createDurableSubscriber"); checkTopicDomain("createDurableSubscriber"); JmsTopicSubscriberImpl jmsSubscriber = null; if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); if (!(topic instanceof JmsDestinationImpl)) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_DESTINATION_NAME", topic); JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0099", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createDurableSubscriber(Topic,String,String,boolean)", (Throwable)je, 1);  throw je; }  JmsDestinationImpl jmsTopic = (JmsDestinationImpl)topic; if (!jmsTopic.getStringProperty("XMSC_CONNECTION_TYPE_NAME").equals(this.connectionTypeName)) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_CONNECTION_TYPE_NAME", this.connectionTypeName); inserts.put("XMSC_CONNECTION_TYPE", jmsTopic.getStringProperty("XMSC_CONNECTION_TYPE_NAME")); JMSException je2 = (JMSException)JmsErrorUtils.createException("JMSCC0092", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createDurableSubscriber(Topic,String,String,boolean)", (Throwable)je2, 2);  throw je2; }  ProviderDestination providerDestination = JmsDestinationImplProxy.getProviderDestination(jmsTopic); if (providerDestination.isTemporary()) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_TYPE", "Topic"); inserts.put("XMSC_DESTINATION_NAME", topic.getTopicName()); JMSException je3 = (JMSException)JmsErrorUtils.createException("JMSCC0093", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createDurableSubscriber(Topic,String,String,boolean)", (Throwable)je3, 3);  throw je3; }  if (subscriptionName == null || "".equals(subscriptionName)) { JMSException je4 = (JMSException)JmsErrorUtils.createException("JMSCC0100", null); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createDurableSubscriber(Topic,String,String,boolean)", (Throwable)je4, 4);  throw je4; }  String clientID = this.connection.getClientID(); if (clientID == null || "".equals(clientID)) { JMSException je5 = (JMSException)JmsErrorUtils.createException("JMSCC0101", null); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createDurableSubscriber(Topic,String,String,boolean)", (Throwable)je5, 5);  throw je5; }  jmsSubscriber = new JmsTopicSubscriberImpl(this, topic, selector, noLocal, subscriptionName, true, false); ProviderMessageConsumer consumer = this.providerSession.createDurableSubscriber(JmsDestinationImplProxy.getProviderDestination((JmsDestinationImpl)topic), subscriptionName, selector, noLocal, jmsSubscriber); jmsSubscriber.setProviderConsumer(consumer); this.syncConsumers.add(jmsSubscriber); JmsDestinationImplProxy.incrementUseCount((JmsDestinationImpl)topic); } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createDurableSubscriber(Topic,String,String,boolean)");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createDurableSubscriber(Topic,String,String,boolean)", jmsSubscriber);  }  return (TopicSubscriber)jmsSubscriber; } public TopicSubscriber createDurableSubscriber(Topic topic, String subscriptionName) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createDurableSubscriber(Topic,String)", new Object[] { topic, subscriptionName });  checkSynchronousUsage("createDurableSubscriber"); checkTopicDomain("createDurableSubscriber"); TopicSubscriber traceRet1 = createDurableSubscriber(topic, subscriptionName, (String)null, false); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createDurableSubscriber(Topic,String)", traceRet1);  return traceRet1; } public MapMessage createMapMessage() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createMapMessage()");  checkSynchronousUsage("createMapMessage"); MapMessage msg = null; if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); msg = new JmsMapMessageImpl(this); } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createMapMessage()");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createMapMessage()", msg);  }  return msg; } public Message createMessage() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createMessage()");  checkSynchronousUsage("createMessage"); Message msg = null; if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); msg = new JmsMessageImpl(this); } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createMessage()");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createMessage()", msg);  }  return msg; } public ObjectMessage createObjectMessage() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createObjectMessage()");  checkSynchronousUsage("createObjectMessage"); ObjectMessage msg = null; if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); msg = new JmsObjectMessageImpl(this); } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createObjectMessage()");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createObjectMessage()", msg);  }  return msg; } public ObjectMessage createObjectMessage(Serializable obj) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createObjectMessage(Serializable)", new Object[] { obj });  checkSynchronousUsage("createObjectMessage"); ObjectMessage msg = null; if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); msg = new JmsObjectMessageImpl(this, obj); } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createObjectMessage(Serializable)");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createObjectMessage(Serializable)", msg);  }  return msg; } public MessageProducer createProducer(Destination dest) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createProducer(Destination)", new Object[] { dest });  checkSynchronousUsage("createProducer"); checkNotMixedDomain(dest, "createProducer"); JmsMessageProducerImpl jmsProducer = null; if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); if (dest != null) { if (!(dest instanceof JmsDestinationImpl)) { JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0099", null); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createProducer(Destination)", (Throwable)je, 1);  throw je; }  if (!((JmsDestinationImpl)dest).getStringProperty("XMSC_CONNECTION_TYPE_NAME").equals(this.connectionTypeName)) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_CONNECTION_TYPE_NAME", this.connectionTypeName); inserts.put("XMSC_CONNECTION_TYPE", ((JmsDestinationImpl)dest).getStringProperty("XMSC_CONNECTION_TYPE_NAME")); JMSException je2 = (JMSException)JmsErrorUtils.createException("JMSCC0092", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createProducer(Destination)", (Throwable)je2, 2);  throw je2; }  }  if (dest instanceof Queue || this instanceof javax.jms.QueueSession || this instanceof javax.jms.XAQueueSession) { jmsProducer = new JmsQueueSenderImpl((Queue)dest, this); } else if (dest instanceof Topic || this instanceof javax.jms.TopicSession || this instanceof javax.jms.XATopicSession) { jmsProducer = new JmsTopicPublisherImpl((Topic)dest, this); } else { jmsProducer = new JmsMessageProducerImpl(dest, this); }  ProviderDestination providerDest = (dest == null) ? null : JmsDestinationImplProxy.getProviderDestination((JmsDestinationImpl)dest); ProviderMessageProducer producer = this.providerSession.createProducer(providerDest, jmsProducer); jmsProducer.setProviderProducer(producer); this.producers.add(jmsProducer); } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createProducer(Destination)");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createProducer(Destination)", jmsProducer);  }  return (MessageProducer)jmsProducer; } public Queue createQueue(String name) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createQueue(String)", new Object[] { name });  checkSynchronousUsage("createQueue"); checkQueueDomain("createQueue"); JmsQueue jmsQueue = null; if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); if (null == name || 0 == name.length()) { JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0085", null); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createQueue(String)", (Throwable)je);  throw je; }  jmsQueue = this.providerFactory.getJmsFactory().createQueue(name, this); JmsDestinationImplProxy.setProviderDestination((JmsDestinationImpl)jmsQueue); } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createQueue(String)");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createQueue(String)", jmsQueue);  }  return (Queue)jmsQueue; } public StreamMessage createStreamMessage() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createStreamMessage()");  checkSynchronousUsage("createStreamMessage"); StreamMessage msg = null; if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); msg = new JmsStreamMessageImpl(this); } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createStreamMessage()");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createStreamMessage()", msg);  }  return msg; } public TemporaryQueue createTemporaryQueue() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createTemporaryQueue()");  checkSynchronousUsage("createTemporaryQueue"); checkQueueDomain("createTemporaryQueue"); if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try {  } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createTemporaryQueue()");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createTemporaryQueue()", null, 1);  }  } public TemporaryTopic createTemporaryTopic() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createTemporaryTopic()");  checkSynchronousUsage("createTemporaryTopic"); checkTopicDomain("createTemporaryTopic"); if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try {  } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createTemporaryTopic()");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createTemporaryTopic()", null, 1);  }  } protected JmsSessionImpl(boolean transacted, int acknowledgeMode, JmsConnectionImpl connection) throws JMSException { super((Map<String, Object>)connection, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3879 */     this.sendQueue = new LinkedBlockingDeque<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3888 */     this.asyncSendTimeoutPropertyDefault = 3000L;
/* 3889 */     this.maximumQueueDepth = 1000;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3896 */     this.asyncSendWaitTimeout = this.asyncSendTimeoutPropertyDefault;
/* 3897 */     this.queueToken = null;
/* 3898 */     this.queueProcessor2 = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3947 */     this.messageLoadingLock = new MessageLoadingLock(); if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "<init>(boolean,int,JmsConnectionImpl)", new Object[] { Boolean.valueOf(transacted), Integer.valueOf(acknowledgeMode), connection });  JmsConnectionMetaDataImpl md = (JmsConnectionMetaDataImpl)connection.getMetaData(); this.isCICSUnmanaged = md.doesConnectionSupport("XMSC_CAPABILITY_NATIVE_CICS_UNMANAGED"); this.isIMS = md.doesConnectionSupport("XMSC_CAPABILITY_NATIVE_IMS"); if (this.isCICSUnmanaged || this.isIMS) { if (!transacted && acknowledgeMode == 1) { this.transacted = false; this.acknowledgeMode = 1; } else { this.transacted = true; this.acknowledgeMode = 0; }  if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "<init>(boolean,int,JmsConnectionImpl)", "In CICS/IMS, transacted and acknowledgeMode might have changed. ", new Object[] { Boolean.valueOf(transacted), Integer.valueOf(acknowledgeMode) });  } else { this.transacted = transacted; this.acknowledgeMode = acknowledgeMode; }  this.connection = connection; this.providerFactory = connection.getProviderFactory(); this.connectionTypeName = connection.getConnectionTypeName(); this.dupsCommitThreshold = JmsConnectionImpl.DEFAULT_DUPS_THRESHOLD; this.producers = Collections.synchronizedSet(new HashSet<>()); this.syncConsumers = Collections.synchronizedSet(new HashSet<>()); this.asyncConsumers = Collections.synchronizedSet(new HashSet<>()); this.browsers = new ArrayList<>(); setIntProperty("XMSC_ACKNOWLEDGE_MODE", this.acknowledgeMode); setBooleanProperty("XMSC_TRANSACTED", this.transacted); try { setLongProperty("XMSC_OBJECT_IDENTITY", System.identityHashCode(this)); } catch (JMSException e) { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "<init>(boolean,int,JmsConnectionImpl)", "Caught expected exception", e);  }  PropertyStore.register("com.ibm.msg.client.jms.asyncSendWaitTimeout", this.asyncSendTimeoutPropertyDefault, Long.valueOf(0L), Long.valueOf(Long.MAX_VALUE)); this.asyncSendWaitTimeout = PropertyStore.getLongProperty("com.ibm.msg.client.jms.asyncSendWaitTimeout"); this.suppressBrowseWithMarkMsg = PropertyStore.getBooleanPropertyObject("com.ibm.msg.client.wmq.suppressBrowseMarkMessageWarning").booleanValue(); if (Trace.isOn) Trace.data(this, "<init>", "com.ibm.msg.client.wmq.suppressBrowseMarkMessageWarning = " + this.suppressBrowseWithMarkMsg, null);  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "<init>(boolean,int,JmsConnectionImpl)");  }
/*      */   public TextMessage createTextMessage() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createTextMessage()");  checkSynchronousUsage("createTextMessage"); TextMessage msg = null; if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); msg = new JmsTextMessageImpl(this); } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createTextMessage()");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createTextMessage()", msg);  }  return msg; }
/*      */   public TextMessage createTextMessage(String text) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createTextMessage(String)", new Object[] { text });  checkSynchronousUsage("createTextMessage"); TextMessage message = createTextMessage(); message.setText(text); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createTextMessage(String)", message);  return message; }
/* 3950 */   public Topic createTopic(String name) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createTopic(String)", new Object[] { name });  checkSynchronousUsage("createTopic"); checkTopicDomain("createTopic"); if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); if (null == name || 0 == name.length()) { JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0085", null); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createTopic(String)", (Throwable)je);  throw je; }  JmsTopic jmsTopic = this.providerFactory.getJmsFactory().createTopic(name, this); JmsDestinationImplProxy.setProviderDestination((JmsDestinationImpl)jmsTopic); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createTopic(String)", jmsTopic, 0);  return (Topic)jmsTopic; } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createTopic(String)");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createTopic(String)", null, 1);  }  } public int getAcknowledgeMode() throws JMSException { checkNotClosed(); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "getAcknowledgeMode()", "getter", Integer.valueOf(this.acknowledgeMode));  return this.acknowledgeMode; } public MessageListener getMessageListener() throws JMSException { if (this.isCICSUnmanaged || this.isIMS) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", "getMessageListener()"); String messageID = this.isCICSUnmanaged ? "JMSCC6001" : "JMSCC6011"; JMSException je = (JMSException)JmsErrorUtils.createException(messageID, inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "getMessageListener()", (Throwable)je, 1);  throw je; }  checkSynchronousUsage("getMessageListener"); checkNotClosed(); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "getMessageListener()", "getter", this.messageListener);  return this.messageListener; } public boolean getTransacted() throws JMSException { checkNotClosed(); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "getTransacted()", "getter", Boolean.valueOf(this.transacted));  return this.transacted; } public void recover() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "recover()");  if (this.isCICSUnmanaged || this.isIMS) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", "recover()"); String messageID = this.isCICSUnmanaged ? "JMSCC6002" : "JMSCC6012"; IllegalStateException ise = (IllegalStateException)JmsErrorUtils.createException(messageID, inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "recover()", (Throwable)ise, 3);  throw ise; }  JmsTls jTls = JmsTls.getInstance(); if (jTls.inCompletionListener() && jTls.completionListenerSession() == this) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", "recover()"); inserts.put("XMSC_INSERT_NAME", "completion"); IllegalStateException ise = (IllegalStateException)JmsErrorUtils.createException("JMSCC0012", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "recover()", (Throwable)ise, 2);  throw ise; }  checkSynchronousUsage("recover"); if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { Iterator<?> asyncConsumerIterator; HashMap<String, Object> inserts; JMSException je; checkNotClosed(); switch (this.acknowledgeMode) { case 1: case 3: asyncConsumerIterator = this.asyncConsumers.iterator(); while (asyncConsumerIterator.hasNext()) { Object msgConsumer = asyncConsumerIterator.next(); if (msgConsumer instanceof JmsMessageConsumerImpl) ((JmsMessageConsumerImpl)msgConsumer).recoverAsyncMessage();  } case 2: if (this.uncommittedReceiveCount > 0) rollbackTransaction();  break;case 0: inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", "recover"); je = (JMSException)JmsErrorUtils.createException("JMSCC0021", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "recover()", (Throwable)je, 3);  throw je; }  } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "recover()");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "recover()");  }  } public void rollback() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "rollback()");  if (this.isCICSUnmanaged || this.isIMS) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", "rollback()"); String messageID = this.isCICSUnmanaged ? "JMSCC6002" : "JMSCC6012"; IllegalStateException ise = (IllegalStateException)JmsErrorUtils.createException(messageID, inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "rollback()", (Throwable)ise, 3);  throw ise; }  JmsTls jTls = JmsTls.getInstance(); if (jTls.inCompletionListener() && jTls.completionListenerSession() == this) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", "rollback()"); inserts.put("XMSC_INSERT_NAME", "completion"); IllegalStateException ise = (IllegalStateException)JmsErrorUtils.createException("JMSCC0012", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "rollback()", (Throwable)ise, 2);  throw ise; }  checkSynchronousUsage("rollback"); if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); if (this.acknowledgeMode != 0) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", "rollback"); JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0014", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "rollback()", (Throwable)je, 3);  throw je; }  rollbackTransaction(); } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "rollback()");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "rollback()");  }  } public void setMessageListener(MessageListener listener) throws JMSException { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "setMessageListener(MessageListener)", "setter", listener);  if (this.isCICSUnmanaged || this.isIMS) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", "setMessageListener(MessageListener)"); String messageID = this.isCICSUnmanaged ? "JMSCC6001" : "JMSCC6011"; JMSException je = (JMSException)JmsErrorUtils.createException(messageID, inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "setMessageListener(MessageListener)", (Throwable)je, 1);  throw je; }  checkSynchronousUsage("setMessageListener"); if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); this.messageListener = listener; } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "setMessageListener(MessageListener)");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  }  } public void unsubscribe(String subscriptionName) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "unsubscribe(String)", new Object[] { subscriptionName });  checkSynchronousUsage("unsubscribe"); if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); if (this instanceof javax.jms.QueueSession) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", "unsubscribe(String)"); inserts.put("XMSC_INSERT_TYPE", getClass().getName()); JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1112", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "unsubscribe(String)", (Throwable)je, 1);  throw je; }  if (subscriptionName == null || "".equals(subscriptionName)) { JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0100", null); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "unsubscribe(String)", (Throwable)je, 2);  throw je; }  this.providerSession.deleteDurableSubscriber(subscriptionName); } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "unsubscribe(String)");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "unsubscribe(String)");  }  } public ProviderSession getProviderSession() { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "getProviderSession()", "getter", this.providerSession);  return this.providerSession; } public void setProviderSession(ProviderSession providerSession) { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "setProviderSession(ProviderSession)", "setter", providerSession);  this.providerSession = providerSession; } protected String getConnectionTypeName() { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "getConnectionTypeName()", "getter", this.connectionTypeName);  return this.connectionTypeName; } protected int getState() { int traceRet1 = this.state.getState(); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "getState()", "getter", Integer.valueOf(traceRet1));  return traceRet1; } protected ReentrantLock getOnMessageLock() { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "getOnMessageLock()", "getter", this.onMessageLock);  return this.onMessageLock; } protected JmsConnection getConnection() { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "getConnection()", "getter", this.connection);  return this.connection; } protected ProviderJmsFactory getJmsFactory() { ProviderJmsFactory traceRet1 = this.connection.getJmsFactory(); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "getJmsFactory()", "getter", traceRet1);  return traceRet1; } protected ProviderMessageFactory getMessageFactory() { ProviderMessageFactory traceRet1 = this.connection.getMessageFactory(); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "getMessageFactory()", "getter", traceRet1);  return traceRet1; } protected void start() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "start()");  this.sessionSyncLock.getExclusiveLock(); try { synchronized (this.state) { this.providerSession.start(); JMSException firstException = null; Object[] syncList = this.syncConsumers.toArray(); for (int i = 0; i < syncList.length; i++) { try { JmsMessageConsumerImpl consImpl = (JmsMessageConsumerImpl)syncList[i]; consImpl.start(); } catch (JMSException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "start()", (Throwable)e, 1);  if (firstException == null) firstException = e;  }  }  Iterator<?> asyncList = this.asyncConsumers.iterator(); while (asyncList.hasNext()) { try { JmsMessageConsumerImpl consImpl = (JmsMessageConsumerImpl)asyncList.next(); consImpl.start(); } catch (JMSException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "start()", (Throwable)e, 2);  if (firstException == null) firstException = e;  }  }  if (firstException != null) { if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "start()", (Throwable)firstException);  throw firstException; }  this.state.setState(1); }  } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "start()");  this.sessionSyncLock.unlockExclusiveLock(); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "start()");  }  } void loadMessageForSend(SendDetails newDetails) throws JMSException { if (Trace.isOn) {
/* 3951 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "loadMessageForSend(SendDetails)", new Object[] { newDetails });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 3956 */       synchronized (this.messageLoadingLock) {
/*      */         
/* 3958 */         if (this.queueProcessor2 == null) {
/*      */           
/* 3960 */           if (Trace.isOn) {
/* 3961 */             Trace.traceData(this, "No SendQueueProcessor2. Creating a new one", null);
/*      */           }
/*      */           
/* 3964 */           this.queueProcessor2 = new SendQueueProcessor2(this);
/*      */         } 
/*      */       } 
/*      */       
/* 3968 */       synchronized (this.queueProcessor2.sendQueueLock) {
/*      */         boolean messageQueued;
/* 3970 */         switch (this.queueProcessor2.getState()) {
/*      */ 
/*      */           
/*      */           case CLOSING:
/*      */           case CLOSED:
/* 3975 */             if (Trace.isOn) {
/* 3976 */               Trace.traceData(this, "No SendQueueProcessor2. Creating a new one", null);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 3981 */             this.queueProcessor2 = new SendQueueProcessor2(this, this.queueProcessor2.sendQueueLock);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case UNSTARTED:
/*      */             try {
/* 3988 */               this.queueToken = WorkQueueManager.enqueue(this.queueProcessor2, 0, false);
/*      */             }
/* 3990 */             catch (CSIException e) {
/* 3991 */               if (Trace.isOn) {
/* 3992 */                 Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "loadMessageForSend(SendDetails)", (Throwable)e, 1);
/*      */               }
/*      */               
/* 3995 */               JMSException je = new JMSException(e.getMessage());
/* 3996 */               je.setLinkedException((Exception)e);
/* 3997 */               if (Trace.isOn) {
/* 3998 */                 Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "loadMessageForSend(SendDetails)", (Throwable)je, 1);
/*      */               }
/*      */               
/* 4001 */               throw je;
/*      */             } 
/*      */ 
/*      */ 
/*      */           
/*      */           case ACTIVE:
/*      */           case UNLOCKED:
/* 4008 */             if (Trace.isOn) {
/* 4009 */               Trace.traceData(this, "Putting details to sendQueue before starting sendQueueProcessor2", newDetails);
/*      */             }
/* 4011 */             messageQueued = false;
/*      */             try {
/* 4013 */               messageQueued = this.queueProcessor2.offer(newDetails, this.asyncSendWaitTimeout, TimeUnit.MILLISECONDS);
/*      */             }
/* 4015 */             catch (InterruptedException ie) {
/* 4016 */               if (Trace.isOn) {
/* 4017 */                 Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "loadMessageForSend(SendDetails)", ie, 2);
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 4025 */               HashMap<String, Object> inserts = new HashMap<>();
/* 4026 */               JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC5006", inserts);
/* 4027 */               je.setLinkedException(ie);
/* 4028 */               if (Trace.isOn) {
/* 4029 */                 Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "loadMessageForSend(SendDetails)", (Throwable)je, 2);
/*      */               }
/*      */               
/* 4032 */               throw je;
/*      */             } 
/*      */             
/* 4035 */             if (!messageQueued) {
/*      */ 
/*      */               
/* 4038 */               HashMap<String, Object> inserts = new HashMap<>();
/* 4039 */               JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC5006", inserts);
/*      */               
/* 4041 */               if (Trace.isOn) {
/* 4042 */                 Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "loadMessageForSend(SendDetails)", (Throwable)je, 3);
/*      */               }
/*      */               
/* 4045 */               throw je;
/*      */             } 
/*      */ 
/*      */             
/* 4049 */             if (this.queueProcessor2.getState() == ProcessorState.UNLOCKED || this.queueProcessor2.getState() == ProcessorState.UNSTARTED) {
/*      */               
/*      */               try {
/* 4052 */                 ProcessorState currentState = this.queueProcessor2.getState();
/*      */ 
/*      */ 
/*      */                 
/* 4056 */                 long waitTime = this.asyncSendWaitTimeout;
/* 4057 */                 long startTime = System.currentTimeMillis();
/* 4058 */                 this.queueProcessor2.sendQueueLock.wait(waitTime);
/* 4059 */                 long endTime = System.currentTimeMillis();
/*      */                 
/* 4061 */                 ProcessorState newState = this.queueProcessor2.getState();
/*      */                 
/* 4063 */                 if (newState != ProcessorState.ACTIVE && endTime - startTime >= waitTime)
/*      */                 {
/*      */ 
/*      */                   
/* 4067 */                   if (Trace.isOn) {
/* 4068 */                     Trace.data(this, "loadMessageForSend(SendDetails)", "Timed out waiting for SendQueueProcessor to start", 
/*      */ 
/*      */ 
/*      */                         
/* 4072 */                         Long.valueOf(waitTime));
/*      */                   }
/*      */ 
/*      */ 
/*      */                   
/* 4077 */                   this.queueProcessor2.close();
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 4082 */                   this.queueProcessor2 = null;
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 4087 */                   HashMap<String, String> data = new HashMap<>();
/* 4088 */                   data.put("original queueProcessorState", currentState.toString());
/* 4089 */                   data.put("final queueProcessorState", newState.toString());
/* 4090 */                   data.put("timeout", Long.toBinaryString(waitTime));
/*      */                   
/* 4092 */                   Trace.ffst(this, "loadMessageForSend(SendDetails)", "XJ003002", data, null);
/*      */ 
/*      */ 
/*      */                   
/* 4096 */                   HashMap<String, Object> inserts = new HashMap<>();
/* 4097 */                   JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC5009", inserts);
/*      */                   
/* 4099 */                   if (Trace.isOn) {
/* 4100 */                     Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "loadMessageForSend(SendDetails)", (Throwable)je, 4);
/*      */                   }
/*      */                   
/* 4103 */                   throw je;
/*      */                 
/*      */                 }
/*      */               
/*      */               }
/* 4108 */               catch (InterruptedException ie) {
/* 4109 */                 if (Trace.isOn) {
/* 4110 */                   Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "loadMessageForSend(SendDetails)", ie, 3);
/*      */                 }
/*      */               } 
/*      */             }
/*      */             break;
/*      */         } 
/*      */ 
/*      */       
/*      */       } 
/*      */     } finally {
/* 4120 */       if (Trace.isOn) {
/* 4121 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "loadMessageForSend(SendDetails)");
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 4126 */     if (Trace.isOn)
/* 4127 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "loadMessageForSend(SendDetails)");  } void stop() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "stop()");  stop(true); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "stop()");  } private void stop(boolean changeState) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "stop(boolean)", new Object[] { Boolean.valueOf(changeState) });  this.providerSession.stop(); if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { Object[] cons; if (changeState) this.state.setState(2);  JMSException firstException = null; synchronized (this.consumerListsLock) { synchronized (this.syncConsumers) { Iterator<?> iterator = this.syncConsumers.iterator(); while (iterator.hasNext()) { try { ((JmsMessageConsumerImpl)iterator.next()).stopUnconditional(); } catch (JMSException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "stop(boolean)", (Throwable)e, 1);  if (firstException == null) firstException = e;  }  }  }  cons = this.asyncConsumers.toArray(); }  for (int i = 0; i < cons.length; i++) { try { ((JmsMessageConsumerImpl)cons[i]).stopUnconditional(); } catch (JMSException e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "stop(boolean)", (Throwable)e, 2);  if (firstException == null) firstException = e;  }  }  if (this.queueToken != null) { if (Trace.isOn) Trace.traceData(this, "pausing sendQueueProcessor", this.queueToken);  this.queueToken.pause(); }  if (firstException != null) { if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "stop(boolean)", (Throwable)firstException);  throw firstException; }  } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "stop(boolean)");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "stop(boolean)");  }  } public int getProducerCount() { int traceRet1 = this.producers.size(); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "getProducerCount()", "getter", Integer.valueOf(traceRet1));  return traceRet1; } public int getConsumerCount() { int consumerCount = this.syncConsumers.size(); consumerCount += this.asyncConsumers.size(); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "getConsumerCount()", "getter", Integer.valueOf(consumerCount));  return consumerCount; } void removeProducer(JmsMessageProducerImpl producer) { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "removeProducer(JmsMessageProducerImpl)", new Object[] { producer });  this.producers.remove(producer); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "removeProducer(JmsMessageProducerImpl)");  } void removeConsumer(JmsMessageConsumerImpl consumer) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "removeConsumer(JmsMessageConsumerImpl)", new Object[] { consumer });  if (this.acknowledgeMode == 3 && this.uncommittedReceiveCount > 0) this.providerSession.commit();  this.syncConsumers.remove(consumer); this.asyncConsumers.remove(consumer); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "removeConsumer(JmsMessageConsumerImpl)");  } void removeBrowser(JmsQueueBrowserImpl browser) { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "removeBrowser(JmsQueueBrowserImpl)", new Object[] { browser });  synchronized (this.browsers) { this.browsers.remove(browser); }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "removeBrowser(JmsQueueBrowserImpl)");  } void notifyMessagePreConsume(boolean isTransacted) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "notifyMessagePreConsume(boolean)", new Object[] { Boolean.valueOf(isTransacted) });  if (!isTransacted) this.uncommittedReceiveCount++;  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "notifyMessagePreConsume(boolean)");  } void notifyMessagePostConsume() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "notifyMessagePostConsume()");  if (!(this instanceof com.ibm.msg.client.jms.JmsXASession) && !this.providerSession.isInGlobalTransaction() && ((this.acknowledgeMode == 3 && this.uncommittedReceiveCount >= this.dupsCommitThreshold) || this.acknowledgeMode == 1)) { this.providerSession.commit(); this.uncommittedReceiveCount = 0; }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "notifyMessagePostConsume()");  } void notifyMessageConsumed(boolean isXA) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "notifyMessageConsumed(boolean)", new Object[] { Boolean.valueOf(isXA) });  notifyMessagePreConsume(isXA); notifyMessagePostConsume(); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "notifyMessageConsumed(boolean)");  } int getAndResetCommitCount() { int currentUncommittedReceiveCount = this.uncommittedReceiveCount; this.uncommittedReceiveCount = 0; if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "getAndResetCommitCount()", "getter", Integer.valueOf(currentUncommittedReceiveCount));  return currentUncommittedReceiveCount; } void commitTransaction() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "commitTransaction()");  this.providerSession.commit(); this.uncommittedReceiveCount = 0; if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "commitTransaction()");  } void rollbackTransaction() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "rollbackTransaction()");  this.providerSession.rollback(); this.uncommittedReceiveCount = 0; if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "rollbackTransaction()");  } boolean isAsync() { boolean traceRet1 = (!this.asyncConsumers.isEmpty() && this.state.getState() == 1); traceRet1 = (traceRet1 || Thread.currentThread() == this.asyncPutThread); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "isAsync()", "getter", Boolean.valueOf(traceRet1));  return traceRet1; } void checkSynchronousUsage(String methodName) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "checkSynchronousUsage(String)", new Object[] { methodName });  if ((isAsync() && !this.onMessageLock.isHeldByCurrentThread()) || Thread.currentThread() == this.asyncPutThread) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", methodName); JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0033", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "checkSynchronousUsage(String)", (Throwable)je);  throw je; }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "checkSynchronousUsage(String)");  } void registerSyncConsumer(MessageConsumer consumer) { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "registerSyncConsumer(MessageConsumer)", new Object[] { consumer });  synchronized (this.consumerListsLock) { this.asyncConsumers.remove(consumer); if (!this.syncConsumers.contains(consumer)) this.syncConsumers.add((JmsMessageConsumerImpl)consumer);  }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "registerSyncConsumer(MessageConsumer)");  } void registerAsyncConsumer(MessageConsumer consumer) { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "registerAsyncConsumer(MessageConsumer)", new Object[] { consumer });  synchronized (this.consumerListsLock) { this.syncConsumers.remove(consumer); if (!this.asyncConsumers.contains(consumer)) this.asyncConsumers.add((JmsMessageConsumerImpl)consumer);  }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "registerAsyncConsumer(MessageConsumer)");  } ReentrantDoubleLock getSessionSyncLock() { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "getSessionSyncLock()", "getter", this.sessionSyncLock);  return this.sessionSyncLock; } public boolean isClosed() { boolean traceRet1 = this.state.isClosed(); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "isClosed()", "getter", Boolean.valueOf(traceRet1));  return traceRet1; } protected void checkNotClosed() throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "checkNotClosed()");  this.state.checkNotClosed("JMSCC0020"); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "checkNotClosed()");  } private void checkNotMixedDomain(Destination dest, String method) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "checkNotMixedDomain(Destination,String)", new Object[] { dest, method });  boolean topicDomainOnly = (this instanceof javax.jms.TopicSession || this instanceof javax.jms.XATopicSession); boolean queueDomainOnly = (this instanceof javax.jms.QueueSession || this instanceof javax.jms.XAQueueSession); if ((topicDomainOnly && dest instanceof Queue) || (queueDomainOnly && dest instanceof Topic)) { String sessionType; HashMap<String, Object> inserts = new HashMap<>(); String failingMethod = method; if ("createConsumer".equals(method) && this instanceof javax.jms.TopicSession) { failingMethod = "createSubscriber"; } else if ("createConsumer".equals(method) && this instanceof javax.jms.QueueSession) { failingMethod = "createReceiver"; }  if ("createProducer".equals(method) && this instanceof javax.jms.TopicSession) failingMethod = "createPublisher";  if ("createProducer".equals(method) && this instanceof javax.jms.QueueSession) failingMethod = "createSender";  inserts.put("XMSC_INSERT_METHOD", failingMethod); inserts.put("XMSC_INSERT_TYPE", dest.getClass().getName()); if (this instanceof javax.jms.XASession) { sessionType = topicDomainOnly ? "javax.jms.XATopicSession" : "javax.jms.XAQueueSession"; } else { sessionType = topicDomainOnly ? "javax.jms.TopicSession" : "javax.jms.QueueSession"; }  inserts.put("XMSC_INSERT_SESSION", sessionType); JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1113", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "checkNotMixedDomain(Destination,String)", (Throwable)je);  throw je; }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "checkNotMixedDomain(Destination,String)");  } private void checkQueueDomain(String method) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "checkQueueDomain(String)", new Object[] { method });  if (this instanceof javax.jms.TopicSession || this instanceof javax.jms.XATopicSession) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", method); inserts.put("XMSC_INSERT_TYPE", "TopicSession"); JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1112", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "checkQueueDomain(String)", (Throwable)je);  throw je; }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "checkQueueDomain(String)");  } private void checkTopicDomain(String method) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "checkTopicDomain(String)", new Object[] { method });  if (this instanceof javax.jms.QueueSession || this instanceof javax.jms.XAQueueSession) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", method); inserts.put("XMSC_INSERT_TYPE", "QueueSession"); JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1112", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "checkTopicDomain(String)", (Throwable)je);  throw je; }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "checkTopicDomain(String)");  } public void run() { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "run()");  if (this.isCICSUnmanaged || this.isIMS) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", "run()"); String messageID = this.isCICSUnmanaged ? "JMSCC6001" : "JMSCC6011"; DetailedJMSException e = (DetailedJMSException)JmsErrorUtils.createException(messageID, inserts); JMSRuntimeException re = e.getUnchecked(); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "run()", (Throwable)re, 5);  throw re; }  if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { try { if (this.state.getState() == 3) { if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "run()", 1);  return; }  if (this.messageReferences != null && this.messageListener != null) for (int i = 0; i < this.messageReferences.size(); i++) { boolean onMessageThrewException = false; Message message = null; try { JmsMessageReferenceImpl msgRef = (JmsMessageReferenceImpl)this.messageReferences.get(i); message = consume(msgRef); if (message != null) { this.uncommittedReceiveCount++; setBooleanProperty("ASF_Message_Went_Missing", false); try { this.messageListener.onMessage(message); } catch (Exception e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "run()", e, 1);  onMessageThrewException = true; if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "run()", e, 2);  throw e; } catch (Error error) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "run()", error, 2);  if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "run()", error, 4);  onMessageThrewException = true; if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "run()", error, 3);  throw error; }  if (!this.transacted && !(this instanceof com.ibm.msg.client.jms.JmsXASession) && this.acknowledgeMode != 2 && !this.providerSession.isInGlobalTransaction()) { this.uncommittedReceiveCount = 0; this.providerSession.commit(); }  } else { setBooleanProperty("ASF_Message_Went_Missing", true); if (!this.nullMsgOnDelivery) { if (this.suppressBrowseWithMarkMsg) { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "run()", "Marked message not found, but browse-with-mark warning message is suppressed by user", null);  } else { JmsErrorUtils.log(this, "run()", "JMSCC0108", null); }  this.nullMsgOnDelivery = true; }  }  } catch (Exception e) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "run()", e, 3);  String messageid = "JMSCC0110"; if (onMessageThrewException) messageid = "JMSCC0109";  HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_EXCEPTION", e); if (!onMessageThrewException || !this.onMessageHasThrownException) { if (onMessageThrewException) this.onMessageHasThrownException = true;  JmsErrorUtils.log(this, "run()", messageid, inserts); }  if (!this.transacted && !(this instanceof com.ibm.msg.client.jms.JmsXASession) && this.acknowledgeMode != 2 && this.uncommittedReceiveCount > 0 && !this.providerSession.isInGlobalTransaction()) { try { this.providerSession.rollback(); } catch (JMSException je2) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "run()", (Throwable)je2, 4);  this.connection.reportException(je2, false); HashMap<String, Object> inserts2 = new HashMap<>(); inserts2.put("XMSC_INSERT_EXCEPTION", je2); JmsErrorUtils.log(this, "run()", "JMSCC0110", inserts2); }  this.uncommittedReceiveCount = 0; }  if (e instanceof RuntimeException) { RuntimeException traceRet1 = (RuntimeException)e; if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "run()", traceRet1, 4);  throw traceRet1; }  RuntimeException re = (RuntimeException)JmsErrorUtils.createException(messageid, inserts); re.initCause(e); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "run()", re, 5);  throw re; }  }   } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "run()", 1);  this.messageReferences = null; }  } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "run()", 2);  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "run()", 2);  }  } public void deliver(List<JmsMessageReference> messageReferences2) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "deliver(List<JmsMessageReference>)", new Object[] { messageReferences2 });  if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { if (this.messageReferences != null) { HashMap<String, Object> inserts = new HashMap<>(); inserts.put("XMSC_INSERT_METHOD", "deliver"); JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0034", inserts); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "deliver(List<JmsMessageReference>)", (Throwable)je);  throw je; }  this.messageReferences = messageReferences2; } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "deliver(List<JmsMessageReference>)");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  }  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "deliver(List<JmsMessageReference>)");  } public JmsMessageReference recreateMessageReference(byte[] flatMR) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "recreateMessageReference(byte [ ])", new Object[] { flatMR });  if (null == flatMR || 0 == flatMR.length) { JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1096", null); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "recreateMessageReference(byte [ ])", (Throwable)je);  throw je; }  if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); String destURI = this.providerSession.getDestinationURI(flatMR); ProviderDestination provdest = null; if (destURI != null) { JmsDestination dest = this.providerFactory.getJmsFactory().createDestination(destURI, this); JmsDestinationImpl destimpl = (JmsDestinationImpl)dest; JmsDestinationImplProxy.setProviderDestination(destimpl); provdest = JmsDestinationImplProxy.getProviderDestination(destimpl); }  ProviderMessageReference pmr = this.providerSession.recreateMessageReference(flatMR, provdest); if (pmr.isManagedQueue()) provdest.setDestType(3);  JmsMessageReference jmr = new JmsMessageReferenceImpl(this.connectionTypeName, pmr); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "recreateMessageReference(byte [ ])", jmr, 0);  return jmr; } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "recreateMessageReference(byte [ ])");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "recreateMessageReference(byte [ ])", null, 1);  }  } protected void enableMessageReferenceProcessing() { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "enableMessageReferenceProcessing()");  this.providerSession.enableMessageReferenceProcessing(); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "enableMessageReferenceProcessing()");  } private Message consume(JmsMessageReference msgRef) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "consume(JmsMessageReference)", new Object[] { msgRef });  if (null == msgRef || !(msgRef instanceof JmsMessageReferenceImpl)) { JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1096", null); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "consume(JmsMessageReference)", (Throwable)je);  throw je; }  JmsMessageReferenceImpl jmsMsgRef = (JmsMessageReferenceImpl)msgRef; ProviderMessageReference provMsgRef = jmsMsgRef.getDelegate(); String destURI = provMsgRef.getDestinationAsString(); if (null != destURI) { JmsDestination dest = this.providerFactory.getJmsFactory().createDestination(destURI, this); JmsDestinationImpl destimpl = (JmsDestinationImpl)dest; JmsDestinationImplProxy.setProviderDestination(destimpl); ProviderDestination provdest = JmsDestinationImplProxy.getProviderDestination(destimpl); if (provMsgRef.isManagedQueue()) provdest.setDestType(3);  provMsgRef.setDestination(provdest); }  this.providerSession.loadMessageReference(jmsMsgRef.getDelegate()); Message message = jmsMsgRef.getMessage(this); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "consume(JmsMessageReference)", message);  return message; } public Message consume(byte[] flatMR) throws JMSException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "consume(byte [ ])", new Object[] { flatMR });  if (null == flatMR || flatMR.length == 0) { JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1096", null); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "consume(byte [ ])", (Throwable)je);  throw je; }  Message message = null; if (!isAsync()) this.sessionSyncLock.getExclusiveLock();  try { checkNotClosed(); JmsMessageReference msgRef = recreateMessageReference(flatMR); message = consume(msgRef); notifyMessageConsumed(this instanceof com.ibm.msg.client.jms.JmsXASession); } finally { if (Trace.isOn) Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "consume(byte [ ])");  if (this.sessionSyncLock.hasExclusiveLock()) this.sessionSyncLock.unlockExclusiveLock();  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "consume(byte [ ])", message);  }  return message; }
/*      */   public boolean equals(Object obj) { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "equals(Object)", new Object[] { obj });  boolean traceRet1 = super.equals(obj); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "equals(Object)", Boolean.valueOf(traceRet1));  return traceRet1; }
/*      */   public int hashCode() { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "hashCode()");  int traceRet1 = super.hashCode(); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "hashCode()", Integer.valueOf(traceRet1));  return traceRet1; }
/*      */   private void writeObject(ObjectOutputStream out) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "writeObject(ObjectOutputStream)", new Object[] { out });  NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsSession"); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "writeObject(ObjectOutputStream)", traceRet1);  throw traceRet1; }
/*      */   private void readObject(ObjectInputStream in) throws IOException { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "readObject(ObjectInputStream)", new Object[] { in });  NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsSession"); if (Trace.isOn) Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "readObject(ObjectInputStream)", traceRet1);  throw traceRet1; }
/*      */   public void clearMessageReferences() { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "clearMessageReferences()");  if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "clearMessageReferences()", new Object[] { Integer.valueOf(this.messageReferences.size()) });  this.messageReferences = null; if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "clearMessageReferences()");  if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "clearMessageReferences()");  }
/*      */   class SendDetails {
/*      */     JmsMessageProducerImpl producer; boolean inIdentifiedContext; JmsDestinationImpl dest; Message originalMessage; Message sendingMessage; int deliveryMode; int priority; long timeToLive; long deliveryDelay; CompletionListener completionListener; boolean validateProperties;
/*      */     public SendDetails(JmsMessageProducerImpl producer, boolean inIdentifiedContext, JmsDestinationImpl dest, Message originalMessage, Message sendingMessage, int deliveryMode, int priority, long timeToLive, long deliveryDelay, boolean validateProperties, CompletionListener completionListener) { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.SendDetails", "<init>(JmsMessageProducerImpl,boolean,JmsDestinationImpl,Message,Message,int,int,long,long,boolean,CompletionListener)", new Object[] { producer, Boolean.valueOf(inIdentifiedContext), dest, originalMessage, sendingMessage, Integer.valueOf(deliveryMode), Integer.valueOf(priority), Long.valueOf(timeToLive), Long.valueOf(deliveryDelay), Boolean.valueOf(validateProperties), completionListener });  this.producer = producer; this.inIdentifiedContext = inIdentifiedContext; this.dest = dest; this.originalMessage = originalMessage; this.sendingMessage = sendingMessage; this.deliveryMode = deliveryMode; this.priority = priority; this.timeToLive = timeToLive; this.deliveryDelay = deliveryDelay; this.completionListener = completionListener; this.validateProperties = validateProperties; if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.SendDetails", "<init>(JmsMessageProducerImpl,boolean,JmsDestinationImpl,Message,Message,int,int,long,long,boolean,CompletionListener)");  }
/*      */     public String toString() { if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.SendDetails", "toString()");  StringBuffer sb = new StringBuffer(); sb.append("producer = " + this.producer + "\n"); sb.append("inIdentifiedContext = " + this.inIdentifiedContext + "\n"); sb.append("dest = " + this.dest + "\n"); sb.append("deliveryMode = " + this.deliveryMode + "\n"); sb.append("priority = " + this.priority + "\n"); sb.append("deliveryDelay = " + this.deliveryDelay + "\n"); sb.append("timeToLive = " + this.timeToLive + "\n"); sb.append("completionListener = " + this.completionListener + "\n"); sb.append("validateProperties = " + this.validateProperties + "\n"); String traceRet1 = sb.toString(); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.SendDetails", "toString()", traceRet1);  return traceRet1; } }
/*      */   int getMaxQueueDepth() { PropertyStore.register("com.ibm.msg.client.jms.asyncSendMaxBufferSize", 1000L, Long.valueOf(0L), Long.valueOf(2147483647L)); this.maximumQueueDepth = PropertyStore.getLongPropertyObject("com.ibm.msg.client.jms.asyncSendMaxBufferSize").intValue(); if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "getMaxQueueDepth()", "getter", Integer.valueOf(this.maximumQueueDepth));  return this.maximumQueueDepth; }
/*      */   void setMaxQueueDepth(int newMaxQueueDepth) { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "setMaxQueueDepth(int)", "setter", Integer.valueOf(newMaxQueueDepth));  this.maximumQueueDepth = newMaxQueueDepth; }
/*      */   long getAsyncSendWaitTimeout() { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "getAsyncSendWaitTimeout()", "getter", Long.valueOf(this.asyncSendWaitTimeout));  return this.asyncSendWaitTimeout; }
/*      */   void setAsyncSendWaitTimeout(long newAsyncSendWaitTimeout) { if (Trace.isOn) Trace.data(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "setAsyncSendWaitTimeout(long)", "setter", Long.valueOf(newAsyncSendWaitTimeout));  this.asyncSendWaitTimeout = newAsyncSendWaitTimeout; }
/*      */   private static class MessageLoadingLock {
/*      */     private MessageLoadingLock() {} }
/* 4143 */   public MessageConsumer createDurableConsumer(Topic topic, String subscriptionName) throws InvalidDestinationException, IllegalStateException, JMSException { if (Trace.isOn) {
/* 4144 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createDurableConsumer(Topic,String)", new Object[] { topic, subscriptionName });
/*      */     }
/*      */ 
/*      */     
/* 4148 */     this.connection.checkValidAPIUsage("createDurableConsumer(Topic,String)");
/*      */     
/* 4150 */     TopicSubscriber topicSubscriber = createDurableSubscriber(topic, subscriptionName);
/* 4151 */     if (Trace.isOn) {
/* 4152 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createDurableConsumer(Topic,String)", topicSubscriber);
/*      */     }
/*      */     
/* 4155 */     return (MessageConsumer)topicSubscriber; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageConsumer createDurableConsumer(Topic topic, String subscriptionName, String selector, boolean noLocal) throws InvalidDestinationException, InvalidSelectorException, IllegalStateException, JMSException {
/* 4168 */     if (Trace.isOn) {
/* 4169 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createDurableConsumer(Topic,String,String,boolean)", new Object[] { topic, subscriptionName, selector, 
/* 4170 */             Boolean.valueOf(noLocal) });
/*      */     }
/*      */     
/* 4173 */     this.connection.checkValidAPIUsage("createDurableConsumer(Topic,String,String,boolean)");
/*      */     
/* 4175 */     TopicSubscriber topicSubscriber = createDurableSubscriber(topic, subscriptionName, selector, noLocal);
/* 4176 */     if (Trace.isOn) {
/* 4177 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createDurableConsumer(Topic,String,String,boolean)", topicSubscriber);
/*      */     }
/*      */     
/* 4180 */     return (MessageConsumer)topicSubscriber;
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
/* 4191 */     if (Trace.isOn) {
/* 4192 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedConsumer(Topic,String)", new Object[] { topic, sharedSubscriptionName });
/*      */     }
/*      */ 
/*      */     
/* 4196 */     this.connection.checkValidAPIUsage("createSharedConsumer(Topic,String)");
/*      */ 
/*      */     
/* 4199 */     checkSynchronousUsage("createSharedConsumer");
/*      */ 
/*      */     
/* 4202 */     checkNotMixedDomain((Destination)topic, "createSharedConsumer");
/*      */     
/* 4204 */     MessageConsumer traceRet1 = createSharedConsumer(topic, sharedSubscriptionName, (String)null);
/* 4205 */     if (Trace.isOn) {
/* 4206 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedConsumer(Topic,String)", traceRet1);
/*      */     }
/*      */     
/* 4209 */     return traceRet1;
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
/*      */   public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName, String selector) throws JMSException, InvalidDestinationException, InvalidSelectorException {
/* 4221 */     if (Trace.isOn) {
/* 4222 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedConsumer(Topic,String,String)", new Object[] { topic, sharedSubscriptionName, selector });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4227 */     this.connection.checkValidAPIUsage("createSharedConsumer(Topic,String,String)");
/*      */ 
/*      */     
/* 4230 */     checkSynchronousUsage("createConsumer");
/*      */ 
/*      */     
/* 4233 */     checkTopicDomain("createSharedConsumer");
/*      */     
/* 4235 */     JmsTopicSubscriberImpl jmsSubscriber = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4242 */     if (!isAsync()) {
/* 4243 */       this.sessionSyncLock.getExclusiveLock();
/*      */     }
/*      */     try {
/* 4246 */       checkNotClosed();
/*      */       
/* 4248 */       if (!(topic instanceof JmsDestinationImpl)) {
/* 4249 */         HashMap<String, Object> inserts = new HashMap<>();
/* 4250 */         inserts.put("XMSC_DESTINATION_NAME", topic);
/* 4251 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0099", inserts);
/* 4252 */         if (Trace.isOn) {
/* 4253 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedConsumer(Topic,String,String)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 4256 */         throw je;
/*      */       } 
/*      */       
/* 4259 */       JmsDestinationImpl jmsTopic = (JmsDestinationImpl)topic;
/*      */       
/* 4261 */       if (!jmsTopic.getStringProperty("XMSC_CONNECTION_TYPE_NAME").equals(this.connectionTypeName)) {
/* 4262 */         HashMap<String, Object> inserts = new HashMap<>();
/* 4263 */         inserts.put("XMSC_CONNECTION_TYPE_NAME", this.connectionTypeName);
/* 4264 */         inserts.put("XMSC_CONNECTION_TYPE", jmsTopic.getStringProperty("XMSC_CONNECTION_TYPE_NAME"));
/* 4265 */         JMSException je2 = (JMSException)JmsErrorUtils.createException("JMSCC0092", inserts);
/* 4266 */         if (Trace.isOn) {
/* 4267 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedConsumer(Topic,String,String)", (Throwable)je2, 2);
/*      */         }
/*      */         
/* 4270 */         throw je2;
/*      */       } 
/*      */       
/* 4273 */       if (topic instanceof JmsTemporaryTopicImpl && ((JmsTemporaryTopicImpl)topic).getConnection() != this.connection) {
/* 4274 */         HashMap<String, Object> inserts = new HashMap<>();
/* 4275 */         inserts.put("XMSC_DESTINATION_NAME", topic);
/* 4276 */         JMSException je2 = (JMSException)JmsErrorUtils.createException("JMSCC3003", inserts);
/* 4277 */         if (Trace.isOn) {
/* 4278 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedConsumer(Topic,String,String)", (Throwable)je2, 3);
/*      */         }
/*      */         
/* 4281 */         throw je2;
/*      */       } 
/*      */       
/* 4284 */       if (sharedSubscriptionName == null || "".equals(sharedSubscriptionName)) {
/* 4285 */         JMSException je4 = (JMSException)JmsErrorUtils.createException("JMSCC0100", null);
/* 4286 */         if (Trace.isOn) {
/* 4287 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedConsumer(Topic,String,String)", (Throwable)je4, 4);
/*      */         }
/*      */         
/* 4290 */         throw je4;
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
/* 4301 */       synchronized (this.state)
/*      */       {
/* 4303 */         jmsSubscriber = new JmsTopicSubscriberImpl(this, topic, selector, false, sharedSubscriptionName, true, false);
/*      */         
/* 4305 */         ProviderDestination providerDestination = JmsDestinationImplProxy.getProviderDestination(jmsTopic);
/*      */         
/* 4307 */         ProviderMessageConsumer consumer = this.providerSession.createSharedConsumer(providerDestination, sharedSubscriptionName, selector, jmsSubscriber);
/*      */ 
/*      */ 
/*      */         
/* 4311 */         jmsSubscriber.setProviderConsumer(consumer);
/*      */ 
/*      */         
/* 4314 */         if (getState() == 1) {
/* 4315 */           jmsSubscriber.start();
/*      */         } else {
/*      */           
/* 4318 */           jmsSubscriber.stop();
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4327 */         this.syncConsumers.add(jmsSubscriber);
/*      */ 
/*      */ 
/*      */         
/* 4331 */         JmsDestinationImplProxy.incrementUseCount((JmsDestinationImpl)topic);
/*      */       }
/*      */     
/*      */     } finally {
/*      */       
/* 4336 */       if (Trace.isOn) {
/* 4337 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedConsumer(Topic,String,String)");
/*      */       }
/*      */       
/* 4340 */       if (this.sessionSyncLock.hasExclusiveLock()) {
/* 4341 */         this.sessionSyncLock.unlockExclusiveLock();
/*      */       }
/*      */       
/* 4344 */       if (Trace.isOn) {
/* 4345 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedConsumer(Topic,String,String)", jmsSubscriber);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 4350 */     return (MessageConsumer)jmsSubscriber;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MessageConsumer createSharedDurableConsumer(Topic topic, String sharedSubscriptionName) throws JMSException, InvalidDestinationException {
/* 4361 */     if (Trace.isOn) {
/* 4362 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedDurableConsumer(Topic,String)", new Object[] { topic, sharedSubscriptionName });
/*      */     }
/*      */ 
/*      */     
/* 4366 */     this.connection.checkValidAPIUsage("createSharedDurableConsumer(Topic,String)");
/*      */ 
/*      */     
/* 4369 */     checkSynchronousUsage("createSharedDurableConsumer");
/*      */ 
/*      */     
/* 4372 */     checkNotMixedDomain((Destination)topic, "createSharedConsumer");
/*      */     
/* 4374 */     MessageConsumer traceRet1 = createSharedDurableConsumer(topic, sharedSubscriptionName, (String)null);
/* 4375 */     if (Trace.isOn) {
/* 4376 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedDurableConsumer(Topic,String)", traceRet1);
/*      */     }
/*      */     
/* 4379 */     return traceRet1;
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
/*      */   public MessageConsumer createSharedDurableConsumer(Topic topic, String sharedSubscriptionName, String selector) throws InvalidDestinationException, IllegalStateException, JMSException {
/* 4391 */     if (Trace.isOn) {
/* 4392 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedDurableConsumer(Topic,String,String)", new Object[] { topic, sharedSubscriptionName, selector });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 4397 */     this.connection.checkValidAPIUsage("createSharedDurableConsumer(Topic,String,String)");
/*      */ 
/*      */     
/* 4400 */     checkSynchronousUsage("createConsumer");
/*      */ 
/*      */     
/* 4403 */     checkTopicDomain("createSharedDurableConsumer");
/*      */     
/* 4405 */     JmsTopicSubscriberImpl jmsSubscriber = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4412 */     if (!isAsync()) {
/* 4413 */       this.sessionSyncLock.getExclusiveLock();
/*      */     }
/*      */     try {
/* 4416 */       checkNotClosed();
/*      */       
/* 4418 */       if (!(topic instanceof JmsDestinationImpl)) {
/* 4419 */         HashMap<String, Object> inserts = new HashMap<>();
/* 4420 */         inserts.put("XMSC_DESTINATION_NAME", topic);
/* 4421 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0099", inserts);
/* 4422 */         if (Trace.isOn) {
/* 4423 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedDurableConsumer(Topic,String,String)", (Throwable)je, 1);
/*      */         }
/*      */         
/* 4426 */         throw je;
/*      */       } 
/*      */       
/* 4429 */       JmsDestinationImpl jmsTopic = (JmsDestinationImpl)topic;
/*      */       
/* 4431 */       if (!jmsTopic.getStringProperty("XMSC_CONNECTION_TYPE_NAME").equals(this.connectionTypeName)) {
/* 4432 */         HashMap<String, Object> inserts = new HashMap<>();
/* 4433 */         inserts.put("XMSC_CONNECTION_TYPE_NAME", this.connectionTypeName);
/* 4434 */         inserts.put("XMSC_CONNECTION_TYPE", jmsTopic.getStringProperty("XMSC_CONNECTION_TYPE_NAME"));
/* 4435 */         JMSException je2 = (JMSException)JmsErrorUtils.createException("JMSCC0092", inserts);
/* 4436 */         if (Trace.isOn) {
/* 4437 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedDurableConsumer(Topic,String,String)", (Throwable)je2, 2);
/*      */         }
/*      */         
/* 4440 */         throw je2;
/*      */       } 
/*      */       
/* 4443 */       if (topic instanceof JmsTemporaryTopicImpl && ((JmsTemporaryTopicImpl)topic).getConnection() != this.connection) {
/* 4444 */         HashMap<String, Object> inserts = new HashMap<>();
/* 4445 */         inserts.put("XMSC_DESTINATION_NAME", topic);
/* 4446 */         JMSException je2 = (JMSException)JmsErrorUtils.createException("JMSCC3003", inserts);
/* 4447 */         if (Trace.isOn) {
/* 4448 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedDurableConsumer(Topic,String,String)", (Throwable)je2, 3);
/*      */         }
/*      */         
/* 4451 */         throw je2;
/*      */       } 
/*      */       
/* 4454 */       if (sharedSubscriptionName == null || "".equals(sharedSubscriptionName)) {
/* 4455 */         JMSException je4 = (JMSException)JmsErrorUtils.createException("JMSCC0100", null);
/* 4456 */         if (Trace.isOn) {
/* 4457 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedDurableConsumer(Topic,String,String)", (Throwable)je4, 4);
/*      */         }
/*      */         
/* 4460 */         throw je4;
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
/* 4471 */       synchronized (this.state)
/*      */       {
/* 4473 */         jmsSubscriber = new JmsTopicSubscriberImpl(this, topic, selector, false, sharedSubscriptionName, true, true);
/*      */         
/* 4475 */         ProviderDestination providerDestination = JmsDestinationImplProxy.getProviderDestination(jmsTopic);
/*      */         
/* 4477 */         ProviderMessageConsumer consumer = this.providerSession.createSharedDurableConsumer(providerDestination, sharedSubscriptionName, selector, jmsSubscriber);
/*      */ 
/*      */ 
/*      */         
/* 4481 */         jmsSubscriber.setProviderConsumer(consumer);
/*      */ 
/*      */         
/* 4484 */         if (getState() == 1) {
/* 4485 */           jmsSubscriber.start();
/*      */         } else {
/*      */           
/* 4488 */           jmsSubscriber.stop();
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4497 */         this.syncConsumers.add(jmsSubscriber);
/*      */ 
/*      */ 
/*      */         
/* 4501 */         JmsDestinationImplProxy.incrementUseCount((JmsDestinationImpl)topic);
/*      */       }
/*      */     
/*      */     } finally {
/*      */       
/* 4506 */       if (Trace.isOn) {
/* 4507 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedDurableConsumer(Topic,String,String)");
/*      */       }
/*      */       
/* 4510 */       if (this.sessionSyncLock.hasExclusiveLock()) {
/* 4511 */         this.sessionSyncLock.unlockExclusiveLock();
/*      */       }
/*      */       
/* 4514 */       if (Trace.isOn) {
/* 4515 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "createSharedDurableConsumer(Topic,String,String)", jmsSubscriber);
/*      */       }
/*      */     } 
/*      */     
/* 4519 */     return (MessageConsumer)jmsSubscriber;
/*      */   }
/*      */   
/*      */   Message duplicate(Message message) {
/* 4523 */     if (Trace.isOn) {
/* 4524 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "duplicate(Message)", new Object[] { message });
/*      */     }
/*      */ 
/*      */     
/* 4528 */     if (message instanceof Serializable) {
/*      */       
/*      */       try {
/* 4531 */         ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 4532 */         ObjectOutputStream oos = new ObjectOutputStream(baos);
/* 4533 */         oos.writeObject(message);
/* 4534 */         oos.close();
/*      */ 
/*      */         
/* 4537 */         byte[] bytes = baos.toByteArray();
/* 4538 */         ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
/* 4539 */         ObjectInputStream ois = new ObjectInputStream(bais);
/* 4540 */         Message dup = (Message)ois.readObject();
/* 4541 */         ois.close();
/*      */ 
/*      */         
/* 4544 */         if (dup instanceof BytesMessage) {
/* 4545 */           ((BytesMessage)dup).reset();
/*      */         }
/* 4547 */         else if (dup instanceof StreamMessage) {
/* 4548 */           ((StreamMessage)dup).reset();
/*      */         } 
/*      */ 
/*      */         
/* 4552 */         if (Trace.isOn) {
/* 4553 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "duplicate(Message)", dup, 1);
/*      */         }
/*      */         
/* 4556 */         return dup;
/*      */       }
/* 4558 */       catch (IOException|ClassNotFoundException|JMSException e) {
/* 4559 */         if (Trace.isOn) {
/* 4560 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "duplicate(Message)", e);
/*      */         }
/*      */ 
/*      */         
/* 4564 */         if (Trace.isOn) {
/* 4565 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "duplicate(Message)", message, 2);
/*      */         }
/*      */         
/* 4568 */         return message;
/*      */       } 
/*      */     }
/*      */     
/* 4572 */     if (Trace.isOn) {
/* 4573 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "duplicate(Message)", message, 3);
/*      */     }
/*      */     
/* 4576 */     return message;
/*      */   }
/*      */   
/*      */   public static class CompletionListenerRunner
/*      */     implements Runnable
/*      */   {
/* 4582 */     JmsSessionImpl session = null;
/* 4583 */     CompletionListener completionListener = null;
/* 4584 */     Message message = null;
/* 4585 */     Exception e = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public CompletionListenerRunner(CompletionListener completionListener, Message message, Exception e) {
/* 4593 */       if (Trace.isOn) {
/* 4594 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.CompletionListenerRunner", "<init>(JmsSessionImpl,CompletionListener,Message,Exception)", new Object[] { completionListener, message, e });
/*      */       }
/*      */       
/* 4597 */       this.completionListener = completionListener;
/* 4598 */       this.message = message;
/* 4599 */       this.e = e;
/*      */       
/* 4601 */       if (Trace.isOn) {
/* 4602 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.CompletionListenerRunner", "<init>(JmsSessionImpl,CompletionListener,Message,Exception)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/* 4613 */       if (Trace.isOn) {
/* 4614 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.CompletionListenerRunner", "run()");
/*      */       }
/*      */       
/*      */       try {
/* 4618 */         if (this.e == null)
/*      */         {
/* 4620 */           this.completionListener.onCompletion(this.message);
/*      */         }
/*      */         else
/*      */         {
/* 4624 */           this.completionListener.onException(this.message, this.e);
/*      */         }
/*      */       
/* 4627 */       } catch (Throwable t) {
/* 4628 */         if (Trace.isOn) {
/* 4629 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.CompletionListenerRunner", "run()", t);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 4639 */       if (Trace.isOn) {
/* 4640 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.CompletionListenerRunner", "run()");
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private static class SynchronousCompletionListenerLock
/*      */   {
/*      */     private SynchronousCompletionListenerLock() {}
/*      */   }
/*      */   
/*      */   class SynchronousCompletionListener
/*      */     implements CompletionListener
/*      */   {
/* 4653 */     JmsSessionImpl.SynchronousCompletionListenerLock lock = null;
/*      */     boolean complete = false;
/*      */     
/*      */     public SynchronousCompletionListener() {
/* 4657 */       if (Trace.isOn) {
/* 4658 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SynchronousCompletionListener", "<init>()");
/*      */       }
/*      */ 
/*      */       
/* 4662 */       this.lock = new JmsSessionImpl.SynchronousCompletionListenerLock();
/*      */       
/* 4664 */       if (Trace.isOn) {
/* 4665 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SynchronousCompletionListener", "<init>()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void waitForCompletion(long timeout) {
/* 4672 */       if (Trace.isOn) {
/* 4673 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SynchronousCompletionListener", "waitForCompletion(long)", new Object[] {
/* 4674 */               Long.valueOf(timeout)
/*      */             });
/*      */       }
/* 4677 */       if (this.complete) {
/* 4678 */         if (Trace.isOn) {
/* 4679 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.SynchronousCompletionListener", "waitForCompletion(long)", 1);
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/* 4684 */       synchronized (this.lock) {
/* 4685 */         while (!this.complete) {
/*      */           try {
/* 4687 */             if (Trace.isOn) {
/* 4688 */               Trace.traceData(this, "Waiting", null);
/*      */             }
/* 4690 */             this.lock.wait(timeout);
/*      */           }
/* 4692 */           catch (InterruptedException e) {
/* 4693 */             if (Trace.isOn) {
/* 4694 */               Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.SynchronousCompletionListener", "waitForCompletion(long)", e);
/*      */             }
/*      */ 
/*      */             
/* 4698 */             if (Trace.isOn) {
/* 4699 */               Trace.traceData(this, "Wait interrupted", null);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 4705 */       if (Trace.isOn) {
/* 4706 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SynchronousCompletionListener", "waitForCompletion(long)", 2);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void waitForCompletion() {
/* 4714 */       if (Trace.isOn) {
/* 4715 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SynchronousCompletionListener", "waitForCompletion()");
/*      */       }
/*      */ 
/*      */       
/* 4719 */       while (!this.complete) {
/* 4720 */         waitForCompletion(5000L);
/*      */       }
/*      */       
/* 4723 */       if (Trace.isOn) {
/* 4724 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SynchronousCompletionListener", "waitForCompletion()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void onCompletion(Message arg0) {
/* 4732 */       if (Trace.isOn) {
/* 4733 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SynchronousCompletionListener", "onCompletion(Message)", new Object[] { arg0 });
/*      */       }
/*      */       
/* 4736 */       synchronized (this.lock) {
/* 4737 */         this.complete = true;
/* 4738 */         if (Trace.isOn) {
/* 4739 */           Trace.traceData(this, "Notifying waiting threads of completion", null);
/*      */         }
/* 4741 */         this.lock.notifyAll();
/*      */       } 
/* 4743 */       if (Trace.isOn) {
/* 4744 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SynchronousCompletionListener", "onCompletion(Message)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void onException(Message arg0, Exception e) {
/* 4753 */       if (Trace.isOn) {
/* 4754 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SynchronousCompletionListener", "onException(Message,Exception)", new Object[] { arg0, e });
/*      */       }
/*      */ 
/*      */       
/* 4758 */       synchronized (this.lock) {
/* 4759 */         this.complete = true;
/* 4760 */         if (Trace.isOn) {
/* 4761 */           Trace.traceData(this, "Notifying waiting threads of Exception", null);
/*      */         }
/* 4763 */         this.lock.notifyAll();
/*      */       } 
/*      */       
/* 4766 */       if (Trace.isOn) {
/* 4767 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SynchronousCompletionListener", "onException(Message,Exception)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class ReentrantDoubleLock
/*      */   {
/*      */     private ReentrantReadWriteLockSubclass internalLock;
/*      */ 
/*      */ 
/*      */     
/*      */     private static class ReentrantReadWriteLockSubclass
/*      */       extends ReentrantReadWriteLock
/*      */     {
/*      */       private static final long serialVersionUID = 6085027363690643053L;
/*      */ 
/*      */       
/*      */       public ReentrantReadWriteLockSubclass() {
/* 4787 */         if (Trace.isOn) {
/* 4788 */           Trace.entry(this, "com.ibm.msg.client.jms.internal.ReentrantReadWriteLockSubclass", "<init>()");
/*      */         }
/*      */         
/* 4791 */         if (Trace.isOn) {
/* 4792 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.ReentrantReadWriteLockSubclass", "<init>()");
/*      */         }
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public ReentrantReadWriteLockSubclass(boolean fair) {
/* 4799 */         super(fair);
/* 4800 */         if (Trace.isOn)
/* 4801 */           Trace.entry(this, "com.ibm.msg.client.jms.internal.ReentrantReadWriteLockSubclass", "<init>(boolean)", new Object[] {
/* 4802 */                 Boolean.valueOf(fair)
/*      */               }); 
/* 4804 */         if (Trace.isOn) {
/* 4805 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.ReentrantReadWriteLockSubclass", "<init>(boolean)");
/*      */         }
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Thread getOwner() {
/* 4813 */         Thread traceRet1 = super.getOwner();
/* 4814 */         if (Trace.isOn) {
/* 4815 */           Trace.data(this, "com.ibm.msg.client.jms.internal.ReentrantReadWriteLockSubclass", "getOwner()", "getter", traceRet1);
/*      */         }
/*      */         
/* 4818 */         return traceRet1;
/*      */       }
/*      */ 
/*      */       
/*      */       public Collection<Thread> getQueuedWriterThreads() {
/* 4823 */         Collection<Thread> traceRet1 = super.getQueuedWriterThreads();
/* 4824 */         if (Trace.isOn) {
/* 4825 */           Trace.data(this, "com.ibm.msg.client.jms.internal.ReentrantReadWriteLockSubclass", "getQueuedWriterThreads()", "getter", traceRet1);
/*      */         }
/*      */         
/* 4828 */         return traceRet1;
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ReentrantDoubleLock() {
/* 4839 */       if (Trace.isOn) {
/* 4840 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "<init>()");
/*      */       }
/* 4842 */       this.internalLock = new ReentrantReadWriteLockSubclass();
/*      */       
/* 4844 */       if (Trace.isOn) {
/* 4845 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "<init>()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ReentrantDoubleLock(boolean fair) {
/* 4854 */       if (Trace.isOn) {
/* 4855 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "<init>(boolean)", new Object[] { Boolean.valueOf(fair) });
/*      */       }
/*      */       
/* 4858 */       this.internalLock = new ReentrantReadWriteLockSubclass(fair);
/* 4859 */       if (Trace.isOn) {
/* 4860 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "<init>(boolean)");
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
/*      */     public void trySharedLock() {
/* 4872 */       if (Trace.isOn) {
/* 4873 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "trySharedLock()");
/*      */       }
/*      */ 
/*      */       
/* 4877 */       boolean gotLock = this.internalLock.readLock().tryLock();
/*      */       
/* 4879 */       if (!gotLock) {
/*      */ 
/*      */ 
/*      */         
/* 4883 */         if (Trace.isOn) {
/* 4884 */           Trace.traceData(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "trySharedLock()", "Failed to get lock. ExclusiveLock is already held by another thread", null);
/*      */         }
/*      */         
/* 4887 */         getSharedLock();
/*      */       } 
/*      */       
/* 4890 */       if (Trace.isOn) {
/* 4891 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "trySharedLock()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void getSharedLock() {
/* 4902 */       if (Trace.isOn) {
/* 4903 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "getSharedLock()");
/*      */       }
/* 4905 */       this.internalLock.readLock().lock();
/* 4906 */       if (Trace.isOn) {
/* 4907 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "getSharedLock()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void getExclusiveLock() {
/* 4917 */       if (Trace.isOn) {
/* 4918 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "getExclusiveLock()");
/*      */       }
/*      */       
/* 4921 */       this.internalLock.writeLock().lock();
/* 4922 */       if (Trace.isOn) {
/* 4923 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "getExclusiveLock()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void unlockSharedLock() {
/* 4933 */       if (Trace.isOn) {
/* 4934 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "unlockSharedLock()");
/*      */       }
/*      */       
/* 4937 */       this.internalLock.readLock().unlock();
/* 4938 */       if (Trace.isOn) {
/* 4939 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "unlockSharedLock()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void unlockExclusiveLock() {
/* 4949 */       if (Trace.isOn) {
/* 4950 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "unlockExclusiveLock()");
/*      */       }
/*      */       
/* 4953 */       this.internalLock.writeLock().unlock();
/* 4954 */       if (Trace.isOn) {
/* 4955 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "unlockExclusiveLock()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean downGradeLock() {
/* 4965 */       if (Trace.isOn) {
/* 4966 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "downGradeLock()");
/*      */       }
/* 4968 */       this.internalLock.readLock().lock();
/* 4969 */       this.internalLock.writeLock().unlock();
/* 4970 */       if (Trace.isOn) {
/* 4971 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "downGradeLock()", 
/* 4972 */             Boolean.valueOf(true));
/*      */       }
/* 4974 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean hasExclusiveLock() {
/* 4981 */       if (Trace.isOn) {
/* 4982 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "hasExclusiveLock()");
/*      */       }
/*      */ 
/*      */       
/* 4986 */       boolean traceRet1 = this.internalLock.writeLock().isHeldByCurrentThread();
/* 4987 */       if (Trace.isOn) {
/* 4988 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "hasExclusiveLock()", 
/* 4989 */             Boolean.valueOf(traceRet1));
/*      */       }
/* 4991 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getSharedLockCount() {
/* 4998 */       int traceRet1 = this.internalLock.getReadHoldCount();
/* 4999 */       if (Trace.isOn) {
/* 5000 */         Trace.data(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "getSharedLockCount()", "getter", 
/* 5001 */             Integer.valueOf(traceRet1));
/*      */       }
/* 5003 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Thread exclusiveLockOwner() {
/* 5010 */       if (Trace.isOn) {
/* 5011 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "exclusiveLockOwner()");
/*      */       }
/*      */       
/* 5014 */       Thread traceRet1 = this.internalLock.getOwner();
/* 5015 */       if (Trace.isOn) {
/* 5016 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "exclusiveLockOwner()", traceRet1);
/*      */       }
/*      */       
/* 5019 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getExclusiveQueueLength() {
/* 5026 */       if (Trace.isOn) {
/* 5027 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "getExclusiveQueueLength()");
/*      */       }
/*      */ 
/*      */       
/* 5031 */       Collection<Thread> threads = this.internalLock.getQueuedWriterThreads();
/*      */       
/* 5033 */       int traceRet1 = threads.size();
/* 5034 */       if (Trace.isOn) {
/* 5035 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.ReentrantDoubleLock", "getExclusiveQueueLength()", 
/* 5036 */             Integer.valueOf(traceRet1), 2);
/*      */       }
/* 5038 */       return traceRet1;
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
/*      */   public enum ProcessorState
/*      */   {
/* 5051 */     UNSTARTED,
/*      */     
/* 5053 */     ACTIVE,
/*      */     
/* 5055 */     UNLOCKED,
/*      */     
/* 5057 */     CLOSING,
/*      */     
/* 5059 */     CLOSED;
/*      */   }
/*      */   
/*      */   class SendQueueProcessor2
/*      */     implements BlockingQueue<SendDetails>, Runnable {
/* 5064 */     private final String sendQueueProcessorThreadName = "MQ JMS Asynchronous Send Thread";
/*      */     
/*      */     private String originalThreadName;
/*      */     
/*      */     private LinkedBlockingQueue<JmsSessionImpl.SendDetails> sendQueue;
/* 5069 */     private long pollTime = 1L;
/* 5070 */     private int maximumQueueDepth = 10;
/*      */     
/*      */     private class SendQueueLock
/*      */     {
/*      */       private SendQueueLock() {}
/*      */     }
/*      */     
/* 5077 */     private SendQueueLock sendQueueLock = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private JmsSessionImpl session;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private JmsSessionImpl.ProcessorState state;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean closeRequested = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private StandardMBean sendQueueProcessorMonitorBean;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private String sendQueueProcessorMonitorName;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private SendQueueProcessorMonitorImpl sendQueueProcessorMonitor;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void init(JmsSessionImpl session, SendQueueLock existingLock) {
/* 5116 */       if (Trace.isOn) {
/* 5117 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "init(JmsSessionImpl,SendQueueLock)", new Object[] { session, existingLock });
/*      */       }
/*      */ 
/*      */       
/* 5121 */       PropertyStore.register("com.ibm.msg.client.jms.asyncSendPollTimeout", 1L, Long.valueOf(0L), Long.valueOf(Long.MAX_VALUE));
/* 5122 */       this.pollTime = PropertyStore.getLongPropertyObject("com.ibm.msg.client.jms.asyncSendPollTimeout").longValue();
/*      */ 
/*      */ 
/*      */       
/* 5126 */       PropertyStore.register("com.ibm.msg.client.jms.asyncSendMaxBufferSize", 1000L, Long.valueOf(0L), Long.valueOf(2147483647L));
/* 5127 */       this.maximumQueueDepth = PropertyStore.getLongPropertyObject("com.ibm.msg.client.jms.asyncSendMaxBufferSize").intValue();
/*      */       
/* 5129 */       this.session = session;
/* 5130 */       this.sendQueue = new LinkedBlockingQueue<>(this.maximumQueueDepth);
/* 5131 */       this.sendQueueLock = existingLock;
/*      */ 
/*      */       
/* 5134 */       intializeSendQueueProcessorMonitor();
/*      */       
/* 5136 */       this.state = JmsSessionImpl.ProcessorState.UNSTARTED;
/*      */       
/* 5138 */       if (Trace.isOn) {
/* 5139 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "init(JmsSessionImpl,SendQueueLock)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/* 5147 */       if (Trace.isOn) {
/* 5148 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "run()");
/*      */       }
/*      */       
/* 5151 */       if (Trace.isOn) {
/* 5152 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl.SendQueueProcessor2", "run()");
/*      */       }
/*      */       
/*      */       try {
/* 5156 */         this.session.asyncPutThread = Thread.currentThread();
/* 5157 */         saveAndSetName("MQ JMS Asynchronous Send Thread");
/*      */ 
/*      */         
/* 5160 */         JmsSessionImpl.this.sessionSyncLock.trySharedLock();
/*      */         
/* 5162 */         synchronized (this.sendQueueLock) {
/*      */           
/* 5164 */           this.state = JmsSessionImpl.ProcessorState.ACTIVE;
/*      */ 
/*      */           
/* 5167 */           this.sendQueueLock.notifyAll();
/*      */           
/* 5169 */           if (this.closeRequested) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 5176 */             this.state = JmsSessionImpl.ProcessorState.CLOSED;
/* 5177 */             if (Trace.isOn) {
/* 5178 */               Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "run()", 1);
/*      */             }
/*      */             
/*      */             return;
/*      */           } 
/*      */         } 
/*      */         
/* 5185 */         JmsSessionImpl.SendDetails details = null;
/* 5186 */         Exception se = null;
/*      */         
/* 5188 */         boolean working = true;
/*      */ 
/*      */         
/*      */         do {
/* 5192 */           details = this.sendQueue.poll();
/*      */           
/* 5194 */           if (details == null && !this.closeRequested) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 5199 */             synchronized (this.sendQueueLock) {
/*      */ 
/*      */               
/* 5202 */               this.state = JmsSessionImpl.ProcessorState.UNLOCKED;
/*      */             } 
/* 5204 */             JmsSessionImpl.this.sessionSyncLock.unlockSharedLock();
/*      */             
/*      */             try {
/* 5207 */               long count = 0L;
/* 5208 */               while (count < this.pollTime && !this.closeRequested) {
/* 5209 */                 details = this.sendQueue.poll(1L, TimeUnit.SECONDS);
/* 5210 */                 if (details == null) {
/* 5211 */                   count++;
/*      */                 
/*      */                 }
/*      */               
/*      */               }
/*      */             
/*      */             }
/* 5218 */             catch (InterruptedException ie) {
/* 5219 */               if (Trace.isOn) {
/* 5220 */                 Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "run()", ie, 1);
/*      */               }
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/* 5226 */             if (details != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 5232 */               JmsSessionImpl.this.sessionSyncLock.trySharedLock();
/*      */ 
/*      */               
/* 5235 */               synchronized (this.sendQueueLock) {
/* 5236 */                 this.state = JmsSessionImpl.ProcessorState.ACTIVE;
/* 5237 */                 this.sendQueueLock.notifyAll();
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           
/* 5242 */           if (details != null) {
/*      */ 
/*      */             
/*      */             try {
/*      */               
/* 5247 */               se = null;
/*      */               
/* 5249 */               details.producer.synchronousSendInternal(details.dest, details.sendingMessage, details.deliveryMode, details.priority, details.timeToLive, details.deliveryDelay, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             }
/* 5257 */             catch (JMSException e) {
/* 5258 */               if (Trace.isOn) {
/* 5259 */                 Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "run()", (Throwable)e, 2);
/*      */               }
/*      */               
/* 5262 */               JMSException jMSException1 = e;
/*      */             }
/* 5264 */             catch (Throwable t) {
/* 5265 */               if (Trace.isOn) {
/* 5266 */                 Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "run()", t, 3);
/*      */               }
/*      */ 
/*      */               
/* 5270 */               if (t instanceof Exception) {
/* 5271 */                 se = (Exception)t;
/*      */               } else {
/*      */                 
/* 5274 */                 se = new Exception("Unexpected Exception thrown during send", t);
/*      */               } 
/*      */             } 
/*      */ 
/*      */             
/*      */             try {
/* 5280 */               if (details.originalMessage instanceof JmsMessage) {
/* 5281 */                 ((JmsMessage)details.originalMessage).updateFromMessage(details.sendingMessage);
/*      */               } else {
/*      */ 
/*      */                 
/*      */                 try {
/*      */                   
/* 5287 */                   JmsMessageImpl.genericMessageUpdate(details.sendingMessage, details.originalMessage);
/*      */                 }
/* 5289 */                 catch (Throwable t) {
/* 5290 */                   if (Trace.isOn) {
/* 5291 */                     Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "run()", t, 4);
/*      */                   }
/*      */                 } 
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/* 5298 */               if (se == null) {
/* 5299 */                 details.completionListener.onCompletion(details.sendingMessage);
/*      */               } else {
/*      */                 
/* 5302 */                 details.completionListener.onException(details.sendingMessage, se);
/*      */               }
/*      */             
/* 5305 */             } catch (Throwable t) {
/* 5306 */               if (Trace.isOn) {
/* 5307 */                 Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "run()", t, 5);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/* 5312 */               HashMap<String, Object> data = new HashMap<>();
/* 5313 */               data.put("Exception", t);
/*      */               
/* 5315 */               Trace.ffst(this, "runItem()", "XJ003003", data, null);
/*      */             } 
/*      */           } 
/*      */           
/* 5319 */           if (details == null && !this.closeRequested) {
/*      */             
/* 5321 */             if (Trace.isOn) {
/* 5322 */               Trace.data(this, "run()", "closeRequested. Making final check on sendQueue", null);
/*      */             }
/*      */             
/* 5325 */             synchronized (this.sendQueueLock) {
/*      */ 
/*      */               
/* 5328 */               if (this.sendQueue.isEmpty()) {
/* 5329 */                 if (Trace.isOn) {
/* 5330 */                   Trace.data(this, "run()", "No more messages. Safe to close", null);
/*      */                 }
/* 5332 */                 this.state = JmsSessionImpl.ProcessorState.CLOSING;
/* 5333 */                 working = false;
/* 5334 */                 this.session.asyncPutThread = null;
/*      */                 
/* 5336 */                 while (JmsSessionImpl.this.sessionSyncLock.getSharedLockCount() > 0) {
/* 5337 */                   JmsSessionImpl.this.sessionSyncLock.unlockSharedLock();
/*      */                 }
/* 5339 */                 this.sendQueueLock.notifyAll();
/*      */               
/*      */               }
/*      */               else {
/*      */                 
/* 5344 */                 if (Trace.isOn) {
/* 5345 */                   Trace.data(this, "run()", "new message was put to the queue, restarting loop", null);
/*      */                 }
/* 5347 */                 working = true;
/* 5348 */                 JmsSessionImpl.this.sessionSyncLock.trySharedLock();
/* 5349 */                 this.state = JmsSessionImpl.ProcessorState.ACTIVE;
/* 5350 */                 this.sendQueueLock.notifyAll();
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           
/* 5355 */           if (!this.closeRequested && this.state != JmsSessionImpl.ProcessorState.CLOSING)
/*      */             continue; 
/* 5357 */           working = false;
/*      */           
/* 5359 */           this.session.asyncPutThread = null;
/*      */           
/* 5361 */           while (JmsSessionImpl.this.sessionSyncLock.getSharedLockCount() > 0) {
/* 5362 */             JmsSessionImpl.this.sessionSyncLock.unlockSharedLock();
/*      */           
/*      */           }
/*      */         }
/* 5366 */         while (working);
/*      */ 
/*      */         
/* 5369 */         closeSendQueueProcessorMonitor();
/* 5370 */         resetName();
/*      */         
/* 5372 */         this.state = JmsSessionImpl.ProcessorState.CLOSED;
/*      */       
/*      */       }
/* 5375 */       catch (Throwable t) {
/* 5376 */         if (Trace.isOn) {
/* 5377 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "run()", t, 6);
/*      */         }
/* 5379 */         if (Trace.isOn) {
/* 5380 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl.sendQueueProcessor2", "run()", t, 1);
/*      */         }
/* 5382 */         HashMap<String, Object> data = new HashMap<>();
/* 5383 */         data.put("Exception", t);
/* 5384 */         if (this.sendQueue != null) {
/* 5385 */           data.put("sendQueue depth", Integer.valueOf(this.sendQueue.size()));
/*      */         }
/* 5387 */         Trace.ffst(this, "run()", "XJ003004", data, null);
/*      */         
/* 5389 */         synchronized (this.sendQueueLock) {
/* 5390 */           this.state = JmsSessionImpl.ProcessorState.CLOSED;
/*      */         }
/*      */       
/*      */       } finally {
/*      */         
/* 5395 */         if (Trace.isOn) {
/* 5396 */           Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "run()");
/*      */         }
/* 5398 */         synchronized (this.sendQueueLock) {
/* 5399 */           this.state = JmsSessionImpl.ProcessorState.CLOSED;
/*      */         } 
/*      */         
/* 5402 */         if (Trace.isOn) {
/* 5403 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl.sendQueueProcessor2", "run()");
/*      */         }
/*      */       } 
/*      */       
/* 5407 */       if (Trace.isOn) {
/* 5408 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "run()", 2);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     long getPollTimeout() {
/* 5417 */       if (Trace.isOn) {
/* 5418 */         Trace.data(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "getPollTimeout()", "getter", 
/* 5419 */             Long.valueOf(this.pollTime));
/*      */       }
/* 5421 */       return this.pollTime;
/*      */     }
/*      */     
/*      */     void setPollTimeout(long newTimeout) {
/* 5425 */       if (Trace.isOn) {
/* 5426 */         Trace.data(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "setPollTimeout(long)", "setter", 
/* 5427 */             Long.valueOf(newTimeout));
/*      */       }
/* 5429 */       this.pollTime = newTimeout;
/*      */     }
/*      */     
/*      */     int getMaximumQueueDepth() {
/* 5433 */       if (Trace.isOn) {
/* 5434 */         Trace.data(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "getMaximumQueueDepth()", "getter", 
/* 5435 */             Integer.valueOf(this.maximumQueueDepth));
/*      */       }
/* 5437 */       return this.maximumQueueDepth;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void close() {
/* 5447 */       if (Trace.isOn) {
/* 5448 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "close()");
/*      */       }
/* 5450 */       if (Trace.isOn) {
/* 5451 */         Trace.data(this, "close()", "Close requested on SendQueueProcessor", Thread.currentThread());
/* 5452 */         Trace.data(this, "close()", "Current processor state = " + this.state.toString());
/*      */       } 
/* 5454 */       this.closeRequested = true;
/*      */       
/* 5456 */       synchronized (this.sendQueueLock) {
/* 5457 */         if (this.state != JmsSessionImpl.ProcessorState.CLOSED) {
/* 5458 */           this.state = JmsSessionImpl.ProcessorState.CLOSING;
/*      */         }
/*      */       } 
/*      */       
/* 5462 */       if (Trace.isOn) {
/* 5463 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "close()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public JmsSessionImpl.ProcessorState getState() {
/* 5471 */       synchronized (this.sendQueueLock) {
/* 5472 */         if (Trace.isOn) {
/* 5473 */           Trace.data(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "getState()", "getter", this.state);
/*      */         }
/*      */         
/* 5476 */         return this.state;
/*      */       } 
/*      */     }
/*      */     
/*      */     private void saveAndSetName(final String newName) {
/* 5481 */       if (Trace.isOn) {
/* 5482 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "saveAndSetName(final String)", new Object[] { newName });
/*      */       }
/*      */ 
/*      */       
/* 5486 */       this.originalThreadName = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*      */           {
/*      */             public String run()
/*      */             {
/* 5490 */               if (Trace.isOn) {
/* 5491 */                 Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "run()");
/*      */               }
/*      */               try {
/* 5494 */                 String oldName = Thread.currentThread().getName();
/* 5495 */                 Thread.currentThread().setName(newName);
/* 5496 */                 if (Trace.isOn) {
/* 5497 */                   Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "run()", oldName, 1);
/*      */                 }
/* 5499 */                 return oldName;
/*      */               }
/* 5501 */               catch (SecurityException e) {
/* 5502 */                 if (Trace.isOn) {
/* 5503 */                   Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.null", "run()", e);
/*      */                 }
/* 5505 */                 if (Trace.isOn) {
/* 5506 */                   Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "run()", null, 2);
/*      */                 }
/* 5508 */                 return null;
/*      */               } 
/*      */             }
/*      */           });
/* 5512 */       if (Trace.isOn) {
/* 5513 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "saveAndSetName(final String)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private void resetName() {
/* 5520 */       if (Trace.isOn) {
/* 5521 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "resetName()");
/*      */       }
/* 5523 */       if (this.originalThreadName != null) {
/* 5524 */         AccessController.doPrivileged(new PrivilegedAction()
/*      */             {
/*      */               public Object run()
/*      */               {
/* 5528 */                 if (Trace.isOn) {
/* 5529 */                   Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "run()");
/*      */                 }
/*      */                 try {
/* 5532 */                   Thread.currentThread().setName(JmsSessionImpl.SendQueueProcessor2.this.originalThreadName);
/* 5533 */                   if (Trace.isOn) {
/* 5534 */                     Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "run()", null, 1);
/*      */                   }
/* 5536 */                   return null;
/*      */                 }
/* 5538 */                 catch (SecurityException e) {
/* 5539 */                   if (Trace.isOn) {
/* 5540 */                     Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.null", "run()", e);
/*      */                   }
/* 5542 */                   if (Trace.isOn) {
/* 5543 */                     Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "run()", null, 2);
/*      */                   }
/* 5545 */                   return null;
/*      */                 } 
/*      */               }
/*      */             });
/*      */       }
/* 5550 */       if (Trace.isOn) {
/* 5551 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "resetName()");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public SendQueueProcessor2(JmsSessionImpl session)
/*      */     {
/* 5558 */       this.sendQueueProcessorMonitorBean = null;
/* 5559 */       this.sendQueueProcessorMonitorName = null; if (Trace.isOn) Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "<init>(JmsSessionImpl)", new Object[] { session });  init(session, new SendQueueLock()); if (Trace.isOn) Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "<init>(JmsSessionImpl)");  } public SendQueueProcessor2(JmsSessionImpl session, SendQueueLock existingLock) { this.sendQueueProcessorMonitorBean = null; this.sendQueueProcessorMonitorName = null;
/*      */       if (Trace.isOn) {
/*      */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "<init>(JmsSessionImpl,SendQueueLock)", new Object[] { session, existingLock });
/*      */       }
/*      */       init(session, existingLock);
/*      */       if (Trace.isOn) {
/*      */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "<init>(JmsSessionImpl,SendQueueLock)");
/*      */       } }
/*      */ 
/*      */     
/*      */     private void intializeSendQueueProcessorMonitor() {
/* 5570 */       if (Trace.isOn) {
/* 5571 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "intializeSendQueueProcessorMonitor()");
/*      */       }
/*      */ 
/*      */       
/* 5575 */       this.sendQueueProcessorMonitor = new SendQueueProcessorMonitorImpl(this.session, this);
/* 5576 */       Trace.registerDumpableObject(this.sendQueueProcessorMonitor);
/*      */       
/*      */       try {
/* 5579 */         this.sendQueueProcessorMonitorBean = new StandardMBean((T)this.sendQueueProcessorMonitor, (Class)SendQueueProcessorMonitor.class);
/*      */       }
/* 5581 */       catch (NotCompliantMBeanException e) {
/* 5582 */         if (Trace.isOn) {
/* 5583 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "intializeSendQueueProcessorMonitor()", e);
/*      */         }
/*      */         
/* 5586 */         HashMap<String, Object> inserts = new HashMap<>();
/* 5587 */         inserts.put("SendQueueProcessorMonitorImpl", this.sendQueueProcessorMonitor);
/* 5588 */         inserts.put("SendQueueProcessorMonitor", SendQueueProcessorMonitor.class);
/* 5589 */         inserts.put("NotCompliantMBeanException", e);
/*      */         
/* 5591 */         StringBuffer message = new StringBuffer();
/* 5592 */         message.append("Failed to create StandardMBean for sendQueueProcessor monitor\n");
/* 5593 */         for (Map.Entry<String, Object> insert : inserts.entrySet()) {
/* 5594 */           message.append(insert.getKey());
/* 5595 */           message.append(": ");
/* 5596 */           message.append(insert.getValue());
/* 5597 */           message.append("\n");
/*      */         } 
/*      */         
/* 5600 */         Log.logNLS("com.ibm.msg.client.jms.internal.JmsSessionImpl", "intializeSendQueueProcessorMonitor(SendQueueProcessor)", message
/*      */             
/* 5602 */             .toString());
/*      */         
/* 5604 */         if (Trace.isOn) {
/* 5605 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "intializeSendQueueProcessorMonitor()", 1);
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 5611 */       this.sendQueueProcessorMonitorName = "SendQueueProcessorMonitor(" + hashCode() + ")";
/* 5612 */       MonitorAgent.registerMBean(this.sendQueueProcessorMonitorBean, "JMSCC", this.sendQueueProcessorMonitorName);
/* 5613 */       if (Trace.isOn) {
/* 5614 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "intializeSendQueueProcessorMonitor()", 2);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private void closeSendQueueProcessorMonitor() {
/* 5621 */       if (Trace.isOn) {
/* 5622 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "closeSendQueueProcessorMonitor()");
/*      */       }
/*      */       
/* 5625 */       Trace.deRegisterDumpableObject(this.sendQueueProcessorMonitor);
/*      */       
/*      */       try {
/* 5628 */         MonitorAgent.unregisterMBean(this.sendQueueProcessorMonitorName, "JMSCC");
/*      */       }
/* 5630 */       catch (Throwable t) {
/* 5631 */         if (Trace.isOn) {
/* 5632 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "closeSendQueueProcessorMonitor()", t);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 5637 */         if (Trace.isOn) {
/* 5638 */           Trace.data(this, "closeSendQueueProcessorMonitor()", "Problem occurred unregistering MBean. ", t);
/*      */         }
/*      */       } 
/*      */       
/* 5642 */       this.sendQueueProcessorMonitor = null;
/*      */       
/* 5644 */       if (Trace.isOn) {
/* 5645 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "closeSendQueueProcessorMonitor()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int remainingCapacity() {
/* 5655 */       if (Trace.isOn) {
/* 5656 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "remainingCapacity()");
/*      */       }
/*      */       
/* 5659 */       int traceRet1 = this.sendQueue.remainingCapacity();
/* 5660 */       if (Trace.isOn) {
/* 5661 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "remainingCapacity()", 
/* 5662 */             Integer.valueOf(traceRet1));
/*      */       }
/* 5664 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public int drainTo(Collection<? super JmsSessionImpl.SendDetails> c) {
/* 5669 */       if (Trace.isOn) {
/* 5670 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "drainTo(Collection<? super SendDetails>)", new Object[] { c });
/*      */       }
/*      */       
/* 5673 */       int traceRet1 = this.sendQueue.drainTo(c);
/* 5674 */       if (Trace.isOn) {
/* 5675 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "drainTo(Collection<? super SendDetails>)", 
/* 5676 */             Integer.valueOf(traceRet1));
/*      */       }
/* 5678 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public int drainTo(Collection<? super JmsSessionImpl.SendDetails> c, int maxElements) {
/* 5683 */       if (Trace.isOn) {
/* 5684 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "drainTo(Collection<? super SendDetails>,int)", new Object[] { c, 
/*      */               
/* 5686 */               Integer.valueOf(maxElements) });
/*      */       }
/* 5688 */       int traceRet1 = this.sendQueue.drainTo(c, maxElements);
/* 5689 */       if (Trace.isOn) {
/* 5690 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "drainTo(Collection<? super SendDetails>,int)", 
/* 5691 */             Integer.valueOf(traceRet1));
/*      */       }
/* 5693 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isEmpty() {
/* 5698 */       boolean traceRet1 = this.sendQueue.isEmpty();
/* 5699 */       if (Trace.isOn) {
/* 5700 */         Trace.data(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "isEmpty()", "getter", 
/* 5701 */             Boolean.valueOf(traceRet1));
/*      */       }
/* 5703 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public Object[] toArray() {
/* 5708 */       if (Trace.isOn) {
/* 5709 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "toArray()");
/*      */       }
/* 5711 */       Object[] traceRet1 = this.sendQueue.toArray();
/* 5712 */       if (Trace.isOn) {
/* 5713 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "toArray()", traceRet1);
/*      */       }
/*      */       
/* 5716 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Object[] toArray(Object[] a) {
/* 5722 */       if (Trace.isOn) {
/* 5723 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "toArray(Object [ ])", new Object[] { a });
/*      */       }
/*      */       
/* 5726 */       Object[] traceRet1 = this.sendQueue.toArray(a);
/* 5727 */       if (Trace.isOn) {
/* 5728 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "toArray(Object [ ])", traceRet1);
/*      */       }
/*      */       
/* 5731 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean containsAll(Collection<?> c) {
/* 5736 */       if (Trace.isOn) {
/* 5737 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "containsAll(Collection<?>)", new Object[] { c });
/*      */       }
/*      */       
/* 5740 */       boolean traceRet1 = this.sendQueue.containsAll(c);
/* 5741 */       if (Trace.isOn) {
/* 5742 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "containsAll(Collection<?>)", 
/* 5743 */             Boolean.valueOf(traceRet1));
/*      */       }
/* 5745 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addAll(Collection<? extends JmsSessionImpl.SendDetails> c) {
/* 5750 */       if (Trace.isOn) {
/* 5751 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "addAll(Collection)", new Object[] { c });
/*      */       }
/*      */ 
/*      */       
/* 5755 */       boolean traceRet1 = this.sendQueue.addAll(c);
/* 5756 */       if (Trace.isOn) {
/* 5757 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "addAll(Collection)", 
/* 5758 */             Boolean.valueOf(traceRet1));
/*      */       }
/* 5760 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean removeAll(Collection<?> c) {
/* 5765 */       if (Trace.isOn) {
/* 5766 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "removeAll(Collection)", new Object[] { c });
/*      */       }
/*      */       
/* 5769 */       boolean traceRet1 = this.sendQueue.removeAll(c);
/* 5770 */       if (Trace.isOn) {
/* 5771 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "removeAll(Collection)", 
/* 5772 */             Boolean.valueOf(traceRet1));
/*      */       }
/* 5774 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean retainAll(Collection<?> c) {
/* 5779 */       if (Trace.isOn) {
/* 5780 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "retainAll(Collection)", new Object[] { c });
/*      */       }
/*      */       
/* 5783 */       boolean traceRet1 = this.sendQueue.retainAll(c);
/* 5784 */       if (Trace.isOn) {
/* 5785 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "retainAll(Collection)", 
/* 5786 */             Boolean.valueOf(traceRet1));
/*      */       }
/* 5788 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public void clear() {
/* 5793 */       if (Trace.isOn) {
/* 5794 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "clear()");
/*      */       }
/* 5796 */       this.sendQueue.clear();
/* 5797 */       if (Trace.isOn) {
/* 5798 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "clear()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean add(JmsSessionImpl.SendDetails e) {
/* 5805 */       if (Trace.isOn) {
/* 5806 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "add(SendDetails)", new Object[] { e });
/*      */       }
/*      */       
/* 5809 */       synchronized (this.sendQueueLock) {
/* 5810 */         if (this.state == JmsSessionImpl.ProcessorState.CLOSING || this.state == JmsSessionImpl.ProcessorState.CLOSED) {
/* 5811 */           if (Trace.isOn) {
/* 5812 */             Trace.traceData(this, "add(SendDetails)", "Attempt to put message to closed SendQueueProcessor2", e);
/*      */           }
/* 5814 */           IllegalStateException ise = new IllegalStateException("SendQueueProcessor2 closed");
/* 5815 */           if (Trace.isOn) {
/* 5816 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "add(SendDetails)", ise);
/*      */           }
/*      */           
/* 5819 */           throw ise;
/*      */         } 
/* 5821 */         if (Trace.isOn) {
/* 5822 */           Trace.data(this, "add(SendDetails)", "adding to sendQueue", e);
/*      */         }
/* 5824 */         boolean traceRet1 = this.sendQueue.add(e);
/* 5825 */         if (Trace.isOn) {
/* 5826 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "add(SendDetails)", 
/* 5827 */               Boolean.valueOf(traceRet1));
/*      */         }
/* 5829 */         return traceRet1;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean offer(JmsSessionImpl.SendDetails e) {
/* 5835 */       if (Trace.isOn) {
/* 5836 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "offer(SendDetails)", new Object[] { e });
/*      */       }
/*      */       
/* 5839 */       synchronized (this.sendQueueLock) {
/* 5840 */         if (this.state == JmsSessionImpl.ProcessorState.CLOSING || this.state == JmsSessionImpl.ProcessorState.CLOSED) {
/* 5841 */           if (Trace.isOn) {
/* 5842 */             Trace.traceData(this, "offer(SendDetails)", "Attempt to put message to closed SendQueueProcessor2", e);
/*      */           }
/* 5844 */           IllegalStateException ise = new IllegalStateException("SendQueueProcessor2 closed");
/* 5845 */           if (Trace.isOn) {
/* 5846 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "offer(SendDetails)", ise);
/*      */           }
/*      */           
/* 5849 */           throw ise;
/*      */         } 
/* 5851 */         if (Trace.isOn) {
/* 5852 */           Trace.data(this, "offer(SendDetails)", "offering to sendQueue", e);
/*      */         }
/* 5854 */         boolean traceRet1 = this.sendQueue.offer(e);
/* 5855 */         if (Trace.isOn) {
/* 5856 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "offer(SendDetails)", 
/* 5857 */               Boolean.valueOf(traceRet1));
/*      */         }
/* 5859 */         return traceRet1;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void put(JmsSessionImpl.SendDetails e) throws InterruptedException {
/* 5865 */       if (Trace.isOn) {
/* 5866 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "put(SendDetails)", new Object[] { e });
/*      */       }
/*      */       
/* 5869 */       synchronized (this.sendQueueLock) {
/* 5870 */         if (this.state == JmsSessionImpl.ProcessorState.CLOSING || this.state == JmsSessionImpl.ProcessorState.CLOSED) {
/* 5871 */           if (Trace.isOn) {
/* 5872 */             Trace.traceData(this, "put(SendDetails)", "Attempt to put message to closed SendQueueProcessor2", e);
/*      */           }
/* 5874 */           IllegalStateException ise = new IllegalStateException("SendQueueProcessor2 closed");
/* 5875 */           if (Trace.isOn) {
/* 5876 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "put(SendDetails)", ise);
/*      */           }
/*      */           
/* 5879 */           throw ise;
/*      */         } 
/* 5881 */         if (Trace.isOn) {
/* 5882 */           Trace.data(this, "put(SendDetails)", "putting to sendQueue", e);
/*      */         }
/* 5884 */         this.sendQueue.put(e);
/*      */       } 
/* 5886 */       if (Trace.isOn) {
/* 5887 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "put(SendDetails)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean offer(JmsSessionImpl.SendDetails e, long timeout, TimeUnit unit) throws InterruptedException {
/* 5895 */       if (Trace.isOn) {
/* 5896 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "offer(SendDetails,long,TimeUnit)", new Object[] { e, 
/* 5897 */               Long.valueOf(timeout), unit });
/*      */       }
/* 5899 */       synchronized (this.sendQueueLock) {
/* 5900 */         if (this.state == JmsSessionImpl.ProcessorState.CLOSING || this.state == JmsSessionImpl.ProcessorState.CLOSED) {
/* 5901 */           if (Trace.isOn) {
/* 5902 */             Trace.traceData(this, "offer(SendDetails)", "Attempt to put message to closed SendQueueProcessor2", e);
/*      */           }
/* 5904 */           IllegalStateException ise = new IllegalStateException("SendQueueProcessor2 closed");
/* 5905 */           if (Trace.isOn) {
/* 5906 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "offer(SendDetails,long,TimeUnit)", ise);
/*      */           }
/*      */           
/* 5909 */           throw ise;
/*      */         } 
/* 5911 */         if (Trace.isOn) {
/* 5912 */           Trace.data(this, "offer(SendDetails)", "offering to sendQueue", e);
/*      */         }
/* 5914 */         boolean traceRet1 = this.sendQueue.offer(e, timeout, unit);
/* 5915 */         if (Trace.isOn) {
/* 5916 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "offer(SendDetails,long,TimeUnit)", 
/* 5917 */               Boolean.valueOf(traceRet1));
/*      */         }
/* 5919 */         return traceRet1;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public JmsSessionImpl.SendDetails remove() {
/* 5925 */       if (Trace.isOn) {
/* 5926 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "remove()");
/*      */       }
/* 5928 */       JmsSessionImpl.SendDetails traceRet1 = this.sendQueue.remove();
/* 5929 */       if (Trace.isOn) {
/* 5930 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "remove()", traceRet1);
/*      */       }
/*      */       
/* 5933 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public JmsSessionImpl.SendDetails poll() {
/* 5938 */       if (Trace.isOn) {
/* 5939 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "poll()");
/*      */       }
/* 5941 */       JmsSessionImpl.SendDetails traceRet1 = this.sendQueue.poll();
/* 5942 */       if (Trace.isOn) {
/* 5943 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "poll()", traceRet1);
/*      */       }
/*      */       
/* 5946 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public JmsSessionImpl.SendDetails take() throws InterruptedException {
/* 5951 */       if (Trace.isOn) {
/* 5952 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "take()");
/*      */       }
/* 5954 */       JmsSessionImpl.SendDetails traceRet1 = this.sendQueue.take();
/* 5955 */       if (Trace.isOn) {
/* 5956 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "take()", traceRet1);
/*      */       }
/*      */       
/* 5959 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public JmsSessionImpl.SendDetails poll(long timeout, TimeUnit unit) throws InterruptedException {
/* 5964 */       if (Trace.isOn)
/* 5965 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "poll(long,TimeUnit)", new Object[] {
/* 5966 */               Long.valueOf(timeout), unit
/*      */             }); 
/* 5968 */       JmsSessionImpl.SendDetails traceRet1 = this.sendQueue.poll(timeout, unit);
/* 5969 */       if (Trace.isOn) {
/* 5970 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "poll(long,TimeUnit)", traceRet1);
/*      */       }
/*      */       
/* 5973 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public JmsSessionImpl.SendDetails element() {
/* 5978 */       if (Trace.isOn) {
/* 5979 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "element()");
/*      */       }
/* 5981 */       JmsSessionImpl.SendDetails traceRet1 = this.sendQueue.element();
/* 5982 */       if (Trace.isOn) {
/* 5983 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "element()", traceRet1);
/*      */       }
/*      */       
/* 5986 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public JmsSessionImpl.SendDetails peek() {
/* 5991 */       if (Trace.isOn) {
/* 5992 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "peek()");
/*      */       }
/* 5994 */       JmsSessionImpl.SendDetails traceRet1 = this.sendQueue.peek();
/* 5995 */       if (Trace.isOn) {
/* 5996 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "peek()", traceRet1);
/*      */       }
/*      */       
/* 5999 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean remove(Object o) {
/* 6004 */       if (Trace.isOn) {
/* 6005 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "remove(Object)", new Object[] { o });
/*      */       }
/*      */       
/* 6008 */       boolean traceRet1 = this.sendQueue.remove(o);
/* 6009 */       if (Trace.isOn) {
/* 6010 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "remove(Object)", 
/* 6011 */             Boolean.valueOf(traceRet1));
/*      */       }
/* 6013 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean contains(Object o) {
/* 6018 */       if (Trace.isOn) {
/* 6019 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "contains(Object)", new Object[] { o });
/*      */       }
/*      */       
/* 6022 */       boolean traceRet1 = this.sendQueue.contains(o);
/* 6023 */       if (Trace.isOn) {
/* 6024 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "contains(Object)", 
/* 6025 */             Boolean.valueOf(traceRet1));
/*      */       }
/* 6027 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public int size() {
/* 6032 */       if (Trace.isOn) {
/* 6033 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "size()");
/*      */       }
/* 6035 */       int traceRet1 = this.sendQueue.size();
/* 6036 */       if (Trace.isOn) {
/* 6037 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "size()", 
/* 6038 */             Integer.valueOf(traceRet1));
/*      */       }
/* 6040 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Iterator iterator() {
/* 6046 */       if (Trace.isOn) {
/* 6047 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "iterator()");
/*      */       }
/* 6049 */       Iterator<JmsSessionImpl.SendDetails> traceRet1 = this.sendQueue.iterator();
/* 6050 */       if (Trace.isOn) {
/* 6051 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.SendQueueProcessor2", "iterator()", traceRet1);
/*      */       }
/*      */       
/* 6054 */       return traceRet1;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private class SendQueueLock
/*      */   {
/*      */     private SendQueueLock() {}
/*      */   }
/*      */   
/*      */   public void dump(PrintWriter pw, int level) {
/* 6065 */     if (Trace.isOn) {
/* 6066 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "dump(PrintWriter,int)", new Object[] { pw, 
/* 6067 */             Integer.valueOf(level) });
/*      */     }
/* 6069 */     String prefix = Trace.buildPrefix(level);
/* 6070 */     pw.format("%s%s@%x (%s)\n", new Object[] { prefix, getClass().getName(), Integer.valueOf(hashCode()), (this.providerSession == null) ? "<null>" : 
/* 6071 */           String.format("%s@%x", new Object[] { this.providerSession.getClass().getName(), Integer.valueOf(this.providerSession.hashCode()) }) });
/* 6072 */     super.dump(pw, level + 1);
/* 6073 */     if (Trace.isOn) {
/* 6074 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "dump(PrintWriter,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isInGlobalTransaction() {
/* 6082 */     if (Trace.isOn) {
/* 6083 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "isInGlobalTransaction");
/*      */     }
/* 6085 */     boolean retval = this.providerSession.isInGlobalTransaction();
/* 6086 */     if (Trace.isOn) {
/* 6087 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsSessionImpl", "isInGlobalTransaction", Boolean.valueOf(retval));
/*      */     }
/* 6089 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONObject toJson() {
/* 6097 */     return this.providerSession.toJson();
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsSessionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */