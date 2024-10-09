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
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class MQCFSF
/*     */   extends PCFFilterParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFSF.java";
/*     */   
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.mq.pcf.MQCFSF", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFSF.java");
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
/*  67 */   static final HeaderType TYPE = new HeaderType("MQCFSF");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int type = 14;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public int strucLength = 24;
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
/*     */   public int codedCharSetId;
/*     */ 
/*     */ 
/*     */   
/*     */   public int filterValueLength;
/*     */ 
/*     */ 
/*     */   
/*     */   public String filterValue;
/*     */ 
/*     */   
/*     */   private final com.ibm.mq.headers.pcf.MQCFSF myDelegate;
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFSF() {
/* 108 */     super(TYPE);
/* 109 */     if (Trace.isOn) {
/* 110 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSF", "<init>()");
/*     */     }
/* 112 */     this.myDelegate = (com.ibm.mq.headers.pcf.MQCFSF)this.delegate;
/*     */     
/* 114 */     if (Trace.isOn) {
/* 115 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSF", "<init>()");
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
/*     */   public MQCFSF(MQMessage message) throws MQException, IOException {
/* 128 */     this();
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSF", "<init>(MQMessage)", new Object[] { message });
/*     */     }
/* 132 */     initialize(message);
/*     */     
/* 134 */     if (Trace.isOn) {
/* 135 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSF", "<init>(MQMessage)");
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
/*     */   public MQCFSF(DataInput message, int encoding, int characterSet) throws MQException, IOException {
/* 150 */     this();
/* 151 */     if (Trace.isOn) {
/* 152 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSF", "<init>(DataInput,int,int)", new Object[] { message, 
/* 153 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 156 */       this.delegate.read(message, encoding, characterSet);
/*     */     }
/* 158 */     catch (MQDataException mqe) {
/* 159 */       if (Trace.isOn) {
/* 160 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFSF", "<init>(DataInput,int,int)", (Throwable)mqe, 1);
/*     */       }
/*     */       
/* 163 */       MQException traceRet1 = new MQException(mqe.completionCode, mqe.reasonCode, mqe.exceptionSource);
/*     */       
/* 165 */       if (Trace.isOn) {
/* 166 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFSF", "<init>(DataInput,int,int)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 169 */       throw traceRet1;
/*     */     }
/* 171 */     catch (Exception e) {
/* 172 */       if (Trace.isOn) {
/* 173 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFSF", "<init>(DataInput,int,int)", e, 2);
/*     */       }
/* 175 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 176 */       if (Trace.isOn) {
/* 177 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFSF", "<init>(DataInput,int,int)", traceRet2, 2);
/*     */       }
/* 179 */       throw traceRet2;
/*     */     } 
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSF", "<init>(DataInput,int,int)");
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
/*     */   public MQCFSF(int parameter, int operator, String filterValue) {
/* 195 */     this();
/* 196 */     if (Trace.isOn)
/* 197 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSF", "<init>(int,int,String)", new Object[] {
/* 198 */             Integer.valueOf(parameter), Integer.valueOf(operator), filterValue
/*     */           }); 
/* 200 */     setParameter(this.parameter = parameter);
/* 201 */     setOperator(this.operator = operator);
/* 202 */     setFilterValue(this.filterValue = filterValue);
/*     */     
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSF", "<init>(int,int,String)");
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
/*     */   public int getType() {
/* 217 */     int traceRet1 = this.myDelegate.getType();
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSF", "getType()", "getter", Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 222 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 231 */     int traceRet1 = this.myDelegate.getStrucLength();
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSF", "getStrucLength()", "getter", 
/* 234 */           Integer.valueOf(traceRet1));
/*     */     }
/* 236 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameter() {
/* 246 */     int traceRet1 = this.myDelegate.getParameter();
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSF", "getParameter()", "getter", 
/* 249 */           Integer.valueOf(traceRet1));
/*     */     }
/* 251 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameter(int value) {
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSF", "setParameter(int)", "setter", 
/* 262 */           Integer.valueOf(value));
/*     */     }
/* 264 */     this.myDelegate.setParameter(this.parameter = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOperator() {
/* 274 */     int traceRet1 = this.myDelegate.getOperator();
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSF", "getOperator()", "getter", 
/* 277 */           Integer.valueOf(traceRet1));
/*     */     }
/* 279 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOperator(int value) {
/* 288 */     if (Trace.isOn) {
/* 289 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSF", "setOperator(int)", "setter", 
/* 290 */           Integer.valueOf(value));
/*     */     }
/* 292 */     this.myDelegate.setOperator(this.operator = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 301 */     int traceRet1 = this.myDelegate.getCodedCharSetId();
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSF", "getCodedCharSetId()", "getter", 
/* 304 */           Integer.valueOf(traceRet1));
/*     */     }
/* 306 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int value) {
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSF", "setCodedCharSetId(int)", "setter", 
/* 317 */           Integer.valueOf(value));
/*     */     }
/* 319 */     this.myDelegate.setCodedCharSetId(this.codedCharSetId = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFilterValueLength() {
/* 328 */     int traceRet1 = this.myDelegate.getFilterValueLength();
/* 329 */     if (Trace.isOn) {
/* 330 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSF", "getFilterValueLength()", "getter", 
/* 331 */           Integer.valueOf(traceRet1));
/*     */     }
/* 333 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFilterValue() {
/* 342 */     String traceRet1 = this.myDelegate.getFilterValue();
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSF", "getFilterValue()", "getter", traceRet1);
/*     */     }
/* 346 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilterValue(String value) {
/* 355 */     if (Trace.isOn) {
/* 356 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSF", "setFilterValue(String)", "setter", value);
/*     */     }
/* 358 */     this.myDelegate.setFilterValue(this.filterValue = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 366 */     Object traceRet1 = getFilterValue();
/* 367 */     if (Trace.isOn) {
/* 368 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSF", "getValue()", "getter", traceRet1);
/*     */     }
/* 370 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 378 */     String traceRet1 = getFilterValue();
/* 379 */     if (Trace.isOn) {
/* 380 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSF", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 382 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readCachedContent() throws MQException, IOException {
/* 392 */     if (Trace.isOn) {
/* 393 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSF", "readCachedContent()");
/*     */     }
/* 395 */     this.strucLength = getStrucLength();
/* 396 */     this.parameter = getParameter();
/* 397 */     this.operator = getOperator();
/* 398 */     this.codedCharSetId = getCodedCharSetId();
/* 399 */     this.filterValueLength = getFilterValueLength();
/* 400 */     this.filterValue = getFilterValue();
/*     */     
/* 402 */     if (Trace.isOn) {
/* 403 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSF", "readCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discardCachedContent() {
/* 413 */     if (Trace.isOn) {
/* 414 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSF", "discardCachedContent()");
/*     */     }
/* 416 */     this.strucLength = 24;
/* 417 */     this.parameter = 0;
/* 418 */     this.operator = 0;
/* 419 */     this.codedCharSetId = 0;
/* 420 */     this.filterValueLength = 0;
/* 421 */     this.filterValue = null;
/*     */     
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSF", "discardCachedContent()");
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
/* 436 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSF", "writeCachedContent()");
/*     */     }
/* 438 */     setParameter(this.parameter);
/* 439 */     setOperator(this.operator);
/* 440 */     setCodedCharSetId(this.codedCharSetId);
/* 441 */     setFilterValue(this.filterValue);
/*     */     
/* 443 */     if (Trace.isOn) {
/* 444 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSF", "writeCachedContent()");
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
/*     */   public boolean equals(Object obj) {
/* 458 */     if (Trace.isOn) {
/* 459 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSF", "equals(Object)", new Object[] { obj });
/*     */     }
/* 461 */     if (obj != null && obj instanceof MQCFSF) {
/* 462 */       MQCFSF other = (MQCFSF)obj;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 469 */       boolean traceRet1 = (other.getType() == getType() && other.getParameter() == getParameter() && other.getOperator() == getOperator() && other.getValue().equals(getValue()) && other.getCodedCharSetId() == getCodedCharSetId());
/*     */       
/* 471 */       if (Trace.isOn) {
/* 472 */         Trace.exit(this, "com.ibm.mq.pcf.MQCFSF", "equals(Object)", 
/* 473 */             Boolean.valueOf(traceRet1), 1);
/*     */       }
/* 475 */       return traceRet1;
/*     */     } 
/* 477 */     if (Trace.isOn) {
/* 478 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSF", "equals(Object)", Boolean.valueOf(false), 2);
/*     */     }
/* 480 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 488 */     if (Trace.isOn) {
/* 489 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSF", "hashCode()");
/*     */     }
/* 491 */     int hashCode = 0;
/* 492 */     hashCode += getParameter() * 31;
/* 493 */     hashCode += getOperator() * 37;
/* 494 */     hashCode += getCodedCharSetId() * 41;
/* 495 */     hashCode += getValue().hashCode();
/*     */     
/* 497 */     if (Trace.isOn) {
/* 498 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSF", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 500 */     return hashCode;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\MQCFSF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */