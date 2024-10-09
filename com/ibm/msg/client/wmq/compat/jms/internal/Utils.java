/*     */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*     */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQC;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQMessage;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*     */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import java.util.GregorianCalendar;
/*     */ import javax.jms.JMSException;
/*     */ import javax.transaction.Transaction;
/*     */ import javax.transaction.TransactionManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Utils
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/Utils.java";
/*     */   static final int MQCA_BASE_Q_NAME = 2002;
/*     */   static final int MQCA_Q_MGR_NAME = 2015;
/*     */   static final int MQCA_Q_NAME = 2016;
/*     */   static final int MQCA_Q_MGR_IDENTIFIER = 2032;
/*     */   static final int MQCA_DEAD_LETTER_Q_NAME = 2006;
/*     */   static final int MQCA_BACKOUT_REQ_Q_NAME = 2019;
/*     */   static final int MQIA_DEFINITION_TYPE = 7;
/*     */   static final int MQIA_Q_TYPE = 20;
/*     */   static final int MQIA_BACKOUT_THRESHOLD = 22;
/*     */   private static final String CLASSNAME = "com.ibm.msg.client.wmq.compat.jms.internal.Utils";
/*     */   private static final int BUFSIZE = 128;
/*     */   
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/Utils.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   private static Object xactionManager = null;
/*     */   private static boolean wasNotDetected = false;
/*  97 */   private static final Object xactionManagerLock = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String[] listMatchingQueues(MQQueueManager qm, MQQueue replyQ, String filter) {
/* 117 */     if (Trace.isOn) {
/* 118 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "listMatchingQueues(MQQueueManager,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,String)", new Object[] { qm, replyQ, filter });
/*     */     }
/*     */ 
/*     */     
/* 122 */     String[] ret = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 130 */       MQQueue commandQueue = qm.accessQueue("SYSTEM.ADMIN.COMMAND.QUEUE", 8208);
/*     */ 
/*     */       
/* 133 */       PCF pcf = new PCF(18);
/* 134 */       pcf.addParameter(2016, filter);
/* 135 */       pcf.addParameter(20, 1);
/*     */ 
/*     */       
/* 138 */       MQMessage message = new MQMessage();
/* 139 */       message.replyToQueueName = replyQ.name;
/* 140 */       message.expiry = 5000;
/* 141 */       pcf.write(message);
/*     */ 
/*     */       
/* 144 */       commandQueue.put(message, new MQPutMessageOptions());
/* 145 */       commandQueue.close();
/*     */ 
/*     */       
/* 148 */       MQGetMessageOptions gmo = new MQGetMessageOptions();
/* 149 */       gmo.options = 8193;
/* 150 */       gmo.waitInterval = 5000;
/*     */       
/* 152 */       message.correlationId = message.messageId;
/* 153 */       message.messageId = MQC.MQMI_NONE;
/* 154 */       replyQ.get(message, gmo);
/*     */       
/* 156 */       if (message != null) {
/* 157 */         PCF replyPCF = new PCF(message);
/*     */         
/* 159 */         if (replyPCF.getParameterCount() == 1 && replyPCF.getParameterType(0) == 6)
/*     */         {
/* 161 */           ret = ((PCF.MQCFSL)replyPCF.getParameterAt(0)).getStrings();
/*     */         }
/*     */       } 
/* 164 */       if (Trace.isOn) {
/* 165 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "listMatchingQueues(MQQueueManager,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,String)", ret, 1);
/*     */       }
/*     */ 
/*     */       
/* 169 */       return ret;
/*     */     }
/* 171 */     catch (Exception e) {
/* 172 */       if (Trace.isOn) {
/* 173 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "listMatchingQueues(MQQueueManager,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,String)", e);
/*     */       }
/*     */ 
/*     */       
/* 177 */       if (Trace.isOn) {
/* 178 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "listMatchingQueues(MQQueueManager,com.ibm.msg.client.wmq.compat.base.internal.MQQueue,String)", null, 2);
/*     */       }
/*     */ 
/*     */       
/* 182 */       return null;
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
/*     */   private static String bytesToHex(byte[] bArray, int maxLength) {
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "bytesToHex(byte [ ],int)", new Object[] { bArray, 
/* 201 */             Integer.valueOf(maxLength) });
/*     */     }
/*     */     
/* 204 */     if (bArray == null) {
/* 205 */       String traceRet1 = "<null>";
/* 206 */       if (Trace.isOn) {
/* 207 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "bytesToHex(byte [ ],int)", traceRet1, 1);
/*     */       }
/*     */       
/* 210 */       return traceRet1;
/*     */     } 
/*     */ 
/*     */     
/* 214 */     int dumpLength = bArray.length;
/* 215 */     if (maxLength != -1 && maxLength < dumpLength) {
/* 216 */       dumpLength = maxLength;
/*     */     }
/*     */ 
/*     */     
/* 220 */     StringBuffer hex = new StringBuffer();
/*     */     
/* 222 */     for (int i = 0; i < dumpLength; i++) {
/* 223 */       int binByte = bArray[i];
/*     */       
/* 225 */       if (binByte < 0) {
/* 226 */         binByte += 256;
/*     */       }
/* 228 */       if (binByte < 16) {
/* 229 */         hex.append('0');
/*     */       }
/*     */       
/* 232 */       hex.append(Integer.toHexString(binByte).toUpperCase());
/*     */     } 
/*     */     
/* 235 */     String traceRet2 = hex.toString();
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "bytesToHex(byte [ ],int)", traceRet2, 2);
/*     */     }
/*     */     
/* 240 */     return traceRet2;
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
/*     */   protected static String bytesToHex(byte[] bArray) {
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "bytesToHex(byte [ ])", new Object[] { bArray });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 262 */     String traceRet1 = bytesToHex(bArray, -1);
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "bytesToHex(byte [ ])", traceRet1);
/*     */     }
/*     */     
/* 267 */     return traceRet1;
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
/*     */   protected static byte[] hexToBytes(String hex) throws JMSException {
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "hexToBytes(String)", new Object[] { hex });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 288 */     byte[] out = null;
/*     */     
/* 290 */     if (hex != null) {
/* 291 */       int len = hex.length();
/*     */       
/* 293 */       if (len % 2 != 0) {
/* 294 */         JMSException traceRet1 = ConfigEnvironment.newException("MQJMS1044");
/* 295 */         if (Trace.isOn) {
/* 296 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "hexToBytes(String)", (Throwable)traceRet1, 1);
/*     */         }
/*     */         
/* 299 */         throw traceRet1;
/*     */       } 
/*     */       
/* 302 */       len /= 2;
/* 303 */       out = new byte[len];
/*     */       
/* 305 */       for (int i = 0; i < len; i++) {
/* 306 */         int digit1 = Character.digit(hex.charAt(2 * i), 16);
/* 307 */         int digit2 = Character.digit(hex.charAt(2 * i + 1), 16);
/* 308 */         if (digit1 == -1 || digit2 == -1) {
/* 309 */           JMSException traceRet2 = ConfigEnvironment.newException("MQJMS1044");
/* 310 */           if (Trace.isOn) {
/* 311 */             Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "hexToBytes(String)", (Throwable)traceRet2, 2);
/*     */           }
/*     */           
/* 314 */           throw traceRet2;
/*     */         } 
/* 316 */         out[i] = (byte)(digit1 * 16 + digit2);
/*     */       } 
/*     */     } 
/*     */     
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "hexToBytes(String)", out);
/*     */     }
/* 323 */     return out;
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
/*     */   protected static String inquireString(MQManagedObject obj, int attr) {
/* 338 */     if (Trace.isOn) {
/* 339 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "inquireString(com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject,int)", new Object[] { obj, 
/*     */             
/* 341 */             Integer.valueOf(attr) });
/*     */     }
/* 343 */     String result = null;
/*     */     
/*     */     try {
/* 346 */       result = obj.getAttributeString(attr, 128).trim();
/*     */     }
/* 348 */     catch (Exception e) {
/* 349 */       if (Trace.isOn) {
/* 350 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "inquireString(com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject,int)", e);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 355 */     if (Trace.isOn) {
/* 356 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "inquireString(com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject,int)", result);
/*     */     }
/*     */ 
/*     */     
/* 360 */     return result;
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
/*     */   protected static int inquireInt(MQManagedObject obj, int attr) {
/* 375 */     if (Trace.isOn) {
/* 376 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "inquireInt(com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject,int)", new Object[] { obj, 
/*     */             
/* 378 */             Integer.valueOf(attr) });
/*     */     }
/* 380 */     int result = -1;
/*     */     
/*     */     try {
/* 383 */       int[] selectors = { attr };
/* 384 */       int[] intAttrs = new int[1];
/* 385 */       byte[] charAttrs = null;
/*     */       
/* 387 */       obj.inquire(selectors, intAttrs, charAttrs);
/* 388 */       result = intAttrs[0];
/*     */     
/*     */     }
/* 391 */     catch (Exception e) {
/* 392 */       if (Trace.isOn) {
/* 393 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "inquireInt(com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject,int)", e);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 398 */     if (Trace.isOn) {
/* 399 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "inquireInt(com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject,int)", 
/*     */           
/* 401 */           Integer.valueOf(result));
/*     */     }
/* 403 */     return result;
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
/*     */   protected static String padString(String str, int len) {
/* 421 */     if (Trace.isOn) {
/* 422 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "padString(String,int)", new Object[] { str, 
/* 423 */             Integer.valueOf(len) });
/*     */     }
/*     */     
/* 426 */     String result = null;
/*     */ 
/*     */     
/* 429 */     if (len > 0) {
/*     */ 
/*     */       
/* 432 */       int origLength = (str == null) ? 0 : str.length();
/*     */       
/* 434 */       if (origLength > len) {
/*     */         
/* 436 */         result = str.substring(0, len);
/* 437 */       } else if (origLength < len) {
/* 438 */         char[] resultArray = new char[len];
/* 439 */         Arrays.fill(resultArray, ' ');
/* 440 */         if (origLength > 0) {
/* 441 */           System.arraycopy(str.toCharArray(), 0, resultArray, 0, origLength);
/*     */         }
/* 443 */         result = new String(resultArray);
/*     */       } else {
/* 445 */         result = str;
/*     */       } 
/*     */     } 
/* 448 */     if (Trace.isOn) {
/* 449 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "padString(String,int)", result);
/*     */     }
/*     */     
/* 452 */     return result;
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
/*     */   protected static String getDate(GregorianCalendar dateTime) {
/*     */     String output;
/* 466 */     if (Trace.isOn) {
/* 467 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "getDate(GregorianCalendar)", new Object[] { dateTime });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 473 */     if (dateTime != null) {
/* 474 */       int year = dateTime.get(1);
/* 475 */       int month = dateTime.get(2) + 1;
/* 476 */       int day = dateTime.get(5);
/*     */       
/* 478 */       output = "00000000" + (year * 10000 + month * 100 + day);
/* 479 */       output = output.substring(output.length() - 8);
/*     */     } else {
/* 481 */       output = "        ";
/*     */     } 
/*     */     
/* 484 */     if (Trace.isOn) {
/* 485 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "getDate(GregorianCalendar)", output);
/*     */     }
/*     */     
/* 488 */     return output;
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
/*     */   protected static String getTime(GregorianCalendar dateTime) {
/*     */     String output;
/* 502 */     if (Trace.isOn) {
/* 503 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "getTime(GregorianCalendar)", new Object[] { dateTime });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 509 */     if (dateTime != null) {
/* 510 */       int hour = dateTime.get(11);
/* 511 */       int min = dateTime.get(12);
/* 512 */       int sec = dateTime.get(13);
/* 513 */       int hund = dateTime.get(14) / 10;
/*     */       
/* 515 */       output = "00000000" + (hour * 1000000 + min * 10000 + sec * 100 + hund);
/* 516 */       output = output.substring(output.length() - 8);
/*     */     } else {
/* 518 */       output = "        ";
/*     */     } 
/*     */     
/* 521 */     if (Trace.isOn) {
/* 522 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "getTime(GregorianCalendar)", output);
/*     */     }
/*     */     
/* 525 */     return output;
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
/*     */   protected static boolean isRRSTransactionInProgress() {
/* 540 */     if (Trace.isOn) {
/* 541 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 546 */     if (wasNotDetected) {
/* 547 */       if (Trace.isOn) {
/* 548 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "Previous call did not detect a WAS OS/390 TransactionManager", null);
/*     */       }
/* 550 */       if (Trace.isOn) {
/* 551 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()", 
/* 552 */             Boolean.valueOf(false), 1);
/*     */       }
/* 554 */       return false;
/*     */     } 
/*     */     
/* 557 */     synchronized (xactionManagerLock) {
/* 558 */       if (xactionManager == null) {
/*     */ 
/*     */         
/*     */         try {
/*     */           
/* 563 */           CSSystem.dynamicLoadClass("javax.transaction.TransactionManager", Utils.class);
/*     */         }
/* 565 */         catch (ClassNotFoundException cnfe) {
/* 566 */           if (Trace.isOn) {
/* 567 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()", cnfe, 1);
/*     */           }
/*     */           
/* 570 */           if (Trace.isOn) {
/* 571 */             Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "Class javax.transaction.TransactionManager not found; WAS OS/390 not detected", null);
/*     */           }
/*     */           
/* 574 */           wasNotDetected = true;
/* 575 */           if (Trace.isOn) {
/* 576 */             Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()", 
/* 577 */                 Boolean.valueOf(false), 2);
/*     */           }
/* 579 */           return false;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 585 */         String tmProperty = "com.ibm.ws390.jta.TransactionManager";
/* 586 */         PropertyStore.register(tmProperty, "com.ibm.ws390.tx.TransactionManagerImpl");
/* 587 */         String classname = PropertyStore.getStringProperty(tmProperty);
/*     */         
/* 589 */         Method getTxMgrMth = null;
/*     */ 
/*     */         
/*     */         try {
/* 593 */           Class<?> txMgrClass = CSSystem.dynamicLoadClass(classname, Utils.class);
/* 594 */           getTxMgrMth = txMgrClass.getMethod("getTransactionManager", (Class[])null);
/*     */         }
/* 596 */         catch (NullPointerException npe) {
/* 597 */           if (Trace.isOn) {
/* 598 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()", npe, 2);
/*     */           }
/*     */           
/* 601 */           wasNotDetected = true;
/* 602 */           if (Trace.isOn) {
/* 603 */             Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()", 
/* 604 */                 Boolean.valueOf(false), 3);
/*     */           }
/* 606 */           return false;
/*     */         
/*     */         }
/* 609 */         catch (ClassNotFoundException cnfe) {
/* 610 */           if (Trace.isOn) {
/* 611 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()", cnfe, 3);
/*     */           }
/*     */           
/* 614 */           if (Trace.isOn) {
/* 615 */             Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "Class " + classname + " not found; WAS OS/390 not detected", null);
/*     */           }
/* 617 */           wasNotDetected = true;
/* 618 */           if (Trace.isOn) {
/* 619 */             Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()", 
/* 620 */                 Boolean.valueOf(false), 4);
/*     */           }
/* 622 */           return false;
/*     */         }
/* 624 */         catch (NoSuchMethodException nsme) {
/* 625 */           if (Trace.isOn) {
/* 626 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()", nsme, 4);
/*     */           }
/*     */           
/* 629 */           if (Trace.isOn) {
/* 630 */             Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "Method getTransactionManager() not found on tm implementation", null);
/*     */           }
/*     */           
/* 633 */           wasNotDetected = true;
/* 634 */           if (Trace.isOn) {
/* 635 */             Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()", 
/* 636 */                 Boolean.valueOf(false), 5);
/*     */           }
/* 638 */           return false;
/*     */         } 
/*     */ 
/*     */         
/*     */         try {
/* 643 */           TransactionManager txMgr = (TransactionManager)getTxMgrMth.invoke(null, (Object[])null);
/* 644 */           if (txMgr == null) {
/* 645 */             if (Trace.isOn) {
/* 646 */               Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "No TransactionManager present. Reverting to local transaction behaviour for this JVM", null);
/*     */             }
/*     */             
/* 649 */             wasNotDetected = true;
/* 650 */             if (Trace.isOn) {
/* 651 */               Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()", 
/* 652 */                   Boolean.valueOf(false), 6);
/*     */             }
/* 654 */             return false;
/*     */           } 
/*     */           
/* 657 */           xactionManager = txMgr;
/*     */         }
/* 659 */         catch (InvocationTargetException ite) {
/* 660 */           if (Trace.isOn) {
/* 661 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()", ite, 5);
/*     */           }
/*     */           
/* 664 */           if (Trace.isOn) {
/* 665 */             Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "Error when finding TransactionManager", null);
/*     */           }
/*     */           
/* 668 */           wasNotDetected = true;
/* 669 */           if (Trace.isOn) {
/* 670 */             Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()", 
/* 671 */                 Boolean.valueOf(false), 7);
/*     */           }
/* 673 */           return false;
/*     */         }
/* 675 */         catch (IllegalAccessException iae) {
/* 676 */           if (Trace.isOn) {
/* 677 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()", iae, 6);
/*     */           }
/*     */           
/* 680 */           if (Trace.isOn) {
/* 681 */             Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "Error when finding TransactionManager", null);
/*     */           }
/*     */           
/* 684 */           wasNotDetected = true;
/* 685 */           if (Trace.isOn) {
/* 686 */             Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()", 
/* 687 */                 Boolean.valueOf(false), 8);
/*     */           }
/* 689 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 698 */     Transaction tran = null;
/*     */     try {
/* 700 */       TransactionManager txMgr = (TransactionManager)xactionManager;
/* 701 */       tran = txMgr.getTransaction();
/*     */     }
/* 703 */     catch (Exception e) {
/* 704 */       if (Trace.isOn) {
/* 705 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()", e, 7);
/*     */       }
/*     */       
/* 708 */       if (Trace.isOn) {
/* 709 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "Unexpected exception while getting Transaction", null);
/*     */       }
/* 711 */       if (Trace.isOn) {
/* 712 */         Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()", 
/* 713 */             Boolean.valueOf(false), 9);
/*     */       }
/* 715 */       return false;
/*     */     } 
/*     */     
/* 718 */     if (Trace.isOn) {
/* 719 */       if (tran == null) {
/* 720 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "No global transaction present.", null);
/*     */       } else {
/* 722 */         Trace.traceData("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "Global transaction active on this thread.", null);
/*     */       } 
/*     */     }
/*     */     
/* 726 */     boolean traceRet2 = (tran != null);
/* 727 */     if (Trace.isOn) {
/* 728 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Utils", "isRRSTransactionInProgress()", 
/* 729 */           Boolean.valueOf(traceRet2), 10);
/*     */     }
/* 731 */     return traceRet2;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */