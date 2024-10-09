/*     */ package com.ibm.msg.client.wmq.compat.base.internal;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
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
/*     */ public class MQConnectionOptions
/*     */   extends JmqiObject
/*     */ {
/*     */   public static final String MQCNO_STRUCT_ID = "CNO ";
/*     */   
/*     */   static {
/*  36 */     if (Trace.isOn) {
/*  37 */       Trace.data("com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQConnectionOptions.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   public static final char[] MQCNO_STRUCT_ID_ARRAY = new char[] { 'C', 'N', 'O', ' ' };
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.msg.client.wmq.compat/src/com/ibm/msg/client/wmq/compat/base/internal/MQConnectionOptions.java";
/*     */ 
/*     */ 
/*     */   
/*  55 */   private MQChannelDefinition clientConn = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   private JmqiEnvironment jmqiEnv = MQSESSION.getJmqiEnv();
/*     */   
/*  64 */   private MQCNO jmqiStructure = this.jmqiEnv.newMQCNO();
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
/*  76 */   private MQConnectionSecurityParameters securityParms = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   private MQSSLConfigurationOptions SSLConfig = null;
/*     */   
/*  83 */   private String structId = "CNO ";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQConnectionOptions() {
/*  89 */     super(MQSESSION.getJmqiEnv());
/*  90 */     if (Trace.isOn) {
/*  91 */       Trace.entry(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "<init>()");
/*     */     }
/*     */     
/*  94 */     if (Trace.isOn) {
/*  95 */       Trace.exit(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "<init>()");
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
/*     */   public byte[] getConnectionId() {
/* 110 */     byte[] traceRet1 = this.jmqiStructure.getConnectionId();
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "getConnectionId()", "getter", traceRet1);
/*     */     }
/*     */     
/* 115 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getConnTag() {
/* 125 */     byte[] traceRet1 = this.jmqiStructure.getConnTag();
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "getConnTag()", "getter", traceRet1);
/*     */     }
/*     */     
/* 130 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQCNO getJMQIStructure() {
/* 138 */     if (this.clientConn != null) {
/* 139 */       this.jmqiStructure.setClientConn(this.clientConn.getJMQIStructure());
/*     */     }
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "getJMQIStructure()", "getter", this.jmqiStructure);
/*     */     }
/*     */     
/* 145 */     return this.jmqiStructure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQChannelDefinition getMQCD() {
/* 156 */     if (Trace.isOn) {
/* 157 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "getMQCD()", "getter", this.clientConn);
/*     */     }
/*     */     
/* 160 */     return this.clientConn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQConnectionSecurityParameters getMQCSP() {
/* 169 */     if (Trace.isOn) {
/* 170 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "getMQCSP()", "getter", this.securityParms);
/*     */     }
/*     */     
/* 173 */     return this.securityParms;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQSSLConfigurationOptions getMQSCO() {
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "getMQSCO()", "getter", this.SSLConfig);
/*     */     }
/*     */     
/* 186 */     return this.SSLConfig;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 197 */     int traceRet1 = this.jmqiStructure.getOptions();
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "getOptions()", "getter", 
/* 200 */           Integer.valueOf(traceRet1));
/*     */     }
/* 202 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getRequiresFAP8() {
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "getRequiresFAP8()", "getter", 
/* 211 */           Boolean.valueOf(this.requiresFAP8));
/*     */     }
/* 213 */     return this.requiresFAP8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStructId() {
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "getStructId()", "getter", this.structId);
/*     */     }
/*     */     
/* 227 */     return this.structId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char[] getStructIdAsArray() {
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "getStructIdAsArray()", "getter", MQCNO_STRUCT_ID_ARRAY);
/*     */     }
/*     */     
/* 241 */     return MQCNO_STRUCT_ID_ARRAY;
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
/* 252 */     int traceRet1 = this.jmqiStructure.getVersion();
/* 253 */     if (Trace.isOn) {
/* 254 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "getVersion()", "getter", 
/* 255 */           Integer.valueOf(traceRet1));
/*     */     }
/* 257 */     return traceRet1;
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
/*     */   public void setConnectionId(byte[] b) {
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "setConnectionId(byte [ ])", "setter", b);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 277 */     this.jmqiStructure.setConnectionId(b);
/*     */ 
/*     */     
/* 280 */     if (this.jmqiStructure.getVersion() < 5) {
/* 281 */       this.jmqiStructure.setVersion(5);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnTag(byte[] b) {
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "setConnTag(byte [ ])", "setter", b);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 300 */     this.jmqiStructure.setConnTag(b);
/*     */ 
/*     */     
/* 303 */     if (this.jmqiStructure.getVersion() < 3) {
/* 304 */       this.jmqiStructure.setVersion(3);
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
/*     */   public void setMQCD(MQChannelDefinition cd) {
/* 317 */     if (Trace.isOn) {
/* 318 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "setMQCD(MQChannelDefinition)", "setter", cd);
/*     */     }
/*     */     
/* 321 */     this.clientConn = cd;
/*     */ 
/*     */     
/* 324 */     if (this.jmqiStructure.getVersion() < 2) {
/* 325 */       this.jmqiStructure.setVersion(2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMQCSP(MQConnectionSecurityParameters csp) {
/* 335 */     if (Trace.isOn) {
/* 336 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "setMQCSP(MQConnectionSecurityParameters)", "setter", csp);
/*     */     }
/*     */     
/* 339 */     this.securityParms = csp;
/* 340 */     this.jmqiStructure.setSecurityParms(csp.getJMQIStructure());
/*     */ 
/*     */     
/* 343 */     if (this.jmqiStructure.getVersion() < 5) {
/* 344 */       this.jmqiStructure.setVersion(5);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMQSCO(MQSSLConfigurationOptions sco) {
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "setMQSCO(MQSSLConfigurationOptions)", "setter", sco);
/*     */     }
/*     */     
/* 358 */     this.SSLConfig = sco;
/* 359 */     this.requiresFAP8 = true;
/* 360 */     this.jmqiStructure.setSslConfig(sco.getJMQIStructure());
/*     */ 
/*     */     
/* 363 */     if (this.jmqiStructure.getVersion() < 4) {
/* 364 */       this.jmqiStructure.setVersion(4);
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
/*     */   public void setOptions(int opts) {
/* 379 */     if (Trace.isOn) {
/* 380 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "setOptions(int)", "setter", 
/* 381 */           Integer.valueOf(opts));
/*     */     }
/* 383 */     this.jmqiStructure.setOptions(opts);
/* 384 */     if ((opts & 0x8) != 0 || (opts & 0x10) != 0 || (opts & 0x2) != 0 || (opts & 0x4) != 0)
/*     */     {
/*     */ 
/*     */       
/* 388 */       this.requiresFAP8 = true;
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
/* 401 */     if (Trace.isOn) {
/* 402 */       Trace.data(this, "com.ibm.msg.client.wmq.compat.base.internal.MQConnectionOptions", "setVersion(int)", "setter", 
/* 403 */           Integer.valueOf(i));
/*     */     }
/* 405 */     this.jmqiStructure.setVersion(i);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\msg\client\wmq\compat\base\internal\MQConnectionOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */