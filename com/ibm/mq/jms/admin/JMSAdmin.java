/*      */ package com.ibm.mq.jms.admin;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*      */ import com.ibm.msg.client.commonservices.propertystore.PropertyStore;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.charset.Charset;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.SortedMap;
/*      */ import java.util.TreeMap;
/*      */ import java.util.Vector;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.JMSSecurityException;
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
/*      */ public class JMSAdmin
/*      */ {
/*      */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/JMSAdmin.java";
/*      */   private static final String DEF_CONFIG_FILENAME = "JMSAdmin.config";
/*      */   private static final int VERB_NON = -1;
/*      */   private static final int VERB_END = 0;
/*      */   private static final int VERB_DEF = 1;
/*      */   private static final int VERB_CHG = 2;
/*      */   private static final int VERB_DIS = 3;
/*      */   private static final int VERB_DEL = 4;
/*      */   private static final int VERB_COP = 5;
/*      */   private static final int VERB_MOV = 6;
/*      */   private static final int VERB_ALT = 7;
/*      */   
/*      */   static {
/*   55 */     if (Trace.isOn) {
/*   56 */       Trace.data("com.ibm.mq.jms.admin.JMSAdmin", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/JMSAdmin.java");
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
/*   74 */   private AdminService service = null;
/*      */   
/*      */   private boolean verbose = false;
/*   77 */   private String cfgFilename = "JMSAdmin.config";
/*   78 */   private String icf = null;
/*   79 */   private String purl = null;
/*   80 */   private String auth = "none";
/*   81 */   private String userdn = null;
/*   82 */   private String userpw = null;
/*   83 */   private static PrintWriter pw = null;
/*      */   
/*   85 */   private BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Properties config;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   96 */   private String wildCardCharacter = "*";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JMSAdmin(boolean verbosity, Properties cfg, AdminService svc, PrintWriter pw) {
/*  103 */     if (Trace.isOn)
/*  104 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "<init>(boolean,Properties,AdminService,PrintWriter)", new Object[] {
/*      */             
/*  106 */             Boolean.valueOf(verbosity), cfg, svc, pw
/*      */           }); 
/*  108 */     this.verbose = verbosity;
/*  109 */     this.cfgFilename = "UNDEFINED";
/*      */ 
/*      */ 
/*      */     
/*  113 */     if (pw == null) {
/*  114 */       initializePW();
/*      */     } else {
/*      */       
/*  117 */       JMSAdmin.pw = pw;
/*      */     } 
/*      */     
/*  120 */     if (cfg != null) {
/*  121 */       setConfigVariables(cfg);
/*      */     }
/*      */ 
/*      */     
/*  125 */     if (svc != null) {
/*  126 */       this.service = svc;
/*      */     }
/*      */ 
/*      */     
/*  130 */     checkServiceActive(this.service);
/*  131 */     if (Trace.isOn) {
/*  132 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "<init>(boolean,Properties,AdminService,PrintWriter)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkServiceActive(AdminService as) {
/*  143 */     if (Trace.isOn) {
/*  144 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "checkServiceActive(AdminService)", new Object[] { as });
/*      */     }
/*      */     
/*  147 */     if (as == null || !as.isActive()) {
/*  148 */       pw.println(ConfigEnvironment.getMessage("JMSADM4110"));
/*  149 */       pw.flush();
/*  150 */       System.exit(-1);
/*      */     }
/*  152 */     else if (this.verbose) {
/*  153 */       pw.println(ConfigEnvironment.getMessage("JMSADM4003"));
/*  154 */       pw.flush();
/*      */     } 
/*  156 */     if (Trace.isOn) {
/*  157 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "checkServiceActive(AdminService)");
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
/*      */   private JMSAdmin(boolean verbosity, String cfgfn) {
/*  170 */     if (Trace.isOn)
/*  171 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "<init>(boolean,String)", new Object[] {
/*  172 */             Boolean.valueOf(verbosity), cfgfn
/*      */           }); 
/*  174 */     this.verbose = verbosity;
/*  175 */     this.cfgFilename = cfgfn;
/*      */ 
/*      */     
/*  178 */     if (pw == null) {
/*  179 */       initializePW();
/*      */     }
/*      */     
/*  182 */     loadConfig(this.cfgFilename);
/*      */     
/*      */     try {
/*  185 */       this.service = new AdminService(this.icf, this.purl, this.auth, this.config, verbosity);
/*      */     }
/*  187 */     catch (JMSSecurityException jmse) {
/*  188 */       if (Trace.isOn) {
/*  189 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "<init>(boolean,String)", (Throwable)jmse, 1);
/*      */       }
/*      */       
/*  192 */       pw.println(jmse);
/*  193 */       pw.flush();
/*  194 */       System.exit(-1);
/*      */     } 
/*      */     
/*  197 */     pw.println();
/*  198 */     pw.println(ConfigEnvironment.getMessage("JMSADM1003"));
/*  199 */     pw.println(ConfigEnvironment.getMessage("JMSADM4000"));
/*  200 */     pw.println();
/*  201 */     pw.flush();
/*      */     
/*      */     try {
/*  204 */       if (this.verbose) {
/*  205 */         pw.println(ConfigEnvironment.getMessage("JMSADM4002"));
/*  206 */         pw.println("   " + ConfigEnvironment.getMessage("JMSADM4094") + ": " + this.service.getICF());
/*  207 */         pw.println("   " + ConfigEnvironment.getMessage("JMSADM4095") + ": " + this.service.getPURL());
/*  208 */         pw.flush();
/*      */       } 
/*      */       
/*  211 */       if (this.service.needsAuthorization()) {
/*      */         
/*  213 */         if (this.userdn == null) {
/*  214 */           pw.print(ConfigEnvironment.getMessage("JMSADM4134") + ": ");
/*  215 */           pw.flush();
/*  216 */           this.userdn = this.in.readLine();
/*      */         } 
/*      */         
/*  219 */         if (this.userpw == null) {
/*  220 */           pw.print(ConfigEnvironment.getMessage("JMSADM4135") + ": ");
/*  221 */           pw.flush();
/*  222 */           this.userpw = this.in.readLine();
/*      */         } 
/*      */         
/*  225 */         pw.println("");
/*  226 */         pw.flush();
/*      */       } 
/*      */       
/*  229 */       this.service.initJNDI(this.userdn, this.userpw);
/*      */     
/*      */     }
/*  232 */     catch (Exception e) {
/*  233 */       if (Trace.isOn) {
/*  234 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "<init>(boolean,String)", e, 2);
/*      */       }
/*  236 */       if (this.verbose) {
/*  237 */         pw.println(ConfigEnvironment.getMessage("JMSADM4008") + ": " + e);
/*  238 */         pw.flush();
/*      */       } 
/*  240 */       System.exit(-1);
/*      */     } 
/*      */ 
/*      */     
/*  244 */     checkServiceActive(this.service);
/*      */ 
/*      */ 
/*      */     
/*  248 */     commandLoop();
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  253 */       this.service.stopJNDI();
/*      */     
/*      */     }
/*  256 */     catch (Exception e) {
/*  257 */       if (Trace.isOn) {
/*  258 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "<init>(boolean,String)", e, 3);
/*      */       }
/*  260 */       if (this.verbose) {
/*  261 */         pw.println(ConfigEnvironment.getMessage("JMSADM4008") + ": " + e);
/*  262 */         pw.flush();
/*      */       } 
/*      */     } 
/*  265 */     if (Trace.isOn) {
/*  266 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "<init>(boolean,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void commandLoop() {
/*  276 */     if (Trace.isOn) {
/*  277 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "commandLoop()");
/*      */     }
/*  279 */     boolean carryOn = true;
/*      */     
/*  281 */     String inLine = null;
/*      */     
/*  283 */     if (this.verbose) {
/*  284 */       pw.println("\n" + ConfigEnvironment.getMessage("JMSADM4085") + "\n");
/*      */     }
/*  286 */     pw.flush();
/*  287 */     while (carryOn) {
/*  288 */       pw.print(this.service.getPathString() + "> ");
/*  289 */       pw.flush();
/*  290 */       StringBuffer buf = new StringBuffer();
/*      */       
/*      */       try {
/*  293 */         boolean sameCommand = true;
/*  294 */         while (sameCommand) {
/*  295 */           inLine = this.in.readLine();
/*      */           
/*  297 */           if (inLine != null) {
/*  298 */             if (inLine.length() == 0) {
/*  299 */               sameCommand = false;
/*      */             }
/*  301 */             else if (inLine.charAt(0) == '*' || inLine.charAt(0) == '#' || inLine.charAt(0) == '/') {
/*  302 */               inLine = "";
/*  303 */               sameCommand = false;
/*      */             }
/*  305 */             else if (inLine.charAt(inLine.length() - 1) != '+') {
/*  306 */               sameCommand = false;
/*      */             } else {
/*      */               
/*  309 */               inLine = inLine.substring(0, inLine.length() - 1);
/*      */             } 
/*      */             
/*  312 */             if (inLine.length() > 0) {
/*  313 */               buf.append(" " + inLine);
/*      */             }
/*      */           }
/*      */         
/*      */         } 
/*  318 */       } catch (IOException ex) {
/*  319 */         if (Trace.isOn) {
/*  320 */           Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "commandLoop()", ex, 1);
/*      */         }
/*  322 */         pw.println(ConfigEnvironment.getMessage("JMSADM4086") + ": " + ex);
/*  323 */         pw.flush();
/*      */       } 
/*      */ 
/*      */       
/*  327 */       inLine = buf.toString();
/*      */ 
/*      */       
/*  330 */       if (inLine.length() > 0) {
/*      */         
/*  332 */         if (!execCommand(inLine)) {
/*  333 */           carryOn = false;
/*      */         }
/*      */ 
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/*  340 */       pw.println("");
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  346 */       this.in.close();
/*  347 */       pw.flush();
/*      */     }
/*  349 */     catch (IOException ex) {
/*  350 */       if (Trace.isOn) {
/*  351 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "commandLoop()", ex, 2);
/*      */       }
/*  353 */       if (this.verbose) {
/*  354 */         pw.println(ConfigEnvironment.getMessage("JMSADM4008") + ": " + ex);
/*  355 */         pw.flush();
/*      */       } 
/*      */     } 
/*      */     
/*  359 */     exitMessage();
/*  360 */     if (Trace.isOn) {
/*  361 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "commandLoop()");
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
/*      */   protected boolean execCommand(String cmdLine) {
/*  375 */     if (Trace.isOn) {
/*  376 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "execCommand(String)", new Object[] { cmdLine });
/*      */     }
/*      */     
/*  379 */     boolean retCode = true;
/*      */     
/*  381 */     Command c = new Command(cmdLine);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  387 */     if (c.getArgCount() == -1) {
/*  388 */       pw.println(ConfigEnvironment.getMessage("JMSADM4120"));
/*  389 */       pw.flush();
/*      */     } else {
/*      */       
/*  392 */       int v = c.getVerb();
/*      */       
/*  394 */       if (v == 0) {
/*  395 */         if (c.getObjType() == -1 && c.getObjName() == null) {
/*  396 */           retCode = false;
/*      */         } else {
/*      */           
/*  399 */           pw.println(ConfigEnvironment.getMessage("JMSADM4120"));
/*      */         } 
/*  401 */         pw.flush();
/*      */       
/*      */       }
/*  404 */       else if (v == 1) {
/*  405 */         doDefine(c);
/*      */       
/*      */       }
/*  408 */       else if (v == 7) {
/*  409 */         doAlter(c);
/*      */       
/*      */       }
/*  412 */       else if (v == 2) {
/*  413 */         if (c.getObjType() == -2) {
/*  414 */           String ctxname = c.getObjName();
/*      */           try {
/*  416 */             this.service.chContext(ctxname);
/*      */           }
/*  418 */           catch (Exception e) {
/*  419 */             if (Trace.isOn) {
/*  420 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "execCommand(String)", e);
/*      */             }
/*  422 */             pw.println(ConfigEnvironment.getMessage("JMSADM4097"));
/*  423 */             pw.flush();
/*      */           } 
/*      */         } else {
/*      */           
/*  427 */           pw.println(ConfigEnvironment.getMessage("JMSADM4120"));
/*  428 */           pw.flush();
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  433 */       else if (v == 3) {
/*      */         
/*  435 */         if (c.getObjType() == -2) {
/*  436 */           if (c.getObjName() == null && c.getArgCount() == 0) {
/*  437 */             doDisplayCtx();
/*      */           } else {
/*      */             
/*  440 */             pw.println(ConfigEnvironment.getMessage("JMSADM4120"));
/*      */           }
/*      */         
/*      */         }
/*  444 */         else if (c.getArgCount() == -1) {
/*  445 */           pw.println(ConfigEnvironment.getMessage("JMSADM4120"));
/*      */         } else {
/*      */           
/*  448 */           doDisplay(c);
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*  454 */       else if (v == 4) {
/*  455 */         if (c.getObjType() == -2) {
/*  456 */           doDeleteCtx(c.getObjName());
/*      */         } else {
/*      */           
/*  459 */           doDelete(c);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  464 */       else if (v == 5) {
/*  465 */         doCopy(c);
/*      */ 
/*      */       
/*      */       }
/*  469 */       else if (v == 6) {
/*  470 */         doMove(c);
/*      */ 
/*      */       
/*      */       }
/*  474 */       else if (c.getVerbString() != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  479 */         pw.println(ConfigEnvironment.getMessage("JMSADM4087") + ": " + c.getVerbString());
/*      */       } 
/*      */     } 
/*  482 */     pw.println("");
/*  483 */     pw.flush();
/*      */     
/*  485 */     if (Trace.isOn) {
/*  486 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "execCommand(String)", 
/*  487 */           Boolean.valueOf(retCode));
/*      */     }
/*  489 */     return retCode;
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
/*      */   private void loadConfig(String fn) {
/*  507 */     if (Trace.isOn) {
/*  508 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "loadConfig(String)", new Object[] { fn });
/*      */     }
/*  510 */     this.config = new Properties();
/*      */ 
/*      */     
/*  513 */     try (FileInputStream inStream = new FileInputStream(fn)) {
/*  514 */       this.config.load(inStream);
/*      */     
/*      */     }
/*  517 */     catch (IOException e1) {
/*  518 */       if (Trace.isOn) {
/*  519 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "loadConfig(String)", e1, 1);
/*      */       }
/*  521 */       pw.println(ConfigEnvironment.getMessage("JMSADM4121") + ": " + fn);
/*  522 */       String newfn = null;
/*      */       try {
/*  524 */         String sep = PropertyStore.file_separator;
/*  525 */         String installPath = "MQ_JAVA_INSTALL_PATH";
/*  526 */         PropertyStore.register(installPath, "");
/*  527 */         newfn = PropertyStore.getStringProperty(installPath) + sep + "bin" + sep + fn;
/*      */         
/*  529 */         try (FileInputStream inStream = new FileInputStream(newfn)) {
/*  530 */           this.config.load(inStream);
/*      */         }
/*      */       
/*  533 */       } catch (IOException e2) {
/*  534 */         if (Trace.isOn) {
/*  535 */           Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "loadConfig(String)", e2, 2);
/*      */         }
/*  537 */         pw.println(ConfigEnvironment.getMessage("JMSADM4121") + ": " + newfn);
/*  538 */         if (this.verbose) {
/*  539 */           pw.println("\n" + e2);
/*      */         }
/*  541 */         pw.flush();
/*  542 */         if (Trace.isOn) {
/*  543 */           Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "loadConfig(String)", 1);
/*      */         }
/*      */         
/*  546 */         System.exit(-1);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  551 */     setConfigVariables(this.config);
/*  552 */     if (Trace.isOn) {
/*  553 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "loadConfig(String)", 2);
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
/*      */   private void setConfigVariables(Properties cfg) {
/*  566 */     if (Trace.isOn) {
/*  567 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "setConfigVariables(Properties)", new Object[] { cfg });
/*      */     }
/*      */     
/*  570 */     this.icf = getMandatoryProperty(cfg, "INITIAL_CONTEXT_FACTORY");
/*  571 */     this.purl = getMandatoryProperty(cfg, "PROVIDER_URL");
/*  572 */     String thisauth = cfg.getProperty("SECURITY_AUTHENTICATION");
/*  573 */     this.userdn = cfg.getProperty("PROVIDER_USERDN");
/*  574 */     this.userpw = cfg.getProperty("PROVIDER_PASSWORD");
/*      */ 
/*      */     
/*  577 */     this.icf = this.icf.trim();
/*  578 */     this.purl = this.purl.trim();
/*      */ 
/*      */ 
/*      */     
/*  582 */     if (thisauth != null) {
/*  583 */       this.auth = thisauth;
/*      */     }
/*  585 */     if (Trace.isOn) {
/*  586 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "setConfigVariables(Properties)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private String getMandatoryProperty(Properties cfg, String propertyName) {
/*  592 */     if (Trace.isOn) {
/*  593 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "getMandatoryProperty(Properties, String)", new Object[] { cfg, propertyName });
/*      */     }
/*      */     
/*  596 */     String result = cfg.getProperty(propertyName);
/*  597 */     if (result == null) {
/*  598 */       Exception e = new Exception(ConfigEnvironment.getMessage("JMSADM4105 : " + propertyName));
/*  599 */       pw.println(e);
/*  600 */       pw.flush();
/*  601 */       if (Trace.isOn) {
/*  602 */         Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "getMandatoryProperty(Properties, String)", 1);
/*      */       }
/*  604 */       System.exit(-1);
/*      */     } 
/*  606 */     if (Trace.isOn) {
/*  607 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "getMandatoryProperty(Properties, String)", result);
/*      */     }
/*  609 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void doDefine(Command c) {
/*  619 */     if (Trace.isOn) {
/*  620 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDefine(Command)", new Object[] { c });
/*      */     }
/*  622 */     AdminObject aobj = null;
/*      */     
/*  624 */     if (c.getObjType() == -2) {
/*  625 */       String ctxname = c.getObjName();
/*      */       try {
/*  627 */         this.service.mkContext(ctxname);
/*      */       }
/*  629 */       catch (Exception e) {
/*  630 */         if (Trace.isOn) {
/*  631 */           Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDefine(Command)", e, 1);
/*      */         }
/*  633 */         pw.println(ConfigEnvironment.getMessage("JMSADM4111"));
/*  634 */         if (this.verbose) {
/*  635 */           pw.println("\n" + e);
/*      */         }
/*      */         
/*  638 */         pw.flush();
/*      */       } 
/*      */     } else {
/*      */       
/*  642 */       Map<String, Object> props = new HashMap<>();
/*  643 */       int argcount = c.getArgCount();
/*      */       
/*  645 */       for (int i = 0; i < argcount; i++) {
/*      */ 
/*      */ 
/*      */         
/*  649 */         if (c.getArgValue(i) != null) {
/*  650 */           if (c.getArgValue(i).length() > 0) {
/*  651 */             props.put(c.getArgName(i), c.getArgValue(i));
/*      */           }
/*      */         } else {
/*      */           
/*  655 */           props.put(c.getArgName(i), "<null>");
/*      */         } 
/*      */       } 
/*      */       
/*  659 */       aobj = new AdminObject(c.getObjType(), props);
/*      */       
/*  661 */       if (aobj.errorRaised()) {
/*  662 */         pw.println(ConfigEnvironment.getMessage("JMSADM4112"));
/*  663 */         pw.flush();
/*  664 */         int errType = aobj.getErrorType();
/*      */         
/*  666 */         switch (errType) {
/*      */           case 0:
/*  668 */             pw.println(ConfigEnvironment.getMessage("JMSADM4129", aobj.getErrorString1()));
/*  669 */             pw.flush();
/*      */             break;
/*      */           
/*      */           case 1:
/*  673 */             pw.println(ConfigEnvironment.getMessage("JMSADM4125", aobj.getErrorString1(), aobj.getErrorString2()));
/*  674 */             pw.flush();
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 3:
/*  686 */             pw.println(ConfigEnvironment.getMessage("JMSADM4127") + ": " + aobj.getErrorString1());
/*  687 */             pw.flush();
/*      */             break;
/*      */           
/*      */           case 4:
/*  691 */             pw.println(ConfigEnvironment.getMessage("JMSADM4128", aobj.getErrorString1(), aobj.getErrorString2()));
/*  692 */             pw.flush();
/*      */             break;
/*      */           
/*      */           case 5:
/*  696 */             pw.println(aobj.getErrorString1());
/*  697 */             pw.flush();
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  705 */         aobj.resetError();
/*      */       } else {
/*      */         
/*      */         try {
/*  709 */           this.service.bind(c.getObjName(), aobj);
/*      */         }
/*  711 */         catch (Exception e) {
/*  712 */           if (Trace.isOn) {
/*  713 */             Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDefine(Command)", e, 2);
/*      */           }
/*  715 */           pw.println(ConfigEnvironment.getMessage("JMSADM4113"));
/*  716 */           if (this.verbose) {
/*  717 */             pw.println("\n" + e);
/*      */           }
/*      */           
/*  720 */           pw.flush();
/*      */         } 
/*      */       } 
/*      */     } 
/*  724 */     if (Trace.isOn) {
/*  725 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDefine(Command)");
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
/*      */   private void doAlter(Command c) {
/*  739 */     if (Trace.isOn) {
/*  740 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "doAlter(Command)", new Object[] { c });
/*      */     }
/*  742 */     int objType = c.getObjType();
/*  743 */     String alias = c.getObjName();
/*  744 */     AdminObject aobj = null;
/*      */     
/*  746 */     if (alias == null) {
/*  747 */       if (Trace.isOn) {
/*  748 */         Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doAlter(Command)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     try {
/*  754 */       aobj = this.service.get(alias);
/*      */     }
/*  756 */     catch (Exception e) {
/*  757 */       if (Trace.isOn) {
/*  758 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doAlter(Command)", e, 1);
/*      */       }
/*  760 */       if (this.verbose) {
/*  761 */         pw.println(ConfigEnvironment.getMessage("JMSADM4008") + ": " + e);
/*  762 */         pw.flush();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  768 */     if (aobj == null) {
/*  769 */       pw.println(ConfigEnvironment.getMessage("JMSADM4096"));
/*  770 */       pw.flush();
/*  771 */       if (Trace.isOn) {
/*  772 */         Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doAlter(Command)", 2);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  779 */     if (!objectTypeMatch(objType, aobj.getType())) {
/*  780 */       if (Trace.isOn) {
/*  781 */         Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doAlter(Command)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  787 */     Map<String, Object> props = aobj.getProperties();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  793 */     if (isConnectionFactoryObject(aobj) && props.get("TRANSPORT") != null) {
/*  794 */       if (props.get("TRANSPORT").equals("BIND")) {
/*      */         
/*  796 */         removeClientProperties(props);
/*  797 */         removeDirectProperties(props);
/*      */       } 
/*  799 */       if (props.get("TRANSPORT").equals("CLIENT"))
/*      */       {
/*  801 */         removeDirectProperties(props);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  809 */     String transportStartedAs = null;
/*  810 */     if (props.get("TRANSPORT") != null) {
/*  811 */       transportStartedAs = props.get("TRANSPORT").toString().toUpperCase();
/*      */     }
/*      */     
/*  814 */     boolean userSetCNL = false;
/*      */ 
/*      */     
/*  817 */     int argcount = c.getArgCount();
/*      */     
/*  819 */     for (int i = 0; i < argcount; i++) {
/*      */ 
/*      */ 
/*      */       
/*  823 */       if (c.getArgValue(i) == null) {
/*  824 */         String longName = toLongName(c.getArgName(i));
/*      */ 
/*      */         
/*  827 */         if (longName != null)
/*      */         {
/*  829 */           props.remove(longName);
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*      */           
/*  838 */           pw.println(ConfigEnvironment.getMessage("JMSADM4125", aobj.getObject().getClass().getName(), c.getArgName(i)));
/*  839 */           pw.flush();
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  846 */         String argName = c.getArgName(i);
/*  847 */         String argValue = c.getArgValue(i);
/*  848 */         String longName = toLongName(argName);
/*      */ 
/*      */ 
/*      */         
/*  852 */         if (longName == null) {
/*      */ 
/*      */           
/*  855 */           pw.println(ConfigEnvironment.getMessage("JMSADM4125", aobj.getObject().getClass().getName(), argName));
/*  856 */           pw.flush();
/*      */         
/*      */         }
/*  859 */         else if (longName.equalsIgnoreCase("HOSTNAME") && !userSetCNL) {
/*      */           
/*  861 */           String originalConnectionNameList = (String)props.get("CONNECTIONNAMELIST");
/*  862 */           if (originalConnectionNameList != null) {
/*  863 */             String strippedConnectionNameList; int endHost = originalConnectionNameList.indexOf('(');
/*  864 */             if (endHost == -1) {
/*      */               
/*  866 */               if (originalConnectionNameList.length() > 0) {
/*      */                 
/*  868 */                 endHost = originalConnectionNameList.indexOf(',');
/*  869 */                 if (endHost == -1) {
/*      */                   
/*  871 */                   strippedConnectionNameList = originalConnectionNameList;
/*      */                 } else {
/*      */                   
/*  874 */                   strippedConnectionNameList = originalConnectionNameList.substring(endHost);
/*      */                 }
/*      */               
/*      */               } else {
/*      */                 
/*  879 */                 strippedConnectionNameList = "";
/*      */               } 
/*      */             } else {
/*      */               
/*  883 */               strippedConnectionNameList = originalConnectionNameList.substring(endHost);
/*      */             } 
/*  885 */             if (argValue != null && 
/*  886 */               argValue.trim().length() != 0 && !argValue.trim().equals("()") && !argValue.trim().equals(","))
/*      */             {
/*  888 */               props.put("CONNECTIONNAMELIST", argValue + strippedConnectionNameList);
/*      */             }
/*      */           }
/*      */         
/*      */         }
/*  893 */         else if (longName.equalsIgnoreCase("PORT") && !userSetCNL) {
/*  894 */           String originalConnectionNameList = (String)props.get("CONNECTIONNAMELIST");
/*  895 */           if (originalConnectionNameList != null) {
/*  896 */             int startPort = originalConnectionNameList.indexOf('(');
/*  897 */             int endPort = originalConnectionNameList.indexOf(')');
/*      */             
/*  899 */             String uptoPort = originalConnectionNameList.substring(0, startPort + 1);
/*  900 */             String afterPort = originalConnectionNameList.substring(endPort);
/*  901 */             String strippedConnectionNameList = uptoPort + argValue + afterPort;
/*  902 */             props.put("CONNECTIONNAMELIST", strippedConnectionNameList);
/*      */           } 
/*      */         } else {
/*      */           
/*  906 */           if (longName.equalsIgnoreCase("CONNECTIONNAMELIST"))
/*      */           {
/*      */ 
/*      */             
/*  910 */             userSetCNL = true;
/*      */           }
/*      */ 
/*      */           
/*  914 */           props.put(longName, argValue);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  923 */     props.remove("VERSION");
/*      */ 
/*      */ 
/*      */     
/*  927 */     String transportEndedAs = null;
/*  928 */     if (props.get("TRANSPORT") != null) {
/*  929 */       transportEndedAs = props.get("TRANSPORT").toString().toUpperCase();
/*      */     }
/*      */     
/*  932 */     if (isConnectionFactoryObject(aobj)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  941 */       if (transportStartedAs.equals("CLIENT") && transportEndedAs.equals("BIND")) {
/*      */         
/*  943 */         removeClientProperties(props);
/*  944 */         removeDirectProperties(props);
/*      */       } 
/*      */       
/*  947 */       if (transportStartedAs.equals("DIRECT") && transportEndedAs.equals("BIND")) {
/*      */         
/*  949 */         removeClientProperties(props);
/*  950 */         removeDirectProperties(props);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  955 */       if (transportStartedAs.equals("DIRECTHTTP") && transportEndedAs.equals("BIND")) {
/*  956 */         removeClientProperties(props);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  962 */     AdminObject newObject = new AdminObject(c.getObjType(), props);
/*      */     
/*  964 */     if (newObject.errorRaised()) {
/*  965 */       pw.println(ConfigEnvironment.getMessage("JMSADM4112"));
/*  966 */       pw.flush();
/*  967 */       int errType = newObject.getErrorType();
/*      */       
/*  969 */       switch (errType) {
/*      */         case 0:
/*  971 */           pw.println(ConfigEnvironment.getMessage("JMSADM4129", newObject.getErrorString1()));
/*  972 */           pw.flush();
/*      */           break;
/*      */         
/*      */         case 1:
/*  976 */           pw.println(ConfigEnvironment.getMessage("JMSADM4125", newObject.getErrorString1(), newObject.getErrorString2()));
/*  977 */           pw.flush();
/*      */           break;
/*      */         
/*      */         case 3:
/*  981 */           pw.println(ConfigEnvironment.getMessage("JMSADM4127") + ": " + newObject.getErrorString1());
/*  982 */           pw.flush();
/*      */           break;
/*      */         
/*      */         case 4:
/*  986 */           pw.println(ConfigEnvironment.getMessage("JMSADM4128", newObject.getErrorString1(), newObject.getErrorString2()));
/*  987 */           pw.flush();
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  995 */       newObject.resetError();
/*      */     } else {
/*      */ 
/*      */       
/*      */       try {
/* 1000 */         this.service.rebind(c.getObjName(), newObject);
/*      */       }
/* 1002 */       catch (Exception e) {
/* 1003 */         if (Trace.isOn) {
/* 1004 */           Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doAlter(Command)", e, 2);
/*      */         }
/* 1006 */         pw.println(ConfigEnvironment.getMessage("JMSADM4113"));
/* 1007 */         if (this.verbose) {
/* 1008 */           pw.println("\n" + e);
/*      */         }
/*      */         
/* 1011 */         pw.flush();
/*      */       } 
/*      */     } 
/* 1014 */     if (Trace.isOn) {
/* 1015 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doAlter(Command)", 4);
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
/*      */   private boolean isConnectionFactoryObject(AdminObject o) {
/* 1029 */     if (Trace.isOn) {
/* 1030 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "isConnectionFactoryObject(AdminObject)", new Object[] { o });
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
/*      */     try {
/* 1043 */       BAO tmp = (BAO)o.getBAOObject();
/*      */ 
/*      */ 
/*      */       
/* 1047 */       if (tmp.name().indexOf("QCF") != -1 || tmp.name().indexOf("TCF") != -1 || tmp.name().indexOf("CF") != -1) {
/*      */         
/* 1049 */         if (Trace.isOn) {
/* 1050 */           Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "isConnectionFactoryObject(AdminObject)", 
/* 1051 */               Boolean.valueOf(true), 1);
/*      */         }
/* 1053 */         return true;
/*      */       } 
/*      */       
/* 1056 */       if (Trace.isOn) {
/* 1057 */         Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "isConnectionFactoryObject(AdminObject)", 
/* 1058 */             Boolean.valueOf(false), 2);
/*      */       }
/* 1060 */       return false;
/*      */     
/*      */     }
/* 1063 */     catch (Exception e) {
/* 1064 */       if (Trace.isOn) {
/* 1065 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "isConnectionFactoryObject(AdminObject)", e);
/*      */       }
/*      */ 
/*      */       
/* 1069 */       if (Trace.isOn) {
/* 1070 */         Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "isConnectionFactoryObject(AdminObject)", 
/* 1071 */             Boolean.valueOf(false), 3);
/*      */       }
/* 1073 */       return false;
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
/*      */   private void removeClientProperties(Map<String, Object> props) {
/* 1087 */     if (Trace.isOn) {
/* 1088 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "removeClientProperties(Map<String , Object>)", new Object[] { props });
/*      */     }
/*      */     
/* 1091 */     props.remove("HOSTNAME");
/* 1092 */     props.remove("CHANNEL");
/* 1093 */     props.remove("RECEXIT");
/* 1094 */     props.remove("RECEXITINIT");
/* 1095 */     props.remove("SECEXIT");
/* 1096 */     props.remove("SECEXITINIT");
/* 1097 */     props.remove("SENDEXIT");
/* 1098 */     props.remove("SENDEXITINIT");
/* 1099 */     props.remove("SSLCIPHERSUITE");
/* 1100 */     props.remove("SSLCRL");
/* 1101 */     props.remove("SSLPEERNAME");
/* 1102 */     props.remove("SENDEXIT");
/* 1103 */     props.remove("SSLPEERNAME");
/* 1104 */     props.remove("PORT");
/* 1105 */     props.remove("CCSID");
/* 1106 */     props.remove("LOCALADDRESS");
/* 1107 */     props.remove("SSLRESETCOUNT");
/* 1108 */     props.remove("SSLFIPSREQUIRED");
/* 1109 */     props.remove("CCDTURL");
/* 1110 */     props.remove("CLIENTRECONNECTOPTIONS");
/* 1111 */     props.remove("CONNECTIONNAMELIST");
/* 1112 */     props.remove("CLIENTRECONNECTTIMEOUT");
/*      */     
/* 1114 */     if (Trace.isOn) {
/* 1115 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "removeClientProperties(Map<String , Object>)");
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
/*      */   private void removeDirectProperties(Map<String, Object> props) {
/* 1130 */     if (Trace.isOn) {
/* 1131 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "removeDirectProperties(Map<String , Object>)", new Object[] { props });
/*      */     }
/*      */     
/* 1134 */     props.remove("MULTICAST");
/* 1135 */     props.remove("PROXYHOSTNAME");
/* 1136 */     props.remove("PROXYPORT");
/* 1137 */     props.remove("DIRECTAUTH");
/* 1138 */     props.remove("MAXBUFFSIZE");
/*      */     
/* 1140 */     if (Trace.isOn) {
/* 1141 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "removeDirectProperties(Map<String , Object>)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String toLongName(String p) {
/* 1152 */     if (Trace.isOn) {
/* 1153 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "toLongName(String)", new Object[] { p });
/*      */     }
/* 1155 */     String traceRet1 = AP.getLongName(p);
/* 1156 */     if (Trace.isOn) {
/* 1157 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "toLongName(String)", traceRet1);
/*      */     }
/* 1159 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void doCopy(Command c) {
/* 1169 */     if (Trace.isOn) {
/* 1170 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "doCopy(Command)", new Object[] { c });
/*      */     }
/* 1172 */     int objType = c.getObjType();
/* 1173 */     String alias = c.getObjName();
/* 1174 */     AdminObject aobj = null;
/*      */     
/* 1176 */     if (alias == null) {
/* 1177 */       if (Trace.isOn) {
/* 1178 */         Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doCopy(Command)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     try {
/* 1184 */       aobj = this.service.get(alias);
/*      */     }
/* 1186 */     catch (Exception e) {
/* 1187 */       if (Trace.isOn) {
/* 1188 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doCopy(Command)", e, 1);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1194 */     if (aobj == null) {
/* 1195 */       pw.println(ConfigEnvironment.getMessage("JMSADM4096"));
/* 1196 */       pw.flush();
/* 1197 */       if (Trace.isOn) {
/* 1198 */         Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doCopy(Command)", 2);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1205 */     if (!objectTypeMatch(objType, aobj.getType())) {
/* 1206 */       if (Trace.isOn) {
/* 1207 */         Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doCopy(Command)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     try {
/* 1213 */       this.service.bind(c.getArgValue(0), aobj);
/*      */     }
/* 1215 */     catch (Exception e) {
/* 1216 */       if (Trace.isOn) {
/* 1217 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doCopy(Command)", e, 2);
/*      */       }
/* 1219 */       pw.println(ConfigEnvironment.getMessage("JMSADM4113"));
/* 1220 */       if (this.verbose) {
/* 1221 */         pw.println("\n" + e);
/*      */       }
/* 1223 */       pw.flush();
/*      */     } 
/* 1225 */     if (Trace.isOn) {
/* 1226 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doCopy(Command)", 4);
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
/*      */   private void doMove(Command c) {
/* 1238 */     if (Trace.isOn) {
/* 1239 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "doMove(Command)", new Object[] { c });
/*      */     }
/*      */     
/* 1242 */     int objType = c.getObjType();
/* 1243 */     String alias = c.getObjName();
/* 1244 */     AdminObject aobj = null;
/*      */     
/* 1246 */     if (alias == null) {
/* 1247 */       if (Trace.isOn) {
/* 1248 */         Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doMove(Command)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     try {
/* 1254 */       aobj = this.service.get(alias);
/*      */     }
/* 1256 */     catch (Exception e) {
/* 1257 */       if (Trace.isOn) {
/* 1258 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doMove(Command)", e, 1);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1264 */     if (aobj == null) {
/* 1265 */       pw.println(ConfigEnvironment.getMessage("JMSADM4096"));
/* 1266 */       pw.flush();
/* 1267 */       if (Trace.isOn) {
/* 1268 */         Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doMove(Command)", 2);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1275 */     if (!objectTypeMatch(objType, aobj.getType())) {
/* 1276 */       if (Trace.isOn) {
/* 1277 */         Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doMove(Command)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*      */     try {
/* 1284 */       this.service.bind(c.getArgValue(0), aobj);
/*      */     }
/* 1286 */     catch (Exception e) {
/* 1287 */       if (Trace.isOn) {
/* 1288 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doMove(Command)", e, 2);
/*      */       }
/* 1290 */       pw.println(ConfigEnvironment.getMessage("JMSADM4113"));
/* 1291 */       if (this.verbose) {
/* 1292 */         pw.println("\n" + e);
/*      */       }
/* 1294 */       pw.flush();
/* 1295 */       if (Trace.isOn) {
/* 1296 */         Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doMove(Command)", 4);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*      */     try {
/* 1303 */       this.service.unbind(alias);
/*      */     }
/* 1305 */     catch (Exception e) {
/* 1306 */       if (Trace.isOn) {
/* 1307 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doMove(Command)", e, 3);
/*      */       }
/* 1309 */       pw.println(ConfigEnvironment.getMessage("JMSADM4122"));
/* 1310 */       if (this.verbose) {
/* 1311 */         pw.println("\n" + e);
/*      */       }
/* 1313 */       pw.flush();
/*      */     } 
/* 1315 */     if (Trace.isOn) {
/* 1316 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doMove(Command)", 5);
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
/*      */   private void doDisplay(Command c) {
/* 1328 */     if (Trace.isOn) {
/* 1329 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDisplay(Command)", new Object[] { c });
/*      */     }
/* 1331 */     int objType = c.getObjType();
/* 1332 */     String alias = c.getObjName();
/* 1333 */     AdminObject aobj = null;
/*      */     
/* 1335 */     if (alias == null) {
/* 1336 */       if (Trace.isOn) {
/* 1337 */         Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDisplay(Command)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1344 */     if (alias.indexOf(this.wildCardCharacter) != -1) {
/*      */       
/*      */       try {
/*      */ 
/*      */         
/* 1349 */         this.service.get(alias);
/*      */       
/*      */       }
/* 1352 */       catch (Exception e) {
/* 1353 */         if (Trace.isOn) {
/* 1354 */           Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDisplay(Command)", e, 1);
/*      */         }
/*      */         
/* 1357 */         doWildcardDisplay(c);
/* 1358 */         if (Trace.isOn) {
/* 1359 */           Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDisplay(Command)", 2);
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/*      */     }
/*      */     
/*      */     try {
/* 1367 */       aobj = this.service.get(alias);
/*      */     }
/* 1369 */     catch (JMSException e) {
/* 1370 */       if (Trace.isOn) {
/* 1371 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDisplay(Command)", (Throwable)e, 2);
/*      */       }
/* 1373 */       pw.println(e.getMessage());
/* 1374 */       pw.flush();
/*      */     }
/* 1376 */     catch (Exception e) {
/* 1377 */       if (Trace.isOn) {
/* 1378 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDisplay(Command)", e, 3);
/*      */       }
/* 1380 */       pw.println(ConfigEnvironment.getMessage("JMSADM4096"));
/* 1381 */       if (this.verbose) {
/* 1382 */         pw.println("\n" + e);
/*      */       }
/* 1384 */       pw.flush();
/*      */     } 
/*      */     
/* 1387 */     if (aobj == null) {
/* 1388 */       if (Trace.isOn) {
/* 1389 */         Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDisplay(Command)", 3);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1396 */     if (!objectTypeMatch(objType, aobj.getType())) {
/* 1397 */       if (Trace.isOn) {
/* 1398 */         Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDisplay(Command)", 4);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1403 */     Map<String, Object> props = aobj.getProperties();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1410 */     if (isConnectionFactoryObject(aobj) && props.get("TRANSPORT") != null) {
/* 1411 */       if (props.get("TRANSPORT").equals("BIND")) {
/*      */         
/* 1413 */         removeClientProperties(props);
/* 1414 */         removeDirectProperties(props);
/*      */       } 
/* 1416 */       if (props.get("TRANSPORT").equals("CLIENT"))
/*      */       {
/* 1418 */         removeDirectProperties(props);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1423 */     SortedMap<String, Object> sortedProps = new TreeMap<>();
/* 1424 */     for (Map.Entry<String, Object> me : props.entrySet()) {
/* 1425 */       sortedProps.put(me.getKey(), me.getValue());
/*      */     }
/*      */     
/* 1428 */     for (Map.Entry<String, Object> entry : sortedProps.entrySet()) {
/* 1429 */       String key = entry.getKey();
/* 1430 */       Object val = entry.getValue();
/* 1431 */       pw.println("");
/* 1432 */       pw.flush();
/*      */       
/* 1434 */       if (val != null) {
/* 1435 */         pw.print("    " + key + "(" + String.valueOf(val) + ")");
/*      */       } else {
/*      */         
/* 1438 */         pw.print("    " + key + "(*error*)");
/*      */       } 
/*      */       
/* 1441 */       pw.flush();
/*      */     } 
/*      */     
/* 1444 */     pw.println("");
/* 1445 */     pw.flush();
/* 1446 */     if (Trace.isOn) {
/* 1447 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDisplay(Command)", 5);
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
/*      */   private void doDisplayCtx() {
/* 1459 */     if (Trace.isOn) {
/* 1460 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDisplayCtx()");
/*      */     }
/* 1462 */     int numCtx = 0;
/* 1463 */     int numAdm = 0;
/* 1464 */     int numOth = 0;
/*      */     
/* 1466 */     pw.print("\n  " + ConfigEnvironment.getMessage("JMSADM4089", this.service.getPathString()));
/* 1467 */     pw.flush();
/*      */     
/*      */     try {
/* 1470 */       Vector<String> entries = this.service.getContents();
/*      */       
/* 1472 */       for (int i = 0; i < entries.size(); i += 2) {
/* 1473 */         String type = entries.elementAt(i);
/* 1474 */         String icon = null;
/*      */         
/* 1476 */         if (type.endsWith("Context") || type.endsWith("ContextImpl") || type
/* 1477 */           .endsWith("LDAPCtx")) {
/* 1478 */           icon = "[D]";
/* 1479 */           numCtx++;
/*      */         }
/* 1481 */         else if (type.endsWith("MQQueueConnectionFactory") || type.endsWith("MQTopicConnectionFactory") || type.endsWith("MQQueue") || type.endsWith("MQTopic") || type
/* 1482 */           .endsWith("MQXAQueueConnectionFactory") || type
/* 1483 */           .endsWith("MQXATopicConnectionFactory") || type
/* 1484 */           .endsWith("JMSWrapXAQueueConnectionFactory") || type
/* 1485 */           .endsWith("JMSWrapXATopicConnectionFactory") || type.endsWith("MQXAConnectionFactory") || type.endsWith("MQConnectionFactory")) {
/*      */ 
/*      */ 
/*      */           
/* 1489 */           icon = " a ";
/* 1490 */           numAdm++;
/*      */         } else {
/*      */           
/* 1493 */           icon = "   ";
/* 1494 */           numOth++;
/*      */         } 
/*      */         
/* 1497 */         String name = entries.elementAt(i + 1);
/* 1498 */         name = padToLength(name, 25, ' ');
/*      */         
/* 1500 */         if (i == 0) {
/* 1501 */           pw.print("\n");
/*      */         }
/*      */         
/* 1504 */         pw.print("\n  " + icon + " " + name + " " + type);
/* 1505 */         pw.flush();
/*      */       } 
/*      */       
/* 1508 */       pw.println("\n\n  " + (numCtx + numAdm + numOth) + " " + ConfigEnvironment.getMessage("JMSADM4098"));
/* 1509 */       pw.println("    " + numCtx + " " + ConfigEnvironment.getMessage("JMSADM4099"));
/* 1510 */       pw.println("    " + (numAdm + numOth) + " " + ConfigEnvironment.getMessage("JMSADM4100") + ", " + numAdm + " " + 
/* 1511 */           ConfigEnvironment.getMessage("JMSADM4101"));
/* 1512 */       pw.flush();
/*      */     }
/* 1514 */     catch (RuntimeException re) {
/* 1515 */       throw re;
/*      */     }
/* 1517 */     catch (Exception e) {
/* 1518 */       if (Trace.isOn) {
/* 1519 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDisplayCtx()", e);
/*      */       }
/*      */     } 
/* 1522 */     if (Trace.isOn) {
/* 1523 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDisplayCtx()");
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
/*      */   private void doWildcardDisplay(Command c) {
/* 1537 */     if (Trace.isOn) {
/* 1538 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "doWildcardDisplay(Command)", new Object[] { c });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1543 */     int theObjectType = c.getObjType();
/*      */ 
/*      */     
/* 1546 */     String objName = c.getObjName();
/* 1547 */     String startsWith = "";
/* 1548 */     String endsWith = "";
/*      */     
/* 1550 */     if (!objName.equals(this.wildCardCharacter)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1558 */       int wCardIndex = objName.indexOf(this.wildCardCharacter);
/*      */ 
/*      */       
/* 1561 */       startsWith = objName.substring(0, wCardIndex);
/* 1562 */       endsWith = objName.substring(wCardIndex + 1);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1569 */       Vector<String> entries = this.service.getContents();
/*      */ 
/*      */       
/* 1572 */       String namePrefix = this.service.getNamePrefix();
/*      */       
/* 1574 */       for (int i = 1; i < entries.size(); i += 2)
/*      */       {
/* 1576 */         String name = entries.elementAt(i);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1581 */         if (name.endsWith(endsWith) && (name.startsWith(startsWith) || (namePrefix != null && name.startsWith(namePrefix + startsWith))))
/*      */         {
/*      */ 
/*      */ 
/*      */           
/* 1586 */           AdminObject aobj = null;
/*      */ 
/*      */           
/*      */           try {
/* 1590 */             aobj = this.service.get(name);
/*      */             
/* 1592 */             if (aobj != null)
/*      */             {
/*      */               
/* 1595 */               if (theObjectType == aobj.getType())
/*      */               {
/*      */ 
/*      */                 
/* 1599 */                 BAO myObj = (BAO)aobj.getBAOObject();
/*      */ 
/*      */                 
/* 1602 */                 String disCommand = myObj.name() + "(" + name + ")";
/*      */ 
/*      */                 
/* 1605 */                 pw.print(disCommand);
/* 1606 */                 doDisplay(new Command(c.getVerbString() + " " + disCommand));
/* 1607 */                 pw.println("");
/*      */               
/*      */               }
/*      */ 
/*      */             
/*      */             }
/*      */           }
/* 1614 */           catch (Exception e) {
/* 1615 */             if (Trace.isOn) {
/* 1616 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doWildcardDisplay(Command)", e, 1);
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1628 */     catch (Exception e) {
/* 1629 */       if (Trace.isOn) {
/* 1630 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doWildcardDisplay(Command)", e, 2);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1636 */     if (Trace.isOn) {
/* 1637 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doWildcardDisplay(Command)");
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
/*      */   private String padToLength(String str, int lengthParam, char padChar) {
/* 1652 */     if (Trace.isOn) {
/* 1653 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "padToLength(String,int,char)", new Object[] { str, 
/* 1654 */             Integer.valueOf(lengthParam), Character.valueOf(padChar) });
/*      */     }
/* 1656 */     int length = lengthParam;
/* 1657 */     boolean rJustify = (length < 0);
/* 1658 */     if (rJustify) {
/* 1659 */       length = -1 * length;
/*      */     }
/* 1661 */     int padSize = length - str.length();
/*      */     
/* 1663 */     if (padSize < 1) {
/* 1664 */       if (Trace.isOn) {
/* 1665 */         Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "padToLength(String,int,char)", str, 1);
/*      */       }
/*      */       
/* 1668 */       return str;
/*      */     } 
/*      */     
/* 1671 */     StringBuffer buf = null;
/*      */     
/* 1673 */     if (!rJustify) {
/* 1674 */       buf = new StringBuffer(str);
/* 1675 */       for (int i = 0; i < padSize; i++) {
/* 1676 */         buf.append(padChar);
/*      */       }
/*      */     } else {
/*      */       
/* 1680 */       buf = new StringBuffer();
/* 1681 */       for (int i = 0; i < padSize; i++) {
/* 1682 */         buf.append(padChar);
/*      */       }
/* 1684 */       buf.append(str);
/*      */     } 
/*      */     
/* 1687 */     String traceRet1 = buf.toString();
/* 1688 */     if (Trace.isOn) {
/* 1689 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "padToLength(String,int,char)", traceRet1, 2);
/*      */     }
/*      */     
/* 1692 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void doDeleteCtx(String ctxname) {
/* 1702 */     if (Trace.isOn) {
/* 1703 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDeleteCtx(String)", new Object[] { ctxname });
/*      */     }
/*      */     
/*      */     try {
/* 1707 */       this.service.rmContext(ctxname);
/*      */     }
/* 1709 */     catch (Exception e) {
/* 1710 */       if (Trace.isOn) {
/* 1711 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDeleteCtx(String)", e);
/*      */       }
/* 1713 */       if (e instanceof javax.naming.ContextNotEmptyException) {
/* 1714 */         pw.println(ConfigEnvironment.getMessage("JMSADM4009"));
/* 1715 */         if (this.verbose) {
/* 1716 */           pw.println("\n" + e);
/*      */         }
/* 1718 */         pw.flush();
/*      */       }
/* 1720 */       else if (e instanceof javax.naming.InvalidNameException) {
/* 1721 */         pw.println(ConfigEnvironment.getMessage("JMSADM4115"));
/* 1722 */         if (this.verbose) {
/* 1723 */           pw.println("\n" + e);
/*      */         }
/* 1725 */         pw.flush();
/*      */       } else {
/*      */         
/* 1728 */         pw.println(ConfigEnvironment.getMessage("JMSADM4130"));
/* 1729 */         if (this.verbose) {
/* 1730 */           pw.println("\n" + e);
/*      */         }
/* 1732 */         pw.flush();
/*      */       } 
/*      */     } 
/* 1735 */     if (Trace.isOn) {
/* 1736 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDeleteCtx(String)");
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
/*      */   private void doDelete(Command c) {
/* 1749 */     if (Trace.isOn) {
/* 1750 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDelete(Command)", new Object[] { c });
/*      */     }
/* 1752 */     int objType = c.getObjType();
/* 1753 */     String alias = c.getObjName();
/* 1754 */     AdminObject aobj = null;
/*      */     
/*      */     try {
/* 1757 */       aobj = this.service.get(alias);
/*      */     }
/* 1759 */     catch (JMSException e) {
/* 1760 */       if (Trace.isOn) {
/* 1761 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDelete(Command)", (Throwable)e, 1);
/*      */       }
/*      */       
/* 1764 */       pw.println("\n" + e);
/* 1765 */       pw.flush();
/*      */     }
/* 1767 */     catch (Exception e) {
/* 1768 */       if (Trace.isOn) {
/* 1769 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDelete(Command)", e, 2);
/*      */       }
/* 1771 */       pw.println(ConfigEnvironment.getMessage("JMSADM4096"));
/*      */       
/* 1773 */       if (this.verbose) {
/* 1774 */         pw.println("\n" + e);
/*      */       }
/* 1776 */       pw.flush();
/*      */     } 
/*      */ 
/*      */     
/* 1780 */     if (aobj != null) {
/*      */ 
/*      */       
/* 1783 */       if (!objectTypeMatch(objType, aobj.getType())) {
/* 1784 */         if (Trace.isOn) {
/* 1785 */           Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDelete(Command)", 1);
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*      */       try {
/* 1792 */         this.service.unbind(alias);
/*      */       }
/* 1794 */       catch (Exception e) {
/* 1795 */         if (Trace.isOn) {
/* 1796 */           Trace.catchBlock(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDelete(Command)", e, 3);
/*      */         }
/* 1798 */         pw.println(ConfigEnvironment.getMessage("JMSADM4096"));
/* 1799 */         if (this.verbose) {
/* 1800 */           pw.println("\n" + e);
/*      */         }
/* 1802 */         pw.flush();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1807 */     if (Trace.isOn) {
/* 1808 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "doDelete(Command)", 2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void initializePW() {
/* 1817 */     if (Trace.isOn) {
/* 1818 */       Trace.entry("com.ibm.mq.jms.admin.JMSAdmin", "initializePW()");
/*      */     }
/*      */ 
/*      */     
/* 1822 */     if (pw == null) {
/*      */       
/*      */       try {
/*      */ 
/*      */         
/* 1827 */         String encProp = "console.encoding";
/* 1828 */         PropertyStore.register(encProp, "");
/* 1829 */         String encoding = PropertyStore.getStringProperty(encProp);
/*      */         
/* 1831 */         if (NLSServices.isWindowsLatinCodepage() && encoding.length() != 0 && encoding != null) {
/* 1832 */           pw = new PrintWriter(new OutputStreamWriter(System.out, encoding));
/*      */         } else {
/*      */           
/* 1835 */           pw = new PrintWriter(new OutputStreamWriter(System.out, Charset.defaultCharset()));
/*      */         }
/*      */       
/* 1838 */       } catch (UnsupportedEncodingException uee) {
/* 1839 */         if (Trace.isOn) {
/* 1840 */           Trace.catchBlock("com.ibm.mq.jms.admin.JMSAdmin", "initializePW()", uee);
/*      */         }
/* 1842 */         pw = new PrintWriter(new OutputStreamWriter(System.out));
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1848 */     if (Trace.isOn) {
/* 1849 */       Trace.exit("com.ibm.mq.jms.admin.JMSAdmin", "initializePW()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void main(String[] args) {
/* 1860 */     if (Trace.isOn) {
/* 1861 */       Trace.entry("com.ibm.mq.jms.admin.JMSAdmin", "main(String [ ])", new Object[] { args });
/*      */     }
/* 1863 */     boolean verbosity = false;
/* 1864 */     String cfgfn = "JMSAdmin.config";
/*      */     
/* 1866 */     for (int i = 0; i < args.length; i++) {
/* 1867 */       String arg = args[i].toUpperCase();
/*      */       
/* 1869 */       if (arg.equals("-V")) {
/* 1870 */         verbosity = true;
/*      */       }
/* 1872 */       else if (arg.equals("-CFG")) {
/* 1873 */         if (i + 1 < args.length) {
/* 1874 */           cfgfn = args[++i];
/*      */         }
/*      */       }
/* 1877 */       else if (arg.equals("-T")) {
/* 1878 */         Trace.setOn(true);
/*      */       }
/*      */       else {
/*      */         
/* 1882 */         if (pw == null) {
/* 1883 */           initializePW();
/*      */         }
/*      */         
/* 1886 */         pw.println(ConfigEnvironment.getMessage("JMSADM5003") + ": " + args[i]);
/* 1887 */         pw.flush();
/*      */       } 
/*      */     } 
/*      */     
/* 1891 */     new JMSAdmin(verbosity, cfgfn);
/* 1892 */     if (Trace.isOn) {
/* 1893 */       Trace.exit("com.ibm.mq.jms.admin.JMSAdmin", "main(String [ ])");
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
/*      */   private boolean objectTypeMatch(int exp, int act) {
/* 1907 */     if (Trace.isOn)
/* 1908 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "objectTypeMatch(int,int)", new Object[] {
/* 1909 */             Integer.valueOf(exp), Integer.valueOf(act)
/*      */           }); 
/* 1911 */     boolean match = (exp == act);
/*      */ 
/*      */     
/* 1914 */     if (!match) {
/* 1915 */       pw.println(ConfigEnvironment.getMessage("JMSADM4131"));
/*      */     }
/* 1917 */     if (Trace.isOn) {
/* 1918 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "objectTypeMatch(int,int)", 
/* 1919 */           Boolean.valueOf(match));
/*      */     }
/* 1921 */     return match;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void exitMessage() {
/* 1928 */     if (Trace.isOn) {
/* 1929 */       Trace.entry(this, "com.ibm.mq.jms.admin.JMSAdmin", "exitMessage()");
/*      */     }
/* 1931 */     pw.println(ConfigEnvironment.getMessage("JMSADM4001") + "\n");
/* 1932 */     pw.flush();
/* 1933 */     if (Trace.isOn) {
/* 1934 */       Trace.exit(this, "com.ibm.mq.jms.admin.JMSAdmin", "exitMessage()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class Command
/*      */   {
/* 1945 */     private int verb = -1;
/* 1946 */     private String verbString = null;
/* 1947 */     private int objType = -1;
/* 1948 */     private String objName = null;
/* 1949 */     private int targType = -1;
/* 1950 */     private int numArgs = 0;
/* 1951 */     private String[] argName = null;
/* 1952 */     private String[] argValue = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Command(String inLine) {
/* 1959 */       if (Trace.isOn) {
/* 1960 */         Trace.entry(this, "com.ibm.mq.jms.admin.Command", "<init>(String)", new Object[] { inLine });
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1965 */       int STATE_IDLE = 0;
/* 1966 */       int STATE_NAME = 1;
/* 1967 */       int STATE_VAL = 2;
/*      */ 
/*      */ 
/*      */       
/* 1971 */       this.numArgs = 0;
/* 1972 */       this.verb = -1;
/* 1973 */       this.verbString = null;
/*      */ 
/*      */ 
/*      */       
/* 1977 */       Vector<String> vTokens = new Vector<>();
/* 1978 */       StringBuffer currName = null;
/* 1979 */       StringBuffer currVal = null;
/* 1980 */       int state = 0;
/* 1981 */       boolean carryOn = true;
/* 1982 */       int errorPos = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1989 */       int parenthLevel = 0;
/*      */       
/* 1991 */       for (int i = 0; i < inLine.length() && carryOn; i++) {
/* 1992 */         char ch = inLine.charAt(i);
/*      */         
/* 1994 */         if (ch == '(') {
/* 1995 */           if (state == 2)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2002 */             currVal.append(ch);
/* 2003 */             parenthLevel++;
/*      */           
/*      */           }
/* 2006 */           else if (state == 0)
/*      */           {
/* 2008 */             errorPos = i;
/* 2009 */             carryOn = false;
/*      */           }
/* 2011 */           else if (state == 1)
/*      */           {
/* 2013 */             vTokens.addElement(currName.toString().toUpperCase());
/* 2014 */             currName = null;
/* 2015 */             currVal = new StringBuffer();
/* 2016 */             state = 2;
/*      */ 
/*      */             
/* 2019 */             parenthLevel = 0;
/*      */           }
/*      */         
/* 2022 */         } else if (ch == ')') {
/* 2023 */           if (state == 2)
/*      */           {
/* 2025 */             if (parenthLevel == 0)
/*      */             {
/* 2027 */               vTokens.addElement(currVal.toString());
/* 2028 */               currVal = null;
/* 2029 */               state = 0;
/*      */             
/*      */             }
/*      */             else
/*      */             {
/* 2034 */               currVal.append(ch);
/* 2035 */               parenthLevel--;
/*      */             }
/*      */           
/*      */           }
/* 2039 */           else if (state == 0)
/*      */           {
/* 2041 */             errorPos = i;
/* 2042 */             carryOn = false;
/*      */           }
/* 2044 */           else if (state == 1)
/*      */           {
/* 2046 */             errorPos = i;
/* 2047 */             carryOn = false;
/*      */           }
/*      */         
/* 2050 */         } else if (ch == ' ' || ch == '\t' || ch == '\n') {
/* 2051 */           if (state != 0)
/*      */           {
/*      */             
/* 2054 */             if (state == 2)
/*      */             {
/* 2056 */               currVal.append(ch);
/*      */             }
/* 2058 */             else if (state == 1)
/*      */             {
/* 2060 */               vTokens.addElement(currName.toString());
/* 2061 */               vTokens.addElement(null);
/* 2062 */               currName = null;
/* 2063 */               state = 0;
/*      */             }
/*      */           
/*      */           }
/* 2067 */         } else if (state == 0) {
/*      */           
/* 2069 */           currName = new StringBuffer();
/* 2070 */           currName.append(ch);
/* 2071 */           state = 1;
/*      */         }
/* 2073 */         else if (state == 1) {
/*      */           
/* 2075 */           currName.append(ch);
/*      */         }
/* 2077 */         else if (state == 2) {
/*      */           
/* 2079 */           currVal.append(ch);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2087 */       if (state == 2) {
/*      */         
/* 2089 */         errorPos = inLine.length();
/*      */       }
/* 2091 */       else if (state != 0) {
/*      */ 
/*      */         
/* 2094 */         if (state == 1) {
/*      */           
/* 2096 */           vTokens.addElement(currName.toString());
/* 2097 */           vTokens.addElement(null);
/* 2098 */           currName = null;
/* 2099 */           state = 0;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2105 */       int tokenCount = vTokens.size();
/* 2106 */       String[] tokens = new String[tokenCount];
/*      */       
/* 2108 */       for (int j = 0; j < tokenCount; j++) {
/* 2109 */         tokens[j] = vTokens.elementAt(j);
/*      */       }
/*      */       
/* 2112 */       vTokens = null;
/*      */ 
/*      */ 
/*      */       
/* 2116 */       if (errorPos != -1) {
/* 2117 */         JMSAdmin.pw.println(ConfigEnvironment.getMessage("JMSADM4123"));
/* 2118 */         JMSAdmin.pw.flush();
/* 2119 */         if (Trace.isOn) {
/* 2120 */           Trace.exit(this, "com.ibm.mq.jms.admin.Command", "<init>(String)", 1);
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
/*      */         return;
/*      */       } 
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
/* 2145 */       if (tokenCount == 0) {
/* 2146 */         if (Trace.isOn) {
/* 2147 */           Trace.exit(this, "com.ibm.mq.jms.admin.Command", "<init>(String)", 2);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */ 
/*      */       
/* 2156 */       String command = tokens[0].toUpperCase();
/* 2157 */       this.verbString = command;
/*      */       
/* 2159 */       if (command.equals("END")) {
/* 2160 */         this.verb = 0;
/*      */       }
/* 2162 */       else if (command.equals("DEF") || command.equals("DEFINE")) {
/* 2163 */         this.verb = 1;
/*      */       }
/* 2165 */       else if (command.equals("CHG") || command.equals("CHANGE")) {
/* 2166 */         this.verb = 2;
/*      */       }
/* 2168 */       else if (command.equals("DIS") || command.equals("DISPLAY")) {
/* 2169 */         this.verb = 3;
/*      */       }
/* 2171 */       else if (command.equals("DEL") || command.equals("DELETE")) {
/* 2172 */         this.verb = 4;
/*      */       }
/* 2174 */       else if (command.equals("CP") || command.equals("COPY")) {
/* 2175 */         this.verb = 5;
/*      */       }
/* 2177 */       else if (command.equals("MV") || command.equals("MOVE")) {
/* 2178 */         this.verb = 6;
/*      */       }
/* 2180 */       else if (command.equals("ALT") || command.equals("ALTER")) {
/* 2181 */         this.verb = 7;
/*      */       } 
/*      */       
/* 2184 */       if (this.verb != -1) {
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
/* 2211 */         if (this.verb == 0) {
/* 2212 */           if (tokenCount != 2) {
/* 2213 */             this.numArgs = -1;
/*      */           }
/*      */         }
/* 2216 */         else if (this.verb == 3) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2226 */           if (tokenCount != 4) {
/* 2227 */             this.numArgs = -1;
/*      */           }
/*      */         }
/* 2230 */         else if (this.verb == 1) {
/* 2231 */           if (tokenCount < 4 || tokens[3] == null) {
/* 2232 */             this.numArgs = -1;
/*      */           }
/*      */         }
/* 2235 */         else if (this.verb == 4) {
/* 2236 */           if (tokenCount != 4 || tokens[3] == null) {
/* 2237 */             this.numArgs = -1;
/*      */           }
/*      */         }
/* 2240 */         else if (this.verb == 2) {
/* 2241 */           if (tokenCount != 4 || tokens[3] == null) {
/* 2242 */             this.numArgs = -1;
/*      */           }
/*      */         }
/* 2245 */         else if (this.verb == 5) {
/* 2246 */           if (tokenCount != 6 || tokens[3] == null || tokens[5] == null) {
/* 2247 */             this.numArgs = -1;
/*      */           }
/*      */         }
/* 2250 */         else if (this.verb == 6) {
/* 2251 */           if (tokenCount != 6 || tokens[3] == null || tokens[5] == null) {
/* 2252 */             this.numArgs = -1;
/*      */           }
/*      */         }
/* 2255 */         else if (this.verb == 7 && (
/* 2256 */           tokenCount < 4 || tokens[3] == null)) {
/* 2257 */           this.numArgs = -1;
/*      */         } 
/*      */ 
/*      */         
/* 2261 */         if (this.numArgs == -1) {
/* 2262 */           if (Trace.isOn) {
/* 2263 */             Trace.exit(this, "com.ibm.mq.jms.admin.Command", "<init>(String)", 3);
/*      */           }
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */ 
/*      */         
/* 2271 */         if (this.verb != 0) {
/* 2272 */           String arg = tokens[2].toUpperCase();
/*      */           
/* 2274 */           if (arg.equals("CF")) {
/* 2275 */             this.objType = 8;
/*      */           }
/* 2277 */           else if (arg.equals("QCF")) {
/* 2278 */             this.objType = 0;
/*      */           }
/* 2280 */           else if (arg.equals("Q")) {
/* 2281 */             this.objType = 1;
/*      */           }
/* 2283 */           else if (arg.equals("TCF")) {
/* 2284 */             this.objType = 2;
/*      */           }
/* 2286 */           else if (arg.equals("T")) {
/* 2287 */             this.objType = 3;
/*      */           }
/* 2289 */           else if (arg.equals("CTX")) {
/* 2290 */             this.objType = -2;
/*      */           }
/* 2292 */           else if (arg.equals("XACF")) {
/* 2293 */             this.objType = 9;
/*      */           }
/* 2295 */           else if (arg.equals("XAQCF")) {
/* 2296 */             this.objType = 4;
/*      */           }
/* 2298 */           else if (arg.equals("XATCF")) {
/* 2299 */             this.objType = 5;
/*      */           }
/* 2301 */           else if (arg.equals("WSQCF")) {
/* 2302 */             this.objType = 6;
/*      */           }
/* 2304 */           else if (arg.equals("WSTCF")) {
/* 2305 */             this.objType = 7;
/*      */           } else {
/*      */             
/* 2308 */             this.numArgs = -1;
/* 2309 */             if (Trace.isOn) {
/* 2310 */               Trace.exit(this, "com.ibm.mq.jms.admin.Command", "<init>(String)", 4);
/*      */             }
/*      */             
/*      */             return;
/*      */           } 
/* 2315 */           this.objName = tokens[3];
/* 2316 */           this.numArgs = (tokenCount - 4) / 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2322 */           if (this.verb == 3) {
/* 2323 */             if ((this.objType == -2 && this.objName != null) || (this.objType != -2 && this.objName == null)) {
/* 2324 */               this.numArgs = -1;
/*      */ 
/*      */             
/*      */             }
/*      */ 
/*      */           
/*      */           }
/* 2331 */           else if (this.verb == 5 || this.verb == 6) {
/* 2332 */             String targ = tokens[4].toUpperCase();
/*      */             
/* 2334 */             if (arg.equals("CF")) {
/* 2335 */               this.targType = 8;
/*      */             }
/* 2337 */             else if (targ.equals("QCF")) {
/* 2338 */               this.targType = 0;
/*      */             }
/* 2340 */             else if (targ.equals("Q")) {
/* 2341 */               this.targType = 1;
/*      */             }
/* 2343 */             else if (targ.equals("TCF")) {
/* 2344 */               this.targType = 2;
/*      */             }
/* 2346 */             else if (targ.equals("T")) {
/* 2347 */               this.targType = 3;
/*      */             }
/* 2349 */             else if (targ.equals("CTX")) {
/* 2350 */               this.targType = -2;
/*      */             }
/* 2352 */             else if (arg.equals("XACF")) {
/* 2353 */               this.targType = 9;
/*      */             }
/* 2355 */             else if (targ.equals("XAQCF")) {
/* 2356 */               this.targType = 4;
/*      */             }
/* 2358 */             else if (targ.equals("XATCF")) {
/* 2359 */               this.targType = 5;
/*      */             }
/* 2361 */             else if (arg.equals("WSQCF")) {
/* 2362 */               this.objType = 6;
/*      */             }
/* 2364 */             else if (arg.equals("WSTCF")) {
/* 2365 */               this.objType = 7;
/*      */             } else {
/*      */               
/* 2368 */               this.numArgs = -1;
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2374 */             if (this.objType == -2 || this.targType == -2 || this.objType != this.targType) {
/* 2375 */               this.numArgs = -1;
/*      */ 
/*      */             
/*      */             }
/*      */ 
/*      */           
/*      */           }
/* 2382 */           else if (this.verb == 7 && 
/* 2383 */             this.objType == -2) {
/* 2384 */             this.numArgs = -1;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2391 */           if ((this.objName == null || this.objName.length() == 0) && 
/* 2392 */             this.verb != 0 && (this.verb != 3 || this.objType != -2)) {
/* 2393 */             this.numArgs = -1;
/*      */           }
/*      */ 
/*      */           
/* 2397 */           if (this.numArgs == -1) {
/* 2398 */             if (Trace.isOn) {
/* 2399 */               Trace.exit(this, "com.ibm.mq.jms.admin.Command", "<init>(String)", 5);
/*      */             }
/*      */ 
/*      */             
/*      */             return;
/*      */           } 
/*      */           
/* 2406 */           if (this.numArgs > 0) {
/* 2407 */             this.argName = new String[this.numArgs];
/* 2408 */             this.argValue = new String[this.numArgs];
/*      */             
/* 2410 */             for (int k = 0; k < this.numArgs; k++) {
/* 2411 */               this.argName[k] = tokens[4 + k * 2];
/* 2412 */               this.argValue[k] = tokens[5 + k * 2];
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2418 */             if ((this.verb == 5 || this.verb == 6) && (
/* 2419 */               this.argValue[0] == null || this.argValue[0].length() == 0)) {
/* 2420 */               this.numArgs = -1;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 2426 */       if (Trace.isOn) {
/* 2427 */         Trace.exit(this, "com.ibm.mq.jms.admin.Command", "<init>(String)", 6);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public int getVerb() {
/* 2433 */       if (Trace.isOn) {
/* 2434 */         Trace.data(this, "com.ibm.mq.jms.admin.Command", "getVerb()", "getter", 
/* 2435 */             Integer.valueOf(this.verb));
/*      */       }
/* 2437 */       return this.verb;
/*      */     }
/*      */     
/*      */     public String getVerbString() {
/* 2441 */       if (Trace.isOn) {
/* 2442 */         Trace.data(this, "com.ibm.mq.jms.admin.Command", "getVerbString()", "getter", this.verbString);
/*      */       }
/*      */       
/* 2445 */       return this.verbString;
/*      */     }
/*      */     
/*      */     public int getObjType() {
/* 2449 */       if (Trace.isOn) {
/* 2450 */         Trace.data(this, "com.ibm.mq.jms.admin.Command", "getObjType()", "getter", 
/* 2451 */             Integer.valueOf(this.objType));
/*      */       }
/* 2453 */       return this.objType;
/*      */     }
/*      */     
/*      */     public String getObjName() {
/* 2457 */       if (Trace.isOn) {
/* 2458 */         Trace.data(this, "com.ibm.mq.jms.admin.Command", "getObjName()", "getter", this.objName);
/*      */       }
/* 2460 */       return this.objName;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getTargType() {
/* 2466 */       if (Trace.isOn) {
/* 2467 */         Trace.data(this, "com.ibm.mq.jms.admin.Command", "getTargType()", "getter", 
/* 2468 */             Integer.valueOf(this.targType));
/*      */       }
/* 2470 */       return this.targType;
/*      */     }
/*      */     
/*      */     public String getArgName(int i) {
/* 2474 */       if (Trace.isOn)
/* 2475 */         Trace.entry(this, "com.ibm.mq.jms.admin.Command", "getArgName(int)", new Object[] {
/* 2476 */               Integer.valueOf(i)
/*      */             }); 
/* 2478 */       String traceRet1 = this.argName[i];
/* 2479 */       if (Trace.isOn) {
/* 2480 */         Trace.exit(this, "com.ibm.mq.jms.admin.Command", "getArgName(int)", traceRet1);
/*      */       }
/* 2482 */       return traceRet1;
/*      */     }
/*      */     
/*      */     public String getArgValue(int i) {
/* 2486 */       if (Trace.isOn)
/* 2487 */         Trace.entry(this, "com.ibm.mq.jms.admin.Command", "getArgValue(int)", new Object[] {
/* 2488 */               Integer.valueOf(i)
/*      */             }); 
/* 2490 */       String traceRet1 = this.argValue[i];
/* 2491 */       if (Trace.isOn) {
/* 2492 */         Trace.exit(this, "com.ibm.mq.jms.admin.Command", "getArgValue(int)", traceRet1);
/*      */       }
/* 2494 */       return traceRet1;
/*      */     }
/*      */     
/*      */     public int getArgCount() {
/* 2498 */       if (Trace.isOn) {
/* 2499 */         Trace.data(this, "com.ibm.mq.jms.admin.Command", "getArgCount()", "getter", 
/* 2500 */             Integer.valueOf(this.numArgs));
/*      */       }
/* 2502 */       return this.numArgs;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\JMSAdmin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */