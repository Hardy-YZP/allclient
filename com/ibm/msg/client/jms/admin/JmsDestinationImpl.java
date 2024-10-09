/*     */ package com.ibm.msg.client.jms.admin;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsConstants;
/*     */ import com.ibm.msg.client.jms.JmsDestination;
/*     */ import com.ibm.msg.client.jms.JmsFactoryFactory;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.jms.internal.JmsErrorUtils;
/*     */ import com.ibm.msg.client.jms.internal.JmsFactoryFactoryImpl;
/*     */ import com.ibm.msg.client.jms.internal.JmsPropertyContextImpl;
/*     */ import com.ibm.msg.client.jms.internal.JmsReadablePropertyContextImpl;
/*     */ import com.ibm.msg.client.provider.ProviderDestination;
/*     */ import com.ibm.msg.client.provider.ProviderFactoryFactory;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public class JmsDestinationImpl
/*     */   extends JmsPropertyContextImpl
/*     */   implements JmsDestination
/*     */ {
/*     */   private static final long serialVersionUID = -6804615500270643424L;
/*     */   private String originalName;
/*     */   private String connectionTypeName;
/*     */   private transient ProviderDestination providerDestination;
/*     */   private volatile transient int useCount;
/*     */   
/*     */   static {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.data("com.ibm.msg.client.jms.admin.JmsDestinationImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/admin/JmsDestinationImpl.java");
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
/*  87 */   private transient Object useCountLock = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsDestinationImpl() {
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "<init>()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 100 */       initialise((String)null, (String)null);
/*     */     }
/* 102 */     catch (JMSException e) {
/* 103 */       if (Trace.isOn) {
/* 104 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "<init>()", (Throwable)e);
/*     */       }
/* 106 */       HashMap<String, JMSException> info = new HashMap<>();
/* 107 */       info.put("exception", e);
/* 108 */       Trace.ffst(this, "setDefaultProperties()", "XJ005002", info, null);
/*     */     } 
/*     */     
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "<init>()");
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
/*     */   public JmsDestinationImpl(String connectionTypeName) {
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "<init>(String)", new Object[] { connectionTypeName });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 133 */       initialise(connectionTypeName, (String)null);
/*     */     }
/* 135 */     catch (JMSException e) {
/* 136 */       if (Trace.isOn) {
/* 137 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "<init>(String)", (Throwable)e);
/*     */       }
/*     */       
/* 140 */       HashMap<String, JMSException> info = new HashMap<>();
/* 141 */       info.put("exception", e);
/* 142 */       Trace.ffst(this, "setDefaultProperties()", "XJ005003", info, null);
/*     */     } 
/*     */     
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "<init>(String)");
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
/*     */   public JmsDestinationImpl(String connectionTypeName, String name) throws JMSException {
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "<init>(String,String)", new Object[] { connectionTypeName, name });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 166 */     if (!(this instanceof com.ibm.msg.client.jms.JmsTemporaryQueue) && !(this instanceof com.ibm.msg.client.jms.JmsTemporaryTopic) && (
/* 167 */       name == null || name.length() == 0 || name.equals("queue://") || name.equals("topic://"))) {
/*     */ 
/*     */       
/* 170 */       HashMap<String, Object> inserts = new HashMap<>();
/* 171 */       inserts.put("XMSC_DESTINATION_NAME", name);
/* 172 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC0095", inserts);
/* 173 */       if (Trace.isOn) {
/* 174 */         Trace.throwing(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "<init>(String,String)", (Throwable)je);
/*     */       }
/*     */       
/* 177 */       throw je;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 182 */     initialise(connectionTypeName, name);
/*     */     
/* 184 */     if (Trace.isOn) {
/* 185 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "<init>(String,String)");
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
/*     */   protected void initialise(String connectionTypeName1, String name) throws JMSException {
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "initialise(String,String)", new Object[] { connectionTypeName1, name });
/*     */     }
/*     */ 
/*     */     
/* 208 */     setDefaultProperties();
/*     */     
/* 210 */     if (connectionTypeName1 != null) {
/*     */       
/* 212 */       setConnectionTypeName(connectionTypeName1);
/* 213 */       if (name == null) {
/* 214 */         setProviderDestination();
/*     */       } else {
/*     */         
/* 217 */         setProviderDestination(name);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "initialise(String,String)");
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
/*     */   protected String getName() {
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "getName()");
/*     */     }
/*     */     
/* 239 */     if (this.providerDestination != null) {
/* 240 */       String traceRet2 = this.providerDestination.getName();
/* 241 */       if (Trace.isOn) {
/* 242 */         Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "getName()", traceRet2, 1);
/*     */       }
/*     */       
/* 245 */       return traceRet2;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 252 */       String name = getStringProperty("XMSC_DESTINATION_NAME");
/* 253 */       if (Trace.isOn) {
/* 254 */         Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "getName()", name, 2);
/*     */       }
/* 256 */       return name;
/*     */     }
/* 258 */     catch (JMSException e) {
/* 259 */       if (Trace.isOn) {
/* 260 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "getName()", (Throwable)e);
/*     */       }
/*     */       
/* 263 */       if (Trace.isOn) {
/* 264 */         Trace.data(this, "getName", "Failed to retrieve name from either a ProviderDestination, or from the DESTINATION_NAME property", null);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 270 */       if (Trace.isOn) {
/* 271 */         Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "getName()", null, 3);
/*     */       }
/* 273 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setProviderDestination() throws JMSException {
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "setProviderDestination()");
/*     */     }
/*     */ 
/*     */     
/* 286 */     setProviderDestination((String)null);
/*     */     
/* 288 */     if (Trace.isOn) {
/* 289 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "setProviderDestination()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setProviderDestination(String name) throws JMSException {
/* 300 */     if (Trace.isOn) {
/* 301 */       Trace.data(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "setProviderDestination(String)", "setter", name);
/*     */     }
/*     */ 
/*     */     
/* 305 */     if (this.providerDestination == null) {
/*     */       
/* 307 */       ProviderFactoryFactory providerFactory = ((JmsFactoryFactoryImpl)JmsFactoryFactory.getInstance(this.connectionTypeName)).getProviderFactoryFactory();
/*     */       
/* 309 */       int destType = -1;
/* 310 */       if (this instanceof com.ibm.msg.client.jms.JmsQueue) {
/* 311 */         destType = 1;
/*     */       }
/* 313 */       else if (this instanceof com.ibm.msg.client.jms.JmsTopic) {
/* 314 */         destType = 2;
/*     */       } 
/*     */       
/* 317 */       this.originalName = name;
/*     */       
/* 319 */       setProviderDestination(providerFactory.createProviderDestination(destType, name, (JmsPropertyContext)this));
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
/*     */   protected void setProviderDestination(ProviderDestination providerDest) throws JMSException {
/* 335 */     if (Trace.isOn) {
/* 336 */       Trace.data(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "setProviderDestination(ProviderDestination)", "setter", providerDest);
/*     */     }
/*     */ 
/*     */     
/* 340 */     this.providerDestination = providerDest;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStringProperty(String propertyName, String value) throws JMSException {
/* 350 */     if (Trace.isOn) {
/* 351 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "setStringProperty(String,String)", new Object[] { propertyName, value });
/*     */     }
/*     */     
/* 354 */     if (propertyName.equals("XMSC_DESTINATION_NAME")) {
/* 355 */       this.originalName = value;
/*     */     }
/* 357 */     super.setStringProperty(propertyName, value);
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "setStringProperty(String,String)");
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
/*     */   protected ProviderDestination getProviderDestination() throws JMSException {
/* 371 */     setProviderDestination();
/* 372 */     if (Trace.isOn) {
/* 373 */       Trace.data(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "getProviderDestination()", "getter", this.providerDestination);
/*     */     }
/*     */     
/* 376 */     return this.providerDestination;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getConnectionTypeName() {
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.data(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "getConnectionTypeName()", "getter", this.connectionTypeName);
/*     */     }
/*     */     
/* 385 */     return this.connectionTypeName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 394 */     if (Trace.isOn) {
/* 395 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "toString()");
/*     */     }
/*     */     
/* 398 */     String traceRet1 = toURI();
/* 399 */     if (Trace.isOn) {
/* 400 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "toString()", traceRet1);
/*     */     }
/*     */     
/* 403 */     return traceRet1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setConnectionTypeName(String connectionTypeName) {
/* 408 */     if (Trace.isOn) {
/* 409 */       Trace.data(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "setConnectionTypeName(String)", "setter", connectionTypeName);
/*     */     }
/*     */ 
/*     */     
/* 413 */     this.connectionTypeName = connectionTypeName;
/*     */     try {
/* 415 */       setStringProperty("XMSC_CONNECTION_TYPE_NAME", connectionTypeName);
/*     */     }
/* 417 */     catch (JMSException e) {
/* 418 */       if (Trace.isOn) {
/* 419 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "setConnectionTypeName(String)", (Throwable)e, 1);
/*     */       }
/*     */ 
/*     */       
/* 423 */       Trace.ffst(this, "setConnectionTypeName(String)", "XJ005004", null, null);
/*     */     } 
/*     */ 
/*     */     
/* 427 */     boolean found = false;
/* 428 */     for (int count = 0; count < JmsConstants.providerNames.length; count++) {
/* 429 */       if (JmsConstants.providerNames[count].equalsIgnoreCase(connectionTypeName)) {
/*     */         try {
/* 431 */           setIntProperty("XMSC_CONNECTION_TYPE", count);
/*     */         }
/* 433 */         catch (JMSException e) {
/* 434 */           if (Trace.isOn) {
/* 435 */             Trace.catchBlock(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "setConnectionTypeName(String)", (Throwable)e, 2);
/*     */           }
/*     */ 
/*     */           
/* 439 */           Trace.ffst(this, "setConnectionTypeName(String)", "XJ005005", null, null);
/*     */         } 
/* 441 */         found = true;
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 449 */     if (!found) {
/* 450 */       HashMap<String, String> data = new HashMap<>();
/* 451 */       data.put("XMSC_CONNECTION_TYPE_NAME", connectionTypeName);
/* 452 */       data.put("MSG", "Unkown connection type name");
/* 453 */       Trace.ffst(this, "setConnectionTypeName(String)", "XJ005006", data, null);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String toURI() {
/* 458 */     if (Trace.isOn) {
/* 459 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "toURI()");
/*     */     }
/* 461 */     String uri = null;
/*     */     
/* 463 */     if (this.providerDestination != null) {
/*     */       try {
/* 465 */         uri = this.providerDestination.toURI();
/*     */       }
/* 467 */       catch (JMSException e) {
/* 468 */         if (Trace.isOn) {
/* 469 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "toURI()", (Throwable)e);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 474 */     if (Trace.isOn) {
/* 475 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "toURI()", uri);
/*     */     }
/* 477 */     return uri;
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
/*     */   public String getQueueName() throws JMSException {
/* 489 */     if (this.providerDestination != null && this.providerDestination.isTopic()) {
/* 490 */       HashMap<String, Object> inserts = new HashMap<>();
/* 491 */       inserts.put("XMSC_INSERT_METHOD", "getQueueName()");
/* 492 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 493 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1112", inserts);
/*     */       
/* 495 */       if (Trace.isOn) {
/* 496 */         Trace.throwing(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "getQueueName()", (Throwable)je);
/*     */       }
/*     */       
/* 499 */       throw je;
/*     */     } 
/* 501 */     String traceRet1 = toURI();
/* 502 */     if (Trace.isOn) {
/* 503 */       Trace.data(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "getQueueName()", "getter", traceRet1);
/*     */     }
/*     */     
/* 506 */     return traceRet1;
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
/*     */   public String getTopicName() throws JMSException {
/* 518 */     if (this.providerDestination != null && this.providerDestination.isQueue()) {
/* 519 */       HashMap<String, Object> inserts = new HashMap<>();
/* 520 */       inserts.put("XMSC_INSERT_METHOD", "getTopicName()");
/* 521 */       inserts.put("XMSC_INSERT_TYPE", getClass().getName());
/* 522 */       JMSException je = (JMSException)JmsErrorUtils.createException("JMSCC1112", inserts);
/*     */       
/* 524 */       if (Trace.isOn) {
/* 525 */         Trace.throwing(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "getTopicName()", (Throwable)je);
/*     */       }
/*     */       
/* 528 */       throw je;
/*     */     } 
/*     */     
/* 531 */     String traceRet1 = toURI();
/* 532 */     if (Trace.isOn) {
/* 533 */       Trace.data(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "getTopicName()", "getter", traceRet1);
/*     */     }
/*     */     
/* 536 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getUseCount() {
/* 544 */     if (Trace.isOn) {
/* 545 */       Trace.data(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "getUseCount()", "getter", 
/* 546 */           Integer.valueOf(this.useCount));
/*     */     }
/* 548 */     return this.useCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void incrementUseCount() {
/* 555 */     if (Trace.isOn) {
/* 556 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "incrementUseCount()");
/*     */     }
/*     */     
/* 559 */     synchronized (this.useCountLock) {
/* 560 */       this.useCount++;
/*     */     } 
/*     */     
/* 563 */     if (Trace.isOn) {
/* 564 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "incrementUseCount()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void decrementUseCount() {
/* 573 */     if (Trace.isOn) {
/* 574 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "decrementUseCount()");
/*     */     }
/*     */     
/* 577 */     synchronized (this.useCountLock) {
/* 578 */       this.useCount--;
/*     */       
/* 580 */       if (this.useCount < 0) {
/* 581 */         Trace.ffst(this, "decrementUsageCount()", "XJ005001", null, null);
/*     */       }
/*     */     } 
/*     */     
/* 585 */     if (Trace.isOn) {
/* 586 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "decrementUseCount()");
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
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 599 */     if (Trace.isOn) {
/* 600 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "readObject(java.io.ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */ 
/*     */     
/* 604 */     in.defaultReadObject();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 611 */       if (this.connectionTypeName != null) {
/* 612 */         setProviderDestination();
/*     */       }
/* 614 */       this.useCountLock = new Object();
/*     */     }
/* 616 */     catch (JMSException e) {
/* 617 */       if (Trace.isOn) {
/* 618 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "readObject(java.io.ObjectInputStream)", (Throwable)e);
/*     */       }
/*     */ 
/*     */       
/* 622 */       HashMap<String, JMSException> info = new HashMap<>();
/* 623 */       info.put("exception", e);
/* 624 */       Trace.ffst(this, "readObject(ObjectInputStream)", "XJ005007", info, JMSException.class);
/*     */     } 
/*     */     
/* 627 */     if (Trace.isOn) {
/* 628 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "readObject(java.io.ObjectInputStream)");
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
/*     */   private void readObjectNoData() throws ObjectStreamException {
/* 643 */     if (Trace.isOn) {
/* 644 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "readObjectNoData()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 651 */     this.useCountLock = new Object();
/* 652 */     if (Trace.isOn) {
/* 653 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "readObjectNoData()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setDefaultProperties() {
/* 663 */     if (Trace.isOn) {
/* 664 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "setDefaultProperties()");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 671 */       if (this instanceof javax.jms.Queue) {
/* 672 */         setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)33);
/*     */       }
/* 674 */       else if (this instanceof javax.jms.Topic) {
/* 675 */         setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)34);
/*     */       } else {
/*     */         
/* 678 */         setShortProperty("XMSC_ADMIN_OBJECT_TYPE", (short)36);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 684 */       setIntProperty("deliveryMode", -2);
/* 685 */       setLongProperty("timeToLive", -2L);
/* 686 */       setIntProperty("priority", -2);
/*     */     }
/* 688 */     catch (JMSException e) {
/* 689 */       if (Trace.isOn) {
/* 690 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "setDefaultProperties()", (Throwable)e);
/*     */       }
/*     */ 
/*     */       
/* 694 */       HashMap<String, JMSException> info = new HashMap<>();
/* 695 */       info.put("exception", e);
/* 696 */       Trace.ffst(this, "setDefaultProperties()", "XJ005008", info, null);
/*     */     } 
/* 698 */     if (Trace.isOn) {
/* 699 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "setDefaultProperties()");
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
/*     */   protected static void _decrementUseCount(JmsDestinationImpl dest) {
/* 712 */     if (Trace.isOn) {
/* 713 */       Trace.entry("com.ibm.msg.client.jms.admin.JmsDestinationImpl", "_decrementUseCount(JmsDestinationImpl)", new Object[] { dest });
/*     */     }
/*     */     
/* 716 */     dest.decrementUseCount();
/* 717 */     if (Trace.isOn) {
/* 718 */       Trace.exit("com.ibm.msg.client.jms.admin.JmsDestinationImpl", "_decrementUseCount(JmsDestinationImpl)");
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
/*     */   protected static void _incrementUseCount(JmsDestinationImpl dest) {
/* 731 */     if (Trace.isOn) {
/* 732 */       Trace.entry("com.ibm.msg.client.jms.admin.JmsDestinationImpl", "_incrementUseCount(JmsDestinationImpl)", new Object[] { dest });
/*     */     }
/*     */     
/* 735 */     dest.incrementUseCount();
/* 736 */     if (Trace.isOn) {
/* 737 */       Trace.exit("com.ibm.msg.client.jms.admin.JmsDestinationImpl", "_incrementUseCount(JmsDestinationImpl)");
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
/*     */   protected static void _setProviderDestination(JmsDestinationImpl dest) throws JMSException {
/* 750 */     if (Trace.isOn) {
/* 751 */       Trace.entry("com.ibm.msg.client.jms.admin.JmsDestinationImpl", "_setProviderDestination(JmsDestinationImpl)", new Object[] { dest });
/*     */     }
/*     */     
/* 754 */     dest.setProviderDestination();
/* 755 */     if (Trace.isOn) {
/* 756 */       Trace.exit("com.ibm.msg.client.jms.admin.JmsDestinationImpl", "_setProviderDestination(JmsDestinationImpl)");
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
/*     */   protected static ProviderDestination _getProviderDestination(JmsDestinationImpl dest) throws JMSException {
/* 769 */     if (Trace.isOn) {
/* 770 */       Trace.entry("com.ibm.msg.client.jms.admin.JmsDestinationImpl", "_getProviderDestination(JmsDestinationImpl)", new Object[] { dest });
/*     */     }
/*     */     
/* 773 */     ProviderDestination traceRet1 = dest.getProviderDestination();
/* 774 */     if (Trace.isOn) {
/* 775 */       Trace.exit("com.ibm.msg.client.jms.admin.JmsDestinationImpl", "_getProviderDestination(JmsDestinationImpl)", traceRet1);
/*     */     }
/*     */     
/* 778 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 789 */     if (Trace.isOn) {
/* 790 */       Trace.entry(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "hashCode()");
/*     */     }
/* 792 */     int result = 42;
/*     */     try {
/* 794 */       Map<String, Object> map = getProperties();
/* 795 */       Set<Map.Entry<String, Object>> entrySet = map.entrySet();
/* 796 */       synchronized (map) {
/* 797 */         Iterator<Map.Entry<String, Object>> i = entrySet.iterator();
/* 798 */         while (i.hasNext()) {
/* 799 */           Map.Entry<String, Object> e = i.next();
/* 800 */           String key = e.getKey();
/* 801 */           String value = getStringProperty(key);
/*     */           
/* 803 */           if (value != null) {
/* 804 */             result ^= value.hashCode();
/*     */           }
/*     */         }
/*     */       
/*     */       } 
/* 809 */     } catch (JMSException e) {
/* 810 */       if (Trace.isOn) {
/* 811 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "hashCode()", (Throwable)e);
/*     */       }
/*     */       
/* 814 */       if (Trace.isOn) {
/* 815 */         Trace.catchBlock("com.ibm.msg.client.jms.internal.JmsDestinationImpl", "hashCode", (Throwable)e);
/*     */       }
/*     */     } 
/* 818 */     if (Trace.isOn) {
/* 819 */       Trace.exit(this, "com.ibm.msg.client.jms.admin.JmsDestinationImpl", "hashCode()", 
/* 820 */           Integer.valueOf(result));
/*     */     }
/* 822 */     return result;
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
/* 836 */     boolean result = true;
/*     */ 
/*     */     
/* 839 */     if (obj == null) {
/* 840 */       return false;
/*     */     }
/* 842 */     if (this == obj) {
/* 843 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 847 */     if (!getClass().getName().equals(obj.getClass().getName())) {
/* 848 */       result = false;
/*     */     } else {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 854 */         result = compareContexts((JmsReadablePropertyContextImpl)this, (JmsReadablePropertyContextImpl)obj);
/*     */       }
/* 856 */       catch (Exception e) {
/*     */ 
/*     */         
/* 859 */         result = false;
/*     */       } 
/*     */     } 
/*     */     
/* 863 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\admin\JmsDestinationImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */