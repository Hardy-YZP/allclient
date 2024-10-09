/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.componentmanager.Component;
/*     */ import com.ibm.msg.client.commonservices.componentmanager.ComponentManager;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsCapabilityContext;
/*     */ import com.ibm.msg.client.jms.JmsConnectionFactory;
/*     */ import com.ibm.msg.client.jms.JmsConstants;
/*     */ import com.ibm.msg.client.jms.JmsDestination;
/*     */ import com.ibm.msg.client.jms.JmsFactoryFactory;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.jms.JmsQueue;
/*     */ import com.ibm.msg.client.jms.JmsQueueConnectionFactory;
/*     */ import com.ibm.msg.client.jms.JmsTopic;
/*     */ import com.ibm.msg.client.jms.JmsTopicConnectionFactory;
/*     */ import com.ibm.msg.client.jms.JmsXAConnectionFactory;
/*     */ import com.ibm.msg.client.jms.JmsXAQueueConnectionFactory;
/*     */ import com.ibm.msg.client.jms.JmsXATopicConnectionFactory;
/*     */ import com.ibm.msg.client.provider.ProviderFactoryFactory;
/*     */ import com.ibm.msg.client.provider.ProviderJmsFactory;
/*     */ import com.ibm.msg.client.provider.ProviderMessageFactory;
/*     */ import com.ibm.msg.client.provider.ProviderMetaData;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Map;
/*     */ import javax.jms.JMSException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsFactoryFactoryImpl
/*     */   extends JmsFactoryFactory
/*     */ {
/*     */   static {
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.data("com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsFactoryFactoryImpl.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   private static final Hashtable<String, JmsFactoryFactoryImpl> factories = new Hashtable<>();
/*     */ 
/*     */   
/*     */   protected int connectionType;
/*     */ 
/*     */   
/*     */   protected String connectionTypeName;
/*     */   
/*     */   private ProviderFactoryFactory providerFactory;
/*     */   
/*     */   private ProviderJmsFactory providerJmsFactory;
/*     */ 
/*     */   
/*     */   public static JmsFactoryFactory getInstance(int connectionType) throws JMSException {
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "getInstance(int)", new Object[] {
/*  89 */             Integer.valueOf(connectionType)
/*     */           });
/*     */     }
/*  92 */     JmsFactoryFactory factory = null;
/*     */ 
/*     */ 
/*     */     
/*  96 */     if (connectionType >= 0 && connectionType < JmsConstants.providerNames.length) {
/*  97 */       factory = getInstance(JmsConstants.providerNames[connectionType]);
/*     */     }
/*     */     else {
/*     */       
/* 101 */       HashMap<String, Object> inserts = new HashMap<>();
/* 102 */       inserts.put("XMSC_CONNECTION_TYPE", Integer.valueOf(connectionType));
/* 103 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0091", inserts);
/*     */       
/* 105 */       if (Trace.isOn) {
/* 106 */         Trace.throwing("com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "getInstance(int)", (Throwable)je);
/*     */       }
/*     */       
/* 109 */       throw je;
/*     */     } 
/*     */     
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "getInstance(int)", factory);
/*     */     }
/*     */     
/* 116 */     return factory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JmsFactoryFactory getInstance(String connectionTypeName) throws JMSException {
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "getInstance(String)", new Object[] { connectionTypeName });
/*     */     }
/*     */ 
/*     */     
/* 130 */     JmsFactoryFactoryImpl factory = null;
/*     */     
/* 132 */     synchronized (factories) {
/* 133 */       factory = factories.get(connectionTypeName);
/*     */       
/* 135 */       if (factory == null) {
/*     */         
/*     */         try {
/* 138 */           factory = new JmsFactoryFactoryImpl();
/* 139 */           factory.connectionTypeName = connectionTypeName;
/*     */ 
/*     */           
/* 142 */           for (int count = 0; count < JmsConstants.providerNames.length; count++) {
/* 143 */             if (JmsConstants.providerNames[count].equalsIgnoreCase(connectionTypeName)) {
/* 144 */               factory.connectionType = count;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/* 149 */           ComponentManager compMgr = ComponentManager.getInstance();
/* 150 */           HashMap<Object, Object> filter = new HashMap<>();
/* 151 */           filter.put("XMSC_PROVIDER_NAME", connectionTypeName);
/* 152 */           Component component = compMgr.getComponent("MPI", filter);
/* 153 */           factory.providerFactory = (ProviderFactoryFactory)component.getFactoryInstance();
/*     */ 
/*     */           
/* 156 */           factories.put(connectionTypeName, factory);
/*     */           
/* 158 */           factory.providerJmsFactory = factory.providerFactory.getJmsFactory();
/*     */         
/*     */         }
/* 161 */         catch (Exception e) {
/* 162 */           if (Trace.isOn) {
/* 163 */             Trace.catchBlock("com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "getInstance(String)", e);
/*     */           }
/*     */           
/* 166 */           HashMap<String, Object> inserts = new HashMap<>();
/* 167 */           inserts.put("XMSC_CONNECTION_TYPE", connectionTypeName);
/* 168 */           JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0091", inserts);
/*     */           
/* 170 */           je.setLinkedException(e);
/* 171 */           if (Trace.isOn) {
/* 172 */             Trace.throwing("com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "getInstance(String)", (Throwable)je);
/*     */           }
/*     */           
/* 175 */           throw je;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "getInstance(String)", factory);
/*     */     }
/*     */     
/* 184 */     return factory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JmsFactoryFactory getInstance(HashMap<String, Object> filter) throws JMSException {
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "getInstance(HashMap<String , Object>)", new Object[] { filter });
/*     */     }
/*     */     
/* 198 */     String connectionTypeName = (String)filter.get("XMSC_PROVIDER_NAME");
/*     */     
/* 200 */     JmsFactoryFactory traceRet1 = getInstance(connectionTypeName);
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "getInstance(HashMap<String , Object>)", traceRet1);
/*     */     }
/*     */     
/* 205 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsConnectionFactory createConnectionFactory() throws JMSException {
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createConnectionFactory()");
/*     */     }
/*     */ 
/*     */     
/* 219 */     JmsConnectionFactory traceRet1 = this.providerJmsFactory.createConnectionFactory();
/*     */ 
/*     */     
/* 222 */     traceRet1.setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)20);
/*     */ 
/*     */     
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createConnectionFactory()", traceRet1);
/*     */     }
/*     */     
/* 229 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsConnectionFactory createConnectionFactory(String name) throws JMSException {
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createConnectionFactory(String)", new Object[] { name });
/*     */     }
/*     */ 
/*     */     
/* 241 */     JmsConnectionFactory traceRet1 = this.providerJmsFactory.createConnectionFactory(name);
/*     */ 
/*     */     
/* 244 */     traceRet1.setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)20);
/*     */ 
/*     */     
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createConnectionFactory(String)", traceRet1);
/*     */     }
/*     */     
/* 251 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsQueueConnectionFactory createQueueConnectionFactory() throws JMSException {
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createQueueConnectionFactory()");
/*     */     }
/*     */     
/* 263 */     JmsQueueConnectionFactory traceRet1 = this.providerJmsFactory.createQueueConnectionFactory();
/*     */ 
/*     */     
/* 266 */     traceRet1.setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)17);
/*     */ 
/*     */     
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createQueueConnectionFactory()", traceRet1);
/*     */     }
/*     */     
/* 273 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsQueueConnectionFactory createQueueConnectionFactory(String uri) throws JMSException {
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createQueueConnectionFactory(String)", new Object[] { uri });
/*     */     }
/*     */     
/* 286 */     JmsQueueConnectionFactory traceRet1 = this.providerJmsFactory.createQueueConnectionFactory(uri);
/*     */ 
/*     */     
/* 289 */     traceRet1.setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)17);
/*     */ 
/*     */     
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createQueueConnectionFactory(String)", traceRet1);
/*     */     }
/*     */     
/* 296 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsTopicConnectionFactory createTopicConnectionFactory() throws JMSException {
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createTopicConnectionFactory()");
/*     */     }
/*     */     
/* 309 */     JmsTopicConnectionFactory traceRet1 = this.providerJmsFactory.createTopicConnectionFactory();
/*     */ 
/*     */     
/* 312 */     traceRet1.setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)18);
/*     */ 
/*     */     
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createTopicConnectionFactory()", traceRet1);
/*     */     }
/*     */     
/* 319 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsTopicConnectionFactory createTopicConnectionFactory(String uri) throws JMSException {
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createTopicConnectionFactory(String)", new Object[] { uri });
/*     */     }
/*     */     
/* 332 */     JmsTopicConnectionFactory traceRet1 = this.providerJmsFactory.createTopicConnectionFactory(uri);
/*     */ 
/*     */     
/* 335 */     traceRet1.setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)18);
/*     */ 
/*     */     
/* 338 */     if (Trace.isOn) {
/* 339 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createTopicConnectionFactory(String)", traceRet1);
/*     */     }
/*     */     
/* 342 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsXAConnectionFactory createXAConnectionFactory() throws JMSException {
/* 351 */     if (Trace.isOn) {
/* 352 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXAConnectionFactory()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 358 */     JmsCapabilityContext capabilities = getCapabilities();
/*     */ 
/*     */     
/* 361 */     if (!capabilities.getBooleanProperty("XMSC_CAPABILITY_TRANSACTIONS_XA")) {
/*     */       
/* 363 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC4001", null);
/*     */       
/* 365 */       if (Trace.isOn) {
/* 366 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXAConnectionFactory()", (Throwable)je);
/*     */       }
/*     */       
/* 369 */       throw je;
/*     */     } 
/*     */     
/* 372 */     JmsXAConnectionFactory traceRet1 = this.providerJmsFactory.createXAConnectionFactory();
/*     */ 
/*     */     
/* 375 */     traceRet1
/* 376 */       .setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)84);
/*     */ 
/*     */ 
/*     */     
/* 380 */     if (Trace.isOn) {
/* 381 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXAConnectionFactory()", traceRet1);
/*     */     }
/*     */     
/* 384 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsXAConnectionFactory createXAConnectionFactory(String uri) throws JMSException {
/* 393 */     if (Trace.isOn) {
/* 394 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXAConnectionFactory(String)", new Object[] { uri });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 400 */     JmsCapabilityContext capabilities = getCapabilities();
/*     */ 
/*     */     
/* 403 */     if (!capabilities.getBooleanProperty("XMSC_CAPABILITY_TRANSACTIONS_XA")) {
/*     */       
/* 405 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC4001", null);
/*     */       
/* 407 */       if (Trace.isOn) {
/* 408 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXAConnectionFactory(String)", (Throwable)je);
/*     */       }
/*     */       
/* 411 */       throw je;
/*     */     } 
/*     */     
/* 414 */     JmsXAConnectionFactory traceRet1 = this.providerJmsFactory.createXAConnectionFactory(uri);
/*     */ 
/*     */     
/* 417 */     traceRet1
/* 418 */       .setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)84);
/*     */ 
/*     */ 
/*     */     
/* 422 */     if (Trace.isOn) {
/* 423 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXAConnectionFactory(String)", traceRet1);
/*     */     }
/*     */     
/* 426 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsXAQueueConnectionFactory createXAQueueConnectionFactory() throws JMSException {
/* 435 */     if (Trace.isOn) {
/* 436 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXAQueueConnectionFactory()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 442 */     JmsCapabilityContext capabilities = getCapabilities();
/*     */ 
/*     */     
/* 445 */     if (!capabilities.getBooleanProperty("XMSC_CAPABILITY_TRANSACTIONS_XA")) {
/*     */       
/* 447 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC4001", null);
/*     */       
/* 449 */       if (Trace.isOn) {
/* 450 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXAQueueConnectionFactory()", (Throwable)je);
/*     */       }
/*     */       
/* 453 */       throw je;
/*     */     } 
/*     */     
/* 456 */     JmsXAQueueConnectionFactory traceRet1 = this.providerJmsFactory.createXAQueueConnectionFactory();
/*     */ 
/*     */     
/* 459 */     traceRet1
/* 460 */       .setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)81);
/*     */ 
/*     */ 
/*     */     
/* 464 */     if (Trace.isOn) {
/* 465 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXAQueueConnectionFactory()", traceRet1);
/*     */     }
/*     */     
/* 468 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsXAQueueConnectionFactory createXAQueueConnectionFactory(String uri) throws JMSException {
/* 477 */     if (Trace.isOn) {
/* 478 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXAQueueConnectionFactory(String)", new Object[] { uri });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 484 */     JmsCapabilityContext capabilities = getCapabilities();
/*     */ 
/*     */     
/* 487 */     if (!capabilities.getBooleanProperty("XMSC_CAPABILITY_TRANSACTIONS_XA")) {
/*     */       
/* 489 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC4001", null);
/*     */       
/* 491 */       if (Trace.isOn) {
/* 492 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXAQueueConnectionFactory(String)", (Throwable)je);
/*     */       }
/*     */       
/* 495 */       throw je;
/*     */     } 
/*     */     
/* 498 */     JmsXAQueueConnectionFactory traceRet1 = this.providerJmsFactory.createXAQueueConnectionFactory(uri);
/*     */ 
/*     */     
/* 501 */     traceRet1
/* 502 */       .setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)81);
/*     */ 
/*     */ 
/*     */     
/* 506 */     if (Trace.isOn) {
/* 507 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXAQueueConnectionFactory(String)", traceRet1);
/*     */     }
/*     */     
/* 510 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsXATopicConnectionFactory createXATopicConnectionFactory() throws JMSException {
/* 519 */     if (Trace.isOn) {
/* 520 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXATopicConnectionFactory()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 526 */     JmsCapabilityContext capabilities = getCapabilities();
/*     */ 
/*     */     
/* 529 */     if (!capabilities.getBooleanProperty("XMSC_CAPABILITY_TRANSACTIONS_XA")) {
/*     */       
/* 531 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC4001", null);
/*     */       
/* 533 */       if (Trace.isOn) {
/* 534 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXATopicConnectionFactory()", (Throwable)je);
/*     */       }
/*     */       
/* 537 */       throw je;
/*     */     } 
/*     */     
/* 540 */     JmsXATopicConnectionFactory traceRet1 = this.providerJmsFactory.createXATopicConnectionFactory();
/*     */ 
/*     */     
/* 543 */     traceRet1
/* 544 */       .setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)82);
/*     */ 
/*     */ 
/*     */     
/* 548 */     if (Trace.isOn) {
/* 549 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXATopicConnectionFactory()", traceRet1);
/*     */     }
/*     */     
/* 552 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsXATopicConnectionFactory createXATopicConnectionFactory(String uri) throws JMSException {
/* 561 */     if (Trace.isOn) {
/* 562 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXATopicConnectionFactory(String)", new Object[] { uri });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 568 */     JmsCapabilityContext capabilities = getCapabilities();
/*     */ 
/*     */     
/* 571 */     if (!capabilities.getBooleanProperty("XMSC_CAPABILITY_TRANSACTIONS_XA")) {
/*     */       
/* 573 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC4001", null);
/*     */       
/* 575 */       if (Trace.isOn) {
/* 576 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXATopicConnectionFactory(String)", (Throwable)je);
/*     */       }
/*     */       
/* 579 */       throw je;
/*     */     } 
/*     */     
/* 582 */     JmsXATopicConnectionFactory traceRet1 = this.providerJmsFactory.createXATopicConnectionFactory(uri);
/*     */ 
/*     */     
/* 585 */     traceRet1
/* 586 */       .setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)82);
/*     */ 
/*     */ 
/*     */     
/* 590 */     if (Trace.isOn) {
/* 591 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createXATopicConnectionFactory(String)", traceRet1);
/*     */     }
/*     */     
/* 594 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsQueue createQueue(String name) throws JMSException {
/* 603 */     if (Trace.isOn) {
/* 604 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createQueue(String)", new Object[] { name });
/*     */     }
/*     */     
/* 607 */     JmsQueue traceRet1 = this.providerJmsFactory.createQueue(name);
/*     */ 
/*     */     
/* 610 */     traceRet1.setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)33);
/*     */ 
/*     */     
/* 613 */     if (Trace.isOn) {
/* 614 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createQueue(String)", traceRet1);
/*     */     }
/*     */     
/* 617 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsTopic createTopic(String name) throws JMSException {
/* 626 */     if (Trace.isOn) {
/* 627 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createTopic(String)", new Object[] { name });
/*     */     }
/*     */ 
/*     */     
/* 631 */     JmsTopic traceRet1 = this.providerJmsFactory.createTopic(name);
/*     */     
/* 633 */     traceRet1.setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)34);
/*     */ 
/*     */     
/* 636 */     if (Trace.isOn) {
/* 637 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createTopic(String)", traceRet1);
/*     */     }
/*     */     
/* 640 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getConnectionType() {
/* 649 */     if (Trace.isOn) {
/* 650 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "getConnectionType()", "getter", 
/* 651 */           Integer.valueOf(this.connectionType));
/*     */     }
/* 653 */     return this.connectionType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getConnectionTypeName() {
/* 662 */     if (Trace.isOn) {
/* 663 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "getConnectionTypeName()", "getter", this.connectionTypeName);
/*     */     }
/*     */     
/* 666 */     return this.connectionTypeName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsCapabilityContext getCapabilities() throws JMSException {
/* 675 */     Map<String, Object> captable = this.providerFactory.getCapabilities();
/* 676 */     JmsCapabilityContext capabilities = new JmsCapabilityContextImpl(captable, false);
/* 677 */     if (Trace.isOn) {
/* 678 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "getCapabilities()", "getter", capabilities);
/*     */     }
/*     */     
/* 681 */     return capabilities;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderFactoryFactory getProviderFactoryFactory() throws JMSException {
/* 692 */     if (Trace.isOn) {
/* 693 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "getProviderFactoryFactory()", "getter", this.providerFactory);
/*     */     }
/*     */     
/* 696 */     return this.providerFactory;
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
/*     */   protected ProviderMessageFactory getProviderMessageFactory() throws JMSException {
/* 708 */     ProviderMessageFactory traceRet1 = this.providerFactory.getMessageFactory();
/* 709 */     if (Trace.isOn) {
/* 710 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "getProviderMessageFactory()", "getter", traceRet1);
/*     */     }
/*     */     
/* 713 */     return traceRet1;
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
/*     */   protected ProviderMetaData getProviderMetaData() throws JMSException {
/* 726 */     ProviderMetaData traceRet1 = this.providerFactory.getMetaData();
/* 727 */     if (Trace.isOn) {
/* 728 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "getProviderMetaData()", "getter", traceRet1);
/*     */     }
/*     */     
/* 731 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsConnectionFactory createConnectionFactory(short flag) throws JMSException {
/* 741 */     if (Trace.isOn) {
/* 742 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createConnectionFactory(short)", new Object[] {
/* 743 */             Short.valueOf(flag)
/*     */           });
/*     */     }
/* 746 */     JmsConnectionFactory cf = (JmsConnectionFactory)createJmsObject((short)(flag | 0x10), null);
/*     */     
/* 748 */     if (Trace.isOn) {
/* 749 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createConnectionFactory(short)", cf);
/*     */     }
/*     */     
/* 752 */     return cf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsDestination createDestination(short flag, String name) throws JMSException {
/* 760 */     if (Trace.isOn)
/* 761 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createDestination(short,String)", new Object[] {
/* 762 */             Short.valueOf(flag), name
/*     */           }); 
/* 764 */     JmsDestination dest = (JmsDestination)createJmsObject((short)(flag | 0x20), name);
/* 765 */     if (Trace.isOn) {
/* 766 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createDestination(short,String)", dest);
/*     */     }
/*     */     
/* 769 */     return dest;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsPropertyContext createJmsObject(short flag, Object parameter) throws JMSException {
/* 777 */     if (Trace.isOn) {
/* 778 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createJmsObject(short,Object)", new Object[] {
/* 779 */             Short.valueOf(flag), parameter
/*     */           });
/*     */     }
/*     */ 
/*     */     
/* 784 */     short[] invalidFlags = { 48, 36, 7, 96, 288, 320 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 795 */     boolean valid = true;
/* 796 */     for (int count = 0; count < invalidFlags.length; count++) {
/* 797 */       valid = checkFlag(flag, invalidFlags[count]);
/*     */ 
/*     */       
/* 800 */       if (!valid) {
/* 801 */         HashMap<String, Object> inserts = new HashMap<>();
/*     */         
/* 803 */         inserts.put("XMSC_INSERT_VALUE", Short.valueOf(flag));
/* 804 */         inserts.put("XMSC_INSERT_NAME", "JmsObject flag");
/* 805 */         JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0005", inserts);
/*     */         
/* 807 */         if (Trace.isOn) {
/* 808 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createJmsObject(short,Object)", (Throwable)je);
/*     */         }
/*     */         
/* 811 */         throw je;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 816 */     JmsPropertyContext jmsObject = this.providerJmsFactory.createJmsObject(flag, parameter);
/*     */ 
/*     */     
/* 819 */     if (jmsObject != null) {
/* 820 */       jmsObject.setShortProperty("XMSC_ADMIN_OBJECT_TYPE", flag);
/*     */     }
/*     */     
/* 823 */     if (Trace.isOn) {
/* 824 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "createJmsObject(short,Object)", jmsObject);
/*     */     }
/*     */     
/* 827 */     return jmsObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean checkFlag(short flag, short flagToCheck) {
/* 838 */     if (Trace.isOn) {
/* 839 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "checkFlag(short,short)", new Object[] {
/* 840 */             Short.valueOf(flag), Short.valueOf(flagToCheck)
/*     */           });
/*     */     }
/*     */ 
/*     */     
/* 845 */     boolean containsInvalidFlag = ((flag & flagToCheck) == flagToCheck);
/*     */     
/* 847 */     if (Trace.isOn) {
/* 848 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl", "checkFlag(short,short)", 
/* 849 */           Boolean.valueOf(!containsInvalidFlag));
/*     */     }
/* 851 */     return !containsInvalidFlag;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsFactoryFactoryImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */