/*      */ package com.ibm.mq.ese.pki;
/*      */ 
/*      */ import com.ibm.mq.ese.config.ConfigException;
/*      */ import com.ibm.mq.ese.config.KeyStoreConfig;
/*      */ import com.ibm.mq.ese.config.PasswordObject;
/*      */ import com.ibm.mq.ese.core.AMBIException;
/*      */ import com.ibm.mq.ese.core.KeyStoreAccess;
/*      */ import com.ibm.mq.ese.core.X500NameWrapper;
/*      */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*      */ import com.ibm.mq.ese.util.TraceUtil;
/*      */ import com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil;
/*      */ import com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract;
/*      */ import com.ibm.msg.client.commonservices.passwordprotection.PBEException;
/*      */ import com.ibm.msg.client.commonservices.passwordprotection.passwordencodings.EncodedPasswordV0;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.File;
/*      */ import java.security.InvalidKeyException;
/*      */ import java.security.KeyFactory;
/*      */ import java.security.KeyStore;
/*      */ import java.security.KeyStoreException;
/*      */ import java.security.PrivateKey;
/*      */ import java.security.PublicKey;
/*      */ import java.security.cert.Certificate;
/*      */ import java.security.cert.X509Certificate;
/*      */ import java.security.interfaces.RSAPrivateKey;
/*      */ import java.security.interfaces.RSAPublicKey;
/*      */ import java.security.spec.RSAPrivateKeySpec;
/*      */ import java.security.spec.RSAPublicKeySpec;
/*      */ import java.util.Arrays;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import javax.crypto.Cipher;
/*      */ import org.bouncycastle.util.encoders.Base64;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class AbstractKeyStoreAccess
/*      */   implements KeyStoreAccess
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/AbstractKeyStoreAccess.java";
/*      */   private static final String INITIALKEY_ENVVAR = "MQS_AMSCRED_KEYFILE";
/*      */   public static final String EYECATCHER = "<AMS>";
/*      */   private static char[] initialkey;
/*      */   
/*      */   static {
/*   73 */     if (Trace.isOn) {
/*   74 */       Trace.data("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/AbstractKeyStoreAccess.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean defaultInitialKeyUsed = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String initalkeyfilepath;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   93 */   private static final byte[] FIXEDSALT = new byte[] { 24, -47, 1, -69, -28, -127, -111, 42, -100, 81, 21, 87, -2, 104, 57, -82, -49, 82, 12, -19, -74, 49, 16, -88 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  102 */   private static final byte[] AMS_DEFAULT_KEY = new byte[] { -120, 68, 53, -88, 118, -59, -27, 52, -86, 86, 43, -51, -92, -89, 61, 30, -79, -62, -27, 50, 31, -4, 89, 21, -108, -115, -37, -47, -18, -27, 91, -51 };
/*      */   
/*  104 */   private static final byte[] AMS_KEY_MASK = new byte[] { -65, 118, -92, -86, -26, -11, -4, -63, 123, 45, -85, 107, 19, -83, -127, -56, 104, Byte.MAX_VALUE, -33, -114, -81, 95, 63, -109, 94, -87, -122, 30, -58, 119, 28, -22 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  110 */   protected KeyStore ks = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  115 */   protected PasswordObject keyStorePassword = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  120 */   protected String keyStoreFile = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  125 */   protected PasswordObject pkeyPassword = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  130 */   protected String keyStoreType = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  135 */   protected String keyStoreProvider = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  140 */   protected String credentialAlias = null;
/*      */   
/*      */   public AbstractKeyStoreAccess(KeyStoreConfig keyStoreConfig) {
/*  143 */     if (Trace.isOn) {
/*  144 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "AbstractKeyStoreAccess(KeyStoreConfig)");
/*      */     }
/*      */ 
/*      */     
/*  148 */     this.keyStoreType = keyStoreConfig.getType();
/*  149 */     this.keyStoreProvider = keyStoreConfig.getProvider();
/*  150 */     this.credentialAlias = keyStoreConfig.getAlias();
/*  151 */     this.keyStoreFile = keyStoreConfig.getKeyStorePath();
/*      */     
/*  153 */     if (Trace.isOn) {
/*  154 */       Trace.traceData(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "AbstractKeyStoreAccess(KeyStoreConfig)", "keyStoreFile: ", this.keyStoreFile);
/*      */ 
/*      */       
/*  157 */       Trace.traceData(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "AbstractKeyStoreAccess(KeyStoreConfig)", "keyStoreType: ", this.keyStoreType);
/*      */ 
/*      */       
/*  160 */       Trace.traceData(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "AbstractKeyStoreAccess(KeyStoreConfig)", "keyStoreProvider: ", this.keyStoreProvider);
/*      */ 
/*      */       
/*  163 */       Trace.traceData(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "AbstractKeyStoreAccess(KeyStoreConfig)", "credentialAlias: ", this.credentialAlias);
/*      */ 
/*      */       
/*  166 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "AbstractKeyStoreAccess(KeyStoreConfig)");
/*      */     } 
/*      */     
/*  169 */     if (Trace.isOn) {
/*  170 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "<init>(KeyStoreConfig)");
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
/*      */   public static void loadInitialKey(String configfilevalue) throws ConfigException {
/*  186 */     if (Trace.isOn) {
/*  187 */       Trace.entry("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "loadInitialKey(String)", new Object[] { configfilevalue });
/*      */     }
/*      */ 
/*      */     
/*  191 */     String EnvfileLocation = System.getProperty("MQS_AMSCRED_KEYFILE");
/*  192 */     if (EnvfileLocation != null) {
/*  193 */       if (Trace.isOn) {
/*  194 */         Trace.data("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "loadInitialKey(String)", "Loading Initial Key from System property value " + EnvfileLocation);
/*      */       }
/*      */       
/*  197 */       File toLoad = new File(EnvfileLocation);
/*      */       try {
/*  199 */         initialkey = CryptoUtil.readInitialKey(toLoad);
/*  200 */         initalkeyfilepath = toLoad.getAbsolutePath();
/*      */       }
/*  202 */       catch (PBEException e) {
/*  203 */         if (Trace.isOn) {
/*  204 */           Trace.catchBlock("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "loadInitialKey(String)", (Throwable)e);
/*      */         }
/*  206 */         HashMap<String, String> inserts = new HashMap<>();
/*  207 */         inserts.put("AMS_INITIAL_KEYFILE", toLoad.getAbsolutePath());
/*  208 */         ConfigException thrown = new ConfigException(AmsErrorMessages.mjp_bad_initial_keyfile, inserts, (Throwable)e);
/*  209 */         if (Trace.isOn) {
/*  210 */           Trace.throwing("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "loadInitialKey(String)", (Throwable)thrown);
/*      */         }
/*  212 */         throw thrown;
/*      */       }
/*      */     
/*  215 */     } else if (configfilevalue != null) {
/*  216 */       if (Trace.isOn) {
/*  217 */         Trace.data("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "loadInitialKey(String)", "Loading Initial Key from Configuration File value " + configfilevalue);
/*      */       }
/*      */       
/*  220 */       File toLoad = new File(configfilevalue);
/*      */       try {
/*  222 */         initialkey = CryptoUtil.readInitialKey(toLoad);
/*  223 */         initalkeyfilepath = toLoad.getAbsolutePath();
/*      */       }
/*  225 */       catch (PBEException e) {
/*  226 */         if (Trace.isOn) {
/*  227 */           Trace.catchBlock("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "loadInitialKey(String)", (Throwable)e);
/*      */         }
/*  229 */         HashMap<String, String> inserts = new HashMap<>();
/*  230 */         inserts.put("AMS_INITIAL_KEYFILE", toLoad.getAbsolutePath());
/*  231 */         ConfigException thrown = new ConfigException(AmsErrorMessages.mjp_bad_initial_keyfile, inserts, (Throwable)e);
/*  232 */         if (Trace.isOn) {
/*  233 */           Trace.throwing("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "loadInitialKey(String)", (Throwable)thrown);
/*      */         }
/*  235 */         throw thrown;
/*      */       } 
/*      */     } else {
/*      */       
/*  239 */       if (Trace.isOn) {
/*  240 */         Trace.data("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "loadInitialKey(String)", "Using default initial key");
/*      */       }
/*      */       
/*  243 */       initialkey = CryptoUtil.rebuildXORdKey(AMS_DEFAULT_KEY, AMS_KEY_MASK);
/*  244 */       defaultInitialKeyUsed = true;
/*      */     } 
/*  246 */     if (Trace.isOn) {
/*  247 */       Trace.exit("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "loadInitialKey(String)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void overrideInitialKey(File toLoad) throws PBEException {
/*  257 */     if (Trace.isOn) {
/*  258 */       Trace.entry("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "overrideInitialKey(char[])", new Object[] { "********" });
/*      */     }
/*      */     
/*  261 */     initialkey = CryptoUtil.readInitialKey(toLoad);
/*  262 */     initalkeyfilepath = toLoad.getAbsolutePath();
/*  263 */     defaultInitialKeyUsed = false;
/*  264 */     if (Trace.isOn) {
/*  265 */       Trace.exit("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "overrideInitialKey(char[])");
/*      */     }
/*      */   }
/*      */   
/*      */   public void init(KeyStore myKs) {
/*  270 */     if (Trace.isOn) {
/*  271 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "init(KeyStore)", new Object[] { myKs });
/*      */     }
/*      */     
/*  274 */     if (this.ks == null) {
/*  275 */       this.ks = myKs;
/*      */     }
/*  277 */     if (Trace.isOn) {
/*  278 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "init(KeyStore)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public X509Certificate getCertificate(String alias) throws AMBIException {
/*  288 */     if (Trace.isOn) {
/*  289 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getCertificate(String)", new Object[] { alias });
/*      */     }
/*      */ 
/*      */     
/*  293 */     X509Certificate cert = null;
/*      */     try {
/*  295 */       cert = (X509Certificate)getKs().getCertificate(alias);
/*      */     }
/*  297 */     catch (KeyStoreException e) {
/*  298 */       if (Trace.isOn) {
/*  299 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getCertificate(String)", e);
/*      */       }
/*      */       
/*  302 */       HashMap<String, Object> inserts = new HashMap<>();
/*  303 */       inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", alias);
/*  304 */       inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/*  305 */       AMBIException ex = new AMBIException(AmsErrorMessages.mju_user_certificate_not_found, inserts, e);
/*  306 */       if (Trace.isOn) {
/*  307 */         Trace.throwing(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getCertificate(String)", (Throwable)ex);
/*      */       }
/*  309 */       throw ex;
/*      */     } 
/*      */     
/*  312 */     if (Trace.isOn) {
/*  313 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getCertificate(String)", cert);
/*      */     }
/*  315 */     return cert;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PrivateKey getPrivateKey(String alias) throws AMBIException {
/*  323 */     if (Trace.isOn) {
/*  324 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getPrivateKey(String)", new Object[] { alias });
/*      */     }
/*      */ 
/*      */     
/*  328 */     PrivateKey pKey = null;
/*      */     
/*      */     try {
/*  331 */       char[] pkeypw = null;
/*  332 */       if (this.pkeyPassword != null) {
/*  333 */         if (Trace.isOn) {
/*  334 */           Trace.data("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getPrivateKey(String)", "Password is protected in type: ", this.pkeyPassword
/*  335 */               .getProtectionType());
/*      */         }
/*  337 */         switch (this.pkeyPassword.getProtectionType()) {
/*      */           
/*      */           case PLAINTEXT:
/*      */           case NULL:
/*  341 */             pkeypw = new char[(this.pkeyPassword.getPassword()).length];
/*  342 */             System.arraycopy(this.pkeyPassword.getPassword(), 0, pkeypw, 0, (this.pkeyPassword.getPassword()).length);
/*      */             break;
/*      */           case OLDPROTECTED:
/*  345 */             pkeypw = decryptPasswordOld(new String(this.pkeyPassword.getPassword()));
/*      */             break;
/*      */           case NEWPROTECTED:
/*  348 */             pkeypw = decryptPassword(new String(this.pkeyPassword.getPassword()));
/*      */             break;
/*      */         } 
/*      */ 
/*      */       
/*  353 */       } else if (Trace.isOn) {
/*  354 */         Trace.data("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getPrivateKey(String)", "Private key password has not be created and is null.");
/*      */       } 
/*      */ 
/*      */       
/*  358 */       pKey = (PrivateKey)getKs().getKey(alias, pkeypw);
/*      */       
/*  360 */       if (pkeypw != null) {
/*  361 */         Arrays.fill(pkeypw, false);
/*      */       }
/*      */     }
/*  364 */     catch (Exception e) {
/*  365 */       if (Trace.isOn) {
/*  366 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getPrivateKey(String)", e);
/*      */       }
/*  368 */       HashMap<String, Object> inserts = new HashMap<>();
/*  369 */       inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", alias);
/*  370 */       inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/*  371 */       AMBIException ex = new AMBIException(AmsErrorMessages.mju_user_privatekey_not_found, inserts, e);
/*  372 */       if (Trace.isOn) {
/*  373 */         Trace.throwing(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getPrivateKey(String)", (Throwable)ex);
/*      */       }
/*  375 */       throw ex;
/*      */     } 
/*      */     
/*  378 */     if (Trace.isOn) {
/*  379 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getPrivateKey(String)", "************");
/*      */     }
/*      */     
/*  382 */     return pKey;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PrivateKey getPrivateKey(String alias, String password) throws AMBIException {
/*  392 */     if (Trace.isOn) {
/*  393 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getPrivateKey(String,String)", new Object[] { alias, "************" });
/*      */     }
/*      */ 
/*      */     
/*  397 */     PrivateKey pKey = null;
/*      */     try {
/*  399 */       pKey = (PrivateKey)getKs().getKey(alias, password.toCharArray());
/*      */     }
/*  401 */     catch (Exception e) {
/*  402 */       if (Trace.isOn) {
/*  403 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getPrivateKey(String,String)", e);
/*      */       }
/*      */       
/*  406 */       HashMap<String, Object> inserts = new HashMap<>();
/*  407 */       inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", alias);
/*  408 */       inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/*  409 */       AMBIException ex = new AMBIException(AmsErrorMessages.mju_user_privatekey_not_found, inserts, e);
/*  410 */       if (Trace.isOn) {
/*  411 */         Trace.throwing(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getPrivateKey(String,String)", (Throwable)ex);
/*      */       }
/*      */       
/*  414 */       throw ex;
/*      */     } 
/*      */     
/*  417 */     if (Trace.isOn) {
/*  418 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getPrivateKey(String, String)", "************");
/*      */     }
/*      */     
/*  421 */     return pKey;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Enumeration<String> aliases() throws AMBIException {
/*  429 */     if (Trace.isOn) {
/*  430 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "aliases()");
/*      */     }
/*      */     
/*  433 */     Enumeration<String> eAliases = null;
/*      */     try {
/*  435 */       eAliases = getKs().aliases();
/*      */     }
/*  437 */     catch (KeyStoreException e) {
/*  438 */       if (Trace.isOn) {
/*  439 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "aliases()", e);
/*      */       }
/*  441 */       HashMap<String, String> inserts = new HashMap<>();
/*  442 */       inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/*  443 */       ConfigException ex = new ConfigException(AmsErrorMessages.mju_keystore_aliases_not_found, inserts, e);
/*  444 */       if (Trace.isOn) {
/*  445 */         Trace.throwing(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "aliases()", (Throwable)ex);
/*      */       }
/*  447 */       throw ex;
/*      */     } 
/*      */     
/*  450 */     if (Trace.isOn) {
/*  451 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "aliases()", eAliases);
/*      */     }
/*  453 */     return eAliases;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsAlias(String alias) throws AMBIException {
/*  463 */     if (Trace.isOn) {
/*  464 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "containsAlias(String)", new Object[] { alias });
/*      */     }
/*      */ 
/*      */     
/*  468 */     boolean contains = false;
/*      */     try {
/*  470 */       contains = getKs().containsAlias(alias);
/*      */     }
/*  472 */     catch (Exception e) {
/*  473 */       if (Trace.isOn) {
/*  474 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "containsAlias(String)", e);
/*      */       }
/*  476 */       HashMap<String, Object> inserts = new HashMap<>();
/*  477 */       inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", alias);
/*  478 */       inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/*  479 */       AMBIException ex = new AMBIException(AmsErrorMessages.mju_keystore_alias_verify, inserts, e);
/*  480 */       if (Trace.isOn) {
/*  481 */         Trace.throwing(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "containsAlias(String)", (Throwable)ex);
/*      */       }
/*  483 */       throw ex;
/*      */     } 
/*      */     
/*  486 */     if (Trace.isOn) {
/*  487 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "containsAlias(String)", 
/*  488 */           Boolean.valueOf(contains));
/*      */     }
/*  490 */     return contains;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Certificate[] getCertificateChain(String alias) throws AMBIException {
/*  499 */     if (Trace.isOn) {
/*  500 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getCertificateChain(String)", new Object[] { alias });
/*      */     }
/*      */ 
/*      */     
/*  504 */     Certificate[] certChain = null;
/*      */     try {
/*  506 */       certChain = getKs().getCertificateChain(alias);
/*      */     }
/*  508 */     catch (Exception e) {
/*  509 */       if (Trace.isOn) {
/*  510 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getCertificateChain(String)", e);
/*      */       }
/*      */       
/*  513 */       HashMap<String, Object> inserts = new HashMap<>();
/*  514 */       inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", alias);
/*  515 */       inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/*  516 */       AMBIException ex = new AMBIException(AmsErrorMessages.mju_keystore_certificate_chain_not_found, inserts, e);
/*  517 */       if (Trace.isOn) {
/*  518 */         Trace.throwing(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getCertificateChain(String)", (Throwable)ex);
/*      */       }
/*      */       
/*  521 */       throw ex;
/*      */     } 
/*      */     
/*  524 */     if (Trace.isOn) {
/*  525 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getCertificateChain(String)", certChain);
/*      */     }
/*      */     
/*  528 */     return certChain;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCertificateEntry(String alias) throws AMBIException {
/*  537 */     if (Trace.isOn) {
/*  538 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "isCertificateEntry(String)", new Object[] { alias });
/*      */     }
/*      */ 
/*      */     
/*  542 */     boolean isCertificate = false;
/*      */     try {
/*  544 */       isCertificate = getKs().isCertificateEntry(alias);
/*      */     }
/*  546 */     catch (Exception e) {
/*  547 */       if (Trace.isOn) {
/*  548 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "isCertificateEntry(String)", e);
/*      */       }
/*      */       
/*  551 */       HashMap<String, Object> inserts = new HashMap<>();
/*  552 */       inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", alias);
/*  553 */       inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/*  554 */       AMBIException ex = new AMBIException(AmsErrorMessages.mju_error_keystore_certificate_entry_verify, inserts, e);
/*  555 */       if (Trace.isOn) {
/*  556 */         Trace.throwing(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "isCertificateEntry(String)", (Throwable)ex);
/*      */       }
/*      */       
/*  559 */       throw ex;
/*      */     } 
/*      */     
/*  562 */     if (Trace.isOn) {
/*  563 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "isCertificateEntry(String)", 
/*  564 */           Boolean.valueOf(isCertificate));
/*      */     }
/*  566 */     return isCertificate;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isKeyEntry(String alias) throws AMBIException {
/*  574 */     if (Trace.isOn) {
/*  575 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "isKeyEntry(String)", new Object[] { alias });
/*      */     }
/*      */ 
/*      */     
/*  579 */     boolean isKey = false;
/*      */     try {
/*  581 */       isKey = getKs().isKeyEntry(alias);
/*      */     }
/*  583 */     catch (Exception e) {
/*  584 */       if (Trace.isOn) {
/*  585 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "isKeyEntry(String)", e);
/*      */       }
/*  587 */       HashMap<String, Object> inserts = new HashMap<>();
/*  588 */       inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", alias);
/*  589 */       inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/*  590 */       AMBIException ex = new AMBIException(AmsErrorMessages.mju_error_keystore_certificate_entry_verify, inserts, e);
/*  591 */       if (Trace.isOn) {
/*  592 */         Trace.throwing(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "isKeyEntry(String)", (Throwable)ex);
/*      */       }
/*  594 */       throw ex;
/*      */     } 
/*      */     
/*  597 */     if (Trace.isOn) {
/*  598 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "isKeyEntry(String)", 
/*  599 */           Boolean.valueOf(isKey));
/*      */     }
/*  601 */     return isKey;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getType() {
/*  609 */     if (Trace.isOn) {
/*  610 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getType()");
/*      */     }
/*      */ 
/*      */     
/*  614 */     String type = getKs().getType();
/*      */     
/*  616 */     if (Trace.isOn) {
/*  617 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getType()", new Object[] { type });
/*      */     }
/*      */     
/*  620 */     return type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public X509Certificate[] getCertificates(List<String> dns, boolean all) throws AMBIException {
/*  630 */     if (Trace.isOn) {
/*  631 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getCertificates(List,boolean)", new Object[] { dns, Boolean.valueOf(all) });
/*      */     }
/*      */     
/*  634 */     X509Certificate[] certificates = doGetCertificates(dns, all);
/*      */     
/*  636 */     if (Trace.isOn) {
/*  637 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getCertificates(List,boolean)", certificates);
/*      */     }
/*      */     
/*  640 */     return certificates;
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
/*      */   private X509Certificate[] doGetCertificates(List<String> dns, boolean all) throws AMBIException {
/*  653 */     if (Trace.isOn) {
/*  654 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "doGetCertificates(List,boolean)", new Object[] { dns, 
/*  655 */             Boolean.valueOf(all) });
/*      */     }
/*  657 */     X509Certificate[] ret = null;
/*  658 */     if (dns != null && !dns.isEmpty()) {
/*  659 */       Enumeration<String> e = aliases();
/*  660 */       ret = new X509Certificate[dns.size()];
/*      */       
/*  662 */       while (e.hasMoreElements() && !dns.isEmpty()) {
/*  663 */         String alias = e.nextElement();
/*  664 */         X509Certificate cert = getCertificate(alias);
/*  665 */         if (cert != null) {
/*  666 */           String certSubjectDN = cert.getSubjectX500Principal().toString();
/*  667 */           X500NameWrapper certX500Name = new X500NameWrapper(certSubjectDN);
/*      */           
/*  669 */           for (int i = 0; i < dns.size(); i++) {
/*  670 */             String recipientName = dns.get(i);
/*  671 */             X500NameWrapper recipientX500Name = new X500NameWrapper(recipientName);
/*      */             
/*  673 */             if (certX500Name.isEqual(recipientX500Name)) {
/*  674 */               ret[-(dns.size() - ret.length)] = cert;
/*  675 */               dns.remove(i);
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  682 */     if (all && !dns.isEmpty()) {
/*  683 */       HashMap<String, Object> inserts = new HashMap<>();
/*  684 */       inserts.put("AMS_INSERT_RECIPIENTS_NAMES", TraceUtil.objectsAsString(dns.toArray()));
/*  685 */       MissingCertificateException ex = new MissingCertificateException(AmsErrorMessages.mju_policy_failed_to_get_receiver_certs, inserts);
/*  686 */       if (Trace.isOn) {
/*  687 */         Trace.throwing(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "doGetCertificates(List,boolean)", (Throwable)ex);
/*      */       }
/*      */       
/*  690 */       throw ex;
/*      */     } 
/*  692 */     if (!all) {
/*  693 */       int howManyNulls = 0;
/*  694 */       for (int i = 0; i < ret.length; i++) {
/*  695 */         if (ret[i] == null) {
/*  696 */           howManyNulls++;
/*      */         }
/*      */       } 
/*  699 */       if (howManyNulls > 0) {
/*  700 */         X509Certificate[] bac = ret;
/*  701 */         ret = new X509Certificate[bac.length - howManyNulls];
/*  702 */         int j = 0;
/*  703 */         for (int k = 0; k < bac.length; k++) {
/*  704 */           if (bac[k] != null && j < ret.length) {
/*  705 */             ret[j++] = bac[k];
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*  710 */     if (Trace.isOn) {
/*  711 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "doGetCertificates(List,boolean)", ret);
/*      */     }
/*      */     
/*  714 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public KeyStore getKeyStore() {
/*  722 */     return this.ks;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  730 */     if (Trace.isOn) {
/*  731 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "toString()");
/*      */     }
/*  733 */     StringBuilder buff = new StringBuilder();
/*  734 */     buff.append(this.keyStoreType).append('/').append(this.keyStoreProvider).append('/');
/*      */     
/*  736 */     if (this.keyStoreFile != null) {
/*  737 */       buff.append(this.keyStoreFile);
/*      */     }
/*  739 */     String traceRet1 = buff.toString();
/*  740 */     if (Trace.isOn) {
/*  741 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "toString()", traceRet1);
/*      */     }
/*  743 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getProvider() {
/*  751 */     if (Trace.isOn) {
/*  752 */       Trace.data(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getProvider()", "getter", this.keyStoreProvider);
/*      */     }
/*      */     
/*  755 */     return this.keyStoreProvider;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setPkeyPass(KeyStoreConfig keyStoreConfig) {
/*  762 */     if (Trace.isOn) {
/*  763 */       Trace.data(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "setPkeyPass(KeyStoreConfig)", "setter", keyStoreConfig);
/*      */     }
/*      */     
/*  766 */     if (keyStoreConfig.getPrivKeyPassword() != null && keyStoreConfig
/*  767 */       .getPrivKeyPassword().getProtectionType() != PasswordObject.PasswordType.NULL) {
/*  768 */       this.pkeyPassword = new PasswordObject(keyStoreConfig.getPrivKeyPassword());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  774 */     if (Trace.isOn) {
/*  775 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "hashCode()");
/*      */     }
/*  777 */     int traceRet1 = this.keyStoreFile.hashCode();
/*  778 */     if (Trace.isOn) {
/*  779 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "hashCode()", 
/*  780 */           Integer.valueOf(traceRet1));
/*      */     }
/*  782 */     return traceRet1;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/*  787 */     if (Trace.isOn) {
/*  788 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "equals(Object)", new Object[] { obj });
/*      */     }
/*      */     
/*  791 */     boolean traceRet1 = this.keyStoreFile.equals(obj);
/*  792 */     if (Trace.isOn) {
/*  793 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "equals(Object)", 
/*  794 */           Boolean.valueOf(traceRet1));
/*      */     }
/*  796 */     return traceRet1;
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
/*      */   public static String encryptPassword(char[] plainTextPassword, String property) throws AMBIException {
/*  809 */     if (Trace.isOn) {
/*  810 */       Trace.entry("AbstractKeyStoreAccess", "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "encryptPassword(char[])");
/*      */     }
/*  812 */     String result = "";
/*      */     
/*      */     try {
/*  815 */       EncodedPasswordAbstract p = CryptoUtil.encryptPassword(plainTextPassword, initialkey, FIXEDSALT, 2, "<AMS>");
/*      */       
/*  817 */       result = p.toPrintableString();
/*      */     
/*      */     }
/*  820 */     catch (PBEException e) {
/*  821 */       if (Trace.isOn) {
/*  822 */         Trace.catchBlock("AbstractKeyStoreAccess", "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "encryptPassword(char[])", (Throwable)e);
/*      */       }
/*  824 */       HashMap<String, Object> inserts = new HashMap<>();
/*  825 */       inserts.put("AMS_INSERT_PROPERTY_KEY", property);
/*  826 */       AMBIException ex = new AMBIException(AmsErrorMessages.mju_keystore_password_protection_failure_new, inserts, (Throwable)e);
/*  827 */       if (Trace.isOn) {
/*  828 */         Trace.throwing("AbstractKeyStoreAccess", "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "encryptPassword(char [ ])", (Throwable)ex, 1);
/*      */       }
/*  830 */       throw ex;
/*      */     } 
/*      */     
/*  833 */     if (Trace.isOn) {
/*  834 */       Trace.exit("AbstractKeyStoreAccess", "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "encryptPassword(char[])", new Object[] { result });
/*      */     }
/*  836 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public String encryptPasswordOld(char[] plainTextPassword) throws AMBIException {
/*  847 */     if (Trace.isOn) {
/*  848 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "encryptPasswordOld(char[])");
/*      */     }
/*      */ 
/*      */     
/*  852 */     byte[] pwBytes = null;
/*      */     try {
/*  854 */       Cipher cp = Cipher.getInstance("RSA", "BC");
/*      */       
/*  856 */       if (!getKs().containsAlias(this.credentialAlias)) {
/*  857 */         HashMap<String, Object> inserts = new HashMap<>();
/*  858 */         inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", this.credentialAlias);
/*  859 */         inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/*  860 */         AMBIException ex = new AMBIException(AmsErrorMessages.mju_credential_alias_not_found_keystore, inserts);
/*  861 */         if (Trace.isOn) {
/*  862 */           Trace.throwing(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "encryptPasswordOld(char [ ])", (Throwable)ex, 1);
/*      */         }
/*      */         
/*  865 */         throw ex;
/*      */       } 
/*      */       
/*  868 */       if (!getKs().isKeyEntry(this.credentialAlias)) {
/*  869 */         HashMap<String, Object> inserts = new HashMap<>();
/*  870 */         inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", this.credentialAlias);
/*  871 */         inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/*  872 */         AMBIException ex = new AMBIException(AmsErrorMessages.mju_credential_alias_not_key_entry, inserts);
/*  873 */         if (Trace.isOn) {
/*  874 */           Trace.throwing(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "encryptPasswordOld(char [ ])", (Throwable)ex, 2);
/*      */         }
/*      */         
/*  877 */         throw ex;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  882 */       char[] pkeypw = null;
/*  883 */       if (this.pkeyPassword != null) {
/*  884 */         if (Trace.isOn) {
/*  885 */           Trace.data("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getPrivateKey(String)", "Password is protected in type: ", this.pkeyPassword
/*  886 */               .getProtectionType());
/*      */         }
/*  888 */         switch (this.pkeyPassword.getProtectionType()) {
/*      */           case PLAINTEXT:
/*      */           case NULL:
/*  891 */             pkeypw = new char[(this.pkeyPassword.getPassword()).length];
/*  892 */             System.arraycopy(this.pkeyPassword.getPassword(), 0, pkeypw, 0, (this.pkeyPassword.getPassword()).length);
/*      */             break;
/*      */           case OLDPROTECTED:
/*  895 */             pkeypw = decryptPasswordOld(new String(this.pkeyPassword.getPassword()));
/*      */             break;
/*      */           case NEWPROTECTED:
/*  898 */             pkeypw = decryptPassword(new String(this.pkeyPassword.getPassword()));
/*      */             break;
/*      */         } 
/*      */ 
/*      */       
/*  903 */       } else if (Trace.isOn) {
/*  904 */         Trace.data("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getPrivateKey(String)", "Private key password has not be created and is null.");
/*      */       } 
/*      */ 
/*      */       
/*  908 */       PrivateKey privateKey = (PrivateKey)getKs().getKey(this.credentialAlias, pkeypw);
/*      */       
/*  910 */       Arrays.fill(pkeypw, false);
/*      */       
/*  912 */       if (privateKey == null) {
/*  913 */         HashMap<String, Object> inserts = new HashMap<>();
/*  914 */         inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", this.credentialAlias);
/*  915 */         inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/*  916 */         AMBIException ex = new AMBIException(AmsErrorMessages.mju_user_privatekey_not_found, inserts, new InvalidKeyException("null"));
/*      */         
/*  918 */         if (Trace.isOn) {
/*  919 */           Trace.throwing(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "encryptPasswordOld(char [ ])", (Throwable)ex, 3);
/*      */         }
/*      */         
/*  922 */         throw ex;
/*      */       } 
/*      */ 
/*      */       
/*  926 */       KeyFactory kFactory = KeyFactory.getInstance("RSA", "BC");
/*      */ 
/*      */       
/*  929 */       if (((RSAPrivateKey)privateKey).getModulus() == null || ((RSAPrivateKey)privateKey).getPrivateExponent() == null) {
/*  930 */         HashMap<String, Object> inserts = new HashMap<>();
/*  931 */         inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", this.credentialAlias);
/*  932 */         inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/*  933 */         AMBIException ex = new AMBIException(AmsErrorMessages.mju_user_privatekey_not_found, inserts, new InvalidKeyException("Failed to extract the key"));
/*      */         
/*  935 */         if (Trace.isOn) {
/*  936 */           Trace.throwing(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "encryptPasswordOld(char [ ])", (Throwable)ex, 4);
/*      */         }
/*      */         
/*  939 */         throw ex;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  944 */       RSAPublicKeySpec rsaPubSpec = new RSAPublicKeySpec(((RSAPrivateKey)privateKey).getModulus(), ((RSAPrivateKey)privateKey).getPrivateExponent());
/*      */       
/*  946 */       PublicKey pseudoPublicKey = kFactory.generatePublic(rsaPubSpec);
/*      */ 
/*      */       
/*  949 */       cp.init(1, pseudoPublicKey);
/*  950 */       cp.update(CryptoUtil.charArrayToByteArray(plainTextPassword));
/*      */ 
/*      */       
/*  953 */       byte[] shroudedBytes = cp.doFinal();
/*      */ 
/*      */       
/*  956 */       pwBytes = Base64.encode(shroudedBytes);
/*      */     }
/*  958 */     catch (Exception e) {
/*  959 */       if (Trace.isOn) {
/*  960 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "encryptPasswordOld(char [ ])", e);
/*      */       }
/*      */       
/*  963 */       HashMap<String, Object> inserts = new HashMap<>();
/*  964 */       inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", this.credentialAlias);
/*  965 */       inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/*  966 */       AMBIException ex = new AMBIException(AmsErrorMessages.mju_keystore_password_protection_failure, inserts, e);
/*  967 */       if (Trace.isOn) {
/*  968 */         Trace.throwing(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "encryptPasswordOld(char [ ])", (Throwable)ex, 5);
/*      */       }
/*      */       
/*  971 */       throw ex;
/*      */     } 
/*      */     
/*  974 */     String traceRet1 = new String(pwBytes);
/*  975 */     if (Trace.isOn) {
/*  976 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "encryptPasswordOld(char [ ])", traceRet1);
/*      */     }
/*      */     
/*  979 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected char[] decryptPassword(String encryptedPassword) throws AMBIException {
/*      */     char[] result;
/*  990 */     if (Trace.isOn) {
/*  991 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "decryptPassword(String)", new Object[] { "********" });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  998 */       EncodedPasswordAbstract p = CryptoUtil.getEncodedPasswordObjFromString(encryptedPassword, "<AMS>");
/*  999 */       if (p instanceof EncodedPasswordV0) {
/*      */ 
/*      */         
/* 1002 */         result = decryptPasswordOld(((EncodedPasswordV0)p).getPassword());
/*      */       } else {
/*      */         
/* 1005 */         result = CryptoUtil.decryptPassword(initialkey, FIXEDSALT, p);
/*      */       }
/*      */     
/* 1008 */     } catch (PBEException e) {
/* 1009 */       if (Trace.isOn) {
/* 1010 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "decryptPassword(String)", (Throwable)e, 1);
/*      */       }
/* 1012 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1013 */       inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", this.credentialAlias);
/* 1014 */       inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/* 1015 */       AMBIException ex = new AMBIException(AmsErrorMessages.mju_keystore_password_unprotection_failure, inserts, (Throwable)e);
/* 1016 */       if (Trace.isOn) {
/* 1017 */         Trace.throwing(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "decryptPassword(String)", (Throwable)ex, 2);
/*      */       }
/* 1019 */       throw ex;
/*      */     } 
/*      */     
/* 1022 */     if (Trace.isOn) {
/* 1023 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "decryptPassword(String)", "***********");
/*      */     }
/*      */     
/* 1026 */     return result;
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
/*      */   @Deprecated
/*      */   protected char[] decryptPasswordOld(String encryptedPassword) throws AMBIException {
/* 1041 */     if (Trace.isOn) {
/* 1042 */       Trace.entry(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "decryptPasswordOld(String)", new Object[] { "********" });
/*      */     }
/*      */ 
/*      */     
/* 1046 */     char[] decryptedPassword = null;
/*      */     try {
/* 1048 */       byte[] decoded = Base64.decode(encryptedPassword);
/* 1049 */       PublicKey publicKey = null;
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1054 */         publicKey = getKs().getCertificate(this.credentialAlias).getPublicKey();
/*      */       }
/* 1056 */       catch (Exception e) {
/* 1057 */         if (Trace.isOn) {
/* 1058 */           Trace.catchBlock(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "decryptPasswordOld(String)", e, 1);
/*      */         }
/*      */         
/* 1061 */         HashMap<String, Object> inserts = new HashMap<>();
/* 1062 */         inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", this.credentialAlias);
/* 1063 */         inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/* 1064 */         AMBIException ex = new AMBIException(AmsErrorMessages.mju_user_certificate_not_found, inserts, e);
/* 1065 */         if (Trace.isOn) {
/* 1066 */           Trace.throwing(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "decryptPasswordOld(String)", (Throwable)ex, 1);
/*      */         }
/*      */         
/* 1069 */         throw ex;
/*      */       } 
/* 1071 */       Cipher cp = Cipher.getInstance("RSA", "BC");
/*      */       
/* 1073 */       KeyFactory kFactory = KeyFactory.getInstance("RSA", "BC");
/*      */ 
/*      */ 
/*      */       
/* 1077 */       RSAPrivateKeySpec rsaPubSpec = new RSAPrivateKeySpec(((RSAPublicKey)publicKey).getModulus(), ((RSAPublicKey)publicKey).getPublicExponent());
/*      */       
/* 1079 */       PrivateKey pseudoPrivateKey = kFactory.generatePrivate(rsaPubSpec);
/*      */       
/* 1081 */       cp.init(2, pseudoPrivateKey);
/* 1082 */       cp.update(decoded);
/*      */       
/* 1084 */       byte[] passwordBytes = cp.doFinal();
/* 1085 */       decryptedPassword = CryptoUtil.byteArrayToCharArray(passwordBytes);
/*      */     }
/* 1087 */     catch (Exception e) {
/* 1088 */       if (Trace.isOn) {
/* 1089 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "decryptPasswordOld(String)", e, 2);
/*      */       }
/*      */       
/* 1092 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1093 */       inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", this.credentialAlias);
/* 1094 */       inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/* 1095 */       AMBIException ex = new AMBIException(AmsErrorMessages.mju_keystore_password_unprotection_failure, inserts, e);
/* 1096 */       if (Trace.isOn) {
/* 1097 */         Trace.throwing(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "decryptPasswordOld(String)", (Throwable)ex, 2);
/*      */       }
/*      */       
/* 1100 */       throw ex;
/*      */     } 
/*      */     
/* 1103 */     if (Trace.isOn) {
/* 1104 */       Trace.exit(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "decryptPasswordOld(String)", "*********");
/*      */     }
/*      */     
/* 1107 */     return decryptedPassword;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected KeyStore getKs() {
/* 1114 */     if (Trace.isOn) {
/* 1115 */       Trace.data(this, "com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "getKs()", "getter", this.ks);
/*      */     }
/* 1117 */     return this.ks;
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
/*      */   public static boolean testDecrypt(PasswordObject p) {
/* 1130 */     if (Trace.isOn) {
/* 1131 */       Trace.entry("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "testDecrypt(PasswordObject)", new Object[] { "********" });
/*      */     }
/* 1133 */     boolean result = false;
/*      */     
/* 1135 */     if (p != null) {
/* 1136 */       if (Trace.isOn) {
/* 1137 */         Trace.data("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "testDecrypt(PasswordObject)", "Password is protected in type", p
/* 1138 */             .getProtectionType());
/*      */       }
/* 1140 */       switch (p.getProtectionType()) {
/*      */         case PLAINTEXT:
/*      */         case NULL:
/* 1143 */           result = true;
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case OLDPROTECTED:
/* 1149 */           result = true;
/*      */           break;
/*      */         case NEWPROTECTED:
/*      */           try {
/* 1153 */             EncodedPasswordAbstract po = CryptoUtil.getEncodedPasswordObjFromString(new String(p.getPassword()), "<AMS>");
/*      */             
/* 1155 */             if (po.getAlgorithm() == 0) {
/* 1156 */               if (Trace.isOn) {
/* 1157 */                 Trace.data("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "testDecrypt(PasswordObject)", "Password is an old password protection in a new format. We cannot test it so continue");
/*      */               }
/*      */               
/* 1160 */               result = true;
/*      */               break;
/*      */             } 
/* 1163 */             result = CryptoUtil.testDecryptPassword(initialkey, FIXEDSALT, po);
/*      */           
/*      */           }
/* 1166 */           catch (PBEException e) {
/* 1167 */             if (Trace.isOn) {
/* 1168 */               Trace.catchBlock("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "testDecrypt(PasswordObject)", (Throwable)e);
/*      */             }
/* 1170 */             result = false;
/*      */           } 
/*      */           break;
/*      */         
/*      */         default:
/* 1175 */           result = false;
/*      */           break;
/*      */       } 
/*      */ 
/*      */     
/*      */     } else {
/* 1181 */       result = true;
/*      */     } 
/*      */     
/* 1184 */     if (Trace.isOn) {
/* 1185 */       Trace.exit("com.ibm.mq.ese.pki.AbstractKeyStoreAccess", "testDecrypt(PasswordObject)", Boolean.valueOf(result));
/*      */     }
/* 1187 */     return result;
/*      */   }
/*      */   
/*      */   public static boolean usingDefaultKey() {
/* 1191 */     return defaultInitialKeyUsed;
/*      */   }
/*      */   
/*      */   public static String getInitialKeyFilePath() {
/* 1195 */     return initalkeyfilepath;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\pki\AbstractKeyStoreAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */