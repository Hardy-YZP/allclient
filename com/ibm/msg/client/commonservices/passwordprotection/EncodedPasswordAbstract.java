/*     */ package com.ibm.msg.client.commonservices.passwordprotection;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.passwordprotection.passwordencodings.EncodedPasswordV0;
/*     */ import com.ibm.msg.client.commonservices.passwordprotection.passwordencodings.EncodedPasswordV1;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Arrays;
/*     */ import java.util.Base64;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EncodedPasswordAbstract
/*     */ {
/*     */   static final String copyright_notice = "Licensed Materials - Property of IBM 5724-H72 (c) Copyright IBM Corp. 2020 All Rights Reserved. US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.";
/*     */   public static final char SEPARATOR = '!';
/*     */   private String eyeCatcher;
/*  40 */   protected static Base64.Encoder base64encoder = Base64.getEncoder();
/*  41 */   protected static Base64.Decoder base64decoder = Base64.getDecoder();
/*     */ 
/*     */   
/*     */   private int algorithm;
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAlgorithm() {
/*  49 */     return this.algorithm;
/*     */   }
/*     */   
/*     */   protected void setAlgorithm(int alg) {
/*  53 */     this.algorithm = alg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String toPrintableString();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static EncodedPasswordAbstract createEncodedPasswordFromString(String encodedString, String eyeCatcher) throws PBEException {
/*  72 */     return createEncodedPasswordFromString(encodedString, eyeCatcher, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static EncodedPasswordAbstract createEncodedPasswordFromString(String encodedString, String eyeCatcher, boolean javaString) throws PBEException {
/*     */     EncodedPasswordV0 encodedPasswordV0;
/*     */     EncodedPasswordV1 encodedPasswordV1;
/*     */     int algorithm;
/*     */     StringBuffer sb;
/*     */     int i;
/*     */     byte[] iv, password;
/*     */     PBEException thrown;
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry("com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract", "createEncodedPasswordFromString(String, String, boolean)", new Object[] { "********", eyeCatcher, 
/*  89 */             Boolean.valueOf(javaString) });
/*     */     }
/*     */ 
/*     */     
/*  93 */     if (eyeCatcher != null && !encodedString.startsWith(eyeCatcher)) {
/*     */       
/*  95 */       PBEException pBEException = new PBEException("Encoded String is missing eyecatcher", PBEException.PBERC.MISSINGEYECATCHER);
/*  96 */       if (Trace.isOn) {
/*  97 */         Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract", "createEncodedPasswordFromString(String, String, boolean)", pBEException);
/*     */       }
/*     */       
/* 100 */       throw pBEException;
/*     */     } 
/*     */ 
/*     */     
/* 104 */     String[] components = encodedString.substring(eyeCatcher.length()).split(String.valueOf('!'));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     if (!javaString) {
/* 110 */       if (components.length == 1) {
/*     */ 
/*     */         
/* 113 */         PBEException pBEException = new PBEException("Encoded String does not start with number", PBEException.PBERC.ENCODEDSTRINGINCORRECTFORMAT);
/*     */ 
/*     */         
/* 116 */         if (Trace.isOn) {
/* 117 */           Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract", "createEncodedPasswordFromString(String, String, boolean)", pBEException);
/*     */         }
/*     */         
/* 120 */         throw pBEException;
/*     */       } 
/*     */ 
/*     */       
/* 124 */       components = Arrays.<String>copyOfRange(components, 1, components.length);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 131 */       algorithm = Integer.parseInt(components[0]);
/* 132 */       if (Trace.isOn) {
/* 133 */         Trace.data("com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract", "createEncodedPasswordFromString(String, String, boolean)", "Algorithm detected as: " + algorithm);
/*     */       }
/*     */     }
/* 136 */     catch (NumberFormatException e) {
/* 137 */       if (Trace.isOn) {
/* 138 */         Trace.catchBlock("com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract", "createEncodedPasswordFromString(String, String, boolean)", e);
/*     */       }
/*     */       
/* 141 */       PBEException pBEException = new PBEException("Encoded String does not start with number", PBEException.PBERC.ENCODEDSTRINGINCORRECTFORMAT);
/*     */ 
/*     */       
/* 144 */       if (Trace.isOn) {
/* 145 */         Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract", "createEncodedPasswordFromString(String, String, boolean)", pBEException);
/*     */       }
/*     */       
/* 148 */       throw pBEException;
/*     */     } 
/*     */     
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.data("com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract", "createEncodedPasswordFromString(String, String, boolean)", "Components length: " + components.length);
/*     */     }
/*     */ 
/*     */     
/* 156 */     switch (algorithm) {
/*     */ 
/*     */ 
/*     */       
/*     */       case 0:
/* 161 */         if (components.length == 1) {
/*     */           
/* 163 */           PBEException pBEException = new PBEException("V0 password is not correctly formatted", PBEException.PBERC.ENCODEDSTRINGINCORRECTFORMAT);
/*     */           
/* 165 */           if (Trace.isOn) {
/* 166 */             Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract", "createEncodedPasswordFromString(String, String, boolean)", pBEException);
/*     */           }
/*     */           
/* 169 */           throw pBEException;
/*     */         } 
/* 171 */         sb = new StringBuffer();
/* 172 */         for (i = 1; i < components.length; i++) {
/* 173 */           if (i != 1)
/*     */           {
/* 175 */             sb.append("!");
/*     */           }
/* 177 */           sb.append(components[i]);
/*     */         } 
/* 179 */         encodedPasswordV0 = new EncodedPasswordV0(sb.toString(), eyeCatcher);
/*     */         break;
/*     */       
/*     */       case 1:
/*     */       case 2:
/* 184 */         if (components.length != 3) {
/*     */           
/* 186 */           PBEException pBEException = new PBEException("V1 password is not correctly formatted", PBEException.PBERC.ENCODEDSTRINGINCORRECTFORMAT);
/*     */           
/* 188 */           if (Trace.isOn) {
/* 189 */             Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract", "createEncodedPasswordFromString(String, String, boolean)", pBEException);
/*     */           }
/*     */           
/* 192 */           throw pBEException;
/*     */         } 
/*     */         
/* 195 */         iv = null;
/*     */         
/*     */         try {
/* 198 */           if (javaString) {
/*     */             
/* 200 */             if (components[1].length() > 0) {
/* 201 */               if (Trace.isOn) {
/* 202 */                 Trace.data("com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract", "createEncodedPasswordFromString(String, String, boolean)", "Decoding Java String IV");
/*     */               }
/*     */               
/* 205 */               iv = base64decoder.decode(components[1]);
/*     */             } 
/*     */ 
/*     */             
/* 209 */             password = base64decoder.decode(components[2]);
/*     */           } else {
/* 211 */             password = base64decoder.decode(components[1]);
/* 212 */             if (components[2].length() > 0) {
/* 213 */               if (Trace.isOn) {
/* 214 */                 Trace.data("com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract", "createEncodedPasswordFromString(String, String, boolean)", "Decoding C String IV");
/*     */               }
/*     */               
/* 217 */               iv = base64decoder.decode(components[2]);
/*     */             } 
/*     */           } 
/* 220 */         } catch (IllegalArgumentException e) {
/* 221 */           if (Trace.isOn) {
/* 222 */             Trace.catchBlock("com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract", "createEncodedPasswordFromString(String, String, boolean)", e);
/*     */           }
/*     */           
/* 225 */           PBEException pBEException = new PBEException("V1 password IV or password was not base64 encoded", PBEException.PBERC.ENCODEDSTRINGINCORRECTFORMAT);
/*     */ 
/*     */           
/* 228 */           if (Trace.isOn) {
/* 229 */             Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract", "createEncodedPasswordFromString(String, String, boolean)", pBEException);
/*     */           }
/*     */           
/* 232 */           throw pBEException;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 237 */         encodedPasswordV1 = new EncodedPasswordV1(algorithm, iv, password, eyeCatcher);
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/* 242 */         thrown = new PBEException("Unknown algorithm value", PBEException.PBERC.UNRECOGNIZEDALGORITHM);
/* 243 */         if (Trace.isOn) {
/* 244 */           Trace.throwing("com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract", "createEncodedPasswordFromString(String, String, boolean)", thrown);
/*     */         }
/*     */         
/* 247 */         throw thrown;
/*     */     } 
/*     */     
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.exit("com.ibm.msg.client.commonservices.passwordprotection.EncodedPasswordAbstract", "createEncodedPasswordFromString(String, String, boolean)", 
/* 252 */           encodedPasswordV1.isTraceable() ? encodedPasswordV1 : "********");
/*     */     }
/* 254 */     return (EncodedPasswordAbstract)encodedPasswordV1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 262 */     return super.hashCode();
/*     */   }
/*     */   
/*     */   public String getEyeCatcher() {
/* 266 */     return this.eyeCatcher;
/*     */   }
/*     */   
/*     */   public void setEyeCatcher(String eyeCatcher) {
/* 270 */     this.eyeCatcher = eyeCatcher;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTraceable() {
/* 279 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\commonservices\passwordprotection\EncodedPasswordAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */