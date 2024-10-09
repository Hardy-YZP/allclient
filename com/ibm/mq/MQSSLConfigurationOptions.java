/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQSCO;
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
/*     */ class MQSSLConfigurationOptions
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQSSLConfigurationOptions.java";
/*     */   
/*     */   static {
/*  34 */     if (Trace.isOn) {
/*  35 */       Trace.data("com.ibm.mq.MQSSLConfigurationOptions", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQSSLConfigurationOptions.java");
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
/*  50 */   private MQSCO jmqiStructure = MQSESSION.getJmqiEnv().newMQSCO();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQSCO_STRUCT_ID = "SCO ";
/*     */ 
/*     */ 
/*     */   
/*  59 */   public static final String GSK_ACCELERATOR_RAINBOW_CS_OFF = null;
/*  60 */   public static final String GSK_ACCELERATOR_RAINBOW_CS_ON = null;
/*  61 */   public static final String GSK_ACCELERATOR_NCIPHER_NF_OFF = null;
/*  62 */   public static final String GSK_ACCELERATOR_NCIPHER_NF_ON = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int RESET_COUNT_DEFAULT = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public MQSSLConfigurationOptions() {
/*  71 */     super(MQSESSION.getJmqiEnv());
/*  72 */     if (Trace.isOn) {
/*  73 */       Trace.entry(this, "com.ibm.mq.MQSSLConfigurationOptions", "<init>()");
/*     */     }
/*     */ 
/*     */     
/*  77 */     if (Trace.isOn) {
/*  78 */       Trace.exit(this, "com.ibm.mq.MQSSLConfigurationOptions", "<init>()");
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
/*     */   public String getStructId() {
/*  93 */     if (Trace.isOn) {
/*  94 */       Trace.data(this, "com.ibm.mq.MQSSLConfigurationOptions", "getStructId()", "getter", "SCO ");
/*     */     }
/*     */     
/*  97 */     return "SCO ";
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
/*     */   public void setVersion(int i) {
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.data(this, "com.ibm.mq.MQSSLConfigurationOptions", "setVersion(int)", "setter", 
/* 112 */           Integer.valueOf(i));
/*     */     }
/* 114 */     this.jmqiStructure.setVersion(i);
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
/*     */   public int getVersion() {
/* 126 */     int traceRet1 = this.jmqiStructure.getVersion();
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.data(this, "com.ibm.mq.MQSSLConfigurationOptions", "getVersion()", "getter", 
/* 129 */           Integer.valueOf(traceRet1));
/*     */     }
/* 131 */     return traceRet1;
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
/*     */   public void setKeyRepository(String rep) {
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.data(this, "com.ibm.mq.MQSSLConfigurationOptions", "setKeyRepository(String)", "setter", rep);
/*     */     }
/*     */     
/* 148 */     this.jmqiStructure.setKeyRepository(rep);
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
/*     */   public String getKeyRepository() {
/* 160 */     String traceRet1 = this.jmqiStructure.getKeyRepository();
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.data(this, "com.ibm.mq.MQSSLConfigurationOptions", "getKeyRepository()", "getter", traceRet1);
/*     */     }
/*     */     
/* 165 */     return traceRet1;
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
/*     */   public void setKeyRepositoryPassword(String rep) {
/* 193 */     throw new UnsupportedOperationException();
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
/*     */   public String getKeyRepositoryPassword() {
/* 216 */     throw new UnsupportedOperationException();
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
/*     */   public void setCryptoHardware(String cry) {
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.data(this, "com.ibm.mq.MQSSLConfigurationOptions", "setCryptoHardware(String)", "setter", cry);
/*     */     }
/*     */     
/* 232 */     this.jmqiStructure.setCryptoHardware(cry);
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
/*     */   public String getCryptoHardware() {
/* 244 */     String traceRet1 = this.jmqiStructure.getCryptoHardware();
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.data(this, "com.ibm.mq.MQSSLConfigurationOptions", "getCryptoHardware()", "getter", traceRet1);
/*     */     }
/*     */     
/* 249 */     return traceRet1;
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
/*     */   public int getAuthInfoRecCount() {
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.data(this, "com.ibm.mq.MQSSLConfigurationOptions", "getAuthInfoRecCount()", "getter", 
/* 279 */           Integer.valueOf(0));
/*     */     }
/* 281 */     return 0;
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
/*     */   public void setKeyResetCount(int c) {
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.data(this, "com.ibm.mq.MQSSLConfigurationOptions", "setKeyResetCount(int)", "setter", 
/* 295 */           Integer.valueOf(c));
/*     */     }
/* 297 */     this.jmqiStructure.setKeyResetCount(c);
/*     */ 
/*     */     
/* 300 */     if (this.jmqiStructure.getVersion() < 2) {
/* 301 */       this.jmqiStructure.setVersion(2);
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
/*     */   public int getKeyResetCount() {
/* 314 */     int traceRet1 = this.jmqiStructure.getKeyResetCount();
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.data(this, "com.ibm.mq.MQSSLConfigurationOptions", "getKeyResetCount()", "getter", 
/* 317 */           Integer.valueOf(traceRet1));
/*     */     }
/* 319 */     return traceRet1;
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
/*     */   public void setFipsRequired(boolean b) {
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.data(this, "com.ibm.mq.MQSSLConfigurationOptions", "setFipsRequired(boolean)", "setter", 
/* 333 */           Boolean.valueOf(b));
/*     */     }
/* 335 */     if (b) {
/* 336 */       this.jmqiStructure.setFipsRequired(1);
/*     */     } else {
/* 338 */       this.jmqiStructure.setFipsRequired(0);
/*     */     } 
/*     */     
/* 341 */     if (this.jmqiStructure.getVersion() < 2) {
/* 342 */       this.jmqiStructure.setVersion(2);
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
/*     */   public boolean isFipsRequired() {
/* 356 */     boolean traceRet1 = (this.jmqiStructure.getFipsRequired() == 1);
/*     */     
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.data(this, "com.ibm.mq.MQSSLConfigurationOptions", "isFipsRequired()", "getter", 
/* 360 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 362 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQSCO getJMQIStructure() {
/* 371 */     if (Trace.isOn) {
/* 372 */       Trace.data(this, "com.ibm.mq.MQSSLConfigurationOptions", "getJMQIStructure()", "getter", this.jmqiStructure);
/*     */     }
/*     */     
/* 375 */     return this.jmqiStructure;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQSSLConfigurationOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */