/*      */ package com.ibm.mq.ese.jmqi;
/*      */ 
/*      */ import com.ibm.mq.constants.QmgrSplCapability;
/*      */ import com.ibm.mq.ese.conv.CcsidConverter;
/*      */ import com.ibm.mq.ese.intercept.ConnectionContext;
/*      */ import com.ibm.mq.ese.intercept.JmqiCBInterceptor;
/*      */ import com.ibm.mq.ese.intercept.JmqiCloseInterceptor;
/*      */ import com.ibm.mq.ese.intercept.JmqiConnInterceptor;
/*      */ import com.ibm.mq.ese.intercept.JmqiContextContainer;
/*      */ import com.ibm.mq.ese.intercept.JmqiDiscInterceptor;
/*      */ import com.ibm.mq.ese.intercept.JmqiGetInterceptor;
/*      */ import com.ibm.mq.ese.intercept.JmqiInterceptorsFactory;
/*      */ import com.ibm.mq.ese.intercept.JmqiOpenInterceptor;
/*      */ import com.ibm.mq.ese.intercept.JmqiPutInterceptor;
/*      */ import com.ibm.mq.ese.intercept.JmqiSubscribeInterceptor;
/*      */ import com.ibm.mq.ese.intercept.MQGetContext;
/*      */ import com.ibm.mq.ese.intercept.MQPutContext;
/*      */ import com.ibm.mq.ese.intercept.SmqiObject;
/*      */ import com.ibm.mq.ese.pki.CertAccess;
/*      */ import com.ibm.mq.ese.pki.X509CertificateValidator;
/*      */ import com.ibm.mq.ese.pki.X509CertificateValidatorImpl;
/*      */ import com.ibm.mq.ese.prot.MessageProtection;
/*      */ import com.ibm.mq.ese.service.EseMQException;
/*      */ import com.ibm.mq.ese.service.EseMQService;
/*      */ import com.ibm.mq.ese.service.PolicyService;
/*      */ import com.ibm.mq.ese.service.ServicesFactoryImpl;
/*      */ import com.ibm.mq.ese.service.UserMapService;
/*      */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*      */ import com.ibm.mq.jmqi.JmqiException;
/*      */ import com.ibm.mq.jmqi.JmqiMQ;
/*      */ import com.ibm.mq.jmqi.MQCBD;
/*      */ import com.ibm.mq.jmqi.MQCNO;
/*      */ import com.ibm.mq.jmqi.MQGMO;
/*      */ import com.ibm.mq.jmqi.MQMD;
/*      */ import com.ibm.mq.jmqi.MQOD;
/*      */ import com.ibm.mq.jmqi.MQPMO;
/*      */ import com.ibm.mq.jmqi.MQSD;
/*      */ import com.ibm.mq.jmqi.handles.Hconn;
/*      */ import com.ibm.mq.jmqi.handles.Hobj;
/*      */ import com.ibm.mq.jmqi.handles.PbyteBuffer;
/*      */ import com.ibm.mq.jmqi.handles.Phconn;
/*      */ import com.ibm.mq.jmqi.handles.Phobj;
/*      */ import com.ibm.mq.jmqi.handles.Pint;
/*      */ import com.ibm.mq.jmqi.monitoring.AbstractJMQISupport;
/*      */ import com.ibm.mq.jmqi.system.JmqiConnectOptions;
/*      */ import com.ibm.mq.jmqi.system.JmqiSP;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.LpiPrivConnStruct;
/*      */ import com.ibm.mq.jmqi.system.LpiSD;
/*      */ import com.ibm.mq.jmqi.system.SpiConnectOptions;
/*      */ import com.ibm.mq.jmqi.system.SpiGetOptions;
/*      */ import com.ibm.mq.jmqi.system.SpiOpenOptions;
/*      */ import com.ibm.mq.jmqi.system.SpiPutOptions;
/*      */ import com.ibm.mq.jmqi.system.zrfp.Triplet;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.HashMap;
/*      */ import java.util.concurrent.atomic.AtomicInteger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class InterceptedJmqiImpl
/*      */   extends AbstractJMQISupport
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/jmqi/InterceptedJmqiImpl.java";
/*      */   
/*      */   static {
/*   92 */     if (Trace.isOn) {
/*   93 */       Trace.data("com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/jmqi/InterceptedJmqiImpl.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  103 */   private JmqiInterceptorsFactory interceptorsFactory = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JmqiConnInterceptor connInterceptor;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JmqiOpenInterceptor openInterceptor;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JmqiPutInterceptor putInterceptor;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JmqiGetInterceptor getInterceptor;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JmqiCBInterceptor cbInterceptor;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JmqiCloseInterceptor closeInterceptor;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JmqiDiscInterceptor discInterceptor;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JmqiSubscribeInterceptor subscribeInterceptor;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JmqiContextContainer contextContainer;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private PolicyService policyService;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InterceptedJmqiImpl(JmqiEnvironment env, int options, JmqiMQ mq) throws JmqiException {
/*  161 */     super(env, options, mq);
/*  162 */     if (Trace.isOn) {
/*  163 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "<init>(JmqiEnvironment,int,JmqiMQ)", new Object[] { env, 
/*  164 */             Integer.valueOf(options), mq });
/*      */     }
/*      */     
/*      */     try {
/*  168 */       this.delegate = mq;
/*  169 */       this.interceptorsFactory = new JmqiInterceptorsFactory(env, this.delegate);
/*  170 */       if (!(env instanceof JmqiSystemEnvironment)) {
/*  171 */         JmqiException traceRet1 = new JmqiException(env, -1, new String[0], 2, 2286, new IllegalArgumentException("JmqiEnvironment must be of JmqiSystemEnvironment type"));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  178 */         if (Trace.isOn) {
/*  179 */           Trace.throwing(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "<init>(JmqiEnvironment,int,JmqiMQ)", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/*  182 */         throw traceRet1;
/*      */       } 
/*  184 */       JmqiSystemEnvironment sysEnv = (JmqiSystemEnvironment)env;
/*  185 */       this.contextContainer = new JmqiContextContainer();
/*  186 */       createServices(sysEnv);
/*  187 */       createInterceptors();
/*      */     }
/*  189 */     catch (Exception e) {
/*  190 */       if (Trace.isOn) {
/*  191 */         Trace.catchBlock(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "<init>(JmqiEnvironment,int,JmqiMQ)", e);
/*      */       }
/*      */       
/*  194 */       HashMap<String, Exception> data = new HashMap<>();
/*  195 */       data.put("exception", e);
/*  196 */       Trace.ffst(InterceptedJmqiImpl.class.getName(), "<InterceptedJmqiImpl>", "MP008001", data, null);
/*  197 */       JmqiException traceRet2 = new JmqiException(env, -1, new String[0], 2, 2286, e);
/*      */ 
/*      */ 
/*      */       
/*  201 */       if (Trace.isOn) {
/*  202 */         Trace.throwing(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "<init>(JmqiEnvironment,int,JmqiMQ)", (Throwable)traceRet2, 2);
/*      */       }
/*      */       
/*  205 */       throw traceRet2;
/*      */     } 
/*  207 */     if (Trace.isOn) {
/*  208 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "<init>(JmqiEnvironment,int,JmqiMQ)");
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
/*      */   private void createServices(JmqiSystemEnvironment myEnv) {
/*  222 */     if (Trace.isOn) {
/*  223 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "createServices(JmqiSystemEnvironment)", new Object[] { myEnv });
/*      */     }
/*      */     
/*  226 */     ServicesFactoryImpl servicesFactoryImpl = new ServicesFactoryImpl();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  233 */     EseMQService mqService = servicesFactoryImpl.createMQService(this.delegate, (JmqiEnvironment)myEnv);
/*      */     
/*  235 */     UserMapService identityService = servicesFactoryImpl.createIdentityService();
/*      */     
/*  237 */     CertAccess certAccess = servicesFactoryImpl.createCertAccess((JmqiEnvironment)myEnv);
/*  238 */     X509CertificateValidatorImpl validator = new X509CertificateValidatorImpl(certAccess);
/*      */ 
/*      */     
/*  241 */     MessageProtection cryptoService = servicesFactoryImpl.createProtectionService((JmqiEnvironment)myEnv, (X509CertificateValidator)validator);
/*      */     
/*  243 */     CcsidConverter converter = servicesFactoryImpl.createCcsidConverter(this.delegate, myEnv);
/*      */     
/*  245 */     this.policyService = servicesFactoryImpl.createPolicyService(this.delegate, myEnv, mqService);
/*      */     
/*  247 */     this.interceptorsFactory.setEseIdentityService(identityService);
/*  248 */     this.interceptorsFactory.setEseMQService(mqService);
/*  249 */     this.interceptorsFactory.setCryptoService(cryptoService);
/*  250 */     this.interceptorsFactory.setCcsidConverter(converter);
/*  251 */     this.interceptorsFactory.setPolicyService(this.policyService);
/*  252 */     this.interceptorsFactory.setCertAccess(certAccess);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  257 */     if (Trace.isOn) {
/*  258 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "createServices(JmqiSystemEnvironment)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void createInterceptors() {
/*  268 */     if (Trace.isOn) {
/*  269 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "createInterceptors()");
/*      */     }
/*  271 */     this
/*  272 */       .connInterceptor = this.interceptorsFactory.createConnInterceptor(this.contextContainer);
/*  273 */     this
/*  274 */       .openInterceptor = this.interceptorsFactory.createOpenInterceptor(this.contextContainer);
/*  275 */     this
/*  276 */       .putInterceptor = this.interceptorsFactory.createPutInterceptor(this.contextContainer);
/*  277 */     this
/*  278 */       .getInterceptor = this.interceptorsFactory.createGetInterceptor(this.contextContainer);
/*  279 */     this.cbInterceptor = this.interceptorsFactory.createCBInterceptor(this.contextContainer, this.getInterceptor);
/*      */     
/*  281 */     this
/*  282 */       .closeInterceptor = this.interceptorsFactory.createCloseInterceptor(this.contextContainer);
/*  283 */     this
/*  284 */       .discInterceptor = this.interceptorsFactory.createDiscInterceptor(this.contextContainer);
/*  285 */     this
/*  286 */       .subscribeInterceptor = this.interceptorsFactory.createSubscribeInterceptor(this.contextContainer);
/*  287 */     if (Trace.isOn) {
/*  288 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "createInterceptors()");
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
/*      */   public void MQCONN(String mgrName, Phconn phconn, Pint compCode, Pint reason) {
/*  302 */     if (Trace.isOn) {
/*  303 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQCONN(String,Phconn,Pint,Pint)", new Object[] { mgrName, phconn, compCode, reason });
/*      */     }
/*      */     
/*  306 */     this.delegate.MQCONN(mgrName, phconn, compCode, reason);
/*  307 */     QmgrSplCapability capability = phconn.getHconn().getQmgrSplCapability();
/*  308 */     if (compCode.x != 2 && interceptMqCalls(capability)) {
/*  309 */       this.connInterceptor.afterMQCONN(capability, mgrName, phconn, compCode, reason);
/*      */     }
/*  311 */     if (Trace.isOn) {
/*  312 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQCONN(String,Phconn,Pint,Pint)");
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
/*      */   public void MQCONNX(String mgrName, MQCNO connectOpts, Phconn phconn, Pint compCode, Pint reason) {
/*  324 */     if (Trace.isOn) {
/*  325 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQCONNX(String,MQCNO,Phconn,Pint,Pint)", new Object[] { mgrName, connectOpts, phconn, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/*  329 */     this.delegate.MQCONNX(mgrName, connectOpts, phconn, compCode, reason);
/*  330 */     QmgrSplCapability capability = phconn.getHconn().getQmgrSplCapability();
/*  331 */     if (compCode.x != 2 && interceptMqCalls(capability)) {
/*  332 */       this.connInterceptor.afterMQCONNX(capability, mgrName, connectOpts, phconn, compCode, reason);
/*      */     }
/*      */     
/*  335 */     if (Trace.isOn) {
/*  336 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQCONNX(String,MQCNO,Phconn,Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void spiConnect(String mgrName, SpiConnectOptions spiConnectOpts, MQCNO connectOpts, Phconn phconn, Pint compCode, Pint reason) {
/*  347 */     if (Trace.isOn) {
/*  348 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiConnect(String,SpiConnectOptions,MQCNO,Phconn,Pint,Pint)", new Object[] { mgrName, spiConnectOpts, connectOpts, phconn, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/*  352 */     ((JmqiSP)this.delegate).spiConnect(mgrName, (LpiPrivConnStruct)spiConnectOpts, connectOpts, phconn, compCode, reason);
/*      */     
/*  354 */     QmgrSplCapability capability = phconn.getHconn().getQmgrSplCapability();
/*  355 */     if (compCode.x != 2 && interceptMqCalls(capability)) {
/*  356 */       this.connInterceptor.afterSpiConnect(capability, mgrName, spiConnectOpts, connectOpts, phconn, compCode, reason);
/*      */     }
/*      */     
/*  359 */     if (Trace.isOn) {
/*  360 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiConnect(String,SpiConnectOptions,MQCNO,Phconn,Pint,Pint)");
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
/*      */   public void jmqiConnect(String mgrName, JmqiConnectOptions jmqiConnectOpts, MQCNO connectOpts, Hconn parentHconn, Phconn phconn, Pint compCode, Pint reason) {
/*  372 */     if (Trace.isOn) {
/*  373 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)", new Object[] { mgrName, jmqiConnectOpts, connectOpts, parentHconn, phconn, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/*  377 */     ((JmqiSP)this.delegate).jmqiConnect(mgrName, jmqiConnectOpts, connectOpts, parentHconn, phconn, compCode, reason);
/*      */     
/*  379 */     QmgrSplCapability capability = phconn.getHconn().getQmgrSplCapability();
/*  380 */     if (compCode.x != 2 && interceptMqCalls(capability)) {
/*  381 */       this.connInterceptor.afterJmqiConnect(capability, mgrName, jmqiConnectOpts, connectOpts, parentHconn, phconn, compCode, reason);
/*      */     }
/*      */     
/*  384 */     if (Trace.isOn) {
/*  385 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiConnect(String,JmqiConnectOptions,MQCNO,Hconn,Phconn,Pint,Pint)");
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
/*      */   public void MQDISC(Phconn phconn, Pint cc, Pint rc) {
/*  401 */     if (Trace.isOn) {
/*  402 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQDISC(Phconn,Pint,Pint)", new Object[] { phconn, cc, rc });
/*      */     }
/*      */     
/*  405 */     cc.x = 0;
/*  406 */     rc.x = 0;
/*      */     
/*  408 */     boolean doIntercept = interceptMqCalls(phconn.getHconn());
/*  409 */     if (!doIntercept) {
/*  410 */       this.delegate.MQDISC(phconn, cc, rc);
/*  411 */       if (Trace.isOn) {
/*  412 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQDISC(Phconn,Pint,Pint)", 1);
/*      */       }
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/*  420 */     Hconn originalDiscHconn = phconn.getHconn();
/*      */     try {
/*  422 */       this.discInterceptor.beforeMQDISC(phconn, cc, rc);
/*  423 */       if (cc.x == 2) {
/*  424 */         if (Trace.isOn) {
/*  425 */           Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQDISC(Phconn,Pint,Pint)", 2);
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  431 */       this.delegate.MQDISC(phconn, cc, rc);
/*  432 */       if (cc.x == 2) {
/*  433 */         if (Trace.isOn) {
/*  434 */           Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQDISC(Phconn,Pint,Pint)", 3);
/*      */         }
/*      */         
/*      */         return;
/*      */       } 
/*      */     } finally {
/*  440 */       this.discInterceptor.afterMQDISC(originalDiscHconn, phconn, cc, rc);
/*  441 */       if (Trace.isOn) {
/*  442 */         Trace.finallyBlock(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQDISC(Phconn,Pint,Pint)");
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  447 */     if (Trace.isOn) {
/*  448 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQDISC(Phconn,Pint,Pint)", 4);
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
/*      */   public void MQOPEN(Hconn hconn, MQOD objDesc, int options, Phobj hobj, Pint compCode, Pint reason) {
/*  465 */     if (Trace.isOn) {
/*  466 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", new Object[] { hconn, objDesc, 
/*      */             
/*  468 */             Integer.valueOf(options), hobj, compCode, reason });
/*      */     }
/*  470 */     boolean doIntercept = interceptMqCalls(hconn);
/*  471 */     if (!doIntercept) {
/*  472 */       this.delegate.MQOPEN(hconn, objDesc, options, hobj, compCode, reason);
/*  473 */       if (Trace.isOn) {
/*  474 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  479 */     compCode.x = 0;
/*  480 */     reason.x = 0;
/*      */     
/*  482 */     SmqiObject eseObject = this.openInterceptor.beforeMQOPEN(hconn, objDesc, options, hobj, compCode, reason);
/*      */     
/*  484 */     if (compCode.x == 2) {
/*  485 */       if (Trace.isOn) {
/*  486 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  491 */     this.delegate.MQOPEN(hconn, objDesc, options, hobj, compCode, reason);
/*  492 */     if (compCode.x != 2) {
/*  493 */       this.openInterceptor.afterMQOPEN(eseObject, hconn, objDesc, options, hobj, compCode, reason);
/*      */     }
/*      */     
/*  496 */     if (Trace.isOn) {
/*  497 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQOPEN(Hconn,MQOD,int,Phobj,Pint,Pint)", 3);
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
/*      */   public void spiOpen(Hconn hconn, MQOD od, SpiOpenOptions options, Phobj hobj, Pint compCode, Pint reason) {
/*  514 */     if (Trace.isOn) {
/*  515 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiOpen(Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)", new Object[] { hconn, od, options, hobj, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/*  519 */     boolean doIntercept = interceptMqCalls(hconn);
/*  520 */     if (!doIntercept) {
/*  521 */       ((JmqiSP)this.delegate).spiOpen(hconn, od, options, hobj, compCode, reason);
/*  522 */       if (Trace.isOn) {
/*  523 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiOpen(Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  529 */     SmqiObject eseObject = this.openInterceptor.beforeSpiOpen(hconn, od, options, hobj, compCode, reason);
/*      */ 
/*      */     
/*  532 */     if (requestPolicy(hconn)) {
/*  533 */       options.setVersion(3);
/*  534 */       options.setLpiOptions(options.getLpiOptions() | 0x20);
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  539 */         boolean isZOS = (hconn.getPlatform() == 1);
/*      */         
/*  541 */         options.setZOS(isZOS);
/*      */       }
/*  543 */       catch (JmqiException e) {
/*  544 */         if (Trace.isOn) {
/*  545 */           Trace.catchBlock(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiOpen(Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)", (Throwable)e);
/*      */         }
/*      */         
/*  548 */         compCode.x = 2;
/*  549 */         reason.x = 2195;
/*      */       } 
/*      */     } 
/*      */     
/*  553 */     if (compCode.x == 2) {
/*  554 */       if (Trace.isOn) {
/*  555 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiOpen(Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  560 */     ((JmqiSP)this.delegate).spiOpen(hconn, od, options, hobj, compCode, reason);
/*  561 */     if (compCode.x != 2) {
/*  562 */       this.openInterceptor.afterSpiOpen(eseObject, hconn, od, options, hobj, compCode, reason);
/*      */     }
/*      */     
/*  565 */     if (Trace.isOn) {
/*  566 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiOpen(Hconn,MQOD,SpiOpenOptions,Phobj,Pint,Pint)", 3);
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
/*      */   public void MQCLOSE(Hconn hconn, Phobj phobj, int options, Pint cc, Pint rc) {
/*  583 */     if (Trace.isOn) {
/*  584 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", new Object[] { hconn, phobj, 
/*  585 */             Integer.valueOf(options), cc, rc });
/*      */     }
/*      */     
/*  588 */     boolean doIntercept = interceptMqCalls(hconn);
/*  589 */     if (!doIntercept) {
/*  590 */       this.delegate.MQCLOSE(hconn, phobj, options, cc, rc);
/*  591 */       if (Trace.isOn) {
/*  592 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  598 */     Hobj closingHobj = phobj.getHobj();
/*  599 */     this.delegate.MQCLOSE(hconn, phobj, options, cc, rc);
/*  600 */     if (cc.x == 2) {
/*  601 */       if (Trace.isOn) {
/*  602 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  607 */     this.closeInterceptor.afterMQCLOSE(closingHobj, hconn, phobj, options, cc, rc);
/*  608 */     if (Trace.isOn) {
/*  609 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQCLOSE(Hconn,Phobj,int,Pint,Pint)", 3);
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
/*      */   public void jmqiPut(Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, ByteBuffer[] buffers, Pint cc, Pint rc) {
/*  628 */     if (Trace.isOn) {
/*  629 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", new Object[] { hconn, hobj, msgDesc, putMsgOpts, buffers, cc, rc });
/*      */     }
/*      */ 
/*      */     
/*  633 */     boolean doIntercept = interceptMqCalls(hconn);
/*  634 */     if (!doIntercept || 
/*  635 */       !this.contextContainer.hasProtectedConn(hconn)) {
/*  636 */       ((JmqiSP)this.delegate).jmqiPut(hconn, hobj, msgDesc, putMsgOpts, buffers, cc, rc);
/*      */       
/*  638 */       if (Trace.isOn) {
/*  639 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  645 */     MQPutContext esePutData = this.putInterceptor.beforeJmqiPut(hconn, hobj, msgDesc, putMsgOpts, buffers, cc, rc);
/*      */     
/*  647 */     if (cc.x == 2) {
/*  648 */       if (Trace.isOn) {
/*  649 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  654 */     ((JmqiSP)this.delegate).jmqiPut(hconn, hobj, msgDesc, putMsgOpts, esePutData
/*  655 */         .getBuffers(), cc, rc);
/*  656 */     if (cc.x == 2) {
/*  657 */       if (Trace.isOn) {
/*  658 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  663 */     this.putInterceptor.afterJmqiPut(esePutData, hconn, hobj, msgDesc, putMsgOpts, buffers, cc, rc);
/*      */     
/*  665 */     if (Trace.isOn) {
/*  666 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiPut(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", 4);
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
/*      */   public void MQPUT(Hconn hconn, Hobj hobj, MQMD msgDesc, MQPMO putMsgOpts, int bufferLength, ByteBuffer buffer, Pint cc, Pint rc) {
/*  683 */     if (Trace.isOn) {
/*  684 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, msgDesc, putMsgOpts, 
/*      */             
/*  686 */             Integer.valueOf(bufferLength), buffer, cc, rc });
/*      */     }
/*  688 */     boolean doIntercept = interceptMqCalls(hconn);
/*  689 */     if (!doIntercept || 
/*  690 */       !this.contextContainer.hasProtectedConn(hconn)) {
/*  691 */       this.delegate.MQPUT(hconn, hobj, msgDesc, putMsgOpts, bufferLength, buffer, cc, rc);
/*      */       
/*  693 */       if (Trace.isOn) {
/*  694 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  700 */     MQPutContext putContext = this.putInterceptor.beforeMQPUT(hconn, hobj, msgDesc, putMsgOpts, bufferLength, buffer, cc, rc);
/*      */     
/*  702 */     if (cc.x == 2) {
/*  703 */       if (Trace.isOn) {
/*  704 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  710 */     ByteBuffer protectedBuffer = putContext.getBuffer();
/*      */     
/*  712 */     int protectedBuffLength = (protectedBuffer == buffer) ? bufferLength : protectedBuffer.limit();
/*  713 */     this.delegate.MQPUT(hconn, hobj, msgDesc, putMsgOpts, protectedBuffLength, protectedBuffer, cc, rc);
/*      */     
/*  715 */     if (cc.x == 2) {
/*  716 */       if (Trace.isOn) {
/*  717 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  722 */     this.putInterceptor.afterMQPUT(putContext, hconn, hobj, msgDesc, putMsgOpts, bufferLength, buffer, cc, rc);
/*      */     
/*  724 */     if (Trace.isOn) {
/*  725 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQPUT(Hconn,Hobj,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", 4);
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
/*      */   public void spiPut(Hconn hconn, Hobj hobj, MQMD mqmd, MQPMO mqpmo, SpiPutOptions spiPutOpts, int bufferLength, ByteBuffer buffer, Pint cc, Pint rc) {
/*  744 */     if (Trace.isOn) {
/*  745 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, hobj, mqmd, mqpmo, spiPutOpts, 
/*      */             
/*  747 */             Integer.valueOf(bufferLength), buffer, cc, rc });
/*      */     }
/*  749 */     boolean doIntercept = interceptMqCalls(hconn);
/*  750 */     if (!doIntercept || 
/*  751 */       !this.contextContainer.hasProtectedConn(hconn)) {
/*  752 */       ((JmqiSP)this.delegate).spiPut(hconn, hobj, mqmd, mqpmo, spiPutOpts, bufferLength, buffer, cc, rc);
/*      */       
/*  754 */       if (Trace.isOn) {
/*  755 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  761 */     MQPutContext esePutData = this.putInterceptor.beforeSpiPut(hconn, hobj, mqmd, mqpmo, spiPutOpts, bufferLength, buffer, cc, rc);
/*      */     
/*  763 */     if (cc.x == 2) {
/*  764 */       if (Trace.isOn) {
/*  765 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  771 */     ByteBuffer protectedBuffer = esePutData.getBuffer();
/*      */     
/*  773 */     int protectedBuffLength = (protectedBuffer == buffer) ? bufferLength : protectedBuffer.limit();
/*  774 */     ((JmqiSP)this.delegate).spiPut(hconn, hobj, mqmd, mqpmo, spiPutOpts, protectedBuffLength, protectedBuffer, cc, rc);
/*      */     
/*  776 */     if (cc.x == 2) {
/*  777 */       if (Trace.isOn) {
/*  778 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  783 */     this.putInterceptor.afterSpiPut(esePutData, hconn, hobj, mqmd, mqpmo, spiPutOpts, bufferLength, buffer, cc, rc);
/*      */     
/*  785 */     if (Trace.isOn) {
/*  786 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,int,ByteBuffer,Pint,Pint)", 4);
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
/*      */   public void spiPut(Hconn hconn, Hobj hobj, MQMD mqmd, MQPMO mqpmo, SpiPutOptions spiPutOpts, ByteBuffer[] buffers, Pint cc, Pint rc) {
/*  804 */     if (Trace.isOn) {
/*  805 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],Pint,Pint)", new Object[] { hconn, hobj, mqmd, mqpmo, spiPutOpts, buffers, cc, rc });
/*      */     }
/*      */ 
/*      */     
/*  809 */     boolean doIntercept = interceptMqCalls(hconn);
/*  810 */     if (!doIntercept || 
/*  811 */       !this.contextContainer.hasProtectedConn(hconn)) {
/*  812 */       ((JmqiSP)this.delegate).spiPut(hconn, hobj, mqmd, mqpmo, spiPutOpts, buffers, cc, rc);
/*      */       
/*  814 */       if (Trace.isOn) {
/*  815 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],Pint,Pint)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  821 */     MQPutContext esePutData = this.putInterceptor.beforeSpiPut(hconn, hobj, mqmd, mqpmo, spiPutOpts, buffers, cc, rc);
/*      */     
/*  823 */     if (cc.x == 2) {
/*  824 */       if (Trace.isOn) {
/*  825 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  830 */     ((JmqiSP)this.delegate).spiPut(hconn, hobj, mqmd, mqpmo, spiPutOpts, esePutData
/*  831 */         .getBuffers(), cc, rc);
/*  832 */     if (cc.x == 2) {
/*  833 */       if (Trace.isOn) {
/*  834 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],Pint,Pint)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*  839 */     this.putInterceptor.afterSpiPut(esePutData, hconn, hobj, mqmd, mqpmo, spiPutOpts, buffers, cc, rc);
/*      */     
/*  841 */     if (Trace.isOn) {
/*  842 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiPut(Hconn,Hobj,MQMD,MQPMO,SpiPutOptions,ByteBuffer [ ],Pint,Pint)", 4);
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
/*      */   public void jmqiPutWithTriplets(Hconn hconn, Hobj hobj, MQMD mqmd, MQPMO mqpmo, ByteBuffer[] buffer, Triplet[] triplets, Pint compCode, Pint reason) {
/*  855 */     if (Trace.isOn) {
/*  856 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", new Object[] { hconn, hobj, mqmd, mqpmo, buffer, triplets, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/*  860 */     boolean doIntercept = interceptMqCalls(hconn);
/*  861 */     if (!doIntercept || 
/*  862 */       !this.contextContainer.hasProtectedConn(hconn)) {
/*  863 */       ((JmqiSP)this.delegate).jmqiPutWithTriplets(hconn, hobj, mqmd, mqpmo, buffer, triplets, compCode, reason);
/*      */       
/*  865 */       if (Trace.isOn) {
/*  866 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  872 */     this.putInterceptor.beforeJmqiPutWithTriplets(hconn, hobj, mqmd, mqpmo, buffer, triplets, compCode, reason);
/*      */     
/*  874 */     ((JmqiSP)this.delegate).jmqiPutWithTriplets(hconn, hobj, mqmd, mqpmo, buffer, triplets, compCode, reason);
/*      */     
/*  876 */     this.putInterceptor.afterJmqiPutWithTriplets(hconn, hobj, mqmd, mqpmo, buffer, triplets, compCode, reason);
/*      */     
/*  878 */     if (Trace.isOn) {
/*  879 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiPutWithTriplets(Hconn,Hobj,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class Put1Hobj
/*      */     implements Hobj
/*      */   {
/*      */     private final int handle;
/*      */ 
/*      */ 
/*      */     
/*  894 */     private static AtomicInteger counter = new AtomicInteger(0);
/*      */     
/*      */     public Put1Hobj() {
/*  897 */       if (Trace.isOn) {
/*  898 */         Trace.entry(this, "com.ibm.mq.ese.jmqi.Put1Hobj", "<init>()");
/*      */       }
/*  900 */       this.handle = counter.incrementAndGet();
/*  901 */       if (Trace.isOn) {
/*  902 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.Put1Hobj", "<init>()");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getIntegerHandle() {
/*  909 */       if (Trace.isOn) {
/*  910 */         Trace.data(this, "com.ibm.mq.ese.jmqi.Put1Hobj", "getIntegerHandle()", "getter", 
/*  911 */             Integer.valueOf(this.handle));
/*      */       }
/*  913 */       return this.handle;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public byte[] getAMSPolicy() {
/*  921 */       if (Trace.isOn) {
/*  922 */         Trace.data(this, "com.ibm.mq.ese.jmqi.Put1Hobj", "getAMSPolicy()", "getter", null);
/*      */       }
/*  924 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getAMSErrorQueue() {
/*  932 */       if (Trace.isOn) {
/*  933 */         Trace.data(this, "com.ibm.mq.ese.jmqi.Put1Hobj", "getAMSErrorQueue()", "getter", null);
/*      */       }
/*  935 */       return null;
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
/*      */   public void MQPUT1(Hconn hconn, MQOD objDesc, MQMD msgDesc, MQPMO putMsgOpts, int bufferLength, ByteBuffer buffer, Pint cc, Pint rc) {
/*  952 */     if (Trace.isOn) {
/*  953 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)", new Object[] { hconn, objDesc, msgDesc, putMsgOpts, 
/*      */             
/*  955 */             Integer.valueOf(bufferLength), buffer, cc, rc });
/*      */     }
/*      */     
/*  958 */     doPut1(false, hconn, objDesc, msgDesc, putMsgOpts, bufferLength, new ByteBuffer[] { buffer }, cc, rc);
/*      */     
/*  960 */     if (Trace.isOn) {
/*  961 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQPUT1(Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer,Pint,Pint)");
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
/*      */   public void jmqiPut1(Hconn hconn, MQOD objDesc, MQMD msgDesc, MQPMO putMsgOpts, ByteBuffer[] buffers, Pint cc, Pint rc) {
/*  979 */     if (Trace.isOn) {
/*  980 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)", new Object[] { hconn, objDesc, msgDesc, putMsgOpts, buffers, cc, rc });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  985 */     doPut1(true, hconn, objDesc, msgDesc, putMsgOpts, -1, buffers, cc, rc);
/*      */     
/*  987 */     if (Trace.isOn) {
/*  988 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiPut1(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Pint,Pint)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void doPut1(boolean useJmqiPut1, Hconn hconn, MQOD objDesc, MQMD msgDesc, MQPMO putMsgOpts, int bufferLength, ByteBuffer[] buffers, Pint cc, Pint rc) {
/*  996 */     if (Trace.isOn)
/*  997 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "doPut1(boolean,Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer [ ],Pint,Pint)", new Object[] {
/*      */             
/*  999 */             Boolean.valueOf(useJmqiPut1), hconn, objDesc, msgDesc, putMsgOpts, 
/* 1000 */             Integer.valueOf(bufferLength), buffers, cc, rc
/*      */           }); 
/* 1002 */     boolean doIntercept = interceptMqCalls(hconn);
/* 1003 */     if (doIntercept) {
/* 1004 */       Hobj tmpHobj = new Put1Hobj();
/* 1005 */       Phobj phobj = this.env.newPhobj();
/* 1006 */       boolean shouldClose = false;
/* 1007 */       boolean prevQopNone = false;
/*      */       
/*      */       try {
/* 1010 */         ConnectionContext ctxt = this.contextContainer.getContext(hconn);
/* 1011 */         if (ctxt == null) {
/* 1012 */           cc.x = 2;
/* 1013 */           rc.x = 2018;
/* 1014 */           if (Trace.isOn) {
/* 1015 */             Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "doPut1(boolean,Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer [ ],Pint,Pint)", 1);
/*      */           }
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 1021 */         if (objDesc == null) {
/* 1022 */           cc.x = 2;
/* 1023 */           rc.x = 2044;
/* 1024 */           if (Trace.isOn) {
/* 1025 */             Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "doPut1(boolean,Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer [ ],Pint,Pint)", 2);
/*      */           }
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 1032 */         if (objDesc.getObjectType() == 1 || objDesc
/* 1033 */           .getObjectType() == 1002 || objDesc
/* 1034 */           .getObjectType() == 1004 || objDesc
/* 1035 */           .getObjectType() == 1003 || objDesc
/* 1036 */           .getObjectType() == 1005) {
/*      */ 
/*      */           
/* 1039 */           if (objDesc.getObjectName() == null) {
/* 1040 */             cc.x = 2;
/* 1041 */             rc.x = 2152;
/* 1042 */             if (Trace.isOn) {
/* 1043 */               Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "doPut1(boolean,Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer [ ],Pint,Pint)", 3);
/*      */             }
/*      */             
/*      */             return;
/*      */           } 
/*      */           
/* 1049 */           String trimmedName = objDesc.getObjectName().trim();
/*      */ 
/*      */           
/* 1052 */           if (ctxt.isCachePut1Dests()) {
/* 1053 */             prevQopNone = ctxt.containsQopNoneMQPUT1(trimmedName);
/*      */           }
/*      */           
/* 1056 */           SmqiObject eseObject = this.openInterceptor.beforeMQOPEN(hconn, objDesc, 16, phobj, cc, rc);
/*      */           
/* 1058 */           if (cc.x == 2) {
/* 1059 */             if (Trace.isOn) {
/* 1060 */               Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "doPut1(boolean,Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer [ ],Pint,Pint)", 4);
/*      */             }
/*      */             
/*      */             return;
/*      */           } 
/*      */           
/* 1066 */           if (prevQopNone)
/*      */           {
/* 1068 */             eseObject.setSecPolicy(this.policyService.createNonePolicy(trimmedName));
/*      */           }
/* 1070 */           phobj.setHobj(tmpHobj);
/*      */           
/* 1072 */           this.openInterceptor.afterMQOPEN(eseObject, hconn, objDesc, 16, phobj, cc, rc);
/* 1073 */           if (cc.x == 2) {
/* 1074 */             if (Trace.isOn) {
/* 1075 */               Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "doPut1(boolean,Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer [ ],Pint,Pint)", 5);
/*      */             }
/*      */             
/*      */             return;
/*      */           } 
/* 1080 */           shouldClose = true;
/*      */           
/* 1082 */           if (ctxt.isCachePut1Dests() && !prevQopNone && eseObject
/* 1083 */             .getSecPolicy().getQop() == 0) {
/* 1084 */             ctxt.storeQopNoneMQPUT1(trimmedName);
/*      */           }
/*      */           
/* 1087 */           if (this.contextContainer.hasProtectedConn(hconn) == true && eseObject
/* 1088 */             .getSecPolicy().getQop() != 0) {
/* 1089 */             MQPutContext esePutData = this.putInterceptor.beforeJmqiPut(hconn, tmpHobj, msgDesc, putMsgOpts, buffers, cc, rc);
/*      */             
/* 1091 */             if (cc.x == 2) {
/* 1092 */               if (Trace.isOn) {
/* 1093 */                 Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "doPut1(boolean,Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer [ ],Pint,Pint)", 6);
/*      */               }
/*      */               
/*      */               return;
/*      */             } 
/*      */             
/* 1099 */             if (useJmqiPut1) {
/* 1100 */               ((JmqiSP)this.delegate).jmqiPut1(hconn, objDesc, msgDesc, putMsgOpts, esePutData
/* 1101 */                   .getBuffers(), cc, rc);
/*      */             } else {
/* 1103 */               this.delegate.MQPUT1(hconn, objDesc, msgDesc, putMsgOpts, esePutData.getBuffer().limit(), esePutData
/* 1104 */                   .getBuffer(), cc, rc);
/*      */             } 
/* 1106 */             if (cc.x == 2) {
/* 1107 */               if (Trace.isOn) {
/* 1108 */                 Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "doPut1(boolean,Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer [ ],Pint,Pint)", 7);
/*      */               }
/*      */               
/*      */               return;
/*      */             } 
/* 1113 */             this.putInterceptor.afterJmqiPut(esePutData, hconn, tmpHobj, msgDesc, putMsgOpts, buffers, cc, rc);
/*      */             
/* 1115 */             if (cc.x == 2) {
/* 1116 */               if (ctxt.isCachePut1Dests() && rc.x == 2063) {
/* 1117 */                 ctxt.removeQopNoneMQPUT1(trimmedName);
/*      */               }
/* 1119 */               if (Trace.isOn) {
/* 1120 */                 Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "doPut1(boolean,Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer [ ],Pint,Pint)", 8);
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*      */               return;
/*      */             } 
/* 1127 */           } else if (useJmqiPut1) {
/* 1128 */             ((JmqiSP)this.delegate).jmqiPut1(hconn, objDesc, msgDesc, putMsgOpts, buffers, cc, rc);
/*      */           } else {
/* 1130 */             this.delegate.MQPUT1(hconn, objDesc, msgDesc, putMsgOpts, bufferLength, buffers[0], cc, rc);
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/* 1136 */         else if (useJmqiPut1) {
/* 1137 */           ((JmqiSP)this.delegate).jmqiPut1(hconn, objDesc, msgDesc, putMsgOpts, buffers, cc, rc);
/*      */         } else {
/* 1139 */           this.delegate.MQPUT1(hconn, objDesc, msgDesc, putMsgOpts, bufferLength, buffers[0], cc, rc);
/*      */         }
/*      */       
/*      */       }
/*      */       finally {
/*      */         
/* 1145 */         if (Trace.isOn) {
/* 1146 */           Trace.finallyBlock(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "doPut1(boolean,Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer [ ],Pint,Pint)");
/*      */         }
/*      */         
/* 1149 */         if (shouldClose) {
/* 1150 */           int closeOptions = 0;
/* 1151 */           this.closeInterceptor.afterMQCLOSE(tmpHobj, hconn, phobj, closeOptions, cc, rc);
/*      */         }
/*      */       
/*      */       } 
/* 1155 */     } else if (useJmqiPut1) {
/* 1156 */       ((JmqiSP)this.delegate).jmqiPut1(hconn, objDesc, msgDesc, putMsgOpts, buffers, cc, rc);
/*      */     } else {
/* 1158 */       this.delegate.MQPUT1(hconn, objDesc, msgDesc, putMsgOpts, bufferLength, buffers[0], cc, rc);
/*      */     } 
/*      */     
/* 1161 */     if (Trace.isOn) {
/* 1162 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "doPut1(boolean,Hconn,MQOD,MQMD,MQPMO,int,ByteBuffer [ ],Pint,Pint)", 9);
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
/*      */   public void jmqiPut1WithTriplets(Hconn hconn, MQOD mqod, MQMD mqmd, MQPMO mqpmo, ByteBuffer[] buffer, Triplet[] triplets, Pint cc, Pint rc) {
/* 1175 */     if (Trace.isOn) {
/* 1176 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)", new Object[] { hconn, mqod, mqmd, mqpmo, buffer, triplets, cc, rc });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1181 */     boolean doIntercept = interceptMqCalls(hconn);
/* 1182 */     if (doIntercept) {
/* 1183 */       this.putInterceptor.beforeJmqiPut1WithTriplets(hconn, mqod, mqmd, mqpmo, buffer, triplets, cc, rc);
/*      */       
/* 1185 */       ((JmqiSP)this.delegate).jmqiPut1WithTriplets(hconn, mqod, mqmd, mqpmo, buffer, triplets, cc, rc);
/*      */       
/* 1187 */       this.putInterceptor.afterJmqiPut1WithTriplets(hconn, mqod, mqmd, mqpmo, buffer, triplets, cc, rc);
/*      */     } else {
/*      */       
/* 1190 */       ((JmqiSP)this.delegate).jmqiPut1WithTriplets(hconn, mqod, mqmd, mqpmo, buffer, triplets, cc, rc);
/*      */     } 
/*      */ 
/*      */     
/* 1194 */     if (Trace.isOn) {
/* 1195 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiPut1WithTriplets(Hconn,MQOD,MQMD,MQPMO,ByteBuffer [ ],Triplet [ ],Pint,Pint)");
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
/*      */   public void MQGET(Hconn hconn, Hobj hobj, MQMD msgDesc, MQGMO getMsgOpts, int bufferLength, ByteBuffer buffer, Pint dataLength, Pint cc, Pint rc) {
/* 1216 */     if (Trace.isOn) {
/* 1217 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, msgDesc, getMsgOpts, 
/*      */             
/* 1219 */             Integer.valueOf(bufferLength), buffer, dataLength, cc, rc });
/*      */     }
/* 1221 */     boolean doIntercept = interceptMqCalls(hconn);
/* 1222 */     if (!doIntercept || 
/* 1223 */       !this.contextContainer.hasProtectedConn(hconn)) {
/* 1224 */       this.delegate.MQGET(hconn, hobj, msgDesc, getMsgOpts, bufferLength, buffer, dataLength, cc, rc);
/*      */       
/* 1226 */       if (Trace.isOn) {
/* 1227 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1233 */     MQGetContext mqGetContext = this.getInterceptor.beforeMQGET(hconn, hobj, msgDesc, getMsgOpts, bufferLength, buffer, dataLength, cc, rc);
/*      */     
/* 1235 */     if (cc.x == 2) {
/* 1236 */       if (Trace.isOn) {
/* 1237 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1242 */     this.delegate.MQGET(hconn, hobj, msgDesc, getMsgOpts, bufferLength, buffer, dataLength, cc, rc);
/*      */     
/* 1244 */     if (cc.x == 2) {
/* 1245 */       if (Trace.isOn) {
/* 1246 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1251 */     this.getInterceptor.afterMQGET(mqGetContext, hconn, hobj, msgDesc, getMsgOpts, bufferLength, buffer, dataLength, cc, rc);
/*      */     
/* 1253 */     if (Trace.isOn) {
/* 1254 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQGET(Hconn,Hobj,MQMD,MQGMO,int,ByteBuffer,Pint,Pint,Pint)", 4);
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
/*      */   public void spiGet(Hconn hconn, Hobj hobj, MQMD mqmd, MQGMO mqgmo, SpiGetOptions spiOptions, int bufferLength, ByteBuffer buffer, Pint dataLength, Pint cc, Pint rc) {
/* 1273 */     if (Trace.isOn) {
/* 1274 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", new Object[] { hconn, hobj, mqmd, mqgmo, spiOptions, 
/*      */             
/* 1276 */             Integer.valueOf(bufferLength), buffer, dataLength, cc, rc });
/*      */     }
/*      */     
/* 1279 */     boolean doIntercept = interceptMqCalls(hconn);
/* 1280 */     if (!doIntercept || 
/* 1281 */       !this.contextContainer.hasProtectedConn(hconn)) {
/* 1282 */       ((JmqiSP)this.delegate).spiGet(hconn, hobj, mqmd, mqgmo, spiOptions, bufferLength, buffer, dataLength, cc, rc);
/*      */       
/* 1284 */       if (Trace.isOn) {
/* 1285 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1291 */     MQGetContext mqGetContext = this.getInterceptor.beforeSpiGet(hconn, hobj, mqmd, mqgmo, spiOptions, bufferLength, buffer, dataLength, cc, rc);
/*      */     
/* 1293 */     if (cc.x == 2) {
/* 1294 */       if (Trace.isOn) {
/* 1295 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1300 */     ((JmqiSP)this.delegate).spiGet(hconn, hobj, mqmd, mqgmo, spiOptions, bufferLength, buffer, dataLength, cc, rc);
/*      */     
/* 1302 */     if (cc.x == 2) {
/* 1303 */       if (Trace.isOn) {
/* 1304 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1309 */     this.getInterceptor.afterSpiGet(mqGetContext, hconn, hobj, mqmd, mqgmo, spiOptions, bufferLength, buffer, dataLength, cc, rc);
/*      */     
/* 1311 */     if (Trace.isOn) {
/* 1312 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiGet(Hconn,Hobj,MQMD,MQGMO,SpiGetOptions,int,ByteBuffer,Pint,Pint,Pint)", 4);
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
/*      */   public ByteBuffer jmqiGet(Hconn hconn, Hobj hobj, MQMD md, MQGMO gmo, int expectedMsgLength, int maxMsgLength, PbyteBuffer pByteBuffer, Pint msgTooSmallForBufferCount, Pint dataLength, Pint cc, Pint rc) {
/* 1331 */     if (Trace.isOn) {
/* 1332 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", new Object[] { hconn, hobj, md, gmo, 
/*      */             
/* 1334 */             Integer.valueOf(expectedMsgLength), Integer.valueOf(maxMsgLength), pByteBuffer, msgTooSmallForBufferCount, dataLength, cc, rc });
/*      */     }
/*      */     
/* 1337 */     boolean doIntercept = interceptMqCalls(hconn);
/* 1338 */     if (!doIntercept || 
/* 1339 */       !this.contextContainer.hasProtectedConn(hconn)) {
/*      */       
/* 1341 */       ByteBuffer traceRet1 = ((JmqiSP)this.delegate).jmqiGet(hconn, hobj, md, gmo, expectedMsgLength, maxMsgLength, pByteBuffer, msgTooSmallForBufferCount, dataLength, cc, rc);
/*      */ 
/*      */       
/* 1344 */       if (Trace.isOn) {
/* 1345 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", traceRet1, 1);
/*      */       }
/*      */ 
/*      */       
/* 1349 */       return traceRet1;
/*      */     } 
/*      */     
/* 1352 */     MQGetContext mqGetContext = this.getInterceptor.beforeJmqiGet(hconn, hobj, md, gmo, expectedMsgLength, maxMsgLength, pByteBuffer, msgTooSmallForBufferCount, dataLength, cc, rc);
/*      */ 
/*      */     
/* 1355 */     if (cc.x == 2) {
/* 1356 */       ByteBuffer traceRet2 = pByteBuffer.getBuffer();
/* 1357 */       if (Trace.isOn) {
/* 1358 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", traceRet2, 2);
/*      */       }
/*      */       
/* 1361 */       return traceRet2;
/*      */     } 
/* 1363 */     ByteBuffer jmqiGetResult = ((JmqiSP)this.delegate).jmqiGet(hconn, hobj, md, gmo, expectedMsgLength, maxMsgLength, pByteBuffer, msgTooSmallForBufferCount, dataLength, cc, rc);
/*      */ 
/*      */     
/* 1366 */     if (cc.x == 2) {
/* 1367 */       if (Trace.isOn) {
/* 1368 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", jmqiGetResult, 3);
/*      */       }
/*      */ 
/*      */       
/* 1372 */       return jmqiGetResult;
/*      */     } 
/* 1374 */     if (pByteBuffer != null && pByteBuffer.getBuffer() == null) {
/* 1375 */       pByteBuffer.setBuffer(jmqiGetResult);
/*      */     }
/* 1377 */     this.getInterceptor.afterJmqiGet(mqGetContext, hconn, hobj, md, gmo, expectedMsgLength, maxMsgLength, pByteBuffer, msgTooSmallForBufferCount, dataLength, cc, rc);
/*      */ 
/*      */     
/* 1380 */     ByteBuffer traceRet3 = pByteBuffer.getBuffer();
/* 1381 */     if (Trace.isOn) {
/* 1382 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "jmqiGet(Hconn,Hobj,MQMD,MQGMO,int,int,PbyteBuffer,Pint,Pint,Pint,Pint)", traceRet3, 4);
/*      */     }
/*      */ 
/*      */     
/* 1386 */     return traceRet3;
/*      */   }
/*      */   
/*      */   private boolean interceptMqCalls(QmgrSplCapability capability) {
/* 1390 */     if (Trace.isOn) {
/* 1391 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "interceptMqCalls(QmgrSplCapability)", new Object[] { capability });
/*      */     }
/*      */     
/* 1394 */     boolean doIntercept = false;
/* 1395 */     if (capability != null) {
/* 1396 */       doIntercept = (capability != QmgrSplCapability.NOT_SUPPORTED);
/*      */     }
/* 1398 */     if (Trace.isOn) {
/* 1399 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "interceptMqCalls(QmgrSplCapability)", 
/* 1400 */           Boolean.valueOf(doIntercept));
/*      */     }
/* 1402 */     return doIntercept;
/*      */   }
/*      */   
/*      */   private boolean interceptMqCalls(Hconn hconn) {
/* 1406 */     if (Trace.isOn) {
/* 1407 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "interceptMqCalls(Hconn)", new Object[] { hconn });
/*      */     }
/*      */     
/* 1410 */     boolean doIntercept = false;
/* 1411 */     ConnectionContext context = this.contextContainer.getContext(hconn);
/* 1412 */     if (context != null) {
/* 1413 */       doIntercept = interceptMqCalls(context.getSplCapability());
/*      */     }
/* 1415 */     if (Trace.isOn) {
/* 1416 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "interceptMqCalls(Hconn)", 
/* 1417 */           Boolean.valueOf(doIntercept));
/*      */     }
/* 1419 */     return doIntercept;
/*      */   }
/*      */   
/*      */   private boolean requestPolicy(Hconn hconn) {
/* 1423 */     if (Trace.isOn) {
/* 1424 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "requestPolicy(Hconn)", new Object[] { hconn });
/*      */     }
/*      */     
/* 1427 */     boolean requestPolicy = false;
/* 1428 */     ConnectionContext context = this.contextContainer.getContext(hconn);
/* 1429 */     if (context != null) {
/* 1430 */       requestPolicy = (context.getSplCapability() == QmgrSplCapability.SUPPORTED);
/*      */     }
/* 1432 */     if (Trace.isOn) {
/* 1433 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "requestPolicy(Hconn)", 
/* 1434 */           Boolean.valueOf(requestPolicy));
/*      */     }
/* 1436 */     return requestPolicy;
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
/*      */   public void MQCB(Hconn hconn, int operation, MQCBD callbackDesc, Hobj hobj, MQMD msgDesc, MQGMO getMsgOpts, Pint cc, Pint rc) {
/* 1452 */     if (Trace.isOn) {
/* 1453 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)", new Object[] { hconn, 
/*      */             
/* 1455 */             Integer.valueOf(operation), callbackDesc, hobj, msgDesc, getMsgOpts, cc, rc });
/*      */     }
/* 1457 */     boolean doIntercept = interceptMqCalls(hconn);
/* 1458 */     if (!doIntercept || 
/* 1459 */       !this.contextContainer.hasProtectedConn(hconn)) {
/* 1460 */       this.delegate.MQCB(hconn, operation, callbackDesc, hobj, msgDesc, getMsgOpts, cc, rc);
/*      */       
/* 1462 */       if (Trace.isOn) {
/* 1463 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)", 1);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1468 */     this.cbInterceptor.beforeMQCB(hconn, operation, callbackDesc, hobj, msgDesc, getMsgOpts, cc, rc);
/*      */ 
/*      */     
/* 1471 */     if (cc.x == 2) {
/* 1472 */       if (Trace.isOn) {
/* 1473 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)", 2);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 1478 */     this.delegate.MQCB(hconn, operation, callbackDesc, hobj, msgDesc, getMsgOpts, cc, rc);
/*      */     
/* 1480 */     if (cc.x == 2) {
/* 1481 */       if (Trace.isOn) {
/* 1482 */         Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)", 3);
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/*      */     try {
/* 1488 */       this.cbInterceptor.afterMQCB(hconn, operation, callbackDesc, hobj, msgDesc, getMsgOpts, cc, rc);
/*      */     
/*      */     }
/* 1491 */     catch (EseMQException e) {
/* 1492 */       if (Trace.isOn) {
/* 1493 */         Trace.catchBlock(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)", (Throwable)e);
/*      */       }
/*      */       
/* 1496 */       this.delegate.MQCB(hconn, 512, callbackDesc, hobj, msgDesc, getMsgOpts, cc, rc);
/*      */     } 
/*      */     
/* 1499 */     if (Trace.isOn) {
/* 1500 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQCB(Hconn,int,MQCBD,Hobj,MQMD,MQGMO,Pint,Pint)", 4);
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
/*      */   public void spiSubscribe(Hconn hconn, LpiSD plpiSD, MQSD subDesc, Phobj hobj, Phobj hsub, Pint compCode, Pint reason) {
/* 1520 */     if (Trace.isOn) {
/* 1521 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)", new Object[] { hconn, plpiSD, subDesc, hobj, hsub, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/* 1525 */     ((JmqiSP)this.delegate).spiSubscribe(hconn, plpiSD, subDesc, hobj, hsub, compCode, reason);
/*      */     
/* 1527 */     boolean doIntercept = interceptMqCalls(hconn);
/* 1528 */     if (doIntercept) {
/* 1529 */       this.subscribeInterceptor.afterSpiSubscribe(hconn, plpiSD, subDesc, hobj, hsub, compCode, reason);
/*      */     }
/*      */     
/* 1532 */     if (Trace.isOn) {
/* 1533 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "spiSubscribe(Hconn,LpiSD,MQSD,Phobj,Phobj,Pint,Pint)");
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
/*      */   public void MQSUB(Hconn hconn, MQSD subDesc, Phobj hobj, Phobj hsub, Pint compCode, Pint reason) {
/* 1550 */     if (Trace.isOn) {
/* 1551 */       Trace.entry(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)", new Object[] { hconn, subDesc, hobj, hsub, compCode, reason });
/*      */     }
/*      */ 
/*      */     
/* 1555 */     this.delegate.MQSUB(hconn, subDesc, hobj, hsub, compCode, reason);
/* 1556 */     boolean doIntercept = interceptMqCalls(hconn);
/* 1557 */     if (doIntercept) {
/* 1558 */       this.subscribeInterceptor.afterMQSUB(hconn, subDesc, hobj, hsub, compCode, reason);
/*      */     }
/*      */     
/* 1561 */     if (Trace.isOn)
/* 1562 */       Trace.exit(this, "com.ibm.mq.ese.jmqi.InterceptedJmqiImpl", "MQSUB(Hconn,MQSD,Phobj,Phobj,Pint,Pint)"); 
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\jmqi\InterceptedJmqiImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */