/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.DumpableObject;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.DetailedMessageFormatException;
/*     */ import com.ibm.msg.client.jms.JmsConsumer;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import java.io.PrintWriter;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.JMSRuntimeException;
/*     */ import javax.jms.Message;
/*     */ import javax.jms.MessageFormatRuntimeException;
/*     */ import javax.jms.MessageListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsConsumerImpl
/*     */   extends JmsPropertyContextImpl
/*     */   implements JmsConsumer, JmsCloseableResource, DumpableObject
/*     */ {
/*     */   static final String COPYRIGHT = "IBM Confidential OCO Source Materials 5655-L82,5655-R36,5724-H72,5724-L26 (C) Copyright IBM Corp. 2016 The source code for the program is not published or otherwise divested of its trade secrets, irrespective of what has been deposited with the U.S. Copyright Office. ";
/*     */   private static final long serialVersionUID = 6890997596376469338L;
/*     */   private JmsMessageConsumerImpl consumer;
/*     */   private JmsContextImpl context;
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.msg.client.jms.internal.JmsConsumerImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsConsumerImpl.java");
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
/*     */   private boolean closed = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   JmsConsumerImpl(JmsContextImpl context, JmsMessageConsumerImpl consumer) {
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "<init>(JmsContextImpl,JmsMessageConsumerImpl)", new Object[] { context, consumer });
/*     */     }
/*     */     
/*  86 */     this.context = context;
/*  87 */     this.consumer = consumer;
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "<init>(JmsContextImpl,JmsMessageConsumerImpl)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws JMSRuntimeException {
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "close()");
/*     */     }
/*     */     
/* 104 */     if (!this.closed) {
/*     */       try {
/* 106 */         this.consumer.close();
/* 107 */         this.context.removeCloseable(this);
/*     */       }
/* 109 */       catch (JMSException e) {
/* 110 */         if (Trace.isOn) {
/* 111 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "close()", (Throwable)e);
/*     */         }
/* 113 */         JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 114 */         je.initCause((Throwable)e);
/* 115 */         if (Trace.isOn) {
/* 116 */           Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "close()", (Throwable)je);
/*     */         }
/* 118 */         throw je;
/*     */       } finally {
/*     */         
/* 121 */         if (Trace.isOn) {
/* 122 */           Trace.finallyBlock(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "close()");
/*     */         }
/* 124 */         this.closed = true;
/*     */       } 
/*     */     }
/*     */     
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "close()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageListener getMessageListener() throws JMSRuntimeException {
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "getMessageListener()");
/*     */     }
/*     */     
/*     */     try {
/* 144 */       MessageListener traceRet1 = this.consumer.getMessageListener();
/* 145 */       if (Trace.isOn) {
/* 146 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "getMessageListener()", traceRet1);
/*     */       }
/*     */       
/* 149 */       return traceRet1;
/*     */     }
/* 151 */     catch (JMSException e) {
/* 152 */       if (Trace.isOn) {
/* 153 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "getMessageListener()", (Throwable)e);
/*     */       }
/*     */       
/* 156 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 157 */       je.initCause((Throwable)e);
/* 158 */       if (Trace.isOn) {
/* 159 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "getMessageListener()", (Throwable)je);
/*     */       }
/*     */       
/* 162 */       throw je;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessageSelector() throws JMSRuntimeException {
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "getMessageSelector()");
/*     */     }
/*     */     
/*     */     try {
/* 176 */       String traceRet1 = this.consumer.getMessageSelector();
/* 177 */       if (Trace.isOn) {
/* 178 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "getMessageSelector()", traceRet1);
/*     */       }
/*     */       
/* 181 */       return traceRet1;
/*     */     }
/* 183 */     catch (JMSException e) {
/* 184 */       if (Trace.isOn) {
/* 185 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "getMessageSelector()", (Throwable)e);
/*     */       }
/*     */       
/* 188 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 189 */       je.initCause((Throwable)e);
/* 190 */       if (Trace.isOn) {
/* 191 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "getMessageSelector()", (Throwable)je);
/*     */       }
/*     */       
/* 194 */       throw je;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Message receive() throws JMSRuntimeException {
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receive()");
/*     */     }
/*     */     try {
/* 207 */       Message message = this.consumer.receive();
/* 208 */       if (Trace.isOn) {
/* 209 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receive()", message);
/*     */       }
/* 211 */       return message;
/*     */     }
/* 213 */     catch (JMSException e) {
/* 214 */       if (Trace.isOn) {
/* 215 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receive()", (Throwable)e);
/*     */       }
/* 217 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 218 */       je.initCause((Throwable)e);
/* 219 */       if (Trace.isOn) {
/* 220 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receive()", (Throwable)je);
/*     */       }
/* 222 */       throw je;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Message receive(long timeout) throws JMSRuntimeException {
/* 231 */     if (Trace.isOn)
/* 232 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receive(long)", new Object[] {
/* 233 */             Long.valueOf(timeout)
/*     */           }); 
/*     */     try {
/* 236 */       Message message = this.consumer.receive(timeout);
/* 237 */       if (Trace.isOn) {
/* 238 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receive(long)", message);
/*     */       }
/*     */       
/* 241 */       return message;
/*     */     }
/* 243 */     catch (JMSException e) {
/* 244 */       if (Trace.isOn) {
/* 245 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receive(long)", (Throwable)e);
/*     */       }
/*     */       
/* 248 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 249 */       je.initCause((Throwable)e);
/* 250 */       if (Trace.isOn) {
/* 251 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receive(long)", (Throwable)je);
/*     */       }
/*     */       
/* 254 */       throw je;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T receiveBody(Class<T> arg0) throws MessageFormatRuntimeException, JMSRuntimeException {
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receiveBody(Class<T>)", new Object[] { arg0 });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 269 */       return (T)receiveBody((Class)arg0, 0L);
/*     */     } finally {
/* 271 */       if (Trace.isOn) {
/* 272 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receiveBody(Class<T>)");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T receiveBody(Class<T> arg0, long timeout) throws MessageFormatRuntimeException, JMSRuntimeException {
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receiveBody(Class<T>,long)", new Object[] { arg0, 
/* 284 */             Long.valueOf(timeout) });
/*     */     }
/*     */     
/*     */     try {
/* 288 */       boolean globalTransactionInProgress = this.consumer.session.isInGlobalTransaction();
/*     */       
/* 290 */       int ackMode = this.consumer.session.getAcknowledgeMode();
/* 291 */       if (!globalTransactionInProgress && (ackMode == 1 || ackMode == 3)) {
/* 292 */         T traceRet1 = _receiveBodyLock(arg0, timeout);
/* 293 */         if (Trace.isOn) {
/* 294 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receiveBody(Class<T>,long)", traceRet1, 1);
/*     */         }
/*     */         
/* 297 */         return traceRet1;
/*     */       } 
/* 299 */       T traceRet2 = _receiveBody(arg0, timeout);
/* 300 */       if (Trace.isOn) {
/* 301 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receiveBody(Class<T>,long)", traceRet2, 2);
/*     */       }
/*     */       
/* 304 */       return traceRet2;
/*     */     
/*     */     }
/* 307 */     catch (JMSException e) {
/* 308 */       if (Trace.isOn) {
/* 309 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receiveBody(Class<T>,long)", (Throwable)e);
/*     */       }
/*     */       
/* 312 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 313 */       je.initCause((Throwable)e);
/* 314 */       if (Trace.isOn) {
/* 315 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receiveBody(Class<T>,long)", (Throwable)je);
/*     */       }
/*     */       
/* 318 */       throw je;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T receiveBodyNoWait(Class<T> arg0) throws MessageFormatRuntimeException, JMSRuntimeException {
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receiveBodyNoWait(Class<T>)", new Object[] { arg0 });
/*     */     }
/*     */     
/*     */     try {
/* 332 */       return (T)receiveBody((Class)arg0, -1L);
/*     */     } finally {
/* 334 */       if (Trace.isOn) {
/* 335 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receiveBodyNoWait(Class<T>)");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Message receiveNoWait() throws JMSRuntimeException {
/* 345 */     if (Trace.isOn) {
/* 346 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receiveNoWait()");
/*     */     }
/*     */     try {
/* 349 */       Message message = this.consumer.receiveNoWait();
/* 350 */       if (Trace.isOn) {
/* 351 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receiveNoWait()", message);
/*     */       }
/*     */       
/* 354 */       return message;
/*     */     }
/* 356 */     catch (JMSException e) {
/* 357 */       if (Trace.isOn) {
/* 358 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receiveNoWait()", (Throwable)e);
/*     */       }
/*     */       
/* 361 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 362 */       je.initCause((Throwable)e);
/* 363 */       if (Trace.isOn) {
/* 364 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "receiveNoWait()", (Throwable)je);
/*     */       }
/*     */       
/* 367 */       throw je;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMessageListener(MessageListener listener) throws JMSRuntimeException {
/* 376 */     if (Trace.isOn) {
/* 377 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "setMessageListener(MessageListener)", "setter", listener);
/*     */     }
/*     */     
/*     */     try {
/* 381 */       this.consumer.setMessageListener(listener);
/*     */     }
/* 383 */     catch (JMSException e) {
/* 384 */       if (Trace.isOn) {
/* 385 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "setMessageListener(MessageListener)", (Throwable)e);
/*     */       }
/*     */       
/* 388 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 389 */       je.initCause((Throwable)e);
/* 390 */       if (Trace.isOn) {
/* 391 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "setMessageListener(MessageListener)", (Throwable)je);
/*     */       }
/*     */       
/* 394 */       throw je;
/*     */     } 
/*     */   }
/*     */   
/*     */   private <T> T _receiveBody(Class<T> arg0, long timeout) {
/* 399 */     if (Trace.isOn) {
/* 400 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "_receiveBody(Class<T>,long)", new Object[] { arg0, 
/* 401 */             Long.valueOf(timeout) });
/*     */     }
/*     */     try {
/* 404 */       JmsMessageImpl message = null;
/* 405 */       if (timeout == -1L) {
/* 406 */         message = (JmsMessageImpl)this.consumer.receiveNoWait();
/*     */       } else {
/* 408 */         message = (JmsMessageImpl)this.consumer.receive(timeout);
/*     */       } 
/*     */       
/* 411 */       if (message != null) {
/* 412 */         T body = message.getBody(arg0);
/* 413 */         if (Trace.isOn) {
/* 414 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "_receiveBody(Class<T>,long)", body, 1);
/*     */         }
/*     */         
/* 417 */         return body;
/*     */       } 
/* 419 */       if (Trace.isOn) {
/* 420 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "_receiveBody(Class<T>,long)", null, 2);
/*     */       }
/*     */       
/* 423 */       return null;
/*     */     }
/* 425 */     catch (JMSException e) {
/* 426 */       if (Trace.isOn) {
/* 427 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "_receiveBody(Class<T>,long)", (Throwable)e);
/*     */       }
/*     */       
/* 430 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 431 */       je.initCause((Throwable)e);
/* 432 */       if (Trace.isOn) {
/* 433 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "_receiveBody(Class<T>,long)", (Throwable)je);
/*     */       }
/*     */       
/* 436 */       throw je;
/*     */     } 
/*     */   }
/*     */   
/*     */   private <T> T _receiveBodyLock(Class<T> arg0, long timeout) {
/* 441 */     if (Trace.isOn) {
/* 442 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "_receiveBodyLock(Class<T>,long)", new Object[] { arg0, 
/* 443 */             Long.valueOf(timeout) });
/*     */     }
/*     */     
/*     */     try {
/*     */       JmsMessageImpl message;
/* 448 */       ProviderMessage providerMessage = this.consumer.getProviderConsumer().lockMessage(timeout);
/*     */ 
/*     */       
/* 451 */       if (providerMessage != null) {
/* 452 */         message = (JmsMessageImpl)JmsMessageImpl.inboundJmsInstance(providerMessage, this.consumer.session, this.consumer.session
/*     */             
/* 454 */             .getConnectionTypeName());
/*     */       } else {
/*     */         
/* 457 */         message = null;
/*     */       } 
/*     */       
/* 460 */       if (message != null) {
/*     */         try {
/* 462 */           T body = message.getBody(arg0);
/*     */           
/* 464 */           if (body == null) {
/* 465 */             this.consumer.getProviderConsumer().unlockMessage();
/*     */ 
/*     */             
/* 468 */             DetailedMessageFormatException mfre = (DetailedMessageFormatException)JmsErrorUtils.createException("JMSCC5004", null);
/* 469 */             JMSRuntimeException ucMfre = mfre.getUnchecked();
/*     */             
/* 471 */             if (Trace.isOn) {
/* 472 */               Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "_receiveBodyLock(Class<T>,long)", (Throwable)ucMfre, 1);
/*     */             }
/*     */             
/* 475 */             throw ucMfre;
/*     */           } 
/*     */           
/* 478 */           this.consumer.getProviderConsumer().removeLockedMessage(providerMessage);
/*     */           
/* 480 */           if (Trace.isOn) {
/* 481 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "_receiveBodyLock(Class<T>,long)", body, 1);
/*     */           }
/*     */           
/* 484 */           return body;
/*     */         }
/* 486 */         catch (JMSException|ClassCastException cce) {
/* 487 */           if (Trace.isOn) {
/* 488 */             Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "_receiveBodyLock(Class<T>,long)", cce, 1);
/*     */           }
/*     */ 
/*     */           
/* 492 */           this.consumer.getProviderConsumer().unlockMessage();
/*     */           
/* 494 */           MessageFormatRuntimeException mfre = new MessageFormatRuntimeException(cce.getMessage());
/* 495 */           mfre.initCause(cce);
/* 496 */           if (Trace.isOn) {
/* 497 */             Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "_receiveBodyLock(Class<T>,long)", (Throwable)mfre, 2);
/*     */           }
/*     */           
/* 500 */           throw mfre;
/*     */         } 
/*     */       }
/* 503 */       if (Trace.isOn) {
/* 504 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "_receiveBodyLock(Class<T>,long)", null, 2);
/*     */       }
/*     */       
/* 507 */       return null;
/*     */     }
/* 509 */     catch (JMSException e) {
/* 510 */       if (Trace.isOn) {
/* 511 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "_receiveBodyLock(Class<T>,long)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 514 */       JMSRuntimeException je = JmsErrorUtils.convertJMSException(e);
/* 515 */       je.initCause((Throwable)e);
/* 516 */       if (Trace.isOn) {
/* 517 */         Trace.throwing(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "_receiveBodyLock(Class<T>,long)", (Throwable)je, 3);
/*     */       }
/*     */       
/* 520 */       throw je;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dump(PrintWriter pw, int level) {
/* 529 */     if (Trace.isOn) {
/* 530 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "dump(PrintWriter,int)", new Object[] { pw, 
/* 531 */             Integer.valueOf(level) });
/*     */     }
/* 533 */     String prefix = Trace.buildPrefix(level);
/* 534 */     pw.format("%s%s@%x (%s)%n", new Object[] { prefix, getClass().getName(), Integer.valueOf(hashCode()), (this.consumer == null) ? "<null>" : 
/* 535 */           String.format("%s@%x", new Object[] { this.consumer.getClass().getName(), Integer.valueOf(this.consumer.hashCode()) }) });
/* 536 */     if (this.consumer != null) {
/* 537 */       this.consumer.dump(pw, level + 1);
/*     */     }
/* 539 */     if (Trace.isOn)
/* 540 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsConsumerImpl", "dump(PrintWriter,int)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsConsumerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */