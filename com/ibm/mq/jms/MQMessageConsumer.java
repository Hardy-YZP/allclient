/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.jms.JMSMessage;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsMessageConsumer;
/*     */ import com.ibm.msg.client.jms.JmsPropertyContext;
/*     */ import com.ibm.msg.client.jms.JmsQueueReceiver;
/*     */ import com.ibm.msg.client.jms.JmsTopicSubscriber;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Message;
/*     */ import javax.jms.MessageConsumer;
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
/*     */ public class MQMessageConsumer
/*     */   extends MQRoot
/*     */   implements MessageConsumer, JmsMessageConsumer
/*     */ {
/*     */   static final long serialVersionUID = 1357803352856448348L;
/*     */   protected JmsMessageConsumer commonConsumer;
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.jms.MQMessageConsumer", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQMessageConsumer.java");
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
/*     */   MQMessageConsumer() {
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageConsumer", "<init>()");
/*     */     }
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageConsumer", "<init>()");
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
/*     */   public void close() throws JMSException {
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageConsumer", "close()");
/*     */     }
/*  80 */     this.commonConsumer.close();
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageConsumer", "close()");
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
/*     */   public Destination getDestination() throws JMSException {
/*  95 */     MQDestination wrapper = null;
/*  96 */     if (this.commonConsumer instanceof JmsTopicSubscriber) {
/*  97 */       wrapper = (MQTopic)((JmsTopicSubscriber)this.commonConsumer).getTopic();
/*     */     }
/*  99 */     else if (this.commonConsumer instanceof JmsQueueReceiver) {
/* 100 */       wrapper = (MQQueue)((JmsQueueReceiver)this.commonConsumer).getQueue();
/*     */     } 
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.data(this, "com.ibm.mq.jms.MQMessageConsumer", "getDestination()", "getter", wrapper);
/*     */     }
/* 105 */     return wrapper;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageListener getMessageListener() throws JMSException {
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageConsumer", "getMessageListener()");
/*     */     }
/* 119 */     FacadeMessageListener wrapper = (FacadeMessageListener)this.commonConsumer.getMessageListener();
/*     */ 
/*     */ 
/*     */     
/* 123 */     if (wrapper == null) {
/* 124 */       if (Trace.isOn) {
/* 125 */         Trace.exit(this, "com.ibm.mq.jms.MQMessageConsumer", "getMessageListener()", null, 1);
/*     */       }
/* 127 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     MessageListener listener = wrapper.listener;
/*     */     
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageConsumer", "getMessageListener()", listener, 2);
/*     */     }
/* 138 */     return listener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessageSelector() throws JMSException {
/* 148 */     String traceRet1 = this.commonConsumer.getMessageSelector();
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.data(this, "com.ibm.mq.jms.MQMessageConsumer", "getMessageSelector()", "getter", traceRet1);
/*     */     }
/*     */     
/* 153 */     return traceRet1;
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
/*     */   public Message receive() throws JMSException {
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageConsumer", "receive()");
/*     */     }
/* 179 */     Message commonMessage = this.commonConsumer.receive();
/* 180 */     JMSMessage jMSMessage = MQSession.MessageProxy.wrapMessage(commonMessage);
/*     */     
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageConsumer", "receive()", jMSMessage);
/*     */     }
/* 185 */     return (Message)jMSMessage;
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
/*     */   public Message receive(long timeout) throws JMSException {
/* 205 */     if (Trace.isOn)
/* 206 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageConsumer", "receive(long)", new Object[] {
/* 207 */             Long.valueOf(timeout)
/*     */           }); 
/* 209 */     Message commonMessage = this.commonConsumer.receive(timeout);
/* 210 */     JMSMessage jMSMessage = MQSession.MessageProxy.wrapMessage(commonMessage);
/*     */     
/* 212 */     if (Trace.isOn) {
/* 213 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageConsumer", "receive(long)", jMSMessage);
/*     */     }
/* 215 */     return (Message)jMSMessage;
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
/*     */   public Message receiveNoWait() throws JMSException {
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.entry(this, "com.ibm.mq.jms.MQMessageConsumer", "receiveNoWait()");
/*     */     }
/* 231 */     Message commonMessage = this.commonConsumer.receiveNoWait();
/* 232 */     JMSMessage jMSMessage = MQSession.MessageProxy.wrapMessage(commonMessage);
/*     */     
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit(this, "com.ibm.mq.jms.MQMessageConsumer", "receiveNoWait()", jMSMessage);
/*     */     }
/* 237 */     return (Message)jMSMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   void setDelegate(JmsMessageConsumer commonConsumer) {
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.data(this, "com.ibm.mq.jms.MQMessageConsumer", "setDelegate(JmsMessageConsumer)", "setter", commonConsumer);
/*     */     }
/*     */     
/* 246 */     this.commonConsumer = commonConsumer;
/* 247 */     this.delegate = (JmsPropertyContext)commonConsumer;
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
/*     */   public void setMessageListener(MessageListener listener) throws JMSException {
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.data(this, "com.ibm.mq.jms.MQMessageConsumer", "setMessageListener(MessageListener)", "setter", listener);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 265 */     FacadeMessageListener wrapper = null;
/* 266 */     if (listener != null) {
/* 267 */       wrapper = new FacadeMessageListener(listener);
/*     */     }
/* 269 */     this.commonConsumer.setMessageListener(wrapper);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getNoLocal() throws JMSException {
/* 279 */     boolean traceRet1 = this.commonConsumer.getNoLocal();
/* 280 */     if (Trace.isOn) {
/* 281 */       Trace.data(this, "com.ibm.mq.jms.MQMessageConsumer", "getNoLocal()", "getter", 
/* 282 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 284 */     return traceRet1;
/*     */   }
/*     */   
/*     */   private static class FacadeMessageListener
/*     */     implements MessageListener {
/* 289 */     private MessageListener listener = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public FacadeMessageListener(MessageListener listener) {
/* 298 */       if (Trace.isOn) {
/* 299 */         Trace.entry(this, "com.ibm.mq.jms.FacadeMessageListener", "<init>(MessageListener)", new Object[] { listener });
/*     */       }
/*     */       
/* 302 */       this.listener = listener;
/* 303 */       if (Trace.isOn) {
/* 304 */         Trace.exit(this, "com.ibm.mq.jms.FacadeMessageListener", "<init>(MessageListener)");
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
/*     */     public void onMessage(Message message) {
/* 316 */       if (Trace.isOn) {
/* 317 */         Trace.entry(this, "com.ibm.mq.jms.FacadeMessageListener", "onMessage(Message)", new Object[] { message });
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 322 */       JMSMessage wrapper = MQSession.MessageProxy.wrapMessage(message);
/*     */       
/* 324 */       this.listener.onMessage((Message)wrapper);
/* 325 */       if (Trace.isOn)
/* 326 */         Trace.exit(this, "com.ibm.mq.jms.FacadeMessageListener", "onMessage(Message)"); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQMessageConsumer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */