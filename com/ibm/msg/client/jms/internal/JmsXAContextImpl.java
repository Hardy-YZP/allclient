/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.jms.JmsXAContext;
/*     */ import java.io.Serializable;
/*     */ import javax.jms.BytesMessage;
/*     */ import javax.jms.Connection;
/*     */ import javax.jms.ConnectionMetaData;
/*     */ import javax.jms.Destination;
/*     */ import javax.jms.ExceptionListener;
/*     */ import javax.jms.JMSConsumer;
/*     */ import javax.jms.JMSContext;
/*     */ import javax.jms.JMSProducer;
/*     */ import javax.jms.MapMessage;
/*     */ import javax.jms.Message;
/*     */ import javax.jms.ObjectMessage;
/*     */ import javax.jms.Queue;
/*     */ import javax.jms.QueueBrowser;
/*     */ import javax.jms.StreamMessage;
/*     */ import javax.jms.TemporaryQueue;
/*     */ import javax.jms.TemporaryTopic;
/*     */ import javax.jms.TextMessage;
/*     */ import javax.jms.Topic;
/*     */ import javax.transaction.xa.XAResource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsXAContextImpl
/*     */   extends JmsContextImpl
/*     */   implements JmsXAContext
/*     */ {
/*     */   private static final long serialVersionUID = -1403498418221437782L;
/*     */   
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.msg.client.jms.internal.JmsXAContextImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsXAContextImpl.java");
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
/*     */   public JmsXAContextImpl(Connection connection) {
/*  69 */     this(connection, 1);
/*  70 */     if (Trace.isOn) {
/*  71 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "<init>(Connection)", new Object[] { connection });
/*     */     }
/*     */     
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "<init>(Connection)");
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
/*     */   public JmsXAContextImpl(Connection connection, int sessionMode) {
/*  87 */     super(connection, null);
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "<init>(Connection,int)", new Object[] { connection, 
/*  90 */             Integer.valueOf(sessionMode) });
/*     */     }
/*     */     
/*  93 */     JmsContextImpl.SessionWrapper uninitializedSession = new JmsContextImpl.SessionWrapper(connection, sessionMode);
/*     */ 
/*     */     
/*  96 */     setSession(uninitializedSession);
/*     */     
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "<init>(Connection,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JMSContext getContext() {
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "getContext()");
/*     */     }
/*     */     
/* 114 */     JMSContext context = new JMSContext()
/*     */       {
/*     */         public void acknowledge()
/*     */         {
/* 118 */           if (Trace.isOn) {
/* 119 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "acknowledge()");
/*     */           }
/* 121 */           JmsXAContextImpl.this.acknowledge();
/* 122 */           if (Trace.isOn) {
/* 123 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "acknowledge()");
/*     */           }
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void close() {
/* 130 */           if (Trace.isOn) {
/* 131 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "close()");
/*     */           }
/* 133 */           JmsXAContextImpl.this.close();
/* 134 */           if (Trace.isOn) {
/* 135 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "close()");
/*     */           }
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void commit() {
/* 142 */           if (Trace.isOn) {
/* 143 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "commit()");
/*     */           }
/* 145 */           JmsXAContextImpl.this.commit();
/* 146 */           if (Trace.isOn) {
/* 147 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "commit()");
/*     */           }
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public QueueBrowser createBrowser(Queue arg0) {
/* 154 */           if (Trace.isOn) {
/* 155 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createBrowser(Queue)", new Object[] { arg0 });
/*     */           }
/*     */           
/* 158 */           QueueBrowser traceRet1 = JmsXAContextImpl.this.createBrowser(arg0);
/* 159 */           if (Trace.isOn) {
/* 160 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createBrowser(Queue)", traceRet1);
/*     */           }
/*     */           
/* 163 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public QueueBrowser createBrowser(Queue arg0, String arg1) {
/* 168 */           if (Trace.isOn) {
/* 169 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createBrowser(Queue,String)", new Object[] { arg0, arg1 });
/*     */           }
/*     */           
/* 172 */           QueueBrowser traceRet1 = JmsXAContextImpl.this.createBrowser(arg0, arg1);
/* 173 */           if (Trace.isOn) {
/* 174 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createBrowser(Queue,String)", traceRet1);
/*     */           }
/*     */           
/* 177 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public BytesMessage createBytesMessage() {
/* 182 */           if (Trace.isOn) {
/* 183 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createBytesMessage()");
/*     */           }
/*     */           
/* 186 */           BytesMessage traceRet1 = JmsXAContextImpl.this.createBytesMessage();
/* 187 */           if (Trace.isOn) {
/* 188 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createBytesMessage()", traceRet1);
/*     */           }
/*     */           
/* 191 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public JMSConsumer createConsumer(Destination arg0) {
/* 196 */           if (Trace.isOn) {
/* 197 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createConsumer(Destination)", new Object[] { arg0 });
/*     */           }
/*     */ 
/*     */           
/* 201 */           JMSConsumer traceRet1 = JmsXAContextImpl.this.createConsumer(arg0);
/* 202 */           if (Trace.isOn) {
/* 203 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createConsumer(Destination)", traceRet1);
/*     */           }
/*     */           
/* 206 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public JMSConsumer createConsumer(Destination arg0, String arg1) {
/* 211 */           if (Trace.isOn) {
/* 212 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createConsumer(Destination,String)", new Object[] { arg0, arg1 });
/*     */           }
/*     */ 
/*     */           
/* 216 */           JMSConsumer traceRet1 = JmsXAContextImpl.this.createConsumer(arg0, arg1);
/* 217 */           if (Trace.isOn) {
/* 218 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createConsumer(Destination,String)", traceRet1);
/*     */           }
/*     */           
/* 221 */           return traceRet1;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public JMSConsumer createConsumer(Destination arg0, String arg1, boolean arg2) {
/* 227 */           if (Trace.isOn) {
/* 228 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createConsumer(Destination,String,boolean)", new Object[] { arg0, arg1, 
/*     */                   
/* 230 */                   Boolean.valueOf(arg2) });
/*     */           }
/* 232 */           JMSConsumer traceRet1 = JmsXAContextImpl.this.createConsumer(arg0, arg1, arg2);
/* 233 */           if (Trace.isOn) {
/* 234 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createConsumer(Destination,String,boolean)", traceRet1);
/*     */           }
/*     */           
/* 237 */           return traceRet1;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public JMSContext createContext(int arg0) {
/* 243 */           if (Trace.isOn)
/* 244 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createContext(int)", new Object[] {
/* 245 */                   Integer.valueOf(arg0)
/*     */                 }); 
/* 247 */           JMSContext traceRet1 = JmsXAContextImpl.this.createContext(arg0);
/* 248 */           if (Trace.isOn) {
/* 249 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createContext(int)", traceRet1);
/*     */           }
/*     */           
/* 252 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public JMSConsumer createDurableConsumer(Topic arg0, String arg1) {
/* 257 */           if (Trace.isOn) {
/* 258 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createDurableConsumer(Topic,String)", new Object[] { arg0, arg1 });
/*     */           }
/*     */           
/* 261 */           JMSConsumer traceRet1 = JmsXAContextImpl.this.createDurableConsumer(arg0, arg1);
/* 262 */           if (Trace.isOn) {
/* 263 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createDurableConsumer(Topic,String)", traceRet1);
/*     */           }
/*     */           
/* 266 */           return traceRet1;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public JMSConsumer createDurableConsumer(Topic arg0, String arg1, String arg2, boolean arg3) {
/* 272 */           if (Trace.isOn) {
/* 273 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createDurableConsumer(Topic,String,String,boolean)", new Object[] { arg0, arg1, arg2, 
/*     */                   
/* 275 */                   Boolean.valueOf(arg3) });
/*     */           }
/*     */           
/* 278 */           JMSConsumer traceRet1 = JmsXAContextImpl.this.createDurableConsumer(arg0, arg1, arg2, arg3);
/* 279 */           if (Trace.isOn) {
/* 280 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createDurableConsumer(Topic,String,String,boolean)", traceRet1);
/*     */           }
/*     */           
/* 283 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public MapMessage createMapMessage() {
/* 288 */           if (Trace.isOn) {
/* 289 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createMapMessage()");
/*     */           }
/*     */ 
/*     */           
/* 293 */           MapMessage traceRet1 = JmsXAContextImpl.this.createMapMessage();
/* 294 */           if (Trace.isOn) {
/* 295 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createMapMessage()", traceRet1);
/*     */           }
/*     */           
/* 298 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public Message createMessage() {
/* 303 */           if (Trace.isOn) {
/* 304 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createMessage()");
/*     */           }
/*     */ 
/*     */           
/* 308 */           Message traceRet1 = JmsXAContextImpl.this.createMessage();
/* 309 */           if (Trace.isOn) {
/* 310 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createMessage()", traceRet1);
/*     */           }
/* 312 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public ObjectMessage createObjectMessage() {
/* 317 */           if (Trace.isOn) {
/* 318 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createObjectMessage()");
/*     */           }
/*     */ 
/*     */           
/* 322 */           ObjectMessage traceRet1 = JmsXAContextImpl.this.createObjectMessage();
/* 323 */           if (Trace.isOn) {
/* 324 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createObjectMessage()", traceRet1);
/*     */           }
/*     */           
/* 327 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public ObjectMessage createObjectMessage(Serializable arg0) {
/* 332 */           if (Trace.isOn) {
/* 333 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createObjectMessage(Serializable)", new Object[] { arg0 });
/*     */           }
/*     */ 
/*     */           
/* 337 */           ObjectMessage traceRet1 = JmsXAContextImpl.this.createObjectMessage(arg0);
/* 338 */           if (Trace.isOn) {
/* 339 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createObjectMessage(Serializable)", traceRet1);
/*     */           }
/*     */           
/* 342 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public JMSProducer createProducer() {
/* 347 */           if (Trace.isOn) {
/* 348 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createProducer()");
/*     */           }
/*     */ 
/*     */           
/* 352 */           JMSProducer traceRet1 = JmsXAContextImpl.this.createProducer();
/* 353 */           if (Trace.isOn) {
/* 354 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createProducer()", traceRet1);
/*     */           }
/* 356 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public Queue createQueue(String arg0) {
/* 361 */           if (Trace.isOn) {
/* 362 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createQueue(String)", new Object[] { arg0 });
/*     */           }
/*     */ 
/*     */           
/* 366 */           Queue traceRet1 = JmsXAContextImpl.this.createQueue(arg0);
/* 367 */           if (Trace.isOn) {
/* 368 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createQueue(String)", traceRet1);
/*     */           }
/*     */           
/* 371 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public JMSConsumer createSharedConsumer(Topic arg0, String arg1) {
/* 376 */           if (Trace.isOn) {
/* 377 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createSharedConsumer(Topic,String)", new Object[] { arg0, arg1 });
/*     */           }
/*     */ 
/*     */           
/* 381 */           JMSConsumer traceRet1 = JmsXAContextImpl.this.createSharedConsumer(arg0, arg1);
/* 382 */           if (Trace.isOn) {
/* 383 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSharedConsumer(Topic,String)", traceRet1);
/*     */           }
/*     */           
/* 386 */           return traceRet1;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public JMSConsumer createSharedConsumer(Topic arg0, String arg1, String arg2) {
/* 392 */           if (Trace.isOn) {
/* 393 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createSharedConsumer(Topic,String,String)", new Object[] { arg0, arg1, arg2 });
/*     */           }
/*     */ 
/*     */           
/* 397 */           JMSConsumer traceRet1 = JmsXAContextImpl.this.createSharedConsumer(arg0, arg1, arg2);
/* 398 */           if (Trace.isOn) {
/* 399 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSharedConsumer(Topic,String,String)", traceRet1);
/*     */           }
/*     */           
/* 402 */           return traceRet1;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public JMSConsumer createSharedDurableConsumer(Topic arg0, String arg1) {
/* 408 */           if (Trace.isOn) {
/* 409 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createSharedDurableConsumer(Topic,String)", new Object[] { arg0, arg1 });
/*     */           }
/*     */ 
/*     */           
/* 413 */           JMSConsumer traceRet1 = JmsXAContextImpl.this.createSharedDurableConsumer(arg0, arg1);
/* 414 */           if (Trace.isOn) {
/* 415 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSharedDurableConsumer(Topic,String)", traceRet1);
/*     */           }
/*     */           
/* 418 */           return traceRet1;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public JMSConsumer createSharedDurableConsumer(Topic arg0, String arg1, String arg2) {
/* 424 */           if (Trace.isOn) {
/* 425 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createSharedDurableConsumer(Topic,String,String)", new Object[] { arg0, arg1, arg2 });
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 430 */           JMSConsumer traceRet1 = JmsXAContextImpl.this.createSharedDurableConsumer(arg0, arg1, arg2);
/* 431 */           if (Trace.isOn) {
/* 432 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createSharedDurableConsumer(Topic,String,String)", traceRet1);
/*     */           }
/*     */           
/* 435 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public StreamMessage createStreamMessage() {
/* 440 */           if (Trace.isOn) {
/* 441 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createStreamMessage()");
/*     */           }
/*     */ 
/*     */           
/* 445 */           StreamMessage traceRet1 = JmsXAContextImpl.this.createStreamMessage();
/* 446 */           if (Trace.isOn) {
/* 447 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createStreamMessage()", traceRet1);
/*     */           }
/*     */           
/* 450 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public TemporaryQueue createTemporaryQueue() {
/* 455 */           if (Trace.isOn) {
/* 456 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createTemporaryQueue()");
/*     */           }
/*     */ 
/*     */           
/* 460 */           TemporaryQueue traceRet1 = JmsXAContextImpl.this.createTemporaryQueue();
/* 461 */           if (Trace.isOn) {
/* 462 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTemporaryQueue()", traceRet1);
/*     */           }
/*     */           
/* 465 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public TemporaryTopic createTemporaryTopic() {
/* 470 */           if (Trace.isOn) {
/* 471 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createTemporaryTopic()");
/*     */           }
/*     */ 
/*     */           
/* 475 */           TemporaryTopic traceRet1 = JmsXAContextImpl.this.createTemporaryTopic();
/* 476 */           if (Trace.isOn) {
/* 477 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTemporaryTopic()", traceRet1);
/*     */           }
/*     */           
/* 480 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public TextMessage createTextMessage() {
/* 485 */           if (Trace.isOn) {
/* 486 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createTextMessage()");
/*     */           }
/*     */ 
/*     */           
/* 490 */           TextMessage traceRet1 = JmsXAContextImpl.this.createTextMessage();
/* 491 */           if (Trace.isOn) {
/* 492 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTextMessage()", traceRet1);
/*     */           }
/*     */           
/* 495 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public TextMessage createTextMessage(String arg0) {
/* 500 */           if (Trace.isOn) {
/* 501 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createTextMessage(String)", new Object[] { arg0 });
/*     */           }
/*     */ 
/*     */           
/* 505 */           TextMessage traceRet1 = JmsXAContextImpl.this.createTextMessage(arg0);
/* 506 */           if (Trace.isOn) {
/* 507 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTextMessage(String)", traceRet1);
/*     */           }
/*     */           
/* 510 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public Topic createTopic(String arg0) {
/* 515 */           if (Trace.isOn) {
/* 516 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "createTopic(String)", new Object[] { arg0 });
/*     */           }
/*     */ 
/*     */           
/* 520 */           Topic traceRet1 = JmsXAContextImpl.this.createTopic(arg0);
/* 521 */           if (Trace.isOn) {
/* 522 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "createTopic(String)", traceRet1);
/*     */           }
/*     */           
/* 525 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean getAutoStart() {
/* 530 */           if (Trace.isOn) {
/* 531 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "getAutoStart()");
/*     */           }
/*     */           
/* 534 */           boolean traceRet1 = JmsXAContextImpl.this.getAutoStart();
/* 535 */           if (Trace.isOn) {
/* 536 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getAutoStart()", 
/* 537 */                 Boolean.valueOf(traceRet1));
/*     */           }
/* 539 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public String getClientID() {
/* 544 */           if (Trace.isOn) {
/* 545 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "getClientID()");
/*     */           }
/*     */           
/* 548 */           String traceRet1 = JmsXAContextImpl.this.getClientID();
/* 549 */           if (Trace.isOn) {
/* 550 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getClientID()", traceRet1);
/*     */           }
/* 552 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public ExceptionListener getExceptionListener() {
/* 557 */           if (Trace.isOn) {
/* 558 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "getExceptionListener()");
/*     */           }
/*     */ 
/*     */           
/* 562 */           ExceptionListener traceRet1 = JmsXAContextImpl.this.getExceptionListener();
/* 563 */           if (Trace.isOn) {
/* 564 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getExceptionListener()", traceRet1);
/*     */           }
/*     */           
/* 567 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public ConnectionMetaData getMetaData() {
/* 572 */           if (Trace.isOn) {
/* 573 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "getMetaData()");
/*     */           }
/*     */           
/* 576 */           ConnectionMetaData traceRet1 = JmsXAContextImpl.this.getMetaData();
/* 577 */           if (Trace.isOn) {
/* 578 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getMetaData()", traceRet1);
/*     */           }
/* 580 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public int getSessionMode() {
/* 585 */           if (Trace.isOn) {
/* 586 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "getSessionMode()");
/*     */           }
/*     */ 
/*     */           
/* 590 */           int traceRet1 = JmsXAContextImpl.this.getSessionMode();
/* 591 */           if (Trace.isOn) {
/* 592 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getSessionMode()", 
/* 593 */                 Integer.valueOf(traceRet1));
/*     */           }
/* 595 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean getTransacted() {
/* 600 */           if (Trace.isOn) {
/* 601 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "getTransacted()");
/*     */           }
/*     */ 
/*     */           
/* 605 */           boolean traceRet1 = JmsXAContextImpl.this.getTransacted();
/* 606 */           if (Trace.isOn) {
/* 607 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "getTransacted()", 
/* 608 */                 Boolean.valueOf(traceRet1));
/*     */           }
/* 610 */           return traceRet1;
/*     */         }
/*     */ 
/*     */         
/*     */         public void recover() {
/* 615 */           if (Trace.isOn) {
/* 616 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "recover()");
/*     */           }
/*     */           
/* 619 */           JmsXAContextImpl.this.recover();
/* 620 */           if (Trace.isOn) {
/* 621 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "recover()");
/*     */           }
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void rollback() {
/* 628 */           if (Trace.isOn) {
/* 629 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "rollback()");
/*     */           }
/*     */           
/* 632 */           JmsXAContextImpl.this.rollback();
/* 633 */           if (Trace.isOn) {
/* 634 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "rollback()");
/*     */           }
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void setAutoStart(boolean arg0) {
/* 641 */           if (Trace.isOn) {
/* 642 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "setAutoStart(boolean)", new Object[] {
/* 643 */                   Boolean.valueOf(arg0)
/*     */                 });
/*     */           }
/* 646 */           JmsXAContextImpl.this.setAutoStart(arg0);
/* 647 */           if (Trace.isOn) {
/* 648 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setAutoStart(boolean)");
/*     */           }
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void setClientID(String arg0) {
/* 655 */           if (Trace.isOn) {
/* 656 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "setClientID(String)", new Object[] { arg0 });
/*     */           }
/*     */ 
/*     */           
/* 660 */           JmsXAContextImpl.this.setClientID(arg0);
/* 661 */           if (Trace.isOn) {
/* 662 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setClientID(String)");
/*     */           }
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void setExceptionListener(ExceptionListener arg0) {
/* 669 */           if (Trace.isOn) {
/* 670 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "setExceptionListener(ExceptionListener)", new Object[] { arg0 });
/*     */           }
/*     */ 
/*     */           
/* 674 */           JmsXAContextImpl.this.setExceptionListener(arg0);
/* 675 */           if (Trace.isOn) {
/* 676 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "setExceptionListener(ExceptionListener)");
/*     */           }
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public void start() {
/* 684 */           if (Trace.isOn) {
/* 685 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "start()");
/*     */           }
/*     */           
/* 688 */           JmsXAContextImpl.this.start();
/* 689 */           if (Trace.isOn) {
/* 690 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "start()");
/*     */           }
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void stop() {
/* 697 */           if (Trace.isOn) {
/* 698 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "stop()");
/*     */           }
/*     */           
/* 701 */           JmsXAContextImpl.this.stop();
/* 702 */           if (Trace.isOn) {
/* 703 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "stop()");
/*     */           }
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void unsubscribe(String arg0) {
/* 710 */           if (Trace.isOn) {
/* 711 */             Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "unsubscribe(String)", new Object[] { arg0 });
/*     */           }
/*     */ 
/*     */           
/* 715 */           JmsXAContextImpl.this.unsubscribe(arg0);
/* 716 */           if (Trace.isOn) {
/* 717 */             Trace.exit(this, "com.ibm.msg.client.jms.internal.null", "unsubscribe(String)");
/*     */           }
/*     */         }
/*     */       };
/*     */ 
/*     */     
/* 723 */     if (Trace.isOn) {
/* 724 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "getContext()", context);
/*     */     }
/*     */     
/* 727 */     return context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XAResource getXAResource() {
/* 737 */     XAResource xar = this.session.getXAResource();
/* 738 */     if (Trace.isOn) {
/* 739 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsXAContextImpl", "getXAResource()", "getter", xar);
/*     */     }
/*     */     
/* 742 */     return xar;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsXAContextImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */