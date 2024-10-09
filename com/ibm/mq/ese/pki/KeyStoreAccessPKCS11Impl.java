/*     */ package com.ibm.mq.ese.pki;
/*     */ 
/*     */ import com.ibm.mq.ese.config.KeyStoreConfig;
/*     */ import com.ibm.mq.ese.config.PasswordObject;
/*     */ import com.ibm.mq.ese.core.AMBIException;
/*     */ import com.ibm.mq.ese.core.Lifecycle;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ import java.security.GeneralSecurityException;
/*     */ import java.security.KeyStore;
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
/*     */ public class KeyStoreAccessPKCS11Impl
/*     */   extends AbstractKeyStoreAccess
/*     */   implements Lifecycle
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/KeyStoreAccessPKCS11Impl.java";
/*     */   
/*     */   static {
/*  45 */     if (Trace.isOn) {
/*  46 */       Trace.data("com.ibm.mq.ese.pki.KeyStoreAccessPKCS11Impl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/KeyStoreAccessPKCS11Impl.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   private static final String CLASS = KeyStoreAccessPKCS11Impl.class.getName();
/*     */ 
/*     */   
/*     */   private boolean useExplicitKeyStore = false;
/*     */ 
/*     */   
/*     */   private boolean usePKCS11IMPLKS = true;
/*     */   
/*  61 */   public static final Object KS_LOCK = new Object();
/*     */   
/*     */   private static KeyStore staticKS;
/*     */   
/*     */   public KeyStoreAccessPKCS11Impl(KeyStoreConfig keyStoreConfig) {
/*  66 */     super(keyStoreConfig);
/*  67 */     if (Trace.isOn) {
/*  68 */       Trace.entry(this, "com.ibm.mq.ese.pki.KeyStoreAccessPKCS11Impl", "<init>(KeyStoreConfig)", new Object[] { keyStoreConfig });
/*     */     }
/*     */ 
/*     */     
/*  72 */     this.keyStoreFile = keyStoreConfig.getType();
/*  73 */     this.keyStorePassword = new PasswordObject(keyStoreConfig.getKeyStorePassword());
/*  74 */     setPkeyPass(keyStoreConfig);
/*  75 */     if (this.keyStoreProvider != null && this.keyStoreProvider.length() > 0) {
/*  76 */       this.useExplicitKeyStore = true;
/*  77 */       this.usePKCS11IMPLKS = this.keyStoreProvider.toUpperCase().startsWith("IBM");
/*     */     } 
/*     */     
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.exit(this, "com.ibm.mq.ese.pki.KeyStoreAccessPKCS11Impl", "<init>(KeyStoreConfig)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() throws AMBIException {
/*  92 */     if (Trace.isOn) {
/*  93 */       Trace.entry(this, "com.ibm.mq.ese.pki.KeyStoreAccessPKCS11Impl", "init()");
/*     */     }
/*     */     
/*     */     try {
/*  97 */       synchronized (KS_LOCK) {
/*  98 */         if (staticKS == null) {
/*  99 */           this.ks = null;
/*     */           
/* 101 */           if (this.useExplicitKeyStore) {
/* 102 */             staticKS = KeyStore.getInstance(this.usePKCS11IMPLKS ? "PKCS11IMPLKS" : "PKCS11", this.keyStoreProvider);
/*     */           }
/*     */           else {
/*     */             
/* 106 */             staticKS = KeyStore.getInstance(this.usePKCS11IMPLKS ? "PKCS11IMPLKS" : "PKCS11");
/*     */           } 
/*     */ 
/*     */           
/* 110 */           char[] keystorepw = null;
/* 111 */           switch (this.keyStorePassword.getProtectionType()) {
/*     */             case PLAINTEXT:
/*     */             case NULL:
/* 114 */               keystorepw = new char[(this.keyStorePassword.getPassword()).length];
/* 115 */               System.arraycopy(this.keyStorePassword.getPassword(), 0, keystorepw, 0, (this.keyStorePassword.getPassword()).length);
/*     */               break;
/*     */             case OLDPROTECTED:
/* 118 */               keystorepw = decryptPasswordOld(new String(this.keyStorePassword.getPassword()));
/*     */               break;
/*     */             case NEWPROTECTED:
/* 121 */               keystorepw = decryptPassword(new String(this.keyStorePassword.getPassword()));
/*     */               break;
/*     */           } 
/* 124 */           staticKS.load(null, keystorepw);
/*     */           
/* 126 */           Arrays.fill(keystorepw, false);
/*     */           
/* 128 */           this.keyStoreProvider = staticKS.getProvider().getName();
/* 129 */           String info = staticKS.getProvider().getInfo();
/* 130 */           if (Trace.isOn) {
/* 131 */             Trace.traceInfo(CLASS, "init()", "Provider information", info);
/*     */           }
/* 133 */           HashMap<String, Object> inserts = new HashMap<>();
/* 134 */           inserts.put("AMS_INSERT_PKCS11_PROVIDER_INFORMATION", info);
/* 135 */           AmsErrorMessages.log(CLASS, "init()", AmsErrorMessages.mju_pkcs11_keystore_init, inserts);
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 142 */     catch (GeneralSecurityException e) {
/* 143 */       if (Trace.isOn) {
/* 144 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.KeyStoreAccessPKCS11Impl", "init()", e, 1);
/*     */       }
/* 146 */       HashMap<String, Object> inserts = new HashMap<>();
/* 147 */       inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/* 148 */       AMBIException ex = new AMBIException(AmsErrorMessages.mju_error_keystore_init_failed, inserts, e);
/* 149 */       if (Trace.isOn) {
/* 150 */         Trace.throwing(this, "com.ibm.mq.ese.pki.KeyStoreAccessPKCS11Impl", "init()", (Throwable)ex, 1);
/*     */       }
/* 152 */       throw ex;
/*     */     }
/* 154 */     catch (IOException e) {
/* 155 */       if (Trace.isOn) {
/* 156 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.KeyStoreAccessPKCS11Impl", "init()", e, 2);
/*     */       }
/* 158 */       HashMap<String, Object> inserts = new HashMap<>();
/* 159 */       inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/* 160 */       AMBIException ex = new AMBIException(AmsErrorMessages.mju_error_keystore_init_failed, inserts, e);
/* 161 */       if (Trace.isOn) {
/* 162 */         Trace.throwing(this, "com.ibm.mq.ese.pki.KeyStoreAccessPKCS11Impl", "init()", (Throwable)ex, 2);
/*     */       }
/* 164 */       throw ex;
/*     */     } 
/*     */     
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit(this, "com.ibm.mq.ese.pki.KeyStoreAccessPKCS11Impl", "init()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cleanUp() throws AMBIException {
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.entry(this, "com.ibm.mq.ese.pki.KeyStoreAccessPKCS11Impl", "cleanUp()");
/*     */     }
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.exit(this, "com.ibm.mq.ese.pki.KeyStoreAccessPKCS11Impl", "cleanUp()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected KeyStore getKs() {
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.data(this, "com.ibm.mq.ese.pki.KeyStoreAccessPKCS11Impl", "getKs()", "getter", staticKS);
/*     */     }
/*     */     
/* 193 */     return staticKS;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\pki\KeyStoreAccessPKCS11Impl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */