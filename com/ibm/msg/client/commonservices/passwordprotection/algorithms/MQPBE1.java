/*     */ package com.ibm.msg.client.commonservices.passwordprotection.algorithms;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil;
/*     */ import com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract;
/*     */ import com.ibm.msg.client.commonservices.passwordprotection.MQAbstractPBE;
/*     */ import com.ibm.msg.client.commonservices.passwordprotection.PBEException;
/*     */ import com.ibm.msg.client.commonservices.passwordprotection.passwordencodings.EncodedPasswordV1;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.security.GeneralSecurityException;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.spec.KeySpec;
/*     */ import java.util.Arrays;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.spec.IvParameterSpec;
/*     */ import javax.crypto.spec.PBEKeySpec;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ import javax.security.auth.DestroyFailedException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQPBE1
/*     */   extends MQAbstractPBE
/*     */ {
/*     */   static final String copyright_notice = "Licensed Materials - Property of IBM 5724-H72 (c) Copyright IBM Corp. 2020,2022 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   private static final int PBK_ITERATIONS = 84756;
/*     */   private static final int PBK_KEY_LENGTH = 128;
/*     */   private static final String ALGORITHM_PBK = "PBKDF2WithHmacSHA1";
/*     */   private static final String TRANSFORM_PBK = "AES/CBC/PKCS5Padding";
/*  67 */   private MessageDigest md5 = null;
/*     */   
/*     */   public static final int MD5_HASH_LEN = 16;
/*     */   
/*     */   private boolean needsReInit = true;
/*     */   
/*     */   private int checkSum;
/*     */   
/*  75 */   private int initialisedOpmode = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SecretKeySpec encryptionKey;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQPBE1(char[] initialKey, byte[] fixedSalt) throws PBEException {
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "MQPBE1(char[], byte[])", new Object[] { "********", "********" });
/*     */     }
/*     */     
/*  94 */     initAlgorithm("PBKDF2WithHmacSHA1", "AES/CBC/PKCS5Padding");
/*     */     
/*  96 */     if (initialKey == null) {
/*  97 */       PBEException thrown = new PBEException("Initial Key Cannot be null", PBEException.PBERC.INVALIDINITIALKEY);
/*  98 */       if (Trace.isOn) {
/*  99 */         Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "MQPBE1(char[], byte[])", (Throwable)thrown);
/*     */       }
/*     */       
/* 102 */       throw thrown;
/*     */     } 
/* 104 */     if (fixedSalt == null) {
/* 105 */       PBEException thrown = new PBEException("Fixed Salt Cannot be null", PBEException.PBERC.INVALIDFIXEDSALT);
/* 106 */       if (Trace.isOn) {
/* 107 */         Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "MQPBE1(char[], byte[])", (Throwable)thrown);
/*     */       }
/*     */       
/* 110 */       throw thrown;
/*     */     } 
/* 112 */     initKey(initialKey, fixedSalt);
/*     */     
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "MQPBE1(char[], byte[])");
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
/*     */   private void initKey(char[] initialKey, byte[] fixedSalt) throws PBEException {
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "initKey(char[], byte[])", new Object[] { "********", "********" });
/*     */     }
/*     */     
/* 135 */     KeySpec spec = new PBEKeySpec(initialKey, fixedSalt, 84756, 128);
/*     */     try {
/* 137 */       SecretKey pbkKey = this.keyFactory.generateSecret(spec);
/* 138 */       this.encryptionKey = new SecretKeySpec(pbkKey.getEncoded(), "AES");
/* 139 */       this.needsReInit = true;
/* 140 */       this.checkSum = getCheckSum(initialKey, fixedSalt);
/* 141 */     } catch (Exception e) {
/* 142 */       PBEException thrown = new PBEException(e.getMessage(), PBEException.PBERC.INITKEYFAILURE);
/* 143 */       if (Trace.isOn) {
/* 144 */         Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "initKey(char[], byte[])", (Throwable)thrown);
/*     */       }
/*     */     } 
/*     */     
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "initKey(char[], byte[])");
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
/*     */   private void initPBE1Cipher(int opmode, byte[] iv) throws PBEException {
/* 165 */     if (Trace.isOn)
/* 166 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "initPBE1Cipher(int, byte[])", new Object[] {
/* 167 */             Integer.valueOf(opmode), iv
/*     */           }); 
/* 169 */     if (this.needsReInit || opmode != this.initialisedOpmode || iv == null || (iv != null && !Arrays.equals(iv, this.cipher.getIV()))) {
/* 170 */       IvParameterSpec parms = (iv == null) ? null : new IvParameterSpec(iv);
/*     */       try {
/* 172 */         this.cipher.init(opmode, this.encryptionKey, parms);
/* 173 */         this.needsReInit = false;
/* 174 */       } catch (Exception e) {
/* 175 */         PBEException thrown = new PBEException(e.getMessage(), PBEException.PBERC.INITCIPHERFAILURE);
/* 176 */         if (Trace.isOn) {
/* 177 */           Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "initPBE1Cipher(int, byte[])", (Throwable)thrown);
/*     */         }
/*     */         
/* 180 */         throw thrown;
/*     */       } 
/* 182 */       this.initialisedOpmode = opmode;
/*     */     }
/* 184 */     else if (Trace.isOn) {
/* 185 */       Trace.data("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "initPBE1Cipher(int, byte[])", "Did nothing");
/*     */     } 
/*     */ 
/*     */     
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "initPBE1Cipher(int, byte[])");
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
/*     */   public synchronized EncodedPasswordV1 encode(int algorithm, char[] cleartext, byte[] iv, String eyeCatcher) throws PBEException {
/*     */     EncodedPasswordV1 encodedPassword;
/* 212 */     byte[] digest = null;
/* 213 */     byte[] passwordByteArray = null;
/*     */     
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "encode(int, char[], byte[], String)", new Object[] {
/* 217 */             Integer.valueOf(algorithm), "********", iv, eyeCatcher
/*     */           });
/*     */     }
/* 220 */     initPBE1Cipher(1, iv);
/*     */ 
/*     */     
/* 223 */     passwordByteArray = CryptoUtil.charArrayToByteArray(cleartext);
/*     */     
/* 225 */     if (algorithm == 2) {
/*     */       
/*     */       try {
/* 228 */         digest = getDigest(passwordByteArray);
/* 229 */         byte[] tempcpy = new byte[passwordByteArray.length + digest.length];
/* 230 */         System.arraycopy(digest, 0, tempcpy, 0, digest.length);
/* 231 */         System.arraycopy(passwordByteArray, 0, tempcpy, digest.length, passwordByteArray.length);
/* 232 */         passwordByteArray = tempcpy;
/* 233 */       } catch (NoSuchAlgorithmException e) {
/* 234 */         PBEException thrown = new PBEException("Unable to get algorithm for digest creation", PBEException.PBERC.ENCRYPTFAILURE);
/* 235 */         if (Trace.isOn) {
/* 236 */           Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "encode(int, char[], byte[], String)", (Throwable)thrown);
/*     */         }
/*     */         
/* 239 */         throw thrown;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 248 */       encodedPassword = new EncodedPasswordV1(algorithm, this.cipher.getIV(), this.cipher.doFinal(passwordByteArray), eyeCatcher);
/*     */     }
/* 250 */     catch (Exception e) {
/* 251 */       PBEException thrown = new PBEException(e.getMessage(), PBEException.PBERC.ENCRYPTFAILURE);
/* 252 */       if (Trace.isOn) {
/* 253 */         Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "encode(int, char[], byte[], String)", (Throwable)thrown);
/*     */       }
/*     */       
/* 256 */       throw thrown;
/*     */     } 
/* 258 */     Arrays.fill(passwordByteArray, (byte)0);
/*     */     
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "encode(int, char[], byte[], String)", 
/*     */           
/* 263 */           encodedPassword.isTraceable() ? encodedPassword : "********");
/*     */     }
/* 265 */     return encodedPassword;
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
/*     */   public synchronized char[] decode(EncodedPasswordAbstract encodedPassword) throws PBEException {
/* 279 */     byte[] passwordByteArray = null;
/* 280 */     if (Trace.isOn) {
/*     */       
/* 282 */       (new Object[2])[0] = 
/* 283 */         Integer.valueOf(encodedPassword.getAlgorithm()); (new Object[2])[1] = encodedPassword; (new Object[2])[0] = Integer.valueOf(encodedPassword.getAlgorithm()); (new Object[2])[1] = "********"; Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "decode(int, EncodedPasswordAbstract)", encodedPassword.isTraceable() ? new Object[2] : new Object[2]);
/*     */     } 
/*     */     
/* 286 */     if (!(encodedPassword instanceof EncodedPasswordV1)) {
/* 287 */       PBEException thrown = new PBEException("Unrecognized password version", PBEException.PBERC.UNKNOWNPASSWORDENCODING);
/*     */       
/* 289 */       if (Trace.isOn) {
/* 290 */         Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "decode(int, EncodedPasswordAbstract)", (Throwable)thrown);
/*     */       }
/*     */       
/* 293 */       throw thrown;
/*     */     } 
/* 295 */     EncodedPasswordV1 encodedPasswordv1 = (EncodedPasswordV1)encodedPassword;
/*     */ 
/*     */     
/* 298 */     initPBE1Cipher(2, encodedPasswordv1.getIV());
/*     */ 
/*     */     
/*     */     try {
/* 302 */       passwordByteArray = this.cipher.doFinal(encodedPasswordv1.getPassword());
/* 303 */       if (encodedPassword.getAlgorithm() == 2)
/*     */       {
/* 305 */         if (passwordByteArray.length < 16) {
/* 306 */           throw new GeneralSecurityException("Cannot decrypt credential - ciphertext too short for protection mode.");
/*     */         }
/*     */ 
/*     */         
/* 310 */         byte[] origPassword = Arrays.copyOfRange(passwordByteArray, 16, passwordByteArray.length);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 315 */         if (!Arrays.equals(getDigest(origPassword), Arrays.copyOfRange(passwordByteArray, 0, 16))) {
/* 316 */           throw new GeneralSecurityException("Cannot decrypt credential - digest mismatch.");
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 321 */         passwordByteArray = origPassword;
/*     */       }
/*     */     
/* 324 */     } catch (Exception e) {
/* 325 */       PBEException thrown = new PBEException(e.getMessage(), PBEException.PBERC.DECRYPTFAILURE);
/* 326 */       if (Trace.isOn) {
/* 327 */         Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "decode(int, EncodedPasswordAbstract)", (Throwable)thrown);
/*     */       }
/*     */       
/* 330 */       throw thrown;
/*     */     } 
/*     */ 
/*     */     
/* 334 */     char[] userPassword = CryptoUtil.byteArrayToCharArray(passwordByteArray);
/* 335 */     Arrays.fill(passwordByteArray, (byte)0);
/*     */     
/* 337 */     if (Trace.isOn) {
/* 338 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "decode(int, EncodedPasswordAbstract)", new Object[] { "********" });
/*     */     }
/*     */     
/* 341 */     return userPassword;
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
/*     */   public boolean verify(char[] password, EncodedPasswordAbstract encodedPassword, String eyeCatcher) {
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "verify(char[], EncodedPasswordAbstract, String)", new Object[] { "********", 
/*     */             
/* 361 */             encodedPassword.isTraceable() ? encodedPassword : "********", eyeCatcher });
/*     */     }
/* 363 */     boolean match = false;
/*     */     
/* 365 */     if (encodedPassword instanceof EncodedPasswordV1) {
/* 366 */       EncodedPasswordV1 encodedPasswordv1 = (EncodedPasswordV1)encodedPassword;
/*     */       
/*     */       try {
/* 369 */         EncodedPasswordV1 encodedPasswordV1 = encode(encodedPassword.getAlgorithm(), password, encodedPasswordv1.getIV(), eyeCatcher);
/*     */         
/* 371 */         match = encodedPasswordV1.equals(encodedPassword);
/* 372 */       } catch (PBEException e) {
/* 373 */         Trace.data("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "verify(char[], EncodedPasswordAbstract, String)", "Failed to encode password so cannot match: " + e
/*     */             
/* 375 */             .getMessage());
/* 376 */         match = false;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 381 */       if (Trace.isOn) {
/* 382 */         Trace.data("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "verify(char[], EncodedPasswordAbstract, String)", "Given EncodedPasswordAbstract is not of type EncodedPasswordV1 so cannot match");
/*     */       }
/*     */ 
/*     */       
/* 386 */       match = false;
/*     */     } 
/* 388 */     if (Trace.isOn) {
/* 389 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "verify(char[], EncodedPasswordAbstract, String)", 
/* 390 */           Boolean.valueOf(match));
/*     */     }
/* 392 */     return match;
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
/*     */   byte[] getDigest(byte[] pw) throws NoSuchAlgorithmException {
/* 406 */     if (this.md5 == null) {
/* 407 */       this.md5 = MessageDigest.getInstance("MD5");
/*     */     }
/* 409 */     this.md5.update(pw);
/* 410 */     return this.md5.digest();
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
/*     */   int getCheckSum(char[] initialKey, byte[] fixedSalt) {
/* 423 */     int checksum = 0;
/* 424 */     for (char c : initialKey) {
/* 425 */       checksum += c;
/*     */     }
/* 427 */     for (byte b : fixedSalt) {
/* 428 */       checksum += b;
/*     */     }
/* 430 */     return checksum;
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
/*     */   public synchronized void reInitializeIfNecessary(char[] initialKey, byte[] fixedSalt) throws PBEException {
/* 444 */     if (Trace.isOn) {
/* 445 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "ReInitializeIfNecessary(char[], byte[])", new Object[] { "********", "********" });
/*     */     }
/*     */     
/* 448 */     int newCheckSum = getCheckSum(initialKey, fixedSalt);
/*     */     
/* 450 */     if (newCheckSum != this.checkSum) {
/*     */       
/* 452 */       if (Trace.isOn) {
/* 453 */         Trace.data("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "ReInitializeIfNecessary(char[], byte[])", "Checksums did not match. Re-initializing keys.");
/*     */       }
/*     */ 
/*     */       
/*     */       try {
/* 458 */         this.encryptionKey.destroy();
/* 459 */       } catch (DestroyFailedException e) {
/* 460 */         Trace.catchBlock("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "ReInitializeIfNecessary(char[], byte[])", e);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 470 */       initKey(initialKey, fixedSalt);
/*     */     }
/* 472 */     else if (Trace.isOn) {
/* 473 */       Trace.data("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "ReInitializeIfNecessary(char[], byte[])", "Checksums matched so no need to re-initialize");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 478 */     if (Trace.isOn)
/* 479 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.algorithms.MQPBE1", "ReInitializeIfNecessary(char[], byte[])"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\passwordprotection\algorithms\MQPBE1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */