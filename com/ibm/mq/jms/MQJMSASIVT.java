/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import com.ibm.msg.client.services.Version;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Hashtable;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Message;
/*     */ import javax.jms.MessageListener;
/*     */ import javax.jms.Queue;
/*     */ import javax.jms.QueueConnection;
/*     */ import javax.jms.QueueReceiver;
/*     */ import javax.jms.QueueSender;
/*     */ import javax.jms.QueueSession;
/*     */ import javax.jms.TextMessage;
/*     */ import javax.naming.Context;
/*     */ import javax.naming.directory.InitialDirContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQJMSASIVT
/*     */   extends MQJMSAbstractIVT
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQJMSASIVT.java";
/*     */   private Message inMessage;
/*     */   
/*     */   static {
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.data("com.ibm.mq.jms.MQJMSASIVT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQJMSASIVT.java");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.entry("com.ibm.mq.jms.MQJMSASIVT", "static()");
/*     */     }
/* 131 */     CLASSNAME = "MQJMSASIVT";
/* 132 */     if (Trace.isOn) {
/* 133 */       Trace.exit("com.ibm.mq.jms.MQJMSASIVT", "static()");
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
/*     */   public static void main(String[] args) throws UnsupportedEncodingException {
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.entry("com.ibm.mq.jms.MQJMSASIVT", "main(String [ ])", new Object[] { args });
/*     */     }
/*     */     
/* 149 */     NLSServices.addCatalogue("com.ibm.mq.jms.resources.JMSMQ_MessageResourceBundle", "JMSMQ");
/* 150 */     setupPrintWriter();
/* 151 */     setupWatcherThread();
/*     */     
/* 153 */     String ccsid = null;
/* 154 */     String channel = null;
/* 155 */     boolean client = false;
/* 156 */     String hostname = null;
/* 157 */     String port = null;
/* 158 */     String version = null;
/* 159 */     String QMGR = null;
/* 160 */     Context ctx = null;
/*     */     
/* 162 */     boolean useJNDI = true;
/* 163 */     boolean enableTrace = false;
/* 164 */     String icf = "com.sun.jndi.ldap.LdapCtxFactory";
/* 165 */     String url = null;
/* 166 */     int portNumber = 0;
/* 167 */     int ccsidNumber = 0;
/*     */ 
/*     */     
/*     */     try {
/* 171 */       Class.forName("javax.jms.Message");
/*     */     }
/* 173 */     catch (ClassNotFoundException e) {
/* 174 */       if (Trace.isOn) {
/* 175 */         Trace.catchBlock("com.ibm.mq.jms.MQJMSASIVT", "main(String [ ])", e, 1);
/*     */       }
/*     */       
/* 178 */       if (Trace.isOn) {
/* 179 */         Trace.traceData(CLASSNAME, "javax.jms.Message missing", null);
/* 180 */         Trace.traceData(CLASSNAME, "classpath check failed with " + e, null);
/* 181 */         Trace.exit(CLASSNAME, "main", null);
/*     */       } 
/*     */       
/* 184 */       System.err.println(NLSServices.getMessage("JMSMQ5037", new Object[] { "javax.jms.Message" }));
/*     */     } 
/*     */ 
/*     */     
/* 188 */     String pv = "7";
/* 189 */     Version.Component[] comp = Version.getComponents("MPI", "com.ibm.msg.client.wmq");
/*     */     
/* 191 */     if (comp.length > 0) {
/* 192 */       pv = comp[0].getVersionString() + " " + (String)comp[0].getImplementationInfo().get("CMVC");
/*     */     }
/*     */     
/* 195 */     printWriter.println();
/* 196 */     printWriter.println("Licensed Materials - Property of IBM 5724-I07 (c) Copyright IBM Corp. 2004, 2016 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.");
/* 197 */     printWriter.println(NLSServices.getMessage("JMSMQ1002", new Object[] { pv }));
/* 198 */     printWriter.println(NLSServices.getMessage("JMSMQ5000") + "\n");
/* 199 */     printWriter.flush();
/*     */ 
/*     */     
/* 202 */     for (int i = 0; i < args.length; i++) {
/* 203 */       String arg = args[i].toLowerCase();
/*     */       
/* 205 */       if (arg.equals("-nojndi")) {
/* 206 */         useJNDI = false;
/* 207 */       } else if (arg.equals("-m")) {
/*     */ 
/*     */         
/* 210 */         if (i + 1 < args.length) {
/* 211 */           QMGR = args[++i];
/*     */         } else {
/* 213 */           String msg = NLSServices.getMessage("JMSMQ5036", new Object[] { "-m" });
/* 214 */           printWriter.println(msg);
/* 215 */           printWriter.flush();
/*     */         } 
/* 217 */       } else if (arg.equals("-t")) {
/* 218 */         Trace.setOn(true);
/* 219 */         enableTrace = true;
/* 220 */       } else if (arg.equals("-url")) {
/* 221 */         if (i + 1 < args.length) {
/* 222 */           url = args[++i];
/*     */         } else {
/* 224 */           printWriter.println(NLSServices.getMessage("JMSMQ5001"));
/* 225 */           printWriter.flush();
/*     */         } 
/* 227 */       } else if (arg.equals("-icf")) {
/* 228 */         if (i + 1 < args.length) {
/* 229 */           icf = args[++i];
/*     */         } else {
/* 231 */           printWriter.println(NLSServices.getMessage("JMSMQ5002"));
/* 232 */           printWriter.flush();
/*     */         }
/*     */       
/* 235 */       } else if (arg.equals("-client")) {
/* 236 */         client = true;
/* 237 */       } else if (arg.equals("-host")) {
/* 238 */         if (i + 1 < args.length) {
/* 239 */           hostname = args[++i];
/*     */         } else {
/* 241 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-host" }));
/* 242 */           printWriter.flush();
/*     */         } 
/* 244 */       } else if (arg.equals("-port")) {
/* 245 */         if (i + 1 < args.length) {
/* 246 */           port = args[++i];
/*     */         } else {
/* 248 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-port" }));
/*     */         } 
/* 250 */       } else if (arg.equals("-channel")) {
/* 251 */         if (i + 1 < args.length) {
/* 252 */           channel = args[++i];
/*     */         } else {
/* 254 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-channel" }));
/* 255 */           printWriter.flush();
/*     */         } 
/* 257 */       } else if (arg.equals("-ccsid")) {
/* 258 */         if (i + 1 < args.length) {
/* 259 */           ccsid = args[++i];
/*     */         } else {
/* 261 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-ccsid" }));
/* 262 */           printWriter.flush();
/*     */         }
/*     */       
/* 265 */       } else if (arg.equals("-v")) {
/* 266 */         if (i + 1 < args.length) {
/* 267 */           version = args[++i];
/*     */         } else {
/* 269 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-v" }));
/* 270 */           printWriter.flush();
/*     */         } 
/*     */       } else {
/* 273 */         printWriter.println(NLSServices.getMessage("JMSMQ5003", new Object[] { arg }));
/* 274 */         printWriter.flush();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 279 */     if (enableTrace && 
/* 280 */       Trace.isOn) {
/* 281 */       Trace.entry(CLASSNAME, "main");
/* 282 */       Trace.traceData(CLASSNAME, "Trace started from main by -t flag", null);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 287 */     if (useJNDI) {
/* 288 */       if (url == null) {
/* 289 */         displayUsage();
/* 290 */         System.exit(-1);
/*     */       } 
/* 292 */       if (client || QMGR != null || hostname != null || port != null || channel != null || version != null)
/*     */       {
/* 294 */         displayUsage();
/* 295 */         System.exit(-1);
/*     */       }
/*     */     
/* 298 */     } else if (!client) {
/* 299 */       if (hostname != null || port != null || channel != null) {
/* 300 */         displayUsage();
/* 301 */         System.exit(-1);
/*     */       }
/*     */     
/* 304 */     } else if (QMGR == null || hostname == null) {
/* 305 */       displayUsage();
/* 306 */       System.exit(-1);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 312 */     if (!client && !useJNDI && 
/* 313 */       QMGR == null) {
/* 314 */       QMGR = "";
/*     */     }
/*     */     
/* 317 */     if (client) {
/*     */       try {
/* 319 */         if (port == null) {
/* 320 */           portNumber = 1414;
/*     */         } else {
/* 322 */           portNumber = Integer.parseInt(port);
/*     */         }
/*     */       
/* 325 */       } catch (NumberFormatException nfe) {
/* 326 */         if (Trace.isOn) {
/* 327 */           Trace.catchBlock("com.ibm.mq.jms.MQJMSASIVT", "main(String [ ])", nfe, 2);
/*     */         }
/* 329 */         printWriter.println(NLSServices.getMessage("JMSMQ5064"));
/* 330 */         printWriter.flush();
/* 331 */         System.exit(-1);
/*     */       } 
/* 333 */       if (channel == null) {
/* 334 */         channel = "SYSTEM.DEF.SVRCONN";
/*     */       }
/* 336 */       if (ccsid != null && ccsid.length() > 0) {
/*     */         
/*     */         try {
/* 339 */           ccsidNumber = Integer.parseInt(ccsid);
/*     */         }
/* 341 */         catch (NumberFormatException nfe) {
/* 342 */           if (Trace.isOn) {
/* 343 */             Trace.catchBlock("com.ibm.mq.jms.MQJMSASIVT", "main(String [ ])", nfe, 3);
/*     */           }
/* 345 */           printWriter.println(NLSServices.getMessage("JMSMQ1046", new Object[] { ccsid }));
/* 346 */           printWriter.flush();
/* 347 */           System.exit(-1);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 352 */     if (Trace.isOn) {
/* 353 */       Trace.traceData(CLASSNAME, "Using following parameters:", null);
/* 354 */       if (useJNDI) {
/* 355 */         Trace.traceData(CLASSNAME, "Using JNDI", null);
/* 356 */         Trace.traceData(CLASSNAME, "InitialContextFactory: '" + icf + "'", null);
/* 357 */         Trace.traceData(CLASSNAME, "ProviderURL: '" + url + "'", null);
/*     */       } else {
/* 359 */         Trace.traceData(CLASSNAME, "Not using JNDI", null);
/* 360 */         if (client) {
/* 361 */           Trace.traceData(CLASSNAME, "Using CLIENT connection", null);
/* 362 */           Trace.traceData(CLASSNAME, "Queue Manager: '" + QMGR + "'", null);
/* 363 */           Trace.traceData(CLASSNAME, "HostName: '" + hostname + "'", null);
/* 364 */           Trace.traceData(CLASSNAME, "Port: " + portNumber, null);
/* 365 */           Trace.traceData(CLASSNAME, "Channel: '" + channel + "'", null);
/* 366 */           Trace.traceData(CLASSNAME, "Ccsid: '" + ccsid + "'", null);
/*     */         } else {
/* 368 */           Trace.traceData(CLASSNAME, "Using BINDINGS connection", null);
/* 369 */           Trace.traceData(CLASSNAME, "Queue Manager: '" + QMGR + "'", null);
/*     */         } 
/* 371 */         Trace.traceData(CLASSNAME, "Provider version: " + ((version == null) ? "not set" : version), null);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 376 */     MQJMSASIVT mqjmsasivt = new MQJMSASIVT();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 385 */       if (Trace.isOn) {
/* 386 */         Trace.traceData(CLASSNAME, "checking classpath", null);
/*     */       }
/*     */ 
/*     */       
/*     */       try {
/* 391 */         Class.forName("javax.jms.Message");
/*     */       }
/* 393 */       catch (ClassNotFoundException e) {
/* 394 */         if (Trace.isOn) {
/* 395 */           Trace.catchBlock("com.ibm.mq.jms.MQJMSASIVT", "main(String [ ])", e, 4);
/*     */         }
/* 397 */         if (Trace.isOn) {
/* 398 */           Trace.traceData(CLASSNAME, "javax.jms.Message missing", null);
/*     */         }
/* 400 */         String msg = NLSServices.getMessage("JMSMQ5037", new Object[] { "javax.jms.jar" });
/* 401 */         System.err.println(msg);
/* 402 */         printWriter.flush();
/* 403 */         if (Trace.isOn) {
/* 404 */           Trace.throwing("com.ibm.mq.jms.MQJMSASIVT", "main(String [ ])", e, 1);
/*     */         }
/* 406 */         throw e;
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 411 */         Class.forName("javax.naming.InitialContext");
/*     */       }
/* 413 */       catch (ClassNotFoundException e) {
/* 414 */         if (Trace.isOn) {
/* 415 */           Trace.catchBlock("com.ibm.mq.jms.MQJMSASIVT", "main(String [ ])", e, 5);
/*     */         }
/* 417 */         if (Trace.isOn) {
/* 418 */           Trace.traceData(CLASSNAME, "javax.naming.InitialContext missing", null);
/*     */         }
/* 420 */         String msg = NLSServices.getMessage("JMSMQ5037", new Object[] { "jndi.jar" });
/* 421 */         System.err.println(msg);
/* 422 */         printWriter.flush();
/* 423 */         if (Trace.isOn) {
/* 424 */           Trace.throwing("com.ibm.mq.jms.MQJMSASIVT", "main(String [ ])", e, 2);
/*     */         }
/* 426 */         throw e;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 431 */       if (useJNDI) {
/*     */         try {
/* 433 */           Class.forName(icf);
/*     */         }
/* 435 */         catch (ClassNotFoundException e) {
/* 436 */           if (Trace.isOn) {
/* 437 */             Trace.catchBlock("com.ibm.mq.jms.MQJMSASIVT", "main(String [ ])", e, 6);
/*     */           }
/* 439 */           if (Trace.isOn) {
/* 440 */             Trace.traceData(CLASSNAME, "jndi provider for '" + icf + "' missing", null);
/*     */           }
/* 442 */           String msg = NLSServices.getMessage("JMSMQ5038", new Object[] { icf });
/* 443 */           System.err.println(msg);
/* 444 */           printWriter.flush();
/* 445 */           if (Trace.isOn) {
/* 446 */             Trace.throwing("com.ibm.mq.jms.MQJMSASIVT", "main(String [ ])", e, 3);
/*     */           }
/* 448 */           throw e;
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 457 */     catch (ClassNotFoundException e) {
/* 458 */       if (Trace.isOn) {
/* 459 */         Trace.catchBlock("com.ibm.mq.jms.MQJMSASIVT", "main(String [ ])", e, 7);
/*     */       }
/* 461 */       if (Trace.isOn) {
/* 462 */         Trace.traceData(CLASSNAME, "classpath check failed with " + e, null);
/* 463 */         Trace.exit(CLASSNAME, "main");
/*     */       } 
/* 465 */       System.err.println(e);
/* 466 */       printWriter.flush();
/* 467 */       System.exit(-1);
/*     */     } 
/*     */     
/* 470 */     if (useJNDI) {
/* 471 */       if (Trace.isOn) {
/* 472 */         Trace.traceData(CLASSNAME, "User has opted for JNDI lookup", null);
/*     */       }
/*     */       
/* 475 */       if (url == null) {
/* 476 */         printWriter.println(NLSServices.getMessage("JMSMQ5004"));
/* 477 */         printWriter.flush();
/* 478 */         if (Trace.isOn) {
/* 479 */           Trace.traceData(CLASSNAME, "User has not specified a URL for JNDI lookup", null);
/* 480 */           Trace.traceData(CLASSNAME, "Exiting via System.exit(-1)", null);
/* 481 */           Trace.exit(CLASSNAME, "main");
/*     */         } 
/* 483 */         System.exit(-1);
/*     */       } else {
/* 485 */         printWriter.println(NLSServices.getMessage("JMSMQ5005"));
/* 486 */         printWriter.flush();
/*     */         
/* 488 */         Hashtable<String, String> env = new Hashtable<>();
/* 489 */         env.put("java.naming.factory.initial", icf);
/* 490 */         env.put("java.naming.provider.url", url);
/*     */         
/* 492 */         env.put("java.naming.referral", "throw");
/*     */         
/*     */         try {
/* 495 */           ctx = new InitialDirContext(env);
/*     */         }
/* 497 */         catch (Exception e) {
/* 498 */           if (Trace.isOn) {
/* 499 */             Trace.catchBlock("com.ibm.mq.jms.MQJMSASIVT", "main(String [ ])", e, 8);
/*     */           }
/* 501 */           String msg = NLSServices.getMessage("JMSMQ5006");
/* 502 */           printWriter.println(msg);
/*     */           
/* 504 */           printWriter.println(e);
/* 505 */           printWriter.flush();
/*     */           
/* 507 */           if (Trace.isOn) {
/* 508 */             Trace.traceData(CLASSNAME, "Initial context creation failed!", null);
/* 509 */             Trace.traceData(CLASSNAME, "main", e);
/* 510 */             Trace.traceData(CLASSNAME, "Exiting via System.exit(-1)", null);
/* 511 */             Trace.exit(CLASSNAME, "main");
/*     */           } 
/* 513 */           printWriter.flush();
/* 514 */           System.exit(-1);
/*     */         }
/*     */       
/*     */       } 
/* 518 */     } else if (Trace.isOn) {
/* 519 */       Trace.traceData(CLASSNAME, "User is bypassing JNDI lookup", null);
/*     */     } 
/*     */     
/* 522 */     mqjmsasivt.runIVT(ctx, QMGR, hostname, portNumber, channel, version, ccsidNumber);
/*     */     
/* 524 */     if (Trace.isOn) {
/* 525 */       Trace.traceData(CLASSNAME, "IVT finished", null);
/* 526 */       Trace.exit(CLASSNAME, "main");
/*     */     } 
/*     */     
/* 529 */     if (Trace.isOn) {
/* 530 */       Trace.exit("com.ibm.mq.jms.MQJMSASIVT", "main(String [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void runIVT(Context jndiContext, String queueManagerName, String hostname, int portNumber, String channel, String version, int ccsidNumber) {
/* 538 */     if (Trace.isOn) {
/* 539 */       Trace.entry(this, "com.ibm.mq.jms.MQJMSASIVT", "runIVT(Context,String,String,int,String,String,int)", new Object[] { jndiContext, queueManagerName, hostname, 
/*     */             
/* 541 */             Integer.valueOf(portNumber), channel, version, 
/* 542 */             Integer.valueOf(ccsidNumber) });
/*     */     }
/* 544 */     this.inMessage = null;
/* 545 */     msgListenerFired = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 550 */     printWriter.println();
/* 551 */     String outString = "A simple text message from the MQJMSIVT program";
/*     */     
/* 553 */     MQQueueConnectionFactory factory = null;
/* 554 */     if (jndiContext == null) {
/*     */ 
/*     */       
/* 557 */       if (Trace.isOn) {
/* 558 */         Trace.traceData(CLASSNAME, "Creating a QCF ourselves", null);
/*     */       }
/*     */       
/* 561 */       printWriter.println(NLSServices.getMessage("JMSMQ5007"));
/* 562 */       printWriter.flush();
/* 563 */       factory = new MQQueueConnectionFactory();
/*     */       
/*     */       try {
/* 566 */         factory.setQueueManager(queueManagerName);
/*     */         
/* 568 */         if (null != version) {
/* 569 */           factory.setStringProperty("XMSC_WMQ_PROVIDER_VERSION", version);
/*     */         }
/*     */ 
/*     */         
/* 573 */         if (hostname != null) {
/* 574 */           factory.setTransportType(1);
/* 575 */           factory.setHostName(hostname);
/* 576 */           factory.setPort(portNumber);
/* 577 */           factory.setChannel(channel);
/* 578 */           if (ccsidNumber != 0)
/*     */           {
/* 580 */             factory.setCCSID(ccsidNumber);
/*     */           }
/*     */         }
/*     */       
/* 584 */       } catch (JMSException e) {
/* 585 */         if (Trace.isOn) {
/* 586 */           Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSASIVT", "runIVT(Context,String,String,int,String,String,int)", (Throwable)e, 1);
/*     */         }
/*     */ 
/*     */         
/* 590 */         printWriter.println(NLSServices.getMessage("JMSMQ5115"));
/* 591 */         printWriter.println(e);
/* 592 */         if (Trace.isOn) {
/* 593 */           Trace.traceData(CLASSNAME, "Could not set properties in QCF", null);
/* 594 */           Trace.traceData(CLASSNAME, "main", e);
/* 595 */           Trace.traceData(CLASSNAME, "Exiting via System.exit(-1)", null);
/* 596 */           Trace.exit(CLASSNAME, "main");
/*     */         } 
/* 598 */         printWriter.flush();
/* 599 */         ivtFail();
/*     */       } 
/*     */       
/* 602 */       if (Trace.isOn) {
/* 603 */         Trace.traceData(CLASSNAME, "QCF created OK", null);
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 609 */       if (Trace.isOn) {
/* 610 */         Trace.traceData(CLASSNAME, "Obtaining a QCF from JNDI (lookup key is ivtQCF)", null);
/*     */       }
/*     */ 
/*     */       
/* 614 */       printWriter.println(NLSServices.getMessage("JMSMQ5008"));
/* 615 */       printWriter.flush();
/*     */       try {
/* 617 */         factory = (MQQueueConnectionFactory)jndiContext.lookup("ivtQCF");
/*     */       }
/* 619 */       catch (Exception e) {
/* 620 */         if (Trace.isOn) {
/* 621 */           Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSASIVT", "runIVT(Context,String,String,int,String,String,int)", e, 2);
/*     */         }
/*     */         
/*     */         try {
/* 625 */           if (Trace.isOn) {
/* 626 */             Trace.traceData(CLASSNAME, "Normal lookup failed with the following exception: ", null);
/*     */             
/* 628 */             Trace.traceData(CLASSNAME, "main", e);
/* 629 */             Trace.traceData(CLASSNAME, "Now trying with cn= prefix", null);
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 634 */           factory = (MQQueueConnectionFactory)jndiContext.lookup("cn=ivtQCF");
/*     */         }
/* 636 */         catch (Exception e2) {
/* 637 */           if (Trace.isOn) {
/* 638 */             Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSASIVT", "runIVT(Context,String,String,int,String,String,int)", e2, 3);
/*     */           }
/*     */           
/* 641 */           printWriter.println(NLSServices.getMessage("JMSMQ5009"));
/* 642 */           printWriter.println(e2);
/* 643 */           printWriter.flush();
/* 644 */           if (Trace.isOn) {
/* 645 */             Trace.traceData(CLASSNAME, "Extended lookup failed with the following exception: ", null);
/*     */             
/* 647 */             Trace.traceData(CLASSNAME, "main", e2);
/* 648 */             Trace.traceData(CLASSNAME, "Exiting via System.exit(-1)", null);
/* 649 */             Trace.exit(CLASSNAME, "main");
/*     */           } 
/* 651 */           System.exit(-1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 657 */     printWriter.println(NLSServices.getMessage("JMSMQ5010"));
/* 658 */     printWriter.flush();
/*     */     try {
/* 660 */       if (Trace.isOn) {
/* 661 */         Trace.traceData(CLASSNAME, "Creating connection", null);
/*     */       }
/*     */       
/* 664 */       QueueConnection connection = factory.createQueueConnection();
/*     */ 
/*     */       
/* 667 */       printWriter.println(NLSServices.getMessage("JMSMQ5073"));
/* 668 */       printWriter.flush();
/* 669 */       boolean transacted = false;
/*     */       
/* 671 */       if (Trace.isOn) {
/* 672 */         Trace.traceData(CLASSNAME, "Creating sync session", null);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 677 */       QueueSession syncSession = connection.createQueueSession(transacted, 1);
/*     */       
/* 679 */       printWriter.println(NLSServices.getMessage("JMSMQ5074"));
/* 680 */       printWriter.flush();
/* 681 */       if (Trace.isOn) {
/* 682 */         Trace.traceData(CLASSNAME, "Creating async session", null);
/*     */       }
/* 684 */       QueueSession asyncSession = connection.createQueueSession(transacted, 1);
/*     */       
/* 686 */       Queue ioQueue = null;
/* 687 */       if (jndiContext == null) {
/*     */         
/* 689 */         if (Trace.isOn) {
/* 690 */           Trace.traceData(CLASSNAME, "Creating a Q ourselves", null);
/*     */         }
/*     */         
/* 693 */         printWriter.println(NLSServices.getMessage("JMSMQ5012"));
/* 694 */         printWriter.flush();
/* 695 */         ioQueue = syncSession.createQueue("SYSTEM.DEFAULT.LOCAL.QUEUE");
/*     */       }
/*     */       else {
/*     */         
/* 699 */         if (Trace.isOn) {
/* 700 */           Trace.traceData(CLASSNAME, "Obtaining a Q from JNDI (lookup key is ivtQ)", null);
/*     */         }
/*     */ 
/*     */         
/* 704 */         printWriter.println(NLSServices.getMessage("JMSMQ5013"));
/* 705 */         printWriter.flush();
/*     */         try {
/* 707 */           ioQueue = (Queue)jndiContext.lookup("ivtQ");
/*     */         }
/* 709 */         catch (Exception e) {
/* 710 */           if (Trace.isOn) {
/* 711 */             Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSASIVT", "runIVT(Context,String,String,int,String,String,int)", e, 4);
/*     */           }
/*     */           
/*     */           try {
/* 715 */             if (Trace.isOn) {
/* 716 */               Trace.traceData(CLASSNAME, "Normal lookup failed with the following exception: ", null);
/*     */               
/* 718 */               Trace.traceData(CLASSNAME, "main", e);
/* 719 */               Trace.traceData(CLASSNAME, "Now trying with cn= prefix", null);
/*     */             } 
/*     */ 
/*     */             
/* 723 */             ioQueue = (Queue)jndiContext.lookup("cn=ivtQ");
/*     */           }
/* 725 */           catch (Exception e2) {
/* 726 */             if (Trace.isOn) {
/* 727 */               Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSASIVT", "runIVT(Context,String,String,int,String,String,int)", e2, 5);
/*     */             }
/*     */             
/* 730 */             printWriter.println(NLSServices.getMessage("JMSMQ5014"));
/* 731 */             printWriter.println(e2);
/* 732 */             if (Trace.isOn) {
/* 733 */               Trace.traceData(CLASSNAME, "Extended lookup failed with the following exception: ", null);
/*     */               
/* 735 */               Trace.traceData(CLASSNAME, "main", e2);
/* 736 */               Trace.traceData(CLASSNAME, "Exiting via System.exit(-1)", null);
/* 737 */               Trace.exit(CLASSNAME, "main");
/*     */             } 
/* 739 */             printWriter.flush();
/* 740 */             System.exit(-1);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 746 */       printWriter.println(NLSServices.getMessage("JMSMQ5015"));
/* 747 */       printWriter.flush();
/* 748 */       if (Trace.isOn) {
/* 749 */         Trace.traceData(CLASSNAME, "Creating a sender", null);
/*     */       }
/* 751 */       QueueSender queueSender = syncSession.createSender(ioQueue);
/*     */ 
/*     */       
/* 754 */       printWriter.println(NLSServices.getMessage("JMSMQ5016"));
/* 755 */       printWriter.flush();
/* 756 */       if (Trace.isOn) {
/* 757 */         Trace.traceData(CLASSNAME, "Creating a receiver", null);
/*     */       }
/*     */       
/* 760 */       QueueReceiver queueReceiver = asyncSession.createReceiver(ioQueue);
/*     */       
/* 762 */       printWriter.println(NLSServices.getMessage("JMSMQ5077"));
/* 763 */       printWriter.flush();
/* 764 */       if (Trace.isOn) {
/* 765 */         Trace.traceData(CLASSNAME, "Setting message listener", null);
/*     */       }
/*     */       
/* 768 */       queueReceiver.setMessageListener(new MessageListener()
/*     */           {
/*     */             public void onMessage(Message arg0)
/*     */             {
/* 772 */               if (Trace.isOn) {
/* 773 */                 Trace.entry(this, "com.ibm.mq.jms.MQJMSASIVT", "onMessage(Message)", new Object[] { arg0 });
/*     */               }
/*     */               
/* 776 */               MQJMSAbstractIVT.printWriter.println(NLSServices.getMessage("JMSMQ5076"));
/* 777 */               MQJMSAbstractIVT.printWriter.flush();
/* 778 */               if (Trace.isOn) {
/* 779 */                 Trace.traceData(MQJMSAbstractIVT.CLASSNAME, "Receiving the message", null);
/*     */               }
/*     */ 
/*     */               
/* 783 */               synchronized (MQJMSAbstractIVT.threadWaitLock) {
/*     */ 
/*     */                 
/* 786 */                 MQJMSAbstractIVT.msgListenerFired = true;
/*     */ 
/*     */ 
/*     */                 
/* 790 */                 MQJMSASIVT.this.inMessage = arg0;
/*     */ 
/*     */                 
/* 793 */                 MQJMSAbstractIVT.threadWaitLock.notify();
/*     */               } 
/*     */               
/* 796 */               if (Trace.isOn) {
/* 797 */                 Trace.exit(this, "com.ibm.mq.jms.null", "onMessage(Message)");
/*     */               }
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */       
/* 804 */       printWriter.println(NLSServices.getMessage("JMSMQ5075"));
/* 805 */       printWriter.flush();
/* 806 */       if (Trace.isOn) {
/* 807 */         Trace.traceData(CLASSNAME, "Starting the connection", null);
/*     */       }
/* 809 */       connection.start();
/*     */ 
/*     */       
/* 812 */       printWriter.println(NLSServices.getMessage("JMSMQ5017"));
/* 813 */       printWriter.flush();
/* 814 */       if (Trace.isOn) {
/* 815 */         Trace.traceData(CLASSNAME, "Creating a message", null);
/*     */       }
/* 817 */       TextMessage outMessage = syncSession.createTextMessage();
/* 818 */       outMessage.setText(outString);
/*     */ 
/*     */       
/* 821 */       printWriter.println(NLSServices.getMessage("JMSMQ5018", new Object[] { "SYSTEM.DEFAULT.LOCAL.QUEUE" }));
/* 822 */       printWriter.flush();
/* 823 */       if (Trace.isOn) {
/* 824 */         Trace.traceData(CLASSNAME, "Sending the message", null);
/*     */       }
/* 826 */       queueSender.send((Message)outMessage);
/*     */ 
/*     */ 
/*     */       
/* 830 */       synchronized (threadWaitLock) {
/* 831 */         int retryCount = 0;
/* 832 */         int RETRY_LIMIT = 5;
/* 833 */         while (!msgListenerFired && retryCount++ < 5) {
/*     */           try {
/* 835 */             threadWaitLock.wait(1000L);
/*     */           }
/* 837 */           catch (InterruptedException e) {
/* 838 */             if (Trace.isOn) {
/* 839 */               Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSASIVT", "runIVT(Context,String,String,int,String,String,int)", e, 6);
/*     */             }
/*     */             
/* 842 */             if (Trace.isOn) {
/* 843 */               Trace.traceData(CLASSNAME, "Waiting for responce, thread was interupted", e);
/*     */             }
/*     */             
/* 846 */             this.inMessage = null;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 856 */       if (this.inMessage == null) {
/* 857 */         printWriter.println(NLSServices.getMessage("JMSMQ5020"));
/* 858 */         printWriter.flush();
/*     */ 
/*     */ 
/*     */         
/* 862 */         JMSException traceRet1 = new JMSException(NLSServices.getMessage("JMSMQ5021"));
/* 863 */         if (Trace.isOn) {
/* 864 */           Trace.throwing(this, "com.ibm.mq.jms.MQJMSASIVT", "runIVT(Context,String,String,int,String,String,int)", (Throwable)traceRet1, 1);
/*     */         }
/*     */         
/* 867 */         throw traceRet1;
/*     */       } 
/*     */ 
/*     */       
/* 871 */       printWriter.println("\n" + NLSServices.getMessage("JMSMQ5022") + " " + this.inMessage);
/* 872 */       printWriter.flush();
/*     */       
/* 874 */       if (this.inMessage instanceof TextMessage) {
/* 875 */         if (Trace.isOn) {
/* 876 */           Trace.traceData(CLASSNAME, "Retrived message is a TextMessage; now checking for equality with the sent message", null);
/*     */         }
/*     */ 
/*     */         
/* 880 */         String replyString = ((TextMessage)this.inMessage).getText();
/* 881 */         if (replyString.equals(outString)) {
/* 882 */           if (Trace.isOn) {
/* 883 */             Trace.traceData(CLASSNAME, "Messages are equal. Great!", null);
/*     */           }
/*     */           
/* 886 */           printWriter.println(NLSServices.getMessage("JMSMQ5023"));
/* 887 */           printWriter.flush();
/*     */         } else {
/* 889 */           if (Trace.isOn) {
/* 890 */             Trace.traceData(CLASSNAME, "ERROR! Messages differ!", null);
/* 891 */             Trace.traceData(CLASSNAME, "Original=" + outString, null);
/* 892 */             Trace.traceData(CLASSNAME, "Reply=" + replyString, null);
/*     */           } 
/*     */           
/* 895 */           printWriter.println(NLSServices.getMessage("JMSMQ5024"));
/* 896 */           printWriter.println(NLSServices.getMessage("JMSMQ5025") + " = '" + outString + "'");
/*     */           
/* 898 */           printWriter.println(NLSServices.getMessage("JMSMQ5026") + " = '" + replyString + "'");
/*     */           
/* 900 */           printWriter.flush();
/*     */         } 
/*     */       } else {
/* 903 */         printWriter.println(NLSServices.getMessage("JMSMQ5027"));
/* 904 */         printWriter.flush();
/* 905 */         if (Trace.isOn) {
/* 906 */           Trace.traceData(CLASSNAME, "The retrieved message was not a TextMessage", null);
/*     */         }
/*     */ 
/*     */         
/* 910 */         JMSException traceRet2 = new JMSException(NLSServices.getMessage("JMSMQ5028"));
/* 911 */         if (Trace.isOn) {
/* 912 */           Trace.throwing(this, "com.ibm.mq.jms.MQJMSASIVT", "runIVT(Context,String,String,int,String,String,int)", (Throwable)traceRet2, 2);
/*     */         }
/*     */         
/* 915 */         throw traceRet2;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 920 */       if (Trace.isOn) {
/* 921 */         Trace.traceData(CLASSNAME, "Closing down queues, session and connection", null);
/*     */       }
/*     */       
/* 924 */       printWriter.println(NLSServices.getMessage("JMSMQ5029"));
/* 925 */       printWriter.flush();
/* 926 */       queueReceiver.close();
/* 927 */       printWriter.println(NLSServices.getMessage("JMSMQ5030"));
/* 928 */       printWriter.flush();
/* 929 */       queueSender.close();
/*     */       
/* 931 */       printWriter.println(NLSServices.getMessage("JMSMQ5031"));
/* 932 */       printWriter.flush();
/* 933 */       syncSession.close();
/* 934 */       asyncSession.close();
/* 935 */       syncSession = null;
/* 936 */       asyncSession = null;
/*     */       
/* 938 */       printWriter.println(NLSServices.getMessage("JMSMQ5032"));
/* 939 */       printWriter.flush();
/* 940 */       connection.close();
/* 941 */       connection = null;
/*     */ 
/*     */       
/* 944 */       printWriter.println(NLSServices.getMessage("JMSMQ5033"));
/* 945 */       printWriter.flush();
/*     */     }
/* 947 */     catch (JMSException je) {
/* 948 */       if (Trace.isOn) {
/* 949 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSASIVT", "runIVT(Context,String,String,int,String,String,int)", (Throwable)je, 7);
/*     */       }
/*     */       
/* 952 */       printWriter.println(NLSServices.getMessage("JMSMQ5034"));
/* 953 */       handleException((Exception)je, printWriter);
/* 954 */       ivtFail();
/*     */     }
/* 956 */     catch (Exception e) {
/* 957 */       if (Trace.isOn) {
/* 958 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSASIVT", "runIVT(Context,String,String,int,String,String,int)", e, 8);
/*     */       }
/*     */       
/* 961 */       printWriter.println(NLSServices.getMessage("JMSMQ5034"));
/* 962 */       handleException(e, printWriter);
/* 963 */       ivtFail();
/*     */     } 
/*     */     
/* 966 */     printWriter.println(NLSServices.getMessage("JMSMQ5035"));
/* 967 */     printWriter.flush();
/* 968 */     if (Trace.isOn)
/* 969 */       Trace.exit(this, "com.ibm.mq.jms.MQJMSASIVT", "runIVT(Context,String,String,int,String,String,int)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQJMSASIVT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */