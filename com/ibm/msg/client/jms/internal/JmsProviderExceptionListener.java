/*     */ package com.ibm.msg.client.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.commonservices.workqueue.WorkQueueManager;
/*     */ import com.ibm.msg.client.provider.ProviderExceptionListener;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Vector;
/*     */ import javax.jms.ExceptionListener;
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
/*     */ class JmsProviderExceptionListener
/*     */   implements ProviderExceptionListener, Runnable
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsProviderExceptionListener.java";
/*     */   static final int STATE_IDLE = 0;
/*     */   static final int STATE_DELIVERYINPROGRESS = 1;
/*     */   static final int STATE_DELIVERYPAUSING = 2;
/*     */   static final int STATE_DELIVERYPAUSED = 3;
/*     */   
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms.internal/src/com/ibm/msg/client/jms/internal/JmsProviderExceptionListener.java");
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
/*  67 */   private int state = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean connectionBrokenExReceived = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   private Thread activeDeliveryThread = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   private ExceptionListener eListener = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   private Vector<JMSException> exceptions = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   private Object lock = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   private int asyncExceptionFlags = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmsProviderExceptionListener(int asyncExceptionFlags) {
/* 108 */     this(asyncExceptionFlags, null);
/* 109 */     if (Trace.isOn)
/* 110 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "<init>(int)", new Object[] {
/* 111 */             Integer.valueOf(asyncExceptionFlags)
/*     */           }); 
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "<init>(int)");
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
/*     */   public JmsProviderExceptionListener(int asyncExceptionFlags, ExceptionListener listener) {
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "<init>(int,ExceptionListener)", new Object[] {
/* 130 */             Integer.valueOf(asyncExceptionFlags), listener
/*     */           });
/*     */     }
/* 133 */     this.asyncExceptionFlags = asyncExceptionFlags;
/* 134 */     this.eListener = listener;
/* 135 */     this.lock = new Object();
/* 136 */     this.exceptions = new Vector<>();
/* 137 */     if (Trace.isOn) {
/* 138 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "<init>(int,ExceptionListener)");
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
/*     */   public void setExceptionListener(ExceptionListener listener) {
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "setExceptionListener(ExceptionListener)", new Object[] { listener });
/*     */     }
/*     */ 
/*     */     
/* 157 */     if (listener == this.eListener) {
/* 158 */       if (Trace.isOn) {
/* 159 */         Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "setExceptionListener(ExceptionListener)", 1);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 165 */     synchronized (this.lock) {
/*     */ 
/*     */       
/* 168 */       if (this.state != 0 && this.state != 1) {
/* 169 */         if (Trace.isOn) {
/* 170 */           Trace.traceData(this, "setExceptionListener(ExceptionListener)", "Concurrent use - ignored", null);
/*     */         }
/*     */         
/* 173 */         if (Trace.isOn) {
/* 174 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "setExceptionListener(ExceptionListener)", 2);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 184 */       if (this.state == 1 && this.activeDeliveryThread != Thread.currentThread()) {
/* 185 */         if (Trace.isOn) {
/* 186 */           Trace.traceData(this, "setExceptionListener(ExceptionListener)", "Pausing exception delivery", null);
/*     */         }
/*     */         
/* 189 */         this.state = 2;
/*     */         try {
/* 191 */           this.lock.wait(10000L);
/*     */         }
/* 193 */         catch (InterruptedException ie) {
/* 194 */           if (Trace.isOn) {
/* 195 */             Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "setExceptionListener(ExceptionListener)", ie);
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 200 */         if (this.state == 2) {
/* 201 */           if (Trace.isOn) {
/* 202 */             Trace.traceData(this, "setExceptionListener(ExceptionListener)", "Pause failed - continuing", null);
/*     */           }
/*     */           
/* 205 */           this.state = 1;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 210 */       this.eListener = listener;
/*     */ 
/*     */       
/* 213 */       if (this.state != 1) {
/* 214 */         if (this.exceptions.size() > 0) {
/* 215 */           if (Trace.isOn) {
/* 216 */             Trace.traceData(this, "setExceptionListener(ExceptionListener)", "Creating new work item to re-start delivery of remaining exceptions", null);
/*     */           }
/*     */           
/* 219 */           queueWorkItem();
/* 220 */           this.state = 1;
/*     */         } else {
/* 222 */           this.state = 0;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "setExceptionListener(ExceptionListener)", 3);
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
/*     */   public ExceptionListener getExceptionListener() {
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.data(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "getExceptionListener()", "getter", this.eListener);
/*     */     }
/*     */     
/* 246 */     return this.eListener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onException(JMSException exception, boolean connectionBroken) {
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "onException(JMSException,boolean)", new Object[] { exception, 
/*     */             
/* 259 */             Boolean.valueOf(connectionBroken) });
/*     */     }
/*     */     
/* 262 */     synchronized (this.lock) {
/*     */ 
/*     */       
/* 265 */       if (exception == null) {
/* 266 */         if (Trace.isOn) {
/* 267 */           Trace.traceData(this, "onException(JMSException, boolean)", "Null Exception", null);
/*     */         }
/* 269 */         if (Trace.isOn) {
/* 270 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "onException(JMSException,boolean)", 1);
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 276 */       if (this.eListener == null) {
/* 277 */         if (Trace.isOn) {
/* 278 */           Trace.traceData(this, "onException(JMSException, boolean)", "No listener  - Exception ignored", exception);
/*     */         }
/*     */         
/* 281 */         HashMap<String, Object> hashMap = new HashMap<>();
/* 282 */         hashMap.put("XMSC_INSERT_EXCEPTION", exception);
/* 283 */         JmsErrorUtils.log(this, "onException(JMSException, boolean)", "JMSCC3034", hashMap);
/*     */         
/* 285 */         if (Trace.isOn) {
/* 286 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "onException(JMSException,boolean)", 2);
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 292 */       if (this.connectionBrokenExReceived) {
/* 293 */         if (Trace.isOn) {
/* 294 */           Trace.traceData(this, "onException(JMSException, boolean)", "connection already broken - Exception ignored", exception);
/*     */         }
/*     */         
/* 297 */         if (Trace.isOn) {
/* 298 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "onException(JMSException,boolean)", 3);
/*     */         }
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */ 
/*     */       
/* 306 */       if (connectionBroken) {
/* 307 */         if (Trace.isOn) {
/* 308 */           Trace.traceData(this, "onException(JMSException, boolean)", "Connection broken exception received", null);
/*     */         }
/*     */         
/* 311 */         this.connectionBrokenExReceived = true;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 316 */       int exTypeFlags = -1;
/* 317 */       if (!connectionBroken) {
/* 318 */         exTypeFlags ^= 0x1;
/*     */       }
/* 320 */       if ((exTypeFlags & this.asyncExceptionFlags) == 0) {
/* 321 */         if (Trace.isOn) {
/* 322 */           Trace.traceData(this, "onException(JMSException, boolean)", "Exception type not required for listener - ignored", exception);
/*     */         }
/*     */         
/* 325 */         HashMap<String, Object> hashMap = new HashMap<>();
/* 326 */         hashMap.put("XMSC_INSERT_EXCEPTION", exception);
/* 327 */         JmsErrorUtils.log(this, "onException(JMSException, boolean)", "JMSCC3035", hashMap);
/*     */         
/* 329 */         if (Trace.isOn) {
/* 330 */           Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "onException(JMSException,boolean)", 4);
/*     */         }
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */ 
/*     */       
/* 338 */       HashMap<String, Object> inserts = new HashMap<>();
/* 339 */       inserts.put("XMSC_INSERT_EXCEPTION", exception);
/* 340 */       JmsErrorUtils.log(this, "onException(JMSException, boolean)", "JMSCC3036", inserts);
/*     */ 
/*     */       
/* 343 */       this.exceptions.add(exception);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 348 */       if (this.state == 0) {
/* 349 */         if (Trace.isOn) {
/* 350 */           Trace.traceData(this, "onException(JMSException, boolean)", "Starting new work item to deliver exception", null);
/*     */         }
/*     */         
/* 353 */         queueWorkItem();
/* 354 */         this.state = 1;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 359 */     if (Trace.isOn) {
/* 360 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "onException(JMSException,boolean)", 5);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 371 */     if (Trace.isOn) {
/* 372 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "run()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     while (true) {
/*     */       JMSException userException;
/*     */ 
/*     */       
/* 381 */       synchronized (this.lock) {
/*     */         
/* 383 */         if (this.state == 2) {
/* 384 */           this.state = 3;
/* 385 */           this.lock.notify();
/* 386 */           this.activeDeliveryThread = null; break;
/*     */         } 
/* 388 */         if (this.exceptions.size() == 0) {
/* 389 */           this.activeDeliveryThread = null;
/* 390 */           this.state = 0;
/*     */           
/*     */           break;
/*     */         } 
/* 394 */         userException = this.exceptions.remove(0);
/*     */ 
/*     */ 
/*     */         
/* 398 */         if (this.activeDeliveryThread == null) {
/* 399 */           this.activeDeliveryThread = Thread.currentThread();
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 405 */       ExceptionListener el = this.eListener;
/* 406 */       if (el != null) {
/*     */         
/*     */         try {
/*     */           
/* 410 */           if (Trace.isOn) {
/* 411 */             Trace.traceData(this, "run()", "Delivering Exception to listener", new Object[] { el });
/*     */           }
/* 413 */           el.onException(userException);
/* 414 */           if (Trace.isOn) {
/* 415 */             Trace.traceData(this, "run()", "Delivered Exception to listener", null);
/*     */           }
/*     */         }
/* 418 */         catch (Throwable e) {
/* 419 */           if (Trace.isOn) {
/* 420 */             Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "run()", e);
/*     */           }
/*     */ 
/*     */           
/* 424 */           HashMap<String, Serializable> ffstData = new HashMap<>();
/* 425 */           ffstData.put("Exception", e);
/* 426 */           ffstData.put("Message", "JMSCC1026");
/* 427 */           Trace.ffst(this, "run()", "XJ008001", ffstData, null);
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 434 */     if (Trace.isOn) {
/* 435 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "run()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void queueWorkItem() {
/* 442 */     if (Trace.isOn) {
/* 443 */       Trace.entry(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "queueWorkItem()");
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 448 */       WorkQueueManager.enqueue(this);
/*     */     }
/* 450 */     catch (Exception e) {
/* 451 */       if (Trace.isOn) {
/* 452 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "queueWorkItem()", e);
/*     */       }
/*     */ 
/*     */       
/* 456 */       HashMap<String, Serializable> ffstData = new HashMap<>();
/* 457 */       ffstData.put("Exception", e);
/* 458 */       ffstData.put("Message", "JMSCC1027");
/* 459 */       Trace.ffst(this, "onException(JMSException)", "XJ008002", ffstData, null);
/*     */     } 
/* 461 */     if (Trace.isOn)
/* 462 */       Trace.exit(this, "com.ibm.msg.client.jms.internal.JmsProviderExceptionListener", "queueWorkItem()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\internal\JmsProviderExceptionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */