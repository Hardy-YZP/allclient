/*     */ package com.ibm.mq.jms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.Console;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Hashtable;
/*     */ import javax.jms.JMSException;
/*     */ import javax.jms.Message;
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
/*     */ public class MQJMSIVT
/*     */   extends MQJMSAbstractIVT
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQJMSIVT.java";
/*     */   
/*     */   static {
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.data("com.ibm.mq.jms.MQJMSIVT", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms/src/com/ibm/mq/jms/MQJMSIVT.java");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.entry("com.ibm.mq.jms.MQJMSIVT", "static()");
/*     */     }
/* 121 */     CLASSNAME = "MQJMSIVT";
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.exit("com.ibm.mq.jms.MQJMSIVT", "static()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws UnsupportedEncodingException {
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.entry("com.ibm.mq.jms.MQJMSIVT", "main(String [ ])", new Object[] { args });
/*     */     }
/*     */     
/* 138 */     NLSServices.addCatalogue("com.ibm.mq.jms.resources.JMSMQ_MessageResourceBundle", "JMSMQ");
/* 139 */     setupPrintWriter();
/* 140 */     setupWatcherThread();
/*     */     
/* 142 */     String QMGR = null;
/* 143 */     boolean client = false;
/* 144 */     String hostname = null;
/* 145 */     String port = null;
/* 146 */     String channel = null;
/* 147 */     String ccsid = null;
/* 148 */     String version = null;
/* 149 */     String user = null;
/* 150 */     String password = null;
/* 151 */     boolean passwordSupplied = false;
/* 152 */     Context ctx = null;
/*     */     
/* 154 */     boolean useJNDI = true;
/* 155 */     boolean enableTrace = false;
/* 156 */     String icf = "com.sun.jndi.ldap.LdapCtxFactory";
/* 157 */     String url = null;
/* 158 */     int portNumber = 0;
/* 159 */     int ccsidNumber = 0;
/*     */     
/* 161 */     earlyClassChecking();
/*     */     
/* 163 */     displayVersion();
/* 164 */     printWriter.println(NLSServices.getMessage("JMSMQ5000") + "\n");
/* 165 */     printWriter.flush();
/*     */ 
/*     */     
/* 168 */     runFrom(printWriter, MQJMSIVT.class);
/*     */     
/* 170 */     printWriter.flush();
/*     */ 
/*     */     
/* 173 */     for (int i = 0; i < args.length; i++) {
/* 174 */       String arg = args[i].toLowerCase();
/*     */       
/* 176 */       if (arg.equals("-nojndi")) {
/* 177 */         useJNDI = false;
/* 178 */       } else if (arg.equals("-m")) {
/*     */ 
/*     */         
/* 181 */         if (i + 1 < args.length) {
/* 182 */           QMGR = args[++i];
/*     */         } else {
/* 184 */           String msg = NLSServices.getMessage("JMSMQ5036", new Object[] { "-m" });
/* 185 */           printWriter.println(msg);
/* 186 */           printWriter.flush();
/*     */         } 
/* 188 */       } else if (arg.equals("-t")) {
/* 189 */         Trace.setOn(true);
/* 190 */         enableTrace = true;
/* 191 */       } else if (arg.equals("-url")) {
/* 192 */         if (i + 1 < args.length) {
/* 193 */           url = args[++i];
/*     */         } else {
/* 195 */           printWriter.println(NLSServices.getMessage("JMSMQ5001"));
/* 196 */           printWriter.flush();
/*     */         } 
/* 198 */       } else if (arg.equals("-icf")) {
/* 199 */         if (i + 1 < args.length) {
/* 200 */           icf = args[++i];
/*     */         } else {
/* 202 */           printWriter.println(NLSServices.getMessage("JMSMQ5002"));
/* 203 */           printWriter.flush();
/*     */         }
/*     */       
/* 206 */       } else if (arg.equals("-client")) {
/* 207 */         client = true;
/* 208 */       } else if (arg.equals("-host")) {
/* 209 */         if (i + 1 < args.length) {
/* 210 */           hostname = args[++i];
/*     */         } else {
/* 212 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-host" }));
/* 213 */           printWriter.flush();
/*     */         } 
/* 215 */       } else if (arg.equals("-port")) {
/* 216 */         if (i + 1 < args.length) {
/* 217 */           port = args[++i];
/*     */         } else {
/* 219 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-port" }));
/*     */         } 
/* 221 */       } else if (arg.equals("-channel")) {
/* 222 */         if (i + 1 < args.length) {
/* 223 */           channel = args[++i];
/*     */         } else {
/* 225 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-channel" }));
/* 226 */           printWriter.flush();
/*     */         } 
/* 228 */       } else if (arg.equals("-ccsid")) {
/* 229 */         if (i + 1 < args.length) {
/* 230 */           ccsid = args[++i];
/*     */         } else {
/* 232 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-ccsid" }));
/* 233 */           printWriter.flush();
/*     */         }
/*     */       
/* 236 */       } else if (arg.equals("-v")) {
/* 237 */         if (i + 1 < args.length) {
/* 238 */           version = args[++i];
/*     */         } else {
/* 240 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-v" }));
/* 241 */           printWriter.flush();
/*     */         }
/*     */       
/* 244 */       } else if (arg.equals("-u")) {
/* 245 */         if (i + 1 < args.length) {
/* 246 */           user = args[++i];
/*     */         } else {
/* 248 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-u" }));
/* 249 */           printWriter.flush();
/*     */         } 
/* 251 */       } else if (arg.equals("-w")) {
/* 252 */         if (i + 1 < args.length) {
/* 253 */           password = args[++i];
/* 254 */           passwordSupplied = true;
/*     */         } else {
/* 256 */           printWriter.println(NLSServices.getMessage("JMSMQ5036", new Object[] { "-w" }));
/* 257 */           printWriter.flush();
/*     */         } 
/*     */       } else {
/* 260 */         printWriter.println(NLSServices.getMessage("JMSMQ5003", new Object[] { arg }));
/* 261 */         printWriter.flush();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 266 */     if (enableTrace && 
/* 267 */       Trace.isOn) {
/* 268 */       Trace.entry(CLASSNAME, "main");
/* 269 */       Trace.traceData(CLASSNAME, "Trace started from main by -t flag", null);
/*     */     } 
/*     */ 
/*     */     
/* 273 */     MQJMSIVT mqjmsivtInstance = new MQJMSIVT();
/*     */ 
/*     */     
/* 276 */     if (useJNDI) {
/* 277 */       if (url == null) {
/* 278 */         displayUsage();
/* 279 */         mqjmsivtInstance.ivtFail();
/*     */       } 
/* 281 */       if (client || QMGR != null || hostname != null || port != null || channel != null || version != null)
/*     */       {
/* 283 */         displayUsage();
/* 284 */         mqjmsivtInstance.ivtFail();
/*     */       }
/*     */     
/* 287 */     } else if (!client) {
/* 288 */       if (hostname != null || port != null || channel != null) {
/* 289 */         displayUsage();
/* 290 */         mqjmsivtInstance.ivtFail();
/*     */       }
/*     */     
/* 293 */     } else if (QMGR == null || hostname == null) {
/* 294 */       displayUsage();
/* 295 */       mqjmsivtInstance.ivtFail();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 301 */     if (!client && !useJNDI && 
/* 302 */       QMGR == null) {
/* 303 */       QMGR = "";
/*     */     }
/*     */     
/* 306 */     if (client) {
/*     */       try {
/* 308 */         if (port == null) {
/* 309 */           portNumber = 1414;
/*     */         } else {
/* 311 */           portNumber = Integer.parseInt(port);
/*     */         }
/*     */       
/* 314 */       } catch (NumberFormatException nfe) {
/* 315 */         if (Trace.isOn) {
/* 316 */           Trace.catchBlock("com.ibm.mq.jms.MQJMSIVT", "main(String [ ])", nfe, 1);
/*     */         }
/* 318 */         printWriter.println(NLSServices.getMessage("JMSMQ5064"));
/* 319 */         printWriter.flush();
/* 320 */         mqjmsivtInstance.ivtFail();
/*     */       } 
/* 322 */       if (channel == null) {
/* 323 */         channel = "SYSTEM.DEF.SVRCONN";
/*     */       }
/* 325 */       if (ccsid != null && ccsid.length() > 0) {
/*     */         
/*     */         try {
/* 328 */           ccsidNumber = Integer.parseInt(ccsid);
/*     */         }
/* 330 */         catch (NumberFormatException nfe) {
/* 331 */           if (Trace.isOn) {
/* 332 */             Trace.catchBlock("com.ibm.mq.jms.MQJMSIVT", "main(String [ ])", nfe, 2);
/*     */           }
/* 334 */           printWriter.println(NLSServices.getMessage("JMSMQ1046", new Object[] { ccsid }));
/* 335 */           printWriter.flush();
/* 336 */           mqjmsivtInstance.ivtFail();
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 342 */     if (user != null && !passwordSupplied) {
/* 343 */       printWriter.print(NLSServices.getMessage("JMSMQ5116", new Object[] { ccsid }));
/* 344 */       printWriter.flush();
/* 345 */       Console c = System.console();
/*     */       try {
/* 347 */         if (c != null) {
/* 348 */           password = new String(c.readPassword());
/*     */         } else {
/* 350 */           throw new Exception();
/*     */         } 
/* 352 */       } catch (Exception e) {
/* 353 */         printWriter.println();
/* 354 */         printWriter.println(NLSServices.getMessage("JMSMQ5117", new Object[] { ccsid }));
/* 355 */         printWriter.flush();
/* 356 */         mqjmsivtInstance.ivtFail();
/*     */       } 
/* 358 */       printWriter.println();
/*     */     } 
/*     */ 
/*     */     
/* 362 */     if (Trace.isOn) {
/* 363 */       Trace.traceData(CLASSNAME, "Using following parameters:", null);
/* 364 */       if (useJNDI) {
/* 365 */         Trace.traceData(CLASSNAME, "Using JNDI", null);
/* 366 */         Trace.traceData(CLASSNAME, "InitialContextFactory: '" + icf + "'", null);
/* 367 */         Trace.traceData(CLASSNAME, "ProviderURL: '" + url + "'", null);
/*     */       } else {
/* 369 */         Trace.traceData(CLASSNAME, "Not using JNDI", null);
/* 370 */         if (client) {
/* 371 */           Trace.traceData(CLASSNAME, "Using CLIENT connection", null);
/* 372 */           Trace.traceData(CLASSNAME, "Queue Manager: '" + QMGR + "'", null);
/* 373 */           Trace.traceData(CLASSNAME, "HostName: '" + hostname + "'", null);
/* 374 */           Trace.traceData(CLASSNAME, "Port: " + portNumber, null);
/* 375 */           Trace.traceData(CLASSNAME, "Channel: '" + channel + "'", null);
/* 376 */           Trace.traceData(CLASSNAME, "Ccsid: '" + ccsid + "'", null);
/* 377 */           Trace.traceData(CLASSNAME, "User: '" + user + "'", null);
/* 378 */           Trace.traceData(CLASSNAME, "PasswordLength: '" + password.length() + "'", null);
/*     */         } else {
/* 380 */           Trace.traceData(CLASSNAME, "Using BINDINGS connection", null);
/* 381 */           Trace.traceData(CLASSNAME, "Queue Manager: '" + QMGR + "'", null);
/*     */         } 
/* 383 */         Trace.traceData(CLASSNAME, ("Provider version: " + version == null) ? "not set" : version, null);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 388 */     detailedClassChecking(useJNDI, icf);
/*     */     
/* 390 */     if (useJNDI) {
/* 391 */       if (Trace.isOn) {
/* 392 */         Trace.traceData(CLASSNAME, "User has opted for JNDI lookup", null);
/*     */       }
/*     */       
/* 395 */       if (url == null) {
/* 396 */         printWriter.println(NLSServices.getMessage("JMSMQ5004"));
/* 397 */         printWriter.flush();
/* 398 */         if (Trace.isOn) {
/* 399 */           Trace.traceData(CLASSNAME, "User has not specified a URL for JNDI lookup", null);
/* 400 */           Trace.traceData(CLASSNAME, "Exiting via System.exit(-1)", null);
/* 401 */           Trace.exit(CLASSNAME, "main");
/*     */         } 
/* 403 */         mqjmsivtInstance.ivtFail();
/*     */       } else {
/* 405 */         printWriter.println(NLSServices.getMessage("JMSMQ5005"));
/* 406 */         printWriter.flush();
/*     */         
/* 408 */         Hashtable<String, String> env = new Hashtable<>();
/* 409 */         env.put("java.naming.factory.initial", icf);
/* 410 */         env.put("java.naming.provider.url", url);
/*     */         
/* 412 */         env.put("java.naming.referral", "throw");
/*     */         
/*     */         try {
/* 415 */           ctx = new InitialDirContext(env);
/*     */         }
/* 417 */         catch (Exception e) {
/* 418 */           if (Trace.isOn) {
/* 419 */             Trace.catchBlock("com.ibm.mq.jms.MQJMSIVT", "main(String [ ])", e, 3);
/*     */           }
/* 421 */           String msg = NLSServices.getMessage("JMSMQ5006");
/* 422 */           printWriter.println(msg);
/*     */           
/* 424 */           printWriter.println(e);
/* 425 */           printWriter.flush();
/*     */           
/* 427 */           if (Trace.isOn) {
/* 428 */             Trace.traceData(CLASSNAME, "Initial context creation failed!", null);
/* 429 */             Trace.traceData(CLASSNAME, "main", e);
/* 430 */             Trace.traceData(CLASSNAME, "Exiting via System.exit(-1)", null);
/* 431 */             Trace.exit(CLASSNAME, "main");
/*     */           } 
/* 433 */           printWriter.flush();
/* 434 */           mqjmsivtInstance.ivtFail();
/*     */         }
/*     */       
/*     */       } 
/* 438 */     } else if (Trace.isOn) {
/* 439 */       Trace.traceData(CLASSNAME, "User is bypassing JNDI lookup", null);
/*     */     } 
/*     */ 
/*     */     
/* 443 */     mqjmsivtInstance.runIVT(ctx, QMGR, hostname, portNumber, channel, version, ccsidNumber, user, password);
/*     */     
/* 445 */     if (Trace.isOn) {
/* 446 */       Trace.traceData(CLASSNAME, "IVT finished", null);
/* 447 */       Trace.exit(CLASSNAME, "main");
/*     */     } 
/*     */     
/* 450 */     if (Trace.isOn) {
/* 451 */       Trace.exit("com.ibm.mq.jms.MQJMSIVT", "main(String [ ])");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void runIVT(Context jndiContext, String qManagerName, String hostname, int portNumber, String channel, String version, int ccsidNumber, String user, String password) {
/* 460 */     if (Trace.isOn) {
/* 461 */       Trace.entry(this, "com.ibm.mq.jms.MQJMSIVT", "runIVT(Context,String,String,int,String,String,int)", new Object[] { jndiContext, qManagerName, hostname, 
/*     */             
/* 463 */             Integer.valueOf(portNumber), channel, version, 
/* 464 */             Integer.valueOf(ccsidNumber), user, Integer.valueOf(password.length()) });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 469 */     Queue ioQueue = null;
/*     */     
/* 471 */     printWriter.println();
/* 472 */     String outString = "A simple text message from the MQJMSIVT program";
/*     */     
/* 474 */     MQQueueConnectionFactory factory = null;
/* 475 */     if (jndiContext == null) {
/*     */ 
/*     */       
/* 478 */       if (Trace.isOn) {
/* 479 */         Trace.traceData(CLASSNAME, "Creating a QCF ourselves", null);
/*     */       }
/*     */       
/* 482 */       printWriter.println(NLSServices.getMessage("JMSMQ5007"));
/* 483 */       printWriter.flush();
/* 484 */       factory = new MQQueueConnectionFactory();
/*     */       
/*     */       try {
/* 487 */         factory.setQueueManager(qManagerName);
/*     */         
/* 489 */         if (null != version) {
/* 490 */           factory.setStringProperty("XMSC_WMQ_PROVIDER_VERSION", version);
/*     */         }
/*     */ 
/*     */         
/* 494 */         if (hostname != null) {
/* 495 */           factory.setTransportType(1);
/* 496 */           factory.setHostName(hostname);
/* 497 */           factory.setPort(portNumber);
/* 498 */           factory.setChannel(channel);
/* 499 */           if (user != null) {
/* 500 */             factory.setStringProperty("XMSC_USERID", user);
/* 501 */             factory.setStringProperty("XMSC_PASSWORD", password);
/* 502 */             factory.setBooleanProperty("XMSC_USER_AUTHENTICATION_MQCSP", true);
/*     */           } 
/*     */           
/* 505 */           if (ccsidNumber != 0) {
/* 506 */             factory.setCCSID(ccsidNumber);
/*     */           }
/*     */         }
/*     */       
/* 510 */       } catch (JMSException e) {
/* 511 */         if (Trace.isOn) {
/* 512 */           Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSIVT", "runIVT(Context,String,String,int,String,String,int)", (Throwable)e, 1);
/*     */         }
/*     */ 
/*     */         
/* 516 */         printWriter.println(NLSServices.getMessage("JMSMQ5115"));
/* 517 */         printWriter.println(e);
/* 518 */         if (Trace.isOn) {
/* 519 */           Trace.traceData(CLASSNAME, "Could not set properties in QCF", null);
/* 520 */           Trace.traceData(CLASSNAME, "main", e);
/* 521 */           Trace.traceData(CLASSNAME, "Exiting via System.exit(-1)", null);
/* 522 */           Trace.exit(CLASSNAME, "main");
/*     */         } 
/* 524 */         printWriter.flush();
/* 525 */         ivtFail();
/*     */       } 
/*     */       
/* 528 */       if (Trace.isOn) {
/* 529 */         Trace.traceData(CLASSNAME, "QCF created OK", null);
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 535 */       if (Trace.isOn) {
/* 536 */         Trace.traceData(CLASSNAME, "Obtaining a QCF from JNDI (lookup key is ivtQCF)", null);
/*     */       }
/*     */ 
/*     */       
/* 540 */       printWriter.println(NLSServices.getMessage("JMSMQ5008"));
/* 541 */       printWriter.flush();
/*     */       try {
/* 543 */         factory = (MQQueueConnectionFactory)jndiContext.lookup("ivtQCF");
/*     */       }
/* 545 */       catch (Exception e) {
/* 546 */         if (Trace.isOn) {
/* 547 */           Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSIVT", "runIVT(Context,String,String,int,String,String,int)", e, 2);
/*     */         }
/*     */         
/*     */         try {
/* 551 */           if (Trace.isOn) {
/* 552 */             Trace.traceData(CLASSNAME, "Normal lookup failed with the following exception: ", null);
/*     */             
/* 554 */             Trace.traceData(CLASSNAME, "main", e);
/* 555 */             Trace.traceData(CLASSNAME, "Now trying with cn= prefix", null);
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 560 */           factory = (MQQueueConnectionFactory)jndiContext.lookup("cn=ivtQCF");
/*     */         }
/* 562 */         catch (Exception e2) {
/* 563 */           if (Trace.isOn) {
/* 564 */             Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSIVT", "runIVT(Context,String,String,int,String,String,int)", e2, 3);
/*     */           }
/*     */           
/* 567 */           printWriter.println(NLSServices.getMessage("JMSMQ5009"));
/* 568 */           printWriter.println(e2);
/* 569 */           printWriter.flush();
/* 570 */           if (Trace.isOn) {
/* 571 */             Trace.traceData(CLASSNAME, "Extended lookup failed with the following exception: ", null);
/*     */             
/* 573 */             Trace.traceData(CLASSNAME, "main", e2);
/* 574 */             Trace.traceData(CLASSNAME, "Exiting via System.exit(-1)", null);
/* 575 */             Trace.exit(CLASSNAME, "main");
/*     */           } 
/* 577 */           ivtFail();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 583 */     printWriter.println(NLSServices.getMessage("JMSMQ5010"));
/* 584 */     printWriter.flush();
/*     */     try {
/* 586 */       if (Trace.isOn) {
/* 587 */         Trace.traceData(CLASSNAME, "Creating connection", null);
/*     */       }
/*     */       
/* 590 */       QueueConnection connection = factory.createQueueConnection();
/*     */       
/* 592 */       connection.start();
/*     */ 
/*     */       
/* 595 */       printWriter.println(NLSServices.getMessage("JMSMQ5011"));
/* 596 */       printWriter.flush();
/* 597 */       boolean transacted = false;
/*     */       
/* 599 */       if (Trace.isOn) {
/* 600 */         Trace.traceData(CLASSNAME, "Creating session", null);
/*     */       }
/*     */       
/* 603 */       QueueSession session = connection.createQueueSession(transacted, 1);
/*     */       
/* 605 */       if (jndiContext == null) {
/*     */         
/* 607 */         if (Trace.isOn) {
/* 608 */           Trace.traceData(CLASSNAME, "Creating a Q ourselves", null);
/*     */         }
/*     */         
/* 611 */         printWriter.println(NLSServices.getMessage("JMSMQ5012"));
/* 612 */         printWriter.flush();
/* 613 */         ioQueue = session.createQueue("SYSTEM.DEFAULT.LOCAL.QUEUE");
/*     */       }
/*     */       else {
/*     */         
/* 617 */         if (Trace.isOn) {
/* 618 */           Trace.traceData(CLASSNAME, "Obtaining a Q from JNDI (lookup key is ivtQ)", null);
/*     */         }
/*     */ 
/*     */         
/* 622 */         printWriter.println(NLSServices.getMessage("JMSMQ5013"));
/* 623 */         printWriter.flush();
/*     */         try {
/* 625 */           ioQueue = (Queue)jndiContext.lookup("ivtQ");
/*     */         }
/* 627 */         catch (Exception e) {
/* 628 */           if (Trace.isOn) {
/* 629 */             Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSIVT", "runIVT(Context,String,String,int,String,String,int)", e, 4);
/*     */           }
/*     */           
/*     */           try {
/* 633 */             if (Trace.isOn) {
/* 634 */               Trace.traceData(CLASSNAME, "Normal lookup failed with the following exception: ", null);
/*     */               
/* 636 */               Trace.traceData(CLASSNAME, "main", e);
/* 637 */               Trace.traceData(CLASSNAME, "Now trying with cn= prefix", null);
/*     */             } 
/*     */ 
/*     */             
/* 641 */             ioQueue = (Queue)jndiContext.lookup("cn=ivtQ");
/*     */           }
/* 643 */           catch (Exception e2) {
/* 644 */             if (Trace.isOn) {
/* 645 */               Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSIVT", "runIVT(Context,String,String,int,String,String,int)", e2, 5);
/*     */             }
/*     */             
/* 648 */             printWriter.println(NLSServices.getMessage("JMSMQ5014"));
/* 649 */             printWriter.println(e2);
/* 650 */             if (Trace.isOn) {
/* 651 */               Trace.traceData(CLASSNAME, "Extended lookup failed with the following exception: ", null);
/*     */               
/* 653 */               Trace.traceData(CLASSNAME, "main", e2);
/* 654 */               Trace.traceData(CLASSNAME, "Exiting via System.exit(-1)", null);
/* 655 */               Trace.exit(CLASSNAME, "main");
/*     */             } 
/* 657 */             printWriter.flush();
/* 658 */             ivtFail();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 664 */       printWriter.println(NLSServices.getMessage("JMSMQ5015"));
/* 665 */       printWriter.flush();
/* 666 */       if (Trace.isOn) {
/* 667 */         Trace.traceData(CLASSNAME, "Creating a sender", null);
/*     */       }
/* 669 */       QueueSender queueSender = session.createSender(ioQueue);
/*     */ 
/*     */       
/* 672 */       printWriter.println(NLSServices.getMessage("JMSMQ5016"));
/* 673 */       printWriter.flush();
/* 674 */       if (Trace.isOn) {
/* 675 */         Trace.traceData(CLASSNAME, "Creating a receiver", null);
/*     */       }
/* 677 */       QueueReceiver queueReceiver = session.createReceiver(ioQueue);
/*     */ 
/*     */       
/* 680 */       printWriter.println(NLSServices.getMessage("JMSMQ5017"));
/* 681 */       printWriter.flush();
/* 682 */       if (Trace.isOn) {
/* 683 */         Trace.traceData(CLASSNAME, "Creating a message", null);
/*     */       }
/* 685 */       TextMessage outMessage = session.createTextMessage();
/* 686 */       outMessage.setText(outString);
/*     */ 
/*     */       
/* 689 */       printWriter.println(NLSServices.getMessage("JMSMQ5018", new Object[] { "SYSTEM.DEFAULT.LOCAL.QUEUE" }));
/* 690 */       printWriter.flush();
/* 691 */       if (Trace.isOn) {
/* 692 */         Trace.traceData(CLASSNAME, "Sending the message", null);
/*     */       }
/* 694 */       queueSender.send((Message)outMessage);
/*     */ 
/*     */       
/* 697 */       printWriter.println(NLSServices.getMessage("JMSMQ5019"));
/* 698 */       printWriter.flush();
/* 699 */       if (Trace.isOn) {
/* 700 */         Trace.traceData(CLASSNAME, "Receiving the message", null);
/*     */       }
/* 702 */       Message inMessage = queueReceiver.receive(1000L);
/*     */       
/* 704 */       if (inMessage == null) {
/* 705 */         printWriter.println(NLSServices.getMessage("JMSMQ5020"));
/* 706 */         printWriter.flush();
/*     */ 
/*     */ 
/*     */         
/* 710 */         JMSException traceRet1 = new JMSException(NLSServices.getMessage("JMSMQ5021"));
/* 711 */         if (Trace.isOn) {
/* 712 */           Trace.throwing(this, "com.ibm.mq.jms.MQJMSIVT", "runIVT(Context,String,String,int,String,String,int)", (Throwable)traceRet1, 1);
/*     */         }
/*     */         
/* 715 */         throw traceRet1;
/*     */       } 
/*     */ 
/*     */       
/* 719 */       printWriter.println("\n" + NLSServices.getMessage("JMSMQ5022") + " " + inMessage);
/* 720 */       printWriter.flush();
/*     */       
/* 722 */       if (inMessage instanceof TextMessage) {
/* 723 */         if (Trace.isOn) {
/* 724 */           Trace.traceData(CLASSNAME, "Retrived message is a TextMessage; now checking for equality with the sent message", null);
/*     */         }
/*     */ 
/*     */         
/* 728 */         String replyString = ((TextMessage)inMessage).getText();
/* 729 */         if (replyString.equals(outString)) {
/* 730 */           if (Trace.isOn) {
/* 731 */             Trace.traceData(CLASSNAME, "Messages are equal. Great!", null);
/*     */           }
/*     */           
/* 734 */           printWriter.println(NLSServices.getMessage("JMSMQ5023"));
/* 735 */           printWriter.flush();
/*     */         } else {
/* 737 */           if (Trace.isOn) {
/* 738 */             Trace.traceData(CLASSNAME, "ERROR! Messages differ!", null);
/* 739 */             Trace.traceData(CLASSNAME, "Original=" + outString, null);
/* 740 */             Trace.traceData(CLASSNAME, "Reply=" + replyString, null);
/*     */           } 
/*     */           
/* 743 */           printWriter.println(NLSServices.getMessage("JMSMQ5024"));
/* 744 */           printWriter.println(NLSServices.getMessage("JMSMQ5025") + " = '" + outString + "'");
/*     */           
/* 746 */           printWriter.println(NLSServices.getMessage("JMSMQ5026") + " = '" + replyString + "'");
/*     */           
/* 748 */           printWriter.flush();
/*     */         } 
/*     */       } else {
/* 751 */         printWriter.println(NLSServices.getMessage("JMSMQ5027"));
/* 752 */         printWriter.flush();
/* 753 */         if (Trace.isOn) {
/* 754 */           Trace.traceData(CLASSNAME, "The retrieved message was not a TextMessage", null);
/*     */         }
/*     */ 
/*     */         
/* 758 */         JMSException traceRet2 = new JMSException(NLSServices.getMessage("JMSMQ5028"));
/* 759 */         if (Trace.isOn) {
/* 760 */           Trace.throwing(this, "com.ibm.mq.jms.MQJMSIVT", "runIVT(Context,String,String,int,String,String,int)", (Throwable)traceRet2, 2);
/*     */         }
/*     */         
/* 763 */         throw traceRet2;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 768 */       if (Trace.isOn) {
/* 769 */         Trace.traceData(CLASSNAME, "Closing down queues, session and connection", null);
/*     */       }
/*     */       
/* 772 */       printWriter.println(NLSServices.getMessage("JMSMQ5029"));
/* 773 */       printWriter.flush();
/* 774 */       queueReceiver.close();
/* 775 */       printWriter.println(NLSServices.getMessage("JMSMQ5030"));
/* 776 */       printWriter.flush();
/* 777 */       queueSender.close();
/*     */       
/* 779 */       printWriter.println(NLSServices.getMessage("JMSMQ5031"));
/* 780 */       printWriter.flush();
/* 781 */       session.close();
/* 782 */       session = null;
/*     */       
/* 784 */       printWriter.println(NLSServices.getMessage("JMSMQ5032"));
/* 785 */       printWriter.flush();
/* 786 */       connection.close();
/* 787 */       connection = null;
/*     */ 
/*     */       
/* 790 */       printWriter.println(NLSServices.getMessage("JMSMQ5033"));
/* 791 */       printWriter.flush();
/*     */     }
/* 793 */     catch (Exception e) {
/* 794 */       if (Trace.isOn) {
/* 795 */         Trace.catchBlock(this, "com.ibm.mq.jms.MQJMSIVT", "runIVT(Context,String,String,int,String,String,int)", e, 6);
/*     */       }
/*     */       
/* 798 */       printWriter.println(NLSServices.getMessage("JMSMQ5034"));
/* 799 */       printWriter.flush();
/* 800 */       handleException(e, printWriter);
/*     */       
/* 802 */       if (Trace.isOn) {
/* 803 */         Trace.traceData(CLASSNAME, "The following exception was caught: ", null);
/* 804 */         Trace.traceData(CLASSNAME, "main", e);
/*     */       } 
/*     */       
/* 807 */       ivtFail();
/*     */     } 
/*     */     
/* 810 */     printWriter.println(NLSServices.getMessage("JMSMQ5035"));
/* 811 */     printWriter.flush();
/* 812 */     if (Trace.isOn)
/* 813 */       Trace.exit(this, "com.ibm.mq.jms.MQJMSIVT", "runIVT(Context,String,String,int,String,String,int)"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\MQJMSIVT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */