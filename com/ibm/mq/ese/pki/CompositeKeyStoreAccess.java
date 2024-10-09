/*     */ package com.ibm.mq.ese.pki;
/*     */ 
/*     */ import com.ibm.mq.ese.core.AMBIException;
/*     */ import com.ibm.mq.ese.core.KeyStoreAccess;
/*     */ import com.ibm.mq.ese.core.Lifecycle;
/*     */ import com.ibm.mq.ese.core.X500NameWrapper;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.mq.ese.util.TraceUtil;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.security.KeyStore;
/*     */ import java.security.PrivateKey;
/*     */ import java.security.cert.Certificate;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
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
/*     */ public class CompositeKeyStoreAccess
/*     */   implements KeyStoreAccess, Lifecycle
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/CompositeKeyStoreAccess.java";
/*     */   private KeyStoreAccess primary;
/*     */   private KeyStoreAccess secondary;
/*     */   
/*     */   static {
/*  50 */     if (Trace.isOn) {
/*  51 */       Trace.data("com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/CompositeKeyStoreAccess.java");
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
/*     */   public CompositeKeyStoreAccess(KeyStoreAccess primary, KeyStoreAccess secondary) {
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.entry(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "<init>(KeyStoreAccess,KeyStoreAccess)", new Object[] { primary, secondary });
/*     */     }
/*     */ 
/*     */     
/*  84 */     if (primary == null) {
/*  85 */       IllegalArgumentException traceRet1 = new IllegalArgumentException("primary is null");
/*  86 */       if (Trace.isOn) {
/*  87 */         Trace.throwing(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "<init>(KeyStoreAccess,KeyStoreAccess)", traceRet1, 1);
/*     */       }
/*     */       
/*  90 */       throw traceRet1;
/*     */     } 
/*  92 */     if (secondary == null) {
/*  93 */       IllegalArgumentException traceRet2 = new IllegalArgumentException("secondary is null");
/*  94 */       if (Trace.isOn) {
/*  95 */         Trace.throwing(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "<init>(KeyStoreAccess,KeyStoreAccess)", traceRet2, 2);
/*     */       }
/*     */       
/*  98 */       throw traceRet2;
/*     */     } 
/* 100 */     this.primary = primary;
/* 101 */     this.secondary = secondary;
/*     */     
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "<init>(KeyStoreAccess,KeyStoreAccess)");
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
/*     */   public X509Certificate getCertificate(String alias) throws AMBIException {
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getCertificate(String)", new Object[] { alias });
/*     */     }
/*     */ 
/*     */     
/* 125 */     X509Certificate ret = this.primary.getCertificate(alias);
/* 126 */     if (ret == null) {
/* 127 */       ret = this.secondary.getCertificate(alias);
/*     */     }
/*     */     
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getCertificate(String)", ret);
/*     */     }
/* 133 */     return ret;
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
/*     */   public PrivateKey getPrivateKey(String alias) throws AMBIException {
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.entry(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getPrivateKey(String)", new Object[] { alias });
/*     */     }
/*     */ 
/*     */     
/* 150 */     PrivateKey privateKey = this.primary.getPrivateKey(alias);
/*     */     
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getPrivateKey(String)", privateKey);
/*     */     }
/*     */     
/* 156 */     return privateKey;
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
/*     */   public PrivateKey getPrivateKey(String alias, String pass) throws AMBIException {
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.entry(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getPrivateKey(String,String)", new Object[] { alias, pass });
/*     */     }
/*     */     
/* 174 */     PrivateKey privateKey = this.primary.getPrivateKey(alias, pass);
/*     */     
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getPrivateKey(String,String)", privateKey);
/*     */     }
/*     */     
/* 180 */     return privateKey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration<String> aliases() throws AMBIException {
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.entry(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "aliases()");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 197 */     final List<String> list = new LinkedList<>();
/* 198 */     Enumeration<String> e1 = this.primary.aliases();
/* 199 */     while (e1.hasMoreElements()) {
/* 200 */       list.add(e1.nextElement());
/*     */     }
/*     */     
/* 203 */     Enumeration<String> e2 = this.secondary.aliases();
/* 204 */     while (e2.hasMoreElements()) {
/* 205 */       String alias = e2.nextElement();
/* 206 */       if (!list.contains(alias)) {
/* 207 */         list.add(alias);
/*     */       }
/*     */     } 
/*     */     
/* 211 */     Enumeration<String> enumeration = new Enumeration<String>()
/*     */       {
/* 213 */         Iterator<String> it = list.iterator();
/*     */ 
/*     */         
/*     */         public boolean hasMoreElements() {
/* 217 */           return this.it.hasNext();
/*     */         }
/*     */ 
/*     */         
/*     */         public String nextElement() {
/* 222 */           return this.it.next();
/*     */         }
/*     */       };
/*     */ 
/*     */     
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "aliases()", enumeration);
/*     */     }
/* 230 */     return enumeration;
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
/*     */   public boolean containsAlias(String alias) throws AMBIException {
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.entry(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "containsAlias(String)", new Object[] { alias });
/*     */     }
/*     */ 
/*     */     
/* 247 */     boolean ret = this.primary.containsAlias(alias);
/* 248 */     if (!ret) {
/* 249 */       ret = this.secondary.containsAlias(alias);
/*     */     }
/*     */     
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "containsAlias(String)", 
/* 254 */           Boolean.valueOf(ret));
/*     */     }
/* 256 */     return ret;
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
/*     */   public Certificate[] getCertificateChain(String alias) throws AMBIException {
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.entry(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getCertificateChain(String)", new Object[] { alias });
/*     */     }
/*     */ 
/*     */     
/* 274 */     Certificate[] certificateChain = this.primary.getCertificateChain(alias);
/* 275 */     if (certificateChain == null || certificateChain.length == 0) {
/* 276 */       Certificate[] traceRet1 = this.secondary.getCertificateChain(alias);
/* 277 */       if (Trace.isOn) {
/* 278 */         Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getCertificateChain(String)", traceRet1, 1);
/*     */       }
/*     */       
/* 281 */       return traceRet1;
/*     */     } 
/*     */     
/* 284 */     if (Trace.isOn) {
/* 285 */       Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getCertificateChain(String)", certificateChain, 2);
/*     */     }
/*     */     
/* 288 */     return certificateChain;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 299 */     String type = this.primary.getType();
/*     */     
/* 301 */     if (Trace.isOn) {
/* 302 */       Trace.data(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getType()", "getter", type);
/*     */     }
/* 304 */     return type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCertificateEntry(String alias) throws AMBIException {
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.entry(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "isCertificateEntry(String)", new Object[] { alias });
/*     */     }
/*     */ 
/*     */     
/* 320 */     boolean isCertificate = (this.primary.isCertificateEntry(alias) || this.secondary.isCertificateEntry(alias));
/*     */     
/* 322 */     if (Trace.isOn) {
/* 323 */       Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "isCertificateEntry(String)", 
/* 324 */           Boolean.valueOf(isCertificate));
/*     */     }
/* 326 */     return isCertificate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isKeyEntry(String alias) throws AMBIException {
/* 336 */     if (Trace.isOn) {
/* 337 */       Trace.entry(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "isKeyEntry(String)", new Object[] { alias });
/*     */     }
/*     */ 
/*     */     
/* 341 */     boolean ret = this.primary.isKeyEntry(alias);
/*     */     
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "isKeyEntry(String)", 
/* 345 */           Boolean.valueOf(ret));
/*     */     }
/* 347 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public X509Certificate[] getCertificates(List<String> dns, boolean all) throws AMBIException {
/* 353 */     if (Trace.isOn) {
/* 354 */       Trace.entry(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getCertificates(List,boolean)", new Object[] { dns, 
/* 355 */             Boolean.valueOf(all) });
/*     */     }
/*     */     
/* 358 */     List<X509Certificate> ret = new LinkedList<>();
/*     */     
/* 360 */     if (dns == null || dns.size() == 0) {
/* 361 */       if (all) {
/* 362 */         HashMap<String, Object> inserts = new HashMap<>();
/* 363 */         inserts.put("AMS_INSERT_RECIPIENTS_NAMES", TraceUtil.objectsAsString(dns.toArray()));
/* 364 */         MissingCertificateException ex = new MissingCertificateException(AmsErrorMessages.mju_policy_failed_to_get_receiver_certs, inserts);
/* 365 */         if (Trace.isOn) {
/* 366 */           Trace.throwing(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getCertificates(List,boolean)", (Throwable)ex);
/*     */         }
/*     */         
/* 369 */         throw ex;
/*     */       } 
/* 371 */       X509Certificate[] traceRet1 = new X509Certificate[0];
/* 372 */       if (Trace.isOn) {
/* 373 */         Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getCertificates(List,boolean)", traceRet1, 1);
/*     */       }
/*     */       
/* 376 */       return traceRet1;
/*     */     } 
/*     */     
/* 379 */     int dnsSize = dns.size();
/* 380 */     X509Certificate[] ret1 = this.primary.getCertificates(dns, false);
/* 381 */     if (Trace.isOn) {
/*     */       
/* 383 */       String text = "primary keystore contains certificates: " + ((ret1 == null) ? "<null>" : String.valueOf(ret1.length));
/* 384 */       Trace.traceInfo(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getCertificates(List, boolean)", text, "");
/*     */     } 
/*     */     
/* 387 */     if (ret1 == null) {
/* 388 */       ret1 = new X509Certificate[0];
/*     */     }
/* 390 */     ret.addAll(Arrays.asList(ret1));
/* 391 */     if (ret1.length != dnsSize) {
/* 392 */       X509Certificate[] ret2 = this.secondary.getCertificates(dns, false);
/* 393 */       if (Trace.isOn) {
/*     */         
/* 395 */         String text = "secondary keystore contains certificates: " + ((ret2 == null) ? "<null>" : String.valueOf(ret2.length));
/* 396 */         Trace.traceInfo(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getCertificates(List, boolean)", text, "");
/*     */       } 
/*     */       
/* 399 */       for (int i = 0; i < ret2.length; i++) {
/* 400 */         if (!containsDN(ret, ret2[i])) {
/* 401 */           ret.add(ret2[i]);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 407 */     X509Certificate[] retarr = ret.<X509Certificate>toArray(new X509Certificate[ret.size()]);
/*     */     
/* 409 */     if (Trace.isOn) {
/* 410 */       Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getCertificates(List,boolean)", retarr, 2);
/*     */     }
/*     */     
/* 413 */     return retarr;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean containsDN(List<X509Certificate> certs, X509Certificate cert) throws AMBIException {
/* 418 */     if (Trace.isOn) {
/* 419 */       Trace.entry(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "containsDN(List,X509Certificate)", new Object[] { certs, cert });
/*     */     }
/*     */ 
/*     */     
/* 423 */     X500NameWrapper certName = new X500NameWrapper(cert.getSubjectX500Principal().getName());
/* 424 */     for (X509Certificate listCert : certs) {
/*     */       
/* 426 */       X500NameWrapper listCertName = new X500NameWrapper(listCert.getSubjectX500Principal().getName());
/* 427 */       if (certName.isEqual(listCertName)) {
/* 428 */         if (Trace.isOn) {
/* 429 */           Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "containsDN(List,X509Certificate)", 
/* 430 */               Boolean.valueOf(true), 1);
/*     */         }
/* 432 */         return true;
/*     */       } 
/*     */     } 
/* 435 */     if (Trace.isOn) {
/* 436 */       Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "containsDN(List,X509Certificate)", 
/* 437 */           Boolean.valueOf(false), 2);
/*     */     }
/* 439 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProvider() {
/* 447 */     String traceRet1 = this.primary.getProvider();
/* 448 */     if (Trace.isOn) {
/* 449 */       Trace.data(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getProvider()", "getter", traceRet1);
/*     */     }
/*     */     
/* 452 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() throws AMBIException {
/* 462 */     if (Trace.isOn) {
/* 463 */       Trace.entry(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "init()");
/*     */     }
/*     */     
/* 466 */     if (this.primary instanceof Lifecycle) {
/* 467 */       ((Lifecycle)this.primary).init();
/*     */     }
/* 469 */     if (this.secondary instanceof Lifecycle) {
/* 470 */       ((Lifecycle)this.secondary).init();
/*     */     }
/*     */     
/* 473 */     if (Trace.isOn) {
/* 474 */       Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "init()");
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
/*     */   public void cleanUp() throws AMBIException {
/* 486 */     if (Trace.isOn) {
/* 487 */       Trace.entry(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "cleanUp()");
/*     */     }
/* 489 */     if (this.primary instanceof Lifecycle) {
/* 490 */       ((Lifecycle)this.primary).cleanUp();
/*     */     }
/* 492 */     if (this.secondary instanceof Lifecycle) {
/* 493 */       ((Lifecycle)this.secondary).cleanUp();
/*     */     }
/* 495 */     if (Trace.isOn) {
/* 496 */       Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "cleanUp()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KeyStore getKeyStore() {
/* 506 */     if (Trace.isOn) {
/* 507 */       Trace.entry(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getKeyStore()");
/*     */     }
/* 509 */     KeyStore ret = this.primary.getKeyStore();
/* 510 */     if (ret == null) {
/* 511 */       ret = this.secondary.getKeyStore();
/*     */     }
/* 513 */     String traceRet1 = ret.toString();
/* 514 */     if (Trace.isOn) {
/* 515 */       Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getKeyStore()", traceRet1);
/*     */     }
/* 517 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KeyStore getPrimaryKeyStore() {
/* 524 */     if (Trace.isOn) {
/* 525 */       Trace.entry(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getPrimaryKeyStore()");
/*     */     }
/* 527 */     KeyStore ret = this.primary.getKeyStore();
/* 528 */     String traceRet1 = ret.toString();
/* 529 */     if (Trace.isOn) {
/* 530 */       Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getPrimaryKeyStore()", traceRet1);
/*     */     }
/* 532 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KeyStore getSecondaryKeyStore() {
/* 539 */     if (Trace.isOn) {
/* 540 */       Trace.entry(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getSecondaryKeyStore()");
/*     */     }
/* 542 */     KeyStore ret = this.secondary.getKeyStore();
/* 543 */     String traceRet1 = ret.toString();
/* 544 */     if (Trace.isOn) {
/* 545 */       Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getSecondaryKeyStore()", traceRet1);
/*     */     }
/* 547 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 557 */     if (Trace.isOn) {
/* 558 */       Trace.entry(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "toString()");
/*     */     }
/* 560 */     StringBuilder buffer = new StringBuilder();
/* 561 */     buffer.append("Primary keystore: ");
/* 562 */     if (this.primary != null) {
/* 563 */       buffer.append(this.primary.toString());
/*     */     } else {
/*     */       
/* 566 */       buffer.append("<null>");
/*     */     } 
/* 568 */     buffer.append(" / secondary keystore: ");
/* 569 */     if (this.secondary != null) {
/* 570 */       buffer.append(this.secondary.toString());
/*     */     } else {
/*     */       
/* 573 */       buffer.append("<null>");
/*     */     } 
/* 575 */     String traceRet1 = buffer.toString();
/* 576 */     if (Trace.isOn) {
/* 577 */       Trace.exit(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "toString()", traceRet1);
/*     */     }
/* 579 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KeyStoreAccess getPrimaryKeyStoreAccess() {
/* 586 */     if (Trace.isOn) {
/* 587 */       Trace.data(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getPrimaryKeyStoreAccess()", "getter", this.primary);
/*     */     }
/*     */     
/* 590 */     return this.primary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KeyStoreAccess getSecondaryKeyStoreAccess() {
/* 597 */     if (Trace.isOn) {
/* 598 */       Trace.data(this, "com.ibm.mq.ese.pki.CompositeKeyStoreAccess", "getSecondaryKeyStoreAccess()", "getter", this.secondary);
/*     */     }
/*     */     
/* 601 */     return this.secondary;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\pki\CompositeKeyStoreAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */