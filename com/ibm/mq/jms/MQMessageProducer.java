/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.jms.JMSMessage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsDestination;
/*     */ import com.ibm.msg.client.jms.JmsMessageProducer;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import javax.jms.CompletionListener;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.InvalidDestinationException;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Message;
/*     */ import javax.jms.MessageFormatException;
/*     */ import javax.jms.MessageProducer;
/*     */ import javax.jms.TemporaryQueue;
/*     */ import javax.jms.TemporaryTopic;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQMessageProducer
/*     */   extends MQRoot
/*     */   implements MessageProducer, JmsMessageProducer
/*     */ {
/*     */   private static final long serialVersionUID = 5533585627918234225L;
/*     */   protected JmsMessageProducer commonProducer;
/*     */   
/*     */   static {
/*  47 */     if (Trace.isOn) {
/*  48 */       Trace.data("com.ibm.mq.jms.MQMessageProducer", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQMessageProducer.java");
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
/*     */   MQMessageProducer() {
/*  61 */     if (Trace.isOn) {
/*  62 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageProducer", "<init>()");
/*     */     }
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageProducer", "<init>()");
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
/*     */   public void close() throws JMSException {
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageProducer", "close()");
/*     */     }
/*  82 */     this.commonProducer.close();
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageProducer", "close()");
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
/*     */   public int getDeliveryMode() throws JMSException {
/*  99 */     int traceRet1 = this.commonProducer.getDeliveryMode();
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.data(this, "com.ibm.mq.jms.MQMessageProducer", "getDeliveryMode()", "getter", 
/* 102 */           Integer.valueOf(traceRet1));
/*     */     }
/* 104 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Destination getDestination() throws JMSException {
/* 115 */     Destination traceRet1 = null;
/* 116 */     Destination dest = this.commonProducer.getDestination();
/*     */ 
/*     */     
/* 119 */     if (dest instanceof TemporaryQueue) {
/* 120 */       traceRet1 = new MQTemporaryQueue((TemporaryQueue)dest);
/*     */     }
/* 122 */     else if (dest instanceof TemporaryTopic) {
/* 123 */       traceRet1 = new MQTemporaryTopic((TemporaryTopic)dest);
/*     */     } else {
/*     */       
/* 126 */       traceRet1 = dest;
/*     */     } 
/*     */     
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.data(this, "com.ibm.mq.jms.MQMessageProducer", "getDestination()", "getter", traceRet1);
/*     */     }
/*     */     
/* 133 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDisableMessageID() throws JMSException {
/* 144 */     boolean traceRet1 = this.commonProducer.getDisableMessageID();
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.data(this, "com.ibm.mq.jms.MQMessageProducer", "getDisableMessageID()", "getter", 
/* 147 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 149 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDisableMessageTimestamp() throws JMSException {
/* 160 */     boolean traceRet1 = this.commonProducer.getDisableMessageTimestamp();
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.data(this, "com.ibm.mq.jms.MQMessageProducer", "getDisableMessageTimestamp()", "getter", 
/* 163 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 165 */     return traceRet1;
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
/*     */   public int getPriority() throws JMSException {
/* 177 */     int traceRet1 = this.commonProducer.getPriority();
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.data(this, "com.ibm.mq.jms.MQMessageProducer", "getPriority()", "getter", 
/* 180 */           Integer.valueOf(traceRet1));
/*     */     }
/* 182 */     return traceRet1;
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
/*     */   public long getTimeToLive() throws JMSException {
/* 196 */     long traceRet1 = this.commonProducer.getTimeToLive();
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.data(this, "com.ibm.mq.jms.MQMessageProducer", "getTimeToLive()", "getter", 
/* 199 */           Long.valueOf(traceRet1));
/*     */     }
/* 201 */     return traceRet1;
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
/*     */   public void send(Destination destination, Message message) throws JMSException {
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageProducer", "send(Destination,Message)", new Object[] { destination, message });
/*     */     }
/*     */     
/* 223 */     JmsDestination dest = (destination == null) ? null : (JmsDestination)((MQDestination)destination).validateDestination();
/* 224 */     Message msg = message;
/* 225 */     if (message instanceof JMSMessage)
/*     */     {
/* 227 */       msg = ((JMSMessage)message).getDelegate();
/*     */     }
/* 229 */     this.commonProducer.send((Destination)dest, msg);
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageProducer", "send(Destination,Message)");
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
/*     */   public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageProducer", "send(Destination,Message,int,int,long)", new Object[] { destination, message, 
/*     */             
/* 258 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), Long.valueOf(timeToLive) });
/*     */     }
/* 260 */     JmsDestination dest = (destination == null) ? null : (JmsDestination)((MQDestination)destination).validateDestination();
/* 261 */     Message msg = message;
/* 262 */     if (message instanceof JMSMessage)
/*     */     {
/* 264 */       msg = ((JMSMessage)message).getDelegate();
/*     */     }
/* 266 */     this.commonProducer.send((Destination)dest, msg, deliveryMode, priority, timeToLive);
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageProducer", "send(Destination,Message,int,int,long)");
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
/*     */   public void send(Message message) throws JMSException {
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageProducer", "send(Message)", new Object[] { message });
/*     */     }
/*     */ 
/*     */     
/* 288 */     Message msg = message;
/* 289 */     if (message instanceof JMSMessage)
/*     */     {
/* 291 */       msg = ((JMSMessage)message).getDelegate();
/*     */     }
/* 293 */     this.commonProducer.send(msg);
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageProducer", "send(Message)");
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
/*     */   public void send(Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
/* 311 */     if (Trace.isOn) {
/* 312 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageProducer", "send(Message,int,int,long)", new Object[] { message, 
/* 313 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), 
/* 314 */             Long.valueOf(timeToLive) });
/*     */     }
/* 316 */     Message msg = message;
/* 317 */     if (message instanceof JMSMessage)
/*     */     {
/* 319 */       msg = ((JMSMessage)message).getDelegate();
/*     */     }
/* 321 */     this.commonProducer.send(msg, deliveryMode, priority, timeToLive);
/* 322 */     if (Trace.isOn) {
/* 323 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageProducer", "send(Message,int,int,long)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setDelegate(JmsMessageProducer commonProducer) {
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.data(this, "com.ibm.mq.jms.MQMessageProducer", "setDelegate(JmsMessageProducer)", "setter", commonProducer);
/*     */     }
/*     */     
/* 334 */     this.commonProducer = commonProducer;
/* 335 */     this.delegate = (JmsPropertyContext)commonProducer;
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
/*     */   public void setDeliveryMode(int deliveryMode) throws JMSException {
/* 352 */     if (Trace.isOn) {
/* 353 */       Trace.data(this, "com.ibm.mq.jms.MQMessageProducer", "setDeliveryMode(int)", "setter", 
/* 354 */           Integer.valueOf(deliveryMode));
/*     */     }
/* 356 */     this.commonProducer.setDeliveryMode(deliveryMode);
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
/*     */   public void setDisableMessageID(boolean value) throws JMSException {
/* 378 */     if (Trace.isOn) {
/* 379 */       Trace.data(this, "com.ibm.mq.jms.MQMessageProducer", "setDisableMessageID(boolean)", "setter", 
/* 380 */           Boolean.valueOf(value));
/*     */     }
/* 382 */     this.commonProducer.setDisableMessageID(value);
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
/*     */   public void setDisableMessageTimestamp(boolean value) throws JMSException {
/* 394 */     if (Trace.isOn) {
/* 395 */       Trace.data(this, "com.ibm.mq.jms.MQMessageProducer", "setDisableMessageTimestamp(boolean)", "setter", 
/* 396 */           Boolean.valueOf(value));
/*     */     }
/* 398 */     this.commonProducer.setDisableMessageTimestamp(value);
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
/*     */   public void setPriority(int priority) throws JMSException {
/* 415 */     if (Trace.isOn) {
/* 416 */       Trace.data(this, "com.ibm.mq.jms.MQMessageProducer", "setPriority(int)", "setter", 
/* 417 */           Integer.valueOf(priority));
/*     */     }
/* 419 */     this.commonProducer.setPriority(priority);
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
/*     */   public void setTimeToLive(long timeToLive) throws JMSException {
/* 436 */     if (Trace.isOn) {
/* 437 */       Trace.data(this, "com.ibm.mq.jms.MQMessageProducer", "setTimeToLive(long)", "setter", 
/* 438 */           Long.valueOf(timeToLive));
/*     */     }
/* 440 */     this.commonProducer.setTimeToLive(timeToLive);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getDeliveryDelay() throws JMSException {
/* 451 */     long deliveryDelay = this.commonProducer.getDeliveryDelay();
/*     */     
/* 453 */     if (Trace.isOn) {
/* 454 */       Trace.data(this, "com.ibm.mq.jms.MQMessageProducer", "getDeliveryDelay()", "getter", 
/* 455 */           Long.valueOf(deliveryDelay));
/*     */     }
/* 457 */     return deliveryDelay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void send(Message message, CompletionListener completionListener) throws JMSException, MessageFormatException, InvalidDestinationException, IllegalArgumentException, UnsupportedOperationException {
/* 465 */     if (Trace.isOn) {
/* 466 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageProducer", "send(Message,CompletionListener)", new Object[] { message, completionListener });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 471 */     Message msg = message;
/* 472 */     if (message instanceof JMSMessage)
/*     */     {
/* 474 */       msg = ((JMSMessage)message).getDelegate();
/*     */     }
/*     */     
/* 477 */     CompletionListener wrapper = null;
/* 478 */     if (completionListener != null) {
/* 479 */       wrapper = new FacadeCompletionListener(completionListener);
/*     */     }
/*     */     
/* 482 */     this.commonProducer.send(msg, wrapper);
/* 483 */     if (Trace.isOn) {
/* 484 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageProducer", "send(Message,CompletionListener)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void send(Destination destination, Message message, CompletionListener completionListener) throws JMSException, MessageFormatException, InvalidDestinationException, IllegalArgumentException, UnsupportedOperationException {
/* 495 */     if (Trace.isOn) {
/* 496 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageProducer", "send(Destination,Message,CompletionListener)", new Object[] { destination, message, completionListener });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 501 */     JmsDestination dest = (destination == null) ? null : (JmsDestination)((MQDestination)destination).validateDestination();
/* 502 */     Message msg = message;
/* 503 */     if (message instanceof JMSMessage)
/*     */     {
/* 505 */       msg = ((JMSMessage)message).getDelegate();
/*     */     }
/*     */     
/* 508 */     CompletionListener wrapper = null;
/* 509 */     if (completionListener != null) {
/* 510 */       wrapper = new FacadeCompletionListener(completionListener);
/*     */     }
/*     */     
/* 513 */     this.commonProducer.send((Destination)dest, msg, wrapper);
/* 514 */     if (Trace.isOn) {
/* 515 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageProducer", "send(Destination,Message,CompletionListener)");
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
/*     */   public void send(Message message, int deliveryMode, int priority, long timeToLive, CompletionListener completionListener) throws JMSException, MessageFormatException, InvalidDestinationException, IllegalArgumentException, UnsupportedOperationException {
/* 527 */     if (Trace.isOn) {
/* 528 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageProducer", "send(Message,int,int,long,CompletionListener)", new Object[] { message, 
/*     */             
/* 530 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), Long.valueOf(timeToLive), completionListener });
/*     */     }
/*     */ 
/*     */     
/* 534 */     Message msg = message;
/* 535 */     if (message instanceof JMSMessage)
/*     */     {
/* 537 */       msg = ((JMSMessage)message).getDelegate();
/*     */     }
/*     */     
/* 540 */     CompletionListener wrapper = null;
/* 541 */     if (completionListener != null) {
/* 542 */       wrapper = new FacadeCompletionListener(completionListener);
/*     */     }
/*     */     
/* 545 */     this.commonProducer.send(msg, deliveryMode, priority, timeToLive, wrapper);
/* 546 */     if (Trace.isOn) {
/* 547 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageProducer", "send(Message,int,int,long,CompletionListener)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive, CompletionListener completionListener) throws JMSException, MessageFormatException, InvalidDestinationException, IllegalArgumentException, UnsupportedOperationException {
/* 558 */     if (Trace.isOn) {
/* 559 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageProducer", "send(Destination,Message,int,int,long,CompletionListener)", new Object[] { destination, message, 
/*     */             
/* 561 */             Integer.valueOf(deliveryMode), Integer.valueOf(priority), Long.valueOf(timeToLive), completionListener });
/*     */     }
/*     */ 
/*     */     
/* 565 */     JmsDestination dest = (destination == null) ? null : (JmsDestination)((MQDestination)destination).validateDestination();
/* 566 */     Message msg = message;
/* 567 */     if (message instanceof JMSMessage)
/*     */     {
/* 569 */       msg = ((JMSMessage)message).getDelegate();
/*     */     }
/*     */     
/* 572 */     CompletionListener wrapper = null;
/* 573 */     if (completionListener != null) {
/* 574 */       wrapper = new FacadeCompletionListener(completionListener);
/*     */     }
/*     */     
/* 577 */     this.commonProducer.send((Destination)dest, msg, deliveryMode, priority, timeToLive, wrapper);
/* 578 */     if (Trace.isOn) {
/* 579 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageProducer", "send(Destination,Message,int,int,long,CompletionListener)");
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
/*     */   public void setDeliveryDelay(long deliveryDelay) throws JMSException {
/* 591 */     if (Trace.isOn) {
/* 592 */       Trace.data(this, "com.ibm.mq.jms.MQMessageProducer", "setDeliveryDelay(long)", "setter", 
/* 593 */           Long.valueOf(deliveryDelay));
/*     */     }
/* 595 */     this.commonProducer.setDeliveryDelay(deliveryDelay);
/*     */   }
/*     */   
/*     */   private static class FacadeCompletionListener
/*     */     implements CompletionListener {
/* 600 */     private CompletionListener listener = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public FacadeCompletionListener(CompletionListener listener) {
/* 608 */       if (Trace.isOn) {
/* 609 */         Trace.entry(this, "com.ibm.mq.jms.FacadeCompletionListener", "<init>(CompletionListener)", new Object[] { listener });
/*     */       }
/*     */ 
/*     */       
/* 613 */       this.listener = listener;
/*     */       
/* 615 */       if (Trace.isOn) {
/* 616 */         Trace.exit(this, "com.ibm.mq.jms.FacadeCompletionListener", "<init>(CompletionListener)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void onCompletion(Message message) {
/* 623 */       if (Trace.isOn) {
/* 624 */         Trace.entry(this, "com.ibm.mq.jms.FacadeCompletionListener", "onCompletion(Message)", new Object[] { message });
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 629 */       JMSMessage wrapper = MQSession.MessageProxy.wrapMessage(message);
/*     */       
/* 631 */       this.listener.onCompletion((Message)wrapper);
/*     */       
/* 633 */       if (Trace.isOn) {
/* 634 */         Trace.exit(this, "com.ibm.mq.jms.FacadeCompletionListener", "onCompletion(Message)");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void onException(Message message, Exception exception) {
/* 641 */       if (Trace.isOn) {
/* 642 */         Trace.entry(this, "com.ibm.mq.jms.FacadeCompletionListener", "onException(Message,Exception)", new Object[] { message, exception });
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 647 */       JMSMessage wrapper = MQSession.MessageProxy.wrapMessage(message);
/*     */       
/* 649 */       this.listener.onException((Message)wrapper, exception);
/*     */       
/* 651 */       if (Trace.isOn)
/* 652 */         Trace.exit(this, "com.ibm.mq.jms.FacadeCompletionListener", "onException(Message,Exception)"); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQMessageProducer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */