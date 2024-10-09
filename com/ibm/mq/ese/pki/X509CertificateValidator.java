/*    */ package com.ibm.mq.ese.pki;
/*    */ 
/*    */ import com.ibm.mq.ese.core.EseUser;
/*    */ import java.security.cert.X509Certificate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface X509CertificateValidator
/*    */ {
/*    */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/pki/X509CertificateValidator.java";
/* 36 */   public static final String[] KEY_USAGE_NAMES = new String[] { "digitalSignature", "nonRepudiation", "keyEncipherment", "dataEncipherment", "keyAgreement", "keyCertSign", "cRLSign", "encipherOnly", "decipherOnly" };
/*    */   
/*    */   public static final int DIGITAL_SIGNATURE_INDEX = 0;
/*    */   
/*    */   public static final int NON_REPUDIATION_INDEX = 1;
/*    */   
/*    */   public static final int KEY_ENCIPHERMENT_INDEX = 2;
/*    */   
/*    */   public static final int DATA_ENCIPHERMENT_INDEX = 3;
/*    */   public static final int KEY_AGREEMENT_INDEX = 4;
/*    */   public static final int KEY_CERT_SIGN_INDEX = 5;
/*    */   public static final int CRL_SIGN_INDEX = 6;
/*    */   public static final int ENCIPHER_ONLY_INDEX = 7;
/*    */   public static final int DECIPHER_ONLY_INDEX = 8;
/* 50 */   public static final boolean[] SENDER_KEY_USAGE = new boolean[] { true, true, false, false, false, false, false, false };
/*    */   
/* 52 */   public static final int[] SENDER_KEY_USAGE_MATCH = new int[] { 0, 1 };
/*    */ 
/*    */   
/* 55 */   public static final boolean[] RECIPIENT_KEY_USAGE = new boolean[] { false, false, true, false, false, false, false, false };
/*    */   
/* 57 */   public static final int[] RECIPIENT_KEY_USAGE_MATCH = new int[] { 2 };
/*    */   
/*    */   void validateX509Certificate(X509Certificate paramX509Certificate, boolean[] paramArrayOfboolean, int[] paramArrayOfint, EseUser paramEseUser) throws InvalidCertificateException;
/*    */   
/*    */   void validateX509Certificate(X509Certificate paramX509Certificate, boolean[] paramArrayOfboolean, int[] paramArrayOfint, boolean paramBoolean, EseUser paramEseUser) throws InvalidCertificateException;
/*    */   
/*    */   void validateX509Certificate(X509Certificate paramX509Certificate, boolean[] paramArrayOfboolean, int[] paramArrayOfint, boolean paramBoolean1, boolean paramBoolean2, EseUser paramEseUser) throws InvalidCertificateException;
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\pki\X509CertificateValidator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */