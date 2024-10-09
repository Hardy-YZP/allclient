/*     */ package com.ibm.mq.ese.core;
/*     */ 
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SecurityPolicy
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/SecurityPolicy.java";
/*     */   public static final int MQESE_TOLERATE_NO = 0;
/*     */   public static final int MQESE_TOLERATE_YES = 1;
/*     */   private String name;
/*     */   private int qop;
/*     */   
/*     */   static {
/*  38 */     if (Trace.isOn) {
/*  39 */       Trace.data("com.ibm.mq.ese.core.SecurityPolicy", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/core/SecurityPolicy.java");
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
/*  75 */   private int reuse = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String errorQName;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String signAlg;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String encAlg;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   private List<String> signerDNs = new LinkedList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   private List<String> recipientDNs = new LinkedList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private X509Certificate[] recipientsCertificates;
/*     */ 
/*     */ 
/*     */   
/*     */   private int version;
/*     */ 
/*     */ 
/*     */   
/*     */   private int audit;
/*     */ 
/*     */ 
/*     */   
/*     */   private int tolerateFlag;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SecurityPolicy(String name) {
/* 123 */     this();
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.entry(this, "com.ibm.mq.ese.core.SecurityPolicy", "<init>(String)", new Object[] { name });
/*     */     }
/*     */     
/* 128 */     this.name = name;
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.exit(this, "com.ibm.mq.ese.core.SecurityPolicy", "<init>(String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SecurityPolicy(String name, String sign) {
/* 137 */     this(name);
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.entry(this, "com.ibm.mq.ese.core.SecurityPolicy", "<init>(String,String)", new Object[] { name, sign });
/*     */     }
/*     */     
/* 142 */     this.signAlg = sign;
/* 143 */     this.qop = 1;
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.exit(this, "com.ibm.mq.ese.core.SecurityPolicy", "<init>(String,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SecurityPolicy(String name, String sign, String enc) {
/* 152 */     this(name, sign);
/* 153 */     if (Trace.isOn) {
/* 154 */       Trace.entry(this, "com.ibm.mq.ese.core.SecurityPolicy", "<init>(String,String,String)", new Object[] { name, sign, enc });
/*     */     }
/*     */ 
/*     */     
/* 158 */     this.encAlg = enc;
/* 159 */     this.qop = 2;
/* 160 */     if (Trace.isOn) {
/* 161 */       Trace.exit(this, "com.ibm.mq.ese.core.SecurityPolicy", "<init>(String,String,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SecurityPolicy(String name, String enc, int reuse) {
/* 168 */     this(name);
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.entry(this, "com.ibm.mq.ese.core.SecurityPolicy", "<init>(String,String,int)", new Object[] { name, enc, 
/*     */             
/* 172 */             Integer.valueOf(reuse) });
/*     */     }
/* 174 */     this.encAlg = enc;
/* 175 */     this.qop = 3;
/* 176 */     this.reuse = reuse;
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.exit(this, "com.ibm.mq.ese.core.SecurityPolicy", "<init>(String,String,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SecurityPolicy() {
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.entry(this, "com.ibm.mq.ese.core.SecurityPolicy", "<init>()");
/*     */     }
/* 188 */     this.qop = 0;
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.exit(this, "com.ibm.mq.ese.core.SecurityPolicy", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void deduceQoP() {
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.entry(this, "com.ibm.mq.ese.core.SecurityPolicy", "deduceQoP()");
/*     */     }
/*     */ 
/*     */     
/* 204 */     if (this.signAlg != null) {
/* 205 */       if (this.encAlg != null) {
/* 206 */         this.qop = 2;
/*     */       } else {
/*     */         
/* 209 */         this.qop = 1;
/*     */       }
/*     */     
/* 212 */     } else if (this.encAlg != null) {
/* 213 */       this.qop = 3;
/*     */     } 
/*     */     
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.exit(this, "com.ibm.mq.ese.core.SecurityPolicy", "deduceQoP()");
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
/*     */   public String toString() {
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.entry(this, "com.ibm.mq.ese.core.SecurityPolicy", "toString()");
/*     */     }
/*     */     
/* 234 */     String traceRet1 = this.name + "/" + this.signAlg + "/" + this.encAlg + "/tflag:" + this.tolerateFlag;
/*     */     
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.exit(this, "com.ibm.mq.ese.core.SecurityPolicy", "toString()", traceRet1);
/*     */     }
/*     */     
/* 240 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "getName()", "getter", this.name);
/*     */     }
/*     */     
/* 248 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "setName(String)", "setter", name);
/*     */     }
/*     */     
/* 256 */     this.name = name;
/*     */   }
/*     */   
/*     */   public int getQop() {
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "getQop()", "getter", 
/* 262 */           Integer.valueOf(this.qop));
/*     */     }
/* 264 */     return this.qop;
/*     */   }
/*     */   
/*     */   public int getReuse() {
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "getReuse()", "getter", 
/* 270 */           Integer.valueOf(this.reuse));
/*     */     }
/* 272 */     return this.reuse;
/*     */   }
/*     */   
/*     */   public String getErrorQName() {
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "getErrorQName()", "getter", this.errorQName);
/*     */     }
/*     */     
/* 280 */     return this.errorQName;
/*     */   }
/*     */   
/*     */   public void setErrorQName(String errorQName) {
/* 284 */     if (Trace.isOn) {
/* 285 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "setErrorQName(String)", "setter", errorQName);
/*     */     }
/*     */     
/* 288 */     this.errorQName = errorQName;
/*     */   }
/*     */   
/*     */   public String getSignAlg() {
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "getSignAlg()", "getter", this.signAlg);
/*     */     }
/*     */     
/* 296 */     return (this.signAlg == null) ? "" : this.signAlg;
/*     */   }
/*     */   
/*     */   public void setSignAlg(String signAlg) {
/* 300 */     if (Trace.isOn) {
/* 301 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "setSignAlg(String)", "setter", signAlg);
/*     */     }
/*     */     
/* 304 */     this.signAlg = (signAlg == null) ? null : ((signAlg.length() == 0) ? null : signAlg);
/* 305 */     deduceQoP();
/*     */   }
/*     */   
/*     */   public String getEncAlg() {
/* 309 */     if (Trace.isOn) {
/* 310 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "getEncAlg()", "getter", this.encAlg);
/*     */     }
/*     */     
/* 313 */     return (this.encAlg == null) ? "" : this.encAlg;
/*     */   }
/*     */   
/*     */   public void setEncAlg(String encAlg) {
/* 317 */     if (Trace.isOn) {
/* 318 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "setEncAlg(String)", "setter", encAlg);
/*     */     }
/*     */     
/* 321 */     this.encAlg = (encAlg == null) ? null : ((encAlg.length() == 0) ? null : encAlg);
/* 322 */     deduceQoP();
/*     */   }
/*     */   
/*     */   public void setReuse(int reuse) {
/* 326 */     if (Trace.isOn) {
/* 327 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "setReuse(int)", "setter", 
/* 328 */           Integer.valueOf(reuse));
/*     */     }
/* 330 */     this.reuse = reuse;
/*     */   }
/*     */   
/*     */   public List<String> getSignerDNs() {
/* 334 */     if (Trace.isOn) {
/* 335 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "getSignerDNs()", "getter", this.signerDNs);
/*     */     }
/*     */     
/* 338 */     return this.signerDNs;
/*     */   }
/*     */   
/*     */   public void setSignerDNs(List<String> signerDNs) {
/* 342 */     if (Trace.isOn) {
/* 343 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "setSignerDNs(List)", "setter", signerDNs);
/*     */     }
/*     */     
/* 346 */     this.signerDNs = signerDNs;
/*     */   }
/*     */   
/*     */   public List<String> getRecipientDNs() {
/* 350 */     if (Trace.isOn) {
/* 351 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "getRecipientDNs()", "getter", this.recipientDNs);
/*     */     }
/*     */     
/* 354 */     return this.recipientDNs;
/*     */   }
/*     */   
/*     */   public void setRecipientDNs(List<String> recipientDNs) {
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "setRecipientDNs(List)", "setter", recipientDNs);
/*     */     }
/*     */     
/* 362 */     this.recipientDNs = recipientDNs;
/*     */   }
/*     */   
/*     */   public X509Certificate[] getRecipientsCertificates() {
/* 366 */     if (Trace.isOn) {
/* 367 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "getRecipientsCertificates()", "getter", this.recipientsCertificates);
/*     */     }
/*     */ 
/*     */     
/* 371 */     return this.recipientsCertificates;
/*     */   }
/*     */   
/*     */   public void setRecipientsCertificates(X509Certificate[] certificates) {
/* 375 */     if (Trace.isOn) {
/* 376 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "setRecipientsCertificates(X509Certificate [ ])", "setter", certificates);
/*     */     }
/*     */ 
/*     */     
/* 380 */     this.recipientsCertificates = certificates;
/*     */   }
/*     */   
/*     */   public int getVersion() {
/* 384 */     if (Trace.isOn) {
/* 385 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "getVersion()", "getter", 
/* 386 */           Integer.valueOf(this.version));
/*     */     }
/* 388 */     return this.version;
/*     */   }
/*     */   
/*     */   public void setVersion(int version) {
/* 392 */     if (Trace.isOn) {
/* 393 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "setVersion(int)", "setter", 
/* 394 */           Integer.valueOf(version));
/*     */     }
/* 396 */     this.version = version;
/*     */   }
/*     */   
/*     */   public void setAudit(int audit) {
/* 400 */     if (Trace.isOn) {
/* 401 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "setAudit(int)", "setter", 
/* 402 */           Integer.valueOf(audit));
/*     */     }
/* 404 */     this.audit = audit;
/*     */   }
/*     */   
/*     */   public int getAudit() {
/* 408 */     if (Trace.isOn) {
/* 409 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "getAudit()", "getter", 
/* 410 */           Integer.valueOf(this.audit));
/*     */     }
/* 412 */     return this.audit;
/*     */   }
/*     */   
/*     */   public int getTolerateFlag() {
/* 416 */     if (Trace.isOn) {
/* 417 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "getTolerateFlag()", "getter", 
/*     */           
/* 419 */           Integer.valueOf(this.tolerateFlag));
/*     */     }
/* 421 */     return this.tolerateFlag;
/*     */   }
/*     */   
/*     */   public void setTolerateFlag(int tolerateFlag) {
/* 425 */     if (Trace.isOn) {
/* 426 */       Trace.data(this, "com.ibm.mq.ese.core.SecurityPolicy", "setTolerateFlag(int)", "setter", 
/*     */           
/* 428 */           Integer.valueOf(tolerateFlag));
/*     */     }
/* 430 */     this.tolerateFlag = tolerateFlag;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\core\SecurityPolicy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */