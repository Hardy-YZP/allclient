/*      */ package com.ibm.mq.jms.admin;
/*      */ 
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.text.MessageFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Properties;
/*      */ import java.util.Vector;
/*      */ import javax.jms.JMSException;
/*      */ import javax.jms.JMSSecurityException;
/*      */ import javax.naming.Context;
/*      */ import javax.naming.InitialContext;
/*      */ import javax.naming.Name;
/*      */ import javax.naming.NameClassPair;
/*      */ import javax.naming.NameNotFoundException;
/*      */ import javax.naming.NamingEnumeration;
/*      */ import javax.naming.NamingException;
/*      */ import javax.naming.Reference;
/*      */ import javax.naming.directory.BasicAttributes;
/*      */ import javax.naming.directory.DirContext;
/*      */ import javax.naming.directory.InitialDirContext;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class AdminService
/*      */ {
/*      */   private static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/AdminService.java";
/*      */   private static final String CLASSNAME = "com.ibm.mq.jms.admin.AdminService";
/*      */   private static final int SYSTEM_NONE = -1;
/*      */   private static final int SYSTEM_FS = 0;
/*      */   private static final int SYSTEM_LDAP = 1;
/*      */   private static final int SYSTEM_WEBSPHERE = 2;
/*      */   private static final String DEFAULT_PREFIX = "cn=";
/*      */   static final int AUTH_NONE = 0;
/*      */   static final int AUTH_SIMPLE = 1;
/*      */   static final int AUTH_CRAM_MD5 = 2;
/*      */   static final String CTX_INIT = "=INIT";
/*      */   static final String CTX_UP = "=UP";
/*      */   
/*      */   static {
/*   84 */     if (Trace.isOn) {
/*   85 */       Trace.data("com.ibm.mq.jms.admin.AdminService", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jms.admin/src/com/ibm/mq/jms/admin/AdminService.java");
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean active = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  130 */   protected Context ictx = null;
/*  131 */   protected Context cctx = null;
/*  132 */   private String cctxString = null;
/*  133 */   private ArrayList<Context> parentChain = null;
/*  134 */   private ArrayList<String> parentChainString = null;
/*      */   
/*  136 */   private String initialContextFactory = null;
/*  137 */   private String providerURL = null;
/*  138 */   private int authentication = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   protected Properties properties;
/*      */ 
/*      */   
/*      */   protected boolean verbosity = false;
/*      */ 
/*      */   
/*      */   protected static final String USE_INITIAL_DIR_CONTEXT = "USE_INITIAL_DIR_CONTEXT";
/*      */ 
/*      */   
/*      */   protected static final String NAME_PREFIX = "NAME_PREFIX";
/*      */ 
/*      */   
/*      */   protected static final String NAME_READABILITY = "NAME_READABILITY_MARKER";
/*      */ 
/*      */   
/*      */   protected static final String ICF_CONFIG_TRUE = "TRUE";
/*      */ 
/*      */   
/*      */   protected static final String ICF_CONFIG_FALSE = "FALSE";
/*      */ 
/*      */ 
/*      */   
/*      */   AdminService(String icf, String purl, String auth, Properties properties, boolean verbosity) throws JMSSecurityException {
/*  165 */     if (Trace.isOn) {
/*  166 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminService", "<init>(String,String,String,Properties,boolean)", new Object[] { icf, purl, auth, properties, 
/*      */             
/*  168 */             Boolean.valueOf(verbosity) });
/*      */     }
/*  170 */     this.properties = properties;
/*  171 */     this.initialContextFactory = icf;
/*  172 */     this.providerURL = purl;
/*  173 */     this.verbosity = verbosity;
/*      */     
/*  175 */     if (auth.toUpperCase().equals("NONE")) {
/*  176 */       this.authentication = 0;
/*      */     }
/*  178 */     else if (auth.toUpperCase().equals("SIMPLE")) {
/*  179 */       this.authentication = 1;
/*      */     }
/*  181 */     else if (auth.toUpperCase().equals("CRAM_MD5") || auth.toUpperCase().equals("CRAM-MD5")) {
/*  182 */       this.authentication = 2;
/*      */     } else {
/*      */       
/*  185 */       this.authentication = 0;
/*      */       
/*  187 */       JMSSecurityException traceRet1 = new JMSSecurityException(ConfigEnvironment.getMessage("JMSADM4139"));
/*  188 */       if (Trace.isOn) {
/*  189 */         Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "<init>(String,String,String,Properties,boolean)", (Throwable)traceRet1);
/*      */       }
/*      */       
/*  192 */       throw traceRet1;
/*      */     } 
/*  194 */     if (Trace.isOn) {
/*  195 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminService", "<init>(String,String,String,Properties,boolean)");
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
/*      */   void initJNDI(String id, String pw) throws NamingException, JMSException, ClassNotFoundException {
/*  210 */     if (Trace.isOn) {
/*  211 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminService", "initJNDI(String,String)", new Object[] { id, pw });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  216 */     if (!this.active) {
/*      */       
/*  218 */       Properties env = (Properties)this.properties.clone();
/*  219 */       env.setProperty("java.naming.factory.initial", this.initialContextFactory);
/*  220 */       env.setProperty("java.naming.provider.url", this.providerURL);
/*  221 */       env.setProperty("java.naming.security.authentication", (this.authentication == 0) ? "none" : ((this.authentication == 1) ? "simple" : ((this.authentication == 2) ? "CRAM_MD5" : "none")));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  229 */       env.setProperty("java.naming.referral", "throw");
/*  230 */       if (needsAuthorization()) {
/*  231 */         env.setProperty("java.naming.security.principal", id);
/*  232 */         env.setProperty("java.naming.security.credentials", pw);
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  237 */         setICFProperties();
/*      */ 
/*      */         
/*  240 */         if (this.properties.getProperty("USE_INITIAL_DIR_CONTEXT").toUpperCase().equals("TRUE")) {
/*  241 */           this.ictx = new InitialDirContext(env);
/*      */         } else {
/*      */           
/*  244 */           this.ictx = new InitialContext(env);
/*      */         }
/*      */       
/*  247 */       } catch (NamingException e) {
/*  248 */         if (Trace.isOn) {
/*  249 */           Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "initJNDI(String,String)", e);
/*      */         }
/*      */         
/*  252 */         Exception e1 = (Exception)e.getRootCause();
/*  253 */         if (e1 instanceof ClassNotFoundException) {
/*  254 */           ClassNotFoundException e2 = (ClassNotFoundException)e1;
/*  255 */           this.cctxString = ConfigEnvironment.getMessage("JMSADM4142");
/*  256 */           System.out.println(this.cctxString + "\n");
/*  257 */           if (Trace.isOn) {
/*  258 */             Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "initJNDI(String,String)", e2, 1);
/*      */           }
/*      */           
/*  261 */           throw e2;
/*      */         } 
/*      */         
/*  264 */         this.cctxString = ConfigEnvironment.getMessage("JMSADM4110");
/*  265 */         if (e instanceof NameNotFoundException) {
/*  266 */           Name remainingName = ((NameNotFoundException)e).getRemainingName();
/*  267 */           String notResolvedMessage = ConfigEnvironment.getMessage("JMSADM4117");
/*  268 */           this.cctxString += "\n" + MessageFormat.format(notResolvedMessage, new Object[] { remainingName.toString() });
/*      */         } 
/*  270 */         if (!this.verbosity) {
/*  271 */           this.cctxString += "\n" + ConfigEnvironment.getMessage("JMSADM4116");
/*      */         }
/*  273 */         System.out.println(this.cctxString + "\n");
/*  274 */         if (Trace.isOn) {
/*  275 */           Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "initJNDI(String,String)", e, 2);
/*      */         }
/*      */         
/*  278 */         throw e;
/*      */       } 
/*  280 */       this.parentChain = new ArrayList<>();
/*  281 */       this.parentChain.add(this.cctx);
/*      */       
/*  283 */       this.parentChainString = new ArrayList<>();
/*  284 */       this.parentChainString.add(ConfigEnvironment.getMessage("JMSADM4007"));
/*      */       
/*  286 */       this.cctx = this.ictx;
/*  287 */       this.cctxString = ConfigEnvironment.getMessage("JMSADM4007");
/*  288 */       this.active = true;
/*      */     } 
/*  290 */     if (Trace.isOn) {
/*  291 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminService", "initJNDI(String,String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean needsAuthorization() {
/*  302 */     if (Trace.isOn) {
/*  303 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminService", "needsAuthorization()");
/*      */     }
/*  305 */     boolean traceRet1 = (this.authentication != 0);
/*  306 */     if (Trace.isOn) {
/*  307 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminService", "needsAuthorization()", 
/*  308 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  310 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void stopJNDI() throws NamingException {
/*  319 */     if (Trace.isOn) {
/*  320 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminService", "stopJNDI()");
/*      */     }
/*  322 */     if (this.active) {
/*      */       try {
/*  324 */         this.ictx.close();
/*  325 */         this.active = false;
/*  326 */         this.parentChain = null;
/*  327 */         this.ictx = null;
/*  328 */         this.cctx = null;
/*      */       }
/*  330 */       catch (NamingException e) {
/*  331 */         if (Trace.isOn) {
/*  332 */           Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "stopJNDI()", e);
/*      */         }
/*  334 */         if (Trace.isOn) {
/*  335 */           Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "stopJNDI()", e);
/*      */         }
/*  337 */         throw e;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  344 */     if (Trace.isOn) {
/*  345 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminService", "stopJNDI()");
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
/*      */   AdminObject get(String alias) throws NamingException, JMSException {
/*      */     int objType;
/*  360 */     if (Trace.isOn) {
/*  361 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminService", "get(String)", new Object[] { alias });
/*      */     }
/*  363 */     if (!this.active) {
/*      */       
/*  365 */       JMSException e = new JMSException(ConfigEnvironment.getMessage("JMSADM4104"));
/*  366 */       if (Trace.isOn) {
/*  367 */         Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "get(String)", (Throwable)e, 1);
/*      */       }
/*  369 */       throw e;
/*      */     } 
/*      */     
/*  372 */     Object retObj = null;
/*      */ 
/*      */     
/*      */     try {
/*  376 */       String fullAlias = alias;
/*      */ 
/*      */       
/*  379 */       if (this.properties.get("NAME_PREFIX") != null && 
/*  380 */         !fullAlias.startsWith((String)this.properties.get("NAME_PREFIX"))) {
/*  381 */         fullAlias = this.properties.get("NAME_PREFIX") + fullAlias;
/*      */       }
/*      */       
/*  384 */       retObj = this.cctx.lookup(fullAlias);
/*      */     }
/*  386 */     catch (NamingException e1) {
/*  387 */       if (Trace.isOn) {
/*  388 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "get(String)", e1);
/*      */       }
/*      */       
/*  391 */       if (Trace.isOn) {
/*  392 */         Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "get(String)", e1, 2);
/*      */       }
/*  394 */       throw e1;
/*      */     } 
/*      */ 
/*      */     
/*  398 */     String className = retObj.getClass().getName();
/*      */     
/*  400 */     if (className.equals("com.ibm.ejs.jms.mq.JMSWrapXAQueueConnectionFactory")) {
/*  401 */       objType = 6;
/*      */     }
/*  403 */     else if (className.equals("com.ibm.ejs.jms.mq.JMSWrapXATopicConnectionFactory")) {
/*  404 */       objType = 7;
/*      */     }
/*  406 */     else if (retObj instanceof com.ibm.mq.jms.MQXAQueueConnectionFactory) {
/*  407 */       objType = 4;
/*      */     }
/*  409 */     else if (retObj instanceof com.ibm.mq.jms.MQXATopicConnectionFactory) {
/*  410 */       objType = 5;
/*      */     }
/*  412 */     else if (retObj instanceof com.ibm.mq.jms.MQXAConnectionFactory) {
/*  413 */       objType = 9;
/*      */     }
/*  415 */     else if (retObj instanceof com.ibm.mq.jms.MQQueueConnectionFactory) {
/*  416 */       objType = 0;
/*      */     }
/*  418 */     else if (retObj instanceof com.ibm.mq.jms.MQQueue) {
/*  419 */       objType = 1;
/*      */     }
/*  421 */     else if (retObj instanceof com.ibm.mq.jms.MQTopicConnectionFactory) {
/*  422 */       objType = 2;
/*      */     }
/*  424 */     else if (retObj instanceof com.ibm.mq.jms.MQTopic) {
/*  425 */       objType = 3;
/*      */     }
/*  427 */     else if (retObj instanceof com.ibm.mq.jms.MQConnectionFactory) {
/*  428 */       objType = 8;
/*      */     } else {
/*      */       JMSException e;
/*      */ 
/*      */       
/*  433 */       String refClass = null;
/*  434 */       if (retObj instanceof Reference) {
/*  435 */         refClass = ((Reference)retObj).getClassName();
/*      */       }
/*      */ 
/*      */       
/*  439 */       if (refClass != null && refClass.startsWith("com.ibm.ejs")) {
/*  440 */         e = new JMSException(ConfigEnvironment.getMessage("JMSADM4137"));
/*      */       }
/*      */       else {
/*      */         
/*  444 */         e = new JMSException(ConfigEnvironment.getMessage("JMSADM4106"));
/*      */       } 
/*      */       
/*  447 */       if (Trace.isOn) {
/*  448 */         Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "get(String)", (Throwable)e, 3);
/*      */       }
/*  450 */       throw e;
/*      */     } 
/*      */ 
/*      */     
/*  454 */     AdminObject traceRet1 = new AdminObject(objType, retObj, alias);
/*  455 */     if (Trace.isOn) {
/*  456 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminService", "get(String)", traceRet1);
/*      */     }
/*  458 */     return traceRet1;
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
/*      */   Vector getContents() throws NamingException, JMSException {
/*  477 */     if (!this.active) {
/*      */       
/*  479 */       JMSException e = new JMSException(ConfigEnvironment.getMessage("JMSADM4104"));
/*  480 */       if (Trace.isOn) {
/*  481 */         Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "getContents()", (Throwable)e, 1);
/*      */       }
/*  483 */       throw e;
/*      */     } 
/*      */     
/*  486 */     Vector<String> objs = new Vector<>();
/*  487 */     NamingEnumeration<NameClassPair> ne = null;
/*      */     
/*      */     try {
/*  490 */       ne = this.cctx.list("");
/*      */ 
/*      */       
/*  493 */       while (ne.hasMore()) {
/*  494 */         NameClassPair nc = ne.next();
/*      */         
/*  496 */         String objType = nc.getClassName();
/*  497 */         String name = nc.getName();
/*      */ 
/*      */ 
/*      */         
/*  501 */         if (this.properties.containsKey("NAME_READABILITY_MARKER")) {
/*  502 */           int idx = name.indexOf(this.properties.getProperty("NAME_READABILITY_MARKER"));
/*  503 */           if (idx != -1) {
/*  504 */             name = name.substring(0, idx);
/*      */           }
/*      */         } 
/*      */         
/*  508 */         objs.addElement(objType);
/*  509 */         objs.addElement(name);
/*      */       }
/*      */     
/*  512 */     } catch (NamingException e) {
/*  513 */       if (Trace.isOn) {
/*  514 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "getContents()", e);
/*      */       }
/*  516 */       if (Trace.isOn) {
/*  517 */         Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "getContents()", e, 2);
/*      */       }
/*  519 */       throw e;
/*      */     } 
/*      */     
/*  522 */     if (Trace.isOn) {
/*  523 */       Trace.data(this, "com.ibm.mq.jms.admin.AdminService", "getContents()", "getter", objs);
/*      */     }
/*  525 */     return objs;
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
/*      */   void bind(String alias, AdminObject obj) throws NamingException, JMSException {
/*  537 */     if (Trace.isOn) {
/*  538 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminService", "bind(String,AdminObject)", new Object[] { alias, obj });
/*      */     }
/*      */     
/*  541 */     if (!this.active) {
/*      */       
/*  543 */       JMSException e = new JMSException(ConfigEnvironment.getMessage("JMSADM4104"));
/*  544 */       if (Trace.isOn) {
/*  545 */         Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "bind(String,AdminObject)", (Throwable)e, 1);
/*      */       }
/*      */       
/*  548 */       throw e;
/*      */     } 
/*      */ 
/*      */     
/*  552 */     if (((String)this.properties.get("USE_INITIAL_DIR_CONTEXT")).toUpperCase().equals("TRUE")) {
/*  553 */       BasicAttributes ba = new BasicAttributes();
/*  554 */       ba.put("objectclass", "javaContainer");
/*  555 */       ba.put("cn", alias);
/*      */       
/*      */       try {
/*  558 */         String fullAlias = alias;
/*      */         
/*  560 */         if (this.properties.get("NAME_PREFIX") != null && 
/*  561 */           !fullAlias.startsWith((String)this.properties.get("NAME_PREFIX"))) {
/*  562 */           fullAlias = this.properties.get("NAME_PREFIX") + fullAlias;
/*      */         }
/*      */         
/*  565 */         ((DirContext)this.cctx).bind(fullAlias, obj.getObject(), ba);
/*      */       }
/*  567 */       catch (NamingException e1) {
/*  568 */         if (Trace.isOn) {
/*  569 */           Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "bind(String,AdminObject)", e1, 1);
/*      */         }
/*      */ 
/*      */         
/*  573 */         if (Trace.isOn) {
/*  574 */           Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "bind(String,AdminObject)", e1, 2);
/*      */         }
/*      */         
/*  577 */         throw e1;
/*      */       } 
/*      */     } else {
/*      */ 
/*      */       
/*      */       try {
/*  583 */         this.cctx.bind(alias, obj.getObject());
/*      */       }
/*  585 */       catch (NamingException e) {
/*  586 */         if (Trace.isOn) {
/*  587 */           Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "bind(String,AdminObject)", e, 2);
/*      */         }
/*      */         
/*  590 */         if (Trace.isOn) {
/*  591 */           Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "bind(String,AdminObject)", e, 3);
/*      */         }
/*      */         
/*  594 */         throw e;
/*      */       } 
/*      */     } 
/*      */     
/*  598 */     if (Trace.isOn) {
/*  599 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminService", "bind(String,AdminObject)");
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
/*      */   void rebind(String alias, AdminObject obj) throws NamingException, JMSException {
/*  613 */     if (Trace.isOn) {
/*  614 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminService", "rebind(String,AdminObject)", new Object[] { alias, obj });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  624 */     if (((String)this.properties.get("USE_INITIAL_DIR_CONTEXT")).toUpperCase().equals("TRUE")) {
/*  625 */       BasicAttributes ba = new BasicAttributes();
/*  626 */       ba.put("objectclass", "javaContainer");
/*  627 */       ba.put("cn", alias);
/*      */       
/*      */       try {
/*  630 */         String fullAlias = alias;
/*      */         
/*  632 */         if (this.properties.get("NAME_PREFIX") != null && 
/*  633 */           !fullAlias.startsWith((String)this.properties.get("NAME_PREFIX"))) {
/*  634 */           fullAlias = this.properties.get("NAME_PREFIX") + fullAlias;
/*      */         }
/*      */         
/*  637 */         ((DirContext)this.cctx).rebind(fullAlias, obj.getObject(), ba);
/*      */       
/*      */       }
/*  640 */       catch (NamingException e1) {
/*  641 */         if (Trace.isOn) {
/*  642 */           Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "rebind(String,AdminObject)", e1, 1);
/*      */         }
/*      */         
/*  645 */         if (Trace.isOn) {
/*  646 */           Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "rebind(String,AdminObject)", e1, 1);
/*      */         }
/*      */         
/*  649 */         throw e1;
/*      */       } 
/*      */     } else {
/*      */ 
/*      */       
/*      */       try {
/*  655 */         this.cctx.rebind(alias, obj.getObject());
/*      */       }
/*  657 */       catch (NamingException e) {
/*  658 */         if (Trace.isOn) {
/*  659 */           Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "rebind(String,AdminObject)", e, 2);
/*      */         }
/*      */         
/*  662 */         if (Trace.isOn) {
/*  663 */           Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "rebind(String,AdminObject)", e, 2);
/*      */         }
/*      */         
/*  666 */         throw e;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  671 */     if (Trace.isOn) {
/*  672 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminService", "rebind(String,AdminObject)");
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
/*      */   void unbind(String alias) throws NamingException, JMSException {
/*  685 */     if (Trace.isOn) {
/*  686 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminService", "unbind(String)", new Object[] { alias });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  697 */       String fullAlias = alias;
/*      */       
/*  699 */       if (this.properties.get("NAME_PREFIX") != null && 
/*  700 */         !fullAlias.startsWith((String)this.properties.get("NAME_PREFIX"))) {
/*  701 */         fullAlias = this.properties.get("NAME_PREFIX") + fullAlias;
/*      */       }
/*      */       
/*  704 */       this.cctx.unbind(fullAlias);
/*      */     }
/*  706 */     catch (NamingException e1) {
/*  707 */       if (Trace.isOn) {
/*  708 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "unbind(String)", e1);
/*      */       }
/*  710 */       if (Trace.isOn) {
/*  711 */         Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "unbind(String)", e1);
/*      */       }
/*  713 */       throw e1;
/*      */     } 
/*  715 */     if (Trace.isOn) {
/*  716 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminService", "unbind(String)");
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
/*      */   void mkContext(String name) throws NamingException, JMSException {
/*  729 */     if (Trace.isOn) {
/*  730 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminService", "mkContext(String)", new Object[] { name });
/*      */     }
/*      */     
/*  733 */     if (!this.active) {
/*      */       
/*  735 */       JMSException e = new JMSException(ConfigEnvironment.getMessage("JMSADM4104"));
/*  736 */       if (Trace.isOn) {
/*  737 */         Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "mkContext(String)", (Throwable)e, 1);
/*      */       }
/*      */       
/*  740 */       throw e;
/*      */     } 
/*      */     
/*      */     try {
/*  744 */       if (((String)this.properties.get("USE_INITIAL_DIR_CONTEXT")).toUpperCase().equals("TRUE")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  770 */         String fullName = name;
/*  771 */         if (fullName.indexOf("=") == -1) {
/*  772 */           if (this.properties.get("NAME_PREFIX") != null) {
/*  773 */             fullName = this.properties.get("NAME_PREFIX") + name;
/*      */           } else {
/*      */             
/*  776 */             fullName = "cn=" + name;
/*      */           } 
/*      */         }
/*      */         
/*      */         try {
/*  781 */           ((DirContext)this.cctx).createSubcontext(fullName, new BasicAttributes("objectclass", "top"));
/*      */         }
/*  783 */         catch (NamingException ne) {
/*  784 */           if (Trace.isOn) {
/*  785 */             Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "mkContext(String)", ne, 1);
/*      */           }
/*      */ 
/*      */           
/*  789 */           this.cctx.createSubcontext(fullName);
/*      */         }
/*      */       
/*  792 */       } else if (((String)this.properties.get("USE_INITIAL_DIR_CONTEXT")).toUpperCase().equals("FALSE")) {
/*      */         
/*  794 */         this.cctx.createSubcontext(name);
/*      */       }
/*      */     
/*  797 */     } catch (NamingException e) {
/*  798 */       if (Trace.isOn) {
/*  799 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "mkContext(String)", e, 2);
/*      */       }
/*  801 */       if (Trace.isOn) {
/*  802 */         Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "mkContext(String)", e, 2);
/*      */       }
/*  804 */       throw e;
/*      */     } 
/*  806 */     if (Trace.isOn) {
/*  807 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminService", "mkContext(String)");
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
/*      */   void rmContext(String name) throws NamingException, JMSException {
/*  820 */     if (Trace.isOn) {
/*  821 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminService", "rmContext(String)", new Object[] { name });
/*      */     }
/*      */     
/*  824 */     if (!this.active) {
/*      */       
/*  826 */       JMSException e = new JMSException(ConfigEnvironment.getMessage("JMSADM4104"));
/*  827 */       if (Trace.isOn) {
/*  828 */         Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "rmContext(String)", (Throwable)e, 1);
/*      */       }
/*      */       
/*  831 */       throw e;
/*      */     } 
/*      */     
/*  834 */     String actualName = name;
/*      */ 
/*      */     
/*  837 */     Context test = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  845 */       this.cctx.lookup(name);
/*      */     }
/*  847 */     catch (ClassCastException e1) {
/*  848 */       if (Trace.isOn) {
/*  849 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "rmContext(String)", e1, 1);
/*      */       }
/*      */       
/*  852 */       if (Trace.isOn) {
/*  853 */         Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "rmContext(String)", e1, 2);
/*      */       }
/*  855 */       throw e1;
/*      */     }
/*  857 */     catch (NamingException e2) {
/*  858 */       if (Trace.isOn) {
/*  859 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "rmContext(String)", e2, 2);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  865 */       if (((String)this.properties.get("USE_INITIAL_DIR_CONTEXT")).toUpperCase().equals("TRUE")) {
/*      */         try {
/*  867 */           if (this.properties.get("NAME_PREFIX") != null) {
/*  868 */             actualName = this.properties.get("NAME_PREFIX") + name;
/*      */           } else {
/*      */             
/*  871 */             actualName = "cn=" + name;
/*      */           } 
/*      */           
/*  874 */           test = (Context)this.cctx.lookup(actualName);
/*      */         }
/*  876 */         catch (NamingException e3) {
/*  877 */           if (Trace.isOn) {
/*  878 */             Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "rmContext(String)", e3, 3);
/*      */           }
/*      */           
/*  881 */           if (Trace.isOn) {
/*  882 */             Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "rmContext(String)", e3, 3);
/*      */           }
/*      */           
/*  885 */           throw e3;
/*      */         } 
/*      */       } else {
/*      */         
/*  889 */         if (Trace.isOn) {
/*  890 */           Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "rmContext(String)", e2, 4);
/*      */         }
/*  892 */         throw e2;
/*      */       } 
/*      */     } 
/*      */     
/*      */     try {
/*  897 */       this.cctx.destroySubcontext(actualName);
/*      */     }
/*  899 */     catch (NamingException e) {
/*  900 */       if (Trace.isOn) {
/*  901 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "rmContext(String)", e, 4);
/*      */       }
/*  903 */       if (Trace.isOn) {
/*  904 */         Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "rmContext(String)", e, 5);
/*      */       }
/*  906 */       throw e;
/*      */     } 
/*  908 */     if (Trace.isOn) {
/*  909 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminService", "rmContext(String)");
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
/*      */   void chContext(String ctxname) throws NamingException, JMSException {
/*  924 */     if (Trace.isOn) {
/*  925 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminService", "chContext(String)", new Object[] { ctxname });
/*      */     }
/*      */     
/*  928 */     if (!this.active) {
/*      */       
/*  930 */       JMSException e = new JMSException(ConfigEnvironment.getMessage("JMSADM4104"));
/*  931 */       if (Trace.isOn) {
/*  932 */         Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "chContext(String)", (Throwable)e, 1);
/*      */       }
/*      */       
/*  935 */       throw e;
/*      */     } 
/*      */     
/*  938 */     if (ctxname.toUpperCase().equals("=INIT")) {
/*  939 */       this.cctx = this.ictx;
/*  940 */       this.cctxString = ConfigEnvironment.getMessage("JMSADM4007");
/*      */       
/*  942 */       this.parentChain = new ArrayList<>();
/*  943 */       this.parentChain.add(this.cctx);
/*      */       
/*  945 */       this.parentChainString = new ArrayList<>();
/*  946 */       this.parentChainString.add(ConfigEnvironment.getMessage("JMSADM4007"));
/*      */     }
/*  948 */     else if (ctxname.toUpperCase().equals("=UP")) {
/*  949 */       if (!this.cctx.equals(this.ictx)) {
/*  950 */         this.cctx = this.parentChain.get(0);
/*  951 */         this.cctxString = this.parentChainString.get(0);
/*      */         
/*  953 */         this.parentChain.remove(0);
/*  954 */         this.parentChainString.remove(0);
/*      */       } 
/*      */     } else {
/*      */       
/*      */       try {
/*  959 */         if (ctxname.length() == 0 || ctxname
/*  960 */           .equals(".") || ctxname
/*  961 */           .equals("..")) {
/*      */           
/*  963 */           JMSException e = new JMSException("null context");
/*  964 */           if (Trace.isOn) {
/*  965 */             Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "chContext(String)", (Throwable)e, 2);
/*      */           }
/*      */           
/*  968 */           throw e;
/*      */         } 
/*      */         
/*  971 */         Context savectx = this.cctx;
/*  972 */         this.cctx = (Context)this.cctx.lookup(ctxname);
/*      */         
/*  974 */         this.parentChain.add(0, savectx);
/*  975 */         this.parentChainString.add(0, ctxname);
/*      */       }
/*  977 */       catch (NamingException e1) {
/*  978 */         if (Trace.isOn) {
/*  979 */           Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "chContext(String)", e1, 1);
/*      */         }
/*      */         
/*  982 */         if (((String)this.properties.get("USE_INITIAL_DIR_CONTEXT")).toUpperCase()
/*  983 */           .equals("TRUE")) {
/*      */ 
/*      */           
/*      */           try {
/*  987 */             String prefix = (String)this.properties.get("NAME_PREFIX");
/*  988 */             if (prefix == null) {
/*  989 */               prefix = "cn=";
/*      */             }
/*      */             
/*  992 */             Context savectx = this.cctx;
/*  993 */             this.cctx = (Context)this.cctx.lookup(prefix + ctxname);
/*      */             
/*  995 */             this.parentChain.add(0, savectx);
/*  996 */             this.parentChainString.add(0, prefix + ctxname);
/*      */           }
/*  998 */           catch (NamingException e2) {
/*  999 */             if (Trace.isOn) {
/* 1000 */               Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "chContext(String)", e2, 2);
/*      */             }
/*      */             
/* 1003 */             if (Trace.isOn) {
/* 1004 */               Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "chContext(String)", e2, 3);
/*      */             }
/*      */             
/* 1007 */             throw e2;
/*      */           } 
/*      */         } else {
/*      */           
/* 1011 */           if (Trace.isOn) {
/* 1012 */             Trace.throwing(this, "com.ibm.mq.jms.admin.AdminService", "chContext(String)", e1, 4);
/*      */           }
/*      */           
/* 1015 */           throw e1;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1019 */     if (Trace.isOn) {
/* 1020 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminService", "chContext(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isActive() {
/* 1031 */     if (Trace.isOn) {
/* 1032 */       Trace.data(this, "com.ibm.mq.jms.admin.AdminService", "isActive()", "getter", 
/* 1033 */           Boolean.valueOf(this.active));
/*      */     }
/* 1035 */     return this.active;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String getCctxString() {
/* 1044 */     if (Trace.isOn) {
/* 1045 */       Trace.data(this, "com.ibm.mq.jms.admin.AdminService", "getCctxString()", "getter", this.cctxString);
/*      */     }
/*      */     
/* 1048 */     return this.cctxString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String getPathString() {
/* 1057 */     StringBuffer path = new StringBuffer();
/*      */     
/* 1059 */     for (int i = this.parentChainString.size() - 1; i >= 0; i--) {
/* 1060 */       if (i != this.parentChainString.size() - 1) {
/* 1061 */         path.append("/");
/*      */       }
/* 1063 */       path.append(this.parentChainString.get(i));
/*      */     } 
/* 1065 */     String traceRet1 = new String(path);
/* 1066 */     if (Trace.isOn) {
/* 1067 */       Trace.data(this, "com.ibm.mq.jms.admin.AdminService", "getPathString()", "getter", traceRet1);
/*      */     }
/*      */     
/* 1070 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Context getIctx() {
/* 1079 */     if (Trace.isOn) {
/* 1080 */       Trace.data(this, "com.ibm.mq.jms.admin.AdminService", "getIctx()", "getter", this.ictx);
/*      */     }
/* 1082 */     return this.ictx;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String getICF() {
/* 1091 */     if (Trace.isOn) {
/* 1092 */       Trace.data(this, "com.ibm.mq.jms.admin.AdminService", "getICF()", "getter", this.initialContextFactory);
/*      */     }
/*      */     
/* 1095 */     return this.initialContextFactory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String getPURL() {
/* 1104 */     if (Trace.isOn) {
/* 1105 */       Trace.data(this, "com.ibm.mq.jms.admin.AdminService", "getPURL()", "getter", this.providerURL);
/*      */     }
/* 1107 */     return this.providerURL;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getAuth() {
/* 1117 */     if (Trace.isOn) {
/* 1118 */       Trace.data(this, "com.ibm.mq.jms.admin.AdminService", "getAuth()", "getter", 
/* 1119 */           Integer.valueOf(this.authentication));
/*      */     }
/* 1121 */     return this.authentication;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setICFProperties() {
/* 1131 */     if (Trace.isOn) {
/* 1132 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminService", "setICFProperties()");
/*      */     }
/*      */     
/* 1135 */     String[] defaults_LDAP = { "TRUE", "cn=", null };
/* 1136 */     String[] defaults_WEBSPHERE = { "FALSE", null, ".." };
/* 1137 */     String[] defaults_FS = { "FALSE", null, null };
/*      */     
/* 1139 */     String[] current_defaults = null;
/*      */ 
/*      */     
/* 1142 */     if (this.initialContextFactory.equals("com.sun.jndi.ldap.LdapCtxFactory") || this.initialContextFactory
/* 1143 */       .equals("com.ibm.jndi.LDAPCtxFactory")) {
/*      */       
/* 1145 */       current_defaults = defaults_LDAP;
/*      */     
/*      */     }
/* 1148 */     else if (this.initialContextFactory.equals("com.sun.jndi.fscontext.RefFSContextFactory")) {
/*      */ 
/*      */       
/* 1151 */       current_defaults = defaults_FS;
/*      */     
/*      */     }
/* 1154 */     else if (this.initialContextFactory.equals("com.ibm.ejs.ns.jndi.CNInitialContextFactory") || this.initialContextFactory
/* 1155 */       .equals("com.ibm.websphere.naming.WsnInitialContextFactory") || this.initialContextFactory
/* 1156 */       .equals("com.ibm.ws.naming.ldap.WsnLdapInitialContextFactory")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1162 */       current_defaults = defaults_WEBSPHERE;
/*      */     
/*      */     }
/* 1165 */     else if (this.initialContextFactory.toUpperCase().indexOf("LDAP") > -1) {
/*      */ 
/*      */ 
/*      */       
/* 1169 */       current_defaults = defaults_LDAP;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1174 */     if (current_defaults != null) {
/*      */ 
/*      */ 
/*      */       
/* 1178 */       if (!this.properties.containsKey("USE_INITIAL_DIR_CONTEXT")) {
/* 1179 */         this.properties.setProperty("USE_INITIAL_DIR_CONTEXT", current_defaults[0]);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1184 */       if (!this.properties.containsKey("NAME_PREFIX") && current_defaults[1] != null) {
/* 1185 */         this.properties.setProperty("NAME_PREFIX", current_defaults[1]);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1190 */       if (!this.properties.containsKey("NAME_READABILITY_MARKER") && current_defaults[2] != null) {
/* 1191 */         this.properties.put("NAME_READABILITY_MARKER", current_defaults[2]);
/*      */ 
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1200 */       if (!this.properties.containsKey("USE_INITIAL_DIR_CONTEXT")) {
/* 1201 */         this.properties.put("USE_INITIAL_DIR_CONTEXT", "FALSE");
/*      */       }
/*      */ 
/*      */       
/* 1205 */       if (!this.properties.containsKey("NAME_PREFIX")) {
/*      */ 
/*      */ 
/*      */         
/* 1209 */         String icfProp = (String)this.properties.get("USE_INITIAL_DIR_CONTEXT");
/* 1210 */         if (icfProp.toUpperCase().equals("TRUE")) {
/* 1211 */           this.properties.put("NAME_PREFIX", "cn=");
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1219 */     if (Trace.isOn) {
/* 1220 */       Trace.exit(this, "com.ibm.mq.jms.admin.AdminService", "setICFProperties()");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   String getNamePrefix() {
/* 1230 */     if (Trace.isOn) {
/* 1231 */       Trace.entry(this, "com.ibm.mq.jms.admin.AdminService", "getNamePrefix()");
/*      */     }
/*      */     
/*      */     try {
/* 1235 */       String traceRet1 = (String)this.properties.get("NAME_PREFIX");
/* 1236 */       if (Trace.isOn) {
/* 1237 */         Trace.exit(this, "com.ibm.mq.jms.admin.AdminService", "getNamePrefix()", traceRet1, 1);
/*      */       }
/* 1239 */       return traceRet1;
/*      */     
/*      */     }
/* 1242 */     catch (Exception e) {
/* 1243 */       if (Trace.isOn) {
/* 1244 */         Trace.catchBlock(this, "com.ibm.mq.jms.admin.AdminService", "getNamePrefix()", e);
/*      */       }
/*      */       
/* 1247 */       if (Trace.isOn) {
/* 1248 */         Trace.exit(this, "com.ibm.mq.jms.admin.AdminService", "getNamePrefix()", null, 2);
/*      */       }
/* 1250 */       return null;
/*      */     }
/*      */     finally {
/*      */       
/* 1254 */       if (Trace.isOn)
/* 1255 */         Trace.finallyBlock(this, "com.ibm.mq.jms.admin.AdminService", "getNamePrefix()"); 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jms\admin\AdminService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */