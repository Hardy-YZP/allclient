/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsConnection;
/*     */ import com.ibm.msg.client.jms.JmsConnectionBrowser;
/*     */ import com.ibm.msg.client.jms.JmsConnectionConsumer;
/*     */ import com.ibm.msg.client.jms.JmsDestination;
/*     */ import com.ibm.msg.client.jms.JmsMessageReference;
/*     */ import com.ibm.msg.client.jms.JmsMessageReferenceHandler;
/*     */ import com.ibm.msg.client.jms.JmsSession;
/*     */ import com.ibm.msg.client.jms.JmsTopic;
/*     */ import java.io.IOException;
/*     */ import java.io.NotSerializableException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.ServerSession;
/*     */ import javax.jms.ServerSessionPool;
/*     */ import javax.jms.Session;
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
/*     */ 
/*     */ 
/*     */ public class JmsConnectionConsumerImpl
/*     */   extends JmsPropertyContextImpl
/*     */   implements JmsConnectionConsumer
/*     */ {
/*     */   private static final long serialVersionUID = -4200467330524463500L;
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsConnectionConsumerImpl.java";
/*     */   private JmsConnection connection;
/*     */   private JmsDestination destination;
/*     */   private String messageSelector;
/*     */   private ServerSessionPool serverSessionPool;
/*     */   private int maxMessages;
/*     */   private String subscriptionName;
/*     */   private boolean shared;
/*     */   private boolean durable;
/*     */   
/*     */   static {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.data("com.ibm.msg.client.jms.internal.JmsConnectionConsumerImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsConnectionConsumerImpl.java");
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
/*  90 */   private State state = new State(1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JmsConnectionBrowser connBrowser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JmsConnectionConsumerImpl(JmsConnectionImpl connection, JmsDestination destination, String messageSelector, ServerSessionPool serverSessionPool, int maxMessages) throws JMSException {
/* 106 */     this(connection, destination, null, messageSelector, serverSessionPool, maxMessages, false, false);
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionConsumerImpl", "<init>(JmsConnectionImpl,JmsDestination,String,ServerSessionPool,int)", new Object[] { connection, destination, messageSelector, serverSessionPool, 
/*     */             
/* 110 */             Integer.valueOf(maxMessages) });
/*     */     }
/*     */     
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionConsumerImpl", "<init>(JmsConnectionImpl,JmsDestination,String,ServerSessionPool,int)");
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
/*     */   
/*     */   protected JmsConnectionConsumerImpl(JmsConnectionImpl connection, JmsDestination destination, String subscriptionName, String messageSelector, ServerSessionPool serverSessionPool, int maxMessages, boolean shared, boolean durable) throws JMSException {
/* 142 */     super((Map<String, Object>)connection, true);
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionConsumerImpl", "<init>(JmsConnectionImpl,JmsDestination,String,String,ServerSessionPool,int,boolean,boolean)", new Object[] { connection, destination, subscriptionName, messageSelector, serverSessionPool, 
/*     */             
/* 146 */             Integer.valueOf(maxMessages), Boolean.valueOf(shared), 
/* 147 */             Boolean.valueOf(durable) });
/*     */     }
/*     */     
/* 150 */     this.connection = connection;
/* 151 */     connection.getConnectionTypeName();
/* 152 */     this.destination = destination;
/* 153 */     this.messageSelector = messageSelector;
/* 154 */     this.serverSessionPool = serverSessionPool;
/* 155 */     this.maxMessages = maxMessages;
/* 156 */     this.subscriptionName = subscriptionName;
/* 157 */     this.shared = shared;
/* 158 */     this.durable = durable;
/* 159 */     createConnectionBrowser();
/*     */     
/*     */     try {
/* 162 */       setLongProperty("XMSC_OBJECT_IDENTITY", System.identityHashCode(this));
/*     */     }
/* 164 */     catch (JMSException e) {
/* 165 */       if (Trace.isOn) {
/* 166 */         Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionConsumerImpl", "<init>(JmsConnectionImpl,JmsDestination,String,String,ServerSessionPool,int,boolean,boolean)", "Caught expected exception", e);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionConsumerImpl", "<init>(JmsConnectionImpl,JmsDestination,String,String,ServerSessionPool,int,boolean,boolean)");
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
/*     */   public ServerSessionPool getServerSessionPool() throws JMSException {
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConnectionConsumerImpl", "getServerSessionPool()", "getter", this.serverSessionPool);
/*     */     }
/*     */     
/* 192 */     return this.serverSessionPool;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws JMSException {
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionConsumerImpl", "close()");
/*     */     }
/*     */     
/* 206 */     if (this.state.close()) {
/* 207 */       if (Trace.isOn) {
/* 208 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionConsumerImpl", "close()", 1);
/*     */       }
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 215 */     this.connBrowser.close();
/*     */     
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionConsumerImpl", "close()", 2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void createConnectionBrowser() throws JMSException {
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionConsumerImpl", "createConnectionBrowser()");
/*     */     }
/*     */ 
/*     */     
/* 230 */     JmsMessageReferenceHandler refHandler = new JmsMessageReferenceHandlerImpl();
/*     */     
/* 232 */     if (this.destination instanceof com.ibm.msg.client.jms.JmsQueue) {
/*     */       
/* 234 */       this.connBrowser = this.connection.createConnectionBrowser(this.destination, this.messageSelector, refHandler, 0);
/*     */     }
/*     */     else {
/*     */       
/* 238 */       boolean nolocal = false;
/* 239 */       if (this.shared) {
/* 240 */         if (this.durable)
/*     */         {
/* 242 */           this
/* 243 */             .connBrowser = this.connection.createSharedDurableConnectionBrowser((JmsTopic)this.destination, this.subscriptionName, this.messageSelector, refHandler, 0, nolocal);
/*     */         }
/*     */         else
/*     */         {
/* 247 */           this.connBrowser = this.connection.createSharedConnectionBrowser((JmsTopic)this.destination, this.subscriptionName, this.messageSelector, refHandler, 0, nolocal);
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 252 */       else if (this.durable) {
/*     */         
/* 254 */         this.connBrowser = this.connection.createDurableConnectionBrowser((JmsTopic)this.destination, this.subscriptionName, this.messageSelector, refHandler, 0, nolocal);
/*     */       }
/*     */       else {
/*     */         
/* 258 */         this.connBrowser = this.connection.createConnectionBrowser(this.destination, this.messageSelector, refHandler, 0);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConnectionConsumerImpl", "createConnectionBrowser()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private class JmsMessageReferenceHandlerImpl
/*     */     implements JmsMessageReferenceHandler
/*     */   {
/*     */     private ArrayList<JmsMessageReference> messageReferences;
/*     */ 
/*     */     
/*     */     JmsMessageReferenceHandlerImpl() throws JMSException {
/* 276 */       if (Trace.isOn) {
/* 277 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceHandlerImpl", "<init>()");
/*     */       }
/*     */ 
/*     */       
/* 281 */       if (JmsConnectionConsumerImpl.this.maxMessages <= 0) {
/* 282 */         HashMap<String, Object> inserts = new HashMap<>();
/* 283 */         inserts.put("XMSC_INSERT_BATCH_SIZE", Integer.valueOf(JmsConnectionConsumerImpl.this.maxMessages));
/* 284 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0086", inserts);
/* 285 */         if (Trace.isOn) {
/* 286 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceHandlerImpl", "<init>()", (Throwable)je, 1);
/*     */         }
/*     */         
/* 289 */         throw je;
/*     */       } 
/*     */       
/* 292 */       if (JmsConnectionConsumerImpl.this.serverSessionPool == null) {
/* 293 */         JMSException je2 = (JMSException)JmsErrorUtils.createException("JMSCC0087", null);
/* 294 */         if (Trace.isOn) {
/* 295 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceHandlerImpl", "<init>()", (Throwable)je2, 2);
/*     */         }
/*     */         
/* 298 */         throw je2;
/*     */       } 
/*     */       
/* 301 */       this.messageReferences = new ArrayList<>(JmsConnectionConsumerImpl.this.maxMessages);
/*     */ 
/*     */       
/* 304 */       if (Trace.isOn) {
/* 305 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceHandlerImpl", "<init>()");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void endDeliver() throws JMSException {
/* 316 */       if (Trace.isOn) {
/* 317 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceHandlerImpl", "endDeliver()");
/*     */       }
/*     */ 
/*     */       
/* 321 */       ArrayList<JmsMessageReference> currentReferences = null;
/*     */       
/* 323 */       synchronized (this) {
/*     */         
/* 325 */         if (0 == this.messageReferences.size()) {
/*     */ 
/*     */           
/* 328 */           if (Trace.isOn) {
/* 329 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceHandlerImpl", "endDeliver()", 1);
/*     */           }
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 335 */         currentReferences = this.messageReferences;
/*     */         
/* 337 */         this.messageReferences = new ArrayList<>(JmsConnectionConsumerImpl.this.maxMessages);
/*     */       } 
/*     */       
/* 340 */       endDeliverInternal(currentReferences);
/*     */       
/* 342 */       if (Trace.isOn) {
/* 343 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceHandlerImpl", "endDeliver()", 2);
/*     */       }
/*     */     }
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
/*     */     private void endDeliverInternal(ArrayList<JmsMessageReference> currentReferences) throws JMSException {
/* 357 */       if (Trace.isOn) {
/* 358 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceHandlerImpl", "endDeliverInternal(ArrayList<JmsMessageReference>)", new Object[] { currentReferences });
/*     */       }
/*     */       
/* 361 */       ServerSession serverSession = null;
/*     */       try {
/* 363 */         serverSession = JmsConnectionConsumerImpl.this.serverSessionPool.getServerSession();
/*     */       }
/* 365 */       catch (Exception e) {
/* 366 */         if (Trace.isOn) {
/* 367 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceHandlerImpl", "endDeliverInternal(ArrayList<JmsMessageReference>)", e, 1);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 373 */       if (serverSession == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 378 */         synchronized (this) {
/* 379 */           this.messageReferences.clear();
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 385 */         if (Trace.isOn) {
/* 386 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceHandlerImpl", "endDeliverInternal(ArrayList<JmsMessageReference>)", 1);
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 392 */       Session jmsSession = null;
/* 393 */       Exception thrown = null;
/*     */       try {
/* 395 */         jmsSession = serverSession.getSession();
/*     */       }
/* 397 */       catch (Exception e) {
/* 398 */         if (Trace.isOn) {
/* 399 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceHandlerImpl", "endDeliverInternal(ArrayList<JmsMessageReference>)", e, 2);
/*     */         }
/*     */ 
/*     */         
/* 403 */         thrown = e;
/*     */       } 
/*     */       
/* 406 */       if (jmsSession == null || !(jmsSession instanceof JmsSession)) {
/* 407 */         HashMap<String, Object> inserts = new HashMap<>();
/* 408 */         inserts.put("XMSC_INSERT_SESSION", jmsSession);
/*     */         
/* 410 */         JMSException je2 = (JMSException)JmsErrorUtils.createException("JMSCC0089", inserts);
/* 411 */         if (thrown != null) {
/* 412 */           je2.setLinkedException(thrown);
/*     */         }
/* 414 */         if (Trace.isOn) {
/* 415 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceHandlerImpl", "endDeliverInternal(ArrayList<JmsMessageReference>)", (Throwable)je2);
/*     */         }
/*     */         
/* 418 */         throw je2;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 426 */         ((JmsSession)jmsSession).deliver(currentReferences);
/*     */       }
/* 428 */       catch (JMSException jmsEx1) {
/* 429 */         if (Trace.isOn) {
/* 430 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceHandlerImpl", "endDeliverInternal(ArrayList<JmsMessageReference>)", (Throwable)jmsEx1, 3);
/*     */         }
/*     */         
/* 433 */         HashMap<String, Object> ffstData = new HashMap<>();
/* 434 */         ffstData.put("JMS Session", jmsSession);
/* 435 */         ffstData.put("Exception", jmsEx1);
/* 436 */         Trace.ffst(this, "endDeliverInternal()", "XJ009003", ffstData, null);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 441 */       serverSession.start();
/*     */       
/* 443 */       if (Trace.isOn) {
/* 444 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceHandlerImpl", "endDeliverInternal(ArrayList<JmsMessageReference>)", 2);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void handleMessageReference(JmsMessageReference messageReference) throws JMSException {
/* 455 */       if (Trace.isOn) {
/* 456 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceHandlerImpl", "handleMessageReference(JmsMessageReference)", new Object[] { messageReference });
/*     */       }
/*     */ 
/*     */       
/* 460 */       ArrayList<JmsMessageReference> messagesToDeliver = null;
/*     */       
/* 462 */       synchronized (this) {
/*     */ 
/*     */         
/* 465 */         if (this.messageReferences.size() < JmsConnectionConsumerImpl.this.maxMessages) {
/* 466 */           this.messageReferences.add(messageReference);
/*     */         }
/*     */         else {
/*     */           
/* 470 */           HashMap<String, Integer> ffstData = new HashMap<>();
/* 471 */           ffstData.put("maxMessages", Integer.valueOf(JmsConnectionConsumerImpl.this.maxMessages));
/* 472 */           ffstData.put("messageReferences.size()", Integer.valueOf(this.messageReferences.size()));
/* 473 */           Trace.ffst(this, "handleMessageReference()", "XJ009002", ffstData, null);
/*     */         } 
/*     */ 
/*     */         
/* 477 */         if (this.messageReferences.size() == JmsConnectionConsumerImpl.this.maxMessages) {
/* 478 */           messagesToDeliver = this.messageReferences;
/* 479 */           this.messageReferences = new ArrayList<>(JmsConnectionConsumerImpl.this.maxMessages);
/*     */         } 
/*     */       } 
/* 482 */       if (messagesToDeliver != null) {
/* 483 */         endDeliverInternal(messagesToDeliver);
/*     */       }
/*     */       
/* 486 */       if (Trace.isOn) {
/* 487 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsMessageReferenceHandlerImpl", "handleMessageReference(JmsMessageReference)");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 496 */     if (Trace.isOn) {
/* 497 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionConsumerImpl", "writeObject(ObjectOutputStream)", new Object[] { out });
/*     */     }
/*     */     
/* 500 */     NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsConnectionConsumer");
/* 501 */     if (Trace.isOn) {
/* 502 */       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionConsumerImpl", "writeObject(ObjectOutputStream)", traceRet1);
/*     */     }
/*     */     
/* 505 */     throw traceRet1;
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException {
/* 509 */     if (Trace.isOn) {
/* 510 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConnectionConsumerImpl", "readObject(ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */     
/* 513 */     NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsConnectionConsumer");
/* 514 */     if (Trace.isOn) {
/* 515 */       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionConsumerImpl", "readObject(ObjectInputStream)", traceRet1);
/*     */     }
/*     */     
/* 518 */     throw traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsConnectionConsumerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */