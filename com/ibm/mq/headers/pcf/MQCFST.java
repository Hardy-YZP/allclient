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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQCFST
/*     */   extends PCFParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFST.java";
/*     */   
/*     */   static {
/*  55 */     if (Trace.isOn) {
/*  56 */       Trace.data("com.ibm.mq.headers.pcf.MQCFST", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFST.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   static final HeaderType TYPE = new HeaderType("MQCFST");
/*     */   
/*     */   static final int type = 4;
/*     */   
/*     */   static final int strucLength = 20;
/*     */   
/*  71 */   static final HeaderField Type = TYPE.addMQLong("Type", 4);
/*  72 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 20);
/*     */   
/*  74 */   static final HeaderField Parameter = TYPE.addMQLong("Parameter");
/*  75 */   static final HeaderField CodedCharSetId = TYPE.addMQLong("CodedCharSetId", 0);
/*  76 */   static final HeaderField StringLength = TYPE.addMQLong("StringLength");
/*  77 */   static final HeaderField String = TYPE.addMQChar("String", StringLength, StrucLength, CodedCharSetId); private static final int SIZEOF_INT = 4; private boolean stringIsNull; public MQCFST(DataInput message) throws MQDataException, IOException { this(); if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFST", "<init>(DataInput)", new Object[] { message });  read((DataInput)MessageWrapper.wrap(message)); if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFST", "<init>(DataInput)");  }
/*     */   public MQCFST(DataInput message, int encoding, int characterSet) throws MQDataException, IOException { this(); if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFST", "<init>(DataInput,int,int)", new Object[] { message, Integer.valueOf(encoding), Integer.valueOf(characterSet) });  try { read((DataInput)MessageWrapper.wrap(message), encoding, characterSet); }
/*     */     catch (MQDataException mde) { if (Trace.isOn)
/*     */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFST", "<init>(DataInput,int,int)", (Throwable)mde, 1);  if (Trace.isOn)
/*     */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFST", "<init>(DataInput,int,int)", (Throwable)mde, 1);  throw mde; }
/*     */     catch (IOException ioe)
/*     */     { if (Trace.isOn)
/*     */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFST", "<init>(DataInput,int,int)", ioe, 2);  if (Trace.isOn)
/*     */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFST", "<init>(DataInput,int,int)", ioe, 2);  throw ioe; }
/*     */     catch (Exception e)
/*     */     { if (Trace.isOn)
/*     */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFST", "<init>(DataInput,int,int)", e, 3);  RuntimeException traceRet1 = new RuntimeException(e); if (Trace.isOn)
/*     */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFST", "<init>(DataInput,int,int)", traceRet1, 3);  throw traceRet1; }
/*     */      if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFST", "<init>(DataInput,int,int)");  }
/*  95 */   public static int write(DataOutput message, int parameter, String string) throws IOException { if (Trace.isOn) {
/*  96 */       Trace.entry("com.ibm.mq.headers.pcf.MQCFST", "write(DataOutput,int,String)", new Object[] { message, 
/*  97 */             Integer.valueOf(parameter), string });
/*     */     }
/*  99 */     MessageWrapper wrappedMessage = MessageWrapper.wrap(message);
/* 100 */     int stringLength = getStringLength(string, wrappedMessage.getCharacterSet());
/*     */ 
/*     */ 
/*     */     
/* 104 */     int padLength = stringLength % 4;
/* 105 */     if (padLength != 0) {
/* 106 */       padLength = 4 - padLength;
/*     */     }
/*     */     
/* 109 */     int totalLength = 20 + stringLength + padLength;
/*     */     
/* 111 */     wrappedMessage.writeString("");
/* 112 */     wrappedMessage.writeInt(4);
/* 113 */     wrappedMessage.writeInt(totalLength);
/* 114 */     wrappedMessage.writeInt(parameter);
/* 115 */     wrappedMessage.writeInt(wrappedMessage.getCharacterSet());
/* 116 */     wrappedMessage.writeInt(stringLength);
/* 117 */     wrappedMessage.writeString(string);
/*     */     
/* 119 */     if (padLength != 0) {
/* 120 */       wrappedMessage.writeString(pads[padLength]);
/*     */     }
/*     */     
/* 123 */     if (Trace.isOn) {
/* 124 */       Trace.exit("com.ibm.mq.headers.pcf.MQCFST", "write(DataOutput,int,String)", 
/* 125 */           Integer.valueOf(totalLength));
/*     */     }
/* 127 */     return totalLength; }
/*     */   public MQCFST(int parameter, String string) { this(); if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFST", "<init>(int,String)", new Object[] { Integer.valueOf(parameter), string });  setParameter(parameter); setString(string); if (Trace.isOn) Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFST", "<init>(int,String)");  }
/*     */   public boolean equals(Object obj) { if (Trace.isOn) Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFST", "equals(Object)", new Object[] { obj });  if (obj != null && obj instanceof MQCFST) { MQCFST other = (MQCFST)obj; String otherValue = other.getString(), value = getString(); boolean traceRet1 = (other.getParameter() == getParameter() && other.getCodedCharSetId() == getCodedCharSetId() && otherValue != null && value != null && otherValue.equals(value)); if (Trace.isOn)
/*     */         Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFST", "equals(Object)", Boolean.valueOf(traceRet1), 1);  return traceRet1; }  if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFST", "equals(Object)", Boolean.valueOf(false), 2);  return false; }
/*     */   public int hashCode() { if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFST", "hashCode()");  int hashCode = 0; hashCode += getParameter() * 31; hashCode += getCodedCharSetId() * 37; hashCode += getValue().hashCode(); if (Trace.isOn)
/* 134 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFST", "hashCode()", Integer.valueOf(hashCode));  return hashCode; } public MQCFST() { super(TYPE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 404 */     this.stringIsNull = false;
/*     */     if (Trace.isOn)
/*     */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFST", "<init>()"); 
/*     */     if (Trace.isOn)
/*     */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFST", "<init>()");  }
/*     */   public int getType() { int traceRet1 = getIntValue(Type);
/*     */     if (Trace.isOn)
/*     */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFST", "getType()", "getter", Integer.valueOf(traceRet1)); 
/* 412 */     return traceRet1; } public String getString() { String traceRet1 = this.stringIsNull ? null : getStringValue(String);
/* 413 */     if (Trace.isOn) {
/* 414 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFST", "getString()", "getter", traceRet1);
/*     */     }
/* 416 */     return traceRet1; } public int getStrucLength() { int traceRet1 = getIntValue(StrucLength); if (Trace.isOn) Trace.data(this, "com.ibm.mq.headers.pcf.MQCFST", "getStrucLength()", "getter", Integer.valueOf(traceRet1));  return traceRet1; }
/*     */   public int getParameter() { int traceRet1 = getIntValue(Parameter); if (Trace.isOn) Trace.data(this, "com.ibm.mq.headers.pcf.MQCFST", "getParameter()", "getter", Integer.valueOf(traceRet1));  return traceRet1; }
/*     */   public void setParameter(int value) { if (Trace.isOn) Trace.data(this, "com.ibm.mq.headers.pcf.MQCFST", "setParameter(int)", "setter", Integer.valueOf(value));  setIntValue(Parameter, value); }
/*     */   public int getCodedCharSetId() { int traceRet1 = getIntValue(CodedCharSetId); if (Trace.isOn)
/*     */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFST", "getCodedCharSetId()", "getter", Integer.valueOf(traceRet1));  return traceRet1; }
/*     */   public void setCodedCharSetId(int value) { if (Trace.isOn)
/*     */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFST", "setCodedCharSetId(int)", "setter", Integer.valueOf(value));  setIntValue(CodedCharSetId, value); }
/*     */   public int getStringLength() { int traceRet1 = getIntValue(StringLength); if (Trace.isOn)
/*     */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFST", "getStringLength()", "getter", Integer.valueOf(traceRet1));  return traceRet1; }
/* 425 */   public void setString(String value) { if (Trace.isOn) {
/* 426 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFST", "setString(String)", "setter", value);
/*     */     }
/* 428 */     this.stringIsNull = (value == null);
/* 429 */     setStringValue(String, value); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 437 */     Object traceRet1 = getString();
/* 438 */     if (Trace.isOn) {
/* 439 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFST", "getValue()", "getter", traceRet1);
/*     */     }
/* 441 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 449 */     String traceRet1 = getString();
/* 450 */     if (Trace.isOn) {
/* 451 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFST", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 453 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\MQCFST.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */