/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.disthub2.impl.matching.BadMessageFormatMatchingException;
/*     */ import com.ibm.disthub2.impl.matching.FormattedMessage;
/*     */ import com.ibm.disthub2.impl.matching.NoSuchFieldNameException;
/*     */ import com.ibm.disthub2.impl.matching.selector.BooleanValue;
/*     */ import com.ibm.disthub2.impl.matching.selector.NumericValue;
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.provider.ProviderMessage;
/*     */ import com.ibm.msg.client.wmq.common.internal.WMQDestinationURIParser;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.charset.Charset;
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
/*     */ class SelectorDataAccessor
/*     */   implements FormattedMessage
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/SelectorDataAccessor.java";
/*     */   private ProviderMessage omsgJMS;
/*     */   private MQMsg2 omsgMQSeries;
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/SelectorDataAccessor.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SelectorDataAccessor(ProviderMessage msgJMS, MQMsg2 msgMQSeries) {
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "<init>(ProviderMessage,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)", new Object[] { msgJMS, msgMQSeries });
/*     */     }
/*     */ 
/*     */     
/*  87 */     this.omsgJMS = msgJMS;
/*  88 */     this.omsgMQSeries = msgMQSeries;
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "<init>(ProviderMessage,com.ibm.msg.client.wmq.compat.base.internal.MQMsg2)");
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
/*     */   public boolean isValidHeaderValue(String fieldName) throws BadMessageFormatMatchingException, NoSuchFieldNameException {
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "isValidHeaderValue(String)", new Object[] { fieldName });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "isValidHeaderValue(String)", 
/* 120 */           Boolean.valueOf(false));
/*     */     }
/* 122 */     return false;
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
/*     */   public Object getHeaderValue(String fieldName) throws BadMessageFormatMatchingException, NoSuchFieldNameException {
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getHeaderValue(String)", new Object[] { fieldName });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getHeaderValue(String)", null);
/*     */     }
/*     */     
/* 151 */     return null;
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
/*     */   public Object getHeaderStringValue(String fieldName) throws BadMessageFormatMatchingException, NoSuchFieldNameException {
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getHeaderStringValue(String)", new Object[] { fieldName });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getHeaderStringValue(String)", null);
/*     */     }
/*     */     
/* 180 */     return null;
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
/*     */   public Object getHeaderNumberValue(String fieldName) throws BadMessageFormatMatchingException, NoSuchFieldNameException {
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getHeaderNumberValue(String)", new Object[] { fieldName });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getHeaderNumberValue(String)", null);
/*     */     }
/*     */     
/* 209 */     return null;
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
/*     */   public String getPropertiesTopic() throws BadMessageFormatMatchingException {
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getPropertiesTopic()", "getter", null);
/*     */     }
/*     */     
/* 229 */     return null;
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
/*     */   public Object getFieldValue(String fieldNameP) throws BadMessageFormatMatchingException, NoSuchFieldNameException {
/* 251 */     if (Trace.isOn) {
/* 252 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getFieldValue(String)", new Object[] { fieldNameP });
/*     */     }
/*     */     
/* 255 */     String fieldName = fieldNameP;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 260 */       Object o = null;
/*     */ 
/*     */ 
/*     */       
/* 264 */       if (fieldName.indexOf("JMS.") == 0) {
/* 265 */         if (Trace.isOn) {
/* 266 */           Trace.traceData(this, "fieldName = '" + fieldName + "'", null);
/*     */         }
/* 268 */         fieldName = fieldName.substring(4);
/* 269 */         if (Trace.isOn) {
/* 270 */           Trace.traceData(this, "fieldName = '" + fieldName + "'", null);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 275 */       if (fieldName.indexOf("JMSX") == 0) {
/*     */ 
/*     */         
/* 278 */         if (fieldName.equals("JMSXApplID")) {
/* 279 */           if (this.omsgMQSeries != null) {
/* 280 */             o = this.omsgMQSeries.getPutApplicationName();
/*     */           
/*     */           }
/*     */         }
/* 284 */         else if (fieldName.equals("JMSXDeliveryCount")) {
/* 285 */           if (this.omsgMQSeries != null)
/*     */           {
/*     */             
/* 288 */             o = Integer.valueOf(this.omsgMQSeries.getBackoutCount() + 1);
/*     */           
/*     */           }
/*     */         }
/* 292 */         else if (fieldName.equals("JMSXUserID") && 
/* 293 */           this.omsgMQSeries != null) {
/* 294 */           o = this.omsgMQSeries.getUserId();
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 299 */         if (o == null) {
/* 300 */           o = this.omsgJMS.getObjectProperty(fieldName);
/*     */ 
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 306 */       else if (fieldName.indexOf("JMS_") == 0) {
/* 307 */         if (this.omsgMQSeries != null) {
/* 308 */           if (fieldName.equals("JMS_IBM_Feedback")) {
/* 309 */             o = Integer.valueOf(this.omsgMQSeries.getFeedback());
/*     */           
/*     */           }
/* 312 */           else if (fieldName.equals("JMS_IBM_Format")) {
/* 313 */             o = this.omsgJMS.getObjectProperty("JMS_IBM_Format");
/*     */           
/*     */           }
/* 316 */           else if (fieldName.equals("JMS_IBM_MsgType")) {
/* 317 */             o = Integer.valueOf(this.omsgMQSeries.getMessageType());
/*     */           
/*     */           }
/* 320 */           else if (fieldName.equals("JMS_IBM_PutApplType")) {
/* 321 */             o = Integer.valueOf(this.omsgMQSeries.getPutApplicationType());
/*     */           
/*     */           }
/* 324 */           else if (fieldName.equals("JMS_IBM_PutDate")) {
/*     */             try {
/* 326 */               o = new String(this.omsgMQSeries.getPutDateAsBytes(), "UTF-8");
/*     */             }
/* 328 */             catch (UnsupportedEncodingException e1) {
/* 329 */               if (Trace.isOn) {
/* 330 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getFieldValue(String)", e1, 1);
/*     */               
/*     */               }
/*     */             
/*     */             }
/*     */ 
/*     */           
/*     */           }
/* 338 */           else if (fieldName.equals("JMS_IBM_PutTime")) {
/* 339 */             o = new String(this.omsgMQSeries.getPutTimeAsBytes(), Charset.defaultCharset());
/*     */           
/*     */           }
/* 342 */           else if (fieldName.equals("JMS_IBM_Report_COA")) {
/* 343 */             o = Integer.valueOf(this.omsgMQSeries.getReport() & 0x700);
/*     */           
/*     */           }
/* 346 */           else if (fieldName.equals("JMS_IBM_Report_COD")) {
/* 347 */             o = Integer.valueOf(this.omsgMQSeries.getReport() & 0x3800);
/*     */           
/*     */           }
/* 350 */           else if (fieldName.equals("JMS_IBM_Report_Pass_Correl_ID")) {
/* 351 */             o = Integer.valueOf(this.omsgMQSeries.getReport() & 0x40);
/*     */           
/*     */           }
/* 354 */           else if (fieldName.equals("JMS_IBM_Report_Exception")) {
/* 355 */             o = Integer.valueOf(this.omsgMQSeries.getReport() & 0x7000000);
/*     */           
/*     */           }
/* 358 */           else if (fieldName.equals("JMS_IBM_Report_Expiration")) {
/* 359 */             o = Integer.valueOf(this.omsgMQSeries.getReport() & 0xE00000);
/*     */           
/*     */           }
/* 362 */           else if (fieldName.equals("JMS_IBM_Report_Pass_Msg_ID")) {
/* 363 */             o = Integer.valueOf(this.omsgMQSeries.getReport() & 0x80);
/*     */           
/*     */           }
/* 366 */           else if (fieldName.equals("JMS_IBM_Report_NAN")) {
/* 367 */             o = Integer.valueOf(this.omsgMQSeries.getReport() & 0x2);
/*     */           
/*     */           }
/* 370 */           else if (fieldName.equals("JMS_IBM_Report_PAN")) {
/* 371 */             o = Integer.valueOf(this.omsgMQSeries.getReport() & 0x1);
/*     */           
/*     */           }
/* 374 */           else if (fieldName.equals("JMS_IBM_Report_Discard_Msg")) {
/* 375 */             o = Integer.valueOf(this.omsgMQSeries.getReport() & 0x8000000);
/*     */ 
/*     */           
/*     */           }
/* 379 */           else if (fieldName.equals("JMS_IBM_Last_Msg_In_Group")) {
/* 380 */             if (Trace.isOn) {
/* 381 */               Trace.traceData(this, "@PN - Last ProviderMessage in group returning " + (((this.omsgMQSeries.getMessageFlags() & 0x10) > 0) ? 1 : 0), null);
/*     */             }
/* 383 */             o = Boolean.valueOf(((this.omsgMQSeries.getMessageFlags() & 0x10) > 0));
/*     */ 
/*     */           
/*     */           }
/* 387 */           else if (fieldName.equals("JMS_IBM_ArmCorrelator")) {
/* 388 */             o = this.omsgJMS.getObjectProperty("JMS_IBM_ArmCorrelator");
/* 389 */           } else if (fieldName.equals("JMS_IBM_RMCorrelator")) {
/* 390 */             o = this.omsgJMS.getObjectProperty("JMS_IBM_RMCorrelator");
/*     */           } 
/*     */         } else {
/*     */           
/* 394 */           o = null;
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 399 */       else if (fieldName.indexOf("JMS") == 0) {
/*     */         
/* 401 */         if (fieldName.equals("JMSCorrelationID")) {
/* 402 */           o = this.omsgJMS.getJMSCorrelationID();
/*     */         
/*     */         }
/* 405 */         else if (fieldName.equals("JMSDeliveryMode")) {
/*     */ 
/*     */           
/* 408 */           switch (this.omsgJMS.getJMSDeliveryMode().intValue()) {
/*     */             
/*     */             case 2:
/* 411 */               o = "PERSISTENT";
/*     */               break;
/*     */             
/*     */             case 1:
/* 415 */               o = "NON_PERSISTENT";
/*     */               break;
/*     */             
/*     */             default:
/* 419 */               o = null;
/*     */               break;
/*     */           } 
/*     */         
/* 423 */         } else if (fieldName.equals("JMSDestination")) {
/* 424 */           String destination = this.omsgJMS.getJMSDestinationAsString();
/* 425 */           WMQDestinationURIParser destParser = new WMQDestinationURIParser(destination);
/* 426 */           o = destParser.getDestinationName();
/*     */         
/*     */         }
/* 429 */         else if (fieldName.equals("JMSExpiration")) {
/* 430 */           o = this.omsgJMS.getJMSExpiration();
/*     */         
/*     */         }
/* 433 */         else if (fieldName.equals("JMSMessageID")) {
/* 434 */           o = this.omsgJMS.getJMSMessageID();
/*     */         
/*     */         }
/* 437 */         else if (fieldName.equals("JMSPriority")) {
/* 438 */           o = this.omsgJMS.getJMSPriority();
/*     */         
/*     */         }
/* 441 */         else if (fieldName.equals("JMSRedelivered")) {
/* 442 */           o = this.omsgJMS.getJMSRedelivered();
/*     */         
/*     */         }
/* 445 */         else if (fieldName.equals("JMSReplyTo")) {
/* 446 */           String destination = this.omsgJMS.getJMSReplyToAsString();
/* 447 */           WMQDestinationURIParser destParser = new WMQDestinationURIParser(destination);
/* 448 */           o = destParser.getDestinationName();
/*     */         
/*     */         }
/* 451 */         else if (fieldName.equals("JMSTimestamp")) {
/* 452 */           o = this.omsgJMS.getJMSTimestamp();
/*     */         
/*     */         }
/* 455 */         else if (fieldName.equals("JMSType")) {
/* 456 */           o = this.omsgJMS.getJMSType();
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 465 */         if (Trace.isOn) {
/* 466 */           Trace.traceData(this, "Checking for user property " + fieldName, null);
/*     */         }
/* 468 */         o = this.omsgJMS.getObjectProperty(fieldName);
/*     */       } 
/*     */ 
/*     */       
/* 472 */       if (o == null) {
/* 473 */         if (Trace.isOn) {
/* 474 */           Trace.traceData(this, "Property " + fieldName + " has a null value", null);
/*     */         }
/* 476 */         if (Trace.isOn) {
/* 477 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getFieldValue(String)", null, 1);
/*     */         }
/*     */         
/* 480 */         return null;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 486 */       if (o instanceof String) {
/*     */ 
/*     */ 
/*     */         
/* 490 */         if (Trace.isOn) {
/* 491 */           Trace.traceData(this, "Property " + fieldName + " has string value " + o, null);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 496 */         if (Trace.isOn) {
/* 497 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getFieldValue(String)", o, 2);
/*     */         }
/*     */         
/* 500 */         return o;
/*     */       } 
/*     */       
/* 503 */       if (o instanceof Boolean) {
/* 504 */         boolean x = ((Boolean)o).booleanValue();
/*     */ 
/*     */ 
/*     */         
/* 508 */         if (Trace.isOn) {
/* 509 */           Trace.traceData(this, "Property " + fieldName + " has boolean value " + o, null);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 515 */         Object b = BooleanValue.valueOf(x);
/* 516 */         if (Trace.isOn) {
/* 517 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getFieldValue(String)", b, 3);
/*     */         }
/*     */         
/* 520 */         return b;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 526 */       if (Trace.isOn) {
/* 527 */         Trace.traceData(this, "Property " + fieldName + " has numeric value " + o, null);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 533 */       Object n = new NumericValue((Number)o);
/* 534 */       if (Trace.isOn) {
/* 535 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getFieldValue(String)", n, 4);
/*     */       }
/*     */       
/* 538 */       return n;
/*     */     
/*     */     }
/* 541 */     catch (MQException mqe) {
/* 542 */       if (Trace.isOn) {
/* 543 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getFieldValue(String)", (Throwable)mqe, 2);
/*     */       }
/*     */ 
/*     */       
/* 547 */       NoSuchFieldNameException e = new NoSuchFieldNameException("JMSException: " + fieldName + " " + mqe.toString());
/* 548 */       if (Trace.isOn) {
/* 549 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getFieldValue(String)", (Throwable)e, 1);
/*     */       }
/*     */       
/* 552 */       throw e;
/*     */     }
/* 554 */     catch (JMSException eJMS) {
/* 555 */       if (Trace.isOn) {
/* 556 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getFieldValue(String)", (Throwable)eJMS, 3);
/*     */       }
/*     */ 
/*     */       
/* 560 */       NoSuchFieldNameException e = new NoSuchFieldNameException("JMSException: " + fieldName + " " + eJMS.toString());
/* 561 */       if (Trace.isOn) {
/* 562 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getFieldValue(String)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 565 */       throw e;
/*     */     
/*     */     }
/* 568 */     catch (NoSuchFieldNameException fne) {
/* 569 */       if (Trace.isOn) {
/* 570 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getFieldValue(String)", (Throwable)fne, 4);
/*     */       }
/*     */       
/* 573 */       if (Trace.isOn) {
/* 574 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.SelectorDataAccessor", "getFieldValue(String)", (Throwable)fne, 3);
/*     */       }
/*     */       
/* 577 */       throw fne;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\SelectorDataAccessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */