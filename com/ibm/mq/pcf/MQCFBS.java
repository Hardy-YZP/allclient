/*     */ package com.ibm.mq.pcf;
/*     */ 
/*     */ import com.ibm.mq.MQException;
/*     */ import com.ibm.mq.MQMessage;
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
/*     */ import com.ibm.mq.headers.internal.HexString;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ import java.io.DataInput;
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.Charset;
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
/*     */ public class MQCFBS
/*     */   extends PCFParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFBS.java";
/*     */   
/*     */   static {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.data("com.ibm.mq.pcf.MQCFBS", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFBS.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   static final HeaderType TYPE = new HeaderType("MQCFBS");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int type = 9;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public int strucLength = 16;
/*     */ 
/*     */ 
/*     */   
/*     */   public int parameter;
/*     */ 
/*     */ 
/*     */   
/*     */   public int stringLength;
/*     */ 
/*     */ 
/*     */   
/*  95 */   public byte[] string = new byte[0];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final com.ibm.mq.headers.pcf.MQCFBS myDelegate;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String asHexString(byte[] bytes) {
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.entry("com.ibm.mq.pcf.MQCFBS", "asHexString(byte [ ])", new Object[] { bytes });
/*     */     }
/* 109 */     String traceRet1 = (bytes == null) ? null : HexString.hexString(bytes);
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit("com.ibm.mq.pcf.MQCFBS", "asHexString(byte [ ])", traceRet1);
/*     */     }
/* 113 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFBS() {
/* 120 */     super(TYPE);
/* 121 */     if (Trace.isOn) {
/* 122 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBS", "<init>()");
/*     */     }
/* 124 */     this.myDelegate = (com.ibm.mq.headers.pcf.MQCFBS)this.delegate;
/*     */     
/* 126 */     if (Trace.isOn) {
/* 127 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBS", "<init>()");
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
/*     */   public MQCFBS(MQMessage message) throws MQException, IOException {
/* 140 */     this();
/* 141 */     if (Trace.isOn) {
/* 142 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBS", "<init>(MQMessage)", new Object[] { message });
/*     */     }
/* 144 */     initialize(message);
/*     */     
/* 146 */     if (Trace.isOn) {
/* 147 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBS", "<init>(MQMessage)");
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
/*     */   public MQCFBS(DataInput message, int encoding, int characterSet) throws MQException, IOException {
/* 162 */     this();
/* 163 */     if (Trace.isOn) {
/* 164 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBS", "<init>(DataInput,int,int)", new Object[] { message, 
/* 165 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 168 */       this.delegate.read(message, encoding, characterSet);
/*     */     }
/* 170 */     catch (MQDataException mqe) {
/* 171 */       if (Trace.isOn) {
/* 172 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFBS", "<init>(DataInput,int,int)", (Throwable)mqe, 1);
/*     */       }
/*     */       
/* 175 */       MQException traceRet1 = new MQException(mqe.completionCode, mqe.reasonCode, mqe.exceptionSource);
/*     */       
/* 177 */       if (Trace.isOn) {
/* 178 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFBS", "<init>(DataInput,int,int)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 181 */       throw traceRet1;
/*     */     }
/* 183 */     catch (Exception e) {
/* 184 */       if (Trace.isOn) {
/* 185 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFBS", "<init>(DataInput,int,int)", e, 2);
/*     */       }
/* 187 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 188 */       if (Trace.isOn) {
/* 189 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFBS", "<init>(DataInput,int,int)", traceRet2, 2);
/*     */       }
/* 191 */       throw traceRet2;
/*     */     } 
/* 193 */     if (Trace.isOn) {
/* 194 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBS", "<init>(DataInput,int,int)");
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
/*     */   public MQCFBS(int parameter, String string) {
/* 207 */     this();
/* 208 */     if (Trace.isOn)
/* 209 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBS", "<init>(int,String)", new Object[] {
/* 210 */             Integer.valueOf(parameter), string
/*     */           }); 
/* 212 */     setParameter(this.parameter = parameter);
/* 213 */     setString(this.string = (string == null) ? null : string.getBytes(Charset.defaultCharset()));
/*     */     
/* 215 */     if (Trace.isOn) {
/* 216 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBS", "<init>(int,String)");
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
/*     */   public MQCFBS(int parameter, byte[] bytes) {
/* 228 */     this();
/* 229 */     if (Trace.isOn)
/* 230 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBS", "<init>(int,byte [ ])", new Object[] {
/* 231 */             Integer.valueOf(parameter), bytes
/*     */           }); 
/* 233 */     setParameter(this.parameter = parameter);
/* 234 */     setString(this.string = bytes);
/*     */     
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBS", "<init>(int,byte [ ])");
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
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBS", "equals(Object)", new Object[] { obj });
/*     */     }
/* 255 */     if (obj != null && obj instanceof MQCFBS) {
/* 256 */       MQCFBS other = (MQCFBS)obj;
/* 257 */       byte[] otherValue = other.string, value = this.string;
/*     */ 
/*     */ 
/*     */       
/* 261 */       boolean traceRet1 = (other.parameter == this.parameter && otherValue != null && value != null && Arrays.equals(value, otherValue));
/* 262 */       if (Trace.isOn) {
/* 263 */         Trace.exit(this, "com.ibm.mq.pcf.MQCFBS", "equals(Object)", 
/* 264 */             Boolean.valueOf(traceRet1), 1);
/*     */       }
/* 266 */       return traceRet1;
/*     */     } 
/* 268 */     if (Trace.isOn) {
/* 269 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBS", "equals(Object)", Boolean.valueOf(false), 2);
/*     */     }
/* 271 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 279 */     if (Trace.isOn) {
/* 280 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBS", "hashCode()");
/*     */     }
/* 282 */     int hashCode = 0;
/* 283 */     hashCode += getParameter();
/* 284 */     hashCode += Arrays.hashCode(getString());
/*     */     
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBS", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 289 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 300 */     int traceRet1 = this.myDelegate.getType();
/* 301 */     if (Trace.isOn) {
/* 302 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBS", "getType()", "getter", Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 305 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 314 */     int traceRet1 = this.myDelegate.getStrucLength();
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBS", "getStrucLength()", "getter", 
/* 317 */           Integer.valueOf(traceRet1));
/*     */     }
/* 319 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameter() {
/* 329 */     int traceRet1 = this.myDelegate.getParameter();
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBS", "getParameter()", "getter", 
/* 332 */           Integer.valueOf(traceRet1));
/*     */     }
/* 334 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameter(int value) {
/* 343 */     if (Trace.isOn) {
/* 344 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBS", "setParameter(int)", "setter", 
/* 345 */           Integer.valueOf(value));
/*     */     }
/* 347 */     this.myDelegate.setParameter(this.parameter = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStringLength() {
/* 356 */     int traceRet1 = this.myDelegate.getStringLength();
/* 357 */     if (Trace.isOn) {
/* 358 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBS", "getStringLength()", "getter", 
/* 359 */           Integer.valueOf(traceRet1));
/*     */     }
/* 361 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getString() {
/* 370 */     byte[] traceRet1 = this.myDelegate.getString();
/* 371 */     if (Trace.isOn) {
/* 372 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBS", "getString()", "getter", traceRet1);
/*     */     }
/* 374 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setString(byte[] value) {
/* 383 */     if (Trace.isOn) {
/* 384 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBS", "setString(byte [ ])", "setter", value);
/*     */     }
/* 386 */     this.myDelegate.setString(this.string = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 394 */     Object traceRet1 = getString();
/* 395 */     if (Trace.isOn) {
/* 396 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBS", "getValue()", "getter", traceRet1);
/*     */     }
/* 398 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 406 */     String traceRet1 = HexString.hexString(getString());
/* 407 */     if (Trace.isOn) {
/* 408 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBS", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 410 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readCachedContent() throws MQException, IOException {
/* 420 */     if (Trace.isOn) {
/* 421 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBS", "readCachedContent()");
/*     */     }
/* 423 */     this.strucLength = getStrucLength();
/* 424 */     this.parameter = getParameter();
/* 425 */     this.stringLength = getStringLength();
/* 426 */     this.string = getString();
/*     */     
/* 428 */     if (Trace.isOn) {
/* 429 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBS", "readCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discardCachedContent() {
/* 439 */     if (Trace.isOn) {
/* 440 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBS", "discardCachedContent()");
/*     */     }
/* 442 */     this.strucLength = 16;
/* 443 */     this.parameter = 0;
/* 444 */     this.stringLength = 0;
/* 445 */     this.string = null;
/*     */     
/* 447 */     if (Trace.isOn) {
/* 448 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBS", "discardCachedContent()");
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
/* 459 */     if (Trace.isOn) {
/* 460 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFBS", "writeCachedContent()");
/*     */     }
/* 462 */     setParameter(this.parameter);
/* 463 */     setString(this.string);
/*     */     
/* 465 */     if (Trace.isOn) {
/* 466 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFBS", "writeCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeaderVersion() {
/* 475 */     int traceRet1 = this.myDelegate.getHeaderVersion();
/* 476 */     if (Trace.isOn) {
/* 477 */       Trace.data(this, "com.ibm.mq.pcf.MQCFBS", "getHeaderVersion()", "getter", 
/* 478 */           Integer.valueOf(traceRet1));
/*     */     }
/* 480 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\MQCFBS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */