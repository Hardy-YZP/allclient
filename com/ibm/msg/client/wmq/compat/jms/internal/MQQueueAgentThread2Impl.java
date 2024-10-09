/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderDestination;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MQQueueAgentThread2Impl
/*     */   extends MQQueueAgentThread
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQQueueAgentThread2Impl.java";
/*     */   
/*     */   static {
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQQueueAgentThread2Impl.java");
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
/*  78 */   private static long sweepInterval = 30000L;
/*  79 */   private int headerDataSize = -1;
/*  80 */   private int messageBufferSize = 0;
/*  81 */   private int messageMQMDandRFH2Size = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final int sizeofMQMDv2 = 364;
/*     */ 
/*     */   
/*     */   private MQMessageReference storedMsgReference;
/*     */ 
/*     */   
/*  91 */   private static int defaultHeaderDataLength = 5000;
/*     */ 
/*     */   
/*     */   MQQueueAgentThread2Impl(MQQueueAgent mqQueueAgent, MQConnection jmsConnection, String qmgrName, String qName) throws JMSException {
/*  95 */     super(mqQueueAgent, jmsConnection, qmgrName, qName);
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "<init>(MQQueueAgent,MQConnection,String,String)", new Object[] { mqQueueAgent, jmsConnection, qmgrName, qName });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 102 */     setSweepInterval(jmsConnection.getSweepTime());
/* 103 */     this.headerDataSize = jmsConnection.getHeaderDataSize();
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.traceData(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "headerDataSize= " + this.headerDataSize + " sweepInterval: " + sweepInterval, null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     this.options = 139434;
/*     */ 
/*     */ 
/*     */     
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "<init>(MQQueueAgent,MQConnection,String,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static synchronized void setSweepInterval(long sweepInterval) {
/* 124 */     MQQueueAgentThread2Impl.sweepInterval = sweepInterval;
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
/* 137 */     if (Trace.isOn)
/* 138 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "browse(int,long)", new Object[] {
/* 139 */             Integer.valueOf(dataQuantity), Long.valueOf(wait)
/*     */           }); 
/* 141 */     MQMessageReference deliverMR = null;
/* 142 */     if (this.storedMsgReference != null) {
/*     */       
/* 144 */       deliverMR = this.storedMsgReference;
/*     */     } else {
/* 146 */       long currentTime = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 153 */         boolean browseFirst = true;
/*     */         
/* 155 */         if (this.startSweep) {
/* 156 */           currentTime = System.currentTimeMillis();
/* 157 */           this.sweepStartedTime = System.currentTimeMillis();
/* 158 */           this.startSweep = false;
/* 159 */           this.sweepTimeoutStart = 0L;
/*     */         } 
/* 161 */         deliverMR = browseMsg(dataQuantity, browseFirst, (int)wait);
/* 162 */         if (deliverMR == null) {
/*     */ 
/*     */           
/* 165 */           deliverNow();
/* 166 */           this.startSweep = true;
/* 167 */         } else if (this.sweepTimeoutStart != 0L) {
/*     */ 
/*     */           
/* 170 */           if (currentTime == 0L) {
/* 171 */             currentTime = System.currentTimeMillis();
/*     */           }
/* 173 */           if (currentTime > this.sweepTimeoutStart + sweepInterval) {
/* 174 */             deliverNow();
/* 175 */             this.startSweep = true;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 180 */           if (currentTime == 0L) {
/* 181 */             currentTime = System.currentTimeMillis();
/*     */           }
/* 183 */           this.sweepTimeoutStart = currentTime;
/*     */         }
/*     */       
/* 186 */       } catch (MQException mqe) {
/* 187 */         if (Trace.isOn) {
/* 188 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "browse(int,long)", (Throwable)mqe);
/*     */         }
/*     */ 
/*     */         
/* 192 */         JMSException je = ConfigEnvironment.newException("MQJMS1025");
/* 193 */         je.setLinkedException((Exception)mqe);
/* 194 */         if (Trace.isOn) {
/* 195 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "browse(int,long)", (Throwable)je);
/*     */         }
/*     */ 
/*     */         
/* 199 */         throw je;
/*     */       } 
/*     */     } 
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "browse(int,long)", deliverMR);
/*     */     }
/*     */     
/* 206 */     return deliverMR;
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
/*     */   private MQMessageReference browseMsg(int dataQuantity, boolean browseFirst, int timeout) throws MQException {
/* 222 */     if (Trace.isOn)
/* 223 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "browseMsg(int,boolean,int)", new Object[] {
/* 224 */             Integer.valueOf(dataQuantity), 
/* 225 */             Boolean.valueOf(browseFirst), Integer.valueOf(timeout)
/*     */           }); 
/* 227 */     MQMessageReference outMR = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 233 */       if (browseFirst) {
/* 234 */         this.mqGMO.options &= 0xFFFFFFDF;
/* 235 */         this.mqGMO.options |= 0x10;
/*     */       } else {
/* 237 */         this.mqGMO.options &= 0xFFFFFFEF;
/* 238 */         this.mqGMO.options |= 0x20;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 243 */       this.mqGMO.options |= 0x1100000;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 248 */       boolean useBatching = (dataQuantity == 0);
/*     */ 
/*     */       
/* 251 */       this.mqGMO.options |= 0x1;
/* 252 */       this.mqGMO.waitInterval = timeout;
/*     */ 
/*     */       
/* 255 */       MQJMSMessage message = new MQJMSMessage();
/*     */ 
/*     */       
/* 258 */       if (Trace.isOn) {
/* 259 */         Trace.traceData(this, "About to browse message:", null);
/* 260 */         Trace.traceData(this, ((this.mqGMO.options & 0x20) == 0) ? "- browsing first message" : "- browsing next message", null);
/* 261 */         Trace.traceData(this, ((this.mqGMO.options & 0x1) == 0) ? "- browsing without waiting" : ("- browsing with wait for " + this.mqGMO.waitInterval), null);
/* 262 */         Trace.traceData(this, useBatching ? "- using batched-get" : "- using non-batched get", null);
/*     */       } 
/*     */ 
/*     */       
/* 266 */       int reason = 0;
/* 267 */       switch (dataQuantity) {
/*     */         case 0:
/* 269 */           Trace.traceData(this, "- browsing with NO_DATA", null);
/* 270 */           this.mqGMO.options |= 0x40;
/* 271 */           Trace.traceData(this, "- browsing with truncation", null);
/* 272 */           reason = this.mqQueue.spiBatchedGetNoExc(message, this.mqGMO, 0);
/* 273 */           if (reason == 2079) {
/* 274 */             reason = 0;
/*     */           }
/*     */           break;
/*     */         case 1:
/* 278 */           Trace.traceData(this, "- browsing with HEADER_DATA", null);
/* 279 */           reason = getMessageHeaderOnly(message);
/*     */           break;
/*     */         case 2:
/* 282 */           Trace.traceData(this, "- browsing with FULL_DATA", null);
/* 283 */           this.mqGMO.options &= 0xFFFFFFBF;
/* 284 */           Trace.traceData(this, "- browsing with full message", null);
/* 285 */           reason = this.mqQueue.getMsg2NoExc(message, this.mqGMO);
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 291 */       if (reason == 2033) {
/*     */         
/* 293 */         if (Trace.isOn) {
/* 294 */           Trace.traceData(this, "MQRC_NO_MSG_AVAILABLE", null);
/*     */         }
/* 296 */         if (Trace.isOn) {
/* 297 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "browseMsg(int,boolean,int)", null, 1);
/*     */         }
/*     */         
/* 300 */         return null;
/* 301 */       }  if (reason != 0) {
/*     */         
/* 303 */         if (Trace.isOn) {
/* 304 */           Trace.traceData(this, "Unexpected reason code " + reason, null);
/*     */         }
/* 306 */         MQException traceRet1 = new MQException(2, reason, this);
/* 307 */         if (Trace.isOn) {
/* 308 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "browseMsg(int,boolean,int)", (Throwable)traceRet1, 1);
/*     */         }
/*     */ 
/*     */         
/* 312 */         throw traceRet1;
/*     */       } 
/*     */       
/* 315 */       if (Trace.isOn) {
/* 316 */         Trace.traceData(this, "Browsed message: length" + message.getMessageDataLength() + " dataQuantityHint= " + dataQuantity, null);
/*     */       }
/* 318 */       outMR = new MQMessageReference(this.jmsSession);
/*     */       
/* 320 */       outMR.setMsgToken(this.mqGMO.msgToken);
/*     */       
/* 322 */       outMR.setDestination((ProviderDestination)this.mqQueueAgent.getProviderDestination());
/*     */       
/* 324 */       outMR.setMQJMSMessage(message, dataQuantity, this.messageMQMDandRFH2Size);
/*     */ 
/*     */ 
/*     */       
/* 328 */       this.mqGMO.options &= 0xFEEFFFFF;
/*     */       
/* 330 */       if (Trace.isOn) {
/* 331 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "browseMsg(int,boolean,int)", outMR, 2);
/*     */       }
/*     */       
/* 334 */       return outMR;
/*     */     }
/* 336 */     catch (MQException mqe) {
/* 337 */       if (Trace.isOn) {
/* 338 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "browseMsg(int,boolean,int)", (Throwable)mqe);
/*     */       }
/*     */ 
/*     */       
/* 342 */       if (Trace.isOn) {
/* 343 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "browseMsg(int,boolean,int)", (Throwable)mqe, 2);
/*     */       }
/*     */       
/* 346 */       throw mqe;
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
/*     */   protected void deliverMsgRef(MQConnectionBrowser browser, MQMessageReference msgRefP) throws JMSException {
/* 362 */     if (Trace.isOn) {
/* 363 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "deliverMsgRef(MQConnectionBrowser,MQMessageReference)", new Object[] { browser, msgRefP });
/*     */     }
/*     */ 
/*     */     
/* 367 */     MQMessageReference msgRef = msgRefP;
/*     */     
/* 369 */     MQJMSMessage tmpMessage = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 376 */     if (Trace.isOn) {
/* 377 */       Trace.traceData(this, "Browsing message with MQGMO_MARK_BROWSE_CO_OP", null);
/*     */     }
/* 379 */     this.mqGMO.options &= 0xFEFFFFDE;
/* 380 */     this.mqGMO.options |= 0xA00010;
/* 381 */     this.mqGMO.matchOptions = 32;
/* 382 */     System.arraycopy(msgRef.getMsgToken(), 0, this.mqGMO.msgToken, 0, 16);
/* 383 */     tmpMessage = new MQJMSMessage();
/*     */     
/* 385 */     int reason = 0;
/*     */ 
/*     */     
/* 388 */     switch (msgRef.getDataQuantity()) {
/*     */       case 1:
/* 390 */         if (Trace.isOn) {
/* 391 */           Trace.traceData(this, "Browsing message with MQGMO_MARK_BROWSE_CO_OP - header only : " + msgRef.getHeaderLength() + " bytes", null);
/*     */         }
/* 393 */         reason = this.mqQueue.getMsg2NoExc(tmpMessage, this.mqGMO, msgRef.getHeaderLength());
/*     */         break;
/*     */       default:
/* 396 */         if (Trace.isOn) {
/* 397 */           Trace.traceData(this, "Browsing message with MQGMO_MARK_BROWSE_CO_OP - full message", null);
/*     */         }
/* 399 */         reason = this.mqQueue.getMsg2NoExc(tmpMessage, this.mqGMO);
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 404 */     this.mqGMO.options &= 0xFF5FFFFF;
/* 405 */     this.mqGMO.matchOptions = 0;
/*     */     
/* 407 */     this.storedMsgReference = null;
/*     */ 
/*     */ 
/*     */     
/* 411 */     if (reason == 2033 || reason == 2200) {
/*     */       
/* 413 */       if (Trace.isOn) {
/* 414 */         Trace.traceData(this, "ProviderMessage has been marked or removed from the queue with reason: " + reason, null);
/*     */       }
/* 416 */       msgRef = null;
/* 417 */     } else if (reason != 0 && reason != 2079) {
/*     */ 
/*     */       
/* 420 */       if (reason == 2016) {
/*     */ 
/*     */ 
/*     */         
/* 424 */         this.storedMsgReference = msgRef;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 429 */         unmarkBrowseMessage(msgRef);
/*     */       } 
/*     */ 
/*     */       
/* 433 */       MQException me = new MQException(2, reason, this);
/* 434 */       JMSException je = ConfigEnvironment.newException("MQJMS1025");
/* 435 */       je.setLinkedException((Exception)me);
/* 436 */       if (Trace.isOn) {
/* 437 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "deliverMsgRef(MQConnectionBrowser,MQMessageReference)", (Throwable)je);
/*     */       }
/*     */       
/* 440 */       throw je;
/*     */     } 
/*     */     
/* 443 */     if (msgRef != null) {
/* 444 */       browser.deliver(msgRef);
/*     */     }
/*     */     
/* 447 */     if (Trace.isOn) {
/* 448 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "deliverMsgRef(MQConnectionBrowser,MQMessageReference)");
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
/*     */   boolean updateConfig() throws JMSException {
/* 461 */     if (Trace.isOn) {
/* 462 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "updateConfig()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 467 */     if (this.startSweep) {
/*     */ 
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 475 */         this.mqQueue.close();
/*     */       }
/* 477 */       catch (MQException mqe) {
/* 478 */         if (Trace.isOn) {
/* 479 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "updateConfig()", (Throwable)mqe, 1);
/*     */         }
/*     */ 
/*     */         
/* 483 */         JMSException je = ConfigEnvironment.newException("MQJMS2000");
/* 484 */         je.setLinkedException((Exception)mqe);
/*     */         
/* 486 */         if (Trace.isOn) {
/* 487 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "updateConfig()", (Throwable)je, 1);
/*     */         }
/*     */ 
/*     */         
/* 491 */         throw je;
/*     */       } 
/*     */       try {
/* 494 */         this.mqQueue = this.mqQueueManager.accessQueue(this.qName, this.options);
/*     */       }
/* 496 */       catch (MQException mqe) {
/* 497 */         if (Trace.isOn) {
/* 498 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "updateConfig()", (Throwable)mqe, 2);
/*     */         }
/*     */ 
/*     */         
/* 502 */         JMSException je = ConfigEnvironment.newException("MQJMS2008");
/* 503 */         je.setLinkedException((Exception)mqe);
/*     */         
/* 505 */         if (Trace.isOn) {
/* 506 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "updateConfig()", (Throwable)je, 2);
/*     */         }
/*     */ 
/*     */         
/* 510 */         throw je;
/*     */       } 
/* 512 */       if (Trace.isOn) {
/* 513 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "updateConfig()", 
/* 514 */             Boolean.valueOf(true), 1);
/*     */       }
/* 516 */       return true;
/*     */     } 
/*     */     
/* 519 */     if (Trace.isOn) {
/* 520 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "updateConfig()", 
/* 521 */           Boolean.valueOf(false), 2);
/*     */     }
/* 523 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getMessageHeaderOnly(MQJMSMessage message) {
/* 534 */     if (Trace.isOn) {
/* 535 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "getMessageHeaderOnly(MQJMSMessage)", new Object[] { message });
/*     */     }
/*     */     
/* 538 */     if (Trace.isOn) {
/* 539 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "getMessageHeaderOnly(MQJMSMessage)", new Object[] { message });
/*     */     }
/*     */     
/* 542 */     this.mqGMO.options |= 0x40;
/* 543 */     if (Trace.isOn) {
/* 544 */       Trace.traceData(this, "- browsing with truncation", null);
/*     */     }
/*     */     
/* 547 */     int reason = 0;
/*     */ 
/*     */     
/* 550 */     int truncatedBufferSize = 36 + defaultHeaderDataLength;
/*     */     
/* 552 */     if (this.messageBufferSize > truncatedBufferSize) {
/* 553 */       truncatedBufferSize = this.messageBufferSize;
/* 554 */       if (Trace.isOn) {
/* 555 */         Trace.traceData(this, "Increased truncatedBufferSize to ", Integer.valueOf(truncatedBufferSize));
/*     */       
/*     */       }
/*     */     }
/* 559 */     else if (Trace.isOn) {
/* 560 */       Trace.traceData(this, "Using default minimum header browse size of ", Integer.valueOf(truncatedBufferSize));
/*     */     } 
/*     */ 
/*     */     
/* 564 */     reason = this.mqQueue.getMsg2NoExc(message, this.mqGMO, truncatedBufferSize);
/* 565 */     if (reason == 2033) {
/* 566 */       if (Trace.isOn) {
/* 567 */         Trace.traceData(this, " - First browse. No message available", null);
/* 568 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "getMessageHeaderOnly(MQJMSMessage)", Integer.valueOf(reason), 1);
/*     */       } 
/* 570 */       if (Trace.isOn) {
/* 571 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "getMessageHeaderOnly(MQJMSMessage)", 
/* 572 */             Integer.valueOf(reason), 1);
/*     */       }
/* 574 */       return reason;
/*     */     } 
/*     */     
/* 577 */     if (reason == 2079) {
/* 578 */       reason = 0;
/*     */     }
/*     */     
/* 581 */     long format = 0L;
/*     */     try {
/* 583 */       format = message.getFormatAsLong();
/* 584 */       if (format != 5571313732236222496L && format != -3109514705028104128L) {
/* 585 */         if (Trace.isOn) {
/* 586 */           Trace.traceData(this, " - Received message does not have RFH2", null);
/* 587 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "getMessageHeaderOnly(MQJMSMessage)", Integer.valueOf(reason), 2);
/*     */         } 
/* 589 */         if (Trace.isOn) {
/* 590 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "getMessageHeaderOnly(MQJMSMessage)", 
/* 591 */               Integer.valueOf(reason), 2);
/*     */         }
/* 593 */         return reason;
/*     */       } 
/*     */       
/* 596 */       if (Trace.isOn) {
/* 597 */         Trace.traceData(this, "Message: " + message.toString(), null);
/*     */       }
/* 599 */       int msgEncoding = 0;
/* 600 */       int strucLength = 0;
/*     */       
/* 602 */       msgEncoding = message.getEncoding();
/*     */       
/* 604 */       message.skipReadingBytes(8);
/*     */       
/* 606 */       strucLength = message.readInt(msgEncoding);
/*     */       
/* 608 */       this.messageMQMDandRFH2Size = strucLength + 364;
/* 609 */       if (Trace.isOn) {
/* 610 */         Trace.traceData(this, "MQMD + RFH2 length = " + this.messageMQMDandRFH2Size, null);
/*     */       }
/*     */       
/* 613 */       if (truncatedBufferSize < strucLength)
/*     */       {
/*     */         
/* 616 */         if (Trace.isOn) {
/* 617 */           Trace.traceData(this, "RFH2 was truncated. Re-browsing message with buffer length " + strucLength, null);
/*     */         }
/*     */         
/* 620 */         MQGetMessageOptions gmo = new MQGetMessageOptions();
/*     */         
/* 622 */         gmo.options &= 0xFFFFFFDE;
/* 623 */         gmo.options |= 0x50;
/* 624 */         gmo.matchOptions = 32;
/*     */         
/* 626 */         System.arraycopy(this.mqGMO.msgToken, 0, gmo.msgToken, 0, 16);
/* 627 */         reason = this.mqQueue.getMsg2NoExc(message, gmo, strucLength);
/*     */         
/* 629 */         if (reason == 2033) {
/* 630 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "getMessageHeaderOnly(MQJMSMessage)", Integer.valueOf(reason));
/* 631 */           if (Trace.isOn) {
/* 632 */             Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "getMessageHeaderOnly(MQJMSMessage)", 
/* 633 */                 Integer.valueOf(reason), 3);
/*     */           }
/* 635 */           return reason;
/*     */         } 
/*     */         
/* 638 */         if (reason == 2079) {
/* 639 */           reason = 0;
/*     */         
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 645 */         Trace.traceData(this, "Resetting read position in message", null);
/* 646 */         message.resetReadPosition();
/*     */       }
/*     */     
/*     */     }
/* 650 */     catch (Exception e) {
/* 651 */       if (Trace.isOn) {
/* 652 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "getMessageHeaderOnly(MQJMSMessage)", e);
/*     */       }
/*     */ 
/*     */       
/* 656 */       reason = 2033;
/* 657 */       if (Trace.isOn) {
/* 658 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "getMessageHeaderOnly(MQJMSMessage)", e);
/*     */       }
/*     */     } 
/*     */     
/* 662 */     if (Trace.isOn) {
/* 663 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "getMessageHeaderOnly(MQJMSMessage)", Integer.valueOf(reason), 3);
/*     */     }
/* 665 */     if (Trace.isOn) {
/* 666 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "getMessageHeaderOnly(MQJMSMessage)", 
/* 667 */           Integer.valueOf(reason), 4);
/*     */     }
/* 669 */     return reason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void unmarkBrowseMessage(MQMessageReference msgReference) {
/* 678 */     if (Trace.isOn) {
/* 679 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "unmarkBrowseMessage(MQMessageReference)", new Object[] { msgReference });
/*     */     }
/*     */ 
/*     */     
/* 683 */     MQJMSMessage tmpMessage = new MQJMSMessage();
/*     */     
/* 685 */     this.mqGMO.options |= 0x800000;
/* 686 */     this.mqGMO.matchOptions = 32;
/* 687 */     System.arraycopy(msgReference.getMsgToken(), 0, this.mqGMO.msgToken, 0, 16);
/* 688 */     int reason = this.mqQueue.getMsg2NoExc(tmpMessage, this.mqGMO);
/*     */     
/* 690 */     if (Trace.isOn) {
/* 691 */       Trace.traceData(this, "ProviderMessage attempted to be unmarked, returned reason: " + reason, null);
/*     */     }
/*     */     
/* 694 */     this.mqGMO.options &= 0xFF7FFFFF;
/* 695 */     this.mqGMO.matchOptions = 0;
/*     */     
/* 697 */     if (Trace.isOn)
/* 698 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQQueueAgentThread2Impl", "unmarkBrowseMessage(MQMessageReference)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQQueueAgentThread2Impl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */