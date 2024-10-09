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
/*     */ public class MQCFIF
/*     */   extends PCFFilterParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFIF.java";
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.headers.pcf.MQCFIF", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFIF.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   private static final HeaderType TYPE = new HeaderType("MQCFIF");
/*     */   
/*     */   private static final int type = 13;
/*     */   
/*     */   private static final int strucLength = 20;
/*  67 */   private static final HeaderField Type = TYPE.addMQLong("Type", 13);
/*  68 */   private static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 20);
/*  69 */   private static final HeaderField Parameter = TYPE.addMQLong("Parameter");
/*  70 */   private static final HeaderField Operator = TYPE.addMQLong("Operator");
/*  71 */   private static final HeaderField Value = TYPE.addMQLong("Value");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFIF() {
/*  77 */     super(TYPE);
/*  78 */     if (Trace.isOn) {
/*  79 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIF", "<init>()");
/*     */     }
/*  81 */     if (Trace.isOn) {
/*  82 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIF", "<init>()");
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
/*     */   public MQCFIF(DataInput message) throws MQDataException, IOException {
/*  95 */     this();
/*  96 */     if (Trace.isOn) {
/*  97 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIF", "<init>(DataInput)", new Object[] { message });
/*     */     }
/*     */     
/* 100 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 102 */     if (Trace.isOn) {
/* 103 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIF", "<init>(DataInput)");
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
/*     */   public MQCFIF(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 119 */     this();
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIF", "<init>(DataInput,int,int)", new Object[] { message, 
/* 122 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 125 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 127 */     catch (MQDataException mde) {
/* 128 */       if (Trace.isOn) {
/* 129 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFIF", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/*     */ 
/*     */       
/* 133 */       if (Trace.isOn) {
/* 134 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFIF", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/*     */       
/* 137 */       throw mde;
/*     */     }
/* 139 */     catch (IOException ioe) {
/* 140 */       if (Trace.isOn) {
/* 141 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFIF", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/*     */       
/* 144 */       if (Trace.isOn) {
/* 145 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFIF", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/*     */       
/* 148 */       throw ioe;
/*     */     }
/* 150 */     catch (Exception e) {
/* 151 */       if (Trace.isOn) {
/* 152 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFIF", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/*     */       
/* 155 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 156 */       if (Trace.isOn) {
/* 157 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFIF", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 160 */       throw traceRet1;
/*     */     } 
/* 162 */     if (Trace.isOn) {
/* 163 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIF", "<init>(DataInput,int,int)");
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
/* 176 */     this();
/* 177 */     if (Trace.isOn)
/* 178 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIF", "<init>(int,int,int)", new Object[] {
/* 179 */             Integer.valueOf(parameter), Integer.valueOf(operator), Integer.valueOf(value)
/*     */           }); 
/* 181 */     setParameter(parameter);
/* 182 */     setOperator(operator);
/* 183 */     setFilterValue(value);
/*     */     
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIF", "<init>(int,int,int)");
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
/* 201 */     if (Trace.isOn) {
/* 202 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIF", "equals(Object)", new Object[] { obj });
/*     */     }
/* 204 */     if (obj != null && obj instanceof MQCFIF) {
/* 205 */       MQCFIF other = (MQCFIF)obj;
/*     */ 
/*     */ 
/*     */       
/* 209 */       boolean traceRet1 = (other.getParameter() == getParameter() && other.getOperator() == getOperator() && other.getValue() == getValue());
/* 210 */       if (Trace.isOn) {
/* 211 */         Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIF", "equals(Object)", 
/* 212 */             Boolean.valueOf(traceRet1), 1);
/*     */       }
/* 214 */       return traceRet1;
/*     */     } 
/* 216 */     if (Trace.isOn) {
/* 217 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIF", "equals(Object)", Boolean.valueOf(false), 2);
/*     */     }
/*     */     
/* 220 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 228 */     if (Trace.isOn) {
/* 229 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIF", "hashCode()");
/*     */     }
/* 231 */     int hashCode = 0;
/* 232 */     hashCode += getParameter();
/* 233 */     hashCode += 31 * getOperator();
/* 234 */     hashCode += 37 * getFilterValue();
/* 235 */     hashCode += getStringValue().hashCode();
/*     */     
/* 237 */     if (Trace.isOn) {
/* 238 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIF", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 240 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 250 */     int traceRet1 = getIntValue(Type);
/* 251 */     if (Trace.isOn) {
/* 252 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIF", "getType()", "getter", 
/* 253 */           Integer.valueOf(traceRet1));
/*     */     }
/* 255 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 264 */     int traceRet1 = getIntValue(StrucLength);
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIF", "getStrucLength()", "getter", 
/* 267 */           Integer.valueOf(traceRet1));
/*     */     }
/* 269 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameter() {
/* 279 */     int traceRet1 = getIntValue(Parameter);
/* 280 */     if (Trace.isOn) {
/* 281 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIF", "getParameter()", "getter", 
/* 282 */           Integer.valueOf(traceRet1));
/*     */     }
/* 284 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameter(int value) {
/* 293 */     if (Trace.isOn) {
/* 294 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIF", "setParameter(int)", "setter", 
/* 295 */           Integer.valueOf(value));
/*     */     }
/* 297 */     setIntValue(Parameter, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOperator() {
/* 307 */     int traceRet1 = getIntValue(Operator);
/* 308 */     if (Trace.isOn) {
/* 309 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIF", "getOperator()", "getter", 
/* 310 */           Integer.valueOf(traceRet1));
/*     */     }
/* 312 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOperator(int value) {
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIF", "setOperator(int)", "setter", 
/* 323 */           Integer.valueOf(value));
/*     */     }
/* 325 */     setIntValue(Operator, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFilterValue() {
/* 334 */     int traceRet1 = getIntValue(Value);
/* 335 */     if (Trace.isOn) {
/* 336 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIF", "getFilterValue()", "getter", 
/* 337 */           Integer.valueOf(traceRet1));
/*     */     }
/* 339 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilterValue(int value) {
/* 348 */     if (Trace.isOn) {
/* 349 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIF", "setFilterValue(int)", "setter", 
/* 350 */           Integer.valueOf(value));
/*     */     }
/* 352 */     setIntValue(Value, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 360 */     Object traceRet1 = Integer.valueOf(getFilterValue());
/* 361 */     if (Trace.isOn) {
/* 362 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIF", "getValue()", "getter", traceRet1);
/*     */     }
/* 364 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 372 */     String traceRet1 = Integer.toString(getFilterValue());
/* 373 */     if (Trace.isOn) {
/* 374 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIF", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 376 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\MQCFIF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */