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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQCFSL
/*     */   extends PCFParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFSL.java";
/*     */   
/*     */   static {
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.data("com.ibm.mq.headers.pcf.MQCFSL", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFSL.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   static final HeaderType TYPE = new HeaderType("MQCFSL");
/*     */   
/*     */   static final int type = 6;
/*     */   
/*     */   static final int strucLength = 24;
/*     */   
/*  71 */   static final HeaderField Type = TYPE.addMQLong("Type", 6);
/*  72 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 24);
/*     */   
/*  74 */   static final HeaderField Parameter = TYPE.addMQLong("Parameter");
/*  75 */   static final HeaderField CodedCharSetId = TYPE.addMQLong("CodedCharSetId", 0);
/*  76 */   static final HeaderField Count = TYPE.addMQLong("Count");
/*  77 */   static final HeaderField StringLength = TYPE.addMQLong("StringLength");
/*  78 */   static final HeaderField Strings = TYPE.addMQCharArray("Strings", Count, StringLength, StrucLength, CodedCharSetId);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFSL() {
/*  85 */     super(TYPE);
/*  86 */     if (Trace.isOn) {
/*  87 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFSL", "<init>()");
/*     */     }
/*  89 */     if (Trace.isOn) {
/*  90 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFSL", "<init>()");
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
/*     */   public MQCFSL(DataInput message) throws MQDataException, IOException {
/* 103 */     this();
/* 104 */     if (Trace.isOn) {
/* 105 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFSL", "<init>(DataInput)", new Object[] { message });
/*     */     }
/*     */     
/* 108 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFSL", "<init>(DataInput)");
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
/*     */   public MQCFSL(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 127 */     this();
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFSL", "<init>(DataInput,int,int)", new Object[] { message, 
/* 130 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 133 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 135 */     catch (MQDataException mde) {
/* 136 */       if (Trace.isOn) {
/* 137 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFSL", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/*     */ 
/*     */       
/* 141 */       if (Trace.isOn) {
/* 142 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFSL", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/* 144 */       throw mde;
/*     */     }
/* 146 */     catch (IOException ioe) {
/* 147 */       if (Trace.isOn) {
/* 148 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFSL", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/*     */       
/* 151 */       if (Trace.isOn) {
/* 152 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFSL", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 154 */       throw ioe;
/*     */     }
/* 156 */     catch (Exception e) {
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFSL", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/* 160 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 161 */       if (Trace.isOn) {
/* 162 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFSL", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 165 */       throw traceRet1;
/*     */     } 
/* 167 */     if (Trace.isOn) {
/* 168 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFSL", "<init>(DataInput,int,int)");
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
/* 180 */     this();
/* 181 */     if (Trace.isOn)
/* 182 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFSL", "<init>(int,String [ ])", new Object[] {
/* 183 */             Integer.valueOf(parameter), strings
/*     */           }); 
/* 185 */     setParameter(parameter);
/* 186 */     setStrings(strings);
/*     */     
/* 188 */     if (Trace.isOn) {
/* 189 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFSL", "<init>(int,String [ ])");
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
/* 208 */     if (Trace.isOn) {
/* 209 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFSL", "equals(Object)", new Object[] { obj });
/*     */     }
/* 211 */     if (obj != null && obj instanceof MQCFSL) {
/* 212 */       MQCFSL other = (MQCFSL)obj;
/* 213 */       String[] otherValues = other.getStrings(), values = getStrings();
/*     */       
/* 215 */       if (other.getParameter() == getParameter() && other
/* 216 */         .getCodedCharSetId() == getCodedCharSetId() && otherValues != null && values != null && otherValues.length == values.length) {
/*     */ 
/*     */ 
/*     */         
/* 220 */         int i = values.length;
/* 221 */         boolean match = true;
/*     */         
/* 223 */         while (match && i-- > 0) {
/* 224 */           if (otherValues[i] != null) {
/* 225 */             match = otherValues[i].equals(values[i]);
/*     */             continue;
/*     */           } 
/* 228 */           match = (values[i] == null);
/*     */         } 
/*     */ 
/*     */         
/* 232 */         if (Trace.isOn) {
/* 233 */           Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFSL", "equals(Object)", 
/* 234 */               Boolean.valueOf(match), 1);
/*     */         }
/* 236 */         return match;
/*     */       } 
/* 238 */       if (Trace.isOn) {
/* 239 */         Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFSL", "equals(Object)", Boolean.valueOf(false), 2);
/*     */       }
/* 241 */       return false;
/*     */     } 
/* 243 */     if (Trace.isOn) {
/* 244 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFSL", "equals(Object)", Boolean.valueOf(false), 3);
/*     */     }
/*     */     
/* 247 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 255 */     if (Trace.isOn) {
/* 256 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFSL", "hashCode()");
/*     */     }
/* 258 */     int hashCode = 0;
/* 259 */     hashCode += getParameter() * 31;
/* 260 */     hashCode += getCodedCharSetId() * 37;
/* 261 */     hashCode += Arrays.hashCode((Object[])getStrings());
/*     */     
/* 263 */     if (Trace.isOn) {
/* 264 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFSL", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 266 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 276 */     int traceRet1 = getIntValue(Type);
/* 277 */     if (Trace.isOn) {
/* 278 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSL", "getType()", "getter", 
/* 279 */           Integer.valueOf(traceRet1));
/*     */     }
/* 281 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 290 */     int traceRet1 = getIntValue(StrucLength);
/* 291 */     if (Trace.isOn) {
/* 292 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSL", "getStrucLength()", "getter", 
/* 293 */           Integer.valueOf(traceRet1));
/*     */     }
/* 295 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameter() {
/* 305 */     int traceRet1 = getIntValue(Parameter);
/* 306 */     if (Trace.isOn) {
/* 307 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSL", "getParameter()", "getter", 
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
/*     */   public void setParameter(int value) {
/* 319 */     if (Trace.isOn) {
/* 320 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSL", "setParameter(int)", "setter", 
/* 321 */           Integer.valueOf(value));
/*     */     }
/* 323 */     setIntValue(Parameter, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 332 */     int traceRet1 = getIntValue(CodedCharSetId);
/* 333 */     if (Trace.isOn) {
/* 334 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSL", "getCodedCharSetId()", "getter", 
/* 335 */           Integer.valueOf(traceRet1));
/*     */     }
/* 337 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int value) {
/* 346 */     if (Trace.isOn) {
/* 347 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSL", "setCodedCharSetId(int)", "setter", 
/* 348 */           Integer.valueOf(value));
/*     */     }
/*     */ 
/*     */     
/* 352 */     setIntValue(CodedCharSetId, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCount() {
/* 361 */     int traceRet1 = getIntValue(Count);
/* 362 */     if (Trace.isOn) {
/* 363 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSL", "getCount()", "getter", 
/* 364 */           Integer.valueOf(traceRet1));
/*     */     }
/* 366 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStringLength() {
/* 375 */     int traceRet1 = getIntValue(StringLength);
/* 376 */     if (Trace.isOn) {
/* 377 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSL", "getStringLength()", "getter", 
/* 378 */           Integer.valueOf(traceRet1));
/*     */     }
/* 380 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getStrings() {
/* 389 */     String[] traceRet1 = trimNulls((String[])getValue(Strings));
/* 390 */     if (Trace.isOn) {
/* 391 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSL", "getStrings()", "getter", traceRet1);
/*     */     }
/* 393 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrings(String[] value) {
/* 402 */     if (Trace.isOn) {
/* 403 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSL", "setStrings(String [ ])", "setter", value);
/*     */     }
/* 405 */     setValue(Strings, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 413 */     Object traceRet1 = getStrings();
/* 414 */     if (Trace.isOn) {
/* 415 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSL", "getValue()", "getter", traceRet1);
/*     */     }
/* 417 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 425 */     StringBuffer sb = new StringBuffer();
/* 426 */     String[] strings = getStrings();
/*     */     
/* 428 */     for (int i = 0; i < strings.length; i++) {
/* 429 */       sb.append(strings[i]);
/* 430 */       sb.append('\n');
/*     */     } 
/*     */     
/* 433 */     if (sb.length() > 0) {
/* 434 */       sb.setLength(sb.length() - 1);
/*     */     }
/*     */     
/* 437 */     String traceRet1 = new String(sb);
/*     */     
/* 439 */     if (Trace.isOn) {
/* 440 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFSL", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 442 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\MQCFSL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */