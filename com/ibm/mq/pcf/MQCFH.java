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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class MQCFH
/*     */   extends PCFHeader
/*     */ {
/*     */   public static final int SIZE = 36;
/*     */   
/*     */   static {
/*  58 */     if (Trace.isOn) {
/*  59 */       Trace.data("com.ibm.mq.pcf.MQCFH", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq/src/com/ibm/mq/pcf/MQCFH.java");
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
/*  71 */   static final HeaderType TYPE = new HeaderType("MQCFH");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public int type = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int strucLength = 36;
/*     */ 
/*     */ 
/*     */   
/*  90 */   public int version = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   public int command;
/*     */ 
/*     */ 
/*     */   
/*  98 */   public int msgSeqNumber = 1;
/*     */ 
/*     */ 
/*     */   
/* 102 */   public int control = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compCode;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int reason;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int parameterCount;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final com.ibm.mq.headers.pcf.MQCFH myDelegate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int write(MQMessage message, int command, int parameterCount) throws IOException {
/* 130 */     if (Trace.isOn) {
/* 131 */       Trace.entry("com.ibm.mq.pcf.MQCFH", "write(MQMessage,int,int)", new Object[] { message, 
/* 132 */             Integer.valueOf(command), Integer.valueOf(parameterCount) });
/*     */     }
/*     */     
/* 135 */     int traceRet1 = write(message, command, parameterCount, 1, 1);
/* 136 */     if (Trace.isOn) {
/* 137 */       Trace.exit("com.ibm.mq.pcf.MQCFH", "write(MQMessage,int,int)", Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 140 */     return traceRet1;
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
/*     */   public static int write(MQMessage message, int command, int parameterCount, int type, int version) throws IOException {
/* 158 */     if (Trace.isOn) {
/* 159 */       Trace.entry("com.ibm.mq.pcf.MQCFH", "write(MQMessage,int,int,int,int)", new Object[] { message, 
/* 160 */             Integer.valueOf(command), Integer.valueOf(parameterCount), Integer.valueOf(type), 
/* 161 */             Integer.valueOf(version) });
/*     */     }
/*     */     
/* 164 */     int traceRet1 = com.ibm.mq.headers.pcf.MQCFH.write((DataOutput)message, command, parameterCount, type, version);
/* 165 */     if (Trace.isOn) {
/* 166 */       Trace.exit("com.ibm.mq.pcf.MQCFH", "write(MQMessage,int,int,int,int)", 
/* 167 */           Integer.valueOf(traceRet1));
/*     */     }
/* 169 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQCFH() {
/* 176 */     super(TYPE);
/* 177 */     if (Trace.isOn) {
/* 178 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFH", "<init>()");
/*     */     }
/* 180 */     this.myDelegate = (com.ibm.mq.headers.pcf.MQCFH)this.delegate;
/*     */     
/* 182 */     if (Trace.isOn) {
/* 183 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFH", "<init>()");
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
/*     */   public MQCFH(MQMessage message) throws MQException, IOException {
/* 196 */     this();
/* 197 */     if (Trace.isOn) {
/* 198 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFH", "<init>(MQMessage)", new Object[] { message });
/*     */     }
/* 200 */     initialize(message);
/*     */     
/* 202 */     if (Trace.isOn) {
/* 203 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFH", "<init>(MQMessage)");
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
/*     */   public MQCFH(DataInput message, int encoding, int characterSet) throws MQException, IOException {
/* 218 */     this();
/* 219 */     if (Trace.isOn) {
/* 220 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFH", "<init>(DataInput,int,int)", new Object[] { message, 
/* 221 */             Integer.valueOf(encoding), Integer.valueOf(characterSet) });
/*     */     }
/*     */     try {
/* 224 */       this.delegate.read(message, encoding, characterSet);
/*     */     }
/* 226 */     catch (MQDataException mde) {
/* 227 */       if (Trace.isOn) {
/* 228 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFH", "<init>(DataInput,int,int)", (Throwable)mde, 1);
/*     */       }
/*     */       
/* 231 */       MQException traceRet1 = new MQException(mde.completionCode, mde.reasonCode, mde.exceptionSource);
/*     */       
/* 233 */       if (Trace.isOn) {
/* 234 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFH", "<init>(DataInput,int,int)", (Throwable)traceRet1, 1);
/*     */       }
/*     */       
/* 237 */       throw traceRet1;
/*     */     }
/* 239 */     catch (Exception e) {
/* 240 */       if (Trace.isOn) {
/* 241 */         Trace.catchBlock(this, "com.ibm.mq.pcf.MQCFH", "<init>(DataInput,int,int)", e, 2);
/*     */       }
/* 243 */       RuntimeException traceRet2 = new RuntimeException(e);
/* 244 */       if (Trace.isOn) {
/* 245 */         Trace.throwing(this, "com.ibm.mq.pcf.MQCFH", "<init>(DataInput,int,int)", traceRet2, 2);
/*     */       }
/* 247 */       throw traceRet2;
/*     */     } 
/* 249 */     if (Trace.isOn) {
/* 250 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFH", "<init>(DataInput,int,int)");
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
/* 262 */     this();
/* 263 */     if (Trace.isOn)
/* 264 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFH", "<init>(int,int)", new Object[] {
/* 265 */             Integer.valueOf(command), Integer.valueOf(parameterCount)
/*     */           }); 
/* 267 */     setCommand(this.command = command);
/* 268 */     setParameterCount(this.parameterCount = parameterCount);
/*     */     
/* 270 */     if (Trace.isOn) {
/* 271 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFH", "<init>(int,int)");
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
/* 285 */     if (Trace.isOn) {
/* 286 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFH", "equals(Object)", new Object[] { obj });
/*     */     }
/* 288 */     boolean areEqual = false;
/* 289 */     if (obj != null && obj instanceof MQCFH) {
/* 290 */       MQCFH other = (MQCFH)obj;
/*     */       
/* 292 */       int t1 = getType();
/* 293 */       int t2 = other.getType();
/* 294 */       boolean typesMatch = (t1 == t2);
/* 295 */       int l1 = getStrucLength();
/* 296 */       int l2 = other.getStrucLength();
/* 297 */       boolean lengthsMatch = (l1 == l2);
/*     */       
/* 299 */       areEqual = (typesMatch && lengthsMatch);
/*     */     } 
/*     */     
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFH", "equals(Object)", Boolean.valueOf(areEqual));
/*     */     }
/* 305 */     return areEqual;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 315 */     int traceRet1 = this.myDelegate.getType();
/* 316 */     if (Trace.isOn) {
/* 317 */       Trace.data(this, "com.ibm.mq.pcf.MQCFH", "getType()", "getter", Integer.valueOf(traceRet1));
/*     */     }
/* 319 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(int value) {
/* 328 */     if (Trace.isOn) {
/* 329 */       Trace.data(this, "com.ibm.mq.pcf.MQCFH", "setType(int)", "setter", Integer.valueOf(value));
/*     */     }
/* 331 */     this.myDelegate.setType(this.type = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrucLength() {
/* 340 */     int traceRet1 = this.myDelegate.getStrucLength();
/* 341 */     if (Trace.isOn) {
/* 342 */       Trace.data(this, "com.ibm.mq.pcf.MQCFH", "getStrucLength()", "getter", 
/* 343 */           Integer.valueOf(traceRet1));
/*     */     }
/* 345 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 354 */     int traceRet1 = this.myDelegate.getVersion();
/* 355 */     if (Trace.isOn) {
/* 356 */       Trace.data(this, "com.ibm.mq.pcf.MQCFH", "getVersion()", "getter", 
/* 357 */           Integer.valueOf(traceRet1));
/*     */     }
/* 359 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int value) {
/* 368 */     if (Trace.isOn) {
/* 369 */       Trace.data(this, "com.ibm.mq.pcf.MQCFH", "setVersion(int)", "setter", Integer.valueOf(value));
/*     */     }
/*     */     
/* 372 */     this.myDelegate.setVersion(this.version = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCommand() {
/* 381 */     int traceRet1 = this.myDelegate.getCommand();
/* 382 */     if (Trace.isOn) {
/* 383 */       Trace.data(this, "com.ibm.mq.pcf.MQCFH", "getCommand()", "getter", 
/* 384 */           Integer.valueOf(traceRet1));
/*     */     }
/* 386 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommand(int value) {
/* 395 */     if (Trace.isOn) {
/* 396 */       Trace.data(this, "com.ibm.mq.pcf.MQCFH", "setCommand(int)", "setter", Integer.valueOf(value));
/*     */     }
/*     */     
/* 399 */     this.myDelegate.setCommand(this.command = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMsgSeqNumber() {
/* 408 */     int traceRet1 = this.myDelegate.getMsgSeqNumber();
/* 409 */     if (Trace.isOn) {
/* 410 */       Trace.data(this, "com.ibm.mq.pcf.MQCFH", "getMsgSeqNumber()", "getter", 
/* 411 */           Integer.valueOf(traceRet1));
/*     */     }
/* 413 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMsgSeqNumber(int value) {
/* 422 */     if (Trace.isOn) {
/* 423 */       Trace.data(this, "com.ibm.mq.pcf.MQCFH", "setMsgSeqNumber(int)", "setter", 
/* 424 */           Integer.valueOf(value));
/*     */     }
/* 426 */     this.myDelegate.setMsgSeqNumber(this.msgSeqNumber = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getControl() {
/* 435 */     int traceRet1 = this.myDelegate.getControl();
/* 436 */     if (Trace.isOn) {
/* 437 */       Trace.data(this, "com.ibm.mq.pcf.MQCFH", "getControl()", "getter", 
/* 438 */           Integer.valueOf(traceRet1));
/*     */     }
/* 440 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setControl(int value) {
/* 449 */     if (Trace.isOn) {
/* 450 */       Trace.data(this, "com.ibm.mq.pcf.MQCFH", "setControl(int)", "setter", Integer.valueOf(value));
/*     */     }
/*     */     
/* 453 */     this.myDelegate.setControl(this.control = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCompCode() {
/* 462 */     int traceRet1 = this.myDelegate.getCompCode();
/* 463 */     if (Trace.isOn) {
/* 464 */       Trace.data(this, "com.ibm.mq.pcf.MQCFH", "getCompCode()", "getter", 
/* 465 */           Integer.valueOf(traceRet1));
/*     */     }
/* 467 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCompCode(int value) {
/* 476 */     if (Trace.isOn) {
/* 477 */       Trace.data(this, "com.ibm.mq.pcf.MQCFH", "setCompCode(int)", "setter", 
/* 478 */           Integer.valueOf(value));
/*     */     }
/* 480 */     this.myDelegate.setCompCode(this.compCode = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReason() {
/* 489 */     int traceRet1 = this.myDelegate.getReason();
/* 490 */     if (Trace.isOn) {
/* 491 */       Trace.data(this, "com.ibm.mq.pcf.MQCFH", "getReason()", "getter", Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 494 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReason(int value) {
/* 503 */     if (Trace.isOn) {
/* 504 */       Trace.data(this, "com.ibm.mq.pcf.MQCFH", "setReason(int)", "setter", Integer.valueOf(value));
/*     */     }
/*     */     
/* 507 */     this.myDelegate.setReason(this.reason = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParameterCount() {
/* 516 */     int traceRet1 = this.myDelegate.getParameterCount();
/* 517 */     if (Trace.isOn) {
/* 518 */       Trace.data(this, "com.ibm.mq.pcf.MQCFH", "getParameterCount()", "getter", 
/* 519 */           Integer.valueOf(traceRet1));
/*     */     }
/* 521 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameterCount(int value) {
/* 530 */     if (Trace.isOn) {
/* 531 */       Trace.data(this, "com.ibm.mq.pcf.MQCFH", "setParameterCount(int)", "setter", 
/* 532 */           Integer.valueOf(value));
/*     */     }
/* 534 */     this.myDelegate.setParameterCount(this.parameterCount = value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readCachedContent() throws MQException, IOException {
/* 544 */     if (Trace.isOn) {
/* 545 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFH", "readCachedContent()");
/*     */     }
/* 547 */     this.type = getType();
/* 548 */     this.version = getVersion();
/* 549 */     this.command = getCommand();
/* 550 */     this.msgSeqNumber = getMsgSeqNumber();
/* 551 */     this.control = getControl();
/* 552 */     this.compCode = getCompCode();
/* 553 */     this.reason = getReason();
/* 554 */     this.parameterCount = getParameterCount();
/*     */     
/* 556 */     if (Trace.isOn) {
/* 557 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFH", "readCachedContent()");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void discardCachedContent() {
/* 567 */     if (Trace.isOn) {
/* 568 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFH", "discardCachedContent()");
/*     */     }
/* 570 */     this.type = 1;
/* 571 */     this.version = 1;
/* 572 */     this.command = 0;
/* 573 */     this.msgSeqNumber = 1;
/* 574 */     this.control = 1;
/* 575 */     this.compCode = 0;
/* 576 */     this.reason = 0;
/* 577 */     this.parameterCount = 0;
/*     */     
/* 579 */     if (Trace.isOn) {
/* 580 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFH", "discardCachedContent()");
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
/* 591 */     if (Trace.isOn) {
/* 592 */       Trace.entry(this, "com.ibm.mq.pcf.MQCFH", "writeCachedContent()");
/*     */     }
/* 594 */     setType(this.type);
/* 595 */     setVersion(this.version);
/* 596 */     setCommand(this.command);
/* 597 */     setMsgSeqNumber(this.msgSeqNumber);
/* 598 */     setControl(this.control);
/* 599 */     setCompCode(this.compCode);
/* 600 */     setReason(this.reason);
/* 601 */     setParameterCount(this.parameterCount);
/*     */     
/* 603 */     if (Trace.isOn)
/* 604 */       Trace.exit(this, "com.ibm.mq.pcf.MQCFH", "writeCachedContent()"); 
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\pcf\MQCFH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */