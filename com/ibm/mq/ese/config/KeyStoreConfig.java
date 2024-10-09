/*     */ package com.ibm.mq.ese.config;
/*     */ 
/*     */ import com.ibm.mq.ese.core.SecurityProvider;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.mq.ese.pki.AbstractKeyStoreAccess;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class KeyStoreConfig
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/config/KeyStoreConfig.java";
/*     */   
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.mq.ese.config.KeyStoreConfig", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/config/KeyStoreConfig.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public static final String CLASS = KeyStoreConfig.class.getCanonicalName();
/*     */ 
/*     */   
/*     */   private static final String KSCONF_TYPE = "type";
/*     */ 
/*     */ 
/*     */   
/*     */   static class Fields
/*     */   {
/*     */     static final String KSCONF_KEY_PASS = ".key_pass";
/*     */ 
/*     */     
/*     */     static final String KSCONF_KEYSTORE_PASS = ".keystore_pass";
/*     */     
/*     */     static final String KSCONF_ENCRYPTED = ".encrypted";
/*     */     
/*     */     private static final String KSCONF_PROVIDER = ".provider";
/*     */     
/*     */     private static final String KSCONF_CERTIFICATE = ".certificate";
/*     */     
/*     */     private static final String KSCONF_KEYSTORE = ".keystore";
/*     */     
/*     */     static final String KSCONF_TOKEN_PIN = ".token_pin";
/*     */     
/*     */     static final String CRED_INITIAL_KEYFILE = ".keyfile";
/*     */     
/*     */     static final String KSCONF_SECONDARY_KEYSTORE_PASS = ".secondary_keystore_pass";
/*     */     
/*     */     static final String KSCONF_SECONDARY_KEYSTORE = ".secondary_keystore";
/*     */     
/*     */     static final String KSCONF_CRL_URI = "CRL.URI";
/*     */     
/*     */     static final String KSCONF_CRL_FILE = "CRL.FILE";
/*     */     
/*     */     static final String KSCONF_CRL_LDAP_HOST = "CRL.LDAP.HOST";
/*     */     
/*     */     static final String KSCONF_CRL_LDAP_PORT = "CRL.LDAP.PORT";
/*     */     
/*     */     static final String KSCONF_CRL_CHECK_CDP = "CRL.CDP";
/*     */     
/*     */     static final String KSCONF_OCSP_ENABLE = "OCSP.ENABLE";
/*     */   }
/*     */ 
/*     */   
/*     */   static class PasswordProtection
/*     */   {
/*     */     static final String KSCONF_PROTECTED_YES = "yes";
/*     */     
/*     */     static final String KSCONF_PROTECTED_NO = "no";
/*     */   }
/*     */ 
/*     */   
/*     */   public static class KeystoreType
/*     */   {
/*     */     public static final String KEYSTORE_JKS = "JKS";
/*     */     
/*     */     public static final String KEYSTORE_JCEKS = "JCEKS";
/*     */     
/*     */     public static final String KEYSTORE_PKCS11 = "PKCS11";
/*     */     
/*     */     public static final String KEYSTORE_PKCS11IMPLKS = "PKCS11IMPLKS";
/*     */     
/*     */     public static final String KEYSTORE_JCERACFKS = "JCERACFKS";
/*     */   }
/*     */ 
/*     */   
/*     */   private static class PropertyNamePrefix
/*     */   {
/*     */     private static final String PREFIX_JKS = "JKS";
/*     */     
/*     */     private static final String PREFIX_JCEKS = "JCEKS";
/*     */     
/*     */     private static final String PREFIX_JCERACFKS = "JCERACFKS";
/*     */     
/*     */     private static final String KEYSTORE_PKCS11_PREFIX = "PKCS11";
/*     */     
/*     */     private static final String PREFIX_AMSCRED = "AMSCRED";
/*     */   }
/*     */   
/* 133 */   private static Map<String, String> maskableAttributes = new HashMap<String, String>()
/*     */     {
/*     */       private static final long serialVersionUID = -6516115544852643899L;
/*     */     };
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
/*     */   private String provider;
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
/*     */   private String keyStorePath;
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
/*     */   private String alias;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 179 */   private PasswordObject keyStorePassword = new PasswordObject();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   private PasswordObject privKeyPassword = new PasswordObject();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean passwordsProtected = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String type;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean initialized = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String secondaryKeyStorePath;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 209 */   private PasswordObject secondaryKeyStorePass = new PasswordObject();
/*     */   
/* 211 */   private PkiConfig pkiConfig = null;
/*     */   
/* 213 */   private String initialkeylocation = null;
/*     */ 
/*     */   
/*     */   private Properties originalProps;
/*     */ 
/*     */   
/*     */   private boolean supressPasswordWarnings = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public Properties getOriginalProps() {
/* 224 */     return this.originalProps;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KeyStoreConfig(Properties props, boolean supressPasswordWarnings) throws ConfigException {
/* 234 */     if (Trace.isOn)
/* 235 */       Trace.entry(this, "com.ibm.mq.ese.config.KeyStoreConfig", "<init>(Properties)", new Object[] {
/* 236 */             Trace.sanitizeMap(props, maskableAttributes), Boolean.valueOf(supressPasswordWarnings)
/*     */           }); 
/* 238 */     this.supressPasswordWarnings = supressPasswordWarnings;
/* 239 */     init(props);
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.exit(this, "com.ibm.mq.ese.config.KeyStoreConfig", "<init>(Properties)");
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
/*     */   public KeyStoreConfig(Properties props) throws ConfigException {
/* 253 */     if (Trace.isOn)
/* 254 */       Trace.entry(this, "com.ibm.mq.ese.config.KeyStoreConfig", "<init>(Properties)", new Object[] {
/* 255 */             Trace.sanitizeMap(props, maskableAttributes)
/*     */           }); 
/* 257 */     init(props);
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.exit(this, "com.ibm.mq.ese.config.KeyStoreConfig", "<init>(Properties)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void init(Properties props) throws ConfigException {
/* 269 */     boolean isSoftwareKS = false;
/* 270 */     if (Trace.isOn)
/* 271 */       Trace.entry(this, "com.ibm.mq.ese.config.KeyStoreConfig", "init(Properties)", new Object[] {
/* 272 */             Trace.sanitizeMap(props, maskableAttributes)
/*     */           }); 
/* 274 */     if (props == null) {
/* 275 */       IllegalArgumentException traceRet1 = new IllegalArgumentException("null");
/* 276 */       if (Trace.isOn) {
/* 277 */         Trace.throwing(this, "com.ibm.mq.ese.config.KeyStoreConfig", "init(Properties)", traceRet1);
/*     */       }
/* 279 */       throw traceRet1;
/*     */     } 
/*     */     
/* 282 */     for (Map.Entry<Object, Object> entry : props.entrySet()) {
/* 283 */       String key = entry.getKey().toString();
/* 284 */       String value = entry.getValue().toString();
/*     */       
/* 286 */       if (key.toUpperCase().startsWith("JCEKS")) {
/* 287 */         this.type = "JCEKS";
/* 288 */         doSetValue("JCEKS", key, value, true);
/* 289 */         isSoftwareKS = true; continue;
/* 290 */       }  if (key.toUpperCase().startsWith("JKS")) {
/*     */         
/* 292 */         if (getType() == null) {
/* 293 */           this.type = "JKS";
/*     */         }
/* 295 */         doSetValue("JKS", key, value, false);
/* 296 */         isSoftwareKS = true; continue;
/* 297 */       }  if (key.toUpperCase().startsWith("JCERACFKS")) {
/* 298 */         this.type = "JCERACFKS";
/* 299 */         doSetValue("JCERACFKS", key, value, true);
/* 300 */         isSoftwareKS = true; continue;
/* 301 */       }  if (key.toUpperCase().startsWith("AMSCRED")) {
/* 302 */         doSetValue("AMSCRED", key, value, true);
/*     */       }
/*     */     } 
/*     */     
/* 306 */     this.pkiConfig = new PkiConfig(props);
/* 307 */     if (!isSoftwareKS && SecurityProvider.isPkcs11Enabled()) {
/* 308 */       initPKCS11(props);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 313 */     AbstractKeyStoreAccess.loadInitialKey(this.initialkeylocation);
/*     */ 
/*     */     
/* 316 */     if (this.passwordsProtected) {
/* 317 */       ValidateAllPasswords();
/* 318 */     } else if (!this.supressPasswordWarnings && this.type != "JCERACFKS") {
/*     */       
/* 320 */       HashMap<String, Object> inserts = new HashMap<>();
/* 321 */       AmsErrorMessages.log(CLASS, "com.ibm.mq.ese.config.KeyStoreConfig", AmsErrorMessages.mju_plain_passwords, inserts);
/*     */     } 
/*     */ 
/*     */     
/* 325 */     this.originalProps = props;
/*     */     
/* 327 */     this.initialized = true;
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.exit(this, "com.ibm.mq.ese.config.KeyStoreConfig", "init(Properties)", this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initPKCS11(Properties props) {
/* 338 */     if (Trace.isOn)
/* 339 */       Trace.entry(this, "com.ibm.mq.ese.config.KeyStoreConfig", "initPKCS11(Properties)", new Object[] {
/* 340 */             Trace.sanitizeMap(props, maskableAttributes)
/*     */           }); 
/* 342 */     for (Map.Entry<Object, Object> entry : props.entrySet()) {
/* 343 */       String key = entry.getKey().toString();
/* 344 */       String value = entry.getValue().toString();
/* 345 */       if (key.toUpperCase().startsWith("PKCS11")) {
/*     */         
/* 347 */         this.type = "PKCS11";
/* 348 */         doSetValue("PKCS11", key, value, true);
/*     */       } 
/*     */     } 
/*     */     
/* 352 */     if (Trace.isOn) {
/* 353 */       Trace.exit(this, "com.ibm.mq.ese.config.KeyStoreConfig", "initPKCS11(Properties)");
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
/*     */   private void doSetValue(String prefix, String key, String value, boolean override) {
/* 371 */     String trimmedString = (value == null) ? "" : value.trim();
/* 372 */     if (key.substring(prefix.length()).equalsIgnoreCase(".keystore"))
/*     */     {
/* 374 */       if (getKeyStorePath() == null || override) {
/* 375 */         this.keyStorePath = trimmedString;
/*     */       }
/*     */     }
/* 378 */     if (key.substring(prefix.length()).equalsIgnoreCase(".certificate"))
/*     */     {
/* 380 */       if (getAlias() == null || override) {
/* 381 */         this.alias = trimmedString;
/*     */       }
/*     */     }
/* 384 */     if (key.substring(prefix.length()).equalsIgnoreCase(".encrypted"))
/*     */     {
/* 386 */       if (trimmedString.equalsIgnoreCase("no")) {
/* 387 */         this.passwordsProtected = false;
/*     */       }
/*     */     }
/* 390 */     if (key.substring(prefix.length()).equalsIgnoreCase(".keystore_pass"))
/*     */     {
/* 392 */       if (getKeyStorePassword().getProtectionType() == PasswordObject.PasswordType.NULL || override) {
/* 393 */         this.keyStorePassword = new PasswordObject(trimmedString.toCharArray());
/*     */       }
/*     */     }
/* 396 */     if (key.substring(prefix.length()).equalsIgnoreCase(".key_pass"))
/*     */     {
/* 398 */       if (getPrivKeyPassword().getProtectionType() == PasswordObject.PasswordType.NULL || override) {
/* 399 */         this.privKeyPassword = new PasswordObject(trimmedString.toCharArray());
/*     */       }
/*     */     }
/* 402 */     if ((getProvider() == null || override) && 
/* 403 */       key.substring(prefix.length()).equalsIgnoreCase(".provider"))
/*     */     {
/* 405 */       this.provider = trimmedString;
/*     */     }
/*     */ 
/*     */     
/* 409 */     if (key.substring(prefix.length()).equalsIgnoreCase(".token_pin"))
/*     */     {
/* 411 */       if (getKeyStorePassword().getProtectionType() == PasswordObject.PasswordType.NULL || override) {
/* 412 */         this.keyStorePassword = new PasswordObject(trimmedString.toCharArray());
/*     */       }
/*     */     }
/*     */     
/* 416 */     if ((getSecondaryKeyStorePath() == null || override) && 
/* 417 */       key.substring(prefix.length()).equalsIgnoreCase(".secondary_keystore"))
/*     */     {
/* 419 */       this.secondaryKeyStorePath = trimmedString;
/*     */     }
/*     */ 
/*     */     
/* 423 */     if ((getSecondaryKeyStorePass().getProtectionType() == PasswordObject.PasswordType.NULL || override) && 
/* 424 */       key.substring(prefix.length()).equalsIgnoreCase(".secondary_keystore_pass"))
/*     */     {
/* 426 */       this.secondaryKeyStorePass = new PasswordObject(trimmedString.toCharArray());
/*     */     }
/*     */     
/* 429 */     if (key.substring(prefix.length()).equalsIgnoreCase(".keyfile") && (
/* 430 */       getInitialKeyfile() == null || override)) {
/* 431 */       this.initialkeylocation = trimmedString;
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
/*     */   public Object[] validate() {
/* 443 */     List<String> list = new ArrayList<>();
/* 444 */     String providerCopy = (this.provider == null) ? "" : this.provider;
/* 445 */     boolean isPKCS11 = false;
/* 446 */     if (isInvalidKeystoreType()) {
/* 447 */       list.add("type");
/*     */     }
/*     */ 
/*     */     
/* 451 */     if (!isEmptyString(this.type) && this.type
/* 452 */       .equalsIgnoreCase("PKCS11")) {
/* 453 */       isPKCS11 = true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 458 */     if ("JCERACFKS".equals(this.type) && 
/* 459 */       !isEmptyString(providerCopy) && !providerCopy.equalsIgnoreCase("IBMJCE")) {
/* 460 */       list.add(".provider");
/*     */     }
/*     */     
/* 463 */     if (!isPKCS11 && isInvalidKeystorePath(providerCopy)) {
/* 464 */       list.add(".keystore");
/*     */     }
/* 466 */     if (isEmptyString(this.alias)) {
/* 467 */       list.add(".certificate");
/*     */     }
/*     */     
/* 470 */     if (!"JCERACFKS".equals(this.type) && isEmptyString(this.keyStorePassword.getPassword())) {
/* 471 */       if (this.type != null && isPKCS11) {
/* 472 */         list.add(".token_pin");
/*     */       } else {
/* 474 */         list.add(".keystore_pass");
/*     */       } 
/*     */     }
/* 477 */     if (!isEmptyString(this.keyStorePassword.getPassword()) && containsNonAscii(this.keyStorePassword.getPassword())) {
/* 478 */       AmsErrorMessages.log(CLASS, "validate()", AmsErrorMessages.mju_keystore_password_not_ascii);
/* 479 */       list.add(".keystore_pass");
/*     */     } 
/* 481 */     if (!isPKCS11 && isInvalidPrivateKeyPassword(providerCopy)) {
/* 482 */       list.add(".key_pass");
/*     */     }
/* 484 */     this.pkiConfig.validate(list);
/* 485 */     return list.toArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean containsNonAscii(char[] chars) {
/* 495 */     if (chars != null) {
/* 496 */       for (int i = 0; i < chars.length; i++) {
/* 497 */         if (chars[i] > '') {
/* 498 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 502 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isEmptyString(String s) {
/* 512 */     return (s == null || s.length() == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isEmptyString(char[] arr) {
/* 517 */     return (arr == null || arr.length == 0);
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
/*     */   private boolean isInvalidPrivateKeyPassword(String providerCopy) {
/* 532 */     return (isEmptyString(this.privKeyPassword.getPassword()) && 
/* 533 */       !providerCopy.toUpperCase().contains("PKCS11") && 
/* 534 */       !"JCERACFKS".equals(this.type));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isPathValidKeyring() {
/* 541 */     String[] uri = (this.keyStorePath == null) ? null : this.keyStorePath.split("://", 3);
/* 542 */     String[] kr = (uri != null && uri.length == 2 && uri[0].equalsIgnoreCase("safkeyring")) ? uri[1].split("/", 3) : null;
/* 543 */     boolean retval = (kr != null && kr.length == 2 && !isEmptyString(kr[1]));
/*     */     
/* 545 */     if (Trace.isOn)
/* 546 */       Trace.data(this, "com.ibm.mq.ese.config.KeyStoreConfig", "isPathValidKeyring()", this.keyStorePath, Boolean.valueOf(retval)); 
/* 547 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isInvalidKeystorePath(String providerCopy) {
/* 558 */     return ((isEmptyString(this.keyStorePath) && !providerCopy.toUpperCase().contains("PKCS11")) || ("JCERACFKS"
/* 559 */       .equals(this.type) && !isPathValidKeyring()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isInvalidKeystoreType() {
/* 567 */     return (isEmptyString(this.type) || (
/* 568 */       !this.type.equalsIgnoreCase("JCEKS") && 
/* 569 */       !this.type.equals("PKCS11") && 
/* 570 */       !this.type.equalsIgnoreCase("JKS") && (
/* 571 */       !this.type.equals("JCERACFKS") || JmqiEnvironment.getOperatingSystem() != JmqiEnvironment.OS_ZSERIES)));
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
/*     */   public String toString() {
/* 583 */     return this.type + "#" + this.keyStorePath + "#" + this.alias;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/* 588 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getProvider() {
/* 593 */     return this.provider;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getKeyStorePath() {
/* 598 */     return this.keyStorePath;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAlias() {
/* 603 */     return this.alias;
/*     */   }
/*     */ 
/*     */   
/*     */   public PasswordObject getKeyStorePassword() {
/* 608 */     return this.keyStorePassword;
/*     */   }
/*     */ 
/*     */   
/*     */   public PasswordObject getPrivKeyPassword() {
/* 613 */     return this.privKeyPassword;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPasswordsProtected() {
/* 618 */     return this.passwordsProtected;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setKeyStorePassword(PasswordObject keyStorePassword) {
/* 623 */     this.keyStorePassword.replace(keyStorePassword);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrivKeyPassword(PasswordObject privKeyPassword) {
/* 628 */     this.privKeyPassword.replace(privKeyPassword);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPasswordsProtected(boolean passwordsProtected) {
/* 633 */     this.passwordsProtected = passwordsProtected;
/*     */   }
/*     */   
/*     */   public String getInitialKeyfile() {
/* 637 */     return this.initialkeylocation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cleanUp() {
/* 645 */     if (Trace.isOn) {
/* 646 */       Trace.entry(this, "com.ibm.mq.ese.config.KeyStoreConfig", "cleanUp()");
/*     */     }
/* 648 */     if (this.keyStorePassword != null) {
/* 649 */       this.keyStorePassword.wipeData();
/*     */     }
/* 651 */     if (this.privKeyPassword != null) {
/* 652 */       this.privKeyPassword.wipeData();
/*     */     }
/* 654 */     this.initialized = false;
/* 655 */     if (Trace.isOn) {
/* 656 */       Trace.exit(this, "com.ibm.mq.ese.config.KeyStoreConfig", "cleanUp()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInitialized() {
/* 665 */     return this.initialized;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSecondaryKeyStorePath() {
/* 670 */     return this.secondaryKeyStorePath;
/*     */   }
/*     */ 
/*     */   
/*     */   public PasswordObject getSecondaryKeyStorePass() {
/* 675 */     return this.secondaryKeyStorePass;
/*     */   }
/*     */ 
/*     */   
/*     */   public PkiConfig getPkiConfig() {
/* 680 */     return this.pkiConfig;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ValidateAllPasswords() throws ConfigException {
/* 691 */     if (Trace.isOn) {
/* 692 */       Trace.entry(this, "com.ibm.mq.ese.config.KeyStoreConfig", "ValidateAllPasswords()");
/*     */     }
/*     */ 
/*     */     
/* 696 */     if (this.keyStorePassword != null && this.keyStorePassword.getProtectionType() != PasswordObject.PasswordType.NULL) {
/* 697 */       ValidateAllPasswordsEx(this.keyStorePassword, ".keystore_pass");
/*     */     }
/*     */     
/* 700 */     if (this.privKeyPassword != null && this.privKeyPassword.getProtectionType() != PasswordObject.PasswordType.NULL) {
/* 701 */       ValidateAllPasswordsEx(this.privKeyPassword, ".key_pass");
/*     */     }
/*     */     
/* 704 */     if (this.secondaryKeyStorePass != null && this.secondaryKeyStorePass.getProtectionType() != PasswordObject.PasswordType.NULL) {
/* 705 */       ValidateAllPasswordsEx(this.secondaryKeyStorePass, ".secondary_keystore_pass");
/*     */     }
/*     */     
/* 708 */     if (!this.supressPasswordWarnings && AbstractKeyStoreAccess.usingDefaultKey()) {
/*     */       
/* 710 */       HashMap<String, Object> inserts = new HashMap<>();
/* 711 */       AmsErrorMessages.log(CLASS, "com.ibm.mq.ese.config.KeyStoreConfig", AmsErrorMessages.mju_default_key_used, inserts);
/*     */     } 
/*     */     
/* 714 */     if (Trace.isOn) {
/* 715 */       Trace.exit(this, "com.ibm.mq.ese.config.KeyStoreConfig", "ValidateAllPasswords()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void ValidateAllPasswordsEx(PasswordObject toConfigure, String field) throws ConfigException {
/* 721 */     String temppw = new String(toConfigure.getPassword());
/* 722 */     if (temppw.startsWith("<AMS>"))
/*     */     
/* 724 */     { toConfigure.setProtectionType(PasswordObject.PasswordType.NEWPROTECTED); }
/* 725 */     else { if (temppw.contains("<")) {
/*     */         
/* 727 */         HashMap<String, String> inserts = new HashMap<>();
/* 728 */         inserts.put("AMS_INSERT_VARIABLE_NAME", field);
/* 729 */         inserts.put("AMS_INSERT_EYECATCHER", "<AMS>");
/* 730 */         ConfigException thrown = new ConfigException(AmsErrorMessages.mjp_password_incorrect_format, (HashMap)inserts);
/* 731 */         if (Trace.isOn) {
/* 732 */           Trace.throwing(this, "com.ibm.mq.ese.config.KeyStoreConfig", "ValidateAllPasswords()", (Throwable)thrown);
/*     */         }
/* 734 */         throw thrown;
/*     */       } 
/*     */       
/* 737 */       toConfigure.setProtectionType(PasswordObject.PasswordType.OLDPROTECTED); }
/*     */ 
/*     */     
/* 740 */     temppw = temppw.replaceAll(Pattern.quote("\\r\\n"), "\r\n");
/* 741 */     temppw = temppw.replaceAll(Pattern.quote("\\="), "=");
/* 742 */     temppw = temppw.replaceAll(Pattern.quote("\\!"), "!");
/* 743 */     toConfigure.setPassword(temppw.toCharArray());
/*     */ 
/*     */     
/* 746 */     if (!AbstractKeyStoreAccess.testDecrypt(toConfigure)) {
/* 747 */       HashMap<String, String> inserts = new HashMap<>();
/* 748 */       inserts.put("AMS_INSERT_VARIABLE_NAME", field);
/* 749 */       ConfigException thrown = new ConfigException(AmsErrorMessages.mjp_password_decryption_failure, (HashMap)inserts);
/* 750 */       if (Trace.isOn) {
/* 751 */         Trace.throwing(this, "com.ibm.mq.ese.config.KeyStoreConfig", "ValidateAllPasswords()", (Throwable)thrown);
/*     */       }
/* 753 */       throw thrown;
/*     */     } 
/* 755 */     if (Trace.isOn) {
/* 756 */       Trace.data("com.ibm.mq.ese.config.KeyStoreConfig", "ValidateAllPasswords()", field + " - OK!");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPrefix() {
/* 764 */     switch (this.type) {
/*     */       case "JCEKS":
/* 766 */         return "JCEKS";
/*     */       
/*     */       case "JKS":
/* 769 */         return "JKS";
/*     */       
/*     */       case "JCERACFKS":
/* 772 */         return "JCERACFKS";
/*     */       case "PKCS11":
/* 774 */         return "PKCS11";
/*     */     } 
/* 776 */     return "";
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\config\KeyStoreConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */