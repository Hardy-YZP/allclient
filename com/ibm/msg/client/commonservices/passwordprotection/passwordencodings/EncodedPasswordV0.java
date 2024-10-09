/*     */ package com.ibm.msg.client.commonservices.passwordprotection.passwordencodings;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract;
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
/*     */ public class EncodedPasswordV0
/*     */   extends EncodedPasswordAbstract
/*     */ {
/*     */   static final String copyright_notice = "Licensed Materials - Property of IBM 5724-H72 (c) Copyright IBM Corp. 2020 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
/*  42 */   private String encodedPassword = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EncodedPasswordV0(String password, String eyeCatcher) {
/*  53 */     setAlgorithm(0);
/*  54 */     this.encodedPassword = password;
/*  55 */     setEyeCatcher(eyeCatcher);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPassword() {
/*  62 */     return this.encodedPassword;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/*  72 */     if (o instanceof EncodedPasswordV0) {
/*  73 */       EncodedPasswordV0 e = (EncodedPasswordV0)o;
/*  74 */       return (getAlgorithm() == e.getAlgorithm() && this.encodedPassword.equals(e.getPassword()));
/*     */     } 
/*  76 */     return false;
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
/*  87 */     StringBuffer buffer = new StringBuffer(getEyeCatcher());
/*     */     
/*  89 */     buffer.append(getAlgorithm());
/*  90 */     buffer.append('!');
/*     */     
/*  92 */     buffer.append(this.encodedPassword);
/*     */     
/*  94 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTraceable() {
/* 102 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\passwordprotection\passwordencodings\EncodedPasswordV0.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */