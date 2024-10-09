/*     */ package com.ibm.mq.jmqi.system;
/*     */ 
/*     */ import com.ibm.mq.jmqi.JmqiEnvironment;
/*     */ import com.ibm.mq.jmqi.JmqiException;
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LpiCOMMINFO_ATTR_DATA
/*     */   extends AbstractMqiStructure
/*     */ {
/*     */   public static final String sccsid2 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/system/LpiCOMMINFO_ATTR_DATA.java";
/*     */   private static final int SIZEOF_CommInfoName = 48;
/*     */   private static final int SIZEOF_CommInfoDesc = 64;
/*     */   private static final int SIZEOF_GroupAddress = 264;
/*     */   private static final int SIZEOF_CommInfoType = 4;
/*     */   private static final int SIZEOF_Port = 4;
/*     */   private static final int SIZEOF_MsgHistory = 4;
/*     */   private static final int SIZEOF_CodedCharSetId = 4;
/*     */   private static final int SIZEOF_Encoding = 4;
/*     */   private static final int SIZEOF_NewSubHistory = 4;
/*     */   private static final int SIZEOF_MCHBInterval = 4;
/*     */   private static final int SIZEOF_MCProperties = 4;
/*     */   private static final int SIZEOF_CommEvent = 4;
/*     */   private static final int SIZEOF_MCBridge = 4;
/*     */   private static final int SIZEOF_MonitorInterval = 4;
/*     */   private static final int SIZEOF_Padding = 4;
/*     */   private String CommInfoName;
/*     */   private String CommInfoDesc;
/*     */   private String GroupAddress;
/*     */   private int CommInfoType;
/*     */   private int Port;
/*     */   private int MsgHistory;
/*     */   private int CodedCharSetId;
/*     */   private int Encoding;
/*     */   private int NewSubHistory;
/*     */   private int MCHBInterval;
/*     */   private int MCProperties;
/*     */   private int CommEvent;
/*     */   private int MCBridge;
/*     */   private int MonitorInterval;
/*     */   private int Padding;
/*     */   
/*     */   public LpiCOMMINFO_ATTR_DATA(JmqiEnvironment env) {
/* 111 */     super(env);
/* 112 */     if (Trace.isOn) {
/* 113 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/*     */     
/* 116 */     if (Trace.isOn) {
/* 117 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "<init>(JmqiEnvironment)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getStructureSize(int ptrSize) {
/* 127 */     if (Trace.isOn)
/* 128 */       Trace.entry("com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getStructureSize(int)", new Object[] {
/* 129 */             Integer.valueOf(ptrSize)
/*     */           }); 
/* 131 */     int size = 0;
/* 132 */     size += 48;
/* 133 */     size += 64;
/* 134 */     size += 264;
/* 135 */     size += 4;
/* 136 */     size += 4;
/* 137 */     size += 4;
/* 138 */     size += 4;
/* 139 */     size += 4;
/* 140 */     size += 4;
/* 141 */     size += 4;
/* 142 */     size += 4;
/* 143 */     size += 4;
/* 144 */     size += 4;
/* 145 */     size += 4;
/* 146 */     size += 4;
/*     */ 
/*     */     
/* 149 */     if (Trace.isOn) {
/* 150 */       Trace.exit("com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getStructureSize(int)", 
/* 151 */           Integer.valueOf(size));
/*     */     }
/* 153 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 164 */     if (Trace.isOn)
/* 165 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getRequiredBufferSize(int,JmqiCodepage)", new Object[] {
/* 166 */             Integer.valueOf(ptrSize), cp
/*     */           }); 
/* 168 */     int size = getSize(ptrSize);
/*     */     
/* 170 */     if (Trace.isOn) {
/* 171 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getRequiredBufferSize(int,JmqiCodepage)", 
/* 172 */           Integer.valueOf(size));
/*     */     }
/* 174 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCommInfoName() {
/* 181 */     if (Trace.isOn) {
/* 182 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getCommInfoName()", "getter", this.CommInfoName);
/*     */     }
/*     */     
/* 185 */     return this.CommInfoName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommInfoName(String string) {
/* 192 */     if (Trace.isOn) {
/* 193 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "setCommInfoName(String)", "setter", string);
/*     */     }
/*     */     
/* 196 */     this.CommInfoName = string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCommInfoDesc() {
/* 203 */     if (Trace.isOn) {
/* 204 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getCommInfoDesc()", "getter", this.CommInfoDesc);
/*     */     }
/*     */     
/* 207 */     return this.CommInfoDesc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommInfoDesc(String string) {
/* 214 */     if (Trace.isOn) {
/* 215 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "setCommInfoDesc(String)", "setter", string);
/*     */     }
/*     */     
/* 218 */     this.CommInfoDesc = string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getGroupAddress() {
/* 225 */     if (Trace.isOn) {
/* 226 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getGroupAddress()", "getter", this.GroupAddress);
/*     */     }
/*     */     
/* 229 */     return this.GroupAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGroupAddress(String string) {
/* 236 */     if (Trace.isOn) {
/* 237 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "setGroupAddress(String)", "setter", string);
/*     */     }
/*     */     
/* 240 */     this.GroupAddress = string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCommInfoType() {
/* 247 */     if (Trace.isOn) {
/* 248 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getCommInfoType()", "getter", 
/* 249 */           Integer.valueOf(this.CommInfoType));
/*     */     }
/* 251 */     return this.CommInfoType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int value) {
/* 258 */     if (Trace.isOn) {
/* 259 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "setOptions(int)", "setter", 
/* 260 */           Integer.valueOf(value));
/*     */     }
/* 262 */     this.CommInfoType = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPort() {
/* 269 */     if (Trace.isOn) {
/* 270 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getPort()", "getter", 
/* 271 */           Integer.valueOf(this.Port));
/*     */     }
/* 273 */     return this.Port;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPort(int value) {
/* 280 */     if (Trace.isOn) {
/* 281 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "setPort(int)", "setter", 
/* 282 */           Integer.valueOf(value));
/*     */     }
/* 284 */     this.Port = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMsgHistory() {
/* 291 */     if (Trace.isOn) {
/* 292 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getMsgHistory()", "getter", 
/* 293 */           Integer.valueOf(this.MsgHistory));
/*     */     }
/* 295 */     return this.MsgHistory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMsgHistory(int value) {
/* 302 */     if (Trace.isOn) {
/* 303 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "setMsgHistory(int)", "setter", 
/* 304 */           Integer.valueOf(value));
/*     */     }
/* 306 */     this.MsgHistory = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCodedCharSetId() {
/* 313 */     if (Trace.isOn) {
/* 314 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getCodedCharSetId()", "getter", 
/* 315 */           Integer.valueOf(this.CodedCharSetId));
/*     */     }
/* 317 */     return this.CodedCharSetId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCodedCharSetId(int value) {
/* 324 */     if (Trace.isOn) {
/* 325 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "setCodedCharSetId(int)", "setter", 
/* 326 */           Integer.valueOf(value));
/*     */     }
/* 328 */     this.CodedCharSetId = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEncoding() {
/* 335 */     if (Trace.isOn) {
/* 336 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getEncoding()", "getter", 
/* 337 */           Integer.valueOf(this.Encoding));
/*     */     }
/* 339 */     return this.Encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncoding(int value) {
/* 346 */     if (Trace.isOn) {
/* 347 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "setEncoding(int)", "setter", 
/* 348 */           Integer.valueOf(value));
/*     */     }
/* 350 */     this.Encoding = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNewSubHistory() {
/* 357 */     if (Trace.isOn) {
/* 358 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getNewSubHistory()", "getter", 
/* 359 */           Integer.valueOf(this.NewSubHistory));
/*     */     }
/* 361 */     return this.NewSubHistory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNewSubHistory(int value) {
/* 368 */     if (Trace.isOn) {
/* 369 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "setNewSubHistory(int)", "setter", 
/* 370 */           Integer.valueOf(value));
/*     */     }
/* 372 */     this.NewSubHistory = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMCHBInterval() {
/* 379 */     if (Trace.isOn) {
/* 380 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getMCHBInterval()", "getter", 
/* 381 */           Integer.valueOf(this.MCHBInterval));
/*     */     }
/* 383 */     return this.MCHBInterval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMCHBInterval(int value) {
/* 390 */     if (Trace.isOn) {
/* 391 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "setMCHBInterval(int)", "setter", 
/* 392 */           Integer.valueOf(value));
/*     */     }
/* 394 */     this.MCHBInterval = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMCProperties() {
/* 401 */     if (Trace.isOn) {
/* 402 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getMCProperties()", "getter", 
/* 403 */           Integer.valueOf(this.MCProperties));
/*     */     }
/* 405 */     return this.MCProperties;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMCProperties(int value) {
/* 412 */     if (Trace.isOn) {
/* 413 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "setMCProperties(int)", "setter", 
/* 414 */           Integer.valueOf(value));
/*     */     }
/* 416 */     this.MCProperties = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCommEvent() {
/* 423 */     if (Trace.isOn) {
/* 424 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getCommEvent()", "getter", 
/* 425 */           Integer.valueOf(this.CommEvent));
/*     */     }
/* 427 */     return this.CommEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommEvent(int value) {
/* 434 */     if (Trace.isOn) {
/* 435 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "setCommEvent(int)", "setter", 
/* 436 */           Integer.valueOf(value));
/*     */     }
/* 438 */     this.CommEvent = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMCBridge() {
/* 445 */     if (Trace.isOn) {
/* 446 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getMCBridge()", "getter", 
/* 447 */           Integer.valueOf(this.MCBridge));
/*     */     }
/* 449 */     return this.MCBridge;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMCBridge(int value) {
/* 456 */     if (Trace.isOn) {
/* 457 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "setMCBridge(int)", "setter", 
/* 458 */           Integer.valueOf(value));
/*     */     }
/* 460 */     this.MCBridge = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMonitorInterval() {
/* 467 */     if (Trace.isOn) {
/* 468 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getMonitorInterval()", "getter", 
/* 469 */           Integer.valueOf(this.MonitorInterval));
/*     */     }
/* 471 */     return this.MonitorInterval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMonitorInterval(int value) {
/* 478 */     if (Trace.isOn) {
/* 479 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "setMonitorInterval(int)", "setter", 
/* 480 */           Integer.valueOf(value));
/*     */     }
/* 482 */     this.MonitorInterval = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPadding() {
/* 489 */     if (Trace.isOn) {
/* 490 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "getPadding()", "getter", 
/* 491 */           Integer.valueOf(this.Padding));
/*     */     }
/* 493 */     return this.Padding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPadding(int value) {
/* 500 */     if (Trace.isOn) {
/* 501 */       Trace.data(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "setPadding(int)", "setter", 
/* 502 */           Integer.valueOf(value));
/*     */     }
/* 504 */     this.Padding = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 512 */     if (Trace.isOn) {
/* 513 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 515 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 517 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 518 */     int pos = offset;
/*     */     
/* 520 */     dc.writeMQField(this.CommInfoName, buffer, pos, 48, cp, tls);
/* 521 */     pos += 48;
/*     */     
/* 523 */     dc.writeMQField(this.CommInfoDesc, buffer, pos, 64, cp, tls);
/* 524 */     pos += 64;
/*     */     
/* 526 */     dc.writeMQField(this.GroupAddress, buffer, pos, 264, cp, tls);
/* 527 */     pos += 264;
/*     */     
/* 529 */     dc.writeI32(this.CommInfoType, buffer, pos, swap);
/* 530 */     pos += 4;
/*     */     
/* 532 */     dc.writeI32(this.Port, buffer, pos, swap);
/* 533 */     pos += 4;
/*     */     
/* 535 */     dc.writeI32(this.MsgHistory, buffer, pos, swap);
/* 536 */     pos += 4;
/*     */     
/* 538 */     dc.writeI32(this.CodedCharSetId, buffer, pos, swap);
/* 539 */     pos += 4;
/*     */     
/* 541 */     dc.writeI32(this.Encoding, buffer, pos, swap);
/* 542 */     pos += 4;
/*     */     
/* 544 */     dc.writeI32(this.NewSubHistory, buffer, pos, swap);
/* 545 */     pos += 4;
/*     */     
/* 547 */     dc.writeI32(this.MCHBInterval, buffer, pos, swap);
/* 548 */     pos += 4;
/*     */     
/* 550 */     dc.writeI32(this.MCProperties, buffer, pos, swap);
/* 551 */     pos += 4;
/*     */     
/* 553 */     dc.writeI32(this.CommEvent, buffer, pos, swap);
/* 554 */     pos += 4;
/*     */     
/* 556 */     dc.writeI32(this.MCBridge, buffer, pos, swap);
/* 557 */     pos += 4;
/*     */     
/* 559 */     dc.writeI32(this.MonitorInterval, buffer, pos, swap);
/* 560 */     pos += 4;
/*     */     
/* 562 */     dc.writeI32(this.Padding, buffer, pos, swap);
/* 563 */     pos += 4;
/*     */ 
/*     */     
/* 566 */     int traceRet1 = pos - offset;
/*     */     
/* 568 */     if (Trace.isOn) {
/* 569 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 570 */           Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 573 */     return traceRet1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 581 */     if (Trace.isOn) {
/* 582 */       Trace.entry(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 584 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 586 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/* 587 */     int pos = offset;
/*     */     
/* 589 */     this.CommInfoName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 590 */     pos += 48;
/*     */     
/* 592 */     this.CommInfoDesc = dc.readMQField(buffer, pos, 64, cp, tls);
/* 593 */     pos += 64;
/*     */     
/* 595 */     this.GroupAddress = dc.readMQField(buffer, pos, 264, cp, tls);
/* 596 */     pos += 264;
/*     */     
/* 598 */     this.CommInfoType = dc.readI32(buffer, pos, swap);
/* 599 */     pos += 4;
/*     */     
/* 601 */     this.Port = dc.readI32(buffer, pos, swap);
/* 602 */     pos += 4;
/*     */     
/* 604 */     this.MsgHistory = dc.readI32(buffer, pos, swap);
/* 605 */     pos += 4;
/*     */     
/* 607 */     this.CodedCharSetId = dc.readI32(buffer, pos, swap);
/* 608 */     pos += 4;
/*     */     
/* 610 */     this.Encoding = dc.readI32(buffer, pos, swap);
/* 611 */     pos += 4;
/*     */     
/* 613 */     this.NewSubHistory = dc.readI32(buffer, pos, swap);
/* 614 */     pos += 4;
/*     */     
/* 616 */     this.MCHBInterval = dc.readI32(buffer, pos, swap);
/* 617 */     pos += 4;
/*     */     
/* 619 */     this.MCProperties = dc.readI32(buffer, pos, swap);
/* 620 */     pos += 4;
/*     */     
/* 622 */     this.CommEvent = dc.readI32(buffer, pos, swap);
/* 623 */     pos += 4;
/*     */     
/* 625 */     this.MCBridge = dc.readI32(buffer, pos, swap);
/* 626 */     pos += 4;
/*     */     
/* 628 */     this.MCProperties = dc.readI32(buffer, pos, swap);
/* 629 */     pos += 4;
/*     */     
/* 631 */     this.Padding = dc.readI32(buffer, pos, swap);
/* 632 */     pos += 4;
/*     */ 
/*     */     
/* 635 */     int traceRet1 = pos - offset;
/*     */     
/* 637 */     if (Trace.isOn) {
/* 638 */       Trace.exit(this, "com.ibm.mq.jmqi.system.LpiCOMMINFO_ATTR_DATA", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 639 */           Integer.valueOf(traceRet1));
/*     */     }
/*     */     
/* 642 */     return traceRet1;
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
/*     */   public void addFieldsToFormatter(JmqiStructureFormatter fmt) {
/* 654 */     fmt.add("CommInfoName", this.CommInfoName);
/* 655 */     fmt.add("CommInfoDesc", this.CommInfoDesc);
/* 656 */     fmt.add("GroupAddress", this.GroupAddress);
/* 657 */     fmt.add("CommInfoType", this.CommInfoType);
/* 658 */     fmt.add("Port", this.Port);
/* 659 */     fmt.add("MsgHistory", this.MsgHistory);
/* 660 */     fmt.add("CodedCharSetId", this.CodedCharSetId);
/* 661 */     fmt.add("Encoding", this.Encoding);
/* 662 */     fmt.add("NewSubHistory", this.NewSubHistory);
/* 663 */     fmt.add("MCHBInterval", this.MCHBInterval);
/* 664 */     fmt.add("MCProperties", this.MCProperties);
/* 665 */     fmt.add("CommEvent", this.CommEvent);
/* 666 */     fmt.add("MCBridge", this.MCBridge);
/* 667 */     fmt.add("MonitorInterval", this.MonitorInterval);
/* 668 */     fmt.add("Padding", this.Padding);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\system\LpiCOMMINFO_ATTR_DATA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */