/*     */ package com.ibm.mq.ese.config;
/*     */ 
/*     */ import com.ibm.mq.ese.core.AMBIException;
/*     */ import com.ibm.mq.ese.core.KeyStoreAccess;
/*     */ import com.ibm.mq.ese.core.Lifecycle;
/*     */ import com.ibm.mq.ese.core.SecurityProvider;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.mq.ese.pki.AbstractKeyStoreAccess;
/*     */ import com.ibm.mq.ese.pki.CompositeKeyStoreAccess;
/*     */ import com.ibm.mq.ese.pki.KeyStoreAccessFactory;
/*     */ import com.ibm.mq.ese.util.ConfFile;
/*     */ import com.ibm.mq.ese.util.DuplicateKeyException;
/*     */ import com.ibm.msg.client.commonservices.nls.NLSServices;
/*     */ import com.ibm.msg.client.commonservices.passwordprotection.PBEException;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
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
/*     */ public class KeyStoreConfigProtector
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/config/KeyStoreConfigProtector.java";
/*     */   
/*     */   static {
/*  64 */     if (Trace.isOn) {
/*  65 */       Trace.data("com.ibm.mq.ese.config.KeyStoreConfigProtector", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/config/KeyStoreConfigProtector.java");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*  70 */   private static String keystoreConfigPath = "";
/*  71 */   private static String initialkeyfile = "";
/*  72 */   private static File initialkey = null;
/*  73 */   private static String prefix = "";
/*  74 */   private static int algorithm = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.entry("com.ibm.mq.ese.config.KeyStoreConfigProtector", "main(String [ ])", new Object[] { args });
/*     */     }
/*  88 */     int rc = 0;
/*     */     try {
/*  90 */       readArgs(args);
/*  91 */     } catch (Exception e) {
/*  92 */       System.out.println(e.getMessage());
/*  93 */       rc = 1;
/*     */     } 
/*  95 */     if (rc == 0) {
/*     */       try {
/*  97 */         if (initialkeyfile.length() != 0) {
/*     */           
/*  99 */           initialkey = new File(initialkeyfile);
/* 100 */           if (!initialkey.exists() || !initialkey.isFile() || !initialkey.canRead()) {
/* 101 */             HashMap<String, String> inserts = new HashMap<>();
/* 102 */             inserts.put("AMS_INITIAL_KEYFILE", initialkey.getAbsolutePath());
/* 103 */             ConfigException thrown = new ConfigException(AmsErrorMessages.mjp_bad_initial_keyfile, (HashMap)inserts);
/* 104 */             if (Trace.isOn) {
/* 105 */               Trace.throwing("com.ibm.mq.ese.config.KeyStoreConfigProtector", "main(String [ ])", (Throwable)thrown, 1);
/*     */             }
/* 107 */             throw thrown;
/*     */           } 
/*     */         } 
/*     */         
/* 111 */         if (rc == 0) {
/* 112 */           rc = protectConfig();
/*     */         }
/*     */       }
/* 115 */       catch (Exception e) {
/* 116 */         if (Trace.isOn) {
/* 117 */           Trace.catchBlock("com.ibm.mq.ese.config.KeyStoreConfigProtector", "main(String [ ])", e, 3);
/*     */         }
/* 119 */         HashMap<String, String> inserts = new HashMap<>();
/* 120 */         inserts.put("AMS_INSERT_FILENAME", keystoreConfigPath);
/* 121 */         ConfigException ex = new ConfigException(AmsErrorMessages.mju_password_protection_unexpected_error, (HashMap)inserts);
/* 122 */         System.out.println(ex.getMessage());
/* 123 */         System.out.println(e.getMessage());
/* 124 */         rc = 1;
/*     */       } 
/*     */     }
/*     */     
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.exit("com.ibm.mq.ese.config.KeyStoreConfigProtector", "main(String [ ])", rc);
/*     */     }
/* 131 */     System.exit(rc);
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
/*     */   private static char[] readSecurelyFromSTDIN(String prompt) throws IOException {
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.entry("com.ibm.mq.ese.config.KeyStoreConfigProtector", "readSecurelyFromSTDIN(String)", prompt);
/*     */     }
/* 149 */     char[] returned = null;
/* 150 */     if (System.console() != null) {
/* 151 */       if (Trace.isOn) {
/* 152 */         Trace.data("com.ibm.mq.ese.config.KeyStoreConfigProtector", "readSecurelyFromSTDIN(String)", "Reading via System.Console");
/*     */       }
/*     */       
/* 155 */       returned = System.console().readPassword(prompt, new Object[0]);
/*     */     } else {
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.data("com.ibm.mq.ese.config.KeyStoreConfigProtector", "readSecurelyFromSTDIN(String)", "No System.Console available so falling back");
/*     */       }
/*     */       
/* 161 */       System.out.print(prompt);
/* 162 */       BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
/* 163 */       String full = in.readLine();
/* 164 */       returned = full.toCharArray();
/*     */     } 
/*     */     
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit("com.ibm.mq.ese.config.KeyStoreConfigProtector", "readSecurelyFromSTDIN(String)", new Object[] { "********" });
/*     */     }
/*     */     
/* 171 */     return returned;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int protectConfig() {
/* 180 */     if (Trace.isOn) {
/* 181 */       Trace.entry("com.ibm.mq.ese.config.KeyStoreConfigProtector", "protectConfig()");
/*     */     }
/* 183 */     int rc = 0;
/* 184 */     boolean fatalError = true;
/*     */     try {
/* 186 */       KeyStoreConfig keyStoreConfig = readKeystoreConf(keystoreConfigPath);
/* 187 */       if (!"JCEKS".equals(keyStoreConfig.getType()) && 
/* 188 */         !"JKS".equals(keyStoreConfig.getType()) && 
/* 189 */         !"PKCS11".equals(keyStoreConfig.getType())) {
/* 190 */         HashMap<String, String> inserts = new HashMap<>();
/* 191 */         inserts.put("AMS_INSERT_FILENAME", keystoreConfigPath);
/* 192 */         ConfigException ex = new ConfigException(AmsErrorMessages.mju_incorrect_keystore_type_password_protection, (HashMap)inserts);
/*     */         
/* 194 */         if (Trace.isOn) {
/* 195 */           Trace.throwing("com.ibm.mq.ese.config.KeyStoreConfigProtector", "protectConfig()", (Throwable)ex, 1);
/*     */         }
/* 197 */         if ("JCERACFKS".equals(keyStoreConfig.getType())) {
/* 198 */           fatalError = false;
/*     */         }
/* 200 */         throw ex;
/*     */       } 
/*     */       
/* 203 */       if (keyStoreConfig == null || keyStoreConfig.getType() == null) {
/* 204 */         ConfigException ex = new ConfigException(NLSServices.getMessage(AmsErrorMessages.mjc_keystoreprotect_usage));
/* 205 */         if (Trace.isOn) {
/* 206 */           Trace.throwing("com.ibm.mq.ese.config.KeyStoreConfigProtector", "protectConfig()", (Throwable)ex, 1);
/*     */         }
/* 208 */         throw ex;
/*     */       } 
/*     */       
/* 211 */       if (initialkey != null) {
/*     */         
/*     */         try {
/* 214 */           AbstractKeyStoreAccess.overrideInitialKey(initialkey);
/* 215 */         } catch (PBEException e) {
/* 216 */           HashMap<String, String> inserts = new HashMap<>();
/* 217 */           inserts.put("AMS_INITIAL_KEYFILE", initialkey.getAbsolutePath());
/* 218 */           ConfigException thrown = new ConfigException(AmsErrorMessages.mjp_bad_initial_keyfile, (HashMap)inserts, (Throwable)e);
/* 219 */           if (Trace.isOn) {
/* 220 */             Trace.throwing("com.ibm.mq.ese.config.KeyStoreConfigProtector", "main(String [ ])", (Throwable)thrown, 2);
/*     */           }
/* 222 */           throw thrown;
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 227 */       if (keyStoreConfig.isPasswordsProtected()) {
/*     */         
/* 229 */         HashMap<String, String> inserts = new HashMap<>();
/* 230 */         inserts.put("AMS_INSERT_FILENAME", keyStoreConfig.getKeyStorePath());
/* 231 */         ConfigException ex = new ConfigException(AmsErrorMessages.mju_config_already_protected, (HashMap)inserts);
/* 232 */         if (Trace.isOn) {
/* 233 */           Trace.throwing("com.ibm.mq.ese.config.KeyStoreConfigProtector", "protectConfig()", (Throwable)ex, 3);
/*     */         }
/* 235 */         throw ex;
/*     */       } 
/*     */       
/* 238 */       prefix = keyStoreConfig.getPrefix();
/* 239 */       if (prefix.equals("")) {
/* 240 */         ConfigException ex = new ConfigException(AmsErrorMessages.mju_cannot_read_keystore_properties);
/* 241 */         if (Trace.isOn) {
/* 242 */           Trace.throwing("com.ibm.mq.ese.config.KeyStoreConfigProtector", "protectConfig()", (Throwable)ex, 4);
/*     */         }
/* 244 */         throw ex;
/*     */       } 
/*     */       
/* 247 */       if (algorithm == 0) {
/* 248 */         ProtectMode0(keyStoreConfig);
/*     */       } else {
/*     */         
/* 251 */         if (Trace.isOn) {
/* 252 */           Trace.data("com.ibm.mq.ese.config.KeyStoreConfigProtector", "protectConfig()", "Protecting passwords in properties object");
/*     */         }
/*     */         
/* 255 */         Properties replacedProps = new Properties();
/* 256 */         boolean sawEncryptedFlag = false;
/* 257 */         for (Map.Entry<Object, Object> entry : keyStoreConfig.getOriginalProps().entrySet()) {
/* 258 */           String key = entry.getKey().toString();
/* 259 */           String value = entry.getValue().toString();
/* 260 */           String trimmedString = (value == null) ? "" : value.trim();
/*     */           
/* 262 */           if (value != null && value.length() != 0)
/*     */           {
/* 264 */             if (key.equalsIgnoreCase(prefix + ".key_pass")) {
/* 265 */               value = AbstractKeyStoreAccess.encryptPassword(trimmedString.toCharArray(), prefix + ".key_pass");
/*     */             }
/* 267 */             else if (key.equalsIgnoreCase(prefix + ".keystore_pass")) {
/* 268 */               value = AbstractKeyStoreAccess.encryptPassword(trimmedString.toCharArray(), prefix + ".keystore_pass");
/*     */             }
/* 270 */             else if (key.equalsIgnoreCase(prefix + ".token_pin")) {
/* 271 */               value = AbstractKeyStoreAccess.encryptPassword(trimmedString.toCharArray(), prefix + ".token_pin");
/*     */             }
/* 273 */             else if (key.equalsIgnoreCase(prefix + ".encrypted")) {
/* 274 */               value = "yes";
/* 275 */               sawEncryptedFlag = true;
/* 276 */             } else if (key.equalsIgnoreCase(prefix + ".secondary_keystore_pass")) {
/* 277 */               value = AbstractKeyStoreAccess.encryptPassword(trimmedString.toCharArray(), prefix + ".secondary_keystore_pass");
/*     */             } else {
/*     */               continue;
/*     */             } 
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 285 */           replacedProps.put(key, value);
/*     */         } 
/*     */         
/* 288 */         if (Trace.isOn) {
/* 289 */           Trace.data("com.ibm.mq.ese.config.KeyStoreConfigProtector", "protectConfig()", "Writing new keystore configuration file");
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 294 */         writeOutPasswords(replacedProps, !sawEncryptedFlag);
/*     */ 
/*     */         
/* 297 */         if (AbstractKeyStoreAccess.usingDefaultKey()) {
/* 298 */           ConfigException ex = new ConfigException(AmsErrorMessages.mju_default_key_used);
/*     */           
/* 300 */           System.out.println(ex.getMessage());
/*     */         } else {
/* 302 */           HashMap<String, String> inserts = new HashMap<>();
/* 303 */           inserts.put("AMS_INITIAL_KEYFILE", AbstractKeyStoreAccess.getInitialKeyFilePath());
/* 304 */           ConfigException ex = new ConfigException(AmsErrorMessages.mju_keyfile_used_runamscred, (HashMap)inserts);
/* 305 */           System.out.println(ex.getMessage());
/*     */         } 
/*     */       } 
/* 308 */     } catch (AMBIException e) {
/* 309 */       if (Trace.isOn) {
/* 310 */         Trace.catchBlock("com.ibm.mq.ese.config.KeyStoreConfigProtector", "protectConfig()", (Throwable)e, 2);
/*     */       }
/* 312 */       System.out.println(e.getMessage());
/* 313 */       rc = 1;
/* 314 */     } catch (Exception e) {
/* 315 */       if (Trace.isOn) {
/* 316 */         Trace.catchBlock("com.ibm.mq.ese.config.KeyStoreConfigProtector", "protectConfig()", e, 3);
/*     */       }
/* 318 */       HashMap<String, String> inserts = new HashMap<>();
/* 319 */       inserts.put("AMS_INSERT_FILENAME", keystoreConfigPath);
/* 320 */       ConfigException ex = new ConfigException(AmsErrorMessages.mju_password_protection_unexpected_error, (HashMap)inserts);
/* 321 */       System.out.println(ex.getMessage());
/* 322 */       System.out.println(e.getMessage());
/* 323 */       if (fatalError) {
/* 324 */         rc = 2;
/*     */       }
/*     */     } 
/*     */     
/* 328 */     if (rc == 0) {
/*     */       
/* 330 */       HashMap<String, String> inserts = new HashMap<>();
/* 331 */       inserts.put("AMS_INSERT_FILENAME", keystoreConfigPath);
/* 332 */       ConfigException ex = new ConfigException(AmsErrorMessages.mju_password_protection_complete, (HashMap)inserts);
/* 333 */       System.out.println(ex.getMessage());
/*     */     } 
/*     */     
/* 336 */     if (Trace.isOn) {
/* 337 */       Trace.exit("com.ibm.mq.ese.config.KeyStoreConfigProtector", "protectConfig()", rc);
/*     */     }
/* 339 */     return rc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeOutPasswords(Properties replacedProps, boolean addEncryptedFlag) throws IOException {
/* 350 */     if (Trace.isOn) {
/* 351 */       Trace.entry("com.ibm.mq.ese.config.KeyStoreConfigProtector", "writeOutPasswords(Properties)", "********");
/*     */     }
/* 353 */     File toAdjust = new File(keystoreConfigPath);
/* 354 */     BufferedReader bfre = null;
/* 355 */     FileOutputStream filewriter = null;
/*     */     try {
/* 357 */       bfre = new BufferedReader(new FileReader(toAdjust));
/* 358 */       StringBuffer newOutput = new StringBuffer();
/*     */       String line;
/* 360 */       while ((line = bfre.readLine()) != null) {
/*     */         
/* 362 */         for (Map.Entry<Object, Object> entry : replacedProps.entrySet()) {
/* 363 */           String key = entry.getKey().toString();
/* 364 */           if (line.toLowerCase().startsWith(key.toLowerCase())) {
/* 365 */             if (Trace.isOn) {
/* 366 */               Trace.data("com.ibm.mq.ese.config.KeyStoreConfigProtector", "writeOutPasswords(Properties)", "Replacing " + key);
/*     */             }
/*     */ 
/*     */             
/* 370 */             String value = entry.getValue().toString();
/* 371 */             String trimmedString = (value == null) ? "" : value.trim();
/* 372 */             line = key + '=' + trimmedString;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */         
/* 378 */         newOutput.append(line);
/* 379 */         newOutput.append('\n');
/*     */       } 
/*     */       
/* 382 */       bfre.close();
/* 383 */       bfre = null;
/*     */       
/* 385 */       if (addEncryptedFlag) {
/* 386 */         newOutput.append(prefix + ".encrypted" + "=" + "yes" + "\n");
/*     */       }
/*     */       
/* 389 */       if (Trace.isOn) {
/* 390 */         Trace.data("com.ibm.mq.ese.config.KeyStoreConfigProtector", "writeOutPasswords(Properties)", "New file constructed, writing out");
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 395 */       filewriter = new FileOutputStream(toAdjust);
/* 396 */       filewriter.write(newOutput.toString().getBytes());
/*     */     }
/* 398 */     catch (Exception e) {
/*     */       
/* 400 */       if (Trace.isOn) {
/* 401 */         Trace.catchBlock("com.ibm.mq.ese.config.KeyStoreConfigProtector", "writeOutPasswords(Properties)", e, 1);
/*     */       }
/* 403 */       throw e;
/*     */     } finally {
/* 405 */       if (Trace.isOn) {
/* 406 */         Trace.finallyBlock("com.ibm.mq.ese.config.KeyStoreConfigProtector", "writeOutPasswords(Properties)", 1);
/*     */       }
/* 408 */       if (bfre != null) {
/*     */         try {
/* 410 */           bfre.close();
/* 411 */         } catch (IOException iOException) {}
/*     */       }
/*     */ 
/*     */       
/* 415 */       if (filewriter != null) {
/*     */         try {
/* 417 */           filewriter.close();
/* 418 */         } catch (IOException iOException) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.exit("com.ibm.mq.ese.config.KeyStoreConfigProtector", "writeOutPasswords(Properties)");
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
/*     */   private static void ProtectMode0(KeyStoreConfig keyStoreConfig) throws AMBIException, IOException {
/* 437 */     if (Trace.isOn) {
/* 438 */       Trace.entry("com.ibm.mq.ese.config.KeyStoreConfigProtector", "ProtectMode0(KeyStoreConfig)");
/*     */     }
/* 440 */     SecurityProvider.init();
/*     */     
/* 442 */     KeyStoreAccess ks = KeyStoreAccessFactory.getInstance(keyStoreConfig);
/* 443 */     if (ks == null) {
/* 444 */       HashMap<String, String> inserts = new HashMap<>();
/* 445 */       inserts.put("AMS_INSERT_FILENAME", keystoreConfigPath);
/* 446 */       ConfigException ex = new ConfigException(AmsErrorMessages.mju_incorrect_keystore_type_password_protection, (HashMap)inserts);
/*     */       
/* 448 */       if (Trace.isOn) {
/* 449 */         Trace.throwing("com.ibm.mq.ese.config.KeyStoreConfigProtector", "ProtectMode0(KeyStoreConfig)", (Throwable)ex, 1);
/*     */       }
/* 451 */       throw ex;
/*     */     } 
/*     */ 
/*     */     
/* 455 */     char[] ksPassCopy = new char[(keyStoreConfig.getKeyStorePassword().getPassword()).length];
/* 456 */     System.arraycopy(keyStoreConfig.getKeyStorePassword().getPassword(), 0, ksPassCopy, 0, ksPassCopy.length);
/*     */     
/* 458 */     if (ks instanceof Lifecycle) {
/* 459 */       ((Lifecycle)ks).init();
/*     */     }
/*     */     
/* 462 */     AbstractKeyStoreAccess impl = (AbstractKeyStoreAccess)ks;
/* 463 */     if (ks instanceof CompositeKeyStoreAccess) {
/* 464 */       KeyStoreAccess primaryKeyStore = ((CompositeKeyStoreAccess)ks).getPrimaryKeyStoreAccess();
/* 465 */       if (!(primaryKeyStore instanceof com.ibm.mq.ese.pki.KeyStoreAccessPKCS11Impl)) {
/* 466 */         HashMap<String, String> inserts = new HashMap<>();
/* 467 */         inserts.put("AMS_INSERT_FILENAME", keystoreConfigPath);
/* 468 */         ConfigException ex = new ConfigException(AmsErrorMessages.mju_incorrect_keystore_type_password_protection, (HashMap)inserts);
/*     */         
/* 470 */         if (Trace.isOn) {
/* 471 */           Trace.throwing("com.ibm.mq.ese.config.KeyStoreConfigProtector", "ProtectMode0(KeyStoreConfig)", (Throwable)ex, 2);
/*     */         }
/* 473 */         throw ex;
/*     */       } 
/*     */       
/* 476 */       impl = (AbstractKeyStoreAccess)primaryKeyStore;
/*     */     } 
/*     */     
/* 479 */     if (Trace.isOn) {
/* 480 */       Trace.data("com.ibm.mq.ese.config.KeyStoreConfigProtector", "writeOutPasswords(Properties)", "Configuration setup, encrypting passwords");
/*     */     }
/*     */ 
/*     */     
/* 484 */     String encKsPwd = impl.encryptPasswordOld(ksPassCopy);
/* 485 */     String encPrivPwd = impl.encryptPasswordOld(keyStoreConfig.getPrivKeyPassword().getPassword());
/* 486 */     String sec = null;
/*     */     
/* 488 */     char[] secondaryKeyStorePass = null;
/* 489 */     if (ks instanceof com.ibm.mq.ese.pki.KeyStoreAccessPKCS11Impl) {
/* 490 */       secondaryKeyStorePass = keyStoreConfig.getSecondaryKeyStorePass().getPassword();
/* 491 */       if (secondaryKeyStorePass != null) {
/* 492 */         sec = impl.encryptPasswordOld(secondaryKeyStorePass);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 497 */     if (Trace.isOn) {
/* 498 */       Trace.data("com.ibm.mq.ese.config.KeyStoreConfigProtector", "protectConfig()", "Protecting passwords in properties object");
/*     */     }
/*     */ 
/*     */     
/* 502 */     Properties replacedProps = new Properties();
/* 503 */     boolean sawEncryptedFlag = false;
/* 504 */     for (Map.Entry<Object, Object> entry : keyStoreConfig.getOriginalProps().entrySet()) {
/* 505 */       String key = entry.getKey().toString();
/* 506 */       String value = entry.getValue().toString();
/*     */       
/* 508 */       if (value != null && value.length() != 0)
/*     */       {
/* 510 */         if (key.equalsIgnoreCase(prefix + ".key_pass")) {
/* 511 */           value = encPrivPwd;
/* 512 */         } else if (key.equalsIgnoreCase(prefix + ".keystore_pass")) {
/* 513 */           value = encKsPwd;
/* 514 */         } else if (key.equalsIgnoreCase(prefix + ".token_pin")) {
/* 515 */           value = encKsPwd;
/* 516 */         } else if (key.equalsIgnoreCase(prefix + ".encrypted")) {
/* 517 */           value = "yes";
/* 518 */           sawEncryptedFlag = true;
/* 519 */         } else if (key.equalsIgnoreCase(prefix + ".secondary_keystore_pass")) {
/* 520 */           if (sec != null) {
/* 521 */             value = sec;
/*     */           }
/*     */         } else {
/*     */           continue;
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 529 */       replacedProps.put(key, value);
/*     */     } 
/*     */     
/* 532 */     if (Trace.isOn) {
/* 533 */       Trace.data("com.ibm.mq.ese.config.KeyStoreConfigProtector", "protectConfig()", "Writing new keystore configuration file");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 538 */     writeOutPasswords(replacedProps, !sawEncryptedFlag);
/*     */     
/* 540 */     if (Trace.isOn) {
/* 541 */       Trace.exit("com.ibm.mq.ese.config.KeyStoreConfigProtector", "ProtectMode0(KeyStoreConfig)");
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
/*     */   private static KeyStoreConfig readKeystoreConf(String pathToKeystore) throws ConfigException {
/*     */     KeyStoreConfig keyStoreConfig;
/* 556 */     if (Trace.isOn) {
/* 557 */       Trace.entry("com.ibm.mq.ese.config.KeyStoreConfigProtector", "readKeystoreConf(String)", pathToKeystore);
/*     */     }
/*     */     
/* 560 */     FileInputStream fis = null;
/*     */     
/*     */     try {
/* 563 */       File path = new File(pathToKeystore);
/* 564 */       fis = new FileInputStream(path);
/* 565 */       ConfFile confFile = new ConfFile();
/* 566 */       confFile.load(fis);
/* 567 */       keyStoreConfig = new KeyStoreConfig((Properties)confFile, true);
/* 568 */     } catch (DuplicateKeyException e) {
/* 569 */       if (Trace.isOn) {
/* 570 */         Trace.catchBlock("com.ibm.mq.ese.config.KeyStoreConfigProtector", "readKeystoreConf(String)", (Throwable)e, 1);
/*     */       }
/* 572 */       HashMap<String, String> inserts = new HashMap<>();
/* 573 */       inserts.put("AMS_INSERT_CONFIG_KEY", e.getKey());
/* 574 */       ConfigException ex = new ConfigException(AmsErrorMessages.mqo_s_usermap_error_duplicate_key, (HashMap)inserts, (Throwable)e);
/* 575 */       if (Trace.isOn) {
/* 576 */         Trace.throwing("com.ibm.mq.ese.config.KeyStoreConfigProtector", "readKeystoreConf(String)", (Throwable)ex, 1);
/*     */       }
/* 578 */       throw ex;
/* 579 */     } catch (IOException e) {
/* 580 */       if (Trace.isOn) {
/* 581 */         Trace.catchBlock("com.ibm.mq.ese.config.KeyStoreConfigProtector", "readKeystoreConf(String)", e, 2);
/*     */       }
/* 583 */       ConfigException ex = new ConfigException(AmsErrorMessages.mju_cannot_read_keystore_properties, e);
/* 584 */       if (Trace.isOn) {
/* 585 */         Trace.throwing("com.ibm.mq.ese.config.KeyStoreConfigProtector", "readKeystoreConf(String)", (Throwable)ex, 2);
/*     */       }
/* 587 */       throw ex;
/*     */     } finally {
/* 589 */       if (Trace.isOn) {
/* 590 */         Trace.finallyBlock("com.ibm.mq.ese.config.KeyStoreConfigProtector", "readKeystoreConf(String)", 1);
/*     */       }
/* 592 */       if (fis != null) {
/*     */         try {
/* 594 */           fis.close();
/* 595 */         } catch (IOException e) {
/* 596 */           if (Trace.isOn) {
/* 597 */             Trace.catchBlock("com.ibm.mq.ese.config.KeyStoreConfigProtector", "readKeystoreConf(String)", e, 3);
/*     */           }
/*     */           
/* 600 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 605 */     if (Trace.isOn) {
/* 606 */       Trace.exit("com.ibm.mq.ese.config.KeyStoreConfigProtector", "readKeystoreConf(String)", keyStoreConfig);
/*     */     }
/* 608 */     return keyStoreConfig;
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
/*     */   private static void readArgs(String[] args) throws AMBIException {
/* 620 */     if (Trace.isOn) {
/* 621 */       Trace.entry("com.ibm.mq.ese.config.KeyStoreConfigProtector", "readArgs(String[])", (Object[])args);
/*     */     }
/*     */     try {
/* 624 */       int length = args.length;
/* 625 */       if (length == 0) {
/* 626 */         throw new Exception("No args supplied to read");
/*     */       }
/*     */       
/* 629 */       int i = 0;
/* 630 */       while (i < length) {
/* 631 */         char opt2; if (args[i].charAt(0) != '-') {
/* 632 */           throw new Exception("Missing - for flag");
/*     */         }
/*     */         
/* 635 */         char opt = args[i].toLowerCase().charAt(1);
/*     */         
/* 637 */         switch (opt) {
/*     */           case 'h':
/* 639 */             throw new Exception();
/*     */           case 'f':
/* 641 */             if (args[i].length() < 3) {
/*     */               
/* 643 */               keystoreConfigPath = args[++i]; break;
/*     */             } 
/* 645 */             throw new Exception("Unknown flag" + args[i].toLowerCase());
/*     */ 
/*     */ 
/*     */           
/*     */           case 's':
/* 650 */             if (args[i].length() < 3)
/*     */             {
/* 652 */               throw new Exception("Unknown flag" + args[i].toLowerCase());
/*     */             }
/* 654 */             opt2 = args[i].toLowerCase().charAt(2);
/* 655 */             switch (opt2) {
/*     */               case 'f':
/* 657 */                 initialkeyfile = args[++i];
/*     */                 break;
/*     */               case 'p':
/* 660 */                 algorithm = Integer.parseInt(args[++i]);
/*     */                 break;
/*     */             } 
/* 663 */             throw new Exception("Unknown flag" + args[i].toLowerCase());
/*     */ 
/*     */ 
/*     */           
/*     */           default:
/* 668 */             throw new Exception("Unknown flag" + args[i].toLowerCase());
/*     */         } 
/* 670 */         i++;
/*     */       } 
/*     */       
/* 673 */       if (keystoreConfigPath == null || keystoreConfigPath.trim().equals("") || keystoreConfigPath.length() == 0) {
/* 674 */         throw new Exception("Keystore config path is missing");
/*     */       }
/* 676 */       if (algorithm < 0 || algorithm > 2) {
/* 677 */         throw new Exception("Algorithm is invalid");
/*     */       }
/*     */     }
/* 680 */     catch (Exception e) {
/* 681 */       if (Trace.isOn) {
/* 682 */         Trace.catchBlock("com.ibm.mq.ese.config.KeyStoreConfigProtector", "readArgs(String[])", e, 1);
/*     */       }
/* 684 */       ConfigException ex = new ConfigException(AmsErrorMessages.mjc_keystoreprotect_usage);
/* 685 */       if (Trace.isOn) {
/* 686 */         Trace.throwing("com.ibm.mq.ese.config.KeyStoreConfigProtector", "readArgs(String [ ])", (Throwable)ex, 1);
/*     */       }
/* 688 */       throw ex;
/*     */     } 
/* 690 */     if (Trace.isOn)
/* 691 */       Trace.exit("com.ibm.mq.ese.config.KeyStoreConfigProtector", "readArgs(String [ ])"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\config\KeyStoreConfigProtector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */