/*     */ package com.ibm.mq.pcf;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.mq.MQMessage;
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
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
/*     */ @Deprecated
/*     */ public class MQCFIL64
/*     */   extends PCFParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFIL64.java";
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.mq.pcf.MQCFIL64", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFIL64.java");
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
/*  66 */   static final HeaderType TYPE = new HeaderType("MQCFIL64");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int type = 25;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public int strucLength = 16;
/*     */ 
/*     */ 
/*     */   
/*     */   public int parameter;
/*     */ 
/*     */ 
/*     */   
/*     */   public int count;
/*     */ 
/*     */ 
/*     */   
/*     */   public long[] values;
/*     */ 
/*     */   
/*     */   private final com.ibm.mq.headers.pcf.MQCFIL64 myDelegate;
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFIL64() {
/*  99 */     super(TYPE);
/* 100 */     if (Trace.isOn) {
/* 101 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL64", "<init>()");
/*     */     }
/* 103 */     this.myDelegate = (com.ibm.mq.headers.pcf.MQCFIL64)this.delegate;
/*     */     
/* 105 */     if (Trace.isOn) {
/* 106 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL64", "<init>()");
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
/*     */   public MQCFIL64(MQMessage message) throws MQException, IOException {
/* 119 */     this();
/* 120 */     if (Trace.isOn) {
/* 121 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL64", "<init>(MQMessage)", new Object[] { message });
/*     */     }
/* 123 */     initialize(message);
/*     */     
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL64", "<init>(MQMessage)");
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
/*     */   public MQCFIL64(DataInput message, int encoding, int characterSet) throws MQException, IOException {
/* 142 */     this();
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL64", "<init>(DataInput,int,int)", new Object[] { message, 
/* 145 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 148 */       this.delegate.read(message, encoding, characterSet);
/*     */     }
/* 150 */     catch (MQDataException mqe) {
/* 151 */       if (Trace.isOn) {
/* 152 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFIL64", "<init>(DataInput,int,int)", (Throwable)mqe, 1);
/*     */       }
/*     */       
/* 155 */       MQException traceRet1 = new MQException(mqe.completionCode, mqe.reasonCode, mqe.exceptionSource);
/*     */       
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFIL64", "<init>(DataInput,int,int)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 161 */       throw traceRet1;
/*     */     }
/* 163 */     catch (Exception e) {
/* 164 */       if (Trace.isOn) {
/* 165 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFIL64", "<init>(DataInput,int,int)", e, 2);
/*     */       }
/* 167 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 168 */       if (Trace.isOn) {
/* 169 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFIL64", "<init>(DataInput,int,int)", traceRet2, 2);
/*     */       }
/*     */       
/* 172 */       throw traceRet2;
/*     */     } 
/* 174 */     if (Trace.isOn) {
/* 175 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL64", "<init>(DataInput,int,int)");
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
/* 187 */     this();
/* 188 */     if (Trace.isOn)
/* 189 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL64", "<init>(int,long [ ])", new Object[] {
/* 190 */             Integer.valueOf(parameter), values
/*     */           }); 
/* 192 */     setParameter(this.parameter = parameter);
/* 193 */     setValues(this.values = values);
/*     */     
/* 195 */     if (Trace.isOn) {
/* 196 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL64", "<init>(int,long [ ])");
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
/* 210 */     if (Trace.isOn) {
/* 211 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL64", "equals(Object)", new Object[] { obj });
/*     */     }
/* 213 */     if (obj != null && obj instanceof MQCFIL64) {
/* 214 */       MQCFIL64 other = (MQCFIL64)obj;
/*     */       
/* 216 */       long[] otherValues = other.values, values = this.values;
/*     */       
/* 218 */       if (other.parameter == this.parameter && otherValues != null && values != null && otherValues.length == values.length) {
/*     */         
/* 220 */         int i = values.length;
/* 221 */         boolean match = true;
/*     */         
/* 223 */         while (match && i-- > 0) {
/* 224 */           match = (otherValues[i] == values[i]);
/*     */         }
/*     */         
/* 227 */         if (Trace.isOn) {
/* 228 */           Trace.exit(this, "com.ibm.mq.pcf.MQCFIL64", "equals(Object)", Boolean.valueOf(match), 1);
/*     */         }
/*     */         
/* 231 */         return match;
/*     */       } 
/* 233 */       if (Trace.isOn) {
/* 234 */         Trace.exit(this, "com.ibm.mq.pcf.MQCFIL64", "equals(Object)", Boolean.valueOf(false), 2);
/*     */       }
/* 236 */       return false;
/*     */     } 
/* 238 */     if (Trace.isOn) {
/* 239 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL64", "equals(Object)", Boolean.valueOf(false), 3);
/*     */     }
/* 241 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL64", "hashCode()");
/*     */     }
/* 252 */     int hashCode = 0;
/* 253 */     hashCode += getParameter();
/* 254 */     hashCode += Arrays.hashCode(getValues());
/*     */     
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL64", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 259 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 269 */     int traceRet1 = this.myDelegate.getType();
/* 270 */     if (Trace.isOn) {
/* 271 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL64", "getType()", "getter", 
/* 272 */           Integer.valueOf(traceRet1));
/*     */     }
/* 274 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 283 */     int traceRet1 = this.myDelegate.getStrucLength();
/* 284 */     if (Trace.isOn) {
/* 285 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL64", "getStrucLength()", "getter", 
/* 286 */           Integer.valueOf(traceRet1));
/*     */     }
/* 288 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameter() {
/* 298 */     int traceRet1 = this.myDelegate.getParameter();
/* 299 */     if (Trace.isOn) {
/* 300 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL64", "getParameter()", "getter", 
/* 301 */           Integer.valueOf(traceRet1));
/*     */     }
/* 303 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameter(int value) {
/* 312 */     if (Trace.isOn) {
/* 313 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL64", "setParameter(int)", "setter", 
/* 314 */           Integer.valueOf(value));
/*     */     }
/* 316 */     this.myDelegate.setParameter(this.parameter = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCount() {
/* 325 */     int traceRet1 = this.myDelegate.getCount();
/* 326 */     if (Trace.isOn) {
/* 327 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL64", "getCount()", "getter", 
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
/*     */   public long[] getValues() {
/* 339 */     long[] traceRet1 = this.myDelegate.getValues();
/* 340 */     if (Trace.isOn) {
/* 341 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL64", "getValues()", "getter", traceRet1);
/*     */     }
/* 343 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValues(long[] values) {
/* 352 */     if (Trace.isOn) {
/* 353 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL64", "setValues(long [ ])", "setter", values);
/*     */     }
/* 355 */     this.myDelegate.setValues(this.values = values);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 363 */     Object traceRet1 = getValues();
/* 364 */     if (Trace.isOn) {
/* 365 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL64", "getValue()", "getter", traceRet1);
/*     */     }
/* 367 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 375 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 377 */     long[] values = getValues();
/*     */     
/* 379 */     for (int i = 0; i < values.length; i++) {
/* 380 */       sb.append(values[i]);
/* 381 */       sb.append(' ');
/*     */     } 
/*     */     
/* 384 */     if (sb.length() > 0) {
/* 385 */       sb.setLength(sb.length());
/*     */     }
/*     */     
/* 388 */     String traceRet1 = new String(sb);
/*     */     
/* 390 */     if (Trace.isOn) {
/* 391 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL64", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 393 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readCachedContent() throws MQException, IOException {
/* 403 */     if (Trace.isOn) {
/* 404 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL64", "readCachedContent()");
/*     */     }
/* 406 */     this.strucLength = getStrucLength();
/* 407 */     this.parameter = getParameter();
/* 408 */     this.count = getCount();
/* 409 */     this.values = getValues();
/*     */     
/* 411 */     if (Trace.isOn) {
/* 412 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL64", "readCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discardCachedContent() {
/* 422 */     if (Trace.isOn) {
/* 423 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL64", "discardCachedContent()");
/*     */     }
/* 425 */     this.strucLength = 16;
/* 426 */     this.parameter = 0;
/* 427 */     this.count = 0;
/* 428 */     this.values = null;
/*     */     
/* 430 */     if (Trace.isOn) {
/* 431 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL64", "discardCachedContent()");
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
/* 442 */     if (Trace.isOn) {
/* 443 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL64", "writeCachedContent()");
/*     */     }
/* 445 */     setParameter(this.parameter);
/* 446 */     setValues(this.values);
/*     */     
/* 448 */     if (Trace.isOn) {
/* 449 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL64", "writeCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeaderVersion() {
/* 459 */     int traceRet1 = this.myDelegate.getHeaderVersion();
/* 460 */     if (Trace.isOn) {
/* 461 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL64", "getHeaderVersion()", "getter", 
/* 462 */           Integer.valueOf(traceRet1));
/*     */     }
/* 464 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\MQCFIL64.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */