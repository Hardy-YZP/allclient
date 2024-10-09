/*     */ package com.ibm.mq.ese.service;
/*     */ 
/*     */ import com.ibm.mq.ese.config.ConfigException;
/*     */ import com.ibm.mq.ese.config.KeyStoreConfig;
/*     */ import com.ibm.mq.ese.core.AMBIException;
/*     */ import com.ibm.mq.ese.core.EseUser;
/*     */ import com.ibm.mq.ese.core.KeyStoreAccess;
/*     */ import com.ibm.mq.ese.core.Lifecycle;
/*     */ import com.ibm.mq.ese.core.X500NameWrapper;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.mq.ese.pki.KeyStoreAccessFactory;
/*     */ import com.ibm.mq.ese.pki.MissingCertificateException;
/*     */ import com.ibm.mq.ese.util.ConfFile;
/*     */ import com.ibm.mq.ese.util.DuplicateKeyException;
/*     */ import com.ibm.mq.ese.util.PathResolver;
/*     */ import com.ibm.mq.ese.util.TraceUtil;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.HashMap;
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
/*     */ public class UserMapServiceImpl
/*     */   implements UserMapService
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/UserMapServiceImpl.java";
/*     */   
/*     */   static {
/*  63 */     if (Trace.isOn) {
/*  64 */       Trace.data("com.ibm.mq.ese.service.UserMapServiceImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/service/UserMapServiceImpl.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   private static final String CLASS = UserMapServiceImpl.class.getName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExternalUsername() throws UserMapException {
/*  79 */     String ret = getStandaloneAppUserName();
/*     */     
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.data(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "getExternalUsername()", "getter", ret);
/*     */     }
/*     */     
/*  85 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getStandaloneAppUserName() throws UserMapException {
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.entry(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "getStandaloneAppUserName()");
/*     */     }
/*     */     
/*     */     try {
/*  99 */       String userName = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*     */           {
/*     */             public String run()
/*     */             {
/* 103 */               if (Trace.isOn) {
/* 104 */                 Trace.entry(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "run()");
/*     */               }
/* 106 */               String traceRet1 = System.getProperty("user.name");
/* 107 */               if (Trace.isOn) {
/* 108 */                 Trace.exit(this, "com.ibm.mq.ese.service.null", "run()", traceRet1);
/*     */               }
/* 110 */               return traceRet1;
/*     */             }
/*     */           });
/* 113 */       if (userName == null) {
/* 114 */         HashMap<String, Object> inserts = new HashMap<>();
/* 115 */         inserts.put("AMS_INSERT_PROPERTY_KEY", "user.name");
/* 116 */         UserMapException ex = new UserMapException(AmsErrorMessages.mju_cfg_ambi_cfg_err_getting_system_properties, inserts);
/* 117 */         if (Trace.isOn) {
/* 118 */           Trace.throwing(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "getStandaloneAppUserName()", (Throwable)ex, 1);
/*     */         }
/*     */         
/* 121 */         throw ex;
/*     */       } 
/* 123 */       if (Trace.isOn) {
/* 124 */         Trace.exit(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "getStandaloneAppUserName()", userName);
/*     */       }
/*     */       
/* 127 */       return userName;
/*     */     }
/* 129 */     catch (SecurityException e) {
/* 130 */       if (Trace.isOn) {
/* 131 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "getStandaloneAppUserName()", e);
/*     */       }
/*     */       
/* 134 */       HashMap<String, Object> inserts = new HashMap<>();
/* 135 */       inserts.put("AMS_INSERT_PROPERTY_KEY", "user.name");
/* 136 */       UserMapException ex = new UserMapException(AmsErrorMessages.mju_cfg_ambi_cfg_err_getting_system_properties, inserts, e);
/* 137 */       if (Trace.isOn) {
/* 138 */         Trace.throwing(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "getStandaloneAppUserName()", (Throwable)ex, 2);
/*     */       }
/*     */       
/* 141 */       throw ex;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KeyStoreConfig readKeystoreConfig(File path) throws ConfigException {
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.entry(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "readKeystoreConfig(File)", new Object[] { path });
/*     */     }
/*     */ 
/*     */     
/* 155 */     String meth = "readKeystoreConfig";
/* 156 */     FileInputStream fis = null;
/*     */     try {
/* 158 */       fis = new FileInputStream(path);
/* 159 */       ConfFile confFile = new ConfFile();
/* 160 */       confFile.load(fis);
/* 161 */       KeyStoreConfig ksConf = new KeyStoreConfig((Properties)confFile);
/* 162 */       Object[] fields = ksConf.validate();
/* 163 */       if (fields != null && fields.length > 0) {
/* 164 */         HashMap<String, Object> inserts = new HashMap<>();
/* 165 */         inserts.put("AMS_INSERT_PROPERTY_KEY", TraceUtil.join(fields));
/* 166 */         AmsErrorMessages.log(CLASS, meth, AmsErrorMessages.mju_wrong_key, inserts);
/* 167 */         ConfigException ex = new ConfigException(AmsErrorMessages.mju_cannot_read_keystore_properties);
/* 168 */         if (Trace.isOn) {
/* 169 */           Trace.throwing(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "readKeystoreConfig(File)", (Throwable)ex, 1);
/*     */         }
/* 171 */         throw ex;
/*     */       } 
/* 173 */       confFile.clear();
/* 174 */       if (Trace.isOn) {
/* 175 */         Trace.exit(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "readKeystoreConfig(File)", ksConf);
/*     */       }
/* 177 */       return ksConf;
/*     */     }
/* 179 */     catch (DuplicateKeyException e) {
/* 180 */       if (Trace.isOn) {
/* 181 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "readKeystoreConfig(File)", (Throwable)e, 1);
/*     */       }
/*     */       
/* 184 */       HashMap<String, String> inserts = new HashMap<>();
/* 185 */       inserts.put("AMS_INSERT_CONFIG_KEY", e.getKey());
/* 186 */       ConfigException ex = new ConfigException(AmsErrorMessages.mqo_s_usermap_error_duplicate_key, inserts, (Throwable)e);
/* 187 */       if (Trace.isOn) {
/* 188 */         Trace.throwing(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "readKeystoreConfig(File)", (Throwable)ex, 2);
/*     */       }
/* 190 */       throw ex;
/*     */     }
/* 192 */     catch (IOException e) {
/* 193 */       if (Trace.isOn) {
/* 194 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "readKeystoreConfig(File)", e, 2);
/*     */       }
/*     */       
/* 197 */       ConfigException ex = new ConfigException(AmsErrorMessages.mju_cannot_read_keystore_properties, e);
/* 198 */       if (Trace.isOn) {
/* 199 */         Trace.throwing(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "readKeystoreConfig(File)", (Throwable)ex, 3);
/*     */       }
/* 201 */       throw ex;
/*     */     }
/* 203 */     catch (ConfigException e) {
/* 204 */       if (Trace.isOn) {
/* 205 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "readKeystoreConfig(File)", (Throwable)e, 3);
/*     */       }
/*     */       
/* 208 */       ConfigException ex = new ConfigException(AmsErrorMessages.mju_cannot_read_keystore_properties, (Throwable)e);
/* 209 */       if (Trace.isOn) {
/* 210 */         Trace.throwing(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "readKeystoreConfig(File)", (Throwable)ex, 4);
/*     */       }
/* 212 */       throw ex;
/*     */     } finally {
/*     */       
/* 215 */       if (Trace.isOn) {
/* 216 */         Trace.finallyBlock(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "readKeystoreConfig(File)");
/*     */       }
/*     */       
/* 219 */       if (fis != null) {
/*     */         try {
/* 221 */           fis.close();
/*     */         }
/* 223 */         catch (IOException e) {
/* 224 */           if (Trace.isOn) {
/* 225 */             Trace.catchBlock(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "readKeystoreConfig(File)", e, 4);
/*     */           }
/*     */         } 
/*     */       }
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
/*     */   public EseUser getCredentials() throws UserMapException, ConfigException, AMBIException {
/* 241 */     String userName = getExternalUsername();
/* 242 */     EseUser credentials = getCredentials(userName);
/*     */     
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.data(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "getCredentials()", "getter", credentials);
/*     */     }
/*     */     
/* 248 */     return credentials;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EseUser getCredentials(String user) throws ConfigException, AMBIException {
/*     */     KeyStoreConfig keyStoreConfig;
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.entry(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "getCredentials(String)", new Object[] { user });
/*     */     }
/*     */ 
/*     */     
/* 264 */     final File path = PathResolver.getKeystorePath();
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.traceInfo(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "getCredentials(String)", "using keystore configuration: ", path
/*     */           
/* 268 */           .getAbsolutePath());
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 273 */       keyStoreConfig = AccessController.<KeyStoreConfig>doPrivileged(new PrivilegedExceptionAction<KeyStoreConfig>()
/*     */           {
/*     */             public KeyStoreConfig run() throws ConfigException
/*     */             {
/* 277 */               if (Trace.isOn) {
/* 278 */                 Trace.entry(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "run()");
/*     */               }
/* 280 */               KeyStoreConfig traceRet1 = UserMapServiceImpl.this.readKeystoreConfig(path);
/* 281 */               if (Trace.isOn) {
/* 282 */                 Trace.exit(this, "com.ibm.mq.ese.service.null", "run()", traceRet1);
/*     */               }
/* 284 */               return traceRet1;
/*     */             }
/*     */           });
/*     */     }
/* 288 */     catch (PrivilegedActionException e) {
/* 289 */       if (Trace.isOn) {
/* 290 */         Trace.catchBlock(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "getCredentials(String)", e);
/*     */       }
/*     */       
/* 293 */       ConfigException ex = new ConfigException(AmsErrorMessages.mju_cannot_read_keystore_properties, e);
/* 294 */       if (Trace.isOn) {
/* 295 */         Trace.throwing(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "getCredentials(String)", (Throwable)ex);
/*     */       }
/* 297 */       throw ex;
/*     */     } 
/* 299 */     KeyStoreAccess ks = KeyStoreAccessFactory.getInstance(keyStoreConfig);
/* 300 */     if (ks instanceof Lifecycle) {
/* 301 */       ((Lifecycle)ks).init();
/*     */     }
/* 303 */     EseUser eseUserInfo = constructUser(user, keyStoreConfig, ks);
/* 304 */     keyStoreConfig.cleanUp();
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.traceInfo(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "getCredentials(String)", "keystore: ", ks);
/*     */     }
/*     */ 
/*     */     
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.exit(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "getCredentials(String)", eseUserInfo);
/*     */     }
/*     */     
/* 314 */     return eseUserInfo;
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
/*     */   private EseUser constructUser(String userName, final KeyStoreConfig keyStoreConfig, KeyStoreAccess ks) throws ConfigException, AMBIException {
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.entry(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "constructUser(final String,final KeyStoreConfig,final KeyStoreAccess)", new Object[] { userName, keyStoreConfig, ks });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 336 */     String path = keyStoreConfig.getKeyStorePath();
/* 337 */     if (path == null) {
/* 338 */       path = keyStoreConfig.getType();
/*     */     }
/* 340 */     String alias = keyStoreConfig.getAlias();
/* 341 */     if (!ks.containsAlias(alias)) {
/* 342 */       HashMap<String, Object> inserts = new HashMap<>();
/* 343 */       inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", alias);
/* 344 */       inserts.put("AMS_INSERT_FILENAME", path);
/* 345 */       MissingCertificateException ex = new MissingCertificateException(AmsErrorMessages.mju_credential_alias_not_found_keystore_MissingCertificateException, inserts);
/* 346 */       ConfigException traceRet1 = new ConfigException((Exception)ex);
/* 347 */       if (Trace.isOn) {
/* 348 */         Trace.throwing(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "constructUser(final String,final KeyStoreConfig,final KeyStoreAccess)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 351 */       throw traceRet1;
/*     */     } 
/* 353 */     X509Certificate certificate = ks.getCertificate(alias);
/* 354 */     if (certificate == null) {
/* 355 */       HashMap<String, Object> inserts = new HashMap<>();
/* 356 */       inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", alias);
/* 357 */       inserts.put("AMS_INSERT_FILENAME", path);
/* 358 */       MissingCertificateException ex = new MissingCertificateException(AmsErrorMessages.mju_user_certificate_not_found_MissingCertificateException, inserts);
/* 359 */       ConfigException traceRet2 = new ConfigException((Exception)ex);
/* 360 */       if (Trace.isOn) {
/* 361 */         Trace.throwing(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "constructUser(final String,final KeyStoreConfig,final KeyStoreAccess)", (Throwable)traceRet2, 2);
/*     */       }
/*     */       
/* 364 */       throw traceRet2;
/*     */     } 
/*     */     
/* 367 */     X500NameWrapper userDN = new X500NameWrapper(certificate.getSubjectX500Principal().getName());
/* 368 */     EseUser user = new EseUser();
/* 369 */     user.setKeyStoreAccess(ks);
/* 370 */     user.setUserDN(userDN.toString());
/* 371 */     user.setUserName(userName);
/* 372 */     user.setUserCertificate(certificate);
/* 373 */     user.setAlias(alias);
/* 374 */     user.setProvider(keyStoreConfig.getProvider());
/* 375 */     user.setPkiSpec((keyStoreConfig.getPkiConfig()).pkiSpec);
/*     */     
/* 377 */     AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run()
/*     */           {
/* 381 */             if (Trace.isOn) {
/* 382 */               Trace.entry(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "run()");
/*     */             }
/*     */             
/* 385 */             String crldp = System.getProperty("com.ibm.security.enableCRLDP");
/* 386 */             if (crldp == null && (keyStoreConfig.getPkiConfig()).pkiSpec.checkCDP) {
/* 387 */               System.setProperty("com.ibm.security.enableCRLDP", "true");
/*     */               
/* 389 */               if (Trace.isOn) {
/* 390 */                 Trace.exit(this, "com.ibm.mq.ese.service.null", "run()", null, 1);
/*     */               }
/* 392 */               return null;
/*     */             } 
/* 394 */             if (Trace.isOn) {
/* 395 */               Trace.exit(this, "com.ibm.mq.ese.service.null", "run()", null, 2);
/*     */             }
/* 397 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 401 */     if (Trace.isOn) {
/* 402 */       Trace.traceInfo(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "constructUser(final String, final KeyStoreConfig, final KeyStoreAccess)", "userDN is: ", userDN);
/*     */ 
/*     */       
/* 405 */       Trace.traceInfo(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "constructUser(final String, final KeyStoreConfig, final KeyStoreAccess)", "alias is: ", alias);
/*     */ 
/*     */       
/* 408 */       Trace.traceInfo(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "constructUser(final String, final KeyStoreConfig, final KeyStoreAccess)", "certificate is: '", certificate);
/*     */     } 
/*     */ 
/*     */     
/* 412 */     if (Trace.isOn) {
/* 413 */       Trace.exit(this, "com.ibm.mq.ese.service.UserMapServiceImpl", "constructUser(final String,final KeyStoreConfig,final KeyStoreAccess)", user);
/*     */     }
/*     */     
/* 416 */     return user;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\service\UserMapServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */