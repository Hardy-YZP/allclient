/*     */ package com.ibm.mq.ese.service;
/*     */ 
/*     */ import com.ibm.mq.ese.conv.CcsidConverter;
/*     */ import com.ibm.mq.ese.conv.JmqiCcsidConverter;
/*     */ import com.ibm.mq.ese.pki.CertAccess;
/*     */ import com.ibm.mq.ese.pki.CertAccessWrapper;
/*     */ import com.ibm.mq.ese.pki.X509CertificateValidator;
/*     */ import com.ibm.mq.ese.prot.MessageProtection;
/*     */ import com.ibm.mq.ese.prot.MessageProtectionWrapper;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiMQ;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServicesFactoryImpl
/*     */   implements ServicesFactory
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/ServicesFactoryImpl.java";
/*     */   
/*     */   static {
/*  44 */     if (Trace.isOn) {
/*  45 */       Trace.data("com.ibm.mq.ese.service.ServicesFactoryImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/ServicesFactoryImpl.java");
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
/*     */   public EseMQService createMQService(JmqiMQ jmqi, JmqiEnvironment env) {
/*  59 */     if (Trace.isOn) {
/*  60 */       Trace.entry(this, "com.ibm.mq.ese.service.ServicesFactoryImpl", "createMQService(JmqiMQ,JmqiEnvironment)", new Object[] { jmqi, env });
/*     */     }
/*     */     
/*  63 */     EseMQService eseMQservice = new EseMQServiceImpl(jmqi, env);
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.exit(this, "com.ibm.mq.ese.service.ServicesFactoryImpl", "createMQService(JmqiMQ,JmqiEnvironment)", eseMQservice);
/*     */     }
/*     */     
/*  68 */     return eseMQservice;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserMapService createIdentityService() {
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.entry(this, "com.ibm.mq.ese.service.ServicesFactoryImpl", "createIdentityService()");
/*     */     }
/*  81 */     UserMapServiceImpl eseIdentityService = new UserMapServiceImpl();
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.exit(this, "com.ibm.mq.ese.service.ServicesFactoryImpl", "createIdentityService()", eseIdentityService);
/*     */     }
/*     */     
/*  86 */     return eseIdentityService;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageProtection createProtectionService(JmqiEnvironment env, X509CertificateValidator validator) {
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, "com.ibm.mq.ese.service.ServicesFactoryImpl", "createProtectionService(JmqiEnvironment,X509CertificateValidator)", new Object[] { env, validator });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 102 */     MessageProtectionWrapper messageProtectionWrapper = new MessageProtectionWrapper(validator, env);
/*     */     
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.exit(this, "com.ibm.mq.ese.service.ServicesFactoryImpl", "createProtectionService(JmqiEnvironment,X509CertificateValidator)", messageProtectionWrapper);
/*     */     }
/*     */ 
/*     */     
/* 109 */     return (MessageProtection)messageProtectionWrapper;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CcsidConverter createCcsidConverter(JmqiMQ jmqi, JmqiSystemEnvironment sysEnv) {
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.mq.ese.service.ServicesFactoryImpl", "createCcsidConverter(JmqiMQ,JmqiSystemEnvironment)", new Object[] { jmqi, sysEnv });
/*     */     }
/*     */     
/* 124 */     JmqiCcsidConverter ccsidConverter = new JmqiCcsidConverter(jmqi, sysEnv);
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.exit(this, "com.ibm.mq.ese.service.ServicesFactoryImpl", "createCcsidConverter(JmqiMQ,JmqiSystemEnvironment)", ccsidConverter);
/*     */     }
/*     */     
/* 129 */     return (CcsidConverter)ccsidConverter;
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
/*     */   public PolicyService createPolicyService(JmqiMQ jmqi, JmqiSystemEnvironment env, EseMQService eseMQService) {
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.entry(this, "com.ibm.mq.ese.service.ServicesFactoryImpl", "createPolicyService(JmqiMQ,JmqiSystemEnvironment,EseMQService)", new Object[] { jmqi, env, eseMQService });
/*     */     }
/*     */ 
/*     */     
/* 146 */     PolicyServiceImpl impl = new PolicyServiceImpl(jmqi, env, eseMQService);
/* 147 */     if (Trace.isOn) {
/* 148 */       Trace.exit(this, "com.ibm.mq.ese.service.ServicesFactoryImpl", "createPolicyService(JmqiMQ,JmqiSystemEnvironment,EseMQService)", impl);
/*     */     }
/*     */     
/* 151 */     return impl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CertAccess createCertAccess(JmqiEnvironment env) {
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.entry(this, "com.ibm.mq.ese.service.ServicesFactoryImpl", "createCertAccess(JmqiEnvironment)");
/*     */     }
/*     */     
/* 163 */     CertAccessWrapper certAccessWrapper = new CertAccessWrapper(env);
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.exit(this, "com.ibm.mq.ese.service.ServicesFactoryImpl", "createCertAccess(JmqiEnvironment)", certAccessWrapper);
/*     */     }
/*     */     
/* 168 */     return (CertAccess)certAccessWrapper;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\service\ServicesFactoryImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */