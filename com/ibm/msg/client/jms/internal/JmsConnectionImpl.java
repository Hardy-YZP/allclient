/*      */ package com.ibm.msg.client.jms.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.locking.TraceableReentrantLock;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsConnection;
/*      */ import com.ibm.msg.client.jms.JmsConnectionBrowser;
/*      */ import com.ibm.msg.client.jms.JmsDestination;
/*      */ import com.ibm.msg.client.jms.JmsMessageReference;
/*      */ import com.ibm.msg.client.jms.JmsMessageReferenceHandler;
/*      */ import com.ibm.msg.client.jms.JmsTopic;
/*      */ import com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl;
/*      */ import com.ibm.msg.client.jms.admin.JmsDestinationImpl;
/*      */ import com.ibm.msg.client.provider.ProviderConnection;
/*      */ import com.ibm.msg.client.provider.ProviderConnectionBrowser;
/*      */ import com.ibm.msg.client.provider.ProviderDestination;
/*      */ import com.ibm.msg.client.provider.ProviderFactoryFactory;
/*      */ import com.ibm.msg.client.provider.ProviderJmsFactory;
/*      */ import com.ibm.msg.client.provider.ProviderMessageFactory;
/*      */ import com.ibm.msg.client.provider.ProviderMessageReference;
/*      */ import com.ibm.msg.client.provider.ProviderMessageReferenceHandler;
/*      */ import com.ibm.msg.client.provider.ProviderOnMessageController;
/*      */ import com.ibm.msg.client.provider.ProviderSession;
/*      */ import java.io.IOException;
/*      */ import java.io.NotSerializableException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import javax.jms.ConnectionConsumer;
/*      */ import javax.jms.ConnectionMetaData;
/*      */ import javax.jms.Destination;
/*      */ import javax.jms.ExceptionListener;
/*      */ import javax.jms.IllegalStateException;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.ServerSessionPool;
/*      */ import javax.jms.Session;
/*      */ import javax.jms.Topic;
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
/*      */ public class JmsConnectionImpl
/*      */   extends JmsPropertyContextImpl
/*      */   implements JmsConnection
/*      */ {
/*      */   private static final long serialVersionUID = -1683009756330801802L;
/*      */   
/*      */   static {
/*   89 */     if (Trace.isOn) {
/*   90 */       Trace.data("com.ibm.msg.client.jms.internal.JmsConnectionImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsConnectionImpl.java");
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
/*  110 */   protected static int DEFAULT_DUPS_THRESHOLD = 20;
/*      */   
/*      */   private static class ClientIDSetter
/*      */   {
/*      */     private Thread thread;
/*      */     private StackTraceElement[] stack;
/*      */     private Date when;
/*      */     
/*      */     ClientIDSetter() {
/*  119 */       if (Trace.isOn) {
/*  120 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.ClientIDSetter", "<init>()");
/*      */       }
/*  122 */       this.thread = Thread.currentThread();
/*  123 */       this.stack = this.thread.getStackTrace();
/*  124 */       this.when = new Date();
/*      */       
/*  126 */       if (Trace.isOn) {
/*  127 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.ClientIDSetter", "<init>()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/*  134 */       if (Trace.isOn) {
/*  135 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.ClientIDSetter", "toString()");
/*      */       }
/*  137 */       StringBuilder buff = new StringBuilder();
/*  138 */       buff.append(this.thread.toString());
/*  139 */       buff.append('\n');
/*  140 */       buff.append('\t');
/*  141 */       buff.append(this.when.toString());
/*  142 */       buff.append('\n');
/*  143 */       for (StackTraceElement ste : this.stack) {
/*  144 */         buff.append(String.format("\t: %s%n", new Object[] { ste.toString() }));
/*      */       } 
/*  146 */       String traceRet1 = buff.toString();
/*  147 */       if (Trace.isOn) {
/*  148 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.ClientIDSetter", "toString()", traceRet1);
/*      */       }
/*  150 */       return traceRet1;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*  155 */   protected static final Map<String, ClientIDSetter> clientIDSetters = Collections.synchronizedMap(new HashMap<>());
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ProviderFactoryFactory providerFactory;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ProviderMessageFactory providerMessageFactory;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ProviderJmsFactory providerJmsFactory;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ProviderConnection providerConnection;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ConnectionMetaData metaData;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JmsProviderExceptionListener exceptionListenerProxy;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private volatile boolean clientIDFixed = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean autoStart = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected List<JmsSessionImpl> sessions;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected List<JmsSessionImpl> pendingSessions;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected List<JmsSessionImpl> pendingSessionRemovals;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected volatile boolean forceSessionsPending = false;
/*      */ 
/*      */ 
/*      */   
/*  219 */   protected TraceableReentrantLock pendingSessionsLock = new TraceableReentrantLock();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private List<JmsConnectionBrowserImpl> browsers;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private List<JmsDestination> temporaryDestinations;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  242 */   protected State state = new State(2);
/*      */ 
/*      */   
/*      */   private String connectionTypeName;
/*      */ 
/*      */   
/*  248 */   private Set<JmsContextImpl> containingContexts = new HashSet<>();
/*      */   
/*      */   private boolean receiveStoppedConnMsgLogged = false;
/*  251 */   private Object receiveStoppedConnMsgLoggedLock = new Object();
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isCICSUnmanaged = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isIMS = false;
/*      */ 
/*      */ 
/*      */   
/*      */   public JmsConnectionImpl(JmsConnectionFactoryImpl connectionFactory) throws JMSException {
/*  264 */     super((Map<String, Object>)connectionFactory, true);
/*  265 */     if (Trace.isOn) {
/*  266 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "<init>(JmsConnectionFactoryImpl)", new Object[] { connectionFactory });
/*      */     }
/*      */ 
/*      */     
/*  270 */     if (propertyExists("XMSC_CLIENT_ID")) {
/*  271 */       String clientID = getStringProperty("XMSC_CLIENT_ID");
/*  272 */       if (clientID != null && clientID.length() > 0) {
/*  273 */         checkDuplicateClientID(clientID, true);
/*      */       }
/*      */     } 
/*      */     
/*  277 */     this.sessions = new ArrayList<>();
/*  278 */     this.pendingSessions = new ArrayList<>();
/*  279 */     this.pendingSessionRemovals = new ArrayList<>();
/*  280 */     this.browsers = new ArrayList<>();
/*  281 */     this.exceptionListenerProxy = new JmsProviderExceptionListener(getIntProperty("XMSC_ASYNC_EXCEPTIONS"));
/*      */     
/*  283 */     this.temporaryDestinations = new ArrayList<>();
/*  284 */     this.state.setState(2);
/*      */     
/*  286 */     this.connectionTypeName = getStringProperty("XMSC_CONNECTION_TYPE_NAME");
/*  287 */     this.providerFactory = ((JmsFactoryFactoryImpl)JmsFactoryFactoryImpl.getInstance(this.connectionTypeName)).getProviderFactoryFactory();
/*  288 */     this.providerMessageFactory = this.providerFactory.getMessageFactory();
/*  289 */     this.providerJmsFactory = this.providerFactory.getJmsFactory();
/*      */     
/*      */     try {
/*  292 */       setLongProperty("XMSC_OBJECT_IDENTITY", System.identityHashCode(this));
/*      */     }
/*  294 */     catch (JMSException e) {
/*  295 */       if (Trace.isOn) {
/*  296 */         Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "<init>(JmsConnectionFactoryImpl)", "Caught expected exception", e);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  303 */     if (Trace.isOn) {
/*  304 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "<init>(JmsConnectionFactoryImpl)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/*  311 */     PropertyStore.register("com.ibm.mq.jms.SupportMQExtensions", false);
/*      */   }
/*      */   
/*      */   private void checkDuplicateClientID(String clientID, boolean addToSet) throws JMSException {
/*  315 */     if (Trace.isOn) {
/*  316 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "checkDuplicateClientID(String,boolean)", new Object[] { clientID, 
/*  317 */             Boolean.valueOf(addToSet) });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  322 */     boolean enforceClientIdUniqueness = !PropertyStore.getBooleanPropertyObject("com.ibm.mq.jms.SupportMQExtensions").booleanValue();
/*      */     
/*  324 */     if (enforceClientIdUniqueness) {
/*  325 */       ClientIDSetter previousSetter = clientIDSetters.get(clientID);
/*  326 */       if (previousSetter != null) {
/*  327 */         HashMap<String, Object> inserts = new HashMap<>();
/*  328 */         inserts.put("XMSC_INSERT_VALUE", clientID);
/*  329 */         inserts.put("XMSC_INSERT_STRING", previousSetter.toString());
/*      */         
/*  331 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0111", inserts);
/*  332 */         if (Trace.isOn) {
/*  333 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "checkDuplicateClientID(String,boolean)", (Throwable)je);
/*      */         }
/*      */         
/*  336 */         throw je;
/*      */       } 
/*      */       
/*  339 */       if (addToSet) {
/*  340 */         clientIDSetters.put(clientID, new ClientIDSetter());
/*      */       }
/*      */     } 
/*      */     
/*  344 */     if (Trace.isOn) {
/*  345 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "checkDuplicateClientID(String,boolean)");
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
/*      */   public void checkClose(JmsContextImpl context) throws JMSException {
/*  358 */     if (Trace.isOn) {
/*  359 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "checkClose(JmsContextImpl)", new Object[] { context });
/*      */     }
/*      */     
/*  362 */     removeContext(context);
/*  363 */     if (this.containingContexts.isEmpty()) {
/*  364 */       close();
/*      */     }
/*  366 */     if (Trace.isOn) {
/*  367 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "checkClose(JmsContextImpl)");
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
/*      */   public void close() throws JMSException {
/*  379 */     if (Trace.isOn) {
/*  380 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "close()");
/*      */     }
/*      */     
/*  383 */     if (this.state.close()) {
/*  384 */       if (Trace.isOn) {
/*  385 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "close()", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  390 */     JmsTls tls = JmsTls.getInstance();
/*      */     
/*  392 */     if (tls.inCompletionListener() && this.sessions.contains(tls.completionListenerSession())) {
/*  393 */       HashMap<String, Object> inserts = new HashMap<>();
/*  394 */       inserts.put("XMSC_INSERT_METHOD", "close()");
/*  395 */       inserts.put("XMSC_INSERT_NAME", "completion");
/*      */       
/*  397 */       IllegalStateException ise = (IllegalStateException)JmsErrorUtils.createException("JMSCC0012", inserts);
/*  398 */       if (Trace.isOn) {
/*  399 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "close()", (Throwable)ise, 1);
/*      */       }
/*      */       
/*  402 */       throw ise;
/*      */     } 
/*      */     
/*  405 */     if (tls.inMessageListener() && this.sessions.contains(tls.messageListenerSession())) {
/*  406 */       HashMap<String, Object> inserts = new HashMap<>();
/*  407 */       inserts.put("XMSC_INSERT_METHOD", "close()");
/*  408 */       inserts.put("XMSC_INSERT_NAME", "message");
/*      */       
/*  410 */       IllegalStateException ise = (IllegalStateException)JmsErrorUtils.createException("JMSCC0012", inserts);
/*  411 */       if (Trace.isOn) {
/*  412 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "close()", (Throwable)ise, 2);
/*      */       }
/*      */       
/*  415 */       throw ise;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  423 */     dropClientId();
/*      */ 
/*      */ 
/*      */     
/*  427 */     this.exceptionListenerProxy.setExceptionListener(null);
/*      */     
/*      */     try {
/*  430 */       synchronized (this.state)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  444 */         List<JmsConnectionBrowserImpl> clonedBrowsers = new ArrayList<>(this.browsers);
/*  445 */         for (JmsConnectionBrowserImpl browser : clonedBrowsers) {
/*  446 */           browser.close(true);
/*      */         }
/*      */         
/*  449 */         clonedBrowsers = null;
/*      */         
/*  451 */         assert this.browsers.size() == 0 : Trace.ffstAssertion(this, "close()", "XJ006002", new Object[] { "assertion=>browsers.size()==0", this.browsers });
/*      */ 
/*      */         
/*  454 */         List<JmsSessionImpl> clonedSessions = new ArrayList<>(this.sessions);
/*  455 */         for (JmsSessionImpl session : clonedSessions) {
/*  456 */           session.close(true);
/*      */         }
/*      */         
/*  459 */         clonedSessions = null;
/*      */         
/*  461 */         assert this.sessions.size() == 0 : Trace.ffstAssertion(this, "close()", "XJ006001", new Object[] { "assertion=>sessions.size()==0", this.sessions });
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  471 */     catch (JMSException je) {
/*  472 */       if (Trace.isOn) {
/*  473 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "close()", (Throwable)je);
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
/*  484 */       if (Trace.isOn) {
/*  485 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "close()", (Throwable)je, 3);
/*      */       }
/*  487 */       throw je;
/*      */     } finally {
/*      */       
/*  490 */       if (Trace.isOn) {
/*  491 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "close()", 1);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  497 */       if (this.providerConnection != null) {
/*  498 */         this.providerConnection.close();
/*      */       }
/*      */     } 
/*      */     
/*  502 */     if (Trace.isOn) {
/*  503 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "close()", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dropClientId() {
/*  513 */     if (Trace.isOn) {
/*  514 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "dropClientId()");
/*      */     }
/*  516 */     fixClientID();
/*      */     
/*      */     try {
/*  519 */       if (propertyExists("XMSC_CLIENT_ID")) {
/*  520 */         clientIDSetters.remove(getStringProperty("XMSC_CLIENT_ID"));
/*      */       }
/*      */     }
/*  523 */     catch (JMSException e) {
/*  524 */       if (Trace.isOn) {
/*  525 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "dropClientId()", (Throwable)e);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  530 */     if (Trace.isOn) {
/*  531 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "dropClientId()");
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
/*      */   public ConnectionConsumer createConnectionConsumer(Destination destination, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
/*  552 */     if (Trace.isOn) {
/*  553 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createConnectionConsumer(Destination,String,ServerSessionPool,int)", new Object[] { destination, messageSelector, sessionPool, 
/*      */             
/*  555 */             Integer.valueOf(maxMessages) });
/*      */     }
/*      */ 
/*      */     
/*  559 */     if (this.isCICSUnmanaged || this.isIMS) {
/*  560 */       HashMap<String, Object> inserts = new HashMap<>();
/*  561 */       inserts.put("XMSC_INSERT_METHOD", "createConnectionConsumer(Destination,String,ServerSessionPool,int)");
/*      */       
/*  563 */       String messageID = this.isCICSUnmanaged ? "JMSCC6001" : "JMSCC6011";
/*  564 */       JMSException je = (JMSException)JmsErrorUtils.createException(messageID, inserts);
/*      */       
/*  566 */       if (Trace.isOn) {
/*  567 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createConnectionConsumer(Destination,String,ServerSessionPool,int)", (Throwable)je, 1);
/*      */       }
/*      */       
/*  570 */       throw je;
/*      */     } 
/*      */     
/*  573 */     this.state.checkNotClosed("JMSCC0008");
/*      */ 
/*      */     
/*  576 */     if (destination == null || (!(destination instanceof com.ibm.msg.client.jms.JmsQueue) && !(destination instanceof JmsTopic))) {
/*  577 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0085", null);
/*  578 */       if (Trace.isOn) {
/*  579 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createConnectionConsumer(Destination,String,ServerSessionPool,int)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  582 */       throw je;
/*      */     } 
/*      */     
/*  585 */     if (null == sessionPool) {
/*  586 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1084", null);
/*  587 */       if (Trace.isOn) {
/*  588 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createConnectionConsumer(Destination,String,ServerSessionPool,int)", (Throwable)je, 3);
/*      */       }
/*      */       
/*  591 */       throw je;
/*      */     } 
/*      */     
/*  594 */     if (maxMessages <= 0) {
/*  595 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1083", null);
/*  596 */       if (Trace.isOn) {
/*  597 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createConnectionConsumer(Destination,String,ServerSessionPool,int)", (Throwable)je, 4);
/*      */       }
/*      */       
/*  600 */       throw je;
/*      */     } 
/*      */     
/*  603 */     fixClientID();
/*  604 */     JmsConnectionConsumerImpl connectionConsumer = new JmsConnectionConsumerImpl(this, (JmsDestination)destination, messageSelector, sessionPool, maxMessages);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  611 */     if (Trace.isOn) {
/*  612 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createConnectionConsumer(Destination,String,ServerSessionPool,int)", connectionConsumer);
/*      */     }
/*      */     
/*  615 */     return (ConnectionConsumer)connectionConsumer;
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
/*      */   public ConnectionConsumer createDurableConnectionConsumer(Topic destination, String subscriptionName, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
/*  637 */     if (Trace.isOn) {
/*  638 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", new Object[] { destination, subscriptionName, messageSelector, sessionPool, 
/*      */             
/*  640 */             Integer.valueOf(maxMessages) });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  645 */     if (this.isCICSUnmanaged || this.isIMS) {
/*  646 */       HashMap<String, Object> inserts = new HashMap<>();
/*  647 */       inserts.put("XMSC_INSERT_METHOD", "createDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)");
/*      */       
/*  649 */       String messageID = this.isCICSUnmanaged ? "JMSCC6001" : "JMSCC6011";
/*  650 */       JMSException je = (JMSException)JmsErrorUtils.createException(messageID, inserts);
/*      */       
/*  652 */       if (Trace.isOn) {
/*  653 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je, 1);
/*      */       }
/*      */       
/*  656 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/*  660 */     this.state.checkNotClosed("JMSCC0008");
/*      */ 
/*      */     
/*  663 */     if (destination == null || !(destination instanceof JmsTopic)) {
/*  664 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0085", null);
/*  665 */       if (Trace.isOn) {
/*  666 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  669 */       throw je;
/*      */     } 
/*      */     
/*  672 */     JmsDestinationImpl jmsTopic = (JmsDestinationImpl)destination;
/*  673 */     ProviderDestination providerDestination = JmsDestinationImplProxy.getProviderDestination(jmsTopic);
/*      */     
/*  675 */     if (providerDestination.isTemporary()) {
/*  676 */       HashMap<String, Object> inserts = new HashMap<>();
/*  677 */       inserts.put("XMSC_INSERT_TYPE", "Topic");
/*  678 */       inserts.put("XMSC_DESTINATION_NAME", jmsTopic.getTopicName());
/*  679 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0093", inserts);
/*  680 */       if (Trace.isOn) {
/*  681 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je, 3);
/*      */       }
/*      */       
/*  684 */       throw je;
/*      */     } 
/*      */     
/*  687 */     if (null == sessionPool) {
/*  688 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1084", null);
/*  689 */       if (Trace.isOn) {
/*  690 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je, 4);
/*      */       }
/*      */       
/*  693 */       throw je;
/*      */     } 
/*      */     
/*  696 */     if (maxMessages <= 0) {
/*  697 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1083", null);
/*  698 */       if (Trace.isOn) {
/*  699 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je, 5);
/*      */       }
/*      */       
/*  702 */       throw je;
/*      */     } 
/*      */     
/*  705 */     if (null == subscriptionName || subscriptionName.length() == 0) {
/*  706 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC3039", null);
/*  707 */       if (Trace.isOn) {
/*  708 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je, 6);
/*      */       }
/*      */       
/*  711 */       throw je;
/*      */     } 
/*      */     
/*  714 */     fixClientID();
/*      */ 
/*      */     
/*  717 */     String clientID = getClientID();
/*      */     
/*  719 */     if (clientID == null || "".equals(clientID)) {
/*  720 */       JMSException je5 = (JMSException)JmsErrorUtils.createException("JMSCC0101", null);
/*  721 */       if (Trace.isOn) {
/*  722 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je5, 7);
/*      */       }
/*      */       
/*  725 */       throw je5;
/*      */     } 
/*      */     
/*  728 */     JmsConnectionConsumerImpl connectionConsumer = new JmsConnectionConsumerImpl(this, (JmsDestination)destination, subscriptionName, messageSelector, sessionPool, maxMessages, false, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  736 */     JmsDestinationImplProxy.incrementUseCount((JmsDestinationImpl)destination);
/*      */     
/*  738 */     if (Trace.isOn) {
/*  739 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", connectionConsumer);
/*      */     }
/*      */ 
/*      */     
/*  743 */     return (ConnectionConsumer)connectionConsumer;
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
/*      */   public Session createSession(boolean transacted, int ackModeParam) throws JMSException {
/*  756 */     if (Trace.isOn) {
/*  757 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSession(boolean,int)", new Object[] {
/*  758 */             Boolean.valueOf(transacted), 
/*  759 */             Integer.valueOf(ackModeParam)
/*      */           });
/*      */     }
/*  762 */     int ackMode = ackModeParam;
/*      */ 
/*      */     
/*  765 */     this.state.checkNotClosed("JMSCC0008");
/*      */ 
/*      */ 
/*      */     
/*  769 */     if (this.isCICSUnmanaged || this.isIMS) {
/*  770 */       synchronized (this.state) {
/*  771 */         if (this.sessions.size() >= 1) {
/*  772 */           String messageID = this.isCICSUnmanaged ? "JMSCC6003" : "JMSCC6013";
/*  773 */           JMSException je = (JMSException)JmsErrorUtils.createException(messageID, null);
/*      */           
/*  775 */           if (Trace.isOn) {
/*  776 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSession(boolean,int)", (Throwable)je, 1);
/*      */           }
/*      */           
/*  779 */           throw je;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*  784 */     if (!transacted && !validNonTransactedAckmode(ackMode)) {
/*  785 */       HashMap<String, Object> inserts = new HashMap<>();
/*  786 */       inserts.put("XMSC_ACKNOWLEDGE_MODE", Integer.valueOf(ackMode));
/*  787 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0097", inserts);
/*  788 */       if (Trace.isOn) {
/*  789 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSession(boolean,int)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  792 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/*  796 */     fixClientID();
/*      */ 
/*      */     
/*  799 */     if (transacted) {
/*  800 */       ackMode = 0;
/*      */     }
/*      */ 
/*      */     
/*  804 */     JmsSessionImpl jmsSession = instantiateSession(transacted, ackMode);
/*  805 */     jmsSession.setIntProperty("XMSC_ACKNOWLEDGE_MODE", ackMode);
/*  806 */     jmsSession.setBooleanProperty("XMSC_TRANSACTED", transacted);
/*      */ 
/*      */     
/*  809 */     if (this instanceof javax.jms.XAConnection || this instanceof javax.jms.XAQueueConnection || this instanceof javax.jms.XATopicConnection) {
/*  810 */       short newFlags = (short)(jmsSession.getShortProperty("XMSC_ADMIN_OBJECT_TYPE") & 0xFFFFFFBF);
/*  811 */       jmsSession.setShortProperty("XMSC_ADMIN_OBJECT_TYPE", newFlags);
/*      */     } 
/*      */     
/*  814 */     ProviderSession providerSession = this.providerConnection.createSession(jmsSession);
/*  815 */     jmsSession.setProviderSession(providerSession);
/*      */     
/*  817 */     boolean addToMainList = true;
/*      */     
/*  819 */     this.pendingSessionsLock.lock();
/*      */     try {
/*  821 */       if (this.forceSessionsPending) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  828 */         this.pendingSessions.add(jmsSession);
/*  829 */         addToMainList = false;
/*  830 */         if (Trace.isOn) {
/*  831 */           Trace.data(this, "createSession(boolean,int)", "added session to pending list", null);
/*      */         }
/*      */       } 
/*      */     } finally {
/*      */       
/*  836 */       this.pendingSessionsLock.unlock();
/*      */     } 
/*      */     
/*  839 */     if (addToMainList) {
/*  840 */       synchronized (this.state) {
/*      */ 
/*      */         
/*  843 */         if (this.state.equals(1)) {
/*  844 */           jmsSession.start();
/*      */         }
/*  846 */         this.sessions.add(jmsSession);
/*  847 */         if (Trace.isOn) {
/*  848 */           Trace.data(this, "createSession(boolean,int)", "added session to main list", null);
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*  853 */     if (Trace.isOn) {
/*  854 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSession(boolean,int)", jmsSession);
/*      */     }
/*      */     
/*  857 */     return (Session)jmsSession;
/*      */   }
/*      */   
/*      */   protected boolean validNonTransactedAckmode(int ackMode) {
/*  861 */     if (Trace.isOn) {
/*  862 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "validNonTransactedAckmode(int)", new Object[] {
/*  863 */             Integer.valueOf(ackMode)
/*      */           });
/*      */     }
/*  866 */     switch (ackMode) {
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/*  870 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "validNonTransactedAckmode(int)", 
/*  871 */             Boolean.valueOf(true));
/*  872 */         return true;
/*      */     } 
/*      */     
/*  875 */     if (Trace.isOn) {
/*  876 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "validNonTransactedAckmode(int)", 
/*  877 */           Boolean.valueOf(false));
/*      */     }
/*  879 */     return false;
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
/*      */   public String getClientID() throws JMSException {
/*  891 */     this.state.checkNotClosed("JMSCC0008");
/*      */     
/*  893 */     String cliendId = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  899 */     cliendId = getStringProperty("XMSC_CLIENT_ID");
/*      */     
/*  901 */     if (Trace.isOn) {
/*  902 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "getClientID()", "getter", cliendId);
/*      */     }
/*      */     
/*  905 */     return cliendId;
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
/*      */   public ExceptionListener getExceptionListener() throws JMSException {
/*  917 */     this.state.checkNotClosed("JMSCC0008");
/*      */     
/*  919 */     ExceptionListener traceRet1 = this.exceptionListenerProxy.getExceptionListener();
/*  920 */     if (Trace.isOn) {
/*  921 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "getExceptionListener()", "getter", traceRet1);
/*      */     }
/*      */     
/*  924 */     return traceRet1;
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
/*      */   public ConnectionMetaData getMetaData() throws JMSException {
/*  936 */     this.state.checkNotClosed("JMSCC0008");
/*      */     
/*  938 */     if (this.metaData == null) {
/*  939 */       this.metaData = (ConnectionMetaData)new JmsConnectionMetaDataImpl(this.providerConnection);
/*      */     }
/*      */     
/*  942 */     if (Trace.isOn) {
/*  943 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "getMetaData()", "getter", this.metaData);
/*      */     }
/*      */     
/*  946 */     return this.metaData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setClientID(String clientID) throws JMSException {
/*  957 */     if (Trace.isOn) {
/*  958 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "setClientID(String)", "setter", clientID);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  964 */     if (this.isCICSUnmanaged || this.isIMS) {
/*  965 */       HashMap<String, Object> inserts = new HashMap<>();
/*  966 */       inserts.put("XMSC_INSERT_METHOD", "setClientID(String)");
/*      */       
/*  968 */       String messageID = this.isCICSUnmanaged ? "JMSCC6001" : "JMSCC6011";
/*  969 */       JMSException je = (JMSException)JmsErrorUtils.createException(messageID, inserts);
/*      */       
/*  971 */       if (Trace.isOn) {
/*  972 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "setClientID(String)", (Throwable)je, 4);
/*      */       }
/*      */       
/*  975 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  983 */     checkDuplicateClientID(clientID, false);
/*      */ 
/*      */     
/*  986 */     if (this.clientIDFixed) {
/*  987 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC3031", null);
/*  988 */       if (Trace.isOn) {
/*  989 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "setClientID(String)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  992 */       throw je;
/*      */     } 
/*      */     
/*  995 */     if (null == clientID || clientID.length() == 0) {
/*  996 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0002", null);
/*  997 */       if (Trace.isOn) {
/*  998 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "setClientID(String)", (Throwable)je, 3);
/*      */       }
/*      */       
/* 1001 */       throw je;
/*      */     } 
/*      */     
/* 1004 */     if (propertyExists("XMSC_CLIENT_ID") && null != getStringProperty("XMSC_CLIENT_ID") && 
/*      */       
/* 1006 */       getStringProperty("XMSC_CLIENT_ID").trim().length() > 0) {
/* 1007 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC3032", null);
/* 1008 */       if (Trace.isOn) {
/* 1009 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "setClientID(String)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 1012 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1018 */     setStringProperty("XMSC_CLIENT_ID", clientID);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1024 */     for (JmsSessionImpl session : this.sessions) {
/* 1025 */       session.setStringProperty("XMSC_CLIENT_ID", clientID);
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
/*      */   public void setExceptionListener(ExceptionListener exceptionListener) throws JMSException {
/* 1037 */     if (Trace.isOn) {
/* 1038 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "setExceptionListener(ExceptionListener)", new Object[] { exceptionListener });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1044 */     if (this.isCICSUnmanaged || this.isIMS) {
/* 1045 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1046 */       inserts.put("XMSC_INSERT_METHOD", "setExceptionListener(ExceptionListener)");
/*      */       
/* 1048 */       String messageID = this.isCICSUnmanaged ? "JMSCC6001" : "JMSCC6011";
/* 1049 */       JMSException je = (JMSException)JmsErrorUtils.createException(messageID, inserts);
/*      */       
/* 1051 */       if (Trace.isOn) {
/* 1052 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "setExceptionListener(ExceptionListener))", (Throwable)je, 1);
/*      */       }
/*      */       
/* 1055 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/* 1059 */     this.state.checkNotClosed("JMSCC0008");
/*      */     
/* 1061 */     fixClientID();
/*      */     
/* 1063 */     if (this.exceptionListenerProxy.getExceptionListener() != exceptionListener) {
/* 1064 */       this.exceptionListenerProxy.setExceptionListener(exceptionListener);
/*      */       
/* 1066 */       if (exceptionListener == null) {
/* 1067 */         this.providerConnection.setExceptionListener(null);
/*      */       } else {
/*      */         
/* 1070 */         this.providerConnection.setExceptionListener(this.exceptionListenerProxy);
/*      */       } 
/*      */     } 
/*      */     
/* 1074 */     if (Trace.isOn) {
/* 1075 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "setExceptionListener(ExceptionListener)");
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
/*      */   public void start() throws JMSException {
/* 1087 */     if (Trace.isOn) {
/* 1088 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "start()");
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
/* 1104 */     synchronized (this.state) {
/* 1105 */       for (int i = 0; i < this.sessions.size(); i++) {
/* 1106 */         ((JmsSessionImpl)this.sessions.get(i)).enableMessageReferenceProcessing();
/*      */       }
/*      */     } 
/*      */     
/* 1110 */     this.providerConnection.start();
/*      */     
/* 1112 */     fixClientID();
/*      */     
/* 1114 */     synchronized (this.state) {
/*      */ 
/*      */       
/* 1117 */       this.state.checkNotClosed("JMSCC0008");
/*      */       
/* 1119 */       if (this.state.equals(2)) {
/* 1120 */         int i; for (i = 0; i < this.sessions.size(); i++) {
/* 1121 */           ((JmsSessionImpl)this.sessions.get(i)).start();
/*      */         }
/* 1123 */         for (i = 0; i < this.browsers.size(); i++) {
/* 1124 */           ((JmsConnectionBrowserImpl)this.browsers.get(i)).start();
/*      */         }
/* 1126 */         this.state.setState(1);
/*      */       } 
/*      */     } 
/*      */     
/* 1130 */     synchronized (this.receiveStoppedConnMsgLoggedLock) {
/*      */       
/* 1132 */       this.receiveStoppedConnMsgLogged = true;
/*      */     } 
/*      */     
/* 1135 */     if (Trace.isOn) {
/* 1136 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "start()");
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
/*      */   public void stop() throws JMSException {
/* 1148 */     if (Trace.isOn) {
/* 1149 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "stop()");
/*      */     }
/*      */     
/* 1152 */     JmsTls tls = JmsTls.getInstance();
/*      */ 
/*      */     
/* 1155 */     int providerLegVersion = getMetaData().getProviderMajorVersion();
/*      */     
/* 1157 */     boolean providerStopRequestMade = false;
/*      */     
/* 1159 */     if (tls.inMessageListener() && this.sessions.contains(tls.messageListenerSession())) {
/* 1160 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1161 */       inserts.put("XMSC_INSERT_METHOD", "stop()");
/* 1162 */       inserts.put("XMSC_INSERT_NAME", "message");
/*      */       
/* 1164 */       IllegalStateException ise = (IllegalStateException)JmsErrorUtils.createException("JMSCC0012", inserts);
/* 1165 */       if (Trace.isOn) {
/* 1166 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "stop()", (Throwable)ise, 1);
/*      */       }
/* 1168 */       throw ise;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1173 */     if (this.isCICSUnmanaged || this.isIMS) {
/* 1174 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1175 */       inserts.put("XMSC_INSERT_METHOD", "stop()");
/*      */       
/* 1177 */       String messageID = this.isCICSUnmanaged ? "JMSCC6001" : "JMSCC6011";
/* 1178 */       JMSException je = (JMSException)JmsErrorUtils.createException(messageID, inserts);
/*      */       
/* 1180 */       if (Trace.isOn) {
/* 1181 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "stop()", (Throwable)je, 1);
/*      */       }
/*      */       
/* 1184 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1193 */     fixClientID();
/*      */ 
/*      */     
/* 1196 */     if (this.providerConnection instanceof ProviderOnMessageController) {
/* 1197 */       ((ProviderOnMessageController)this.providerConnection).initiateConnectionStop();
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
/* 1208 */     this.pendingSessionsLock.lock();
/*      */     try {
/* 1210 */       this.forceSessionsPending = true;
/*      */     } finally {
/*      */       
/* 1213 */       this.pendingSessionsLock.unlock();
/*      */     } 
/*      */     
/* 1216 */     synchronized (this.state) {
/*      */ 
/*      */       
/* 1219 */       this.state.checkNotClosed("JMSCC0008");
/*      */       
/* 1221 */       if (this.state.equals(1) || this.state.equals(2)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1247 */         if (providerLegVersion == 6) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1252 */           providerStopRequestMade = true;
/* 1253 */           this.providerConnection.stop();
/*      */         } 
/*      */         int i;
/* 1256 */         for (i = 0; i < this.sessions.size(); i++) {
/* 1257 */           ((JmsSessionImpl)this.sessions.get(i)).stop();
/*      */         }
/*      */         
/* 1260 */         for (i = 0; i < this.browsers.size(); i++) {
/* 1261 */           ((JmsConnectionBrowserImpl)this.browsers.get(i)).stop();
/*      */         }
/* 1263 */         this.state.setState(2);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1270 */       this.pendingSessionsLock.lock();
/*      */       try {
/* 1272 */         this.sessions.addAll(this.pendingSessions);
/* 1273 */         this.pendingSessions.clear();
/* 1274 */         this.sessions.removeAll(this.pendingSessionRemovals);
/* 1275 */         this.pendingSessionRemovals.clear();
/* 1276 */         this.forceSessionsPending = false;
/*      */       } finally {
/*      */         
/* 1279 */         this.pendingSessionsLock.unlock();
/*      */       } 
/*      */       
/* 1282 */       if (!providerStopRequestMade) {
/* 1283 */         this.providerConnection.stop();
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1289 */     if (this.providerConnection instanceof ProviderOnMessageController) {
/* 1290 */       ((ProviderOnMessageController)this.providerConnection).finishConnectionStop();
/*      */     }
/*      */     
/* 1293 */     if (Trace.isOn) {
/* 1294 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "stop()");
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
/*      */   public JmsConnectionBrowser createConnectionBrowser(JmsDestination destination, String selector, JmsMessageReferenceHandler messageRefHandler, int quantityHint) throws JMSException {
/* 1316 */     if (Trace.isOn) {
/* 1317 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createConnectionBrowser(JmsDestination,String,JmsMessageReferenceHandler,int)", new Object[] { destination, selector, messageRefHandler, 
/*      */             
/* 1319 */             Integer.valueOf(quantityHint) });
/*      */     }
/*      */ 
/*      */     
/* 1323 */     this.state.checkNotClosed("JMSCC0008");
/*      */ 
/*      */     
/* 1326 */     if (quantityHint != 0 && quantityHint != 1 && quantityHint != 2) {
/* 1327 */       IllegalArgumentException iae = (IllegalArgumentException)JmsErrorUtils.createException("JMSCC1094", null);
/* 1328 */       if (Trace.isOn) {
/* 1329 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createConnectionBrowser(JmsDestination,String,JmsMessageReferenceHandler,int)", iae, 1);
/*      */       }
/*      */       
/* 1332 */       throw iae;
/*      */     } 
/*      */     
/* 1335 */     if (destination == null || (!(destination instanceof com.ibm.msg.client.jms.JmsQueue) && !(destination instanceof JmsTopic))) {
/* 1336 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0085", null);
/* 1337 */       if (Trace.isOn) {
/* 1338 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createConnectionBrowser(JmsDestination,String,JmsMessageReferenceHandler,int)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1341 */       throw je;
/*      */     } 
/*      */     
/* 1344 */     if (null == messageRefHandler) {
/* 1345 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1093", null);
/* 1346 */       if (Trace.isOn) {
/* 1347 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createConnectionBrowser(JmsDestination,String,JmsMessageReferenceHandler,int)", (Throwable)je, 3);
/*      */       }
/*      */       
/* 1350 */       throw je;
/*      */     } 
/*      */     
/* 1353 */     fixClientID();
/*      */ 
/*      */     
/* 1356 */     JmsConnectionBrowserImpl connBrowser = new JmsConnectionBrowserImpl(this, destination, selector, messageRefHandler, quantityHint, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1361 */     ProviderDestination provDest = JmsDestinationImplProxy.getProviderDestination((JmsDestinationImpl)destination);
/* 1362 */     ProviderMessageReferenceHandler pmrh = new JmsProviderMessageRefHandler(messageRefHandler);
/*      */ 
/*      */     
/* 1365 */     ProviderConnectionBrowser providerConnectionBrowser = this.providerConnection.createConnectionBrowser(provDest, selector, pmrh, quantityHint);
/* 1366 */     connBrowser.setProviderConnectionBrowser(providerConnectionBrowser);
/*      */     
/* 1368 */     synchronized (this.state) {
/*      */ 
/*      */       
/* 1371 */       if (this.state.equals(1)) {
/* 1372 */         connBrowser.start();
/*      */       }
/* 1374 */       this.browsers.add(connBrowser);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1379 */     JmsDestinationImplProxy.incrementUseCount((JmsDestinationImpl)destination);
/*      */     
/* 1381 */     if (Trace.isOn) {
/* 1382 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createConnectionBrowser(JmsDestination,String,JmsMessageReferenceHandler,int)", connBrowser);
/*      */     }
/*      */ 
/*      */     
/* 1386 */     return connBrowser;
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
/*      */   public JmsConnectionBrowser createDurableConnectionBrowser(JmsTopic topic, String subName, String selector, JmsMessageReferenceHandler messageRefHandler, int quantityHint, boolean noLocal) throws JMSException {
/* 1410 */     if (Trace.isOn) {
/* 1411 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", new Object[] { topic, subName, selector, messageRefHandler, 
/*      */             
/* 1413 */             Integer.valueOf(quantityHint), 
/* 1414 */             Boolean.valueOf(noLocal) });
/*      */     }
/*      */ 
/*      */     
/* 1418 */     this.state.checkNotClosed("JMSCC0008");
/*      */ 
/*      */     
/* 1421 */     if (quantityHint != 0 && quantityHint != 1 && quantityHint != 2) {
/* 1422 */       IllegalArgumentException iae = (IllegalArgumentException)JmsErrorUtils.createException("JMSCC1094", null);
/* 1423 */       if (Trace.isOn) {
/* 1424 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", iae, 1);
/*      */       }
/*      */ 
/*      */       
/* 1428 */       throw iae;
/*      */     } 
/*      */     
/* 1431 */     if (topic == null) {
/* 1432 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0085", null);
/* 1433 */       if (Trace.isOn) {
/* 1434 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */       
/* 1438 */       throw je;
/*      */     } 
/*      */     
/* 1441 */     JmsDestinationImpl jmsTopic = (JmsDestinationImpl)topic;
/* 1442 */     ProviderDestination providerDestionation = JmsDestinationImplProxy.getProviderDestination(jmsTopic);
/* 1443 */     if (providerDestionation.isTemporary()) {
/* 1444 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1445 */       inserts.put("XMSC_INSERT_TYPE", "Topic");
/* 1446 */       inserts.put("XMSC_DESTINATION_NAME", jmsTopic.getTopicName());
/* 1447 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0093", inserts);
/* 1448 */       if (Trace.isOn) {
/* 1449 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */       
/* 1453 */       throw je;
/*      */     } 
/*      */     
/* 1456 */     if (null == messageRefHandler) {
/* 1457 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1093", null);
/* 1458 */       if (Trace.isOn) {
/* 1459 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */       
/* 1463 */       throw je;
/*      */     } 
/*      */     
/* 1466 */     if (null == subName || subName.length() == 0) {
/* 1467 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC3039", null);
/* 1468 */       if (Trace.isOn) {
/* 1469 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", (Throwable)je, 5);
/*      */       }
/*      */ 
/*      */       
/* 1473 */       throw je;
/*      */     } 
/*      */     
/* 1476 */     fixClientID();
/*      */ 
/*      */     
/* 1479 */     String clientID = getClientID();
/*      */     
/* 1481 */     if (clientID == null || "".equals(clientID.trim())) {
/* 1482 */       JMSException je5 = (JMSException)JmsErrorUtils.createException("JMSCC0101", null);
/* 1483 */       if (Trace.isOn) {
/* 1484 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", (Throwable)je5, 6);
/*      */       }
/*      */ 
/*      */       
/* 1488 */       throw je5;
/*      */     } 
/*      */ 
/*      */     
/* 1492 */     JmsConnectionBrowserImpl connBrowser = new JmsConnectionBrowserImpl(this, topic, subName, selector, messageRefHandler, quantityHint, noLocal, false, true);
/*      */ 
/*      */ 
/*      */     
/* 1496 */     ProviderDestination provDest = JmsDestinationImplProxy.getProviderDestination((JmsDestinationImpl)topic);
/* 1497 */     ProviderMessageReferenceHandler pmrh = new JmsProviderMessageRefHandler(messageRefHandler);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1502 */     String clientid = getStringProperty("XMSC_CLIENT_ID");
/*      */     
/* 1504 */     ProviderConnectionBrowser providerConnectionBrowser = this.providerConnection.createDurableConnectionBrowser(provDest, clientid, subName, selector, pmrh, quantityHint, noLocal);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1512 */     connBrowser.setProviderConnectionBrowser(providerConnectionBrowser);
/*      */     
/* 1514 */     synchronized (this.state) {
/*      */ 
/*      */       
/* 1517 */       if (this.state.equals(1)) {
/* 1518 */         connBrowser.start();
/*      */       }
/* 1520 */       this.browsers.add(connBrowser);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1525 */     JmsDestinationImplProxy.incrementUseCount((JmsDestinationImpl)topic);
/*      */     
/* 1527 */     if (Trace.isOn) {
/* 1528 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", connBrowser);
/*      */     }
/*      */ 
/*      */     
/* 1532 */     return connBrowser;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ProviderConnection getProviderConnection() {
/* 1540 */     if (Trace.isOn) {
/* 1541 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "getProviderConnection()", "getter", this.providerConnection);
/*      */     }
/*      */     
/* 1544 */     return this.providerConnection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProviderConnection(ProviderConnection providerConnection) throws JMSException {
/* 1554 */     if (Trace.isOn) {
/* 1555 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "setProviderConnection(ProviderConnection)", "setter", providerConnection);
/*      */     }
/*      */     
/* 1558 */     this.providerConnection = providerConnection;
/*      */ 
/*      */     
/* 1561 */     JmsConnectionMetaDataImpl md = (JmsConnectionMetaDataImpl)getMetaData();
/*      */     
/* 1563 */     this.isCICSUnmanaged = md.doesConnectionSupport("XMSC_CAPABILITY_NATIVE_CICS_UNMANAGED");
/* 1564 */     this.isIMS = md.doesConnectionSupport("XMSC_CAPABILITY_NATIVE_IMS");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ProviderFactoryFactory getProviderFactory() {
/* 1571 */     if (Trace.isOn) {
/* 1572 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "getProviderFactory()", "getter", this.providerFactory);
/*      */     }
/*      */     
/* 1575 */     return this.providerFactory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ProviderMessageFactory getMessageFactory() {
/* 1583 */     if (Trace.isOn) {
/* 1584 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "getMessageFactory()", "getter", this.providerMessageFactory);
/*      */     }
/*      */     
/* 1587 */     return this.providerMessageFactory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ProviderJmsFactory getJmsFactory() {
/* 1595 */     if (Trace.isOn) {
/* 1596 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "getJmsFactory()", "getter", this.providerJmsFactory);
/*      */     }
/*      */     
/* 1599 */     return this.providerJmsFactory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProviderFactory(ProviderFactoryFactory providerFactory) {
/* 1607 */     if (Trace.isOn) {
/* 1608 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "setProviderFactory(ProviderFactoryFactory)", "setter", providerFactory);
/*      */     }
/*      */     
/* 1611 */     this.providerFactory = providerFactory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reportException(JMSException e, boolean connectionBroken) {
/* 1620 */     if (Trace.isOn) {
/* 1621 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "reportException(JMSException,boolean)", new Object[] { e, 
/* 1622 */             Boolean.valueOf(connectionBroken) });
/*      */     }
/*      */ 
/*      */     
/* 1626 */     if (connectionBroken) {
/* 1627 */       dropClientId();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1633 */     this.exceptionListenerProxy.onException(e, connectionBroken);
/*      */     
/* 1635 */     if (Trace.isOn) {
/* 1636 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "reportException(JMSException,boolean)");
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
/*      */   protected JmsSessionImpl instantiateSession(boolean transacted, int acknowledgeMode) throws JMSException {
/* 1654 */     if (Trace.isOn)
/* 1655 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "instantiateSession(boolean,int)", new Object[] {
/* 1656 */             Boolean.valueOf(transacted), 
/* 1657 */             Integer.valueOf(acknowledgeMode)
/*      */           }); 
/* 1659 */     JmsSessionImpl jmsSession = null;
/*      */     
/* 1661 */     jmsSession = new JmsSessionImpl(transacted, acknowledgeMode, this);
/*      */     
/* 1663 */     if (Trace.isOn) {
/* 1664 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "instantiateSession(boolean,int)", jmsSession);
/*      */     }
/*      */     
/* 1667 */     return jmsSession;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void removeSession(JmsSessionImpl sess) {
/* 1677 */     if (Trace.isOn) {
/* 1678 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "removeSession(JmsSessionImpl)", new Object[] { sess });
/*      */     }
/*      */ 
/*      */     
/* 1682 */     boolean removeFromMainList = true;
/*      */     
/* 1684 */     this.pendingSessionsLock.lock();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1691 */       if (this.forceSessionsPending) {
/* 1692 */         removeFromMainList = false;
/* 1693 */         boolean pendingSessionRemoved = this.pendingSessions.remove(sess);
/* 1694 */         if (pendingSessionRemoved) {
/* 1695 */           if (Trace.isOn) {
/* 1696 */             Trace.data(this, "removeSession(JmsSessionImpl)", "removed a pending session");
/*      */ 
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 1705 */           this.pendingSessionRemovals.add(sess);
/* 1706 */           if (Trace.isOn) {
/* 1707 */             Trace.data(this, "removeSession(JmsSessionImpl)", "queued a pending session delete");
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } finally {
/*      */       
/* 1713 */       this.pendingSessionsLock.unlock();
/*      */     } 
/*      */     
/* 1716 */     if (removeFromMainList) {
/* 1717 */       boolean removed = false;
/* 1718 */       synchronized (this.state) {
/*      */         
/* 1720 */         removed = this.sessions.remove(sess);
/*      */       } 
/* 1722 */       if (Trace.isOn) {
/* 1723 */         if (removed) {
/* 1724 */           Trace.data(this, "removeSession(JmsSessionImpl)", "removed a session from the main list");
/*      */         } else {
/*      */           
/* 1727 */           Trace.data(this, "removeSession(JmsSessionImpl)", "failed to remove a session from the main list");
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1733 */     if (Trace.isOn) {
/* 1734 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "removeSession(JmsSessionImpl)");
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
/*      */   void removeConnectionBrowser(JmsConnectionBrowserImpl browser) {
/* 1748 */     if (Trace.isOn) {
/* 1749 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "removeConnectionBrowser(JmsConnectionBrowserImpl)", new Object[] { browser });
/*      */     }
/*      */     
/* 1752 */     synchronized (this.state) {
/*      */ 
/*      */       
/* 1755 */       this.browsers.remove(browser);
/*      */     } 
/* 1757 */     if (Trace.isOn) {
/* 1758 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "removeConnectionBrowser(JmsConnectionBrowserImpl)");
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
/*      */   protected State getState() {
/* 1771 */     synchronized (this.state) {
/*      */       
/* 1773 */       if (Trace.isOn) {
/* 1774 */         Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "getState()", "getter", this.state);
/*      */       }
/*      */       
/* 1777 */       return this.state;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void fixClientID() {
/* 1786 */     if (Trace.isOn) {
/* 1787 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "fixClientID()");
/*      */     }
/*      */     
/* 1790 */     this.clientIDFixed = true;
/*      */     
/* 1792 */     if (Trace.isOn) {
/* 1793 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "fixClientID()");
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
/*      */   void unFixClientID() {
/* 1805 */     if (Trace.isOn) {
/* 1806 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "unFixClientID()");
/*      */     }
/*      */     
/* 1809 */     this.clientIDFixed = false;
/*      */     
/* 1811 */     if (Trace.isOn) {
/* 1812 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "unFixClientID()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void proxyUnFixClientID(JmsConnectionImpl conn) {
/* 1819 */     conn.unFixClientID();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addTemporaryDestination(JmsDestination tempDest) {
/* 1829 */     if (Trace.isOn) {
/* 1830 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "addTemporaryDestination(JmsDestination)", new Object[] { tempDest });
/*      */     }
/*      */ 
/*      */     
/* 1834 */     fixClientID();
/* 1835 */     synchronized (this.temporaryDestinations) {
/*      */       
/* 1837 */       this.temporaryDestinations.add(tempDest);
/*      */     } 
/*      */     
/* 1840 */     if (Trace.isOn) {
/* 1841 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "addTemporaryDestination(JmsDestination)");
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
/*      */   protected void removeTemporaryDestination(JmsDestination tempDest) {
/* 1855 */     if (Trace.isOn) {
/* 1856 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "removeTemporaryDestination(JmsDestination)", new Object[] { tempDest });
/*      */     }
/*      */ 
/*      */     
/* 1860 */     synchronized (this.temporaryDestinations) {
/*      */       
/* 1862 */       this.temporaryDestinations.remove(tempDest);
/*      */     } 
/* 1864 */     if (Trace.isOn) {
/* 1865 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "removeTemporaryDestination(JmsDestination)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getConnectionTypeName() {
/* 1873 */     if (Trace.isOn) {
/* 1874 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "getConnectionTypeName()", "getter", this.connectionTypeName);
/*      */     }
/*      */     
/* 1877 */     return this.connectionTypeName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void validateProperty(String name, Object value) throws JMSException {
/* 1885 */     if (Trace.isOn) {
/* 1886 */       if ("XMSC_PASSWORD".equals(name)) {
/* 1887 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "validateProperty(String,Object)", new Object[] { name, "********" });
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1892 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "validateProperty(String,Object)", new Object[] { name, value });
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1897 */     this.state.checkNotClosed("JMSCC0008");
/*      */ 
/*      */     
/* 1900 */     if (name.equals("XMSC_CLIENT_ID")) {
/*      */ 
/*      */       
/* 1903 */       checkDuplicateClientID((String)value, true);
/*      */ 
/*      */       
/* 1906 */       if (this.clientIDFixed) {
/* 1907 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC3031", null);
/* 1908 */         if (Trace.isOn) {
/* 1909 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "setClientID(String)", (Throwable)je, 1);
/*      */         }
/* 1911 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/* 1915 */       fixClientID();
/*      */     } 
/*      */     
/* 1918 */     if (Trace.isOn) {
/* 1919 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "validateProperty(String,Object)");
/*      */     }
/*      */   }
/*      */   
/*      */   void logReceiveStoppedConnMsg() {
/*      */     boolean b;
/* 1925 */     if (Trace.isOn) {
/* 1926 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "logReceiveStoppedConnMsg()");
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
/* 1939 */     synchronized (this.receiveStoppedConnMsgLoggedLock) {
/* 1940 */       b = this.receiveStoppedConnMsgLogged;
/* 1941 */       this.receiveStoppedConnMsgLogged = true;
/*      */     } 
/* 1943 */     if (!b) {
/* 1944 */       JmsErrorUtils.log(this, "logReceiveStoppedConnMsg", "JMSCC0004", null);
/*      */     }
/* 1946 */     if (Trace.isOn) {
/* 1947 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "logReceiveStoppedConnMsg()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   class JmsProviderMessageRefHandler
/*      */     implements ProviderMessageReferenceHandler
/*      */   {
/*      */     private JmsMessageReferenceHandler refHandler;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     JmsProviderMessageRefHandler(JmsMessageReferenceHandler refHandler) {
/* 1963 */       if (Trace.isOn) {
/* 1964 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageRefHandler", "<init>(JmsMessageReferenceHandler)", new Object[] { refHandler });
/*      */       }
/*      */ 
/*      */       
/* 1968 */       this.refHandler = refHandler;
/* 1969 */       if (Trace.isOn) {
/* 1970 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageRefHandler", "<init>(JmsMessageReferenceHandler)");
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
/*      */     public void endDeliver() throws JMSException {
/* 1983 */       if (Trace.isOn) {
/* 1984 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageRefHandler", "endDeliver()");
/*      */       }
/*      */ 
/*      */       
/* 1988 */       this.refHandler.endDeliver();
/*      */       
/* 1990 */       if (Trace.isOn) {
/* 1991 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageRefHandler", "endDeliver()");
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
/*      */     public void handleMessageReference(ProviderMessageReference msgReference) throws JMSException {
/* 2005 */       if (Trace.isOn) {
/* 2006 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageRefHandler", "handleMessageReference(ProviderMessageReference)", new Object[] { msgReference });
/*      */       }
/*      */ 
/*      */       
/* 2010 */       JmsMessageReference jmsMessageRef = new JmsMessageReferenceImpl(JmsConnectionImpl.this.connectionTypeName, msgReference);
/* 2011 */       this.refHandler.handleMessageReference(jmsMessageRef);
/*      */       
/* 2013 */       if (Trace.isOn) {
/* 2014 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageRefHandler", "handleMessageReference(ProviderMessageReference)");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 2024 */     if (Trace.isOn) {
/* 2025 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "writeObject(ObjectOutputStream)", new Object[] { out });
/*      */     }
/*      */     
/* 2028 */     NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsConnection");
/* 2029 */     if (Trace.isOn) {
/* 2030 */       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "writeObject(ObjectOutputStream)", traceRet1);
/*      */     }
/*      */     
/* 2033 */     throw traceRet1;
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException {
/* 2037 */     if (Trace.isOn) {
/* 2038 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "readObject(ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */     
/* 2041 */     NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsConnection");
/* 2042 */     if (Trace.isOn) {
/* 2043 */       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "readObject(ObjectInputStream)", traceRet1);
/*      */     }
/*      */     
/* 2046 */     throw traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Session createSession() throws JMSException {
/*      */     Session traceRet1;
/* 2056 */     if (Trace.isOn) {
/* 2057 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSession()");
/*      */     }
/*      */     
/* 2060 */     checkValidAPIUsage("Session.createSession()");
/*      */ 
/*      */ 
/*      */     
/* 2064 */     if (this.isCICSUnmanaged || this.isIMS) {
/*      */ 
/*      */       
/* 2067 */       traceRet1 = createSession(true, 0);
/*      */     } else {
/*      */       
/* 2070 */       traceRet1 = createSession(false, 1);
/*      */     } 
/*      */     
/* 2073 */     if (Trace.isOn) {
/* 2074 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSession()", traceRet1);
/*      */     }
/*      */     
/* 2077 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Session createSession(int sessionMode) throws JMSException {
/*      */     Session traceRet1, traceRet2;
/* 2085 */     if (Trace.isOn) {
/* 2086 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSession(int)", new Object[] {
/* 2087 */             Integer.valueOf(sessionMode)
/*      */           });
/*      */     }
/* 2090 */     checkValidAPIUsage("Session.createSession(int)");
/*      */     
/* 2092 */     switch (sessionMode) {
/*      */       case 0:
/* 2094 */         traceRet1 = createSession(true, 0);
/* 2095 */         if (Trace.isOn) {
/* 2096 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSession(int)", traceRet1, 1);
/*      */         }
/*      */         
/* 2099 */         return traceRet1;
/*      */       
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/* 2104 */         traceRet2 = createSession(false, sessionMode);
/* 2105 */         if (Trace.isOn) {
/* 2106 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSession(int)", traceRet2, 2);
/*      */         }
/*      */         
/* 2109 */         return traceRet2;
/*      */     } 
/*      */ 
/*      */     
/* 2113 */     HashMap<String, Object> inserts = new HashMap<>();
/* 2114 */     inserts.put("XMSC_ACKNOWLEDGE_MODE", Integer.valueOf(sessionMode));
/* 2115 */     JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0097", inserts);
/* 2116 */     if (Trace.isOn) {
/* 2117 */       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSession(int)", (Throwable)je);
/*      */     }
/*      */     
/* 2120 */     throw je;
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
/*      */   public ConnectionConsumer createSharedConnectionConsumer(Topic topic, String subscriptionName, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
/* 2136 */     if (Trace.isOn) {
/* 2137 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedConnectionConsumer(Topic,String,String,ServerSessionPool,int)", new Object[] { topic, subscriptionName, messageSelector, sessionPool, 
/*      */             
/* 2139 */             Integer.valueOf(maxMessages) });
/*      */     }
/*      */     
/* 2142 */     checkValidAPIUsage("Session.createSharedConnectionConsumer(Topic,String,String,ServerSessionPool,int)");
/*      */ 
/*      */ 
/*      */     
/* 2146 */     if (this.isCICSUnmanaged || this.isIMS) {
/* 2147 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2148 */       inserts.put("XMSC_INSERT_METHOD", "createSharedConnectionConsumer(Topic,String,String,ServerSessionPool,int)");
/*      */       
/* 2150 */       String messageID = this.isCICSUnmanaged ? "JMSCC6001" : "JMSCC6011";
/* 2151 */       JMSException je = (JMSException)JmsErrorUtils.createException(messageID, inserts);
/*      */       
/* 2153 */       if (Trace.isOn) {
/* 2154 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je, 5);
/*      */       }
/*      */       
/* 2157 */       throw je;
/*      */     } 
/*      */     
/* 2160 */     this.state.checkNotClosed("JMSCC0008");
/*      */ 
/*      */     
/* 2163 */     if (topic == null || !(topic instanceof JmsTopic)) {
/* 2164 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0085", null);
/* 2165 */       if (Trace.isOn) {
/* 2166 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 2169 */       throw je;
/*      */     } 
/*      */     
/* 2172 */     if (null == sessionPool) {
/* 2173 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1084", null);
/* 2174 */       if (Trace.isOn) {
/* 2175 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je, 3);
/*      */       }
/*      */       
/* 2178 */       throw je;
/*      */     } 
/*      */     
/* 2181 */     if (maxMessages <= 0) {
/* 2182 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1083", null);
/* 2183 */       if (Trace.isOn) {
/* 2184 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 2187 */       throw je;
/*      */     } 
/*      */     
/* 2190 */     if (null == subscriptionName || subscriptionName.length() == 0) {
/* 2191 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC3039", null);
/* 2192 */       if (Trace.isOn) {
/* 2193 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je, 5);
/*      */       }
/*      */       
/* 2196 */       throw je;
/*      */     } 
/*      */     
/* 2199 */     fixClientID();
/* 2200 */     JmsConnectionConsumerImpl connectionConsumer = new JmsConnectionConsumerImpl(this, (JmsDestination)topic, subscriptionName, messageSelector, sessionPool, maxMessages, true, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2207 */     if (Trace.isOn) {
/* 2208 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedConnectionConsumer(Topic,String,String,ServerSessionPool,int)", connectionConsumer);
/*      */     }
/*      */ 
/*      */     
/* 2212 */     return (ConnectionConsumer)connectionConsumer;
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
/*      */   public ConnectionConsumer createSharedDurableConnectionConsumer(Topic topic, String subscriptionName, String messageSelector, ServerSessionPool sessionPool, int maxMessages) throws JMSException {
/* 2227 */     if (Trace.isOn) {
/* 2228 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", new Object[] { topic, subscriptionName, messageSelector, sessionPool, 
/*      */ 
/*      */             
/* 2231 */             Integer.valueOf(maxMessages) });
/*      */     }
/*      */     
/* 2234 */     checkValidAPIUsage("Session.createSharedConnectionConsumer(Topic,String,String,ServerSessionPool,int)");
/*      */ 
/*      */ 
/*      */     
/* 2238 */     if (this.isCICSUnmanaged || this.isIMS) {
/* 2239 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2240 */       inserts.put("XMSC_INSERT_METHOD", "createSharedDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)");
/*      */       
/* 2242 */       String messageID = this.isCICSUnmanaged ? "JMSCC6001" : "JMSCC6011";
/* 2243 */       JMSException je = (JMSException)JmsErrorUtils.createException(messageID, inserts);
/*      */       
/* 2245 */       if (Trace.isOn) {
/* 2246 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je, 7);
/*      */       }
/*      */       
/* 2249 */       throw je;
/*      */     } 
/*      */ 
/*      */     
/* 2253 */     this.state.checkNotClosed("JMSCC0008");
/*      */ 
/*      */     
/* 2256 */     if (topic == null || !(topic instanceof JmsTopic)) {
/* 2257 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0085", null);
/* 2258 */       if (Trace.isOn) {
/* 2259 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 2262 */       throw je;
/*      */     } 
/*      */     
/* 2265 */     JmsDestinationImpl jmsTopic = (JmsDestinationImpl)topic;
/* 2266 */     ProviderDestination providerDestination = JmsDestinationImplProxy.getProviderDestination(jmsTopic);
/*      */     
/* 2268 */     if (providerDestination.isTemporary()) {
/* 2269 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2270 */       inserts.put("XMSC_INSERT_TYPE", "Topic");
/* 2271 */       inserts.put("XMSC_DESTINATION_NAME", jmsTopic.getTopicName());
/* 2272 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0093", inserts);
/* 2273 */       if (Trace.isOn) {
/* 2274 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je, 3);
/*      */       }
/*      */       
/* 2277 */       throw je;
/*      */     } 
/*      */     
/* 2280 */     if (null == sessionPool) {
/* 2281 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1084", null);
/* 2282 */       if (Trace.isOn) {
/* 2283 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 2286 */       throw je;
/*      */     } 
/*      */     
/* 2289 */     if (maxMessages <= 0) {
/* 2290 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1083", null);
/* 2291 */       if (Trace.isOn) {
/* 2292 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je, 5);
/*      */       }
/*      */       
/* 2295 */       throw je;
/*      */     } 
/*      */     
/* 2298 */     if (null == subscriptionName || subscriptionName.length() == 0) {
/* 2299 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC3039", null);
/* 2300 */       if (Trace.isOn) {
/* 2301 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", (Throwable)je, 6);
/*      */       }
/*      */       
/* 2304 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2309 */     JmsConnectionConsumerImpl connectionConsumer = new JmsConnectionConsumerImpl(this, (JmsDestination)topic, subscriptionName, messageSelector, sessionPool, maxMessages, true, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2317 */     JmsDestinationImplProxy.incrementUseCount((JmsDestinationImpl)topic);
/*      */     
/* 2319 */     if (Trace.isOn) {
/* 2320 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedDurableConnectionConsumer(Topic,String,String,ServerSessionPool,int)", connectionConsumer);
/*      */     }
/*      */ 
/*      */     
/* 2324 */     return (ConnectionConsumer)connectionConsumer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addContext(JmsContextImpl context) {
/* 2332 */     if (Trace.isOn) {
/* 2333 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "addContext(JmsContextImpl)", new Object[] { context });
/*      */     }
/*      */     
/* 2336 */     this.containingContexts.add(context);
/* 2337 */     if (Trace.isOn) {
/* 2338 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "addContext(JmsContextImpl)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void removeContext(JmsContextImpl context) {
/* 2345 */     if (Trace.isOn) {
/* 2346 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "removeContext(JmsContextImpl)", new Object[] { context });
/*      */     }
/*      */     
/* 2349 */     this.containingContexts.remove(context);
/* 2350 */     if (Trace.isOn) {
/* 2351 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "removeContext(JmsContextImpl)");
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
/*      */   public JmsConnectionBrowser createSharedConnectionBrowser(JmsTopic topic, String subName, String selector, JmsMessageReferenceHandler messageRefHandler, int quantityHint, boolean noLocal) throws JMSException {
/* 2370 */     if (Trace.isOn) {
/* 2371 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", new Object[] { topic, subName, selector, messageRefHandler, 
/*      */             
/* 2373 */             Integer.valueOf(quantityHint), 
/* 2374 */             Boolean.valueOf(noLocal) });
/*      */     }
/*      */ 
/*      */     
/* 2378 */     this.state.checkNotClosed("JMSCC0008");
/*      */ 
/*      */     
/* 2381 */     if (quantityHint != 0 && quantityHint != 1 && quantityHint != 2) {
/* 2382 */       IllegalArgumentException iae = (IllegalArgumentException)JmsErrorUtils.createException("JMSCC1094", null);
/* 2383 */       if (Trace.isOn) {
/* 2384 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", iae, 1);
/*      */       }
/*      */ 
/*      */       
/* 2388 */       throw iae;
/*      */     } 
/*      */     
/* 2391 */     if (topic == null) {
/* 2392 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0085", null);
/* 2393 */       if (Trace.isOn) {
/* 2394 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */       
/* 2398 */       throw je;
/*      */     } 
/*      */     
/* 2401 */     JmsDestinationImpl jmsTopic = (JmsDestinationImpl)topic;
/* 2402 */     ProviderDestination providerDestionation = JmsDestinationImplProxy.getProviderDestination(jmsTopic);
/* 2403 */     if (providerDestionation.isTemporary()) {
/* 2404 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2405 */       inserts.put("XMSC_INSERT_TYPE", "Topic");
/* 2406 */       inserts.put("XMSC_DESTINATION_NAME", jmsTopic.getTopicName());
/* 2407 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0093", inserts);
/* 2408 */       if (Trace.isOn) {
/* 2409 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */       
/* 2413 */       throw je;
/*      */     } 
/*      */     
/* 2416 */     if (null == messageRefHandler) {
/* 2417 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1093", null);
/* 2418 */       if (Trace.isOn) {
/* 2419 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */       
/* 2423 */       throw je;
/*      */     } 
/*      */     
/* 2426 */     if (null == subName || subName.length() == 0) {
/* 2427 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC3039", null);
/* 2428 */       if (Trace.isOn) {
/* 2429 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", (Throwable)je, 5);
/*      */       }
/*      */ 
/*      */       
/* 2433 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2439 */     JmsConnectionBrowserImpl connBrowser = new JmsConnectionBrowserImpl(this, topic, subName, selector, messageRefHandler, quantityHint, noLocal, true, false);
/*      */ 
/*      */ 
/*      */     
/* 2443 */     ProviderDestination provDest = JmsDestinationImplProxy.getProviderDestination((JmsDestinationImpl)topic);
/* 2444 */     ProviderMessageReferenceHandler pmrh = new JmsProviderMessageRefHandler(messageRefHandler);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2449 */     String clientid = getStringProperty("XMSC_CLIENT_ID");
/*      */     
/* 2451 */     ProviderConnectionBrowser providerConnectionBrowser = this.providerConnection.createSharedConnectionBrowser(provDest, clientid, subName, selector, pmrh, quantityHint, noLocal);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2459 */     connBrowser.setProviderConnectionBrowser(providerConnectionBrowser);
/*      */     
/* 2461 */     synchronized (this.state) {
/*      */ 
/*      */       
/* 2464 */       if (this.state.equals(1)) {
/* 2465 */         connBrowser.start();
/*      */       }
/* 2467 */       this.browsers.add(connBrowser);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2472 */     JmsDestinationImplProxy.incrementUseCount((JmsDestinationImpl)topic);
/*      */     
/* 2474 */     if (Trace.isOn) {
/* 2475 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", connBrowser);
/*      */     }
/*      */ 
/*      */     
/* 2479 */     return connBrowser;
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
/*      */   public JmsConnectionBrowser createSharedDurableConnectionBrowser(JmsTopic topic, String subName, String selector, JmsMessageReferenceHandler messageRefHandler, int quantityHint, boolean noLocal) throws JMSException {
/* 2496 */     if (Trace.isOn) {
/* 2497 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", new Object[] { topic, subName, selector, messageRefHandler, 
/*      */             
/* 2499 */             Integer.valueOf(quantityHint), 
/* 2500 */             Boolean.valueOf(noLocal) });
/*      */     }
/*      */ 
/*      */     
/* 2504 */     this.state.checkNotClosed("JMSCC0008");
/*      */ 
/*      */     
/* 2507 */     if (quantityHint != 0 && quantityHint != 1 && quantityHint != 2) {
/* 2508 */       IllegalArgumentException iae = (IllegalArgumentException)JmsErrorUtils.createException("JMSCC1094", null);
/* 2509 */       if (Trace.isOn) {
/* 2510 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", iae, 1);
/*      */       }
/*      */ 
/*      */       
/* 2514 */       throw iae;
/*      */     } 
/*      */     
/* 2517 */     if (topic == null) {
/* 2518 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0085", null);
/* 2519 */       if (Trace.isOn) {
/* 2520 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", (Throwable)je, 2);
/*      */       }
/*      */ 
/*      */       
/* 2524 */       throw je;
/*      */     } 
/*      */     
/* 2527 */     JmsDestinationImpl jmsTopic = (JmsDestinationImpl)topic;
/* 2528 */     ProviderDestination providerDestionation = JmsDestinationImplProxy.getProviderDestination(jmsTopic);
/* 2529 */     if (providerDestionation.isTemporary()) {
/* 2530 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2531 */       inserts.put("XMSC_INSERT_TYPE", "Topic");
/* 2532 */       inserts.put("XMSC_DESTINATION_NAME", jmsTopic.getTopicName());
/* 2533 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0093", inserts);
/* 2534 */       if (Trace.isOn) {
/* 2535 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */       
/* 2539 */       throw je;
/*      */     } 
/*      */     
/* 2542 */     if (null == messageRefHandler) {
/* 2543 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1093", null);
/* 2544 */       if (Trace.isOn) {
/* 2545 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", (Throwable)je, 4);
/*      */       }
/*      */ 
/*      */       
/* 2549 */       throw je;
/*      */     } 
/*      */     
/* 2552 */     if (null == subName || subName.length() == 0) {
/* 2553 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC3039", null);
/* 2554 */       if (Trace.isOn) {
/* 2555 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", (Throwable)je, 5);
/*      */       }
/*      */ 
/*      */       
/* 2559 */       throw je;
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
/* 2577 */     JmsConnectionBrowserImpl connBrowser = new JmsConnectionBrowserImpl(this, topic, subName, selector, messageRefHandler, quantityHint, noLocal, true, true);
/*      */ 
/*      */ 
/*      */     
/* 2581 */     ProviderDestination provDest = JmsDestinationImplProxy.getProviderDestination((JmsDestinationImpl)topic);
/* 2582 */     ProviderMessageReferenceHandler pmrh = new JmsProviderMessageRefHandler(messageRefHandler);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2587 */     String clientid = getStringProperty("XMSC_CLIENT_ID");
/*      */     
/* 2589 */     ProviderConnectionBrowser providerConnectionBrowser = this.providerConnection.createSharedDurableConnectionBrowser(provDest, clientid, subName, selector, pmrh, quantityHint, noLocal);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2597 */     connBrowser.setProviderConnectionBrowser(providerConnectionBrowser);
/*      */     
/* 2599 */     synchronized (this.state) {
/*      */ 
/*      */       
/* 2602 */       if (this.state.equals(1)) {
/* 2603 */         connBrowser.start();
/*      */       }
/* 2605 */       this.browsers.add(connBrowser);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2610 */     JmsDestinationImplProxy.incrementUseCount((JmsDestinationImpl)topic);
/*      */     
/* 2612 */     if (Trace.isOn) {
/* 2613 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "createSharedDurableConnectionBrowser(JmsTopic,String,String,JmsMessageReferenceHandler,int,boolean)", connBrowser);
/*      */     }
/*      */ 
/*      */     
/* 2617 */     return connBrowser;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean getAutoStart() {
/* 2625 */     if (Trace.isOn) {
/* 2626 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "getAutoStart()", "getter", 
/* 2627 */           Boolean.valueOf(this.autoStart));
/*      */     }
/* 2629 */     return this.autoStart;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setAutoStart(boolean autoStart) {
/* 2636 */     if (Trace.isOn) {
/* 2637 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "setAutoStart(boolean)", "setter", 
/* 2638 */           Boolean.valueOf(autoStart));
/*      */     }
/*      */     
/* 2641 */     this.autoStart = autoStart;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void checkValidAPIUsage(String methodName) throws JMSException {
/* 2651 */     if (Trace.isOn) {
/* 2652 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "checkValidAPIUsage(String)", new Object[] { methodName });
/*      */     }
/*      */ 
/*      */     
/* 2656 */     if (!((JmsConnectionMetaDataImpl)getMetaData()).doesConnectionSupport("XMSC_CAPABILITY_JMS2_API")) {
/* 2657 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2658 */       inserts.put("XMSC_INSERT_METHOD", methodName);
/* 2659 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC5007", inserts);
/* 2660 */       if (Trace.isOn) {
/* 2661 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "checkValidAPIUsage(String)", (Throwable)je);
/*      */       }
/*      */       
/* 2664 */       throw je;
/*      */     } 
/* 2666 */     if (Trace.isOn) {
/* 2667 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "checkValidAPIUsage(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public enum JMS2Function
/*      */   {
/* 2676 */     ASYNCHRONOUS_SEND,
/*      */     
/* 2678 */     DELAYED_DELIVERY,
/*      */     
/* 2680 */     SHARED_SUBSCRIPTIONS;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 2687 */       if (Trace.isOn) {
/* 2688 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JMS2Function", "toString()");
/*      */       }
/* 2690 */       String str = super.toString();
/* 2691 */       switch (this) {
/*      */         case ASYNCHRONOUS_SEND:
/* 2693 */           str = "Asynchrounous Send";
/*      */           break;
/*      */         case DELAYED_DELIVERY:
/* 2696 */           str = "Delayed Delivery";
/*      */           break;
/*      */         case SHARED_SUBSCRIPTIONS:
/* 2699 */           str = "Shared Subscriptions";
/*      */           break;
/*      */       } 
/* 2702 */       if (Trace.isOn) {
/* 2703 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JMS2Function", "toString()", str);
/*      */       }
/* 2705 */       return str;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void checkValidFnUsage(JMS2Function fn) throws JMSException {
/* 2711 */     if (Trace.isOn) {
/* 2712 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "checkValidFnUsage(JMS2Function)", new Object[] { fn });
/*      */     }
/*      */ 
/*      */     
/* 2716 */     if (!((JmsConnectionMetaDataImpl)getMetaData()).doesConnectionSupport("XMSC_CAPABILITY_JMS2_FUNCTION")) {
/* 2717 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2718 */       inserts.put("XMSC_INSERT_METHOD", fn.toString());
/* 2719 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC5008", inserts);
/* 2720 */       if (Trace.isOn) {
/* 2721 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "checkValidFnUsage(JMS2Function)", (Throwable)je);
/*      */       }
/*      */       
/* 2724 */       throw je;
/*      */     } 
/* 2726 */     if (Trace.isOn) {
/* 2727 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "checkValidFnUsage(JMS2Function)");
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
/* 2739 */     if (Trace.isOn) {
/* 2740 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "dump(PrintWriter,int)", new Object[] { pw, 
/* 2741 */             Integer.valueOf(level) });
/*      */     }
/* 2743 */     String prefix = Trace.buildPrefix(level);
/* 2744 */     pw.format("%s%s@%x (%s)\n", new Object[] { prefix, getClass().getName(), Integer.valueOf(hashCode()), (this.providerConnection == null) ? "<null>" : 
/* 2745 */           String.format("%s@%x", new Object[] { this.providerConnection.getClass().getName(), Integer.valueOf(this.providerConnection.hashCode()) }) });
/* 2746 */     super.dump(pw, level + 1);
/* 2747 */     if (Trace.isOn) {
/* 2748 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "dump(PrintWriter,int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean inCICS() {
/* 2758 */     if (Trace.isOn) {
/* 2759 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "inCICS()");
/*      */     }
/* 2761 */     if (Trace.isOn) {
/* 2762 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "inCICS()", 
/* 2763 */           Boolean.valueOf(this.isCICSUnmanaged));
/*      */     }
/* 2765 */     return this.isCICSUnmanaged;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean inIMS() {
/* 2772 */     if (Trace.isOn) {
/* 2773 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "inIMS()");
/*      */     }
/* 2775 */     if (Trace.isOn) {
/* 2776 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionImpl", "inIMS()", 
/* 2777 */           Boolean.valueOf(this.isIMS));
/*      */     }
/* 2779 */     return this.isIMS;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSONObject toJson() {
/* 2787 */     return this.providerConnection.toJson();
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsConnectionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */