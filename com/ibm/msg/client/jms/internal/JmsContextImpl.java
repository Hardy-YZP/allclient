/*      */ package com.ibm.msg.client.jms.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.DetailedJMSException;
/*      */ import com.ibm.msg.client.jms.JmsContext;
/*      */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.Serializable;
/*      */ import java.lang.ref.Reference;
/*      */ import java.lang.ref.ReferenceQueue;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import javax.jms.BytesMessage;
/*      */ import javax.jms.Connection;
/*      */ import javax.jms.ConnectionMetaData;
/*      */ import javax.jms.Destination;
/*      */ import javax.jms.ExceptionListener;
/*      */ import javax.jms.IllegalStateRuntimeException;
/*      */ import javax.jms.InvalidClientIDRuntimeException;
/*      */ import javax.jms.InvalidDestinationRuntimeException;
/*      */ import javax.jms.InvalidSelectorRuntimeException;
/*      */ import javax.jms.JMSConsumer;
/*      */ import javax.jms.JMSContext;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.JMSProducer;
/*      */ import javax.jms.JMSRuntimeException;
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
/*      */ import javax.jms.TransactionRolledBackRuntimeException;
/*      */ import javax.jms.XAConnection;
/*      */ import javax.jms.XASession;
/*      */ import javax.transaction.xa.XAResource;
/*      */ import org.json.JSONException;
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
/*      */ public class JmsContextImpl
/*      */   extends JmsPropertyContextImpl
/*      */   implements JmsContext
/*      */ {
/*      */   private static final long serialVersionUID = -2321163581437877047L;
/*      */   private Connection connection;
/*      */   protected transient SessionWrapper session;
/*      */   
/*      */   static {
/*   87 */     if (Trace.isOn) {
/*   88 */       Trace.data("com.ibm.msg.client.jms.internal.JmsContextImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsContextImpl.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   98 */   private transient JmsCloseableResourceSet closeables = new JmsCloseableResourceSet();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JmsMessageProducerImpl internalProducer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void acknowledge() throws IllegalStateRuntimeException, JMSRuntimeException {
/*      */     JmsSessionImpl theSession;
/*  128 */     if (Trace.isOn) {
/*  129 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "acknowledge()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  136 */       theSession = this.session.getSessionImpl();
/*      */     }
/*  138 */     catch (JMSException je) {
/*  139 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  140 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/*  145 */       theSession.checkNotClosed();
/*      */ 
/*      */       
/*  148 */       theSession.checkSynchronousUsage("acknowledge");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  155 */       if (theSession.getTransacted()) {
/*  156 */         if (Trace.isOn) {
/*  157 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "acknowledge()", 1);
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  163 */       int sessAck = theSession.getAcknowledgeMode();
/*  164 */       if (sessAck == 2) {
/*  165 */         JmsSessionImpl.ReentrantDoubleLock sessionSyncLock = theSession.getSessionSyncLock();
/*  166 */         sessionSyncLock.getExclusiveLock();
/*      */         
/*      */         try {
/*  169 */           theSession.commitTransaction();
/*      */         } finally {
/*      */           
/*  172 */           if (Trace.isOn) {
/*  173 */             Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "acknowledge()");
/*      */           }
/*      */           
/*  176 */           sessionSyncLock.unlockExclusiveLock();
/*      */         }
/*      */       
/*      */       } 
/*  180 */     } catch (JMSException je) {
/*  181 */       if (Trace.isOn) {
/*  182 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "acknowledge()", (Throwable)je);
/*      */       }
/*      */       
/*  185 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  186 */       if (Trace.isOn) {
/*  187 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "acknowledge()", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  190 */       throw traceRet1;
/*      */     } 
/*  192 */     if (Trace.isOn) {
/*  193 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "acknowledge()", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() throws IllegalStateRuntimeException, JMSRuntimeException {
/*  203 */     if (Trace.isOn) {
/*  204 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "close()");
/*      */     }
/*      */     
/*      */     try {
/*  208 */       JMSException exception = null;
/*  209 */       exception = closeSession();
/*      */       try {
/*  211 */         ((JmsConnectionImpl)this.connection).checkClose(this);
/*      */       }
/*  213 */       catch (JMSException e) {
/*  214 */         if (exception == null) {
/*  215 */           exception = e;
/*      */         }
/*      */       } 
/*  218 */       for (JmsCloseableResource closeable : this.closeables) {
/*  219 */         if (closeable != null) {
/*  220 */           closeable.close();
/*      */         }
/*      */       } 
/*  223 */       this.closeables.clear();
/*      */       
/*  225 */       if (exception != null) {
/*  226 */         if (Trace.isOn) {
/*  227 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "close()", (Throwable)exception);
/*      */         }
/*  229 */         JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(exception);
/*  230 */         if (Trace.isOn) {
/*  231 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "close()", (Throwable)traceRet1);
/*      */         }
/*      */         
/*  234 */         throw traceRet1;
/*      */       } 
/*      */     } finally {
/*      */       
/*  238 */       Trace.deRegisterFFSTObject(this);
/*      */     } 
/*  240 */     if (Trace.isOn) {
/*  241 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "close()");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private JMSException closeSession() {
/*  247 */     if (Trace.isOn) {
/*  248 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "closeSession()");
/*      */     }
/*  250 */     JMSException exception = null;
/*      */     try {
/*  252 */       this.session.close();
/*      */     }
/*  254 */     catch (JMSException e) {
/*  255 */       exception = e;
/*      */     } 
/*  257 */     if (Trace.isOn) {
/*  258 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "closeSession()", exception);
/*      */     }
/*  260 */     return exception;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void commit() throws IllegalStateRuntimeException, TransactionRolledBackRuntimeException, JMSRuntimeException {
/*  268 */     if (Trace.isOn) {
/*  269 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "commit()");
/*      */     }
/*      */     
/*      */     try {
/*  273 */       this.session.commit();
/*      */     }
/*  275 */     catch (JMSException je) {
/*  276 */       if (Trace.isOn) {
/*  277 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "commit()", (Throwable)je);
/*      */       }
/*  279 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  280 */       if (Trace.isOn) {
/*  281 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "commit()", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  284 */       throw traceRet1;
/*      */     } 
/*  286 */     if (Trace.isOn) {
/*  287 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "commit()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public QueueBrowser createBrowser(Queue arg0) throws JMSRuntimeException {
/*  297 */     if (Trace.isOn) {
/*  298 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createBrowser(Queue)", new Object[] { arg0 });
/*      */     }
/*      */ 
/*      */     
/*  302 */     QueueBrowser browser = null;
/*      */     
/*      */     try {
/*  305 */       browser = this.session.createBrowser(arg0);
/*      */     }
/*  307 */     catch (JMSException je) {
/*  308 */       if (Trace.isOn) {
/*  309 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createBrowser(Queue)", (Throwable)je);
/*      */       }
/*      */       
/*  312 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  313 */       if (Trace.isOn) {
/*  314 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createBrowser(Queue)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  317 */       throw traceRet1;
/*      */     } 
/*  319 */     if (Trace.isOn) {
/*  320 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createBrowser(Queue)", browser);
/*      */     }
/*      */     
/*  323 */     return browser;
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
/*      */   public QueueBrowser createBrowser(Queue arg0, String arg1) throws JMSRuntimeException, InvalidDestinationRuntimeException, InvalidSelectorRuntimeException {
/*  335 */     if (Trace.isOn) {
/*  336 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createBrowser(Queue,String)", new Object[] { arg0, arg1 });
/*      */     }
/*      */     
/*  339 */     QueueBrowser browser = null;
/*      */     
/*      */     try {
/*  342 */       browser = this.session.createBrowser(arg0, arg1);
/*      */     }
/*  344 */     catch (JMSException je) {
/*  345 */       if (Trace.isOn) {
/*  346 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createBrowser(Queue,String)", (Throwable)je);
/*      */       }
/*      */       
/*  349 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  350 */       if (Trace.isOn) {
/*  351 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createBrowser(Queue,String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  354 */       throw traceRet1;
/*      */     } 
/*  356 */     if (Trace.isOn) {
/*  357 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createBrowser(Queue,String)", browser);
/*      */     }
/*      */     
/*  360 */     return browser;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BytesMessage createBytesMessage() throws JMSRuntimeException {
/*  368 */     if (Trace.isOn) {
/*  369 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createBytesMessage()");
/*      */     }
/*      */     
/*  372 */     BytesMessage message = null;
/*      */     try {
/*  374 */       message = this.session.createBytesMessage();
/*      */     }
/*  376 */     catch (JMSException je) {
/*  377 */       if (Trace.isOn) {
/*  378 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createBytesMessage()", (Throwable)je);
/*      */       }
/*      */       
/*  381 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  382 */       if (Trace.isOn) {
/*  383 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createBytesMessage()", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  386 */       throw traceRet1;
/*      */     } 
/*  388 */     if (Trace.isOn) {
/*  389 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createBytesMessage()", message);
/*      */     }
/*      */     
/*  392 */     return message;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSConsumer createConsumer(Destination arg0) throws JMSRuntimeException, InvalidDestinationRuntimeException {
/*  400 */     if (Trace.isOn) {
/*  401 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createConsumer(Destination)", new Object[] { arg0 });
/*      */     }
/*      */ 
/*      */     
/*  405 */     JmsConsumerImpl jmsConsumerImpl = null;
/*      */     try {
/*  407 */       JmsMessageConsumerImpl internalConsumer = (JmsMessageConsumerImpl)this.session.createConsumer(arg0);
/*  408 */       jmsConsumerImpl = new JmsConsumerImpl(this, internalConsumer);
/*  409 */       this.closeables.add(jmsConsumerImpl);
/*      */       
/*  411 */       if (getAutoStart()) {
/*  412 */         this.connection.start();
/*      */       }
/*      */     }
/*  415 */     catch (JMSException je) {
/*  416 */       if (Trace.isOn) {
/*  417 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createConsumer(Destination)", (Throwable)je);
/*      */       }
/*      */       
/*  420 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  421 */       if (Trace.isOn) {
/*  422 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createConsumer(Destination)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  425 */       throw traceRet1;
/*      */     } 
/*      */     
/*  428 */     if (Trace.isOn) {
/*  429 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createConsumer(Destination)", jmsConsumerImpl);
/*      */     }
/*      */     
/*  432 */     return (JMSConsumer)jmsConsumerImpl;
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
/*      */   public JMSConsumer createConsumer(Destination arg0, String arg1) throws JMSRuntimeException, InvalidDestinationRuntimeException, InvalidSelectorRuntimeException {
/*  444 */     if (Trace.isOn) {
/*  445 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createConsumer(Destination,String)", new Object[] { arg0, arg1 });
/*      */     }
/*      */     
/*  448 */     JmsConsumerImpl jmsConsumerImpl = null;
/*      */     try {
/*  450 */       JmsMessageConsumerImpl internalConsumer = (JmsMessageConsumerImpl)this.session.createConsumer(arg0, arg1);
/*  451 */       jmsConsumerImpl = new JmsConsumerImpl(this, internalConsumer);
/*  452 */       this.closeables.add(jmsConsumerImpl);
/*      */       
/*  454 */       if (getAutoStart()) {
/*  455 */         this.connection.start();
/*      */       
/*      */       }
/*      */     }
/*  459 */     catch (JMSException je) {
/*  460 */       if (Trace.isOn) {
/*  461 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createConsumer(Destination,String)", (Throwable)je);
/*      */       }
/*      */       
/*  464 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  465 */       if (Trace.isOn) {
/*  466 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createConsumer(Destination,String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  469 */       throw traceRet1;
/*      */     } 
/*  471 */     if (Trace.isOn) {
/*  472 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createConsumer(Destination,String)", jmsConsumerImpl);
/*      */     }
/*      */     
/*  475 */     return (JMSConsumer)jmsConsumerImpl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSConsumer createConsumer(Destination arg0, String arg1, boolean arg2) throws JMSRuntimeException, InvalidDestinationRuntimeException, InvalidSelectorRuntimeException {
/*  486 */     if (Trace.isOn) {
/*  487 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createConsumer(Destination,String,boolean)", new Object[] { arg0, arg1, 
/*      */             
/*  489 */             Boolean.valueOf(arg2) });
/*      */     }
/*      */     
/*  492 */     JmsConsumerImpl jmsConsumerImpl = null;
/*      */     try {
/*  494 */       JmsMessageConsumerImpl internalConsumer = (JmsMessageConsumerImpl)this.session.createConsumer(arg0, arg1, arg2);
/*  495 */       jmsConsumerImpl = new JmsConsumerImpl(this, internalConsumer);
/*  496 */       this.closeables.add(jmsConsumerImpl);
/*      */       
/*  498 */       if (getAutoStart()) {
/*  499 */         this.connection.start();
/*      */       }
/*      */     }
/*  502 */     catch (JMSException je) {
/*  503 */       if (Trace.isOn) {
/*  504 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createConsumer(Destination,String,boolean)", (Throwable)je);
/*      */       }
/*      */       
/*  507 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  508 */       if (Trace.isOn) {
/*  509 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createConsumer(Destination,String,boolean)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  512 */       throw traceRet1;
/*      */     } 
/*  514 */     if (Trace.isOn) {
/*  515 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createConsumer(Destination,String,boolean)", jmsConsumerImpl);
/*      */     }
/*      */     
/*  518 */     return (JMSConsumer)jmsConsumerImpl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSContext createContext(int arg0) throws JMSRuntimeException {
/*  526 */     if (Trace.isOn)
/*  527 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createContext(int)", new Object[] {
/*  528 */             Integer.valueOf(arg0)
/*      */           }); 
/*  530 */     JmsContextImpl context = null;
/*      */ 
/*      */ 
/*      */     
/*  534 */     if (inCICS() || inIMS()) {
/*  535 */       HashMap<String, Object> inserts = new HashMap<>();
/*  536 */       inserts.put("XMSC_INSERT_METHOD", "createContext(int)");
/*      */       
/*  538 */       String messageID = inCICS() ? "JMSCC6001" : "JMSCC6011";
/*  539 */       DetailedJMSException e = (DetailedJMSException)JmsErrorUtils.createException(messageID, inserts);
/*  540 */       JMSRuntimeException je = e.getUnchecked();
/*      */       
/*  542 */       if (Trace.isOn) {
/*  543 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createContext(int)", (Throwable)je, 1);
/*      */       }
/*      */       
/*  546 */       throw je;
/*      */     } 
/*      */     
/*      */     try {
/*  550 */       Session newSession = this.connection.createSession(arg0);
/*  551 */       context = new JmsContextImpl(this.connection, newSession);
/*  552 */       context.setAutoStart(getAutoStart());
/*      */     }
/*  554 */     catch (JMSException je) {
/*  555 */       if (Trace.isOn) {
/*  556 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createContext(int)", (Throwable)je);
/*      */       }
/*      */       
/*  559 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  560 */       if (Trace.isOn) {
/*  561 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createContext(int)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  564 */       throw traceRet1;
/*      */     } 
/*  566 */     if (Trace.isOn) {
/*  567 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createContext(int)", context);
/*      */     }
/*      */     
/*  570 */     return (JMSContext)context;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSConsumer createDurableConsumer(Topic arg0, String arg1) throws InvalidDestinationRuntimeException, IllegalStateRuntimeException, JMSRuntimeException {
/*  581 */     if (Trace.isOn) {
/*  582 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createDurableConsumer(Topic,String)", new Object[] { arg0, arg1 });
/*      */     }
/*      */ 
/*      */     
/*  586 */     JmsConsumerImpl jmsConsumerImpl = null;
/*      */     try {
/*  588 */       JmsMessageConsumerImpl internalConsumer = (JmsMessageConsumerImpl)this.session.createDurableSubscriber(arg0, arg1);
/*  589 */       jmsConsumerImpl = new JmsConsumerImpl(this, internalConsumer);
/*  590 */       this.closeables.add(jmsConsumerImpl);
/*      */       
/*  592 */       if (getAutoStart()) {
/*  593 */         this.connection.start();
/*      */       }
/*      */     }
/*  596 */     catch (JMSException je) {
/*  597 */       if (Trace.isOn) {
/*  598 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createDurableConsumer(Topic,String)", (Throwable)je);
/*      */       }
/*      */       
/*  601 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  602 */       if (Trace.isOn) {
/*  603 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createDurableConsumer(Topic,String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  606 */       throw traceRet1;
/*      */     } 
/*  608 */     if (Trace.isOn) {
/*  609 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createDurableConsumer(Topic,String)", jmsConsumerImpl);
/*      */     }
/*      */     
/*  612 */     return (JMSConsumer)jmsConsumerImpl;
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
/*      */   public JMSConsumer createDurableConsumer(Topic arg0, String arg1, String arg2, boolean arg3) throws InvalidDestinationRuntimeException, InvalidSelectorRuntimeException, IllegalStateRuntimeException, JMSRuntimeException {
/*  625 */     if (Trace.isOn) {
/*  626 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createDurableConsumer(Topic,String,String,boolean)", new Object[] { arg0, arg1, arg2, 
/*      */             
/*  628 */             Boolean.valueOf(arg3) });
/*      */     }
/*  630 */     JmsConsumerImpl jmsConsumerImpl = null;
/*      */     try {
/*  632 */       JmsMessageConsumerImpl internalConsumer = (JmsMessageConsumerImpl)this.session.createDurableSubscriber(arg0, arg1, arg2, arg3);
/*  633 */       jmsConsumerImpl = new JmsConsumerImpl(this, internalConsumer);
/*  634 */       this.closeables.add(jmsConsumerImpl);
/*      */       
/*  636 */       if (getAutoStart()) {
/*  637 */         this.connection.start();
/*      */       }
/*      */     }
/*  640 */     catch (JMSException je) {
/*  641 */       if (Trace.isOn) {
/*  642 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createDurableConsumer(Topic,String,String,boolean)", (Throwable)je);
/*      */       }
/*      */       
/*  645 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  646 */       if (Trace.isOn) {
/*  647 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createDurableConsumer(Topic,String,String,boolean)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  650 */       throw traceRet1;
/*      */     } 
/*  652 */     if (Trace.isOn) {
/*  653 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createDurableConsumer(Topic,String,String,boolean)", jmsConsumerImpl);
/*      */     }
/*      */     
/*  656 */     return (JMSConsumer)jmsConsumerImpl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MapMessage createMapMessage() throws JMSRuntimeException {
/*  664 */     if (Trace.isOn) {
/*  665 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createMapMessage()");
/*      */     }
/*  667 */     MapMessage message = null;
/*      */     try {
/*  669 */       message = this.session.createMapMessage();
/*      */     }
/*  671 */     catch (JMSException je) {
/*  672 */       if (Trace.isOn) {
/*  673 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createMapMessage()", (Throwable)je);
/*      */       }
/*      */       
/*  676 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  677 */       if (Trace.isOn) {
/*  678 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createMapMessage()", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  681 */       throw traceRet1;
/*      */     } 
/*  683 */     if (Trace.isOn) {
/*  684 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createMapMessage()", message);
/*      */     }
/*      */     
/*  687 */     return message;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Message createMessage() throws JMSRuntimeException {
/*  695 */     if (Trace.isOn) {
/*  696 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createMessage()");
/*      */     }
/*      */     
/*  699 */     Message message = null;
/*      */     try {
/*  701 */       message = this.session.createMessage();
/*      */     }
/*  703 */     catch (JMSException je) {
/*  704 */       if (Trace.isOn) {
/*  705 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createMessage()", (Throwable)je);
/*      */       }
/*      */       
/*  708 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  709 */       if (Trace.isOn) {
/*  710 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createMessage()", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  713 */       throw traceRet1;
/*      */     } 
/*  715 */     if (Trace.isOn) {
/*  716 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createMessage()", message);
/*      */     }
/*      */     
/*  719 */     return message;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ObjectMessage createObjectMessage() throws JMSRuntimeException {
/*  727 */     if (Trace.isOn) {
/*  728 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createObjectMessage()");
/*      */     }
/*      */     
/*  731 */     ObjectMessage message = null;
/*      */     try {
/*  733 */       message = this.session.createObjectMessage();
/*      */     }
/*  735 */     catch (JMSException je) {
/*  736 */       if (Trace.isOn) {
/*  737 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createObjectMessage()", (Throwable)je);
/*      */       }
/*      */       
/*  740 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  741 */       if (Trace.isOn) {
/*  742 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createObjectMessage()", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  745 */       throw traceRet1;
/*      */     } 
/*  747 */     if (Trace.isOn) {
/*  748 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createObjectMessage()", message);
/*      */     }
/*      */     
/*  751 */     return message;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ObjectMessage createObjectMessage(Serializable arg0) throws JMSRuntimeException {
/*  759 */     if (Trace.isOn) {
/*  760 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createObjectMessage(Serializable)", new Object[] { arg0 });
/*      */     }
/*      */     
/*  763 */     ObjectMessage message = null;
/*      */     try {
/*  765 */       message = this.session.createObjectMessage(arg0);
/*      */     }
/*  767 */     catch (JMSException je) {
/*  768 */       if (Trace.isOn) {
/*  769 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createObjectMessage(Serializable)", (Throwable)je);
/*      */       }
/*      */       
/*  772 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  773 */       if (Trace.isOn) {
/*  774 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createObjectMessage(Serializable)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  777 */       throw traceRet1;
/*      */     } 
/*  779 */     if (Trace.isOn) {
/*  780 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createObjectMessage(Serializable)", message);
/*      */     }
/*      */     
/*  783 */     return message;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSProducer createProducer() {
/*  791 */     if (Trace.isOn) {
/*  792 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createProducer()");
/*      */     }
/*      */     
/*  795 */     JmsProducerImpl jmsProducerImpl = null;
/*      */     
/*      */     try {
/*  798 */       JmsMessageProducerImpl internalProducer1 = getInternalProducer();
/*  799 */       jmsProducerImpl = new JmsProducerImpl(this, (Session)this.session.getSessionImpl(), internalProducer1);
/*  800 */       this.closeables.add(jmsProducerImpl);
/*      */     }
/*  802 */     catch (JMSException je) {
/*  803 */       if (Trace.isOn) {
/*  804 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createProducer()", (Throwable)je);
/*      */       }
/*      */       
/*  807 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  808 */       if (Trace.isOn) {
/*  809 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createProducer()", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  812 */       throw traceRet1;
/*      */     } 
/*      */     
/*  815 */     if (Trace.isOn) {
/*  816 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createProducer()", jmsProducerImpl);
/*      */     }
/*      */     
/*  819 */     return (JMSProducer)jmsProducerImpl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Queue createQueue(String arg0) throws JMSRuntimeException {
/*  828 */     if (Trace.isOn) {
/*  829 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createQueue(String)", new Object[] { arg0 });
/*      */     }
/*      */ 
/*      */     
/*  833 */     Queue queue = null;
/*      */     try {
/*  835 */       queue = this.session.createQueue(arg0);
/*      */     }
/*  837 */     catch (JMSException je) {
/*  838 */       if (Trace.isOn) {
/*  839 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createQueue(String)", (Throwable)je);
/*      */       }
/*      */       
/*  842 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  843 */       if (Trace.isOn) {
/*  844 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createQueue(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  847 */       throw traceRet1;
/*      */     } 
/*  849 */     if (Trace.isOn) {
/*  850 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createQueue(String)", queue);
/*      */     }
/*      */     
/*  853 */     return queue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName) throws JMSRuntimeException, InvalidDestinationRuntimeException, InvalidSelectorRuntimeException {
/*  864 */     if (Trace.isOn) {
/*  865 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createSharedConsumer(Topic,String)", new Object[] { topic, sharedSubscriptionName });
/*      */     }
/*      */ 
/*      */     
/*  869 */     checkValidFnUsage(JmsConnectionImpl.JMS2Function.SHARED_SUBSCRIPTIONS);
/*      */     
/*  871 */     JmsConsumerImpl jmsConsumerImpl = null;
/*      */     try {
/*  873 */       JmsMessageConsumerImpl internalConsumer = (JmsMessageConsumerImpl)this.session.createSharedConsumer(topic, sharedSubscriptionName);
/*  874 */       jmsConsumerImpl = new JmsConsumerImpl(this, internalConsumer);
/*  875 */       this.closeables.add(jmsConsumerImpl);
/*      */       
/*  877 */       if (getAutoStart()) {
/*  878 */         this.connection.start();
/*      */       
/*      */       }
/*      */     }
/*  882 */     catch (JMSException je) {
/*  883 */       if (Trace.isOn) {
/*  884 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createSharedConsumer(Topic,String)", (Throwable)je);
/*      */       }
/*      */       
/*  887 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  888 */       if (Trace.isOn) {
/*  889 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createSharedConsumer(Topic,String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  892 */       throw traceRet1;
/*      */     } 
/*  894 */     if (Trace.isOn) {
/*  895 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createSharedConsumer(Topic,String)", jmsConsumerImpl);
/*      */     }
/*      */     
/*  898 */     return (JMSConsumer)jmsConsumerImpl;
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
/*      */   public JMSConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName, String messageSelector) throws JMSRuntimeException, InvalidDestinationRuntimeException, InvalidSelectorRuntimeException {
/*  910 */     if (Trace.isOn) {
/*  911 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createSharedConsumer(Topic,String,String)", new Object[] { topic, sharedSubscriptionName, messageSelector });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  916 */     checkValidFnUsage(JmsConnectionImpl.JMS2Function.SHARED_SUBSCRIPTIONS);
/*      */     
/*  918 */     JmsConsumerImpl jmsConsumerImpl = null;
/*      */     try {
/*  920 */       JmsMessageConsumerImpl internalConsumer = (JmsMessageConsumerImpl)this.session.createSharedConsumer(topic, sharedSubscriptionName, messageSelector);
/*      */ 
/*      */       
/*  923 */       jmsConsumerImpl = new JmsConsumerImpl(this, internalConsumer);
/*  924 */       this.closeables.add(jmsConsumerImpl);
/*      */       
/*  926 */       if (getAutoStart()) {
/*  927 */         this.connection.start();
/*      */       
/*      */       }
/*      */     }
/*  931 */     catch (JMSException je) {
/*  932 */       if (Trace.isOn) {
/*  933 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createSharedConsumer(Topic,String,String)", (Throwable)je);
/*      */       }
/*      */       
/*  936 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  937 */       if (Trace.isOn) {
/*  938 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createSharedConsumer(Topic,String,String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  941 */       throw traceRet1;
/*      */     } 
/*  943 */     if (Trace.isOn) {
/*  944 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createSharedConsumer(Topic,String,String)", jmsConsumerImpl);
/*      */     }
/*      */     
/*  947 */     return (JMSConsumer)jmsConsumerImpl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JMSConsumer createSharedDurableConsumer(Topic topic, String name) throws InvalidDestinationRuntimeException, JMSRuntimeException {
/*  956 */     if (Trace.isOn) {
/*  957 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createSharedDurableConsumer(Topic,String)", new Object[] { topic, name });
/*      */     }
/*      */ 
/*      */     
/*  961 */     checkValidFnUsage(JmsConnectionImpl.JMS2Function.SHARED_SUBSCRIPTIONS);
/*      */     
/*  963 */     JmsConsumerImpl jmsConsumerImpl = null;
/*      */     try {
/*  965 */       JmsMessageConsumerImpl internalConsumer = (JmsMessageConsumerImpl)this.session.createSharedDurableConsumer(topic, name);
/*  966 */       jmsConsumerImpl = new JmsConsumerImpl(this, internalConsumer);
/*  967 */       this.closeables.add(jmsConsumerImpl);
/*      */       
/*  969 */       if (getAutoStart()) {
/*  970 */         this.connection.start();
/*      */       }
/*      */     }
/*  973 */     catch (JMSException je) {
/*  974 */       if (Trace.isOn) {
/*  975 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createSharedDurableConsumer(Topic,String)", (Throwable)je);
/*      */       }
/*      */       
/*  978 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*  979 */       if (Trace.isOn) {
/*  980 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createSharedDurableConsumer(Topic,String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  983 */       throw traceRet1;
/*      */     } 
/*  985 */     if (Trace.isOn) {
/*  986 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createSharedDurableConsumer(Topic,String)", jmsConsumerImpl);
/*      */     }
/*      */     
/*  989 */     return (JMSConsumer)jmsConsumerImpl;
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
/*      */   public JMSConsumer createSharedDurableConsumer(Topic topic, String name, String messageSelector) throws InvalidDestinationRuntimeException, InvalidSelectorRuntimeException, JMSRuntimeException {
/* 1002 */     if (Trace.isOn) {
/* 1003 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createSharedDurableConsumer(Topic,String,String)", new Object[] { topic, name, messageSelector });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1008 */     checkValidFnUsage(JmsConnectionImpl.JMS2Function.SHARED_SUBSCRIPTIONS);
/*      */     
/* 1010 */     JmsConsumerImpl jmsConsumerImpl = null;
/*      */     try {
/* 1012 */       JmsMessageConsumerImpl internalConsumer = (JmsMessageConsumerImpl)this.session.createSharedDurableConsumer(topic, name, messageSelector);
/* 1013 */       jmsConsumerImpl = new JmsConsumerImpl(this, internalConsumer);
/* 1014 */       this.closeables.add(jmsConsumerImpl);
/*      */       
/* 1016 */       if (getAutoStart()) {
/* 1017 */         this.connection.start();
/*      */       }
/*      */     }
/* 1020 */     catch (JMSException je) {
/* 1021 */       if (Trace.isOn) {
/* 1022 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createSharedDurableConsumer(Topic,String,String)", (Throwable)je);
/*      */       }
/*      */       
/* 1025 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1026 */       if (Trace.isOn) {
/* 1027 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createSharedDurableConsumer(Topic,String,String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1030 */       throw traceRet1;
/*      */     } 
/* 1032 */     if (Trace.isOn) {
/* 1033 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createSharedDurableConsumer(Topic,String,String)", jmsConsumerImpl);
/*      */     }
/*      */     
/* 1036 */     return (JMSConsumer)jmsConsumerImpl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public StreamMessage createStreamMessage() throws JMSRuntimeException {
/* 1045 */     if (Trace.isOn) {
/* 1046 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createStreamMessage()");
/*      */     }
/*      */     
/* 1049 */     StreamMessage message = null;
/*      */     try {
/* 1051 */       message = this.session.createStreamMessage();
/*      */     }
/* 1053 */     catch (JMSException je) {
/* 1054 */       if (Trace.isOn) {
/* 1055 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createStreamMessage()", (Throwable)je);
/*      */       }
/*      */       
/* 1058 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1059 */       if (Trace.isOn) {
/* 1060 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createStreamMessage()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1063 */       throw traceRet1;
/*      */     } 
/* 1065 */     if (Trace.isOn) {
/* 1066 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createStreamMessage()", message);
/*      */     }
/*      */     
/* 1069 */     return message;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TemporaryQueue createTemporaryQueue() throws JMSRuntimeException {
/* 1077 */     if (Trace.isOn) {
/* 1078 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTemporaryQueue()");
/*      */     }
/*      */     
/* 1081 */     TemporaryQueue tQueue = null;
/*      */     try {
/* 1083 */       tQueue = this.session.createTemporaryQueue();
/*      */     }
/* 1085 */     catch (JMSException je) {
/* 1086 */       if (Trace.isOn) {
/* 1087 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTemporaryQueue()", (Throwable)je);
/*      */       }
/*      */       
/* 1090 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1091 */       if (Trace.isOn) {
/* 1092 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTemporaryQueue()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1095 */       throw traceRet1;
/*      */     } 
/* 1097 */     if (Trace.isOn) {
/* 1098 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTemporaryQueue()", tQueue);
/*      */     }
/*      */     
/* 1101 */     return tQueue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TemporaryTopic createTemporaryTopic() throws JMSRuntimeException {
/* 1109 */     if (Trace.isOn) {
/* 1110 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTemporaryTopic()");
/*      */     }
/*      */     
/* 1113 */     TemporaryTopic tTopic = null;
/*      */     try {
/* 1115 */       tTopic = this.session.createTemporaryTopic();
/*      */     }
/* 1117 */     catch (JMSException je) {
/* 1118 */       if (Trace.isOn) {
/* 1119 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTemporaryTopic()", (Throwable)je);
/*      */       }
/*      */       
/* 1122 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1123 */       if (Trace.isOn) {
/* 1124 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTemporaryTopic()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1127 */       throw traceRet1;
/*      */     } 
/* 1129 */     if (Trace.isOn) {
/* 1130 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTemporaryTopic()", tTopic);
/*      */     }
/*      */     
/* 1133 */     return tTopic;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TextMessage createTextMessage() throws JMSRuntimeException {
/* 1141 */     if (Trace.isOn) {
/* 1142 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTextMessage()");
/*      */     }
/*      */     
/* 1145 */     TextMessage message = null;
/*      */     try {
/* 1147 */       message = this.session.createTextMessage();
/*      */     }
/* 1149 */     catch (JMSException je) {
/* 1150 */       if (Trace.isOn) {
/* 1151 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTextMessage()", (Throwable)je);
/*      */       }
/*      */       
/* 1154 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1155 */       if (Trace.isOn) {
/* 1156 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTextMessage()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1159 */       throw traceRet1;
/*      */     } 
/* 1161 */     if (Trace.isOn) {
/* 1162 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTextMessage()", message);
/*      */     }
/*      */     
/* 1165 */     return message;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TextMessage createTextMessage(String arg0) throws JMSRuntimeException {
/* 1173 */     if (Trace.isOn) {
/* 1174 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTextMessage(String)", new Object[] { arg0 });
/*      */     }
/*      */ 
/*      */     
/* 1178 */     TextMessage message = null;
/*      */     try {
/* 1180 */       message = this.session.createTextMessage(arg0);
/*      */     }
/* 1182 */     catch (JMSException je) {
/* 1183 */       if (Trace.isOn) {
/* 1184 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTextMessage(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1187 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1188 */       if (Trace.isOn) {
/* 1189 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTextMessage(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1192 */       throw traceRet1;
/*      */     } 
/* 1194 */     if (Trace.isOn) {
/* 1195 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTextMessage(String)", message);
/*      */     }
/*      */     
/* 1198 */     return message;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Topic createTopic(String arg0) throws JMSRuntimeException {
/* 1206 */     if (Trace.isOn) {
/* 1207 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTopic(String)", new Object[] { arg0 });
/*      */     }
/*      */ 
/*      */     
/* 1211 */     Topic topic = null;
/*      */     try {
/* 1213 */       topic = this.session.createTopic(arg0);
/*      */     }
/* 1215 */     catch (JMSException je) {
/* 1216 */       if (Trace.isOn) {
/* 1217 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTopic(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1220 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1221 */       if (Trace.isOn) {
/* 1222 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTopic(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1225 */       throw traceRet1;
/*      */     } 
/* 1227 */     if (Trace.isOn) {
/* 1228 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "createTopic(String)", topic);
/*      */     }
/*      */     
/* 1231 */     return topic;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getAutoStart() {
/* 1240 */     boolean autoStart = ((JmsConnectionImpl)this.connection).getAutoStart();
/* 1241 */     if (Trace.isOn) {
/* 1242 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "getAutoStart()", "getter", 
/* 1243 */           Boolean.valueOf(autoStart));
/*      */     }
/* 1245 */     return autoStart;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getClientID() throws JMSRuntimeException {
/* 1254 */     String clientID = null;
/*      */     try {
/* 1256 */       clientID = this.connection.getClientID();
/*      */     }
/* 1258 */     catch (JMSException je) {
/* 1259 */       if (Trace.isOn) {
/* 1260 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "getClientID()", (Throwable)je);
/*      */       }
/*      */       
/* 1263 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1264 */       if (Trace.isOn) {
/* 1265 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "getClientID()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1268 */       throw traceRet1;
/*      */     } 
/* 1270 */     if (Trace.isOn) {
/* 1271 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "getClientID()", "getter", clientID);
/*      */     }
/*      */     
/* 1274 */     return clientID;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ExceptionListener getExceptionListener() throws JMSRuntimeException {
/* 1283 */     ExceptionListener listener = null;
/*      */     try {
/* 1285 */       listener = this.connection.getExceptionListener();
/*      */     }
/* 1287 */     catch (JMSException je) {
/* 1288 */       if (Trace.isOn) {
/* 1289 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "getExceptionListener()", (Throwable)je);
/*      */       }
/*      */       
/* 1292 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1293 */       if (Trace.isOn) {
/* 1294 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "getExceptionListener()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1297 */       throw traceRet1;
/*      */     } 
/* 1299 */     if (Trace.isOn) {
/* 1300 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "getExceptionListener()", "getter", listener);
/*      */     }
/*      */     
/* 1303 */     return listener;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ConnectionMetaData getMetaData() throws JMSRuntimeException {
/* 1312 */     ConnectionMetaData metaData = null;
/*      */     try {
/* 1314 */       metaData = this.connection.getMetaData();
/*      */     }
/* 1316 */     catch (JMSException je) {
/* 1317 */       if (Trace.isOn) {
/* 1318 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "getMetaData()", (Throwable)je);
/*      */       }
/*      */       
/* 1321 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1322 */       if (Trace.isOn) {
/* 1323 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "getMetaData()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1326 */       throw traceRet1;
/*      */     } 
/* 1328 */     if (Trace.isOn) {
/* 1329 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "getMetaData()", "getter", metaData);
/*      */     }
/*      */     
/* 1332 */     return metaData;
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
/*      */   public int getSessionMode() throws JMSRuntimeException {
/* 1353 */     int sessionMode = -1;
/*      */     try {
/* 1355 */       sessionMode = this.session.getAcknowledgeMode();
/*      */ 
/*      */     
/*      */     }
/* 1359 */     catch (JMSException je) {
/* 1360 */       if (Trace.isOn) {
/* 1361 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "getSessionMode()", (Throwable)je);
/*      */       }
/*      */       
/* 1364 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1365 */       if (Trace.isOn) {
/* 1366 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "getSessionMode()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1369 */       throw traceRet1;
/*      */     } 
/* 1371 */     if (Trace.isOn) {
/* 1372 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "getSessionMode()", "getter", 
/* 1373 */           Integer.valueOf(sessionMode));
/*      */     }
/* 1375 */     return sessionMode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getTransacted() throws JMSRuntimeException {
/* 1384 */     boolean transacted = false;
/*      */     try {
/* 1386 */       transacted = this.session.getTransacted();
/*      */     }
/* 1388 */     catch (JMSException je) {
/* 1389 */       if (Trace.isOn) {
/* 1390 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "getTransacted()", (Throwable)je);
/*      */       }
/*      */       
/* 1393 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1394 */       if (Trace.isOn) {
/* 1395 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "getTransacted()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1398 */       throw traceRet1;
/*      */     } 
/* 1400 */     if (Trace.isOn) {
/* 1401 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "getTransacted()", "getter", 
/* 1402 */           Boolean.valueOf(transacted));
/*      */     }
/* 1404 */     return transacted;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void recover() throws IllegalStateRuntimeException, JMSRuntimeException {
/* 1412 */     if (Trace.isOn) {
/* 1413 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "recover()");
/*      */     }
/*      */     
/*      */     try {
/* 1417 */       this.session.recover();
/*      */     }
/* 1419 */     catch (JMSException je) {
/* 1420 */       if (Trace.isOn) {
/* 1421 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "recover()", (Throwable)je);
/*      */       }
/* 1423 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1424 */       if (Trace.isOn) {
/* 1425 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "recover()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1428 */       throw traceRet1;
/*      */     } 
/* 1430 */     if (Trace.isOn) {
/* 1431 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "recover()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void rollback() throws IllegalStateRuntimeException, JMSRuntimeException {
/* 1441 */     if (Trace.isOn) {
/* 1442 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "rollback()");
/*      */     }
/*      */     
/*      */     try {
/* 1446 */       this.session.rollback();
/*      */     }
/* 1448 */     catch (JMSException je) {
/* 1449 */       if (Trace.isOn) {
/* 1450 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "rollback()", (Throwable)je);
/*      */       }
/* 1452 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1453 */       if (Trace.isOn) {
/* 1454 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "rollback()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1457 */       throw traceRet1;
/*      */     } 
/* 1459 */     if (Trace.isOn) {
/* 1460 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "rollback()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAutoStart(boolean arg0) throws IllegalStateRuntimeException {
/* 1471 */     if (Trace.isOn) {
/* 1472 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "setAutoStart(boolean)", "setter", 
/* 1473 */           Boolean.valueOf(arg0));
/*      */     }
/*      */ 
/*      */     
/* 1477 */     ((JmsConnectionImpl)this.connection).setAutoStart(arg0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setClientID(String arg0) throws InvalidClientIDRuntimeException, IllegalStateRuntimeException, JMSRuntimeException {
/* 1485 */     if (Trace.isOn) {
/* 1486 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "setClientID(String)", "setter", arg0);
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1491 */       this.connection.setClientID(arg0);
/*      */     }
/* 1493 */     catch (JMSException je) {
/* 1494 */       if (Trace.isOn) {
/* 1495 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "setClientID(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1498 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1499 */       if (Trace.isOn) {
/* 1500 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "setClientID(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1503 */       throw traceRet1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExceptionListener(ExceptionListener arg0) throws IllegalStateRuntimeException, JMSRuntimeException {
/* 1512 */     if (Trace.isOn) {
/* 1513 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "setExceptionListener(ExceptionListener)", "setter", arg0);
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1518 */       this.connection.setExceptionListener(arg0);
/*      */     }
/* 1520 */     catch (JMSException je) {
/* 1521 */       if (Trace.isOn) {
/* 1522 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "setExceptionListener(ExceptionListener)", (Throwable)je);
/*      */       }
/*      */       
/* 1525 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1526 */       if (Trace.isOn) {
/* 1527 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "setExceptionListener(ExceptionListener)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1530 */       throw traceRet1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void start() throws IllegalStateRuntimeException, JMSRuntimeException {
/* 1539 */     if (Trace.isOn) {
/* 1540 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "start()");
/*      */     }
/*      */     
/*      */     try {
/* 1544 */       this.connection.start();
/*      */     }
/* 1546 */     catch (JMSException je) {
/* 1547 */       if (Trace.isOn) {
/* 1548 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "start()", (Throwable)je);
/*      */       }
/* 1550 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1551 */       if (Trace.isOn) {
/* 1552 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "start()", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1555 */       throw traceRet1;
/*      */     } 
/* 1557 */     if (Trace.isOn) {
/* 1558 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "start()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void stop() throws IllegalStateRuntimeException, JMSRuntimeException {
/* 1568 */     if (Trace.isOn) {
/* 1569 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "stop()");
/*      */     }
/*      */     
/*      */     try {
/* 1573 */       this.connection.stop();
/*      */     }
/* 1575 */     catch (JMSException je) {
/* 1576 */       if (Trace.isOn) {
/* 1577 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "stop()", (Throwable)je);
/*      */       }
/* 1579 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1580 */       if (Trace.isOn) {
/* 1581 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "stop()", (Throwable)traceRet1);
/*      */       }
/* 1583 */       throw traceRet1;
/*      */     } 
/* 1585 */     if (Trace.isOn) {
/* 1586 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "stop()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void unsubscribe(String arg0) throws JMSRuntimeException, InvalidDestinationRuntimeException {
/* 1596 */     if (Trace.isOn) {
/* 1597 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "unsubscribe(String)", new Object[] { arg0 });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1602 */       this.session.unsubscribe(arg0);
/*      */     }
/* 1604 */     catch (JMSException je) {
/* 1605 */       if (Trace.isOn) {
/* 1606 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "unsubscribe(String)", (Throwable)je);
/*      */       }
/*      */       
/* 1609 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 1610 */       if (Trace.isOn) {
/* 1611 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "unsubscribe(String)", (Throwable)traceRet1);
/*      */       }
/*      */       
/* 1614 */       throw traceRet1;
/*      */     } 
/* 1616 */     if (Trace.isOn) {
/* 1617 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "unsubscribe(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void removeCloseable(JmsCloseableResource closeable) {
/* 1623 */     if (Trace.isOn) {
/* 1624 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "removeCloseable(JmsCloseableResource)", new Object[] { closeable });
/*      */     }
/*      */     
/* 1627 */     this.closeables.remove(closeable);
/* 1628 */     if (Trace.isOn) {
/* 1629 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "removeCloseable(JmsCloseableResource)");
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
/*      */   private static class JmsCloseableResourceSet
/*      */     implements Iterable<JmsCloseableResource>
/*      */   {
/* 1646 */     Map<WeakReference<JmsCloseableResource>, Object> underlying = new ConcurrentHashMap<>();
/*      */     
/* 1648 */     Object dummyEntry = new Object();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1654 */     ReferenceQueue<JmsCloseableResource> refQueue = new ReferenceQueue<>();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void add(JmsCloseableResource key) {
/* 1660 */       if (Trace.isOn) {
/* 1661 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsCloseableResourceSet", "add(JmsCloseableResource)", new Object[] { key });
/*      */       }
/*      */       
/* 1664 */       this.underlying.put(new WeakReference<>(key, this.refQueue), this.dummyEntry);
/* 1665 */       cleanup();
/* 1666 */       if (Trace.isOn) {
/* 1667 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsCloseableResourceSet", "add(JmsCloseableResource)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void clear() {
/* 1674 */       if (Trace.isOn) {
/* 1675 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsCloseableResourceSet", "clear()");
/*      */       }
/* 1677 */       this.underlying.clear();
/* 1678 */       if (Trace.isOn) {
/* 1679 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsCloseableResourceSet", "clear()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void remove(JmsCloseableResource key) {
/* 1688 */       if (Trace.isOn) {
/* 1689 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsCloseableResourceSet", "remove(JmsCloseableResource)", new Object[] { key });
/*      */       }
/*      */       
/* 1692 */       this.underlying.remove(new WeakReference<>(key));
/* 1693 */       cleanup();
/* 1694 */       if (Trace.isOn) {
/* 1695 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsCloseableResourceSet", "remove(JmsCloseableResource)");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Iterator<JmsCloseableResource> iterator() {
/* 1706 */       if (Trace.isOn) {
/* 1707 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsCloseableResourceSet", "iterator()");
/*      */       }
/* 1709 */       cleanup();
/* 1710 */       Iterator<JmsCloseableResource> traceRet1 = new Iterator<JmsCloseableResource>()
/*      */         {
/* 1712 */           Iterator<WeakReference<JmsCloseableResource>> i = JmsContextImpl.JmsCloseableResourceSet.this.underlying.keySet().iterator();
/*      */ 
/*      */           
/*      */           public boolean hasNext() {
/* 1716 */             boolean traceRet2 = this.i.hasNext();
/* 1717 */             if (Trace.isOn) {
/* 1718 */               Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsCloseableResourceSet", "iterator()", 
/* 1719 */                   Boolean.valueOf(traceRet2), 2);
/*      */             }
/* 1721 */             return traceRet2;
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public JmsCloseableResource next() {
/* 1729 */             JmsCloseableResource traceRet3 = ((WeakReference<JmsCloseableResource>)this.i.next()).get();
/* 1730 */             if (Trace.isOn) {
/* 1731 */               Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsCloseableResourceSet", "iterator()", traceRet3, 3);
/*      */             }
/*      */             
/* 1734 */             return traceRet3;
/*      */           }
/*      */ 
/*      */           
/*      */           public void remove() {
/* 1739 */             this.i.remove();
/*      */           }
/*      */         };
/* 1742 */       if (Trace.isOn) {
/* 1743 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsCloseableResourceSet", "iterator()", traceRet1, 1);
/*      */       }
/*      */       
/* 1746 */       return traceRet1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void cleanup() {
/* 1754 */       if (Trace.isOn) {
/* 1755 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsCloseableResourceSet", "cleanup()");
/*      */       }
/*      */       Reference<JmsCloseableResource> ref;
/* 1758 */       while ((ref = (Reference)this.refQueue.poll()) != null) {
/* 1759 */         JmsCloseableResource closeable = ref.get();
/* 1760 */         if (closeable != null) {
/* 1761 */           closeable.close();
/*      */         }
/* 1763 */         this.underlying.remove(ref);
/*      */       } 
/* 1765 */       if (Trace.isOn)
/* 1766 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsCloseableResourceSet", "cleanup()"); 
/*      */     }
/*      */     
/*      */     private JmsCloseableResourceSet() {}
/*      */   }
/*      */   
/*      */   void checkValidFnUsage(JmsConnectionImpl.JMS2Function fn) {
/* 1773 */     if (Trace.isOn) {
/* 1774 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "checkValidFnUsage(JmsConnectionImpl.JMS2Function)", new Object[] { fn });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1779 */       ((JmsConnectionImpl)this.connection).checkValidFnUsage(fn);
/*      */     }
/* 1781 */     catch (JMSException je) {
/* 1782 */       if (Trace.isOn) {
/* 1783 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "checkValidFnUsage(JmsConnectionImpl.JMS2Function)", (Throwable)je);
/*      */       }
/*      */       
/* 1786 */       JMSRuntimeException traceRet2 = JmsErrorUtils.convertJMSException(je);
/* 1787 */       if (Trace.isOn) {
/* 1788 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "checkValidFnUsage(JmsConnectionImpl.JMS2Function)", (Throwable)traceRet2);
/*      */       }
/*      */       
/* 1791 */       throw traceRet2;
/*      */     } 
/* 1793 */     if (Trace.isOn) {
/* 1794 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "checkValidFnUsage(JmsConnectionImpl.JMS2Function)");
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
/*      */   public void dump(PrintWriter pw, int level) {
/* 1806 */     String prefix = Trace.buildPrefix(level);
/* 1807 */     pw.format("%s%s@%x%n", new Object[] { prefix, getClass().getName(), Integer.valueOf(hashCode()) });
/* 1808 */     if (this.creator != null) {
/* 1809 */       pw.format("%s  created by \"%s\" (id: %d) @ %s - stack :%n", new Object[] { prefix, this.creator
/* 1810 */             .getName(), Long.valueOf(this.creator.getId()), Trace.formatTimeStamp(this.createTime) });
/*      */     }
/*      */     
/* 1813 */     ((JmsConnectionImpl)this.connection).dump(pw, level + 1);
/*      */     try {
/* 1815 */       this.session.getSessionImpl().dump(pw, level + 1);
/*      */     }
/* 1817 */     catch (JMSException e) {
/*      */       
/* 1819 */       pw.format("Failed to get Session: %s%n", new Object[] { e.getMessage() });
/*      */     } 
/* 1821 */     pw.format("%s  [Closeable Resources]%n", new Object[] { prefix });
/* 1822 */     for (JmsCloseableResource closeable : this.closeables) {
/* 1823 */       closeable.dump(pw, level + 1);
/* 1824 */       pw.println();
/*      */     } 
/* 1826 */     pw.format("%s  [End of Closeable Resources]%n", new Object[] { prefix });
/* 1827 */     super.dump(pw, level + 1);
/*      */   }
/*      */   
/*      */   private boolean inCICS() throws JMSRuntimeException {
/* 1831 */     return ((JmsConnectionImpl)this.connection).inCICS();
/*      */   }
/*      */   
/*      */   private boolean inIMS() throws JMSRuntimeException {
/* 1835 */     return ((JmsConnectionImpl)this.connection).inIMS();
/*      */   }
/*      */ 
/*      */   
/*      */   protected Session getSession() {
/* 1840 */     return (Session)this.session;
/*      */   }
/*      */   
/*      */   protected void setSession(SessionWrapper s) {
/* 1844 */     this.session = s;
/*      */   }
/*      */   
/*      */   protected Connection getConnection() {
/* 1848 */     return this.connection;
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
/*      */   protected static class SessionWrapper
/*      */     implements XASession
/*      */   {
/* 1865 */     private Connection owner = null;
/*      */ 
/*      */     
/* 1868 */     private Session theSession = null;
/*      */ 
/*      */     
/* 1871 */     private XASession cachedXASession = null;
/* 1872 */     private Session cachedNonXASession = null;
/*      */     
/* 1874 */     private int acknowledgeMode = 1;
/*      */ 
/*      */     
/*      */     private boolean usingXA = false;
/*      */ 
/*      */ 
/*      */     
/*      */     public SessionWrapper(Connection connection) {
/* 1882 */       this(connection, 1);
/*      */     }
/*      */     
/*      */     public SessionWrapper(Connection connection, int sessionMode) {
/* 1886 */       this.owner = connection;
/* 1887 */       this.acknowledgeMode = sessionMode;
/*      */     }
/*      */     
/*      */     public SessionWrapper(Connection connection, Session session) {
/* 1891 */       this.owner = connection;
/* 1892 */       if (session instanceof XASession) {
/* 1893 */         this.cachedXASession = (XASession)session;
/* 1894 */         this.theSession = (Session)this.cachedXASession;
/*      */       } else {
/*      */         
/* 1897 */         this.cachedNonXASession = session;
/* 1898 */         this.theSession = this.cachedNonXASession;
/*      */       } 
/*      */       
/*      */       try {
/* 1902 */         if (session != null) {
/* 1903 */           this.acknowledgeMode = session.getAcknowledgeMode();
/*      */         }
/*      */       }
/* 1906 */       catch (JMSException e) {
/* 1907 */         HashMap<String, Object> data = new HashMap<>();
/* 1908 */         data.put("theConnection", connection);
/* 1909 */         data.put("theSession", this.theSession);
/* 1910 */         Trace.ffst(this, " SessionWrapper.<init>(Connection,Session)", "XJ00C001", data, JMSException.class);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected JmsSessionImpl getSessionImpl() throws JMSException {
/* 1918 */       setSession();
/* 1919 */       return (JmsSessionImpl)this.theSession;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private synchronized void setSession() throws JMSException {
/* 1927 */       if (this.usingXA) {
/* 1928 */         if (this.cachedXASession == null) {
/* 1929 */           this.cachedXASession = ((XAConnection)this.owner).createXASession();
/*      */         }
/*      */         
/* 1932 */         this.theSession = (Session)this.cachedXASession;
/*      */       } else {
/*      */         
/* 1935 */         if (this.cachedNonXASession == null) {
/* 1936 */           this.cachedNonXASession = this.owner.createSession(false, this.acknowledgeMode);
/*      */         }
/*      */         
/* 1939 */         this.theSession = this.cachedNonXASession;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void close() throws JMSException {
/* 1948 */       if (this.theSession == null) {
/*      */         return;
/*      */       }
/*      */       
/* 1952 */       this.theSession.close();
/* 1953 */       this.theSession = null;
/*      */       
/* 1955 */       if (this.cachedNonXASession != null) {
/* 1956 */         this.cachedNonXASession.close();
/* 1957 */         this.cachedNonXASession = null;
/*      */       } 
/*      */       
/* 1960 */       if (this.cachedXASession != null) {
/* 1961 */         this.cachedXASession.close();
/* 1962 */         this.cachedXASession = null;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public QueueBrowser createBrowser(Queue arg0) throws JMSException {
/* 1970 */       setSession();
/* 1971 */       return this.theSession.createBrowser(arg0);
/*      */     }
/*      */ 
/*      */     
/*      */     public QueueBrowser createBrowser(Queue arg0, String arg1) throws JMSException {
/* 1976 */       setSession();
/* 1977 */       return this.theSession.createBrowser(arg0, arg1);
/*      */     }
/*      */ 
/*      */     
/*      */     public BytesMessage createBytesMessage() throws JMSException {
/* 1982 */       setSession();
/* 1983 */       return this.theSession.createBytesMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     public MessageConsumer createConsumer(Destination arg0) throws JMSException {
/* 1988 */       setSession();
/* 1989 */       return this.theSession.createConsumer(arg0);
/*      */     }
/*      */ 
/*      */     
/*      */     public MessageConsumer createConsumer(Destination arg0, String arg1) throws JMSException {
/* 1994 */       setSession();
/* 1995 */       return this.theSession.createConsumer(arg0, arg1);
/*      */     }
/*      */ 
/*      */     
/*      */     public MessageConsumer createConsumer(Destination arg0, String arg1, boolean arg2) throws JMSException {
/* 2000 */       setSession();
/* 2001 */       return this.theSession.createConsumer(arg0, arg1, arg2);
/*      */     }
/*      */ 
/*      */     
/*      */     public MessageConsumer createDurableConsumer(Topic arg0, String arg1) throws JMSException {
/* 2006 */       setSession();
/* 2007 */       return this.theSession.createDurableConsumer(arg0, arg1);
/*      */     }
/*      */ 
/*      */     
/*      */     public MessageConsumer createDurableConsumer(Topic arg0, String arg1, String arg2, boolean arg3) throws JMSException {
/* 2012 */       setSession();
/* 2013 */       return this.theSession.createDurableConsumer(arg0, arg1, arg2, arg3);
/*      */     }
/*      */ 
/*      */     
/*      */     public TopicSubscriber createDurableSubscriber(Topic arg0, String arg1) throws JMSException {
/* 2018 */       setSession();
/* 2019 */       return this.theSession.createDurableSubscriber(arg0, arg1);
/*      */     }
/*      */ 
/*      */     
/*      */     public TopicSubscriber createDurableSubscriber(Topic arg0, String arg1, String arg2, boolean arg3) throws JMSException {
/* 2024 */       setSession();
/* 2025 */       return this.theSession.createDurableSubscriber(arg0, arg1, arg2, arg3);
/*      */     }
/*      */ 
/*      */     
/*      */     public MapMessage createMapMessage() throws JMSException {
/* 2030 */       setSession();
/* 2031 */       return this.theSession.createMapMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     public Message createMessage() throws JMSException {
/* 2036 */       setSession();
/* 2037 */       return this.theSession.createMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     public ObjectMessage createObjectMessage() throws JMSException {
/* 2042 */       setSession();
/* 2043 */       return this.theSession.createObjectMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     public ObjectMessage createObjectMessage(Serializable arg0) throws JMSException {
/* 2048 */       setSession();
/* 2049 */       return this.theSession.createObjectMessage(arg0);
/*      */     }
/*      */ 
/*      */     
/*      */     public MessageProducer createProducer(Destination arg0) throws JMSException {
/* 2054 */       setSession();
/* 2055 */       return this.theSession.createProducer(arg0);
/*      */     }
/*      */ 
/*      */     
/*      */     public Queue createQueue(String arg0) throws JMSException {
/* 2060 */       setSession();
/* 2061 */       return this.theSession.createQueue(arg0);
/*      */     }
/*      */ 
/*      */     
/*      */     public MessageConsumer createSharedConsumer(Topic arg0, String arg1) throws JMSException {
/* 2066 */       setSession();
/* 2067 */       return this.theSession.createSharedConsumer(arg0, arg1);
/*      */     }
/*      */ 
/*      */     
/*      */     public MessageConsumer createSharedConsumer(Topic arg0, String arg1, String arg2) throws JMSException {
/* 2072 */       setSession();
/* 2073 */       return this.theSession.createSharedConsumer(arg0, arg1, arg2);
/*      */     }
/*      */ 
/*      */     
/*      */     public MessageConsumer createSharedDurableConsumer(Topic arg0, String arg1) throws JMSException {
/* 2078 */       setSession();
/* 2079 */       return this.theSession.createSharedDurableConsumer(arg0, arg1);
/*      */     }
/*      */ 
/*      */     
/*      */     public MessageConsumer createSharedDurableConsumer(Topic arg0, String arg1, String arg2) throws JMSException {
/* 2084 */       setSession();
/* 2085 */       return this.theSession.createSharedDurableConsumer(arg0, arg1, arg2);
/*      */     }
/*      */ 
/*      */     
/*      */     public StreamMessage createStreamMessage() throws JMSException {
/* 2090 */       setSession();
/* 2091 */       return this.theSession.createStreamMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     public TemporaryQueue createTemporaryQueue() throws JMSException {
/* 2096 */       setSession();
/* 2097 */       return this.theSession.createTemporaryQueue();
/*      */     }
/*      */ 
/*      */     
/*      */     public TemporaryTopic createTemporaryTopic() throws JMSException {
/* 2102 */       setSession();
/* 2103 */       return this.theSession.createTemporaryTopic();
/*      */     }
/*      */ 
/*      */     
/*      */     public TextMessage createTextMessage() throws JMSException {
/* 2108 */       setSession();
/* 2109 */       return this.theSession.createTextMessage();
/*      */     }
/*      */ 
/*      */     
/*      */     public TextMessage createTextMessage(String arg0) throws JMSException {
/* 2114 */       setSession();
/* 2115 */       return this.theSession.createTextMessage(arg0);
/*      */     }
/*      */ 
/*      */     
/*      */     public Topic createTopic(String arg0) throws JMSException {
/* 2120 */       setSession();
/* 2121 */       return this.theSession.createTopic(arg0);
/*      */     }
/*      */ 
/*      */     
/*      */     public int getAcknowledgeMode() throws JMSException {
/* 2126 */       setSession();
/* 2127 */       return this.theSession.getAcknowledgeMode();
/*      */     }
/*      */ 
/*      */     
/*      */     public MessageListener getMessageListener() throws JMSException {
/* 2132 */       setSession();
/* 2133 */       return this.theSession.getMessageListener();
/*      */     }
/*      */ 
/*      */     
/*      */     public void recover() throws JMSException {
/* 2138 */       setSession();
/* 2139 */       this.theSession.recover();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void run() {
/*      */       try {
/* 2150 */         setSession();
/*      */       }
/* 2152 */       catch (JMSException je) {
/* 2153 */         JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 2154 */         throw traceRet1;
/*      */       } 
/* 2156 */       this.theSession.run();
/*      */     }
/*      */ 
/*      */     
/*      */     public void setMessageListener(MessageListener arg0) throws JMSException {
/* 2161 */       setSession();
/* 2162 */       this.theSession.setMessageListener(arg0);
/*      */     }
/*      */ 
/*      */     
/*      */     public void unsubscribe(String arg0) throws JMSException {
/* 2167 */       setSession();
/* 2168 */       this.theSession.unsubscribe(arg0);
/*      */     }
/*      */ 
/*      */     
/*      */     public void commit() throws JMSException {
/* 2173 */       setSession();
/* 2174 */       this.theSession.commit();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Session getSession() throws JMSException {
/* 2180 */       HashMap<String, Object> data = new HashMap<>();
/* 2181 */       data.put("theSession", this.theSession);
/* 2182 */       data.put("usingXA", Boolean.valueOf(this.usingXA));
/* 2183 */       Trace.ffst(this, "getSession()", "XJ00C003", data, JMSException.class);
/* 2184 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean getTransacted() throws JMSException {
/* 2189 */       setSession();
/* 2190 */       return this.theSession.getTransacted();
/*      */     }
/*      */     
/*      */     public void setUsingXA(boolean usingXA) {
/* 2194 */       this.usingXA = usingXA;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public XAResource getXAResource() {
/*      */       try {
/* 2201 */         this.usingXA = true;
/* 2202 */         setSession();
/*      */       }
/* 2204 */       catch (JMSException je) {
/* 2205 */         JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 2206 */         throw traceRet1;
/*      */       } 
/* 2208 */       return ((XASession)this.theSession).getXAResource();
/*      */     }
/*      */ 
/*      */     
/*      */     public void rollback() throws JMSException {
/* 2213 */       setSession();
/* 2214 */       this.theSession.rollback();
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/* 2219 */       return String.valueOf(this.theSession);
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
/*      */   public void setUsingXA(boolean usingXA) {
/* 2236 */     this.session.setUsingXA(usingXA);
/*      */   }
/*      */   
/*      */   protected static void proxyUnFixClientID(JmsContextImpl context) {
/* 2240 */     Connection connection = context.getConnection();
/*      */     
/* 2242 */     if (connection instanceof JmsConnectionImpl) {
/* 2243 */       JmsConnectionImpl jmsConnection = (JmsConnectionImpl)connection;
/* 2244 */       jmsConnection.unFixClientID();
/*      */     
/*      */     }
/* 2247 */     else if (Trace.isOn) {
/* 2248 */       HashMap<String, String> data = new HashMap<>();
/* 2249 */       data.put("Connection class", connection.getClass().getName());
/* 2250 */       Trace.traceData("com.ibm.msg.client.jms.internal.JmsContextImpl", "proxyUnFixClientID(JmsContextImpl)", "Connection is not a JmsConnectionImpl", data);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public JmsContextImpl(Connection connection, Session session) {
/* 2257 */     this.internalProducer = null; if (Trace.isOn)
/*      */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "<init>(Connection,Session)", new Object[] { connection, session });  this.connection = connection; ((JmsConnectionImpl)connection).addContext(this);
/*      */     ((JmsConnectionImpl)connection).unFixClientID();
/*      */     this.session = new SessionWrapper(connection, session);
/*      */     Trace.registerFFSTObject(this);
/*      */     if (Trace.isOn)
/* 2263 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsContextImpl", "<init>(Connection,Session)");  } private JmsMessageProducerImpl getInternalProducer() throws JMSException { if (this.internalProducer == null || this.internalProducer.state.isClosed()) {
/* 2264 */       this.internalProducer = (JmsMessageProducerImpl)this.session.createProducer(null);
/*      */     }
/*      */     
/* 2267 */     return this.internalProducer; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void validateProducerProperty(String name, Object value) throws JMSException {
/* 2277 */     this.internalProducer.validateProperty(name, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearBackingProducer() {
/* 2286 */     if (this.internalProducer != null) {
/*      */       try {
/* 2288 */         HashMap<String, String> data = new HashMap<>();
/* 2289 */         data.put("Internal Producer", this.internalProducer.toString());
/* 2290 */         Trace.traceData("com.ibm.msg.client.jms.internal.JmsContextImpl", "clearBackingProducer(JmsContextImpl)", "Closing the Internal Message Producer", data);
/*      */         
/* 2292 */         this.internalProducer.close();
/*      */       }
/* 2294 */       catch (JMSException e) {
/* 2295 */         HashMap<String, Object> data = new HashMap<>();
/* 2296 */         data.put("theConnection", this.connection);
/* 2297 */         data.put("internalProducer", this.internalProducer);
/* 2298 */         Trace.ffst(this, "JmsContextImpl.clearBackingProducer()", "XJ00C002", data, JMSException.class);
/*      */       } 
/* 2300 */       this.internalProducer = null;
/*      */     } else {
/*      */       
/* 2303 */       Trace.traceData("com.ibm.msg.client.jms.internal.JmsContextImpl", "clearBackingProducer(JmsContextImpl)", "Internal Message Producer is null", null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 2314 */     JSONObject resultObject = new JSONObject();
/*      */     try {
/* 2316 */       resultObject.put("ObjectId", stringifyMe());
/* 2317 */       resultObject.put("Connection", jsonify(this.connection));
/* 2318 */       resultObject.put("Session", jsonify(this.session.theSession));
/*      */     }
/* 2320 */     catch (JSONException jSONException) {}
/*      */ 
/*      */     
/* 2323 */     return resultObject.toString();
/*      */   }
/*      */   
/*      */   private JSONObject jsonify(Object o) {
/* 2327 */     return (o instanceof JmsPropertyContext) ? ((JmsPropertyContext)o).toJson() : null;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsContextImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */