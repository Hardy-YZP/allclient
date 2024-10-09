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
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class MQCFSL
/*     */   extends PCFParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFSL.java";
/*     */   
/*     */   static {
/*  56 */     if (Trace.isOn) {
/*  57 */       Trace.data("com.ibm.mq.pcf.MQCFSL", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFSL.java");
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
/*  68 */   static final HeaderType TYPE = new HeaderType("MQCFSL");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int type = 6;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public int strucLength = 24;
/*     */ 
/*     */ 
/*     */   
/*     */   public int parameter;
/*     */ 
/*     */ 
/*     */   
/*     */   public int codedCharSetId;
/*     */ 
/*     */ 
/*     */   
/*     */   public int count;
/*     */ 
/*     */ 
/*     */   
/*     */   public int stringLength;
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] strings;
/*     */ 
/*     */   
/*     */   private final com.ibm.mq.headers.pcf.MQCFSL myDelegate;
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFSL() {
/* 109 */     super(TYPE);
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSL", "<init>()");
/*     */     }
/* 113 */     this.myDelegate = (com.ibm.mq.headers.pcf.MQCFSL)this.delegate;
/*     */     
/* 115 */     if (Trace.isOn) {
/* 116 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSL", "<init>()");
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
/*     */   public MQCFSL(MQMessage message) throws MQException, IOException {
/* 129 */     this();
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSL", "<init>(MQMessage)", new Object[] { message });
/*     */     }
/* 133 */     initialize(message);
/*     */     
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSL", "<init>(MQMessage)");
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
/*     */   public MQCFSL(DataInput message, int encoding, int characterSet) throws MQException, IOException {
/* 151 */     this();
/* 152 */     if (Trace.isOn) {
/* 153 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSL", "<init>(DataInput,int,int)", new Object[] { message, 
/* 154 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 157 */       this.delegate.read(message, encoding, characterSet);
/*     */     }
/* 159 */     catch (MQDataException mqe) {
/* 160 */       if (Trace.isOn) {
/* 161 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFSL", "<init>(DataInput,int,int)", (Throwable)mqe, 1);
/*     */       }
/*     */       
/* 164 */       MQException traceRet1 = new MQException(mqe.completionCode, mqe.reasonCode, mqe.exceptionSource);
/*     */       
/* 166 */       if (Trace.isOn) {
/* 167 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFSL", "<init>(DataInput,int,int)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 170 */       throw traceRet1;
/*     */     }
/* 172 */     catch (Exception e) {
/* 173 */       if (Trace.isOn) {
/* 174 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFSL", "<init>(DataInput,int,int)", e, 2);
/*     */       }
/* 176 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 177 */       if (Trace.isOn) {
/* 178 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFSL", "<init>(DataInput,int,int)", traceRet2, 2);
/*     */       }
/* 180 */       throw traceRet2;
/*     */     } 
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSL", "<init>(DataInput,int,int)");
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
/*     */   public MQCFSL(int parameter, String[] strings) {
/* 195 */     this();
/* 196 */     if (Trace.isOn)
/* 197 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSL", "<init>(int,String [ ])", new Object[] {
/* 198 */             Integer.valueOf(parameter), strings
/*     */           }); 
/* 200 */     setParameter(this.parameter = parameter);
/* 201 */     setStrings(this.strings = strings);
/*     */     
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSL", "<init>(int,String [ ])");
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
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSL", "equals(Object)", new Object[] { obj });
/*     */     }
/* 226 */     if (obj != null && obj instanceof MQCFSL) {
/* 227 */       MQCFSL other = (MQCFSL)obj;
/* 228 */       String[] otherValues = other.strings, values = this.strings;
/*     */       
/* 230 */       if (other.parameter == this.parameter && other
/* 231 */         .getCodedCharSetId() == getCodedCharSetId() && otherValues != null && values != null && otherValues.length == values.length) {
/*     */ 
/*     */ 
/*     */         
/* 235 */         int i = values.length;
/* 236 */         boolean match = true;
/*     */         
/* 238 */         while (match && i-- > 0) {
/* 239 */           if (otherValues[i] != null) {
/* 240 */             match = otherValues[i].equals(values[i]); continue;
/*     */           } 
/* 242 */           match = (values[i] == null);
/*     */         } 
/*     */ 
/*     */         
/* 246 */         if (Trace.isOn) {
/* 247 */           Trace.exit(this, "com.ibm.mq.pcf.MQCFSL", "equals(Object)", Boolean.valueOf(match), 1);
/*     */         }
/* 249 */         return match;
/*     */       } 
/* 251 */       if (Trace.isOn) {
/* 252 */         Trace.exit(this, "com.ibm.mq.pcf.MQCFSL", "equals(Object)", Boolean.valueOf(false), 2);
/*     */       }
/* 254 */       return false;
/*     */     } 
/* 256 */     if (Trace.isOn) {
/* 257 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSL", "equals(Object)", Boolean.valueOf(false), 3);
/*     */     }
/* 259 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 267 */     if (Trace.isOn) {
/* 268 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSL", "hashCode()");
/*     */     }
/* 270 */     int hashCode = 0;
/* 271 */     hashCode += getParameter() * 31;
/* 272 */     hashCode += getCodedCharSetId() * 37;
/* 273 */     hashCode += Arrays.hashCode((Object[])getStrings());
/*     */     
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSL", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 278 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 288 */     int traceRet1 = this.myDelegate.getType();
/* 289 */     if (Trace.isOn) {
/* 290 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSL", "getType()", "getter", Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 293 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 302 */     int traceRet1 = this.myDelegate.getStrucLength();
/* 303 */     if (Trace.isOn) {
/* 304 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSL", "getStrucLength()", "getter", 
/* 305 */           Integer.valueOf(traceRet1));
/*     */     }
/* 307 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameter() {
/* 317 */     int traceRet1 = this.myDelegate.getParameter();
/* 318 */     if (Trace.isOn) {
/* 319 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSL", "getParameter()", "getter", 
/* 320 */           Integer.valueOf(traceRet1));
/*     */     }
/* 322 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameter(int value) {
/* 331 */     if (Trace.isOn) {
/* 332 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSL", "setParameter(int)", "setter", 
/* 333 */           Integer.valueOf(value));
/*     */     }
/* 335 */     this.myDelegate.setParameter(this.parameter = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 344 */     int traceRet1 = this.myDelegate.getCodedCharSetId();
/* 345 */     if (Trace.isOn) {
/* 346 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSL", "getCodedCharSetId()", "getter", 
/* 347 */           Integer.valueOf(traceRet1));
/*     */     }
/* 349 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int value) {
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSL", "setCodedCharSetId(int)", "setter", 
/* 360 */           Integer.valueOf(value));
/*     */     }
/*     */ 
/*     */     
/* 364 */     this.myDelegate.setCodedCharSetId(this.codedCharSetId = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCount() {
/* 373 */     int traceRet1 = this.myDelegate.getCount();
/* 374 */     if (Trace.isOn) {
/* 375 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSL", "getCount()", "getter", Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 378 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStringLength() {
/* 387 */     int traceRet1 = this.myDelegate.getStringLength();
/* 388 */     if (Trace.isOn) {
/* 389 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSL", "getStringLength()", "getter", 
/* 390 */           Integer.valueOf(traceRet1));
/*     */     }
/* 392 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getStrings() {
/* 401 */     String[] traceRet1 = this.myDelegate.getStrings();
/* 402 */     if (Trace.isOn) {
/* 403 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSL", "getStrings()", "getter", traceRet1);
/*     */     }
/* 405 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrings(String[] value) {
/* 414 */     if (Trace.isOn) {
/* 415 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSL", "setStrings(String [ ])", "setter", value);
/*     */     }
/* 417 */     this.myDelegate.setStrings(this.strings = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 425 */     Object traceRet1 = getStrings();
/* 426 */     if (Trace.isOn) {
/* 427 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSL", "getValue()", "getter", traceRet1);
/*     */     }
/* 429 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 437 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 439 */     String[] strings = getStrings();
/*     */     
/* 441 */     for (int i = 0; i < strings.length; i++) {
/* 442 */       sb.append(strings[i]);
/* 443 */       sb.append('\n');
/*     */     } 
/*     */     
/* 446 */     if (sb.length() > 0) {
/* 447 */       sb.setLength(sb.length() - 1);
/*     */     }
/*     */     
/* 450 */     String traceRet1 = new String(sb);
/*     */     
/* 452 */     if (Trace.isOn) {
/* 453 */       Trace.data(this, "com.ibm.mq.pcf.MQCFSL", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 455 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readCachedContent() throws MQException, IOException {
/* 465 */     if (Trace.isOn) {
/* 466 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSL", "readCachedContent()");
/*     */     }
/* 468 */     this.strucLength = getStrucLength();
/* 469 */     this.parameter = getParameter();
/* 470 */     this.codedCharSetId = getCodedCharSetId();
/* 471 */     this.count = getCount();
/* 472 */     this.stringLength = getStringLength();
/* 473 */     this.strings = getStrings();
/*     */     
/* 475 */     if (Trace.isOn) {
/* 476 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSL", "readCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discardCachedContent() {
/* 486 */     if (Trace.isOn) {
/* 487 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSL", "discardCachedContent()");
/*     */     }
/* 489 */     this.strucLength = 24;
/* 490 */     this.parameter = 0;
/* 491 */     this.codedCharSetId = 0;
/* 492 */     this.count = 0;
/* 493 */     this.stringLength = 0;
/* 494 */     this.strings = null;
/*     */     
/* 496 */     if (Trace.isOn) {
/* 497 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSL", "discardCachedContent()");
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
/* 508 */     if (Trace.isOn) {
/* 509 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFSL", "writeCachedContent()");
/*     */     }
/* 511 */     setParameter(this.parameter);
/* 512 */     setCodedCharSetId(this.codedCharSetId);
/* 513 */     setStrings(this.strings);
/*     */     
/* 515 */     if (Trace.isOn)
/* 516 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFSL", "writeCachedContent()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\MQCFSL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */