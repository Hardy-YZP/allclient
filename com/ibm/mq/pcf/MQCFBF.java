/*     */ package com.ibm.mq.pcf;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.mq.MQMessage;
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
/*     */ import com.ibm.mq.headers.internal.HexString;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ public class MQCFBF
/*     */   extends PCFFilterParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFBF.java";
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.mq.pcf.MQCFBF", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFBF.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   private static final HeaderType TYPE = new HeaderType("MQCFBF");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int type = 15;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public int strucLength = 20;
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
/*     */   public int filterValueLength;
/*     */ 
/*     */ 
/*     */   
/*  97 */   public byte[] filterValue = new byte[0];
/*     */ 
/*     */   
/*     */   private final com.ibm.mq.headers.pcf.MQCFBF myDelegate;
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFBF() {
/* 105 */     super(TYPE);
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBF", "<init>()");
/*     */     }
/* 109 */     this.myDelegate = (com.ibm.mq.headers.pcf.MQCFBF)this.delegate;
/*     */     
/* 111 */     if (Trace.isOn) {
/* 112 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBF", "<init>()");
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
/*     */   public MQCFBF(MQMessage message) throws MQException, IOException {
/* 125 */     this();
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBF", "<init>(MQMessage)", new Object[] { message });
/*     */     }
/* 129 */     initialize(message);
/*     */     
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBF", "<init>(MQMessage)");
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
/*     */   public MQCFBF(DataInput message, int encoding, int characterSet) throws MQException, IOException {
/* 147 */     this();
/* 148 */     if (Trace.isOn) {
/* 149 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBF", "<init>(DataInput,int,int)", new Object[] { message, 
/* 150 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 153 */       this.delegate.read(message, encoding, characterSet);
/*     */     }
/* 155 */     catch (MQDataException mqe) {
/* 156 */       if (Trace.isOn) {
/* 157 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFBF", "<init>(DataInput,int,int)", (Throwable)mqe, 1);
/*     */       }
/*     */       
/* 160 */       MQException traceRet1 = new MQException(mqe.completionCode, mqe.reasonCode, mqe.exceptionSource);
/*     */       
/* 162 */       if (Trace.isOn) {
/* 163 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFBF", "<init>(DataInput,int,int)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 166 */       throw traceRet1;
/*     */     }
/* 168 */     catch (Exception e) {
/* 169 */       if (Trace.isOn) {
/* 170 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFBF", "<init>(DataInput,int,int)", e, 2);
/*     */       }
/* 172 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 173 */       if (Trace.isOn) {
/* 174 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFBF", "<init>(DataInput,int,int)", traceRet2, 2);
/*     */       }
/* 176 */       throw traceRet2;
/*     */     } 
/* 178 */     if (Trace.isOn) {
/* 179 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBF", "<init>(DataInput,int,int)");
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
/*     */   public MQCFBF(int parameter, int operator, byte[] filterValue) {
/* 192 */     this();
/* 193 */     if (Trace.isOn)
/* 194 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBF", "<init>(int,int,byte [ ])", new Object[] {
/* 195 */             Integer.valueOf(parameter), Integer.valueOf(operator), filterValue
/*     */           }); 
/* 197 */     setParameter(this.parameter = parameter);
/* 198 */     setOperator(this.operator = operator);
/* 199 */     setFilterValue(this.filterValue = filterValue);
/*     */     
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBF", "<init>(int,int,byte [ ])");
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
/*     */   public boolean equals(Object obj) {
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBF", "equals(Object)", new Object[] { obj });
/*     */     }
/* 221 */     if (obj != null && obj instanceof MQCFBF) {
/* 222 */       MQCFBF other = (MQCFBF)obj;
/*     */ 
/*     */ 
/*     */       
/* 226 */       boolean traceRet1 = (other.parameter == this.parameter && other.operator == this.operator && Arrays.equals(other.filterValue, this.filterValue));
/* 227 */       if (Trace.isOn) {
/* 228 */         Trace.exit(this, "com.ibm.mq.pcf.MQCFBF", "equals(Object)", 
/* 229 */             Boolean.valueOf(traceRet1), 1);
/*     */       }
/* 231 */       return traceRet1;
/*     */     } 
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBF", "equals(Object)", Boolean.valueOf(false), 2);
/*     */     }
/* 236 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 244 */     if (Trace.isOn) {
/* 245 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBF", "hashCode()");
/*     */     }
/*     */     
/* 248 */     int hashCode = 0;
/* 249 */     hashCode += getParameter();
/* 250 */     hashCode += 31 * getOperator();
/* 251 */     hashCode += Arrays.hashCode(getFilterValue());
/*     */ 
/*     */     
/* 254 */     if (Trace.isOn) {
/* 255 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBF", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 257 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 267 */     int traceRet1 = this.myDelegate.getType();
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBF", "getType()", "getter", Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 272 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 281 */     int traceRet1 = this.myDelegate.getStrucLength();
/* 282 */     if (Trace.isOn) {
/* 283 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBF", "getStrucLength()", "getter", 
/* 284 */           Integer.valueOf(traceRet1));
/*     */     }
/* 286 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameter() {
/* 296 */     int traceRet1 = this.myDelegate.getParameter();
/* 297 */     if (Trace.isOn) {
/* 298 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBF", "getParameter()", "getter", 
/* 299 */           Integer.valueOf(traceRet1));
/*     */     }
/* 301 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameter(int value) {
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBF", "setParameter(int)", "setter", 
/* 312 */           Integer.valueOf(value));
/*     */     }
/* 314 */     this.myDelegate.setParameter(this.parameter = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOperator() {
/* 324 */     int traceRet1 = this.myDelegate.getOperator();
/* 325 */     if (Trace.isOn) {
/* 326 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBF", "getOperator()", "getter", 
/* 327 */           Integer.valueOf(traceRet1));
/*     */     }
/* 329 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOperator(int value) {
/* 338 */     if (Trace.isOn) {
/* 339 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBF", "setOperator(int)", "setter", 
/* 340 */           Integer.valueOf(value));
/*     */     }
/* 342 */     this.myDelegate.setOperator(this.operator = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFilterValueLength() {
/* 351 */     int traceRet1 = this.myDelegate.getFilterValueLength();
/* 352 */     if (Trace.isOn) {
/* 353 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBF", "getFilterValueLength()", "getter", 
/* 354 */           Integer.valueOf(traceRet1));
/*     */     }
/* 356 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getFilterValue() {
/* 365 */     byte[] traceRet1 = this.myDelegate.getFilterValue();
/* 366 */     if (Trace.isOn) {
/* 367 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBF", "getFilterValue()", "getter", traceRet1);
/*     */     }
/* 369 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilterValue(byte[] value) {
/* 378 */     if (Trace.isOn) {
/* 379 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBF", "setFilterValue(byte [ ])", "setter", value);
/*     */     }
/* 381 */     this.myDelegate.setFilterValue(this.filterValue = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 389 */     Object traceRet1 = getFilterValue();
/* 390 */     if (Trace.isOn) {
/* 391 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBF", "getValue()", "getter", traceRet1);
/*     */     }
/* 393 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 401 */     String traceRet1 = HexString.hexString(getFilterValue());
/* 402 */     if (Trace.isOn) {
/* 403 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBF", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 405 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readCachedContent() throws MQException, IOException {
/* 415 */     if (Trace.isOn) {
/* 416 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBF", "readCachedContent()");
/*     */     }
/* 418 */     this.strucLength = getStrucLength();
/* 419 */     this.parameter = getParameter();
/* 420 */     this.operator = getOperator();
/* 421 */     this.filterValueLength = getFilterValueLength();
/* 422 */     this.filterValue = getFilterValue();
/*     */     
/* 424 */     if (Trace.isOn) {
/* 425 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBF", "readCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discardCachedContent() {
/* 435 */     if (Trace.isOn) {
/* 436 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBF", "discardCachedContent()");
/*     */     }
/* 438 */     this.strucLength = 20;
/* 439 */     this.parameter = 0;
/* 440 */     this.operator = 0;
/* 441 */     this.filterValueLength = 0;
/* 442 */     this.filterValue = null;
/*     */     
/* 444 */     if (Trace.isOn) {
/* 445 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBF", "discardCachedContent()");
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
/* 456 */     if (Trace.isOn) {
/* 457 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBF", "writeCachedContent()");
/*     */     }
/* 459 */     setParameter(this.parameter);
/* 460 */     setOperator(this.operator);
/* 461 */     setFilterValue(this.filterValue);
/*     */     
/* 463 */     if (Trace.isOn)
/* 464 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBF", "writeCachedContent()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\MQCFBF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */