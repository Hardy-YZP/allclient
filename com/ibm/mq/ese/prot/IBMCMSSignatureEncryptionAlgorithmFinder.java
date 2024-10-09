/*    */ package com.ibm.mq.ese.prot;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import org.bouncycastle.asn1.ASN1Encodable;
/*    */ import org.bouncycastle.asn1.ASN1ObjectIdentifier;
/*    */ import org.bouncycastle.asn1.DERNull;
/*    */ import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
/*    */ import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/*    */ import org.bouncycastle.cms.DefaultCMSSignatureEncryptionAlgorithmFinder;
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
/*    */ public class IBMCMSSignatureEncryptionAlgorithmFinder
/*    */   extends DefaultCMSSignatureEncryptionAlgorithmFinder
/*    */ {
/* 40 */   protected static Set<ASN1ObjectIdentifier> oldRSAIds = new HashSet<>();
/*    */   static {
/* 42 */     oldRSAIds.add(PKCSObjectIdentifiers.sha224WithRSAEncryption);
/* 43 */     oldRSAIds.add(PKCSObjectIdentifiers.sha256WithRSAEncryption);
/* 44 */     oldRSAIds.add(PKCSObjectIdentifiers.sha384WithRSAEncryption);
/* 45 */     oldRSAIds.add(PKCSObjectIdentifiers.sha512WithRSAEncryption);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AlgorithmIdentifier findEncryptionAlgorithm(AlgorithmIdentifier signatureAlgorithm) {
/* 54 */     if (oldRSAIds.contains(signatureAlgorithm.getAlgorithm())) {
/* 55 */       return new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, (ASN1Encodable)DERNull.INSTANCE);
/*    */     }
/*    */     
/* 58 */     return super.findEncryptionAlgorithm(signatureAlgorithm);
/*    */   }
/*    */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\prot\IBMCMSSignatureEncryptionAlgorithmFinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */