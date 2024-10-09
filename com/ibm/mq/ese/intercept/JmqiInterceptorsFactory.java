/*     */ package com.ibm.mq.ese.intercept;
/*     */ 
/*     */ import com.ibm.mq.ese.conv.CcsidConverter;
/*     */ import com.ibm.mq.ese.pki.CertAccess;
/*     */ import com.ibm.mq.ese.prot.MessageProtection;
/*     */ import com.ibm.mq.ese.service.EseMQService;
/*     */ import com.ibm.mq.ese.service.PolicyService;
/*     */ import com.ibm.mq.ese.service.UserMapService;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiMQ;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JmqiInterceptorsFactory
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiInterceptorsFactory.java";
/*     */   private EseMQService eseMQService;
/*     */   private UserMapService eseIdentityService;
/*     */   private MessageProtection cryptoService;
/*     */   private JmqiEnvironment env;
/*     */   private JmqiMQ jmqi;
/*     */   private CcsidConverter ccsidConverter;
/*     */   private PolicyService policyService;
/*     */   private CertAccess certAccess;
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/JmqiInterceptorsFactory.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiInterceptorsFactory(JmqiEnvironment env, JmqiMQ jmqi) {
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "<init>(JmqiEnvironment,JmqiMQ)", new Object[] { env, jmqi });
/*     */     }
/*     */     
/* 106 */     this.env = env;
/* 107 */     this.jmqi = jmqi;
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "<init>(JmqiEnvironment,JmqiMQ)");
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
/*     */   
/*     */   public JmqiConnInterceptor createConnInterceptor(JmqiContextContainer contextContainer) {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "createConnInterceptor(JmqiContextContainer)", new Object[] { contextContainer });
/*     */     }
/*     */     
/* 126 */     JmqiConnInterceptorImpl impl = new JmqiConnInterceptorImpl();
/* 127 */     setServices(impl);
/* 128 */     impl.setContextContainer(contextContainer);
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "createConnInterceptor(JmqiContextContainer)", impl);
/*     */     }
/*     */     
/* 133 */     return impl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiOpenInterceptor createOpenInterceptor(JmqiContextContainer contextContainer) {
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "createOpenInterceptor(JmqiContextContainer)", new Object[] { contextContainer });
/*     */     }
/*     */     
/* 147 */     JmqiOpenInterceptorImpl impl = new JmqiOpenInterceptorImpl();
/* 148 */     setServices(impl);
/* 149 */     impl.setContextContainer(contextContainer);
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "createOpenInterceptor(JmqiContextContainer)", impl);
/*     */     }
/*     */     
/* 154 */     return impl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiPutInterceptor createPutInterceptor(JmqiContextContainer contextContainer) {
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "createPutInterceptor(JmqiContextContainer)", new Object[] { contextContainer });
/*     */     }
/*     */     
/* 168 */     JmqiPutInterceptorImpl impl = new JmqiPutInterceptorImpl();
/* 169 */     setServices(impl);
/* 170 */     impl.setContextContainer(contextContainer);
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "createPutInterceptor(JmqiContextContainer)", impl);
/*     */     }
/*     */     
/* 175 */     return impl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiGetInterceptor createGetInterceptor(JmqiContextContainer contextContainer) {
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "createGetInterceptor(JmqiContextContainer)", new Object[] { contextContainer });
/*     */     }
/*     */     
/* 189 */     JmqiGetInterceptorImpl impl = new JmqiGetInterceptorImpl();
/* 190 */     setServices(impl);
/* 191 */     impl.setContextContainer(contextContainer);
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "createGetInterceptor(JmqiContextContainer)", impl);
/*     */     }
/*     */     
/* 196 */     return impl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiCBInterceptor createCBInterceptor(JmqiContextContainer contextContainer, JmqiGetInterceptor getInterceptor) {
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "createCBInterceptor(JmqiContextContainer,JmqiGetInterceptor)", new Object[] { contextContainer, getInterceptor });
/*     */     }
/*     */ 
/*     */     
/* 213 */     JmqiCBInterceptorImpl impl = new JmqiCBInterceptorImpl();
/* 214 */     setServices(impl);
/* 215 */     impl.setContextContainer(contextContainer);
/* 216 */     impl.setJmqiGetInterceptor(getInterceptor);
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "createCBInterceptor(JmqiContextContainer,JmqiGetInterceptor)", impl);
/*     */     }
/*     */     
/* 221 */     return impl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiCloseInterceptor createCloseInterceptor(JmqiContextContainer contextContainer) {
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "createCloseInterceptor(JmqiContextContainer)", new Object[] { contextContainer });
/*     */     }
/*     */     
/* 235 */     JmqiCloseInterceptorImpl impl = new JmqiCloseInterceptorImpl();
/* 236 */     setServices(impl);
/* 237 */     impl.setContextContainer(contextContainer);
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "createCloseInterceptor(JmqiContextContainer)", impl);
/*     */     }
/*     */     
/* 242 */     return impl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiDiscInterceptor createDiscInterceptor(JmqiContextContainer contextContainer) {
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "createDiscInterceptor(JmqiContextContainer)", new Object[] { contextContainer });
/*     */     }
/*     */     
/* 256 */     JmqiDiscInterceptorImpl impl = new JmqiDiscInterceptorImpl();
/* 257 */     setServices(impl);
/* 258 */     impl.setContextContainer(contextContainer);
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "createDiscInterceptor(JmqiContextContainer)", impl);
/*     */     }
/*     */     
/* 263 */     return impl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JmqiSubscribeInterceptor createSubscribeInterceptor(JmqiContextContainer contextContainer) {
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.entry(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "createSubscribeInterceptor(JmqiContextContainer)", new Object[] { contextContainer });
/*     */     }
/*     */     
/* 277 */     JmqiSubscribeInterceptorImpl impl = new JmqiSubscribeInterceptorImpl();
/* 278 */     setServices(impl);
/* 279 */     impl.setContextContainer(contextContainer);
/* 280 */     if (Trace.isOn) {
/* 281 */       Trace.exit(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "createSubscribeInterceptor(JmqiContextContainer)", impl);
/*     */     }
/*     */     
/* 284 */     return impl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setServices(AbstractJmqiInterceptor interceptor) {
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.data(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "setServices(AbstractJmqiInterceptor)", "setter", interceptor);
/*     */     }
/*     */     
/* 297 */     interceptor.setIdentityService(this.eseIdentityService);
/* 298 */     interceptor.setMQService(this.eseMQService);
/* 299 */     interceptor.setCryptoService(this.cryptoService);
/* 300 */     interceptor.setEnv(this.env);
/* 301 */     interceptor.setJmqi(this.jmqi);
/* 302 */     interceptor.setCcsidConverter(this.ccsidConverter);
/* 303 */     interceptor.setPolicyService(this.policyService);
/* 304 */     interceptor.setCertAccess(this.certAccess);
/*     */   }
/*     */   
/*     */   public EseMQService getEseMQService() {
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.data(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "getEseMQService()", "getter", this.eseMQService);
/*     */     }
/*     */     
/* 312 */     return this.eseMQService;
/*     */   }
/*     */   
/*     */   public void setEseMQService(EseMQService eseMQService) {
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.data(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "setEseMQService(EseMQService)", "setter", eseMQService);
/*     */     }
/*     */     
/* 320 */     this.eseMQService = eseMQService;
/*     */   }
/*     */   
/*     */   public UserMapService getEseIdentityService() {
/* 324 */     if (Trace.isOn) {
/* 325 */       Trace.data(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "getEseIdentityService()", "getter", this.eseIdentityService);
/*     */     }
/*     */     
/* 328 */     return this.eseIdentityService;
/*     */   }
/*     */   
/*     */   public void setEseIdentityService(UserMapService eseIdentityService) {
/* 332 */     if (Trace.isOn) {
/* 333 */       Trace.data(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "setEseIdentityService(UserMapService)", "setter", eseIdentityService);
/*     */     }
/*     */     
/* 336 */     this.eseIdentityService = eseIdentityService;
/*     */   }
/*     */   
/*     */   public void setCryptoService(MessageProtection cryptoService) {
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.data(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "setCryptoService(MessageProtection)", "setter", cryptoService);
/*     */     }
/*     */     
/* 344 */     this.cryptoService = cryptoService;
/*     */   }
/*     */   
/*     */   public void setCcsidConverter(CcsidConverter converter) {
/* 348 */     if (Trace.isOn) {
/* 349 */       Trace.data(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "setCcsidConverter(CcsidConverter)", "setter", converter);
/*     */     }
/*     */     
/* 352 */     this.ccsidConverter = converter;
/*     */   }
/*     */   
/*     */   public void setPolicyService(PolicyService policyService) {
/* 356 */     if (Trace.isOn) {
/* 357 */       Trace.data(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "setPolicyService(PolicyService)", "setter", policyService);
/*     */     }
/*     */     
/* 360 */     this.policyService = policyService;
/*     */   }
/*     */   
/*     */   public void setCertAccess(CertAccess certAccess) {
/* 364 */     if (Trace.isOn) {
/* 365 */       Trace.data(this, "com.ibm.mq.ese.intercept.JmqiInterceptorsFactory", "setCertAccess(CertAccess)", "setter", certAccess);
/*     */     }
/*     */     
/* 368 */     this.certAccess = certAccess;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\JmqiInterceptorsFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */