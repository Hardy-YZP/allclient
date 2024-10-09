/*      */ package com.ibm.msg.client.wmq.compat.jms.internal;
/*      */ 
/*      */ import com.ibm.mq.MQException;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.provider.ProviderExceptionListener;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQGetMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQManagedObject;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQMsg2;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQPutMessageOptions;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueue;
/*      */ import com.ibm.msg.client.wmq.compat.base.internal.MQQueueManager;
/*      */ import java.io.PrintWriter;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.GregorianCalendar;
/*      */ import javax.jms.IllegalStateException;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.TransactionInProgressException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Cleanup
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/Cleanup.java";
/*      */   private static final String CLSNAME = "Cleanup";
/*      */   private static final String reportQueueName = "SYSTEM.JMS.REPORT.QUEUE";
/*      */   
/*      */   static {
/*   64 */     if (Trace.isOn) {
/*   65 */       Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/jms/internal/Cleanup.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   77 */   private static long assumeOld = 60000L;
/*      */ 
/*      */   
/*      */   private static final int CAT_SUCC_DEREG = 1;
/*      */ 
/*      */   
/*      */   private static final int CAT_SUCC_STOP = 2;
/*      */ 
/*      */   
/*      */   private static final int CAT_SUCCESS = 3;
/*      */ 
/*      */   
/*      */   private static final int CAT_UNSUCCESS = 4;
/*      */ 
/*      */   
/*      */   private static final int CAT_PCF_RESPONSE = 5;
/*      */ 
/*      */   
/*      */   private static final int CAT_UNSUCCESS_DEREG = 6;
/*      */   
/*      */   private static final int CAT_UNRECOGNISED = 99;
/*      */   
/*      */   private static final int RESP_DONE = 0;
/*      */   
/*      */   private static final int RESP_TRY_LATER = 1;
/*      */   
/*      */   private static final int RESP_FAILED = 2;
/*      */   
/*  105 */   private static final byte[] dummyArray = new byte[] { 77, 81, 32, 74, 77, 83, 32, 67, 108, 101, 97, 110, 117, 112, 32, 84, 101, 109, 112, 111, 114, 97, 114, 121, 32, 77, 101, 115, 115, 97, 103, 101 };
/*      */   
/*  107 */   private static final ByteBuffer dummyMessageText = ByteBuffer.wrap(dummyArray);
/*      */ 
/*      */ 
/*      */   
/*  111 */   private static final String[] defaultNDQueues = new String[] { "SYSTEM.JMS.ND.SUBSCRIBER.QUEUE", "SYSTEM.JMS.ND.CC.SUBSCRIBER.QUEUE" };
/*      */ 
/*      */ 
/*      */   
/*  115 */   private static final byte[] blankMessageId = new byte[24];
/*      */   
/*      */   private static final int CLEANUP_PROPERTY_NOT_SET = -1;
/*      */   
/*      */   private static final int CLEANUP_PROPERTY_ERROR = -2;
/*  120 */   static int cleanupProperty = -1;
/*      */ 
/*      */   
/*  123 */   static String invalidCleanupValue = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean endCleanup = false;
/*      */ 
/*      */ 
/*      */   
/*  132 */   private ProviderExceptionListener exceptionListener = null;
/*      */ 
/*      */ 
/*      */   
/*  136 */   private PrintWriter printWriter = null;
/*      */ 
/*      */   
/*  139 */   private Object runLock = new Object();
/*      */ 
/*      */   
/*      */   private boolean isRunning = false;
/*      */ 
/*      */   
/*  145 */   private long cleanupInterval = 3600000L;
/*  146 */   private int cleanupLevel = 1;
/*      */ 
/*      */   
/*      */   static {
/*  150 */     if (Trace.isOn) {
/*  151 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "static()");
/*      */     }
/*      */     
/*  154 */     String cleanupPropertyName = "com.ibm.mq.jms.cleanup";
/*  155 */     PropertyStore.register(cleanupPropertyName, "");
/*  156 */     String levelName = PropertyStore.getStringProperty(cleanupPropertyName);
/*      */     
/*  158 */     if (levelName != null) {
/*      */ 
/*      */       
/*  161 */       levelName = levelName.toUpperCase();
/*  162 */       if (levelName.equals("NONE")) {
/*  163 */         cleanupProperty = 0;
/*  164 */       } else if (levelName.equals("SAFE")) {
/*  165 */         cleanupProperty = 1;
/*  166 */       } else if (levelName.equals("STRONG")) {
/*  167 */         cleanupProperty = 2;
/*      */       } else {
/*  169 */         cleanupProperty = -2;
/*  170 */         invalidCleanupValue = levelName;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  175 */     if (cleanupProperty == -1) {
/*  176 */       cleanupProperty = 1;
/*      */     }
/*      */     
/*  179 */     if (Trace.isOn) {
/*  180 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "static()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Cleanup() {
/*  188 */     if (Trace.isOn) {
/*  189 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "<init>()");
/*      */     }
/*      */     
/*  192 */     if (Trace.isOn) {
/*  193 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "<init>()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCleanupInterval(long interval) throws JMSException {
/*  206 */     if (Trace.isOn) {
/*  207 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "setCleanupInterval(long)", "setter", 
/*  208 */           Long.valueOf(interval));
/*      */     }
/*      */     
/*      */     try {
/*  212 */       if (interval < 0L) {
/*      */         
/*  214 */         JMSException je = ConfigEnvironment.newException("MQJMS1006", "cleanupInterval", String.valueOf(interval));
/*  215 */         if (Trace.isOn) {
/*  216 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "setCleanupInterval(long)", (Throwable)je, 1);
/*      */         }
/*      */         
/*  219 */         throw je;
/*      */       } 
/*      */       
/*  222 */       synchronized (this.runLock) {
/*  223 */         if (this.isRunning && (
/*  224 */           getCleanupLevel() == 3 || getCleanupLevel() == 4)) {
/*  225 */           String msg = ConfigEnvironment.getMessage("MQJMS3043");
/*  226 */           IllegalStateException illegalStateException = new IllegalStateException(msg);
/*  227 */           if (Trace.isOn) {
/*  228 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "setCleanupInterval(long)", (Throwable)illegalStateException, 2);
/*      */           }
/*      */           
/*  231 */           throw illegalStateException;
/*      */         } 
/*      */ 
/*      */         
/*  235 */         this.cleanupInterval = interval;
/*      */         
/*  237 */         if (this.isRunning) {
/*  238 */           this.runLock.notifyAll();
/*      */         }
/*      */       }
/*      */     
/*  242 */     } catch (JMSException je) {
/*  243 */       if (Trace.isOn) {
/*  244 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "setCleanupInterval(long)", (Throwable)je);
/*      */       }
/*      */       
/*  247 */       if (Trace.isOn) {
/*  248 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "setCleanupInterval(long)", (Throwable)je, 3);
/*      */       }
/*      */       
/*  251 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getCleanupInterval() {
/*  263 */     if (Trace.isOn) {
/*  264 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "getCleanupInterval()", "getter", 
/*  265 */           Long.valueOf(this.cleanupInterval));
/*      */     }
/*  267 */     return this.cleanupInterval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCleanupLevel(int levelP) throws JMSException {
/*  285 */     if (Trace.isOn) {
/*  286 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "setCleanupLevel(int)", "setter", 
/*  287 */           Integer.valueOf(levelP));
/*      */     }
/*      */     
/*  290 */     int level = levelP; try {
/*      */       JMSException je;
/*  292 */       switch (level) {
/*      */         case -1:
/*  294 */           if (cleanupProperty == -2) {
/*      */ 
/*      */             
/*  297 */             JMSException e = ConfigEnvironment.newException("MQJMS1006", "cleanupLevel", invalidCleanupValue);
/*  298 */             if (Trace.isOn) {
/*  299 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "setCleanupLevel(int)", (Throwable)e, 1);
/*      */             }
/*      */             
/*  302 */             throw e;
/*      */           } 
/*  304 */           level = cleanupProperty;
/*      */           break;
/*      */         
/*      */         case 0:
/*      */         case 1:
/*      */         case 2:
/*      */         case 3:
/*      */         case 4:
/*      */           break;
/*      */         
/*      */         default:
/*  315 */           je = ConfigEnvironment.newException("MQJMS1006", "cleanupLevel", String.valueOf(level));
/*  316 */           if (Trace.isOn) {
/*  317 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "setCleanupLevel(int)", (Throwable)je, 2);
/*      */           }
/*      */           
/*  320 */           throw je;
/*      */       } 
/*      */       
/*  323 */       synchronized (this.runLock) {
/*  324 */         if (this.isRunning && (
/*  325 */           level == 3 || level == 4) && getCleanupInterval() > 0L) {
/*  326 */           String msg = ConfigEnvironment.getMessage("MQJMS3043");
/*  327 */           IllegalStateException illegalStateException = new IllegalStateException(msg);
/*  328 */           if (Trace.isOn) {
/*  329 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "setCleanupLevel(int)", (Throwable)illegalStateException, 3);
/*      */           }
/*      */           
/*  332 */           throw illegalStateException;
/*      */         } 
/*      */ 
/*      */         
/*  336 */         this.cleanupLevel = level;
/*      */         
/*  338 */         if (this.isRunning) {
/*  339 */           this.runLock.notifyAll();
/*      */         }
/*      */       }
/*      */     
/*  343 */     } catch (JMSException je) {
/*  344 */       if (Trace.isOn) {
/*  345 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "setCleanupLevel(int)", (Throwable)je);
/*      */       }
/*      */       
/*  348 */       if (Trace.isOn) {
/*  349 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "setCleanupLevel(int)", (Throwable)je, 4);
/*      */       }
/*      */       
/*  352 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCleanupLevel() {
/*  365 */     if (Trace.isOn) {
/*  366 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "getCleanupLevel()", "getter", 
/*  367 */           Integer.valueOf(this.cleanupLevel));
/*      */     }
/*  369 */     return this.cleanupLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void run(MQConnection conn) {
/*  388 */     if (Trace.isOn) {
/*  389 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "run(MQConnection)", new Object[] { conn });
/*      */     }
/*      */     
/*      */     try {
/*      */       int level;
/*      */       
/*      */       boolean end;
/*      */       
/*      */       MQQueueManager qmgr;
/*      */       
/*  399 */       synchronized (this.runLock) {
/*      */ 
/*      */ 
/*      */         
/*  403 */         qmgr = conn.getInitialQM();
/*  404 */         if (qmgr == null) {
/*  405 */           qmgr = conn.createQMNonXA();
/*      */         }
/*      */         
/*  408 */         this.isRunning = true;
/*  409 */         level = getCleanupLevel();
/*  410 */         long cleanupInterval = getCleanupInterval();
/*      */ 
/*      */ 
/*      */         
/*  414 */         if (level == 0) {
/*  415 */           String msg = ConfigEnvironment.getMessage("MQJMS3044");
/*  416 */           IllegalStateException traceRet1 = new IllegalStateException(msg);
/*  417 */           if (Trace.isOn) {
/*  418 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "run(MQConnection)", (Throwable)traceRet1, 1);
/*      */           }
/*      */           
/*  421 */           throw traceRet1;
/*      */         } 
/*      */         
/*  424 */         if ((level == 3 || level == 4) && cleanupInterval > 0L) {
/*  425 */           String msg = ConfigEnvironment.getMessage("MQJMS3043");
/*  426 */           IllegalStateException traceRet2 = new IllegalStateException(msg);
/*  427 */           if (Trace.isOn) {
/*  428 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "run(MQConnection)", (Throwable)traceRet2, 2);
/*      */           }
/*      */           
/*  431 */           throw traceRet2;
/*      */         } 
/*      */         
/*  434 */         this.endCleanup = false;
/*  435 */         end = false;
/*      */       } 
/*      */ 
/*      */       
/*  439 */       while (!end)
/*      */       {
/*      */         
/*  442 */         long timeStarted = System.currentTimeMillis();
/*  443 */         if (Trace.isOn) {
/*  444 */           Trace.traceData(this, "Cleanup started at " + timeStarted, null);
/*      */         }
/*  446 */         if (this.printWriter != null) {
/*      */           
/*  448 */           GregorianCalendar gc = new GregorianCalendar();
/*  449 */           String date = Utils.getDate(gc);
/*  450 */           String time = Utils.getTime(gc);
/*  451 */           String key = "MQJMS3052";
/*  452 */           String messageText = ConfigEnvironment.getMessage(key, time, date);
/*  453 */           this.printWriter.println(messageText);
/*  454 */           this.printWriter.flush();
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*  460 */           performCleanup(qmgr, level);
/*  461 */           if (this.printWriter != null)
/*      */           {
/*  463 */             GregorianCalendar gc = new GregorianCalendar();
/*  464 */             String date = Utils.getDate(gc);
/*  465 */             String time = Utils.getTime(gc);
/*  466 */             String key = "MQJMS3053";
/*  467 */             String messageText = ConfigEnvironment.getMessage(key, time, date);
/*  468 */             this.printWriter.println(messageText);
/*  469 */             this.printWriter.flush();
/*      */           }
/*      */         
/*      */         }
/*  473 */         catch (JMSException je) {
/*  474 */           if (Trace.isOn) {
/*  475 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "run(MQConnection)", (Throwable)je, 1);
/*      */           }
/*      */ 
/*      */           
/*  479 */           if (Trace.isOn) {
/*  480 */             Trace.traceData(this, "run - informing ProviderExceptionListener", null);
/*      */           }
/*  482 */           ProviderExceptionListener el = this.exceptionListener;
/*  483 */           if (el != null) {
/*  484 */             el.onException(je, true);
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  490 */         boolean cont = false;
/*  491 */         synchronized (this.runLock) {
/*  492 */           while (!cont) {
/*  493 */             long cleanupInterval = getCleanupInterval();
/*  494 */             level = getCleanupLevel();
/*      */             
/*  496 */             if (this.endCleanup || cleanupInterval == 0L) {
/*  497 */               cont = true;
/*  498 */               end = true; continue;
/*      */             } 
/*  500 */             long waitFor = timeStarted + cleanupInterval - System.currentTimeMillis();
/*  501 */             if (waitFor > 0L) {
/*  502 */               if (Trace.isOn) {
/*  503 */                 Trace.traceData(this, "Sleeping for " + waitFor, null);
/*      */               }
/*  505 */               if (this.printWriter != null) {
/*      */ 
/*      */                 
/*  508 */                 String key = "MQJMS3054";
/*      */ 
/*      */                 
/*  511 */                 long waitTimeInSeconds = waitFor / 1000L;
/*  512 */                 long waitTimeInMinutes = waitTimeInSeconds / 60L;
/*  513 */                 String insert = String.valueOf(waitTimeInMinutes);
/*  514 */                 String messageText = ConfigEnvironment.getMessage(key, insert);
/*  515 */                 this.printWriter.println(messageText);
/*  516 */                 this.printWriter.flush();
/*      */               } 
/*      */               try {
/*  519 */                 this.runLock.wait(waitFor);
/*      */               }
/*  521 */               catch (InterruptedException ie) {
/*  522 */                 if (Trace.isOn) {
/*  523 */                   Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "run(MQConnection)", ie, 2);
/*      */                 }
/*      */               } 
/*      */               continue;
/*      */             } 
/*  528 */             cont = true;
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*  535 */     } catch (JMSException je) {
/*  536 */       if (Trace.isOn) {
/*  537 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "run(MQConnection)", (Throwable)je, 3);
/*      */       }
/*      */ 
/*      */       
/*  541 */       if (Trace.isOn) {
/*  542 */         Trace.traceData(this, "run - informing ProviderExceptionListener", null);
/*      */       }
/*  544 */       ProviderExceptionListener el = this.exceptionListener;
/*  545 */       if (el != null) {
/*  546 */         el.onException(je, true);
/*      */       }
/*      */     } finally {
/*      */       
/*  550 */       if (Trace.isOn) {
/*  551 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "run(MQConnection)");
/*      */       }
/*      */       
/*  554 */       synchronized (this.runLock) {
/*  555 */         this.isRunning = false;
/*  556 */         this.runLock.notifyAll();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  561 */     if (Trace.isOn) {
/*  562 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "run(MQConnection)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void stop() {
/*  573 */     if (Trace.isOn) {
/*  574 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "stop()");
/*      */     }
/*      */     
/*  577 */     synchronized (this.runLock) {
/*  578 */       if (this.isRunning && !this.endCleanup) {
/*  579 */         this.endCleanup = true;
/*  580 */         this.runLock.notifyAll();
/*      */       } 
/*  582 */       while (this.isRunning) {
/*  583 */         if (Trace.isOn) {
/*  584 */           Trace.traceData(this, "Cleanup still running: wait", null);
/*      */         }
/*      */         try {
/*  587 */           this.runLock.wait();
/*      */         }
/*  589 */         catch (InterruptedException ie) {
/*  590 */           if (Trace.isOn) {
/*  591 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "stop()", ie);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  598 */     if (Trace.isOn) {
/*  599 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "stop()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExceptionListener(ProviderExceptionListener el) {
/*  614 */     if (Trace.isOn) {
/*  615 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "setExceptionListener(ProviderExceptionListener)", "setter", el);
/*      */     }
/*      */     
/*  618 */     this.exceptionListener = el;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ProviderExceptionListener getExceptionListener() {
/*  628 */     if (Trace.isOn) {
/*  629 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "getExceptionListener()", "getter", this.exceptionListener);
/*      */     }
/*      */     
/*  632 */     return this.exceptionListener;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isRunning() {
/*  642 */     if (Trace.isOn) {
/*  643 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "isRunning()", "getter", 
/*  644 */           Boolean.valueOf(this.isRunning));
/*      */     }
/*  646 */     return this.isRunning;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void cleanup(MQConnection conn) throws JMSException {
/*  663 */     if (Trace.isOn) {
/*  664 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "cleanup(MQConnection)", new Object[] { conn });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  672 */       MQQueueManager qmgr = conn.getInitialQM();
/*  673 */       if (qmgr == null) {
/*  674 */         qmgr = conn.createQMNonXA();
/*      */       }
/*      */       
/*  677 */       int cleanupLevel = getCleanupLevel();
/*  678 */       if (cleanupLevel == 0) {
/*  679 */         String msg = ConfigEnvironment.getMessage("MQJMS3044");
/*  680 */         IllegalStateException traceRet1 = new IllegalStateException(msg);
/*  681 */         if (Trace.isOn) {
/*  682 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "cleanup(MQConnection)", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/*  685 */         throw traceRet1;
/*      */       } 
/*      */       
/*  688 */       performCleanup(qmgr, cleanupLevel);
/*      */     }
/*  690 */     catch (JMSException je) {
/*  691 */       if (Trace.isOn) {
/*  692 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "cleanup(MQConnection)", (Throwable)je);
/*      */       }
/*      */       
/*  695 */       if (Trace.isOn) {
/*  696 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "cleanup(MQConnection)", (Throwable)je, 2);
/*      */       }
/*      */       
/*  699 */       throw je;
/*      */     } 
/*      */     
/*  702 */     if (Trace.isOn) {
/*  703 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "cleanup(MQConnection)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void performCleanup(MQQueueManager qmgr, int level) throws JMSException {
/*  720 */     if (Trace.isOn) {
/*  721 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", new Object[] { qmgr, 
/*  722 */             Integer.valueOf(level) });
/*      */     }
/*      */     
/*  725 */     MQQueue reportQueue = null;
/*  726 */     long currentTime = 0L;
/*      */     
/*      */     try {
/*      */       JMSException je;
/*      */       
/*  731 */       if (level == 0) {
/*  732 */         if (Trace.isOn) {
/*  733 */           Trace.traceData(this, "Cleanup level is NONE; skipping cleanup", null);
/*      */         }
/*  735 */         if (Trace.isOn) {
/*  736 */           Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */ 
/*      */       
/*  745 */       int openOptions = 8216;
/*  746 */       switch (level) {
/*      */         case 1:
/*      */         case 2:
/*  749 */           openOptions |= 0x2;
/*      */           break;
/*      */         case 3:
/*      */         case 4:
/*  753 */           openOptions |= 0x4;
/*      */           break;
/*      */         
/*      */         default:
/*  757 */           je = ConfigEnvironment.newException("MQJMS1016", "Bad level " + level + " encountered in Cleanup");
/*      */ 
/*      */           
/*  760 */           if (Trace.isOn) {
/*  761 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)je, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  767 */           throw je;
/*      */       } 
/*      */       
/*      */       try {
/*  771 */         reportQueue = qmgr.accessQueue("SYSTEM.JMS.REPORT.QUEUE", openOptions);
/*      */       }
/*  773 */       catch (MQException mqe) {
/*  774 */         if (Trace.isOn) {
/*  775 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)mqe, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  780 */         if (mqe.reasonCode == 2042) {
/*  781 */           if ((openOptions & 0x2) == 2) {
/*      */             
/*  783 */             JMSException jMSException2 = ConfigEnvironment.newException("MQJMS3045", "SYSTEM.JMS.REPORT.QUEUE");
/*  784 */             jMSException2.setLinkedException((Exception)mqe);
/*  785 */             if (Trace.isOn) {
/*  786 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)jMSException2, 2);
/*      */             }
/*      */             
/*  789 */             throw jMSException2;
/*      */           } 
/*      */ 
/*      */           
/*  793 */           JMSException jMSException1 = ConfigEnvironment.newException("MQJMS3046", "SYSTEM.JMS.REPORT.QUEUE");
/*  794 */           jMSException1.setLinkedException((Exception)mqe);
/*  795 */           if (Trace.isOn) {
/*  796 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)jMSException1, 3);
/*      */           }
/*      */           
/*  799 */           throw jMSException1;
/*      */         } 
/*      */ 
/*      */         
/*  803 */         JMSException jMSException = ConfigEnvironment.newException("MQJMS2008", "SYSTEM.JMS.REPORT.QUEUE");
/*  804 */         jMSException.setLinkedException((Exception)mqe);
/*  805 */         if (Trace.isOn) {
/*  806 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)jMSException, 4);
/*      */         }
/*      */         
/*  809 */         throw jMSException;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  819 */         MQMsg2 dummyMsg = new MQMsg2();
/*  820 */         dummyMsg.setMessageData(dummyMessageText);
/*  821 */         dummyMsg.setCharacterSet(1208);
/*  822 */         dummyMsg.setPersistence(0);
/*  823 */         MQPutMessageOptions dummyPMO = new MQPutMessageOptions();
/*  824 */         dummyPMO.options = 8194;
/*  825 */         reportQueue.putMsg2(dummyMsg, dummyPMO);
/*      */         
/*  827 */         currentTime = dummyMsg.getPutTimeMillis();
/*      */         
/*  829 */         qmgr.backout();
/*      */       }
/*  831 */       catch (MQException mqe) {
/*  832 */         if (Trace.isOn) {
/*  833 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)mqe, 2);
/*      */         }
/*      */ 
/*      */         
/*  837 */         JMSException jMSException = ConfigEnvironment.newException("MQJMS2007");
/*  838 */         jMSException.setLinkedException((Exception)mqe);
/*  839 */         if (Trace.isOn) {
/*  840 */           Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)jMSException, 5);
/*      */         }
/*      */         
/*  843 */         throw jMSException;
/*      */       } 
/*  845 */       if (Trace.isOn) {
/*  846 */         Trace.traceData(this, "Current MQ clock is " + currentTime, null);
/*      */       }
/*      */ 
/*      */       
/*  850 */       boolean done = false;
/*  851 */       MQMsg2 msg = new MQMsg2();
/*  852 */       MQGetMessageOptions gmo = new MQGetMessageOptions();
/*  853 */       boolean first = true;
/*  854 */       while (!done) {
/*  855 */         int category = 0;
/*      */ 
/*      */         
/*  858 */         gmo.options = 8704;
/*  859 */         if (first) {
/*  860 */           gmo.options |= 0x10;
/*      */         } else {
/*  862 */           gmo.options |= 0x20;
/*      */         } 
/*  864 */         gmo.matchOptions = 0;
/*      */         
/*      */         try {
/*  867 */           reportQueue.getMsg2(msg, gmo);
/*      */         }
/*  869 */         catch (MQException mqe) {
/*  870 */           if (Trace.isOn) {
/*  871 */             Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)mqe, 3);
/*      */           }
/*      */           
/*  874 */           if (mqe.reasonCode == 2033) {
/*      */             
/*  876 */             done = true;
/*      */           }
/*      */           else {
/*      */             
/*  880 */             JMSException jMSException = ConfigEnvironment.newException("MQJMS2002");
/*  881 */             jMSException.setLinkedException((Exception)mqe);
/*  882 */             if (Trace.isOn) {
/*  883 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)jMSException, 6);
/*      */             }
/*      */             
/*  886 */             throw jMSException;
/*      */           } 
/*      */         } 
/*      */         
/*  890 */         if (!done) {
/*  891 */           JMSException jMSException; if (Trace.isOn) {
/*  892 */             Trace.traceData(this, "Found message on report queue", null);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  897 */           long age = 0L;
/*      */           try {
/*  899 */             age = currentTime - msg.getPutTimeMillis();
/*      */           }
/*  901 */           catch (MQException mqe) {
/*  902 */             if (Trace.isOn) {
/*  903 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)mqe, 4);
/*      */             }
/*      */ 
/*      */             
/*  907 */             String detail = "Unexpected error from getPutTimeMillis (" + mqe + ")";
/*      */             
/*  909 */             JMSException jMSException1 = ConfigEnvironment.newException("MQJMS1016", detail);
/*  910 */             jMSException1.setLinkedException((Exception)mqe);
/*  911 */             if (Trace.isOn) {
/*  912 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)jMSException1, 7);
/*      */             }
/*      */             
/*  915 */             throw jMSException1;
/*      */           } 
/*      */ 
/*      */           
/*  919 */           MQBrokerMessage mqbm = null;
/*      */           try {
/*  921 */             mqbm = MQBrokerMessage.fromMessage(msg);
/*      */           }
/*  923 */           catch (JMSException jMSException1) {
/*  924 */             if (Trace.isOn) {
/*  925 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)jMSException1, 5);
/*      */             }
/*      */ 
/*      */             
/*  929 */             if (Trace.isOn) {
/*  930 */               Trace.traceData(this, "ProviderMessage is not a broker message", null);
/*      */             }
/*  932 */             category = 99;
/*      */           } 
/*      */           
/*      */           try {
/*  936 */             if (mqbm != null)
/*      */             {
/*  938 */               String command = mqbm.get("MQPSCommand");
/*  939 */               String compCode = mqbm.get("MQPSCompCode");
/*  940 */               String reasonCode = mqbm.get("MQPSReason");
/*  941 */               String reasonText = mqbm.get("MQPSReasonText");
/*      */               
/*  943 */               if (Trace.isOn) {
/*  944 */                 Trace.traceData(this, "ProviderMessage has command '" + command + "'", null);
/*  945 */                 Trace.traceData(this, "ProviderMessage has completion code '" + compCode + "'", null);
/*  946 */                 Trace.traceData(this, "ProviderMessage has reason code '" + reasonCode + "'", null);
/*      */ 
/*      */                 
/*  949 */                 Trace.traceData(this, "ProviderMessage has reason text '" + reasonText + "'", null);
/*      */               } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  966 */               if ("DeregSub".equals(command) || reasonText.equals("MQRCCF_NOT_REGISTERED") || reasonText
/*  967 */                 .equals(Integer.toString(3073))) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  974 */                 if (compCode != null && compCode.equals("0")) {
/*      */                   
/*  976 */                   if (Trace.isOn) {
/*  977 */                     Trace.traceData(this, "ProviderMessage is response to dereg sub w. full data", null);
/*      */                   }
/*  979 */                   category = 1;
/*      */                 } else {
/*  981 */                   if (Trace.isOn) {
/*  982 */                     Trace.traceData(this, "ProviderMessage is response to unsuccessful dereg sub", null);
/*      */                   }
/*  984 */                   category = 6;
/*      */                 } 
/*  986 */               } else if ("RegSub".equals(command)) {
/*      */ 
/*      */                 
/*  989 */                 if (compCode != null && compCode.equals("0")) {
/*      */ 
/*      */                   
/*  992 */                   boolean poro = mqbm.isOptionSet("MQPSRegOpts", "PubOnReqOnly");
/*  993 */                   if (poro) {
/*      */                     
/*  995 */                     if (Trace.isOn) {
/*  996 */                       Trace.traceData(this, "ProviderMessage is response to reg sub w. poro", null);
/*      */                     }
/*  998 */                     category = 2;
/*      */                   
/*      */                   }
/*      */                   else {
/*      */                     
/* 1003 */                     if (Trace.isOn) {
/* 1004 */                       Trace.traceData(this, "ProviderMessage appears to be response to reg sub, setting to remove message and subs (CAT_SUCC_STOP)", null);
/*      */                     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/* 1018 */                     category = 2;
/*      */                   } 
/*      */                 } else {
/* 1021 */                   if (Trace.isOn) {
/* 1022 */                     Trace.traceData(this, "ProviderMessage is response to unsuccessful reg sub", null);
/*      */                   }
/* 1024 */                   category = 4;
/*      */                 }
/*      */               
/*      */               }
/* 1028 */               else if (command == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1033 */                 if (Trace.isOn) {
/* 1034 */                   Trace.traceData(this, "ProviderMessage has no command field", null);
/*      */                 }
/* 1036 */                 if (compCode == null) {
/* 1037 */                   category = 99;
/* 1038 */                 } else if (compCode.equals("0")) {
/* 1039 */                   category = 3;
/*      */                 } else {
/* 1041 */                   category = 4;
/*      */                 }
/*      */               
/*      */               } else {
/*      */                 
/* 1046 */                 if (Trace.isOn) {
/* 1047 */                   Trace.traceData(this, "ProviderMessage has unrecognised command field", null);
/*      */                 }
/* 1049 */                 category = 99;
/*      */               }
/*      */             
/*      */             }
/*      */           
/* 1054 */           } catch (JMSException jMSException1) {
/* 1055 */             if (Trace.isOn) {
/* 1056 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)jMSException1, 6);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1062 */             String detail = "Error dealing with MQBrokerMessage (" + jMSException1.toString() + ")";
/* 1063 */             JMSException je2 = ConfigEnvironment.newException("MQJMS1016", detail);
/* 1064 */             je2.setLinkedException((Exception)jMSException1);
/* 1065 */             if (Trace.isOn) {
/* 1066 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)je2, 8);
/*      */             }
/*      */             
/* 1069 */             throw je2;
/*      */           } 
/*      */           
/*      */           try {
/* 1073 */             if (category == 99)
/*      */             {
/*      */               
/* 1076 */               if (msg.getFormat().equals("MQADMIN ") && msg.getMessageType() == 2) {
/* 1077 */                 category = 5;
/*      */               }
/*      */             }
/*      */           }
/* 1081 */           catch (MQException mqe) {
/* 1082 */             if (Trace.isOn) {
/* 1083 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)mqe, 7);
/*      */             }
/*      */ 
/*      */             
/* 1087 */             String detail = "Could not fetch header data from MQMsg2 (" + mqe + ")";
/* 1088 */             JMSException jMSException1 = ConfigEnvironment.newException("MQJMS1016", detail);
/* 1089 */             jMSException1.setLinkedException((Exception)mqe);
/* 1090 */             if (Trace.isOn) {
/* 1091 */               Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)jMSException1, 9);
/*      */             }
/*      */             
/* 1094 */             throw jMSException1;
/*      */           } 
/*      */ 
/*      */           
/* 1098 */           boolean sufficientlyOld = (age >= assumeOld);
/*      */           
/* 1100 */           if (Trace.isOn) {
/* 1101 */             Trace.traceData(this, "ProviderMessage is of category " + category, null);
/* 1102 */             if (sufficientlyOld) {
/* 1103 */               Trace.traceData(this, "ProviderMessage is sufficiently old", null);
/*      */             } else {
/* 1105 */               Trace.traceData(this, "ProviderMessage is not sufficiently old", null);
/*      */             } 
/*      */           } 
/*      */           
/* 1109 */           boolean process = false;
/* 1110 */           switch (level) {
/*      */             case 1:
/* 1112 */               if (sufficientlyOld && (category == 1 || category == 2 || category == 3 || category == 5)) {
/* 1113 */                 process = true; break;
/*      */               } 
/* 1115 */               process = false;
/*      */               break;
/*      */ 
/*      */             
/*      */             case 2:
/* 1120 */               if (sufficientlyOld) {
/* 1121 */                 process = true; break;
/*      */               } 
/* 1123 */               process = false;
/*      */               break;
/*      */ 
/*      */             
/*      */             case 3:
/*      */             case 4:
/* 1129 */               process = true;
/*      */               break;
/*      */ 
/*      */             
/*      */             default:
/* 1134 */               jMSException = ConfigEnvironment.newException("MQJMS1016", "Bad level " + level + " encountered in Cleanup");
/* 1135 */               if (Trace.isOn) {
/* 1136 */                 Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)jMSException, 10);
/*      */               }
/*      */               
/* 1139 */               throw jMSException;
/*      */           } 
/*      */ 
/*      */           
/* 1143 */           boolean removeMessage = false;
/* 1144 */           int result = 1;
/* 1145 */           if (process) {
/* 1146 */             result = processMessage(qmgr, msg, mqbm, category);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1157 */           if (level == 4) {
/* 1158 */             removeMessage = true;
/* 1159 */           } else if (result == 0) {
/* 1160 */             removeMessage = true;
/* 1161 */           } else if (category == 6) {
/* 1162 */             removeMessage = true;
/*      */           } else {
/* 1164 */             removeMessage = false;
/*      */           } 
/*      */           
/* 1167 */           if (removeMessage) {
/* 1168 */             if (Trace.isOn) {
/* 1169 */               Trace.traceData(this, "Removing message", null);
/*      */             }
/*      */             
/*      */             try {
/* 1173 */               gmo.options = 8512;
/*      */ 
/*      */               
/* 1176 */               reportQueue.getMsg2(msg, gmo, 1);
/*      */             }
/* 1178 */             catch (MQException mqe) {
/* 1179 */               if (Trace.isOn) {
/* 1180 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)mqe, 8);
/*      */               }
/*      */               
/* 1183 */               if (mqe.reasonCode != 2079) {
/*      */ 
/*      */ 
/*      */                 
/* 1187 */                 JMSException jMSException1 = ConfigEnvironment.newException("MQJMS1016");
/* 1188 */                 jMSException1.setLinkedException((Exception)mqe);
/* 1189 */                 if (Trace.isOn) {
/* 1190 */                   Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)jMSException1, 11);
/*      */                 }
/*      */                 
/* 1193 */                 throw jMSException1;
/*      */               } 
/*      */             } 
/* 1196 */           } else if (Trace.isOn) {
/* 1197 */             Trace.traceData(this, "Leaving message on queue", null);
/*      */           } 
/*      */ 
/*      */           
/* 1201 */           first = false;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1207 */       if (level == 4) {
/* 1208 */         cleanupNonDur(qmgr, reportQueue);
/*      */       
/*      */       }
/*      */     }
/* 1212 */     catch (JMSException je) {
/* 1213 */       if (Trace.isOn) {
/* 1214 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)je, 9);
/*      */       }
/*      */       
/* 1217 */       if (Trace.isOn) {
/* 1218 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)je, 12);
/*      */       }
/*      */       
/* 1221 */       throw je;
/*      */     } finally {
/*      */       
/* 1224 */       if (Trace.isOn) {
/* 1225 */         Trace.finallyBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)");
/*      */       }
/*      */       
/*      */       try {
/* 1229 */         if (reportQueue != null) {
/* 1230 */           reportQueue.close();
/*      */         }
/*      */       }
/* 1233 */       catch (MQException mqe) {
/* 1234 */         if (Trace.isOn) {
/* 1235 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", (Throwable)mqe, 10);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1241 */     if (Trace.isOn) {
/* 1242 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "performCleanup(MQQueueManager,int)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int processMessage(MQQueueManager qmgr, MQMsg2 msg, MQBrokerMessage mqbm, int category) throws JMSException {
/* 1262 */     if (Trace.isOn) {
/* 1263 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "processMessage(MQQueueManager,MQMsg2,MQBrokerMessage,int)", new Object[] { qmgr, msg, mqbm, 
/*      */             
/* 1265 */             Integer.valueOf(category) });
/*      */     }
/*      */     
/* 1268 */     int out = 2;
/*      */     try {
/*      */       JMSException je;
/* 1271 */       if (Trace.isOn) {
/* 1272 */         Trace.traceData(this, "Category " + category + " message", null);
/*      */       }
/* 1274 */       switch (category) {
/*      */         case 1:
/*      */         case 2:
/* 1277 */           if (Trace.isOn) {
/* 1278 */             Trace.traceData(this, "Cleaning up any orphaned message", null);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1283 */           if (mqbm.isOptionSet("MQPSRegOpts", "CorrelAsId")) {
/*      */             
/*      */             try {
/* 1286 */               String qmName = mqbm.get("MQPSQMgrName");
/* 1287 */               String qName = mqbm.get("MQPSQName");
/* 1288 */               String correlId = mqbm.get("MQPSCorrelId");
/* 1289 */               byte[] correlIdAsBytes = Utils.hexToBytes(correlId);
/*      */ 
/*      */               
/* 1292 */               if (qmName != null && qmName.equals(qmgr.name) && qName != null && correlId != null && correlIdAsBytes != null && correlIdAsBytes.length == 24) {
/*      */ 
/*      */                 
/* 1295 */                 if (Trace.isOn) {
/* 1296 */                   Trace.traceData(this, "Removing messages with correlId " + correlId + " from " + qName, null);
/*      */                 }
/*      */                 
/* 1299 */                 removeMessages(qmgr, qName, correlIdAsBytes);
/* 1300 */                 out = 0; break;
/*      */               } 
/* 1302 */               if (Trace.isOn) {
/* 1303 */                 Trace.traceData(this, "Response message has surprising information - ignoring", null);
/*      */               }
/* 1305 */               out = 2;
/*      */             
/*      */             }
/* 1308 */             catch (JMSException jMSException) {
/* 1309 */               if (Trace.isOn) {
/* 1310 */                 Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "processMessage(MQQueueManager,MQMsg2,MQBrokerMessage,int)", (Throwable)jMSException, 1);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/* 1315 */               out = 2;
/*      */             } 
/*      */             break;
/*      */           } 
/*      */           try {
/* 1320 */             String qmName = mqbm.get("MQPSQMgrName");
/* 1321 */             String qName = mqbm.get("MQPSQName");
/* 1322 */             String topic = mqbm.get("MQPSTopic");
/*      */             
/* 1324 */             if (qmName != null && qmName.trim().equals(qmgr.name.trim()) && qName != null) {
/* 1325 */               if (Trace.isOn) {
/* 1326 */                 Trace.traceData(this, "Removing dynamic queue " + qName, null);
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1333 */               if (topic != null && topic.trim().equals("JMS:SYS:Unknown") && qName.trim().equals("SYSTEM.JMS.REPORT.QUEUE")) {
/* 1334 */                 if (Trace.isOn) {
/* 1335 */                   Trace.traceData(this, "Found durable subscriber unsubscribe request response, flagging to remove", null);
/*      */                 }
/* 1337 */                 out = 0;
/*      */                 break;
/*      */               } 
/* 1340 */               if (Trace.isOn) {
/* 1341 */                 Trace.traceData(this, "Removing dynamic queue " + qName, null);
/*      */               }
/* 1343 */               removeDynamicQueue(qmgr, qName);
/* 1344 */               out = 0;
/*      */               break;
/*      */             } 
/* 1347 */             if (Trace.isOn) {
/* 1348 */               Trace.traceData(this, "Reponse message has surprising information - ignoring", null);
/*      */             }
/* 1350 */             out = 2;
/*      */           
/*      */           }
/* 1353 */           catch (TransactionInProgressException tipe) {
/* 1354 */             if (Trace.isOn) {
/* 1355 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "processMessage(MQQueueManager,MQMsg2,MQBrokerMessage,int)", (Throwable)tipe, 2);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 1360 */             if (Trace.isOn) {
/* 1361 */               Trace.traceData(this, "Will retry this cleanup later", null);
/*      */             }
/* 1363 */             out = 1;
/*      */           }
/* 1365 */           catch (JMSException jMSException) {
/* 1366 */             if (Trace.isOn) {
/* 1367 */               Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "processMessage(MQQueueManager,MQMsg2,MQBrokerMessage,int)", (Throwable)jMSException, 3);
/*      */             }
/*      */ 
/*      */             
/* 1371 */             out = 2;
/*      */           } 
/*      */           break;
/*      */ 
/*      */         
/*      */         case 3:
/*      */         case 4:
/*      */         case 5:
/*      */         case 99:
/* 1380 */           if (Trace.isOn) {
/* 1381 */             Trace.traceData(this, "Nothing to do", null);
/*      */           }
/* 1383 */           out = 0;
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/* 1388 */           je = ConfigEnvironment.newException("MQJMS1016");
/* 1389 */           if (Trace.isOn) {
/* 1390 */             Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "processMessage(MQQueueManager,MQMsg2,MQBrokerMessage,int)", (Throwable)je, 1);
/*      */           }
/*      */           
/* 1393 */           throw je;
/*      */       } 
/*      */       
/* 1396 */       if (Trace.isOn) {
/* 1397 */         Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "processMessage(MQQueueManager,MQMsg2,MQBrokerMessage,int)", 
/* 1398 */             Integer.valueOf(out));
/*      */       }
/* 1400 */       return out;
/*      */     }
/* 1402 */     catch (JMSException je) {
/* 1403 */       if (Trace.isOn) {
/* 1404 */         Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "processMessage(MQQueueManager,MQMsg2,MQBrokerMessage,int)", (Throwable)je, 4);
/*      */       }
/*      */       
/* 1407 */       if (Trace.isOn) {
/* 1408 */         Trace.throwing(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "processMessage(MQQueueManager,MQMsg2,MQBrokerMessage,int)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1411 */       throw je;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void removeMessages(MQQueueManager qmgr, String qName, byte[] correlId) throws JMSException {
/* 1428 */     if (Trace.isOn) {
/* 1429 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(MQQueueManager,String,byte [ ])", new Object[] { qmgr, qName, correlId });
/*      */     }
/*      */ 
/*      */     
/* 1433 */     MQQueue q = null;
/*      */     try {
/*      */       try {
/* 1436 */         q = qmgr.accessQueue(qName, 8193);
/*      */       }
/* 1438 */       catch (MQException mqe) {
/* 1439 */         if (Trace.isOn) {
/* 1440 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(MQQueueManager,String,byte [ ])", (Throwable)mqe, 1);
/*      */         }
/*      */         
/* 1443 */         if (mqe.reasonCode == 2085) {
/*      */           
/* 1445 */           if (Trace.isOn) {
/* 1446 */             Trace.traceData("Cleanup", "Queue doesn't exist. No messages to remove.", null);
/*      */           }
/* 1448 */           if (Trace.isOn) {
/* 1449 */             Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(MQQueueManager,String,byte [ ])", 1);
/*      */           }
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 1456 */         JMSException je = ConfigEnvironment.newException("MQJMS3014", qName);
/* 1457 */         je.setLinkedException((Exception)mqe);
/* 1458 */         if (Trace.isOn) {
/* 1459 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(MQQueueManager,String,byte [ ])", (Throwable)je, 1);
/*      */         }
/*      */         
/* 1462 */         throw je;
/*      */       } 
/*      */       
/* 1465 */       removeMessages(q, correlId);
/*      */     }
/* 1467 */     catch (JMSException je) {
/* 1468 */       if (Trace.isOn) {
/* 1469 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(MQQueueManager,String,byte [ ])", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1472 */       if (Trace.isOn) {
/* 1473 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(MQQueueManager,String,byte [ ])", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1476 */       throw je;
/*      */     } finally {
/*      */       
/* 1479 */       if (Trace.isOn) {
/* 1480 */         Trace.finallyBlock("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(MQQueueManager,String,byte [ ])");
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/* 1485 */         if (q != null) {
/* 1486 */           q.close();
/*      */         }
/*      */       }
/* 1489 */       catch (MQException mqe) {
/* 1490 */         if (Trace.isOn) {
/* 1491 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(MQQueueManager,String,byte [ ])", (Throwable)mqe, 3);
/*      */         }
/*      */         
/* 1494 */         if (Trace.isOn) {
/* 1495 */           Trace.traceData("Cleanup", "Ignoring exception from queue.close()", null);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1500 */     if (Trace.isOn) {
/* 1501 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(MQQueueManager,String,byte [ ])", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void removeMessages(MQQueue queue, byte[] correlId) throws JMSException {
/* 1519 */     if (Trace.isOn) {
/* 1520 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,byte [ ])", new Object[] { queue, correlId });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1526 */       MQMsg2 junk = new MQMsg2();
/* 1527 */       junk.setCorrelationId(correlId);
/*      */       
/* 1529 */       MQGetMessageOptions gmo = new MQGetMessageOptions();
/* 1530 */       gmo.options = 8260;
/* 1531 */       gmo.matchOptions = 2;
/*      */       
/* 1533 */       int count = 0;
/* 1534 */       boolean done = false;
/* 1535 */       while (!done)
/*      */       {
/*      */         
/*      */         try {
/* 1539 */           queue.getMsg2(junk, gmo, 1);
/* 1540 */           count++;
/*      */         }
/* 1542 */         catch (MQException mqe) {
/* 1543 */           if (Trace.isOn) {
/* 1544 */             Trace.data("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,byte [ ])", "Caught expected exception at catch index 1", mqe);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1550 */           if (mqe.reasonCode == 2079) {
/*      */             
/* 1552 */             if (Trace.isOn) {
/* 1553 */               Trace.traceData("Cleanup", "Received truncated message", null);
/*      */             }
/* 1555 */             count++; continue;
/* 1556 */           }  if (mqe.reasonCode == 2033) {
/*      */             
/* 1558 */             if (Trace.isOn) {
/* 1559 */               Trace.traceData("Cleanup", "Removed " + count + " messages", null);
/*      */             }
/* 1561 */             if (Trace.isOn) {
/* 1562 */               Trace.traceData("Cleanup", "Got 2033 - no more messages", null);
/*      */             }
/* 1564 */             done = true;
/*      */             
/*      */             continue;
/*      */           } 
/* 1568 */           JMSException je = ConfigEnvironment.newException("MQJMS2002");
/* 1569 */           je.setLinkedException((Exception)mqe);
/* 1570 */           if (Trace.isOn) {
/* 1571 */             Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,byte [ ])", (Throwable)je, 1);
/*      */           }
/*      */ 
/*      */           
/* 1575 */           throw je;
/*      */         }
/*      */       
/*      */       }
/*      */     
/* 1580 */     } catch (JMSException je) {
/* 1581 */       if (Trace.isOn) {
/* 1582 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,byte [ ])", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1585 */       if (Trace.isOn) {
/* 1586 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,byte [ ])", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1589 */       throw je;
/*      */     } 
/*      */     
/* 1592 */     if (Trace.isOn) {
/* 1593 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(com.ibm.msg.client.wmq.compat.base.internal.MQQueue,byte [ ])");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void removeMessages(MQQueue queue) throws JMSException {
/* 1608 */     if (Trace.isOn) {
/* 1609 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", new Object[] { queue });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1615 */       MQMsg2 junk = new MQMsg2();
/*      */       
/* 1617 */       MQGetMessageOptions gmo = new MQGetMessageOptions();
/* 1618 */       gmo.options = 8260;
/*      */       
/* 1620 */       int count = 0;
/* 1621 */       boolean done = false;
/* 1622 */       while (!done) {
/*      */         try {
/* 1624 */           junk.setMessageId(blankMessageId);
/* 1625 */           junk.setCorrelationId(blankMessageId);
/*      */ 
/*      */           
/* 1628 */           queue.getMsg2(junk, gmo, 1);
/* 1629 */           count++;
/*      */         }
/* 1631 */         catch (MQException mqe) {
/* 1632 */           if (Trace.isOn) {
/* 1633 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)mqe, 1);
/*      */           }
/*      */           
/* 1636 */           if (mqe.reasonCode == 2079) {
/*      */             
/* 1638 */             count++; continue;
/* 1639 */           }  if (mqe.reasonCode == 2033) {
/*      */             
/* 1641 */             if (Trace.isOn) {
/* 1642 */               Trace.traceData("Cleanup", "Removed " + count + " messages", null);
/*      */             }
/* 1644 */             if (Trace.isOn) {
/* 1645 */               Trace.traceData("Cleanup", "Got 2033 - no more messages", null);
/*      */             }
/* 1647 */             done = true;
/*      */             continue;
/*      */           } 
/* 1650 */           if (Trace.isOn) {
/* 1651 */             Trace.traceData("Cleanup", "Removed " + count + " messages", null);
/*      */           }
/*      */           
/* 1654 */           JMSException je = ConfigEnvironment.newException("MQJMS2002");
/* 1655 */           je.setLinkedException((Exception)mqe);
/* 1656 */           if (Trace.isOn) {
/* 1657 */             Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)je, 1);
/*      */           }
/*      */           
/* 1660 */           throw je;
/*      */         }
/*      */       
/*      */       }
/*      */     
/* 1665 */     } catch (JMSException je) {
/* 1666 */       if (Trace.isOn) {
/* 1667 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1670 */       if (Trace.isOn) {
/* 1671 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)je, 2);
/*      */       }
/*      */       
/* 1674 */       throw je;
/*      */     } 
/*      */     
/* 1677 */     if (Trace.isOn) {
/* 1678 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeMessages(com.ibm.msg.client.wmq.compat.base.internal.MQQueue)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void removeDynamicQueue(MQQueueManager qmgr, String qName) throws JMSException {
/* 1696 */     if (Trace.isOn) {
/* 1697 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeDynamicQueue(MQQueueManager,String)", new Object[] { qmgr, qName });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1702 */       MQQueue q = null;
/*      */       
/*      */       try {
/* 1705 */         q = qmgr.accessQueue(qName, 8196);
/*      */       }
/* 1707 */       catch (MQException mqe) {
/* 1708 */         if (Trace.isOn) {
/* 1709 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeDynamicQueue(MQQueueManager,String)", (Throwable)mqe, 1);
/*      */         }
/*      */         
/* 1712 */         if (mqe.reasonCode == 2042)
/*      */         
/*      */         { 
/*      */           try {
/*      */ 
/*      */             
/* 1718 */             if (Trace.isOn) {
/* 1719 */               Trace.traceData("Cleanup", "Dynamic Queue " + qName + " in use (mqrc2042), removing anyway.", null);
/*      */             }
/*      */             
/* 1722 */             q = qmgr.accessQueue(qName, 8194);
/*      */           }
/* 1724 */           catch (MQException me) {
/* 1725 */             if (Trace.isOn) {
/* 1726 */               Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeDynamicQueue(MQQueueManager,String)", (Throwable)me, 2);
/*      */             }
/*      */             
/* 1729 */             if (me.reasonCode == 2085 || me.reasonCode == 2052) {
/*      */               
/* 1731 */               if (Trace.isOn) {
/* 1732 */                 Trace.traceData("Cleanup", "Queue doesn't exist.", null);
/*      */               }
/* 1734 */               if (Trace.isOn) {
/* 1735 */                 Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeDynamicQueue(MQQueueManager,String)", 1);
/*      */               }
/*      */ 
/*      */               
/*      */               return;
/*      */             } 
/*      */             
/* 1742 */             JMSException je = ConfigEnvironment.newException("MQJMS3014", qName);
/* 1743 */             je.setLinkedException((Exception)me);
/* 1744 */             if (Trace.isOn) {
/* 1745 */               Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeDynamicQueue(MQQueueManager,String)", (Throwable)je, 1);
/*      */             }
/*      */             
/* 1748 */             throw je;
/*      */           }  }
/* 1750 */         else { if (mqe.reasonCode == 2085 || mqe.reasonCode == 2052) {
/*      */             
/* 1752 */             if (Trace.isOn) {
/* 1753 */               Trace.traceData("Cleanup", "Queue doesn't exist.", null);
/*      */             }
/* 1755 */             if (Trace.isOn) {
/* 1756 */               Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeDynamicQueue(MQQueueManager,String)", 2);
/*      */             }
/*      */             
/*      */             return;
/*      */           } 
/*      */           
/* 1762 */           JMSException je = ConfigEnvironment.newException("MQJMS3014", qName);
/* 1763 */           je.setLinkedException((Exception)mqe);
/* 1764 */           if (Trace.isOn) {
/* 1765 */             Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeDynamicQueue(MQQueueManager,String)", (Throwable)je, 2);
/*      */           }
/*      */           
/* 1768 */           throw je; }
/*      */       
/*      */       } 
/*      */ 
/*      */       
/* 1773 */       q.closeOptions = 2;
/*      */       
/*      */       try {
/* 1776 */         q.close();
/*      */       }
/* 1778 */       catch (MQException mqe) {
/* 1779 */         if (Trace.isOn) {
/* 1780 */           Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeDynamicQueue(MQQueueManager,String)", (Throwable)mqe, 3);
/*      */         }
/*      */         
/* 1783 */         if (Trace.isOn) {
/* 1784 */           Trace.traceData("Cleanup", "DELETE_PURGE failed", null);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1801 */         q.closeOptions = 0;
/*      */         try {
/* 1803 */           q.close();
/*      */         }
/* 1805 */         catch (MQException mqe2) {
/* 1806 */           if (Trace.isOn) {
/* 1807 */             Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeDynamicQueue(MQQueueManager,String)", (Throwable)mqe2, 4);
/*      */           }
/*      */ 
/*      */           
/* 1811 */           if (Trace.isOn) {
/* 1812 */             Trace.traceData("Cleanup", "Ignoring MQException on close", null);
/*      */           }
/*      */         } 
/*      */         
/* 1816 */         if (mqe.reasonCode == 2045) {
/*      */ 
/*      */           
/* 1819 */           String msg = ConfigEnvironment.getMessage("MQJMS0010");
/* 1820 */           TransactionInProgressException transactionInProgressException = new TransactionInProgressException(msg);
/* 1821 */           transactionInProgressException.setLinkedException((Exception)mqe);
/* 1822 */           if (Trace.isOn) {
/* 1823 */             Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeDynamicQueue(MQQueueManager,String)", (Throwable)transactionInProgressException, 3);
/*      */           }
/*      */           
/* 1826 */           throw transactionInProgressException;
/*      */         } 
/*      */ 
/*      */         
/* 1830 */         JMSException je = ConfigEnvironment.newException("MQJMS3017", qName);
/* 1831 */         je.setLinkedException((Exception)mqe);
/* 1832 */         if (Trace.isOn) {
/* 1833 */           Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeDynamicQueue(MQQueueManager,String)", (Throwable)je, 4);
/*      */         }
/*      */         
/* 1836 */         throw je;
/*      */       }
/*      */     
/* 1839 */     } catch (JMSException je) {
/* 1840 */       if (Trace.isOn) {
/* 1841 */         Trace.catchBlock("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeDynamicQueue(MQQueueManager,String)", (Throwable)je, 5);
/*      */       }
/*      */       
/* 1844 */       if (Trace.isOn) {
/* 1845 */         Trace.throwing("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeDynamicQueue(MQQueueManager,String)", (Throwable)je, 5);
/*      */       }
/*      */       
/* 1848 */       throw je;
/*      */     } 
/*      */     
/* 1851 */     if (Trace.isOn) {
/* 1852 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "removeDynamicQueue(MQQueueManager,String)", 3);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String resolveQMName(MQConnection conn) throws JMSException {
/* 1866 */     if (Trace.isOn) {
/* 1867 */       Trace.entry("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "resolveQMName(MQConnection)", new Object[] { conn });
/*      */     }
/*      */ 
/*      */     
/* 1871 */     String resolvedName = null;
/* 1872 */     MQQueueManager qmgr = null;
/*      */ 
/*      */ 
/*      */     
/* 1876 */     qmgr = conn.getInitialQM();
/* 1877 */     if (qmgr == null) {
/* 1878 */       qmgr = conn.createQMNonXA();
/*      */     }
/* 1880 */     if (qmgr != null) {
/* 1881 */       resolvedName = Utils.inquireString((MQManagedObject)qmgr, 2015);
/*      */     }
/* 1883 */     if (Trace.isOn) {
/* 1884 */       Trace.exit("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "resolveQMName(MQConnection)", resolvedName);
/*      */     }
/*      */     
/* 1887 */     return resolvedName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void cleanupNonDur(MQQueueManager qmgr, MQQueue reportQueue) {
/* 1919 */     if (Trace.isOn) {
/* 1920 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "cleanupNonDur(MQQueueManager,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", new Object[] { qmgr, reportQueue });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1925 */     String[] queues = Utils.listMatchingQueues(qmgr, reportQueue, "SYSTEM.JMS.ND.*");
/*      */     
/* 1927 */     if (queues == null) {
/* 1928 */       if (Trace.isOn) {
/* 1929 */         Trace.traceData(this, "listMatchingQueues failed; only clearing out known SYSTEM.JMS.ND.* queues", null);
/*      */       }
/*      */       
/* 1932 */       queues = defaultNDQueues;
/*      */     } 
/*      */     
/* 1935 */     for (int i = 0; i < queues.length; i++) {
/* 1936 */       String qName = queues[i];
/* 1937 */       MQQueue queue = null;
/* 1938 */       if (Trace.isOn)
/* 1939 */         Trace.traceData(this, "Dealing with queue " + qName, null);  try {
/*      */         String trimmed; int beginIndex; String last16;
/*      */         char[] char16;
/*      */         int j;
/* 1943 */         queue = qmgr.accessQueue(qName, 8228);
/* 1944 */         if (Trace.isOn) {
/* 1945 */           Trace.traceData(this, "Opened queue for input and inquire", null);
/*      */         }
/*      */         
/* 1948 */         int defType = queue.getDefinitionType();
/* 1949 */         if (Trace.isOn) {
/* 1950 */           Trace.traceData(this, "Definition type is " + defType, null);
/*      */         }
/*      */         
/* 1953 */         boolean delete = false;
/* 1954 */         boolean error = false;
/* 1955 */         switch (defType) {
/*      */           case 1:
/*      */           case 3:
/* 1958 */             delete = false;
/*      */             break;
/*      */           case 2:
/* 1961 */             trimmed = qName.trim();
/* 1962 */             beginIndex = trimmed.length() - 16;
/* 1963 */             if (beginIndex < 0) {
/* 1964 */               delete = false; break;
/*      */             } 
/* 1966 */             last16 = trimmed.substring(beginIndex);
/* 1967 */             char16 = last16.toCharArray();
/* 1968 */             delete = true;
/* 1969 */             for (j = 0; j < 16 && delete; j++) {
/* 1970 */               if ((char16[j] < '0' || char16[j] > '9') && (char16[j] < 'a' || char16[j] > 'z') && (char16[j] < 'A' || char16[j] > 'Z')) {
/* 1971 */                 delete = false;
/*      */               }
/*      */             } 
/*      */             break;
/*      */           
/*      */           default:
/* 1977 */             if (Trace.isOn) {
/* 1978 */               Trace.traceData(this, "WARNING: Unrecognised definition type", null);
/*      */             }
/* 1980 */             error = true;
/* 1981 */             delete = false;
/*      */             break;
/*      */         } 
/*      */         
/* 1985 */         if (!error && !delete) {
/* 1986 */           if (Trace.isOn) {
/* 1987 */             Trace.traceData(this, "Removing messages from queue", null);
/*      */           }
/* 1989 */           removeMessages(queue);
/* 1990 */         } else if (!error && delete) {
/* 1991 */           if (Trace.isOn) {
/* 1992 */             Trace.traceData(this, "Setting queue.closeOptions to DELETE_PURGE", null);
/*      */           }
/* 1994 */           queue.closeOptions = 2;
/*      */         }
/*      */       
/* 1997 */       } catch (MQException mqe) {
/* 1998 */         if (Trace.isOn) {
/* 1999 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "cleanupNonDur(MQQueueManager,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)mqe, 1);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2004 */         if (Trace.isOn) {
/* 2005 */           Trace.traceData(this, "Ignoring exception while performing NONDUR cleanup:", null);
/*      */         }
/*      */       }
/* 2008 */       catch (JMSException je) {
/* 2009 */         if (Trace.isOn) {
/* 2010 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "cleanupNonDur(MQQueueManager,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)je, 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 2015 */         if (Trace.isOn) {
/* 2016 */           Trace.traceData(this, "Ignoring exception while performing NONDUR cleanup:", null);
/*      */         }
/*      */       } 
/*      */       
/*      */       try {
/* 2021 */         if (queue != null) {
/* 2022 */           queue.close();
/*      */         }
/*      */       }
/* 2025 */       catch (MQException mqe) {
/* 2026 */         if (Trace.isOn) {
/* 2027 */           Trace.catchBlock(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "cleanupNonDur(MQQueueManager,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)", (Throwable)mqe, 3);
/*      */         }
/*      */ 
/*      */         
/* 2031 */         if (Trace.isOn) {
/* 2032 */           Trace.traceData(this, "Ignoring exception on queue.close", null);
/*      */         }
/*      */       } 
/* 2035 */       queue = null;
/*      */     } 
/*      */     
/* 2038 */     if (Trace.isOn) {
/* 2039 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "cleanupNonDur(MQQueueManager,com.ibm.msg.client.wmq.compat.base.internal.MQQueue)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPrintWriter(PrintWriter pw) {
/* 2057 */     if (Trace.isOn) {
/* 2058 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "setPrintWriter(PrintWriter)", "setter", pw);
/*      */     }
/*      */     
/* 2061 */     if (Trace.isOn) {
/* 2062 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", "setPrintWriter(PrintWriter)", "setter", pw);
/*      */     }
/*      */     
/* 2065 */     this.printWriter = pw;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\jms\internal\Cleanup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */