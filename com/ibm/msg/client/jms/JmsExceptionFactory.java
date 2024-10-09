/*     */ package com.ibm.msg.client.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.JMSRuntimeException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmsExceptionFactory
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8593377892654124834L;
/*     */   int version;
/*     */   String exceptionData;
/*     */   
/*     */   static {
/*  73 */     if (Trace.isOn) {
/*  74 */       Trace.data("com.ibm.msg.client.jms.JmsExceptionFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.jms/src/com/ibm/msg/client/jms/JmsExceptionFactory.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   ArrayList<byte[]> linkedExceptions = (ArrayList)new ArrayList<>();
/*     */   
/*     */   static final String EXCLAMATION_POINT = "</exclam/>";
/*     */   
/*     */   static final String EQUALS = "</equals/>";
/*     */   
/*     */   static final String AMPERSAND = "</ampersand/>";
/*     */   
/*     */   static final String NULL_VALUE = "</null/>";
/*     */   
/*     */   public JmsExceptionFactory(Throwable e) {
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.entry(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "<init>(Throwable)", new Object[] { e });
/*     */     }
/*     */ 
/*     */     
/* 100 */     this.version = 7;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     this.exceptionData = collapseToString(e);
/*     */ 
/*     */ 
/*     */     
/* 111 */     Throwable linkedException = e.getCause();
/* 112 */     while (linkedException != null) {
/*     */       
/*     */       try {
/* 115 */         ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 116 */         ObjectOutputStream out = new ObjectOutputStream(bos);
/* 117 */         out.writeObject(linkedException);
/* 118 */         out.close();
/*     */ 
/*     */         
/* 121 */         byte[] buf = bos.toByteArray();
/* 122 */         bos.close();
/*     */ 
/*     */         
/* 125 */         this.linkedExceptions.add(buf);
/*     */ 
/*     */         
/* 128 */         linkedException = linkedException.getCause();
/*     */       }
/* 130 */       catch (IOException ioe) {
/* 131 */         if (Trace.isOn) {
/* 132 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "<init>(Throwable)", ioe);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 137 */         linkedException.printStackTrace();
/* 138 */         ioe.printStackTrace();
/*     */       } 
/*     */     } 
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.exit(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "<init>(Throwable)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.entry(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "readResolve()");
/*     */     }
/*     */ 
/*     */     
/* 156 */     Object traceRet1 = createException();
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.exit(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "readResolve()", traceRet1);
/*     */     }
/* 160 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Exception createException() {
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.entry(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "createException()");
/*     */     }
/*     */ 
/*     */     
/* 173 */     Exception e = inflateFromString(this.exceptionData);
/*     */ 
/*     */     
/* 176 */     Throwable currentException = e;
/*     */     
/* 178 */     for (int i = 0; i < this.linkedExceptions.size(); i++) {
/*     */       
/* 180 */       byte[] buf = this.linkedExceptions.get(i);
/*     */ 
/*     */       
/*     */       try {
/* 184 */         ByteArrayInputStream bis = new ByteArrayInputStream(buf);
/* 185 */         ObjectInputStream ois = new ObjectInputStream(bis);
/* 186 */         Throwable t = (Throwable)ois.readObject();
/* 187 */         ois.close();
/* 188 */         bis.close();
/*     */ 
/*     */         
/* 191 */         if (currentException instanceof JMSException) {
/* 192 */           ((JMSException)currentException).setLinkedException((Exception)t);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 197 */         currentException = t;
/*     */       }
/* 199 */       catch (IOException ioe) {
/* 200 */         if (Trace.isOn) {
/* 201 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "createException()", ioe, 1);
/*     */ 
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 207 */       catch (ClassNotFoundException cnfe) {
/* 208 */         if (Trace.isOn) {
/* 209 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "createException()", cnfe, 2);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.exit(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "createException()", e);
/*     */     }
/* 221 */     return e;
/*     */   }
/*     */   
/*     */   private String collapseToString(Throwable exception) {
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.entry(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "collapseToString(Throwable)", new Object[] { exception });
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 243 */     StringBuilder data = new StringBuilder();
/*     */ 
/*     */     
/* 246 */     data.append("!V" + this.version);
/* 247 */     data.append("!JMS_EXCEPTION");
/* 248 */     data.append("!" + removeInvalidChars(exception.getClass().getName()));
/*     */     
/* 250 */     if (exception instanceof JMSException) {
/*     */       
/* 252 */       data.append("!" + removeInvalidChars(((JMSException)exception).getErrorCode()));
/* 253 */       data.append("!" + removeInvalidChars(((JMSException)exception).getMessage()));
/* 254 */     } else if (exception instanceof JMSRuntimeException) {
/*     */       
/* 256 */       data.append("!" + removeInvalidChars(((JMSRuntimeException)exception).getErrorCode()));
/* 257 */       data.append("!" + removeInvalidChars(((JMSRuntimeException)exception).getMessage()));
/*     */     } else {
/* 259 */       data.append("!No error code available for " + exception.getClass().getSimpleName());
/* 260 */       data.append("!No message available for " + exception.getClass().getSimpleName());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 266 */     StackTraceElement[] stackTrace = exception.getStackTrace();
/* 267 */     StringBuilder serializedStackTrace = new StringBuilder();
/*     */     
/*     */     try {
/* 270 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 271 */       ObjectOutputStream out = new ObjectOutputStream(bos);
/* 272 */       out.writeObject(stackTrace);
/* 273 */       out.close();
/*     */ 
/*     */       
/* 276 */       byte[] buf = bos.toByteArray();
/* 277 */       bos.close();
/*     */ 
/*     */       
/* 280 */       for (int j = 0; j < buf.length; j++) {
/* 281 */         serializedStackTrace.append(Byte.toString(buf[j]));
/* 282 */         serializedStackTrace.append(",");
/*     */       }
/*     */     
/* 285 */     } catch (IOException ioe) {
/* 286 */       if (Trace.isOn) {
/* 287 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "collapseToString(Throwable)", ioe, 1);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 294 */     data.append("!" + serializedStackTrace);
/*     */ 
/*     */     
/* 297 */     data.append("!EXCEPTION_DETAILS!");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 318 */     if (exception instanceof JmsExceptionDetail) {
/*     */       
/* 320 */       JmsExceptionDetail e = (JmsExceptionDetail)exception;
/*     */       
/* 322 */       data.append("reason=").append(removeInvalidChars(exception.getMessage()));
/* 323 */       if (exception instanceof JMSException) {
/*     */         
/* 325 */         data.append("&errorCode=").append(removeInvalidChars(((JMSException)exception).getErrorCode()));
/* 326 */       } else if (exception instanceof JMSRuntimeException) {
/*     */         
/* 328 */         data.append("&errorCode=").append(removeInvalidChars(((JMSRuntimeException)exception).getErrorCode()));
/*     */       } 
/* 330 */       data.append("&explanation=").append(removeInvalidChars(e.getExplanation()));
/* 331 */       data.append("&useraction=").append(removeInvalidChars(e.getUserAction()));
/*     */ 
/*     */       
/* 334 */       HashMap<String, Object> props = new HashMap<>();
/* 335 */       Iterator<String> i = e.getKeys();
/*     */ 
/*     */ 
/*     */       
/* 339 */       if (i != null) {
/* 340 */         while (i.hasNext()) {
/* 341 */           String key = i.next();
/* 342 */           props.put(key, e.getValue(key));
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 347 */       StringBuilder serializedInserts = new StringBuilder();
/*     */       
/*     */       try {
/* 350 */         ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 351 */         ObjectOutputStream out = new ObjectOutputStream(bos);
/* 352 */         out.writeObject(props);
/* 353 */         out.close();
/*     */ 
/*     */         
/* 356 */         byte[] buf = bos.toByteArray();
/* 357 */         bos.close();
/*     */ 
/*     */         
/* 360 */         for (int j = 0; j < buf.length; j++) {
/* 361 */           serializedInserts.append(Byte.toString(buf[j]));
/* 362 */           serializedInserts.append(",");
/*     */         }
/*     */       
/* 365 */       } catch (IOException ioe) {
/* 366 */         if (Trace.isOn) {
/* 367 */           Trace.catchBlock(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "collapseToString(Throwable)", ioe, 2);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 374 */       data.append("&inserts=").append(serializedInserts);
/*     */     } 
/*     */     
/* 377 */     String traceRet1 = data.toString();
/* 378 */     if (Trace.isOn) {
/* 379 */       Trace.exit(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "collapseToString(Throwable)", traceRet1);
/*     */     }
/*     */     
/* 382 */     return traceRet1;
/*     */   }
/*     */   
/*     */   private Exception inflateFromString(String string) {
/*     */     DetailedTransactionRolledBackException detailedTransactionRolledBackException;
/* 387 */     if (Trace.isOn) {
/* 388 */       Trace.entry(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "inflateFromString(String)", new Object[] { string });
/*     */     }
/*     */ 
/*     */     
/* 392 */     Exception e = null;
/* 393 */     String[] fields = string.split("!");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 402 */     String exceptionType = replaceInvalidChars(fields[3]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 409 */     String stackTraceString = fields[6];
/* 410 */     StackTraceElement[] stackTrace = null;
/*     */     try {
/* 412 */       String[] stackTraceStringArray = stackTraceString.split(",");
/*     */       
/* 414 */       byte[] stackTraceBytes = new byte[stackTraceStringArray.length];
/* 415 */       for (int j = 0; j < stackTraceBytes.length; j++) {
/* 416 */         stackTraceBytes[j] = Byte.parseByte(stackTraceStringArray[j]);
/*     */       }
/*     */       
/* 419 */       ByteArrayInputStream bis = new ByteArrayInputStream(stackTraceBytes);
/* 420 */       ObjectInputStream ois = new ObjectInputStream(bis);
/* 421 */       stackTrace = (StackTraceElement[])ois.readObject();
/* 422 */       ois.close();
/* 423 */       bis.close();
/*     */     }
/* 425 */     catch (IOException ioe) {
/* 426 */       if (Trace.isOn) {
/* 427 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "inflateFromString(String)", ioe, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 432 */       ioe.printStackTrace();
/*     */     }
/* 434 */     catch (ClassNotFoundException cnfe) {
/* 435 */       if (Trace.isOn) {
/* 436 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "inflateFromString(String)", cnfe, 2);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 446 */     String[] eData = fields[8].split("&");
/*     */     
/* 448 */     String eReason = eData[0];
/* 449 */     eReason = eReason.substring(eReason.indexOf("=") + 1, eReason.length());
/* 450 */     eReason = replaceInvalidChars(eReason);
/*     */     
/* 452 */     String eCode = eData[1];
/* 453 */     eCode = eCode.substring(eCode.indexOf("=") + 1, eCode.length());
/* 454 */     eCode = replaceInvalidChars(eCode);
/*     */     
/* 456 */     String eExplanation = eData[2];
/* 457 */     eExplanation = eExplanation.substring(eExplanation.indexOf("=") + 1, eExplanation.length());
/* 458 */     eExplanation = replaceInvalidChars(eExplanation);
/*     */     
/* 460 */     String eAction = eData[3];
/* 461 */     eAction = eAction.substring(eAction.indexOf("=") + 1, eAction.length());
/* 462 */     eAction = replaceInvalidChars(eAction);
/*     */     
/* 464 */     String eInsertsString = eData[4];
/* 465 */     eInsertsString = eInsertsString.substring(eInsertsString.indexOf("=") + 1, eInsertsString
/* 466 */         .length());
/* 467 */     HashMap<String, Object> eInserts = null;
/*     */     try {
/* 469 */       String[] eInsertsStringArray = eInsertsString.split(",");
/*     */       
/* 471 */       byte[] eInsertsBytes = new byte[eInsertsStringArray.length];
/* 472 */       for (int j = 0; j < eInsertsBytes.length; j++) {
/* 473 */         eInsertsBytes[j] = Byte.parseByte(eInsertsStringArray[j]);
/*     */       }
/*     */       
/* 476 */       ByteArrayInputStream bis = new ByteArrayInputStream(eInsertsBytes);
/* 477 */       ObjectInputStream ois = new ObjectInputStream(bis);
/* 478 */       eInserts = (HashMap<String, Object>)ois.readObject();
/* 479 */       ois.close();
/* 480 */       bis.close();
/*     */     }
/* 482 */     catch (IOException ioe) {
/* 483 */       if (Trace.isOn) {
/* 484 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "inflateFromString(String)", ioe, 3);
/*     */ 
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 490 */     catch (ClassNotFoundException cnfe) {
/* 491 */       if (Trace.isOn) {
/* 492 */         Trace.catchBlock(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "inflateFromString(String)", cnfe, 4);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 500 */     if (exceptionType.equals(DetailedIllegalStateException.class.getName())) {
/* 501 */       DetailedIllegalStateException detailedIllegalStateException = new DetailedIllegalStateException(eReason, eCode, eExplanation, eAction, eInserts);
/* 502 */       detailedIllegalStateException.setStackTrace(stackTrace);
/*     */     } 
/* 504 */     if (exceptionType.equals(DetailedInvalidDestinationException.class.getName())) {
/* 505 */       DetailedInvalidDestinationException detailedInvalidDestinationException = new DetailedInvalidDestinationException(eReason, eCode, eExplanation, eAction, eInserts);
/* 506 */       detailedInvalidDestinationException.setStackTrace(stackTrace);
/*     */     } 
/* 508 */     if (exceptionType.equals(DetailedInvalidSelectorException.class.getName())) {
/* 509 */       DetailedInvalidSelectorException detailedInvalidSelectorException = new DetailedInvalidSelectorException(eReason, eCode, eExplanation, eAction, eInserts);
/* 510 */       detailedInvalidSelectorException.setStackTrace(stackTrace);
/*     */     } 
/* 512 */     if (exceptionType.equals(DetailedJMSException.class.getName())) {
/* 513 */       DetailedJMSException detailedJMSException = new DetailedJMSException(eReason, eCode, eExplanation, eAction, eInserts);
/* 514 */       detailedJMSException.setStackTrace(stackTrace);
/*     */     } 
/* 516 */     if (exceptionType.equals(DetailedJMSSecurityException.class.getName())) {
/* 517 */       DetailedJMSSecurityException detailedJMSSecurityException = new DetailedJMSSecurityException(eReason, eCode, eExplanation, eAction, eInserts);
/* 518 */       detailedJMSSecurityException.setStackTrace(stackTrace);
/*     */     } 
/* 520 */     if (exceptionType.equals(DetailedMessageEOFException.class.getName())) {
/* 521 */       DetailedMessageEOFException detailedMessageEOFException = new DetailedMessageEOFException(eReason, eCode, eExplanation, eAction, eInserts);
/* 522 */       detailedMessageEOFException.setStackTrace(stackTrace);
/*     */     } 
/* 524 */     if (exceptionType.equals(DetailedMessageFormatException.class.getName())) {
/* 525 */       DetailedMessageFormatException detailedMessageFormatException = new DetailedMessageFormatException(eReason, eCode, eExplanation, eAction, eInserts);
/* 526 */       detailedMessageFormatException.setStackTrace(stackTrace);
/*     */     } 
/* 528 */     if (exceptionType.equals(DetailedMessageNotReadableException.class.getName())) {
/* 529 */       DetailedMessageNotReadableException detailedMessageNotReadableException = new DetailedMessageNotReadableException(eReason, eCode, eExplanation, eAction, eInserts);
/* 530 */       detailedMessageNotReadableException.setStackTrace(stackTrace);
/*     */     } 
/* 532 */     if (exceptionType.equals(DetailedMessageNotWriteableException.class.getName())) {
/* 533 */       DetailedMessageNotWriteableException detailedMessageNotWriteableException = new DetailedMessageNotWriteableException(eReason, eCode, eExplanation, eAction, eInserts);
/* 534 */       detailedMessageNotWriteableException.setStackTrace(stackTrace);
/*     */     } 
/* 536 */     if (exceptionType.equals(DetailedResourceAllocationException.class.getName())) {
/* 537 */       DetailedResourceAllocationException detailedResourceAllocationException = new DetailedResourceAllocationException(eReason, eCode, eExplanation, eAction, eInserts);
/* 538 */       detailedResourceAllocationException.setStackTrace(stackTrace);
/*     */     } 
/* 540 */     if (exceptionType.equals(DetailedTransactionInProgressException.class.getName())) {
/* 541 */       DetailedTransactionInProgressException detailedTransactionInProgressException = new DetailedTransactionInProgressException(eReason, eCode, eExplanation, eAction, eInserts);
/*     */       
/* 543 */       detailedTransactionInProgressException.setStackTrace(stackTrace);
/*     */     } 
/* 545 */     if (exceptionType.equals(DetailedTransactionRolledBackException.class.getName())) {
/* 546 */       detailedTransactionRolledBackException = new DetailedTransactionRolledBackException(eReason, eCode, eExplanation, eAction, eInserts);
/*     */       
/* 548 */       detailedTransactionRolledBackException.setStackTrace(stackTrace);
/*     */     } 
/*     */     
/* 551 */     if (Trace.isOn) {
/* 552 */       Trace.exit(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "inflateFromString(String)", detailedTransactionRolledBackException);
/*     */     }
/*     */     
/* 555 */     return (Exception)detailedTransactionRolledBackException;
/*     */   }
/*     */   
/*     */   private String removeInvalidChars(String inputLine) {
/* 559 */     if (Trace.isOn) {
/* 560 */       Trace.entry(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "removeInvalidChars(String)", new Object[] { inputLine });
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
/* 572 */     if (inputLine == null) {
/* 573 */       if (Trace.isOn) {
/* 574 */         Trace.exit(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "removeInvalidChars(String)", "</null/>", 1);
/*     */       }
/*     */       
/* 577 */       return "</null/>";
/*     */     } 
/*     */     
/* 580 */     String result = inputLine;
/* 581 */     result = result.replaceAll("!", "</exclam/>");
/* 582 */     result = result.replaceAll("&", "</ampersand/>");
/* 583 */     result = result.replaceAll("=", "</equals/>");
/*     */     
/* 585 */     if (Trace.isOn) {
/* 586 */       Trace.exit(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "removeInvalidChars(String)", result, 2);
/*     */     }
/*     */     
/* 589 */     return result;
/*     */   }
/*     */   
/*     */   private String replaceInvalidChars(String inputLine) {
/* 593 */     if (Trace.isOn) {
/* 594 */       Trace.entry(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "replaceInvalidChars(String)", new Object[] { inputLine });
/*     */     }
/*     */     
/* 597 */     if (inputLine.equals("</null/>")) {
/* 598 */       if (Trace.isOn) {
/* 599 */         Trace.exit(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "replaceInvalidChars(String)", null, 1);
/*     */       }
/*     */       
/* 602 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 608 */     String result = inputLine;
/* 609 */     result = result.replaceAll("</exclam/>", "!");
/* 610 */     result = result.replaceAll("</ampersand/>", "&");
/* 611 */     result = result.replaceAll("</equals/>", "=");
/*     */     
/* 613 */     if (Trace.isOn) {
/* 614 */       Trace.exit(this, "com.ibm.msg.client.jms.JmsExceptionFactory", "replaceInvalidChars(String)", result, 2);
/*     */     }
/*     */     
/* 617 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\jms\JmsExceptionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */