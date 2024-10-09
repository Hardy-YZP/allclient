/*     */ package com.ibm.mq.ese.pki;
/*     */ 
/*     */ import com.ibm.mq.ese.core.AMBIException;
/*     */ import com.ibm.mq.ese.core.KeyStoreAccess;
/*     */ import com.ibm.mq.ese.core.PkiSpec;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.security.InvalidAlgorithmParameterException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.cert.CRL;
/*     */ import java.security.cert.CRLException;
/*     */ import java.security.cert.CertStore;
/*     */ import java.security.cert.CertStoreException;
/*     */ import java.security.cert.LDAPCertStoreParameters;
/*     */ import java.security.cert.X509CRL;
/*     */ import java.security.cert.X509CRLSelector;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.bouncycastle.cert.X509CRLHolder;
/*     */ import org.bouncycastle.cert.jcajce.JcaX509CRLConverter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CertAccessImpl
/*     */   implements CertAccess
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/CertAccessImpl.java";
/*     */   private FileAccessor fileAccessor;
/*     */   private LdapAccessor ldapAccessor;
/*     */   
/*     */   static {
/*  69 */     if (Trace.isOn) {
/*  70 */       Trace.data("com.ibm.mq.ese.pki.CertAccessImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/CertAccessImpl.java");
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
/*     */   public CertAccessImpl() {
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry(this, "com.ibm.mq.ese.pki.CertAccessImpl", "<init>()");
/*     */     }
/*  90 */     this.fileAccessor = new FileAccessor();
/*  91 */     this.ldapAccessor = new LdapAccessor();
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.exit(this, "com.ibm.mq.ese.pki.CertAccessImpl", "<init>()");
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
/*     */   public X509Certificate[] loadCertificates(KeyStoreAccess ks, PkiSpec pki, List<String> dns) throws MissingCertificateException, CertAccessException {
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.entry(this, "com.ibm.mq.ese.pki.CertAccessImpl", "loadCertificates(KeyStoreAccess,PkiSpec,List)", new Object[] { ks, pki, dns });
/*     */     }
/*     */ 
/*     */     
/* 113 */     List<String> dnsCopy = new LinkedList<>(dns);
/* 114 */     X509Certificate[] certificates = null;
/*     */     try {
/* 116 */       certificates = ks.getCertificates(dnsCopy, true);
/*     */     }
/* 118 */     catch (MissingCertificateException e) {
/* 119 */       if (Trace.isOn) {
/* 120 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.CertAccessImpl", "loadCertificates(KeyStoreAccess,PkiSpec,List)", (Throwable)e, 1);
/*     */       }
/*     */       
/* 123 */       if (Trace.isOn) {
/* 124 */         Trace.throwing(this, "com.ibm.mq.ese.pki.CertAccessImpl", "loadCertificates(KeyStoreAccess,PkiSpec,List)", (Throwable)e, 1);
/*     */       }
/*     */       
/* 127 */       throw e;
/*     */     }
/* 129 */     catch (AMBIException e) {
/* 130 */       if (Trace.isOn) {
/* 131 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.CertAccessImpl", "loadCertificates(KeyStoreAccess,PkiSpec,List)", (Throwable)e, 2);
/*     */       }
/*     */       
/* 134 */       CertAccessException traceRet1 = new CertAccessException(e);
/* 135 */       if (Trace.isOn) {
/* 136 */         Trace.throwing(this, "com.ibm.mq.ese.pki.CertAccessImpl", "loadCertificates(KeyStoreAccess,PkiSpec,List)", (Throwable)traceRet1, 2);
/*     */       }
/*     */       
/* 139 */       throw traceRet1;
/*     */     } 
/* 141 */     if (certificates == null || certificates.length == 0) {
/* 142 */       MissingCertificateException ex = new MissingCertificateException(AmsErrorMessages.mjp_msg_error_getting_no_recipient_cert_MissingCertificateException);
/* 143 */       if (Trace.isOn) {
/* 144 */         Trace.throwing(this, "com.ibm.mq.ese.pki.CertAccessImpl", "loadCertificates(KeyStoreAccess,PkiSpec,List)", (Throwable)ex, 3);
/*     */       }
/*     */       
/* 147 */       throw ex;
/*     */     } 
/*     */     
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.exit(this, "com.ibm.mq.ese.pki.CertAccessImpl", "loadCertificates(KeyStoreAccess,PkiSpec,List)", certificates);
/*     */     }
/*     */     
/* 154 */     return certificates;
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
/*     */   public X509CRL[] loadCRLs(KeyStoreAccess ks, PkiSpec pki, X509Certificate[] certs) throws CrlAccessException {
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.entry(this, "com.ibm.mq.ese.pki.CertAccessImpl", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])", new Object[] { ks, pki, certs });
/*     */     }
/*     */ 
/*     */     
/* 171 */     Set<X509CRL> crls = null;
/* 172 */     if ((certs == null || certs.length == 0) && 
/* 173 */       Trace.isOn) {
/* 174 */       Trace.traceInfo(this, "com.ibm.mq.ese.pki.CertAccessImpl", "loadCRLs(KeyStoreAccess, PkiSpec, X509Certificate[])", "no certificates to search CRLs for", "");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 179 */     X509CRL[] fileCrls = new X509CRL[0];
/* 180 */     X509CRL[] ldapCrls = new X509CRL[0];
/* 181 */     if (this.fileAccessor != null) {
/* 182 */       fileCrls = this.fileAccessor.loadCRLs(ks, pki, certs);
/*     */     }
/* 184 */     if (this.ldapAccessor != null) {
/* 185 */       ldapCrls = this.ldapAccessor.loadCRLs(ks, pki, certs);
/*     */     }
/*     */ 
/*     */     
/* 189 */     crls = new HashSet<>(fileCrls.length + ldapCrls.length + 1);
/* 190 */     crls.addAll(Arrays.asList(fileCrls));
/* 191 */     crls.addAll(Arrays.asList(ldapCrls));
/*     */     
/* 193 */     X509CRL[] traceRet1 = crls.<X509CRL>toArray(new X509CRL[crls.size()]);
/* 194 */     if (Trace.isOn) {
/* 195 */       Trace.exit(this, "com.ibm.mq.ese.pki.CertAccessImpl", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])", traceRet1);
/*     */     }
/*     */     
/* 198 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class FileAccessor
/*     */     implements CertAccess
/*     */   {
/* 206 */     private JcaX509CRLConverter crlConverter = new JcaX509CRLConverter();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public X509Certificate[] loadCertificates(KeyStoreAccess ks, PkiSpec pki, List<String> dns) throws MissingCertificateException, CertAccessException {
/* 219 */       if (Trace.isOn) {
/* 220 */         Trace.entry(this, "com.ibm.mq.ese.pki.FileAccessor", "loadCertificates(KeyStoreAccess,PkiSpec,List)", new Object[] { ks, pki, dns });
/*     */       }
/*     */       
/* 223 */       if (Trace.isOn) {
/* 224 */         Trace.exit(this, "com.ibm.mq.ese.pki.FileAccessor", "loadCertificates(KeyStoreAccess,PkiSpec,List)", null);
/*     */       }
/*     */       
/* 227 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public X509CRL[] loadCRLs(KeyStoreAccess ks, PkiSpec pki, X509Certificate[] certs) throws CrlAccessException {
/* 240 */       if (Trace.isOn) {
/* 241 */         Trace.entry(this, "com.ibm.mq.ese.pki.FileAccessor", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])", new Object[] { ks, pki, certs });
/*     */       }
/*     */ 
/*     */       
/* 245 */       URI[] crlUris = pki.crlUris;
/* 246 */       String[] crlFiles = pki.crlFiles;
/*     */       
/* 248 */       FileInputStream fis = null;
/* 249 */       BufferedInputStream bis = null;
/* 250 */       List<X509CRL> crls = new LinkedList<>();
/* 251 */       List<File> files = new LinkedList<>();
/*     */       
/* 253 */       if (crlUris != null) {
/* 254 */         for (int i = 0; i < crlUris.length; i++) {
/* 255 */           URI uri = crlUris[i];
/* 256 */           if ("file".equalsIgnoreCase(uri.getScheme())) {
/* 257 */             files.add(new File(uri));
/*     */           
/*     */           }
/* 260 */           else if (Trace.isOn) {
/* 261 */             Trace.traceInfo(this, "com.ibm.mq.ese.pki.FileAccessor", "loadCRLs(KeyStoreAccess, PkiSpec, X509Certificate[])", "skipping non file URI: ", uri);
/*     */           } 
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 268 */       if (crlFiles != null) {
/* 269 */         for (int i = 0; i < crlFiles.length; i++) {
/* 270 */           files.add(new File(crlFiles[i]));
/*     */         }
/*     */       }
/*     */       
/* 274 */       String currentFile = null;
/*     */       try {
/* 276 */         for (File file : files) {
/* 277 */           currentFile = file.getAbsolutePath();
/* 278 */           if (Trace.isOn) {
/* 279 */             Trace.traceInfo(this, "com.ibm.mq.ese.pki.FileAccessor", "loadCRLs(KeyStoreAccess, PkiSpec, X509Certificate[])", "loading ", currentFile);
/*     */           }
/*     */ 
/*     */           
/* 283 */           fis = new FileInputStream(file);
/* 284 */           bis = new BufferedInputStream(fis);
/* 285 */           X509CRLHolder crlHolder = new X509CRLHolder(bis);
/* 286 */           X509CRL crl = this.crlConverter.getCRL(crlHolder);
/* 287 */           bis.close();
/* 288 */           bis = null;
/* 289 */           fis.close();
/* 290 */           fis = null;
/* 291 */           crls.add(crl);
/*     */         }
/*     */       
/* 294 */       } catch (IOException e) {
/* 295 */         if (Trace.isOn) {
/* 296 */           Trace.catchBlock(this, "com.ibm.mq.ese.pki.FileAccessor", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])", e, 1);
/*     */         }
/*     */         
/* 299 */         HashMap<String, Object> inserts = new HashMap<>();
/* 300 */         inserts.put("AMS_INSERT_CRL_NAME", currentFile);
/* 301 */         CrlAccessException ex = new CrlAccessException(AmsErrorMessages.mjp_certvalid_error_crl_not_found, inserts, e);
/* 302 */         if (Trace.isOn) {
/* 303 */           Trace.throwing(this, "com.ibm.mq.ese.pki.FileAccessor", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])", (Throwable)ex, 1);
/*     */         }
/*     */         
/* 306 */         throw ex;
/*     */       }
/* 308 */       catch (CRLException e) {
/* 309 */         if (Trace.isOn) {
/* 310 */           Trace.catchBlock(this, "com.ibm.mq.ese.pki.FileAccessor", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])", e, 2);
/*     */         }
/*     */         
/* 313 */         HashMap<String, Object> inserts = new HashMap<>();
/* 314 */         inserts.put("AMS_INSERT_CRL_NAME", currentFile);
/* 315 */         CrlAccessException ex = new CrlAccessException(AmsErrorMessages.mjp_certvalid_error_crl_not_found, inserts, e);
/* 316 */         if (Trace.isOn) {
/* 317 */           Trace.throwing(this, "com.ibm.mq.ese.pki.FileAccessor", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])", (Throwable)ex, 2);
/*     */         }
/*     */         
/* 320 */         throw ex;
/*     */       } finally {
/*     */         
/* 323 */         if (Trace.isOn) {
/* 324 */           Trace.finallyBlock(this, "com.ibm.mq.ese.pki.FileAccessor", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])");
/*     */         }
/*     */         
/* 327 */         if (bis != null) {
/*     */           try {
/* 329 */             bis.close();
/*     */           }
/* 331 */           catch (IOException nop) {
/* 332 */             if (Trace.isOn) {
/* 333 */               Trace.catchBlock(this, "com.ibm.mq.ese.pki.FileAccessor", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])", nop, 3);
/*     */             }
/*     */           } 
/*     */         }
/*     */         
/* 338 */         if (fis != null) {
/*     */           try {
/* 340 */             fis.close();
/*     */           }
/* 342 */           catch (IOException nop) {
/* 343 */             if (Trace.isOn) {
/* 344 */               Trace.catchBlock(this, "com.ibm.mq.ese.pki.FileAccessor", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])", nop, 4);
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 350 */       X509CRL[] traceRet1 = crls.<X509CRL>toArray(new X509CRL[crls.size()]);
/* 351 */       if (Trace.isOn) {
/* 352 */         Trace.exit(this, "com.ibm.mq.ese.pki.FileAccessor", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])", traceRet1);
/*     */       }
/*     */       
/* 355 */       return traceRet1;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean initialise() {
/* 360 */       return false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class LdapAccessor
/*     */     implements CertAccess
/*     */   {
/*     */     private CertStore store;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 379 */     private static final Object STORE_LOCK = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static final int MAX_RECONNECT_COUNT = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public X509Certificate[] loadCertificates(KeyStoreAccess ks, PkiSpec pki, List<String> dns) throws MissingCertificateException, CertAccessException {
/* 398 */       if (Trace.isOn) {
/* 399 */         Trace.entry(this, "com.ibm.mq.ese.pki.LdapAccessor", "loadCertificates(KeyStoreAccess,PkiSpec,List)", new Object[] { ks, pki, dns });
/*     */       }
/*     */       
/* 402 */       if (Trace.isOn) {
/* 403 */         Trace.exit(this, "com.ibm.mq.ese.pki.LdapAccessor", "loadCertificates(KeyStoreAccess,PkiSpec,List)", null);
/*     */       }
/*     */       
/* 406 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public X509CRL[] loadCRLs(KeyStoreAccess ks, PkiSpec pki, X509Certificate[] certs) throws CrlAccessException {
/* 420 */       if (Trace.isOn) {
/* 421 */         Trace.entry(this, "com.ibm.mq.ese.pki.LdapAccessor", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])", new Object[] { ks, pki, certs });
/*     */       }
/*     */ 
/*     */       
/* 425 */       CertStore localStore = initConnection(pki);
/* 426 */       int currentThreadReconnectCount = 0;
/* 427 */       while (localStore != null) {
/*     */         try {
/* 429 */           X509CRLSelector selector = new X509CRLSelector();
/* 430 */           Collection<String> issuerNames = new HashSet<>(certs.length);
/* 431 */           for (int i = 0; i < certs.length; i++) {
/* 432 */             X509Certificate cert = certs[i];
/* 433 */             issuerNames.add(cert.getIssuerX500Principal().getName());
/*     */           } 
/* 435 */           selector.setIssuerNames(issuerNames);
/* 436 */           Collection<CRL> crls = (Collection)localStore.getCRLs(selector);
/* 437 */           currentThreadReconnectCount = 0;
/* 438 */           if (Trace.isOn)
/* 439 */             Trace.exit(this, "com.ibm.mq.ese.pki.LdapAccessor", "loadCRLs(KeyStoreAccess, PkiSpec, X509Certificate[])", new Object[] {
/*     */                   
/* 441 */                   Integer.valueOf(crls.size())
/*     */                 }); 
/* 443 */           X509CRL[] traceRet1 = crls.<X509CRL>toArray(new X509CRL[crls.size()]);
/* 444 */           if (Trace.isOn) {
/* 445 */             Trace.exit(this, "com.ibm.mq.ese.pki.LdapAccessor", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])", traceRet1, 1);
/*     */           }
/*     */           
/* 448 */           return traceRet1;
/*     */         }
/* 450 */         catch (CertStoreException e) {
/* 451 */           if (Trace.isOn) {
/* 452 */             Trace.catchBlock(this, "com.ibm.mq.ese.pki.LdapAccessor", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])", e, 1);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 457 */           if (!(e.getCause() instanceof javax.naming.CommunicationException) || ++currentThreadReconnectCount >= 5) {
/* 458 */             CrlAccessException ex = new CrlAccessException(AmsErrorMessages.mjp_certvalid_error_crl_failed_to_retrieve, e);
/* 459 */             if (Trace.isOn) {
/* 460 */               Trace.throwing(this, "com.ibm.mq.ese.pki.LdapAccessor", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])", (Throwable)ex, 1);
/*     */             }
/*     */             
/* 463 */             throw ex;
/*     */           } 
/* 465 */           synchronized (STORE_LOCK) {
/* 466 */             this.store = localStore = null;
/* 467 */             localStore = initConnection(pki);
/*     */           }
/*     */         
/* 470 */         } catch (IOException e) {
/* 471 */           if (Trace.isOn) {
/* 472 */             Trace.catchBlock(this, "com.ibm.mq.ese.pki.LdapAccessor", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])", e, 2);
/*     */           }
/*     */           
/* 475 */           if (!(e.getCause() instanceof javax.naming.CommunicationException) || ++currentThreadReconnectCount >= 5) {
/* 476 */             CrlAccessException ex = new CrlAccessException(AmsErrorMessages.mjp_certvalid_error_crl_failed_to_retrieve, e);
/* 477 */             if (Trace.isOn) {
/* 478 */               Trace.throwing(this, "com.ibm.mq.ese.pki.LdapAccessor", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])", (Throwable)ex, 2);
/*     */             }
/*     */             
/* 481 */             throw ex;
/*     */           } 
/* 483 */           synchronized (STORE_LOCK) {
/* 484 */             this.store = localStore = null;
/* 485 */             localStore = initConnection(pki);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 490 */       X509CRL[] traceRet2 = new X509CRL[0];
/* 491 */       if (Trace.isOn) {
/* 492 */         Trace.exit(this, "com.ibm.mq.ese.pki.LdapAccessor", "loadCRLs(KeyStoreAccess,PkiSpec,X509Certificate [ ])", traceRet2, 2);
/*     */       }
/*     */       
/* 495 */       return traceRet2;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private CertStore initConnection(PkiSpec pki) throws CrlAccessException {
/* 506 */       if (Trace.isOn) {
/* 507 */         Trace.entry(this, "com.ibm.mq.ese.pki.LdapAccessor", "initConnection(PkiSpec)", new Object[] { pki });
/*     */       }
/*     */ 
/*     */       
/* 511 */       int index = 0;
/* 512 */       List<PkiSpec.ConnectionConfig> connections = pki.ldapConfig.connections;
/* 513 */       if (connections.size() < 1) {
/* 514 */         if (Trace.isOn) {
/* 515 */           Trace.traceInfo(this, "com.ibm.mq.ese.pki.LdapAccessor", "initConnection(PkiSpec)", "no LDAP configuration available", "");
/*     */           
/* 517 */           Trace.exit(this, "com.ibm.mq.ese.pki.LdapAccessor", "initConnection(PkiSpec)");
/*     */         } 
/*     */         
/* 520 */         if (Trace.isOn) {
/* 521 */           Trace.exit(this, "com.ibm.mq.ese.pki.LdapAccessor", "initConnection(PkiSpec)", null, 1);
/*     */         }
/* 523 */         return null;
/*     */       } 
/*     */       try {
/* 526 */         synchronized (STORE_LOCK) {
/* 527 */           while (this.store == null && index <= connections.size()) {
/*     */             
/* 529 */             String host = ((PkiSpec.ConnectionConfig)connections.get(index)).host;
/* 530 */             int port = ((PkiSpec.ConnectionConfig)connections.get(index)).portNum;
/* 531 */             if (Trace.isOn) {
/* 532 */               Trace.traceInfo(this, "com.ibm.mq.ese.pki.LdapAccessor", "initConnection(PkiSpec)", "using configuration: " + index + " " + host + " " + port, "");
/*     */             }
/*     */ 
/*     */             
/* 536 */             LDAPCertStoreParameters params = new LDAPCertStoreParameters(host, port);
/*     */             
/*     */             try {
/* 539 */               if (this.store == null) {
/* 540 */                 this.store = CertStore.getInstance("LDAP", params);
/*     */               }
/*     */             }
/* 543 */             catch (InvalidAlgorithmParameterException e) {
/* 544 */               if (Trace.isOn) {
/* 545 */                 Trace.catchBlock(this, "com.ibm.mq.ese.pki.LdapAccessor", "initConnection(PkiSpec)", e, 1);
/*     */               }
/* 547 */               Throwable cause = e.getCause();
/* 548 */               if (Trace.isOn) {
/* 549 */                 Trace.catchBlock(this, "com.ibm.mq.ese.pki.LdapAccessor", "initConnection(PkiSpec)", e);
/*     */               }
/*     */               
/* 552 */               if (!(cause instanceof javax.naming.CommunicationException)) {
/* 553 */                 if (Trace.isOn) {
/* 554 */                   Trace.throwing(this, "com.ibm.mq.ese.pki.LdapAccessor", "initConnection(PkiSpec)", e, 1);
/*     */                 }
/* 556 */                 throw e;
/*     */               } 
/* 558 */               if (++index >= connections.size()) {
/* 559 */                 CrlAccessException traceRet1 = (CrlAccessException)cause;
/* 560 */                 if (Trace.isOn) {
/* 561 */                   Trace.throwing(this, "com.ibm.mq.ese.pki.LdapAccessor", "initConnection(PkiSpec)", (Throwable)traceRet1, 2);
/*     */                 }
/*     */                 
/* 564 */                 throw traceRet1;
/*     */               } 
/*     */             } 
/*     */           } 
/* 568 */           if (Trace.isOn) {
/* 569 */             Trace.exit(this, "com.ibm.mq.ese.pki.LdapAccessor", "initConnection(PkiSpec)", this.store, 2);
/*     */           }
/* 571 */           return this.store;
/*     */         }
/*     */       
/* 574 */       } catch (InvalidAlgorithmParameterException e) {
/* 575 */         if (Trace.isOn) {
/* 576 */           Trace.catchBlock(this, "com.ibm.mq.ese.pki.LdapAccessor", "initConnection(PkiSpec)", e, 2);
/*     */         }
/* 578 */         CrlAccessException ex = new CrlAccessException(AmsErrorMessages.mjp_certvalid_error_crl_failed_to_retrieve, e);
/* 579 */         if (Trace.isOn) {
/* 580 */           Trace.throwing(this, "com.ibm.mq.ese.pki.LdapAccessor", "initConnection(PkiSpec)", (Throwable)ex, 3);
/*     */         }
/* 582 */         throw ex;
/*     */       }
/* 584 */       catch (NoSuchAlgorithmException e) {
/* 585 */         if (Trace.isOn) {
/* 586 */           Trace.catchBlock(this, "com.ibm.mq.ese.pki.LdapAccessor", "initConnection(PkiSpec)", e, 3);
/*     */         }
/* 588 */         CrlAccessException ex = new CrlAccessException(AmsErrorMessages.mjp_certvalid_error_crl_failed_to_retrieve, e);
/* 589 */         if (Trace.isOn) {
/* 590 */           Trace.throwing(this, "com.ibm.mq.ese.pki.LdapAccessor", "initConnection(PkiSpec)", (Throwable)ex, 4);
/*     */         }
/* 592 */         throw ex;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean initialise() {
/* 598 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setFileAccessor(FileAccessor fileAccessor) {
/* 603 */     if (Trace.isOn) {
/* 604 */       Trace.data(this, "com.ibm.mq.ese.pki.CertAccessImpl", "setFileAccessor(FileAccessor)", "setter", fileAccessor);
/*     */     }
/*     */     
/* 607 */     this.fileAccessor = fileAccessor;
/*     */   }
/*     */   
/*     */   public void setLdapAccessor(LdapAccessor ldapAccessor) {
/* 611 */     if (Trace.isOn) {
/* 612 */       Trace.data(this, "com.ibm.mq.ese.pki.CertAccessImpl", "setLdapAccessor(LdapAccessor)", "setter", ldapAccessor);
/*     */     }
/*     */     
/* 615 */     this.ldapAccessor = ldapAccessor;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean initialise() {
/* 620 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\pki\CertAccessImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */