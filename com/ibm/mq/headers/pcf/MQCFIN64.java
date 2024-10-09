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
/*     */ public class MQCFIN64
/*     */   extends PCFParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFIN64.java";
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.headers.pcf.MQCFIN64", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFIN64.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   static final HeaderType TYPE = new HeaderType("MQCFIN64");
/*     */   
/*     */   static final int type = 23;
/*     */   
/*     */   static final int strucLength = 24;
/*     */   
/*  67 */   static final HeaderField Type = TYPE.addMQLong("Type", 23);
/*  68 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 24);
/*  69 */   static final HeaderField Parameter = TYPE.addMQLong("Parameter");
/*  70 */   static final HeaderField Reserved = TYPE.addMQLong("Reserved");
/*  71 */   static final HeaderField Value = TYPE.addMQInt64("Value");
/*     */ 
/*     */   
/*     */   private static final int HEADER_VERSION = 3;
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFIN64() {
/*  79 */     super(TYPE);
/*  80 */     if (Trace.isOn) {
/*  81 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIN64", "<init>()");
/*     */     }
/*  83 */     if (Trace.isOn) {
/*  84 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIN64", "<init>()");
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
/*     */   public MQCFIN64(DataInput message) throws MQDataException, IOException {
/*  97 */     this();
/*  98 */     if (Trace.isOn) {
/*  99 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIN64", "<init>(DataInput)", new Object[] { message });
/*     */     }
/*     */     
/* 102 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIN64", "<init>(DataInput)");
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
/*     */   public MQCFIN64(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 121 */     this();
/* 122 */     if (Trace.isOn) {
/* 123 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIN64", "<init>(DataInput,int,int)", new Object[] { message, 
/* 124 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 127 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 129 */     catch (MQDataException mde) {
/* 130 */       if (Trace.isOn) {
/* 131 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFIN64", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/*     */ 
/*     */       
/* 135 */       if (Trace.isOn) {
/* 136 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFIN64", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/*     */       
/* 139 */       throw mde;
/*     */     }
/* 141 */     catch (IOException ioe) {
/* 142 */       if (Trace.isOn) {
/* 143 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFIN64", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/*     */       
/* 146 */       if (Trace.isOn) {
/* 147 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFIN64", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/*     */       
/* 150 */       throw ioe;
/*     */     }
/* 152 */     catch (Exception e) {
/* 153 */       if (Trace.isOn) {
/* 154 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFIN64", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/*     */       
/* 157 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 158 */       if (Trace.isOn) {
/* 159 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFIN64", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 162 */       throw traceRet1;
/*     */     } 
/* 164 */     if (Trace.isOn) {
/* 165 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIN64", "<init>(DataInput,int,int)");
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
/* 177 */     this();
/* 178 */     if (Trace.isOn)
/* 179 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIN64", "<init>(int,long)", new Object[] {
/* 180 */             Integer.valueOf(parameter), Long.valueOf(value)
/*     */           }); 
/* 182 */     setParameter(parameter);
/* 183 */     setLongValue(value);
/*     */     
/* 185 */     if (Trace.isOn) {
/* 186 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIN64", "<init>(int,long)");
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
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIN64", "equals(Object)", new Object[] { obj });
/*     */     }
/* 202 */     if (obj != null && obj instanceof MQCFIN64) {
/* 203 */       MQCFIN64 other = (MQCFIN64)obj;
/*     */ 
/*     */       
/* 206 */       boolean traceRet1 = (other.getParameter() == getParameter() && other.getValue() == getValue());
/* 207 */       if (Trace.isOn) {
/* 208 */         Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIN64", "equals(Object)", 
/* 209 */             Boolean.valueOf(traceRet1), 1);
/*     */       }
/* 211 */       return traceRet1;
/*     */     } 
/* 213 */     if (Trace.isOn) {
/* 214 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIN64", "equals(Object)", Boolean.valueOf(false), 2);
/*     */     }
/*     */     
/* 217 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIN64", "hashCode()");
/*     */     }
/* 228 */     int hashCode = 0;
/* 229 */     hashCode += getParameter();
/* 230 */     hashCode = (int)(hashCode + 31L * getLongValue());
/*     */     
/* 232 */     if (Trace.isOn) {
/* 233 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIN64", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/*     */     
/* 236 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 246 */     int traceRet1 = getIntValue(Type);
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN64", "getType()", "getter", 
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
/*     */   public int getStrucLength() {
/* 260 */     int traceRet1 = getIntValue(StrucLength);
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN64", "getStrucLength()", "getter", 
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
/*     */   
/*     */   public int getParameter() {
/* 275 */     int traceRet1 = getIntValue(Parameter);
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN64", "getParameter()", "getter", 
/* 278 */           Integer.valueOf(traceRet1));
/*     */     }
/* 280 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameter(int value) {
/* 289 */     if (Trace.isOn) {
/* 290 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN64", "setParameter(int)", "setter", 
/* 291 */           Integer.valueOf(value));
/*     */     }
/* 293 */     setIntValue(Parameter, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReserved() {
/* 301 */     int traceRet1 = getIntValue(Reserved);
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN64", "getReserved()", "getter", 
/* 304 */           Integer.valueOf(traceRet1));
/*     */     }
/* 306 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReserved(int value) {
/* 314 */     if (Trace.isOn) {
/* 315 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN64", "setReserved(int)", "setter", 
/* 316 */           Integer.valueOf(value));
/*     */     }
/* 318 */     setIntValue(Reserved, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLongValue() {
/* 327 */     long traceRet1 = getInt64Value(Value);
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN64", "getLongValue()", "getter", 
/* 330 */           Long.valueOf(traceRet1));
/*     */     }
/* 332 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLongValue(long value) {
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN64", "setLongValue(long)", "setter", 
/* 343 */           Long.valueOf(value));
/*     */     }
/* 345 */     setInt64Value(Value, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 353 */     Object traceRet1 = Long.valueOf(getLongValue());
/* 354 */     if (Trace.isOn) {
/* 355 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN64", "getValue()", "getter", traceRet1);
/*     */     }
/* 357 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 365 */     String traceRet1 = Long.toString(getLongValue());
/* 366 */     if (Trace.isOn) {
/* 367 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN64", "getStringValue()", "getter", traceRet1);
/*     */     }
/*     */     
/* 370 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeaderVersion() {
/* 378 */     if (Trace.isOn) {
/* 379 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN64", "getHeaderVersion()", "getter", 
/* 380 */           Integer.valueOf(3));
/*     */     }
/* 382 */     return 3;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\MQCFIN64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */