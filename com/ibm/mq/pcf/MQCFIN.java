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
/*     */ @Deprecated
/*     */ public class MQCFIN
/*     */   extends PCFParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFIN.java";
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.pcf.MQCFIN", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFIN.java");
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
/*  65 */   static final HeaderType TYPE = new HeaderType("MQCFIN");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int type = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int strucLength = 16;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int parameter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int value;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final com.ibm.mq.headers.pcf.MQCFIN myDelegate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int write(MQMessage message, int parameter, int value) throws IOException {
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.entry("com.ibm.mq.pcf.MQCFIN", "write(MQMessage,int,int)", new Object[] { message, 
/* 105 */             Integer.valueOf(parameter), Integer.valueOf(value) });
/*     */     }
/* 107 */     int traceRet1 = com.ibm.mq.headers.pcf.MQCFIN.write(message, parameter, value);
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.exit("com.ibm.mq.pcf.MQCFIN", "write(MQMessage,int,int)", Integer.valueOf(traceRet1));
/*     */     }
/* 111 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFIN() {
/* 118 */     super(TYPE);
/* 119 */     if (Trace.isOn) {
/* 120 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN", "<init>()");
/*     */     }
/* 122 */     this.myDelegate = (com.ibm.mq.headers.pcf.MQCFIN)this.delegate;
/*     */     
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN", "<init>()");
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
/*     */   public MQCFIN(MQMessage message) throws MQException, IOException {
/* 138 */     this();
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN", "<init>(MQMessage)", new Object[] { message });
/*     */     }
/* 142 */     initialize(message);
/*     */     
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN", "<init>(MQMessage)");
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
/*     */   public MQCFIN(DataInput message, int encoding, int characterSet) throws MQException, IOException {
/* 160 */     this();
/* 161 */     if (Trace.isOn) {
/* 162 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN", "<init>(DataInput,int,int)", new Object[] { message, 
/* 163 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 166 */       this.delegate.read(message, encoding, characterSet);
/*     */     }
/* 168 */     catch (MQDataException mqe) {
/* 169 */       if (Trace.isOn) {
/* 170 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFIN", "<init>(DataInput,int,int)", (Throwable)mqe, 1);
/*     */       }
/*     */       
/* 173 */       MQException traceRet1 = new MQException(mqe.completionCode, mqe.reasonCode, mqe.exceptionSource);
/*     */       
/* 175 */       if (Trace.isOn) {
/* 176 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFIN", "<init>(DataInput,int,int)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 179 */       throw traceRet1;
/*     */     }
/* 181 */     catch (Exception e) {
/* 182 */       if (Trace.isOn) {
/* 183 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFIN", "<init>(DataInput,int,int)", e, 2);
/*     */       }
/* 185 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 186 */       if (Trace.isOn) {
/* 187 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFIN", "<init>(DataInput,int,int)", traceRet2, 2);
/*     */       }
/* 189 */       throw traceRet2;
/*     */     } 
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN", "<init>(DataInput,int,int)");
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
/*     */   public MQCFIN(int parameter, int value) {
/* 204 */     this();
/* 205 */     if (Trace.isOn)
/* 206 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN", "<init>(int,int)", new Object[] {
/* 207 */             Integer.valueOf(parameter), Integer.valueOf(value)
/*     */           }); 
/* 209 */     setParameter(this.parameter = parameter);
/* 210 */     setIntValue(this.value = value);
/*     */     
/* 212 */     if (Trace.isOn) {
/* 213 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN", "<init>(int,int)");
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
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN", "equals(Object)", new Object[] { obj });
/*     */     }
/* 229 */     if (obj != null && obj instanceof MQCFIN) {
/* 230 */       MQCFIN other = (MQCFIN)obj;
/*     */       
/* 232 */       boolean traceRet1 = (other.parameter == this.parameter && other.value == this.value);
/* 233 */       if (Trace.isOn) {
/* 234 */         Trace.exit(this, "com.ibm.mq.pcf.MQCFIN", "equals(Object)", Boolean.valueOf(traceRet1), 1);
/*     */       }
/*     */       
/* 237 */       return traceRet1;
/*     */     } 
/* 239 */     if (Trace.isOn) {
/* 240 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN", "equals(Object)", Boolean.valueOf(false), 2);
/*     */     }
/* 242 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN", "hashCode()");
/*     */     }
/* 253 */     int hashCode = 0;
/* 254 */     hashCode += getParameter();
/* 255 */     hashCode += 31 * getIntValue();
/*     */     
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 260 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 270 */     int traceRet1 = this.myDelegate.getType();
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN", "getType()", "getter", Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 275 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 284 */     int traceRet1 = this.myDelegate.getStrucLength();
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN", "getStrucLength()", "getter", 
/* 287 */           Integer.valueOf(traceRet1));
/*     */     }
/* 289 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameter() {
/* 299 */     int traceRet1 = this.myDelegate.getParameter();
/* 300 */     if (Trace.isOn) {
/* 301 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN", "getParameter()", "getter", 
/* 302 */           Integer.valueOf(traceRet1));
/*     */     }
/* 304 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameter(int value) {
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN", "setParameter(int)", "setter", 
/* 315 */           Integer.valueOf(value));
/*     */     }
/* 317 */     this.myDelegate.setParameter(this.parameter = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIntValue() {
/* 326 */     int traceRet1 = this.myDelegate.getIntValue();
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN", "getIntValue()", "getter", 
/* 329 */           Integer.valueOf(traceRet1));
/*     */     }
/* 331 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIntValue(int value) {
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN", "setIntValue(int)", "setter", 
/* 342 */           Integer.valueOf(value));
/*     */     }
/* 344 */     this.myDelegate.setIntValue(this.value = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 354 */     Object traceRet1 = Integer.valueOf(getIntValue());
/*     */     
/* 356 */     if (Trace.isOn) {
/* 357 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN", "getValue()", "getter", traceRet1);
/*     */     }
/* 359 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 367 */     String traceRet1 = Integer.toString(getIntValue());
/* 368 */     if (Trace.isOn) {
/* 369 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIN", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 371 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readCachedContent() throws MQException, IOException {
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN", "readCachedContent()");
/*     */     }
/* 384 */     this.parameter = getParameter();
/* 385 */     this.value = getIntValue();
/*     */     
/* 387 */     if (Trace.isOn) {
/* 388 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN", "readCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discardCachedContent() {
/* 398 */     if (Trace.isOn) {
/* 399 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN", "discardCachedContent()");
/*     */     }
/* 401 */     this.parameter = 0;
/* 402 */     this.value = 0;
/*     */     
/* 404 */     if (Trace.isOn) {
/* 405 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN", "discardCachedContent()");
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
/* 416 */     if (Trace.isOn) {
/* 417 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIN", "writeCachedContent()");
/*     */     }
/* 419 */     setParameter(this.parameter);
/* 420 */     setIntValue(this.value);
/*     */     
/* 422 */     if (Trace.isOn)
/* 423 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIN", "writeCachedContent()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\MQCFIN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */