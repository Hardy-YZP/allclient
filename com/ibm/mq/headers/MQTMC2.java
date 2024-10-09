/*     */ package com.ibm.mq.headers;
/*     */ 
/*     */ import com.ibm.mq.headers.internal.Header;
/*     */ import com.ibm.mq.headers.internal.HeaderField;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
/*     */ import com.ibm.mq.headers.internal.MessageWrapper;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQTMC2
/*     */   extends Header
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQTMC2.java";
/*     */   public static final int SIZE = 732;
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.mq.headers.MQTMC2", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQTMC2.java");
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
/*  69 */   static final HeaderType TYPE = new HeaderType("MQTMC2");
/*     */   
/*  71 */   static final HeaderField StrucId = TYPE.addMQChar("StrucId", "TMC ");
/*  72 */   static final HeaderField Version = TYPE.addMQChar("Version", "   2");
/*  73 */   static final HeaderField QName = TYPE.addMQChar("QName", 48);
/*  74 */   static final HeaderField ProcessName = TYPE.addMQChar("ProcessName", 48);
/*  75 */   static final HeaderField TriggerData = TYPE.addMQChar("TriggerData", 64);
/*  76 */   static final HeaderField ApplType = TYPE.addMQChar("ApplType", 4);
/*  77 */   static final HeaderField ApplId = TYPE.addMQChar("ApplId", 256);
/*  78 */   static final HeaderField EnvData = TYPE.addMQChar("EnvData", 128);
/*  79 */   static final HeaderField UserData = TYPE.addMQChar("UserData", 128);
/*  80 */   static final HeaderField QMgrName = TYPE.addMQChar("QMgrName", 48);
/*     */   
/*     */   protected MQTMC2(HeaderType type) {
/*  83 */     super(type);
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.entry(this, "com.ibm.mq.headers.MQTMC2", "<init>(HeaderType)", new Object[] { type });
/*     */     }
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.exit(this, "com.ibm.mq.headers.MQTMC2", "<init>(HeaderType)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQTMC2() {
/*  97 */     super(TYPE);
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.entry(this, "com.ibm.mq.headers.MQTMC2", "<init>()");
/*     */     }
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.exit(this, "com.ibm.mq.headers.MQTMC2", "<init>()");
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
/*     */   public MQTMC2(DataInput message) throws MQDataException, IOException {
/* 115 */     this();
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.entry(this, "com.ibm.mq.headers.MQTMC2", "<init>(DataInput)", new Object[] { message });
/*     */     }
/* 119 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.exit(this, "com.ibm.mq.headers.MQTMC2", "<init>(DataInput)");
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
/*     */   public MQTMC2(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 138 */     this();
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.entry(this, "com.ibm.mq.headers.MQTMC2", "<init>(DataInput,int,int)", new Object[] { message, 
/* 141 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 144 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 146 */     catch (MQDataException mde) {
/* 147 */       if (Trace.isOn) {
/* 148 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQTMC2", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/*     */       
/* 151 */       if (Trace.isOn) {
/* 152 */         Trace.throwing(this, "com.ibm.mq.headers.MQTMC2", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/* 154 */       throw mde;
/*     */     }
/* 156 */     catch (IOException ioe) {
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQTMC2", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 160 */       if (Trace.isOn) {
/* 161 */         Trace.throwing(this, "com.ibm.mq.headers.MQTMC2", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 163 */       throw ioe;
/*     */     }
/* 165 */     catch (Exception e) {
/* 166 */       if (Trace.isOn) {
/* 167 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQTMC2", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/* 169 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 170 */       if (Trace.isOn) {
/* 171 */         Trace.throwing(this, "com.ibm.mq.headers.MQTMC2", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 174 */       throw traceRet1;
/*     */     } 
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.exit(this, "com.ibm.mq.headers.MQTMC2", "<init>(DataInput,int,int)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStrucId() {
/* 188 */     String traceRet1 = getStringValue(StrucId);
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "getStrucId()", "getter", traceRet1);
/*     */     }
/* 192 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVersion() {
/* 201 */     String traceRet1 = getStringValue(Version);
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "getVersion()", "getter", traceRet1);
/*     */     }
/* 205 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setVersion(int value) {
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "setVersion(int)", "setter", 
/* 217 */           Integer.valueOf(value));
/*     */     }
/* 219 */     setVersion("" + value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(String value) {
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "setVersion(String)", "setter", value);
/*     */     }
/* 230 */     setStringValue(Version, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQName() {
/* 239 */     String traceRet1 = getStringValue(QName);
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "getQName()", "getter", traceRet1);
/*     */     }
/* 243 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQName(String value) {
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "setQName(String)", "setter", value);
/*     */     }
/* 255 */     setStringValue(QName, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProcessName() {
/* 264 */     String traceRet1 = getStringValue(ProcessName);
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "getProcessName()", "getter", traceRet1);
/*     */     }
/* 268 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProcessName(String value) {
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "setProcessName(String)", "setter", value);
/*     */     }
/* 280 */     setStringValue(ProcessName, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTriggerData() {
/* 289 */     String traceRet1 = getStringValue(TriggerData);
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "getTriggerData()", "getter", traceRet1);
/*     */     }
/* 293 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTriggerData(String value) {
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "setTriggerData(String)", "setter", value);
/*     */     }
/* 305 */     setStringValue(TriggerData, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApplType() {
/* 314 */     String traceRet1 = getStringValue(ApplType);
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "getApplType()", "getter", traceRet1);
/*     */     }
/* 318 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setApplType(int value) {
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "setApplType(int)", "setter", 
/* 330 */           Integer.valueOf(value));
/*     */     }
/* 332 */     setApplType("" + value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApplType(String value) {
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "setApplType(String)", "setter", value);
/*     */     }
/* 344 */     setStringValue(ApplType, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApplId() {
/* 353 */     String traceRet1 = getStringValue(ApplId);
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "getApplId()", "getter", traceRet1);
/*     */     }
/* 357 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApplId(String value) {
/* 366 */     if (Trace.isOn) {
/* 367 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "setApplId(String)", "setter", value);
/*     */     }
/* 369 */     setStringValue(ApplId, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEnvData() {
/* 378 */     String traceRet1 = getStringValue(EnvData);
/* 379 */     if (Trace.isOn) {
/* 380 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "getEnvData()", "getter", traceRet1);
/*     */     }
/* 382 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnvData(String value) {
/* 391 */     if (Trace.isOn) {
/* 392 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "setEnvData(String)", "setter", value);
/*     */     }
/* 394 */     setStringValue(EnvData, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserData() {
/* 403 */     String traceRet1 = getStringValue(UserData);
/* 404 */     if (Trace.isOn) {
/* 405 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "getUserData()", "getter", traceRet1);
/*     */     }
/* 407 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserData(String value) {
/* 416 */     if (Trace.isOn) {
/* 417 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "setUserData(String)", "setter", value);
/*     */     }
/* 419 */     setStringValue(UserData, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQMgrName() {
/* 428 */     String traceRet1 = getStringValue(QMgrName);
/* 429 */     if (Trace.isOn) {
/* 430 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "getQMgrName()", "getter", traceRet1);
/*     */     }
/* 432 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQMgrName(String value) {
/* 441 */     if (Trace.isOn) {
/* 442 */       Trace.data(this, "com.ibm.mq.headers.MQTMC2", "setQMgrName(String)", "setter", value);
/*     */     }
/* 444 */     setStringValue(QMgrName, value);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQTMC2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */