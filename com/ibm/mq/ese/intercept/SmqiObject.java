/*     */ package com.ibm.mq.ese.intercept;
/*     */ 
/*     */ import com.ibm.mq.constants.CMQC;
/*     */ import com.ibm.mq.ese.core.EseUser;
/*     */ import com.ibm.mq.ese.core.SecurityPolicy;
/*     */ import com.ibm.mq.jmqi.handles.Hobj;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.security.SecureRandom;
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
/*     */ public class SmqiObject
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/SmqiObject.java";
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.ese.intercept.SmqiObject", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/intercept/SmqiObject.java");
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
/*  66 */   private Hobj hobj = CMQC.jmqi_MQHO_NONE;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   private String objectName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   private String resolvedName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SecurityPolicy secPolicy;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ConnectionContext context;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private EseUser user;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int mqObjectType;
/*     */ 
/*     */ 
/*     */   
/*     */   private int openOpts;
/*     */ 
/*     */ 
/*     */   
/*     */   private long noMsgTime;
/*     */ 
/*     */ 
/*     */   
/* 109 */   private byte[] secretKeyForPut = null;
/* 110 */   private byte[] secretKeyForPutProtected = null;
/*     */   
/* 112 */   private byte[] secretKeyForGet = null;
/* 113 */   private byte[] secretKeyForGetEncrypted = null;
/* 114 */   String encAlgorithmForGet = null;
/* 115 */   String senderDNForGet = null;
/*     */   
/* 117 */   private int useCount = -1;
/*     */   
/*     */   private boolean senderCertificateValidated = false;
/*     */   
/* 121 */   SecureRandom sr = new SecureRandom();
/*     */   
/*     */   protected SmqiObject() {
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.entry(this, "com.ibm.mq.ese.intercept.SmqiObject", "<init>()");
/*     */     }
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.exit(this, "com.ibm.mq.ese.intercept.SmqiObject", "<init>()");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public SmqiObject(Hobj hobj, ConnectionContext connectionContext) {
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.entry(this, "com.ibm.mq.ese.intercept.SmqiObject", "<init>(Hobj,ConnectionContext)", new Object[] { hobj, connectionContext });
/*     */     }
/*     */     
/* 138 */     this.hobj = hobj;
/* 139 */     this.context = connectionContext;
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.exit(this, "com.ibm.mq.ese.intercept.SmqiObject", "<init>(Hobj,ConnectionContext)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.entry(this, "com.ibm.mq.ese.intercept.SmqiObject", "toString()");
/*     */     }
/* 151 */     StringBuilder buffer = new StringBuilder();
/* 152 */     buffer.append("hobj: '").append(this.hobj);
/* 153 */     String traceRet1 = buffer.toString();
/* 154 */     if (Trace.isOn) {
/* 155 */       Trace.exit(this, "com.ibm.mq.ese.intercept.SmqiObject", "toString()", traceRet1);
/*     */     }
/* 157 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isModelQueue() {
/* 168 */     boolean traceRet1 = (this.mqObjectType == 1 && !this.objectName.equals(this.resolvedName));
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "isModelQueue()", "getter", 
/* 171 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 173 */     return traceRet1;
/*     */   }
/*     */   
/*     */   public String getObjectName() {
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "getObjectName()", "getter", this.objectName);
/*     */     }
/*     */     
/* 181 */     return this.objectName;
/*     */   }
/*     */   
/*     */   public void setObjectName(String name) {
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "setObjectName(String)", "setter", name);
/*     */     }
/*     */     
/* 189 */     this.objectName = name;
/*     */   }
/*     */   
/*     */   public Hobj getHobj() {
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "getHobj()", "getter", this.hobj);
/*     */     }
/* 196 */     return this.hobj;
/*     */   }
/*     */   
/*     */   public void setHobj(Hobj hobj) {
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "setHobj(Hobj)", "setter", hobj);
/*     */     }
/* 203 */     this.hobj = hobj;
/*     */   }
/*     */   
/*     */   public void setSecPolicy(SecurityPolicy policy) {
/* 207 */     if (Trace.isOn) {
/* 208 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "setSecPolicy(SecurityPolicy)", "setter", policy);
/*     */     }
/*     */     
/* 211 */     this.secPolicy = policy;
/*     */   }
/*     */   
/*     */   public SecurityPolicy getSecPolicy() {
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "getSecPolicy()", "getter", this.secPolicy);
/*     */     }
/* 218 */     return this.secPolicy;
/*     */   }
/*     */   
/*     */   public ConnectionContext getContext() {
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "getContext()", "getter", this.context);
/*     */     }
/* 225 */     return this.context;
/*     */   }
/*     */   
/*     */   public void setUser(EseUser userInfo) {
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "setUser(EseUser)", "setter", userInfo);
/*     */     }
/*     */     
/* 233 */     this.user = userInfo;
/*     */   }
/*     */   
/*     */   public EseUser getUser() {
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "getUser()", "getter", this.user);
/*     */     }
/* 240 */     return this.user;
/*     */   }
/*     */   
/*     */   public int getKeyUsesRemaining() {
/* 244 */     int keyUsesRemaining = 0;
/* 245 */     switch (this.secPolicy.getReuse()) {
/*     */       case -1:
/* 247 */         keyUsesRemaining = Integer.MAX_VALUE;
/*     */         break;
/*     */       case 0:
/* 250 */         keyUsesRemaining = 0;
/*     */         break;
/*     */       default:
/* 253 */         keyUsesRemaining = this.secPolicy.getReuse() - this.useCount + 1;
/*     */         break;
/*     */     } 
/*     */     
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "getKeyUsesRemaining()", "getter", 
/* 259 */           Integer.valueOf(keyUsesRemaining));
/*     */     }
/* 261 */     return keyUsesRemaining;
/*     */   }
/*     */   
/*     */   public String getResolvedName() {
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "getResolvedName()", "getter", this.resolvedName);
/*     */     }
/*     */     
/* 269 */     return this.resolvedName;
/*     */   }
/*     */   
/*     */   public void setResolvedName(String resolvedName) {
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "setResolvedName(String)", "setter", resolvedName);
/*     */     }
/*     */     
/* 277 */     this.resolvedName = resolvedName;
/*     */   }
/*     */   
/*     */   public int getMqObjectType() {
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "getMqObjectType()", "getter", 
/* 283 */           Integer.valueOf(this.mqObjectType));
/*     */     }
/* 285 */     return this.mqObjectType;
/*     */   }
/*     */   
/*     */   public void setMqObjectType(int mqObjectType) {
/* 289 */     if (Trace.isOn) {
/* 290 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "setMqObjectType(int)", "setter", 
/* 291 */           Integer.valueOf(mqObjectType));
/*     */     }
/* 293 */     this.mqObjectType = mqObjectType;
/*     */   }
/*     */   
/*     */   public void setOpenOpts(int options) {
/* 297 */     if (Trace.isOn) {
/* 298 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "setOpenOpts(int)", "setter", 
/* 299 */           Integer.valueOf(options));
/*     */     }
/* 301 */     this.openOpts = options;
/*     */   }
/*     */   
/*     */   public int getOpenOpts() {
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "getOpenOpts()", "getter", 
/* 307 */           Integer.valueOf(this.openOpts));
/*     */     }
/* 309 */     return this.openOpts;
/*     */   }
/*     */   
/*     */   public long getNoMsgTime() {
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "getNoMsgTime()", "getter", 
/* 315 */           Long.valueOf(this.noMsgTime));
/*     */     }
/* 317 */     return this.noMsgTime;
/*     */   }
/*     */   
/*     */   public void setNoMsgTime(long noMsgTime) {
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "setNoMsgTime(long)", "setter", 
/* 323 */           Long.valueOf(noMsgTime));
/*     */     }
/* 325 */     this.noMsgTime = noMsgTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[][] getSecretKeyForPut(int keySize) {
/* 333 */     if (Trace.isOn)
/* 334 */       Trace.entry(this, "com.ibm.mq.ese.intercept.SmqiObject", "getSecretKeyForPut(int)", new Object[] {
/* 335 */             Integer.valueOf(keySize)
/*     */           }); 
/* 337 */     int keySizeBytes = keySize / 8;
/*     */     
/* 339 */     if (this.secretKeyForPut == null || this.useCount == -1) {
/* 340 */       if (Trace.isOn) {
/* 341 */         Trace.exit(this, "com.ibm.mq.ese.intercept.SmqiObject", "getSecretKeyForPut(int)", null, 0);
/*     */       }
/* 343 */       return (byte[][])null;
/*     */     } 
/*     */     
/* 346 */     if (this.secretKeyForPut.length != keySizeBytes) {
/* 347 */       if (Trace.isOn) {
/* 348 */         Trace.exit(this, "com.ibm.mq.ese.intercept.SmqiObject", "getSecretKeyForPut(int)", null, 1);
/*     */       }
/* 350 */       return (byte[][])null;
/*     */     } 
/*     */     
/* 353 */     if (this.secPolicy.getReuse() == -1) {
/* 354 */       byte[][] arrayOfByte = { this.secretKeyForPut, this.secretKeyForPutProtected };
/* 355 */       if (Trace.isOn) {
/* 356 */         Trace.exit(this, "com.ibm.mq.ese.intercept.SmqiObject", "getSecretKeyForPut(int)", arrayOfByte, 2);
/*     */       }
/* 358 */       return arrayOfByte;
/*     */     } 
/*     */     
/* 361 */     if (this.secPolicy.getReuse() <= this.useCount) {
/* 362 */       if (Trace.isOn) {
/* 363 */         Trace.exit(this, "com.ibm.mq.ese.intercept.SmqiObject", "getSecretKeyForPut(int)", null, 3);
/*     */       }
/* 365 */       return (byte[][])null;
/*     */     } 
/*     */ 
/*     */     
/* 369 */     this.useCount++;
/* 370 */     byte[][] result = { this.secretKeyForPut, this.secretKeyForPutProtected };
/* 371 */     if (Trace.isOn) {
/* 372 */       Trace.exit(this, "com.ibm.mq.ese.intercept.SmqiObject", "getSecretKeyForPut(int)", result, 4);
/*     */     }
/* 374 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] newSecretKeyForPut(int keySize) {
/* 379 */     if (Trace.isOn)
/* 380 */       Trace.entry(this, "com.ibm.mq.ese.intercept.SmqiObject", "newSecretKeyForPut(int)", new Object[] {
/* 381 */             Integer.valueOf(keySize)
/*     */           }); 
/* 383 */     int keySizeBytes = keySize / 8;
/* 384 */     byte[] newKey = new byte[keySizeBytes];
/* 385 */     this.sr.nextBytes(newKey);
/* 386 */     this.useCount = 1;
/* 387 */     if (Trace.isOn) {
/* 388 */       Trace.exit(this, "com.ibm.mq.ese.intercept.SmqiObject", "newSecretKeyForPut(int)", newKey);
/*     */     }
/* 390 */     return newKey;
/*     */   }
/*     */   
/*     */   public void cacheSecretKeyForPut(byte[] secretKey, byte[] secretKeyProtected) {
/* 394 */     if (Trace.isOn) {
/* 395 */       Trace.entry(this, "com.ibm.mq.ese.intercept.SmqiObject", "cacheSecretKeyForPut(byte[], byte[])", new Object[] { secretKey, secretKeyProtected });
/*     */     }
/*     */     
/* 398 */     this.secretKeyForPut = Arrays.copyOf(secretKey, secretKey.length);
/* 399 */     this.secretKeyForPutProtected = Arrays.copyOf(secretKeyProtected, secretKeyProtected.length);
/* 400 */     if (Trace.isOn) {
/* 401 */       Trace.exit(this, "com.ibm.mq.ese.intercept.SmqiObject", "cacheSecretKeyForPut(byte[], byte[])");
/*     */     }
/*     */   }
/*     */   
/*     */   public void cacheSecretKeyForGet(byte[] secretKey, byte[] secretKeyEncrypted) {
/* 406 */     if (Trace.isOn) {
/* 407 */       Trace.entry(this, "com.ibm.mq.ese.intercept.SmqiObject", "cacheSecretKeyForGet(byte[], byte[])", new Object[] { secretKey, secretKeyEncrypted });
/*     */     }
/*     */     
/* 410 */     this.secretKeyForGet = Arrays.copyOf(secretKey, secretKey.length);
/* 411 */     this.secretKeyForGetEncrypted = Arrays.copyOf(secretKeyEncrypted, secretKeyEncrypted.length);
/* 412 */     if (Trace.isOn) {
/* 413 */       Trace.exit(this, "com.ibm.mq.ese.intercept.SmqiObject", "cacheSecretKeyForGet(byte[], byte[])");
/*     */     }
/*     */   }
/*     */   
/*     */   public byte[] checkSecretKeyForGet(byte[] secretKeyEncrypted) {
/* 418 */     if (Trace.isOn) {
/* 419 */       Trace.entry(this, "com.ibm.mq.ese.intercept.SmqiObject", "checkSecretKeyForGet(byte[])", new Object[] { secretKeyEncrypted });
/*     */     }
/*     */     
/* 422 */     if (Arrays.equals(this.secretKeyForGetEncrypted, secretKeyEncrypted)) {
/* 423 */       if (Trace.isOn) {
/* 424 */         Trace.exit(this, "com.ibm.mq.ese.intercept.SmqiObject", "checkSecretKeyForGet(byte[])", this.secretKeyForGet, 0);
/*     */       }
/* 426 */       return this.secretKeyForGet;
/*     */     } 
/* 428 */     if (Trace.isOn) {
/* 429 */       Trace.exit(this, "com.ibm.mq.ese.intercept.SmqiObject", "checkSecretKeyForGet(byte[])", null, 1);
/*     */     }
/* 431 */     return null;
/*     */   }
/*     */   
/*     */   public void cacheEncAlgorithmForGet(String actualEncAlg) {
/* 435 */     if (Trace.isOn) {
/* 436 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "cacheEncAlgorithmForGet(String)", "setter", actualEncAlg);
/*     */     }
/*     */     
/* 439 */     this.encAlgorithmForGet = actualEncAlg;
/*     */   }
/*     */   
/*     */   public String getEncAlgorithmForGet() {
/* 443 */     if (Trace.isOn) {
/* 444 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "getEncAlgorithmForGet()", "getter", this.encAlgorithmForGet);
/*     */     }
/*     */     
/* 447 */     return this.encAlgorithmForGet;
/*     */   }
/*     */   
/*     */   public void cacheSenderDNForGet(String senderDN) {
/* 451 */     if (Trace.isOn) {
/* 452 */       Trace.data(this, "com.ibm.mq.ese.intercept.SmqiObject", "cacheSenderDNForGet(String)", "setter", 
/* 453 */           Integer.valueOf(senderDN));
/*     */     }
/* 455 */     this.senderDNForGet = senderDN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean senderCertificateValidated() {
/* 463 */     return this.senderCertificateValidated;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void senderCertificateValidated(boolean senderCertificateValidated) {
/* 470 */     this.senderCertificateValidated = senderCertificateValidated;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\intercept\SmqiObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */