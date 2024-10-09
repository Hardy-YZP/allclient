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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQWIH
/*     */   extends Header
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQWIH.java";
/*     */   
/*     */   static {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.data("com.ibm.mq.headers.MQWIH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/MQWIH.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   static final HeaderType TYPE = new HeaderType("MQWIH");
/*     */   
/*  68 */   static final HeaderField StrucId = TYPE.addMQChar("StrucId", "WIH ");
/*  69 */   static final HeaderField Version = TYPE.addMQLong("Version", 1);
/*  70 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 120);
/*  71 */   static final HeaderField Encoding = TYPE.addMQLong("Encoding");
/*  72 */   static final HeaderField CodedCharSetId = TYPE.addMQLong("CodedCharSetId");
/*  73 */   static final HeaderField Format = TYPE.addMQChar("Format", "        ");
/*  74 */   static final HeaderField Flags = TYPE.addMQLong("Flags");
/*  75 */   static final HeaderField ServiceName = TYPE.addMQChar("ServiceName", 32);
/*  76 */   static final HeaderField ServiceStep = TYPE.addMQChar("ServiceStep", 8);
/*  77 */   static final HeaderField MsgToken = TYPE.addMQByte("MsgToken", 16);
/*  78 */   static final HeaderField Reserved = TYPE.addMQChar("Reserved", 32);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQWIH() {
/*  84 */     super(TYPE);
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.entry(this, "com.ibm.mq.headers.MQWIH", "<init>()");
/*     */     }
/*  88 */     if (Trace.isOn) {
/*  89 */       Trace.exit(this, "com.ibm.mq.headers.MQWIH", "<init>()");
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
/*     */   public MQWIH(DataInput message) throws MQDataException, IOException {
/* 102 */     this();
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.entry(this, "com.ibm.mq.headers.MQWIH", "<init>(DataInput)", new Object[] { message });
/*     */     }
/* 106 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.exit(this, "com.ibm.mq.headers.MQWIH", "<init>(DataInput)");
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
/*     */   public MQWIH(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 125 */     this();
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.entry(this, "com.ibm.mq.headers.MQWIH", "<init>(DataInput,int,int)", new Object[] { message, 
/* 128 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 131 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 133 */     catch (MQDataException mde) {
/* 134 */       if (Trace.isOn) {
/* 135 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQWIH", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/*     */       
/* 138 */       if (Trace.isOn) {
/* 139 */         Trace.throwing(this, "com.ibm.mq.headers.MQWIH", "<init>(DataInput,int,int)", mde, 1);
/*     */       }
/* 141 */       throw mde;
/*     */     }
/* 143 */     catch (IOException ioe) {
/* 144 */       if (Trace.isOn) {
/* 145 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQWIH", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 147 */       if (Trace.isOn) {
/* 148 */         Trace.throwing(this, "com.ibm.mq.headers.MQWIH", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 150 */       throw ioe;
/*     */     }
/* 152 */     catch (Exception e) {
/* 153 */       if (Trace.isOn) {
/* 154 */         Trace.catchBlock(this, "com.ibm.mq.headers.MQWIH", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/* 156 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.throwing(this, "com.ibm.mq.headers.MQWIH", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 161 */       throw traceRet1;
/*     */     } 
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.exit(this, "com.ibm.mq.headers.MQWIH", "<init>(DataInput,int,int)");
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
/* 175 */     String traceRet1 = getStringValue(StrucId);
/* 176 */     if (Trace.isOn) {
/* 177 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "getStrucId()", "getter", traceRet1);
/*     */     }
/* 179 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 188 */     int traceRet1 = getIntValue(Version);
/* 189 */     if (Trace.isOn) {
/* 190 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "getVersion()", "getter", 
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
/*     */   public int getStrucLength() {
/* 202 */     int traceRet1 = getIntValue(StrucLength);
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "getStrucLength()", "getter", 
/* 205 */           Integer.valueOf(traceRet1));
/*     */     }
/* 207 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEncoding() {
/* 216 */     int traceRet1 = getIntValue(Encoding);
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "getEncoding()", "getter", 
/* 219 */           Integer.valueOf(traceRet1));
/*     */     }
/* 221 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncoding(int value) {
/* 230 */     if (Trace.isOn) {
/* 231 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "setEncoding(int)", "setter", 
/* 232 */           Integer.valueOf(value));
/*     */     }
/* 234 */     setIntValue(Encoding, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 243 */     int traceRet1 = getIntValue(CodedCharSetId);
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "getCodedCharSetId()", "getter", 
/* 246 */           Integer.valueOf(traceRet1));
/*     */     }
/* 248 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int value) {
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "setCodedCharSetId(int)", "setter", 
/* 259 */           Integer.valueOf(value));
/*     */     }
/* 261 */     setIntValue(CodedCharSetId, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 270 */     String traceRet1 = getStringValue(Format);
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "getFormat()", "getter", traceRet1);
/*     */     }
/* 274 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(String value) {
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "setFormat(String)", "setter", value);
/*     */     }
/* 286 */     setStringValue(Format, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlags() {
/* 295 */     int traceRet1 = getIntValue(Flags);
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "getFlags()", "getter", 
/* 298 */           Integer.valueOf(traceRet1));
/*     */     }
/* 300 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlags(int value) {
/* 309 */     if (Trace.isOn) {
/* 310 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "setFlags(int)", "setter", 
/* 311 */           Integer.valueOf(value));
/*     */     }
/* 313 */     setIntValue(Flags, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getServiceName() {
/* 322 */     String traceRet1 = getStringValue(ServiceName);
/* 323 */     if (Trace.isOn) {
/* 324 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "getServiceName()", "getter", traceRet1);
/*     */     }
/* 326 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServiceName(String value) {
/* 335 */     if (Trace.isOn) {
/* 336 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "setServiceName(String)", "setter", value);
/*     */     }
/* 338 */     setStringValue(ServiceName, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getServiceStep() {
/* 347 */     String traceRet1 = getStringValue(ServiceStep);
/* 348 */     if (Trace.isOn) {
/* 349 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "getServiceStep()", "getter", traceRet1);
/*     */     }
/* 351 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServiceStep(String value) {
/* 360 */     if (Trace.isOn) {
/* 361 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "setServiceStep(String)", "setter", value);
/*     */     }
/* 363 */     setStringValue(ServiceStep, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getMsgToken() {
/* 372 */     byte[] traceRet1 = getBytesValue(MsgToken);
/* 373 */     if (Trace.isOn) {
/* 374 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "getMsgToken()", "getter", traceRet1);
/*     */     }
/* 376 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMsgToken(byte[] value) {
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "setMsgToken(byte [ ])", "setter", value);
/*     */     }
/* 388 */     setBytesValue(MsgToken, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReserved() {
/* 396 */     String traceRet1 = getStringValue(Reserved);
/* 397 */     if (Trace.isOn) {
/* 398 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "getReserved()", "getter", traceRet1);
/*     */     }
/* 400 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReserved(String value) {
/* 408 */     if (Trace.isOn) {
/* 409 */       Trace.data(this, "com.ibm.mq.headers.MQWIH", "setReserved(String)", "setter", value);
/*     */     }
/* 411 */     setStringValue(Reserved, value);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\MQWIH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */