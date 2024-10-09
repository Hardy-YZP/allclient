/*      */ package com.ibm.mq.jmqi;
/*      */ 
/*      */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*      */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*      */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*      */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*      */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*      */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*      */ import com.ibm.mq.jmqi.system.JmqiTls;
/*      */ import com.ibm.msg.client.commonservices.trace.Trace;
/*      */ import java.util.Arrays;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MQSCO
/*      */   extends AbstractMqiStructure
/*      */   implements Cloneable
/*      */ {
/*      */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQSCO.java";
/*      */   private static final int SIZEOF_STRUC_ID = 4;
/*      */   private static final int SIZEOF_VERSION = 4;
/*      */   private static final int SIZEOF_KEY_REPOSITORY = 256;
/*      */   private static final int SIZEOF_CRYPTO_HARDWARE = 256;
/*      */   private static final int SIZEOF_AUTH_INFO_REC_COUNT = 4;
/*      */   private static final int SIZEOF_AUTH_INFO_REC_OFFSET = 4;
/*      */   private static final int SIZEOF_KEY_RESET_COUNT = 4;
/*      */   private static final int SIZEOF_FIPS_REQUIRED = 4;
/*      */   private static final int SIZEOF_ENCRYPTION_POLICY_SUITEB = 16;
/*      */   private static final int SIZEOF_CERTIFICATE_VAL_POLICY = 4;
/*      */   private static final int SIZEOF_CERTIFICATE_LABEL = 64;
/*      */   private static final int SIZEOF_KEY_REPO_PASSWORD_OFFSET = 4;
/*      */   private static final int SIZEOF_KEY_REPO_PASSWORD_LENGTH = 4;
/*      */   
/*      */   static {
/*   74 */     if (Trace.isOn) {
/*   75 */       Trace.data("com.ibm.mq.jmqi.MQSCO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQSCO.java");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  105 */   private int version = 1;
/*  106 */   private String keyRepository = "";
/*  107 */   private String cryptoHardware = "";
/*  108 */   private MQAIR[] authInfoRecords = null;
/*  109 */   private int keyResetCount = 0;
/*  110 */   private int fipsRequired = 0;
/*  111 */   private int[] encryptionPolicySuiteB = new int[] { 1, 0, 0, 0 };
/*  112 */   private int certificateValPolicy = 0;
/*  113 */   private String certificateLabel = "";
/*  114 */   private String keyRepoPassword = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] keyRepoPassword_cachedBytes;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected MQSCO(JmqiEnvironment env) {
/*  127 */     super(env);
/*  128 */     if (Trace.isOn) {
/*  129 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSCO", "<init>(JmqiEnvironment)", new Object[] { env });
/*      */     }
/*      */     
/*  132 */     if (Trace.isOn) {
/*  133 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSCO", "<init>(JmqiEnvironment)");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurrentVersion() {
/*  143 */     if (Trace.isOn) {
/*  144 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "getCurrentVersion()", "getter", 
/*  145 */           Integer.valueOf(6));
/*      */     }
/*  147 */     return 6;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV1(JmqiEnvironment env, int ptrSize) {
/*  158 */     int size = 0;
/*  159 */     size += 4;
/*  160 */     size += 4;
/*  161 */     size += 256;
/*  162 */     size += 256;
/*  163 */     size += 4;
/*  164 */     size += 4;
/*  165 */     size += ptrSize;
/*      */     
/*  167 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV1(JmqiEnvironment env, int ptrSize) {
/*  178 */     int size = getFieldSizeV1(env, ptrSize);
/*      */     
/*  180 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV2(JmqiEnvironment env, int ptrSize) {
/*  191 */     int size = getFieldSizeV1(env, ptrSize);
/*  192 */     size += 4;
/*  193 */     size += 4;
/*      */     
/*  195 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV2(JmqiEnvironment env, int ptrSize) {
/*  206 */     int size = getFieldSizeV2(env, ptrSize);
/*  207 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*      */     
/*  209 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV3(JmqiEnvironment env, int ptrSize) {
/*  220 */     int size = getFieldSizeV2(env, ptrSize);
/*  221 */     size += 16;
/*      */     
/*  223 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV3(JmqiEnvironment env, int ptrSize) {
/*  234 */     int size = getFieldSizeV3(env, ptrSize);
/*  235 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*      */     
/*  237 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV4(JmqiEnvironment env, int ptrSize) {
/*  248 */     int size = getFieldSizeV3(env, ptrSize);
/*  249 */     size += 4;
/*      */     
/*  251 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV4(JmqiEnvironment env, int ptrSize) {
/*  262 */     int size = getFieldSizeV4(env, ptrSize);
/*  263 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*      */     
/*  265 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV5(JmqiEnvironment env, int ptrSize) {
/*  276 */     int size = getFieldSizeV4(env, ptrSize);
/*  277 */     size += 64;
/*      */     
/*  279 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV5(JmqiEnvironment env, int ptrSize) {
/*  290 */     int size = getFieldSizeV5(env, ptrSize);
/*  291 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*      */     
/*  293 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getFieldSizeV6(JmqiEnvironment env, int ptrSize) {
/*  306 */     int size = getFieldSizeV5(env, ptrSize);
/*  307 */     size += ptrSize;
/*  308 */     size += 4;
/*  309 */     size += 4;
/*      */     
/*  311 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSizeV6(JmqiEnvironment env, int ptrSize) {
/*  322 */     int size = getFieldSizeV6(env, ptrSize);
/*  323 */     size += JmqiTools.alignToGrain(ptrSize, size);
/*      */     
/*  325 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSize(int ptrSize) throws JmqiException {
/*  336 */     if (Trace.isOn)
/*  337 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSCO", "getSize(int)", new Object[] {
/*  338 */             Integer.valueOf(ptrSize)
/*      */           }); 
/*  340 */     int traceRet1 = getSize(this.env, this.version, ptrSize);
/*  341 */     if (Trace.isOn) {
/*  342 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSCO", "getSize(int)", Integer.valueOf(traceRet1));
/*      */     }
/*  344 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/*      */     int size;
/*  360 */     switch (version) {
/*      */       case 1:
/*  362 */         size = getSizeV1(env, ptrSize);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  391 */         return size;case 2: size = getSizeV2(env, ptrSize); return size;case 3: size = getSizeV3(env, ptrSize); return size;case 4: size = getSizeV4(env, ptrSize); return size;case 5: size = getSizeV5(env, ptrSize); return size;case 6: size = getSizeV6(env, ptrSize); return size;
/*      */     } 
/*      */     JmqiException e = new JmqiException(env, -1, null, 2, 6128, null);
/*      */     throw e;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/*  402 */     if (Trace.isOn) {
/*  403 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSCO", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/*  404 */             Integer.valueOf(ptrSize), cp
/*      */           });
/*      */     }
/*  407 */     int authInfoRecordsSize = 0;
/*      */     
/*  409 */     if (this.authInfoRecords != null) {
/*  410 */       for (MQAIR authInfoRecord : this.authInfoRecords) {
/*  411 */         if (authInfoRecord == null) {
/*      */           
/*  413 */           JmqiException traceRet2 = new JmqiException(this.env, -1, null, 2, 2385, null);
/*  414 */           if (Trace.isOn) {
/*  415 */             Trace.throwing(this, "com.ibm.mq.jmqi.MQSCO", "getRequiredBufferSize(int,JmqiCodepage)", traceRet2);
/*      */           }
/*      */           
/*  418 */           throw traceRet2;
/*      */         } 
/*      */ 
/*      */         
/*  422 */         authInfoRecordsSize += authInfoRecord.getRequiredBufferSize(ptrSize, cp);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  427 */     int size = getSize(this.env, this.version, ptrSize);
/*  428 */     size += authInfoRecordsSize;
/*  429 */     if (Trace.isOn) {
/*  430 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSCO", "getRequiredBufferSize(int,JmqiCodepage)", 
/*  431 */           Integer.valueOf(size));
/*      */     }
/*      */ 
/*      */     
/*  435 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*  436 */     this.keyRepoPassword_cachedBytes = dc.getStrBytes(this.keyRepoPassword, cp);
/*  437 */     size += this.keyRepoPassword_cachedBytes.length;
/*      */     
/*  439 */     return size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/*  450 */     if (Trace.isOn) {
/*  451 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSCO", "clone()");
/*      */     }
/*      */     
/*  454 */     Object newObject = super.clone();
/*  455 */     MQSCO newMQSCO = (MQSCO)newObject;
/*      */ 
/*      */     
/*  458 */     newMQSCO.setVersion(this.version);
/*  459 */     newMQSCO.setKeyRepository(this.keyRepository);
/*  460 */     newMQSCO.setCryptoHardware(this.cryptoHardware);
/*  461 */     newMQSCO.setKeyResetCount(this.keyResetCount);
/*  462 */     newMQSCO.setFipsRequired(this.fipsRequired);
/*  463 */     newMQSCO.setEncryptionPolicySuiteB(this.encryptionPolicySuiteB);
/*  464 */     newMQSCO.setCertificateValPolicy(this.certificateValPolicy);
/*      */ 
/*      */     
/*  467 */     if (this.authInfoRecords != null) {
/*  468 */       MQAIR[] newMQAIRs = new MQAIR[this.authInfoRecords.length];
/*  469 */       for (int i = 0; i < this.authInfoRecords.length; i++) {
/*  470 */         newMQAIRs[i] = (MQAIR)this.authInfoRecords[i].clone();
/*      */       }
/*      */       
/*  473 */       newMQSCO.setAuthInfoRecords(newMQAIRs);
/*      */     } 
/*      */     
/*  476 */     newMQSCO.setKeyRepoPassword(this.keyRepoPassword);
/*      */     
/*  478 */     if (Trace.isOn) {
/*  479 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSCO", "clone()", newObject);
/*      */     }
/*  481 */     return newObject;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  491 */     if (Trace.isOn) {
/*  492 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSCO", "hashCode()");
/*      */     }
/*  494 */     int hashCode = 0;
/*  495 */     hashCode += 31 * this.version;
/*  496 */     hashCode += 37 * ((this.keyRepository != null) ? this.keyRepository.hashCode() : 0);
/*  497 */     hashCode += 41 * ((this.cryptoHardware != null) ? this.cryptoHardware.hashCode() : 0);
/*  498 */     hashCode += 43 * getAuthInfoRecCount();
/*      */ 
/*      */     
/*  501 */     if (this.authInfoRecords != null) {
/*  502 */       for (int i = 0; i < getAuthInfoRecCount(); i++) {
/*  503 */         hashCode += 53 * this.authInfoRecords[i].hashCode();
/*      */       }
/*      */     }
/*      */     
/*  507 */     hashCode += 59 * this.keyResetCount;
/*  508 */     hashCode += 61 * this.fipsRequired;
/*      */     
/*  510 */     hashCode += 71 * this.certificateValPolicy;
/*  511 */     hashCode += 73 * ((this.certificateLabel != null) ? this.certificateLabel.hashCode() : 0);
/*  512 */     hashCode += 83 * ((this.keyRepoPassword != null) ? this.keyRepoPassword.hashCode() : 0);
/*      */     
/*  514 */     if (Trace.isOn) {
/*  515 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSCO", "hashCode()", Integer.valueOf(hashCode));
/*      */     }
/*  517 */     return hashCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object object) {
/*  528 */     if (Trace.isOn) {
/*  529 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSCO", "equals(Object)", new Object[] { object });
/*      */     }
/*  531 */     if (object == null) {
/*  532 */       if (Trace.isOn) {
/*  533 */         Trace.exit(this, "com.ibm.mq.jmqi.MQSCO", "equals(Object)", Boolean.valueOf(false), 1);
/*      */       }
/*  535 */       return false;
/*      */     } 
/*  537 */     if (!(object instanceof MQSCO)) {
/*  538 */       if (Trace.isOn) {
/*  539 */         Trace.exit(this, "com.ibm.mq.jmqi.MQSCO", "equals(Object)", Boolean.valueOf(false), 2);
/*      */       }
/*  541 */       return false;
/*      */     } 
/*  543 */     MQSCO mqsco = (MQSCO)object;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  556 */     boolean areEqual = (this.version == mqsco.getVersion() && objEquals(this.keyRepository, mqsco.getKeyRepository()) && objEquals(this.cryptoHardware, mqsco.getCryptoHardware()) && getAuthInfoRecCount() == mqsco.getAuthInfoRecCount() && this.keyResetCount == mqsco.getKeyResetCount() && this.fipsRequired == mqsco.getFipsRequired() && Arrays.equals(this.encryptionPolicySuiteB, mqsco.getEncryptionPolicySuiteB()) && this.certificateValPolicy == mqsco.getCertificateValPolicy() && objEquals(this.certificateLabel, mqsco.getCertificateLabel()));
/*      */     
/*  558 */     if (areEqual) {
/*  559 */       if (this.authInfoRecords != null) {
/*  560 */         MQAIR[] csp2records = mqsco.getAuthInfoRecords();
/*      */         
/*  562 */         if (csp2records != null) {
/*  563 */           areEqual = (areEqual && this.authInfoRecords.length == csp2records.length);
/*      */           
/*  565 */           for (int i = 0; areEqual && i < getAuthInfoRecCount(); i++) {
/*  566 */             if (this.authInfoRecords[i] != null) {
/*  567 */               areEqual = (areEqual && this.authInfoRecords[i].equals(csp2records[i]));
/*      */             }
/*      */           } 
/*      */         } else {
/*      */           
/*  572 */           areEqual = false;
/*      */         } 
/*      */       } 
/*      */       
/*  576 */       if (this.keyRepoPassword == null) {
/*  577 */         areEqual = (areEqual && mqsco.getKeyRepoPassword() == null);
/*      */       } else {
/*      */         
/*  580 */         areEqual = (areEqual && this.keyRepoPassword.equals(mqsco.getKeyRepoPassword()));
/*      */       } 
/*      */     } 
/*      */     
/*  584 */     if (Trace.isOn) {
/*  585 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSCO", "equals(Object)", Boolean.valueOf(areEqual), 3);
/*      */     }
/*  587 */     return areEqual;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean objEquals(Object o1, Object o2) {
/*  597 */     if (Trace.isOn) {
/*  598 */       Trace.entry("com.ibm.mq.jmqi.MQSCO", "objEquals(Object,Object)", new Object[] { o1, o2 });
/*      */     }
/*  600 */     if (o1 == null && o2 == null) {
/*  601 */       if (Trace.isOn) {
/*  602 */         Trace.exit("com.ibm.mq.jmqi.MQSCO", "objEquals(Object,Object)", Boolean.valueOf(true), 1);
/*      */       }
/*  604 */       return true;
/*      */     } 
/*  606 */     if (o1 == null || o2 == null) {
/*  607 */       if (Trace.isOn) {
/*  608 */         Trace.exit("com.ibm.mq.jmqi.MQSCO", "objEquals(Object,Object)", Boolean.valueOf(false), 2);
/*      */       }
/*      */       
/*  611 */       return false;
/*      */     } 
/*  613 */     boolean traceRet1 = o1.equals(o2);
/*  614 */     if (Trace.isOn) {
/*  615 */       Trace.exit("com.ibm.mq.jmqi.MQSCO", "objEquals(Object,Object)", Boolean.valueOf(traceRet1), 3);
/*      */     }
/*      */     
/*  618 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MQAIR[] getAuthInfoRecords() {
/*  625 */     if (Trace.isOn) {
/*  626 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "getAuthInfoRecords()", "getter", this.authInfoRecords);
/*      */     }
/*      */     
/*  629 */     return this.authInfoRecords;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAuthInfoRecords(MQAIR[] authoInfoRecords) {
/*  636 */     if (Trace.isOn) {
/*  637 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "setAuthInfoRecords(MQAIR [ ])", "setter", authoInfoRecords);
/*      */     }
/*      */     
/*  640 */     this.authInfoRecords = authoInfoRecords;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVersion() {
/*  648 */     if (Trace.isOn) {
/*  649 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "getVersion()", "getter", Integer.valueOf(this.version));
/*      */     }
/*      */     
/*  652 */     return this.version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVersion(int version) {
/*  660 */     if (Trace.isOn) {
/*  661 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "setVersion(int)", "setter", 
/*  662 */           Integer.valueOf(version));
/*      */     }
/*  664 */     this.version = version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getKeyRepository() {
/*  671 */     if (Trace.isOn) {
/*  672 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "getKeyRepository()", "getter", this.keyRepository);
/*      */     }
/*  674 */     return this.keyRepository;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setKeyRepository(String keyRepository) {
/*  681 */     if (Trace.isOn) {
/*  682 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "setKeyRepository(String)", "setter", keyRepository);
/*      */     }
/*      */     
/*  685 */     this.keyRepository = keyRepository;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCryptoHardware() {
/*  692 */     if (Trace.isOn) {
/*  693 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "getCryptoHardware()", "getter", this.cryptoHardware);
/*      */     }
/*  695 */     return this.cryptoHardware;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCryptoHardware(String cryptoHardware) {
/*  702 */     if (Trace.isOn) {
/*  703 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "setCryptoHardware(String)", "setter", cryptoHardware);
/*      */     }
/*      */     
/*  706 */     this.cryptoHardware = cryptoHardware;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAuthInfoRecCount() {
/*  713 */     int retval = (this.authInfoRecords != null) ? this.authInfoRecords.length : 0;
/*  714 */     if (Trace.isOn) {
/*  715 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "getAuthInfoRecCount()", "getter", 
/*  716 */           Integer.valueOf(retval));
/*      */     }
/*  718 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getKeyResetCount() {
/*  725 */     if (Trace.isOn) {
/*  726 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "getKeyResetCount()", "getter", 
/*  727 */           Integer.valueOf(this.keyResetCount));
/*      */     }
/*  729 */     return this.keyResetCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setKeyResetCount(int keyResetCount) {
/*  736 */     if (Trace.isOn) {
/*  737 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "setKeyResetCount(int)", "setter", 
/*  738 */           Integer.valueOf(keyResetCount));
/*      */     }
/*  740 */     this.keyResetCount = keyResetCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFipsRequired() {
/*  747 */     if (Trace.isOn) {
/*  748 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "getFipsRequired()", "getter", 
/*  749 */           Integer.valueOf(this.fipsRequired));
/*      */     }
/*  751 */     return this.fipsRequired;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFipsRequired(int fipsRequired) {
/*  758 */     if (Trace.isOn) {
/*  759 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "setFipsRequired(int)", "setter", 
/*  760 */           Integer.valueOf(fipsRequired));
/*      */     }
/*  762 */     this.fipsRequired = fipsRequired;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int[] getEncryptionPolicySuiteB() {
/*  769 */     if (Trace.isOn) {
/*  770 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "getEncryptionPolicySuiteB()", "getter", this.encryptionPolicySuiteB);
/*      */     }
/*      */     
/*  773 */     return this.encryptionPolicySuiteB;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEncryptionPolicySuiteB(int[] encryptionPolicySuiteB) {
/*  780 */     if (Trace.isOn) {
/*  781 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "setEncryptionPolicySuiteB(int [ ])", "setter", encryptionPolicySuiteB);
/*      */     }
/*      */     
/*  784 */     this.encryptionPolicySuiteB = encryptionPolicySuiteB;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCertificateValPolicy() {
/*  791 */     if (Trace.isOn) {
/*  792 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "getCertificateValPolicy()", "getter", 
/*  793 */           Integer.valueOf(this.certificateValPolicy));
/*      */     }
/*  795 */     return this.certificateValPolicy;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCertificateValPolicy(int certificateValPolicy) {
/*  802 */     if (Trace.isOn) {
/*  803 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "setCertificateValPolicy(int)", "setter", 
/*  804 */           Integer.valueOf(certificateValPolicy));
/*      */     }
/*  806 */     this.certificateValPolicy = certificateValPolicy;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCertificateLabel() {
/*  813 */     if (Trace.isOn) {
/*  814 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "getCertificateLabel()", "getter", this.certificateLabel);
/*      */     }
/*      */     
/*  817 */     return this.certificateLabel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCertificateLabel(String certificateLabel) {
/*  824 */     if (Trace.isOn) {
/*  825 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "setCertificateLabel(String)", "setter", certificateLabel);
/*      */     }
/*      */     
/*  828 */     this.certificateLabel = certificateLabel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getKeyRepoPassword() {
/*  836 */     if (Trace.isOn) {
/*  837 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "getKeyRepoPassword()", "getter", (this.keyRepoPassword == null) ? null : "********");
/*      */     }
/*      */     
/*  840 */     return this.keyRepoPassword;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setKeyRepoPassword(String keyRepoPassword) {
/*  848 */     if (Trace.isOn) {
/*  849 */       Trace.data(this, "com.ibm.mq.jmqi.MQSCO", "setKeyRepoPassword(String)", "setter", (keyRepoPassword == null) ? null : "********");
/*      */     }
/*      */     
/*  852 */     this.keyRepoPassword = keyRepoPassword;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  860 */     if (Trace.isOn) {
/*  861 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSCO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/*  863 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*      */     }
/*  865 */     int traceRet1 = writeToBuffer(buffer, offset, false, ptrSize, swap, cp, tls);
/*      */     
/*  867 */     if (Trace.isOn) {
/*  868 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSCO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/*  869 */           Integer.valueOf(traceRet1));
/*      */     }
/*  871 */     return traceRet1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int writeToBuffer(byte[] buffer, int offset, boolean obscure, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/*  886 */     if (Trace.isOn) {
/*  887 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSCO", "writeToBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/*  889 */             Integer.valueOf(offset), Boolean.valueOf(obscure), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*      */     }
/*      */     
/*  892 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*  893 */     int pos = offset;
/*      */     
/*  895 */     int keyRepoPasswordLengthPosition = -1;
/*  896 */     int keyRepoPasswordOffsetPosition = -1;
/*      */ 
/*      */     
/*  899 */     dc.writeMQField("SCO ", buffer, pos, 4, cp, tls);
/*  900 */     pos += 4;
/*      */ 
/*      */     
/*  903 */     dc.writeI32(this.version, buffer, pos, swap);
/*  904 */     pos += 4;
/*      */ 
/*      */     
/*  907 */     dc.writeField(this.keyRepository, buffer, pos, 256, cp, tls);
/*  908 */     pos += 256;
/*      */ 
/*      */     
/*  911 */     dc.writeField(this.cryptoHardware, buffer, pos, 256, cp, tls);
/*  912 */     pos += 256;
/*      */ 
/*      */     
/*  915 */     dc.writeI32(getAuthInfoRecCount(), buffer, pos, swap);
/*  916 */     pos += 4;
/*      */ 
/*      */     
/*  919 */     if (getAuthInfoRecCount() == 0) {
/*  920 */       dc.clear(buffer, pos, 4);
/*      */     } else {
/*      */       
/*  923 */       dc.writeI32(getSize(this.env, this.version, ptrSize), buffer, pos, swap);
/*      */     } 
/*  925 */     pos += 4;
/*      */ 
/*      */     
/*  928 */     dc.clear(buffer, pos, ptrSize);
/*  929 */     pos += ptrSize;
/*      */     
/*  931 */     if (this.version >= 2) {
/*      */       
/*  933 */       dc.writeI32(this.keyResetCount, buffer, pos, swap);
/*  934 */       pos += 4;
/*      */ 
/*      */       
/*  937 */       dc.writeI32(this.fipsRequired, buffer, pos, swap);
/*  938 */       pos += 4;
/*      */ 
/*      */       
/*  941 */       if (this.version == 2) {
/*  942 */         int padding = JmqiTools.alignToGrain(ptrSize, pos);
/*  943 */         dc.clear(buffer, pos, padding);
/*  944 */         pos += padding;
/*      */       } 
/*      */     } 
/*      */     
/*  948 */     if (this.version >= 3) {
/*      */       
/*  950 */       for (int i = 0; i < 4; i++) {
/*  951 */         dc.writeI32(this.encryptionPolicySuiteB[i], buffer, pos + 4 * i, swap);
/*      */       }
/*  953 */       pos += 16;
/*      */ 
/*      */       
/*  956 */       if (this.version == 3) {
/*  957 */         int padding = JmqiTools.alignToGrain(ptrSize, pos);
/*  958 */         dc.clear(buffer, pos, padding);
/*  959 */         pos += padding;
/*      */       } 
/*      */     } 
/*      */     
/*  963 */     if (this.version >= 4) {
/*      */       
/*  965 */       dc.writeI32(this.certificateValPolicy, buffer, pos, swap);
/*  966 */       pos += 4;
/*      */ 
/*      */       
/*  969 */       if (this.version == 4) {
/*  970 */         int padding = JmqiTools.alignToGrain(ptrSize, pos);
/*  971 */         dc.clear(buffer, pos, padding);
/*  972 */         pos += padding;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  978 */     if (this.authInfoRecords != null) {
/*  979 */       for (MQAIR authInfoRecord : this.authInfoRecords) {
/*  980 */         if (authInfoRecord == null) {
/*      */           
/*  982 */           JmqiException e = new JmqiException(this.env, -1, null, 2, 2385, null);
/*  983 */           if (Trace.isOn) {
/*  984 */             Trace.throwing(this, "com.ibm.mq.jmqi.MQSCO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", e);
/*      */           }
/*      */           
/*  987 */           throw e;
/*      */         } 
/*      */         
/*  990 */         pos = authInfoRecord.writeToBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*      */       } 
/*      */     }
/*  993 */     if (this.version >= 5) {
/*      */       
/*  995 */       dc.writeField(this.certificateLabel, buffer, pos, 64, cp, tls);
/*  996 */       pos += 64;
/*      */ 
/*      */       
/*  999 */       if (this.version == 5) {
/* 1000 */         int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1001 */         dc.clear(buffer, pos, padding);
/* 1002 */         pos += padding;
/*      */       } 
/*      */     } 
/*      */     
/* 1006 */     if (this.version >= 6) {
/*      */       
/* 1008 */       dc.clear(buffer, pos, ptrSize);
/* 1009 */       pos += ptrSize;
/*      */       
/* 1011 */       keyRepoPasswordOffsetPosition = pos;
/* 1012 */       pos += 4;
/*      */       
/* 1014 */       keyRepoPasswordLengthPosition = pos;
/* 1015 */       pos += 4;
/*      */       
/* 1017 */       if (this.version == 6) {
/* 1018 */         int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1019 */         dc.clear(buffer, pos, padding);
/* 1020 */         pos += padding;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1026 */     if (this.version >= 6)
/*      */     {
/* 1028 */       if (keyRepoPasswordOffsetPosition > 0) {
/*      */         byte[] keyRepoPasswordBytes;
/*      */         
/* 1031 */         if (this.keyRepoPassword_cachedBytes != null) {
/* 1032 */           keyRepoPasswordBytes = this.keyRepoPassword_cachedBytes;
/* 1033 */           this.keyRepoPassword_cachedBytes = null;
/*      */         } else {
/*      */           
/* 1036 */           keyRepoPasswordBytes = dc.getStrBytes(this.keyRepoPassword, cp);
/*      */         } 
/*      */         
/* 1039 */         dc.writeI32(pos - offset, buffer, keyRepoPasswordOffsetPosition, swap);
/* 1040 */         dc.writeI32(keyRepoPasswordBytes.length, buffer, keyRepoPasswordLengthPosition, swap);
/*      */         
/* 1042 */         if (obscure) {
/* 1043 */           for (int i = 0; i < keyRepoPasswordBytes.length; i++) {
/* 1044 */             buffer[pos + i] = 120;
/*      */           }
/*      */         } else {
/*      */           
/* 1048 */           System.arraycopy(keyRepoPasswordBytes, 0, buffer, pos, keyRepoPasswordBytes.length);
/*      */         } 
/* 1050 */         pos += keyRepoPasswordBytes.length;
/*      */       } 
/*      */     }
/*      */     
/* 1054 */     if (Trace.isOn) {
/* 1055 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSCO", "writeToBuffer(byte [ ],int,boolean,int,boolean,JmqiCodepage,JmqiTls)", 
/* 1056 */           Integer.valueOf(pos));
/*      */     }
/* 1058 */     return pos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 1066 */     if (Trace.isOn) {
/* 1067 */       Trace.entry(this, "com.ibm.mq.jmqi.MQSCO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*      */             
/* 1069 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*      */     }
/*      */     
/* 1072 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 1073 */     int pos = offset;
/*      */ 
/*      */ 
/*      */     
/* 1077 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 1078 */     if (!strucId.equals("SCO ")) {
/*      */       
/* 1080 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 6107, null);
/* 1081 */       if (Trace.isOn) {
/* 1082 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQSCO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*      */       }
/*      */       
/* 1085 */       throw traceRet1;
/*      */     } 
/* 1087 */     pos += 4;
/*      */ 
/*      */     
/* 1090 */     this.version = dc.readI32(buffer, pos, swap);
/* 1091 */     pos += 4;
/*      */ 
/*      */     
/* 1094 */     this.keyRepository = dc.readField(buffer, pos, 256, cp, tls);
/* 1095 */     pos += 256;
/*      */ 
/*      */     
/* 1098 */     this.cryptoHardware = dc.readField(buffer, pos, 256, cp, tls);
/* 1099 */     pos += 256;
/*      */ 
/*      */     
/* 1102 */     int authInfoRecCount = dc.readI32(buffer, pos, swap);
/* 1103 */     pos += 4;
/*      */ 
/*      */     
/* 1106 */     int authInfoRecOffset = dc.readI32(buffer, pos, swap);
/* 1107 */     pos += 4;
/*      */ 
/*      */     
/* 1110 */     pos += ptrSize;
/*      */     
/* 1112 */     if (this.version >= 2) {
/*      */       
/* 1114 */       this.keyResetCount = dc.readI32(buffer, pos, swap);
/* 1115 */       pos += 4;
/*      */ 
/*      */       
/* 1118 */       this.fipsRequired = dc.readI32(buffer, pos, swap);
/* 1119 */       pos += 4;
/*      */ 
/*      */       
/* 1122 */       if (this.version == 2) {
/* 1123 */         int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1124 */         pos += padding;
/*      */       } 
/*      */     } 
/*      */     
/* 1128 */     if (this.version >= 3) {
/*      */       
/* 1130 */       for (int i = 0; i < 4; i++) {
/* 1131 */         this.encryptionPolicySuiteB[i] = dc.readI32(buffer, pos + 4 * i, swap);
/*      */       }
/* 1133 */       pos += 16;
/*      */ 
/*      */       
/* 1136 */       if (this.version == 3) {
/* 1137 */         int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1138 */         pos += padding;
/*      */       } 
/*      */     } 
/*      */     
/* 1142 */     if (this.version >= 4) {
/*      */       
/* 1144 */       this.certificateValPolicy = dc.readI32(buffer, pos, swap);
/* 1145 */       pos += 4;
/*      */ 
/*      */       
/* 1148 */       if (this.version == 4) {
/* 1149 */         int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1150 */         pos += padding;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1158 */     if (authInfoRecCount > 0) {
/* 1159 */       this.authInfoRecords = new MQAIR[authInfoRecCount];
/* 1160 */       pos = authInfoRecOffset;
/* 1161 */       for (int i = 0; i < authInfoRecCount; i++) {
/* 1162 */         this.authInfoRecords[i] = this.env.newMQAIR();
/* 1163 */         pos = this.authInfoRecords[i].readFromBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*      */       } 
/*      */     } else {
/*      */       
/* 1167 */       this.authInfoRecords = null;
/*      */     } 
/* 1169 */     if (this.version >= 5) {
/*      */       
/* 1171 */       this.certificateLabel = dc.readField(buffer, pos, 64, cp, tls);
/* 1172 */       pos += 64;
/*      */ 
/*      */       
/* 1175 */       if (this.version == 5) {
/* 1176 */         int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1177 */         pos += padding;
/*      */       } 
/*      */     } 
/*      */     
/* 1181 */     int keyRepoOffset = -1;
/* 1182 */     int keyRepoLength = -1;
/*      */     
/* 1184 */     if (this.version >= 6) {
/*      */       
/* 1186 */       pos += ptrSize;
/*      */ 
/*      */       
/* 1189 */       keyRepoOffset = dc.readI32(buffer, pos, swap);
/* 1190 */       pos += 4;
/*      */ 
/*      */       
/* 1193 */       keyRepoLength = dc.readI32(buffer, pos, swap);
/* 1194 */       pos += 4;
/*      */ 
/*      */       
/* 1197 */       if (this.version == 6) {
/* 1198 */         int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 1199 */         pos += padding;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1206 */     if (this.version >= 6)
/*      */     {
/* 1208 */       if (keyRepoOffset > 0) {
/* 1209 */         this.keyRepoPassword = dc.readField(buffer, keyRepoOffset, keyRepoLength, cp, tls);
/*      */       } else {
/*      */         
/* 1212 */         this.keyRepoPassword = null;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1217 */     int keyRepoEnd = keyRepoOffset + keyRepoLength;
/*      */     
/* 1219 */     int traceRet2 = Math.max(pos, keyRepoEnd);
/* 1220 */     if (Trace.isOn) {
/* 1221 */       Trace.exit(this, "com.ibm.mq.jmqi.MQSCO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 1222 */           Integer.valueOf(traceRet2));
/*      */     }
/* 1224 */     return traceRet2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addFieldsToFormatter(JmqiStructureFormatter fmt) {
/* 1236 */     fmt.add("version", this.version);
/* 1237 */     fmt.add("keyRepository", this.keyRepository);
/* 1238 */     fmt.add("cryptoHardware", this.cryptoHardware);
/* 1239 */     fmt.add("authInfoRecCount", getAuthInfoRecCount());
/* 1240 */     fmt.add("keyResetCount", this.keyResetCount);
/* 1241 */     fmt.add("fipsRequired", this.fipsRequired);
/* 1242 */     fmt.add("encryptionPolicySuiteB", this.encryptionPolicySuiteB);
/* 1243 */     fmt.add("certificateValPolicy", this.certificateValPolicy);
/* 1244 */     fmt.add("certificateLabel", this.certificateLabel);
/* 1245 */     fmt.add("keyRepoPassword", JmqiTools.tracePassword(this.keyRepoPassword));
/*      */   }
/*      */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQSCO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */