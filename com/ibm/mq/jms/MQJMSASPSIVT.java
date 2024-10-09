/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Hashtable;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Message;
/*     */ import javax.jms.MessageListener;
/*     */ import javax.jms.TextMessage;
/*     */ import javax.jms.Topic;
/*     */ import javax.jms.TopicConnection;
/*     */ import javax.jms.TopicConnectionFactory;
/*     */ import javax.jms.TopicPublisher;
/*     */ import javax.jms.TopicSession;
/*     */ import javax.jms.TopicSubscriber;
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
/*     */ public class MQJMSASPSIVT
/*     */   extends MQJMSAbstractIVT
/*     */ {
/*     */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQJMSASPSIVT.java";
/*     */   private static Message inMessage;
/*     */   
/*     */   static {
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.data("com.ibm.mq.jms.MQJMSASPSIVT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQJMSASPSIVT.java");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.entry("com.ibm.mq.jms.MQJMSASPSIVT", "static()");
/*     */     }
/* 113 */     CLASSNAME = "MQJMSASPSIVT";
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.exit("com.ibm.mq.jms.MQJMSASPSIVT", "static()");
/*     */     }
/*     */ 
/*     */     
/* 119 */     inMessage = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws UnsupportedEncodingException {
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.entry("com.ibm.mq.jms.MQJMSASPSIVT", "main(String [ ])", new Object[] { args });
/*     */     }
/*     */     
/* 131 */     Context ctx = null;
/*     */     
/* 133 */     boolean useJNDI = true;
/* 134 */     String icf = "com.sun.jndi.ldap.LdapCtxFactory";
/* 135 */     String url = null;
/* 136 */     int portNumber = 0;
/* 137 */     int ccsidNumber = 0;
/* 138 */     int systemrc = 0;
/*     */     
/* 140 */     String QMGR = null;
/* 141 */     boolean client = false;
/* 142 */     String hostname = null;
/* 143 */     String port = null;
/* 144 */     String channel = null;
/* 145 */     String ccsid = null;
/* 146 */     String bqm = null;
/* 147 */     String version = null;
/*     */     
/* 149 */     NLSServices.addCatalogue("com.ibm.mq.jms.resources.JMSMQ_MessageResourceBundle", "JMSMQ");
/* 150 */     setupPrintWriter();
/* 151 */     setupWatcherThread();
/* 152 */     earlyClassChecking();
/* 153 */     displayVersion();
/* 154 */     runFrom(printWriter, MQJMSPSIVT.class);
/*     */ 
/*     */     
/* 157 */     for (int i = 0; i < args.length; i++) {
/* 158 */       String arg = args[i].toLowerCase();
/* 159 */       if (arg.equals("-nojndi")) {
/*     */ 
/*     */ 
/*     */         
/* 163 */         useJNDI = false;
/* 164 */       } else if (arg.equals("-m")) {
/*     */ 
/*     */         
/* 167 */         if (i + 1 < args.length) {
/* 168 */           QMGR = args[++i];
/*     */         } else {
/* 170 */           String msg = NLSServices.getMessage("JMSMQ5036", new Object[] { "-m" });
/* 171 */           printWriter.println(msg);
/* 172 */           printWriter.flush();
/*     */         } 
/* 174 */       } else if (arg.equals("-t")) {
/*     */         
/* 176 */         Trace.setOn(true);
/* 177 */       } else if (arg.equals("-url")) {
/*     */         
/* 179 */         if (i + 1 < args.length) {
/* 180 */           url = args[++i];
/*     */         } else {
/* 182 */           printWriter.println(NLSServices.getMessage("JMSMQ5001"));
/* 183 */           printWriter.flush();
/*     */         } 
/* 185 */       } else if (arg.equals("-icf")) {
/*     */ 
/*     */         
/* 188 */         if (i + 1 < args.length) {
/* 189 */           icf = args[++i];
/*     */         } else {
/* 191 */           printWriter.println(NLSServices.getMessage("JMSMQ5002"));
/* 192 */           printWriter.flush();
/*     */         } 
/* 194 */       } else if (arg.equals("-client")) {
/* 195 */         client = true;
/* 196 */       } else if (arg.equals("-host")) {
/* 197 */         if (i + 1 < args.length) {
/* 198 */           hostname = args[++i];
/*     */         } else {
/* 200 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-host" }));
/* 201 */           printWriter.flush();
/*     */         } 
/* 203 */       } else if (arg.equals("-port")) {
/* 204 */         if (i + 1 < args.length) {
/* 205 */           port = args[++i];
/*     */         } else {
/* 207 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-port" }));
/* 208 */           printWriter.flush();
/*     */         } 
/* 210 */       } else if (arg.equals("-channel")) {
/* 211 */         if (i + 1 < args.length) {
/* 212 */           channel = args[++i];
/*     */         } else {
/* 214 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-channel" }));
/* 215 */           printWriter.flush();
/*     */         } 
/* 217 */       } else if (arg.equals("-bqm")) {
/* 218 */         if (i + 1 < args.length) {
/* 219 */           bqm = args[++i];
/*     */         } else {
/* 221 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-bqm" }));
/* 222 */           printWriter.flush();
/*     */         }
/*     */       
/* 225 */       } else if (arg.equals("-ccsid")) {
/* 226 */         if (i + 1 < args.length) {
/* 227 */           ccsid = args[++i];
/*     */         } else {
/* 229 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-ccsid" }));
/* 230 */           printWriter.flush();
/*     */         }
/*     */       
/* 233 */       } else if (arg.equals("-v")) {
/* 234 */         if (i + 1 < args.length) {
/* 235 */           version = args[++i];
/*     */         } else {
/* 237 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-v" }));
/* 238 */           printWriter.flush();
/*     */         } 
/*     */       } else {
/*     */         
/* 242 */         String msg = NLSServices.getMessage("JMSMQ5003", new Object[] { arg });
/* 243 */         printWriter.println(msg);
/* 244 */         printWriter.flush();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 249 */     if (useJNDI) {
/* 250 */       if (url == null) {
/* 251 */         displayPubSubUsage();
/* 252 */         System.exit(-1);
/*     */       } 
/* 254 */       if (client || QMGR != null || hostname != null || port != null || channel != null || bqm != null || version != null)
/*     */       {
/* 256 */         displayPubSubUsage();
/* 257 */         System.exit(-1);
/*     */       }
/*     */     
/* 260 */     } else if (!client) {
/* 261 */       if (hostname != null || port != null || channel != null) {
/* 262 */         displayPubSubUsage();
/* 263 */         System.exit(-1);
/*     */       }
/*     */     
/* 266 */     } else if (QMGR == null || hostname == null) {
/* 267 */       displayPubSubUsage();
/* 268 */       System.exit(-1);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 274 */     if (!client && !useJNDI && 
/* 275 */       QMGR == null) {
/* 276 */       QMGR = "";
/*     */     }
/*     */     
/* 279 */     if (!useJNDI && bqm == null) {
/* 280 */       bqm = QMGR;
/*     */     }
/* 282 */     if (client) {
/*     */       try {
/* 284 */         if (port == null) {
/* 285 */           portNumber = 1414;
/*     */         } else {
/* 287 */           portNumber = Integer.parseInt(port);
/*     */         }
/*     */       
/* 290 */       } catch (NumberFormatException nfe) {
/* 291 */         if (Trace.isOn) {
/* 292 */           Trace.catchBlock("com.ibm.mq.jms.MQJMSASPSIVT", "main(String [ ])", nfe, 1);
/*     */         }
/* 294 */         printWriter.println(NLSServices.getMessage("JMSMQ5064"));
/* 295 */         printWriter.flush();
/* 296 */         System.exit(-1);
/*     */       } 
/* 298 */       if (channel == null) {
/* 299 */         channel = "SYSTEM.DEF.SVRCONN";
/*     */       }
/* 301 */       if (ccsid != null && ccsid.length() > 0) {
/*     */         
/*     */         try {
/* 304 */           ccsidNumber = Integer.parseInt(ccsid);
/*     */         }
/* 306 */         catch (NumberFormatException nfe) {
/* 307 */           if (Trace.isOn) {
/* 308 */             Trace.catchBlock("com.ibm.mq.jms.MQJMSASPSIVT", "main(String [ ])", nfe, 2);
/*     */           }
/* 310 */           printWriter.println(NLSServices.getMessage("JMSMQ1046", new Object[] { ccsid }));
/* 311 */           printWriter.flush();
/* 312 */           System.exit(-1);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 317 */     detailedClassChecking(useJNDI, icf);
/*     */     
/* 319 */     MQJMSASPSIVT mqjmsaspsivt = new MQJMSASPSIVT();
/*     */ 
/*     */ 
/*     */     
/* 323 */     if (useJNDI) {
/* 324 */       if (url == null) {
/*     */         
/* 326 */         printWriter.println(NLSServices.getMessage("JMSMQ5004"));
/* 327 */         printWriter.flush();
/* 328 */         System.exit(-1);
/*     */       } else {
/* 330 */         printWriter.println(NLSServices.getMessage("JMSMQ5005"));
/* 331 */         printWriter.flush();
/*     */ 
/*     */         
/* 334 */         Hashtable<String, String> environment = new Hashtable<>();
/*     */         
/* 336 */         environment.put("java.naming.factory.initial", icf);
/*     */         
/* 338 */         environment.put("java.naming.provider.url", url);
/*     */ 
/*     */ 
/*     */         
/* 342 */         environment.put("java.naming.referral", "throw");
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 347 */           ctx = new InitialDirContext(environment);
/*     */         }
/* 349 */         catch (Exception e) {
/* 350 */           if (Trace.isOn) {
/* 351 */             Trace.catchBlock("com.ibm.mq.jms.MQJMSASPSIVT", "main(String [ ])", e, 3);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 356 */           String msg = NLSServices.getMessage("JMSMQ5006");
/* 357 */           printWriter.println(msg);
/*     */           
/* 359 */           printWriter.println(e);
/* 360 */           printWriter.flush();
/* 361 */           System.exit(-1);
/*     */         } 
/*     */         
/* 364 */         printWriter.println(NLSServices.getMessage("JMSMQ5040"));
/* 365 */         printWriter.flush();
/*     */       } 
/*     */     }
/*     */     
/* 369 */     mqjmsaspsivt.runIVT(ctx, QMGR, hostname, portNumber, channel, bqm, version, ccsidNumber);
/*     */     
/* 371 */     printWriter.println(NLSServices.getMessage("JMSMQ5059") + "\n");
/* 372 */     printWriter.flush();
/* 373 */     System.exit(systemrc);
/* 374 */     if (Trace.isOn) {
/* 375 */       Trace.exit("com.ibm.mq.jms.MQJMSASPSIVT", "main(String [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void runIVT(Context ctx, String queueManagerName, String hostname, int portNumber, String channel, String bqm, String version, int ccsidNumber) {
/* 383 */     if (Trace.isOn) {
/* 384 */       Trace.entry(this, "com.ibm.mq.jms.MQJMSASPSIVT", "runIVT(Context,String,String,int,String,String,String,int)", new Object[] { ctx, queueManagerName, hostname, 
/*     */             
/* 386 */             Integer.valueOf(portNumber), channel, bqm, version, 
/* 387 */             Integer.valueOf(ccsidNumber) });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 393 */     TopicConnectionFactory factory = null;
/* 394 */     Topic topic = null;
/* 395 */     if (ctx != null) {
/*     */ 
/*     */       
/*     */       try {
/* 399 */         factory = (TopicConnectionFactory)ctx.lookup("ivtTCF");
/*     */       }
/* 401 */       catch (Exception e) {
/* 402 */         if (Trace.isOn) {
/* 403 */           Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSASPSIVT", "runIVT(Context,String,String,int,String,String,String,int)", e, 1);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 410 */           factory = (TopicConnectionFactory)ctx.lookup("cn=ivtTCF");
/*     */         }
/* 412 */         catch (Exception e2) {
/* 413 */           if (Trace.isOn) {
/* 414 */             Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSASPSIVT", "runIVT(Context,String,String,int,String,String,String,int)", e2, 2);
/*     */           }
/*     */ 
/*     */           
/* 418 */           printWriter.println(NLSServices.getMessage("JMSMQ5041"));
/* 419 */           printWriter.println(e2);
/* 420 */           printWriter.flush();
/* 421 */           System.exit(-1);
/*     */         } 
/*     */       } 
/*     */       
/* 425 */       printWriter.println(NLSServices.getMessage("JMSMQ5042"));
/* 426 */       printWriter.flush();
/*     */       
/*     */       try {
/* 429 */         topic = (Topic)ctx.lookup("ivtT");
/*     */       }
/* 431 */       catch (Exception e) {
/* 432 */         if (Trace.isOn) {
/* 433 */           Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSASPSIVT", "runIVT(Context,String,String,int,String,String,String,int)", e, 3);
/*     */         }
/*     */ 
/*     */         
/*     */         try {
/* 438 */           topic = (Topic)ctx.lookup("cn=ivtT");
/*     */         }
/* 440 */         catch (Exception e2) {
/* 441 */           if (Trace.isOn) {
/* 442 */             Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSASPSIVT", "runIVT(Context,String,String,int,String,String,String,int)", e2, 4);
/*     */           }
/*     */ 
/*     */           
/* 446 */           printWriter.println(NLSServices.getMessage("JMSMQ5043"));
/* 447 */           printWriter.println(e2);
/* 448 */           printWriter.flush();
/* 449 */           System.exit(-1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 454 */     String outString = "A simple text message from the MQJMSPSIVT program";
/*     */     
/*     */     try {
/* 457 */       if (ctx == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 462 */         printWriter.println(NLSServices.getMessage("JMSMQ5044"));
/* 463 */         printWriter.flush();
/* 464 */         factory = new MQTopicConnectionFactory();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 470 */           ((MQTopicConnectionFactory)factory).setQueueManager(queueManagerName);
/* 471 */           ((MQTopicConnectionFactory)factory).setBrokerQueueManager(bqm);
/* 472 */           ((MQTopicConnectionFactory)factory).setSubscriptionStore(2);
/*     */           
/* 474 */           if (null != version) {
/* 475 */             ((MQTopicConnectionFactory)factory).setStringProperty("XMSC_WMQ_PROVIDER_VERSION", version);
/*     */           }
/*     */ 
/*     */           
/* 479 */           if (hostname != null) {
/* 480 */             ((MQTopicConnectionFactory)factory).setTransportType(1);
/* 481 */             ((MQTopicConnectionFactory)factory).setHostName(hostname);
/* 482 */             ((MQTopicConnectionFactory)factory).setPort(portNumber);
/* 483 */             ((MQTopicConnectionFactory)factory).setChannel(channel);
/* 484 */             if (ccsidNumber != 0) {
/* 485 */               ((MQTopicConnectionFactory)factory).setCCSID(ccsidNumber);
/*     */             }
/*     */           }
/*     */         
/*     */         }
/* 490 */         catch (JMSException e) {
/* 491 */           if (Trace.isOn) {
/* 492 */             Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSASPSIVT", "runIVT(Context,String,String,int,String,String,String,int)", (Throwable)e, 5);
/*     */           }
/*     */ 
/*     */           
/* 496 */           printWriter.println(NLSServices.getMessage("JMSMQ5045"));
/* 497 */           printWriter.println(e);
/* 498 */           printWriter.flush();
/* 499 */           ivtFail();
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 505 */       printWriter.println(NLSServices.getMessage("JMSMQ5010"));
/* 506 */       printWriter.flush();
/*     */ 
/*     */       
/* 509 */       TopicConnection connection = factory.createTopicConnection();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 514 */       printWriter.println(NLSServices.getMessage("JMSMQ5073"));
/* 515 */       printWriter.flush();
/* 516 */       boolean transacted = false;
/* 517 */       TopicSession syncSession = connection.createTopicSession(transacted, 1);
/*     */       
/* 519 */       printWriter.println(NLSServices.getMessage("JMSMQ5074"));
/* 520 */       printWriter.flush();
/* 521 */       if (Trace.isOn) {
/* 522 */         Trace.traceData(MQJMSASIVT.CLASSNAME, "Creating async session", null);
/*     */       }
/* 524 */       TopicSession asyncSession = connection.createTopicSession(transacted, 1);
/*     */       
/* 526 */       if (ctx == null) {
/*     */ 
/*     */ 
/*     */         
/* 530 */         printWriter.println(NLSServices.getMessage("JMSMQ5046"));
/* 531 */         printWriter.flush();
/*     */ 
/*     */         
/*     */         try {
/* 535 */           topic = syncSession.createTopic("MQJMS/PSIVT/Information");
/*     */         }
/* 537 */         catch (JMSException je) {
/* 538 */           if (Trace.isOn) {
/* 539 */             Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSASPSIVT", "runIVT(Context,String,String,int,String,String,String,int)", (Throwable)je, 6);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 544 */           printWriter.println(NLSServices.getMessage("JMSMQ5047"));
/* 545 */           printWriter.println(je);
/* 546 */           printWriter.flush();
/* 547 */           ivtFail();
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 553 */       printWriter.println(NLSServices.getMessage("JMSMQ5048"));
/* 554 */       printWriter.flush();
/* 555 */       TopicPublisher tPub = syncSession.createPublisher(topic);
/*     */ 
/*     */       
/* 558 */       printWriter.println(NLSServices.getMessage("JMSMQ5049"));
/* 559 */       printWriter.flush();
/* 560 */       TopicSubscriber tSub = asyncSession.createSubscriber(topic);
/*     */       
/* 562 */       printWriter.println(NLSServices.getMessage("JMSMQ5077"));
/* 563 */       printWriter.flush();
/* 564 */       if (Trace.isOn) {
/* 565 */         Trace.traceData(MQJMSASIVT.CLASSNAME, "Setting message listener", null);
/*     */       }
/* 567 */       tSub.setMessageListener(new MessageListener()
/*     */           {
/*     */             public void onMessage(Message arg0)
/*     */             {
/* 571 */               if (Trace.isOn) {
/* 572 */                 Trace.entry(this, "com.ibm.mq.jms.MQJMSASPSIVT", "onMessage(Message)", new Object[] { arg0 });
/*     */               }
/*     */               
/* 575 */               MQJMSAbstractIVT.printWriter.println(NLSServices.getMessage("JMSMQ5076"));
/* 576 */               MQJMSAbstractIVT.printWriter.flush();
/* 577 */               if (Trace.isOn) {
/* 578 */                 Trace.traceData(MQJMSAbstractIVT.CLASSNAME, "Receiving the message", null);
/*     */               }
/*     */ 
/*     */               
/* 582 */               synchronized (MQJMSAbstractIVT.threadWaitLock) {
/*     */ 
/*     */                 
/* 585 */                 MQJMSAbstractIVT.msgListenerFired = true;
/*     */ 
/*     */ 
/*     */                 
/* 589 */                 MQJMSASPSIVT.inMessage = arg0;
/*     */ 
/*     */                 
/* 592 */                 MQJMSAbstractIVT.threadWaitLock.notify();
/*     */               } 
/*     */               
/* 595 */               if (Trace.isOn) {
/* 596 */                 Trace.exit(this, "com.ibm.mq.jms.null", "onMessage(Message)");
/*     */               }
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */       
/* 603 */       printWriter.println(NLSServices.getMessage("JMSMQ5075"));
/* 604 */       printWriter.flush();
/* 605 */       if (Trace.isOn) {
/* 606 */         Trace.traceData(MQJMSASIVT.CLASSNAME, "Starting the connection", null);
/*     */       }
/* 608 */       connection.start();
/*     */ 
/*     */ 
/*     */       
/* 612 */       printWriter.println(NLSServices.getMessage("JMSMQ5017"));
/* 613 */       printWriter.flush();
/* 614 */       TextMessage outMessage = syncSession.createTextMessage();
/* 615 */       printWriter.println(NLSServices.getMessage("JMSMQ5050"));
/* 616 */       printWriter.flush();
/* 617 */       outMessage.setText(outString);
/*     */ 
/*     */       
/* 620 */       printWriter
/* 621 */         .println(NLSServices.getMessage("JMSMQ5051", new Object[] {
/* 622 */               topic.getTopicName() }));
/* 623 */       printWriter.flush();
/* 624 */       tPub.publish((Message)outMessage);
/*     */ 
/*     */       
/* 627 */       synchronized (threadWaitLock) {
/*     */         
/*     */         try {
/* 630 */           if (!msgListenerFired) {
/* 631 */             threadWaitLock.wait(5000L);
/*     */           }
/*     */         }
/* 634 */         catch (InterruptedException e) {
/* 635 */           if (Trace.isOn) {
/* 636 */             Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSASPSIVT", "runIVT(Context,String,String,int,String,String,String,int)", e, 7);
/*     */           }
/*     */           
/* 639 */           if (Trace.isOn) {
/* 640 */             Trace.traceData(CLASSNAME, "Waiting for responce, thread was interupted", e);
/*     */           }
/*     */           
/* 643 */           inMessage = null;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 655 */       if (inMessage == null) {
/* 656 */         printWriter.println(NLSServices.getMessage("JMSMQ5054"));
/* 657 */         printWriter.flush();
/*     */         
/* 659 */         printWriter.println(NLSServices.getMessage("JMSMQ5055"));
/* 660 */         printWriter.flush();
/*     */ 
/*     */ 
/*     */         
/* 664 */         JMSException traceRet1 = new JMSException(NLSServices.getMessage("JMSMQ5021"));
/* 665 */         if (Trace.isOn) {
/* 666 */           Trace.throwing(this, "com.ibm.mq.jms.MQJMSASPSIVT", "runIVT(Context,String,String,int,String,String,String,int)", (Throwable)traceRet1);
/*     */         }
/*     */         
/* 669 */         throw traceRet1;
/*     */       } 
/*     */ 
/*     */       
/* 673 */       printWriter.println("\n" + NLSServices.getMessage("JMSMQ5022") + ": " + inMessage);
/*     */       
/* 675 */       printWriter.flush();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 680 */       if (inMessage instanceof TextMessage) {
/*     */ 
/*     */         
/* 683 */         String replyString = ((TextMessage)inMessage).getText();
/*     */ 
/*     */         
/* 686 */         if (replyString.equals(outString)) {
/* 687 */           printWriter.println(NLSServices.getMessage("JMSMQ5023"));
/* 688 */           printWriter.flush();
/*     */         } else {
/*     */           
/* 691 */           printWriter.println(NLSServices.getMessage("JMSMQ5024"));
/* 692 */           printWriter.println(NLSServices.getMessage("JMSMQ5025") + " = '" + outString + "'");
/*     */           
/* 694 */           printWriter.println(NLSServices.getMessage("JMSMQ5026") + " = '" + replyString + "'");
/*     */           
/* 696 */           printWriter.flush();
/* 697 */           ivtFail();
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 702 */         printWriter.println(NLSServices.getMessage("JMSMQ5027"));
/* 703 */         printWriter.flush();
/* 704 */         ivtFail();
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 711 */       printWriter.println(NLSServices.getMessage("JMSMQ5056"));
/* 712 */       printWriter.flush();
/* 713 */       tSub.close();
/*     */ 
/*     */       
/* 716 */       printWriter.println(NLSServices.getMessage("JMSMQ5057"));
/* 717 */       printWriter.flush();
/* 718 */       tPub.close();
/*     */ 
/*     */       
/* 721 */       printWriter.println(NLSServices.getMessage("JMSMQ5031"));
/* 722 */       printWriter.flush();
/* 723 */       syncSession.close();
/* 724 */       syncSession = null;
/* 725 */       asyncSession.close();
/* 726 */       asyncSession = null;
/*     */ 
/*     */       
/* 729 */       printWriter.println(NLSServices.getMessage("JMSMQ5032"));
/* 730 */       printWriter.flush();
/* 731 */       connection.close();
/* 732 */       connection = null;
/*     */     
/*     */     }
/* 735 */     catch (JMSException je) {
/* 736 */       if (Trace.isOn) {
/* 737 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSASPSIVT", "runIVT(Context,String,String,int,String,String,String,int)", (Throwable)je, 8);
/*     */       }
/*     */       
/* 740 */       printWriter.println(NLSServices.getMessage("JMSMQ5034"));
/* 741 */       handleException((Exception)je, printWriter);
/*     */     }
/* 743 */     catch (Exception e) {
/* 744 */       if (Trace.isOn) {
/* 745 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSASPSIVT", "runIVT(Context,String,String,int,String,String,String,int)", e, 9);
/*     */       }
/*     */       
/* 748 */       handleException(e, printWriter);
/*     */     } 
/* 750 */     if (Trace.isOn)
/* 751 */       Trace.exit(this, "com.ibm.mq.jms.MQJMSASPSIVT", "runIVT(Context,String,String,int,String,String,String,int)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQJMSASPSIVT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */