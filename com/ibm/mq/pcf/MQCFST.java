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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ public class MQCFST
/*     */   extends PCFParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFST.java";
/*     */   
/*     */   static {
/*  54 */     if (Trace.isOn) {
/*  55 */       Trace.data("com.ibm.mq.pcf.MQCFST", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFST.java");
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
/*  67 */   static final HeaderType TYPE = new HeaderType("MQCFST");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int type = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public int strucLength = 20;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int parameter;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int codedCharSetId;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int stringLength;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String string;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final com.ibm.mq.headers.pcf.MQCFST myDelegate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int write(MQMessage message, int parameter, String string) throws IOException {
/* 113 */     if (Trace.isOn) {
/* 114 */       Trace.entry("com.ibm.mq.pcf.MQCFST", "write(MQMessage,int,String)", new Object[] { message, 
/* 115 */             Integer.valueOf(parameter), string });
/*     */     }
/* 117 */     int traceRet1 = com.ibm.mq.headers.pcf.MQCFST.write((DataOutput)message, parameter, string);
/* 118 */     if (Trace.isOn) {
/* 119 */       Trace.exit("com.ibm.mq.pcf.MQCFST", "write(MQMessage,int,String)", 
/* 120 */           Integer.valueOf(traceRet1));
/*     */     }
/* 122 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFST() {
/* 129 */     super(TYPE);
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFST", "<init>()");
/*     */     }
/* 133 */     this.myDelegate = (com.ibm.mq.headers.pcf.MQCFST)this.delegate;
/*     */     
/* 135 */     if (Trace.isOn) {
/* 136 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFST", "<init>()");
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
/*     */   public MQCFST(MQMessage message) throws MQException, IOException {
/* 149 */     this();
/* 150 */     if (Trace.isOn) {
/* 151 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFST", "<init>(MQMessage)", new Object[] { message });
/*     */     }
/* 153 */     initialize(message);
/*     */     
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFST", "<init>(MQMessage)");
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
/*     */   public MQCFST(DataInput message, int encoding, int characterSet) throws MQException, IOException {
/* 171 */     this();
/* 172 */     if (Trace.isOn) {
/* 173 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFST", "<init>(DataInput,int,int)", new Object[] { message, 
/* 174 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 177 */       this.delegate.read(message, encoding, characterSet);
/*     */     }
/* 179 */     catch (MQDataException mqe) {
/* 180 */       if (Trace.isOn) {
/* 181 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFST", "<init>(DataInput,int,int)", (Throwable)mqe, 1);
/*     */       }
/*     */       
/* 184 */       MQException traceRet1 = new MQException(mqe.completionCode, mqe.reasonCode, mqe.exceptionSource);
/*     */       
/* 186 */       if (Trace.isOn) {
/* 187 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFST", "<init>(DataInput,int,int)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 190 */       throw traceRet1;
/*     */     }
/* 192 */     catch (Exception e) {
/* 193 */       if (Trace.isOn) {
/* 194 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFST", "<init>(DataInput,int,int)", e, 2);
/*     */       }
/* 196 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 197 */       if (Trace.isOn) {
/* 198 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFST", "<init>(DataInput,int,int)", traceRet2, 2);
/*     */       }
/* 200 */       throw traceRet2;
/*     */     } 
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFST", "<init>(DataInput,int,int)");
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
/*     */   public MQCFST(int parameter, String string) {
/* 215 */     this();
/* 216 */     if (Trace.isOn)
/* 217 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFST", "<init>(int,String)", new Object[] {
/* 218 */             Integer.valueOf(parameter), string
/*     */           }); 
/* 220 */     setParameter(this.parameter = parameter);
/* 221 */     setString(this.string = string);
/*     */     
/* 223 */     if (Trace.isOn) {
/* 224 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFST", "<init>(int,String)");
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
/*     */   public boolean equals(Object obj) {
/* 242 */     if (Trace.isOn) {
/* 243 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFST", "equals(Object)", new Object[] { obj });
/*     */     }
/* 245 */     if (obj != null && obj instanceof MQCFST) {
/* 246 */       MQCFST other = (MQCFST)obj;
/* 247 */       String otherValue = other.string, value = this.string;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 254 */       boolean traceRet1 = (other.parameter == this.parameter && other.getCodedCharSetId() == getCodedCharSetId() && otherValue != null && value != null && otherValue.equals(value));
/* 255 */       if (Trace.isOn) {
/* 256 */         Trace.exit(this, "com.ibm.mq.pcf.MQCFST", "equals(Object)", 
/* 257 */             Boolean.valueOf(traceRet1), 1);
/*     */       }
/* 259 */       return traceRet1;
/*     */     } 
/* 261 */     if (Trace.isOn) {
/* 262 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFST", "equals(Object)", Boolean.valueOf(false), 2);
/*     */     }
/* 264 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 272 */     if (Trace.isOn) {
/* 273 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFST", "hashCode()");
/*     */     }
/* 275 */     int hashCode = 0;
/* 276 */     hashCode += getParameter() * 31;
/* 277 */     hashCode += getCodedCharSetId() * 37;
/* 278 */     hashCode += getValue().hashCode();
/*     */     
/* 280 */     if (Trace.isOn) {
/* 281 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFST", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 283 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 291 */     int traceRet1 = this.myDelegate.getType();
/* 292 */     if (Trace.isOn) {
/* 293 */       Trace.data(this, "com.ibm.mq.pcf.MQCFST", "getType()", "getter", Integer.valueOf(traceRet1));
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
/* 307 */       Trace.data(this, "com.ibm.mq.pcf.MQCFST", "getStrucLength()", "getter", 
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
/* 322 */       Trace.data(this, "com.ibm.mq.pcf.MQCFST", "getParameter()", "getter", 
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
/* 335 */       Trace.data(this, "com.ibm.mq.pcf.MQCFST", "setParameter(int)", "setter", 
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
/*     */   public int getCodedCharSetId() {
/* 347 */     int traceRet1 = this.myDelegate.getCodedCharSetId();
/* 348 */     if (Trace.isOn) {
/* 349 */       Trace.data(this, "com.ibm.mq.pcf.MQCFST", "getCodedCharSetId()", "getter", 
/* 350 */           Integer.valueOf(traceRet1));
/*     */     }
/* 352 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int value) {
/* 361 */     if (Trace.isOn) {
/* 362 */       Trace.data(this, "com.ibm.mq.pcf.MQCFST", "setCodedCharSetId(int)", "setter", 
/* 363 */           Integer.valueOf(value));
/*     */     }
/*     */ 
/*     */     
/* 367 */     this.myDelegate.setCodedCharSetId(this.codedCharSetId = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStringLength() {
/* 376 */     int traceRet1 = this.myDelegate.getStringLength();
/* 377 */     if (Trace.isOn) {
/* 378 */       Trace.data(this, "com.ibm.mq.pcf.MQCFST", "getStringLength()", "getter", 
/* 379 */           Integer.valueOf(traceRet1));
/*     */     }
/* 381 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString() {
/* 390 */     String traceRet1 = this.myDelegate.getString();
/* 391 */     if (Trace.isOn) {
/* 392 */       Trace.data(this, "com.ibm.mq.pcf.MQCFST", "getString()", "getter", traceRet1);
/*     */     }
/* 394 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setString(String value) {
/* 403 */     if (Trace.isOn) {
/* 404 */       Trace.data(this, "com.ibm.mq.pcf.MQCFST", "setString(String)", "setter", value);
/*     */     }
/* 406 */     this.myDelegate.setString(this.string = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 414 */     Object traceRet1 = getString();
/* 415 */     if (Trace.isOn) {
/* 416 */       Trace.data(this, "com.ibm.mq.pcf.MQCFST", "getValue()", "getter", traceRet1);
/*     */     }
/* 418 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 426 */     String traceRet1 = getString();
/* 427 */     if (Trace.isOn) {
/* 428 */       Trace.data(this, "com.ibm.mq.pcf.MQCFST", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 430 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readCachedContent() throws MQException, IOException {
/* 440 */     if (Trace.isOn) {
/* 441 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFST", "readCachedContent()");
/*     */     }
/* 443 */     this.strucLength = getStrucLength();
/* 444 */     this.parameter = getParameter();
/* 445 */     this.codedCharSetId = getCodedCharSetId();
/* 446 */     this.stringLength = getStringLength();
/* 447 */     this.string = getString();
/*     */     
/* 449 */     if (Trace.isOn) {
/* 450 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFST", "readCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discardCachedContent() {
/* 460 */     if (Trace.isOn) {
/* 461 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFST", "discardCachedContent()");
/*     */     }
/* 463 */     this.strucLength = 20;
/* 464 */     this.parameter = 0;
/* 465 */     this.codedCharSetId = 0;
/* 466 */     this.stringLength = 0;
/* 467 */     this.string = null;
/*     */     
/* 469 */     if (Trace.isOn) {
/* 470 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFST", "discardCachedContent()");
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
/* 481 */     if (Trace.isOn) {
/* 482 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFST", "writeCachedContent()");
/*     */     }
/* 484 */     setParameter(this.parameter);
/* 485 */     setCodedCharSetId(this.codedCharSetId);
/* 486 */     setString(this.string);
/*     */     
/* 488 */     if (Trace.isOn)
/* 489 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFST", "writeCachedContent()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\MQCFST.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */