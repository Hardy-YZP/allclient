/*     */ package com.ibm.mq.headers.pcf;
/*     */ 
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.internal.HeaderField;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
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
/*     */ public class MQCFIL64
/*     */   extends PCFParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFIL64.java";
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.mq.headers.pcf.MQCFIL64", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFIL64.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   static final HeaderType TYPE = new HeaderType("MQCFIL64");
/*     */   
/*     */   static final int type = 25;
/*     */   
/*     */   static final int strucLength = 16;
/*     */   
/*  68 */   static final HeaderField Type = TYPE.addMQLong("Type", 25);
/*  69 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 16);
/*     */   
/*  71 */   static final HeaderField Parameter = TYPE.addMQLong("Parameter");
/*  72 */   static final HeaderField Count = TYPE.addMQLong("Count");
/*  73 */   static final HeaderField Values = TYPE.addMQInt64Array("Values", Count, StrucLength);
/*     */ 
/*     */   
/*     */   private static final int HEADER_VERSION = 3;
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFIL64() {
/*  81 */     super(TYPE);
/*  82 */     if (Trace.isOn) {
/*  83 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIL64", "<init>()");
/*     */     }
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIL64", "<init>()");
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
/*     */   public MQCFIL64(DataInput message) throws MQDataException, IOException {
/*  99 */     this();
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIL64", "<init>(DataInput)", new Object[] { message });
/*     */     }
/*     */     
/* 104 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIL64", "<init>(DataInput)");
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
/*     */   public MQCFIL64(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 123 */     this();
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIL64", "<init>(DataInput,int,int)", new Object[] { message, 
/* 126 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 129 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 131 */     catch (MQDataException mde) {
/* 132 */       if (Trace.isOn) {
/* 133 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFIL64", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/*     */ 
/*     */       
/* 137 */       if (Trace.isOn) {
/* 138 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFIL64", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/*     */       
/* 141 */       throw mde;
/*     */     }
/* 143 */     catch (IOException ioe) {
/* 144 */       if (Trace.isOn) {
/* 145 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFIL64", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/*     */       
/* 148 */       if (Trace.isOn) {
/* 149 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFIL64", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/*     */       
/* 152 */       throw ioe;
/*     */     }
/* 154 */     catch (Exception e) {
/* 155 */       if (Trace.isOn) {
/* 156 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFIL64", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/*     */       
/* 159 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 160 */       if (Trace.isOn) {
/* 161 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFIL64", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 164 */       throw traceRet1;
/*     */     } 
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIL64", "<init>(DataInput,int,int)");
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
/*     */   public MQCFIL64(int parameter, long[] values) {
/* 179 */     this();
/* 180 */     if (Trace.isOn)
/* 181 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIL64", "<init>(int,long [ ])", new Object[] {
/* 182 */             Integer.valueOf(parameter), values
/*     */           }); 
/* 184 */     setParameter(parameter);
/* 185 */     setValues(values);
/*     */     
/* 187 */     if (Trace.isOn) {
/* 188 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIL64", "<init>(int,long [ ])");
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
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIL64", "equals(Object)", new Object[] { obj });
/*     */     }
/* 206 */     if (obj != null && obj instanceof MQCFIL64) {
/* 207 */       MQCFIL64 other = (MQCFIL64)obj;
/* 208 */       long[] otherValues = other.getValues(), values = getValues();
/*     */       
/* 210 */       if (other.getParameter() == getParameter() && otherValues != null && values != null && otherValues.length == values.length) {
/*     */         
/* 212 */         int i = values.length;
/* 213 */         boolean match = true;
/*     */         
/* 215 */         while (match && i-- > 0) {
/* 216 */           match = (otherValues[i] == values[i]);
/*     */         }
/*     */         
/* 219 */         if (Trace.isOn) {
/* 220 */           Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIL64", "equals(Object)", 
/* 221 */               Boolean.valueOf(match), 1);
/*     */         }
/* 223 */         return match;
/*     */       } 
/* 225 */       if (Trace.isOn) {
/* 226 */         Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIL64", "equals(Object)", 
/* 227 */             Boolean.valueOf(false), 2);
/*     */       }
/* 229 */       return false;
/*     */     } 
/* 231 */     if (Trace.isOn) {
/* 232 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIL64", "equals(Object)", Boolean.valueOf(false), 3);
/*     */     }
/*     */     
/* 235 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIL64", "hashCode()");
/*     */     }
/* 246 */     int hashCode = 0;
/* 247 */     hashCode += getParameter();
/* 248 */     hashCode += Arrays.hashCode(getValues());
/*     */     
/* 250 */     if (Trace.isOn) {
/* 251 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIL64", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/*     */     
/* 254 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 264 */     int traceRet1 = getIntValue(Type);
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL64", "getType()", "getter", 
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
/*     */   public int getStrucLength() {
/* 278 */     int traceRet1 = getIntValue(StrucLength);
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL64", "getStrucLength()", "getter", 
/* 281 */           Integer.valueOf(traceRet1));
/*     */     }
/* 283 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameter() {
/* 293 */     int traceRet1 = getIntValue(Parameter);
/* 294 */     if (Trace.isOn) {
/* 295 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL64", "getParameter()", "getter", 
/* 296 */           Integer.valueOf(traceRet1));
/*     */     }
/* 298 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameter(int value) {
/* 307 */     if (Trace.isOn) {
/* 308 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL64", "setParameter(int)", "setter", 
/* 309 */           Integer.valueOf(value));
/*     */     }
/* 311 */     setIntValue(Parameter, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCount() {
/* 320 */     int traceRet1 = getIntValue(Count);
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL64", "getCount()", "getter", 
/* 323 */           Integer.valueOf(traceRet1));
/*     */     }
/* 325 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long[] getValues() {
/* 334 */     long[] traceRet1 = getInt64ListValue(Values);
/* 335 */     if (Trace.isOn) {
/* 336 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL64", "getValues()", "getter", traceRet1);
/*     */     }
/* 338 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValues(long[] values) {
/* 347 */     if (Trace.isOn) {
/* 348 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL64", "setValues(long [ ])", "setter", values);
/*     */     }
/*     */     
/* 351 */     setInt64ListValue(Values, values);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 359 */     Object traceRet1 = getValues();
/* 360 */     if (Trace.isOn) {
/* 361 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL64", "getValue()", "getter", traceRet1);
/*     */     }
/* 363 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 371 */     StringBuffer sb = new StringBuffer();
/* 372 */     long[] values = getValues();
/*     */     
/* 374 */     for (int i = 0; i < values.length; i++) {
/* 375 */       sb.append(values[i]);
/* 376 */       sb.append(' ');
/*     */     } 
/*     */     
/* 379 */     if (sb.length() > 0) {
/* 380 */       sb.setLength(sb.length());
/*     */     }
/*     */     
/* 383 */     String traceRet1 = new String(sb);
/*     */     
/* 385 */     if (Trace.isOn) {
/* 386 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL64", "getStringValue()", "getter", traceRet1);
/*     */     }
/*     */     
/* 389 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeaderVersion() {
/* 397 */     if (Trace.isOn) {
/* 398 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL64", "getHeaderVersion()", "getter", 
/* 399 */           Integer.valueOf(3));
/*     */     }
/* 401 */     return 3;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\MQCFIL64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */