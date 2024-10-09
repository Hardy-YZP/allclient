/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderDestination;
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
/*     */ 
/*     */ 
/*     */ class MQQueueAgentThread1Impl
/*     */   extends MQQueueAgentThread
/*     */ {
/*     */   static {
/*  46 */     if (Trace.isOn) {
/*  47 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQQueueAgentThread1Impl.java");
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
/*  62 */   private long EOQTimeout = 3000L;
/*  63 */   private static long sweepInterval = 30000L;
/*     */ 
/*     */   
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQQueueAgentThread1Impl.java";
/*     */ 
/*     */   
/*  69 */   private long eoqTimeoutStart = 0L;
/*     */   
/*  71 */   private int headerDataSize = -1;
/*  72 */   private int messageBufferSize = 0;
/*  73 */   private int messageMQMDandRFH2Size = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final int sizeofMQMDv2 = 364;
/*     */ 
/*     */ 
/*     */   
/*  82 */   private MQMessageGroup seenLastSweep = new MQMessageGroup();
/*  83 */   private MQMessageGroup seenThisSweep = new MQMessageGroup();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MQQueueAgentThread1Impl(MQQueueAgent mqQueueAgent, MQConnection jmsConnection, String qmgrName, String qName) throws JMSException {
/*  90 */     super(mqQueueAgent, jmsConnection, qmgrName, qName);
/*  91 */     if (Trace.isOn) {
/*  92 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "<init>(MQQueueAgent,MQConnection,String,String)", new Object[] { mqQueueAgent, jmsConnection, qmgrName, qName });
/*     */     }
/*     */ 
/*     */     
/*  96 */     this.EOQTimeout = jmsConnection.getEoqTimeout();
/*  97 */     this.headerDataSize = jmsConnection.getHeaderDataSize();
/*  98 */     setSweepInterval(jmsConnection.getSweepTime());
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.traceData(this, "MQQueueAgentThread1Impl: headerDataSize= " + this.headerDataSize + " sweepInterval: " + sweepInterval, null);
/*     */     }
/*     */     
/* 103 */     this.options = 8362;
/*     */ 
/*     */     
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "<init>(MQQueueAgent,MQConnection,String,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static synchronized void setSweepInterval(long sweepInterval) {
/* 116 */     MQQueueAgentThread1Impl.sweepInterval = sweepInterval;
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
/*     */   MQMessageReference browse(int dataQuantity, long wait) throws JMSException {
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "browse(int,long)", new Object[] {
/* 131 */             Integer.valueOf(dataQuantity), Long.valueOf(wait)
/*     */           });
/*     */     }
/* 134 */     MQMessageReference deliverMR = null;
/* 135 */     long currentTime = 0L;
/*     */     
/*     */     try {
/* 138 */       boolean browseFirst = false;
/* 139 */       if (this.startSweep) {
/* 140 */         browseFirst = true;
/* 141 */         currentTime = System.currentTimeMillis();
/* 142 */         this.sweepStartedTime = System.currentTimeMillis();
/* 143 */         this.startSweep = false;
/* 144 */         this.sweepTimeoutStart = 0L;
/* 145 */         this.eoqTimeoutStart = -1L;
/* 146 */         MQMessageGroup t = this.seenLastSweep;
/* 147 */         this.seenLastSweep = this.seenThisSweep;
/* 148 */         this.seenThisSweep = t;
/* 149 */         this.seenThisSweep.clear();
/*     */       } 
/*     */       
/* 152 */       boolean done = false;
/* 153 */       while (!done) {
/*     */         
/* 155 */         deliverMR = browseMsg(dataQuantity, browseFirst, 0);
/* 156 */         browseFirst = false;
/*     */         
/* 158 */         if (deliverMR != null) {
/*     */ 
/*     */           
/* 161 */           deliverMR = makeSuitableForDelivery(dataQuantity, deliverMR);
/* 162 */           if (deliverMR != null) {
/* 163 */             done = true;
/*     */           }
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 169 */         done = true;
/*     */       } 
/*     */ 
/*     */       
/* 173 */       if (deliverMR == null) {
/*     */         
/* 175 */         deliverNow();
/*     */ 
/*     */ 
/*     */         
/* 179 */         long waitStart = System.currentTimeMillis();
/* 180 */         currentTime = waitStart;
/* 181 */         done = false;
/*     */         
/* 183 */         if (this.EOQTimeout != -1L) {
/* 184 */           if (this.eoqTimeoutStart == -1L) {
/* 185 */             this.eoqTimeoutStart = waitStart;
/* 186 */           } else if (waitStart - this.eoqTimeoutStart >= this.EOQTimeout) {
/*     */ 
/*     */             
/* 189 */             done = true;
/* 190 */             this.startSweep = true;
/*     */           } 
/*     */         }
/*     */         
/* 194 */         while (!done) {
/*     */ 
/*     */ 
/*     */           
/* 198 */           int timeout = (int)(wait - currentTime - waitStart);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 203 */           if (this.EOQTimeout != -1L && timeout > this.EOQTimeout) {
/* 204 */             timeout = (int)this.EOQTimeout;
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 211 */           if (timeout < 1000) {
/* 212 */             timeout = 1000;
/*     */           }
/*     */           
/* 215 */           deliverMR = browseMsg(dataQuantity, false, timeout);
/*     */           
/* 217 */           if (deliverMR != null) {
/* 218 */             deliverMR = makeSuitableForDelivery(dataQuantity, deliverMR);
/* 219 */             if (deliverMR != null) {
/* 220 */               done = true;
/*     */               continue;
/*     */             } 
/* 223 */             deliverMR = null;
/* 224 */             currentTime = System.currentTimeMillis();
/* 225 */             if (wait - currentTime - waitStart <= 0L) {
/* 226 */               done = true;
/*     */             }
/*     */             
/*     */             continue;
/*     */           } 
/* 231 */           done = true;
/* 232 */           deliverMR = null;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 237 */       if (deliverMR == null) {
/*     */ 
/*     */         
/* 240 */         deliverNow();
/* 241 */         this.startSweep = true;
/* 242 */       } else if (this.sweepTimeoutStart != 0L) {
/*     */ 
/*     */         
/* 245 */         if (currentTime == 0L) {
/* 246 */           currentTime = System.currentTimeMillis();
/*     */         }
/* 248 */         if (currentTime > this.sweepTimeoutStart + 30000L) {
/* 249 */           deliverNow();
/* 250 */           this.startSweep = true;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 255 */         if (currentTime == 0L) {
/* 256 */           currentTime = System.currentTimeMillis();
/*     */         }
/* 258 */         this.sweepTimeoutStart = currentTime;
/*     */       } 
/*     */       
/* 261 */       if (Trace.isOn) {
/* 262 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "browse(int,long)", deliverMR);
/*     */       }
/*     */       
/* 265 */       return deliverMR;
/*     */     }
/* 267 */     catch (MQException mqe) {
/* 268 */       if (Trace.isOn) {
/* 269 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "browse(int,long)", (Throwable)mqe, 1);
/*     */       }
/*     */ 
/*     */       
/* 273 */       JMSException je = ConfigEnvironment.newException("MQJMS1025");
/* 274 */       je.setLinkedException((Exception)mqe);
/* 275 */       if (Trace.isOn) {
/* 276 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "browse(int,long)", (Throwable)je, 1);
/*     */       }
/*     */       
/* 279 */       throw je;
/*     */     }
/* 281 */     catch (JMSException je) {
/* 282 */       if (Trace.isOn) {
/* 283 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "browse(int,long)", (Throwable)je, 2);
/*     */       }
/*     */ 
/*     */       
/* 287 */       if (Trace.isOn) {
/* 288 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "browse(int,long)", (Throwable)je, 2);
/*     */       }
/*     */       
/* 291 */       throw je;
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
/*     */   private MQMessageReference browseMsg(int dataQuantity, boolean browseFirst, int timeout) throws MQException {
/* 305 */     if (Trace.isOn)
/* 306 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "browseMsg(int,boolean,int)", new Object[] {
/* 307 */             Integer.valueOf(dataQuantity), 
/* 308 */             Boolean.valueOf(browseFirst), Integer.valueOf(timeout)
/*     */           }); 
/* 310 */     MQMessageReference outMR = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 316 */       if (browseFirst) {
/* 317 */         this.mqGMO.options &= 0xFFFFFFDF;
/* 318 */         this.mqGMO.options |= 0x10;
/*     */       } else {
/* 320 */         this.mqGMO.options &= 0xFFFFFFEF;
/* 321 */         this.mqGMO.options |= 0x20;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 327 */       boolean useBatching = (dataQuantity == 0);
/*     */       
/* 329 */       if (dataQuantity == 1 && 
/* 330 */         this.headerDataSize != -1) {
/* 331 */         this.messageBufferSize = this.headerDataSize;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 336 */       if (timeout == 0) {
/* 337 */         this.mqGMO.options &= 0xFFFFFFFE;
/*     */       } else {
/* 339 */         this.mqGMO.options |= 0x1;
/* 340 */         this.mqGMO.waitInterval = timeout;
/*     */       } 
/*     */ 
/*     */       
/* 344 */       MQJMSMessage message = new MQJMSMessage();
/*     */ 
/*     */       
/* 347 */       if (Trace.isOn) {
/* 348 */         Trace.traceData(this, "About to browse message:", null);
/* 349 */         Trace.traceData(this, ((this.mqGMO.options & 0x20) == 0) ? "- browsing first message" : "- browsing next message", null);
/* 350 */         Trace.traceData(this, ((this.mqGMO.options & 0x1) == 0) ? "- browsing without waiting" : ("- browsing with wait for " + this.mqGMO.waitInterval), null);
/* 351 */         Trace.traceData(this, useBatching ? "- using batched-get" : "- using non-batched get", null);
/*     */       } 
/*     */ 
/*     */       
/* 355 */       int reason = 0;
/*     */       
/* 357 */       switch (dataQuantity) {
/*     */         case 0:
/* 359 */           if (Trace.isOn) {
/* 360 */             Trace.traceData(this, "- browsing with NO_DATA", null);
/*     */           }
/* 362 */           this.mqGMO.options |= 0x40;
/* 363 */           if (Trace.isOn) {
/* 364 */             Trace.traceData(this, "- browsing with truncation", null);
/*     */           }
/* 366 */           reason = this.mqQueue.spiBatchedGetNoExc(message, this.mqGMO, 0);
/* 367 */           if (reason == 2079) {
/* 368 */             reason = 0;
/*     */           }
/*     */           break;
/*     */         case 1:
/* 372 */           if (Trace.isOn) {
/* 373 */             Trace.traceData(this, "- browsing with HEADER_DATA", null);
/*     */           }
/* 375 */           reason = getMessageHeaderOnly(message);
/*     */           break;
/*     */         case 2:
/* 378 */           if (Trace.isOn) {
/* 379 */             Trace.traceData(this, "- browsing with FULL_DATA", null);
/*     */           }
/* 381 */           this.mqGMO.options &= 0xFFFFFFBF;
/* 382 */           if (Trace.isOn) {
/* 383 */             Trace.traceData(this, "- browsing with fulle message", null);
/*     */           }
/*     */           
/* 386 */           reason = this.mqQueue.getMsg2NoExc(message, this.mqGMO);
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 392 */       if (reason == 2033) {
/*     */         
/* 394 */         if (Trace.isOn) {
/* 395 */           Trace.traceData(this, "MQRC_NO_MSG_AVAILABLE", null);
/*     */         }
/* 397 */         if (Trace.isOn) {
/* 398 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "browseMsg(int,boolean,int)", null, 1);
/*     */         }
/*     */         
/* 401 */         return null;
/* 402 */       }  if (reason != 0) {
/*     */         
/* 404 */         if (Trace.isOn) {
/* 405 */           Trace.traceData(this, "Unexpected reason code " + reason, null);
/*     */         }
/* 407 */         MQException traceRet1 = new MQException(2, reason, this);
/* 408 */         if (Trace.isOn) {
/* 409 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "browseMsg(int,boolean,int)", (Throwable)traceRet1, 1);
/*     */         }
/*     */ 
/*     */         
/* 413 */         throw traceRet1;
/*     */       } 
/*     */       
/* 416 */       if (Trace.isOn) {
/* 417 */         Trace.traceData(this, "Browsed message: length= " + message.getMessageDataLength() + " dataQuantityHint= " + dataQuantity, null);
/*     */       }
/* 419 */       outMR = new MQMessageReference(this.jmsSession);
/*     */       
/* 421 */       outMR.setDestination((ProviderDestination)this.mqQueueAgent.getProviderDestination());
/* 422 */       outMR.setMQJMSMessage(message, dataQuantity, this.messageMQMDandRFH2Size);
/*     */ 
/*     */       
/* 425 */       if (Trace.isOn) {
/* 426 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "browseMsg(int,boolean,int)", outMR, 2);
/*     */       }
/*     */       
/* 429 */       return outMR;
/*     */     }
/* 431 */     catch (MQException mqe) {
/* 432 */       if (Trace.isOn) {
/* 433 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "browseMsg(int,boolean,int)", (Throwable)mqe);
/*     */       }
/*     */ 
/*     */       
/* 437 */       if (Trace.isOn) {
/* 438 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "browseMsg(int,boolean,int)", (Throwable)mqe, 2);
/*     */       }
/*     */       
/* 441 */       throw mqe;
/*     */     } 
/*     */   }
/*     */   
/*     */   long getEOQTimeout() {
/* 446 */     if (Trace.isOn) {
/* 447 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "getEOQTimeout()", "getter", 
/* 448 */           Long.valueOf(this.EOQTimeout));
/*     */     }
/* 450 */     return this.EOQTimeout;
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
/*     */   private int getMessageHeaderOnly(MQJMSMessage message) {
/* 462 */     if (Trace.isOn) {
/* 463 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "getMessageHeaderOnly(MQJMSMessage)", new Object[] { message });
/*     */     }
/*     */     
/* 466 */     if (Trace.isOn) {
/* 467 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "getMessageHeaderOnly(MQJMSMessage)", new Object[] { message });
/*     */     }
/*     */     
/* 470 */     this.mqGMO.options |= 0x40;
/* 471 */     if (Trace.isOn) {
/* 472 */       Trace.traceData(this, "- browsing with truncation", null);
/*     */     }
/*     */     
/* 475 */     int reason = 0;
/*     */     
/* 477 */     int truncatedBufferSize = 400;
/* 478 */     if (400 < this.messageBufferSize) {
/* 479 */       truncatedBufferSize = this.messageBufferSize;
/*     */     }
/*     */     
/* 482 */     reason = this.mqQueue.getMsg2NoExc(message, this.mqGMO, truncatedBufferSize);
/* 483 */     if (reason == 2033) {
/* 484 */       if (Trace.isOn) {
/* 485 */         Trace.traceData(this, "getMessageHeaderOnly - First browse. No message available ", null);
/* 486 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "getMessageHeaderOnly(MQJMSMessage)", 1);
/*     */       } 
/* 488 */       if (Trace.isOn) {
/* 489 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "getMessageHeaderOnly(MQJMSMessage)", 
/* 490 */             Integer.valueOf(reason), 1);
/*     */       }
/* 492 */       return reason;
/*     */     } 
/* 494 */     if (Trace.isOn) {
/* 495 */       Trace.traceData(this, "Message: ", message.toString());
/*     */     }
/*     */     
/* 498 */     if (reason == 2079) {
/* 499 */       reason = 0;
/*     */     }
/*     */     
/* 502 */     long format = 0L;
/*     */     try {
/* 504 */       format = message.getFormatAsLong();
/* 505 */       if (format != 5571313732236222496L && format != -3109514705028104128L) {
/* 506 */         if (Trace.isOn) {
/* 507 */           Trace.traceData(this, "getMessageHeaderOnly - Received message does not have RFH2", null);
/* 508 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "getMessageHeaderOnly(MQJMSMessage)", 2);
/*     */         } 
/* 510 */         if (Trace.isOn) {
/* 511 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "getMessageHeaderOnly(MQJMSMessage)", 
/* 512 */               Integer.valueOf(reason), 2);
/*     */         }
/* 514 */         return reason;
/*     */       } 
/*     */       
/* 517 */       if (Trace.isOn) {
/* 518 */         Trace.traceData(this, "Message: ", message.toString());
/*     */       }
/* 520 */       int msgEncoding = 0;
/* 521 */       int strucLength = 0;
/*     */       
/* 523 */       msgEncoding = message.getEncoding();
/*     */       
/* 525 */       message.skipReadingBytes(8);
/*     */       
/* 527 */       strucLength = message.readInt(msgEncoding);
/*     */       
/* 529 */       this.messageMQMDandRFH2Size = strucLength + 364;
/* 530 */       if (Trace.isOn) {
/* 531 */         Trace.traceData(this, "MQMD + RFH2 length = " + this.messageMQMDandRFH2Size, null);
/*     */       }
/*     */     }
/* 534 */     catch (Exception e) {
/* 535 */       if (Trace.isOn) {
/* 536 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "getMessageHeaderOnly(MQJMSMessage)", e);
/*     */       }
/*     */ 
/*     */       
/* 540 */       reason = 2033;
/* 541 */       if (Trace.isOn) {
/* 542 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "getMessageHeaderOnly(MQJMSMessage)", e);
/*     */       }
/*     */     } 
/* 545 */     if (Trace.isOn) {
/* 546 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "getMessageHeaderOnly(MQJMSMessage)", 3);
/*     */     }
/* 548 */     if (Trace.isOn) {
/* 549 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "getMessageHeaderOnly(MQJMSMessage)", 
/* 550 */           Integer.valueOf(reason), 3);
/*     */     }
/* 552 */     return reason;
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
/*     */   private MQMessageReference makeSuitableForDelivery(int dataQuantity, MQMessageReference msgRef) throws MQException, JMSException {
/* 567 */     if (Trace.isOn) {
/* 568 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "makeSuitableForDelivery(int,MQMessageReference)", new Object[] {
/*     */             
/* 570 */             Integer.valueOf(dataQuantity), msgRef
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 576 */     MQJMSMessage tmpMessage = null;
/*     */     
/*     */     try {
/* 579 */       MQMessageReference outMR, oldMR = this.seenLastSweep.removeMessage(msgRef);
/* 580 */       boolean suitable = false;
/*     */       
/* 582 */       if (oldMR == null) {
/*     */         
/* 584 */         if (Trace.isOn) {
/* 585 */           Trace.traceData(this, "New message", null);
/*     */         }
/* 587 */         outMR = msgRef;
/* 588 */         msgRef.setBrowseTime(this.sweepStartedTime);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 593 */         tmpMessage = msgRef.getMQJMSMessage();
/* 594 */         MQMessageReference tmpMR = (MQMessageReference)msgRef.clone();
/*     */         
/* 596 */         this.seenThisSweep.addMessage(tmpMR);
/* 597 */         suitable = true;
/*     */       } else {
/*     */         
/* 600 */         if (Trace.isOn) {
/* 601 */           Trace.traceData(this, "Previously seen message", null);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 606 */         int oldBackoutCount = oldMR.getBackoutCount();
/* 607 */         int newBackoutCount = msgRef.getBackoutCount();
/* 608 */         boolean backoutCountChanged = (newBackoutCount != oldBackoutCount);
/*     */ 
/*     */ 
/*     */         
/* 612 */         int oldQuantity = oldMR.getDataQuantity();
/* 613 */         int newQuantity = msgRef.getDataQuantity();
/*     */         
/* 615 */         if ((oldQuantity == newQuantity && null != oldMR.getMQJMSMessage()) || (oldQuantity == 1 && newQuantity == 0) || oldQuantity == 2) {
/*     */ 
/*     */           
/* 618 */           if (Trace.isOn) {
/* 619 */             Trace.traceData(this, "using old MessageReference.", null);
/*     */           }
/*     */           
/* 622 */           outMR = oldMR;
/*     */ 
/*     */ 
/*     */           
/* 626 */           if (backoutCountChanged) {
/* 627 */             outMR.setBackoutCount(newBackoutCount);
/*     */           }
/*     */         } else {
/* 630 */           if (Trace.isOn) {
/* 631 */             Trace.traceData(this, "using new MessageReference.", null);
/*     */           }
/* 633 */           msgRef.setBrowseTime(oldMR.getBrowseTime());
/* 634 */           outMR = msgRef;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 640 */         MQMessageReference tmpMR = (MQMessageReference)outMR.clone();
/* 641 */         this.seenThisSweep.addMessage(tmpMR);
/*     */ 
/*     */         
/* 644 */         if (oldMR.isWanted() && (backoutCountChanged || oldMR.getBrowseTime() + 60000L <= this.sweepStartedTime)) {
/* 645 */           suitable = true;
/* 646 */           outMR.setBrowseTime(this.sweepStartedTime);
/*     */         } else {
/* 648 */           outMR = null;
/*     */         } 
/*     */       } 
/*     */       
/* 652 */       if (Trace.isOn) {
/* 653 */         if (suitable) {
/* 654 */           Trace.traceData(this, "ProviderMessage is suitable for delivery", null);
/*     */         } else {
/* 656 */           Trace.traceData(this, "ProviderMessage not suitable for delivery", null);
/*     */         } 
/*     */       }
/*     */       
/* 660 */       if (suitable)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 668 */         if (dataQuantity != 0 && outMR.getDataQuantity() != 2) {
/*     */ 
/*     */ 
/*     */           
/* 672 */           MQJMSMessage message = outMR.getMQJMSMessage();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 678 */           if (null == message) {
/* 679 */             if (null != tmpMessage) {
/* 680 */               message = tmpMessage;
/*     */             }
/*     */             else {
/*     */               
/* 684 */               message = new MQJMSMessage();
/* 685 */               message.setMessageId(outMR.getMessageId());
/* 686 */               message.setCorrelationId(outMR.getCorrelId());
/*     */             } 
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 700 */           if (this.messageMQMDandRFH2Size > message.getMessageDataLength()) {
/* 701 */             if (Trace.isOn) {
/* 702 */               Trace.traceData(this, "messageMQMDandRFH2Size is greater than message length: " + message.getMessageDataLength(), null);
/*     */             }
/* 704 */             if (Trace.isOn) {
/* 705 */               Trace.traceData(this, "Browsing message with HEADER_DATA : " + outMR.getHeaderLength(), null);
/*     */             }
/* 707 */             this.mqGMO.options = 80;
/*     */             
/* 709 */             this.mqGMO.matchOptions = 3;
/*     */             
/* 711 */             int reason = this.mqQueue.getMsg2NoExc(message, this.mqGMO, this.messageMQMDandRFH2Size);
/*     */             
/* 713 */             this.mqGMO.matchOptions = 0;
/* 714 */             if (reason == 2079) {
/* 715 */               reason = 0;
/* 716 */               if (Trace.isOn) {
/* 717 */                 Trace.traceData(this, "Using truncated message.", null);
/*     */               }
/*     */             } 
/* 720 */             if (reason == 0) {
/*     */               
/* 722 */               outMR.setMQJMSMessage(message, 1, this.messageMQMDandRFH2Size);
/* 723 */             } else if (reason == 2033) {
/*     */               
/* 725 */               if (Trace.isOn) {
/* 726 */                 Trace.traceData(this, "MQRC_NO_MSG_AVAILABLE: message has been removed from queue", null);
/*     */               }
/* 728 */               outMR = null;
/*     */             } else {
/*     */               
/* 731 */               MQException traceRet1 = new MQException(2, reason, this);
/* 732 */               if (Trace.isOn) {
/* 733 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "makeSuitableForDelivery(int,MQMessageReference)", (Throwable)traceRet1, 1);
/*     */               }
/*     */ 
/*     */               
/* 737 */               throw traceRet1;
/*     */             } 
/*     */           } else {
/* 740 */             if (Trace.isOn) {
/* 741 */               Trace.traceData(this, "messageMQMDandRFH2Size is not greater than message length: " + message.getMessageDataLength(), null);
/*     */             }
/*     */             
/* 744 */             outMR.setMQJMSMessage(message, 1, this.messageMQMDandRFH2Size);
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 749 */       if (Trace.isOn) {
/* 750 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "makeSuitableForDelivery(int,MQMessageReference)", outMR);
/*     */       }
/*     */       
/* 753 */       return outMR;
/*     */     }
/* 755 */     catch (MQException mqe) {
/* 756 */       if (Trace.isOn) {
/* 757 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "makeSuitableForDelivery(int,MQMessageReference)", (Throwable)mqe, 1);
/*     */       }
/*     */ 
/*     */       
/* 761 */       if (Trace.isOn) {
/* 762 */         Trace.traceData(this, "makeSuitableForDelivery.", null);
/*     */       }
/* 764 */       if (Trace.isOn) {
/* 765 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "makeSuitableForDelivery(int,MQMessageReference)", (Throwable)mqe, 2);
/*     */       }
/*     */       
/* 768 */       throw mqe;
/*     */     }
/* 770 */     catch (JMSException je) {
/* 771 */       if (Trace.isOn) {
/* 772 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "makeSuitableForDelivery(int,MQMessageReference)", (Throwable)je, 2);
/*     */       }
/*     */ 
/*     */       
/* 776 */       if (Trace.isOn) {
/* 777 */         Trace.traceData(this, "makeSuitableForDelivery.", null);
/*     */       }
/* 779 */       if (Trace.isOn) {
/* 780 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "makeSuitableForDelivery(int,MQMessageReference)", (Throwable)je, 3);
/*     */       }
/*     */       
/* 783 */       throw je;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void setEOQTimeout(long newTimeout) {
/* 789 */     if (Trace.isOn) {
/* 790 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "setEOQTimeout(long)", "setter", 
/* 791 */           Long.valueOf(newTimeout));
/*     */     }
/* 793 */     if ((newTimeout < -2L || newTimeout > 30000L) && 
/* 794 */       Trace.isOn) {
/* 795 */       Trace.traceData(this, "timeout being set to possible invalid value: " + newTimeout, null);
/*     */     }
/*     */     
/* 798 */     this.EOQTimeout = newTimeout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean updateConfig() throws JMSException {
/* 809 */     if (Trace.isOn) {
/* 810 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "updateConfig()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 815 */     if (this.startSweep) {
/*     */ 
/*     */ 
/*     */       
/* 819 */       this.seenThisSweep.clear();
/* 820 */       if (Trace.isOn) {
/* 821 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "updateConfig()", 
/* 822 */             Boolean.valueOf(true), 1);
/*     */       }
/* 824 */       return true;
/*     */     } 
/*     */     
/* 827 */     if (Trace.isOn) {
/* 828 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread1Impl", "updateConfig()", 
/* 829 */           Boolean.valueOf(false), 2);
/*     */     }
/* 831 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQQueueAgentThread1Impl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */