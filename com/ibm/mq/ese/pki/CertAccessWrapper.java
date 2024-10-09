/*     */ package com.ibm.mq.ese.pki;
/*     */ 
/*     */ import com.ibm.mq.ese.core.KeyStoreAccess;
/*     */ import com.ibm.mq.ese.core.PkiSpec;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.security.cert.X509CRL;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CertAccessWrapper
/*     */   implements CertAccess
/*     */ {
/*  41 */   private JmqiEnvironment env = null;
/*  42 */   private CertAccess delegate = null;
/*     */   
/*     */   public CertAccessWrapper(JmqiEnvironment env) {
/*  45 */     this.env = env;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public X509Certificate[] loadCertificates(KeyStoreAccess ks, PkiSpec pki, List<String> dns) throws MissingCertificateException, CertAccessException {
/*  51 */     if (!isInitialised())
/*     */     {
/*     */ 
/*     */       
/*  55 */       throw new CertAccessException(AmsErrorMessages.mjp_CertAccessImpl_not_loaded1);
/*     */     }
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.data(this, "com.ibm.mq.ese.pki.CertAccessWrapper", "loadCertificates(KeyStoreAccess,PkiSpec,List)", "Delegating call to: " + this.delegate
/*     */           
/*  60 */           .getClass().getCanonicalName(), null);
/*     */     }
/*  62 */     return this.delegate.loadCertificates(ks, pki, dns);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public X509CRL[] loadCRLs(KeyStoreAccess ks, PkiSpec pki, X509Certificate[] certs) throws CrlAccessException {
/*  68 */     if (!isInitialised()) {
/*  69 */       throw new CrlAccessException(AmsErrorMessages.mjp_CertAccessImpl_not_loaded2);
/*     */     }
/*  71 */     if (Trace.isOn) {
/*  72 */       Trace.data(this, "com.ibm.mq.ese.pki.CertAccessWrapper", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate[])", "Delegating call to: " + this.delegate
/*     */           
/*  74 */           .getClass().getCanonicalName(), null);
/*     */     }
/*  76 */     return this.delegate.loadCRLs(ks, pki, certs);
/*     */   }
/*     */   
/*     */   private boolean isInitialised() {
/*  80 */     return (this.delegate != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean initialise() throws JmqiException {
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.entry(this, "com.ibm.mq.ese.pki.CertAccessWrapper", "initialise()", null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  91 */     if (isInitialised()) {
/*  92 */       if (Trace.isOn) {
/*  93 */         Trace.exit(this, "com.ibm.mq.ese.pki.CertAccessWrapper", "initialise()", "Returning false. Certificate access service has already been initialised : " + this.delegate
/*     */ 
/*     */             
/*  96 */             .getClass().getCanonicalName(), 1);
/*     */       }
/*  98 */       return false;
/*     */     } 
/*     */     
/* 101 */     synchronized (this) {
/*     */ 
/*     */       
/* 104 */       if (isInitialised()) {
/* 105 */         if (Trace.isOn) {
/* 106 */           Trace.exit(this, "com.ibm.mq.ese.pki.CertAccessWrapper", "initialise()", "Returning false. Certificate access service has already been initialised : " + this.delegate
/*     */ 
/*     */               
/* 109 */               .getClass().getCanonicalName(), 2);
/*     */         }
/* 111 */         return false;
/*     */       } 
/*     */       
/*     */       try {
/* 115 */         Class<?> msgProtectionClass = JmqiTools.dynamicLoadClass(this.env, "com.ibm.mq.ese.pki.CertAccessImpl", getClass());
/* 116 */         Class<?>[] paramTypes = new Class[0];
/* 117 */         Constructor<?> constructor = msgProtectionClass.getConstructor(paramTypes);
/* 118 */         Object[] params = new Object[0];
/* 119 */         this.delegate = (CertAccess)constructor.newInstance(params);
/* 120 */         this.delegate.initialise();
/*     */       }
/* 122 */       catch (ClassNotFoundException|NoClassDefFoundError|SecurityException|NoSuchMethodException|IllegalArgumentException|InstantiationException|IllegalAccessException|java.lang.reflect.InvocationTargetException ex) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 132 */         if (Trace.isOn) {
/* 133 */           Trace.catchBlock(this, "com.ibm.mq.ese.pki.CertAccessWrapper", "initialise()", ex, 1);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 141 */         JmqiException traceRet1 = new JmqiException(this.env, 9546, new String[] { JmqiTools.getExSumm(ex), null, JmqiTools.getFailingCall(ex) }, 2, 2195, ex);
/*     */         
/* 143 */         if (Trace.isOn) {
/* 144 */           Trace.throwing(this, "com.ibm.mq.ese.pki.CertAccessWrapper", "initialise()", (Throwable)traceRet1, 1);
/*     */         }
/*     */         
/* 147 */         throw traceRet1;
/*     */       }
/* 149 */       catch (RuntimeException e) {
/* 150 */         if (Trace.isOn) {
/* 151 */           Trace.catchBlock(this, "com.ibm.mq.ese.pki.CertAccessWrapper", "initialise()", e, 2);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 158 */         Throwable cause = e.getCause();
/*     */ 
/*     */         
/* 161 */         if (cause instanceof ClassNotFoundException) {
/*     */           
/* 163 */           JmqiException jmqie = new JmqiException(this.env, 9546, new String[] { JmqiTools.getExSumm(e), null, JmqiTools.getFailingCall(e) }, 2, 2195, e);
/*     */           
/* 165 */           if (Trace.isOn) {
/* 166 */             Trace.throwing(this, "com.ibm.mq.ese.pki.CertAccessWrapper", "initialise()", (Throwable)jmqie, 2);
/*     */           }
/*     */           
/* 169 */           throw jmqie;
/*     */         } 
/*     */ 
/*     */         
/* 173 */         if (Trace.isOn) {
/* 174 */           Trace.throwing(this, "com.ibm.mq.ese.pki.CertAccessWrapper", "initialise()", e, 3);
/*     */         }
/*     */         
/* 177 */         throw e;
/*     */       } 
/*     */     } 
/*     */     
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.exit(this, "com.ibm.mq.ese.pki.CertAccessWrapper", "initialise()", "Returning true as the certificate access service has now been initialised: " + this.delegate
/*     */ 
/*     */           
/* 185 */           .getClass().getCanonicalName());
/*     */     }
/* 187 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\pki\CertAccessWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */