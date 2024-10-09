/*     */ package com.ibm.mq.headers.pcf;
/*     */ 
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.internal.HeaderField;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
/*     */ import com.ibm.mq.headers.internal.MessageWrapper;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
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
/*     */ public class MQCFIL
/*     */   extends PCFParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFIL.java";
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.mq.headers.pcf.MQCFIL", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFIL.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   static final HeaderType TYPE = new HeaderType("MQCFIL");
/*     */   
/*     */   static final int type = 5;
/*     */   
/*     */   static final int strucLength = 16;
/*  68 */   static final HeaderField Type = TYPE.addMQLong("Type", 5);
/*  69 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 16);
/*     */   
/*  71 */   static final HeaderField Parameter = TYPE.addMQLong("Parameter");
/*  72 */   static final HeaderField Count = TYPE.addMQLong("Count");
/*  73 */   static final HeaderField Values = TYPE.addMQLongArray("Values", Count, StrucLength);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int SIZEOF_INT = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int write(DataOutput message, int parameter, int[] values) throws IOException {
/*  87 */     if (Trace.isOn) {
/*  88 */       Trace.entry("com.ibm.mq.headers.pcf.MQCFIL", "write(DataOutput,int,int [ ])", new Object[] { message, 
/*  89 */             Integer.valueOf(parameter), values });
/*     */     }
/*  91 */     int count = (values == null) ? 0 : values.length;
/*     */     
/*  93 */     message.writeInt(5);
/*  94 */     message.writeInt(16 + count * 4);
/*  95 */     message.writeInt(parameter);
/*  96 */     message.writeInt(count);
/*     */     
/*  98 */     for (int i = 0; i < count; i++) {
/*  99 */       message.writeInt(values[i]);
/*     */     }
/*     */     
/* 102 */     int traceRet1 = 16 + count * 4;
/* 103 */     if (Trace.isOn) {
/* 104 */       Trace.exit("com.ibm.mq.headers.pcf.MQCFIL", "write(DataOutput,int,int [ ])", 
/* 105 */           Integer.valueOf(traceRet1));
/*     */     }
/* 107 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFIL() {
/* 114 */     super(TYPE);
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIL", "<init>()");
/*     */     }
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIL", "<init>()");
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
/*     */   public MQCFIL(DataInput message) throws MQDataException, IOException {
/* 132 */     this();
/* 133 */     if (Trace.isOn) {
/* 134 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIL", "<init>(DataInput)", new Object[] { message });
/*     */     }
/*     */     
/* 137 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIL", "<init>(DataInput)");
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
/*     */   public MQCFIL(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 156 */     this();
/* 157 */     if (Trace.isOn) {
/* 158 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIL", "<init>(DataInput,int,int)", new Object[] { message, 
/* 159 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 162 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 164 */     catch (MQDataException mde) {
/* 165 */       if (Trace.isOn) {
/* 166 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFIL", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/*     */ 
/*     */       
/* 170 */       if (Trace.isOn) {
/* 171 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFIL", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/*     */       
/* 174 */       throw mde;
/*     */     }
/* 176 */     catch (IOException ioe) {
/* 177 */       if (Trace.isOn) {
/* 178 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFIL", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/*     */       
/* 181 */       if (Trace.isOn) {
/* 182 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFIL", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/*     */       
/* 185 */       throw ioe;
/*     */     }
/* 187 */     catch (Exception e) {
/* 188 */       if (Trace.isOn) {
/* 189 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFIL", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/*     */       
/* 192 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 193 */       if (Trace.isOn) {
/* 194 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFIL", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 197 */       throw traceRet1;
/*     */     } 
/* 199 */     if (Trace.isOn) {
/* 200 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIL", "<init>(DataInput,int,int)");
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
/*     */   public MQCFIL(int parameter, int[] values) {
/* 212 */     this();
/* 213 */     if (Trace.isOn)
/* 214 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIL", "<init>(int,int [ ])", new Object[] {
/* 215 */             Integer.valueOf(parameter), values
/*     */           }); 
/* 217 */     setParameter(parameter);
/* 218 */     setValues(values);
/*     */     
/* 220 */     if (Trace.isOn) {
/* 221 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIL", "<init>(int,int [ ])");
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
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIL", "equals(Object)", new Object[] { obj });
/*     */     }
/* 239 */     if (obj != null && obj instanceof MQCFIL) {
/* 240 */       MQCFIL other = (MQCFIL)obj;
/* 241 */       int[] otherValues = other.getValues(), values = getValues();
/*     */       
/* 243 */       if (other.getParameter() == getParameter() && otherValues != null && values != null && otherValues.length == values.length) {
/*     */         
/* 245 */         int i = values.length;
/* 246 */         boolean match = true;
/*     */         
/* 248 */         while (match && i-- > 0) {
/* 249 */           match = (otherValues[i] == values[i]);
/*     */         }
/*     */         
/* 252 */         if (Trace.isOn) {
/* 253 */           Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIL", "equals(Object)", 
/* 254 */               Boolean.valueOf(match), 1);
/*     */         }
/* 256 */         return match;
/*     */       } 
/* 258 */       if (Trace.isOn) {
/* 259 */         Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIL", "equals(Object)", Boolean.valueOf(false), 2);
/*     */       }
/*     */       
/* 262 */       return false;
/*     */     } 
/* 264 */     if (Trace.isOn) {
/* 265 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIL", "equals(Object)", Boolean.valueOf(false), 3);
/*     */     }
/*     */     
/* 268 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 276 */     if (Trace.isOn) {
/* 277 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIL", "hashCode()");
/*     */     }
/* 279 */     int hashCode = 0;
/* 280 */     hashCode += getParameter();
/* 281 */     hashCode += Arrays.hashCode(getValues());
/*     */     
/* 283 */     if (Trace.isOn) {
/* 284 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIL", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 286 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 296 */     int traceRet1 = getIntValue(Type);
/* 297 */     if (Trace.isOn) {
/* 298 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL", "getType()", "getter", 
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
/*     */   public int getStrucLength() {
/* 310 */     int traceRet1 = getIntValue(StrucLength);
/* 311 */     if (Trace.isOn) {
/* 312 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL", "getStrucLength()", "getter", 
/* 313 */           Integer.valueOf(traceRet1));
/*     */     }
/* 315 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameter() {
/* 325 */     int traceRet1 = getIntValue(Parameter);
/* 326 */     if (Trace.isOn) {
/* 327 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL", "getParameter()", "getter", 
/* 328 */           Integer.valueOf(traceRet1));
/*     */     }
/* 330 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameter(int value) {
/* 339 */     if (Trace.isOn) {
/* 340 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL", "setParameter(int)", "setter", 
/* 341 */           Integer.valueOf(value));
/*     */     }
/* 343 */     setIntValue(Parameter, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCount() {
/* 352 */     int traceRet1 = getIntValue(Count);
/* 353 */     if (Trace.isOn) {
/* 354 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL", "getCount()", "getter", 
/* 355 */           Integer.valueOf(traceRet1));
/*     */     }
/* 357 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getValues() {
/* 366 */     int[] traceRet1 = getIntListValue(Values);
/* 367 */     if (Trace.isOn) {
/* 368 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL", "getValues()", "getter", traceRet1);
/*     */     }
/* 370 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValues(int[] values) {
/* 379 */     if (Trace.isOn) {
/* 380 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL", "setValues(int [ ])", "setter", values);
/*     */     }
/* 382 */     setIntListValue(Values, values);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 390 */     Object traceRet1 = getValues();
/* 391 */     if (Trace.isOn) {
/* 392 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL", "getValue()", "getter", traceRet1);
/*     */     }
/* 394 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 402 */     StringBuffer sb = new StringBuffer();
/* 403 */     int[] values = getValues();
/*     */     
/* 405 */     for (int i = 0; i < values.length; i++) {
/* 406 */       sb.append(values[i]);
/* 407 */       sb.append(' ');
/*     */     } 
/*     */     
/* 410 */     if (sb.length() > 0) {
/* 411 */       sb.setLength(sb.length() - 1);
/*     */     }
/*     */     
/* 414 */     String traceRet1 = new String(sb);
/*     */     
/* 416 */     if (Trace.isOn) {
/* 417 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIL", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 419 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\MQCFIL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */