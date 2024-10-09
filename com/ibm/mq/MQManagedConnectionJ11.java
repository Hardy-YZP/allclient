/*      */ package com.ibm.mq;
/*      */ 
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiObject;
/*      */ import com.ibm.mq.jmqi.JmqiUtils;
/*      */ import com.ibm.mq.jmqi.handles.Phconn;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.internal.Configuration;
/*      */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.net.URL;
/*      */ import java.util.Collection;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Vector;
/*      */ import javax.net.ssl.SSLSocketFactory;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class MQManagedConnectionJ11
/*      */   extends JmqiObject
/*      */ {
/*      */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQManagedConnectionJ11.java";
/*      */   private String qmgrName;
/*      */   private volatile MQSESSION session;
/*      */   
/*      */   static {
/*   83 */     if (Trace.isOn) {
/*   84 */       Trace.data("com.ibm.mq.MQManagedConnectionJ11", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQManagedConnectionJ11.java");
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
/*  103 */   private Phconn Hconn = MQSESSION.getJmqiEnv().newPhconn();
/*      */ 
/*      */   
/*      */   private volatile boolean connected = false;
/*      */ 
/*      */   
/*  109 */   private Hashtable<String, Object> properties = new Hashtable<>();
/*      */ 
/*      */   
/*      */   private boolean reusable = true;
/*      */ 
/*      */   
/*  115 */   Vector<MQQueueManager> qmgrs = new Vector<>();
/*      */ 
/*      */ 
/*      */   
/*      */   MQConnectionRequestInfo initialCxReqInf;
/*      */ 
/*      */ 
/*      */   
/*      */   MQManagedConnectionFactory initialMCF;
/*      */ 
/*      */ 
/*      */   
/*  127 */   MQManagedConnectionMetaData metaData = null;
/*      */ 
/*      */   
/*  130 */   Vector<MQConnectionEventListener> mqListeners = new Vector<>();
/*      */ 
/*      */   
/*      */   volatile boolean allowErrorEvents = false;
/*      */ 
/*      */   
/*  136 */   private int cmdLevel = -1;
/*      */ 
/*      */   
/*  139 */   private int platform = -1;
/*      */ 
/*      */   
/*  142 */   private int advCap = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQManagedConnectionJ11(String qmgrName1, Hashtable<String, Object> properties, MQConnectionRequestInfo cxRequestInfo, MQManagedConnectionFactory mcf) throws MQException {
/*  171 */     super(MQSESSION.getJmqiEnv());
/*  172 */     if (Trace.isOn) {
/*  173 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "<init>(String,Hashtable,MQConnectionRequestInfo,MQManagedConnectionFactory)", new Object[] { qmgrName1, 
/*      */             
/*  175 */             Trace.sanitizeMap(properties, "password", "XXXXXXXXXXXX"), cxRequestInfo, mcf });
/*      */     }
/*  177 */     Pint pCompCode = new Pint();
/*  178 */     Pint pReason = new Pint();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  183 */     this.qmgrName = qmgrName1;
/*  184 */     if (this.qmgrName == null) {
/*  185 */       this.qmgrName = "";
/*      */     }
/*      */ 
/*      */     
/*  189 */     if (properties == null || cxRequestInfo == null || mcf == null) {
/*      */       
/*  191 */       NullPointerException traceRet1 = new NullPointerException();
/*      */       
/*  193 */       if (Trace.isOn) {
/*  194 */         Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "<init>(String,Hashtable,MQConnectionRequestInfo,MQManagedConnectionFactory)", traceRet1, 1);
/*      */       }
/*      */ 
/*      */       
/*  198 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/*  202 */     this.initialCxReqInf = cxRequestInfo;
/*  203 */     this.initialMCF = (MQManagedConnectionFactory)mcf.clone();
/*      */     
/*  205 */     this.properties = properties;
/*  206 */     this.connected = false;
/*      */ 
/*      */     
/*  209 */     this.session = MQSESSION.getSession(this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  219 */     String transport = getStringProperty("transport");
/*      */ 
/*      */     
/*  222 */     URL ccdtUrl = null;
/*  223 */     if (transport.equals("MQSeries Client")) {
/*      */       
/*  225 */       Object ccdtUrlObject = getProperty("CCDT URL");
/*  226 */       if (ccdtUrlObject != null && ccdtUrlObject instanceof URL)
/*      */       {
/*  228 */         ccdtUrl = (URL)ccdtUrlObject;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  233 */     MQConnectionOptions cno = constructCNO(transport, ccdtUrl);
/*  234 */     JmqiConnectOptions jmqiCno = constructJmqiCNO(transport, ccdtUrl);
/*      */     
/*  236 */     this.session.MQCONNX_j(this.qmgrName, jmqiCno, cno, this.Hconn, pCompCode, pReason);
/*      */ 
/*      */     
/*  239 */     if (pCompCode.x == 1 && pReason.x == 2267) {
/*      */       
/*  241 */       pCompCode.x = 0;
/*  242 */       pReason.x = 0;
/*      */     } 
/*      */     
/*  245 */     if (pCompCode.x != 0 || pReason.x != 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  251 */       MQException me = new MQException(pCompCode.x, pReason.x, this, this.session.getLastJmqiException());
/*      */ 
/*      */       
/*  254 */       this.session = null;
/*      */       
/*  256 */       if (Trace.isOn) {
/*  257 */         Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "<init>(String,Hashtable,MQConnectionRequestInfo,MQManagedConnectionFactory)", me, 2);
/*      */       }
/*      */       
/*  260 */       throw me;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  266 */     this.platform = jmqiCno.getPlatform();
/*  267 */     this.cmdLevel = jmqiCno.getCmdLevel();
/*      */     
/*  269 */     if (this.platform < 0 || this.cmdLevel < 0 || this.advCap < 0) {
/*      */       
/*  271 */       Phobj pQmgrHobj = MQSESSION.getJmqiEnv().newPhobj();
/*  272 */       MQOD qmgrOD = new MQOD();
/*  273 */       qmgrOD.ObjectType = 5;
/*  274 */       int[] selectors = new int[2];
/*  275 */       selectors[0] = 32;
/*  276 */       selectors[1] = 31;
/*  277 */       int[] intAttrs = new int[2];
/*      */       
/*  279 */       this.session.MQOPEN(this.Hconn.getHconn(), qmgrOD, 32, pQmgrHobj, pCompCode, pReason);
/*      */       
/*  281 */       if (pCompCode.x != 2) {
/*      */         
/*  283 */         this.session.MQINQ(this.Hconn.getHconn(), pQmgrHobj.getHobj(), 2, selectors, 2, intAttrs, 0, null, pCompCode, pReason);
/*      */         
/*  285 */         if (pCompCode.x == 0) {
/*      */           
/*  287 */           this.platform = intAttrs[0];
/*  288 */           this.cmdLevel = intAttrs[1];
/*      */           
/*  290 */           if (this.advCap < 0) {
/*  291 */             if (this.platform != 1 || this.cmdLevel < 904) {
/*  292 */               this.advCap = 0;
/*      */             } else {
/*  294 */               selectors[0] = 273;
/*  295 */               intAttrs[0] = 0;
/*  296 */               this.session.MQINQ(this.Hconn.getHconn(), pQmgrHobj.getHobj(), 1, selectors, 1, intAttrs, 0, null, pCompCode, pReason);
/*  297 */               if (pCompCode.x == 0) {
/*  298 */                 this.advCap = intAttrs[0];
/*      */               }
/*      */             } 
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/*  305 */         this.session.MQCLOSE(this.Hconn.getHconn(), pQmgrHobj, 0, pCompCode, pReason);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  312 */     if (JmqiEnvironment.getOperatingSystem() == JmqiEnvironment.OS_ZSERIES && transport
/*  313 */       .equals("MQSeries Client") && (this.platform != 1 || this.advCap != 1))
/*      */     {
/*  315 */       if (!MQEnvironment.forceAllowClientConnection()) {
/*      */         
/*  317 */         this.session.MQDISC(this.Hconn, pCompCode, pReason);
/*  318 */         this.session = null;
/*      */         
/*  320 */         MQException me = new MQException(2, 2012, this);
/*      */         
/*  322 */         if (Trace.isOn) {
/*  323 */           Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "<init>(String,Hashtable,MQConnectionRequestInfo,MQManagedConnectionFactory)", me, 3);
/*      */         }
/*      */         
/*  326 */         throw me;
/*      */       } 
/*      */     }
/*  329 */     this.connected = true;
/*      */     
/*  331 */     if (Trace.isOn) {
/*  332 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "<init>(String,Hashtable,MQConnectionRequestInfo,MQManagedConnectionFactory)");
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
/*      */   public void addMQConnectionEventListener(MQConnectionEventListener listener) {
/*  350 */     if (Trace.isOn) {
/*  351 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "addMQConnectionEventListener(MQConnectionEventListener)", new Object[] { listener });
/*      */     }
/*      */ 
/*      */     
/*  355 */     if (listener == null) {
/*      */       
/*  357 */       NullPointerException traceRet1 = new NullPointerException();
/*      */       
/*  359 */       if (Trace.isOn) {
/*  360 */         Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "addMQConnectionEventListener(MQConnectionEventListener)", traceRet1);
/*      */       }
/*      */       
/*  363 */       throw traceRet1;
/*      */     } 
/*      */     
/*  366 */     this.mqListeners.addElement(listener);
/*      */     
/*  368 */     if (Trace.isOn) {
/*  369 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "addMQConnectionEventListener(MQConnectionEventListener)");
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
/*      */   public void removeMQConnectionEventListener(MQConnectionEventListener listener) {
/*  385 */     if (Trace.isOn) {
/*  386 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "removeMQConnectionEventListener(MQConnectionEventListener)", new Object[] { listener });
/*      */     }
/*      */ 
/*      */     
/*  390 */     if (listener == null) {
/*  391 */       NullPointerException traceRet1 = new NullPointerException();
/*      */       
/*  393 */       if (Trace.isOn) {
/*  394 */         Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "removeMQConnectionEventListener(MQConnectionEventListener)", traceRet1);
/*      */       }
/*      */       
/*  397 */       throw traceRet1;
/*      */     } 
/*      */     
/*  400 */     this.mqListeners.removeElement(listener);
/*      */     
/*  402 */     if (Trace.isOn) {
/*  403 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "removeMQConnectionEventListener(MQConnectionEventListener)");
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
/*      */   public synchronized Object getConnection(MQConnectionRequestInfo cxRequestInfo) throws MQResourceException {
/*      */     MQQueueManager qm;
/*  429 */     if (Trace.isOn) {
/*  430 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "getConnection(MQConnectionRequestInfo)", new Object[] { cxRequestInfo });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  435 */     if (!this.connected) {
/*  436 */       MQResourceException traceRet1 = new MQResourceException(2, 6124, this, "MQJI040");
/*  437 */       if (Trace.isOn) {
/*  438 */         Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "getConnection(MQConnectionRequestInfo)", traceRet1, 1);
/*      */       }
/*      */       
/*  441 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/*  445 */     if (!this.reusable) {
/*  446 */       MQResourceException traceRet1 = new MQResourceException(2, 6124, this, "MQJI042");
/*  447 */       if (Trace.isOn) {
/*  448 */         Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "getConnection(MQConnectionRequestInfo)", traceRet1, 2);
/*      */       }
/*      */       
/*  451 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  456 */     if (!isSuitable(cxRequestInfo, this.initialMCF)) {
/*  457 */       MQResourceException traceRet1 = new MQResourceException(2, 6124, this, "MQJI039");
/*  458 */       if (Trace.isOn) {
/*  459 */         Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "getConnection(MQConnectionRequestInfo)", traceRet1, 3);
/*      */       }
/*      */       
/*  462 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/*  467 */       qm = new MQQueueManager(this);
/*      */     }
/*  469 */     catch (MQException mqe) {
/*  470 */       if (Trace.isOn) {
/*  471 */         Trace.catchBlock(this, "com.ibm.mq.MQManagedConnectionJ11", "getConnection(MQConnectionRequestInfo)", mqe);
/*      */       }
/*      */       
/*  474 */       MQResourceException re = ReasonCodeInfo.getResourceException(mqe);
/*  475 */       if (Trace.isOn) {
/*  476 */         Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "getConnection(MQConnectionRequestInfo)", re, 4);
/*      */       }
/*      */       
/*  479 */       throw re;
/*      */     } 
/*      */ 
/*      */     
/*  483 */     this.qmgrs.addElement(qm);
/*      */ 
/*      */     
/*  486 */     this.allowErrorEvents = true;
/*      */     
/*  488 */     if (Trace.isOn) {
/*  489 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "getConnection(MQConnectionRequestInfo)", qm);
/*      */     }
/*      */     
/*  492 */     return qm;
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
/*      */   public synchronized void destroy() throws MQResourceException {
/*  509 */     if (Trace.isOn) {
/*  510 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "destroy()");
/*      */     }
/*  512 */     Pint pCompCode = new Pint();
/*  513 */     Pint pReason = new Pint();
/*      */     
/*  515 */     this.allowErrorEvents = false;
/*      */     
/*  517 */     Enumeration<MQQueueManager> e = this.qmgrs.elements();
/*  518 */     while (e.hasMoreElements()) {
/*  519 */       MQQueueManager qm = e.nextElement();
/*  520 */       synchronized (qm) {
/*  521 */         if (qm.isConnected()) {
/*  522 */           qm.cleanup();
/*      */         }
/*      */       } 
/*      */     } 
/*  526 */     this.qmgrs.removeAllElements();
/*  527 */     if (this.connected) {
/*  528 */       this.session.MQDISC(this.Hconn, pCompCode, pReason);
/*      */     } else {
/*      */       
/*  531 */       MQResourceException traceRet1 = new MQResourceException(2, 6124, this, "MQJI040");
/*      */       
/*  533 */       if (Trace.isOn) {
/*  534 */         Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "destroy()", traceRet1, 1);
/*      */       }
/*  536 */       throw traceRet1;
/*      */     } 
/*      */     
/*  539 */     JmqiException jmqiE = null;
/*  540 */     jmqiE = this.session.getLastJmqiException();
/*      */ 
/*      */ 
/*      */     
/*  544 */     this.connected = false;
/*  545 */     this.session = null;
/*      */     
/*  547 */     if (pCompCode.x != 0 || pReason.x != 0) {
/*      */       
/*  549 */       MQException mqe = new MQException(pCompCode.x, pReason.x, this, jmqiE);
/*      */       
/*  551 */       MQResourceException re = ReasonCodeInfo.getResourceException(mqe);
/*  552 */       if (Trace.isOn) {
/*  553 */         Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "destroy()", re, 2);
/*      */       }
/*  555 */       throw re;
/*      */     } 
/*  557 */     if (Trace.isOn) {
/*  558 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "destroy()");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void cleanup() throws MQResourceException {
/*  593 */     if (Trace.isOn) {
/*  594 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "cleanup()");
/*      */     }
/*  596 */     Pint pCompCode = new Pint();
/*  597 */     Pint pReason = new Pint();
/*      */     
/*  599 */     this.allowErrorEvents = false;
/*      */     
/*  601 */     Enumeration<MQQueueManager> e = this.qmgrs.elements();
/*  602 */     while (e.hasMoreElements()) {
/*  603 */       MQQueueManager qm = e.nextElement();
/*  604 */       synchronized (qm) {
/*  605 */         if (qm.isConnected()) {
/*  606 */           qm.cleanup();
/*      */         }
/*      */       } 
/*      */     } 
/*  610 */     this.qmgrs.removeAllElements();
/*      */ 
/*      */     
/*  613 */     String mtsupp = getStringProperty("Thread access");
/*  614 */     if (mtsupp != null && mtsupp.equals("SINGLE_THREAD")) {
/*  615 */       if (Trace.isOn)
/*      */       {
/*  617 */         Trace.data(this, "cleanup()", "cleanup attempted on SINGLE_THREAD MQManagedConnection", "");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  623 */       MQResourceException traceRet1 = new MQResourceException(2, 6120, this, "MQJI042");
/*      */       
/*  625 */       if (Trace.isOn) {
/*  626 */         Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "cleanup()", traceRet1, 1);
/*      */       }
/*  628 */       throw traceRet1;
/*      */     } 
/*      */     
/*  631 */     if (this.connected) {
/*  632 */       this.session.MQBACK(this.Hconn.getHconn(), pCompCode, pReason);
/*      */     } else {
/*      */       
/*  635 */       MQResourceException traceRet1 = new MQResourceException(2, 6120, this, "MQJI040");
/*  636 */       if (Trace.isOn) {
/*  637 */         Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "cleanup()", traceRet1, 2);
/*      */       }
/*  639 */       throw traceRet1;
/*      */     } 
/*      */     
/*  642 */     if (!this.reusable) {
/*      */ 
/*      */ 
/*      */       
/*  646 */       MQResourceException traceRet1 = new MQResourceException(2, 6120, this, "MQJI042");
/*  647 */       if (Trace.isOn) {
/*  648 */         Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "cleanup()", traceRet1, 3);
/*      */       }
/*  650 */       throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/*  654 */     if (pCompCode.x != 0 || pReason.x != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/*  659 */       if (pReason.x != 2012) {
/*      */         
/*  661 */         MQException mqe = new MQException(pCompCode.x, pReason.x, this, this.session.getLastJmqiException());
/*  662 */         MQResourceException re = ReasonCodeInfo.getResourceException(mqe);
/*  663 */         if (Trace.isOn) {
/*  664 */           Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "cleanup()", re, 4);
/*      */         }
/*  666 */         throw re;
/*      */       } 
/*      */     }
/*  669 */     if (Trace.isOn) {
/*  670 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "cleanup()");
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
/*      */   public synchronized MQManagedConnectionMetaData getMetaData() throws MQException {
/*  688 */     if (this.metaData == null) {
/*  689 */       this.metaData = new MQManagedConnectionMetaData(this);
/*      */     }
/*      */     
/*  692 */     if (Trace.isOn) {
/*  693 */       Trace.data(this, "com.ibm.mq.MQManagedConnectionJ11", "getMetaData()", "getter", this.metaData);
/*      */     }
/*  695 */     return this.metaData;
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
/*      */   public void fireConnectionClosedEvent(MQQueueManager qmgr, boolean calledFromFinalizer) {
/*      */     Vector<MQConnectionEventListener> ls;
/*  713 */     if (Trace.isOn) {
/*  714 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "fireConnectionClosedEvent(MQQueueManager,boolean)", new Object[] { qmgr, 
/*      */             
/*  716 */             Boolean.valueOf(calledFromFinalizer) });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  722 */     synchronized (this) {
/*  723 */       ls = (Vector<MQConnectionEventListener>)this.mqListeners.clone();
/*      */     } 
/*      */     
/*  726 */     for (MQConnectionEventListener l : ls) {
/*  727 */       l.connectionClosed(this, qmgr, calledFromFinalizer);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  732 */     this.qmgrs.removeElement(qmgr);
/*      */     
/*  734 */     if (Trace.isOn) {
/*  735 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "fireConnectionClosedEvent(MQQueueManager,boolean)");
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
/*      */   public void fireConnectionErrorEvent(MQQueueManager qmgr, Exception exception) {
/*  757 */     if (Trace.isOn) {
/*  758 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "fireConnectionErrorEvent(MQQueueManager,Exception)", new Object[] { qmgr, exception });
/*      */     }
/*      */ 
/*      */     
/*  762 */     if (this.allowErrorEvents) {
/*      */       Vector<MQConnectionEventListener> ls;
/*      */       
/*  765 */       synchronized (this) {
/*  766 */         ls = (Vector<MQConnectionEventListener>)this.mqListeners.clone();
/*      */       } 
/*      */       
/*  769 */       Enumeration<MQConnectionEventListener> e = ls.elements();
/*  770 */       while (e.hasMoreElements()) {
/*  771 */         MQConnectionEventListener l = e.nextElement();
/*  772 */         l.connectionErrorOccurred(this, qmgr, exception);
/*      */       } 
/*      */     } 
/*      */     
/*  776 */     if (Trace.isOn) {
/*  777 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "fireConnectionErrorEvent(MQQueueManager,Exception)");
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
/*      */   public boolean isConnected() {
/*  792 */     if (Trace.isOn) {
/*  793 */       Trace.data(this, "com.ibm.mq.MQManagedConnectionJ11", "isConnected()", "getter", 
/*  794 */           Boolean.valueOf(this.connected));
/*      */     }
/*  796 */     return this.connected;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQSESSION getMQSESSION() {
/*  806 */     if (Trace.isOn) {
/*  807 */       Trace.data(this, "com.ibm.mq.MQManagedConnectionJ11", "getMQSESSION()", "getter", this.session);
/*      */     }
/*  809 */     return this.session;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Phconn getHConn() {
/*  820 */     if (Trace.isOn) {
/*  821 */       Trace.data(this, "com.ibm.mq.MQManagedConnectionJ11", "getHConn()", "getter", this.Hconn);
/*      */     }
/*  823 */     return this.Hconn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getQmgrName() {
/*  834 */     if (Trace.isOn) {
/*  835 */       Trace.data(this, "com.ibm.mq.MQManagedConnectionJ11", "getQmgrName()", "getter", this.qmgrName);
/*      */     }
/*  837 */     return this.qmgrName;
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
/*      */   public Object getProperty(Object key) {
/*  851 */     if (Trace.isOn) {
/*  852 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "getProperty(Object)", new Object[] { key });
/*      */     }
/*      */     
/*  855 */     Object traceRet1 = this.properties.get(key);
/*      */     
/*  857 */     if (Trace.isOn) {
/*  858 */       int traceRet1Length = -1;
/*  859 */       if (key == "password" && traceRet1 != null) {
/*  860 */         traceRet1Length = ((String)traceRet1).length();
/*      */       }
/*  862 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "getProperty(Object)", (key == "password") ? 
/*      */ 
/*      */           
/*  865 */           Integer.valueOf(traceRet1Length) : traceRet1);
/*      */     } 
/*  867 */     return traceRet1;
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
/*      */   int getIntegerProperty(Object key) {
/*  882 */     if (Trace.isOn) {
/*  883 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "getIntegerProperty(Object)", new Object[] { key });
/*      */     }
/*      */     
/*  886 */     int traceRet1 = getIntegerProperty(key, 0);
/*      */     
/*  888 */     if (Trace.isOn) {
/*  889 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "getIntegerProperty(Object)", 
/*  890 */           Integer.valueOf(traceRet1));
/*      */     }
/*  892 */     return traceRet1;
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
/*      */   public int getIntegerProperty(Object key, int defaultValue) {
/*      */     int value;
/*  908 */     if (Trace.isOn) {
/*  909 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "getIntegerProperty(Object,int)", new Object[] { key, 
/*  910 */             Integer.valueOf(defaultValue) });
/*      */     }
/*      */     
/*  913 */     Object object = getProperty(key);
/*  914 */     if (object != null && object instanceof Integer) {
/*  915 */       value = ((Integer)object).intValue();
/*      */     } else {
/*  917 */       value = defaultValue;
/*      */     } 
/*      */     
/*  920 */     if (Trace.isOn) {
/*  921 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "getIntegerProperty(Object,int)", 
/*  922 */           Integer.valueOf(value));
/*      */     }
/*  924 */     return value;
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
/*      */   public String getStringProperty(Object key) {
/*  938 */     if (Trace.isOn) {
/*  939 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "getStringProperty(Object)", new Object[] { key });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  945 */     String traceRet1 = getStringProperty(key, null);
/*      */     
/*  947 */     if (Trace.isOn) {
/*  948 */       int traceRet1Length = -1;
/*  949 */       if (traceRet1 != null) {
/*  950 */         traceRet1Length = traceRet1.length();
/*      */       }
/*  952 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "getStringProperty(Object)", new Object[] { (key == "password") ? 
/*      */ 
/*      */             
/*  955 */             Integer.valueOf(traceRet1Length) : traceRet1 });
/*      */     } 
/*  957 */     return traceRet1;
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
/*      */   public String getStringProperty(Object key, String defaultValue) {
/*      */     String value;
/*  973 */     if (Trace.isOn) {
/*  974 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "getStringProperty(Object,String)", new Object[] { key, defaultValue });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  981 */     Object object = getProperty(key);
/*  982 */     if (object != null && object instanceof String) {
/*  983 */       value = (String)object;
/*      */     } else {
/*  985 */       value = defaultValue;
/*      */     } 
/*      */     
/*  988 */     if (Trace.isOn) {
/*  989 */       int valueLength = -1;
/*  990 */       if (value != null) {
/*  991 */         valueLength = value.length();
/*      */       }
/*  993 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "getStringProperty(Object,String)", new Object[] { (key == "password") ? 
/*      */ 
/*      */             
/*  996 */             Integer.valueOf(valueLength) : value });
/*      */     } 
/*  998 */     return value;
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
/*      */   String getStringProperty(Object key, String defaultValue, int length) {
/* 1018 */     if (Trace.isOn) {
/* 1019 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "getStringProperty(Object,String,int)", new Object[] { (key == "password") ? 
/*      */ 
/*      */             
/* 1022 */             Integer.valueOf(((String)key).length()) : key, Integer.valueOf(length) });
/*      */     }
/*      */     
/* 1025 */     String traceRet1 = MQSESSION.setStringToLength(getStringProperty(key, defaultValue), length);
/*      */ 
/*      */     
/* 1028 */     if (Trace.isOn) {
/* 1029 */       int traceRet1Length = -1;
/* 1030 */       if (traceRet1 != null) {
/* 1031 */         traceRet1Length = traceRet1.length();
/*      */       }
/* 1033 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "getStringProperty(Object,String,int)", new Object[] { (key == "password") ? 
/*      */ 
/*      */             
/* 1036 */             Integer.valueOf(traceRet1Length) : traceRet1 });
/*      */     } 
/* 1038 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNotReusable() {
/* 1047 */     if (Trace.isOn) {
/* 1048 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "setNotReusable()");
/*      */     }
/* 1050 */     this.reusable = false;
/*      */     
/* 1052 */     if (Trace.isOn) {
/* 1053 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "setNotReusable()");
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
/*      */   boolean isReusable() {
/* 1065 */     if (Trace.isOn) {
/* 1066 */       Trace.data(this, "com.ibm.mq.MQManagedConnectionJ11", "isReusable()", "getter", 
/* 1067 */           Boolean.valueOf(this.reusable));
/*      */     }
/* 1069 */     return this.reusable;
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
/*      */   boolean isSuitable(MQConnectionRequestInfo cxRequestInfo, MQManagedConnectionFactory mcf) {
/* 1087 */     if (Trace.isOn) {
/* 1088 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "isSuitable(MQConnectionRequestInfo,MQManagedConnectionFactory)", new Object[] { cxRequestInfo, mcf });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1095 */     if (this.connected && this.reusable && this.initialMCF.equals(mcf)) {
/*      */ 
/*      */ 
/*      */       
/* 1099 */       if (cxRequestInfo instanceof ClientConnectionRequestInfo) {
/*      */ 
/*      */ 
/*      */         
/* 1103 */         boolean traceRet1 = ((ClientConnectionRequestInfo)cxRequestInfo).isSuitable(this);
/*      */         
/* 1105 */         if (Trace.isOn) {
/* 1106 */           Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "isSuitable(MQConnectionRequestInfo,MQManagedConnectionFactory)", 
/*      */               
/* 1108 */               Boolean.valueOf(traceRet1), 1);
/*      */         }
/* 1110 */         return traceRet1;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1115 */       boolean traceRet2 = this.initialCxReqInf.equals(cxRequestInfo);
/* 1116 */       if (Trace.isOn) {
/* 1117 */         Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "isSuitable(MQConnectionRequestInfo,MQManagedConnectionFactory)", 
/*      */             
/* 1119 */             Boolean.valueOf(traceRet2), 2);
/*      */       }
/* 1121 */       return traceRet2;
/*      */     } 
/*      */     
/* 1124 */     if (Trace.isOn) {
/* 1125 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "isSuitable(MQConnectionRequestInfo,MQManagedConnectionFactory)", 
/* 1126 */           Boolean.valueOf(false), 3);
/*      */     }
/*      */     
/* 1129 */     return false;
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
/*      */   protected boolean authenticate(Pint pCompCode, Pint pReason) throws MQException {
/* 1149 */     if (Trace.isOn) {
/* 1150 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "authenticate(Pint,Pint)", new Object[] { pCompCode, pReason });
/*      */     }
/*      */     
/* 1153 */     String fid = "authenticate(Pint,Pint)";
/*      */     try {
/* 1155 */       MQSESSION session1 = MQSESSION.getSession(this);
/* 1156 */       if (Trace.isOn) {
/* 1157 */         Trace.data(this, "authenticate(Pint,Pint)", "Session associated with this managed connection is ", session1);
/*      */       }
/*      */ 
/*      */       
/* 1161 */       MQManagedConnectionMetaData metaData1 = getMetaData();
/* 1162 */       if (Trace.isOn) {
/* 1163 */         Trace.data(this, "authenticate(Pint,Pint)", "MetaData associated with this managed connection is ", metaData1
/*      */             
/* 1165 */             .toString());
/*      */       }
/* 1167 */       int level = metaData1.getCommandLevel();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1172 */       if (level >= 531) {
/* 1173 */         session1.setAuthenticateBindings(true);
/*      */       } else {
/* 1175 */         session1.setAuthenticateBindings(false);
/*      */       } 
/* 1177 */       String userId = getUserId();
/* 1178 */       String password = getStringProperty("password", "");
/*      */       
/* 1180 */       pCompCode.x = 0;
/* 1181 */       if (null != userId) {
/* 1182 */         session1.authenticate(this.Hconn.getHconn(), userId, password, pCompCode, pReason);
/*      */       }
/*      */ 
/*      */       
/* 1186 */       if (pCompCode.x == 2) {
/* 1187 */         if (Trace.isOn) {
/* 1188 */           Trace.data(this, "authenticate(Pint,Pint)", "Failed to authenticate userId and Password: rc=", 
/*      */               
/* 1190 */               Integer.toString(pReason.x));
/*      */         }
/*      */         
/* 1193 */         MQException me = new MQException(pCompCode.x, pReason.x, this, session1.getLastJmqiException());
/*      */         
/* 1195 */         if (Trace.isOn) {
/* 1196 */           Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "authenticate(Pint,Pint)", me, 1);
/*      */         }
/*      */         
/* 1199 */         throw me;
/*      */       } 
/*      */       
/* 1202 */       if (Trace.isOn) {
/* 1203 */         Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "authenticate(Pint,Pint)", 
/* 1204 */             Boolean.valueOf(true));
/*      */       }
/* 1206 */       return true;
/*      */     }
/* 1208 */     catch (RuntimeException rte) {
/* 1209 */       if (Trace.isOn) {
/* 1210 */         Trace.catchBlock(this, "com.ibm.mq.MQManagedConnectionJ11", "authenticate(Pint,Pint)", rte, 1);
/*      */       }
/*      */       
/* 1213 */       if (Trace.isOn) {
/* 1214 */         Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "authenticate(Pint,Pint)", rte, 2);
/*      */       }
/*      */       
/* 1217 */       throw rte;
/*      */     }
/* 1219 */     catch (Exception e) {
/* 1220 */       if (Trace.isOn) {
/* 1221 */         Trace.catchBlock(this, "com.ibm.mq.MQManagedConnectionJ11", "authenticate(Pint,Pint)", e, 2);
/*      */       }
/*      */       
/* 1224 */       if (e instanceof MQException) {
/* 1225 */         MQException traceRet1 = (MQException)e;
/* 1226 */         if (Trace.isOn) {
/* 1227 */           Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "authenticate(Pint,Pint)", traceRet1, 3);
/*      */         }
/*      */         
/* 1230 */         throw traceRet1;
/*      */       } 
/*      */       
/* 1233 */       MQException mqe = new MQException(2, 2102, this, e);
/*      */ 
/*      */       
/* 1236 */       if (Trace.isOn) {
/* 1237 */         Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "authenticate(Pint,Pint)", mqe, 4);
/*      */       }
/*      */       
/* 1240 */       throw mqe;
/*      */     } finally {
/*      */       
/* 1243 */       if (Trace.isOn) {
/* 1244 */         Trace.finallyBlock(this, "com.ibm.mq.MQManagedConnectionJ11", "authenticate(Pint,Pint)");
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
/*      */   
/*      */   private MQChannelDefinition constructMQCD() throws MQException {
/* 1259 */     if (Trace.isOn) {
/* 1260 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "constructMQCD()");
/*      */     }
/* 1262 */     String fid = "constructMQCD()";
/* 1263 */     MQChannelDefinition cd = new MQChannelDefinition();
/*      */     
/* 1265 */     cd.maxMessageLength = 104857600;
/*      */     
/* 1267 */     cd.channelName = getStringProperty("channel", "", 20);
/*      */     
/* 1269 */     cd.queueManagerName = this.qmgrName;
/*      */     
/* 1271 */     cd.connectionName = getStringProperty("hostname", "");
/*      */     
/* 1273 */     if (cd.connectionName == null || cd.connectionName.equals("")) {
/* 1274 */       cd.connectionName = "localhost";
/*      */     }
/*      */     
/* 1277 */     int port = getIntegerProperty("port", -1);
/* 1278 */     if (port == -1) {
/* 1279 */       port = 1414;
/*      */     }
/* 1281 */     cd.connectionName += "(" + Integer.toString(port) + ")";
/* 1282 */     String userID = getUserId();
/* 1283 */     String password = getStringProperty("password", "");
/* 1284 */     if (Trace.isOn) {
/* 1285 */       Trace.data(this, "constructMQCD()", "Setting CD.userID = ", userID);
/*      */     }
/* 1287 */     cd.remoteUserId = userID;
/* 1288 */     if (Trace.isOn) {
/* 1289 */       Trace.data(this, "constructMQCD()", "Setting CD.remotePassword = XXXXXXXXXXXX");
/*      */     }
/* 1291 */     cd.remotePassword = password;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1296 */     String sslCiphersSuite = getStringProperty("SSL Cipher Suite");
/*      */     
/* 1298 */     if (sslCiphersSuite != null && sslCiphersSuite.trim().length() > 0) {
/* 1299 */       Boolean sslFips = (Boolean)getProperty("SSL Fips Required");
/* 1300 */       boolean sslFipsboolean = (sslFips != null) ? sslFips.booleanValue() : false;
/*      */       
/* 1302 */       if (JmqiUtils.toCipherSpec(sslCiphersSuite, sslFipsboolean) == null) {
/* 1303 */         MQException traceRet1 = new MQException(2, 2400, this);
/*      */ 
/*      */ 
/*      */         
/* 1307 */         if (Trace.isOn) {
/* 1308 */           Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "constructMQCD()", traceRet1);
/*      */         }
/*      */         
/* 1311 */         throw traceRet1;
/*      */       } 
/*      */     } 
/* 1314 */     cd.sslCipherSuite = sslCiphersSuite;
/* 1315 */     cd.sslPeerName = getStringProperty("SSL Peer Name");
/*      */ 
/*      */     
/* 1318 */     Vector<Integer> hdrCompList = (Vector)getProperty("Header Compression Property");
/* 1319 */     cd.hdrCompList = hdrCompList;
/* 1320 */     Vector<Integer> msgCompList = (Vector)getProperty("Message Compression Property");
/* 1321 */     cd.msgCompList = msgCompList;
/*      */     
/* 1323 */     cd.localAddress = getStringProperty("Local Address Property");
/*      */ 
/*      */     
/* 1326 */     cd.sharingConversations = getIntegerProperty("sharingConversations", 10);
/*      */ 
/*      */     
/* 1329 */     if (Trace.isOn) {
/* 1330 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "constructMQCD()", cd);
/*      */     }
/* 1332 */     return cd;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String getUserId() {
/* 1341 */     String userId = getStringProperty("userID");
/* 1342 */     if (userId != null) {
/* 1343 */       userId = userId.trim();
/* 1344 */       if (userId.length() == 0) {
/* 1345 */         userId = null;
/*      */       }
/*      */     } 
/* 1348 */     if (Trace.isOn) {
/* 1349 */       Trace.data(this, "com.ibm.mq.MQManagedConnectionJ11", "getUserId()", "getter", userId);
/*      */     }
/* 1351 */     return userId;
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
/*      */   private MQConnectionOptions constructCNO(String transport, URL ccdtUrl) throws MQException {
/* 1367 */     if (Trace.isOn) {
/* 1368 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "constructCNO(String,URL)", new Object[] { transport, ccdtUrl });
/*      */     }
/*      */     
/* 1371 */     String fid = "constructCNO(String,URL)";
/* 1372 */     MQConnectionOptions mqcno = new MQConnectionOptions();
/*      */ 
/*      */ 
/*      */     
/* 1376 */     int options = getIntegerProperty("connectOptions");
/*      */     
/* 1378 */     int shareOpts = options & 0xE0;
/*      */ 
/*      */     
/* 1381 */     if (shareOpts == 0) {
/* 1382 */       options |= 0x40;
/*      */     }
/*      */ 
/*      */     
/* 1386 */     if (transport.equals("MQSeries Client")) {
/* 1387 */       options |= 0x2000000;
/*      */     }
/*      */     
/* 1390 */     mqcno.setOptions(options);
/* 1391 */     mqcno.setVersion(1);
/*      */     
/* 1393 */     if (transport.equals("MQSeries Client") && ccdtUrl == null)
/*      */     {
/*      */       
/* 1396 */       mqcno.setMQCD(constructMQCD());
/*      */     }
/*      */ 
/*      */     
/* 1400 */     Object obj = getProperty("ConnTag Property");
/* 1401 */     boolean set = false;
/* 1402 */     if (obj != null && obj instanceof byte[]) {
/* 1403 */       byte[] b = (byte[])obj;
/* 1404 */       for (int i = 0; i < b.length; i++) {
/* 1405 */         if (b[i] != 0) {
/* 1406 */           set = true;
/*      */         }
/*      */       } 
/* 1409 */       if (set == true) {
/* 1410 */         mqcno.setVersion(3);
/* 1411 */         mqcno.setConnTag(b);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1417 */     if (transport.equals("MQSeries Client")) {
/*      */       
/* 1419 */       int i = getIntegerProperty("KeyResetCount", 0);
/* 1420 */       boolean sslFipsRequired = false;
/* 1421 */       Boolean sslFips = (Boolean)getProperty("SSL Fips Required");
/* 1422 */       if (sslFips != null) {
/* 1423 */         sslFipsRequired = sslFips.booleanValue();
/*      */       }
/*      */       
/* 1426 */       if (i < 0 || i > 999999999) {
/* 1427 */         MQException traceRet2 = new MQException(2, 2409, this);
/*      */ 
/*      */ 
/*      */         
/* 1431 */         if (Trace.isOn) {
/* 1432 */           Trace.throwing(this, "com.ibm.mq.MQManagedConnectionJ11", "constructCNO(String,URL)", traceRet2);
/*      */         }
/*      */         
/* 1435 */         throw traceRet2;
/*      */       } 
/*      */       
/* 1438 */       if (i > 0 || sslFipsRequired) {
/*      */         
/* 1440 */         mqcno.setVersion(4);
/*      */         
/* 1442 */         MQSSLConfigurationOptions sco = new MQSSLConfigurationOptions();
/* 1443 */         sco.setKeyResetCount(i);
/* 1444 */         if (sslFipsRequired) {
/* 1445 */           sco.setFipsRequired(sslFipsRequired);
/*      */         }
/*      */         
/* 1448 */         sco.setVersion(2);
/*      */         
/* 1450 */         mqcno.setMQSCO(sco);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1455 */     int jmqiFlags = getIntegerProperty("JMQI FLAGS");
/*      */ 
/*      */ 
/*      */     
/* 1459 */     String password = getStringProperty("password", null);
/* 1460 */     String username = getUserId();
/*      */     
/* 1462 */     boolean createDefaultMQCSP = ((jmqiFlags & 0x80) != 0);
/*      */     
/* 1464 */     if ((createDefaultMQCSP || transport.equals("MQSeries Bindings")) && username != null && username
/* 1465 */       .trim().length() > 0) {
/*      */       
/* 1467 */       MQConnectionSecurityParameters csp = new MQConnectionSecurityParameters();
/* 1468 */       csp.setCSPUserId(username);
/* 1469 */       csp.setCSPPassword(password);
/* 1470 */       csp.setAuthenticationType(1);
/*      */       
/* 1472 */       mqcno.setVersion(5);
/*      */ 
/*      */       
/* 1475 */       mqcno.setMQCSP(csp);
/*      */       
/* 1477 */       if (Trace.isOn) {
/* 1478 */         Trace.data(this, "constructCNO(String,URL)", "MQCSP Username = ", username);
/* 1479 */         Trace.data(this, "constructCNO(String,URL)", "MQCSP Username length = ", Integer.toString(username.length()));
/* 1480 */         Trace.data(this, "constructCNO(String,URL)", "Constructed CSP AuthType ", Integer.toString(csp.getAuthenticationType()));
/*      */       } 
/*      */     } 
/*      */     
/* 1484 */     if (Trace.isOn) {
/* 1485 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "constructCNO(String,URL)", mqcno);
/*      */     }
/*      */     
/* 1488 */     return mqcno;
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
/*      */   private JmqiConnectOptions constructJmqiCNO(String transport, URL ccdtUrl) throws MQException {
/* 1506 */     if (Trace.isOn) {
/* 1507 */       Trace.entry(this, "com.ibm.mq.MQManagedConnectionJ11", "constructJmqiCNO(String,URL)", new Object[] { transport, ccdtUrl });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1512 */     JmqiSystemEnvironment jmqiSystemEnv = (JmqiSystemEnvironment)MQSESSION.getJmqiEnv();
/*      */     
/* 1514 */     JmqiConnectOptions jmqiCNO = jmqiSystemEnv.newJmqiConnectOptions();
/*      */     
/* 1516 */     String userId = getUserId();
/* 1517 */     if (userId != null) {
/* 1518 */       jmqiCNO.setUserIdentifier(userId);
/*      */     }
/* 1520 */     String password = getStringProperty("password");
/* 1521 */     if (password != null && password.length() > 0) {
/* 1522 */       jmqiCNO.setPassword(password);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1527 */     String appName = getStringProperty("APPNAME");
/* 1528 */     if (appName != null) {
/* 1529 */       jmqiCNO.setApplicationName(getStringProperty("APPNAME"));
/* 1530 */       if (appName.equals("com.ibm.mq.webui.backend") && transport.equals("MQSeries Bindings")) {
/* 1531 */         jmqiCNO.setWebAdminConnection(true);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1536 */     jmqiCNO.setCcdtUrl(ccdtUrl);
/*      */     
/* 1538 */     if (getProperty("channelSecurityExit") != null) {
/* 1539 */       jmqiCNO
/* 1540 */         .setSecurityExit(getProperty("channelSecurityExit"));
/* 1541 */       jmqiCNO
/* 1542 */         .setSecurityExitUserData((String)getProperty("channelSecurityExitUserData"));
/*      */     } else {
/* 1544 */       jmqiCNO
/* 1545 */         .setSecurityExit(getProperty("securityExit"));
/* 1546 */       jmqiCNO
/* 1547 */         .setSecurityExitUserData((String)getProperty("securityExitUserData"));
/*      */     } 
/* 1549 */     if (getProperty("channelReceiveExit") != null) {
/* 1550 */       jmqiCNO
/* 1551 */         .setReceiveExits(getProperty("channelReceiveExit"));
/* 1552 */       jmqiCNO
/* 1553 */         .setReceiveExitsUserData((String)getProperty("channelReceiveExitUserData"));
/*      */     } else {
/* 1555 */       jmqiCNO
/* 1556 */         .setReceiveExits(getProperty("receiveExit"));
/* 1557 */       jmqiCNO
/* 1558 */         .setReceiveExitsUserData((String)getProperty("receiveExitUserData"));
/*      */     } 
/* 1560 */     if (getProperty("channelSendExit") != null) {
/* 1561 */       jmqiCNO
/* 1562 */         .setSendExits(getProperty("channelSendExit"));
/* 1563 */       jmqiCNO
/* 1564 */         .setSendExitsUserData((String)getProperty("channelSendExitUserData"));
/*      */     } else {
/* 1566 */       jmqiCNO.setSendExits(getProperty("sendExit"));
/* 1567 */       jmqiCNO
/* 1568 */         .setSendExitsUserData((String)getProperty("sendExitUserData"));
/*      */     } 
/* 1570 */     if (getProperty("exitClasspath") != null) {
/* 1571 */       jmqiCNO
/* 1572 */         .setExitClassPath((String)getProperty("exitClasspath"));
/*      */     }
/*      */     
/* 1575 */     int ccsid = getIntegerProperty("CCSID");
/* 1576 */     if (ccsid > 0) {
/* 1577 */       jmqiCNO.setQueueManagerCCSID(ccsid);
/*      */     }
/*      */     
/* 1580 */     Object crlCertStores = getProperty("SSL CertStores");
/* 1581 */     if (crlCertStores != null && crlCertStores instanceof Collection) {
/* 1582 */       jmqiCNO.setCrlCertStores((Collection)crlCertStores);
/*      */     }
/*      */     
/* 1585 */     Object sslSocketFactory = getProperty("SSL Socket Factory");
/* 1586 */     if (sslSocketFactory != null && sslSocketFactory instanceof SSLSocketFactory)
/*      */     {
/* 1588 */       jmqiCNO.setSslSocketFactory((SSLSocketFactory)sslSocketFactory);
/*      */     }
/*      */ 
/*      */     
/* 1592 */     int jmqiFlags = getIntegerProperty("JMQI FLAGS");
/*      */ 
/*      */ 
/*      */     
/* 1596 */     Configuration config = this.env.getConfiguration();
/* 1597 */     Object objQMgrMQCSP = getProperty("Use MQCSP authentication");
/*      */     
/* 1599 */     Boolean useQMgrMQCSP = (objQMgrMQCSP instanceof Boolean) ? (Boolean)objQMgrMQCSP : ((objQMgrMQCSP instanceof String) ? Boolean.valueOf((String)objQMgrMQCSP) : null);
/* 1600 */     boolean useConfigMQCSP = config.getBoolValue(Configuration.USE_MQCSP_AUTHENTICATION);
/* 1601 */     boolean defaultConfigMQCSP = config.wasDefaulted((Configuration.CfgProperty)Configuration.USE_MQCSP_AUTHENTICATION);
/* 1602 */     boolean setBitMQCSP = ((jmqiFlags & 0x80) != 0);
/*      */     
/* 1604 */     if (Trace.isOn) {
/* 1605 */       Trace.data(this, "com.ibm.mq.MQManagedConnectionJ11", "constructJmqiCNO()", "useQMgrMQCSP", (useQMgrMQCSP != null) ? useQMgrMQCSP : "not set");
/* 1606 */       Trace.data(this, "com.ibm.mq.MQManagedConnectionJ11", "constructJmqiCNO()", "useConfigMQCSP", !defaultConfigMQCSP ? Boolean.valueOf(useConfigMQCSP) : "not set");
/*      */     } 
/*      */     
/* 1609 */     if ((setBitMQCSP || transport.equals("MQSeries Bindings")) && userId != null && userId
/* 1610 */       .trim().length() > 0) {
/* 1611 */       jmqiCNO.setFlag(128);
/*      */     } else {
/*      */       
/* 1614 */       jmqiCNO.unsetFlag(128);
/*      */     } 
/*      */     
/* 1617 */     if (Trace.isOn) {
/* 1618 */       Trace.exit(this, "com.ibm.mq.MQManagedConnectionJ11", "constructJmqiCNO(String,URL)", jmqiCNO);
/*      */     }
/*      */     
/* 1621 */     return jmqiCNO;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCmdLevel() {
/* 1630 */     if (Trace.isOn) {
/* 1631 */       Trace.data(this, "com.ibm.mq.MQManagedConnectionJ11", "getCmdLevel()", "getter", 
/* 1632 */           Integer.valueOf(this.cmdLevel));
/*      */     }
/* 1634 */     return this.cmdLevel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPlatform() {
/* 1644 */     if (Trace.isOn) {
/* 1645 */       Trace.data(this, "com.ibm.mq.MQManagedConnectionJ11", "getPlatform()", "getter", 
/* 1646 */           Integer.valueOf(this.platform));
/*      */     }
/* 1648 */     return this.platform;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQManagedConnectionJ11.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */