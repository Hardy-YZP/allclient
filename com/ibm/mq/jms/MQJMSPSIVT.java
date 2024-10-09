/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Hashtable;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Message;
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
/*     */ public class MQJMSPSIVT
/*     */   extends MQJMSAbstractIVT
/*     */ {
/*     */   static {
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.data("com.ibm.mq.jms.MQJMSPSIVT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQJMSPSIVT.java");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.entry("com.ibm.mq.jms.MQJMSPSIVT", "static()");
/*     */     }
/*  97 */     CLASSNAME = "MQJMSPSIVT";
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.exit("com.ibm.mq.jms.MQJMSPSIVT", "static()");
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
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.entry("com.ibm.mq.jms.MQJMSPSIVT", "main(String [ ])", new Object[] { args });
/*     */     }
/*     */     
/* 115 */     Context ctx = null;
/* 116 */     String QMGR = null;
/* 117 */     boolean client = false;
/* 118 */     String hostname = null;
/* 119 */     String port = null;
/* 120 */     String channel = null;
/* 121 */     String ccsid = null;
/* 122 */     String bqm = null;
/* 123 */     String version = null;
/*     */     
/* 125 */     boolean useJNDI = true;
/* 126 */     String icf = "com.sun.jndi.ldap.LdapCtxFactory";
/* 127 */     String url = null;
/* 128 */     int portNumber = 0;
/* 129 */     int ccsidNumber = 0;
/*     */     
/* 131 */     NLSServices.addCatalogue("com.ibm.mq.jms.resources.JMSMQ_MessageResourceBundle", "JMSMQ");
/* 132 */     setupPrintWriter();
/* 133 */     setupWatcherThread();
/* 134 */     earlyClassChecking();
/* 135 */     displayVersion();
/* 136 */     printWriter.println(NLSServices.getMessage("JMSMQ5039") + "\n");
/* 137 */     printWriter.flush();
/*     */     
/* 139 */     runFrom(printWriter, MQJMSPSIVT.class);
/*     */     
/* 141 */     printWriter.flush();
/*     */ 
/*     */     
/* 144 */     for (int i = 0; i < args.length; i++) {
/* 145 */       String arg = args[i].toLowerCase();
/* 146 */       if (arg.equals("-nojndi")) {
/*     */ 
/*     */ 
/*     */         
/* 150 */         useJNDI = false;
/* 151 */       } else if (arg.equals("-m")) {
/*     */ 
/*     */         
/* 154 */         if (i + 1 < args.length) {
/* 155 */           QMGR = args[++i];
/*     */         } else {
/* 157 */           String msg = NLSServices.getMessage("JMSMQ5036", new Object[] { "-m" });
/* 158 */           printWriter.println(msg);
/* 159 */           printWriter.flush();
/*     */         } 
/* 161 */       } else if (arg.equals("-t")) {
/*     */         
/* 163 */         Trace.setOn(true);
/* 164 */       } else if (arg.equals("-url")) {
/*     */         
/* 166 */         if (i + 1 < args.length) {
/* 167 */           url = args[++i];
/*     */         } else {
/* 169 */           printWriter.println(NLSServices.getMessage("JMSMQ5001"));
/* 170 */           printWriter.flush();
/*     */         } 
/* 172 */       } else if (arg.equals("-icf")) {
/*     */ 
/*     */         
/* 175 */         if (i + 1 < args.length) {
/* 176 */           icf = args[++i];
/*     */         } else {
/* 178 */           printWriter.println(NLSServices.getMessage("JMSMQ5002"));
/* 179 */           printWriter.flush();
/*     */         } 
/* 181 */       } else if (arg.equals("-client")) {
/* 182 */         client = true;
/* 183 */       } else if (arg.equals("-host")) {
/* 184 */         if (i + 1 < args.length) {
/* 185 */           hostname = args[++i];
/*     */         } else {
/* 187 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-host" }));
/* 188 */           printWriter.flush();
/*     */         } 
/* 190 */       } else if (arg.equals("-port")) {
/* 191 */         if (i + 1 < args.length) {
/* 192 */           port = args[++i];
/*     */         } else {
/* 194 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-port" }));
/* 195 */           printWriter.flush();
/*     */         } 
/* 197 */       } else if (arg.equals("-channel")) {
/* 198 */         if (i + 1 < args.length) {
/* 199 */           channel = args[++i];
/*     */         } else {
/* 201 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-channel" }));
/* 202 */           printWriter.flush();
/*     */         } 
/* 204 */       } else if (arg.equals("-bqm")) {
/* 205 */         if (i + 1 < args.length) {
/* 206 */           bqm = args[++i];
/*     */         } else {
/* 208 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-bqm" }));
/* 209 */           printWriter.flush();
/*     */         }
/*     */       
/* 212 */       } else if (arg.equals("-ccsid")) {
/* 213 */         if (i + 1 < args.length) {
/* 214 */           ccsid = args[++i];
/*     */         } else {
/* 216 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-ccsid" }));
/* 217 */           printWriter.flush();
/*     */         }
/*     */       
/* 220 */       } else if (arg.equals("-v")) {
/* 221 */         if (i + 1 < args.length) {
/* 222 */           version = args[++i];
/*     */         } else {
/* 224 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-v" }));
/* 225 */           printWriter.flush();
/*     */         } 
/*     */       } else {
/*     */         
/* 229 */         String msg = NLSServices.getMessage("JMSMQ5003", new Object[] { arg });
/* 230 */         printWriter.println(msg);
/* 231 */         printWriter.flush();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 236 */     if (useJNDI) {
/* 237 */       if (url == null) {
/* 238 */         displayPubSubUsage();
/* 239 */         System.exit(-1);
/*     */       } 
/* 241 */       if (client || QMGR != null || hostname != null || port != null || channel != null || bqm != null || version != null)
/*     */       {
/* 243 */         displayPubSubUsage();
/* 244 */         System.exit(-1);
/*     */       }
/*     */     
/* 247 */     } else if (!client) {
/* 248 */       if (hostname != null || port != null || channel != null) {
/* 249 */         displayPubSubUsage();
/* 250 */         System.exit(-1);
/*     */       }
/*     */     
/* 253 */     } else if (QMGR == null || hostname == null) {
/* 254 */       displayPubSubUsage();
/* 255 */       System.exit(-1);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 261 */     if (!client && !useJNDI && 
/* 262 */       QMGR == null) {
/* 263 */       QMGR = "";
/*     */     }
/*     */     
/* 266 */     if (!useJNDI && bqm == null) {
/* 267 */       bqm = QMGR;
/*     */     }
/* 269 */     if (client) {
/*     */       try {
/* 271 */         if (port == null) {
/* 272 */           portNumber = 1414;
/*     */         } else {
/* 274 */           portNumber = Integer.parseInt(port);
/*     */         }
/*     */       
/* 277 */       } catch (NumberFormatException nfe) {
/* 278 */         if (Trace.isOn) {
/* 279 */           Trace.catchBlock("com.ibm.mq.jms.MQJMSPSIVT", "main(String [ ])", nfe, 1);
/*     */         }
/* 281 */         printWriter.println(NLSServices.getMessage("JMSMQ5064"));
/* 282 */         printWriter.flush();
/* 283 */         System.exit(-1);
/*     */       } 
/* 285 */       if (channel == null) {
/* 286 */         channel = "SYSTEM.DEF.SVRCONN";
/*     */       }
/* 288 */       if (ccsid != null && ccsid.length() > 0) {
/*     */         
/*     */         try {
/* 291 */           ccsidNumber = Integer.parseInt(ccsid);
/*     */         }
/* 293 */         catch (NumberFormatException nfe) {
/* 294 */           if (Trace.isOn) {
/* 295 */             Trace.catchBlock("com.ibm.mq.jms.MQJMSPSIVT", "main(String [ ])", nfe, 2);
/*     */           }
/* 297 */           printWriter.println(NLSServices.getMessage("JMSMQ1046", new Object[] { ccsid }));
/* 298 */           printWriter.flush();
/* 299 */           System.exit(-1);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 304 */     detailedClassChecking(useJNDI, icf);
/*     */ 
/*     */ 
/*     */     
/* 308 */     if (useJNDI) {
/* 309 */       if (url == null) {
/*     */         
/* 311 */         printWriter.println(NLSServices.getMessage("JMSMQ5004"));
/* 312 */         printWriter.flush();
/* 313 */         System.exit(-1);
/*     */       } else {
/* 315 */         printWriter.println(NLSServices.getMessage("JMSMQ5005"));
/* 316 */         printWriter.flush();
/*     */ 
/*     */         
/* 319 */         Hashtable<String, String> environment = new Hashtable<>();
/*     */         
/* 321 */         environment.put("java.naming.factory.initial", icf);
/*     */         
/* 323 */         environment.put("java.naming.provider.url", url);
/*     */ 
/*     */ 
/*     */         
/* 327 */         environment.put("java.naming.referral", "throw");
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 332 */           ctx = new InitialDirContext(environment);
/*     */         }
/* 334 */         catch (Exception e) {
/* 335 */           if (Trace.isOn) {
/* 336 */             Trace.catchBlock("com.ibm.mq.jms.MQJMSPSIVT", "main(String [ ])", e, 3);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 341 */           String msg = NLSServices.getMessage("JMSMQ5006");
/* 342 */           printWriter.println(msg);
/*     */           
/* 344 */           printWriter.println(e);
/* 345 */           printWriter.flush();
/* 346 */           System.exit(-1);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 351 */     (new MQJMSPSIVT()).runPSIVT(ctx, QMGR, hostname, portNumber, channel, bqm, version, ccsidNumber);
/*     */     
/* 353 */     printWriter.println(NLSServices.getMessage("JMSMQ5059") + "\n");
/* 354 */     printWriter.flush();
/*     */     
/* 356 */     if (Trace.isOn) {
/* 357 */       Trace.exit("com.ibm.mq.jms.MQJMSPSIVT", "main(String [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void runPSIVT(Context ctx, String queueManagerName, String hostname, int portNumber, String channel, String bqm, String version, int ccsidNumber) {
/* 365 */     if (Trace.isOn) {
/* 366 */       Trace.entry(this, "com.ibm.mq.jms.MQJMSPSIVT", "runPSIVT(Context,String,String,int,String,String,String,int)", new Object[] { ctx, queueManagerName, hostname, 
/*     */             
/* 368 */             Integer.valueOf(portNumber), channel, bqm, version, 
/* 369 */             Integer.valueOf(ccsidNumber) });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 374 */     TopicConnectionFactory factory = null;
/* 375 */     Topic topic = null;
/* 376 */     if (ctx != null) {
/* 377 */       printWriter.println(NLSServices.getMessage("JMSMQ5040"));
/* 378 */       printWriter.flush();
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 383 */         factory = (TopicConnectionFactory)ctx.lookup("ivtTCF");
/*     */       }
/* 385 */       catch (Exception e) {
/* 386 */         if (Trace.isOn) {
/* 387 */           Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSPSIVT", "runPSIVT(Context,String,String,int,String,String,String,int)", e, 1);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 394 */           factory = (TopicConnectionFactory)ctx.lookup("cn=ivtTCF");
/*     */         }
/* 396 */         catch (Exception e2) {
/* 397 */           if (Trace.isOn) {
/* 398 */             Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSPSIVT", "runPSIVT(Context,String,String,int,String,String,String,int)", e2, 2);
/*     */           }
/*     */ 
/*     */           
/* 402 */           printWriter.println(NLSServices.getMessage("JMSMQ5041"));
/* 403 */           printWriter.println(e2);
/* 404 */           printWriter.flush();
/* 405 */           System.exit(-1);
/*     */         } 
/*     */       } 
/*     */       
/* 409 */       printWriter.println(NLSServices.getMessage("JMSMQ5042"));
/* 410 */       printWriter.flush();
/*     */       
/*     */       try {
/* 413 */         topic = (Topic)ctx.lookup("ivtT");
/*     */       }
/* 415 */       catch (Exception e) {
/* 416 */         if (Trace.isOn) {
/* 417 */           Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSPSIVT", "runPSIVT(Context,String,String,int,String,String,String,int)", e, 3);
/*     */         }
/*     */ 
/*     */         
/*     */         try {
/* 422 */           topic = (Topic)ctx.lookup("cn=ivtT");
/*     */         }
/* 424 */         catch (Exception e2) {
/* 425 */           if (Trace.isOn) {
/* 426 */             Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSPSIVT", "runPSIVT(Context,String,String,int,String,String,String,int)", e2, 4);
/*     */           }
/*     */ 
/*     */           
/* 430 */           printWriter.println(NLSServices.getMessage("JMSMQ5043"));
/* 431 */           printWriter.println(e2);
/* 432 */           printWriter.flush();
/* 433 */           System.exit(-1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 438 */     String outString = "A simple text message from the MQJMSPSIVT program";
/*     */     
/*     */     try {
/* 441 */       if (ctx == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 446 */         printWriter.println(NLSServices.getMessage("JMSMQ5044"));
/* 447 */         printWriter.flush();
/* 448 */         factory = new MQTopicConnectionFactory();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 454 */           ((MQTopicConnectionFactory)factory).setQueueManager(queueManagerName);
/* 455 */           ((MQTopicConnectionFactory)factory).setBrokerQueueManager(bqm);
/* 456 */           ((MQTopicConnectionFactory)factory).setSubscriptionStore(2);
/*     */           
/* 458 */           if (null != version) {
/* 459 */             ((MQTopicConnectionFactory)factory).setStringProperty("XMSC_WMQ_PROVIDER_VERSION", version);
/*     */           }
/*     */ 
/*     */           
/* 463 */           if (hostname != null) {
/* 464 */             ((MQTopicConnectionFactory)factory).setTransportType(1);
/* 465 */             ((MQTopicConnectionFactory)factory).setHostName(hostname);
/* 466 */             ((MQTopicConnectionFactory)factory).setPort(portNumber);
/* 467 */             ((MQTopicConnectionFactory)factory).setChannel(channel);
/* 468 */             if (ccsidNumber != 0) {
/* 469 */               ((MQTopicConnectionFactory)factory).setCCSID(ccsidNumber);
/*     */             }
/*     */           }
/*     */         
/*     */         }
/* 474 */         catch (JMSException e) {
/* 475 */           if (Trace.isOn) {
/* 476 */             Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSPSIVT", "runPSIVT(Context,String,String,int,String,String,String,int)", (Throwable)e, 5);
/*     */           }
/*     */ 
/*     */           
/* 480 */           printWriter.println(NLSServices.getMessage("JMSMQ5045"));
/* 481 */           printWriter.println(e);
/* 482 */           printWriter.flush();
/* 483 */           ivtFail();
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 489 */       printWriter.println(NLSServices.getMessage("JMSMQ5010"));
/* 490 */       printWriter.flush();
/*     */ 
/*     */       
/* 493 */       TopicConnection connection = factory.createTopicConnection();
/*     */ 
/*     */ 
/*     */       
/* 497 */       connection.start();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 502 */       printWriter.println(NLSServices.getMessage("JMSMQ5011"));
/* 503 */       printWriter.flush();
/* 504 */       boolean transacted = false;
/* 505 */       TopicSession session = connection.createTopicSession(transacted, 1);
/*     */       
/* 507 */       if (ctx == null) {
/*     */ 
/*     */ 
/*     */         
/* 511 */         printWriter.println(NLSServices.getMessage("JMSMQ5046"));
/* 512 */         printWriter.flush();
/*     */ 
/*     */         
/*     */         try {
/* 516 */           topic = session.createTopic("MQJMS/PSIVT/Information");
/*     */         }
/* 518 */         catch (JMSException je) {
/* 519 */           if (Trace.isOn) {
/* 520 */             Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSPSIVT", "runPSIVT(Context,String,String,int,String,String,String,int)", (Throwable)je, 6);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 525 */           printWriter.println(NLSServices.getMessage("JMSMQ5047"));
/* 526 */           printWriter.println(je);
/* 527 */           printWriter.flush();
/* 528 */           ivtFail();
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 534 */       printWriter.println(NLSServices.getMessage("JMSMQ5048"));
/* 535 */       printWriter.flush();
/* 536 */       TopicPublisher tPub = session.createPublisher(topic);
/*     */ 
/*     */       
/* 539 */       printWriter.println(NLSServices.getMessage("JMSMQ5049"));
/* 540 */       printWriter.flush();
/* 541 */       TopicSubscriber tSub = session.createSubscriber(topic);
/*     */ 
/*     */ 
/*     */       
/* 545 */       printWriter.println(NLSServices.getMessage("JMSMQ5017"));
/* 546 */       printWriter.flush();
/* 547 */       TextMessage outMessage = session.createTextMessage();
/* 548 */       printWriter.println(NLSServices.getMessage("JMSMQ5050"));
/* 549 */       printWriter.flush();
/* 550 */       outMessage.setText(outString);
/*     */ 
/*     */       
/* 553 */       printWriter
/* 554 */         .println(NLSServices.getMessage("JMSMQ5051", new Object[] {
/* 555 */               topic.getTopicName() }));
/* 556 */       tPub.publish((Message)outMessage);
/* 557 */       printWriter.flush();
/*     */ 
/*     */ 
/*     */       
/* 561 */       printWriter.println(NLSServices.getMessage("JMSMQ5052"));
/* 562 */       printWriter.flush();
/* 563 */       Message inMessage = tSub.receive(5000L);
/*     */ 
/*     */ 
/*     */       
/* 567 */       if (inMessage == null) {
/* 568 */         printWriter.println(NLSServices.getMessage("JMSMQ5054"));
/* 569 */         printWriter.flush();
/*     */         
/* 571 */         printWriter.println(NLSServices.getMessage("JMSMQ5055"));
/* 572 */         printWriter.flush();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 577 */         JMSException traceRet1 = new JMSException(NLSServices.getMessage("JMSMQ5021"));
/* 578 */         if (Trace.isOn) {
/* 579 */           Trace.throwing(this, "com.ibm.mq.jms.MQJMSPSIVT", "runPSIVT(Context,String,String,int,String,String,String,int)", (Throwable)traceRet1);
/*     */         }
/*     */         
/* 582 */         throw traceRet1;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 587 */       printWriter.println("\n" + NLSServices.getMessage("JMSMQ5022") + ": " + inMessage);
/*     */       
/* 589 */       printWriter.flush();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 594 */       if (inMessage instanceof TextMessage) {
/*     */ 
/*     */         
/* 597 */         String replyString = ((TextMessage)inMessage).getText();
/*     */ 
/*     */         
/* 600 */         if (replyString.equals(outString)) {
/* 601 */           printWriter.println(NLSServices.getMessage("JMSMQ5023"));
/* 602 */           printWriter.flush();
/*     */         } else {
/*     */           
/* 605 */           printWriter.println(NLSServices.getMessage("JMSMQ5024"));
/* 606 */           printWriter.println(NLSServices.getMessage("JMSMQ5025") + " = '" + outString + "'");
/*     */           
/* 608 */           printWriter.println(NLSServices.getMessage("JMSMQ5026") + " = '" + replyString + "'");
/*     */           
/* 610 */           printWriter.flush();
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 615 */         printWriter.println(NLSServices.getMessage("JMSMQ5027"));
/* 616 */         printWriter.flush();
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 623 */       printWriter.println(NLSServices.getMessage("JMSMQ5056"));
/* 624 */       printWriter.flush();
/* 625 */       tSub.close();
/*     */ 
/*     */       
/* 628 */       printWriter.println(NLSServices.getMessage("JMSMQ5057"));
/* 629 */       printWriter.flush();
/* 630 */       tPub.close();
/*     */ 
/*     */       
/* 633 */       printWriter.println(NLSServices.getMessage("JMSMQ5031"));
/* 634 */       printWriter.flush();
/* 635 */       session.close();
/* 636 */       session = null;
/*     */ 
/*     */       
/* 639 */       printWriter.println(NLSServices.getMessage("JMSMQ5032"));
/* 640 */       printWriter.flush();
/* 641 */       connection.close();
/* 642 */       connection = null;
/*     */     
/*     */     }
/* 645 */     catch (JMSException je) {
/* 646 */       if (Trace.isOn) {
/* 647 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSPSIVT", "runPSIVT(Context,String,String,int,String,String,String,int)", (Throwable)je, 7);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 652 */       printWriter.println(NLSServices.getMessage("JMSMQ5034"));
/* 653 */       handleException((Exception)je, printWriter);
/* 654 */       printWriter.flush();
/* 655 */       ivtFail();
/*     */     }
/* 657 */     catch (Exception e) {
/* 658 */       if (Trace.isOn) {
/* 659 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSPSIVT", "runPSIVT(Context,String,String,int,String,String,String,int)", e, 8);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 664 */       printWriter.println(NLSServices.getMessage("JMSMQ5034"));
/* 665 */       e.printStackTrace(printWriter);
/* 666 */       printWriter.flush();
/* 667 */       ivtFail();
/*     */     } 
/* 669 */     if (Trace.isOn)
/* 670 */       Trace.exit(this, "com.ibm.mq.jms.MQJMSPSIVT", "runPSIVT(Context,String,String,int,String,String,String,int)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQJMSPSIVT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */