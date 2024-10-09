/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.disthub2.impl.matching.BadMessageFormatMatchingException;
/*     */ import com.ibm.disthub2.impl.matching.FormattedMessage;
/*     */ import com.ibm.disthub2.impl.matching.MatchingContext;
/*     */ import com.ibm.disthub2.impl.matching.selector.BooleanValue;
/*     */ import com.ibm.disthub2.impl.matching.selector.EvalContext;
/*     */ import com.ibm.disthub2.impl.matching.selector.Evaluator;
/*     */ import com.ibm.disthub2.impl.matching.selector.MatchParser;
/*     */ import com.ibm.disthub2.impl.matching.selector.Selector;
/*     */ import com.ibm.disthub2.impl.matching.selector.TokenMgrError;
/*     */ import com.ibm.mq.jms.SyntaxException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
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
/*     */ class MQMessageSelector
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQMessageSelector.java";
/*     */   private static final String JMSCORRELATIONID = "JMSCorrelationID";
/*     */   private static final String JMSMESSAGEID = "JMSMessageID";
/*     */   private static final int ID_LENGTH = 24;
/*     */   
/*     */   static {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/MQMessageSelector.java");
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
/*  79 */   private MatchParser matchParser = null;
/*     */   
/*  81 */   private Selector selectorTree = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   private String specialSelector = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQMessageSelector() {
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "<init>()");
/*     */     }
/*     */     
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "<init>()");
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
/*     */   public String getSelector() {
/*     */     String selector;
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "getSelector()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 120 */     if (this.selectorTree == null) {
/*     */       
/* 122 */       selector = this.specialSelector;
/*     */     } else {
/* 124 */       selector = this.selectorTree.toString();
/*     */     } 
/*     */     
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "getSelector()", selector);
/*     */     }
/*     */     
/* 131 */     return selector;
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
/*     */   public boolean isSelected(JMSMessage msgJMS, MQMsg2 msgMQSeries) throws JMSException {
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "isSelected(com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", new Object[] { msgJMS, msgMQSeries });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       boolean bResult;
/*     */ 
/*     */       
/* 156 */       if (this.selectorTree == null) {
/*     */         
/* 158 */         bResult = true;
/* 159 */       } else if (msgJMS == null) {
/* 160 */         bResult = false;
/*     */       } else {
/*     */ 
/*     */         
/*     */         try {
/* 165 */           FormattedMessage formattedMessage = new SelectorDataAccessor(msgJMS, msgMQSeries);
/*     */ 
/*     */           
/* 168 */           MatchingContext evalContext = new MatchingContext();
/* 169 */           evalContext.setMessage(formattedMessage);
/*     */ 
/*     */           
/* 172 */           Object result = Evaluator.eval(this.selectorTree, (EvalContext)evalContext, false);
/*     */ 
/*     */ 
/*     */           
/* 176 */           if (result instanceof BooleanValue) {
/*     */ 
/*     */             
/* 179 */             bResult = ((BooleanValue)result).booleanValue();
/* 180 */           } else if (result == null) {
/* 181 */             bResult = false;
/*     */           } else {
/* 183 */             JMSException traceRet1 = new JMSException("Need a message here");
/* 184 */             if (Trace.isOn) {
/* 185 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "isSelected(com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)traceRet1, 1);
/*     */             }
/*     */ 
/*     */             
/* 189 */             throw traceRet1;
/*     */           }
/*     */         
/* 192 */         } catch (BadMessageFormatMatchingException e) {
/* 193 */           if (Trace.isOn) {
/* 194 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "isSelected(com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)e, 1);
/*     */           }
/*     */ 
/*     */           
/* 198 */           bResult = false;
/*     */ 
/*     */           
/* 201 */           JMSException traceRet2 = new JMSException(e.toString());
/* 202 */           if (Trace.isOn) {
/* 203 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "isSelected(com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)traceRet2, 2);
/*     */           }
/*     */ 
/*     */           
/* 207 */           throw traceRet2;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 213 */       if (Trace.isOn) {
/* 214 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "isSelected(com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", 
/*     */             
/* 216 */             Boolean.valueOf(bResult));
/*     */       }
/* 218 */       return bResult;
/*     */     }
/* 220 */     catch (JMSException je) {
/* 221 */       JMSException jMSException1; if (Trace.isOn) {
/* 222 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "isSelected(com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)jMSException1, 2);
/*     */       }
/*     */ 
/*     */       
/* 226 */       if (Trace.isOn) {
/* 227 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "isSelected(com.ibm.msg.client.wmq.compat.jms.internal.JMSMessage,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", (Throwable)jMSException1, 3);
/*     */       }
/*     */ 
/*     */       
/* 231 */       throw jMSException1;
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
/*     */   @Deprecated
/*     */   public void setSelector(String selector) throws SyntaxException {
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "setSelector(String)", "setter", selector);
/*     */     }
/*     */     
/* 248 */     setSelector(selector, null);
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
/*     */   @Deprecated
/*     */   public boolean setSelector(String selector, MQMsg2 mqmsg2) throws SyntaxException {
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "setSelector(String,MQMsg2)", new Object[] { selector, mqmsg2 });
/*     */     }
/*     */ 
/*     */     
/* 274 */     boolean traceRet1 = setSelector(selector, mqmsg2, null);
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "setSelector(String,MQMsg2)", 
/* 277 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 279 */     return traceRet1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean setSelector(String selectorP, MQMsg2 mqmsg2, MQGetMessageOptions gmo) throws SyntaxException {
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "setSelector(String,MQMsg2,MQGetMessageOptions)", new Object[] { selectorP, mqmsg2, gmo });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 313 */     String selector = selectorP;
/* 314 */     boolean bSpecialCase = false;
/*     */ 
/*     */     
/* 317 */     if (selector != null && selector.trim().length() == 0) {
/* 318 */       if (Trace.isOn) {
/* 319 */         Trace.traceData(this, "blank selector changed to null", null);
/*     */       }
/* 321 */       selector = null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 326 */     if (selector == null) {
/* 327 */       if (Trace.isOn) {
/* 328 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "setSelector(String,MQMsg2,MQGetMessageOptions)", 
/* 329 */             Boolean.valueOf(bSpecialCase), 1);
/*     */       }
/* 331 */       return bSpecialCase;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 336 */     if (mqmsg2 != null && selector.length() > 0) {
/* 337 */       String tokens = selector.trim();
/*     */       
/*     */       try {
/* 340 */         byte[] id = null;
/* 341 */         byte[] msgId = null;
/* 342 */         byte[] correlId = null;
/* 343 */         int idLength = 0;
/* 344 */         boolean bId = false;
/*     */ 
/*     */         
/* 347 */         if (tokens.startsWith("JMSCorrelationID")) {
/* 348 */           tokens = tokens.substring("JMSCorrelationID".length()).trim();
/* 349 */           idLength = 24;
/* 350 */           bId = true;
/*     */ 
/*     */           
/* 353 */           correlId = new byte[idLength];
/* 354 */           id = correlId;
/*     */ 
/*     */           
/* 357 */           if (gmo != null) {
/* 358 */             gmo.matchOptions |= 0x2;
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 363 */         if (tokens.startsWith("JMSMessageID")) {
/* 364 */           tokens = tokens.substring("JMSMessageID".length()).trim();
/* 365 */           idLength = 24;
/* 366 */           bId = true;
/*     */ 
/*     */           
/* 369 */           msgId = new byte[idLength];
/* 370 */           id = msgId;
/*     */ 
/*     */           
/* 373 */           if (gmo != null) {
/* 374 */             gmo.matchOptions |= 0x1;
/*     */           }
/*     */         } 
/*     */         
/* 378 */         if (bId)
/*     */         {
/* 380 */           if (tokens.charAt(0) == '=') {
/* 381 */             tokens = tokens.substring(1).trim();
/*     */             
/* 383 */             if (tokens.startsWith("'ID:")) {
/* 384 */               boolean bHex = false;
/*     */ 
/*     */               
/* 387 */               tokens = tokens.substring(4).toLowerCase();
/* 388 */               if (tokens.length() == idLength * 2 + 1 && tokens.charAt(idLength * 2) == '\'') {
/*     */ 
/*     */ 
/*     */                 
/* 392 */                 bHex = true;
/* 393 */                 for (int i = 0; i < 24; i++) {
/* 394 */                   int n = 0;
/*     */                   
/* 396 */                   char c1 = tokens.charAt(i * 2);
/* 397 */                   if (c1 >= '0' && c1 <= '9') {
/* 398 */                     n += c1 - 48;
/*     */                   }
/* 400 */                   else if (c1 >= 'a' && c1 <= 'f') {
/* 401 */                     n += c1 - 97 + 10;
/*     */                   } else {
/* 403 */                     bHex = false;
/*     */                   } 
/*     */ 
/*     */                   
/* 407 */                   n *= 16;
/* 408 */                   char c2 = tokens.charAt(i * 2 + 1);
/* 409 */                   if (c2 >= '0' && c2 <= '9') {
/* 410 */                     n += c2 - 48;
/*     */                   }
/* 412 */                   else if (c2 >= 'a' && c2 <= 'f') {
/* 413 */                     n += c2 - 97 + 10;
/*     */                   } else {
/* 415 */                     bHex = false;
/*     */                   } 
/*     */ 
/*     */                   
/* 419 */                   id[i] = (byte)n;
/*     */                 } 
/*     */               } 
/*     */               
/* 423 */               if (bHex) {
/* 424 */                 bSpecialCase = true;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         }
/* 429 */         if (msgId != null) {
/* 430 */           mqmsg2.setMessageId(msgId);
/* 431 */         } else if (correlId != null) {
/* 432 */           mqmsg2.setCorrelationId(correlId);
/*     */         }
/*     */       
/* 435 */       } catch (StringIndexOutOfBoundsException e) {
/* 436 */         if (Trace.isOn) {
/* 437 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "setSelector(String,MQMsg2,MQGetMessageOptions)", e, 1);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 444 */     if (bSpecialCase) {
/* 445 */       if (Trace.isOn) {
/* 446 */         Trace.traceData(this, "Selector is a special case.", null);
/*     */       }
/*     */       
/* 449 */       this.specialSelector = selector;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 454 */       selector = parseOctal(selector);
/*     */ 
/*     */       
/* 457 */       this.matchParser = MatchParser.prime(null, selector, true);
/*     */       
/*     */       try {
/* 460 */         this.selectorTree = this.matchParser.getSelector();
/*     */ 
/*     */ 
/*     */         
/* 464 */         if (Trace.isOn) {
/* 465 */           Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "setSelector(String,MQMsg2,MQGetMessageOptions)", "selectorTree: ", this.selectorTree
/* 466 */               .toString());
/*     */         }
/*     */       }
/* 469 */       catch (TokenMgrError e) {
/* 470 */         if (Trace.isOn) {
/* 471 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "setSelector(String,MQMsg2,MQGetMessageOptions)", (Throwable)e, 2);
/*     */         }
/*     */         
/* 474 */         if (Trace.isOn) {
/* 475 */           Trace.traceData(this, "Selector was Invalid", null);
/*     */         }
/* 477 */         String key = "MQJMS0004";
/* 478 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 479 */         SyntaxException je = new SyntaxException(msg);
/* 480 */         if (Trace.isOn) {
/* 481 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "setSelector(String,MQMsg2,MQGetMessageOptions)", (Throwable)je, 1);
/*     */         }
/*     */         
/* 484 */         throw je;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 489 */       if (this.selectorTree.type == 2) {
/* 490 */         if (Trace.isOn) {
/* 491 */           Trace.traceData(this, "Selector was Invalid", null);
/*     */         }
/*     */         
/* 494 */         String key = "MQJMS0004";
/* 495 */         String msg = ConfigEnvironment.getErrorMessage(key);
/* 496 */         SyntaxException je = new SyntaxException(msg);
/* 497 */         if (Trace.isOn) {
/* 498 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "setSelector(String,MQMsg2,MQGetMessageOptions)", (Throwable)je, 2);
/*     */         }
/*     */         
/* 501 */         throw je;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 507 */     if (Trace.isOn) {
/* 508 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "setSelector(String,MQMsg2,MQGetMessageOptions)", 
/* 509 */           Boolean.valueOf(bSpecialCase), 2);
/*     */     }
/* 511 */     return bSpecialCase;
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
/*     */   private String parseOctal(String s) {
/* 531 */     if (Trace.isOn) {
/* 532 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "parseOctal(String)", new Object[] { s });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 537 */     String bodged = null;
/*     */     
/* 539 */     if (s != null) {
/*     */       
/* 541 */       String[] elements = s.split("=");
/*     */ 
/*     */       
/* 544 */       if (elements.length == 2) {
/*     */         
/* 546 */         elements[0] = elements[0].trim();
/* 547 */         elements[1] = elements[1].trim();
/*     */         
/* 549 */         if (elements[1].startsWith("0") && !elements[1].startsWith("0x")) {
/*     */ 
/*     */           
/*     */           try {
/* 553 */             long decimal = Long.decode(elements[1]).longValue();
/*     */ 
/*     */             
/* 556 */             bodged = elements[0] + "=" + Long.toString(decimal);
/*     */           }
/* 558 */           catch (NumberFormatException nfe) {
/* 559 */             if (Trace.isOn) {
/* 560 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "parseOctal(String)", nfe);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 565 */             bodged = s;
/*     */           } 
/*     */         } else {
/*     */           
/* 569 */           bodged = s;
/*     */         } 
/*     */       } else {
/*     */         
/* 573 */         bodged = s;
/*     */       } 
/*     */     } 
/*     */     
/* 577 */     if (Trace.isOn) {
/* 578 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.MQMessageSelector", "parseOctal(String)", bodged);
/*     */     }
/*     */     
/* 581 */     return bodged;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\MQMessageSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */