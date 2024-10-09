/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsQueue;
/*     */ import com.ibm.msg.client.jms.JmsQueueBrowser;
/*     */ import com.ibm.msg.client.jms.admin.JmsDestinationImpl;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.provider.ProviderQueueBrowser;
/*     */ import java.io.IOException;
/*     */ import java.io.NotSerializableException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Vector;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Message;
/*     */ import javax.jms.Queue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsQueueBrowserImpl
/*     */   extends JmsPropertyContextImpl
/*     */   implements JmsQueueBrowser
/*     */ {
/*     */   private static final long serialVersionUID = -2524264664543657143L;
/*     */   private JmsQueue queue;
/*     */   private String selector;
/*     */   private ProviderQueueBrowser providerQueueBrowser;
/*     */   private JmsSessionImpl session;
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsQueueBrowserImpl.java");
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
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.entry("com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "static()");
/*     */     }
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.data("c.i.m.c.jms.internal.JmsQueueBrowserImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsQueueBrowserImpl.java");
/*     */     }
/*     */     
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.exit("com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   private State state = new State(1);
/*     */ 
/*     */ 
/*     */   
/*     */   private JmsSessionImpl.ReentrantDoubleLock sessionSyncLock;
/*     */ 
/*     */ 
/*     */   
/*  91 */   private Vector<Enumeration<?>> enums = new Vector<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   JmsQueueBrowserImpl(JmsSessionImpl session, JmsQueue queue, String selector) throws JMSException {
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "<init>(JmsSessionImpl,JmsQueue,String)", new Object[] { session, queue, selector });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 104 */       setLongProperty("XMSC_OBJECT_IDENTITY", System.identityHashCode(this));
/*     */     }
/* 106 */     catch (JMSException e) {
/* 107 */       if (Trace.isOn) {
/* 108 */         Trace.data(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "<init>(JmsSessionImpl,JmsQueue,String)", "Caught expected exception", e);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 114 */     this.queue = queue;
/* 115 */     this.selector = selector;
/* 116 */     this.session = session;
/* 117 */     this.sessionSyncLock = session.getSessionSyncLock();
/*     */     
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "<init>(JmsSessionImpl,JmsQueue,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws JMSException {
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "close()");
/*     */     }
/* 133 */     close(false);
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "close()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close(boolean closingFromSession) throws JMSException {
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "close(boolean)", new Object[] {
/* 148 */             Boolean.valueOf(closingFromSession)
/*     */           });
/*     */     }
/* 151 */     if (!this.session.isAsync()) {
/* 152 */       this.sessionSyncLock.getExclusiveLock();
/*     */     }
/*     */     
/*     */     try {
/* 156 */       if (!this.state.close()) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 161 */         JmsDestinationImplProxy.decrementUseCount((JmsDestinationImpl)this.queue);
/*     */ 
/*     */         
/* 164 */         for (int i = 0; i < this.enums.size(); i++) {
/* 165 */           JmsEnumImpl jmsEnum = (JmsEnumImpl)this.enums.get(i);
/* 166 */           jmsEnum.close();
/*     */         } 
/*     */ 
/*     */         
/* 170 */         this.enums.clear();
/* 171 */         this.session.removeBrowser(this);
/* 172 */         this.providerQueueBrowser.close(closingFromSession);
/*     */       } 
/*     */     } finally {
/*     */       
/* 176 */       if (Trace.isOn) {
/* 177 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "close(boolean)");
/*     */       }
/*     */       
/* 180 */       if (this.sessionSyncLock.hasExclusiveLock()) {
/* 181 */         this.sessionSyncLock.unlockExclusiveLock();
/*     */       }
/*     */     } 
/*     */     
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "close(boolean)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration<Object> getEnumeration() throws JMSException {
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "getEnumeration()");
/*     */     }
/*     */ 
/*     */     
/* 200 */     this.sessionSyncLock.getExclusiveLock();
/*     */     try {
/* 202 */       this.state.checkNotClosed("JMSCC0055");
/*     */       
/* 204 */       Enumeration<Object> providerEnum = this.providerQueueBrowser.getEnumeration();
/*     */ 
/*     */       
/* 207 */       Enumeration<Object> jmsEnum = new JmsEnumImpl(providerEnum);
/*     */ 
/*     */       
/* 210 */       this.enums.add(jmsEnum);
/*     */       
/* 212 */       if (Trace.isOn) {
/* 213 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "getEnumeration()", jmsEnum);
/*     */       }
/*     */       
/* 216 */       return jmsEnum;
/*     */     } finally {
/*     */       
/* 219 */       if (Trace.isOn) {
/* 220 */         Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "getEnumeration()");
/*     */       }
/*     */       
/* 223 */       this.sessionSyncLock.unlockExclusiveLock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessageSelector() throws JMSException {
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "getMessageSelector()", "getter", this.selector);
/*     */     }
/*     */     
/* 236 */     return this.selector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Queue getQueue() throws JMSException {
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "getQueue()", "getter", this.queue);
/*     */     }
/*     */     
/* 248 */     return (Queue)this.queue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ProviderQueueBrowser getProviderBrowser() {
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "getProviderBrowser()", "getter", this.providerQueueBrowser);
/*     */     }
/*     */     
/* 259 */     return this.providerQueueBrowser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProviderBrowser(ProviderQueueBrowser providerQueueBrowser) {
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "setProviderBrowser(ProviderQueueBrowser)", "setter", providerQueueBrowser);
/*     */     }
/*     */     
/* 270 */     this.providerQueueBrowser = providerQueueBrowser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "equals(Object)", new Object[] { obj });
/*     */     }
/*     */     
/* 282 */     boolean traceRet1 = super.equals(obj);
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "equals(Object)", 
/* 285 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 287 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 295 */     if (Trace.isOn) {
/* 296 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "hashCode()");
/*     */     }
/* 298 */     int traceRet1 = super.hashCode();
/* 299 */     if (Trace.isOn) {
/* 300 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "hashCode()", 
/* 301 */           Integer.valueOf(traceRet1));
/*     */     }
/* 303 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class JmsEnumImpl
/*     */     implements Enumeration<Object>
/*     */   {
/*     */     private Enumeration<Object> providerEnum;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 321 */     private State state = new State(1);
/*     */     
/*     */     JmsEnumImpl(Enumeration<Object> providerEnum) {
/* 324 */       if (Trace.isOn) {
/* 325 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsEnumImpl", "<init>(Enumeration<Object>)", new Object[] { providerEnum });
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 331 */       this.providerEnum = providerEnum;
/*     */       
/* 333 */       if (Trace.isOn) {
/* 334 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsEnumImpl", "<init>(Enumeration<Object>)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean hasMoreElements() {
/* 345 */       if (Trace.isOn) {
/* 346 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsEnumImpl", "hasMoreElements()");
/*     */       }
/*     */       
/* 349 */       JmsQueueBrowserImpl.this.sessionSyncLock.getExclusiveLock();
/*     */ 
/*     */       
/*     */       try {
/* 353 */         if (this.state.getState() == 3 || this.state.getState() == 4) {
/*     */           
/* 355 */           if (Trace.isOn) {
/* 356 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsEnumImpl", "hasMoreElements()", 
/* 357 */                 Boolean.valueOf(false), 1);
/*     */           }
/* 359 */           return false;
/*     */         } 
/*     */         
/* 362 */         boolean more = this.providerEnum.hasMoreElements();
/* 363 */         if (Trace.isOn) {
/* 364 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsEnumImpl", "hasMoreElements()", 
/* 365 */               Boolean.valueOf(more), 2);
/*     */         }
/* 367 */         return more;
/*     */       } finally {
/*     */         
/* 370 */         if (Trace.isOn) {
/* 371 */           Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsEnumImpl", "hasMoreElements()");
/*     */         }
/*     */         
/* 374 */         JmsQueueBrowserImpl.this.sessionSyncLock.unlockExclusiveLock();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object nextElement() {
/* 384 */       if (Trace.isOn) {
/* 385 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsEnumImpl", "nextElement()");
/*     */       }
/*     */       
/* 388 */       JmsQueueBrowserImpl.this.sessionSyncLock.getExclusiveLock();
/*     */       try {
/* 390 */         Message jmsMessage = null;
/*     */         
/* 392 */         if (this.state.getState() == 3) {
/*     */ 
/*     */           
/* 395 */           IllegalStateException ise = (IllegalStateException)JmsErrorUtils.createException("JMSCC0055", null);
/* 396 */           if (Trace.isOn) {
/* 397 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsEnumImpl", "nextElement()", ise, 1);
/*     */           }
/*     */           
/* 400 */           throw ise;
/*     */         } 
/*     */         
/* 403 */         if (hasMoreElements()) {
/*     */           
/* 405 */           ProviderMessage nextProviderMessage = (ProviderMessage)this.providerEnum.nextElement();
/*     */ 
/*     */ 
/*     */           
/*     */           try {
/* 410 */             jmsMessage = JmsMessageImpl.inboundJmsInstance(nextProviderMessage, null, JmsQueueBrowserImpl.this.session
/* 411 */                 .getConnectionTypeName());
/*     */           
/*     */           }
/* 414 */           catch (JMSException jmse) {
/* 415 */             if (Trace.isOn) {
/* 416 */               Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsEnumImpl", "nextElement()", (Throwable)jmse);
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 425 */             HashMap<String, Object> inserts = new HashMap<>();
/* 426 */             inserts.put("XMSC_INSERT_EXCEPTION", jmse);
/* 427 */             JmsQueueBrowserImpl.MessageConversionFailed mcf = (JmsQueueBrowserImpl.MessageConversionFailed)JmsErrorUtils.createException("JMSCC0040", inserts);
/*     */             
/* 429 */             if (Trace.isOn) {
/* 430 */               Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsEnumImpl", "nextElement()", mcf, 2);
/*     */             }
/*     */             
/* 433 */             throw mcf;
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 438 */         if (jmsMessage == null) {
/*     */           
/* 440 */           NoSuchElementException nsee = (NoSuchElementException)JmsErrorUtils.createException("JMSCC0054", null);
/*     */           
/* 442 */           if (Trace.isOn) {
/* 443 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsEnumImpl", "nextElement()", nsee, 3);
/*     */           }
/*     */           
/* 446 */           throw nsee;
/*     */         } 
/*     */         
/* 449 */         if (Trace.isOn) {
/* 450 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsEnumImpl", "nextElement()", jmsMessage);
/*     */         }
/*     */         
/* 453 */         return jmsMessage;
/*     */       } finally {
/*     */         
/* 456 */         if (Trace.isOn) {
/* 457 */           Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsEnumImpl", "nextElement()");
/*     */         }
/*     */         
/* 460 */         JmsQueueBrowserImpl.this.sessionSyncLock.unlockExclusiveLock();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void close() throws JMSException {
/* 468 */       if (Trace.isOn) {
/* 469 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsEnumImpl", "close()");
/*     */       }
/*     */       
/* 472 */       this.state.close();
/* 473 */       if (Trace.isOn) {
/* 474 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsEnumImpl", "close()");
/*     */       }
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
/*     */   static class MessageConversionFailed
/*     */     extends RuntimeException
/*     */   {
/*     */     private static final long serialVersionUID = 5625335485005971594L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public MessageConversionFailed(String message, Throwable cause) {
/* 499 */       super(message, cause);
/* 500 */       if (Trace.isOn) {
/* 501 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.MessageConversionFailed", "<init>(String,Throwable)", new Object[] { message, cause });
/*     */       }
/*     */ 
/*     */       
/* 505 */       if (Trace.isOn) {
/* 506 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.MessageConversionFailed", "<init>(String,Throwable)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public MessageConversionFailed(Throwable cause) {
/* 517 */       super(cause);
/* 518 */       if (Trace.isOn) {
/* 519 */         Trace.entry(this, "com.ibm.msg.client.jms.internal.MessageConversionFailed", "<init>(Throwable)", new Object[] { cause });
/*     */       }
/*     */ 
/*     */       
/* 523 */       if (Trace.isOn) {
/* 524 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.MessageConversionFailed", "<init>(Throwable)");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 535 */     if (Trace.isOn) {
/* 536 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "writeObject(ObjectOutputStream)", new Object[] { out });
/*     */     }
/*     */     
/* 539 */     NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsQueueBrowser");
/*     */     
/* 541 */     if (Trace.isOn) {
/* 542 */       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "writeObject(ObjectOutputStream)", traceRet1);
/*     */     }
/*     */     
/* 545 */     throw traceRet1;
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException {
/* 549 */     if (Trace.isOn) {
/* 550 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "readObject(ObjectInputStream)", new Object[] { in });
/*     */     }
/*     */     
/* 553 */     NotSerializableException traceRet1 = new NotSerializableException("com.ibm.msg.client.jms.JmsQueueBrowser");
/*     */     
/* 555 */     if (Trace.isOn) {
/* 556 */       Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsQueueBrowserImpl", "readObject(ObjectInputStream)", traceRet1);
/*     */     }
/*     */     
/* 559 */     throw traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsQueueBrowserImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */