/*     */ package com.ibm.msg.client.commonservices.passwordprotection;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Arrays;
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
/*     */ public class CryptoUtil
/*     */ {
/*     */   static final String copyright_notice = "Licensed Materials - Property of IBM 5724-H72 (c) Copyright IBM Corp. 2020 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public static final int LATEST_ALGORITHM = 2;
/*     */   
/*     */   public static char[] decryptPassword(char[] initalkey, byte[] fixedSalt, EncodedPasswordAbstract encodedPassword) throws PBEException {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "decryptPassword(char[], byte[], EncodedPasswordAbstract)", new Object[] { "********", "********", 
/*     */             
/*  55 */             encodedPassword.isTraceable() ? encodedPassword : "********" });
/*     */     }
/*     */     
/*  58 */     char[] result = null;
/*     */     
/*  60 */     if (encodedPassword != null) {
/*  61 */       MQPasswordCipher cipher = MQPBEFactory.getFactoryInstance().getSharedCipher(encodedPassword.getAlgorithm(), initalkey, fixedSalt);
/*     */       
/*  63 */       result = cipher.decode(encodedPassword);
/*     */     } 
/*  65 */     if (Trace.isOn) {
/*  66 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "decryptPassword(char[], byte[], EncodedPasswordAbstract)", new Object[] { "********" });
/*     */     }
/*     */     
/*  69 */     return result;
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
/*     */   public static String decryptPasswordAsString(char[] initalkey, byte[] fixedSalt, EncodedPasswordAbstract encodedPassword) throws PBEException {
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "decryptPasswordAsString(char[], byte[], EncodedPasswordAbstract)", new Object[] { "********", "********", 
/*     */             
/*  91 */             encodedPassword.isTraceable() ? encodedPassword : "********" });
/*     */     }
/*     */     
/*  94 */     String result = null;
/*     */     
/*  96 */     char[] passwd = decryptPassword(initalkey, fixedSalt, encodedPassword);
/*     */     
/*  98 */     if (passwd != null) {
/*  99 */       result = new String(passwd);
/* 100 */       Arrays.fill(passwd, ' ');
/*     */     } 
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "decryptPasswordAsString(char[], byte[], EncodedPasswordAbstract)", new Object[] { "********" });
/*     */     }
/*     */     
/* 106 */     return result;
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
/*     */   public static boolean testDecryptPassword(char[] initalkey, byte[] fixedSalt, EncodedPasswordAbstract encodedPassword) {
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "testDecryptPassword(char[], byte[], EncodedPasswordAbstract)", new Object[] { "********", "********", 
/*     */             
/* 125 */             encodedPassword.isTraceable() ? encodedPassword : "********" });
/*     */     }
/*     */     
/* 128 */     boolean result = true;
/* 129 */     if (encodedPassword != null) {
/*     */       try {
/* 131 */         decryptPassword(initalkey, fixedSalt, encodedPassword);
/* 132 */       } catch (PBEException e) {
/* 133 */         if (Trace.isOn) {
/* 134 */           Trace.catchBlock("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "testDecryptPassword(char[], byte[], EncodedPasswordAbstract)", e);
/*     */         }
/*     */ 
/*     */         
/* 138 */         result = false;
/*     */       } 
/*     */     }
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "testDecryptPassword(char[], byte[], EncodedPasswordAbstract)", 
/* 143 */           Boolean.valueOf(result));
/*     */     }
/* 145 */     return result;
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
/*     */   public static EncodedPasswordAbstract encryptPassword(char[] password, char[] initialkey, byte[] fixedSalt, int algorithm, String eyeCatcher) throws PBEException {
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "encryptPassword(char[], char[], byte[], int, String)", new Object[] { "********", "********", "********", 
/*     */             
/* 169 */             Integer.valueOf(algorithm), eyeCatcher });
/*     */     }
/*     */     
/* 172 */     EncodedPasswordAbstract result = null;
/*     */     
/* 174 */     MQPasswordCipher cipher = MQPBEFactory.getFactoryInstance().getSharedCipher(algorithm, initialkey, fixedSalt);
/* 175 */     result = cipher.encode(algorithm, password, eyeCatcher);
/*     */     
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "encryptPassword(char[], char[], byte[], int, String)", 
/* 179 */           result.isTraceable() ? result : "********");
/*     */     }
/* 181 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] charArrayToByteArray(char[] chars) {
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "charArrayToByteArray(char[])", new Object[] { "********" });
/*     */     }
/*     */     
/* 196 */     ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(CharBuffer.wrap(chars));
/* 197 */     byte[] byteArray = Arrays.copyOfRange(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
/* 198 */     Arrays.fill(byteBuffer.array(), (byte)0);
/*     */     
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "charArrayToByteArray(char[])", new Object[] { "********" });
/*     */     }
/*     */     
/* 204 */     return byteArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static char[] byteArrayToCharArray(byte[] bytes) {
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "byteArrayToCharArray(byte[])", new Object[] { "********" });
/*     */     }
/*     */     
/* 219 */     CharBuffer charBuffer = Charset.forName("UTF-8").decode(ByteBuffer.wrap(bytes));
/* 220 */     char[] charArray = Arrays.copyOfRange(charBuffer.array(), charBuffer.position(), charBuffer.limit());
/* 221 */     Arrays.fill(charBuffer.array(), ' ');
/*     */     
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "byteArrayToCharArray(byte[])", new Object[] { "********" });
/*     */     }
/*     */     
/* 227 */     return charArray;
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
/*     */   public static EncodedPasswordAbstract getEncodedPasswordObjFromString(String encodedString, String eyeCatcher) throws PBEException {
/* 244 */     return getEncodedPasswordObjFromString(encodedString, eyeCatcher, true);
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
/*     */   public static EncodedPasswordAbstract getEncodedPasswordObjFromString(String encodedString, String eyeCatcher, boolean javaString) throws PBEException {
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "getEncodedPasswordObjFromString(String, String, boolean)", new Object[] { "********", eyeCatcher, 
/* 261 */             Boolean.valueOf(javaString) });
/*     */     }
/*     */     
/* 264 */     EncodedPasswordAbstract result = EncodedPasswordAbstract.createEncodedPasswordFromString(encodedString, eyeCatcher, javaString);
/*     */     
/* 266 */     if (Trace.isOn) {
/* 267 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "getEncodedPasswordObjFromString(String, String, boolean)", 
/* 268 */           result.isTraceable() ? result : "********");
/*     */     }
/* 270 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static char[] readInitialKey(File initialKeyFile) throws PBEException {
/*     */     char[] initialKey;
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "readInitialKey(File)", new Object[] { initialKeyFile });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 288 */     try (BufferedReader reader = new BufferedReader(new FileReader(initialKeyFile))) {
/* 289 */       String firstLine = reader.readLine();
/* 290 */       if (firstLine != null) {
/* 291 */         initialKey = firstLine.toCharArray();
/*     */       } else {
/* 293 */         PBEException thrown = new PBEException("Initial keyfile is empty", PBEException.PBERC.INITIALKEYFILEBADFORMAT);
/* 294 */         if (Trace.isOn) {
/* 295 */           Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "readInitialKey(File)", thrown);
/*     */         }
/*     */         
/* 298 */         throw thrown;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 303 */       if (reader.read() != -1) {
/* 304 */         PBEException thrown = new PBEException("Initial keyfile has too many lines", PBEException.PBERC.INITIALKEYFILEBADFORMAT);
/* 305 */         if (Trace.isOn) {
/* 306 */           Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "readInitialKey(File)", thrown);
/*     */         }
/*     */         
/* 309 */         throw thrown;
/*     */       } 
/* 311 */     } catch (IOException e) {
/* 312 */       PBEException thrown = new PBEException("Unable to read Initial Keyfile", PBEException.PBERC.CANNOTREADINITIALKEYFILE);
/* 313 */       if (Trace.isOn) {
/* 314 */         Trace.catchBlock("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "readInitialKey(File)", e);
/*     */       }
/* 316 */       if (Trace.isOn) {
/* 317 */         Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "readInitialKey(File)", thrown);
/*     */       }
/*     */       
/* 320 */       throw thrown;
/*     */     } 
/*     */     
/* 323 */     if (Trace.isOn) {
/* 324 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "readInitialKey(File)", new Object[] { "********" });
/*     */     }
/*     */ 
/*     */     
/* 328 */     return initialKey;
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
/*     */   public static char[] rebuildXORdKey(byte[] key, byte[] mask) {
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "rebuildDefaultKey(byte[], byte[])", new Object[] { "********", "********" });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 346 */     byte[] xoredKey = new byte[key.length];
/* 347 */     for (int i = 0; i < xoredKey.length; i++) {
/* 348 */       xoredKey[i] = (byte)(key[i] ^ mask[key.length - i + 1]);
/*     */     }
/*     */     
/* 351 */     char[] result = byteArrayToCharArray(xoredKey);
/* 352 */     Arrays.fill(xoredKey, (byte)0);
/*     */ 
/*     */     
/* 355 */     if (Trace.isOn) {
/* 356 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.CryptoUtil", "rebuildDefaultKey(byte[], byte[])", new Object[] { "********" });
/*     */     }
/*     */ 
/*     */     
/* 360 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\passwordprotection\CryptoUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */