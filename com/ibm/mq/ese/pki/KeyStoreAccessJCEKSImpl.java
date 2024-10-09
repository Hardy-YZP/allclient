/*     */ package com.ibm.mq.ese.pki;
/*     */ 
/*     */ import com.ibm.mq.ese.config.KeyStoreConfig;
/*     */ import com.ibm.mq.ese.config.PasswordObject;
/*     */ import com.ibm.mq.ese.core.AMBIException;
/*     */ import com.ibm.mq.ese.core.Lifecycle;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.security.AccessControlException;
/*     */ import java.security.AccessController;
/*     */ import java.security.KeyStore;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KeyStoreAccessJCEKSImpl
/*     */   extends AbstractKeyStoreAccess
/*     */   implements Lifecycle
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/KeyStoreAccessJCEKSImpl.java";
/*     */   static final int KS_SECONDARY = 1;
/*     */   
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/KeyStoreAccessJCEKSImpl.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean useExplicitKeyStore = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KeyStoreAccessJCEKSImpl(KeyStoreConfig keyStoreConfig) {
/*  73 */     super(keyStoreConfig);
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "<init>(KeyStoreConfig)", new Object[] { keyStoreConfig });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  80 */     if (this.keyStoreProvider != null && (this.keyStoreProvider
/*  81 */       .equals("IBMJCEFIPS") || this.keyStoreProvider
/*  82 */       .equals("IBMJCEPlusFIPS"))) {
/*  83 */       if (Trace.isOn) {
/*  84 */         Trace.traceInfo(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "KeyStoreAccessJCEKSImpl(KeyStoreConfig)", "keyStoreFile: '" + this.keyStoreFile + "' will be using IBMJCE as a keystore provider", "");
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  89 */       this.keyStoreProvider = "IBMJCE";
/*  90 */       this.useExplicitKeyStore = true;
/*     */     } 
/*  92 */     this.keyStorePassword = new PasswordObject(keyStoreConfig.getKeyStorePassword());
/*  93 */     setPkeyPass(keyStoreConfig);
/*     */     
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.exit(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "<init>(KeyStoreConfig)");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public KeyStoreAccessJCEKSImpl(KeyStoreConfig keyStoreConfig, int type) {
/* 102 */     this(keyStoreConfig);
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.entry(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "<init>(KeyStoreConfig,int)", new Object[] { keyStoreConfig, 
/* 105 */             Integer.valueOf(type) });
/*     */     }
/* 107 */     if (type == 1) {
/* 108 */       this.keyStoreType = "JCEKS";
/* 109 */       this.keyStoreFile = keyStoreConfig.getSecondaryKeyStorePath();
/* 110 */       this.keyStorePassword = new PasswordObject(keyStoreConfig.getSecondaryKeyStorePass());
/*     */     } 
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.exit(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "<init>(KeyStoreConfig,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void openKeyStore() throws AMBIException {
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.entry(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "openKeyStore()");
/*     */     }
/*     */     
/* 127 */     FileInputStream in = null;
/* 128 */     if (this.ks == null) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 133 */         if (!this.useExplicitKeyStore) {
/* 134 */           this.ks = KeyStore.getInstance(this.keyStoreType);
/*     */         } else {
/* 136 */           this.ks = KeyStore.getInstance(this.keyStoreType, this.keyStoreProvider);
/*     */         } 
/* 138 */         File file = accessKeystoreFile(this.keyStoreFile);
/* 139 */         in = new FileInputStream(file);
/* 140 */         this.ks.load(in, null);
/* 141 */         in.close();
/* 142 */         in = null;
/*     */ 
/*     */         
/* 145 */         char[] keystorepass = null;
/* 146 */         switch (this.keyStorePassword.getProtectionType()) {
/*     */           case PLAINTEXT:
/*     */           case NULL:
/* 149 */             keystorepass = new char[(this.keyStorePassword.getPassword()).length];
/* 150 */             System.arraycopy(this.keyStorePassword.getPassword(), 0, keystorepass, 0, (this.keyStorePassword.getPassword()).length);
/*     */             break;
/*     */           case OLDPROTECTED:
/* 153 */             keystorepass = decryptPasswordOld(new String(this.keyStorePassword.getPassword()));
/*     */             break;
/*     */           case NEWPROTECTED:
/* 156 */             keystorepass = decryptPassword(new String(this.keyStorePassword.getPassword()));
/*     */             break;
/*     */         } 
/*     */         
/* 160 */         if (!this.useExplicitKeyStore) {
/* 161 */           this.ks = KeyStore.getInstance(this.keyStoreType);
/*     */         } else {
/* 163 */           this.ks = KeyStore.getInstance(this.keyStoreType, this.keyStoreProvider);
/*     */         } 
/* 165 */         in = new FileInputStream(file);
/* 166 */         this.ks.load(in, keystorepass);
/*     */         
/* 168 */         Arrays.fill(keystorepass, false);
/*     */         
/* 170 */         in.close();
/*     */       }
/* 172 */       catch (AMBIException e) {
/* 173 */         if (Trace.isOn) {
/* 174 */           Trace.catchBlock(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "openKeyStore()", (Throwable)e, 1);
/*     */         }
/* 176 */         if (Trace.isOn) {
/* 177 */           Trace.throwing(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "openKeyStore()", (Throwable)e, 1);
/*     */         }
/* 179 */         throw e;
/*     */       }
/* 181 */       catch (Exception e) {
/* 182 */         if (Trace.isOn) {
/* 183 */           Trace.catchBlock(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "openKeyStore()", e, 2);
/*     */         }
/* 185 */         HashMap<String, Object> inserts = new HashMap<>();
/* 186 */         inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/* 187 */         AMBIException ex = new AMBIException(AmsErrorMessages.mju_error_keystore_init_failed, inserts, e);
/* 188 */         if (Trace.isOn) {
/* 189 */           Trace.throwing(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "openKeyStore()", (Throwable)ex, 2);
/*     */         }
/* 191 */         throw ex;
/*     */       } finally {
/*     */         
/* 194 */         if (Trace.isOn) {
/* 195 */           Trace.finallyBlock(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "openKeyStore()");
/*     */         }
/* 197 */         if (in != null) {
/*     */           try {
/* 199 */             in.close();
/*     */           }
/* 201 */           catch (IOException e) {
/* 202 */             if (Trace.isOn) {
/* 203 */               Trace.catchBlock(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "openKeyStore()", e, 3);
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 211 */     if (Trace.isOn) {
/* 212 */       Trace.exit(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "openKeyStore()");
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
/*     */   static File accessKeystoreFile(String fileName) throws AMBIException {
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.entry("com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "accessKeystoreFile(String)", new Object[] { fileName });
/*     */     }
/*     */     
/* 232 */     SecurityException secex = null;
/* 233 */     String[] extensions = { ".jks", ".jceks", "", ".jck" };
/* 234 */     for (int i = 0; i < extensions.length; i++) {
/*     */       try {
/* 236 */         String ext = extensions[i];
/* 237 */         String filename = fileName + ext;
/* 238 */         File file = new File(filename);
/* 239 */         if (file.exists()) {
/* 240 */           if (Trace.isOn) {
/* 241 */             Trace.exit("com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "accessKeystoreFile(String)", file);
/*     */           }
/* 243 */           return file;
/*     */         }
/*     */       
/* 246 */       } catch (SecurityException e) {
/* 247 */         if (Trace.isOn) {
/* 248 */           Trace.catchBlock("com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "accessKeystoreFile(String)", e);
/*     */         }
/*     */         
/* 251 */         if (secex == null) {
/* 252 */           secex = e;
/*     */         }
/*     */       } 
/*     */     } 
/* 256 */     if (secex != null) {
/* 257 */       HashMap<String, Object> hashMap = new HashMap<>();
/* 258 */       hashMap.put("AMS_INSERT_FILENAME", fileName);
/* 259 */       AMBIException aMBIException = new AMBIException(AmsErrorMessages.mju_error_keystore_init_failed, hashMap, new AccessControlException(fileName));
/*     */       
/* 261 */       if (Trace.isOn) {
/* 262 */         Trace.throwing("com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "accessKeystoreFile(String)", (Throwable)aMBIException, 1);
/*     */       }
/*     */       
/* 265 */       throw aMBIException;
/*     */     } 
/* 267 */     HashMap<String, Object> inserts = new HashMap<>();
/* 268 */     inserts.put("AMS_INSERT_FILENAME", fileName);
/* 269 */     AMBIException ex = new AMBIException(AmsErrorMessages.mju_error_keystore_init_failed, inserts, new FileNotFoundException(fileName));
/*     */     
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.throwing("com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "accessKeystoreFile(String)", (Throwable)ex, 2);
/*     */     }
/*     */     
/* 275 */     throw ex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() throws AMBIException {
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.entry(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "init()");
/*     */     }
/*     */     try {
/* 287 */       AccessController.doPrivileged(new PrivilegedExceptionAction()
/*     */           {
/*     */             public Object run() throws Exception
/*     */             {
/* 291 */               if (Trace.isOn) {
/* 292 */                 Trace.entry(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "run()");
/*     */               }
/* 294 */               KeyStoreAccessJCEKSImpl.this.openKeyStore();
/* 295 */               if (Trace.isOn) {
/* 296 */                 Trace.exit(this, "com.ibm.mq.ese.pki.null", "run()", null);
/*     */               }
/* 298 */               return null;
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 305 */     catch (PrivilegedActionException e) {
/* 306 */       if (Trace.isOn) {
/* 307 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "init()", e);
/*     */       }
/* 309 */       if (e.getException() instanceof AMBIException) {
/* 310 */         AMBIException traceRet1 = (AMBIException)e.getException();
/* 311 */         if (Trace.isOn) {
/* 312 */           Trace.throwing(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "init()", (Throwable)traceRet1, 1);
/*     */         }
/* 314 */         throw traceRet1;
/*     */       } 
/* 316 */       AMBIException traceRet2 = new AMBIException(e.getException());
/* 317 */       if (Trace.isOn) {
/* 318 */         Trace.throwing(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "init()", (Throwable)traceRet2, 2);
/*     */       }
/* 320 */       throw traceRet2;
/*     */     } 
/* 322 */     if (Trace.isOn) {
/* 323 */       Trace.exit(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "init()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cleanUp() throws AMBIException {
/* 333 */     if (Trace.isOn) {
/* 334 */       Trace.entry(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "cleanUp()");
/*     */     }
/* 336 */     if (Trace.isOn)
/* 337 */       Trace.exit(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCEKSImpl", "cleanUp()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\pki\KeyStoreAccessJCEKSImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */