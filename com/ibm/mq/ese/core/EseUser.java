/*     */ package com.ibm.mq.ese.core;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.security.Principal;
/*     */ import java.security.PrivateKey;
/*     */ import java.security.cert.X509Certificate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EseUser
/*     */   implements Principal
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/EseUser.java";
/*     */   private String userName;
/*     */   private String userDN;
/*     */   private KeyStoreAccess keyStoreAccess;
/*     */   private X509Certificate userCertificate;
/*     */   private String alias;
/*     */   private String provider;
/*     */   private PkiSpec pkiSpec;
/*     */   
/*     */   static {
/*  39 */     if (Trace.isOn) {
/*  40 */       Trace.data("com.ibm.mq.ese.core.EseUser", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/EseUser.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserName() {
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.data(this, "com.ibm.mq.ese.core.EseUser", "getUserName()", "getter", this.userName);
/*     */     }
/*  90 */     return this.userName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KeyStoreAccess getKeyStoreAccess() {
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.data(this, "com.ibm.mq.ese.core.EseUser", "getKeyStoreAccess()", "getter", this.keyStoreAccess);
/*     */     }
/* 102 */     return this.keyStoreAccess;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserDN() {
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.data(this, "com.ibm.mq.ese.core.EseUser", "getUserDN()", "getter", this.userDN);
/*     */     }
/* 114 */     return this.userDN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrivateKey getPrivateKey() throws AMBIException {
/* 123 */     PrivateKey traceRet1 = this.keyStoreAccess.getPrivateKey(this.alias);
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.data(this, "com.ibm.mq.ese.core.EseUser", "getPrivateKey()", "getter", traceRet1);
/*     */     }
/* 127 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public X509Certificate getCertificate() {
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.data(this, "com.ibm.mq.ese.core.EseUser", "getCertificate()", "getter", this.userCertificate);
/*     */     }
/*     */     
/* 140 */     return this.userCertificate;
/*     */   }
/*     */   
/*     */   public void setUserName(String userName) {
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.data(this, "com.ibm.mq.ese.core.EseUser", "setUserName(String)", "setter", userName);
/*     */     }
/* 147 */     this.userName = userName;
/*     */   }
/*     */   
/*     */   public void setUserDN(String userDN) {
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.data(this, "com.ibm.mq.ese.core.EseUser", "setUserDN(String)", "setter", userDN);
/*     */     }
/* 154 */     this.userDN = userDN;
/*     */   }
/*     */   
/*     */   public void setKeyStoreAccess(KeyStoreAccess keyStore) {
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.data(this, "com.ibm.mq.ese.core.EseUser", "setKeyStoreAccess(KeyStoreAccess)", "setter", keyStore);
/*     */     }
/*     */     
/* 162 */     this.keyStoreAccess = keyStore;
/*     */   }
/*     */   
/*     */   public void setUserCertificate(X509Certificate userCertificate) {
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.data(this, "com.ibm.mq.ese.core.EseUser", "setUserCertificate(X509Certificate)", "setter", userCertificate);
/*     */     }
/*     */     
/* 170 */     this.userCertificate = userCertificate;
/*     */   }
/*     */   
/*     */   public void setAlias(String credentialAlias) {
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.data(this, "com.ibm.mq.ese.core.EseUser", "setAlias(String)", "setter", credentialAlias);
/*     */     }
/*     */     
/* 178 */     this.alias = credentialAlias;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.entry(this, "com.ibm.mq.ese.core.EseUser", "toString()");
/*     */     }
/* 191 */     StringBuilder buffer = new StringBuilder();
/* 192 */     buffer.append("name: ").append(this.userName);
/* 193 */     buffer.append("; DN: ").append(this.userDN);
/* 194 */     String traceRet1 = buffer.toString();
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.exit(this, "com.ibm.mq.ese.core.EseUser", "toString()", traceRet1);
/*     */     }
/* 198 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 206 */     String traceRet1 = getUserDN();
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.data(this, "com.ibm.mq.ese.core.EseUser", "getName()", "getter", traceRet1);
/*     */     }
/* 210 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getKeystoreAlias() {
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.data(this, "com.ibm.mq.ese.core.EseUser", "getKeystoreAlias()", "getter", this.alias);
/*     */     }
/* 220 */     return this.alias;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProvider() {
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.data(this, "com.ibm.mq.ese.core.EseUser", "getProvider()", "getter", this.provider);
/*     */     }
/* 232 */     return this.provider;
/*     */   }
/*     */   
/*     */   public void setProvider(String provider) {
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.data(this, "com.ibm.mq.ese.core.EseUser", "setProvider(String)", "setter", provider);
/*     */     }
/* 239 */     this.provider = provider;
/*     */   }
/*     */   
/*     */   public PkiSpec getPkiSpec() {
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.data(this, "com.ibm.mq.ese.core.EseUser", "getPkiSpec()", "getter", this.pkiSpec);
/*     */     }
/* 246 */     return this.pkiSpec;
/*     */   }
/*     */   
/*     */   public void setPkiSpec(PkiSpec pkiSpec) {
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.data(this, "com.ibm.mq.ese.core.EseUser", "setPkiSpec(PkiSpec)", "setter", pkiSpec);
/*     */     }
/* 253 */     this.pkiSpec = pkiSpec;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\core\EseUser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */