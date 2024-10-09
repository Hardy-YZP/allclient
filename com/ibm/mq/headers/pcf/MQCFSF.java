/*     */ package com.ibm.mq.headers.pcf;
/*     */ 
/*     */ import com.ibm.mq.headers.MQDataException;
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
/*     */ public class MQCFSF
/*     */   extends PCFFilterParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFSF.java";
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.mq.headers.pcf.MQCFSF", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFSF.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   static final HeaderType TYPE = new HeaderType("MQCFSF");
/*     */   
/*     */   static final int type1 = 14;
/*     */   
/*     */   static final int strucLength = 24;
/*  68 */   static final HeaderField Type = TYPE.addMQLong("Type", 14);
/*  69 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 24);
/*     */   
/*  71 */   static final HeaderField Parameter = TYPE.addMQLong("Parameter");
/*  72 */   static final HeaderField Operator = TYPE.addMQLong("Operator");
/*  73 */   static final HeaderField CodedCharSetId = TYPE.addMQLong("CodedCharSetId");
/*  74 */   static final HeaderField FilterValueLength = TYPE.addMQLong("FilterValueLength");
/*  75 */   static final HeaderField FilterValue = TYPE.addMQChar("FilterValue", FilterValueLength, StrucLength, CodedCharSetId);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFSF() {
/*  82 */     super(TYPE);
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFSF", "<init>()");
/*     */     }
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFSF", "<init>()");
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
/*     */   public MQCFSF(DataInput message) throws MQDataException, IOException {
/* 100 */     this();
/* 101 */     if (Trace.isOn) {
/* 102 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFSF", "<init>(DataInput)", new Object[] { message });
/*     */     }
/*     */     
/* 105 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFSF", "<init>(DataInput)");
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
/*     */   public MQCFSF(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 124 */     this();
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFSF", "<init>(DataInput,int,int)", new Object[] { message, 
/* 127 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 130 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 132 */     catch (MQDataException mde) {
/* 133 */       if (Trace.isOn) {
/* 134 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFSF", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/*     */ 
/*     */       
/* 138 */       if (Trace.isOn) {
/* 139 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFSF", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/*     */       
/* 142 */       throw mde;
/*     */     }
/* 144 */     catch (IOException ioe) {
/* 145 */       if (Trace.isOn) {
/* 146 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFSF", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/*     */       
/* 149 */       if (Trace.isOn) {
/* 150 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFSF", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/*     */       
/* 153 */       throw ioe;
/*     */     }
/* 155 */     catch (Exception e) {
/* 156 */       if (Trace.isOn) {
/* 157 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFSF", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/*     */       
/* 160 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 161 */       if (Trace.isOn) {
/* 162 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFSF", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 165 */       throw traceRet1;
/*     */     } 
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFSF", "<init>(DataInput,int,int)");
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
/* 181 */     this();
/* 182 */     if (Trace.isOn)
/* 183 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFSF", "<init>(int,int,String)", new Object[] {
/* 184 */             Integer.valueOf(parameter), Integer.valueOf(operator), filterValue
/*     */           }); 
/* 186 */     setParameter(parameter);
/* 187 */     setOperator(operator);
/* 188 */     setFilterValue(filterValue);
/*     */     
/* 190 */     if (Trace.isOn) {
/* 191 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFSF", "<init>(int,int,String)");
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
/* 203 */     int traceRet1 = getIntValue(Type);
/* 204 */     if (Trace.isOn) {
/* 205 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSF", "getType()", "getter", 
/* 206 */           Integer.valueOf(traceRet1));
/*     */     }
/* 208 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 217 */     int traceRet1 = getIntValue(StrucLength);
/* 218 */     if (Trace.isOn) {
/* 219 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSF", "getStrucLength()", "getter", 
/* 220 */           Integer.valueOf(traceRet1));
/*     */     }
/* 222 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameter() {
/* 232 */     int traceRet1 = getIntValue(Parameter);
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSF", "getParameter()", "getter", 
/* 235 */           Integer.valueOf(traceRet1));
/*     */     }
/* 237 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameter(int value) {
/* 246 */     if (Trace.isOn) {
/* 247 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSF", "setParameter(int)", "setter", 
/* 248 */           Integer.valueOf(value));
/*     */     }
/* 250 */     setIntValue(Parameter, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOperator() {
/* 260 */     int traceRet1 = getIntValue(Operator);
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSF", "getOperator()", "getter", 
/* 263 */           Integer.valueOf(traceRet1));
/*     */     }
/* 265 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOperator(int value) {
/* 274 */     if (Trace.isOn) {
/* 275 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSF", "setOperator(int)", "setter", 
/* 276 */           Integer.valueOf(value));
/*     */     }
/* 278 */     setIntValue(Operator, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 287 */     int traceRet1 = getIntValue(CodedCharSetId);
/* 288 */     if (Trace.isOn) {
/* 289 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSF", "getCodedCharSetId()", "getter", 
/* 290 */           Integer.valueOf(traceRet1));
/*     */     }
/* 292 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int value) {
/* 301 */     if (Trace.isOn) {
/* 302 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSF", "setCodedCharSetId(int)", "setter", 
/* 303 */           Integer.valueOf(value));
/*     */     }
/* 305 */     setIntValue(CodedCharSetId, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFilterValueLength() {
/* 314 */     int traceRet1 = getIntValue(FilterValueLength);
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSF", "getFilterValueLength()", "getter", 
/* 317 */           Integer.valueOf(traceRet1));
/*     */     }
/* 319 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFilterValue() {
/* 328 */     String traceRet1 = getStringValue(FilterValue);
/* 329 */     if (Trace.isOn) {
/* 330 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSF", "getFilterValue()", "getter", traceRet1);
/*     */     }
/* 332 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilterValue(String value) {
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSF", "setFilterValue(String)", "setter", value);
/*     */     }
/*     */     
/* 345 */     setStringValue(FilterValue, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 353 */     Object traceRet1 = getFilterValue();
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSF", "getValue()", "getter", traceRet1);
/*     */     }
/* 357 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 365 */     String traceRet1 = getFilterValue();
/* 366 */     if (Trace.isOn) {
/* 367 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSF", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 369 */     return traceRet1;
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
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFSF", "equals(Object)", new Object[] { obj });
/*     */     }
/* 384 */     if (obj != null && obj instanceof MQCFSF) {
/* 385 */       MQCFSF other = (MQCFSF)obj;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 392 */       boolean traceRet1 = (other.getType() == getType() && other.getParameter() == getParameter() && other.getOperator() == getOperator() && other.getValue().equals(getValue()) && other.getCodedCharSetId() == getCodedCharSetId());
/*     */       
/* 394 */       if (Trace.isOn) {
/* 395 */         Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFSF", "equals(Object)", 
/* 396 */             Boolean.valueOf(traceRet1), 1);
/*     */       }
/* 398 */       return traceRet1;
/*     */     } 
/* 400 */     if (Trace.isOn) {
/* 401 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFSF", "equals(Object)", Boolean.valueOf(false), 2);
/*     */     }
/*     */     
/* 404 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 412 */     if (Trace.isOn) {
/* 413 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFSF", "hashCode()");
/*     */     }
/* 415 */     int hashCode = 0;
/* 416 */     hashCode += getParameter() * 31;
/* 417 */     hashCode += getOperator() * 37;
/* 418 */     hashCode += getCodedCharSetId() * 41;
/* 419 */     hashCode += getValue().hashCode();
/*     */     
/* 421 */     if (Trace.isOn) {
/* 422 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFSF", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 424 */     return hashCode;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\MQCFSF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */