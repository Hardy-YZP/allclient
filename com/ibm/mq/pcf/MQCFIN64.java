/*     */ package com.ibm.mq.pcf;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.mq.MQMessage;
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
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
/*     */ @Deprecated
/*     */ public class MQCFIN64
/*     */   extends PCFParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFIN64.java";
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.mq.pcf.MQCFIN64", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFIN64.java");
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
/*  65 */   static final HeaderType TYPE = new HeaderType("MQCFIN64");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int type = 23;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int strucLength = 24;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int parameter;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int reserved;
/*     */ 
/*     */ 
/*     */   
/*     */   public long value;
/*     */ 
/*     */ 
/*     */   
/*     */   private final com.ibm.mq.headers.pcf.MQCFIN64 myDelegate;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFIN64() {
/*  99 */     super(TYPE);
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN64", "<init>()");
/*     */     }
/* 103 */     this.myDelegate = (com.ibm.mq.headers.pcf.MQCFIN64)this.delegate;
/*     */     
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN64", "<init>()");
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
/*     */   public MQCFIN64(MQMessage message) throws MQException, IOException {
/* 119 */     this();
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN64", "<init>(MQMessage)", new Object[] { message });
/*     */     }
/* 123 */     initialize(message);
/*     */     
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN64", "<init>(MQMessage)");
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
/*     */   public MQCFIN64(DataInput message, int encoding, int characterSet) throws MQException, IOException {
/* 142 */     this();
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN64", "<init>(DataInput,int,int)", new Object[] { message, 
/* 145 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 148 */       this.delegate.read(message, encoding, characterSet);
/*     */     }
/* 150 */     catch (MQDataException mqe) {
/* 151 */       if (Trace.isOn) {
/* 152 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFIN64", "<init>(DataInput,int,int)", (Throwable)mqe, 1);
/*     */       }
/*     */       
/* 155 */       MQException traceRet1 = new MQException(mqe.completionCode, mqe.reasonCode, mqe.exceptionSource);
/*     */       
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFIN64", "<init>(DataInput,int,int)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 161 */       throw traceRet1;
/*     */     }
/* 163 */     catch (Exception e) {
/* 164 */       if (Trace.isOn) {
/* 165 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFIN64", "<init>(DataInput,int,int)", e, 2);
/*     */       }
/* 167 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 168 */       if (Trace.isOn) {
/* 169 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFIN64", "<init>(DataInput,int,int)", traceRet2, 2);
/*     */       }
/*     */       
/* 172 */       throw traceRet2;
/*     */     } 
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN64", "<init>(DataInput,int,int)");
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
/*     */   public MQCFIN64(int parameter, long value) {
/* 187 */     this();
/* 188 */     if (Trace.isOn)
/* 189 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN64", "<init>(int,long)", new Object[] {
/* 190 */             Integer.valueOf(parameter), Long.valueOf(value)
/*     */           }); 
/* 192 */     setParameter(this.parameter = parameter);
/* 193 */     setLongValue(this.value = value);
/*     */     
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN64", "<init>(int,long)");
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
/*     */   public boolean equals(Object obj) {
/* 209 */     if (Trace.isOn) {
/* 210 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN64", "equals(Object)", new Object[] { obj });
/*     */     }
/* 212 */     if (obj != null && obj instanceof MQCFIN64) {
/* 213 */       MQCFIN64 other = (MQCFIN64)obj;
/*     */       
/* 215 */       boolean traceRet1 = (other.parameter == this.parameter && other.value == this.value);
/* 216 */       if (Trace.isOn) {
/* 217 */         Trace.exit(this, "com.ibm.mq.pcf.MQCFIN64", "equals(Object)", Boolean.valueOf(traceRet1), 1);
/*     */       }
/*     */       
/* 220 */       return traceRet1;
/*     */     } 
/* 222 */     if (Trace.isOn) {
/* 223 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN64", "equals(Object)", Boolean.valueOf(false), 2);
/*     */     }
/* 225 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN64", "hashCode()");
/*     */     }
/* 236 */     int hashCode = 0;
/* 237 */     hashCode += getParameter();
/* 238 */     hashCode = (int)(hashCode + 31L * getLongValue());
/*     */     
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN64", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 243 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 253 */     int traceRet1 = this.myDelegate.getType();
/* 254 */     if (Trace.isOn) {
/* 255 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN64", "getType()", "getter", 
/* 256 */           Integer.valueOf(traceRet1));
/*     */     }
/* 258 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 267 */     int traceRet1 = this.myDelegate.getStrucLength();
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN64", "getStrucLength()", "getter", 
/* 270 */           Integer.valueOf(traceRet1));
/*     */     }
/* 272 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameter() {
/* 282 */     int traceRet1 = this.myDelegate.getParameter();
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN64", "getParameter()", "getter", 
/* 285 */           Integer.valueOf(traceRet1));
/*     */     }
/* 287 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameter(int value) {
/* 296 */     if (Trace.isOn) {
/* 297 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN64", "setParameter(int)", "setter", 
/* 298 */           Integer.valueOf(value));
/*     */     }
/* 300 */     this.myDelegate.setParameter(this.parameter = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReserved() {
/* 308 */     int traceRet1 = this.myDelegate.getReserved();
/* 309 */     if (Trace.isOn) {
/* 310 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN64", "getReserved()", "getter", 
/* 311 */           Integer.valueOf(traceRet1));
/*     */     }
/* 313 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReserved(int value) {
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN64", "setReserved(int)", "setter", 
/* 323 */           Integer.valueOf(value));
/*     */     }
/* 325 */     this.myDelegate.setReserved(this.reserved = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLongValue() {
/* 334 */     long traceRet1 = this.myDelegate.getLongValue();
/* 335 */     if (Trace.isOn) {
/* 336 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN64", "getLongValue()", "getter", 
/* 337 */           Long.valueOf(traceRet1));
/*     */     }
/* 339 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLongValue(long value) {
/* 348 */     if (Trace.isOn) {
/* 349 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN64", "setLongValue(long)", "setter", 
/* 350 */           Long.valueOf(value));
/*     */     }
/* 352 */     this.myDelegate.setLongValue(this.value = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 360 */     Object traceRet1 = Long.valueOf(getLongValue());
/* 361 */     if (Trace.isOn) {
/* 362 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN64", "getValue()", "getter", traceRet1);
/*     */     }
/* 364 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 372 */     String traceRet1 = Long.toString(getLongValue());
/* 373 */     if (Trace.isOn) {
/* 374 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN64", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 376 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readCachedContent() throws MQException, IOException {
/* 386 */     if (Trace.isOn) {
/* 387 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN64", "readCachedContent()");
/*     */     }
/* 389 */     this.parameter = getParameter();
/* 390 */     this.reserved = getReserved();
/* 391 */     this.value = getLongValue();
/*     */     
/* 393 */     if (Trace.isOn) {
/* 394 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN64", "readCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discardCachedContent() {
/* 404 */     if (Trace.isOn) {
/* 405 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN64", "discardCachedContent()");
/*     */     }
/* 407 */     this.parameter = 0;
/* 408 */     this.reserved = 0;
/* 409 */     this.value = 0L;
/*     */     
/* 411 */     if (Trace.isOn) {
/* 412 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN64", "discardCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeCachedContent() throws IOException {
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN64", "writeCachedContent()");
/*     */     }
/* 426 */     setParameter(this.parameter);
/* 427 */     setReserved(this.reserved);
/* 428 */     setLongValue(this.value);
/*     */     
/* 430 */     if (Trace.isOn) {
/* 431 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN64", "writeCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeaderVersion() {
/* 441 */     int traceRet1 = this.myDelegate.getHeaderVersion();
/* 442 */     if (Trace.isOn) {
/* 443 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN64", "getHeaderVersion()", "getter", 
/* 444 */           Integer.valueOf(traceRet1));
/*     */     }
/* 446 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\MQCFIN64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */