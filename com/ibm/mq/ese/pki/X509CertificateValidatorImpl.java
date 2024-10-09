/*     */ package com.ibm.mq.ese.pki;
/*     */ 
/*     */ import com.ibm.mq.ese.core.AMBIException;
/*     */ import com.ibm.mq.ese.core.EseUser;
/*     */ import com.ibm.mq.ese.core.KeyStoreAccess;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.security.AccessController;
/*     */ import java.security.GeneralSecurityException;
/*     */ import java.security.InvalidAlgorithmParameterException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.NoSuchProviderException;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.security.Security;
/*     */ import java.security.cert.CertPath;
/*     */ import java.security.cert.CertPathBuilder;
/*     */ import java.security.cert.CertPathBuilderException;
/*     */ import java.security.cert.CertPathBuilderResult;
/*     */ import java.security.cert.CertPathValidator;
/*     */ import java.security.cert.CertPathValidatorException;
/*     */ import java.security.cert.CertStore;
/*     */ import java.security.cert.Certificate;
/*     */ import java.security.cert.CertificateExpiredException;
/*     */ import java.security.cert.CertificateNotYetValidException;
/*     */ import java.security.cert.CollectionCertStoreParameters;
/*     */ import java.security.cert.PKIXBuilderParameters;
/*     */ import java.security.cert.PKIXParameters;
/*     */ import java.security.cert.TrustAnchor;
/*     */ import java.security.cert.X509CRL;
/*     */ import java.security.cert.X509CertSelector;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class X509CertificateValidatorImpl
/*     */   implements X509CertificateValidator
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/X509CertificateValidatorImpl.java";
/*     */   private static final String CRLDP_EXTENSION_OID = "2.5.29.31";
/*     */   
/*     */   static {
/*  75 */     if (Trace.isOn) {
/*  76 */       Trace.data("com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/X509CertificateValidatorImpl.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   private static final String CLASS = X509CertificateValidatorImpl.class
/*  86 */     .getName();
/*     */ 
/*     */   
/*     */   private CertAccess certAccess;
/*     */ 
/*     */ 
/*     */   
/*     */   public X509CertificateValidatorImpl(CertAccess certAccess) {
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "<init>(CertAccess)", new Object[] { certAccess });
/*     */     }
/*     */     
/*  98 */     this.certAccess = certAccess;
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "<init>(CertAccess)");
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
/*     */   public void validateX509Certificate(X509Certificate cert, boolean[] keyUsage, int[] bitsToMatch, EseUser user) throws InvalidCertificateException {
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate,boolean [ ],int [ ],EseUser)", new Object[] { cert, keyUsage, bitsToMatch, user });
/*     */     }
/*     */ 
/*     */     
/* 119 */     validateX509Certificate(cert, keyUsage, bitsToMatch, true, user);
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate,boolean [ ],int [ ],EseUser)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void validateX509Certificate(X509Certificate cert, boolean[] keyUsage, int[] bitsToMatch, boolean allMustMatch, EseUser user) throws InvalidCertificateException {
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate,boolean [ ],int [ ],boolean,EseUser)", new Object[] { cert, keyUsage, bitsToMatch, 
/*     */             
/* 134 */             Boolean.valueOf(allMustMatch), user });
/*     */     }
/*     */     
/* 137 */     validateX509Certificate(cert, keyUsage, bitsToMatch, allMustMatch, true, user);
/*     */     
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate,boolean [ ],int [ ],boolean,EseUser)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void validateX509Certificate(X509Certificate cert, boolean[] keyUsage, int[] bitsToMatch, boolean allMustMatch, boolean checkRevocation, EseUser user) throws InvalidCertificateException {
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate,boolean [ ],int [ ],boolean,boolean,EseUser)", new Object[] { cert, keyUsage, bitsToMatch, 
/*     */             
/* 153 */             Boolean.valueOf(allMustMatch), Boolean.valueOf(checkRevocation), user });
/*     */     }
/*     */     
/* 156 */     if (cert == null) {
/* 157 */       IllegalArgumentException traceRet1 = new IllegalArgumentException("cert is null");
/* 158 */       if (Trace.isOn) {
/* 159 */         Trace.throwing(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate,boolean [ ],int [ ],boolean,boolean,EseUser)", traceRet1, 1);
/*     */       }
/*     */       
/* 162 */       throw traceRet1;
/*     */     } 
/* 164 */     if (user == null) {
/* 165 */       IllegalArgumentException traceRet2 = new IllegalArgumentException("user is null");
/* 166 */       if (Trace.isOn) {
/* 167 */         Trace.throwing(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate,boolean [ ],int [ ],boolean,boolean,EseUser)", traceRet2, 2);
/*     */       }
/*     */       
/* 170 */       throw traceRet2;
/*     */     } 
/* 172 */     String dn = cert.getSubjectX500Principal().getName();
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.traceInfo(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate, boolean[], int[], boolean, EseUser)", dn + "/" + cert
/*     */           
/* 176 */           .getSerialNumber().toString(), "");
/*     */     }
/* 178 */     KeyStoreAccess ks = user.getKeyStoreAccess();
/* 179 */     checkUsageBits(cert, keyUsage, bitsToMatch, allMustMatch);
/* 180 */     verifyCAConstraint(cert);
/*     */     
/* 182 */     Set<TrustAnchor> trustAnchors = new HashSet<>();
/*     */     
/* 184 */     List<Object> allCertificates = new LinkedList();
/*     */ 
/*     */     
/*     */     try {
/* 188 */       addTrustAnchorsAndCertificates(cert, ks, trustAnchors, allCertificates);
/*     */       
/* 190 */       CertPath certPath = buildCertPath(cert, trustAnchors, allCertificates);
/*     */       
/* 192 */       traceCertPath(certPath);
/* 193 */       TrustAnchor ta = getChainTrustAnchor(cert, trustAnchors, certPath);
/* 194 */       verifyValidityOfTrustAnchor(ta);
/* 195 */       List<? extends Certificate> c = certPath.getCertificates();
/*     */       
/* 197 */       X509Certificate[] arr = c.<X509Certificate>toArray(new X509Certificate[c.size()]);
/* 198 */       if (arr.length == 0 && isSelfSigned(cert)) {
/* 199 */         arr = new X509Certificate[] { cert };
/*     */       }
/*     */       
/* 202 */       X509CRL[] crls = this.certAccess.loadCRLs(user.getKeyStoreAccess(), user
/* 203 */           .getPkiSpec(), arr);
/* 204 */       if (crls.length > 0 && isSelfSigned(cert) && checkRevocation) {
/* 205 */         checkRevocationOfSelfSigned(cert, crls);
/*     */       }
/*     */       
/* 208 */       CertPathValidator certPathValidator = CertPathValidator.getInstance("PKIX", "BC");
/* 209 */       PKIXParameters certPathParameters = new PKIXParameters(trustAnchors);
/*     */       
/* 211 */       if (checkRevocation) {
/* 212 */         if ((crls != null && crls.length > 0) || isOCSPEnabled() || useCRLDPExtension(certPath, ta)) {
/* 213 */           certPathParameters.setRevocationEnabled(true);
/* 214 */           if (crls != null) {
/* 215 */             allCertificates.addAll(Arrays.asList((Object[])crls));
/*     */           }
/*     */         } else {
/*     */           
/* 219 */           certPathParameters.setRevocationEnabled(false);
/*     */         } 
/*     */       } else {
/*     */         
/* 223 */         certPathParameters.setRevocationEnabled(false);
/*     */       } 
/*     */       
/* 226 */       CollectionCertStoreParameters ccsp = new CollectionCertStoreParameters(allCertificates);
/*     */       
/* 228 */       CertStore store = CertStore.getInstance("Collection", ccsp);
/* 229 */       certPathParameters.addCertStore(store);
/* 230 */       certPathValidator.validate(certPath, certPathParameters);
/*     */     }
/* 232 */     catch (InvalidCertificateException e) {
/* 233 */       if (Trace.isOn) {
/* 234 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate,boolean [ ],int [ ],boolean,boolean,EseUser)", (Throwable)e, 1);
/*     */       }
/*     */       
/* 237 */       if (Trace.isOn) {
/* 238 */         Trace.throwing(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate,boolean [ ],int [ ],boolean,boolean,EseUser)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 241 */       throw e;
/*     */     }
/* 243 */     catch (CertPathValidatorException e) {
/* 244 */       if (Trace.isOn) {
/* 245 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate,boolean [ ],int [ ],boolean,boolean,EseUser)", e, 2);
/*     */       }
/*     */       
/* 248 */       throwCertPathValidatorException(cert, e);
/*     */     }
/* 250 */     catch (CrlAccessException e) {
/* 251 */       if (Trace.isOn) {
/* 252 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate,boolean [ ],int [ ],boolean,boolean,EseUser)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 255 */       HashMap<String, Object> inserts = new HashMap<>();
/* 256 */       inserts.put("AMS_INSERT_CERTIFICATE_SUBJECT", dn);
/* 257 */       InvalidCertificateException ex = new InvalidCertificateException(AmsErrorMessages.mjp_msg_error_failed_to_verify_cert_chain, inserts, (Throwable)e);
/* 258 */       if (Trace.isOn) {
/* 259 */         Trace.throwing(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate,boolean [ ],int [ ],boolean,boolean,EseUser)", (Throwable)ex, 4);
/*     */       }
/*     */       
/* 262 */       throw ex;
/*     */     }
/* 264 */     catch (GeneralSecurityException e) {
/* 265 */       if (Trace.isOn) {
/* 266 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate,boolean [ ],int [ ],boolean,boolean,EseUser)", e, 4);
/*     */       }
/*     */       
/* 269 */       HashMap<String, Object> inserts = new HashMap<>();
/* 270 */       inserts.put("AMS_INSERT_CERTIFICATE_SUBJECT", dn);
/* 271 */       InvalidCertificateException ex = new InvalidCertificateException(AmsErrorMessages.mjp_msg_error_failed_to_verify_cert_chain, inserts, e);
/* 272 */       if (Trace.isOn) {
/* 273 */         Trace.throwing(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate,boolean [ ],int [ ],boolean,boolean,EseUser)", (Throwable)ex, 5);
/*     */       }
/*     */       
/* 276 */       throw ex;
/*     */     }
/* 278 */     catch (AMBIException e) {
/* 279 */       if (Trace.isOn) {
/* 280 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate,boolean [ ],int [ ],boolean,boolean,EseUser)", (Throwable)e, 5);
/*     */       }
/*     */       
/* 283 */       HashMap<String, Object> inserts = new HashMap<>();
/* 284 */       inserts.put("AMS_INSERT_CERTIFICATE_SUBJECT", dn);
/* 285 */       InvalidCertificateException ex = new InvalidCertificateException(AmsErrorMessages.mjp_msg_error_failed_to_verify_cert_chain, inserts, (Throwable)e);
/* 286 */       if (Trace.isOn) {
/* 287 */         Trace.throwing(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate,boolean [ ],int [ ],boolean,boolean,EseUser)", (Throwable)ex, 6);
/*     */       }
/*     */       
/* 290 */       throw ex;
/*     */     } 
/*     */     
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "validateX509Certificate(X509Certificate,boolean [ ],int [ ],boolean,boolean,EseUser)");
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
/*     */   private boolean useCRLDPExtension(CertPath certPath, TrustAnchor ta) {
/* 309 */     if (Trace.isOn) {
/* 310 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "useCRLDPExtension(CertPath,TrustAnchor)", new Object[] { certPath, ta });
/*     */     }
/*     */ 
/*     */     
/* 314 */     String property = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*     */         {
/*     */           public String run()
/*     */           {
/* 318 */             if (Trace.isOn) {
/* 319 */               Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "run()");
/*     */             }
/*     */             
/* 322 */             String traceRet1 = System.getProperty("com.ibm.security.enableCRLDP");
/* 323 */             if (Trace.isOn) {
/* 324 */               Trace.exit(this, "com.ibm.mq.ese.pki.null", "run()", traceRet1);
/*     */             }
/* 326 */             return traceRet1;
/*     */           }
/*     */         });
/* 329 */     if (property == null || !property.equals("true")) {
/* 330 */       if (Trace.isOn) {
/* 331 */         Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "useCRLDPExtension(CertPath,TrustAnchor)", 
/* 332 */             Boolean.valueOf(false), 1);
/*     */       }
/* 334 */       return false;
/*     */     } 
/*     */     
/* 337 */     List<Certificate> certs = (List)certPath.getCertificates();
/* 338 */     for (Iterator<Certificate> iterator = certs.iterator(); iterator.hasNext(); ) {
/* 339 */       X509Certificate cert = (X509Certificate)iterator.next();
/* 340 */       if (hasCrlDpExtension(cert)) {
/* 341 */         if (Trace.isOn) {
/* 342 */           Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "useCRLDPExtension(CertPath,TrustAnchor)", 
/* 343 */               Boolean.valueOf(true), 2);
/*     */         }
/* 345 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 349 */     if (ta != null) {
/* 350 */       X509Certificate cert = ta.getTrustedCert();
/* 351 */       if (hasCrlDpExtension(cert)) {
/* 352 */         if (Trace.isOn) {
/* 353 */           Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "useCRLDPExtension(CertPath,TrustAnchor)", 
/* 354 */               Boolean.valueOf(true), 3);
/*     */         }
/* 356 */         return true;
/*     */       } 
/*     */     } 
/* 359 */     if (Trace.isOn) {
/* 360 */       Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "useCRLDPExtension(CertPath,TrustAnchor)", 
/* 361 */           Boolean.valueOf(false), 4);
/*     */     }
/* 363 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasCrlDpExtension(X509Certificate cert) {
/* 372 */     if (Trace.isOn) {
/* 373 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "hasCrlDpExtension(X509Certificate)", new Object[] { cert });
/*     */     }
/*     */ 
/*     */     
/* 377 */     byte[] cRLDistributionPoints = cert.getExtensionValue("2.5.29.31");
/* 378 */     boolean traceRet1 = (cRLDistributionPoints != null && cRLDistributionPoints.length > 0);
/* 379 */     if (Trace.isOn) {
/* 380 */       Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "hasCrlDpExtension(X509Certificate)", 
/* 381 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 383 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkRevocationOfSelfSigned(X509Certificate cert, X509CRL[] crls) throws InvalidCertificateException {
/* 393 */     if (Trace.isOn) {
/* 394 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "checkRevocationOfSelfSigned(X509Certificate,X509CRL [ ])", new Object[] { cert, crls });
/*     */     }
/*     */     
/* 397 */     for (int i = 0; i < crls.length; i++) {
/* 398 */       X509CRL x509crl = crls[i];
/* 399 */       if (x509crl.isRevoked(cert)) {
/* 400 */         HashMap<String, Object> inserts = new HashMap<>();
/* 401 */         inserts.put("AMS_INSERT_SUBJECT_NAME", cert.getSubjectX500Principal().getName());
/* 402 */         InvalidCertificateException ex = new InvalidCertificateException(AmsErrorMessages.mjp_certvalid_cert_revoked, inserts);
/* 403 */         if (Trace.isOn) {
/* 404 */           Trace.throwing(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "checkRevocationOfSelfSigned(X509Certificate,X509CRL [ ])", (Throwable)ex);
/*     */         }
/*     */         
/* 407 */         throw ex;
/*     */       } 
/*     */     } 
/* 410 */     if (Trace.isOn) {
/* 411 */       Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "checkRevocationOfSelfSigned(X509Certificate,X509CRL [ ])");
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
/*     */   private void verifyValidityOfTrustAnchor(TrustAnchor ta) throws CertificateExpiredException, CertificateNotYetValidException {
/* 429 */     if (Trace.isOn) {
/* 430 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "verifyValidityOfTrustAnchor(TrustAnchor)", new Object[] { ta });
/*     */     }
/*     */     
/* 433 */     String meth = "verifyValidityOfTrustAnchor";
/* 434 */     if (ta != null) {
/*     */       try {
/* 436 */         ta.getTrustedCert().checkValidity();
/*     */       }
/* 438 */       catch (CertificateExpiredException e) {
/* 439 */         if (Trace.isOn) {
/* 440 */           Trace.catchBlock(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "verifyValidityOfTrustAnchor(TrustAnchor)", e, 1);
/*     */         }
/*     */         
/* 443 */         HashMap<String, Object> inserts = new HashMap<>();
/* 444 */         inserts.put("AMS_INSERT_CERTIFICATE_SUBJECT", ta.getTrustedCert().getSubjectX500Principal().getName());
/* 445 */         AmsErrorMessages.log(CLASS, meth, AmsErrorMessages.mjp_certvalid_error_checking_cert_validity, inserts);
/* 446 */         if (Trace.isOn) {
/* 447 */           Trace.throwing(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "verifyValidityOfTrustAnchor(TrustAnchor)", e, 1);
/*     */         }
/*     */         
/* 450 */         throw e;
/*     */       }
/* 452 */       catch (CertificateNotYetValidException e) {
/* 453 */         if (Trace.isOn) {
/* 454 */           Trace.catchBlock(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "verifyValidityOfTrustAnchor(TrustAnchor)", e, 2);
/*     */         }
/*     */         
/* 457 */         if (Trace.isOn) {
/* 458 */           Trace.throwing(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "verifyValidityOfTrustAnchor(TrustAnchor)", e, 2);
/*     */         }
/*     */         
/* 461 */         throw e;
/*     */       } 
/*     */     }
/* 464 */     if (Trace.isOn) {
/* 465 */       Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "verifyValidityOfTrustAnchor(TrustAnchor)");
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
/*     */   private TrustAnchor getChainTrustAnchor(X509Certificate cert, Set<TrustAnchor> trustAnchors, CertPath certPath) {
/* 479 */     if (Trace.isOn) {
/* 480 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "getChainTrustAnchor(X509Certificate,Set,CertPath)", new Object[] { cert, trustAnchors, certPath });
/*     */     }
/*     */ 
/*     */     
/* 484 */     String taDN = null;
/* 485 */     int certPathLength = certPath.getCertificates().size();
/* 486 */     if (certPathLength == 0) {
/* 487 */       taDN = cert.getIssuerX500Principal().getName();
/*     */     }
/*     */     else {
/*     */       
/* 491 */       X509Certificate lastCert = (X509Certificate)certPath.getCertificates().get(certPathLength - 1);
/* 492 */       taDN = lastCert.getIssuerX500Principal().getName();
/*     */     } 
/*     */     
/* 495 */     for (TrustAnchor ta : trustAnchors) {
/*     */       
/* 497 */       if (ta.getTrustedCert().getSubjectX500Principal().getName().equals(taDN)) {
/* 498 */         if (Trace.isOn) {
/* 499 */           Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "getChainTrustAnchor(X509Certificate,Set,CertPath)", ta, 1);
/*     */         }
/*     */         
/* 502 */         return ta;
/*     */       } 
/*     */     } 
/* 505 */     if (Trace.isOn) {
/* 506 */       Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "getChainTrustAnchor(X509Certificate,Set,CertPath)", null, 2);
/*     */     }
/*     */     
/* 509 */     return null;
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
/*     */   private void verifyCAConstraint(X509Certificate cert) throws InvalidCertificateException {
/* 521 */     if (Trace.isOn) {
/* 522 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "verifyCAConstraint(X509Certificate)", new Object[] { cert });
/*     */     }
/*     */     
/* 525 */     if (cert.getBasicConstraints() != -1) {
/* 526 */       HashMap<String, Object> inserts = new HashMap<>();
/* 527 */       inserts.put("AMS_INSERT_SUBJECT_NAME", cert.getSubjectX500Principal().getName());
/* 528 */       InvalidCertificateException ex = new InvalidCertificateException(AmsErrorMessages.mjp_certvalid_ca_used_as_ee, inserts);
/* 529 */       if (Trace.isOn) {
/* 530 */         Trace.throwing(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "verifyCAConstraint(X509Certificate)", (Throwable)ex);
/*     */       }
/*     */       
/* 533 */       throw ex;
/*     */     } 
/* 535 */     if (Trace.isOn) {
/* 536 */       Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "verifyCAConstraint(X509Certificate)");
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
/*     */   private void throwCertPathValidatorException(X509Certificate cert, CertPathValidatorException e) throws InvalidCertificateException {
/* 549 */     if (Trace.isOn) {
/* 550 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "throwCertPathValidatorException(X509Certificate,CertPathValidatorException)", new Object[] { cert, e });
/*     */     }
/*     */ 
/*     */     
/* 554 */     Throwable cause = e.getCause();
/* 555 */     if (cause instanceof CertificateNotYetValidException) {
/* 556 */       HashMap<String, Object> hashMap = new HashMap<>();
/* 557 */       hashMap.put("AMS_INSERT_CERTIFICATE_SUBJECT", cert.getSubjectX500Principal().getName());
/* 558 */       hashMap.put("AMS_INSERT_DATE", cert.getNotBefore());
/* 559 */       InvalidCertificateException invalidCertificateException = new InvalidCertificateException(AmsErrorMessages.mjp_certvalid_error_cert_not_valid_yet, hashMap, e);
/* 560 */       if (Trace.isOn) {
/* 561 */         Trace.throwing(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "throwCertPathValidatorException(X509Certificate,CertPathValidatorException)", (Throwable)invalidCertificateException, 1);
/*     */       }
/*     */       
/* 564 */       throw invalidCertificateException;
/*     */     } 
/* 566 */     if (cause instanceof CertificateExpiredException) {
/* 567 */       HashMap<String, Object> hashMap = new HashMap<>();
/* 568 */       hashMap.put("AMS_INSERT_CERTIFICATE_SUBJECT", cert.getSubjectX500Principal().getName());
/* 569 */       hashMap.put("AMS_INSERT_DATE", cert.getNotAfter());
/* 570 */       InvalidCertificateException invalidCertificateException = new InvalidCertificateException(AmsErrorMessages.mjp_certvalid_error_cert_expired, hashMap, e);
/* 571 */       if (Trace.isOn) {
/* 572 */         Trace.throwing(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "throwCertPathValidatorException(X509Certificate,CertPathValidatorException)", (Throwable)invalidCertificateException, 2);
/*     */       }
/*     */       
/* 575 */       throw invalidCertificateException;
/*     */     } 
/* 577 */     if (cause instanceof java.security.SignatureException) {
/* 578 */       HashMap<String, Object> hashMap = new HashMap<>();
/* 579 */       hashMap.put("AMS_INSERT_ISSUER_NAME", cert.getIssuerX500Principal().getName());
/* 580 */       InvalidCertificateException invalidCertificateException = new InvalidCertificateException(AmsErrorMessages.mjp_msg_error_verify_crl_signature, hashMap, e);
/* 581 */       if (Trace.isOn) {
/* 582 */         Trace.throwing(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "throwCertPathValidatorException(X509Certificate,CertPathValidatorException)", (Throwable)invalidCertificateException, 3);
/*     */       }
/*     */       
/* 585 */       throw invalidCertificateException;
/*     */     } 
/*     */     
/* 588 */     HashMap<String, Object> inserts = new HashMap<>();
/* 589 */     if (cert == null) {
/* 590 */       inserts.put("AMS_INSERT_CERTIFICATE_SUBJECT", "<null>");
/*     */     } else {
/*     */       
/* 593 */       inserts.put("AMS_INSERT_CERTIFICATE_SUBJECT", cert.getSubjectX500Principal().getName());
/*     */     } 
/* 595 */     InvalidCertificateException ex = new InvalidCertificateException(AmsErrorMessages.mjp_msg_error_failed_to_verify_cert_chain, inserts, e);
/* 596 */     if (Trace.isOn) {
/* 597 */       Trace.throwing(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "throwCertPathValidatorException(X509Certificate,CertPathValidatorException)", (Throwable)ex, 4);
/*     */     }
/*     */     
/* 600 */     throw ex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isOCSPEnabled() {
/* 610 */     if (Trace.isOn) {
/* 611 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "isOCSPEnabled()");
/*     */     }
/*     */     
/* 614 */     Boolean enabled = AccessController.<Boolean>doPrivileged(new PrivilegedAction<Boolean>()
/*     */         {
/*     */           public Boolean run()
/*     */           {
/* 618 */             if (Trace.isOn) {
/* 619 */               Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "run()");
/*     */             }
/* 621 */             String property = Security.getProperty("ocsp.enable");
/* 622 */             if (property != null && property.equalsIgnoreCase("true")) {
/* 623 */               Boolean traceRet1 = Boolean.valueOf(true);
/* 624 */               if (Trace.isOn) {
/* 625 */                 Trace.exit(this, "com.ibm.mq.ese.pki.null", "run()", traceRet1, 1);
/*     */               }
/* 627 */               return traceRet1;
/*     */             } 
/* 629 */             Boolean traceRet2 = Boolean.FALSE;
/* 630 */             if (Trace.isOn) {
/* 631 */               Trace.exit(this, "com.ibm.mq.ese.pki.null", "run()", traceRet2, 2);
/*     */             }
/* 633 */             return traceRet2;
/*     */           }
/*     */         });
/* 636 */     boolean traceRet3 = enabled.booleanValue();
/* 637 */     if (Trace.isOn) {
/* 638 */       Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "isOCSPEnabled()", 
/* 639 */           Boolean.valueOf(traceRet3));
/*     */     }
/* 641 */     return traceRet3;
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
/*     */   private void addTrustAnchorsAndCertificates(X509Certificate cert, KeyStoreAccess ks, Set<TrustAnchor> trustAnchors, List<Object> allCertificates) throws AMBIException {
/* 658 */     if (Trace.isOn) {
/* 659 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "addTrustAnchorsAndCertificates(X509Certificate,KeyStoreAccess,Set,List)", new Object[] { cert, ks, trustAnchors, allCertificates });
/*     */     }
/*     */     
/* 662 */     allCertificates.add(cert);
/*     */     
/* 664 */     boolean certSelfSigned = isSelfSigned(cert);
/* 665 */     Enumeration<String> enumeration = ks.aliases();
/* 666 */     while (enumeration.hasMoreElements()) {
/* 667 */       String alias = enumeration.nextElement();
/* 668 */       X509Certificate c = ks.getCertificate(alias);
/* 669 */       if (c != null) {
/* 670 */         if (certSelfSigned && c.equals(cert)) {
/*     */           
/* 672 */           trustAnchors.add(new TrustAnchor(c, null)); continue;
/*     */         } 
/* 674 */         if (!c.equals(cert)) {
/*     */           
/* 676 */           if (ks.isCertificateEntry(alias) && isSelfSigned(c)) {
/* 677 */             trustAnchors.add(new TrustAnchor(c, null));
/*     */             continue;
/*     */           } 
/* 680 */           allCertificates.add(c);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 685 */     traceTAs(trustAnchors);
/* 686 */     if (Trace.isOn) {
/* 687 */       Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "addTrustAnchorsAndCertificates(X509Certificate,KeyStoreAccess,Set,List)");
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
/*     */   private void traceTAs(Set<TrustAnchor> trustAnchors) {
/* 699 */     if (Trace.isOn) {
/* 700 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "traceTAs(Set)", new Object[] { trustAnchors });
/*     */     }
/*     */     
/* 703 */     if (!Trace.isOn) {
/* 704 */       if (Trace.isOn) {
/* 705 */         Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "traceTAs(Set)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 710 */     for (TrustAnchor ta : trustAnchors) {
/* 711 */       Trace.data(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "traceTAs(Set)", ta
/* 712 */           .getTrustedCert().getSubjectX500Principal().getName(), null);
/*     */     }
/* 714 */     if (Trace.isOn) {
/* 715 */       Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "traceTAs(Set)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void traceCertPath(CertPath certPath) {
/* 726 */     if (Trace.isOn) {
/* 727 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "traceCertPath(CertPath)", new Object[] { certPath });
/*     */     }
/* 729 */     if (!Trace.isOn) {
/* 730 */       if (Trace.isOn) {
/* 731 */         Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "traceCertPath(CertPath)");
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 736 */     Iterator<? extends Certificate> certificates = certPath.getCertificates().iterator();
/* 737 */     while (certificates.hasNext()) {
/* 738 */       X509Certificate cert = (X509Certificate)certificates.next();
/* 739 */       Trace.data(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "traceTAs(Set)", cert
/* 740 */           .getSubjectX500Principal().getName(), null);
/*     */     } 
/* 742 */     if (Trace.isOn) {
/* 743 */       Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "traceCertPath(CertPath)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isSelfSigned(X509Certificate cert) {
/* 754 */     if (Trace.isOn) {
/* 755 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "isSelfSigned(X509Certificate)", new Object[] { cert });
/*     */     }
/*     */     
/* 758 */     boolean traceRet1 = cert.getIssuerX500Principal().equals(cert.getSubjectX500Principal());
/* 759 */     if (Trace.isOn) {
/* 760 */       Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "isSelfSigned(X509Certificate)", 
/* 761 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 763 */     return traceRet1;
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
/*     */   private CertPath buildCertPath(X509Certificate cert, Set<TrustAnchor> trustAnchors, List<Object> allCertificates) throws InvalidAlgorithmParameterException, CertPathBuilderException, NoSuchAlgorithmException {
/* 781 */     if (Trace.isOn) {
/* 782 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "buildCertPath(X509Certificate,Set,List)", new Object[] { cert, trustAnchors, allCertificates });
/*     */     }
/*     */ 
/*     */     
/* 786 */     CertPathBuilder certPathBuilder = null;
/*     */     
/*     */     try {
/* 789 */       certPathBuilder = CertPathBuilder.getInstance("PKIX", "BC");
/*     */     }
/* 791 */     catch (NoSuchProviderException e) {
/* 792 */       if (Trace.isOn) {
/* 793 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "buildCertPath(X509Certificate,Set,List)", e, 1);
/*     */       }
/*     */       
/* 796 */       return null;
/*     */     } 
/* 798 */     X509CertSelector certSelector = new X509CertSelector();
/* 799 */     certSelector.setCertificate(cert);
/*     */     
/* 801 */     PKIXBuilderParameters cpbp = new PKIXBuilderParameters(trustAnchors, certSelector);
/*     */ 
/*     */     
/* 804 */     cpbp.setRevocationEnabled(false);
/*     */     
/* 806 */     CollectionCertStoreParameters ccsp = new CollectionCertStoreParameters(allCertificates);
/*     */     
/* 808 */     CertStore store = CertStore.getInstance("Collection", ccsp);
/*     */     
/* 810 */     cpbp.addCertStore(store);
/*     */     
/* 812 */     CertPathBuilderResult certPathBuilderResult = certPathBuilder.build(cpbp);
/* 813 */     CertPath certPath = certPathBuilderResult.getCertPath();
/*     */     
/* 815 */     if (Trace.isOn) {
/* 816 */       Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "buildCertPath(X509Certificate,Set,List)", certPath);
/*     */     }
/*     */     
/* 819 */     return certPath;
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
/*     */   private void checkUsageBits(X509Certificate cert, boolean[] keyUsage, int[] bitsToMatch, boolean allMustMatch) throws InvalidCertificateException {
/* 837 */     if (Trace.isOn) {
/* 838 */       Trace.entry(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "checkUsageBits(X509Certificate,boolean [ ],int [ ],boolean)", new Object[] { cert, keyUsage, bitsToMatch, 
/*     */             
/* 840 */             Boolean.valueOf(allMustMatch) });
/*     */     }
/*     */     
/* 843 */     boolean[] keyUsageBits = cert.getKeyUsage();
/*     */ 
/*     */ 
/*     */     
/* 847 */     if (keyUsageBits == null || bitsToMatch == null || bitsToMatch.length == 0) {
/* 848 */       if (Trace.isOn) {
/* 849 */         Trace.traceInfo(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "checkUsageBits(X509Certificate, boolean[], int[], boolean)", "usage bits not set", "");
/*     */       }
/*     */ 
/*     */       
/* 853 */       if (Trace.isOn) {
/* 854 */         Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "checkUsageBits(X509Certificate,boolean [ ],int [ ],boolean)", 1);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 859 */     if (allMustMatch) {
/* 860 */       for (int i = 0; i < bitsToMatch.length; i++) {
/* 861 */         int index = bitsToMatch[i];
/* 862 */         if (keyUsageBits[index] != keyUsage[index]) {
/* 863 */           HashMap<String, Object> inserts = new HashMap<>();
/* 864 */           inserts.put("AMS_INSERT_CERTIFICATE_SUBJECT", cert.getSubjectX500Principal().getName());
/* 865 */           inserts.put("AMS_INSERT_USAGE_BIT_NAME", KEY_USAGE_NAMES[index]);
/* 866 */           inserts.put("AMS_INSERT_EXPECTED_USAGE_BIT_VALUE", Boolean.valueOf(keyUsage[index]));
/* 867 */           inserts.put("AMS_INSERT_USAGE_BIT_VALUE", Boolean.valueOf(keyUsageBits[index]));
/* 868 */           InvalidCertificateException ex = new InvalidCertificateException(AmsErrorMessages.mjp_certvalid_error_cert_keyusage_not_match, inserts);
/* 869 */           if (Trace.isOn) {
/* 870 */             Trace.throwing(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "checkUsageBits(X509Certificate,boolean [ ],int [ ],boolean)", (Throwable)ex, 1);
/*     */           }
/*     */           
/* 873 */           throw ex;
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 878 */       for (int i = 0; i < bitsToMatch.length; i++) {
/* 879 */         int index = bitsToMatch[i];
/* 880 */         if (keyUsageBits[index] == keyUsage[index]) {
/*     */           
/* 882 */           if (Trace.isOn) {
/* 883 */             Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "checkUsageBits(X509Certificate,boolean [ ],int [ ],boolean)", 2);
/*     */           }
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */       
/* 890 */       StringBuilder act = new StringBuilder();
/* 891 */       StringBuilder req = new StringBuilder();
/* 892 */       for (int cnt = 0; cnt < bitsToMatch.length; cnt++) {
/* 893 */         if (cnt != 0) {
/* 894 */           act.append(", ");
/* 895 */           req.append(", ");
/*     */         } 
/* 897 */         act.append(KEY_USAGE_NAMES[cnt] + "(" + (keyUsage[cnt] ? 1 : 0) + ")");
/* 898 */         req.append(KEY_USAGE_NAMES[cnt] + "(" + (keyUsageBits[cnt] ? 1 : 0) + ")");
/*     */       } 
/* 900 */       HashMap<String, Object> inserts = new HashMap<>();
/* 901 */       inserts.put("AMS_INSERT_SUBJECT_NAME", cert.getSubjectX500Principal().getName());
/* 902 */       inserts.put("AMS_INSERT_KEY_VALUE_LIST", act.toString());
/* 903 */       inserts.put("AMS_INSERT_EXPECTED_KEY_VALUE_LIST", req.toString());
/* 904 */       InvalidCertificateException ex = new InvalidCertificateException(AmsErrorMessages.mjp_certvalid_error_cert_no_keyusage_bit_matches, inserts);
/* 905 */       if (Trace.isOn) {
/* 906 */         Trace.throwing(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "checkUsageBits(X509Certificate,boolean [ ],int [ ],boolean)", (Throwable)ex, 2);
/*     */       }
/*     */       
/* 909 */       throw ex;
/*     */     } 
/*     */     
/* 912 */     if (Trace.isOn)
/* 913 */       Trace.exit(this, "com.ibm.mq.ese.pki.X509CertificateValidatorImpl", "checkUsageBits(X509Certificate,boolean [ ],int [ ],boolean)", 3); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\pki\X509CertificateValidatorImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */