/*     */ package com.ibm.mq.headers.pcf;
/*     */ 
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.internal.HeaderField;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
/*     */ import com.ibm.mq.headers.internal.HexString;
/*     */ import com.ibm.mq.headers.internal.MessageWrapper;
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
/*     */ public class MQCFBF
/*     */   extends PCFFilterParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFBF.java";
/*     */   
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.mq.headers.pcf.MQCFBF", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFBF.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   static final HeaderType TYPE = new HeaderType("MQCFBF");
/*     */   
/*  67 */   static final HeaderField Type = TYPE.addMQLong("Type", 15);
/*  68 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 20);
/*     */   
/*  70 */   static final HeaderField Parameter = TYPE.addMQLong("Parameter");
/*  71 */   static final HeaderField Operator = TYPE.addMQLong("Operator");
/*  72 */   static final HeaderField FilterValueLength = TYPE.addMQLong("FilterValueLength");
/*  73 */   static final HeaderField FilterValue = TYPE.addMQByte("FilterValue", FilterValueLength, StrucLength);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFBF() {
/*  80 */     super(TYPE);
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFBF", "<init>()");
/*     */     }
/*  84 */     if (Trace.isOn) {
/*  85 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFBF", "<init>()");
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
/*     */   public MQCFBF(DataInput message) throws MQDataException, IOException {
/*  98 */     this();
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFBF", "<init>(DataInput)", new Object[] { message });
/*     */     }
/*     */     
/* 103 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFBF", "<init>(DataInput)");
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
/*     */   public MQCFBF(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 122 */     this();
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFBF", "<init>(DataInput,int,int)", new Object[] { message, 
/* 125 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 128 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 130 */     catch (MQDataException mde) {
/* 131 */       if (Trace.isOn) {
/* 132 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFBF", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/*     */ 
/*     */       
/* 136 */       if (Trace.isOn) {
/* 137 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFBF", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/*     */       
/* 140 */       throw mde;
/*     */     }
/* 142 */     catch (IOException ioe) {
/* 143 */       if (Trace.isOn) {
/* 144 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFBF", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/*     */       
/* 147 */       if (Trace.isOn) {
/* 148 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFBF", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/*     */       
/* 151 */       throw ioe;
/*     */     }
/* 153 */     catch (Exception e) {
/* 154 */       if (Trace.isOn) {
/* 155 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFBF", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/*     */       
/* 158 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 159 */       if (Trace.isOn) {
/* 160 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFBF", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 163 */       throw traceRet1;
/*     */     } 
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFBF", "<init>(DataInput,int,int)");
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
/* 179 */     this();
/* 180 */     if (Trace.isOn)
/* 181 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFBF", "<init>(int,int,byte [ ])", new Object[] {
/* 182 */             Integer.valueOf(parameter), Integer.valueOf(operator), filterValue
/*     */           }); 
/* 184 */     setParameter(parameter);
/* 185 */     setOperator(operator);
/* 186 */     setFilterValue(filterValue);
/*     */     
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFBF", "<init>(int,int,byte [ ])");
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
/* 205 */     if (Trace.isOn) {
/* 206 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFBF", "equals(Object)", new Object[] { obj });
/*     */     }
/* 208 */     if (obj != null && obj instanceof MQCFBF) {
/* 209 */       MQCFBF other = (MQCFBF)obj;
/*     */ 
/*     */ 
/*     */       
/* 213 */       boolean traceRet1 = (other.getParameter() == getParameter() && other.getOperator() == getOperator() && Arrays.equals(other.getFilterValue(), getFilterValue()));
/* 214 */       if (Trace.isOn) {
/* 215 */         Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFBF", "equals(Object)", 
/* 216 */             Boolean.valueOf(traceRet1), 1);
/*     */       }
/* 218 */       return traceRet1;
/*     */     } 
/*     */     
/* 221 */     if (Trace.isOn) {
/* 222 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFBF", "equals(Object)", Boolean.valueOf(false), 2);
/*     */     }
/*     */     
/* 225 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFBF", "hashCode()");
/*     */     }
/*     */     
/* 237 */     int hashCode = 0;
/* 238 */     hashCode += getParameter();
/* 239 */     hashCode += 31 * getOperator();
/* 240 */     hashCode += Arrays.hashCode(getFilterValue());
/*     */ 
/*     */     
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFBF", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 246 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 256 */     int traceRet1 = getIntValue(Type);
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBF", "getType()", "getter", 
/* 259 */           Integer.valueOf(traceRet1));
/*     */     }
/* 261 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 270 */     int traceRet1 = getIntValue(StrucLength);
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBF", "getStrucLength()", "getter", 
/* 273 */           Integer.valueOf(traceRet1));
/*     */     }
/* 275 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameter() {
/* 285 */     int traceRet1 = getIntValue(Parameter);
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBF", "getParameter()", "getter", 
/* 288 */           Integer.valueOf(traceRet1));
/*     */     }
/* 290 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameter(int value) {
/* 299 */     if (Trace.isOn) {
/* 300 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBF", "setParameter(int)", "setter", 
/* 301 */           Integer.valueOf(value));
/*     */     }
/* 303 */     setIntValue(Parameter, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOperator() {
/* 313 */     int traceRet1 = getIntValue(Operator);
/* 314 */     if (Trace.isOn) {
/* 315 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBF", "getOperator()", "getter", 
/* 316 */           Integer.valueOf(traceRet1));
/*     */     }
/* 318 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOperator(int value) {
/* 327 */     if (Trace.isOn) {
/* 328 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBF", "setOperator(int)", "setter", 
/* 329 */           Integer.valueOf(value));
/*     */     }
/* 331 */     setIntValue(Operator, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFilterValueLength() {
/* 340 */     int traceRet1 = getIntValue(FilterValueLength);
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBF", "getFilterValueLength()", "getter", 
/* 343 */           Integer.valueOf(traceRet1));
/*     */     }
/* 345 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getFilterValue() {
/* 354 */     byte[] traceRet1 = getBytesValue(FilterValue);
/* 355 */     if (Trace.isOn) {
/* 356 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBF", "getFilterValue()", "getter", traceRet1);
/*     */     }
/* 358 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilterValue(byte[] value) {
/* 367 */     if (Trace.isOn) {
/* 368 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBF", "setFilterValue(byte [ ])", "setter", value);
/*     */     }
/*     */     
/* 371 */     setBytesValue(FilterValue, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 379 */     Object traceRet1 = getFilterValue();
/* 380 */     if (Trace.isOn) {
/* 381 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBF", "getValue()", "getter", traceRet1);
/*     */     }
/* 383 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 391 */     String traceRet1 = HexString.hexString(getFilterValue());
/* 392 */     if (Trace.isOn) {
/* 393 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBF", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 395 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\MQCFBF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */