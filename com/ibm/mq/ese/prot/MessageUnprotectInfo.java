/*     */ package com.ibm.mq.ese.prot;
/*     */ 
/*     */ import com.ibm.mq.ese.core.MessageProtectionConstants;
/*     */ import com.ibm.mq.ese.nls.AmsErrorMessages;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MessageUnprotectInfo
/*     */ {
/*     */   public static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/prot/MessageUnprotectInfo.java";
/*     */   
/*     */   static {
/*  40 */     if (Trace.isOn) {
/*  41 */       Trace.data("com.ibm.mq.ese.prot.MessageUnprotectInfo", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.ese/src/com/ibm/mq/ese/prot/MessageUnprotectInfo.java");
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
/*  54 */   private String senderDN = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   private byte[] unprotectedData = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int protType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Date signDate;
/*     */ 
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
/*     */   
/*     */   public MessageUnprotectInfo(String senderDN, byte[] unprotectData, int protType, Date signDate, String signAlg, String encAlg) throws MessageProtectionException {
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.entry(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "<init>(String,byte [ ],int,Date,String,String)", new Object[] { senderDN, unprotectData, 
/*     */             
/*  92 */             Integer.valueOf(protType), signDate, signAlg, encAlg });
/*     */     }
/*  94 */     if (senderDN == null || senderDN.length() == 0) {
/*  95 */       HashMap<String, Object> inserts = new HashMap<>();
/*  96 */       inserts.put("AMS_INSERT_CERTIFICATE_SUBJECT", "");
/*  97 */       MessageProtectionException ex = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_msg_sender_cert_not_valid, inserts);
/*  98 */       ex.initCause(new IllegalArgumentException("senderDN == null"));
/*  99 */       if (Trace.isOn) {
/* 100 */         Trace.throwing(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "<init>(String,byte [ ],int,Date,String,String)", (Throwable)ex, 1);
/*     */       }
/*     */       
/* 103 */       throw ex;
/*     */     } 
/* 105 */     this.senderDN = senderDN;
/*     */     
/* 107 */     if (unprotectData == null) {
/* 108 */       MessageProtectionException ex = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_msg_unprotection_failed, new IllegalArgumentException("unprotectData == null"));
/*     */ 
/*     */       
/* 111 */       if (Trace.isOn) {
/* 112 */         Trace.throwing(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "<init>(String,byte [ ],int,Date,String,String)", (Throwable)ex, 2);
/*     */       }
/*     */       
/* 115 */       throw ex;
/*     */     } 
/* 117 */     this.unprotectedData = unprotectData;
/*     */     
/* 119 */     if (protType != 2 && protType != 1) {
/*     */       
/* 121 */       String protName = Integer.toString(protType);
/* 122 */       if (protType < MessageProtectionConstants.QOP_NAMES.length) {
/* 123 */         protName = MessageProtectionConstants.QOP_NAMES[protType];
/*     */       }
/* 125 */       HashMap<String, Object> inserts = new HashMap<>();
/* 126 */       inserts.put("AMS_INSERT_QUALITY_OF_PROTECTION", protName);
/* 127 */       MessageProtectionException ex = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_invalid_quality_of_protection, inserts, new IllegalArgumentException("protType"));
/*     */ 
/*     */       
/* 130 */       if (Trace.isOn) {
/* 131 */         Trace.throwing(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "<init>(String,byte [ ],int,Date,String,String)", (Throwable)ex, 3);
/*     */       }
/*     */       
/* 134 */       throw ex;
/*     */     } 
/* 136 */     this.protType = protType;
/* 137 */     this.signDate = signDate;
/* 138 */     this.signAlg = signAlg;
/* 139 */     this.encAlg = encAlg;
/* 140 */     if (Trace.isOn) {
/* 141 */       Trace.exit(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "<init>(String,byte [ ],int,Date,String,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MessageUnprotectInfo(byte[] unprotectData, int protType, Date signDate, String encAlg) throws MessageProtectionException {
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.entry(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "<init>(byte [ ],int,Date,String)", new Object[] { unprotectData, 
/* 150 */             Integer.valueOf(protType), signDate, encAlg });
/*     */     }
/*     */     
/* 153 */     if (unprotectData == null) {
/* 154 */       MessageProtectionException ex = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_msg_unprotection_failed, new IllegalArgumentException("unprotectData == null"));
/*     */ 
/*     */       
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.throwing(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "<init>(String,byte [ ],int,Date,String,String)", (Throwable)ex, 2);
/*     */       }
/*     */       
/* 161 */       throw ex;
/*     */     } 
/* 163 */     this.unprotectedData = unprotectData;
/*     */     
/* 165 */     if (protType != 3) {
/* 166 */       String protName = Integer.toString(protType);
/* 167 */       if (protType < MessageProtectionConstants.QOP_NAMES.length) {
/* 168 */         protName = MessageProtectionConstants.QOP_NAMES[protType];
/*     */       }
/* 170 */       HashMap<String, Object> inserts = new HashMap<>();
/* 171 */       inserts.put("AMS_INSERT_QUALITY_OF_PROTECTION", protName);
/* 172 */       MessageProtectionException ex = new MessageProtectionException(AmsErrorMessages.mjp_msg_error_invalid_quality_of_protection, inserts, new IllegalArgumentException("protType"));
/*     */ 
/*     */       
/* 175 */       if (Trace.isOn) {
/* 176 */         Trace.throwing(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "<init>(String,byte [ ],int,Date,String,String)", (Throwable)ex, 3);
/*     */       }
/*     */       
/* 179 */       throw ex;
/*     */     } 
/*     */     
/* 182 */     this.protType = protType;
/* 183 */     this.signDate = signDate;
/* 184 */     this.encAlg = encAlg;
/*     */     
/* 186 */     if (Trace.isOn) {
/* 187 */       Trace.exit(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "<init>(String,byte [ ],int,Date,String,String)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean wasSigned() {
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.entry(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "wasSigned()");
/*     */     }
/* 201 */     boolean traceRet1 = (this.protType == 1);
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.exit(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "wasSigned()", 
/* 204 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 206 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean wasSealed() {
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.entry(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "wasSealed()");
/*     */     }
/* 217 */     boolean traceRet1 = (this.protType == 2);
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.exit(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "wasSealed()", 
/* 220 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 222 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean wasConfidential() {
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.entry(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "wasConfidential()");
/*     */     }
/* 233 */     boolean traceRet1 = (this.protType == 3);
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.exit(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "wasConfidential()", 
/* 236 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 238 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSenderDN() {
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.data(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "getSenderDN()", "getter", this.senderDN);
/*     */     }
/*     */     
/* 252 */     return this.senderDN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getUnprotectedData() {
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.data(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "getUnprotectedData()", "getter", this.unprotectedData);
/*     */     }
/*     */     
/* 265 */     return this.unprotectedData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProtType() {
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.data(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "getProtType()", "getter", 
/* 275 */           Integer.valueOf(this.protType));
/*     */     }
/* 277 */     return this.protType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getSignDate() {
/* 284 */     if (Trace.isOn) {
/* 285 */       Trace.data(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "getSignDate()", "getter", this.signDate);
/*     */     }
/*     */     
/* 288 */     return this.signDate;
/*     */   }
/*     */   
/*     */   public String getSignAlg() {
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.data(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "getSignAlg()", "getter", this.signAlg);
/*     */     }
/*     */     
/* 296 */     return this.signAlg;
/*     */   }
/*     */   
/*     */   public String getEncAlg() {
/* 300 */     if (Trace.isOn) {
/* 301 */       Trace.data(this, "com.ibm.mq.ese.prot.MessageUnprotectInfo", "getEncAlg()", "getter", this.encAlg);
/*     */     }
/* 303 */     return this.encAlg;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\ese\prot\MessageUnprotectInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */