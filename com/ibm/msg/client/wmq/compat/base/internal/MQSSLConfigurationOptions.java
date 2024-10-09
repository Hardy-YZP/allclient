/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQSSLConfigurationOptions
/*     */   extends JmqiObject
/*     */ {
/*     */   static {
/*  35 */     if (Trace.isOn) {
/*  36 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQSSLConfigurationOptions", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQSSLConfigurationOptions.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   public static final String GSK_ACCELERATOR_NCIPHER_NF_OFF = null;
/*     */ 
/*     */   
/*  48 */   public static final String GSK_ACCELERATOR_NCIPHER_NF_ON = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public static final String GSK_ACCELERATOR_RAINBOW_CS_OFF = null;
/*     */ 
/*     */   
/*  58 */   public static final String GSK_ACCELERATOR_RAINBOW_CS_ON = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String MQSCO_STRUCT_ID = "SCO ";
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int RESET_COUNT_DEFAULT = 0;
/*     */ 
/*     */   
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQSSLConfigurationOptions.java";
/*     */ 
/*     */   
/*  72 */   private MQSCO jmqiStructure = MQSESSION.getJmqiEnv().newMQSCO();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQSSLConfigurationOptions() {
/*  78 */     super(MQSESSION.getJmqiEnv());
/*  79 */     if (Trace.isOn) {
/*  80 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSSLConfigurationOptions", "<init>()");
/*     */     }
/*     */     
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSSLConfigurationOptions", "<init>()");
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
/*     */   public int getAuthInfoRecCount() {
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSSLConfigurationOptions", "getAuthInfoRecCount()", "getter", 
/* 101 */           Integer.valueOf(0));
/*     */     }
/* 103 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCryptoHardware() {
/* 114 */     String traceRet1 = this.jmqiStructure.getCryptoHardware();
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSSLConfigurationOptions", "getCryptoHardware()", "getter", traceRet1);
/*     */     }
/*     */     
/* 119 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQSCO getJMQIStructure() {
/* 127 */     if (Trace.isOn) {
/* 128 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSSLConfigurationOptions", "getJMQIStructure()", "getter", this.jmqiStructure);
/*     */     }
/*     */     
/* 131 */     return this.jmqiStructure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getKeyRepository() {
/* 142 */     String traceRet1 = this.jmqiStructure.getKeyRepository();
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSSLConfigurationOptions", "getKeyRepository()", "getter", traceRet1);
/*     */     }
/*     */     
/* 147 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getKeyResetCount() {
/* 158 */     int traceRet1 = this.jmqiStructure.getKeyResetCount();
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSSLConfigurationOptions", "getKeyResetCount()", "getter", 
/* 161 */           Integer.valueOf(traceRet1));
/*     */     }
/* 163 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStructId() {
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSSLConfigurationOptions", "getStructId()", "getter", "SCO ");
/*     */     }
/*     */     
/* 177 */     return "SCO ";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 188 */     int traceRet1 = this.jmqiStructure.getVersion();
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSSLConfigurationOptions", "getVersion()", "getter", 
/* 191 */           Integer.valueOf(traceRet1));
/*     */     }
/* 193 */     return traceRet1;
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
/*     */   public boolean isFipsRequired() {
/* 221 */     boolean traceRet1 = (this.jmqiStructure.getFipsRequired() == 1);
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSSLConfigurationOptions", "isFipsRequired()", "getter", 
/* 224 */           Boolean.valueOf(traceRet1));
/*     */     }
/* 226 */     return traceRet1;
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
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSSLConfigurationOptions", "setCryptoHardware(String)", "setter", cry);
/*     */     }
/*     */     
/* 242 */     this.jmqiStructure.setCryptoHardware(cry);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFipsRequired(boolean b) {
/* 253 */     if (Trace.isOn) {
/* 254 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSSLConfigurationOptions", "setFipsRequired(boolean)", "setter", 
/* 255 */           Boolean.valueOf(b));
/*     */     }
/* 257 */     if (b) {
/* 258 */       this.jmqiStructure.setFipsRequired(1);
/*     */     } else {
/*     */       
/* 261 */       this.jmqiStructure.setFipsRequired(0);
/*     */     } 
/*     */     
/* 264 */     if (this.jmqiStructure.getVersion() < 2) {
/* 265 */       this.jmqiStructure.setVersion(2);
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
/*     */   public void setKeyRepository(String rep) {
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSSLConfigurationOptions", "setKeyRepository(String)", "setter", rep);
/*     */     }
/*     */     
/* 282 */     this.jmqiStructure.setKeyRepository(rep);
/*     */   }
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
/* 294 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSSLConfigurationOptions", "setKeyResetCount(int)", "setter", 
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
/*     */   public void setVersion(int i) {
/* 314 */     if (Trace.isOn) {
/* 315 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQSSLConfigurationOptions", "setVersion(int)", "setter", 
/* 316 */           Integer.valueOf(i));
/*     */     }
/* 318 */     this.jmqiStructure.setVersion(i);
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
/*     */   public void setKeyRepositoryPassword(String rep) {
/* 345 */     throw new UnsupportedOperationException();
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
/*     */   public String getKeyRepositoryPassword() {
/* 369 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQSSLConfigurationOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */