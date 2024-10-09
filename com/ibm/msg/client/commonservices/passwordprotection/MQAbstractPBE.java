/*     */ package com.ibm.msg.client.commonservices.passwordprotection;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.SecretKeyFactory;
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
/*     */ public abstract class MQAbstractPBE
/*     */   implements MQPasswordCipher
/*     */ {
/*     */   static final String copyright_notice = "Licensed Materials - Property of IBM 5724-H72 (c) Copyright IBM Corp. 2020 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
/*  43 */   protected SecretKeyFactory keyFactory = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   protected Cipher cipher = null;
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
/*     */   protected void initAlgorithm(String algorithm, String transformation) throws PBEException {
/*  60 */     if (Trace.isOn) {
/*  61 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.MQAbstractPBE", "initAlgorithm(String, String)", new Object[] { algorithm, transformation });
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  66 */       if (this.keyFactory == null) {
/*  67 */         this.keyFactory = SecretKeyFactory.getInstance(algorithm);
/*     */       }
/*     */ 
/*     */       
/*  71 */       if (this.cipher == null) {
/*  72 */         this.cipher = Cipher.getInstance(transformation);
/*     */       }
/*  74 */     } catch (Exception e) {
/*  75 */       if (Trace.isOn) {
/*  76 */         Trace.catchBlock("com.ibm.msg.client.commonservices.passwordprotection.MQAbstractPBE", "initAlgorithm(String, String)", e);
/*     */       }
/*     */       
/*  79 */       PBEException thrown = new PBEException("Failed to initialize alogrithm", PBEException.PBERC.INITALGORITHMFAILURE);
/*  80 */       if (Trace.isOn) {
/*  81 */         Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.MQAbstractPBE", "initAlgorithm(String, String)", thrown);
/*     */       }
/*     */       
/*  84 */       throw thrown;
/*     */     } 
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.MQAbstractPBE", "initAlgorithm(String, String)");
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
/*     */   public EncodedPasswordAbstract encode(int algorithm, char[] cleartext, String eyeCatcher) throws PBEException {
/* 102 */     return encode(algorithm, cleartext, null, eyeCatcher);
/*     */   }
/*     */   
/*     */   protected abstract EncodedPasswordAbstract encode(int paramInt, char[] paramArrayOfchar, byte[] paramArrayOfbyte, String paramString) throws PBEException;
/*     */   
/*     */   public abstract char[] decode(EncodedPasswordAbstract paramEncodedPasswordAbstract) throws PBEException;
/*     */   
/*     */   public abstract boolean verify(char[] paramArrayOfchar, EncodedPasswordAbstract paramEncodedPasswordAbstract, String paramString) throws PBEException;
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\passwordprotection\MQAbstractPBE.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */