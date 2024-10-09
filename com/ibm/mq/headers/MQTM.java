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
/*     */ public class MQTM
/*     */   extends Header
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQTM.java";
/*     */   public static final int SIZE = 684;
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.mq.headers.MQTM", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQTM.java");
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
/*  69 */   static final HeaderType TYPE = new HeaderType("MQTM");
/*     */   
/*  71 */   static final HeaderField StrucId = TYPE.addMQChar("StrucId", "TM  ");
/*  72 */   static final HeaderField Version = TYPE.addMQLong("Version", 1);
/*  73 */   static final HeaderField QName = TYPE.addMQChar("QName", 48);
/*  74 */   static final HeaderField ProcessName = TYPE.addMQChar("ProcessName", 48);
/*  75 */   static final HeaderField TriggerData = TYPE.addMQChar("TriggerData", 64);
/*  76 */   static final HeaderField ApplType = TYPE.addMQLong("ApplType");
/*  77 */   static final HeaderField ApplId = TYPE.addMQChar("ApplId", 256);
/*  78 */   static final HeaderField EnvData = TYPE.addMQChar("EnvData", 128);
/*  79 */   static final HeaderField UserData = TYPE.addMQChar("UserData", 128);
/*     */   
/*     */   protected MQTM(HeaderType type) {
/*  82 */     super(type);
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry(this, "com.ibm.mq.headers.MQTM", "<init>(HeaderType)", new Object[] { type });
/*     */     }
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.exit(this, "com.ibm.mq.headers.MQTM", "<init>(HeaderType)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQTM() {
/*  96 */     super(TYPE);
/*  97 */     if (Trace.isOn) {
/*  98 */       Trace.entry(this, "com.ibm.mq.headers.MQTM", "<init>()");
/*     */     }
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.exit(this, "com.ibm.mq.headers.MQTM", "<init>()");
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
/*     */   public MQTM(DataInput message) throws MQDataException, IOException {
/* 114 */     this();
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.entry(this, "com.ibm.mq.headers.MQTM", "<init>(DataInput)", new Object[] { message });
/*     */     }
/* 118 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.exit(this, "com.ibm.mq.headers.MQTM", "<init>(DataInput)");
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
/*     */   public MQTM(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 137 */     this();
/* 138 */     if (Trace.isOn) {
/* 139 */       Trace.entry(this, "com.ibm.mq.headers.MQTM", "<init>(DataInput,int,int)", new Object[] { message, 
/* 140 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 143 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 145 */     catch (MQDataException mde) {
/* 146 */       if (Trace.isOn) {
/* 147 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQTM", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/*     */       
/* 150 */       if (Trace.isOn) {
/* 151 */         Trace.throwing(this, "com.ibm.mq.headers.MQTM", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/* 153 */       throw mde;
/*     */     }
/* 155 */     catch (IOException ioe) {
/* 156 */       if (Trace.isOn) {
/* 157 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQTM", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 159 */       if (Trace.isOn) {
/* 160 */         Trace.throwing(this, "com.ibm.mq.headers.MQTM", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 162 */       throw ioe;
/*     */     }
/* 164 */     catch (Exception e) {
/* 165 */       if (Trace.isOn) {
/* 166 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQTM", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/* 168 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 169 */       if (Trace.isOn) {
/* 170 */         Trace.throwing(this, "com.ibm.mq.headers.MQTM", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 173 */       throw traceRet1;
/*     */     } 
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.exit(this, "com.ibm.mq.headers.MQTM", "<init>(DataInput,int,int)");
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
/* 187 */     String traceRet1 = getStringValue(StrucId);
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.data(this, "com.ibm.mq.headers.MQTM", "getStrucId()", "getter", traceRet1);
/*     */     }
/* 191 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 200 */     int traceRet1 = getIntValue(Version);
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.data(this, "com.ibm.mq.headers.MQTM", "getVersion()", "getter", 
/* 203 */           Integer.valueOf(traceRet1));
/*     */     }
/* 205 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getQName() {
/* 214 */     String traceRet1 = getStringValue(QName);
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.data(this, "com.ibm.mq.headers.MQTM", "getQName()", "getter", traceRet1);
/*     */     }
/* 218 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQName(String value) {
/* 227 */     if (Trace.isOn) {
/* 228 */       Trace.data(this, "com.ibm.mq.headers.MQTM", "setQName(String)", "setter", value);
/*     */     }
/* 230 */     setStringValue(QName, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProcessName() {
/* 239 */     String traceRet1 = getStringValue(ProcessName);
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.data(this, "com.ibm.mq.headers.MQTM", "getProcessName()", "getter", traceRet1);
/*     */     }
/* 243 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProcessName(String value) {
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.data(this, "com.ibm.mq.headers.MQTM", "setProcessName(String)", "setter", value);
/*     */     }
/* 255 */     setStringValue(ProcessName, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTriggerData() {
/* 264 */     String traceRet1 = getStringValue(TriggerData);
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.data(this, "com.ibm.mq.headers.MQTM", "getTriggerData()", "getter", traceRet1);
/*     */     }
/* 268 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTriggerData(String value) {
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.data(this, "com.ibm.mq.headers.MQTM", "setTriggerData(String)", "setter", value);
/*     */     }
/* 280 */     setStringValue(TriggerData, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getApplType() {
/* 289 */     int traceRet1 = getIntValue(ApplType);
/* 290 */     if (Trace.isOn) {
/* 291 */       Trace.data(this, "com.ibm.mq.headers.MQTM", "getApplType()", "getter", 
/* 292 */           Integer.valueOf(traceRet1));
/*     */     }
/* 294 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApplType(int value) {
/* 303 */     if (Trace.isOn) {
/* 304 */       Trace.data(this, "com.ibm.mq.headers.MQTM", "setApplType(int)", "setter", 
/* 305 */           Integer.valueOf(value));
/*     */     }
/* 307 */     setIntValue(ApplType, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApplId() {
/* 316 */     String traceRet1 = getStringValue(ApplId);
/* 317 */     if (Trace.isOn) {
/* 318 */       Trace.data(this, "com.ibm.mq.headers.MQTM", "getApplId()", "getter", traceRet1);
/*     */     }
/* 320 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApplId(String value) {
/* 329 */     if (Trace.isOn) {
/* 330 */       Trace.data(this, "com.ibm.mq.headers.MQTM", "setApplId(String)", "setter", value);
/*     */     }
/* 332 */     setStringValue(ApplId, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEnvData() {
/* 341 */     String traceRet1 = getStringValue(EnvData);
/* 342 */     if (Trace.isOn) {
/* 343 */       Trace.data(this, "com.ibm.mq.headers.MQTM", "getEnvData()", "getter", traceRet1);
/*     */     }
/* 345 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnvData(String value) {
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.data(this, "com.ibm.mq.headers.MQTM", "setEnvData(String)", "setter", value);
/*     */     }
/* 357 */     setStringValue(EnvData, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserData() {
/* 366 */     String traceRet1 = getStringValue(UserData);
/* 367 */     if (Trace.isOn) {
/* 368 */       Trace.data(this, "com.ibm.mq.headers.MQTM", "getUserData()", "getter", traceRet1);
/*     */     }
/* 370 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserData(String value) {
/* 379 */     if (Trace.isOn) {
/* 380 */       Trace.data(this, "com.ibm.mq.headers.MQTM", "setUserData(String)", "setter", value);
/*     */     }
/* 382 */     setStringValue(UserData, value);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQTM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */