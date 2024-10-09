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
/*     */ 
/*     */ 
/*     */ public class MQCFH
/*     */   extends PCFHeader
/*     */ {
/*     */   static final String sccsid = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFH.java";
/*     */   protected static final int SIZE = 36;
/*     */   
/*     */   static {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.data("com.ibm.mq.headers.pcf.MQCFH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/headers/pcf/MQCFH.java");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   static final HeaderType TYPE = new HeaderType("MQCFH");
/*     */   
/*     */   private static final int type = 1;
/*     */   
/*     */   private static final int strucLength = 36;
/*     */   
/*     */   private static final int version = 1;
/*  76 */   static final HeaderField Type = TYPE.addMQLong("Type", 1);
/*  77 */   static final HeaderField StrucLength = TYPE.addMQLong("StrucLength", 36);
/*  78 */   static final HeaderField Version = TYPE.addMQLong("Version", 1);
/*  79 */   static final HeaderField Command = TYPE.addMQLong("Command");
/*  80 */   static final HeaderField MsgSeqNumber = TYPE.addMQLong("MsgSeqNumber", 1);
/*  81 */   static final HeaderField Control = TYPE.addMQLong("Control", 1);
/*  82 */   static final HeaderField CompCode = TYPE.addMQLong("CompCode");
/*  83 */   static final HeaderField Reason = TYPE.addMQLong("Reason");
/*  84 */   static final HeaderField ParameterCount = TYPE.addMQLong("ParameterCount");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int write(Object message, int command, int parameterCount) throws IOException {
/*  99 */     if (Trace.isOn) {
/* 100 */       Trace.entry("com.ibm.mq.headers.pcf.MQCFH", "write(Object,int,int)", new Object[] { message, 
/* 101 */             Integer.valueOf(command), Integer.valueOf(parameterCount) });
/*     */     }
/*     */     
/* 104 */     int traceRet1 = write((DataOutput)message, command, parameterCount, 1, 1);
/*     */     
/* 106 */     if (Trace.isOn) {
/* 107 */       Trace.exit("com.ibm.mq.headers.pcf.MQCFH", "write(Object,int,int)", 
/* 108 */           Integer.valueOf(traceRet1));
/*     */     }
/* 110 */     return traceRet1;
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
/*     */   public static int write(DataOutput message, int command, int parameterCount, int type, int version) throws IOException {
/* 128 */     if (Trace.isOn) {
/* 129 */       Trace.entry("com.ibm.mq.headers.pcf.MQCFH", "write(DataOutput,int,int,int,int)", new Object[] { message, 
/* 130 */             Integer.valueOf(command), Integer.valueOf(parameterCount), 
/* 131 */             Integer.valueOf(type), Integer.valueOf(version) });
/*     */     }
/* 133 */     message.writeInt(type);
/* 134 */     message.writeInt(36);
/* 135 */     message.writeInt(version);
/* 136 */     message.writeInt(command);
/* 137 */     message.writeInt(1);
/* 138 */     message.writeInt(1);
/* 139 */     message.writeInt(0);
/* 140 */     message.writeInt(0);
/* 141 */     message.writeInt(parameterCount);
/*     */     
/* 143 */     if (Trace.isOn) {
/* 144 */       Trace.exit("com.ibm.mq.headers.pcf.MQCFH", "write(DataOutput,int,int,int,int)", 
/* 145 */           Integer.valueOf(36));
/*     */     }
/* 147 */     return 36;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFH() {
/* 154 */     super(TYPE);
/* 155 */     if (Trace.isOn) {
/* 156 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFH", "<init>()");
/*     */     }
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFH", "<init>()");
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
/*     */   public MQCFH(DataInput message) throws MQDataException, IOException {
/* 172 */     this();
/* 173 */     if (Trace.isOn) {
/* 174 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFH", "<init>(DataInput)", new Object[] { message });
/*     */     }
/*     */     
/* 177 */     read((DataInput)MessageWrapper.wrap(message));
/*     */     
/* 179 */     if (Trace.isOn) {
/* 180 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFH", "<init>(DataInput)");
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
/*     */   public MQCFH(DataInput message, int encoding, int characterSet) throws MQDataException, IOException {
/* 196 */     this();
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFH", "<init>(DataInput,int,int)", new Object[] { message, 
/* 199 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 202 */       read((DataInput)MessageWrapper.wrap(message), encoding, characterSet);
/*     */     }
/* 204 */     catch (MQDataException mde) {
/* 205 */       if (Trace.isOn) {
/* 206 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFH", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/*     */ 
/*     */       
/* 210 */       if (Trace.isOn) {
/* 211 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFH", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/* 213 */       throw mde;
/*     */     }
/* 215 */     catch (IOException ioe) {
/* 216 */       if (Trace.isOn) {
/* 217 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFH", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/*     */       
/* 220 */       if (Trace.isOn) {
/* 221 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFH", "<init>(DataInput,int,int)", ioe, 2);
/*     */       }
/* 223 */       throw ioe;
/*     */     }
/* 225 */     catch (Exception e) {
/* 226 */       if (Trace.isOn) {
/* 227 */         Trace.catchBlock(this, "com.ibm.mq.headers.pcf.MQCFH", "<init>(DataInput,int,int)", e, 3);
/*     */       }
/* 229 */       RuntimeException traceRet1 = new RuntimeException(e);
/* 230 */       if (Trace.isOn) {
/* 231 */         Trace.throwing(this, "com.ibm.mq.headers.pcf.MQCFH", "<init>(DataInput,int,int)", traceRet1, 3);
/*     */       }
/*     */       
/* 234 */       throw traceRet1;
/*     */     } 
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFH", "<init>(DataInput,int,int)");
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
/*     */   public MQCFH(int command, int parameterCount) {
/* 249 */     this();
/* 250 */     if (Trace.isOn)
/* 251 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFH", "<init>(int,int)", new Object[] {
/* 252 */             Integer.valueOf(command), Integer.valueOf(parameterCount)
/*     */           }); 
/* 254 */     setCommand(command);
/* 255 */     setParameterCount(parameterCount);
/*     */     
/* 257 */     if (Trace.isOn) {
/* 258 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFH", "<init>(int,int)");
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
/* 272 */     if (Trace.isOn) {
/* 273 */       Trace.entry(this, "com.ibm.mq.headers.pcf.MQCFH", "equals(Object)", new Object[] { obj });
/*     */     }
/* 275 */     boolean areEqual = false;
/* 276 */     if (obj != null && obj instanceof MQCFH) {
/* 277 */       MQCFH other = (MQCFH)obj;
/*     */       
/* 279 */       int t1 = getType();
/* 280 */       int t2 = other.getType();
/* 281 */       boolean typesMatch = (t1 == t2);
/* 282 */       int l1 = getStrucLength();
/* 283 */       int l2 = other.getStrucLength();
/* 284 */       boolean lengthsMatch = (l1 == l2);
/*     */       
/* 286 */       areEqual = (typesMatch && lengthsMatch);
/*     */     } 
/*     */     
/* 289 */     if (Trace.isOn) {
/* 290 */       Trace.exit(this, "com.ibm.mq.headers.pcf.MQCFH", "equals(Object)", Boolean.valueOf(areEqual));
/*     */     }
/*     */     
/* 293 */     return areEqual;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 303 */     int traceRet1 = getIntValue(Type);
/* 304 */     if (Trace.isOn) {
/* 305 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFH", "getType()", "getter", 
/* 306 */           Integer.valueOf(traceRet1));
/*     */     }
/* 308 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(int value) {
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFH", "setType(int)", "setter", 
/* 318 */           Integer.valueOf(value));
/*     */     }
/* 320 */     setIntValue(Type, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 329 */     int traceRet1 = getIntValue(StrucLength);
/* 330 */     if (Trace.isOn) {
/* 331 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFH", "getStrucLength()", "getter", 
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
/*     */   public int getVersion() {
/* 343 */     int traceRet1 = getIntValue(Version);
/* 344 */     if (Trace.isOn) {
/* 345 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFH", "getVersion()", "getter", 
/* 346 */           Integer.valueOf(traceRet1));
/*     */     }
/* 348 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int value) {
/* 356 */     if (Trace.isOn) {
/* 357 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFH", "setVersion(int)", "setter", 
/* 358 */           Integer.valueOf(value));
/*     */     }
/* 360 */     setIntValue(Version, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCommand() {
/* 369 */     int traceRet1 = getIntValue(Command);
/* 370 */     if (Trace.isOn) {
/* 371 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFH", "getCommand()", "getter", 
/* 372 */           Integer.valueOf(traceRet1));
/*     */     }
/* 374 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommand(int value) {
/* 383 */     if (Trace.isOn) {
/* 384 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFH", "setCommand(int)", "setter", 
/* 385 */           Integer.valueOf(value));
/*     */     }
/* 387 */     setIntValue(Command, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMsgSeqNumber() {
/* 396 */     int traceRet1 = getIntValue(MsgSeqNumber);
/* 397 */     if (Trace.isOn) {
/* 398 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFH", "getMsgSeqNumber()", "getter", 
/* 399 */           Integer.valueOf(traceRet1));
/*     */     }
/* 401 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMsgSeqNumber(int value) {
/* 410 */     if (Trace.isOn) {
/* 411 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFH", "setMsgSeqNumber(int)", "setter", 
/* 412 */           Integer.valueOf(value));
/*     */     }
/* 414 */     setIntValue(MsgSeqNumber, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getControl() {
/* 423 */     int traceRet1 = getIntValue(Control);
/* 424 */     if (Trace.isOn) {
/* 425 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFH", "getControl()", "getter", 
/* 426 */           Integer.valueOf(traceRet1));
/*     */     }
/* 428 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setControl(int value) {
/* 437 */     if (Trace.isOn) {
/* 438 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFH", "setControl(int)", "setter", 
/* 439 */           Integer.valueOf(value));
/*     */     }
/* 441 */     setIntValue(Control, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCompCode() {
/* 450 */     int traceRet1 = getIntValue(CompCode);
/* 451 */     if (Trace.isOn) {
/* 452 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFH", "getCompCode()", "getter", 
/* 453 */           Integer.valueOf(traceRet1));
/*     */     }
/* 455 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCompCode(int value) {
/* 464 */     if (Trace.isOn) {
/* 465 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFH", "setCompCode(int)", "setter", 
/* 466 */           Integer.valueOf(value));
/*     */     }
/* 468 */     setIntValue(CompCode, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReason() {
/* 477 */     int traceRet1 = getIntValue(Reason);
/* 478 */     if (Trace.isOn) {
/* 479 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFH", "getReason()", "getter", 
/* 480 */           Integer.valueOf(traceRet1));
/*     */     }
/* 482 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReason(int value) {
/* 491 */     if (Trace.isOn) {
/* 492 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFH", "setReason(int)", "setter", 
/* 493 */           Integer.valueOf(value));
/*     */     }
/* 495 */     setIntValue(Reason, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameterCount() {
/* 504 */     int traceRet1 = getIntValue(ParameterCount);
/* 505 */     if (Trace.isOn) {
/* 506 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFH", "getParameterCount()", "getter", 
/* 507 */           Integer.valueOf(traceRet1));
/*     */     }
/* 509 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameterCount(int value) {
/* 518 */     if (Trace.isOn) {
/* 519 */       Trace.data(this, "com.ibm.mq.headers.pcf.MQCFH", "setParameterCount(int)", "setter", 
/* 520 */           Integer.valueOf(value));
/*     */     }
/* 522 */     setIntValue(ParameterCount, value);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\headers\pcf\MQCFH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */