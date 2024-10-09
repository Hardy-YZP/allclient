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
/*     */ public class MQCFIN
/*     */   extends PCFParameter
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFIN.java";
/*     */   
/*     */   static {
/*  52 */     if (Trace.isOn) {
/*  53 */       Trace.data("com.ibm.mq.headers.pcf.MQCFIN", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFIN.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   static final HeaderType TYPE = new HeaderType("MQCFIN");
/*     */   
/*     */   protected static final int type = 3;
/*     */   
/*     */   protected static final int strucLength = 16;
/*     */   
/*  68 */   static final HeaderField Type = TYPE.addMQLong("Type", 3);
/*  69 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 16);
/*  70 */   static final HeaderField Parameter = TYPE.addMQLong("Parameter");
/*  71 */   static final HeaderField Value = TYPE.addMQLong("Value");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int write(Object message, int parameter, int value) throws IOException {
/*  85 */     if (Trace.isOn) {
/*  86 */       Trace.entry("com.ibm.mq.headers.pcf.MQCFIN", "write(Object,int,int)", new Object[] { message, 
/*  87 */             Integer.valueOf(parameter), Integer.valueOf(value) });
/*     */     }
/*  89 */     MessageWrapper wrappedMessage = MessageWrapper.wrap((DataOutput)message);
/*  90 */     wrappedMessage.writeInt(3);
/*  91 */     wrappedMessage.writeInt(16);
/*  92 */     wrappedMessage.writeInt(parameter);
/*  93 */     wrappedMessage.writeInt(value);
/*     */     
/*  95 */     if (Trace.isOn) {
/*  96 */       Trace.exit("com.ibm.mq.headers.pcf.MQCFIN", "write(Object,int,int)", 
/*  97 */           Integer.valueOf(16));
/*     */     }
/*  99 */     return 16;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFIN() {
/* 106 */     super(TYPE);
/* 107 */     if (Trace.isOn) {
/* 108 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIN", "<init>()");
/*     */     }
/* 110 */     if (Trace.isOn) {
/* 111 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIN", "<init>()");
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
/*     */   public MQCFIN(DataInput message) throws MQDataException, IOException {
/* 124 */     this();
/* 125 */     if (Trace.isOn) {
/* 126 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIN", "<init>(DataInput)", new Object[] { message });
/*     */     }
/*     */     
/* 129 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 131 */     if (Trace.isOn) {
/* 132 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIN", "<init>(DataInput)");
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
/*     */   public MQCFIN(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 148 */     this();
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIN", "<init>(DataInput,int,int)", new Object[] { message, 
/* 151 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 154 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 156 */     catch (MQDataException mde) {
/* 157 */       if (Trace.isOn) {
/* 158 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFIN", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/*     */ 
/*     */       
/* 162 */       if (Trace.isOn) {
/* 163 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFIN", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/*     */       
/* 166 */       throw mde;
/*     */     }
/* 168 */     catch (IOException ioe) {
/* 169 */       if (Trace.isOn) {
/* 170 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFIN", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/*     */       
/* 173 */       if (Trace.isOn) {
/* 174 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFIN", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/*     */       
/* 177 */       throw ioe;
/*     */     }
/* 179 */     catch (Exception e) {
/* 180 */       if (Trace.isOn) {
/* 181 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFIN", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/*     */       
/* 184 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 185 */       if (Trace.isOn) {
/* 186 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFIN", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 189 */       throw traceRet1;
/*     */     } 
/* 191 */     if (Trace.isOn) {
/* 192 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIN", "<init>(DataInput,int,int)");
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
/*     */   public MQCFIN(int parameter, int value) {
/* 204 */     this();
/* 205 */     if (Trace.isOn)
/* 206 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIN", "<init>(int,int)", new Object[] {
/* 207 */             Integer.valueOf(parameter), Integer.valueOf(value)
/*     */           }); 
/* 209 */     setParameter(parameter);
/* 210 */     setIntValue(value);
/*     */     
/* 212 */     if (Trace.isOn) {
/* 213 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIN", "<init>(int,int)");
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
/*     */   public boolean equals(Object obj) {
/* 226 */     if (Trace.isOn) {
/* 227 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIN", "equals(Object)", new Object[] { obj });
/*     */     }
/* 229 */     if (obj != null && obj instanceof MQCFIN) {
/* 230 */       MQCFIN other = (MQCFIN)obj;
/*     */ 
/*     */       
/* 233 */       boolean traceRet1 = (other.getParameter() == getParameter() && other.getValue() == getValue());
/* 234 */       if (Trace.isOn) {
/* 235 */         Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIN", "equals(Object)", 
/* 236 */             Boolean.valueOf(traceRet1), 1);
/*     */       }
/* 238 */       return traceRet1;
/*     */     } 
/* 240 */     if (Trace.isOn) {
/* 241 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIN", "equals(Object)", Boolean.valueOf(false), 2);
/*     */     }
/*     */     
/* 244 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 252 */     if (Trace.isOn) {
/* 253 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFIN", "hashCode()");
/*     */     }
/* 255 */     int hashCode = 0;
/* 256 */     hashCode += getParameter();
/* 257 */     hashCode += 31 * getIntValue();
/*     */     
/* 259 */     if (Trace.isOn) {
/* 260 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFIN", "hashCode()", Integer.valueOf(hashCode));
/*     */     }
/* 262 */     return hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 272 */     int traceRet1 = getIntValue(Type);
/* 273 */     if (Trace.isOn) {
/* 274 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN", "getType()", "getter", 
/* 275 */           Integer.valueOf(traceRet1));
/*     */     }
/* 277 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 286 */     int traceRet1 = getIntValue(StrucLength);
/* 287 */     if (Trace.isOn) {
/* 288 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN", "getStrucLength()", "getter", 
/* 289 */           Integer.valueOf(traceRet1));
/*     */     }
/* 291 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameter() {
/* 301 */     int traceRet1 = getIntValue(Parameter);
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN", "getParameter()", "getter", 
/* 304 */           Integer.valueOf(traceRet1));
/*     */     }
/* 306 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameter(int value) {
/* 315 */     if (Trace.isOn) {
/* 316 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN", "setParameter(int)", "setter", 
/* 317 */           Integer.valueOf(value));
/*     */     }
/* 319 */     setIntValue(Parameter, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIntValue() {
/* 328 */     int traceRet1 = getIntValue(Value);
/* 329 */     if (Trace.isOn) {
/* 330 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN", "getIntValue()", "getter", 
/* 331 */           Integer.valueOf(traceRet1));
/*     */     }
/* 333 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIntValue(int value) {
/* 342 */     if (Trace.isOn) {
/* 343 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN", "setIntValue(int)", "setter", 
/* 344 */           Integer.valueOf(value));
/*     */     }
/* 346 */     setIntValue(Value, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValue() {
/* 356 */     Object traceRet1 = Integer.valueOf(getIntValue());
/*     */     
/* 358 */     if (Trace.isOn) {
/* 359 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN", "getValue()", "getter", traceRet1);
/*     */     }
/* 361 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringValue() {
/* 369 */     String traceRet1 = Integer.toString(getIntValue());
/* 370 */     if (Trace.isOn) {
/* 371 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFIN", "getStringValue()", "getter", traceRet1);
/*     */     }
/* 373 */     return traceRet1;
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\MQCFIN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */