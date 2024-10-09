/*     */ package com.ibm.mq.ese.pki;
/*     */ 
/*     */ import com.ibm.mq.ese.config.KeyStoreConfig;
/*     */ import com.ibm.mq.ese.core.AMBIException;
/*     */ import com.ibm.mq.ese.core.Lifecycle;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.security.AccessController;
/*     */ import java.security.KeyStore;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import java.util.HashMap;
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
/*     */ public class KeyStoreAccessJCERACFKSImpl
/*     */   extends AbstractKeyStoreAccess
/*     */   implements Lifecycle
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/KeyStoreAccessJCERACFKSImpl.java";
/*     */   static final int KS_SECONDARY = 1;
/*     */   
/*     */   static {
/*  51 */     if (Trace.isOn) {
/*  52 */       Trace.data("com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/KeyStoreAccessJCERACFKSImpl.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   private static final Pattern URI_SPLIT_PATTERN = Pattern.compile("://");
/*  62 */   private static final Pattern SLASH_SPLIT_PATTERN = Pattern.compile("/");
/*     */ 
/*     */   
/*     */   protected String keyRingName;
/*     */ 
/*     */   
/*     */   protected String keyRingUser;
/*     */ 
/*     */ 
/*     */   
/*     */   public KeyStoreAccessJCERACFKSImpl(KeyStoreConfig keyStoreConfig) {
/*  73 */     super(keyStoreConfig);
/*  74 */     if (Trace.isOn) {
/*  75 */       Trace.entry(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "<init>(KeyStoreConfig)", new Object[] { keyStoreConfig });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  80 */     if (this.keyStoreProvider == null || this.keyStoreProvider
/*  81 */       .equals("IBMJCE") || this.keyStoreProvider
/*  82 */       .equals("IBMJCEFIPS") || 
/*  83 */       !this.keyStoreProvider.equals("IBMJCEPlusFIPS"));
/*     */ 
/*     */     
/*  86 */     this.keyStoreProvider = "IBMJCE";
/*     */ 
/*     */     
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.exit(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "<init>(KeyStoreConfig)");
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
/*     */   public KeyStoreAccessJCERACFKSImpl(KeyStoreConfig keyStoreConfig, int type) {
/* 102 */     this(keyStoreConfig);
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.entry(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "<init>(KeyStoreConfig,int)", new Object[] { keyStoreConfig, 
/* 105 */             Integer.valueOf(type) });
/*     */     }
/* 107 */     if (type == 1) {
/* 108 */       this.keyStoreType = "JCERACFKS";
/* 109 */       this.keyStoreFile = keyStoreConfig.getSecondaryKeyStorePath();
/*     */     } 
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.exit(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "<init>(KeyStoreConfig,int)");
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
/*     */   private AMBIException getInitFailedEx(Exception e) {
/* 124 */     HashMap<String, Object> inserts = new HashMap<>();
/* 125 */     inserts.put("AMS_INSERT_FILENAME", this.keyStoreFile);
/* 126 */     return new AMBIException(AmsErrorMessages.mju_error_keystore_init_failed, inserts, e);
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
/*     */   private Constructor<?> getRACFInputStreamConstructor() throws AMBIException {
/*     */     Constructor<?> RACFInputStreamConstructor;
/*     */     try {
/* 141 */       Class<?> RACFInputStreamClass = Class.forName("com.ibm.crypto.provider.RACFInputStream");
/* 142 */       RACFInputStreamConstructor = RACFInputStreamClass.getDeclaredConstructor(new Class[] { String.class, String.class, char[].class });
/*     */       
/* 144 */       RACFInputStreamConstructor.setAccessible(true);
/* 145 */     } catch (Exception e) {
/* 146 */       AMBIException ae = getInitFailedEx(e);
/* 147 */       if (Trace.isOn) {
/* 148 */         Trace.throwing(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "getRACFInputStreamConstructor()", (Throwable)ae, 1);
/*     */       }
/* 150 */       throw ae;
/*     */     } 
/* 152 */     return RACFInputStreamConstructor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] parseRacfKeystoreName(String name) {
/* 163 */     String[] uriSplit = (name != null && name.length() > 0) ? URI_SPLIT_PATTERN.split(name, 3) : new String[0];
/*     */     
/* 165 */     String[] userRing = (uriSplit.length == 2 && uriSplit[0].equalsIgnoreCase("safkeyring")) ? SLASH_SPLIT_PATTERN.split(uriSplit[1], 3) : new String[0];
/* 166 */     return userRing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String[] parseUserKeyring() throws AMBIException {
/* 175 */     String[] userRing = parseRacfKeystoreName(this.keyStoreFile);
/* 176 */     if (userRing.length != 2 || userRing[1].length() == 0) {
/* 177 */       AMBIException e = getInitFailedEx(new Exception("Keystore name incorrect format"));
/* 178 */       if (Trace.isOn) {
/* 179 */         Trace.throwing(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "parseUserKeyring()", (Throwable)e, 1);
/*     */       }
/* 181 */       throw e;
/*     */     } 
/* 183 */     return userRing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void openKeyStore() throws AMBIException {
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.entry(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "openKeyStore()");
/*     */     }
/*     */     
/* 195 */     if (this.ks == null) {
/*     */       
/*     */       try {
/* 198 */         this.ks = KeyStore.getInstance(this.keyStoreType, "IBMJCE");
/* 199 */         String[] userKeyring = parseUserKeyring();
/* 200 */         this.keyRingUser = userKeyring[0];
/* 201 */         this.keyRingName = userKeyring[1];
/*     */ 
/*     */         
/* 204 */         Constructor<?> RACFInputStreamConstructor = getRACFInputStreamConstructor();
/* 205 */         InputStream keyRing = null;
/*     */         try {
/* 207 */           keyRing = (InputStream)RACFInputStreamConstructor.newInstance(new Object[] { this.keyRingUser, this.keyRingName, null });
/* 208 */           this.ks.load(keyRing, null);
/*     */         } finally {
/* 210 */           if (keyRing != null) {
/*     */             try {
/* 212 */               keyRing.close();
/* 213 */             } catch (IOException iOException) {}
/*     */             
/* 215 */             keyRing = null;
/*     */           }
/*     */         
/*     */         } 
/* 219 */       } catch (AMBIException e) {
/* 220 */         if (Trace.isOn) {
/* 221 */           Trace.catchBlock(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "openKeyStore()", (Throwable)e, 1);
/* 222 */           Trace.throwing(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "openKeyStore()", (Throwable)e, 1);
/*     */         } 
/* 224 */         throw e;
/*     */       }
/* 226 */       catch (Exception e) {
/* 227 */         if (Trace.isOn) {
/* 228 */           Trace.catchBlock(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "openKeyStore()", e, 2);
/*     */         }
/* 230 */         AMBIException ae = getInitFailedEx(e);
/* 231 */         if (Trace.isOn) {
/* 232 */           Trace.throwing(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "openKeyStore()", (Throwable)ae, 2);
/*     */         }
/* 234 */         throw ae;
/*     */       } 
/*     */     }
/*     */     
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.exit(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "openKeyStore()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() throws AMBIException {
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.entry(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "init()");
/*     */     }
/*     */     try {
/* 253 */       AccessController.doPrivileged(new PrivilegedExceptionAction()
/*     */           {
/*     */             public Object run() throws Exception {
/* 256 */               if (Trace.isOn) {
/* 257 */                 Trace.entry(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "run()");
/*     */               }
/* 259 */               KeyStoreAccessJCERACFKSImpl.this.openKeyStore();
/* 260 */               if (Trace.isOn) {
/* 261 */                 Trace.exit(this, "com.ibm.mq.ese.pki.null", "run()", null);
/*     */               }
/* 263 */               return null;
/*     */             }
/*     */           });
/* 266 */     } catch (PrivilegedActionException e) {
/* 267 */       AMBIException ae; if (Trace.isOn) {
/* 268 */         Trace.catchBlock(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "init()", e);
/*     */       }
/*     */       
/* 271 */       if (e.getException() instanceof AMBIException) {
/* 272 */         ae = (AMBIException)e.getException();
/*     */       } else {
/* 274 */         ae = new AMBIException(e.getException());
/*     */       } 
/* 276 */       if (Trace.isOn) {
/* 277 */         Trace.throwing(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "init()", (Throwable)ae, 1);
/*     */       }
/* 279 */       throw ae;
/*     */     } 
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.exit(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "init()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cleanUp() throws AMBIException {
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.entry(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "cleanUp()");
/*     */     }
/* 295 */     if (Trace.isOn)
/* 296 */       Trace.exit(this, "com.ibm.mq.ese.pki.KeyStoreAccessJCERACFKSImpl", "cleanUp()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\pki\KeyStoreAccessJCERACFKSImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */