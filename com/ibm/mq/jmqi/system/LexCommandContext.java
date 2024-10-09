/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
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
/*     */ public class LexCommandContext
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/LexCommandContext.java";
/*     */   private static final int SIZEOF_COMMAND = 4;
/*     */   private static final int SIZEOF_EVENT_ORIGIN = 4;
/*     */   private static final int SIZEOF_EVENT_QMGR = 48;
/*     */   private static final int SIZEOF_ACCOUNTING_TOKEN = 32;
/*     */   private static final int SIZEOF_APPL_IDENTITY_DATA = 32;
/*     */   private static final int SIZEOF_APPL_TYPE = 4;
/*     */   private static final int SIZEOF_APPL_NAME = 28;
/*     */   private static final int SIZEOF_APPL_ORIGIN_DATA = 4;
/*     */   private int command;
/*     */   private int eventOrigin;
/*     */   private String eventQMgr;
/*  68 */   private byte[] accountingToken = new byte[32];
/*     */   
/*     */   private String applIdentityData;
/*     */   
/*     */   private int applType;
/*     */   
/*     */   private String applName;
/*     */   
/*     */   private String applOriginData;
/*     */ 
/*     */   
/*     */   public LexCommandContext(JmqiEnvironment env) {
/*  80 */     super(env);
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LexCommandContext", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LexCommandContext", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getFieldSizeV1(int ptrSize) {
/*  96 */     if (Trace.isOn)
/*  97 */       Trace.entry("com.ibm.mq.jmqi.system.LexCommandContext", "getFieldSizeV1(int)", new Object[] {
/*  98 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 100 */     int size = 0;
/* 101 */     size += 4;
/* 102 */     size += 4;
/* 103 */     size += 48;
/* 104 */     size += 32;
/* 105 */     size += 32;
/* 106 */     size += 4;
/* 107 */     size += 28;
/* 108 */     size += 4;
/*     */     
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit("com.ibm.mq.jmqi.system.LexCommandContext", "getFieldSizeV1(int)", 
/* 112 */           Integer.valueOf(size));
/*     */     }
/* 114 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV1(int ptrSize) {
/* 122 */     if (Trace.isOn)
/* 123 */       Trace.entry("com.ibm.mq.jmqi.system.LexCommandContext", "getSizeV1(int)", new Object[] {
/* 124 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 126 */     int size = getFieldSizeV1(ptrSize);
/*     */     
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.exit("com.ibm.mq.jmqi.system.LexCommandContext", "getSizeV1(int)", 
/* 130 */           Integer.valueOf(size));
/*     */     }
/* 132 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.entry("com.ibm.mq.jmqi.system.LexCommandContext", "getSize(JmqiEnvironment,int,int)", new Object[] { env, 
/* 145 */             Integer.valueOf(version), Integer.valueOf(ptrSize) });
/*     */     }
/* 147 */     int traceRet1 = getSizeV1(ptrSize);
/*     */     
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.exit("com.ibm.mq.jmqi.system.LexCommandContext", "getSize(JmqiEnvironment,int,int)", 
/* 151 */           Integer.valueOf(traceRet1));
/*     */     }
/* 153 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 164 */     if (Trace.isOn)
/* 165 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LexCommandContext", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 166 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 168 */     int version = 1;
/* 169 */     int size = getSize(this.env, version, ptrSize);
/*     */     
/* 171 */     if (Trace.isOn) {
/* 172 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LexCommandContext", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 173 */           Integer.valueOf(size));
/*     */     }
/* 175 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCommand() {
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexCommandContext", "getCommand()", "getter", 
/* 184 */           Integer.valueOf(this.command));
/*     */     }
/* 186 */     return this.command;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommand(int command) {
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexCommandContext", "setCommand(int)", "setter", 
/* 195 */           Integer.valueOf(command));
/*     */     }
/* 197 */     this.command = command;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEventOrigin() {
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexCommandContext", "getEventOrigin()", "getter", 
/* 206 */           Integer.valueOf(this.eventOrigin));
/*     */     }
/* 208 */     return this.eventOrigin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEventOrigin(int eventOrigin) {
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexCommandContext", "setEventOrigin(int)", "setter", 
/* 217 */           Integer.valueOf(eventOrigin));
/*     */     }
/* 219 */     this.eventOrigin = eventOrigin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEventQMgr() {
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexCommandContext", "getEventQMgr()", "getter", this.eventQMgr);
/*     */     }
/*     */     
/* 230 */     return this.eventQMgr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEventQMgr(String eventQMgr) {
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexCommandContext", "setEventQMgr(String)", "setter", eventQMgr);
/*     */     }
/*     */     
/* 241 */     this.eventQMgr = eventQMgr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getAccountingToken() {
/* 248 */     if (Trace.isOn) {
/* 249 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexCommandContext", "getAccountingToken()", "getter", this.accountingToken);
/*     */     }
/*     */     
/* 252 */     return this.accountingToken;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAccountingToken(byte[] accountingToken) {
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexCommandContext", "setAccountingToken(byte [ ])", "setter", accountingToken);
/*     */     }
/*     */     
/* 263 */     this.accountingToken = accountingToken;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApplIdentityData() {
/* 270 */     if (Trace.isOn) {
/* 271 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexCommandContext", "getApplIdentityData()", "getter", this.applIdentityData);
/*     */     }
/*     */     
/* 274 */     return this.applIdentityData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApplIdentityData(String applIdentityData) {
/* 281 */     if (Trace.isOn) {
/* 282 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexCommandContext", "setApplIdentityData(String)", "setter", applIdentityData);
/*     */     }
/*     */     
/* 285 */     this.applIdentityData = applIdentityData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getApplType() {
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexCommandContext", "getApplType()", "getter", 
/* 294 */           Integer.valueOf(this.applType));
/*     */     }
/* 296 */     return this.applType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApplType(int applType) {
/* 303 */     if (Trace.isOn) {
/* 304 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexCommandContext", "setApplType(int)", "setter", 
/* 305 */           Integer.valueOf(applType));
/*     */     }
/* 307 */     this.applType = applType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApplName() {
/* 314 */     if (Trace.isOn) {
/* 315 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexCommandContext", "getApplName()", "getter", this.applName);
/*     */     }
/*     */     
/* 318 */     return this.applName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApplName(String applName) {
/* 325 */     if (Trace.isOn) {
/* 326 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexCommandContext", "setApplName(String)", "setter", applName);
/*     */     }
/*     */     
/* 329 */     this.applName = applName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApplOriginData() {
/* 336 */     if (Trace.isOn) {
/* 337 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexCommandContext", "getApplOriginData()", "getter", this.applOriginData);
/*     */     }
/*     */     
/* 340 */     return this.applOriginData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setApplOriginData(String applOriginData) {
/* 347 */     if (Trace.isOn) {
/* 348 */       Trace.data(this, "com.ibm.mq.jmqi.system.LexCommandContext", "setApplOriginData(String)", "setter", applOriginData);
/*     */     }
/*     */     
/* 351 */     this.applOriginData = applOriginData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 360 */     if (Trace.isOn) {
/* 361 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LexCommandContext", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 363 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 365 */     int pos = offset;
/* 366 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 368 */     dc.writeI32(this.command, buffer, pos, swap);
/* 369 */     pos += 4;
/*     */     
/* 371 */     dc.writeI32(this.eventOrigin, buffer, pos, swap);
/* 372 */     pos += 4;
/*     */     
/* 374 */     dc.writeMQField(this.eventQMgr, buffer, pos, 48, cp, tls);
/* 375 */     pos += 48;
/*     */     
/* 377 */     System.arraycopy(this.accountingToken, 0, buffer, pos, 32);
/* 378 */     pos += 32;
/*     */     
/* 380 */     dc.writeField(this.applIdentityData, buffer, pos, 32, cp, tls);
/* 381 */     pos += 32;
/*     */     
/* 383 */     dc.writeI32(this.applType, buffer, pos, swap);
/* 384 */     pos += 4;
/*     */     
/* 386 */     dc.writeField(this.applName, buffer, pos, 28, cp, tls);
/* 387 */     pos += 28;
/*     */     
/* 389 */     dc.writeField(this.applOriginData, buffer, pos, 4, cp, tls);
/* 390 */     pos += 4;
/*     */ 
/*     */ 
/*     */     
/* 394 */     int traceRet1 = pos - offset;
/*     */     
/* 396 */     if (Trace.isOn) {
/* 397 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LexCommandContext", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 398 */           Integer.valueOf(traceRet1));
/*     */     }
/* 400 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 409 */     if (Trace.isOn) {
/* 410 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LexCommandContext", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 412 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 414 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 415 */     int pos = offset;
/*     */     
/* 417 */     this.command = dc.readI32(buffer, pos, swap);
/* 418 */     pos += 4;
/*     */     
/* 420 */     this.eventOrigin = dc.readI32(buffer, pos, swap);
/* 421 */     pos += 4;
/*     */     
/* 423 */     this.eventQMgr = dc.readMQField(buffer, pos, 48, cp, tls);
/* 424 */     pos += 48;
/*     */     
/* 426 */     System.arraycopy(buffer, pos, this.accountingToken, 0, 32);
/* 427 */     pos += 32;
/*     */     
/* 429 */     this.applIdentityData = dc.readField(buffer, pos, 32, cp, tls);
/* 430 */     pos += 32;
/*     */     
/* 432 */     this.applType = dc.readI32(buffer, pos, swap);
/* 433 */     pos += 4;
/*     */     
/* 435 */     this.applName = dc.readField(buffer, pos, 28, cp, tls);
/* 436 */     pos += 28;
/*     */     
/* 438 */     this.applOriginData = dc.readField(buffer, pos, 4, cp, tls);
/* 439 */     pos += 4;
/* 440 */     int traceRet1 = pos - offset;
/*     */ 
/*     */ 
/*     */     
/* 444 */     if (Trace.isOn) {
/* 445 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LexCommandContext", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 446 */           Integer.valueOf(traceRet1));
/*     */     }
/* 448 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFieldsToFormatter(JmqiStructureFormatter fmt) {
/* 459 */     fmt.add("command", this.command);
/* 460 */     fmt.add("eventOrigin", this.eventOrigin);
/* 461 */     fmt.add("eventQMgr", this.eventQMgr);
/* 462 */     fmt.add("accountingToken", this.accountingToken);
/* 463 */     fmt.add("applIdentityData", this.applIdentityData);
/* 464 */     fmt.add("applType", this.applType);
/* 465 */     fmt.add("applName", this.applName);
/* 466 */     fmt.add("applOriginData", this.applOriginData);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\LexCommandContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */