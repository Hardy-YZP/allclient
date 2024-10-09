/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import com.ibm.mq.constants.MQConstants;
/*     */ import com.ibm.mq.exits.MQCD;
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiUtils;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LpiAdoptUserOptions
/*     */ {
/*     */   private JmqiEnvironment env;
/*     */   private String strucId;
/*     */   private int version;
/*     */   private MQCD channel;
/*     */   private String connectionName;
/*     */   private int environment;
/*     */   private int connectOptions;
/*     */   private String productIdentifier;
/*     */   private String clientIdentifier;
/*     */   public static final int lpiSPIADOPT_USERID = 1;
/*     */   public static final int lpiSPIADOPT_APPLNAME = 2;
/*     */   public static final int lpiSPIADOPT_ACCTTOKEN = 4;
/*     */   public static final int lpiSPIADOPT_APPLTYPE = 8;
/*     */   public static final int lpiSPIADOPT_RESTORE_CONTEXT = 16;
/*     */   public static final int lpiSPIADOPT_RETURN = 32;
/*     */   public static final int lpiSPIADOPT_CONNOPTS = 64;
/*     */   public static final int lpiSPIADOPT_FORCECONNID = 128;
/*     */   public static final int lpiSPIADOPT_MONITORING = 256;
/*     */   public static final int lpiSPIADOPT_CHANNELNAME = 512;
/*     */   public static final int lpiSPIADOPT_PRODUCTID = 1024;
/*     */   public static final int lpiSPIADOPT_CICSCONTEXT = 2048;
/*     */   public static final int lpiSPIADOPT_CLIENTID = 4096;
/* 102 */   static String lpiAdoptUserSTRUCID = "LADU";
/* 103 */   static int lpiAdoptUserVERSION_1 = 1;
/* 104 */   static int lpiAdoptUserVERSION_2 = 2;
/* 105 */   static int lpiAdoptUserVERSION_3 = 3;
/* 106 */   static int lpiAdoptUserCurrentVersion = lpiAdoptUserVERSION_3;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LpiAdoptUserOptions(JmqiEnvironment env) {
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiAdoptUserOptions", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/* 116 */     this.env = env;
/* 117 */     this.strucId = lpiAdoptUserSTRUCID;
/* 118 */     this.version = lpiAdoptUserCurrentVersion;
/* 119 */     this.channel = null;
/* 120 */     this.connectionName = null;
/* 121 */     this.environment = 5;
/* 122 */     this.connectOptions = 0;
/* 123 */     this.productIdentifier = null;
/* 124 */     this.clientIdentifier = null;
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiAdoptUserOptions", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCD getChannel() {
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiAdoptUserOptions", "getChannel()", "getter", this.channel);
/*     */     }
/*     */     
/* 139 */     return this.channel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChannel(MQCD channel) {
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiAdoptUserOptions", "setChannel(MQCD)", "setter", channel);
/*     */     }
/*     */     
/* 150 */     this.channel = channel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getConnectionName() {
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiAdoptUserOptions", "getConnectionName()", "getter", this.connectionName);
/*     */     }
/*     */     
/* 161 */     return this.connectionName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnectionName(String connectionName) {
/* 168 */     if (Trace.isOn) {
/* 169 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiAdoptUserOptions", "setConnectionName(String)", "setter", connectionName);
/*     */     }
/*     */     
/* 172 */     this.connectionName = connectionName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEnvironment() {
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiAdoptUserOptions", "getEnvironment()", "getter", 
/* 181 */           Integer.valueOf(this.environment));
/*     */     }
/* 183 */     return this.environment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnvironment(int environment) {
/* 190 */     if (Trace.isOn) {
/* 191 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiAdoptUserOptions", "setEnvironment(int)", "setter", 
/* 192 */           Integer.valueOf(environment));
/*     */     }
/* 194 */     this.environment = environment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getConnectOptions() {
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiAdoptUserOptions", "getConnectOptions()", "getter", 
/* 203 */           Integer.valueOf(this.connectOptions));
/*     */     }
/* 205 */     return this.connectOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnectOptions(int connectOptions) {
/* 212 */     if (Trace.isOn) {
/* 213 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiAdoptUserOptions", "setConnectOptions(int)", "setter", 
/* 214 */           Integer.valueOf(connectOptions));
/*     */     }
/* 216 */     this.connectOptions = connectOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProductIdentifier() {
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiAdoptUserOptions", "getProductIdentifier()", "getter", this.productIdentifier);
/*     */     }
/*     */     
/* 227 */     return this.productIdentifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProductIdentifier(String productIdentifier) {
/* 234 */     if (Trace.isOn) {
/* 235 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiAdoptUserOptions", "setProductIdentifier(String)", "setter", productIdentifier);
/*     */     }
/*     */     
/* 238 */     this.productIdentifier = productIdentifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClientIdentifier() {
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiAdoptUserOptions", "getClientIdentifier()", "getter", this.clientIdentifier);
/*     */     }
/*     */     
/* 249 */     return this.clientIdentifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClientIdentifier(String clientIdentifier) {
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiAdoptUserOptions", "setClientIdentifier(String)", "setter", clientIdentifier);
/*     */     }
/*     */     
/* 260 */     this.clientIdentifier = clientIdentifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiAdoptUserOptions", "toString()");
/*     */     }
/* 271 */     JmqiStructureFormatter fmt = new JmqiStructureFormatter(this.env, 15, 2, JmqiUtils.NL);
/* 272 */     fmt.add("StrucId", this.strucId);
/* 273 */     fmt.add("Version", this.version);
/* 274 */     fmt.add("Channel", this.channel.toString());
/* 275 */     fmt.add("ConnectionName", this.connectionName);
/* 276 */     fmt.add("Environment", this.environment);
/* 277 */     fmt.add("Environment", MQConstants.lookup(this.environment, "MQXE_.*"));
/* 278 */     fmt.add("ConnectOptions", this.connectOptions);
/* 279 */     fmt.add("ConnectOptions", MQConstants.decodeOptionsForTrace(this.connectOptions, "MQCNO_.*"));
/* 280 */     fmt.add("ProductIdentifier", this.productIdentifier);
/* 281 */     String traceRet1 = fmt.toString();
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiAdoptUserOptions", "toString()", traceRet1);
/*     */     }
/* 285 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\LpiAdoptUserOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */