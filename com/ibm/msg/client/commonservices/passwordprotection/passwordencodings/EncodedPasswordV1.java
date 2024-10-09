/*     */ package com.ibm.msg.client.commonservices.passwordprotection.passwordencodings;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EncodedPasswordV1
/*     */   extends EncodedPasswordAbstract
/*     */ {
/*     */   static final String copyright_notice = "Licensed Materials - Property of IBM 5724-H72 (c) Copyright IBM Corp. 2020 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
/*  46 */   private byte[] iv = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   private byte[] password = null;
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
/*     */   public EncodedPasswordV1(int algorithm, byte[] iv, byte[] password, String eyeCatcher) {
/*  66 */     setAlgorithm(algorithm);
/*  67 */     this.iv = iv;
/*  68 */     this.password = password;
/*  69 */     setEyeCatcher(eyeCatcher);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getPassword() {
/*  76 */     return this.password;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getIV() {
/*  83 */     return this.iv;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/*  93 */     if (o instanceof EncodedPasswordV1) {
/*  94 */       EncodedPasswordV1 e = (EncodedPasswordV1)o;
/*  95 */       return (getAlgorithm() == e.getAlgorithm() && Arrays.equals(this.iv, e.getIV()) && 
/*  96 */         Arrays.equals(this.password, e.getPassword()));
/*     */     } 
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toPrintableString() {
/* 109 */     StringBuffer buffer = new StringBuffer(getEyeCatcher());
/*     */     
/* 111 */     buffer.append(getAlgorithm());
/* 112 */     buffer.append('!');
/*     */     
/* 114 */     if (this.iv != null) {
/* 115 */       buffer.append(base64encoder.encodeToString(this.iv));
/*     */     }
/*     */     
/* 118 */     buffer.append('!');
/* 119 */     buffer.append(base64encoder.encodeToString(this.password));
/*     */     
/* 121 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\passwordprotection\passwordencodings\EncodedPasswordV1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */