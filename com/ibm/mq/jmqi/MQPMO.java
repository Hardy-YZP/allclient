/*     */ package com.ibm.mq.jmqi;
/*     */ 
/*     */ import com.ibm.mq.constants.MQConstants;
/*     */ import com.ibm.mq.jmqi.internal.AbstractMqiStructure;
/*     */ import com.ibm.mq.jmqi.internal.JmqiDC;
/*     */ import com.ibm.mq.jmqi.internal.JmqiStructureFormatter;
/*     */ import com.ibm.mq.jmqi.internal.JmqiTools;
/*     */ import com.ibm.mq.jmqi.system.JmqiCodepage;
/*     */ import com.ibm.mq.jmqi.system.JmqiSystemEnvironment;
/*     */ import com.ibm.mq.jmqi.system.JmqiTls;
/*     */ import com.ibm.msg.client.commonservices.trace.Trace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MQPMO
/*     */   extends AbstractMqiStructure
/*     */   implements Cloneable
/*     */ {
/*     */   public static final String sccsid1 = "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQPMO.java";
/*     */   private static final int SIZEOF_STRUCID = 4;
/*     */   private static final int SIZEOF_VERSION = 4;
/*     */   private static final int SIZEOF_OPTIONS = 4;
/*     */   private static final int SIZEOF_TIMEOUT = 4;
/*     */   private static final int SIZEOF_CONTEXT = 4;
/*     */   private static final int SIZEOF_KNOWN_DEST_COUNT = 4;
/*     */   private static final int SIZEOF_UNKNOWN_DEST_COUNT = 4;
/*     */   private static final int SIZEOF_INVALID_DEST_COUNT = 4;
/*     */   private static final int SIZEOF_RESOLVED_Q_NAME = 48;
/*     */   private static final int SIZEOF_RESOLVED_Q_MGR_NAME = 48;
/*     */   private static final int SIZEOF_RECS_PRESENT = 4;
/*     */   private static final int SIZEOF_PUT_MSG_REC_FIELDS = 4;
/*     */   private static final int SIZEOF_PUT_MSG_REC_OFFSET = 4;
/*     */   private static final int SIZEOF_RESPONSE_REC_OFFSET = 4;
/*     */   private static final int SIZEOF_ORIGINALMSGHANDLE = 8;
/*     */   private static final int SIZEOF_NEWMSGHANDLE = 8;
/*     */   private static final int SIZEOF_ACTION = 4;
/*     */   private static final int SIZEOF_SUBLEVEL = 4;
/*     */   
/*     */   static {
/*  43 */     if (Trace.isOn) {
/*  44 */       Trace.data("com.ibm.mq.jmqi.MQPMO", "static", "SCCS id", "@(#) MQMBID sn=p932-L230207 su=_mMBuZqcAEe2pWoFAaNK_Tg pn=com.ibm.mq.jmqi/src/com/ibm/mq/jmqi/MQPMO.java");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   private int version = 1;
/* 116 */   private int options = 0;
/* 117 */   private int context = 0;
/* 118 */   private int knownDestCount = 0;
/* 119 */   private int unknownDestCount = 0;
/* 120 */   private int invalidDestCount = 0;
/* 121 */   private String resolvedQName = null;
/* 122 */   private String resolvedQMgrName = null;
/* 123 */   private int recsPresent = 0;
/* 124 */   private int putMsgRecFields = 0;
/* 125 */   private MQPMR[] putMsgRecords = null;
/* 126 */   private MQRR[] responseRecords = null;
/* 127 */   private long originalMsgHandle = 0L;
/* 128 */   private long newMsgHandle = 0L;
/* 129 */   private int action = 0;
/* 130 */   private int subLevel = 9;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MQPMO(JmqiEnvironment env) {
/* 138 */     super(env);
/* 139 */     if (Trace.isOn) {
/* 140 */       Trace.entry(this, "com.ibm.mq.jmqi.MQPMO", "<init>(JmqiEnvironment)", new Object[] { env });
/*     */     }
/* 142 */     if (Trace.isOn) {
/* 143 */       Trace.exit(this, "com.ibm.mq.jmqi.MQPMO", "<init>(JmqiEnvironment)");
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
/*     */   public static int getSizeV1(int ptrSize) {
/* 157 */     int size = 128;
/*     */     
/* 159 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSizeV2(int ptrSize) {
/* 170 */     int size = getSizeV1(ptrSize) + 4 + 4 + 4 + 4 + ptrSize + ptrSize;
/* 171 */     return size;
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
/*     */   public static int getSizeV3(int ptrSize) {
/* 183 */     int size = getSizeV2(ptrSize) + 8 + 8 + 4 + 4;
/*     */ 
/*     */     
/* 186 */     int padding = JmqiTools.alignToGrain(ptrSize, size);
/* 187 */     size += padding;
/*     */     
/* 189 */     return size;
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
/*     */   public static int getSize(JmqiEnvironment env, int version, int ptrSize) throws JmqiException {
/*     */     int size;
/* 204 */     switch (version) {
/*     */       case 1:
/* 206 */         size = getSizeV1(ptrSize);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 219 */         return size;case 2: size = getSizeV2(ptrSize); return size;case 3: size = getSizeV3(ptrSize); return size;
/*     */     } 
/*     */     JmqiException e = new JmqiException(env, -1, null, 2, 2173, null);
/*     */     throw e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 229 */     if (Trace.isOn) {
/* 230 */       Trace.entry(this, "com.ibm.mq.jmqi.MQPMO", "clone()");
/*     */     }
/* 232 */     MQPMO traceRet = new MQPMO(this.env);
/* 233 */     traceRet.populateFields(this);
/*     */     
/* 235 */     if (Trace.isOn) {
/* 236 */       Trace.exit(this, "com.ibm.mq.jmqi.MQPMO", "clone()", traceRet);
/*     */     }
/* 238 */     return traceRet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void populateFields(MQPMO source) {
/* 245 */     if (Trace.isOn) {
/* 246 */       Trace.entry(this, "com.ibm.mq.jmqi.MQPMO", "populateFields(MQPMO)", new Object[] { source });
/*     */     }
/* 248 */     this.action = source.action;
/* 249 */     this.context = source.context;
/* 250 */     this.invalidDestCount = source.invalidDestCount;
/* 251 */     this.knownDestCount = source.knownDestCount;
/* 252 */     this.newMsgHandle = source.newMsgHandle;
/* 253 */     this.options = source.options;
/* 254 */     this.originalMsgHandle = source.originalMsgHandle;
/* 255 */     this.putMsgRecFields = source.putMsgRecFields;
/* 256 */     this.putMsgRecords = source.putMsgRecords;
/* 257 */     this.recsPresent = source.recsPresent;
/* 258 */     this.resolvedQMgrName = source.resolvedQMgrName;
/* 259 */     this.resolvedQName = source.resolvedQName;
/* 260 */     this.responseRecords = source.responseRecords;
/* 261 */     this.subLevel = source.subLevel;
/* 262 */     this.unknownDestCount = source.unknownDestCount;
/* 263 */     this.version = source.version;
/*     */     
/* 265 */     if (Trace.isOn) {
/* 266 */       Trace.exit(this, "com.ibm.mq.jmqi.MQPMO", "populateFields(MQPMO)");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getContext() {
/* 275 */     if (Trace.isOn) {
/* 276 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "getContext()", "getter", Integer.valueOf(this.context));
/*     */     }
/* 278 */     return this.context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContext(int context) {
/* 286 */     if (Trace.isOn) {
/* 287 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "setContext(int)", "setter", 
/* 288 */           Integer.valueOf(context));
/*     */     }
/* 290 */     this.context = context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInvalidDestCount() {
/* 298 */     if (Trace.isOn) {
/* 299 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "getInvalidDestCount()", "getter", 
/* 300 */           Integer.valueOf(this.invalidDestCount));
/*     */     }
/* 302 */     return this.invalidDestCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInvalidDestCount(int invalidDestCount) {
/* 310 */     if (Trace.isOn) {
/* 311 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "setInvalidDestCount(int)", "setter", 
/* 312 */           Integer.valueOf(invalidDestCount));
/*     */     }
/* 314 */     this.invalidDestCount = invalidDestCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getKnownDestCount() {
/* 322 */     if (Trace.isOn) {
/* 323 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "getKnownDestCount()", "getter", 
/* 324 */           Integer.valueOf(this.knownDestCount));
/*     */     }
/* 326 */     return this.knownDestCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKnownDestCount(int knownDestCount) {
/* 334 */     if (Trace.isOn) {
/* 335 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "setKnownDestCount(int)", "setter", 
/* 336 */           Integer.valueOf(knownDestCount));
/*     */     }
/* 338 */     this.knownDestCount = knownDestCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOptions() {
/* 346 */     if (Trace.isOn) {
/* 347 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "getOptions()", "getter", Integer.valueOf(this.options));
/*     */     }
/* 349 */     return this.options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptions(int options) {
/* 357 */     if (Trace.isOn) {
/* 358 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "setOptions(int)", "setter", 
/* 359 */           Integer.valueOf(options));
/*     */     }
/* 361 */     this.options = options;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPutMsgRecFields() {
/* 369 */     if (Trace.isOn) {
/* 370 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "getPutMsgRecFields()", "getter", 
/* 371 */           Integer.valueOf(this.putMsgRecFields));
/*     */     }
/* 373 */     return this.putMsgRecFields;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPutMsgRecFields(int putMsgRecsFields) {
/* 381 */     if (Trace.isOn) {
/* 382 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "setPutMsgRecFields(int)", "setter", 
/* 383 */           Integer.valueOf(putMsgRecsFields));
/*     */     }
/* 385 */     this.putMsgRecFields = putMsgRecsFields;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecsPresent() {
/* 393 */     if (Trace.isOn) {
/* 394 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "getRecsPresent()", "getter", 
/* 395 */           Integer.valueOf(this.recsPresent));
/*     */     }
/* 397 */     return this.recsPresent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRecsPresent(int recsPresent) {
/* 405 */     if (Trace.isOn) {
/* 406 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "setRecsPresent(int)", "setter", 
/* 407 */           Integer.valueOf(recsPresent));
/*     */     }
/* 409 */     this.recsPresent = recsPresent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getResolvedQMgrName() {
/* 417 */     if (Trace.isOn) {
/* 418 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "getResolvedQMgrName()", "getter", this.resolvedQMgrName);
/*     */     }
/*     */     
/* 421 */     return this.resolvedQMgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResolvedQMgrName(String resolvedQMgrName) {
/* 429 */     if (Trace.isOn) {
/* 430 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "setResolvedQMgrName(String)", "setter", resolvedQMgrName);
/*     */     }
/*     */     
/* 433 */     this.resolvedQMgrName = resolvedQMgrName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getResolvedQName() {
/* 441 */     if (Trace.isOn) {
/* 442 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "getResolvedQName()", "getter", this.resolvedQName);
/*     */     }
/* 444 */     return this.resolvedQName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResolvedQName(String resolvedQName) {
/* 452 */     if (Trace.isOn) {
/* 453 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "setResolvedQName(String)", "setter", resolvedQName);
/*     */     }
/*     */     
/* 456 */     this.resolvedQName = resolvedQName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUnknownDestCount() {
/* 464 */     if (Trace.isOn) {
/* 465 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "getUnknownDestCount()", "getter", 
/* 466 */           Integer.valueOf(this.unknownDestCount));
/*     */     }
/* 468 */     return this.unknownDestCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnknownDestCount(int unknownDestCount) {
/* 476 */     if (Trace.isOn) {
/* 477 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "setUnknownDestCount(int)", "setter", 
/* 478 */           Integer.valueOf(unknownDestCount));
/*     */     }
/* 480 */     this.unknownDestCount = unknownDestCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 489 */     if (Trace.isOn) {
/* 490 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "getVersion()", "getter", Integer.valueOf(this.version));
/*     */     }
/* 492 */     return this.version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(int version) {
/* 501 */     if (Trace.isOn) {
/* 502 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "setVersion(int)", "setter", 
/* 503 */           Integer.valueOf(version));
/*     */     }
/* 505 */     this.version = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQPMR[] getPutMsgRecords() {
/* 513 */     if (Trace.isOn) {
/* 514 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "getPutMsgRecords()", "getter", this.putMsgRecords);
/*     */     }
/* 516 */     return this.putMsgRecords;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPutMsgRecords(MQPMR[] putMsgRecords) {
/* 524 */     if (Trace.isOn) {
/* 525 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "setPutMsgRecords(MQPMR [ ])", "setter", putMsgRecords);
/*     */     }
/*     */     
/* 528 */     this.putMsgRecords = putMsgRecords;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MQRR[] getResponseRecords() {
/* 536 */     if (Trace.isOn) {
/* 537 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "getResponseRecords()", "getter", this.responseRecords);
/*     */     }
/* 539 */     return this.responseRecords;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResponseRecords(MQRR[] responseRecords) {
/* 547 */     if (Trace.isOn) {
/* 548 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "setResponseRecords(MQRR [ ])", "setter", responseRecords);
/*     */     }
/*     */     
/* 551 */     this.responseRecords = responseRecords;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getOriginalMsgHandle() {
/* 559 */     if (Trace.isOn) {
/* 560 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "getOriginalMsgHandle()", "getter", 
/* 561 */           Long.valueOf(this.originalMsgHandle));
/*     */     }
/* 563 */     return this.originalMsgHandle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOriginalMsgHandle(long originalMsgHandle) {
/* 570 */     if (Trace.isOn) {
/* 571 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "setOriginalMsgHandle(long)", "setter", 
/* 572 */           Long.valueOf(originalMsgHandle));
/*     */     }
/* 574 */     this.originalMsgHandle = originalMsgHandle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getNewMsgHandle() {
/* 581 */     if (Trace.isOn) {
/* 582 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "getNewMsgHandle()", "getter", 
/* 583 */           Long.valueOf(this.newMsgHandle));
/*     */     }
/* 585 */     return this.newMsgHandle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNewMsgHandle(long newMsgHandle) {
/* 592 */     if (Trace.isOn) {
/* 593 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "setNewMsgHandle(long)", "setter", 
/* 594 */           Long.valueOf(newMsgHandle));
/*     */     }
/* 596 */     this.newMsgHandle = newMsgHandle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAction() {
/* 603 */     if (Trace.isOn) {
/* 604 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "getAction()", "getter", Integer.valueOf(this.action));
/*     */     }
/* 606 */     return this.action;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAction(int action) {
/* 613 */     if (Trace.isOn) {
/* 614 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "setAction(int)", "setter", 
/* 615 */           Integer.valueOf(action));
/*     */     }
/* 617 */     this.action = action;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSubLevel() {
/* 624 */     if (Trace.isOn) {
/* 625 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "getSubLevel()", "getter", 
/* 626 */           Integer.valueOf(this.subLevel));
/*     */     }
/* 628 */     return this.subLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubLevel(int subLevel) {
/* 635 */     if (Trace.isOn) {
/* 636 */       Trace.data(this, "com.ibm.mq.jmqi.MQPMO", "setSubLevel(int)", "setter", 
/* 637 */           Integer.valueOf(subLevel));
/*     */     }
/* 639 */     this.subLevel = subLevel;
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
/*     */   public int getRequiredBufferSize(int ptrSize, JmqiCodepage cp) throws JmqiException {
/* 651 */     int size = getSize(this.env, this.version, ptrSize);
/*     */ 
/*     */     
/* 654 */     if (this.version >= 2 && this.recsPresent > 0) {
/*     */ 
/*     */       
/* 657 */       if (this.putMsgRecFields != 0)
/*     */       {
/*     */         
/* 660 */         size += this.recsPresent * MQPMR.calcRequiredBufferSize(this.putMsgRecFields);
/*     */       }
/*     */ 
/*     */       
/* 664 */       if (this.responseRecords != null) {
/* 665 */         size += this.recsPresent * MQRR.getSize(this.env, ptrSize);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 670 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int writeToBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 678 */     if (Trace.isOn) {
/* 679 */       Trace.entry(this, "com.ibm.mq.jmqi.MQPMO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 681 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 683 */     int pos = offset;
/* 684 */     int beginPos = offset;
/* 685 */     int putMsgRecOffsetPosition = -1;
/* 686 */     int responseRecOffsetPosition = -1;
/* 687 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 689 */     dc.writeMQField("PMO ", buffer, pos, 4, cp, tls);
/* 690 */     pos += 4;
/*     */     
/* 692 */     dc.writeI32(this.version, buffer, pos, swap);
/* 693 */     pos += 4;
/*     */     
/* 695 */     dc.writeI32(this.options, buffer, pos, swap);
/* 696 */     pos += 4;
/*     */     
/* 698 */     dc.clear(buffer, pos, 4);
/* 699 */     pos += 4;
/*     */     
/* 701 */     dc.writeI32(this.context, buffer, pos, swap);
/* 702 */     pos += 4;
/*     */ 
/*     */     
/* 705 */     dc.clear(buffer, pos, 108);
/* 706 */     pos += 108;
/*     */     
/* 708 */     if (this.version >= 2) {
/*     */       
/* 710 */       dc.writeI32(this.recsPresent, buffer, pos, swap);
/* 711 */       pos += 4;
/*     */       
/* 713 */       dc.writeI32(this.putMsgRecFields, buffer, pos, swap);
/* 714 */       pos += 4;
/*     */ 
/*     */       
/* 717 */       dc.clear(buffer, pos, 8 + ptrSize + ptrSize);
/*     */ 
/*     */       
/* 720 */       putMsgRecOffsetPosition = pos;
/* 721 */       pos += 4;
/* 722 */       responseRecOffsetPosition = pos;
/* 723 */       pos += 4 + ptrSize + ptrSize;
/*     */     } 
/*     */     
/* 726 */     if (this.version >= 3) {
/*     */       
/* 728 */       dc.writeI64(this.originalMsgHandle, buffer, pos, swap);
/* 729 */       pos += 8;
/*     */       
/* 731 */       dc.writeI64(this.newMsgHandle, buffer, pos, swap);
/* 732 */       pos += 8;
/*     */       
/* 734 */       dc.writeI32(this.action, buffer, pos, swap);
/* 735 */       pos += 4;
/*     */       
/* 737 */       dc.writeI32(this.subLevel, buffer, pos, swap);
/* 738 */       pos += 4;
/*     */       
/* 740 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 741 */       dc.clear(buffer, pos, padding);
/* 742 */       pos += padding;
/*     */     } 
/*     */     
/* 745 */     if (putMsgRecOffsetPosition > 0 && this.recsPresent > 0) {
/*     */       
/* 747 */       if (this.putMsgRecFields != 0) {
/* 748 */         if (this.putMsgRecords == null || this.putMsgRecords.length == 0) {
/*     */           
/* 750 */           JmqiException e = new JmqiException(this.env, -1, null, 2, 2158, null);
/*     */           
/* 752 */           if (Trace.isOn) {
/* 753 */             Trace.throwing(this, "com.ibm.mq.jmqi.MQPMO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", e, 1);
/*     */           }
/*     */           
/* 756 */           throw e;
/*     */         } 
/* 758 */         if (this.putMsgRecords.length < this.recsPresent) {
/*     */           
/* 760 */           JmqiException e = new JmqiException(this.env, -1, null, 2, 2159, null);
/* 761 */           if (Trace.isOn) {
/* 762 */             Trace.throwing(this, "com.ibm.mq.jmqi.MQPMO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", e, 2);
/*     */           }
/*     */           
/* 765 */           throw e;
/*     */         } 
/*     */         
/* 768 */         dc.writeI32(pos - beginPos, buffer, putMsgRecOffsetPosition, swap);
/*     */         
/* 770 */         for (int i = 0; i < this.recsPresent; i++) {
/* 771 */           this.putMsgRecords[i].setOwningPmo(this);
/* 772 */           pos = this.putMsgRecords[i].writeToBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*     */         } 
/*     */       } 
/*     */       
/* 776 */       if (this.responseRecords != null) {
/* 777 */         if (this.responseRecords.length < this.recsPresent) {
/*     */           
/* 779 */           JmqiException e = new JmqiException(this.env, -1, null, 2, 2156, null);
/*     */           
/* 781 */           if (Trace.isOn) {
/* 782 */             Trace.throwing(this, "com.ibm.mq.jmqi.MQPMO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", e, 3);
/*     */           }
/*     */           
/* 785 */           throw e;
/*     */         } 
/*     */         
/* 788 */         dc.writeI32(pos - beginPos, buffer, responseRecOffsetPosition, swap);
/*     */         
/* 790 */         for (int i = 0; i < this.recsPresent; i++) {
/* 791 */           pos = this.responseRecords[i].writeToBuffer(buffer, pos, ptrSize, swap, cp, tls);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 796 */     if (Trace.isOn) {
/* 797 */       Trace.exit(this, "com.ibm.mq.jmqi.MQPMO", "writeToBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 798 */           Integer.valueOf(pos));
/*     */     }
/* 800 */     return pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readFromBuffer(byte[] buffer, int offset, int ptrSize, boolean swap, JmqiCodepage cp, JmqiTls tls) throws JmqiException {
/* 808 */     if (Trace.isOn) {
/* 809 */       Trace.entry(this, "com.ibm.mq.jmqi.MQPMO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", new Object[] { buffer, 
/*     */             
/* 811 */             Integer.valueOf(offset), Integer.valueOf(ptrSize), Boolean.valueOf(swap), cp, tls });
/*     */     }
/* 813 */     int pos = offset;
/* 814 */     int startPos = offset;
/* 815 */     int putMsgRecOffset = -1;
/* 816 */     int responseRecOffset = -1;
/* 817 */     int variableDataEnd = -1;
/* 818 */     JmqiDC dc = ((JmqiSystemEnvironment)this.env).getDC();
/*     */     
/* 820 */     String strucId = dc.readMQField(buffer, pos, 4, cp, tls);
/* 821 */     if (!strucId.equals("PMO ")) {
/*     */       
/* 823 */       JmqiException traceRet1 = new JmqiException(this.env, -1, null, 2, 2173, null);
/* 824 */       if (Trace.isOn) {
/* 825 */         Trace.throwing(this, "com.ibm.mq.jmqi.MQPMO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", traceRet1);
/*     */       }
/*     */       
/* 828 */       throw traceRet1;
/*     */     } 
/* 830 */     pos += 4;
/*     */ 
/*     */ 
/*     */     
/* 834 */     pos += 16;
/*     */ 
/*     */     
/* 837 */     this.knownDestCount = dc.readI32(buffer, pos, swap);
/* 838 */     pos += 4;
/*     */ 
/*     */     
/* 841 */     this.unknownDestCount = dc.readI32(buffer, pos, swap);
/* 842 */     pos += 4;
/*     */ 
/*     */     
/* 845 */     this.invalidDestCount = dc.readI32(buffer, pos, swap);
/* 846 */     pos += 4;
/*     */ 
/*     */     
/* 849 */     this.resolvedQName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 850 */     pos += 48;
/*     */ 
/*     */     
/* 853 */     this.resolvedQMgrName = dc.readMQField(buffer, pos, 48, cp, tls);
/* 854 */     pos += 48;
/*     */ 
/*     */     
/* 857 */     if (this.version >= 2) {
/*     */ 
/*     */       
/* 860 */       this.recsPresent = dc.readI32(buffer, pos, swap);
/* 861 */       pos += 4;
/*     */ 
/*     */       
/* 864 */       this.putMsgRecFields = dc.readI32(buffer, pos, swap);
/* 865 */       pos += 4;
/*     */ 
/*     */       
/* 868 */       putMsgRecOffset = dc.readI32(buffer, pos, swap);
/* 869 */       pos += 4;
/*     */ 
/*     */       
/* 872 */       if (this.recsPresent > 0 && putMsgRecOffset != 0 && this.putMsgRecFields != 0) {
/*     */         
/* 874 */         if (this.putMsgRecords == null) {
/* 875 */           this.putMsgRecords = new MQPMR[this.recsPresent];
/*     */         }
/* 877 */         else if (this.putMsgRecords.length < this.recsPresent) {
/* 878 */           MQPMR[] newPutMsgRecords = new MQPMR[this.recsPresent];
/* 879 */           for (int j = this.putMsgRecords.length; j < this.recsPresent; j++) {
/* 880 */             newPutMsgRecords[j] = this.putMsgRecords[j];
/*     */           }
/* 882 */           this.putMsgRecords = newPutMsgRecords;
/*     */         } 
/*     */         
/* 885 */         int pmrEnd = startPos + putMsgRecOffset;
/* 886 */         for (int i = 0; i < this.recsPresent; i++) {
/* 887 */           if (this.putMsgRecords[i] == null) {
/* 888 */             this.putMsgRecords[i] = this.env.newMQPMR();
/* 889 */             this.putMsgRecords[i].setOwningPmo(this);
/*     */           } 
/* 891 */           pmrEnd = this.putMsgRecords[i].readFromBuffer(buffer, pmrEnd, ptrSize, swap, cp, tls);
/*     */         } 
/* 893 */         if (pmrEnd > variableDataEnd) {
/* 894 */           variableDataEnd = pmrEnd;
/*     */         }
/*     */       } else {
/*     */         
/* 898 */         this.putMsgRecords = null;
/*     */       } 
/*     */ 
/*     */       
/* 902 */       responseRecOffset = dc.readI32(buffer, pos, swap);
/* 903 */       pos += 4;
/*     */ 
/*     */       
/* 906 */       if (this.recsPresent > 0 && responseRecOffset != 0) {
/*     */         
/* 908 */         if (this.responseRecords == null) {
/* 909 */           this.responseRecords = new MQRR[this.recsPresent];
/*     */         }
/* 911 */         else if (this.responseRecords.length < this.recsPresent) {
/* 912 */           MQRR[] newResponseRecords = new MQRR[this.recsPresent];
/* 913 */           for (int j = this.responseRecords.length; j < this.recsPresent; j++) {
/* 914 */             newResponseRecords[j] = this.responseRecords[j];
/*     */           }
/* 916 */           this.responseRecords = newResponseRecords;
/*     */         } 
/* 918 */         int rrEnd = startPos + responseRecOffset;
/* 919 */         for (int i = 0; i < this.recsPresent; i++) {
/* 920 */           if (this.responseRecords[i] == null) {
/* 921 */             this.responseRecords[i] = this.env.newMQRR();
/*     */           }
/* 923 */           rrEnd = this.responseRecords[i].readFromBuffer(buffer, rrEnd, ptrSize, swap, cp, tls);
/*     */         } 
/* 925 */         if (rrEnd > variableDataEnd) {
/* 926 */           variableDataEnd = rrEnd;
/*     */         }
/*     */       } else {
/*     */         
/* 930 */         this.responseRecords = null;
/*     */       } 
/*     */ 
/*     */       
/* 934 */       pos += ptrSize + ptrSize;
/*     */     } 
/*     */ 
/*     */     
/* 938 */     if (this.version >= 3) {
/*     */ 
/*     */       
/* 941 */       this.originalMsgHandle = dc.readI64(buffer, pos, swap);
/* 942 */       pos += 8;
/*     */ 
/*     */       
/* 945 */       this.newMsgHandle = dc.readI64(buffer, pos, swap);
/* 946 */       pos += 8;
/*     */ 
/*     */       
/* 949 */       this.action = dc.readI32(buffer, pos, swap);
/* 950 */       pos += 4;
/*     */ 
/*     */       
/* 953 */       this.subLevel = dc.readI32(buffer, pos, swap);
/* 954 */       pos += 4;
/*     */ 
/*     */       
/* 957 */       int padding = JmqiTools.alignToGrain(ptrSize, pos);
/* 958 */       pos += padding;
/*     */     } 
/*     */ 
/*     */     
/* 962 */     if (variableDataEnd > pos) {
/* 963 */       pos = variableDataEnd;
/*     */     }
/*     */     
/* 966 */     if (Trace.isOn) {
/* 967 */       Trace.exit(this, "com.ibm.mq.jmqi.MQPMO", "readFromBuffer(byte [ ],int,int,boolean,JmqiCodepage,JmqiTls)", 
/* 968 */           Integer.valueOf(pos));
/*     */     }
/* 970 */     return pos;
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
/* 982 */     fmt.add("version", this.version);
/* 983 */     fmt.add("options", this.options);
/* 984 */     String optionDescription = MQConstants.decodeOptionsForTrace(this.options, "MQPMO_.*");
/* 985 */     fmt.add("option flags", optionDescription);
/* 986 */     fmt.add("context", this.context);
/* 987 */     fmt.add("knownDestCount", this.knownDestCount);
/* 988 */     fmt.add("unknownDestCount", this.unknownDestCount);
/* 989 */     fmt.add("invalidDestCount", this.invalidDestCount);
/* 990 */     fmt.add("resolvedQName", this.resolvedQName);
/* 991 */     fmt.add("resolvedQMgrName", this.resolvedQMgrName);
/* 992 */     fmt.add("recsPresent", this.recsPresent);
/* 993 */     fmt.add("putMsgRecFields", this.putMsgRecFields);
/* 994 */     fmt.add("putMsgRecords", this.putMsgRecords);
/* 995 */     fmt.add("responseRecords", this.responseRecords);
/* 996 */     fmt.add("originalMsgHandle", this.originalMsgHandle);
/* 997 */     fmt.add("newMsgHandle", this.newMsgHandle);
/* 998 */     fmt.add("action", this.action);
/* 999 */     fmt.add("subLevel", this.subLevel);
/*     */   }
/*     */ }


/* Location:              D:\download\com.ibm.mq.allclient-9.3.2.0.jar!\com\ibm\mq\jmqi\MQPMO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */