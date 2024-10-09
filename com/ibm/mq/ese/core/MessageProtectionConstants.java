/*     */ package com.ibm.mq.ese.core;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface MessageProtectionConstants
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/MessageProtectionConstants.java";
/*     */   public static final short QOP_NONE = 0;
/*     */   public static final short QOP_INTEGRITY = 1;
/*     */   public static final short QOP_PRIVACY = 2;
/*     */   public static final short QOP_CONFIDENTIALITY = 3;
/*  56 */   public static final String[] QOP_NAMES = new String[] { "None", "Integrity", "Privacy", "Confidentiality" };
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ENCRYPTION_RC2 = "RC2";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ENCRYPTION_RC2_CBC = "RC2/CBC";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ENCRYPTION_RC2_CBC_OID = "1.2.840.113549.3.2";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ENCRYPTION_DES = "DES";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ENCRYPTION_DES_CBC_OID = "1.3.14.3.2.7";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ENCRYPTION_3DES = "DESede";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ENCRYPTION_3DES_ALT = "DESEDE-3KEY/CBC";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ENCRYPTION_3DES_CBC_OID = "1.2.840.113549.3.7";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ENCRYPTION_AES = "AES";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ENCRYPTION_AES128 = "AES128";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ENCRYPTION_AES_128_CBC = "AES-128/CBC";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ENCRYPTION_AES128_CBC_OID = "2.16.840.1.101.3.4.1.2";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ENCRYPTION_AES256 = "AES256";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ENCRYPTION_AES_256_CBC = "AES-256/CBC";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String ENCRYPTION_AES256_CBC_OID = "2.16.840.1.101.3.4.1.42";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int ENCRYPTION_RC2_KEYSIZE = 128;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int ENCRYPTION_DES_KEYSIZE = 64;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int ENCRYPTION_3DES_KEYSIZE = 192;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int ENCRYPTION_AES128_KEYSIZE = 128;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int ENCRYPTION_AES256_KEYSIZE = 256;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MD2_WITH_RSAENCRYPTION = "MD2withRSA";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MD2_WITH_RSAENCRYPTION_OID = "1.2.840.113549.1.1.2";
/*     */ 
/*     */   
/*     */   public static final String MD5_WITH_RSAENCRYPTION = "MD5withRSA";
/*     */ 
/*     */   
/*     */   public static final String MD5_WITH_RSAENCRYPTION_OID = "1.2.840.113549.1.1.4";
/*     */ 
/*     */   
/*     */   public static final String SHA1_WITH_RSAENCRYPTION = "SHA1withRSA";
/*     */ 
/*     */   
/*     */   public static final String SHA1_WITH_RSAENCRYPTION_OID = "1.2.840.113549.1.1.5";
/*     */ 
/*     */   
/*     */   public static final String SHA224_WITH_RSAENCRYPTION = "SHA224withRSA";
/*     */ 
/*     */   
/*     */   public static final String SHA224_WITH_RSAENCRYPTION_OID = "1.2.840.113549.1.1.14";
/*     */ 
/*     */   
/*     */   public static final String SHA2_WITH_RSAENCRYPTION = "SHA2withRSA";
/*     */ 
/*     */   
/*     */   public static final String SHA256_WITH_RSAENCRYPTION = "SHA256withRSA";
/*     */ 
/*     */   
/*     */   public static final String SHA256_WITH_RSAENCRYPTION_OID = "1.2.840.113549.1.1.11";
/*     */ 
/*     */   
/*     */   public static final String SHA3_WITH_RSAENCRYPTION = "SHA3withRSA";
/*     */ 
/*     */   
/*     */   public static final String SHA384_WITH_RSAENCRYPTION = "SHA384withRSA";
/*     */ 
/*     */   
/*     */   public static final String SHA384_WITH_RSAENCRYPTION_OID = "1.2.840.113549.1.1.12";
/*     */ 
/*     */   
/*     */   public static final String SHA5_WITH_RSAENCRYPTION = "SHA5withRSA";
/*     */ 
/*     */   
/*     */   public static final String SHA512_WITH_RSAENCRYPTION = "SHA512withRSA";
/*     */ 
/*     */   
/*     */   public static final String SHA512_WITH_RSAENCRYPTION_OID = "1.2.840.113549.1.1.13";
/*     */ 
/*     */   
/*     */   public static final String DUMMY_SIGNATURE_ALGORITHM = "DUMMY";
/*     */ 
/*     */   
/*     */   @Deprecated
/* 196 */   public static final String[] MAP_ACME_TO_JAVA_ENC_ALG = new String[] { "DESede", "DESede", "DES", "RC2", "DES", "DESede", "DESede", "AES128", "AES256" };
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
/*     */   @Deprecated
/* 214 */   public static final String[] MAP_ACME_TO_JAVA_SIG_ALG = new String[] { "SHA1withRSA", "MD2withRSA", "MD5withRSA", "SHA1withRSA" };
/*     */   @Deprecated
/*     */   public static final int MAP_ACME_BASE = 256;
/*     */   public static final String ENCRYPTION_RSA = "RSA";
/*     */   public static final int PKCS11_DES_KEY_LENGTH = 64;
/*     */   public static final int PKCS11_3DES_KEY_LENGTH = 192;
/*     */   public static final String SIGNED_DATA_OID = "1.2.840.113549.1.7.2";
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\core\MessageProtectionConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */