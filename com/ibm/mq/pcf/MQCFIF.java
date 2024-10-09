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
/*     */ public class MQCFIF
/*     */   extends PCFFilterParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFIF.java";
/*     */   public static final int SIZE = 20;
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.mq.pcf.MQCFIF", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFIF.java");
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
/*  69 */   static final HeaderType TYPE = new HeaderType("MQCFIF");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int type = 13;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int strucLength = 20;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int parameter;
/*     */ 
/*     */ 
/*     */   
/*     */   public int operator;
/*     */ 
/*     */ 
/*     */   
/*     */   public int value;
/*     */ 
/*     */ 
/*     */   
/*     */   private final com.ibm.mq.headers.pcf.MQCFIF myDelegate;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFIF() {
/* 102 */     super(TYPE);
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIF", "<init>()");
/*     */     }
/* 106 */     this.myDelegate = (com.ibm.mq.headers.pcf.MQCFIF)this.delegate;
/*     */     
/* 108 */     if (Trace.isOn) {
/* 109 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIF", "<init>()");
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
/*     */   public MQCFIF(MQMessage message) throws MQException, IOException {
/* 122 */     this();
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIF", "<init>(MQMessage)", new Object[] { message });
/*     */     }
/* 126 */     initialize(message);
/*     */     
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIF", "<init>(MQMessage)");
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
/*     */   public MQCFIF(DataInput message, int encoding, int characterSet) throws MQException, IOException {
/* 144 */     this();
/* 145 */     if (Trace.isOn) {
/* 146 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIF", "<init>(DataInput,int,int)", new Object[] { message, 
/* 147 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 150 */       this.delegate.read(message, encoding, characterSet);
/*     */     }
/* 152 */     catch (MQDataException mqe) {
/* 153 */       if (Trace.isOn) {
/* 154 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFIF", "<init>(DataInput,int,int)", (Throwable)mqe, 1);
/*     */       }
/*     */       
/* 157 */       MQException traceRet1 = new MQException(mqe.completionCode, mqe.reasonCode, mqe.exceptionSource);
/*     */       
/* 159 */       if (Trace.isOn) {
/* 160 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFIF", "<init>(DataInput,int,int)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 163 */       throw traceRet1;
/*     */     }
/* 165 */     catch (Exception e) {
/* 166 */       if (Trace.isOn) {
/* 167 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFIF", "<init>(DataInput,int,int)", e, 2);
/*     */       }
/* 169 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 170 */       if (Trace.isOn) {
/* 171 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFIF", "<init>(DataInput,int,int)", traceRet2, 2);
/*     */       }
/* 173 */       throw traceRet2;
/*     */     } 
/* 175 */     if (Trace.isOn) {
/* 176 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIF", "<init>(DataInput,int,int)");
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
/*     */   public MQCFIF(int parameter, int operator, int value) {
/* 189 */     this();
/* 190 */     if (Trace.isOn)
/* 191 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIF", "<init>(int,int,int)", new Object[] {
/* 192 */             Integer.valueOf(parameter), Integer.valueOf(operator), Integer.valueOf(value)
/*     */           }); 
/* 194 */     setParameter(this.parameter = parameter);
/* 195 */     setOperator(this.operator = operator);
/* 196 */     setFilterValue(this.value = value);
/*     */     
/* 198 */     if (Trace.isOn) {
/* 199 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIF", "<init>(int,int,int)");
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
/*     */   public boolean equals(Object obj) {
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIF", "equals(Object)", new Object[] { obj });
/*     */     }
/* 217 */     if (obj != null && obj instanceof MQCFIF) {
/* 218 */       MQCFIF other = (MQCFIF)obj;
/*     */       
/* 220 */       boolean traceRet1 = (other.parameter == this.parameter && other.operator == this.operator && other.value == this.value);
/*     */ 
/*     */       
/* 223 */       if (Trace.isOn) {
/* 224 */         Trace.exit(this, "com.ibm.mq.pcf.MQCFIF", "equals(Object)", 
/* 225 */             Boolean.valueOf(traceRet1), 1);
/*     */       }
/* 227 */       return traceRet1;
/*     */     } 
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIF", "equals(Object)", Boolean.valueOf(false), 2);
/*     */     }
/* 232 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIF", "hashCode()");
/*     */     }
/* 243 */     int hashCode = 0;
/* 244 */     hashCode += getParameter();
/* 245 */     hashCode += 31 * getOperator();
/* 246 */     hashCode += 37 * getFilterValue();
/* 247 */     hashCode += getStringValue().hashCode();
/*     */     
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIF", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 252 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 262 */     int traceRet1 = this.myDelegate.getType();
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIF", "getType()", "getter", Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 267 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 276 */     int traceRet1 = this.myDelegate.getStrucLength();
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIF", "getStrucLength()", "getter", 
/* 279 */           Integer.valueOf(traceRet1));
/*     */     }
/* 281 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameter() {
/* 291 */     int traceRet1 = this.myDelegate.getParameter();
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIF", "getParameter()", "getter", 
/* 294 */           Integer.valueOf(traceRet1));
/*     */     }
/* 296 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameter(int value) {
/* 305 */     if (Trace.isOn) {
/* 306 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIF", "setParameter(int)", "setter", 
/* 307 */           Integer.valueOf(value));
/*     */     }
/* 309 */     this.myDelegate.setParameter(this.parameter = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOperator() {
/* 319 */     int traceRet1 = this.myDelegate.getOperator();
/* 320 */     if (Trace.isOn) {
/* 321 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIF", "getOperator()", "getter", 
/* 322 */           Integer.valueOf(traceRet1));
/*     */     }
/* 324 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOperator(int value) {
/* 333 */     if (Trace.isOn) {
/* 334 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIF", "setOperator(int)", "setter", 
/* 335 */           Integer.valueOf(value));
/*     */     }
/* 337 */     this.myDelegate.setOperator(this.operator = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFilterValue() {
/* 346 */     int traceRet1 = this.myDelegate.getFilterValue();
/* 347 */     if (Trace.isOn) {
/* 348 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIF", "getFilterValue()", "getter", 
/* 349 */           Integer.valueOf(traceRet1));
/*     */     }
/* 351 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilterValue(int value) {
/* 360 */     if (Trace.isOn) {
/* 361 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIF", "setFilterValue(int)", "setter", 
/* 362 */           Integer.valueOf(value));
/*     */     }
/* 364 */     this.myDelegate.setFilterValue(this.value = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 372 */     Object traceRet1 = Integer.valueOf(getFilterValue());
/* 373 */     if (Trace.isOn) {
/* 374 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIF", "getValue()", "getter", traceRet1);
/*     */     }
/* 376 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 384 */     String traceRet1 = Integer.toString(getFilterValue());
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIF", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 388 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readCachedContent() throws MQException, IOException {
/* 398 */     if (Trace.isOn) {
/* 399 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIF", "readCachedContent()");
/*     */     }
/* 401 */     this.parameter = getParameter();
/* 402 */     this.operator = getOperator();
/* 403 */     this.value = getFilterValue();
/*     */     
/* 405 */     if (Trace.isOn) {
/* 406 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIF", "readCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discardCachedContent() {
/* 416 */     if (Trace.isOn) {
/* 417 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIF", "discardCachedContent()");
/*     */     }
/* 419 */     this.parameter = 0;
/* 420 */     this.operator = 0;
/* 421 */     this.value = 0;
/*     */     
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIF", "discardCachedContent()");
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
/* 435 */     if (Trace.isOn) {
/* 436 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIF", "writeCachedContent()");
/*     */     }
/* 438 */     setParameter(this.parameter);
/* 439 */     setOperator(this.operator);
/* 440 */     setFilterValue(this.value);
/*     */     
/* 442 */     if (Trace.isOn)
/* 443 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIF", "writeCachedContent()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\MQCFIF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */