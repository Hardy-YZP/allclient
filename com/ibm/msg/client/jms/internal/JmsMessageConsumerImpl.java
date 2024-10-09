/*      */ package com.ibm.msg.client.jms.internal;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.JmsMessageConsumer;
/*      */ import com.ibm.msg.client.jms.admin.JmsDestinationImpl;
/*      */ import com.ibm.msg.client.provider.ProviderMessage;
/*      */ import com.ibm.msg.client.provider.ProviderMessageConsumer;
/*      */ import com.ibm.msg.client.provider.ProviderMessageListener;
/*      */ import java.io.IOException;
/*      */ import java.io.NotSerializableException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.concurrent.locks.ReentrantLock;
/*      */ import javax.jms.Destination;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.Message;
/*      */ import javax.jms.MessageConsumer;
/*      */ import javax.jms.MessageListener;
/*      */ import javax.jms.Session;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JmsMessageConsumerImpl
/*      */   extends JmsPropertyContextImpl
/*      */   implements JmsMessageConsumer
/*      */ {
/*      */   private static final long serialVersionUID = 5264547666759984190L;
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsMessageConsumerImpl.java";
/*      */   private Destination destination;
/*      */   private String selector;
/*      */   private boolean noLocal;
/*      */   private String name;
/*      */   private boolean durable;
/*      */   private boolean shared;
/*      */   private ProviderMessageConsumer providerConsumer;
/*      */   private MessageListener messageListener;
/*      */   protected JmsSessionImpl session;
/*      */   
/*      */   static {
/*   58 */     if (Trace.isOn) {
/*   59 */       Trace.data("com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsMessageConsumerImpl.java");
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
/*      */   protected boolean closed = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private volatile boolean inboundDisabled = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean recoverMessage = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  105 */   protected State state = new State(1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int sessionAckMode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final JmsSessionImpl.ReentrantDoubleLock sessionSyncLock;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean isCICSUnmanaged;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean isIMS;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   JmsMessageConsumerImpl(JmsSessionImpl session, Destination dest, String selector, boolean noLocal, String name, boolean shared, boolean durable) throws JMSException {
/*  137 */     super((Map<String, Object>)session, true);
/*  138 */     if (Trace.isOn) {
/*  139 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "<init>(JmsSessionImpl,Destination,String,boolean,String,boolean,boolean)", new Object[] { session, dest, selector, 
/*      */             
/*  141 */             Boolean.valueOf(noLocal), name, Boolean.valueOf(shared), 
/*  142 */             Boolean.valueOf(durable) });
/*      */     }
/*      */     
/*  145 */     this.sessionSyncLock = session.getSessionSyncLock();
/*      */ 
/*      */     
/*  148 */     this.sessionAckMode = session.getAcknowledgeMode();
/*      */     
/*  150 */     JmsConnectionMetaDataImpl md = (JmsConnectionMetaDataImpl)session.getConnection().getMetaData();
/*  151 */     this.isCICSUnmanaged = md.doesConnectionSupport("XMSC_CAPABILITY_NATIVE_CICS_UNMANAGED");
/*  152 */     this.isIMS = md.doesConnectionSupport("XMSC_CAPABILITY_NATIVE_IMS");
/*      */     
/*  154 */     initialize(session, dest, selector, noLocal, name, shared, durable);
/*      */     
/*  156 */     if (Trace.isOn) {
/*  157 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "<init>(JmsSessionImpl,Destination,String,boolean,String,boolean,boolean)");
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
/*      */   protected void initialize(JmsSessionImpl session, Destination dest, String selector, boolean noLocal, String name, boolean shared, boolean durable) throws JMSException {
/*  173 */     if (Trace.isOn) {
/*  174 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "initialize(JmsSessionImpl,Destination,String,boolean,String,boolean,boolean)", new Object[] { session, dest, selector, 
/*      */             
/*  176 */             Boolean.valueOf(noLocal), name, 
/*  177 */             Boolean.valueOf(shared), Boolean.valueOf(durable) });
/*      */     }
/*      */     
/*  180 */     this.destination = dest;
/*  181 */     this.selector = selector;
/*  182 */     this.noLocal = noLocal;
/*  183 */     this.session = session;
/*  184 */     this.name = name;
/*  185 */     this.shared = shared;
/*  186 */     this.durable = durable;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  191 */     setIntProperty("deliveryMode", 2);
/*  192 */     setBooleanProperty("XMSC_NOLOCAL", noLocal);
/*      */ 
/*      */     
/*      */     try {
/*  196 */       setLongProperty("XMSC_OBJECT_IDENTITY", System.identityHashCode(this));
/*      */     }
/*  198 */     catch (JMSException e) {
/*  199 */       if (Trace.isOn) {
/*  200 */         Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "initialize(JmsSessionImpl,Destination,String,boolean,String,boolean,boolean)", "Caught expected exception", e);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  208 */     if (session.getState() == 1) {
/*  209 */       start();
/*      */     } else {
/*      */       
/*  212 */       stop();
/*      */     } 
/*      */     
/*  215 */     if (Trace.isOn) {
/*  216 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "initialize(JmsSessionImpl,Destination,String,boolean,String,boolean,boolean)");
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
/*  228 */     if (Trace.isOn) {
/*  229 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "close()");
/*      */     }
/*  231 */     close(false);
/*  232 */     if (Trace.isOn) {
/*  233 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "close()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close(boolean closingFromSession) throws JMSException {
/*  244 */     if (Trace.isOn) {
/*  245 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "close(boolean)", new Object[] { Boolean.valueOf(closingFromSession) });
/*      */     }
/*      */     
/*  248 */     if (this.state.isClosed()) {
/*  249 */       if (Trace.isOn) {
/*  250 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "close(boolean)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  256 */     ReentrantLock onMessageLock = this.session.getOnMessageLock();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  262 */     if (!closingFromSession)
/*      */     {
/*      */ 
/*      */       
/*  266 */       stopUnconditional();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  272 */     if (!this.session.isAsync()) {
/*  273 */       this.sessionSyncLock.getExclusiveLock();
/*      */     }
/*      */     
/*  276 */     onMessageLock.lock();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  282 */       synchronized (this.state) {
/*      */         
/*  284 */         if (this.state.close()) {
/*  285 */           if (Trace.isOn) {
/*  286 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "close(boolean)", 2);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */ 
/*      */         
/*  295 */         JmsDestinationImplProxy.decrementUseCount((JmsDestinationImpl)this.destination);
/*      */ 
/*      */         
/*  298 */         if (this.providerConsumer != null) {
/*  299 */           this.providerConsumer.close(closingFromSession, onMessageLock);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  309 */       this.session.removeConsumer(this);
/*      */     }
/*      */     finally {
/*      */       
/*  313 */       if (Trace.isOn) {
/*  314 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "close(boolean)", 2);
/*      */       }
/*      */       
/*  317 */       if (this.sessionSyncLock.hasExclusiveLock()) {
/*  318 */         this.sessionSyncLock.unlockExclusiveLock();
/*      */       }
/*  320 */       onMessageLock.unlock();
/*      */     } 
/*      */     
/*  323 */     if (Trace.isOn) {
/*  324 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "close(boolean)", 3);
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
/*      */   public MessageListener getMessageListener() throws JMSException {
/*  338 */     if (inCICSUnmanaged() || this.isIMS) {
/*  339 */       HashMap<String, Object> inserts = new HashMap<>();
/*  340 */       inserts.put("XMSC_INSERT_METHOD", "getMessageListener()");
/*      */       
/*  342 */       String messageID = this.isCICSUnmanaged ? "JMSCC6001" : "JMSCC6011";
/*  343 */       JMSException je = (JMSException)JmsErrorUtils.createException(messageID, inserts);
/*      */       
/*  345 */       if (Trace.isOn) {
/*  346 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "getMessageListener()", (Throwable)je);
/*      */       }
/*      */       
/*  349 */       throw je;
/*      */     } 
/*      */     
/*  352 */     this.state.checkNotClosed("JMSCC0032");
/*  353 */     if (Trace.isOn) {
/*  354 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "getMessageListener()", "getter", this.messageListener);
/*      */     }
/*      */     
/*  357 */     return this.messageListener;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMessageSelector() throws JMSException {
/*  366 */     this.state.checkNotClosed("JMSCC0032");
/*  367 */     if (Trace.isOn) {
/*  368 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "getMessageSelector()", "getter", this.selector);
/*      */     }
/*      */     
/*  371 */     return this.selector;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Message receive() throws JMSException {
/*  380 */     if (Trace.isOn) {
/*  381 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receive()");
/*      */     }
/*  383 */     checkSynchronousUsage("receive");
/*      */ 
/*      */ 
/*      */     
/*  387 */     this.state.checkNotClosed("JMSCC0032");
/*      */     
/*  389 */     boolean msgLogged = false;
/*      */     
/*  391 */     boolean waiting = true;
/*  392 */     this.sessionSyncLock.getExclusiveLock();
/*      */     
/*      */     do {
/*  395 */       if (Trace.isOn) {
/*  396 */         Trace.data(this, "receive()", "Session state = " + State.toString(this.session.getState()), null);
/*      */       }
/*  398 */       switch (this.session.getState()) {
/*      */         
/*      */         case 1:
/*  401 */           waiting = false;
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/*  407 */           while (this.sessionSyncLock.hasExclusiveLock()) {
/*  408 */             this.sessionSyncLock.unlockExclusiveLock();
/*      */           }
/*      */           
/*  411 */           if (!msgLogged) {
/*      */ 
/*      */             
/*  414 */             msgLogged = true;
/*  415 */             JmsConnectionImpl c = (JmsConnectionImpl)this.session.getConnection();
/*  416 */             c.logReceiveStoppedConnMsg();
/*      */           } 
/*      */           
/*      */           try {
/*  420 */             synchronized (this.state) {
/*  421 */               if (Trace.isOn) {
/*  422 */                 Trace.data(this, "receive()", "waiting, 5000ms", null);
/*      */               }
/*  424 */               this.state.wait(5000L);
/*  425 */               if (Trace.isOn) {
/*  426 */                 Trace.data(this, "receive()", "Wait complete", null);
/*      */               }
/*      */             }
/*      */           
/*      */           }
/*  431 */           catch (InterruptedException ie) {
/*  432 */             if (Trace.isOn) {
/*  433 */               Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receive()", ie);
/*      */             }
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  440 */           if (1 == this.session.getState()) {
/*  441 */             this.sessionSyncLock.getExclusiveLock();
/*      */           }
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/*  448 */           while (this.sessionSyncLock.hasExclusiveLock()) {
/*  449 */             this.sessionSyncLock.unlockExclusiveLock();
/*      */           }
/*  451 */           if (Trace.isOn) {
/*  452 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receive()", null, 1);
/*      */           }
/*      */           
/*  455 */           return null;
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/*  460 */     } while (waiting);
/*      */     
/*  462 */     Message msg = null;
/*      */     
/*      */     try {
/*  465 */       msg = receiveInboundMessage(0L);
/*      */     }
/*      */     finally {
/*      */       
/*  469 */       if (Trace.isOn) {
/*  470 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receive()");
/*      */       }
/*      */       
/*  473 */       while (this.sessionSyncLock.hasExclusiveLock()) {
/*  474 */         this.sessionSyncLock.unlockExclusiveLock();
/*      */       }
/*      */     } 
/*  477 */     if (Trace.isOn) {
/*  478 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receive()", msg, 2);
/*      */     }
/*      */     
/*  481 */     return msg;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Message receive(long millisParam) throws JMSException {
/*  489 */     if (Trace.isOn) {
/*  490 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receive(long)", new Object[] {
/*  491 */             Long.valueOf(millisParam)
/*      */           });
/*      */     }
/*  494 */     long millis = millisParam;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  499 */     if (0L == millis) {
/*  500 */       Message message = receive();
/*  501 */       if (Trace.isOn) {
/*  502 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receive(long)", message, 1);
/*      */       }
/*      */       
/*  505 */       return message;
/*      */     } 
/*      */     
/*  508 */     checkSynchronousUsage("receive");
/*      */     
/*  510 */     Message msg = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  520 */       this.state.checkNotClosed("JMSCC0032");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  525 */       if (0L > millis) {
/*  526 */         HashMap<String, Object> inserts = new HashMap<>();
/*  527 */         inserts.put("XMSC_INSERT_TIMEOUT", Long.valueOf(millis));
/*  528 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0107", inserts);
/*  529 */         if (Trace.isOn) {
/*  530 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receive(long)", (Throwable)je);
/*      */         }
/*      */         
/*  533 */         throw je;
/*      */       } 
/*      */ 
/*      */       
/*  537 */       this.sessionSyncLock.getExclusiveLock();
/*      */       
/*  539 */       boolean waiting = true;
/*  540 */       long timeEntered = System.currentTimeMillis();
/*  541 */       long endTime = timeEntered + millis;
/*  542 */       long currTime = System.currentTimeMillis();
/*  543 */       boolean isStarted = false;
/*      */       
/*      */       do {
/*      */         JmsConnectionImpl c;
/*  547 */         if (Trace.isOn) {
/*  548 */           Trace.data(this, "receive(long)", "Session state = " + State.toString(this.session.getState()), null);
/*      */         }
/*      */ 
/*      */         
/*  552 */         switch (this.session.getState()) {
/*      */ 
/*      */           
/*      */           case 1:
/*  556 */             isStarted = true;
/*  557 */             waiting = false;
/*      */             break;
/*      */ 
/*      */           
/*      */           case 2:
/*  562 */             while (this.sessionSyncLock.hasExclusiveLock()) {
/*  563 */               this.sessionSyncLock.unlockExclusiveLock();
/*      */             }
/*      */             
/*  566 */             c = (JmsConnectionImpl)this.session.getConnection();
/*  567 */             c.logReceiveStoppedConnMsg();
/*      */ 
/*      */             
/*  570 */             synchronized (this.state) {
/*      */               try {
/*  572 */                 if (Trace.isOn) {
/*  573 */                   Trace.data(this, "receive(long)", "waiting, 5000ms", null);
/*      */                 }
/*      */                 
/*  576 */                 this.state.wait(endTime - currTime);
/*      */                 
/*  578 */                 if (Trace.isOn) {
/*  579 */                   Trace.data(this, "receive(long)", "Wait complete", null);
/*      */                 }
/*      */               }
/*  582 */               catch (InterruptedException ie) {
/*  583 */                 if (Trace.isOn) {
/*  584 */                   Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receive(long)", ie);
/*      */                 }
/*      */               } 
/*      */             } 
/*      */ 
/*      */             
/*  590 */             this.sessionSyncLock.getExclusiveLock();
/*  591 */             currTime = System.currentTimeMillis();
/*  592 */             if (currTime < endTime) {
/*      */               break;
/*      */             }
/*      */ 
/*      */ 
/*      */             
/*  598 */             waiting = false;
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/*  604 */             while (this.sessionSyncLock.hasExclusiveLock()) {
/*  605 */               this.sessionSyncLock.unlockExclusiveLock();
/*      */             }
/*  607 */             if (Trace.isOn) {
/*  608 */               Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receive(long)", null, 2);
/*      */             }
/*      */             
/*  611 */             return null;
/*      */         } 
/*      */ 
/*      */       
/*  615 */       } while (waiting);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  620 */       millis = Math.max(0L, endTime - currTime);
/*      */       
/*  622 */       if (millis == 0L)
/*      */       {
/*  624 */         if (isStarted) {
/*      */ 
/*      */           
/*  627 */           millis = -1L;
/*      */         }
/*      */         else {
/*      */           
/*  631 */           while (this.sessionSyncLock.hasExclusiveLock()) {
/*  632 */             this.sessionSyncLock.unlockExclusiveLock();
/*      */           }
/*      */           
/*  635 */           if (Trace.isOn) {
/*  636 */             Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receive(long)", "timeout has expired, so returning null");
/*      */ 
/*      */ 
/*      */             
/*  640 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receive(long)", null, 5);
/*      */           } 
/*  642 */           if (Trace.isOn) {
/*  643 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receive(long)", null, 3);
/*      */           }
/*      */           
/*  646 */           return null;
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  652 */       if (3 == this.session.getState()) {
/*      */         
/*  654 */         while (this.sessionSyncLock.hasExclusiveLock()) {
/*  655 */           this.sessionSyncLock.unlockExclusiveLock();
/*      */         }
/*      */         
/*  658 */         if (Trace.isOn) {
/*  659 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receive(long)", null, 4);
/*      */         }
/*      */         
/*  662 */         return null;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  667 */         msg = receiveInboundMessage(millis);
/*      */       }
/*      */       finally {
/*      */         
/*  671 */         if (Trace.isOn) {
/*  672 */           Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receive(long)", 1);
/*      */         }
/*      */         
/*  675 */         while (this.sessionSyncLock.hasExclusiveLock()) {
/*  676 */           this.sessionSyncLock.unlockExclusiveLock();
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*      */     finally {
/*      */       
/*  683 */       if (Trace.isOn) {
/*  684 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receive(long)", 2);
/*      */       }
/*      */       
/*  687 */       while (this.sessionSyncLock.hasExclusiveLock()) {
/*  688 */         this.sessionSyncLock.unlockExclusiveLock();
/*      */       }
/*      */     } 
/*      */     
/*  692 */     if (Trace.isOn) {
/*  693 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receive(long)", msg, 6);
/*      */     }
/*      */     
/*  696 */     return msg;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Message receiveNoWait() throws JMSException {
/*  704 */     if (Trace.isOn) {
/*  705 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receiveNoWait()");
/*      */     }
/*      */     
/*  708 */     checkSynchronousUsage("receiveNoWait");
/*      */     
/*  710 */     Message message = null;
/*      */     
/*  712 */     this.sessionSyncLock.getExclusiveLock();
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  717 */       synchronized (this.state) {
/*      */ 
/*      */         
/*  720 */         this.state.checkNotClosed("JMSCC0032");
/*      */         
/*  722 */         if (!this.inboundDisabled)
/*      */         {
/*  724 */           if (this.session.getState() == 1) {
/*  725 */             message = receiveInboundMessage(-1L);
/*      */           }
/*  727 */           else if (this.session.getState() == 2) {
/*  728 */             JmsConnectionImpl c = (JmsConnectionImpl)this.session.getConnection();
/*  729 */             c.logReceiveStoppedConnMsg();
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } finally {
/*      */       
/*  735 */       if (Trace.isOn) {
/*  736 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receiveNoWait()");
/*      */       }
/*      */       
/*  739 */       while (this.sessionSyncLock.hasExclusiveLock()) {
/*  740 */         this.sessionSyncLock.unlockExclusiveLock();
/*      */       }
/*      */     } 
/*      */     
/*  744 */     if (Trace.isOn) {
/*  745 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receiveNoWait()", message);
/*      */     }
/*      */     
/*  748 */     return message;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMessageListener(MessageListener listener) throws JMSException {
/*  756 */     if (Trace.isOn) {
/*  757 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "setMessageListener(MessageListener)", "setter", listener);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  763 */     if (this.isCICSUnmanaged || this.isIMS) {
/*  764 */       HashMap<String, Object> inserts = new HashMap<>();
/*  765 */       inserts.put("XMSC_INSERT_METHOD", "setMessageListener(MessageListener)");
/*      */       
/*  767 */       String messageID = this.isCICSUnmanaged ? "JMSCC6001" : "JMSCC6011";
/*  768 */       JMSException je = (JMSException)JmsErrorUtils.createException(messageID, inserts);
/*      */       
/*  770 */       if (Trace.isOn) {
/*  771 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "setMessageListener(MessageListener)", (Throwable)je, 1);
/*      */       }
/*      */       
/*  774 */       throw je;
/*      */     } 
/*      */     
/*  777 */     if (!this.session.isAsync()) {
/*  778 */       this.sessionSyncLock.getExclusiveLock();
/*      */     }
/*      */     
/*      */     try {
/*  782 */       this.state.checkNotClosed("JMSCC0032");
/*      */       
/*  784 */       this.messageListener = listener;
/*  785 */       if (listener == null)
/*      */       {
/*  787 */         this.providerConsumer.setMessageListener(null);
/*  788 */         this.session.registerSyncConsumer((MessageConsumer)this);
/*      */       }
/*      */       else
/*      */       {
/*  792 */         ProviderMessageListener providerListener = new JmsProviderMessageListener(listener);
/*  793 */         this.providerConsumer.setMessageListener(providerListener);
/*  794 */         this.session.registerAsyncConsumer((MessageConsumer)this);
/*      */       }
/*      */     
/*      */     }
/*      */     finally {
/*      */       
/*  800 */       if (Trace.isOn) {
/*  801 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "setMessageListener(MessageListener)");
/*      */       }
/*      */       
/*  804 */       if (this.sessionSyncLock.hasExclusiveLock()) {
/*  805 */         this.sessionSyncLock.unlockExclusiveLock();
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
/*      */   private void emergencyClose() {
/*  817 */     if (Trace.isOn) {
/*  818 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "emergencyClose()");
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  823 */       if (this.providerConsumer != null) {
/*  824 */         this.providerConsumer.close(false, null);
/*      */       
/*      */       }
/*      */     }
/*  828 */     catch (JMSException ex) {
/*  829 */       if (Trace.isOn) {
/*  830 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "emergencyClose()", (Throwable)ex);
/*      */       
/*      */       }
/*      */     }
/*      */     finally {
/*      */       
/*  836 */       if (Trace.isOn) {
/*  837 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "emergencyClose()");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  842 */       this.providerConsumer = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  848 */       this.closed = true;
/*      */     } 
/*      */     
/*  851 */     if (Trace.isOn) {
/*  852 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "emergencyClose()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ProviderMessageConsumer getProviderConsumer() {
/*  863 */     if (Trace.isOn) {
/*  864 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "getProviderConsumer()", "getter", this.providerConsumer);
/*      */     }
/*      */     
/*  867 */     return this.providerConsumer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProviderConsumer(ProviderMessageConsumer providerConsumer) {
/*  875 */     if (Trace.isOn) {
/*  876 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "setProviderConsumer(ProviderMessageConsumer)", "setter", providerConsumer);
/*      */     }
/*      */     
/*  879 */     this.providerConsumer = providerConsumer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void start() throws JMSException {
/*  887 */     if (Trace.isOn) {
/*  888 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "start()");
/*      */     }
/*      */ 
/*      */     
/*  892 */     if (this.providerConsumer != null)
/*      */     {
/*  894 */       synchronized (this.state) {
/*      */         
/*  896 */         if (!this.closed && !this.state.isClosed()) {
/*      */           
/*  898 */           this.state.notify();
/*      */ 
/*      */           
/*  901 */           this.providerConsumer.start(false);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  912 */     this.inboundDisabled = false;
/*      */     
/*  914 */     if (Trace.isOn) {
/*  915 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "start()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void stopUnconditional() throws JMSException {
/*  925 */     if (Trace.isOn) {
/*  926 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "stopUnconditional()");
/*      */     }
/*      */ 
/*      */     
/*  930 */     this.inboundDisabled = true;
/*      */     
/*  932 */     if (this.providerConsumer != null) {
/*  933 */       this.providerConsumer.stop();
/*      */     }
/*      */     
/*  936 */     if (Trace.isOn) {
/*  937 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "stopUnconditional()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void stop() throws JMSException {
/*  947 */     if (Trace.isOn) {
/*  948 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "stop()");
/*      */     }
/*      */     
/*  951 */     synchronized (this.state) {
/*  952 */       if (!this.closed && !this.state.isClosed()) {
/*  953 */         stopUnconditional();
/*      */       }
/*      */     } 
/*      */     
/*  957 */     if (Trace.isOn) {
/*  958 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "stop()");
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
/*      */   private void checkSynchronousUsage(String method) throws JMSException {
/*  972 */     if (Trace.isOn) {
/*  973 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "checkSynchronousUsage(String)", new Object[] { method });
/*      */     }
/*      */     
/*  976 */     this.session.checkSynchronousUsage(method);
/*      */ 
/*      */ 
/*      */     
/*  980 */     if (!this.isCICSUnmanaged && !this.isIMS && this.session.getMessageListener() != null) {
/*  981 */       HashMap<String, Object> inserts = new HashMap<>();
/*  982 */       inserts.put("XMSC_INSERT_METHOD", method);
/*  983 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0033", inserts);
/*  984 */       if (Trace.isOn) {
/*  985 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "checkSynchronousUsage(String)", (Throwable)je);
/*      */       }
/*      */       
/*  988 */       throw je;
/*      */     } 
/*  990 */     if (Trace.isOn) {
/*  991 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "checkSynchronousUsage(String)");
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
/*      */   private Message receiveInboundMessage(long timeout) throws JMSException {
/*      */     Message message;
/* 1004 */     if (Trace.isOn) {
/* 1005 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receiveInboundMessage(long)", new Object[] {
/* 1006 */             Long.valueOf(timeout)
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1015 */     this.state.checkNotClosed("JMSCC0032");
/*      */ 
/*      */ 
/*      */     
/* 1019 */     if (!this.session.asyncConsumers.isEmpty()) {
/* 1020 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1021 */       inserts.put("XMSC_INSERT_METHOD", "receiveInboundMessage(long)");
/* 1022 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0033", inserts);
/* 1023 */       if (Trace.isOn) {
/* 1024 */         Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receiveInboundMessage(long)", "Session was started in async mode, receive() call cannot continue");
/*      */ 
/*      */ 
/*      */         
/* 1028 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receiveInboundMessage(long)", (Throwable)je);
/*      */       } 
/*      */       
/* 1031 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1037 */     int lockCount = 0;
/* 1038 */     while (this.sessionSyncLock.hasExclusiveLock()) {
/* 1039 */       this.sessionSyncLock.unlockExclusiveLock();
/* 1040 */       lockCount++;
/*      */     } 
/*      */     
/* 1043 */     if (Trace.isOn) {
/* 1044 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receiveInboundMessage(long)", "Released " + lockCount + "holds on exclusive sessionSyncLock", null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1051 */     ProviderMessage providerMessage = this.providerConsumer.receive(timeout);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1058 */     if (providerMessage != null) {
/* 1059 */       message = JmsMessageImpl.inboundJmsInstance(providerMessage, this.session, this.session.getConnectionTypeName());
/*      */     } else {
/*      */       
/* 1062 */       message = null;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1067 */     if (message != null) {
/* 1068 */       boolean isXA = false;
/* 1069 */       this.session.notifyMessageConsumed(isXA);
/*      */     } 
/*      */     
/* 1072 */     if (Trace.isOn) {
/* 1073 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "receiveInboundMessage(long)", message);
/*      */     }
/*      */     
/* 1076 */     return message;
/*      */   }
/*      */   
/*      */   private class JmsProviderMessageListener
/*      */     implements ProviderMessageListener
/*      */   {
/* 1082 */     private MessageListener jmsListener = null;
/*      */     
/* 1084 */     private ReentrantLock onMessageLock = null;
/*      */     
/* 1086 */     private JmsConnectionImpl connection = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public JmsProviderMessageListener(MessageListener jmsListener) {
/* 1092 */       if (Trace.isOn) {
/* 1093 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "<init>(MessageListener)", new Object[] { jmsListener });
/*      */       }
/*      */ 
/*      */       
/* 1097 */       this.jmsListener = jmsListener;
/* 1098 */       this.onMessageLock = JmsMessageConsumerImpl.this.session.getOnMessageLock();
/* 1099 */       this.connection = (JmsConnectionImpl)JmsMessageConsumerImpl.this.session.getConnection();
/*      */       
/* 1101 */       if (Trace.isOn) {
/* 1102 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "<init>(MessageListener)");
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
/*      */     public void onMessage(ProviderMessage message) {
/* 1116 */       if (Trace.isOn) {
/* 1117 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "onMessage(ProviderMessage)", new Object[] { message });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1122 */       JmsTls tls = JmsTls.getInstance();
/* 1123 */       tls.inMessageListener(true, (Session)JmsMessageConsumerImpl.this.session);
/*      */ 
/*      */       
/*      */       try {
/* 1127 */         if (message != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1135 */           this.onMessageLock.lock();
/*      */ 
/*      */           
/*      */           try {
/* 1139 */             boolean transacted = (JmsMessageConsumerImpl.this.sessionAckMode == 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             try {
/* 1148 */               JmsMessageConsumerImpl.this.session.notifyMessagePreConsume(transacted);
/*      */ 
/*      */ 
/*      */               
/* 1152 */               int attempts = getDeliveryCount(message);
/*      */               
/* 1154 */               boolean retry = false;
/* 1155 */               boolean onMessageThrewException = false;
/*      */ 
/*      */ 
/*      */               
/*      */               do {
/* 1160 */                 if (attempts > 1) {
/* 1161 */                   message.setJMSRedelivered(true);
/*      */                 }
/*      */                 
/* 1164 */                 if (retry && JmsMessageConsumerImpl.this.state.equals(3)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 1173 */                   if (JmsMessageConsumerImpl.this.sessionAckMode == 3 || JmsMessageConsumerImpl.this.sessionAckMode == 1) {
/* 1174 */                     JmsMessageConsumerImpl.this.session.rollbackTransaction();
/*      */                   }
/* 1176 */                   if (Trace.isOn) {
/* 1177 */                     Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "onMessage(ProviderMessage)", 1);
/*      */                   }
/*      */                   
/*      */                   return;
/*      */                 } 
/*      */                 
/* 1183 */                 retry = false;
/* 1184 */                 onMessageThrewException = false;
/*      */ 
/*      */                 
/*      */                 try {
/* 1188 */                   Message jmsMessage = JmsMessageImpl.inboundJmsInstance(message, JmsMessageConsumerImpl.this.session, JmsMessageConsumerImpl.this.session.getConnectionTypeName());
/* 1189 */                   JmsMessageConsumerImpl.this.recoverMessage = false;
/* 1190 */                   int msgDeliveryMode = jmsMessage.getJMSDeliveryMode();
/*      */ 
/*      */                   
/* 1193 */                   this.jmsListener.onMessage(jmsMessage);
/*      */ 
/*      */                   
/* 1196 */                   if ((JmsMessageConsumerImpl.this.sessionAckMode == 1 || JmsMessageConsumerImpl.this.sessionAckMode == 3) && msgDeliveryMode != 2) {
/* 1197 */                     if (Trace.isOn) {
/* 1198 */                       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "In AUTO_ACKNOWLEDGE or DUPS_OK_ACKNOWLEDGE mode, checking for recover call");
/*      */                     }
/*      */ 
/*      */                     
/* 1202 */                     if (JmsMessageConsumerImpl.this.recoverMessage) {
/* 1203 */                       JmsMessageConsumerImpl.this.recoverMessage = false;
/*      */                       
/* 1205 */                       if (Trace.isOn) {
/* 1206 */                         Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "Redelivering message due to recover call");
/*      */                       }
/*      */ 
/*      */                       
/* 1210 */                       retry = checkRequeue(message, attempts);
/*      */                     }
/*      */                   
/*      */                   } 
/* 1214 */                 } catch (Throwable re) {
/* 1215 */                   if (Trace.isOn) {
/* 1216 */                     Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "onMessage(ProviderMessage)", re, 1);
/*      */                   }
/*      */ 
/*      */                   
/* 1220 */                   onMessageThrewException = true;
/* 1221 */                   retry = handleOnMessageThrowable(re, message, attempts);
/*      */                 } 
/* 1223 */                 attempts++;
/*      */               }
/* 1225 */               while (retry);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1233 */               if (!onMessageThrewException && (JmsMessageConsumerImpl.this.sessionAckMode == 3 || JmsMessageConsumerImpl.this.sessionAckMode == 1)) {
/* 1234 */                 JmsMessageConsumerImpl.this.session.notifyMessagePostConsume();
/*      */               }
/*      */             }
/* 1237 */             catch (Exception e) {
/* 1238 */               if (Trace.isOn) {
/* 1239 */                 Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "onMessage(ProviderMessage)", e, 2);
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1245 */               HashMap<String, Object> inserts = new HashMap<>();
/* 1246 */               inserts.put("XMSC_INSERT_EXCEPTION", e);
/* 1247 */               inserts.put("XMSC_INSERT_METHOD", "onMessage");
/* 1248 */               JMSException jmse = (JMSException)JmsErrorUtils.createException("JMSCC0007", inserts);
/* 1249 */               jmse.setLinkedException(e);
/*      */               
/* 1251 */               JmsMessageConsumerImpl.this.emergencyClose();
/*      */ 
/*      */               
/* 1254 */               if (this.connection != null) {
/* 1255 */                 this.connection.reportException(jmse, false);
/*      */               }
/*      */             }
/*      */           
/*      */           } finally {
/*      */             
/* 1261 */             if (Trace.isOn) {
/* 1262 */               Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "onMessage(ProviderMessage)", 1);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 1267 */             this.onMessageLock.unlock();
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1273 */         attemptConsumerClose();
/*      */       }
/*      */       finally {
/*      */         
/* 1277 */         if (Trace.isOn) {
/* 1278 */           Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "onMessage(ProviderMessage)", 2);
/*      */         }
/*      */ 
/*      */         
/* 1282 */         tls.inMessageListener(false, null);
/*      */       } 
/* 1284 */       if (Trace.isOn) {
/* 1285 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "onMessage(ProviderMessage)", 2);
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
/*      */     private int getDeliveryCount(ProviderMessage message) {
/* 1298 */       if (Trace.isOn) {
/* 1299 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "getDeliveryCount(ProviderMessage)", new Object[] { message });
/*      */       }
/*      */ 
/*      */       
/* 1303 */       int deliveryCount = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1310 */         if (message.propertyExists("JMSXDeliveryCount")) {
/* 1311 */           deliveryCount = message.getIntProperty("JMSXDeliveryCount");
/*      */         }
/*      */       }
/* 1314 */       catch (NumberFormatException nfe) {
/* 1315 */         if (Trace.isOn) {
/* 1316 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "getDeliveryCount(ProviderMessage)", nfe, 1);
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 1321 */       catch (JMSException je) {
/* 1322 */         if (Trace.isOn) {
/* 1323 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "getDeliveryCount(ProviderMessage)", (Throwable)je, 2);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1328 */       if (Trace.isOn) {
/* 1329 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "getDeliveryCount(ProviderMessage)", 
/* 1330 */             Integer.valueOf(deliveryCount));
/*      */       }
/* 1332 */       return deliveryCount;
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
/*      */     private boolean handleOnMessageThrowable(Throwable re, ProviderMessage message, int attempts) {
/* 1344 */       if (Trace.isOn) {
/* 1345 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "handleOnMessageThrowable(Throwable,ProviderMessage,int)", new Object[] { re, message, 
/*      */               
/* 1347 */               Integer.valueOf(attempts) });
/*      */       }
/*      */       
/* 1350 */       boolean retry = false;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1356 */         JMSException jmse = (JMSException)JmsErrorUtils.createException("JMSCC0037", null);
/* 1357 */         jmse.setLinkedException(new Exception(re));
/*      */ 
/*      */         
/* 1360 */         if (this.connection != null) {
/* 1361 */           this.connection.reportException(jmse, false);
/*      */         }
/*      */         
/* 1364 */         retry = checkRequeue(message, attempts);
/*      */       }
/* 1366 */       catch (Exception e1) {
/* 1367 */         if (Trace.isOn) {
/* 1368 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "handleOnMessageThrowable(Throwable,ProviderMessage,int)", e1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1373 */         JmsMessageConsumerImpl.this.emergencyClose();
/*      */       } 
/*      */       
/* 1376 */       if (Trace.isOn) {
/* 1377 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "handleOnMessageThrowable(Throwable,ProviderMessage,int)", 
/* 1378 */             Boolean.valueOf(retry));
/*      */       }
/* 1380 */       return retry;
/*      */     }
/*      */     private boolean checkRequeue(ProviderMessage message, int attempts) throws JMSException {
/*      */       boolean retry;
/* 1384 */       if (Trace.isOn) {
/* 1385 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "checkRequeue(ProviderMessage,int)", new Object[] { message, 
/* 1386 */               Integer.valueOf(attempts) });
/*      */       }
/*      */       
/* 1389 */       if (!JmsMessageConsumerImpl.this.providerConsumer.shouldMessageBeRequeued(attempts, message)) {
/* 1390 */         retry = true;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1395 */         retry = false;
/* 1396 */         JmsMessageConsumerImpl.this.providerConsumer.handlePoisonMessage(message);
/*      */       } 
/* 1398 */       if (Trace.isOn) {
/* 1399 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "checkRequeue(ProviderMessage,int)", 
/* 1400 */             Boolean.valueOf(retry));
/*      */       }
/* 1402 */       return retry;
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
/*      */     private void attemptConsumerClose() {
/* 1414 */       if (Trace.isOn) {
/* 1415 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "attemptConsumerClose()");
/*      */       }
/*      */       
/* 1418 */       int sessionState = JmsMessageConsumerImpl.this.session.getState();
/* 1419 */       switch (sessionState) {
/*      */         case 3:
/*      */           try {
/* 1422 */             JmsMessageConsumerImpl.this.stopUnconditional();
/*      */           }
/* 1424 */           catch (JMSException e) {
/* 1425 */             if (Trace.isOn) {
/* 1426 */               Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "attemptConsumerClose()", (Throwable)e, 1);
/*      */             }
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/*      */           try {
/* 1437 */             JmsMessageConsumerImpl.this.stop();
/*      */           }
/* 1439 */           catch (JMSException jmse) {
/* 1440 */             if (Trace.isOn) {
/* 1441 */               Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "attemptConsumerClose()", (Throwable)jmse, 2);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1451 */             if (this.connection != null) {
/* 1452 */               this.connection.reportException(jmse, false);
/*      */             }
/*      */           } 
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1462 */       if (Trace.isOn) {
/* 1463 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderMessageListener", "attemptConsumerClose()");
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
/*      */ 
/*      */   
/*      */   public boolean getNoLocal() throws JMSException {
/* 1479 */     this.state.checkNotClosed("JMSCC0032");
/*      */ 
/*      */     
/* 1482 */     if (this instanceof javax.jms.QueueReceiver) {
/* 1483 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1484 */       inserts.put("XMSC_INSERT_METHOD", "getNoLocal()");
/* 1485 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 1486 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1112", inserts);
/* 1487 */       if (Trace.isOn) {
/* 1488 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "getNoLocal()", (Throwable)je);
/*      */       }
/*      */       
/* 1491 */       throw je;
/*      */     } 
/* 1493 */     if (Trace.isOn) {
/* 1494 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "getNoLocal()", "getter", 
/* 1495 */           Boolean.valueOf(this.noLocal));
/*      */     }
/* 1497 */     return this.noLocal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getDurable() throws JMSException {
/* 1507 */     this.state.checkNotClosed("JMSCC0032");
/*      */ 
/*      */     
/* 1510 */     if (this instanceof javax.jms.QueueReceiver) {
/* 1511 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1512 */       inserts.put("XMSC_INSERT_METHOD", "getDurable()");
/* 1513 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 1514 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1112", inserts);
/* 1515 */       if (Trace.isOn) {
/* 1516 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "getDurable()", (Throwable)je);
/*      */       }
/*      */       
/* 1519 */       throw je;
/*      */     } 
/* 1521 */     if (Trace.isOn) {
/* 1522 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "getDurable()", "getter", 
/* 1523 */           Boolean.valueOf(this.durable));
/*      */     }
/* 1525 */     return this.durable;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getShared() throws JMSException {
/* 1535 */     this.state.checkNotClosed("JMSCC0032");
/*      */ 
/*      */     
/* 1538 */     if (this instanceof javax.jms.QueueReceiver) {
/* 1539 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1540 */       inserts.put("XMSC_INSERT_METHOD", "getShared()");
/* 1541 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 1542 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1112", inserts);
/* 1543 */       if (Trace.isOn) {
/* 1544 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "getShared()", (Throwable)je);
/*      */       }
/*      */       
/* 1547 */       throw je;
/*      */     } 
/* 1549 */     if (Trace.isOn) {
/* 1550 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "getShared()", "getter", 
/* 1551 */           Boolean.valueOf(this.shared));
/*      */     }
/* 1553 */     return this.shared;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void recoverAsyncMessage() {
/* 1560 */     if (Trace.isOn) {
/* 1561 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "recoverAsyncMessage()");
/*      */     }
/*      */     
/* 1564 */     this.recoverMessage = true;
/* 1565 */     if (Trace.isOn) {
/* 1566 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "recoverAsyncMessage()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 1574 */     if (Trace.isOn) {
/* 1575 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "writeObject(ObjectOutputStream)", new Object[] { out });
/*      */     }
/*      */     
/* 1578 */     NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsMessageConsumer");
/* 1579 */     if (Trace.isOn) {
/* 1580 */       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "writeObject(ObjectOutputStream)", traceRet1);
/*      */     }
/*      */     
/* 1583 */     throw traceRet1;
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException {
/* 1587 */     if (Trace.isOn) {
/* 1588 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "readObject(ObjectInputStream)", new Object[] { in });
/*      */     }
/*      */     
/* 1591 */     NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsMessageConsumer");
/* 1592 */     if (Trace.isOn) {
/* 1593 */       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "readObject(ObjectInputStream)", traceRet1);
/*      */     }
/*      */     
/* 1596 */     throw traceRet1;
/*      */   }
/*      */   
/*      */   private boolean inCICSUnmanaged() throws JMSException {
/* 1600 */     if (Trace.isOn) {
/* 1601 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "inCICSUnmanaged()");
/*      */     }
/*      */     
/* 1604 */     boolean traceRet1 = ((JmsConnectionMetaDataImpl)this.session.getConnection().getMetaData()).doesConnectionSupport("XMSC_CAPABILITY_NATIVE_CICS_UNMANAGED");
/* 1605 */     if (Trace.isOn) {
/* 1606 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageConsumerImpl", "inCICSUnmanaged()", 
/* 1607 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1609 */     return traceRet1;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsMessageConsumerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */