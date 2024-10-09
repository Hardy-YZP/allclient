/*     */ package com.ibm.mq;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiObject;
/*     */ import com.ibm.mq.jmqi.MQCNO;
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
/*     */ class MQConnectionOptions
/*     */   extends JmqiObject
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQConnectionOptions.java";
/*     */   
/*     */   static {
/*  35 */     if (Trace.isOn) {
/*  36 */       Trace.data("com.ibm.mq.MQConnectionOptions", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/MQConnectionOptions.java");
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
/*  50 */   private MQCNO jmqiStructure = MQSESSION.getJmqiEnv().newMQCNO();
/*     */   
/*     */   public static final String MQCNO_STRUCT_ID = "CNO ";
/*     */   
/*  54 */   public static final char[] MQCNO_STRUCT_ID_ARRAY = new char[] { 'C', 'N', 'O', ' ' };
/*     */   
/*  56 */   private String structId = "CNO ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean requiresFAP8 = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   private MQChannelDefinition clientConn = null;
/*     */ 
/*     */   
/*  71 */   public static final char[] MQCT_NONE = MQC.MQCT_NONE_ARRAY;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   private MQSSLConfigurationOptions SSLConfig = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   private MQConnectionSecurityParameters securityParms = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQConnectionOptions() {
/*  89 */     super(MQSESSION.getJmqiEnv());
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.entry(this, "com.ibm.mq.MQConnectionOptions", "<init>()");
/*     */     }
/*     */ 
/*     */     
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.exit(this, "com.ibm.mq.MQConnectionOptions", "<init>()");
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
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "getStructId()", "getter", this.structId);
/*     */     }
/* 114 */     return this.structId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char[] getStructIdAsArray() {
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "getStructIdAsArray()", "getter", MQCNO_STRUCT_ID_ARRAY);
/*     */     }
/*     */     
/* 129 */     return MQCNO_STRUCT_ID_ARRAY;
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
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "setVersion(int)", "setter", 
/* 144 */           Integer.valueOf(i));
/*     */     }
/* 146 */     this.jmqiStructure.setVersion(i);
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
/* 158 */     int traceRet1 = this.jmqiStructure.getVersion();
/* 159 */     if (Trace.isOn) {
/* 160 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "getVersion()", "getter", 
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int opts) {
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "setOptions(int)", "setter", 
/* 179 */           Integer.valueOf(opts));
/*     */     }
/* 181 */     this.jmqiStructure.setOptions(opts);
/* 182 */     if ((opts & 0x8) != 0 || (opts & 0x10) != 0 || (opts & 0x2) != 0 || (opts & 0x4) != 0)
/*     */     {
/*     */ 
/*     */       
/* 186 */       this.requiresFAP8 = true;
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
/*     */   public int getOptions() {
/* 199 */     int traceRet1 = this.jmqiStructure.getOptions();
/* 200 */     if (Trace.isOn) {
/* 201 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "getOptions()", "getter", 
/* 202 */           Integer.valueOf(traceRet1));
/*     */     }
/* 204 */     return traceRet1;
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
/*     */   public void setMQCD(MQChannelDefinition cd) {
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "setMQCD(MQChannelDefinition)", "setter", cd);
/*     */     }
/*     */     
/* 220 */     this.clientConn = cd;
/*     */ 
/*     */     
/* 223 */     if (this.jmqiStructure.getVersion() < 2) {
/* 224 */       this.jmqiStructure.setVersion(2);
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
/*     */   public MQChannelDefinition getMQCD() {
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "getMQCD()", "getter", this.clientConn);
/*     */     }
/* 240 */     return this.clientConn;
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
/*     */   public void setConnTag(byte[] b) {
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "setConnTag(byte [ ])", "setter", b);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 259 */     this.jmqiStructure.setConnTag(b);
/*     */     
/* 261 */     if (this.jmqiStructure.getVersion() < 3) {
/* 262 */       this.jmqiStructure.setVersion(3);
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
/*     */   public byte[] getConnTag() {
/* 276 */     byte[] traceRet1 = this.jmqiStructure.getConnTag();
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "getConnTag()", "getter", traceRet1);
/*     */     }
/* 280 */     return traceRet1;
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
/*     */   public void setConnectionId(byte[] b) {
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "setConnectionId(byte [ ])", "setter", b);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 301 */     this.jmqiStructure.setConnectionId(b);
/*     */     
/* 303 */     if (this.jmqiStructure.getVersion() < 5) {
/* 304 */       this.jmqiStructure.setVersion(5);
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
/*     */   public byte[] getConnectionId() {
/* 316 */     byte[] traceRet1 = this.jmqiStructure.getConnectionId();
/* 317 */     if (Trace.isOn) {
/* 318 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "getConnectionId()", "getter", traceRet1);
/*     */     }
/*     */     
/* 321 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMQSCO(MQSSLConfigurationOptions sco) {
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "setMQSCO(MQSSLConfigurationOptions)", "setter", sco);
/*     */     }
/*     */     
/* 335 */     this.SSLConfig = sco;
/* 336 */     this.requiresFAP8 = true;
/* 337 */     this.jmqiStructure.setSslConfig(sco.getJMQIStructure());
/*     */     
/* 339 */     if (this.jmqiStructure.getVersion() < 4) {
/* 340 */       this.jmqiStructure.setVersion(4);
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
/*     */   public MQSSLConfigurationOptions getMQSCO() {
/* 352 */     if (Trace.isOn) {
/* 353 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "getMQSCO()", "getter", this.SSLConfig);
/*     */     }
/* 355 */     return this.SSLConfig;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMQCSP(MQConnectionSecurityParameters csp) {
/* 365 */     if (Trace.isOn) {
/* 366 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "setMQCSP(MQConnectionSecurityParameters)", "setter", csp);
/*     */     }
/*     */     
/* 369 */     this.securityParms = csp;
/* 370 */     this.jmqiStructure.setSecurityParms(csp.getJMQIStructure());
/*     */     
/* 372 */     if (this.jmqiStructure.getVersion() < 5) {
/* 373 */       this.jmqiStructure.setVersion(5);
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
/*     */   public MQConnectionSecurityParameters getMQCSP() {
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "getMQCSP()", "getter", this.securityParms);
/*     */     }
/* 388 */     return this.securityParms;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getRequiresFAP8() {
/* 398 */     if (Trace.isOn) {
/* 399 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "getRequiresFAP8()", "getter", 
/* 400 */           Boolean.valueOf(this.requiresFAP8));
/*     */     }
/* 402 */     return this.requiresFAP8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQCNO getJMQIStructure() {
/* 413 */     boolean fipsRequired = false;
/* 414 */     if (this.SSLConfig != null) {
/* 415 */       fipsRequired = this.SSLConfig.isFipsRequired();
/*     */     }
/* 417 */     if (this.clientConn != null) {
/* 418 */       this.jmqiStructure.setClientConn(this.clientConn.getJMQIStructure(fipsRequired));
/*     */     }
/* 420 */     if (Trace.isOn) {
/* 421 */       Trace.data(this, "com.ibm.mq.MQConnectionOptions", "getJMQIStructure()", "getter", this.jmqiStructure);
/*     */     }
/*     */     
/* 424 */     return this.jmqiStructure;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\MQConnectionOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */