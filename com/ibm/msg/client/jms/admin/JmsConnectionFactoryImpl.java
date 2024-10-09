/*     */ package com.ibm.msg.client.jms.admin;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.internal.JmsConnectionImpl;
/*     */ import com.ibm.msg.client.jms.internal.JmsContextImpl;
/*     */ import com.ibm.msg.client.jms.internal.JmsErrorUtils;
/*     */ import com.ibm.msg.client.jms.internal.JmsXAConnectionImpl;
/*     */ import com.ibm.msg.client.jms.internal.JmsXAContextImpl;
/*     */ import com.ibm.msg.client.provider.ProviderConnectionFactory;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.jms.Connection;
/*     */ import javax.jms.JMSContext;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.JMSRuntimeException;
/*     */ import javax.jms.Session;
/*     */ import javax.jms.XAConnection;
/*     */ 
/*     */ public class JmsConnectionFactoryImpl extends JmsPropertyContextImpl implements JmsConnectionFactory {
/*     */   private static final long serialVersionUID = 1768302810905848221L;
/*     */   protected transient ProviderFactoryFactory providerFactory;
/*     */   protected ProviderConnectionFactory providerConnectionFactory;
/*     */   private String connectionTypeName;
/*     */   
/*     */   public JmsConnectionFactoryImpl() {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "<init>()"); 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "<init>()"); 
/*     */   }
/*     */   
/*     */   protected JmsConnectionFactoryImpl(String connectionTypeName) throws JMSException {
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "<init>(String)", new Object[] { connectionTypeName }); 
/*     */     setStringProperty("XMSC_CONNECTION_TYPE_NAME", connectionTypeName);
/*     */     boolean found = false;
/*     */     for (int count = 0; count < JmsConstants.providerNames.length; count++) {
/*     */       if (JmsConstants.providerNames[count].equalsIgnoreCase(connectionTypeName)) {
/*     */         setIntProperty("XMSC_CONNECTION_TYPE", count);
/*     */         found = true;
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     if (!found) {
/*     */       setIntProperty("XMSC_CONNECTION_TYPE", -1);
/*     */       HashMap<String, String> data = new HashMap<>();
/*     */       data.put("XMSC_CONNECTION_TYPE_NAME", connectionTypeName);
/*     */       data.put("MSG", "Unknown connection type name");
/*     */       Trace.ffst("JmsConnectionFactoryImpl", "<init>String providerName)", "XJ004001", data, JMSException.class);
/*     */     } 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "<init>(String)"); 
/*     */   }
/*     */   
/*     */   protected synchronized void setProviderFactory(boolean xa) throws JMSException {
/*     */     if (Trace.isOn)
/*     */       Trace.data(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "setProviderFactory(boolean)", "setter", Boolean.valueOf(xa)); 
/*     */     if (this.providerFactory == null) {
/*     */       ProviderConnectionFactory providerCF;
/*     */       this.connectionTypeName = getStringProperty("XMSC_CONNECTION_TYPE_NAME");
/*     */       getIntProperty("XMSC_CONNECTION_TYPE");
/*     */       this.providerFactory = ((JmsFactoryFactoryImpl)JmsFactoryFactoryImpl.getInstance(this.connectionTypeName)).getProviderFactoryFactory();
/*     */       try {
/*     */         if (xa) {
/*     */           ProviderXAConnectionFactory providerXAConnectionFactory = this.providerFactory.createProviderXAConnectionFactory((JmsPropertyContext)this);
/*     */         } else {
/*     */           providerCF = this.providerFactory.createProviderConnectionFactory((JmsPropertyContext)this);
/*     */         } 
/*     */       } catch (JMSException je) {
/*     */         if (Trace.isOn)
/*     */           Trace.catchBlock(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "setProviderFactory(boolean)", (Throwable)je); 
/*     */         this.providerFactory = null;
/*     */         if (Trace.isOn)
/*     */           Trace.throwing(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "setProviderFactory(boolean)", (Throwable)je); 
/*     */         throw je;
/*     */       } 
/*     */       setProviderConnectionFactory(providerCF);
/*     */     } 
/*     */   }
/*     */   
/*     */   static {
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.data("com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/admin/JmsConnectionFactoryImpl.java");
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
/* 254 */     PropertyStore.register("com.ibm.msg.client.jms.overrideConnectionFactory", false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Connection _createConnection(String userID, String password, boolean forceCrossDomain) throws JMSException {
/*     */     JmsConnectionImpl connection;
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "_createConnection(String,String,boolean)", new Object[] { userID, (password == null) ? password : 
/*     */ 
/*     */             
/* 270 */             Integer.valueOf(password.length()), Boolean.valueOf(forceCrossDomain) });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 277 */     boolean xa = (this instanceof javax.jms.XAConnectionFactory || this instanceof javax.jms.XAQueueConnectionFactory || this instanceof javax.jms.XATopicConnectionFactory);
/*     */     
/* 279 */     setProviderFactory(xa);
/*     */ 
/*     */ 
/*     */     
/* 283 */     boolean overrideFromPropertyStore = PropertyStore.getBooleanProperty("com.ibm.msg.client.jms.overrideConnectionFactory");
/*     */     
/* 285 */     if (overrideFromPropertyStore) {
/* 286 */       updateConnectionFactoryFromPropertyStore();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 295 */     if (forceCrossDomain) {
/* 296 */       connection = new JmsConnectionImpl(this);
/*     */     }
/* 298 */     else if (this instanceof javax.jms.QueueConnectionFactory) {
/* 299 */       JmsQueueConnectionImpl jmsQueueConnectionImpl = new JmsQueueConnectionImpl(this);
/*     */     }
/* 301 */     else if (this instanceof javax.jms.TopicConnectionFactory) {
/* 302 */       JmsTopicConnectionImpl jmsTopicConnectionImpl = new JmsTopicConnectionImpl(this);
/*     */     } else {
/*     */       
/* 305 */       connection = new JmsConnectionImpl(this);
/*     */     } 
/*     */ 
/*     */     
/* 309 */     if (xa) {
/* 310 */       short newFlags = (short)(connection.getShortProperty("XMSC_ADMIN_OBJECT_TYPE") & 0xFFFFFFBF);
/* 311 */       connection.setShortProperty("XMSC_ADMIN_OBJECT_TYPE", newFlags);
/*     */     } 
/*     */     
/* 314 */     if (userID != null) {
/* 315 */       connection.setStringProperty("XMSC_USERID", userID);
/*     */     }
/*     */     
/* 318 */     if (password != null) {
/* 319 */       connection.setStringProperty("XMSC_PASSWORD", password);
/*     */     }
/*     */     
/* 322 */     ProviderConnection providerConnection = this.providerConnectionFactory.createProviderConnection((JmsPropertyContext)connection);
/* 323 */     connection.setProviderConnection(providerConnection);
/*     */     
/* 325 */     if (Trace.isOn) {
/* 326 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "_createConnection(String,String,boolean)", connection);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 331 */     return (Connection)connection; } private void setProviderConnectionFactory(ProviderConnectionFactory providerCF) { if (Trace.isOn)
/*     */       Trace.data(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "setProviderConnectionFactory(ProviderConnectionFactory)", "setter", providerCF);  this.providerConnectionFactory = providerCF; }
/*     */   public Connection createConnection() throws JMSException { if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createConnection()");  Connection traceRet1 = createConnection((String)null, (String)null); if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createConnection()", traceRet1);  return traceRet1; }
/*     */   public Connection createConnection(String userID, String password) throws JMSException { if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createConnection(String,String)", new Object[] { userID, (password == null) ? password : Integer.valueOf(password.length()) }); 
/*     */     Connection connection = _createConnection(userID, password, false);
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createConnection(String,String)", connection); 
/*     */     return connection; }
/*     */   protected JmsConnection createXAConnectionInternal(String userID, String password) throws JMSException { JmsXAConnectionImpl jmsXAConnectionImpl;
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createXAConnectionInternal(String,String)", new Object[] { userID, (password == null) ? password : 
/*     */ 
/*     */             
/* 347 */             Integer.valueOf(password.length()) });
/*     */     }
/*     */     
/* 350 */     setProviderFactory(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 357 */     JmsConnectionImpl connection = null;
/* 358 */     if (this instanceof javax.jms.XAQueueConnectionFactory) {
/* 359 */       JmsXAQueueConnectionImpl jmsXAQueueConnectionImpl = new JmsXAQueueConnectionImpl(this);
/*     */     }
/* 361 */     else if (this instanceof javax.jms.XATopicConnectionFactory) {
/* 362 */       JmsXATopicConnectionImpl jmsXATopicConnectionImpl = new JmsXATopicConnectionImpl(this);
/*     */     }
/* 364 */     else if (this instanceof javax.jms.XAConnectionFactory) {
/* 365 */       jmsXAConnectionImpl = new JmsXAConnectionImpl(this);
/*     */     } else {
/*     */       
/* 368 */       HashMap<String, JmsConnectionFactoryImpl> data = new HashMap<>();
/* 369 */       data.put("connection", this);
/* 370 */       Trace.ffst(this, "createXAConnection(String,String)", "XJ004002", data, JMSException.class);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 376 */     if (userID != null) {
/* 377 */       jmsXAConnectionImpl.setStringProperty("XMSC_USERID", userID);
/*     */     }
/*     */     
/* 380 */     if (password != null) {
/* 381 */       jmsXAConnectionImpl.setStringProperty("XMSC_PASSWORD", password);
/*     */     }
/*     */ 
/*     */     
/* 385 */     ProviderXAConnection providerXAConnection = ((ProviderXAConnectionFactory)this.providerConnectionFactory).createProviderXAConnection((JmsPropertyContext)jmsXAConnectionImpl);
/* 386 */     jmsXAConnectionImpl.setProviderConnection((ProviderConnection)providerXAConnection);
/*     */     
/* 388 */     if (Trace.isOn) {
/* 389 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createXAConnectionInternal(String,String)", jmsXAConnectionImpl);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 394 */     return (JmsConnection)jmsXAConnectionImpl; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 405 */     if (Trace.isOn) {
/* 406 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "hashCode()");
/*     */     }
/* 408 */     int result = 42;
/* 409 */     Iterator<Map.Entry<String, Object>> i = getProperties().entrySet().iterator();
/* 410 */     while (i.hasNext()) {
/* 411 */       Map.Entry<String, Object> e = i.next();
/* 412 */       String value = e.getKey();
/*     */       
/* 414 */       if (value != null) {
/* 415 */         result ^= value.hashCode();
/*     */       }
/*     */     } 
/* 418 */     if (Trace.isOn) {
/* 419 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "hashCode()", 
/* 420 */           Integer.valueOf(result));
/*     */     }
/* 422 */     return result;
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
/*     */   public boolean equals(Object obj) {
/* 436 */     boolean result = true;
/*     */ 
/*     */     
/* 439 */     if (obj == null) {
/* 440 */       return false;
/*     */     }
/* 442 */     if (this == obj) {
/* 443 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 447 */     if (!getClass().getName().equals(obj.getClass().getName())) {
/* 448 */       result = false;
/*     */     } else {
/*     */ 
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 455 */         result = compareContexts((JmsReadablePropertyContextImpl)this, (JmsReadablePropertyContextImpl)obj);
/*     */       }
/* 457 */       catch (Exception e) {
/*     */ 
/*     */         
/* 460 */         result = false;
/*     */       } 
/*     */     } 
/*     */     
/* 464 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JMSContext createContext() {
/* 474 */     if (Trace.isOn) {
/* 475 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createContext()");
/*     */     }
/*     */ 
/*     */     
/* 479 */     JmsContextImpl context = null;
/*     */     try {
/* 481 */       Connection connection = _createConnection((String)null, (String)null, true);
/*     */       
/* 483 */       if (!((JmsConnectionMetaDataImpl)connection.getMetaData()).doesConnectionSupport("XMSC_CAPABILITY_JMS2_API")) {
/*     */         
/*     */         try {
/* 486 */           connection.close();
/*     */         }
/* 488 */         catch (Exception e) {
/* 489 */           if (Trace.isOn) {
/* 490 */             Trace.catchBlock(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createContext()", e, 1);
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 496 */         HashMap<String, Object> inserts = new HashMap<>();
/* 497 */         inserts.put("XMSC_INSERT_METHOD", "createContext()");
/* 498 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC5007", inserts);
/* 499 */         if (Trace.isOn) {
/* 500 */           Trace.throwing(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createContext()", (Throwable)je, 1);
/*     */         }
/*     */         
/* 503 */         throw je;
/*     */       } 
/*     */       
/* 506 */       Session session = connection.createSession();
/*     */       
/* 508 */       context = new JmsContextImpl(connection, session);
/*     */     }
/* 510 */     catch (JMSException je) {
/* 511 */       if (Trace.isOn) {
/* 512 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createContext()", (Throwable)je, 2);
/*     */       }
/*     */       
/* 515 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 516 */       if (Trace.isOn) {
/* 517 */         Trace.throwing(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createContext()", (Throwable)traceRet1, 2);
/*     */       }
/*     */       
/* 520 */       throw traceRet1;
/*     */     } 
/*     */     
/* 523 */     if (Trace.isOn) {
/* 524 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createContext()", context);
/*     */     }
/*     */     
/* 527 */     return (JMSContext)context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JMSContext createContext(int sessionMode) {
/* 536 */     if (Trace.isOn) {
/* 537 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createContext(int)", new Object[] {
/* 538 */             Integer.valueOf(sessionMode)
/*     */           });
/*     */     }
/* 541 */     JmsContextImpl context = null;
/*     */     try {
/* 543 */       Connection connection = _createConnection((String)null, (String)null, true);
/*     */       
/* 545 */       if (!((JmsConnectionMetaDataImpl)connection.getMetaData()).doesConnectionSupport("XMSC_CAPABILITY_JMS2_API")) {
/*     */         
/*     */         try {
/* 548 */           connection.close();
/*     */         }
/* 550 */         catch (Exception e) {
/* 551 */           if (Trace.isOn) {
/* 552 */             Trace.catchBlock(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createContext(int)", e, 1);
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 558 */         HashMap<String, Object> inserts = new HashMap<>();
/* 559 */         inserts.put("XMSC_INSERT_METHOD", "createContext()");
/* 560 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC5007", inserts);
/* 561 */         if (Trace.isOn) {
/* 562 */           Trace.throwing(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createContext(int)", (Throwable)je, 1);
/*     */         }
/*     */         
/* 565 */         throw je;
/*     */       } 
/*     */       
/* 568 */       Session session = connection.createSession(sessionMode);
/*     */       
/* 570 */       context = new JmsContextImpl(connection, session);
/*     */     }
/* 572 */     catch (JMSException je) {
/* 573 */       if (Trace.isOn) {
/* 574 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createContext(int)", (Throwable)je, 2);
/*     */       }
/*     */       
/* 577 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/* 578 */       if (Trace.isOn) {
/* 579 */         Trace.throwing(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createContext(int)", (Throwable)traceRet1, 2);
/*     */       }
/*     */       
/* 582 */       throw traceRet1;
/*     */     } 
/*     */     
/* 585 */     if (Trace.isOn) {
/* 586 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createContext(int)", context);
/*     */     }
/*     */     
/* 589 */     return (JMSContext)context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JMSContext createContext(String userName, String password) {
/* 600 */     if (Trace.isOn) {
/* 601 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createContext(String,String)", new Object[] { userName, (password == null) ? password : 
/*     */ 
/*     */             
/* 604 */             Integer.valueOf(password.length()) });
/*     */     }
/*     */     
/* 607 */     JmsContextImpl context = null;
/*     */     try {
/* 609 */       Connection connection = _createConnection(userName, password, true);
/*     */       
/* 611 */       if (!((JmsConnectionMetaDataImpl)connection.getMetaData()).doesConnectionSupport("XMSC_CAPABILITY_JMS2_API")) {
/*     */         
/*     */         try {
/* 614 */           connection.close();
/*     */         }
/* 616 */         catch (Exception e) {
/*     */           
/* 618 */           if (Trace.isOn) {
/* 619 */             Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConnectionFactoryImpl", "createContext(String,String)", e, 1);
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 627 */         HashMap<String, Object> inserts = new HashMap<>();
/* 628 */         inserts.put("XMSC_INSERT_METHOD", "createContext()");
/* 629 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC5007", inserts);
/*     */         
/* 631 */         if (Trace.isOn) {
/* 632 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionFactoryImpl", "createContext(String,String)", (Throwable)je, 1);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 638 */         throw je;
/*     */       } 
/*     */       
/* 641 */       Session session = connection.createSession();
/*     */       
/* 643 */       context = new JmsContextImpl(connection, session);
/*     */     }
/* 645 */     catch (JMSException je) {
/* 646 */       if (Trace.isOn) {
/* 647 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConnectionFactoryImpl", "createContext(String,String)", (Throwable)je, 2);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 654 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*     */       
/* 656 */       if (Trace.isOn) {
/* 657 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionFactoryImpl", "createContext(String,String)", (Throwable)traceRet1, 2);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 663 */       throw traceRet1;
/*     */     } 
/*     */     
/* 666 */     if (Trace.isOn) {
/* 667 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createContext(String,String)", context);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 672 */     return (JMSContext)context;
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
/*     */   public JMSContext createContext(String userName, String password, int sessionMode) {
/* 684 */     if (Trace.isOn) {
/* 685 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createContext(String,String,int)", new Object[] { userName, (password == null) ? password : 
/*     */ 
/*     */ 
/*     */             
/* 689 */             Integer.valueOf(password.length()), 
/* 690 */             Integer.valueOf(sessionMode) });
/*     */     }
/*     */     
/* 693 */     JmsContextImpl context = null;
/*     */     try {
/* 695 */       Connection connection = _createConnection(userName, password, true);
/*     */       
/* 697 */       if (!((JmsConnectionMetaDataImpl)connection.getMetaData()).doesConnectionSupport("XMSC_CAPABILITY_JMS2_API")) {
/*     */         
/*     */         try {
/* 700 */           connection.close();
/*     */         }
/* 702 */         catch (Exception e) {
/*     */           
/* 704 */           if (Trace.isOn) {
/* 705 */             Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConnectionFactoryImpl", "createContext(String,String,int)", e, 1);
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 713 */         HashMap<String, Object> inserts = new HashMap<>();
/* 714 */         inserts.put("XMSC_INSERT_METHOD", "createContext()");
/* 715 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC5007", inserts);
/* 716 */         if (Trace.isOn) {
/* 717 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionFactoryImpl", "createContext(String,String,int)", (Throwable)je, 1);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 723 */         throw je;
/*     */       } 
/*     */       
/* 726 */       Session session = connection.createSession(sessionMode);
/*     */       
/* 728 */       context = new JmsContextImpl(connection, session);
/*     */     }
/* 730 */     catch (JMSException je) {
/* 731 */       if (Trace.isOn) {
/* 732 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConnectionFactoryImpl", "createContext(String,String,int)", (Throwable)je, 2);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 739 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*     */       
/* 741 */       if (Trace.isOn) {
/* 742 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionFactoryImpl", "createContext(String,String,int)", (Throwable)traceRet1, 2);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 748 */       throw traceRet1;
/*     */     } 
/*     */     
/* 751 */     if (Trace.isOn) {
/* 752 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createContext(String,String,int)", context);
/*     */     }
/* 754 */     return (JMSContext)context;
/*     */   }
/*     */ 
/*     */   
/*     */   protected JmsXAContext createXAContextInternal(String userName, String password, int sessionMode) {
/* 759 */     if (Trace.isOn) {
/* 760 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createXAContextInternal(String,String,int)", new Object[] { userName, (password == null) ? password : 
/*     */ 
/*     */             
/* 763 */             Integer.valueOf(password.length()), Integer.valueOf(sessionMode) });
/*     */     }
/*     */     
/* 766 */     JmsXAContextImpl context = null;
/*     */     try {
/* 768 */       XAConnection connection = (XAConnection)createXAConnectionInternal(userName, password);
/*     */       
/* 770 */       if (!((JmsConnectionMetaDataImpl)connection.getMetaData()).doesConnectionSupport("XMSC_CAPABILITY_JMS2_API")) {
/*     */         
/*     */         try {
/* 773 */           connection.close();
/*     */         }
/* 775 */         catch (Exception e) {
/*     */           
/* 777 */           if (Trace.isOn) {
/* 778 */             Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConnectionFactoryImpl", "createXAContextInternal(String,String,int)", e, 1);
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 786 */         HashMap<String, Object> inserts = new HashMap<>();
/* 787 */         inserts.put("XMSC_INSERT_METHOD", "createContext()");
/* 788 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC5007", inserts);
/* 789 */         if (Trace.isOn) {
/* 790 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionFactoryImpl", "createXAContextInternal(String,String,int)", (Throwable)je, 1);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 796 */         throw je;
/*     */       } 
/*     */       
/* 799 */       context = new JmsXAContextImpl((Connection)connection, sessionMode);
/*     */     }
/* 801 */     catch (JMSException je) {
/* 802 */       if (Trace.isOn) {
/* 803 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConnectionFactoryImpl", "createXAContextInternal(String,String,int)", (Throwable)je, 2);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 810 */       JMSRuntimeException traceRet1 = JmsErrorUtils.convertJMSException(je);
/*     */       
/* 812 */       if (Trace.isOn) {
/* 813 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConnectionFactoryImpl", "createXAContextInternal(String,String,int)", (Throwable)traceRet1, 2);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 819 */       throw traceRet1;
/*     */     } 
/*     */     
/* 822 */     if (Trace.isOn) {
/* 823 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "createXAContextInternal(String,String,int)", context);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 828 */     return (JmsXAContext)context;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean logOverrideValues = true;
/*     */   
/*     */   private synchronized void updateConnectionFactoryFromPropertyStore() throws JMSException {
/* 835 */     if (Trace.isOn) {
/* 836 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "updateConnectionFactoryFromPropertyStore()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 841 */     HashMap<String, Object> properties = PropertyStore.getAll();
/*     */     
/* 843 */     StringBuilder builder = new StringBuilder();
/*     */ 
/*     */     
/* 846 */     for (String key : properties.keySet()) {
/* 847 */       if (key.startsWith("jmscf.")) {
/* 848 */         String propName = key.substring("jmscf.".length());
/*     */ 
/*     */ 
/*     */         
/* 852 */         Object newValue = properties.get(key);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 857 */         if (this.logOverrideValues) {
/* 858 */           Object originalValue = getObjectProperty(propName);
/* 859 */           if (originalValue == null) {
/* 860 */             originalValue = new String("<NULL>");
/*     */           }
/* 862 */           HashMap<String, Object> inserts = new HashMap<>();
/* 863 */           inserts.put("XMSC_OVERRIDDEN_PROPERTY", propName);
/* 864 */           inserts.put("XMSC_ORIGINAL__PROPERTY_VALUE", originalValue);
/* 865 */           inserts.put("XMSC_NEW_PROPERTY_VALUE", newValue);
/*     */           
/* 867 */           String message = JmsErrorUtils.getMessage("JMSCC5011", inserts);
/* 868 */           builder.append(message);
/* 869 */           builder.append(PropertyStore.line_separator);
/*     */         } 
/*     */         
/* 872 */         setObjectProperty(propName, newValue);
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
/* 883 */     if (this.logOverrideValues) {
/*     */       
/* 885 */       HashMap<String, Object> inserts = new HashMap<>();
/* 886 */       inserts.put("XMSC_INSERT_PROPERTY_OVERRIDE_LIST", builder.toString());
/* 887 */       JmsErrorUtils.log(this, "updateConnectionFactoryFromPropertyStore", "JMSCC5010", inserts);
/*     */ 
/*     */       
/* 890 */       this.logOverrideValues = false;
/*     */     } 
/*     */     
/* 893 */     if (Trace.isOn) {
/* 894 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsConnectionFactoryImpl", "updateConnectionFactoryFromPropertyStore()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 901 */   private static HashSet<String> changeableProperties = new HashSet<String>() {
/*     */       private static final long serialVersionUID = 3272339995126810793L;
/*     */     };
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\admin\JmsConnectionFactoryImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */