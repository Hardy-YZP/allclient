/*     */ package com.ibm.mq.pcf;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.mq.MQMessage;
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
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
/*     */ @Deprecated
/*     */ public class MQCFIL
/*     */   extends PCFParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFIL.java";
/*     */   
/*     */   static {
/*  53 */     if (Trace.isOn) {
/*  54 */       Trace.data("com.ibm.mq.pcf.MQCFIL", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFIL.java");
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
/*  65 */   static final HeaderType TYPE = new HeaderType("MQCFIL");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int type = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public int strucLength = 16;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int parameter;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int count;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] values;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final com.ibm.mq.headers.pcf.MQCFIL myDelegate;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int write(MQMessage message, int parameter, int[] values) throws IOException {
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.entry("com.ibm.mq.pcf.MQCFIL", "write(MQMessage,int,int [ ])", new Object[] { message, 
/* 108 */             Integer.valueOf(parameter), values });
/*     */     }
/*     */     
/* 111 */     int traceRet1 = com.ibm.mq.headers.pcf.MQCFIL.write((DataOutput)message, parameter, values);
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.exit("com.ibm.mq.pcf.MQCFIL", "write(MQMessage,int,int [ ])", 
/* 114 */           Integer.valueOf(traceRet1));
/*     */     }
/* 116 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFIL() {
/* 123 */     super(TYPE);
/* 124 */     if (Trace.isOn) {
/* 125 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL", "<init>()");
/*     */     }
/* 127 */     this.myDelegate = (com.ibm.mq.headers.pcf.MQCFIL)this.delegate;
/*     */     
/* 129 */     if (Trace.isOn) {
/* 130 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL", "<init>()");
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
/*     */   public MQCFIL(MQMessage message) throws MQException, IOException {
/* 143 */     this();
/* 144 */     if (Trace.isOn) {
/* 145 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL", "<init>(MQMessage)", new Object[] { message });
/*     */     }
/* 147 */     initialize(message);
/*     */     
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL", "<init>(MQMessage)");
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
/*     */   public MQCFIL(DataInput message, int encoding, int characterSet) throws MQException, IOException {
/* 165 */     this();
/* 166 */     if (Trace.isOn) {
/* 167 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL", "<init>(DataInput,int,int)", new Object[] { message, 
/* 168 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 171 */       this.delegate.read(message, encoding, characterSet);
/*     */     }
/* 173 */     catch (MQDataException mqe) {
/* 174 */       if (Trace.isOn) {
/* 175 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFIL", "<init>(DataInput,int,int)", (Throwable)mqe, 1);
/*     */       }
/*     */       
/* 178 */       MQException traceRet1 = new MQException(mqe.completionCode, mqe.reasonCode, mqe.exceptionSource);
/*     */       
/* 180 */       if (Trace.isOn) {
/* 181 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFIL", "<init>(DataInput,int,int)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 184 */       throw traceRet1;
/*     */     }
/* 186 */     catch (Exception e) {
/* 187 */       if (Trace.isOn) {
/* 188 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFIL", "<init>(DataInput,int,int)", e, 2);
/*     */       }
/* 190 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 191 */       if (Trace.isOn) {
/* 192 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFIL", "<init>(DataInput,int,int)", traceRet2, 2);
/*     */       }
/* 194 */       throw traceRet2;
/*     */     } 
/* 196 */     if (Trace.isOn) {
/* 197 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL", "<init>(DataInput,int,int)");
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
/* 209 */     this();
/* 210 */     if (Trace.isOn)
/* 211 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL", "<init>(int,int [ ])", new Object[] {
/* 212 */             Integer.valueOf(parameter), values
/*     */           }); 
/* 214 */     setParameter(this.parameter = parameter);
/* 215 */     setValues(this.values = values);
/*     */     
/* 217 */     if (Trace.isOn) {
/* 218 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL", "<init>(int,int [ ])");
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
/* 233 */     if (Trace.isOn) {
/* 234 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL", "equals(Object)", new Object[] { obj });
/*     */     }
/* 236 */     if (obj != null && obj instanceof MQCFIL) {
/* 237 */       MQCFIL other = (MQCFIL)obj;
/*     */       
/* 239 */       int[] otherValues = other.values, values = this.values;
/*     */       
/* 241 */       if (other.parameter == this.parameter && otherValues != null && values != null && otherValues.length == values.length) {
/*     */         
/* 243 */         int i = values.length;
/* 244 */         boolean match = true;
/*     */         
/* 246 */         while (match && i-- > 0) {
/* 247 */           match = (otherValues[i] == values[i]);
/*     */         }
/*     */         
/* 250 */         if (Trace.isOn) {
/* 251 */           Trace.exit(this, "com.ibm.mq.pcf.MQCFIL", "equals(Object)", Boolean.valueOf(match), 1);
/*     */         }
/* 253 */         return match;
/*     */       } 
/* 255 */       if (Trace.isOn) {
/* 256 */         Trace.exit(this, "com.ibm.mq.pcf.MQCFIL", "equals(Object)", Boolean.valueOf(false), 2);
/*     */       }
/* 258 */       return false;
/*     */     } 
/* 260 */     if (Trace.isOn) {
/* 261 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL", "equals(Object)", Boolean.valueOf(false), 3);
/*     */     }
/* 263 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 271 */     if (Trace.isOn) {
/* 272 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL", "hashCode()");
/*     */     }
/* 274 */     int hashCode = 0;
/* 275 */     hashCode += getParameter();
/* 276 */     hashCode += Arrays.hashCode(getValues());
/*     */     
/* 278 */     if (Trace.isOn) {
/* 279 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 281 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 291 */     int traceRet1 = this.myDelegate.getType();
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL", "getType()", "getter", Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 296 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 305 */     int traceRet1 = this.myDelegate.getStrucLength();
/* 306 */     if (Trace.isOn) {
/* 307 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL", "getStrucLength()", "getter", 
/* 308 */           Integer.valueOf(traceRet1));
/*     */     }
/* 310 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameter() {
/* 320 */     int traceRet1 = this.myDelegate.getParameter();
/* 321 */     if (Trace.isOn) {
/* 322 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL", "getParameter()", "getter", 
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
/*     */   public void setParameter(int value) {
/* 334 */     if (Trace.isOn) {
/* 335 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL", "setParameter(int)", "setter", 
/* 336 */           Integer.valueOf(value));
/*     */     }
/* 338 */     this.myDelegate.setParameter(this.parameter = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCount() {
/* 347 */     int traceRet1 = this.myDelegate.getCount();
/* 348 */     if (Trace.isOn) {
/* 349 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL", "getCount()", "getter", Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 352 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getValues() {
/* 361 */     int[] traceRet1 = this.myDelegate.getValues();
/* 362 */     if (Trace.isOn) {
/* 363 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL", "getValues()", "getter", traceRet1);
/*     */     }
/* 365 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValues(int[] values) {
/* 374 */     if (Trace.isOn) {
/* 375 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL", "setValues(int [ ])", "setter", values);
/*     */     }
/* 377 */     this.myDelegate.setValues(this.values = values);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 385 */     Object traceRet1 = getValues();
/* 386 */     if (Trace.isOn) {
/* 387 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL", "getValue()", "getter", traceRet1);
/*     */     }
/* 389 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 397 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 399 */     int[] values = getValues();
/*     */     
/* 401 */     for (int i = 0; i < values.length; i++) {
/* 402 */       sb.append(values[i]);
/* 403 */       sb.append(' ');
/*     */     } 
/*     */     
/* 406 */     if (sb.length() > 0) {
/* 407 */       sb.setLength(sb.length() - 1);
/*     */     }
/*     */     
/* 410 */     String traceRet1 = new String(sb);
/*     */     
/* 412 */     if (Trace.isOn) {
/* 413 */       Trace.data(this, "com.ibm.mq.pcf.MQCFIL", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 415 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readCachedContent() throws MQException, IOException {
/* 425 */     if (Trace.isOn) {
/* 426 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL", "readCachedContent()");
/*     */     }
/* 428 */     this.strucLength = getStrucLength();
/* 429 */     this.parameter = getParameter();
/* 430 */     this.count = getCount();
/* 431 */     this.values = getValues();
/*     */     
/* 433 */     if (Trace.isOn) {
/* 434 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL", "readCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discardCachedContent() {
/* 444 */     if (Trace.isOn) {
/* 445 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL", "discardCachedContent()");
/*     */     }
/* 447 */     this.strucLength = 16;
/* 448 */     this.parameter = 0;
/* 449 */     this.count = 0;
/* 450 */     this.values = null;
/*     */     
/* 452 */     if (Trace.isOn) {
/* 453 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL", "discardCachedContent()");
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
/* 464 */     if (Trace.isOn) {
/* 465 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFIL", "writeCachedContent()");
/*     */     }
/* 467 */     setParameter(this.parameter);
/* 468 */     setValues(this.values);
/*     */     
/* 470 */     if (Trace.isOn)
/* 471 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFIL", "writeCachedContent()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\MQCFIL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */