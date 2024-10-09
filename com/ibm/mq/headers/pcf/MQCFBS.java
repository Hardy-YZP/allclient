/*     */ package com.ibm.mq.headers.pcf;
/*     */ 
/*     */ import com.ibm.mq.headers.MQDataException;
/*     */ import com.ibm.mq.headers.internal.HeaderField;
/*     */ import com.ibm.mq.headers.internal.HeaderType;
/*     */ import com.ibm.mq.headers.internal.HexString;
/*     */ import com.ibm.mq.headers.internal.MessageWrapper;
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
/*     */ public class MQCFBS
/*     */   extends PCFParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFBS.java";
/*     */   
/*     */   static {
/*  57 */     if (Trace.isOn) {
/*  58 */       Trace.data("com.ibm.mq.headers.pcf.MQCFBS", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFBS.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   private static final HeaderType TYPE = new HeaderType("MQCFBS");
/*     */   
/*  68 */   private static final HeaderField Type = TYPE.addMQLong("Type", 9);
/*  69 */   private static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 16);
/*     */   
/*  71 */   private static final HeaderField Parameter = TYPE.addMQLong("Parameter");
/*  72 */   private static final HeaderField StringLength = TYPE.addMQLong("StringLength");
/*  73 */   private static final int HEADER_VERSION = 2; private boolean stringIsNull; public MQCFBS(DataInput message) throws MQDataException, IOException { this(); if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFBS", "<init>(DataInput)", new Object[] { message });  read((DataInput)MessageWrapper.wrap(message)); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFBS", "<init>(DataInput)");  } private static final HeaderField String = TYPE.addMQByte("String", StringLength, StrucLength);
/*     */   public MQCFBS(DataInput message, int encoding, int characterSet) throws MQDataException, IOException { this(); if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFBS", "<init>(DataInput,int,int)", new Object[] { message, Integer.valueOf(encoding), Integer.valueOf(characterSet) });  try { read((DataInput)MessageWrapper.wrap(message), encoding, characterSet); } catch (MQDataException mde) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFBS", "<init>(DataInput,int,int)", (Throwable)mde, 1);  if (Trace.isOn) Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFBS", "<init>(DataInput,int,int)", (Throwable)mde, 1);  throw mde; } catch (IOException ioe) { if (Trace.isOn) Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFBS", "<init>(DataInput,int,int)", ioe, 2);  if (Trace.isOn)
/*     */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFBS", "<init>(DataInput,int,int)", ioe, 2);  throw ioe; } catch (Exception e) { if (Trace.isOn)
/*     */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFBS", "<init>(DataInput,int,int)", e, 3);  RuntimeException traceRet1 = new RuntimeException(e); if (Trace.isOn)
/*     */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFBS", "<init>(DataInput,int,int)", traceRet1, 3);  throw traceRet1; }  if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFBS", "<init>(DataInput,int,int)");  }
/*     */   public MQCFBS(int parameter, String string) { this(); if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFBS", "<init>(int,String)", new Object[] { Integer.valueOf(parameter), string });  setParameter(parameter); setString((string == null) ? null : string.getBytes(Charset.defaultCharset())); if (Trace.isOn)
/*  81 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFBS", "<init>(int,String)");  } public MQCFBS() { super(TYPE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 337 */     this.stringIsNull = false; if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFBS", "<init>()");  if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFBS", "<init>()");  } public MQCFBS(int parameter, byte[] bytes) { this(); if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFBS", "<init>(int,byte [ ])", new Object[] { Integer.valueOf(parameter), bytes });  setParameter(parameter); setString(bytes); if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFBS", "<init>(int,byte [ ])");  }
/*     */   public boolean equals(Object obj) { if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFBS", "equals(Object)", new Object[] { obj });  if (obj != null && obj instanceof MQCFBS) { MQCFBS other = (MQCFBS)obj; byte[] otherValue = other.getString(), value = getString(); boolean traceRet1 = (other.getParameter() == getParameter() && otherValue != null && value != null && Arrays.equals(value, otherValue)); if (Trace.isOn)
/*     */         Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFBS", "equals(Object)", Boolean.valueOf(traceRet1), 1);  return traceRet1; }  if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFBS", "equals(Object)", Boolean.valueOf(false), 2);  return false; }
/* 345 */   public byte[] getString() { byte[] traceRet1 = this.stringIsNull ? null : getBytesValue(String);
/* 346 */     if (Trace.isOn) {
/* 347 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBS", "getString()", "getter", traceRet1);
/*     */     }
/* 349 */     return traceRet1; } public int hashCode() { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFBS", "hashCode()");  int hashCode = 0; hashCode += getParameter(); hashCode += Arrays.hashCode(getString()); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFBS", "hashCode()", Integer.valueOf(hashCode));  return hashCode; }
/*     */   public int getType() { int traceRet1 = getIntValue(Type); if (Trace.isOn) Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBS", "getType()", "getter", Integer.valueOf(traceRet1));  return traceRet1; }
/*     */   public int getStrucLength() { int traceRet1 = getIntValue(StrucLength); if (Trace.isOn) Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBS", "getStrucLength()", "getter", Integer.valueOf(traceRet1));  return traceRet1; }
/*     */   public int getParameter() { int traceRet1 = getIntValue(Parameter); if (Trace.isOn)
/*     */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBS", "getParameter()", "getter", Integer.valueOf(traceRet1));  return traceRet1; }
/*     */   public void setParameter(int value) { if (Trace.isOn)
/*     */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBS", "setParameter(int)", "setter", Integer.valueOf(value));  setIntValue(Parameter, value); }
/*     */   public int getStringLength() { int traceRet1 = getIntValue(StringLength); if (Trace.isOn)
/*     */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBS", "getStringLength()", "getter", Integer.valueOf(traceRet1));  return traceRet1; }
/* 358 */   public void setString(byte[] value) { if (Trace.isOn) {
/* 359 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBS", "setString(byte [ ])", "setter", value);
/*     */     }
/* 361 */     this.stringIsNull = (value == null);
/* 362 */     setBytesValue(String, value); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 370 */     Object traceRet1 = getString();
/* 371 */     if (Trace.isOn) {
/* 372 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBS", "getValue()", "getter", traceRet1);
/*     */     }
/* 374 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 382 */     String traceRet1 = HexString.hexString(getString());
/* 383 */     if (Trace.isOn) {
/* 384 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBS", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 386 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeaderVersion() {
/* 394 */     if (Trace.isOn) {
/* 395 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFBS", "getHeaderVersion()", "getter", 
/* 396 */           Integer.valueOf(2));
/*     */     }
/* 398 */     return 2;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\MQCFBS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */