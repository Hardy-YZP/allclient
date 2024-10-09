/*      */ package com.ibm.mq.jms;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.cssystem.CSSystem;
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import com.ibm.msg.client.jms.internal.JmsConnectionImpl;
/*      */ import com.ibm.msg.client.provider.ProviderExceptionListener;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.nio.charset.Charset;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import javax.jms.ExceptionListener;
/*      */ import javax.jms.JMSException;
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
/*      */   extends MQConnectionFactory
/*      */   implements Runnable
/*      */ {
/*      */   static final String copyright_notice = "Licensed Materials - Property of IBM 5724-I07 (c) Copyright IBM Corp. 2004, 2015 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
/*      */   private static final long serialVersionUID = -3271226297625651418L;
/*      */   private Object cleanup;
/*      */   private transient ExceptionListener exceptionListener;
/*      */   private transient PrintWriter printWriter;
/*      */   private static Method cleanupInternalMethod;
/*      */   private static Method cleanupLevelMethod;
/*      */   private static Method isRunningMethod;
/*      */   private static Method setExceptionListenerMethod;
/*      */   private static Method stopMethod;
/*      */   private static Class<?> cleanupClass;
/*      */   private static Method cleanupMethod;
/*      */   private static Method setPrintWriterMethod;
/*      */   private static Method runMethod;
/*      */   
/*      */   static {
/*   61 */     if (Trace.isOn) {
/*   62 */       Trace.data("com.ibm.mq.jms.Cleanup", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/Cleanup.java");
/*      */     }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  109 */     if (Trace.isOn) {
/*  110 */       Trace.entry("com.ibm.mq.jms.Cleanup", "static()");
/*      */     }
/*      */     
/*      */     try {
/*  114 */       cleanupClass = CSSystem.dynamicLoadClass("com.ibm.msg.client.wmq.compat.jms.internal.Cleanup", Cleanup.class);
/*      */       
/*  116 */       cleanupInternalMethod = cleanupClass.getMethod("setCleanupInterval", new Class[] { long.class });
/*  117 */       cleanupLevelMethod = cleanupClass.getMethod("setCleanupLevel", new Class[] { int.class });
/*  118 */       isRunningMethod = cleanupClass.getMethod("isRunning", (Class[])null);
/*  119 */       setExceptionListenerMethod = cleanupClass.getMethod("setExceptionListener", new Class[] { ProviderExceptionListener.class });
/*  120 */       stopMethod = cleanupClass.getMethod("stop", (Class[])null);
/*      */       
/*  122 */       Class<?> mqConnection = CSSystem.dynamicLoadClass("com.ibm.msg.client.wmq.compat.jms.internal.MQConnection", Cleanup.class);
/*      */       
/*  124 */       cleanupMethod = cleanupClass.getMethod("cleanup", new Class[] { mqConnection });
/*  125 */       runMethod = cleanupClass.getMethod("run", new Class[] { mqConnection });
/*  126 */       setPrintWriterMethod = cleanupClass.getMethod("setPrintWriter", new Class[] { PrintWriter.class });
/*      */     
/*      */     }
/*  129 */     catch (Exception e) {
/*  130 */       if (Trace.isOn) {
/*  131 */         Trace.catchBlock("com.ibm.mq.jms.Cleanup", "static()", e);
/*      */       }
/*  133 */       HashMap<String, Exception> data = new HashMap<>();
/*  134 */       data.put("exception", e);
/*  135 */       Trace.ffst("com.ibm.mq.jms.Cleanup", "<clinit>", "XF009001", data, null);
/*      */     } 
/*      */     
/*  138 */     if (Trace.isOn) {
/*  139 */       Trace.exit("com.ibm.mq.jms.Cleanup", "static()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Cleanup() {
/*  147 */     if (Trace.isOn) {
/*  148 */       Trace.entry(this, "com.ibm.mq.jms.Cleanup", "<init>()");
/*      */     }
/*      */     
/*  151 */     this.exceptionListener = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  159 */       this.cleanup = cleanupClass.newInstance();
/*      */       
/*  161 */       long cleanupInternal = getCleanupInterval();
/*  162 */       cleanupInternalMethod.invoke(this.cleanup, new Object[] { Long.valueOf(cleanupInternal) });
/*      */       
/*  164 */       int cleanupLevel = getCleanupLevel();
/*  165 */       cleanupLevelMethod.invoke(this.cleanup, new Object[] { Integer.valueOf(cleanupLevel) });
/*      */     }
/*  167 */     catch (Exception je) {
/*  168 */       if (Trace.isOn) {
/*  169 */         Trace.catchBlock(this, "com.ibm.mq.jms.Cleanup", "<init>()", je);
/*      */       }
/*  171 */       HashMap<String, Exception> data = new HashMap<>();
/*  172 */       data.put("exception", je);
/*  173 */       Trace.ffst("com.ibm.mq.jms.Cleanup", "<linit>", "XF009002", data, null);
/*      */     } 
/*      */     
/*  176 */     if (Trace.isOn) {
/*  177 */       Trace.exit(this, "com.ibm.mq.jms.Cleanup", "<init>()");
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
/*      */   public Cleanup(MQConnectionFactory mqcf) throws JMSException {
/*  192 */     this();
/*  193 */     if (Trace.isOn) {
/*  194 */       Trace.entry(this, "com.ibm.mq.jms.Cleanup", "<init>(MQConnectionFactory)", new Object[] { mqcf });
/*      */     }
/*      */ 
/*      */     
/*  198 */     if (mqcf == null) {
/*  199 */       if (Trace.isOn) {
/*  200 */         Trace.exit(this, "com.ibm.mq.jms.Cleanup", "<init>(MQConnectionFactory)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  206 */     Enumeration<?> props = mqcf.getPropertyNames();
/*  207 */     while (props.hasMoreElements()) {
/*  208 */       String name = (String)props.nextElement();
/*  209 */       setObjectProperty(name, mqcf.getObjectProperty(name));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  219 */     if (getQueueManager() == null || getQueueManager().trim().equals("")) {
/*      */       
/*  221 */       String resolvedQMName = null;
/*  222 */       MQConnection mqc = null;
/*      */ 
/*      */       
/*      */       try {
/*  226 */         if (Trace.isOn) {
/*  227 */           Trace.traceData(this, "Cleanup - QueueManager has null or empty name. Need to resolve it", null);
/*      */         }
/*      */         
/*  230 */         mqc = (MQConnection)createConnection();
/*      */ 
/*      */ 
/*      */         
/*  234 */         resolvedQMName = mqc.getStringProperty("XMSC_WMQ_RESOLVED_QUEUE_MANAGER");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  240 */       catch (JMSException je) {
/*  241 */         if (Trace.isOn) {
/*  242 */           Trace.catchBlock(this, "com.ibm.mq.jms.Cleanup", "<init>(MQConnectionFactory)", (Throwable)je, 1);
/*      */         }
/*  244 */         if (Trace.isOn) {
/*  245 */           Trace.traceData(this, "Cleanup - Error creating queue manager", null);
/*      */         }
/*      */       } 
/*      */       
/*  249 */       if (Trace.isOn) {
/*  250 */         Trace.traceData(this, "Cleanup - Got resolved QueueManager name = '" + resolvedQMName + "'", null);
/*      */       }
/*      */ 
/*      */       
/*  254 */       if (resolvedQMName == null) {
/*  255 */         resolvedQMName = "";
/*      */       } else {
/*  257 */         resolvedQMName = resolvedQMName.trim();
/*      */       } 
/*      */ 
/*      */       
/*  261 */       if (Trace.isOn) {
/*  262 */         Trace.traceData(this, "Cleanup - Close the queue manager and connection", null);
/*      */       }
/*      */       try {
/*  265 */         if (mqc != null) {
/*  266 */           mqc.close();
/*      */         }
/*      */       }
/*  269 */       catch (JMSException je) {
/*  270 */         if (Trace.isOn) {
/*  271 */           Trace.catchBlock(this, "com.ibm.mq.jms.Cleanup", "<init>(MQConnectionFactory)", (Throwable)je, 2);
/*      */         }
/*  273 */         if (Trace.isOn) {
/*  274 */           Trace.traceData(this, "Cleanup - Error closing queue manager", null);
/*      */         }
/*      */       } 
/*      */       
/*  278 */       setQueueManager(resolvedQMName);
/*      */     } 
/*      */     
/*  281 */     if (Trace.isOn) {
/*  282 */       Trace.exit(this, "com.ibm.mq.jms.Cleanup", "<init>(MQConnectionFactory)", 2);
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
/*  295 */     if (Trace.isOn) {
/*  296 */       Trace.data(this, "com.ibm.mq.jms.Cleanup", "setCleanupInterval(long)", "setter", 
/*  297 */           Long.valueOf(interval));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  302 */     super.setCleanupInterval(interval);
/*  303 */     if (this.cleanup != null) {
/*      */       try {
/*  305 */         cleanupInternalMethod.invoke(this.cleanup, new Object[] { Long.valueOf(interval) });
/*      */       }
/*  307 */       catch (Exception e) {
/*  308 */         if (Trace.isOn) {
/*  309 */           Trace.catchBlock(this, "com.ibm.mq.jms.Cleanup", "setCleanupInterval(long)", e);
/*      */         }
/*  311 */         HashMap<String, Exception> data = new HashMap<>();
/*  312 */         data.put("exception", e);
/*  313 */         Trace.ffst("com.ibm.mq.jms.Cleanup", "setCleanupInternal()", "XF009003", data, null);
/*      */       } 
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
/*  326 */     long traceRet1 = 0L;
/*      */     
/*      */     try {
/*  329 */       traceRet1 = super.getCleanupInterval();
/*      */     }
/*  331 */     catch (JMSException je) {
/*  332 */       if (Trace.isOn) {
/*  333 */         Trace.catchBlock(this, "com.ibm.mq.jms.Cleanup", "getCleanupInterval()", (Throwable)je);
/*      */       }
/*  335 */       HashMap<String, Exception> data = new HashMap<>();
/*  336 */       data.put("exception", je);
/*  337 */       Trace.ffst("com.ibm.mq.jms.Cleanup", "getCleanupInterval()", "XF009004", data, null);
/*      */     } 
/*  339 */     if (Trace.isOn) {
/*  340 */       Trace.data(this, "com.ibm.mq.jms.Cleanup", "getCleanupInterval()", "getter", 
/*  341 */           Long.valueOf(traceRet1));
/*      */     }
/*  343 */     return traceRet1;
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
/*      */   public void setCleanupLevel(int level) throws JMSException {
/*  362 */     if (Trace.isOn) {
/*  363 */       Trace.data(this, "com.ibm.mq.jms.Cleanup", "setCleanupLevel(int)", "setter", 
/*  364 */           Integer.valueOf(level));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  369 */     if (level == -1) {
/*  370 */       HashMap<String, String> inserts = new HashMap<>();
/*  371 */       inserts.put("XMSC_INSERT_PROPERTY", "cleanupLevel");
/*  372 */       inserts.put("XMSC_INSERT_VALUE", String.valueOf(level));
/*  373 */       JMSException je = (JMSException)NLSServices.createException("JMSMQ1006", inserts);
/*  374 */       if (Trace.isOn) {
/*  375 */         Trace.throwing(this, "com.ibm.mq.jms.Cleanup", "setCleanupLevel(int)", (Throwable)je);
/*      */       }
/*  377 */       throw je;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  382 */     super.setCleanupLevel(level);
/*  383 */     if (this.cleanup != null) {
/*      */       try {
/*  385 */         cleanupLevelMethod.invoke(this.cleanup, new Object[] { Integer.valueOf(level) });
/*      */       }
/*  387 */       catch (Exception e) {
/*  388 */         if (Trace.isOn) {
/*  389 */           Trace.catchBlock(this, "com.ibm.mq.jms.Cleanup", "setCleanupLevel(int)", e);
/*      */         }
/*  391 */         HashMap<String, Exception> data = new HashMap<>();
/*  392 */         data.put("exception", e);
/*  393 */         Trace.ffst("com.ibm.mq.jms.Cleanup", "setCleanupLevel(int level)", "XF009005", data, null);
/*      */       } 
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
/*  407 */     int traceRet1 = 0;
/*      */     
/*      */     try {
/*  410 */       traceRet1 = super.getCleanupLevel();
/*      */     }
/*  412 */     catch (JMSException je) {
/*  413 */       if (Trace.isOn) {
/*  414 */         Trace.catchBlock(this, "com.ibm.mq.jms.Cleanup", "getCleanupLevel()", (Throwable)je);
/*      */       }
/*  416 */       HashMap<String, Exception> data = new HashMap<>();
/*  417 */       data.put("exception", je);
/*  418 */       Trace.ffst("com.ibm.mq.jms.Cleanup", "getCleanupLevel()", "XF009006", data, null);
/*      */     } 
/*      */     
/*  421 */     if (Trace.isOn) {
/*  422 */       Trace.data(this, "com.ibm.mq.jms.Cleanup", "getCleanupLevel()", "getter", 
/*  423 */           Integer.valueOf(traceRet1));
/*      */     }
/*  425 */     return traceRet1;
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
/*      */   public static void main(String[] args) throws UnsupportedEncodingException {
/*      */     PrintWriter pw1;
/*  469 */     if (Trace.isOn) {
/*  470 */       Trace.entry("com.ibm.mq.jms.Cleanup", "main(String [ ])", new Object[] { args });
/*      */     }
/*      */     
/*  473 */     boolean client = false;
/*  474 */     String qmgrName = "";
/*  475 */     int r = 0;
/*  476 */     int level = 0;
/*  477 */     boolean trace = false;
/*  478 */     String host = null;
/*  479 */     int port = 0;
/*  480 */     String channel = null;
/*      */     
/*  482 */     String errparam = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  488 */     String encProp = "console.encoding";
/*  489 */     PropertyStore.register(encProp, "");
/*  490 */     String encoding = PropertyStore.getStringProperty(encProp);
/*      */     
/*  492 */     if (NLSServices.isWindowsLatinCodepage() && encoding != "" && encoding != null) {
/*  493 */       pw1 = new PrintWriter(new OutputStreamWriter(System.out, encoding));
/*      */     } else {
/*  495 */       pw1 = new PrintWriter(new OutputStreamWriter(System.out, Charset.defaultCharset()));
/*      */     } 
/*      */ 
/*      */     
/*  499 */     final PrintWriter pw = pw1;
/*      */     
/*  501 */     pw.println();
/*  502 */     pw.println(NLSServices.getMessage("Licensed Materials - Property of IBM 5724-I07 (c) Copyright IBM Corp. 2004, 2015 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp."));
/*  503 */     pw.println(NLSServices.getMessage("JMSMQ1002"));
/*  504 */     pw.println(NLSServices.getMessage("JMSMQ5079"));
/*  505 */     pw.println();
/*  506 */     pw.flush();
/*      */ 
/*      */     
/*  509 */     int i = 0;
/*      */     try {
/*  511 */       while (i < args.length) {
/*  512 */         String a = args[i].toLowerCase();
/*  513 */         errparam = args[i];
/*  514 */         if (a.equals("-client")) {
/*  515 */           client = true;
/*  516 */         } else if (a.equals("safe")) {
/*  517 */           level = 1;
/*  518 */         } else if (a.equals("strong")) {
/*  519 */           level = 2;
/*  520 */         } else if (a.equals("force")) {
/*  521 */           level = 3;
/*  522 */         } else if (a.equals("nondur")) {
/*  523 */           level = 4;
/*  524 */         } else if (a.equals("-m")) {
/*  525 */           qmgrName = args[++i];
/*  526 */         } else if (a.equals("-r")) {
/*  527 */           r = Integer.parseInt(args[++i]);
/*  528 */         } else if (a.equals("-host")) {
/*  529 */           host = args[++i];
/*  530 */         } else if (a.equals("-port")) {
/*  531 */           port = Integer.parseInt(args[++i]);
/*  532 */         } else if (a.equals("-channel")) {
/*  533 */           channel = args[++i];
/*  534 */         } else if (a.equals("-t")) {
/*  535 */           trace = true;
/*      */         } else {
/*  537 */           IllegalArgumentException traceRet2 = new IllegalArgumentException();
/*  538 */           if (Trace.isOn) {
/*  539 */             Trace.throwing("com.ibm.mq.jms.Cleanup", "main(String [ ])", traceRet2);
/*      */           }
/*  541 */           throw traceRet2;
/*      */         } 
/*      */         
/*  544 */         i++;
/*      */       }
/*      */     
/*  547 */     } catch (NumberFormatException nfe) {
/*  548 */       if (Trace.isOn) {
/*  549 */         Trace.catchBlock("com.ibm.mq.jms.Cleanup", "main(String [ ])", nfe, 1);
/*      */       }
/*      */       
/*  552 */       String msg = NLSServices.getMessage("JMSMQ5092", new Object[] { errparam });
/*  553 */       pw.println(msg);
/*  554 */       displayUsage(pw);
/*  555 */       pw.flush();
/*  556 */       if (Trace.isOn) {
/*  557 */         Trace.exit("com.ibm.mq.jms.Cleanup", "main(String [ ])", 1);
/*      */       }
/*      */       
/*      */       return;
/*  561 */     } catch (IllegalArgumentException iae) {
/*  562 */       if (Trace.isOn) {
/*  563 */         Trace.catchBlock("com.ibm.mq.jms.Cleanup", "main(String [ ])", iae, 2);
/*      */       }
/*      */       
/*  566 */       String msg = NLSServices.getMessage("JMSMQ5093", new Object[] { errparam });
/*  567 */       pw.println(msg);
/*  568 */       displayUsage(pw);
/*  569 */       pw.flush();
/*  570 */       if (Trace.isOn) {
/*  571 */         Trace.exit("com.ibm.mq.jms.Cleanup", "main(String [ ])", 2);
/*      */       }
/*      */       
/*      */       return;
/*  575 */     } catch (IndexOutOfBoundsException ioobe) {
/*  576 */       if (Trace.isOn) {
/*  577 */         Trace.catchBlock("com.ibm.mq.jms.Cleanup", "main(String [ ])", ioobe, 3);
/*      */       }
/*      */       
/*  580 */       String msg = NLSServices.getMessage("JMSMQ5094", new Object[] { errparam });
/*  581 */       pw.println(msg);
/*  582 */       displayUsage(pw);
/*  583 */       pw.flush();
/*  584 */       if (Trace.isOn) {
/*  585 */         Trace.exit("com.ibm.mq.jms.Cleanup", "main(String [ ])", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  591 */     if (level == 0) {
/*      */       
/*  593 */       String msg = NLSServices.getMessage("JMSMQ5090");
/*  594 */       pw.println(msg);
/*  595 */       displayUsage(pw);
/*  596 */       pw.flush();
/*  597 */       if (Trace.isOn) {
/*  598 */         Trace.exit("com.ibm.mq.jms.Cleanup", "main(String [ ])", 4);
/*      */       }
/*      */       return;
/*      */     } 
/*  602 */     if (client && host == null) {
/*      */       
/*  604 */       String msg = NLSServices.getMessage("JMSMQ5091");
/*  605 */       pw.println(msg);
/*  606 */       displayUsage(pw);
/*  607 */       pw.flush();
/*  608 */       if (Trace.isOn) {
/*  609 */         Trace.exit("com.ibm.mq.jms.Cleanup", "main(String [ ])", 5);
/*      */       }
/*      */       return;
/*      */     } 
/*  613 */     if (!client && (host != null || port != 0 || channel != null)) {
/*      */       
/*  615 */       String msg = NLSServices.getMessage("JMSMQ5078");
/*  616 */       pw.println(msg);
/*  617 */       displayUsage(pw);
/*  618 */       pw.flush();
/*  619 */       if (Trace.isOn) {
/*  620 */         Trace.exit("com.ibm.mq.jms.Cleanup", "main(String [ ])", 6);
/*      */       }
/*      */       return;
/*      */     } 
/*  624 */     if (port < 0) {
/*      */       
/*  626 */       String msg = NLSServices.getMessage("JMSMQ1006", new Object[] { "-port", 
/*      */             
/*  628 */             String.valueOf(port) });
/*  629 */       pw.println(msg);
/*  630 */       pw.println();
/*  631 */       pw.flush();
/*  632 */       if (Trace.isOn) {
/*  633 */         Trace.exit("com.ibm.mq.jms.Cleanup", "main(String [ ])", 7);
/*      */       }
/*      */       return;
/*      */     } 
/*  637 */     if (r < 0) {
/*      */       
/*  639 */       String msg = NLSServices.getMessage("JMSMQ1006", new Object[] { "-r", String.valueOf(r) });
/*  640 */       pw.println(msg);
/*  641 */       pw.println();
/*  642 */       pw.flush();
/*  643 */       if (Trace.isOn) {
/*  644 */         Trace.exit("com.ibm.mq.jms.Cleanup", "main(String [ ])", 8);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  649 */     if ((level == 3 || level == 4) && r > 0) {
/*      */       
/*  651 */       String msg = NLSServices.getMessage("JMSMQ3043");
/*  652 */       pw.println(msg);
/*  653 */       pw.println();
/*  654 */       pw.flush();
/*  655 */       if (Trace.isOn) {
/*  656 */         Trace.exit("com.ibm.mq.jms.Cleanup", "main(String [ ])", 9);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  662 */     if (client) {
/*  663 */       if (port == 0) {
/*  664 */         port = 1414;
/*      */       }
/*  666 */       if (channel == null) {
/*  667 */         channel = "SYSTEM.DEF.SVRCONN";
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  672 */     if (trace) {
/*  673 */       Trace.setOn(true);
/*  674 */       Trace.traceData("Cleanup", "Trace started from main by -t flag", null);
/*      */     } 
/*      */ 
/*      */     
/*  678 */     Cleanup c = new Cleanup();
/*      */     try {
/*  680 */       c.setQueueManager(qmgrName);
/*  681 */       if (client) {
/*  682 */         c.setTransportType(1);
/*  683 */         c.setHostName(host);
/*  684 */         c.setChannel(channel);
/*  685 */         c.setPort(port);
/*      */       } else {
/*  687 */         c.setTransportType(0);
/*      */       } 
/*  689 */       c.setCleanupInterval(r * 60000L);
/*  690 */       c.setCleanupLevel(level);
/*      */     }
/*  692 */     catch (JMSException je) {
/*  693 */       if (Trace.isOn) {
/*  694 */         Trace.catchBlock("com.ibm.mq.jms.Cleanup", "main(String [ ])", (Throwable)je, 4);
/*      */       }
/*      */       
/*  697 */       pw.println(je.getMessage());
/*  698 */       displayUsage(pw);
/*      */     } 
/*      */     
/*  701 */     if (r > 0) {
/*      */       
/*  703 */       c.setExceptionListener(new ExceptionListener()
/*      */           {
/*      */             public void onException(JMSException e)
/*      */             {
/*  707 */               if (Trace.isOn) {
/*  708 */                 Trace.entry(this, "com.ibm.mq.jms.Cleanup", "onException(JMSException)", new Object[] { e });
/*      */               }
/*      */               
/*  711 */               pw.println(NLSServices.getMessage("JMSMQ5085"));
/*  712 */               pw.println(e);
/*  713 */               if (e.getLinkedException() != null) {
/*  714 */                 pw.println(NLSServices.getMessage("JMSMQ5086"));
/*  715 */                 pw.println(e.getLinkedException());
/*  716 */                 pw.flush();
/*      */               } 
/*  718 */               if (Trace.isOn) {
/*  719 */                 Trace.exit(this, "com.ibm.mq.jms.null", "onException(JMSException)");
/*      */               }
/*      */             }
/*      */           });
/*      */ 
/*      */ 
/*      */       
/*  726 */       c.setPrintWriter(pw1);
/*      */       
/*      */       try {
/*  729 */         c.cleanup(true);
/*      */       }
/*  731 */       catch (JMSException e) {
/*  732 */         if (Trace.isOn) {
/*  733 */           Trace.catchBlock("com.ibm.mq.jms.Cleanup", "main(String [ ])", (Throwable)e, 5);
/*      */         }
/*  735 */         if (Trace.isOn) {
/*  736 */           Trace.catchBlock("com.ibm.mq.jms.Cleanup", "main(String [ ])", (Throwable)e, 6);
/*      */         }
/*  738 */         pw.println(NLSServices.getMessage("JMSMQ5085"));
/*  739 */         pw.println(e);
/*  740 */         if (e.getLinkedException() != null) {
/*  741 */           pw.println(NLSServices.getMessage("JMSMQ5086"));
/*  742 */           pw.println(e.getLinkedException());
/*      */         } 
/*  744 */         pw.println();
/*  745 */         pw.flush();
/*      */       } 
/*      */     } else {
/*      */       
/*      */       try {
/*  750 */         c.cleanup(false);
/*  751 */         pw.println(NLSServices.getMessage("JMSMQ5084"));
/*      */       }
/*  753 */       catch (JMSException e) {
/*  754 */         if (Trace.isOn) {
/*  755 */           Trace.catchBlock("com.ibm.mq.jms.Cleanup", "main(String [ ])", (Throwable)e, 6);
/*      */         }
/*  757 */         pw.println(NLSServices.getMessage("JMSMQ5085"));
/*  758 */         pw.println(e);
/*  759 */         if (e.getLinkedException() != null) {
/*  760 */           pw.println(NLSServices.getMessage("JMSMQ5086"));
/*  761 */           pw.println(e.getLinkedException());
/*      */         } 
/*      */       } 
/*  764 */       pw.println();
/*  765 */       pw.flush();
/*      */     } 
/*  767 */     if (Trace.isOn) {
/*  768 */       Trace.exit("com.ibm.mq.jms.Cleanup", "main(String [ ])", 10);
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
/*      */   private static void displayUsage(PrintWriter pw) {
/*  780 */     if (Trace.isOn) {
/*  781 */       Trace.entry("com.ibm.mq.jms.Cleanup", "displayUsage(PrintWriter)", new Object[] { pw });
/*      */     }
/*  783 */     pw.println(NLSServices.getMessage("JMSMQ5080"));
/*  784 */     pw.println(NLSServices.getMessage("JMSMQ5081"));
/*  785 */     pw.println(NLSServices.getMessage("JMSMQ5082"));
/*  786 */     pw.println(NLSServices.getMessage("JMSMQ5083"));
/*  787 */     pw.println();
/*  788 */     if (Trace.isOn) {
/*  789 */       Trace.exit("com.ibm.mq.jms.Cleanup", "displayUsage(PrintWriter)");
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
/*      */   
/*      */   public void run() {
/*  810 */     if (Trace.isOn) {
/*  811 */       Trace.entry(this, "com.ibm.mq.jms.Cleanup", "run()");
/*      */     }
/*      */     
/*      */     try {
/*  815 */       cleanup(true);
/*      */     }
/*  817 */     catch (JMSException je) {
/*  818 */       if (Trace.isOn) {
/*  819 */         Trace.catchBlock(this, "com.ibm.mq.jms.Cleanup", "run()", (Throwable)je);
/*      */       }
/*  821 */       if (Trace.isOn) {
/*  822 */         Trace.traceData(this, "Ignoring exceptionl, will be passed back by exception listener", je);
/*      */       }
/*      */     } 
/*      */     
/*  826 */     if (Trace.isOn) {
/*  827 */       Trace.exit(this, "com.ibm.mq.jms.Cleanup", "run()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void stop() {
/*  837 */     if (Trace.isOn) {
/*  838 */       Trace.entry(this, "com.ibm.mq.jms.Cleanup", "stop()");
/*      */     }
/*      */     
/*      */     try {
/*  842 */       stopMethod.invoke(this.cleanup, (Object[])null);
/*      */     }
/*  844 */     catch (Exception e) {
/*  845 */       if (Trace.isOn) {
/*  846 */         Trace.catchBlock(this, "com.ibm.mq.jms.Cleanup", "stop()", e);
/*      */       }
/*  848 */       HashMap<String, Exception> data = new HashMap<>();
/*  849 */       data.put("exception", e);
/*  850 */       Trace.ffst("com.ibm.mq.jms.Cleanup", "stop", "XF009007", data, null);
/*      */     } 
/*      */     
/*  853 */     if (Trace.isOn) {
/*  854 */       Trace.exit(this, "com.ibm.mq.jms.Cleanup", "stop()");
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
/*      */   public void setExceptionListener(ExceptionListener el) {
/*  869 */     if (Trace.isOn) {
/*  870 */       Trace.entry(this, "com.ibm.mq.jms.Cleanup", "setExceptionListener(ExceptionListener)", new Object[] { el });
/*      */     }
/*      */ 
/*      */     
/*  874 */     this.exceptionListener = el;
/*      */ 
/*      */ 
/*      */     
/*  878 */     if (this.cleanup != null) {
/*      */       try {
/*  880 */         if (el == null) {
/*  881 */           setExceptionListenerMethod.invoke(this.cleanup, (Object[])null);
/*      */         } else {
/*      */           
/*  884 */           setExceptionListenerMethod.invoke(this.cleanup, new Object[] { new ProviderExceptionListener()
/*      */                 {
/*      */                   public void onException(JMSException e, boolean connectionBroken)
/*      */                   {
/*  888 */                     if (Trace.isOn) {
/*  889 */                       Trace.entry(this, "com.ibm.mq.jms.Cleanup", "onException(JMSException,boolean)", new Object[] { e, 
/*  890 */                             Boolean.valueOf(connectionBroken) });
/*      */                     }
/*      */                     
/*  893 */                     ExceptionListener e2 = Cleanup.this.exceptionListener;
/*  894 */                     if (e2 != null) {
/*  895 */                       e2.onException(e);
/*      */                     }
/*  897 */                     if (Trace.isOn) {
/*  898 */                       Trace.exit(this, "com.ibm.mq.jms.null", "onException(JMSException,boolean)");
/*      */                     }
/*      */                   }
/*      */                 } });
/*      */         }
/*      */       
/*      */       }
/*  905 */       catch (Exception e) {
/*  906 */         if (Trace.isOn) {
/*  907 */           Trace.catchBlock(this, "com.ibm.mq.jms.Cleanup", "setExceptionListener(ExceptionListener)", e);
/*      */         }
/*      */         
/*  910 */         HashMap<String, Exception> data = new HashMap<>();
/*  911 */         data.put("exception", e);
/*  912 */         Trace.ffst("com.ibm.mq.jms.Cleanup", "<clinit>", "XF009008", data, null);
/*      */       } 
/*      */     }
/*  915 */     if (Trace.isOn) {
/*  916 */       Trace.exit(this, "com.ibm.mq.jms.Cleanup", "setExceptionListener(ExceptionListener)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ExceptionListener getExceptionListener() {
/*  927 */     if (Trace.isOn) {
/*  928 */       Trace.data(this, "com.ibm.mq.jms.Cleanup", "getExceptionListener()", "getter", this.exceptionListener);
/*      */     }
/*      */     
/*  931 */     return this.exceptionListener;
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
/*      */   public void setPrintWriter(PrintWriter pw) {
/*  945 */     if (Trace.isOn) {
/*  946 */       Trace.data(this, "com.ibm.mq.jms.Cleanup", "setPrintWriter(PrintWriter)", "setter", pw);
/*      */     }
/*  948 */     if (Trace.isOn) {
/*  949 */       Trace.entry(this, "com.ibm.mq.jms.Cleanup", "setPrintWriter(PrintWriter)", new Object[] { pw });
/*      */     }
/*      */ 
/*      */     
/*  953 */     this.printWriter = pw;
/*      */     
/*  955 */     if (this.cleanup != null) {
/*      */       try {
/*  957 */         if (pw == null) {
/*  958 */           setPrintWriterMethod.invoke(this.cleanup, (Object[])null);
/*      */         } else {
/*  960 */           setPrintWriterMethod.invoke(this.cleanup, new Object[] { this.printWriter });
/*      */         }
/*      */       
/*  963 */       } catch (Exception e) {
/*  964 */         if (Trace.isOn) {
/*  965 */           Trace.catchBlock(this, "com.ibm.mq.jms.Cleanup", "setPrintWriter(PrintWriter)", e);
/*      */         }
/*  967 */         if (Trace.isOn) {
/*  968 */           Trace.catchBlock(this, "com.ibm.mq.jms.Cleanup", "setPrintWriter(PrintWriter)", e);
/*      */         }
/*  970 */         HashMap<String, Object> data = new HashMap<>();
/*  971 */         data.put("exception", e);
/*  972 */         Trace.ffst("com.ibm.mq.jms.Cleanup", "setPrintWriter(PrintWriter)", "XF00900C", data, null);
/*      */       } 
/*      */     }
/*      */     
/*  976 */     if (Trace.isOn) {
/*  977 */       Trace.exit(this, "com.ibm.mq.jms.Cleanup", "setPrintWriter(PrintWriter)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isRunning() {
/*  987 */     if (Trace.isOn) {
/*  988 */       Trace.entry(this, "com.ibm.mq.jms.Cleanup", "isRunning()");
/*      */     }
/*      */     
/*  991 */     boolean traceRet1 = false;
/*      */     
/*      */     try {
/*  994 */       Object returnValue = isRunningMethod.invoke(this.cleanup, (Object[])null);
/*  995 */       if (returnValue instanceof Boolean) {
/*  996 */         traceRet1 = ((Boolean)returnValue).booleanValue();
/*      */       }
/*      */     }
/*  999 */     catch (Exception e) {
/* 1000 */       if (Trace.isOn) {
/* 1001 */         Trace.catchBlock(this, "com.ibm.mq.jms.Cleanup", "isRunning()", e);
/*      */       }
/* 1003 */       HashMap<String, Exception> data = new HashMap<>();
/* 1004 */       data.put("exception", e);
/* 1005 */       Trace.ffst(this, "isRunning", "XF009009", data, null);
/*      */     } 
/*      */ 
/*      */     
/* 1009 */     if (Trace.isOn) {
/* 1010 */       Trace.exit(this, "com.ibm.mq.jms.Cleanup", "isRunning()", Boolean.valueOf(traceRet1));
/*      */     }
/* 1012 */     return traceRet1;
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
/*      */   public void cleanup(boolean runAtRegularIntervals) throws JMSException {
/* 1028 */     if (Trace.isOn)
/* 1029 */       Trace.entry(this, "com.ibm.mq.jms.Cleanup", "cleanup(boolean)", new Object[] {
/* 1030 */             Boolean.valueOf(runAtRegularIntervals)
/*      */           }); 
/* 1032 */     if (Trace.isOn) {
/* 1033 */       Trace.entry(this, "com.ibm.mq.jms.Cleanup", "cleanup(boolean)", new Object[] {
/* 1034 */             Boolean.valueOf(runAtRegularIntervals)
/*      */           });
/*      */     }
/* 1037 */     MQConnection mqc = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1042 */     String providerVersion = getProviderVersion();
/* 1043 */     if (providerVersion.equalsIgnoreCase("unspecified")) {
/* 1044 */       setProviderVersion("6");
/*      */     } else {
/* 1046 */       int providerVersionNum = 0;
/* 1047 */       String[] providerVerElements = providerVersion.split("\\.");
/*      */       try {
/* 1049 */         providerVersionNum = Integer.parseInt(providerVerElements[0]);
/*      */       }
/* 1051 */       catch (Exception e) {
/* 1052 */         if (Trace.isOn) {
/* 1053 */           Trace.catchBlock(this, "com.ibm.mq.jms.Cleanup", "cleanup(boolean)", e, 1);
/*      */         }
/*      */ 
/*      */         
/* 1057 */         HashMap<String, Object> ffstData = new HashMap<>();
/* 1058 */         ffstData.put("Exception", e);
/* 1059 */         Trace.ffst(this, "cleanup(boolean)", "XF00900A", ffstData, JMSException.class);
/*      */       } 
/*      */       
/* 1062 */       if (providerVersionNum > 6) {
/* 1063 */         HashMap<String, Object> inserts = new HashMap<>();
/* 1064 */         inserts.put("XMSC_WMQ_PROVIDER_VERSION", String.valueOf(providerVersion));
/* 1065 */         JMSException je = (JMSException)NLSServices.createException("JMSMQ1120", inserts);
/*      */         
/* 1067 */         if (Trace.isOn) {
/* 1068 */           Trace.throwing(this, "com.ibm.mq.jms.Cleanup", "cleanup(boolean)", (Throwable)je, 1);
/*      */         }
/* 1070 */         throw je;
/*      */       } 
/*      */     } 
/*      */     
/*      */     try {
/* 1075 */       mqc = (MQConnection)createConnection();
/*      */       
/* 1077 */       Object mqv6c = null;
/* 1078 */       mqv6c = ((JmsConnectionImpl)mqc.commonConn).getProviderConnection();
/*      */ 
/*      */       
/* 1081 */       if (runAtRegularIntervals) {
/* 1082 */         if (Trace.isOn) {
/* 1083 */           Trace.data(this, "com.ibm.mq.jms.Cleanup", "cleanup(boolean)", "Performing Cleanup at regular intervals.", null);
/*      */         }
/*      */         
/* 1086 */         runMethod.invoke(this.cleanup, new Object[] { mqv6c });
/*      */       } else {
/* 1088 */         cleanupMethod.invoke(this.cleanup, new Object[] { mqv6c });
/*      */       }
/*      */     
/* 1091 */     } catch (JMSException je) {
/* 1092 */       if (Trace.isOn) {
/* 1093 */         Trace.catchBlock(this, "com.ibm.mq.jms.Cleanup", "cleanup(boolean)", (Throwable)je, 2);
/*      */       }
/* 1095 */       if (Trace.isOn) {
/* 1096 */         Trace.traceData(this, "Exception raised during connect or cleanup", je);
/*      */       }
/* 1098 */       if (Trace.isOn) {
/* 1099 */         Trace.throwing(this, "com.ibm.mq.jms.Cleanup", "cleanup(boolean)", (Throwable)je, 2);
/*      */       }
/* 1101 */       throw je;
/*      */     }
/* 1103 */     catch (Exception e) {
/* 1104 */       if (Trace.isOn) {
/* 1105 */         Trace.catchBlock(this, "com.ibm.mq.jms.Cleanup", "cleanup(boolean)", e, 3);
/*      */       }
/* 1107 */       HashMap<String, Object> data = new HashMap<>();
/* 1108 */       data.put("exception", e);
/* 1109 */       Trace.ffst(this, "cleanup", "XF00900B", data, null);
/*      */     } finally {
/*      */       
/* 1112 */       if (Trace.isOn) {
/* 1113 */         Trace.finallyBlock(this, "com.ibm.mq.jms.Cleanup", "cleanup(boolean)");
/*      */       }
/*      */       try {
/* 1116 */         if (mqc != null) {
/* 1117 */           mqc.close();
/* 1118 */           if (Trace.isOn) {
/* 1119 */             Trace.traceData(this, "Disconnected", null);
/*      */           }
/*      */         }
/*      */       
/* 1123 */       } catch (JMSException je) {
/* 1124 */         if (Trace.isOn) {
/* 1125 */           Trace.catchBlock(this, "com.ibm.mq.jms.Cleanup", "cleanup(boolean)", (Throwable)je, 4);
/*      */         }
/* 1127 */         if (Trace.isOn) {
/* 1128 */           Trace.traceData(this, "Ignoring exception from mqc.close()", null);
/*      */         }
/*      */       } 
/*      */     } 
/* 1132 */     if (Trace.isOn)
/* 1133 */       Trace.exit(this, "com.ibm.mq.jms.Cleanup", "cleanup(boolean)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\Cleanup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */