/*      */ package com.ibm.mq.ese.prot;
/*      */ 
/*      */ import com.ibm.mq.ese.core.AMBIException;
/*      */ import com.ibm.mq.ese.core.AMBIHeader;
/*      */ import com.ibm.mq.ese.core.AlgorithmHelper;
/*      */ import com.ibm.mq.ese.core.EseUser;
/*      */ import com.ibm.mq.ese.core.KeyStoreAccess;
/*      */ import com.ibm.mq.ese.core.MessageProtectionConstants;
/*      */ import com.ibm.mq.ese.core.SecurityPolicy;
/*      */ import com.ibm.mq.ese.core.X500NameWrapper;
/*      */ import com.ibm.mq.ese.intercept.SmqiObject;
/*      */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*      */ import com.ibm.mq.ese.pki.CompositeKeyStoreAccess;
/*      */ import com.ibm.mq.ese.pki.InvalidCertificateException;
/*      */ import com.ibm.mq.ese.pki.MissingCertificateException;
/*      */ import com.ibm.mq.ese.pki.X509CertificateValidator;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.net.URL;
/*      */ import java.security.AccessController;
/*      */ import java.security.GeneralSecurityException;
/*      */ import java.security.InvalidAlgorithmParameterException;
/*      */ import java.security.InvalidKeyException;
/*      */ import java.security.KeyStore;
/*      */ import java.security.KeyStoreException;
/*      */ import java.security.NoSuchAlgorithmException;
/*      */ import java.security.NoSuchProviderException;
/*      */ import java.security.PrivateKey;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.security.Provider;
/*      */ import java.security.Security;
/*      */ import java.security.cert.CertPath;
/*      */ import java.security.cert.CertPathBuilder;
/*      */ import java.security.cert.CertPathBuilderException;
/*      */ import java.security.cert.CertStore;
/*      */ import java.security.cert.Certificate;
/*      */ import java.security.cert.CertificateEncodingException;
/*      */ import java.security.cert.CertificateException;
/*      */ import java.security.cert.PKIXBuilderParameters;
/*      */ import java.security.cert.PKIXCertPathBuilderResult;
/*      */ import java.security.cert.TrustAnchor;
/*      */ import java.security.cert.X509CertSelector;
/*      */ import java.security.cert.X509Certificate;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.jar.Attributes;
/*      */ import java.util.jar.Manifest;
/*      */ import javax.crypto.ShortBufferException;
/*      */ import org.bouncycastle.asn1.ASN1Encodable;
/*      */ import org.bouncycastle.asn1.ASN1InputStream;
/*      */ import org.bouncycastle.asn1.ASN1ObjectIdentifier;
/*      */ import org.bouncycastle.asn1.cms.AttributeTable;
/*      */ import org.bouncycastle.asn1.cms.CMSAttributes;
/*      */ import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
/*      */ import org.bouncycastle.asn1.cms.ContentInfo;
/*      */ import org.bouncycastle.asn1.cms.SignedData;
/*      */ import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/*      */ import org.bouncycastle.cert.X509CertificateHolder;
/*      */ import org.bouncycastle.cert.jcajce.JcaCertStore;
/*      */ import org.bouncycastle.cert.jcajce.JcaCertStoreBuilder;
/*      */ import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
/*      */ import org.bouncycastle.cms.CMSAttributeTableGenerator;
/*      */ import org.bouncycastle.cms.CMSEnvelopedData;
/*      */ import org.bouncycastle.cms.CMSEnvelopedDataGenerator;
/*      */ import org.bouncycastle.cms.CMSException;
/*      */ import org.bouncycastle.cms.CMSProcessableByteArray;
/*      */ import org.bouncycastle.cms.CMSSignatureEncryptionAlgorithmFinder;
/*      */ import org.bouncycastle.cms.CMSSignedData;
/*      */ import org.bouncycastle.cms.CMSSignedDataGenerator;
/*      */ import org.bouncycastle.cms.CMSTypedData;
/*      */ import org.bouncycastle.cms.DefaultSignedAttributeTableGenerator;
/*      */ import org.bouncycastle.cms.Recipient;
/*      */ import org.bouncycastle.cms.RecipientId;
/*      */ import org.bouncycastle.cms.RecipientInfoGenerator;
/*      */ import org.bouncycastle.cms.RecipientInformation;
/*      */ import org.bouncycastle.cms.RecipientInformationStore;
/*      */ import org.bouncycastle.cms.SignerInfoGenerator;
/*      */ import org.bouncycastle.cms.SignerInformation;
/*      */ import org.bouncycastle.cms.SignerInformationStore;
/*      */ import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
/*      */ import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
/*      */ import org.bouncycastle.cms.jcajce.JcaX509CertSelectorConverter;
/*      */ import org.bouncycastle.cms.jcajce.JceCMSContentEncryptorBuilder;
/*      */ import org.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient;
/*      */ import org.bouncycastle.cms.jcajce.JceKeyTransRecipientId;
/*      */ import org.bouncycastle.cms.jcajce.JceKeyTransRecipientInfoGenerator;
/*      */ import org.bouncycastle.crypto.BlockCipher;
/*      */ import org.bouncycastle.crypto.InvalidCipherTextException;
/*      */ import org.bouncycastle.crypto.engines.AESFastEngine;
/*      */ import org.bouncycastle.crypto.engines.DESEngine;
/*      */ import org.bouncycastle.crypto.engines.DESedeEngine;
/*      */ import org.bouncycastle.crypto.engines.RC2Engine;
/*      */ import org.bouncycastle.jce.provider.BouncyCastleProvider;
/*      */ import org.bouncycastle.operator.ContentSigner;
/*      */ import org.bouncycastle.operator.DefaultAlgorithmNameFinder;
/*      */ import org.bouncycastle.operator.DigestCalculatorProvider;
/*      */ import org.bouncycastle.operator.OperatorCreationException;
/*      */ import org.bouncycastle.operator.OutputEncryptor;
/*      */ import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
/*      */ import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
/*      */ import org.bouncycastle.util.Selector;
/*      */ import org.bouncycastle.util.Store;
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
/*      */ public class MessageProtectionBCImpl
/*      */   implements MessageProtection
/*      */ {
/*      */   private static final String DIGEST_ALG_SHA = "SHA";
/*      */   private static final String DIGEST_ALG_SHA1 = "SHA1";
/*      */   private static final String DIGEST_ALG_SHA2 = "SHA2";
/*      */   private static final String DIGEST_ALG_SHA256 = "SHA256";
/*      */   private static final String DIGEST_ALG_SHA3 = "SHA3";
/*      */   private static final String DIGEST_ALG_SHA384 = "SHA384";
/*      */   private static final String DIGEST_ALG_SHA5 = "SHA5";
/*      */   private static final String DIGEST_ALG_SHA512 = "SHA512";
/*      */   private static final String DIGEST_ALG_MD5 = "MD5";
/*      */   private static final String CONFIDENTIALITY_KEY_SIGNING_ALGORITHM = "SHA256WITHRSA";
/*      */   
/*      */   static {
/*  149 */     if (Trace.isOn) {
/*  150 */       Trace.data("MessageProtectionBCImpl", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/prot/MessageProtectionBCImpl.java");
/*      */     }
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
/*  209 */     if (Trace.isOn) {
/*  210 */       Trace.entry("MessageProtectionBCImpl", "static()");
/*      */     }
/*  212 */     Security.addProvider((Provider)new BouncyCastleProvider());
/*  213 */     if (Trace.isOn) {
/*  214 */       Trace.exit("MessageProtectionBCImpl", "static()");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  222 */     if (Trace.isOn) {
/*  223 */       String bcVersion = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*      */           {
/*      */             public String run()
/*      */             {
/*  227 */               InputStream is = null;
/*  228 */               String versionFound = null;
/*      */               try {
/*  230 */                 String classPath = BouncyCastleProvider.class.getResource("BouncyCastleProvider.class").toString();
/*  231 */                 if (classPath.startsWith("jar")) {
/*  232 */                   String manifestPath = classPath.substring(0, classPath.lastIndexOf("!") + 1) + "/META-INF/MANIFEST.MF";
/*      */                   
/*  234 */                   is = (new URL(manifestPath)).openStream();
/*  235 */                   Manifest manifest = new Manifest(is);
/*  236 */                   Attributes attr = manifest.getMainAttributes();
/*  237 */                   versionFound = attr.getValue("Implementation-Version");
/*  238 */                   if (versionFound == null || versionFound.length() == 0) {
/*  239 */                     Trace.data("MessageProtectionBCImpl", "static()", "Unable to determine BouncyCastleProvider version - Implementation-Version value was \"" + versionFound + "\"", classPath);
/*      */                   
/*      */                   }
/*      */                 
/*      */                 }
/*      */                 else {
/*      */                   
/*  246 */                   Trace.data("MessageProtectionBCImpl", "static()", "Unable to determine BouncyCastleProvider version. Unexpected resource URI: " + classPath);
/*      */                 
/*      */                 }
/*      */               
/*      */               }
/*  251 */               catch (Exception e) {
/*      */                 
/*  253 */                 Trace.data("MessageProtectionBCImpl", "static()", "Unable to determine BouncyCastleProvider version due to exception: " + e);
/*      */               }
/*      */               finally {
/*      */                 
/*  257 */                 if (is != null) {
/*      */                   try {
/*  259 */                     is.close();
/*      */                   }
/*  261 */                   catch (IOException iOException) {}
/*      */                 }
/*      */               } 
/*      */ 
/*      */               
/*  266 */               return versionFound;
/*      */             }
/*      */           });
/*  269 */       if (bcVersion != null && bcVersion.length() > 0)
/*  270 */         Trace.data("MessageProtectionBCImpl", "static()", "BouncyCastleProvider version: " + bcVersion
/*  271 */             .toString()); 
/*      */     } 
/*      */   }
/*      */   Map<String, String> sha2NameMapping = new HashMap<String, String>() { private static final long serialVersionUID = 1L; }
/*      */   ;
/*      */   private X509CertificateValidator certificateValidator;
/*      */   private JcaX509CertificateConverter certificateConverter = new JcaX509CertificateConverter();
/*      */   private DefaultAlgorithmNameFinder danFinder = new DefaultAlgorithmNameFinder();
/*      */   
/*      */   public MessageProtectionBCImpl(X509CertificateValidator validator) {
/*  281 */     if (Trace.isOn) {
/*  282 */       Trace.entry(this, "MessageProtectionBCImpl", "<init>(X509CertificateValidator)", new Object[] { validator });
/*      */     }
/*      */     
/*  285 */     this.certificateValidator = validator;
/*  286 */     if (Trace.isOn) {
/*  287 */       Trace.exit(this, "MessageProtectionBCImpl", "<init>(X509CertificateValidator)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*  292 */   static int pCount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] protect(byte[] msg, SmqiObject smqiObject, AMBIHeader hdr, EseUser user) throws MessageProtectionException {
/*  303 */     if (Trace.isOn) {
/*  304 */       Trace.entry(this, "MessageProtectionBCImpl", "protect(byte [ ],SmqiObject,AMBIHeader,EseUser)", new Object[] { msg, smqiObject, hdr, user });
/*      */     }
/*      */ 
/*      */     
/*  308 */     if (msg == null || msg.length == 0) {
/*      */ 
/*      */       
/*  311 */       if (Trace.isOn) {
/*  312 */         Trace.traceInfo(this, "com.ibm.mq.ese.prot.MessageProtectionBCImpl", "protect(byte[], SmqiObject, EseUser)", "skipping an empty message body", "");
/*      */       }
/*      */       
/*  315 */       if (Trace.isOn) {
/*  316 */         Trace.exit(this, "MessageProtectionBCImpl", "protect(byte [ ],SmqiObject,AMBIHeader,EseUser)", msg, 1);
/*      */       }
/*  318 */       return msg;
/*      */     } 
/*      */     
/*  321 */     SecurityPolicy policy = smqiObject.getSecPolicy();
/*      */     
/*  323 */     if (policy == null) {
/*  324 */       IllegalArgumentException e = new IllegalArgumentException("policy == null");
/*  325 */       MessageProtectionException traceRet1 = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_msg_protection_failed, e);
/*      */       
/*  327 */       if (Trace.isOn) {
/*  328 */         Trace.throwing(this, "MessageProtectionBCImpl", "protect(byte [ ],SmqiObject,AMBIHeader,EseUser)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/*  331 */       throw traceRet1;
/*      */     } 
/*      */     
/*  334 */     if (user == null) {
/*  335 */       IllegalArgumentException e = new IllegalArgumentException("user == null");
/*  336 */       MessageProtectionException traceRet2 = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_msg_protection_failed, e);
/*      */       
/*  338 */       if (Trace.isOn) {
/*  339 */         Trace.throwing(this, "MessageProtectionBCImpl", "protect(byte [ ],SmqiObject,AMBIHeader,EseUser)", (Throwable)traceRet2, 2);
/*      */       }
/*      */       
/*  342 */       throw traceRet2;
/*      */     } 
/*      */     
/*  345 */     int policyQop = policy.getQop();
/*  346 */     validateQop(policyQop);
/*  347 */     X509Certificate senderCert = user.getCertificate();
/*  348 */     if (senderCert == null) {
/*  349 */       HashMap<String, Object> inserts = new HashMap<>();
/*  350 */       inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", user.getKeystoreAlias());
/*  351 */       inserts.put("AMS_INSERT_FILENAME", user.getKeyStoreAccess().toString());
/*  352 */       MessageProtectionException ex = new MessageProtectionException(AmsErrorMessages.mju_user_certificate_not_found_MessageProtectionException, inserts);
/*      */       
/*  354 */       if (Trace.isOn) {
/*  355 */         Trace.throwing(this, "MessageProtectionBCImpl", "protect(byte [ ],SmqiObject,AMBIHeader,EseUser)", (Throwable)ex, 3);
/*      */       }
/*      */       
/*  358 */       throw ex;
/*      */     } 
/*      */     try {
/*      */       MessageProtectionException traceRet4;
/*  362 */       if (!smqiObject.senderCertificateValidated()) {
/*      */ 
/*      */         
/*  365 */         validateSenderCertificate(senderCert, user, false);
/*  366 */         smqiObject.senderCertificateValidated(true);
/*      */       } 
/*      */       
/*  369 */       byte[] result = null;
/*      */       
/*  371 */       switch (policyQop) {
/*      */         case 0:
/*  373 */           result = msg;
/*      */           break;
/*      */         case 1:
/*  376 */           result = sign(msg, policy, user).toASN1Structure().getEncoded("DL");
/*      */           break;
/*      */         case 2:
/*  379 */           result = envelopeSignedData(sign(msg, policy, user), policy, user);
/*      */           break;
/*      */         case 3:
/*  382 */           result = makeConfidential(msg, smqiObject, policy, hdr);
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/*  387 */           traceRet4 = IllegalProtectionTypeException.create(Integer.toString(policyQop), null);
/*  388 */           if (Trace.isOn) {
/*  389 */             Trace.throwing(this, "MessageProtectionBCImpl", "protect(byte [ ],SmqiObject,AMBIHeader,EseUser)", (Throwable)traceRet4, 4);
/*      */           }
/*      */           
/*  392 */           throw traceRet4;
/*      */       } 
/*      */       
/*  395 */       if (Trace.isOn) {
/*  396 */         Trace.exit(this, "MessageProtectionBCImpl", "protect(byte [ ],SmqiObject,AMBIHeader,EseUser)", result, 2);
/*      */       }
/*      */       
/*  399 */       return result;
/*      */     }
/*  401 */     catch (Exception e) {
/*  402 */       if (Trace.isOn) {
/*  403 */         Trace.catchBlock(this, "MessageProtectionBCImpl", "protect(byte [ ],SmqiObject,AMBIHeader,EseUser)", e);
/*      */       }
/*  405 */       AmsErrorMessages.logProtectionException("com.ibm.mq.ese.prot.MessageProtectionBCImpl", "protect(byte [ ],SmqiObject,AMBIHeader,EseUser)", e);
/*      */       
/*  407 */       MessageProtectionException ex = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_msg_protection_failed, e);
/*      */       
/*  409 */       if (Trace.isOn) {
/*  410 */         Trace.throwing(this, "MessageProtectionBCImpl", "protect(byte [ ],SmqiObject,AMBIHeader,EseUser)", (Throwable)ex, 5);
/*      */       }
/*      */       
/*  413 */       throw ex;
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
/*      */   private byte[] makeConfidential(byte[] msg, SmqiObject smqiObject, SecurityPolicy policy, AMBIHeader hdr) throws AMBIException, IOException, InvalidCipherTextException {
/*      */     IllegalAlgorithmNameException traceRet1;
/*  430 */     if (Trace.isOn) {
/*  431 */       Trace.entry(this, "MessageProtectionBCImpl", "makeConfidential(byte [ ],SmqiObject,SecurityPolicy,AMBIHeader)", new Object[] { msg, smqiObject, policy, hdr });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  436 */     String encAlg = policy.getEncAlg();
/*  437 */     int keySize = 0;
/*  438 */     BlockCipher engine = getEngineForPut(encAlg);
/*  439 */     switch (encAlg) {
/*      */       case "RC2":
/*  441 */         keySize = 128;
/*      */         break;
/*      */       case "DES":
/*  444 */         keySize = 64;
/*      */         break;
/*      */       case "DESede":
/*  447 */         keySize = 192;
/*      */         break;
/*      */       case "AES128":
/*  450 */         keySize = 128;
/*      */         break;
/*      */       case "AES256":
/*  453 */         keySize = 256;
/*      */         break;
/*      */       default:
/*  456 */         traceRet1 = new IllegalAlgorithmNameException(encAlg, null);
/*      */         
/*  458 */         if (Trace.isOn) {
/*  459 */           Trace.throwing(this, "MessageProtectionBCImpl", "makeConfidential(byte [ ],SmqiObject,SecurityPolicy,AMBIHeader)", (Throwable)traceRet1);
/*      */         }
/*      */         
/*  462 */         throw traceRet1;
/*      */     } 
/*      */ 
/*      */     
/*  466 */     byte[][] protectedKeyArray = smqiObject.getSecretKeyForPut(keySize);
/*      */ 
/*      */ 
/*      */     
/*  470 */     byte[] protectedKey = null;
/*  471 */     byte[] secretKey = null;
/*      */     
/*  473 */     if (protectedKeyArray == null) {
/*      */ 
/*      */ 
/*      */       
/*  477 */       secretKey = smqiObject.newSecretKeyForPut(keySize);
/*      */ 
/*      */       
/*  480 */       protectedKey = envelopeData(policy, new CMSProcessableByteArray(new ASN1ObjectIdentifier(CMSObjectIdentifiers.data
/*  481 */               .getId()), secretKey));
/*      */ 
/*      */       
/*  484 */       smqiObject.cacheSecretKeyForPut(secretKey, protectedKey);
/*      */     } else {
/*      */       
/*  487 */       secretKey = protectedKeyArray[0];
/*  488 */       protectedKey = protectedKeyArray[1];
/*      */     } 
/*      */     
/*  491 */     CipherWriter cw = new CipherWriter(engine);
/*  492 */     byte[] ivAndData = cw.write(msg, secretKey);
/*      */     
/*  494 */     byte[] result = new byte[protectedKey.length + ivAndData.length];
/*  495 */     System.arraycopy(protectedKey, 0, result, 0, protectedKey.length);
/*  496 */     System.arraycopy(ivAndData, 0, result, protectedKey.length, ivAndData.length);
/*      */     
/*  498 */     hdr.setVersionMajor((byte)3);
/*  499 */     if (smqiObject.getKeyUsesRemaining() != 0)
/*      */     {
/*      */       
/*  502 */       hdr.setReuseKey(true);
/*      */     }
/*      */     
/*  505 */     hdr.setKeyBlockSize(protectedKey.length);
/*  506 */     hdr.setIVBlockSize((short)engine.getBlockSize());
/*  507 */     int encryptedBlockSize = ivAndData.length - engine.getBlockSize();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  517 */     hdr.setEncBlockSize(encryptedBlockSize);
/*      */     
/*  519 */     if (Trace.isOn) {
/*  520 */       Trace.exit(this, "MessageProtectionBCImpl", "makeConfidential(byte [ ],SmqiObject,SecurityPolicy,AMBIHeader)", result);
/*      */     }
/*      */     
/*  523 */     return result; } private BlockCipher getEngineForPut(String actualEncAlg) throws IllegalProtectionTypeException { RC2Engine rC2Engine; DESEngine dESEngine;
/*      */     DESedeEngine dESedeEngine;
/*      */     AESFastEngine aESFastEngine;
/*      */     HashMap<String, Object> inserts;
/*      */     IllegalProtectionTypeException ex;
/*  528 */     if (Trace.isOn) {
/*  529 */       Trace.entry(this, "com.ibm.mq.ese.prot.MessageProtectionBCImpl", "getEngineForPut(String)", new Object[] { actualEncAlg });
/*      */     }
/*      */     
/*  532 */     BlockCipher engineForPut = null;
/*  533 */     switch (actualEncAlg) {
/*      */       case "RC2":
/*  535 */         rC2Engine = new RC2Engine();
/*      */         break;
/*      */       case "DES":
/*  538 */         dESEngine = new DESEngine();
/*      */         break;
/*      */       case "DESede":
/*  541 */         dESedeEngine = new DESedeEngine();
/*      */         break;
/*      */       case "AES128":
/*  544 */         aESFastEngine = new AESFastEngine();
/*      */         break;
/*      */       case "AES256":
/*  547 */         aESFastEngine = new AESFastEngine();
/*      */         break;
/*      */       default:
/*  550 */         inserts = new HashMap<>();
/*  551 */         inserts.put("AMS_INSERT_ENCRYPTION_ALGORITHM_ID", actualEncAlg);
/*      */ 
/*      */         
/*  554 */         ex = new IllegalProtectionTypeException(AmsErrorMessages.mjp_msg_error_msg_protection_failed, inserts);
/*      */ 
/*      */         
/*  557 */         if (Trace.isOn) {
/*  558 */           Trace.throwing(this, "com.ibm.mq.ese.prot.MessageProtectionBCImpl", "getEngineForPut(String)", (Throwable)ex);
/*      */         }
/*  560 */         throw ex;
/*      */     } 
/*  562 */     if (Trace.isOn) {
/*  563 */       Trace.exit(this, "com.ibm.mq.ese.prot.MessageProtectionBCImpl", "getEngineForPut(String)", aESFastEngine);
/*      */     }
/*  565 */     return (BlockCipher)aESFastEngine; } private BlockCipher getEngineForGet(String actualEncAlg) throws IllegalProtectionTypeException { RC2Engine rC2Engine; DESEngine dESEngine; DESedeEngine dESedeEngine;
/*      */     AESFastEngine aESFastEngine;
/*      */     HashMap<String, Object> inserts;
/*      */     IllegalProtectionTypeException ex;
/*  569 */     if (Trace.isOn) {
/*  570 */       Trace.entry(this, "com.ibm.mq.ese.prot.MessageProtectionBCImpl", "getEngineForGet(String)", new Object[] { actualEncAlg });
/*      */     }
/*      */     
/*  573 */     BlockCipher engineForGet = null;
/*  574 */     switch (actualEncAlg) {
/*      */       case "RC2":
/*  576 */         rC2Engine = new RC2Engine();
/*      */         break;
/*      */       case "DES":
/*  579 */         dESEngine = new DESEngine();
/*      */         break;
/*      */       case "DESede":
/*  582 */         dESedeEngine = new DESedeEngine();
/*      */         break;
/*      */       case "AES128":
/*  585 */         aESFastEngine = new AESFastEngine();
/*      */         break;
/*      */       case "AES256":
/*  588 */         aESFastEngine = new AESFastEngine();
/*      */         break;
/*      */       default:
/*  591 */         inserts = new HashMap<>();
/*  592 */         inserts.put("AMS_INSERT_ENCRYPTION_ALGORITHM_ID", actualEncAlg);
/*      */ 
/*      */         
/*  595 */         ex = new IllegalProtectionTypeException(AmsErrorMessages.mjp_msg_error_msg_unprotection_failed, inserts);
/*      */         
/*  597 */         if (Trace.isOn) {
/*  598 */           Trace.throwing(this, "com.ibm.mq.ese.prot.MessageProtectionBCImpl", "getEngineForGet(String)", (Throwable)ex);
/*      */         }
/*  600 */         throw ex;
/*      */     } 
/*  602 */     if (Trace.isOn) {
/*  603 */       Trace.exit(this, "com.ibm.mq.ese.prot.MessageProtectionBCImpl", "getEngineForGet(String)", aESFastEngine);
/*      */     }
/*  605 */     return (BlockCipher)aESFastEngine; }
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
/*      */   public MessageUnprotectInfo unprotect(byte[] protMsg, SecurityPolicy policy, AMBIHeader hdr, SmqiObject smqiObject, EseUser user) throws MessageProtectionException {
/*  617 */     if (Trace.isOn) {
/*  618 */       Trace.entry(this, "MessageProtectionBCImpl", "unprotect(byte [ ],SecurityPolicy,AMBIHeader,SmqiObject,EseUser)", new Object[] { protMsg, policy, hdr, smqiObject, user });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  623 */     Date signDate = null; try {
/*      */       IllegalProtectionTypeException ex; String actualEncAlg; InputStream msgAsStream; IllegalProtectionTypeException illegalProtectionTypeException1; InputStream inputStream1; CMSEnvelopedData envelopedData; CMSSignedData cmsSignedData; String str1; CMSSignedData cMSSignedData1;
/*  625 */       if (protMsg == null) {
/*  626 */         IllegalArgumentException traceRet1 = new IllegalArgumentException("protMsg is null");
/*  627 */         if (Trace.isOn) {
/*  628 */           Trace.throwing(this, "MessageProtectionBCImpl", "unprotect(byte [ ],SecurityPolicy,AMBIHeader,SmqiObject,EseUser)", traceRet1, 1);
/*      */         }
/*      */         
/*  631 */         throw traceRet1;
/*      */       } 
/*  633 */       if (policy == null) {
/*  634 */         IllegalArgumentException traceRet2 = new IllegalArgumentException("policy is null");
/*  635 */         if (Trace.isOn) {
/*  636 */           Trace.throwing(this, "MessageProtectionBCImpl", "unprotect(byte [ ],SecurityPolicy,AMBIHeader,SmqiObject,EseUser)", traceRet2, 2);
/*      */         }
/*      */         
/*  639 */         throw traceRet2;
/*      */       } 
/*  641 */       if (user == null) {
/*  642 */         IllegalArgumentException traceRet3 = new IllegalArgumentException("userInfo is null");
/*  643 */         if (Trace.isOn) {
/*  644 */           Trace.throwing(this, "MessageProtectionBCImpl", "unprotect(byte [ ],SecurityPolicy,AMBIHeader,SmqiObject,EseUser)", traceRet3, 3);
/*      */         }
/*      */         
/*  647 */         throw traceRet3;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  652 */       int policyQop = policy.getQop();
/*  653 */       validateQop(policyQop);
/*      */       
/*  655 */       if (Trace.isOn) {
/*  656 */         Trace.traceInfo(this, "com.ibm.mq.ese.prot.MessageProtectionBCImpl", "unprotect(byte[], SecurityPolicy, EseUser)", "Protected Message Length: ", 
/*  657 */             Integer.valueOf(protMsg.length));
/*      */       }
/*  659 */       if (protMsg.length < 3) {
/*  660 */         ShortBufferException traceRet4 = new ShortBufferException("protMsg.length = " + protMsg.length);
/*  661 */         if (Trace.isOn) {
/*  662 */           Trace.throwing(this, "MessageProtectionBCImpl", "unprotect(byte [ ],SecurityPolicy,AMBIHeader,SmqiObject,EseUser)", traceRet4, 4);
/*      */         }
/*      */         
/*  665 */         throw traceRet4;
/*      */       } 
/*      */       
/*  668 */       int msgQop = hdr.getQop();
/*      */       
/*  670 */       switch (policyQop) {
/*      */         
/*      */         case 1:
/*  673 */           switch (msgQop) {
/*      */             case 1:
/*      */             case 2:
/*      */               break;
/*      */           } 
/*  678 */           ex = getIPTException(policyQop, msgQop);
/*  679 */           if (Trace.isOn) {
/*  680 */             Trace.throwing(this, "MessageProtectionBCImpl", "unprotect(byte [ ],SecurityPolicy,AMBIHeader,SmqiObject,EseUser)", (Throwable)ex, 5);
/*      */           }
/*      */           
/*  683 */           throw ex;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/*  689 */           switch (msgQop) {
/*      */             case 2:
/*      */               break;
/*      */           } 
/*  693 */           ex = getIPTException(policyQop, msgQop);
/*  694 */           if (Trace.isOn) {
/*  695 */             Trace.throwing(this, "MessageProtectionBCImpl", "unprotect(byte [ ],SecurityPolicy,AMBIHeader,SmqiObject,EseUser)", (Throwable)ex, 6);
/*      */           }
/*      */           
/*  698 */           throw ex;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 3:
/*  704 */           switch (msgQop) {
/*      */             case 2:
/*  706 */               policy.setSignAlg("DUMMY");
/*      */               break;
/*      */             case 3:
/*      */               break;
/*      */           } 
/*  711 */           ex = getIPTException(policyQop, msgQop);
/*  712 */           if (Trace.isOn) {
/*  713 */             Trace.throwing(this, "MessageProtectionBCImpl", "unprotect(byte [ ],SecurityPolicy,AMBIHeader,SmqiObject,EseUser)", (Throwable)ex, 7);
/*      */           }
/*      */           
/*  716 */           throw ex;
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/*  721 */           ex = getIPTException(policyQop, msgQop);
/*  722 */           if (Trace.isOn) {
/*  723 */             Trace.throwing(this, "MessageProtectionBCImpl", "unprotect(byte [ ],SecurityPolicy,AMBIHeader,SmqiObject,EseUser)", (Throwable)ex, 8);
/*      */           }
/*      */           
/*  726 */           throw ex;
/*      */       } 
/*      */       
/*  729 */       MessageUnprotectInfo ret = null;
/*      */       
/*  731 */       switch (msgQop) {
/*      */         case 1:
/*  733 */           actualEncAlg = "";
/*  734 */           inputStream1 = new ByteArrayInputStream(protMsg);
/*  735 */           cmsSignedData = new CMSSignedData(inputStream1);
/*  736 */           ret = getUnprotectedFromSigned(cmsSignedData, policy, user, msgQop, signDate, actualEncAlg);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 2:
/*  741 */           msgAsStream = new ByteArrayInputStream(protMsg);
/*  742 */           envelopedData = new CMSEnvelopedData(msgAsStream);
/*      */           
/*  744 */           str1 = getEncAlg(envelopedData);
/*      */           
/*  746 */           validateEncryptionStrength(str1, policy.getEncAlg());
/*      */           
/*  748 */           if (Trace.isOn) {
/*  749 */             Trace.traceInfo(this, "com.ibm.mq.ese.prot.MessageProtectionBCImpl", "unprotect(byte[], SecurityPolicy, EseUser)", "Privacy Protection used. Encryption algorithm ", str1);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  754 */           cMSSignedData1 = getFromEnvelope(user, envelopedData);
/*  755 */           ret = getUnprotectedFromSigned(cMSSignedData1, policy, user, msgQop, signDate, str1);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 3:
/*  760 */           ret = getUnprotectedFromConfidential(protMsg, policy, hdr, smqiObject, user, signDate, msgQop);
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/*  765 */           illegalProtectionTypeException1 = getIPTException(policyQop, msgQop);
/*  766 */           if (Trace.isOn) {
/*  767 */             Trace.throwing(this, "MessageProtectionBCImpl", "unprotect(byte [ ],SecurityPolicy,AMBIHeader,SmqiObject,EseUser)", (Throwable)illegalProtectionTypeException1, 9);
/*      */           }
/*      */           
/*  770 */           throw illegalProtectionTypeException1;
/*      */       } 
/*      */       
/*  773 */       if (Trace.isOn) {
/*  774 */         Trace.exit(this, "MessageProtectionBCImpl", "unprotect(byte [ ],SecurityPolicy,AMBIHeader,SmqiObject,EseUser)", ret);
/*      */       }
/*      */       
/*  777 */       return ret;
/*      */     }
/*  779 */     catch (Exception e) {
/*  780 */       if (Trace.isOn) {
/*  781 */         Trace.catchBlock(this, "MessageProtectionBCImpl", "unprotect(byte [ ],SecurityPolicy,AMBIHeader,SmqiObject,EseUser)", e);
/*      */       }
/*      */       
/*  784 */       Trace.catchBlock(this, "com.ibm.mq.ese.prot.MessageProtectionBCImpl", "unprotect(byte[], SecurityPolicy, EseUser)", e);
/*      */       
/*  786 */       AmsErrorMessages.logProtectionException("com.ibm.mq.ese.prot.MessageProtectionBCImpl", "unprotect(byte [ ],SecurityPolicy,AMBIHeader,SmqiObject,EseUser)", e);
/*      */       
/*  788 */       MessageProtectionException ex = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_msg_unprotection_failed, e);
/*      */       
/*  790 */       if (Trace.isOn) {
/*  791 */         Trace.throwing(this, "MessageProtectionBCImpl", "unprotect(byte [ ],SecurityPolicy,AMBIHeader,SmqiObject,EseUser)", (Throwable)ex, 10);
/*      */       }
/*      */       
/*  794 */       throw ex;
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MessageUnprotectInfo getUnprotectedFromConfidential(byte[] protMsg, SecurityPolicy policy, AMBIHeader hdr, SmqiObject smqiObject, EseUser user, Date signDate, int msgQop) throws Exception {
/*  814 */     if (Trace.isOn) {
/*  815 */       Trace.entry(this, "MessageProtectionBCImpl", "getUnprotectedFromConfidential(byte [ ],SecurityPolicy,AMBIHeader,SmqiObject,EseUser,Date,int)", new Object[] { protMsg, policy, hdr, smqiObject, user, signDate, 
/*      */             
/*  817 */             Integer.valueOf(msgQop) });
/*      */     }
/*      */ 
/*      */     
/*  821 */     byte[] secretKeyEncrypted = Arrays.copyOfRange(protMsg, 0, hdr.getKeyBlockSize());
/*      */     
/*  823 */     byte[] secretKey = hdr.reuseKeyIsset() ? smqiObject.checkSecretKeyForGet(secretKeyEncrypted) : null;
/*  824 */     String actualEncAlg = smqiObject.getEncAlgorithmForGet();
/*      */     
/*  826 */     if (secretKey == null) {
/*      */       
/*  828 */       InputStream envelopedKey = new ByteArrayInputStream(secretKeyEncrypted);
/*  829 */       CMSEnvelopedData envelopedData = new CMSEnvelopedData(envelopedKey);
/*      */       
/*  831 */       actualEncAlg = getEncAlg(envelopedData);
/*  832 */       validateEncryptionStrength(actualEncAlg, policy.getEncAlg());
/*      */       
/*  834 */       secretKey = getFromEnveloped(user, envelopedData);
/*  835 */       if (hdr.reuseKeyIsset()) {
/*      */         
/*  837 */         smqiObject.cacheSecretKeyForGet(secretKey, secretKeyEncrypted);
/*  838 */         smqiObject.cacheEncAlgorithmForGet(actualEncAlg);
/*      */       } 
/*      */     } 
/*      */     
/*  842 */     BlockCipher engine = getEngineForGet(actualEncAlg);
/*      */     
/*  844 */     CipherReader cr = new CipherReader(engine);
/*  845 */     int dataToDecryptSize = hdr.getKeyBlockSize() + hdr.getIVBlockSize() + hdr.getEncBlockSize();
/*      */     
/*  847 */     InputStream msgAsStream = new ByteArrayInputStream(Arrays.copyOf(protMsg, dataToDecryptSize));
/*  848 */     byte[] contentBytes = cr.read(hdr, msgAsStream, secretKey);
/*  849 */     MessageUnprotectInfo ret = new MessageUnprotectInfo(contentBytes, msgQop, signDate, actualEncAlg);
/*  850 */     if (Trace.isOn) {
/*  851 */       Trace.exit(this, "MessageProtectionBCImpl", "getUnprotectedFromConfidential(byte [ ],SecurityPolicy,AMBIHeader,SmqiObject,EseUser,Date,int)", ret);
/*      */     }
/*      */ 
/*      */     
/*  855 */     return ret;
/*      */   }
/*      */ 
/*      */   
/*      */   private MessageUnprotectInfo getUnprotectedFromSigned(CMSSignedData cmsSignedData, SecurityPolicy policy, EseUser user, int msgQop, Date signDate, String actualEncAlg) throws Exception {
/*  860 */     if (Trace.isOn) {
/*  861 */       Trace.entry(this, "MessageProtectionBCImpl", "getUnprotectedFromSigned(CMSSignedData,SecurityPolicy,EseUser,int,Date,String)", new Object[] { cmsSignedData, policy, user, 
/*      */             
/*  863 */             Integer.valueOf(msgQop), signDate, actualEncAlg });
/*      */     }
/*  865 */     X509Certificate signerCertificate = getSignerCertificate(cmsSignedData);
/*      */     
/*  867 */     String senderDN = X500NameWrapper.normalizeNames(signerCertificate.getSubjectX500Principal().getName());
/*      */     
/*  869 */     SignerInformationStore sis = cmsSignedData.getSignerInfos();
/*  870 */     Collection<SignerInformation> signers = sis.getSigners();
/*  871 */     SignerInformation signer = signers.iterator().next();
/*      */ 
/*      */     
/*  874 */     String sigAlgName = this.danFinder.getAlgorithmName(new ASN1ObjectIdentifier(signer.getDigestAlgOID()));
/*  875 */     validateSignatureAlg(sigAlgName, getSignAlgForBC(policy));
/*      */     
/*  877 */     validateSenderCertificate(signerCertificate, user, true);
/*      */     
/*  879 */     validateSignedData(signerCertificate, cmsSignedData, user);
/*      */     
/*  881 */     CMSProcessableByteArray content = (CMSProcessableByteArray)cmsSignedData.getSignedContent();
/*  882 */     byte[] contentBytes = (byte[])content.getContent();
/*      */     
/*  884 */     MessageUnprotectInfo ret = new MessageUnprotectInfo(senderDN, contentBytes, msgQop, signDate, sigAlgName, actualEncAlg);
/*      */ 
/*      */     
/*  887 */     if (Trace.isOn) {
/*  888 */       Trace.exit(this, "MessageProtectionBCImpl", "getUnprotectedFromSigned(CMSSignedData,SecurityPolicy,EseUser,int,Date,String)", ret);
/*      */     }
/*      */     
/*  891 */     return ret;
/*      */   }
/*      */   
/*      */   private IllegalProtectionTypeException getIPTException(int policyQop, int msgQop) {
/*  895 */     if (Trace.isOn)
/*  896 */       Trace.entry(this, "MessageProtectionBCImpl", "getIPTException(final int,int)", new Object[] {
/*  897 */             Integer.valueOf(policyQop), Integer.valueOf(msgQop)
/*      */           }); 
/*  899 */     HashMap<String, Object> inserts = new HashMap<>();
/*  900 */     inserts.put("AMS_INSERT_EXPECTED_QUALITY_OF_PROTECTION", MessageProtectionConstants.QOP_NAMES[policyQop]);
/*      */     
/*  902 */     inserts.put("AMS_INSERT_QUALITY_OF_PROTECTION", MessageProtectionConstants.QOP_NAMES[msgQop]);
/*      */     
/*  904 */     IllegalProtectionTypeException ex = new IllegalProtectionTypeException(AmsErrorMessages.mjp_msg_error_qop_mismatch, inserts);
/*      */     
/*  906 */     if (Trace.isOn) {
/*  907 */       Trace.exit(this, "MessageProtectionBCImpl", "getIPTException(final int,int)", ex);
/*      */     }
/*  909 */     return ex;
/*      */   }
/*      */ 
/*      */   
/*      */   private X509Certificate getSignerCertificate(CMSSignedData cmsSignedData) throws MissingCertificateException, MessageProtectionException, CertificateException {
/*  914 */     if (Trace.isOn) {
/*  915 */       Trace.entry(this, "MessageProtectionBCImpl", "getSignerCertificate(CMSSignedData)", new Object[] { cmsSignedData });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  920 */     Store certificateStore = cmsSignedData.getCertificates();
/*      */     
/*  922 */     Collection<X509CertificateHolder> certCollection = certificateStore.getMatches(new AlwaysSelector());
/*      */     
/*  924 */     if (certCollection == null || certCollection.size() < 1) {
/*  925 */       MissingCertificateException ex = new MissingCertificateException(AmsErrorMessages.mjp_msg_error_sender_certificate_not_found);
/*      */       
/*  927 */       if (Trace.isOn) {
/*  928 */         Trace.throwing(this, "MessageProtectionBCImpl", "getSignerCertificate(CMSSignedData)", (Throwable)ex, 1);
/*      */       }
/*  930 */       throw ex;
/*      */     } 
/*  932 */     if (certCollection.size() > 1) {
/*  933 */       MessageProtectionException ex = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_invalid_amount_of_sender_certificate);
/*      */       
/*  935 */       if (Trace.isOn) {
/*  936 */         Trace.throwing(this, "MessageProtectionBCImpl", "getSignerCertificate(CMSSignedData)", (Throwable)ex, 2);
/*      */       }
/*  938 */       throw ex;
/*      */     } 
/*      */     
/*  941 */     X509CertificateHolder signerCertHolder = certCollection.iterator().next();
/*  942 */     X509Certificate signerCertificate = this.certificateConverter.getCertificate(signerCertHolder);
/*  943 */     if (signerCertificate == null) {
/*  944 */       MissingCertificateException ex = new MissingCertificateException(AmsErrorMessages.mjp_msg_error_sender_certificate_not_found);
/*      */       
/*  946 */       if (Trace.isOn) {
/*  947 */         Trace.throwing(this, "MessageProtectionBCImpl", "getSignerCertificate(CMSSignedData)", (Throwable)ex, 3);
/*      */       }
/*  949 */       throw ex;
/*      */     } 
/*  951 */     if (Trace.isOn) {
/*  952 */       Trace.exit(this, "MessageProtectionBCImpl", "getSignerCertificate(CMSSignedData)", signerCertificate);
/*      */     }
/*  954 */     return signerCertificate;
/*      */   }
/*      */   
/*      */   private String getEncAlg(CMSEnvelopedData envelopedData) {
/*  958 */     if (Trace.isOn) {
/*  959 */       Trace.entry(this, "MessageProtectionBCImpl", "getEncAlg(CMSEnvelopedData)", new Object[] { envelopedData });
/*      */     }
/*  961 */     AlgorithmIdentifier encAlgId = envelopedData.getContentEncryptionAlgorithm();
/*  962 */     String encAlg = this.danFinder.getAlgorithmName(encAlgId);
/*  963 */     String result = null;
/*      */     
/*  965 */     switch (encAlg) {
/*      */       case "2.16.840.1.101.3.4.1.2":
/*      */       case "AES-128/CBC":
/*  968 */         result = "AES128";
/*      */         break;
/*      */       case "2.16.840.1.101.3.4.1.42":
/*      */       case "AES-256/CBC":
/*  972 */         result = "AES256";
/*      */         break;
/*      */       case "1.2.840.113549.3.2":
/*      */       case "RC2/CBC":
/*  976 */         result = "RC2";
/*      */         break;
/*      */       case "1.3.14.3.2.7":
/*  979 */         result = "DES";
/*      */         break;
/*      */       case "1.2.840.113549.3.7":
/*      */       case "DESEDE-3KEY/CBC":
/*  983 */         result = "DESede";
/*      */         break;
/*      */       default:
/*  986 */         result = encAlg;
/*      */         break;
/*      */     } 
/*  989 */     if (Trace.isOn) {
/*  990 */       Trace.exit(this, "MessageProtectionBCImpl", "getEncAlg(CMSEnvelopedData)", result);
/*      */     }
/*  992 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   private CMSSignedData getFromEnvelope(EseUser user, CMSEnvelopedData envelopedData) throws MessageProtectionException, InvalidCertificateException, AMBIException, CMSException, IOException {
/*  997 */     if (Trace.isOn) {
/*  998 */       Trace.entry(this, "MessageProtectionBCImpl", "getFromEnvelope(EseUser,CMSEnvelopedData)", new Object[] { user, envelopedData });
/*      */     }
/*      */ 
/*      */     
/* 1002 */     byte[] recipientData = getFromEnveloped(user, envelopedData);
/* 1003 */     CMSSignedData cmsSignedData = AMSsignedToCMSSignedData(recipientData);
/* 1004 */     if (Trace.isOn) {
/* 1005 */       Trace.exit(this, "MessageProtectionBCImpl", "getFromEnvelope(EseUser,CMSEnvelopedData)", cmsSignedData);
/*      */     }
/* 1007 */     return cmsSignedData;
/*      */   }
/*      */ 
/*      */   
/*      */   private byte[] getFromEnveloped(EseUser user, CMSEnvelopedData envelopedData) throws MessageProtectionException, InvalidCertificateException, AMBIException, CMSException {
/* 1012 */     if (Trace.isOn) {
/* 1013 */       Trace.entry(this, "MessageProtectionBCImpl", "getFromEnveloped(EseUser,CMSEnvelopedData)", new Object[] { user, envelopedData });
/*      */     }
/*      */ 
/*      */     
/* 1017 */     X509Certificate recipientCert = user.getCertificate();
/* 1018 */     if (recipientCert == null) {
/* 1019 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1020 */       inserts.put("AMS_INSERT_CREDENTIAL_ALIAS", user.getKeystoreAlias());
/* 1021 */       inserts.put("AMS_INSERT_FILENAME", user.getKeyStoreAccess().toString());
/* 1022 */       MessageProtectionException ex = new MessageProtectionException(AmsErrorMessages.mju_user_certificate_not_found_MessageProtectionException, inserts);
/*      */       
/* 1024 */       if (Trace.isOn) {
/* 1025 */         Trace.throwing(this, "MessageProtectionBCImpl", "getFromEnveloped(EseUser,CMSEnvelopedData)", (Throwable)ex, 1);
/*      */       }
/* 1027 */       throw ex;
/*      */     } 
/* 1029 */     validateRecipientCertificate(recipientCert, user);
/* 1030 */     RecipientInformationStore recipientStore = envelopedData.getRecipientInfos();
/*      */     
/* 1032 */     JceKeyTransRecipientId jceKeyTransRecipientId = new JceKeyTransRecipientId(user.getCertificate());
/*      */ 
/*      */     
/* 1035 */     RecipientInformation recipient = recipientStore.get((RecipientId)jceKeyTransRecipientId);
/*      */     
/* 1037 */     if (recipient == null) {
/* 1038 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1039 */       inserts.put("AMS_INSERT_DISTINGUISHED_NAME", user.getUserDN());
/* 1040 */       AMBIException ex = new AMBIException(AmsErrorMessages.mjp_msg_error_user_not_in_recipient, inserts);
/* 1041 */       if (Trace.isOn) {
/* 1042 */         Trace.throwing(this, "MessageProtectionBCImpl", "getFromEnveloped(EseUser,CMSEnvelopedData)", (Throwable)ex, 2);
/*      */       }
/* 1044 */       throw ex;
/*      */     } 
/*      */     
/* 1047 */     byte[] recipientData = recipient.getContent((Recipient)new JceKeyTransEnvelopedRecipient(user.getPrivateKey()));
/* 1048 */     if (Trace.isOn) {
/* 1049 */       Trace.exit(this, "MessageProtectionBCImpl", "getFromEnveloped(EseUser,CMSEnvelopedData)", recipientData);
/*      */     }
/* 1051 */     return recipientData;
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
/*      */   private void validateSenderCertificate(X509Certificate senderCertificate, EseUser user, boolean checkRevocation) throws InvalidCertificateException {
/* 1065 */     if (Trace.isOn) {
/* 1066 */       Trace.entry(this, "MessageProtectionBCImpl", "validateSenderCertificate(X509Certificate,EseUser, boolean)", new Object[] { senderCertificate, user, 
/* 1067 */             Boolean.valueOf(checkRevocation) });
/*      */     }
/*      */     
/*      */     try {
/* 1071 */       this.certificateValidator.validateX509Certificate(senderCertificate, X509CertificateValidator.SENDER_KEY_USAGE, X509CertificateValidator.SENDER_KEY_USAGE_MATCH, false, checkRevocation, user);
/*      */     
/*      */     }
/* 1074 */     catch (InvalidCertificateException e) {
/* 1075 */       if (Trace.isOn) {
/* 1076 */         Trace.catchBlock(this, "MessageProtectionBCImpl", "validateSenderCertificate(X509Certificate,EseUser)", (Throwable)e, 1);
/*      */       }
/*      */       
/* 1079 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1080 */       inserts.put("AMS_INSERT_CERTIFICATE_SUBJECT", senderCertificate
/* 1081 */           .getSubjectX500Principal().getName());
/*      */ 
/*      */       
/* 1084 */       InvalidCertificateException ex = new InvalidCertificateException(AmsErrorMessages.mjp_msg_error_msg_sender_cert_not_valid_InvalidCertificateException, inserts, e.getCause());
/* 1085 */       if (Trace.isOn) {
/* 1086 */         Trace.throwing(this, "MessageProtectionBCImpl", "validateSenderCertificate(X509Certificate,EseUser)", (Throwable)ex, 1);
/*      */       }
/*      */       
/* 1089 */       throw ex;
/*      */     }
/* 1091 */     catch (Exception e) {
/* 1092 */       if (Trace.isOn) {
/* 1093 */         Trace.catchBlock(this, "MessageProtectionBCImpl", "validateSenderCertificate(X509Certificate,EseUser)", e, 2);
/*      */       }
/*      */       
/* 1096 */       if (Trace.isOn) {
/* 1097 */         Trace.catchBlock(this, "com.ibm.mq.ese.prot.MessageProtectionBCImpl", "validateSenderCertificate(X509Certificate, EseUser)", e);
/*      */       }
/*      */       
/* 1100 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1101 */       inserts.put("AMS_INSERT_CERTIFICATE_SUBJECT", senderCertificate
/* 1102 */           .getSubjectX500Principal().getName());
/* 1103 */       InvalidCertificateException ex = new InvalidCertificateException(AmsErrorMessages.mjp_msg_error_msg_sender_cert_not_valid_InvalidCertificateException, inserts, e);
/*      */       
/* 1105 */       if (Trace.isOn) {
/* 1106 */         Trace.throwing(this, "MessageProtectionBCImpl", "validateSenderCertificate(X509Certificate,EseUser)", (Throwable)ex, 2);
/*      */       }
/*      */       
/* 1109 */       throw ex;
/*      */     } 
/*      */     
/* 1112 */     if (Trace.isOn) {
/* 1113 */       Trace.exit(this, "MessageProtectionBCImpl", "validateSenderCertificate(X509Certificate,EseUser)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void validateSignedData(X509Certificate signerCertificate, CMSSignedData cmsSignedData, EseUser user) throws Exception {
/* 1120 */     if (Trace.isOn) {
/* 1121 */       Trace.entry(this, "MessageProtectionBCImpl", "validateSignedData(X509Certificate,CMSSignedData,EseUser)", new Object[] { signerCertificate, cmsSignedData, user });
/*      */     }
/*      */ 
/*      */     
/* 1125 */     CertStore certsAndCRLs = (new JcaCertStoreBuilder()).setProvider("BC").addCertificates(cmsSignedData.getCertificates()).build();
/* 1126 */     SignerInformationStore signers = cmsSignedData.getSignerInfos();
/* 1127 */     Iterator<SignerInformation> it = signers.getSigners().iterator();
/*      */     
/* 1129 */     boolean valid = false;
/*      */     
/* 1131 */     if (it.hasNext()) {
/* 1132 */       SignerInformation signer = it.next();
/* 1133 */       X509CertSelector signerConstraints = (new JcaX509CertSelectorConverter()).getCertSelector(signer.getSID());
/*      */       
/* 1135 */       signerConstraints.setKeyUsage(getKeyUsage(128));
/*      */       
/*      */       try {
/*      */         PKIXBuilderParameters builderParams;
/* 1139 */         if (isSelfSigned(signerCertificate)) {
/* 1140 */           builderParams = constructPKIXBuilderParameters(signerCertificate, signerConstraints);
/*      */         } else {
/*      */           
/* 1143 */           KeyStoreAccess ks = user.getKeyStoreAccess();
/* 1144 */           if (ks instanceof CompositeKeyStoreAccess) {
/* 1145 */             builderParams = constructPKIXBuilderParameters(((CompositeKeyStoreAccess)ks).getSecondaryKeyStore(), signerConstraints);
/*      */           } else {
/*      */             
/* 1148 */             builderParams = constructPKIXBuilderParameters(ks.getKeyStore(), signerConstraints);
/*      */           } 
/*      */         } 
/* 1151 */         configurePKIXBuilderParameters(builderParams, certsAndCRLs);
/*      */         
/* 1153 */         PKIXCertPathBuilderResult result = buildPath(builderParams);
/*      */         
/* 1155 */         CertPath certPath = result.getCertPath();
/* 1156 */         List<? extends Certificate> certificates = certPath.getCertificates();
/*      */         try {
/* 1158 */           valid = signer.verify((new JcaSimpleSignerInfoVerifierBuilder()).setProvider("BC")
/* 1159 */               .build((X509Certificate)certificates.get(0)));
/*      */         }
/* 1161 */         catch (OperatorCreationException|CMSException e) {
/* 1162 */           if (Trace.isOn) {
/* 1163 */             Trace.catchBlock(this, "MessageProtectionBCImpl", "validateSignedData(X509Certificate,CMSSignedData,EseUser)", e, 1);
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 1170 */       catch (CertPathBuilderException e) {
/* 1171 */         if (Trace.isOn) {
/* 1172 */           Trace.catchBlock(this, "MessageProtectionBCImpl", "validateSignedData(X509Certificate,CMSSignedData,EseUser)", e, 2);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1180 */     if (!valid) {
/* 1181 */       Exception traceRet1 = new Exception("No suitable trust path found");
/* 1182 */       if (Trace.isOn) {
/* 1183 */         Trace.throwing(this, "MessageProtectionBCImpl", "validateSignedData(X509Certificate,CMSSignedData,EseUser)", traceRet1);
/*      */       }
/*      */       
/* 1186 */       throw traceRet1;
/*      */     } 
/* 1188 */     if (Trace.isOn) {
/* 1189 */       Trace.exit(this, "MessageProtectionBCImpl", "validateSignedData(X509Certificate,CMSSignedData,EseUser)");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean isSelfSigned(X509Certificate cert) {
/* 1195 */     if (Trace.isOn) {
/* 1196 */       Trace.entry("MessageProtectionBCImpl", "isSelfSigned(X509Certificate)", new Object[] { cert });
/*      */     }
/*      */     
/* 1199 */     boolean traceRet1 = cert.getIssuerX500Principal().equals(cert.getSubjectX500Principal());
/*      */     
/* 1201 */     if (traceRet1) {
/*      */       try {
/* 1203 */         cert.verify(cert.getPublicKey());
/*      */       }
/* 1205 */       catch (InvalidKeyException|CertificateException|NoSuchAlgorithmException|NoSuchProviderException|java.security.SignatureException e) {
/*      */ 
/*      */         
/* 1208 */         if (Trace.isOn) {
/* 1209 */           Trace.catchBlock(MessageProtectionBCImpl.class, "MessageProtectionBCImpl", "isSelfSigned(X509Certificate)", e, 1);
/*      */         }
/*      */         
/* 1212 */         traceRet1 = false;
/*      */       } 
/*      */     }
/*      */     
/* 1216 */     if (Trace.isOn) {
/* 1217 */       Trace.exit("MessageProtectionBCImpl", "isSelfSigned(X509Certificate)", 
/* 1218 */           Boolean.valueOf(traceRet1));
/*      */     }
/* 1220 */     return traceRet1;
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
/*      */   private static PKIXCertPathBuilderResult buildPath(PKIXBuilderParameters buildParams) throws NoSuchAlgorithmException, NoSuchProviderException, CertPathBuilderException, InvalidAlgorithmParameterException {
/* 1235 */     if (Trace.isOn) {
/* 1236 */       Trace.entry("MessageProtectionBCImpl", "buildPath(PKIXBuilderParameters)", new Object[] { buildParams });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1241 */     CertPathBuilder builder = CertPathBuilder.getInstance("PKIX", "BC");
/*      */     
/* 1243 */     PKIXCertPathBuilderResult traceRet1 = (PKIXCertPathBuilderResult)builder.build(buildParams);
/*      */     
/* 1245 */     if (Trace.isOn) {
/* 1246 */       Trace.exit("MessageProtectionBCImpl", "buildPath(PKIXBuilderParameters)", traceRet1);
/*      */     }
/*      */     
/* 1249 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static PKIXBuilderParameters constructPKIXBuilderParameters(X509Certificate rootCert, X509CertSelector endConstraints) throws InvalidAlgorithmParameterException {
/* 1257 */     PKIXBuilderParameters buildParams = new PKIXBuilderParameters(Collections.singleton(new TrustAnchor(rootCert, null)), endConstraints);
/*      */     
/* 1259 */     return buildParams;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static PKIXBuilderParameters constructPKIXBuilderParameters(KeyStore keyStore, X509CertSelector endConstraints) throws KeyStoreException, InvalidAlgorithmParameterException {
/* 1266 */     PKIXBuilderParameters buildParams = new PKIXBuilderParameters(keyStore, endConstraints);
/* 1267 */     return buildParams;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void configurePKIXBuilderParameters(PKIXBuilderParameters buildParams, CertStore certsAndCRLs) {
/* 1273 */     buildParams.addCertStore(certsAndCRLs);
/* 1274 */     buildParams.setRevocationEnabled(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean[] getKeyUsage(int mask) {
/* 1284 */     if (Trace.isOn) {
/* 1285 */       Trace.entry("MessageProtectionBCImpl", "getKeyUsage(int)", new Object[] { Integer.valueOf(mask) });
/*      */     }
/* 1287 */     byte[] bytes = { (byte)(mask & 0xFF), (byte)((mask & 0xFF00) >> 8) };
/* 1288 */     boolean[] keyUsage = new boolean[9];
/*      */     
/* 1290 */     for (int i = 0; i != 9; i++) {
/* 1291 */       keyUsage[i] = ((bytes[i / 8] & 128 >>> i % 8) != 0);
/*      */     }
/*      */     
/* 1294 */     if (Trace.isOn) {
/* 1295 */       Trace.exit("MessageProtectionBCImpl", "getKeyUsage(int)", keyUsage);
/*      */     }
/* 1297 */     return keyUsage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void validateQop(int qop) throws IllegalProtectionTypeException {
/*      */     String qopName;
/*      */     IllegalProtectionTypeException traceRet1;
/* 1308 */     if (Trace.isOn) {
/* 1309 */       Trace.entry(this, "MessageProtectionBCImpl", "validateQop(int)", new Object[] { Integer.valueOf(qop) });
/*      */     }
/*      */     
/* 1312 */     switch (qop) {
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/*      */         break;
/*      */ 
/*      */       
/*      */       default:
/* 1320 */         qopName = (qop < MessageProtectionConstants.QOP_NAMES.length) ? MessageProtectionConstants.QOP_NAMES[qop] : Integer.toString(qop);
/*      */         
/* 1322 */         traceRet1 = IllegalProtectionTypeException.create(qopName, new IllegalArgumentException(qopName));
/* 1323 */         if (Trace.isOn) {
/* 1324 */           Trace.throwing(this, "MessageProtectionBCImpl", "validateQop(int)", (Throwable)traceRet1);
/*      */         }
/* 1326 */         throw traceRet1;
/*      */     } 
/*      */     
/* 1329 */     if (Trace.isOn) {
/* 1330 */       Trace.exit(this, "MessageProtectionBCImpl", "validateQop(int)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isValid() {
/* 1340 */     if (Trace.isOn) {
/* 1341 */       Trace.data(this, "MessageProtectionBCImpl", "isValid()", "getter", Boolean.valueOf(true));
/*      */     }
/* 1343 */     return true;
/*      */   }
/*      */   
/*      */   private CMSSignedData sign(byte[] data, SecurityPolicy policy, EseUser userInfo) throws AMBIException {
/* 1347 */     if (Trace.isOn) {
/* 1348 */       Trace.entry(this, "MessageProtectionBCImpl", "sign(byte [ ],SecurityPolicy,EseUser)", new Object[] { data, policy, userInfo });
/*      */     }
/*      */ 
/*      */     
/* 1352 */     CMSSignedData traceRet1 = sign(data, getSignAlgForBC(policy), userInfo);
/* 1353 */     if (Trace.isOn) {
/* 1354 */       Trace.exit(this, "MessageProtectionBCImpl", "sign(byte [ ],SecurityPolicy,EseUser)", traceRet1);
/*      */     }
/* 1356 */     return traceRet1;
/*      */   }
/*      */   
/*      */   private CMSSignedData sign(byte[] data, String signAlg, EseUser userInfo) throws AMBIException {
/* 1360 */     if (Trace.isOn) {
/* 1361 */       Trace.entry(this, "MessageProtectionBCImpl", "sign(byte [ ],String,EseUser)", new Object[] { data, signAlg, userInfo });
/*      */     }
/*      */     
/* 1364 */     PrivateKey key = userInfo.getPrivateKey();
/* 1365 */     X509Certificate cert = userInfo.getCertificate();
/*      */     
/*      */     try {
/* 1368 */       Store certs = null;
/* 1369 */       JcaCertStore jcaCertStore = new JcaCertStore(Arrays.asList(new X509Certificate[] { cert }));
/* 1370 */       SignerInfoGenerator signerInfoGenerator = null;
/*      */       try {
/* 1372 */         DigestCalculatorProvider digestProvider = (new JcaDigestCalculatorProviderBuilder()).build();
/* 1373 */         IBMCMSSignatureEncryptionAlgorithmFinder iBMCMSSignatureEncryptionAlgorithmFinder = new IBMCMSSignatureEncryptionAlgorithmFinder();
/*      */         
/* 1375 */         JcaSignerInfoGeneratorBuilder signerInfoGeneratorBuilder = new JcaSignerInfoGeneratorBuilder(digestProvider, (CMSSignatureEncryptionAlgorithmFinder)iBMCMSSignatureEncryptionAlgorithmFinder);
/*      */         
/* 1377 */         ContentSigner contentSigner = (new JcaContentSignerBuilder(signAlg)).build(key);
/*      */         
/* 1379 */         SignerInfoGenerator signerInfoGeneratorTemplate = signerInfoGeneratorBuilder.build(contentSigner, cert);
/*      */ 
/*      */         
/* 1382 */         final CMSAttributeTableGenerator signedAttributeTableGenerator = signerInfoGeneratorTemplate.getSignedAttributeTableGenerator();
/*      */         
/* 1384 */         CMSAttributeTableGenerator unsignedAttributeTableGenerator = signerInfoGeneratorTemplate.getUnsignedAttributeTableGenerator();
/*      */         
/* 1386 */         DefaultSignedAttributeTableGenerator defaultSignedAttributeTableGenerator = new DefaultSignedAttributeTableGenerator()
/*      */           {
/*      */             public AttributeTable getAttributes(Map parameters)
/*      */             {
/* 1390 */               AttributeTable attributeTable = signedAttributeTableGenerator.getAttributes(parameters);
/* 1391 */               attributeTable = makeRFC3851Compliant(attributeTable);
/* 1392 */               return attributeTable;
/*      */             }
/*      */             
/*      */             private AttributeTable makeRFC3851Compliant(AttributeTable attributeTable) {
/* 1396 */               return attributeTable.remove(CMSAttributes.cmsAlgorithmProtect);
/*      */             }
/*      */           };
/*      */         
/* 1400 */         signerInfoGenerator = new SignerInfoGenerator(signerInfoGeneratorTemplate, (CMSAttributeTableGenerator)defaultSignedAttributeTableGenerator, unsignedAttributeTableGenerator);
/*      */ 
/*      */       
/*      */       }
/* 1404 */       catch (IllegalArgumentException e) {
/* 1405 */         if (Trace.isOn) {
/* 1406 */           Trace.catchBlock(this, "MessageProtectionBCImpl", "sign(byte [ ],String,EseUser)", e, 1);
/*      */         }
/* 1408 */         IllegalAlgorithmNameException traceRet1 = new IllegalAlgorithmNameException(signAlg, null);
/* 1409 */         if (Trace.isOn) {
/* 1410 */           Trace.throwing(this, "MessageProtectionBCImpl", "sign(byte [ ],String,EseUser)", (Throwable)traceRet1, 1);
/*      */         }
/* 1412 */         throw traceRet1;
/*      */       } 
/*      */       
/* 1415 */       CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
/* 1416 */       gen.addSignerInfoGenerator(signerInfoGenerator);
/* 1417 */       gen.addCertificates((Store)jcaCertStore);
/*      */ 
/*      */       
/* 1420 */       CMSProcessableByteArray cMSProcessableByteArray = new CMSProcessableByteArray(data);
/* 1421 */       CMSSignedData traceRet2 = gen.generate((CMSTypedData)cMSProcessableByteArray, true);
/* 1422 */       if (Trace.isOn) {
/* 1423 */         Trace.exit(this, "MessageProtectionBCImpl", "sign(byte [ ],String,EseUser)", traceRet2);
/*      */       }
/* 1425 */       return traceRet2;
/*      */     }
/* 1427 */     catch (CMSException|CertificateEncodingException|OperatorCreationException e) {
/* 1428 */       if (Trace.isOn) {
/* 1429 */         Trace.catchBlock(this, "MessageProtectionBCImpl", "sign(byte [ ],String,EseUser)", e, 2);
/*      */       }
/* 1431 */       MessageProtectionException traceRet3 = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_msg_protection_failed, e);
/*      */       
/* 1433 */       if (Trace.isOn) {
/* 1434 */         Trace.throwing(this, "MessageProtectionBCImpl", "sign(byte [ ],String,EseUser)", (Throwable)traceRet3, 2);
/*      */       }
/* 1436 */       throw traceRet3;
/*      */     } 
/*      */   }
/*      */   
/*      */   private String getSignAlgForBC(SecurityPolicy policy) {
/* 1441 */     if (Trace.isOn) {
/* 1442 */       Trace.entry(this, "MessageProtectionBCImpl", "getSignAlgForBC(SecurityPolicy)", new Object[] { policy });
/*      */     }
/* 1444 */     String signAlg = policy.getSignAlg();
/* 1445 */     String result = this.sha2NameMapping.get(signAlg);
/* 1446 */     if (result == null) {
/* 1447 */       result = signAlg;
/*      */     }
/* 1449 */     if (Trace.isOn) {
/* 1450 */       Trace.exit(this, "MessageProtectionBCImpl", "getSignAlgForBC(SecurityPolicy)", result);
/*      */     }
/* 1452 */     return result;
/*      */   }
/*      */   
/*      */   private byte[] envelopeSignedData(CMSSignedData signed, SecurityPolicy policy, EseUser user) throws AMBIException {
/* 1456 */     if (Trace.isOn) {
/* 1457 */       Trace.entry(this, "MessageProtectionBCImpl", "envelopeSignedData(CMSSignedData,SecurityPolicy,EseUser)", new Object[] { signed, policy, user });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1463 */     ContentInfo ci = signed.toASN1Structure();
/* 1464 */     SignedData content = (SignedData)ci.getContent();
/* 1465 */     byte[] signedEnc = null;
/*      */     try {
/* 1467 */       signedEnc = content.getEncoded("DER");
/*      */     }
/* 1469 */     catch (IOException e) {
/* 1470 */       if (Trace.isOn) {
/* 1471 */         Trace.catchBlock(this, "MessageProtectionBCImpl", "envelopeSignedData(CMSSignedData,SecurityPolicy,EseUser)", e, 1);
/*      */       }
/*      */       
/* 1474 */       MessageProtectionException traceRet1 = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_msg_protection_failed, e);
/*      */       
/* 1476 */       if (Trace.isOn) {
/* 1477 */         Trace.throwing(this, "MessageProtectionBCImpl", "envelopeSignedData(CMSSignedData,SecurityPolicy,EseUser)", (Throwable)traceRet1, 1);
/*      */       }
/*      */       
/* 1480 */       throw traceRet1;
/*      */     } 
/* 1482 */     byte[] signedEncoded = null;
/*      */     try {
/* 1484 */       signedEncoded = decodeFromDER(signedEnc);
/*      */     }
/* 1486 */     catch (IOException e) {
/* 1487 */       if (Trace.isOn) {
/* 1488 */         Trace.catchBlock(this, "MessageProtectionBCImpl", "envelopeSignedData(CMSSignedData,SecurityPolicy,EseUser)", e, 2);
/*      */       }
/*      */       
/* 1491 */       MessageProtectionException traceRet2 = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_msg_protection_failed, e);
/*      */       
/* 1493 */       if (Trace.isOn) {
/* 1494 */         Trace.throwing(this, "MessageProtectionBCImpl", "envelopeSignedData(CMSSignedData,SecurityPolicy,EseUser)", (Throwable)traceRet2, 2);
/*      */       }
/*      */       
/* 1497 */       throw traceRet2;
/*      */     } 
/*      */     
/* 1500 */     byte[] traceRet3 = envelopeData(policy, new CMSProcessableByteArray(new ASN1ObjectIdentifier(CMSObjectIdentifiers.signedData
/* 1501 */             .getId()), signedEncoded));
/* 1502 */     if (Trace.isOn) {
/* 1503 */       Trace.exit(this, "MessageProtectionBCImpl", "envelopeSignedData(CMSSignedData,SecurityPolicy,EseUser)", traceRet3);
/*      */     }
/*      */     
/* 1506 */     return traceRet3;
/*      */   }
/*      */ 
/*      */   
/*      */   private byte[] envelopeData(SecurityPolicy policy, CMSProcessableByteArray dataAsProcessableByteArray) throws MessageProtectionException, IllegalAlgorithmNameException {
/* 1511 */     if (Trace.isOn) {
/* 1512 */       Trace.entry(this, "MessageProtectionBCImpl", "envelopeData(SecurityPolicy,CMSProcessableByteArray)", new Object[] { policy, dataAsProcessableByteArray });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1517 */     CMSEnvelopedDataGenerator edGen = new CMSEnvelopedDataGenerator();
/*      */ 
/*      */     
/* 1520 */     for (X509Certificate recipientsCertificate : policy.getRecipientsCertificates()) {
/* 1521 */       JceKeyTransRecipientInfoGenerator jceKeyTransRecipientInfoGenerator; RecipientInfoGenerator rig = null;
/*      */       try {
/* 1523 */         jceKeyTransRecipientInfoGenerator = new JceKeyTransRecipientInfoGenerator(recipientsCertificate);
/*      */       }
/* 1525 */       catch (CertificateEncodingException e) {
/* 1526 */         if (Trace.isOn) {
/* 1527 */           Trace.catchBlock(this, "MessageProtectionBCImpl", "envelopeData(SecurityPolicy,CMSProcessableByteArray)", e, 1);
/*      */         }
/*      */         
/* 1530 */         MessageProtectionException traceRet1 = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_msg_protection_failed, e);
/*      */         
/* 1532 */         if (Trace.isOn) {
/* 1533 */           Trace.throwing(this, "MessageProtectionBCImpl", "envelopeData(SecurityPolicy,CMSProcessableByteArray)", (Throwable)traceRet1, 1);
/*      */         }
/*      */         
/* 1536 */         throw traceRet1;
/*      */       } 
/*      */       
/* 1539 */       edGen.addRecipientInfoGenerator((RecipientInfoGenerator)jceKeyTransRecipientInfoGenerator);
/*      */     } 
/*      */     
/* 1542 */     String encAlgOID = AlgorithmHelper.getAlgorithmOID(policy.getEncAlg());
/* 1543 */     if (encAlgOID == null) {
/* 1544 */       IllegalAlgorithmNameException traceRet2 = new IllegalAlgorithmNameException(policy.getEncAlg(), null);
/* 1545 */       if (Trace.isOn) {
/* 1546 */         Trace.throwing(this, "MessageProtectionBCImpl", "envelopeData(SecurityPolicy,CMSProcessableByteArray)", (Throwable)traceRet2, 2);
/*      */       }
/*      */       
/* 1549 */       throw traceRet2;
/*      */     } 
/*      */     
/* 1552 */     OutputEncryptor outputEncryptor = null;
/*      */ 
/*      */     
/*      */     try {
/* 1556 */       outputEncryptor = (new JceCMSContentEncryptorBuilder(new ASN1ObjectIdentifier(encAlgOID))).setProvider("BC").build();
/*      */     }
/* 1558 */     catch (CMSException e) {
/* 1559 */       if (Trace.isOn) {
/* 1560 */         Trace.catchBlock(this, "MessageProtectionBCImpl", "envelopeData(SecurityPolicy,CMSProcessableByteArray)", (Throwable)e, 2);
/*      */       }
/*      */       
/* 1563 */       MessageProtectionException traceRet3 = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_msg_protection_failed, (Throwable)e);
/*      */       
/* 1565 */       if (Trace.isOn) {
/* 1566 */         Trace.throwing(this, "MessageProtectionBCImpl", "envelopeData(SecurityPolicy,CMSProcessableByteArray)", (Throwable)traceRet3, 3);
/*      */       }
/*      */       
/* 1569 */       throw traceRet3;
/*      */     } 
/*      */     
/* 1572 */     CMSEnvelopedData ed = null;
/*      */     try {
/* 1574 */       ed = edGen.generate((CMSTypedData)dataAsProcessableByteArray, outputEncryptor);
/*      */     }
/* 1576 */     catch (CMSException e) {
/* 1577 */       if (Trace.isOn) {
/* 1578 */         Trace.catchBlock(this, "MessageProtectionBCImpl", "envelopeData(SecurityPolicy,CMSProcessableByteArray)", (Throwable)e, 3);
/*      */       }
/*      */       
/* 1581 */       MessageProtectionException traceRet4 = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_msg_protection_failed, (Throwable)e);
/*      */       
/* 1583 */       if (Trace.isOn) {
/* 1584 */         Trace.throwing(this, "MessageProtectionBCImpl", "envelopeData(SecurityPolicy,CMSProcessableByteArray)", (Throwable)traceRet4, 4);
/*      */       }
/*      */       
/* 1587 */       throw traceRet4;
/*      */     } 
/*      */     
/* 1590 */     ContentInfo edContent = ed.toASN1Structure();
/*      */     try {
/* 1592 */       byte[] traceRet7 = edContent.getEncoded("DL");
/* 1593 */       if (Trace.isOn) {
/* 1594 */         Trace.exit(this, "MessageProtectionBCImpl", "envelopeData(SecurityPolicy,CMSProcessableByteArray)", traceRet7);
/*      */       }
/*      */       
/* 1597 */       return traceRet7;
/*      */     }
/* 1599 */     catch (IOException e) {
/* 1600 */       if (Trace.isOn) {
/* 1601 */         Trace.catchBlock(this, "MessageProtectionBCImpl", "envelopeData(SecurityPolicy,CMSProcessableByteArray)", e, 4);
/*      */       }
/*      */       
/* 1604 */       MessageProtectionException traceRet5 = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_msg_protection_failed, e);
/*      */       
/* 1606 */       if (Trace.isOn) {
/* 1607 */         Trace.throwing(this, "MessageProtectionBCImpl", "envelopeData(SecurityPolicy,CMSProcessableByteArray)", (Throwable)traceRet5, 5);
/*      */       }
/*      */       
/* 1610 */       throw traceRet5;
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
/*      */   private static byte[] encodeAsDER(byte[] data) throws IOException {
/* 1624 */     if (Trace.isOn) {
/* 1625 */       Trace.entry("MessageProtectionBCImpl", "encodeAsDER(byte [ ])", new Object[] { data });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1631 */     int datalength = data.length;
/* 1632 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 1633 */     baos.write(48);
/*      */ 
/*      */     
/* 1636 */     int lengthBytes = 0;
/* 1637 */     int temp = datalength;
/* 1638 */     for (lengthBytes = 0; temp > 0; ) { lengthBytes++; temp >>= 8; }
/*      */ 
/*      */     
/* 1641 */     baos.write(0x80 | lengthBytes);
/*      */     
/* 1643 */     for (int i = lengthBytes - 1; i >= 0; i--) {
/* 1644 */       baos.write(datalength >> i * 8 & 0xFF);
/*      */     }
/* 1646 */     baos.write(data);
/* 1647 */     byte[] traceRet1 = baos.toByteArray();
/* 1648 */     if (Trace.isOn) {
/* 1649 */       Trace.exit("MessageProtectionBCImpl", "encodeAsDER(byte [ ])", traceRet1);
/*      */     }
/* 1651 */     return traceRet1;
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
/*      */   private static byte[] decodeFromDER(byte[] DERdata) throws IOException {
/* 1663 */     if (Trace.isOn) {
/* 1664 */       Trace.entry("MessageProtectionBCImpl", "decodeFromDER(byte [ ])", new Object[] { DERdata });
/*      */     }
/* 1666 */     ByteArrayInputStream bais = new ByteArrayInputStream(DERdata);
/* 1667 */     bais.read();
/* 1668 */     int x = bais.read();
/*      */     
/* 1670 */     int lengthBytes = x & 0xFFFFFF7F;
/* 1671 */     int arrayLength = 0;
/* 1672 */     for (int i = 0; i < lengthBytes; i++) {
/* 1673 */       arrayLength <<= 8;
/* 1674 */       arrayLength |= bais.read();
/*      */     } 
/* 1676 */     byte[] result = new byte[arrayLength];
/* 1677 */     bais.read(result);
/* 1678 */     if (Trace.isOn) {
/* 1679 */       Trace.exit("MessageProtectionBCImpl", "decodeFromDER(byte [ ])", result);
/*      */     }
/* 1681 */     return result;
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
/*      */   private void validateEncryptionStrength(String messageAlgorithmName, String policyAlgorithmName) throws MessageProtectionException {
/* 1696 */     if (Trace.isOn) {
/* 1697 */       Trace.entry(this, "MessageProtectionBCImpl", "validateEncryptionStrength(String,String)", new Object[] { messageAlgorithmName, policyAlgorithmName });
/*      */     }
/*      */ 
/*      */     
/* 1701 */     boolean valid = false;
/* 1702 */     boolean policy_valid = true;
/* 1703 */     int policyAlgorithm = 0;
/*      */     
/* 1705 */     if (policyAlgorithmName != null && policyAlgorithmName.length() > 0)
/*      */     {
/* 1707 */       switch (policyAlgorithmName) {
/*      */         case "2.16.840.1.101.3.4.1.42":
/*      */         case "AES256":
/*      */         case "AES-256/CBC":
/* 1711 */           policyAlgorithm = 5;
/*      */           break;
/*      */         
/*      */         case "2.16.840.1.101.3.4.1.2":
/*      */         case "AES128":
/*      */         case "AES-128/CBC":
/* 1717 */           policyAlgorithm = 4;
/*      */           break;
/*      */         
/*      */         case "1.2.840.113549.3.7":
/*      */         case "DESede":
/* 1722 */           policyAlgorithm = 3;
/*      */           break;
/*      */         
/*      */         case "1.3.14.3.2.7":
/*      */         case "DES":
/* 1727 */           policyAlgorithm = 2;
/*      */           break;
/*      */         
/*      */         case "1.2.840.113549.3.2":
/*      */         case "RC2":
/* 1732 */           policyAlgorithm = 1;
/*      */           break;
/*      */         
/*      */         default:
/* 1736 */           policy_valid = false;
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1745 */     if (policy_valid == true) {
/* 1746 */       switch (messageAlgorithmName) {
/*      */         
/*      */         case "2.16.840.1.101.3.4.1.42":
/*      */         case "AES256":
/*      */         case "AES-256/CBC":
/* 1751 */           if (policyAlgorithm >= 0 && policyAlgorithm <= 5) {
/* 1752 */             valid = true;
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case "2.16.840.1.101.3.4.1.2":
/*      */         case "AES128":
/*      */         case "AES-128/CBC":
/* 1760 */           if (policyAlgorithm >= 0 && policyAlgorithm <= 4) {
/* 1761 */             valid = true;
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case "1.2.840.113549.3.7":
/*      */         case "DESede":
/*      */         case "DESEDE-3KEY/CBC":
/* 1769 */           if (policyAlgorithm >= 0 && policyAlgorithm <= 3) {
/* 1770 */             valid = true;
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case "1.3.14.3.2.7":
/*      */         case "DES":
/* 1777 */           if (policyAlgorithm >= 0 && policyAlgorithm <= 2) {
/* 1778 */             valid = true;
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case "1.2.840.113549.3.2":
/*      */         case "RC2/CBC":
/*      */         case "RC2":
/* 1786 */           if (policyAlgorithm >= 0 && policyAlgorithm <= 1) {
/* 1787 */             valid = true;
/*      */           }
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1796 */     if (!valid) {
/* 1797 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1798 */       inserts.put("AMS_INSERT_EXPECTED_ENCRYPTION_STRENGTH", 
/* 1799 */           (policyAlgorithmName.length() == 0) ? "NONE" : policyAlgorithmName);
/* 1800 */       inserts.put("AMS_INSERT_ENCRYPTION_STRENGTH", messageAlgorithmName);
/* 1801 */       MessageProtectionException ex = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_encryption_strength_mismatch, inserts);
/*      */       
/* 1803 */       if (Trace.isOn) {
/* 1804 */         Trace.throwing(this, "MessageProtectionBCImpl", "validateEncryptionStrength(String,String)", (Throwable)ex);
/*      */       }
/* 1806 */       throw ex;
/*      */     } 
/*      */     
/* 1809 */     if (Trace.isOn) {
/* 1810 */       Trace.exit(this, "MessageProtectionBCImpl", "validateEncryptionStrength(String,String)");
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
/*      */   private void validateRecipientCertificate(X509Certificate recipientCert, EseUser user) throws InvalidCertificateException {
/* 1824 */     if (Trace.isOn) {
/* 1825 */       Trace.entry(this, "MessageProtectionBCImpl", "validateRecipientCertificate(X509Certificate,EseUser)", new Object[] { recipientCert, user });
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 1830 */       this.certificateValidator.validateX509Certificate(recipientCert, X509CertificateValidator.RECIPIENT_KEY_USAGE, X509CertificateValidator.RECIPIENT_KEY_USAGE_MATCH, user);
/*      */     
/*      */     }
/* 1833 */     catch (InvalidCertificateException e) {
/* 1834 */       if (Trace.isOn) {
/* 1835 */         Trace.catchBlock(this, "MessageProtectionBCImpl", "validateRecipientCertificate(X509Certificate,EseUser)", (Throwable)e, 1);
/*      */       }
/*      */       
/* 1838 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1839 */       inserts.put("AMS_INSERT_CERTIFICATE_SUBJECT", recipientCert.getSubjectX500Principal().getName());
/*      */       
/* 1841 */       InvalidCertificateException ex = new InvalidCertificateException(AmsErrorMessages.mjp_msg_error_msg_recipient_cert_not_valid, inserts, e.getCause());
/* 1842 */       if (Trace.isOn) {
/* 1843 */         Trace.throwing(this, "MessageProtectionBCImpl", "validateRecipientCertificate(X509Certificate,EseUser)", (Throwable)ex, 1);
/*      */       }
/*      */       
/* 1846 */       throw ex;
/*      */     }
/* 1848 */     catch (Exception e) {
/* 1849 */       if (Trace.isOn) {
/* 1850 */         Trace.catchBlock(this, "MessageProtectionBCImpl", "validateRecipientCertificate(X509Certificate,EseUser)", e, 2);
/*      */       }
/*      */       
/* 1853 */       if (Trace.isOn) {
/* 1854 */         Trace.traceInfo(this, "com.ibm.mq.ese.prot.MessageProtectionBCImpl", "validateRecipientCertificate(X509Certificate, EseUser)", "caught exception while validating sender's certificate", "");
/*      */       }
/*      */ 
/*      */       
/* 1858 */       HashMap<String, Object> inserts = new HashMap<>();
/* 1859 */       inserts.put("AMS_INSERT_CERTIFICATE_SUBJECT", recipientCert.getSubjectX500Principal().getName());
/* 1860 */       InvalidCertificateException ex = new InvalidCertificateException(AmsErrorMessages.mjp_msg_error_msg_recipient_cert_not_valid, inserts, e);
/*      */       
/* 1862 */       if (Trace.isOn) {
/* 1863 */         Trace.throwing(this, "MessageProtectionBCImpl", "validateRecipientCertificate(X509Certificate,EseUser)", (Throwable)ex, 2);
/*      */       }
/*      */       
/* 1866 */       throw ex;
/*      */     } 
/*      */     
/* 1869 */     if (Trace.isOn) {
/* 1870 */       Trace.exit(this, "MessageProtectionBCImpl", "validateRecipientCertificate(X509Certificate,EseUser)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private CMSSignedData AMSsignedToCMSSignedData(byte[] recipientData) throws IOException, CMSException {
/* 1880 */     if (Trace.isOn) {
/* 1881 */       Trace.entry(this, "MessageProtectionBCImpl", "AMSsignedToCMSSignedData(byte [ ])", new Object[] { recipientData });
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1886 */     byte[] protectedData = encodeAsDER(recipientData);
/* 1887 */     Object o = null;
/*      */     
/* 1889 */     try (ASN1InputStream ais = new ASN1InputStream(protectedData)) {
/*      */       
/* 1891 */       o = ais.readObject();
/*      */     } 
/*      */ 
/*      */     
/* 1895 */     SignedData sd = SignedData.getInstance(o);
/*      */ 
/*      */     
/* 1898 */     CMSSignedData csd = new CMSSignedData(new ContentInfo(CMSObjectIdentifiers.signedData, (ASN1Encodable)sd));
/* 1899 */     if (Trace.isOn) {
/* 1900 */       Trace.exit(this, "MessageProtectionBCImpl", "AMSsignedToCMSSignedData(byte [ ])", csd);
/*      */     }
/* 1902 */     return csd;
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
/*      */   private void validateSignatureAlg(String messageAlg, String policyAlg) throws MessageProtectionException {
/* 1917 */     if (Trace.isOn) {
/* 1918 */       Trace.entry(this, "MessageProtectionBCImpl", "validateSignatureAlg(String,String)", new Object[] { messageAlg, policyAlg });
/*      */     }
/*      */ 
/*      */     
/* 1922 */     boolean valid = false;
/* 1923 */     boolean policy_valid = true;
/* 1924 */     int polAlg = 0;
/*      */ 
/*      */     
/* 1927 */     switch (policyAlg) {
/*      */       case "1.2.840.113549.1.1.13":
/*      */       case "SHA512withRSA":
/*      */       case "SHA5withRSA":
/*      */       case "SHA5":
/*      */       case "SHA512":
/* 1933 */         polAlg = 6;
/*      */         break;
/*      */       
/*      */       case "1.2.840.113549.1.1.12":
/*      */       case "SHA384withRSA":
/*      */       case "SHA3withRSA":
/*      */       case "SHA3":
/*      */       case "SHA384":
/* 1941 */         polAlg = 5;
/*      */         break;
/*      */       
/*      */       case "1.2.840.113549.1.1.11":
/*      */       case "SHA256withRSA":
/*      */       case "SHA2withRSA":
/*      */       case "SHA2":
/*      */       case "SHA256":
/* 1949 */         polAlg = 4;
/*      */         break;
/*      */       
/*      */       case "1.2.840.113549.1.1.5":
/*      */       case "SHA1withRSA":
/*      */       case "SHA":
/*      */       case "SHA1":
/* 1956 */         polAlg = 2;
/*      */         break;
/*      */       
/*      */       case "1.2.840.113549.1.1.4":
/*      */       case "MD5withRSA":
/*      */       case "MD5":
/* 1962 */         polAlg = 1;
/*      */         break;
/*      */       
/*      */       case "DUMMY":
/* 1966 */         polAlg = 0;
/*      */         break;
/*      */       
/*      */       default:
/* 1970 */         policy_valid = false;
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1982 */     if (policy_valid == true) {
/* 1983 */       switch (messageAlg) {
/*      */         
/*      */         case "1.2.840.113549.1.1.13":
/*      */         case "SHA512withRSA":
/*      */         case "SHA5withRSA":
/*      */         case "SHA5":
/*      */         case "SHA512":
/* 1990 */           if (polAlg >= 0 && polAlg <= 6) {
/* 1991 */             valid = true;
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case "1.2.840.113549.1.1.12":
/*      */         case "SHA384withRSA":
/*      */         case "SHA3withRSA":
/*      */         case "SHA3":
/*      */         case "SHA384":
/* 2001 */           if (polAlg >= 0 && polAlg <= 5) {
/* 2002 */             valid = true;
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case "1.2.840.113549.1.1.11":
/*      */         case "SHA256withRSA":
/*      */         case "SHA2withRSA":
/*      */         case "SHA2":
/*      */         case "SHA256":
/* 2012 */           if (polAlg >= 0 && polAlg <= 4) {
/* 2013 */             valid = true;
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case "1.2.840.113549.1.1.5":
/*      */         case "SHA1withRSA":
/*      */         case "SHA":
/*      */         case "SHA1":
/* 2022 */           if (polAlg >= 0 && polAlg <= 2) {
/* 2023 */             valid = true;
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case "1.2.840.113549.1.1.4":
/*      */         case "MD5withRSA":
/*      */         case "MD5":
/* 2031 */           if (polAlg >= 0 && polAlg <= 1) {
/* 2032 */             valid = true;
/*      */           }
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 2044 */     if (!valid) {
/* 2045 */       if (Trace.isOn) {
/* 2046 */         Trace.traceInfo(this, "com.ibm.mq.ese.prot.MessageProtectionBCImpl", "validateSignatureAlg(String, String)", "Signature algorithm '" + messageAlg + "' does not match policy settings '" + policyAlg + "'", "");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2051 */       HashMap<String, Object> inserts = new HashMap<>();
/* 2052 */       inserts.put("AMS_INSERT_SIGNATURE_ALGORITHM", messageAlg);
/* 2053 */       MessageProtectionException ex = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_invalid_signature_algorithm, inserts);
/*      */       
/* 2055 */       if (Trace.isOn) {
/* 2056 */         Trace.throwing(this, "MessageProtectionBCImpl", "validateSignatureAlg(String,String)", (Throwable)ex);
/*      */       }
/* 2058 */       throw ex;
/*      */     } 
/*      */     
/* 2061 */     if (Trace.isOn)
/* 2062 */       Trace.exit(this, "MessageProtectionBCImpl", "validateSignatureAlg(String,String)"); 
/*      */   }
/*      */   
/*      */   private static class AlwaysSelector
/*      */     implements Selector<Object>
/*      */   {
/*      */     private AlwaysSelector() {}
/*      */     
/*      */     public Object clone() {
/* 2071 */       if (Trace.isOn) {
/* 2072 */         Trace.entry(this, "AlwaysSelector", "clone()");
/*      */       }
/* 2074 */       Object traceRet1 = new AlwaysSelector();
/* 2075 */       if (Trace.isOn) {
/* 2076 */         Trace.exit(this, "AlwaysSelector", "clone()", traceRet1);
/*      */       }
/* 2078 */       return traceRet1;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean match(Object obj) {
/* 2083 */       if (Trace.isOn) {
/* 2084 */         Trace.entry(this, "AlwaysSelector", "match(Object)", new Object[] { obj });
/*      */       }
/* 2086 */       if (Trace.isOn) {
/* 2087 */         Trace.exit(this, "AlwaysSelector", "match(Object)", Boolean.valueOf(true));
/*      */       }
/* 2089 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean initialise() {
/* 2099 */     return false;
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\prot\MessageProtectionBCImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */