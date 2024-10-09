/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQAIR
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQAIR.java";
/*     */   private static final int SIZEOF_STRUC_ID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_AUTH_INFO_TYPE = 4;
/*     */   private static final int SIZEOF_AUTH_INFO_CONN_NAME = 264;
/*     */   private static final int SIZEOF_LDAP_USER_NAME_OFFSET = 4;
/*     */   private static final int SIZEOF_LDAP_USER_NAME_LENGTH = 4;
/*     */   private static final int SIZEOF_LDAP_PASSWORD = 32;
/*     */   private static final int SIZEOF_OCSP_RESPONDER_URL = 256;
/*     */   
/*     */   static {
/*  66 */     if (Trace.isOn) {
/*  67 */       Trace.data("com.ibm.mq.jmqi.MQAIR", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQAIR.java");
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
/*  91 */   private int version = 1;
/*  92 */   private int authInfoType = 1;
/*  93 */   private String authInfoConnName = "";
/*  94 */   private String ldapUserName = null;
/*  95 */   private String ldapPassword = "";
/*  96 */   private String ocspResponderURL = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] ldapUserName_cachedBytes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQAIR(JmqiEnvironment env) {
/* 109 */     super(env);
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.entry(this, "com.ibm.mq.jmqi.MQAIR", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.exit(this, "com.ibm.mq.jmqi.MQAIR", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrentVersion() {
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.data(this, "com.ibm.mq.jmqi.MQAIR", "getCurrentVersion()", "getter", 
/* 126 */           Integer.valueOf(2));
/*     */     }
/* 128 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV1(int ptrSize) {
/* 138 */     int size = 0;
/* 139 */     size += 4;
/* 140 */     size += 4;
/* 141 */     size += 4;
/* 142 */     size += 264;
/* 143 */     size += JmqiTools.alignToGrain(ptrSize, size);
/* 144 */     size += ptrSize;
/* 145 */     size += 4;
/* 146 */     size += 4;
/* 147 */     size += 32;
/*     */     
/* 149 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 159 */     int size = getFieldSizeV1(ptrSize);
/* 160 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*     */     
/* 162 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getFieldSizeV2(int ptrSize) {
/* 172 */     int size = getFieldSizeV1(ptrSize);
/* 173 */     size += 256;
/*     */     
/* 175 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV2(int ptrSize) {
/* 185 */     int size = getFieldSizeV2(ptrSize);
/* 186 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*     */     
/* 188 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize(int ptrSize) throws JmqiException {
/* 199 */     if (Trace.isOn)
/* 200 */       Trace.entry(this, "com.ibm.mq.jmqi.MQAIR", "getSize(int)", new Object[] {
/* 201 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 203 */     int traceRet1 = getSize(this.env, this.version, ptrSize);
/*     */     
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.exit(this, "com.ibm.mq.jmqi.MQAIR", "getSize(int)", Integer.valueOf(traceRet1));
/*     */     }
/* 208 */     return traceRet1;
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
/*     */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/*     */     int size;
/* 224 */     switch (version) {
/*     */       case 1:
/* 226 */         size = getSizeV1(ptrSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 239 */         return size;case 2: size = getSizeV2(ptrSize); return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 2385, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 252 */     int size = getSize(this.env, this.version, ptrSize);
/*     */ 
/*     */     
/* 255 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 257 */     this.ldapUserName_cachedBytes = dc.getStrBytes(this.ldapUserName, cp);
/* 258 */     size += this.ldapUserName_cachedBytes.length;
/*     */     
/* 260 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.entry(this, "com.ibm.mq.jmqi.MQAIR", "clone()");
/*     */     }
/*     */     
/* 275 */     Object newObject = super.clone();
/* 276 */     MQAIR newMQAIR = (MQAIR)newObject;
/*     */     
/* 278 */     newMQAIR.setVersion(this.version);
/* 279 */     newMQAIR.setAuthInfoType(this.authInfoType);
/* 280 */     newMQAIR.setAuthInfoConnName(this.authInfoConnName);
/* 281 */     newMQAIR.setLdapUserName(this.ldapUserName);
/* 282 */     newMQAIR.setLdapPassword(this.ldapPassword);
/* 283 */     newMQAIR.setOcspResponderURL(this.ocspResponderURL);
/*     */     
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.exit(this, "com.ibm.mq.jmqi.MQAIR", "clone()", newObject);
/*     */     }
/* 288 */     return newObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 298 */     if (Trace.isOn) {
/* 299 */       Trace.entry(this, "com.ibm.mq.jmqi.MQAIR", "hashCode()");
/*     */     }
/* 301 */     int hashCode = 0;
/* 302 */     hashCode += 31 * this.version;
/* 303 */     hashCode += 37 * this.authInfoType;
/* 304 */     hashCode += 41 * ((this.authInfoConnName != null) ? this.authInfoConnName.hashCode() : 0);
/* 305 */     hashCode += 43 * ((this.ldapUserName != null) ? this.ldapUserName.hashCode() : 0);
/* 306 */     hashCode += 47 * ((this.ldapPassword != null) ? this.ldapPassword.hashCode() : 0);
/* 307 */     hashCode += 53 * ((this.ocspResponderURL != null) ? this.ocspResponderURL.hashCode() : 0);
/*     */     
/* 309 */     if (Trace.isOn) {
/* 310 */       Trace.exit(this, "com.ibm.mq.jmqi.MQAIR", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 312 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 323 */     if (Trace.isOn) {
/* 324 */       Trace.entry(this, "com.ibm.mq.jmqi.MQAIR", "equals(Object)", new Object[] { object });
/*     */     }
/* 326 */     boolean areEqual = true;
/* 327 */     if (object == null) {
/* 328 */       areEqual = false;
/*     */     }
/* 330 */     else if (object instanceof MQAIR) {
/* 331 */       MQAIR mqair = (MQAIR)object;
/* 332 */       areEqual = (areEqual && this.version == mqair.getVersion());
/* 333 */       areEqual = (areEqual && this.authInfoType == mqair.getAuthInfoType());
/*     */       
/* 335 */       if (this.authInfoConnName == null) {
/* 336 */         areEqual = (areEqual && mqair.getAuthInfoConnName() == null);
/*     */       } else {
/*     */         
/* 339 */         areEqual = (areEqual && this.authInfoConnName.equals(mqair.getAuthInfoConnName()));
/*     */       } 
/*     */       
/* 342 */       if (this.ldapUserName == null) {
/* 343 */         areEqual = (areEqual && mqair.getLdapUserName() == null);
/*     */       } else {
/*     */         
/* 346 */         areEqual = (areEqual && this.ldapUserName.equals(mqair.getLdapUserName()));
/*     */       } 
/*     */       
/* 349 */       if (this.ldapPassword == null) {
/* 350 */         areEqual = (areEqual && mqair.getLdapPassword() == null);
/*     */       } else {
/*     */         
/* 353 */         areEqual = (areEqual && this.ldapPassword.equals(mqair.getLdapPassword()));
/*     */       } 
/*     */       
/* 356 */       if (this.ocspResponderURL == null) {
/* 357 */         areEqual = (areEqual && mqair.getOcspResponderURL() == null);
/*     */       } else {
/*     */         
/* 360 */         areEqual = (areEqual && this.ocspResponderURL.equals(mqair.getOcspResponderURL()));
/*     */       } 
/*     */     } else {
/*     */       
/* 364 */       areEqual = false;
/*     */     } 
/*     */     
/* 367 */     if (Trace.isOn) {
/* 368 */       Trace.exit(this, "com.ibm.mq.jmqi.MQAIR", "equals(Object)", Boolean.valueOf(areEqual));
/*     */     }
/* 370 */     return areEqual;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 378 */     if (Trace.isOn) {
/* 379 */       Trace.data(this, "com.ibm.mq.jmqi.MQAIR", "getVersion()", "getter", Integer.valueOf(this.version));
/*     */     }
/*     */     
/* 382 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 390 */     if (Trace.isOn) {
/* 391 */       Trace.data(this, "com.ibm.mq.jmqi.MQAIR", "setVersion(int)", "setter", 
/* 392 */           Integer.valueOf(version));
/*     */     }
/* 394 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAuthInfoType() {
/* 401 */     if (Trace.isOn) {
/* 402 */       Trace.data(this, "com.ibm.mq.jmqi.MQAIR", "getAuthInfoType()", "getter", 
/* 403 */           Integer.valueOf(this.authInfoType));
/*     */     }
/* 405 */     return this.authInfoType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAuthInfoType(int authInfoType) {
/* 412 */     if (Trace.isOn) {
/* 413 */       Trace.data(this, "com.ibm.mq.jmqi.MQAIR", "setAuthInfoType(int)", "setter", 
/* 414 */           Integer.valueOf(authInfoType));
/*     */     }
/* 416 */     this.authInfoType = authInfoType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAuthInfoConnName() {
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.data(this, "com.ibm.mq.jmqi.MQAIR", "getAuthInfoConnName()", "getter", this.authInfoConnName);
/*     */     }
/*     */     
/* 427 */     return this.authInfoConnName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAuthInfoConnName(String authInfoConnName) {
/* 434 */     if (Trace.isOn) {
/* 435 */       Trace.data(this, "com.ibm.mq.jmqi.MQAIR", "setAuthInfoConnName(String)", "setter", authInfoConnName);
/*     */     }
/*     */     
/* 438 */     this.authInfoConnName = authInfoConnName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLdapUserName() {
/* 445 */     if (Trace.isOn) {
/* 446 */       Trace.data(this, "com.ibm.mq.jmqi.MQAIR", "getLdapUserName()", "getter", this.ldapUserName);
/*     */     }
/* 448 */     return this.ldapUserName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLdapUserName(String ldapUserName) {
/* 455 */     if (Trace.isOn) {
/* 456 */       Trace.data(this, "com.ibm.mq.jmqi.MQAIR", "setLdapUserName(String)", "setter", ldapUserName);
/*     */     }
/*     */     
/* 459 */     this.ldapUserName = ldapUserName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLdapPassword() {
/* 467 */     if (Trace.isOn) {
/* 468 */       Trace.data(this, "com.ibm.mq.jmqi.MQAIR", "getLdapPassword()", "getter", (this.ldapPassword == null) ? null : "********");
/*     */     }
/*     */     
/* 471 */     return this.ldapPassword;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLdapPassword(String ldapPassword) {
/* 479 */     if (Trace.isOn) {
/* 480 */       Trace.data(this, "com.ibm.mq.jmqi.MQAIR", "setLdapPassword(String)", "setter", (ldapPassword == null) ? null : "********");
/*     */     }
/*     */     
/* 483 */     this.ldapPassword = ldapPassword;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOcspResponderURL() {
/* 490 */     if (Trace.isOn) {
/* 491 */       Trace.data(this, "com.ibm.mq.jmqi.MQAIR", "getOcspResponderURL()", "getter", this.ocspResponderURL);
/*     */     }
/*     */     
/* 494 */     return this.ocspResponderURL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOcspResponderURL(String ocspResponderURL) {
/* 501 */     if (Trace.isOn) {
/* 502 */       Trace.data(this, "com.ibm.mq.jmqi.MQAIR", "setOcspResponderURL(String)", "setter", ocspResponderURL);
/*     */     }
/*     */     
/* 505 */     this.ocspResponderURL = ocspResponderURL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 513 */     if (Trace.isOn) {
/* 514 */       Trace.entry(this, "com.ibm.mq.jmqi.MQAIR", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 516 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/*     */     
/* 519 */     int pos = offset;
/* 520 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 521 */     int ldapUserNameOffsetPosition = -1;
/* 522 */     int ldapUserNameLengthPosition = -1;
/*     */     
/* 524 */     dc.writeMQField("AIR ", buffer, pos, 4, cp, tls);
/* 525 */     pos += 4;
/*     */     
/* 527 */     dc.writeI32(this.version, buffer, pos, swap);
/* 528 */     pos += 4;
/*     */     
/* 530 */     dc.writeI32(this.authInfoType, buffer, pos, swap);
/* 531 */     pos += 4;
/*     */     
/* 533 */     dc.writeField(this.authInfoConnName, buffer, pos, 264, cp, tls);
/* 534 */     pos += 264;
/*     */     
/* 536 */     int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 537 */     dc.clear(buffer, pos, padding);
/* 538 */     pos += padding;
/*     */     
/* 540 */     dc.clear(buffer, pos, ptrSize);
/* 541 */     pos += ptrSize;
/*     */     
/* 543 */     ldapUserNameOffsetPosition = pos;
/* 544 */     pos += 4;
/*     */     
/* 546 */     ldapUserNameLengthPosition = pos;
/* 547 */     pos += 4;
/*     */     
/* 549 */     dc.writeField(this.ldapPassword, buffer, pos, 32, cp, tls);
/* 550 */     pos += 32;
/*     */     
/* 552 */     if (this.version == 1) {
/* 553 */       padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 554 */       dc.clear(buffer, pos, padding);
/* 555 */       pos += padding;
/*     */     } 
/* 557 */     if (this.version >= 2) {
/*     */       
/* 559 */       dc.writeField(this.ocspResponderURL, buffer, pos, 256, cp, tls);
/* 560 */       pos += 256;
/*     */       
/* 562 */       if (this.version == 2) {
/* 563 */         padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 564 */         dc.clear(buffer, pos, padding);
/* 565 */         pos += padding;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 572 */     if (ldapUserNameOffsetPosition > 0) {
/*     */       byte[] ldapUserNameBytes;
/*     */       
/* 575 */       if (this.ldapUserName_cachedBytes != null) {
/* 576 */         ldapUserNameBytes = this.ldapUserName_cachedBytes;
/* 577 */         this.ldapUserName_cachedBytes = null;
/*     */       } else {
/*     */         
/* 580 */         ldapUserNameBytes = dc.getStrBytes(this.ldapUserName, cp);
/*     */       } 
/*     */       
/* 583 */       dc.writeI32(pos - offset, buffer, ldapUserNameOffsetPosition, swap);
/* 584 */       dc.writeI32(ldapUserNameBytes.length, buffer, ldapUserNameLengthPosition, swap);
/*     */       
/* 586 */       System.arraycopy(ldapUserNameBytes, 0, buffer, pos, ldapUserNameBytes.length);
/* 587 */       pos += ldapUserNameBytes.length;
/*     */     } 
/*     */     
/* 590 */     if (Trace.isOn) {
/* 591 */       Trace.exit(this, "com.ibm.mq.jmqi.MQAIR", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 592 */           Integer.valueOf(pos));
/*     */     }
/* 594 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 602 */     if (Trace.isOn) {
/* 603 */       Trace.entry(this, "com.ibm.mq.jmqi.MQAIR", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 605 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/*     */     
/* 608 */     int pos = offset;
/* 609 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 611 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 612 */     if (!strucId.equals("AIR ")) {
/*     */       
/* 614 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2385, null);
/* 615 */       if (Trace.isOn) {
/* 616 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQAIR", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*     */       }
/*     */       
/* 619 */       throw traceRet1;
/*     */     } 
/* 621 */     pos += 4;
/*     */ 
/*     */     
/* 624 */     this.version = dc.readI32(buffer, pos, swap);
/* 625 */     pos += 4;
/*     */ 
/*     */     
/* 628 */     this.authInfoType = dc.readI32(buffer, pos, swap);
/* 629 */     pos += 4;
/*     */ 
/*     */     
/* 632 */     this.authInfoConnName = dc.readField(buffer, pos, 264, cp, tls);
/* 633 */     pos += 264;
/*     */ 
/*     */     
/* 636 */     int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 637 */     pos += padding;
/*     */ 
/*     */     
/* 640 */     pos += ptrSize;
/*     */ 
/*     */     
/* 643 */     int ldapUserNameOffset = dc.readI32(buffer, pos, swap);
/* 644 */     pos += 4;
/*     */ 
/*     */     
/* 647 */     int ldapUserNameLength = dc.readI32(buffer, pos, swap);
/* 648 */     pos += 4;
/*     */ 
/*     */     
/* 651 */     this.ldapPassword = dc.readField(buffer, pos, 32, cp, tls);
/* 652 */     pos += 32;
/*     */ 
/*     */     
/* 655 */     if (this.version == 1) {
/* 656 */       padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 657 */       pos += padding;
/*     */     } 
/*     */     
/* 660 */     if (this.version >= 2) {
/*     */       
/* 662 */       this.ocspResponderURL = dc.readField(buffer, pos, 256, cp, tls);
/* 663 */       pos += 256;
/*     */ 
/*     */       
/* 666 */       if (this.version == 2) {
/* 667 */         padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 668 */         pos += padding;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 677 */     if (ldapUserNameOffset > 0) {
/* 678 */       this.ldapUserName = dc.readField(buffer, pos, ldapUserNameLength, cp, tls);
/* 679 */       pos += ldapUserNameLength;
/*     */     } else {
/*     */       
/* 682 */       this.ldapUserName = null;
/*     */     } 
/*     */     
/* 685 */     if (Trace.isOn) {
/* 686 */       Trace.exit(this, "com.ibm.mq.jmqi.MQAIR", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 687 */           Integer.valueOf(pos));
/*     */     }
/* 689 */     return pos;
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
/*     */   public void addFieldsToFormatter(JmqiStructureFormatter fmt) {
/* 701 */     fmt.add("version", this.version);
/* 702 */     fmt.add("authInfoType", this.authInfoType);
/* 703 */     fmt.add("authInfoConnName", this.authInfoConnName);
/* 704 */     fmt.add("ldapUserName", this.ldapUserName);
/* 705 */     fmt.add("ldapPassword", JmqiTools.tracePassword(this.ldapPassword));
/* 706 */     fmt.add("ocspResponderURL", this.ocspResponderURL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public String getAuthInfoConname() {
/* 715 */     String traceRet1 = getAuthInfoConnName();
/* 716 */     if (Trace.isOn) {
/* 717 */       Trace.data(this, "com.ibm.mq.jmqi.MQAIR", "getAuthInfoConname()", "getter", traceRet1);
/*     */     }
/* 719 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setAuthInfoConname(String authInfoConname) {
/* 728 */     if (Trace.isOn) {
/* 729 */       Trace.data(this, "com.ibm.mq.jmqi.MQAIR", "setAuthInfoConname(String)", "setter", authInfoConname);
/*     */     }
/*     */     
/* 732 */     setAuthInfoConnName(authInfoConname);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public String getOcspResponderUrl() {
/* 741 */     String traceRet1 = getOcspResponderURL();
/* 742 */     if (Trace.isOn) {
/* 743 */       Trace.data(this, "com.ibm.mq.jmqi.MQAIR", "getOcspResponderUrl()", "getter", traceRet1);
/*     */     }
/* 745 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setOcspResponderUrl(String ocspResponderUrl) {
/* 754 */     if (Trace.isOn) {
/* 755 */       Trace.data(this, "com.ibm.mq.jmqi.MQAIR", "setOcspResponderUrl(String)", "setter", ocspResponderUrl);
/*     */     }
/*     */     
/* 758 */     setOcspResponderURL(ocspResponderUrl);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQAIR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */